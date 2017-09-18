package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class WaitingForSNAPFileUploadStatusNotification extends WebtopPage {

	Logger log = Logger.getLogger(WaitingForSNAPFileUploadStatusNotification.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public WaitingForSNAPFileUploadStatusNotification(FrameworkContext context) {
		super(context, "WaitingForSNAPFileUploadStatusNotification");
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
		public static final String btnSave = "btnSave";
		public static final String lnkTrayName = "lnkTrayName";
	}

	public boolean waitingForSNAPFileUploadStatusNotification() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.lstResult, 5)) {
				iSelectValue(Locators.lstResult, "Files Uploaded");
				iClick(Locators.btnSave, null,
						"Click on Save button in the tray:Waiting for SNAP File Upload Status Notification (will auto close) Page:Click on Save button in the tray");
				if (!isLocatorVisible(Locators.lstResult, 1))
					report.reportPassEvent("SYSTEM: Waiting for SNAP File Upload Status Notification (will auto close)",
							"SYSTEM: Waiting for SNAP File Upload Status Notification (will auto close)completed successfully");
				else
					report.reportHardFailEvent(
							"SYSTEM: Waiting for SNAP File Upload Status Notification (will auto close)",
							"SYSTEM: Waiting for SNAP File Upload Status Notification (will auto close) Failed!!!",
							Status.FAIL);
			}

		} catch (Exception e) {
			report.reportHardFailEvent("SYSTEM: Waiting for SNAP File Upload Status Notification (will auto close)",
					"SYSTEM: Waiting for SNAP File Upload Status Notification (will auto close) Failed!!!",
					Status.FAIL);
			String eMsg = "Error in SYSTEM: Waiting for SNAP File Upload Status Notification (will auto close) --- "
					+ e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}

	public boolean verifyWaitingForSNAPFileUploadStatusNotification() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.lnkTrayName, 2)) {
				report.reportPassEvent("Tray Verification for SYSTEM: Waiting for SNAP File Upload Status Notification (will auto close)",
						"SYSTEM: Waiting for SNAP File Upload Status Notification (will auto close) is Displayed");
				}
			else{
				report.reportHardFailEvent("Tray Verification for SYSTEM: Waiting for SNAP File Upload Status Notification (will auto close)", "SYSTEM: Waiting for SNAP File Upload Status Notification (will auto close) tray is not displayed");
			}

		} catch (Exception e) {
			report.reportHardFailEvent("SYSTEM: Waiting for SNAP File Upload Status Notification (will auto close)",
					"SYSTEM: Waiting for SNAP File Upload Status Notification (will auto close) Failed!!!",
					Status.FAIL);
			String eMsg = "Error in SYSTEM: Waiting for SNAP File Upload Status Notification (will auto close) --- "
					+ e.getMessage();
			log.error(eMsg);

		}
		return mstatus = false;
	}
}
