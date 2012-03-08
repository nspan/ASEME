package asemedashboardview.views.actions;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import AIP.AIPmodel;
import AIP.Participant;
import SRM.Activity;
import SRM.Capability;
import SRM.Role;
import SRM.SRMFactory;
import SRM.SRMPackage;
import SRM.SRMmodel;
import SUC.SUCPackage;
import SUC.SUCmodel;
import SUC.SystemRole;
import SUC.UseCase;
import asemedashboardview.views.ASEMEAction;
import asemedashboardview.views.ASEMEFacade;
import asemedashboardview.views.ASEMEState;

public class TransformSUC2SRMModelAction implements ASEMEAction {

	private ASEMEFacade context;

	@Override
	public void init(ASEMEFacade context) {
		this.context = context;
	}

	@Override
	public boolean isEnabled() {
		ASEMEState state = context.getState();
		//TODO : Verify if AIP also needs to be enabled
		if(state.getSUC()==null ) {
			return false;
		}
		return true;
	}

	@Override
	public void run() {
		ASEMEState state = context.getState(); 
		URI suc = state.getSUC();
		URI srm = state.getSRM();
		if (srm == null) {
			srm = suc.trimFileExtension().appendFileExtension("srm"); //$NON-NLS-1$
			state.setSRM(srm);
		}
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet
		.getResourceFactoryRegistry()
		.getExtensionToFactoryMap()
		.put(Resource.Factory.Registry.DEFAULT_EXTENSION,
				new XMIResourceFactoryImpl());

		resourceSet.getPackageRegistry().put(SUCPackage.eNS_URI, SUCPackage.eINSTANCE);
		resourceSet.getPackageRegistry().put(SRMPackage.eNS_URI, SRMPackage.eINSTANCE);

		Resource resource = resourceSet.getResource(suc, true);

		//Create an SRM file for each SUC Role
		SUCmodel sucModel = (SUCmodel) resource.getContents().get(0);
		Iterator<SUC.Role> iterator = sucModel.getRoles().iterator();
		for(SUC.Role sucRole = iterator.next(); iterator.hasNext();sucRole = iterator.next()) {
			if (sucRole instanceof SystemRole) {
				SRMmodel srmModel = SRMFactory.eINSTANCE.createSRMmodel();
				SRM.Role srmRole = SRMFactory.eINSTANCE.createRole();
				srmModel.getRoles().add(srmRole);
				srmRole.setName(sucRole.getName());
				String srmString = state.getSRM().toString();
				srmString =  srmString.substring(0, srmString.length() - 4);
				srmString = srmString + srmRole.getName() + ".srm";
				srm = URI.createURI(srmString);
				Resource newResource = resourceSet.createResource(srm);
				newResource.getContents().add(srmModel);
				newResource.getContents().add(srmRole);
				srmRole.setLiveness(new String(srmRole.getName() + "=?\n"));
				//Transformation Code
				Iterator<UseCase> iterator2 = sucRole.getParticipates_in().iterator();
				for (UseCase usecase = iterator2.next(); iterator2.hasNext(); usecase = iterator2.next()) {
					// if the use case includes others it is inserted as a
					// capability
					// if the use case has another participant the capability's
					// name is the usecase name followed by .<Role name>
					// add a line in the liveness formula with the usecase
					// equals to the included ones connected by question marks
					// else it is inserted as an activity
					if (usecase.getInclude().size() > 0) {
						Capability cp = SRMFactory.eINSTANCE.createCapability();
						newResource.getContents().add(cp);
						srmModel.getCapabilities().add(cp);
						srmRole.getCapabilities().add(cp);
						if (usecase.getParticipant().size() > 0) {
							cp.setName(new String(usecase.getName() + "_"
									+ srmRole.getName()));
						} else {
							cp.setName(new String(usecase.getName()));
						}
						srmRole.setLiveness(new String(srmRole.getLiveness()
								+ cp.getName() + "="));
						boolean firstLivenessRightSideElement = true;
						for (Iterator<UseCase> iterator3 = usecase.getInclude()
								.iterator(); iterator3.hasNext();) {
							UseCase tmpUsecase = iterator3.next();
							if (tmpUsecase.getParticipant().get(0).getName()
									.equalsIgnoreCase(srmRole.getName())) {
								srmRole.setLiveness(new String(srmRole
										.getLiveness()
										+ (firstLivenessRightSideElement ? ""
												: "?") + tmpUsecase.getName()));
								if (firstLivenessRightSideElement)
									firstLivenessRightSideElement = false;
							}
						}
						srmRole.setLiveness(srmRole.getLiveness()+"\n");
					} else {
						Activity activity = SRMFactory.eINSTANCE
						.createActivity();
						newResource.getContents().add(activity);
						srmModel.getActivities().add(activity);
						srmRole.getActivities().add(activity);
						activity.setName(usecase.getName());
						activity.setFunctionality(usecase.getSpecified_by());
					}
				}
				for (iterator2 = sucRole
						.getParticipates_in().iterator(); iterator2.hasNext();) {
					UseCase usecase = iterator2.next();
					if (usecase.getInclude().size() > 0) {
						for (Iterator<Capability> iterator3 = srmRole
								.getCapabilities().iterator(); iterator3
								.hasNext();) {
							Capability tmpCapability = iterator3.next();
							if (tmpCapability.getName().indexOf(
									usecase.getName()) >= 0) {
								for (Iterator<UseCase> iterator4 = usecase
										.getInclude().iterator(); iterator4
										.hasNext();) {
									UseCase tmpUseCase = iterator4.next();
									for (Iterator<Activity> iterator5 = srmRole
											.getActivities().iterator(); iterator5
											.hasNext();) {
										Activity tmpActivity = iterator5.next();
										if (tmpActivity.getName()
												.equalsIgnoreCase(
														tmpUseCase.getName())) {
											tmpCapability.getActivities().add(
													tmpActivity);
											break;
										}
									}
								}
								break;
							}
						}
					}
				}
				URI aip = state.getAIP();
				if(aip!=null) {
				Resource aipResource = resourceSet.getResource(aip, true);
				AIPmodel aipModel = (AIPmodel) aipResource.getContents().get(0);
				Iterator<AIP.Participant> aipIterator = aipModel.getParticipants().iterator();
				for(AIP.Participant aipParticipant = aipIterator.next(); aipIterator.hasNext();aipParticipant = aipIterator.next()) {
					if(aipParticipant.getName().contains("_" + srmRole.getName()) == true) {
						Role tmpParticipant = SRMFactory.eINSTANCE.createRole();
						tmpParticipant.setName(aipParticipant.getName());
						tmpParticipant.setLiveness(aipParticipant.getLiveness());
						srmModel.getRoles().add(tmpParticipant);
					}
				}
				}
				// save the SRM model
				newResource.getContents().add(srmModel);
				
				try {
					Map<String, Object> options = new HashMap<String, Object>();
					options.put(XMLResource.OPTION_ENCODING, "UTF8");
					newResource.save(options);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}

		}
	}
}
