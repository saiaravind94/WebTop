package com.comcast.WebTopCases;

import org.testng.annotations.Test;

import com.comcast.pages.broadsoft.BroadsoftGroupPage;
import com.comcast.pages.broadsoft.BroadsoftLogInPage;
import com.comcast.pages.webtop.AccountProfileTab;
import com.comcast.pages.webtop.AttachDocumentsToDisconnectOrder;
import com.comcast.pages.webtop.BusinessCentralTab;
import com.comcast.pages.webtop.EnterDOIForDeviceSwap;
import com.comcast.pages.webtop.ErrorDeterminingOrderType;
import com.comcast.pages.webtop.ErrorValidatingOrderBeforeSendingToSNAP;
import com.comcast.pages.webtop.OnDOI_ContinueToProvisioningOfDeviceSwap;
import com.comcast.pages.webtop.OrderNewDeviceOrGenerateRMAIfNeeded;
import com.comcast.pages.webtop.ServicesPage;
import com.comcast.pages.webtop.UpdateBillingForDeviceSwapOrder;
import com.comcast.pages.webtop.UpdateBillingForTNDisconnectOrder;
import com.comcast.pages.webtop.UserDefinedReports;
import com.comcast.pages.webtop.WebTopLogInPage;
import com.comcast.pages.webtop.WebTopMainPage;
import com.comcast.reporting.Status;

public class SBP_MACD_Remove_Enterprise_Admin_Only
		extends DisconnectFlow {
	
	@Test(priority = 70000, groups={"MACD", "All"})
	public void verifyAdminReportsBeforeMACD() throws Exception {		
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		BusinessCentralTab businessCentralTab = new BusinessCentralTab(frameworkContext);
		UserDefinedReports userDefinedReports = new UserDefinedReports(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
			}
			webTopMainPage.searchOrder("Group ID");
			businessCentralTab.launchReport("List Tech and Enterprise Admins");
			userDefinedReports.getRows("Before");
			if(dataDump.getValue("TechAndEABefore_MACD").equals("3")){
				log.info("Existing EA and Tech Users are reflected in the user defined reports in WebTop");
				report.reportPassEvent("Verify existing EA and Tech users is reflected in the user defined reports in webtop", "Existing EA and Tech Users are reflected in the user defined reports in WebTop!!!", Status.PASS);
			}
			else
			{
				report.reportHardFailEvent("Verify existing EA and Tech users is reflected in the user defined reports in webtop", "Existing EA and Tech Users are not reflected in the user defined reports in WebTop!!!", Status.FAIL);
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("verify existing EA and Tech users is reflected in the user defined reports in webtop", "verify existing EA and Tech users is reflected in the user defined reports in webtop is Failed!!!", Status.FAIL);
			String eMsg = "Error in verify existing EA and Tech users is reflected in the user defined reports in webtop --- " + Ex.getMessage();
			log.error(eMsg);					
		}
	}
	
	@Test(priority = 70500, groups={"MACD", "All"})
	public void removeEAOnly() throws Exception {		
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		AccountProfileTab accountProfileTab = new AccountProfileTab(frameworkContext);		
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
			}
			webTopMainPage.searchOrder("Group ID");
			accountProfileTab.removeEAOnly();			
		} catch (Exception Ex) {
			report.reportHardFailEvent("Removing EAO User",
					"Removing EAO User Failed!!!", Status.FAIL);
			String eMsg = "Error in Removing EAO User --- " + Ex.getMessage();
			log.error(eMsg);			
		}
	}
	
	@Test(priority = 72000, groups={"MACD", "All"})
	public void verifyAdminReportsAfterMACD() throws Exception {		
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		BusinessCentralTab businessCentralTab = new BusinessCentralTab(frameworkContext);
		UserDefinedReports userDefinedReports = new UserDefinedReports(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
			}
			webTopMainPage.searchOrder("Group ID");
			businessCentralTab.launchReport("List Tech and Enterprise Admins");
			userDefinedReports.getRows("After");
			if(dataDump.getValue("TechAndEAAfter_MACD").equals("2")){
				report.reportPassEvent("Verify removed EA users is reflected in the user defined reports in webtop", "Removed EA User is reflected in the user defined reports in WebTop!!!", Status.PASS);
			}
			else
			{
				report.reportHardFailEvent("Verify removed EA users is reflected in the user defined reports in webtop", "Removed EA User is not reflected in the user defined reports in WebTop!!!", Status.FAIL);
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verify removed EA users is reflected in the user defined report", "Verify removed EA users is reflected in the user defined report is Failed!!!", Status.FAIL);
			String eMsg = "Error in Verify removed EA users is reflected in the user defined report --- " + Ex.getMessage();
			log.error(eMsg);			
		}
	}
	
	
	
	
	
}
