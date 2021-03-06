package org.osate.ge.internal.businessObjectHandlers;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.osate.aadl2.AbstractFeature;
import org.osate.aadl2.Access;
import org.osate.aadl2.AccessSpecification;
import org.osate.aadl2.AccessType;
import org.osate.aadl2.ArrayableElement;
import org.osate.aadl2.Classifier;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.ComponentType;
import org.osate.aadl2.DirectedFeature;
import org.osate.aadl2.DirectionType;
import org.osate.aadl2.Element;
import org.osate.aadl2.EventDataSource;
import org.osate.aadl2.EventSource;
import org.osate.aadl2.Feature;
import org.osate.aadl2.FeatureGroup;
import org.osate.aadl2.FeatureGroupType;
import org.osate.aadl2.FeaturePrototypeActual;
import org.osate.aadl2.FeaturePrototypeBinding;
import org.osate.aadl2.InternalFeature;
import org.osate.aadl2.NamedElement;
import org.osate.aadl2.PortSpecification;
import org.osate.aadl2.ProcessorFeature;
import org.osate.aadl2.PrototypeBinding;
import org.osate.aadl2.SubprogramProxy;
import org.osate.aadl2.modelsupport.util.ResolvePrototypeUtil;
import org.osate.ge.BusinessObjectContext;
import org.osate.ge.Categories;
import org.osate.ge.GraphicalConfiguration;
import org.osate.ge.GraphicalConfigurationBuilder;
import org.osate.ge.PaletteEntry;
import org.osate.ge.PaletteEntryBuilder;
import org.osate.ge.di.CanCreate;
import org.osate.ge.di.CanDelete;
import org.osate.ge.di.Create;
import org.osate.ge.di.GetGraphicalConfiguration;
import org.osate.ge.di.GetName;
import org.osate.ge.di.GetPaletteEntries;
import org.osate.ge.di.IsApplicable;
import org.osate.ge.di.Names;
import org.osate.ge.di.ValidateName;
import org.osate.ge.graphics.Graphic;
import org.osate.ge.internal.DockingPosition;
import org.osate.ge.internal.di.CanRename;
import org.osate.ge.internal.graphics.AadlGraphics;
import org.osate.ge.internal.labels.LabelConfiguration;
import org.osate.ge.internal.labels.LabelConfigurationBuilder;
import org.osate.ge.internal.services.NamingService;
import org.osate.ge.internal.util.AadlArrayUtil;
import org.osate.ge.internal.util.AadlFeatureUtil;
import org.osate.ge.internal.util.AadlPrototypeUtil;
import org.osate.ge.internal.util.ImageHelper;
import org.osate.ge.internal.util.AadlInheritanceUtil;
import org.osate.ge.internal.util.StringUtil;
import org.osate.ge.query.StandaloneQuery;
import org.osate.ge.services.QueryService;

public class FeatureHandler {
	private static final StandaloneQuery parentQuery = StandaloneQuery.create((root) -> root.ancestors().first());
	private static final LabelConfiguration labelConfiguration = LabelConfigurationBuilder.create().aboveTop().left().build();
	
	@IsApplicable
	public boolean isApplicable(final @Named(Names.BUSINESS_OBJECT) Object bo) {
		return bo instanceof Feature || bo instanceof InternalFeature || bo instanceof ProcessorFeature;
	}
	
	@CanDelete
	public boolean canEdit(final @Named(Names.BUSINESS_OBJECT) NamedElement feature, final @Named(Names.BUSINESS_OBJECT_CONTEXT) BusinessObjectContext boc, final QueryService queryService) {
		final Object containerBo = queryService.getFirstBusinessObject(parentQuery, boc);
		return feature.getContainingClassifier() == containerBo;
	}

	@CanRename
	public boolean canRename(final @Named(Names.BUSINESS_OBJECT) NamedElement feature, final @Named(Names.BUSINESS_OBJECT_CONTEXT) BusinessObjectContext boc, final QueryService queryService) {
		return canEdit(feature, boc, queryService) && (!(feature instanceof Feature) || ((Feature)feature).getRefined() == null);
	}
	
	@GetPaletteEntries
	public PaletteEntry[] getPaletteEntries(final @Named(Names.DIAGRAM_BO) Object diagramBo) {		
		final List<PaletteEntry> entries = new ArrayList<PaletteEntry>();		
		final Classifier classifier = diagramBo instanceof Classifier ? (Classifier)diagramBo : null;

		for(EClass featureType : AadlFeatureUtil.getFeatureTypes()) {
			if(classifier == null || AadlFeatureUtil.canOwnFeatureType(classifier, featureType)) {
				entries.add(createPaletteEntry(featureType));
			}
		}
		
		return entries.toArray(new PaletteEntry[entries.size()]);
	}
	
	private static PaletteEntry createPaletteEntry(final EClass featureType) {
		return PaletteEntryBuilder.create().label(StringUtil.camelCaseToUser(featureType.getName())).icon(ImageHelper.getImage(featureType.getName())).category(Categories.FEATURES).context(featureType).build();
	}
	
	@CanCreate
	public boolean canCreate(final @Named(Names.TARGET_BO) EObject targetBo, final @Named(Names.PALETTE_ENTRY_CONTEXT) EClass featureType) {		
		// The container must be a Feature Group Type or a ComponentType and it must have a method to create the feature type that is controlled by this pattern
		return (targetBo instanceof FeatureGroupType || 
					targetBo instanceof ComponentType || 
					targetBo instanceof ComponentImplementation) && 
				AadlFeatureUtil.canOwnFeatureType((Classifier)targetBo, featureType);
	}
	
	@Create
	public NamedElement createBusinessObject(@Named(Names.OWNER_BO) final Classifier classifier, final @Named(Names.PALETTE_ENTRY_CONTEXT) EClass featureType, final @Named(Names.DOCKING_POSITION) DockingPosition dockingPosition, final NamingService namingService) {
		final String newFeatureName = namingService.buildUniqueIdentifier(classifier, "new_feature");
		
		final NamedElement newFeature = AadlFeatureUtil.createFeature(classifier, featureType);
		newFeature.setName(newFeatureName);
		
		// Set in or out based on target docking position
		final boolean isRight = dockingPosition == DockingPosition.RIGHT;
		if(newFeature instanceof DirectedFeature) {
			if(!(newFeature instanceof FeatureGroup)) {
				final DirectedFeature newDirectedFeature = (DirectedFeature)newFeature;						
				newDirectedFeature.setIn(!isRight);
				newDirectedFeature.setOut(isRight);
			}
		} else if(newFeature instanceof Access) {
			final Access access = (Access)newFeature;						
			access.setKind(isRight ? AccessType.PROVIDES : AccessType.REQUIRES);
		}
		
		if(classifier instanceof ComponentType) {
			((ComponentType) classifier).setNoFeatures(false);
		}
		
		return newFeature;
	}
	
	@GetGraphicalConfiguration
	public GraphicalConfiguration getGraphicalConfiguration(final @Named(Names.BUSINESS_OBJECT) NamedElement feature, 
			final @Named(Names.BUSINESS_OBJECT_CONTEXT) BusinessObjectContext featureBoc) {
		return GraphicalConfigurationBuilder.create().
				graphic(getGraphicalRepresentation(feature, featureBoc)).
				defaultDockingPosition(getDefaultDockingPosition(feature, featureBoc)).
				defaultLabelConfiguration(labelConfiguration).
				defaultForeground(AadlInheritanceUtil.isInherited(featureBoc) ? Colors.INHERITED_ELEMENT_COLOR : null).
				build();
	}
	
	private Graphic getGraphicalRepresentation(NamedElement feature, BusinessObjectContext featureBoc) {
		// Check to see if it is a prototype feature
		if(feature instanceof AbstractFeature) {
			final AbstractFeature af = (AbstractFeature)feature;
			if(af.getFeaturePrototype() != null) {
				// Lookup the binding
				// Get the proper context (FeatureGroupType or ComponentClassifier) - May be indirectly for example from Subcomponent...
				final Element bindingContext = AadlPrototypeUtil.getPrototypeBindingContext(featureBoc);
				if(bindingContext != null) {
					final PrototypeBinding binding = ResolvePrototypeUtil.resolveFeaturePrototype(af.getFeaturePrototype(), bindingContext);
					if(binding instanceof FeaturePrototypeBinding) {
						FeaturePrototypeActual actual = ((FeaturePrototypeBinding) binding).getActual();
						if(actual instanceof PortSpecification) {
							final DirectionType direction = getDirection(actual, featureBoc);
							return AadlGraphics.getFeatureGraphic(((PortSpecification)actual).getCategory(), direction);
						} else if(actual instanceof AccessSpecification) {
							final DirectionType direction = getDirection(actual, featureBoc);
							return AadlGraphics.getFeatureGraphic(((AccessSpecification)actual).getCategory(), direction);
						}
					}
				}
			}
		}
		
		final DirectionType direction = getDirection(feature, featureBoc);
		return AadlGraphics.getFeatureGraphic(feature.eClass(), direction); 
	}	
	
	private DockingPosition getDefaultDockingPosition(NamedElement feature, BusinessObjectContext featureBoc) {
		return getDirection(feature, featureBoc) == DirectionType.OUT ? DockingPosition.RIGHT : DockingPosition.LEFT;
	}
	
	/**
	 * 
	 * @param feature a feature or feature specification
	 * @return
	 */
	private DirectionType getDirection(final Element feature, final BusinessObjectContext featureBoc) {
		DirectionType direction;
		if(feature instanceof DirectedFeature) {
			direction = ((DirectedFeature) feature).getDirection();
		} else if(feature instanceof PortSpecification) {
			direction = ((PortSpecification) feature).getDirection();
		} else if(feature instanceof Access) {
			direction = ((Access)feature).getKind() == AccessType.PROVIDES ? DirectionType.OUT : DirectionType.IN;
		} else if(feature instanceof AccessSpecification) {
			direction = ((AccessSpecification)feature).getKind() == AccessType.PROVIDES ? DirectionType.OUT : DirectionType.IN;
		} else if(feature instanceof EventSource || feature instanceof EventDataSource || feature instanceof SubprogramProxy) {
			direction = DirectionType.IN;
		} else {
			direction = DirectionType.IN_OUT;
		}
		
		// Invert the feature as appropriate
		if(AadlFeatureUtil.isFeatureInverted(featureBoc)) {
			if(direction == DirectionType.IN) {
				direction = DirectionType.OUT;
			} else if(direction == DirectionType.OUT) {
				direction = DirectionType.IN;
			}
		}		
		
		return direction;
	}
	
	@GetName
	public String getName(final @Named(Names.BUSINESS_OBJECT) NamedElement feature) {
		String name = feature.getName() == null ? "" : feature.getName();
		
		if(feature instanceof ArrayableElement) {
			name += AadlArrayUtil.getDimensionUserString((ArrayableElement) feature);
		}
		
		return name;
	}	
	
	@ValidateName
	public String validateName(final @Named(Names.BUSINESS_OBJECT) NamedElement feature, final @Named(Names.NAME) String value, final NamingService namingService) {
		return namingService.checkNameValidity(feature, value);
	}
}
