/**
 */
package SAG;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Goal</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link SAG.Goal#getRequirements <em>Requirements</em>}</li>
 *   <li>{@link SAG.Goal#getName <em>Name</em>}</li>
 *   <li>{@link SAG.Goal#getDepender <em>Depender</em>}</li>
 *   <li>{@link SAG.Goal#getDependee <em>Dependee</em>}</li>
 * </ul>
 *
 * @see SAG.SAGPackage#getGoal()
 * @model
 * @generated
 */
public interface Goal extends EObject {
	/**
	 * Returns the value of the '<em><b>Requirements</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Requirements</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Requirements</em>' attribute.
	 * @see #setRequirements(String)
	 * @see SAG.SAGPackage#getGoal_Requirements()
	 * @model
	 * @generated
	 */
	String getRequirements();

	/**
	 * Sets the value of the '{@link SAG.Goal#getRequirements <em>Requirements</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Requirements</em>' attribute.
	 * @see #getRequirements()
	 * @generated
	 */
	void setRequirements(String value);

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
	 * @see SAG.SAGPackage#getGoal_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link SAG.Goal#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Depender</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link SAG.Actor#getMy_goal <em>My goal</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Depender</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Depender</em>' reference.
	 * @see #setDepender(Actor)
	 * @see SAG.SAGPackage#getGoal_Depender()
	 * @see SAG.Actor#getMy_goal
	 * @model opposite="my_goal" required="true"
	 * @generated
	 */
	Actor getDepender();

	/**
	 * Sets the value of the '{@link SAG.Goal#getDepender <em>Depender</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Depender</em>' reference.
	 * @see #getDepender()
	 * @generated
	 */
	void setDepender(Actor value);

	/**
	 * Returns the value of the '<em><b>Dependee</b></em>' reference list.
	 * The list contents are of type {@link SAG.Actor}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dependee</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dependee</em>' reference list.
	 * @see SAG.SAGPackage#getGoal_Dependee()
	 * @model
	 * @generated
	 */
	EList<Actor> getDependee();

} // Goal
