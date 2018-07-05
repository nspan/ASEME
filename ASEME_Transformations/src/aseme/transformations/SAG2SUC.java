package aseme.transformations;

import java.util.HashMap;
import java.util.Iterator;

//import asemedashboardview.views.actions.ASEMEXmlHelper;
import SAG.Actor;
import SAG.Goal;
import SAG.SAGmodel;
import SUC.Role;
import SUC.SUCFactory;
//import SUC.SUCPackage;
import SUC.SUCmodel;
import SUC.UseCase;

public class SAG2SUC {
	
	public static SUCmodel transformSag2Suc(SAGmodel sagModel){
		
		SUCmodel sucModel = SUCFactory.eINSTANCE.createSUCmodel();
		HashMap<String, Role> roles = new HashMap<String, Role>();
		
		for (Iterator<Actor> iterator = sagModel.getActors().iterator(); iterator.hasNext();) {
			
			Actor tmpActor = iterator.next();
			
			Role tmpRole = SUCFactory.eINSTANCE.createRole();
			
			tmpRole.setName(tmpActor.getName());
			sucModel.getRoles().add(tmpRole);
			roles.put(tmpRole.getName(), tmpRole);
		}
		
		for (Iterator<Goal> iterator = sagModel.getGoals().iterator(); iterator.hasNext();) {
			
			Goal tmpGoal = iterator.next();
			
			UseCase tmpUsecase = SUCFactory.eINSTANCE.createUseCase();
			
			tmpUsecase.setName(tmpGoal.getName());
			sucModel.getUsecases().add(tmpUsecase);
			tmpUsecase.getParticipant().add(roles.get(tmpGoal.getDepender().getName()));
			
			for (Iterator<Actor> iterator2 = tmpGoal.getDependee().iterator(); iterator2
					.hasNext();) {
				Actor tmpDependee = iterator2.next();
				//add a new participant only if it is not the same with the depender
				if (!tmpGoal.getDepender().getName().equalsIgnoreCase(tmpDependee.getName()))
					//add the participant
					tmpUsecase.getParticipant().add(roles.get(tmpDependee.getName()));
			}
			
			tmpUsecase.setSpecified_by(tmpGoal.getRequirements());
		}
		
		
		
		return sucModel;
		
	}
	

}
