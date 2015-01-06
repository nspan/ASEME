package SUC.diagram.providers;

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

import SUC.SUCPackage;
import SUC.diagram.edit.parts.RoleEditPart;
import SUC.diagram.edit.parts.RoleParticipates_inEditPart;
import SUC.diagram.edit.parts.SUCmodelEditPart;
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
	private static DiagramElementTypeImages elementTypeImages = new DiagramElementTypeImages(
			SUCDiagramEditorPlugin.getInstance()
					.getItemProvidersAdapterFactory());

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
	public static final IElementType Role_2001 = getElementType("SUCdesign.diagram.Role_2001"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType UseCase_2002 = getElementType("SUCdesign.diagram.UseCase_2002"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType UseCaseInclude_4003 = getElementType("SUCdesign.diagram.UseCaseInclude_4003"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType RoleParticipates_in_4001 = getElementType("SUCdesign.diagram.RoleParticipates_in_4001"); //$NON-NLS-1$

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

			elements.put(SUCmodel_1000, SUCPackage.eINSTANCE.getSUCmodel());

			elements.put(Role_2001, SUCPackage.eINSTANCE.getRole());

			elements.put(UseCase_2002, SUCPackage.eINSTANCE.getUseCase());

			elements.put(UseCaseInclude_4003,
					SUCPackage.eINSTANCE.getUseCase_Include());

			elements.put(RoleParticipates_in_4001,
					SUCPackage.eINSTANCE.getRole_Participates_in());
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
			KNOWN_ELEMENT_TYPES.add(Role_2001);
			KNOWN_ELEMENT_TYPES.add(UseCase_2002);
			KNOWN_ELEMENT_TYPES.add(UseCaseInclude_4003);
			KNOWN_ELEMENT_TYPES.add(RoleParticipates_in_4001);
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
		case RoleEditPart.VISUAL_ID:
			return Role_2001;
		case UseCaseEditPart.VISUAL_ID:
			return UseCase_2002;
		case UseCaseIncludeEditPart.VISUAL_ID:
			return UseCaseInclude_4003;
		case RoleParticipates_inEditPart.VISUAL_ID:
			return RoleParticipates_in_4001;
		}
		return null;
	}

	/**
	 * @generated
	 */
	public static final DiagramElementTypes TYPED_INSTANCE = new DiagramElementTypes(
			elementTypeImages) {

		/**
		 * @generated
		 */
		@Override
		public boolean isKnownElementType(IElementType elementType) {
			return SUC.diagram.providers.SUCElementTypes
					.isKnownElementType(elementType);
		}

		/**
		 * @generated
		 */
		@Override
		public IElementType getElementTypeForVisualId(int visualID) {
			return SUC.diagram.providers.SUCElementTypes
					.getElementType(visualID);
		}

		/**
		 * @generated
		 */
		@Override
		public ENamedElement getDefiningNamedElement(
				IAdaptable elementTypeAdapter) {
			return SUC.diagram.providers.SUCElementTypes
					.getElement(elementTypeAdapter);
		}
	};

}
