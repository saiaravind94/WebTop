package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class ReviewAndCorrectSFID extends WebtopPage {

	Logger log = Logger.getLogger(ReviewAndCorrectSFID.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public ReviewAndCorrectSFID(FrameworkContext context) {
		super(context, "ReviewAndCorrectSFID");
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
		public static final String lnkReviewAndCorrectSFID = "lnkReviewAndCorrectSFID";
		public static final String btnSave = "btnSave";
	}

	public boolean reviewAndCorrectSFID() {
		mstatus = true;
		try {
			if(!isLocatorVisible(Locators.lnkReviewAndCorrectSFID, 1))
			{
				iClick(Locators.tabHistory);
			}
			if (isLocatorVisible(Locators.lnkReviewAndCorrectSFID, 1)) {
				iClick(Locators.lnkReviewAndCorrectSFID, null, "Click on Review and Correct SFID tray");
				iClick(Locators.btnSave, null, "Click on Save button");
				if (!isLocatorVisible(Locators.lnkReviewAndCorrectSFID, 1))
					report.reportPassEvent("Review and Correct SFID  Tray",
							"Review and Correct SFID  completed successfully");
				else
					report.reportHardFailEvent("Review and Correct SFID ", "Review and Correct SFID  Failed!!!",
							Status.FAIL);
			}
		} catch (Exception e) {
			mstatus = false;
			report.reportHardFailEvent("Review and Correct SFID ", "Review and Correct SFID  Failed!!!",
					Status.FAIL);
			String eMsg = "Error in Review and Correct SFID  --- " + e.getMessage();
			log.error(eMsg);
		}
		return mstatus;
	}

}
