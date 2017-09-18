package com.comcast.pages.webtop;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import com.comcast.commons.WebtopPage;
import com.comcast.pages.webtop.CAREE911UnexpectedErrorsonOrder.Locators;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class AttachDocumentsToDisconnectOrder extends WebtopPage {

	Logger log = Logger.getLogger(AttachDocumentsToDisconnectOrder.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public AttachDocumentsToDisconnectOrder(FrameworkContext context) {
		super(context, "AttachDocumentsToDisconnectOrder");
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
		public static final String lnkSelectAll = "lnkSelectAll";
		public static final String trayLinkList = "trayLinkList";
	}

	public boolean attachDocumentsToDisconnectOrder() {
		mstatus = true;
		String filePath;
		try {
			if (isLocatorVisible(Locators.lnkSelectAll, 1)) {
				iClick(Locators.lnkSelectAll, null, "Click on Select All Link:Batch Management Page:Select All Link");
				getLocatorWEList(Locators.trayLinkList).get(0).click();
			}
			if (isLocatorVisible(Locators.trayName, 2)) {
				iClick(Locators.trayName, null, "Click on Attach Documents to Disconnect Order link");	
				testLocatorClickable(Locators.btnChooseFile);							
				if (dataDump.getValue("ImportFileName").isEmpty()) {
					filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\attachements.txt";
				}else					
				{
					filePath = dataDump.getValue("ImportFileName");
				}
				browser.findElement(By.xpath("//input[@type='file']")).sendKeys(filePath);
				iClick(Locators.btnAttach);
				iSelectValue(Locators.lstResult, "Document(s) Attached to Tray");
				iClick(Locators.btnSetResult);
				if (isLocatorVisible(Locators.btnReturn, 2)) {
					iClick(Locators.btnReturn);
				}
				if (!isLocatorVisible(Locators.trayName, 1))
					report.reportPassEvent("CARE: Attach Documents to Disconnect Order",
							"CARE: Attach Documents to Disconnect Order completed successfully");
				else
					report.reportHardFailEvent("CARE: Attach Documents to Disconnect Order",
							"CARE: Attach Documents to Disconnect Order Failed!!!", Status.FAIL);
			} else {
				report.reportHardFailEvent("CARE: Attach Documents to Disconnect Order",
						"CARE: Attach Documents to Disconnect Order tray is not displayed!!!", Status.FAIL);
			}
		} catch (Exception e) {
			report.reportHardFailEvent("CARE: Attach Documents to Disconnect Order",
					"CARE: Attach Documents to Disconnect Order Failed!!!", Status.FAIL);
			String eMsg = "Error in CARE: Attach Documents to Disconnect Order --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}

}

