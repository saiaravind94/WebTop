package com.comcast.WebTopCases;

import org.testng.annotations.Test;

import com.comcast.pages.broadsoft.BroadsoftGroupPage;
import com.comcast.pages.broadsoft.BroadsoftLogInPage;
import com.comcast.pages.webtop.AccountProfileTab;
import com.comcast.pages.webtop.ErrorDeterminingOrderType;
import com.comcast.pages.webtop.ErrorValidatingOrderBeforeSendingToSNAP;
import com.comcast.pages.webtop.ServicesPage;
import com.comcast.pages.webtop.WebTopLogInPage;
import com.comcast.pages.webtop.WebTopMainPage;
import com.comcast.reporting.Status;

public class LBP_MACD_add_AALine_on_Existing_UnUsed_AA
		extends BVE_NC_Std_site_UCseat_Loudringer_with_horn_3_strobe_lights_3_strobe_clears_Extension {

	@Test(priority = 23000, groups={"MACD", "All"})
	public void verifyGroupServicesBeforeMACD() throws Exception {
		
		BroadsoftGroupPage broadsoftGroupPage = new BroadsoftGroupPage(frameworkContext);
		try {
			if (!BroadsoftLogInPage.broadsoftLogedIn) {
				broadsoftLogin();
			}
			broadsoftGroupPage.searchGroups("Group ID", "Equal To", dataDump.getValue("GroupID"));
			broadsoftGroupPage.verifyGroupServices("Simultaneous Ring Personal", 1, 1, 1);
			broadsoftGroupPage.verifyGroupServices("Auto Attendant - Basic", 5, 5, 5);
		} catch (Exception Ex) {
			report.reportHardFailEvent("Broadsoft AA verification", "Broadsoft AA failed", Status.FAIL);
			String eMsg = "Error in Broadsoft AA verification failed" + Ex.getMessage();
			log.error(eMsg);
			
		}
	}
	@Test(priority = 94700, groups={"MACD", "All"})
	public void verifyGroupServicesAfterMACD() throws Exception {
		BroadsoftGroupPage broadsoftGroupPage = new BroadsoftGroupPage(frameworkContext);
		try {
			if (!BroadsoftLogInPage.broadsoftLogedIn) {
				broadsoftLogin();
			}
			broadsoftGroupPage.searchGroups("Group ID", "Equal To", dataDump.getValue("GroupID"));
			broadsoftGroupPage.verifyGroupServices("Simultaneous Ring Personal", 1, 1, 1);
			broadsoftGroupPage.verifyGroupServices("Auto Attendant - Basic", 6, 6, 6);
		} catch (Exception Ex) {
			report.reportHardFailEvent("Broadsoft AA verification", "Broadsoft AA failed", Status.FAIL);
			String eMsg = "Error in Broadsoft AA verification failed" + Ex.getMessage();
			log.error(eMsg);
			
		}
	}
	@Test(priority = 70000, groups={"MACD", "All"})
	public void getVoipListBeforePlacingMACD() throws Exception {
		
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		AccountProfileTab accountProfileTab = new AccountProfileTab(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
			}
			webTopMainPage.searchOrder("Group ID");
			accountProfileTab.getVoipList("before");
		} catch (Exception Ex) {
			report.reportHardFailEvent("Get VOIP Numbers", "Getting VOIP Numbers from AA is Failed!!!", Status.FAIL);
			String eMsg = "Error in Getting VOIP Numbers --- " + Ex.getMessage();
			log.error(eMsg);
			
		}
	}

	@Test(priority = 70500, groups={"MACD", "All"})
	public void addVoIpAnnounceAndAA() throws Exception {
		
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		AccountProfileTab accountProfileTab = new AccountProfileTab(frameworkContext);
		ServicesPage servicesPage = new ServicesPage(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
			}
			webTopMainPage.searchOrder("Group ID");
			accountProfileTab.launchAddInventory();
			servicesPage.addServices("AddAALineOnExistingAA", "VoIP");
			servicesPage.addANNOUNCE();
			servicesPage.addItemOrBundle("Item", "VIP", "ANNOUNCE-ANNOUNCE | 0.00", 1);
			servicesPage.addItemOrBundle("Item", "VIP", "BVEAATN-Auto Attendant | 0.00", 1);
			accountProfileTab.submitTheOrder();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Add VoIp Announce on existing VoIp",
					"Add VoIp Announce on existing VoIp is Failed!!!", Status.FAIL);
			String eMsg = "Error in Add VoIp Announce on existing VoIp --- " + Ex.getMessage();
			log.error(eMsg);
			
		}
	}	

	@Test(priority = 94450, groups={"MACD", "All"})
	public void errorValidatingOrderBeforeSendingToSNAP() throws Exception {
		
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		ErrorValidatingOrderBeforeSendingToSNAP errorValidatingOrderBeforeSendingToSNAP = new ErrorValidatingOrderBeforeSendingToSNAP(
				frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}
			
			errorValidatingOrderBeforeSendingToSNAP.errorValidatingOrderBeforeSendingToSNAP();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Retry validating order before sending to SNAP",
					"Retry validating order before sending to SNAP Failed!!!", Status.FAIL);
			String eMsg = "Error in Retry validating order before sending to SNAP --- " + Ex.getMessage();
			log.error(eMsg);
			
		}
	}

	@Test(priority = 100000, groups={"MACD", "All"})
	public void getVoipListAfterPlacingMACD() throws Exception {
		
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		AccountProfileTab accountProfileTab = new AccountProfileTab(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
			}
			webTopMainPage.searchOrder("Group ID");
			accountProfileTab.getVoipList("after");
		} catch (Exception Ex) {
			report.reportHardFailEvent("Get VOIP Numbers", "Getting VOIP Numbers from AA is Failed!!!", Status.FAIL);
			String eMsg = "Error in Getting VOIP Numbers --- " + Ex.getMessage();
			log.error(eMsg);
			
		}
	}

	@Test(priority = 100500, groups={"MACD", "All"})
	public void verifyNewlyAddedVoiPonExistingUnusedAA() throws Exception {
		AccountProfileTab accountProfileTab = new AccountProfileTab(frameworkContext);
		accountProfileTab.verifyNewlyAddedVoiPonExistingUnusedAA();
	}

}
