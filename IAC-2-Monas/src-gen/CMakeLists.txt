
message ( STATUS "" )
message ( STATUS " _..::: Configuring StateChartExample :::.._ " )
message ( STATUS "" )

#add_library ( StateChartExample ${LibBuildType}  StateChartExample.cpp )  
              
add_library ( StateChartExample SHARED
        StateChartExample.cpp )
        
#target_link_libraries ( StateChartExample statechartEngine narukom )

install ( TARGETS StateChartExample
RUNTIME DESTINATION   bin
LIBRARY DESTINATION   lib
ARCHIVE DESTINATION   lib/static )

set ( StatechartLibraries ${StatechartLibraries} StateChartExample PARENT_SCOPE )
	