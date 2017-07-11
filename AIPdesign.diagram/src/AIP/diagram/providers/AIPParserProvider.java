package AIP.diagram.providers;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.GetParserOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParserProvider;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserService;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.ui.services.parser.ParserHintAdapter;
import org.eclipse.gmf.runtime.notation.View;

import AIP.AIPPackage;
import AIP.diagram.edit.parts.ParticipantEngaging_rulesEditPart;
import AIP.diagram.edit.parts.ParticipantLivenessEditPart;
import AIP.diagram.edit.parts.ParticipantNameEditPart;
import AIP.diagram.edit.parts.ParticipantOutcomesEditPart;
import AIP.diagram.edit.parts.ProtocolNameEditPart;
import AIP.diagram.parsers.MessageFormatParser;
import AIP.diagram.part.AIPVisualIDRegistry;

/**
 * @generated
 */
public class AIPParserProvider extends AbstractProvider implements IParserProvider {

	/**
	* @generated
	*/
	private IParser protocolName_5001Parser;

	/**
	* @generated
	*/
	private IParser getProtocolName_5001Parser() {
		if (protocolName_5001Parser == null) {
			EAttribute[] features = new EAttribute[] { AIPPackage.eINSTANCE.getProtocol_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			protocolName_5001Parser = parser;
		}
		return protocolName_5001Parser;
	}

	/**
	* @generated
	*/
	private IParser participantName_5002Parser;

	/**
	* @generated
	*/
	private IParser getParticipantName_5002Parser() {
		if (participantName_5002Parser == null) {
			EAttribute[] features = new EAttribute[] { AIPPackage.eINSTANCE.getParticipant_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			participantName_5002Parser = parser;
		}
		return participantName_5002Parser;
	}

	/**
	* @generated
	*/
	private IParser participantLiveness_5003Parser;

	/**
	* @generated
	*/
	private IParser getParticipantLiveness_5003Parser() {
		if (participantLiveness_5003Parser == null) {
			EAttribute[] features = new EAttribute[] { AIPPackage.eINSTANCE.getParticipant_Liveness() };
			MessageFormatParser parser = new MessageFormatParser(features);
			participantLiveness_5003Parser = parser;
		}
		return participantLiveness_5003Parser;
	}

	/**
	* @generated
	*/
	private IParser participantEngaging_rules_5004Parser;

	/**
	* @generated
	*/
	private IParser getParticipantEngaging_rules_5004Parser() {
		if (participantEngaging_rules_5004Parser == null) {
			EAttribute[] features = new EAttribute[] { AIPPackage.eINSTANCE.getParticipant_Engaging_rules() };
			MessageFormatParser parser = new MessageFormatParser(features);
			participantEngaging_rules_5004Parser = parser;
		}
		return participantEngaging_rules_5004Parser;
	}

	/**
	* @generated
	*/
	private IParser participantOutcomes_5005Parser;

	/**
	* @generated
	*/
	private IParser getParticipantOutcomes_5005Parser() {
		if (participantOutcomes_5005Parser == null) {
			EAttribute[] features = new EAttribute[] { AIPPackage.eINSTANCE.getParticipant_Outcomes() };
			MessageFormatParser parser = new MessageFormatParser(features);
			participantOutcomes_5005Parser = parser;
		}
		return participantOutcomes_5005Parser;
	}

	/**
	* @generated
	*/
	protected IParser getParser(int visualID) {
		switch (visualID) {
		case ProtocolNameEditPart.VISUAL_ID:
			return getProtocolName_5001Parser();
		case ParticipantNameEditPart.VISUAL_ID:
			return getParticipantName_5002Parser();
		case ParticipantLivenessEditPart.VISUAL_ID:
			return getParticipantLiveness_5003Parser();
		case ParticipantEngaging_rulesEditPart.VISUAL_ID:
			return getParticipantEngaging_rules_5004Parser();
		case ParticipantOutcomesEditPart.VISUAL_ID:
			return getParticipantOutcomes_5005Parser();
		}
		return null;
	}

	/**
	* Utility method that consults ParserService
	* @generated
	*/
	public static IParser getParser(IElementType type, EObject object, String parserHint) {
		return ParserService.getInstance().getParser(new HintAdapter(type, object, parserHint));
	}

	/**
	* @generated
	*/
	public IParser getParser(IAdaptable hint) {
		String vid = (String) hint.getAdapter(String.class);
		if (vid != null) {
			return getParser(AIPVisualIDRegistry.getVisualID(vid));
		}
		View view = (View) hint.getAdapter(View.class);
		if (view != null) {
			return getParser(AIPVisualIDRegistry.getVisualID(view));
		}
		return null;
	}

	/**
	* @generated
	*/
	public boolean provides(IOperation operation) {
		if (operation instanceof GetParserOperation) {
			IAdaptable hint = ((GetParserOperation) operation).getHint();
			if (AIPElementTypes.getElement(hint) == null) {
				return false;
			}
			return getParser(hint) != null;
		}
		return false;
	}

	/**
	* @generated
	*/
	private static class HintAdapter extends ParserHintAdapter {

		/**
		* @generated
		*/
		private final IElementType elementType;

		/**
		* @generated
		*/
		public HintAdapter(IElementType type, EObject object, String parserHint) {
			super(object, parserHint);
			assert type != null;
			elementType = type;
		}

		/**
		* @generated
		*/
		public Object getAdapter(Class adapter) {
			if (IElementType.class.equals(adapter)) {
				return elementType;
			}
			return super.getAdapter(adapter);
		}
	}

}
