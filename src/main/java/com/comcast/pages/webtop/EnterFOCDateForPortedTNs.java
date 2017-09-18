package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class EnterFOCDateForPortedTNs extends WebtopPage {

	Logger log = Logger.getLogger(EnterFOCDateForPortedTNs.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public EnterFOCDateForPortedTNs(FrameworkContext context) {
		super(context, "EnterFOCDateForPortedTNs");
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
		public static final String lnkEnterFOCDateForPortedTNs = "lnkEnterFOCDateForPortedTNs";
		public static final String dropdownScheduleOrCancel = "dropdownScheduleOrCancel";
		public static final String txtFOC = "txtFOC";
		public static final String btnSave = "btnSave";
		public static final String txtErrorFOC="txtErrorFOC";
	}

	public boolean enterFOCDateForPortedTNs() {
		mstatus = true;
		try {
			if (!isLocatorVisible(Locators.lnkEnterFOCDateForPortedTNs, 5)) {
				iClick(Locators.tabHistory, null, "Click on History Tab");
			}
			iClick(Locators.lnkEnterFOCDateForPortedTNs, null, "Click on Enter FOC Date for Ported TNs Tray");
			iSelectValue(Locators.dropdownScheduleOrCancel, "FOC Date Scheduled");
			iEnterText(Locators.txtFOC, getCurrentDateTime_Operation("mmddyyyy_timestamp", "plus", 1, 0, true),
					"Enter FOC Date");
			iClick(Locators.btnSave, null, "Click on Save button");
			if (!isLocatorVisible(Locators.lnkEnterFOCDateForPortedTNs, 1))
				report.reportPassEvent("Enter FOC Date for Ported TNs Tray",
						"Enter FOC Date for Ported TNs completed successfully");
			else
				report.reportHardFailEvent("Enter FOC Date for Ported TNs", "Enter FOC Date for Ported TNs Failed!!!",
						Status.FAIL);
		} catch (Exception e) {
			mstatus = false;
			report.reportHardFailEvent("Enter FOC Date for Ported TNs", "Enter FOC Date for Ported TNs Failed!!!",
					Status.FAIL);
			String eMsg = "Error in Enter FOC Date for Ported TNs --- " + e.getMessage();
			log.error(eMsg);
		}
		return mstatus;
	}
	public boolean enterInvalidFocDate(String Date){
		mstatus=true;
		try{
			if(!isLocatorVisible(Locators.lnkEnterFOCDateForPortedTNs,5)){
				iClick(Locators.tabHistory,null,"Click on History Tab");
			}
			iClick(Locators.lnkEnterFOCDateForPortedTNs, null, "Click on Enter FOC Date for Ported TNs Tray");
			iSelectValue(Locators.dropdownScheduleOrCancel, "FOC Date Scheduled");
			iEnterText(Locators.txtFOC, Date);
			iClick(Locators.btnSave, null, "Click on Save button");
			WorkOrderComments workordercomments=new WorkOrderComments(context);
			workordercomments.focValidation(Date);
			if(isLocatorVisible(Locators.txtErrorFOC,1)){
				
				report.reportPassEvent("Verification of Error Message for invalid FOC", "ADMIN:Error validating FOC date entered is displayed");
			}
			else{
				report.reportSoftFailEvent("Verification of Error Message for invalid FOC", "ADMIN:Error validating FOC date entered is not displayed");
			}
			
				
		} catch (Exception e) {
			mstatus = false;
			report.reportHardFailEvent("Enter FOC Date for Ported TNs", "Enter FOC Date for Ported TNs Failed!!!",
					Status.FAIL);
			String eMsg = "Error in Enter FOC Date for Ported TNs --- " + e.getMessage();
			log.error(eMsg);
		}
		
		return mstatus;
		
	}

}
