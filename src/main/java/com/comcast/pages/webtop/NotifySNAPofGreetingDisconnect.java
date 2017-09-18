package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class NotifySNAPofGreetingDisconnect extends WebtopPage {

	Logger log = Logger.getLogger(NotifySNAPofGreetingDisconnect.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public NotifySNAPofGreetingDisconnect(FrameworkContext context) {
		super(context, "NotifySNAPofGreetingDisconnect");
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

	public boolean notifySNAPofGreetingDisconnect() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.trayName, 2)) {
				iSelectValue(Locators.lstResult, "Completed");
				iClick(Locators.btnSaveTray, null,
						"Click on Save button in the tray:Notify SNAP of Greeting Disconnect Page:Click on Save button in the tray");
				if (!isLocatorVisible(Locators.trayName, 1)) {
					report.reportPassEvent("CARE: Notify SNAP of Greeting Disconnect",
							"CARE: Notify SNAP of Greeting Disconnect Completed successfully!!!", Status.PASS);
				} else {
					report.reportHardFailEvent("CARE: Notify SNAP of Greeting Disconnect",
							"CARE: Notify SNAP of Greeting Disconnect Failed!!!", Status.FAIL);
				}
			}
		} catch (Exception e) {
			report.reportHardFailEvent("CARE: Notify SNAP of Greeting Disconnect",
					"CARE: Notify SNAP of Greeting Disconnect Failed!!!", Status.FAIL);
			String eMsg = "Error in CARE: Notify SNAP of Greeting Disconnect --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
}
