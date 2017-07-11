package aseme.transformations;

import java.util.Iterator;
import java.util.LinkedList;

import statechart.Model;
import statechart.StatechartFactory;
import AIP.AIPmodel;
import AIP.Participant;
import AIP.Protocol;

public class AIP2EAC {
	
	
	public static LinkedList<Model> transformAip2Eac( AIPmodel aip){
		
		LinkedList<Model> list = new LinkedList<Model>();
		
		
		
		for (Iterator<Protocol> protocolIterator = aip.getProtocols().iterator(); protocolIterator.hasNext();){
			Protocol protocol = protocolIterator.next();
			
			String liveness = new String();
			liveness = protocol.getName() + "=\n";
			
			for (Iterator<Participant> participantIterator = protocol.getParticipants().iterator();
					participantIterator.hasNext();){
				Participant tmpParticipant = participantIterator.next();
				
				String[] lines = liveness.split("\n");
				String reconstruct = new String();
				
				//sto first line meta to = den thelw ||
				if (lines.length<2){
					lines[0] += tmpParticipant.getName();
				}
				else{
					lines[0] += "||" + tmpParticipant.getName();
				}
				
				for (int i=0; i<lines.length; i++){
					reconstruct += lines[i] +"\n" ; 
				}
				
				liveness = reconstruct + tmpParticipant.getName() + "=" +tmpParticipant.getLiveness();
					
			}
			
			
			Model stct = StatechartFactory.eINSTANCE.createModel();
			
			Liveness2Stct tr = new Liveness2Stct();
			stct = tr.transformation(liveness);
			list.add(stct);
					
		}
		
		return list;
		
	}

}
