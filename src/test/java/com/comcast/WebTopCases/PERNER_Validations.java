package com.comcast.WebTopCases;

import org.testng.annotations.Test;

import com.comcast.pages.webtop.ExtendedMiscInfo;
import com.comcast.pages.webtop.WebTopLogInPage;
import com.comcast.pages.webtop.WebTopMainPage;

public class PERNER_Validations extends CommonE2EFlow{	
	
	@Test(priority = 5050, groups={"New Connect", "All"})
	public void verifyBCPFlagIsSetToYes() throws Exception {		
		try {
			WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
			ExtendedMiscInfo ExtendedMiscInfo = new ExtendedMiscInfo(frameworkContext);
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchOrder("Salesforce Opportunity ID(FID)");
			}
			String[] fieldList = {"BCP Enabled"};
			String[] expectdValues = {"Yes"};
			String[] attributeToCheck = {"value"};
			ExtendedMiscInfo.validateFieldValue(fieldList, expectdValues, attributeToCheck);			
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verify BCP Flag is set to Yes", "Verify BCP Flag is set to Yes is Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Verify BCP Flag is set to Yes is Failed!!! " + Ex.getMessage();			
			log.error(eMsg);
		}
	}
	
	@Test(priority = 5050, groups={"New Connect", "All"})
	public void verifyBCPFlagIsSetToNo() throws Exception {		
		try {
			WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
			ExtendedMiscInfo ExtendedMiscInfo = new ExtendedMiscInfo(frameworkContext);
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchOrder("Salesforce Opportunity ID(FID)");
			}
			String[] fieldList = {"BCP Enabled"};
			String[] expectdValues = {"No"};
			String[] attributeToCheck = {"value"};
			ExtendedMiscInfo.validateFieldValue(fieldList, expectdValues, attributeToCheck);			
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verify BCP Flag is set to No", "Verify BCP Flag is set to No is Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Verify BCP Flag is set to No is Failed!!! " + Ex.getMessage();			
			log.error(eMsg);
		}
	}
	
	
	

}
