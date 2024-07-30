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

package org.rococoa.cocoa.foundation;

import org.rococoa.ObjCClass;
import org.rococoa.Rococoa;

/**
 * @author Duncan McGregor
 * @author Paul Loy (added addEntriesFromDictionary for Growl contrib)
 */
public abstract class NSMutableDictionary extends NSDictionary {

    public static final _Class CLASS = Rococoa.createClass("NSMutableDictionary", _Class.class); //$NON-NLS-1$

    public interface _Class extends ObjCClass {
        NSMutableDictionary dictionaryWithCapacity(int numItems);

        NSMutableDictionary dictionaryWithObjects_forKeys(NSArray objects, NSArray keys);

        NSMutableDictionary dictionaryWithObjectsAndKeys(NSObject... objects);

        NSMutableDictionary dictionaryWithDictionary(NSDictionary dict);
    }

    public static NSMutableDictionary dictionaryWithCapacity(int numItems) {
        return CLASS.dictionaryWithCapacity(numItems);
    }

    public static NSMutableDictionary dictionaryWithObjects_forKeys(NSArray objects, NSArray keys) {
        return CLASS.dictionaryWithObjects_forKeys(objects, keys);
    }

    public static NSMutableDictionary dictionaryWithObjectsAndKeys(NSObject... objects) {
        return CLASS.dictionaryWithObjectsAndKeys(objects);
    }

    public static NSMutableDictionary dictionaryWithDictionary(NSDictionary dictionary) {
        return CLASS.dictionaryWithDictionary(dictionary);
    }

    public abstract void setValue_forKey(NSObject object, NSObject key);

    public abstract void setValue_forKey(NSObject object, String key);

    public abstract void addEntriesFromDictionary(NSDictionary dictionary);

}
