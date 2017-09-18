package com.comcast.WebTopCases;

import org.testng.annotations.Test;

import com.comcast.pages.webtop.AccountProfileTab;
import com.comcast.pages.webtop.BusinessCentralTab;
import com.comcast.pages.webtop.UserDefinedReports;
import com.comcast.pages.webtop.WebTopLogInPage;
import com.comcast.pages.webtop.WebTopMainPage;
import com.comcast.reporting.Status;

public class SBP_MACD_User_Type_Change_Admin_To_User
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
	public void updateAdminUserToStandardUser() throws Exception {		
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		AccountProfileTab accountProfileTab = new AccountProfileTab(frameworkContext);		
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
			}
			webTopMainPage.searchOrder("Group ID");
			accountProfileTab.getANIList("before");
			String BTNNo = accountProfileTab.getBTNNo();
			//Get the ANI number and index of the ANI which is linked to BTN ANI(Admin user ANI)
			int adminUserToStdUserConvertIdx = 0;
			for(int idx = 0; idx<dataDump.getValue("UCANIList_before").split("\\|").length;idx++)
			{
				if(BTNNo.equalsIgnoreCase(dataDump.getValue("UCANIList_before").split("\\|")[idx])){
					dataDump.setValue("adminUserToStdUserConvert", dataDump.getValue("UCANIList_before").split("\\|")[idx]);
					adminUserToStdUserConvertIdx = idx;
					break;
				}				
			}			
			accountProfileTab.editSeat("UC", adminUserToStdUserConvertIdx + 1);			
			accountProfileTab.changeUser("Standard");					
		} catch (Exception Ex) {
			report.reportHardFailEvent("Updating admin user to non adnin user",
					"Updating admin user to non adnin user Failed!!!", Status.FAIL);
			String eMsg = "Error in Updating admin user to non adnin user --- " + Ex.getMessage();
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
				report.reportPassEvent("Verify EA/TA users is reflected in the user defined reports in webtop after converting TA to Standard User", "EA/TA User is reflected in the user defined reports after converting TA to Standard User in WebTop!!!", Status.PASS);
			}
			else
			{
				report.reportHardFailEvent("Verify EA/TA users is reflected in the user defined reports in webtop after converting TA to Standard User", "EA/TA User is not reflected in the user defined reports after converting TA to Standard User in WebTop !!!", Status.FAIL);
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verify EA/TA users is reflected in the user defined report", "Verify EA/TA users is not reflected in the user defined report  after converting TA to Standard User is Failed!!!", Status.FAIL);
			String eMsg = "Error in Verify EA/TA users is reflected in the user defined report --- " + Ex.getMessage();
			log.error(eMsg);			
		}
	}
	
	
	
	
	
}
