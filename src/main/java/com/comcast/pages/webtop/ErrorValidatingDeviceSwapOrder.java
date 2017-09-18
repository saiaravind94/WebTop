package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class ErrorValidatingDeviceSwapOrder extends WebtopPage {

	Logger log = Logger.getLogger(ErrorValidatingDeviceSwapOrder.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public ErrorValidatingDeviceSwapOrder(FrameworkContext context) {
		super(context, "ErrorValidatingDeviceSwapOrder");
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
		public static final String btnDeleteorder = "btnDeleteorder";
	}

	public boolean verifyIfTrayIsDisplayed() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.trayName, 2)) {				
					report.reportPassEvent("CARE: Error Validating Device Swap Order",
							"CARE: Error Validating Device Swap Order completed successfully!!!");				
			} else {
				report.reportHardFailEvent("CARE: Error Validating Device Swap Order",
						"CARE: Error Validating Device Swap Order tray is not displayed!!!",
						Status.FAIL);
			}
		} catch (Exception e) {
			report.reportHardFailEvent("CARE: Error Validating Device Swap Order",
					"CARE: Error Validating Device Swap Order Failed!!!", Status.FAIL);
			String eMsg = "Error in CARE: Error Validating Device Swap Order --- "
					+ e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean deleteOrder() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.btnDeleteorder, 2)) {
				iClick(Locators.btnDeleteorder);
			} else {
				report.reportHardFailEvent("Deleting the Order", "Error Deleting the Order!!!", Status.FAIL);
			}
		} catch (Exception e) {
			report.reportHardFailEvent("Deleting the Order", "Error Deleting the Order!!!", Status.FAIL);
			String eMsg = "Error in Deleting the Order --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;

	}

}
