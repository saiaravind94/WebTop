package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class WaitForResponseFromNeustar extends WebtopPage {

	Logger log = Logger.getLogger(WaitForResponseFromNeustar.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public WaitForResponseFromNeustar(FrameworkContext context) {
		super(context, "WaitForResponseFromNeustar");
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

	public boolean waitForResponseFromNeustar() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.lstResult, 5)) {
				iSelectValue(Locators.lstResult, "Successful");
				iClick(Locators.btnSaveTray, null,
						"Click on Save button in the tray:Wait for Response from Neustar (DO NOT SET RESULT) Page:Click on Save button in the tray");
				if (!isLocatorVisible(Locators.lstResult, 1))
					report.reportPassEvent("SYSTEM: Wait for Response from Neustar (DO NOT SET RESULT)",
							"SYSTEM: Wait for Response from Neustar (DO NOT SET RESULT) completed successfully");
				else
					report.reportHardFailEvent("SYSTEM: Wait for Response from Neustar (DO NOT SET RESULT)",
							"SYSTEM: Wait for Response from Neustar (DO NOT SET RESULT) Failed!!!", Status.FAIL);
			}
			/*
			 * else { report.reportHardFailEvent(
			 * "SYSTEM: Wait for Response from Neustar (DO NOT SET RESULT)",
			 * "SYSTEM: Wait for Response from Neustar (DO NOT SET RESULT) tray is not displayed!!!"
			 * , Status.FAIL); String eMsg =
			 * "SYSTEM: Wait for Response from Neustar (DO NOT SET RESULT) tray is not displayed --- "
			 * ; log.error(eMsg); mstatus = false; }
			 */
		} catch (Exception e) {
			report.reportHardFailEvent("SYSTEM: Wait for Response from Neustar (DO NOT SET RESULT)",
					"SYSTEM: Wait for Response from Neustar (DO NOT SET RESULT) Failed!!!", Status.FAIL);
			String eMsg = "Error in SYSTEM: Wait for Response from Neustar (DO NOT SET RESULT) --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
}
