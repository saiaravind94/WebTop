package com.comcast.bcp;

import java.util.Random;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import com.comcast.commons.WebtopPage;
import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.utils.DataTable;
import com.comcast.utils.IUserDetails;
import com.comcast.utils.TestSettings;

/**
 * Represents default page of the application
 * 
 */
public class WhiteListEmail extends WebtopPage {
	FrameworkContext context;
	DataTable dataSet;
	Logger log = Logger.getLogger(WhiteListEmail.class);
	TestSettings settings;
	private IUserDetails userDetails;
	private boolean mstatus;

	public static class Locators {
		public static final String drpdwnRegion = "drpdwnRegion";
		public static final String optnEmail = "optnEmail";
		public static final String txtInput = "txtInput";
		public static final String btnwhitelistEMail = "btnwhitelistEMail";
		public static final String txtResult = "txtResult";
		public static final String txtNTLogin = "txtNTLogin";
		public static final String txtNTLoginPassword = "txtNTLoginPassword";

	}

	public WhiteListEmail(FrameworkContext context) {
		super(context, "BCPPage");
		report = context.getReport();
		this.context = context;
		this.dataSet = context.getDataTable();
		this.userDetails = context.getUserDetail();
		settings = context.getSettings();
	}

	@Override
	protected boolean isValidPage() {
		return true;
	}

	@Override
	protected void waitForPageLoad() {

	}

	public void whiteListEmail() throws InterruptedException {
		String[] ids = dataDump.getValue("MailIdList").split("-");
		for (int idx = 0; idx < ids.length; idx++) {
			browser.get(settings.get("APP_URL_WHITELIST"));
			if (isLocatorVisible(Locators.drpdwnRegion, 60)) {
				iSelectValue(Locators.drpdwnRegion, "STG");
				iClickRadioButton(Locators.optnEmail, true);				
				iEnterText(Locators.txtInput, ids[idx]);
				if (isLocatorVisible(Locators.txtNTLogin, 1)) {
					iEnterText(Locators.txtNTLogin,
							userDetails.getUserName("APP_" + settings.getEnvironmentToTest() + "_UNAME_SIMON"));
					iEnterText(Locators.txtNTLoginPassword, settings.DecodeString(
							userDetails.getPassword("APP_" + settings.getEnvironmentToTest() + "_PWD_SIMON")));
				}
				iClick(Locators.btnwhitelistEMail, Locators.txtResult,
						"Click On WhiteList Email button:WhiteList Page:WhiteList Email Button");
				if (iVerifyText(Locators.txtResult, "Succeed to Insert")) {
					log.info("Email: " + ids[idx] + " Whitlisted Sucessfully");
					report.reportPassEvent("White List Email",
							ids[idx] + " Email Id is whiteListed");
				} else {
					log.error("Sorry! This Email id " + ids[idx]
							+ " already whitelisted and we an not use further.");
					report.reportHardFailEvent("White List Email Id verification",
							"Sorry This Email id:" + ids[idx] + " already whitelisted");
				}
				
			} else {
				log.error("WhiteList Email home page is not displayed after 60 seconds");
				report.reportHardFailEvent("WhiteList Email home page",
						"WhiteList Email home page is not displayed after 60 seconds");
			}
		}
		
	}
	
	public void whiteListOneEmail(String eMail) throws InterruptedException {		
			browser.get(settings.get("APP_URL_WHITELIST"));
			if (isLocatorVisible(Locators.drpdwnRegion, 60)) {
				iSelectValue(Locators.drpdwnRegion, "STG");
				iClickRadioButton(Locators.optnEmail, true);				
				iEnterText(Locators.txtInput, eMail);
				if (isLocatorVisible(Locators.txtNTLogin, 1)) {
					iEnterText(Locators.txtNTLogin,
							userDetails.getUserName("APP_" + settings.getEnvironmentToTest() + "_UNAME_SIMON"));
					iEnterText(Locators.txtNTLoginPassword, settings.DecodeString(
							userDetails.getPassword("APP_" + settings.getEnvironmentToTest() + "_PWD_SIMON")));
				}
				iClick(Locators.btnwhitelistEMail, Locators.txtResult,
						"Click On WhiteList Email button:WhiteList Page:WhiteList Email Button");
				if (iVerifyText(Locators.txtResult, "Succeed to Insert")) {
					log.info("Email: " + eMail + " Whitlisted Sucessfully");
					report.reportPassEvent("White List Email",
							eMail + " Email Id is whiteListed");
				} else {
					log.error("Sorry! This Email id " + eMail
							+ " already whitelisted and we an not use further.");
					report.reportHardFailEvent("White List Email Id verification",
							"Sorry This Email id:" + eMail + " already whitelisted");
				}
				
			} else {
				log.error("WhiteList Email home page is not displayed after 60 seconds");
				report.reportHardFailEvent("WhiteList Email home page",
						"WhiteList Email home page is not displayed after 60 seconds");
			}
		
		
	}
}
