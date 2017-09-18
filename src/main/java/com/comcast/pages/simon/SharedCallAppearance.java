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
public class SharedCallAppearance extends WebtopPage {
	FrameworkContext context;
	DataTable dataSet;
	Logger log = Logger.getLogger(SharedCallAppearance.class);

	private boolean mstatus;

	public static class Locators {
		public static final String navSharedCallAppearance = "navSharedCallAppearance";
		public static final String spinner = "spinner";
		public static String lnkConfigureSCA = "";
		public static final String drpUser = "drpUser";
		public static final String drpType = "drpType";
		public static final String btnSave = "btnSave";

	}

	public SharedCallAppearance(FrameworkContext context) {
		super(context, "SharedCallAppearance");
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
	public boolean configureSharedCallAppearance(String seatName, String Type) {
		mstatus = true;
		if (waitForElement(testLocatorClickable(Locators.navSharedCallAppearance), 20)) {
			try {
				if (waitForElement(testLocatorClickable(Locators.navSharedCallAppearance), 20)) {
					iClick(Locators.navSharedCallAppearance, null, "Click on Shared Call Appearance");
				}
				sleep(4000);
				String[] SharedCallAppearance = dataSet.getValue("SeatNameToEnableBLF").split("\\|");
				for (int idx = 0; idx < SharedCallAppearance.length; idx++) {
					addLocator(Locators.lnkConfigureSCA, "xpath",
							"//div[@class='call-pickup-info']/p[contains(.,'" + SharedCallAppearance[idx] + "')]",
							"Verify if the user for SCA is selected ");
					iClick(Locators.lnkConfigureSCA);
					waitForSpinnerComplete();
					iClick(Locators.drpUser);
					selectvalue_dropdown("div", seatName, true);
					iClick(Locators.drpType);
					selectvalue_dropdown("div", Type, true);
					iClick(Locators.btnSave);
					report.reportPassEvent("Configure SCA", "Configure SCA is successful Successful");
					sleep(7000);
				}

			} catch (Exception Ex) {
				report.reportHardFailEvent("Shared Call Configuration Configuration",
						"Shared Call Configuration  Failed" + Ex.getMessage());
				eMsg = report.getMessage() + "Shared Call Configuration Failed!!! " + Ex.getMessage();
				mstatus = false;
				log.error(eMsg);
			}

		}
		return mstatus;
	}
}
