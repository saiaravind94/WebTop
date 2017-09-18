Set oShell = CreateObject("WScript.Shell")
SOAFolderPath = WScript.Arguments.Item(0)
Include Replace(SOAFolderPath, "WebTopSOADataFiles", "SOAFileUpdatorScripts") & "\Utilities"
SOAFolderPath = WScript.Arguments.Item(0)
log("SOA Folder path is: " & SOAFolderPath)
Dim fso, path, file, recentDate, recentFile
Set fso = CreateObject("Scripting.FileSystemObject")
Set recentFile = Nothing
Dim RegEx :Set regEx = New RegExp
regEx.Pattern = WScript.Arguments.Item(1)
regEx.IgnoreCase = False
log("SOA File pattern to find: " & regEx.Pattern)

For Each file in fso.GetFolder(SOAFolderPath).Files
	If regEx.Test(file) Then
	  If (recentFile is Nothing) Then
		Set recentFile = file
	  ElseIf (file.Name > recentFile.Name) Then
		Set recentFile = file
	  End If
	End If
Next


oShell.Run "TASKKILL /IM EXCEL.EXE /F", , True
log("Killed already running EXCEL procesess if any")
WScript.sleep 1000
Set objExcel = CreateObject("Excel.Application")
log("Created a EXCEL application object")
oldFileName = SOAFolderPath & "\" & recentFile.Name
log("SOA File name to update is: " & oldFileName)
Set objWorkbook = objExcel.Workbooks.Open(oldFileName)
log("Opened the excel work book")
curSheetName = "SOA Configurator"
columnIdxToUpdate = getColumnIdx(curSheetName, "Site1")
log("Column index(Site1) to update is: " & columnIdxToUpdate)
log("Update 'Contact Name', 'Business Phone', 'Email' fields with blank values")
objWorkbook.Worksheets(curSheetName).Cells(getRowIdx(curSheetName, "Contact Name* :"), columnIdxToUpdate).Value = ""
objWorkbook.Worksheets(curSheetName).Cells(getRowIdx(curSheetName, "Business Phone* :"), columnIdxToUpdate).Value = ""
objWorkbook.Worksheets(curSheetName).Cells(getRowIdx(curSheetName, "Email* :"), columnIdxToUpdate).Value = ""
log("Updated the 'Contact Name', 'Business Phone', 'Email' fields with blank values")
log("Calling CloseValidationPopup.vbs")
oShell.Run Chr(34) & Replace(SOAFolderPath, "WebTopSOADataFiles", "SOAFileUpdatorScripts") & "CloseValidationPopup.vbs"  & Chr(34)
log("Calling PopulateSOAData Macro")
objExcel.Application.Run "PopulateSOAData"
objWorkbook.Save
Set objWorkbook = Nothing
oShell.Run "TASKKILL /IM EXCEL.EXE /F", , True
oShell.Run "TASKKILL /IM WSCRIPT.EXE /F", , True

Sub Include(yourFile)
  Dim oFSO, oFileBeingReadIn   ' define Objects
  Dim sFileContents            ' define Strings
 
  Set oFSO = CreateObject("Scripting.FileSystemObject")
  Set oFileBeingReadIn = oFSO.OpenTextFile(yourFile & ".vbs", 1)
  sFileContents = oFileBeingReadIn.ReadAll
  oFileBeingReadIn.Close
  ExecuteGlobal sFileContents
  Set oFSO = Nothing
End Sub
				
		