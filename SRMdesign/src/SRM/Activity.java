/**
 * <copyright>
 * </copyright>
 *
 * $Id$
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
 * <ul>
 *   <li>{@link SRM.Activity#getName <em>Name</em>}</li>
 *   <li>{@link SRM.Activity#getFunctionality <em>Functionality</em>}</li>
 * </ul>
 * </p>
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
	 * @model unique="false" required="true" ordered="false"
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
	 * Returns the value of the '<em><b>Functionality</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Functionality</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Functionality</em>' attribute.
	 * @see #setFunctionality(String)
	 * @see SRM.SRMPackage#getActivity_Functionality()
	 * @model unique="false" ordered="false"
	 * @generated
	 */
	String getFunctionality();

	/**
	 * Sets the value of the '{@link SRM.Activity#getFunctionality <em>Functionality</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Functionality</em>' attribute.
	 * @see #getFunctionality()
	 * @generated
	 */
	void setFunctionality(String value);

} // Activity
