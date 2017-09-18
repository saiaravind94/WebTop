package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class WaitForDOCDate extends WebtopPage {

	Logger log = Logger.getLogger(WaitForDOCDate.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public WaitForDOCDate(FrameworkContext context) {
		super(context, "WaitForDOCDate");
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

	public boolean waitForDOCDate() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.lstResult, 5)) {
				iSelectValue(Locators.lstResult, "DOC Date has arrived");
				iClick(Locators.btnSaveTray, null,
						"Click on Save button in the tray:SYSTEM: Wait For DOC Date Page:Click on Save button in the tray");
				if (!isLocatorVisible(Locators.lstResult, 1))
					report.reportPassEvent("SYSTEM: Wait For DOC Date",
							"SYSTEM: Wait For DOC Date completed successfully");
				else
					report.reportHardFailEvent("SYSTEM: Wait For DOC Date", "SYSTEM: Wait For DOC Date Failed!!!",
							Status.FAIL);
			} 
		} catch (Exception e) {
			report.reportHardFailEvent("SYSTEM: Wait For DOC Date", "SYSTEM: Wait For DOC Date Failed!!!", Status.FAIL);
			String eMsg = "Error in SYSTEM: Wait For DOC Date --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
}
