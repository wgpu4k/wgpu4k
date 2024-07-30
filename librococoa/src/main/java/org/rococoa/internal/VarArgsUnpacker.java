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

public class VarArgsUnpacker {

    private static final String SEPERATOR = ", ";
    private static final Object[] NULLARGS = new Object[0];

    private final Object[] args;

    public VarArgsUnpacker(Object... args) {
        this.args = args != null ? args : NULLARGS;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Object arg : args) {
            result.append(arg).append(SEPERATOR);
        }
        if (result.length() > 0) {
            result.setLength(result.length() - SEPERATOR.length());
        }
        return result.toString();
    }

}
