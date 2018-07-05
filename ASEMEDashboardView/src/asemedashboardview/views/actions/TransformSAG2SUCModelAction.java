package asemedashboardview.views.actions;

import java.util.Objects;

import javax.swing.JOptionPane;

import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import SAG.SAGmodel;
import SUC.SUCmodel;
import SUC.diagram.edit.parts.SUCmodelEditPart;
import SUC.diagram.part.SUCDiagramEditor;
import SUC.diagram.part.SUCDiagramEditorPlugin;
import aseme.transformations.AsemeModelSaveHelper;
import aseme.transformations.SAG2SUC;
import asemedashboardview.views.ASEMEAction;
import asemedashboardview.views.ASEMEFacade;
import asemedashboardview.views.ASEMEState;

public class TransformSAG2SUCModelAction implements ASEMEAction {

	private ASEMEFacade context;

	@Override
	public void init(ASEMEFacade context) {
		this.context = context;
	}

	@Override
	public boolean isEnabled() {
		ASEMEState state = context.getState();
		if (state.getSAG() == null) {
			return false;
		}
		return true;
	}

	@Override
	public void run() {
		ASEMEState state = context.getState();
		URI sag = state.getSAG();
		URI suc = state.getSUC();
		if (suc == null) {
			suc = sag.trimFileExtension().appendFileExtension("suc"); //$NON-NLS-1$
			state.setSUC(suc);

		}

		else {

			boolean quest = MessageDialog.openQuestion(context.getShell(), "SUC model exists!",
					suc.toString() + " already exists. Do you want to overwrite?");

			if (!quest) {
				String sucName = sag.trimFileExtension().toString() + "_0";

				String[] sucPathParts = sucName.split("/");

				String newName = JOptionPane.showInputDialog("Give name", sucPathParts[sucPathParts.length - 1]);

				if (Objects.isNull(newName)) {
					return;
				}

				sucPathParts[sucPathParts.length - 1] = newName;

				String reconstructedPath = "";

				for (int i = 0; i < sucPathParts.length; i++) {

					reconstructedPath += sucPathParts[i] + "/";
				}

				reconstructedPath = reconstructedPath.substring(0, reconstructedPath.length() - 1);

				suc = URI.createURI(reconstructedPath).appendFileExtension("suc");

			}
		}

		// ResourceSet resourceSet = new ResourceSetImpl();
		// Register the appropriate resource factory to handle all file
		// extensions.
		//

		// resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
		// .put(Resource.Factory.Registry.DEFAULT_EXTENSION, new
		// XMIResourceFactoryImpl());

		// Register the package to ensure it is available during loading.
		//
		// resourceSet.getPackageRegistry().put(SUCPackage.eNS_URI,
		// SUCPackage.eINSTANCE);

		// load SUC model

		Resource resource = AsemeModelSaveHelper.staticResourceSet.getResource(state.getSAG(), true); // resourceSet.getResource(state.getSAG(),
																										// true);
		SAGmodel sagModel = (SAGmodel) resource.getContents().get(0);

		try {
			SUCmodel sucm = SAG2SUC.transformSag2Suc(sagModel);

			AsemeModelSaveHelper.saveURI(sucm, suc);

			URI diag = suc.trimFileExtension().appendFileExtension("sucd");

			this.createDiagram(sucm, diag);

			MessageDialog.openConfirm(context.getShell(), "SAG2SUC transformation",
					"Output : " + suc.toPlatformString(isEnabled()));

		} catch (Exception e) {
			// Diagnostician.INSTANCE.validate(sagModel).getMessage();
			// MultiStatus status = createMultiStatus(e.getLocalizedMessage(),
			// e);
			// ErrorDialog.openError(context.getShell(), "SAG2SUC Error",
			// Diagnostician.INSTANCE.validate(sagModel).getMessage(), null);
			state.setSUC(null);
			boolean diagn = Diagnostician.INSTANCE.validate(sagModel,
					Diagnostician.INSTANCE.createDefaultDiagnostic(sagModel));
			if (!diagn) {
				MessageDialog.openError(context.getShell(), "Error", "Error in validating SAG model");
			} else {
				MessageDialog.openError(context.getShell(), "Error", "something went wrong");
			}
		}

	}

	private void createDiagram(SUCmodel sucm, URI diag) {
		Diagram diagram = ViewService.createDiagram(sucm, SUCmodelEditPart.MODEL_ID,
				SUCDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);

		sucm.eResource().getContents().add(diagram);

		AsemeModelSaveHelper.saveURI(sucm.eResource().getContents().get(1), diag);

		try {
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(new URIEditorInput(diag),
					SUCDiagramEditor.ID);
		} catch (PartInitException e) {
			MessageDialog.openError(context.getShell(), "Error", "Error in opening diagram");
		}
	}

}
