package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class RemoveBriaFromGroupInSwitch extends WebtopPage {

	Logger log = Logger.getLogger(RemoveBriaFromGroupInSwitch.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public RemoveBriaFromGroupInSwitch(FrameworkContext context) {
		super(context, "RemoveBriaFromGroupInSwitch");
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

	public boolean removeBriaFromGroupInSwitch() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.trayName, 2)) {
				iSelectValue(Locators.lstResult, "Completed");
				iClick(Locators.btnSaveTray, null,
						"Click on Save button in the tray:Remove Bria from Group in Switch Page:Click on Save button in the tray");
				if (!isLocatorVisible(Locators.trayName, 2)) {
					report.reportPassEvent("Remove Bria from Group in Switch",
							"Remove Bria from Group in Switchcompleted successfully!!!");
				} else {
					report.reportHardFailEvent("Remove Bria from Group in Switch",
							"Remove Bria from Group in Switch Failed!!!",
							Status.FAIL);
				}
			}
		} catch (Exception e) {
			report.reportHardFailEvent("Remove Bria from Group in Switch",
					"Remove Bria from Group in Switch Failed!!!", Status.FAIL);
			String eMsg = "Error in Remove Bria from Group in Switch --- "
					+ e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
}
