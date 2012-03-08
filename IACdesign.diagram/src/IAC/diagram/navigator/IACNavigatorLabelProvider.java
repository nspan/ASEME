package IAC.diagram.navigator;

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

import IAC.Model;
import IAC.diagram.edit.parts.ModelEditPart;
import IAC.diagram.edit.parts.NodeEditPart;
import IAC.diagram.edit.parts.NodeFather_ofEditPart;
import IAC.diagram.edit.parts.NodeNameEditPart;
import IAC.diagram.edit.parts.NodeVariablesEditPart;
import IAC.diagram.edit.parts.TransitionEditPart;
import IAC.diagram.edit.parts.TransitionTEEditPart;
import IAC.diagram.edit.parts.VariableEditPart;
import IAC.diagram.edit.parts.VariableNameEditPart;
import IAC.diagram.part.IACDiagramEditorPlugin;
import IAC.diagram.part.IACVisualIDRegistry;
import IAC.diagram.providers.IACElementTypes;
import IAC.diagram.providers.IACParserProvider;

/**
 * @generated
 */
public class IACNavigatorLabelProvider extends LabelProvider implements
		ICommonLabelProvider, ITreePathLabelProvider {

	/**
	 * @generated
	 */
	static {
		IACDiagramEditorPlugin
				.getInstance()
				.getImageRegistry()
				.put("Navigator?UnknownElement", ImageDescriptor.getMissingImageDescriptor()); //$NON-NLS-1$
		IACDiagramEditorPlugin
				.getInstance()
				.getImageRegistry()
				.put("Navigator?ImageNotFound", ImageDescriptor.getMissingImageDescriptor()); //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	public void updateLabel(ViewerLabel label, TreePath elementPath) {
		Object element = elementPath.getLastSegment();
		if (element instanceof IACNavigatorItem
				&& !isOwnView(((IACNavigatorItem) element).getView())) {
			return;
		}
		label.setText(getText(element));
		label.setImage(getImage(element));
	}

	/**
	 * @generated
	 */
	public Image getImage(Object element) {
		if (element instanceof IACNavigatorGroup) {
			IACNavigatorGroup group = (IACNavigatorGroup) element;
			return IACDiagramEditorPlugin.getInstance().getBundledImage(
					group.getIcon());
		}

		if (element instanceof IACNavigatorItem) {
			IACNavigatorItem navigatorItem = (IACNavigatorItem) element;
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
		switch (IACVisualIDRegistry.getVisualID(view)) {
		case NodeEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http://www.amcl.tuc.gr/aseme/metamodels/IAC?Node", IACElementTypes.Node_2006); //$NON-NLS-1$
		case NodeFather_ofEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http://www.amcl.tuc.gr/aseme/metamodels/IAC?Node?Father_of", IACElementTypes.NodeFather_of_4008); //$NON-NLS-1$
		case VariableEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http://www.amcl.tuc.gr/aseme/metamodels/IAC?Variable", IACElementTypes.Variable_2005); //$NON-NLS-1$
		case ModelEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Diagram?http://www.amcl.tuc.gr/aseme/metamodels/IAC?Model", IACElementTypes.Model_1000); //$NON-NLS-1$
		case NodeVariablesEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http://www.amcl.tuc.gr/aseme/metamodels/IAC?Node?variables", IACElementTypes.NodeVariables_4006); //$NON-NLS-1$
		case TransitionEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http://www.amcl.tuc.gr/aseme/metamodels/IAC?Transition", IACElementTypes.Transition_4007); //$NON-NLS-1$
		}
		return getImage("Navigator?UnknownElement", null); //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	private Image getImage(String key, IElementType elementType) {
		ImageRegistry imageRegistry = IACDiagramEditorPlugin.getInstance()
				.getImageRegistry();
		Image image = imageRegistry.get(key);
		if (image == null && elementType != null
				&& IACElementTypes.isKnownElementType(elementType)) {
			image = IACElementTypes.getImage(elementType);
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
		if (element instanceof IACNavigatorGroup) {
			IACNavigatorGroup group = (IACNavigatorGroup) element;
			return group.getGroupName();
		}

		if (element instanceof IACNavigatorItem) {
			IACNavigatorItem navigatorItem = (IACNavigatorItem) element;
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
		switch (IACVisualIDRegistry.getVisualID(view)) {
		case NodeEditPart.VISUAL_ID:
			return getNode_2006Text(view);
		case NodeFather_ofEditPart.VISUAL_ID:
			return getNodeFather_of_4008Text(view);
		case VariableEditPart.VISUAL_ID:
			return getVariable_2005Text(view);
		case ModelEditPart.VISUAL_ID:
			return getModel_1000Text(view);
		case NodeVariablesEditPart.VISUAL_ID:
			return getNodeVariables_4006Text(view);
		case TransitionEditPart.VISUAL_ID:
			return getTransition_4007Text(view);
		}
		return getUnknownElementText(view);
	}

	/**
	 * @generated
	 */
	private String getNode_2006Text(View view) {
		IParser parser = IACParserProvider.getParser(IACElementTypes.Node_2006,
				view.getElement() != null ? view.getElement() : view,
				IACVisualIDRegistry.getType(NodeNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			IACDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5015); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getNodeFather_of_4008Text(View view) {
		return ""; //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	private String getVariable_2005Text(View view) {
		IParser parser = IACParserProvider.getParser(
				IACElementTypes.Variable_2005,
				view.getElement() != null ? view.getElement() : view,
				IACVisualIDRegistry.getType(VariableNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			IACDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5013); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getModel_1000Text(View view) {
		Model domainModelElement = (Model) view.getElement();
		if (domainModelElement != null) {
			return domainModelElement.getName();
		} else {
			IACDiagramEditorPlugin.getInstance().logError(
					"No domain element for view with visualID = " + 1000); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getNodeVariables_4006Text(View view) {
		return ""; //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	private String getTransition_4007Text(View view) {
		IParser parser = IACParserProvider.getParser(
				IACElementTypes.Transition_4007,
				view.getElement() != null ? view.getElement() : view,
				IACVisualIDRegistry.getType(TransitionTEEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			IACDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 6005); //$NON-NLS-1$
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
		return ModelEditPart.MODEL_ID.equals(IACVisualIDRegistry
				.getModelID(view));
	}

}
