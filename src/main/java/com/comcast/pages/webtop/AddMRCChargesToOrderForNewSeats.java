package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class AddMRCChargesToOrderForNewSeats extends WebtopPage {

	Logger log = Logger.getLogger(AddMRCChargesToOrderForNewSeats.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public AddMRCChargesToOrderForNewSeats(FrameworkContext context) {
		super(context, "AddMRCChargesToOrderForNewSeats");
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

	public boolean addMRCChargesToOrderForNewSeats() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.trayName, 2)) {
				iSelectValue(Locators.lstResult, "Completed");
				iClick(Locators.btnSaveTray, null,
						"Click on Save button in the tray:CARE: Add MRC Charge(s) to Order for New Seat(s) Page:Click on Save button in the tray");
				if (!isLocatorVisible(Locators.trayName, 1)) {
					report.reportPassEvent("CARE: Add MRC Charge(s) to Order for New Seat(s)",
							"CARE: Add MRC Charge(s) to Order for New Seat(s) completed successfully!!!", Status.PASS);
				} else {
					report.reportHardFailEvent("CARE: Add MRC Charge(s) to Order for New Seat(s)",
							"CARE: Add MRC Charge(s) to Order for New Seat(s) Failed!!!", Status.FAIL);
				}
			} else {
				report.reportHardFailEvent("CARE: Add MRC Charge(s) to Order for New Seat(s)",
						"CARE: Add MRC Charge(s) to Order for New Seat(s) tray is not displayed!!!", Status.FAIL);
			}
		} catch (Exception e) {
			report.reportHardFailEvent("CARE: Add MRC Charge(s) to Order for New Seat(s)",
					"CARE: Add MRC Charge(s) to Order for New Seat(s) Failed!!!", Status.FAIL);
			String eMsg = "Error in CARE: Add MRC Charge(s) to Order for New Seat(s) --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
}
