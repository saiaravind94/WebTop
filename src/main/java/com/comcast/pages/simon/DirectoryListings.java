package com.comcast.pages.simon;

import org.apache.log4j.Logger;
import com.comcast.commons.WebtopPage;
import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.utils.TestSettings;
import com.comcast.utils.DataTable;
import com.comcast.utils.IUserDetails;

/**
 * Represents default page of the application
 * 
 */
public class DirectoryListings extends WebtopPage {
	FrameworkContext context;
	private IUserDetails userDetails;
	DataTable dataSet;
	private TestSettings settings;

	Logger log = Logger.getLogger(DirectoryListings.class);

	private boolean mstatus;

	public static class Locators {
		public static final String lnkDirectory = "lnkDirectory";
		public static final String lnkDirectory_TABTN = "lnkDirectory_TABTN";
		public static final String txtDirectoryDisplayName = "txtDirectoryDisplayName";
		public static final String drpPublishingOptions = "drpPublishingOptions";
		public static final String txtPlaceListingAs = "txtPlaceListingAs";
		public static final String btnMainListingTn = "btnMainListingTn";
		public static final String txtheaderText = "txtheaderText";
		public static final String txtheaderCode = "txtheaderCode";
		public static final String txtheaderSICCode = "txtheaderSICCode";
		public static final String btnSaveGeneral = "btnSaveGeneral";
		public static final String elmtDirectoryListingSaveSuccessful = "elmtDirectoryListingSaveSuccessful";
	}

	public DirectoryListings(FrameworkContext context) {
		super(context, "DirectoryListings");
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

	public boolean directoryListing() {
		mstatus = true;
		try {
			scrollUp();
			if (waitForElement(testLocatorClickable(Locators.lnkDirectory), 5)) {
				iClick(Locators.lnkDirectory, null, "Click on Directory");
			}
			sleep(3000);
			iClick(Locators.lnkDirectory_TABTN);
			iEnterText(Locators.txtDirectoryDisplayName, dataSet.getValue("DirectoryDisplayName"),
					"Enter Directory Display Name");
			iClick(Locators.drpPublishingOptions, null, "Click on TABTN");
			selectvalue_dropdown("div", "Listed", false);
			iEnterText(Locators.txtPlaceListingAs, dataSet.getValue("PlaceListingAs"), "Enter Place Listing As");
			iClick(Locators.btnMainListingTn, null, "Click on Main Listing");
			Scroll_Page_Down();
			iEnterText(Locators.txtheaderText, dataSet.getValue("HeaderText"), "Enter Header Text");
			iEnterText(Locators.txtheaderCode, dataSet.getValue("HeaderCode"), "Enter Header Code");
			iEnterText(Locators.txtheaderSICCode, dataSet.getValue("SICCode"), "Enter SIC Code");
			iClick(Locators.btnSaveGeneral, null, "Click on Save");
			log.info("Current URL is: " + getCurrentURL());
			String currentURL = getCurrentURL();
			testLocatorVisible(Locators.elmtDirectoryListingSaveSuccessful);
			String[] temp = currentURL.split("/");
			dataDump.setValue("FolderName_RT", temp[4].trim());
			log.info("SIMON Page Folder Name: " + dataDump.getValue("FolderName_RT"));
			report.reportPassEvent("Directory Listing Information", "Directory Listing Information Entered");
		} catch (Exception Ex) {
			report.reportHardFailEvent("Directory Listing Information",
					"Directory Listing Information Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Directory Listing Information in SIMON Failed!!! " + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

}
