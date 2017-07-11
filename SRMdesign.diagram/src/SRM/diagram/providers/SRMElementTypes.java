package SRM.diagram.providers;

import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.tooling.runtime.providers.DiagramElementTypeImages;
import org.eclipse.gmf.tooling.runtime.providers.DiagramElementTypes;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

import SRM.SRMPackage;
import SRM.diagram.edit.parts.ActivityEditPart;
import SRM.diagram.edit.parts.CapabilityCapability_activitiesEditPart;
import SRM.diagram.edit.parts.CapabilityEditPart;
import SRM.diagram.edit.parts.FunctionalityActivitiesEditPart;
import SRM.diagram.edit.parts.FunctionalityEditPart;
import SRM.diagram.edit.parts.RoleCapabilitiesEditPart;
import SRM.diagram.edit.parts.RoleEditPart;
import SRM.diagram.edit.parts.RoleRole_activitiesEditPart;
import SRM.diagram.edit.parts.SRMmodelEditPart;
import SRM.diagram.part.SRMDiagramEditorPlugin;

/**
 * @generated
 */
public class SRMElementTypes {

	/**
	* @generated
	*/
	private SRMElementTypes() {
	}

	/**
	* @generated
	*/
	private static Map<IElementType, ENamedElement> elements;

	/**
	* @generated
	*/
	private static DiagramElementTypeImages elementTypeImages = new DiagramElementTypeImages(
			SRMDiagramEditorPlugin.getInstance().getItemProvidersAdapterFactory());

	/**
	* @generated
	*/
	private static Set<IElementType> KNOWN_ELEMENT_TYPES;

	/**
	* @generated
	*/
	public static final IElementType SRMmodel_1000 = getElementType("SRMdesign.diagram.SRMmodel_1000"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType Functionality_2001 = getElementType("SRMdesign.diagram.Functionality_2001"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType Capability_2002 = getElementType("SRMdesign.diagram.Capability_2002"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType Role_2003 = getElementType("SRMdesign.diagram.Role_2003"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType Activity_2004 = getElementType("SRMdesign.diagram.Activity_2004"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType RoleRole_activities_4001 = getElementType(
			"SRMdesign.diagram.RoleRole_activities_4001"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType CapabilityCapability_activities_4002 = getElementType(
			"SRMdesign.diagram.CapabilityCapability_activities_4002"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType FunctionalityActivities_4003 = getElementType(
			"SRMdesign.diagram.FunctionalityActivities_4003"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType RoleCapabilities_4004 = getElementType("SRMdesign.diagram.RoleCapabilities_4004"); //$NON-NLS-1$

	/**
	* @generated
	*/
	public static ImageDescriptor getImageDescriptor(ENamedElement element) {
		return elementTypeImages.getImageDescriptor(element);
	}

	/**
	* @generated
	*/
	public static Image getImage(ENamedElement element) {
		return elementTypeImages.getImage(element);
	}

	/**
	* @generated
	*/
	public static ImageDescriptor getImageDescriptor(IAdaptable hint) {
		return getImageDescriptor(getElement(hint));
	}

	/**
	* @generated
	*/
	public static Image getImage(IAdaptable hint) {
		return getImage(getElement(hint));
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

			elements.put(SRMmodel_1000, SRMPackage.eINSTANCE.getSRMmodel());

			elements.put(Functionality_2001, SRMPackage.eINSTANCE.getFunctionality());

			elements.put(Capability_2002, SRMPackage.eINSTANCE.getCapability());

			elements.put(Role_2003, SRMPackage.eINSTANCE.getRole());

			elements.put(Activity_2004, SRMPackage.eINSTANCE.getActivity());

			elements.put(RoleRole_activities_4001, SRMPackage.eINSTANCE.getRole_Role_activities());

			elements.put(CapabilityCapability_activities_4002,
					SRMPackage.eINSTANCE.getCapability_Capability_activities());

			elements.put(FunctionalityActivities_4003, SRMPackage.eINSTANCE.getFunctionality_Activities());

			elements.put(RoleCapabilities_4004, SRMPackage.eINSTANCE.getRole_Capabilities());
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
			KNOWN_ELEMENT_TYPES.add(SRMmodel_1000);
			KNOWN_ELEMENT_TYPES.add(Functionality_2001);
			KNOWN_ELEMENT_TYPES.add(Capability_2002);
			KNOWN_ELEMENT_TYPES.add(Role_2003);
			KNOWN_ELEMENT_TYPES.add(Activity_2004);
			KNOWN_ELEMENT_TYPES.add(RoleRole_activities_4001);
			KNOWN_ELEMENT_TYPES.add(CapabilityCapability_activities_4002);
			KNOWN_ELEMENT_TYPES.add(FunctionalityActivities_4003);
			KNOWN_ELEMENT_TYPES.add(RoleCapabilities_4004);
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
		case FunctionalityEditPart.VISUAL_ID:
			return Functionality_2001;
		case CapabilityEditPart.VISUAL_ID:
			return Capability_2002;
		case RoleEditPart.VISUAL_ID:
			return Role_2003;
		case ActivityEditPart.VISUAL_ID:
			return Activity_2004;
		case RoleRole_activitiesEditPart.VISUAL_ID:
			return RoleRole_activities_4001;
		case CapabilityCapability_activitiesEditPart.VISUAL_ID:
			return CapabilityCapability_activities_4002;
		case FunctionalityActivitiesEditPart.VISUAL_ID:
			return FunctionalityActivities_4003;
		case RoleCapabilitiesEditPart.VISUAL_ID:
			return RoleCapabilities_4004;
		}
		return null;
	}

	/**
	* @generated
	*/
	public static final DiagramElementTypes TYPED_INSTANCE = new DiagramElementTypes(elementTypeImages) {

		/**
		* @generated
		*/
		@Override

		public boolean isKnownElementType(IElementType elementType) {
			return SRM.diagram.providers.SRMElementTypes.isKnownElementType(elementType);
		}

		/**
		* @generated
		*/
		@Override

		public IElementType getElementTypeForVisualId(int visualID) {
			return SRM.diagram.providers.SRMElementTypes.getElementType(visualID);
		}

		/**
		* @generated
		*/
		@Override

		public ENamedElement getDefiningNamedElement(IAdaptable elementTypeAdapter) {
			return SRM.diagram.providers.SRMElementTypes.getElement(elementTypeAdapter);
		}
	};

}
