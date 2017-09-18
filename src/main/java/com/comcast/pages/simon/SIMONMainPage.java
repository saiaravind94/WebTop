
package com.comcast.pages.simon;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.comcast.commons.WebtopPage;
import com.comcast.pages.simon.TNManagement.Locators;
import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.utils.ComparisonType;
import com.comcast.utils.TestSettings;
import com.comcast.utils.ValidationType;
import com.comcast.utils.DataTable;
import com.comcast.utils.IUserDetails;

/**
 * Represents default page of the application
 * 
 */
public class SIMONMainPage extends WebtopPage {
	FrameworkContext context;
	private IUserDetails userDetails;
	DataTable dataSet;
	private TestSettings settings;	
	Logger log = Logger.getLogger(SIMONMainPage.class);

	private boolean mstatus;

	public static class Locators {
		public static final String btnUpload = "btnUpload";
		public static final String btnchooseFile = "btnchooseFile";
		public static final String elmnuploadstatus = "elmnuploadstatus";
		public static final String elmttick = "elmttick";
		public static final String txtSearchbox = "txtSearchbox";
		public static final String btnSearch = "btnSearch";		
		public static final String navtnmgmt = "navtnmgmt";
		public static final String btnDashBoard = "btnDashBoard";
		public static final String txtsiteExistError = "txtsiteExistError";	
		public static final String txtUploadFailError = "txtUploadFailError";		
		public static final String txtContactNameError = "txtContactNameError";
		public static final String txtContactPhoneError = "txtContactPhoneError";
		public static final String txtContactEmailError = "txtContactEmailError";
		public static final String txtOppIDBlankError =   "txtOppIDBlankError";		
		public static String SFId = "";
		public static String txtSiteLockMsg = "";
		public static String txtSiteLockMsgClose = "";
		public static final String txtMultiplePMErrorMsg = "txtMultiplePMErrorMsg";

	}

	public SIMONMainPage(FrameworkContext context) {
		super(context, "SIMONMainPage");
		report = context.getReport();
		this.context = context;
		this.dataSet = context.getDataTable();
		this.userDetails = context.getUserDetail();
		this.settings = context.getSettings();	
		this.browser = context.getDriver();
	}

	@Override
	protected boolean isValidPage() {
		return true;
	}

	@Override
	protected void waitForPageLoad() {

	}

	public boolean Signout() {
		return mstatus;
	}

	public boolean simonUploadDataFile() {
		mstatus = true;
		int retry = 0;
		int maxTry = 3;
		try {			
			iClick(Locators.btnUpload, null, "Click on Upload button");
			do {
				browser.findElement(By.xpath("//input[@type='file']")).sendKeys(dataDump.getValue("dataFileName"));
				if (isLocatorVisible(Locators.elmnuploadstatus, 60)) {
					if (testLocatorClickable(Locators.elmnuploadstatus, 2).getText().equals("Upload Failed!")) {
						retry = retry + 1;
						log.warn("SOA upload in simon retry meachnisms triggered. retry count is: " + retry);
					} else {
						iClick(Locators.elmttick);
						sleep(2000);
						report.reportPassEvent("SOA Upload in SIMON", "SOA Upload in SIMON Passed");
						break;
					}
					if (retry == maxTry) {
						report.reportHardFailEvent("SOA Upload in SIMON",
								"SOA Upload in SIMON Failed after 3 attempts");
					}
				}
			} while ((retry >= 1));
		} catch (Exception Ex) {
			report.reportHardFailEvent("SOA Upload in SIMON", "SOA Upload in SIMON Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "SOA Upload in SIMON Failed!!! " + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public void verifySiteIsLocked()
	{
		String username = userDetails.getUserName("APP_" + settings.getEnvironmentToTest() + "_UNAME_SIMON");
		addLocator(Locators.txtSiteLockMsg, "xpath", "//div[.='Site is locked by "+username+"']", "Site lock message");
		addLocator(Locators.txtSiteLockMsgClose, "xpath", "//a[.='X']", "Site lock message close");
		if(isLocatorVisible(Locators.txtSiteLockMsg, 5))
		{
			report.reportPassEvent("Site Lock message verifcation", "Site Lock message verifcation passed");
		}
		else
		{
			report.reportSoftFailEvent("Site Lock message verifcation", "Site Lock message verifcation failed");
		}
		if(isLocatorVisible(Locators.txtSiteLockMsgClose, 1)){
			iClick(Locators.txtSiteLockMsgClose);
		}
		
	}
	
	public void SFOpportunityIDSearch() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.btnDashBoard, 2)) {
				iClick(Locators.btnDashBoard, Locators.txtSearchbox,
						"Click on Dash board button:TN Page:Dashboard button");
			}
			testLocatorClickable(Locators.txtSearchbox).click();
			iEnterText(Locators.txtSearchbox, dataDump.getValue("SFOpportunityId"), "Enter SFOpp");
			iClick(Locators.btnSearch);
			addLocator(Locators.SFId, "xpath", "//div[.='"+dataDump.getValue("SFOpportunityId")+"']", "Opprtunity Id");
			iClick(Locators.SFId, null, "Click on search result");
			waitForElement(testLocatorClickable(Locators.navtnmgmt));
			sleep(2000);
			report.reportDoneEvent("SFOpportunity ID Search", "SFOpportunity ID "+dataDump.getValue("SFOpportunityId")+" Search Done");
		} catch (Exception Ex) {
			report.reportHardFailEvent("Search SalesForce Opportunity ID",
					"Search SalesForce Opportunity ID Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Search SalesForce Opportunity ID Failed!!! " + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
	}

	public void verifyDuplicateSiteError() {
		mstatus = true;
		try {
			iClick(Locators.btnUpload, null, "Click on Upload button");
			browser.findElement(By.xpath("//input[@type='file']")).sendKeys(dataDump.getValue("dataFileName"));
			if (isLocatorVisible(Locators.txtsiteExistError, 15)) {
				iValidateLocatorValue(Locators.txtsiteExistError, "BODT Exception : Site already exists :",
						ComparisonType.EQUAL, "Verify the Error Message when File with Same name is uploaded");
			}

		} catch (Exception Ex) {
			report.reportHardFailEvent("Verify the Error Message when File with Same name is uploaded",
					"Verification of Error Message when File with Same name is uploaded-Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Verification of Error Message when File with Same name is uploaded-Failed!!! "
					+ Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
	}

	public boolean verifySiteExists() {
		mstatus = false;
		try {			
			if (isLocatorVisible(Locators.btnDashBoard, 2)) {
				iClick(Locators.btnDashBoard, Locators.txtSearchbox,
						"Click on Dash board button:TN Page:Dashboard button");
			}
			testLocatorClickable(Locators.txtSearchbox).click();
			iEnterText(Locators.txtSearchbox, dataDump.getValue("SFOpportunityId"), "Enter SFOpp");
			iClick(Locators.btnSearch);
			addLocator(Locators.SFId, "xpath", "//div[.='"+dataDump.getValue("SFOpportunityId")+"']", "Opprtunity Id");
			if (isLocatorVisible(Locators.SFId, 10)) {				
				testLocatorClickable(Locators.SFId);
				iClick(Locators.SFId, null, "Click on search result");
				waitForElement(testLocatorClickable(Locators.navtnmgmt));
				sleep(2000);
				log.info("Site Already Exists");
				return true;
			}

		} catch (Exception Ex) {
			report.reportHardFailEvent("Verify Site Exists", "Verify Site Exists Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Verify Site Exists Failed!!! " + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}
	public boolean verifyErrorMsgForCustomerContactDetails() {
		mstatus = false;
		try {			
			if (isLocatorVisible(Locators.txtUploadFailError, 10)) {				
				iValidateLocatorValue(Locators.txtContactNameError, "Customer Contact Name for a site cannot be blank", ComparisonType.SUBSTR, "Verify the Error Text is displayed if Contact Name is blank");
				iValidateLocatorValue(Locators.txtContactPhoneError, "Customer Contact Phone for a site cannot be blank", ComparisonType.SUBSTR, "Verify the Error Text is displayed if Contact TN is blank");
				iValidateLocatorValue(Locators.txtContactEmailError, "Customer Contact Email for a site cannot be blank",ComparisonType.SUBSTR, "Verify the Error Text is displayed if Contact Email is blank");			
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verify the Error Message displayed for Blank Contact Details", "The Error Message displayed for Blank Contact Details-Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "The Error Message displayed for Blank Contact Details-Failed!!! " + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}
	
	public boolean verifyErrorMsgForMultiplePMs() {
		mstatus = false;
		try {			
			if (isLocatorVisible(Locators.txtUploadFailError, 10)) {				
				iValidateLocatorValue(Locators.txtMultiplePMErrorMsg, "Site should not have more than 1 primary manager.", ComparisonType.SUBSTR, "Verify the Error Text when the site is having more than one PM");
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verify the Error Message displayed when the site is having more than one PM", "Verify the Error Message displayed when the site is having more than one PM is Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Verify the Error Message displayed when the site is having more than one PM is Failed!!! " + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}
	
	public boolean verifyErrorMsgIfOnePMInASite() {
		mstatus = false;
		try {			
			if (isLocatorVisible(Locators.txtUploadFailError, 10)) {				
				iValidateLocatorProperty(Locators.txtMultiplePMErrorMsg, ValidationType.NOTVISIBLE,
						"PM Validation Error message is not displayed as expected");
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("PM Validation Error message is not displayed as expected", "PM Validation Error message is displayed and it is NOT expected" + Ex.getMessage());
			eMsg = report.getMessage() + "PM Validation Error message is displayed and it is NOT expected!!! " + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}
	
	public boolean verifyUploadFailForBlankOpportunityID(String ErrorMessage) {
		mstatus = true;
		
		try {			
			iClick(Locators.btnUpload, null, "Click on Upload button");
			browser.findElement(By.xpath("//input[@type='file']")).sendKeys(dataDump.getValue("dataFileName"));
			if (isLocatorVisible(Locators.txtOppIDBlankError, 30)) {
				iValidateLocatorValue(Locators.txtOppIDBlankError, ErrorMessage, ComparisonType.SUBSTR, "Verify the Error Text is displayed if SF Opp ID is blank");
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verify the Error Message displayed when SOA with blank SF Opportunity ID is uploaded in SIMON", "The Error Message displayed for Blank Contact Details-Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Verification of Error Message when SOA with Blank Opportunity ID is uploaded in SIMON" + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	
}
