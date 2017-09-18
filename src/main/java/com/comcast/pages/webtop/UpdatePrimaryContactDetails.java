package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class UpdatePrimaryContactDetails extends WebtopPage {

	Logger log = Logger.getLogger(UpdatePrimaryContactDetails.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public UpdatePrimaryContactDetails(FrameworkContext context) {
		super(context, "UpdatePrimaryContactDetails");
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

	public boolean updatePrimaryContactDetails() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.trayName, 2)) {
				iSelectValue(Locators.lstResult, "Successful");
				iClick(Locators.btnSaveTray, null,
						"Click on Save button in the tray:Update Primary Contact Details Page:Click on Save button in the tray");
				if (!isLocatorVisible(Locators.trayName, 2)) {
					report.reportPassEvent("CARE: Update Primary Contact Details",
							"CARE: Update Primary Contact Details completed successfully!!!");
				} else {
					report.reportHardFailEvent("CARE: Update Primary Contact Details",
							"CARE: Update Primary Contact Details Failed!!!", Status.FAIL);
				}
			}
		} catch (Exception e) {
			report.reportHardFailEvent("CARE: Update Primary Contact Details",
					"CARE: Update Primary Contact Details Failed!!!", Status.FAIL);
			String eMsg = "Error in CARE: Update Primary Contact Details --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
}
