/**
 */
package SRM;

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
 *   <li>{@link SRM.Role#getRole_activities <em>Role activities</em>}</li>
 *   <li>{@link SRM.Role#getLiveness <em>Liveness</em>}</li>
 *   <li>{@link SRM.Role#getName <em>Name</em>}</li>
 *   <li>{@link SRM.Role#getCapabilities <em>Capabilities</em>}</li>
 * </ul>
 *
 * @see SRM.SRMPackage#getRole()
 * @model
 * @generated
 */
public interface Role extends EObject {
	/**
	 * Returns the value of the '<em><b>Role activities</b></em>' reference list.
	 * The list contents are of type {@link SRM.Activity}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Role activities</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Role activities</em>' reference list.
	 * @see SRM.SRMPackage#getRole_Role_activities()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	EList<Activity> getRole_activities();

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
	 * @see SRM.SRMPackage#getRole_Liveness()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	String getLiveness();

	/**
	 * Sets the value of the '{@link SRM.Role#getLiveness <em>Liveness</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Liveness</em>' attribute.
	 * @see #getLiveness()
	 * @generated
	 */
	void setLiveness(String value);

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
	 * @see SRM.SRMPackage#getRole_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link SRM.Role#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Capabilities</b></em>' reference list.
	 * The list contents are of type {@link SRM.Capability}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Capabilities</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Capabilities</em>' reference list.
	 * @see SRM.SRMPackage#getRole_Capabilities()
	 * @model ordered="false"
	 * @generated
	 */
	EList<Capability> getCapabilities();

} // Role
