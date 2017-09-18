package com.comcast.bcp;

import org.apache.log4j.Logger;
import com.comcast.commons.WebtopPage;
import com.comcast.reporting.Status;
import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.utils.DataTable;
import com.comcast.utils.TestSettings;

/**
 * Represents default page of the application
 * 
 */
public class ActivateUser extends WebtopPage {
	FrameworkContext context;
	DataTable dataSet;
	Logger log = Logger.getLogger(ActivateUser.class);
	TestSettings settings;

	public static class Locators {
		public static final String txtAccountNumber = "txtAccountNumber";
		public static final String txtZipCode = "txtZipCode";
		public static final String chkBoxCaptcha = "chkBoxCaptcha";
		public static final String btnSearchProfiles = "btnSearchProfiles";
	}

	public ActivateUser(FrameworkContext context) {
		super(context, "ActivateUser");
		report = context.getReport();
		this.context = context;
		this.dataSet = context.getDataTable();
		context.getUserDetail();
		settings = context.getSettings();
	}

	@Override
	protected boolean isValidPage() {
		return true;
	}

	@Override
	protected void waitForPageLoad() {

	}

	public void activateUser() throws InterruptedException {
		try{
			if(isLocatorVisible(Locators.btnSearchProfiles, 1)){
				if(isLocatorVisible(Locators.txtAccountNumber, 1)){
					iEnterText(Locators.txtAccountNumber, dataDump.getValue("ChildAccountID"));
				}
				iEnterText(Locators.txtZipCode, "19335");
				jsUpdateDOMProperty(getLocatorWEList(Locators.btnSearchProfiles).get(0), "data-role", "validation");
				iClick(Locators.btnSearchProfiles);
				report.reportPassEvent("Account configuration in BCP Portal", "Account configuration in BCP Portal Passed");
			}
		}
		catch(Exception ex)
		{
			report.reportHardFailEvent("Account configuration in BCP Portal",
					"Account configuration in BCP Portal Failed!!!", Status.FAIL);
			String eMsg = "Error in Account configuration in BCP Portal --- " + ex.getMessage();
			log.error(eMsg);			
		}
	}
}
