package JavaHelpers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import statechart.Variable;

public class Variables {

	public static String InitVariables(List<Variable> variables){
		
		String res = "";
		Set<String> topics = new HashSet<String>();
		Pattern variablePattern = Pattern.compile("\\w+\\.\\w+\\.\\w+");
		for(int i=0; i<variables.size(); i++){
			String type = variables.get(i).getType();
			
			// topic type msg_type
			
			Matcher varMatcher = variablePattern.matcher(type);
						
			if(varMatcher.find()){
				StringTokenizer strtok = new StringTokenizer(type, ".");
				String topic = strtok.nextToken();
				if ( ! topics.contains( topic ) ){
					topics.add(topic);
					res += "\t_blk->updateSubscription(\""+topic+"\",msgentry::SUBSCRIBE_ON_TOPIC);\n";
				}
			}
		}	
		//System.out.println(res);
		return res;
	}
	
	
	public static String ReadVariables(List<Variable> variables){
		
		String res = "";
		
		// topic type msg_type
		Pattern variablePattern = Pattern.compile("\\w+\\.\\w+\\.\\w+");
		for(int i=0; i<variables.size(); i++){
			String type  = variables.get(i).getType();
			String name = variables.get(i).getName();
			Matcher varMatcher = variablePattern.matcher(type);
			
			if ( varMatcher.find() ) {
				
				String[] toks = type.split("\\.");
				
				if ( toks.length < 3 )
					continue;
								
				res += "\t"
						+name
						+ " = _blk->read" + toks[1] + "<" + toks[2] + ">"
						+ " (\"" + toks[0] + "\" );\n" ;			
			}
		}
		System.out.println(res);
		return res;
	}

	public static String DeclareVariables(List<Variable> variables){
		
		String res = "";
		Set<String> type_set = new HashSet<String>();
		type_set.add("int");
		type_set.add("bool");
		type_set.add("float");
		type_set.add("double");
		type_set.add("string");
		//type_set.add("");
		
		// topic type msg_type
		//Pattern variablePattern = Pattern.compile("\\w+\\.\\w+\\.\\w+");
		for(int i=0; i<variables.size(); i++){
			String type  = variables.get(i).getType();
			String name = variables.get(i).getName();
			//Matcher varMatcher = variablePattern.matcher(type);
			
			//checking if variable is of type class
			if(!type_set.contains(type))
			{
res+="class "+type+"{\n public:\n //enter class vars \n \t"+type+"(){\n //enter code here \n\t}\n\t virtual ~"+type+"(){\n\n\t}\n};\n"+type+" "+name+";\n";
				//return res;
			}else
			{
				res+=type+" "+name+";\n"+type+" getVar_"+name+"(){\n return "+name+";\n}\n void setVar_"+name+"("+type+" v){\n\t"+name+"=v;\n}\n";
			}			
			
		}//kleinei h for
		//System.out.println("Entered variables = "+res);
		return res;
	}
	
	//for reading properties file
	public static String ReadHeaderProperties()
	{
		String res="";
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("include_classes.txt"));						
		
		String line = null;
		//System.out.println("header_properties.txt contains");
		
			while ((line = reader.readLine()) != null) {
				res+="#include "+"\""+line+"\""+"\n";
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("include_classes.txt non existent");
			return res;
		}//kleinei h while
		return res;
	}//closing function

	//read class properties
	public static String ReadClassProperties()
	{
		String res="";
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("instances.txt"));						
		
		String line = null;
		//System.out.println("header_properties.txt contains");
		
			while ((line = reader.readLine()) != null) {			   
			   res+=line+"\n";
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("instances.txt non existent");
			return res;
		}//kleinei h while
		return res;
	}//closing function
}//closing class
