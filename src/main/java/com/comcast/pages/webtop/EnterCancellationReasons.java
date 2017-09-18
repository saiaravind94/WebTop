package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class EnterCancellationReasons extends WebtopPage {

	Logger log = Logger.getLogger(EnterCancellationReasons.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public EnterCancellationReasons(FrameworkContext context) {
		super(context, "EnterCancellationReasons");
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
		public static final String txtareaCancellationReason = "txtareaCancellationReason";
		public static final String drpCancelIntent = "drpCancelIntent";
		public static final String txtHistoryTab = "txtHistoryTab";
		public static final String btnSave = "btnSave";
		
	}

	public boolean cancelOrderandEnterCancellationReason() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.lnkSetResult, 5)) {
				iClick(Locators.lnkSetResult);
				iEnterText(Locators.txtareaCancellationReason, "Canceling Order", "Enter the Text giving the Cancellation Reason");
				iSelectValue(Locators.drpCancelIntent, "Canceled Order");
				iClick(Locators.btnSave, null,
						"Click on Save button in the tray:INSTALL TECH: Perform CPE Installation (DOI) Page:Click on Save button in the tray");
				if (!isLocatorVisible(Locators.txtHistoryTab, 3))
					report.reportPassEvent("verify if the Cancelling Order is successful",
							"Cancelling Order is successful");
			}
		} catch (Exception e) {
			report.reportHardFailEvent("verify if the Cancelling Order is successful",
					"verify if the Cancelling Order is successful Failed!!!", Status.FAIL);
			String eMsg = "Error in INSTALL TECH: Perform CPE Installation (DOI) --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
}
