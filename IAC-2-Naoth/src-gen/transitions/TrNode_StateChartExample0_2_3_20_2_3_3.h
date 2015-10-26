
#include "../ICondition.h"
#include "../BlackBoard.h"
// 0.2.3.2TOSitDown
class TrCond_StateChartExample0_2_3_20_2_3_3 : public statechart_engine::ICondition
{	
public:
	void UserInit () 
	{

	}
	bool Eval() 
	{
		/* event1[ballFound==true] */
		
if(this->_blk->ballFound==true){
 return true;
}else{
 return false;
}
 	
	
    }
};
		