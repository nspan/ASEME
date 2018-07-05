package asemedashboardview.views.actions;

import java.io.IOException;
import java.util.LinkedList;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import SRM.SRMmodel;
import SRM.diagram.part.SRMDiagramEditor;
import SRM.diagram.part.SRMDiagramEditorUtil;
import aseme.transformations.AsemeModelSaveHelper;
import aseme.transformations.SRM2IAC;
import asemedashboardview.views.ASEMEAction;
import asemedashboardview.views.ASEMEFacade;
import asemedashboardview.views.ASEMEState;
import statechart.Model;
import statechart.diagram.edit.parts.ModelEditPart;
import statechart.diagram.part.StateChartDiagramEditorPlugin;

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
		
//		ResourceSet resourceSet = new ResourceSetImpl();
//		// Register the appropriate resource factory to handle all file
//		// extensions.
//		//
//				
//		resourceSet
//				.getResourceFactoryRegistry()
//				.getExtensionToFactoryMap()
//				.put(Resource.Factory.Registry.DEFAULT_EXTENSION,
//						new XMIResourceFactoryImpl());
//		
//		// Register the package to ensure it is available during loading.
//		//
//		resourceSet.getPackageRegistry().put(SRMPackage.eNS_URI, SRMPackage.eINSTANCE);
//		resourceSet.getPackageRegistry().put(StatechartPackage.eNS_URI, StatechartPackage.eINSTANCE);
		
		// load SRM model
					
		Resource resource = AsemeModelSaveHelper.staticResourceSet.getResource(state.getSRM(), true);	
		SRMmodel srmModel = (SRMmodel) resource.getContents().get(0);
					
		
			LinkedList<Model> statecharts = new LinkedList<Model>();
			
			try{
				statecharts = SRM2IAC.transformSrm2Eac(srmModel);
			}
			catch( Exception e){
				MessageDialog.openError(context.getShell(), "Error", "Malformed liveness formula!" );
			}
			
			
			
			for (int i=0; i<statecharts.size(); i++){
				
				Model statechart = statecharts.get(i);
				statechart = SRM2IAC.postprocessing(srmModel, statechart, state.getProject());
				
				String filename= "/" + state.getProject().getName() + "/" + statechart.getName()+".stct";
				
				AsemeModelSaveHelper.saveFilename(statechart, filename);
				
				Diagram diagram = ViewService.createDiagram(statechart,
						ModelEditPart.MODEL_ID,
						StateChartDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
				
				String diagName= "/" + state.getProject().getName() + "/" + statechart.getName()+".kse";
				
				URI diag = URI.createURI(diagName);
				
				AsemeModelSaveHelper.saveFilename(diagram, diagName);
				
			}
			
			try {
				state.getProject().refreshLocal(IResource.DEPTH_INFINITE, null);
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	//	}
			
			
			MessageDialog.openInformation(context.getShell(), "ASEME", "SRM2IAC transformation completed successfully!" );

	}
	
	private void createDiagram(SRMmodel srmm, URI diag){
		Diagram diagram = ViewService.createDiagram(srmm,
				ModelEditPart.MODEL_ID,
				StateChartDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
		
		srmm.eResource().getContents().add(diagram);
		
		try {
			srmm.eResource().save(SRMDiagramEditorUtil.getSaveOptions());
		} catch (IOException e) {
			//FormDiagramEditorPlugin.getInstance().logError(
				//	"Save operation failed for: " + task.eResource(), e); //$NON-NLS-1$
			System.out.println("Save Diagram my ass!!!");
		}
		
		
		AsemeModelSaveHelper.saveURI(srmm.eResource().getContents().get(1), diag);
		
		try {
			PlatformUI.getWorkbench().getActiveWorkbenchWindow()
				.getActivePage().openEditor(new URIEditorInput(diag), SRMDiagramEditor.ID);
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

	
	
}
