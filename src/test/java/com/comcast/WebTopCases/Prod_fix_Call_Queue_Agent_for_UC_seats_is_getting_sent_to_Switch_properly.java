package com.comcast.WebTopCases;

import org.testng.annotations.Test;

import com.comcast.pages.broadsoft.BroadsoftGroupPage;
import com.comcast.pages.broadsoft.BroadsoftLogInPage;
import com.comcast.pages.webtop.AccountProfileTab;
import com.comcast.pages.webtop.WebTopLogInPage;
import com.comcast.pages.webtop.WebTopMainPage;
import com.comcast.reporting.Status;

public class Prod_fix_Call_Queue_Agent_for_UC_seats_is_getting_sent_to_Switch_properly
		extends CommonE2EFlow {
	
	@Test(priority = 70000, groups={"New Connect", "All"})
	public void verifyCQBAgentBundleInAccountProfile() throws Exception {		
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		AccountProfileTab accountProfileTab = new AccountProfileTab(frameworkContext);		
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
			}
			webTopMainPage.searchOrder("Group ID");
			accountProfileTab.verifyCallQueueAgentBasicBundle();			
		} catch (Exception Ex) {
			report.reportHardFailEvent("Bundle verification in Account profile page for CQB Agent",
					"Bundle verification in Account profile page for CQB Agent Failed");
			String eMsg = "Error in Bundle verification in Account profile page for CQB Agent --- " + Ex.getMessage();
			log.error(eMsg);					
		}
	}
	
	@Test(priority = 70500, groups={"New Connect", "All"})
	public void verifyGroupServicesForCQB() throws Exception {		
		BroadsoftGroupPage broadsoftGroupPage = new BroadsoftGroupPage(frameworkContext);
		try {
			BroadsoftLogInPage.broadsoftLogedIn =false;
			if (!BroadsoftLogInPage.broadsoftLogedIn) {
				broadsoftLogin();				
			}
			broadsoftGroupPage.searchGroups("Group ID", "Equal To", dataDump.getValue("GroupID"));
			broadsoftGroupPage.verifyGroupServices("Call Center - Basic", 1, 1, 1);
			log.info("abc");
		} catch (Exception Ex) {
			report.reportHardFailEvent("Broadsoft Call Center - Basic verification", "Broadsoft Call Center - Basic failed",
					Status.FAIL);
			String eMsg = "Error in Broadsoft Call Center - Basic verification failed" + Ex.getMessage();
			log.error(eMsg);
			
		}
	}	
}
