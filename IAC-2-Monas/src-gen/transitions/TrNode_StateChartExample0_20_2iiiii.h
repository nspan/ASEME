
#include "architecture/statechartEngine/ICondition.h"
#include "messages/AllMessagesHeader.h"		
#include "tools/BehaviorConst.h"	
#include "tools/logger.h"
#include "tools/toString.h"
// _open_group_play_or_NoPlay_close_group_TO_open_group_play_or_NoPlay_close_group_
class TrCond_StateChartExample0_20_2 : public statechart_engine::ICondition {
			
public:

	void UserInit () {
		_blk->updateSubscription("behavior",msgentry::SUBSCRIBE_ON_TOPIC);

	}

	bool Eval() {
		/* boost::posix_time::from_iso_string(_behavior.State.TimeoutMsg.wakeup()) < boost::posix_time::microsec_clock::local_time() && behavior.State.GameStateMessage.player_state()!=PLAYER_FINISHED */
		
		boost::shared_ptr<const TimeoutMsg> var_99600198 = _blk->readState<TimeoutMsg> ("behavior" );
		boost::shared_ptr<const GameStateMessage> var_621149599 = _blk->readState<GameStateMessage> ("behavior" );

		
		if ( var_99600198.get() == 0 ){
			return true;
		}
		if ( var_621149599.get() == 0 ){
			return false;
		}

		
				return ( boost::posix_time::from_iso_string(var_99600198->wakeup()) < boost::posix_time::microsec_clock::local_time() && var_621149599->player_state()!=PLAYER_FINISHED ); 

		
    }
};
		