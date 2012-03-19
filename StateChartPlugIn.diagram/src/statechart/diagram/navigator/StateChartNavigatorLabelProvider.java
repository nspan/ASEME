/*
 * Kouretes Statechart Editor-KSE developed 
 * by Angeliki Topalidou-Kyniazopoulou
 * for Kouretes Team. Code developed by Nikolaos Spanoudakis, Alex Parashos,
 * ibm, apache and eclipse is used.
 */
package statechart.diagram.navigator;

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

import statechart.Model;
import statechart.Node;
import statechart.diagram.edit.parts.ModelEditPart;
import statechart.diagram.edit.parts.Node10EditPart;
import statechart.diagram.edit.parts.Node11EditPart;
import statechart.diagram.edit.parts.Node12EditPart;
import statechart.diagram.edit.parts.Node2EditPart;
import statechart.diagram.edit.parts.Node3EditPart;
import statechart.diagram.edit.parts.Node4EditPart;
import statechart.diagram.edit.parts.Node5EditPart;
import statechart.diagram.edit.parts.Node6EditPart;
import statechart.diagram.edit.parts.Node7EditPart;
import statechart.diagram.edit.parts.Node8EditPart;
import statechart.diagram.edit.parts.Node9EditPart;
import statechart.diagram.edit.parts.NodeEditPart;
import statechart.diagram.edit.parts.NodeName2EditPart;
import statechart.diagram.edit.parts.NodeName3EditPart;
import statechart.diagram.edit.parts.NodeName4EditPart;
import statechart.diagram.edit.parts.NodeName5EditPart;
import statechart.diagram.edit.parts.NodeName6EditPart;
import statechart.diagram.edit.parts.NodeNameEditPart;
import statechart.diagram.edit.parts.TransitionEditPart;
import statechart.diagram.edit.parts.TransitionTEEditPart;
import statechart.diagram.edit.parts.VariableEditPart;
import statechart.diagram.edit.parts.VariableNameEditPart;
import statechart.diagram.edit.parts.WrappingLabel2EditPart;
import statechart.diagram.edit.parts.WrappingLabel3EditPart;
import statechart.diagram.edit.parts.WrappingLabel4EditPart;
import statechart.diagram.edit.parts.WrappingLabelEditPart;
import statechart.diagram.part.StateChartDiagramEditorPlugin;
import statechart.diagram.part.StateChartVisualIDRegistry;
import statechart.diagram.providers.StateChartElementTypes;
import statechart.diagram.providers.StateChartParserProvider;

/**
 * @generated
 */
public class StateChartNavigatorLabelProvider extends LabelProvider implements
		ICommonLabelProvider, ITreePathLabelProvider {

	/**
	 * @generated
	 */
	static {
		StateChartDiagramEditorPlugin
				.getInstance()
				.getImageRegistry()
				.put("Navigator?UnknownElement", ImageDescriptor.getMissingImageDescriptor()); //$NON-NLS-1$
		StateChartDiagramEditorPlugin
				.getInstance()
				.getImageRegistry()
				.put("Navigator?ImageNotFound", ImageDescriptor.getMissingImageDescriptor()); //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	public void updateLabel(ViewerLabel label, TreePath elementPath) {
		Object element = elementPath.getLastSegment();
		if (element instanceof StateChartNavigatorItem
				&& !isOwnView(((StateChartNavigatorItem) element).getView())) {
			return;
		}
		label.setText(getText(element));
		label.setImage(getImage(element));
	}

	/**
	 * @generated
	 */
	public Image getImage(Object element) {
		if (element instanceof StateChartNavigatorGroup) {
			StateChartNavigatorGroup group = (StateChartNavigatorGroup) element;
			return StateChartDiagramEditorPlugin.getInstance().getBundledImage(
					group.getIcon());
		}

		if (element instanceof StateChartNavigatorItem) {
			StateChartNavigatorItem navigatorItem = (StateChartNavigatorItem) element;
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
		switch (StateChartVisualIDRegistry.getVisualID(view)) {
		case Node10EditPart.VISUAL_ID:
			return getImage(
					"Navigator?Node?http://statechart/1.0?Node", StateChartElementTypes.Node_3004); //$NON-NLS-1$
		case Node11EditPart.VISUAL_ID:
			return getImage(
					"Navigator?Node?http://statechart/1.0?Node", StateChartElementTypes.Node_3005); //$NON-NLS-1$
		case Node5EditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http://statechart/1.0?Node", StateChartElementTypes.Node_2006); //$NON-NLS-1$
		case Node2EditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http://statechart/1.0?Node", StateChartElementTypes.Node_2002); //$NON-NLS-1$
		case Node12EditPart.VISUAL_ID:
			return getImage(
					"Navigator?Node?http://statechart/1.0?Node", StateChartElementTypes.Node_3006); //$NON-NLS-1$
		case Node6EditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http://statechart/1.0?Node", StateChartElementTypes.Node_2007); //$NON-NLS-1$
		case Node3EditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http://statechart/1.0?Node", StateChartElementTypes.Node_2004); //$NON-NLS-1$
		case VariableEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http://statechart/1.0?Variable", StateChartElementTypes.Variable_2003); //$NON-NLS-1$
		case TransitionEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http://statechart/1.0?Transition", StateChartElementTypes.Transition_4001); //$NON-NLS-1$
		case Node4EditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http://statechart/1.0?Node", StateChartElementTypes.Node_2005); //$NON-NLS-1$
		case Node7EditPart.VISUAL_ID:
			return getImage(
					"Navigator?Node?http://statechart/1.0?Node", StateChartElementTypes.Node_3001); //$NON-NLS-1$
		case ModelEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Diagram?http://statechart/1.0?Model", StateChartElementTypes.Model_1000); //$NON-NLS-1$
		case Node9EditPart.VISUAL_ID:
			return getImage(
					"Navigator?Node?http://statechart/1.0?Node", StateChartElementTypes.Node_3003); //$NON-NLS-1$
		case Node8EditPart.VISUAL_ID:
			return getImage(
					"Navigator?Node?http://statechart/1.0?Node", StateChartElementTypes.Node_3002); //$NON-NLS-1$
		case NodeEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http://statechart/1.0?Node", StateChartElementTypes.Node_2001); //$NON-NLS-1$
		}
		return getImage("Navigator?UnknownElement", null); //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	private Image getImage(String key, IElementType elementType) {
		ImageRegistry imageRegistry = StateChartDiagramEditorPlugin
				.getInstance().getImageRegistry();
		Image image = imageRegistry.get(key);
		if (image == null && elementType != null
				&& StateChartElementTypes.isKnownElementType(elementType)) {
			image = StateChartElementTypes.getImage(elementType);
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
		if (element instanceof StateChartNavigatorGroup) {
			StateChartNavigatorGroup group = (StateChartNavigatorGroup) element;
			return group.getGroupName();
		}

		if (element instanceof StateChartNavigatorItem) {
			StateChartNavigatorItem navigatorItem = (StateChartNavigatorItem) element;
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
		switch (StateChartVisualIDRegistry.getVisualID(view)) {
		case Node10EditPart.VISUAL_ID:
			return getNode_3004Text(view);
		case Node11EditPart.VISUAL_ID:
			return getNode_3005Text(view);
		case Node5EditPart.VISUAL_ID:
			return getNode_2006Text(view);
		case Node2EditPart.VISUAL_ID:
			return getNode_2002Text(view);
		case Node12EditPart.VISUAL_ID:
			return getNode_3006Text(view);
		case Node6EditPart.VISUAL_ID:
			return getNode_2007Text(view);
		case Node3EditPart.VISUAL_ID:
			return getNode_2004Text(view);
		case VariableEditPart.VISUAL_ID:
			return getVariable_2003Text(view);
		case TransitionEditPart.VISUAL_ID:
			return getTransition_4001Text(view);
		case Node4EditPart.VISUAL_ID:
			return getNode_2005Text(view);
		case Node7EditPart.VISUAL_ID:
			return getNode_3001Text(view);
		case ModelEditPart.VISUAL_ID:
			return getModel_1000Text(view);
		case Node9EditPart.VISUAL_ID:
			return getNode_3003Text(view);
		case Node8EditPart.VISUAL_ID:
			return getNode_3002Text(view);
		case NodeEditPart.VISUAL_ID:
			return getNode_2001Text(view);
		}
		return getUnknownElementText(view);
	}

	/**
	 * @generated
	 */
	private String getNode_3005Text(View view) {
		IParser parser = StateChartParserProvider
				.getParser(StateChartElementTypes.Node_3005,
						view.getElement() != null ? view.getElement() : view,
						StateChartVisualIDRegistry
								.getType(NodeName6EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			StateChartDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5003); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getNode_2002Text(View view) {
		IParser parser = StateChartParserProvider
				.getParser(StateChartElementTypes.Node_2002,
						view.getElement() != null ? view.getElement() : view,
						StateChartVisualIDRegistry
								.getType(NodeName2EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			StateChartDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5007); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getNode_3006Text(View view) {
		Node domainModelElement = (Node) view.getElement();
		if (domainModelElement != null) {
			return domainModelElement.getName();
		} else {
			StateChartDiagramEditorPlugin.getInstance().logError(
					"No domain element for view with visualID = " + 3006); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getNode_2001Text(View view) {
		IParser parser = StateChartParserProvider.getParser(
				StateChartElementTypes.Node_2001,
				view.getElement() != null ? view.getElement() : view,
				StateChartVisualIDRegistry.getType(NodeNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			StateChartDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5006); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getNode_3002Text(View view) {
		IParser parser = StateChartParserProvider
				.getParser(StateChartElementTypes.Node_3002,
						view.getElement() != null ? view.getElement() : view,
						StateChartVisualIDRegistry
								.getType(NodeName4EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			StateChartDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5002); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getVariable_2003Text(View view) {
		IParser parser = StateChartParserProvider.getParser(
				StateChartElementTypes.Variable_2003,
				view.getElement() != null ? view.getElement() : view,
				StateChartVisualIDRegistry
						.getType(VariableNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			StateChartDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5008); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getNode_2006Text(View view) {
		IParser parser = StateChartParserProvider
				.getParser(StateChartElementTypes.Node_2006,
						view.getElement() != null ? view.getElement() : view,
						StateChartVisualIDRegistry
								.getType(NodeName3EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			StateChartDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5011); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getNode_2004Text(View view) {
		Node domainModelElement = (Node) view.getElement();
		if (domainModelElement != null) {
			return domainModelElement.getName();
		} else {
			StateChartDiagramEditorPlugin.getInstance().logError(
					"No domain element for view with visualID = " + 2004); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getNode_2007Text(View view) {
		IParser parser = StateChartParserProvider.getParser(
				StateChartElementTypes.Node_2007,
				view.getElement() != null ? view.getElement() : view,
				StateChartVisualIDRegistry
						.getType(WrappingLabelEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			StateChartDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5012); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getNode_3003Text(View view) {
		IParser parser = StateChartParserProvider
				.getParser(StateChartElementTypes.Node_3003,
						view.getElement() != null ? view.getElement() : view,
						StateChartVisualIDRegistry
								.getType(NodeName5EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			StateChartDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5005); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getNode_3004Text(View view) {
		Node domainModelElement = (Node) view.getElement();
		if (domainModelElement != null) {
			return domainModelElement.getName();
		} else {
			StateChartDiagramEditorPlugin.getInstance().logError(
					"No domain element for view with visualID = " + 3004); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getTransition_4001Text(View view) {
		IParser parser = StateChartParserProvider.getParser(
				StateChartElementTypes.Transition_4001,
				view.getElement() != null ? view.getElement() : view,
				StateChartVisualIDRegistry
						.getType(TransitionTEEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			StateChartDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 6001); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getNode_3001Text(View view) {
		IParser parser = StateChartParserProvider.getParser(
				StateChartElementTypes.Node_3001,
				view.getElement() != null ? view.getElement() : view,
				StateChartVisualIDRegistry
						.getType(WrappingLabel2EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			StateChartDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5001); //$NON-NLS-1$
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
			StateChartDiagramEditorPlugin.getInstance().logError(
					"No domain element for view with visualID = " + 1000); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getNode_2005Text(View view) {
		Node domainModelElement = (Node) view.getElement();
		if (domainModelElement != null) {
			return domainModelElement.getName();
		} else {
			StateChartDiagramEditorPlugin.getInstance().logError(
					"No domain element for view with visualID = " + 2005); //$NON-NLS-1$
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
		return ModelEditPart.MODEL_ID.equals(StateChartVisualIDRegistry
				.getModelID(view));
	}

}
