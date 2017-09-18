package com.comcast.pages.simon;

import java.util.Random;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriverException;

import com.comcast.commons.WebtopPage;
import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.utils.DataTable;

/**
 * Represents default page of the application
 * 
 */
public class TNManagement extends WebtopPage {
	FrameworkContext context;
	DataTable dataSet;
	Logger log = Logger.getLogger(TNManagement.class);

	private boolean mstatus;

	public static class Locators {
		public static final String navtnmgmt = "navtnmgmt";
		public static final String lnktnmgmt_TABTN = "lnktnmgmt_TABTN";
		public static final String btnDirectoryLising = "btnDirectoryLising";
		public static final String btnSavelink = "btnSavelink";
		public static final String elmtTNinventorysuccess = "elmtTNinventorysuccess";
		public static final String drpdownPortType = "drpdownPortType";
		public static final String txtTNNumber = "txtTNNumber";
		public static final String txtExistingServices = "txtExistingServices";
		public static final String drpdownState = "drpdownState";
		public static final String txtNPA = "txtNPA";
		public static final String txtNXX = "txtNXX";
		public static final String txtRatecenter = "txtRatecenter";
		public static final String tollFreeTN = "tollFreeTN";
		public static final String drpdwnAreaOfService = "drpdwnAreaOfService";
		public static final String btnDashBoard = "btnDashBoard";
		public static final String txtSearchbox = "txtSearchbox";
		public static final String toggleBtnCRCP = "toggleBtnCRCP";
		public static final String btnAddTN = "btnAddTN";
		public static final String dropDownTNType = "dropDownTNType";
		public static final String dropDownPortType = "dropDownPortType";
		public static final String btnAdd = "btnAdd";
		public static final String UnassignedSeat = "UnassignedSeat";
		public static final String dropDownSeat = "dropDownSeat";
		public static String addedTN = "";

	}

	public TNManagement(FrameworkContext context) {
		super(context, "TNManagement");
		report = context.getReport();
		this.context = context;
		this.dataSet = context.getDataTable();
		context.getUserDetail();
		context.getSettings();
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

	public boolean TN_Management() {
		mstatus = true;
		try {
			scrollUp();
			if (waitForElement(testLocatorClickable(Locators.navtnmgmt), 20)) {
				iClick(Locators.navtnmgmt, null, "Click on TN Management");
			}
			jsClick(testLocatorClickable(Locators.lnktnmgmt_TABTN));
			iClick(Locators.btnDirectoryLising, null, "Click on Directory Listing ");
			iClick(Locators.btnSavelink, null, "Click on Save");
			isLocatorVisible(Locators.elmtTNinventorysuccess, 50);
			// testLocatorVisible(Locators.elmtTNinventorysuccess);
			sleep(3000);
			jsClick(testLocatorClickable(Locators.lnktnmgmt_TABTN));
			sleep(2000);
			report.reportPassEvent("TN Management Information", "TN Management Information Entered");
		} catch (Exception Ex) {
			report.reportHardFailEvent("TN Management Information",
					"TN Management Information Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "TN Management Information in SIMON Failed!!! " + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public boolean portMigration() {
		mstatus = true;
		try {
			String[] portTypeList = dataSet.getValue("PortType").split("\\|");
			String[] SeatNameToPort = dataSet.getValue("SeatNameToPort").split("\\|");
			for (int idx = 0; idx < portTypeList.length; idx++) {
				if (!isLocatorVisible(Locators.lnktnmgmt_TABTN, 5)) {
					iClick(Locators.navtnmgmt);
				}
				Scroll_Page_Down();
				By by = By.xpath("//div[contains(text(),'" + SeatNameToPort[idx] + "')]");
				jsClick(browser.findElement(by));				
				try {
					iClick(Locators.drpdownPortType, null, "Click on Port Type Drop Down");
				} catch (Exception ex) {
					scrollDown();
					iClick(Locators.drpdownPortType, null, "Click on Port Type Drop Down");
				}				
				selectvalue_dropdown("div", portTypeList[idx], true);
				Random rn = new Random();
				String portedTN = dataSet.getValue("portTNRange") + (rn.nextInt(899) + 100);
				iEnterText_withoutVerify(Locators.txtTNNumber, portedTN, "Entering the ported TN Number");
				iEnterText(Locators.txtExistingServices, "Vodafone");
				report.reportDoneEvent("TN Ported Information", "TN Ported Information Entered");
				iClick(Locators.btnSavelink, null, "Click on Save");
				testLocatorVisible(Locators.elmtTNinventorysuccess);
				sleep(3000);
				scrollUp();
				browser.findElement(by).click();
			}
			sleep(2000);
		} catch (Exception Ex) {
			report.reportHardFailEvent("TN Ported Information", "TN Ported Information Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "TN Ported Information in SIMON Failed!!! " + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public boolean updateCRCPState() {
		mstatus = true;
		try {
			String[] SeatNameToUpdateState = dataSet.getValue("SeatNameToUpdateState").split("\\|");
			String[] CRCPStateList = dataSet.getValue("CRCPStateList").split("\\|");
			String[] NPAList = dataSet.getValue("NPA").split("\\|");
			String[] RateCenterList = dataSet.getValue("CRCPRateCenter").split("\\|");
			for (int idx = 0; idx < SeatNameToUpdateState.length; idx++) {
				if (!isLocatorVisible(Locators.lnktnmgmt_TABTN, 5)) {
					iClick(Locators.navtnmgmt);
				}
				Scroll_Page_Down();

				scrollUp();
				scrollUp();
				By by = By.xpath("//div[contains(text(),'" + SeatNameToUpdateState[idx] + "')]");
				try {
					browser.findElement(by).click();
				} catch (WebDriverException ex) {
					scrollDown();
					browser.findElement(by).click();
				}
				Scroll_Page_Down();
				Scroll_Page_Down();
				if (isLocatorVisible(Locators.toggleBtnCRCP, 2)) {
					iClick(Locators.toggleBtnCRCP, null, "Enable CRCP TN");
				}
				iClick(Locators.drpdownState, null, "Click on state Drop Down");
				selectvalue_dropdown("div", CRCPStateList[idx], false);
				iEnterText(Locators.txtNPA, NPAList[idx]);
				iEnterText(Locators.txtRatecenter, RateCenterList[idx]);
				report.reportDoneEvent("TN CRCP State Information", "TN CRCP State Information Entered");
				jsClick(getLocatorWEList(Locators.btnSavelink).get(0));
				testLocatorVisible(Locators.elmtTNinventorysuccess);
				/*
				 * sleep(4000); scrollUp(); try{
				 * browser.findElement(by).click(); } catch (WebDriverException
				 * ex){ scrollDown(); browser.findElement(by).click(); }
				 */
			}
			sleep(2000);
		} catch (Exception Ex) {
			report.reportHardFailEvent("TN CRCP State Information",
					"TN CRCP State Information Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "TN CRCP State Information in SIMON Failed!!! " + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public boolean tollFreeTNConfiguration() {
		mstatus = true;
		try {
			if (!isLocatorVisible(Locators.tollFreeTN, 5)) {
				jsClick(testLocatorClickable(Locators.navtnmgmt, 5));
			}
			jsClick(testLocatorClickable(Locators.tollFreeTN, 1));
			Random rn = new Random();
			String tollFreeTN = dataSet.getValue("TFTNRange") + (rn.nextInt(899) + 100);
			iEnterText_withoutVerify(Locators.txtTNNumber, tollFreeTN, "Toll Free TN Entered");
			dataDump.setValue("TollFreeTNNumber", tollFreeTN);
			iClick(Locators.drpdwnAreaOfService, null,
					"Click on Area Of service Drop down:TN Management Page: Area of service drop down");
			selectvalue_dropdown("div", dataSet.getValue("areaOfService"), false);
			report.reportPassEvent("Toll Free TN", "Toll Free TN configuration done");
			iClick(Locators.btnSavelink, null, "Click on Save");
			testLocatorVisible(Locators.elmtTNinventorysuccess);
			sleep(3000);
			jsClick(testLocatorClickable(Locators.tollFreeTN));
			sleep(2000);
		} catch (Exception Ex) {
			report.reportHardFailEvent("Toll Free TN", "Toll Free TN configuration Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Toll Free TN configuration in SIMON Failed!!! " + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public boolean addNewTN(String TNType, String portType, String seatName) {
		mstatus = true;
		try {
			if (!isLocatorVisible(Locators.btnAddTN, 2)) {
				iClick(Locators.navtnmgmt);
			}
			testLocatorClickable(Locators.btnAddTN, 10);
			iClick(Locators.btnAddTN);
			iClick(Locators.dropDownTNType);
			selectvalue_dropdown("div", TNType, false);
			iClick(Locators.dropDownPortType);
			selectvalue_dropdown("div", portType, false);
			iClick(Locators.btnAdd, null, "Add a New TN:TN Management:Add button");
			sleep(5000);
			waitForElement(getLocatorWEList(Locators.UnassignedSeat).get(0));
			iClick(Locators.dropDownSeat);
			selectvalue_dropdown("div", seatName, true);
			iClick(Locators.btnSavelink, null, "Click on Save");
			testLocatorVisible(Locators.elmtTNinventorysuccess);
			report.reportPassEvent("New TN seat assignment", "Seat assigned to the new User");
			addLocator(Locators.addedTN, "xpath",
					"//div[@class='td tn-seat'][contains(normalize-space(.),'" + seatName + "')]", "AddedNewTN");
			testLocatorClickable(Locators.addedTN);
			iClick(Locators.addedTN, null, "Clicked on Added TN: TN Management page: Added TN");
			scrollUp();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Add and assign the seat to the TN",
					"Add and assign the seat to the TN Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Add and assign the seat to the TN in SIMON Failed!!! " + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}
}
