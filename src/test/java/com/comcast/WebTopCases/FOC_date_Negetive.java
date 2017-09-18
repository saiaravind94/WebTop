package com.comcast.WebTopCases;

import org.testng.annotations.Test;

import com.comcast.pages.webtop.EnterFOCDateForPortedTNs;
import com.comcast.pages.webtop.ErrorValidatingFOCDateEntered;
import com.comcast.pages.webtop.WebTopLogInPage;
import com.comcast.pages.webtop.WebTopMainPage;
import com.comcast.reporting.Status;

public class FOC_date_Negetive extends CommonE2EFlow {
	
	
	@Test(priority = 15150, groups={"New Connect", "All"})
	public void enterFOCDateForPortedTNsTrayValidations() throws Exception {

		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		EnterFOCDateForPortedTNs enterFOCDateForPortedTNs = new EnterFOCDateForPortedTNs(frameworkContext);
		ErrorValidatingFOCDateEntered errorValidatingFOCDateEntered=new ErrorValidatingFOCDateEntered(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}
			enterFOCDateForPortedTNs.enterInvalidFocDate("10/27/016");
			errorValidatingFOCDateEntered.errorValidatingFOCDateEntered();
			enterFOCDateForPortedTNs.enterInvalidFocDate("20/12/2016");
			errorValidatingFOCDateEntered.errorValidatingFOCDateEntered();
			enterFOCDateForPortedTNs.enterFOCDateForPortedTNs();
		} catch (Exception Ex) {

			report.reportHardFailEvent("Enter FOC Date for Ported TNs", "Enter FOC Date for Ported TNs Failed!!!",
					Status.FAIL);
			String eMsg = "Error in Enter FOC Date for Ported TNs --- " + Ex.getMessage();
			log.error(eMsg);
		}
	}

}
