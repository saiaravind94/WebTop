
	Function getRowIdx(sheetName, cellText)
		objWorkbook.Worksheets(sheetName).Select
		Dim rngX : Set rngX = objWorkbook.Worksheets(sheetName).Range("A1:XX10000").Find(cellText)
		getRowIdx = rngX.row
	End Function
	
	Function getColumnIdx(sheetName, cellText)
		objWorkbook.Worksheets(sheetName).Select
		Dim rngX : Set rngX = objWorkbook.Worksheets(sheetName).Range("A1:XX10000").Find(cellText)
		getColumnIdx = rngX.column
	End Function
	
	Function getTimestamp()
		curDate = now
		getTimestamp = year(curdate) & Right(String(2, "0") & Month(curdate), 2) & Right(String(2, "0") & day(curdate), 2) & Hour(curdate)& Minute(curdate)& Second(curdate)
	End Function
	
	Function getDateAndTimeHourMin()
		curDate = now
		getDateAndTimeHourMin = year(curdate) &Right(String(2, "0") & Month(curdate), 2) & Right(String(2, "0") & day(curdate), 2)& Hour(curdate)& Minute(curdate)
	End Function
	
	Function getMonthDate()
		curDate = now
		getMonthDate = Right(String(2, "0") & Month(curdate), 2) & Right(String(2, "0") & day(curdate), 2) 
	End Function
	
	Function log(ByVal message)
		Const ForReading = 1, ForWriting = 2, ForAppending = 8, TristateFalse = 0
		Dim fs, f
		' Creates a new file object
		AFileName = "C:\logs\SOAScriptExecutorLog.txt"
		Set  fs = CreateObject("Scripting.FileSystemObject")
		If Not fs.FileExists(AFileName) Then
			Set f = fs.CreateTextFile(AFileName)
		Else
			Set f = fs.OpenTextFile(AFileName, ForAppending, TristateFalse)
		End If
		f.Write getTimestamp() & " [INFO] " & message & vbNewLine
		f.Close
		Set fs = Nothing
		Set f = Nothing
	End Function
	
	Function commonFieldUpdates(ByVal objWorkbook, ByVal curSheetName, ByVal testName)
		With objWorkbook.Worksheets(curSheetName)
			
			' Get the Old Values
			LeadId = .Cells(getRowIdx(curSheetName, "Lead ID* :"), columnIdxToUpdate).Value
			SFAccountId = .Cells(getRowIdx(curSheetName, "SF Account ID* :"), columnIdxToUpdate).Value
			SFLocationId = .Cells(getRowIdx(curSheetName, "SF Location ID* :"), columnIdxToUpdate).Value
			SFOpportunityId = .Cells(getRowIdx(curSheetName, "SF Opportunity ID* :"), columnIdxToUpdate).Value
			SalesRepPERNR = .Cells(getRowIdx(curSheetName, "Sales Rep PERNR:"), 02).Value

			' Update the new values
			.Cells(getRowIdx(curSheetName, "Billing Account Name :"), columnIdxToUpdate).Value = testName & getTimestamp()
			.Cells(getRowIdx(curSheetName, "Location Name* :"), columnIdxToUpdate).Value = testName & getTimestamp()
			.Cells(getRowIdx(curSheetName, "Company Name* :"), columnIdxToUpdate).Value = testName & getTimestamp()
			.Cells(getRowIdx(curSheetName, "Lead ID* :"), columnIdxToUpdate).Value = LeadId + 1
			.Cells(getRowIdx(curSheetName, "SF Account ID* :"), columnIdxToUpdate).Value = SFAccountId + 1 
			.Cells(getRowIdx(curSheetName, "SF Location ID* :"), columnIdxToUpdate).Value = SFLocationId + 1
			.Cells(getRowIdx(curSheetName, "SF Opportunity ID* :"), columnIdxToUpdate).Value = SFOpportunityId + 1
			.Cells(getRowIdx(curSheetName, "Account Name:"), 02).Value = testName & getTimestamp()
			.Cells(getRowIdx(curSheetName, "Sales Rep PERNR:"), 02).value = SalesRepPERNR + 1
			.Cells(getRowIdx(curSheetName, "Contact Name* :"), columnIdxToUpdate).Value = "FName LName" & getTimestamp()
			.Cells(getRowIdx(curSheetName, "Business Phone* :"), columnIdxToUpdate).Value = "98" & right(getTimestamp(),8)
			.Cells(getRowIdx(curSheetName, "Cell Phone :"), columnIdxToUpdate).Value =  "98" & right(getTimestamp(),8)
			.Cells(getRowIdx(curSheetName, "Fax Number :"), columnIdxToUpdate).Value = "98" & right(getTimestamp(),8)
			.Cells(getRowIdx(curSheetName, "Email* :"), columnIdxToUpdate).Value = "email_"& right(getTimestamp(),8) & "@maildrop.cc"
			.Cells(getRowIdx(curSheetName, "Tech Contact Email Address* :"), columnIdxToUpdate).Value = "email_"& right(getTimestamp(),8) & "@maildrop.cc"
			.Cells(getRowIdx(curSheetName, "Billing Contact Name :"), columnIdxToUpdate).Value = testName & right(getTimestamp(),8)
			.Cells(getRowIdx(curSheetName, "Billing Contact Phone :"), columnIdxToUpdate).Value =  "98" & right(getTimestamp(),8)
			.Cells(getRowIdx(curSheetName, "Billing Contact Fax :"), columnIdxToUpdate).Value =  "98" & right(getTimestamp(),8)
			.Cells(getRowIdx(curSheetName, "Billing Contact Email :"), columnIdxToUpdate).Value = "email_"& right(getTimestamp(),8) & "@maildrop.cc"                  
			.Cells(getRowIdx(curSheetName, "Primary Contact:"), 02).Value = "FName " & testName & getTimestamp()
			.Cells(getRowIdx(curSheetName, "Business Phone:"), 02).Value = "98" & right(getTimestamp(),8)
			.Cells(getRowIdx(curSheetName, "Cell Phone:"), 02).Value = "98" & right(getTimestamp(),8)
			.Cells(getRowIdx(curSheetName, "Fax:"), 02).Value = "98" & right(getTimestamp(),8)
			.Cells(getRowIdx(curSheetName, "Email:"), 02).Value = "email_"& right(getTimestamp(),8) & "@maildrop.cc"
			.Cells(getRowIdx(curSheetName, "Acct Rep Email:"), 02).Value = "email_"& right(getTimestamp(),8) & "@maildrop.cc"
			.Cells(getRowIdx(curSheetName, "VSS/SE Email:"), 02).Value = "email_"& right(getTimestamp(),8) & "@maildrop.cc"
		End With
	End Function
	
	Function commonFieldUpdatesForProdLBOrder(ByVal objWorkbook, ByVal curSheetName, ByVal testName)
		With objWorkbook.Worksheets(curSheetName)
			
			' Get the Old Values
			LeadId = .Cells(getRowIdx(curSheetName, "Lead ID* :"), columnIdxToUpdate).Value
			SFAccountId = .Cells(getRowIdx(curSheetName, "SF Account ID* :"), columnIdxToUpdate).Value
			SFLocationId = .Cells(getRowIdx(curSheetName, "SF Location ID* :"), columnIdxToUpdate).Value
			SFOpportunityId = .Cells(getRowIdx(curSheetName, "SF Opportunity ID* :"), columnIdxToUpdate).Value
			SalesRepPERNR = .Cells(getRowIdx(curSheetName, "Sales Rep PERNR:"), 02).Value

			' Update the new values
			.Cells(getRowIdx(curSheetName, "Billing Account Name :"), columnIdxToUpdate).Value = testName & getDateAndTimeHourMin()
			.Cells(getRowIdx(curSheetName, "Location Name* :"), columnIdxToUpdate).Value = testName & getDateAndTimeHourMin()
			.Cells(getRowIdx(curSheetName, "Company Name* :"), columnIdxToUpdate).Value = testName & getDateAndTimeHourMin()
			.Cells(getRowIdx(curSheetName, "Lead ID* :"), columnIdxToUpdate).Value = LeadId + 1
			SFAccountId = Split(SFAccountId, "-")
			newSFAccountId = "A-" & (SFAccountId(1) + 1)
			.Cells(getRowIdx(curSheetName, "SF Account ID* :"), columnIdxToUpdate).Value = newSFAccountId
			.Cells(getRowIdx(curSheetName, "SF Location ID* :"), columnIdxToUpdate).Value = SFLocationId + 1
			.Cells(getRowIdx(curSheetName, "SF Opportunity ID* :"), columnIdxToUpdate).Value = SFOpportunityId + 1
			.Cells(getRowIdx(curSheetName, "Account Name:"), 02).Value = testName & getDateAndTimeHourMin()
			.Cells(getRowIdx(curSheetName, "Sales Rep PERNR:"), 02).value = SalesRepPERNR + 1
			.Cells(getRowIdx(curSheetName, "Email* :"), columnIdxToUpdate).Value = "prodtest" & getDateAndTimeHourMin() & "@yopmail.com"
			.Cells(getRowIdx(curSheetName, "Email:"), 02).Value = "prodtest" & getDateAndTimeHourMin() & "@yopmail.com"
		End With
	End Function
	
	Function multiSiteOrderCommonFieldsUpdate(ByVal objWorkbook, ByVal curSheetName, ByVal testName)
		With objWorkbook.Worksheets(curSheetName)
			
			' Get the Old Values from Site1
			LeadId = .Cells(getRowIdx(curSheetName, "Lead ID* :"), site1ColumnIndex).Value
			SFAccountId = .Cells(getRowIdx(curSheetName, "SF Account ID* :"), site1ColumnIndex).Value
			SFLocationId = .Cells(getRowIdx(curSheetName, "SF Location ID* :"), site1ColumnIndex).Value
			SFOpportunityId = .Cells(getRowIdx(curSheetName, "SF Opportunity ID* :"), site1ColumnIndex).Value
			SalesRepPERNR = .Cells(getRowIdx(curSheetName, "Sales Rep PERNR:"), 02).Value

			' Update the new values for Site1, Site2
			.Cells(getRowIdx(curSheetName, "Billing Account Name :"), site1ColumnIndex).Value = testName & getTimestamp()
			.Cells(getRowIdx(curSheetName, "Billing Account Name :"), site2ColumnIndex).Value = testName & getTimestamp()
			
			.Cells(getRowIdx(curSheetName, "Location Name* :"), site1ColumnIndex).Value = testName & "Site1_" & getTimestamp()
			.Cells(getRowIdx(curSheetName, "Location Name* :"), site2ColumnIndex).Value = testName & "Site2_" & getTimestamp()
			
			.Cells(getRowIdx(curSheetName, "Company Name* :"), site1ColumnIndex).Value = testName & getTimestamp()
			.Cells(getRowIdx(curSheetName, "Company Name* :"), site2ColumnIndex).Value = testName & getTimestamp()
			
			.Cells(getRowIdx(curSheetName, "Lead ID* :"), site1ColumnIndex).Value = LeadId + 2
			.Cells(getRowIdx(curSheetName, "Lead ID* :"), site2ColumnIndex).Value = LeadId + 2
			
			.Cells(getRowIdx(curSheetName, "SF Account ID* :"), site1ColumnIndex).Value = SFAccountId + 2
			.Cells(getRowIdx(curSheetName, "SF Account ID* :"), site2ColumnIndex).Value = SFAccountId + 2
			
			.Cells(getRowIdx(curSheetName, "SF Location ID* :"), site1ColumnIndex).Value = SFLocationId + 2
			.Cells(getRowIdx(curSheetName, "SF Location ID* :"), site2ColumnIndex).Value = .Cells(getRowIdx(curSheetName, "SF Location ID* :"), site2ColumnIndex).Value + 2
			
			.Cells(getRowIdx(curSheetName, "SF Opportunity ID* :"), site1ColumnIndex).Value = SFOpportunityId + 2
			.Cells(getRowIdx(curSheetName, "SF Opportunity ID* :"), site2ColumnIndex).Value = .Cells(getRowIdx(curSheetName, "SF Opportunity ID* :"), site2ColumnIndex).Value + 2
			
			.Cells(getRowIdx(curSheetName, "Contact Name* :"), site1ColumnIndex).Value = "FName LNameSite1_" & getTimestamp()
			.Cells(getRowIdx(curSheetName, "Contact Name* :"), site2ColumnIndex).Value = "FName LNameSite2_" & getTimestamp()
			
			.Cells(getRowIdx(curSheetName, "Business Phone* :"), site1ColumnIndex).Value = "98" & right(getTimestamp(),8)
			.Cells(getRowIdx(curSheetName, "Business Phone* :"), site2ColumnIndex).Value = "98" & right(getTimestamp(),8)
			
			.Cells(getRowIdx(curSheetName, "Cell Phone :"), site1ColumnIndex).Value =  "98" & right(getTimestamp(),8)
			.Cells(getRowIdx(curSheetName, "Cell Phone :"), site2ColumnIndex).Value =  "98" & right(getTimestamp(),8)
			
			.Cells(getRowIdx(curSheetName, "Fax Number :"), site1ColumnIndex).Value = "98" & right(getTimestamp(),8)
			.Cells(getRowIdx(curSheetName, "Fax Number :"), site2ColumnIndex).Value = "98" & right(getTimestamp(),8)
			
			.Cells(getRowIdx(curSheetName, "Email* :"), site1ColumnIndex).Value = "email_"& right(getTimestamp(),8) & "@maildrop.cc"
			.Cells(getRowIdx(curSheetName, "Email* :"), site2ColumnIndex).Value = "email_"& right(getTimestamp(),8) & "@maildrop.cc"
			
			.Cells(getRowIdx(curSheetName, "Tech Contact Email Address* :"), site1ColumnIndex).Value = "email_"& right(getTimestamp(),8) & "@maildrop.cc"
			.Cells(getRowIdx(curSheetName, "Tech Contact Email Address* :"), site2ColumnIndex).Value = "email_"& right(getTimestamp(),8) & "@maildrop.cc"
			
			.Cells(getRowIdx(curSheetName, "Billing Contact Name :"), site1ColumnIndex).Value = testName & right(getTimestamp(),8)
			.Cells(getRowIdx(curSheetName, "Billing Contact Name :"), site2ColumnIndex).Value = testName & right(getTimestamp(),8)
			
			.Cells(getRowIdx(curSheetName, "Billing Contact Phone :"), site1ColumnIndex).Value =  "98" & right(getTimestamp(),8)
			.Cells(getRowIdx(curSheetName, "Billing Contact Phone :"), site2ColumnIndex).Value =  "98" & right(getTimestamp(),8)
			
			.Cells(getRowIdx(curSheetName, "Billing Contact Fax :"), site1ColumnIndex).Value =  "98" & right(getTimestamp(),8)
			.Cells(getRowIdx(curSheetName, "Billing Contact Fax :"), site2ColumnIndex).Value =  "98" & right(getTimestamp(),8)
			
			.Cells(getRowIdx(curSheetName, "Billing Contact Email :"), site1ColumnIndex).Value = "email_"& right(getTimestamp(),8) & "@maildrop.cc"
			.Cells(getRowIdx(curSheetName, "Billing Contact Email :"), site2ColumnIndex).Value = "email_"& right(getTimestamp(),8) & "@maildrop.cc"
			
			.Cells(getRowIdx(curSheetName, "Primary Site* :"), site1ColumnIndex).Value = "Yes"
			.Cells(getRowIdx(curSheetName, "Primary Site* :"), site2ColumnIndex).Value = "No"
			
			
			.Cells(getRowIdx(curSheetName, "Account Name:"), 02).Value = testName & getTimestamp()
			.Cells(getRowIdx(curSheetName, "Sales Rep PERNR:"), 02).value = SalesRepPERNR + 2                  
			.Cells(getRowIdx(curSheetName, "Primary Contact:"), 02).Value = "FName " & testName & getTimestamp()
			.Cells(getRowIdx(curSheetName, "Business Phone:"), 02).Value = "98" & right(getTimestamp(),8)
			.Cells(getRowIdx(curSheetName, "Cell Phone:"), 02).Value = "98" & right(getTimestamp(),8)
			.Cells(getRowIdx(curSheetName, "Fax:"), 02).Value = "98" & right(getTimestamp(),8)
			.Cells(getRowIdx(curSheetName, "Email:"), 02).Value = "email_"& right(getTimestamp(),8) & "@maildrop.cc"
			.Cells(getRowIdx(curSheetName, "Acct Rep Email:"), 02).Value = "email_"& right(getTimestamp(),8) & "@maildrop.cc"
			.Cells(getRowIdx(curSheetName, "VSS/SE Email:"), 02).Value = "email_"& right(getTimestamp(),8) & "@maildrop.cc"
		End With
	End Function
	
	Function BCPSOAUpdate(ByVal objWorkbook, ByVal curSheetName, ByVal testName)
		With objWorkbook.Worksheets(curSheetName)
			
		' Get Current timetamp with yyyymmddhhmmss foramt
		curDate = now
		getTimestamp() = year(curdate) & month(curdate)& day(curdate)& Hour(curdate)& Minute(curdate)& Second(curdate)
		
		objWorkbook.Worksheets("Sales Order Form 2").Cells(getRowIdx("Sales Order Form 2", "Service Term: "), getColumnIdx("Sales Order Form 2", "Service Term: ")+1).Value = "12"
		
		' Customer Information
		.Cells(getRowIdx(curSheetName, "Location Name:"), getColumnIdx(curSheetName, "Location Name:")+1).Value = testName & getTimestamp()
		.Cells(getRowIdx(curSheetName, "Company Name:"), getColumnIdx(curSheetName, "Company Name:")+1).Value = testName & getTimestamp()
		.Cells(getRowIdx(curSheetName, "Contact Name:"), getColumnIdx(curSheetName, "Contact Name:")+1).Value = "FName LName" & getTimestamp()
		.Cells(getRowIdx(curSheetName, "Address 1:"), getColumnIdx(curSheetName, "Address 1:")+1).Value = "12 ADAMS LN"
		.Cells(getRowIdx(curSheetName, "Address 2:"), getColumnIdx(curSheetName, "Address 2:")+1).Value = ""
		.Cells(getRowIdx(curSheetName, "City:"), getColumnIdx(curSheetName, "City:")+1).Value = "DOWNINGTOWN"
		.Cells(getRowIdx(curSheetName, "State:"), getColumnIdx(curSheetName, "State:")+1).Value = "PA"
		.Cells(getRowIdx(curSheetName, "Zip:"), getColumnIdx(curSheetName, "Zip:")+1).Value = "19335"
		.Cells(getRowIdx(curSheetName, "Business Phone:"), getColumnIdx(curSheetName, "Business Phone:")+1).Value = "98" & right(getTimestamp(),8)
		.Cells(getRowIdx(curSheetName, "Cell Phone:"), getColumnIdx(curSheetName, "Cell Phone:")+1).Value = "98" & right(getTimestamp(),8)
		.Cells(getRowIdx(curSheetName, "Fax Number:"), getColumnIdx(curSheetName, "Fax Number:")+1).Value = "98" & right(getTimestamp(),8)
		.Cells(getRowIdx(curSheetName, "Email:"), getColumnIdx(curSheetName, "Email:")+1).Value = "email_"& right(getTimestamp(),8) & "@maildrop.cc"
		.Cells(getRowIdx(curSheetName, "Technical Contact Name:"), getColumnIdx(curSheetName, "Technical Contact Name:")+1).Value = "Deskin"
		.Cells(getRowIdx(curSheetName, "Technical Contact Phone Number:"), getColumnIdx(curSheetName, "Technical Contact Phone Number:")+1).Value = "98" & right(getTimestamp(),8)
		.Cells(getRowIdx(curSheetName, "Technical Contact Email Address:"), getColumnIdx(curSheetName, "Technical Contact Email Address:")+1).Value =  "email_"& right(getTimestamp(),8) & "@maildrop.cc"
		.Cells(getRowIdx(curSheetName, "Technical Contact On-Site (Y/N)"), getColumnIdx(curSheetName, "Technical Contact On-Site (Y/N)")+1).Value = "Y"
		.Cells(getRowIdx(curSheetName, "Site Type:"), getColumnIdx(curSheetName, "Site Type:")+1).Value = "Standard"
		
		' Billing Information
		.Cells(getRowIdx(curSheetName, "Billing Address Details Same as Service Location?"), getColumnIdx(curSheetName, "Billing Address Details Same as Service Location?")+1).Value = "Yes"
		.Cells(getRowIdx(curSheetName, "Tax Exempt*?"), getColumnIdx(curSheetName, "Tax Exempt*?")+1).Value = "Yes"
		
		.Cells(getRowIdx(curSheetName, "Date of Quote:"), getColumnIdx(curSheetName, "Date of Quote:")+1).Value = FormatDateTime(Now, vbShortDate)
		.Cells(getRowIdx(curSheetName, "Directory Listing"), getColumnIdx(curSheetName, "Directory Listing")+1).Value = "Published"
		.Cells(getRowIdx(curSheetName, "Porting?"), getColumnIdx(curSheetName, "Porting?")+1).Value = "No"
		.Cells(getRowIdx(curSheetName, "Customer requests International Dialing?"), getColumnIdx(curSheetName, "Customer requests International Dialing?")+1).Value = "No"
		
		'Emergency E911 Information
		.Cells(getRowIdx(curSheetName, "*Street Number"), getColumnIdx(curSheetName, "*Street Number")+1).Value = "12"
		.Cells(getRowIdx(curSheetName, "*Street Name"), getColumnIdx(curSheetName, "*Street Name")+1).Value = "ADAMS"
		.Cells(getRowIdx(curSheetName, "Suffix/Type"), getColumnIdx(curSheetName, "Suffix/Type")+1).Value = "LN"

		
		' Ship to information
		.Cells(getRowIdx(curSheetName, "Shipping Address Same as Service Location?"), getColumnIdx(curSheetName, "Shipping Address Same as Service Location?")+1).Value = "Yes"

		' For Comcast Only section
		.Cells(getRowIdx(curSheetName, "Sales Representative PERNR:"), getColumnIdx(curSheetName, "Sales Representative PERNR:")+1).Value = .Cells(getRowIdx(curSheetName, "Sales Representative PERNR:"), getColumnIdx(curSheetName, "Sales Representative PERNR:")+1).Value + 1
		.Cells(getRowIdx(curSheetName, "Sales Manager/Director:"), getColumnIdx(curSheetName, "Sales Manager/Director:")+1).Value = "Rene Adler"
		.Cells(getRowIdx(curSheetName, "Sales Manager/Director Approval:"), getColumnIdx(curSheetName, "Sales Manager/Director Approval:")+1).Value = "Albrecht Ernst"
		.Cells(getRowIdx(curSheetName, "Market:"), getColumnIdx(curSheetName, "Market:")+1).Value = "99999 - Workbench Test"
		.Cells(getRowIdx(curSheetName, "Lead ID:"), getColumnIdx(curSheetName, "Lead ID:")+1).Value = .Cells(getRowIdx(curSheetName, "Lead ID:"), getColumnIdx(curSheetName, "Lead ID:")+1).Value + 1
		.Cells(getRowIdx(curSheetName, "Account Rep Name:"), getColumnIdx(curSheetName, "Account Rep Name:")+1).Value = "Allgower Karl"
		.Cells(getRowIdx(curSheetName, " Telephone Number:"), getColumnIdx(curSheetName, " Telephone Number:")+1).Value = "8566384009"
		.Cells(getRowIdx(curSheetName, "Email Address:"), getColumnIdx(curSheetName, "Email Address:")+1).Value = "email_"& right(getTimestamp(),8) & "@maildrop.cc"
		.Cells(getRowIdx(curSheetName, "VSS/SE Name:"), getColumnIdx(curSheetName, "VSS/SE Name:")+1).Value = "Klaus Allofs"
		.Cells(getRowIdx(curSheetName, "VSS/SE Email:"), getColumnIdx(curSheetName, "VSS/SE Email:")+1).Value = "email_"& right(getTimestamp(),8) & "@maildrop.cc"
		.Cells(getRowIdx(curSheetName, "Transport Type:"), getColumnIdx(curSheetName, "Transport Type:")+1).Value = "Coax"
		.Cells(getRowIdx(curSheetName, "Sales Channel:"), getColumnIdx(curSheetName, "Sales Channel:")+1).Value = "BSR/OBSR/BAE1"
		.Cells(getRowIdx(curSheetName, "SF Account ID:"), getColumnIdx(curSheetName, "SF Account ID:")+1).Value = .Cells(getRowIdx(curSheetName, "SF Account ID:"), getColumnIdx(curSheetName, "SF Account ID:")+1).Value + 1
		.Cells(getRowIdx(curSheetName, "SF Location ID:"), getColumnIdx(curSheetName, "SF Location ID:")+1).Value = .Cells(getRowIdx(curSheetName, "SF Location ID:"), getColumnIdx(curSheetName, "SF Location ID:")+1).Value + 1
		.Cells(getRowIdx(curSheetName, "SF Opportunity ID:"), getColumnIdx(curSheetName, "SF Opportunity ID:")+1).Value = .Cells(getRowIdx(curSheetName, "SF Opportunity ID:"), getColumnIdx(curSheetName, "SF Opportunity ID:")+1).Value + 1
		.Cells(getRowIdx(curSheetName, "Special Configuration"), getColumnIdx(curSheetName, "Special Configuration")+1).Value = "BVE Cordless Office"
		.Cells(getRowIdx(curSheetName, "Primary Site:"), getColumnIdx(curSheetName, "Primary Site:")+1).Value = "Yes"
		.Cells(getRowIdx(curSheetName, "Customer Requested Date:"), getColumnIdx(curSheetName, "Customer Requested Date:")+1).Value = FormatDateTime(Now, vbShortDate)
		
		.Cells(getRowIdx(curSheetName, "Government Program:"), getColumnIdx(curSheetName, "Government Program:")+1).Value = "None"
		.Cells(getRowIdx(curSheetName, "Enterprise Ext. Dialing Option:"), getColumnIdx(curSheetName, "Enterprise Ext. Dialing Option:")+1).Value = "6 Digit"
		
		End With
	End Function
	
	'********************************** BEGIN: BCP SOA V1.44 ********************************************************************************************************************
	Function BCPSOACommonFieldUpdate_V1_44(ByVal objWorkbook, ByVal curSheetName, ByVal testName, ByVal mailIdList)
		log("Inside BCPSOACommonFieldUpdate_V1_44 Function")
		With objWorkbook.Worksheets(curSheetName)
			timeStamp = getTimestamp()
			' Get the Old Values
			LeadId = .Cells(getRowIdx(curSheetName, "Lead ID* :"), columnIdxToUpdate).Value
			SFAccountId = .Cells(getRowIdx(curSheetName, "SF Account #* :"), columnIdxToUpdate).Value
			SFLocationId = .Cells(getRowIdx(curSheetName, "SF Location ID* :"), columnIdxToUpdate).Value
			SFOpportunityId = .Cells(getRowIdx(curSheetName, "SF Opportunity ID* :"), columnIdxToUpdate).Value
			log("Taken the old values of LeadId, SFAccount Id, SF Location ID, SF Opprtunity Id")
			' Update the new values
			.Cells(getRowIdx(curSheetName, "Billing Account Name :"), columnIdxToUpdate).Value = testName & timeStamp
			.Cells(getRowIdx(curSheetName, "Location Name* :"), columnIdxToUpdate).Value = testName & timeStamp
			.Cells(getRowIdx(curSheetName, "Company Name* :"), columnIdxToUpdate).Value = testName & timeStamp
			.Cells(getRowIdx(curSheetName, "Lead ID* :"), columnIdxToUpdate).Value = LeadId + 1
			SFAccountId = Split(SFAccountId, "-")
			newSFAccountId = "A-" & (SFAccountId(1) + 1)
			.Cells(getRowIdx(curSheetName, "SF Account #* :"), columnIdxToUpdate).Value = newSFAccountId
			.Cells(getRowIdx(curSheetName, "SF Location ID* :"), columnIdxToUpdate).Value = SFLocationId + 1
			.Cells(getRowIdx(curSheetName, "SF Opportunity ID* :"), columnIdxToUpdate).Value = SFOpportunityId + 1
			.Cells(getRowIdx(curSheetName, "Account Name:"), 02).Value = testName & getTimestamp()
			.Cells(getRowIdx(curSheetName, "Sales Rep PERNR:"), 02).value = "10247770"
			.Cells(getRowIdx(curSheetName, "Contact Name* :"), columnIdxToUpdate).Value = "FName LName" & timeStamp
			.Cells(getRowIdx(curSheetName, "Business Phone* :"), columnIdxToUpdate).Value = "98" & right(timeStamp,8)
			.Cells(getRowIdx(curSheetName, "Cell Phone :"), columnIdxToUpdate).Value =  "98" & right(timeStamp,8)
			.Cells(getRowIdx(curSheetName, "Fax Number :"), columnIdxToUpdate).Value = "98" & right(timeStamp,8)
			.Cells(getRowIdx(curSheetName, "Email* :"), columnIdxToUpdate).Value = "email_"& right(timeStamp,8) & "@maildrop.cc"
			.Cells(getRowIdx(curSheetName, "Tech Contact Name* :"), columnIdxToUpdate).Value = "FName LName" & timeStamp
			.Cells(getRowIdx(curSheetName, "Tech Contact Email Address* :"), columnIdxToUpdate).Value = "email_"& right(timeStamp,8) & "@maildrop.cc"
			On Error Resume Next
				If (UBound(mailIdList) >= 1) Then
					.Cells(getRowIdx(curSheetName, "Tech Contact Email Address* :"), columnIdxToUpdate).Value = mailIdList(0)			
				End If
			On Error Goto 0
			.Cells(getRowIdx(curSheetName, "Billing Contact Name :"), columnIdxToUpdate).Value = testName & right(timeStamp,8)
			.Cells(getRowIdx(curSheetName, "Billing Contact Phone :"), columnIdxToUpdate).Value =  "98" & right(timeStamp,8)
			.Cells(getRowIdx(curSheetName, "Billing Contact Fax :"), columnIdxToUpdate).Value =  "98" & right(timeStamp,8)
			.Cells(getRowIdx(curSheetName, "Billing Contact Email :"), columnIdxToUpdate).Value = "email_"& right(timeStamp,8) & "@maildrop.cc"                  
			.Cells(getRowIdx(curSheetName, "Primary Contact:"), 02).Value = "FName LName_" & timeStamp
			.Cells(getRowIdx(curSheetName, "Business Phone:"), 02).Value = "98" & right(timeStamp,8)
			.Cells(getRowIdx(curSheetName, "Cell Phone:"), 02).Value = "98" & right(timeStamp,8)
			.Cells(getRowIdx(curSheetName, "Fax:"), 02).Value = "98" & right(timeStamp,8)
			.Cells(getRowIdx(curSheetName, "Email:"), 02).Value = "email_"& right(timeStamp,8) & "@maildrop.cc"
			On Error Resume Next
				If (UBound(mailIdList) >= 1) Then
					.Cells(getRowIdx(curSheetName, "Email:"), 02).Value = mailIdList(0)
				End If
			On Error Goto 0
			.Cells(getRowIdx(curSheetName, "Acct Rep Email:"), 02).Value = "email_"& right(timeStamp,8) & "@maildrop.cc"
			.Cells(getRowIdx(curSheetName, "VSS/SE Email:"), 02).Value = "email_"& right(timeStamp,8) & "@maildrop.cc"
			log("Updated the common fields Inside BCPSOACommonFieldUpdate_V1_44 Function")
		End With
	End Function
	
	Function BCPSOASimonSiteUpdate_V1_44(ByVal objWorkbook, ByVal curSheetName, ByVal mailIdList)
		With objWorkbook.Worksheets(curSheetName)
			mailidIdx = 1
			For idx = 9 To 1000
				If(.Cells(idx, getColumnIdx(curSheetName, "Seat Purpose")).Value = "Primary Manager") Then
					.Cells(idx, getColumnIdx(curSheetName, "Seat Purpose")).Value = "Service User"
					.Cells(idx, getColumnIdx(curSheetName, "Seat Purpose")).Value = "Primary Manager"
					.Cells(idx, getColumnIdx(curSheetName, "TN Type")).Value = "New"
					.Cells(idx, getColumnIdx(curSheetName, "Phone Model")).Value = "No Hardware"
				ElseIf(.Cells(idx, getColumnIdx(curSheetName, "Seat Purpose")).Value <> "") Then
					.Cells(idx, getColumnIdx(curSheetName, "Email Address")).Value = mailIdList(mailidIdx)
					mailidIdx = mailidIdx + 1
				ElseIf(.Cells(idx, getColumnIdx(curSheetName, "Seat Purpose")).Value = "") Then
					Exit For
				End If
			Next
		End With
	End Function
	
	Function commonFieldUpdatesForProdSBOrder(ByVal objWorkbook, ByVal curSheetName, ByVal testName, ByVal mailIdList)
		With objWorkbook.Worksheets(curSheetName)
			log("Inside commonFieldUpdatesForProdSBOrder Function")
			' Get the Old Values
			LeadId = .Cells(getRowIdx(curSheetName, "Lead ID* :"), columnIdxToUpdate).Value
			SFAccountId = .Cells(getRowIdx(curSheetName, "SF Account #* :"), columnIdxToUpdate).Value
			SFLocationId = .Cells(getRowIdx(curSheetName, "SF Location ID* :"), columnIdxToUpdate).Value
			SFOpportunityId = .Cells(getRowIdx(curSheetName, "SF Opportunity ID* :"), columnIdxToUpdate).Value
			log("Taken the old values of LeadId, SFAccount Id, SF Location ID, SF Opprtunity Id")

			' Update the new values
			.Cells(getRowIdx(curSheetName, "Billing Account Name :"), columnIdxToUpdate).Value = testName & getDateAndTimeHourMin()
			.Cells(getRowIdx(curSheetName, "Location Name* :"), columnIdxToUpdate).Value = testName & getDateAndTimeHourMin()
			.Cells(getRowIdx(curSheetName, "Company Name* :"), columnIdxToUpdate).Value = testName & getDateAndTimeHourMin()
			.Cells(getRowIdx(curSheetName, "Lead ID* :"), columnIdxToUpdate).Value = LeadId + 1
			SFAccountId = Split(SFAccountId, "-")
			newSFAccountId = "A-" & (SFAccountId(1) + 1)
			.Cells(getRowIdx(curSheetName, "SF Account #* :"), columnIdxToUpdate).Value = newSFAccountId
			.Cells(getRowIdx(curSheetName, "SF Location ID* :"), columnIdxToUpdate).Value = SFLocationId + 1
			.Cells(getRowIdx(curSheetName, "SF Opportunity ID* :"), columnIdxToUpdate).Value = SFOpportunityId + 1
			.Cells(getRowIdx(curSheetName, "Account Name:"), 02).Value = testName & getDateAndTimeHourMin()
			.Cells(getRowIdx(curSheetName, "Sales Rep PERNR:"), 02).value = "10247770"
			.Cells(getRowIdx(curSheetName, "Email* :"), columnIdxToUpdate).Value = "prodtest" & getDateAndTimeHourMin() & "@maildrop.cc"
			.Cells(getRowIdx(curSheetName, "Email:"), 02).Value = "prodtest" & getDateAndTimeHourMin() & "@maildrop.cc"
			On Error Resume Next
				If (UBound(mailIdList) >= 1) Then
					.Cells(getRowIdx(curSheetName, "Email:"), 02).Value = mailIdList(0)
					.Cells(getRowIdx(curSheetName, "Email* :"), columnIdxToUpdate).Value = mailIdList(0)
				End If
			On Error Goto 0
		End With
	End Function
	
	Function BCPSOACommonFieldUpdateForMultiSite_V1_44(ByVal objWorkbook, ByVal curSheetName, ByVal testName, ByVal mailIdList)
		With objWorkbook.Worksheets(curSheetName)
			
			' Get the Old Values from Site1
			LeadId = .Cells(getRowIdx(curSheetName, "Lead ID* :"), site1ColumnIndex).Value
			SFAccountId = .Cells(getRowIdx(curSheetName, "SF Account #* :"), site1ColumnIndex).Value
			SFLocationId = .Cells(getRowIdx(curSheetName, "SF Location ID* :"), site1ColumnIndex).Value
			SFOpportunityId = .Cells(getRowIdx(curSheetName, "SF Opportunity ID* :"), site1ColumnIndex).Value
			timeStamp = getTimestamp()

			' Update the new values for Site1, Site2
			.Cells(getRowIdx(curSheetName, "Billing Account Name :"), site1ColumnIndex).Value = testName & timeStamp
			.Cells(getRowIdx(curSheetName, "Billing Account Name :"), site2ColumnIndex).Value = testName & timeStamp
			
			.Cells(getRowIdx(curSheetName, "Location Name* :"), site1ColumnIndex).Value = testName & "Site1_" & timeStamp
			.Cells(getRowIdx(curSheetName, "Location Name* :"), site2ColumnIndex).Value = testName & "Site2_" & timeStamp
			
			.Cells(getRowIdx(curSheetName, "Company Name* :"), site1ColumnIndex).Value = testName & timeStamp
			.Cells(getRowIdx(curSheetName, "Company Name* :"), site2ColumnIndex).Value = testName & timeStamp
			
			.Cells(getRowIdx(curSheetName, "Lead ID* :"), site1ColumnIndex).Value = LeadId + 2
			.Cells(getRowIdx(curSheetName, "Lead ID* :"), site2ColumnIndex).Value = LeadId + 2
			
			SFAccountId = Split(SFAccountId, "-")
			newSFAccountId = "A-" & (SFAccountId(1) + 2)
			.Cells(getRowIdx(curSheetName, "SF Account #* :"), site1ColumnIndex).Value = newSFAccountId
			SFAccountId = .Cells(getRowIdx(curSheetName, "SF Account #* :"), site2ColumnIndex).Value
			SFAccountId = Split(SFAccountId, "-")
			newSFAccountId = "A-" & (SFAccountId(1) + 2)
			.Cells(getRowIdx(curSheetName, "SF Account #* :"), site2ColumnIndex).Value = newSFAccountId
			
			.Cells(getRowIdx(curSheetName, "SF Location ID* :"), site1ColumnIndex).Value = SFLocationId + 2
			.Cells(getRowIdx(curSheetName, "SF Location ID* :"), site2ColumnIndex).Value = .Cells(getRowIdx(curSheetName, "SF Location ID* :"), site2ColumnIndex).Value + 2
			
			.Cells(getRowIdx(curSheetName, "SF Opportunity ID* :"), site1ColumnIndex).Value = SFOpportunityId + 2
			.Cells(getRowIdx(curSheetName, "SF Opportunity ID* :"), site2ColumnIndex).Value = .Cells(getRowIdx(curSheetName, "SF Opportunity ID* :"), site2ColumnIndex).Value + 2
			
			.Cells(getRowIdx(curSheetName, "Contact Name* :"), site1ColumnIndex).Value = "FName LNameSite1_" & timeStamp
			.Cells(getRowIdx(curSheetName, "Contact Name* :"), site2ColumnIndex).Value = "FName LNameSite2_" & timeStamp
			
			.Cells(getRowIdx(curSheetName, "Business Phone* :"), site1ColumnIndex).Value = "98" & right(timeStamp,8)
			.Cells(getRowIdx(curSheetName, "Business Phone* :"), site2ColumnIndex).Value = "98" & right(timeStamp,8)
			
			.Cells(getRowIdx(curSheetName, "Cell Phone :"), site1ColumnIndex).Value =  "98" & right(timeStamp,8)
			.Cells(getRowIdx(curSheetName, "Cell Phone :"), site2ColumnIndex).Value =  "98" & right(timeStamp,8)
			
			.Cells(getRowIdx(curSheetName, "Fax Number :"), site1ColumnIndex).Value = "98" & right(timeStamp,8)
			.Cells(getRowIdx(curSheetName, "Fax Number :"), site2ColumnIndex).Value = "98" & right(timeStamp,8)
			
			.Cells(getRowIdx(curSheetName, "Email* :"), site1ColumnIndex).Value = "email_"& right(timeStamp,8) & "@maildrop.cc"
			.Cells(getRowIdx(curSheetName, "Email* :"), site2ColumnIndex).Value = "email_"& right(timeStamp,8) & "@maildrop.cc"
			
			.Cells(getRowIdx(curSheetName, "Tech Contact Name* :"), site1ColumnIndex).Value = "FName LName" & timeStamp
			.Cells(getRowIdx(curSheetName, "Tech Contact Name* :"), site2ColumnIndex).Value = "FName LName" & timeStamp
			
			.Cells(getRowIdx(curSheetName, "Tech Contact Email Address* :"), site1ColumnIndex).Value = "email_"& right(timeStamp,8) & "@maildrop.cc"
			.Cells(getRowIdx(curSheetName, "Tech Contact Email Address* :"), site2ColumnIndex).Value = "email_"& right(timeStamp,8) & "@maildrop.cc"
			On Error Resume Next
				If (UBound(mailIdList) >= 1) Then
					.Cells(getRowIdx(curSheetName, "Tech Contact Email Address* :"), site1ColumnIndex).Value = mailIdList(0)
					.Cells(getRowIdx(curSheetName, "Tech Contact Email Address* :"), site2ColumnIndex).Value = mailIdList(0)
				End If
			On Error Goto 0
			
			.Cells(getRowIdx(curSheetName, "Billing Contact Name :"), site1ColumnIndex).Value = testName & timeStamp
			.Cells(getRowIdx(curSheetName, "Billing Contact Name :"), site2ColumnIndex).Value = testName & timeStamp
			
			.Cells(getRowIdx(curSheetName, "Billing Contact Phone :"), site1ColumnIndex).Value =  "98" & right(timeStamp,8)
			.Cells(getRowIdx(curSheetName, "Billing Contact Phone :"), site2ColumnIndex).Value =  "98" & right(timeStamp,8)
			
			.Cells(getRowIdx(curSheetName, "Billing Contact Fax :"), site1ColumnIndex).Value =  "98" & right(timeStamp,8)
			.Cells(getRowIdx(curSheetName, "Billing Contact Fax :"), site2ColumnIndex).Value =  "98" & right(timeStamp,8)
			
			.Cells(getRowIdx(curSheetName, "Billing Contact Email :"), site1ColumnIndex).Value = "email_"& right(timeStamp,8) & "@maildrop.cc"
			.Cells(getRowIdx(curSheetName, "Billing Contact Email :"), site2ColumnIndex).Value = "email_"& right(timeStamp,8) & "@maildrop.cc"
			
			.Cells(getRowIdx(curSheetName, "Primary Site* :"), site1ColumnIndex).Value = "Yes"
			.Cells(getRowIdx(curSheetName, "Primary Site* :"), site2ColumnIndex).Value = "No"
			
			
			.Cells(getRowIdx(curSheetName, "Account Name:"), 02).Value = testName & timeStamp
			.Cells(getRowIdx(curSheetName, "Sales Rep PERNR:"), 02).value = "10247770"
			.Cells(getRowIdx(curSheetName, "Primary Contact:"), 02).Value = "FName LName_" & timeStamp
			.Cells(getRowIdx(curSheetName, "Business Phone:"), 02).Value = "98" & right(timeStamp,8)
			.Cells(getRowIdx(curSheetName, "Cell Phone:"), 02).Value = "98" & right(timeStamp,8)
			.Cells(getRowIdx(curSheetName, "Fax:"), 02).Value = "98" & right(timeStamp,8)
			.Cells(getRowIdx(curSheetName, "Email:"), 02).Value = "email_"& right(timeStamp,8) & "@maildrop.cc"
			On Error Resume Next
				If (UBound(mailIdList) >= 1) Then
					.Cells(getRowIdx(curSheetName, "Email:"), 02).Value =  mailIdList(0)
				End If
			On Error Goto 0
			
			.Cells(getRowIdx(curSheetName, "Acct Rep Email:"), 02).Value = "email_"& right(timeStamp,8) & "@maildrop.cc"
			.Cells(getRowIdx(curSheetName, "VSS/SE Email:"), 02).Value = "email_"& right(timeStamp,8) & "@maildrop.cc"
		End With
	End Function
	
	   '********************************** BEGIN: BCP SOA V1.43 ********************************************************************************************************************
	 Function BCPSOAUpdate_V1_43(ByVal objWorkbook, ByVal curSheetName, ByVal testName, ByVal mailIdList)
		timeStamp = getTimestamp()
		With objWorkbook.Worksheets("Sales Order Form 1")
			.Cells(getRowIdx("Sales Order Form 1", "Account Name:"), getColumnIdx("Sales Order Form 1", "Account Name:")+1).Value = testName & timeStamp
			.Cells(getRowIdx("Sales Order Form 1", "Primary Contact:"), getColumnIdx("Sales Order Form 1", "Primary Contact:")+1).Value = "FName LName_" & timeStamp
			.Cells(getRowIdx("Sales Order Form 1", "Email:"), getColumnIdx("Sales Order Form 1", "Email:")+1).Value =  mailIdList(0)
		End With
			
			'  Customer Information
		With objWorkbook.Worksheets(curSheetName)
			 .Cells(getRowIdx(curSheetName, "Location Name:"), getColumnIdx(curSheetName, "Location Name:")+1).Value = testName & timeStamp 
			 .Cells(getRowIdx(curSheetName, "Company Name:"), getColumnIdx(curSheetName, "Company Name:")+1).Value = testName & timeStamp
			 .Cells(getRowIdx(curSheetName, "Contact Name:"), getColumnIdx(curSheetName, "Contact Name:")+1).Value = "FName LName" & timeStamp 
			 .Cells(getRowIdx(curSheetName, "Business Phone:"), getColumnIdx(curSheetName, "Business Phone:")+1).Value = "98" & right(timeStamp,8)
			 .Cells(getRowIdx(curSheetName, "Cell Phone:"), getColumnIdx(curSheetName, "Cell Phone:")+1).Value = "98" & right(timeStamp,8)
			 .Cells(getRowIdx(curSheetName, "Fax Number:"), getColumnIdx(curSheetName, "Fax Number:")+1).Value = "98" & right(timeStamp,8)
			 .Cells(getRowIdx(curSheetName, "Email:"), getColumnIdx(curSheetName, "Email:")+1).Value = mailIdList(0)
			 .Cells(getRowIdx(curSheetName, "Technical Contact Phone Number:"), getColumnIdx(curSheetName, "Technical Contact Phone Number:")+1).Value = "98" & right(timeStamp,8)
			 .Cells(getRowIdx(curSheetName, "Technical Contact Email Address:"), getColumnIdx(curSheetName, "Technical Contact Email Address:")+1).Value =  "email_"& right(timeStamp,8) & "@maildrop.cc"
			 .Cells(getRowIdx(curSheetName, "Technical Contact On-Site (Y/N)"), getColumnIdx(curSheetName, "Technical Contact On-Site (Y/N)")+1).Value = "Y"
			 .Cells(getRowIdx(curSheetName, "Billing Address Details Same as Service Location?"), getColumnIdx(curSheetName, "Billing Address Details Same as Service Location?")+1).Value = "No"
			 .Cells(getRowIdx(curSheetName, "Billing Address Details Same as Service Location?"), getColumnIdx(curSheetName, "Billing Address Details Same as Service Location?")+1).Value = "Yes"
			  
			 .Cells(getRowIdx(curSheetName, "Shipping Address Same as Service Location?"), getColumnIdx(curSheetName, "Shipping Address Same as Service Location?")+1).Value = "No"
			 .Cells(getRowIdx(curSheetName, "Shipping Address Same as Service Location?"), getColumnIdx(curSheetName, "Shipping Address Same as Service Location?")+1).Value = "Yes"
			  objWorkbook.Worksheets(curSheetName).Unprotect "adam88"
			 ' For Comcast Only section 
			 .Cells(getRowIdx(curSheetName, "Sales Representative PERNR:"), getColumnIdx(curSheetName, "Sales Representative PERNR:")+1).Value = .Cells(getRowIdx(curSheetName, "Sales Representative PERNR:"), getColumnIdx(curSheetName, "Sales Representative PERNR:")+1).Value + 1
			 .Cells(getRowIdx(curSheetName, "Lead ID:"), getColumnIdx(curSheetName, "Lead ID:")+1).Value = .Cells(getRowIdx(curSheetName, "Lead ID:"), getColumnIdx(curSheetName, "Lead ID:")+1).Value + 1
			 .Cells(getRowIdx(curSheetName, "Email Address:"), getColumnIdx(curSheetName, "Email Address:")+1).Value = "email_"& right(timeStamp,8) & "@maildrop.cc"
			 .Cells(getRowIdx(curSheetName, "VSS/SE Email:"), getColumnIdx(curSheetName, "VSS/SE Email:")+1).Value = "email_"& right(timeStamp,8) & "@maildrop.cc"
			 .Cells(getRowIdx(curSheetName, "Transport Type:"), getColumnIdx(curSheetName, "Transport Type:")+1).Value = "Coax"  
			 SFAccountId = .Cells(getRowIdx(curSheetName, "SF Account ID:"), getColumnIdx(curSheetName, "SF Account ID:")+1).Value 
			 SFAccountId = split(SFAccountId,"-")
			 newSFAccountId = "A-" & (SFAccountId(1) + 1)
			 .Cells(getRowIdx(curSheetName, "SF Account ID:"), getColumnIdx(curSheetName, "SF Account ID:")+1).Value = newSFAccountId
			 .Cells(getRowIdx(curSheetName, "SF Location ID:"), getColumnIdx(curSheetName, "SF Location ID:")+1).Value = .Cells(getRowIdx(curSheetName, "SF Location ID:"), getColumnIdx(curSheetName, "SF Location ID:")+1).Value + 1
			 .Cells(getRowIdx(curSheetName, "SF Opportunity ID:"), getColumnIdx(curSheetName, "SF Opportunity ID:")+1).Value = .Cells(getRowIdx(curSheetName, "SF Opportunity ID:"), getColumnIdx(curSheetName, "SF Opportunity ID:")+1).Value + 1 			 
			 .Cells(getRowIdx(curSheetName, "Government Program:"), getColumnIdx(curSheetName, "Government Program:")+1).Value = "None" 
			 .Cells(getRowIdx(curSheetName, "Enterprise Ext. Dialing Option:"), getColumnIdx(curSheetName, "Enterprise Ext. Dialing Option:")+1).Value = "6 Digit"
			 
			 curSheetName = "SIMON_Site1"
			 With objWorkbook.Worksheets(curSheetName)
				 mailidIdx = 1
				 For idx = 9 To 1000
					 If(.Cells(idx, getColumnIdx(curSheetName, "Seat Purpose")).Value = "Primary Manager") Then
						 .Cells(idx, getColumnIdx(curSheetName, "First Name")).Value = "FName"
						 .Cells(idx, getColumnIdx(curSheetName, "Last Name")).Value = testName & timeStamp
						 .Cells(idx, getColumnIdx(curSheetName, "Email Address")).Value = mailIdList(0)
						 .Cells(idx, getColumnIdx(curSheetName, "Seat Purpose")).Value = "Primary Manager" 
					 ElseIf(.Cells(idx, getColumnIdx(curSheetName, "Seat Purpose")).Value <> "") Then
						 .Cells(idx, getColumnIdx(curSheetName, "Email Address")).Value = mailIdList(mailidIdx)
						 mailidIdx = mailidIdx + 1 
					 ElseIf(.Cells(idx, getColumnIdx(curSheetName, "Seat Purpose")).Value = "") Then
						 Exit For
					 End If
				 Next
			 End With
		 End With
	 End Function

	