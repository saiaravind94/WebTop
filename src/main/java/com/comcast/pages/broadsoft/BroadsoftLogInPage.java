package com.comcast.pages.broadsoft;

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
public class BroadsoftLogInPage extends WebtopPage {
	FrameworkContext context;
	private IUserDetails userDetails;
	DataTable dataSet;
	private TestSettings settings;
	public static boolean broadsoftLogedIn = false;
	Logger log = Logger.getLogger(BroadsoftLogInPage.class);

	private boolean mstatus;

	public static class Locators {
		public static final String txtbroadsoftUsername = "txtbroadsoftUsername";
		public static final String txtbroadsoftPwd = "txtbroadsoftPwd";
		public static final String btnbroadsoftogin = "btnbroadsoftLogin";
		public static final String btnbroadsoftLogout = "btnbroadsoftLogout";

	}

	public BroadsoftLogInPage(FrameworkContext context) {
		super(context, "broadsoftLogInPage");
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

	public boolean broadsoftLogin() {
		mstatus = true;
		try {
			String username, password = "";
			username = userDetails.getUserName("APP_" + settings.getEnvironmentToTest() + "_UNAME_BROADSOFT");
			password = settings
					.DecodeString(userDetails.getPassword("APP_" + settings.getEnvironmentToTest() + "_PWD_BROADSOFT"));
			if (username != "" && password != "") {
				broadsoftLoginPage(username, password);
				broadsoftLogedIn = true;
			} else {
				log.error("Username/Password is empty");
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Broadsoft Login", "User Login NOT successful" + Ex.getMessage());
			eMsg = report.getMessage() + "Broadsoft Login Failed!!! " + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public void broadsoftLogout() {
		if (isLocatorVisible(Locators.btnbroadsoftLogout, 2)) {
			iClick(Locators.btnbroadsoftLogout, null, "Click on Logout button");
			broadsoftLogedIn = false;
		}

	}

	public void closeBrowser() {
		browser.close();
		sleep(5000);
	}

	public boolean broadsoftLoginPage(String userName, String password) {
		mstatus = true;
		try {
			log.debug("BROAD SOFT Login URL is: "
					+ settings.get("APP_" + settings.getEnvironmentToTest() + "_URL_BROADSOFT"));
			browser.get(settings.get("APP_" + settings.getEnvironmentToTest() + "_URL_BROADSOFT"));
			waitforPageLoadComplete();
			if (isLocatorVisible(Locators.txtbroadsoftUsername, 3)) {
				iEnterText(Locators.txtbroadsoftUsername, userName, "Enter User Name");
				report.reportDoneEvent("Enter username", "Entered UserName as -> " + userName);
				iEnterText(Locators.txtbroadsoftPwd, password, "Enter Password");
				report.reportDoneEvent("Enter password", "Entered Password as-> " + password.replaceAll(".", "*"));
				iClick(Locators.btnbroadsoftogin, Locators.btnbroadsoftLogout, "Click on Login button");
				report.reportDoneEvent("Click on Login", "Login Clicked successfully");
				waitforPageLoadCompletemillisec();
				report.reportPassEvent("broadsoft Login", "broadsoft Login Sucessful");
			}

		} catch (Exception Ex) {
			report.reportHardFailEvent("broadsoft Login",
					"User Login NOT successful, EXCEPTION CAUGHT : " + Ex.getMessage());
			eMsg = report.getMessage() + "broadsoft Login Failed!!! " + Ex.getMessage();
			mstatus = false;
			log.error(Ex.getMessage());
		}
		return mstatus;
	}

}
