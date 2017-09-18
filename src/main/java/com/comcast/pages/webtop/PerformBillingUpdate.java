package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.pages.simon.OrderInformation.Locators;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class PerformBillingUpdate extends WebtopPage {

	Logger log = Logger.getLogger(PerformBillingUpdate.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public PerformBillingUpdate(FrameworkContext context) {
		super(context, "PerformBillingUpdate");
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

	public boolean performBillingUpdate() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.lstResult, 5)) {
				iSelectValue(Locators.lstResult, "Completed");
				iClick(Locators.btnSaveTray, null,
						"Click on Save button in the tray: Perform Billing Update Page:Click on Save button in the tray");
				if (!isLocatorVisible(Locators.lstResult, 1)) {
					report.reportPassEvent("BILLING: Perform Billing Update",
							"BILLING: Perform Billing Update completed successfully");
					dataDump.setValue("OrderStatus", "Completed");
				} else
					report.reportHardFailEvent("BILLING: Perform Billing Update",
							"BILLING: Perform Billing Update Failed!!!", Status.FAIL);
			} else {
				report.reportHardFailEvent("BILLING: Perform Billing Update",
						"BILLING: Perform Billing Update tray is not displayed!!!", Status.FAIL);
				String eMsg = "BILLING: Perform Billing Update tray is not displayed--- ";
				log.error(eMsg);
				mstatus = false;
			}
		} catch (Exception e) {
			report.reportHardFailEvent("BILLING: Perform Billing Update", "BILLING: Perform Billing Update Failed!!!",
					Status.FAIL);
			String eMsg = "Error in BILLING: Perform Billing Update --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
}
