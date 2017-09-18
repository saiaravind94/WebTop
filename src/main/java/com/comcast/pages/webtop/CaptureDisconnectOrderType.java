package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class CaptureDisconnectOrderType extends WebtopPage {

	Logger log = Logger.getLogger(CaptureDisconnectOrderType.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public CaptureDisconnectOrderType(FrameworkContext context) {
		super(context, "CaptureDisconnectOrderType");
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
		public static final String lstDisconnectOrderType = "lstDisconnectOrderType";
		public static final String btnSaveTray = "btnSaveTray";
	}

	public boolean captureDisconnectOrderType() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.trayName, 2)) {
				iClick(Locators.trayName);
				iSelectValue(Locators.lstDisconnectOrderType, "Voluntary");
				iClick(Locators.btnSaveTray, null,
						"Click on Save button in the tray:Capture Disconnect Order Type Page:Click on Save button in the tray");
				if (!isLocatorVisible(Locators.trayName, 2))
					report.reportPassEvent("CARE: Capture Disconnect Order Type",
							"CARE: Capture Disconnect Order Type Completed", Status.PASS);
				else
					report.reportHardFailEvent("CARE: Capture Disconnect Order Type",
							"CARE: Capture Disconnect Order Type Failed!!!", Status.FAIL);
			} else {
				report.reportHardFailEvent("CARE: Capture Disconnect Order Type",
						"CARE: Capture Disconnect Order Type tray is not displayed!!!", Status.FAIL);
			}
		} catch (Exception e) {
			report.reportHardFailEvent("CARE: Capture Disconnect Order Type",
					"CARE: Capture Disconnect Order Type Failed!!!", Status.FAIL);
			String eMsg = "Error in CARE: Capture Disconnect Order Type --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
}
