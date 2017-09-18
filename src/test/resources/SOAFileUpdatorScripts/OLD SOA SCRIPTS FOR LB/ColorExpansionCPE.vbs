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

' EXAMPLE SIMON SITE1
'	1	Seat	No Seat	BW Anywhere	FN1722211409	BWAnyWhere	kesavan_rajamanickam@comcast.com	New			No Hardware												
'	2	Seat	No Seat	Voice Portal	FN1722211410	VoicePortal	kesavan_rajamanickam@comcast.com	New			No Hardware												
'	3	Seat	Standard Seat	Tech Admin User	FN1722211411	TABTNSTDSeat001	kesavan_rajamanickam@comcast.com	New			Polycom VVX 310 HD												
'	4	Seat	Standard Seat	Enterprise Admin User	FN1722211412	STDSeat002	kesavan_rajamanickam@comcast.com	New			Polycom VVX 410 HD w 1 sidecar												
'	5	Seat	Standard Seat	Standard User	FN1722211413	STDSeat003	kesavan_rajamanickam@comcast.com	New			Polycom VVX 500 HD w 1 sidecar												
'	6	Seat	UC Seat	Standard User	FN1722211414	UCSeat001	kesavan_rajamanickam@comcast.com	New			Polycom VVX 600 HD w 1 sidecar												
'	7	Seat	UC Seat	Standard User	FN1722211415	UCSeat002	kesavan_rajamanickam@comcast.com	New			Polycom VVX 1500 HD												


' New outpul file will be generated with ColorExpansionCPE_<UpdatedOppornityID>.xlsm pattern
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
		
		newFileName = SOAFolderPath & "ColorExpansionCPE_" & (SFOpportunityId + 1) & ".xlsm"

		commonFieldUpdates objWorkbook, curSheetName, "ColorExpansionCPE_"
		
		'-------------------------------------------------------------------------------
		.Cells(getRowIdx(curSheetName, "Standard Seats")-1, columnIdxToUpdate).Value = 8
		.Cells(getRowIdx(curSheetName, "Standard Seats"), columnIdxToUpdate).Value = 3
		.Cells(getRowIdx(curSheetName, "Unified Communication Seats"), columnIdxToUpdate).Value = 2
		.Cells(getRowIdx(curSheetName, "Softphone-Only UC seats"), columnIdxToUpdate).Value = 2
		.Cells(getRowIdx(curSheetName, "EdgeMarc 4550 (5 WAN Calls) - 1 per physical location"), columnIdxToUpdate).Value = 1
		.Cells(getRowIdx(curSheetName, "Polycom SoundPoint VVX 310 HD"), columnIdxToUpdate).Value = 1
		.Cells(getRowIdx(curSheetName, "Polycom SoundPoint VVX 410 HD"), columnIdxToUpdate).Value = 1
		.Cells(getRowIdx(curSheetName, "Polycom Soundpoint VVX 500 HD Phone"), columnIdxToUpdate).Value = 1
		.Cells(getRowIdx(curSheetName, "Polycom SoundPoint VVX 600 HD"), columnIdxToUpdate).Value = 1
		.Cells(getRowIdx(curSheetName, "Polycom Soundpoint VVX 500 HD Phone"), columnIdxToUpdate).Value = 1
		.Cells(getRowIdx(curSheetName, "Polycom VVX 1500 HD"), columnIdxToUpdate).Value = 1
		.Cells(getRowIdx(curSheetName, "Polycom VVX Color Expansion Module"), columnIdxToUpdate).Value = 3
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


