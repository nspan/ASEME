package aseme.transformations;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;

import SRM.Activity;
import SRM.Capability;
import SRM.Role;
import SRM.SRMFactory;
import SRM.SRMPackage;
import SRM.SRMmodel;
import statechart.Model;
import statechart.Node;
import statechart.StatechartFactory;
import statechart.Transition;
import statechart.Variable;


public class SRM2EAC {
	
	public SRM2EAC(){
		
	}
	
	public static LinkedList<Model> transformSrm2Eac( SRMmodel srm){
		
		LinkedList<Model> list = new LinkedList<Model>();
		
		for (Iterator<Role> roleIterator = srm.getRoles().iterator(); roleIterator.hasNext();){
			
			Role tmpRole = roleIterator.next();
			
			Model stct = StatechartFactory.eINSTANCE.createModel();
			
			Liveness2Stct tr = new Liveness2Stct();
			//String tmpFormula = tmpRole.getLiveness();
			stct = tr.transformation(tmpRole.getLiveness());
			list.add(stct);
			
		}	
		
		return list;
		
	}
	
	public static Model postprocessing( SRMmodel srm, Model statechart, IProject project){
		
		LinkedList<String> capabilities = exportCapabilities(srm);
		
		DFTraversal(statechart.getNodes().get(0), srm, project, statechart, capabilities);
		
		return statechart;
	}
	
	private static void DFTraversal(Node root, SRMmodel srm, IProject project, Model statechart, LinkedList<String> capabilities){
		Iterator<Node> it = root.getChildren().iterator();
		Node search = null;
		while( it.hasNext()){
			search = it.next();
			//if(search.getLabel().equals(label)){
				//return search;
			if (search.getType() == "START" || search.getType() == "END"){
				DFTraversal(search, srm, project, statechart, capabilities);
			}
			else{
				if(search.getType() == "BASIC"){
					
					for(Iterator<Activity> acts = srm.getActivities().iterator(); acts.hasNext();){
						Activity tmpAct = acts.next();
						if (tmpAct.getName().equalsIgnoreCase(search.getName())){
							search.setActivity(tmpAct.getFunctionality().getDescription());
							break;
						}	
					}
					
					//System.out.println("Activity: " + search.getName());
					DFTraversal(search, srm, project, statechart, capabilities);
				}
				else{
					//SRM2EAC tr = new SRM2EAC();
					//LinkedList<String> capabilities = exportCapabilities(srm);
					for (int i=0; i<capabilities.size(); i++ ){
						
					//for (Iterator<Capability> caps = srm.getCapabilities().iterator(); caps.hasNext();){
					//	Capability tmpCap = caps.next();
					//	if (tmpCap.getName().equalsIgnoreCase(search.getName()) && (search.getName().contains("_"))){
							//AN einai capability k exei "_" MALLON exei AIP, opote psaxnw arxeio
						if (capabilities.get(i).equalsIgnoreCase(search.getName())){	// && (search.getName().contains("_"))){
							String[] score = search.getName().split("_");
							IFile temp = project.getFile(score[0]+".stct");
							
							//if ( temp != null){
							if(temp.exists()){
								Resource res = AsemeModelSaveHelper.staticResourceSet.getResource(URI.createPlatformResourceURI(temp.getFullPath().toString(), true), true);
								Model protocol = (Model)res.getContents().get(0);
								//System.out.println(protocol.getName());
								//String tempName = search.getLabel();
								Node nod = DFSearch(protocol.getNodes().get(0), search.getName());
								
								if (nod != null){
									
									associateVariablesTraversal(nod, search, statechart, nod.getLabel(), search.getLabel());
									
									for (Iterator<Transition> protocolTrasitionIterator = protocol.getTransitions().iterator(); 
											protocolTrasitionIterator.hasNext();){
										Transition protocolTransition = protocolTrasitionIterator.next();
										//if (protocolTransition.getTarget().equals(nod)){
										if(protocolTransition.getTE() != null){
											//String[] match = protocolTransition.getName().split("TO");
										/*	Node testNode = protocol.getNodes().get(0);
											if ( protocolTransition.getName().startsWith(testNode.getChildren().get(0).getName() + "TO") 
													|| protocolTransition.getName().endsWith("TO" + nod.getLabel())){
												System.out.println(protocolTransition.getName());
												for (Iterator<Transition> findStarting = statechart.getTransitions().iterator(); findStarting.hasNext();){
													
													Transition startingTr = findStarting.next();
													
													if (startingTr.getName().endsWith("TO"+search.getName())){
														System.out.println(startingTr.getName());
														startingTr.setTE(protocolTransition.getTE());
														break;
													}
												}
											}
											else if( protocolTransition.getName().endsWith("TO" +
													protocol.getNodes().get(0).getChildren().get(protocol.getNodes().get(0).getChildren().size()-1).getName() )){
												
												for (Iterator<Transition> findEnding = statechart.getTransitions().iterator(); findEnding.hasNext();){
													
													Transition endingTr = findEnding.next();
													
													if (endingTr.getName().contains( "TO"+search.getChildren().get( search.getChildren().size()-1 ).getName() )){
														System.out.println(endingTr.getName());
														endingTr.setTE(protocolTransition.getTE());
														break;
													}
												}
											}
											else{*/
												String [] nameTest = protocolTransition.getName().split("TO");
												String match = new String();
												if ( (nameTest[0].contains(".")&& nameTest[0].startsWith("0")) 
														|| (nameTest[1].contains(".") && nameTest[1].startsWith("0"))){
													match = protocolTransition.getName().replace(nod.getLabel(), search.getLabel());
												}
												else if((nameTest[0].contains(".")&& nameTest[0].startsWith("0")) 
														&& (nameTest[1].contains(".") && nameTest[1].startsWith("0"))){
													match = protocolTransition.getName().replace(nod.getLabel(), search.getLabel());
												}
												
												//else if (nameTest[1].contains(".") && nameTest[1].startsWith("0")){
													//match = protocolTransition.getName().replace(nod.getLabel(), search.getLabel());
												//}
												else{
													match = protocolTransition.getName();
												}
														
												for (Iterator<Transition> roleTransitionIterator = statechart.getTransitions().iterator(); roleTransitionIterator.hasNext();){
													Transition roleTransition = roleTransitionIterator.next();
													//if (roleTransition.getTarget().equals(search)){
													if (roleTransition.getName().equals(match)){
														roleTransition.setTE(protocolTransition.getTE());
														System.out.println("Update TE");
														break;
													}
												}
											//}	//edw kleinei to else	
										}
									}
								}
							}
						}
					}
					
					
					DFTraversal(search, srm, project, statechart,capabilities);
				}
			}
		}
	}

	
	private static Node DFSearch(Node root, String label){
		Iterator<Node> it = root.getChildren().iterator();
		Node search = null;
		while( it.hasNext()){
			search = it.next();
			if(search.getName().equals(label)){
				return search;
			}
			else if(search.getChildren().size()>0){
				Node newsearch = DFSearch(search, label);
				if (newsearch!=null)
					return newsearch;
			}
		}
		return null;
	}
	
	
	private static LinkedList<String> exportCapabilities(SRMmodel srm){
		LinkedList<String> caps = new LinkedList<String>();
		for (Iterator<Role> roleIter = srm.getRoles().iterator(); roleIter.hasNext();){
			Role tmpRole = roleIter.next();
			
			String[] temp = tmpRole.getLiveness().split("\n");
			
			for (int i=1; i<temp.length; i++){
				//String[] temp2 = new String[2];
				String[] temp2 = temp[i].split("=");
				if (temp2[0].contains("_")){
					caps.add(temp2[0]);
				}
			}
		}
		//for(int j=0; j<caps.size(); j++){
			//System.out.println(caps.get(j));
		//}
		
		return caps;
	}
	
	private static void associateVariablesTraversal(Node protocolNode, Node roleNode, Model stct, String protocolPrefix, String rolePrefix ){
		
		Iterator<Node> it = protocolNode.getChildren().iterator();
		Node current = null;
		while( it.hasNext()){
			current = it.next();
			
			if (current.getType() == "START" || current.getType() == "END"){
				associateVariablesTraversal(current, roleNode, stct, protocolPrefix, rolePrefix);
			}
			else{
				if (current.getVariables() != null){
					
					
					//if (current.getName().contains(".")){
						//roleTmp = DFSearch(roleNode, current.getName().replace(protocolPrefix, rolePrefix));
					//}
					//else{	
					//}
					
					
					Node roleTmp = StatechartFactory.eINSTANCE.createNode();
					roleTmp = DFSearch(roleNode, current.getName());
					
					for( Iterator<Variable> varIt = current.getVariables().iterator(); varIt.hasNext();){
						Variable tmpVar = StatechartFactory.eINSTANCE.createVariable();
						tmpVar = varIt.next();
						stct.getVariables().add(tmpVar);
						roleTmp.getVariables().add(tmpVar);
						System.out.println("Add Variable");
						}
				
				}	
				associateVariablesTraversal(current, roleNode, stct, protocolPrefix, rolePrefix);
			}		
		}
	}
	
}
