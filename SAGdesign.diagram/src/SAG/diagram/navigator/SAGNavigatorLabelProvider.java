package SAG.diagram.navigator;

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

import SAG.diagram.edit.parts.ActorEditPart;
import SAG.diagram.edit.parts.ActorMy_goalEditPart;
import SAG.diagram.edit.parts.ActorNameEditPart;
import SAG.diagram.edit.parts.GoalDependeeEditPart;
import SAG.diagram.edit.parts.GoalEditPart;
import SAG.diagram.edit.parts.GoalNameEditPart;
import SAG.diagram.edit.parts.SAGmodelEditPart;
import SAG.diagram.part.SAGDiagramEditorPlugin;
import SAG.diagram.part.SAGVisualIDRegistry;
import SAG.diagram.providers.SAGElementTypes;
import SAG.diagram.providers.SAGParserProvider;

/**
 * @generated
 */
public class SAGNavigatorLabelProvider extends LabelProvider implements ICommonLabelProvider, ITreePathLabelProvider {

	/**
	* @generated
	*/
	static {
		SAGDiagramEditorPlugin.getInstance().getImageRegistry().put("Navigator?UnknownElement", //$NON-NLS-1$
				ImageDescriptor.getMissingImageDescriptor());
		SAGDiagramEditorPlugin.getInstance().getImageRegistry().put("Navigator?ImageNotFound", //$NON-NLS-1$
				ImageDescriptor.getMissingImageDescriptor());
	}

	/**
	* @generated
	*/
	public void updateLabel(ViewerLabel label, TreePath elementPath) {
		Object element = elementPath.getLastSegment();
		if (element instanceof SAGNavigatorItem && !isOwnView(((SAGNavigatorItem) element).getView())) {
			return;
		}
		label.setText(getText(element));
		label.setImage(getImage(element));
	}

	/**
	* @generated
	*/
	public Image getImage(Object element) {
		if (element instanceof SAGNavigatorGroup) {
			SAGNavigatorGroup group = (SAGNavigatorGroup) element;
			return SAGDiagramEditorPlugin.getInstance().getBundledImage(group.getIcon());
		}

		if (element instanceof SAGNavigatorItem) {
			SAGNavigatorItem navigatorItem = (SAGNavigatorItem) element;
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
		switch (SAGVisualIDRegistry.getVisualID(view)) {
		case SAGmodelEditPart.VISUAL_ID:
			return getImage("Navigator?Diagram?http://www.amcl.tuc.gr/aseme/metamodels/SAG?SAGmodel", //$NON-NLS-1$
					SAGElementTypes.SAGmodel_1000);
		case ActorEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http://www.amcl.tuc.gr/aseme/metamodels/SAG?Actor", //$NON-NLS-1$
					SAGElementTypes.Actor_2001);
		case GoalEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http://www.amcl.tuc.gr/aseme/metamodels/SAG?Goal", //$NON-NLS-1$
					SAGElementTypes.Goal_2002);
		case ActorMy_goalEditPart.VISUAL_ID:
			return getImage("Navigator?Link?http://www.amcl.tuc.gr/aseme/metamodels/SAG?Actor?my_goal", //$NON-NLS-1$
					SAGElementTypes.ActorMy_goal_4001);
		case GoalDependeeEditPart.VISUAL_ID:
			return getImage("Navigator?Link?http://www.amcl.tuc.gr/aseme/metamodels/SAG?Goal?dependee", //$NON-NLS-1$
					SAGElementTypes.GoalDependee_4002);
		}
		return getImage("Navigator?UnknownElement", null); //$NON-NLS-1$
	}

	/**
	* @generated
	*/
	private Image getImage(String key, IElementType elementType) {
		ImageRegistry imageRegistry = SAGDiagramEditorPlugin.getInstance().getImageRegistry();
		Image image = imageRegistry.get(key);
		if (image == null && elementType != null && SAGElementTypes.isKnownElementType(elementType)) {
			image = SAGElementTypes.getImage(elementType);
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
		if (element instanceof SAGNavigatorGroup) {
			SAGNavigatorGroup group = (SAGNavigatorGroup) element;
			return group.getGroupName();
		}

		if (element instanceof SAGNavigatorItem) {
			SAGNavigatorItem navigatorItem = (SAGNavigatorItem) element;
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
		switch (SAGVisualIDRegistry.getVisualID(view)) {
		case SAGmodelEditPart.VISUAL_ID:
			return getSAGmodel_1000Text(view);
		case ActorEditPart.VISUAL_ID:
			return getActor_2001Text(view);
		case GoalEditPart.VISUAL_ID:
			return getGoal_2002Text(view);
		case ActorMy_goalEditPart.VISUAL_ID:
			return getActorMy_goal_4001Text(view);
		case GoalDependeeEditPart.VISUAL_ID:
			return getGoalDependee_4002Text(view);
		}
		return getUnknownElementText(view);
	}

	/**
	* @generated
	*/
	private String getSAGmodel_1000Text(View view) {
		return ""; //$NON-NLS-1$
	}

	/**
	* @generated
	*/
	private String getActor_2001Text(View view) {
		IParser parser = SAGParserProvider.getParser(SAGElementTypes.Actor_2001,
				view.getElement() != null ? view.getElement() : view,
				SAGVisualIDRegistry.getType(ActorNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			SAGDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5001); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getGoal_2002Text(View view) {
		IParser parser = SAGParserProvider.getParser(SAGElementTypes.Goal_2002,
				view.getElement() != null ? view.getElement() : view,
				SAGVisualIDRegistry.getType(GoalNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			SAGDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5002); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getActorMy_goal_4001Text(View view) {
		return ""; //$NON-NLS-1$
	}

	/**
	* @generated
	*/
	private String getGoalDependee_4002Text(View view) {
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
		return SAGmodelEditPart.MODEL_ID.equals(SAGVisualIDRegistry.getModelID(view));
	}

}
