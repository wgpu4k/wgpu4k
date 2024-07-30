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


import com.sun.jna.Native;
import org.rococoa.cocoa.foundation.NSInvocation;

import com.sun.jna.Memory;

/**
 * Maps to and from bytes in an NSInvocation to Java types.
 *
 * @author duncan
 */
public abstract class NSInvocationMapper {

    protected final Class<?> type;
    protected final String typeString;

    protected NSInvocationMapper(String typeString, Class<?> type) {
        this.type = type;
        this.typeString = typeString;
    }

    public String typeString() {
        return typeString;
    }

    public Object readArgumentFrom(NSInvocation invocation, int index, Class<?> type) {
        Memory buffer = new Memory(Native.LONG_SIZE); // big enough for long or double
        invocation.getArgument_atIndex(buffer, index);
        return readFrom(buffer, type);
    }

    protected Object readFrom(Memory buffer, Class<?> type) {
        throw new Error("Should be overridden or bypassed");
    }

    public abstract Memory bufferForResult(Object methodCallResult);

}
