package aseme.transformations.xpdl;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.BpmnModelElementInstance;
import org.camunda.bpm.model.bpmn.instance.Collaboration;
import org.camunda.bpm.model.bpmn.instance.Definitions;
import org.camunda.bpm.model.bpmn.instance.EndEvent;
import org.camunda.bpm.model.bpmn.instance.FlowNode;
import org.camunda.bpm.model.bpmn.instance.Lane;
import org.camunda.bpm.model.bpmn.instance.LaneSet;
import org.camunda.bpm.model.bpmn.instance.ParallelGateway;
import org.camunda.bpm.model.bpmn.instance.Participant;
import org.camunda.bpm.model.bpmn.instance.Process;
import org.camunda.bpm.model.bpmn.instance.SequenceFlow;
import org.camunda.bpm.model.bpmn.instance.StartEvent;
import org.camunda.bpm.model.bpmn.instance.bpmndi.BpmnDiagram;
import org.camunda.bpm.model.bpmn.instance.bpmndi.BpmnEdge;
import org.camunda.bpm.model.bpmn.instance.bpmndi.BpmnPlane;
import org.camunda.bpm.model.bpmn.instance.bpmndi.BpmnShape;
import org.camunda.bpm.model.bpmn.instance.dc.Bounds;
import org.camunda.bpm.model.bpmn.instance.di.Waypoint;


public class Liveness2BPMN {
	public Participant participant;
	public Collaboration collaboration;
	public BpmnModelInstance modelInstance;
	public Definitions definitions;
	public BpmnPlane processPlane;
	public Process process;
	public BpmnDiagram bpmnDiagram;
	public StartEvent startEvent;
	public BpmnShape startEventShape;
	public BpmnShape participantShape;
	public EndEvent endEvent;
	BpmnShape endEventShape;
	public LaneSet laneset;
	public Lane lane1;
	public static String participantname;
	public static List<String> rolelist;
	public int lanecounter = 0;
	public BpmnShape laneshape;
	BpmnModelElementInstance task2;
	public ParallelGateway xorstar;
	public BpmnShape xorshapebrackets;
	
	public Liveness2BPMN() {
		// TODO Auto-generated constructor stub
	}
	
	public static <T> void main(String[] args) throws IOException{
	//	String agent1 = "ComplexProvider=SP~\n"
	//			+ "SP=ReceiveRequestMessage.ProcessRequest.SendResponseMessage\n"
	//			+ "ProcessRequest=(DecideRouteType.SR.SortRoutes)|(DecidePOITypes.SR.DecidePois.SR)\n"
	//			+ "SR=SendRequestMessage.ReceiveResponseMessage";
//
//		String agent2 = "Testmessager1=ReceiveRequestMessage.ProcessRequest.SendResponseMessage\n"
//				+ "ProcessRequest=SendRequestMessage.ReceiveResponseMessage";
		
		//String agent1 = "ServiceRequester=A.SendRequest.ReceiveResponse";
		
		//String agent2 = "ServiceProvider=A|ReceiveRequest|SendRequesttoComplex|ReceiveResponsefromComplex|esSendResponsetoRequester";
		
		String agent1 = "x=SendRequesttoagent2.ReceiveResponsenek";
		String agent2 = "f=SendRequestnek.ReceiveResponseplanet";
		String agent3 = "ComplexService=ReceiveRequestfromProvider.ProcessRequest.SendResponsetoProvider";

		List<String> roles = new LinkedList<String>();
		//roles.add(liveness);
		roles.add(agent1);
		roles.add(agent2);
		roles.add(agent3);
		
		rolelist = roles;
		
		String outputFile = "C:/Users/nek/Desktop/Myfirst.bpmn";
		File f = new File(outputFile);
		try {
			outputFile = f.getCanonicalPath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
		
		System.out.println("Creating BPMN Model.\n");
		Liveness2BPMN liveness2bpmn = new Liveness2BPMN();
		//liveness2bpmn.createRoles(roles);
		//liveness2bpmn.createLanes(roles);
		liveness2bpmn.createCollaborationLanes(roles);
		Bpmn.writeModelToFile(f, liveness2bpmn.modelInstance);
		System.out.println("End of the program");	
	}

	public void writeModelToFile(File f, BpmnModelInstance modelInstance2) {
		// TODO Auto-generated method stub
		Bpmn.writeModelToFile(f, modelInstance);
	}

	public void createCollaborationLanes(List<String> roles){
		// TODO Auto-generated method stub
		Live2BPMN live2bpmn = new Live2BPMN();
		 //Calendar to create unique xmiids
		Calendar cal2 = Calendar.getInstance();
		long d = cal2.getTimeInMillis();
		if(lanecounter == 0){
			
			modelInstance = Bpmn.createEmptyModel();
		    definitions = modelInstance.newInstance(Definitions.class);
		    definitions.setId("Idok");
		    definitions.setTargetNamespace("http://www.jboss.org/drools");
			definitions.setExporter("org.eclipse.bpmn.modeler.core"); 
			definitions.setTypeLanguage("http://www.java.com/javaTypes");
		    modelInstance.setDefinitions(definitions);
		    
		    bpmnDiagram = modelInstance.newInstance(BpmnDiagram.class);
		    bpmnDiagram.setId("diagram");
		    bpmnDiagram.setName("diagram");
		    bpmnDiagram.setDocumentation("bpmn diagram element");
		    bpmnDiagram.setResolution(120.0);
			modelInstance.getDefinitions().addChildElement(bpmnDiagram);
		    
			
		    collaboration = modelInstance.newInstance(Collaboration.class);
		    collaboration.setId("collaborationid");
		    modelInstance.getDefinitions().addChildElement(collaboration);
			d = cal2.getTimeInMillis();
			processPlane = modelInstance.newInstance(BpmnPlane.class);
			processPlane.setId("plane"+d);
			
			
			StringTokenizer line2 = new StringTokenizer(roles.get(0), "=");
			participantname = line2.nextToken();
			
			live2bpmn.firstacts = new LinkedList<BpmnModelElementInstance>();
			live2bpmn.formulas = new Hashtable<String, String>();
			  StringTokenizer line = new StringTokenizer(roles.get(0), "\n");
			  while (line.hasMoreTokens()) {
					String tmp = line.nextToken();
					StringTokenizer formulaElements = new StringTokenizer(tmp, "=");
					String leftHandSide = formulaElements.nextToken();
					live2bpmn.setleftHandSide(leftHandSide);
					String formula = formulaElements.nextToken();
//					if(formula.contains(leftHandSide)){
//						System.out.println("There is a self-reference in the formula and the program will exit.");
//						System.out.println("Check your formula.");
//						System.exit(0);
//					}
					//String formula1 = preprocessing(formula);
					//System.out.println("What is the formula after preprocessing:"
						//	+ formula1);
					live2bpmn.formulas.put(leftHandSide, formula);
				}
			  //System.out.println("What are the formulas?" + live2bpmn.formulas);
			  line = new StringTokenizer(roles.get(0), "\n");
			  StringTokenizer formulaElements = new StringTokenizer(line.nextToken(),
						"=");
			  String leftHandSide = formulaElements.nextToken();
			  d = cal2.getTimeInMillis(); 

				  d = cal2.getTimeInMillis();  
				participant = modelInstance.newInstance(Participant.class);
				participant.setId("anid00"+d);
				participant.setName(participantname);
				collaboration.addChildElement(participant);
				
				 d = cal2.getTimeInMillis();
				 process = createElement(definitions, "transformedprocess"+d, Process.class);
				 process.setName("roleprocess"+d);
				
				 participant.setProcess(process);
				 participantShape = modelInstance.newInstance(BpmnShape.class);
				 participantShape.setId("participantshape");
				 participantShape.setBpmnElement(participant);

				 processPlane.setBpmnElement(process);
				 bpmnDiagram.setBpmnPlane(processPlane); 
				 processPlane.getDiagramElements().add(participantShape);
				 
				 d = cal2.getTimeInMillis();
				 laneset = createElement(process, "lanesetid"+d, LaneSet.class);
				 d = cal2.getTimeInMillis();
				 lane1 = createElement(laneset, "lane"+d, Lane.class);
				    
				 startEvent = createElement(process, "start", StartEvent.class);
				 startEventShape = modelInstance.newInstance(BpmnShape.class);
				 startEventShape.setId("startShape");
				 startEventShape.setBpmnElement(startEvent);
				 processPlane.getDiagramElements().add(startEventShape);
				 live2bpmn.setpreviousshape(startEventShape);
				    
				 live2bpmn.xaxis = 0.0;
			 Bounds startEventBounds = modelInstance.newInstance(Bounds.class);
			 startEventBounds.setHeight(36.0);
				 startEventBounds.setWidth(36.0);
				 startEventBounds.setX(live2bpmn.xaxis+70.0);
				 live2bpmn.xaxis = live2bpmn.xaxis+70.0;
				 startEventBounds.setY(live2bpmn.yaxis+70.0);
				 live2bpmn.yaxis=live2bpmn.yaxis+70.0;
				 startEventShape.setBounds(startEventBounds);

				 endEvent = createElement(process, "end", EndEvent.class);
				 endEventShape = modelInstance.newInstance(BpmnShape.class);
				 endEventShape.setId("endShape");
				 endEventShape.setBpmnElement(endEvent);
				 processPlane.getDiagramElements().add(endEventShape);
				   
				 live2bpmn.laneset = laneset;
				 live2bpmn.lane1 = lane1;
				 live2bpmn.process = process;
				 live2bpmn.modelInstance = modelInstance;
				 live2bpmn.processPlane = processPlane;
				 live2bpmn.bpmnDiagram = bpmnDiagram;
					//create process Time for recursion
				 BpmnModelElementInstance task2;
					
					task2 = live2bpmn.createMessagePartners(live2bpmn.formulas.get(leftHandSide), process, (BpmnModelElementInstance) startEvent);
				
				    // create flows
				    SequenceFlow flow = createSequenceFlow(process, (FlowNode)task2, endEvent);
				    
				    BpmnEdge flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
					d = cal2.getTimeInMillis();
					flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
					flowEdge.setId("flowEdgelanes"+d);
					flowEdge.setBpmnElement(flow);
					processPlane.getDiagramElements().add(flowEdge);
					
					 Waypoint startWaypoint = modelInstance.newInstance(Waypoint.class);
					 startWaypoint.setX(668.0);
					 startWaypoint.setY(330.0);
					 flowEdge.getWaypoints().add(startWaypoint);

					Waypoint endWaypoint = modelInstance.newInstance(Waypoint.class);
					endWaypoint.setX(718.0);
					endWaypoint.setY(330.0);
					flowEdge.getWaypoints().add(endWaypoint);	
				    
				    Bounds endEventBounds = modelInstance.newInstance(Bounds.class);
				    endEventBounds.setHeight(36.0);
				    endEventBounds.setWidth(36.0);
				    endEventBounds.setX(live2bpmn.xaxis+70.0);
				    live2bpmn.xaxis=live2bpmn.xaxis+70.0;
				    endEventBounds.setY(live2bpmn.yaxis+70.0);
				    live2bpmn.yaxis=live2bpmn.yaxis+70.0;
				    endEventShape.setBounds(endEventBounds);
				    
				    Bounds participantBounds = modelInstance.newInstance(Bounds.class);
				    participantBounds.setHeight(live2bpmn.xaxis+100.0);
				    participantBounds.setWidth(endEventBounds.getY()-startEventBounds.getY());
				    participantBounds.setX(startEventBounds.getX());
				    participantBounds.setY(startEventBounds.getY());
				    participantShape.setBounds(participantBounds);
				    
				    live2bpmn.setpreviousshape(null);
				    
		 int i = 0;
			for(i=1;i<roles.size();i++){
				System.out.println("Mpikes edo?");
				try {
					line2 = new StringTokenizer(roles.get(i), "=");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
			try {
				participantname = line2.nextToken();
			} catch (Exception e) {
			}
				live2bpmn.formulas = new Hashtable<String, String>();
				live2bpmn.firstacts = new LinkedList<BpmnModelElementInstance>();
				try {
					line = new StringTokenizer(roles.get(i), "\n");
				} catch (Exception e) {
				}
				while (line.hasMoreTokens()){
				String tmp = line.nextToken();
					formulaElements = new StringTokenizer(tmp, "=");
					leftHandSide = formulaElements.nextToken();
					live2bpmn.setleftHandSide(leftHandSide);
					String formula = formulaElements.nextToken();
							if(formula.contains(leftHandSide)){
								System.out.println("There is a self-reference in the formula and the program will exit.");
								System.out.println("Check your formula.");
								System.exit(0);
							}
							//String formula1 = preprocessing(formula);
							//System.out.println("What is the formula after preprocessing:"
								//	+ formula1);
					live2bpmn.formulas.put(leftHandSide, formula);
					}				
				  try {
					line2 = new StringTokenizer(roles.get(i), "\n");
				} catch (Exception e) {
				}
					  try {
						formulaElements = new StringTokenizer(line2.nextToken(),"=");
					} catch (Exception e) {
					}
					  leftHandSide = formulaElements.nextToken();

					  d = cal2.getTimeInMillis();  
					participant = modelInstance.newInstance(Participant.class);
					participant.setId("anid00"+i+d);
					participant.setName(participantname);
					collaboration.addChildElement(participant);
					
					 d = cal2.getTimeInMillis();
					 process = createElement(definitions, "transformedprocess"+i+d, Process.class);
					 process.setName("roleprocess"+d);
					
					 participant.setProcess(process);
					 participantShape = modelInstance.newInstance(BpmnShape.class);
					 participantShape.setId("participantshape"+i);
					 participantShape.setBpmnElement(participant);

					 processPlane.setBpmnElement(process);
					 bpmnDiagram.setBpmnPlane(processPlane); 
					 processPlane.getDiagramElements().add(participantShape);
					 
					 d = cal2.getTimeInMillis();
					 laneset = createElement(process, "lanesetid"+i+d, LaneSet.class);
					 d = cal2.getTimeInMillis();
					 lane1 = createElement(laneset, "lane"+i+d, Lane.class);
					     
					 live2bpmn.laneset = laneset;
					 live2bpmn.lane1 = lane1;
					 live2bpmn.process = process;
					 live2bpmn.modelInstance = modelInstance;
					 live2bpmn.processPlane = processPlane;
					 live2bpmn.bpmnDiagram = bpmnDiagram;
						
					 live2bpmn.createMessageLanes(live2bpmn.formulas.get(leftHandSide), process);
								    
					    participantBounds = modelInstance.newInstance(Bounds.class);
					    participantBounds.setHeight(live2bpmn.xaxis+100.0);
					    participantBounds.setWidth(live2bpmn.yaxis+100.0);
					    participantBounds.setX(live2bpmn.xaxis);
					    participantBounds.setY(live2bpmn.yaxis);
					    participantShape.setBounds(participantBounds);
					    
					    live2bpmn.setpreviousshape(null);
			}
		}
	}
	
	public void createLanes(List<String> roles){
		Live2BPMN live2bpmn = new Live2BPMN();
		
		modelInstance = Bpmn.createEmptyModel();
	    definitions = modelInstance.newInstance(Definitions.class);
	    definitions.setId("Idok");
	    definitions.setTargetNamespace("http://www.jboss.org/drools");
		definitions.setExporter("org.eclipse.bpmn.modeler.core"); 
		definitions.setTypeLanguage("http://www.java.com/javaTypes");
	    modelInstance.setDefinitions(definitions);
	    
	    bpmnDiagram = modelInstance.newInstance(BpmnDiagram.class);
	    bpmnDiagram.setId("diagram");
	    bpmnDiagram.setName("diagram");
	    bpmnDiagram.setDocumentation("bpmn diagram element");
	    bpmnDiagram.setResolution(120.0);
		modelInstance.getDefinitions().addChildElement(bpmnDiagram);
	    
	    //Calendar to create unique xmiids
		  	Calendar cal2 = Calendar.getInstance();
			long d = cal2.getTimeInMillis();
			collaboration = modelInstance.newInstance(Collaboration.class);
	    	collaboration.setId("collaborationid");
	    	modelInstance.getDefinitions().addChildElement(collaboration);
		 	d = cal2.getTimeInMillis();
		 	processPlane = modelInstance.newInstance(BpmnPlane.class);
		 	processPlane.setId("plane"+d);

		 	d = cal2.getTimeInMillis();
			process = createElement(definitions, "transformedprocess"+d, Process.class);
			process.setName("roleprocess"+d);
			processPlane.setBpmnElement(process);
			bpmnDiagram.setBpmnPlane(processPlane); 
			
				startEvent = createElement(process, "start", StartEvent.class);
				startEventShape = modelInstance.newInstance(BpmnShape.class);
				startEventShape.setId("startShape");
				startEventShape.setBpmnElement(startEvent);
				processPlane.getDiagramElements().add(startEventShape);
			 
			 	live2bpmn.xaxis = 0.0;
			    Bounds startEventBounds = modelInstance.newInstance(Bounds.class);
			    startEventBounds.setHeight(36.0);
			    startEventBounds.setWidth(36.0);
			    startEventBounds.setX(live2bpmn.xaxis+70.0);
			    live2bpmn.xaxis = live2bpmn.xaxis+70.0;
			    startEventBounds.setY(live2bpmn.yaxis+70.0);
			    live2bpmn.yaxis=live2bpmn.yaxis+70.0;
			    startEventShape.setBounds(startEventBounds);
			    
			    d = cal2.getTimeInMillis();
				ParallelGateway para = createElement(process, "para1"+d, ParallelGateway.class);
				modelInstance.getModelElementById("para1"+d);
				para.setName("parallel");
				d=cal2.getTimeInMillis();
				BpmnShape parashape = modelInstance.newInstance(BpmnShape.class);
				parashape.setId("parashape"+d);
				parashape.setBpmnElement(para);
				processPlane.getDiagramElements().add(parashape);
				
				Bounds paraBounds = modelInstance.newInstance(Bounds.class);
				 paraBounds.setHeight(50.0);
				 paraBounds.setWidth(100.0);
				 paraBounds.setX(live2bpmn.xaxis+70.0);
				 live2bpmn.xaxis = live2bpmn.xaxis+70.0;
				 paraBounds.setY(live2bpmn.yaxis+70.0);
				 live2bpmn.yaxis = live2bpmn.yaxis+70.0;
				 parashape.setBounds(paraBounds);
				 
				 SequenceFlow flow1 = createSequenceFlow(process, (FlowNode) startEvent, para);
				 //actprevious = xor;
				 BpmnEdge flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
					flowEdge.setId("flowEdge");
				    flowEdge.setBpmnElement(flow1);
				    flowEdge.setSourceElement(startEventShape);
				    flowEdge.setTargetElement(parashape);
				    processPlane.getDiagramElements().add(flowEdge);
				 
				d = cal2.getTimeInMillis();
				ParallelGateway para2 = createElement(process, "para2exithere"+d, ParallelGateway.class);
				para2.setName("parallelexit");
				d=cal2.getTimeInMillis();
				BpmnShape para2shape = modelInstance.newInstance(BpmnShape.class);
				para2shape.setId("parashapeexit"+d);
				para2shape.setBpmnElement(para2);
				processPlane.getDiagramElements().add(para2shape);
					 
				Bounds para2Bounds = modelInstance.newInstance(Bounds.class);
				para2Bounds.setHeight(50.0);
				para2Bounds.setWidth(100.0);
				para2Bounds.setX(live2bpmn.xaxis+70.0);
				live2bpmn.xaxis = live2bpmn.xaxis+70.0;
				para2Bounds.setY(live2bpmn.yaxis+70.0);
				live2bpmn.yaxis = live2bpmn.yaxis+70.0;
				para2shape.setBounds(para2Bounds);

			    endEvent = createElement(process, "end", EndEvent.class);
			    endEventShape = modelInstance.newInstance(BpmnShape.class);
			    endEventShape.setId("endShape");
			    endEventShape.setBpmnElement(endEvent);
			    processPlane.getDiagramElements().add(endEventShape);
			    
			    SequenceFlow flow = createSequenceFlow(process, (FlowNode)para2, endEvent);
			    
			    flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
			    flowEdge.setId("flowEdge");
			    flowEdge.setBpmnElement(flow);
			    flowEdge.setSourceElement(para2shape);
			    flowEdge.setTargetElement(endEventShape);
			    processPlane.getDiagramElements().add(flowEdge);
			    
			    Bounds endEventBounds = modelInstance.newInstance(Bounds.class);
			    endEventBounds.setHeight(36.0);
			    endEventBounds.setWidth(36.0);
			    endEventBounds.setX(live2bpmn.xaxis+70.0);
			    live2bpmn.xaxis=live2bpmn.xaxis+70.0;
			    endEventBounds.setY(live2bpmn.yaxis+70.0);
			    live2bpmn.yaxis=live2bpmn.yaxis+70.0;
			    endEventShape.setBounds(endEventBounds);
			    
			    live2bpmn.process = process;
			    live2bpmn.modelInstance = modelInstance;
			    live2bpmn.processPlane = processPlane;
			    live2bpmn.bpmnDiagram = bpmnDiagram;
			    
		 int i = 0;
			for (i = 0; i < roles.size(); i++) {
				StringTokenizer line2 = new StringTokenizer(roles.get(i), "=");
				participantname = line2.nextToken();
				
				live2bpmn.firstacts = new LinkedList<BpmnModelElementInstance>();
				live2bpmn.formulas = new Hashtable<String, String>();
				  StringTokenizer line = new StringTokenizer(roles.get(i), "\n");
				  while (line.hasMoreTokens()) {
						String tmp = line.nextToken();
						StringTokenizer formulaElements = new StringTokenizer(tmp, "=");
						String leftHandSide = formulaElements.nextToken();
						live2bpmn.setleftHandSide(leftHandSide);
						String formula = formulaElements.nextToken();
//						if(formula.contains(leftHandSide)){
//							System.out.println("There is a self-reference in the formula and the program will exit.");
//							System.out.println("Check your formula.");
//							System.exit(0);
//						}
						//String formula1 = preprocessing(formula);
						//System.out.println("What is the formula after preprocessing:"
							//	+ formula1);
						live2bpmn.formulas.put(leftHandSide, formula);
					}
				  line = new StringTokenizer(roles.get(i), "\n");
				  StringTokenizer formulaElements = new StringTokenizer(line.nextToken(),
							"=");
				  String leftHandSide = formulaElements.nextToken();
				  d = cal2.getTimeInMillis();  
				participant = modelInstance.newInstance(Participant.class);
				participant.setId("anid00"+i+d);
				participant.setName(participantname);
				collaboration.addChildElement(participant);
				
				 participant.setProcess(process);
				 participantShape = modelInstance.newInstance(BpmnShape.class);
				 participantShape.setId("participantshape");
				 participantShape.setBpmnElement(participant);

				 processPlane.getDiagramElements().add(participantShape);
				 
				 d = cal2.getTimeInMillis();
				 laneset = createElement(process, "lanesetid"+i+d, LaneSet.class);
				 d = cal2.getTimeInMillis();
				 lane1 = createElement(laneset, "lane"+i+d, Lane.class);
				 
				    live2bpmn.lane1 = lane1;
				    live2bpmn.setpreviousshape(parashape);
					//create process Time for recursion
				    BpmnModelElementInstance task2 = live2bpmn.createProcessrecursively(live2bpmn.formulas.get(leftHandSide), process, (BpmnModelElementInstance) para);
				    
				    d = cal2.getTimeInMillis();
				    
				    SequenceFlow flowtasktopara = createSequenceFlow(process, (FlowNode)task2, (FlowNode)para2);
				    
				    flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
				    flowEdge.setId("flowEdge");
				    flowEdge.setBpmnElement(flowtasktopara);
				    flowEdge.setSourceElement(live2bpmn.previousshape);
				    flowEdge.setTargetElement(para2shape);
				    processPlane.getDiagramElements().add(flowEdge);
				    
				    Bounds tasktopara2Bounds = modelInstance.newInstance(Bounds.class);
				    tasktopara2Bounds.setHeight(36.0);
				    tasktopara2Bounds.setWidth(36.0);
				    tasktopara2Bounds.setX(live2bpmn.xaxis+70.0);
				    live2bpmn.xaxis=live2bpmn.xaxis+70.0;
				    tasktopara2Bounds.setY(live2bpmn.yaxis+70.0);
				    live2bpmn.yaxis=live2bpmn.yaxis+70.0;
				    endEventShape.setBounds(tasktopara2Bounds);
				        
				    // create flows
				    d = cal2.getTimeInMillis();
				    Bounds participantBounds = modelInstance.newInstance(Bounds.class);
				    participantBounds.setHeight(live2bpmn.xaxis+100.0);
				    participantBounds.setWidth(endEventBounds.getY()-startEventBounds.getY());
				    participantBounds.setX(startEventBounds.getX());
				    participantBounds.setY(startEventBounds.getY());
				    participantShape.setBounds(participantBounds);
				    
				    live2bpmn.setpreviousshape(null);
			}
	}
	
	public void createRoles(List<String> roles) {
		// TODO Auto-generated method stub
			Live2BPMN live2bpmn = new Live2BPMN();
		
			modelInstance = Bpmn.createEmptyModel();
		    definitions = modelInstance.newInstance(Definitions.class);
		    definitions.setId("Idok");
		    definitions.setTargetNamespace("http://www.jboss.org/drools");
			definitions.setExporter("org.eclipse.bpmn.modeler.core"); 
			definitions.setTypeLanguage("http://www.java.com/javaTypes");
		    modelInstance.setDefinitions(definitions);
		    
		    bpmnDiagram = modelInstance.newInstance(BpmnDiagram.class);
		    bpmnDiagram.setId("diagram");
		    bpmnDiagram.setName("diagram");
		    bpmnDiagram.setDocumentation("bpmn diagram element");
		    bpmnDiagram.setResolution(120.0);
			modelInstance.getDefinitions().addChildElement(bpmnDiagram);
		    
		    //Calendar to create unique xmiids
			  Calendar cal2 = Calendar.getInstance();
				long d = cal2.getTimeInMillis();
		    collaboration = modelInstance.newInstance(Collaboration.class);
		    collaboration.setId("collaborationid");
		    modelInstance.getDefinitions().addChildElement(collaboration);
			 d = cal2.getTimeInMillis();
			 processPlane = modelInstance.newInstance(BpmnPlane.class);
			 processPlane.setId("plane"+d);

		    int i = 0;
			for (i = 0; i < roles.size(); i++) {
				StringTokenizer line2 = new StringTokenizer(roles.get(i), "=");
				participantname = line2.nextToken();
				
				live2bpmn.firstacts = new LinkedList<BpmnModelElementInstance>();
				live2bpmn.formulas = new Hashtable<String, String>();
				  StringTokenizer line = new StringTokenizer(roles.get(i), "\n");
				  while (line.hasMoreTokens()) {
						String tmp = line.nextToken();
						StringTokenizer formulaElements = new StringTokenizer(tmp, "=");
						String leftHandSide = formulaElements.nextToken();
						live2bpmn.setleftHandSide(leftHandSide);
						String formula = formulaElements.nextToken();
//						if(formula.contains(leftHandSide)){
//							System.out.println("There is a self-reference in the formula and the program will exit.");
//							System.out.println("Check your formula.");
//							System.exit(0);
//						}
						//String formula1 = preprocessing(formula);
						//System.out.println("What is the formula after preprocessing:"
							//	+ formula1);
						live2bpmn.formulas.put(leftHandSide, formula);
					}
				  //System.out.println("What are the formulas?" + live2bpmn.formulas);
				  line = new StringTokenizer(roles.get(i), "\n");
				  StringTokenizer formulaElements = new StringTokenizer(line.nextToken(),
							"=");
				  String leftHandSide = formulaElements.nextToken();
				  d = cal2.getTimeInMillis();  
				participant = modelInstance.newInstance(Participant.class);
				participant.setId("anid00"+i+d);
				participant.setName(participantname);
				collaboration.addChildElement(participant);
				
				d = cal2.getTimeInMillis();
				process = createElement(definitions, "transformedprocess"+i+d, Process.class);
				process.setName("roleprocess"+i+d);
				
				 participant.setProcess(process);
				 participantShape = modelInstance.newInstance(BpmnShape.class);
				 participantShape.setId("participantshape"+i);
				 participantShape.setBpmnElement(participant);

				 processPlane.setBpmnElement(process);
				 bpmnDiagram.setBpmnPlane(processPlane); 
				 processPlane.getDiagramElements().add(participantShape);
				 
				 d = cal2.getTimeInMillis();
				 laneset = createElement(process, "lanesetid"+i+d, LaneSet.class);
				 d = cal2.getTimeInMillis();
				 lane1 = createElement(laneset, "lane"+i+d, Lane.class);
				    
				 startEvent = createElement(process, "start"+i, StartEvent.class);
				 startEventShape = modelInstance.newInstance(BpmnShape.class);
				 startEventShape.setId("startShape"+i);
				 startEventShape.setBpmnElement(startEvent);
				 processPlane.getDiagramElements().add(startEventShape);
				 live2bpmn.setpreviousshape(startEventShape);
				    
				 	live2bpmn.xaxis = 0.0;
				    Bounds startEventBounds = modelInstance.newInstance(Bounds.class);
				    startEventBounds.setHeight(36.0);
				    startEventBounds.setWidth(36.0);
				    startEventBounds.setX(live2bpmn.xaxis+70.0);
				    live2bpmn.xaxis = live2bpmn.xaxis+70.0;
				    startEventBounds.setY(live2bpmn.yaxis+70.0);
				    live2bpmn.yaxis=live2bpmn.yaxis+70.0;
				    startEventShape.setBounds(startEventBounds);
				    
				    endEvent = createElement(process, "end"+i, EndEvent.class);
				    endEventShape = modelInstance.newInstance(BpmnShape.class);
				    endEventShape.setId("endShape"+i);
				    endEventShape.setBpmnElement(endEvent);
				    processPlane.getDiagramElements().add(endEventShape);
				    
				    live2bpmn.lane1 = lane1;
				    live2bpmn.process = process;
				    live2bpmn.modelInstance = modelInstance;
				    live2bpmn.processPlane = processPlane;
				    live2bpmn.bpmnDiagram = bpmnDiagram;
					//create process Time for recursion
				    BpmnModelElementInstance task2 = live2bpmn.createProcessrecursively(live2bpmn.formulas.get(leftHandSide), process, (BpmnModelElementInstance) startEvent);
				    
				    // create flows
				    SequenceFlow flow = createSequenceFlow(process, (FlowNode)task2, endEvent);
				    
				    BpmnEdge flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
					d = cal2.getTimeInMillis();
					flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
					flowEdge.setId("flowEdge"+i+d);
					flowEdge.setBpmnElement(flow);
					processPlane.getDiagramElements().add(flowEdge);
					
					 Waypoint startWaypoint = modelInstance.newInstance(Waypoint.class);
					 startWaypoint.setX(668.0);
					 startWaypoint.setY(330.0);
					 flowEdge.getWaypoints().add(startWaypoint);

					Waypoint endWaypoint = modelInstance.newInstance(Waypoint.class);
					endWaypoint.setX(718.0);
					endWaypoint.setY(330.0);
					flowEdge.getWaypoints().add(endWaypoint);	
				    
				    Bounds endEventBounds = modelInstance.newInstance(Bounds.class);
				    endEventBounds.setHeight(36.0);
				    endEventBounds.setWidth(36.0);
				    endEventBounds.setX(live2bpmn.xaxis+70.0);
				    live2bpmn.xaxis=live2bpmn.xaxis+70.0;
				    endEventBounds.setY(live2bpmn.yaxis+70.0);
				    live2bpmn.yaxis=live2bpmn.yaxis+70.0;
				    endEventShape.setBounds(endEventBounds);
				    
				    Bounds participantBounds = modelInstance.newInstance(Bounds.class);
				    participantBounds.setHeight(live2bpmn.xaxis+100.0);
				    participantBounds.setWidth(endEventBounds.getY()-startEventBounds.getY());
				    participantBounds.setX(startEventBounds.getX());
				    participantBounds.setY(startEventBounds.getY());
				    participantShape.setBounds(participantBounds);
				    
				    live2bpmn.setpreviousshape(null);
			}
	}

	public <T extends BpmnModelElementInstance> T createElement(BpmnModelElementInstance parentElement, String id, Class<T> elementClass) {
		    T element = modelInstance.newInstance(elementClass);
		    element.setAttributeValue("id", id, true);
		    parentElement.addChildElement(element);
		    return element;
		  }

	 public SequenceFlow createSequenceFlow(Process process, FlowNode from, FlowNode to) {
		    SequenceFlow sequenceFlow = createElement(process, from.getId() + "-" + to.getId(), SequenceFlow.class);
		    process.addChildElement(sequenceFlow);
		    sequenceFlow.setSource(from);
		    from.getOutgoing().add(sequenceFlow);
		    sequenceFlow.setTarget(to);
		    to.getIncoming().add(sequenceFlow);
		    return sequenceFlow;
		  }

	 public static String getparticipantname(){
		 return participantname;
	 }
	 
	 public void createOptionACollaborationLanes(List<String> roles){

			// TODO Auto-generated method stub
			Live2BPMN live2bpmn = new Live2BPMN();
			 //Calendar to create unique xmiids
			Calendar cal2 = Calendar.getInstance();
			long d = cal2.getTimeInMillis();
				modelInstance = Bpmn.createEmptyModel();
			    definitions = modelInstance.newInstance(Definitions.class);
			    definitions.setId("Idok");
			    definitions.setTargetNamespace("http://www.jboss.org/drools");
				definitions.setExporter("org.eclipse.bpmn.modeler.core"); 
				definitions.setTypeLanguage("http://www.java.com/javaTypes");
			    modelInstance.setDefinitions(definitions);
			    
			    bpmnDiagram = modelInstance.newInstance(BpmnDiagram.class);
			    bpmnDiagram.setId("diagram");
			    bpmnDiagram.setName("diagram");
			    bpmnDiagram.setDocumentation("bpmn diagram element");
			    bpmnDiagram.setResolution(120.0);
				modelInstance.getDefinitions().addChildElement(bpmnDiagram);
			    
			    collaboration = modelInstance.newInstance(Collaboration.class);
			    collaboration.setId("collaborationid");
			    modelInstance.getDefinitions().addChildElement(collaboration);
				d = cal2.getTimeInMillis();
				processPlane = modelInstance.newInstance(BpmnPlane.class);
				processPlane.setId("plane"+d);
				
				StringTokenizer line2 = new StringTokenizer(roles.get(0), "=");
				participantname = line2.nextToken();
				
				live2bpmn.firstacts = new LinkedList<BpmnModelElementInstance>();
				live2bpmn.formulas = new Hashtable<String, String>();
				String leftHandSide ="";
				  StringTokenizer line = new StringTokenizer(roles.get(0), "\n");
				  while (line.hasMoreTokens()) {
						String tmp = line.nextToken();
						StringTokenizer formulaElements = new StringTokenizer(tmp, "=");
						leftHandSide = formulaElements.nextToken();
						String formula = formulaElements.nextToken();
						live2bpmn.formulas.put(leftHandSide, formula);
					}
				  line = new StringTokenizer(roles.get(0), "\n");
				  StringTokenizer formulaElements = new StringTokenizer(line.nextToken(),
							"=");
				  leftHandSide = formulaElements.nextToken();
				  live2bpmn.setleftHandSide(leftHandSide);
				  d = cal2.getTimeInMillis(); 

					  d = cal2.getTimeInMillis();  
					participant = modelInstance.newInstance(Participant.class);
					participant.setId("anid00"+d);
					participant.setName(participantname);
					collaboration.addChildElement(participant);
					
					 d = cal2.getTimeInMillis();
					 process = createElement(definitions, "transformedprocess"+d, Process.class);
					 process.setName("roleprocess"+d);
					
					 participant.setProcess(process);
					 participantShape = modelInstance.newInstance(BpmnShape.class);
					 participantShape.setId("participantshape");
					 participantShape.setBpmnElement(participant);

					 processPlane.setBpmnElement(process);
					 bpmnDiagram.setBpmnPlane(processPlane); 
					 processPlane.getDiagramElements().add(participantShape);
					 
					 d = cal2.getTimeInMillis();
					 laneset = createElement(process, "lanesetid"+d, LaneSet.class);
					 d = cal2.getTimeInMillis();
					 lane1 = createElement(laneset, leftHandSide, Lane.class);
					 lane1.setName(leftHandSide);
					 laneshape = modelInstance.newInstance(BpmnShape.class);
					 d=cal2.getTimeInMillis();
					 laneshape.setId("laneshape"+d);
					 laneshape.setBpmnElement(lane1);
					 
					 startEvent = createElement(process, "start", StartEvent.class);
					 startEventShape = modelInstance.newInstance(BpmnShape.class);
					 startEventShape.setId("startShape");
					 startEventShape.setBpmnElement(startEvent);
					 processPlane.getDiagramElements().add(startEventShape);
					 live2bpmn.setpreviousshape(startEventShape);
					    
					 live2bpmn.xaxis = 0.0;
					 Bounds startEventBounds = modelInstance.newInstance(Bounds.class);
					 startEventBounds.setHeight(36.0);
					 startEventBounds.setWidth(36.0);
					 startEventBounds.setX(live2bpmn.xaxis+70.0);
					 live2bpmn.xaxis = live2bpmn.xaxis+70.0;
					 startEventBounds.setY(live2bpmn.yaxis+70.0);
					 live2bpmn.yaxis=live2bpmn.yaxis+70.0;
					 startEventShape.setBounds(startEventBounds);

					 endEvent = createElement(process, "end", EndEvent.class);
					 endEventShape = modelInstance.newInstance(BpmnShape.class);
					 endEventShape.setId("endShape");
					 endEventShape.setBpmnElement(endEvent);
					 processPlane.getDiagramElements().add(endEventShape);
					 
					 Bounds laneBounds = modelInstance.newInstance(Bounds.class);
					 laneBounds.setHeight(live2bpmn.xaxis+100.0);
					 laneBounds.setWidth(live2bpmn.yaxis+100.0);
					 laneBounds.setX(startEventBounds.getX());
					 laneBounds.setY(startEventBounds.getY());
					 laneshape.setBounds(laneBounds);
					    
					 live2bpmn.laneset = laneset;
					 live2bpmn.lane1 = lane1;
					 live2bpmn.process = process;
					 live2bpmn.modelInstance = modelInstance;
					 live2bpmn.processPlane = processPlane;
					 live2bpmn.bpmnDiagram = bpmnDiagram;
						//create process Time for recursion
					 
						
						task2 = live2bpmn.createMessagePartners(live2bpmn.formulas.get(leftHandSide), process, (BpmnModelElementInstance) startEvent);
					
						d=cal2.getTimeInMillis();
						live2bpmn.xaxis=live2bpmn.xaxis+70.0;
						live2bpmn.yaxis=live2bpmn.yaxis+70.0;
						xorstar = modelInstance.newInstance(ParallelGateway.class);
						xorstar.setName("stardecision");
						xorstar.setId("xor"+d+d);
						d=cal2.getTimeInMillis();
						process.addChildElement(xorstar);
						
						
						xorshapebrackets = modelInstance.newInstance(BpmnShape.class);
						xorshapebrackets.setId("xor1"+d);
						xorshapebrackets.setBpmnElement(xorstar);
						processPlane.addChildElement(xorshapebrackets);
						
						 Bounds xorbracketsBounds = modelInstance.newInstance(Bounds.class);
						 xorbracketsBounds.setHeight(50.0);
						 xorbracketsBounds.setWidth(100.0);
						 xorbracketsBounds.setX(live2bpmn.xaxis+70.0);
						 live2bpmn.xaxis = live2bpmn.xaxis+70.0;
						 xorbracketsBounds.setY(live2bpmn.yaxis+70.0);
						 live2bpmn.yaxis = live2bpmn.yaxis+70.0;
						 xorshapebrackets.setBounds(xorbracketsBounds);
					    // create flows
					 SequenceFlow tasktopara = createSequenceFlow(process, (FlowNode)task2, xorstar);
						 BpmnEdge flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
							d = cal2.getTimeInMillis();
							flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
							flowEdge.setId("flowparalanes"+d);
							flowEdge.setBpmnElement(tasktopara);
							processPlane.getDiagramElements().add(flowEdge);
//							
							 Waypoint startWaypoint = modelInstance.newInstance(Waypoint.class);
							 startWaypoint.setX(668.0);
							 startWaypoint.setY(330.0);
							 flowEdge.getWaypoints().add(startWaypoint);

							Waypoint endWaypoint = modelInstance.newInstance(Waypoint.class);
							endWaypoint.setX(718.0);
							endWaypoint.setY(330.0);
						flowEdge.getWaypoints().add(endWaypoint);	
						    
					    SequenceFlow flow = createSequenceFlow(process, (FlowNode)xorstar, endEvent);
					    
					    flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
						d = cal2.getTimeInMillis();
						flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
						flowEdge.setId("flowEdgelanes"+d);
						flowEdge.setBpmnElement(flow);
						processPlane.getDiagramElements().add(flowEdge);
						
						startWaypoint = modelInstance.newInstance(Waypoint.class);
						 startWaypoint.setX(668.0);
						 startWaypoint.setY(330.0);
						 flowEdge.getWaypoints().add(startWaypoint);

						 endWaypoint = modelInstance.newInstance(Waypoint.class);
						endWaypoint.setX(718.0);
						endWaypoint.setY(330.0);
						flowEdge.getWaypoints().add(endWaypoint);	
					    
					    Bounds endEventBounds = modelInstance.newInstance(Bounds.class);
					    endEventBounds.setHeight(36.0);
					    endEventBounds.setWidth(36.0);
					    endEventBounds.setX(live2bpmn.xaxis+70.0);
					    live2bpmn.xaxis=live2bpmn.xaxis+70.0;
					    endEventBounds.setY(live2bpmn.yaxis+70.0);
					    live2bpmn.yaxis=live2bpmn.yaxis+70.0;
					    endEventShape.setBounds(endEventBounds);
					    
					    Bounds participantBounds = modelInstance.newInstance(Bounds.class);
					    participantBounds.setHeight(live2bpmn.xaxis+100.0);
					    participantBounds.setWidth(endEventBounds.getY()-startEventBounds.getY());
					    participantBounds.setX(startEventBounds.getX());
					    participantBounds.setY(startEventBounds.getY());
					    participantShape.setBounds(participantBounds);
					    
					    //live2bpmn.setpreviousshape(null);
					    
			 int i = 0;
				for(i=1;i<roles.size();i++){
					try {
						line2 = new StringTokenizer(roles.get(i), "=");
					} catch (Exception e) {
						// TODO Auto-generated catch block
					}
				try {
					participantname = line2.nextToken();
				} catch (Exception e) {
				}
					live2bpmn.formulas = new Hashtable<String, String>();
					live2bpmn.firstacts = new LinkedList<BpmnModelElementInstance>();
					try {
						line = new StringTokenizer(roles.get(i), "\n");
					} catch (Exception e) {
					}
					while (line.hasMoreTokens()){
					String tmp = line.nextToken();
						formulaElements = new StringTokenizer(tmp, "=");
						leftHandSide = formulaElements.nextToken();
						String formula = formulaElements.nextToken();
						live2bpmn.formulas.put(leftHandSide, formula);
						}				
					  try {
						line2 = new StringTokenizer(roles.get(i), "\n");
					} catch (Exception e) {
					}
						  try {
							formulaElements = new StringTokenizer(line2.nextToken(),"=");
						} catch (Exception e) {
						}
						  leftHandSide = formulaElements.nextToken();
						  live2bpmn.setleftHandSide(leftHandSide);
						
						 d = cal2.getTimeInMillis();
						 d = cal2.getTimeInMillis();
						 lane1 = createElement(laneset, leftHandSide, Lane.class);
						 lane1.setName(leftHandSide);   
						 live2bpmn.lane1 = lane1;
						 laneshape = modelInstance.newInstance(BpmnShape.class);
						 d=cal2.getTimeInMillis();
						 //laneshape.setId("laneshape"+d+i);
						 laneshape.setBpmnElement(lane1);
							
						 live2bpmn.createMessageLanes(live2bpmn.formulas.get(leftHandSide), process);
						 tasktopara = null;
						 task2 = live2bpmn.instance;
						 
							 
								tasktopara = createSequenceFlow(process, (FlowNode)task2, xorstar);
							
						
							flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
							d = cal2.getTimeInMillis();
								flowEdge.setBpmnElement(tasktopara);
						
							processPlane.getDiagramElements().add(flowEdge);
							
							 startWaypoint = modelInstance.newInstance(Waypoint.class);
							 startWaypoint.setX(668.0);
							 startWaypoint.setY(330.0);
							 flowEdge.getWaypoints().add(startWaypoint);

							endWaypoint = modelInstance.newInstance(Waypoint.class);
							endWaypoint.setX(718.0);
							endWaypoint.setY(330.0);
							flowEdge.getWaypoints().add(endWaypoint);	
						 
						 laneBounds = modelInstance.newInstance(Bounds.class);
						 laneBounds.setHeight(live2bpmn.xaxis+100.0);
						 laneBounds.setWidth(live2bpmn.yaxis+100.0);
						 laneBounds.setX(startEventBounds.getX());
						    laneBounds.setY(startEventBounds.getY());
						    laneshape.setBounds(laneBounds);
						    
						    participantBounds = modelInstance.newInstance(Bounds.class);
						    participantBounds.setHeight(live2bpmn.xaxis+100.0);
						    participantBounds.setWidth(live2bpmn.yaxis+100.0);
						    participantBounds.setX(live2bpmn.xaxis);
						    participantBounds.setY(live2bpmn.yaxis);
						    participantShape.setBounds(participantBounds);
						    
						   // live2bpmn.setpreviousshape(null);
				}
			
		
		}
}
