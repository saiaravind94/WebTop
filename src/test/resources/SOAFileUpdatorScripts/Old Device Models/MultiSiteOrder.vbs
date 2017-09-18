' This script will update the following In Seat Based order as per SOA Version 1.40

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
'	1	Seat	No Seat	BW Anywhere	FN7125113660	BWAnyWhere	Kesavan_Rajamanickam@cable.comcast.com	New			No Hardware												
'	2	Seat	No Seat	Voice Portal	FN7125113661	VoicePortal	Kesavan_Rajamanickam@cable.comcast.com	New			No Hardware												
'	3	Seat	UC Seat	Tech Admin User	FN7125113662	TABTNstdseat001	Kesavan_Rajamanickam@cable.comcast.com	New			Polycom VVX 310 HD												
'	4	Seat	UC Seat	Enterprise Admin User	FN7125113663	EAStdSeat002	Kesavan_Rajamanickam@cable.comcast.com	New			Polycom VVX 410 HD												
'	5	Seat	UC Seat	Standard User	FN7125113664	UCSeat003	Kesavan_Rajamanickam@cable.comcast.com	New			Polycom VVX 500 HD												
'	6	Seat	UC Seat	Standard User	FN7125113665	UCSeat001	Kesavan_Rajamanickam@cable.comcast.com	New			Polycom VVX 600 HD												

' New outpul file will be generated with MultiSite_<UpdatedOppornityID>.xlsm pattern
' Author : Kesavan(krajam003c)
' Last Updated : 25th Jan 2017

Set oShell = CreateObject("WScript.Shell")
'SOAFolderPath = WScript.Arguments.Item(0)
SOAFolderPath = "D:\Webtop\src\test\resources\WebTopSOADataFiles\"
Dim fso, path, file, recentDate, recentFile
Set fso = CreateObject("Scripting.FileSystemObject")
Set recentFile = Nothing
Dim RegEx :Set regEx = New RegExp
'regEx.Pattern = WScript.Arguments.Item(1)
regEx.Pattern ="MultiSiteOrder_.*.xlsm"
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
	site1ColumnIndex = getColumnIdx(curSheetName, "Site1")
	site2ColumnIndex = getColumnIdx(curSheetName, "Site2")
	
	With objWorkbook.Worksheets(curSheetName)
		SFOpportunityId = .Cells(getRowIdx(curSheetName, "SF Opportunity ID* :"), site1ColumnIndex).Value
		
		newFileName = SOAFolderPath & "MultiSiteOrder_" & (SFOpportunityId + 2) & ".xlsm"
		multiSiteOrderCommonFieldsUpdate objWorkbook, curSheetName, "MultiSiteOrder_"
		
		' For Site1
		.Cells(getRowIdx(curSheetName, "Unified Communication Seats"), site1ColumnIndex).Value = 6
		.Cells(getRowIdx(curSheetName, "Polycom SoundPoint VVX 410 HD"), site1ColumnIndex).Value = 6
		
		' For Site2
		.Cells(getRowIdx(curSheetName, "Unified Communication Seats"), site2ColumnIndex).Value = 6
		.Cells(getRowIdx(curSheetName, "Polycom SoundPoint VVX 410 HD"), site2ColumnIndex).Value = 6
		
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


