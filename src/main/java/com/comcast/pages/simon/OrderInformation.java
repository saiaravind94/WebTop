package com.comcast.pages.simon;

import org.apache.log4j.Logger;
import com.comcast.commons.WebtopPage;
import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.utils.ComparisonType;
import com.comcast.utils.TestSettings;
import com.comcast.utils.DataTable;
import com.comcast.utils.IUserDetails;

/**
 * Represents default page of the application
 * 
 */
public class OrderInformation extends WebtopPage {
	FrameworkContext context;
	private IUserDetails userDetails;
	DataTable dataSet;
	private TestSettings settings;

	Logger log = Logger.getLogger(OrderInformation.class);

	private boolean mstatus;

	public static class Locators {
		public static final String lnkOrderInformation = "lnkOrderInformation";
		public static final String txtsdpmname = "txtsdpmname";
		public static final String txtsdename = "txtsdename";
		public static final String txtSingleViewParentAccID = "txtSingleViewParentAccID";
		public static final String txtSOAOrderID = "txtSOAOrderID";
		public static final String txtGroupID = "txtGroupID";
		public static final String btnSave = "btnSave";
		public static final String elmtSavesuccessful = "elmtSavesuccessful";
		public static final String txtLocalBillerAccountID = "txtLocalBillerAccountID";
		public static final String txtSingleViewChildAccID = "txtSingleViewChildAccID";
		public static final String txtPricingBase = "txtPricingBase";
		public static final String txtSiteType = "txtSiteType";
		public static final String lnkServicesInformation = "lnkServicesInformation";
		public static final String txtLeadID = "txtLeadID";
		
		public static final String spinner = "spinner";
	}

	public OrderInformation(FrameworkContext context) {
		super(context, "OrderInformation");
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

	public boolean orderInformation() {
		mstatus = true;
		try {
			if (waitForElement(testLocatorClickable(Locators.lnkOrderInformation), 10)) {
				iClick(Locators.lnkOrderInformation, null, "Click on Order Information");
			}
			testLocatorVisible(Locators.txtsdpmname);
			iEnterText(Locators.txtsdpmname, dataSet.getValue("CustomerName"), "Enter SDPM Name");
			iEnterText(Locators.txtsdename, dataSet.getValue("SDEName"), "Enter SDE Name");
			iEnterText(Locators.txtSingleViewParentAccID, dataDump.getValue("ParentAccountID_RT"),
					"Enter SV Parent Account ID");
			iEnterText(Locators.txtSOAOrderID, dataDump.getValue("OrderID_RT"), "Enter Order ID");
			iEnterText(Locators.txtGroupID, dataDump.getValue("GroupID"), "Enter Group ID");
			dataDump.setValue("LeadID",getLocatorAttribute(Locators.txtLeadID, "value"));
			dataDump.setValue("PricingBase", getLocatorAttribute(Locators.txtPricingBase, "value"));
			dataDump.setValue("OrderType", getLocatorAttribute(Locators.txtSiteType, "value"));
			if (isLocatorVisible(Locators.txtLocalBillerAccountID, 1)) {
				iEnterText(Locators.txtLocalBillerAccountID, dataDump.getValue("DSTorCSGAccNo"));
				iEnterText(Locators.txtSingleViewChildAccID, dataDump.getValue("ChildAccountID"));
			}
			iClick(Locators.btnSave, null, "Click on Save");
			testLocatorClickable(Locators.elmtSavesuccessful);
			sleep(4000);
			report.reportPassEvent("Order Information", "Order Information Entered");
		} catch (Exception Ex) {
			report.reportHardFailEvent("Order Information", "Order Information Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Order Information in SIMON Failed!!! " + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public boolean orderInformationAutoSave() {
		mstatus = true;
		try {
			if (waitForElement(testLocatorClickable(Locators.lnkOrderInformation), 10)) {
				iClick(Locators.lnkOrderInformation, null, "Click on Order Information");
			}
			testLocatorVisible(Locators.txtsdpmname);
			iEnterText(Locators.txtsdpmname, "AutoTestSDPMName", "Enter SDPM Name");
			iEnterText(Locators.txtsdename, "AutoTestSDEName", "Enter SDE Name");
			iEnterText(Locators.txtSingleViewParentAccID, "123456789", "Enter SV Parent Account ID");
			iEnterText(Locators.txtSOAOrderID, "12345", "Enter Order ID");
			iEnterText(Locators.txtGroupID, "1234", "Enter Group ID");
			report.reportPassEvent("Order Information Auto save", "Order Information Entered");
			scrollUp();
			if (waitForElement(testLocatorClickable(Locators.lnkServicesInformation), 10)) {
				iClick(Locators.lnkServicesInformation, null, "Click Services Information");
			}
			if (waitForElement(testLocatorClickable(Locators.lnkOrderInformation), 10)
					&& !isLocatorVisible(Locators.spinner, 20)) {
				iClick(Locators.lnkOrderInformation, null, "Click on Order Information");
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Order Information auto save",
					"Order Information auto save Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Order Information auto save in SIMON Failed!!! " + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public boolean verifyIfOrderInformationAutoSaved() {
		mstatus = true;
		try {
			if (!isLocatorVisible(Locators.spinner, 2)) {
				iValidateAttributeValue(Locators.txtsdpmname, "value", "AutoTestSDPMName", ComparisonType.EQUAL,
						"Verify if SDPM Name is auto saved?");
				iValidateAttributeValue(Locators.txtsdename, "value", "AutoTestSDEName", ComparisonType.EQUAL,
						"Verify if SDE Name is auto saved?");
				iValidateAttributeValue(Locators.txtSingleViewParentAccID, "value", "123456789", ComparisonType.EQUAL,
						"Verify if SV Parent Account ID is auto saved?");
				iValidateAttributeValue(Locators.txtSOAOrderID, "value", "12345", ComparisonType.EQUAL,
						"Verify if Order ID Name is auto saved?");
				iValidateAttributeValue(Locators.txtGroupID, "value", "1234", ComparisonType.EQUAL,
						"Verify if Group ID is auto saved?");
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verification of Order Information ",
					"Verification of Order Information is Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Verification of Order Information in SIMON Failed!!! " + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}
	
	public boolean updateLBAccountAndChildBAN(String ChildAccId) {
		mstatus = true;
		try {
			if (waitForElement(testLocatorClickable(Locators.lnkOrderInformation), 10)) {
				iClick(Locators.lnkOrderInformation, null, "Click on Order Information");
			}
			testLocatorVisible(Locators.txtsdpmname);
			iEnterText(Locators.txtsdpmname, dataSet.getValue("CustomerName"), "Enter SDPM Name");
			iEnterText(Locators.txtsdename, dataSet.getValue("SDEName"), "Enter SDE Name");
			iEnterText(Locators.txtSingleViewParentAccID, "986531423",
					"Enter SV Parent Account ID");
			iEnterText(Locators.txtSOAOrderID, "12345", "Enter Order ID");
			iEnterText(Locators.txtGroupID, "12345", "Enter Group ID");
			dataDump.setValue("LeadID",getLocatorAttribute(Locators.txtLeadID, "value"));
			dataDump.setValue("PricingBase", getLocatorAttribute(Locators.txtPricingBase, "value"));
			dataDump.setValue("OrderType", getLocatorAttribute(Locators.txtSiteType, "value"));
			if (isLocatorVisible(Locators.txtLocalBillerAccountID, 1)) {
				iEnterText(Locators.txtLocalBillerAccountID, "0809800898594754");
				if(ChildAccId.isEmpty() || ChildAccId.equals(null) || ChildAccId.equals(""))					
					iEnterText(Locators.txtSingleViewChildAccID, "943643568");
				else
					iEnterText(Locators.txtSingleViewChildAccID, ChildAccId);
			}
			iClick(Locators.btnSave, null, "Click on Save");
			testLocatorClickable(Locators.elmtSavesuccessful);
			sleep(4000);
			report.reportPassEvent("Order Information", "Order Information Entered");
		} catch (Exception Ex) {
			report.reportHardFailEvent("Order Information", "Order Information Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Order Information in SIMON Failed!!! " + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

}
