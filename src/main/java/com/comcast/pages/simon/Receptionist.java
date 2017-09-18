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
public class Receptionist extends WebtopPage {
	FrameworkContext context;	
	DataTable dataSet;
	Logger log = Logger.getLogger(Receptionist.class);

	private boolean mstatus;

	public static class Locators {
		public static final String navReceptionist = "navReceptionist";
		public static final String spinner = "spinner";
		public static final String lnkConfigureReceptionist = "lnkConfigureReceptionist";
		public static final String drpSelectSeatName = "drpSelectSeatName";
		public static final String btnSave = "btnSave";
		public static String drpValue = "";

	}

	public Receptionist(FrameworkContext context) {
		super(context, "Receptionist");
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

	public boolean configureReceptionist(String seatName) {
		mstatus = true;
		try {
			if (waitForElement(testLocatorClickable(Locators.navReceptionist), 20)) {
				iClick(Locators.navReceptionist);
			}
			waitForSpinnerComplete();
			if (waitForElement(testLocatorClickable(Locators.lnkConfigureReceptionist), 20)) {
				iClick(Locators.lnkConfigureReceptionist);
			}
			waitForSpinnerComplete();
			iClick(Locators.drpSelectSeatName);
			addLocator(Locators.drpValue, "xpath",
					"//span[.='Seat Name']//following::div[contains(text(),'"+seatName+"')]", "Add Locator for Dropdown");
			iClick(Locators.drpValue);

			iClick(Locators.btnSave);
			sleep(15000);
			report.reportPassEvent("Configure Receptionist", "Configure Receptionist Successful");
		} catch (Exception Ex) {
			report.reportHardFailEvent("Receptionist Configuration",
					"Receptionist Configuration Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Receptionist Configuration Failed!!! " + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}
}
