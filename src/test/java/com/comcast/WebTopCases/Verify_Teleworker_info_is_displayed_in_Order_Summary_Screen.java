package com.comcast.WebTopCases;

import org.testng.annotations.Test;

import com.comcast.commons.WebtopPage;
import com.comcast.pages.simon.OrderSummaryPage;
import com.comcast.pages.simon.SIMONMainPage;
import com.comcast.pages.simon.SimonLogInPage;

public class Verify_Teleworker_info_is_displayed_in_Order_Summary_Screen extends CommonE2EFlow {

	@Test(priority = 7300, groups = { "New Connect", "All" })
	public void verifyESGLicenseQuantityInOSPageAndPDFFile() {
		try {			
			SIMONMainPage SimonMainPage = new SIMONMainPage(frameworkContext);
			OrderSummaryPage orderSummaryPage = new OrderSummaryPage(frameworkContext);
			WebtopPage webtopPage = new WebtopPage(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				SimonMainPage.SFOpportunityIDSearch();
			}			
			orderSummaryPage.launchOrderSumaryType("LNP");
			orderSummaryPage.verifyESGLicenceQuantity("None");
			orderSummaryPage.downloadPDF();			
			String pdfText = webtopPage.getPDFContent(dataDump.getValue("OrderSummaryPDF"));
			if (pdfText.contains("##Call Capacity (ESG License Quantity) ILD##None##")) {
				report.reportPassEvent("ESG License quantity verification in PDF File",
						"ESG License quantity verification passed in in PDF File");
			} else {
				report.reportSoftFailEvent("ESG License quantity scenario verification in PDF File",
						"ESG License quantity scenario verification in PDF File failed in in PDF File");
			}
			orderSummaryPage.closeOrderSummary();
			sleep(4000);
		} catch (Exception Ex) {
			report.reportHardFailEvent("ESG License quantity Verification for Teleworker Site",
					"ESG License quantity Verification for Teleworker Site failed" + Ex.getMessage());
			eMsg = report.getMessage() + "ESG License quantity Verification for Teleworker Site failed" + Ex.getMessage();
			log.error(eMsg);
		}
	}
	
	@Test(priority = 7350, groups = { "New Connect", "All" })
	public void verifySiteTypeInOSPageAndPDFFile() {
		try {			
			SIMONMainPage SimonMainPage = new SIMONMainPage(frameworkContext);
			OrderSummaryPage orderSummaryPage = new OrderSummaryPage(frameworkContext);
			WebtopPage webtopPage = new WebtopPage(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				SimonMainPage.SFOpportunityIDSearch();
			}			
			orderSummaryPage.launchOrderSumaryType("Detailed Order Summary");
			orderSummaryPage.verifySiteType("teleworker");
			orderSummaryPage.downloadPDF();			
			String pdfText = webtopPage.getPDFContent(dataDump.getValue("OrderSummaryPDF"));			
			if (pdfText.contains("##Address 1 Address 2 Site Type##12 ADAMS LN N/A teleworker##")) {
				report.reportPassEvent("Site Type verification in PDF File",
						"Site Type verification passed in in PDF File");
			} else {
				report.reportSoftFailEvent("Site Type verification in PDF File",
						"Site Type verification in PDF File failed in in PDF File");
			}
			orderSummaryPage.closeOrderSummary();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Site Type Verification for Teleworker Site",
					"Site Type for Teleworker Site failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Site Type Verification for Teleworker Site failed" + Ex.getMessage();
			log.error(eMsg);
		}
	}

	

}
