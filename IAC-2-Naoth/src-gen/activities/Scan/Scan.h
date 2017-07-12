
#ifndef _Scan_h_
#define _Scan_h_ 1

#include "../../IActivity.hpp"
#include "../../BlackBoard.h"		

namespace statechart_engine{
class Scan : public IActivity {
			
public:
	Scan(string str,BlackBoard &blkw):IActivity(blkw)
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
		return "Scan";
	}
    void Reset (){}		
};
}
#endif // _Scan_h_
	