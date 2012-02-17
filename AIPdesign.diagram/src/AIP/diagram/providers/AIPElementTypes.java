package AIP.diagram.providers;

import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;

import AIP.AIPPackage;
import AIP.diagram.edit.parts.AIPmodelEditPart;
import AIP.diagram.edit.parts.ParticipantEditPart;
import AIP.diagram.edit.parts.ProtocolEditPart;
import AIP.diagram.edit.parts.ProtocolParticipantsEditPart;
import AIP.diagram.part.AIPDiagramEditorPlugin;

/**
 * @generated
 */
public class AIPElementTypes {

	/**
	 * @generated
	 */
	private AIPElementTypes() {
	}

	/**
	 * @generated
	 */
	private static Map<IElementType, ENamedElement> elements;

	/**
	 * @generated
	 */
	private static ImageRegistry imageRegistry;

	/**
	 * @generated
	 */
	private static Set<IElementType> KNOWN_ELEMENT_TYPES;

	/**
	 * @generated
	 */
	public static final IElementType AIPmodel_1000 = getElementType("AIPdesign.diagram.AIPmodel_1000"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Participant_2003 = getElementType("AIPdesign.diagram.Participant_2003"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Protocol_2004 = getElementType("AIPdesign.diagram.Protocol_2004"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType ProtocolParticipants_4002 = getElementType("AIPdesign.diagram.ProtocolParticipants_4002"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	private static ImageRegistry getImageRegistry() {
		if (imageRegistry == null) {
			imageRegistry = new ImageRegistry();
		}
		return imageRegistry;
	}

	/**
	 * @generated
	 */
	private static String getImageRegistryKey(ENamedElement element) {
		return element.getName();
	}

	/**
	 * @generated
	 */
	private static ImageDescriptor getProvidedImageDescriptor(
			ENamedElement element) {
		if (element instanceof EStructuralFeature) {
			EStructuralFeature feature = ((EStructuralFeature) element);
			EClass eContainingClass = feature.getEContainingClass();
			EClassifier eType = feature.getEType();
			if (eContainingClass != null && !eContainingClass.isAbstract()) {
				element = eContainingClass;
			} else if (eType instanceof EClass
					&& !((EClass) eType).isAbstract()) {
				element = eType;
			}
		}
		if (element instanceof EClass) {
			EClass eClass = (EClass) element;
			if (!eClass.isAbstract()) {
				return AIPDiagramEditorPlugin.getInstance()
						.getItemImageDescriptor(
								eClass.getEPackage().getEFactoryInstance()
										.create(eClass));
			}
		}
		// TODO : support structural features
		return null;
	}

	/**
	 * @generated
	 */
	public static ImageDescriptor getImageDescriptor(ENamedElement element) {
		String key = getImageRegistryKey(element);
		ImageDescriptor imageDescriptor = getImageRegistry().getDescriptor(key);
		if (imageDescriptor == null) {
			imageDescriptor = getProvidedImageDescriptor(element);
			if (imageDescriptor == null) {
				imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
			}
			getImageRegistry().put(key, imageDescriptor);
		}
		return imageDescriptor;
	}

	/**
	 * @generated
	 */
	public static Image getImage(ENamedElement element) {
		String key = getImageRegistryKey(element);
		Image image = getImageRegistry().get(key);
		if (image == null) {
			ImageDescriptor imageDescriptor = getProvidedImageDescriptor(element);
			if (imageDescriptor == null) {
				imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
			}
			getImageRegistry().put(key, imageDescriptor);
			image = getImageRegistry().get(key);
		}
		return image;
	}

	/**
	 * @generated
	 */
	public static ImageDescriptor getImageDescriptor(IAdaptable hint) {
		ENamedElement element = getElement(hint);
		if (element == null) {
			return null;
		}
		return getImageDescriptor(element);
	}

	/**
	 * @generated
	 */
	public static Image getImage(IAdaptable hint) {
		ENamedElement element = getElement(hint);
		if (element == null) {
			return null;
		}
		return getImage(element);
	}

	/**
	 * Returns 'type' of the ecore object associated with the hint.
	 * 
	 * @generated
	 */
	public static ENamedElement getElement(IAdaptable hint) {
		Object type = hint.getAdapter(IElementType.class);
		if (elements == null) {
			elements = new IdentityHashMap<IElementType, ENamedElement>();

			elements.put(AIPmodel_1000, AIPPackage.eINSTANCE.getAIPmodel());

			elements.put(Participant_2003,
					AIPPackage.eINSTANCE.getParticipant());

			elements.put(Protocol_2004, AIPPackage.eINSTANCE.getProtocol());

			elements.put(ProtocolParticipants_4002,
					AIPPackage.eINSTANCE.getProtocol_Participants());
		}
		return (ENamedElement) elements.get(type);
	}

	/**
	 * @generated
	 */
	private static IElementType getElementType(String id) {
		return ElementTypeRegistry.getInstance().getType(id);
	}

	/**
	 * @generated
	 */
	public static boolean isKnownElementType(IElementType elementType) {
		if (KNOWN_ELEMENT_TYPES == null) {
			KNOWN_ELEMENT_TYPES = new HashSet<IElementType>();
			KNOWN_ELEMENT_TYPES.add(AIPmodel_1000);
			KNOWN_ELEMENT_TYPES.add(Participant_2003);
			KNOWN_ELEMENT_TYPES.add(Protocol_2004);
			KNOWN_ELEMENT_TYPES.add(ProtocolParticipants_4002);
		}
		return KNOWN_ELEMENT_TYPES.contains(elementType);
	}

	/**
	 * @generated
	 */
	public static IElementType getElementType(int visualID) {
		switch (visualID) {
		case AIPmodelEditPart.VISUAL_ID:
			return AIPmodel_1000;
		case ParticipantEditPart.VISUAL_ID:
			return Participant_2003;
		case ProtocolEditPart.VISUAL_ID:
			return Protocol_2004;
		case ProtocolParticipantsEditPart.VISUAL_ID:
			return ProtocolParticipants_4002;
		}
		return null;
	}

}
