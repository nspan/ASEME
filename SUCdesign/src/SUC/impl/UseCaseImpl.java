/**
 */
package SUC.impl;

import SUC.Role;
import SUC.SUCPackage;
import SUC.UseCase;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Use Case</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link SUC.impl.UseCaseImpl#getParticipant <em>Participant</em>}</li>
 *   <li>{@link SUC.impl.UseCaseImpl#getInclude <em>Include</em>}</li>
 *   <li>{@link SUC.impl.UseCaseImpl#getName <em>Name</em>}</li>
 *   <li>{@link SUC.impl.UseCaseImpl#getSpecified_by <em>Specified by</em>}</li>
 * </ul>
 *
 * @generated
 */
public class UseCaseImpl extends MinimalEObjectImpl.Container implements UseCase {
	/**
	 * The cached value of the '{@link #getParticipant() <em>Participant</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParticipant()
	 * @generated
	 * @ordered
	 */
	protected EList<Role> participant;

	/**
	 * The cached value of the '{@link #getInclude() <em>Include</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInclude()
	 * @generated
	 * @ordered
	 */
	protected EList<UseCase> include;

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
	 * The default value of the '{@link #getSpecified_by() <em>Specified by</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSpecified_by()
	 * @generated
	 * @ordered
	 */
	protected static final String SPECIFIED_BY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSpecified_by() <em>Specified by</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSpecified_by()
	 * @generated
	 * @ordered
	 */
	protected String specified_by = SPECIFIED_BY_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UseCaseImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SUCPackage.Literals.USE_CASE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Role> getParticipant() {
		if (participant == null) {
			participant = new EObjectWithInverseResolvingEList.ManyInverse<Role>(Role.class, this, SUCPackage.USE_CASE__PARTICIPANT, SUCPackage.ROLE__PARTICIPATES_IN);
		}
		return participant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<UseCase> getInclude() {
		if (include == null) {
			include = new EObjectResolvingEList<UseCase>(UseCase.class, this, SUCPackage.USE_CASE__INCLUDE);
		}
		return include;
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
			eNotify(new ENotificationImpl(this, Notification.SET, SUCPackage.USE_CASE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSpecified_by() {
		return specified_by;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSpecified_by(String newSpecified_by) {
		String oldSpecified_by = specified_by;
		specified_by = newSpecified_by;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SUCPackage.USE_CASE__SPECIFIED_BY, oldSpecified_by, specified_by));
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
			case SUCPackage.USE_CASE__PARTICIPANT:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getParticipant()).basicAdd(otherEnd, msgs);
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
			case SUCPackage.USE_CASE__PARTICIPANT:
				return ((InternalEList<?>)getParticipant()).basicRemove(otherEnd, msgs);
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
			case SUCPackage.USE_CASE__PARTICIPANT:
				return getParticipant();
			case SUCPackage.USE_CASE__INCLUDE:
				return getInclude();
			case SUCPackage.USE_CASE__NAME:
				return getName();
			case SUCPackage.USE_CASE__SPECIFIED_BY:
				return getSpecified_by();
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
			case SUCPackage.USE_CASE__PARTICIPANT:
				getParticipant().clear();
				getParticipant().addAll((Collection<? extends Role>)newValue);
				return;
			case SUCPackage.USE_CASE__INCLUDE:
				getInclude().clear();
				getInclude().addAll((Collection<? extends UseCase>)newValue);
				return;
			case SUCPackage.USE_CASE__NAME:
				setName((String)newValue);
				return;
			case SUCPackage.USE_CASE__SPECIFIED_BY:
				setSpecified_by((String)newValue);
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
			case SUCPackage.USE_CASE__PARTICIPANT:
				getParticipant().clear();
				return;
			case SUCPackage.USE_CASE__INCLUDE:
				getInclude().clear();
				return;
			case SUCPackage.USE_CASE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SUCPackage.USE_CASE__SPECIFIED_BY:
				setSpecified_by(SPECIFIED_BY_EDEFAULT);
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
			case SUCPackage.USE_CASE__PARTICIPANT:
				return participant != null && !participant.isEmpty();
			case SUCPackage.USE_CASE__INCLUDE:
				return include != null && !include.isEmpty();
			case SUCPackage.USE_CASE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SUCPackage.USE_CASE__SPECIFIED_BY:
				return SPECIFIED_BY_EDEFAULT == null ? specified_by != null : !SPECIFIED_BY_EDEFAULT.equals(specified_by);
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
		result.append(", specified_by: ");
		result.append(specified_by);
		result.append(')');
		return result.toString();
	}

} //UseCaseImpl
