package com.comcast.WebTopCases;

import org.testng.annotations.Test;

import com.comcast.pages.broadsoft.BroadsoftGroupPage;
import com.comcast.pages.broadsoft.BroadsoftLogInPage;
import com.comcast.pages.webtop.AcceptOrRejectTNDeactivationOrder;
import com.comcast.pages.webtop.AccountProfileTab;
import com.comcast.pages.webtop.AttachDocumentsTo10DigitTNRemovalOrder;
import com.comcast.pages.webtop.AutoAdjustBundleBasedOnConfigurationItemsOnThisOrder;
import com.comcast.pages.webtop.ErrorDeterminingOrderType;
import com.comcast.pages.webtop.ErrorOccurredWhenAddingDevicesToCEMP_VGS;
import com.comcast.pages.webtop.ErrorValidatingOrderBeforeSendingToSNAP;
import com.comcast.pages.webtop.ReconfigureSwitchForTNDeactivationOrDisconnectOrder;
import com.comcast.pages.webtop.RemoveBriaFromGroupInSwitch;
import com.comcast.pages.webtop.ServicesPage;
import com.comcast.pages.webtop.TerminateApplicableBundlesAtCustomerLevel;
import com.comcast.pages.webtop.TicketOpenedWithCEMPTeamForManualProvisioning;
import com.comcast.pages.webtop.UpdateBillingFor10digitTNRemovalOrder;
import com.comcast.pages.webtop.WebTopLogInPage;
import com.comcast.pages.webtop.WebTopMainPage;
import com.comcast.reporting.Status;

public class LBP_MACD_Reassign_10Digit_UC_ANI_ToVoIP_UC_Seat_user
		extends BVE_NC_Std_site_UCseat_Loudringer_with_horn_3_strobe_lights_3_strobe_clears_Extension {

	@Test(priority = 70500, groups={"MACD", "All"})
	public void convertUCSeatANIToVoIpUser() throws Exception {
		
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
			int UCSeatidxToConvert = 0;
			for(int idx = 0; idx<dataDump.getValue("UCANIList_before").split("\\|").length;idx++)
			{
				if(!BTNNo.equalsIgnoreCase(dataDump.getValue("UCANIList_before").split("\\|")[idx])){
					dataDump.setValue("ANINumberToConvert_RT", dataDump.getValue("UCANIList_before").split("\\|")[idx]);
					UCSeatidxToConvert = idx;
					break;
				}				
			}			
			accountProfileTab.addSeat("UC", UCSeatidxToConvert + 1);			
			servicesPage.addServices("convertUCSeatANIToVoIPUser", "VoIP");
			servicesPage.addVoIP();
			servicesPage.addItemOrBundle("Item", "VIP", "CONVERTANI-Convert ANI Record to VoIP | 0.00", 1);			
			accountProfileTab.submitTheOrder();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Add VoIp Announce on existing VoIp",
					"Add VoIp Announce on existing VoIp is Failed!!!", Status.FAIL);
			String eMsg = "Error in Add VoIp Announce on existing VoIp --- " + Ex.getMessage();
			log.error(eMsg);
			
		}
	}
	
	@Test(priority = 72000, groups={"MACD", "All"})
	public void attachDocumentsTo10DigitTNRemovalOrder() throws Exception {
		
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		AttachDocumentsTo10DigitTNRemovalOrder attachDocumentsTo10DigitTNRemovalOrder = new AttachDocumentsTo10DigitTNRemovalOrder(frameworkContext);		
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}			
			attachDocumentsTo10DigitTNRemovalOrder.attachDocumentsTo10DigitTNRemovalOrder();
		} catch (Exception Ex) {
			report.reportHardFailEvent("CARE: Attach Documents to 10-digit TN Removal Order", "CARE: Attach Documents to 10-digit TN Removal Order Failed!!!", Status.FAIL);
			String eMsg = "Error in CARE: Attach Documents to 10-digit TN Removal Order --- " + Ex.getMessage();
			log.error(eMsg);
			
		}
	}
	
	@Test(priority = 72500, groups={"MACD", "All"})
	public void acceptOrRejectTNDeactivationOrder() throws Exception {
		
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		AcceptOrRejectTNDeactivationOrder acceptOrRejectTNDeactivationOrder = new AcceptOrRejectTNDeactivationOrder(frameworkContext);		
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}
			new AutoAdjustBundleBasedOnConfigurationItemsOnThisOrder(frameworkContext).autoAdjustBundleBasedOnConfigurationItemsOnThisOrder();
			acceptOrRejectTNDeactivationOrder.acceptOrRejectTNDeactivationOrder();
		} catch (Exception Ex) {
			report.reportHardFailEvent("CARE: Accept or Reject TN Deactivation Order (TNs remain on account)", "CARE: Accept or Reject TN Deactivation Order (TNs remain on account) Failed!!!",
					Status.FAIL);
			String eMsg = "Error in CARE: Accept or Reject TN Deactivation Order (TNs remain on account) --- " + Ex.getMessage();
			log.error(eMsg);
			
		}
	}
	
	@Test(priority = 73000, groups={"MACD", "All"})
	public void reconfigureSwitchForTNDeactivationOrDisconnectOrder() throws Exception {
		
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		ReconfigureSwitchForTNDeactivationOrDisconnectOrder reconfigureSwitchForTNDeactivationOrDisconnectOrder = new ReconfigureSwitchForTNDeactivationOrDisconnectOrder(frameworkContext);		
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}			
			reconfigureSwitchForTNDeactivationOrDisconnectOrder.reconfigureSwitchForTNDeactivationOrDisconnectOrder();
		} catch (Exception Ex) {
			report.reportHardFailEvent("VOIP PROV: Reconfigure Switch for TN Deactivation/Disconnect Order", "VOIP PROV: Reconfigure Switch for TN Deactivation/Disconnect Order Failed!!!",
					Status.FAIL);
			String eMsg = "Error in VOIP PROV: Reconfigure Switch for TN Deactivation/Disconnect Order --- " + Ex.getMessage();
			log.error(eMsg);
			
		}
	}
	
	@Test(priority = 73100, groups={"MACD", "All"})
	public void removeBriaFromGroupInSwitch() throws Exception {
		
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		RemoveBriaFromGroupInSwitch removeBriaFromGroupInSwitch = new RemoveBriaFromGroupInSwitch(frameworkContext);		
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}			
			removeBriaFromGroupInSwitch.removeBriaFromGroupInSwitch();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Remove Bria from Group in Switch",
					"Remove Bria from Group in Switch Failed!!!", Status.FAIL);
			String eMsg = "Error in Remove Bria from Group in Switch --- "
					+ Ex.getMessage();
			log.error(eMsg);			
		}
	}
	
	@Test(priority = 73500, groups={"MACD", "All"})
	public void terminateApplicableBundlesAtCustomerLevel() throws Exception {
		
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		TerminateApplicableBundlesAtCustomerLevel terminateApplicableBundlesAtCustomerLevel = new TerminateApplicableBundlesAtCustomerLevel(frameworkContext);		
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}			
			terminateApplicableBundlesAtCustomerLevel.terminateApplicableBundlesAtCustomerLevel();
		} catch (Exception Ex) {
			report.reportHardFailEvent("CARE: Terminate Applicable Bundles at Customer Level", "CARE: Accept or Reject TN Deactivation Order (TNs remain on account) Failed!!!",
					Status.FAIL);
			String eMsg = "Error in CARE: Terminate Applicable Bundles at Customer Level --- " + Ex.getMessage();
			log.error(eMsg);
			
		}
	}
	
	@Test(priority = 74000, groups={"MACD", "All"})
	public void updateBillingFor10digitTNRemovalOrder() throws Exception {
		
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		UpdateBillingFor10digitTNRemovalOrder updateBillingFor10digitTNRemovalOrder = new UpdateBillingFor10digitTNRemovalOrder(frameworkContext);		
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}
			new ErrorOccurredWhenAddingDevicesToCEMP_VGS(frameworkContext).errorOccurredWhenAddingDevicesToCEMP_VGS();
			new TicketOpenedWithCEMPTeamForManualProvisioning(frameworkContext).ticketOpenedWithCEMPTeamForManualProvisioning();
			updateBillingFor10digitTNRemovalOrder.updateBillingFor10digitTNRemovalOrder();
		} catch (Exception Ex) {
			report.reportHardFailEvent("BILLING: Update Billing for 10-digit TN Removal Order", "BILLING: Update Billing for 10-digit TN Removal Order Failed!!!", Status.FAIL);
			String eMsg = "Error in BILLING: Update Billing for 10-digit TN Removal Order --- " + Ex.getMessage();
			log.error(eMsg);		
		}
	}
	
	@Test(priority = 97500, groups={"MACD", "All"})
	public void broadsoftLoginAfterMACD() throws Exception {
		broadsoftLogin();
	}
	

	@Test(priority = 98000, groups={"MACD", "All"})
	public void verify10DigitTNNumberisRemovedFromSwitch() throws Exception {
		
		BroadsoftGroupPage broadsoftGroupPage = new BroadsoftGroupPage(frameworkContext);
		try {
			if (!BroadsoftLogInPage.broadsoftLogedIn) {
				broadsoftLogin();
			}
			broadsoftGroupPage.searchGroups("Group ID", "Equal To", dataDump.getValue("GroupID"));
			broadsoftGroupPage.verifyUsers();
			if(!broadsoftGroupPage.verify10DigitTN(dataDump.getValue("ANINumberToConvert_RT")))
			{
				report.reportPassEvent("Verify if the 10 Digit TN Number is removed", "10 Digit TN Number is removed");
			}
			else
			{
				report.reportHardFailEvent("Verify if the 10 Digit TN Number is removed", "10 Digit TN Number is not removed");
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Broadsoft 10 Digit TN Number verification", "Broadsoft 10 Digit TN Number verification failed", Status.FAIL);
			String eMsg = "Error in Broadsoft 10 Digit TN Number verification failed" + Ex.getMessage();
			log.error(eMsg);
			
		}
	}
	
	
}
