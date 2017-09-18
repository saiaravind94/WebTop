package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.pages.webtop.WebTopMainPage.Locators;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class ErrorValidatingOrderBeforeSendingToSNAP extends WebtopPage {

	Logger log = Logger.getLogger(ErrorValidatingOrderBeforeSendingToSNAP.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public ErrorValidatingOrderBeforeSendingToSNAP(FrameworkContext context) {
		super(context, "ErrorValidatingOrderBeforeSendingToSNAP");
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
		public static final String expandFIDInfo = "expandFIDInfo";
		public static final String dropDownEngagementOption = "dropDownEngagementOption";
		public static final String dropDownVoiceGender = "dropDownVoiceGender";
		public static final String btnSaveFIDValues = "btnSaveFIDValues";
	}

	public boolean errorValidatingOrderBeforeSendingToSNAP() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.lstResult, 2)) {
				while (!isLocatorVisible(Locators.dropDownEngagementOption, 2)) {
					iClick(Locators.expandFIDInfo);
				}
				iSelectValue(Locators.dropDownEngagementOption, "Full Engagement");
				iSelectValue(Locators.dropDownEngagementOption, "Male");
				iClick(Locators.btnSaveFIDValues);
				iSelectValue(Locators.lstResult, "Retry validating order before sending to SNAP");
				iClick(Locators.btnSaveTray, null,
						"Click on Save button in the tray:Retry validating order before sending to SNAP Page:Click on Save button in the tray");
				if (!isLocatorVisible(Locators.lstResult, 1))
					report.reportPassEvent("Retry validating order before sending to SNAP",
							"Retry validating order before sending to SNAP completed successfully");
				else
					report.reportHardFailEvent("Retry validating order before sending to SNAP",
							"Retry validating order before sending to SNAP Failed!!!", Status.FAIL);
			}

		} catch (Exception e) {
			report.reportHardFailEvent("Retry validating order before sending to SNAP",
					"Retry validating order before sending to SNAP Failed!!!", Status.FAIL);
			String eMsg = "Error in Retry validating order before sending to SNAP --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
}
