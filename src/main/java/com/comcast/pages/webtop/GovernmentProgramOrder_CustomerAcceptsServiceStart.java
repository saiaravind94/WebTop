package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class GovernmentProgramOrder_CustomerAcceptsServiceStart extends WebtopPage {

	Logger log = Logger.getLogger(GovernmentProgramOrder_CustomerAcceptsServiceStart.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public GovernmentProgramOrder_CustomerAcceptsServiceStart(FrameworkContext context) {
		super(context, "GovernmentProgramOrder_CustomerAcceptsServiceStart");
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

	public boolean governmentProgramOrder_CustomerAcceptsServiceStart() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.lstResult, 5)) {
				iSelectValue(Locators.lstResult, "Yes - Continue Order");
				iClick(Locators.btnSaveTray, null,
						"Click on Save button in the tray: SDPM: Government Program Order - Customer Accepts Service Start Page:Click on Save button in the tray");
				if (!isLocatorVisible(Locators.lstResult, 1))
					report.reportPassEvent("SDPM: Government Program Order - Customer Accepts Service Start",
							"SDPM: Government Program Order - Customer Accepts Service Start completed successfully");
				else
					report.reportHardFailEvent("SDPM: Government Program Order - Customer Accepts Service Start",
							"SDPM: Government Program Order - Customer Accepts Service Start Update Failed!!!",
							Status.FAIL);
			} else {
				report.reportHardFailEvent("SDPM: Government Program Order - Customer Accepts Service Start",
						"SDPM: Government Program Order - Customer Accepts Service Start tray is not displayed!!!",
						Status.FAIL);
				String eMsg = "SDPM: Government Program Order - Customer Accepts Service Start tray is not displayed--- ";
				log.error(eMsg);
				mstatus = false;
			}
		} catch (Exception e) {
			report.reportHardFailEvent("SDPM: Government Program Order - Customer Accepts Service Start",
					"SDPM: Government Program Order - Customer Accepts Service Start Update Failed!!!", Status.FAIL);
			String eMsg = "Error in SDPM: Government Program Order - Customer Accepts Service Start --- "
					+ e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
}
