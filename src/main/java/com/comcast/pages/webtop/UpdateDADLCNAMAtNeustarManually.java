package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class UpdateDADLCNAMAtNeustarManually extends WebtopPage {

	Logger log = Logger.getLogger(UpdateDADLCNAMAtNeustarManually.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public UpdateDADLCNAMAtNeustarManually(FrameworkContext context) {
		super(context, "UpdateDADLCNAMAtNeustarManually");
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

	public boolean updateDADLCNAMAtNeustarManually() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.lstResult, 5)) {
				iSelectValue(Locators.lstResult, "Completed");
				iClick(Locators.btnSave, null,
						"Click on Save button in the tray:Update DA/DL/CNAM at Neustar Manually	 Page:Click on Save button in the tray");
				if (!isLocatorVisible(Locators.lstResult, 1))
					report.reportPassEvent("Update DA/DL/CNAM at Neustar Manually	",
							"Update DA/DL/CNAM at Neustar Manually	 completed successfully");
				else
					report.reportHardFailEvent("Error Updating DA/DL/CNAM at Neustar",
							"Update DA/DL/CNAM at Neustar Manually	 Failed!!!", Status.FAIL);
			}
		} catch (Exception e) {
			report.reportHardFailEvent("Update DA/DL/CNAM at Neustar Manually	",
					"Update DA/DL/CNAM at Neustar Manually	 Failed!!!", Status.FAIL);
			String eMsg = "Update DA/DL/CNAM at Neustar Manually	 --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
}
