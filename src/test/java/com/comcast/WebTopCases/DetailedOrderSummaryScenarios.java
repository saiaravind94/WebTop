package com.comcast.WebTopCases;

import org.testng.annotations.Test;

import com.comcast.commons.WebtopPage;
import com.comcast.pages.simon.OrderSummaryPage;
import com.comcast.pages.simon.Receptionist;
import com.comcast.pages.simon.SIMONMainPage;
import com.comcast.pages.simon.SeatConfiguration;
import com.comcast.pages.simon.SimonLogInPage;
import com.comcast.utils.DataTable;

public class DetailedOrderSummaryScenarios extends CommonE2EFlow {
	/********
	 * BEGIN:DetailedOSComparisonWithMockUpCQAgent_AA_HG_SCA_RC_VoiceSelections
	 ****/
	DataTable dataSet;

	@Test(priority = 10010, groups = { "New Connect", "All" })
	public void enableBLFFeature() {
		try {
			SIMONMainPage SimonMainPage = new SIMONMainPage(frameworkContext);
			SeatConfiguration seatConfiguration = new SeatConfiguration(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				SimonMainPage.SFOpportunityIDSearch();
			}
			seatConfiguration.enableBLFFeature();
		} catch (Exception ex) {
			report.reportHardFailEvent("Enable BLF/SCA feature", "Enable BLF/SCA feature Failed" + ex.getMessage());
			eMsg = "Enable BLF/SCA feature Failed!!! " + ex.getMessage();
			log.error(eMsg);
		}
	}

	@Test(priority = 10020, groups = { "New Connect", "All" })
	public void configureReceptionist() {
		try {
			SIMONMainPage SimonMainPage = new SIMONMainPage(frameworkContext);
			Receptionist receptionist = new Receptionist(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				SimonMainPage.SFOpportunityIDSearch();
			}
			receptionist.configureReceptionist("UCSeat002");
		} catch (Exception ex) {
			report.reportHardFailEvent("Configure Receptionist in SIMON",
					"Configure Receptionist in SIMON Failed" + ex.getMessage());
			eMsg = "Configure Receptionist in SIMON Failed!!! " + ex.getMessage();
			log.error(eMsg);
		}
	}

	@Test(priority = 10050, groups = { "New Connect", "All" })
	public void SIMON_DetailedOrderSummaryValidations() {
		try {
			SIMONMainPage SimonMainPage = new SIMONMainPage(frameworkContext);
			OrderSummaryPage orderSummaryPage = new OrderSummaryPage(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				SimonMainPage.SFOpportunityIDSearch();
			}
			orderSummaryPage.launchOrderSumaryType("Detailed Order Summary");
			String[] voiceSelectionFieldList = { "Total New Lines", "Basic Seats", "Unified Communication Seats",
					"Reception Console", "Auto Attendant", "Additional Hunt Group", "Additional Voicemail Box",
					"CQ Agent per UC Seat Subscribed", "Toll Free Number(s) (plus usage)", "Unassigned TNs",
					"Additional or Alternate TNs", "Non-Published Directory Listing", "Additional Directory Listing",
					"Customer Equipment" };
			String[] voiceSelectionExpectedValue = { "3", "0", "3", "1", "5", "3", "0", "1", "0", "1", "0", "8", "0",
					"5" };
			orderSummaryPage.verifyVoiceSelection(voiceSelectionFieldList, voiceSelectionExpectedValue);

			String AutoAttendent[] = { "Unused AutoAttendant-1", "Unused AutoAttendant-2", "Unused AutoAttendant-3",
					"Unused AutoAttendant-4", "AA001" };
			orderSummaryPage.verifyAutoAttendents(AutoAttendent);
			sleep(5000);

			String HuntGroups[] = { "Unused HuntGroup1", "Unused HuntGroup2", "HG001" };
			orderSummaryPage.verifyHuntGroup(HuntGroups);

			orderSummaryPage.receptionistConsoleDetails("UCSeat002");
			orderSummaryPage.verifySharedCallAppearance("UCSeat002", "Polycom VVX 311 HD");

			orderSummaryPage.verifyCallQueueDetails("CQB001");
		} catch (Exception ex) {
			report.reportHardFailEvent("Detailed Order Summary Validations",
					"Detailed Order Summary Validations Failed" + ex.getMessage());
			eMsg = "Detailed Order Summary Validations Failed!!! " + ex.getMessage();
			log.error(eMsg);
		}
	}

	@Test(priority = 10100, groups = { "New Connect", "All" })
	public void SIMON_DetailedOrderSummaryPDFValidations() throws Exception {
		SIMONMainPage SimonMainPage = new SIMONMainPage(frameworkContext);
		OrderSummaryPage orderSummaryPage = new OrderSummaryPage(frameworkContext);
		if (!SimonLogInPage.simonLogedIn) {
			SIMONLogin();
			SimonMainPage.SFOpportunityIDSearch();
		}
		boolean status;
		orderSummaryPage.downloadPDF();
		WebtopPage webtopPage = new WebtopPage(frameworkContext);
		String pdfText = webtopPage.getPDFContent(dataDump.getValue("OrderSummaryPDF"));

		status = (pdfText.contains("##Detailed Order Summary##") && pdfText.contains(
				"##Voice Selections: Voice Selections 1 of 1##Non-Published Directory Listing 8 8##Non-Listed Directory Listing 0 0##Additional Directory Listing 0 0##Customer Equipment 5 5##"));
		orderSummaryPage.PDFVerification(status, "Verification of Voice Selection in OS PDF File");

		status = (pdfText.contains("Unused AutoAttendant-1") && pdfText.contains("Unused AutoAttendant-2")
				&& pdfText.contains("Unused AutoAttendant-3") && pdfText.contains("Unused AutoAttendant-4")
				&& pdfText.contains("AA001"));
		orderSummaryPage.PDFVerification(status, "Verification of AA in OS PDF File");

		status = (pdfText.contains("Unused HuntGroup1") && pdfText.contains("Unused HuntGroup2")
				&& pdfText.contains("HG001"));
		orderSummaryPage.PDFVerification(status, "Verification of HG in OS PDF File");

		status = (pdfText.contains("UC Seat Receptionist##UCSeat002"));
		orderSummaryPage.PDFVerification(status, "Verification of Receptionlist Console in OS PDF File");

		status = (pdfText.contains(
				"Shared Call Appearance: Shared Call Appearance 1 of 1##Device Setup##Seat Name Device Type Number of Side Cars##UCSeat002")
				&& pdfText.contains("Polycom VVX 311 HD 0"));
		orderSummaryPage.PDFVerification(status, "Verification of SCA in OS PDF File");

		status = (pdfText.contains("Call Queue #1: CQB001"));
		orderSummaryPage.PDFVerification(status, "Verification of Call Queue in OS PDF File");
	}

	/********
	 * END:DetailedOSComparisonWithMockUpCQAgent_AA_HG_SCA_RC_VoiceSelections
	 ****/
	/********
	 * BEGIN:New_Polycom_phone_models_to_appear_on_the_Order_Summary_Report
	 ******/
	@Test(priority = 7300, groups = { "New Connect", "All" })
	public void newPolycomDevicesOSVerification() {
		try {
			SIMONMainPage simonMainPage = new SIMONMainPage(frameworkContext);
			OrderSummaryPage orderSummaryPage = new OrderSummaryPage(frameworkContext);
			SeatConfiguration seatConfiguration = new SeatConfiguration(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				simonMainPage.SFOpportunityIDSearch();
			}

			String[] DeviceType = { "Polycom VVX 311 HD", "Polycom VVX 411 HD", "Polycom VVX 501 HD",
					"Polycom VVX 601 HD W Camera" };
			String seatsToVerifyDevices = "UCSeat001|UCSeat003|EAU001|UCSeat004";
			seatConfiguration.verifyDeviceTypeSelected(DeviceType, seatsToVerifyDevices);
			orderSummaryPage.launchOrderSumaryType("Detailed Order Summary");

			String[] OrderSummaryDeviceTypes = { "Polycom VVX 311 HD", "Polycom VVX 411 HD", "Polycom VVX 501 HD",
					"Polycom VVX 601 HD W Camera" };
			orderSummaryPage.verifyDeviceTypes(OrderSummaryDeviceTypes);
			orderSummaryPage.closeOrderSummary();
			sleep(7000);
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verification of Device Type Displayed in the Detailed Order Summary Page",
					"Verification of Device Type Displayed in the Detailed Order Summary Page failed"
							+ Ex.getMessage());
			eMsg = report.getMessage()
					+ "Verification of Device Type Displayed in the Detailed Order Summary Page failed"
					+ Ex.getMessage();
			log.error(eMsg);
		}
	}

	/********
	 * BEGIN:Verify_fields_are_correctly_populated_in_LNP_OS_ PDF_
	 * as_in_OS_Screen_for_Standard_ site
	 ******/
	@Test(priority = 9000, groups = { "New Connect", "All" })
	public void lNPOrderSummaryVerificationForStandardSite() {
		try {
			this.dataSet = frameworkContext.getDataTable();
			SIMONMainPage simonMainPage = new SIMONMainPage(frameworkContext);
			OrderSummaryPage orderSummaryPage = new OrderSummaryPage(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				simonMainPage.SFOpportunityIDSearch();
			}
			orderSummaryPage.launchOrderSumaryType("LNP");
			orderSummaryPage.verifyLNPOrderSummaryFieldValues();
			orderSummaryPage.downloadPDF();
			WebtopPage webtopPage = new WebtopPage(frameworkContext);
			String pdfText = webtopPage.getPDFContent(dataDump.getValue("OrderSummaryPDF"));
			boolean status;
			status = (pdfText
					.contains(dataDump.getValue("GroupID") + " " + dataDump.getValue("ParentAccountID_RT") + "##")
					&& pdfText.contains(dataDump.getValue("GroupID") + " " + "Customer Name##Lead ID##"
							+ dataDump.getValue("LeadID") + "##Call Capacity (ESG License Quantity) ILD##"
							+ dataSet.getValue("ESGLicenseQuantity"))
					&& pdfText.toLowerCase().contains(dataDump.getValue("CustomerName").toLowerCase()));
			orderSummaryPage.PDFVerification(status, "LNP Order Summary Validation in PDF File");
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verification of Field Parameters Displayed in the LNP Order Summary Page",
					"Verification of Field Parameters Displayed in the LNP Order Summary Page failed"
							+ Ex.getMessage());
			eMsg = report.getMessage()
					+ "Verification of Field Parameters Displayed in the LNP Order Summary Page failed"
					+ Ex.getMessage();
			log.error(eMsg);
		}
	}

	/********
	 * END:Verify_fields_are_correctly_populated_in_LNP_OS_ PDF_
	 * as_in_OS_Screen_for_Standard_ site
	 ******/
	/********
	 * BEGIN:directoryListingsOrderSummaryVerificationForStandardSite
	 ******/
	@Test(priority = 10500, groups = { "New Connect", "All" })
	public void directoryListingsOrderSummaryVerificationForStandardSite() {
		try {
			this.dataSet = frameworkContext.getDataTable();
			SIMONMainPage simonMainPage = new SIMONMainPage(frameworkContext);
			OrderSummaryPage orderSummaryPage = new OrderSummaryPage(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				simonMainPage.SFOpportunityIDSearch();
			}
			orderSummaryPage.launchOrderSumaryType("Directory Listings");
			orderSummaryPage.verifyDirectoryListingFieldValues("UCSeat001", "On", "Off");
			orderSummaryPage.downloadPDF();
			WebtopPage webtopPage = new WebtopPage(frameworkContext);
			String pdfText = webtopPage.getPDFContent(dataDump.getValue("OrderSummaryPDF"));
			boolean status;
			status = (pdfText
					.contains(dataDump.getValue("GroupID") + " " + dataDump.getValue("ParentAccountID_RT") + "##")
					&& pdfText.toLowerCase().contains(dataDump.getValue("CustomerName").toLowerCase())
					&& pdfText.contains(dataDump.getValue("GroupID") + " " + "Customer Name##Lead ID##"
							+ dataDump.getValue("LeadID") + "##Call Capacity (ESG License Quantity) ILD##"
							+ dataSet.getValue("ESGLicenseQuantity"))
					&& pdfText.contains(
							"##Directory Listing Number Omit Address##On Off##Header Text Header Code SIC Code##"
									+ dataSet.getValue("HeaderText") + " " + dataSet.getValue("HeaderCode") + " "
									+ dataSet.getValue("SICCode") + "##"));
			orderSummaryPage.PDFVerification(status, "Directory Lisiting Order Summary Validation in PDF File");

		} catch (Exception Ex) {
			report.reportHardFailEvent(
					"Verification of Field Parameters Displayed in Directory Lisiting Order Summary  Page",
					"Verification of Field Parameters Displayed in Directory Listing Order Summary failed"
							+ Ex.getMessage());
			eMsg = report.getMessage()
					+ "Verification of Field Parameters Displayed in Directory Listing Order Summary failed"
					+ Ex.getMessage();
			log.error(eMsg);
		}
	}

	/********
	 * END:directoryListingsOrderSummaryVerificationForStandardSite
	 ******/
	/********
	 * BEGIN:
	 * Verify_Broadworks_Anywhere_Ext_and_Voice_Portal_Ext_configured_correctly_in_Order_Summary_Screen
	 ******/
	@Test(priority = 10550, groups = { "New Connect", "All" })
	public void verifyBWAnywhereVPEXTinDetailedOrderSummary() {
		try {
			this.dataSet = frameworkContext.getDataTable();
			SIMONMainPage simonMainPage = new SIMONMainPage(frameworkContext);
			OrderSummaryPage orderSummaryPage = new OrderSummaryPage(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				simonMainPage.SFOpportunityIDSearch();
			}
			orderSummaryPage.launchOrderSumaryType("Detailed Order Summary");

			String[] Purpose = { "BW Anywhere", "Voice Portal" };
			String[] Ext = { "999998", "999999" };
			String[] InvName = { "" };
			String[] InvTN = { "" };
			orderSummaryPage.verifyTNInventoryDetails(Purpose, Ext, InvName, InvTN);
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verification of BW Anywhere and Voice Portal in Detailed Order Summary  Page",
					"Verification of BW Anywhere and Voice Portal in Detailed Order Summary  Page" + Ex.getMessage());
			eMsg = report.getMessage() + "Verification of BW Anywhere and Voice Portal in Detailed Order Summary  Page"
					+ Ex.getMessage();
			log.error(eMsg);
		}
	}

	/********
	 * * END:
	 * Verify_Broadworks_Anywhere_Ext_and_Voice_Portal_Ext_configured_correctly_in_Order_Summary_Screen.
	 ******/
	/*******
	 * BEGIN:
	 * Detailed_Order_Summary_Comparison_with_Mock_Up_Telephone_Number_Inventory_Details
	 ******/
	@Test(priority = 10600, groups = { "New Connect", "All" })
	public void verifyTNInventoryDetailsandPDFinDetailedOrderSummary() {
		boolean status;
		try {
			this.dataSet = frameworkContext.getDataTable();
			SIMONMainPage simonMainPage = new SIMONMainPage(frameworkContext);
			OrderSummaryPage orderSummaryPage = new OrderSummaryPage(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				simonMainPage.SFOpportunityIDSearch();
			}
			orderSummaryPage.launchOrderSumaryType("Detailed Order Summary");
			String[] purpose = { "BW Anywhere", "Voice Portal", "Auto-Attendent", "Enterprise Admin User",
					"Standard User", "Standard User", "Hunt Group" };
			String[] ext = { "999998", "999999", "", "", "", "", "" };
			String[] invName = { "BWAnyWhere", "VoicePortal", "AA001", "EAU001", "UCSeat001", "UCSeat002", "HG001" };
			String[] invTN = { "New", "New", "New", "New", "New", "New", "New" };
			orderSummaryPage.verifyTNInventoryDetails(purpose, ext, invName, invTN);
			orderSummaryPage.downloadPDF();
			WebtopPage webtopPage = new WebtopPage(frameworkContext);
			String pdfText = webtopPage.getPDFContent(dataDump.getValue("OrderSummaryPDF"));
			status = (pdfText.contains("BW Anywhere BWAnyWhere") && pdfText.contains("##Voice Portal VoicePortal##")
					&& pdfText.contains("##Auto Attendant AA001##")
					&& pdfText.contains("##Standard User TABTNUCSeat001##")
					&& pdfText.contains("##Standard User UCSeat002##") && pdfText.contains("##CQB001##")
					&& pdfText.contains("##Enterprise Admin##User##EAU001##")
					&& pdfText.contains("##Hunt Group HG001##"));
			orderSummaryPage.PDFVerification(status, "Verification of TN Inventory Details in PDF File");
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verification of TN Number Inventory Details in Detailed Order Summary Page",
					"Verification of TN Number Inventory Details in Detailed Order Summary Page" + Ex.getMessage());
			eMsg = report.getMessage() + "Verification of TN Number Inventory Details in Detailed Order Summary Page"
					+ Ex.getMessage();
			log.error(eMsg);
		}
	}

	/********
	 * * BEGIN: DetailedOS_Comparison_with_Mock_Up_Seat_Details
	 ******/
	@Test(priority = 10700, groups = { "New Connect", "All" })
	public void verifySeatDetailsFieldsandPDFinDetailedOrderSummary() {
		boolean status;
		try {
			this.dataSet = frameworkContext.getDataTable();
			SIMONMainPage simonMainPage = new SIMONMainPage(frameworkContext);
			OrderSummaryPage orderSummaryPage = new OrderSummaryPage(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				simonMainPage.SFOpportunityIDSearch();
			}
			orderSummaryPage.launchOrderSumaryType("Detailed Order Summary");
			String[] seat = { "No Seat", "No Seat", "UC Seat", "UC Seat", "UC Seat" };
			String[] purpose = { "Voice Portal", "BW Anywhere", "Standard User", "Standard User",
					"Enterprise Admin User" };
			String[] name = { "VoicePortal", "BWAnyWhere", "UCSeat002", "TABTNUCSeat001", "EAU001" };
			String[] phoneModel = { "No Hardware", "No Hardware", "Polycom VVX 311 HD", "Polycom VVX 311 HD",
					"No Hardware" };
			orderSummaryPage.verifySeatDetailsinDetailedOrderSummary(seat, purpose, name, phoneModel);
			orderSummaryPage.downloadPDF();
			WebtopPage webtopPage = new WebtopPage(frameworkContext);
			String pdfText = webtopPage.getPDFContent(dataDump.getValue("OrderSummaryPDF"));
			status = (pdfText.contains("No Seat Voice Portal VoicePortal##")
					&& pdfText.contains("UC Seat Enterprise Admin##User##EAU001##")
					&& pdfText.contains("UC Seat Standard User##TABTNUCSeat001##")
					&& pdfText.contains("No Seat BW Anywhere BWAnyWhere##")
					&& pdfText.contains("UC Seat Standard User UCSeat002##"));
			orderSummaryPage.PDFVerification(status, "Verification of TN Inventory Details in PDF File");
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verification of Seat Details in Detailed Order Summary Page",
					"Verification of Seat Details in Detailed Order Summary Page Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Verification of Seat Details in Detailed Order Summary Page Failed"
					+ Ex.getMessage();
			log.error(eMsg);
		}
	}

	/********
	 * * END:DetailedOS_Comparison_with_Mock_Up_Seat_Details
	 *******/

	/********
	 * * BEGIN:
	 * Verify_fields_are_correctly_populated_in_Toll_Free_Numbers_OS_pdf_for_Standard_site
	 ******/
	@Test(priority = 10800, groups = { "New Connect", "All" })
	public void verifyFieldsandPDFInTollFreeNumbersOSScreen() {
		boolean status;
		try {
			this.dataSet = frameworkContext.getDataTable();
			SIMONMainPage simonMainPage = new SIMONMainPage(frameworkContext);
			OrderSummaryPage orderSummaryPage = new OrderSummaryPage(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				simonMainPage.SFOpportunityIDSearch();
			}
			orderSummaryPage.launchOrderSumaryType("Toll Free Numbers");
			orderSummaryPage.verifyTollFreeNumbersOrderSummaryFieldValues("Domestic Only");
			orderSummaryPage.downloadPDF();
			WebtopPage webtopPage = new WebtopPage(frameworkContext);
			String pdfText = webtopPage.getPDFContent(dataDump.getValue("OrderSummaryPDF"));
			status = (pdfText.toLowerCase().contains(dataDump.getValue("CustomerName").toLowerCase())
					&& pdfText.contains("Customer Name##Lead ID##" + dataDump.getValue("LeadID")
							+ "##Call Capacity (ESG License Quantity) ILD##" + dataSet.getValue("ESGLicenseQuantity")
							+ " Domestic Only##")
					&& pdfText.contains(dataDump.getValue("ParentAccountID_RT")) && pdfText.contains(
							"##" + dataDump.getValue("GroupID") + " " + dataSet.getValue("CustomerName") + "##"));
			orderSummaryPage.PDFVerification(status, "Verification of TN Inventory Details in PDF File");
		} catch (Exception Ex) {
			report.reportHardFailEvent(
					"Verification of Feild Parameters in Toll Free Numbers Order Summary Page and PDF",
					"Verification of Feild Parameters in Toll Free Numbers Order Summary and PDF Page Failed"
							+ Ex.getMessage());
			eMsg = report.getMessage()
					+ "Verification of Feild Parameters in Toll Free Numbers Order Summary Page and PDF Failed"
					+ Ex.getMessage();
			log.error(eMsg);
		}
	}

	/********
	 * * END:DetailedOS_Comparison_with_Mock_Up_Seat_Details
	 *******/

	@Test(priority = 10800, groups = { "New Connect", "All" })
	public void verifyOSPageIsDisplayedWithReadOnlyUser() {
		this.userDetails = frameworkContext.getUserDetail();
		String readOnlyUserName = userDetails
				.getUserName("APP_" + settings.getEnvironmentToTest() + "_UNAME_SIMON_READONLY");
		String readOnlyPassword = settings.DecodeString(
				userDetails.getUserName("APP_" + settings.getEnvironmentToTest() + "_PWD_SIMON_READONLY"));
		SimonLogInPage.simonLogedIn = false;
		try {
			this.dataSet = frameworkContext.getDataTable();
			if (!SimonLogInPage.simonLogedIn) {
				new SimonLogInPage(frameworkContext).openNewBrowserInstance(readOnlyUserName, readOnlyPassword);
				new SIMONMainPage(frameworkContext).SFOpportunityIDSearch();
				new SIMONMainPage(frameworkContext).verifySiteIsLocked();
				SimonLogInPage.simonLogedIn = true;
			}
			String[] osTypesToCheck = { "Directory Listings", "Toll Free Numbers" };
			new OrderSummaryPage(frameworkContext).verifySiteNameIsDisplayedInAllOSPage(osTypesToCheck);
		} catch (Exception Ex) {
			report.reportHardFailEvent("Open SIMON with another user",
					"Open SIMON with another user is failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Open SIMON with another user is failed" + Ex.getMessage();
			log.error(eMsg);
		}
	}

	@Test(priority = 10050, groups = { "New Connect", "All" })
	public void verifyScopeDialingDetails() {
		try {
			SIMONMainPage SimonMainPage = new SIMONMainPage(frameworkContext);
			OrderSummaryPage orderSummaryPage = new OrderSummaryPage(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				SimonMainPage.SFOpportunityIDSearch();
			}
			orderSummaryPage.launchOrderSumaryType("Voice Provisioning");
			orderSummaryPage.verifyScopeDialingDetails();
		} catch (Exception ex) {
			report.reportHardFailEvent(
					"Verification of Scope Dialing Extn/Name/NameDialing Entries for AA Order Summary Page",
					"Verification of Scope Dialing Extn/Name/NameDialing Entries for AA Order Summary Page Failed"
							+ ex.getMessage());
			eMsg = report.getMessage()
					+ "Verification of Scope Dialing Extn/Name/NameDialing Entries for AA Order Summary Page Failed"
					+ ex.getMessage();
			log.error(eMsg);
		}
	}

	/**************************************************************************************************************************************************************
	 * BEGIN:
	 * Verify_fields_are_correctly_populated_in_Voice_Provisioning_order_summary_pdf_as_in_order_summary_screen_for_Multisite_Standard_Teleworker_site
	 **************************************************************************************************************************************************************/

	@Test(priority = 10050, groups = { "New Connect", "All" })
	public void MS_STDSiteValidationsInOSPage() {
		try {
			SIMONMainPage SimonMainPage = new SIMONMainPage(frameworkContext);
			OrderSummaryPage orderSummaryPage = new OrderSummaryPage(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				SimonMainPage.SFOpportunityIDSearch();
			}
			orderSummaryPage.launchOrderSumaryType("Detailed Order Summary");
			orderSummaryPage.verifySiteType("standard");
			String[] voiceSelectionFieldList = { "Total New Lines", "Basic Seats", "Unified Communication Seats",
					"Reception Console", "Auto Attendant", "Additional Hunt Group", "Additional Voicemail Box",
					"CQ Agent per UC Seat Subscribed", "Toll Free Number(s) (plus usage)", "Unassigned TNs",
					"Additional or Alternate TNs", "Non-Published Directory Listing", "Additional Directory Listing",
					"Customer Equipment" };
			String[] voiceSelectionExpectedValue = { "10", "0", "10", "0", "5", "3", "0", "0", "0", "2", "0", "16", "0",
					"14" };
			orderSummaryPage.verifyVoiceSelection(voiceSelectionFieldList, voiceSelectionExpectedValue);

			String AutoAttendent[] = { "Unused AutoAttendant-1", "Unused AutoAttendant-2", "Unused AutoAttendant-3",
					"Unused AutoAttendant-4", "Unused AutoAttendant-5" };
			orderSummaryPage.verifyAutoAttendents(AutoAttendent);
			sleep(5000);
			String HuntGroups[] = { "Unused HuntGroup1", "Unused HuntGroup2", "Unused HuntGroup3" };
			orderSummaryPage.verifyHuntGroup(HuntGroups);
		} catch (Exception ex) {
			report.reportHardFailEvent("Detailed Order Summary Validations for Multisite(Standard Site)",
					"Detailed Order Summary Validations for Multisite(Standard Site) Failed" + ex.getMessage());
			eMsg = "Detailed Order Summary Validations for Multisite(Standard Site)Failed!!! " + ex.getMessage();
			log.error(eMsg);
		}
	}

	@Test(priority = 10100, groups = { "New Connect", "All" })
	public void MS_STDSiteValidationsInPDF() {
		try {
			SIMONMainPage SimonMainPage = new SIMONMainPage(frameworkContext);
			OrderSummaryPage orderSummaryPage = new OrderSummaryPage(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				SimonMainPage.SFOpportunityIDSearch();
			}
			orderSummaryPage.downloadPDF();
			WebtopPage webtopPage = new WebtopPage(frameworkContext);
			String pdfText = webtopPage.getPDFContent(dataDump.getValue("OrderSummaryPDF"));
			boolean status = (pdfText.contains(
					"##Voice Selections Amount All Sites Total##Total New Lines 6 10##Basic Seats 0 0##Unified Communication Seats 6 10##Reception Console 0 0##Auto Attendant 5 5##Additional Hunt Group 3 3##Additional Voicemail Box 0 0##CQ Agent per UC Seat Subscribed 0 0##Toll Free Number(s) (plus usage) 0 0##Unassigned TNs 1 2##Detailed Order Summary##Customer: MSite_STDTWSIMONValidation_20170620101840##Detailed Order SummaryPage 1 of 10##Voice Selections: Voice Selections 1 of 1##Additional or Alternate TNs 0 0##Non-Published Directory Listing 9 16##Non-Listed Directory Listing 0 0##Additional Directory Listing 0 0##Customer Equipment 8 14##")
					&& pdfText.contains("##Address 1 Address 2 Site Type##12 ADAMS LN N/A standard##"));
			orderSummaryPage.PDFVerification(status, "Verification of Voice Selections in OS PDF File");
			orderSummaryPage.closeOrderSummary();
		} catch (Exception ex) {
			report.reportHardFailEvent("Detailed Order Summary Validations for Multisite(Standard Site) in PDF File",
					"Detailed Order Summary Validations for Multisite(Standard Site) in PDF File Failed"
							+ ex.getMessage());
			eMsg = "Detailed Order Summary Validations for Multisite(Standard Site) in PDF File Failed!!! "
					+ ex.getMessage();
			log.error(eMsg);
		}
	}

	@Test(priority = 10150, groups = { "New Connect", "All" })
	public void MS_TWSiteValidationsInOSPage() {
		try {			
			SIMONMainPage SimonMainPage = new SIMONMainPage(frameworkContext);
			OrderSummaryPage orderSummaryPage = new OrderSummaryPage(frameworkContext);
			int SFID = Integer.parseInt(getSOAFileDetails());
			dataDump.setValue("SFOpportunityId", String.valueOf((SFID + 1)));
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();				
			}
			SimonMainPage.SFOpportunityIDSearch();
			orderSummaryPage.launchOrderSumaryType("Detailed Order Summary");
			orderSummaryPage.verifySiteType("teleworker");
			String[] voiceSelectionFieldList = { "Total New Lines", "Basic Seats", "Unified Communication Seats",
					"Reception Console", "Auto Attendant", "Additional Hunt Group", "Additional Voicemail Box",
					"CQ Agent per UC Seat Subscribed", "Toll Free Number(s) (plus usage)", "Unassigned TNs",
					"Additional or Alternate TNs", "Non-Published Directory Listing", "Additional Directory Listing",
					"Customer Equipment" };
			String[] voiceSelectionExpectedValue = { "10", "0", "10", "0", "5", "3", "0", "0", "0", "2", "0", "16", "0",
					"14" };
			orderSummaryPage.verifyVoiceSelection(voiceSelectionFieldList, voiceSelectionExpectedValue);			
		} catch (Exception ex) {
			report.reportHardFailEvent("Detailed Order Summary Validations for Multisite(Teleworker Site)",
					"Detailed Order Summary Validations for Multisite(Teleworker Site) Failed" + ex.getMessage());
			eMsg = "Detailed Order Summary Validations for Multisite(Teleworker Site)Failed!!! " + ex.getMessage();
			log.error(eMsg);
		}
	}

	@Test(priority = 10200, groups = { "New Connect", "All" })
	public void MS_TWSiteValidationsInPDF() {
		try {
			SIMONMainPage SimonMainPage = new SIMONMainPage(frameworkContext);
			OrderSummaryPage orderSummaryPage = new OrderSummaryPage(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				SimonMainPage.SFOpportunityIDSearch();
			}
			orderSummaryPage.downloadPDF();
			WebtopPage webtopPage = new WebtopPage(frameworkContext);
			String pdfText = webtopPage.getPDFContent(dataDump.getValue("OrderSummaryPDF"));
			boolean status = (pdfText.contains(
					"##Voice Selections Amount All Sites Total##Total New Lines 4 10##Basic Seats 0 0##Unified Communication Seats 4 10##Reception Console 0 0##Auto Attendant 0 5##Additional Hunt Group 0 3##Additional Voicemail Box 0 0##CQ Agent per UC Seat Subscribed 0 0##Toll Free Number(s) (plus usage) 0 0##Unassigned TNs 1 2##Detailed Order Summary##Customer: MSite_STDTWSIMONValidation_20170620101840##Detailed Order SummaryPage 1 of 4##Voice Selections: Voice Selections 1 of 1##Additional or Alternate TNs 0 0##Non-Published Directory Listing 7 16##Non-Listed Directory Listing 0 0##Additional Directory Listing 0 0##Customer Equipment 6 14##")
					&& pdfText.contains("##Address 1 Address 2 Site Type##12 ADAMS LN N/A teleworker##"));
			orderSummaryPage.PDFVerification(status, "Verification of Voice Selections in OS PDF File");
			orderSummaryPage.closeOrderSummary();
		} catch (Exception ex) {
			report.reportHardFailEvent("Detailed Order Summary Validations for Multisite(Teleworker Site) in PDF File",
					"Detailed Order Summary Validations for Multisite(Teleworker Site) in PDF File Failed"
							+ ex.getMessage());
			eMsg = "Detailed Order Summary Validations for Multisite(Teleworker Site) in PDF File Failed!!! "
					+ ex.getMessage();
			log.error(eMsg);
		}
	}

	/**************************************************************************************************************************************************************
	 * END:
	 * Verify_fields_are_correctly_populated_in_Voice_Provisioning_order_summary_pdf_as_in_order_summary_screen_for_Multisite_Standard_Teleworker_site
	 **************************************************************************************************************************************************************/

}
