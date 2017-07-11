package SRM.diagram.providers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.IClientSelector;
import org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil;
import org.eclipse.gmf.runtime.notation.View;

import SRM.Activity;
import SRM.Capability;
import SRM.Role;
import SRM.diagram.edit.parts.SRMmodelEditPart;
import SRM.diagram.part.SRMDiagramEditorPlugin;
import SRM.diagram.part.SRMVisualIDRegistry;

/**
 * @generated
 */
public class SRMValidationProvider {

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
				SRMDiagramEditorPlugin.getInstance().logError("Validation failed", e); //$NON-NLS-1$
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
			return constraintsActive && SRMmodelEditPart.MODEL_ID.equals(SRMVisualIDRegistry.getModelID((View) object));
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
	public static class Adapter1 extends AbstractModelConstraint {

		/**
		* @generated NOT
		*/
		public IStatus validate(IValidationContext ctx) {
			Role context = (Role) ctx.getTarget();
			
			String formula = context.getLiveness().replaceAll(" ", "");

			ArrayList<String> left = new ArrayList<String>();
			ArrayList<String> right = new ArrayList<String>();


			Pattern testPattern = Pattern.compile("\\w+_*w*");

			Matcher testMatcher = testPattern.matcher(formula);


			StringTokenizer line = new StringTokenizer(formula, "\n");

			while (line.hasMoreTokens()) {
				String tmp = line.nextToken();
				StringTokenizer formulaElements = new StringTokenizer(tmp, "=");

				left.add(formulaElements.nextToken());
				right.add(formulaElements.nextToken());
			}
			
			boolean added = false;
			
			for( Iterator<Capability> capIter = context.getCapabilities().iterator(); capIter.hasNext();){
				
				Capability tmpCap = capIter.next();
				
				boolean found = false;
				
				for (int i=1; i<left.size(); i++){
					
					if (tmpCap.getName().equals(left.get(i))){
						
						found = true;
						break;
					}
					
				}
				
				if (!found){
					
					String capForm = new String();
					
					if (tmpCap.getDescription()!=null){
						capForm = tmpCap.getName() + "=" + tmpCap.getDescription();
					}
						
					else{
						capForm = tmpCap.getName();
					}
					
					if (context.getLiveness().endsWith("\n")){
						context.setLiveness( context.getLiveness() + capForm+ "\n" );
					}
					else{
						context.setLiveness( context.getLiveness() +"\n"+ capForm+ "\n" );
					}
					
					added = true;
					
				}
			
			}
			
			
			return ctx.createSuccessStatus();
			
		}
	}

	/**
	* @generated
	*/
	static String formatElement(EObject object) {
		return EMFCoreUtil.getQualifiedName(object, true);
	}

}
