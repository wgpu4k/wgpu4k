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

import com.sun.jna.Pointer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.rococoa.*;
import org.rococoa.cocoa.CFIndex;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Listens to invocations of methods on a Java NSObject, and forwards them to
 * its Objective-C counterpart.
 *
 * @author duncan
 */
public class ObjCObjectInvocationHandler implements InvocationHandler, MethodInterceptor {

    private static final int FINALIZE_AUTORELEASE_BATCH_SIZE = 1000;

    private static final Logger logging = Logger.getLogger("org.rococoa.proxy");

    static final Method OBJECT_TOSTRING;
    static final Method OBJECT_HASHCODE;
    static final Method OBJECT_EQUALS;
    static final Method OCOBJECT_ID;

    static {
        try {
            OBJECT_TOSTRING = Object.class.getMethod("toString");
            OBJECT_HASHCODE = Object.class.getMethod("hashCode");
            OBJECT_EQUALS = Object.class.getMethod("equals", Object.class);
            OCOBJECT_ID = ObjCObject.class.getMethod("id");
        } catch (NoSuchMethodException x) {
            throw new RococoaException("Error retrieving method", x);
        }
    }

    private ID ocInstance;
    private final String javaClassName;
    private final boolean invokeAllMethodsOnMainThread;
    private final boolean releaseOnFinalize;
    private volatile boolean finalized;

    public ObjCObjectInvocationHandler(final ID ocInstance, Class<? extends ObjCObject> javaClass, boolean retain) {
        this.ocInstance = ocInstance;
        javaClassName = javaClass.getSimpleName();
        invokeAllMethodsOnMainThread = shouldInvokeMethodsOnMainThread(javaClass);
        releaseOnFinalize = shouldReleaseInFinalize(javaClass);

        if (logging.isLoggable(Level.FINEST)) {
            CFIndex retainCount = Foundation.cfGetRetainCount(ocInstance);
            logging.finest(String.format("Creating ObjCObjectInvocationHandler for id %s, javaclass %s. retain = %s, retainCount = %s",
                    ocInstance, javaClass, retain, retainCount.intValue()));
        }

        if (ocInstance.isNull()) {
            throw new NullPointerException();
        }

        if (retain) {
            if (callAcrossToMainThread()) {
                Foundation.runOnMainThread(() -> Foundation.cfRetain(ocInstance));
            } else {
                Foundation.cfRetain(ocInstance);
            }
        }
    }

    private boolean shouldReleaseInFinalize(Class<? extends ObjCObject> javaClass) {
        // Almost everything should be released in finalize, except wrappers for
        // NSAutoreleasePool.
        ReleaseInFinalize annotation = javaClass.getAnnotation(ReleaseInFinalize.class);
        if (annotation == null) {
            return true;
        }
        return annotation.value();
    }

    @Override
    protected void finalize() throws Throwable {
        if (finalized || !releaseOnFinalize) {
            return;
        }
        try {
            if (callAcrossToMainThread()) {
                Foundation.runOnMainThread(() -> release());
            } else {
                AutoreleaseBatcher autoreleaseBatcher = AutoreleaseBatcher.forThread(FINALIZE_AUTORELEASE_BATCH_SIZE);
                release();
                autoreleaseBatcher.operate();
            }
            super.finalize();
        } finally {
            finalized = true;
        }
        super.finalize();
    }

    // must be run on appropriate thread
    private void release() {
        if (ocInstance.isNull()) {
            return;
        }
        if (logging.isLoggable(Level.FINEST)) {
            CFIndex retainCount = Foundation.cfGetRetainCount(ocInstance);
            logging.finest(String.format("finalizing [%s %s], releasing with retain count = %s",
                    javaClassName, ocInstance, retainCount.intValue()));
        }
        Foundation.cfRelease(ocInstance);
    }

    /**
     * Callback from java.lang.reflect proxy
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Exception {
        if (logging.isLoggable(Level.FINEST)) {
            logging.finest(String.format("invoking [%s %s].%s(%s)",
                    javaClassName, ocInstance, method.getName(), new VarArgsUnpacker(args)));
        }
        if (isSpecialMethod(method)) {
            return invokeSpecialMethod(method, args);
        }
        return invokeCocoa(method, args);
    }

    /**
     * Callback from cglib proxy
     */
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        if (logging.isLoggable(Level.FINEST)) {
            logging.finest(String.format("invoking [%s %s].%s(%s)",
                    javaClassName, ocInstance, method.getName(), new VarArgsUnpacker(args)));
        }
        if (isSpecialMethod(method)) {
            return invokeSpecialMethod(method, args);
        }
        if (!Modifier.isAbstract(method.getModifiers())) {
            // method is not abstract, so a Java override has been provided, which we call
            return methodProxy.invokeSuper(proxy, args);
        }
        // normal case
        return invokeCocoa(method, args);
    }

    private boolean isSpecialMethod(Method method) {
        return (OBJECT_TOSTRING.equals(method) ||
                OBJECT_EQUALS.equals(method) ||
                OCOBJECT_ID.equals(method));
    }

    private Object invokeSpecialMethod(final Method method, final Object[] args) {
        if (OBJECT_TOSTRING.equals(method)) {
            return invokeDescription();
        }
        if (OBJECT_EQUALS.equals(method)) {
            if (args[0] == null) {
                return false;
            }
            if (args[0] instanceof ObjCObject) {
                return invokeIsEqual(((ObjCObject) args[0]).id());
            }
            return false;
        }
        if (OCOBJECT_ID.equals(method)) {
            return ocInstance;
        }
        throw new IllegalArgumentException("Not a special method " + method);
    }

    private Object invokeDescription() {
        return sendOnThisOrMainThread(null, ocInstance, "description", String.class);
    }

    private Object invokeIsEqual(final ID another) {
        return sendOnThisOrMainThread(null, ocInstance, "isEqual:", Boolean.class, another);
    }

    protected Object invokeCocoa(final Method method, Object[] args) {
        String selectorName = selectorNameFor(method);
        Class<?> returnType = returnTypeFor(method);
        Object[] marshalledArgs = marshallArgsFor(args);

        Object result = sendOnThisOrMainThread(method, ocInstance, selectorName, returnType, marshalledArgs);
        if (method.getName().startsWith("init")) {
            handleInitMethod(result);
        }
        fillInReferences(args, marshalledArgs);

        if (result instanceof Pointer && method.getReturnType().equals(String.class)) {
            // special case for return char*
            return ((Pointer) result).getString(0);
        }
        if (result instanceof ID) {
            if (((ID) result).isNull()) {
                return null;
            }
        }
        return result;
    }

    private void handleInitMethod(Object result) {
        // Normally init methods return self, but on error they may return nil.
        // In this case the ObjC object for which this is the handler is considered
        // freed and should not be released when we are finalized.
        if (result != null) {
            return;
        }
        ocInstance = ID.fromLong(0);
    }

    private Object sendOnThisOrMainThread(Method method, final ID id, final String selectorName, final Class<?> returnType, final Object... args) {
        if (callAcrossToMainThreadFor(method)) {
            return Foundation.callOnMainThread(
                    (Callable<Object>) () -> Foundation.send(id, selectorName, returnType, method, args));
        } else {
            return Foundation.send(id, selectorName, returnType, method, args);
        }
    }

    /**
     * We need to make sure that we have filled in all NSObjectByReferences
     * so that they are retained.
     */
    private void fillInReferences(Object[] args, Object[] marshalledArgs) {
        if (args == null) {
            return;
        }
        for (int i = 0; i < args.length; i++) {
            Object original = args[i];
            Object marshalled = marshalledArgs[i];
            if (marshalled instanceof IDByReference) {
                if (!(original instanceof ObjCObjectByReference)) {
                    logging.severe("Bad marshalling");
                    continue;
                }
                ((ObjCObjectByReference) original).setObject(
                        Rococoa.wrap(((IDByReference) marshalled).getValue(), ObjCObject.class));
            }
        }
    }

    private Class<?> returnTypeFor(final Method method) {
        ReturnType annotation = method.getAnnotation(ReturnType.class);
        if (annotation == null) {
            return method.getReturnType();
        } else {
            return annotation.value();
        }
    }

    private Object[] marshallArgsFor(Object[] args) {
        if (args == null) {
            return null;
        }
        List<Object> result = new ArrayList<>(args.length);
        for (Object arg : args) {
            final Object marshalled = marshall(arg);
            if (marshalled instanceof Object[]) {
                // flatten varags, it would never(?) make sense to pass Object[] to Cococoa
                result.addAll(Arrays.asList((Object[]) marshalled));
            } else {
                result.add(marshalled);
            }
        }
        return result.toArray(new Object[result.size()]);
    }

    private Object marshall(Object arg) {
        // Note that this is not the only marshalling that is done.
        // RococoaTypeMapper also gets involved.
        if (arg == null) {
            return null;
        }
        if (arg instanceof ObjCObjectByReference) {
            // Forward conversion (another backwards conversion will take place in fillInReferences)
            IDByReference idref = new IDByReference();
            ObjCObject ob = ((ObjCObjectByReference) arg).getValueAs(ObjCObject.class);
            if (ob != null) {
                idref.setValue(ob.id());
            }
            return idref;
        }
        return arg;
    }

    private String selectorNameFor(Method method) {
        String methodName = method.getName();
        if (methodName.endsWith("_")) {
            // lets us append _ to allow Java keywords as method names
            methodName = methodName.substring(0, methodName.length() - 1);
        }
        if (method.getParameterTypes().length == 0) {
            return methodName;
        }
        String[] parts = methodName.split("_");
        StringBuilder result = new StringBuilder();
        for (String part : parts) {
            result.append(part).append(":");
        }
        return result.toString();
    }

    private boolean shouldInvokeMethodsOnMainThread(AnnotatedElement element) {
        return element != null && element.getAnnotation(RunOnMainThread.class) != null;
    }

    private boolean callAcrossToMainThread() {
        return callAcrossToMainThreadFor(null);
    }

    private boolean callAcrossToMainThreadFor(Method m) {
        return (invokeAllMethodsOnMainThread || shouldInvokeMethodsOnMainThread(m)) && !Foundation.isMainThread();
    }
}
