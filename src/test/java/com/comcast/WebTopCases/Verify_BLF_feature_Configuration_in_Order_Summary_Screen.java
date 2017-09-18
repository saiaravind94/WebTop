package com.comcast.WebTopCases;

import org.testng.annotations.Test;

import com.comcast.commons.WebtopPage;
import com.comcast.pages.simon.Create_New_MACD;
import com.comcast.pages.simon.MACDConfigurationPage;
import com.comcast.pages.simon.OrderSummaryPage;
import com.comcast.pages.simon.Receptionist;
import com.comcast.pages.simon.SIMONMainPage;
import com.comcast.pages.simon.SeatConfiguration;
import com.comcast.pages.simon.Settings;
import com.comcast.pages.simon.SharedCallAppearance;
import com.comcast.pages.simon.SimonLogInPage;

public class Verify_BLF_feature_Configuration_in_Order_Summary_Screen extends CommonE2EFlow {
	@Test(priority = 10050, groups = { "New Connect", "All" })
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

	@Test(priority = 10060, groups = { "New Connect", "All" })
	public void configureSharedCallApp() {
		try {
			SIMONMainPage SimonMainPage = new SIMONMainPage(frameworkContext);
			SharedCallAppearance SCA = new SharedCallAppearance(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				SimonMainPage.SFOpportunityIDSearch();
			}
			SCA.configureSharedCallAppearance("UCSeat003", "BLF");
		} catch (Exception ex) {
			report.reportHardFailEvent("Configure Shared Call Appearance in SIMON",
					"Configure Shared Call Appearance in SIMON Failed" + ex.getMessage());
			eMsg = "Configure Shared Call Appearance in SIMON Failed!!! " + ex.getMessage();
			log.error(eMsg);
		}
	}

	@Test(priority = 10070, groups = { "New Connect", "All" })
	public void verifySCAFieldsInOrderSummary() {
		try {
			SIMONMainPage SimonMainPage = new SIMONMainPage(frameworkContext);
			OrderSummaryPage OrderSummaryPage = new OrderSummaryPage(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				SimonMainPage.SFOpportunityIDSearch();
			}

			OrderSummaryPage.launchOrderSumaryType("Detailed Order Summary");
			OrderSummaryPage.verifySCAInOrderSummary("UCSeat001", "Polycom VVX 311 HD", "0", "1", "UCSeat003", "BLF");

		} catch (Exception ex) {
			report.reportHardFailEvent("Configure Receptionist in SIMON",
					"Configure Receptionist in SIMON Failed" + ex.getMessage());
			eMsg = "Configure Receptionist in SIMON Failed!!! " + ex.getMessage();
			log.error(eMsg);
		}
	}

}
