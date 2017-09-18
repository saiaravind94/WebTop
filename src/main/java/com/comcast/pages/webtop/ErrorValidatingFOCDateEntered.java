package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class ErrorValidatingFOCDateEntered extends WebtopPage {

	Logger log = Logger.getLogger(ErrorValidatingFOCDateEntered.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public ErrorValidatingFOCDateEntered(FrameworkContext context) {
		super(context, "ErrorValidatingFOCDateEntered");
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

	public boolean errorValidatingFOCDateEntered () {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.trayName, 2)) {
				iSelectValue(Locators.lstResult, "Re-Enter FOC Date");
				iClick(Locators.btnSaveTray, null,
						"Click on Save button in the tray:Accept or Reject TN Deactivation Order (TNs remain on account) Page:Click on Save button in the tray");
				if (!isLocatorVisible(Locators.trayName, 2)) {
					report.reportPassEvent("ADMIN: Error Validating FOC Date Entered",
							"ADMIN: Error Validating FOC Date Entered completed successfully!!!");
				} else {
					report.reportHardFailEvent("ADMIN: Error Validating FOC Date Entered",
							"ADMIN: Error Validating FOC Date Entered Failed!!!",
							Status.FAIL);
				}
			} else {
				report.reportHardFailEvent("ADMIN: Error Validating FOC Date Entered",
						"ADMIN: Error Validating FOC Date Entered tray is not displayed!!!",
						Status.FAIL);
			}
		} catch (Exception e) {
			report.reportHardFailEvent("ADMIN: Error Validating FOC Date Entered",
					"ADMIN: Error Validating FOC Date Entered Failed!!!", Status.FAIL);
			String eMsg = "Error in ADMIN: Error Validating FOC Date Entered --- "
					+ e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
}
