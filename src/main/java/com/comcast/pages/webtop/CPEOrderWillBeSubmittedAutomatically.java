package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class CPEOrderWillBeSubmittedAutomatically extends WebtopPage {

	Logger log = Logger.getLogger(CPEOrderWillBeSubmittedAutomatically.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public CPEOrderWillBeSubmittedAutomatically(FrameworkContext context) {
		super(context, "CPEOrderWillBeSubmittedAutomatically");
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

	public boolean CPE_OrderWillBeSubmittedAutomatically() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.lstResult, 5)) {
				iSelectValue(Locators.lstResult, "Submit Order Automatically");
				iClick(Locators.btnSaveTray, null,
						"Click on Save button in the tray:CPE Order will be submitted automatically Page:Click on Save button in the tray");
				if (!isLocatorVisible(Locators.lstResult, 1))
					report.reportPassEvent("CPE Order will be submitted automatically",
							"CPE Order will be submitted automatically completed successfully");
				else
					report.reportHardFailEvent("CPE Order will be submitted automatically",
							"CPE Order will be submitted automatically Failed!!!", Status.FAIL);
			}

		} catch (Exception e) {
			report.reportHardFailEvent("CPE Order will be submitted automatically",
					"CPE Order will be submitted automatically Failed!!!", Status.FAIL);
			String eMsg = "Error in CPE Order will be submitted automatically --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
}
