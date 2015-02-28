package org.osate.ge.dialogs;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IElementComparer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.IShellProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.ui.PlatformUI;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.osate.aadl2.Aadl2Factory;
import org.osate.aadl2.Aadl2Package;
import org.osate.aadl2.AadlInteger;
import org.osate.aadl2.ArrayDimension;
import org.osate.aadl2.ArraySize;
import org.osate.aadl2.NamedElement;
import org.osate.aadl2.Property;
import org.osate.aadl2.PropertyConstant;
import org.osate.xtext.aadl2.properties.util.EMFIndexRetrieval;

public class EditDimensionsDialog extends TitleAreaDialog {
	private boolean allowMultipleDimensions;
	private List<ArrayDimension> dimensions = new ArrayList<ArrayDimension>();
	private List<ArrayDimension> modifiedDimensions; // Stores the modified dimensions after the user selected Ok
	private ListViewer dimensionListViewer;
	private Button addBtn;
    private Button modifyBtn;
    private Button deleteBtn;
    private Button upBtn;
    private Button downBtn;
    
	public EditDimensionsDialog(final Shell parentShell, List<ArrayDimension> initialDimensions, boolean allowMultipleDimensions) {
		super(parentShell);
		
		this.allowMultipleDimensions = allowMultipleDimensions;
		
		// Make a copy of the initial dimensions
		dimensions.addAll(EcoreUtil.copyAll(initialDimensions));
	}
	
	@Override
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText("Modify Dimensions");
	}
	
	@Override
	public void create() {
		super.create();
		setTitle("Array Dimensions");
	}
	
	@Override
  	protected Control createDialogArea(final Composite parent) {
	    final Composite area = (Composite)super.createDialogArea(parent);
	    
	    // Create basic panel
	    final Composite container = new Composite(area, SWT.NONE);
	    container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false)); 
	    container.setLayout(new GridLayout(2, false));
	    
	    // Left Pane
	    final Composite leftPane = new Composite(container, SWT.NONE);
	    leftPane.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
	    leftPane.setLayout(new RowLayout(SWT.VERTICAL));
	    final org.eclipse.swt.widgets.List dimensionList = new org.eclipse.swt.widgets.List(leftPane, SWT.SINGLE | SWT.BORDER | SWT.V_SCROLL);
	    dimensionList.setLayoutData(new RowData(300, 300));
	    
	    dimensionListViewer = new ListViewer(dimensionList);
	    dimensionListViewer.setContentProvider(new ArrayContentProvider());
	    dimensionListViewer.setLabelProvider(new LabelProvider() {
	    	@Override
	    	public String getText(final Object element) {
	    		final ArrayDimension dim = (ArrayDimension)element;
	    		final ArraySize dimSize = dim.getSize();
	    		
	    		final String txt;
	    		if(dimSize == null) {
	    			txt = "[]";
	    		} else if(dimSize.getSizeProperty() != null) {
	    			if(dimSize.getSizeProperty() instanceof Property) {
	    				txt = "[" + ((Property)dimSize.getSizeProperty()).getQualifiedName() + "]";
	    			} else if(dimSize.getSizeProperty() instanceof PropertyConstant) {
	    				txt = "[" + ((PropertyConstant)dimSize.getSizeProperty()).getQualifiedName() + "]";
	    			} else {
	    				txt = "[<Unsupported case>]";
	    			}
	    		} else {
	    			txt = "[" + dimSize.getSize() + "]";
	    		}

	    		return txt;
	    	}
	    });
	    
	    dimensionListViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(final SelectionChangedEvent event) {
				refreshWidgetEnabledStates();
			}	    	
	    });
	    
	    dimensionListViewer.addDoubleClickListener(new IDoubleClickListener() {
			@Override
			public void doubleClick(final DoubleClickEvent event) {
				showModifySelectedDimensionDialog();	
			} 
	    });
	    dimensionListViewer.setInput(dimensions);

	    addBtn = new Button(leftPane, SWT.PUSH);
	    addBtn.setText("Add");
	    addBtn.setToolTipText("Add a new array dimension.");
	    addBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				final Aadl2Package pkg = Aadl2Factory.eINSTANCE.getAadl2Package();
				final ArrayDimension newDim = (ArrayDimension)pkg.getEFactoryInstance().create(pkg.getArrayDimension());
				dimensions.add(newDim);
				dimensionListViewer.refresh();
				dimensionListViewer.setSelection(new StructuredSelection(newDim));
			}
	    });
	    
	    // Right Pane
	    final Composite rightPane = new Composite(container, SWT.NONE);
	    rightPane.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
	    final RowLayout rightPaneLayout = new RowLayout(SWT.VERTICAL);
	    rightPaneLayout.fill = true;
	    rightPane.setLayout(rightPaneLayout);
	    
	    modifyBtn = new Button(rightPane, SWT.PUSH);
	    modifyBtn.setText("Modify...");
	    modifyBtn.setToolTipText("Modify the array dimension.");
	    modifyBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				showModifySelectedDimensionDialog();
			}
	    });
	    
	    deleteBtn = new Button(rightPane, SWT.PUSH);
	    deleteBtn.setText("Delete");
	    deleteBtn.setToolTipText("Delete the array dimension.");
	    deleteBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
			    if(MessageDialog.openQuestion(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Confirm Delete", "Are you sure you want to delete this dimension?")) {
					final ArrayDimension dim = getSelectedDimension();
					dimensions.remove(dim);
					dimensionListViewer.refresh();
					refreshWidgetEnabledStates();
			    }
			}
	    });
	    
	    upBtn = new Button(rightPane, SWT.PUSH);
	    upBtn.setText("Up");
	    upBtn.setToolTipText("Move the array dimension up.");
	    upBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				final ArrayDimension dim = getSelectedDimension();
				final int index = dimensions.indexOf(dim);
				if(index > 0) {
					dimensions.remove(index);
					dimensions.add(index-1, dim);
				}
				
				dimensionListViewer.refresh();
				refreshWidgetEnabledStates();
			}
	    });
	    
	    downBtn = new Button(rightPane, SWT.PUSH);
	    downBtn.setText("Down");
	    downBtn.setToolTipText("Move the array dimension down.");
	    downBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				final ArrayDimension dim = getSelectedDimension();
				final int index = dimensions.indexOf(dim);
				if(index != -1) {
					dimensions.remove(index);
					dimensions.add(index+1, dim);
				}
				
				dimensionListViewer.refresh();
				refreshWidgetEnabledStates();
			}
	    });
	    	    
	    refreshWidgetEnabledStates();
	    
	    return area;
	}
	
	private void showModifySelectedDimensionDialog() {
		final ArrayDimension dim = getSelectedDimension();
		
		if(dim != null) {
			// Show the editor dimension dialog. If the user selects OK, it will modify the passed in object.
			final EditDimensionDialog dlg = new EditDimensionDialog(EditDimensionsDialog.this, dim);
			if(dlg.open() == Dialog.CANCEL) {
				return;
			}
			
			dimensionListViewer.refresh();
			refreshWidgetEnabledStates();
		}
	}
	
	private ArrayDimension getSelectedDimension() {
		final StructuredSelection selection = (StructuredSelection)dimensionListViewer.getSelection();
		final ArrayDimension dim = (ArrayDimension)selection.getFirstElement();
		return dim;
	}
	
	private void refreshWidgetEnabledStates() {
		final ArrayDimension dim = getSelectedDimension();
		final int dimIndex = dimensions.indexOf(dim);
		
		addBtn.setEnabled(dimensions.size() == 0 || allowMultipleDimensions);
		modifyBtn.setEnabled(dim != null);
		deleteBtn.setEnabled(dim != null);
		upBtn.setEnabled(dim != null && dimIndex >= 1);
		downBtn.setEnabled(dim != null && dimIndex < (dimensions.size()-1));
	}
	
	@Override
	public void okPressed() {
		// Make a copy of the selected dimensions
		modifiedDimensions = new ArrayList<ArrayDimension>();
		modifiedDimensions.addAll(EcoreUtil.copyAll(dimensions));
		
		super.okPressed();
	}
	
	public List<ArrayDimension> getDimensions() {
		return modifiedDimensions;
	}
	
	// Dialog for modifying a single dimension. Modifies the object passed to it.
	private static class EditDimensionDialog extends Dialog {
		private static String unspecifiedTxt = "Unspecified";
		private static String numberTxt = "Number";
	    private static String propertyTxt = "Property";
	    private static String propertyConstantTxt = "Property Constant";
	    private static String unsupportedTypeTxt = "Unsupported Type";
		private final ArrayDimension dimension;
		private Composite detailsPane;
		private RowData visibleRowData = new RowData();
		private RowData hiddenRowData = new RowData();
	    private Combo typeCmb;
	    private Composite numberPane;
	    private Spinner numberValue;
	    private Composite propertyPane;
	    private ComboViewer propertyCmb;
	    private Composite propertyConstantPane;
	    private ComboViewer propertyConstantCmb;	    
	    
		public EditDimensionDialog(final IShellProvider parentShell, final ArrayDimension dimension) {
			super(parentShell);
			this.dimension = dimension;
			visibleRowData.exclude = false;
			hiddenRowData.exclude = true;
		}
		
		@Override
		protected void configureShell(Shell shell) {
			super.configureShell(shell);
			shell.setText("Modify Dimension");
		}
		
		@Override
	  	protected Control createDialogArea(final Composite parent) {
			final Composite area = (Composite)super.createDialogArea(parent);
		    		    
		    // Create basic panel
		    final Composite container = new Composite(area, SWT.NONE);
		    container.setLayout(new RowLayout(SWT.VERTICAL));
		    
		    // Type Combo Box
		    typeCmb = new Combo(container, SWT.DROP_DOWN | SWT.READ_ONLY);
		    typeCmb.add(unspecifiedTxt);
		    typeCmb.add(numberTxt);
		    typeCmb.add(propertyTxt);
		    typeCmb.add(propertyConstantTxt);		    
		    typeCmb.setText(getTypeText(dimension));
		    typeCmb.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(final SelectionEvent e) {
					updateWidgetVisibility();
				}
		    });

		    detailsPane = new Composite(container, SWT.NONE);
		    detailsPane.setLayout(new StackLayout());
		    
		    // Number Pane
		    numberPane = new Composite(detailsPane, SWT.NONE);
			numberPane.setLayout(new RowLayout(SWT.HORIZONTAL));
			final Label numberLbl = new Label(numberPane, SWT.NONE);
			numberLbl.setText("Size:");
			numberValue = new Spinner(numberPane, SWT.NONE);			
			
			final IElementComparer namedElementComparer = new IElementComparer() {
				@Override
				public boolean equals(final Object a, final Object b) {
					if(a instanceof NamedElement && b instanceof NamedElement && ((NamedElement) a).getQualifiedName() != null) {
						return ((NamedElement)a).getQualifiedName().equalsIgnoreCase(((NamedElement)b).getQualifiedName());
					}
					
					return a.equals(b);
				}

				@Override
				public int hashCode(Object element) {
					return element.hashCode();
				}				
			};
			
			// Property Pane
		    propertyPane = new Composite(detailsPane, SWT.NONE);
			propertyPane.setLayout(new RowLayout(SWT.HORIZONTAL));
			propertyCmb = new ComboViewer(propertyPane, SWT.DROP_DOWN | SWT.READ_ONLY);
			propertyCmb.setContentProvider(new ArrayContentProvider());
			propertyCmb.setLabelProvider(new ElementLabelProvider());
			propertyCmb.setSorter(new LabelViewerSorter());
			propertyCmb.setInput(getValidProperties());
			propertyCmb.setComparer(namedElementComparer);
			
			// Property Constant Pane
		    propertyConstantPane = new Composite(detailsPane, SWT.NONE);
			propertyConstantPane.setLayout(new RowLayout(SWT.HORIZONTAL));
			propertyConstantCmb = new ComboViewer(propertyConstantPane, SWT.DROP_DOWN | SWT.READ_ONLY);
			propertyConstantCmb.setContentProvider(new ArrayContentProvider());
			propertyConstantCmb.setLabelProvider(new ElementLabelProvider());
			propertyConstantCmb.setSorter(new LabelViewerSorter());
			propertyConstantCmb.setInput(getValidPropertyConstants());
			propertyConstantCmb.setComparer(namedElementComparer);
			
			// Set initial values
			final ArraySize dimSize = dimension.getSize();
			if(dimSize != null) {
				if(dimSize.getSizeProperty() == null) {
					numberValue.setSelection((int)dimSize.getSize());
				} else if(dimSize.getSizeProperty() instanceof Property) {
					propertyCmb.setSelection(new StructuredSelection(dimSize.getSizeProperty()));
				} else if(dimSize.getSizeProperty() instanceof PropertyConstant) {
					propertyConstantCmb.setSelection(new StructuredSelection(dimSize.getSizeProperty()));
				}
			}

			updateWidgetVisibility();

		    return area;
		}
		
		private List<Property> getValidProperties() {
			final List<Property> results = new ArrayList<Property>();
			
			final XtextResourceSet rs = new XtextResourceSet();
			for(final IEObjectDescription objDesc : EMFIndexRetrieval.getAllClassifiersOfTypeInWorkspace(Aadl2Factory.eINSTANCE.getAadl2Package().getProperty())) {
				final Property prop = (Property)rs.getEObject(EcoreUtil.getURI(objDesc.getEObjectOrProxy()), true);
				if(prop != null && prop.getPropertyType() instanceof AadlInteger) {				
					results.add(prop);
				}
			}
			
			return results;
		}
		
		private List<PropertyConstant> getValidPropertyConstants() {
			final XtextResourceSet rs = new XtextResourceSet();
			final List<PropertyConstant> results = new ArrayList<PropertyConstant>();
			for(final IEObjectDescription objDesc : EMFIndexRetrieval.getAllClassifiersOfTypeInWorkspace(Aadl2Factory.eINSTANCE.getAadl2Package().getPropertyConstant())) {
				final PropertyConstant propConst = (PropertyConstant)rs.getEObject(EcoreUtil.getURI(objDesc.getEObjectOrProxy()), true);
				if(propConst != null && propConst.getPropertyType() instanceof AadlInteger) {		
					results.add(propConst);
				}
			}
			
			return results;
		}
		
		private void updateWidgetVisibility() {
			final String type = typeCmb.getText();
			
			final StackLayout detailsLayout = (StackLayout)detailsPane.getLayout();
			if(numberTxt.equals(type)) {
				detailsLayout.topControl = numberPane;
			} else if(propertyTxt.equals(type)) {
				detailsLayout.topControl = propertyPane;
			} else if(propertyConstantTxt.equals(type)) {
				detailsLayout.topControl = propertyConstantPane;				
			} else {
				detailsLayout.topControl = null;
			}

			detailsPane.layout();
		}

		private static String getTypeText(final ArrayDimension dim) {
			final ArraySize size = dim.getSize();
			
			if(size == null) {
				return unspecifiedTxt;
			} else if(size.getSizeProperty() == null) {
				return numberTxt;
			} else if(size.getSizeProperty() instanceof Property) {
				return propertyTxt;
			} else if(size.getSizeProperty() instanceof PropertyConstant) {
				return propertyConstantTxt;
			} else {
				return unsupportedTypeTxt;
			}
		}
		
		@Override
		public void okPressed() {
			final String type = typeCmb.getText();
			
			// Modify the dimension based on the user's selection
			if(unspecifiedTxt.equals(type)) {
				dimension.setSize(null);
			} else if(numberTxt.equals(type)) {
				dimension.createSize().setSize(numberValue.getSelection());				
			} else if(propertyTxt.equals(type)) {
				final StructuredSelection propSelection = (StructuredSelection)propertyCmb.getSelection();
				final Property prop = (Property)propSelection.getFirstElement();
				if(prop != null) {
					dimension.setSize(null);
					dimension.createSize().setSizeProperty(prop);
				}
			} else if(propertyConstantTxt.equals(type)) {
				final StructuredSelection propConstSelection = (StructuredSelection)propertyConstantCmb.getSelection();
				final PropertyConstant propConst = (PropertyConstant)propConstSelection.getFirstElement();
				if(propConst != null) {
					dimension.setSize(null);
					dimension.createSize().setSizeProperty(propConst);
				}
			}
			
			super.okPressed();
		}
	}
}
