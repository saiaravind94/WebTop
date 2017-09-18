
package com.comcast.pages.webtop;

import com.comcast.commons.WebtopPage;
import com.comcast.pages.webtop.WebTopLogInPage.Locators;
import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.utils.DataTable;
import com.comcast.utils.Page;
import com.comcast.utils.TestSettings;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import java.awt.AWTException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class WebTopMainPage extends WebtopPage {
	Logger log = Logger.getLogger(WebTopMainPage.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;
	private TestSettings settings;
	
	public WebTopMainPage(FrameworkContext context) {
		super(context, "WebTopMainPage");
		report = context.getReport();
		this.context = context;
		this.dataSet = context.getDataTable();
		this.settings = context.getSettings();
		log.debug("Done calling WebToppage");
	}

	@Override
	protected boolean isValidPage() {
		return true;
	}

	public static class Locators {
		public static final String menubar = "menuAdmin";
		public static final String utilitiesmenu = "menuUtilities";
		public static final String fileimportmenu = "menuFileImport";
		public static final String lstSearch = "lstSearch";
		public static final String txtFor = "txtFor";
		public static final String btnGo = "btnGo";
		public static final String lnkOrderId = "lnkOrderId";
		public static final String lnkInprogressOrder = "lnkInprogressOrder";
	}

	public boolean searchFor(String searchFor) {
		mstatus = true;
		String temp="";
		try {
			iSelectValue(Locators.lstSearch, searchFor);
			switch (searchFor) {
			case "Salesforce Opportunity ID(FID)":
				iEnterText(Locators.txtFor, dataDump.getValue("SFOpportunityId"), "Enter SF Opportunity ID");
				temp = dataDump.getValue("SFOpportunityId");
				break;
			case "Group ID":
				iEnterText(Locators.txtFor, dataDump.getValue("GroupID"), "Enter Group ID");
				temp = dataDump.getValue("GroupID");
				break;
			default:
				log.error("Invalid options for search");
			}
			iClick(Locators.btnGo, null, "Click on Go Button:WebTop Main Page: Go Button:");
			waitforPageLoadComplete();
			report.reportPassEvent("Search with " + searchFor +": "+ temp, "WebTop Main Page");
			if (isLocatorVisible(Locators.lnkOrderId, 2)) {
				jsClick(testLocatorClickable(Locators.lnkOrderId));
			} else if (isLocatorVisible(Locators.lnkInprogressOrder, 2)) {
				iClick(Locators.lnkInprogressOrder, null, "Click on the Open Order link");
			} else {
				browser.findElement(By.xpath(".//a[text()='" + dataDump.getValue("GroupID") + "']")).click();
				waitforPageLoadComplete();
				iClick(Locators.lnkInprogressOrder, null, "Click on the Open Order link");
			}
		} catch (Exception e) {
			report.reportHardFailEvent("Search with " + searchFor, "Search with " + searchFor + " failed!!!");
			String eMsg = "Error in Search Opportunity ID --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}

	public boolean searchOrder(String searchFor) {
		mstatus = true;
		String temp="";
		try {
			iSelectValue(Locators.lstSearch, searchFor);
			switch (searchFor) {
			case "Salesforce Opportunity ID(FID)":
				iEnterText(Locators.txtFor, dataDump.getValue("SFOpportunityId"), "Enter SF Opportunity ID");
				temp = dataDump.getValue("SFOpportunityId");
				break;
			case "Group ID":
				iEnterText(Locators.txtFor, dataDump.getValue("GroupID"), "Enter Group ID");
				temp = dataDump.getValue("GroupID");
				break;
			default:
				log.error("Invalid options for search");
			}
			iClick(Locators.btnGo, null, "Click on Go Button:WebTop Main Page: Go Button:");
			waitforPageLoadComplete();
			try {
				WebElement groupId = browser
						.findElement(By.xpath(".//a[text()='" + dataDump.getValue("GroupID") + "']"));
				if (groupId != null) {
					jsClick(groupId);
				}
			} catch (NoSuchElementException ex) {

			}

			report.reportPassEvent("Search with " + searchFor +": " + temp, "WebTop Main Page");
		} catch (Exception e) {
			report.reportHardFailEvent("Search with " + searchFor, "Search with " + searchFor + " failed!!!");
			String eMsg = "Error in Search Opportunity/Group ID --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}

	public boolean launchFileImportMenu() throws AWTException, InterruptedException {
		mstatus = true;
		try {
			log.info("Launch Admin-->Utilities-->File Import Menu");
			browser.get(settings.get("APP_" + settings.getEnvironmentToTest() + "_URL_WEBTOP") + "utilities/fileimport/fileimport.jsf?refresh=yes");
			} 
		catch (Exception Ex) {
			report.reportHardFailEvent("Launch File Import Menu",
					"Launch File Import Menu Failed!!!" + Ex.getMessage());
			eMsg = report.getMessage() + "Launch File Import Menu Failed!!! " + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}
	
	public boolean verifyIfOpenOrderGenerated() {
		mstatus = true;
		Boolean openOrderFound = false;
		try {
			iSelectValue(Locators.lstSearch, "Group ID");
			iEnterText(Locators.txtFor, dataDump.getValue("GroupID"), "Enter Group ID");
			iClick(Locators.btnGo, null, "Click on Go Button:WebTop Main Page: Go Button:");
			waitforPageLoadComplete();
			if(isLocatorVisible(Locators.lnkInprogressOrder, 2))
			{
				openOrderFound = true;
			}			
			mstatus = openOrderFound;
		} catch (Exception e) {
			report.reportHardFailEvent("Verify If Open order generated or not","Verify If Open order generated or not failed!!!");
			String eMsg = "Error in Verify If Open order generated or not --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
	
}

