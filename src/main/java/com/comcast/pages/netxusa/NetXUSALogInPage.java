package com.comcast.pages.netxusa;

import org.apache.log4j.Logger;
import com.comcast.commons.WebtopPage;
import com.comcast.pages.netxusa.NetXUSAMainPage.Locators;
import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.utils.TestSettings;
import com.comcast.utils.DataTable;
import com.comcast.utils.IUserDetails;

/**
 * Represents default page of the application
 * 
 */
public class NetXUSALogInPage extends WebtopPage {
	FrameworkContext context;
	private IUserDetails userDetails;
	DataTable dataSet;
	private TestSettings settings;
	public static boolean NetXLogedIn = false;
	Logger log = Logger.getLogger(NetXUSALogInPage.class);

	private boolean mstatus;

	public static class Locators {
		public static final String netxuserName = "netxuserName";
		public static final String netxpwd = "netxpwd";
		public static final String lnkSignIn = "lnkSignIn";
		public static final String btnSignIn = "btnSignIn";
		public static final String btnSignOut = "btnSignOut";
	}

	public NetXUSALogInPage(FrameworkContext context) {
		super(context, "NetXUSALogInPage");
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

	public boolean NetXLogin() {
		mstatus = true;
		try {
			String username, password = "";
			username = userDetails.getUserName("APP_" + settings.getEnvironmentToTest() + "_UNAME_NETX");
			password = settings
					.DecodeString(userDetails.getPassword("APP_" + settings.getEnvironmentToTest() + "_PWD_NETX"));
			if (username != "" && password != "") {
				NetxLoginPage(username, password);
				NetXLogedIn = true;
			} else {
				log.error("Username/Password is empty");
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("NetX Login", "User Login NOT successful" + Ex.getMessage());
			eMsg = report.getMessage() + "NetX Login Failed!!! " + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public void NetXLogout() {
		iClick(Locators.btnSignOut, null, "Click on Logout button");
		testLocatorVisible(Locators.lnkSignIn);
		NetXLogedIn = false;
	}

	public boolean NetxLoginPage(String userName, String password) {
		mstatus = true;
		try {
			log.debug(
					"NetX Login URL is: " + settings.get("APP_" + settings.getEnvironmentToTest() + "_URL_NETX"));
			browser.get(settings.get("APP_" + settings.getEnvironmentToTest() + "_URL_NETX"));
			waitforPageLoadComplete();
			if (isLocatorVisible(Locators.lnkSignIn, 2)) {
				iClick(Locators.lnkSignIn, null, "Click on SignIn Link");
				iEnterText(Locators.netxuserName, userName, "Enter User Name");
				report.reportDoneEvent("Enter username", "Entered UserName as -> " + userName);
				iEnterText(Locators.netxpwd, password, "Enter Password");
				report.reportDoneEvent("Enter password", "Entered Password as-> " + password.replaceAll(".", "*"));
				iClick(Locators.btnSignIn, Locators.btnSignOut, "Click on SignIn button");
				report.reportDoneEvent("Click on SignIn", "SignIn Clicked successfully");
				waitforPageLoadCompletemillisec();
				report.reportPassEvent("NetX Login", "NetX Login Sucessful");
			}

		} catch (Exception Ex) {
			report.reportHardFailEvent("NetX Login",
					"User Login NOT successful, EXCEPTION CAUGHT : " + Ex.getMessage());
			eMsg = report.getMessage() + "NetX Login Failed!!! " + Ex.getMessage();
			mstatus = false;
			log.error(Ex.getMessage());
		}
		return mstatus;
	}

}
