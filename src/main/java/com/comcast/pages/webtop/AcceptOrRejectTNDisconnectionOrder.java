package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class AcceptOrRejectTNDisconnectionOrder extends WebtopPage {

	Logger log = Logger.getLogger(AcceptOrRejectTNDisconnectionOrder.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public AcceptOrRejectTNDisconnectionOrder(FrameworkContext context) {
		super(context, "AcceptOrRejectTNDisconnectionOrder");
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

	public boolean acceptOrRejectTNDisconnectionOrder() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.trayName, 2)) {
				iSelectValue(Locators.lstResult, "Accept Disconnect Order");
				iClick(Locators.btnSaveTray, null,
						"Click on Save button in the tray:Accept or Reject TN Disconnection Order Page:Click on Save button in the tray");
				if (!isLocatorVisible(Locators.trayName, 1)) {
					report.reportPassEvent("CARE: Accept or Reject TN Disconnection Order",
							"CARE: Accept or Reject TN Disconnection Order Completed successfully!!!", Status.PASS);
				} else {
					report.reportHardFailEvent("CARE: Accept or Reject TN Disconnection Order",
							"CARE: Accept or Reject TN Disconnection Order Failed!!!", Status.FAIL);
				}
			} else {
				report.reportHardFailEvent("CARE: Accept or Reject TN Disconnection Order",
						"CARE: Accept or Reject TN Disconnection Order tray is not displayed!!!", Status.FAIL);
			}

		} catch (Exception e) {
			report.reportHardFailEvent("CARE: Accept or Reject TN Disconnection Order",
					"CARE: Accept or Reject TN Disconnection Order Failed!!!", Status.FAIL);
			String eMsg = "Error in CARE: Accept or Reject TN Disconnection Order --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
}
