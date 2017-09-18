
package com.comcast.pages.netxusa;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import com.comcast.commons.WebtopPage;
import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.utils.TestSettings;
import com.comcast.utils.DataTable;
import com.comcast.utils.IUserDetails;

/**
 * Represents default page of the application
 * 
 */

public class NetXUSAMainPage extends WebtopPage {
	FrameworkContext context;
	private IUserDetails userDetails;
	DataTable dataSet;
	private TestSettings settings;
	public static boolean NetXLogedIn = false;
	Logger log = Logger.getLogger(NetXUSAMainPage.class);

	private boolean mstatus;

	public static class Locators {
		public static final String menuOrder = "menuOrder";
		public static final String menuSalesOrders = "menuSalesOrders";
		public static final String txtSearch = "txtSearch";
		public static final String btnSearch = "btnSearch";
		public static final String lnkProcessTestOrder = "lnkProcessTestOrder";
		public static final String btnProcessTestOrder = "btnProcessTestOrder";
		public static final String msgProcessTestOrder = "msgProcessTestOrder";
	}

	public NetXUSAMainPage(FrameworkContext context) {
		super(context, "NetXUSAMainPage");
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

	public boolean launchSalesOrderMenu() {
		mstatus = true;
		try {
			browser.get(settings.get("APP_" + settings.getEnvironmentToTest() + "_URL_NETX") + "order.php?oType=Sales%20Orders");			
		} catch (Exception Ex) {
			report.reportHardFailEvent("Launch Sales Orders Menu", "Launch Sales Orders Menu Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Launch Sales Orders Menu Failed!!! " + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public boolean clickOnOrder() {
		mstatus = true;
		boolean found = false;
		int tryCount = 0;
		int MaxTry = 45;
		try {
			testLocatorClickable(Locators.txtSearch);
			String xpath = "//font[.='Allocated']/../../child::td/a[contains(@href,'searchTxt="
					+ dataDump.getValue("ChildAccountID") + "&oType=All&resultPerPage=10&resultOffset=0')]";
			while (!found) {
				try {
					tryCount = tryCount + 1;
					iEnterText(Locators.txtSearch, dataDump.getValue("ChildAccountID"), "Enter Child BAN ID");
					iClick(Locators.btnSearch, null,
							"Search CHILD BAN ID: Sales Order Search Page: Search CHILD BAN Id");
					waitforPageLoadComplete();
					found = waitForElement(browser.findElement(By.xpath(xpath)), 2);
				} catch (NoSuchElementException Ex) {
					log.warn("Sales Order ID is not comming in Netx Application. Try Count is: " + tryCount);
					if (tryCount >= MaxTry) {
						report.reportHardFailEvent("Netx order Search", "Netx Order not flowing in NetX application");
						break;
					}
				}
			}
			browser.findElement(By.xpath(xpath)).click();
			report.reportPassEvent("Click on Sales Order", "Click on Sales Order");
		} catch (Exception Ex) {
			report.reportHardFailEvent("Click on Sales Order", "Click on Sales Order Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Click on Sales Order Failed!!! " + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public boolean processTestOrder() {
		mstatus = true;
		try {
			iClick(Locators.lnkProcessTestOrder, null,
					"Click process Test Order link: Sales Order Page: Process Test Order");
			waitforPageLoadComplete();
			iClick(Locators.btnProcessTestOrder, null,
					"Click process Test Order button: Sales Order Page: Process Test Order");
			waitforPageLoadComplete();
			waitForElement(testLocatorClickable(Locators.msgProcessTestOrder));
			report.reportPassEvent("Process Test Order", "Process Test Order successfully");
		} catch (Exception Ex) {
			report.reportHardFailEvent("Process Test Order", "Process Test Order Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Process Test Order Failed!!! " + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

}

