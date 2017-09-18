' This script will update the following In Seat Based order as per SOA Version 1.40
' EXAMPLE SIMON SITE1
'	1	Seat	No Seat	BW Anywhere	BWAnyWhere	LN1733174640	LN1733174640@comcast.com	New			No Hardware												
'	2	Seat	No Seat	Voice Portal	VoicePortal	LN1733174641	LN1733174641@comcast.com	New			No Hardware												
'	3	Seat	UC Seat	Standard User	STDSeat001	LN1733174642	LN1733174642@comcast.com	New			No Hardware	Polycom SoundPoint VVX 310 HD											
'	4	Seat	UC Seat	Standard User	STDSeat002	LN1733174643	LN1733174643@comcast.com	New			No Hardware	Polycom SoundPoint VVX 310 HD											
'	5	Seat	UC Seat	Tech Admin User	TABTNUCSeat001	LN1733174644	LN1733174644@comcast.com	New			No Hardware	Polycom SoundPoint VVX 310 HD											
'	6	Seat	UC Seat	Enterprise Admin User	UCSeat002	LN1733174645	LN1733174645@comcast.com	New			No Hardware	Polycom SoundPoint VVX 310 HD											

' New outpul file will be generated with MACD_SBP_TW_<UpdatedOppornityID>.xlsm pattern
' Author : Kesavan(krajam003c)
' Last Updated : 07th Mar 2017

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
		
		newFileName = SOAFolderPath & "\MACD_SBP_TW_" & (SFOpportunityId + 1) & ".xlsm"
		
		commonFieldUpdates objWorkbook, curSheetName, "MACD_SBP_TW_"
		
		'---------------------------------------------------------------------------------------------------------------------
		.Cells(getRowIdx(curSheetName, "Site Type* :"), columnIdxToUpdate).Value = "Teleworker"
		.Cells(getRowIdx(curSheetName, "Teleworker Internet N/W :"), columnIdxToUpdate).Value = "Xfinity"
		.Cells(getRowIdx(curSheetName, "Transport Type* :"),columnIdxToUpdate).Value = "Comcast Data"
		.Cells(getRowIdx(curSheetName, "Unified Communication Seats"), columnIdxToUpdate).Value = 4
		.Cells(getRowIdx(curSheetName, "Polycom VVX 311 HD"), columnIdxToUpdate).Value = 4
		'---------------------------------------------------------------------------------------------------------------------
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


