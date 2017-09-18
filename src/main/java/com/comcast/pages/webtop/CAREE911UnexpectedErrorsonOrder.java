package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class CAREE911UnexpectedErrorsonOrder extends WebtopPage {

	Logger log = Logger.getLogger(CAREE911UnexpectedErrorsonOrder.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public CAREE911UnexpectedErrorsonOrder(FrameworkContext context) {
		super(context, "CAREE911UnexpectedErrorsonOrder");
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
		public static final String trayName = "trayName";
		public static final String btnSetResult = "btnSetResult";
		public static final String lnkSelectAll = "lnkSelectAll";
		public static final String trayLinkList = "trayLinkList";

	}

	public boolean cAREE911UnexpectedErrorsonOrder() {
		mstatus = true;
		try {
			iClick(Locators.lnkSelectAll, null, "Click on Select All Link:Batch Management Page:Select All Link");
			getLocatorWEList(Locators.trayLinkList).get(0).click();
			if (isLocatorVisible(Locators.trayName, 5)) {
				iClick(Locators.trayName);
				iSelectValue(Locators.lstResult, "Address Change Completed Manually");
				iClick(Locators.btnSetResult, null,
						"Click on Set Result button in the tray: E-911 Unexpected Errors on Order Page:Click on Set Result button in the tray");
				if (!isLocatorVisible(Locators.lstResult, 1))
					report.reportPassEvent("E-911 Unexpected Errors on Order",
							"E-911 Unexpected Errors on Order completed successfully");
				else
					report.reportHardFailEvent("E-911 Unexpected Errors on Order",
							"E-911 Unexpected Errors on Order Failed!!!", Status.FAIL);
			} else {
				report.reportHardFailEvent("E-911 Unexpected Errors on Order",
						"E-911 Unexpected Errors on Order tray is not displayed!!!", Status.FAIL);
				String eMsg = "E-911 Unexpected Errors on Order tray is not displayed--- ";
				log.error(eMsg);
				mstatus = false;
			}
		} catch (Exception e) {
			report.reportHardFailEvent("E-911 Unexpected Errors on Order", "E-911 Unexpected Errors on Order Failed!!!",
					Status.FAIL);
			String eMsg = "Error in E-911 Unexpected Errors on Order --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
}
