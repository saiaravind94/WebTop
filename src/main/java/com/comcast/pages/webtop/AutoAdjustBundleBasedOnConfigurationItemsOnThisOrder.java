package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class AutoAdjustBundleBasedOnConfigurationItemsOnThisOrder extends WebtopPage {

	Logger log = Logger.getLogger(AutoAdjustBundleBasedOnConfigurationItemsOnThisOrder.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public AutoAdjustBundleBasedOnConfigurationItemsOnThisOrder(FrameworkContext context) {
		super(context, "AutoAdjustBundleBasedOnConfigurationItemsOnThisOrder");
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

	public boolean autoAdjustBundleBasedOnConfigurationItemsOnThisOrder() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.trayName, 2)) {
				iSelectValue(Locators.lstResult, "Yes");
				iClick(Locators.btnSaveTray, null,
						"Click on Save button in the tray: Auto-Adjust Bundle Based On Configuration Items On This Order Page:Click on Save button in the tray");
				if (!isLocatorVisible(Locators.trayName, 2)) {
					report.reportPassEvent("CARE:  Auto-Adjust Bundle Based On Configuration Items On This Order",
							"CARE:  Auto-Adjust Bundle Based On Configuration Items On This Order completed successfully!!!");
				} else {
					report.reportHardFailEvent("CARE:  Auto-Adjust Bundle Based On Configuration Items On This Order",
							"CARE:  Auto-Adjust Bundle Based On Configuration Items On This Order Failed!!!",
							Status.FAIL);
				}
			}
		} catch (Exception e) {
			report.reportHardFailEvent("CARE:  Auto-Adjust Bundle Based On Configuration Items On This Order",
					"CARE:  Auto-Adjust Bundle Based On Configuration Items On This Order Failed!!!", Status.FAIL);
			String eMsg = "Error in CARE:  Auto-Adjust Bundle Based On Configuration Items On This Order --- "
					+ e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
}
