package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.pages.webtop.WebTopMainPage.Locators;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class ValidationErrorsFoundonSOAOrder extends WebtopPage {

	Logger log = Logger.getLogger(ValidationErrorsFoundonSOAOrder.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public ValidationErrorsFoundonSOAOrder(FrameworkContext context) {
		super(context, "ValidationErrorsFoundonSOAOrder");
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
		public static final String tabHistory = "tabHistory";
		public static final String lstResult = "lstResult";
		public static final String btnSaveTray = "btnSaveTray";
		public static final String lnkInprogressOrder = "lnkInprogressOrder";
		public static final String errorValidationErrorsFoundonSOAOrderTray = "errorValidationErrorsFoundonSOAOrderTray";
		public static final String expandContactInfo = "expandContactInfo";
		public static final String expandMainContact = "expandMainContact";
		public static final String lnkEditContact = "lnkEditContact";
		public static final String txtName = "txtName";
		public static final String btnUpdate = "btnUpdate";
		public static final String tabProspectProfile = "tabProspectProfile";
		public static final String lnkOrderId = "lnkOrderId";
		public static final String btnGo = "btnGo";

	}

	public boolean validationErrorsFoundonSOAOrder() {
		mstatus = true;
		try {
			if (!isLocatorVisible(Locators.errorValidationErrorsFoundonSOAOrderTray, 5)) {
				iClick(Locators.tabHistory, null, "Click on History Tab");
			}
			if (isLocatorVisible(Locators.errorValidationErrorsFoundonSOAOrderTray, 5)) {
				report.reportPassEvent("Validation for Missing Last Name in Main Conatct",
						"Validation Errors Found on SOA Order Tray", Status.PASS);
				// Update Main Contact Last Name with Single Char and retry the
				// Tray
				iClick(Locators.tabProspectProfile, null,
						"Prospect Profile Tab Click:Validation Errors Found on SOA Order Tray:Prospect Profile");
				while (!isLocatorVisible(Locators.expandMainContact, 1)) {
					jsClick(testLocatorClickable(Locators.expandContactInfo));
				}
				while (!isLocatorVisible(Locators.lnkEditContact, 1)) {
					jsClick(testLocatorClickable(Locators.expandMainContact));
				}
				jsClick(testLocatorClickable(Locators.lnkEditContact));
				iEnterText(Locators.txtName, "FNAME L", "Entered the Main contact Last Name with single character");
				report.reportDoneEvent("Update Main contact Last Name with Single Char ",
						"Main conatct Last Name update", Status.SCREENSHOT);
				iClick(Locators.btnUpdate, null, "Click on the Update Button");
				if (isLocatorVisible(Locators.lnkOrderId, 2)) {
					iClick(Locators.lnkOrderId, null, "Click on Order ID");
				}
				if (isLocatorVisible(Locators.lnkInprogressOrder, 2)) {
					iClick(Locators.lnkInprogressOrder, null, "Click on the Open Order link");
				}
				if (isLocatorVisible(Locators.tabHistory, 5)) {
					iClick(Locators.tabHistory, null, "Click on History Tab");
				}
				if (iGetText(Locators.errorValidationErrorsFoundonSOAOrderTray).contains("SOA"))
					iSelectValue(Locators.lstResult, "Retry Validating SOA Order");
				else
					iSelectValue(Locators.lstResult, "Retry Validating SDW Order");
				iClick(Locators.btnSaveTray, null,
						"Click on Save button in the tray:Validation Errors Found on SOA Order Tray Page:Click on Save button in the tray");
				if (isLocatorVisible(Locators.errorValidationErrorsFoundonSOAOrderTray, 5)) {
					report.reportPassEvent("Failed after giving Last Name with Single Char as expected",
							"Validation Errors Found on SOA Order Tray", Status.PASS);
				} else {
					report.reportHardFailEvent("Failed after giving Last Name with Single Char",
							"Validation Errors Found on SOA Order Tray", Status.FAIL);
				}
				// Update Main Contact Last Name with two Char and retry the
				// Tray
				iClick(Locators.tabProspectProfile, null,
						"Prospect Profile Tab Click:Validation Errors Found on SOA Order Tray:Prospect Profile");
				while (!isLocatorVisible(Locators.expandMainContact, 1)) {
					jsClick(testLocatorClickable(Locators.expandContactInfo));
				}
				while (!isLocatorVisible(Locators.lnkEditContact, 1)) {
					jsClick(testLocatorClickable(Locators.expandMainContact));
				}
				jsClick(testLocatorClickable(Locators.lnkEditContact));
				iEnterText(Locators.txtName, "FNAME LN", "Entered the Main contact Last Name with two characters");
				report.reportDoneEvent("Update Main contact Last Name with two Char ", "Main conatct Last Name update",
						Status.SCREENSHOT);
				iClick(Locators.btnUpdate, null, "Click on the Update Button");
				if (isLocatorVisible(Locators.lnkOrderId, 2)) {
					iClick(Locators.lnkOrderId, null, "Click on Order ID");
				}
				if (isLocatorVisible(Locators.lnkInprogressOrder, 2)) {
					iClick(Locators.lnkInprogressOrder, null, "Click on the Open Order link");
				}
				if (isLocatorVisible(Locators.tabHistory, 2)) {
					iClick(Locators.tabHistory, null, "Click on History Tab");
				}
				if (iGetText(Locators.errorValidationErrorsFoundonSOAOrderTray).contains("SOA"))
					iSelectValue(Locators.lstResult, "Retry Validating SOA Order");
				else
					iSelectValue(Locators.lstResult, "Retry Validating SDW Order");
				iClick(Locators.btnSaveTray, null,
						"Click on Save button in the tray:Validation Errors Found on SOA Order Tray Page:Click on Save button in the tray");
				if (!isLocatorVisible(Locators.errorValidationErrorsFoundonSOAOrderTray, 5)) {
					report.reportPassEvent("Passed after giving Last Name with two Char as expected",
							"Retry Last Name with 2 char", Status.PASS);
				} else {
					report.reportHardFailEvent("Failed after giving Last Name with two Chars",
							"Validation Errors Found on SOA Order Tray", Status.FAIL);
				}

			}

		} catch (Exception e) {
			report.reportHardFailEvent("Retry Validating SOA Order Main Contact Last Name with 2 char",
					"Retry Validating SOA Order Main Contact Last Name with 2 char Failed!!!", Status.FAIL);
			String eMsg = "Error in Retry Validating SOA Order Main Contact Last Name with 2 chars --- "
					+ e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}

	public boolean updateMainConatctFNameOnly() {
		mstatus = true;
		try {
			// Update Main Contact Last Name with Single Char and retry the Tray
			while (!isLocatorVisible(Locators.expandMainContact, 1)) {
				jsClick(testLocatorClickable(Locators.expandContactInfo));
			}
			while (!isLocatorVisible(Locators.lnkEditContact, 1)) {
				jsClick(testLocatorClickable(Locators.expandMainContact));
			}
			jsClick(testLocatorClickable(Locators.lnkEditContact));
			iEnterText(Locators.txtName, "FNAME", "Entered the Main contact Last Name Only");
			report.reportDoneEvent("Update Main contact Last Name Only ", "Main conatct Last Name update",
					Status.SCREENSHOT);
			iClick(Locators.btnUpdate, null, "Click on the Update Button");
		} catch (Exception e) {
			report.reportHardFailEvent("Update Main Contact with First Name Only",
					"Update Main Contact with First Name Only Failed!!!", Status.FAIL);
			String eMsg = "Error in Updating Main Contact with First Name Only --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}

	public boolean validationPromotionalDiscount(String BundlePrice) {
		mstatus = true;
		try {
			if (!isLocatorVisible(Locators.errorValidationErrorsFoundonSOAOrderTray, 1)) {
				iClick(Locators.tabHistory, null, "Click on History Tab");
			}
			if (isLocatorVisible(Locators.errorValidationErrorsFoundonSOAOrderTray, 2)) {
				report.reportPassEvent("Validation for Error validating Promotional Discount",
						"Validation Errors Found on SDW Order Tray is displayed as expected", Status.PASS);
				// Update the promotional discount for the bundle and retry
				iClick(Locators.tabProspectProfile, null,
						"Customer Profile Tab Click:Validation Errors Found on SDW Order Tray:Customer Profile");
				new AccountProfileTab(context).editBundle("10-19", BundlePrice);
				iClick(Locators.btnGo);
				if (isLocatorVisible(Locators.lnkOrderId, 2)) {
					iClick(Locators.lnkOrderId, null, "Click on Order ID");
				}
				if (isLocatorVisible(Locators.lnkInprogressOrder, 2)) {
					iClick(Locators.lnkInprogressOrder, null, "Click on the Open Order link");
				}
				if (isLocatorVisible(Locators.tabHistory, 2)) {
					iClick(Locators.tabHistory, null, "Click on History Tab");
				}
				iSelectValue(Locators.lstResult, "Retry Validating SDW Order");
				iClick(Locators.btnSaveTray, null,
						"Click on Save button in the tray:Validation Errors Found on SDW Order Tray Page:Click on Save button in the tray");
				if (!isLocatorVisible(Locators.errorValidationErrorsFoundonSOAOrderTray, 5)) {
					report.reportPassEvent("Passed after Updating the bundle price as expected",
							"Retry after Updating the bundle price", Status.PASS);
				} else {
					report.reportHardFailEvent("Failed after after Updating the bundle price",
							"Validation Errors Found on SDW Order Tray", Status.FAIL);
				}
			}

		} catch (Exception e) {
			report.reportHardFailEvent("Retry Validating SDW Order bundle price",
					"Retry Validating SDW Order bundle price Failed!!!", Status.FAIL);
			String eMsg = "Error in Retry Validating SDW Order --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
}
