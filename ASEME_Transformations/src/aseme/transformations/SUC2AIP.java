package aseme.transformations;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import SUC.Role;
import SUC.SUCmodel;
import SUC.UseCase;
import AIP.AIPFactory;
import AIP.AIPmodel;
import AIP.Participant;
import AIP.Protocol;

public class SUC2AIP {
	
	//public SUC2AIP(){
		
	//}
	
	public static AIPmodel transformSuc2Aip(SUCmodel sucModel){
		
		AIPmodel aipModel = AIPFactory.eINSTANCE.createAIPmodel();
		
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
					tmpParticipant.setLiveness(new String(""));//tmpParticipant
						//	.getName() + "="));
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
								.getName()).setLiveness(new String(
										participants.get(
												tmpProtocol.getName()
												+ "_"
												+ tmpUsecase.getParticipant()
												.get(0).getName())
												.getLiveness()
												+ tmpUsecase.getName() + "?"));
					}
				}
				aipModel.getProtocols().add(tmpProtocol);
				// add the activities!!!
			}
		}
		
		
		return aipModel;
	}

}
