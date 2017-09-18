package com.comcast.bcp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

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
public class CreateBCPPortalUserPassword extends WebtopPage {
	FrameworkContext context;
	DataTable dataSet;
	Logger log = Logger.getLogger(CreateBCPPortalUserPassword.class);
	TestSettings settings;

	public static class Locators {
		public static final String txtPassword = "txtPassword";
		public static final String txtConfirmPassword = "txtConfirmPassword";
		public static final String txtAnswer = "txtAnswer";
		public static final String chkBoxTermsOfService = "chkBoxTermsOfService";
		public static final String btnDone = "btnDone";	}

	public CreateBCPPortalUserPassword(FrameworkContext context) {
		super(context, "CreateBCPPortalUserPassword");
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

	public void createBCPPortalPassword() throws InterruptedException {
		try{
			if(isLocatorVisible(Locators.txtPassword, 1)){
				iEnterText(Locators.txtPassword, "Welcome1");
				iEnterText(Locators.txtConfirmPassword, "Welcome1");
				iEnterText(Locators.txtAnswer, "Soda");
				iClick(Locators.chkBoxTermsOfService);			
				iClick(Locators.btnDone);
				report.reportPassEvent("Configure BCP Portal Password creation", "Configure BCP Portal Password creation completed");
			}
		}
		catch(Exception ex)
		{
			report.reportHardFailEvent("Configure the BCP Portal Password",
					"Configuring the BCP Portal Password Failed!!!", Status.FAIL);
			String eMsg = "Error in Configuring the BCP Portal Password --- " + ex.getMessage();
			log.error(eMsg);			
		}
	}
}
