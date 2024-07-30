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
import org.rococoa.ID;
import org.rococoa.Selector;
import org.rococoa.cocoa.CFIndex;

/**
 * JNA Library for plain C calls, standard JNA marshalling applies to these
 */
public interface FoundationLibrary extends Library {

    void NSLog(ID pString, Object thing);

    ID CFStringCreateWithCString(ID allocator, String string, int encoding);

    ID CFStringCreateWithBytes(ID allocator, byte[] bytes, int byteCount, int encoding, byte isExternalRepresentation);

    String CFStringGetCStringPtr(ID string, int encoding);

    byte CFStringGetCString(ID theString, byte[] buffer, int bufferSize, int encoding);

    int CFStringGetLength(ID theString);

    ID CFRetain(ID cfTypeRef);

    void CFRelease(ID cfTypeRef);

    CFIndex CFGetRetainCount(ID cfTypeRef);

    ID objc_getClass(String className);

    ID class_createInstance(ID pClass, int extraBytes);

    Selector sel_registerName(String selectorName);
}