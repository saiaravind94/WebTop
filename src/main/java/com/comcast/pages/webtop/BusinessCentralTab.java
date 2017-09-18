package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.Report;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class BusinessCentralTab extends WebtopPage {

	Logger log = Logger.getLogger(BusinessCentralTab.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public BusinessCentralTab(FrameworkContext context) {
		super(context, "BusinessCentralTab");
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
		public static final String tabBusinessCentral = "tabBusinessCentral";
		public static final String lnkTerminate = "lnkTerminate";
		public static final String btnTerminate = "btnTerminate";
		public static final String imgCalendar = "imgCalendar";
		public static final String btnToday = "btnToday";
		public static String lnkReport = "";
		public static final String lnkExpandAll = "lnkExpandAll";
		public static final String ETSCCount = "ETSCCount";

	}

	public boolean terminateAccount() {
		mstatus = true;
		try {
			if (!isLocatorVisible(Locators.lnkTerminate, 1)) {
				iClick(Locators.tabBusinessCentral, Locators.lnkTerminate,
						"Click on Business Central Page: Business Central Page: Business Central Tab");
			}
			iClick(Locators.lnkTerminate, Locators.imgCalendar,
					"Click on Terminate Link: Business Central Page: Terminate Link");
			iClick(Locators.imgCalendar, Locators.btnToday, "Click on calendar: Edit Termination Page: Calendar image");
			iClick(Locators.btnToday, null, "Click on today date: Edit Termination Page: Date selection");
			iClick(Locators.btnTerminate);
			report.reportPassEvent("Terminate account", "Terminate account completed!!!", Status.PASS);
		} catch (Exception e) {
			report.reportHardFailEvent("Terminate account", "Terminate account Failed!!!", Status.FAIL);
			String eMsg = "Error in Terminating account --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}

	public boolean launchReport(String reportName) {
		mstatus = true;
		addLocator(Locators.lnkReport, "xpath", "//span[.='" + reportName + "']", reportName);
		if (isLocatorVisible(Locators.lnkReport, 2)) {
			iClick(Locators.lnkReport);
			report.reportPassScreenshotEvent("Launching User Defined reports",
					"Launched User Defined report: " + reportName);
		} else {
			mstatus = true;
			report.reportHardFailEvent("Launching User Defined reports", "Launch User Defined reports failed!!!",
					Status.FAIL);
		}
		return mstatus;
	}

	public void verifyETSCCount(String expectedValue) throws InterruptedException {
		if (!isLocatorVisible(Locators.ETSCCount, 2)) {
			iClick(Locators.lnkExpandAll);
		}
		if (iVerifyText(Locators.ETSCCount, expectedValue)) {
			report.reportPassEvent("ETSC Count verification in Account Information tab of Business central page",
					"ETCS Count: " + expectedValue + " is displayed as expected");
		} else {
			report.reportSoftFailEvent("ETSC Count verification in Account Information tab of Business central page",
					"ETCS Count: " + expectedValue + " is not displayed as expected. Actual Value is: "
							+ iGetText(Locators.ETSCCount));
		}

	}

	public String getETSCCOunt() throws InterruptedException {
		if (!isLocatorVisible(Locators.ETSCCount, 2)) {
			iClick(Locators.lnkExpandAll);
		}
		return iGetText(Locators.ETSCCount);
	}

}
