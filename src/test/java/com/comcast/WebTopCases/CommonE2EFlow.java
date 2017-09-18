package com.comcast.WebTopCases;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.eclipse.persistence.internal.helper.DatabaseTable;
import org.testng.annotations.Test;

import com.comcast.pages.webtop.*;
import com.comcast.bcp.ActivateUser;
import com.comcast.bcp.BCPLogin;
import com.comcast.bcp.CreateBCPPortalUserPassword;
import com.comcast.bcp.GenerateUniqueChildBAN;
import com.comcast.bcp.WhiteListEmail;
import com.comcast.commons.ComcastTest;
import com.comcast.commons.WebtopPage;
import com.comcast.mail.GenerateUniqueEMail;
import com.comcast.mail.OpenMailInInbox;
import com.comcast.pages.simon.SimonLogInPage;
import com.comcast.pages.broadsoft.BroadsoftGroupPage;
import com.comcast.pages.broadsoft.BroadsoftLogInPage;
import com.comcast.pages.netxusa.NetXUSALogInPage;
import com.comcast.pages.netxusa.NetXUSAMainPage;
import com.comcast.pages.simon.AA_BusinessHours;
import com.comcast.pages.simon.CallQueue;
import com.comcast.pages.simon.CallerIDDetails;
import com.comcast.pages.simon.TNManagement;
import com.comcast.pages.simon.OrderInformation.Locators;
import com.comcast.pages.simon.DirectoryListings;
import com.comcast.pages.simon.EquipmentConfiguration;
import com.comcast.pages.simon.CustomerDetails;
import com.comcast.pages.simon.OrderInformation;
import com.comcast.pages.simon.SIMONMainPage;
import com.comcast.pages.simon.SeatConfiguration;
import com.comcast.pages.simon.ServiceInformation;
import com.comcast.pages.simon.Settings;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.Page;

public class CommonE2EFlow extends ComcastTest {
	DataTable dataSet;
	String eMsg = "";

	Logger log = Logger.getLogger(CommonE2EFlow.class);

	@Test(priority = 50, groups={"New Connect", "All"})
	public void UniqueMailAndChildBANCreation() throws Exception {

		try {
			GenerateUniqueEMail genaerateUniqueEMail = new GenerateUniqueEMail(frameworkContext);
			WhiteListEmail whiteListEmail = new WhiteListEmail(frameworkContext);
			GenerateUniqueChildBAN genarateUniqueChildBAN = new GenerateUniqueChildBAN(frameworkContext);
			genaerateUniqueEMail.generateUniqueEMail();
			whiteListEmail.whiteListEmail();
			genarateUniqueChildBAN.genarateUniqueChildBAN();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Unique Email and Child BAN", "Unique Email and Child BAN Failed" + Ex.getMessage());
			eMsg = "Unique Email and Child BAN Failed!!! " + Ex.getMessage();

			log.error(eMsg);
		}
	}

	@Test(priority = 100, groups={"New Connect", "All"}, timeOut = 300000)
	public void SOAUpdator() throws Exception {

		this.dataSet = frameworkContext.getDataTable();
		try {
			try {
				WebtopPage webtopPage = new WebtopPage(frameworkContext);
				log.warn(
						"SOA File updator script execution is in-progress using external script. Please wait for that to be completed");
				webtopPage.SOAExecutor();
				log.warn("SOA File updator script execution is completed using external script.");
				getSOAFileDetails();
				dataDump.setValue("OrderCategory", "New Connect");
				dataDump.setValue("SiteType", "SingleSite");
			} catch (IOException e) {
				log.error("SOA Updator Failed");
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("SOA Updator", "SOA Updator Failed" + Ex.getMessage());
			eMsg = "SOA Updator Failed!!! " + Ex.getMessage();

			log.error(eMsg);
		}
	}
	

	@Test(priority = 500, groups={"New Connect", "All"})
	public void webTopLogin() throws Exception {

		try {
			WebTopLogInPage webTopLoginPage = new WebTopLogInPage(frameworkContext);
			webTopLoginPage.webTopLogin();
		} catch (Exception Ex) {
			report.reportHardFailEvent("WebTop Login", "User Login NOT successful" + Ex.getMessage());
			eMsg = report.getMessage() + "Webtop Login Failed!!! " + Ex.getMessage();

			log.error(eMsg);
		}
	}

	@Test(priority = 1000, groups = { "New Connect", "All" })
	public void bulkLoadFileImport() throws Exception {

		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		BulkLoadFileImport bulkLoadFileImport = new BulkLoadFileImport(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
			}
			if (webTopMainPage.launchFileImportMenu()) {				
				if (!new WebtopPage(frameworkContext).checkIfFileExists(dataDump.getValue("dataFileName"))){
					SOAUpdator();
				}
				bulkLoadFileImport.loadBulkImportFileAndVerify();
			} else {
				log.error("Launching the File Import menu failed");
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Loading Import File", "Loading Import File Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Loading Import File Failed!!! " + Ex.getMessage();

			log.error(eMsg);
		}
	}

	@Test(priority = 1500, groups={"New Connect", "All"})
	public void SOA_Or_SDW_Required() throws Exception {

		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		SOAOrSDWRequired ObjSOAOrSDWRequired = new SOAOrSDWRequired(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
			}
			if (dataDump.getValue("GroupID").isEmpty()) {
				webTopMainPage.searchFor("Salesforce Opportunity ID(FID)");
			} else {
				webTopMainPage.searchFor("Group ID");
			}
			ObjSOAOrSDWRequired.SOARequired();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Loading SOA or SDW required documents",
					"Loading SOA or SDW required file Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Loading SOA or SDW required file Failed!!! " + Ex.getMessage();

			log.error(eMsg);
		}
	}

	@Test(priority = 2000, groups={"New Connect", "All"})
	public void prequalCheckList() throws Exception {

		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		PrequalCheckList prequalCheckList = new PrequalCheckList(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				if (dataDump.getValue("GroupID").isEmpty()) {
					webTopMainPage.searchFor("Salesforce Opportunity ID(FID)");
				} else {
					webTopMainPage.searchFor("Group ID");
				}
			}
			prequalCheckList.prequalCheckList();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Loading Prequal CheckList documents",
					"Loading Prequal CheckList file Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Loading Prequal CheckList file Failed!!! " + Ex.getMessage();

			log.error(eMsg);
		}
	}

	@Test(priority = 2500, groups={"New Connect", "All"})
	public void reviewOrderForAccuracyAndAcceptance() throws Exception {

		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		ReviewOrderForAccuracyAndAcceptance reviewOrderForAccuracyAndAcceptance = new ReviewOrderForAccuracyAndAcceptance(
				frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				if (dataDump.getValue("GroupID").isEmpty()) {
					webTopMainPage.searchFor("Salesforce Opportunity ID(FID)");
				} else {
					webTopMainPage.searchFor("Group ID");
				}
			}
			reviewOrderForAccuracyAndAcceptance.reviewOrderForAccuracyAndAcceptance();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Review Order for Accuracy and Acceptance",
					"Review Order for Accuracy and Acceptance Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Review Order for Accuracy and Acceptance Failed!!! " + Ex.getMessage();

			log.error(eMsg);
		}
	}

	@Test(priority = 3000, groups={"New Connect", "All"})
	public void SDPMCompleteDesignReview() throws Exception {

		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		SDPM_CompleteDesignReview SDPMCompleteDesignReview = new SDPM_CompleteDesignReview(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				if (dataDump.getValue("GroupID").isEmpty()) {
					webTopMainPage.searchFor("Salesforce Opportunity ID(FID)");
				} else {
					webTopMainPage.searchFor("Group ID");
				}
			}
			SDPMCompleteDesignReview.SDPMCompleteDesignReview();
		} catch (Exception Ex) {

			report.reportHardFailEvent("SDPM Complete design and review", "SDPM Complete design and review Failed!!!",
					Status.FAIL);
			String eMsg = "Error in SDPM Complete design and review --- " + Ex.getMessage();
			log.error(eMsg);
		}
	}

	
	@Test(priority = 3200, groups={"New Connect", "All"})
	public void reviewAndCorrectSFID() throws Exception {
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		ReviewAndCorrectSFID reviewAndCorrectSFID = new ReviewAndCorrectSFID(
				frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				if (dataDump.getValue("GroupID").isEmpty()) {
					webTopMainPage.searchFor("Salesforce Opportunity ID(FID)");
				} else {
					webTopMainPage.searchFor("Group ID");
				}
			}
			reviewAndCorrectSFID.reviewAndCorrectSFID();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Review and Correct SFID ", "Review and Correct SFID  Failed!!!",
					Status.FAIL);
			String eMsg = "Error in Review and Correct SFID  --- " + Ex.getMessage();
			log.error(eMsg);
		}
	}
	
	@Test(priority = 3500, groups={"New Connect", "All"})
	public void createAndEnterDSTOrDSGAccountNumber() throws Exception {

		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		CreateAndEnterDSTOrDSGAccountNumber createAndEnterDSTOrDSGAccountNumber = new CreateAndEnterDSTOrDSGAccountNumber(
				frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				if (dataDump.getValue("GroupID").isEmpty()) {
					webTopMainPage.searchFor("Salesforce Opportunity ID(FID)");
				} else {
					webTopMainPage.searchFor("Group ID");
				}
			}
			createAndEnterDSTOrDSGAccountNumber.createAndEnterDSTOrDSGAccountNumber();
		} catch (Exception Ex) {

			report.reportHardFailEvent("Create and Enter DST/CSG Account Number",
					"Create and Enter DST/CSG Account Number Failed!!!", Status.FAIL);
			String eMsg = "Error in Create and Enter DST/CSG Account Number --- " + Ex.getMessage();
			log.error(eMsg);
		}
	}

	@Test(priority = 4000, groups={"New Connect", "All"})
	public void errorValidatingCSGOrDSTFormData() throws Exception {

		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		ErrorValidatingCSGOrDSTFormData errorValidatingCSGOrDSTFormData = new ErrorValidatingCSGOrDSTFormData(
				frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				if (dataDump.getValue("GroupID").isEmpty()) {
					webTopMainPage.searchFor("Salesforce Opportunity ID(FID)");
				} else {
					webTopMainPage.searchFor("Group ID");
				}
			}
			errorValidatingCSGOrDSTFormData.errorValidatingCSGOrDSTFormData();
		} catch (Exception Ex) {
			report.reportHardFailEvent(" Error Validating CSG/DST Form Data",
					" Error Validating CSG/DST Form Data Failed!!!", Status.FAIL);
			String eMsg = "Error in Validating CSG/DST Form Data --- " + Ex.getMessage();
			log.error(eMsg);

		}
	}
	
	@Test(priority = 4500, groups={"New Connect", "All"})
	public void scheduleDOIOrDOC() throws Exception {

		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		ScheduleDOIOrDOC scheduleDOIOrDOC = new ScheduleDOIOrDOC(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				if (dataDump.getValue("GroupID").isEmpty()) {
					webTopMainPage.searchFor("Salesforce Opportunity ID(FID)");
				} else {
					webTopMainPage.searchFor("Group ID");
				}
			}
			scheduleDOIOrDOC.scheduleDOIOrDOC();
		} catch (Exception Ex) {

			report.reportHardFailEvent("Schedule DOI/DOC", "Schedule DOI/DOC Failed!!!", Status.FAIL);
			String eMsg = "Error in Schedule DOI/DOC --- " + Ex.getMessage();
			log.error(eMsg);
		}
	}

	@Test(priority = 5000, groups={"New Connect", "All"})
	public void SOAOrderComplete() throws Exception {

		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		SOAOrderComplete ObjSOAOrderComplete = new SOAOrderComplete(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				if (dataDump.getValue("GroupID").isEmpty()) {
					webTopMainPage.searchFor("Salesforce Opportunity ID(FID)");
				} else {
					webTopMainPage.searchFor("Group ID");
				}
			}
			new ErrorSendingETSCToSFDC(frameworkContext).errorSendingETSCToSFDC();
			ObjSOAOrderComplete.SOAOrderComplete();
		} catch (Exception Ex) {

			report.reportHardFailEvent("SOA Order Complete", "SOA Order Complete Failed!!!", Status.FAIL);
			String eMsg = "Error in SOA Order Complete --- " + Ex.getMessage();
			log.error(eMsg);
		}
	}

	@Test(priority = 5500, groups={"New Connect", "All"})
	public void webTopLogout() throws Exception {

		WebTopLogInPage webTopLogInPage = new WebTopLogInPage(frameworkContext);
		try {
			webTopLogInPage.logout();
		} catch (Exception Ex) {

			report.reportHardFailEvent("Logout WebTop Application", "Logout WebTop Application Failed!!!", Status.FAIL);
			String eMsg = "Error in Logout WebTop Application --- " + Ex.getMessage();
			log.error(eMsg);
		}
	}

	@Test(priority = 6000, groups={"New Connect", "All"})
	public void SIMONLogin() throws Exception {

		try {
			SimonLogInPage simonLogInPage = new SimonLogInPage(frameworkContext);
			simonLogInPage.simonLogin();
		} catch (Exception Ex) {
			report.reportHardFailEvent("SIMON Login", "User Login NOT successful" + Ex.getMessage());
			eMsg = report.getMessage() + "SIMON Login Failed!!! " + Ex.getMessage();

			log.error(eMsg);
		}
	}
	
	@Test(priority = 6100, groups={"New Connect", "All"})
	public void DeleteSiteIfExists() throws Exception {
		try {
			SIMONMainPage simonMainPage = new SIMONMainPage(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
			}
			getSOAFileDetails();
			if(simonMainPage.verifySiteExists())
				new Settings(frameworkContext).deleteSite();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Delete Site If Exists", "Delete Site NOT successful" + Ex.getMessage());
			eMsg = report.getMessage() + "Delete Site Failed!!! " + Ex.getMessage();
			log.error(eMsg);
		}
	}

	@Test(priority = 6500, groups={"New Connect", "All"})
	public void SIMONUploadDataFile() throws Exception {

		try {
			SIMONMainPage simonMainPage = new SIMONMainPage(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
			}
			if (!new WebtopPage(frameworkContext).checkIfFileExists(dataDump.getValue("dataFileName"))){
				SOAUpdator();
			}
			simonMainPage.simonUploadDataFile();			
		} catch (Exception Ex) {
			report.reportHardFailEvent("SOA Upload in SIMON", "SOA Upload in SIMON Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "SOA Upload in SIMON Failed!!! " + Ex.getMessage();

			log.error(eMsg);
		}
	}

	@Test(priority = 7000, groups={"New Connect", "All"})
	public void SFOpportunityIDSearch() throws Exception {

		try {
			SIMONMainPage simonMainPage = new SIMONMainPage(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
			}
			simonMainPage.SFOpportunityIDSearch();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Search SalesForce Opportunity ID",
					"Search SalesForce Opportunity ID Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Search SalesForce Opportunity ID Failed!!! " + Ex.getMessage();

			log.error(eMsg);
		}
	}

	@Test(priority = 7500, groups={"New Connect", "All"})
	public void customerDetails() throws Exception {

		try {
			SIMONMainPage simonMainPage = new SIMONMainPage(frameworkContext);
			CustomerDetails customerDetails = new CustomerDetails(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				simonMainPage.SFOpportunityIDSearch();
			}
			customerDetails.customerDetails();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Customer Details", "Customer Details Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Customer Details in SIMON Failed!!! " + Ex.getMessage();

			log.error(eMsg);
		}
	}

	@Test(priority = 8000, groups={"New Connect", "All"})
	public void orderInformation() throws Exception {

		try {
			SIMONMainPage simonMainPage = new SIMONMainPage(frameworkContext);
			OrderInformation orderInformation = new OrderInformation(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				simonMainPage.SFOpportunityIDSearch();
			}
			orderInformation.orderInformation();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Order Information", "Order Information Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Order Information in SIMON Failed!!! " + Ex.getMessage();

			log.error(eMsg);
		}
	}

	@Test(priority = 8500, groups={"New Connect", "All"})
	public void serviceInformation() throws Exception {

		try {
			SIMONMainPage simonMainPage = new SIMONMainPage(frameworkContext);
			ServiceInformation serviceInformation = new ServiceInformation(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				simonMainPage.SFOpportunityIDSearch();
			}
			serviceInformation.serviceInformation();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Service Information", "Service Information Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Service Information in SIMON Failed!!! " + Ex.getMessage();

			log.error(eMsg);
		}
	}

	@Test(priority = 9000, groups={"New Connect", "All"})
	public void callerIDInformation() throws Exception {

		try {
			SIMONMainPage simonMainPage = new SIMONMainPage(frameworkContext);
			CallerIDDetails callerIDDetails = new CallerIDDetails(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();				
			}
			simonMainPage.SFOpportunityIDSearch();
			callerIDDetails.callerIDInformation();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Caller ID Information", "Caller ID Information Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Caller ID Information in SIMON Failed!!! " + Ex.getMessage();

			log.error(eMsg);
		}
	}

	@Test(priority = 9500, groups={"New Connect", "All"})
	public void TNManagement() throws Exception {

		try {
			SIMONMainPage simonMainPage = new SIMONMainPage(frameworkContext);
			TNManagement ObjTNManagement = new TNManagement(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				simonMainPage.SFOpportunityIDSearch();
			}
			ObjTNManagement.TN_Management();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Caller ID Information", "Caller ID Information Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Caller ID Information in SIMON Failed!!! " + Ex.getMessage();

			log.error(eMsg);
		}
	}
	
	@Test(priority = 9600, groups={"New Connect", "All"})
	public void enableCallQueueFeature() throws Exception {
		try {
			SIMONMainPage simonMainPage = new SIMONMainPage(frameworkContext);
			SeatConfiguration seatConfiguration = new SeatConfiguration(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();				
			}
			simonMainPage.SFOpportunityIDSearch();
			seatConfiguration.enableCallQueueFeature();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Enable Call Queue Feature for UC Seat",
					"Enable Call Queue Feature for UC Seat Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Enable Call Queue Feature for UC Seat in SIMON Failed!!! "
					+ Ex.getMessage();
			log.error(eMsg);
		}
	}
	
	@Test(priority = 9700, groups={"New Connect", "All"})
	public void addUsersIntoCallQueue() throws Exception {
		try {
			SIMONMainPage simonMainPage = new SIMONMainPage(frameworkContext);
			CallQueue callQueue = new CallQueue(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				simonMainPage.SFOpportunityIDSearch();
			}
			dataSet = frameworkContext.getDataTable();
			callQueue.addUsersinCallQueue(dataSet.getValue("MakeSeatAvailableAsAgent"));
		} catch (Exception Ex) {
			report.reportHardFailEvent("Call Queue Configuration for Add Users",
					"Call Queue Configuration for Add Users Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Call Queue Configuration for Add Users Failed!!! " + Ex.getMessage();			
			log.error(eMsg);
		}
	}
	
	@Test(priority = 9800, groups={"New Connect", "All"})
	public void tollFreeTNConfiguration() throws Exception {
		
		try {
			SIMONMainPage simonMainPage = new SIMONMainPage(frameworkContext);
			TNManagement tnManagement = new TNManagement(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
			}
			simonMainPage.SFOpportunityIDSearch();
			tnManagement.tollFreeTNConfiguration();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Toll Free TN", "Toll Free TN configuration Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Toll Free TN configuration in SIMON Failed!!! " + Ex.getMessage();
			log.error(eMsg);
		}
	}

	@Test(priority = 10000, groups={"New Connect", "All"})
	public void directoryListing() throws Exception {

		try {
			SIMONMainPage simonMainPage = new SIMONMainPage(frameworkContext);
			DirectoryListings directoryListings = new DirectoryListings(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
			}
			simonMainPage.SFOpportunityIDSearch();
			directoryListings.directoryListing();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Directory Listing Information",
					"Directory Listing Information Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Directory Listing Information in SIMON Failed!!! " + Ex.getMessage();

			log.error(eMsg);
		}
	}

	@Test(priority = 10100, groups={"New Connect", "All"})
	public void equipmentConfiguration() throws Exception {

		try {
			SIMONMainPage simonMainPage = new SIMONMainPage(frameworkContext);
			EquipmentConfiguration equipmentConfiguration = new EquipmentConfiguration(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				simonMainPage.SFOpportunityIDSearch();
			}
			equipmentConfiguration.equipmentConfiguration();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Equipment Configuration", "Equipment Configuration Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Equipment Configuration in SIMON Failed!!! " + Ex.getMessage();

			log.error(eMsg);
		}
	}

	@Test(priority = 10500, groups={"New Connect", "All"})
	public void AABusinessHours() throws Exception {

		try {
			SIMONMainPage simonMainPage = new SIMONMainPage(frameworkContext);
			AA_BusinessHours AABusinessHours = new AA_BusinessHours(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				simonMainPage.SFOpportunityIDSearch();
			}
			AABusinessHours.AABusinessHours();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Auto Attendants Business Hours Configuration",
					"Auto Attendants  Business Hours Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Auto Attendants Business Hours Configuration in SIMON Failed!!! "
					+ Ex.getMessage();

			log.error(eMsg);
		}
	}

	@Test(priority = 11000, groups={"New Connect", "All"})
	public void generateWebTopFile() throws Exception {

		try {
			SIMONMainPage simonMainPage = new SIMONMainPage(frameworkContext);
			Settings settings = new Settings(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				simonMainPage.SFOpportunityIDSearch();
			}
			settings.generateWebTopFileToDownload();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Generate Webtop File and Unzip",
					"Generate Webtop File and Unzipping Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Generate Webtop File and Unzipping in SIMON Failed!!! " + Ex.getMessage();

			log.error(eMsg);
		}
	}

	@Test(priority = 11500, groups={"New Connect", "All"})
	public void SIMONLogout() throws Exception {

		SimonLogInPage simonLogInPage = new SimonLogInPage(frameworkContext);
		try {
			simonLogInPage.simonLogout();

		} catch (Exception Ex) {

			report.reportHardFailEvent("Logout SIMON Application", "Logout SIMON Application Failed!!!", Status.FAIL);
			String eMsg = "Error in Logout SIMON Application --- " + Ex.getMessage();
			log.error(eMsg);
		}
	}

	@Test(priority = 12000, groups={"New Connect", "All"})
	public void webTopLoginAfterSIMON() throws Exception {
		webTopLogin();
	}

	@Test(priority = 12500, groups={"New Connect", "All"})
	public void bulkLoadFileImportAfterSIMON() throws Exception {
		bulkLoadFileImport();
	}

	@Test(priority = 13000, groups={"New Connect", "All"})
	public void SOA_Or_SDW_RequiredAfterSIMON() throws Exception {
		SOA_Or_SDW_Required();
	}
	
	@Test(priority = 13100, groups={"New Connect", "All"})
	public void resolveBillingMismatch() throws Exception {
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		ResolveBillingMismatch resolveBillingMismatch = new ResolveBillingMismatch(
				frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}
			resolveBillingMismatch.resolveBillingMismatch();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Resolve Billing Mismatch Tray",
					"Resolve Billing Mismatch Tray Failed!!!", Status.FAIL);
			String eMsg = "Error in Resolve Billing Mismatch Tray  --- " + Ex.getMessage();
			log.error(eMsg);
		}
	}

	@Test(priority = 13500, groups={"New Connect", "All"})
	public void reviewOrderForAccuracyAndAcceptanceAfterSIMON() throws Exception {
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		ReviewOrderForAccuracyAndAcceptance reviewOrderForAccuracyAndAcceptance = new ReviewOrderForAccuracyAndAcceptance(
				frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}
			resolveBillingMismatch();
			reviewOrderForAccuracyAndAcceptance.result();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Review Order for Accuracy and Acceptance",
					"Review Order for Accuracy and Acceptance Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Review Order for Accuracy and Acceptance Failed!!! " + Ex.getMessage();

			log.error(eMsg);
		}
	}

	@Test(priority = 14000, groups={"New Connect", "All"})
	public void chooseShippingMethodForCPEOrder() throws Exception {

		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		ChooseShippingMethodForCPEOrder chooseShippingMethodForCPEOrder = new ChooseShippingMethodForCPEOrder(
				frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}
			new ErrorSendingETSCToSFDC(frameworkContext).errorSendingETSCToSFDC();
			chooseShippingMethodForCPEOrder.chooseShippingMethodForCPEOrder();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Choose Shipping Method For CPE Order",
					"Choose Shipping Method For CPE Order Failed!!!", Status.FAIL);
			String eMsg = "Error in Choose Shipping Method For CPE Order --- " + Ex.getMessage();
			log.error(eMsg);

		}
	}

	

	@Test(priority = 14500, groups={"New Connect", "All"})
	public void errorReservingNativeTNs() throws Exception {

		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		ErrorReservingNativeTNs errorReservingNativeTNs = new ErrorReservingNativeTNs(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}
			errorReservingNativeTNs.errorReservingNativeTNs();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Retry Reserving Natve TNs", "Retry Reserving Natve TNs Failed!!!", Status.FAIL);
			String eMsg = "Error in Retry Reserving Natve TNs --- " + Ex.getMessage();
			log.error(eMsg);

		}
	}
	
	@Test(priority = 14600, groups={"New Connect", "All"})
	public void atLeastOneMissingMACAddressStillExistsOnTheOrder() throws Exception {

		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		AtLeastOneMissingMACAddressStillExistsOnTheOrder atLeastOneMissingMACAddressStillExistsOnTheOrder = new AtLeastOneMissingMACAddressStillExistsOnTheOrder(
				frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}
			atLeastOneMissingMACAddressStillExistsOnTheOrder.atLeastOneMissingMACAddressStillExistsOnTheOrder();
		} catch (Exception Ex) {
			report.reportHardFailEvent("At least one missing MAC Address still exists on the order",
					"At least one missing MAC Address still exists on the order Failed!!!", Status.FAIL);
			String eMsg = "Error in At least one missing MAC Address still exists on the order --- " + Ex.getMessage();
			log.error(eMsg);

		}
	}

	@Test(priority = 15000, groups={"New Connect", "All"})
	public void CPEOrderWillBeSubmittedAutomatically() throws Exception {

		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		CPEOrderWillBeSubmittedAutomatically ObjCPEOrderWillBeSubmittedAutomatically = new CPEOrderWillBeSubmittedAutomatically(
				frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}
			ObjCPEOrderWillBeSubmittedAutomatically.CPE_OrderWillBeSubmittedAutomatically();
		} catch (Exception Ex) {
			report.reportHardFailEvent("CPE Order will be submitted Automatically",
					"CPE Order will be submitted Automatically Failed!!!", Status.FAIL);
			String eMsg = "Error inCPE Order will be submitted Automatically --- " + Ex.getMessage();
			log.error(eMsg);

		}
	}

	@Test(priority = 15100, groups={"New Connect", "All"})
	public void provisionPortedTNsForOrder() throws Exception {

		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		ProvisionPortedTNsForOrder provisionPortedTNsForOrder = new ProvisionPortedTNsForOrder(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}
			provisionPortedTNsForOrder.provisionPortedTNsForOrder();
		} catch (Exception Ex) {
			report.reportHardFailEvent("PROV: Provision Ported TNs for Order",
					"PROV: Provision Ported TNs for Order Failed!!!", Status.FAIL);
			String eMsg = "Error in PROV: Provision Ported TNs for Order --- " + Ex.getMessage();
			log.error(eMsg);

		}
	}

	@Test(priority = 15200, groups={"New Connect", "All"})
	public void enterFOCDateForPortedTNs() throws Exception {

		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		EnterFOCDateForPortedTNs enterFOCDateForPortedTNs = new EnterFOCDateForPortedTNs(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}
			enterFOCDateForPortedTNs.enterFOCDateForPortedTNs();
		} catch (Exception Ex) {

			report.reportHardFailEvent("Enter FOC Date for Ported TNs", "Enter FOC Date for Ported TNs Failed!!!",
					Status.FAIL);
			String eMsg = "Error in Enter FOC Date for Ported TNs --- " + Ex.getMessage();
			log.error(eMsg);
		}
	}
	
	

	@Test(priority = 15500, groups={"New Connect", "All"})
	public void waitingForOrderAcceptRejectResponseFromSNAP() throws Exception {
		SNAPSynchronousResponseContainsErrors snapSynchronousResponseContainsErrors = new SNAPSynchronousResponseContainsErrors(frameworkContext);
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		WaitingForOrderAcceptRejectResponseFromSNAP waitingForOrderAcceptRejectResponseFromSNAP = new WaitingForOrderAcceptRejectResponseFromSNAP(
				frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}
			snapSynchronousResponseContainsErrors.SNAPSynchronousResponseContainsError();
			waitingForOrderAcceptRejectResponseFromSNAP.waitingForOrderAcceptRejectResponseFromSNAP();
		} catch (Exception Ex) {
			report.reportHardFailEvent("SYSTEM: Waiting for Order Accept/Reject Response from SNAP (will auto close)",
					"SYSTEM: Waiting for Order Accept/Reject Response from SNAP (will auto close) Failed!!!",
					Status.FAIL);
			String eMsg = "Error in SYSTEM: Waiting for Order Accept/Reject Response from SNAP (will auto close) --- "
					+ Ex.getMessage();
			log.error(eMsg);
		}
	}

	@Test(priority = 16000, groups={"New Connect", "All"})
	public void waitingForSNAPFileUploadStatusNotification() throws Exception {

		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		WaitingForSNAPFileUploadStatusNotification waitingForSNAPFileUploadStatusNotification = new WaitingForSNAPFileUploadStatusNotification(
				frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}
			waitingForSNAPFileUploadStatusNotification.waitingForSNAPFileUploadStatusNotification();
		} catch (Exception Ex) {
			report.reportHardFailEvent("SYSTEM: Waiting for SNAP File Upload Status Notification (will auto close)",
					"SYSTEM: Waiting for SNAP File Upload Status Notification (will auto close) Failed!!!",
					Status.FAIL);
			String eMsg = "Error in SYSTEM: Waiting for SNAP File Upload Status Notification (will auto close) --- "
					+ Ex.getMessage();
			log.error(eMsg);

		}
	}

	@Test(priority = 16500, groups={"New Connect", "All"})
	public void webTopLogoutAfterShippingMethodForCPEOrder() throws Exception {
		webTopLogout();
	}

	@Test(priority = 17000, groups={"New Connect", "All"})
	public void NetXLogin() throws Exception {

		try {
			NetXUSALogInPage netXUSALogInPage = new NetXUSALogInPage(frameworkContext);
			netXUSALogInPage.NetXLogin();
		} catch (Exception Ex) {
			report.reportHardFailEvent("NetX Login", "User Login NOT successful, EXCEPTION CAUGHT : " + Ex.getMessage());
			eMsg = report.getMessage() + "NetX Login Failed!!! " + Ex.getMessage();

			log.error(Ex.getMessage());
		}
	}

	@Test(priority = 17500, groups={"New Connect", "All"})
	public void processTestOrder() throws Exception {

		NetXUSAMainPage netXUSAMainPage = new NetXUSAMainPage(frameworkContext);
		try {
			if (!NetXUSALogInPage.NetXLogedIn) {
				NetXLogin();
			}
			netXUSAMainPage.launchSalesOrderMenu();
			netXUSAMainPage.clickOnOrder();
			netXUSAMainPage.processTestOrder();
			new NetXUSALogInPage(frameworkContext).NetXLogout();
		} catch (

		Exception Ex) {
			report.reportHardFailEvent("Process Test Order in NetX", "Process Test Order in NetX Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Process Test Order in NetX Failed!!! " + Ex.getMessage();

			log.error(eMsg);
		}
	}

	@Test(priority = 18000, groups={"New Connect", "All"})
	public void wait24HoursThenRecheckForCPEMACAddress() throws Exception {

		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		Wait24HoursThenRecheckForCPEMACAddress wait24HoursThenRecheckForCPEMACAddress = new Wait24HoursThenRecheckForCPEMACAddress(
				frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}
			wait24HoursThenRecheckForCPEMACAddress.wait24HoursThenRecheckForCPEMACAddress();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Wait 24 hours, then recheck for CPE MAC Address(es)",
					"Wait 24 hours, then recheck for CPE MAC Address(es) Failed!!!", Status.FAIL);
			String eMsg = "Error in Wait 24 hours, then recheck for CPE MAC Address(es) --- " + Ex.getMessage();
			log.error(eMsg);

		}
	}

	@Test(priority = 18500, groups={"New Connect", "All"})
	public void verifyCPEShipmentOrder() throws Exception {

		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		VerifyCPEOrderShipment verifyCPEOrderShipment = new VerifyCPEOrderShipment(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}
			verifyCPEOrderShipment.verifyCPEShipmentOrder();
		} catch (Exception Ex) {

			report.reportHardFailEvent("Verify CPE Shipment Order", "Verify CPE Shipment Order Failed!!!", Status.FAIL);
			String eMsg = "Error in Verify CPE Shipment Order --- " + Ex.getMessage();
			log.error(eMsg);
		}
	}

	@Test(priority = 19000, groups={"New Connect", "All"})
	public void cPEInfoNotReturnedAfterTwoAttempts() throws Exception {

		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		CPEInfoNotReturnedAfterTwoAttempts cPEInfoNotReturnedAfterTwoAttempts = new CPEInfoNotReturnedAfterTwoAttempts(
				frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}
			cPEInfoNotReturnedAfterTwoAttempts.cPEInfoNotReturnedAfterTwoAttempts();
		} catch (Exception Ex) {
			report.reportHardFailEvent("CPE Info not returned after two attempts",
					"CPE Info not returned after two attempts Failed!!!", Status.FAIL);
			String eMsg = "Error in CPE Info not returned after two attempts --- " + Ex.getMessage();
			log.error(eMsg);

		}
	}

	@Test(priority = 19500, groups={"New Connect", "All"})
	public void e911IncompleteUpdateErrorsOnOrder() throws Exception {

		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		E911IncompleteUpdateErrorsOnOrder e911IncompleteUpdateErrorsOnOrder = new E911IncompleteUpdateErrorsOnOrder(
				frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}
			e911IncompleteUpdateErrorsOnOrder.e911IncompleteUpdateErrorsOnOrder();
		} catch (Exception Ex) {
			report.reportHardFailEvent("E-911 Incomplete Update Errors on Order",
					"E-911 Incomplete Update Errors on Order Failed!!!", Status.FAIL);
			String eMsg = "Error in E-911 Incomplete Update Errors on Order --- " + Ex.getMessage();
			log.error(eMsg);

		}
	}

	@Test(priority = 20000, groups={"New Connect", "All"})
	public void installTechPreProvisionESG() throws Exception {

		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		InstallTech_PreProvisionESG installTech_PreProvisionESG = new InstallTech_PreProvisionESG(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}
			installTech_PreProvisionESG.installTechPreProvisionESG();
		} catch (Exception Ex) {
			report.reportHardFailEvent("INSTALL TECH: Pre-Provision ESG", "INSTALL TECH: Pre-Provision ESG Failed!!!",
					Status.FAIL);
			String eMsg = "Error in INSTALL TECH: Pre-Provision ESG --- " + Ex.getMessage();
			log.error(eMsg);

		}
	}

	@Test(priority = 20500, groups={"New Connect", "All"})
	public void errorProvisioningE911ForApplicableTNs() throws Exception {

		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		ErrorProvisioningE911ForApplicableTNs errorProvisioningE911ForApplicableTNs = new ErrorProvisioningE911ForApplicableTNs(
				frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}
			errorProvisioningE911ForApplicableTNs.errorProvisioningE911ForApplicableTNs();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Provisioning E911 for Applicable TNs",
					"Provisioning E911 for Applicable TNs Failed!!!", Status.FAIL);
			String eMsg = "Error in Provisioning E911 for Applicable TNs --- " + Ex.getMessage();
			log.error(eMsg);

		}
	}

	@Test(priority = 21000, groups={"New Connect", "All"})
	public void errorProvisioningBroadworksUsersOrEnterprise() throws Exception {

		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		ErrorProvisioningBroadworksUsersOrEnterprise errorProvisioningBroadworksUsers = new ErrorProvisioningBroadworksUsersOrEnterprise(
				frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}
			errorProvisioningBroadworksUsers.errorProvisioningBroadworksUsers();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Provisioning Broadworks Users/Enterprise",
					"Provisioning Broadworks Users/Enterprise Failed!!!", Status.FAIL);
			String eMsg = "Error in Provisioning Broadworks Users/Enterprise --- " + Ex.getMessage();
			log.error(eMsg);

		}
	}

	@Test(priority = 21500, groups={"New Connect", "All"})
	public void configureAdvancedBroadworksFeatures() throws Exception {

		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		ConfigureAdvancedBroadworksFeatures configureAdvancedBroadworksFeatures = new ConfigureAdvancedBroadworksFeatures(
				frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}
			configureAdvancedBroadworksFeatures.configureAdvancedBroadworksFeatures();
		} catch (Exception Ex) {
			report.reportHardFailEvent("VOIP PROV: Configure Advanced Broadworks Features",
					"VOIP PROV: Configure Advanced Broadworks Features Failed!!!", Status.FAIL);
			String eMsg = "Error in VOIP PROV: Configure Advanced Broadworks Features --- " + Ex.getMessage();
			log.error(eMsg);

		}
	}

	@Test(priority = 22000, groups={"New Connect", "All"})
	public void configureAutoAttendantsAndHuntGroups() throws Exception {

		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		ConfigureAutoAttendantsAndHuntGroups configureAutoAttendantsAndHuntGroups = new ConfigureAutoAttendantsAndHuntGroups(
				frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}
			configureAutoAttendantsAndHuntGroups.configureAutoAttendantsAndHuntGroups();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Configure Auto Attendants and Hunt Groups",
					"Configure Auto Attendants and Hunt Groups Failed!!!", Status.FAIL);
			String eMsg = "Error in Configure Auto Attendants and Hunt Groups --- " + Ex.getMessage();
			log.error(eMsg);

		}
	}

	@Test(priority = 22100, groups={"New Connect", "All"})
	public void dataIssueExistsWithTollFreeTranslationTNAndOrANIFIDValue() throws Exception {

		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		DataIssueExistsWithTollFreeTranslationTNAndOrANIFIDValue dataIssueExistsWithTollFreeTranslationTNAndOrANIFIDValue = new DataIssueExistsWithTollFreeTranslationTNAndOrANIFIDValue(
				frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}
			dataIssueExistsWithTollFreeTranslationTNAndOrANIFIDValue
					.dataIssueExistsWithTollFreeTranslationTNAndOrANIFIDValue();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Configure Auto Attendants and Hunt Groups",
					"Configure Auto Attendants and Hunt Groups Failed!!!", Status.FAIL);
			String eMsg = "Error in Configure Auto Attendants and Hunt Groups --- " + Ex.getMessage();
			log.error(eMsg);

		}
	}

	@Test(priority = 22500, groups={"New Connect", "All"})
	public void broadsoftLogin() throws Exception {

		WebTopLogInPage.webTopLogedIn = false;
		try {
			BroadsoftLogInPage broadsoftLogInPage = new BroadsoftLogInPage(frameworkContext);
			broadsoftLogInPage.broadsoftLogin();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Broadsoft Login", "User Login NOT successful" + Ex.getMessage());
			eMsg = report.getMessage() + "Broadsoft Login Failed!!! " + Ex.getMessage();

			log.error(eMsg);
		}
	}

	@Test(priority = 23000, groups={"New Connect", "All"})
	public void broadsoftGroupAndUsersVerification() throws Exception {

		BroadsoftGroupPage broadsoftGroupPage = new BroadsoftGroupPage(frameworkContext);
		try {
			if (!BroadsoftLogInPage.broadsoftLogedIn) {
				broadsoftLogin();
			}
			broadsoftGroupPage.searchGroups("Group ID", "Equal To", dataDump.getValue("GroupID"));
			broadsoftGroupPage.verifyUsers();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Broadsoft Group/Users verfication", "Broadsoft Group/Users verification failed",
					Status.FAIL);
			String eMsg = "Error in Broadsoft Group/Users verfication--- " + Ex.getMessage();
			log.error(eMsg);

		}
	}

	@Test(priority = 23100, groups={"New Connect", "All"})
	public void broadsoftGroupAndExtensionDialingDigitVerification() throws Exception {

		this.dataSet = frameworkContext.getDataTable();
		BroadsoftGroupPage broadsoftGroupPage = new BroadsoftGroupPage(frameworkContext);
		try {
			if (!BroadsoftLogInPage.broadsoftLogedIn) {
				broadsoftLogin();
			}
			broadsoftGroupPage.searchGroups("Group ID", "Equal To", dataDump.getValue("GroupID"));
			broadsoftGroupPage
					.verifyExtensionDialingDigits(Integer.parseInt(dataSet.getValue("ExtensionDialingDigits")));
		} catch (Exception Ex) {
			report.reportHardFailEvent("Broadsoft Group/Users verfication", "Broadsoft Group/Users verification failed",
					Status.FAIL);
			String eMsg = "Error in Broadsoft Group/Users verfication--- " + Ex.getMessage();
			log.error(eMsg);

		}
	}
	
	@Test(priority = 23150, groups={"New Connect", "All"})	
	public void activatePMUser() throws Exception {		
		try {
			WebTopLogInPage.webTopLogedIn = false;
			OpenMailInInbox openMailInInbox = new OpenMailInInbox(frameworkContext);
			openMailInInbox.openPMMailInInbox(dataDump.getValue("MailIdList").split("\\-")[0]);
			ActivateUser activateUser = new ActivateUser(frameworkContext);
			activateUser.activateUser();
			CreateBCPPortalUserPassword createBCPPortalUserPassword = new CreateBCPPortalUserPassword(frameworkContext);
			createBCPPortalUserPassword.createBCPPortalPassword();
			BCPLogin loginBCP = new BCPLogin(frameworkContext);
			loginBCP.loginBCP(dataDump.getValue("MailIdList").split("\\-")[0]);
			loginBCP.signOut();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Primary Manager Activation", "Primary Manager Activation Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Primary Manager Activation Failed!!! " + Ex.getMessage();			
			log.error(eMsg);
		}
	}
	
	@Test(priority = 23160, groups={"New Connect", "All"})	
	public void activateServiceUsers() throws Exception {		
		try {
			WebTopLogInPage.webTopLogedIn = false;
			for(int idx = 3; idx < dataDump.getValue("MailIdList").split("\\-").length; idx++){
				OpenMailInInbox openMailInInbox = new OpenMailInInbox(frameworkContext);
				openMailInInbox.openSUMailInInbox(dataDump.getValue("MailIdList").split("\\-")[idx]);
				ActivateUser activateUser = new ActivateUser(frameworkContext);
				activateUser.activateUser();
				CreateBCPPortalUserPassword createBCPPortalUserPassword = new CreateBCPPortalUserPassword(frameworkContext);
				createBCPPortalUserPassword.createBCPPortalPassword();
				BCPLogin loginBCP = new BCPLogin(frameworkContext);
				loginBCP.loginBCP(dataDump.getValue("MailIdList").split("\\-")[idx]);
				loginBCP.signOut();
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Service User Activation", "Service User Activation Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Service User Activation Failed!!! " + Ex.getMessage();			
			log.error(eMsg);
		}
	}

	@Test(priority = 23500, groups={"New Connect", "All"})
	public void waitForResponseFromBCPPortal() throws Exception {
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		WaitForResponseFromBCPPortal waitForResponseFromBCPPortal = new WaitForResponseFromBCPPortal(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}
			waitForResponseFromBCPPortal.waitForResponseFromBCPPortal();
		} catch (Exception Ex) {
			report.reportHardFailEvent("SYSTEM: Wait for Response from BCP Portal",
					"SYSTEM: Wait for Response from BCP Portal Failed!!!", Status.FAIL);
			String eMsg = "Error in SYSTEM: Wait for Response from BCP Portal --- " + Ex.getMessage();
			log.error(eMsg);

		}
	}

	@Test(priority = 24000, groups={"New Connect", "All"})
	public void performCPEInstallation_DOI() throws Exception {

		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		PerformCPEInstallation_DOI performCPEInstallation_DOI = new PerformCPEInstallation_DOI(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}
			performCPEInstallation_DOI.performCPEInstallation_DOI("Completed");
		} catch (Exception Ex) {
			report.reportHardFailEvent("INSTALL TECH: Perform CPE Installation (DOI)",
					"INSTALL TECH: Perform CPE Installation (DOI) Failed!!!", Status.FAIL);
			String eMsg = "Error in INSTALL TECH: Perform CPE Installation (DOI) --- " + Ex.getMessage();
			log.error(eMsg);

		}
	}

	@Test(priority = 24500, groups={"New Connect", "All"})
	public void waitForDOCDate() throws Exception {

		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		WaitForDOCDate waitForDOCDate = new WaitForDOCDate(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}
			waitForDOCDate.waitForDOCDate();
		} catch (Exception Ex) {
			report.reportHardFailEvent("SYSTEM: Wait For DOC Date", "SYSTEM: Wait For DOC Date Failed!!!", Status.FAIL);
			String eMsg = "Error in SYSTEM: Wait For DOC Date --- " + Ex.getMessage();
			log.error(eMsg);

		}
	}

	@Test(priority = 25000, groups={"New Connect", "All"})
	public void cutoverBVE() throws Exception {

		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		CutoverBVE cutoverBVE = new CutoverBVE(frameworkContext);
		WorkOrderComments workOrderComments = new WorkOrderComments(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}
			cutoverBVE.cutoverBVE();
			workOrderComments.PONVerification();
		} catch (Exception Ex) {
			report.reportHardFailEvent("INSTALL TECH: Cutover BVE", "INSTALL TECH: Cutover BVE Failed!!!", Status.FAIL);
			String eMsg = "Error in INSTALL TECH: Cutover BVE --- " + Ex.getMessage();
			log.error(eMsg);

		}
	}

	@Test(priority = 25500, groups={"New Connect", "All"})
	public void waitForResponseFromNeustar() throws Exception {

		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		WaitForResponseFromNeustar waitForResponseFromNeustar = new WaitForResponseFromNeustar(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}
			waitForResponseFromNeustar.waitForResponseFromNeustar();
		} catch (Exception Ex) {
			report.reportHardFailEvent("SYSTEM: Wait for Response from Neustar (DO NOT SET RESULT)",
					"SYSTEM: Wait for Response from Neustar (DO NOT SET RESULT) Failed!!!", Status.FAIL);
			String eMsg = "Error in SYSTEM: Wait for Response from Neustar (DO NOT SET RESULT) --- " + Ex.getMessage();
			log.error(eMsg);

		}
	}

	@Test(priority = 26000, groups={"New Connect", "All"})
	public void errorProvisioningDAOrDLOrCNAMatNeustar() throws Exception {

		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		ErrorProvisioningDAOrDLOrCNAMatNeustar errorProvisioningDAOrDLOrCNAMatNeustar = new ErrorProvisioningDAOrDLOrCNAMatNeustar(
				frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}
			errorProvisioningDAOrDLOrCNAMatNeustar.errorProvisioningDAOrDLOrCNAMatNeustar();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Error Provisioning DA/DL/CNAM at Neustar",
					"Error Provisioning DA/DL/CNAM at Neustar Failed!!!", Status.FAIL);
			String eMsg = "Error in Error Provisioning DA/DL/CNAM at Neustar --- " + Ex.getMessage();
			log.error(eMsg);

		}
	}

	@Test(priority = 26500, groups={"New Connect", "All"})
	public void provisionDAOrDLOrCNAMatNeustarManually() throws Exception {

		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		ProvisionDAOrDLOrCNAMatNeustarManually provisionDAOrDLOrCNAMatNeustarManually = new ProvisionDAOrDLOrCNAMatNeustarManually(
				frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}
			provisionDAOrDLOrCNAMatNeustarManually.provisionDAOrDLOrCNAMatNeustarManually();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Provision DA/DL/CNAM at Neustar Manually",
					"Provision DA/DL/CNAM at Neustar Manually Failed!!!", Status.FAIL);
			String eMsg = "Error in Provision DA/DL/CNAM at Neustar Manually --- " + Ex.getMessage();
			log.error(eMsg);

		}
	}

	@Test(priority = 27000, groups={"New Connect", "All"})
	public void waitAtLeast2MinutesBeforeCheckingCEMPStatus() throws Exception {
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		WaitAtLeast2MinutesBeforeCheckingCEMPStatus waitAtLeast2MinutesBeforeCheckingCEMPStatus = new WaitAtLeast2MinutesBeforeCheckingCEMPStatus(
				frameworkContext);
		ErrorInitiatingRequestToAddDevicesToCEMP_VGS errorInitiatingRequestToAddDevicesToCEMP_VGS = new ErrorInitiatingRequestToAddDevicesToCEMP_VGS(frameworkContext);
		TicketOpenedWithCEMPTeamForManualProvisioning ticketOpenedWithCEMPTeamForManualProvisioning = new TicketOpenedWithCEMPTeamForManualProvisioning(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}
			errorInitiatingRequestToAddDevicesToCEMP_VGS.errorInitiatingRequestToAddDevicesToCEMP_VGS();
			ticketOpenedWithCEMPTeamForManualProvisioning.ticketOpenedWithCEMPTeamForManualProvisioning();
			waitAtLeast2MinutesBeforeCheckingCEMPStatus.waitAtLeast2MinutesBeforeCheckingCEMPStatus();
		} catch (Exception Ex) {
			report.reportHardFailEvent("SYSTEM: Wait at least 2 Minutes before checking CEMP Status",
					"SYSTEM: Wait at least 2 Minutes before checking CEMP Status Failed!!!", Status.FAIL);
			String eMsg = "Error in SYSTEM: Wait at least 2 Minutes before checking CEMP Status --- " + Ex.getMessage();
			log.error(eMsg);

		}
	}

	@Test(priority = 27500, groups={"New Connect", "All"})
	public void performTollFreeCIC() throws Exception {
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		PerformTollFreeCIC performTollFreeCIC = new PerformTollFreeCIC(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}
			performTollFreeCIC.performTollFreeCIC();
		} catch (Exception Ex) {
			report.reportHardFailEvent("INSTALL TECH: Perform Toll Free CIC",
					"INSTALL TECH: Perform Toll Free CIC Failed!!!", Status.FAIL);
			String eMsg = "Error in INSTALL TECH: Perform Toll Free CIC --- " + Ex.getMessage();
			log.error(eMsg);

		}
	}

	@Test(priority = 28000, groups={"New Connect", "All"})
	public void governmentProgramOrder_CustomerAcceptsServiceStart() throws Exception {

		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		GovernmentProgramOrder_CustomerAcceptsServiceStart governmentProgramOrder_CustomerAcceptsServiceStart = new GovernmentProgramOrder_CustomerAcceptsServiceStart(
				frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}
			governmentProgramOrder_CustomerAcceptsServiceStart.governmentProgramOrder_CustomerAcceptsServiceStart();
		} catch (Exception Ex) {
			report.reportHardFailEvent("SDPM: Government Program Order - Customer Accepts Service Start",
					"SDPM: Government Program Order - Customer Accepts Service Start Update Failed!!!", Status.FAIL);
			String eMsg = "Error in SDPM: Government Program Order - Customer Accepts Service Start --- "
					+ Ex.getMessage();
			log.error(eMsg);

		}
	}
	

	@Test(priority = 28500, groups={"New Connect", "All"})
	public void performBillingUpdate() throws Exception {
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		PerformBillingUpdate performBillingUpdate = new PerformBillingUpdate(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}
			new ErrorOccurredWhenAddingDevicesToCEMP_VGS(frameworkContext).errorOccurredWhenAddingDevicesToCEMP_VGS();
			new TicketOpenedWithCEMPTeamForManualProvisioning(frameworkContext).ticketOpenedWithCEMPTeamForManualProvisioning();
			performBillingUpdate.performBillingUpdate();
		} catch (Exception Ex) {
			report.reportHardFailEvent("BILLING: Perform Billing Update", "BILLING: Perform Billing Update Failed!!!",
					Status.FAIL);
			String eMsg = "Error in BILLING: Perform Billing Update --- " + Ex.getMessage();
			log.error(eMsg);

		}
		
	}
	public String getSOAFileDetails()
	{
		try{
			String dataFilePattern = "", importFilePattern = "";
			WebtopPage webtopPage = new WebtopPage(frameworkContext);
			String folderPath = System.getProperty("user.dir") + "\\src\\test\\resources\\WebTopSOADataFiles\\";
			if(!frameworkContext.getDataTable().getValue("SOAFileNamePattern").isEmpty())
				dataFilePattern = frameworkContext.getDataTable().getValue("SOAFileNamePattern");
			if(!frameworkContext.getDataTable().getValue("ImportDataFileNamePattern").isEmpty())
				importFilePattern = frameworkContext.getDataTable().getValue("ImportDataFileNamePattern");
			String dataFileName = webtopPage.getLatestFile(folderPath, dataFilePattern);
			String ImportFileName = webtopPage.getLatestFile(folderPath, importFilePattern);
			dataDump.setValue("dataFileName", dataFileName);
			if(ImportFileName != null)
				dataDump.setValue("ImportFileName", ImportFileName);
			String[] OpportunityId = dataFileName.split("[.]");
			Pattern pattern = Pattern.compile("\\d+$");
			Matcher matcher = pattern.matcher(OpportunityId[0]);
			if (matcher.find()) {
				dataDump.setValue("SFOpportunityId", matcher.group(0));
				return matcher.group(0);
			}
			else
			{
				log.error("SF Opportunity Id is not there in the SOA file name with pattern: \\d+$");
				return "";
			}			 
		}
		catch(Exception ex)
		{
			log.error("SF Opportunity Id is not there in the SOA file name with pattern: \\d+$");
			report.reportHardFailEvent("Get SOA Details(Opportunity id from SOA file name)", "Getting Opportunity id from SOA file name is failed");
			return "";
		}
		
	}
	
}


