package com.comcast.bcp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.log4j.Logger;
import com.comcast.commons.WebtopPage;
import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.utils.DataTable;
import com.comcast.utils.TestSettings;

/**
 * Represents default page of the application
 * 
 */
public class GenerateUniqueChildBAN extends WebtopPage {
	FrameworkContext context;
	DataTable dataSet;
	Logger log = Logger.getLogger(GenerateUniqueChildBAN.class);
	TestSettings settings;

	public static class Locators {
		public static final String drpdwnRegion = "drpdwnRegion";
		public static final String drpdwnLevel = "drpdwnLevel";
		public static final String txtInput = "txtInput";
		public static final String btnGetAccountLevel = "btnGetAccountLevel";
		public static final String txtChildBANResult = "txtChildBANResult";
	}

	public GenerateUniqueChildBAN(FrameworkContext context) {
		super(context, "BCPPage");
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

	public void genarateUniqueChildBAN() throws InterruptedException {
		browser.get(settings.get("APP_URL_CHILDBAN"));
		if (isLocatorVisible(Locators.drpdwnRegion, 60)) {
			iSelectValue(Locators.drpdwnRegion, "INT");
			iSelectValue(Locators.drpdwnLevel, "Level 2");
			boolean success = true;
			if (!dataDump.getValue("MailIdList").equalsIgnoreCase("")) {
				do {
					Random rn = new Random();
					String childAccId = "9" + (new SimpleDateFormat("YYMM").format(new Date()))
							+ (rn.nextInt(8999) + 1000);
					iEnterText(Locators.txtInput, childAccId);
					dataDump.setValue("ChildAccountID", childAccId);
					dataDump.setValue("ParentAccountID_RT", String.valueOf((Integer.parseInt(childAccId) - 1)));
					iClick(Locators.btnGetAccountLevel, Locators.txtChildBANResult,
							"Click On Get Account Level button:Generate Unique Child BANPage:Get Account Level Button");
					if (iGetText(Locators.txtChildBANResult)
							// .contains("No Authorization object was found for
							// " + childAccId)) {
							.contains("Fail to call")) {
						log.info("Unique Child Ban is: " + childAccId);
						report.reportPassEvent("Generate Unique Child BAN",
								"Unique Child BAN Number is: " + childAccId);
						break;
					} else {
						success = false;
					}
				} while (!success);
			}
		} else {
			log.error("Unique Child BAN page is not displayed after 60 seconds");
			report.reportHardFailEvent("Unique Child BAN home page",
					"Unique Child BAN page is not displayed after 60 seconds");
		}
	}
}
