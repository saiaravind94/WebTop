package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class CaptureDisconnectOrderReason extends WebtopPage {

	Logger log = Logger.getLogger(CaptureDisconnectOrderReason.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public CaptureDisconnectOrderReason(FrameworkContext context) {
		super(context, "CaptureDisconnectOrderReason");
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
		public static final String lstDisconnectReason = "lstDisconnectReason";
		public static final String btnSaveTray = "btnSaveTray";
	}

	public boolean captureDisconnectOrderReason() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.trayName, 2)) {
				iClick(Locators.trayName);
				iSelectValue(Locators.lstDisconnectReason, "Moving Business");
				iClick(Locators.btnSaveTray, null,
						"Click on Save button in the tray:Capture Disconnect Order Reason Page:Click on Save button in the tray");
				if (!isLocatorVisible(Locators.trayName, 1))
					report.reportPassEvent("CARE: Capture Disconnect Order Reason",
							"CARE: Capture Disconnect Order Reason Completed successfully!!!", Status.PASS);
				else
					report.reportHardFailEvent("CARE: Capture Disconnect Order Reason",
							"CARE: Capture Disconnect Order Reason Failed!!!", Status.FAIL);
			} else {
				report.reportHardFailEvent("CARE: Capture Disconnect Order Reason",
						"CARE: Capture Disconnect Order Reason tray is not displayed!!!", Status.FAIL);
			}
		} catch (Exception e) {
			report.reportHardFailEvent("CARE: Capture Disconnect Order Reason",
					"CARE: Capture Disconnect Order Reason Failed!!!", Status.FAIL);
			String eMsg = "Error in CARE: Capture Disconnect Order Reason--- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
}
