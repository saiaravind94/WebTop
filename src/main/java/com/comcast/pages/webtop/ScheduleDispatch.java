package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.pages.webtop.EnterFOCDateForPortedTNs.Locators;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class ScheduleDispatch extends WebtopPage {

	Logger log = Logger.getLogger(ScheduleDispatch.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public ScheduleDispatch(FrameworkContext context) {
		super(context, "ScheduleDispatch");
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
		public static final String lstScheduleOrSkipDispactedDate = "lstScheduleOrSkipDispactedDate";
		public static final String btnSaveTray = "btnSaveTray";
		public static final String txtDispatchDueDate = "txtDispatchDueDate";
	}

	public boolean scheduleDispatch() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.trayName, 2)) {
				iClick(Locators.trayName);
				iSelectValue(Locators.lstScheduleOrSkipDispactedDate, "Dispatch Scheduled");
				iEnterText(Locators.txtDispatchDueDate,
						getCurrentDateTime_Operation("mmddyyyy_timestamp", "plus", 1, 0, true));
				iClick(Locators.btnSaveTray, null,
						"Click on save button in the tray: Schedule Dispatch Page:Click on Save button in the tray");
				if (!isLocatorVisible(Locators.trayName, 1))
					report.reportPassEvent("CARE: Schedule Dispatch",
							"CARE: Schedule Dispatch completed successfully!!!", Status.PASS);
				else
					report.reportHardFailEvent("CARE: Schedule Dispatch", "CARE: Schedule Dispatch Failed!!!",
							Status.FAIL);
			} else {
				report.reportHardFailEvent("CARE: Schedule Dispatch",
						"CARE: Schedule Dispatch tray is not displayed!!!", Status.FAIL);
			}
		} catch (Exception e) {
			report.reportHardFailEvent("CARE: Schedule Dispatch", "CARE: Schedule Dispatch Failed!!!", Status.FAIL);
			String eMsg = "Error in CARE: Schedule Dispatch--- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
}
