package JavaHelpers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MadUtils mu=new MadUtils();
		
		try{
			mu.showVarsFromFile();
		}catch(IOException e)
		{
			System.out.println("Error in reading file");
		}
		
		//to parakatw testarei thn grammatiki mou
		
		TransExpr trEx=new TransExpr();
		
		 BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		    String s;
		    String result=new String("");
		    String res2=new String("");
		    System.out.println("result's initial size "+result.length());
		    try{
		    	System.out.println("Enter a boolean expression and press enter:");
		    	s = in.readLine();
		    	if(s!=null && s.length()!=0)
		    	{
		    		result=result+" "+TransExpr.getNaothConditionExpression(s);
		    		//res2=res2+" "+TransExpr.getOldCondition(s);
		    		//res2=res2+" "+TransExpr.ReadVarCondExpr(s);
		    		//System.out.println(TransExpr.ReadVarCondExpr(s));
		    		//System.out.println(TransExpr.EvalCondExpr(s));
		    		
		    	}else
		    	{
		    		System.out.println("String not initialised");
		    	}
		    	//!= null && s.length() != 0
		    }catch(IOException e)
		    {
		    	System.out.println("Error reading value");
		    }	
		    System.out.println();
		   
		    System.out.println("The boolean expression is: "+result);
		    //System.out.println("The old boolean expression is: "+res2);
		

	}//closing main

}
