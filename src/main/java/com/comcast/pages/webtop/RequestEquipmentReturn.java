package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class RequestEquipmentReturn extends WebtopPage {

	Logger log = Logger.getLogger(RequestEquipmentReturn.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public RequestEquipmentReturn(FrameworkContext context) {
		super(context, "RequestEquipmentReturn");
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
		public static final String lnkSetResult = "lnkSetResult";
		public static final String drpRslt = "drpRslt";
		public static final String drpSetResult = "drpSetResult";
		public static final String txtHistoryTab = "txtHistoryTab";
		
	}

	public boolean requestEquipmentReturn() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.lnkSetResult, 5)) {
				iClick(Locators.lnkSetResult);
				iSelectValue(Locators.drpRslt, "Completed");
				iClick(Locators.drpSetResult, null,
						"Click on Set Result Button button For Request Equipment Return Tray:");
				if (!isLocatorVisible(Locators.txtHistoryTab, 3))
					report.reportPassEvent("Verify if the Request Equipment Return tray is successfully completed",
							"Verification of Request Equipment tray is successful");
			}
		} catch (Exception e) {
			report.reportHardFailEvent("Verify if the Request Equipment Return Tray is successfully completed",
					"Verification of the Request Equipment Return tray Failed!!!", Status.FAIL);
			String eMsg = "Error in Request Equipment Return  --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
}
