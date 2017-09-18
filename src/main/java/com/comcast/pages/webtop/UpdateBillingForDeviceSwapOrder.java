package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class UpdateBillingForDeviceSwapOrder extends WebtopPage {

	Logger log = Logger.getLogger(UpdateBillingForDeviceSwapOrder.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public UpdateBillingForDeviceSwapOrder(FrameworkContext context) {
		super(context, "UpdateBillingForDeviceSwapOrder");
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

	public boolean updateBillingForDeviceSwapOrder() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.trayName, 2)) {
				iSelectValue(Locators.lstResult, "Completed");
				iClick(Locators.btnSaveTray, null,
						"Click on Save button in the tray:BILLING: Update Billing for swap Order Page:Click on Save button in the tray");
				if (!isLocatorVisible(Locators.trayName, 1))
					report.reportPassEvent("BILLING: Update Billing for Swap Order",
							"BILLING: Update Billing for Swap Order completed successfully!!!", Status.PASS);
				else
					report.reportHardFailEvent("BILLING: Update Billing for Swap Order",
							"BILLING: Update Billing for Swap Order Failed!!!", Status.FAIL);
			} else {
				report.reportHardFailEvent("BILLING: Update Billing for Swap Order",
						"BILLING: Update Billing for Swap Order tray is not displayed!!!", Status.FAIL);
			}
		} catch (Exception e) {
			report.reportHardFailEvent("BILLING: Update Billing for Swap Order",
					"BILLING: Update Billing for Swap Order Failed!!!", Status.FAIL);
			String eMsg = "Error in BILLING: Update Billing for Swap Change Order --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
}
