
#include "../ICondition.h"
#include "../BlackBoard.h"
// _open_group_play_or_NoPlay_close_group_TO0.3
class TrCond_StateChartExample0_20_3 : public statechart_engine::ICondition
{	
public:
	void UserInit () 
	{

	}
	bool Eval() 
	{
		/* [vf==12345]/TimeoutAction.behavior.200 */
		
if(this->_blk->vf==12345){
 return true;
}else{
 return false;
}
 	
	
    }
};
		
#include "../IAction.h"
#include "../TimeoutAction.h"
// _open_group_play_or_NoPlay_close_group_TO0.3
class TrAction_StateChartExample0_20_3 : public statechart_engine::
	TimeoutAction
{
	/* TimeoutAction.behavior.200 */
public:	TrAction_StateChartExample0_20_3() : statechart_engine::TimeoutAction( "behavior", 200 ) { 
		;
	 }
};

