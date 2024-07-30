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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.rococoa.ID;
import org.rococoa.Rococoa;
import org.rococoa.RococoaException;
import org.rococoa.cocoa.foundation.NSInvocation;
import org.rococoa.cocoa.foundation.NSMethodSignature;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.sun.jna.Memory;

/**
 * Holds the callbacks called when a method is invoked on an Objective-C proxy
 * for a Java object.
 * <p>
 * When a message is sent to an OC object first it is sent
 * methodSignatureForSelector: Our Obj-C proxy forwards this to
 * methodSignatureCallback; we build a method signature string in Java
 * corresponding to the Java method and return it.
 * <p>
 * The object is then sent forwardInvocation: passing an NSInvocation. It
 * forwards this to selectorInvokedCallback, which we use to invoke the method
 * on the Java Object.
 *
 * @author duncan
 */
public class OCInvocationCallbacks {

    private static final Logger logging = Logger.getLogger("org.rococoa.callback");

    private final Object javaObject;

    /**
     * Called when method is about to be invoked on OC proxy and needs a method signature as String
     *
     * @see "http://www.cocoadev.com/index.pl?NSMethodSignature"
     */
    public final RococoaLibrary.MethodSignatureCallback methodSignatureCallback =
            new RococoaLibrary.MethodSignatureCallback() {
                public String callback(String selectorName) {
                    if (logging.isLoggable(Level.FINEST)) {
                        logging.finest(String.format("callback wanting methodSignature for selector %s", selectorName));
                    }
                    return methodSignatureForSelector(selectorName);
                }
            };

    /**
     * Called when method has been invoked on OC proxy and needs to be forwarded to javaObject
     */
    public final RococoaLibrary.SelectorInvokedCallback selectorInvokedCallback =
            new RococoaLibrary.SelectorInvokedCallback() {
                public void callback(String selectorName, ID nsInvocation) {
                    if (logging.isLoggable(Level.FINEST)) {
                        logging.finest(String.format("callback invoking %s on %s", selectorName, javaObject));
                    }
                    callMethod(javaObject, selectorName, Rococoa.wrap(nsInvocation, NSInvocation.class));
                }
            };

    public OCInvocationCallbacks(Object javaObject) {
        this.javaObject = javaObject;
    }

    protected String methodSignatureForSelector(String selectorName) {
        Method method = methodForSelector(selectorName);
        return method == null ?
                null :
                ocMethodSignatureAsString(method);
    }

    protected Method methodForSelector(String selectorName) {
        if (null == selectorName) {
            logging.severe("methodForSelector called with null selectorName");
            return null;
        }
        int parameterCount = countColons(selectorName);
        String methodName = methodNameForSelector(selectorName);
        try {
            Method[] methods = javaObject.getClass().getMethods();
            for (Method method : methods) {
                if (method.getName().equals(methodName) && method.getParameterTypes().length == parameterCount) {
                    boolean match = null != stringForType(method.getReturnType());
                    if (match) {
                        for (Class<?> parameterType : method.getParameterTypes()) {
                            if (null == stringForType(parameterType)) {
                                match = false;
                                break;
                            }
                        }
                    }
                    if (match) {
                        return method;
                    }
                }
            }
            logging.fine("No method " + methodName + " for selector:" + selectorName);
            return null;
        } catch (Exception e) {
            logging.log(Level.SEVERE, "Exception finding methodForSelector", e);
            return null;
        }
    }

    /**
     * @see "http://developer.apple.com/documentation/Cocoa/Conceptual/ObjectiveC/Articles/chapter_13_section_9.html"
     */
    protected String ocMethodSignatureAsString(Method method) {
        StringBuilder result = new StringBuilder();
        result.append(stringForType(method.getReturnType()));
        result.append("@:"); // self and cmd - id and selector
        for (Class<?> parameterType : method.getParameterTypes()) {
            result.append(stringForType(parameterType));
        }
        return result.toString();
    }

    private void callMethod(Object o, String selectorName, NSInvocation invocation) {
        try {
            Method method = methodForSelector(selectorName);

            NSMethodSignature nsMethodSignature = invocation.methodSignature();
            String typeToReturnToObjC = nsMethodSignature.methodReturnType();

            if (nsMethodSignature.numberOfArguments() - method.getParameterTypes().length != 2) { // self, _cmd
                throw new NoSuchMethodException(String.format(
                        "Number of arguments mismatch for invocation  of selector %s (%s arguments supplied), method %s expects %s",
                        selectorName, nsMethodSignature.numberOfArguments(), method.getName(), method.getParameterTypes().length));
            }
            if (typeToReturnToObjC.equals("v") && method.getReturnType() != void.class) {
                throw new NoSuchMethodException(String.format(
                        "Selector %s expects void return, but method %s returns %s",
                        selectorName, method.getName(), method.getReturnType()));
            }
            if (method.getReturnType() == void.class && !(typeToReturnToObjC.equals("v"))) {
                throw new NoSuchMethodException(String.format(
                        "Method %s returns void, but selector %s expects %s",
                        method.getName(), selectorName, typeToReturnToObjC));
            }
            Object[] marshalledArgs = argsForFrom(method, invocation, nsMethodSignature);
            method.setAccessible(true); // needed if implementation is an anonymous subclass of Object
            Object result = method.invoke(o, marshalledArgs);
            putResultIntoInvocation(invocation, typeToReturnToObjC, result);
        } catch (InvocationTargetException e) {
            logging.log(Level.SEVERE, "Exception calling method for selector " + selectorName, e);
            throw new RococoaException("Exception calling method for selector " + selectorName, e.getCause());
        } catch (Exception e) {
            logging.log(Level.SEVERE, "Exception calling method for selector " + selectorName, e);
            throw new RococoaException("Exception calling method for selector " + selectorName, e);
        }
    }

    private String methodNameForSelector(String selectorName) {
        String candidate = selectorName.replaceAll(":", "_");
        return candidate.endsWith("_") ?
                candidate.substring(0, candidate.length() - 1) :
                candidate;
    }

    private Object[] argsForFrom(Method method, NSInvocation invocation, NSMethodSignature nsMethodSignature) {
        Class<?>[] parameterTypes = method.getParameterTypes();
        Object[] result = new Object[parameterTypes.length];
        for (int i = 0; i < result.length; i++) {
            int indexAccountingForSelfAndCmd = 2 + i;
            result[i] = javaObjectForOCArgument(invocation,
                    indexAccountingForSelfAndCmd,
                    nsMethodSignature.getArgumentTypeAtIndex(indexAccountingForSelfAndCmd),
                    parameterTypes[i]);
        }
        return result;
    }

    /**
     * At this point we have an NSInvocation, which has the arguments to the
     * call in it. We know the type of the argument, and the type of the
     * parameter expected by the Java method. [1]
     * Our mission is to get the value of the argument from the NSInvocation
     * and return a Java object of the desired type.
     */
    private Object javaObjectForOCArgument(NSInvocation invocation,
                                           int indexInInvocation, String objCArgumentTypeAsString, Class<?> javaParameterType) {
        NSInvocationMapper mapper = NSInvocationMapperLookup.mapperForType(javaParameterType);
        if (mapper == null) {
            throw new IllegalStateException(
                    String.format("Don't (yet) know how to marshall argument Objective-C type %s as %s",
                            objCArgumentTypeAsString, javaParameterType));
        }
        return mapper.readArgumentFrom(invocation, indexInInvocation, javaParameterType);
    }

    private void putResultIntoInvocation(NSInvocation invocation, String typeToReturnToObjC, Object result) {
        if (typeToReturnToObjC.equals("v")) {
            if (result != null) {
                throw new IllegalStateException("Java method returned a result, but expected void");// void
            }
            return;
        }
        if (null == result) {
            return;
        }
        Memory buffer = bufferForReturn(result);
        if (buffer == null) {
            throw new IllegalStateException(
                    String.format("Don't (yet) know how to marshall result %s as Objective-C type %s", result, typeToReturnToObjC));
        }
        invocation.setReturnValue(buffer);
    }

    private Memory bufferForReturn(Object methodCallResult) {
        NSInvocationMapper mapper = NSInvocationMapperLookup.mapperForType(methodCallResult.getClass());
        return mapper == null ? null : mapper.bufferForResult(methodCallResult);
    }

    private int countColons(String selectorName) {
        int result = 0;
        for (int i = 0; i < selectorName.length(); i++) {
            if (selectorName.charAt(i) == ':') {
                result++;
            }
        }
        return result;
    }

    private String stringForType(Class<?> clas) {
        NSInvocationMapper result = NSInvocationMapperLookup.mapperForType(clas);
        if (result == null) {
            logging.warning("Unable to give Objective-C type string for Java type " + clas);
            return null;
        }
        return result.typeString();
    }

    /*
     * [1] - http://en.wikipedia.org/wiki/Parameter_(computer_science)
     * Although parameters are also commonly referred to as arguments,
     * arguments are more properly thought of as the actual values or references
     * assigned to the parameter variables when the subroutine is called at
     * runtime. When discussing code that is calling into a subroutine, any
     * values or references passed into the subroutine are the arguments, and
     * the place in the code where these values or references are given is the
     * parameter list. When discussing the code inside the subroutine
     * definition, the variables in the subroutine's parameter list are the
     * parameters, while the values of the parameters at runtime are the
     * arguments.
     */
}