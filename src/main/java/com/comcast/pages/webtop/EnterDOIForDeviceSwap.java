package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class EnterDOIForDeviceSwap extends WebtopPage {

	Logger log = Logger.getLogger(EnterDOIForDeviceSwap.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public EnterDOIForDeviceSwap(FrameworkContext context) {
		super(context, "EnterDOIForDeviceSwap");
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
		public static final String lnkEnterDOIForDeviceSwap = "lnkEnterDOIForDeviceSwap";
		public static final String txtDOI = "txtDOI";
		public static final String btnSave = "btnSave";
	}

	public boolean enterDOIForDeviceSwap() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.lnkEnterDOIForDeviceSwap, 2)) {
				// iClick(Locators.tabHistory, null, "Click on History Tab");
				iClick(Locators.lnkEnterDOIForDeviceSwap, null, "Click on Enter DOI For Device swap Tray");
				iEnterText(Locators.txtDOI, getCurrentDateTime_Operation("mmddyyyy_timestamp", "plus", 1, 0, true),
						"Enter DOC");
				iClick(Locators.btnSave, null, "Click on Save button");
				if (!isLocatorVisible(Locators.lnkEnterDOIForDeviceSwap, 1))
					report.reportPassEvent("Enter DOI For Device SWAP Tray",
							"Enter DOI For Device SWAP completed successfully");
				else
					report.reportHardFailEvent("Enter DOI For Device SWAP", "Enter DOI For Device SWAP Failed!!!",
							Status.FAIL);
			} else {
				report.reportHardFailEvent("Enter DOI For Device SWAP Tray",
						"Enter DOI For Device SWAP tray is not displayed!!!", Status.FAIL);
			}
		} catch (Exception e) {
			mstatus = false;
			report.reportHardFailEvent("Enter DOI For Device SWAP", "Enter DOI For Device SWAP Failed!!!", Status.FAIL);
			String eMsg = "Error in Enter DOI For Device SWAP --- " + e.getMessage();
			log.error(eMsg);
		}
		return mstatus;
	}

}
