package IAC.diagram.providers;

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

import IAC.IACPackage;
import IAC.diagram.edit.parts.NodeNameEditPart;
import IAC.diagram.edit.parts.NodeTypeEditPart;
import IAC.diagram.edit.parts.TransitionTEEditPart;
import IAC.diagram.edit.parts.VariableNameEditPart;
import IAC.diagram.edit.parts.VariableTypeEditPart;
import IAC.diagram.parsers.MessageFormatParser;
import IAC.diagram.part.IACVisualIDRegistry;

/**
 * @generated
 */
public class IACParserProvider extends AbstractProvider implements
		IParserProvider {

	/**
	 * @generated
	 */
	private IParser variableName_5013Parser;

	/**
	 * @generated
	 */
	private IParser getVariableName_5013Parser() {
		if (variableName_5013Parser == null) {
			EAttribute[] features = new EAttribute[] { IACPackage.eINSTANCE
					.getVariable_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			variableName_5013Parser = parser;
		}
		return variableName_5013Parser;
	}

	/**
	 * @generated
	 */
	private IParser variableType_5014Parser;

	/**
	 * @generated
	 */
	private IParser getVariableType_5014Parser() {
		if (variableType_5014Parser == null) {
			EAttribute[] features = new EAttribute[] { IACPackage.eINSTANCE
					.getVariable_Type() };
			MessageFormatParser parser = new MessageFormatParser(features);
			variableType_5014Parser = parser;
		}
		return variableType_5014Parser;
	}

	/**
	 * @generated
	 */
	private IParser nodeName_5015Parser;

	/**
	 * @generated
	 */
	private IParser getNodeName_5015Parser() {
		if (nodeName_5015Parser == null) {
			EAttribute[] features = new EAttribute[] { IACPackage.eINSTANCE
					.getNode_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			nodeName_5015Parser = parser;
		}
		return nodeName_5015Parser;
	}

	/**
	 * @generated
	 */
	private IParser nodeType_5016Parser;

	/**
	 * @generated
	 */
	private IParser getNodeType_5016Parser() {
		if (nodeType_5016Parser == null) {
			EAttribute[] features = new EAttribute[] { IACPackage.eINSTANCE
					.getNode_Type() };
			MessageFormatParser parser = new MessageFormatParser(features);
			nodeType_5016Parser = parser;
		}
		return nodeType_5016Parser;
	}

	/**
	 * @generated
	 */
	private IParser transitionTE_6005Parser;

	/**
	 * @generated
	 */
	private IParser getTransitionTE_6005Parser() {
		if (transitionTE_6005Parser == null) {
			EAttribute[] features = new EAttribute[] { IACPackage.eINSTANCE
					.getTransition_TE() };
			MessageFormatParser parser = new MessageFormatParser(features);
			transitionTE_6005Parser = parser;
		}
		return transitionTE_6005Parser;
	}

	/**
	 * @generated
	 */
	protected IParser getParser(int visualID) {
		switch (visualID) {
		case VariableNameEditPart.VISUAL_ID:
			return getVariableName_5013Parser();
		case VariableTypeEditPart.VISUAL_ID:
			return getVariableType_5014Parser();
		case NodeNameEditPart.VISUAL_ID:
			return getNodeName_5015Parser();
		case NodeTypeEditPart.VISUAL_ID:
			return getNodeType_5016Parser();
		case TransitionTEEditPart.VISUAL_ID:
			return getTransitionTE_6005Parser();
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
			return getParser(IACVisualIDRegistry.getVisualID(vid));
		}
		View view = (View) hint.getAdapter(View.class);
		if (view != null) {
			return getParser(IACVisualIDRegistry.getVisualID(view));
		}
		return null;
	}

	/**
	 * @generated
	 */
	public boolean provides(IOperation operation) {
		if (operation instanceof GetParserOperation) {
			IAdaptable hint = ((GetParserOperation) operation).getHint();
			if (IACElementTypes.getElement(hint) == null) {
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
