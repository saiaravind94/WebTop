package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class ProvisionPortedTNsForOrder extends WebtopPage {

	Logger log = Logger.getLogger(ProvisionPortedTNsForOrder.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public ProvisionPortedTNsForOrder(FrameworkContext context) {
		super(context, "ProvisionPortedTNsForOrder");
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

	public boolean provisionPortedTNsForOrder() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.lstResult, 5)) {
				iSelectValue(Locators.lstResult, "TNs Provisioned - Waiting on FOC Date");
				iClick(Locators.btnSaveTray, null,
						"Click on Save button in the tray:PROV: Provision Ported TNs for Order Page:Click on Save button in the tray");
				if (!isLocatorVisible(Locators.lstResult, 1))
					report.reportPassEvent("PROV: Provision Ported TNs for Order",
							"PROV: Provision Ported TNs for Order completed successfully");
				else
					report.reportHardFailEvent("PROV: Provision Ported TNs for Order",
							"PROV: Provision Ported TNs for Order Failed!!!", Status.FAIL);
			}
		} catch (Exception e) {
			report.reportHardFailEvent("PROV: Provision Ported TNs for Order",
					"PROV: Provision Ported TNs for Order Failed!!!", Status.FAIL);
			String eMsg = "Error in PROV: Provision Ported TNs for Order --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
}
