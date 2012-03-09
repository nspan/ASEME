
#include "architecture/statechartEngine/ICondition.h"
#include "messages/AllMessagesHeader.h"		
#include "tools/BehaviorConst.h"	
#include "tools/logger.h"
#include "tools/toString.h"
// 0.2.3.2TOSitDown
class TrCond_StateChartExample0_2_3_20_2_3_3 : public statechart_engine::ICondition {
			
public:

	void UserInit () {
		_blk->updateSubscription("behavior",msgentry::SUBSCRIBE_ON_TOPIC);

	}

	bool Eval() {
		/* behavior.State.HeadToBMessage.ballfound()>0 */
		
		boost::shared_ptr<const HeadToBMessage> var_1901744185 = _blk->readState<HeadToBMessage> ("behavior" );

		
		if ( var_1901744185.get() == 0 ){
			return false;
		}

		
				return ( var_1901744185->ballfound()>0 ); 

		
    }
};
		