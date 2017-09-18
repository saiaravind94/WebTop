package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class ErrorCheckingIfTodayIsWithin5BusinessDaysOfDOI extends WebtopPage {

	Logger log = Logger.getLogger(ErrorCheckingIfTodayIsWithin5BusinessDaysOfDOI.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public ErrorCheckingIfTodayIsWithin5BusinessDaysOfDOI(FrameworkContext context) {
		super(context, "ErrorCheckingIfTodayIsWithin5BusinessDaysOfDOI");
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

	public static class Locators {
		public static final String tabHistory = "tabHistory";
		public static final String trayName = "trayName";
		public static final String lstResult = "lstResult";
		public static final String btnSaveTray = "btnSaveTray";
	}

	public boolean errorCheckingIfTodayIsWithin5BusinessDaysOfDOI() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.trayName, 2)) {
				iSelectValue(Locators.lstResult, "Retry Checking DOI");
				iClick(Locators.btnSaveTray, null,
						"Click on Save button in the tray:Error checking if today is within 5 business days of DOI Page:Click on Save button in the tray");
				if (!isLocatorVisible(Locators.trayName, 2)) {
					report.reportPassEvent("Error checking if today is within 5 business days of DOI",
							"Error checking if today is within 5 business days of DOI completed successfully!!!");
				} else {
					report.reportHardFailEvent("Error checking if today is within 5 business days of DOI",
							"Error checking if today is within 5 business days of DOI Failed!!!",
							Status.FAIL);
				}
			} 
		} catch (Exception e) {
			report.reportHardFailEvent("Error checking if today is within 5 business days of DOI",
					"Error checking if today is within 5 business days of DOI Failed!!!", Status.FAIL);
			String eMsg = "Error in Error checking if today is within 5 business days of DOI --- "
					+ e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
}
