package com.comcast.WebTopCases;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.comcast.pages.webtop.*;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;

public class CancelFlow extends CommonE2EFlow {
	DataTable dataSet;
	String eMsg = "";

	Logger log = Logger.getLogger(CancelFlow.class);

	@Test(priority = 28010, groups = { "New Connect", "All" })
	public void performCPEInstallationDOICancelOrder() throws Exception {
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		PerformCPEInstallation_DOI performCPEInstallation_DOI = new PerformCPEInstallation_DOI(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}
			performCPEInstallation_DOI.performCPEInstallation_DOI("Cancel Order");
		} catch (Exception Ex) {
			report.reportHardFailEvent("Perfrom Cancel Order in  Perform CPE Installation tray",
					"Perfrom Cancel Order in  Perform CPE Installation tray Failed!!!", Status.FAIL);
			String eMsg = "Perfrom Cancel Order in  Perform CPE Installation tray-Failed" + Ex.getMessage();
			log.error(eMsg);
		}
	}

	@Test(priority = 28020, groups = { "New Connect", "All" })
	public void enterCancellationReasonsforCancelOrder() throws Exception {
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		EnterCancellationReasons enterCancellationReasons = new EnterCancellationReasons(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}
			enterCancellationReasons.cancelOrderandEnterCancellationReason();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Enter Cancellation Reasons for Cancel Order",
					"Enter Cancellation Reasons for Cancel Order Failed!!!", Status.FAIL);
			String eMsg = "Enter Cancellation Reasons for Cancel Order Failed!!!" + Ex.getMessage();
			log.error(eMsg);
		}
	}

	@Test(priority = 28030, groups = { "New Connect", "All" })
	public void cancelSNAPOrder() throws Exception {
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		SDPMCancelSNAPOrder sDPMCancelSnapOrder = new SDPMCancelSNAPOrder(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}
			sDPMCancelSnapOrder.cancelSNAPOrder();
			webTopLogout();
		} catch (Exception Ex) {
			report.reportHardFailEvent("SDPM: Government Program Order - Customer Accepts Service Start",
					"SDPM: Government Program Order - Customer Accepts Service Start Update Failed!!!", Status.FAIL);
			String eMsg = "Error in SDPM: Government Program Order - Customer Accepts Service Start --- "
					+ Ex.getMessage();
			log.error(eMsg);

		}
	}

	@Test(priority = 28040, groups = { "New Connect", "All" })
	public void requestEquipmentReturn() throws Exception {
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		RequestEquipmentReturn requestEquipmentReturn = new RequestEquipmentReturn(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}
			requestEquipmentReturn.requestEquipmentReturn();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Request Equipment Return Tray", "Request Equipment Return Tray Failed!!!",
					Status.FAIL);
			String eMsg = "VRequest Equipment Return Tray Failed!!!" + Ex.getMessage();
			log.error(eMsg);
		}
	}

	@Test(priority = 28050, groups = { "New Connect", "All" })
	public void removeNecessaryRecordsFromBroadworks() throws Exception {
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		RemoveNecessaryRecordsfromBroadworks removeNecessaryRecordsfromBroadworks = new RemoveNecessaryRecordsfromBroadworks(
				frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}
			removeNecessaryRecordsfromBroadworks.removeNecessaryRecordsFromBroadworks();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verification of Remove Necessory Records from Broadsworks Tray",
					"Verification of Remove Necessory Records from Broadsworks Tray Failed!!!", Status.FAIL);
			String eMsg = "Verification of Remove Necessory Records from Broadsworks Tray Failed!!!" + Ex.getMessage();
			log.error(eMsg);
		}
	}

	@Test(priority = 28060, groups = { "New Connect", "All" })
	public void removeEnterpriseFromBroadsworks() throws Exception {
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		RemoveEnterpriseFromBroadsworks removeEnterpriseFromBroadsworks = new RemoveEnterpriseFromBroadsworks(
				frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}
			removeEnterpriseFromBroadsworks.removeEnterpriseFromBroadsworks();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verification of Remove Enterprise from Broadsworks Tray",
					"Verification of Remove Enterprise from Broadsworks Tray Failed!!!", Status.FAIL);
			String eMsg = "Verification of Remove Enterprise from Broadsworks Tray Failed!!!" + Ex.getMessage();
			log.error(eMsg);
		}
	}

	@Test(priority = 28070, groups = { "New Connect", "All" })
	public void removeTNFromIntradoManually() throws Exception {
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		RemoveTNFromIntradoManually removeTNFromIntradoManually = new RemoveTNFromIntradoManually(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}
			removeTNFromIntradoManually.removeTNFromIntradoManually();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verification of Remove TN from Intrado manually Tray",
					"Verification of Remove TN from Intrado manually Tray Failed!!!", Status.FAIL);
			String eMsg = "Verification of Remove TN from Intrado manually Tray Failed!!!" + Ex.getMessage();
			log.error(eMsg);
		}
	}

	@Test(priority = 28080, groups = { "New Connect", "All" })
	public void validateSNAPAutoTray() throws Exception {
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		AutoTrayValidations autoTrayValidations = new AutoTrayValidations(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}
			autoTrayValidations.validateAutoTrayStatus("Auto: Begin Cancel SNAP Feature Disconnect", "Successful");
			webTopLogout();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verification of SNAP Auto Tray", "Verification of SNAP Auto Tray Failed!!!");
			String eMsg = "Verification of SNAP Auto Tray Failed!!!" + Ex.getMessage();
			log.error(eMsg);

		}
	}

}
