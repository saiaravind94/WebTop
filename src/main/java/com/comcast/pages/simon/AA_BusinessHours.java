package com.comcast.pages.simon;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import com.comcast.commons.WebtopPage;
import com.comcast.pages.simon.OrderSummaryPage.Locators;
import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.utils.TestSettings;
import com.comcast.utils.ComparisonType;
import com.comcast.utils.DataTable;
import com.comcast.utils.IUserDetails;

/**
 * Represents default page of the application
 * 
 */
public class AA_BusinessHours extends WebtopPage {
	FrameworkContext context;
	DataTable dataSet;
	Logger log = Logger.getLogger(AA_BusinessHours.class);

	private boolean mstatus;

	public static class Locators {
		public static final String AATab = "AATab";
		public static final String lnkAA = "lnkAA";
		public static final String elmtSavesuccessful = "elmtSavesuccessful";
		public static final String txtBusinessHours = "txtBusinessHours";
		public static final String btnSavelink = "btnSavelink";
		public static final String dropDownGreetingsStar = "dropDownGreetingsStar";
		public static final String dropDownGreetingsHash = "dropDownGreetingsHash";
		public static final String lnkAAKeysScript = "lnkAAKeysScript";
		public static final String tgleActiveAA = "tgleActiveAA";
		public static final String btnAvSeatsAndGroups = "btnAvSeatsAndGroups";
		public static final String txtAvailableSeatsGrpName = "txtAvailableSeatsGrpName";
		public static String txtAvailableHG = "";
		public static String txtAvailableAA = "";
		public static String txtAvailableCQ = "";
		public static final String btnCancel = "btnCancel";
		public static final String columnTypeHeader = "columnTypeHeader";
		public static final String columnTypeHeaderAsc = "columnTypeHeaderAsc";
		public static final String columnTypeHeaderDsec = "columnTypeHeaderDsec";
		public static final String txtEntityTypes = "txtEntityTypes";
	}

	public AA_BusinessHours(FrameworkContext context) {
		super(context, "AA_BusinessHours");
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

	public boolean AABusinessHours() {
		mstatus = true;
		try {
			String[] AAList = dataSet.getValue("AASeatToConfigure").split("\\|");
			if (AAList.length == 0) {
				AAList[0] = "";
			}
			for (int idx = 0; idx < AAList.length; idx++) {
				configureAASettings(AAList[idx]);
				String URL = getCurrentURL();
				String temp = URL.replaceFirst("step/.*", "step/2");
				browser.get(temp);
				iDoubleClick(Locators.dropDownGreetingsHash);
				selectvalue_dropdown("div", "Transfer To Operator", false);
				iDoubleClick(Locators.dropDownGreetingsStar);
				selectvalue_dropdown("div", "Dial By Name", false);
				scrollDown();
				iClick(Locators.lnkAAKeysScript);
				iClick(getLocatorWEList(Locators.btnSavelink).get(getLocatorWEList(Locators.btnSavelink).size() - 1));
				testLocatorClickable(Locators.elmtSavesuccessful);
				report.reportPassEvent("Auto Attendants Business Hours Configuration",
						"Auto Attendants Business Hours configured");
				sleep(5000);
				iClick(Locators.AATab, Locators.lnkAA, "Click on Auto Attendants");
				waitForSpinnerComplete();				
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Auto Attendants Business Hours Configuration",
					"Auto Attendants  Business Hours Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Auto Attendants Business Hours Configuration in SIMON Failed!!! "
					+ Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public boolean verifyAvailableGreetingUsersForAA(String[] greeting, String hours) {
		mstatus = true;
		Boolean status;
		try {
			String URL = getCurrentURL();
			String temp = null;
			if (hours.equalsIgnoreCase("BUSINESS HOURS")) {
				temp = URL.replaceFirst("step/.*", "step/2");
			} else if (hours.equalsIgnoreCase("AFTER HOURS")) {
				temp = URL.replaceFirst("step/.*", "step/3");
				sleep(6000);
			}
			browser.get(temp);
			int i = 0;
			for (i = 0; i < greeting.length; i++) {
				if (testLocatorClickable(Locators.dropDownGreetingsHash, 15) != null) {
					iDoubleClick(Locators.dropDownGreetingsHash);
				}
				selectvalue_dropdown("div", greeting[i], false);
				sleep(3000);
				iClick(Locators.btnAvSeatsAndGroups);
				addLocator(Locators.txtAvailableHG, "xpath", "//div[@class='td entity-type'and contains(.,'Hunt')]",
						"AddLocatorForHG");
				List<WebElement> HGs = getLocatorWEList(Locators.txtAvailableHG);
				addLocator(Locators.txtAvailableAA, "xpath",
						"//div[@class='td entity-type'and contains(.,'Attendant')]", "Adding Locators for AA");
				List<WebElement> AAs = getLocatorWEList(Locators.txtAvailableAA);
				addLocator(Locators.txtAvailableCQ, "xpath", "//div[@class='td entity-type'and contains(.,'Queue')]",
						"AddLocatorForHG");
				List<WebElement> CQs = getLocatorWEList(Locators.txtAvailableCQ);
				status = false;
				if ((HGs.size() >= 1) && (AAs.size() >= 1) && (CQs.size() == 1)) {
					status = true;
				}
				iReport("",
						"Verification of AA, HG and CQ is displayed under available Seats/Groups for the AA " + hours
								+ " greetings: " + greeting[i],
						status ? "AA, HG and CQ is displayed under available Seats/Groups for the AA " + hours
								+ " greetings: " + greeting[i] + " as expected"
								: "AA, HG and CQ is NOT displayed under available Seats/Groups for the AA " + hours
										+ " greetings:" + greeting[i] + "as expected",
						status);
				iClick(Locators.btnCancel);
				log.info("");
			}
			scrollDown();
			iClick(Locators.lnkAAKeysScript);
			iClick(getLocatorWEList(Locators.btnSavelink).get(getLocatorWEList(Locators.btnSavelink).size() - 1));
			testLocatorClickable(Locators.elmtSavesuccessful);
			report.reportPassEvent("Verify greetings for Auto Attendent", "Configuring AA Greeting is Successful");
		} catch (Exception Ex) {
			report.reportHardFailEvent("Auto Attendants Business Hours Configuration",
					"Auto Attendants  Business Hours Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Auto Attendants Business Hours Configuration in SIMON Failed!!! "
					+ Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public boolean configureAASettings(String AAName) {
		mstatus = true;
		try {
			dataDump.setValue("HasAutoAttendents", "Yes");
			scrollUp();
			if (waitForElement(testLocatorClickable(Locators.AATab), 5)) {
				iClick(Locators.AATab, Locators.lnkAA, "Click on Auto Attendants");
			}
			waitforPageLoadComplete();
			sleep(3000);
			if (AAName.equalsIgnoreCase("")) {
				iClick(Locators.lnkAA, null, "Click on Auto Attendant");
			} else {
				addLocator(Locators.lnkAA, "xpath", "//div[contains(@title,'" + AAName + "')]",
						"Auto Attentenant Seat");
				iClick(Locators.lnkAA, null, "Click on Auto Attendant");
			}
			sleep(5000);
			if (isLocatorVisible(Locators.tgleActiveAA, 3)) {
				iClick(Locators.tgleActiveAA);
				iClick(getLocatorWEList(Locators.btnSavelink).get(getLocatorWEList(Locators.btnSavelink).size() - 1));
				testLocatorClickable(Locators.elmtSavesuccessful);
			}

		} catch (Exception Ex) {
			report.reportHardFailEvent("Auto Attendants Configuration",
					"Auto Attendants Configuration Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Auto Attendants Configuration in SIMON Failed!!! " + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}
	
	public boolean verifyOrderOfUsersInAvailableSeatsAndGroups(String[] greeting, String hours, String orderType, List<String> expectedUserOrder) {
		mstatus = true;		
		try {
			String URL = getCurrentURL();
			String temp = null;
			if (hours.equalsIgnoreCase("BUSINESS HOURS")) {
				temp = URL.replaceFirst("step/.*", "step/2");
			} else if (hours.equalsIgnoreCase("AFTER HOURS")) {
				temp = URL.replaceFirst("step/.*", "step/3");
				sleep(6000);
			}
			browser.get(temp);
			int i = 0;
			for (i = 0; i < greeting.length; i++) {
				if (testLocatorClickable(Locators.dropDownGreetingsHash, 15) != null) {
					iDoubleClick(Locators.dropDownGreetingsHash);
				}
				selectvalue_dropdown("div", greeting[i], false);
				sleep(3000);
				iClick(Locators.btnAvSeatsAndGroups);
				List<String> actualUserList  = new ArrayList<String>();				
				switch (orderType.toUpperCase()){
					case "ASC":
						iClick(Locators.columnTypeHeader);						
						break;
					case "DSEC":
						iClick(Locators.columnTypeHeader);
						iClick(Locators.columnTypeHeaderAsc);						
						break;
					default:
						break;				
				}
				List<WebElement> entityTypes = getLocatorWEList(Locators.txtEntityTypes);
				for(WebElement elem:entityTypes){
					actualUserList.add(elem.getText());
				}
				iReport("",
						"Verification of Available Seats and Group Order is displayed under available Seats/Groups for the AA in " + orderType.toUpperCase()
								+ " order",
						expectedUserOrder.toString().contentEquals(actualUserList.toString()) ? "Available Seats and Group Order is displayed under available Seats/Groups for the AA in " + orderType.toUpperCase()
								+ " order as expected"
								: "Available Seats and Group Order is displayed under available Seats/Groups for the AA in " + orderType.toUpperCase()
								+ " order is NOT displayed as expected",
								expectedUserOrder.toString().contentEquals(actualUserList.toString()));
				iClick(Locators.btnCancel);
				iClick(getLocatorWEList(Locators.btnSavelink).get(getLocatorWEList(Locators.btnSavelink).size() - 1));
				testLocatorClickable(Locators.elmtSavesuccessful);
			}			
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verification of Available Seats and Group Order is displayed under available Seats/Groups for the AA with ASC/DSEC order",
					"Verification of Available Seats and Group Order is displayed under available Seats/Groups for the AA Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Verification of Available Seats and Group Order is displayed under available Seats/Groups for the AA in SIMON Failed!!! "
					+ Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}
}
