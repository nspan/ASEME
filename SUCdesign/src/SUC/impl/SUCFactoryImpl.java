/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package SUC.impl;

import SUC.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SUCFactoryImpl extends EFactoryImpl implements SUCFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static SUCFactory init() {
		try {
			SUCFactory theSUCFactory = (SUCFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.amcl.tuc.gr/aseme/metamodels/SUC"); 
			if (theSUCFactory != null) {
				return theSUCFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new SUCFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SUCFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case SUCPackage.SU_CMODEL: return createSUCmodel();
			case SUCPackage.USE_CASE: return createUseCase();
			case SUCPackage.ROLE: return createRole();
			case SUCPackage.HUMAN_ROLE: return createHumanRole();
			case SUCPackage.SYSTEM_ROLE: return createSystemRole();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SUCmodel createSUCmodel() {
		SUCmodelImpl suCmodel = new SUCmodelImpl();
		return suCmodel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UseCase createUseCase() {
		UseCaseImpl useCase = new UseCaseImpl();
		return useCase;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Role createRole() {
		RoleImpl role = new RoleImpl();
		return role;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HumanRole createHumanRole() {
		HumanRoleImpl humanRole = new HumanRoleImpl();
		return humanRole;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SystemRole createSystemRole() {
		SystemRoleImpl systemRole = new SystemRoleImpl();
		return systemRole;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SUCPackage getSUCPackage() {
		return (SUCPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static SUCPackage getPackage() {
		return SUCPackage.eINSTANCE;
	}

} //SUCFactoryImpl
