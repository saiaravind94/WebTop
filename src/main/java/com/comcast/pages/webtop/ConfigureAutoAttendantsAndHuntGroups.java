package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class ConfigureAutoAttendantsAndHuntGroups extends WebtopPage {

	Logger log = Logger.getLogger(ConfigureAutoAttendantsAndHuntGroups.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public ConfigureAutoAttendantsAndHuntGroups(FrameworkContext context) {
		super(context, "ConfigureAutoAttendantsAndHuntGroups");
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

	public boolean configureAutoAttendantsAndHuntGroups() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.lstResult, 5)) {
				iSelectValue(Locators.lstResult, "Completed");
				iClick(Locators.btnSaveTray, null,
						"Click on Save button in the tray:Configure Auto Attendants and Hunt Groups Page:Click on Save button in the tray");
				if (!isLocatorVisible(Locators.lstResult, 1))
					report.reportPassEvent("Configure Auto Attendants and Hunt Groups",
							"Configure Auto Attendants and Hunt Groups completed successfully");
				else
					report.reportHardFailEvent("Configure Auto Attendants and Hunt Groups",
							"Configure Auto Attendants and Hunt Groups Failed!!!", Status.FAIL);
			}
		} catch (Exception e) {
			report.reportHardFailEvent("Configure Auto Attendants and Hunt Groups",
					"Configure Auto Attendants and Hunt Groups Failed!!!", Status.FAIL);
			String eMsg = "Error in Configure Auto Attendants and Hunt Groups --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
}
