package aseme.m2t.IACmodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import statechart.Node;
import statechart.Variable;

public class NodeHelper {
	
	public static  ArrayList<Node> p = new ArrayList<Node>();

  public static String className( Node e ) {
    return ((e.getLabel().equalsIgnoreCase("0"))?e.getName()+"Agent":e.getName()+"Behaviour");
  }

  public static String classFileName( Node e ) {
    return className(e)+".java";
  }

  public static String variableFileName( Variable e ) {
	    return e.getType()+".java";
	  }
  
  public static String variableHolderFileName( Variable e ) {
	    return e.getType()+"Holder.java";
	  }

  public static String lowerCaseFirstCharacterOfVariable( Variable e ) {
	    return new String(e.getType().substring(0, 1).toLowerCase()+e.getType().substring(1)); //getName
	  }
  
  public static List<Node> returnAllNodes(Node node){
	    List<Node> listOfNodes = new ArrayList<Node>();
	    addAllNodes(node, listOfNodes);
	    return listOfNodes;
	}

	private static void addAllNodes(Node node, List<Node> listOfNodes) {
		
		if (!listOfNodes.isEmpty()){
			listOfNodes = new ArrayList<Node>();
		}
		
	    if (node != null) {
	        listOfNodes.add(node);
	        List<Node> children = node.getChildren();
	        if (children != null) {
	            for (Node child: children) {
	                addAllNodes(child, listOfNodes);
	            }
	        }
	    }
	}
	
	public List<Node> tree2list(Node h){
		
		if (!p.isEmpty()){
			p = new ArrayList<Node>();
		}
	
		    Stack<Node> s = new Stack<Node>();
		    
		    s.push(h);
		    p.add(h);
		    
		    while(!s.empty()){
		    	
		    	
		       h = s.pop();
		       
		       if (h.getChildren() != null){
		    	   
		    	   for(int i=0; i< h.getChildren().size(); i++){
		    		  	   
			    	   if (!(p.contains(h.getChildren().get(i)))){
			    		   s.push(h.getChildren().get(i));
			    		   p.add(h.getChildren().get(i));
			    		 
			    	   }
			    	   else{
			    		    h = s.pop();
			    	   }
			       }
		    	   
		       }
		       else{	h=	s.pop();
		    	  
		       }       
		      
		    }
		    
		    //System.err.println("tree2list post-while");
		    //for (int j=0; j<p.size(); j++ ){
		    	//System.out.println(p.get(j).getLabel());
		    //}
		    
		    return p;
		 }
	
	
	
}
