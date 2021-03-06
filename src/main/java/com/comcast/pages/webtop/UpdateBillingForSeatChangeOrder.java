package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class UpdateBillingForSeatChangeOrder extends WebtopPage {

	Logger log = Logger.getLogger(UpdateBillingForSeatChangeOrder.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public UpdateBillingForSeatChangeOrder(FrameworkContext context) {
		super(context, "UpdateBillingForSeatChangeOrder");
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

	public boolean updateBillingForSeatChangeOrder() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.trayName, 2)) {
				iSelectValue(Locators.lstResult, "Completed");
				iClick(Locators.btnSaveTray, null,
						"Click on Save button in the tray:BILLING: Update Billing for Seat Change Order Page:Click on Save button in the tray");
				if (!isLocatorVisible(Locators.trayName, 1))
					report.reportPassEvent("BILLING: Update Billing for Seat Change Order",
							"BILLING: Update Billing for Seat Change Order completed successfully");
				else
					report.reportHardFailEvent("BILLING: Update Billing for Seat Change Order",
							"BILLING: Update Billing for Seat Change Order Failed!!!", Status.FAIL);

			} else {
				report.reportHardFailEvent("BILLING: Update Billing for Seat Change Order",
						"BILLING: Update Billing for Seat Change Order tray is not displayed!!!", Status.FAIL);
			}
		} catch (Exception e) {
			report.reportHardFailEvent("BILLING: Update Billing for Seat Change Order",
					"BILLING: Update Billing for Seat Change Order Failed!!!", Status.FAIL);
			String eMsg = "Error in BILLING: Update Billing for Seat Change Order --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
}
