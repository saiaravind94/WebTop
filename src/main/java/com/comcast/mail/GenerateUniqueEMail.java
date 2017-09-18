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
public class GenerateUniqueEMail extends WebtopPage {
	FrameworkContext context;
	DataTable dataSet;
	Logger log = Logger.getLogger(GenerateUniqueEMail.class);
	TestSettings settings;
	private boolean mstatus;

	public static class Locators {
		public static final String txtloginId = "txtloginId";
		public static final String btnGo = "btnGo";		

	}

	public GenerateUniqueEMail(FrameworkContext context) {
		super(context, "GenerateUniqueEMail");
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

	public void generateUniqueEMail() {
		String mailIdsList = "";
		String uniqueEmail="";
		for(int idx = 0; idx < Integer.parseInt(dataSet.getValue("NoOfMailsToWhiteList"));idx++){
			uniqueEmail="";
			browser.get(settings.get("APP_URL_MAIL"));
			browser.manage().deleteAllCookies();		
			if (isLocatorVisible(Locators.txtloginId, 60)) {
				uniqueEmail = "Auto_" + getTimestamp();
				iEnterText(Locators.txtloginId, uniqueEmail);
				iClick(Locators.btnGo, null, "Click On GO button:Mailinator page: Go button");
				uniqueEmail = uniqueEmail + "@maildrop.cc";
				report.reportPassEvent("Unique Mail id creation", "Unique Mail id: "+uniqueEmail+" created");
				if((idx+1) == Integer.parseInt(dataSet.getValue("NoOfMailsToWhiteList")))
					mailIdsList += uniqueEmail;
				else
					mailIdsList += uniqueEmail + "-";							
				}
			
			else {
				
				log.error("Mail Home page is not displayed after 60 seconds");
				report.reportHardFailEvent("Mail Home page", "Mail Home page is not displayed after 60 seconds");
			}			
		}
		dataDump.setValue("MailIdList", mailIdsList);
	}
	
	public void generateOneUniqueEMail() {	
		String uniqueEmail="";	
		browser.get(settings.get("APP_URL_MAIL"));
		browser.manage().deleteAllCookies();		
		if (isLocatorVisible(Locators.txtloginId, 60)) {
		uniqueEmail = "Auto_" + getTimestamp();
		iEnterText(Locators.txtloginId, uniqueEmail);
		iClick(Locators.btnGo, null, "Click On GO button:Mailinator page: Go button");
		uniqueEmail = uniqueEmail + "@maildrop.cc";
		report.reportPassEvent("Unique Mail id creation", "Unique Mail id: "+uniqueEmail+" created");
		dataDump.setValue("MACDSeatMailID", uniqueEmail);
		}
		else {
			
			log.error("Mail Home page is not displayed after 60 seconds");
			report.reportHardFailEvent("Mail Home page", "Mail Home page is not displayed after 60 seconds");
		}
	}
}
