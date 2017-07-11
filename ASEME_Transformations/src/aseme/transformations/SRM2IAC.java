package aseme.transformations;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;


import SRM.Activity;
import SRM.Capability;
import SRM.Functionality;
import SRM.Role;
import SRM.SRMFactory;
import SRM.SRMmodel;
import statechart.Model;
import statechart.Node;
import statechart.StatechartFactory;
import statechart.Transition;
import statechart.Variable;


public class SRM2IAC {
	
	public static boolean SRMcheck( SRMmodel srm ){
		
		
		
		
		boolean changed = false;
		
		ArrayList<Activity> addedActs = new ArrayList<Activity>();
		ArrayList<Capability> addedCaps = new ArrayList<Capability>();
		
		
		
		for (Iterator<Role> roles = srm.getRoles().iterator(); roles.hasNext();){
			
			Role current = roles.next();
			
			String tmpFormula = current.getLiveness();
			tmpFormula.replaceAll(" ","");
			
			
			//empty formula check
			if (tmpFormula.endsWith("="))
				break;
			
			//Hashtable<String, String> formulas = new Hashtable<String,String>();
			
			ArrayList<String> left = new ArrayList<String>();
			ArrayList<String> right = new ArrayList<String>();
			Pattern testPattern = Pattern.compile("\\w+_*w*");
			
			Matcher testMatcher = testPattern.matcher(tmpFormula);
			
			//ArrayList<String> matsakia = new ArrayList<String>();
			ArrayList<String> acts = new ArrayList<String>();
			
			StringTokenizer line = new StringTokenizer(tmpFormula, "\n");
			
			while (line.hasMoreTokens()) {
				String tmp = line.nextToken();
				StringTokenizer formulaElements = new StringTokenizer(tmp, "=");
				
				left.add(formulaElements.nextToken());
				right.add(formulaElements.nextToken());
				//String leftHandSide = formulaElements.nextToken();
				//String formula = formulaElements.nextToken();
				//formulas.put(leftHandSide, formula);
			}
			
			
			//export activities from liveness formula
			
			boolean found = false;
			
			for (int i=0; i<right.size(); i++ ){
				
				testMatcher = testPattern.matcher(right.get(i));
				
				
				while (testMatcher.find() && (!testMatcher.group().equals("OP")) ){
					
					//matsakia.add(testMatcher.group());
					
					//String termaTmp = testMatcher.group();
					found = false;
					
					for (int l=1; l<left.size(); l++ ){
						if (testMatcher.group().equals(left.get(l))){
							found = true;
							break;
						}
					}
					
					if (!found){
						acts.add(testMatcher.group());
					}
						
					
				}
			}
			
			
			//add non-existing activities to model
			
			boolean actSearch = false;
			
			for (int a=0; a<acts.size(); a++){
				
				actSearch = false;
				
				for (Iterator<Activity> iteratorAct = srm
						.getActivities().iterator(); iteratorAct
						.hasNext();) {
					Activity tmpAct = iteratorAct.next();
					if(acts.get(a).equals(tmpAct.getName())){
						actSearch = true;
						break;
					}
				}
				
				if(!actSearch){
					changed = true;
					Activity ac = SRMFactory.eINSTANCE.createActivity();
					srm.getActivities().add(ac);
					addedActs.add(ac);
					//current.getRole_activities().add(ac);
					ac.setName(acts.get(a));
					
					Functionality func = SRMFactory.eINSTANCE.createFunctionality();
					srm.getFunctionalities().add(func);
					//functionality.setDescription(usecase.getSpecified_by());
					ac.setFunctionality(func);
				}
				
			}
			
			//check for new caps and create them
			
			boolean capSearch = false;
			
			for(int c=1; c<left.size(); c++){
				
				ArrayList<Activity> tmpActList = new ArrayList<Activity>();
				capSearch = false;
				
				for (Iterator<Capability> iteratorCap = srm
						.getCapabilities().iterator(); iteratorCap
						.hasNext();) {
					
					Capability tmpCap = iteratorCap.next();
					
					if(left.get(c).equals(tmpCap.getName())){
						capSearch = true;
						
						if( !(current.getCapabilities().contains(tmpCap)) ){
							current.getCapabilities().add(tmpCap);
						}
						
						//add new acts to capability (if any)
						
						testMatcher = testPattern.matcher(right.get(c));
						
						while(testMatcher.find()){
							
							for (Iterator<Activity> iteratorCapAct = tmpCap.getCapability_activities()
									.iterator(); iteratorCapAct
									.hasNext();) {
								Activity tmpCapAct = iteratorCapAct.next();
								
								if (tmpCapAct.getName().equals(testMatcher.group())){
									break;
								}
								else{//exw activity sto liveness k oxi sto model
									
									for (int z = srm.getActivities().size()-1; z>=0; z--){
										
											
										Activity liveAct = srm.getActivities().get(z);
										
										
										if( (liveAct.getName().equals(testMatcher.group()))){
											System.out.println(testMatcher.group());
		 									tmpActList.add(liveAct);
											break;
										}
									}
									
								}
							}
						}
						
						tmpCap.getCapability_activities().addAll(tmpActList);
						
						//break;
					}
				}
				
				if(!capSearch){
					changed = true;
					Capability cap = SRMFactory.eINSTANCE.createCapability();
					
					srm.getCapabilities().add(cap);
					current.getCapabilities().add(cap);
					cap.setName(left.get(c));
					
					addedCaps.add(cap);

					testMatcher = testPattern.matcher(right.get(c));
					
					while(testMatcher.find()){
						
						for (Iterator<Activity> iteratorAct = srm
								.getActivities().iterator(); iteratorAct
								.hasNext();) {
							Activity tmpActiv = iteratorAct.next();
							
							if (testMatcher.group().equals(tmpActiv.getName())){
								cap.getCapability_activities().add(tmpActiv);
								break;
							}
						}
						
					}
					
					
					
				}
				
				
			}
			
			
		}//Role Iterator closing
		
		
		for (int g=0; g<addedActs.size(); g++){
			
			Activity addedAct = addedActs.get(g);
			
			for (int h=0; h<srm.getCapabilities().size(); h++){
				
				if (srm.getCapabilities().get(h).getName().equals(addedAct.getName())){
					srm.getCapabilities().remove(h);
					break;
				}
				
			}
		}
		
		for (int d=0; d<addedCaps.size(); d++){
			
			Capability addedCap = addedCaps.get(d);
			
			for (int f=0; f<srm.getActivities().size(); f++){
				
				if (srm.getActivities().get(f).getName().equals(addedCap.getName())){
					srm.getActivities().remove(f);
					break;
				}
				
			}
		}
		
		
		return changed;
		
		//AsemeModelSaveHelper.saveURI(srm, model);
		//System.out.println("fadsgf");
		
		
		
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
		//LinkedList<Node> subTreeRoots = new LinkedList<Node>();
		
		DFTraversal(statechart.getNodes().get(0), srm, project, statechart, capabilities);//, subTreeRoots);
		
		return statechart;
	}
	
	private static void DFTraversal(Node root, SRMmodel srm, IProject project, Model statechart, LinkedList<String> capabilities){//,LinkedList<Node> subTreeRoots){
		
		Iterator<Node> it = root.getChildren().iterator();
		Node search = null;
		while( it.hasNext()){
			search = it.next();
			
			 if (search.getType() == "START" || search.getType() == "END"){
				 
				// System.out.println(search.getName());
				//do nothing
			}
			else{
				if(search.getType() == "BASIC"){
					
					//System.out.println(search.getName());
					
					for(Iterator<Activity> acts = srm.getActivities().iterator(); acts.hasNext();){
						Activity tmpAct = acts.next();
						if (tmpAct.getName().equalsIgnoreCase(search.getName())){
							search.setActivity(tmpAct.getFunctionality().getDescription());
							break;
						}	
					}
				}
				else if(search.getType() == "OR" ) {
					
					//System.out.println("OR\t"+search.getName());		
					for (int i=0; i<capabilities.size(); i++ ){
						
					
							//AN einai capability k exei "_" MALLON exei AIP, opote psaxnw arxeio
						if (capabilities.get(i).equalsIgnoreCase(search.getName())){	// && (search.getName().contains("_"))){
							String[] score = search.getName().split("_");
							IFile temp = project.getFile(score[0]+".stct");
							
							//System.out.println("file prefix:\t"+score[0]);
							
							if(temp.exists()){
								Resource res = AsemeModelSaveHelper.staticResourceSet.getResource(URI.createPlatformResourceURI(temp.getFullPath().toString(), true), true);
								Model protocol = (Model)res.getContents().get(0);
								//System.err.println("DFSearch for :\t"+ search.getName());
								
								Node nod = DFSearch(protocol.getNodes().get(0), search.getName());
								
								if (nod != null){
									//System.err.println( "nod\t"+nod.getName());
		
									associateVars(nod,search,statechart, nod.getLabel(), search.getLabel());
									associateTEs(protocol, statechart, nod.getLabel(), search.getLabel());		
											
									break;					
								}	
							}
						}
					}
					DFTraversal(search, srm, project, statechart,capabilities);
				}
				
				else if( search.getType() == "AND" ) {
					DFTraversal(search, srm, project, statechart,capabilities);
				}
				
				
				else{	//Conditional Node
						//System.out.println(search.getName());
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
		
		return caps;
	}
	
	
	private static void associateVars(Node protocolNode, Node roleNode, Model stct, String protocolPrefix, String rolePrefix){
		
		for (Iterator<Node> protocolIterator = protocolNode.getChildren().iterator();
				protocolIterator.hasNext();){	
			Node pNode = protocolIterator.next();
			
			if ( pNode.getVariables().size()>0){// != null ){
				
				for (Iterator<Node> roleIterator = roleNode.getChildren().iterator();
						roleIterator.hasNext();){
					Node rNode = roleIterator.next();
					
					if( rNode.getLabel().equals( pNode.getLabel().replace(protocolPrefix, rolePrefix)) ){
						
						for( Iterator<Variable> varIt = pNode.getVariables().iterator(); varIt.hasNext();){
							
							//for(int o=0; 0<current.getVariables().size(); o++){
							
							Variable tmpVar = StatechartFactory.eINSTANCE.createVariable();
							tmpVar = varIt.next();		//current.getVariables().get(o);	// 
							stct.getVariables().add(tmpVar);
							rNode.getVariables().add(tmpVar);
							//System.out.println("Add Var:\t" +tmpVar.getName());
							}
						System.out.println("break");
						break;	//??
						
					}
				}
			}
			
		}
			
		
	}
	
	private static void associateTEs(Model protocol, Model role, String protocolPrefix, String rolePrefix ){
		
		for (Iterator<Transition> protocolTransitionIterator = protocol.getTransitions().iterator();
				protocolTransitionIterator.hasNext();){
			Transition protocolTransition = protocolTransitionIterator.next();
			
			if ( protocolTransition.getTE() != null){	// && protocolTransition.getName().contains(protocolPrefix)){
				
				String [] nameTest = protocolTransition.getName().split("TO");
				String match = new String();
				
				if((nameTest[0].contains(".")&& nameTest[0].startsWith("0")) 
						&& (nameTest[1].contains(".") && nameTest[1].startsWith("0"))){
					match = protocolTransition.getName().replaceAll(protocolPrefix, rolePrefix);
				}
				else if ( (nameTest[0].contains(".")&& nameTest[0].startsWith("0")) 
						|| (nameTest[1].contains(".") && nameTest[1].startsWith("0"))){		//if Start2Basic or Basic2End
					match = protocolTransition.getName().replace(protocolPrefix, rolePrefix);
				}
				else{
					match = protocolTransition.getName();
				}
				
				for(Iterator<Transition> roleTransitionIterator = role.getTransitions().iterator();
						roleTransitionIterator.hasNext();){
					Transition roleTransition = roleTransitionIterator.next();
					
//					if ( (roleTransition.getName().equals(protocolTransition.getName().replace(protocolPrefix, rolePrefix)) )
//							|| (roleTransition.getName().equals(protocolTransition.getName()) ) ){
					
					if( roleTransition.getName().equals(match)){
						
						roleTransition.setTE(protocolTransition.getTE());
						
//						System.out.println("break");
						break;	//??
						
					}
				}
				
			}
		}
		
	}
	
	
	
	
}
