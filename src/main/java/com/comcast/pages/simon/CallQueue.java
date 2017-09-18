package com.comcast.pages.simon;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.server.handler.interactions.MouseMoveToLocation;

import com.comcast.commons.WebtopPage;
import com.comcast.pages.simon.DirectoryListings.Locators;
import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.utils.TestSettings;
import com.comcast.utils.DataTable;
import com.comcast.utils.IUserDetails;

/**
 * Represents default page of the application
 * 
 */
public class CallQueue extends WebtopPage {
	FrameworkContext context;
	DataTable dataSet;
	Logger log = Logger.getLogger(CallQueue.class);

	private boolean mstatus;

	public static class Locators {
		public static final String navCallQueue = "navCallQueue";
		public static final String spinner = "spinner";
		public static String lnkConfigureCallQueue = "";
		public static final String inpCallQueueName = "inpCallQueueName";
		public static final String inpExt = "inpExt";
		public static final String btnSettings = "btnSettings";
		public static final String drpTimeZone = "drpTimeZone";
		public static final String radCallQueueRingOrder = "radCallQueueRingOrder";
		public static final String btnAllowEscFromQueue = "btnAllowEscFromQueue";
		public static final String inpEscDestinationPhone = "inpEscDestinationPhone";
		public static final String drpESCDigit = "drpESCDigit";
		public static final String drpQueueLt = "drpQueueLt";
		public static final String drpOverFlowPolicy = "drpOverFlowPolicy";
		public static final String inpDestinationTN = "inpDestinationTN";
		public static final String inpWaitBeforeOverflow = "inpWaitBeforeOverflow";
		public static final String inpCQNotes = "inpCQNotes";
		public static final String inpComfortScript = "inpComfortScript";
		public static final String btnSave = "btnSave";
		public static final String drpPhoneNo = "drpPhoneNo";
		public static final String btnUsers = "btnUsers";
		public static final String btnAddUsers = "btnAddUsers";
		public static String txtCQusersSeatName = "";
		public static final String btnAdd = "btnAdd";
		public static final String btnScripts = "btnScripts";
		public static final String drpEntScriptPolicy = "drpEntScriptPolicy";
		public static final String drpComfortScriptPolicy = "drpComfortScriptPolicy";
		public static final String drpOnHoldScriptPolicy = "drpOnHoldScriptPolicy";
		public static final String drpOverFlowScriptPolicy = "drpOverFlowScriptPolicy";
		public static String btnTZ = "";
		public static String QueryLengthDrp = "";
		public static String OnHoldScriptPolicy = "";
		public static final String btnCallQueueSave = "btnCallQueueSave";
		public static String drpESCDigitValue = "";
		public static String drpOnHoldScriptPolicyValue = "";
		public static String drpOnOverFlowScriptPolicyValue = "";

	}

	public CallQueue(FrameworkContext context) {
		super(context, "CallQueue");
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

	public boolean Signout() {
		return mstatus;
	}

	public boolean configureCallQueue(String TN, String exten, String timeZone, String escDestPh, String escDigit,
			String QL, String desTN, String waitBeforeOverFlow, String comfortScript, String notes) {
		mstatus = true;
		try {
			if (waitForElement(testLocatorClickable(Locators.navCallQueue), 20)) {
				iClick(Locators.navCallQueue);
			}
			waitForSpinnerComplete();

			addLocator(Locators.lnkConfigureCallQueue, "xpath",
					"//div[@class='call-queue-info']/p[contains(.,'" + dataSet.getValue("CallQueueDetails") + "')]",
					"Click on Call Queue");
			iClick(Locators.lnkConfigureCallQueue);
			waitForSpinnerComplete();
			sleep(4000);

			iClick(Locators.drpPhoneNo);
			selectvalue_dropdown("div", TN, true);

			iEnterText(Locators.inpExt, exten);
			iClick(Locators.drpTimeZone);
			addLocator(Locators.btnTZ, "xpath", "//div[.='" + timeZone + "']", "Add Locator for Dropdown");
			iClick(Locators.btnTZ);
			// iClick(Locators.radCallQueueRingOrder);
			jsClick(getLocatorWEList(Locators.radCallQueueRingOrder).get(0));
			iClick(Locators.btnAllowEscFromQueue);
			iEnterText_withoutVerify(Locators.inpEscDestinationPhone, escDestPh,
					"Verify the Escapace Destination Phone Entered");
			iClick(Locators.drpESCDigit);
			addLocator(Locators.drpESCDigitValue, "xpath",
					"//span[.='Escape Digit']//following::div[.='" + escDigit + "']", "Select the ESC Digits");
			iClick(Locators.drpESCDigitValue);
			iClick(Locators.drpQueueLt);
			addLocator(Locators.QueryLengthDrp, "xpath", "//span[.='Queue Length *']//following::div[.='" + QL + "']",
					"Select the  Query Length");
			iClick(Locators.QueryLengthDrp);
			iClick(Locators.drpOverFlowPolicy);
			selectvalue_dropdown("div", "Overflow to Escape Destination Phone Number", true);
			iEnterText_withoutVerify(Locators.inpDestinationTN, desTN, "Verify the Destination TN Entered");
			iEnterText(Locators.inpWaitBeforeOverflow, waitBeforeOverFlow);
			iEnterText(Locators.inpComfortScript, comfortScript);
			iEnterText(Locators.inpCQNotes, notes);
			iClick(Locators.btnCallQueueSave);
			waitForSpinnerComplete();
			sleep(4000);
			report.reportPassEvent("Configure Call Queue", "Configure Call Queue Successful");
		} catch (Exception Ex) {
			report.reportHardFailEvent("Configure Call Queue", "Configure Call Queue Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Configure Call Queue Failed!!! " + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public boolean addUsersinCallQueue(String CQBUserToAdd) {
		mstatus = true;
		if (waitForElement(testLocatorClickable(Locators.btnUsers), 10)) {
			iClick(Locators.btnUsers);
		}else if (waitForElement(testLocatorClickable(Locators.navCallQueue), 20)) {
			scrollDown();
			iClick(Locators.navCallQueue);
		}
		waitForSpinnerComplete();
		String[] CallQueueDetails = dataSet.getValue("CallQueueDetails").split("\\|");
		try {
			for (int idx = 0; idx < CallQueueDetails.length; idx++) {
				By by = By.xpath("//div[@class='call-queue-info']/p[contains(.,'" + CallQueueDetails[idx] + "')]");
				browser.findElement(by).click();
				iClick(Locators.btnUsers, null, "Click on Users");
				iClick(Locators.btnAddUsers);
				addLocator(Locators.txtCQusersSeatName, "xpath",
						"//h1[.='Available Call Queue Users']//following::div[.='Seat Name']/../../div[3]/div/div[contains(.,'"
								+ CQBUserToAdd + "')]",
						"Users to Add into CQB");
				iClick(Locators.txtCQusersSeatName);
				iClick(Locators.btnAdd);
				sleep(2000);
				iClick(Locators.btnCallQueueSave);
				sleep(4000);
				report.reportPassEvent("Call Queue Configuration for Add Users" + CallQueueDetails[idx],
						"Call Queue Configuration for Add Users is Successful");
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Call Queue Configuration for Add Users",
					"Call Queue Configuration for Add Users Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Call Queue Configuration for Add Users Failed!!! " + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public boolean configureScriptsCallQueue() {
		mstatus = true;
		try {
			if (waitForElement(testLocatorClickable(Locators.btnScripts), 20)) {
				iClick(Locators.btnScripts);
			}
			iClick(Locators.drpEntScriptPolicy);
			selectvalue_dropdown("div", "Default", true);
			iClick(Locators.drpComfortScriptPolicy);
			selectvalue_dropdown("div", "Default", true);
			iClick(Locators.drpOnHoldScriptPolicy);
			addLocator(Locators.drpOnHoldScriptPolicyValue, "xpath",
					"//span[.='On-Hold Script Policy']//following::div[.='Default'][1]",
					"Enter the On Hold Script PolicyValue");
			iClick(Locators.drpOnHoldScriptPolicyValue);
			sleep(4000);
			scrollDown();
			iClick(Locators.drpOverFlowScriptPolicy);

			addLocator(Locators.drpOnOverFlowScriptPolicyValue, "xpath",
					"//span[.='Overflow Script Policy']//following::div[.='Default'][1]",
					"Enter the Over Flow Script Policy Value");
			iClick(Locators.drpOnOverFlowScriptPolicyValue);
			iClick(Locators.btnCallQueueSave);
			waitForSpinnerComplete();
			report.reportPassEvent("Configure Script-Call Queue", "Configure Script-Call Queue Successful");
		} catch (Exception Ex) {
			report.reportHardFailEvent("Configure Script-Call Queue Call Queue",
					"Configure Script Call Queue Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Configure Script Call Queue Failed!!! " + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

}
