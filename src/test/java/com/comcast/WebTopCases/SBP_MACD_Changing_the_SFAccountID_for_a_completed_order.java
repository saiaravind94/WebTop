package com.comcast.WebTopCases;

import org.testng.annotations.Test;

import com.comcast.pages.webtop.AccountProfileTab;
import com.comcast.pages.webtop.BusinessCentralTab;
import com.comcast.pages.webtop.ExtendedMiscInfo;
import com.comcast.pages.webtop.WebTopLogInPage;
import com.comcast.pages.webtop.WebTopMainPage;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;

public class SBP_MACD_Changing_the_SFAccountID_for_a_completed_order extends DisconnectFlow {
	DataTable dataSet;
	String eMsg = "";
	
	
	@Test(priority = 97100, groups={"MACD", "All"})	
	public void verifyETSCCountAndPricingBundleBeforeMACD() throws Exception {
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		BusinessCentralTab businessCentralTab = new BusinessCentralTab(frameworkContext);
		AccountProfileTab accountProfileTab = new AccountProfileTab(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();				
			}
			webTopMainPage.searchOrder("Group ID");		
			businessCentralTab.verifyETSCCount("5");
			if(accountProfileTab.verifyBundle("1-9"))
			{
				report.reportPassEvent("Seat Based price tier bundle (1-9) is there before MACD as expected", "1-9 bundles is there as expected");
			}
			else
			{
				report.reportSoftFailEvent("Seat Based price tier bundle (1-9) is not there before MACD it is not expected", "1-9 bundles is not there and it is not expected");
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Bundle verification in Account profile page",
					"Bundle verification in Account profile pages failed!!!");
			String eMsg = "Bundle verification in Account profile page failed. --- " + Ex.getMessage();
			log.error(eMsg);
		}
	}
	
	@Test(priority = 97200, groups={"MACD", "All"})
	public void updateNewSFAccountID() throws Exception {
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		ExtendedMiscInfo extendedMiscInfo = new ExtendedMiscInfo(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchOrder("Group ID");
			}			
			extendedMiscInfo.updateSFAccountID();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Updating NEW SF Account ID", "Updating NEW SF Account ID Failed!!!",
					Status.FAIL);
			String eMsg = "Error in Updating NEW SF Account ID --- " + Ex.getMessage();
			log.error(eMsg);
		}
	}
	
	@Test(priority = 97300, groups={"MACD", "All"})	
	public void verifyETSCCountAndPricingBundleAfterMACD() throws Exception {
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		BusinessCentralTab businessCentralTab = new BusinessCentralTab(frameworkContext);
		AccountProfileTab accountProfileTab = new AccountProfileTab(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();				
			}
			webTopMainPage.searchOrder("Group ID");
			int ETSCCount = Integer.parseInt(businessCentralTab.getETSCCOunt());
			if(ETSCCount > 5)
			{
				report.reportPassEvent("ETSC Count verification in Account Information tab of Business central page", "ETCS Count: "+ETSCCount+" is displayed as expected");
			}
			else
			{
				report.reportSoftFailEvent("ETSC Count verification in Account Information tab of Business central page", "ETCS Count: "+ETSCCount+" is not displayed as expected");
			}			
			accountProfileTab.verifyBundleIfTerminated("1-9");			
		} catch (Exception Ex) {
			report.reportHardFailEvent("Bundle/ETSC verification in Account profile page",
					"Bundle/ETSC verification in Account profile pages failed!!!");
			String eMsg = "Bundle/ETSC verification in Account profile page failed. --- " + Ex.getMessage();
			log.error(eMsg);
		}
	}
	
	
}
