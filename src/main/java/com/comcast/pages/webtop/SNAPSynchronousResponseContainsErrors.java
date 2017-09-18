package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class SNAPSynchronousResponseContainsErrors extends WebtopPage {

	Logger log = Logger.getLogger(SNAPSynchronousResponseContainsErrors.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public SNAPSynchronousResponseContainsErrors(FrameworkContext context) {
		super(context, "SNAPSynchronousResponseContainsErrors");
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
		public static final String SNPErrorTray = "SNPErrorTray";
		public static final String tabPerformOrderAction = "tabPerformOrderAction";
		public static final String lnkByPassErrorTray = "lnkByPassErrorTray";
		public static final String trayName = "trayName";
		public static final String btnSetResult="btnSetResult";

	}

	public boolean SNAPSynchronousResponseContainsError() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.SNPErrorTray, 2)) {
				iClick(Locators.tabPerformOrderAction, null, "Click on Perform Order Action to bypass the ERROR tray");
				iClick(Locators.lnkByPassErrorTray);
				if(isLocatorVisible(Locators.trayName, 2)){
					iClick(Locators.trayName);
					iSelectValue(Locators.lstResult, "Successful");
					iClick(Locators.btnSetResult);
					if (!isLocatorVisible(Locators.trayName, 1))
						report.reportDoneEvent("Submit Create Recording Call to SNAP Tray", "Submit Create Recording Call to SNAP completed successfully");
					else
						report.reportHardFailEvent("Submit Create Recording Call to SNAP", "Submit Create Recording Call to SNAP Failed!!!", Status.FAIL);
					}
			}			
		} catch (Exception e) {
			mstatus = false;
			report.reportHardFailEvent("Submit Create Recording Call to SNAP", "Submit Create Recording Call to SNAP Failed!!!", Status.FAIL);
			String eMsg = "Error in Submit Create Recording Call to SNAP --- " + e.getMessage();
			log.error(eMsg);
		}
		return mstatus;
	}

}
