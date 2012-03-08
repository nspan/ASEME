package SRM.diagram.providers;

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

import SRM.SRMPackage;
import SRM.diagram.edit.parts.ActivityEditPart;
import SRM.diagram.edit.parts.CapabilityActivitiesEditPart;
import SRM.diagram.edit.parts.CapabilityEditPart;
import SRM.diagram.edit.parts.RoleActivitiesEditPart;
import SRM.diagram.edit.parts.RoleCapabilitiesEditPart;
import SRM.diagram.edit.parts.RoleEditPart;
import SRM.diagram.edit.parts.SRMmodelEditPart;
import SRM.diagram.part.SRMDiagramEditorPlugin;

/**
 * @generated
 */
public class SRMElementTypes extends ElementInitializers {

	/**
	 * @generated
	 */
	private SRMElementTypes() {
	}

	/**
	 * @generated
	 */
	private static Map elements;

	/**
	 * @generated
	 */
	private static ImageRegistry imageRegistry;

	/**
	 * @generated
	 */
	private static Set KNOWN_ELEMENT_TYPES;

	/**
	 * @generated
	 */
	public static final IElementType SRMmodel_1000 = getElementType("SRMdesign.diagram.SRMmodel_1000"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Capability_2004 = getElementType("SRMdesign.diagram.Capability_2004"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Role_2005 = getElementType("SRMdesign.diagram.Role_2005"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Activity_2006 = getElementType("SRMdesign.diagram.Activity_2006"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType RoleCapabilities_4004 = getElementType("SRMdesign.diagram.RoleCapabilities_4004"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType RoleActivities_4005 = getElementType("SRMdesign.diagram.RoleActivities_4005"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType CapabilityActivities_4006 = getElementType("SRMdesign.diagram.CapabilityActivities_4006"); //$NON-NLS-1$

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
				return SRMDiagramEditorPlugin.getInstance()
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
			elements = new IdentityHashMap();

			elements.put(SRMmodel_1000, SRMPackage.eINSTANCE.getSRMmodel());

			elements.put(Capability_2004, SRMPackage.eINSTANCE.getCapability());

			elements.put(Role_2005, SRMPackage.eINSTANCE.getRole());

			elements.put(Activity_2006, SRMPackage.eINSTANCE.getActivity());

			elements.put(RoleCapabilities_4004, SRMPackage.eINSTANCE
					.getRole_Capabilities());

			elements.put(RoleActivities_4005, SRMPackage.eINSTANCE
					.getRole_Activities());

			elements.put(CapabilityActivities_4006, SRMPackage.eINSTANCE
					.getCapability_Activities());
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
			KNOWN_ELEMENT_TYPES = new HashSet();
			KNOWN_ELEMENT_TYPES.add(SRMmodel_1000);
			KNOWN_ELEMENT_TYPES.add(Capability_2004);
			KNOWN_ELEMENT_TYPES.add(Role_2005);
			KNOWN_ELEMENT_TYPES.add(Activity_2006);
			KNOWN_ELEMENT_TYPES.add(RoleCapabilities_4004);
			KNOWN_ELEMENT_TYPES.add(RoleActivities_4005);
			KNOWN_ELEMENT_TYPES.add(CapabilityActivities_4006);
		}
		return KNOWN_ELEMENT_TYPES.contains(elementType);
	}

	/**
	 * @generated
	 */
	public static IElementType getElementType(int visualID) {
		switch (visualID) {
		case SRMmodelEditPart.VISUAL_ID:
			return SRMmodel_1000;
		case CapabilityEditPart.VISUAL_ID:
			return Capability_2004;
		case RoleEditPart.VISUAL_ID:
			return Role_2005;
		case ActivityEditPart.VISUAL_ID:
			return Activity_2006;
		case RoleCapabilitiesEditPart.VISUAL_ID:
			return RoleCapabilities_4004;
		case RoleActivitiesEditPart.VISUAL_ID:
			return RoleActivities_4005;
		case CapabilityActivitiesEditPart.VISUAL_ID:
			return CapabilityActivities_4006;
		}
		return null;
	}

}
