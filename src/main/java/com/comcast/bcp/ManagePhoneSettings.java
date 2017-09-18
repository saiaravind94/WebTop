package com.comcast.bcp;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

import com.comcast.commons.WebtopPage;
import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.utils.DataTable;
import com.comcast.utils.TestSettings;

/**
 * Represents default page of the application
 * 
 */
public class ManagePhoneSettings extends WebtopPage {
	FrameworkContext context;
	DataTable dataSet;
	Logger log = Logger.getLogger(ManagePhoneSettings.class);
	TestSettings settings;

	public static class Locators {		
		public static final String btnManagePhoneSettings = "btnManagePhoneSettings";
		public static final String lnkEditCallerID = "lnkEditCallerID";
		public static final String CallerIDFName = "CallerIDFName";
		public static final String CallerIDLName = "CallerIDLName";
		public static final String btnTick = "btnTick";
		public static final String lblCallerIDUpdateMessage = "lblCallerIDUpdateMessage";
		public static final String txtUserName = "txtUserName";

	}

	public ManagePhoneSettings(FrameworkContext context) {
		super(context, "ManagePhoneSettings");
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

	public void updateCNAMFields(String emailID) throws InterruptedException {		
		try {				
			log.info("BCP Portal URL is: https://" + settings.get("APP_URL_BCP"));			
			browser.get("https://" + settings.get("APP_URL_BCP") + "/myaccount/");						
			new BCPLogin(context).loginBCP(emailID);
			if(isLocatorVisible(Locators.btnManagePhoneSettings, 5)){
				iClick(Locators.btnManagePhoneSettings, Locators.lnkEditCallerID, "Click on Manage Phone Settings: BCP Home page: Manage Phone Settings button click");
				iClick(Locators.lnkEditCallerID);
				dataDump.setValue("SeatNameToUpdateCNAM", iGetText(Locators.txtUserName).split(" ")[0]);
				iEnterText(Locators.CallerIDFName, dataDump.getValue("NewCNameFName"));
				iEnterText(Locators.CallerIDLName, dataDump.getValue("NewCNameLName"));
				List<WebElement> totalTicks = getLocatorWEList(Locators.btnTick);
				for(WebElement eachTicks : totalTicks){
					if(waitForElement(eachTicks, 1)){
						iClick(eachTicks, null, "Click Tick Icon:CName Update Page: Tick Button");
						break;
					}
				}				
				boolean status = (isLocatorVisible(Locators.lblCallerIDUpdateMessage, 60));
				iReport("", "CName Update in BCP Portal", status ? "CName Update is Passed in BCP Portal" : "CName Update is Failed in BCP Portal", status);				
			}
			else
			{
				log.error("Button Manage Phone Settings is not displyed as expected");
				report.reportHardFailEvent("Click on Manage Phone Settings button", "Button Manage Phone Settings is not displyed as expected");
			}
		} catch (Exception ex) {
			report.reportHardFailEvent("CName Update", "CName Update failed");
			String eMsg = "Error in CName update in BCP Portal --- " + ex.getMessage();
			log.error(eMsg);
		}
	}

}
