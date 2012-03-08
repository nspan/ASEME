package IAC.diagram.providers;

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

import IAC.IACPackage;
import IAC.diagram.edit.parts.ModelEditPart;
import IAC.diagram.edit.parts.NodeEditPart;
import IAC.diagram.edit.parts.NodeFather_ofEditPart;
import IAC.diagram.edit.parts.NodeVariablesEditPart;
import IAC.diagram.edit.parts.TransitionEditPart;
import IAC.diagram.edit.parts.VariableEditPart;
import IAC.diagram.part.IACDiagramEditorPlugin;

/**
 * @generated
 */
public class IACElementTypes {

	/**
	 * @generated
	 */
	private IACElementTypes() {
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
	public static final IElementType Model_1000 = getElementType("IACdesign.diagram.Model_1000"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Variable_2005 = getElementType("IACdesign.diagram.Variable_2005"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Node_2006 = getElementType("IACdesign.diagram.Node_2006"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType NodeVariables_4006 = getElementType("IACdesign.diagram.NodeVariables_4006"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Transition_4007 = getElementType("IACdesign.diagram.Transition_4007"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType NodeFather_of_4008 = getElementType("IACdesign.diagram.NodeFather_of_4008"); //$NON-NLS-1$

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
				return IACDiagramEditorPlugin.getInstance()
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

			elements.put(Model_1000, IACPackage.eINSTANCE.getModel());

			elements.put(Variable_2005, IACPackage.eINSTANCE.getVariable());

			elements.put(Node_2006, IACPackage.eINSTANCE.getNode());

			elements.put(NodeVariables_4006,
					IACPackage.eINSTANCE.getNode_Variables());

			elements.put(Transition_4007, IACPackage.eINSTANCE.getTransition());

			elements.put(NodeFather_of_4008,
					IACPackage.eINSTANCE.getNode_Father_of());
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
			KNOWN_ELEMENT_TYPES.add(Model_1000);
			KNOWN_ELEMENT_TYPES.add(Variable_2005);
			KNOWN_ELEMENT_TYPES.add(Node_2006);
			KNOWN_ELEMENT_TYPES.add(NodeVariables_4006);
			KNOWN_ELEMENT_TYPES.add(Transition_4007);
			KNOWN_ELEMENT_TYPES.add(NodeFather_of_4008);
		}
		return KNOWN_ELEMENT_TYPES.contains(elementType);
	}

	/**
	 * @generated
	 */
	public static IElementType getElementType(int visualID) {
		switch (visualID) {
		case ModelEditPart.VISUAL_ID:
			return Model_1000;
		case VariableEditPart.VISUAL_ID:
			return Variable_2005;
		case NodeEditPart.VISUAL_ID:
			return Node_2006;
		case NodeVariablesEditPart.VISUAL_ID:
			return NodeVariables_4006;
		case TransitionEditPart.VISUAL_ID:
			return Transition_4007;
		case NodeFather_ofEditPart.VISUAL_ID:
			return NodeFather_of_4008;
		}
		return null;
	}

}
