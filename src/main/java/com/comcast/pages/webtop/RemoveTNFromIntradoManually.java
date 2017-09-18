package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class RemoveTNFromIntradoManually extends WebtopPage {

	Logger log = Logger.getLogger(RemoveTNFromIntradoManually.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public RemoveTNFromIntradoManually(FrameworkContext context) {
		super(context, "RemoveTNFromIntradoManually");
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

	public boolean removeTNFromIntradoManually() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.lnkSetResult, 5)) {
				iClick(Locators.lnkSetResult);
				iSelectValue(Locators.drpRslt, "Completed");
				iClick(Locators.drpSetResult, null,
						"Click on Set Result Button button For Remove TN from Intrado manually Tray");
				if (!isLocatorVisible(Locators.txtHistoryTab, 3))
					report.reportPassEvent("Verify if the Remove TN from Intrado manually tray is successfully completed",
							"Verification of Remove TN from Intrado manually is successful");
			}
		} catch (Exception e) {
			report.reportHardFailEvent("Verify if the Remove TN from Intrado manually is successfully completed",
					"Verification of Remove TN from Intrado manually Failed!!!", Status.FAIL);
			String eMsg = "Error in Remove TN from Intrado manually --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
}
