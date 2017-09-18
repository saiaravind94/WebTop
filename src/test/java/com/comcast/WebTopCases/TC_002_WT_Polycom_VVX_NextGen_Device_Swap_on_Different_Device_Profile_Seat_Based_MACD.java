package com.comcast.WebTopCases;

import org.testng.annotations.Test;

import com.comcast.pages.webtop.AccountProfileTab;
import com.comcast.pages.webtop.ErrorDeterminingOrderType;
import com.comcast.pages.webtop.ErrorValidatingDeviceSwapOrder;
import com.comcast.pages.webtop.ServicesPage;
import com.comcast.pages.webtop.WebTopLogInPage;
import com.comcast.pages.webtop.WebTopMainPage;
import com.comcast.pages.webtop.WorkOrderComments;
import com.comcast.reporting.Status;

public class TC_002_WT_Polycom_VVX_NextGen_Device_Swap_on_Different_Device_Profile_Seat_Based_MACD extends MACDFlow {
	
	
	@Test(priority = 50000, groups={"MACD", "All"})
	public void swapUCSeat() throws Exception {
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
			int UCSeatIdxToTerminate = 0;
			for(int idx = 0; idx<dataDump.getValue("UCANIList_before").split("\\|").length;idx++)
			{
				if(!BTNNo.equalsIgnoreCase(dataDump.getValue("UCANIList_before").split("\\|")[idx])){
					dataDump.setValue("ANINumberToTerminate_RT", dataDump.getValue("UCANIList_before").split("\\|")[idx]);
					UCSeatIdxToTerminate = idx;
					break;
				}				
			}
			accountProfileTab.addSeat("UC", UCSeatIdxToTerminate + 1);
			servicesPage.addServices("AddNewPanasonicDevice", "");
			servicesPage.addItemOrBundle("Item", "ANI", "BVEHTPA60I-Panasonic TPA60 Smart IP Cordless Handset | 0.00", 1);
			accountProfileTab.submitTheOrder();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Swapping UC Seat to different profile", "Swapping UC Seat to different profile is Failed!!!", Status.FAIL);
			String eMsg = "Error in Swapping UC Seat to different profile --- " + Ex.getMessage();
			log.error(eMsg);
			
		}
	}
	
	@Test(priority = 50500, groups={"MACD", "All"})
	public void errorDeterminingOrderTypeAfterMACD() throws Exception {
		errorDeterminingOrderType();		
	}
	
	@Test(priority = 51000, groups={"MACD", "All"})
	public void fallOutTrayValidationForDeviceSwap() throws Exception {
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		ErrorValidatingDeviceSwapOrder errorValidatingDeviceSwapOrder = new ErrorValidatingDeviceSwapOrder(frameworkContext);
		WorkOrderComments workOrderComments = new WorkOrderComments(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}			
			errorValidatingDeviceSwapOrder.verifyIfTrayIsDisplayed();
			workOrderComments.deviceSwapErrorMsgValidation("Device dms-tgp cannot be swapped with dms-psip because it has a different profile");
			errorValidatingDeviceSwapOrder.deleteOrder();
		} catch (Exception Ex) {
			report.reportHardFailEvent("CARE: Error Validating Device Swap Order tray",
					"CARE: Error Validating Device Swap Order tray Failed!!!", Status.FAIL);
			String eMsg = "Error in CARE: Error Validating Device Swap Order --- "
					+ Ex.getMessage();
			log.error(eMsg);			
			
		}	
	}

}
