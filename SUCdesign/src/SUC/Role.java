/**
 */
package SUC;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Role</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link SUC.Role#getParticipates_in <em>Participates in</em>}</li>
 *   <li>{@link SUC.Role#getName <em>Name</em>}</li>
 *   <li>{@link SUC.Role#getType <em>Type</em>}</li>
 * </ul>
 *
 * @see SUC.SUCPackage#getRole()
 * @model
 * @generated
 */
public interface Role extends EObject {
	/**
	 * Returns the value of the '<em><b>Participates in</b></em>' reference list.
	 * The list contents are of type {@link SUC.UseCase}.
	 * It is bidirectional and its opposite is '{@link SUC.UseCase#getParticipant <em>Participant</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Participates in</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Participates in</em>' reference list.
	 * @see SUC.SUCPackage#getRole_Participates_in()
	 * @see SUC.UseCase#getParticipant
	 * @model opposite="participant" ordered="false"
	 * @generated
	 */
	EList<UseCase> getParticipates_in();

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see SUC.SUCPackage#getRole_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link SUC.Role#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link SUC.RoleType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see SUC.RoleType
	 * @see #setType(RoleType)
	 * @see SUC.SUCPackage#getRole_Type()
	 * @model
	 * @generated
	 */
	RoleType getType();

	/**
	 * Sets the value of the '{@link SUC.Role#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see SUC.RoleType
	 * @see #getType()
	 * @generated
	 */
	void setType(RoleType value);

} // Role
