/**
 */
package AIP.impl;

import AIP.AIPPackage;
import AIP.Participant;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Participant</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link AIP.impl.ParticipantImpl#getName <em>Name</em>}</li>
 *   <li>{@link AIP.impl.ParticipantImpl#getEngaging_rules <em>Engaging rules</em>}</li>
 *   <li>{@link AIP.impl.ParticipantImpl#getOutcomes <em>Outcomes</em>}</li>
 *   <li>{@link AIP.impl.ParticipantImpl#getLiveness <em>Liveness</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ParticipantImpl extends EObjectImpl implements Participant {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = "";

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
	 * The default value of the '{@link #getEngaging_rules() <em>Engaging rules</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEngaging_rules()
	 * @generated
	 * @ordered
	 */
	protected static final String ENGAGING_RULES_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getEngaging_rules() <em>Engaging rules</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEngaging_rules()
	 * @generated
	 * @ordered
	 */
	protected String engaging_rules = ENGAGING_RULES_EDEFAULT;

	/**
	 * The default value of the '{@link #getOutcomes() <em>Outcomes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutcomes()
	 * @generated
	 * @ordered
	 */
	protected static final String OUTCOMES_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getOutcomes() <em>Outcomes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutcomes()
	 * @generated
	 * @ordered
	 */
	protected String outcomes = OUTCOMES_EDEFAULT;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ParticipantImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AIPPackage.Literals.PARTICIPANT;
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
			eNotify(new ENotificationImpl(this, Notification.SET, AIPPackage.PARTICIPANT__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getEngaging_rules() {
		return engaging_rules;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEngaging_rules(String newEngaging_rules) {
		String oldEngaging_rules = engaging_rules;
		engaging_rules = newEngaging_rules;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AIPPackage.PARTICIPANT__ENGAGING_RULES, oldEngaging_rules, engaging_rules));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getOutcomes() {
		return outcomes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOutcomes(String newOutcomes) {
		String oldOutcomes = outcomes;
		outcomes = newOutcomes;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AIPPackage.PARTICIPANT__OUTCOMES, oldOutcomes, outcomes));
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
			eNotify(new ENotificationImpl(this, Notification.SET, AIPPackage.PARTICIPANT__LIVENESS, oldLiveness, liveness));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case AIPPackage.PARTICIPANT__NAME:
				return getName();
			case AIPPackage.PARTICIPANT__ENGAGING_RULES:
				return getEngaging_rules();
			case AIPPackage.PARTICIPANT__OUTCOMES:
				return getOutcomes();
			case AIPPackage.PARTICIPANT__LIVENESS:
				return getLiveness();
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
			case AIPPackage.PARTICIPANT__NAME:
				setName((String)newValue);
				return;
			case AIPPackage.PARTICIPANT__ENGAGING_RULES:
				setEngaging_rules((String)newValue);
				return;
			case AIPPackage.PARTICIPANT__OUTCOMES:
				setOutcomes((String)newValue);
				return;
			case AIPPackage.PARTICIPANT__LIVENESS:
				setLiveness((String)newValue);
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
			case AIPPackage.PARTICIPANT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case AIPPackage.PARTICIPANT__ENGAGING_RULES:
				setEngaging_rules(ENGAGING_RULES_EDEFAULT);
				return;
			case AIPPackage.PARTICIPANT__OUTCOMES:
				setOutcomes(OUTCOMES_EDEFAULT);
				return;
			case AIPPackage.PARTICIPANT__LIVENESS:
				setLiveness(LIVENESS_EDEFAULT);
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
			case AIPPackage.PARTICIPANT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case AIPPackage.PARTICIPANT__ENGAGING_RULES:
				return ENGAGING_RULES_EDEFAULT == null ? engaging_rules != null : !ENGAGING_RULES_EDEFAULT.equals(engaging_rules);
			case AIPPackage.PARTICIPANT__OUTCOMES:
				return OUTCOMES_EDEFAULT == null ? outcomes != null : !OUTCOMES_EDEFAULT.equals(outcomes);
			case AIPPackage.PARTICIPANT__LIVENESS:
				return LIVENESS_EDEFAULT == null ? liveness != null : !LIVENESS_EDEFAULT.equals(liveness);
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
		result.append(", engaging_rules: ");
		result.append(engaging_rules);
		result.append(", outcomes: ");
		result.append(outcomes);
		result.append(", liveness: ");
		result.append(liveness);
		result.append(')');
		return result.toString();
	}

} //ParticipantImpl
