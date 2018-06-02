package asemedashboardview.views.actions;

import java.util.Objects;

import javax.swing.JOptionPane;

import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import AIP.AIPPackage;
import AIP.AIPmodel;
import AIP.diagram.edit.parts.AIPmodelEditPart;
import AIP.diagram.part.AIPDiagramEditor;
import AIP.diagram.part.AIPDiagramEditorPlugin;
import SUC.SUCPackage;
import SUC.SUCmodel;
import aseme.transformations.AsemeModelSaveHelper;
import aseme.transformations.SUC2AIP;
import asemedashboardview.views.ASEMEAction;
import asemedashboardview.views.ASEMEFacade;
import asemedashboardview.views.ASEMEState;

public class TransformSUC2AIPModelAction implements ASEMEAction {

	private ASEMEFacade context;

	@Override
	public void init(ASEMEFacade context) {
		this.context = context;
	}

	@Override
	public boolean isEnabled() {
		ASEMEState state = context.getState();
		if (state.getSUC() == null) {
			return false;
		}
		return true;
	}

	@Override
	public void run() {
		ASEMEState state = context.getState();
		URI suc = state.getSUC();
		URI aip = state.getAIP();

		if (aip == null) {
			aip = suc.trimFileExtension().appendFileExtension("aip"); //$NON-NLS-1$
			state.setAIP(aip);
		} else {

			boolean quest = MessageDialog.openQuestion(context.getShell(), "AIP model exists!",
					aip.toString() + " already exists. Do you want to overwrite?");

			if (!quest) {
				String aipName = suc.trimFileExtension().toString() + "_0";

				String[] aipPathParts = aipName.split("/");

				String newName = JOptionPane.showInputDialog("Give name", aipPathParts[aipPathParts.length - 1]);

				if (Objects.isNull(newName)) {
					return;
				}

				aipPathParts[aipPathParts.length - 1] = newName;

				String reconstructedPath = "";

				for (int i = 0; i < aipPathParts.length; i++) {

					reconstructedPath += aipPathParts[i] + "/";
				}

				reconstructedPath = reconstructedPath.substring(0, reconstructedPath.length() - 1);

				aip = URI.createURI(reconstructedPath).appendFileExtension("aip");

			}
		}

		ResourceSet resourceSet = new ResourceSetImpl();

		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());

		// Register the package to ensure it is available during loading.
		//
		resourceSet.getPackageRegistry().put(SUCPackage.eNS_URI, SUCPackage.eINSTANCE);
		resourceSet.getPackageRegistry().put(AIPPackage.eNS_URI, AIPPackage.eINSTANCE);

		// load SUC model

		Resource resource = resourceSet.getResource(state.getSUC(), true);
		SUCmodel sucModel = (SUCmodel) resource.getContents().get(0);

		try {

			AIPmodel aipm = SUC2AIP.transformSuc2Aip(sucModel);
			AsemeModelSaveHelper.saveURI(aipm, aip);

			URI diag = aip.trimFileExtension().appendFileExtension("aipd");
			this.createDiagram(aipm, diag);
			MessageDialog.openConfirm(context.getShell(), "SUC2AIP transformation",
					"Output : " + aip.toPlatformString(isEnabled()));
		} catch (Exception e) {
			Diagnostic diagn = Diagnostician.INSTANCE.validate(sucModel);
			if (diagn.getCode() == 0) {
				MessageDialog.openError(context.getShell(), "Error", "Error in validating SUC model");
			} else {
				MessageDialog.openError(context.getShell(), "Error", "Error in SUC2AIP transformation");
			}
		}

	}

	private void createDiagram(AIPmodel aipm, URI diag) {
		Diagram diagram = ViewService.createDiagram(aipm, AIPmodelEditPart.MODEL_ID,
				AIPDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);

		aipm.eResource().getContents().add(diagram);

		AsemeModelSaveHelper.saveURI(aipm.eResource().getContents().get(1), diag);

		try {
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(new URIEditorInput(diag),
					AIPDiagramEditor.ID);
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
