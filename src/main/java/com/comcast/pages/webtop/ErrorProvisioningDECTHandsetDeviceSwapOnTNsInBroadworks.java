package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class ErrorProvisioningDECTHandsetDeviceSwapOnTNsInBroadworks extends WebtopPage {

	Logger log = Logger.getLogger(ErrorProvisioningDECTHandsetDeviceSwapOnTNsInBroadworks.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public ErrorProvisioningDECTHandsetDeviceSwapOnTNsInBroadworks(FrameworkContext context) {
		super(context, "ErrorProvisioningDECTHandsetDeviceSwapOnTNsInBroadworks");
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

	public boolean errorProvisioningDECTHandsetDeviceSwapOnTNsInBroadworks() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.trayName, 2)) {
				iSelectValue(Locators.lstResult, "Device Swap Provisioned Manually");
				iClick(Locators.btnSaveTray, null,
						"Click on Save button in the tray:Error Provisioning DECT Handset Device Swap on TN(s) in Broadworks Page:Click on Save button in the tray");
				if (!isLocatorVisible(Locators.trayName, 2)) {
					report.reportPassEvent("Error Provisioning DECT Handset Device Swap on TN(s) in Broadworks",
							"Error Provisioning DECT Handset Device Swap on TN(s) in Broadworks completed successfully!!!");
				} else {
					report.reportHardFailEvent("Error Provisioning DECT Handset Device Swap on TN(s) in Broadworks",
							"Error Provisioning DECT Handset Device Swap on TN(s) in Broadworks Failed!!!",
							Status.FAIL);
				}
			} else {
				report.reportHardFailEvent("Error Provisioning DECT Handset Device Swap on TN(s) in Broadworks",
						"Error Provisioning DECT Handset Device Swap on TN(s) in Broadworks tray is not displayed!!!",
						Status.FAIL);
			}
		} catch (Exception e) {
			report.reportHardFailEvent("Error Provisioning DECT Handset Device Swap on TN(s) in Broadworks",
					"Error Provisioning DECT Handset Device Swap on TN(s) in Broadworks Failed!!!", Status.FAIL);
			String eMsg = "Error in Error Provisioning DECT Handset Device Swap on TN(s) in Broadworks --- "
					+ e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
}
