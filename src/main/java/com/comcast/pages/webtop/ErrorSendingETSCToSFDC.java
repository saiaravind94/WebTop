package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class ErrorSendingETSCToSFDC extends WebtopPage {

	Logger log = Logger.getLogger(ErrorSendingETSCToSFDC.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public ErrorSendingETSCToSFDC(FrameworkContext context) {
		super(context, "ErrorSendingETSCToSFDC");
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

	public boolean errorSendingETSCToSFDC() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.trayName, 2)) {
				iSelectValue(Locators.lstResult, "Bypass - ETSC Sent To SFDC Manually");
				iClick(Locators.btnSaveTray, null,
						"Click on Save button in the tray:Error Sending ETSC To SFDC Page:Click on Save button in the tray");
				if (!isLocatorVisible(Locators.trayName, 2)) {
					report.reportPassEvent("Error Sending ETSC To SFDC",
							"Error Sending ETSC To SFDC completed successfully!!!");
				} else {
					report.reportHardFailEvent("Error Sending ETSC To SFDC",
							"Error Sending ETSC To SFDC Failed!!!",
							Status.FAIL);
				}
			}
		} catch (Exception e) {
			report.reportHardFailEvent("Error Sending ETSC To SFDC",
					"Error Sending ETSC To SFDC Failed!!!", Status.FAIL);
			String eMsg = "Error in Error Sending ETSC To SFDC --- "
					+ e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
}
