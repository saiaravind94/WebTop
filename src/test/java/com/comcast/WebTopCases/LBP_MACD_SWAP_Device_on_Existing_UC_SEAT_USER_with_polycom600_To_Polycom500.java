package com.comcast.WebTopCases;

import org.testng.annotations.Test;

import com.comcast.pages.broadsoft.BroadsoftGroupPage;
import com.comcast.pages.broadsoft.BroadsoftLogInPage;
import com.comcast.pages.webtop.AccountProfileTab;
import com.comcast.pages.webtop.AttachDocumentsToDisconnectOrder;
import com.comcast.pages.webtop.EnterDOIForDeviceSwap;
import com.comcast.pages.webtop.ErrorDeterminingOrderType;
import com.comcast.pages.webtop.ErrorProvisioningDECTHandsetDeviceSwapOnTNsInBroadworks;
import com.comcast.pages.webtop.ErrorValidatingOrderBeforeSendingToSNAP;
import com.comcast.pages.webtop.OnDOI_ContinueToProvisioningOfDeviceSwap;
import com.comcast.pages.webtop.OrderNewDeviceOrGenerateRMAIfNeeded;
import com.comcast.pages.webtop.ServicesPage;
import com.comcast.pages.webtop.UpdateBillingForDeviceSwapOrder;
import com.comcast.pages.webtop.UpdateBillingForTNDisconnectOrder;
import com.comcast.pages.webtop.WebTopLogInPage;
import com.comcast.pages.webtop.WebTopMainPage;
import com.comcast.reporting.Status;

public class LBP_MACD_SWAP_Device_on_Existing_UC_SEAT_USER_with_polycom600_To_Polycom500
		extends DisconnectFlow {
	
	@Test(priority = 70500, groups={"MACD", "All"})
	public void swapDeviceFromUCSeatPloycom600To500() throws Exception {
		
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
			accountProfileTab.addSeat("UC", UCSeatIdxToSwap + 1);			
			servicesPage.addServices("SWAPUCSeatPolycom600ToPolycom500", "");
			servicesPage.addItemOrBundle("Item", "ANI", "BVEVVX500H-Polycom SoundPoint VVX 500 HD | 0.00", 1);			
			accountProfileTab.submitTheOrder();
		} catch (Exception Ex) {
			report.reportHardFailEvent("SWAP UC Seat from polycom 600 to polycom 500",
					"SWAP UC Seat polycom 600 to from polycom 500 is Failed!!!", Status.FAIL);
			String eMsg = "Error in SWAP UC Seat from polycom 600 to polycom 500 --- " + Ex.getMessage();
			log.error(eMsg);
			
		}
	}
	
	@Test(priority = 70510, groups={"MACD", "All"})
	public void broadsoftLoginBeforeMACD() throws Exception {
		broadsoftLogin();
	}
	

	@Test(priority = 70520, groups={"MACD", "All"})
	public void getIdentityAndProfileMACAddressBeforeMACD() throws Exception {		
		String MACAddress;
		BroadsoftGroupPage broadsoftGroupPage = new BroadsoftGroupPage(frameworkContext);
		try {
			if (!BroadsoftLogInPage.broadsoftLogedIn) {
				broadsoftLogin();
			}
			broadsoftGroupPage.searchGroups("Group ID", "Equal To", dataDump.getValue("GroupID"));
			MACAddress = broadsoftGroupPage.getIdentityAndProfileMACAddress(dataDump.getValue("ANINumberToSWAP"));
			log.info("MAC Address for the TN: "+ dataDump.getValue("ANINumberToSWAP") +" before MACD: " + MACAddress);
			dataDump.setValue("MACAddress_Before", MACAddress);
			report.reportPassEvent("Getting broadsoft Identity and profile MAC address before MACD for the TN", "MAC Address for the TN: " + MACAddress);
			new BroadsoftLogInPage(frameworkContext).broadsoftLogout();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Getting broadsoft Identity and profile MAC address before MACD", "Getting broadsoft Identity and profile MAC address failed", Status.FAIL);
			String eMsg = "Error in Getting broadsoft Identity and profile MAC address before MACD failed" + Ex.getMessage();
			log.error(eMsg);
			
		}
	}
	
	@Test(priority = 71500, groups={"MACD", "All"})
	public void orderNewDeviceOrGenerateRMAIfNeeded() throws Exception {
		
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		OrderNewDeviceOrGenerateRMAIfNeeded orderNewDeviceOrGenerateRMAIfNeeded = new OrderNewDeviceOrGenerateRMAIfNeeded(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();				
			}
			webTopMainPage.searchFor("Group ID");
			orderNewDeviceOrGenerateRMAIfNeeded.orderNewDeviceOrGenerateRMAIfNeeded();
		} catch (Exception Ex) {
			report.reportHardFailEvent("CARE: Order New Device / Generate RMA if needed", "CARE: Order New Device / Generate RMA if needed Failed!!!",
					Status.FAIL);
			String eMsg = "Error in CARE: Order New Device / Generate RMA if needed --- " + Ex.getMessage();
			log.error(eMsg);
			
		}
	}
	
	@Test(priority = 71600, groups={"MACD", "All"})
	public void enterDOIForDeviceSwap() throws Exception {
		
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		EnterDOIForDeviceSwap enterDOIForDeviceSwap = new EnterDOIForDeviceSwap(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}			
			enterDOIForDeviceSwap.enterDOIForDeviceSwap();
		} catch (Exception Ex) {
			
			report.reportHardFailEvent("Enter DOI For Device SWAP", "Enter DOI For Device SWAP Failed!!!",
					Status.FAIL);
			String eMsg = "Error in Enter DOI For Device SWAP --- " + Ex.getMessage();
			log.error(eMsg);
		}
	}
	
	@Test(priority = 71650, groups={"MACD", "All"})
	public void onDOI_ContinueToProvisioningOfDeviceSwap() throws Exception {
		
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		OnDOI_ContinueToProvisioningOfDeviceSwap onDOI_ContinueToProvisioningOfDeviceSwap = new OnDOI_ContinueToProvisioningOfDeviceSwap(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}			
			onDOI_ContinueToProvisioningOfDeviceSwap.onDOI_ContinueToProvisioningOfDeviceSwap();
		} catch (Exception Ex) {
			
			report.reportHardFailEvent("Enter DOI For Device SWAP", "Enter DOI For Device SWAP Failed!!!",
					Status.FAIL);
			String eMsg = "Error in Enter DOI For Device SWAP --- " + Ex.getMessage();
			log.error(eMsg);
		}
	}
	
	@Test(priority = 71660, groups={"MACD", "All"})
	public void errorProvisioningDECTHandsetDeviceSwapOnTNsInBroadworks() throws Exception {		
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		ErrorProvisioningDECTHandsetDeviceSwapOnTNsInBroadworks errorProvisioningDECTHandsetDeviceSwapOnTNsInBroadworks = new ErrorProvisioningDECTHandsetDeviceSwapOnTNsInBroadworks(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}			
			errorProvisioningDECTHandsetDeviceSwapOnTNsInBroadworks.errorProvisioningDECTHandsetDeviceSwapOnTNsInBroadworks();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Error Provisioning DECT Handset Device Swap on TN(s) in Broadworks",
					"Error Provisioning DECT Handset Device Swap on TN(s) in Broadworks Failed!!!", Status.FAIL);
			String eMsg = "Error in Error Provisioning DECT Handset Device Swap on TN(s) in Broadworks --- "
					+ Ex.getMessage();
			log.error(eMsg);
		}
	}
	
	
	@Test(priority = 126000, groups={"MACD", "All"})
	public void updateBillingForDeviceSwapOrder() throws Exception {
		
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		UpdateBillingForDeviceSwapOrder updateBillingForDeviceSwapOrder = new UpdateBillingForDeviceSwapOrder(
				frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}			
			updateBillingForDeviceSwapOrder.updateBillingForDeviceSwapOrder();
		} catch (Exception Ex) {
			report.reportHardFailEvent("BILLING: Update Billing for Swap Order", "BILLING: Update Billing for Swap Order Failed!!!",
					Status.FAIL);
			String eMsg = "Error in BILLING: Update Billing for Swap Change Order --- " + Ex.getMessage();
			log.error(eMsg);
			
		}
	}
	@Test(priority = 126500, groups={"MACD", "All"})
	public void broadsoftLoginAfterMACD() throws Exception {
		broadsoftLogin();
	}
	

	@Test(priority = 127000, groups={"MACD", "All"})
	public void verifyIdentityAndProfileMACAddressAfterMACD() throws Exception {
		
		BroadsoftGroupPage broadsoftGroupPage = new BroadsoftGroupPage(frameworkContext);
		try {
			if (!BroadsoftLogInPage.broadsoftLogedIn) {
				broadsoftLogin();
			}
			broadsoftGroupPage.searchGroups("Group ID", "Equal To", dataDump.getValue("GroupID"));
			log.info("MAC Address for the TN: "+ dataDump.getValue("ANINumberToSWAP") +" after MACD: " + broadsoftGroupPage.getIdentityAndProfileMACAddress(dataDump.getValue("ANINumberToSWAP")));
			dataDump.setValue("MACAddress_After", broadsoftGroupPage.getIdentityAndProfileMACAddress(dataDump.getValue("ANINumberToSWAP")));
			if(!dataDump.getValue("MACAddress_After").equals(dataDump.getValue("MACAddress_Before")))
			{
				report.reportPassEvent("MAC Address verification for the new Device after MACD", "New MAC Address is assigned to the new Device is as expected");
			}
			else
			{
				report.reportHardFailEvent("MAC Address verification for the new Device after MACD", "New MAC Address is not assigned/empty to the new Device which is as expected");
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verification of broadsoft Identity and profile MAC address before MACD", "Verification of broadsoft Identity and profile MAC address failed", Status.FAIL);
			String eMsg = "Error in Verification of broadsoft Identity and profile MAC address before MACD failed" + Ex.getMessage();
			log.error(eMsg);
			
		}
	}
	
	
}
