package com.comcast.pages.simon;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.comcast.commons.WebtopPage;
import com.comcast.pages.simon.TNManagement.Locators;
import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.utils.TestSettings;
import com.comcast.utils.DataTable;
import com.comcast.utils.IUserDetails;

/**
 * Represents default page of the application
 * 
 */
public class EquipmentConfiguration extends WebtopPage {
	FrameworkContext context;
	private IUserDetails userDetails;
	DataTable dataSet;
	private TestSettings settings;

	Logger log = Logger.getLogger(EquipmentConfiguration.class);

	private boolean mstatus;

	public static class Locators {
		public static final String navEquipment = "navEquipment";
		public static final String baseStation = "baseStation";
		public static final String btnAddUsers = "btnAddUsers";
		public static final String lstUsers = "lstUsers";
		public static final String btnAdd = "btnAdd";
		public static final String btnSave = "btnSave";
		public static final String elmtSuccesfulMsg = "elmtSuccesfulMsg";
		public static final String loudRinger = "loudRinger";
		public static final String drpdwnColorStrobeLights = "drpdwnColorStrobeLights";
		public static final String drpdwnClearStrobeLights = "drpdwnClearStrobeLights";
		public static final String loudHornTogglebtn = "loudHornTogglebtn";
		public static final String txtBasestation = "txtBasestation";
		public static final String drpdwnRepeaters = "drpdwnRepeaters";
	}

	public EquipmentConfiguration(FrameworkContext context) {
		super(context, "EquipmentConfiguration");
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

	public boolean equipmentConfiguration() {
		mstatus = true;
		try {
			scrollUp();
			if (waitForElement(testLocatorClickable(Locators.navEquipment), 5)) {
				iClick(Locators.navEquipment, null, "Click on Directory");
			}
			sleep(3000);
			testLocatorClickable(Locators.baseStation);
			iClick(Locators.baseStation);
			iClick(Locators.btnAddUsers);
			List<WebElement> usersList = getLocatorWEList(Locators.lstUsers);
			for (int idx = 0; idx < usersList.size(); idx++) {
				iClick(usersList.get(idx));
			}
			iClick(Locators.btnAdd);
			iClick(Locators.btnSave);
			testLocatorVisible(Locators.elmtSuccesfulMsg);
			report.reportPassEvent("Equipment Configuration", "Equipment Configuration Entered");
		} catch (Exception Ex) {
			report.reportHardFailEvent("Equipment Configuration", "Equipment Configuration Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Equipment Configuration in SIMON Failed!!! " + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}
public boolean baseStationSettings() {
	mstatus = true;
	try {
		scrollUp();
		if (waitForElement(testLocatorClickable(Locators.navEquipment), 5)) {
			iClick(Locators.navEquipment, null, "Click on Directory");
		}
		sleep(3000);
		testLocatorClickable(Locators.baseStation);
		iClick(Locators.baseStation);
		iEnterText(Locators.txtBasestation, "DECTbase 1_Update");
		iClick(Locators.drpdwnRepeaters);
		selectvalue_dropdown("div", "2", false);
		iClick(Locators.btnSave);
		testLocatorVisible(Locators.elmtSuccesfulMsg);
		report.reportPassEvent("Base Station Settings", "Base Station settings Updated");
	} catch (Exception Ex) {
		report.reportHardFailEvent("Base Station Settings", "Base Station settings Updation Failed" + Ex.getMessage());
		eMsg = report.getMessage() + "Base Station settings Updation in SIMON Failed!!! " + Ex.getMessage();
		mstatus = false;
		log.error(eMsg);
	}
	return mstatus;
}

	public boolean loudRingerSettings(String nbOfColorStrobeLights, String nbOfClearStrobeLights, boolean loudHorn) {
		mstatus = true;
		try {
			boolean enabled = false;
			scrollUp();
			if (waitForElement(testLocatorClickable(Locators.navEquipment), 5)) {
				iClick(Locators.navEquipment, null, "Click on Directory");
			}
			sleep(2000);
			testLocatorClickable(Locators.loudRinger);
			iClick(Locators.loudRinger);
			iClick(Locators.drpdwnClearStrobeLights, null, "Click on Clear strobe lights Drop Down");
			browser.findElement(By.xpath(
					"//div[@data-id='" + nbOfClearStrobeLights + "' and contains(@class,'js-dropdown-result-value')]"))
					.click();
			iClick(Locators.drpdwnColorStrobeLights, null, "Click on Color strobe lights Drop Down");
			browser.findElement(By.xpath(
					"//div[@data-id='" + nbOfColorStrobeLights + "' and contains(@class,'js-dropdown-result-value')]"))
					.click();
			enabled = testLocatorClickable(Locators.loudHornTogglebtn).getAttribute("class").contains("checked");
			if (!enabled && loudHorn) {
				iClick(Locators.loudHornTogglebtn);
			}
			iClick(Locators.btnSave);
			sleep(5000);
			report.reportPassEvent("Loud Ringer Configuration", "Loud Ringer Configuration done");
		} catch (Exception Ex) {
			report.reportHardFailEvent("Loud Ringer Configuration",
					"Loud Ringer Configuration Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Loud Ringer Configuration in SIMON Failed!!! " + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

}
