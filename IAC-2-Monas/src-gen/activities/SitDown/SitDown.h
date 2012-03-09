
#ifndef _SitDown_h_
#define _SitDown_h_ 1

#include "architecture/IActivity.h"
			
class SitDown : public IActivity {
			
public:
	
	int Execute ();
	
	void UserInit ();
	
	std::string GetName ();
	
	
};



#endif // _SitDown_h_
	