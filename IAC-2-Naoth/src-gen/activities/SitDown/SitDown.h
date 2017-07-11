
#ifndef _SitDown_h_
#define _SitDown_h_ 1

#include "../../IActivity.hpp"
#include "../../BlackBoard.h"		

namespace statechart_engine{
class SitDown : public IActivity {
			
public:
	SitDown(string str,BlackBoard &blkw):IActivity(blkw)
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
		return "SitDown";
	}
    void Reset (){}		
};
}
#endif // _SitDown_h_
	