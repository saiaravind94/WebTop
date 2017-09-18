package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class RemoveEnterpriseFromBroadsworks extends WebtopPage {

	Logger log = Logger.getLogger(RemoveEnterpriseFromBroadsworks.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public RemoveEnterpriseFromBroadsworks(FrameworkContext context) {
		super(context, "RemoveEnterpriseFromBroadsworks");
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

	public boolean removeEnterpriseFromBroadsworks() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.lnkSetResult, 5)) {
				iClick(Locators.lnkSetResult);
				iSelectValue(Locators.drpRslt, "Completed");
				iClick(Locators.drpSetResult, null,
						"Click on Set Result Button button For Remove Enterprise from Broadworks Tray");
				if (!isLocatorVisible(Locators.txtHistoryTab, 3))
					report.reportPassEvent("Verify if the Remove Enterprise from Broadworks Tray is successfully completed",
							"Verification of Remove Enterprise from Broadworks Tray is successful");
			}
		} catch (Exception e) {
			report.reportHardFailEvent("Verify if the Remove Enterprise from Broadworks Tray is successfully completed",
					"Verification of Remove Enterprise from Broadworks Tray Failed!!!", Status.FAIL);
			String eMsg = "Error in Remove Enterprise from Broadworks Tray --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
}
