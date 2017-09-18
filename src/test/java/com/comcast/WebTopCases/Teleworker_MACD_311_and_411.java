package com.comcast.WebTopCases;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.comcast.pages.broadsoft.BroadsoftGroupPage;
import com.comcast.pages.broadsoft.BroadsoftLogInPage;
import com.comcast.pages.webtop.AccountProfileTab;
import com.comcast.pages.webtop.ServicesPage;
import com.comcast.pages.webtop.WebTopLogInPage;
import com.comcast.pages.webtop.WebTopMainPage;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;

public class Teleworker_MACD_311_and_411 extends Incremental_add_MACD_Support_four_new_phone_models_with_switch_and_Netx {
	DataTable dataSet;
	String eMsg = "";
	
	
	Logger log = Logger.getLogger(Teleworker_MACD_311_and_411.class);	
	
	@Test(priority = 30000, groups={"MACD", "All"})	
	public void addNewTWUCSeatWithBundleFor311() throws Exception {
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
			servicesPage.addANI();
			servicesPage.addItemOrBundle("Item", "OrderInvertoryANI", "BVEUCSTW-TW Unified Communications Seat | 0.00", 1);
			servicesPage.addItemOrBundle("Item", "OrderInvertoryANI", "TWBVE311-TW Polycom VVX 311 HD | 0.00", 1);
			accountProfileTab.submitTheOrder();
			dataDump.setValue("OrderCategory", "MACD");
			dataDump.setValue("OrderStatus", "In-Progress");
			dataDump.setValue("IsMACDPlaced", "Yes");			
		} catch (Exception Ex) {
			report.reportHardFailEvent("Adding New UC Seat ANI With Bundle",
					"Adding New UC Seat ANI With Bundle is Failed!!!", Status.FAIL);
			String eMsg = "Error in Adding New UC Seat ANI With Bundle --- " + Ex.getMessage();
			log.error(eMsg);
			
		}			
	}
	
	@Test(priority = 40000, groups={"MACD", "All"})	
	public void addNewTWUCSeatWithBundleFor411() throws Exception {
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
			servicesPage.addANI();
			servicesPage.addItemOrBundle("Item", "OrderInvertoryANI", "BVEUCSTW-TW Unified Communications Seat | 0.00", 1);
			servicesPage.addItemOrBundle("Item", "OrderInvertoryANI", "TWBVE411-TW Polycom VVX 411 HD | 0.00", 1);
			accountProfileTab.submitTheOrder();
			dataDump.setValue("OrderCategory", "MACD");
			dataDump.setValue("OrderStatus", "In-Progress");
			dataDump.setValue("IsMACDPlaced", "Yes");			
		} catch (Exception Ex) {
			report.reportHardFailEvent("Adding New UC Seat ANI With Bundle",
					"Adding New UC Seat ANI With Bundle is Failed!!!", Status.FAIL);
			String eMsg = "Error in Adding New UC Seat ANI With Bundle --- " + Ex.getMessage();
			log.error(eMsg);
			
		}			
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
	
}
