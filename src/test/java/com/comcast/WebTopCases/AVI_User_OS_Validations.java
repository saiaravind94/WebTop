package com.comcast.WebTopCases;

import org.testng.annotations.Test;

import com.comcast.commons.WebtopPage;
import com.comcast.pages.simon.AA_BusinessHours;
import com.comcast.pages.simon.CallQueue;
import com.comcast.pages.simon.HuntGroup;
import com.comcast.pages.simon.OrderSummaryPage;
import com.comcast.pages.simon.SIMONMainPage;
import com.comcast.pages.simon.SeatConfiguration;
import com.comcast.pages.simon.SharedCallAppearance;
import com.comcast.pages.simon.SimonLogInPage;

public class AVI_User_OS_Validations extends CommonE2EFlow {

	/*******BEGIN US490574_Verify_Callqueue_fields_and_its_values_are_displayed_correctly_in_call_flow_section_once_user_selects_AVI_summary_in_OrderSummaryDropdown */
	@Test(priority = 7250, groups = { "New Connect", "All" })
	public void callQueueConfiguration() {
		try {
			SIMONMainPage SimonMainPage = new SIMONMainPage(frameworkContext);
			SeatConfiguration seatConfiguration = new SeatConfiguration(frameworkContext);
			CallQueue callQueue = new CallQueue(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				SimonMainPage.SFOpportunityIDSearch();
			}
			seatConfiguration.enableCallQueueFeature();
			callQueue.configureCallQueue("Unassigned", "5124", "Eastern", "856-785-1234", "3", "3", "534-543-5435", "2",
					"3", "testing");
			callQueue.configureScriptsCallQueue();
			callQueue.addUsersinCallQueue("UCSeat001");
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verification of Configurations for Call Queue in Call Queue and All Seats",
					"Verification of Configurations for Call Queue in Call Queue and All Seats failed"
							+ Ex.getMessage());
			eMsg = report.getMessage()
					+ "Verification of Configurations for Call Queue in Call Queue and All Seats failed"
					+ Ex.getMessage();
			log.error(eMsg);
		}
	}

	@Test(priority = 7300, groups = { "New Connect", "All" })
	public void callQueueConfigurationAVIOrderSummaryVerification() {
		try {
			SIMONMainPage SimonMainPage = new SIMONMainPage(frameworkContext);
			OrderSummaryPage orderSummaryPage = new OrderSummaryPage(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				SimonMainPage.SFOpportunityIDSearch();
			}
			this.dataSet = frameworkContext.getDataTable();
			orderSummaryPage.launchOrderSumaryType("AVI");
			orderSummaryPage.verifyCallQueueAVIOS(dataSet.getValue("CallQueueDetails"),"5124", "testing", "LN", "CQB",
					"Eastern", "Simultaneous", "3", "3",dataSet.getValue("SeatNameToEnableBLF"), "534-543-5435", "2");
			orderSummaryPage.closeOrderSummary();
			sleep(7000);
		} catch (Exception Ex) {
			report.reportHardFailEvent(
					"Verification of Field Parameters for Call Queue Displayed in the AVI Order Summary Page",
					"Verification of Field Parameters for Call Queue Displayed in the AVI Order Summary Page failed"
							+ Ex.getMessage());
			eMsg = report.getMessage()
					+ "Verification of Field Parameters for Call Queue Displayed in the AVI Order Summary Page failed"
					+ Ex.getMessage();
			log.error(eMsg);
		}
	}

	/******* Verify_AA_fields_and_its_values_are_displayed_correctly_in_call_flow_section_of_AVI_Order_Summary */
	@Test(priority = 7500, groups = { "New Connect", "All" })
	public void veifyAAinAVIOrderSummary() {
		try {
			SIMONMainPage SimonMainPage = new SIMONMainPage(frameworkContext);
			OrderSummaryPage orderSummaryPage = new OrderSummaryPage(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				SimonMainPage.SFOpportunityIDSearch();
			}
			orderSummaryPage.launchOrderSumaryType("AVI");
			String[] AutoAttedents = { "Unused AutoAttendant-1", "Unused AutoAttendant-2", "Unused AutoAttendant-3",
					"Unused AutoAttendant-4", "AA001" };
			orderSummaryPage.verifyAutoAttendentAVIOrderSummary(AutoAttedents);
			orderSummaryPage.closeOrderSummary();
			sleep(7000);
		} catch (Exception Ex) {
			report.reportHardFailEvent(
					"Verification of Field Parameters for Auto Attendent Displayed in the AVI Order Summary Page",
					"Verification of Field Parameters for Auto Attendent Displayed in the AVI Order Summary Page failed"
							+ Ex.getMessage());
			eMsg = report.getMessage()
					+ "Verification of Field Parameters for Call Queue Displayed in the AVI Order Summary Page failed"
					+ Ex.getMessage();
			log.error(eMsg);
		}
	}

	/** Verify_HG_fields_and_its_values_are_displayed_correctly_in_call_flow_section_in_AVI_OrderSummary **/
	@Test(priority = 7550, groups = { "New Connect", "All" })
	public void verifyHGInAVIOrderSummary() {
		try {
			SIMONMainPage SimonMainPage = new SIMONMainPage(frameworkContext);
			OrderSummaryPage orderSummaryPage = new OrderSummaryPage(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				SimonMainPage.SFOpportunityIDSearch();
			}
			orderSummaryPage.launchOrderSumaryType("AVI");
			String[] HuntGroups = { "HG001", "Unused HuntGroup1", "Unused HuntGroup2" };
			orderSummaryPage.verifyHuntGroupAVIOrderSummary(HuntGroups);
			orderSummaryPage.closeOrderSummary();
		} catch (Exception Ex) {
			report.reportHardFailEvent(
					"Verification of Field Parameters for Hunt Group Displayed in the AVI Order Summary Page",
					"Verification of Field Parameters for Hunt Group Displayed in the AVI Order Summary Page failed"
							+ Ex.getMessage());
			eMsg = report.getMessage()
					+ "Verification of Field Parameters for Hunt Group Displayed in the AVI Order Summary Page failed"
					+ Ex.getMessage();
			log.error(eMsg);
		}
	}
	/*******END: US490574_Verify_Callqueue_fields_and_its_values_are_displayed_correctly_in_call_flow_section_once_user_selects_AVI_summary_in_OrderSummaryDropdown */
	/********BEGIN:US490574_Verify_SCA_BLF_fields_and_its_values_are_displayed_correctly_in_CF Section_ AVI_summaryOSdropdown*********/
	@Test(priority = 7600, groups = { "New Connect", "All" })
	public void verifySCABLFFieldsInAVIOrderSummary() {
		try {
			SIMONMainPage SimonMainPage = new SIMONMainPage(frameworkContext);
			SeatConfiguration seatConfiguration=new SeatConfiguration(frameworkContext);
			SharedCallAppearance sca=new SharedCallAppearance(frameworkContext);
			OrderSummaryPage orderSummaryPage = new OrderSummaryPage(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				SimonMainPage.SFOpportunityIDSearch();
			}
			this.dataSet = frameworkContext.getDataTable();
			seatConfiguration.enableBLFFeature();
			sca.configureSharedCallAppearance("UCSeat003", "BLF");
			orderSummaryPage.launchOrderSumaryType("AVI");
			orderSummaryPage.verifySCABLFInAVIOrderSummary(dataSet.getValue("SeatNameToEnableBLF"), "UCSeat003", "BLF");
		} catch (Exception Ex) {
			report.reportHardFailEvent(
					"Verification of Field Parameters for SCA-BLF in the AVI Order Summary Page",
					"Verification of Field Parameters for SCA-BLF in the AVI Order Summary Page failed"
							+ Ex.getMessage());
			eMsg = report.getMessage()
					+ "Verification of Field Parameters for SCA-BLF in the AVI Order Summary Page failed"
					+ Ex.getMessage();
			log.error(eMsg);
		}
	}	
	/********END:US490574_Verify_SCA_BLF_fields_and_its_values_are_displayed_correctly_in_CF Section_ AVI_summaryOSdropdown*********/
	/********BEGIN:US428579_Verify_all_configurations_made_for_call_queue_is_displayed_correctly_in_user_service_summary_screen********/
	@Test(priority = 7700, groups = { "New Connect", "All" })
	public void verifyCallQueueFieldsInUserServiceSummary() {
		try {
			SIMONMainPage SimonMainPage = new SIMONMainPage(frameworkContext);
			CallQueue callQueue=new CallQueue(frameworkContext);
			SeatConfiguration seatConfiguration=new SeatConfiguration(frameworkContext);
			OrderSummaryPage orderSummaryPage = new OrderSummaryPage(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				SimonMainPage.SFOpportunityIDSearch();
			}
			seatConfiguration.enableCallQueueFeature();
			callQueue.configureCallQueue("Unassigned", "4578", "Eastern", "856-124-4577", "2", "2", "856-124-4578", "2", "3", "test");
			callQueue.configureScriptsCallQueue();
			callQueue.addUsersinCallQueue("UCSeat001");
			orderSummaryPage.launchOrderSumaryType("User Service");
			orderSummaryPage.verifyCQInUserOrderSummary("UCSeat001", "CQ");	
		} catch (Exception Ex) {
			report.reportHardFailEvent(
					"Verification of Field Parameters for Call Queue in the User Service Summary Page",
					"Verification of Field Parameters for Call Queue in the User Service Summary Page failed"
							+ Ex.getMessage());
			eMsg = report.getMessage()
					+ "Verification of Field Parameters for Call Queue in the User Service Summary Page failed"
					+ Ex.getMessage();
			log.error(eMsg);
		}
	}
	/********END:US428579_Verify_all_configurations_made_for_call_queue_is_displayed_correctly_in_user_service_summary_screen********/
	/********BEGIN:US428579_Verify_configurations_made_for_HG_ is_displayed_correctly_in_user_service_summary_screen********/
	@Test(priority = 7750, groups = { "New Connect", "All" })
	public void verifyHGFieldsInAVIOrderSummary() {
		try {
			SIMONMainPage SimonMainPage = new SIMONMainPage(frameworkContext);
			HuntGroup hg=new HuntGroup(frameworkContext);
			OrderSummaryPage orderSummaryPage = new OrderSummaryPage(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				SimonMainPage.SFOpportunityIDSearch();
			}
			hg.configureHuntGroupsettings("Eastern","5341");
			hg.configureUsersHuntGroup("UCSeat001");	
			orderSummaryPage.launchOrderSumaryType("User Service");
			orderSummaryPage.verifyHGInUserOrderSummary("UCSeat001", "HG");
		} catch (Exception Ex) {
			report.reportHardFailEvent(
					"Verification of Field Parameters for Hunt Group in the User Service Summary Page",
					"Verification of Field Parameters for Hunt Group in the User Service Summary Page failed"
							+ Ex.getMessage());
			eMsg = report.getMessage()
					+ "Verification of Field Parameters for Hunt Group in the User Service Summary Page failed"
					+ Ex.getMessage();
			log.error(eMsg);
		}
	}	
	/********END:US428579_Verify_configurations_made_for_HG_ is_displayed_correctly_in_user_service_summary_screen********/
	/********BEGIN:US490574_Verify_fields_values_in_equipment_summary_section_once_user_selects_AVI_summary_in_order_summary_dropdown********/
	@Test(priority = 7750, groups = { "New Connect", "All" })
	public void verifyEquipmentSummaryinAVIOrderSummary() {
		try {
			SIMONMainPage SimonMainPage = new SIMONMainPage(frameworkContext);
			OrderSummaryPage orderSummaryPage = new OrderSummaryPage(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				SimonMainPage.SFOpportunityIDSearch();
			}
			orderSummaryPage.launchOrderSumaryType("AVI");
			String equipmentName[]={"No Hardware","Polycom VVX 601 HD W Camera","Polycom VVX 501 HD","Polycom VVX 411 HD","Polycom VVX 311 HD"};
			String Amount[]={"3","1","1","1","1"};
			orderSummaryPage.verifyEquipmentSummaryInAVIOS(equipmentName, Amount);
		} catch (Exception Ex) {
			report.reportHardFailEvent(
					"Verification of Field Parameters for Hunt Group in the User Service Summary Page",
					"Verification of Field Parameters for Hunt Group in the User Service Summary Page failed"
							+ Ex.getMessage());
			eMsg = report.getMessage()
					+ "Verification of Field Parameters for Hunt Group in the User Service Summary Page failed"
					+ Ex.getMessage();
			log.error(eMsg);
		}
	}	
	/********END:US490574_Verify fields_values_in_equipment_summary_section_once_user_selects_AVI_summary_in_order_summary_dropdown********/
	/********BEGIN:US428579_Verify_all_configurations_made_for_auto_attendant_ is_displayed_correctly_in_user_service_summary_screen********/
	@Test(priority = 7750, groups = { "New Connect", "All" })
	public void verifyAAFieldsInUserServiceSummary() {
		try {
			SIMONMainPage SimonMainPage = new SIMONMainPage(frameworkContext);
			OrderSummaryPage orderSummaryPage = new OrderSummaryPage(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				SimonMainPage.SFOpportunityIDSearch();
			}
			orderSummaryPage.launchOrderSumaryType("User Service");
			
			String AANameCongigured[]={"AA001"};
			String AAType[]={"Auto Attendant"};
			orderSummaryPage.verifyUserServiceInOS(AANameCongigured, AAType);
		} catch (Exception Ex) {
			report.reportHardFailEvent(
					"Verification of Field Parameters for Auto Attendent in the User Service Summary Page",
					"Verification of Field Parameters for Auto Attendent in the User Service Summary Page failed"
							+ Ex.getMessage());
			eMsg = report.getMessage()
					+ "Verification of Field Parameters for Auto Attendent in the User Service Summary Page failed"
					+ Ex.getMessage();
			log.error(eMsg);
		}
	}	
	/********BEGIN:US428579_Verify_Configurations_made_for_UC_seat_is_displayed_correctly_in_user_service_summary_screen********/
	@Test(priority = 7800, groups = { "New Connect", "All" })
	public void verifyUCFieldsInUserServiceSummary() {
		try {
			SIMONMainPage SimonMainPage = new SIMONMainPage(frameworkContext);
			OrderSummaryPage orderSummaryPage = new OrderSummaryPage(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				SimonMainPage.SFOpportunityIDSearch();
			}
			orderSummaryPage.launchOrderSumaryType("User Service");
			
			String UserService[]={"UCSeat001","EAU001 ","UCSeat002"};
			String UCType[]={"UC Seat","UC Seat","UC Seat"};
			orderSummaryPage.verifyUserServiceInOS(UserService, UCType);
		} catch (Exception Ex) {
			report.reportHardFailEvent(
					"Verification of Field Parameters for UC Seat in the User Service Summary Page",
					"Verification of Field Parameters for UC Seat in the User Service Summary Page failed"
							+ Ex.getMessage());
			eMsg = report.getMessage()
					+ "Verification of Field Parameters for UC Seat in the User Service Summary Page failed"
					+ Ex.getMessage();
			log.error(eMsg);
		}
	}
	/********END:US428579_Verify_Configurations_made_for_UC_seat_is_displayed_correctly_in_user_service_summary_screen********/
}
