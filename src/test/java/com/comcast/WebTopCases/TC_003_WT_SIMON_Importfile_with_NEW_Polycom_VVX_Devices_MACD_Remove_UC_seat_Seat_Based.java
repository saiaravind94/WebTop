package com.comcast.WebTopCases;

import org.testng.annotations.Test;

import com.comcast.pages.broadsoft.BroadsoftGroupPage;
import com.comcast.pages.broadsoft.BroadsoftLogInPage;
import com.comcast.pages.webtop.AccountProfileTab;
import com.comcast.pages.webtop.ChooseActionForGroupCallCapacityQuantity;
import com.comcast.pages.webtop.ReconfigureSwitchForTNDeactivationOrDisconnectOrder;
import com.comcast.pages.webtop.SelectTNTreatmentForDisconnectOrder;
import com.comcast.pages.webtop.WebTopLogInPage;
import com.comcast.pages.webtop.WebTopMainPage;
import com.comcast.reporting.Status;

public class TC_003_WT_SIMON_Importfile_with_NEW_Polycom_VVX_Devices_MACD_Remove_UC_seat_Seat_Based
		extends DisconnectFlow {
	
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
			broadsoftGroupPage.getGroupServices("BVEUnifiedSeatw/vmail2.1", "before");
		} catch (Exception Ex) {
			report.reportHardFailEvent("Broadsoft Standard/UC Seat verification", "Broadsoft Standard/UC Seat verification failed", Status.FAIL);
			String eMsg = "Error in Broadsoft Standard/UC Seat verification failed" + Ex.getMessage();
			log.error(eMsg);
			
		}
	}
	
	@Test(priority = 70550, groups={"MACD", "All"})
	public void terminateUCSeat() throws Exception {
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		AccountProfileTab accountProfileTab = new AccountProfileTab(frameworkContext);		
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
			}
			webTopMainPage.searchOrder("Group ID");
			accountProfileTab.getANIList("before");
			String BTNNo = accountProfileTab.getBTNNo();
			//Get the ANI number and index of the ANI which is not linked to BTN ANI
			int UCSeatIdxToTerminate = 0;
			for(int idx = 0; idx<dataDump.getValue("UCANIList_before").split("\\|").length;idx++)
			{
				if(!BTNNo.equalsIgnoreCase(dataDump.getValue("UCANIList_before").split("\\|")[idx])){
					dataDump.setValue("ANINumberToTerminate_RT", dataDump.getValue("UCANIList_before").split("\\|")[idx]);
					UCSeatIdxToTerminate = idx;
					break;
				}				
			}
			accountProfileTab.terminateSeat("UC", UCSeatIdxToTerminate + 1);
			dataDump.setValue("OrderCategory", "MACD");			
			dataDump.setValue("IsMACDPlaced", "Yes");
		} catch (Exception Ex) {
			report.reportHardFailEvent("Terminate UC Seat", "Terminate UC seat Failed!!!", Status.FAIL);
			String eMsg = "Error in Terminating UC seat --- " + Ex.getMessage();
			log.error(eMsg);
			
		}
	}
	
	@Test(priority = 121500, groups={"MACD", "All"})
	public void selectTNTreatmentForDisconnectOrder() throws Exception {
		
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		SelectTNTreatmentForDisconnectOrder selectTNTreatmentForDisconnectOrder = new SelectTNTreatmentForDisconnectOrder(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}			
			selectTNTreatmentForDisconnectOrder.selectTNTreatmentForDisconnectOrder();
		} catch (Exception Ex) {
			report.reportHardFailEvent("CARE:  Select TN Treatment for Disconnect Order", "CARE:  Select TN Treatment for Disconnect Order Failed!!!",
					Status.FAIL);
			String eMsg = "Error in CARE:  Select TN Treatment for Disconnect Order --- " + Ex.getMessage();
			log.error(eMsg);			
		}
	}
	
	@Test(priority = 123600, groups={"MACD", "All"})
	public void chooseActionForGroupCallCapacityQuantity() throws Exception {
		
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		ChooseActionForGroupCallCapacityQuantity chooseActionForGroupCallCapacityQuantity = new ChooseActionForGroupCallCapacityQuantity(
				frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}
			chooseActionForGroupCallCapacityQuantity.chooseActionForGroupCallCapacityQuantity();
		} catch (Exception Ex) {
			report.reportHardFailEvent("CARE: Choose action for group Call Capacity quantity ", "CARE: Choose action for group Call Capacity quantity  Failed!!!",
					Status.FAIL);
			String eMsg = "Error in CARE: Choose action for group Call Capacity quantity  --- " + Ex.getMessage();
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
