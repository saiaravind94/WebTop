package com.comcast.WebTopCases;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.comcast.pages.broadsoft.BroadsoftGroupPage;
import com.comcast.pages.broadsoft.BroadsoftLogInPage;
import com.comcast.pages.webtop.*;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;

public class DisconnectFlow extends MACDFlow {
	DataTable dataSet;
	String eMsg = "";
	

	Logger log = Logger.getLogger(DisconnectFlow.class);

	@Test(priority = 120000, groups={"MACD", "All"})
	public void terminateAccount() throws Exception {		
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		BusinessCentralTab businessCentralTab = new BusinessCentralTab(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
			}
			webTopMainPage.searchOrder("Group ID");
			businessCentralTab.terminateAccount();
			dataDump.setValue("OrderStatus", "In-Progress");
			dataDump.setValue("IsMACDPlaced", "Yes");
		} catch (Exception Ex) {
			report.reportHardFailEvent("Terminate account", "Terminate account Failed!!!", Status.FAIL);
			String eMsg = "Error in Terminating account --- " + Ex.getMessage();
			log.error(eMsg);
			
		}
	}
	
	@Test(priority = 120500, groups={"MACD", "All"})
	public void attachDocumentsToDisconnectOrder() throws Exception {
		
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		AttachDocumentsToDisconnectOrder attachDocumentsToDisconnectOrder = new AttachDocumentsToDisconnectOrder(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
			}
			webTopMainPage.searchFor("Group ID");
			attachDocumentsToDisconnectOrder.attachDocumentsToDisconnectOrder();
		} catch (Exception Ex) {
			report.reportHardFailEvent("CARE: Attach Documents to Disconnect Order", "CARE: Attach Documents to Disconnect Order Failed!!!", Status.FAIL);
			String eMsg = "Error in CARE: Attach Documents to Disconnect Order --- " + Ex.getMessage();
			log.error(eMsg);
			
		}
	}
	
	@Test(priority = 121000, groups={"MACD", "All"})
	public void acceptOrRejectTNDisconnectionOrder() throws Exception {
		
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		AcceptOrRejectTNDisconnectionOrder acceptOrRejectTNDisconnectionOrder = new AcceptOrRejectTNDisconnectionOrder(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}			
			acceptOrRejectTNDisconnectionOrder.acceptOrRejectTNDisconnectionOrder();
		} catch (Exception Ex) {
			report.reportHardFailEvent("CARE: Accept or Reject TN Disconnection Order", "CARE: Accept or Reject TN Disconnection Order Failed!!!",
					Status.FAIL);
			String eMsg = "Error in CARE: Accept or Reject TN Disconnection Order --- " + Ex.getMessage();
			log.error(eMsg);
			
		}
	}
	
	@Test(priority = 121100, groups={"MACD", "All"})
	public void autoAdjustBundleBasedOnConfigurationItemsOnThisOrder() throws Exception {		
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		AutoAdjustBundleBasedOnConfigurationItemsOnThisOrder autoAdjustBundleBasedOnConfigurationItemsOnThisOrder = new AutoAdjustBundleBasedOnConfigurationItemsOnThisOrder(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}			
			autoAdjustBundleBasedOnConfigurationItemsOnThisOrder.autoAdjustBundleBasedOnConfigurationItemsOnThisOrder();
		} catch (Exception Ex) {
			report.reportHardFailEvent("CARE:  Auto-Adjust Bundle Based On Configuration Items On This Order",
					"CARE:  Auto-Adjust Bundle Based On Configuration Items On This Order Failed!!!", Status.FAIL);
			String eMsg = "Error in CARE:  Auto-Adjust Bundle Based On Configuration Items On This Order --- "
					+ Ex.getMessage();
			log.error(eMsg);			
		}
	}
	
	@Test(priority = 121110, groups={"MACD", "All"})
	public void reviewMACDBillingImpact() throws Exception {		
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		ReviewMACDBillingImpact reviewMACDBillingImpact = new ReviewMACDBillingImpact(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}			
			reviewMACDBillingImpact.reviewMACDBillingImpact();
		} catch (Exception Ex) {
			report.reportHardFailEvent("CARE: Review MACD Billing Impact",
					"CARE: Review MACD Billing Impact Failed!!!", Status.FAIL);
			String eMsg = "Error in CARE: Review MACD Billing Impact --- " + Ex.getMessage();
			log.error(eMsg);		
		}
	}
	
	@Test(priority = 121120, groups={"MACD", "All"})
	public void updatePrimaryContactDetails() throws Exception {		
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		UpdatePrimaryContactDetails updatePrimaryContactDetails = new UpdatePrimaryContactDetails(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}			
			updatePrimaryContactDetails.updatePrimaryContactDetails();
		} catch (Exception Ex) {
			report.reportHardFailEvent("CARE: Update Primary Contact Details",
					"CARE: Update Primary Contact Details Failed!!!", Status.FAIL);
			String eMsg = "Error in CARE: Update Primary Contact Details --- " + Ex.getMessage();
			log.error(eMsg);		
		}
	}
	
	@Test(priority = 121500, groups={"MACD", "All"})
	public void captureDisconnectOrderType() throws Exception {
		
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		CaptureDisconnectOrderType captureDisconnectOrderType = new CaptureDisconnectOrderType(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}			
			captureDisconnectOrderType.captureDisconnectOrderType();
		} catch (Exception Ex) {
			report.reportHardFailEvent("CARE: Capture Disconnect Order Type'", "CARE: Capture Disconnect Order Type' Failed!!!",
					Status.FAIL);
			String eMsg = "Error in CARE: Capture Disconnect Order Type' --- " + Ex.getMessage();
			log.error(eMsg);
			
		}
	}
	

	@Test(priority = 122000, groups={"MACD", "All"})
	public void captureDisconnectOrderReason() throws Exception {
		
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		CaptureDisconnectOrderReason captureDisconnectOrderReason = new CaptureDisconnectOrderReason(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}			
			captureDisconnectOrderReason.captureDisconnectOrderReason();
		} catch (Exception Ex) {
			report.reportHardFailEvent("CARE: Capture Disconnect Order Reason", "CARE: Capture Disconnect Order Reason Failed!!!",
					Status.FAIL);
			String eMsg = "Error in CARE: Capture Disconnect Order Reason--- " + Ex.getMessage();
			log.error(eMsg);
			
		}
	}
	
	@Test(priority = 122500, groups={"MACD", "All"})
	public void requestRMAForCPE_DisconnectOrder() throws Exception {
		
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		RequestRMAForCPE_DisconnectOrder requestRMAForCPE_DisconnectOrder = new RequestRMAForCPE_DisconnectOrder(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}			
			requestRMAForCPE_DisconnectOrder.requestRMAForCPE_DisconnectOrder();
		} catch (Exception Ex) {
			report.reportHardFailEvent("CARE: Request RMA for CPE (Disconnect Order)", "CARE: Request RMA for CPE (Disconnect Order) Failed!!!",
					Status.FAIL);
			String eMsg = "Error in CARE: Request RMA for CPE (Disconnect Order) --- " + Ex.getMessage();
			log.error(eMsg);
			
		}
	}
	
	@Test(priority = 123000, groups={"MACD", "All"})
	public void scheduleDispatch() throws Exception {
		
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		ScheduleDispatch scheduleDispatch_Coax = new ScheduleDispatch(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}			
			scheduleDispatch_Coax.scheduleDispatch();
		} catch (Exception Ex) {
			report.reportHardFailEvent("CARE: Schedule Dispatch (Coax)", "CARE: Schedule Dispatch (Coax) Failed!!!",
					Status.FAIL);
			String eMsg = "Error in CARE: Schedule Dispatch (Coax)--- " + Ex.getMessage();
			log.error(eMsg);
			
		}
	}
	
	@Test(priority = 123500, groups={"MACD", "All"})
	public void reconfigureSwitchForTNDisconnectOrder() throws Exception {
		
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		ReconfigureSwitchForTNDeactivationOrDisconnectOrder reconfigureSwitchForTNDeactivationOrDisconnectOrder = new ReconfigureSwitchForTNDeactivationOrDisconnectOrder(
				frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}
			reconfigureSwitchForTNDeactivationOrDisconnectOrder.reconfigureSwitchForTNDeactivationOrDisconnectOrder();
		} catch (Exception Ex) {
			report.reportHardFailEvent("CARE: Reconfigure Switch for TN Disconnect Order",
					"CARE: Reconfigure Switch for TN Disconnect Order Failed!!!", Status.FAIL);
			String eMsg = "Error in CARE: Reconfigure Switch for TN Disconnect Order --- " + Ex.getMessage();
			log.error(eMsg);
			
		}
	}
	
	@Test(priority = 124000, groups={"MACD", "All"})
	public void errorDeprovisioningDAOrDLOrCNAMAtNeustar() throws Exception {
		
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		ErrorDeprovisioningDAOrDLOrCNAMAtNeustar errorDeprovisioningDAOrDLOrCNAMAtNeustar = new ErrorDeprovisioningDAOrDLOrCNAMAtNeustar(
				frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}
			errorDeprovisioningDAOrDLOrCNAMAtNeustar.errorDeprovisioningDAOrDLOrCNAMAtNeustar();
		} catch (Exception Ex) {
			report.reportHardFailEvent("ADMIN: Error Deprovisioning DA/DL/CNAM at Neustar", "ADMIN: Error Deprovisioning DA/DL/CNAM at Neustar Failed!!!", Status.FAIL);
			String eMsg = "Error in ADMIN: Error Deprovisioning DA/DL/CNAM at Neustar --- " + Ex.getMessage();
			log.error(eMsg);
			
		}
	}
	
	@Test(priority = 124200, groups={"MACD", "All"})
	public void terminateApplicableBundlesAtCustomerLevel() throws Exception {
		
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		TerminateApplicableBundlesAtCustomerLevel terminateApplicableBundlesAtCustomerLevel = new TerminateApplicableBundlesAtCustomerLevel(
				frameworkContext);
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
	
	@Test(priority = 124300, groups={"MACD", "All"})
	public void addMRCChargesToOrderForNewDevices() throws Exception {
		
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		AddMRCChargesToOrderForNewDevices addMRCChargesToOrderForNewDevices = new AddMRCChargesToOrderForNewDevices(
				frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}			
			addMRCChargesToOrderForNewDevices.addMRCChargesToOrderForNewDevices();
		} catch (Exception Ex) {
			report.reportHardFailEvent("CARE: Add MRC Charge(s) to Order for New Device(s)", "CARE: Add MRC Charge(s) to Order for New Device(s) Failed!!!",
					Status.FAIL);
			String eMsg = "Error in CARE: Add MRC Charge(s) to Order for New Device(s) --- " + Ex.getMessage();
			log.error(eMsg);
			
		}
	}
	
	
	
	@Test(priority = 124500, groups={"MACD", "All"})
	public void uninstallCableModem() throws Exception {
		
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		UninstallCableModem uninstallCableModem = new UninstallCableModem(
				frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}			
			uninstallCableModem.uninstallCableModem();
		} catch (Exception Ex) {
			report.reportHardFailEvent("INSTALL TECH: Uninstall Cable Modem", "INSTALL TECH: Uninstall Cable Modem Failed!!!",
					Status.FAIL);
			String eMsg = "Error in INSTALL TECH: Uninstall Cable Modem --- " + Ex.getMessage();
			log.error(eMsg);
			
		}
	}
	
	@Test(priority = 125000, groups={"MACD", "All"})
	public void decommissionESG() throws Exception {
		
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		DecommissionESG decommissionESG = new DecommissionESG(
				frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}			
			decommissionESG.decommissionESG();
		} catch (Exception Ex) {
			report.reportHardFailEvent("INSTALL TECH: Decommission ESG", "INSTALL TECH: Decommission ESG Failed!!!",
					Status.FAIL);
			String eMsg = "Error in INSTALL TECH: Decommission ESG --- " + Ex.getMessage();
			log.error(eMsg);
			
		}
	}
	
	@Test(priority = 125500, groups={"MACD", "All"})
	public void deprovisionDAOrDLOrCNAMAtNeustarManually() throws Exception {
		
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		DeprovisionDAOrDLOrCNAMAtNeustarManually deprovisionDAOrDLOrCNAMAtNeustarManually = new DeprovisionDAOrDLOrCNAMAtNeustarManually(
				frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}			
			deprovisionDAOrDLOrCNAMAtNeustarManually.deprovisionDAOrDLOrCNAMAtNeustarManually();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Deprovision DA/DL/CNAM at Neustar Manually", "Deprovision DA/DL/CNAM at Neustar Manually Failed!!!", Status.FAIL);
			String eMsg = "Error in Deprovision DA/DL/CNAM at Neustar Manually --- " + Ex.getMessage();
			log.error(eMsg);
			
		}
	}
	
	@Test(priority = 126000, groups={"MACD", "All"})
	public void updateBillingForTNDisconnectOrder() throws Exception {
		
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		UpdateBillingForTNDisconnectOrder updateBillingForTNDisconnectOrder = new UpdateBillingForTNDisconnectOrder(
				frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}			
			updateBillingForTNDisconnectOrder.updateBillingForTNDisconnectOrder();
		} catch (Exception Ex) {
			report.reportHardFailEvent("BILLING: Update Billing for TN Disconnect Order", "BILLING: Update Billing for TN Disconnect Order Failed!!!", Status.FAIL);
			String eMsg = "Error in BILLING: Update Billing for TN Disconnect Order --- " + Ex.getMessage();
			log.error(eMsg);
			
		}
	}
	
	@Test(priority = 126500, groups={"MACD", "All"})
	public void deleteBVETopologyRecords() throws Exception {
		
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		DeleteBVETopologyRecords deleteBVETopologyRecords = new DeleteBVETopologyRecords(
				frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}			
			deleteBVETopologyRecords.deleteBVETopologyRecords();
		} catch (Exception Ex) {
			report.reportHardFailEvent("INSTALL TECH: Delete BVE Topology Records", "INSTALL TECH: Delete BVE Topology Records Failed!!!",
					Status.FAIL);
			String eMsg = "Error in INSTALL TECH: Delete BVE Topology Records --- " + Ex.getMessage();
			log.error(eMsg);
			
		}
	}
	
	@Test(priority = 126600, groups={"MACD", "All"})
	public void errorOccurredWhenRemovingDevicesFromCEMP() throws Exception {
		
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		ErrorOccurredWhenRemovingDevicesFromCEMP errorOccurredWhenRemovingDevicesFromCEMP = new ErrorOccurredWhenRemovingDevicesFromCEMP(
				frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}			
			errorOccurredWhenRemovingDevicesFromCEMP.errorOccurredWhenRemovingDevicesFromCEMP();
		} catch (Exception Ex) {
			report.reportHardFailEvent("CARE: Error Occurred When Removing Devices from CEMP", "CARE: Error Occurred When Removing Devices from CEMP Failed!!!",
					Status.FAIL);
			String eMsg = "Error in CARE: Error Occurred When Removing Devices from CEMP  --- " + Ex.getMessage();
			log.error(eMsg);		}
	}
	
	@Test(priority = 127000, groups={"MACD", "All"})
	public void notifySNAPofGreetingDisconnect() throws Exception {
		
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		NotifySNAPofGreetingDisconnect notifySNAPofGreetingDisconnect = new NotifySNAPofGreetingDisconnect(
				frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}			
			notifySNAPofGreetingDisconnect.notifySNAPofGreetingDisconnect();
		} catch (Exception Ex) {
			report.reportHardFailEvent("CARE: Notify SNAP of Greeting Disconnect", "CARE: Notify SNAP of Greeting Disconnect Failed!!!",
					Status.FAIL);
			String eMsg = "Error in CARE: Notify SNAP of Greeting Disconnect --- " + Ex.getMessage();
			log.error(eMsg);		}
	}
	
	@Test(priority = 127500, groups={"MACD", "All"})
	public void broadsoftLogin_AfterMACD() throws Exception {
		broadsoftLogin();
	}
	
	@Test(priority = 128000, groups={"MACD", "All"})
	public void verifyGroupDisconnectedFromSwitch() throws Exception {		
		BroadsoftGroupPage broadsoftGroupPage = new BroadsoftGroupPage(frameworkContext);
		try {
			if (!BroadsoftLogInPage.broadsoftLogedIn) {
				broadsoftLogin();				
			}
			if(!broadsoftGroupPage.verifyGroups("Group ID", "Equal To", dataDump.getValue("GroupID")))
			{
				report.reportPassEvent("Broadsoft Group/Users verfication", "Broadsoft Group/Users are disconnected from switch", Status.PASS);
			}
			else
			{
				report.reportHardFailEvent("Broadsoft Group/Users verfication", "Broadsoft Group/Users are not disconnected from switch", Status.FAIL);
			}
			dataDump.setValue("OrderStatus", "Disconnected");
		} catch (Exception Ex) {
			report.reportHardFailEvent("Broadsoft Group/Users verfication", "Broadsoft Group/Users are not disconnected from switch", Status.FAIL);
			String eMsg = "Error in Broadsoft Group/Users verfication--- " + Ex.getMessage();
			log.error(eMsg);
			
		}
	}

}
