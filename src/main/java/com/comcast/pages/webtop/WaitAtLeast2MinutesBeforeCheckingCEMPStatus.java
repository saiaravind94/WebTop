package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class WaitAtLeast2MinutesBeforeCheckingCEMPStatus extends WebtopPage {

	Logger log = Logger.getLogger(WaitAtLeast2MinutesBeforeCheckingCEMPStatus.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public WaitAtLeast2MinutesBeforeCheckingCEMPStatus(FrameworkContext context) {
		super(context, "WaitAtLeast2MinutesBeforeCheckingCEMPStatus");
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
		public static final String lstResult = "lstResult";
		public static final String btnSaveTray = "btnSaveTray";
	}

	public boolean waitAtLeast2MinutesBeforeCheckingCEMPStatus() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.lstResult, 5)) {
				iSelectValue(Locators.lstResult, "Time Expired");
				iClick(Locators.btnSaveTray, null,
						"Click on Save button in the tray: Wait at least 2 Minutes before checking CEMP Status Page:Click on Save button in the tray");
				if (!isLocatorVisible(Locators.lstResult, 1))
					report.reportPassEvent("SYSTEM: Wait at least 2 Minutes before checking CEMP Status",
							"SYSTEM: Wait at least 2 Minutes before checking CEMP Status completed successfully");
				else
					report.reportHardFailEvent("SYSTEM: Wait at least 2 Minutes before checking CEMP Status",
							"SYSTEM: Wait at least 2 Minutes before checking CEMP Status Failed!!!", Status.FAIL);
			}
		} catch (Exception e) {
			report.reportHardFailEvent("SYSTEM: Wait at least 2 Minutes before checking CEMP Status",
					"SYSTEM: Wait at least 2 Minutes before checking CEMP Status Failed!!!", Status.FAIL);
			String eMsg = "Error in SYSTEM: Wait at least 2 Minutes before checking CEMP Status --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
}
