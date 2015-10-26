package AIP.diagram.navigator;

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

import AIP.diagram.edit.parts.AIPmodelEditPart;
import AIP.diagram.edit.parts.ParticipantEditPart;
import AIP.diagram.edit.parts.ParticipantNameEditPart;
import AIP.diagram.edit.parts.ProtocolEditPart;
import AIP.diagram.edit.parts.ProtocolNameEditPart;
import AIP.diagram.edit.parts.ProtocolParticipantsEditPart;
import AIP.diagram.part.AIPDiagramEditorPlugin;
import AIP.diagram.part.AIPVisualIDRegistry;
import AIP.diagram.providers.AIPElementTypes;
import AIP.diagram.providers.AIPParserProvider;

/**
 * @generated
 */
public class AIPNavigatorLabelProvider extends LabelProvider implements ICommonLabelProvider, ITreePathLabelProvider {

	/**
	* @generated
	*/
	static {
		AIPDiagramEditorPlugin.getInstance().getImageRegistry().put("Navigator?UnknownElement", //$NON-NLS-1$
				ImageDescriptor.getMissingImageDescriptor());
		AIPDiagramEditorPlugin.getInstance().getImageRegistry().put("Navigator?ImageNotFound", //$NON-NLS-1$
				ImageDescriptor.getMissingImageDescriptor());
	}

	/**
	* @generated
	*/
	public void updateLabel(ViewerLabel label, TreePath elementPath) {
		Object element = elementPath.getLastSegment();
		if (element instanceof AIPNavigatorItem && !isOwnView(((AIPNavigatorItem) element).getView())) {
			return;
		}
		label.setText(getText(element));
		label.setImage(getImage(element));
	}

	/**
	* @generated
	*/
	public Image getImage(Object element) {
		if (element instanceof AIPNavigatorGroup) {
			AIPNavigatorGroup group = (AIPNavigatorGroup) element;
			return AIPDiagramEditorPlugin.getInstance().getBundledImage(group.getIcon());
		}

		if (element instanceof AIPNavigatorItem) {
			AIPNavigatorItem navigatorItem = (AIPNavigatorItem) element;
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
		switch (AIPVisualIDRegistry.getVisualID(view)) {
		case AIPmodelEditPart.VISUAL_ID:
			return getImage("Navigator?Diagram?http://www.acml.tuc.gr/aseme/metamodels/AIP?AIPmodel", //$NON-NLS-1$
					AIPElementTypes.AIPmodel_1000);
		case ParticipantEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http://www.acml.tuc.gr/aseme/metamodels/AIP?Participant", //$NON-NLS-1$
					AIPElementTypes.Participant_2003);
		case ProtocolEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http://www.acml.tuc.gr/aseme/metamodels/AIP?Protocol", //$NON-NLS-1$
					AIPElementTypes.Protocol_2004);
		case ProtocolParticipantsEditPart.VISUAL_ID:
			return getImage("Navigator?Link?http://www.acml.tuc.gr/aseme/metamodels/AIP?Protocol?participants", //$NON-NLS-1$
					AIPElementTypes.ProtocolParticipants_4002);
		}
		return getImage("Navigator?UnknownElement", null); //$NON-NLS-1$
	}

	/**
	* @generated
	*/
	private Image getImage(String key, IElementType elementType) {
		ImageRegistry imageRegistry = AIPDiagramEditorPlugin.getInstance().getImageRegistry();
		Image image = imageRegistry.get(key);
		if (image == null && elementType != null && AIPElementTypes.isKnownElementType(elementType)) {
			image = AIPElementTypes.getImage(elementType);
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
		if (element instanceof AIPNavigatorGroup) {
			AIPNavigatorGroup group = (AIPNavigatorGroup) element;
			return group.getGroupName();
		}

		if (element instanceof AIPNavigatorItem) {
			AIPNavigatorItem navigatorItem = (AIPNavigatorItem) element;
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
		switch (AIPVisualIDRegistry.getVisualID(view)) {
		case AIPmodelEditPart.VISUAL_ID:
			return getAIPmodel_1000Text(view);
		case ParticipantEditPart.VISUAL_ID:
			return getParticipant_2003Text(view);
		case ProtocolEditPart.VISUAL_ID:
			return getProtocol_2004Text(view);
		case ProtocolParticipantsEditPart.VISUAL_ID:
			return getProtocolParticipants_4002Text(view);
		}
		return getUnknownElementText(view);
	}

	/**
	* @generated
	*/
	private String getAIPmodel_1000Text(View view) {
		return ""; //$NON-NLS-1$
	}

	/**
	* @generated
	*/
	private String getParticipant_2003Text(View view) {
		IParser parser = AIPParserProvider.getParser(AIPElementTypes.Participant_2003,
				view.getElement() != null ? view.getElement() : view,
				AIPVisualIDRegistry.getType(ParticipantNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			AIPDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5006); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getProtocol_2004Text(View view) {
		IParser parser = AIPParserProvider.getParser(AIPElementTypes.Protocol_2004,
				view.getElement() != null ? view.getElement() : view,
				AIPVisualIDRegistry.getType(ProtocolNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			AIPDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5010); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getProtocolParticipants_4002Text(View view) {
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
		return AIPmodelEditPart.MODEL_ID.equals(AIPVisualIDRegistry.getModelID(view));
	}

}
