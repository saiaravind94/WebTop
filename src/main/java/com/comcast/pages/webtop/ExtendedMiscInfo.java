package com.comcast.pages.webtop;

import java.util.UUID;

import org.apache.log4j.Logger;
import org.eclipse.persistence.internal.localization.i18n.LoggingLocalizationResource;
import org.openqa.selenium.Keys;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.utils.ComparisonType;

public class ExtendedMiscInfo extends WebtopPage {

	Logger log = Logger.getLogger(ExtendedMiscInfo.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public ExtendedMiscInfo(FrameworkContext context) {
		super(context, "ExtendedMiscInfo");
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
		public static final String tabExtendedInfo = "tabExtendedInfo";
		public static final String expandMiscInfo = "expandMiscInfo";
		public static final String tabCustomerProfile = "tabCustomerProfile";
		public static final String txtRateCenter = "txtRateCenter";
		public static final String txtTNOrderPool = "txtTNOrderPool";
		public static final String txtCSGAccountNumber = "txtCSGAccountNumber";
		public static final String txtDSTAccountNumber = "txtDSTAccountNumber";
		public static final String txtHouseKey = "txtHouseKey";
		public static final String txtMarket = "txtMarket";
		public static final String lstTimeZone = "lstTimeZone";
		public static final String txtESGMACAddress = "txtESGMACAddress";
		public static final String txtSFAccountID = "txtSFAccountID";
		public static final String btnSaveMiscInfo = "btnSaveMiscInfo";
		public static  String txtField = "";
	}

	public boolean configureRateCenter() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.tabExtendedInfo, 2)) {
				iClick(Locators.tabExtendedInfo, null,
						"Click on Entended Info button:Webtop Home Page:Click on Entended Info button");
				while (!isLocatorVisible(Locators.txtRateCenter, 2)) {
					jsClick(testLocatorClickable(Locators.expandMiscInfo));
				}
				iEnterText(Locators.txtRateCenter, dataSet.getValue("Rate"));
				iEnterText(Locators.txtTNOrderPool, dataSet.getValue("Rate"));
				iClick(Locators.btnSaveMiscInfo, null, "Save Misc Info");
			}

		} catch (Exception e) {
			report.reportHardFailEvent("Update Rate center Info", "Update Rate center Info Failed!!!", Status.FAIL);
			String eMsg = "Error in Update Rate center Info --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}

	public boolean configureDSTHouseKeyNumbers() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.tabExtendedInfo, 2)) {
				iClick(Locators.tabExtendedInfo, null,
						"Click on Entended Info button:Webtop Home Page:Click on Entended Info button");
				while (!isLocatorVisible(Locators.txtRateCenter, 2)) {
					jsClick(testLocatorClickable(Locators.expandMiscInfo));
				}
				iEnterText(Locators.txtRateCenter, dataSet.getValue("Rate"));
				iEnterText(Locators.txtTNOrderPool, dataSet.getValue("Rate"));
				if (!dataSet.getValue("BCP Enabled").equalsIgnoreCase("Y")) {
					iEnterText(Locators.txtCSGAccountNumber, dataSet.getValue("CSGAcc Number"), "Enter CSGAcc number");
					iEnterText(Locators.txtDSTAccountNumber, dataSet.getValue("DSTAccountNumber"),
							"Enter DST Acc number");
					iEnterText(Locators.txtHouseKey, dataSet.getValue("HouseKey"), "Enter House Key");
				} else {
					iEnterText(Locators.txtCSGAccountNumber, dataDump.getValue("DSTorCSGAccNo"), "Enter CSGAcc number");					
					iEnterText(Locators.txtHouseKey, dataDump.getValue("HouseKey"), "Enter House Key");
				}
				iEnterText(Locators.txtMarket, "99998-TestCorp");
				iSelectValue(Locators.lstTimeZone, "(GMT-04:00) (US) Eastern Time");
				iClick(Locators.btnSaveMiscInfo, null, "Save Misc Info");
			}

		} catch (Exception e) {
			report.reportHardFailEvent("Update DST/CSG Info", "Update DST/CSG Info Failed!!!", Status.FAIL);
			String eMsg = "Error in Update DST/CSG Info --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}

	public String getESGMACAddress() {
		String ESGMACAddress = "";
		try {
			if (isLocatorVisible(Locators.tabExtendedInfo, 2)) {
				iClick(Locators.tabExtendedInfo, null,
						"Click on Entended Info button:Webtop Home Page:Click on Entended Info button");
				while (!isLocatorVisible(Locators.txtESGMACAddress, 2)) {
					jsClick(testLocatorClickable(Locators.expandMiscInfo));
				}
				ESGMACAddress = testLocatorClickable(Locators.txtESGMACAddress).getAttribute("value");
			}

		} catch (Exception e) {
			report.reportHardFailEvent("Get ESG MAC Address", "Get ESG MAC Address Failed!!!", Status.FAIL);
			String eMsg = "Error in Getting ESG MAC Address --- " + e.getMessage();
			log.error(eMsg);
		}
		return ESGMACAddress;
	}

	public void updateESGMACAddress() {
		String ESGMACAddress = "";
		try {
			if (isLocatorVisible(Locators.tabExtendedInfo, 2)) {
				iClick(Locators.tabExtendedInfo, null,
						"Click on Entended Info button:Webtop Home Page:Click on Entended Info button");
				while (!isLocatorVisible(Locators.txtESGMACAddress, 2)) {
					jsClick(testLocatorClickable(Locators.expandMiscInfo));
				}
				String uuid = UUID.randomUUID().toString();
				ESGMACAddress = uuid.substring(uuid.lastIndexOf("-") + 1);
				iEnterText(Locators.txtESGMACAddress, ESGMACAddress);
				iClick(Locators.btnSaveMiscInfo, null, "Save Misc Info");
			}

		} catch (Exception e) {
			report.reportHardFailEvent("Update ESG MAC Address", "Update ESG MAC Address Failed!!!", Status.FAIL);
			String eMsg = "Error in Updating ESG MAC Address --- " + e.getMessage();
			log.error(eMsg);
		}
	}

	public void updateSFAccountID() {
		try {
			if (isLocatorVisible(Locators.tabExtendedInfo, 2)) {
				iClick(Locators.tabExtendedInfo, null,
						"Click on Entended Info button:Webtop Home Page:Click on Entended Info button");
				while (!isLocatorVisible(Locators.txtSFAccountID, 2)) {
					jsClick(testLocatorClickable(Locators.expandMiscInfo));
				}
				iEnterText(Locators.txtSFAccountID, dataSet.getValue("NewSFAccountID"));
				iClick(Locators.btnSaveMiscInfo, null, "Save Misc Info");
				report.reportPassEvent("Updating NEW SF Account ID", "Updated with NEW SF Account ID");
			}

		} catch (Exception e) {
			report.reportHardFailEvent("Updating NEW SF Account ID", "Updating NEW SF Account ID Failed!!!",
					Status.FAIL);
			String eMsg = "Error in Updating NEW SF Account ID --- " + e.getMessage();
			log.error(eMsg);
		}
	}
	
	public void validateFieldValue(String[] fieldName, String[] expectedValue, String[] attibuteToCheck) {
		int idx = 0;
		try {
			if(isLocatorVisible(Locators.tabCustomerProfile, 1)){
				iClick(Locators.tabCustomerProfile);
			}			
			if (isLocatorVisible(Locators.tabExtendedInfo, 2)) {
				iClick(Locators.tabExtendedInfo, null,
						"Click on Entended Info button:Webtop Home Page:Click on Entended Info button");				
				addLocator(Locators.txtField, "xpath", "//td[.='"+fieldName[idx]+"']//following::select/option[@selected='selected']|//td[.='"+fieldName[idx]+"']//following::input[1]", fieldName[idx] + " Field" );				
				while (!isLocatorVisible(Locators.txtSFAccountID, 1)) {
					jsClick(testLocatorClickable(Locators.expandMiscInfo));
				}
				for(idx = 0; idx < fieldName.length; idx++){					
					addLocator(Locators.txtField, "xpath", "//td[.='"+fieldName[idx]+"']//following::select/option[@selected='selected']|//td[.='"+fieldName[idx]+"']/following::td/input[1]", fieldName[idx] + " Field" );
					scrollElementIntoView(getLocatorWEList(Locators.txtField).get(0));		
					iValidateAttributeValue(Locators.txtField, attibuteToCheck[idx], expectedValue[idx], ComparisonType.EQUAL, fieldName[idx] + " Field validation");					
				}
			}
		} catch (Exception e) {
			report.reportHardFailEvent(fieldName[idx] +  " Validation", fieldName[idx] +  " Validation Failed!!!",
					Status.FAIL);
			String eMsg = fieldName[idx] +  " Validation failed --- " + e.getMessage();
			log.error(eMsg);
		}
	}
}
