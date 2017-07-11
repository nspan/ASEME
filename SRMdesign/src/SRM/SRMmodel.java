/**
 */
package SRM;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SR Mmodel</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link SRM.SRMmodel#getActivities <em>Activities</em>}</li>
 *   <li>{@link SRM.SRMmodel#getCapabilities <em>Capabilities</em>}</li>
 *   <li>{@link SRM.SRMmodel#getRoles <em>Roles</em>}</li>
 *   <li>{@link SRM.SRMmodel#getFunctionalities <em>Functionalities</em>}</li>
 * </ul>
 *
 * @see SRM.SRMPackage#getSRMmodel()
 * @model
 * @generated
 */
public interface SRMmodel extends EObject {
	/**
	 * Returns the value of the '<em><b>Activities</b></em>' containment reference list.
	 * The list contents are of type {@link SRM.Activity}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Activities</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Activities</em>' containment reference list.
	 * @see SRM.SRMPackage#getSRMmodel_Activities()
	 * @model containment="true"
	 * @generated
	 */
	EList<Activity> getActivities();

	/**
	 * Returns the value of the '<em><b>Capabilities</b></em>' containment reference list.
	 * The list contents are of type {@link SRM.Capability}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Capabilities</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Capabilities</em>' containment reference list.
	 * @see SRM.SRMPackage#getSRMmodel_Capabilities()
	 * @model containment="true"
	 * @generated
	 */
	EList<Capability> getCapabilities();

	/**
	 * Returns the value of the '<em><b>Roles</b></em>' containment reference list.
	 * The list contents are of type {@link SRM.Role}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Roles</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Roles</em>' containment reference list.
	 * @see SRM.SRMPackage#getSRMmodel_Roles()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<Role> getRoles();

	/**
	 * Returns the value of the '<em><b>Functionalities</b></em>' containment reference list.
	 * The list contents are of type {@link SRM.Functionality}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Functionalities</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Functionalities</em>' containment reference list.
	 * @see SRM.SRMPackage#getSRMmodel_Functionalities()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<Functionality> getFunctionalities();

} // SRMmodel
