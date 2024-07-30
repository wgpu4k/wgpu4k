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

package org.rococoa.internal;

import org.rococoa.Foundation;
import org.rococoa.ID;

import com.sun.jna.FromNativeContext;
import com.sun.jna.NativeMapped;
import com.sun.jna.ToNativeContext;
import com.sun.jna.TypeConverter;

/**
 * Converts between java.lang.String and Cocooa id, which it needs to return
 * as Integer or Long depending on platform
 */
class StringTypeConverter implements TypeConverter {
    private static final NativeMapped nativeLongConverter = new ID();

    public Class<?> nativeType() {
        // see NSObjectTypeConverter.nativeType
        return nativeLongConverter.nativeType();
    }

    // Takes an Integer or Long representing id (32 or 64 bit respectively)
    // and returns a java.lang.String
    public String fromNative(Object nativeValue, FromNativeContext context) {
        Number nativeValueAsNumber = (Number) nativeValue;
        if (nativeValueAsNumber == null) {
            return null;
        }
        ID id = ID.fromLong(nativeValueAsNumber.longValue());
        if (id.isNull()) {
            return null;
        }
        return Foundation.toString(id);
    }

    // Takes java.lang.String and returns value of an id as Integer or Long
    public Object toNative(Object value, ToNativeContext context) {
        if (value == null) {
            return null;
        }
        String valueAsString = (String) value;
        ID valueAsID = Foundation.cfString(valueAsString);
        Foundation.sendReturnsID(valueAsID, "autorelease");
        return valueAsID.toNative();
    }
}