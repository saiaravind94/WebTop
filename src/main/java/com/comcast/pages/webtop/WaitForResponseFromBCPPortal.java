package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class WaitForResponseFromBCPPortal extends WebtopPage {

	Logger log = Logger.getLogger(WaitForResponseFromBCPPortal.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public WaitForResponseFromBCPPortal(FrameworkContext context) {
		super(context, "WaitForResponseFromBCPPortal");
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
		public static final String lnkWaitForResponseFromBCPPortal = "lnkWaitForResponseFromBCPPortal";
		public static final String lstResult = "lstResult";
		public static final String btnSaveTray = "btnSaveTray";
	}

	public boolean waitForResponseFromBCPPortal() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.lstResult, 5)) {
				iSelectValue(Locators.lstResult, "Identities Provisioned Manually in BCP Portal");
				iClick(Locators.btnSaveTray, null,
						"Click on Save button in the tray: Wait for Response from BCP Portal Page:Click on Save button in the tray");
				if (!isLocatorVisible(Locators.lstResult, 1))
					report.reportPassEvent("SYSTEM: Wait for Response from BCP Portal",
							"SYSTEM: Wait for Response from BCP Portal completed successfully");
				else
					report.reportHardFailEvent("SYSTEM: Wait for Response from BCP Portal",
							"SYSTEM: Wait for Response from BCP Portal Failed!!!", Status.FAIL);
			}
		} catch (Exception e) {
			report.reportHardFailEvent("SYSTEM: Wait for Response from BCP Portal",
					"SYSTEM: Wait for Response from BCP Portal Failed!!!", Status.FAIL);
			String eMsg = "Error in SYSTEM: Wait for Response from BCP Portal --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
}
