package com.comcast.WebTopCases;

import org.testng.annotations.Test;

import com.comcast.commons.WebtopPage;
import com.comcast.pages.simon.Create_New_MACD;
import com.comcast.pages.simon.MACDConfigurationPage;
import com.comcast.pages.simon.OrderSummaryPage;
import com.comcast.pages.simon.SIMONMainPage;
import com.comcast.pages.simon.Settings;
import com.comcast.pages.simon.SimonLogInPage;

public class SimonMACDValidations extends CommonE2EFlow {

	/***************************
	 * BEGIN: BVE_SIMON_MACD_Add_Edit_Remove_RCF_TN_on_existing_customer
	 *****************************************************/

	@Test(priority = 7450, groups = { "New Connect", "All" })
	public void SIMON_MACD_Add_RCF_TN() {
		try {
			Settings createMACDforthisSite = new Settings(frameworkContext);
			Create_New_MACD createnewMACD = new Create_New_MACD(frameworkContext);
			MACDConfigurationPage verifyCustomerDetails = new MACDConfigurationPage(frameworkContext);
			SIMONMainPage SimonMainPage = new SIMONMainPage(frameworkContext);
			OrderSummaryPage orderSummaryPage = new OrderSummaryPage(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				SimonMainPage.SFOpportunityIDSearch();
			}
			createMACDforthisSite.createMACDForThisSite();
			createnewMACD.createNewMACD("New MACD (Existing Customer)", "MACD", "24");
			verifyCustomerDetails.customerDetails("New MACD (Existing Customer)", "MACD", "24");
			verifyCustomerDetails.clickServiceDetails();
			verifyCustomerDetails.clickHereToAddanItem();
			verifyCustomerDetails.serviceDetails("Add", "RCF TN");
			verifyCustomerDetails.addRCFTN("856-123-1234", "879-123-5489", "New", "Pennsylvania");
			orderSummaryPage.clickOrderSummary();
			orderSummaryPage.rcfAddDetailsSummary("856-123-1234", "879-123-5489", "New", "Pennsylvania");
			orderSummaryPage.downloadPDF();
			WebtopPage webtopPage = new WebtopPage(frameworkContext);
			String pdfText = webtopPage.getPDFContent(dataDump.getValue("OrderSummaryPDF"));
			boolean status;
			status = (pdfText.contains("##New MACD MACD 24##") && pdfText.contains(
					"##RCF Add Details##TN Translation TN Seat##856-123-1234 879-123-5489##Port Type Existing Service State##New Pennsylvania##"));
			orderSummaryPage.PDFVerification(status, "RCF TN  Add scenario verificatio in PDF File");
			orderSummaryPage.closeOrderSummary();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Add Toll Free TN for site", "RCF TN  Add scenario Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "RCF TN  Add scenario Failed" + Ex.getMessage();
			log.error(eMsg);
		}
	}

	@Test(priority = 7500, groups = { "New Connect", "All" })
	public void SIMON_MACD_Edit_RCF_TN() {
		try {
			Settings createMACDforthisSite = new Settings(frameworkContext);
			Create_New_MACD createnewMACD = new Create_New_MACD(frameworkContext);
			MACDConfigurationPage verifyCustomerDetails = new MACDConfigurationPage(frameworkContext);
			SIMONMainPage SimonMainPage = new SIMONMainPage(frameworkContext);
			OrderSummaryPage orderSummaryPage = new OrderSummaryPage(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				SimonMainPage.SFOpportunityIDSearch();
				createMACDforthisSite.createMACDForThisSite();
				createnewMACD.createNewMACD("New MACD (Existing Customer)", "MACD", "24");
			}
			verifyCustomerDetails.clickServiceDetails();
			verifyCustomerDetails.clickHereToAddanItem();
			verifyCustomerDetails.serviceDetails("Edit/Change", "RCF TN");
			verifyCustomerDetails.editRCFTN("856-123-1234", "879-123-5489", "EditCFN", "EditCLN", "EditNFN", "EditNLN");
			orderSummaryPage.clickOrderSummary();
			orderSummaryPage.rcfEditDetailsSummary("856-123-1234", "879-123-5489", "EditCFN", "EditCLN", "EditNFN",
					"EditNLN");
			orderSummaryPage.downloadPDF();
			WebtopPage webtopPage = new WebtopPage(frameworkContext);
			String pdfText = webtopPage.getPDFContent(dataDump.getValue("OrderSummaryPDF"));
			boolean status;
			status = (pdfText.contains("##New MACD MACD 24##") && pdfText.contains(
					"##RCF Edit Details##TN Translation TN##856-123-1234 879-123-5489##New First Name New Last Name##EditNFN EditNLN##Current First Name Current Last Name##EditCFN EditCLN##"));
			orderSummaryPage.PDFVerification(status, "RCF TN Edit scenario verification in PDF File");
			orderSummaryPage.closeOrderSummary();
		} catch (Exception Ex) {
			report.reportHardFailEvent("RCF TN Edit scenario verification in PDF File ",
					"RCF TN Edit scenario verification in PDF File failed" + Ex.getMessage());
			eMsg = report.getMessage() + "RCF TN Edit scenario verification in PDF File failed!!! " + Ex.getMessage();
			log.error(eMsg);
		}
	}

	@Test(priority = 7550, groups = { "New Connect", "All" })
	public void SIMON_MACD_Remove_RCF_on_existing_customer() {
		try {
			Settings createMACDforthisSite = new Settings(frameworkContext);
			Create_New_MACD createnewMACD = new Create_New_MACD(frameworkContext);
			MACDConfigurationPage verifyCustomerDetails = new MACDConfigurationPage(frameworkContext);
			SIMONMainPage SimonMainPage = new SIMONMainPage(frameworkContext);
			OrderSummaryPage orderSummaryPage = new OrderSummaryPage(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				SimonMainPage.SFOpportunityIDSearch();
				createMACDforthisSite.createMACDForThisSite();
				createnewMACD.createNewMACD("New MACD (Existing Customer)", "MACD", "24");
			}
			verifyCustomerDetails.clickServiceDetails();
			verifyCustomerDetails.clickHereToAddanItem();

			verifyCustomerDetails.serviceDetails("Remove", "RCF TN");
			verifyCustomerDetails.removeRCFTN("856-123-1234");
			orderSummaryPage.clickOrderSummary();
			orderSummaryPage.rcfRemoveDetailsSummary("856-123-1234");
			orderSummaryPage.downloadPDF();
			WebtopPage webtopPage = new WebtopPage(frameworkContext);
			String pdfText = webtopPage.getPDFContent(dataDump.getValue("OrderSummaryPDF"));
			boolean status;
			status = (pdfText.contains("##RCF Remove Details##TN##856-123-1234##"));
			orderSummaryPage.PDFVerification(status, "RCF Remove scenario verification in PDF File");
			orderSummaryPage.closeOrderSummary();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Removing the RCF TN", "Removing the RCF TN failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Verification of the Removal of Toll Free TN-Failed!!! " + Ex.getMessage();
			log.error(eMsg);
		}
	}

	/***************************
	 * END: BVE_SIMON_MACD_Add_Edit_Remove_RCF_TN_on_existing_customer
	 *****************************************************/

	/***************************
	 * BEGIN: BVE_SIMON_MACD_Add_Edit_Remove_TF_TN_on_existing_customer
	 *****************************************************/

	@Test(priority = 7300, groups = { "New Connect", "All" })
	public void SIMON_MACD_Add_TF_TN() {
		try {
			Settings createMACDforthisSite = new Settings(frameworkContext);
			Create_New_MACD createnewMACD = new Create_New_MACD(frameworkContext);
			MACDConfigurationPage verifyCustomerDetails = new MACDConfigurationPage(frameworkContext);
			SIMONMainPage SimonMainPage = new SIMONMainPage(frameworkContext);
			OrderSummaryPage orderSummaryPage = new OrderSummaryPage(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				SimonMainPage.SFOpportunityIDSearch();
			}
			createMACDforthisSite.createMACDForThisSite();
			createnewMACD.createNewMACD("New MACD (Existing Customer)", "MACD", "24");
			verifyCustomerDetails.customerDetails("New MACD (Existing Customer)", "MACD", "24");
			verifyCustomerDetails.clickServiceDetails();
			verifyCustomerDetails.clickHereToAddanItem();
			verifyCustomerDetails.serviceDetails("Add", "TF TN");
			verifyCustomerDetails.addTollFreeTNDetails("8451231478");
			orderSummaryPage.clickOrderSummary();
			orderSummaryPage.tollFreeAddOrderSummary("845-123-1478", "US", "New");
			orderSummaryPage.downloadPDF();
			WebtopPage webtopPage = new WebtopPage(frameworkContext);
			String pdfText = webtopPage.getPDFContent(dataDump.getValue("OrderSummaryPDF"));
			boolean status;
			status = (pdfText.contains("#New MACD MACD 24#") && pdfText
					.contains("#Toll Free Add Details##TN Translation TN Area of Service##845-123-1478 US##"));
			orderSummaryPage.PDFVerification(status, "Toll Free TN Add scenario verification in PDF File");
			orderSummaryPage.closeOrderSummary();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Add Toll Free TN for site",
					"Create MACD for add Toll Free failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Create MACD for add Toll Free failed" + Ex.getMessage();
			log.error(eMsg);
		}
	}

	@Test(priority = 7350, groups = { "New Connect", "All" })
	public void SIMON_MACD_Edit_TF_TN() {
		try {
			Settings createMACDforthisSite = new Settings(frameworkContext);
			Create_New_MACD createnewMACD = new Create_New_MACD(frameworkContext);
			MACDConfigurationPage verifyCustomerDetails = new MACDConfigurationPage(frameworkContext);
			SIMONMainPage SimonMainPage = new SIMONMainPage(frameworkContext);
			OrderSummaryPage orderSummaryPage = new OrderSummaryPage(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				SimonMainPage.SFOpportunityIDSearch();
				createMACDforthisSite.createMACDForThisSite();
				createnewMACD.createNewMACD("New MACD (Existing Customer)", "MACD", "24");
			}
			verifyCustomerDetails.clickServiceDetails();
			verifyCustomerDetails.clickHereToAddanItem();
			verifyCustomerDetails.serviceDetails("Edit/Change", "TF TN");
			verifyCustomerDetails.editTollFreeTNDetails("845-123-1478", "TFEditFNEdit", "TFEditLNEdit",
					"TFEditFNEditNew", "TFEditLNEditNew");
			orderSummaryPage.clickOrderSummary();
			orderSummaryPage.tollFreeEditOrderSummary("845-123-1478", "TFEditFNEdit", "TFEditLNEdit", "TFEditFNEditNew",
					"TFEditLNEditNew");
			orderSummaryPage.downloadPDF();
			WebtopPage webtopPage = new WebtopPage(frameworkContext);
			String pdfText = webtopPage.getPDFContent(dataDump.getValue("OrderSummaryPDF"));
			boolean status;
			status = (pdfText.contains("##New MACD MACD 24##") && pdfText.contains(
					"##Toll Free Edit Details##TN Translation TN##845-123-1478##New First Name New Last Name##TFEditFNEditNew TFEditLNEditNew##Current First Name Current Last Name##TFEditFNEdit TFEditLNEdit##"));
			orderSummaryPage.PDFVerification(status, "TN Toll Free Edit scenario verification in PDF File");
			orderSummaryPage.closeOrderSummary();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Edit Toll Free Flag for the Seat ",
					"Edit Toll Free Flag for site failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Edit Toll Free Flag for site failed!!! " + Ex.getMessage();
			log.error(eMsg);
		}
	}

	@Test(priority = 7400, groups = { "New Connect", "All" })
	public void SIMON_MACD_Remove_TF_TN_on_existing_customer() {
		try {
			Settings createMACDforthisSite = new Settings(frameworkContext);
			Create_New_MACD createnewMACD = new Create_New_MACD(frameworkContext);
			MACDConfigurationPage verifyCustomerDetails = new MACDConfigurationPage(frameworkContext);
			SIMONMainPage SimonMainPage = new SIMONMainPage(frameworkContext);
			OrderSummaryPage orderSummaryPage = new OrderSummaryPage(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				SimonMainPage.SFOpportunityIDSearch();
				createMACDforthisSite.createMACDForThisSite();
				createnewMACD.createNewMACD("New MACD (Existing Customer)", "MACD", "24");
			}
			verifyCustomerDetails.clickServiceDetails();
			verifyCustomerDetails.clickHereToAddanItem();
			verifyCustomerDetails.serviceDetails("Remove", "TF TN");
			verifyCustomerDetails.removeTollFreeTN("845-123-1478");
			orderSummaryPage.clickOrderSummary();
			orderSummaryPage.tollFreeRemoveOrderSummary("845-123-1478");
			orderSummaryPage.downloadPDF();
			WebtopPage webtopPage = new WebtopPage(frameworkContext);
			String pdfText = webtopPage.getPDFContent(dataDump.getValue("OrderSummaryPDF"));
			boolean status;
			status = (pdfText.contains("##Toll Free Remove Details##TN##"));
			orderSummaryPage.PDFVerification(status, "TN Toll Free Remove scenario verification in PDF File");
			orderSummaryPage.closeOrderSummary();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Removing the Toll Free TN",
					"Removing the Toll Free TN failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Verification of the Removal of Toll Free TN-Failed!!! " + Ex.getMessage();
			log.error(eMsg);
		}
	}

	/***************************
	 * END: BVE_SIMON_MACD_Add_Edit_Remove_TF_TN_on_existing_customer
	 *****************************************************/

	/**************************
	 * BEGIN: BVE_SIMON_MACD_Add_Edit_Remove_UC_Seat_on_existing_customer
	 *****************************************************/
	@Test(priority = 7100, groups = { "New Connect", "All" })
	public void SIMON_MACD_Add_on_existing_customer() {
		try {
			Settings createMACDforthisSite = new Settings(frameworkContext);
			Create_New_MACD createnewMACD = new Create_New_MACD(frameworkContext);
			MACDConfigurationPage verifyCustomerDetails = new MACDConfigurationPage(frameworkContext);
			SIMONMainPage SimonMainPage = new SIMONMainPage(frameworkContext);
			OrderSummaryPage orderSummaryPage = new OrderSummaryPage(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				SimonMainPage.SFOpportunityIDSearch();
			}
			createMACDforthisSite.createMACDForThisSite();
			createnewMACD.createNewMACD("New MACD (Existing Customer)", "MACD", "24");
			verifyCustomerDetails.customerDetails("New MACD (Existing Customer)", "MACD", "24");
			verifyCustomerDetails.clickServiceDetails();
			verifyCustomerDetails.clickHereToAddanItem();
			verifyCustomerDetails.serviceDetails("Add", "Seat");
			verifyCustomerDetails.addSeatForMACDInSimon("UC Seat", "Standard User", "AddSeat", "TestAdd");
			orderSummaryPage.verifyOrderDetails("New MACD", "MACD", "24");
			orderSummaryPage.verifySeatSettingsInSeatAddDetails("Add", "Seat", "AddSeat TestAdd", "UC Seat", "AddSeat",
					"TestAdd", "Standard User");
			orderSummaryPage.downloadPDF();
			WebtopPage webtopPage = new WebtopPage(frameworkContext);
			String pdfText = webtopPage.getPDFContent(dataDump.getValue("OrderSummaryPDF"));
			boolean status;
			status = pdfText.contains("##New MACD MACD 24##") && pdfText.contains(
					"##Seat Add Details##Seat Name Phone Number Ext##AddSeat TestAdd##First Name Last Name Email Address##AddSeat TestAdd##Seat Type Port Type Existing Service##UC Seat##Seat Purpose Business Continuity Number Group BTN##Standard User No##");
			orderSummaryPage.PDFVerification(status, "UC Seat Add scenario verification in PDF File");
			orderSummaryPage.closeOrderSummary();

		} catch (Exception Ex) {
			report.reportHardFailEvent("Create MACD for add site", "Create MACD for add site failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Create MACD for add site in SIMON Failed!!! " + Ex.getMessage();
			log.error(eMsg);
		}
	}

	@Test(priority = 7150, groups = { "New Connect", "All" })
	public void SIMON_MACD_Edit_on_existing_customer() {
		try {
			Settings createMACDforthisSite = new Settings(frameworkContext);
			Create_New_MACD createnewMACD = new Create_New_MACD(frameworkContext);
			MACDConfigurationPage verifyCustomerDetails = new MACDConfigurationPage(frameworkContext);
			SIMONMainPage SimonMainPage = new SIMONMainPage(frameworkContext);
			OrderSummaryPage orderSummaryPage = new OrderSummaryPage(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				SimonMainPage.SFOpportunityIDSearch();
				createMACDforthisSite.createMACDForThisSite();
				createnewMACD.createNewMACD("New MACD (Existing Customer)", "MACD", "24");
			}
			verifyCustomerDetails.clickServiceDetails();
			verifyCustomerDetails.clickHereToAddanItem();
			verifyCustomerDetails.serviceDetails("Edit/Change", "Seat");
			verifyCustomerDetails.editSeatTypeForMACDInSimon("AddSeat", "TestAdd", "Change To", "UC Seat",
					"Basic Seat");
			orderSummaryPage.verifySeatEditDetails("Edit", "Seat", "Basic Seat");
			orderSummaryPage.downloadPDF();
			WebtopPage webtopPage = new WebtopPage(frameworkContext);
			String pdfText = webtopPage.getPDFContent(dataDump.getValue("OrderSummaryPDF"));
			boolean status;
			status = pdfText.contains("##New MACD MACD 24##") && pdfText.contains(
					"##Seat Edit Details##First Name Last Name TN EXT##AddSeat TestAdd##Changes##Action Item Change To##Edit Seat Type Basic Seat##");
			orderSummaryPage.PDFVerification(status, "UC Seat Edit scenario verification in PDF File");
			orderSummaryPage.closeOrderSummary();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Create MACD for add site", "Create MACD for add site failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Create MACD for add site in SIMON Failed!!! " + Ex.getMessage();
			log.error(eMsg);
		}
	}

	@Test(priority = 7151, groups = { "New Connect", "All" })
	public void SIMON_MACD_Remove_on_existing_customer() {
		try {
			Settings createMACDforthisSite = new Settings(frameworkContext);
			Create_New_MACD createnewMACD = new Create_New_MACD(frameworkContext);
			MACDConfigurationPage verifyCustomerDetails = new MACDConfigurationPage(frameworkContext);
			SIMONMainPage SimonMainPage = new SIMONMainPage(frameworkContext);
			OrderSummaryPage orderSummaryPage = new OrderSummaryPage(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				SimonMainPage.SFOpportunityIDSearch();
				createMACDforthisSite.createMACDForThisSite();
				createnewMACD.createNewMACD("New MACD (Existing Customer)", "MACD", "24");
			}
			verifyCustomerDetails.clickServiceDetails();
			verifyCustomerDetails.clickHereToAddanItem();
			verifyCustomerDetails.serviceDetails("Remove", "Seat");
			verifyCustomerDetails.enterRemoveDetailsinServiceDetails("AddSeat", "TestAdd");
			orderSummaryPage.clickOrderSummary();
			orderSummaryPage.removeSeatOrderSummary("AddSeat TestAdd");
			orderSummaryPage.downloadPDF();
			WebtopPage webtopPage = new WebtopPage(frameworkContext);
			String pdfText = webtopPage.getPDFContent(dataDump.getValue("OrderSummaryPDF"));
			boolean status;
			status = pdfText.contains("##Seat Remove Details##Seat Name Phone Number Ext##AddSeat TestAdd##Notes##");
			orderSummaryPage.PDFVerification(status, "UC Seat Remove scenario verification in PDF File");

			orderSummaryPage.closeOrderSummary();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Removing the seat", "Create MACD for add site failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Verification of the Removed seat in the order summary-Failed!!! "
					+ Ex.getMessage();
			log.error(eMsg);
		}
	}

	/**************************
	 * END:BVE_SIMON_MACD_Add_Edit_Remove_UC_Seat_on_existing_customer
	 *****************************************************/
	/**************************
	 * BEGIN:BVE_SIMON_MACD_Add_Remove_Call_Queue_feature_on_existing_customer
	 *****************************************************/
	@Test(priority = 7200, groups = { "New Connect", "All" })
	public void BVE_SIMON_MACD_Add_CallQueue_on_existing_customer() {
		try {
			Settings createMACDforthisSite = new Settings(frameworkContext);
			Create_New_MACD createnewMACD = new Create_New_MACD(frameworkContext);
			MACDConfigurationPage verifyCustomerDetails = new MACDConfigurationPage(frameworkContext);
			SIMONMainPage SimonMainPage = new SIMONMainPage(frameworkContext);
			OrderSummaryPage orderSummaryPage = new OrderSummaryPage(frameworkContext);
			WebtopPage webtopPage = new WebtopPage(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				SimonMainPage.SFOpportunityIDSearch();
			}
			createMACDforthisSite.createMACDForThisSite();
			createnewMACD.createNewMACD("New MACD (Existing Customer)", "MACD", "24");
			verifyCustomerDetails.customerDetails("New MACD (Existing Customer)", "MACD", "24");
			verifyCustomerDetails.clickServiceDetails();
			verifyCustomerDetails.clickHereToAddanItem();
			verifyCustomerDetails.serviceDetails("Add", "Call Queue");
			verifyCustomerDetails.enterCallQueueDetails("AddCallQueue");
			orderSummaryPage.clickOrderSummary();
			orderSummaryPage.addCallQueueOrderSummary("Add", "Call Queue", "AddCallQueue", "Eastern");
			orderSummaryPage.downloadPDF();
			String pdfText = webtopPage.getPDFContent(dataDump.getValue("OrderSummaryPDF"));
			boolean status;
			status = (pdfText.contains("##New MACD MACD 24##") && pdfText.contains(
					"##Call Queue Add Details##Call Queue Name First Name Last Name##AddCallQueue AddCallQueue##Time Zone Port Type Existing Service##Eastern##"));
			orderSummaryPage.PDFVerification(status, "Call Queue Add scenario verification failed in in PDF File");
			orderSummaryPage.closeOrderSummary();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Adding CallQueue for site",
					"Adding CallQueue for site failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Adding CallQueue for site failed!!! " + Ex.getMessage();
			log.error(eMsg);
		}
	}

	@Test(priority = 7250, groups = { "New Connect", "All" })
	public void BVE_SIMON_MACD_Remove_CallQueue_on_existing_customer() {
		try {
			Settings createMACDforthisSite = new Settings(frameworkContext);
			Create_New_MACD createnewMACD = new Create_New_MACD(frameworkContext);
			MACDConfigurationPage verifyCustomerDetails = new MACDConfigurationPage(frameworkContext);
			SIMONMainPage SimonMainPage = new SIMONMainPage(frameworkContext);
			OrderSummaryPage orderSummaryPage = new OrderSummaryPage(frameworkContext);
			WebtopPage webtopPage = new WebtopPage(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				SimonMainPage.SFOpportunityIDSearch();
				createMACDforthisSite.createMACDForThisSite();
				createnewMACD.createNewMACD("New MACD (Existing Customer)", "MACD", "24");
				verifyCustomerDetails.customerDetails("New MACD (Existing Customer)", "MACD", "24");
			}
			verifyCustomerDetails.clickServiceDetails();
			verifyCustomerDetails.clickHereToAddanItem();
			verifyCustomerDetails.serviceDetails("Remove", "Call Queue");
			verifyCustomerDetails.removeCallQueue("RemoveCallQueueName");
			orderSummaryPage.clickOrderSummary();
			orderSummaryPage.removeCallQueueOrderSummary("RemoveCallQueueName");
			orderSummaryPage.downloadPDF();
			String pdfText = webtopPage.getPDFContent(dataDump.getValue("OrderSummaryPDF"));
			boolean status;
			status = (pdfText.contains("##New MACD MACD 24##") && pdfText
					.contains("##Call Queue Remove Details##Call Queue Name Phone Number Ext##RemoveCallQueueName##"));
			orderSummaryPage.PDFVerification(status, "Call Queue Remove scenario verification in PDF File");
			orderSummaryPage.closeOrderSummary();

		} catch (Exception Ex) {
			report.reportHardFailEvent("Adding CallQueue for site",
					"Remove CallQueue for site failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Removing CallQueue for site failed!!! " + Ex.getMessage();
			log.error(eMsg);
		}

	}

	/**************************
	 * END:BVE_SIMON_MACD_Add_Remove_Call_Queue_feature_on_existing_customer
	 *****************************************************/

	/**************************
	 * BEGIN:BVE_SIMON_MACD_Add_Remove_Receptionist_feature_on_existing_customer
	 *****************************************************/
	@Test(priority = 7600, groups = { "New Connect", "All" })
	public void SIMON_MACD_Add_Receptionist() {
		try {
			Settings createMACDforthisSite = new Settings(frameworkContext);
			Create_New_MACD createnewMACD = new Create_New_MACD(frameworkContext);
			MACDConfigurationPage verifyCustomerDetails = new MACDConfigurationPage(frameworkContext);
			SIMONMainPage SimonMainPage = new SIMONMainPage(frameworkContext);
			OrderSummaryPage orderSummaryPage = new OrderSummaryPage(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				SimonMainPage.SFOpportunityIDSearch();
			}
			createMACDforthisSite.createMACDForThisSite();
			createnewMACD.createNewMACD("New MACD (Existing Customer)", "MACD", "24");
			verifyCustomerDetails.customerDetails("New MACD (Existing Customer)", "MACD", "24");
			verifyCustomerDetails.clickServiceDetails();
			verifyCustomerDetails.clickHereToAddanItem();
			verifyCustomerDetails.serviceDetails("Add", "Receptionist");
			verifyCustomerDetails.addReceptionist("RecepName", "RecepUser1");
			orderSummaryPage.clickOrderSummary();
			orderSummaryPage.addReceptionistOrderSummary("RecepName", "RecepUser1");
			orderSummaryPage.downloadPDF();
			WebtopPage webtopPage = new WebtopPage(frameworkContext);
			String pdfText = webtopPage.getPDFContent(dataDump.getValue("OrderSummaryPDF"));
			boolean status;
			status = (pdfText.contains("##New MACD MACD 24##") && pdfText
					.contains("#Receptionist Add Details##Receptionist Name##RecepName##Users##RecepUser1##Notes"));
			orderSummaryPage.PDFVerification(status, "Receptionist Add scenario verification in PDF File");
			orderSummaryPage.closeOrderSummary();

		} catch (Exception Ex) {
			report.reportHardFailEvent("Receptionist Add scenario verification in PDF File",
					"Receptionist Add scenario failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Receptionist Add scenario in SIMON!!! " + Ex.getMessage();
			log.error(eMsg);
		}
	}

	@Test(priority = 7650, groups = { "New Connect", "All" })
	public void SIMON_MACD_RemoveReceptionist() {
		try {
			Settings createMACDforthisSite = new Settings(frameworkContext);
			Create_New_MACD createnewMACD = new Create_New_MACD(frameworkContext);
			MACDConfigurationPage verifyCustomerDetails = new MACDConfigurationPage(frameworkContext);
			SIMONMainPage SimonMainPage = new SIMONMainPage(frameworkContext);
			OrderSummaryPage orderSummaryPage = new OrderSummaryPage(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				SimonMainPage.SFOpportunityIDSearch();
				createMACDforthisSite.createMACDForThisSite();
				createnewMACD.createNewMACD("New MACD (Existing Customer)", "MACD", "24");
			}
			verifyCustomerDetails.clickServiceDetails();
			verifyCustomerDetails.clickHereToAddanItem();
			verifyCustomerDetails.serviceDetails("Remove", "Receptionist");
			verifyCustomerDetails.removeReceptionist("RecepName", "856-123-1234");
			orderSummaryPage.clickOrderSummary();
			orderSummaryPage.removeReceptionistOrderSummary("RecepName", "856-123-1234");
			orderSummaryPage.downloadPDF();
			WebtopPage webtopPage = new WebtopPage(frameworkContext);
			String pdfText = webtopPage.getPDFContent(dataDump.getValue("OrderSummaryPDF"));
			boolean status;
			status = (pdfText.contains("##New MACD MACD 24##") && pdfText.contains(
					"##Receptionist Remove Details##Receptionist Name Phone Number Ext##RecepName 856-123-1234##"));
			orderSummaryPage.PDFVerification(status, "Receptionist Remove scenario verification in PDF File");
			orderSummaryPage.closeOrderSummary();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Receptionist Remove scenario Validation",
					"Receptionist Remove scenario Validation failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Receptionist Remove scenario Validation failed!!! " + Ex.getMessage();
			log.error(eMsg);
		}
	}

	/**************************
	 * END:BVE_SIMON_MACD_Add_Remove_Receptionist_feature_on_existing_customer
	 *****************************************************/
	/**************************
	 * BEGIN:BVE_SIMON_MACD__Add_Remove_Auto_Attendant_feature_on_existing
	 * customer
	 *****************************************************/
	@Test(priority = 7650, groups = { "New Connect", "All" })
	public void SIMON_MACD_Auto_Attendent_Add() {
		try {
			Settings createMACDforthisSite = new Settings(frameworkContext);
			Create_New_MACD createnewMACD = new Create_New_MACD(frameworkContext);
			MACDConfigurationPage verifyCustomerDetails = new MACDConfigurationPage(frameworkContext);
			SIMONMainPage SimonMainPage = new SIMONMainPage(frameworkContext);
			OrderSummaryPage orderSummaryPage = new OrderSummaryPage(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				SimonMainPage.SFOpportunityIDSearch();

			}
			createMACDforthisSite.createMACDForThisSite();
			createnewMACD.createNewMACD("New MACD (Existing Customer)", "MACD", "24");
			verifyCustomerDetails.clickServiceDetails();
			verifyCustomerDetails.clickHereToAddanItem();
			verifyCustomerDetails.serviceDetails("Add", "Auto Attendant");
			verifyCustomerDetails.addAutoAttendent("Test_Attend", "856-142-1234", "TimeScheduleAdd",
					"HolidayScheduleAdd");
			orderSummaryPage.clickOrderSummary();
			orderSummaryPage.addAutoAttendentOrderSummary("Test_Attend", "856-142-1234", "Eastern", "TimeScheduleAdd",
					"HolidayScheduleAdd");
			orderSummaryPage.downloadPDF();
			WebtopPage webtopPage = new WebtopPage(frameworkContext);
			String pdfText = webtopPage.getPDFContent(dataDump.getValue("OrderSummaryPDF"));
			boolean status;
			status = (pdfText.contains("##New MACD MACD 24##") && pdfText.contains(
					"#Auto Attendant Add Details##Auto Attendant Status##Active##Auto Attendant Name First Name Last Name##Test_Attend Test_Attend##Time Zone Port Type Existing Service##Eastern##Phone Number Ext Customer Contact for Recording##856-142-1234"));
			orderSummaryPage.PDFVerification(status, "Add Auto Attendent scenario verification in PDF File");
			orderSummaryPage.closeOrderSummary();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Add Auto Attendent scenario verification in PDF File",
					"Add Auto Attendent scenario verification-PDF Validation failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Add Auto Attendent scenario verification-PDF Validation failed"
					+ Ex.getMessage();
			log.error(eMsg);
		}
	}

	@Test(priority = 7700, groups = { "New Connect", "All" })
	public void SIMON_MACD_Auto_Attendent_Remove() {
		try {
			Settings createMACDforthisSite = new Settings(frameworkContext);
			Create_New_MACD createnewMACD = new Create_New_MACD(frameworkContext);
			MACDConfigurationPage verifyCustomerDetails = new MACDConfigurationPage(frameworkContext);
			SIMONMainPage SimonMainPage = new SIMONMainPage(frameworkContext);
			OrderSummaryPage orderSummaryPage = new OrderSummaryPage(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				SimonMainPage.SFOpportunityIDSearch();
				createMACDforthisSite.createMACDForThisSite();
				createnewMACD.createNewMACD("New MACD (Existing Customer)", "MACD", "24");
			}
			verifyCustomerDetails.clickServiceDetails();
			verifyCustomerDetails.clickHereToAddanItem();
			verifyCustomerDetails.serviceDetails("Remove", "Auto Attendant");
			verifyCustomerDetails.removeAutoAttendent("Test_Attend", "856-142-1234");
			orderSummaryPage.clickOrderSummary();
			orderSummaryPage.removeAutoAttendentOrderSummary("Test_Attend", "856-142-1234");
			orderSummaryPage.downloadPDF();
			WebtopPage webtopPage = new WebtopPage(frameworkContext);
			String pdfText = webtopPage.getPDFContent(dataDump.getValue("OrderSummaryPDF"));
			boolean status;
			status = (pdfText.contains("##New MACD MACD 24##") && pdfText
					.contains("##Auto Attendant Remove Details##Auto Attendant Name Phone Number Ext##Test_Attend##"));
			orderSummaryPage.PDFVerification(status, "Remove AutoAttendent scenario verification in PDF File");
			orderSummaryPage.closeOrderSummary();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Remove Auto Attendent scenario verification in PDF File",
					"Remove Auto Attendent scenario verification-PDF Validation failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Remove Auto Attendent scenario verification-PDF Validation failed"
					+ Ex.getMessage();
			log.error(eMsg);
		}
	}

	/**************************
	 * END:BVE_SIMON_MACD__Add_Remove_Auto_Attendant_feature_on_existing
	 * customer
	 *****************************************************/
	/**************************
	 * BEGIN:BVE_SIMON_MACD_Add_Edit_Remove_TN_on_existng_customer
	 *****************************************************/
	@Test(priority = 7750, groups = { "New Connect", "All" })
	public void SIMON_MACD_TN_Add() {
		try {
			Settings createMACDforthisSite = new Settings(frameworkContext);
			Create_New_MACD createnewMACD = new Create_New_MACD(frameworkContext);
			MACDConfigurationPage verifyCustomerDetails = new MACDConfigurationPage(frameworkContext);
			SIMONMainPage SimonMainPage = new SIMONMainPage(frameworkContext);
			OrderSummaryPage orderSummaryPage = new OrderSummaryPage(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				SimonMainPage.SFOpportunityIDSearch();

			}
			createMACDforthisSite.createMACDForThisSite();
			createnewMACD.createNewMACD("New MACD (Existing Customer)", "MACD", "24");
			verifyCustomerDetails.clickServiceDetails();
			verifyCustomerDetails.clickHereToAddanItem();
			verifyCustomerDetails.serviceDetails("Add", "TN");
			verifyCustomerDetails.addTN("856-781-1234");
			orderSummaryPage.clickOrderSummary();
			orderSummaryPage.addTNOrderSummary("856-781-1234", "New");
			orderSummaryPage.downloadPDF();
			WebtopPage webtopPage = new WebtopPage(frameworkContext);
			String pdfText = webtopPage.getPDFContent(dataDump.getValue("OrderSummaryPDF"));
			boolean status;
			status = (pdfText.contains("##New MACD MACD 24##")
					&& pdfText.contains("##TN Add Details##TN Port Type Existing Service##856-781-1234 New##"));
			orderSummaryPage.PDFVerification(status, "Add TN scenario verification in PDF File");
			orderSummaryPage.closeOrderSummary();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Add TN scenario verification in PDF File",
					"Add TN scenario verification-PDF Validation failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Add TN scenario verification-PDF Validation failed" + Ex.getMessage();
			log.error(eMsg);
		}
	}

	@Test(priority = 7800, groups = { "New Connect", "All" })
	public void SIMON_MACD_TN_Edit() {
		try {
			Settings createMACDforthisSite = new Settings(frameworkContext);
			Create_New_MACD createnewMACD = new Create_New_MACD(frameworkContext);
			MACDConfigurationPage verifyCustomerDetails = new MACDConfigurationPage(frameworkContext);
			SIMONMainPage SimonMainPage = new SIMONMainPage(frameworkContext);
			OrderSummaryPage orderSummaryPage = new OrderSummaryPage(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				SimonMainPage.SFOpportunityIDSearch();
				createMACDforthisSite.createMACDForThisSite();
				createnewMACD.createNewMACD("New MACD (Existing Customer)", "MACD", "24");

			}
			verifyCustomerDetails.clickServiceDetails();
			verifyCustomerDetails.clickHereToAddanItem();
			verifyCustomerDetails.serviceDetails("Edit/Change", "TN");
			verifyCustomerDetails.editTN("856-783-1234", "CurrentFNEdit", "CurrentLNEdit", "NewFNEdit", "NewLN");
			orderSummaryPage.clickOrderSummary();
			orderSummaryPage.editTNOrderSummary("856-783-1234", "NewFNEdit", "NewLN", "CurrentFNEdit", "CurrentLNEdit");
			orderSummaryPage.downloadPDF();
			WebtopPage webtopPage = new WebtopPage(frameworkContext);
			String pdfText = webtopPage.getPDFContent(dataDump.getValue("OrderSummaryPDF"));
			boolean status;
			status = (pdfText.contains(
					"##TN Edit Details##TN New First Name New Last Name##856-783-1234 NewFNEdit NewLN##Current First Name Current Last Name##CurrentFNEdit CurrentLNEdit##"));
			orderSummaryPage.PDFVerification(status, "Edit TN scenario verification in PDF File");
			orderSummaryPage.closeOrderSummary();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Edit TN scenario verification in PDF File",
					"Edit TN scenario verification-PDF Validation failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Edit TN scenario verification-PDF Validation failed" + Ex.getMessage();
			log.error(eMsg);
		}
	}

	@Test(priority = 7800, groups = { "New Connect", "All" })
	public void SIMON_MACD_TN_Remove() {
		try {
			Settings createMACDforthisSite = new Settings(frameworkContext);
			Create_New_MACD createnewMACD = new Create_New_MACD(frameworkContext);
			MACDConfigurationPage verifyCustomerDetails = new MACDConfigurationPage(frameworkContext);
			SIMONMainPage SimonMainPage = new SIMONMainPage(frameworkContext);
			OrderSummaryPage orderSummaryPage = new OrderSummaryPage(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				SimonMainPage.SFOpportunityIDSearch();
				createMACDforthisSite.createMACDForThisSite();
				createnewMACD.createNewMACD("New MACD (Existing Customer)", "MACD", "24");

			}
			verifyCustomerDetails.clickServiceDetails();
			verifyCustomerDetails.clickHereToAddanItem();
			verifyCustomerDetails.serviceDetails("Remove", "TN");
			verifyCustomerDetails.removeTN("856-783-1234");
			orderSummaryPage.clickOrderSummary();
			orderSummaryPage.removeTNOrderSummary("856-783-1234");
			orderSummaryPage.downloadPDF();
			WebtopPage webtopPage = new WebtopPage(frameworkContext);
			String pdfText = webtopPage.getPDFContent(dataDump.getValue("OrderSummaryPDF"));
			boolean status;
			status = (pdfText.contains("##New MACD MACD 24##") && pdfText.contains("##TN Remove Details##"));
			orderSummaryPage.PDFVerification(status, "TN Remove scenario verification in PDF File");

			orderSummaryPage.closeOrderSummary();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Remove TN scenario verification in PDF File",
					"Remove TN scenario verification-PDF Validation failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Remove TN scenario verification-PDF Validation failed" + Ex.getMessage();
			log.error(eMsg);
		}
	}

	/**************************
	 * END:BVE_SIMON_MACD_Add_Edit_Remove_TN_on_existng_customer
	 *****************************************************/
	/**************************
	 * BEGIN:BVE_SIMON_MACD_Add_Remove_Hunt_Group_feature_on_existing_customer
	 *****************************************************/
	@Test(priority = 7900, groups = { "New Connect", "All" })
	public void SIMON_MACD_HuntGroup_Add() {
		try {
			Settings createMACDforthisSite = new Settings(frameworkContext);
			Create_New_MACD createnewMACD = new Create_New_MACD(frameworkContext);
			MACDConfigurationPage verifyCustomerDetails = new MACDConfigurationPage(frameworkContext);
			SIMONMainPage SimonMainPage = new SIMONMainPage(frameworkContext);
			OrderSummaryPage orderSummaryPage = new OrderSummaryPage(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				SimonMainPage.SFOpportunityIDSearch();

			}
			createMACDforthisSite.createMACDForThisSite();
			createnewMACD.createNewMACD("New MACD (Existing Customer)", "MACD", "24");
			verifyCustomerDetails.clickServiceDetails();
			verifyCustomerDetails.clickHereToAddanItem();
			verifyCustomerDetails.serviceDetails("Add", "Hunt Group");
			verifyCustomerDetails.addHuntGroup("HuntGroupAdd");
			orderSummaryPage.clickOrderSummary();
			orderSummaryPage.verifyHuntGroupName("HuntGroupAdd");
			orderSummaryPage.downloadPDF();
			WebtopPage webtopPage = new WebtopPage(frameworkContext);
			String pdfText = webtopPage.getPDFContent(dataDump.getValue("OrderSummaryPDF"));
			boolean status;
			status = (pdfText.contains("##New MACD MACD 24##") && pdfText.contains("HuntGroupAdd"));
			orderSummaryPage.PDFVerification(status, "Add Hunt Group scenario verification in PDF File");
			orderSummaryPage.closeOrderSummary();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Add Hunt Group scenario verification in PDF File",
					"Add Hunt Group scenario verification-PDF Validation failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Add Hunt Group scenario verification-PDF Validation failed" + Ex.getMessage();
			log.error(eMsg);
		}
	}
	@Test(priority = 8000, groups = { "New Connect", "All" })
	public void SIMON_MACD_HuntGroup_Remove() {
		try {
			Settings createMACDforthisSite = new Settings(frameworkContext);
			Create_New_MACD createnewMACD = new Create_New_MACD(frameworkContext);
			MACDConfigurationPage verifyCustomerDetails = new MACDConfigurationPage(frameworkContext);
			SIMONMainPage SimonMainPage = new SIMONMainPage(frameworkContext);
			OrderSummaryPage orderSummaryPage = new OrderSummaryPage(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				SimonMainPage.SFOpportunityIDSearch();
				createMACDforthisSite.createMACDForThisSite();
				createnewMACD.createNewMACD("New MACD (Existing Customer)", "MACD", "24");
			}
			verifyCustomerDetails.clickServiceDetails();
			verifyCustomerDetails.clickHereToAddanItem();
			verifyCustomerDetails.serviceDetails("Remove", "Hunt Group");
			verifyCustomerDetails.removeHuntGroup("HuntGroupRemove", "856-784-1278");
			orderSummaryPage.clickOrderSummary();
			orderSummaryPage.verifyHuntRemove("HuntGroupRemove", "856-784-1278");
			orderSummaryPage.downloadPDF();
			WebtopPage webtopPage = new WebtopPage(frameworkContext);
			String pdfText = webtopPage.getPDFContent(dataDump.getValue("OrderSummaryPDF"));
			boolean status;
			status = (pdfText.contains("##New MACD MACD 24##") && pdfText.contains("##Hunt Group Remove Details##Hunt Group Name Phone Number Ext##HuntGroupRemove 856-784-1278"));
			orderSummaryPage.PDFVerification(status, "Hunt Group Remove scenario verification in PDF File");
			orderSummaryPage.closeOrderSummary();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Remove Hunt Group scenario verification in PDF File",
					"Remove Hunt Group scenario verification-PDF Validation failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Remove Hunt Group scenario verification-PDF Validation failed"
					+ Ex.getMessage();
			log.error(eMsg);
		}
	}
	/************************** BVE_SIMON_MACD_Add_Remove_Hunt_Group_feature_on_existing_customer *****************************************************/
}
