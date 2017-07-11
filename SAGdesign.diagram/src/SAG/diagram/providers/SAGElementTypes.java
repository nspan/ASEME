package SAG.diagram.providers;

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

import SAG.SAGPackage;
import SAG.diagram.edit.parts.ActorEditPart;
import SAG.diagram.edit.parts.ActorMy_goalEditPart;
import SAG.diagram.edit.parts.GoalDependeeEditPart;
import SAG.diagram.edit.parts.GoalEditPart;
import SAG.diagram.edit.parts.SAGmodelEditPart;
import SAG.diagram.part.SAGDiagramEditorPlugin;

/**
 * @generated
 */
public class SAGElementTypes {

	/**
	* @generated
	*/
	private SAGElementTypes() {
	}

	/**
	* @generated
	*/
	private static Map<IElementType, ENamedElement> elements;

	/**
	* @generated
	*/
	private static DiagramElementTypeImages elementTypeImages = new DiagramElementTypeImages(
			SAGDiagramEditorPlugin.getInstance().getItemProvidersAdapterFactory());

	/**
	* @generated
	*/
	private static Set<IElementType> KNOWN_ELEMENT_TYPES;

	/**
	* @generated
	*/
	public static final IElementType SAGmodel_1000 = getElementType("SAGdesign.diagram.SAGmodel_1000"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType Actor_2001 = getElementType("SAGdesign.diagram.Actor_2001"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType Goal_2002 = getElementType("SAGdesign.diagram.Goal_2002"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType ActorMy_goal_4001 = getElementType("SAGdesign.diagram.ActorMy_goal_4001"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType GoalDependee_4002 = getElementType("SAGdesign.diagram.GoalDependee_4002"); //$NON-NLS-1$

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

			elements.put(SAGmodel_1000, SAGPackage.eINSTANCE.getSAGmodel());

			elements.put(Actor_2001, SAGPackage.eINSTANCE.getActor());

			elements.put(Goal_2002, SAGPackage.eINSTANCE.getGoal());

			elements.put(ActorMy_goal_4001, SAGPackage.eINSTANCE.getActor_My_goal());

			elements.put(GoalDependee_4002, SAGPackage.eINSTANCE.getGoal_Dependee());
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
			KNOWN_ELEMENT_TYPES.add(SAGmodel_1000);
			KNOWN_ELEMENT_TYPES.add(Actor_2001);
			KNOWN_ELEMENT_TYPES.add(Goal_2002);
			KNOWN_ELEMENT_TYPES.add(ActorMy_goal_4001);
			KNOWN_ELEMENT_TYPES.add(GoalDependee_4002);
		}
		return KNOWN_ELEMENT_TYPES.contains(elementType);
	}

	/**
	* @generated
	*/
	public static IElementType getElementType(int visualID) {
		switch (visualID) {
		case SAGmodelEditPart.VISUAL_ID:
			return SAGmodel_1000;
		case ActorEditPart.VISUAL_ID:
			return Actor_2001;
		case GoalEditPart.VISUAL_ID:
			return Goal_2002;
		case ActorMy_goalEditPart.VISUAL_ID:
			return ActorMy_goal_4001;
		case GoalDependeeEditPart.VISUAL_ID:
			return GoalDependee_4002;
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
			return SAG.diagram.providers.SAGElementTypes.isKnownElementType(elementType);
		}

		/**
		* @generated
		*/
		@Override

		public IElementType getElementTypeForVisualId(int visualID) {
			return SAG.diagram.providers.SAGElementTypes.getElementType(visualID);
		}

		/**
		* @generated
		*/
		@Override

		public ENamedElement getDefiningNamedElement(IAdaptable elementTypeAdapter) {
			return SAG.diagram.providers.SAGElementTypes.getElement(elementTypeAdapter);
		}
	};

}
