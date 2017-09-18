package com.comcast.WebTopCases;

import org.testng.annotations.Test;

import com.comcast.pages.simon.SIMONMainPage;
import com.comcast.pages.simon.ServiceInformation;
import com.comcast.pages.simon.SimonLogInPage;
import com.comcast.pages.webtop.AccountProfileTab;
import com.comcast.pages.webtop.WaitingForSNAPFileUploadStatusNotification;
import com.comcast.pages.webtop.WebTopLogInPage;
import com.comcast.pages.webtop.WebTopMainPage;

public class AutoAttendentVerifications extends CommonE2EFlow {
	
	@Test(priority = 14000, groups = { "New Connect", "All" })
	public void verifyVOIPEntensionAAMustBe4() throws Exception {
		try {
			WebTopLogInPage WebTopLogInPage = new WebTopLogInPage(frameworkContext);
			WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
			AccountProfileTab accountProfileTab=new AccountProfileTab(frameworkContext);
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();				
			}
			webTopMainPage.searchOrder("Group ID");
			accountProfileTab.verifyUnUsedAAVOIPCount(4);
		} catch (Exception Ex) {
			log.error("Verification of Number of VOIP Extensions with Unused AA in account profile Tab page Failed");
			report.reportHardFailEvent("Verification Number of VOIP Extensions with Unused AA in account profile Tab page",
					"Number of VOIP Extensions with Unused AA in account profile Tab is not displayed as expected");			
		}
	}
	
	@Test(priority = 14000, groups = { "New Connect", "All" })
	public void verifyVOIPEntensionAAMustBe2() throws Exception {
		try {
			WebTopLogInPage WebTopLogInPage = new WebTopLogInPage(frameworkContext);
			WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
			AccountProfileTab accountProfileTab=new AccountProfileTab(frameworkContext);
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();				
			}
			webTopMainPage.searchOrder("Group ID");
			accountProfileTab.verifyUnUsedAAVOIPCount(2);
		} catch (Exception Ex) {
			log.error("Verification of Number of VOIP Extensions with Unused AA in account profile Tab page Failed");
			report.reportHardFailEvent("Verification Number of VOIP Extensions with Unused AA in account profile Tab page",
					"Number of VOIP Extensions with Unused AA in account profile Tab is not displayed as expected");			
		}
	}
	
	@Test(priority = 14500, groups = { "New Connect", "All" })
	public void verifyVOIPEntensionLineItem() throws Exception {
		try {
			WebTopLogInPage WebTopLogInPage = new WebTopLogInPage(frameworkContext);
			WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
			AccountProfileTab accountProfileTab=new AccountProfileTab(frameworkContext);
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();				
			}
			webTopMainPage.searchOrder("Group ID");
			accountProfileTab.validateLineItemCount("Bria Soft Phone" , 2);
		} catch (Exception Ex) {
			log.error("Verification of ExtensionOnly UC seat is not added with Bria softphone in account profile Tab page Failed");
			report.reportHardFailEvent("Verification of Number of Bria softphone to the VOIP Extensions with account profile Tab page",
					"Number of Bria softphone to the VOIP Extensions with account profile Tab is NOT displayed as expected");			
		}
	}
	
}


