package com.comcast.WebTopCases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.testng.annotations.Test;

import com.comcast.commons.WebtopPage;
import com.comcast.pages.simon.AA_BusinessHours;
import com.comcast.pages.simon.Admins;
import com.comcast.pages.simon.Canvas;
import com.comcast.pages.simon.EquipmentConfiguration;
import com.comcast.pages.simon.OrderInformation;
import com.comcast.pages.simon.SIMONMainPage;
import com.comcast.pages.simon.ServiceInformation;
import com.comcast.pages.simon.Settings;
import com.comcast.pages.simon.SimonLogInPage;
import com.comcast.utils.CsvFileReader;
import com.comcast.pages.simon.SIMONMainPage.Locators;
import com.comcast.pages.webtop.AccountProfileTab;
import com.comcast.pages.webtop.ReviewOrderForAccuracyAndAcceptance;
import com.comcast.pages.webtop.WebTopLogInPage;
import com.comcast.pages.webtop.WebTopMainPage;
import com.comcast.pages.simon.SeatConfiguration;

public class SimonValidations extends CommonE2EFlow {

	/*********** Verify_the_changes_made_are_getting_saved_when_switching_from_one_tab_to_another_tab **************************************/
	@Test(priority = 8000, groups = { "New Connect", "All" })
	public void orderInformationValidations() throws Exception {
		try {
			SIMONMainPage simonMainPage = new SIMONMainPage(frameworkContext);
			OrderInformation orderInformation = new OrderInformation(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				simonMainPage.SFOpportunityIDSearch();
			}
			orderInformation.orderInformationAutoSave();
			orderInformation.verifyIfOrderInformationAutoSaved();
		} catch (Exception Ex) {
			report.reportHardFailEvent(
					"Verify Order Information is auto saved when navigate to service Information tab",
					"Verification of Order Information is auto saved when navigate to service Information tab Failed"
							+ Ex.getMessage());
			eMsg = report.getMessage()
					+ "Verification of Order Information is auto saved when navigate to service Information tab in SIMON Failed!!! "
					+ Ex.getMessage();

			log.error(eMsg);
		}
	}

	/*********** Verify_SOA_file_upload_Failing_when_the_file_has_already_been_uploaded_in_SIMON **************************************/
	@Test(priority = 8100, groups = { "New Connect", "All" })
	public void verifyDuplicateSiteError() throws Exception {
		try {
			SIMONMainPage simonMainPage = new SIMONMainPage(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
			}
			simonMainPage.verifyDuplicateSiteError();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verify the Error Message when File with Same name is uploaded",
					"Verification of Error Message when File with Same name is uploaded-Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Verification of Error Message when File with Same name is uploaded-Failed!!! "
					+ Ex.getMessage();

			log.error(eMsg);
		}
	}

	/*********** US428587_US482600_Verify_voicemail_box_are_getting_added_or_removed_successfully **************************************/
	@Test(priority = 8200, groups = { "New Connect", "All" })
	public void verifyadddeleteVoicemail() throws Exception {
		try {
			SIMONMainPage simonMainPage = new SIMONMainPage(frameworkContext);
			ServiceInformation serviceInformation = new ServiceInformation(frameworkContext);

			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				simonMainPage.SFOpportunityIDSearch();
			}
			serviceInformation.addVoicemailinServiceInformation("Configure Seat");
			serviceInformation.deleteVoicemailinServiceInformation("Configure Seat");
		} catch (Exception Ex) {
			report.reportHardFailEvent(
					"Verify the Addition and Delete Operation for Voicemail Box in Service Information",
					"Verify the Addition and Delete Operation for Voicemail Box in Service Information-Failed"
							+ Ex.getMessage());
			eMsg = report.getMessage()
					+ "Verify the Addition and Delete Operation for Voicemail Box in Service Information-Failed!!! "
					+ Ex.getMessage();
			log.error(eMsg);
		}
	}

	/*********** Verify_HG_CQ_TN_displayed_When_AA_prompts_DifferentGreetingCombinations **************************************/
	@Test(priority = 8250, groups = { "New Connect", "All" })
	public void configureAABusinessHrstoVerifyHuntGroupAutoAttendentTN() throws Exception {
		try {
			SIMONMainPage simonMainPage = new SIMONMainPage(frameworkContext);
			AA_BusinessHours autoBusiness = new AA_BusinessHours(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				simonMainPage.SFOpportunityIDSearch();
			}
			dataSet = frameworkContext.getDataTable();
			autoBusiness.configureAASettings(dataSet.getValue("AASeatToConfigure"));
			String[] greeting = { "Transfer To Operator", "Transfer With Prompt", "Transfer Without Prompt" };
			autoBusiness.verifyAvailableGreetingUsersForAA(greeting, "Business Hours");
			autoBusiness.verifyAvailableGreetingUsersForAA(greeting, "After Hours");
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verify the HG/CQ TN are auto Promted for Different Greeting Combinations",
					"Verification of the HG/CQ TN are auto Promt for Different Greeting Combinations Failed"
							+ Ex.getMessage());
			eMsg = report.getMessage()
					+ "Verification of the HG/CQ TN are auto Promt for Different Greeting Combinations Failed!!! "
					+ Ex.getMessage();
			log.error(eMsg);
		}
	}

	/*********** SIMON_Auto_Populate_Information_in_Customer_Contact_for_Recording **************************************/

	@Test(priority = 7800, groups = { "New Connect", "All" })
	public void verifyFieldParametersforCustomerContactforRecording() throws Exception {
		try {
			SIMONMainPage simonMainPage = new SIMONMainPage(frameworkContext);
			ServiceInformation serviceInformation = new ServiceInformation(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				simonMainPage.SFOpportunityIDSearch();
			}
			serviceInformation.verifyParametersforCustomerContactforRecording("FName LName");
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verification of Field Paramenters for Customer Contact for Recording",
					"Verification of Field Paramenters for Customer Contact for Recording Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Verification of Field Paramentes for Customer Contact for Recording Failed!!!"
					+ Ex.getMessage();
			log.error(eMsg);
		}
	}

	@Test(priority = 8000, groups = { "New Connect", "All" })
	public void updateCustDetailsInSOA() throws Exception {
		try {
			WebtopPage webtopPage = new WebtopPage(frameworkContext);
			webtopPage.updateCustDetailsInSOA();
			log.warn("SOA File updator script execution is completed using external script.");
			getSOAFileDetails();
			dataDump.setValue("OrderCategory", "New Connect");
			dataDump.setValue("SiteType", "SingleSite");
		} catch (Exception Ex) {
			report.reportHardFailEvent("Update Customer Details In SOA with blank Values",
					"Update Customer Details In SOA with blank Values Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Update Customer Details In SOA with blank Values Failed!!!" + Ex.getMessage();
			log.error(eMsg);
		}
	}

	@Test(priority = 8500, groups = { "New Connect", "All" })
	public void deleteSite() throws Exception {
		DeleteSiteIfExists();
	}

	@Test(priority = 9000, groups = { "New Connect", "All" })
	public void uploadSite() throws Exception {
		SIMONUploadDataFile();
	}

	@Test(priority = 9575, groups = { "New Connect", "All" })
	public void verifyErrorMsgForCustomerContactDetailsWhenGeneratingWebTopImportFile() throws Exception {
		try {
			SIMONMainPage simonMainPage = new SIMONMainPage(frameworkContext);
			Settings settings = new Settings(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
			}
			simonMainPage.SFOpportunityIDSearch();
			settings.generateWebTopFile();
			simonMainPage.verifyErrorMsgForCustomerContactDetails();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verification of Field Paramenters for Customer Contact for Recording",
					"Update Customer Details In SOA Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Update of Field Paramentes for Customer Contact for Recording Failed!!!"
					+ Ex.getMessage();
			log.error(eMsg);
		}
	}

	/***********
	 * US517738_US397674_Verify_SO_
	 * file_upload_is_getting_failed_if_the_file_doesnt_have_SF_OPP_ID
	 **************************************/

	@Test(priority = 9590, groups = { "New Connect", "All" })
	public void verifyErroruploadingSOAwithBlankSFOppID() throws Exception {
		try {
			SIMONMainPage sIMONMainPage = new SIMONMainPage(frameworkContext);
			sIMONMainPage.verifyUploadFailForBlankOpportunityID("SF Opportunity Id cannot be empty");
		} catch (Exception Ex) {
			report.reportHardFailEvent("Uploading SOA with Blank SF Opportunity ID in SIMON",
					"Uploading SOA with Blank SF Opportunity ID in SIMON" + Ex.getMessage());
			eMsg = report.getMessage() + "Uploading SOA with Blank SF Opportunity ID in SIMON" + Ex.getMessage();
			log.error(eMsg);
		}
	}

	/*********** Import_new_Polycom_VVX_501_HD_devices_into_SIMON_upon_Classic_SOA_file_upload **************************************/
	@Test(priority = 10000, groups = { "New Connect", "All" })
	public void verifyLatestPolycomDevicesAreDisplayedInALLSeats() throws Exception {
		try {
			SeatConfiguration seatConfiguration = new SeatConfiguration(frameworkContext);
			String[] SeatName = { "TABTNUCSeat001_501", "UCSeat002PC501_1W", "UCSeat003PC501_2W", "UCSeat004PC501_3W",
					"UCSeat005PC501_WC", "UCSeat006PC501_WC1", "UCSeat007PC501_WC2", "UCSeat008PC501_WC3" };
			String[] equipmentType = { "Polycom VVX 501 HD", "Polycom VVX 501 HD", "Polycom VVX 501 HD",
					"Polycom VVX 501 HD", "Polycom VVX 501 HD W Camera", "Polycom VVX 501 HD W Camera",
					"Polycom VVX 501 HD W Camera", "Polycom VVX 501 HD W Camera" };
			String[] sideCars = { "", "1", "2", "3", "", "1", "2", "3" };
			seatConfiguration.VerifyDevicedisplayedinEquipmentSettings(SeatName, equipmentType, sideCars);
		} catch (Exception Ex) {
			report.reportHardFailEvent(
					"Verification of the latest Polycom Devices uploaded for respective seats in SIMON",
					"Verification of the latest Polycom Devices uploaded for respective seats in SIMON-Failed"
							+ Ex.getMessage());
			eMsg = report.getMessage()
					+ "Verification of the latest Polycom Devices uploaded for respective seats in SIMON-Failed"
					+ Ex.getMessage();
			log.error(eMsg);
		}
	}

	@Test(priority = 10100, groups = { "New Connect", "All" })
	public void validateNetxUSAFile() throws Exception {
		try {
			SIMONMainPage simonMainPage = new SIMONMainPage(frameworkContext);
			Settings settings = new Settings(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				simonMainPage.SFOpportunityIDSearch();
			}
			settings.generateNeteXFile();
			String[] seatName = { "TABTNUCSeat001_501", "UCSeat002PC501_1W", "UCSeat003PC501_2W", "UCSeat004PC501_3W",
					"UCSeat005PC501_WC", "UCSeat006PC501_WC1", "UCSeat007PC501_WC2", "UCSeat008PC501_WC3" };
			String[] make = { "Polycom", "Polycom", "Polycom", "Polycom", "Polycom", "Polycom", "Polycom", "Polycom" };
			settings.validateNetxUSAFileContent(seatName, make);
		} catch (Exception Ex) {
			report.reportHardFailEvent("Validation of NetxUSA File Device and Make",
					"Validation of NetxUSA File Device and Make failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Validation of NetxUSA File Device and Make failed" + Ex.getMessage();
			log.error(eMsg);
		}
	}

	/******************* US428587_US482600_Verify_no_seat_Voicemail_are_displayed_in_Services_Information_tab_under_Site_Details_from_SOA_file_upload ******************************/
	@Test(priority = 8000, groups = { "New Connect", "All" })
	public void verifyNoVoicemailBoxDisplayedInSIMON() throws Exception {
		try {
			SIMONMainPage simonMainPage = new SIMONMainPage(frameworkContext);
			ServiceInformation serviceInformation = new ServiceInformation(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				simonMainPage.SFOpportunityIDSearch();
			}
			serviceInformation.voicemailNegetiveScenario();
		} catch (Exception Ex) {
			report.reportHardFailEvent(
					"Verify Order Information is auto saved when navigate to service Information tab",
					"Verification of Order Information is auto saved when navigate to service Information tab Failed"
							+ Ex.getMessage());
			eMsg = report.getMessage()
					+ "Verification of Order Information is auto saved when navigate to service Information tab in SIMON Failed!!! "
					+ Ex.getMessage();

			log.error(eMsg);
		}
	}

	/******************* US581748_SEDEV_US428596_Site_Detail_Extension_Automation_New_Field_EXtension_Generation_Seed ******************************/
	@Test(priority = 8100, groups = { "New Connect", "All" })
	public void verifyPresenceOfExtensionGenerationSeed() throws Exception {
		try {
			SIMONMainPage simonMainPage = new SIMONMainPage(frameworkContext);
			ServiceInformation serviceInformation = new ServiceInformation(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				simonMainPage.SFOpportunityIDSearch();
			}
			serviceInformation.verifyExetentionGenerationSeed();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verify if the Extention Generation Seed is displayed in Service Information",
					"Extention Generation Seed is not displayed in Service Information-Failed!!!" + Ex.getMessage());
			eMsg = report.getMessage() + "Extention Generation Seed is not displayed in Service Information-Failed!!!"
					+ Ex.getMessage();

			log.error(eMsg);
		}
	}

	/******************* US581748_SEDEV_US428596_Site_Detail_Extension_Automation_New_Field_Use_TN_If_Available ******************************/
	@Test(priority = 8100, groups = { "New Connect", "All" })
	public void verifyPresenceOfUseTNIfAvailable() throws Exception {
		try {
			SIMONMainPage simonMainPage = new SIMONMainPage(frameworkContext);
			ServiceInformation serviceInformation = new ServiceInformation(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				simonMainPage.SFOpportunityIDSearch();
			}
			serviceInformation.verifyUseTNIfAvailableInServiceInformation();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verify use TN if available is displayed in Service Information",
					"Verify Use TN if available is displayed in Service Information-Failed!!!" + Ex.getMessage());
			eMsg = report.getMessage() + "Use TN if available is displayed in Service Information-Failed"
					+ Ex.getMessage();

			log.error(eMsg);
		}
	}

	/******************* Prod_fix_Validation_1_UC_seat_with_1_Voicemail_box_will_generate_only_1_Line_count_in_SIMON ******************************/
	@Test(priority = 8150, groups = { "New Connect", "All" })
	public void verifyLineCountForTheSeatsConfigured() throws Exception {
		try {
			SIMONMainPage simonMainPage = new SIMONMainPage(frameworkContext);
			ServiceInformation serviceInformation = new ServiceInformation(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				simonMainPage.SFOpportunityIDSearch();
			}
			serviceInformation.verifyTotalNewLines("1");
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verify the new lines Displayed in the Service Information",
					"New lines is not displayed as expected in the Service Information-Failed!!!" + Ex.getMessage());
			eMsg = report.getMessage() + "New lines is not displayed as expected in the Service Information-Failed"
					+ Ex.getMessage();

			log.error(eMsg);
		}
	}

	/******************* Verify_No_seat_voicemail_box_is_created_in_WebTop_file_if_the_simon_order_has_VM_box_created_in_service_inofrmation_tab ******************************/
	@Test(priority = 9050, groups = { "New Connect", "All" })
	public void addVoicemail() throws Exception {
		try {
			SIMONMainPage simonMainPage = new SIMONMainPage(frameworkContext);
			ServiceInformation serviceInformation = new ServiceInformation(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
			}
			simonMainPage.SFOpportunityIDSearch();
			serviceInformation.addVoiceMailInServiceInformation();
			simonMainPage.SFOpportunityIDSearch();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verify the Add Voicemail Box in Service Information",
					"Verify the Add Voicemail Box in Service Information-Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Verify the Add Voicemail Box in Service Information-Failed!!! "
					+ Ex.getMessage();
			log.error(eMsg);
		}
	}

	@Test(priority = 13600, groups = { "New Connect", "All" })
	public void verifyVMBundle() throws Exception {
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		AccountProfileTab accountProfileTab = new AccountProfileTab(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
			}
			webTopMainPage.searchOrder("Group ID");
			accountProfileTab.verifyAdditionalVMBundle();
		} catch (Exception Ex) {
			report.reportSoftFailEvent("Bundle verification in Account profile page for Voice Mail",
					"Bundle verification in Account profile page for Voice Mail Failed");
			eMsg = report.getMessage() + "Bundle verification in Account profile page for Voice Mail Failed!!! "
					+ Ex.getMessage();

			log.error(eMsg);
		}
	}

	/*********** Verify_sorting_is_working_properly_in_AA_prompt_screen_for_TransferToOperator_TransferWithPrompt_TransferWithOutPrompt **************************************/
	@Test(priority = 8250, groups = { "New Connect", "All" })
	public void verifyAvailableSeatsAndGroupsInAscOrDescOrder() throws Exception {
		try {
			SIMONMainPage simonMainPage = new SIMONMainPage(frameworkContext);
			AA_BusinessHours autoBusiness = new AA_BusinessHours(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				simonMainPage.SFOpportunityIDSearch();
			}
			dataSet = frameworkContext.getDataTable();
			autoBusiness.configureAASettings(dataSet.getValue("AASeatToConfigure"));
			List<String> expectedUserList = new ArrayList<String>();
			expectedUserList.add("Auto Attendant");
			expectedUserList.add("Auto Attendant");
			expectedUserList.add("Auto Attendant");
			expectedUserList.add("Auto Attendant");
			expectedUserList.add("Call Queue");
			expectedUserList.add("Hunt Group");
			expectedUserList.add("Hunt Group");
			expectedUserList.add("Hunt Group");
			expectedUserList.add("Seat");
			expectedUserList.add("Seat");
			expectedUserList.add("Seat");
			String[] greeting = { "Transfer To Operator", "Transfer With Prompt", "Transfer Without Prompt" };
			autoBusiness.verifyOrderOfUsersInAvailableSeatsAndGroups(greeting, "Business Hours", "ASC",
					expectedUserList);
			Collections.reverse(expectedUserList);
			autoBusiness.verifyOrderOfUsersInAvailableSeatsAndGroups(greeting, "Business Hours", "DSEC",
					expectedUserList);
			Collections.reverse(expectedUserList);
			autoBusiness.verifyOrderOfUsersInAvailableSeatsAndGroups(greeting, "After Hours", "ASC", expectedUserList);
			Collections.reverse(expectedUserList);
			autoBusiness.verifyOrderOfUsersInAvailableSeatsAndGroups(greeting, "After Hours", "DSEC", expectedUserList);
		} catch (Exception Ex) {
			report.reportHardFailEvent(
					"Verification of Available Seats and Group Order is displayed under available Seats/Groups for the AA with ASC/DSEC order",
					"Verification of Available Seats and Group Order is displayed under available Seats/Groups for the AA Failed"
							+ Ex.getMessage());
			eMsg = report.getMessage()
					+ "Verification of Available Seats and Group Order is displayed under available Seats/Groups for the AA in SIMON Failed!!! "
					+ Ex.getMessage();
		}
	}

	/*********** 001_EMail_address_already_present_for_a_BVE_service **************************************/
	@Test(priority = 8250, groups = { "New Connect", "All" })
	public void validateEMailAddress() throws Exception {
		try {
			SIMONMainPage simonMainPage = new SIMONMainPage(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				simonMainPage.SFOpportunityIDSearch();
			}
			new Settings(frameworkContext).validateEmailAddresses("All emails are valid.");
		} catch (Exception Ex) {
			report.reportHardFailEvent("Validate error Message for Email Addresses",
					"Validate error Message for Email Addresses is failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Validate error Message for Email Addresses is failed" + Ex.getMessage();
			log.error(eMsg);
		}
	}

	@Test(priority = 8255, groups = { "New Connect", "All" })
	public void validatePrimaryManager() throws Exception {
		try {
			SIMONMainPage simonMainPage = new SIMONMainPage(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				simonMainPage.SFOpportunityIDSearch();
			}
			new Settings(frameworkContext).validatePrimaryManager(
					"No Primary manager matching the Local Biller account or SingleView Child account found.");
		} catch (Exception Ex) {
			report.reportHardFailEvent("Validate error Message for Primary Manager",
					"Validate error Message for Primary Manager is failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Validate error Message for Primary Manager is failed" + Ex.getMessage();
			log.error(eMsg);
		}
	}

	@Test(priority = 10500, groups = { "New Connect", "All" })
	public void secondarySiteValidateEMailAddress() throws Exception {
		validateEMailAddress();
	}

	@Test(priority = 10600, groups = { "New Connect", "All" })
	public void secondarySiteValidatePrimaryManager() throws Exception {
		try {
			SIMONMainPage simonMainPage = new SIMONMainPage(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				simonMainPage.SFOpportunityIDSearch();
			}
			new Settings(frameworkContext).validatePrimaryManager(
					"The Primary Manager Email that you entered cannot be accepted. An existing Primary Manager already exists on this account.");
		} catch (Exception Ex) {
			report.reportHardFailEvent("Validate error Message for Primary Manager",
					"Validate error Message for Primary Manager is failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Validate error Message for Primary Manager is failed" + Ex.getMessage();
			log.error(eMsg);
		}
	}

	/*********** 001_EMail_address_already_present_for_a_BVE_service **************************************/
	@Test(priority = 8250, groups = { "New Connect", "All" })
	public void validateEMailAddressMultipleTimes() throws Exception {
		try {
			SIMONMainPage simonMainPage = new SIMONMainPage(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				simonMainPage.SFOpportunityIDSearch();
			}
			for (int idx = 0; idx < 3; idx++) {
				new Settings(frameworkContext)
						.validateEmailAddresses("One or more invalid email(s) found. Please check the seats.");
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Validate error Message for Email Addresses multiple times",
					"Validate error Message for Email Addresses multiple times is failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Validate error Message for Email Addresses multiple times is failed"
					+ Ex.getMessage();
			log.error(eMsg);
		}
	}

	@Test(priority = 8300, groups = { "New Connect", "All" })
	public void correctTheEmailIdForOneSeat() throws Exception {
		try {
			SIMONMainPage simonMainPage = new SIMONMainPage(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				simonMainPage.SFOpportunityIDSearch();
			}
			new SeatConfiguration(frameworkContext).updateEmailId("UCSeat001");
		} catch (Exception Ex) {
			report.reportHardFailEvent("Update Email Id for the Seat",
					"Update Email Id for the Seat is failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Update Email Id for the Seat is failed" + Ex.getMessage();
			log.error(eMsg);
		}
	}

	@Test(priority = 8350, groups = { "New Connect", "All" })
	public void validateEMailAddressMultipleTimesAfterEMailIdCorrection() throws Exception {
		try {
			SIMONMainPage simonMainPage = new SIMONMainPage(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				simonMainPage.SFOpportunityIDSearch();
			}
			for (int idx = 0; idx < 3; idx++) {
				new Settings(frameworkContext).validateEmailAddresses("All emails are valid.");
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Validate email addresses multiple times with unique mail Ids",
					"Validate email addresses multiple times with unique mail Ids failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Validate email addresses multiple times with unique mail Ids is failed"
					+ Ex.getMessage();
			log.error(eMsg);
		}
	}

	@Test(priority = 10000, groups = { "New Connect", "All" })
	public void verifyAllNewDevicesUnderAllSeates() throws Exception {
		try {
			SeatConfiguration seatConfiguration = new SeatConfiguration(frameworkContext);
			String[] SeatName = { "TABTNUCSeat001_501", "UCSeat002PC501_1W", "UCSeat003PC501_2W", "UCSeat004PC501_3W",
					"UCSeat005PC501_WC", "UCSeat006PC501_WC1", "UCSeat007PC501_WC2", "UCSeat008PC501_WC3",
					"UCSeat009PC601", "UCSeat010PC601_1W", "UCSeat011PC601_2W", "UCSeat012PC601_3W",
					"UCSeat013PC601_WC", "UCSeat014PC601_WC1", "UCSeat015PC601_WC2", "UCSeat016PC601_WC3",
					"UCSeat017PC411", "UCSeat018PC411_1W", "UCSeat019PC411_2W", "UCSeat020PC411_3W", "UCSeat021PC311" };
			String[] equipmentType = { "Polycom VVX 501 HD", "Polycom VVX 501 HD", "Polycom VVX 501 HD",
					"Polycom VVX 501 HD", "Polycom VVX 501 HD W Camera", "Polycom VVX 501 HD W Camera",
					"Polycom VVX 501 HD W Camera", "Polycom VVX 501 HD W Camera", "Polycom VVX 601 HD",
					"Polycom VVX 601 HD", "Polycom VVX 601 HD", "Polycom VVX 601 HD", "Polycom VVX 601 HD W Camera",
					"Polycom VVX 601 HD W Camera", "Polycom VVX 601 HD W Camera", "Polycom VVX 601 HD W Camera",
					"Polycom VVX 411 HD", "Polycom VVX 411 HD", "Polycom VVX 411 HD", "Polycom VVX 411 HD",
					"Polycom VVX 311 HD" };
			String[] sideCars = { "", "1", "2", "3", "", "1", "2", "3", "", "1", "2", "3", "", "1", "2", "3", "", "1",
					"2", "3", "" };
			seatConfiguration.VerifyDevicedisplayedinEquipmentSettings(SeatName, equipmentType, sideCars);
		} catch (Exception Ex) {
			report.reportHardFailEvent(
					"Verification of the New Polycom Devices and Images are displayed for respective devices in SIMON",
					"Verification of the New Polycom Devices and Images are displayed for respective devices in SIMON-Failed"
							+ Ex.getMessage());
			eMsg = report.getMessage()
					+ "Verification of the New Polycom Devices and Images are displayed for respective devices in SIMON-Failed"
					+ Ex.getMessage();
			log.error(eMsg);
		}
	}

	@Test(priority = 10100, groups = { "New Connect", "All" })
	public void validateNetxUSAFileForAllNewDevices() throws Exception {
		try {
			SIMONMainPage simonMainPage = new SIMONMainPage(frameworkContext);
			Settings settings = new Settings(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				simonMainPage.SFOpportunityIDSearch();
			}
			settings.generateNeteXFile();
			String[] seatName = { "TABTNUCSeat001_501", "UCSeat002PC501_1W", "UCSeat003PC501_2W", "UCSeat004PC501_3W",
					"UCSeat005PC501_WC", "UCSeat006PC501_WC1", "UCSeat007PC501_WC2", "UCSeat008PC501_WC3",
					"UCSeat009PC601", "UCSeat010PC601_1W", "UCSeat011PC601_2W", "UCSeat012PC601_3W",
					"UCSeat013PC601_WC", "UCSeat014PC601_WC1", "UCSeat015PC601_WC2", "UCSeat016PC601_WC3",
					"UCSeat017PC411", "UCSeat018PC411_1W", "UCSeat019PC411_2W", "UCSeat020PC411_3W", "UCSeat021PC311" };
			String[] make = { "Polycom", "Polycom", "Polycom", "Polycom", "Polycom", "Polycom", "Polycom", "Polycom",
					"Polycom", "Polycom", "Polycom", "Polycom", "Polycom", "Polycom", "Polycom", "Polycom", "Polycom",
					"Polycom", "Polycom", "Polycom", "Polycom" };
			settings.validateNetxUSAFileContent(seatName, make);
		} catch (Exception Ex) {
			report.reportHardFailEvent("Validation of NetxUSA File Device and Make for all new Polycom devices",
					"Validation of NetxUSA File Device and Make for all new Polycom devices failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Validation of NetxUSA File Device and Make for all new Polycom devices failed"
					+ Ex.getMessage();
			log.error(eMsg);
		}
	}

	/**
	 * BEGIN:
	 * TC_003_SIMON_PrimaryManagerValidation_MultiSite_with_two_PMs_BCP_Enabled_Sites
	 **/

	@Test(priority = 8000, groups = { "New Connect", "All" })
	public void updateLBAccountAndChildBAN() throws Exception {
		try {
			SIMONMainPage simonMainPage = new SIMONMainPage(frameworkContext);
			OrderInformation orderInformation = new OrderInformation(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				simonMainPage.SFOpportunityIDSearch();
			}
			orderInformation.updateLBAccountAndChildBAN("");
		} catch (Exception Ex) {
			report.reportHardFailEvent("Update Order Information in Order Information tab",
					"Update Order Information in Order Information tab Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Update Order Information in Order Information tab in SIMON Failed!!! "
					+ Ex.getMessage();
			log.error(eMsg);
		}
	}

	@Test(priority = 8500, groups = { "New Connect", "All" })
	public void validateErrorMessageForMultiplePMs() throws Exception {
		try {
			SIMONMainPage simonMainPage = new SIMONMainPage(frameworkContext);
			Settings settings = new Settings(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				simonMainPage.SFOpportunityIDSearch();
			}
			settings.generateWebTopFile();
			simonMainPage.verifyErrorMsgForMultiplePMs();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Update Order Information in Order Information tab",
					"Update Order Information in Order Information tab Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Update Order Information in Order Information tab in SIMON Failed!!! "
					+ Ex.getMessage();
			log.error(eMsg);
		}
	}

	@Test(priority = 9000, groups = { "New Connect", "All" })
	public void changeOnePMAsSM() throws Exception {
		try {
			SIMONMainPage simonMainPage = new SIMONMainPage(frameworkContext);
			Admins admins = new Admins(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
			}
			simonMainPage.SFOpportunityIDSearch();
			admins.changeOnePMAsSM();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Update Order Information in Order Information tab",
					"Update Order Information in Order Information tab Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Update Order Information in Order Information tab in SIMON Failed!!! "
					+ Ex.getMessage();
			log.error(eMsg);
		}
	}

	@Test(priority = 9500, groups = { "New Connect", "All" })
	public void verifyErrorMsgIfOnePMInPrimarySite() throws Exception {
		try {
			SIMONMainPage simonMainPage = new SIMONMainPage(frameworkContext);
			Settings settings = new Settings(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				simonMainPage.SFOpportunityIDSearch();
			}
			settings.generateWebTopFile();
			simonMainPage.verifyErrorMsgIfOnePMInASite();
		} catch (Exception Ex) {
			report.reportHardFailEvent("PM Validation Error message is not displayed as expected",
					"PM Validation Error message is displayed and it is NOT expected" + Ex.getMessage());
			eMsg = report.getMessage() + "PM Validation Error message is displayed and it is NOT expected!!! "
					+ Ex.getMessage();
			log.error(eMsg);
		}
	}

	@Test(priority = 10000, groups = { "New Connect", "All" })
	public void secondarySiteCustomerDetails() throws Exception {
		if (!SimonLogInPage.simonLogedIn) {
			SIMONLogin();
		}
		if (dataDump.getValue("Site1_SFOpportunityId").isEmpty() || dataDump.getValue("Site1_SFOpportunityId") == null)
			dataDump.setValue("Site1_SFOpportunityId", dataDump.getValue("SFOpportunityId"));
		int SecondaryDSiteSFID = Integer.parseInt(dataDump.getValue("Site1_SFOpportunityId")) + 1;
		dataDump.setValue("SFOpportunityId", String.valueOf(SecondaryDSiteSFID));
		dataDump.setValue("Site2_SFOpportunityId", String.valueOf(SecondaryDSiteSFID));
		new SIMONMainPage(frameworkContext).SFOpportunityIDSearch();
		customerDetails();
	}

	@Test(priority = 10500, groups = { "New Connect", "All" })
	public void secondarySiteUpdateLBAccountAndChildBAN() throws Exception {
		try {
			SIMONMainPage simonMainPage = new SIMONMainPage(frameworkContext);
			OrderInformation orderInformation = new OrderInformation(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				simonMainPage.SFOpportunityIDSearch();
			}
			orderInformation.updateLBAccountAndChildBAN("917085447");
		} catch (Exception Ex) {
			report.reportHardFailEvent("Update Order Information in Order Information tab",
					"Update Order Information in Order Information tab Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Update Order Information in Order Information tab in SIMON Failed!!! "
					+ Ex.getMessage();
			log.error(eMsg);
		}
	}

	@Test(priority = 11000, groups = { "New Connect", "All" })
	public void verifyErrorMsgIfOnePMInPrimarySiteAndNoPMInSecondarySite() throws Exception {
		verifyErrorMsgIfOnePMInPrimarySite();
	}
	
	/*********** NC_Standard_Site_Base_station_settings_Updation **************************************/
	@Test(priority = 7500, groups = { "New Connect", "All" })
	public void updateBaseStation() throws Exception {
		try {
			SIMONMainPage simonMainPage = new SIMONMainPage(frameworkContext);
			EquipmentConfiguration equipmentConfiguration = new EquipmentConfiguration(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				simonMainPage.SFOpportunityIDSearch();
			}
			equipmentConfiguration.baseStationSettings();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Base Station Settings Update in SIMON Equipments page",
					"Base Station Settings Update in SIMON Equipments page Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Base Station Settings Update in Equipments page in SIMON Failed!!! "
					+ Ex.getMessage();
			log.error(eMsg);
		}
	}
	
	/*********** US478859_Verify_canvas_section_is_displayed_in_full_screen_after_clicking_canvas_icon_at_the_top_of_tools_section **************************************/
	@Test(priority = 7500, groups = { "New Connect", "All" })
	public void verifyCanvasMode() throws Exception {
		try {
			SIMONMainPage simonMainPage = new SIMONMainPage(frameworkContext);
			Canvas canvas = new Canvas(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				simonMainPage.SFOpportunityIDSearch();
			}
			canvas.canvasMode();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Canvas mode verification", "Canvas mode verification Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Canvas mode verification in SIMON Failed!!! " + Ex.getMessage();			
			log.error(eMsg);
		}
	}
	
}
