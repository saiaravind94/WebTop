package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class Wait24HoursThenRecheckForCPEMACAddress extends WebtopPage {

	Logger log = Logger.getLogger(Wait24HoursThenRecheckForCPEMACAddress.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public Wait24HoursThenRecheckForCPEMACAddress(FrameworkContext context) {
		super(context, "Wait24HoursThenRecheckForCPEMACAddress");
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

	public boolean wait24HoursThenRecheckForCPEMACAddress() {
		mstatus = true;
		try {
			if (!isLocatorVisible(Locators.lstResult, 5)) {
				iClick(Locators.tabHistory, null, "Click on History Tab");
			}
			iSelectValue(Locators.lstResult, "Time Expired");
			iClick(Locators.btnSaveTray, null,
					"Click on Save button in the tray:Wait 24 hours, then recheck for CPE MAC Address(es):Click on Save button in the tray");
			if (!isLocatorVisible(Locators.lstResult, 1))
				report.reportPassEvent("Wait 24 hours, then recheck for CPE MAC Address(es)",
						"Wait 24 hours, then recheck for CPE MAC Address(es) completed successfully");
			else
				report.reportHardFailEvent("Wait 24 hours, then recheck for CPE MAC Address(es)",
						"Wait 24 hours, then recheck for CPE MAC Address(es) Failed!!!", Status.FAIL);
		} catch (Exception e) {
			report.reportHardFailEvent("Wait 24 hours, then recheck for CPE MAC Address(es)",
					"Wait 24 hours, then recheck for CPE MAC Address(es) Failed!!!", Status.FAIL);
			String eMsg = "Error in Wait 24 hours, then recheck for CPE MAC Address(es) --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
}
