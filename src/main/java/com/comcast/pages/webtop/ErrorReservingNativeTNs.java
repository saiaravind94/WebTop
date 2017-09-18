package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.pages.webtop.WebTopMainPage.Locators;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class ErrorReservingNativeTNs extends WebtopPage {

	Logger log = Logger.getLogger(ErrorReservingNativeTNs.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public ErrorReservingNativeTNs(FrameworkContext context) {
		super(context, "ErrorReservingNativeTNs");
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
		public static final String errorReservingNativeTNTray = "errorReservingNativeTNTray";
		public static final String tabCustomerProfile = "tabCustomerProfile";
		public static final String lnkInprogressOrder = "lnkInprogressOrder";
	}

	public boolean errorReservingNativeTNs() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.errorReservingNativeTNTray, 5)) {
				iClick(Locators.tabCustomerProfile, null,
						"Customer Profile Tab Click:Error Reserving Native TNs:Customer Profile");
				new ExtendedMiscInfo(context).configureRateCenter();
				iClick(Locators.lnkInprogressOrder, null, "Click on the Open Order link");
				iSelectValue(Locators.lstResult, "Retry Reserving Native TNs");
				iClick(Locators.btnSaveTray, null,
						"Click on Save button in the tray:CPE Order will be submitted automatically Page:Click on Save button in the tray");
				if (!isLocatorVisible(Locators.errorReservingNativeTNTray, 2)) {
					report.reportPassEvent("Retry Reserving Natve TNs",
							"Retry Reserving Natve TNs completed successfully");
				} else {
					report.reportHardFailEvent("Retry Reserving Natve TNs", "Retry Reserving Natve TNs Failed!!!",
							Status.FAIL);
				}
			}

		} catch (Exception e) {
			report.reportHardFailEvent("Retry Reserving Natve TNs", "Retry Reserving Natve TNs Failed!!!", Status.FAIL);
			String eMsg = "Error in Retry Reserving Natve TNs --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
}
