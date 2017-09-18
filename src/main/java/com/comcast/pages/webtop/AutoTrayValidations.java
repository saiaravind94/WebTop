package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.pages.webtop.WebTopMainPage.Locators;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class AutoTrayValidations extends WebtopPage {

	Logger log = Logger.getLogger(AutoTrayValidations.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public AutoTrayValidations(FrameworkContext context) {
		super(context, "AutoTrayValidations");
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
		public static final String lnkShowAll = "lnkShowAll";	
		public static String txtAutoTrayName = "";
		public static String txtAutoTrayStatus = "";
	}

	public boolean clickShowALL() {
		mstatus = true;
		try {			
			iClick(Locators.lnkShowAll);
			waitforPageLoadComplete();			
		} catch (Exception e) {
			report.reportHardFailEvent("Verify if Show All is clicked","Unable to click the Show All Button- failed!!!");
			String eMsg = "Unable to locate the Show All Button failed" + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean validateAutoTrayStatus(String trayName, String expectedStatus) {
		mstatus = true;
		try {
			clickShowALL();
			addLocator(Locators.txtAutoTrayName, "xpath", "//a[.='"+trayName+"']", "Adding "+ trayName +"Tray name at runtime");
			if (isLocatorVisible(Locators.txtAutoTrayName, 5)) {
				addLocator(Locators.txtAutoTrayName, "xpath", "//a[.='"+trayName+"']//following::td[@class='rich-table-cell 'and .='"+expectedStatus+"'][1]", "Adding "+ trayName +"Tray name status at runtime");
				iVerifyLocatorVisible(Locators.txtAutoTrayStatus);				
					report.reportPassEvent("Auto: Begin Cancel SNAP Feature Disconnect",
							"Auto: Begin Cancel SNAP Feature Disconnect Tray is displayed and is successfully closed");				
			} else {
				report.reportHardFailEvent("Auto: Begin Cancel SNAP Feature Disconnect",
						"Auto: Begin Cancel SNAP Feature Disconnect is not displayed!!!", Status.FAIL);
				String eMsg = "Auto: Begin Cancel SNAP Feature Disconnect is not displayed--- ";
				log.error(eMsg);
				mstatus = false;
			}
		} catch (Exception e) {
			report.reportHardFailEvent("Auto: Begin Cancel SNAP Feature Disconnect",
					"Auto: Begin Cancel SNAP Feature Disconnect tray is not displayed Failed!!!", Status.FAIL);
			String eMsg = "uto: Begin Cancel SNAP Feature Disconnect tray is not displayed Failed!!!--- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
}
