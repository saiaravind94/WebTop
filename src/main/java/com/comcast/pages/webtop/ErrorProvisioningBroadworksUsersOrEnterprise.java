package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class ErrorProvisioningBroadworksUsersOrEnterprise extends WebtopPage {

	Logger log = Logger.getLogger(ErrorProvisioningBroadworksUsersOrEnterprise.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public ErrorProvisioningBroadworksUsersOrEnterprise(FrameworkContext context) {
		super(context, "ErrorProvisioningBroadworksUsersOrEnterprise");
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

	public boolean errorProvisioningBroadworksUsers() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.trayName, 2)) {
				String trayName = iGetText(Locators.trayName);
				if (trayName.contains("User")) {
					iSelectValue(Locators.lstResult, "Provision User Process Completed Manually");
				} else if (trayName.contains("Enterprise")) {
					iSelectValue(Locators.lstResult, "Provision Enterprise Process Completed Manually");
				} else {
					iSelectValue(Locators.lstResult, "Provision Group Process Completed Manually");
				}
				iClick(Locators.btnSaveTray, null,
						"Click on Save button in the tray:Provisioning Broadworks Users Page:Click on Save button in the tray");
				if (isLocatorVisible(Locators.trayName, 2)) {
					report.reportHardFailEvent("Provisioning Broadworks Group/Users/Enterprise",
							"Provisioning Broadworks Group/Users/Enterprise completed successfully");
				} else {
					report.reportPassEvent("Provisioning Broadworks Group/Users/Enterprise",
							"Provisioning Broadworks Group/Users/Enterprise completed successfully");
				}
			} else {
				report.reportHardFailEvent("Provisioning Broadworks Group/Users/Enterprise",
						"Provisioning Broadworks Users/Enterprise tray not coming", Status.FAIL);
				String eMsg = "Error in Provisioning Broadworks Group/Users/Enterprise --- ";
				log.error(eMsg);
				mstatus = false;
			}
		} catch (Exception e) {
			report.reportHardFailEvent("Provisioning Broadworks Group/Users/Enterprise",
					"Provisioning Broadworks Users Failed!!!", Status.FAIL);
			String eMsg = "Error in Provisioning Broadworks Group/Users/Enterprise --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
}
