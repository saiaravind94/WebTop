package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class RemoveNecessaryRecordsfromBroadworks extends WebtopPage {

	Logger log = Logger.getLogger(RemoveNecessaryRecordsfromBroadworks.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public RemoveNecessaryRecordsfromBroadworks(FrameworkContext context) {
		super(context, "RemoveNecessaryRecordsfromBroadworks");
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
		public static final String lnkSetResult = "lnkSetResult";
		public static final String drpRslt = "drpRslt";
		public static final String drpSetResult = "drpSetResult";
		public static final String txtHistoryTab = "txtHistoryTab";		
	}

	public boolean removeNecessaryRecordsFromBroadworks() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.lnkSetResult, 5)) {
				iClick(Locators.lnkSetResult);
				iSelectValue(Locators.drpRslt, "Completed");
				iClick(Locators.drpSetResult, null,
						"Click on Set Result Button button For Remove Necessary Records from Broadworks Tray");
				if (!isLocatorVisible(Locators.txtHistoryTab, 3))
					report.reportPassEvent("Verify if the Remove Necessary Records from Broadworks Tray is successfully completed",
							"Verification of Remove Necessary Records from Broadworks Tray is successful");
			}
		} catch (Exception e) {
			report.reportHardFailEvent("Verify if the Remove Necessary Records from Broadworks Tray is successfully completed",
					"Verify if the Remove Necessary Records from Broadworks Tray is successfully completed- Failed!!!", Status.FAIL);
			String eMsg = "Error in Remove Necessary Records from Broadworks Tray --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
}
