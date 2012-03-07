package aseme.m2t.IACmodel;

import IAC.Node;
import IAC.Variable;

public class NodeHelper {

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
	    return new String(e.getType().substring(0, 1).toLowerCase()+e.getType().substring(1));
	  }
}
