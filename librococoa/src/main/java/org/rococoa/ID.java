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

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import com.sun.jna.NativeLong;


/**
 * Represents an Objective-C ID.
 * <p>
 * This extends NativeLong for efficiency, but you should really think of it
 * as opaque.
 * <p>
 * Technically, this should be {@link Native#POINTER_SIZE} not {@link Native#LONG_SIZE},
 * but as they are both 32 on 32-bit and 64 on 64-bit we'll gloss over that. Ideally
 * it would be Pointer, but they have no protected constructors.
 */
public class ID extends NativeLong {

    public static ID fromLong(long value) {
        return new ID(value);
    }

    // Public for JNA
    public ID() {
        super();
    }

    protected ID(long value) {
        super(value);
    }

    protected ID(ID anotherID) {
        this(anotherID.longValue());
    }

    @Override
    public String toString() {
        return String.format("[ID 0x%x]", longValue()); //$NON-NLS-1$
    }

    public boolean isNull() {
        return longValue() == 0;
    }

    public static ID getGlobal(String libraryName, String globalVarName) {
        return new ID(NativeLibrary.getInstance(libraryName).getGlobalVariableAddress(globalVarName).getNativeLong(0).longValue());
    }
}
