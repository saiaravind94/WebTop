package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class AcceptOrRejectTNDeactivationOrder extends WebtopPage {

	Logger log = Logger.getLogger(AcceptOrRejectTNDeactivationOrder.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public AcceptOrRejectTNDeactivationOrder(FrameworkContext context) {
		super(context, "AcceptOrRejectTNDeactivationOrder");
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

	public boolean acceptOrRejectTNDeactivationOrder() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.trayName, 2)) {
				iSelectValue(Locators.lstResult, "Accept and Continue");
				iClick(Locators.btnSaveTray, null,
						"Click on Save button in the tray:Accept or Reject TN Deactivation Order (TNs remain on account) Page:Click on Save button in the tray");
				if (!isLocatorVisible(Locators.trayName, 2)) {
					report.reportPassEvent("CARE: Accept or Reject TN Deactivation Order (TNs remain on account)",
							"CARE: Accept or Reject TN Deactivation Order (TNs remain on account) completed successfully!!!");
				} else {
					report.reportHardFailEvent("CARE: Accept or Reject TN Deactivation Order (TNs remain on account)",
							"CARE: Accept or Reject TN Deactivation Order (TNs remain on account) Failed!!!",
							Status.FAIL);
				}
			}
		} catch (Exception e) {
			report.reportHardFailEvent("CARE: Accept or Reject TN Deactivation Order (TNs remain on account)",
					"CARE: Accept or Reject TN Deactivation Order (TNs remain on account) Failed!!!", Status.FAIL);
			String eMsg = "Error in CARE: Accept or Reject TN Deactivation Order (TNs remain on account) --- "
					+ e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
}
