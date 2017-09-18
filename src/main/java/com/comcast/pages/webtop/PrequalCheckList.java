
package com.comcast.pages.webtop;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import com.comcast.commons.WebtopPage;
import com.comcast.pages.webtop.SOAOrSDWRequired.Locators;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class PrequalCheckList extends WebtopPage {

	Logger log = Logger.getLogger(PrequalCheckList.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public PrequalCheckList(FrameworkContext context) {
		super(context, "PrequalCheckList");
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
		public static final String btnPreqaulCheckListAttach = "btnPreqaulCheckListAttach";
		public static final String btnPrequalCheckListFileAttach = "btnPrequalCheckListFileAttach";
		public static final String btnchooseFileProspect = "btnchooseFileProspect";
		public static final String elmtVerifySuccessful = "elmtVerifySuccessful";
		public static final String btnSaveContinue = "btnSaveContinue";
		public static final String tabHistory = "tabHistory";
	}

	public boolean prequalCheckList() {
		mstatus = true;
		try {
			if (!isLocatorVisible(Locators.btnPreqaulCheckListAttach, 5)) {
				iClick(Locators.tabHistory, null, "Click on History Tab");
			}
			iClick(Locators.btnPreqaulCheckListAttach, null, "Click on Prequal CheckList Attach Button");
			browser.findElement(By.xpath("//input[@type='file']")).sendKeys(dataDump.getValue("ImportFileName"));
			waitforPageLoadComplete();
			if (isLocatorVisible(Locators.btnPrequalCheckListFileAttach, 2)) {
				clickndRelease(testLocatorClickable(Locators.btnPrequalCheckListFileAttach));
				waitforPageLoadComplete();
				iVerifyText(Locators.elmtVerifySuccessful,
						"Successfully Uploaded File: " + getFileName(dataDump.getValue("ImportFileName")));
			}
			iClick(Locators.btnSaveContinue, null, "Click on Save Continue button");
			report.reportPassEvent("Prequal CheckList attachement",
					"Prequal CheckList attachement  added successfully");
		} catch (Exception e) {
			mstatus = false;
			report.reportHardFailEvent("Prequal CheckList attachement", "Prequal CheckList attachement Failed!!!",
					Status.FAIL);
			String eMsg = "Error in Prequal CheckList attachement --- " + e.getMessage();
			log.error(eMsg);
		}
		return mstatus;
	}

}

