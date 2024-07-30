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

public abstract class NSError extends NSObject {

    public static final _Class CLASS = Rococoa.createClass("NSError", _Class.class); //$NON-NLS-1$

    public interface _Class extends ObjCClass {
        NSError alloc();

        NSError errorWithDomain_code_userInfo(String domain, NSInteger code, NSDictionary userInfo);
    }

    public abstract NSError initWithDomain_code_userInfo(String domain, NSInteger code, NSDictionary userInfo);

    public abstract NSInteger code();

    public abstract String domain();

    public abstract String localizedDescription();

    public abstract String localizedRecoverySuggestion();

    public abstract NSArray localizedRecoveryOptions();

    public abstract String localizedFailureReason();

    public abstract NSObject recoveryAttempter();

    public abstract NSDictionary userInfo();
}
