package com.comcast.WebTopCases;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.comcast.pages.simon.AA_BusinessHours;
import com.comcast.pages.simon.SIMONMainPage;
import com.comcast.pages.simon.SeatConfiguration;
import com.comcast.pages.simon.SimonLogInPage;
import com.comcast.pages.webtop.AccountProfileTab;
import com.comcast.pages.webtop.BusinessCentralTab;
import com.comcast.pages.webtop.ChooseActionForGroupCallCapacityQuantity;
import com.comcast.pages.webtop.ErrorReservingNativeTNs;
import com.comcast.pages.webtop.PerformBillingUpdate;
import com.comcast.pages.webtop.ScheduleDOIOrDOC;
import com.comcast.pages.webtop.ServiceOrderInitiatedViaSQL;
import com.comcast.pages.webtop.ServicesPage;
import com.comcast.pages.webtop.UpdatePriceTier;
import com.comcast.pages.webtop.ValidationErrorsFoundonSOAOrder;
import com.comcast.pages.webtop.WebTopLogInPage;
import com.comcast.pages.webtop.WebTopMainPage;
import com.comcast.pages.webtop.WorkOrderComments;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.PageBase;

public class SBP_NC_Multi_site_Addition_of_Site_to_an_existing_completed_Site extends SBP_MACD_Teleworker_Site_Partial_Disconnect_UC_seat {
	DataTable dataSet;
	String eMsg = "";
	
	
	Logger log = Logger.getLogger(SBP_NC_Multi_site_Addition_of_Site_to_an_existing_completed_Site.class);
	
	@Test(priority = 28600, groups={"New Connect", "All"})	
	public void verifyETSCCountAndPricingBundleNRCMRCValueForSite1() throws Exception {
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		BusinessCentralTab businessCentralTab = new BusinessCentralTab(frameworkContext);
		AccountProfileTab accountProfileTab = new AccountProfileTab(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();				
			}
			webTopMainPage.searchOrder("Group ID");		
			businessCentralTab.verifyETSCCount("3");
			accountProfileTab.verifyBundleMRCAndNRCValue("1-9", "34.95", "29.95");
		} catch (Exception Ex) {			
			log.error("NRC MRC values verification failed in Account profile Page");
			report.reportSoftFailEvent("Bundle: 1-9 MRC and NRC verification in Account profile page",
					"Bundle:1-9 MRC/NRC Value verification failed");			
		}
	}
	
	@Test(priority = 46000, groups={"New Connect", "All"})	
	public void verifyETSCCountAndPricingBundleNRCMRCValueForSite2() throws Exception {
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		BusinessCentralTab businessCentralTab = new BusinessCentralTab(frameworkContext);
		AccountProfileTab accountProfileTab = new AccountProfileTab(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();				
			}
			webTopMainPage.searchOrder("Group ID");		
			businessCentralTab.verifyETSCCount("20");			
			accountProfileTab.verifyBundleMRCAndNRCValue("10-19", "34.95", "29.95");
			accountProfileTab.verifyBundleMRCAndNRCValue("20+", "29.95", "29.95");
		} catch (Exception Ex) {			
			log.error("NRC MRC values verification failed in Account profile Page");
			report.reportSoftFailEvent("Bundle: 1-9 MRC and NRC verification in Account profile page",
					"Bundle:1-9 MRC/NRC Value verification failed");			
		}
	}	
	
	@Test(priority = 46050, groups={"New Connect", "All"})	
	public void updateDumpValuesAfterSecondarySiteCompleted() throws Exception {
		dataDump.setValue("SFOpportunityId_Site2", dataDump.getValue("SFOpportunityId"));
		dataDump.setValue("GroupID_Site2", dataDump.getValue("GroupID"));
		dataDump.setValue("OrderID_RT_Site2", dataDump.getValue("OrderID_RT"));
		dataDump.setValue("ChildAccountID_Site2", dataDump.getValue("ChildAccountID"));
		dataDump.setValue("SFOpportunityId", dataDump.getValue("SFOpportunityId_Site1"));
		dataDump.setValue("GroupID", dataDump.getValue("GroupID_Site1"));
		dataDump.setValue("OrderID_RT", dataDump.getValue("OrderID_RT_Site1"));			
	}
	
	@Test(priority = 46100, groups={"MACD", "All"})
	public void serviceOrderInitiatedViaSQL() throws Exception {
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		ServiceOrderInitiatedViaSQL serviceOrderInitiatedViaSQL = new ServiceOrderInitiatedViaSQL(
				frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();				
			}
			webTopMainPage.searchFor("Group ID");
			serviceOrderInitiatedViaSQL.serviceOrderInitiatedViaSQL();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Service Order Initiated Via SQL (will close automatically)", "Service Order Initiated Via SQL (will close automatically) Failed!!!",
					Status.FAIL);
			String eMsg = "Error in Service Order Initiated Via SQL (will close automatically) --- " + Ex.getMessage();
			log.error(eMsg);
		}
	}
	
	@Test(priority = 46150, groups={"MACD", "All"})
	public void updatePriceTier() throws Exception {
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		UpdatePriceTier updatePriceTier = new UpdatePriceTier(
				frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();				
			}
			webTopMainPage.searchFor("Group ID");
			updatePriceTier.updatePriceTier();
		} catch (Exception Ex) {
			report.reportHardFailEvent("BILLING: Update Price Tier", "BILLING: Update Price Tier Failed!!!",
					Status.FAIL);
			String eMsg = "Error in BILLING: Update Price Tier --- " + Ex.getMessage();
			log.error(eMsg);
		}
	}
	
	
	
	/*
	@Test(priority = 46100, groups={"MACD", "All"})	
	public void addNewUCSeatWithBundle() throws Exception {
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
			servicesPage.addItemOrBundle("Item", "OrderInvertoryANI", "BVEUCS-Unified Communications Seat | 0.00");
			servicesPage.addItemOrBundle("Bundle", "OrderInvertoryANI", "BVEUC10-19-Seat Based Unified Communication Seats 10-19 | 39.95");
			accountProfileTab.submitTheOrder();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Adding New UC Seat ANI With Bundle",
					"Adding New UC Seat ANI With Bundle is Failed!!!", Status.FAIL);
			String eMsg = "Error in Adding New UC Seat ANI With Bundle --- " + Ex.getMessage();
			log.error(eMsg);
			
		}			
	}
	
	@Test(priority = 71100, groups={"MACD", "All"})
	public void validationErrorsFoundonSDWOrder() throws Exception {		
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		ValidationErrorsFoundonSOAOrder validationErrorsFoundonSOAOrder = new ValidationErrorsFoundonSOAOrder(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}
			validationErrorsFoundonSOAOrder.validationPromotionalDiscount("34.95");
		} catch (Exception Ex) {
			report.reportHardFailEvent("Retry Validating SDW Order bundle price",
					"Retry Validating SDW Order bundle price Failed!!!", Status.FAIL);
			String eMsg = "Error in Retry Validating SDW Order --- "
					+ Ex.getMessage();
			log.error(eMsg);
			
		}
	}
	
	@Test(priority = 71200, groups={"MACD", "All"})
	public void chooseActionForGroupCallCapacityQuantity() throws Exception {
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		ChooseActionForGroupCallCapacityQuantity chooseActionForGroupCallCapacityQuantity = new ChooseActionForGroupCallCapacityQuantity(
				frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}
			chooseActionForGroupCallCapacityQuantity.chooseActionForGroupCallCapacityQuantity();
		} catch (Exception Ex) {
			report.reportHardFailEvent("CARE: Choose action for group Call Capacity quantity ", "CARE: Choose action for group Call Capacity quantity  Failed!!!",
					Status.FAIL);
			String eMsg = "Error in CARE: Choose action for group Call Capacity quantity  --- " + Ex.getMessage();
			log.error(eMsg);
		}
	}
		
	@Test(priority = 92800, groups={"MACD", "All"})
	public void scheduleDOIOrDOCForNewSeat() throws Exception {
		scheduleDOIOrDOC_MACDFlow();
	}
	
	
	@Test(priority = 97000, groups={"MACD", "All"})	
	public void verifyETSCCountAndPricingBundleForSite1() throws Exception {
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		WorkOrderComments workOrderComments = new WorkOrderComments(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}			
			workOrderComments.ETSCCountVerification("10");			
		} catch (Exception Ex) {
			report.reportHardFailEvent("ETSC Count  verification from work order comments",
					"ETSC Count  verification from work order comments failed!!!");
			String eMsg = "ETSC Count  verification from work order comments failed. --- " + Ex.getMessage();
			log.error(eMsg);
		}
	}
	
	@Test(priority = 97100, groups={"MACD", "All"})	
	public void verifyETSCCountAndPricingBundle() throws Exception {
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		BusinessCentralTab businessCentralTab = new BusinessCentralTab(frameworkContext);
		AccountProfileTab accountProfileTab = new AccountProfileTab(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();				
			}
			webTopMainPage.searchOrder("Group ID");		
			businessCentralTab.verifyETSCCount("10");
			accountProfileTab.verifyBundle("10-19");
		} catch (Exception Ex) {
			report.reportHardFailEvent("Bundle verification in Account profile page",
					"Bundle verification in Account profile pages failed!!!");
			String eMsg = "Bundle verification in Account profile page failed. --- " + Ex.getMessage();
			log.error(eMsg);
		}
	}
	
	@Test(priority = 97100, groups={"MACD", "All"})	
	public void updateDumpValuesAfterMACD() throws Exception {
		dataDump.setValue("SFOpportunityId", dataDump.getValue("SFOpportunityId_Site2"));
		dataDump.setValue("GroupID", dataDump.getValue("GroupID_Site2"));
		dataDump.setValue("OrderID_RT", dataDump.getValue("OrderID_RT_Site2"));
		dataDump.setValue("ChildAccountID", dataDump.getValue("ChildAccountID_Site2"));
	}

	@Test(priority = 97200, groups={"MACD", "All"})
	public void serviceOrderInitiatedViaSQL() throws Exception {
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		ServiceOrderInitiatedViaSQL serviceOrderInitiatedViaSQL = new ServiceOrderInitiatedViaSQL(
				frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();				
			}
			webTopMainPage.searchFor("Group ID");
			serviceOrderInitiatedViaSQL.serviceOrderInitiatedViaSQL();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Service Order Initiated Via SQL (will close automatically)", "Service Order Initiated Via SQL (will close automatically) Failed!!!",
					Status.FAIL);
			String eMsg = "Error in Service Order Initiated Via SQL (will close automatically) --- " + Ex.getMessage();
			log.error(eMsg);
		}
	}
	
	@Test(priority = 97300, groups={"MACD", "All"})
	public void updatePriceTier() throws Exception {
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		UpdatePriceTier updatePriceTier = new UpdatePriceTier(
				frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();				
			}
			webTopMainPage.searchFor("Group ID");
			updatePriceTier.updatePriceTier();
		} catch (Exception Ex) {
			report.reportHardFailEvent("BILLING: Update Price Tier", "BILLING: Update Price Tier Failed!!!",
					Status.FAIL);
			String eMsg = "Error in BILLING: Update Price Tier --- " + Ex.getMessage();
			log.error(eMsg);
		}
	}
	
	@Test(priority = 97300, groups={"New Connect", "All"})	
	public void verifyETSCCountAndPricingBundleForSite2() throws Exception {
		verifyETSCCountAndPricingBundle();
	}*/
}
