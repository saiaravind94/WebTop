package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.pages.webtop.WebTopMainPage.Locators;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class ErrorValidatingCSGOrDSTFormData extends WebtopPage {

	Logger log = Logger.getLogger(ErrorValidatingCSGOrDSTFormData.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public ErrorValidatingCSGOrDSTFormData(FrameworkContext context) {
		super(context, "ErrorValidatingCSGOrDSTFormData");
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
		public static final String errorValidatingCSGOrDSTFormDataTray = "errorValidatingCSGOrDSTFormDataTray";
		public static final String tabCustomerProfile = "tabCustomerProfile";
		public static final String lnkInprogressOrder = "lnkInprogressOrder";
	}

	public boolean errorValidatingCSGOrDSTFormData() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.errorValidatingCSGOrDSTFormDataTray, 5)) {
				iClick(Locators.tabCustomerProfile, null,
						"Customer Profile Tab Click: Error Validating CSG/DST Form Data:Customer Profile");
				new ExtendedMiscInfo(context).configureDSTHouseKeyNumbers();
				iClick(Locators.lnkInprogressOrder, null, "Click on the Open Order link");
				iSelectValue(Locators.lstResult, "Re-Enter CSG/DST Account Number");
				iClick(Locators.btnSaveTray, null,
						"Click on Save button in the tray: Error Validating CSG/DST Form Data Page:Click on Save button in the tray");
				if (!isLocatorVisible(Locators.errorValidatingCSGOrDSTFormDataTray, 1)){
					report.reportPassEvent(" Error Validating CSG/DST Form Data",
							"Error Validating CSG/DST Form Data successfully");
					new CreateAndEnterDSTOrDSGAccountNumber(context).saveTray();
				}
				else
					report.reportHardFailEvent(" Error Validating CSG/DST Form Data",
							" Error Validating CSG/DST Form Data Failed!!!", Status.FAIL);
			}

		} catch (Exception e) {
			report.reportHardFailEvent(" Error Validating CSG/DST Form Data",
					" Error Validating CSG/DST Form Data Failed!!!", Status.FAIL);
			String eMsg = "Error in Validating CSG/DST Form Data --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
}
