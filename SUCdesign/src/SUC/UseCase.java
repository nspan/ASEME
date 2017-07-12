/**
 */
package SUC;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Use Case</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link SUC.UseCase#getParticipant <em>Participant</em>}</li>
 *   <li>{@link SUC.UseCase#getInclude <em>Include</em>}</li>
 *   <li>{@link SUC.UseCase#getName <em>Name</em>}</li>
 *   <li>{@link SUC.UseCase#getSpecified_by <em>Specified by</em>}</li>
 * </ul>
 *
 * @see SUC.SUCPackage#getUseCase()
 * @model
 * @generated
 */
public interface UseCase extends EObject {
	/**
	 * Returns the value of the '<em><b>Participant</b></em>' reference list.
	 * The list contents are of type {@link SUC.Role}.
	 * It is bidirectional and its opposite is '{@link SUC.Role#getParticipates_in <em>Participates in</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Participant</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Participant</em>' reference list.
	 * @see SUC.SUCPackage#getUseCase_Participant()
	 * @see SUC.Role#getParticipates_in
	 * @model opposite="participates_in" ordered="false"
	 * @generated
	 */
	EList<Role> getParticipant();

	/**
	 * Returns the value of the '<em><b>Include</b></em>' reference list.
	 * The list contents are of type {@link SUC.UseCase}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Include</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Include</em>' reference list.
	 * @see SUC.SUCPackage#getUseCase_Include()
	 * @model ordered="false"
	 * @generated
	 */
	EList<UseCase> getInclude();

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
	 * @see SUC.SUCPackage#getUseCase_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link SUC.UseCase#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Specified by</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Specified by</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Specified by</em>' attribute.
	 * @see #setSpecified_by(String)
	 * @see SUC.SUCPackage#getUseCase_Specified_by()
	 * @model
	 * @generated
	 */
	String getSpecified_by();

	/**
	 * Sets the value of the '{@link SUC.UseCase#getSpecified_by <em>Specified by</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Specified by</em>' attribute.
	 * @see #getSpecified_by()
	 * @generated
	 */
	void setSpecified_by(String value);

} // UseCase
