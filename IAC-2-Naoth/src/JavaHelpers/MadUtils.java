package JavaHelpers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MadUtils {
	
	public MadUtils()
	{
		
	}
	
	//reading the variables from a txt file
	public void showVarsFromFile() throws IOException
	{
		//BufferedReader br = new BufferedReader(new FileReader("file.txt"));
		BufferedReader reader = new BufferedReader(new FileReader("vars.txt"));
		String line = null;
		System.out.println("vars.txt contains");
		while ((line = reader.readLine()) != null) {
		   System.out.println(line);
		}//kleinei h while
		
	}
}
