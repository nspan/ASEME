package asemedashboardview.views.actions;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import AIP.AIPFactory;
import AIP.AIPPackage;
import AIP.AIPmodel;
import AIP.Participant;
import AIP.Protocol;
import SAG.Actor;
import SAG.Goal;
import SAG.SAGPackage;
import SAG.SAGmodel;
import SUC.Role;
import SUC.SUCFactory;
import SUC.SUCPackage;
import SUC.SUCmodel;
import SUC.UseCase;
import asemedashboardview.views.ASEMEAction;
import asemedashboardview.views.ASEMEFacade;
import asemedashboardview.views.ASEMEState;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

public class TransformSUC2AIPModelAction implements ASEMEAction {

	private ASEMEFacade context;

	@Override
	public void init(ASEMEFacade context) {
		this.context = context;
	}

	@Override
	public boolean isEnabled() {
		ASEMEState state = context.getState();
		if(state.getSUC()==null) {
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
		}
		aip = state.getAIP();
		ResourceSet resourceSet = new ResourceSetImpl();

		resourceSet
		.getResourceFactoryRegistry()
		.getExtensionToFactoryMap()
		.put(Resource.Factory.Registry.DEFAULT_EXTENSION,
				new XMIResourceFactoryImpl());

		// Register the package to ensure it is available during loading.
		//
		resourceSet.getPackageRegistry().put(SUCPackage.eNS_URI, SUCPackage.eINSTANCE);
		resourceSet.getPackageRegistry().put(AIPPackage.eNS_URI, AIPPackage.eINSTANCE);

		// load SUC model

		Resource resource = resourceSet.getResource(state.getSUC(), true);	
		SUCmodel sucModel = (SUCmodel) resource.getContents().get(0);

		// Create a new AIP model instance
		Resource newResource = resourceSet.createResource(state.getAIP());
		AIPmodel aipModel = AIPFactory.eINSTANCE.createAIPmodel();
		newResource.getContents().add(aipModel);
		// transformation code:
		for (Iterator<UseCase> iterator = sucModel.getUsecases().iterator(); iterator.hasNext();) {
			UseCase usecase = iterator.next();
			List<Role> systemRoleUseCaseParticipants = new LinkedList<Role>();
			for (Iterator<Role> iterator2 = usecase.getParticipant().iterator(); iterator2.hasNext();) {
				Role role =  iterator2.next();
				if (role.getType().getLiteral() == "System")
					systemRoleUseCaseParticipants.add(((Role) role));
			}
			if (systemRoleUseCaseParticipants.size() > 1) {
				Protocol tmpProtocol = AIPFactory.eINSTANCE.createProtocol();
				tmpProtocol.setName(usecase.getName());
				HashMap<String, Participant> participants = new HashMap<String, Participant>();
				for (Iterator<Role> iterator2 = systemRoleUseCaseParticipants
						.iterator(); iterator2.hasNext();) {
					Role systemRole =  iterator2.next();
					Participant tmpParticipant = AIPFactory.eINSTANCE.createParticipant();
					tmpParticipant.setName(new String(tmpProtocol.getName()
							+ "_" + systemRole.getName()));
					tmpProtocol.getParticipants().add(tmpParticipant);
					aipModel.getParticipants().add(tmpParticipant);
					participants.put(tmpParticipant.getName(), tmpParticipant);
					tmpParticipant.setLiveness(new String(tmpParticipant
							.getName() + "="));
				}
				for (Iterator<UseCase> iterator2 = usecase.getInclude()
						.iterator(); iterator2.hasNext();) {
					UseCase tmpUsecase = (UseCase) iterator2.next();
					if (participants.get(tmpProtocol.getName() + "_"
							+ tmpUsecase.getParticipant().get(0).getName()) != null) {
						participants.get(
								tmpProtocol.getName()
								+ "_"
								+ tmpUsecase.getParticipant().get(0)
								.getName()).setLiveness(
										participants.get(
												tmpProtocol.getName()
												+ "_"
												+ tmpUsecase.getParticipant()
												.get(0).getName())
												.getLiveness()
												+ tmpUsecase.getName() + "?");
					}
				}
				aipModel.getProtocols().add(tmpProtocol);
				// add the activities!!!
			}
		}
		// save the AIP model
		newResource.getContents().add(aipModel);
		try {
			Map<String, Object> options = new HashMap<String, Object>();
			options.put( XMLResource.OPTION_ENCODING, "UTF8" );
	        options.put( XMLResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE);
	        options.put( XMLResource.OPTION_SCHEMA_LOCATION_IMPLEMENTATION , Boolean.TRUE);
			newResource.save(options);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
