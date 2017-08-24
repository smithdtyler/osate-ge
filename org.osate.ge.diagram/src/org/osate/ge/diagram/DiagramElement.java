/**
 */
package org.osate.ge.diagram;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.osate.ge.diagram.DiagramElement#getId <em>Id</em>}</li>
 *   <li>{@link org.osate.ge.diagram.DiagramElement#getBo <em>Bo</em>}</li>
 *   <li>{@link org.osate.ge.diagram.DiagramElement#isManual <em>Manual</em>}</li>
 *   <li>{@link org.osate.ge.diagram.DiagramElement#getAutoContentsFilter <em>Auto Contents Filter</em>}</li>
 *   <li>{@link org.osate.ge.diagram.DiagramElement#getPosition <em>Position</em>}</li>
 *   <li>{@link org.osate.ge.diagram.DiagramElement#getSize <em>Size</em>}</li>
 *   <li>{@link org.osate.ge.diagram.DiagramElement#getDockArea <em>Dock Area</em>}</li>
 *   <li>{@link org.osate.ge.diagram.DiagramElement#getBendpoints <em>Bendpoints</em>}</li>
 *   <li>{@link org.osate.ge.diagram.DiagramElement#getPrimaryLabelPosition <em>Primary Label Position</em>}</li>
 *   <li>{@link org.osate.ge.diagram.DiagramElement#getBackground <em>Background</em>}</li>
 *   <li>{@link org.osate.ge.diagram.DiagramElement#getOutline <em>Outline</em>}</li>
 *   <li>{@link org.osate.ge.diagram.DiagramElement#getFontColor <em>Font Color</em>}</li>
 *   <li>{@link org.osate.ge.diagram.DiagramElement#getFontSize <em>Font Size</em>}</li>
 *   <li>{@link org.osate.ge.diagram.DiagramElement#getLineWidth <em>Line Width</em>}</li>
 * </ul>
 *
 * @see org.osate.ge.diagram.DiagramPackage#getDiagramElement()
 * @model kind="class"
 * @generated
 */
public class DiagramElement extends DiagramNode {
	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final Long ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected Long id = ID_EDEFAULT;

	/**
	 * The cached value of the '{@link #getBo() <em>Bo</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBo()
	 * @generated
	 * @ordered
	 */
	protected RelativeBusinessObjectReference bo;

	/**
	 * The default value of the '{@link #isManual() <em>Manual</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isManual()
	 * @generated
	 * @ordered
	 */
	protected static final boolean MANUAL_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isManual() <em>Manual</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isManual()
	 * @generated
	 * @ordered
	 */
	protected boolean manual = MANUAL_EDEFAULT;

	/**
	 * The default value of the '{@link #getAutoContentsFilter() <em>Auto Contents Filter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAutoContentsFilter()
	 * @generated
	 * @ordered
	 */
	protected static final String AUTO_CONTENTS_FILTER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAutoContentsFilter() <em>Auto Contents Filter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAutoContentsFilter()
	 * @generated
	 * @ordered
	 */
	protected String autoContentsFilter = AUTO_CONTENTS_FILTER_EDEFAULT;

	/**
	 * The cached value of the '{@link #getPosition() <em>Position</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPosition()
	 * @generated
	 * @ordered
	 */
	protected Point position;

	/**
	 * The cached value of the '{@link #getSize() <em>Size</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSize()
	 * @generated
	 * @ordered
	 */
	protected Dimension size;

	/**
	 * The default value of the '{@link #getDockArea() <em>Dock Area</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDockArea()
	 * @generated
	 * @ordered
	 */
	protected static final String DOCK_AREA_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDockArea() <em>Dock Area</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDockArea()
	 * @generated
	 * @ordered
	 */
	protected String dockArea = DOCK_AREA_EDEFAULT;

	/**
	 * The cached value of the '{@link #getBendpoints() <em>Bendpoints</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBendpoints()
	 * @generated
	 * @ordered
	 */
	protected BendpointList bendpoints;

	/**
	 * The cached value of the '{@link #getPrimaryLabelPosition() <em>Primary Label Position</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrimaryLabelPosition()
	 * @generated
	 * @ordered
	 */
	protected Point primaryLabelPosition;

	/**
	 * The default value of the '{@link #getBackground() <em>Background</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBackground()
	 * @generated
	 * @ordered
	 */
	protected static final String BACKGROUND_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBackground() <em>Background</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBackground()
	 * @generated
	 * @ordered
	 */
	protected String background = BACKGROUND_EDEFAULT;

	/**
	 * The default value of the '{@link #getOutline() <em>Outline</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutline()
	 * @generated
	 * @ordered
	 */
	protected static final String OUTLINE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOutline() <em>Outline</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutline()
	 * @generated
	 * @ordered
	 */
	protected String outline = OUTLINE_EDEFAULT;

	/**
	 * The default value of the '{@link #getFontColor() <em>Font Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFontColor()
	 * @generated
	 * @ordered
	 */
	protected static final String FONT_COLOR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFontColor() <em>Font Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFontColor()
	 * @generated
	 * @ordered
	 */
	protected String fontColor = FONT_COLOR_EDEFAULT;

	/**
	 * The default value of the '{@link #getFontSize() <em>Font Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFontSize()
	 * @generated
	 * @ordered
	 */
	protected static final double FONT_SIZE_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getFontSize() <em>Font Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFontSize()
	 * @generated
	 * @ordered
	 */
	protected double fontSize = FONT_SIZE_EDEFAULT;

	/**
	 * The default value of the '{@link #getLineWidth() <em>Line Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLineWidth()
	 * @generated
	 * @ordered
	 */
	protected static final int LINE_WIDTH_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getLineWidth() <em>Line Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLineWidth()
	 * @generated
	 * @ordered
	 */
	protected int lineWidth = LINE_WIDTH_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DiagramElement() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DiagramPackage.Literals.DIAGRAM_ELEMENT;
	}

	/**
	 * Returns the value of the '<em><b>Bo</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Bo</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Bo</em>' containment reference.
	 * @see #setBo(RelativeBusinessObjectReference)
	 * @see org.osate.ge.diagram.DiagramPackage#getDiagramElement_Bo()
	 * @model containment="true"
	 * @generated
	 */
	public RelativeBusinessObjectReference getBo() {
		return bo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBo(RelativeBusinessObjectReference newBo, NotificationChain msgs) {
		RelativeBusinessObjectReference oldBo = bo;
		bo = newBo;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DiagramPackage.DIAGRAM_ELEMENT__BO, oldBo, newBo);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * Sets the value of the '{@link org.osate.ge.diagram.DiagramElement#getBo <em>Bo</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Bo</em>' containment reference.
	 * @see #getBo()
	 * @generated
	 */
	public void setBo(RelativeBusinessObjectReference newBo) {
		if (newBo != bo) {
			NotificationChain msgs = null;
			if (bo != null)
				msgs = ((InternalEObject)bo).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DiagramPackage.DIAGRAM_ELEMENT__BO, null, msgs);
			if (newBo != null)
				msgs = ((InternalEObject)newBo).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DiagramPackage.DIAGRAM_ELEMENT__BO, null, msgs);
			msgs = basicSetBo(newBo, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.DIAGRAM_ELEMENT__BO, newBo, newBo));
	}

	/**
	 * Returns the value of the '<em><b>Manual</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Manual</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Manual</em>' attribute.
	 * @see #setManual(boolean)
	 * @see org.osate.ge.diagram.DiagramPackage#getDiagramElement_Manual()
	 * @model default="false" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 * @generated
	 */
	public boolean isManual() {
		return manual;
	}

	/**
	 * Sets the value of the '{@link org.osate.ge.diagram.DiagramElement#isManual <em>Manual</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Manual</em>' attribute.
	 * @see #isManual()
	 * @generated
	 */
	public void setManual(boolean newManual) {
		boolean oldManual = manual;
		manual = newManual;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.DIAGRAM_ELEMENT__MANUAL, oldManual, manual));
	}

	/**
	 * Returns the value of the '<em><b>Auto Contents Filter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Auto Contents Filter</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Auto Contents Filter</em>' attribute.
	 * @see #setAutoContentsFilter(String)
	 * @see org.osate.ge.diagram.DiagramPackage#getDiagramElement_AutoContentsFilter()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 * @generated
	 */
	public String getAutoContentsFilter() {
		return autoContentsFilter;
	}

	/**
	 * Sets the value of the '{@link org.osate.ge.diagram.DiagramElement#getAutoContentsFilter <em>Auto Contents Filter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Auto Contents Filter</em>' attribute.
	 * @see #getAutoContentsFilter()
	 * @generated
	 */
	public void setAutoContentsFilter(String newAutoContentsFilter) {
		String oldAutoContentsFilter = autoContentsFilter;
		autoContentsFilter = newAutoContentsFilter;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.DIAGRAM_ELEMENT__AUTO_CONTENTS_FILTER, oldAutoContentsFilter, autoContentsFilter));
	}

	/**
	 * Returns the value of the '<em><b>Position</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Position</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Position</em>' containment reference.
	 * @see #setPosition(Point)
	 * @see org.osate.ge.diagram.DiagramPackage#getDiagramElement_Position()
	 * @model containment="true"
	 * @generated
	 */
	public Point getPosition() {
		return position;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPosition(Point newPosition, NotificationChain msgs) {
		Point oldPosition = position;
		position = newPosition;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DiagramPackage.DIAGRAM_ELEMENT__POSITION, oldPosition, newPosition);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * Sets the value of the '{@link org.osate.ge.diagram.DiagramElement#getPosition <em>Position</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Position</em>' containment reference.
	 * @see #getPosition()
	 * @generated
	 */
	public void setPosition(Point newPosition) {
		if (newPosition != position) {
			NotificationChain msgs = null;
			if (position != null)
				msgs = ((InternalEObject)position).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DiagramPackage.DIAGRAM_ELEMENT__POSITION, null, msgs);
			if (newPosition != null)
				msgs = ((InternalEObject)newPosition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DiagramPackage.DIAGRAM_ELEMENT__POSITION, null, msgs);
			msgs = basicSetPosition(newPosition, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.DIAGRAM_ELEMENT__POSITION, newPosition, newPosition));
	}

	/**
	 * Returns the value of the '<em><b>Size</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Size</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Size</em>' containment reference.
	 * @see #setSize(Dimension)
	 * @see org.osate.ge.diagram.DiagramPackage#getDiagramElement_Size()
	 * @model containment="true"
	 * @generated
	 */
	public Dimension getSize() {
		return size;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSize(Dimension newSize, NotificationChain msgs) {
		Dimension oldSize = size;
		size = newSize;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DiagramPackage.DIAGRAM_ELEMENT__SIZE, oldSize, newSize);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * Sets the value of the '{@link org.osate.ge.diagram.DiagramElement#getSize <em>Size</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Size</em>' containment reference.
	 * @see #getSize()
	 * @generated
	 */
	public void setSize(Dimension newSize) {
		if (newSize != size) {
			NotificationChain msgs = null;
			if (size != null)
				msgs = ((InternalEObject)size).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DiagramPackage.DIAGRAM_ELEMENT__SIZE, null, msgs);
			if (newSize != null)
				msgs = ((InternalEObject)newSize).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DiagramPackage.DIAGRAM_ELEMENT__SIZE, null, msgs);
			msgs = basicSetSize(newSize, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.DIAGRAM_ELEMENT__SIZE, newSize, newSize));
	}

	/**
	 * Returns the value of the '<em><b>Dock Area</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dock Area</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dock Area</em>' attribute.
	 * @see #setDockArea(String)
	 * @see org.osate.ge.diagram.DiagramPackage#getDiagramElement_DockArea()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 * @generated
	 */
	public String getDockArea() {
		return dockArea;
	}

	/**
	 * Sets the value of the '{@link org.osate.ge.diagram.DiagramElement#getDockArea <em>Dock Area</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dock Area</em>' attribute.
	 * @see #getDockArea()
	 * @generated
	 */
	public void setDockArea(String newDockArea) {
		String oldDockArea = dockArea;
		dockArea = newDockArea;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.DIAGRAM_ELEMENT__DOCK_AREA, oldDockArea, dockArea));
	}

	/**
	 * Returns the value of the '<em><b>Bendpoints</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Bendpoints</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Bendpoints</em>' containment reference.
	 * @see #setBendpoints(BendpointList)
	 * @see org.osate.ge.diagram.DiagramPackage#getDiagramElement_Bendpoints()
	 * @model containment="true"
	 * @generated
	 */
	public BendpointList getBendpoints() {
		return bendpoints;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBendpoints(BendpointList newBendpoints, NotificationChain msgs) {
		BendpointList oldBendpoints = bendpoints;
		bendpoints = newBendpoints;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DiagramPackage.DIAGRAM_ELEMENT__BENDPOINTS, oldBendpoints, newBendpoints);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * Sets the value of the '{@link org.osate.ge.diagram.DiagramElement#getBendpoints <em>Bendpoints</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Bendpoints</em>' containment reference.
	 * @see #getBendpoints()
	 * @generated
	 */
	public void setBendpoints(BendpointList newBendpoints) {
		if (newBendpoints != bendpoints) {
			NotificationChain msgs = null;
			if (bendpoints != null)
				msgs = ((InternalEObject)bendpoints).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DiagramPackage.DIAGRAM_ELEMENT__BENDPOINTS, null, msgs);
			if (newBendpoints != null)
				msgs = ((InternalEObject)newBendpoints).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DiagramPackage.DIAGRAM_ELEMENT__BENDPOINTS, null, msgs);
			msgs = basicSetBendpoints(newBendpoints, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.DIAGRAM_ELEMENT__BENDPOINTS, newBendpoints, newBendpoints));
	}

	/**
	 * Returns the value of the '<em><b>Primary Label Position</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Only supported for connections.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Primary Label Position</em>' containment reference.
	 * @see #setPrimaryLabelPosition(Point)
	 * @see org.osate.ge.diagram.DiagramPackage#getDiagramElement_PrimaryLabelPosition()
	 * @model containment="true"
	 * @generated
	 */
	public Point getPrimaryLabelPosition() {
		return primaryLabelPosition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPrimaryLabelPosition(Point newPrimaryLabelPosition, NotificationChain msgs) {
		Point oldPrimaryLabelPosition = primaryLabelPosition;
		primaryLabelPosition = newPrimaryLabelPosition;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DiagramPackage.DIAGRAM_ELEMENT__PRIMARY_LABEL_POSITION, oldPrimaryLabelPosition, newPrimaryLabelPosition);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * Sets the value of the '{@link org.osate.ge.diagram.DiagramElement#getPrimaryLabelPosition <em>Primary Label Position</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Primary Label Position</em>' containment reference.
	 * @see #getPrimaryLabelPosition()
	 * @generated
	 */
	public void setPrimaryLabelPosition(Point newPrimaryLabelPosition) {
		if (newPrimaryLabelPosition != primaryLabelPosition) {
			NotificationChain msgs = null;
			if (primaryLabelPosition != null)
				msgs = ((InternalEObject)primaryLabelPosition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DiagramPackage.DIAGRAM_ELEMENT__PRIMARY_LABEL_POSITION, null, msgs);
			if (newPrimaryLabelPosition != null)
				msgs = ((InternalEObject)newPrimaryLabelPosition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DiagramPackage.DIAGRAM_ELEMENT__PRIMARY_LABEL_POSITION, null, msgs);
			msgs = basicSetPrimaryLabelPosition(newPrimaryLabelPosition, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.DIAGRAM_ELEMENT__PRIMARY_LABEL_POSITION, newPrimaryLabelPosition, newPrimaryLabelPosition));
	}

	/**
	 * Returns the value of the '<em><b>Background</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Background</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Background</em>' attribute.
	 * @see #setBackground(String)
	 * @see org.osate.ge.diagram.DiagramPackage#getDiagramElement_Background()
	 * @model dataType="org.osate.ge.diagram.Color"
	 * @generated
	 */
	public String getBackground() {
		return background;
	}

	/**
	 * Sets the value of the '{@link org.osate.ge.diagram.DiagramElement#getBackground <em>Background</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Background</em>' attribute.
	 * @see #getBackground()
	 * @generated
	 */
	public void setBackground(String newBackground) {
		String oldBackground = background;
		background = newBackground;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.DIAGRAM_ELEMENT__BACKGROUND, oldBackground, background));
	}

	/**
	 * Returns the value of the '<em><b>Outline</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Outline</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Outline</em>' attribute.
	 * @see #setOutline(String)
	 * @see org.osate.ge.diagram.DiagramPackage#getDiagramElement_Outline()
	 * @model dataType="org.osate.ge.diagram.Color"
	 * @generated
	 */
	public String getOutline() {
		return outline;
	}

	/**
	 * Sets the value of the '{@link org.osate.ge.diagram.DiagramElement#getOutline <em>Outline</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Outline</em>' attribute.
	 * @see #getOutline()
	 * @generated
	 */
	public void setOutline(String newOutline) {
		String oldOutline = outline;
		outline = newOutline;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.DIAGRAM_ELEMENT__OUTLINE, oldOutline, outline));
	}

	/**
	 * Returns the value of the '<em><b>Font Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Font Color</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Font Color</em>' attribute.
	 * @see #setFontColor(String)
	 * @see org.osate.ge.diagram.DiagramPackage#getDiagramElement_FontColor()
	 * @model dataType="org.osate.ge.diagram.Color"
	 * @generated
	 */
	public String getFontColor() {
		return fontColor;
	}

	/**
	 * Sets the value of the '{@link org.osate.ge.diagram.DiagramElement#getFontColor <em>Font Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Font Color</em>' attribute.
	 * @see #getFontColor()
	 * @generated
	 */
	public void setFontColor(String newFontColor) {
		String oldFontColor = fontColor;
		fontColor = newFontColor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.DIAGRAM_ELEMENT__FONT_COLOR, oldFontColor, fontColor));
	}

	/**
	 * Returns the value of the '<em><b>Font Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Font Size</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Font Size</em>' attribute.
	 * @see #setFontSize(double)
	 * @see org.osate.ge.diagram.DiagramPackage#getDiagramElement_FontSize()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Double"
	 * @generated
	 */
	public double getFontSize() {
		return fontSize;
	}

	/**
	 * Sets the value of the '{@link org.osate.ge.diagram.DiagramElement#getFontSize <em>Font Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Font Size</em>' attribute.
	 * @see #getFontSize()
	 * @generated
	 */
	public void setFontSize(double newFontSize) {
		double oldFontSize = fontSize;
		fontSize = newFontSize;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.DIAGRAM_ELEMENT__FONT_SIZE, oldFontSize, fontSize));
	}

	/**
	 * Returns the value of the '<em><b>Line Width</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Line Width</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Line Width</em>' attribute.
	 * @see #setLineWidth(int)
	 * @see org.osate.ge.diagram.DiagramPackage#getDiagramElement_LineWidth()
	 * @model default="0" dataType="org.eclipse.emf.ecore.xml.type.Int"
	 * @generated
	 */
	public int getLineWidth() {
		return lineWidth;
	}

	/**
	 * Sets the value of the '{@link org.osate.ge.diagram.DiagramElement#getLineWidth <em>Line Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Line Width</em>' attribute.
	 * @see #getLineWidth()
	 * @generated
	 */
	public void setLineWidth(int newLineWidth) {
		int oldLineWidth = lineWidth;
		lineWidth = newLineWidth;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.DIAGRAM_ELEMENT__LINE_WIDTH, oldLineWidth, lineWidth));
	}

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(Long)
	 * @see org.osate.ge.diagram.DiagramPackage#getDiagramElement_Id()
	 * @model id="true" dataType="org.eclipse.emf.ecore.xml.type.LongObject"
	 * @generated
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the value of the '{@link org.osate.ge.diagram.DiagramElement#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	public void setId(Long newId) {
		Long oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.DIAGRAM_ELEMENT__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DiagramPackage.DIAGRAM_ELEMENT__BO:
				return basicSetBo(null, msgs);
			case DiagramPackage.DIAGRAM_ELEMENT__POSITION:
				return basicSetPosition(null, msgs);
			case DiagramPackage.DIAGRAM_ELEMENT__SIZE:
				return basicSetSize(null, msgs);
			case DiagramPackage.DIAGRAM_ELEMENT__BENDPOINTS:
				return basicSetBendpoints(null, msgs);
			case DiagramPackage.DIAGRAM_ELEMENT__PRIMARY_LABEL_POSITION:
				return basicSetPrimaryLabelPosition(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DiagramPackage.DIAGRAM_ELEMENT__ID:
				return getId();
			case DiagramPackage.DIAGRAM_ELEMENT__BO:
				return getBo();
			case DiagramPackage.DIAGRAM_ELEMENT__MANUAL:
				return isManual();
			case DiagramPackage.DIAGRAM_ELEMENT__AUTO_CONTENTS_FILTER:
				return getAutoContentsFilter();
			case DiagramPackage.DIAGRAM_ELEMENT__POSITION:
				return getPosition();
			case DiagramPackage.DIAGRAM_ELEMENT__SIZE:
				return getSize();
			case DiagramPackage.DIAGRAM_ELEMENT__DOCK_AREA:
				return getDockArea();
			case DiagramPackage.DIAGRAM_ELEMENT__BENDPOINTS:
				return getBendpoints();
			case DiagramPackage.DIAGRAM_ELEMENT__PRIMARY_LABEL_POSITION:
				return getPrimaryLabelPosition();
			case DiagramPackage.DIAGRAM_ELEMENT__BACKGROUND:
				return getBackground();
			case DiagramPackage.DIAGRAM_ELEMENT__OUTLINE:
				return getOutline();
			case DiagramPackage.DIAGRAM_ELEMENT__FONT_COLOR:
				return getFontColor();
			case DiagramPackage.DIAGRAM_ELEMENT__FONT_SIZE:
				return getFontSize();
			case DiagramPackage.DIAGRAM_ELEMENT__LINE_WIDTH:
				return getLineWidth();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case DiagramPackage.DIAGRAM_ELEMENT__ID:
				setId((Long)newValue);
				return;
			case DiagramPackage.DIAGRAM_ELEMENT__BO:
				setBo((RelativeBusinessObjectReference)newValue);
				return;
			case DiagramPackage.DIAGRAM_ELEMENT__MANUAL:
				setManual((Boolean)newValue);
				return;
			case DiagramPackage.DIAGRAM_ELEMENT__AUTO_CONTENTS_FILTER:
				setAutoContentsFilter((String)newValue);
				return;
			case DiagramPackage.DIAGRAM_ELEMENT__POSITION:
				setPosition((Point)newValue);
				return;
			case DiagramPackage.DIAGRAM_ELEMENT__SIZE:
				setSize((Dimension)newValue);
				return;
			case DiagramPackage.DIAGRAM_ELEMENT__DOCK_AREA:
				setDockArea((String)newValue);
				return;
			case DiagramPackage.DIAGRAM_ELEMENT__BENDPOINTS:
				setBendpoints((BendpointList)newValue);
				return;
			case DiagramPackage.DIAGRAM_ELEMENT__PRIMARY_LABEL_POSITION:
				setPrimaryLabelPosition((Point)newValue);
				return;
			case DiagramPackage.DIAGRAM_ELEMENT__BACKGROUND:
				setBackground((String)newValue);
				return;
			case DiagramPackage.DIAGRAM_ELEMENT__OUTLINE:
				setOutline((String)newValue);
				return;
			case DiagramPackage.DIAGRAM_ELEMENT__FONT_COLOR:
				setFontColor((String)newValue);
				return;
			case DiagramPackage.DIAGRAM_ELEMENT__FONT_SIZE:
				setFontSize((Double)newValue);
				return;
			case DiagramPackage.DIAGRAM_ELEMENT__LINE_WIDTH:
				setLineWidth((Integer)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case DiagramPackage.DIAGRAM_ELEMENT__ID:
				setId(ID_EDEFAULT);
				return;
			case DiagramPackage.DIAGRAM_ELEMENT__BO:
				setBo((RelativeBusinessObjectReference)null);
				return;
			case DiagramPackage.DIAGRAM_ELEMENT__MANUAL:
				setManual(MANUAL_EDEFAULT);
				return;
			case DiagramPackage.DIAGRAM_ELEMENT__AUTO_CONTENTS_FILTER:
				setAutoContentsFilter(AUTO_CONTENTS_FILTER_EDEFAULT);
				return;
			case DiagramPackage.DIAGRAM_ELEMENT__POSITION:
				setPosition((Point)null);
				return;
			case DiagramPackage.DIAGRAM_ELEMENT__SIZE:
				setSize((Dimension)null);
				return;
			case DiagramPackage.DIAGRAM_ELEMENT__DOCK_AREA:
				setDockArea(DOCK_AREA_EDEFAULT);
				return;
			case DiagramPackage.DIAGRAM_ELEMENT__BENDPOINTS:
				setBendpoints((BendpointList)null);
				return;
			case DiagramPackage.DIAGRAM_ELEMENT__PRIMARY_LABEL_POSITION:
				setPrimaryLabelPosition((Point)null);
				return;
			case DiagramPackage.DIAGRAM_ELEMENT__BACKGROUND:
				setBackground(BACKGROUND_EDEFAULT);
				return;
			case DiagramPackage.DIAGRAM_ELEMENT__OUTLINE:
				setOutline(OUTLINE_EDEFAULT);
				return;
			case DiagramPackage.DIAGRAM_ELEMENT__FONT_COLOR:
				setFontColor(FONT_COLOR_EDEFAULT);
				return;
			case DiagramPackage.DIAGRAM_ELEMENT__FONT_SIZE:
				setFontSize(FONT_SIZE_EDEFAULT);
				return;
			case DiagramPackage.DIAGRAM_ELEMENT__LINE_WIDTH:
				setLineWidth(LINE_WIDTH_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case DiagramPackage.DIAGRAM_ELEMENT__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case DiagramPackage.DIAGRAM_ELEMENT__BO:
				return bo != null;
			case DiagramPackage.DIAGRAM_ELEMENT__MANUAL:
				return manual != MANUAL_EDEFAULT;
			case DiagramPackage.DIAGRAM_ELEMENT__AUTO_CONTENTS_FILTER:
				return AUTO_CONTENTS_FILTER_EDEFAULT == null ? autoContentsFilter != null : !AUTO_CONTENTS_FILTER_EDEFAULT.equals(autoContentsFilter);
			case DiagramPackage.DIAGRAM_ELEMENT__POSITION:
				return position != null;
			case DiagramPackage.DIAGRAM_ELEMENT__SIZE:
				return size != null;
			case DiagramPackage.DIAGRAM_ELEMENT__DOCK_AREA:
				return DOCK_AREA_EDEFAULT == null ? dockArea != null : !DOCK_AREA_EDEFAULT.equals(dockArea);
			case DiagramPackage.DIAGRAM_ELEMENT__BENDPOINTS:
				return bendpoints != null;
			case DiagramPackage.DIAGRAM_ELEMENT__PRIMARY_LABEL_POSITION:
				return primaryLabelPosition != null;
			case DiagramPackage.DIAGRAM_ELEMENT__BACKGROUND:
				return BACKGROUND_EDEFAULT == null ? background != null : !BACKGROUND_EDEFAULT.equals(background);
			case DiagramPackage.DIAGRAM_ELEMENT__OUTLINE:
				return OUTLINE_EDEFAULT == null ? outline != null : !OUTLINE_EDEFAULT.equals(outline);
			case DiagramPackage.DIAGRAM_ELEMENT__FONT_COLOR:
				return FONT_COLOR_EDEFAULT == null ? fontColor != null : !FONT_COLOR_EDEFAULT.equals(fontColor);
			case DiagramPackage.DIAGRAM_ELEMENT__FONT_SIZE:
				return fontSize != FONT_SIZE_EDEFAULT;
			case DiagramPackage.DIAGRAM_ELEMENT__LINE_WIDTH:
				return lineWidth != LINE_WIDTH_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (id: ");
		result.append(id);
		result.append(", manual: ");
		result.append(manual);
		result.append(", autoContentsFilter: ");
		result.append(autoContentsFilter);
		result.append(", dockArea: ");
		result.append(dockArea);
		result.append(", background: ");
		result.append(background);
		result.append(", outline: ");
		result.append(outline);
		result.append(", fontColor: ");
		result.append(fontColor);
		result.append(", fontSize: ");
		result.append(fontSize);
		result.append(", lineWidth: ");
		result.append(lineWidth);
		result.append(')');
		return result.toString();
	}

} // DiagramElement
