package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class SelectTNTreatmentForDisconnectOrder extends WebtopPage {

	Logger log = Logger.getLogger(SelectTNTreatmentForDisconnectOrder.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public SelectTNTreatmentForDisconnectOrder(FrameworkContext context) {
		super(context, "SelectTNTreatmentForDisconnectOrder");
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
		public static final String lstSelectTNTreatmentForDisconnect = "lstSelectTNTreatmentForDisconnect";
		public static final String btnSaveTray = "btnSaveTray";
	}

	public boolean selectTNTreatmentForDisconnectOrder() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.trayName, 2)) {
				iClick(Locators.trayName);
				iSelectValue(Locators.lstSelectTNTreatmentForDisconnect, "Voluntary Disco - Return TNs to pool");
				iClick(Locators.btnSaveTray, null,
						"Click on Save button in the tray: Select TN Treatment for Disconnect Order Page:Click on Save button in the tray");
				if (!isLocatorVisible(Locators.trayName, 1))
					report.reportPassEvent("CARE:  Select TN Treatment for Disconnect Order",
							"CARE:  Select TN Treatment for Disconnect Order Completed successfully!!!", Status.PASS);
				else
					report.reportHardFailEvent("CARE:  Select TN Treatment for Disconnect Order",
							"CARE:  Select TN Treatment for Disconnect Order Failed!!!", Status.FAIL);
			} else {
				report.reportHardFailEvent("CARE:  Select TN Treatment for Disconnect Order",
						"CARE: Select TN Treatment for Disconnect Order tray is not displayed!!!", Status.FAIL);
			}

		} catch (Exception e) {
			report.reportHardFailEvent("CARE:  Select TN Treatment for Disconnect Order",
					"CARE:  Select TN Treatment for Disconnect Order Failed!!!", Status.FAIL);
			String eMsg = "Error in CARE:  Select TN Treatment for Disconnect Order --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
}
