package com.comcast.pages.simon;

import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import com.comcast.commons.WebtopPage;
import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.utils.TestSettings;
import com.comcast.utils.TestUtils;
import com.comcast.utils.WebDriverManager;
import com.comcast.utils.DataTable;
import com.comcast.utils.IUserDetails;

/**
 * Represents default page of the application
 * 
 */
public class SimonLogInPage extends WebtopPage {
	FrameworkContext context;
	private IUserDetails userDetails;	
	DataTable dataSet;
	private TestSettings settings;
	public static boolean simonLogedIn = false;
	Logger log = Logger.getLogger(SimonLogInPage.class);

	private boolean mstatus;

	public static class Locators {
		public static final String txtSIMONUsername = "txtSIMONUsername";
		public static final String txtSIMONPwd = "txtSIMONPwd";
		public static final String btnSIMONLogin = "btnSIMONLogin";
		public static final String btnSIMONLogout = "btnSIMONLogout";
		public static final String btnSIMONConfirm = "btnSIMONConfirm";
	}

	public SimonLogInPage(FrameworkContext context) {
		super(context, "SimonLogInPage");
		report = context.getReport();
		this.context = context;
		this.dataSet = context.getDataTable();
		this.userDetails = context.getUserDetail();
		this.settings = context.getSettings();
		browser = context.getDriver();
	}

	@Override
	protected boolean isValidPage() {
		return true;
	}

	@Override
	protected void waitForPageLoad() {

	}

	public boolean simonLogin() {
		mstatus = true;
		try {
			String username, password = "";
			username = userDetails.getUserName("APP_" + settings.getEnvironmentToTest() + "_UNAME_SIMON");
			password = settings
					.DecodeString(userDetails.getPassword("APP_" + settings.getEnvironmentToTest() + "_PWD_SIMON"));
			if (username != "" && password != "") {
				simonLoginPage(username, password);
				simonLogedIn = true;
			} else {
				log.error("Username/Password is empty");
				report.reportHardFailEvent("SIMON Login", "Username/Password is empty");
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("SIMON Login", "User Login NOT successful" + Ex.getMessage());
			eMsg = report.getMessage() + "SIMON Login Failed!!! " + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public void simonLogout() {
		int tryCount = 0;
		while(isLocatorVisible(Locators.btnSIMONLogout, 1) && (tryCount <= 5)){
			iClick(Locators.btnSIMONLogout, null, "Click on Logout button");
			iClick(Locators.btnSIMONConfirm, null, "Click on Confirm button");
			tryCount = tryCount + 1;
			isLocatorVisible(Locators.txtSIMONUsername, 1);
		}		
	}

	public boolean simonLoginPage(String userName, String password) {
		mstatus = true;
		try {
			Set<Cookie> cookies = browser.manage().getCookies();				
			for (Cookie cookie : cookies) {
				log.info("Cookie: " + cookie);
				browser.manage().deleteCookieNamed(cookie.getName());
			}
			browser.manage().deleteAllCookies();
			log.debug("SIMON Login URL is: " 
					+ settings.get("APP_" + settings.getEnvironmentToTest() + "_URL_SIMON"));
			browser.get(settings.get("APP_" + settings.getEnvironmentToTest() + "_URL_SIMON"));
			waitforPageLoadComplete();
			iEnterText(Locators.txtSIMONUsername, userName, "Enter User Name");
			report.reportDoneEvent("Enter username", "Entered UserName as -> " + userName);
			iEnterText(Locators.txtSIMONPwd, password, "Enter Password");
			report.reportDoneEvent("Enter password", "Entered Password as-> " + password.replaceAll(".", "*"));
			iClick(Locators.btnSIMONLogin, Locators.btnSIMONLogout, "Click on Login button");
			report.reportDoneEvent("Click on Login", "Login Clicked successfully");
			waitforPageLoadCompletemillisec();
			report.reportPassEvent("SIMON Login", "SIMON Login Sucessful");

		} catch (Exception Ex) {
			report.reportHardFailEvent("SIMON Login",
					"User Login NOT successful, EXCEPTION CAUGHT : " + Ex.getMessage());
			eMsg = report.getMessage() + "SIMON Login Failed!!! " + Ex.getMessage();
			mstatus = false;
			log.error(Ex.getMessage());
		}
		return mstatus;
	}
	
	public boolean openNewBrowserInstance(String userName, String password) {
		mstatus = true;
		try {
			String	webDriversDir = TestUtils.getRelativePath() + "/src/main/resources/BrowserSpecificDrivers";
			String chromeProfile = context.getParamValue("APP_CHROME_PROFILE");
			String chromeArguments = context.getParamValue("APP_CHROME_ARGUMENTS");
			browser = new WebDriverManager().getChromeDriver(webDriversDir, chromeProfile, chromeArguments);
			browser = context.setDriver(browser);
			report = context.getReport();
			report.setDriver(browser);
			simonLoginPage(userName, password);			
		} catch (Exception Ex) {
			report.reportHardFailEvent("Open New TAB in the same chrome instance",
					"Open New TAB in the same chrome instance, EXCEPTION CAUGHT : " + Ex.getMessage());
			eMsg = report.getMessage() + "Open New TAB in the same chrome instance Failed!!! " + Ex.getMessage();
			mstatus = false;
			log.error(Ex.getMessage());
		}
		return mstatus;
	}

}
