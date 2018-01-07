# ASEME
The ASEME IDE

If you are reading this file, you are ready to edit or study the ASEME IDE source code.
Thus we assume you are familiar with ASEME.

Necessary applications and packages:

  - Eclipse Modeling Tools package (Mars 2.0 version preferably) for your platform
  - Xpand and Graphical Modeling Framework Tooling (GMF) modeling components 
    (install from the menu Help -> Install modeling components)

Project Outline :

  This project is a collection of many plugin projects and according to Rich Client Platform (RCP) architechture.

  Each of the AMOLA metamodels is implemented as a EMF model and has its own GMF editor.
  So, there are 4 projects for each model, the MetamodelNameDesign (SAGDesign, SUCDesign etc.) that contains 
  the Java implementation code of the given model the Edit (Java code for editing of model objects) 
  and Editor code (the UI for the EMF editor of the model objects and wizard) 
  are located in generated plugin projects with the .edit and .editor suffix 
  respectively and the .diagram project (the GMF editor).

  There are also IAC-2-Naoth, IAC_EMF.generator projects that  contain the code for generating C++  and Java code respectively,
  from a given Statechart model, and SRM2Xpdl that is the code of the SRM2Xpdl Java Swing application.
  
  Finally, there are ASEME_Transformations that contains the implementation of the meta-models transformations
  and the ASEMEDashboardView that contains the Dashboard code.
  
  
  
To run the project import it at Eclipse Mars2.0 Modeling Tools and run ASEMEDAshbpardView project as an Eclipse Application.
