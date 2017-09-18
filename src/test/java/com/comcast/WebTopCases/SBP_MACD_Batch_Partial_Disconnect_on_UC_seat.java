package com.comcast.WebTopCases;

import org.testng.annotations.Test;

import com.comcast.pages.broadsoft.BroadsoftGroupPage;
import com.comcast.pages.broadsoft.BroadsoftLogInPage;
import com.comcast.pages.webtop.AccountProfileTab;
import com.comcast.pages.webtop.SelectTNTreatmentForDisconnectOrder;
import com.comcast.pages.webtop.WebTopLogInPage;
import com.comcast.pages.webtop.WebTopMainPage;
import com.comcast.reporting.Status;

public class SBP_MACD_Batch_Partial_Disconnect_on_UC_seat
		extends SBP_MACD_Teleworker_Site_Partial_Disconnect_UC_seat {
	
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
			broadsoftGroupPage.verifyGroupServices("TWBVEUnifiedSeatw/vmail2.1", 4, 4, 4);
		} catch (Exception Ex) {
			report.reportHardFailEvent("Broadsoft Standard/UC Seat verification", "Broadsoft Standard/UC Seat verification failed", Status.FAIL);
			String eMsg = "Error in Broadsoft Standard/UC Seat verification failed" + Ex.getMessage();
			log.error(eMsg);
			
		}
	}
	
	@Test(priority = 70550, groups={"MACD", "All"})
	public void batchDisconnect() throws Exception {
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		AccountProfileTab accountProfileTab = new AccountProfileTab(frameworkContext);		
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
			}
			webTopMainPage.searchOrder("Group ID");
			accountProfileTab.getANIList("before");
			String BTNNo = accountProfileTab.getBTNNo();
			//Get the ANI numbers which is not linked to BTN ANI
			String ANINumbersToDisconnect = "";
			for(int idx = 0; idx<dataDump.getValue("UCANIList_before").split("\\|").length;idx++)
			{
				if(!BTNNo.equalsIgnoreCase(dataDump.getValue("UCANIList_before").split("\\|")[idx])){					
					ANINumbersToDisconnect = ANINumbersToDisconnect + dataDump.getValue("UCANIList_before").split("\\|")[idx] + "|";					
				}
							}
			dataDump.setValue("ANINumbersToTerminate_RT", ANINumbersToDisconnect.substring(0,ANINumbersToDisconnect.lastIndexOf("|")));
			accountProfileTab.batchDisconnect(dataDump.getValue("ANINumbersToTerminate_RT"));			
		} catch (Exception Ex) {
			report.reportHardFailEvent("Batch Disconnect", "Batch Disconnect Failed!!!", Status.FAIL);
			String eMsg = "Error in Batch Disconnect --- " + Ex.getMessage();
			log.error(eMsg);
			
		}
	}
	
	@Test(priority = 127000, groups={"MACD", "All"})
	public void broadsoftLoginAfterMACD() throws Exception {
		broadsoftLogin();
	}
	

	@Test(priority = 127500, groups={"MACD", "All"})
	public void verifyGroupServicesAfterMACD() throws Exception {		
		BroadsoftGroupPage broadsoftGroupPage = new BroadsoftGroupPage(frameworkContext);
		try {
			if (!BroadsoftLogInPage.broadsoftLogedIn) {
				broadsoftLogin();
			}
			broadsoftGroupPage.searchGroups("Group ID", "Equal To", dataDump.getValue("GroupID"));			
			broadsoftGroupPage.verifyGroupServices("TWBVEUnifiedSeatw/vmail2.1", 1, 1, 1);
		} catch (Exception Ex) {
			report.reportHardFailEvent("Broadsoft Standard/UC Seat verification", "Broadsoft Standard/UC Seat verification failed", Status.FAIL);
			String eMsg = "Error in Broadsoft Standard/UC Seat verification failed" + Ex.getMessage();
			log.error(eMsg);
			
		}
	}
	
	
}
