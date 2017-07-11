
#include "../ICondition.h"
#include "../BlackBoard.h"
// 0.2.2TONoPlay
class TrCond_StateChartExample0_2_20_2_4 : public statechart_engine::ICondition
{	
public:
	void UserInit () 
	{

	}
	bool Eval() 
	{
		/* [playerState!=PLAYING] */
		
if(this->_blk->playerState!=this->_blk->PLAYING){
 return true;
}else{
 return false;
}
 	
	
    }
};
		