/**
 */
package AIP;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Participant</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link AIP.Participant#getName <em>Name</em>}</li>
 *   <li>{@link AIP.Participant#getEngaging_rules <em>Engaging rules</em>}</li>
 *   <li>{@link AIP.Participant#getOutcomes <em>Outcomes</em>}</li>
 *   <li>{@link AIP.Participant#getLiveness <em>Liveness</em>}</li>
 * </ul>
 *
 * @see AIP.AIPPackage#getParticipant()
 * @model
 * @generated
 */
public interface Participant extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see AIP.AIPPackage#getParticipant_Name()
	 * @model default=""
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link AIP.Participant#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Engaging rules</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Engaging rules</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Engaging rules</em>' attribute.
	 * @see #setEngaging_rules(String)
	 * @see AIP.AIPPackage#getParticipant_Engaging_rules()
	 * @model default=""
	 * @generated
	 */
	String getEngaging_rules();

	/**
	 * Sets the value of the '{@link AIP.Participant#getEngaging_rules <em>Engaging rules</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Engaging rules</em>' attribute.
	 * @see #getEngaging_rules()
	 * @generated
	 */
	void setEngaging_rules(String value);

	/**
	 * Returns the value of the '<em><b>Outcomes</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Outcomes</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Outcomes</em>' attribute.
	 * @see #setOutcomes(String)
	 * @see AIP.AIPPackage#getParticipant_Outcomes()
	 * @model default=""
	 * @generated
	 */
	String getOutcomes();

	/**
	 * Sets the value of the '{@link AIP.Participant#getOutcomes <em>Outcomes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Outcomes</em>' attribute.
	 * @see #getOutcomes()
	 * @generated
	 */
	void setOutcomes(String value);

	/**
	 * Returns the value of the '<em><b>Liveness</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Liveness</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Liveness</em>' attribute.
	 * @see #setLiveness(String)
	 * @see AIP.AIPPackage#getParticipant_Liveness()
	 * @model
	 * @generated
	 */
	String getLiveness();

	/**
	 * Sets the value of the '{@link AIP.Participant#getLiveness <em>Liveness</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Liveness</em>' attribute.
	 * @see #getLiveness()
	 * @generated
	 */
	void setLiveness(String value);

} // Participant
