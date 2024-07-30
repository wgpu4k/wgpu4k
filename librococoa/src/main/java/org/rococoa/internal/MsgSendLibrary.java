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


import com.sun.jna.Library;
import com.sun.jna.Structure;
import org.rococoa.ID;
import org.rococoa.Selector;

/**
 * JNA Library for special message send calls, called and marshalled specially.
 */
public interface MsgSendLibrary extends Library {
    // This doesn't exist in the library, but is synthesised by msgSendHandler
    Object syntheticSendMessage(Class<?> returnType, ID receiver, Selector selector, Object... args);

    Object syntheticSendVarArgsMessage(Class<?> returnType, ID receiver, Selector selector, Object... args);

    // We don't call these directly, but through syntheticSendMessage
    Object objc_msgSend(ID receiver, Selector selector, Object... args);

    Object objc_msgSend(ID receiver, Selector selector, Object arg, Object... args);

    Structure objc_msgSend_stret(ID receiver, Selector selector, Object... args);
}