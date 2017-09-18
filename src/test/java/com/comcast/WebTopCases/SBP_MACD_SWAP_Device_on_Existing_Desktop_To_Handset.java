package com.comcast.WebTopCases;

import org.testng.annotations.Test;

import com.comcast.pages.broadsoft.BroadsoftGroupPage;
import com.comcast.pages.broadsoft.BroadsoftLogInPage;
import com.comcast.pages.webtop.AccountProfileTab;
import com.comcast.pages.webtop.ErrorProvisioningDECTHandsetDeviceSwapOnTNsInBroadworks;
import com.comcast.pages.webtop.ServicesPage;
import com.comcast.pages.webtop.WebTopLogInPage;
import com.comcast.pages.webtop.WebTopMainPage;
import com.comcast.reporting.Status;

public class SBP_MACD_SWAP_Device_on_Existing_Desktop_To_Handset extends  LBP_MACD_SWAP_Device_on_Existing_UC_SEAT_USER_with_polycom600_To_Polycom500{
	
	@Test(priority = 70500, groups={"MACD", "All"})
	public void swapDeviceFromUCSeatDesktopDECTPhoneToCordlessHandset() throws Exception {		
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
			int UCSeatIdxToSwap = 0;
			for(int idx = 0; idx<dataDump.getValue("UCANIList_before").split("\\|").length;idx++)
			{
				if(!BTNNo.equalsIgnoreCase(dataDump.getValue("UCANIList_before").split("\\|")[idx])){
					dataDump.setValue("ANINumberToSWAP", dataDump.getValue("UCANIList_before").split("\\|")[idx]);
					UCSeatIdxToSwap = idx;
					break;
				}				
			}
			accountProfileTab.getDECTBaseNameAndHandsetIPEI("UC", UCSeatIdxToSwap + 1);
			accountProfileTab.addSeat("UC", UCSeatIdxToSwap + 1);			
			servicesPage.addServices("SWAPUCSeatDesktopDECTPhoneToCordlessHandset", "");
			servicesPage.addItemOrBundle("Item", "ANI", "BVEHTPA60I-Panasonic TPA60 Smart IP Cordless Handset | 0.00", 1);			
			accountProfileTab.submitTheOrder();
		} catch (Exception Ex) {
			report.reportHardFailEvent("SWAP UC Seat from Desktop DECT Phone To Cordless Handset",
					"SWAP UC Seat Desktop DECT Phone To Cordless Handset is Failed!!!", Status.FAIL);
			String eMsg = "Error in SWAP UC Seat from Desktop DECT Phone To Cordless Handset --- " + Ex.getMessage();
			log.error(eMsg);
			
		}
	}
	
	@Test(priority = 70520, groups={"MACD", "All"})
	public void verifyDeviceNameAssignedForTheTNBeforeSwap() throws Exception {
		BroadsoftGroupPage broadsoftGroupPage = new BroadsoftGroupPage(frameworkContext);
		try {
			if (!BroadsoftLogInPage.broadsoftLogedIn) {
				broadsoftLogin();
			}
			broadsoftGroupPage.searchGroups("Group ID", "Equal To", dataDump.getValue("GroupID"));
			String DeviceName = broadsoftGroupPage.getDevcieNameForTheTN(dataDump.getValue("ANINumberToSWAP"));
			log.info("Get Device Name assigned for the TN: "+ dataDump.getValue("ANINumberToSWAP") +" before SWAP: " + DeviceName);
			dataDump.setValue("DeviceName_BeforeSWAP", DeviceName);
			if(dataDump.getValue("DeviceName_BeforeSWAP").equals("Panasonic|KX-TPA65 Desktop DECT Phone"))
			{
				report.reportPassEvent("Verification of Device Name assigned for the TN: "+ dataDump.getValue("ANINumberToSWAP") +" before swap","Device Name assigned for the TN: "+ dataDump.getValue("ANINumberToSWAP") +" is "+DeviceName+" before swap is as expected");
				new BroadsoftLogInPage(frameworkContext).broadsoftLogout();
			}
			else
			{
				report.reportHardFailEvent("Verification of Device Name assigned for the TN: "+ dataDump.getValue("ANINumberToSWAP") +" before swap","Device Name assigned for the TN: "+ dataDump.getValue("ANINumberToSWAP") +" is "+DeviceName+" before swap is not expected");
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verification of Device Name assigned for the TN before SWAP", "Verification of Device Name assigned for the TN before SWAP failed", Status.FAIL);
			String eMsg = "Error in Verification of Device Name assigned for the TN before SWAP failed" + Ex.getMessage();
			log.error(eMsg);
			
		}
	}	
	
	@Test(priority = 124150, groups={"MACD", "All"})
	public void errorProvisioningDECTHandsetDeviceSwapOnTNsInBroadworks() throws Exception {		
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		ErrorProvisioningDECTHandsetDeviceSwapOnTNsInBroadworks errorProvisioningDECTHandsetDeviceSwapOnTNsInBroadworks = new ErrorProvisioningDECTHandsetDeviceSwapOnTNsInBroadworks(
				frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}			
			errorProvisioningDECTHandsetDeviceSwapOnTNsInBroadworks.errorProvisioningDECTHandsetDeviceSwapOnTNsInBroadworks();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Error Provisioning DECT Handset Device Swap on TN(s) in Broadworks", "Error Provisioning DECT Handset Device Swap on TN(s) in Broadworks Failed!!!",
					Status.FAIL);
			String eMsg = "Error in Error Provisioning DECT Handset Device Swap on TN(s) in Broadworks --- " + Ex.getMessage();
			log.error(eMsg);
		}
	}
	
	
	@Test(priority = 127000, groups={"MACD", "All"})
	public void verifyDeviceNameAssignedForTheTNAfterSwap() throws Exception {
		BroadsoftGroupPage broadsoftGroupPage = new BroadsoftGroupPage(frameworkContext);
		try {
			if (!BroadsoftLogInPage.broadsoftLogedIn) {
				broadsoftLogin();
			}
			broadsoftGroupPage.searchGroups("Group ID", "Equal To", dataDump.getValue("GroupID"));
			String DeviceName = broadsoftGroupPage.getDevcieNameForTheTN(dataDump.getValue("ANINumberToSWAP"));
			log.info("Get Device Name assigned for the TN: "+ dataDump.getValue("ANINumberToSWAP") +" After SWAP: " + DeviceName);
			dataDump.setValue("DeviceName_AfterSWAP", DeviceName);
			if(dataDump.getValue("DeviceName_AfterSWAP").equals("Panasonic|TPA60 Smart IP Cordless Handse"))
			{
				report.reportPassEvent("Verification of Device Name assigned for the TN: "+ dataDump.getValue("ANINumberToSWAP") +" after swap","Device Name assigned for the TN: "+ dataDump.getValue("ANINumberToSWAP") +" is "+DeviceName+" after swap is as expected");
			}
			else
			{
				report.reportHardFailEvent("Verification of Device Name assigned for the TN: "+ dataDump.getValue("ANINumberToSWAP") +" after swap","Device Name assigned for the TN: "+ dataDump.getValue("ANINumberToSWAP") +" is "+DeviceName+" after swap is not expected");
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verification of Device Name assigned for the TN after SWAP", "Verification of Device Name assigned for the TN after SWAP failed", Status.FAIL);
			String eMsg = "Error in Verification of Device Name assigned for the TN after SWAP failed" + Ex.getMessage();
			log.error(eMsg);
			
		}
	}

}
