package asemedashboardview.views.actions;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import statechart.StatechartPackage;
import SRM.SRMPackage;
import SRM.SRMmodel;
import aseme.transformations.SRM2XPDL;
import aseme.transformations.xpdl.Liveness2XPDLApp;
import asemedashboardview.views.ASEMEAction;
import asemedashboardview.views.ASEMEFacade;
import asemedashboardview.views.ASEMEState;

public class TransformSRM2XPDLModelAction implements ASEMEAction {
	
	private ASEMEFacade context;

	@Override
	public void init(ASEMEFacade context) {
		// TODO Auto-generated method stub
		this.context = context;

	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		ASEMEState state = context.getState();
		
		if(state.getSRM()==null) {
			return false;
		}
		return true;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		ASEMEState state = context.getState();
		
		
		URI srm = state.getSRM();
		String[] tmp = srm.path().split("/");
				
		String filename= state.getProject().getLocation() + "/" + tmp[tmp.length -1];
		
		Liveness2XPDLApp.setFilename(filename);
		
		Liveness2XPDLApp.main(null);
		
		/*
		try {
			state.getProject().refreshLocal(IResource.DEPTH_INFINITE, null);
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
			 

	}

}
