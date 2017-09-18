
package com.comcast.pages.webtop;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class BulkLoadFileImport extends WebtopPage {

	Logger log = Logger.getLogger(BulkLoadFileImport.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public BulkLoadFileImport(FrameworkContext context) {
		super(context, "BulkLoadFileImport");
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
		public static final String btnchoosefile = "btnchooseFile";
		public static final String btnAttachFile = "btnAttachFile";
		public static final String elmtVerifySuccessful = "elmtVerifySuccessful";
		public static final String btnRefreshList = "btnRefreshList";
		public static final String lnkValidateOrProcess = "";
		public static final String lnkFailedorCompleted = "";
	}

	public void loadBulkImportFileAndVerify() {
		try {
			String filePath = "";
			checkLocatorClickable(Locators.btnchoosefile);
			if (dataDump.getValue("WebTopImportFilePath_RT").isEmpty()) {
				filePath = dataDump.getValue("ImportFileName");
			} else {
				filePath = dataDump.getValue("WebTopImportFilePath_RT");
			}
			browser.findElement(By.xpath("//input[@type='file']")).sendKeys(filePath);			
			iClick(Locators.btnAttachFile, null, "Click on Attach button: Bulk Load File Import : Attach button");
			waitforPageLoadComplete();
			iVerifyText(Locators.elmtVerifySuccessful, "Successfully Uploaded File: " + getFileName(filePath));
			WebElement lnkValidateOrProcess;
			WebElement lnkFailedorCompleted;
			int tryCount = 0;
			while (tryCount <= 100) {
				try {
					lnkValidateOrProcess = browser.findElement(By.xpath("//td/a[text()='" + getFileName(filePath)
							+ "']/../../child::td/a[.='validate' or .='process']"));
					tryCount = tryCount + 1;
					if (waitForElement(lnkValidateOrProcess, 1)) {
						if (lnkValidateOrProcess.getText().matches("validate|process")) {
							highlightRedWE(lnkValidateOrProcess);
							jsClick(lnkValidateOrProcess);
						}
					}
				} catch (NoSuchElementException ex) {

				}
				try {
					lnkFailedorCompleted = browser
							.findElement(By.xpath(".//td[.='" + getFileName(filePath) + "']/../child::td[5]"));
					if (waitForElement(lnkFailedorCompleted, 1)) {
						if (lnkFailedorCompleted.getText().equals("failed")) {
							log.error("Import file processing failed");
							report.reportHardFailEvent("Import File processing", "Import file procesing failed",
									Status.FAIL);
						} else if (lnkFailedorCompleted.getText().equals("Completed")) {
							log.info("Import file processing completed");
							report.reportPassEvent("Import File processing", "Import file procesing completed",
									Status.PASS);
							break;
						}
					}
				} catch (NoSuchElementException ex) {

				}

				iClick(Locators.btnRefreshList, null,
						"Click on RefreshList Button: Bulk Load File Import : Refresh List Button");

				if (tryCount == 100) {
					report.reportHardFailEvent("Import File processing", "Import file procesing failed", Status.FAIL);
					break;
				}
			}

		} catch (Exception Ex) {
			report.reportHardFailEvent("Load Bulk File Import", "load Bulk File Import Failed!!!" + Ex.getMessage());
			String eMsg = "Error in Initial File Import --- " + Ex.getMessage();
			mstatus = false;
			mstatus = false;
			log.error(eMsg);
		}
	}
}

