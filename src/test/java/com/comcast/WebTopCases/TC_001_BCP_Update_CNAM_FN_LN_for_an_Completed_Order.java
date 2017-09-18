package com.comcast.WebTopCases;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.comcast.bcp.ManagePhoneSettings;
import com.comcast.pages.broadsoft.BroadsoftGroupPage;
import com.comcast.pages.broadsoft.BroadsoftLogInPage;
import com.comcast.pages.webtop.ErrorProvisioningBroadworksCNAMUpdate;
import com.comcast.pages.webtop.ErrorUpdatingDADLCNAMAtNeustar;
import com.comcast.pages.webtop.UpdateDADLCNAMAtNeustarManually;
import com.comcast.pages.webtop.WebTopLogInPage;
import com.comcast.pages.webtop.WebTopMainPage;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;

public class TC_001_BCP_Update_CNAM_FN_LN_for_an_Completed_Order extends DisconnectFlow {
	DataTable dataSet;
	String eMsg = "";
	
	
	Logger log = Logger.getLogger(TC_001_BCP_Update_CNAM_FN_LN_for_an_Completed_Order.class);
	
	@Test(priority = 30500, groups={"MACD", "All"})	
	public void updateCNameFNameAndLNameInBCPPortal() throws Exception {
		WebTopLogInPage.webTopLogedIn = false;		
		ManagePhoneSettings managePhoneSettings = new ManagePhoneSettings(frameworkContext);
		String emailIDOfUserToUpdateCName = dataDump.getValue("MailIdList").split("\\-")[dataDump.getValue("MailIdList").split("\\-").length - 1];
		dataDump.setValue("NewCNameFName", "NewCNameFName");		
		String NewCNameLName = (new SimpleDateFormat("YYMMddhhmmss").format(new Date()));
		dataDump.setValue("NewCNameLName", "LName_" + NewCNameLName);		
		try {			
			managePhoneSettings.updateCNAMFields(emailIDOfUserToUpdateCName);
			dataDump.setValue("OrderCategory", "MACD");
			dataDump.setValue("OrderStatus", "In-Progress");
			dataDump.setValue("IsMACDPlaced", "Yes");			
		} catch (Exception Ex) {
			report.reportHardFailEvent("CName Update", "CName Update failed");
			String eMsg = "Error in CName update in BCP Portal --- " + Ex.getMessage();
			log.error(eMsg);			
		}			
	}
	
	@Test(priority = 31000, groups={"MACD", "All"})	
	public void errorProvisioningBroadworksCNAMUpdate() throws Exception {
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		ErrorProvisioningBroadworksCNAMUpdate errorProvisioningBroadworksCNAMUpdate = new ErrorProvisioningBroadworksCNAMUpdate(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}
			errorProvisioningBroadworksCNAMUpdate.errorProvisioningBroadworksCNAMUpdate();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Error Provisioning Broadworks CNAM Update",
					"Error Provisioning Broadworks CNAM Update Failed!!!", Status.FAIL);
			String eMsg = "Error Provisioning Broadworks CNAM Update --- " + Ex.getMessage();
			log.error(eMsg);
		}	
	}
	
	@Test(priority = 31500, groups={"MACD", "All"})	
	public void errorUpdatingDADLCNAMAtNeustar() throws Exception {
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		ErrorUpdatingDADLCNAMAtNeustar errorUpdatingDADLCNAMAtNeustar = new ErrorUpdatingDADLCNAMAtNeustar(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}
			errorUpdatingDADLCNAMAtNeustar.errorUpdatingDADLCNAMAtNeustar();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Error Updating DA/DL/CNAM at Neustar",
					"Error Updating DA/DL/CNAM at Neustar Failed!!!", Status.FAIL);
			String eMsg = "Error Updating DA/DL/CNAM at Neustar --- " + Ex.getMessage();
			log.error(eMsg);
		}	
	}
	

	@Test(priority = 32000, groups={"MACD", "All"})	
	public void updateDADLCNAMAtNeustarManually() throws Exception {
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		UpdateDADLCNAMAtNeustarManually updateDADLCNAMAtNeustarManually = new UpdateDADLCNAMAtNeustarManually(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}
			updateDADLCNAMAtNeustarManually.updateDADLCNAMAtNeustarManually();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Error Updating DA/DL/CNAM at Neustar Manually",
					"Error Updating DA/DL/CNAM at Neustar Manually Failed!!!", Status.FAIL);
			String eMsg = "Error Updating DA/DL/CNAM at Neustar Manually--- " + Ex.getMessage();
			log.error(eMsg);
		}	
	}
	
	@Test(priority = 32500, groups={"MACD", "All"})	
	public void verifyCNameUpdateInSwitch() throws Exception {
		BroadsoftGroupPage broadsoftGroupPage = new BroadsoftGroupPage(frameworkContext);
		String CNameSeatToVerify = dataDump.getValue("SeatNameToUpdateCNAM");
		try {
			if (!BroadsoftLogInPage.broadsoftLogedIn) {
				broadsoftLogin();
			}
			broadsoftGroupPage.searchGroups("Group ID", "Equal To", dataDump.getValue("GroupID"));
			broadsoftGroupPage.verifyUsers("First Name", "Contains", CNameSeatToVerify, 1);
			broadsoftGroupPage.verifyCNameForTheUser(CNameSeatToVerify);			
			dataDump.setValue("OrderStatus", "Completed");			
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verify CName update for the User: " + CNameSeatToVerify, "Verify CName update for the User is failed",
					Status.FAIL);
			String eMsg = "Error in Verify CName update for the User is failed" + Ex.getMessage();
			log.error(eMsg);
		}	
	}
	
	
	
}
