package com.comcast.pages.webtop;

import org.apache.log4j.Logger;
import com.comcast.commons.WebtopPage;
import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.utils.TestSettings;
import com.comcast.utils.DataTable;
import com.comcast.utils.IUserDetails;

/**
 * Represents default page of the application
 * 
 */
public class WebTopLogInPage extends WebtopPage {
	FrameworkContext context;
	private IUserDetails userDetails;
	DataTable dataSet;
	private TestSettings settings;
	public static Boolean webTopLogedIn = false;

	Logger log = Logger.getLogger(WebTopLogInPage.class);

	private boolean mstatus;

	public static class Locators {
		public static final String txtWebTopUsername = "txtWebTopUsername";
		public static final String txtWebTopPwd = "txtWebTopPwd";
		public static final String btnWebTopLogin = "btnWebTopLogin";
		public static final String btnWebtopLogout = "btnWebtopLogout";
		public static final String txtBVEText = "txtBVEText";
		public static final String btnGo = "btnGo";
	}

	public WebTopLogInPage(FrameworkContext context) {
		super(context, "WebTopLogInPage");
		report = context.getReport();
		this.context = context;
		this.dataSet = context.getDataTable();
		this.userDetails = context.getUserDetail();
		this.settings = context.getSettings();
	}

	@Override
	protected boolean isValidPage() {
		return true;
	}

	@Override
	protected void waitForPageLoad() {

	}

	public boolean Signout() {
		return mstatus;
	}

	public boolean webTopLogin() {
		mstatus = true;
		try {
			String username, password = "";
			username = userDetails.getUserName("APP_" + settings.getEnvironmentToTest() + "_UNAME_WEBTOP");
			password = settings
					.DecodeString(userDetails.getPassword("APP_" + settings.getEnvironmentToTest() + "_PWD_WEBTOP"));
			if (username != "" && password != "") {
				webTopLoginPage(username, password);
				webTopLogedIn = true;
			} else {
				log.error("Username/Password is empty");
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("WebTop Login", "User Login NOT successful" + Ex.getMessage());
			eMsg = report.getMessage() + "Webtop Login Failed!!! " + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);

		}
		return mstatus;
	}

	public void logout() {
		if (isLocatorVisible(Locators.btnWebtopLogout, 2)) {
			iClick(Locators.btnWebtopLogout, null, "Click on Logout button");
			testLocatorVisible(Locators.txtWebTopUsername);
			webTopLogedIn = false;
		}
	}

	public void closeBrowser() {
		browser.close();
		sleep(5000);
	}

	public boolean webTopLoginPage(String userName, String password) {
		mstatus = true;
		try {
			log.debug("WebTop Login URL is: "
					+ settings.get("APP_" + settings.getEnvironmentToTest() + "_URL_WEBTOP"));
			browser.get(settings.get("APP_" + settings.getEnvironmentToTest() + "_URL_WEBTOP"));
			waitforPageLoadComplete();
			if (isLocatorVisible(Locators.txtBVEText, 2)) {
				iEnterText(Locators.txtBVEText, "BVE", "Entered BVE in home page");
				iClick(Locators.btnGo, null, "Click GO Button:WebTopHomePage:GO Button");
			}
			iEnterText(Locators.txtWebTopUsername, userName, "Enter User Name");
			report.reportDoneEvent("Enter UserName", "Entered UserName as-> " + userName);
			iEnterText(Locators.txtWebTopPwd, password, "Enter Password");
			report.reportDoneEvent("Enter Password", "Entered Password as-> " + password.replaceAll(".", "*"));
			iClick(Locators.btnWebTopLogin, null, "Click on Login button");
			report.reportDoneEvent("Click on Login", "Login Clicked successfully");
			waitforPageLoadCompletemillisec();
			report.reportPassEvent("WebTop Login", "WebTop Login Sucessful");
		} catch (Exception Ex) {
			report.reportHardFailEvent("WebTop Login",
					"User Login NOT successful, EXCEPTION CAUGHT : " + Ex.getMessage());
			eMsg = report.getMessage() + "Webtop Login Failed!!! " + Ex.getMessage();
			mstatus = false;
			log.error(Ex.getMessage());
		}
		return mstatus;
	}

}
