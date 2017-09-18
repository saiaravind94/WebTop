# #######################################################################################################################################################
# @Description This script will rerun the failed test cases alone based on the 'MaxRetryOnTestCaseFailure' system variable values from jenkins executions. 
# Author Kesavan Rajamanickam(krajam003c)
# Last Updated 22nd August 2017
# #######################################################################################################################################################

#!/bin/bash
export PE_RERUN=true # Set PE_RERUN=true, so that the dump file will be loaded during the re-execution of failed tests
ORG_TESTNG_XML_NAME=$TESTNG_XML_NAME
for i in `eval echo {1..$MaxRetryOnTestCaseFailure}` 
do
	echo "Retry attempt Count: "$i
	export TESTNG_XML_NAME=$ORG_TESTNG_XML_NAME-RERUN-$i
	echo "XML Suite Name: " $TESTNG_XML_NAME
	file="target\surefire-reports\testng-failed.xml"
	if [ -f "$file" ]
	then
		echo "$file found."
		cscript.exe "UpdateFailedXMLFile.vbs"
		# Execute the maven test command once the testng file is ready for re-execution
		mvn test "-DsuiteXmlFile=target\surefire-reports\testng.xml"
		echo "Retry attempt : "$i " is done."
		export TESTNG_XML_NAME=""
		export TESTNG_XML_NAME=$ORG_TESTNG_XML_NAME
	else
		echo "$file not found."
	fi
done
