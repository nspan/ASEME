/**
 */
package SUC;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SU Cmodel</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link SUC.SUCmodel#getRoles <em>Roles</em>}</li>
 *   <li>{@link SUC.SUCmodel#getUsecases <em>Usecases</em>}</li>
 * </ul>
 *
 * @see SUC.SUCPackage#getSUCmodel()
 * @model
 * @generated
 */
public interface SUCmodel extends EObject {
	/**
	 * Returns the value of the '<em><b>Roles</b></em>' containment reference list.
	 * The list contents are of type {@link SUC.Role}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Roles</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Roles</em>' containment reference list.
	 * @see SUC.SUCPackage#getSUCmodel_Roles()
	 * @model containment="true" lower="2"
	 * @generated
	 */
	EList<Role> getRoles();

	/**
	 * Returns the value of the '<em><b>Usecases</b></em>' containment reference list.
	 * The list contents are of type {@link SUC.UseCase}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Usecases</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Usecases</em>' containment reference list.
	 * @see SUC.SUCPackage#getSUCmodel_Usecases()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<UseCase> getUsecases();

} // SUCmodel
