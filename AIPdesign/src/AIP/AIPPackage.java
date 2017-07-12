/**
 */
package AIP;

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
 * @see AIP.AIPFactory
 * @model kind="package"
 * @generated
 */
public interface AIPPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "AIP";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.acml.tuc.gr/aseme/metamodels/AIP";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "AIP";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	AIPPackage eINSTANCE = AIP.impl.AIPPackageImpl.init();

	/**
	 * The meta object id for the '{@link AIP.impl.AIPmodelImpl <em>AI Pmodel</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see AIP.impl.AIPmodelImpl
	 * @see AIP.impl.AIPPackageImpl#getAIPmodel()
	 * @generated
	 */
	int AI_PMODEL = 0;

	/**
	 * The feature id for the '<em><b>Participants</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AI_PMODEL__PARTICIPANTS = 0;

	/**
	 * The feature id for the '<em><b>Protocols</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AI_PMODEL__PROTOCOLS = 1;

	/**
	 * The number of structural features of the '<em>AI Pmodel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AI_PMODEL_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link AIP.impl.ProtocolImpl <em>Protocol</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see AIP.impl.ProtocolImpl
	 * @see AIP.impl.AIPPackageImpl#getProtocol()
	 * @generated
	 */
	int PROTOCOL = 1;

	/**
	 * The feature id for the '<em><b>Participants</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROTOCOL__PARTICIPANTS = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROTOCOL__NAME = 1;

	/**
	 * The number of structural features of the '<em>Protocol</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROTOCOL_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link AIP.impl.ParticipantImpl <em>Participant</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see AIP.impl.ParticipantImpl
	 * @see AIP.impl.AIPPackageImpl#getParticipant()
	 * @generated
	 */
	int PARTICIPANT = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT__NAME = 0;

	/**
	 * The feature id for the '<em><b>Engaging rules</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT__ENGAGING_RULES = 1;

	/**
	 * The feature id for the '<em><b>Outcomes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT__OUTCOMES = 2;

	/**
	 * The feature id for the '<em><b>Liveness</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT__LIVENESS = 3;

	/**
	 * The number of structural features of the '<em>Participant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT_FEATURE_COUNT = 4;

	/**
	 * Returns the meta object for class '{@link AIP.AIPmodel <em>AI Pmodel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>AI Pmodel</em>'.
	 * @see AIP.AIPmodel
	 * @generated
	 */
	EClass getAIPmodel();

	/**
	 * Returns the meta object for the containment reference list '{@link AIP.AIPmodel#getParticipants <em>Participants</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Participants</em>'.
	 * @see AIP.AIPmodel#getParticipants()
	 * @see #getAIPmodel()
	 * @generated
	 */
	EReference getAIPmodel_Participants();

	/**
	 * Returns the meta object for the containment reference list '{@link AIP.AIPmodel#getProtocols <em>Protocols</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Protocols</em>'.
	 * @see AIP.AIPmodel#getProtocols()
	 * @see #getAIPmodel()
	 * @generated
	 */
	EReference getAIPmodel_Protocols();

	/**
	 * Returns the meta object for class '{@link AIP.Protocol <em>Protocol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Protocol</em>'.
	 * @see AIP.Protocol
	 * @generated
	 */
	EClass getProtocol();

	/**
	 * Returns the meta object for the reference list '{@link AIP.Protocol#getParticipants <em>Participants</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Participants</em>'.
	 * @see AIP.Protocol#getParticipants()
	 * @see #getProtocol()
	 * @generated
	 */
	EReference getProtocol_Participants();

	/**
	 * Returns the meta object for the attribute '{@link AIP.Protocol#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see AIP.Protocol#getName()
	 * @see #getProtocol()
	 * @generated
	 */
	EAttribute getProtocol_Name();

	/**
	 * Returns the meta object for class '{@link AIP.Participant <em>Participant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Participant</em>'.
	 * @see AIP.Participant
	 * @generated
	 */
	EClass getParticipant();

	/**
	 * Returns the meta object for the attribute '{@link AIP.Participant#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see AIP.Participant#getName()
	 * @see #getParticipant()
	 * @generated
	 */
	EAttribute getParticipant_Name();

	/**
	 * Returns the meta object for the attribute '{@link AIP.Participant#getEngaging_rules <em>Engaging rules</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Engaging rules</em>'.
	 * @see AIP.Participant#getEngaging_rules()
	 * @see #getParticipant()
	 * @generated
	 */
	EAttribute getParticipant_Engaging_rules();

	/**
	 * Returns the meta object for the attribute '{@link AIP.Participant#getOutcomes <em>Outcomes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Outcomes</em>'.
	 * @see AIP.Participant#getOutcomes()
	 * @see #getParticipant()
	 * @generated
	 */
	EAttribute getParticipant_Outcomes();

	/**
	 * Returns the meta object for the attribute '{@link AIP.Participant#getLiveness <em>Liveness</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Liveness</em>'.
	 * @see AIP.Participant#getLiveness()
	 * @see #getParticipant()
	 * @generated
	 */
	EAttribute getParticipant_Liveness();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	AIPFactory getAIPFactory();

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
		 * The meta object literal for the '{@link AIP.impl.AIPmodelImpl <em>AI Pmodel</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see AIP.impl.AIPmodelImpl
		 * @see AIP.impl.AIPPackageImpl#getAIPmodel()
		 * @generated
		 */
		EClass AI_PMODEL = eINSTANCE.getAIPmodel();

		/**
		 * The meta object literal for the '<em><b>Participants</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AI_PMODEL__PARTICIPANTS = eINSTANCE.getAIPmodel_Participants();

		/**
		 * The meta object literal for the '<em><b>Protocols</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AI_PMODEL__PROTOCOLS = eINSTANCE.getAIPmodel_Protocols();

		/**
		 * The meta object literal for the '{@link AIP.impl.ProtocolImpl <em>Protocol</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see AIP.impl.ProtocolImpl
		 * @see AIP.impl.AIPPackageImpl#getProtocol()
		 * @generated
		 */
		EClass PROTOCOL = eINSTANCE.getProtocol();

		/**
		 * The meta object literal for the '<em><b>Participants</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROTOCOL__PARTICIPANTS = eINSTANCE.getProtocol_Participants();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROTOCOL__NAME = eINSTANCE.getProtocol_Name();

		/**
		 * The meta object literal for the '{@link AIP.impl.ParticipantImpl <em>Participant</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see AIP.impl.ParticipantImpl
		 * @see AIP.impl.AIPPackageImpl#getParticipant()
		 * @generated
		 */
		EClass PARTICIPANT = eINSTANCE.getParticipant();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARTICIPANT__NAME = eINSTANCE.getParticipant_Name();

		/**
		 * The meta object literal for the '<em><b>Engaging rules</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARTICIPANT__ENGAGING_RULES = eINSTANCE.getParticipant_Engaging_rules();

		/**
		 * The meta object literal for the '<em><b>Outcomes</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARTICIPANT__OUTCOMES = eINSTANCE.getParticipant_Outcomes();

		/**
		 * The meta object literal for the '<em><b>Liveness</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARTICIPANT__LIVENESS = eINSTANCE.getParticipant_Liveness();

	}

} //AIPPackage
