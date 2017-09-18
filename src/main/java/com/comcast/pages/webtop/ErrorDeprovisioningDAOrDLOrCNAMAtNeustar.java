package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class ErrorDeprovisioningDAOrDLOrCNAMAtNeustar extends WebtopPage {

	Logger log = Logger.getLogger(ErrorDeprovisioningDAOrDLOrCNAMAtNeustar.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public ErrorDeprovisioningDAOrDLOrCNAMAtNeustar(FrameworkContext context) {
		super(context, "ErrorDeprovisioningDAOrDLOrCNAMAtNeustar");
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

	public boolean errorDeprovisioningDAOrDLOrCNAMAtNeustar() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.lstResult, 5)) {
				iSelectValue(Locators.lstResult, "Deprovision Manually");
				iClick(Locators.btnSave, null,
						"Click on Save button in the tray:Error Deprovisioning DA/DL/CNAM at Neustar Page:Click on Save button in the tray");
				if (!isLocatorVisible(Locators.lstResult, 1))
					report.reportPassEvent("ADMIN: Error Deprovisioning DA/DL/CNAM at Neustar",
							"ADMIN: Error Deprovisioning DA/DL/CNAM at Neustar completed successfully");
				else
					report.reportHardFailEvent("ADMIN: Error Deprovisioning DA/DL/CNAM at Neustar",
							"ADMIN: Error Deprovisioning DA/DL/CNAM at Neustar Failed!!!", Status.FAIL);
			}
		} catch (Exception e) {
			report.reportHardFailEvent("ADMIN: Error Deprovisioning DA/DL/CNAM at Neustar",
					"ADMIN: Error Deprovisioning DA/DL/CNAM at Neustar Failed!!!", Status.FAIL);
			String eMsg = "Error in ADMIN: Error Deprovisioning DA/DL/CNAM at Neustar --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
}
