package com.comcast.WebTopCases;

import org.testng.annotations.Test;

import com.comcast.pages.simon.SIMONMainPage;
import com.comcast.pages.simon.SimonLogInPage;
import com.comcast.pages.simon.TNManagement;

public class BVE_NC_TW_SITE_ALL_TN_WITH_MULTI_NATIVE_AND_PORTED_FROM_DIFFERENT_CRCP_ST_TNS extends MACDFlow{
	
	@Test(priority = 9600, groups={"New Connect", "All"})
	public void TNPortMigration() throws Exception {		
		try {
			SIMONMainPage simonMainPage = new SIMONMainPage(frameworkContext);
			TNManagement ObjTNManagement = new TNManagement(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();				
			}
			simonMainPage.SFOpportunityIDSearch();			
			ObjTNManagement.portMigration();
		} catch (Exception Ex) {
			report.reportHardFailEvent("TN Ported Information", "TN Ported Information Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "TN Ported Information in SIMON Failed!!! " + Ex.getMessage();			
			log.error(eMsg);
		}
	}
	
	
	
	@Test(priority = 9700, groups={"New Connect", "All"})
	public void updateCRCPState() throws Exception {		
		try {
			SIMONMainPage simonMainPage = new SIMONMainPage(frameworkContext);
			TNManagement ObjTNManagement = new TNManagement(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();				
			}
			simonMainPage.SFOpportunityIDSearch();			
			ObjTNManagement.updateCRCPState();
		} catch (Exception Ex) {
			report.reportHardFailEvent("TN CRCP State Information", "TN CRCP State Information Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "TN CRCP State Information in SIMON Failed!!! " + Ex.getMessage();			
			log.error(eMsg);
		}
	}

}
