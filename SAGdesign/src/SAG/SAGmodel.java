/**
 */
package SAG;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SA Gmodel</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link SAG.SAGmodel#getGoals <em>Goals</em>}</li>
 *   <li>{@link SAG.SAGmodel#getActors <em>Actors</em>}</li>
 * </ul>
 *
 * @see SAG.SAGPackage#getSAGmodel()
 * @model
 * @generated
 */
public interface SAGmodel extends EObject {
	/**
	 * Returns the value of the '<em><b>Goals</b></em>' containment reference list.
	 * The list contents are of type {@link SAG.Goal}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Goals</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Goals</em>' containment reference list.
	 * @see SAG.SAGPackage#getSAGmodel_Goals()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<Goal> getGoals();

	/**
	 * Returns the value of the '<em><b>Actors</b></em>' containment reference list.
	 * The list contents are of type {@link SAG.Actor}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Actors</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Actors</em>' containment reference list.
	 * @see SAG.SAGPackage#getSAGmodel_Actors()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<Actor> getActors();

} // SAGmodel
