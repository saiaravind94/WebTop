package com.comcast.bcp;

import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.comcast.commons.WebtopPage;
import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.utils.DataTable;
import com.comcast.utils.TestSettings;

/**
 * Represents default page of the application
 * 
 */
public class BCPLogin extends WebtopPage {
	FrameworkContext context;
	DataTable dataSet;
	Logger log = Logger.getLogger(BCPLogin.class);
	TestSettings settings;

	public static class Locators {
		public static final String txtEmail = "txtEmail";
		public static final String txtPassword = "txtPassword";
		public static final String txtCaptcha = "txtCaptcha";
		public static final String btnSignIn = "btnSignIn";
		public static final String lnkSignOut = "lnkSignOut";
		public static final String lnkPleaseTryAgain = "lnkPleaseTryAgain";
		public static final String lnkGetStarted = "lnkGetStarted";
		public static final String txtVMPin = "txtVMPin";
		public static final String txtConfirmVMPin = "txtConfirmVMPin";
		public static final String btnSaveContinue = "btnSaveContinue";
		public static final String btnContinue = "btnContinue";
		public static final String lnkSkip = "lnkSkip";
		public static final String btnContinueToDashboard = "btnContinueToDashboard";
		public static final String imgMyAccount = "imgMyAccount";
		public static final String lnkThanks = "lnkThanks";
		public static String lnkSignIn = "";
		
	}

	public BCPLogin(FrameworkContext context) {
		super(context, "BCPLogin");
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

	public void loginBCP(String emailID) throws InterruptedException {
		int MAXTRY = 3;
		int trycount = 0;
		try {
			String currentUrl = browser.getCurrentUrl();
			log.info(currentUrl);			
			currentUrl = currentUrl.replace("business.int.comcast.com", settings.get("APP_URL_BCP"));
			log.info(currentUrl);			
			if (isLocatorVisible(Locators.txtEmail, 5)) {
				browser.get(currentUrl);
				iEnterText(Locators.txtEmail, emailID);
				iEnterText(Locators.txtPassword, "Welcome1");
				if (isLocatorVisible(Locators.txtCaptcha, 2))
					iEnterText(Locators.txtCaptcha, "answer");
				iClick(Locators.btnSignIn);
			}			
			while (isLocatorVisible(Locators.lnkPleaseTryAgain, 1) && (trycount <= MAXTRY)) {
				iClick(Locators.lnkPleaseTryAgain);
				waitforPageLoadComplete();
				trycount = trycount + 1;
			}
			if (isLocatorVisible(Locators.lnkGetStarted, 1)) {
				iClick(Locators.lnkGetStarted);
				iEnterText(Locators.txtVMPin, "1234");
				iEnterText(Locators.txtConfirmVMPin, "1234");
				iClick(Locators.btnSaveContinue);
				iClick(Locators.btnContinue);
				iClick(Locators.lnkSkip);
				iClick(Locators.btnContinueToDashboard);
				waitforPageLoadComplete();
			}
			while (isLocatorVisible(Locators.lnkThanks, 1)){
				jsClick(getLocatorWEList(Locators.lnkThanks).get(0));
			}

			if (isLocatorVisible(Locators.imgMyAccount, 2)
					|| waitForElement(getLocatorWEList(Locators.lnkSignOut).get(0), 1)) {
				report.reportPassEvent("User: " + emailID + " Login", "User: " + emailID + " logged-in successfull");								
			} else {
				report.reportSoftFailEvent("User: " + emailID + " Login", "User: " + emailID + " logged-in failed");
			}
		} catch (Exception ex) {
			report.reportHardFailEvent("User: " + emailID + " Login ", "User: " + emailID + " logged-in failed");
			String eMsg = "Error in User: " + emailID + " Login in BCP Portal --- " + ex.getMessage();
			log.error(eMsg);
		}

	}
	
	public boolean signOut(){
		mstatus = true;
		try{
			Set<Cookie> cookies = browser.manage().getCookies();				
			for (Cookie cookie : cookies) {
				log.info("Cookie: " + cookie);
				browser.manage().deleteCookieNamed(cookie.getName());
			}
			browser.manage().deleteAllCookies();
			if (waitForElement(getLocatorWEList(Locators.lnkSignOut).get(0), 1))
				jsClick(getLocatorWEList(Locators.lnkSignOut).get(0));
			else {
				if (isLocatorVisible(Locators.imgMyAccount, 1))
					iClick(Locators.imgMyAccount);
				jsClick(getLocatorWEList(Locators.lnkSignOut).get(0));
			}
			waitforPageLoadComplete();
			addLocator(Locators.lnkSignIn, "xpath", "//a[.='Sign In']", "btnSignIn");
			isLocatorVisible(Locators.lnkSignIn, 30);
		}
		catch(Exception ex)
		{
			report.reportHardFailEvent("User Signout", "Signout Failed");
			String eMsg = "Error in Signout BCP Portal --- " + ex.getMessage();
			log.error(eMsg);
		}
		return mstatus;
		
	}

	protected boolean waitForPresenseOfElement(String locId, int seconds) {
		try {
			new WebDriverWait(browser, seconds).until(ExpectedConditions.presenceOfElementLocated(By.xpath(locId)));
			return true;
		} catch (Exception ex) {
			log.error(ex.getMessage());
			return false;
		}
	}

	protected boolean waitForVisibilityOfElement(String locId, int seconds) {
		try {
			new WebDriverWait(browser, seconds).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locId)));
			return true;
		} catch (Exception ex) {
			log.error(ex.getMessage());
			return false;
		}
	}
}
