package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class OrderNewDeviceOrGenerateRMAIfNeeded extends WebtopPage {

	Logger log = Logger.getLogger(OrderNewDeviceOrGenerateRMAIfNeeded.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public OrderNewDeviceOrGenerateRMAIfNeeded(FrameworkContext context) {
		super(context, "OrderNewDeviceOrGenerateRMAIfNeeded");
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

	public boolean orderNewDeviceOrGenerateRMAIfNeeded() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.trayName, 2)) {
				iSelectValue(Locators.lstResult, "Completed");
				iClick(Locators.btnSaveTray, null,
						"Click on Save button in the tray:Order New Device / Generate RMA if needed Page:Click on Save button in the tray");
				if (!isLocatorVisible(Locators.trayName, 1))
					report.reportPassEvent("CARE: Order New Device / Generate RMA if needed",
							"CARE: Order New Device / Generate RMA if needed Completed successfully!!!", Status.PASS);
				else
					report.reportHardFailEvent("CARE: Order New Device / Generate RMA if needed",
							"CARE: Order New Device / Generate RMA if needed Failed!!!", Status.FAIL);
			} else {
				report.reportHardFailEvent("CARE: Order New Device / Generate RMA if needed",
						"CARE: Order New Device / Generate RMA if needed tray is not displayed!!!", Status.FAIL);
			}
		} catch (Exception e) {
			report.reportHardFailEvent("CARE: Order New Device / Generate RMA if needed",
					"CARE: Order New Device / Generate RMA if needed Failed!!!", Status.FAIL);
			String eMsg = "Error in CARE: Order New Device / Generate RMA if needed --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
}
