/**
 */
package SRM;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Activity</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link SRM.Activity#getName <em>Name</em>}</li>
 *   <li>{@link SRM.Activity#getFunctionality <em>Functionality</em>}</li>
 * </ul>
 *
 * @see SRM.SRMPackage#getActivity()
 * @model
 * @generated
 */
public interface Activity extends EObject {
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
	 * @see SRM.SRMPackage#getActivity_Name()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link SRM.Activity#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Functionality</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link SRM.Functionality#getActivities <em>Activities</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Functionality</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Functionality</em>' reference.
	 * @see #setFunctionality(Functionality)
	 * @see SRM.SRMPackage#getActivity_Functionality()
	 * @see SRM.Functionality#getActivities
	 * @model opposite="activities" required="true"
	 * @generated
	 */
	Functionality getFunctionality();

	/**
	 * Sets the value of the '{@link SRM.Activity#getFunctionality <em>Functionality</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Functionality</em>' reference.
	 * @see #getFunctionality()
	 * @generated
	 */
	void setFunctionality(Functionality value);

} // Activity
