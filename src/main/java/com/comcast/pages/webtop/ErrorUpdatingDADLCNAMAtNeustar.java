package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class ErrorUpdatingDADLCNAMAtNeustar extends WebtopPage {

	Logger log = Logger.getLogger(ErrorUpdatingDADLCNAMAtNeustar.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public ErrorUpdatingDADLCNAMAtNeustar(FrameworkContext context) {
		super(context, "ErrorUpdatingDADLCNAMAtNeustar");
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

	public boolean errorUpdatingDADLCNAMAtNeustar() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.lstResult, 5)) {
				iSelectValue(Locators.lstResult, "Update Manually");
				iClick(Locators.btnSave, null,
						"Click on Save button in the tray:Error Updating DA/DL/CNAM at Neustar Page:Click on Save button in the tray");
				if (!isLocatorVisible(Locators.lstResult, 1))
					report.reportPassEvent("Error Updating DA/DL/CNAM at Neustar",
							"Error Updating DA/DL/CNAM at Neustar completed successfully");
				else
					report.reportHardFailEvent("Error Updating DA/DL/CNAM at Neustar",
							"Error Updating DA/DL/CNAM at Neustar Failed!!!", Status.FAIL);
			}
		} catch (Exception e) {
			report.reportHardFailEvent("Error Updating DA/DL/CNAM at Neustar",
					"Error Updating DA/DL/CNAM at Neustar Failed!!!", Status.FAIL);
			String eMsg = "Error Updating DA/DL/CNAM at Neustar --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
}
