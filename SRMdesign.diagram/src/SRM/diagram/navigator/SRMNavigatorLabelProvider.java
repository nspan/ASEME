package SRM.diagram.navigator;

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

import SRM.diagram.edit.parts.ActivityEditPart;
import SRM.diagram.edit.parts.ActivityNameEditPart;
import SRM.diagram.edit.parts.CapabilityActivitiesEditPart;
import SRM.diagram.edit.parts.CapabilityEditPart;
import SRM.diagram.edit.parts.CapabilityNameEditPart;
import SRM.diagram.edit.parts.RoleActivitiesEditPart;
import SRM.diagram.edit.parts.RoleCapabilitiesEditPart;
import SRM.diagram.edit.parts.RoleEditPart;
import SRM.diagram.edit.parts.RoleLivenessEditPart;
import SRM.diagram.edit.parts.SRMmodelEditPart;
import SRM.diagram.part.SRMDiagramEditorPlugin;
import SRM.diagram.part.SRMVisualIDRegistry;
import SRM.diagram.providers.SRMElementTypes;
import SRM.diagram.providers.SRMParserProvider;

/**
 * @generated
 */
public class SRMNavigatorLabelProvider extends LabelProvider implements
		ICommonLabelProvider, ITreePathLabelProvider {

	/**
	 * @generated
	 */
	static {
		SRMDiagramEditorPlugin
				.getInstance()
				.getImageRegistry()
				.put(
						"Navigator?UnknownElement", ImageDescriptor.getMissingImageDescriptor()); //$NON-NLS-1$
		SRMDiagramEditorPlugin
				.getInstance()
				.getImageRegistry()
				.put(
						"Navigator?ImageNotFound", ImageDescriptor.getMissingImageDescriptor()); //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	public void updateLabel(ViewerLabel label, TreePath elementPath) {
		Object element = elementPath.getLastSegment();
		if (element instanceof SRMNavigatorItem
				&& !isOwnView(((SRMNavigatorItem) element).getView())) {
			return;
		}
		label.setText(getText(element));
		label.setImage(getImage(element));
	}

	/**
	 * @generated
	 */
	public Image getImage(Object element) {
		if (element instanceof SRMNavigatorGroup) {
			SRMNavigatorGroup group = (SRMNavigatorGroup) element;
			return SRMDiagramEditorPlugin.getInstance().getBundledImage(
					group.getIcon());
		}

		if (element instanceof SRMNavigatorItem) {
			SRMNavigatorItem navigatorItem = (SRMNavigatorItem) element;
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
		switch (SRMVisualIDRegistry.getVisualID(view)) {
		case SRMmodelEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Diagram?http://www.amcl.tuc.gr/aseme/metamodels/SRM?SRMmodel", SRMElementTypes.SRMmodel_1000); //$NON-NLS-1$
		case CapabilityEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http://www.amcl.tuc.gr/aseme/metamodels/SRM?Capability", SRMElementTypes.Capability_2004); //$NON-NLS-1$
		case RoleEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http://www.amcl.tuc.gr/aseme/metamodels/SRM?Role", SRMElementTypes.Role_2005); //$NON-NLS-1$
		case ActivityEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http://www.amcl.tuc.gr/aseme/metamodels/SRM?Activity", SRMElementTypes.Activity_2006); //$NON-NLS-1$
		case RoleCapabilitiesEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http://www.amcl.tuc.gr/aseme/metamodels/SRM?Role?capabilities", SRMElementTypes.RoleCapabilities_4004); //$NON-NLS-1$
		case RoleActivitiesEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http://www.amcl.tuc.gr/aseme/metamodels/SRM?Role?activities", SRMElementTypes.RoleActivities_4005); //$NON-NLS-1$
		case CapabilityActivitiesEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http://www.amcl.tuc.gr/aseme/metamodels/SRM?Capability?activities", SRMElementTypes.CapabilityActivities_4006); //$NON-NLS-1$
		}
		return getImage("Navigator?UnknownElement", null); //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	private Image getImage(String key, IElementType elementType) {
		ImageRegistry imageRegistry = SRMDiagramEditorPlugin.getInstance()
				.getImageRegistry();
		Image image = imageRegistry.get(key);
		if (image == null && elementType != null
				&& SRMElementTypes.isKnownElementType(elementType)) {
			image = SRMElementTypes.getImage(elementType);
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
		if (element instanceof SRMNavigatorGroup) {
			SRMNavigatorGroup group = (SRMNavigatorGroup) element;
			return group.getGroupName();
		}

		if (element instanceof SRMNavigatorItem) {
			SRMNavigatorItem navigatorItem = (SRMNavigatorItem) element;
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
		switch (SRMVisualIDRegistry.getVisualID(view)) {
		case SRMmodelEditPart.VISUAL_ID:
			return getSRMmodel_1000Text(view);
		case CapabilityEditPart.VISUAL_ID:
			return getCapability_2004Text(view);
		case RoleEditPart.VISUAL_ID:
			return getRole_2005Text(view);
		case ActivityEditPart.VISUAL_ID:
			return getActivity_2006Text(view);
		case RoleCapabilitiesEditPart.VISUAL_ID:
			return getRoleCapabilities_4004Text(view);
		case RoleActivitiesEditPart.VISUAL_ID:
			return getRoleActivities_4005Text(view);
		case CapabilityActivitiesEditPart.VISUAL_ID:
			return getCapabilityActivities_4006Text(view);
		}
		return getUnknownElementText(view);
	}

	/**
	 * @generated
	 */
	private String getSRMmodel_1000Text(View view) {
		return ""; //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	private String getCapability_2004Text(View view) {
		IParser parser = SRMParserProvider.getParser(
				SRMElementTypes.Capability_2004,
				view.getElement() != null ? view.getElement() : view,
				SRMVisualIDRegistry.getType(CapabilityNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			SRMDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5006); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getRole_2005Text(View view) {
		IParser parser = SRMParserProvider.getParser(SRMElementTypes.Role_2005,
				view.getElement() != null ? view.getElement() : view,
				SRMVisualIDRegistry.getType(RoleLivenessEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			SRMDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5007); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getActivity_2006Text(View view) {
		IParser parser = SRMParserProvider.getParser(
				SRMElementTypes.Activity_2006, view.getElement() != null ? view
						.getElement() : view, SRMVisualIDRegistry
						.getType(ActivityNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			SRMDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5009); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getRoleCapabilities_4004Text(View view) {
		return ""; //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	private String getRoleActivities_4005Text(View view) {
		return ""; //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	private String getCapabilityActivities_4006Text(View view) {
		return ""; //$NON-NLS-1$
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
		return SRMmodelEditPart.MODEL_ID.equals(SRMVisualIDRegistry
				.getModelID(view));
	}

}
