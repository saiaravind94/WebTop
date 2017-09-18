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

public class LBP_MACD_CHANGE_STD_SEAT_TO_UC_SEAT
		extends MACDFlow {
	
	@Test(priority = 70500, groups={"MACD", "All"})
	public void migratefromStdSeatToUCSeat() throws Exception {
		
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		AccountProfileTab accountProfileTab = new AccountProfileTab(frameworkContext);
		ServicesPage servicesPage = new ServicesPage(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
			}
			webTopMainPage.searchOrder("Group ID");
			accountProfileTab.getANIList("before");
			String BTNNo = accountProfileTab.getBTNNo();
			//Get the ANI number and index of the ANI which is not linked to BTN ANI
			int stdSeatIdxToConvert = 0;
			for(int idx = 0; idx<dataDump.getValue("StdANIList_before").split("\\|").length;idx++)
			{
				if(!BTNNo.equalsIgnoreCase(dataDump.getValue("StdANIList_before").split("\\|")[idx])){
					dataDump.setValue("ANINumberToConvert_RT", dataDump.getValue("StdANIList_before").split("\\|")[idx]);
					stdSeatIdxToConvert = idx;
					break;
				}				
			}
			accountProfileTab.addSeat("standard", stdSeatIdxToConvert + 1);			
			servicesPage.addServices("AddUCSeatOnExistingStandardSeat", "");
			servicesPage.addItemOrBundle("Item", "ANI", "BVEUCS-Unified Communications Seat | 0.00", 1);			
			accountProfileTab.submitTheOrder();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Add VoIp Announce on existing VoIp",
					"Add VoIp Announce on existing VoIp is Failed!!!", Status.FAIL);
			String eMsg = "Error in Add VoIp Announce on existing VoIp --- " + Ex.getMessage();
			log.error(eMsg);
			
		}
	}
	
	@Test(priority = 70510, groups={"MACD", "All"})
	public void broadsoftLoginBeforeMACD() throws Exception {
		broadsoftLogin();
	}
	

	@Test(priority = 70520, groups={"MACD", "All"})
	public void verifyGroupServicesBeforeMACD() throws Exception {
		
		BroadsoftGroupPage broadsoftGroupPage = new BroadsoftGroupPage(frameworkContext);
		try {
			if (!BroadsoftLogInPage.broadsoftLogedIn) {
				broadsoftLogin();
			}
			broadsoftGroupPage.searchGroups("Group ID", "Equal To", dataDump.getValue("GroupID"));
			broadsoftGroupPage.verifyGroupServices("BVEStandardSeatv1.0", 2, 2, 2);
			broadsoftGroupPage.verifyGroupServices("BVEUnifiedSeatw/vmail2.1", 2, 2, 2);
		} catch (Exception Ex) {
			report.reportHardFailEvent("Broadsoft Standard/UC Seat verification", "Broadsoft Standard/UC Seat verification failed", Status.FAIL);
			String eMsg = "Error in Broadsoft Standard/UC Seat verification failed" + Ex.getMessage();
			log.error(eMsg);
			
		}
	}
	
	@Test(priority = 97500, groups={"MACD", "All"})
	public void broadsoftLoginAfterMACD() throws Exception {
		broadsoftLogin();
	}
	

	@Test(priority = 98000, groups={"MACD", "All"})
	public void verifyGroupServicesAfterMACD() throws Exception {
		
		BroadsoftGroupPage broadsoftGroupPage = new BroadsoftGroupPage(frameworkContext);
		try {
			if (!BroadsoftLogInPage.broadsoftLogedIn) {
				broadsoftLogin();
			}
			broadsoftGroupPage.searchGroups("Group ID", "Equal To", dataDump.getValue("GroupID"));
			broadsoftGroupPage.verifyGroupServices("BVEStandardSeatv1.0", 1, 1, 1);
			broadsoftGroupPage.verifyGroupServices("BVEUnifiedSeatw/vmail2.1", 3, 3, 3);
		} catch (Exception Ex) {
			report.reportHardFailEvent("Broadsoft Standard/UC Seat verification", "Broadsoft Standard/UC Seat verification failed", Status.FAIL);
			String eMsg = "Error in Broadsoft Standard/UC Seat verification failed" + Ex.getMessage();
			log.error(eMsg);
			
		}
	}
	
	
}
