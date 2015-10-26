
#ifndef _NoPlay_h_
#define _NoPlay_h_ 1

#include "../../IActivity.hpp"
#include "../../BlackBoard.h"		

namespace statechart_engine{
class NoPlay : public IActivity {
			
public:
	NoPlay(string str,BlackBoard &blkw):IActivity(blkw)
	{
		;
	}
	int Execute ()
	{
		//remember to check your variables for the 
		//right execution
		
		return 0;
	}
	void UserInit () {
		
	}
	string GetName()
	{
		return "NoPlay";
	}
    void Reset (){}		
};
}
#endif // _NoPlay_h_
	