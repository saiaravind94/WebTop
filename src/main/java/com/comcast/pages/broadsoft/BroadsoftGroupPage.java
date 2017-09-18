package com.comcast.pages.broadsoft;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.comcast.commons.WebtopPage;
import com.comcast.pages.webtop.ChooseShippingMethodForCPEOrder.Locators;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.utils.ComparisonType;

public class BroadsoftGroupPage extends WebtopPage {

	Logger log = Logger.getLogger(BroadsoftGroupPage.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public BroadsoftGroupPage(FrameworkContext context) {
		super(context, "broadsoftGroup");
		report = context.getReport();
		this.context = context;
		this.dataSet = context.getDataTable();
	}

	@Override
	protected boolean isValidPage() {
		return true;
	}

	@Override
	protected void waitForPageLoad() {

	}

	public static class Locators {
		public static final String lnkGroup = "lnkGroup";
		public static final String LnkSystem = "lnkSystem";
		public static final String DrpdnSearchWith = "DrpdnSearchWith";
		public static final String DrpdnSearchCondition = "DrpdnSearchCondition";
		public static final String TxtSearchFor = "TxtSearchFor";
		public static final String btnSearch = "btnSearch";
		public static final String lnkUsers = "lnkUsers";
		public static final String lnkResources = "lnkResources";
		public static final String lnkServices = "lnkServices";
		public static final String UserList = "UserList";
		public static final String extensionDialingDigit = "extensionDialingDigit";
		public static final String lnkClientApplications = "lnkClientApplications";
		public static final String lnkIdentityDeviceProfile = "lnkIdentityDeviceProfile";
		public static String seriviceName = "";
		public static String limit = "";
		public static String allowed = "";
		public static String inUse = "";
		public static String TNMacAddress = "";
		public static String lnkTN = "";
		public static final String lnkProfileSubLink = "lnkProfileSubLink";
		public static final String txtDeviceName = "txtDeviceName";
		public static final String txtCNameFName = "txtCNameFName";
		public static final String txtCNameLName = "txtCNameLName";


	}

	public boolean searchGroups(String searchWith, String searchCondition, String searchFor) {
		mstatus = true;
		try {
			if (!isLocatorVisible(Locators.lnkGroup, 5)) {
				iClick(Locators.LnkSystem, null, "Click on System");
			}
			iClick(Locators.lnkGroup, null, "Click on Groups link");
			report.reportPassEvent("Group", "Clicked");
			iSelectValue(Locators.DrpdnSearchWith, searchWith);
			iSelectValue(Locators.DrpdnSearchCondition, searchCondition);
			iEnterText(Locators.TxtSearchFor, searchFor);
			iClick(Locators.btnSearch, null, "Click on Search");
			waitforPageLoadComplete();
			try {
				browser.findElement(By.xpath("//a[text()='" + searchFor + "']")).click();
				report.reportPassEvent("Broadsoft Groups verfication", "Broadsoft Groups are verified");
			} catch (NoSuchElementException ex) {
				log.error("Broadsoft Group verification failed\n" + ex.getMessage());
				report.reportHardFailEvent("Broadsoft Groups verfication", "Broadsoft Group verification failed");
			}

		} catch (Exception e) {
			report.reportHardFailEvent("Group search Failed", "click Failed!!!", Status.FAIL);
			String eMsg = "Error in Group search --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean verifyUsers(String searchWith, String searchCondition, String searchFor, int expectedNoOfUsers) {
		mstatus = true;
		try {
			waitforPageLoadComplete();
			iClick(Locators.lnkUsers, null, "Click on Users link");
			iSelectValue(Locators.DrpdnSearchWith, searchWith);
			iSelectValue(Locators.DrpdnSearchCondition, searchCondition);
			iEnterText(Locators.TxtSearchFor, searchFor);
			iClick(Locators.btnSearch, null, "Click on Search");
			waitforPageLoadComplete();
			List<WebElement> usersList = browser.findElements(By.xpath("//a[text()='" + searchFor + "']"));
			if(usersList.size() == expectedNoOfUsers){
				report.reportPassEvent("Broadsoft Users verfication", "Broadsoft Users are verified");
				mstatus = true;
			}
			else
			{
				report.reportSoftFailEvent("Broadsoft Users verfication", "Broadsoft Users verification failed");
				mstatus = false;
			}
		} catch (Exception e) {
			report.reportHardFailEvent("User verification Failed", "User verification Failed!!!", Status.FAIL);
			String eMsg = "Error in User verification  --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}

	public boolean verifyGroups(String searchWith, String searchCondition, String searchFor) {
		mstatus = true;
		try {
			if (!isLocatorVisible(Locators.lnkGroup, 5)) {
				iClick(Locators.LnkSystem, null, "Click on System");
			}
			iClick(Locators.lnkGroup, null, "Click on Groups link");
			report.reportPassEvent("Group", "Clicked");
			iSelectValue(Locators.DrpdnSearchWith, searchWith);
			iSelectValue(Locators.DrpdnSearchCondition, searchCondition);
			iEnterText(Locators.TxtSearchFor, searchFor);
			iClick(Locators.btnSearch, null, "Click on Search");
			waitforPageLoadComplete();
			try {
				WebElement groupId = browser.findElement(By.xpath("//a[text()='" + searchFor + "']"));
				mstatus = true;
			} catch (NoSuchElementException ex) {
				mstatus = false;
			}
		} catch (Exception e) {
			report.reportHardFailEvent("Group search Failed", "click Failed!!!", Status.FAIL);
			String eMsg = "Error in Group search --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}

	public boolean verifyUsers() {
		mstatus = true;
		try {
			waitforPageLoadComplete();
			iClick(Locators.lnkUsers, null, "Click on Users link");
			iClick(Locators.btnSearch, null, "Click on Search");
			if (isLocatorVisible(Locators.UserList, 5)) {
				report.reportPassEvent("Broadsoft Users verfication", "Broadsoft Users are verified");
			} else {
				report.reportHardFailEvent("Broadsoft Users verfication", "Broadsoft Users verification failed",
						Status.FAIL);
			}
		}

		catch (Exception e) {
			report.reportHardFailEvent("Broadsoft Users verfication", "Broadsoft Users verification failed",
					Status.FAIL);
			String eMsg = "Error in Broadsoft Users verfication--- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}

	public boolean verify10DigitTN(String TNNo) {
		mstatus = false;
		try {
			WebElement tnNO = browser.findElement(By.xpath("//a[contains(.,'" + TNNo + "')]"));
			if (waitForElement(tnNO, 2)) {
				mstatus = true;
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return mstatus;
	}

	public boolean verifyExtensionDialingDigits(int noOfDigitsExpected) {
		mstatus = true;
		try {
			iClick(Locators.lnkUsers, null, "Click on Users link");
			iClick(Locators.btnSearch, null, "Click on Search");
			if (isLocatorVisible(Locators.extensionDialingDigit, 5)) {
				int actualValue = testLocatorClickable(Locators.extensionDialingDigit).getText().length();
				if (actualValue == noOfDigitsExpected) {
					report.reportPassEvent("Broadsoft Enterprise dialing digits verification",
							"Broadsoft Enterprise dialing digits verification Passed");
				} else {
					report.reportHardFailEvent("Broadsoft Enterprise dialing digits verification",
							"Broadsoft Enterprise dialing digits verification failed. Actual value is :" + actualValue
									+ ". But expected value will be: " + noOfDigitsExpected);
				}
			} else {
				report.reportHardFailEvent("Broadsoft Enterprise dialing digits verification",
						"Broadsoft Enterprise dialing digits verification failed", Status.FAIL);
			}
		}

		catch (Exception e) {
			report.reportHardFailEvent("Broadsoft Enterprise dialing digits verification",
					"Broadsoft Enterprise dialing digits verification failed", Status.FAIL);
			String eMsg = "Error in Broadsoft Enterprise dialing digits verification--- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}

	public boolean verifyGroupServices(String seriviceName, int limit, int allowed, int inUse) {
		mstatus = true;

		if (isLocatorVisible(Locators.lnkResources, 1)) {
			iClick(Locators.lnkResources, null, "Click on Resources link");
		}
		if (isLocatorVisible(Locators.lnkServices, 1)) {
			iClick(Locators.lnkServices, null, "Click on Services link");
		}
		addLocator(Locators.seriviceName, "xpath", "//td[.='" + seriviceName + "']", "Service Name");
		addLocator(Locators.limit, "xpath", "//td[.='" + seriviceName + "']/../child::td[5]/input[1]", "Limit");
		addLocator(Locators.allowed, "xpath", "//td[.='" + seriviceName + "']/../child::td[7]", "Allowed");
		addLocator(Locators.inUse, "xpath", "//td[.='" + seriviceName + "']/../child::td[8]", "In USe");
		try {
			// Service Name check
			if (isLocatorVisible(Locators.seriviceName, 2)) {
				report.reportPassEvent("Is " + seriviceName + "is displayed?",
						seriviceName + " is displayed as expected");
			} else {
				report.reportHardFailEvent("Is " + seriviceName + "is displayed?",
						seriviceName + " is not displayed as expected");
			}
			// Limit, allowed, InUse Check
			if (iVerifyText(Locators.limit, "" + limit + "") && iVerifyText(Locators.allowed, "" + allowed + "")
					&& iVerifyText(Locators.inUse, "" + inUse + "")) {
				report.reportPassEvent("Is " + seriviceName + "is displayed with expected values?",
						seriviceName + " is displayed with expected values");
			} else {
				report.reportHardFailEvent("Is " + seriviceName + "is displayed with expected values?",
						seriviceName + " is not displayed with expected values");
			}

		} catch (Exception e) {
			report.reportHardFailEvent("Broadsoft " + seriviceName + " verification",
					"Broadsoft " + seriviceName + " verification failed", Status.FAIL);
			String eMsg = "Error in Broadsoft " + seriviceName + " verification failed" + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean getGroupServices(String seriviceName, String beforOrAfter) {
		mstatus = true;

		if (isLocatorVisible(Locators.lnkResources, 1)) {
			iClick(Locators.lnkResources, null, "Click on Resources link");
		}
		if (isLocatorVisible(Locators.lnkServices, 1)) {
			iClick(Locators.lnkServices, null, "Click on Services link");
		}
		addLocator(Locators.seriviceName, "xpath", "//td[.='" + seriviceName + "']", "Service Name");
		addLocator(Locators.limit, "xpath", "//td[.='" + seriviceName + "']/../child::td[5]/input[1]", "Limit");
		addLocator(Locators.allowed, "xpath", "//td[.='" + seriviceName + "']/../child::td[7]", "Allowed");
		addLocator(Locators.inUse, "xpath", "//td[.='" + seriviceName + "']/../child::td[8]", "In USe");
		try {
			// Service Name check
			if (isLocatorVisible(Locators.seriviceName, 2)) {
				report.reportPassEvent("Is " + seriviceName + "is displayed?",
						seriviceName + " is displayed as expected");
			} else {
				report.reportHardFailEvent("Is " + seriviceName + "is displayed?",
						seriviceName + " is not displayed as expected");
			}
			dataDump.setValue(seriviceName + "_limit_" + beforOrAfter, iGetText(Locators.limit));
			dataDump.setValue(seriviceName + "_allowed_" + beforOrAfter, iGetText(Locators.allowed));
			dataDump.setValue(seriviceName + "_inUse_" + beforOrAfter, iGetText(Locators.inUse));
		} catch (Exception e) {
			report.reportHardFailEvent("Broadsoft " + seriviceName + " verification",
					"Broadsoft " + seriviceName + " verification failed", Status.FAIL);
			String eMsg = "Error in Broadsoft " + seriviceName + " verification failed" + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}

	public boolean verifyClientAppicationsLinkForTN(String GroupId, String TNNo) {
		mstatus = true;
		try {
			WebElement lnkGroup = browser.findElement(By.xpath("//a[text()='" + GroupId + "']"));
			if (waitForElement(lnkGroup, 1)) {
				iClick(lnkGroup, null, "Click on Group Link: Services Page: Group Link");
				waitforPageLoadComplete();
			}
			iClick(Locators.lnkUsers, null, "Click on Users link");
			iClick(Locators.btnSearch, null, "Click on Search");
			browser.findElement(By.xpath("//a[contains(.,'" + TNNo + "')]")).click();
			if (isLocatorVisible(Locators.lnkClientApplications, 5)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			report.reportHardFailEvent("Broadsoft Client Application Link verification for TN: " + TNNo,
					"Broadsoft Client Application Link verification for TN is failed", Status.FAIL);
			String eMsg = "Error in Broadsoft Client Application Link verification for TN is failed" + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}

	public String getIdentityAndProfileMACAddress(String TNNo) {
		try {
			iClick(Locators.lnkResources);
			jsClick(testLocatorClickable(Locators.lnkIdentityDeviceProfile, 3));
			iEnterText(Locators.TxtSearchFor, TNNo);
			iClick(Locators.btnSearch, null, "Click on Search");
			addLocator(Locators.TNMacAddress, "xpath", ".//a[(.='" + TNNo + "')]//following::a[4]", "MACAddress Label");
			return iGetText(Locators.TNMacAddress);
		} catch (Exception e) {
			report.reportHardFailEvent("MAC Address verification for TN: " + TNNo,
					"MAC Address verification for TN is failed", Status.FAIL);
			String eMsg = "Error in MAC Address verification for TN is failed" + e.getMessage();
			log.error(eMsg);
			return "";
		}
	}

	public String getDevcieNameForTheTN(String TNNo) {
		try {
			this.verifyUsers();
			addLocator(Locators.lnkTN, "xpath", ".//a[contains(.,'" + TNNo + "')]", "MACAddress Label");
			iClick(Locators.lnkTN);
			iClick(Locators.lnkProfileSubLink);
			return getLocatorAttribute(Locators.txtDeviceName, "value");
		} catch (Exception e) {
			report.reportHardFailEvent("Getting Device name for TN: " + TNNo, "Getting Device name for TN is failed",
					Status.FAIL);
			String eMsg = "Error in Getting Device name for TN is failed" + e.getMessage();
			log.error(eMsg);
			return "";
		}
	}
	
	public void verifyCNameForTheUser(String userName) {
		try {
			browser.findElement(By.xpath("//a[contains(.,'" + userName + "')]")).click();
			iClick(Locators.lnkProfileSubLink);
			iValidateAttributeValue(Locators.txtCNameFName, "value", dataDump.getValue("NewCNameFName"), ComparisonType.SUBSTR, "CName First Name Verification");
			iValidateAttributeValue(Locators.txtCNameLName, "value", dataDump.getValue("NewCNameLName"), ComparisonType.SUBSTR, "CName Last Name Verification");
		} catch (Exception e) {
			report.reportHardFailEvent("Verify CName update for the User: " + userName, "Verify CName update for the User is failed",
					Status.FAIL);
			String eMsg = "Error in Verify CName update for the User is failed" + e.getMessage();
			log.error(eMsg);			
		}
		
	}
}
