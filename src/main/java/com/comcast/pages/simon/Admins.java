package com.comcast.pages.simon;

import org.apache.log4j.Logger;
import com.comcast.commons.WebtopPage;
import com.comcast.pages.simon.OrderInformation.Locators;
import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.utils.ComparisonType;
import com.comcast.utils.TestSettings;
import com.comcast.utils.DataTable;
import com.comcast.utils.IUserDetails;

/**
 * Represents default page of the application
 * 
 */
public class Admins extends WebtopPage {
	FrameworkContext context;	
	DataTable dataSet;	

	Logger log = Logger.getLogger(Admins.class);

	public static class Locators {
		public static final String lnkAdmins = "lnkAdmins";
		public static final String drpDownPrimaryManagersList = "drpDownPrimaryManagersList";
		public static final String btnSave = "btnSave";
		public static final String elmtSavesuccessful = "elmtSavesuccessful";		
	}

	public Admins(FrameworkContext context) {
		super(context, "Admins");
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

	public boolean changeOnePMAsSM() {
		mstatus = true;
		try {
			if (waitForElement(testLocatorClickable(Locators.lnkAdmins), 10)) {
				iClick(Locators.lnkAdmins, null, "Click on Admins Tab");
			}
			waitForElement(getLocatorWEList(Locators.drpDownPrimaryManagersList).get(0), 30);
			iClick(getLocatorWEList(Locators.drpDownPrimaryManagersList).get(0), null, "Click on Primary Managers Drop Down: Admins Page: Click on Primary Manager drop down");
			selectvalue_dropdown("div", "Services Manager", false);
			iClick(Locators.btnSave, null, "Click on Save");
			testLocatorClickable(Locators.elmtSavesuccessful);
			sleep(4000);
			report.reportPassEvent("Change One PM as SM", "Change One PM as SM");
		} catch (Exception Ex) {
			report.reportHardFailEvent("Change One PM as SM", "Change One PM as SM Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Change One PM as SM in SIMON Failed!!! " + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

}
