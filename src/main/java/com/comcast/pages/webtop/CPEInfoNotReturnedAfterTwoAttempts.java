package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.pages.webtop.WebTopMainPage.Locators;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class CPEInfoNotReturnedAfterTwoAttempts extends WebtopPage {

	Logger log = Logger.getLogger(CPEInfoNotReturnedAfterTwoAttempts.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public CPEInfoNotReturnedAfterTwoAttempts(FrameworkContext context) {
		super(context, "CPEInfoNotReturnedAfterTwoAttempts");
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
		public static final String trayCPEInfoNotReturnedAfterTwoAttempts = "trayCPEInfoNotReturnedAfterTwoAttempts";
		public static final String tabCustomerProfile = "tabCustomerProfile";
		public static final String lnkInprogressOrder = "lnkInprogressOrder";
	}

	public boolean cPEInfoNotReturnedAfterTwoAttempts() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.trayCPEInfoNotReturnedAfterTwoAttempts, 5)) {
				iClick(Locators.tabCustomerProfile, null,
						"Customer Profile Tab Click: CPE Info not returned after two attempts Page:Customer Profile");
				String EsgMACAddress = new ExtendedMiscInfo(context).getESGMACAddress();
				log.info("ESG MAC Address is:" + EsgMACAddress);
				new AccountProfileTab(context).updateESGMACAddress(EsgMACAddress);
				iSelectValue(Locators.lstResult, "MAC Address(es) filled in manually");
				iClick(Locators.btnSaveTray, null,
						"Click on Save button in the tray: CPE Info not returned after two attempts Page:Click on Save button in the tray");
				if (!isLocatorVisible(Locators.trayCPEInfoNotReturnedAfterTwoAttempts, 1))
					report.reportPassEvent("CPE Info not returned after two attempts",
							"CPE Info not returned after two attempts successfully");
				else
					report.reportHardFailEvent("CPE Info not returned after two attempts",
							"CPE Info not returned after two attempts Failed!!!", Status.FAIL);
			}

		} catch (Exception e) {
			report.reportHardFailEvent("CPE Info not returned after two attempts",
					"CPE Info not returned after two attempts Failed!!!", Status.FAIL);
			String eMsg = "Error in CPE Info not returned after two attempts --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
}
