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
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import statechart.Model;
import statechart.Node;
import statechart.StatechartFactory;
import statechart.StatechartPackage;
import statechart.Transition;
import AIP.AIPPackage;
import AIP.AIPmodel;
import AIP.Participant;
import AIP.Protocol;
import SAG.Actor;
import SAG.SAGPackage;
import SAG.SAGmodel;
import SRM.SRMFactory;
import SRM.SRMmodel;
import SUC.SUCPackage;
import aseme.transformations.AIP2EAC;
import aseme.transformations.AsemeModelSaveHelper;
import asemedashboardview.views.ASEMEAction;
import asemedashboardview.views.ASEMEFacade;
import asemedashboardview.views.ASEMEState;



public class TransformAIP2EACModelAction implements ASEMEAction {

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
		if(state.getAIP()==null) {
			return false;
		}
		return true;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		ASEMEState state = context.getState(); 
		URI aip = state.getAIP();
		URI eac = state.getEAC();
		if (eac == null) {
			eac = aip.trimFileExtension().appendFileExtension("stct"); //$NON-NLS-1$
			//state.setEAC(eac);
		}
		
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
		resourceSet.getPackageRegistry().put(AIPPackage.eNS_URI, AIPPackage.eINSTANCE);
		//resourceSet.getPackageRegistry().put(StatechartPackage.eNS_URI, StatechartPackage.eINSTANCE);
		
		
		// load AIP model
		
		//resourceSet.getPackageRegistry().put(StatechartPackage.eNS_URI,
							//StatechartPackage.eINSTANCE);
					
		Resource resource = resourceSet.getResource(state.getAIP(), true);	
		AIPmodel aipModel = (AIPmodel) resource.getContents().get(0);
					
		//resource = resourceSet.createResource(URI
			//				.createURI(StatechartPackage.eNS_URI));
		//Resource newResource = resourceSet.createResource(state.getEAC());			
		
		
		LinkedList<Model> statecharts = new LinkedList<Model>();
		//AIP2EAC trans = new AIP2EAC();
		try{
			statecharts = AIP2EAC.transformAip2Eac(aipModel);
		}
		catch( Exception e){
			MessageDialog.openError(context.getShell(), "Error", "Malformed liveness formula!" );
		}
		
		
		//AsemeModelSaveHelper helper = new AsemeModelSaveHelper();
		//IPath path = state.getProject().getLocation();
		
		//System.out.println(state.getProject().getName());
		
		for (int i=0; i<statecharts.size(); i++){
			
			Model statechart = statecharts.get(i);
			
			String filename="/"+state.getProject().getName() + "/" + statechart.getName()+".stct";	//new File( //.getAbsolutePath();
			
			//URI name = URI.createPlatformResourceURI(filename, false);
			//AsemeModelSaveHelper.saveURI(statechart, name);
			AsemeModelSaveHelper.saveFilename(statechart, filename);
			
			
		}
		
		try {
			state.getProject().refreshLocal(IResource.DEPTH_INFINITE, null);
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
						
	}
	
	
}
