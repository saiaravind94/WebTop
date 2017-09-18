package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class RequestRMAForCPE_DisconnectOrder extends WebtopPage {

	Logger log = Logger.getLogger(RequestRMAForCPE_DisconnectOrder.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public RequestRMAForCPE_DisconnectOrder(FrameworkContext context) {
		super(context, "RequestRMAForCPE_DisconnectOrder");
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

	public boolean requestRMAForCPE_DisconnectOrder() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.trayName, 2)) {
				iSelectValue(Locators.lstResult, "Completed");
				iClick(Locators.btnSaveTray, null,
						"Click on Save button in the tray:Request RMA for CPE (Disconnect Order) Page:Click on Save button in the tray");
				if (!isLocatorVisible(Locators.trayName, 1))
					report.reportPassEvent("CARE: Request RMA for CPE (Disconnect Order)",
							"CARE: Request RMA for CPE (Disconnect Order) Passed!!!", Status.PASS);
				else
					report.reportHardFailEvent("CARE: Request RMA for CPE (Disconnect Order)",
							"CARE: Request RMA for CPE (Disconnect Order) Failed!!!", Status.FAIL);
			} 
		} catch (Exception e) {
			report.reportHardFailEvent("CARE: Request RMA for CPE (Disconnect Order)",
					"CARE: Request RMA for CPE (Disconnect Order) Failed!!!", Status.FAIL);
			String eMsg = "Error in CARE: Request RMA for CPE (Disconnect Order) --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
}
