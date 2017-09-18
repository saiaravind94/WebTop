package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class ReviewMACDBillingImpact extends WebtopPage {

	Logger log = Logger.getLogger(ReviewMACDBillingImpact.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public ReviewMACDBillingImpact(FrameworkContext context) {
		super(context, "ReviewMACDBillingImpact");
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

	public boolean reviewMACDBillingImpact() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.trayName, 2)) {
				iSelectValue(Locators.lstResult, "Accept Bundle Changes");
				iClick(Locators.btnSaveTray, null,
						"Click on Save button in the tray: Review MACD Billing Impact Page:Click on Save button in the tray");
				if (!isLocatorVisible(Locators.trayName, 2)) {
					report.reportPassEvent("CARE: Review MACD Billing Impact",
							"CARE: Review MACD Billing Impact completed successfully!!!");
				} else {
					report.reportHardFailEvent("CARE:  Review MACD Billing Impact",
							"CARE: Review MACD Billing Impact Failed!!!", Status.FAIL);
				}
			}
		} catch (Exception e) {
			report.reportHardFailEvent("CARE: Review MACD Billing Impact",
					"CARE: Review MACD Billing Impact Failed!!!", Status.FAIL);
			String eMsg = "Error in CARE: Review MACD Billing Impact --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
}
