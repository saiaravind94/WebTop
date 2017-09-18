package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class ConfigureAdvancedBroadworksFeatures extends WebtopPage {

	Logger log = Logger.getLogger(ConfigureAdvancedBroadworksFeatures.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public ConfigureAdvancedBroadworksFeatures(FrameworkContext context) {
		super(context, "ConfigureAdvancedBroadworksFeatures");
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

	public boolean configureAdvancedBroadworksFeatures() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.lstResult, 5)) {
				iSelectValue(Locators.lstResult, "Completed");
				iClick(Locators.btnSaveTray, null,
						"Click on Save button in the tray:VOIP PROV: Configure Advanced Broadworks Features Page:Click on Save button in the tray");
				if (!isLocatorVisible(Locators.lstResult, 1))
					report.reportPassEvent("VOIP PROV: Configure Advanced Broadworks Features",
							"VOIP PROV: Configure Advanced Broadworks Features completed successfully");
				else
					report.reportHardFailEvent("VOIP PROV: Configure Advanced Broadworks Features",
							"VOIP PROV: Configure Advanced Broadworks Features Failed!!!", Status.FAIL);
			}
		} catch (Exception e) {
			report.reportHardFailEvent("VOIP PROV: Configure Advanced Broadworks Features",
					"VOIP PROV: Configure Advanced Broadworks Features Failed!!!", Status.FAIL);
			String eMsg = "Error in VOIP PROV: Configure Advanced Broadworks Features --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
}
