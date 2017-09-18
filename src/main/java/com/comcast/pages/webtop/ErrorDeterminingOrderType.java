package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.pages.webtop.WebTopMainPage.Locators;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class ErrorDeterminingOrderType extends WebtopPage {

	Logger log = Logger.getLogger(ErrorDeterminingOrderType.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public ErrorDeterminingOrderType(FrameworkContext context) {
		super(context, "ErrorDeterminingOrderType");
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
		public static final String expandFIDInfo = "expandFIDInfo";
		public static final String dropDownOrderType = "dropDownOrderType";
		public static final String btnSaveFIDValues = "btnSaveFIDValues";
	}

	public boolean errorDeterminingOrderType() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.lstResult, 5)) {
				while (!isLocatorVisible(Locators.dropDownOrderType, 2)) {
					iClick(Locators.expandFIDInfo);
				}
				iSelectValue(Locators.dropDownOrderType, "SDW");
				iClick(Locators.btnSaveFIDValues);
				iSelectValue(Locators.lstResult, "Retry Determining Order Type");
				iClick(Locators.btnSaveTray, null,
						"Click on Save button in the tray:Retry Determining Order Type Page:Click on Save button in the tray");
				if (!isLocatorVisible(Locators.lstResult, 1))
					report.reportPassEvent("Retry Determining Order Type",
							"Retry Determining Order Type completed successfully");
				else
					report.reportHardFailEvent("Retry Determining Order Type", "Retry Determining Order Type Failed!!!",
							Status.FAIL);
			} else {
				report.reportHardFailEvent("Determining Order Type", "Determining Order Type tray is not displayed!!!",
						Status.FAIL);
			}
		} catch (Exception e) {
			report.reportHardFailEvent("Retry Determining Order Type", "Retry Determining Order Type Failed!!!",
					Status.FAIL);
			String eMsg = "Error in Retry Determining Order Type --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
}
