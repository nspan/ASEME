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
	
	private static int checkRole(Role role){
		
		for (int i = 0; i < role.getParticipates_in().size(); i++) {
			
			UseCase usecase = role.getParticipates_in().get(i);
			
			if ( usecase.getInclude().size() == role.getParticipates_in().size() -1){
				return i;
			}
			
		}
		
		return -1;
		
	}
	
	public static AIPmodel transformSuc2Aip(SUCmodel sucModel){
		
		AIPmodel aipModel = AIPFactory.eINSTANCE.createAIPmodel();
		
		List<Role> multiRolesParticipants = new LinkedList<Role>();
		Integer[] positions = new Integer[sucModel.getRoles().size()];

		
		for (Iterator<Role> roleIterator = sucModel.getRoles().iterator(); roleIterator.hasNext();) {
			Role role =  roleIterator.next();
			
			int useCasePosition = checkRole(role);
			
			if ( (useCasePosition != -1) && (role.getType().getLiteral() == "System"|| role.getType().getLiteral() == "Abstract") ){
				multiRolesParticipants.add(role);
				positions[multiRolesParticipants.size()-1] = useCasePosition;
			}
			
		}
		
		if( multiRolesParticipants.size() > 0 ){
			
			for (int i = 0; i < multiRolesParticipants.size(); i++) {
				
				
				Role multiRole =  multiRolesParticipants.get(i);
				UseCase protocolUseCase = multiRole.getParticipates_in().get(positions[i]);
				
				Protocol protocol = AIPFactory.eINSTANCE.createProtocol();
				protocol.setName(protocolUseCase.getName());
				
				// erwthsh an einai global initiator kai responder
				Participant participant0 = AIPFactory.eINSTANCE.createParticipant();
				Participant participant1 = AIPFactory.eINSTANCE.createParticipant();
				
				participant0.setName(multiRole.getName());
				participant1.setName(multiRole.getName() + "0");
				
				String liveness = "";
				
				for (Iterator<UseCase> iterator = protocolUseCase.getInclude().iterator(); iterator.hasNext();) {
					UseCase useCase = iterator.next();
					
					liveness += " " + useCase.getName() + " ?OP?"; 
					
				}
				
				participant0.setLiveness(liveness);
				participant1.setLiveness(liveness);
				
				protocol.getParticipants().add(participant0);
				protocol.getParticipants().add(participant1);
				
				aipModel.getParticipants().add(participant0);
				aipModel.getParticipants().add(participant1);
				aipModel.getProtocols().add(protocol);	
			
			}
		}
		
		
		
		for (Iterator<UseCase> iterator = sucModel.getUsecases().iterator(); iterator.hasNext();) {
			
			UseCase usecase = iterator.next();
			List<Role> systemRoleUseCaseParticipants = new LinkedList<Role>();
			
			for (Iterator<Role> iterator2 = usecase.getParticipant().iterator(); iterator2.hasNext();) {
				Role role =  iterator2.next();
				
				if (role.getType().getLiteral() == "System"|| role.getType().getLiteral() == "Abstract")
					systemRoleUseCaseParticipants.add(role);
				
				
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
												+ tmpUsecase.getName() + " ?OP? "));
					}
				}
				aipModel.getProtocols().add(tmpProtocol);
				// add the activities!!!
			}
			
		}
		
		
		for (int i=0; i<aipModel.getParticipants().size(); i++){
			
			String tmpForm = aipModel.getParticipants().get(i).getLiveness();
			
			if( tmpForm.endsWith(" ?OP? ")){	
				aipModel.getParticipants().get(i).setLiveness(tmpForm.substring(0, tmpForm.length()-6));
			}
			
		}
		
		return aipModel;
	}

}

