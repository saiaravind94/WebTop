' This script will update the following In Seat Based order as per SOA Version 1.40

' EXAMPLE SIMON SITE1
'	1	Seat	No Seat	BW Anywhere	BWAnyWhere	LN1733155853	LN1733155853@comcast.com	New			No Hardware												
'	2	Seat	No Seat	Voice Portal	VoicePortal	LN1733155854	LN1733155854@comcast.com	New			No Hardware												
'	3	Seat	UC Seat	Standard User	STDSeat001	LN1733155855	LN1733155855@comcast.com	New			Polycom VVX 310 HD												
'	4	Seat	UC Seat	Standard User	STDSeat002	LN1733155856	LN1733155856@comcast.com	New			Polycom VVX 310 HD												
'	5	Seat	UC Seat	Tech Admin User	TABTNUCSeat001	LN1733155857	LN1733155857@comcast.com	New			Polycom VVX 410 HD												
'	6	Seat	UC Seat	Enterprise Admin User	UCSeat002	LN1733155858	LN1733155858@comcast.com	New			Polycom VVX 410 HD												


' New outpul file will be generated with TwinCitiesMarket_<UpdatedOppornityID>.xlsm pattern
' Author : Kesavan(krajam003c)
' Last Updated : 7th Mar 2017

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

		newFileName = SOAFolderPath & "TwinCitiesMarket_" & (SFOpportunityId + 1) & ".xlsm"

		commonFieldUpdates objWorkbook, curSheetName, "TwinCitiesMarket_"
		
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
  Dim oFSO, oFileBeingReadIn
  Dim sFileContents 
  Set oFSO = CreateObject("Scripting.FileSystemObject")
  Set oFileBeingReadIn = oFSO.OpenTextFile(yourFile & ".vbs", 1)
  sFileContents = oFileBeingReadIn.ReadAll
  oFileBeingReadIn.Close
  ExecuteGlobal sFileContents
End Sub


