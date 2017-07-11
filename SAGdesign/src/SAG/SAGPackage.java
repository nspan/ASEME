/**
 */
package SAG;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see SAG.SAGFactory
 * @model kind="package"
 * @generated
 */
public interface SAGPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "SAG";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.amcl.tuc.gr/aseme/metamodels/SAG";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "SAG";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SAGPackage eINSTANCE = SAG.impl.SAGPackageImpl.init();

	/**
	 * The meta object id for the '{@link SAG.impl.SAGmodelImpl <em>SA Gmodel</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see SAG.impl.SAGmodelImpl
	 * @see SAG.impl.SAGPackageImpl#getSAGmodel()
	 * @generated
	 */
	int SA_GMODEL = 0;

	/**
	 * The feature id for the '<em><b>Goals</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SA_GMODEL__GOALS = 0;

	/**
	 * The feature id for the '<em><b>Actors</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SA_GMODEL__ACTORS = 1;

	/**
	 * The number of structural features of the '<em>SA Gmodel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SA_GMODEL_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>SA Gmodel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SA_GMODEL_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link SAG.impl.ActorImpl <em>Actor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see SAG.impl.ActorImpl
	 * @see SAG.impl.SAGPackageImpl#getActor()
	 * @generated
	 */
	int ACTOR = 1;

	/**
	 * The feature id for the '<em><b>My goal</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__MY_GOAL = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__NAME = 1;

	/**
	 * The number of structural features of the '<em>Actor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Actor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link SAG.impl.GoalImpl <em>Goal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see SAG.impl.GoalImpl
	 * @see SAG.impl.SAGPackageImpl#getGoal()
	 * @generated
	 */
	int GOAL = 2;

	/**
	 * The feature id for the '<em><b>Requirements</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GOAL__REQUIREMENTS = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GOAL__NAME = 1;

	/**
	 * The feature id for the '<em><b>Depender</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GOAL__DEPENDER = 2;

	/**
	 * The feature id for the '<em><b>Dependee</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GOAL__DEPENDEE = 3;

	/**
	 * The number of structural features of the '<em>Goal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GOAL_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Goal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GOAL_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link SAG.SAGmodel <em>SA Gmodel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>SA Gmodel</em>'.
	 * @see SAG.SAGmodel
	 * @generated
	 */
	EClass getSAGmodel();

	/**
	 * Returns the meta object for the containment reference list '{@link SAG.SAGmodel#getGoals <em>Goals</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Goals</em>'.
	 * @see SAG.SAGmodel#getGoals()
	 * @see #getSAGmodel()
	 * @generated
	 */
	EReference getSAGmodel_Goals();

	/**
	 * Returns the meta object for the containment reference list '{@link SAG.SAGmodel#getActors <em>Actors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Actors</em>'.
	 * @see SAG.SAGmodel#getActors()
	 * @see #getSAGmodel()
	 * @generated
	 */
	EReference getSAGmodel_Actors();

	/**
	 * Returns the meta object for class '{@link SAG.Actor <em>Actor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Actor</em>'.
	 * @see SAG.Actor
	 * @generated
	 */
	EClass getActor();

	/**
	 * Returns the meta object for the reference list '{@link SAG.Actor#getMy_goal <em>My goal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>My goal</em>'.
	 * @see SAG.Actor#getMy_goal()
	 * @see #getActor()
	 * @generated
	 */
	EReference getActor_My_goal();

	/**
	 * Returns the meta object for the attribute '{@link SAG.Actor#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see SAG.Actor#getName()
	 * @see #getActor()
	 * @generated
	 */
	EAttribute getActor_Name();

	/**
	 * Returns the meta object for class '{@link SAG.Goal <em>Goal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Goal</em>'.
	 * @see SAG.Goal
	 * @generated
	 */
	EClass getGoal();

	/**
	 * Returns the meta object for the attribute '{@link SAG.Goal#getRequirements <em>Requirements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Requirements</em>'.
	 * @see SAG.Goal#getRequirements()
	 * @see #getGoal()
	 * @generated
	 */
	EAttribute getGoal_Requirements();

	/**
	 * Returns the meta object for the attribute '{@link SAG.Goal#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see SAG.Goal#getName()
	 * @see #getGoal()
	 * @generated
	 */
	EAttribute getGoal_Name();

	/**
	 * Returns the meta object for the reference '{@link SAG.Goal#getDepender <em>Depender</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Depender</em>'.
	 * @see SAG.Goal#getDepender()
	 * @see #getGoal()
	 * @generated
	 */
	EReference getGoal_Depender();

	/**
	 * Returns the meta object for the reference list '{@link SAG.Goal#getDependee <em>Dependee</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Dependee</em>'.
	 * @see SAG.Goal#getDependee()
	 * @see #getGoal()
	 * @generated
	 */
	EReference getGoal_Dependee();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	SAGFactory getSAGFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link SAG.impl.SAGmodelImpl <em>SA Gmodel</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see SAG.impl.SAGmodelImpl
		 * @see SAG.impl.SAGPackageImpl#getSAGmodel()
		 * @generated
		 */
		EClass SA_GMODEL = eINSTANCE.getSAGmodel();

		/**
		 * The meta object literal for the '<em><b>Goals</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SA_GMODEL__GOALS = eINSTANCE.getSAGmodel_Goals();

		/**
		 * The meta object literal for the '<em><b>Actors</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SA_GMODEL__ACTORS = eINSTANCE.getSAGmodel_Actors();

		/**
		 * The meta object literal for the '{@link SAG.impl.ActorImpl <em>Actor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see SAG.impl.ActorImpl
		 * @see SAG.impl.SAGPackageImpl#getActor()
		 * @generated
		 */
		EClass ACTOR = eINSTANCE.getActor();

		/**
		 * The meta object literal for the '<em><b>My goal</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTOR__MY_GOAL = eINSTANCE.getActor_My_goal();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTOR__NAME = eINSTANCE.getActor_Name();

		/**
		 * The meta object literal for the '{@link SAG.impl.GoalImpl <em>Goal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see SAG.impl.GoalImpl
		 * @see SAG.impl.SAGPackageImpl#getGoal()
		 * @generated
		 */
		EClass GOAL = eINSTANCE.getGoal();

		/**
		 * The meta object literal for the '<em><b>Requirements</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GOAL__REQUIREMENTS = eINSTANCE.getGoal_Requirements();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GOAL__NAME = eINSTANCE.getGoal_Name();

		/**
		 * The meta object literal for the '<em><b>Depender</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GOAL__DEPENDER = eINSTANCE.getGoal_Depender();

		/**
		 * The meta object literal for the '<em><b>Dependee</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GOAL__DEPENDEE = eINSTANCE.getGoal_Dependee();

	}

} //SAGPackage
