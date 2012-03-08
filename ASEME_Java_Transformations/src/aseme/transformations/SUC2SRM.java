package aseme.transformations;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import SRM.*;
import SUC.*;

public class SUC2SRM {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
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
		resourceSet.getPackageRegistry().put(SUCPackage.eNS_URI,
				SUCPackage.eINSTANCE);
		resourceSet.getPackageRegistry().put(SRMPackage.eNS_URI,
				SRMPackage.eINSTANCE);
		// load SUC model
		Resource r = null;
		if (args != null && args.length > 1) {
			r = resourceSet.getResource(URI.createFileURI(args[0]), true);
		} else {
			r = resourceSet.getResource(URI.createFileURI("mms-refined.suc"),
					true);
		}
		SUCmodel suc_model = (SUCmodel) r.getContents().get(0);

		// Create an SRM file for each SystemRole in the SUC model
		for (Iterator<SUC.Role> iterator = suc_model.getRoles().iterator(); iterator
				.hasNext();) {
			SUC.Role suc_role = iterator.next();
			if (suc_role instanceof SystemRole) {
				Resource newResource = resourceSet.createResource(URI
						.createURI("http:///My.srm"));
				SRMmodel srm = SRMFactory.eINSTANCE.createSRMmodel();
				newResource.getContents().add(srm);
				SRM.Role srm_role = SRMFactory.eINSTANCE.createRole();
				newResource.getContents().add(srm_role);
				srm.getRoles().add(srm_role);
				srm_role.setName(suc_role.getName());
				srm_role.setLiveness(new String(srm_role.getName() + "=?\n"));
				// transformation code:
				for (Iterator<UseCase> iterator2 = suc_role
						.getParticipates_in().iterator(); iterator2.hasNext();) {
					UseCase usecase = iterator2.next();
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
						srm.getCapabilities().add(cp);
						srm_role.getCapabilities().add(cp);
						if (usecase.getParticipant().size() > 0) {
							cp.setName(new String(usecase.getName() + "_"
									+ srm_role.getName()));
						} else {
							cp.setName(new String(usecase.getName()));
						}
						srm_role.setLiveness(new String(srm_role.getLiveness()
								+ cp.getName() + "="));
						boolean firstLivenessRightSideElement = true;
						for (Iterator<UseCase> iterator3 = usecase.getInclude()
								.iterator(); iterator3.hasNext();) {
							UseCase tmpUsecase = iterator3.next();
							if (tmpUsecase.getParticipant().get(0).getName()
									.equalsIgnoreCase(srm_role.getName())) {
								srm_role.setLiveness(new String(srm_role
										.getLiveness()
										+ (firstLivenessRightSideElement ? ""
												: "?") + tmpUsecase.getName()));
								if (firstLivenessRightSideElement)
									firstLivenessRightSideElement = false;
							}
						}
						srm_role.setLiveness(srm_role.getLiveness()+"\n");
					} else {
						Activity activity = SRMFactory.eINSTANCE
								.createActivity();
						newResource.getContents().add(activity);
						srm.getActivities().add(activity);
						srm_role.getActivities().add(activity);
						activity.setName(usecase.getName());
						activity.setFunctionality(usecase.getSpecified_by());
					}
				}
				for (Iterator<UseCase> iterator2 = suc_role
						.getParticipates_in().iterator(); iterator2.hasNext();) {
					UseCase usecase = iterator2.next();
					if (usecase.getInclude().size() > 0) {
						for (Iterator<Capability> iterator3 = srm_role
								.getCapabilities().iterator(); iterator3
								.hasNext();) {
							Capability tmpCapability = iterator3.next();
							if (tmpCapability.getName().indexOf(
									usecase.getName()) >= 0) {
								for (Iterator<UseCase> iterator4 = usecase
										.getInclude().iterator(); iterator4
										.hasNext();) {
									UseCase tmpUseCase = iterator4.next();
									for (Iterator<Activity> iterator5 = srm_role
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
				// save the SRM model
				newResource.getContents().add(srm);
				try {
					FileOutputStream out = null;
					if (args != null && args.length > 1) {
						out = new FileOutputStream(new File(new String(
								args[1].substring(
										0,
										(args[1].indexOf(".srm") > 0 ? args[1]
												.length() - 4 : args[1]
												.length()))
										+ srm_role.getName() + ".srm")));
					} else {
						out = new FileOutputStream(new File(new String(
								"mms-initial-" + srm_role.getName() + ".srm")));
					}
					Map<String, Object> options = new HashMap<String, Object>();
					options.put(XMLResource.OPTION_ENCODING, "UTF8");
					newResource.save(out, options);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
	}

}
