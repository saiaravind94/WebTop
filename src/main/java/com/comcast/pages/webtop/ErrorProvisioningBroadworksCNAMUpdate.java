package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class ErrorProvisioningBroadworksCNAMUpdate extends WebtopPage {

	Logger log = Logger.getLogger(ErrorProvisioningBroadworksCNAMUpdate.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public ErrorProvisioningBroadworksCNAMUpdate(FrameworkContext context) {
		super(context, "ErrorProvisioningBroadworksCNAMUpdate");
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

	public boolean errorProvisioningBroadworksCNAMUpdate() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.lstResult, 5)) {
				iSelectValue(Locators.lstResult, "Retry Provisioning CNAM Update in Broadworks");
				iClick(Locators.btnSave, null,
						"Click on Save button in the tray:Error Provisioning Broadworks CNAM Update Page:Click on Save button in the tray");
				if (!isLocatorVisible(Locators.lstResult, 1))
					report.reportPassEvent("Error Provisioning Broadworks CNAM Update",
							"Error Provisioning Broadworks CNAM Update completed successfully");
				else
					report.reportHardFailEvent("Error Provisioning Broadworks CNAM Update",
							"Error Provisioning Broadworks CNAM Update Failed!!!", Status.FAIL);
			}
		} catch (Exception e) {
			report.reportHardFailEvent("Error Provisioning Broadworks CNAM Update",
					"Error Provisioning Broadworks CNAM Update Failed!!!", Status.FAIL);
			String eMsg = "Error Provisioning Broadworks CNAM Update --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
}
