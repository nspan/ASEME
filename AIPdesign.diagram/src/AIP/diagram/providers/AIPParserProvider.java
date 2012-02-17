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
public class AIPParserProvider extends AbstractProvider implements
		IParserProvider {

	/**
	 * @generated
	 */
	private IParser participantName_5006Parser;

	/**
	 * @generated
	 */
	private IParser getParticipantName_5006Parser() {
		if (participantName_5006Parser == null) {
			EAttribute[] features = new EAttribute[] { AIPPackage.eINSTANCE
					.getParticipant_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			participantName_5006Parser = parser;
		}
		return participantName_5006Parser;
	}

	/**
	 * @generated
	 */
	private IParser participantEngaging_rules_5007Parser;

	/**
	 * @generated
	 */
	private IParser getParticipantEngaging_rules_5007Parser() {
		if (participantEngaging_rules_5007Parser == null) {
			EAttribute[] features = new EAttribute[] { AIPPackage.eINSTANCE
					.getParticipant_Engaging_rules() };
			MessageFormatParser parser = new MessageFormatParser(features);
			participantEngaging_rules_5007Parser = parser;
		}
		return participantEngaging_rules_5007Parser;
	}

	/**
	 * @generated
	 */
	private IParser participantOutcomes_5008Parser;

	/**
	 * @generated
	 */
	private IParser getParticipantOutcomes_5008Parser() {
		if (participantOutcomes_5008Parser == null) {
			EAttribute[] features = new EAttribute[] { AIPPackage.eINSTANCE
					.getParticipant_Outcomes() };
			MessageFormatParser parser = new MessageFormatParser(features);
			participantOutcomes_5008Parser = parser;
		}
		return participantOutcomes_5008Parser;
	}

	/**
	 * @generated
	 */
	private IParser participantLiveness_5009Parser;

	/**
	 * @generated
	 */
	private IParser getParticipantLiveness_5009Parser() {
		if (participantLiveness_5009Parser == null) {
			EAttribute[] features = new EAttribute[] { AIPPackage.eINSTANCE
					.getParticipant_Liveness() };
			MessageFormatParser parser = new MessageFormatParser(features);
			participantLiveness_5009Parser = parser;
		}
		return participantLiveness_5009Parser;
	}

	/**
	 * @generated
	 */
	private IParser protocolName_5010Parser;

	/**
	 * @generated
	 */
	private IParser getProtocolName_5010Parser() {
		if (protocolName_5010Parser == null) {
			EAttribute[] features = new EAttribute[] { AIPPackage.eINSTANCE
					.getProtocol_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			protocolName_5010Parser = parser;
		}
		return protocolName_5010Parser;
	}

	/**
	 * @generated
	 */
	protected IParser getParser(int visualID) {
		switch (visualID) {
		case ParticipantNameEditPart.VISUAL_ID:
			return getParticipantName_5006Parser();
		case ParticipantEngaging_rulesEditPart.VISUAL_ID:
			return getParticipantEngaging_rules_5007Parser();
		case ParticipantOutcomesEditPart.VISUAL_ID:
			return getParticipantOutcomes_5008Parser();
		case ParticipantLivenessEditPart.VISUAL_ID:
			return getParticipantLiveness_5009Parser();
		case ProtocolNameEditPart.VISUAL_ID:
			return getProtocolName_5010Parser();
		}
		return null;
	}

	/**
	 * Utility method that consults ParserService
	 * @generated
	 */
	public static IParser getParser(IElementType type, EObject object,
			String parserHint) {
		return ParserService.getInstance().getParser(
				new HintAdapter(type, object, parserHint));
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
