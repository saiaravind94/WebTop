package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class ReconfigureSwitchForTNDeactivationOrDisconnectOrder extends WebtopPage {

	Logger log = Logger.getLogger(ReconfigureSwitchForTNDeactivationOrDisconnectOrder.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public ReconfigureSwitchForTNDeactivationOrDisconnectOrder(FrameworkContext context) {
		super(context, "ReconfigureSwitchForTNDeactivationOrDisconnectOrder");
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

	public boolean reconfigureSwitchForTNDeactivationOrDisconnectOrder() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.trayName, 2)) {
				iSelectValue(Locators.lstResult, "Completed");
				iClick(Locators.btnSaveTray, null,
						"Click on Save button in the tray:Reconfigure Switch for TN Deactivation/Disconnect Order Page:Click on Save button in the tray");
				report.reportPassEvent("Reconfigure Switch for TN Deactivation/Disconnect Order",
						"Reconfigure Switch for TN Deactivation/Disconnect Order completed!!!", Status.PASS);
			} else {
				report.reportHardFailEvent("Reconfigure Switch for TN Deactivation/Disconnect Order",
						"Reconfigure Switch for TN Deactivation/Disconnect Order tray is not displayed!!!",
						Status.FAIL);
			}
		} catch (Exception e) {
			report.reportHardFailEvent("Reconfigure Switch for TN Deactivation/Disconnect Order",
					"Reconfigure Switch for TN Deactivation/Disconnect Order Failed!!!", Status.FAIL);
			String eMsg = "Error in Reconfigure Switch for TN Deactivation/Disconnect Order --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
}
