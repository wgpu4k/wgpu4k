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

import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;

/**
 * @author <a href="mailto:harald.kuhr@gmail.com">Harald Kuhr</a>
 */
public class NSRect extends Structure implements Structure.ByValue {
    public NSPoint origin;
    public NSSize size;

    public NSRect() {
        this(new NSPoint(0, 0), new NSSize());
    }

    public NSRect(NSPoint origin, NSSize size) {
        this.origin = origin;
        this.size = size;
    }

    public NSRect(Point2D origin, Dimension2D size) {
        this.origin = new NSPoint(origin);
        this.size = new NSSize(size);
    }

    public NSRect(Rectangle2D rect) {
        this.origin = new NSPoint(rect.getX(), rect.getY());
        this.size = new NSSize(rect.getWidth(), rect.getHeight());
    }

    public NSRect(double w, double h) {
        this.origin = new NSPoint(0, 0);
        this.size = new NSSize(w, h);
    }

    public Rectangle2D getBounds() {
        return new Rectangle2D.Double(origin.x.doubleValue(), origin.y.doubleValue(), size.width.doubleValue(), size.height.doubleValue());
    }

    @Override
    protected List getFieldOrder() {
        return Arrays.asList("origin", "size");
    }
}