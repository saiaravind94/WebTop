package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class PerformCPEInstallation_DOI extends WebtopPage {

	Logger log = Logger.getLogger(PerformCPEInstallation_DOI.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public PerformCPEInstallation_DOI(FrameworkContext context) {
		super(context, "PerformCPEInstallation_DOI");
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

	public boolean performCPEInstallation_DOI(String status) {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.lstResult, 5)) {
				iSelectValue(Locators.lstResult, status);
				iClick(Locators.btnSaveTray, null,
						"Click on Save button in the tray:INSTALL TECH: Perform CPE Installation (DOI) Page:Click on Save button in the tray");
				if (!isLocatorVisible(Locators.lstResult, 1))
					report.reportPassEvent("INSTALL TECH: Perform CPE Installation (DOI)",
							"INSTALL TECH: Perform CPE Installation (DOI) completed successfully");
				else
					report.reportHardFailEvent("INSTALL TECH: Perform CPE Installation (DOI)",
							"INSTALL TECH: Perform CPE Installation (DOI) Failed!!!", Status.FAIL);
			} else {
				report.reportHardFailEvent("INSTALL TECH: Perform CPE Installation (DOI)",
						"INSTALL TECH: Perform CPE Installation (DOI) tray is not displayed!!!", Status.FAIL);
				String eMsg = "INSTALL TECH: Perform CPE Installation (DOI) tray is not displayed--- ";
				log.error(eMsg);
				mstatus = false;
			}
		} catch (Exception e) {
			report.reportHardFailEvent("INSTALL TECH: Perform CPE Installation (DOI)",
					"INSTALL TECH: Perform CPE Installation (DOI) Failed!!!", Status.FAIL);
			String eMsg = "Error in INSTALL TECH: Perform CPE Installation (DOI) --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
}
