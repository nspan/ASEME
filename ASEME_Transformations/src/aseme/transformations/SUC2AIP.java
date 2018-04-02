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

	public static AIPmodel transformSuc2Aip(SUCmodel sucModel) {

		AIPmodel aipModel = AIPFactory.eINSTANCE.createAIPmodel();

		for (Iterator<UseCase> iterator = sucModel.getUsecases().iterator(); iterator.hasNext();) {

			UseCase usecase = iterator.next();
			List<Role> systemRoleUseCaseParticipants = new LinkedList<Role>();

			for (Iterator<Role> iterator2 = usecase.getParticipant().iterator(); iterator2.hasNext();) {
				Role role = iterator2.next();
				if (role.getType().getLiteral() == "System" || role.getType().getLiteral() == "Abstract")
					systemRoleUseCaseParticipants.add(((Role) role));
			}

			if (systemRoleUseCaseParticipants.size() > 1 && usecase.getInclude().size() > 0) {

				Protocol tmpProtocol = AIPFactory.eINSTANCE.createProtocol();
				tmpProtocol.setName(usecase.getName());

				HashMap<String, Participant> participants = new HashMap<String, Participant>();

				for (Iterator<Role> iterator2 = systemRoleUseCaseParticipants.iterator(); iterator2.hasNext();) {

					Role systemRole = iterator2.next();
					Participant tmpParticipant = AIPFactory.eINSTANCE.createParticipant();

					tmpParticipant.setName(new String(tmpProtocol.getName() + "_" + systemRole.getName()));
					tmpProtocol.getParticipants().add(tmpParticipant);
					aipModel.getParticipants().add(tmpParticipant);
					participants.put(tmpParticipant.getName(), tmpParticipant);
					tmpParticipant.setLiveness(new String(""));// tmpParticipant
					// .getName() + "="));
				}

				for (Iterator<UseCase> iterator2 = usecase.getInclude().iterator(); iterator2.hasNext();) {
					UseCase tmpUsecase = (UseCase) iterator2.next();
					for (Iterator<Role> iterator3 = tmpUsecase.getParticipant().iterator(); iterator3.hasNext();) {
						Role tmpUsecaseParticipant = (Role) iterator3.next();
						if (participants.get(
								tmpProtocol.getName() + "_" + tmpUsecaseParticipant.getName()) != null) {
							participants.get(tmpProtocol.getName() + "_"
									+ tmpUsecaseParticipant.getName())
									.setLiveness(new String(participants
											.get(tmpProtocol.getName() + "_"
													+ tmpUsecaseParticipant.getName())
											.getLiveness() + tmpUsecase.getName() + " ?OP? "));
						}
					}
				}
				aipModel.getProtocols().add(tmpProtocol);
				// add the activities!!!
			}
		}

		// clean up forgotten ?OP? at the end of the liveness stream
		for (int i = 0; i < aipModel.getParticipants().size(); i++) {

			String tmpForm = aipModel.getParticipants().get(i).getLiveness();

			if (tmpForm.endsWith(" ?OP? ")) {
				aipModel.getParticipants().get(i).setLiveness(tmpForm.substring(0, tmpForm.length() - 6));
			}

		}

		return aipModel;
	}

}
