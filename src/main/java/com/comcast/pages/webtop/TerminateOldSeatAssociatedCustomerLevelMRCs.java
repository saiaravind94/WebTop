package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class TerminateOldSeatAssociatedCustomerLevelMRCs extends WebtopPage {

	Logger log = Logger.getLogger(TerminateOldSeatAssociatedCustomerLevelMRCs.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public TerminateOldSeatAssociatedCustomerLevelMRCs(FrameworkContext context) {
		super(context, "TerminateOldSeatAssociatedCustomerLevelMRCs");
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

	public boolean terminateOldSeatAssociatedCustomerLevelMRCs() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.trayName, 2)) {
				iSelectValue(Locators.lstResult, "Completed");
				iClick(Locators.btnSaveTray, null,
						"Click on Save button in the tray:CARE: Terminate Old Seat Associated Customer Level MRC(s) Page:Click on Save button in the tray");
				if (!isLocatorVisible(Locators.trayName, 1))
					report.reportPassEvent("CARE: Terminate Old Seat Associated Customer Level MRC(s)",
							"CARE: Terminate Old Seat Associated Customer Level MRC(s) completed successfully");
				else
					report.reportHardFailEvent("CARE: Terminate Old Seat Associated Customer Level MRC(s)",
							"CARE: Terminate Old Seat Associated Customer Level MRC(s) Failed!!!", Status.FAIL);
			} else {
				report.reportHardFailEvent("CARE: Terminate Old Seat Associated Customer Level MRC(s)",
						"CARE: Terminate Old Seat Associated Customer Level MRC(s) tray is not displayed!!!",
						Status.FAIL);
			}
		} catch (Exception e) {
			report.reportHardFailEvent("CARE: Terminate Old Seat Associated Customer Level MRC(s)",
					"CARE: Terminate Old Seat Associated Customer Level MRC(s) Failed!!!", Status.FAIL);
			String eMsg = "Error in CARE: Terminate Old Seat Associated Customer Level MRC(s) --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
}
