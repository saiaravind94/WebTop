package com.comcast.pages.simon;

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
public class CallerIDDetails extends WebtopPage {
	FrameworkContext context;
	private IUserDetails userDetails;
	DataTable dataSet;
	private TestSettings settings;

	Logger log = Logger.getLogger(CallerIDDetails.class);

	private boolean mstatus;

	public static class Locators {
		public static final String txtCallerIDName = "txtCallerIDName";
		public static final String elmtSavesuccessful = "elmtSavesuccessful";
		public static final String btnSaveGeneral = "btnSaveGeneral";
		public static final String lnkCallerIdDetails = "lnkCallerIdDetails";
	}

	public CallerIDDetails(FrameworkContext context) {
		super(context, "CallerIDDetails");
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

	public boolean callerIDInformation() {
		mstatus = true;
		try {
			scrollUp();
			if (waitForElement(testLocatorClickable(Locators.lnkCallerIdDetails), 10)) {
				jsClick(testLocatorVisible(Locators.lnkCallerIdDetails));
			}
			testLocatorVisible(Locators.txtCallerIDName);
			iEnterText(Locators.txtCallerIDName, dataSet.getValue("CustomerName"), "Enter Caller ID Name");
			iClick(Locators.btnSaveGeneral, null, "Click on Save");
			testLocatorVisible(Locators.elmtSavesuccessful);
			sleep(8000);
			report.reportPassEvent("Caller ID Information", "Caller ID Information Entered");
		} catch (Exception Ex) {
			report.reportHardFailEvent("Caller ID Information", "Caller ID Information Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Caller ID Information in SIMON Failed!!! " + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

}
