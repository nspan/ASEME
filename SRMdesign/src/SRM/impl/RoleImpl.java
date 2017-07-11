/**
 */
package SRM.impl;

import SRM.Activity;
import SRM.Capability;
import SRM.Role;
import SRM.SRMPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Role</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link SRM.impl.RoleImpl#getRole_activities <em>Role activities</em>}</li>
 *   <li>{@link SRM.impl.RoleImpl#getLiveness <em>Liveness</em>}</li>
 *   <li>{@link SRM.impl.RoleImpl#getName <em>Name</em>}</li>
 *   <li>{@link SRM.impl.RoleImpl#getCapabilities <em>Capabilities</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RoleImpl extends MinimalEObjectImpl.Container implements Role {
	/**
	 * The cached value of the '{@link #getRole_activities() <em>Role activities</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRole_activities()
	 * @generated
	 * @ordered
	 */
	protected EList<Activity> role_activities;

	/**
	 * The default value of the '{@link #getLiveness() <em>Liveness</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLiveness()
	 * @generated
	 * @ordered
	 */
	protected static final String LIVENESS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLiveness() <em>Liveness</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLiveness()
	 * @generated
	 * @ordered
	 */
	protected String liveness = LIVENESS_EDEFAULT;

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
	 * The cached value of the '{@link #getCapabilities() <em>Capabilities</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCapabilities()
	 * @generated
	 * @ordered
	 */
	protected EList<Capability> capabilities;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RoleImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SRMPackage.Literals.ROLE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Activity> getRole_activities() {
		if (role_activities == null) {
			role_activities = new EObjectResolvingEList<Activity>(Activity.class, this, SRMPackage.ROLE__ROLE_ACTIVITIES);
		}
		return role_activities;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLiveness() {
		return liveness;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLiveness(String newLiveness) {
		String oldLiveness = liveness;
		liveness = newLiveness;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SRMPackage.ROLE__LIVENESS, oldLiveness, liveness));
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
			eNotify(new ENotificationImpl(this, Notification.SET, SRMPackage.ROLE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Capability> getCapabilities() {
		if (capabilities == null) {
			capabilities = new EObjectResolvingEList<Capability>(Capability.class, this, SRMPackage.ROLE__CAPABILITIES);
		}
		return capabilities;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SRMPackage.ROLE__ROLE_ACTIVITIES:
				return getRole_activities();
			case SRMPackage.ROLE__LIVENESS:
				return getLiveness();
			case SRMPackage.ROLE__NAME:
				return getName();
			case SRMPackage.ROLE__CAPABILITIES:
				return getCapabilities();
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
			case SRMPackage.ROLE__ROLE_ACTIVITIES:
				getRole_activities().clear();
				getRole_activities().addAll((Collection<? extends Activity>)newValue);
				return;
			case SRMPackage.ROLE__LIVENESS:
				setLiveness((String)newValue);
				return;
			case SRMPackage.ROLE__NAME:
				setName((String)newValue);
				return;
			case SRMPackage.ROLE__CAPABILITIES:
				getCapabilities().clear();
				getCapabilities().addAll((Collection<? extends Capability>)newValue);
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
			case SRMPackage.ROLE__ROLE_ACTIVITIES:
				getRole_activities().clear();
				return;
			case SRMPackage.ROLE__LIVENESS:
				setLiveness(LIVENESS_EDEFAULT);
				return;
			case SRMPackage.ROLE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SRMPackage.ROLE__CAPABILITIES:
				getCapabilities().clear();
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
			case SRMPackage.ROLE__ROLE_ACTIVITIES:
				return role_activities != null && !role_activities.isEmpty();
			case SRMPackage.ROLE__LIVENESS:
				return LIVENESS_EDEFAULT == null ? liveness != null : !LIVENESS_EDEFAULT.equals(liveness);
			case SRMPackage.ROLE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SRMPackage.ROLE__CAPABILITIES:
				return capabilities != null && !capabilities.isEmpty();
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
		result.append(" (liveness: ");
		result.append(liveness);
		result.append(", name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //RoleImpl
