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
'	Site Emergency E911 Information Details					Site1
'	Street Number* :					17
'	Pre-Directional :					
'	Street Name* :					ACADIA
'	Suffix/Type :					ST
'	Post Directional :				
'	City* :					WEST HARTFORD
'	State* :					CT
'	Zip* :					06119
'	Room :					No.12
'	Floor :					2
'	Building :					34
'	Unit :					APT
'	Unit Value :					117
' #################################################################################

' EXAMPLE SIMON SITE1
'	1	Seat	No Seat	BW Anywhere	FN7125143337	BWAnyWhere	Kesavan_Rajamanickam@cable.comcast.com	New			No Hardware												
'	2	Seat	No Seat	Voice Portal	FN7125143338	VoicePortal	Kesavan_Rajamanickam@cable.comcast.com	New			No Hardware												
'	3	Seat	Standard Seat	Tech Admin User	FN7125143339	TABTNstdseat001	Kesavan_Rajamanickam@cable.comcast.com	New			Polycom VVX 310 HD												
'	4	Seat	UC Seat	Standard User	FN7125143341	UCSeat003	Kesavan_Rajamanickam@cable.comcast.com	New			Polycom VVX 500 HD												


' New outpul file will be generated with E911AddressWithUnit_<UpdatedOppornityID>.xlsm pattern
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
		
		newFileName = SOAFolderPath & "\E911AddressWithUnit_" & (SFOpportunityId + 1) & ".xlsm"
		
		commonFieldUpdates objWorkbook, curSheetName, "E911AddressWithUnit_"
		
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

