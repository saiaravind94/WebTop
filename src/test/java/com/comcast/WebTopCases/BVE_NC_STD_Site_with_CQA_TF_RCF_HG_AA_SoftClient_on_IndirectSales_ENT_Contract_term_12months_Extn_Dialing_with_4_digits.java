package com.comcast.WebTopCases;

import org.testng.annotations.Test;

import com.comcast.pages.simon.*;
import com.comcast.pages.simon.SIMONMainPage;
import com.comcast.pages.simon.SimonLogInPage;

public class BVE_NC_STD_Site_with_CQA_TF_RCF_HG_AA_SoftClient_on_IndirectSales_ENT_Contract_term_12months_Extn_Dialing_with_4_digits
		extends MACDFlow {

	@Test(priority = 9600, groups={"New Connect", "All"})
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

	@Test(priority = 41650, groups={"New Connect", "All"})
	public void tollFreeTNConfiguration_MSFlow() throws Exception {
		tollFreeTNConfiguration();
		
	}
}
