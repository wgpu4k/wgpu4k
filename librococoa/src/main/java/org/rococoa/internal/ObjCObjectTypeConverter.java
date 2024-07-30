/*
 * Copyright 2007, 2008, 2009 Duncan McGregor
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

/**
 *
 */
package org.rococoa.internal;

import org.rococoa.Foundation;
import org.rococoa.ID;
import org.rococoa.ObjCObject;
import org.rococoa.Rococoa;
import org.rococoa.Selector;

import com.sun.jna.FromNativeContext;
import com.sun.jna.FunctionResultContext;
import com.sun.jna.NativeMapped;
import com.sun.jna.ToNativeContext;
import com.sun.jna.TypeConverter;

/**
 * A JNA TypeConverter that gets the ID from an NSObject when passing it into 
 * native code, and creates an instance of subclass of NSObject to wrap an id
 * when receiving one from native code.
 */
class ObjCObjectTypeConverter<T extends ObjCObject> implements TypeConverter {

    private static final NativeMapped nativeLongConverter = new ID();

    private final Class<T> javaType;

    public ObjCObjectTypeConverter(Class<T> javaType) {
        this.javaType = javaType;
    }

    public Class<?> nativeType() {
        // we can't return NativeLong here - has to be a primitive type, so
        // delegate so that we are 32/64 correct
        return nativeLongConverter.nativeType();
    }

    // Takes an Integer or Long representing id (32 or 64 bit respectively)
    // and returns an NSObject of javaType with that id.
    public T fromNative(Object nativeValue, FromNativeContext context) {
        Number nativeValueAsNumber = (Number) nativeValue;
        if (nativeValueAsNumber == null) {
            return null;
        }
        ID id = ID.fromLong(nativeValueAsNumber.longValue());
        if (id.isNull()) {
            return null;
        }
        boolean shouldRetain = shouldRetainFor(context);
        return Rococoa.wrap(id, javaType, shouldRetain);
    }

    // Takes an NSObject and returns its id as Integer or Long
    public Object toNative(Object value, ToNativeContext context) {
        if (value == null) {
            return null;
        }
        ObjCObject valueAsNSObject = (ObjCObject) value;
        ID idToReturn = valueAsNSObject.id();
        return idToReturn.toNative();
    }

    // For tests only
    boolean convertsJavaType(Class<?> javaType) {
        return this.javaType == javaType;
    }

    private boolean shouldRetainFor(FromNativeContext context) {
        // Generally we should default to retaining, as by default NSObjects that
        // are returned from methods are owned by the current autorelease pool and
        // unless we retain will be dealloc'ed when is is drained.
        if (!(context instanceof FunctionResultContext resultContext)) {
            return true;
        }
        // The exception is if this conversion is for an object that we own, because
        // the selector name matches those
        Object[] arguments = resultContext.getArguments();
        if (arguments.length < 2) {
            return true;
        }
        if (!(arguments[1] instanceof Selector)) {
            return true;
        }
        boolean dontRetain = Foundation.selectorNameMeansWeOwnReturnedObject(((Selector) arguments[1]).getName());
        return !dontRetain; // OK Smartarse, you express it better.
    }
}