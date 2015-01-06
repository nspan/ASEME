/**
 */
package SUC;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
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
 * @see SUC.SUCFactory
 * @model kind="package"
 * @generated
 */
public interface SUCPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "SUC";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.amcl.tuc.gr/aseme/metamodels/SUC";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "SUC";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SUCPackage eINSTANCE = SUC.impl.SUCPackageImpl.init();

	/**
	 * The meta object id for the '{@link SUC.impl.SUCmodelImpl <em>SU Cmodel</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see SUC.impl.SUCmodelImpl
	 * @see SUC.impl.SUCPackageImpl#getSUCmodel()
	 * @generated
	 */
	int SU_CMODEL = 0;

	/**
	 * The feature id for the '<em><b>Roles</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SU_CMODEL__ROLES = 0;

	/**
	 * The feature id for the '<em><b>Usecases</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SU_CMODEL__USECASES = 1;

	/**
	 * The number of structural features of the '<em>SU Cmodel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SU_CMODEL_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link SUC.impl.UseCaseImpl <em>Use Case</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see SUC.impl.UseCaseImpl
	 * @see SUC.impl.SUCPackageImpl#getUseCase()
	 * @generated
	 */
	int USE_CASE = 1;

	/**
	 * The feature id for the '<em><b>Participant</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CASE__PARTICIPANT = 0;

	/**
	 * The feature id for the '<em><b>Include</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CASE__INCLUDE = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CASE__NAME = 2;

	/**
	 * The feature id for the '<em><b>Specified by</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CASE__SPECIFIED_BY = 3;

	/**
	 * The number of structural features of the '<em>Use Case</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CASE_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link SUC.impl.RoleImpl <em>Role</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see SUC.impl.RoleImpl
	 * @see SUC.impl.SUCPackageImpl#getRole()
	 * @generated
	 */
	int ROLE = 2;

	/**
	 * The feature id for the '<em><b>Participates in</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__PARTICIPATES_IN = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__NAME = 1;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__TYPE = 2;

	/**
	 * The number of structural features of the '<em>Role</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link SUC.RoleType <em>Role Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see SUC.RoleType
	 * @see SUC.impl.SUCPackageImpl#getRoleType()
	 * @generated
	 */
	int ROLE_TYPE = 3;


	/**
	 * Returns the meta object for class '{@link SUC.SUCmodel <em>SU Cmodel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>SU Cmodel</em>'.
	 * @see SUC.SUCmodel
	 * @generated
	 */
	EClass getSUCmodel();

	/**
	 * Returns the meta object for the containment reference list '{@link SUC.SUCmodel#getRoles <em>Roles</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Roles</em>'.
	 * @see SUC.SUCmodel#getRoles()
	 * @see #getSUCmodel()
	 * @generated
	 */
	EReference getSUCmodel_Roles();

	/**
	 * Returns the meta object for the containment reference list '{@link SUC.SUCmodel#getUsecases <em>Usecases</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Usecases</em>'.
	 * @see SUC.SUCmodel#getUsecases()
	 * @see #getSUCmodel()
	 * @generated
	 */
	EReference getSUCmodel_Usecases();

	/**
	 * Returns the meta object for class '{@link SUC.UseCase <em>Use Case</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Use Case</em>'.
	 * @see SUC.UseCase
	 * @generated
	 */
	EClass getUseCase();

	/**
	 * Returns the meta object for the reference list '{@link SUC.UseCase#getParticipant <em>Participant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Participant</em>'.
	 * @see SUC.UseCase#getParticipant()
	 * @see #getUseCase()
	 * @generated
	 */
	EReference getUseCase_Participant();

	/**
	 * Returns the meta object for the reference list '{@link SUC.UseCase#getInclude <em>Include</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Include</em>'.
	 * @see SUC.UseCase#getInclude()
	 * @see #getUseCase()
	 * @generated
	 */
	EReference getUseCase_Include();

	/**
	 * Returns the meta object for the attribute '{@link SUC.UseCase#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see SUC.UseCase#getName()
	 * @see #getUseCase()
	 * @generated
	 */
	EAttribute getUseCase_Name();

	/**
	 * Returns the meta object for the attribute '{@link SUC.UseCase#getSpecified_by <em>Specified by</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Specified by</em>'.
	 * @see SUC.UseCase#getSpecified_by()
	 * @see #getUseCase()
	 * @generated
	 */
	EAttribute getUseCase_Specified_by();

	/**
	 * Returns the meta object for class '{@link SUC.Role <em>Role</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Role</em>'.
	 * @see SUC.Role
	 * @generated
	 */
	EClass getRole();

	/**
	 * Returns the meta object for the reference list '{@link SUC.Role#getParticipates_in <em>Participates in</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Participates in</em>'.
	 * @see SUC.Role#getParticipates_in()
	 * @see #getRole()
	 * @generated
	 */
	EReference getRole_Participates_in();

	/**
	 * Returns the meta object for the attribute '{@link SUC.Role#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see SUC.Role#getName()
	 * @see #getRole()
	 * @generated
	 */
	EAttribute getRole_Name();

	/**
	 * Returns the meta object for the attribute '{@link SUC.Role#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see SUC.Role#getType()
	 * @see #getRole()
	 * @generated
	 */
	EAttribute getRole_Type();

	/**
	 * Returns the meta object for enum '{@link SUC.RoleType <em>Role Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Role Type</em>'.
	 * @see SUC.RoleType
	 * @generated
	 */
	EEnum getRoleType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	SUCFactory getSUCFactory();

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
		 * The meta object literal for the '{@link SUC.impl.SUCmodelImpl <em>SU Cmodel</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see SUC.impl.SUCmodelImpl
		 * @see SUC.impl.SUCPackageImpl#getSUCmodel()
		 * @generated
		 */
		EClass SU_CMODEL = eINSTANCE.getSUCmodel();

		/**
		 * The meta object literal for the '<em><b>Roles</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SU_CMODEL__ROLES = eINSTANCE.getSUCmodel_Roles();

		/**
		 * The meta object literal for the '<em><b>Usecases</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SU_CMODEL__USECASES = eINSTANCE.getSUCmodel_Usecases();

		/**
		 * The meta object literal for the '{@link SUC.impl.UseCaseImpl <em>Use Case</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see SUC.impl.UseCaseImpl
		 * @see SUC.impl.SUCPackageImpl#getUseCase()
		 * @generated
		 */
		EClass USE_CASE = eINSTANCE.getUseCase();

		/**
		 * The meta object literal for the '<em><b>Participant</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference USE_CASE__PARTICIPANT = eINSTANCE.getUseCase_Participant();

		/**
		 * The meta object literal for the '<em><b>Include</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference USE_CASE__INCLUDE = eINSTANCE.getUseCase_Include();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute USE_CASE__NAME = eINSTANCE.getUseCase_Name();

		/**
		 * The meta object literal for the '<em><b>Specified by</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute USE_CASE__SPECIFIED_BY = eINSTANCE.getUseCase_Specified_by();

		/**
		 * The meta object literal for the '{@link SUC.impl.RoleImpl <em>Role</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see SUC.impl.RoleImpl
		 * @see SUC.impl.SUCPackageImpl#getRole()
		 * @generated
		 */
		EClass ROLE = eINSTANCE.getRole();

		/**
		 * The meta object literal for the '<em><b>Participates in</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROLE__PARTICIPATES_IN = eINSTANCE.getRole_Participates_in();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ROLE__NAME = eINSTANCE.getRole_Name();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ROLE__TYPE = eINSTANCE.getRole_Type();

		/**
		 * The meta object literal for the '{@link SUC.RoleType <em>Role Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see SUC.RoleType
		 * @see SUC.impl.SUCPackageImpl#getRoleType()
		 * @generated
		 */
		EEnum ROLE_TYPE = eINSTANCE.getRoleType();

	}

} //SUCPackage
