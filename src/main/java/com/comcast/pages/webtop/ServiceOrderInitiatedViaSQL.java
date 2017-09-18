package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class ServiceOrderInitiatedViaSQL extends WebtopPage {

	Logger log = Logger.getLogger(ServiceOrderInitiatedViaSQL.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public ServiceOrderInitiatedViaSQL(FrameworkContext context) {
		super(context, "ServiceOrderInitiatedViaSQL");
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

	public boolean serviceOrderInitiatedViaSQL() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.trayName, 2)) {
				iSelectValue(Locators.lstResult, "Begin Workflow");
				iClick(Locators.btnSaveTray, null,
						"Click on Save button in the tray:Service Order Initiated Via SQL (will close automatically) Page:Click on Save button in the tray");
				if (!isLocatorVisible(Locators.trayName, 2)) {
					report.reportPassEvent("Service Order Initiated Via SQL (will close automatically)",
							"Service Order Initiated Via SQL (will close automatically) completed successfully!!!");
				} else {
					report.reportHardFailEvent("Service Order Initiated Via SQL (will close automatically)",
							"Service Order Initiated Via SQL (will close automatically) Failed!!!", Status.FAIL);
				}
			}
		} catch (Exception e) {
			report.reportHardFailEvent("Service Order Initiated Via SQL (will close automatically)",
					"Service Order Initiated Via SQL (will close automatically) Failed!!!", Status.FAIL);
			String eMsg = "Error in Service Order Initiated Via SQL (will close automatically) --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
}
