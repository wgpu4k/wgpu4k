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

package org.rococoa;

import java.lang.reflect.Proxy;

import net.sf.cglib.core.DefaultNamingPolicy;
import net.sf.cglib.core.Predicate;
import net.sf.cglib.proxy.Enhancer;

import org.rococoa.cocoa.CFIndex;
import org.rococoa.internal.OCInvocationCallbacks;
import org.rococoa.internal.ObjCObjectInvocationHandler;
import org.rococoa.internal.VarArgsUnpacker;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Static factory for creating Java wrappers for Objective-C instances, and Objective-C
 * wrappers for Java instances. <strong>START HERE</strong>.
 *
 * @author duncan
 */
public abstract class Rococoa {

    private static final Logger logging = Logger.getLogger("org.rococoa.proxy");

    /**
     * Create a Java NSClass representing the Objective-C class with ocClassName
     */
    public static <T extends ObjCClass> T createClass(String ocClassName, Class<T> type) {
        return wrap(Foundation.getClass(ocClassName), type, false);
    }

    /**
     * Create a Java NSObject representing an instance of the Objective-C class
     * ocClassName. The Objective-C instance is created by calling the static
     * factory method named ocMethodName, passing args.
     */
    public static <T extends ObjCObject> T create(String ocClassName, Class<T> javaClass, String ocMethodName, Object... args) {
        boolean weOwnObject = Foundation.selectorNameMeansWeOwnReturnedObject(ocMethodName);

        // If we don't own the object we know that it has been autorelease'd
        // But we need to own these objects, so that they are not dealloc'd when
        // the pool is release'd. So we retain them.
        // Objects that we own (because they were created with 'alloc' or 'new')
        // have not been autorelease'd, so we don't retain them.
        boolean retain = !weOwnObject;
        return create(ocClassName, javaClass, ocMethodName, retain, args);
    }

    /**
     * Create a Java NSObject representing an instance of the Objective-C class
     * ocClassName, created with the class method <code>+new</code>.
     */
    public static <T extends ObjCObject> T create(String ocClassName, Class<T> javaClass) {
        return create(ocClassName, javaClass, "new");
    }

    private static <T extends ObjCObject> T create(String ocClassName, Class<T> javaClass,
                                                   String ocFactoryName,
                                                   boolean retain,
                                                   Object... args) {
        if (logging.isLoggable(Level.FINEST)) {
            logging.finest(String.format("creating [%s (%s)].%s(%s)",
                    ocClassName, javaClass.getName(), ocFactoryName, new VarArgsUnpacker(args)));
        }
        ID ocClass = Foundation.getClass(ocClassName);
        ID ocInstance = Foundation.send(ocClass, ocFactoryName, ID.class, args);
        CFIndex initialRetainCount = Foundation.cfGetRetainCount(ocInstance);
        T result = wrap(ocInstance, javaClass, retain);
        checkRetainCount(ocInstance, retain ? initialRetainCount.intValue() + 1 : initialRetainCount.intValue());
        return result;
    }

    /**
     * Create a Java NSObject wrapping an existing Objective-C instance, represented
     * by id.
     * <p>
     * The NSObject is retained, and released when the object is GC'd.
     */
    public static <T extends ObjCObject> T wrap(ID id, Class<T> javaClass) {
        return wrap(id, javaClass, true);
    }

    /**
     * Create a Java NSObject down-casting an existing NSObject to a more derived
     * type.
     */
    public static <T extends ObjCObject> T cast(ObjCObject object, Class<T> desiredType) {
        if (object == null) {
            return null;
        }
        return wrap(object.id(), desiredType, true);
    }

    public static <T extends ObjCObject> T wrap(ID id, Class<T> javaClass, boolean retain) {
        if (id == null || id.isNull()) {
            return null;
        }
        // Why would we not want to retain? Well if we are wrapping a Core Foundation
        // created object, or one created with new (alloc init), it will not
        // have been autorelease'd. 
        ObjCObjectInvocationHandler invocationHandler = new ObjCObjectInvocationHandler(id, javaClass, retain);
        return createProxy(javaClass, invocationHandler);
    }

    /**
     * Return the ID of a new Objective-C object that will forward messages to
     * javaObject.
     * <p>
     * Keep hold of the ID all the time that methods may be invoked on the Obj-C
     * object, otherwise the callbacks may be GC'd, with amusing consequences.
     *
     * @deprecated because the OC proxy object is never released.
     * Use {@link Rococoa#proxy} instead.
     */
    @Deprecated
    public static ID wrap(Object javaObject) {
        OCInvocationCallbacks callbacks = new OCInvocationCallbacks(javaObject);
        ID idOfOCProxy = Foundation.newOCProxy(callbacks);
        // idOfOCProxy is owned by us, and we have to release it at some stage
        return new ProxyID(idOfOCProxy, callbacks);
    }

    /**
     * Return a new Objective-C object that will forward messages to javaObject,
     * for use in delegates, notifications etc.
     * <p>
     * You need to keep a reference to the returned value for as long as it is
     * active. When it is GC'd, it will release the Objective-C proxy.
     */
    public static ObjCObject proxy(Object javaObject) {
        return proxy(javaObject, ObjCObject.class);
    }

    public static <T extends ObjCObject> T proxy(Object javaObject, Class<T> javaType) {
        ID proxyID = wrap(javaObject);
        // we own the proxyID, so by wrapping it as NSObject, we can arrange for
        // it to be release'd when the NSObject is finalized
        return wrap(proxyID, javaType, false);
    }

    /**
     * Create a java.lang.reflect.Proxy or cglib proxy of type, which forwards
     * invocations to invococationHandler.
     */
    @SuppressWarnings("unchecked")
    private static <T> T createProxy(final Class<T> type, ObjCObjectInvocationHandler invocationHandler) {
        if (type.isInterface()) {
            return (T) Proxy.newProxyInstance(
                    invocationHandler.getClass().getClassLoader(),
                    new Class[]{type}, invocationHandler);
        } else {
            Enhancer e = new Enhancer();
            e.setUseCache(true); // make sure that we reuse if we've already defined
            e.setNamingPolicy(new DefaultNamingPolicy() {
                public String getClassName(String prefix, String source, Object key, Predicate names) {
                    if (source.equals(net.sf.cglib.proxy.Enhancer.class.getName())) {
                        return type.getName() + "$$ByRococoa";
                    } else {
                        return super.getClassName(prefix, source, key, names);
                    }
                }
            });
            e.setSuperclass(type);
            e.setCallback(invocationHandler);
            return (T) e.create();
        }
    }

    // Public only because JNA doesn't call setAccessible to access ctor.
    public static class ProxyID extends ID {
        // used to prevent callbacks being GC'd as long as we hang onto this ID
        @SuppressWarnings("unused")
        private OCInvocationCallbacks callbacks;

        public ProxyID() {
            // required by jna
        }

        public ProxyID(ID anotherID, OCInvocationCallbacks callbacks) {
            super(anotherID);
            this.callbacks = callbacks;
        }
    }

    private static void checkRetainCount(ID ocInstance, int expected) {
        CFIndex retainCount = Foundation.cfGetRetainCount(ocInstance);
        if (retainCount.intValue() != expected) {
            logging.warning("Created an object which had a retain count of " + retainCount + " not " + expected);
        }
    }

    /**
     * Enforce static factory-ness.
     */
    private Rococoa() {
    }
}
