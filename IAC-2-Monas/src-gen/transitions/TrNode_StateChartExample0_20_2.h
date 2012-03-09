
#include "architecture/statechartEngine/IAction.h"
#include "architecture/statechartEngine/TimoutAciton.h"
// _open_group_play_or_NoPlay_close_group_TO_open_group_play_or_NoPlay_close_group_
class TrAction_StateChartExample0_20_2 : public statechart_engine::
				TimeoutAction {
		/* TimeoutAction.behavior.200 */
	public:	TrAction_StateChartExample0_20_2() : statechart_engine::TimeoutAction( "behavior", 200 ) { 
		;
	 }
};

		