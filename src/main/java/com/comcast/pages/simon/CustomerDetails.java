package com.comcast.pages.simon;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;

import com.comcast.commons.WebtopPage;
import com.comcast.pages.webtop.PerformBillingUpdate.Locators;
import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.utils.TestSettings;
import com.comcast.utils.DataTable;
import com.comcast.utils.IUserDetails;

/**
 * Represents default page of the application
 * 
 */
public class CustomerDetails extends WebtopPage {
	FrameworkContext context;
	private IUserDetails userDetails;
	DataTable dataSet;
	private TestSettings settings;

	Logger log = Logger.getLogger(CustomerDetails.class);

	private boolean mstatus;

	public static class Locators {
		public static final String tabCustomerDetails = "tabCustomerDetails";
		public static final String btnSiteShipping = "btnSiteShipping";
		public static final String btnshippingaddresstoggle = "btnshippingaddresstoggle";
		public static final String btnSave = "btnSave";
		public static final String elmtSavesuccessful = "elmtSavesuccessful";
		public static final String bcpEnable="bcpEnable";
		public static final String txtSiteName = "txtSiteName";		
	}

	public CustomerDetails(FrameworkContext context) {
		super(context, "CustomerDetails");
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
	
	public boolean customerDetails() {
		mstatus = true;
		try {
			isLocatorVisible(Locators.tabCustomerDetails, 5);
			if (waitForElement(testLocatorClickable(Locators.tabCustomerDetails), 10)) {
				iClick(Locators.tabCustomerDetails, null, "Click on Customer Details Tab");
			}
			sleep(4000);
			dataDump.setValue("SITE_NAME", getLocatorAttribute(Locators.txtSiteName, "value"));
			if(dataSet.getValue("BCP Enabled").equalsIgnoreCase("Y")&& !getLocatorAttribute(Locators.bcpEnable, "class").contains("checked")){
				iClick(Locators.bcpEnable);			
			}
			Scroll_Page_Down();
			Scroll_Page_Down();
			sleep(2000);
			if (isLocatorVisible(Locators.btnSiteShipping, 3)) {
				Scroll_Page_Down();
				iClick(Locators.btnshippingaddresstoggle, null, "Click on shipping address toggle");
				sleep(2000);
				iClick(Locators.btnSave, null, "Click on Save");
				testLocatorVisible(Locators.elmtSavesuccessful);
				sleep(3000);
			}
			iClick(Locators.btnSave, null, "Click on Save");
			sleep(3000);
			report.reportPassEvent("Customer Details", "Customer Details Entered");
			scrollUp();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Customer Details", "Customer Details Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Customer Details in SIMON Failed!!! " + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

}
