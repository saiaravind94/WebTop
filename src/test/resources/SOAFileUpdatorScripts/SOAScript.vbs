Set oShell = CreateObject("WScript.Shell")
SOAFolderPath = WScript.Arguments.Item(0)
Include Replace(SOAFolderPath, "WebTopSOADataFiles", "SOAFileUpdatorScripts") & "\Utilities"
log("*********************************")
log("SOA Script Execution started")
log("*********************************")
SOAFolderPath = WScript.Arguments.Item(0)
log("SOA Folder path is: " & SOAFolderPath)
Dim fso, path, file, recentDate, recentFile
Set fso = CreateObject("Scripting.FileSystemObject")
Set recentFile = Nothing
Dim RegEx :Set regEx = New RegExp
Dim objWorkbook : Set Workbook = Nothing
regEx.Pattern = WScript.Arguments.Item(1)
regEx.IgnoreCase = False
log("SOA File pattern to find: " & regEx.Pattern)
If(WScript.Arguments.Item(2) <> "") Then
	UniqueMailId =Split(WScript.Arguments.Item(2),"-")
	log("List of Email Ids: " & WScript.Arguments.Item(2))
Else
	Dim UniqueMailId()
End If
testCaseName = WScript.Arguments.Item(3)
log("Test Case Key: " & testCaseName)

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
	log("No recent files found on this path: " & SOAFolderPath & " with pattern: " & regEx.Pattern)
	Msgbox "No recent files found on this path: " & SOAFolderPath & " with pattern: " & regEx.Pattern
Else
	oShell.Run "TASKKILL /IM EXCEL.EXE /F", , True
	log("Killed already running EXCEL procesess if any")
	WScript.sleep 1000
	Set objExcel = CreateObject("Excel.Application")
	log("Created a EXCEL application object")
	oldFileName = SOAFolderPath & "\" & recentFile.Name
	log("SOA File name to update is: " & oldFileName)
	objExcel.DisplayAlerts = False
	On Error Resume Next
	Set objWorkbook = objExcel.Workbooks.Open(oldFileName)
	log("Opened the excel work book")
	objWorkbook.Activate
	On Error Goto 0
	curSheetName = "SOA Configurator"
	columnIdxToUpdate = getColumnIdx(curSheetName, "Site1")
	log("Column index(Site1) to update is: " & columnIdxToUpdate)
	With objWorkbook.Worksheets(curSheetName)
		SFOpportunityId = .Cells(getRowIdx(curSheetName, "SF Opportunity ID* :"), columnIdxToUpdate).Value
		log("SF Opportunity Id is: " & SFOpportunityId)
		newFileName = SOAFolderPath & "\" & testCaseName & "_" & (SFOpportunityId + 1) & ".xlsm"
		log("New SOA FIle name is: " & newFileName)
		Select Case testCaseName
			Case "AAPreProvision"
				BCPSOACommonFieldUpdate_V1_44 objWorkbook, curSheetName, testCaseName & "_", UniqueMailId
				.Cells(getRowIdx(curSheetName, "Sales Rep PERNR:"), 02).value = LEFT(getTimestamp(), 8)
				.Cells(getRowIdx(curSheetName, "Unified Communication Seats"), columnIdxToUpdate).Value = 3
			Case "ActiveAndInActiveAA", "SimonBasicTest", "SimonMACDTest", "TwinCitiesMarket"
				BCPSOACommonFieldUpdate_V1_44 objWorkbook, curSheetName, testCaseName & "_", UniqueMailId
				.Cells(getRowIdx(curSheetName, "Sales Rep PERNR:"), 02).value = Left(getTimestamp(), 8)
			Case "AddAAOnExistingUnUnsedAA"
				commonFieldUpdates objWorkbook, curSheetName, testCaseName & "_"
				.Cells(getRowIdx(curSheetName, "Standard Seats"), columnIdxToUpdate).Value = 2
				.Cells(getRowIdx(curSheetName, "Unified Communication Seats"), columnIdxToUpdate).Value = 2
				.Cells(getRowIdx(curSheetName, "Standard Seats")-1, columnIdxToUpdate).Value = 4
				.Cells(getRowIdx(curSheetName, "Auto Attendant - with a Local TN"), columnIdxToUpdate).Value = 1
				.Cells(getRowIdx(curSheetName, "Polycom SoundPoint VVX 410 HD"), columnIdxToUpdate).Value = 4
				.Cells(getRowIdx(curSheetName, "ALGO Strobe - Blue, Amber, and Red Lens Cover"), columnIdxToUpdate).Value = 1
				.Cells(getRowIdx(curSheetName, "ALGO Strobe - Clear"), columnIdxToUpdate).Value = 1
				.Cells(getRowIdx(curSheetName, "ALGO 8180 Loud Ringer"), columnIdxToUpdate).Value = 1
				.Cells(getRowIdx(curSheetName, "ALGO Weatherproof Loud Horn Add-On"), columnIdxToUpdate).Value = 1
			Case "BCP_1ActiveAA", "BCP_3ActiveAA", "BCP_PERNRValidationOFF", "FiberOrderWithNonListedPERNR"
				BCPSOACommonFieldUpdate_V1_44 objWorkbook, curSheetName, testCaseName & "_", UniqueMailId
				.Cells(getRowIdx(curSheetName, "Site Type* :"), columnIdxToUpdate).Value = "Standard"
				.Cells(getRowIdx(curSheetName, "Sales Rep PERNR:"), 2).value = LEFT(getTimestamp(), 8)
			Case "BCP_PERNRValidationON", "FiberOrderWithListedPERNR"
				BCPSOACommonFieldUpdate_V1_44 objWorkbook, curSheetName, testCaseName & "_", UniqueMailId
				.Cells(getRowIdx(curSheetName, "Site Type* :"), columnIdxToUpdate).Value = "Standard"
				.Cells(getRowIdx(curSheetName, "Sales Rep PERNR:"), 2).value = "10247770"
			Case "BCP_SBP_STD"
				BCPSOACommonFieldUpdate_V1_44 objWorkbook, curSheetName, testCaseName & "_", UniqueMailId
				.Cells(getRowIdx(curSheetName, "Site Type* :"), columnIdxToUpdate).Value = "Standard"
			Case "BCP_STD_5YrsTerm"
				BCPSOACommonFieldUpdate_V1_44 objWorkbook, curSheetName, testCaseName & "_", UniqueMailId
				.Cells(getRowIdx(curSheetName, "Site Type* :"), columnIdxToUpdate).Value = "Standard"
				.Cells(getRowIdx(curSheetName, "Service Term(Months):"), 02).Value = "60"
				.Cells(getRowIdx(curSheetName, "Email:"), 02).Value = UniqueMailId(0)
			Case "BCP_TW_3YrsTerm"
				BCPSOACommonFieldUpdate_V1_44 objWorkbook, curSheetName, testCaseName & "_", UniqueMailId
				.Cells(getRowIdx(curSheetName, "Site Type* :"), columnIdxToUpdate).Value = "Teleworker"
				.Cells(getRowIdx(curSheetName, "Service Term(Months):"), 02).Value = "36"
				.Cells(getRowIdx(curSheetName, "Email:"), 02).Value = UniqueMailId(0)
			Case "BLFFeaturewithSMBOrder"
				BCPSOACommonFieldUpdate_V1_44 objWorkbook, curSheetName, testCaseName & "_", uniqueMailId
				.Cells(getRowIdx(curSheetName, "Sales Rep PERNR:"), 02).value = LEFT(getTimestamp(), 8)
				.Cells(getRowIdx(curSheetName, "Sales Channel:"), 02).value = "SMB"
				.Cells(getRowIdx(curSheetName, "Service Term(Months):"), 02).value = 36
				.Cells(getRowIdx(curSheetName, "Unified Communication Seats"), columnIdxToUpdate).Value = 4
				.Cells(getRowIdx(curSheetName, "Polycom VVX 411 HD"), columnIdxToUpdate).Value = 4
			Case "BVE_BCP_CordlessOrder"
				BCPSOACommonFieldUpdate_V1_44 objWorkbook, curSheetName, testCaseName & "_", uniqueMailId
				curSheetName = "Sales Order Form 3"
				objWorkbook.Worksheets(curSheetName).Unprotect "adam88"
				objWorkbook.Worksheets(curSheetName).Cells(getRowIdx(curSheetName, "Special Configuration"), getColumnIdx(curSheetName, "Special Configuration")+1).Value = "BVE Cordless Office"
			Case "ColorExpansionCPE"
				BCPSOACommonFieldUpdate_V1_44 objWorkbook, curSheetName, testCaseName & "_", uniqueMailId
				.Cells(getRowIdx(curSheetName, "Sales Rep PERNR:"), 02).value = LEFT(getTimestamp(), 8)
				.Cells(getRowIdx(curSheetName, "Unified Communication Seats"), columnIdxToUpdate).Value = 5
				.Cells(getRowIdx(curSheetName, "Softphone-Only UC seats"), columnIdxToUpdate).Value = 2
				.Cells(getRowIdx(curSheetName, "Polycom VVX 411 HD"), columnIdxToUpdate).Value = 2
				.Cells(getRowIdx(curSheetName, "Polycom VVX 501 HD Phone"), columnIdxToUpdate).Value = 1
				.Cells(getRowIdx(curSheetName, "Polycom VVX 601 HD"), columnIdxToUpdate).Value = 1
				.Cells(getRowIdx(curSheetName, "Polycom VVX 1500 HD"), columnIdxToUpdate).Value = 1
				.Cells(getRowIdx(curSheetName, "Polycom VVX Color Expansion Module"), columnIdxToUpdate).Value = 3
			Case "CQA_TF_RCF_AA_HG"
				BCPSOACommonFieldUpdate_V1_44 objWorkbook, curSheetName, testCaseName & "_", uniqueMailId
				.Cells(getRowIdx(curSheetName, "Sales Rep PERNR:"), 02).value = LEFT(getTimestamp(), 8)
				.Cells(getRowIdx(curSheetName, "Unified Communication Seats"), columnIdxToUpdate).Value = 4
				.Cells(getRowIdx(curSheetName, "Softphone-Only UC seats"), columnIdxToUpdate).Value = 2
				.Cells(getRowIdx(curSheetName, "Call Queue Agent per UC Seat Subscribed"), columnIdxToUpdate).Value = 1
				.Cells(getRowIdx(curSheetName, "Toll Free Number(s) (plus usage)"), columnIdxToUpdate).Value = 1
				.Cells(getRowIdx(curSheetName, "Additional Hunt Group"), columnIdxToUpdate).Value = 1
				.Cells(getRowIdx(curSheetName, "Remote Call Forward"), columnIdxToUpdate).Value = 1
				.Cells(getRowIdx(curSheetName, "Polycom VVX 411 HD"), columnIdxToUpdate).Value = 4
				.Cells(getRowIdx(curSheetName, "Enterprise Ext. Dialing Option :"), columnIdxToUpdate).Value = "3 Digit"
				.Cells(getRowIdx(curSheetName, "Service Term(Months):"), 02).Value = "24"
			Case "CQA_SBP"
				BCPSOACommonFieldUpdate_V1_44 objWorkbook, curSheetName, testCaseName & "_", uniqueMailId
				.Cells(getRowIdx(curSheetName, "Sales Rep PERNR:"), 02).value = LEFT(getTimestamp(), 8)
				.Cells(getRowIdx(curSheetName, "Unified Communication Seats"), columnIdxToUpdate).Value = 4
				.Cells(getRowIdx(curSheetName, "Call Queue Agent per UC Seat Subscribed"), columnIdxToUpdate).Value = 1
				.Cells(getRowIdx(curSheetName, "Polycom VVX 411 HD"), columnIdxToUpdate).Value = 4
			Case "DLName_UCASE"
				BCPSOACommonFieldUpdate_V1_44 objWorkbook, curSheetName, testCaseName & "_", uniqueMailId
				.Cells(getRowIdx(curSheetName, "Sales Rep PERNR:"), 02).value = LEFT(getTimestamp(), 8)
			Case "E911AddressWithUnit"
				BCPSOACommonFieldUpdate_V1_44 objWorkbook, curSheetName, testCaseName & "_", uniqueMailId
				.Cells(getRowIdx(curSheetName, "Sales Rep PERNR:"), 02).value = LEFT(getTimestamp(), 8)
				.Cells(getRowIdx(curSheetName, "Unified Communication Seats"), columnIdxToUpdate).Value = 4
				.Cells(getRowIdx(curSheetName, "Softphone-Only UC seats"), columnIdxToUpdate).Value = 1
				.Cells(getRowIdx(curSheetName, "Unit :"), columnIdxToUpdate).Value = "APT"
				.Cells(getRowIdx(curSheetName, "Unit Value :"), columnIdxToUpdate).Value = "117"
			Case "E911BatchAddressUpdate"
				commonFieldUpdates objWorkbook, curSheetName, testCaseName & "_"
				.Cells(getRowIdx(curSheetName, "Standard Seats"), columnIdxToUpdate).Value = 4
				.Cells(getRowIdx(curSheetName, "Unified Communication Seats"), columnIdxToUpdate).Value = 1
				.Cells(getRowIdx(curSheetName, "Standard Seats")-1, columnIdxToUpdate).Value = 1
				.Cells(getRowIdx(curSheetName, "Polycom SoundPoint VVX 410 HD"), columnIdxToUpdate).Value = 5
				.Cells(getRowIdx(curSheetName, "Unit :"), columnIdxToUpdate).Value = "APT"
				.Cells(getRowIdx(curSheetName, "Unit Value :"), columnIdxToUpdate).Value = "117"
			Case "GovtPgm_RHC_WithENT"
				BCPSOACommonFieldUpdate_V1_44 objWorkbook, curSheetName, testCaseName & "_", uniqueMailId
				.Cells(getRowIdx(curSheetName, "Sales Rep PERNR:"), 02).value = LEFT(getTimestamp(), 8)
				.Cells(getRowIdx(curSheetName, "Government Program* :"), columnIdxToUpdate).Value = "Rural Health Care Only"
				.Cells(getRowIdx(curSheetName, "Sales Channel:"), 02).value = "Enterprise"
				.Cells(getRowIdx(curSheetName, "Service Term(Months):"), 02).value = "12"
			Case "HartfordCRCPTN"
				BCPSOACommonFieldUpdate_V1_44 objWorkbook, curSheetName, testCaseName & "_", uniqueMailId
				.Cells(getRowIdx(curSheetName, "Sales Rep PERNR:"), 02).value = LEFT(getTimestamp(), 8)
				.Cells(getRowIdx(curSheetName, "Unified Communication Seats"), columnIdxToUpdate).Value = 4
				.Cells(getRowIdx(curSheetName, "Polycom VVX 411 HD"), columnIdxToUpdate).Value = 4
				.Cells(getRowIdx(curSheetName, "Enterprise Ext. Dialing Option :"), columnIdxToUpdate).Value = "3 Digit"
			Case "LineBased", "MACD_LB", "PrequalTab"
				commonFieldUpdates objWorkbook, curSheetName, testCaseName & "_"
			Case "LRHWith3SLAndClearsExtn"
				BCPSOACommonFieldUpdate_V1_44 objWorkbook, curSheetName, testCaseName & "_", uniqueMailId
				.Cells(getRowIdx(curSheetName, "Sales Rep PERNR:"), 02).value = LEFT(getTimestamp(), 8)
				.Cells(getRowIdx(curSheetName, "Unified Communication Seats"), columnIdxToUpdate).Value = 4
				.Cells(getRowIdx(curSheetName, "Softphone-Only UC seats"), columnIdxToUpdate).Value = 2
				.Cells(getRowIdx(curSheetName, "Polycom VVX 411 HD"), columnIdxToUpdate).Value = 4
				.Cells(getRowIdx(curSheetName, "ALGO Strobe - Blue, Amber, and Red Lens Cover"), columnIdxToUpdate).Value = 1
				.Cells(getRowIdx(curSheetName, "ALGO Strobe - Clear"), columnIdxToUpdate).Value = 1
				.Cells(getRowIdx(curSheetName, "ALGO 8180 Loud Ringer"), columnIdxToUpdate).Value = 1
				.Cells(getRowIdx(curSheetName, "ALGO Weatherproof Loud Horn Add-On"), columnIdxToUpdate).Value = 1
			Case "MACD_TW_LB"
				commonFieldUpdates objWorkbook, curSheetName, testCaseName & "_"
				.Cells(getRowIdx(curSheetName, "Standard Seats")-1, columnIdxToUpdate).Value = 4
				.Cells(getRowIdx(curSheetName, "Standard Seats"), columnIdxToUpdate).Value = 2
				.Cells(getRowIdx(curSheetName, "Unified Communication Seats"), columnIdxToUpdate).Value = 2
				.Cells(getRowIdx(curSheetName, "Site Type* :"), columnIdxToUpdate).Value = "Teleworker"
				.Cells(getRowIdx(curSheetName, "Teleworker Internet N/W :"), columnIdxToUpdate).Value = "Xfinity"
				.Cells(getRowIdx(curSheetName, "Tranport Type* :"),columnIdxToUpdate).Value = "Comcast Data"
				.Cells(getRowIdx(curSheetName, "Enterprise Ext. Dialing Option :"), columnIdxToUpdate).Value = "6 Digit"
			Case "MACD_SBP_BVE_SWAP"
				commonFieldUpdates objWorkbook, curSheetName, testCaseName & "_"
				.Cells(getRowIdx(curSheetName, "Unified Communication Seats"), columnIdxToUpdate).Value = 3
				.Cells(getRowIdx(curSheetName, "Panasonic TGP600 Smart IP Cordless Phone Base Station"), columnIdxToUpdate).Value = 1
				.Cells(getRowIdx(curSheetName, "Panasonic KX-TPA65 Desktop DECT Phone"), columnIdxToUpdate).Value = 3
			Case "MACD_SBP_MS_PRODISCNT"
				site1ColumnIndex = getColumnIdx(curSheetName, "Site1")
				site2ColumnIndex = getColumnIdx(curSheetName, "Site2")
				newFileName = SOAFolderPath & "\" & testCaseName & "_" & (SFOpportunityId + 2) & ".xlsm"
				multiSiteOrderCommonFieldsUpdate objWorkbook, curSheetName, testCaseName & "_"
				' For Site1
				.Cells(getRowIdx(curSheetName, "Unified Communication Seats"), site1ColumnIndex).Value = 5
				.Cells(getRowIdx(curSheetName, "Polycom SoundPoint VVX 410 HD"), site1ColumnIndex).Value = 5
				' For Site2
				.Cells(getRowIdx(curSheetName, "Unified Communication Seats"), site2ColumnIndex).Value = 4
				.Cells(getRowIdx(curSheetName, "Polycom SoundPoint VVX 410 HD"), site2ColumnIndex).Value = 4
			Case "MACD_SBP_MultiSite_STD"
				site1ColumnIndex = getColumnIdx(curSheetName, "Site1")
				site2ColumnIndex = getColumnIdx(curSheetName, "Site2")
				newFileName = SOAFolderPath & "\" & testCaseName & "_" & (SFOpportunityId + 2) & ".xlsm"
				multiSiteOrderCommonFieldsUpdate objWorkbook, curSheetName, testCaseName & "_"
				' For Site1
				.Cells(getRowIdx(curSheetName, "Unified Communication Seats"), site1ColumnIndex).Value = 5
				.Cells(getRowIdx(curSheetName, "Polycom VVX 411 HD"), site1ColumnIndex).Value = 5
				' For Site2
				.Cells(getRowIdx(curSheetName, "Unified Communication Seats"), site2ColumnIndex).Value = 4
				.Cells(getRowIdx(curSheetName, "Polycom VVX 411 HD"), site2ColumnIndex).Value = 4
			case "MACD_SBP_MS_STD"
				site1ColumnIndex = getColumnIdx(curSheetName, "Site1")
				site2ColumnIndex = getColumnIdx(curSheetName, "Site2")
				newFileName = SOAFolderPath & "\" & testCaseName & "_" & (SFOpportunityId + 2) & ".xlsm"
				multiSiteOrderCommonFieldsUpdate objWorkbook, curSheetName, testCaseName & "_"
				' For Site1
				.Cells(getRowIdx(curSheetName, "Unified Communication Seats"), site1ColumnIndex).Value = 3
				.Cells(getRowIdx(curSheetName, "Polycom SoundPoint VVX 410 HD"), site1ColumnIndex).Value = 3
				' For Site2
				.Cells(getRowIdx(curSheetName, "Unified Communication Seats"), site2ColumnIndex).Value = 17
				.Cells(getRowIdx(curSheetName, "Polycom SoundPoint VVX 410 HD"), site2ColumnIndex).Value = 17
			Case "MACD_SBP_STD"
				commonFieldUpdates objWorkbook, curSheetName, testCaseName & "_"
				.Cells(getRowIdx(curSheetName, "Unified Communication Seats"), columnIdxToUpdate).Value = 5
				.Cells(getRowIdx(curSheetName, "Polycom VVX 311 HD"), columnIdxToUpdate).Value = 5
			Case "MACD_SBP_TW"
				commonFieldUpdates objWorkbook, curSheetName, testCaseName & "_"
				.Cells(getRowIdx(curSheetName, "Site Type* :"), columnIdxToUpdate).Value = "Teleworker"
				.Cells(getRowIdx(curSheetName, "Teleworker Internet N/W :"), columnIdxToUpdate).Value = "Xfinity"
				.Cells(getRowIdx(curSheetName, "Transport Type* :"),columnIdxToUpdate).Value = "Comcast Data"
				.Cells(getRowIdx(curSheetName, "Unified Communication Seats"), columnIdxToUpdate).Value = 4
				.Cells(getRowIdx(curSheetName, "Polycom VVX 311 HD"), columnIdxToUpdate).Value = 4
			Case "MissingLNBCPTrayValidations"
				commonFieldUpdates objWorkbook, curSheetName, testCaseName & "_"
				.Cells(getRowIdx(curSheetName, "Contact Name* :"), columnIdxToUpdate).Value = "FName"
				.Cells(getRowIdx(curSheetName, "Unified Communication Seats"), columnIdxToUpdate).Value = 4
				.Cells(getRowIdx(curSheetName, "Panasonic TGP600 Smart IP Cordless Phone Base Station"), columnIdxToUpdate).Value = 1
				.Cells(getRowIdx(curSheetName, "Panasonic KX-TPA65 Desktop DECT Phone"), columnIdxToUpdate).Value = 4
			Case "MultiSite_CQA_TF_RCF_HG_AA"
				site1ColumnIndex = getColumnIdx(curSheetName, "Site1")
				site2ColumnIndex = getColumnIdx(curSheetName, "Site2")
				newFileName = SOAFolderPath & "\" & testCaseName & "_" & (SFOpportunityId + 2) & ".xlsm"
				BCPSOACommonFieldUpdateForMultiSite_V1_44 objWorkbook, curSheetName,  testCaseName & "_", UniqueMailId
				.Cells(getRowIdx(curSheetName, "Sales Channel:"), 02).Value = "Indirect Sales"
				.Cells(getRowIdx(curSheetName, "Sales Rep PERNR:"), 02).value = LEFT(getTimestamp(), 8)
				' For Site1
				.Cells(getRowIdx(curSheetName, "Unified Communication Seats"), site1ColumnIndex).Value = 6
				.Cells(getRowIdx(curSheetName, "Softphone-Only UC seats"), site1ColumnIndex).Value = 1
				.Cells(getRowIdx(curSheetName, "Call Queue Agent per UC Seat Subscribed"), site1ColumnIndex).Value = 1
				.Cells(getRowIdx(curSheetName, "Toll Free Number(s) (plus usage)"), site1ColumnIndex).Value = 1
				.Cells(getRowIdx(curSheetName, "Additional Hunt Group"), site1ColumnIndex).Value = 1
				.Cells(getRowIdx(curSheetName, "Remote Call Forward"), site1ColumnIndex).Value = 1
				.Cells(getRowIdx(curSheetName, "Polycom VVX 411 HD"), site1ColumnIndex).Value = 6
				' For Site2
				.Cells(getRowIdx(curSheetName, "Unified Communication Seats"), site2ColumnIndex).Value = 6
				.Cells(getRowIdx(curSheetName, "Softphone-Only UC seats"), site2ColumnIndex).Value = 1
				.Cells(getRowIdx(curSheetName, "Call Queue Agent per UC Seat Subscribed"), site2ColumnIndex).Value = 1
				.Cells(getRowIdx(curSheetName, "Toll Free Number(s) (plus usage)"), site2ColumnIndex).Value = 1
				.Cells(getRowIdx(curSheetName, "Additional Hunt Group"), site2ColumnIndex).Value = 1
				.Cells(getRowIdx(curSheetName, "Remote Call Forward"), site2ColumnIndex).Value = 1
				.Cells(getRowIdx(curSheetName, "Polycom VVX 411 HD"), site2ColumnIndex).Value = 6
			Case "MultiSiteOrder"
				site1ColumnIndex = getColumnIdx(curSheetName, "Site1")
				site2ColumnIndex = getColumnIdx(curSheetName, "Site2")
				newFileName = SOAFolderPath & "\" & testCaseName & "_" & (SFOpportunityId + 2) & ".xlsm"
				BCPSOACommonFieldUpdateForMultiSite_V1_44 objWorkbook, curSheetName,  testCaseName & "_", UniqueMailId
				' For Site1
				.Cells(getRowIdx(curSheetName, "Unified Communication Seats"), site1ColumnIndex).Value = 6
				.Cells(getRowIdx(curSheetName, "Polycom VVX 411 HD"), site1ColumnIndex).Value = 6
				' For Site2
				.Cells(getRowIdx(curSheetName, "Unified Communication Seats"), site2ColumnIndex).Value = 6
				.Cells(getRowIdx(curSheetName, "Polycom VVX 411 HD"), site2ColumnIndex).Value = 6
				.Cells(getRowIdx(curSheetName, "Sales Rep PERNR:"), 02).value =  LEFT(getTimestamp(), 8)
			Case "OneBS8DECTBase3Repeater"
				commonFieldUpdates objWorkbook, curSheetName, testCaseName & "_"
				.Cells(getRowIdx(curSheetName, "Unified Communication Seats"), columnIdxToUpdate).Value = 8
				.Cells(getRowIdx(curSheetName, "Panasonic TGP600 Smart IP Cordless Phone Base Station"), columnIdxToUpdate).Value = 1
				.Cells(getRowIdx(curSheetName, "Panasonic KX-TPA65 Desktop DECT Phone"), columnIdxToUpdate).Value = 8
				.Cells(getRowIdx(curSheetName, "Panasonic KX-A406 Cordless Repeater"), columnIdxToUpdate).Value = 3
			Case "ProdTest_BSDQE_LB_Std"
				newFileName = SOAFolderPath & "\" & testCaseName & "_" & getMonthDate() & "_" & (SFOpportunityId + 1) & ".xlsm"
				commonFieldUpdatesForProdLBOrder objWorkbook, curSheetName, testCaseName & "_"
			Case "ProdTest_BSDQE_SB_Std"
				newFileName = SOAFolderPath & "\" & testCaseName & "_" & getMonthDate() & "_" & (SFOpportunityId + 1) & ".xlsm"
				commonFieldUpdatesForProdSBOrder objWorkbook, curSheetName, testCaseName & "_", UniqueMailId
				.Cells(getRowIdx(curSheetName, "Sales Rep PERNR:"), 02).value = LEFT(getTimestamp(), 8)
			Case "Reassign10DgtUCANITOVoIP"
				commonFieldUpdates objWorkbook, curSheetName, testCaseName & "_"
				.Cells(getRowIdx(curSheetName, "Standard Seats")-1, columnIdxToUpdate).Value = 4
				.Cells(getRowIdx(curSheetName, "Standard Seats"), columnIdxToUpdate).Value = 2
				.Cells(getRowIdx(curSheetName, "Unified Communication Seats"), columnIdxToUpdate).Value = 2
				.Cells(getRowIdx(curSheetName, "Softphone-Only UC seats"), columnIdxToUpdate).Value = 2
				.Cells(getRowIdx(curSheetName, "EdgeMarc 4550 (5 WAN Calls) - 1 per physical location"), columnIdxToUpdate).Value = 1
				.Cells(getRowIdx(curSheetName, "Polycom SoundPoint VVX 500 HD"), columnIdxToUpdate).Value = 4
				.Cells(getRowIdx(curSheetName, "ALGO Strobe - Blue, Amber, and Red Lens Cover"), columnIdxToUpdate).Value = 1
				.Cells(getRowIdx(curSheetName, "ALGO Strobe - Clear"), columnIdxToUpdate).Value = 1
				.Cells(getRowIdx(curSheetName, "ALGO 8180 Loud Ringer"), columnIdxToUpdate).Value = 1
				.Cells(getRowIdx(curSheetName, "ALGO Weatherproof Loud Horn Add-On"), columnIdxToUpdate).Value = 1
			Case "SeatBased" , "SNAPNotificationAutoTray"
				BCPSOACommonFieldUpdate_V1_44 objWorkbook, curSheetName, testCaseName & "_", uniqueMailId
				.Cells(getRowIdx(curSheetName, "Sales Rep PERNR:"), 02).value = LEFT(getTimestamp(), 8)
				.Cells(getRowIdx(curSheetName, "Unified Communication Seats"), columnIdxToUpdate).Value = 2
			Case "SBTExtensionOnly"
				BCPSOACommonFieldUpdate_V1_44 objWorkbook, curSheetName, testCaseName & "_", uniqueMailId
				.Cells(getRowIdx(curSheetName, "Sales Rep PERNR:"), 02).value = LEFT(getTimestamp(), 8)
				.Cells(getRowIdx(curSheetName, "Unified Communication Seats"), columnIdxToUpdate).Value = 4
			Case "SeatBasedNT_Validation"
				BCPSOACommonFieldUpdate_V1_44 objWorkbook, curSheetName, testCaseName & "_", uniqueMailId
				.Cells(getRowIdx(curSheetName, "Sales Rep PERNR:"), 02).value = LEFT(getTimestamp(), 8)
				.Cells(getRowIdx(curSheetName, "Unified Communication Seats"), columnIdxToUpdate).Value = 5
			Case "SimonOSValidations"
				BCPSOACommonFieldUpdate_V1_44 objWorkbook, curSheetName, testCaseName & "_", uniqueMailId
				.Cells(getRowIdx(curSheetName, "Sales Rep PERNR:"), 02).value = Left(getTimestamp() , 8)
				objWorkbook.Worksheets(curSheetName).Unprotect "adam@144"
				.Cells(getRowIdx(curSheetName, "Reception Console"), getColumnIdx(curSheetName, "Reception Console")+1).Value ="1"
				.Cells(getRowIdx(curSheetName, "Shared Call Appearance"), getColumnIdx(curSheetName, "Shared Call Appearance")+1).Value ="1"
			Case "SimonTWTest"
				BCPSOACommonFieldUpdate_V1_44 objWorkbook, curSheetName, testCaseName & "_", uniqueMailId
				.Cells(getRowIdx(curSheetName, "Sales Rep PERNR:"), 02).value = Left(getTimestamp() , 8)
				.Cells(getRowIdx(curSheetName, "Site Type* :"), 02).Value = "Teleworker"
			Case "TWsiteWithMultiNativeTN"
				BCPSOACommonFieldUpdate_V1_44 objWorkbook, curSheetName, testCaseName & "_", uniqueMailId
				.Cells(getRowIdx(curSheetName, "Sales Rep PERNR:"), 02).value = LEFT(getTimestamp(), 8)
				.Cells(getRowIdx(curSheetName, "Site Type* :"), columnIdxToUpdate).Value = "Teleworker"
				.Cells(getRowIdx(curSheetName, "Transport Type* :"), columnIdxToUpdate).Value = "Comcast Data"
				.Cells(getRowIdx(curSheetName, "Unified Communication Seats"), columnIdxToUpdate).Value = 4
				.Cells(getRowIdx(curSheetName, "Polycom VVX 411 HD"), columnIdxToUpdate).Value = 4
			case "VVXHD_311_411_501_601"
				BCPSOACommonFieldUpdate_V1_44 objWorkbook, curSheetName, testCaseName & "_", uniqueMailId
				.Cells(getRowIdx(curSheetName, "Sales Rep PERNR:"), 02).value = LEFT(getTimestamp(), 8)
				.Cells(getRowIdx(curSheetName, "Unified Communication Seats"), columnIdxToUpdate).Value = 4
				.Cells(getRowIdx(curSheetName, "Polycom VVX 311 HD"), columnIdxToUpdate).Value = 1
				.Cells(getRowIdx(curSheetName, "Polycom VVX 411 HD"), columnIdxToUpdate).Value = 1
				.Cells(getRowIdx(curSheetName, "Polycom VVX 501 HD"), columnIdxToUpdate).Value = 1
				.Cells(getRowIdx(curSheetName, "Polycom VVX 601 HD"), columnIdxToUpdate).Value = 1
			Case Else
				Msgbox "Test Case Name: " & testCaseName & " Not Matches any of the test case name in the switch statement"
				oShell.Run "TASKKILL /IM WSCRIPT.EXE /F", , True
				oShell.Run "TASKKILL /IM EXCEL.EXE /F", , True
		End Select
		log("Calling CloseValidationPopup.vbs")
		oShell.Run Chr(34) & Replace(SOAFolderPath, "WebTopSOADataFiles", "SOAFileUpdatorScripts") & "CloseValidationPopup.vbs"  & Chr(34)
		log("Calling PopulateSOAData Macro")
		objExcel.Application.Run "PopulateSOAData"
		objWorkbook.Save
		Set objWorkbook = Nothing
		' Close the running processes if any
		oShell.Run "TASKKILL /IM EXCEL.EXE /F", , True
		log("Kill the EXCEL process")
		WScript.sleep 1000
		fso.MoveFile oldFileName, newFileName
		log("Saved and renamed the SOA with new name: " & newFileName)
		' Run the Macros so that update will be reflected in the other sheets and will generate a new import file
		Set objExcel = CreateObject("Excel.Application")
		log("Create a object for EXCEL application")
		Set objWorkbook = objExcel.Workbooks.Open(newFileName)
		log("Opening the EXCEL workbook: "  & newFileName)
		curSheetName = "SIMON_Site1"
		On Error Resume Next
			If (UBound(UniqueMailId) >= 1) Then
				BCPSOASimonSiteUpdate_V1_44 objWorkbook, curSheetName, UniqueMailId
				log("Email Ids updated for all the Users in SIMON Site3")
			End If
		On Error Goto 0
		
		If(testCaseName = "MACD_SBP_BVE_SWAP") or (testCaseName = "MissingLNBCPTrayValidations") or(testCaseName = "OneBS8DECTBase3Repeater") Then
			tType =  .Cells(getRowIdx(curSheetName, "Training Type"), columnIdxToUpdate).value
			uniqueMailId = "Auto_" & getTimestamp()
			objWorkbook.Worksheets("Sales Order Form 3").Cells(getRowIdx("Sales Order Form 3", "Training Type") + 1, getColumnIdx("Sales Order Form 3", "Training Type")).Value = tType
			objWorkbook.Worksheets("Sales Order Form 3").Cells(getRowIdx("Sales Order Form 3", "Special Configuration"), getColumnIdx("Sales Order Form 3", "Special Configuration")+1).Value = "BVE Cordless Office"
			objWorkbook.Worksheets("Sales Order Form 3").Cells(getRowIdx("Sales Order Form 3", "Email:"), 04).Value = uniqueMailId
		End If
		log("Calling createWebtopEntityImportFile Macro")
		oShell.Run Chr(34) & Replace(SOAFolderPath, "WebTopSOADataFiles", "SOAFileUpdatorScripts") & "CloseValidationPopup.vbs"  & Chr(34)
		objExcel.Application.Run "createWebtopEntityImportFile"
		objWorkbook.Save
		objWorkbook.Close
		log("*********************************")
		log("SOA Script Execution Completed")
		log("*********************************")
		oShell.Run "TASKKILL /IM WSCRIPT.EXE /F", , True
		oShell.Run "TASKKILL /IM EXCEL.EXE /F", , True
		Set oShell = Nothing
		Set objWorkbook = Nothing
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
  Set oFSO = Nothing
End Sub


