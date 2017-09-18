package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class UpdateBillingFor10digitTNRemovalOrder extends WebtopPage {

	Logger log = Logger.getLogger(UpdateBillingFor10digitTNRemovalOrder.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public UpdateBillingFor10digitTNRemovalOrder(FrameworkContext context) {
		super(context, "UpdateBillingFor10digitTNRemovalOrder");
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

	public boolean updateBillingFor10digitTNRemovalOrder() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.lstResult, 2)) {
				iSelectValue(Locators.lstResult, "Completed");
				iClick(Locators.btnSaveTray, null,
						"Click on Save button in the tray: Update Billing for 10-digit TN Removal Order Page:Click on Save button in the tray");
				if (!isLocatorVisible(Locators.lstResult, 1))
					report.reportPassEvent("BILLING: Update Billing for 10-digit TN Removal Order",
							"BILLING: Update Billing for 10-digit TN Removal Order completed successfully");
				else
					report.reportHardFailEvent("BILLING: Update Billing for 10-digit TN Removal Order",
							"BILLING: Update Billing for 10-digit TN Removal Order Failed!!!", Status.FAIL);
			} else {
				report.reportHardFailEvent("BILLING: Update Billing for 10-digit TN Removal Order",
						"BILLING: Update Billing for 10-digit TN Removal Order tray is not displayed!!!", Status.FAIL);
			}
		} catch (Exception e) {
			report.reportHardFailEvent("BILLING: Update Billing for 10-digit TN Removal Order",
					"BILLING: Update Billing for 10-digit TN Removal Order Failed!!!", Status.FAIL);
			String eMsg = "Error in BILLING: Update Billing for 10-digit TN Removal Order --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
}
