/*******************************************************************************
 * Copyright (C) 2016 University of Alabama in Huntsville (UAH)
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * The US Government has unlimited rights in this work in accordance with W31P4Q-10-D-0092 DO 0105
 *******************************************************************************/
package org.osate.ge.ext.services;

public interface DiagramService {
	/**
	 * Opens the diagram which is linked to the specified business object. If a diagram is not found, it is created.
	 * @param bo
	 */
	void openOrCreateDiagramBusinessObject(Object bo);
}