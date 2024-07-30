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

import org.rococoa.cocoa.CGFloat;

import java.awt.geom.Dimension2D;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;

public class NSSize extends Structure implements Structure.ByValue {
    public CGFloat width;
    public CGFloat height;

    public NSSize() {
        this(0, 0);
    }

    public NSSize(double width, double height) {
        this.width = new CGFloat(width);
        this.height = new CGFloat(height);
    }

    public NSSize(Dimension2D pSize) {
        this(pSize.getWidth(), pSize.getHeight());
    }

    @Override
    protected List getFieldOrder() {
        return Arrays.asList("width", "height");
    }
}
