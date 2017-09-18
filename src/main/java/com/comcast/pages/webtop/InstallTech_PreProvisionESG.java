package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class InstallTech_PreProvisionESG extends WebtopPage {

	Logger log = Logger.getLogger(InstallTech_PreProvisionESG.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public InstallTech_PreProvisionESG(FrameworkContext context) {
		super(context, "InstallTech_PreProvisionESG");
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
		public static final String btnSaveTray = "btnSaveTray";
	}

	public boolean installTechPreProvisionESG() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.lstResult, 5)) {
				iSelectValue(Locators.lstResult, "Completed");
				iClick(Locators.btnSaveTray, null,
						"Click on Save button in the tray:INSTALL TECH: Pre-Provision ESG Page:Click on Save button in the tray");
				if (!isLocatorVisible(Locators.lstResult, 1))
					report.reportPassEvent("INSTALL TECH: Pre-Provision ESG",
							"INSTALL TECH: Pre-Provision ESG completed successfully");
				else
					report.reportHardFailEvent("INSTALL TECH: Pre-Provision ESG",
							"INSTALL TECH: Pre-Provision ESG Failed!!!", Status.FAIL);
			} else {
				report.reportHardFailEvent("INSTALL TECH: Pre-Provision ESG",
						"INSTALL TECH: Pre-Provision ESG tray is not displayed!!!", Status.FAIL);
				String eMsg = "INSTALL TECH: Pre-Provision ESG tray is not displayed --- ";
				log.error(eMsg);
				mstatus = false;
			}
		} catch (Exception e) {
			report.reportHardFailEvent("INSTALL TECH: Pre-Provision ESG", "INSTALL TECH: Pre-Provision ESG Failed!!!",
					Status.FAIL);
			String eMsg = "Error in INSTALL TECH: Pre-Provision ESG --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
}
