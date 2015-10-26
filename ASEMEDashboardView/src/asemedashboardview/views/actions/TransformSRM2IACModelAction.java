package asemedashboardview.views.actions;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.jface.dialogs.MessageDialog;

import statechart.Model;
import statechart.StatechartPackage;
import AIP.AIPPackage;
import AIP.AIPmodel;
import SRM.SRMPackage;
import SRM.SRMmodel;
import aseme.transformations.AIP2EAC;
import aseme.transformations.AsemeModelSaveHelper;
import aseme.transformations.SRM2EAC;
import asemedashboardview.views.ASEMEAction;
import asemedashboardview.views.ASEMEFacade;
import asemedashboardview.views.ASEMEState;

public class TransformSRM2IACModelAction implements ASEMEAction {
	
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
		
		ResourceSet resourceSet = new ResourceSetImpl();
		// Register the appropriate resource factory to handle all file
		// extensions.
		//
		
		
		resourceSet
				.getResourceFactoryRegistry()
				.getExtensionToFactoryMap()
				.put(Resource.Factory.Registry.DEFAULT_EXTENSION,
						new XMIResourceFactoryImpl());
		
		// Register the package to ensure it is available during loading.
		//
		resourceSet.getPackageRegistry().put(SRMPackage.eNS_URI, SRMPackage.eINSTANCE);
		resourceSet.getPackageRegistry().put(StatechartPackage.eNS_URI, StatechartPackage.eINSTANCE);
		
		// load SRM model
					
		Resource resource = resourceSet.getResource(state.getSRM(), true);	
		SRMmodel srmModel = (SRMmodel) resource.getContents().get(0);
					
		//resource = resourceSet.createResource(URI
			//				.createURI(SRMPackage.eNS_URI));
		
		LinkedList<Model> statecharts = new LinkedList<Model>();
		//SRM2EAC trans = new SRM2EAC();
		
		try{
			statecharts = SRM2EAC.transformSrm2Eac(srmModel);
		}
		catch( Exception e){
			MessageDialog.openError(context.getShell(), "Error", "Malformed liveness formula!" );
		}
		
		//String[] score = srmModel.getCapabilities().get(0).getName().split("_");
		//IFile temp = state.getProject().getFile(score[0]+".stct");
		
		/*if ( temp != null){
			
			//System.out.println(state.getProject().getLocation()+"/"+score[0]+".stct");
			//Resource res = resourceSet.createResource(URI.createURI("/"+state.getProject().getName() + "/" + score[0]+".stct"));
			Resource res = resourceSet.getResource(URI.createPlatformResourceURI(temp.getFullPath().toString(), true), true);
			Model mod = (Model)res.getContents().get(0);
			
			
		}*/
		
		
		for (int i=0; i<statecharts.size(); i++){
			
			Model statechart = statecharts.get(i);
			statechart = SRM2EAC.postprocessing(srmModel, statechart, state.getProject());
			//System.out.println("RETURN OF THE JEDI!!!");
			//String filename=new File(state.getProject().getName() + "/" + statechart.getName()+".stct").getAbsolutePath();
			String filename= "/" + state.getProject().getName() + "/" + statechart.getName()+".stct";
			//System.out.println(filename);
			AsemeModelSaveHelper.saveFilename(statechart, filename);
		}
		
		try {
			state.getProject().refreshLocal(IResource.DEPTH_INFINITE, null);
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//state.getProject()
		

	}

	

	
	
}
