package SUC.diagram.navigator;

import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserOptions;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.ITreePathLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.ViewerLabel;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonLabelProvider;

import SUC.diagram.edit.parts.HumanRoleEditPart;
import SUC.diagram.edit.parts.HumanRoleNameEditPart;
import SUC.diagram.edit.parts.RoleEditPart;
import SUC.diagram.edit.parts.RoleNameEditPart;
import SUC.diagram.edit.parts.RoleParticipates_inEditPart;
import SUC.diagram.edit.parts.SUCmodelEditPart;
import SUC.diagram.edit.parts.SystemRoleEditPart;
import SUC.diagram.edit.parts.SystemRoleNameEditPart;
import SUC.diagram.edit.parts.UseCaseEditPart;
import SUC.diagram.edit.parts.UseCaseIncludeEditPart;
import SUC.diagram.edit.parts.UseCaseNameEditPart;
import SUC.diagram.edit.parts.WrappingLabelEditPart;
import SUC.diagram.part.SUCDiagramEditorPlugin;
import SUC.diagram.part.SUCVisualIDRegistry;
import SUC.diagram.providers.SUCElementTypes;
import SUC.diagram.providers.SUCParserProvider;

/**
 * @generated
 */
public class SUCNavigatorLabelProvider extends LabelProvider implements
		ICommonLabelProvider, ITreePathLabelProvider {

	/**
	 * @generated
	 */
	static {
		SUCDiagramEditorPlugin
				.getInstance()
				.getImageRegistry()
				.put("Navigator?UnknownElement", ImageDescriptor.getMissingImageDescriptor()); //$NON-NLS-1$
		SUCDiagramEditorPlugin
				.getInstance()
				.getImageRegistry()
				.put("Navigator?ImageNotFound", ImageDescriptor.getMissingImageDescriptor()); //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	public void updateLabel(ViewerLabel label, TreePath elementPath) {
		Object element = elementPath.getLastSegment();
		if (element instanceof SUCNavigatorItem
				&& !isOwnView(((SUCNavigatorItem) element).getView())) {
			return;
		}
		label.setText(getText(element));
		label.setImage(getImage(element));
	}

	/**
	 * @generated
	 */
	public Image getImage(Object element) {
		if (element instanceof SUCNavigatorGroup) {
			SUCNavigatorGroup group = (SUCNavigatorGroup) element;
			return SUCDiagramEditorPlugin.getInstance().getBundledImage(
					group.getIcon());
		}

		if (element instanceof SUCNavigatorItem) {
			SUCNavigatorItem navigatorItem = (SUCNavigatorItem) element;
			if (!isOwnView(navigatorItem.getView())) {
				return super.getImage(element);
			}
			return getImage(navigatorItem.getView());
		}

		return super.getImage(element);
	}

	/**
	 * @generated
	 */
	public Image getImage(View view) {
		switch (SUCVisualIDRegistry.getVisualID(view)) {
		case RoleParticipates_inEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http://www.amcl.tuc.gr/aseme/metamodels/SUC?Role?participates_in", SUCElementTypes.RoleParticipates_in_4008); //$NON-NLS-1$
		case UseCaseIncludeEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http://www.amcl.tuc.gr/aseme/metamodels/SUC?UseCase?include", SUCElementTypes.UseCaseInclude_4009); //$NON-NLS-1$
		case HumanRoleEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http://www.amcl.tuc.gr/aseme/metamodels/SUC?HumanRole", SUCElementTypes.HumanRole_2008); //$NON-NLS-1$
		case RoleEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http://www.amcl.tuc.gr/aseme/metamodels/SUC?Role", SUCElementTypes.Role_2010); //$NON-NLS-1$
		case UseCaseEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http://www.amcl.tuc.gr/aseme/metamodels/SUC?UseCase", SUCElementTypes.UseCase_2009); //$NON-NLS-1$
		case SystemRoleEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http://www.amcl.tuc.gr/aseme/metamodels/SUC?SystemRole", SUCElementTypes.SystemRole_2007); //$NON-NLS-1$
		case SUCmodelEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Diagram?http://www.amcl.tuc.gr/aseme/metamodels/SUC?SUCmodel", SUCElementTypes.SUCmodel_1000); //$NON-NLS-1$
		}
		return getImage("Navigator?UnknownElement", null); //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	private Image getImage(String key, IElementType elementType) {
		ImageRegistry imageRegistry = SUCDiagramEditorPlugin.getInstance()
				.getImageRegistry();
		Image image = imageRegistry.get(key);
		if (image == null && elementType != null
				&& SUCElementTypes.isKnownElementType(elementType)) {
			image = SUCElementTypes.getImage(elementType);
			imageRegistry.put(key, image);
		}

		if (image == null) {
			image = imageRegistry.get("Navigator?ImageNotFound"); //$NON-NLS-1$
			imageRegistry.put(key, image);
		}
		return image;
	}

	/**
	 * @generated
	 */
	public String getText(Object element) {
		if (element instanceof SUCNavigatorGroup) {
			SUCNavigatorGroup group = (SUCNavigatorGroup) element;
			return group.getGroupName();
		}

		if (element instanceof SUCNavigatorItem) {
			SUCNavigatorItem navigatorItem = (SUCNavigatorItem) element;
			if (!isOwnView(navigatorItem.getView())) {
				return null;
			}
			return getText(navigatorItem.getView());
		}

		return super.getText(element);
	}

	/**
	 * @generated
	 */
	public String getText(View view) {
		if (view.getElement() != null && view.getElement().eIsProxy()) {
			return getUnresolvedDomainElementProxyText(view);
		}
		switch (SUCVisualIDRegistry.getVisualID(view)) {
		case RoleParticipates_inEditPart.VISUAL_ID:
			return getRoleParticipates_in_4008Text(view);
		case UseCaseIncludeEditPart.VISUAL_ID:
			return getUseCaseInclude_4009Text(view);
		case HumanRoleEditPart.VISUAL_ID:
			return getHumanRole_2008Text(view);
		case RoleEditPart.VISUAL_ID:
			return getRole_2010Text(view);
		case UseCaseEditPart.VISUAL_ID:
			return getUseCase_2009Text(view);
		case SystemRoleEditPart.VISUAL_ID:
			return getSystemRole_2007Text(view);
		case SUCmodelEditPart.VISUAL_ID:
			return getSUCmodel_1000Text(view);
		}
		return getUnknownElementText(view);
	}

	/**
	 * @generated
	 */
	private String getRole_2010Text(View view) {
		IParser parser = SUCParserProvider.getParser(SUCElementTypes.Role_2010,
				view.getElement() != null ? view.getElement() : view,
				SUCVisualIDRegistry.getType(RoleNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			SUCDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5013); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getSystemRole_2007Text(View view) {
		IParser parser = SUCParserProvider.getParser(
				SUCElementTypes.SystemRole_2007,
				view.getElement() != null ? view.getElement() : view,
				SUCVisualIDRegistry.getType(SystemRoleNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			SUCDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5009); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getSUCmodel_1000Text(View view) {
		return ""; //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	private String getRoleParticipates_in_4008Text(View view) {
		return ""; //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	private String getUseCase_2009Text(View view) {
		IParser parser = SUCParserProvider.getParser(
				SUCElementTypes.UseCase_2009,
				view.getElement() != null ? view.getElement() : view,
				SUCVisualIDRegistry.getType(UseCaseNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			SUCDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5011); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getHumanRole_2008Text(View view) {
		IParser parser = SUCParserProvider.getParser(
				SUCElementTypes.HumanRole_2008,
				view.getElement() != null ? view.getElement() : view,
				SUCVisualIDRegistry.getType(HumanRoleNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			SUCDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5010); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getUseCaseInclude_4009Text(View view) {
		IParser parser = SUCParserProvider.getParser(
				SUCElementTypes.UseCaseInclude_4009,
				view.getElement() != null ? view.getElement() : view,
				SUCVisualIDRegistry.getType(WrappingLabelEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			SUCDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 6001); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getUnknownElementText(View view) {
		return "<UnknownElement Visual_ID = " + view.getType() + ">"; //$NON-NLS-1$  //$NON-NLS-2$
	}

	/**
	 * @generated
	 */
	private String getUnresolvedDomainElementProxyText(View view) {
		return "<Unresolved domain element Visual_ID = " + view.getType() + ">"; //$NON-NLS-1$  //$NON-NLS-2$
	}

	/**
	 * @generated
	 */
	public void init(ICommonContentExtensionSite aConfig) {
	}

	/**
	 * @generated
	 */
	public void restoreState(IMemento aMemento) {
	}

	/**
	 * @generated
	 */
	public void saveState(IMemento aMemento) {
	}

	/**
	 * @generated
	 */
	public String getDescription(Object anElement) {
		return null;
	}

	/**
	 * @generated
	 */
	private boolean isOwnView(View view) {
		return SUCmodelEditPart.MODEL_ID.equals(SUCVisualIDRegistry
				.getModelID(view));
	}

}
