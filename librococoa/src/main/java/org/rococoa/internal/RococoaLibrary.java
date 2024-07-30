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


import org.rococoa.ID;

import com.sun.jna.Callback;
import com.sun.jna.Library;

/**
 * JNA Library for special operations provided by our own native code
 */
public interface RococoaLibrary extends Library {

    /**
     * Callback from Obj-C to get method signature string for Java method matching selectorName
     */
    interface MethodSignatureCallback extends Callback {
        String callback(String selectorName);
    }

    /**
     * Callback from Obj-C to invoke method on Java object
     */
    interface SelectorInvokedCallback extends Callback {
        void callback(String selectorName, ID nsInvocation);
    }

    /**
     * Return an Obj-C object that will callback on methodSignature required and
     * selector invoked, so that we can use a Java object to implement.
     */
    ID proxyForJavaObject(SelectorInvokedCallback selectorInvokedCallback,
                          MethodSignatureCallback methodSignatureCallback);

    /**
     * Generic callback from Obj-C
     */
    interface VoidCallback extends Callback {
        void callback();
    }

    /**
     * Call callback on the main Cococa event thread
     */
    void callOnMainThread(RococoaLibrary.VoidCallback callback, boolean waitUntilDone);
}