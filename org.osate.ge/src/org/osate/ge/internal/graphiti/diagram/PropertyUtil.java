package org.osate.ge.internal.graphiti.diagram;

import org.eclipse.graphiti.mm.PropertyContainer;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.osate.ge.internal.DockArea;

public class PropertyUtil {
	private static final String NAME_KEY = "name";
	private static final String IS_MANUALLY_POSITIONED_KEY = "is_manually_positioned"; // Whether the shape should be ignored by the automatic layout algorithm
	private static final String IS_TRANSIENT_KEY = "is_transient";
	private static final String SIDE_KEY = "side"; // Which side the shape is on
	private static final String IS_LAYED_OUT_KEY = "is_layed_out"; // Whether the shape has been layed out by the automatic layout algorithm
	private static final String IS_COLORING_CONTAINER_KEY = "is_coloring_container"; // True if the GA's children should be checked during highlighting
	private static final String IS_COLORING_CHILD_KEY = "is_coloring_child"; // True if the GA's color should be adjusted during highlighting
	
	public static String getName(final PropertyContainer pc) {
		return Graphiti.getPeService().getPropertyValue(pc, NAME_KEY);
	}

	public static void setName(final PropertyContainer pc, final String value) {
		Graphiti.getPeService().setPropertyValue(pc, NAME_KEY, value);
	}
	
	/**
	 * Being manually positioned indicates the the pictogram is manually positioned by the editor. Manually positions shapes should not
	 * be moved by automatic diagram layout or adjustment algorithms An example of such a pictogram is a label shape.
	 * @param pe
	 * @return
	 */
	public static boolean isManuallyPositioned(final PictogramElement pe) {
		return "true".equals(Graphiti.getPeService().getPropertyValue(pe, IS_MANUALLY_POSITIONED_KEY));
	}
	
	public static void setIsManuallyPositioned(final PictogramElement pe, final boolean value) {
		Graphiti.getPeService().setPropertyValue(pe, IS_MANUALLY_POSITIONED_KEY, value ? "true" : "false");
	}
	
	public static boolean isTransient(final PictogramElement pe) {
		return "true".equals(Graphiti.getPeService().getPropertyValue(pe, IS_TRANSIENT_KEY));
	}
	
	public static void setIsTransient(final PictogramElement pe, final boolean value) {
		Graphiti.getPeService().setPropertyValue(pe, IS_TRANSIENT_KEY, value ? "true" : "false");
	}
	
	public static DockArea getDockArea(final PictogramElement pe) {
		final String dockAreaId = Graphiti.getPeService().getPropertyValue(pe, SIDE_KEY);
		return dockAreaId == null ? null : DockArea.getById(dockAreaId);
	}
	
	public static void setDockArea(final PictogramElement pe, final DockArea value) {
		if(value == null) {
			Graphiti.getPeService().removeProperty(pe, SIDE_KEY);
		} else {
			Graphiti.getPeService().setPropertyValue(pe, SIDE_KEY, value.id);
		}
	}	
	
	public static boolean isLayedOut(PictogramElement pe) {
		return "true".equals(Graphiti.getPeService().getPropertyValue(pe, IS_LAYED_OUT_KEY));
	}

	public static void setIsLayedOut(PictogramElement pe, boolean value) {
		Graphiti.getPeService().setPropertyValue(pe, IS_LAYED_OUT_KEY, value ? "true" : "false");
	}
	

	// Coloring	
	public static boolean isColoringContainer(final GraphicsAlgorithm ga) {
		return "true".equals(Graphiti.getPeService().getPropertyValue(ga, IS_COLORING_CONTAINER_KEY));
	}
	
	public static void setIsColoringContainer(final GraphicsAlgorithm ga, boolean value) {
		Graphiti.getPeService().setPropertyValue(ga, IS_COLORING_CONTAINER_KEY, value ? "true" : "false");
	}
	
	public static boolean isColoringChild(final GraphicsAlgorithm ga) {
		return "true".equals(Graphiti.getPeService().getPropertyValue(ga, IS_COLORING_CHILD_KEY));		
	}
	
	public static void setIsColoringChild(final GraphicsAlgorithm ga, boolean value) {
		Graphiti.getPeService().setPropertyValue(ga, IS_COLORING_CHILD_KEY, value ? "true" : "false");
	}
}
