package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class SDPM_CompleteDesignReview extends WebtopPage {

	Logger log = Logger.getLogger(SDPM_CompleteDesignReview.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public SDPM_CompleteDesignReview(FrameworkContext context) {
		super(context, "SDPM_CompleteDesignReview");
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
		public static final String lnkSDPMCompleteDesignReview = "lnkSDPMCompleteDesignReview";
		public static final String lstContructionRequired = "lstContructionRequired";
		public static final String txtCompletionETA = "txtCompletionETA";
		public static final String lstDesignReviewScheduled = "lstDesignReviewScheduled";
		public static final String txtDesignReviewDate = "txtDesignReviewDate";
		public static final String btnSave = "btnSave";
	}

	public boolean SDPMCompleteDesignReview() {
		mstatus = true;
		try {
			if (!isLocatorVisible(Locators.lnkSDPMCompleteDesignReview, 5)) {
				iClick(Locators.tabHistory, null, "Click on History Tab");
			}
			iClick(Locators.lnkSDPMCompleteDesignReview, null, "Click on SDPM: Complete Design Review Link");
			iSelectValue(Locators.lstContructionRequired, "Yes");
			iEnterText(Locators.txtCompletionETA,
					getCurrentDateTime_Operation("mmddyyyy_timestamp", "plus", 1, 0, true), "Enter Parent ID");
			iSelectValue(Locators.lstDesignReviewScheduled, "Yes");
			iEnterText(Locators.txtDesignReviewDate,
					getCurrentDateTime_Operation("mmddyyyy_timestamp", "plus", 1, 5, true), "Enter Parent ID");
			iClick(Locators.btnSave, null, "Click on Save button");
			if (!isLocatorVisible(Locators.lnkSDPMCompleteDesignReview, 1))
				report.reportPassEvent("SDPM Complete design and review Tray",
						"SDPM Complete design and review completed successfully");
			else
				report.reportHardFailEvent("SDPM Complete design and review",
						"SDPM Complete design and review Failed!!!", Status.FAIL);
		} catch (Exception e) {
			mstatus = false;
			report.reportHardFailEvent("SDPM Complete design and review", "SDPM Complete design and review Failed!!!",
					Status.FAIL);
			String eMsg = "Error in SDPM Complete design and review --- " + e.getMessage();
			log.error(eMsg);
		}
		return mstatus;
	}

}
