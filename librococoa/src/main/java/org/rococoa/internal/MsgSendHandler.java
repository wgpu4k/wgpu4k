/*
 * Copyright 2007, 2008 Duncan McGregor
 *
 * This file is part of Rococoa, a library to allow Java to talk to Cocoa.
 *
 * Rococoa is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Rococoa is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Rococoa.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.rococoa.internal;

import com.sun.jna.Library;
import com.sun.jna.NativeLibrary;
import com.sun.jna.NativeLong;
import com.sun.jna.Structure;
import org.rococoa.ID;
import org.rococoa.RococoaException;
import org.rococoa.Selector;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Very special case InvocationHandler that invokes the correct message dispatch
 * function for different return types.
 * <p>
 * Either objc_msgSend or objc_msgSend_stret should be called, depending on the
 * return type. The latter is usually for struct by value, but the former is
 * used for small structs on Intel! Oh and the call has to be mangled in all
 * cases as the result is returned on the stack, but is different sizes
 * depending on its type. Luckily jna and libffi take care of the details -
 * provided they know what the return type is.
 * <p>
 * This InvocationHandler is passed the return type as the first arg to the method call that it
 * intercepts, it uses it to determine which function to call, and removes it before
 * calling invoking.
 *
 * @author duncan
 * @see "http://www.cocoabuilder.com/archive/message/cocoa/2006/6/25/166236"
 * @see "http://developer.apple.com/mac/library/documentation/DeveloperTools/Conceptual/LowLevelABI/Mac_OS_X_ABI_Function_Calls.pdf"
 * @see "http://www.sealiesoftware.com/blog/archive/2008/10/30/objc_explain_objc_msgSend_stret.html"
 * <p>
 * Note also that there is a objc_msgSend_fret that is used supposed to be for
 * floating point return types, but that I haven't (yet) had to use.
 * @see "http://www.sealiesoftware.com/blog/archive/2008/11/16/objc_explain_objc_msgSend_fpret.html"
 */
class MsgSendHandler implements InvocationHandler {

    /**
     * @see com.sun.jna.Function#OPTION_INVOKING_METHOD
     */
    private final String OPTION_INVOKING_METHOD = "invoking-method";

    private final static int I386_STRET_CUTOFF = 9;
    private final static int IA64_STRET_CUTOFF = 17;

    private final static int STRET_CUTOFF = NativeLong.SIZE == 8 ? IA64_STRET_CUTOFF : I386_STRET_CUTOFF;

    public final static boolean AARCH64 = System.getProperty("os.arch").trim().equalsIgnoreCase("aarch64");
    public final static boolean PPC = System.getProperty("os.arch").trim().equalsIgnoreCase("ppc");

    private final static Method OBJC_MSGSEND;
    private final static Method OBJC_MSGSEND_VAR_ARGS;
    private final static Method OBJC_MSGSEND_STRET;

    static {
        try {
            OBJC_MSGSEND = MsgSendLibrary.class.getDeclaredMethod("objc_msgSend",
                    ID.class, Selector.class, Object[].class);
            OBJC_MSGSEND_VAR_ARGS = MsgSendLibrary.class.getDeclaredMethod("objc_msgSend",
                    ID.class, Selector.class, Object.class, Object[].class);
            OBJC_MSGSEND_STRET = MsgSendLibrary.class.getDeclaredMethod("objc_msgSend_stret",
                    ID.class, Selector.class, Object[].class);
        } catch (NoSuchMethodException x) {
            throw new RococoaException(x);
        }
    }

    private final MethodFunctionPair objc_msgSend_stret_Pair;
    private final MethodFunctionPair objc_msgSend_varArgs_Pair;
    private final MethodFunctionPair objc_msgSend_Pair;

    private final RococoaTypeMapper rococoaTypeMapper = new RococoaTypeMapper();

    public MsgSendHandler(final NativeLibrary lib) {
        this.objc_msgSend_Pair = new MethodFunctionPair(AARCH64 ? null : OBJC_MSGSEND,
                lib.getFunction("objc_msgSend"));
        this.objc_msgSend_varArgs_Pair = new MethodFunctionPair(OBJC_MSGSEND_VAR_ARGS,
                lib.getFunction("objc_msgSend"));
        this.objc_msgSend_stret_Pair = new MethodFunctionPair(OBJC_MSGSEND_STRET,
                AARCH64 ? null : lib.getFunction("objc_msgSend_stret"));
    }

    public Object invoke(final Object proxy, final Method method, final Object[] args) {
        Class<?> returnTypeForThisCall = (Class<?>) args[0];
        MethodFunctionPair invocation = this.invocationFor(returnTypeForThisCall, MsgSendInvocationMapper.SYNTHETIC_SEND_VARARGS_MSG.equals(method));
        Map<String, Object> options = new HashMap<>(Collections.singletonMap(Library.OPTION_TYPE_MAPPER, rococoaTypeMapper));
        options.put(OPTION_INVOKING_METHOD, invocation.method);
        return invocation.function.invoke(returnTypeForThisCall, Arrays.copyOfRange(args, 1, args.length), options);
    }

    private MethodFunctionPair invocationFor(Class<?> returnTypeForThisCall, boolean varArgs) {
        if (AARCH64) {
            if (varArgs) {
                return objc_msgSend_varArgs_Pair;
            }
            return objc_msgSend_Pair;
        }
        boolean isStruct = Structure.class.isAssignableFrom(returnTypeForThisCall);
        boolean isStructByValue = isStruct && Structure.ByValue.class.isAssignableFrom(returnTypeForThisCall);
        if (!isStructByValue) {
            return objc_msgSend_Pair;
        }
        try {
            if (PPC) {
                // on ppc32 structs never return in registers
                return objc_msgSend_stret_Pair;
            }
            // on i386 structs with sizeof exactly equal to 1, 2, 4, or 8 return in registers
            Structure prototype = (Structure) returnTypeForThisCall.getDeclaredConstructor().newInstance();
            return prototype.size() < STRET_CUTOFF ? objc_msgSend_Pair : objc_msgSend_stret_Pair;
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                 InvocationTargetException e) {
            throw new RococoaException(e);
        }
    }
}
