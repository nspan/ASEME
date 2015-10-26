package asemedashboardview.views;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.draw2d.ActionEvent;
import org.eclipse.draw2d.ActionListener;
import org.eclipse.draw2d.CheckBox;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.presentation.EcoreModelWizard;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.importer.ui.EMFModelWizard;
import org.eclipse.emf.importer.ui.GenModelReloadActionDelegate;
import org.eclipse.gmf.codegen.gmfgen.presentation.GMFGenModelWizard;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;

import SAG.diagram.part.SAGCreationWizard;
import SAG.diagram.part.SAGNewDiagramFileWizard;
import SAG.presentation.SAGModelWizard;
import SUC.diagram.part.SUCCreationWizard;
import AIP.diagram.part.AIPCreationWizard;
import SRM.diagram.part.SRMCreationWizard;
import asemedashboardview.views.ASEMEActionRegistry.ASEMEActionDescriptor;
import asemedashboardview.views.actions.TransformAIP2EACModelAction;
import asemedashboardview.views.actions.TransformIAC2GGModelAction;
import asemedashboardview.views.actions.TransformIAC2JADEModelAction;
import asemedashboardview.views.actions.TransformSRM2IACModelAction;
import asemedashboardview.views.actions.TransformSAG2SUCModelAction;
import asemedashboardview.views.actions.TransformSRM2XPDLModelAction;
import asemedashboardview.views.actions.TransformSUC2AIPModelAction;
import asemedashboardview.views.actions.TransformSUC2SRMModelAction;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gmf.internal.bridge.wizards.GMFGraphSimpleModelWizard;
import org.eclipse.gmf.internal.bridge.wizards.GMFMapGuideModelWizard;
import org.eclipse.gmf.internal.bridge.wizards.GMFToolSimpleModelWizard;
import org.eclipse.gmf.internal.bridge.wizards.WizardOperationMode;
import org.eclipse.gmf.internal.common.URIUtil;
import org.eclipse.gmf.internal.common.ui.FileSelector;
import org.eclipse.gmf.mappings.presentation.GMFMapModelWizard;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.tooldef.presentation.GMFToolModelWizard;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWizard;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

import asemedashboardview.Activator;

public class ASEMEMediator implements ASEMEFacade {

	private static final boolean STRICT = true;

	private ASEMEFigure view;

	private Shell shell;

	private IProject project;

	private ASEMEState state;

	private Map<String, ActionContainer> locations;

	private Map<ASEMEActionDescriptor, IFigure> contributions;

	private Map<String, CheckBox> optionFigures;

	public ASEMEMediator(Shell shell) {
		state = new ASEMEState();
		locations = new HashMap<String, ActionContainer>();
		contributions = new HashMap<ASEMEActionDescriptor, IFigure>();
		optionFigures = new HashMap<String, CheckBox>();
		this.shell = shell;
	}

	public boolean isStrict() {
		return STRICT;
	}

	public Shell getShell() {
		return shell;
	}

	public void setView(ASEMEFigure view) {
		this.view = view;
		locations.put(ASEMEFacade.LOCATION_SAG, view.getSAGFigure());
		locations.put(ASEMEFacade.LOCATION_SUC, view.getSUCFigure());
		locations.put(ASEMEFacade.LOCATION_AIP, view.getAIPFigure());
		locations.put(ASEMEFacade.LOCATION_SRM, view.getSRMFigure());
		locations.put(ASEMEFacade.LOCATION_EAC, view.getEACFigure());
		locations.put(ASEMEFacade.LOCATION_IAC, view.getIACFigure());
		locations.put(ASEMEFacade.LOCATION_JADE, view.getJADEFigure());
		view.getSAGFigure().addAction(createLinkFigure(Messages.ASEMEMediator_Select, new SelectSAGAction()));
		view.getSAGFigure().addAction(createLinkFigure(Messages.ASEMEMediator_Edit, new EditSAGAction()));
		view.getSAGFigure().addAction(createLinkFigure(Messages.ASEMEMediator_Create, new CreateSAGAction()));
		view.getSUCFigure().addAction(createLinkFigure(Messages.ASEMEMediator_Select, new SelectSUCAction()));
		view.getSUCFigure().addAction(createLinkFigure(Messages.ASEMEMediator_Edit, new EditSUCAction()));
		view.getSUCFigure().addAction(createLinkFigure(Messages.ASEMEMediator_Create, new CreateSUCAction()));
		view.getAIPFigure().addAction(createLinkFigure(Messages.ASEMEMediator_Select, new SelectAIPAction()));
		view.getAIPFigure().addAction(createLinkFigure(Messages.ASEMEMediator_Edit, new EditAIPAction()));
		view.getAIPFigure().addAction(createLinkFigure(Messages.ASEMEMediator_Create, new CreateAIPAction()));
		view.getSRMFigure().addAction(createLinkFigure(Messages.ASEMEMediator_Select, new SelectSRMAction()));
		view.getSRMFigure().addAction(createLinkFigure(Messages.ASEMEMediator_Edit, new EditSRMAction()));
		view.getSRMFigure().addAction(createLinkFigure(Messages.ASEMEMediator_Create, new CreateSRMAction()));
		//view.getSRMFigure().addAction(createLinkFigure(Messages.ASEMEMediator_Export, new CreateSRMAction()));
		view.getXPDLFigure().addAction(createLinkFigure(Messages.ASEMEMediator_Select, new SelectXPDLAction()));
		view.getXPDLFigure().addAction(createLinkFigure(Messages.ASEMEMediator_Edit, new EditXPDLAction()));
		view.getEACFigure().addAction(createLinkFigure(Messages.ASEMEMediator_Select, new SelectEACAction()));
		view.getEACFigure().addAction(createLinkFigure(Messages.ASEMEMediator_Edit, new EditEACAction()));
		view.getEACFigure().addAction(createLinkFigure(Messages.ASEMEMediator_Create, new CreateEACAction()));
		view.getIACFigure().addAction(createLinkFigure(Messages.ASEMEMediator_Select, new SelectIACAction()));
		view.getIACFigure().addAction(createLinkFigure(Messages.ASEMEMediator_Edit, new EditIACAction()));
		view.getIACFigure().addAction(createLinkFigure(Messages.ASEMEMediator_Create, new CreateIACAction()));
		view.getJADEFigure().addAction(createLinkFigure(Messages.ASEMEMediator_Select, new SelectJADEAction()));
		view.getJADEFigure().addAction(createLinkFigure(Messages.ASEMEMediator_Edit, new EditJADEAction()));
		
		view.getGGFigure().addAction(createLinkFigure(Messages.ASEMEMediator_Select, new SelectJADEAction()));
		view.getGGFigure().addAction(createLinkFigure(Messages.ASEMEMediator_Edit, new EditJADEAction()));
		//view.getJADEFigure().addAction(createLinkFigure(Messages.ASEMEMediator_Create, new CreateJADEAction()));

		//		view.getDGMFigure().addAction(createLinkFigure(Messages.DashboardMediator_Select, new SelectDGMAction()));
		//		view.getDGMFigure().addAction(createLinkFigure(Messages.DashboardMediator_Edit, new EditDGMAction()));
		//		view.getDGMFigure().addAction(createLinkFigure(Messages.DashboardMediator_Reload, new ReloadDGMAction()));
		//		view.getGDMFigure().addAction(createLinkFigure(Messages.DashboardMediator_Select, new SelectGDMAction()));
		//		view.getGDMFigure().addAction(createLinkFigure(Messages.DashboardMediator_Edit, new EditGDMAction()));
		//		view.getGDMFigure().addAction(createLinkFigure(Messages.DashboardMediator_Create, new CreateGDMAction()));
		//		view.getDMFigure().addAction(createLinkFigure(Messages.DashboardMediator_Select, new SelectDMAction()));
		//		view.getDMFigure().addAction(createLinkFigure(Messages.DashboardMediator_Edit, new EditDMAction()));
		//		view.getDMFigure().addAction(createLinkFigure(Messages.DashboardMediator_Create, new CreateDMAction()));
		//		view.getTDMFigure().addAction(createLinkFigure(Messages.DashboardMediator_Select, new SelectTDMAction()));
		//		view.getTDMFigure().addAction(createLinkFigure(Messages.DashboardMediator_Edit, new EditTDMAction()));
		//		view.getTDMFigure().addAction(createLinkFigure(Messages.DashboardMediator_Create, new CreateTDMAction()));
		//		view.getMMFigure().addAction(createLinkFigure(Messages.DashboardMediator_Select, new SelectMMAction()));
		//		view.getMMFigure().addAction(createLinkFigure(Messages.DashboardMediator_Edit, new EditMMAction()));
		//		view.getMMFigure().addAction(createLinkFigure(Messages.DashboardMediator_Create, new CreateMMAction()));
		//		view.getGMFigure().addAction(createLinkFigure(Messages.DashboardMediator_Select, new SelectGMAction()));
		//		view.getGMFigure().addAction(createLinkFigure(Messages.DashboardMediator_Edit, new EditGMAction()));
		//		view.getGMFigure().addAction(createLinkFigure(Messages.DashboardMediator_Create, new CreateGMAction()));
		//		view.getDM2DGMFigure().addAction(createLinkFigure(Messages.DashboardMediator_Derive, new DeriveDGMAction()));
		//		view.getDM2GDMFigure().addAction(createLinkFigure(Messages.DashboardMediator_Derive, new DeriveGDMAction()));
		//		view.getDM2TDMFigure().addAction(createLinkFigure(Messages.DashboardMediator_Derive, new DeriveTDMAction()));
		//		view.getDM2MMFigure().addAction(createLinkFigure(Messages.DashboardMediator_Combine, new CombineMMAction()));
		//		view.getMM2GMFigure().addAction(createOptionFigure(Messages.DashboardMediator_RCP, ASEMEFacade.OPTION_RCP));
		//		view.getMM2GMFigure().addAction(createLinkFigure(Messages.DashboardMediator_Transform, new TransformMap2GenModelAction()));
		view.getSAG2SUCFigure().addAction(createLinkFigure(Messages.ASEMEMediator_Transform, new TransformSAG2SUCModelAction()));
		view.getSUC2AIPFigure().addAction(createLinkFigure(Messages.ASEMEMediator_Transform, new TransformSUC2AIPModelAction()));
		view.getSUC2SRMFigure().addAction(createLinkFigure(Messages.ASEMEMediator_Transform, new TransformSUC2SRMModelAction()));
		view.getAIP2EACFigure().addAction(createLinkFigure(Messages.ASEMEMediator_Transform, new TransformAIP2EACModelAction()));
		view.getSRM2IACFigure().addAction(createLinkFigure(Messages.ASEMEMediator_Transform, new TransformSRM2IACModelAction()));
		view.getSRM2XPDLFigure().addAction(createLinkFigure(Messages.ASEMEMediator_Transform, new TransformSRM2XPDLModelAction()));
		view.getIAC2JADEFigure().addAction(createLinkFigure(Messages.ASEMEMediator_Transform, new TransformIAC2JADEModelAction()));//TransformIAC2JADEModelAction()));
		view.getIAC2GGFigure().addAction(createLinkFigure(Messages.ASEMEMediator_Transform, new TransformIAC2GGModelAction()));//TransformIAC2GGModelAction()));
		ASEMEActionDescriptor[] descriptors = Activator.getDefault().getDashboardActionRegistry().getDescriptors();
		for (ASEMEActionDescriptor descriptor : descriptors) {
			addDashboardAction(descriptor);
		}
		updateStatus();
	}

	public void addDashboardAction(ASEMEActionDescriptor descriptor) {
		ActionContainer location = locations.get(descriptor.getLocation());
		if (location == null) {
			Activator.getDefault().getLog().log(Activator.createError("Unknown ASEME dashboard location: " + descriptor.getLocation(), null)); //$NON-NLS-1$
			return;
		}
		ASEMEAction action = descriptor.createDashboardAction();
		if (action == null) {
			return;
		}
		IFigure actionFigure = createLinkFigure(descriptor.getLabel(), action);
		location.addAction(actionFigure, descriptor.isStandard());
		contributions.put(descriptor, actionFigure);
	}

	public void removeDashboardAction(ASEMEActionDescriptor descriptor) {
		IFigure actionFigure = contributions.remove(descriptor);
		if (actionFigure == null) {
			return; // not contributed; just ignore
		}
		ActionContainer location = locations.get(descriptor.getLocation());
		if (location == null) {
			Activator.getDefault().getLog().log(Activator.createError("Unknown ASEME Dashboard location: " + descriptor.getLocation(), null)); //$NON-NLS-1$
			return;
		}
		location.removeAction(actionFigure, descriptor.isStandard());
	}

	/**
	 * Also initializes the action.
	 */
	protected IFigure createLinkFigure(String text, ASEMEAction action) {
		action.init(this);
		HyperlinkFigure linkFigure = new HyperlinkFigure(action);
		linkFigure.setText(text);
		return linkFigure;
	}

	protected IFigure createOptionFigure(String text, final String option) {
		final CheckBox optionFigure = new CheckBox(text);
		optionFigure.setRequestFocusEnabled(false);
		optionFigure.setFocusTraversable(false);
		optionFigure.setForegroundColor(ColorConstants.blue);
		optionFigure.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				getState().setOption(option, optionFigure.isSelected());
			}
		});
		optionFigures.put(option, optionFigure);
		return optionFigure;
	}

	public IProject getProject() {
		return project;
	}

	public ASEMEState getState() {
		return state;
	}

	public void setProjectAndState(IProject project, ASEMEState state) {
		this.project = project;
		this.state = state;
		if (this.state == null) {
			this.state = new ASEMEState();
		}
		updateStatus();
	}

	public void updateStatus() {
		if (project == null) {
			view.getStatusLine(0).setText(Messages.ASEMEMediator_SelectProject);
			//view.getStatusLine(1).setText(""); //$NON-NLS-1$
		} else {
			//view.getStatusLine(0).setText(MessageFormat.format(Messages.ASEMEMediator_Project, new Object[] { project.getName()}));
			view.getStatusLine(0).setText("Project : " + project.getName());
			//			double done = (double) state.getSpecifiedModelsCount() / state.getModelsCount();
//			view.getStatusLine(1).setText(MessageFormat.format(Messages.ASEMEMediator_Progress, new Object[] { new Double(done) }));
		}
		setModelName(view.getSAGFigure(), state.getSAG());
		setModelName(view.getSUCFigure(), state.getSUC());
		setModelName(view.getAIPFigure(), state.getAIP());
		setModelName(view.getSRMFigure(), state.getSRM());
		setModelName(view.getEACFigure(), state.getEAC());
		setModelName(view.getIACFigure(), state.getIAC());
		setModelName(view.getJADEFigure(), state.getJADE());
		view.repaint(); // update hyperlinks
	}

	protected void setModelName(ModelFigure figure, URI uri) {
		figure.setName(uri == null ? null : uri.lastSegment());
		figure.setFullName(uri == null ? null : uri.toString());
	}

	protected abstract class SelectFileAction implements ASEMEAction {

		public void init(ASEMEFacade context) {
		}

		public boolean isEnabled() {
			return project != null;
			//return true;
		}

		public void run() {
			IFile file = getURI() == null ? null : URIUtil.getFile(getURI());
			file = FileSelector.selectFile(shell, getFigure().getDescription(), null, file, getFileExtension());
			if (file == null) {
				return;
			}
			setURI(URI.createPlatformResourceURI(file.getFullPath().toString(), true));
			updateStatus();
		}

		protected abstract ModelFigure getFigure();

		protected abstract URI getURI();

		protected abstract void setURI(URI uri);

		protected abstract String getFileExtension();
	}

	protected abstract class EditFileAction implements ASEMEAction {

		public void init(ASEMEFacade context) {
		}

		public boolean isEnabled() {
			return project != null && getURI() != null;
			//return getURI()!= null;
		}

		public void run() {
			IWorkbench workbench = PlatformUI.getWorkbench();
			IWorkbenchWindow workbenchWindow = workbench.getActiveWorkbenchWindow();
			IWorkbenchPage page = workbenchWindow.getActivePage();
			IFile modelFile = URIUtil.getFile(getURI());
			try {
				String fileName = modelFile.getFullPath().toString();
				String editorId = workbench.getEditorRegistry().getDefaultEditor(fileName).getId();
				page.openEditor(new FileEditorInput(modelFile), editorId);
			} catch (PartInitException pie) {
				String msg = MessageFormat.format(Messages.ASEMEMediator_FailToOpen, new Object[] { getURI() });
				MessageDialog.openError(workbenchWindow.getShell(), msg, pie.getMessage());
				//System.err.println(modelFile.getFullPath().toString());
			}
		}

		protected abstract URI getURI();
	}

	protected abstract class RunWizardAction implements ASEMEAction {

		private static final int SIZING_WIZARD_WIDTH = 500;

		private static final int SIZING_WIZARD_HEIGHT = 500;

		public void init(ASEMEFacade context) {
		}

		public boolean isEnabled() {
			return project != null;
			//return true;
		}

		public void run() {
			final IWizard wizard = createWizard();
			if (wizard instanceof IWorkbenchWizard) {
				((IWorkbenchWizard) wizard).init(PlatformUI.getWorkbench(), getSelection());
			}
			WizardDialog dialog = new WizardDialog(shell, wizard) {

				protected void finishPressed() {
					wizardFinished(wizard);
					super.finishPressed();
				}
			};
			dialog.create();
			dialog.getShell().setSize(Math.max(SIZING_WIZARD_WIDTH, dialog.getShell().getSize().x), SIZING_WIZARD_HEIGHT);
			dialog.open();
		}

		protected IStructuredSelection getSelection() {
			List<IFile> selection = new ArrayList<IFile>();
			addFile(selection, state.getSAG());
			addFile(selection, state.getSUC());
			addFile(selection, state.getAIP());
			addFile(selection, state.getSRM());
			addFile(selection, state.getEAC());
			addFile(selection, state.getIAC());
			addFile(selection, state.getJADE());
			return new StructuredSelection(selection);
		}

		protected void addFile(List<IFile> files, URI uri) {
			if (uri == null) {
				return;
			}
			IFile file = URIUtil.getFile(uri);
			if (file != null) {
				files.add(file);
			}
		}

		protected abstract IWizard createWizard();

		protected void wizardFinished(IWizard wizard) {
		}
	}
	private class SelectSAGAction extends SelectFileAction {

		protected ModelFigure getFigure() {
			return ASEMEMediator.this.view.getSAGFigure();
		}

		protected URI getURI() {
			return state.getSAG();
		}

		protected void setURI(URI uri) {
			state.setSAG(uri);
		}

		protected String getFileExtension() {
			return "sag"; //$NON-NLS-1$
		}
	}

	private class SelectSUCAction extends SelectFileAction {

		protected ModelFigure getFigure() {
			return ASEMEMediator.this.view.getSUCFigure();
		}

		protected URI getURI() {
			return state.getSUC();
		}

		protected void setURI(URI uri) {
			state.setSUC(uri);
		}

		protected String getFileExtension() {
			return "suc"; //$NON-NLS-1$
		}
	}

	private class SelectAIPAction extends SelectFileAction {

		protected ModelFigure getFigure() {
			return ASEMEMediator.this.view.getAIPFigure();
		}

		protected URI getURI() {
			return state.getAIP();
		}

		protected void setURI(URI uri) {
			state.setAIP(uri);
		}

		protected String getFileExtension() {
			return "aip"; //$NON-NLS-1$
		}
	}

	private class SelectSRMAction extends SelectFileAction {

		protected ModelFigure getFigure() {
			return ASEMEMediator.this.view.getSRMFigure();
		}

		protected URI getURI() {
			return state.getSRM();
		}

		protected void setURI(URI uri) {
			state.setSRM(uri);
		}

		protected String getFileExtension() {
			return "srm"; //$NON-NLS-1$
		}
	}
	
	private class SelectXPDLAction extends SelectFileAction { ////////////////////////

		protected ModelFigure getFigure() {
			return ASEMEMediator.this.view.getXPDLFigure();
		}

		protected URI getURI() {
			return state.getXPDL();
		}

		protected void setURI(URI uri) {
			state.setXPDL(uri);
		}

		protected String getFileExtension() {
			return "xpdl"; //$NON-NLS-1$
		}
	}

	private class SelectEACAction extends SelectFileAction {

		protected ModelFigure getFigure() {
			return ASEMEMediator.this.view.getEACFigure();
		}

		protected URI getURI() {
			return state.getEAC();
		}

		protected void setURI(URI uri) {
			state.setEAC(uri);
		}

		protected String getFileExtension() {
			return "stct"; //$NON-NLS-1$
		}
	}
	
	private class SelectIACAction extends SelectFileAction {

		protected ModelFigure getFigure() {
			return ASEMEMediator.this.view.getIACFigure();
		}

		protected URI getURI() {
			return state.getIAC();
		}

		protected void setURI(URI uri) {
			state.setIAC(uri);
		}

		protected String getFileExtension() {
			return "stct"; //$NON-NLS-1$
		}
	}
	
	private class SelectJADEAction extends SelectFileAction { ////////////////////////

		protected ModelFigure getFigure() {
			return ASEMEMediator.this.view.getIACFigure();
		}

		protected URI getURI() {
			return state.getIAC();
		}

		protected void setURI(URI uri) {
			state.setIAC(uri);
		}

		protected String getFileExtension() {
			return "iac"; //$NON-NLS-1$
		}
	}

	private class EditSAGAction extends EditFileAction {

		protected URI getURI() {
			return state.getSAG();	
		}
	}

	private class EditSUCAction extends EditFileAction {

		protected URI getURI() {
			return state.getSUC();
		}
	}	

	private class EditAIPAction extends EditFileAction {

		protected URI getURI() {
			return state.getAIP();
		}
	}	

	private class EditSRMAction extends EditFileAction {

		protected URI getURI() {
			return state.getSRM();
		}
	}
	
	private class EditXPDLAction extends EditFileAction {	////////////////////

		protected URI getURI() {
			return state.getSRM();
		}
	}

	private class EditEACAction extends EditFileAction {

		protected URI getURI() {
			return state.getEAC();
		}
	}

	private class EditIACAction extends EditFileAction {

		protected URI getURI() {
			return state.getIAC();
		}
	}
	
	private class EditJADEAction extends EditFileAction { 	/////////////////

		protected URI getURI() {
			return state.getIAC();
		}
	}


	private class CreateSAGAction extends RunWizardAction {

		protected IWizard createWizard() {
			return new SAGCreationWizard();
		}

		protected void wizardFinished(IWizard wizard) {
			
			//			URI domainModelURI = URI.createPlatformResourceURI(file.toString(), false);
			//			EObject diagramRoot = null;
			//			TransactionalEditingDomain editingDomain = null;
			//			new SAGNewDiagramFileWizard(domainModelURI, diagramRoot,editingDomain);
			//IFile file2 = new IFile();

			//			Resource resource = ((SAGCreationWizard) wizard).getDiagram();
			//			IFile file = WorkspaceSynchronizer.getFile(resource);
			//			System.out.println("Resource : " + resource);
			//			java.net.URI javaUri = ((IResource) resource).getLocationURI();
			//			URI uri = URI.createURI(javaUri.toString());
			//			IFile file = ((SAGModelWizard) wizard).getModelFile();
			//			String fileName = file.getFullPath().toString();
			//			System.out.println("File Name : " + fileName);
			//			String diagramFile = fileName.replace(".sag", ".sagd");
			//			System.out.println("Diagram File : " + diagramFile);
			//			IWorkbench workbench = PlatformUI.getWorkbench();
			//			IWorkbenchWindow workbenchWindow = workbench.getActiveWorkbenchWindow();
			//			IWorkbenchPage page = workbenchWindow.getActivePage();
			//			IFile modelFile = URIUtil.getFile(URI.createFileURI(diagramFile));
			//			System.out.println("URI : "+ URI.createFileURI(diagramFile));
			//			String editorId = workbench.getEditorRegistry().getDefaultEditor(diagramFile).getId();
			//			try {
			//				page.openEditor(new FileEditorInput(modelFile), editorId);
			//			} catch (PartInitException e) {
			//				e.printStackTrace();
			//			}
			//			state.setSAG(uri);
			//IFile file = ((SAGModelWizard) wizard).getModelFile();
			Resource resource = ((SAGCreationWizard) wizard).getDiagram();
			//state.setSAG(file);
			updateStatus();
		}
	}

	private class CreateSUCAction extends RunWizardAction {

		protected IWizard createWizard() {
			return new SUCCreationWizard();
		}

		protected void wizardFinished(IWizard wizard) {
			Resource resource = ((SUCCreationWizard) wizard).getDiagram();
			//			state.setSUC(file);
			updateStatus();
		}
	}
	
	private class CreateAIPAction extends RunWizardAction {

		protected IWizard createWizard() {
			return new AIPCreationWizard();
		}

		protected void wizardFinished(IWizard wizard) {
			Resource resource = ((AIPCreationWizard) wizard).getDiagram();
			//			state.setSUC(file);
			updateStatus();
		}
	}


	private class CreateSRMAction extends RunWizardAction {

		protected IWizard createWizard() {
			return new SRMCreationWizard();
		}

		protected void wizardFinished(IWizard wizard) {
			Resource resource = ((SRMCreationWizard) wizard).getDiagram();
			//			state.setSRM(file);
			updateStatus();
		}
	}

	private class CreateEACAction extends RunWizardAction {
		
		public boolean isEnabled(){
			return false;
		}

		protected IWizard createWizard() {
			//return new EACCreationWizard();
			return null;
		}

		protected void wizardFinished(IWizard wizard) {
			//Resource resource = ((EACCreationWizard) wizard).getDiagram();
			//			state.setEAC(file);
			//updateStatus();
		}
	}

	private class CreateIACAction extends RunWizardAction {
		
		public boolean isEnabled(){
			return false;
		}

		protected IWizard createWizard() {
			//return new IACCreationWizard();
			return null;
		}

		protected void wizardFinished(IWizard wizard) {
			//Resource resource = ((IACCreationWizard) wizard).getDiagram();
			//			state.setIAC(file);
			//updateStatus();
		}
	}
	
	//	private class CreateGDMAction extends RunWizardAction {
	//
	//		protected IWizard createWizard() {
	//			return new GMFGraphModelWizard();
	//		}
	//
	//		protected void wizardFinished(IWizard wizard) {
	//			IFile file = ((GMFGraphModelWizard) wizard).getModelFile();
	//			state.setGDM(file);
	//			updateStatus();
	//		}
	//	}
	//
	//	private class CreateDMAction extends RunWizardAction {
	//
	//		protected IWizard createWizard() {
	//			return new EcoreModelWizard();
	//		}
	//
	//		protected void wizardFinished(IWizard wizard) {
	//			IFile file = ((EcoreModelWizard) wizard).getModelFile();
	//			state.setDM(file);
	//			updateStatus();
	//		}
	//	}
	//
	//	private class CreateTDMAction extends RunWizardAction {
	//
	//		protected IWizard createWizard() {
	//			return new GMFToolModelWizard();
	//		}
	//
	//		protected void wizardFinished(IWizard wizard) {
	//			IFile file = ((GMFToolModelWizard) wizard).getModelFile();
	//			state.setTDM(file);
	//			updateStatus();
	//		}
	//	}
	//
	//	private class CreateMMAction extends RunWizardAction {
	//
	//		protected IWizard createWizard() {
	//			return new GMFMapModelWizard();
	//		}
	//
	//		protected void wizardFinished(IWizard wizard) {
	//			IFile file = ((GMFMapModelWizard) wizard).getModelFile();
	//			state.setMM(file);
	//			updateStatus();
	//		}
	//	}
	//
	//	private class CreateGMAction extends RunWizardAction {
	//
	//		protected IWizard createWizard() {
	//			return new GMFGenModelWizard();
	//		}
	//
	//		protected void wizardFinished(IWizard wizard) {
	//			IFile file = ((GMFGenModelWizard) wizard).getModelFile();
	//			state.setGM(file);
	//			updateStatus();
	//		}
	//	}
	//
	//	private class ReloadDGMAction implements ASEMEAction {
	//
	//		public void init(ASEMEFacade context) {
	//		}
	//
	//		public boolean isEnabled() {
	//			return state.getDGM() != null;
	//		}
	//
	//		public void run() {
	//			IFile file = URIUtil.getFile(state.getDGM());
	//			GenModelReloadActionDelegate action = new GenModelReloadActionDelegate();
	//			IAction uiAction = new Action() {
	//			};
	//			action.selectionChanged(uiAction, new StructuredSelection(file));
	//			action.run(uiAction);
	//			updateStatus();
	//		}
	//	}
	//
	//	private class DeriveDGMAction extends RunWizardAction {
	//
	//		public boolean isEnabled() {
	//			return super.isEnabled() && state.getDM() != null;
	//		}
	//
	//		protected IWizard createWizard() {
	//			return new OpenEMFModelWizard();
	//		}
	//
	//		protected void wizardFinished(IWizard wizard) {
	//			IFile file = ((OpenEMFModelWizard) wizard).getModelFile();
	//			state.setDGM(file);
	//			updateStatus();
	//		}
	//
	//		protected IStructuredSelection getSelection() {
	//			return new StructuredSelection(URIUtil.getFile(state.getDM()));
	//		}
	//	}

	//	private static class OpenEMFModelWizard extends EMFModelWizard {
	//
	//		public IFile getModelFile() {
	//			IPath path = genModelContainerPath.append(genModelFileName);
	//			return ResourcesPlugin.getWorkspace().getRoot().getFile(path);
	//		}
	//	}

	//	private class DeriveGDMAction extends RunWizardAction {
	//
	//		public boolean isEnabled() {
	//			return super.isEnabled() && state.getDM() != null;
	//		}
	//
	//		protected IWizard createWizard() {
	//			GMFGraphSimpleModelWizard wizard = new GMFGraphSimpleModelWizard();
	//			wizard.setMode(WizardOperationMode.DETECT);
	//			return wizard;
	//		}
	//
	//		protected void wizardFinished(IWizard wizard) {
	//			IFile file = ((GMFGraphSimpleModelWizard) wizard).getModelFile();
	//			state.setGDM(file);
	//			updateStatus();
	//		}
	//	}

	//	
	//	private class DeriveGDMAction extends RunWizardAction {
	//
	//		public boolean isEnabled() {
	//			return super.isEnabled() && state.getDM() != null;
	//		}
	//
	//		protected IWizard createWizard() {
	//			GMFGraphSimpleModelWizard wizard = new GMFGraphSimpleModelWizard();
	//			wizard.setMode(WizardOperationMode.DETECT);
	//			return wizard;
	//		}
	//
	//		protected void wizardFinished(IWizard wizard) {
	//			IFile file = ((GMFGraphSimpleModelWizard) wizard).getModelFile();
	//			state.setGDM(file);
	//			updateStatus();
	//		}
	//	}
	//
	//	private class DeriveTDMAction extends RunWizardAction {
	//
	//		public boolean isEnabled() {
	//			return super.isEnabled() && state.getDM() != null;
	//		}
	//
	//		protected IWizard createWizard() {
	//			GMFToolSimpleModelWizard wizard = new GMFToolSimpleModelWizard();
	//			wizard.setMode(WizardOperationMode.DETECT);
	//			return wizard;
	//		}
	//
	//		protected void wizardFinished(IWizard wizard) {
	//			IFile file = ((GMFToolSimpleModelWizard) wizard).getModelFile();
	//			state.setTDM(file);
	//			updateStatus();
	//		}
	//	}
	//
	//	private class CombineMMAction extends RunWizardAction {
	//
	//		public boolean isEnabled() {
	//			return project != null && state.getGDM() != null && state.getDM() != null && state.getTDM() != null;
	//		}
	//
	//		protected IWizard createWizard() {
	//			return new GMFMapGuideModelWizard();
	//		}
	//
	//		protected void wizardFinished(IWizard wizard) {
	//			IFile file = ((GMFMapGuideModelWizard) wizard).getModelFile();
	//			state.setMM(file);
	//			updateStatus();
	//		}
	//	}
}
