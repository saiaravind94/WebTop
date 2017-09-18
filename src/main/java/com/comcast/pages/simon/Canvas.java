package com.comcast.pages.simon;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.comcast.commons.WebtopPage;
import com.comcast.pages.simon.TNManagement.Locators;
import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.utils.ComparisonType;
import com.comcast.utils.TestSettings;
import com.comcast.utils.DataTable;
import com.comcast.utils.IUserDetails;

/**
 * Represents default page of the application
 * 
 */
public class Canvas extends WebtopPage {
	FrameworkContext context;
	private IUserDetails userDetails;
	DataTable dataSet;
	private TestSettings settings;

	Logger log = Logger.getLogger(Canvas.class);

	private boolean mstatus;

	public static class Locators {
		public static final String navCanvas = "navCanvas";
		public static final String canvasPage = "canvasPage";
		public static final String lnkBacktosite = "lnkBacktosite";
	}

	public Canvas(FrameworkContext context) {
		super(context, "Canvas");
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

	public boolean canvasMode() {
		mstatus = true;
		try {
			if (waitForElement(testLocatorClickable(Locators.navCanvas), 5)) {
				iClick(Locators.navCanvas, null, "Click on Canvas icon");
			}
			sleep(5000);
			iValidateAttributeValue(Locators.canvasPage, "style", "display: block;", ComparisonType.SUBSTR,
					"After Canvas Mode check if the screen is in full screen");
			iClick(Locators.lnkBacktosite);
			waitForSpinnerComplete();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Canvas mode verification", "Canvas mode verification Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Canvas mode verification in SIMON Failed!!! " + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

}
