
package com.comcast.pages.webtop;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import com.comcast.commons.WebtopPage;
import com.comcast.pages.webtop.PrequalCheckList.Locators;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class SOAOrSDWRequired extends WebtopPage {

	Logger log = Logger.getLogger(SOAOrSDWRequired.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public SOAOrSDWRequired(FrameworkContext context) {
		super(context, "SOAOrSDWRequired");
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
		public static final String btnSOAAttach = "btnSOAAttach";
		public static final String btnSOAFileAttach = "btnSOAFileAttach";
		public static final String btnchooseFileProspect = "btnchooseFileProspect";
		public static final String elmtVerifySuccessful = "elmtVerifySuccessful";
		public static final String btnCompleted = "btnCompleted";
		public static final String chkSelectbox = "chkSelectbox";
		public static final String btnSaveContinue = "btnSaveContinue";
		public static final String tabHistory = "tabHistory";
	}

	public boolean SOARequired() {
		mstatus = true;
		try {			
			if (!isLocatorVisible(Locators.btnSOAAttach, 5)) {
				iClick(Locators.tabHistory, null, "Click on History Tab");
			}
			dataDump.setValue("OrderStatus", "In-Progress");
			iClick(Locators.btnSOAAttach, null, "Click on SOA Attach Button");	
			browser.findElement(By.xpath("//input[@type='file']")).sendKeys(dataDump.getValue("dataFileName"));
			waitforPageLoadComplete();
			if (isLocatorVisible(Locators.btnSOAFileAttach, 2)) {
				clickndRelease(testLocatorClickable(Locators.btnSOAFileAttach));
				waitforPageLoadComplete();
				iVerifyText(Locators.elmtVerifySuccessful, "Successfully Uploaded File: " + getFileName(dataDump.getValue("ImportFileName")));
			}
			iSetCheckBox(Locators.chkSelectbox, true);
			iClick(Locators.btnCompleted, null, "Click on Completed button");
			if (!dataDump.getValue("GroupID").isEmpty()
					|| dataSet.getValue("prequalChecklistRequired").equalsIgnoreCase("No")) {
				iClick(Locators.btnSaveContinue, null, "Click on Save Continue button");
			}
			report.reportPassEvent("SOA Required attachement", "SOA Required attachement added successfully");
		} catch (Exception Ex) {
			mstatus = false;
			report.reportHardFailEvent("SOA Required attachement", "SOA Required attachement failed!!!", Status.FAIL);
			String eMsg = "Error in SOA Required --- " + Ex.getMessage();
			log.error(eMsg);
		}
		return mstatus;
	}

}

