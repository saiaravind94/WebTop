package com.comcast.WebTopCases;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.comcast.pages.simon.AA_BusinessHours;
import com.comcast.pages.simon.SIMONMainPage;
import com.comcast.pages.simon.SeatConfiguration;
import com.comcast.pages.simon.SimonLogInPage;
import com.comcast.pages.webtop.AccountProfileTab;
import com.comcast.pages.webtop.BusinessCentralTab;
import com.comcast.pages.webtop.ErrorDeterminingOrderType;
import com.comcast.pages.webtop.PerformBillingUpdate;
import com.comcast.pages.webtop.ServicesPage;
import com.comcast.pages.webtop.WebTopLogInPage;
import com.comcast.pages.webtop.WebTopMainPage;
import com.comcast.pages.webtop.WorkOrderComments;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.PageBase;

public class SBP_MACD_Addition_Of_Seat_To_An_In_Flight_Order extends MultiSiteFlow {
	DataTable dataSet;
	String eMsg = "";
	
	
	Logger log = Logger.getLogger(SBP_MACD_Addition_Of_Seat_To_An_In_Flight_Order.class);
	
	
	@Test(priority = 5010, groups={"New Connect", "All"})	
	public void updateDumpValuesAfterPrimarySiteSOAFlowCompleted() throws Exception {
		dataDump.setValue("SFOpportunityId_Site1", dataDump.getValue("SFOpportunityId"));
		dataDump.setValue("GroupID_Site1", dataDump.getValue("GroupID"));
		dataDump.setValue("OrderID_RT_Site1", dataDump.getValue("OrderID_RT"));
		dataDump.setValue("ChildAccountID_Site1", dataDump.getValue("ChildAccountID"));
		dataDump.setValue("SFOpportunityId", Integer.toString((Integer.parseInt(dataDump.getValue("SFOpportunityId")) + 1)));
		dataDump.setValue("GroupID", "");
		dataDump.setValue("OrderID_RT", "");			
	}
	@Test(priority = 5020, groups={"New Connect", "All"})
	
	public void SOA_Or_SDW_Required_MSFlow() throws Exception {		
		SOA_Or_SDW_Required();
	}

	
	@Test(priority = 5030, groups={"New Connect", "All"})
	public void prequalCheckList_MSFlow() throws Exception {
		prequalCheckList();
	}

	@Test(priority = 5040, groups={"New Connect", "All"})
	public void reviewOrderForAccuracyAndAcceptance_MSFlow() throws Exception {
		reviewOrderForAccuracyAndAcceptance();
	}

	@Test(priority = 5050, groups={"New Connect", "All"})
	public void SDPMCompleteDesignReview_MSFlow() throws Exception {
		SDPMCompleteDesignReview();
	}
	
	@Test(priority = 5055, groups={"New Connect", "All"})
	public void reviewAndCorrectSFID_MSFlow() throws Exception {
		reviewAndCorrectSFID();
	}

	@Test(priority = 5060, groups={"New Connect", "All"})
	public void createAndEnterDSTOrDSGAccountNumber_MSFlow() throws Exception {
		createAndEnterDSTOrDSGAccountNumber();
	}
	
	@Test(priority = 5070, groups={"New Connect", "All"})
	public void errorValidatingCSGOrDSTFormData_MSFlow() throws Exception {
		errorValidatingCSGOrDSTFormData();
	}
	
	

	@Test(priority = 5090, groups={"New Connect", "All"})
	public void scheduleDOIOrDOC_MSFlow() throws Exception {
		scheduleDOIOrDOC();
	}

	@Test(priority = 5100, groups={"New Connect", "All"})
	public void SOAOrderComplete_MSFlow() throws Exception {
		SOAOrderComplete();
	}

	@Test(priority = 5110, groups={"New Connect", "All"})	
	public void updateDumpValuesAfterSecondarySiteSOAFlowCompleted() throws Exception {
		dataDump.setValue("SFOpportunityId_Site2", dataDump.getValue("SFOpportunityId"));
		dataDump.setValue("GroupID_Site2", dataDump.getValue("GroupID"));
		dataDump.setValue("OrderID_RT_Site2", dataDump.getValue("OrderID_RT"));
		dataDump.setValue("ChildAccountID_Site2", dataDump.getValue("ChildAccountID"));
		dataDump.setValue("SFOpportunityId", dataDump.getValue("SFOpportunityId_Site1"));
		dataDump.setValue("GroupID", dataDump.getValue("GroupID_Site1"));
		dataDump.setValue("OrderID_RT", dataDump.getValue("OrderID_RT_Site1"));
		dataDump.setValue("ChildAccountID", dataDump.getValue("ChildAccountID_Site1"));
	}
	
	@Test(priority = 5120, groups={"New Connect", "All"})
	public void webTopLogout_MSFlow() throws Exception {
		webTopLogout();
	}
	
	@Test(priority = 10600, groups={"New Connect", "All"})
	public void addSeats() throws Exception {

		try {
			SIMONMainPage simonMainPage = new SIMONMainPage(frameworkContext);
			SeatConfiguration seatConfiguration = new SeatConfiguration(frameworkContext);
			com.comcast.pages.simon.TNManagement tnManagement = new com.comcast.pages.simon.TNManagement(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();				
			}
			simonMainPage.SFOpportunityIDSearch();
			seatConfiguration.addSeat("UC Seat", "Standard User", "UCSeat006", "LN", "Polycom VVX 410 HD");
			tnManagement.addNewTN("Inventory TN", "New", "UCSeat006");
			seatConfiguration.addSeat("UC Seat", "Standard User", "UCSeat007", "LN", "Polycom VVX 410 HD");
			tnManagement.addNewTN("Inventory TN", "New", "UCSeat007");
		} catch (Exception Ex) {
			report.reportHardFailEvent("Adding Seat: UC Seat", "Adding seat Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Adding Seat: UC Seat in SIMON Failed!!! " + Ex.getMessage();			
			log.error(eMsg);
		}
	}
	
	
	@Test(priority = 40810, groups={"New Connect", "All"})	
	public void add2NewBundles() throws Exception {
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		AccountProfileTab accountProfileTab = new AccountProfileTab(frameworkContext);
		ServicesPage servicesPage = new ServicesPage(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
			}
			webTopMainPage.searchOrder("Group ID");			
			accountProfileTab.launchAddInventory();
			servicesPage.addServices("Add2NewBundles", "");			
			servicesPage.addItemOrBundle("Bundle", "CustomerLevel", "BVEUC10-19-Seat Based Unified Communication Seats 10-19 | 39.95", 2);
			accountProfileTab.submitTheOrder();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Adding 2 New Bundles",
					"Adding 2 New Bundles is Failed!!!", Status.FAIL);
			String eMsg = "Error in Adding 2 New Bundles --- " + Ex.getMessage();
			log.error(eMsg);
			
		}			
	}
	
	@Test(priority = 40815, groups={"New Connect", "All"})	
	public void errorDeterminingOrderTypeForNewBundles() throws Exception {
		if (!WebTopLogInPage.webTopLogedIn) {
			webTopLogin();
		}
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		webTopMainPage.searchFor("Group ID");
		new ErrorDeterminingOrderType(frameworkContext).errorDeterminingOrderType();
	}
	@Test(priority = 40820, groups={"New Connect", "All"})	
	public void scheduleDOIOrDOCForNewBundles() throws Exception {
		scheduleDOIOrDOC();
	}
	
	@Test(priority = 40830, groups={"New Connect", "All"})	
	public void installTechPreProvisionESGForNewBundles() throws Exception {
		installTechPreProvisionESG();
	}
	
	@Test(priority = 40840, groups={"New Connect", "All"})	
	public void performCPEInstallation_DOIForNewBundles() throws Exception {
		performCPEInstallation_DOI();
	}
	
	@Test(priority = 40850, groups={"New Connect", "All"})	
	public void waitForDOCDateForNewBundles() throws Exception {
		waitForDOCDate();
	}
	
	@Test(priority = 40860, groups={"New Connect", "All"})	
	public void cutoverBVEForNewBundles() throws Exception {
		cutoverBVE();
	}
	
	@Test(priority = 40870, groups={"New Connect", "All"})	
	public void performBillingUpdateForNewBundles() throws Exception {
		performBillingUpdate();
	}
	
	@Test(priority = 40900, groups={"New Connect", "All"})	
	public void verifyETSCCountAndPricingBundleForSite1() throws Exception {
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		WorkOrderComments workOrderComments = new WorkOrderComments(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}			
			workOrderComments.ETSCCountVerification("11");			
		} catch (Exception Ex) {
			report.reportHardFailEvent("ETSC Count  verification from work order comments",
					"ETSC Count  verification from work order comments failed!!!");
			String eMsg = "ETSC Count  verification from work order comments failed. --- " + Ex.getMessage();
			log.error(eMsg);
		}
	}
	
	@Test(priority = 40910, groups={"New Connect", "All"})	
	public void verifyETSCCountAndPricingBundle() throws Exception {
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		BusinessCentralTab businessCentralTab = new BusinessCentralTab(frameworkContext);
		AccountProfileTab accountProfileTab = new AccountProfileTab(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();				
			}
			webTopMainPage.searchOrder("Group ID");		
			businessCentralTab.verifyETSCCount("11");
			accountProfileTab.verifyBundle("10-19");
		} catch (Exception Ex) {
			report.reportHardFailEvent("Bundle verification in Account profile page",
					"Bundle verification in Account profile pages failed!!!");
			String eMsg = "Bundle verification in Account profile page failed. --- " + Ex.getMessage();
			log.error(eMsg);
		}
	}
		
	@Test(priority = 40920, groups={"New Connect", "All"})	
	public void updateDumpValuesAfterPrimarySiteCompleted() throws Exception {
		dataDump.setValue("SFOpportunityId", dataDump.getValue("SFOpportunityId_Site2"));
		dataDump.setValue("GroupID", dataDump.getValue("GroupID_Site2"));
		dataDump.setValue("OrderID_RT", dataDump.getValue("OrderID_RT_Site2"));
		dataDump.setValue("ChildAccountID", dataDump.getValue("ChildAccountID_Site2"));
	}

	@Test(priority = 45910, groups={"New Connect", "All"})	
	public void verifyETSCCountAndPricingBundleForSite2() throws Exception {
		verifyETSCCountAndPricingBundleForSite1();
	}
	
	@Test(priority = 45920, groups={"New Connect", "All"})	
	public void verifyETSCCountAndPricingBundle_MSFlow() throws Exception {
		verifyETSCCountAndPricingBundle();
	}

}
