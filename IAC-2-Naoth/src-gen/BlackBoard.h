
#ifndef BlackBoard_H
#define	 BlackBoard_H

#include "BallPercept.h"
#include "MotionRequest.h"
#include <iostream>
#include <string>
#include <list>
#include "MessageHub.h"
using namespace std;
class BlackBoard
{  
private:
    string name;  
    string playMode;  //mine
  /** this constructor cann only be called from inside */


public:
   BallPercept theBallPercept;  /*already implemented*/
   MotionRequest theMotionRequest;  /*already implemented*/
   int testExt;   
   list<Time_stamp> *listTimeStamps;
   
   //oi metavlhtes pou tha ginontai kathe fora update apo diafores functions
   //gia na mporw kathe fora na parw tis metavlites xrhsimopoiontas 
   //mono to instance tou BlackBoard kai to onoma ths metavlhths pou thelw      

//clearing triggered timeouts
void clearPassedTimeouts()
{
	list<Time_stamp>::iterator list_counter;
	for(list_counter=listTimeStamps->begin();list_counter!=listTimeStamps->end();++list_counter)
	{
		//if timeout is true => timeout is already triggered and has to be removed
		if(list_counter->getTrigger()==true)
		{
			listTimeStamps->erase(list_counter);
		}
	}
}//closing function
   
       
   string testing;
string getVar_testing(){
 return testing;
}
 void setVar_testing(string v){
	testing=v;
}
class Test{
 public:
 //enter class vars 
 	Test(){
 //enter code here 
	}
	 virtual ~Test(){

	}
};
Test testing2;
   
   BlackBoard(){}
   BlackBoard(const string& str){name=str;}
   virtual ~BlackBoard(){}
   
  /** gives access to the instance of the blackboard */
  static BlackBoard& getInstance()
  {
    /** the ONLY instance of the blackboard */
    static BlackBoard instance;
    return instance;
  }
  
  void setPlayMode(string str)
  {
   	playMode=str;
   }    

  string getPlayMode()
  { 
    return playMode;
   }

//checking events on TE 
//using event->getVariable as input from CanExecute
//at TransitionSegment.h
  bool checkEvent(string str)
  {
	//to swsto einai: otan playMode==str => tote return true
	//alla prepei na ginetai exclusive or kai dn exw arketes metavlites gia
	//to simulation. 
        if(playMode!="GameOver")//==str)
	{
	cout<<"Blk::checkEvent is "+str<<endl;
	 return true;
	}else
	return false;
  }

  //sets blackboard name
  void setBlackBoardName(const string& str)
  {
	  name=str;
  }

 //prints blackboard name
  void printBlackBoardName()
  {
    cout<<"BlackBoard name is "<<name<<endl;
  }
  
  //update generated variables
  void updateVars()
  {
   //enter your code here
  }

  //for use in spl only
  void attachTo(MessageHub* hub)
  {
	 //implement for spl
  }
};


#endif	/* BlackBoard_H */
	