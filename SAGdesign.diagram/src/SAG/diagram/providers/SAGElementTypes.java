package SAG.diagram.providers;

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
	private static ImageRegistry imageRegistry;

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
	public static final IElementType Actor_2007 = getElementType("SAGdesign.diagram.Actor_2007"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Goal_2008 = getElementType("SAGdesign.diagram.Goal_2008"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType ActorMy_goal_4011 = getElementType("SAGdesign.diagram.ActorMy_goal_4011"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType GoalDependee_4013 = getElementType("SAGdesign.diagram.GoalDependee_4013"); //$NON-NLS-1$

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
				return SAGDiagramEditorPlugin.getInstance()
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

			elements.put(SAGmodel_1000, SAGPackage.eINSTANCE.getSAGmodel());

			elements.put(Actor_2007, SAGPackage.eINSTANCE.getActor());

			elements.put(Goal_2008, SAGPackage.eINSTANCE.getGoal());

			elements.put(ActorMy_goal_4011,
					SAGPackage.eINSTANCE.getActor_My_goal());

			elements.put(GoalDependee_4013,
					SAGPackage.eINSTANCE.getGoal_Dependee());
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
			KNOWN_ELEMENT_TYPES.add(Actor_2007);
			KNOWN_ELEMENT_TYPES.add(Goal_2008);
			KNOWN_ELEMENT_TYPES.add(ActorMy_goal_4011);
			KNOWN_ELEMENT_TYPES.add(GoalDependee_4013);
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
			return Actor_2007;
		case GoalEditPart.VISUAL_ID:
			return Goal_2008;
		case ActorMy_goalEditPart.VISUAL_ID:
			return ActorMy_goal_4011;
		case GoalDependeeEditPart.VISUAL_ID:
			return GoalDependee_4013;
		}
		return null;
	}

}
