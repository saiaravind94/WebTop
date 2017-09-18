package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class DeprovisionDAOrDLOrCNAMAtNeustarManually extends WebtopPage {

	Logger log = Logger.getLogger(DeprovisionDAOrDLOrCNAMAtNeustarManually.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public DeprovisionDAOrDLOrCNAMAtNeustarManually(FrameworkContext context) {
		super(context, "DeprovisionDAOrDLOrCNAMAtNeustarManually");
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

	public boolean deprovisionDAOrDLOrCNAMAtNeustarManually() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.lstResult, 5)) {
				iSelectValue(Locators.lstResult, "Completed");
				iClick(Locators.btnSave, null,
						"Click on Save button in the tray:Deprovision DA/DL/CNAM at Neustar Manually Page:Click on Save button in the tray");
				if (!isLocatorVisible(Locators.lstResult, 1))
					report.reportPassEvent("Provision DA/DL/CNAM at Neustar Manually",
							"Deprovision DA/DL/CNAM at Neustar Manually completed successfully");
				else
					report.reportHardFailEvent("Deprovision DA/DL/CNAM at Neustar Manually",
							"Deprovision DA/DL/CNAM at Neustar Manually Failed!!!", Status.FAIL);
			}
		} catch (Exception e) {
			report.reportHardFailEvent("Deprovision DA/DL/CNAM at Neustar Manually",
					"Deprovision DA/DL/CNAM at Neustar Manually Failed!!!", Status.FAIL);
			String eMsg = "Error in Deprovision DA/DL/CNAM at Neustar Manually --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
}
