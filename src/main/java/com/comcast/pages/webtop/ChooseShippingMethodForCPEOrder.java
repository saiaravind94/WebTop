package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class ChooseShippingMethodForCPEOrder extends WebtopPage {

	Logger log = Logger.getLogger(ChooseShippingMethodForCPEOrder.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public ChooseShippingMethodForCPEOrder(FrameworkContext context) {
		super(context, "ChooseShippingMethodForCPEOrder");
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
		public static final String lnkChooseShippingMethodForCPEOrder = "lnkChooseShippingMethodForCPEOrder";
		public static final String lstShippingMethod = "lstShippingMethod";
		public static final String btnSave = "btnSave";
	}

	public boolean chooseShippingMethodForCPEOrder() {
		mstatus = true;
		try {
			if (!isLocatorVisible(Locators.lnkChooseShippingMethodForCPEOrder, 5)) {
				iClick(Locators.tabHistory, null, "Click on History Tab");
			}
			iClick(Locators.lnkChooseShippingMethodForCPEOrder, null,
					"Click on Choose Shipping Method For CPE Order link");
			iSelectValue(Locators.lstShippingMethod, dataSet.getValue("ShippingMethod"));
			iClick(Locators.btnSave, null, "Click on Save button");
			if (!isLocatorVisible(Locators.lnkChooseShippingMethodForCPEOrder, 1))
				report.reportPassEvent("Choose Shipping Method For CPE Order",
						"Choose Shipping Method For CPE Order completed successfully");
			else
				report.reportHardFailEvent("Choose Shipping Method For CPE Order",
						"Choose Shipping Method For CPE Order Failed!!!", Status.FAIL);
		} catch (Exception e) {
			report.reportHardFailEvent("Choose Shipping Method For CPE Order",
					"Choose Shipping Method For CPE Order Failed!!!", Status.FAIL);
			String eMsg = "Error in Choose Shipping Method For CPE Order --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
}
