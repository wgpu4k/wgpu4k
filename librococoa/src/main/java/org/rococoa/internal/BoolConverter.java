/*
 * Copyright 2007-2010 Duncan McGregor
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

import com.sun.jna.FromNativeContext;
import com.sun.jna.FromNativeConverter;
import com.sun.jna.ToNativeContext;
import com.sun.jna.ToNativeConverter;

/**
 * Converts {@code java.lang.Boolean} to native by mapping to {@code java.lang.Integer} as defined by:
 * <code>
 * #define YES (BOOL) 1
 * #define NO (BOOL) 0
 * </code>
 *
 * @author <a href="mailto:harald.kuhr@gmail.com">Harald Kuhr</a>
 * @author last modified by $Author: haraldk$
 * @version $Id: BoolConverter.java,v 1.0 Feb 19, 2010 8:44:39 PM haraldk Exp$
 */
public class BoolConverter implements ToNativeConverter, FromNativeConverter {

    public Object toNative(final Object value, final ToNativeContext context) {
        return ((Boolean) value) ? 1 : 0;
    }

    public Object fromNative(final Object value, final FromNativeContext context) {
        return ((Byte) value).intValue() == 1;
    }

    public Class<Byte> nativeType() {
        return byte.class;
    }
}
