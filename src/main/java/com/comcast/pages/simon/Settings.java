package com.comcast.pages.simon;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import com.comcast.commons.WebtopPage;
import com.comcast.reporting.ReportPath;
import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.utils.ComparisonType;
import com.comcast.utils.TestSettings;
import com.comcast.utils.ValidationType;
import com.comcast.utils.CsvFileReader;
import com.comcast.utils.DataTable;
import com.comcast.utils.IUserDetails;

/**
 * Represents default page of the application
 * 
 */
public class Settings extends WebtopPage {
	FrameworkContext context;
	private IUserDetails userDetails;
	DataTable dataSet;
	private TestSettings settings;

	Logger log = Logger.getLogger(Settings.class);

	private boolean mstatus;

	public static class Locators {
		public static final String lnkSettings = "lnkSettings";
		public static final String lnkGenerateWebTopFile = "lnkGenerateWebTopFile";
		public static final String btnDownloadWebtop = "btnDownloadWebtop";
		public static final String lnkCreateMACDforThisSite = "lnkCreateMACDforThisSite";
		public static final String lnkDeleteSite = "lnkDeleteSite";
		public static final String btnDelete = "btnDelete";
		public static final String elemDeleteSucessful = "elemDeleteSucessful";
		public static final String lnkGenerateNeteXFile = "lnkGenerateNeteXFile";
		public static final String btndownloadNetexFile = "btndownloadNetexFile";
		public static final String lnkValidateEMailAddresses = "lnkValidateEMailAddresses";
		public static final String txtEMailValidationMsg = "txtEMailValidationMsg";
		public static final String lnkValidatePrimaryManager = "lnkValidatePrimaryManager";
		public static final String txtValidatePrimaryManager = "txtValidatePrimaryManager";
		public static final String btnBannerMsgClose = "btnBannerMsgClose";
	}

	public Settings(FrameworkContext context) {
		super(context, "Settings");
		report = context.getReport();
		this.context = context;
		this.dataSet = context.getDataTable();
		this.userDetails = context.getUserDetail();
		this.settings = context.getSettings();
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

	public boolean generateWebTopFileToDownload() {
		mstatus = true;
		try {
			generateWebTopFile();
			iClick(Locators.btnDownloadWebtop, null, "Click on Download Webtop File");
			sleep(8000);
			unZipIt(System.getProperty("user.home") + "\\Downloads\\" + dataDump.getValue("FolderName_RT") + ".zip",
					System.getProperty("user.home") + "\\Downloads\\" + dataDump.getValue("FolderName_RT"));
			if (dataDump.getValue("SFOpportunityId_Site1").isEmpty())
				dataDump.setValue("UnzippedFileName_RT",
						dataDump.getValue("SFOpportunityId") + "-" + dataDump.getValue("FolderName_RT") + ".import");
			else
				dataDump.setValue("UnzippedFileName_RT", dataDump.getValue("SFOpportunityId_Site1") + "-"
						+ dataDump.getValue("FolderName_RT") + ".import");

			log.info("Unzipped File Name is: " + dataDump.getValue("UnzippedFileName_RT"));
			dataDump.setValue("WebTopImportFilePath_RT", System.getProperty("user.home") + "\\Downloads\\"
					+ dataDump.getValue("FolderName_RT") + "\\" + dataDump.getValue("UnzippedFileName_RT"));
			log.info("Webtop Import File Path is: " + dataDump.getValue("WebTopImportFilePath_RT"));
			report.reportPassEvent("Generate Webtop File", "Generated Webtop File sucessfully");
		} catch (Exception Ex) {
			report.reportHardFailEvent("Generate Webtop File and Unzip",
					"Generate Webtop File and Unzipping Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Generate Webtop File and Unzipping in SIMON Failed!!! " + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public boolean createMACDForThisSite() {
		mstatus = true;
		try {
			testLocatorClickable(Locators.lnkSettings);
			if (waitForElement(testLocatorClickable(Locators.lnkSettings), 10)) {
				iClick(Locators.lnkSettings, null, "Click on Settings");
			}
			iClick(Locators.lnkCreateMACDforThisSite, null, "Click on Create MACD For the site");
			report.reportPassEvent("Create MACD For the site", "Create MACD For the site has been selected");
		} catch (Exception Ex) {
			report.reportHardFailEvent("Create MACD For the site",
					"Create MACD For the site not selected-Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Selecting Create MACD For the site Failed" + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public boolean deleteSite() {
		mstatus = true;
		try {
			testLocatorClickable(Locators.lnkSettings);
			if (waitForElement(testLocatorClickable(Locators.lnkSettings), 10)) {
				iClick(Locators.lnkSettings, null, "Click on Settings");
			}
			iClick(Locators.lnkDeleteSite, null, "Click on Delete Site Link For the site");
			iClick(Locators.btnDelete);
			waitForSpinnerComplete();
			report.reportPassEvent("Delete Site", "Site has been deleted");
		} catch (Exception Ex) {
			report.reportHardFailEvent("Delete site", "Deleting site Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Delete site Failed" + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public boolean generateWebTopFile() {
		mstatus = true;
		try {
			waitForSpinnerComplete();
			testLocatorClickable(Locators.lnkSettings);
			if (waitForElement(testLocatorClickable(Locators.lnkSettings), 10)) {
				iClick(Locators.lnkSettings, null, "Click on Settings");
			}
			iClick(Locators.lnkGenerateWebTopFile, null, "Click on Generate webtop File");
		} catch (Exception Ex) {
			report.reportHardFailEvent("Clicking on Generate Webtop File",
					"Clicking on Generate Webtop File failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Clicking on Generate Webtop File failed" + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public boolean generateNeteXFile() {
		mstatus = true;
		try {
			waitForSpinnerComplete();
			testLocatorClickable(Locators.lnkSettings);
			if (waitForElement(testLocatorClickable(Locators.lnkSettings), 10)) {
				iClick(Locators.lnkSettings, null, "Click on Settings");
			}
			iClick(Locators.lnkGenerateNeteXFile, null, "Click on Generate NexteX File");		
			waitForSpinnerComplete();
			iClick(Locators.btndownloadNetexFile, null, "Click on Download Netex File");
			sleep(5000);
			String latestNetXFile = getLatestFile(System.getProperty("user.home") + "\\Downloads\\",
					"CPE_Device_List_" + dataDump.getValue("SFOpportunityId") + "_.*.csv");
			String reportPath = ReportPath.getInstance().getReportPath();
			FileUtils.copyFile(new File(latestNetXFile), new File(reportPath + "\\" + context.getTestCaseName() + "_" + new File(latestNetXFile).getName()));
			log.info("NetXUSA File path: " + latestNetXFile);
			dataDump.setValue("NetXUSAFile", latestNetXFile);
		} catch (Exception Ex) {
			report.reportHardFailEvent("Clicking on Generate NetXUSA File",
					"Clicking on Generate NetXUSA File failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Clicking on Generate NetXUSA File failed" + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}
	
	public boolean validateNetxUSAFileContent(String[] seatName, String[] make) {
		mstatus = true;
		boolean result = false;
		report.getReportSettings().takeScreenshotPassedStep=false;
		report.getReportSettings().takeScreenshotFailedStep=false;
		try {			
			HashMap<String, Map<String, String>> csvMap = new HashMap<String, Map<String, String>>();
			CsvFileReader.readCsvFileToMap(dataDump.getValue("NetXUSAFile"), "Label", csvMap);
			for (int i = 0; i < seatName.length; i++) {
				for (Entry<String, Map<String, String>> entry : csvMap.entrySet()) {
					result = false;
					if (entry.getKey().contains(seatName[i]) && entry.getValue().containsValue(make[i])) {
						log.info(seatName[i] + ": is displayed with Make in NetXUSA File: " + make[i] + " as expected");
						result = true;
						break;
					}
				}				
				iReport("", "Verification of Seat with device Make in NetXUSA File",
						result ? seatName[i] + ": is displayed with Make: " + make[i] + " as expected."
								: seatName[i] + ": is NOT displayed with Make: " + make[i] + " as expected.",
						result);
				report.getReportSettings().takeScreenshotFailedStep=true;
				report.getReportSettings().takeScreenshotPassedStep=true;
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Validation of NetxUSA File Device and Make",
					"Validation of NetxUSA File Device and Make failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Validation of NetxUSA File Device and Make failed" + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		report.getReportSettings().takeScreenshotPassedStep=true;
		return mstatus;
	}
	
	public boolean validateEmailAddresses(String expectedMsg) {
		mstatus = true;
		try {
			waitForSpinnerComplete();
			testLocatorClickable(Locators.lnkSettings);
			if (waitForElement(testLocatorClickable(Locators.lnkSettings), 10)) {
				iClick(Locators.lnkSettings, null, "Click on Settings");
			}			
			iClick(Locators.lnkValidateEMailAddresses, null, "Click on Validate EMail Addresses link");		
			waitForSpinnerComplete();
			if(isLocatorVisible(Locators.txtEMailValidationMsg, 60)){
				iValidateLocatorValue(Locators.txtEMailValidationMsg, expectedMsg, ComparisonType.EQUAL, "Validate the EMail addresses validation Fail Message");
				log.info("EMail Address Validation is Passed as Expected");
				if(isLocatorVisible(Locators.btnBannerMsgClose, 1)){
					iClick(Locators.btnBannerMsgClose);
				}
			}
			else
			{
				report.reportSoftFailEvent("Validate Message for Email Addresses", "EMail Address Validation is NOT Passed as Expected. Actual: " + iGetText(Locators.txtEMailValidationMsg) + ". Expected: " + expectedMsg);
				log.error("EMail Address Validation is NOT Passed as Expected. Actual: " + iGetText(Locators.txtEMailValidationMsg) + ". Expected: " + expectedMsg);
				if(isLocatorVisible(Locators.btnBannerMsgClose, 1)){
					iClick(Locators.btnBannerMsgClose);
				}
			}
			if (waitForElement(testLocatorClickable(Locators.lnkSettings), 10)) {
				iClick(Locators.lnkSettings, null, "Click on Settings");
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Validate Message for Email Addresses",
					"Validate Message for Email Addresses is failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Validate Message for Email Addresses is failed" + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public boolean validatePrimaryManager(String expectedMsg) {
		mstatus = true;
		try {
			waitForSpinnerComplete();
			testLocatorClickable(Locators.lnkSettings);
			if (waitForElement(testLocatorClickable(Locators.lnkSettings), 10)) {
				iClick(Locators.lnkSettings, null, "Click on Settings");
			}			
			iClick(Locators.lnkValidatePrimaryManager, null, "Click on Validate Primary Manager link");		
			waitForSpinnerComplete();
			if(isLocatorVisible(Locators.txtValidatePrimaryManager, 60)){
				iValidateLocatorValue(Locators.txtValidatePrimaryManager, expectedMsg, ComparisonType.EQUAL, "Validate the Primary Manager validation Fail/Pass Message");
				log.info("Primary Manager Validation is Passed as Expected");
				if(isLocatorVisible(Locators.btnBannerMsgClose, 1)){
					iClick(Locators.btnBannerMsgClose);
				}
			}
			else
			{
				report.reportSoftFailEvent("Validate Message for Primary Manager", "Primary Manager Validation is NOT Passed as Expected. Actual: " + iGetText(Locators.txtValidatePrimaryManager) + ". Expected: " + expectedMsg);
				log.error("Primary Manager Validation is NOT Passed as Expected. Actual: " + iGetText(Locators.txtValidatePrimaryManager) + ". Expected: " + expectedMsg);
				if(isLocatorVisible(Locators.btnBannerMsgClose, 1)){
					iClick(Locators.btnBannerMsgClose);
				}
			}
			if (waitForElement(testLocatorClickable(Locators.lnkSettings), 10)) {
				iClick(Locators.lnkSettings, null, "Click on Settings");
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Validate Message for Primary Manager",
					"Validate Message for Primary Manager is failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Validate Message for Primary Manager is failed" + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	
}
