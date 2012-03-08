/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package SRM;

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
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see SRM.SRMFactory
 * @model kind="package"
 * @generated
 */
public interface SRMPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "SRM";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.amcl.tuc.gr/aseme/metamodels/SRM";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "SRM";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SRMPackage eINSTANCE = SRM.impl.SRMPackageImpl.init();

	/**
	 * The meta object id for the '{@link SRM.impl.SRMmodelImpl <em>SR Mmodel</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see SRM.impl.SRMmodelImpl
	 * @see SRM.impl.SRMPackageImpl#getSRMmodel()
	 * @generated
	 */
	int SR_MMODEL = 0;

	/**
	 * The feature id for the '<em><b>Activities</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SR_MMODEL__ACTIVITIES = 0;

	/**
	 * The feature id for the '<em><b>Capabilities</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SR_MMODEL__CAPABILITIES = 1;

	/**
	 * The feature id for the '<em><b>Roles</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SR_MMODEL__ROLES = 2;

	/**
	 * The number of structural features of the '<em>SR Mmodel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SR_MMODEL_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link SRM.impl.RoleImpl <em>Role</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see SRM.impl.RoleImpl
	 * @see SRM.impl.SRMPackageImpl#getRole()
	 * @generated
	 */
	int ROLE = 1;

	/**
	 * The feature id for the '<em><b>Activities</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__ACTIVITIES = 0;

	/**
	 * The feature id for the '<em><b>Liveness</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__LIVENESS = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__NAME = 2;

	/**
	 * The feature id for the '<em><b>Capabilities</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__CAPABILITIES = 3;

	/**
	 * The number of structural features of the '<em>Role</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link SRM.impl.ActivityImpl <em>Activity</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see SRM.impl.ActivityImpl
	 * @see SRM.impl.SRMPackageImpl#getActivity()
	 * @generated
	 */
	int ACTIVITY = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__NAME = 0;

	/**
	 * The feature id for the '<em><b>Functionality</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__FUNCTIONALITY = 1;

	/**
	 * The number of structural features of the '<em>Activity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link SRM.impl.CapabilityImpl <em>Capability</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see SRM.impl.CapabilityImpl
	 * @see SRM.impl.SRMPackageImpl#getCapability()
	 * @generated
	 */
	int CAPABILITY = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY__NAME = 0;

	/**
	 * The feature id for the '<em><b>Activities</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY__ACTIVITIES = 1;

	/**
	 * The number of structural features of the '<em>Capability</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_FEATURE_COUNT = 2;


	/**
	 * Returns the meta object for class '{@link SRM.SRMmodel <em>SR Mmodel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>SR Mmodel</em>'.
	 * @see SRM.SRMmodel
	 * @generated
	 */
	EClass getSRMmodel();

	/**
	 * Returns the meta object for the containment reference list '{@link SRM.SRMmodel#getActivities <em>Activities</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Activities</em>'.
	 * @see SRM.SRMmodel#getActivities()
	 * @see #getSRMmodel()
	 * @generated
	 */
	EReference getSRMmodel_Activities();

	/**
	 * Returns the meta object for the containment reference list '{@link SRM.SRMmodel#getCapabilities <em>Capabilities</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Capabilities</em>'.
	 * @see SRM.SRMmodel#getCapabilities()
	 * @see #getSRMmodel()
	 * @generated
	 */
	EReference getSRMmodel_Capabilities();

	/**
	 * Returns the meta object for the containment reference list '{@link SRM.SRMmodel#getRoles <em>Roles</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Roles</em>'.
	 * @see SRM.SRMmodel#getRoles()
	 * @see #getSRMmodel()
	 * @generated
	 */
	EReference getSRMmodel_Roles();

	/**
	 * Returns the meta object for class '{@link SRM.Role <em>Role</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Role</em>'.
	 * @see SRM.Role
	 * @generated
	 */
	EClass getRole();

	/**
	 * Returns the meta object for the reference list '{@link SRM.Role#getActivities <em>Activities</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Activities</em>'.
	 * @see SRM.Role#getActivities()
	 * @see #getRole()
	 * @generated
	 */
	EReference getRole_Activities();

	/**
	 * Returns the meta object for the attribute '{@link SRM.Role#getLiveness <em>Liveness</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Liveness</em>'.
	 * @see SRM.Role#getLiveness()
	 * @see #getRole()
	 * @generated
	 */
	EAttribute getRole_Liveness();

	/**
	 * Returns the meta object for the attribute '{@link SRM.Role#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see SRM.Role#getName()
	 * @see #getRole()
	 * @generated
	 */
	EAttribute getRole_Name();

	/**
	 * Returns the meta object for the reference list '{@link SRM.Role#getCapabilities <em>Capabilities</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Capabilities</em>'.
	 * @see SRM.Role#getCapabilities()
	 * @see #getRole()
	 * @generated
	 */
	EReference getRole_Capabilities();

	/**
	 * Returns the meta object for class '{@link SRM.Activity <em>Activity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Activity</em>'.
	 * @see SRM.Activity
	 * @generated
	 */
	EClass getActivity();

	/**
	 * Returns the meta object for the attribute '{@link SRM.Activity#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see SRM.Activity#getName()
	 * @see #getActivity()
	 * @generated
	 */
	EAttribute getActivity_Name();

	/**
	 * Returns the meta object for the attribute '{@link SRM.Activity#getFunctionality <em>Functionality</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Functionality</em>'.
	 * @see SRM.Activity#getFunctionality()
	 * @see #getActivity()
	 * @generated
	 */
	EAttribute getActivity_Functionality();

	/**
	 * Returns the meta object for class '{@link SRM.Capability <em>Capability</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Capability</em>'.
	 * @see SRM.Capability
	 * @generated
	 */
	EClass getCapability();

	/**
	 * Returns the meta object for the attribute '{@link SRM.Capability#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see SRM.Capability#getName()
	 * @see #getCapability()
	 * @generated
	 */
	EAttribute getCapability_Name();

	/**
	 * Returns the meta object for the reference list '{@link SRM.Capability#getActivities <em>Activities</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Activities</em>'.
	 * @see SRM.Capability#getActivities()
	 * @see #getCapability()
	 * @generated
	 */
	EReference getCapability_Activities();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	SRMFactory getSRMFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link SRM.impl.SRMmodelImpl <em>SR Mmodel</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see SRM.impl.SRMmodelImpl
		 * @see SRM.impl.SRMPackageImpl#getSRMmodel()
		 * @generated
		 */
		EClass SR_MMODEL = eINSTANCE.getSRMmodel();

		/**
		 * The meta object literal for the '<em><b>Activities</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SR_MMODEL__ACTIVITIES = eINSTANCE.getSRMmodel_Activities();

		/**
		 * The meta object literal for the '<em><b>Capabilities</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SR_MMODEL__CAPABILITIES = eINSTANCE.getSRMmodel_Capabilities();

		/**
		 * The meta object literal for the '<em><b>Roles</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SR_MMODEL__ROLES = eINSTANCE.getSRMmodel_Roles();

		/**
		 * The meta object literal for the '{@link SRM.impl.RoleImpl <em>Role</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see SRM.impl.RoleImpl
		 * @see SRM.impl.SRMPackageImpl#getRole()
		 * @generated
		 */
		EClass ROLE = eINSTANCE.getRole();

		/**
		 * The meta object literal for the '<em><b>Activities</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROLE__ACTIVITIES = eINSTANCE.getRole_Activities();

		/**
		 * The meta object literal for the '<em><b>Liveness</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ROLE__LIVENESS = eINSTANCE.getRole_Liveness();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ROLE__NAME = eINSTANCE.getRole_Name();

		/**
		 * The meta object literal for the '<em><b>Capabilities</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROLE__CAPABILITIES = eINSTANCE.getRole_Capabilities();

		/**
		 * The meta object literal for the '{@link SRM.impl.ActivityImpl <em>Activity</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see SRM.impl.ActivityImpl
		 * @see SRM.impl.SRMPackageImpl#getActivity()
		 * @generated
		 */
		EClass ACTIVITY = eINSTANCE.getActivity();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTIVITY__NAME = eINSTANCE.getActivity_Name();

		/**
		 * The meta object literal for the '<em><b>Functionality</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTIVITY__FUNCTIONALITY = eINSTANCE.getActivity_Functionality();

		/**
		 * The meta object literal for the '{@link SRM.impl.CapabilityImpl <em>Capability</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see SRM.impl.CapabilityImpl
		 * @see SRM.impl.SRMPackageImpl#getCapability()
		 * @generated
		 */
		EClass CAPABILITY = eINSTANCE.getCapability();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CAPABILITY__NAME = eINSTANCE.getCapability_Name();

		/**
		 * The meta object literal for the '<em><b>Activities</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CAPABILITY__ACTIVITIES = eINSTANCE.getCapability_Activities();

	}

} //SRMPackage
