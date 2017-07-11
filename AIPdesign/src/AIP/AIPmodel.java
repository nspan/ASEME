/**
 */
package AIP;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>AI Pmodel</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link AIP.AIPmodel#getParticipants <em>Participants</em>}</li>
 *   <li>{@link AIP.AIPmodel#getProtocols <em>Protocols</em>}</li>
 * </ul>
 *
 * @see AIP.AIPPackage#getAIPmodel()
 * @model
 * @generated
 */
public interface AIPmodel extends EObject {
	/**
	 * Returns the value of the '<em><b>Participants</b></em>' containment reference list.
	 * The list contents are of type {@link AIP.Participant}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Participants</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Participants</em>' containment reference list.
	 * @see AIP.AIPPackage#getAIPmodel_Participants()
	 * @model containment="true" lower="2"
	 * @generated
	 */
	EList<Participant> getParticipants();

	/**
	 * Returns the value of the '<em><b>Protocols</b></em>' containment reference list.
	 * The list contents are of type {@link AIP.Protocol}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Protocols</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Protocols</em>' containment reference list.
	 * @see AIP.AIPPackage#getAIPmodel_Protocols()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<Protocol> getProtocols();

} // AIPmodel
