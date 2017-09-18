package com.comcast.WebTopCases;

import org.testng.annotations.Test;

import com.comcast.pages.simon.SIMONMainPage;
import com.comcast.pages.simon.ServiceInformation;
import com.comcast.pages.simon.SimonLogInPage;
import com.comcast.pages.webtop.AccountProfileTab;
import com.comcast.pages.webtop.WaitingForSNAPFileUploadStatusNotification;
import com.comcast.pages.webtop.WebTopLogInPage;
import com.comcast.pages.webtop.WebTopMainPage;

public class US1082604_Few_unused_AA_and_few_active_AA extends CommonE2EFlow {
	
	@Test(priority = 15510, groups = { "New Connect", "All" })
	public void few_unused_AA_and_few_active_AA() throws Exception {

		try {
			WebTopLogInPage WebTopLogInPage = new WebTopLogInPage(frameworkContext);
			WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
			WaitingForSNAPFileUploadStatusNotification waitingForSNAPFileUploadStatusNotification=new WaitingForSNAPFileUploadStatusNotification(frameworkContext);
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}
			waitingForSNAPFileUploadStatusNotification.verifyWaitingForSNAPFileUploadStatusNotification();	
		} catch (Exception Ex) {
			report.reportHardFailEvent("Tray Verification for SYSTEM: Waiting for SNAP File Upload Status Notification (will auto close)" + Ex.getMessage(), eMsg);
			eMsg = report.getMessage() + "Tray Verification for SYSTEM: Waiting for SNAP File Upload Status Notification (will auto close) failed !!! " + Ex.getMessage();
			log.error(eMsg);			
		}
	}
	
	
}


