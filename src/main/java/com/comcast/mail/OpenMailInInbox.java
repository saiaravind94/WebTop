package com.comcast.mail;

import java.util.Random;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import com.comcast.commons.WebtopPage;
import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.utils.DataTable;
import com.comcast.utils.TestSettings;

/**
 * Represents default page of the application
 * 
 */
public class OpenMailInInbox extends WebtopPage {
	FrameworkContext context;
	DataTable dataSet;
	Logger log = Logger.getLogger(OpenMailInInbox.class);
	TestSettings settings;
	private boolean mstatus;
	int MAXTRY = 60;

	public static class Locators {
		public static final String txtloginId = "txtloginId";
		public static final String btnGo = "btnGo";
		public static final String lnkPMMailInBoxLink = "lnkPMMailInBoxLink";
		public static final String frameMailBody = "frameMailBody";
		public static final String lnkComcastBusinessAccount = "lnkComcastBusinessAccount";		
		public static final String lnkSUMailInBoxLink = "lnkSUMailInBoxLink";
		public static final String lnkActivateNow = "lnkActivateNow";		
		public static final String btnCheckForMails = "btnCheckForMails";
	}

	public OpenMailInInbox(FrameworkContext context) {
		super(context, "OpenMailInInbox");
		report = context.getReport();
		this.context = context;
		this.dataSet = context.getDataTable();
		context.getUserDetail();
		settings = context.getSettings();
	}

	@Override
	protected boolean isValidPage() {
		return true;
	}

	@Override
	protected void waitForPageLoad() {

	}

	public void openPMMailInInbox(String mailId) {
		browser.manage().deleteAllCookies();
		browser.get(settings.get("APP_URL_MAIL"));		
		int tryCount = 0;
		if (isLocatorVisible(Locators.txtloginId, 60)) {				
			iEnterText(Locators.txtloginId, mailId);
			iClick(Locators.btnGo, null, "Click On GO button: Maildrop page: Go button");
		}			
		while(!isLocatorVisible(Locators.lnkPMMailInBoxLink, 1) && (tryCount <= MAXTRY)){
			tryCount= tryCount + 1;
			sleep(1000);				
			iClick(Locators.btnCheckForMails);				
			if(tryCount == MAXTRY)
			{
				log.error("Mail not received for the user: " + mailId);
				report.reportHardFailEvent("Verify E-Mail for the user: " + mailId, "Mail not received for the user: " + mailId);
			}
		}			
		iClick(Locators.lnkPMMailInBoxLink);
		WaitandSwitchToFrame(getLocatorWEList(Locators.frameMailBody).get(0));			
		String getlinkURL = getLocatorAttribute(Locators.lnkComcastBusinessAccount, "href");
		browser.get(getlinkURL.replace("business.int.comcast.com", settings.get("APP_URL_BCP")));
		waitforPageLoadComplete();
		browser.switchTo().defaultContent();
	}
	
	public void openSUMailInInbox(String mailId) {					
		browser.get(settings.get("APP_URL_MAIL"));		
		int tryCount = 0;
		if (isLocatorVisible(Locators.txtloginId, 60)) {				
			iEnterText(Locators.txtloginId, mailId);
			iClick(Locators.btnGo, null, "Click On GO button: Maildrop page: Go button");
		}		
		while(!isLocatorVisible(Locators.lnkSUMailInBoxLink, 1) && (tryCount <= MAXTRY)){
			tryCount= tryCount + 1;
			sleep(1000);			
			iClick(Locators.btnCheckForMails);			
			if(tryCount == MAXTRY)
			{
				log.error("Mail not received for Service USer");
				report.reportHardFailEvent("Verify Service USer E-Mail", "Mail not received for Primary Manager");
			}
		}
		iClick(Locators.lnkSUMailInBoxLink);		
		WaitandSwitchToFrame(testLocatorClickable(Locators.frameMailBody, 10));
		String getlinkURL = getLocatorAttribute(Locators.lnkActivateNow, "href");
		browser.get(getlinkURL.replace("business.int.comcast.com", settings.get("APP_URL_MAIL")));
		waitforPageLoadComplete();
		browser.switchTo().defaultContent();
		report.reportPassEvent("Open Mail", "Opened the Mail sucessfully and launch the BCP Portal");
}
}
