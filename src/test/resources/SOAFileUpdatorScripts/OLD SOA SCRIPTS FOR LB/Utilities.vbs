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
	
	Function commonFieldUpdates(ByVal objWorkbook, ByVal curSheetName, ByVal testName)
		With objWorkbook.Worksheets(curSheetName)
			
			' Get Current timetamp with yyyymmddhhmmss foramt
			curDate = now
			timestamp = year(curdate) & month(curdate)& day(curdate)& Hour(curdate)& Minute(curdate)& Second(curdate)
			
			' Get the Old Values
			LeadId = .Cells(getRowIdx(curSheetName, "Lead ID* :"), columnIdxToUpdate).Value
			SFAccountId = .Cells(getRowIdx(curSheetName, "SF Account ID* :"), columnIdxToUpdate).Value
			SFLocationId = .Cells(getRowIdx(curSheetName, "SF Location ID* :"), columnIdxToUpdate).Value
			SFOpportunityId = .Cells(getRowIdx(curSheetName, "SF Opportunity ID* :"), columnIdxToUpdate).Value
			SalesRepPERNR = .Cells(getRowIdx(curSheetName, "Sales Rep PERNR:"), 02).Value

			' Update the new values
			.Cells(getRowIdx(curSheetName, "Billing Account Name :"), columnIdxToUpdate).Value = testName & timestamp
			.Cells(getRowIdx(curSheetName, "Location Name* :"), columnIdxToUpdate).Value = testName & timestamp
			.Cells(getRowIdx(curSheetName, "Company Name* :"), columnIdxToUpdate).Value = testName & timestamp
			.Cells(getRowIdx(curSheetName, "Lead ID* :"), columnIdxToUpdate).Value = LeadId + 1
			.Cells(getRowIdx(curSheetName, "SF Account ID* :"), columnIdxToUpdate).Value = "SS" & Right(SFAccountId, 6) + 1 
			.Cells(getRowIdx(curSheetName, "SF Location ID* :"), columnIdxToUpdate).Value = SFLocationId + 1
			.Cells(getRowIdx(curSheetName, "SF Opportunity ID* :"), columnIdxToUpdate).Value = SFOpportunityId + 1
			.Cells(getRowIdx(curSheetName, "Account Name:"), 02).Value = testName & timestamp
			.Cells(getRowIdx(curSheetName, "Sales Rep PERNR:"), 02).value = SalesRepPERNR + 1
			.Cells(getRowIdx(curSheetName, "Contact Name* :"), columnIdxToUpdate).Value = "FName LName" & timestamp
			.Cells(getRowIdx(curSheetName, "Business Phone* :"), columnIdxToUpdate).Value = "98" & right(timestamp,8)
			.Cells(getRowIdx(curSheetName, "Cell Phone :"), columnIdxToUpdate).Value =  "98" & right(timestamp,8)
			.Cells(getRowIdx(curSheetName, "Fax Number :"), columnIdxToUpdate).Value = "98" & right(timestamp,8)
			.Cells(getRowIdx(curSheetName, "Email* :"), columnIdxToUpdate).Value = "email_"& right(timestamp,8) & "@yopmail.com"
			.Cells(getRowIdx(curSheetName, "Tech Contact Email Address* :"), columnIdxToUpdate).Value = "email_"& right(timestamp,8) & "@yopmail.com"
			.Cells(getRowIdx(curSheetName, "Billing Contact Name :"), columnIdxToUpdate).Value = testName & right(timestamp,8)
			.Cells(getRowIdx(curSheetName, "Billing Contact Phone :"), columnIdxToUpdate).Value =  "98" & right(timestamp,8)
			.Cells(getRowIdx(curSheetName, "Billing Contact Fax :"), columnIdxToUpdate).Value =  "98" & right(timestamp,8)
			.Cells(getRowIdx(curSheetName, "Billing Contact Email :"), columnIdxToUpdate).Value = "email_"& right(timestamp,8) & "@yopmail.com"                  
			.Cells(getRowIdx(curSheetName, "Primary Contact:"), 02).Value = testName & timestamp
			.Cells(getRowIdx(curSheetName, "Business Phone:"), 02).Value = "98" & right(timestamp,8)
			.Cells(getRowIdx(curSheetName, "Cell Phone:"), 02).Value = "98" & right(timestamp,8)
			.Cells(getRowIdx(curSheetName, "Fax:"), 02).Value = "98" & right(timestamp,8)
			.Cells(getRowIdx(curSheetName, "Email:"), 02).Value = "email_"& right(timestamp,8) & "@yopmail.com"
			.Cells(getRowIdx(curSheetName, "Acct Rep Email:"), 02).Value = "email_"& right(timestamp,8) & "@yopmail.com"
			.Cells(getRowIdx(curSheetName, "VSS/SE Email:"), 02).Value = "email_"& right(timestamp,8) & "@yopmail.com"
		End With
	End Function
	
	Function multiSiteOrderCommonFieldsUpdate(ByVal objWorkbook, ByVal curSheetName, ByVal testName)
		With objWorkbook.Worksheets(curSheetName)
			
			' Get Current timetamp with yyyymmddhhmmss foramt
			curDate = now
			timestamp = year(curdate) & month(curdate)& day(curdate)& Hour(curdate)& Minute(curdate)& Second(curdate)
			
			' Get the Old Values from Site1
			LeadId = .Cells(getRowIdx(curSheetName, "Lead ID* :"), site1ColumnIndex).Value
			SFAccountId = .Cells(getRowIdx(curSheetName, "SF Account ID* :"), site1ColumnIndex).Value
			SFLocationId = .Cells(getRowIdx(curSheetName, "SF Location ID* :"), site1ColumnIndex).Value
			SFOpportunityId = .Cells(getRowIdx(curSheetName, "SF Opportunity ID* :"), site1ColumnIndex).Value
			SalesRepPERNR = .Cells(getRowIdx(curSheetName, "Sales Rep PERNR:"), 02).Value

			' Update the new values for Site1, Site2
			.Cells(getRowIdx(curSheetName, "Billing Account Name :"), site1ColumnIndex).Value = testName & timestamp
			.Cells(getRowIdx(curSheetName, "Billing Account Name :"), site2ColumnIndex).Value = testName & timestamp
			
			.Cells(getRowIdx(curSheetName, "Location Name* :"), site1ColumnIndex).Value = testName & "Site1_" & timestamp
			.Cells(getRowIdx(curSheetName, "Location Name* :"), site2ColumnIndex).Value = testName & "Site2_" & timestamp
			
			.Cells(getRowIdx(curSheetName, "Company Name* :"), site1ColumnIndex).Value = testName & timestamp
			.Cells(getRowIdx(curSheetName, "Company Name* :"), site2ColumnIndex).Value = testName & timestamp
			
			.Cells(getRowIdx(curSheetName, "Lead ID* :"), site1ColumnIndex).Value = LeadId + 2
			.Cells(getRowIdx(curSheetName, "Lead ID* :"), site2ColumnIndex).Value = LeadId + 2
			
			.Cells(getRowIdx(curSheetName, "SF Account ID* :"), site1ColumnIndex).Value = "SS" & Right(SFAccountId, 6) + 2
			.Cells(getRowIdx(curSheetName, "SF Account ID* :"), site2ColumnIndex).Value = "SS" & Right(SFAccountId, 6) + 2
			
			.Cells(getRowIdx(curSheetName, "SF Location ID* :"), site1ColumnIndex).Value = SFLocationId + 2
			.Cells(getRowIdx(curSheetName, "SF Location ID* :"), site2ColumnIndex).Value = .Cells(getRowIdx(curSheetName, "SF Location ID* :"), site2ColumnIndex).Value + 2
			
			.Cells(getRowIdx(curSheetName, "SF Opportunity ID* :"), site1ColumnIndex).Value = SFOpportunityId + 2
			.Cells(getRowIdx(curSheetName, "SF Opportunity ID* :"), site2ColumnIndex).Value = .Cells(getRowIdx(curSheetName, "SF Opportunity ID* :"), site2ColumnIndex).Value + 2
			
			.Cells(getRowIdx(curSheetName, "Contact Name* :"), site1ColumnIndex).Value = "FName LNameSite1_" & timestamp
			.Cells(getRowIdx(curSheetName, "Contact Name* :"), site2ColumnIndex).Value = "FName LNameSite2_" & timestamp
			
			.Cells(getRowIdx(curSheetName, "Business Phone* :"), site1ColumnIndex).Value = "98" & right(timestamp,8)
			.Cells(getRowIdx(curSheetName, "Business Phone* :"), site2ColumnIndex).Value = "98" & right(timestamp,8)
			
			.Cells(getRowIdx(curSheetName, "Cell Phone :"), site1ColumnIndex).Value =  "98" & right(timestamp,8)
			.Cells(getRowIdx(curSheetName, "Cell Phone :"), site2ColumnIndex).Value =  "98" & right(timestamp,8)
			
			.Cells(getRowIdx(curSheetName, "Fax Number :"), site1ColumnIndex).Value = "98" & right(timestamp,8)
			.Cells(getRowIdx(curSheetName, "Fax Number :"), site2ColumnIndex).Value = "98" & right(timestamp,8)
			
			.Cells(getRowIdx(curSheetName, "Email* :"), site1ColumnIndex).Value = "email_"& right(timestamp,8) & "@yopmail.com"
			.Cells(getRowIdx(curSheetName, "Email* :"), site2ColumnIndex).Value = "email_"& right(timestamp,8) & "@yopmail.com"
			
			.Cells(getRowIdx(curSheetName, "Tech Contact Email Address* :"), site1ColumnIndex).Value = "email_"& right(timestamp,8) & "@yopmail.com"
			.Cells(getRowIdx(curSheetName, "Tech Contact Email Address* :"), site2ColumnIndex).Value = "email_"& right(timestamp,8) & "@yopmail.com"
			
			.Cells(getRowIdx(curSheetName, "Billing Contact Name :"), site1ColumnIndex).Value = testName & right(timestamp,8)
			.Cells(getRowIdx(curSheetName, "Billing Contact Name :"), site2ColumnIndex).Value = testName & right(timestamp,8)
			
			.Cells(getRowIdx(curSheetName, "Billing Contact Phone :"), site1ColumnIndex).Value =  "98" & right(timestamp,8)
			.Cells(getRowIdx(curSheetName, "Billing Contact Phone :"), site2ColumnIndex).Value =  "98" & right(timestamp,8)
			
			.Cells(getRowIdx(curSheetName, "Billing Contact Fax :"), site1ColumnIndex).Value =  "98" & right(timestamp,8)
			.Cells(getRowIdx(curSheetName, "Billing Contact Fax :"), site2ColumnIndex).Value =  "98" & right(timestamp,8)
			
			.Cells(getRowIdx(curSheetName, "Billing Contact Email :"), site1ColumnIndex).Value = "email_"& right(timestamp,8) & "@yopmail.com"
			.Cells(getRowIdx(curSheetName, "Billing Contact Email :"), site2ColumnIndex).Value = "email_"& right(timestamp,8) & "@yopmail.com"
			
			.Cells(getRowIdx(curSheetName, "Primary Site* :"), site1ColumnIndex).Value = "Yes"
			.Cells(getRowIdx(curSheetName, "Primary Site* :"), site2ColumnIndex).Value = "No"
			
			
			.Cells(getRowIdx(curSheetName, "Account Name:"), 02).Value = testName & timestamp
			.Cells(getRowIdx(curSheetName, "Sales Rep PERNR:"), 02).value = SalesRepPERNR + 2                  
			.Cells(getRowIdx(curSheetName, "Primary Contact:"), 02).Value = testName & timestamp
			.Cells(getRowIdx(curSheetName, "Business Phone:"), 02).Value = "98" & right(timestamp,8)
			.Cells(getRowIdx(curSheetName, "Cell Phone:"), 02).Value = "98" & right(timestamp,8)
			.Cells(getRowIdx(curSheetName, "Fax:"), 02).Value = "98" & right(timestamp,8)
			.Cells(getRowIdx(curSheetName, "Email:"), 02).Value = "email_"& right(timestamp,8) & "@yopmail.com"
			.Cells(getRowIdx(curSheetName, "Acct Rep Email:"), 02).Value = "email_"& right(timestamp,8) & "@yopmail.com"
			.Cells(getRowIdx(curSheetName, "VSS/SE Email:"), 02).Value = "email_"& right(timestamp,8) & "@yopmail.com"
		End With
	End Function