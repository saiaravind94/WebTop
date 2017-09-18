package com.comcast.WebTopCases;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.comcast.pages.webtop.AccountProfileTab;
import com.comcast.pages.webtop.CAREE911UnexpectedErrorsonOrder;
import com.comcast.pages.webtop.PerformBillingUpdate;
import com.comcast.pages.webtop.WebTopLogInPage;
import com.comcast.pages.webtop.WebTopMainPage;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;

public class LBP_MACD_Batch_Update_few_ANI_with_different_e911_address extends MACDFlow {
	DataTable dataSet;
	String eMsg = "";
	

	Logger log = Logger.getLogger(LBP_MACD_Batch_Update_few_ANI_with_different_e911_address.class);

	@Test(priority = 70000, groups={"MACD", "All"})
	public void getE911AddressBeforePlacingMACD() throws Exception {		
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		AccountProfileTab accountProfileTab = new AccountProfileTab(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
			}
			webTopMainPage.searchOrder("Group ID");
			accountProfileTab.getE911Address("before");
		} catch (Exception Ex) {
			report.reportHardFailEvent("Get E-911 address for all ANI before MACD",
					"Get E-911 address for all ANI before MACD Failed!!!", Status.FAIL);
			String eMsg = "Error in Getting E-911 address for all ANI before MACD --- " + Ex.getMessage();
			log.error(eMsg);
			
		}
	}

	@Test(priority = 70500, groups={"MACD", "All"})
	public void bulkUpdateE911Address() throws Exception {		
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		AccountProfileTab accountProfileTab = new AccountProfileTab(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
			}			
			webTopMainPage.searchOrder("Group ID");
			accountProfileTab.batchUpdateE911Address("3");
		} catch (Exception Ex) {
			report.reportHardFailEvent("Batch E-911 address update", "Batch E-911 address update Failed!!!", Status.FAIL);
			String eMsg = "Error in Batch E-911 address update--- " + Ex.getMessage();
			log.error(eMsg);
			
		}
	}

	@Test(priority = 71000, groups={"MACD", "All"})
	public void cAREE911UnexpectedErrorsonOrder() throws Exception {		
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		CAREE911UnexpectedErrorsonOrder cAREE911UnexpectedErrorsonOrder = new CAREE911UnexpectedErrorsonOrder(
				frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
			}
			dataDump.setValue("OrderStatus", "In-Progress");
			dataDump.setValue("IsMACDPlaced", "Yes");
			dataDump.setValue("OrderCategory", "MACD");
			webTopMainPage.searchOrder("Group ID");
			webTopMainPage.searchFor("Group ID");
			cAREE911UnexpectedErrorsonOrder.cAREE911UnexpectedErrorsonOrder();
		} catch (Exception Ex) {
			report.reportHardFailEvent("E-911 Unexpected Errors on Order", "E-911 Unexpected Errors on Order Failed!!!",
					Status.FAIL);
			String eMsg = "Error in E-911 Unexpected Errors on Order --- " + Ex.getMessage();
			log.error(eMsg);
			
		}
	}

	@Test(priority = 71500, groups={"MACD", "All"})
	public void getE911AddressAfterPlacingMACD() throws Exception {		
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		AccountProfileTab accountProfileTab = new AccountProfileTab(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
			}
			webTopMainPage.searchOrder("Group ID");
			accountProfileTab.getE911Address("after");
		} catch (Exception Ex) {
			report.reportHardFailEvent("Get E-911 address for all ANI After MACD",
					"Get E-911 address for all ANI after MACD Failed!!!", Status.FAIL);
			String eMsg = "Error in Getting E-911 address for all ANI After MACD --- " + Ex.getMessage();
			log.error(eMsg);
			
		}
	}

	@Test(priority = 72000, groups={"MACD", "All"})
	public void verifyE911AddressAfterbulkUpdate() throws Exception {
		AccountProfileTab accountProfileTab = new AccountProfileTab(frameworkContext);
		accountProfileTab.verifyE911AddressAfterBulkUpdate();
		dataDump.setValue("OrderCategory", "MACD");
		dataDump.setValue("OrderStatus", "Completed");		
	}

}
