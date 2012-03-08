package asemedashboardview.views.actions;



import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gmf.internal.bridge.transform.TransformOptions;
import org.eclipse.gmf.internal.bridge.transform.TransformToGenModelOperation;
import org.eclipse.jface.dialogs.ErrorDialog;

import asemedashboardview.views.ASEMEAction;
import asemedashboardview.views.ASEMEFacade;
import asemedashboardview.views.ASEMEState;


public class TransformMap2GenModelAction implements ASEMEAction {

	@Override
	public void init(ASEMEFacade context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
//
//	private ASEMEFacade context;
//
//	public void init(ASEMEFacade context) {
//		this.context = context;
//	}
//
//	public boolean isEnabled() {
//		ASEMEState state = context.getState();
//		if (context.isStrict()) {
//			if (state.getDM() == null || state.getDGM() == null || state.getTDM() == null) {
//				return false;
//			}
//		}
//		return state.getMM() != null;
//	}
//
//	public void run() {
//		ASEMEState state = context.getState();
//		URI mm = state.getMM();
//		URI gm = state.getGM();
//		if (gm == null) {
//			gm = mm.trimFileExtension().appendFileExtension("gmfgen"); //$NON-NLS-1$
//			state.setGM(gm);
//		}
//		IStatus result = Status.OK_STATUS;
//		try {
//			final ResourceSet rs = new ResourceSetImpl();
//			TransformToGenModelOperation op = new TransformToGenModelOperation(rs);
//			configureOptions(op.getOptions());
//			op.loadMappingModel(mm, new NullProgressMonitor());
//			op.loadGenModel(state.getDGM(), new NullProgressMonitor());
//			op.setGenURI(gm);
//			result = op.executeTransformation(new NullProgressMonitor());
//		} catch (CoreException ce) {
//			result = ce.getStatus();
//		} finally {
//			context.updateStatus();
//		}
//		ErrorDialog.openError(context.getShell(), null, null, result, IStatus.ERROR | IStatus.WARNING);
//	}
//
//	protected void configureOptions(TransformOptions options) {
//		options.setUseRuntimeFigures(true);
//		options.setUseMapMode(true);
//		options.setGenerateRCP(context.getState().getOption(ASEMEFacade.OPTION_RCP));
//	}
}
