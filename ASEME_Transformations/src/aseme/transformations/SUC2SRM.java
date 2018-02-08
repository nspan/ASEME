package aseme.transformations;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	
public static SRMmodel transformSuc2srm(SUCmodel sucModel, SRMmodel srmModel ){ 
	
		
		
		for(Iterator<SUC.Role> iterator = sucModel.getRoles().iterator(); iterator.hasNext();){
			
			ArrayList<UseCase> included = new ArrayList<UseCase>();
			boolean firstLine = true;
			
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
						
						firstLine = false; //
						
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
									if (tmpParticipant.getType().getLiteral() == "System" || tmpParticipant.getType().getLiteral() == "Abstract"){
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
								
								
							} 
//							else {
//								cp.setName(new String(usecase.getName()));
//							}
							
							///add to capability k sto first line
							if (srmRole.getLiveness()!=null){
								String[] first = srmRole.getLiveness().split("\n");
								String reconstruct = new String();
								
								first[0] += cp.getName() + " ?OP? ";		// "_" + srmRole.getName() +
								
								
								for (int i=0; i<first.length; i++){
									
//									if (first[i].endsWith(" OP? ")){
//										first[i] = first[i].substring(0, first[i].length() - 5); 
//									}
									
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
								
								included.add(tmpUsecase);
								
								if (tmpUsecase.getParticipant().get(0).getName()
										.equalsIgnoreCase(srmRole.getName())) {
									srmRole.setLiveness(new String(srmRole
											.getLiveness()
											+ (firstLivenessRightSideElement ? ""
													: " ?OP? ") + tmpUsecase.getName()));
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
					//edw prepei na nai ta acts pou den einai se kapoio cap
					
					if ( (!(included.contains(usecase))) && (firstLine) ){
						String[] firstt = srmRole.getLiveness().split("\n");
						String reconstruct = new String();
						
						firstt[0] += usecase.getName() + " ?OP? ";		// "_" + srmRole.getName() +
						
						
						for (int i=0; i<firstt.length; i++){
							reconstruct += firstt[i] +"\n" ; 
						}
						
						//System.out.println(reconstruct);
						//System.out.println("--------------------");
						
						srmRole.setLiveness(reconstruct);
						
					}
					
				}
				
				//prin kleisei to if SystemRole pou vlepei to created srmRole
				
				String[] lastt = srmRole.getLiveness().split("\n");
				String reconstructed = new String();
				
				
				
				
				for (int i=0; i<lastt.length; i++){
					
					if (lastt[i].endsWith("?OP? ")){
						lastt[i] = lastt[i].substring(0, lastt[i].length() -6);
					}
						
					reconstructed += lastt[i] +"\n" ; 
				}
				
				//System.out.println(reconstruct);
				//System.out.println("--------------------");
				
				srmRole.setLiveness(reconstructed);
				
				
			}

		}// SUCROLE ITERATOR closing
		return srmModel;
	}
	


public static SRMmodel srmImportAip(SRMmodel srmModel, AIPmodel aipModel){
	System.out.println("Import AIP");
	
	
	//An alla3w thesh sta for opoios participant den kanei mats me tous rolous tou srm einai abstract
	//Alla an prepei na to kanw cap prepei na tsekarw k ta acts pou exei apo katw gt sta entos rolou apla symplhrwnw thn liveness
	
	for(Iterator<AIP.Participant> aipIterator = aipModel.getParticipants().iterator(); aipIterator.hasNext();) {
		
		Participant aipParticipant = aipIterator.next();
		
		boolean abstr = true;
	
		for(Iterator<SRM.Role> roleIterator = srmModel.getRoles().iterator(); roleIterator.hasNext();){
		
			Role srmRole = roleIterator.next();
		
		
		
			if( ( aipParticipant.getName().contains("_" + srmRole.getName()) ) || ( aipParticipant.getName().equals(srmRole.getName()) ) ) {
				
				abstr = false;
				
				System.out.println(aipParticipant.getName());
				
				//srmRole.setLiveness( new String(srmRole.getLiveness() + "\n"+ aipParticipant.getLiveness()));
				
				//String[] aipfirst = aipParticipant.getLiveness().split("=");
				String[] lines = srmRole.getLiveness().split("\n");
				String reconstruct = new String();
				
				// edw h prwth epanalhpsh einai mataih !!!
				
				for (int i=0; i<lines.length; i++){
					
//					System.out.println("mesa sto lines loop");
					
					String[] temp = lines[i].split("=");
					
					if (temp[0].equalsIgnoreCase(aipParticipant.getName())){
						//lines[i] = aipParticipant.getLiveness();
//						System.out.println("mesa sto equals if");
						String[] left = lines[i].split("=");
						lines[i] = left[0] + "=" + aipParticipant.getLiveness();
						break;
					}
					
				}
				
				for (int i=0; i<lines.length; i++){
					reconstruct += lines[i] + "\n"; 
				}
				
				srmRole.setLiveness(reconstruct);
				
				
				
			}
		}//SRMRoleIter closing
		
		if(abstr){
			
			Capability tmpCap = SRMFactory.eINSTANCE.createCapability();
			tmpCap.setName(aipParticipant.getName());
			
			tmpCap.setDescription(aipParticipant.getLiveness());
			
			srmModel.getCapabilities().add(tmpCap);
			
			//String[] tmp = aipParticipant.getLiveness().split("=");
			
			Pattern testPattern = Pattern.compile("\\w+_*w*");
			
			String formula = aipParticipant.getLiveness();	//.replaceAll("OP?", "?");
			
			Matcher testMatcher = testPattern.matcher(formula.replaceAll(" ", ""));
			
			while (testMatcher.find() && (!testMatcher.group().equals("OP"))){
				
				System.out.println(testMatcher.group());
				
				boolean found = false;
				
				for (int j=0; j<srmModel.getActivities().size(); j++){
					
					Activity tmpAct = srmModel.getActivities().get(j);
					
					if (tmpAct.getName().equals(testMatcher.group())){
						tmpCap.getCapability_activities().add(tmpAct);
						found = true;
						break;
					}
				}
				
				if(!found){
					Activity act = SRMFactory.eINSTANCE.createActivity();
					act.setName(testMatcher.group());
					srmModel.getActivities().add(act);
					Functionality functionality = SRMFactory.eINSTANCE.createFunctionality();
					srmModel.getFunctionalities().add(functionality);
					//functionality.setDescription(usecase.getSpecified_by());
					act.setFunctionality(functionality);
					
					tmpCap.getCapability_activities().add(act);
				}
				
			}
		}
		
		
		
		
	}
	
	return srmModel;
}

}
