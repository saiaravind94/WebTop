' #######################################################################################################################################################
' @Description This program will transform the testng-failed.xml file into valid one. 
'              ie., Removing the unwanted methods and classes from the xml file, and remove (failed) from the test name xml tag.
' @Returns It updates the testng-failed.xml file content and write into a testng.xml file in the target\surefire-reports location.
' Author Kesavan Rajamanickam(krajam003c)
' Last Updated 22nd August 2017
' #######################################################################################################################################################
Dim objFSO, objTextFile, strText, replacementstring
Set objFSO = CreateObject("Scripting.FileSystemObject")
Set objTextFile = objFSO.OpenTextFile ("target\surefire-reports\testng-failed.xml", 1)
strText = objTextFile.ReadAll
replacementstring = ""
set regex = new RegExp
regex.Global = true
regex.pattern =".*(beforeMethod|afterMethod|beforeClass|afterClass|beforeSuite|afterSuite|beforeTest|afterTest|setupDataApplication|tearDownApplication).*\n"
strText = regex.Replace(strText, replacementstring)
regex.pattern = "(.*\<class name.*\n.*methods\>.*\n.*\methods\>.*\n.*\>)"
strText = regex.Replace(strText,replacementstring)
strText = Replace(strText,"(failed)", replacementstring)
Set objOutFile = objFSO.CreateTextFile("target\surefire-reports\testng.xml",True) 
objOutFile.Write(strText)
Set objOutFile = Nothing
Set regex = Nothing
Set objTextFile = Nothing
Set objFSO = Nothing
