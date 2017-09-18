package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class VerifyCPEOrderShipment extends WebtopPage {

	Logger log = Logger.getLogger(VerifyCPEOrderShipment.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public VerifyCPEOrderShipment(FrameworkContext context) {
		super(context, "VerifyCPEOrderShipment");
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
		public static final String lnkVerifyCPEShipmentOrder = "lnkVerifyCPEShipmentOrder";
		public static final String lstResult = "lstResult";
		public static final String btnSave = "btnSave";
	}

	public boolean verifyCPEShipmentOrder() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.lnkVerifyCPEShipmentOrder, 2)) {
				iClick(Locators.lnkVerifyCPEShipmentOrder, null,
						"Click on Verify CPE Shipment Order Link:History Page:Verify CPE Shipment Order ");
				iSelectValue(Locators.lstResult, "Shipment Verified");
				iClick(Locators.btnSave, null, "Click on Save button:Verify CPE Shipment Order Page:Save button");
				if (!isLocatorVisible(Locators.lnkVerifyCPEShipmentOrder, 1))
					report.reportPassEvent("Verify CPE Shipment Order Tray",
							"Verify CPE Shipment Order completed successfully");
				else
					report.reportHardFailEvent("Verify CPE Shipment Order", "Verify CPE Shipment Order Failed!!!",
							Status.FAIL);
			}
		} catch (Exception e) {
			mstatus = false;
			report.reportHardFailEvent("Verify CPE Shipment Order", "Verify CPE Shipment Order Failed!!!", Status.FAIL);
			String eMsg = "Error in Verify CPE Shipment Order --- " + e.getMessage();
			log.error(eMsg);
		}
		return mstatus;
	}

}
