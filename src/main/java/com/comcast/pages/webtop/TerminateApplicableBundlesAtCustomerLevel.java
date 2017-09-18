package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class TerminateApplicableBundlesAtCustomerLevel extends WebtopPage {

	Logger log = Logger.getLogger(TerminateApplicableBundlesAtCustomerLevel.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public TerminateApplicableBundlesAtCustomerLevel(FrameworkContext context) {
		super(context, "TerminateApplicableBundlesAtCustomerLevel");
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

	public boolean terminateApplicableBundlesAtCustomerLevel() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.trayName, 2)) {
				iSelectValue(Locators.lstResult, "Completed");
				iClick(Locators.btnSaveTray, null,
						"Click on Save button in the tray:Terminate Applicable Bundles at Customer Level Page:Click on Save button in the tray");
				if (!isLocatorVisible(Locators.trayName, 1))
					report.reportPassEvent("CARE: Terminate Applicable Bundles at Customer Level",
							"CARE: Accept or Reject TN Deactivation Order (TNs remain on account) completed successfully!!!",
							Status.PASS);
				else
					report.reportHardFailEvent("CARE: Terminate Applicable Bundles at Customer Level",
							"CARE: Accept or Reject TN Deactivation Order (TNs remain on account) Failed!!!",
							Status.FAIL);
			}
		} catch (Exception e) {
			report.reportHardFailEvent("CARE: Terminate Applicable Bundles at Customer Level",
					"CARE: Accept or Reject TN Deactivation Order (TNs remain on account) Failed!!!", Status.FAIL);
			String eMsg = "Error in CARE: Terminate Applicable Bundles at Customer Level --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
}
