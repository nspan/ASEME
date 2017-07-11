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
import SRM.diagram.edit.parts.ActivityNameEditPart;
import SRM.diagram.edit.parts.CapabilityNameEditPart;
import SRM.diagram.edit.parts.FunctionalityDescriptionEditPart;
import SRM.diagram.edit.parts.RoleNameEditPart;
import SRM.diagram.parsers.MessageFormatParser;
import SRM.diagram.part.SRMVisualIDRegistry;

/**
 * @generated
 */
public class SRMParserProvider extends AbstractProvider implements IParserProvider {

	/**
	* @generated
	*/
	private IParser functionalityDescription_5001Parser;

	/**
	* @generated
	*/
	private IParser getFunctionalityDescription_5001Parser() {
		if (functionalityDescription_5001Parser == null) {
			EAttribute[] features = new EAttribute[] { SRMPackage.eINSTANCE.getFunctionality_Description() };
			MessageFormatParser parser = new MessageFormatParser(features);
			functionalityDescription_5001Parser = parser;
		}
		return functionalityDescription_5001Parser;
	}

	/**
	* @generated
	*/
	private IParser capabilityName_5002Parser;

	/**
	* @generated
	*/
	private IParser getCapabilityName_5002Parser() {
		if (capabilityName_5002Parser == null) {
			EAttribute[] features = new EAttribute[] { SRMPackage.eINSTANCE.getCapability_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			capabilityName_5002Parser = parser;
		}
		return capabilityName_5002Parser;
	}

	/**
	* @generated
	*/
	private IParser roleName_5003Parser;

	/**
	* @generated
	*/
	private IParser getRoleName_5003Parser() {
		if (roleName_5003Parser == null) {
			EAttribute[] features = new EAttribute[] { SRMPackage.eINSTANCE.getRole_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			roleName_5003Parser = parser;
		}
		return roleName_5003Parser;
	}

	/**
	* @generated
	*/
	private IParser activityName_5004Parser;

	/**
	* @generated
	*/
	private IParser getActivityName_5004Parser() {
		if (activityName_5004Parser == null) {
			EAttribute[] features = new EAttribute[] { SRMPackage.eINSTANCE.getActivity_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			activityName_5004Parser = parser;
		}
		return activityName_5004Parser;
	}

	/**
	* @generated
	*/
	protected IParser getParser(int visualID) {
		switch (visualID) {
		case FunctionalityDescriptionEditPart.VISUAL_ID:
			return getFunctionalityDescription_5001Parser();
		case CapabilityNameEditPart.VISUAL_ID:
			return getCapabilityName_5002Parser();
		case RoleNameEditPart.VISUAL_ID:
			return getRoleName_5003Parser();
		case ActivityNameEditPart.VISUAL_ID:
			return getActivityName_5004Parser();
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
