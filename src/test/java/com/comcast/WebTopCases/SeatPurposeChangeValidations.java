package com.comcast.WebTopCases;

import org.testng.annotations.Test;

import com.comcast.pages.simon.OrderInformation;
import com.comcast.pages.simon.SIMONMainPage;
import com.comcast.pages.simon.SeatConfiguration;
import com.comcast.pages.simon.SimonLogInPage;

public class SeatPurposeChangeValidations extends CommonE2EFlow {	
	@Test(priority = 8100, groups={"New Connect", "All"})
	public void verifySeatPurposeChangeFromENTAdmintoTechAdmin() throws Exception {
		try {
			SIMONMainPage simonMainPage = new SIMONMainPage(frameworkContext);
			SeatConfiguration seatConfiguration=new SeatConfiguration(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				simonMainPage.SFOpportunityIDSearch();
			}
			seatConfiguration.verifySeatPurposeChange("Enterprise Admin User", "Tech Admin User");	
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verify Seat Purpose updated is saved ", "Verification of Seat Purpose update from Enterprise Admin User to Tech Admin user Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Seat Purpose updated Failed" + Ex.getMessage();
			log.error(eMsg);
		}
	}
	@Test(priority = 8150, groups={"New Connect", "All"})
	public void verifySeatPurposeChangeFromTechAdmintoEntAdmin() throws Exception {
		try {
			SIMONMainPage simonMainPage = new SIMONMainPage(frameworkContext);
			SeatConfiguration seatConfiguration=new SeatConfiguration(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				simonMainPage.SFOpportunityIDSearch();
			}
			seatConfiguration.verifySeatPurposeChange("Tech Admin User", "Enterprise Admin User");	
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verify Seat Purpose updated is saved ", "Verification of Seat Purpose update from Tech Admin User to Enterprise Admin user Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Seat Purpose update from Tech Admin to Enterprise Admin Failed" + Ex.getMessage();
			log.error(eMsg);
		}
	}
}
