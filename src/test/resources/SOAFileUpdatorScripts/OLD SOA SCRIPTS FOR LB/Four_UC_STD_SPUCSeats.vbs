' This script will update the following In Line Based order as per SOA Version 1.38

' SOA CONFIGURATOR SHEET
' SF Opportunity ID --------> Update the value by incrementing by 1
' Lead ID* : --------> Update the value by incrementing by 1
' SF Account ID* : --------> Update the value by incrementing by 1
' SF Location ID* : --------> Update the value by incrementing by 1
' Sales Rep PERNR: --------> Update the value by incrementing by 1
' Billing Account Name : --------> Update the value by appending the timestamp
' Location Name* : --------> Update the value by appending the timestamp
' Company Name* : --------> Update the value by appending the timestamp
' Account Name* : --------> Update the value by appending the timestamp
' Service Term(Months): -------> 24 Months
' Lines ----------> 4
' Standard Seats ------> 4
' Unified Communication Seats ----> 4
' Softphone-Only UC seats ---> 4
' EdgeMarc 4550 (5 WAN Calls) - 1 per physical location ----- > 1
' Polycom SoundPoint VVX 310 HD ------> 4
' Polycom SoundPoint VVX 410 HD ------> 4

' EXAMPLE SIMON SITE1
'	1	Seat	No Seat	BW Anywhere	FN1729124655	BWAnyWhere	kesavan_rajamanickam@comcast.com	New			No Hardware												
'	2	Seat	No Seat	Voice Portal	FN1729124656	VoicePortal	kesavan_rajamanickam@comcast.com	New			No Hardware												
'	3	Seat	Standard Seat	Tech Admin User	FN1729124657	TABTNstdseat001	kesavan_rajamanickam@comcast.com	New			Polycom VVX 310 HD												
'	4	Seat	Standard Seat	Enterprise Admin User	FN1729124658	STDSeat002	kesavan_rajamanickam@comcast.com	New			Polycom VVX 310 HD												
'	5	Seat	Standard Seat	Standard User	FN1729124659	STDSeat003	kesavan_rajamanickam@comcast.com	New			Polycom VVX 310 HD												
'	6	Seat	Standard Seat	Standard User	FN1729124660	STDSeat004	kesavan_rajamanickam@comcast.com	New			Polycom VVX 310 HD												
'	7	Seat	UC Seat	Standard User	FN1729124661	UCSeat001	kesavan_rajamanickam@comcast.com	New			Polycom VVX 410 HD												
'	8	Seat	UC Seat	Standard User	FN1729124662	UCSeat002	kesavan_rajamanickam@comcast.com	New			Polycom VVX 410 HD												
'	9	Seat	UC Seat	Standard User	FN1729124663	UCSeat003	kesavan_rajamanickam@comcast.com	New			Polycom VVX 410 HD												
'	10	Seat	UC Seat	Standard User	FN1729124664	UCSeat004	kesavan_rajamanickam@comcast.com	New			Polycom VVX 410 HD												


' New outpul file will be generated with Four_UC_STD_SPUCSeats_<UpdatedOppornityID>.xlsm pattern
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
		
		newFileName = SOAFolderPath & "Four_UC_STD_SPUCSeats_" & (SFOpportunityId + 1) & ".xlsm"

		commonFieldUpdates objWorkbook, curSheetName, "Four_UC_STD_SPUCSeats_"
		
		'-------------------------------------------------------------------------------
		.Cells(getRowIdx(curSheetName, "Service Term(Months):"), 02).value = 24
		.Cells(getRowIdx(curSheetName, "Standard Seats") - 1, columnIdxToUpdate).Value = 4 ' Lines 
		.Cells(getRowIdx(curSheetName, "Standard Seats"), columnIdxToUpdate).Value = 4
		.Cells(getRowIdx(curSheetName, "Unified Communication Seats"), columnIdxToUpdate).Value = 4
		.Cells(getRowIdx(curSheetName, "Softphone-Only UC seats"), columnIdxToUpdate).Value = 4
		.Cells(getRowIdx(curSheetName, "EdgeMarc 4550 (5 WAN Calls) - 1 per physical location"), columnIdxToUpdate).Value = 1
		.Cells(getRowIdx(curSheetName, "Polycom SoundPoint VVX 410 HD"), columnIdxToUpdate).Value = 8
		'-------------------------------------------------------------------------------
		oShell.Run Chr(34) & Replace(SOAFolderPath, "WebTopSOADataFiles", "SOAFileUpdatorScripts") & "CloseValidationPopup.vbs"  & Chr(34)
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