package aseme.transformations;

import java.util.Iterator;

import AIP.AIPmodel;
import AIP.Participant;
import SRM.Activity;
import SRM.Capability;
import SRM.Functionality;
import SRM.Role;
import SRM.SRMFactory;
import SRM.SRMmodel;
import SUC.SUCmodel;
import SUC.UseCase;

public class SUC2SRM {
	
	//public SUC2SRM(){
		
	//}
	
public static SRMmodel transformSuc2srm(SUCmodel sucModel, SRMmodel srmModel ){
		
		for(Iterator<SUC.Role> iterator = sucModel.getRoles().iterator(); iterator.hasNext();){
			
			SUC.Role sucRole = iterator.next();
			
			
			if (sucRole.getType().getLiteral() == "System") {
				
				SRM.Role srmRole = SRMFactory.eINSTANCE.createRole();
				srmModel.getRoles().add(srmRole);
				srmRole.setName(sucRole.getName());
				
				srmRole.setLiveness(new String(srmRole.getName() + "="));
				//Transformation Code
				
				for (Iterator<UseCase> iterator2 = sucRole.getParticipates_in().iterator(); iterator2
						.hasNext();) {
					UseCase usecase = iterator2.next();
					// if the use case includes others it is inserted as a
					// capability
					// if the use case has another participant the capability's
					// name is the usecase name followed by .<Role name>
					// add a line in the liveness formula with the usecase
					// equals to the included ones connected by question marks
					// else it is inserted as an activity
					
					if (usecase.getInclude().size() > 0) {
						
						
						boolean exist = false;
						
						for (Iterator<Capability> iteratorCap = srmModel
								.getCapabilities().iterator(); iteratorCap
								.hasNext();) {
							
							Capability tmpCapability = iteratorCap.next();
							
							if (tmpCapability.getName().equalsIgnoreCase(usecase.getName())){
								srmRole.getCapabilities().add(tmpCapability);
								exist = true;
								break;
							}
							
						}
						
						if (!exist){
							
							Capability cp = SRMFactory.eINSTANCE.createCapability();
							
							srmModel.getCapabilities().add(cp);
							srmRole.getCapabilities().add(cp);
							if (usecase.getParticipant().size() > 0) {
								int count = 0;
								for (Iterator<SUC.Role> partIter = usecase.getParticipant().iterator(); partIter.hasNext(); ){
									SUC.Role tmpParticipant = partIter.next();
									if (tmpParticipant.getType().getLiteral() == "System"){
										count++;
									}
									
									if (count>1)
										break;
								}
								
								if (count>1){
									cp.setName(new String(usecase.getName() 	//));
											  + "_" + srmRole.getName()));
								}
								else{
									cp.setName(new String(usecase.getName()));
								}
								
								
							} else {
								cp.setName(new String(usecase.getName()));
							}
							
							///add to capability k sto first line
							if (srmRole.getLiveness()!=null){
								String[] first = srmRole.getLiveness().split("\n");
								String reconstruct = new String();
								
								first[0] += cp.getName() + "?";		// "_" + srmRole.getName() +
								
								
								for (int i=0; i<first.length; i++){
									reconstruct += first[i] +"\n" ; 
								}
								
								//System.out.println(reconstruct);
								//System.out.println("--------------------");
								
								srmRole.setLiveness(reconstruct);
								
							}
							
							
							srmRole.setLiveness(new String(srmRole.getLiveness() //+ "\n"
									+ cp.getName() + "=")); //+ "_" + srmRole.getName() 
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
							//srmRole.setLiveness(srmRole.getLiveness()+"\n");
							
						}
						
						
						
					} else {
						
						boolean exists = false;
						
						
						
						for (Iterator<Activity> iteratorAct = srmModel
								.getActivities().iterator(); iteratorAct
								.hasNext();) {
							Activity tmpActivity = iteratorAct.next();
							
							
							if (tmpActivity.getName().equalsIgnoreCase(usecase.getName())){
								srmRole.getRole_activities().add(tmpActivity);
								exists = true;
								break;
							}
							
						}
						
						if (!exists){
							Activity activity = SRMFactory.eINSTANCE.createActivity();
							
							srmModel.getActivities().add(activity);
							srmRole.getRole_activities().add(activity);
							activity.setName(usecase.getName());
							
							boolean existFunc = false;
							
							if (usecase.getSpecified_by() != null){
								
								for (Iterator<Functionality> funcIt = srmModel.getFunctionalities().iterator(); funcIt.hasNext();){
									Functionality func = funcIt.next();
									
									if (usecase.getSpecified_by().equalsIgnoreCase(func.getDescription())){
										activity.setFunctionality(func);
										existFunc = true;
										break;
									}
									
								}
								
								if (!existFunc){
									Functionality functionality = SRMFactory.eINSTANCE.createFunctionality();
									srmModel.getFunctionalities().add(functionality);
									functionality.setDescription(usecase.getSpecified_by());
									activity.setFunctionality(functionality);
								}
							}
							else{
								Functionality functionality = SRMFactory.eINSTANCE.createFunctionality();
								srmModel.getFunctionalities().add(functionality);
								functionality.setDescription(usecase.getSpecified_by());
								activity.setFunctionality(functionality);
							}	
							
						}
						
					}
				}
				for (Iterator<UseCase> iterator2 = sucRole.getParticipates_in().iterator(); iterator2.hasNext();) {
					UseCase usecase =  iterator2.next();
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
											.getRole_activities().iterator(); iterator5
											.hasNext();) {
										
										Activity tmpActivity = iterator5.next();
										if (tmpActivity.getName()
												.equalsIgnoreCase(
														tmpUseCase.getName())) {
											tmpCapability.getCapability_activities().add(
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
				
			}

		}
		return srmModel;
	}
	


public static SRMmodel srmImportAip(SRMmodel srmModel, AIPmodel aipModel){
	
	for(Iterator<SRM.Role> roleIterator = srmModel.getRoles().iterator(); roleIterator.hasNext();){
		
		Role srmRole = roleIterator.next();
		
		for(Iterator<AIP.Participant> aipIterator = aipModel.getParticipants().iterator(); aipIterator.hasNext();) {
			
			Participant aipParticipant = aipIterator.next();
		
			if(aipParticipant.getName().contains("_" + srmRole.getName()) == true) {
				
				//srmRole.setLiveness( new String(srmRole.getLiveness() + "\n"+ aipParticipant.getLiveness()));
				
				//String[] aipfirst = aipParticipant.getLiveness().split("=");
				String[] lines = srmRole.getLiveness().split("\n");
				String reconstruct = new String();
				
				// edw h prwth epanalhpsh einai mataih !!!
				
				for (int i=0; i<lines.length; i++){
					
					String[] temp = lines[i].split("=");
					
					if (temp[0].equalsIgnoreCase(aipParticipant.getName())){
						//lines[i] = aipParticipant.getLiveness();
						
						String[] left = lines[i].split("=");
						lines[i] = left[0] + "=" + aipParticipant.getLiveness();
					}
					
				}
				
				for (int i=0; i<lines.length; i++){
					reconstruct += lines[i] + "\n"; 
				}
				
				srmRole.setLiveness(reconstruct);
				
				
				
			}
		}
		
		
	}
	
	return srmModel;
}

}
