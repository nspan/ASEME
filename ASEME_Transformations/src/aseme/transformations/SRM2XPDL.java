package aseme.transformations;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SRM2XPDL {
	
	public static void runJar() throws Exception{
		
		File currentDirFile = new File(".");
		String helper = currentDirFile.getAbsolutePath();
		Process ps=Runtime.getRuntime().exec(new String[]{"java","-jar","liveness2xpdl.jar"});
        ps.waitFor();
        java.io.InputStream is=ps.getInputStream();
        byte b[]=new byte[is.available()];
        is.read(b,0,b.length);
        System.out.println(new String(b));
		
		/*
		List<String> command = new ArrayList<String>();
	    
	    command.add("java");
	    command.add("-jar");
	    command.add("liveness2xpdl.jar");
	    
	    ProcessBuilder builder = new ProcessBuilder(command);		    
	    Process process = builder.start();
        */
	}

}
