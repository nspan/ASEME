package SAG.diagram.providers;

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

import SAG.SAGPackage;
import SAG.diagram.edit.parts.ActorNameEditPart;
import SAG.diagram.edit.parts.GoalNameEditPart;
import SAG.diagram.parsers.MessageFormatParser;
import SAG.diagram.part.SAGVisualIDRegistry;

/**
 * @generated
 */
public class SAGParserProvider extends AbstractProvider implements IParserProvider {

	/**
	* @generated
	*/
	private IParser actorName_5001Parser;

	/**
	* @generated
	*/
	private IParser getActorName_5001Parser() {
		if (actorName_5001Parser == null) {
			EAttribute[] features = new EAttribute[] { SAGPackage.eINSTANCE.getActor_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			actorName_5001Parser = parser;
		}
		return actorName_5001Parser;
	}

	/**
	* @generated
	*/
	private IParser goalName_5002Parser;

	/**
	* @generated
	*/
	private IParser getGoalName_5002Parser() {
		if (goalName_5002Parser == null) {
			EAttribute[] features = new EAttribute[] { SAGPackage.eINSTANCE.getGoal_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			goalName_5002Parser = parser;
		}
		return goalName_5002Parser;
	}

	/**
	* @generated
	*/
	protected IParser getParser(int visualID) {
		switch (visualID) {
		case ActorNameEditPart.VISUAL_ID:
			return getActorName_5001Parser();
		case GoalNameEditPart.VISUAL_ID:
			return getGoalName_5002Parser();
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
			return getParser(SAGVisualIDRegistry.getVisualID(vid));
		}
		View view = (View) hint.getAdapter(View.class);
		if (view != null) {
			return getParser(SAGVisualIDRegistry.getVisualID(view));
		}
		return null;
	}

	/**
	* @generated
	*/
	public boolean provides(IOperation operation) {
		if (operation instanceof GetParserOperation) {
			IAdaptable hint = ((GetParserOperation) operation).getHint();
			if (SAGElementTypes.getElement(hint) == null) {
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
