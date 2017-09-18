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

public class LBP_MACD_CHANGE_UPGRADE_STD_SEAT_TO_REGULAR_UC_SEAT_validate_line_portID_added_BLF_feature_for_UCseat_Users_in_Switch
		extends LBP_MACD_CHANGE_STD_SEAT_TO_UC_SEAT {

	@Test(priority = 70520, groups={"MACD", "All"})
	public void verifyClientApplicationLinkBeforeMACD() throws Exception {
		
		BroadsoftGroupPage broadsoftGroupPage = new BroadsoftGroupPage(frameworkContext);
		try {
			if (!BroadsoftLogInPage.broadsoftLogedIn) {
				broadsoftLogin();
			}
			broadsoftGroupPage.searchGroups("Group ID", "Equal To", dataDump.getValue("GroupID"));
			broadsoftGroupPage.verifyGroupServices("BVEStandardSeatv1.0", 2, 2, 2);
			broadsoftGroupPage.verifyGroupServices("BVEUnifiedSeatw/vmail2.1", 2, 2, 2);
			Boolean lnkFound = broadsoftGroupPage.verifyClientAppicationsLinkForTN(dataDump.getValue("GroupID"),dataDump.getValue("ANINumberToConvert_RT"));
			if(!lnkFound)
			{
				report.reportPassEvent("Client Application Link verification", "Client Application Link is not displyed before MACD as expected", Status.PASS);
			}
			else
			{
				report.reportHardFailEvent("Client Application Link verification", "Client Application Link is displyed before MACD which is not expected", Status.FAIL);
			}
			
		} catch (Exception Ex) {
			report.reportHardFailEvent("Broadsoft Client Application Link verification",
					"Broadsoft Client Application Link verification for TN is failed", Status.FAIL);
			String eMsg = "Error in Broadsoft Client Application Link verification for TN is failed" + Ex.getMessage();
			log.error(eMsg);
			
		}
	}	

	@Test(priority = 98000, groups={"MACD", "All"})
	public void verifyClientApplicationLinkAfterMACD() throws Exception {
		
		BroadsoftGroupPage broadsoftGroupPage = new BroadsoftGroupPage(frameworkContext);
		try {
			if (!BroadsoftLogInPage.broadsoftLogedIn) {
				broadsoftLogin();
			}
			broadsoftGroupPage.searchGroups("Group ID", "Equal To", dataDump.getValue("GroupID"));
			broadsoftGroupPage.verifyGroupServices("BVEStandardSeatv1.0", 1, 1, 1);
			broadsoftGroupPage.verifyGroupServices("BVEUnifiedSeatw/vmail2.1", 3, 3, 3);
			Boolean lnkFound = broadsoftGroupPage.verifyClientAppicationsLinkForTN(dataDump.getValue("GroupID"),dataDump.getValue("ANINumberToConvert_RT"));
			if(lnkFound)
			{
				report.reportPassEvent("Client Application Link verification", "Client Application Link is displyed after MACD as expected", Status.PASS);
			}
			else
			{
				report.reportHardFailEvent("Client Application Link verification", "Client Application Link is not displyed after MACD which is not expected", Status.FAIL);
			}
			
		} catch (Exception Ex) {
			report.reportHardFailEvent("Broadsoft Client Application Link verification",
					"Broadsoft Client Application Link verification for TN is failed", Status.FAIL);
			String eMsg = "Error in Broadsoft Client Application Link verification for TN is failed" + Ex.getMessage();
			log.error(eMsg);
			
		}
	}
	
	
}
