/**
 */
package statechart.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import statechart.Node;
import statechart.StatechartPackage;
import statechart.Variable;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link statechart.impl.NodeImpl#getName <em>Name</em>}</li>
 *   <li>{@link statechart.impl.NodeImpl#getLabel <em>Label</em>}</li>
 *   <li>{@link statechart.impl.NodeImpl#getType <em>Type</em>}</li>
 *   <li>{@link statechart.impl.NodeImpl#getActivity <em>Activity</em>}</li>
 *   <li>{@link statechart.impl.NodeImpl#getVariables <em>Variables</em>}</li>
 *   <li>{@link statechart.impl.NodeImpl#getChildren <em>Children</em>}</li>
 *   <li>{@link statechart.impl.NodeImpl#getFather <em>Father</em>}</li>
 *   <li>{@link statechart.impl.NodeImpl#getActions <em>Actions</em>}</li>
 *   <li>{@link statechart.impl.NodeImpl#getMetadata <em>Metadata</em>}</li>
 * </ul>
 *
 * @generated
 */
public class NodeImpl extends EObjectImpl implements Node {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getLabel() <em>Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLabel()
	 * @generated
	 * @ordered
	 */
	protected static final String LABEL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLabel() <em>Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLabel()
	 * @generated
	 * @ordered
	 */
	protected String label = LABEL_EDEFAULT;

	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final String TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected String type = TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getActivity() <em>Activity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActivity()
	 * @generated
	 * @ordered
	 */
	protected static final String ACTIVITY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getActivity() <em>Activity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActivity()
	 * @generated
	 * @ordered
	 */
	protected String activity = ACTIVITY_EDEFAULT;

	/**
	 * The cached value of the '{@link #getVariables() <em>Variables</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVariables()
	 * @generated
	 * @ordered
	 */
	protected EList<Variable> variables;

	/**
	 * The cached value of the '{@link #getChildren() <em>Children</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChildren()
	 * @generated
	 * @ordered
	 */
	protected EList<Node> children;

	/**
	 * The default value of the '{@link #getActions() <em>Actions</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActions()
	 * @generated
	 * @ordered
	 */
	protected static final String ACTIONS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getActions() <em>Actions</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActions()
	 * @generated
	 * @ordered
	 */
	protected String actions = ACTIONS_EDEFAULT;

	/**
	 * The default value of the '{@link #getMetadata() <em>Metadata</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMetadata()
	 * @generated
	 * @ordered
	 */
	protected static final String METADATA_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMetadata() <em>Metadata</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMetadata()
	 * @generated
	 * @ordered
	 */
	protected String metadata = METADATA_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NodeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return StatechartPackage.Literals.NODE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatechartPackage.NODE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLabel(String newLabel) {
		String oldLabel = label;
		label = newLabel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatechartPackage.NODE__LABEL, oldLabel, label));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(String newType) {
		String oldType = type;
		type = newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatechartPackage.NODE__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getActivity() {
		return activity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActivity(String newActivity) {
		String oldActivity = activity;
		activity = newActivity;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatechartPackage.NODE__ACTIVITY, oldActivity, activity));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Variable> getVariables() {
		if (variables == null) {
			variables = new EObjectResolvingEList<Variable>(Variable.class, this, StatechartPackage.NODE__VARIABLES);
		}
		return variables;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Node> getChildren() {
		if (children == null) {
			children = new EObjectContainmentWithInverseEList<Node>(Node.class, this, StatechartPackage.NODE__CHILDREN, StatechartPackage.NODE__FATHER);
		}
		return children;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Node getFather() {
		if (eContainerFeatureID() != StatechartPackage.NODE__FATHER) return null;
		return (Node)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFather(Node newFather, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newFather, StatechartPackage.NODE__FATHER, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFather(Node newFather) {
		if (newFather != eInternalContainer() || (eContainerFeatureID() != StatechartPackage.NODE__FATHER && newFather != null)) {
			if (EcoreUtil.isAncestor(this, newFather))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newFather != null)
				msgs = ((InternalEObject)newFather).eInverseAdd(this, StatechartPackage.NODE__CHILDREN, Node.class, msgs);
			msgs = basicSetFather(newFather, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatechartPackage.NODE__FATHER, newFather, newFather));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getActions() {
		return actions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActions(String newActions) {
		String oldActions = actions;
		actions = newActions;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatechartPackage.NODE__ACTIONS, oldActions, actions));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getMetadata() {
		return metadata;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMetadata(String newMetadata) {
		String oldMetadata = metadata;
		metadata = newMetadata;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatechartPackage.NODE__METADATA, oldMetadata, metadata));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case StatechartPackage.NODE__CHILDREN:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getChildren()).basicAdd(otherEnd, msgs);
			case StatechartPackage.NODE__FATHER:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetFather((Node)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case StatechartPackage.NODE__CHILDREN:
				return ((InternalEList<?>)getChildren()).basicRemove(otherEnd, msgs);
			case StatechartPackage.NODE__FATHER:
				return basicSetFather(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case StatechartPackage.NODE__FATHER:
				return eInternalContainer().eInverseRemove(this, StatechartPackage.NODE__CHILDREN, Node.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case StatechartPackage.NODE__NAME:
				return getName();
			case StatechartPackage.NODE__LABEL:
				return getLabel();
			case StatechartPackage.NODE__TYPE:
				return getType();
			case StatechartPackage.NODE__ACTIVITY:
				return getActivity();
			case StatechartPackage.NODE__VARIABLES:
				return getVariables();
			case StatechartPackage.NODE__CHILDREN:
				return getChildren();
			case StatechartPackage.NODE__FATHER:
				return getFather();
			case StatechartPackage.NODE__ACTIONS:
				return getActions();
			case StatechartPackage.NODE__METADATA:
				return getMetadata();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case StatechartPackage.NODE__NAME:
				setName((String)newValue);
				return;
			case StatechartPackage.NODE__LABEL:
				setLabel((String)newValue);
				return;
			case StatechartPackage.NODE__TYPE:
				setType((String)newValue);
				return;
			case StatechartPackage.NODE__ACTIVITY:
				setActivity((String)newValue);
				return;
			case StatechartPackage.NODE__VARIABLES:
				getVariables().clear();
				getVariables().addAll((Collection<? extends Variable>)newValue);
				return;
			case StatechartPackage.NODE__CHILDREN:
				getChildren().clear();
				getChildren().addAll((Collection<? extends Node>)newValue);
				return;
			case StatechartPackage.NODE__FATHER:
				setFather((Node)newValue);
				return;
			case StatechartPackage.NODE__ACTIONS:
				setActions((String)newValue);
				return;
			case StatechartPackage.NODE__METADATA:
				setMetadata((String)newValue);
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
			case StatechartPackage.NODE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case StatechartPackage.NODE__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case StatechartPackage.NODE__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case StatechartPackage.NODE__ACTIVITY:
				setActivity(ACTIVITY_EDEFAULT);
				return;
			case StatechartPackage.NODE__VARIABLES:
				getVariables().clear();
				return;
			case StatechartPackage.NODE__CHILDREN:
				getChildren().clear();
				return;
			case StatechartPackage.NODE__FATHER:
				setFather((Node)null);
				return;
			case StatechartPackage.NODE__ACTIONS:
				setActions(ACTIONS_EDEFAULT);
				return;
			case StatechartPackage.NODE__METADATA:
				setMetadata(METADATA_EDEFAULT);
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
			case StatechartPackage.NODE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case StatechartPackage.NODE__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case StatechartPackage.NODE__TYPE:
				return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
			case StatechartPackage.NODE__ACTIVITY:
				return ACTIVITY_EDEFAULT == null ? activity != null : !ACTIVITY_EDEFAULT.equals(activity);
			case StatechartPackage.NODE__VARIABLES:
				return variables != null && !variables.isEmpty();
			case StatechartPackage.NODE__CHILDREN:
				return children != null && !children.isEmpty();
			case StatechartPackage.NODE__FATHER:
				return getFather() != null;
			case StatechartPackage.NODE__ACTIONS:
				return ACTIONS_EDEFAULT == null ? actions != null : !ACTIONS_EDEFAULT.equals(actions);
			case StatechartPackage.NODE__METADATA:
				return METADATA_EDEFAULT == null ? metadata != null : !METADATA_EDEFAULT.equals(metadata);
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
		result.append(" (name: ");
		result.append(name);
		result.append(", label: ");
		result.append(label);
		result.append(", type: ");
		result.append(type);
		result.append(", activity: ");
		result.append(activity);
		result.append(", actions: ");
		result.append(actions);
		result.append(", metadata: ");
		result.append(metadata);
		result.append(')');
		return result.toString();
	}

} //NodeImpl
