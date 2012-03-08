/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package SRM.impl;

import SRM.Activity;
import SRM.SRMPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Activity</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link SRM.impl.ActivityImpl#getName <em>Name</em>}</li>
 *   <li>{@link SRM.impl.ActivityImpl#getFunctionality <em>Functionality</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ActivityImpl extends EObjectImpl implements Activity {
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
	 * The default value of the '{@link #getFunctionality() <em>Functionality</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFunctionality()
	 * @generated
	 * @ordered
	 */
	protected static final String FUNCTIONALITY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFunctionality() <em>Functionality</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFunctionality()
	 * @generated
	 * @ordered
	 */
	protected String functionality = FUNCTIONALITY_EDEFAULT;

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
	public String getFunctionality() {
		return functionality;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFunctionality(String newFunctionality) {
		String oldFunctionality = functionality;
		functionality = newFunctionality;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SRMPackage.ACTIVITY__FUNCTIONALITY, oldFunctionality, functionality));
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
				return getFunctionality();
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
				setFunctionality((String)newValue);
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
				setFunctionality(FUNCTIONALITY_EDEFAULT);
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
				return FUNCTIONALITY_EDEFAULT == null ? functionality != null : !FUNCTIONALITY_EDEFAULT.equals(functionality);
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
		result.append(", functionality: ");
		result.append(functionality);
		result.append(')');
		return result.toString();
	}

} //ActivityImpl
