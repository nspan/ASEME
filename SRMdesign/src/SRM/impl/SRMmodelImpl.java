/**
 */
package SRM.impl;

import SRM.Activity;
import SRM.Capability;
import SRM.Functionality;
import SRM.Role;
import SRM.SRMPackage;
import SRM.SRMmodel;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SR Mmodel</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link SRM.impl.SRMmodelImpl#getActivities <em>Activities</em>}</li>
 *   <li>{@link SRM.impl.SRMmodelImpl#getCapabilities <em>Capabilities</em>}</li>
 *   <li>{@link SRM.impl.SRMmodelImpl#getRoles <em>Roles</em>}</li>
 *   <li>{@link SRM.impl.SRMmodelImpl#getFunctionalities <em>Functionalities</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SRMmodelImpl extends MinimalEObjectImpl.Container implements SRMmodel {
	/**
	 * The cached value of the '{@link #getActivities() <em>Activities</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActivities()
	 * @generated
	 * @ordered
	 */
	protected EList<Activity> activities;

	/**
	 * The cached value of the '{@link #getCapabilities() <em>Capabilities</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCapabilities()
	 * @generated
	 * @ordered
	 */
	protected EList<Capability> capabilities;

	/**
	 * The cached value of the '{@link #getRoles() <em>Roles</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRoles()
	 * @generated
	 * @ordered
	 */
	protected EList<Role> roles;

	/**
	 * The cached value of the '{@link #getFunctionalities() <em>Functionalities</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFunctionalities()
	 * @generated
	 * @ordered
	 */
	protected EList<Functionality> functionalities;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SRMmodelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SRMPackage.Literals.SR_MMODEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Activity> getActivities() {
		if (activities == null) {
			activities = new EObjectContainmentEList<Activity>(Activity.class, this, SRMPackage.SR_MMODEL__ACTIVITIES);
		}
		return activities;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Capability> getCapabilities() {
		if (capabilities == null) {
			capabilities = new EObjectContainmentEList<Capability>(Capability.class, this, SRMPackage.SR_MMODEL__CAPABILITIES);
		}
		return capabilities;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Role> getRoles() {
		if (roles == null) {
			roles = new EObjectContainmentEList<Role>(Role.class, this, SRMPackage.SR_MMODEL__ROLES);
		}
		return roles;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Functionality> getFunctionalities() {
		if (functionalities == null) {
			functionalities = new EObjectContainmentEList<Functionality>(Functionality.class, this, SRMPackage.SR_MMODEL__FUNCTIONALITIES);
		}
		return functionalities;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SRMPackage.SR_MMODEL__ACTIVITIES:
				return ((InternalEList<?>)getActivities()).basicRemove(otherEnd, msgs);
			case SRMPackage.SR_MMODEL__CAPABILITIES:
				return ((InternalEList<?>)getCapabilities()).basicRemove(otherEnd, msgs);
			case SRMPackage.SR_MMODEL__ROLES:
				return ((InternalEList<?>)getRoles()).basicRemove(otherEnd, msgs);
			case SRMPackage.SR_MMODEL__FUNCTIONALITIES:
				return ((InternalEList<?>)getFunctionalities()).basicRemove(otherEnd, msgs);
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
			case SRMPackage.SR_MMODEL__ACTIVITIES:
				return getActivities();
			case SRMPackage.SR_MMODEL__CAPABILITIES:
				return getCapabilities();
			case SRMPackage.SR_MMODEL__ROLES:
				return getRoles();
			case SRMPackage.SR_MMODEL__FUNCTIONALITIES:
				return getFunctionalities();
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
			case SRMPackage.SR_MMODEL__ACTIVITIES:
				getActivities().clear();
				getActivities().addAll((Collection<? extends Activity>)newValue);
				return;
			case SRMPackage.SR_MMODEL__CAPABILITIES:
				getCapabilities().clear();
				getCapabilities().addAll((Collection<? extends Capability>)newValue);
				return;
			case SRMPackage.SR_MMODEL__ROLES:
				getRoles().clear();
				getRoles().addAll((Collection<? extends Role>)newValue);
				return;
			case SRMPackage.SR_MMODEL__FUNCTIONALITIES:
				getFunctionalities().clear();
				getFunctionalities().addAll((Collection<? extends Functionality>)newValue);
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
			case SRMPackage.SR_MMODEL__ACTIVITIES:
				getActivities().clear();
				return;
			case SRMPackage.SR_MMODEL__CAPABILITIES:
				getCapabilities().clear();
				return;
			case SRMPackage.SR_MMODEL__ROLES:
				getRoles().clear();
				return;
			case SRMPackage.SR_MMODEL__FUNCTIONALITIES:
				getFunctionalities().clear();
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
			case SRMPackage.SR_MMODEL__ACTIVITIES:
				return activities != null && !activities.isEmpty();
			case SRMPackage.SR_MMODEL__CAPABILITIES:
				return capabilities != null && !capabilities.isEmpty();
			case SRMPackage.SR_MMODEL__ROLES:
				return roles != null && !roles.isEmpty();
			case SRMPackage.SR_MMODEL__FUNCTIONALITIES:
				return functionalities != null && !functionalities.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //SRMmodelImpl
