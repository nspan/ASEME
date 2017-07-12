package AIP.diagram.part;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.tooling.runtime.update.DiagramUpdater;

import AIP.AIPPackage;
import AIP.AIPmodel;
import AIP.Participant;
import AIP.Protocol;
import AIP.diagram.edit.parts.AIPmodelEditPart;
import AIP.diagram.edit.parts.ParticipantEditPart;
import AIP.diagram.edit.parts.ProtocolEditPart;
import AIP.diagram.edit.parts.ProtocolParticipantsEditPart;
import AIP.diagram.providers.AIPElementTypes;

/**
 * @generated
 */
public class AIPDiagramUpdater {

	/**
	* @generated
	*/
	public static List<AIPNodeDescriptor> getSemanticChildren(View view) {
		switch (AIPVisualIDRegistry.getVisualID(view)) {
		case AIPmodelEditPart.VISUAL_ID:
			return getAIPmodel_1000SemanticChildren(view);
		}
		return Collections.emptyList();
	}

	/**
	* @generated
	*/
	public static List<AIPNodeDescriptor> getAIPmodel_1000SemanticChildren(View view) {
		if (!view.isSetElement()) {
			return Collections.emptyList();
		}
		AIPmodel modelElement = (AIPmodel) view.getElement();
		LinkedList<AIPNodeDescriptor> result = new LinkedList<AIPNodeDescriptor>();
		for (Iterator<?> it = modelElement.getProtocols().iterator(); it.hasNext();) {
			Protocol childElement = (Protocol) it.next();
			int visualID = AIPVisualIDRegistry.getNodeVisualID(view, childElement);
			if (visualID == ProtocolEditPart.VISUAL_ID) {
				result.add(new AIPNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator<?> it = modelElement.getParticipants().iterator(); it.hasNext();) {
			Participant childElement = (Participant) it.next();
			int visualID = AIPVisualIDRegistry.getNodeVisualID(view, childElement);
			if (visualID == ParticipantEditPart.VISUAL_ID) {
				result.add(new AIPNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	* @generated
	*/
	public static List<AIPLinkDescriptor> getContainedLinks(View view) {
		switch (AIPVisualIDRegistry.getVisualID(view)) {
		case AIPmodelEditPart.VISUAL_ID:
			return getAIPmodel_1000ContainedLinks(view);
		case ProtocolEditPart.VISUAL_ID:
			return getProtocol_2001ContainedLinks(view);
		case ParticipantEditPart.VISUAL_ID:
			return getParticipant_2002ContainedLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	* @generated
	*/
	public static List<AIPLinkDescriptor> getIncomingLinks(View view) {
		switch (AIPVisualIDRegistry.getVisualID(view)) {
		case ProtocolEditPart.VISUAL_ID:
			return getProtocol_2001IncomingLinks(view);
		case ParticipantEditPart.VISUAL_ID:
			return getParticipant_2002IncomingLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	* @generated
	*/
	public static List<AIPLinkDescriptor> getOutgoingLinks(View view) {
		switch (AIPVisualIDRegistry.getVisualID(view)) {
		case ProtocolEditPart.VISUAL_ID:
			return getProtocol_2001OutgoingLinks(view);
		case ParticipantEditPart.VISUAL_ID:
			return getParticipant_2002OutgoingLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<AIPLinkDescriptor> getAIPmodel_1000ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<AIPLinkDescriptor> getProtocol_2001ContainedLinks(View view) {
		Protocol modelElement = (Protocol) view.getElement();
		LinkedList<AIPLinkDescriptor> result = new LinkedList<AIPLinkDescriptor>();
		result.addAll(getOutgoingFeatureModelFacetLinks_Protocol_Participants_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<AIPLinkDescriptor> getParticipant_2002ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<AIPLinkDescriptor> getProtocol_2001IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<AIPLinkDescriptor> getParticipant_2002IncomingLinks(View view) {
		Participant modelElement = (Participant) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<AIPLinkDescriptor> result = new LinkedList<AIPLinkDescriptor>();
		result.addAll(getIncomingFeatureModelFacetLinks_Protocol_Participants_4001(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<AIPLinkDescriptor> getProtocol_2001OutgoingLinks(View view) {
		Protocol modelElement = (Protocol) view.getElement();
		LinkedList<AIPLinkDescriptor> result = new LinkedList<AIPLinkDescriptor>();
		result.addAll(getOutgoingFeatureModelFacetLinks_Protocol_Participants_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<AIPLinkDescriptor> getParticipant_2002OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	private static Collection<AIPLinkDescriptor> getIncomingFeatureModelFacetLinks_Protocol_Participants_4001(
			Participant target, Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<AIPLinkDescriptor> result = new LinkedList<AIPLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() == AIPPackage.eINSTANCE.getProtocol_Participants()) {
				result.add(new AIPLinkDescriptor(setting.getEObject(), target,
						AIPElementTypes.ProtocolParticipants_4001, ProtocolParticipantsEditPart.VISUAL_ID));
			}
		}
		return result;
	}

	/**
	* @generated
	*/
	private static Collection<AIPLinkDescriptor> getOutgoingFeatureModelFacetLinks_Protocol_Participants_4001(
			Protocol source) {
		LinkedList<AIPLinkDescriptor> result = new LinkedList<AIPLinkDescriptor>();
		for (Iterator<?> destinations = source.getParticipants().iterator(); destinations.hasNext();) {
			Participant destination = (Participant) destinations.next();
			result.add(new AIPLinkDescriptor(source, destination, AIPElementTypes.ProtocolParticipants_4001,
					ProtocolParticipantsEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	* @generated
	*/
	public static final DiagramUpdater TYPED_INSTANCE = new DiagramUpdater() {
		/**
		* @generated
		*/
		@Override

		public List<AIPNodeDescriptor> getSemanticChildren(View view) {
			return AIPDiagramUpdater.getSemanticChildren(view);
		}

		/**
		* @generated
		*/
		@Override

		public List<AIPLinkDescriptor> getContainedLinks(View view) {
			return AIPDiagramUpdater.getContainedLinks(view);
		}

		/**
		* @generated
		*/
		@Override

		public List<AIPLinkDescriptor> getIncomingLinks(View view) {
			return AIPDiagramUpdater.getIncomingLinks(view);
		}

		/**
		* @generated
		*/
		@Override

		public List<AIPLinkDescriptor> getOutgoingLinks(View view) {
			return AIPDiagramUpdater.getOutgoingLinks(view);
		}
	};

}
