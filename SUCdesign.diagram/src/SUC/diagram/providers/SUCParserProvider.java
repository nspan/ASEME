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

import SUC.SUCPackage;
import SUC.diagram.edit.parts.HumanRoleNameEditPart;
import SUC.diagram.edit.parts.RoleNameEditPart;
import SUC.diagram.edit.parts.SystemRoleNameEditPart;
import SUC.diagram.edit.parts.UseCaseNameEditPart;
import SUC.diagram.edit.parts.UseCaseSpecified_byEditPart;
import SUC.diagram.parsers.MessageFormatParser;
import SUC.diagram.part.SUCVisualIDRegistry;

/**
 * @generated
 */
public class SUCParserProvider extends AbstractProvider implements
		IParserProvider {

	/**
	 * @generated
	 */
	private IParser systemRoleName_5009Parser;

	/**
	 * @generated
	 */
	private IParser getSystemRoleName_5009Parser() {
		if (systemRoleName_5009Parser == null) {
			EAttribute[] features = new EAttribute[] { SUCPackage.eINSTANCE
					.getRole_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			systemRoleName_5009Parser = parser;
		}
		return systemRoleName_5009Parser;
	}

	/**
	 * @generated
	 */
	private IParser humanRoleName_5010Parser;

	/**
	 * @generated
	 */
	private IParser getHumanRoleName_5010Parser() {
		if (humanRoleName_5010Parser == null) {
			EAttribute[] features = new EAttribute[] { SUCPackage.eINSTANCE
					.getRole_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			humanRoleName_5010Parser = parser;
		}
		return humanRoleName_5010Parser;
	}

	/**
	 * @generated
	 */
	private IParser useCaseName_5011Parser;

	/**
	 * @generated
	 */
	private IParser getUseCaseName_5011Parser() {
		if (useCaseName_5011Parser == null) {
			EAttribute[] features = new EAttribute[] { SUCPackage.eINSTANCE
					.getUseCase_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			useCaseName_5011Parser = parser;
		}
		return useCaseName_5011Parser;
	}

	/**
	 * @generated
	 */
	private IParser useCaseSpecified_by_5012Parser;

	/**
	 * @generated
	 */
	private IParser getUseCaseSpecified_by_5012Parser() {
		if (useCaseSpecified_by_5012Parser == null) {
			EAttribute[] features = new EAttribute[] { SUCPackage.eINSTANCE
					.getUseCase_Specified_by() };
			MessageFormatParser parser = new MessageFormatParser(features);
			useCaseSpecified_by_5012Parser = parser;
		}
		return useCaseSpecified_by_5012Parser;
	}

	/**
	 * @generated
	 */
	private IParser roleName_5013Parser;

	/**
	 * @generated
	 */
	private IParser getRoleName_5013Parser() {
		if (roleName_5013Parser == null) {
			EAttribute[] features = new EAttribute[] { SUCPackage.eINSTANCE
					.getRole_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			roleName_5013Parser = parser;
		}
		return roleName_5013Parser;
	}

	/**
	 * @generated
	 */
	protected IParser getParser(int visualID) {
		switch (visualID) {
		case SystemRoleNameEditPart.VISUAL_ID:
			return getSystemRoleName_5009Parser();
		case HumanRoleNameEditPart.VISUAL_ID:
			return getHumanRoleName_5010Parser();
		case UseCaseNameEditPart.VISUAL_ID:
			return getUseCaseName_5011Parser();
		case UseCaseSpecified_byEditPart.VISUAL_ID:
			return getUseCaseSpecified_by_5012Parser();
		case RoleNameEditPart.VISUAL_ID:
			return getRoleName_5013Parser();
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
