package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class SOAOrderComplete extends WebtopPage {

	Logger log = Logger.getLogger(SOAOrderComplete.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public SOAOrderComplete(FrameworkContext context) {
		super(context, "SOAOrderComplete");
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
		public static final String lnkSOAOrderComplete = "lnkSOAOrderComplete";
		public static final String lstResult = "lstResult";
		public static final String btnSetResult = "btnSetResult";
		public static final String elmtGroupID = "elmtGroupID";
		public static final String elmtOrdernumber = "elmtOrdernumber";
		public static final String btnSave = "btnSave";
		public static final String elmtCustomerName = "elmtCustomerName";
	}

	public boolean SOAOrderComplete() {
		mstatus = true;
		try {
			if (!isLocatorVisible(Locators.lstResult, 5)) {
				iClick(Locators.tabHistory, null, "Click on History Tab");
			}
			iClick(Locators.lnkSOAOrderComplete, null, "Click on SOA Order complete tray");
			iSelectValue(Locators.lstResult, "Complete");
			iClick(Locators.btnSetResult, null, "Click on SetResult");
			if (!isLocatorVisible(Locators.lnkSOAOrderComplete, 1))
				report.reportPassEvent("SOA Order Complete Tray", "Done successfully");
			else
				report.reportHardFailEvent("SOA Order Complete", "SOA Order Complete Failed!!!", Status.FAIL);
			String[] temp = testLocatorVisible(Locators.elmtGroupID).getText().split(":");
			dataDump.setValue("GroupID", temp[1].trim());
			temp = null;
			temp = testLocatorVisible(Locators.elmtOrdernumber).getText().split(":");
			dataDump.setValue("OrderID_RT", temp[1].trim());			;
			dataDump.setValue("CustomerName", testLocatorVisible(Locators.elmtCustomerName).getText().split(":")[1].trim());
		} catch (Exception e) {
			mstatus = false;
			report.reportHardFailEvent("SOA Order Complete", "SOA Order Complete Failed!!!", Status.FAIL);
			String eMsg = "Error in SOA Order Complete --- " + e.getMessage();
			log.error(eMsg);
		}
		return mstatus;
	}

}
