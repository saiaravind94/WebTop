package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class DecommissionESG extends WebtopPage {

	Logger log = Logger.getLogger(DecommissionESG.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public DecommissionESG(FrameworkContext context) {
		super(context, "DecommissionESG");
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

	public boolean decommissionESG() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.trayName, 2)) {
				iSelectValue(Locators.lstResult, "Completed");
				iClick(Locators.btnSaveTray, null,
						"Click on Save button in the tray: Decommission ESG Page:Click on Save button in the tray");
				if (!isLocatorVisible(Locators.trayName, 1))
					report.reportPassEvent("INSTALL TECH: Decommission ESG",
							"INSTALL TECH: Decommission ESG completed successfully!!!", Status.PASS);
				else
					report.reportHardFailEvent("INSTALL TECH: Decommission ESG",
							"INSTALL TECH: Decommission ESG tray is not displayed!!!", Status.FAIL);
			} else {
				report.reportHardFailEvent("INSTALL TECH: Decommission ESG",
						"INSTALL TECH: Decommission ESG tray is not displayed!!!", Status.FAIL);
			}
		} catch (Exception e) {
			report.reportHardFailEvent("INSTALL TECH: Decommission ESG", "INSTALL TECH: Decommission ESG Failed!!!",
					Status.FAIL);
			String eMsg = "Error in INSTALL TECH: Decommission ESG --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
}
