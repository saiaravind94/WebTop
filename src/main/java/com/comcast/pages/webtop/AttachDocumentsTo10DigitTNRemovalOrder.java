package com.comcast.pages.webtop;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class AttachDocumentsTo10DigitTNRemovalOrder extends WebtopPage {

	Logger log = Logger.getLogger(AttachDocumentsTo10DigitTNRemovalOrder.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public AttachDocumentsTo10DigitTNRemovalOrder(FrameworkContext context) {
		super(context, "AttachDocumentsTo10-DigitTNRemovalOrder");
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
		public static final String btnReturn = "btnReturn";
	}

	public boolean attachDocumentsTo10DigitTNRemovalOrder() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.trayName, 2)) {
				iClick(Locators.trayName, null, "Click on  Attach Documents to 10-digit TN Removal Order link");
				testLocatorClickable(Locators.btnChooseFile);
				String filePath;
				if (dataDump.getValue("ImportFileName").isEmpty()) {
					filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\attachements.txt";
				}else					
				{
					filePath = dataDump.getValue("ImportFileName");
				}
				browser.findElement(By.xpath("//input[@type='file']")).sendKeys(filePath);
				iClick(Locators.btnAttach);				
				iSelectValue(Locators.lstResult, "Completed");
				iClick(Locators.btnSetResult);
				if (isLocatorVisible(Locators.btnReturn, 2)) {
					iClick(Locators.btnReturn);
				}
				if (!isLocatorVisible(Locators.trayName, 1))
					report.reportPassEvent("CARE: Attach Documents to 10-digit TN Removal Order",
							"CARE: Attach Documents to 10-digit TN Removal Order completed successfully");
				else
					report.reportHardFailEvent("CARE: Attach Documents to 10-digit TN Removal Order",
							"CARE: Attach Documents to 10-digit TN Removal Order Failed!!!", Status.FAIL);
			} else {
				report.reportHardFailEvent("CARE: Attach Documents to 10-digit TN Removal Order",
						"CARE: Attach Documents to 10-digit TN Removal Order tray is not displayed!!!", Status.FAIL);
			}
		} catch (Exception e) {
			report.reportHardFailEvent("CARE: Attach Documents to 10-digit TN Removal Order",
					"CARE: Attach Documents to 10-digit TN Removal Order Failed!!!", Status.FAIL);
			String eMsg = "Error in CARE: Attach Documents to 10-digit TN Removal Order --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}

}

