package com.comcast.pages.simon;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.server.handler.interactions.MouseMoveToLocation;

import com.comcast.commons.WebtopPage;
import com.comcast.pages.simon.DirectoryListings.Locators;
import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.utils.TestSettings;
import com.comcast.utils.DataTable;
import com.comcast.utils.IUserDetails;

/**
 * Represents default page of the application
 * 
 */
public class HuntGroup extends WebtopPage {
	FrameworkContext context;
	DataTable dataSet;
	Logger log = Logger.getLogger(HuntGroup.class);

	private boolean mstatus;

	public static class Locators {
		public static final String navHuntGroup = "navHuntGroup";
		public static final String spinner = "spinner";
		public static final String drpPhoneHG = "drpPhoneHG";
		public static final String inpEXTHG = "inpEXTHG";
		public static final String drpHGTimeZone = "drpHGTimeZone";
		public static final String lnkUsersHG = "lnkUsersHG";
		public static final String btnSave = "btnSave";
		public static final String btnAddHGUsers = "btnAddHGUsers";
		public static final String btnAddAvailableHGUsers = "btnAddAvailableHGUsers";
		public static String AddHuntGrpUsers = "";

	}

	public HuntGroup(FrameworkContext context) {
		super(context, "HuntGroup");
		report = context.getReport();
		this.context = context;
		this.dataSet = context.getDataTable();
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

	public boolean configureHuntGroupsettings( String TZ,String ext) {
		mstatus = true;
		String[] HuntGroup = dataSet.getValue("HuntGroupSelect").split("\\|");
		try {
			if (waitForElement(testLocatorClickable(Locators.navHuntGroup), 20)) {
				iClick(Locators.navHuntGroup, null, "Click on Hunt Groups");
			}
			sleep(4000);
			for (int idx = 0; idx < HuntGroup.length; idx++) {
				By by = By.xpath("//h2[.='Hunt Groups']//following::p[contains(.,'"+HuntGroup[idx]+"')]");
				browser.findElement(by).click();
				waitForSpinnerComplete();
				iEnterText(Locators.inpEXTHG, ext);
				iClick(Locators.drpHGTimeZone);
				selectvalue_dropdown("div", TZ, true);
				Scroll_Page_Down();
				iClick(Locators.btnSave);
				report.reportPassEvent("Configure Hunt Group Settings" + HuntGroup[idx],
						"Configure Hunt Group Feature");
				waitForSpinnerComplete();
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Configure Hunt Group Settings",
					"Configure Hunt Group Settings Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Configure Hunt Group Settings in SIMON Failed!!! " + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public boolean configureUsersHuntGroup(String seatName) {
		mstatus = true;
		try {
			if (waitForElement(testLocatorClickable(Locators.lnkUsersHG), 20)) {
				iClick(Locators.lnkUsersHG);
				waitForSpinnerComplete();
			}
			iClick(Locators.btnAddHGUsers);
			addLocator(Locators.AddHuntGrpUsers, "xpath",
					"//div[@class='js-list gat-items-list']//div[contains(.,'"+seatName+"')]/div",
					"Select the Users to be configured for Hunt Group");
			iClick(Locators.AddHuntGrpUsers);
			iClick(Locators.btnAddAvailableHGUsers);
			iClick(Locators.btnSave);
			waitForSpinnerComplete();
			report.reportPassEvent("Configure users for Hunt Group", "Configure user for Hunt Group is Successful");
		} catch (Exception Ex) {
			report.reportHardFailEvent("Configure users-Hunt Group",
					"Configure users-Hunt Group Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Configure users for Hunt Group Failed!!! " + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

}
