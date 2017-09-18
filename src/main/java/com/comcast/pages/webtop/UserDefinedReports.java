package com.comcast.pages.webtop;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class UserDefinedReports extends WebtopPage {

	Logger log = Logger.getLogger(UserDefinedReports.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public UserDefinedReports(FrameworkContext context) {
		super(context, "UserDefinedReports");
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
		public static final String tblReport = "tblReport";
		public static final String btnClose = "btnClose";
	}

	public boolean getRows(String beforeOrAfter) {
		mstatus = true;
		try {
			String currentWindowHandle = browser.getWindowHandle();
			ArrayList<String> windowHandles = new ArrayList<String>(browser.getWindowHandles());
			for (String window : windowHandles) {
				if (!window.equals(currentWindowHandle)) {
					browser.switchTo().window(window);
					dataDump.setValue("TechAndEA" + beforeOrAfter + "_MACD",
							Integer.toString(getTableRows(Locators.tblReport)));
					dataDump.setValue("NoOfTechOrEnterpriseAdmins", Integer.toString(getTableRows(Locators.tblReport)));
					browser.close();
					browser.switchTo().window(currentWindowHandle);
				}
			}
			report.reportPassEvent("Get rows of report", "Get rows of report is completed!!!", Status.PASS);
		} catch (Exception e) {
			report.reportHardFailEvent("Getting rows of report", "Get rows of report is Failed!!!", Status.FAIL);
			String eMsg = "Error in Getting rows of report --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}

}
