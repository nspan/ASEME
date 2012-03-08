/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package SAG.impl;

import SAG.Actor;
import SAG.Goal;
import SAG.SAGPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Goal</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link SAG.impl.GoalImpl#getRequirements <em>Requirements</em>}</li>
 *   <li>{@link SAG.impl.GoalImpl#getName <em>Name</em>}</li>
 *   <li>{@link SAG.impl.GoalImpl#getDepender <em>Depender</em>}</li>
 *   <li>{@link SAG.impl.GoalImpl#getDependee <em>Dependee</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GoalImpl extends EObjectImpl implements Goal {
	/**
	 * The default value of the '{@link #getRequirements() <em>Requirements</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRequirements()
	 * @generated
	 * @ordered
	 */
	protected static final String REQUIREMENTS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRequirements() <em>Requirements</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRequirements()
	 * @generated
	 * @ordered
	 */
	protected String requirements = REQUIREMENTS_EDEFAULT;

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
	 * The cached value of the '{@link #getDepender() <em>Depender</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDepender()
	 * @generated
	 * @ordered
	 */
	protected Actor depender;

	/**
	 * The cached value of the '{@link #getDependee() <em>Dependee</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDependee()
	 * @generated
	 * @ordered
	 */
	protected EList<Actor> dependee;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GoalImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SAGPackage.Literals.GOAL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getRequirements() {
		return requirements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRequirements(String newRequirements) {
		String oldRequirements = requirements;
		requirements = newRequirements;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SAGPackage.GOAL__REQUIREMENTS, oldRequirements, requirements));
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
			eNotify(new ENotificationImpl(this, Notification.SET, SAGPackage.GOAL__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Actor getDepender() {
		if (depender != null && depender.eIsProxy()) {
			InternalEObject oldDepender = (InternalEObject)depender;
			depender = (Actor)eResolveProxy(oldDepender);
			if (depender != oldDepender) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SAGPackage.GOAL__DEPENDER, oldDepender, depender));
			}
		}
		return depender;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Actor basicGetDepender() {
		return depender;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDepender(Actor newDepender, NotificationChain msgs) {
		Actor oldDepender = depender;
		depender = newDepender;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SAGPackage.GOAL__DEPENDER, oldDepender, newDepender);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDepender(Actor newDepender) {
		if (newDepender != depender) {
			NotificationChain msgs = null;
			if (depender != null)
				msgs = ((InternalEObject)depender).eInverseRemove(this, SAGPackage.ACTOR__MY_GOAL, Actor.class, msgs);
			if (newDepender != null)
				msgs = ((InternalEObject)newDepender).eInverseAdd(this, SAGPackage.ACTOR__MY_GOAL, Actor.class, msgs);
			msgs = basicSetDepender(newDepender, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SAGPackage.GOAL__DEPENDER, newDepender, newDepender));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Actor> getDependee() {
		if (dependee == null) {
			dependee = new EObjectResolvingEList<Actor>(Actor.class, this, SAGPackage.GOAL__DEPENDEE);
		}
		return dependee;
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
			case SAGPackage.GOAL__DEPENDER:
				if (depender != null)
					msgs = ((InternalEObject)depender).eInverseRemove(this, SAGPackage.ACTOR__MY_GOAL, Actor.class, msgs);
				return basicSetDepender((Actor)otherEnd, msgs);
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
			case SAGPackage.GOAL__DEPENDER:
				return basicSetDepender(null, msgs);
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
			case SAGPackage.GOAL__REQUIREMENTS:
				return getRequirements();
			case SAGPackage.GOAL__NAME:
				return getName();
			case SAGPackage.GOAL__DEPENDER:
				if (resolve) return getDepender();
				return basicGetDepender();
			case SAGPackage.GOAL__DEPENDEE:
				return getDependee();
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
			case SAGPackage.GOAL__REQUIREMENTS:
				setRequirements((String)newValue);
				return;
			case SAGPackage.GOAL__NAME:
				setName((String)newValue);
				return;
			case SAGPackage.GOAL__DEPENDER:
				setDepender((Actor)newValue);
				return;
			case SAGPackage.GOAL__DEPENDEE:
				getDependee().clear();
				getDependee().addAll((Collection<? extends Actor>)newValue);
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
			case SAGPackage.GOAL__REQUIREMENTS:
				setRequirements(REQUIREMENTS_EDEFAULT);
				return;
			case SAGPackage.GOAL__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SAGPackage.GOAL__DEPENDER:
				setDepender((Actor)null);
				return;
			case SAGPackage.GOAL__DEPENDEE:
				getDependee().clear();
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
			case SAGPackage.GOAL__REQUIREMENTS:
				return REQUIREMENTS_EDEFAULT == null ? requirements != null : !REQUIREMENTS_EDEFAULT.equals(requirements);
			case SAGPackage.GOAL__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SAGPackage.GOAL__DEPENDER:
				return depender != null;
			case SAGPackage.GOAL__DEPENDEE:
				return dependee != null && !dependee.isEmpty();
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
		result.append(" (requirements: ");
		result.append(requirements);
		result.append(", name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //GoalImpl
