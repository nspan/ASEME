
#include "../ICondition.h"
#include "../BlackBoard.h"
// _open_group_play_or_NoPlay_close_group_TO_open_group_play_or_NoPlay_close_group_
class TrCond_StateChartExample0_20_2 : public statechart_engine::ICondition
{	
public:
	void UserInit () 
	{

	}
	bool Eval() 
	{
		/* [playerState!=FINISHED]/TimeoutAction.behavior.200 */
		
if(this->_blk->playerState!=this->_blk->FINISHED){
 return true;
}else{
 return false;
}
 	
	
    }
};
		
#include "../IAction.h"
#include "../TimeoutAction.h"
// _open_group_play_or_NoPlay_close_group_TO_open_group_play_or_NoPlay_close_group_
class TrAction_StateChartExample0_20_2 : public statechart_engine::
	TimeoutAction
{
	/* TimeoutAction.behavior.200 */
public:	TrAction_StateChartExample0_20_2() : statechart_engine::TimeoutAction( "behavior", 200 ) { 
		;
	 }
};

