package com.comcast.WebTopCases;

import org.testng.annotations.Test;

import com.comcast.pages.broadsoft.BroadsoftGroupPage;
import com.comcast.pages.broadsoft.BroadsoftLogInPage;
import com.comcast.reporting.Status;

public class PartialDisconnectFlow extends SBP_MACD_Teleworker_Site_Partial_Disconnect_UC_seat{
	
	@Test(priority = 70520, groups={"MACD", "All"})
	public void verifyGroupServicesBeforeMACDForBCPOrder() throws Exception {		
		BroadsoftGroupPage broadsoftGroupPage = new BroadsoftGroupPage(frameworkContext);
		try {
			if (!BroadsoftLogInPage.broadsoftLogedIn) {
				broadsoftLogin();
			}
			broadsoftGroupPage.searchGroups("Group ID", "Equal To", dataDump.getValue("GroupID"));			
			broadsoftGroupPage.getGroupServices("BVEUnifiedSeatw/vmail2.1", "before");
		} catch (Exception Ex) {
			report.reportHardFailEvent("Broadsoft Standard/UC Seat verification", "Broadsoft Standard/UC Seat verification failed", Status.FAIL);
			String eMsg = "Error in Broadsoft Standard/UC Seat verification failed" + Ex.getMessage();
			log.error(eMsg);
			
		}
	}
	
	@Test(priority = 127500, groups={"MACD", "All"})
	public void verifyGroupServicesAfterMACDForBCPOrder() throws Exception {		
		BroadsoftGroupPage broadsoftGroupPage = new BroadsoftGroupPage(frameworkContext);
		try {
			if (!BroadsoftLogInPage.broadsoftLogedIn) {
				broadsoftLogin();
			}
			broadsoftGroupPage.searchGroups("Group ID", "Equal To", dataDump.getValue("GroupID"));			
			broadsoftGroupPage.getGroupServices("BVEUnifiedSeatw/vmail2.1", "after");
			if(Integer.parseInt(dataDump.getValue("BVEUnifiedSeatw/vmail2.1_limit_before")) - Integer.parseInt(dataDump.getValue("BVEUnifiedSeatw/vmail2.1_limit_after")) == 1 &&
					Integer.parseInt(dataDump.getValue("BVEUnifiedSeatw/vmail2.1_allowed_before")) - Integer.parseInt(dataDump.getValue("BVEUnifiedSeatw/vmail2.1_allowed_after")) == 1 &&
					Integer.parseInt(dataDump.getValue("BVEUnifiedSeatw/vmail2.1_inUse_before")) - Integer.parseInt(dataDump.getValue("BVEUnifiedSeatw/vmail2.1_inUse_after")) == 1)
			{
				report.reportPassEvent("Broadsoft Standard/UC Seat verification", "Broadsoft Standard/UC Seat verification Passed", Status.PASS);
			}
			else
			{
				report.reportHardFailEvent("Broadsoft Standard/UC Seat verification", "Broadsoft Standard/UC Seat verification failed", Status.FAIL);
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Broadsoft Standard/UC Seat verification", "Broadsoft Standard/UC Seat verification failed", Status.FAIL);
			String eMsg = "Error in Broadsoft Standard/UC Seat verification failed" + Ex.getMessage();
			log.error(eMsg);
			
		}
	}
	
	
	

}
