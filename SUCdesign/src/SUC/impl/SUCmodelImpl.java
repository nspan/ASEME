/**
 */
package SUC.impl;

import SUC.Role;
import SUC.SUCPackage;
import SUC.SUCmodel;
import SUC.UseCase;

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
 * An implementation of the model object '<em><b>SU Cmodel</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link SUC.impl.SUCmodelImpl#getRoles <em>Roles</em>}</li>
 *   <li>{@link SUC.impl.SUCmodelImpl#getUsecases <em>Usecases</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SUCmodelImpl extends MinimalEObjectImpl.Container implements SUCmodel {
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
	 * The cached value of the '{@link #getUsecases() <em>Usecases</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUsecases()
	 * @generated
	 * @ordered
	 */
	protected EList<UseCase> usecases;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SUCmodelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SUCPackage.Literals.SU_CMODEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Role> getRoles() {
		if (roles == null) {
			roles = new EObjectContainmentEList<Role>(Role.class, this, SUCPackage.SU_CMODEL__ROLES);
		}
		return roles;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<UseCase> getUsecases() {
		if (usecases == null) {
			usecases = new EObjectContainmentEList<UseCase>(UseCase.class, this, SUCPackage.SU_CMODEL__USECASES);
		}
		return usecases;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SUCPackage.SU_CMODEL__ROLES:
				return ((InternalEList<?>)getRoles()).basicRemove(otherEnd, msgs);
			case SUCPackage.SU_CMODEL__USECASES:
				return ((InternalEList<?>)getUsecases()).basicRemove(otherEnd, msgs);
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
			case SUCPackage.SU_CMODEL__ROLES:
				return getRoles();
			case SUCPackage.SU_CMODEL__USECASES:
				return getUsecases();
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
			case SUCPackage.SU_CMODEL__ROLES:
				getRoles().clear();
				getRoles().addAll((Collection<? extends Role>)newValue);
				return;
			case SUCPackage.SU_CMODEL__USECASES:
				getUsecases().clear();
				getUsecases().addAll((Collection<? extends UseCase>)newValue);
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
			case SUCPackage.SU_CMODEL__ROLES:
				getRoles().clear();
				return;
			case SUCPackage.SU_CMODEL__USECASES:
				getUsecases().clear();
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
			case SUCPackage.SU_CMODEL__ROLES:
				return roles != null && !roles.isEmpty();
			case SUCPackage.SU_CMODEL__USECASES:
				return usecases != null && !usecases.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //SUCmodelImpl
