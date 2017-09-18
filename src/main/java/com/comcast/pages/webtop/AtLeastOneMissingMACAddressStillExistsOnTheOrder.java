package com.comcast.pages.webtop;

import java.util.UUID;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.pages.webtop.WebTopMainPage.Locators;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;

import javassist.tools.framedump;

import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class AtLeastOneMissingMACAddressStillExistsOnTheOrder extends WebtopPage {

	Logger log = Logger.getLogger(AtLeastOneMissingMACAddressStillExistsOnTheOrder.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public AtLeastOneMissingMACAddressStillExistsOnTheOrder(FrameworkContext context) {
		super(context, "AtLeastOneMissingMACAddressStillExistsOnTheOrder");
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
		public static final String lnkAtLeastOneMissingMACAddressStillExistsOnTheOrderTray = "lnkAtLeastOneMissingMACAddressStillExistsOnTheOrderTray";
		public static final String tabCustomerProfile = "tabCustomerProfile";
		public static final String lnkInprogressOrder = "lnkInprogressOrder";
		public static final String txtCPEOrderID = "txtCPEOrderID";
		public static final String expandFIDInfo = "expandFIDInfo";
		public static final String btnSaveFIDValues = "btnSaveFIDValues";
	}

	public boolean atLeastOneMissingMACAddressStillExistsOnTheOrder() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.lnkAtLeastOneMissingMACAddressStillExistsOnTheOrderTray, 5)) {
				iClick(Locators.tabCustomerProfile, null,
						"Customer Profile Tab Click:Customer Profile Tab Click:Customer Profile");
				new AccountProfileTab(context).addESGMACAddress();
				new AccountProfileTab(context).updateMACAddressForAllDevice();
				while (!isLocatorVisible(Locators.txtCPEOrderID, 2)) {
					iClick(Locators.expandFIDInfo);
				}
				iEnterText(Locators.txtCPEOrderID, randomNumber(6));
				iClick(Locators.btnSaveFIDValues);
				iSelectValue(Locators.lstResult, "MAC Address(es) filled in manually");
				iClick(Locators.btnSaveTray, null,
						"Click on Save button in the tray:At least one missing MAC Address still exists on the order Page:Click on Save button in the tray");
				if (!isLocatorVisible(Locators.lnkAtLeastOneMissingMACAddressStillExistsOnTheOrderTray, 1)) {
					report.reportPassEvent("At least one missing MAC Address still exists on the order",
							"At least one missing MAC Address still exists on the order completed successfully");
				} else {
					report.reportHardFailEvent("At least one missing MAC Address still exists on the order",
							"At least one missing MAC Address still exists on the order Failed!!!", Status.FAIL);
				}
			}

		} catch (Exception e) {
			report.reportHardFailEvent("At least one missing MAC Address still exists on the order",
					"At least one missing MAC Address still exists on the order Failed!!!", Status.FAIL);
			String eMsg = "Error in At least one missing MAC Address still exists on the order --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
}
