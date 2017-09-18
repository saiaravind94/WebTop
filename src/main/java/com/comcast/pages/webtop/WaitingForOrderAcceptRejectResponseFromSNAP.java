package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class WaitingForOrderAcceptRejectResponseFromSNAP extends WebtopPage {

	Logger log = Logger.getLogger(WaitingForOrderAcceptRejectResponseFromSNAP.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public WaitingForOrderAcceptRejectResponseFromSNAP(FrameworkContext context) {
		super(context, "WaitingForOrderAcceptRejectResponseFromSNAP");
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
	}

	public boolean waitingForOrderAcceptRejectResponseFromSNAP() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.lstResult, 5)) {
				iSelectValue(Locators.lstResult, "Accepted");
				iClick(Locators.btnSave, null,
						"Click on Save button in the tray:Waiting for Order Accept/Reject Response from SNAP (will auto close) Page:Click on Save button in the tray");
				if (!isLocatorVisible(Locators.lstResult, 1))
					report.reportPassEvent(
							"SYSTEM: Waiting for Order Accept/Reject Response from SNAP (will auto close)",
							"SYSTEM: Waiting for Order Accept/Reject Response from SNAP (will auto close) completed successfully");
				else
					report.reportHardFailEvent(
							"SYSTEM: Waiting for Order Accept/Reject Response from SNAP (will auto close)",
							"SYSTEM: Waiting for Order Accept/Reject Response from SNAP (will auto close) Failed!!!",
							Status.FAIL);
			}

		} catch (Exception e) {
			report.reportHardFailEvent("SYSTEM: Waiting for Order Accept/Reject Response from SNAP (will auto close)",
					"SYSTEM: Waiting for Order Accept/Reject Response from SNAP (will auto close) Failed!!!",
					Status.FAIL);
			String eMsg = "Error in SYSTEM: Waiting for Order Accept/Reject Response from SNAP (will auto close) --- "
					+ e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
}
