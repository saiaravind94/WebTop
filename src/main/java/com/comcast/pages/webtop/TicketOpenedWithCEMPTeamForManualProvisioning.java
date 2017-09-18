package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class TicketOpenedWithCEMPTeamForManualProvisioning extends WebtopPage {

	Logger log = Logger.getLogger(TicketOpenedWithCEMPTeamForManualProvisioning.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public TicketOpenedWithCEMPTeamForManualProvisioning(FrameworkContext context) {
		super(context, "TicketOpenedWithCEMPTeamForManualProvisioning");
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
		public static final String trayName = "trayName";
		public static final String lstResult = "lstResult";
		public static final String btnSaveTray = "btnSaveTray";
	}

	public boolean ticketOpenedWithCEMPTeamForManualProvisioning() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.trayName, 2)) {
				iSelectValue(Locators.lstResult, "CEMP Device Add complete manually");
				iClick(Locators.btnSaveTray, null,
						"Click on Save button in the tray:Ticket Opened with CEMP Team for Manual Provisioning Page:Click on Save button in the tray");
				if (!isLocatorVisible(Locators.trayName, 2)) {
					report.reportPassEvent("Ticket Opened with CEMP Team for Manual Provisioning",
							"Ticket Opened with CEMP Team for Manual Provisioning completed successfully!!!");
				} else {
					report.reportHardFailEvent("Ticket Opened with CEMP Team for Manual Provisioning",
							"Ticket Opened with CEMP Team for Manual Provisioning Failed!!!",
							Status.FAIL);
				}
			}
		} catch (Exception e) {
			report.reportHardFailEvent("Ticket Opened with CEMP Team for Manual Provisioning",
					"Ticket Opened with CEMP Team for Manual Provisioning Failed!!!", Status.FAIL);
			String eMsg = "Error in Ticket Opened with CEMP Team for Manual Provisioning --- "
					+ e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
}
