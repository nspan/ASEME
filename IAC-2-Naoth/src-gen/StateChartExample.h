#ifndef StateChartExample_h_
#define StateChartExample_h_ 1
		
#include "Statechart.h"
#include "StartState.h"
#include "EndState.h"
#include "OrState.h"
#include "AndState.h"
#include "BasicState.h"
#include "Transitions/TransitionSegment.h"
#include "Transitions/ConditionConnector.h"
#include "transitionHeaders.h"

class StateChartExample
{

public:
	
StateChartExample (MessageHub* com)
{
	
    
	_statechart = new Statechart ( "Node_behavior", com );
	Statechart* Node_0 = _statechart;
	_states.push_back( Node_0 );

	StartState* Node_0_1 = new StartState ( "Node_0_1", Node_0 ); //Name:0.1
	_states.push_back( Node_0_1 );

	OrState* Node_0_2 = new OrState ( "Node__open_group_play_or_NoPlay_close_group_", Node_0 );	//Name:_gr_play_or_NoPlay
	_states.push_back( Node_0_2 );

	StartState* Node_0_2_1 = new StartState ( "Node_0_2_1", Node_0_2 ); //Name:0.2.1
	_states.push_back( Node_0_2_1 );

	ConditionConnector* Node_0_2_2  = new ConditionConnector ( "Node_0_2_2", Node_0_2 ); //Name:0.2.2
	_states.push_back( Node_0_2_2 );

	OrState* Node_0_2_3 = new OrState ( "Node_play", Node_0_2 );	//Name:play
	_states.push_back( Node_0_2_3 );

	StartState* Node_0_2_3_1 = new StartState ( "Node_0_2_3_1", Node_0_2_3 ); //Name:0.2.3.1
	_states.push_back( Node_0_2_3_1 );

	ConditionConnector* Node_0_2_3_2  = new ConditionConnector ( "Node_0_2_3_2", Node_0_2_3 ); //Name:0.2.3.2
	_states.push_back( Node_0_2_3_2 );

					
	
	
	IActivity* NodeActivInst_0_2_3_3 = new SitDown( "SitDown" , *Node_0_2_3->GetBlackboard());	
	_activities.push_back( NodeActivInst_0_2_3_3 );
	BasicState* Node_0_2_3_3 = new BasicState( "Node_SitDown", Node_0_2_3, NodeActivInst_0_2_3_3 ); //Name:SitDown
	_states.push_back( Node_0_2_3_3 );

					
	
	
	IActivity* NodeActivInst_0_2_3_4 = new Scan( "Scan" , *Node_0_2_3->GetBlackboard());	
	_activities.push_back( NodeActivInst_0_2_3_4 );
	BasicState* Node_0_2_3_4 = new BasicState( "Node_Scan", Node_0_2_3, NodeActivInst_0_2_3_4 ); //Name:Scan
	_states.push_back( Node_0_2_3_4 );

	EndState* Node_0_2_3_5 = new EndState ( "Node_0_2_3_5", Node_0_2_3 ); //Name:0.2.3.5
	_states.push_back( Node_0_2_3_5 );

					
	
	
	IActivity* NodeActivInst_0_2_4 = new NoPlay( "NoPlay" , *Node_0_2->GetBlackboard());	
	_activities.push_back( NodeActivInst_0_2_4 );
	BasicState* Node_0_2_4 = new BasicState( "Node_NoPlay", Node_0_2, NodeActivInst_0_2_4 ); //Name:NoPlay
	_states.push_back( Node_0_2_4 );

	EndState* Node_0_2_5 = new EndState ( "Node_0_2_5", Node_0_2 ); //Name:0.2.5
	_states.push_back( Node_0_2_5 );

	EndState* Node_0_3 = new EndState ( "Node_0_3", Node_0 ); //Name:0.3
	_states.push_back( Node_0_3 );
	
    
		

		

		

	_transitions.push_back( new TransitionSegment<State,ConditionConnector>(Node_0_2_1,Node_0_2_2    ) ); //StateChartExample0.2.10.2.2
		

		
		
	ICondition* TrCondInst_StateChartExample0_2_20_2_3 = new TrCond_StateChartExample0_2_20_2_3;
	_conditions.push_back( TrCondInst_StateChartExample0_2_20_2_3 );

		
		
	IAction* TrActionInst_StateChartExample0_2_20_2_3 = new TrAction_StateChartExample0_2_20_2_3;
	_actions.push_back( TrActionInst_StateChartExample0_2_20_2_3 );

	_transitions.push_back( new TransitionSegment<ConditionConnector,State>(Node_0_2_2,Node_0_2_3  ,TrCondInst_StateChartExample0_2_20_2_3 ,TrActionInst_StateChartExample0_2_20_2_3 ) ); //StateChartExample0.2.20.2.3
		

		
		
	ICondition* TrCondInst_StateChartExample0_2_20_2_4 = new TrCond_StateChartExample0_2_20_2_4;
	_conditions.push_back( TrCondInst_StateChartExample0_2_20_2_4 );

		

	_transitions.push_back( new TransitionSegment<ConditionConnector,State>(Node_0_2_2,Node_0_2_4  ,TrCondInst_StateChartExample0_2_20_2_4  ) ); //StateChartExample0.2.20.2.4
		

		

		

	_transitions.push_back( new TransitionSegment<State,State>(Node_0_2_4,Node_0_2_5    ) ); //StateChartExample0.2.40.2.5
		

		

		

	_transitions.push_back( new TransitionSegment<State,State>(Node_0_2_3,Node_0_2_5    ) ); //StateChartExample0.2.30.2.5
		

		

		

	_transitions.push_back( new TransitionSegment<State,ConditionConnector>(Node_0_2_3_1,Node_0_2_3_2    ) ); //StateChartExample0.2.3.10.2.3.2
		
				
	IEvent* TrEventInst_0_2_3_2TOSitDown = new TrEvent_0_2_3_2TOSitDown("event1");
	_events.push_back( TrEventInst_0_2_3_2TOSitDown );

		
		
	ICondition* TrCondInst_StateChartExample0_2_3_20_2_3_3 = new TrCond_StateChartExample0_2_3_20_2_3_3;
	_conditions.push_back( TrCondInst_StateChartExample0_2_3_20_2_3_3 );

		

	_transitions.push_back( new TransitionSegment<ConditionConnector,State>(Node_0_2_3_2,Node_0_2_3_3 ,TrEventInst_0_2_3_2TOSitDown ,TrCondInst_StateChartExample0_2_3_20_2_3_3  ) ); //StateChartExample0.2.3.20.2.3.3
		
				
	IEvent* TrEventInst_0_2_3_2TOScan = new TrEvent_0_2_3_2TOScan("event2");
	_events.push_back( TrEventInst_0_2_3_2TOScan );

		
		
	ICondition* TrCondInst_StateChartExample0_2_3_20_2_3_4 = new TrCond_StateChartExample0_2_3_20_2_3_4;
	_conditions.push_back( TrCondInst_StateChartExample0_2_3_20_2_3_4 );

		

	_transitions.push_back( new TransitionSegment<ConditionConnector,State>(Node_0_2_3_2,Node_0_2_3_4 ,TrEventInst_0_2_3_2TOScan ,TrCondInst_StateChartExample0_2_3_20_2_3_4  ) ); //StateChartExample0.2.3.20.2.3.4
		

		

		

	_transitions.push_back( new TransitionSegment<State,State>(Node_0_2_3_4,Node_0_2_3_5    ) ); //StateChartExample0.2.3.40.2.3.5
		

		

		

	_transitions.push_back( new TransitionSegment<State,State>(Node_0_2_3_3,Node_0_2_3_5    ) ); //StateChartExample0.2.3.30.2.3.5
		

		

		

	_transitions.push_back( new TransitionSegment<State,State>(Node_0_1,Node_0_2    ) ); //StateChartExample0.10.2
		

		
		
	ICondition* TrCondInst_StateChartExample0_20_2 = new TrCond_StateChartExample0_20_2;
	_conditions.push_back( TrCondInst_StateChartExample0_20_2 );

		
		
	IAction* TrActionInst_StateChartExample0_20_2 = new TrAction_StateChartExample0_20_2;
	_actions.push_back( TrActionInst_StateChartExample0_20_2 );

	_transitions.push_back( new TransitionSegment<State,State>(Node_0_2,Node_0_2  ,TrCondInst_StateChartExample0_20_2 ,TrActionInst_StateChartExample0_20_2 ) ); //StateChartExample0.20.2
		

		
		
	ICondition* TrCondInst_StateChartExample0_20_3 = new TrCond_StateChartExample0_20_3;
	_conditions.push_back( TrCondInst_StateChartExample0_20_3 );

		
		
	IAction* TrActionInst_StateChartExample0_20_3 = new TrAction_StateChartExample0_20_3;
	_actions.push_back( TrActionInst_StateChartExample0_20_3 );

	_transitions.push_back( new TransitionSegment<State,State>(Node_0_2,Node_0_3  ,TrCondInst_StateChartExample0_20_3 ,TrActionInst_StateChartExample0_20_3 ) ); //StateChartExample0.20.3	
    
}//closing constructor
		
~StateChartExample ()
{
for ( StateCont::iterator it = _states.begin(); it != _states.end(); it++ )
		delete (*it);
	for ( TransitionCont::iterator it = _transitions.begin(); it != _transitions.end(); it++ )
		delete (*it);
	for ( ActCont::iterator it = _activities.begin(); it != _activities.end(); it++ )
		delete (*it);
	for ( EventCont::iterator it = _events.begin(); it != _events.end(); it++ )
		delete (*it);
	for ( CondCont::iterator it = _conditions.begin(); it != _conditions.end(); it++ )
		delete (*it);
	for ( ActionCont::iterator it = _actions.begin(); it != _actions.end(); it++ )
		delete (*it);
}//closing function

//starting the statechart in a new thread		
void Start ()
{
   _statechart->Start();
}//closing start

//stoping the statechart		
void Stop ()
{
   _statechart->Stop();
}

//check if statechart is already running
bool getStatechartIsRunning()
{
	return _statechart->getStatechartRunning();
}				
private:

	typedef std::vector<statechart_engine::State*> StateCont;
	typedef std::vector<statechart_engine::TransitionSegmentBase*> TransitionCont;
		
	StateCont _states;
	TransitionCont _transitions;
		
	typedef std::vector<IActivity*> ActCont;		
	ActCont _activities;
		
	typedef std::vector<statechart_engine::IEvent*> EventCont;
	typedef std::vector<statechart_engine::ICondition*> CondCont;
	typedef std::vector<statechart_engine::IAction*> ActionCont;
	
	EventCont _events;
	CondCont _conditions;
	ActionCont _actions;
		
	statechart_engine::Statechart* _statechart;
				
};

#endif // StateChartExample_h_
	