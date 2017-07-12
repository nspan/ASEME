package asemedashboardview.views.actions;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.dialogs.MessageDialog;

import aseme.transformations.xpdl.Liveness2XPDLApp;
import asemedashboardview.views.ASEMEAction;
import asemedashboardview.views.ASEMEFacade;
import asemedashboardview.views.ASEMEState;

public class TransformSRM2XPDLModelAction implements ASEMEAction {
	
	private ASEMEFacade context;

	@Override
	public void init(ASEMEFacade context) {
		
		this.context = context;

	}

	@Override
	public boolean isEnabled() {
		
		ASEMEState state = context.getState();
		
		if(state.getSRM()==null) {
			return false;
		}
		return true;
	}

	@Override
	public void run() {
		
		ASEMEState state = context.getState();
		
		// create platform independent filename from the given URI
		URI srm = state.getSRM();
		String[] tmp = srm.path().split("/");
				
		String filename= state.getProject().getLocation() + "/" + tmp[tmp.length -1];
		
		//System.out.println(filename);
		
		// Adding functionality from the SRM2XPDL tool
		
		//Liveness2XPDLApp.setFilename(filename);
		
		
		
		try {
			Liveness2XPDLApp.main(new String[] {filename});
		} catch (Exception e) {
			
			MessageDialog.openError(context.getShell(), "Error", "Something went wrong!" );
		}
		
		
		// Updating the Project explorer, making the generated XPDL/BPMN files visible
		
		try {
			state.getProject().refreshLocal(IResource.DEPTH_INFINITE, null);
		} catch (CoreException e) {
			
			MessageDialog.openError(context.getShell(), "Error", "Something went wrong updating project explorer!" );
		}
			 

	}

}
