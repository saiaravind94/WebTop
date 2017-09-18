package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class ErrorInitiatingRequestToAddDevicesToCEMP_VGS extends WebtopPage {

	Logger log = Logger.getLogger(ErrorInitiatingRequestToAddDevicesToCEMP_VGS.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public ErrorInitiatingRequestToAddDevicesToCEMP_VGS(FrameworkContext context) {
		super(context, "ErrorInitiatingRequestToAddDevicesToCEMP_VGS");
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
		public static final String trayName = "trayName";
		public static final String lstResult = "lstResult";
		public static final String btnSaveTray = "btnSaveTray";
	}

	public boolean errorInitiatingRequestToAddDevicesToCEMP_VGS() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.trayName, 2)) {
				iSelectValue(Locators.lstResult, "Ticket Opened for Manual Completion");
				iClick(Locators.btnSaveTray, null,
						"Click on Save button in the tray:Error Initiating Request to Add Devices to CEMP (VGS) Page:Click on Save button in the tray");
				if (!isLocatorVisible(Locators.trayName, 2)) {
					report.reportPassEvent("Error Initiating Request to Add Devices to CEMP (VGS)",
							"Error Initiating Request to Add Devices to CEMP (VGS) completed successfully!!!");
				} else {
					report.reportHardFailEvent("Error Initiating Request to Add Devices to CEMP (VGS)",
							"Error Initiating Request to Add Devices to CEMP (VGS) Failed!!!",
							Status.FAIL);
				}
			}
		} catch (Exception e) {
			report.reportHardFailEvent("Error Initiating Request to Add Devices to CEMP (VGS)",
					"Error Initiating Request to Add Devices to CEMP (VGS) Failed!!!", Status.FAIL);
			String eMsg = "Error in Error Initiating Request to Add Devices to CEMP (VGS) --- "
					+ e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
}
