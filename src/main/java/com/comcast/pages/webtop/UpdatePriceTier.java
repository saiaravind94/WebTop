package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class UpdatePriceTier extends WebtopPage {

	Logger log = Logger.getLogger(UpdatePriceTier.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public UpdatePriceTier(FrameworkContext context) {
		super(context, "UpdatePriceTier");
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

	public boolean updatePriceTier() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.trayName, 2)) {
				iSelectValue(Locators.lstResult, "Completed");
				iClick(Locators.btnSaveTray, null,
						"Click on Save button in the tray:BILLING: Update Price Tier Page:Click on Save button in the tray");
				if (!isLocatorVisible(Locators.trayName, 2)) {
					report.reportPassEvent("BILLING: Update Price Tier",
							"BILLING: Update Price Tier completed successfully!!!");
				} else {
					report.reportHardFailEvent("BILLING: Update Price Tier", "BILLING: Update Price Tier Failed!!!",
							Status.FAIL);
				}
			}
		} catch (Exception e) {
			report.reportHardFailEvent("BILLING: Update Price Tier", "BILLING: Update Price Tier Failed!!!",
					Status.FAIL);
			String eMsg = "Error in BILLING: Update Price Tier --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
}
