package aseme.m2t.IACmodel;

import IAC.Model;

public class PackageHelper {

  public static String packagePath( Model e ) {
	  System.out.print("defining the package path");
	  String result =e.getName().replaceAll("\\.", "/");
	  return result;
  }
}
