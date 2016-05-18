/*******************************************************************************
 * Copyright (C) 2016 University of Alabama in Huntsville (UAH)
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * The US Government has unlimited rights in this work in accordance with W31P4Q-10-D-0092 DO 0105.
 *******************************************************************************/
package org.osate.ge.graphics;

import org.osate.ge.internal.graphics.Rectangle;
import org.osate.ge.internal.graphics.RoundedRectangle;

public class RectangleBuilder {
	private boolean rounded = false;
	
	private RectangleBuilder() {}
	
	public static RectangleBuilder create() {
		return new RectangleBuilder();
	}
	
	public RectangleBuilder rounded() {
		this.rounded = true;
		return this;
	}
	
	public Graphic build() {
		return rounded ? new RoundedRectangle() : new Rectangle();
	}
}