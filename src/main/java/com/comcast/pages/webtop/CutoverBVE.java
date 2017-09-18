package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class CutoverBVE extends WebtopPage {

	Logger log = Logger.getLogger(CutoverBVE.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public CutoverBVE(FrameworkContext context) {
		super(context, "CutoverBVE");
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

	public boolean cutoverBVE() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.lstResult, 5)) {
				iSelectValue(Locators.lstResult, "Completed");
				iClick(Locators.btnSaveTray, null,
						"Click on Save button in the tray:INSTALL TECH: Cutover BVE Page:Click on Save button in the tray");
				if (!isLocatorVisible(Locators.lstResult, 5))
					report.reportPassEvent("INSTALL TECH: Cutover BVE",
							"INSTALL TECH: Cutover BVE completed successfully");
				else
					report.reportHardFailEvent("INSTALL TECH: Cutover BVE",
							"INSTALL TECH: Cutover BVE tray is not displayed!!!", Status.FAIL);
			} else {
				report.reportSoftFailEvent("INSTALL TECH: Cutover BVE",
						"INSTALL TECH: Cutover BVE tray is not displayed!!!", Status.FAIL);
				String eMsg = "INSTALL TECH: Cutover BVE tray is not displayed--- ";
				log.error(eMsg);
			}
		} catch (Exception e) {
			report.reportHardFailEvent("INSTALL TECH: Cutover BVE", "INSTALL TECH: Cutover BVE Failed!!!", Status.FAIL);
			String eMsg = "Error in INSTALL TECH: Cutover BVE --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
}
