package com.comcast.WebTopCases;

import org.testng.annotations.Test;

import com.comcast.pages.webtop.AccountProfileTab;
import com.comcast.pages.webtop.WebTopLogInPage;
import com.comcast.pages.webtop.WebTopMainPage;

public class TC02_Product_Catalog_update_for_Polycom_VVX_311_device_TW_Site_with_3yr_term extends CommonE2EFlow{	
	
	@Test(priority = 2600, groups={"New Connect", "All"})	
	public void verifyProductCatalog() throws Exception {		
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		AccountProfileTab accountProfileTab = new AccountProfileTab(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				if (dataDump.getValue("GroupID").isEmpty()) {
					webTopMainPage.searchFor("Salesforce Opportunity ID(FID)");
				} else {
					webTopMainPage.searchFor("Group ID");
				}
			}
			accountProfileTab.verifyProductCatalogAmount("0.00");
		} catch (Exception Ex) {
			log.error("Verification of product catalog in account profile page Failed");
			report.reportHardFailEvent("Verification of product catalog amount value  in account profile page",
					"Verification of product catalog amount value in account profile page not displayed as expected");
			}
	}
	
	
	

}
