package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class ErrorProvisioningE911ForApplicableTNs extends WebtopPage {

	Logger log = Logger.getLogger(ErrorProvisioningE911ForApplicableTNs.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public ErrorProvisioningE911ForApplicableTNs(FrameworkContext context) {
		super(context, "ErrorProvisioningE911ForApplicableTNs");
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

	public boolean errorProvisioningE911ForApplicableTNs() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.lstResult, 2)) {
				iSelectValue(Locators.lstResult, "Retry Provisioning E911 for Applicable TNs");
				iClick(Locators.btnSaveTray, null,
						"Click on Save button in the tray:Provisioning E911 for Applicable TNs Page:Click on Save button in the tray");
				if (!isLocatorVisible(Locators.lstResult, 1))
					report.reportPassEvent("Provisioning E911 for Applicable TNs",
							"Provisioning E911 for Applicable TNs successfully");
				else
					report.reportHardFailEvent("Provisioning E911 for Applicable TNs",
							"Provisioning E911 for Applicable TNs Failed!!!", Status.FAIL);
			}
		} catch (Exception e) {
			report.reportHardFailEvent("Provisioning E911 for Applicable TNs",
					"Provisioning E911 for Applicable TNs Failed!!!", Status.FAIL);
			String eMsg = "Error in Provisioning E911 for Applicable TNs --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
}
