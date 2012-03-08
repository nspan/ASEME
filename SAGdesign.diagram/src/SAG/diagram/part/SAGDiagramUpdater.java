package SAG.diagram.part;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.notation.View;

import SAG.Actor;
import SAG.Goal;
import SAG.SAGPackage;
import SAG.SAGmodel;
import SAG.diagram.edit.parts.ActorEditPart;
import SAG.diagram.edit.parts.ActorMy_goalEditPart;
import SAG.diagram.edit.parts.GoalDependeeEditPart;
import SAG.diagram.edit.parts.GoalEditPart;
import SAG.diagram.edit.parts.SAGmodelEditPart;
import SAG.diagram.providers.SAGElementTypes;

/**
 * @generated
 */
public class SAGDiagramUpdater {

	/**
	 * @generated
	 */
	public static List<SAGNodeDescriptor> getSemanticChildren(View view) {
		switch (SAGVisualIDRegistry.getVisualID(view)) {
		case SAGmodelEditPart.VISUAL_ID:
			return getSAGmodel_1000SemanticChildren(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<SAGNodeDescriptor> getSAGmodel_1000SemanticChildren(
			View view) {
		if (!view.isSetElement()) {
			return Collections.emptyList();
		}
		SAGmodel modelElement = (SAGmodel) view.getElement();
		LinkedList<SAGNodeDescriptor> result = new LinkedList<SAGNodeDescriptor>();
		for (Iterator<?> it = modelElement.getActors().iterator(); it.hasNext();) {
			Actor childElement = (Actor) it.next();
			int visualID = SAGVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == ActorEditPart.VISUAL_ID) {
				result.add(new SAGNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator<?> it = modelElement.getGoals().iterator(); it.hasNext();) {
			Goal childElement = (Goal) it.next();
			int visualID = SAGVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == GoalEditPart.VISUAL_ID) {
				result.add(new SAGNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<SAGLinkDescriptor> getContainedLinks(View view) {
		switch (SAGVisualIDRegistry.getVisualID(view)) {
		case SAGmodelEditPart.VISUAL_ID:
			return getSAGmodel_1000ContainedLinks(view);
		case ActorEditPart.VISUAL_ID:
			return getActor_2007ContainedLinks(view);
		case GoalEditPart.VISUAL_ID:
			return getGoal_2008ContainedLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<SAGLinkDescriptor> getIncomingLinks(View view) {
		switch (SAGVisualIDRegistry.getVisualID(view)) {
		case ActorEditPart.VISUAL_ID:
			return getActor_2007IncomingLinks(view);
		case GoalEditPart.VISUAL_ID:
			return getGoal_2008IncomingLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<SAGLinkDescriptor> getOutgoingLinks(View view) {
		switch (SAGVisualIDRegistry.getVisualID(view)) {
		case ActorEditPart.VISUAL_ID:
			return getActor_2007OutgoingLinks(view);
		case GoalEditPart.VISUAL_ID:
			return getGoal_2008OutgoingLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<SAGLinkDescriptor> getSAGmodel_1000ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<SAGLinkDescriptor> getActor_2007ContainedLinks(View view) {
		Actor modelElement = (Actor) view.getElement();
		LinkedList<SAGLinkDescriptor> result = new LinkedList<SAGLinkDescriptor>();
		result.addAll(getOutgoingFeatureModelFacetLinks_Actor_My_goal_4011(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<SAGLinkDescriptor> getGoal_2008ContainedLinks(View view) {
		Goal modelElement = (Goal) view.getElement();
		LinkedList<SAGLinkDescriptor> result = new LinkedList<SAGLinkDescriptor>();
		result.addAll(getOutgoingFeatureModelFacetLinks_Goal_Dependee_4013(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<SAGLinkDescriptor> getActor_2007IncomingLinks(View view) {
		Actor modelElement = (Actor) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<SAGLinkDescriptor> result = new LinkedList<SAGLinkDescriptor>();
		result.addAll(getIncomingFeatureModelFacetLinks_Goal_Dependee_4013(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<SAGLinkDescriptor> getGoal_2008IncomingLinks(View view) {
		Goal modelElement = (Goal) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<SAGLinkDescriptor> result = new LinkedList<SAGLinkDescriptor>();
		result.addAll(getIncomingFeatureModelFacetLinks_Actor_My_goal_4011(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<SAGLinkDescriptor> getActor_2007OutgoingLinks(View view) {
		Actor modelElement = (Actor) view.getElement();
		LinkedList<SAGLinkDescriptor> result = new LinkedList<SAGLinkDescriptor>();
		result.addAll(getOutgoingFeatureModelFacetLinks_Actor_My_goal_4011(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<SAGLinkDescriptor> getGoal_2008OutgoingLinks(View view) {
		Goal modelElement = (Goal) view.getElement();
		LinkedList<SAGLinkDescriptor> result = new LinkedList<SAGLinkDescriptor>();
		result.addAll(getOutgoingFeatureModelFacetLinks_Goal_Dependee_4013(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<SAGLinkDescriptor> getIncomingFeatureModelFacetLinks_Actor_My_goal_4011(
			Goal target,
			Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<SAGLinkDescriptor> result = new LinkedList<SAGLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences
				.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() == SAGPackage.eINSTANCE
					.getActor_My_goal()) {
				result.add(new SAGLinkDescriptor(setting.getEObject(), target,
						SAGElementTypes.ActorMy_goal_4011,
						ActorMy_goalEditPart.VISUAL_ID));
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<SAGLinkDescriptor> getIncomingFeatureModelFacetLinks_Goal_Dependee_4013(
			Actor target,
			Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<SAGLinkDescriptor> result = new LinkedList<SAGLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences
				.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() == SAGPackage.eINSTANCE
					.getGoal_Dependee()) {
				result.add(new SAGLinkDescriptor(setting.getEObject(), target,
						SAGElementTypes.GoalDependee_4013,
						GoalDependeeEditPart.VISUAL_ID));
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<SAGLinkDescriptor> getOutgoingFeatureModelFacetLinks_Actor_My_goal_4011(
			Actor source) {
		LinkedList<SAGLinkDescriptor> result = new LinkedList<SAGLinkDescriptor>();
		for (Iterator<?> destinations = source.getMy_goal().iterator(); destinations
				.hasNext();) {
			Goal destination = (Goal) destinations.next();
			result.add(new SAGLinkDescriptor(source, destination,
					SAGElementTypes.ActorMy_goal_4011,
					ActorMy_goalEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<SAGLinkDescriptor> getOutgoingFeatureModelFacetLinks_Goal_Dependee_4013(
			Goal source) {
		LinkedList<SAGLinkDescriptor> result = new LinkedList<SAGLinkDescriptor>();
		for (Iterator<?> destinations = source.getDependee().iterator(); destinations
				.hasNext();) {
			Actor destination = (Actor) destinations.next();
			result.add(new SAGLinkDescriptor(source, destination,
					SAGElementTypes.GoalDependee_4013,
					GoalDependeeEditPart.VISUAL_ID));
		}
		return result;
	}

}
