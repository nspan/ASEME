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
import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import statechart.Model;
import statechart.Node;
import statechart.StatechartFactory;
import statechart.StatechartPackage;
import statechart.Transition;
import statechart.diagram.edit.parts.ModelEditPart;
import statechart.diagram.part.StateChartDiagramEditorPlugin;
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
		this.context = context;
	}

	@Override
	public boolean isEnabled() {
		ASEMEState state = context.getState();
		if(state.getAIP()==null) {
			return false;
		}
		return true;
	}

	@Override
	public void run() {
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
					
		Resource resource = resourceSet.getResource(state.getAIP(), true);	
		AIPmodel aipModel = (AIPmodel) resource.getContents().get(0);		
		
		
		LinkedList<Model> statecharts = new LinkedList<Model>();
	
		try{
			statecharts = AIP2EAC.transformAip2Eac(aipModel);
		}
		catch( Exception e){
			MessageDialog.openError(context.getShell(), "Error", "Malformed liveness formula! Each formula must have at least one operator!" );
		}
		
		for (int i=0; i<statecharts.size(); i++){
			
			Model statechart = statecharts.get(i);
			
			String filename="/"+state.getProject().getName() + "/" + statechart.getName()+".stct";	//new File( //.getAbsolutePath();
			AsemeModelSaveHelper.saveFilename(statechart, filename);
			
			Diagram diagram = ViewService.createDiagram(statechart,
					ModelEditPart.MODEL_ID,
					StateChartDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
			
			String diagName= "/" + state.getProject().getName() + "/" + statechart.getName()+".kse";
			
			//URI diag = URI.createURI(diagName);
			
			AsemeModelSaveHelper.saveFilename(diagram, diagName);		
			
		}
		
		try {
			state.getProject().refreshLocal(IResource.DEPTH_INFINITE, null);
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
						
	}
	
	
}
