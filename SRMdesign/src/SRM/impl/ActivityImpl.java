/**
 */
package SRM.impl;

import SRM.Activity;
import SRM.Functionality;
import SRM.SRMPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Activity</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link SRM.impl.ActivityImpl#getName <em>Name</em>}</li>
 *   <li>{@link SRM.impl.ActivityImpl#getFunctionality <em>Functionality</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ActivityImpl extends MinimalEObjectImpl.Container implements Activity {
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
	 * The cached value of the '{@link #getFunctionality() <em>Functionality</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFunctionality()
	 * @generated
	 * @ordered
	 */
	protected Functionality functionality;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ActivityImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SRMPackage.Literals.ACTIVITY;
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
			eNotify(new ENotificationImpl(this, Notification.SET, SRMPackage.ACTIVITY__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Functionality getFunctionality() {
		if (functionality != null && functionality.eIsProxy()) {
			InternalEObject oldFunctionality = (InternalEObject)functionality;
			functionality = (Functionality)eResolveProxy(oldFunctionality);
			if (functionality != oldFunctionality) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SRMPackage.ACTIVITY__FUNCTIONALITY, oldFunctionality, functionality));
			}
		}
		return functionality;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Functionality basicGetFunctionality() {
		return functionality;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFunctionality(Functionality newFunctionality, NotificationChain msgs) {
		Functionality oldFunctionality = functionality;
		functionality = newFunctionality;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SRMPackage.ACTIVITY__FUNCTIONALITY, oldFunctionality, newFunctionality);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFunctionality(Functionality newFunctionality) {
		if (newFunctionality != functionality) {
			NotificationChain msgs = null;
			if (functionality != null)
				msgs = ((InternalEObject)functionality).eInverseRemove(this, SRMPackage.FUNCTIONALITY__ACTIVITIES, Functionality.class, msgs);
			if (newFunctionality != null)
				msgs = ((InternalEObject)newFunctionality).eInverseAdd(this, SRMPackage.FUNCTIONALITY__ACTIVITIES, Functionality.class, msgs);
			msgs = basicSetFunctionality(newFunctionality, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SRMPackage.ACTIVITY__FUNCTIONALITY, newFunctionality, newFunctionality));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SRMPackage.ACTIVITY__FUNCTIONALITY:
				if (functionality != null)
					msgs = ((InternalEObject)functionality).eInverseRemove(this, SRMPackage.FUNCTIONALITY__ACTIVITIES, Functionality.class, msgs);
				return basicSetFunctionality((Functionality)otherEnd, msgs);
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
			case SRMPackage.ACTIVITY__FUNCTIONALITY:
				return basicSetFunctionality(null, msgs);
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
			case SRMPackage.ACTIVITY__NAME:
				return getName();
			case SRMPackage.ACTIVITY__FUNCTIONALITY:
				if (resolve) return getFunctionality();
				return basicGetFunctionality();
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
			case SRMPackage.ACTIVITY__NAME:
				setName((String)newValue);
				return;
			case SRMPackage.ACTIVITY__FUNCTIONALITY:
				setFunctionality((Functionality)newValue);
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
			case SRMPackage.ACTIVITY__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SRMPackage.ACTIVITY__FUNCTIONALITY:
				setFunctionality((Functionality)null);
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
			case SRMPackage.ACTIVITY__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SRMPackage.ACTIVITY__FUNCTIONALITY:
				return functionality != null;
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
		result.append(')');
		return result.toString();
	}

} //ActivityImpl
