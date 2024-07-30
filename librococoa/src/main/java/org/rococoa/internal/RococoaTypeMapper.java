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

import org.rococoa.ObjCObject;
import org.rococoa.cocoa.foundation.NSObject;

import com.sun.jna.DefaultTypeMapper;
import com.sun.jna.FromNativeConverter;

/**
 * A JNA TypeMapper that knows how to convert :
 * <ul>
 *   <li>{@link NSObject} to and from an integer type with the right size to be an id.</li>
 *   <li>{@link String} to and from an integer type with the right size to be an id.</li>
 *   <li>{@link boolean} to a byte with the right values for Mac.</li>
 * </ul>
 * <p>
 * Note that nativeType is never NativeLong, but the appropriate Java primitive
 * with the right size of NativeLong.
 * <p>
 * TypeMappers are consulted by JNA to know how to convert between Java values
 * and objects and native values.
 *
 * @author duncan
 */
public class RococoaTypeMapper extends DefaultTypeMapper {

    public RococoaTypeMapper() {
        addToNativeConverter(ObjCObject.class, new ObjCObjectTypeConverter<ObjCObject>(ObjCObject.class));
        addToNativeConverter(Boolean.class, new BoolConverter());
        addFromNativeConverter(Boolean.class, new BoolConverter());
        addTypeConverter(String.class, new StringTypeConverter());
        // addToNativeConverter(NSObjectByReference.class, new ObjectByReferenceConverter());
        // not actually used at present because NSObjectInvocationHandler does marshalling
    }

    @SuppressWarnings("unchecked")
    @Override
    public FromNativeConverter getFromNativeConverter(Class javaType) {
        if (ObjCObject.class.isAssignableFrom(javaType)) {
            // return a new converter that knows the subtype it is going to create
            return new ObjCObjectTypeConverter((Class<ObjCObject>) javaType);
        }
        return super.getFromNativeConverter(javaType);
    }

}
