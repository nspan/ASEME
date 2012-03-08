package SRM.diagram.providers;

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

import SRM.SRMPackage;
import SRM.diagram.edit.parts.ActivityFunctionalityEditPart;
import SRM.diagram.edit.parts.ActivityNameEditPart;
import SRM.diagram.edit.parts.CapabilityNameEditPart;
import SRM.diagram.edit.parts.RoleLivenessEditPart;
import SRM.diagram.edit.parts.RoleNameEditPart;
import SRM.diagram.parsers.MessageFormatParser;
import SRM.diagram.part.SRMVisualIDRegistry;

/**
 * @generated
 */
public class SRMParserProvider extends AbstractProvider implements
		IParserProvider {

	/**
	 * @generated
	 */
	private IParser capabilityName_5006Parser;

	/**
	 * @generated
	 */
	private IParser getCapabilityName_5006Parser() {
		if (capabilityName_5006Parser == null) {
			EAttribute[] features = new EAttribute[] { SRMPackage.eINSTANCE
					.getCapability_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			capabilityName_5006Parser = parser;
		}
		return capabilityName_5006Parser;
	}

	/**
	 * @generated
	 */
	private IParser roleLiveness_5007Parser;

	/**
	 * @generated
	 */
	private IParser getRoleLiveness_5007Parser() {
		if (roleLiveness_5007Parser == null) {
			EAttribute[] features = new EAttribute[] { SRMPackage.eINSTANCE
					.getRole_Liveness() };
			MessageFormatParser parser = new MessageFormatParser(features);
			roleLiveness_5007Parser = parser;
		}
		return roleLiveness_5007Parser;
	}

	/**
	 * @generated
	 */
	private IParser roleName_5008Parser;

	/**
	 * @generated
	 */
	private IParser getRoleName_5008Parser() {
		if (roleName_5008Parser == null) {
			EAttribute[] features = new EAttribute[] { SRMPackage.eINSTANCE
					.getRole_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			roleName_5008Parser = parser;
		}
		return roleName_5008Parser;
	}

	/**
	 * @generated
	 */
	private IParser activityName_5009Parser;

	/**
	 * @generated
	 */
	private IParser getActivityName_5009Parser() {
		if (activityName_5009Parser == null) {
			EAttribute[] features = new EAttribute[] { SRMPackage.eINSTANCE
					.getActivity_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			activityName_5009Parser = parser;
		}
		return activityName_5009Parser;
	}

	/**
	 * @generated
	 */
	private IParser activityFunctionality_5010Parser;

	/**
	 * @generated
	 */
	private IParser getActivityFunctionality_5010Parser() {
		if (activityFunctionality_5010Parser == null) {
			EAttribute[] features = new EAttribute[] { SRMPackage.eINSTANCE
					.getActivity_Functionality() };
			MessageFormatParser parser = new MessageFormatParser(features);
			activityFunctionality_5010Parser = parser;
		}
		return activityFunctionality_5010Parser;
	}

	/**
	 * @generated
	 */
	protected IParser getParser(int visualID) {
		switch (visualID) {
		case CapabilityNameEditPart.VISUAL_ID:
			return getCapabilityName_5006Parser();
		case RoleLivenessEditPart.VISUAL_ID:
			return getRoleLiveness_5007Parser();
		case RoleNameEditPart.VISUAL_ID:
			return getRoleName_5008Parser();
		case ActivityNameEditPart.VISUAL_ID:
			return getActivityName_5009Parser();
		case ActivityFunctionalityEditPart.VISUAL_ID:
			return getActivityFunctionality_5010Parser();
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
			return getParser(SRMVisualIDRegistry.getVisualID(vid));
		}
		View view = (View) hint.getAdapter(View.class);
		if (view != null) {
			return getParser(SRMVisualIDRegistry.getVisualID(view));
		}
		return null;
	}

	/**
	 * @generated
	 */
	public boolean provides(IOperation operation) {
		if (operation instanceof GetParserOperation) {
			IAdaptable hint = ((GetParserOperation) operation).getHint();
			if (SRMElementTypes.getElement(hint) == null) {
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
