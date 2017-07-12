
#include "../ICondition.h"
#include "../BlackBoard.h"
// 0.2.2TOplay
class TrCond_StateChartExample0_2_20_2_3 : public statechart_engine::ICondition
{	
public:
	void UserInit () 
	{

	}
	bool Eval() 
	{
		/* [wasSeen==true]/action=2 ? sd.e=fs */
		
if(this->_blk->wasSeen==true){
 return true;
}else{
 return false;
}
 	
	
    }
};
		
#include "../IAction.h"
// 0.2.2TOplay
class TrAction_StateChartExample0_2_20_2_3 : public statechart_engine::
	IAction
{
	/* action=2 ? sd.e=fs */
public:
	void UserInit () 
	{ 
	}

	int Execute()
	{
 
    this->_blk->action=2 ;
this->_blk->sd.e=this->_blk->fs;


	return 0;
   	}
};

