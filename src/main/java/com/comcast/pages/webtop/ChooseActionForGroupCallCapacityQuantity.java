package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class ChooseActionForGroupCallCapacityQuantity extends WebtopPage {

	Logger log = Logger.getLogger(ChooseActionForGroupCallCapacityQuantity.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public ChooseActionForGroupCallCapacityQuantity(FrameworkContext context) {
		super(context, "ChooseActionForGroupCallCapacityQuantity");
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

	public boolean chooseActionForGroupCallCapacityQuantity() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.trayName, 2)) {
				iSelectValue(Locators.lstResult, "Accept new Call Capacity (Line Count)");
				iClick(Locators.btnSaveTray, null,
						"Click on Save button in the tray:Choose action for group Call Capacity quantity Page:Click on Save button in the tray");
				if (!isLocatorVisible(Locators.trayName, 1))
					report.reportPassEvent("CARE:Choose action for group Call Capacity quantity ",
							"CARE: Choose action for group Call Capacity quantity Completed successfully!!!",
							Status.PASS);
				else
					report.reportHardFailEvent("CARE: Choose action for group Call Capacity quantity ",
							"CARE: Choose action for group Call Capacity quantity  Failed!!!", Status.FAIL);
			}

		} catch (Exception e) {
			report.reportHardFailEvent("CARE: Choose action for group Call Capacity quantity ",
					"CARE: Choose action for group Call Capacity quantity  Failed!!!", Status.FAIL);
			String eMsg = "Error in CARE: Choose action for group Call Capacity quantity  --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
}
