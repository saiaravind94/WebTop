package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class ErrorProvisioningDAOrDLOrCNAMatNeustar extends WebtopPage {

	Logger log = Logger.getLogger(ErrorProvisioningDAOrDLOrCNAMatNeustar.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public ErrorProvisioningDAOrDLOrCNAMatNeustar(FrameworkContext context) {
		super(context, "ErrorProvisioningDAOrDLOrCNAMatNeustar");
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

	public boolean errorProvisioningDAOrDLOrCNAMatNeustar() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.lstResult, 5)) {
				iSelectValue(Locators.lstResult, "Provision Manually");
				iClick(Locators.btnSave, null,
						"Click on Save button in the tray:Error Provisioning DA/DL/CNAM at Neustar Page:Click on Save button in the tray");
				if (!isLocatorVisible(Locators.lstResult, 1))
					report.reportPassEvent("Error Provisioning DA/DL/CNAM at Neustar",
							"Error Provisioning DA/DL/CNAM at Neustar completed successfully");
				else
					report.reportHardFailEvent("Error Provisioning DA/DL/CNAM at Neustar",
							"Error Provisioning DA/DL/CNAM at Neustar Failed!!!", Status.FAIL);
			}
		} catch (Exception e) {
			report.reportHardFailEvent("Error Provisioning DA/DL/CNAM at Neustar",
					"Error Provisioning DA/DL/CNAM at Neustar Failed!!!", Status.FAIL);
			String eMsg = "Error in Error Provisioning DA/DL/CNAM at Neustar --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
}
