package com.comcast.WebTopCases;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.comcast.bcp.ActivateUser;
import com.comcast.bcp.BCPLogin;
import com.comcast.bcp.CreateBCPPortalUserPassword;
import com.comcast.bcp.GenerateUniqueChildBAN;
import com.comcast.bcp.WhiteListEmail;
import com.comcast.mail.GenerateUniqueEMail;
import com.comcast.mail.OpenMailInInbox;
import com.comcast.pages.broadsoft.BroadsoftGroupPage;
import com.comcast.pages.broadsoft.BroadsoftLogInPage;
import com.comcast.pages.webtop.AccountProfileTab;
import com.comcast.pages.webtop.ServicesPage;
import com.comcast.pages.webtop.WebTopLogInPage;
import com.comcast.pages.webtop.WebTopMainPage;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;

public class BCP_WT_MACD_Add_UC_seat_Provision_Identity_for_BCP_for_all_BVE extends Incremental_add_MACD_Support_four_new_phone_models_with_switch_and_Netx {
	DataTable dataSet;
	String eMsg = "";
	
	
	Logger log = Logger.getLogger(BCP_WT_MACD_Add_UC_seat_Provision_Identity_for_BCP_for_all_BVE.class);	
	
	@Test(priority = 29900, groups={"MACD", "All"})
	public void UniqueMailIdCreation() throws Exception {
		WebTopLogInPage.webTopLogedIn = false;
		try {
			GenerateUniqueEMail genaerateUniqueEMail = new GenerateUniqueEMail(frameworkContext);
			WhiteListEmail whiteListEmail = new WhiteListEmail(frameworkContext);
			genaerateUniqueEMail.generateOneUniqueEMail();
			whiteListEmail.whiteListOneEmail(dataDump.getValue("MACDSeatMailID"));			
		} catch (Exception Ex) {
			report.reportHardFailEvent("Unique Email ID creation for MACD Add Seat", "Unique Email ID creation for MACD Add Seat Failed" + Ex.getMessage());
			eMsg = "Unique Email ID creation for MACD Add Seat Failed!!! " + Ex.getMessage();

			log.error(eMsg);
		}
	}
	
	@Test(priority = 30000, groups={"MACD", "All"})	
	public void addNewUCSeatWithServiceUser() throws Exception {
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		AccountProfileTab accountProfileTab = new AccountProfileTab(frameworkContext);
		ServicesPage servicesPage = new ServicesPage(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
			}
			webTopMainPage.searchOrder("Group ID");			
			accountProfileTab.launchAddInventory();
			servicesPage.addServices("AddNewANI", "ANI");			
			servicesPage.addANI(dataDump.getValue("MACDSeatMailID"), "Service User");
			servicesPage.addItemOrBundle("Item", "OrderInvertoryANI", "BVEUCS-Unified Communications Seat | 0.00", 1);			
			accountProfileTab.submitTheOrder();
			dataDump.setValue("OrderCategory", "MACD");
			dataDump.setValue("OrderStatus", "In-Progress");
			dataDump.setValue("IsMACDPlaced", "Yes");			
		} catch (Exception Ex) {
			report.reportHardFailEvent("Adding New UC Seat ANI With Item",
					"Adding New UC Seat ANI With Item is Failed!!!", Status.FAIL);
			String eMsg = "Error in Adding New UC Seat ANI With Item --- " + Ex.getMessage();
			log.error(eMsg);
			
		}			
	}
	
	@Test(priority = 29250, groups={"MACD", "All"})	
	public void validateHGCountBeforeMACDInSwitch() throws Exception {
		BroadsoftGroupPage broadsoftGroupPage = new BroadsoftGroupPage(frameworkContext);
		try {
			if (!BroadsoftLogInPage.broadsoftLogedIn) {
				broadsoftLogin();
			}
			broadsoftGroupPage.searchGroups("Group ID", "Equal To", dataDump.getValue("GroupID"));			
			broadsoftGroupPage.getGroupServices("Hunt Group", "before");
		} catch (Exception Ex) {
			report.reportHardFailEvent("Broadsoft HG verification before MACD", "Broadsoft HG verification before MACD failed", Status.FAIL);
			String eMsg = "Error in Broadsoft HG verification before MACD failed" + Ex.getMessage();
			log.error(eMsg);
			
		}		
	}
	
	@Test(priority = 30000, groups={"MACD", "All"})	
	public void addNewHG() throws Exception {
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		AccountProfileTab accountProfileTab = new AccountProfileTab(frameworkContext);
		ServicesPage servicesPage = new ServicesPage(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
			}
			webTopMainPage.searchOrder("Group ID");			
			accountProfileTab.launchAddInventory();
			servicesPage.addServices("AddNewHG", "VoIP");
			servicesPage.addVoIPWithExtension();
			servicesPage.addItemOrBundle("Item", "VIP", "BVEADHG-Hunt Group | 0.00", 1);			
			accountProfileTab.submitTheOrder();
			dataDump.setValue("OrderCategory", "MACD");
			dataDump.setValue("OrderStatus", "In-Progress");
			dataDump.setValue("IsMACDPlaced", "Yes");			
		} catch (Exception Ex) {
			report.reportHardFailEvent("Adding New VoIP with HG Item",
					"Adding New VoIP with HG Item is Failed!!!", Status.FAIL);
			String eMsg = "Error in Adding New VoIP with HG Item --- " + Ex.getMessage();
			log.error(eMsg);
			
		}			
	}
	
	@Test(priority = 30210, groups={"MACD", "All"})	
	public void autoAdjustBundleBasedOnConfigurationItemsOnThisOrderInMACDFlow() throws Exception {
		autoAdjustBundleBasedOnConfigurationItemsOnThisOrder();
	}
	
	@Test(priority = 30220, groups={"MACD", "All"})	
	public void reviewMACDBillingImpactInMACDFlow() throws Exception {
		reviewMACDBillingImpact();
	}
	
	@Test(priority = 30230, groups={"MACD", "All"})	
	public void updatePrimaryContactDetailsInMACDFlow() throws Exception {
		updatePrimaryContactDetails();
	}	
	
	
	@Test(priority = 62100, groups={"MACD", "All"})
	public void verifyNewDevicesInSwitchFor311And411() throws Exception {		
		BroadsoftGroupPage broadsoftGroupPage = new BroadsoftGroupPage(frameworkContext);
		try {
			if (!BroadsoftLogInPage.broadsoftLogedIn) {
				broadsoftLogin();
			}
			broadsoftGroupPage.searchGroups("Group ID", "Equal To", dataDump.getValue("GroupID"));
			broadsoftGroupPage.verifyUsers("First Name", "Equal To", "FName", 2);			
		} catch (Exception Ex) {
			report.reportHardFailEvent("User verification Failed", "User verification Failed!!!", Status.FAIL);
			String eMsg = "Error in User verification  --- " + Ex.getMessage();
			log.error(eMsg);
		}
	}
	
	@Test(priority = 127500, groups={"MACD", "All"})
	public void validateHGCountAfterMACDInSwitch() throws Exception {		
		BroadsoftGroupPage broadsoftGroupPage = new BroadsoftGroupPage(frameworkContext);
		try {
			BroadsoftLogInPage.broadsoftLogedIn = false;
			if (!BroadsoftLogInPage.broadsoftLogedIn) {
				broadsoftLogin();
			}
			broadsoftGroupPage.searchGroups("Group ID", "Equal To", dataDump.getValue("GroupID"));			
			broadsoftGroupPage.getGroupServices("Hunt Group", "after");
			if(Integer.parseInt(dataDump.getValue("Hunt Group_limit_after")) - Integer.parseInt(dataDump.getValue("Hunt Group_limit_before")) == 1 &&
					Integer.parseInt(dataDump.getValue("Hunt Group_allowed_after")) - Integer.parseInt(dataDump.getValue("Hunt Group_allowed_before")) == 1 &&
					Integer.parseInt(dataDump.getValue("Hunt Group_inUse_after")) - Integer.parseInt(dataDump.getValue("Hunt Group_inUse_before")) == 1)
			{
				report.reportPassEvent("Broadsoft MACD HG ADD verification", "Broadsoft MACD HG ADD verification Passed", Status.PASS);
			}
			else
			{
				report.reportHardFailEvent("Broadsoft MACD HG ADD verification", "Broadsoft MACD HG ADD verification failed", Status.FAIL);
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Broadsoft MACD HG ADD verification", "Broadsoft MACD HG ADD verification failed", Status.FAIL);
			String eMsg = "Error in Broadsoft MACD HG ADD verification failed" + Ex.getMessage();
			log.error(eMsg);
			
		}
	}
	
}
