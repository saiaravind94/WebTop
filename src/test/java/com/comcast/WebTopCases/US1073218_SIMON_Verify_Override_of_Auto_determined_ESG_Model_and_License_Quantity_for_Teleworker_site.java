package com.comcast.WebTopCases;

import org.testng.annotations.Test;

import com.comcast.pages.simon.SIMONMainPage;
import com.comcast.pages.simon.ServiceInformation;
import com.comcast.pages.simon.SimonLogInPage;
import com.comcast.pages.webtop.AccountProfileTab;
import com.comcast.pages.webtop.WebTopLogInPage;
import com.comcast.pages.webtop.WebTopMainPage;

public class US1073218_SIMON_Verify_Override_of_Auto_determined_ESG_Model_and_License_Quantity_for_Teleworker_site extends CommonE2EFlow {
	
	@Test(priority = 8600, groups = { "New Connect", "All" })
	public void updateESGModelAndESGLicQtyAndLines() throws Exception {

		try {
			SIMONMainPage simonMainPage = new SIMONMainPage(frameworkContext);
			ServiceInformation serviceInformation = new ServiceInformation(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				simonMainPage.SFOpportunityIDSearch();
			}
			serviceInformation.verifyESGModelAndESGLicQtyAndLinesAreDisabled();
			serviceInformation.updateESGModelAndESGLicQtyAndLines("10", "EdgeMarc 4550", "10");
		} catch (Exception Ex) {
			report.reportHardFailEvent("Updating ESGModel,ESGLicQty,Lines fields",
					"Updating ESGModel,ESGLicQty,Lines fields" + Ex.getMessage());
			eMsg = report.getMessage() + "Service Information in SIMON Failed!!! " + Ex.getMessage();
			log.error(eMsg);			
		}
	}
	
	@Test(priority = 28600, groups = { "New Connect", "All" })
	public void verifyDeviceLicenseAndQuantity_Webtop(){
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		AccountProfileTab accountProfileTab = new AccountProfileTab(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
			}
			webTopMainPage.searchOrder("Group ID");
			accountProfileTab.verifyEdgeDevice("EdgeMarc 4550", "15");
			accountProfileTab.verifyLineQuantity("10");		
	}catch (Exception Ex) {
		report.reportHardFailEvent("verifying Update for ESGModel,ESGLicQty,Lines fields in Webtop",
				"Updating ESGModel,ESGLicQty,Lines fields failed" + Ex.getMessage());
		eMsg = report.getMessage() + "Service Information in SIMON Failed!!! " + Ex.getMessage();
		log.error(eMsg);			
	}
	}
}


