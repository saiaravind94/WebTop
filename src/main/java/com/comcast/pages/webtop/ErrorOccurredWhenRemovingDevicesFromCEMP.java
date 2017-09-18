package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class ErrorOccurredWhenRemovingDevicesFromCEMP extends WebtopPage {

	Logger log = Logger.getLogger(ErrorOccurredWhenRemovingDevicesFromCEMP.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public ErrorOccurredWhenRemovingDevicesFromCEMP(FrameworkContext context) {
		super(context, "ErrorOccurredWhenRemovingDevicesFromCEMP");
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

	public boolean errorOccurredWhenRemovingDevicesFromCEMP() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.trayName, 2)) {
				iSelectValue(Locators.lstResult, "Device (TN) Removal from CEMP Performed Manually");
				iClick(Locators.btnSaveTray, null,
						"Click on Save button in the tray:Error Occurred When Removing Devices from CEMP Page:Click on Save button in the tray");
				if (!isLocatorVisible(Locators.trayName, 1))
					report.reportPassEvent("CARE:Error Occurred When Removing Devices from CEMP",
							"CARE: Error Occurred When Removing Devices from CEMP Completed successfully!!!",
							Status.PASS);
				else
					report.reportHardFailEvent("CARE: Error Occurred When Removing Devices from CEMP",
							"CARE: Error Occurred When Removing Devices from CEMP Failed!!!", Status.FAIL);
			}

		} catch (Exception e) {
			report.reportHardFailEvent("CARE: Error Occurred When Removing Devices from CEMP",
					"CARE: Error Occurred When Removing Devices from CEMP Failed!!!", Status.FAIL);
			String eMsg = "Error in CARE: Error Occurred When Removing Devices from CEMP  --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
}
