package statechart.diagram.providers;

import javax.swing.JOptionPane;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.IClientSelector;
import org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil;
import org.eclipse.gmf.runtime.notation.View;

import statechart.Model;
import statechart.Node;
import statechart.Transition;
import statechart.diagram.edit.parts.ModelEditPart;
import statechart.diagram.part.StateChartDiagramEditorPlugin;
import statechart.diagram.part.StateChartVisualIDRegistry;

/**
 * @generated
 */
public class StateChartValidationProvider {

	/**
	* @generated
	*/
	private static boolean constraintsActive = false;

	/**
	* @generated
	*/
	public static boolean shouldConstraintsBePrivate() {
		return false;
	}

	/**
	* @generated
	*/
	public static void runWithConstraints(TransactionalEditingDomain editingDomain, Runnable operation) {
		final Runnable op = operation;
		Runnable task = new Runnable() {
			public void run() {
				try {
					constraintsActive = true;
					op.run();
				} finally {
					constraintsActive = false;
				}
			}
		};
		if (editingDomain != null) {
			try {
				editingDomain.runExclusive(task);
			} catch (Exception e) {
				StateChartDiagramEditorPlugin.getInstance().logError("Validation failed", e); //$NON-NLS-1$
			}
		} else {
			task.run();
		}
	}

	/**
	* @generated
	*/
	static boolean isInDefaultEditorContext(Object object) {
		if (shouldConstraintsBePrivate() && !constraintsActive) {
			return false;
		}
		if (object instanceof View) {
			return constraintsActive
					&& ModelEditPart.MODEL_ID.equals(StateChartVisualIDRegistry.getModelID((View) object));
		}
		return true;
	}

	/**
	* @generated
	*/
	public static class DefaultCtx implements IClientSelector {

		/**
		* @generated
		*/
		public boolean selects(Object object) {
			return isInDefaultEditorContext(object);
		}
	}

	/**
	 * @generated NOT
	 */
	// STATECHART RULE: More than one root
	public static class Adapter1 extends AbstractModelConstraint {

		/**
		 * @generated NOT
		 */
		public IStatus validate(IValidationContext ctx) {
			Model context = (Model) ctx.getTarget();

			if (context.getNodes().size() > 1) {
				JOptionPane.showMessageDialog(null, "Failure Only one root node per model");
				System.out.println("Failure Only one root node per model");
				return ctx.createFailureStatus(0);

			} else
				return ctx.createSuccessStatus();
			/**
			 * LIVE VALIDATION
			 * */
		}
	}

	/**
	 * @generated NOT
	 */
	// STATECHART RULE: AND node's children different than OR 
	public static class Adapter2 extends AbstractModelConstraint {

		/**
		 * @generated NOT
		 */
		public IStatus validate(IValidationContext ctx) {
			Node context = (Node) ctx.getTarget();
			if (context.getType().equals("AND")) {
				for (int i = 0; i < context.getChildren().size(); i++) {
					if (!context.getChildren().get(i).getType().equals("OR")) {
						System.out.println("Failure And Can Have Only Or children");
						JOptionPane.showMessageDialog(null, "Failure And Can Have Only Or children");
						return ctx.createFailureStatus(0);
					}
				}
			}
			return ctx.createSuccessStatus();
			/**
			 * LIVE VALIDATION
			 * */
		}
	}

	/**
	 * @generated NOT
	 */
	// STATECHART RULE: Transition can't have START as a target
	public static class Adapter3 extends AbstractModelConstraint {

		/**
		 * @generated NOT
		 */
		public IStatus validate(IValidationContext ctx) {
			Transition context = (Transition) ctx.getTarget();
			if (context.getTarget() != null && context.getTarget().getType().equals("START")) {
				System.out.println("Failure start can't be target");

				//JOptionPane
				//			.showMessageDialog(
				//				null,
				//			context.getTarget().getName()
				//				+ " is a START Node so it shouldn't be target node for a transition");
				return ctx.createFailureStatus(1);
			} else
				return ctx.createSuccessStatus();
		}
	}

	/**
	 * @generated NOT
	 */
	// STATECHART RULE: Transition can't have END as a source
	public static class Adapter4 extends AbstractModelConstraint {

		/**
		 * @generated NOT
		 */
		public IStatus validate(IValidationContext ctx) {
			Transition context = (Transition) ctx.getTarget();
			if (context.getSource() != null && context.getSource().getType().equals("END")) {
				System.out.println("Failure End can't be source");
				//	JOptionPane
				//		.showMessageDialog(
				//			null,
				//		context.getTarget().getName()
				//			+ " is a END Node so it shouldn't be source node for a transition");
				return ctx.createFailureStatus(1);
			} else
				return ctx.createSuccessStatus();
		}
	}

	/**
	 * @generated NOT 
	 */
	// STATECHART RULE: Transition can't have source and target from different father WARNING
	public static class Adapter5 extends AbstractModelConstraint {

		/**
		 * @generated NOT
		 */

		public IStatus validate(IValidationContext ctx) {
			Transition context = (Transition) ctx.getTarget();
			if (context.getSource() != null && context.getTarget() != null) {
				if ((context.getSource().getFather() != null && context.getTarget().getFather() != null
						&& !context.getSource().getFather().equals(context.getTarget().getFather()))) {

					System.out.println("Failure Different Father");
					System.out.println("Target: " + context.getTarget().getName() + " Father_of: "
							+ context.getTarget().getFather().getName());
					System.out.println("Source: " + context.getSource().getName() + " Father_of: "
							+ context.getSource().getFather().getName());
					//	JOptionPane
					//		.showMessageDialog(
					//			null,
					//		context.getName()
					//			+ " is a transition between two nodes of a different parent.\nIt is recommended the source and target node of a transition to have the same parent node.");
					return ctx.createFailureStatus(0);
				} else if (context.getSource().getFather() == null || context.getTarget().getFather() == null) {
					System.out.println("Failure No Transition Father");
					//	JOptionPane
					//		.showMessageDialog(
					//			null,
					//		context.getName()
					//			+ " is a transition between two nodes of a different parent.\nIt is recommended the source and target node of a transition to have the same parent node.");
					return ctx.createFailureStatus(0);
				}
				return ctx.createSuccessStatus();
			} else
				return ctx.createSuccessStatus();
		}
	}

	/**
	 * @generated NOT
	 */
	// STATECHART RULE: An OR node can have only one START node
	public static class Adapter6 extends AbstractModelConstraint {

		/**
		 * @generated NOT 
		 */
		public IStatus validate(IValidationContext ctx) {
			Node context = (Node) ctx.getTarget();
			int count = 0;
			for (int i = 0; i < context.getChildren().size(); i++) {
				if (context.getType().equals("OR") && ((Node) context.getChildren().get(i)).getType().equals("START")) {
					count++;
				}
			}
			if (count > 1) {
				{
					JOptionPane.showMessageDialog(null, "Failure OR Node Can Have Only one START node as a child");
				}
				return ctx.createFailureStatus();

			} else
				return ctx.createSuccessStatus();
			/**
			 * LIVE VALIDATION
			 * */
		}
	}

	/**
	 * @generated NOT
	 */
	// STATECHART RULE: An OR node can have only one END node
	public static class Adapter7 extends AbstractModelConstraint {

		/**
		 * @generated NOT
		 */
		public IStatus validate(IValidationContext ctx) {
			Node context = (Node) ctx.getTarget();
			int count = 0;
			for (int i = 0; i < context.getChildren().size(); i++) {
				if (context.getType().equals("OR") && ((Node) context.getChildren().get(i)).getType().equals("END")) {
					count++;
				}
			}
			if (count > 1) {
				JOptionPane.showMessageDialog(null, "Failure OR Node Can Have Only one END node as a child");
				return ctx.createFailureStatus();
			} else
				return ctx.createSuccessStatus();
			/**
			 * LIVE VALIDATION
			 * */
		}
	}

	/**
	* @generated
	*/
	static String formatElement(EObject object) {
		return EMFCoreUtil.getQualifiedName(object, true);
	}

}
