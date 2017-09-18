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

' New outpul file will be generated with MultiSite_CQA_TF_RCF_HG_AA_<UpdatedOppornityID>.xlsm pattern
' Author : Kesavan(krajam003c)
' Last Updated : 25th Jan 2017

Set oShell = CreateObject("WScript.Shell")
SOAFolderPath = WScript.Arguments.Item(0)
'SOAFolderPath = "D:\Webtop\src\test\resources\WebTopSOADataFiles\"
Dim fso, path, file, recentDate, recentFile
Set fso = CreateObject("Scripting.FileSystemObject")
Set recentFile = Nothing
Dim RegEx :Set regEx = New RegExp
regEx.Pattern =WScript.Arguments.Item(1)

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
		
		newFileName = SOAFolderPath & "MultiSite_CQA_TF_RCF_HG_AA_" & (SFOpportunityId + 2) & ".xlsm"

		multiSiteOrderCommonFieldsUpdate objWorkbook, curSheetName, "MultiSite_CQA_TF_RCF_HG_AA_"
		
		.Cells(getRowIdx(curSheetName, "Sales Channel:"), 02).Value = "Indirect Sales"
		
		' For Site1
		.Cells(getRowIdx(curSheetName, "Standard Seats") - 1, site1ColumnIndex).Value = 4 ' Lines 
		.Cells(getRowIdx(curSheetName, "Standard Seats"), site1ColumnIndex).Value = 3
		.Cells(getRowIdx(curSheetName, "Unified Communication Seats"), site1ColumnIndex).Value = 3
		.Cells(getRowIdx(curSheetName, "Softphone-Only UC seats"), site1ColumnIndex).Value = 1
		.Cells(getRowIdx(curSheetName, "Call Queue Agent per UC Seat Subscribed"), site1ColumnIndex).Value = 1
		.Cells(getRowIdx(curSheetName, "Toll Free Number(s) (plus usage)"), site1ColumnIndex).Value = 1
		.Cells(getRowIdx(curSheetName, "Auto Attendant - with a Local TN"), site1ColumnIndex).Value = 1
		.Cells(getRowIdx(curSheetName, "Additional Hunt Group"), site1ColumnIndex).Value = 1
		.Cells(getRowIdx(curSheetName, "Remote Call Forward"), site1ColumnIndex).Value = 1
		.Cells(getRowIdx(curSheetName, "Polycom SoundPoint VVX 410 HD"), site1ColumnIndex).Value = 6
		.Cells(getRowIdx(curSheetName, "EdgeMarc 4550 (5 WAN Calls) - 1 per physical location"), site1ColumnIndex).Value = 1
		
		' For Site2
		.Cells(getRowIdx(curSheetName, "Standard Seats") - 1, site2ColumnIndex).Value = 4 ' Lines 
		.Cells(getRowIdx(curSheetName, "Standard Seats"), site2ColumnIndex).Value = 3
		.Cells(getRowIdx(curSheetName, "Unified Communication Seats"), site2ColumnIndex).Value = 3
		.Cells(getRowIdx(curSheetName, "Softphone-Only UC seats"), site2ColumnIndex).Value = 1
		.Cells(getRowIdx(curSheetName, "Call Queue Agent per UC Seat Subscribed"), site2ColumnIndex).Value = 1
		.Cells(getRowIdx(curSheetName, "Toll Free Number(s) (plus usage)"), site2ColumnIndex).Value = 1
		.Cells(getRowIdx(curSheetName, "Auto Attendant - with a Local TN"), site2ColumnIndex).Value = 1
		.Cells(getRowIdx(curSheetName, "Additional Hunt Group"), site2ColumnIndex).Value = 1
		.Cells(getRowIdx(curSheetName, "Remote Call Forward"), site2ColumnIndex).Value = 1
		.Cells(getRowIdx(curSheetName, "Polycom SoundPoint VVX 410 HD"), site2ColumnIndex).Value = 6
		.Cells(getRowIdx(curSheetName, "EdgeMarc 4550 (5 WAN Calls) - 1 per physical location"), site2ColumnIndex).Value = 1
		
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


