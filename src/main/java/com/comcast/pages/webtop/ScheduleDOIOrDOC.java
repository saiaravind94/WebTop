package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class ScheduleDOIOrDOC extends WebtopPage {

	Logger log = Logger.getLogger(ScheduleDOIOrDOC.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public ScheduleDOIOrDOC(FrameworkContext context) {
		super(context, "ScheduleDOIOrDOC");
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
		public static final String lnkScheduleDOIOrDOC = "lnkScheduleDOIOrDOC";
		public static final String txtDOI = "txtDOI";
		public static final String txtDOC = "txtDOC";
		public static final String btnSave = "btnSave";
		public static final String dropDownScheduledOrCancelled = "dropDownScheduledOrCancelled";
	}

	public boolean scheduleDOIOrDOC() {
		mstatus = true;
		try {
			if (!isLocatorVisible(Locators.lnkScheduleDOIOrDOC, 5)) {
				iClick(Locators.tabHistory, null, "Click on History Tab");
			}
			iClick(Locators.lnkScheduleDOIOrDOC, null, "Click on Schedule DOC Tray");
			if (isLocatorVisible(Locators.dropDownScheduledOrCancelled, 1)) {
				iSelectValue(Locators.dropDownScheduledOrCancelled, "DOI / DOC Scheduled");
			}
			iEnterText(Locators.txtDOI, getCurrentDateTime_Operation("mmddyyyy_timestamp", "plus", 1, 0, true),
					"Enter DOC");
			iEnterText(Locators.txtDOC, getCurrentDateTime_Operation("mmddyyyy_timestamp", "plus", 1, 5, true),
					"Enter DOI");
			iClick(Locators.btnSave, null, "Click on Save button");
			if (!isLocatorVisible(Locators.lnkScheduleDOIOrDOC, 1))
				report.reportDoneEvent("Schedule DOI/DOC Tray", "Schedule DOI/DOC completed successfully");
			else
				report.reportHardFailEvent("Schedule DOI/DOC", "Schedule DOI/DOC Failed!!!", Status.FAIL);
		} catch (Exception e) {
			mstatus = false;
			report.reportHardFailEvent("Schedule DOI/DOC", "Schedule DOI/DOC Failed!!!", Status.FAIL);
			String eMsg = "Error in Schedule DOI/DOC --- " + e.getMessage();
			log.error(eMsg);
		}
		return mstatus;
	}

}
