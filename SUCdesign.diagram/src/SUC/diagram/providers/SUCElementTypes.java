package SUC.diagram.providers;

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

import SUC.SUCPackage;
import SUC.diagram.edit.parts.HumanRoleEditPart;
import SUC.diagram.edit.parts.RoleEditPart;
import SUC.diagram.edit.parts.RoleParticipates_inEditPart;
import SUC.diagram.edit.parts.SUCmodelEditPart;
import SUC.diagram.edit.parts.SystemRoleEditPart;
import SUC.diagram.edit.parts.UseCaseEditPart;
import SUC.diagram.edit.parts.UseCaseIncludeEditPart;
import SUC.diagram.part.SUCDiagramEditorPlugin;

/**
 * @generated
 */
public class SUCElementTypes {

	/**
	 * @generated
	 */
	private SUCElementTypes() {
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
	public static final IElementType SUCmodel_1000 = getElementType("SUCdesign.diagram.SUCmodel_1000"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType SystemRole_2007 = getElementType("SUCdesign.diagram.SystemRole_2007"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType HumanRole_2008 = getElementType("SUCdesign.diagram.HumanRole_2008"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType UseCase_2009 = getElementType("SUCdesign.diagram.UseCase_2009"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Role_2010 = getElementType("SUCdesign.diagram.Role_2010"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType RoleParticipates_in_4008 = getElementType("SUCdesign.diagram.RoleParticipates_in_4008"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType UseCaseInclude_4009 = getElementType("SUCdesign.diagram.UseCaseInclude_4009"); //$NON-NLS-1$

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
				return SUCDiagramEditorPlugin.getInstance()
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

			elements.put(SUCmodel_1000, SUCPackage.eINSTANCE.getSUCmodel());

			elements.put(SystemRole_2007, SUCPackage.eINSTANCE.getSystemRole());

			elements.put(HumanRole_2008, SUCPackage.eINSTANCE.getHumanRole());

			elements.put(UseCase_2009, SUCPackage.eINSTANCE.getUseCase());

			elements.put(Role_2010, SUCPackage.eINSTANCE.getRole());

			elements.put(RoleParticipates_in_4008,
					SUCPackage.eINSTANCE.getRole_Participates_in());

			elements.put(UseCaseInclude_4009,
					SUCPackage.eINSTANCE.getUseCase_Include());
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
			KNOWN_ELEMENT_TYPES.add(SUCmodel_1000);
			KNOWN_ELEMENT_TYPES.add(SystemRole_2007);
			KNOWN_ELEMENT_TYPES.add(HumanRole_2008);
			KNOWN_ELEMENT_TYPES.add(UseCase_2009);
			KNOWN_ELEMENT_TYPES.add(Role_2010);
			KNOWN_ELEMENT_TYPES.add(RoleParticipates_in_4008);
			KNOWN_ELEMENT_TYPES.add(UseCaseInclude_4009);
		}
		return KNOWN_ELEMENT_TYPES.contains(elementType);
	}

	/**
	 * @generated
	 */
	public static IElementType getElementType(int visualID) {
		switch (visualID) {
		case SUCmodelEditPart.VISUAL_ID:
			return SUCmodel_1000;
		case SystemRoleEditPart.VISUAL_ID:
			return SystemRole_2007;
		case HumanRoleEditPart.VISUAL_ID:
			return HumanRole_2008;
		case UseCaseEditPart.VISUAL_ID:
			return UseCase_2009;
		case RoleEditPart.VISUAL_ID:
			return Role_2010;
		case RoleParticipates_inEditPart.VISUAL_ID:
			return RoleParticipates_in_4008;
		case UseCaseIncludeEditPart.VISUAL_ID:
			return UseCaseInclude_4009;
		}
		return null;
	}

}
