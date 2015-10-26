
#include "../ICondition.h"
#include "../BlackBoard.h"
// 0.2.3.2TOScan
class TrCond_StateChartExample0_2_3_20_2_3_4 : public statechart_engine::ICondition
{	
public:
	void UserInit () 
	{

	}
	bool Eval() 
	{
		/* event2[ballFound!=true] */
		
if(this->_blk->ballFound!=true){
 return true;
}else{
 return false;
}
 	
	
    }
};
		