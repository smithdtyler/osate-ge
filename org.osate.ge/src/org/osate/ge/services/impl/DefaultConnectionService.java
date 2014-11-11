/*******************************************************************************
 * Copyright (C) 2013 University of Alabama in Huntsville (UAH)
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * The US Government has unlimited rights in this work in accordance with W31P4Q-10-D-0092 DO 0073.
 *******************************************************************************/
package org.osate.ge.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.osate.ge.diagrams.common.connections.AadlConnectionInfoProvider;
import org.osate.ge.diagrams.common.connections.ConnectionInfoProvider;
import org.osate.ge.diagrams.common.connections.FlowSpecificationInfoProvider;
import org.osate.ge.diagrams.common.connections.GeneralizationInfoProvider;
import org.osate.ge.diagrams.common.connections.InitialModeInfoProvider;
import org.osate.ge.diagrams.common.connections.ModeTransitionInfoProvider;
import org.osate.ge.services.AnchorService;
import org.osate.ge.services.BusinessObjectResolutionService;
import org.osate.ge.services.ConnectionService;
import org.osate.ge.services.PropertyService;
import org.osate.ge.services.ShapeService;

//TODO:Split class so that one will be the utility class and one will be the delegate to the extensions
public class DefaultConnectionService implements ConnectionService {
	private final ConnectionInfoProvider[] infoProviders;
	private final BusinessObjectResolutionService bor;
	private final IFeatureProvider fp;
	
	public DefaultConnectionService(final AnchorService anchorUtil, final ShapeService shapeHelper, final PropertyService propertyService, final BusinessObjectResolutionService bor, final IFeatureProvider fp) {
		this.bor = bor;
		this.fp = fp;
		
		// TODO: Use extension point or something
		final Diagram diagram = getDiagram();
		infoProviders = new ConnectionInfoProvider[] {
				new AadlConnectionInfoProvider(bor, diagram, anchorUtil, shapeHelper),
				new FlowSpecificationInfoProvider(bor, diagram, anchorUtil, shapeHelper),
				new GeneralizationInfoProvider(bor, diagram, shapeHelper),
				new ModeTransitionInfoProvider(bor, diagram, anchorUtil, shapeHelper),
				new InitialModeInfoProvider(bor, diagram, propertyService)
		};
	}
	
	/* (non-Javadoc)
	 * @see org.osate.ge.diagrams.common.util.ConnectionService#getConnection(org.eclipse.graphiti.mm.pictograms.ContainerShape, java.lang.Object)
	 */
	@Override
	public Connection getConnection(final ContainerShape ownerShape, final Object bo) {
		// Find all connections that match the anchors
		final Diagram diagram = getDiagram();
		for(final Connection c : diagram.getConnections()) {
			if(getOwnerShape(c) == ownerShape && bor.getBusinessObjectForPictogramElement(c) == bo) {
				return c;
			}
		}
		
		return null;
	}

	/* (non-Javadoc)
	 * @see org.osate.ge.diagrams.common.util.ConnectionService#getAnchors(org.eclipse.graphiti.mm.pictograms.ContainerShape, java.lang.Object)
	 */
	@Override
	public Anchor[] getAnchors(final ContainerShape ownerShape, final Object bo) {
		final ConnectionInfoProvider p = getInfoProviderByBusinessObject(bo);
		if(p != null) {
			final Anchor[] anchors = p.getAnchors(ownerShape, bo);
			if(anchors != null && isVisible(anchors[0]) && isVisible(anchors[1])) {
				return anchors;
			}
		}

		return null;
	}	
	
	private boolean isVisible(final Anchor anchor) {
		boolean result = true;
		if(result && anchor.getParent() instanceof Shape) {
			Shape shape = (Shape)anchor.getParent();
			do {
				result = shape.isVisible();
				shape = shape.getContainer();
			} while(shape != null && result);
		}
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see org.osate.ge.diagrams.common.util.ConnectionService#getOwnerShape(org.eclipse.graphiti.mm.pictograms.Connection)
	 */
	@Override
	public ContainerShape getOwnerShape(final Connection connection) {
		final ConnectionInfoProvider p = getInfoProviderByConnection(connection);
		if(p != null) {
			return p.getOwnerShape(connection);
		}
		
		return null;
	}
	
	@Override
	public Iterable<Connection> getConnectionsByOwner(final Shape owner) {
		final List<Connection> results = new ArrayList<Connection>();
		
		// Populate the list with connections that are owned by the specified shape
		for(final Connection c : getDiagram().getConnections()) {
			if(getOwnerShape(c) == owner) {
				results.add(c);
			}
		}

		return results;		
	}
	
	private ConnectionInfoProvider getInfoProviderByBusinessObject(final Object bo) {
		for(final ConnectionInfoProvider p : infoProviders) {
			if(p.isBusinessObjectApplicable(bo)) {
				return p;
			}
		}
		
		return null;
	}
	
	private ConnectionInfoProvider getInfoProviderByConnection(final Connection c) {
		for(final ConnectionInfoProvider p : infoProviders) {
			if(p.isApplicable(c)) {
				return p;
			}
		}
		
		return null;
	}
	
	private Diagram getDiagram() {
		return fp.getDiagramTypeProvider().getDiagram();
	}
}
