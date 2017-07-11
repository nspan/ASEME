package SUC.diagram.providers;

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
import org.eclipse.gmf.tooling.runtime.parsers.EnumParser;

import SUC.SUCPackage;
import SUC.diagram.edit.parts.RoleNameEditPart;
import SUC.diagram.edit.parts.RoleTypeEditPart;
import SUC.diagram.edit.parts.UseCaseNameEditPart;
import SUC.diagram.parsers.MessageFormatParser;
import SUC.diagram.part.SUCVisualIDRegistry;

/**
 * @generated
 */
public class SUCParserProvider extends AbstractProvider implements IParserProvider {

	/**
	* @generated
	*/
	private IParser useCaseName_5003Parser;

	/**
	* @generated
	*/
	private IParser getUseCaseName_5003Parser() {
		if (useCaseName_5003Parser == null) {
			EAttribute[] features = new EAttribute[] { SUCPackage.eINSTANCE.getUseCase_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			useCaseName_5003Parser = parser;
		}
		return useCaseName_5003Parser;
	}

	/**
	* @generated
	*/
	private IParser roleName_5001Parser;

	/**
	* @generated
	*/
	private IParser getRoleName_5001Parser() {
		if (roleName_5001Parser == null) {
			EAttribute[] features = new EAttribute[] { SUCPackage.eINSTANCE.getRole_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			roleName_5001Parser = parser;
		}
		return roleName_5001Parser;
	}

	/**
	* @generated
	*/
	private IParser roleType_5004Parser;

	/**
	* @generated
	*/
	private IParser getRoleType_5004Parser() {
		if (roleType_5004Parser == null) {
			EAttribute editableFeature = SUCPackage.eINSTANCE.getRole_Type();
			EnumParser parser = new EnumParser(editableFeature);
			roleType_5004Parser = parser;
		}
		return roleType_5004Parser;
	}

	/**
	* @generated
	*/
	protected IParser getParser(int visualID) {
		switch (visualID) {
		case UseCaseNameEditPart.VISUAL_ID:
			return getUseCaseName_5003Parser();
		case RoleNameEditPart.VISUAL_ID:
			return getRoleName_5001Parser();

		case RoleTypeEditPart.VISUAL_ID:
			return getRoleType_5004Parser();
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
			return getParser(SUCVisualIDRegistry.getVisualID(vid));
		}
		View view = (View) hint.getAdapter(View.class);
		if (view != null) {
			return getParser(SUCVisualIDRegistry.getVisualID(view));
		}
		return null;
	}

	/**
	* @generated
	*/
	public boolean provides(IOperation operation) {
		if (operation instanceof GetParserOperation) {
			IAdaptable hint = ((GetParserOperation) operation).getHint();
			if (SUCElementTypes.getElement(hint) == null) {
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
