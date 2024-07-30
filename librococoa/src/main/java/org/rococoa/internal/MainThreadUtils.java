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

import org.rococoa.Foundation;
import org.rococoa.ID;
import org.rococoa.RococoaException;
import org.rococoa.Selector;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Exists just to tidy up Foundation.
 *
 * @author duncan
 */
public abstract class MainThreadUtils {
    private static final Logger logging = Logger.getLogger("org.rococoa.foundation");

    private static final ID idNSThreadClass = Foundation.getClass("NSThread");
    private static final Selector isMainThreadSelector = Foundation.selector("isMainThread");

    private static final ThreadLocal<Boolean> isMainThreadThreadLocal = new ThreadLocal<Boolean>() {
        @Override
        protected Boolean initialValue() {
            return nsThreadSaysIsMainThread();
        }
    };

    private MainThreadUtils() {
        //
    }

    // References to callbacks that must live longer than the method invocation because they are called asynchronously
    private static final Set<RococoaLibrary.VoidCallback> asynchronousCallbacks = new HashSet<RococoaLibrary.VoidCallback>();

    /**
     * Return the result of calling callable on the main Cococoa thread.
     */
    @SuppressWarnings("unchecked")
    public static <T> T callOnMainThread(RococoaLibrary rococoaLibrary, final Callable<T> callable) {
        final Object[] result = new Object[1];
        final Throwable[] thrown = new Throwable[1];
        RococoaLibrary.VoidCallback callback = new RococoaLibrary.VoidCallback() {
            public void callback() {
                try {
                    result[0] = callable.call();
                } catch (Throwable t) {
                    thrown[0] = t;
                }
            }
        };

        rococoaLibrary.callOnMainThread(callback, true);
        rethrow(thrown[0]);
        return (T) result[0];
    }

    /**
     * @param runnable      Run runnable on the main Cocoa thread.
     * @param waitUntilDone A Boolean that specifies whether the current thread blocks until after
     *                      the specified selector is performed on the receiver on the main thread.
     */
    public static void runOnMainThread(RococoaLibrary rococoaLibrary, final Runnable runnable, final boolean waitUntilDone) {
        final Throwable[] thrown = new Throwable[1];
        RococoaLibrary.VoidCallback callback = new RococoaLibrary.VoidCallback() {
            public void callback() {
                try {
                    runnable.run();
                } catch (Throwable t) {
                    if (waitUntilDone) {
                        thrown[0] = t;
                    } else {
                        logging.log(Level.SEVERE, "Lost exception on main thread", t);
                    }
                } finally {
                    if (!waitUntilDone) {
                        asynchronousCallbacks.remove(this);
                    }
                }
            }
        };

        if (!waitUntilDone) {
            asynchronousCallbacks.add(callback);
        }
        rococoaLibrary.callOnMainThread(callback, waitUntilDone);
        rethrow(thrown[0]);
    }

    public static boolean isMainThread() {
        return isMainThreadThreadLocal.get();
    }

    private static boolean nsThreadSaysIsMainThread() {
        return Foundation.send(idNSThreadClass, isMainThreadSelector, boolean.class);
    }

    private static void rethrow(Throwable t) {
        if (t == null) {
            return;
        }
        if (t instanceof Error) {
            throw (Error) t;
        }
        if (t instanceof RuntimeException) {
            throw (RuntimeException) t;
        }
        throw new RococoaException(t);
    }
}
