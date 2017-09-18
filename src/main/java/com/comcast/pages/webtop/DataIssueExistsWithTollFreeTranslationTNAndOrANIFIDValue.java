package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.pages.webtop.WebTopMainPage.Locators;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class DataIssueExistsWithTollFreeTranslationTNAndOrANIFIDValue extends WebtopPage {

	Logger log = Logger.getLogger(DataIssueExistsWithTollFreeTranslationTNAndOrANIFIDValue.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public DataIssueExistsWithTollFreeTranslationTNAndOrANIFIDValue(FrameworkContext context) {
		super(context, "DataIssueExistsWithTollFreeTranslationTNAndOrANIFIDValue");
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
		public static final String trayDataIssueExistsWithTollFreeTranslationTNAndOrANIFIDValue = "trayDataIssueExistsWithTollFreeTranslationTNAndOrANIFIDValue";
		public static final String tabCustomerProfile = "tabCustomerProfile";
		public static final String lnkInprogressOrder = "lnkInprogressOrder";
	}

	public boolean dataIssueExistsWithTollFreeTranslationTNAndOrANIFIDValue() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.trayDataIssueExistsWithTollFreeTranslationTNAndOrANIFIDValue, 5)) {
				iClick(Locators.tabCustomerProfile, null,
						"Customer Profile Tab Click:Error Reserving Native TNs:Customer Profile");
				new AccountProfileTab(context).updateTerminatingANI();
				iClick(Locators.lnkInprogressOrder, null, "Click on the Open Order link");
				iSelectValue(Locators.lstResult, "Revalidate Translation TNs");
				iClick(Locators.btnSaveTray, null,
						"Click on Save button in the tray:Data Issue Exists with Toll Free Translation TN and/or ANI FID Value Page:Click on Save button in the tray");
				if (!isLocatorVisible(Locators.trayDataIssueExistsWithTollFreeTranslationTNAndOrANIFIDValue, 1))
					report.reportPassEvent("Revalidate Translation TNs",
							"Revalidate Translation TNs completed successfully");
				else
					report.reportHardFailEvent("Data Issue Exists With Toll Free Translation TN And Or ANI FID Value",
							"Data Issue Exists With Toll Free Translation TN And Or ANI FID Value Failed!!!",
							Status.FAIL);
			}

		} catch (Exception e) {
			report.reportHardFailEvent("Data Issue Exists With Toll Free Translation TN And Or ANI FID Value",
					"Data Issue Exists With Toll Free Translation TN And Or ANI FID Value Failed!!!", Status.FAIL);
			String eMsg = "Error in Data Issue Exists With Toll Free Translation TN And Or ANI FID Value --- "
					+ e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
}
