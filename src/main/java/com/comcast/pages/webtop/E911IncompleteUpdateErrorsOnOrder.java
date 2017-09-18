package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class E911IncompleteUpdateErrorsOnOrder extends WebtopPage {

	Logger log = Logger.getLogger(E911IncompleteUpdateErrorsOnOrder.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public E911IncompleteUpdateErrorsOnOrder(FrameworkContext context) {
		super(context, "E911IncompleteUpdateErrorsOnOrder");
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

	public boolean e911IncompleteUpdateErrorsOnOrder() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.lstResult, 2)) {
				iSelectValue(Locators.lstResult, "E911 Provisioning Completed Manually for ALL TNs on Order");
				iClick(Locators.btnSaveTray, null,
						"Click on Save button in the tray:E-911 Incomplete Update Errors on Order Page:Click on Save button in the tray");
				if (!isLocatorVisible(Locators.lstResult, 1))
					report.reportPassEvent("Provisioning E911 for Applicable TNs",
							"Provisioning E911 for Applicable TNs successfully");
				else
					report.reportHardFailEvent("E-911 Incomplete Update Errors on Order",
							"E-911 Incomplete Update Errors on Order Failed!!!", Status.FAIL);
			}
		} catch (Exception e) {
			report.reportHardFailEvent("E-911 Incomplete Update Errors on Order",
					"E-911 Incomplete Update Errors on Order Failed!!!", Status.FAIL);
			String eMsg = "Error in E-911 Incomplete Update Errors on Order --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
}
