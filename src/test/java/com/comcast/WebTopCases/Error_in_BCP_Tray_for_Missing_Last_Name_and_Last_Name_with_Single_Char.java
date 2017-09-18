package com.comcast.WebTopCases;

import org.testng.annotations.Test;


import com.comcast.pages.webtop.ValidationErrorsFoundonSOAOrder;
import com.comcast.pages.webtop.WebTopLogInPage;
import com.comcast.pages.webtop.WebTopMainPage;
import com.comcast.reporting.Status;

public class Error_in_BCP_Tray_for_Missing_Last_Name_and_Last_Name_with_Single_Char extends MACDFlow{
	
	@Test(priority = 2250, groups={"New Connect", "All"})
	public void validationErrorsFoundonSOAOrder() throws Exception {
		
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		ValidationErrorsFoundonSOAOrder validationErrorsFoundonSOAOrder = new ValidationErrorsFoundonSOAOrder(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				if (dataDump.getValue("GroupID").isEmpty()) {
					webTopMainPage.searchFor("Salesforce Opportunity ID(FID)");
				} else {
					webTopMainPage.searchFor("Group ID");
				}
			}
			validationErrorsFoundonSOAOrder.validationErrorsFoundonSOAOrder();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Retry Validating SOA Order Main Contact Last Name with 2 chars", "Retry Validating SOA Order Main Contact Last Name with 2 char Failed!!!", Status.FAIL);
			String eMsg = "Error in Retry Validating SOA Order Main Contact Last Name with 2 chars --- " + Ex.getMessage();
			log.error(eMsg);
			
		}
	}
	

	@Test(priority = 12600, groups={"New Connect", "All"})
	public void updateMainConatctFNameOnly() throws Exception {
		
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		ValidationErrorsFoundonSOAOrder validationErrorsFoundonSOAOrder = new ValidationErrorsFoundonSOAOrder(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();				
			}
			new WebTopMainPage(frameworkContext).searchOrder("Group ID");
			validationErrorsFoundonSOAOrder.updateMainConatctFNameOnly();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Update Main Contact with First Name Only",
					"Update Main Contact with First Name Only Failed!!!", Status.FAIL);
			String eMsg = "Error in Updating Main Contact with First Name Only --- "
					+ Ex.getMessage();
			log.error(eMsg);
			
		}
	}
	
	@Test(priority = 13100, groups={"New Connect", "All"})
	public void validationErrorsFoundonSOAOrder_SDWFlow() throws Exception {
		
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		ValidationErrorsFoundonSOAOrder validationErrorsFoundonSOAOrder = new ValidationErrorsFoundonSOAOrder(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				if (dataDump.getValue("GroupID").isEmpty()) {
					webTopMainPage.searchFor("Salesforce Opportunity ID(FID)");
				} else {
					webTopMainPage.searchFor("Group ID");
				}
			}
			validationErrorsFoundonSOAOrder.validationErrorsFoundonSOAOrder();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Retry Validating SOA Order Main Contact Last Name with 2 chars", "Retry Validating SOA Order Main Contact Last Name with 2 char Failed!!!", Status.FAIL);
			String eMsg = "Error in Retry Validating SOA Order Main Contact Last Name with 2 chars --- " + Ex.getMessage();
			log.error(eMsg);
			
		}
	}
	
}
