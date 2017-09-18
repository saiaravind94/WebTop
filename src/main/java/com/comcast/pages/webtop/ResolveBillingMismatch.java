package com.comcast.pages.webtop;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.ReportPath;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.utils.DataTable;

public class ResolveBillingMismatch extends WebtopPage {

	Logger log = Logger.getLogger(ResolveBillingMismatch.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public ResolveBillingMismatch(FrameworkContext context) {
		super(context, "ResolveBillingMismatch");
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
		public static final String trayName = "trayName";
		public static final String btnSetResult = "btnSetResult";
		public static final String btnChooseFile = "btnChooseFile";
		public static final String btnAttach = "btnAttach";
		public static final String lnkBillingMismatchReportPDF = "lnkBillingMismatchReportPDF";
	}

	public boolean resolveBillingMismatch() {
		mstatus = true;
		String filePath;
		try {			
			if (isLocatorVisible(Locators.trayName, 2)) {				
				iClick(Locators.trayName, null, "Click on Resolve Billing Mismatch Tray link");
				testLocatorClickable(Locators.btnChooseFile);
				iClick(Locators.lnkBillingMismatchReportPDF);
				sleep(5000);
				String latestBillingMismatchPDFFile = getLatestFile(System.getProperty("user.home") + "\\Downloads\\",
						dataDump.getValue("GroupID") + "-.*. Billing Mismatch Report.pdf");
				String reportPath = ReportPath.getInstance().getReportPath();
				FileUtils.copyFile(new File(latestBillingMismatchPDFFile), new File(
						reportPath + "\\" + context.getTestCaseName() + "_" + new File(latestBillingMismatchPDFFile).getName()));
				log.info("Billing Mismatch Report File path: " + latestBillingMismatchPDFFile);
				dataDump.setValue("BillingMismatchReport", latestBillingMismatchPDFFile);
				if (dataDump.getValue("BillingMismatchReport").isEmpty()) {
					filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\attachements.txt";
				}else					
				{
					filePath = dataDump.getValue("BillingMismatchReport");
				}
				browser.findElement(By.xpath("//input[@type='file']")).sendKeys(filePath);
				iClick(Locators.btnAttach);
				iSelectValue(Locators.lstResult, "IFC Form Signed/Accepted");
				iClick(Locators.btnSetResult);				
				if (!isLocatorVisible(Locators.trayName, 1))
					report.reportPassEvent("Resolve Billing Mismatch Tray",
							"Resolve Billing Mismatch Tray completed successfully");
				else
					report.reportHardFailEvent("Resolve Billing Mismatch Tray",
							"Resolve Billing Mismatch Tray Failed!!!", Status.FAIL);
			}
		} catch (Exception e) {
			report.reportHardFailEvent("Resolve Billing Mismatch Tray",
					"Resolve Billing Mismatch Tray Failed!!!", Status.FAIL);
			String eMsg = "Error in Resolve Billing Mismatch Tray  --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}

}

