package com.comcast.WebTopCases;

import org.testng.annotations.Test;

import com.comcast.pages.simon.SIMONMainPage;
import com.comcast.pages.simon.SeatConfiguration;
import com.comcast.pages.simon.SimonLogInPage;

public class BVE_NC_STD_Site_STD_UC_seats_Extension_VOIP_validate_line_portID_added_BLF_feature_for_All_Ucseat_Users_in_Switch_Contract_term_36months extends MACDFlow{
	
	@Test(priority = 10100, groups={"New Connect", "All"})
	public void enableBLFFeature() throws Exception {
		
		try {
			SIMONMainPage simonMainPage = new SIMONMainPage(frameworkContext);
			SeatConfiguration seatConfiguration = new SeatConfiguration(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				simonMainPage.SFOpportunityIDSearch();
			}
			seatConfiguration.enableBLFFeature();			
		} catch (Exception Ex) {
			report.reportHardFailEvent("Enable BLF Feature for UC Seat", "Enable BLF Feature for UC Seat Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Enable BLF Feature for UC Seat in SIMON Failed!!! " + Ex.getMessage();			
			log.error(eMsg);
		}
	}
	
	

}
