package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class OnDOI_ContinueToProvisioningOfDeviceSwap extends WebtopPage {

	Logger log = Logger.getLogger(OnDOI_ContinueToProvisioningOfDeviceSwap.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public OnDOI_ContinueToProvisioningOfDeviceSwap(FrameworkContext context) {
		super(context, "OnDOI_ContinueToProvisioningOfDeviceSwap");
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

	public boolean onDOI_ContinueToProvisioningOfDeviceSwap() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.lstResult, 5)) {
				iSelectValue(Locators.lstResult, "Continue to Broadworks Provisiong for Device Swap");
				iClick(Locators.btnSave, null,
						"Click on Save button in the tray:On DOI - Continue to Provisioning of Device Swap Page:Click on Save button in the tray");
				if (!isLocatorVisible(Locators.lstResult, 1))
					report.reportPassEvent("CARE: On DOI - Continue to Provisioning of Device Swap",
							"CARE: On DOI - Continue to Provisioning of Device Swap completed successfully");
				else
					report.reportHardFailEvent("CARE: On DOI - Continue to Provisioning of Device Swap",
							"CARE: On DOI - Continue to Provisioning of Device Swap Failed!!!", Status.FAIL);
			}
		} catch (Exception e) {
			report.reportHardFailEvent("CARE: On DOI - Continue to Provisioning of Device Swap",
					"CARE: On DOI - Continue to Provisioning of Device Swap Failed!!!", Status.FAIL);
			String eMsg = "Error in CARE: On DOI - Continue to Provisioning of Device Swap --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
}
