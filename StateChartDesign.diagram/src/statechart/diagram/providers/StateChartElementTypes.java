package statechart.diagram.providers;

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

import statechart.StatechartPackage;
import statechart.diagram.edit.parts.ModelEditPart;
import statechart.diagram.edit.parts.Node10EditPart;
import statechart.diagram.edit.parts.Node11EditPart;
import statechart.diagram.edit.parts.Node12EditPart;
import statechart.diagram.edit.parts.Node2EditPart;
import statechart.diagram.edit.parts.Node3EditPart;
import statechart.diagram.edit.parts.Node4EditPart;
import statechart.diagram.edit.parts.Node5EditPart;
import statechart.diagram.edit.parts.Node6EditPart;
import statechart.diagram.edit.parts.Node7EditPart;
import statechart.diagram.edit.parts.Node8EditPart;
import statechart.diagram.edit.parts.Node9EditPart;
import statechart.diagram.edit.parts.NodeEditPart;
import statechart.diagram.edit.parts.TransitionEditPart;
import statechart.diagram.edit.parts.VariableEditPart;
import statechart.diagram.part.StateChartDiagramEditorPlugin;

/**
 * @generated
 */
public class StateChartElementTypes {

	/**
	* @generated
	*/
	private StateChartElementTypes() {
	}

	/**
	* @generated
	*/
	private static Map<IElementType, ENamedElement> elements;

	/**
	* @generated
	*/
	private static DiagramElementTypeImages elementTypeImages = new DiagramElementTypeImages(
			StateChartDiagramEditorPlugin.getInstance().getItemProvidersAdapterFactory());

	/**
	* @generated
	*/
	private static Set<IElementType> KNOWN_ELEMENT_TYPES;

	/**
	* @generated
	*/
	public static final IElementType Model_1000 = getElementType("StateChartDesign.diagram.Model_1000"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType Node_2001 = getElementType("StateChartDesign.diagram.Node_2001"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType Node_2002 = getElementType("StateChartDesign.diagram.Node_2002"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType Variable_2003 = getElementType("StateChartDesign.diagram.Variable_2003"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType Node_2004 = getElementType("StateChartDesign.diagram.Node_2004"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType Node_2005 = getElementType("StateChartDesign.diagram.Node_2005"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType Node_2006 = getElementType("StateChartDesign.diagram.Node_2006"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType Node_2007 = getElementType("StateChartDesign.diagram.Node_2007"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType Node_3001 = getElementType("StateChartDesign.diagram.Node_3001"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType Node_3002 = getElementType("StateChartDesign.diagram.Node_3002"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType Node_3003 = getElementType("StateChartDesign.diagram.Node_3003"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType Node_3004 = getElementType("StateChartDesign.diagram.Node_3004"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType Node_3005 = getElementType("StateChartDesign.diagram.Node_3005"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType Node_3006 = getElementType("StateChartDesign.diagram.Node_3006"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType Transition_4001 = getElementType("StateChartDesign.diagram.Transition_4001"); //$NON-NLS-1$

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

			elements.put(Model_1000, StatechartPackage.eINSTANCE.getModel());

			elements.put(Node_2001, StatechartPackage.eINSTANCE.getNode());

			elements.put(Node_2002, StatechartPackage.eINSTANCE.getNode());

			elements.put(Variable_2003, StatechartPackage.eINSTANCE.getVariable());

			elements.put(Node_2004, StatechartPackage.eINSTANCE.getNode());

			elements.put(Node_2005, StatechartPackage.eINSTANCE.getNode());

			elements.put(Node_2006, StatechartPackage.eINSTANCE.getNode());

			elements.put(Node_2007, StatechartPackage.eINSTANCE.getNode());

			elements.put(Node_3001, StatechartPackage.eINSTANCE.getNode());

			elements.put(Node_3002, StatechartPackage.eINSTANCE.getNode());

			elements.put(Node_3003, StatechartPackage.eINSTANCE.getNode());

			elements.put(Node_3004, StatechartPackage.eINSTANCE.getNode());

			elements.put(Node_3005, StatechartPackage.eINSTANCE.getNode());

			elements.put(Node_3006, StatechartPackage.eINSTANCE.getNode());

			elements.put(Transition_4001, StatechartPackage.eINSTANCE.getTransition());
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
			KNOWN_ELEMENT_TYPES.add(Node_2001);
			KNOWN_ELEMENT_TYPES.add(Node_2002);
			KNOWN_ELEMENT_TYPES.add(Variable_2003);
			KNOWN_ELEMENT_TYPES.add(Node_2004);
			KNOWN_ELEMENT_TYPES.add(Node_2005);
			KNOWN_ELEMENT_TYPES.add(Node_2006);
			KNOWN_ELEMENT_TYPES.add(Node_2007);
			KNOWN_ELEMENT_TYPES.add(Node_3001);
			KNOWN_ELEMENT_TYPES.add(Node_3002);
			KNOWN_ELEMENT_TYPES.add(Node_3003);
			KNOWN_ELEMENT_TYPES.add(Node_3004);
			KNOWN_ELEMENT_TYPES.add(Node_3005);
			KNOWN_ELEMENT_TYPES.add(Node_3006);
			KNOWN_ELEMENT_TYPES.add(Transition_4001);
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
		case NodeEditPart.VISUAL_ID:
			return Node_2001;
		case Node2EditPart.VISUAL_ID:
			return Node_2002;
		case VariableEditPart.VISUAL_ID:
			return Variable_2003;
		case Node3EditPart.VISUAL_ID:
			return Node_2004;
		case Node4EditPart.VISUAL_ID:
			return Node_2005;
		case Node5EditPart.VISUAL_ID:
			return Node_2006;
		case Node6EditPart.VISUAL_ID:
			return Node_2007;
		case Node7EditPart.VISUAL_ID:
			return Node_3001;
		case Node8EditPart.VISUAL_ID:
			return Node_3002;
		case Node9EditPart.VISUAL_ID:
			return Node_3003;
		case Node10EditPart.VISUAL_ID:
			return Node_3004;
		case Node11EditPart.VISUAL_ID:
			return Node_3005;
		case Node12EditPart.VISUAL_ID:
			return Node_3006;
		case TransitionEditPart.VISUAL_ID:
			return Transition_4001;
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
			return statechart.diagram.providers.StateChartElementTypes.isKnownElementType(elementType);
		}

		/**
		* @generated
		*/
		@Override

		public IElementType getElementTypeForVisualId(int visualID) {
			return statechart.diagram.providers.StateChartElementTypes.getElementType(visualID);
		}

		/**
		* @generated
		*/
		@Override

		public ENamedElement getDefiningNamedElement(IAdaptable elementTypeAdapter) {
			return statechart.diagram.providers.StateChartElementTypes.getElement(elementTypeAdapter);
		}
	};

}
