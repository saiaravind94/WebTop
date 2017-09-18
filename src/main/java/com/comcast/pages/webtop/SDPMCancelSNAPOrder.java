package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class SDPMCancelSNAPOrder extends WebtopPage {

	Logger log = Logger.getLogger(SDPMCancelSNAPOrder.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public SDPMCancelSNAPOrder(FrameworkContext context) {
		super(context, "SDPMCancelSNAPOrder");
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
		public static final String lnkSetResultSnap = "lnkSetResultSnap";
		public static final String lnkSnapTray = "lnkSnapTray";
		public static final String btnSave = "btnSave";
	}

	public boolean cancelSNAPOrder() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.lnkSnapTray, 5)) {
				iSelectValue(Locators.lnkSetResultSnap,"Completed");
				iClick(Locators.btnSave, null,
						"Click on Save button in the tray:Cancel SNAP Order Page:Click on Save button in the tray");
				if (!isLocatorVisible(Locators.lnkSnapTray, 1))
					report.reportPassEvent("SDPM:Cancel SNAP Order",
							"SDPM:Cancel SNAP Order completed successfully");
				
			} else {
				report.reportHardFailEvent("SDPM:Cancel SNAP Order",
						"SDPM:Cancel SNAP Order tray is not displayed!!!", Status.FAIL);
				String eMsg = "SDPM:Cancel SNAP Order tray is not displayed--- ";
				log.error(eMsg);
				mstatus = false;
			}
		} catch (Exception e) {
			report.reportHardFailEvent("SDPM:Cancel SNAP Order",
					"SDPM:Cancel SNAP Order Failed!!!", Status.FAIL);
			String eMsg = "SDPM:Cancel SNAP Order Failed!!!--- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
}
