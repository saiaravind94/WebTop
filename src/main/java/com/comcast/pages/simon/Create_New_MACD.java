package com.comcast.pages.simon;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.server.handler.interactions.MouseMoveToLocation;

import com.comcast.commons.WebtopPage;
import com.comcast.pages.simon.DirectoryListings.Locators;
import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.utils.TestSettings;
import com.comcast.utils.ComparisonType;
import com.comcast.utils.DataTable;
import com.comcast.utils.IUserDetails;

/**
 * Represents default page of the application
 * 
 */
public class Create_New_MACD extends WebtopPage {
	FrameworkContext context;
	private IUserDetails userDetails;
	DataTable dataSet;
	private TestSettings settings;

	Logger log = Logger.getLogger(Create_New_MACD.class);

	private boolean mstatus;

	public static class Locators {
		public static final String drpMACDType = "drpMACDType";
		public static final String txtMACDType = "txtMACDType";
		public static final String drpOrderDescription = "drpOrderDescription";
		public static final String txtOrderDescriptionType = "txtOrderDescriptionType";
		public static final String inpTTSTicketID = "inpTTSTicketID";
		public static final String btnCreate = "btnCreate";

	}

	public Create_New_MACD(FrameworkContext context) {
		super(context, "Create_New_MACD");
		report = context.getReport();
		this.context = context;
		this.dataSet = context.getDataTable();
		this.userDetails = context.getUserDetail();
		this.settings = context.getSettings();
	}

	public boolean createNewMACD(String MACDType, String OrderDescription, String Ticket) {
		mstatus = true;
		try {
			if (testLocatorClickable(Locators.drpMACDType, 15) !=null) {
				iClick(Locators.drpMACDType);
				selectvalue_dropdown("div", MACDType, false);

				iClick(Locators.drpOrderDescription);
				selectvalue_dropdown("div", OrderDescription, false);
				iEnterText(Locators.inpTTSTicketID, Ticket);
				iClick(Locators.btnCreate);
			}

		} catch (Exception Ex) {
			report.reportHardFailEvent("Create a New MACD", "Creation of New MACD is Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Creation of New MACD is Failed!! " + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

}
