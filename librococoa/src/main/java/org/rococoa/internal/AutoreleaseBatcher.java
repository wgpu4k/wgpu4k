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

import org.rococoa.cocoa.foundation.NSAutoreleasePool;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Used by NSObjectInvocationHandler to make sure that there is an NSAutoreleasePool
 * available when NSObject's are finalized, but not pay the price of creating one
 * per call.
 * <p>
 * Take care, this is tested but unproven code (2009/08).
 *
 * @author duncan
 */
public class AutoreleaseBatcher extends OperationBatcher {

    private static final Logger logging = Logger.getLogger("org.rococoa");

    private static final ThreadLocal<AutoreleaseBatcher> threadLocal = new ThreadLocal<AutoreleaseBatcher>();

    private NSAutoreleasePool pool;

    public static AutoreleaseBatcher forThread(int batchSize) {
        if (threadLocal.get() == null) {
            threadLocal.set(new AutoreleaseBatcher(batchSize));
        }
        return threadLocal.get();
    }

    public AutoreleaseBatcher(int batchSize) {
        super(batchSize);
    }

    @Override
    protected void operation() {
        if (logging.isLoggable(Level.FINE)) {
            logging.fine("Draining autorelease pool");
        }
        pool.drain();
    }

    @Override
    protected void reset() {
        pool = NSAutoreleasePool.new_();
    }

}
