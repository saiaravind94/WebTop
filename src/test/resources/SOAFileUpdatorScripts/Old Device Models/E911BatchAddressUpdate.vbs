' This script will update the following In Line Based order as per SOA Version V1.38
' ##############################################################################
' Sheet Name: SOA Cofigurator
' ##############################################################################
' SF Opportunity ID --------> Update the value by incrementing by 1
' Lead ID* : --------> Update the value by incrementing by 1
' SF Account ID* : --------> Update the value by incrementing by 1
' SF Location ID* : --------> Update the value by incrementing by 1
' Sales Rep PERNR: --------> Update the value by incrementing by 1
' Billing Account Name : --------> Update the value by appending the timestamp
' Location Name* : --------> Update the value by appending the timestamp
' Company Name* : --------> Update the value by appending the timestamp
' Account Name* : --------> Update the value by appending the timestamp

' #################################################################################
'Street Number* :					12				
'  Pre-Directional :									
'Street Name* :					ADAMS LN				
'  Suffix/Type :					ST				
'  Post Directional :									
'City* :					DOWNINGTOWN				
'State* :					PA				
'Zip* :					19335				
'Room :									
'Floor :									
'Building :									
'Unit :					APT				
'Unit Value :					117				

' #################################################################################

' EXAMPLE SIMON SITE1


' New outpul file will be generated with E911BatchAddressUpdate_<UpdatedOppornityID>.xlsm pattern
' Author : Kesavan(krajam003c)
' Last Updated : 25th Jan 2017

Set oShell = CreateObject("WScript.Shell")
SOAFolderPath = WScript.Arguments.Item(0)
Dim fso, path, file, recentDate, recentFile
Set fso = CreateObject("Scripting.FileSystemObject")
Set recentFile = Nothing
Dim RegEx :Set regEx = New RegExp
regEx.Pattern = WScript.Arguments.Item(1)
regEx.IgnoreCase = False

For Each file in fso.GetFolder(SOAFolderPath).Files
	If regEx.Test(file) Then
	  If (recentFile is Nothing) Then
		Set recentFile = file
	  ElseIf (file.Name > recentFile.Name) Then
		Set recentFile = file
	  End If
	End If
Next

If recentFile is Nothing Then
  WScript.Echo "no recent files"
Else
	Include Replace(SOAFolderPath, "WebTopSOADataFiles", "SOAFileUpdatorScripts") & "\Utilities"
	Set objExcel = CreateObject("Excel.Application")
	oldFileName = SOAFolderPath & "\" & recentFile.Name
	Set objWorkbook = objExcel.Workbooks.Open(oldFileName)
	curSheetName = "SOA Configurator"
	columnIdxToUpdate = getColumnIdx(curSheetName, "Site1")
	
	With objWorkbook.Worksheets(curSheetName)
		SFOpportunityId = .Cells(getRowIdx(curSheetName, "SF Opportunity ID* :"), columnIdxToUpdate).Value
		
		newFileName = SOAFolderPath & "\E911BatchAddressUpdate_" & (SFOpportunityId + 1) & ".xlsm"
		
		commonFieldUpdates objWorkbook, curSheetName, "E911BatchAddressUpdate_"
		.Cells(getRowIdx(curSheetName, "Standard Seats"), columnIdxToUpdate).Value = 4
		.Cells(getRowIdx(curSheetName, "Unified Communication Seats"), columnIdxToUpdate).Value = 1
		.Cells(getRowIdx(curSheetName, "Standard Seats")-1, columnIdxToUpdate).Value = 1
		.Cells(getRowIdx(curSheetName, "Polycom SoundPoint VVX 410 HD"), columnIdxToUpdate).Value = 5
		.Cells(getRowIdx(curSheetName, "Unit :"), columnIdxToUpdate).Value = "APT"
		.Cells(getRowIdx(curSheetName, "Unit Value :"), columnIdxToUpdate).Value = "117"
		oShell.Run Replace(SOAFolderPath, "WebTopSOADataFiles", "SOAFileUpdatorScripts") & "\CloseValidationPopup.vbs"
		objExcel.Application.Run "PopulateSOAData"
		objWorkbook.Save

		' Close the running processes if any
		oShell.Run "TASKKILL /IM EXCEL.EXE /F", , True
		WScript.sleep 1000
		fso.MoveFile oldFileName, newFileName
		
		' Run the Macros so that update will be reflected in the other sheets and will generate a new import file
		Set objExcel = CreateObject("Excel.Application")
		Set objWorkbook = objExcel.Workbooks.Open(newFileName)
		objExcel.Application.Run "createWebtopEntityImportFile"
		objWorkbook.Save
		objWorkbook.Close
		oShell.Run "TASKKILL /IM WSCRIPT.EXE /F", , True
		oShell.Run "TASKKILL /IM EXCEL.EXE /F", , True
		Set oShell = Nothing
	End With
End If

Sub Include(yourFile)
  Dim oFSO, oFileBeingReadIn   ' define Objects
  Dim sFileContents            ' define Strings
 
  Set oFSO = CreateObject("Scripting.FileSystemObject")
  Set oFileBeingReadIn = oFSO.OpenTextFile(yourFile & ".vbs", 1)
  sFileContents = oFileBeingReadIn.ReadAll
  oFileBeingReadIn.Close
  ExecuteGlobal sFileContents
End Sub

