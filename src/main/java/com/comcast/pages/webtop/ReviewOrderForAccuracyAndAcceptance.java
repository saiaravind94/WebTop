package com.comcast.pages.webtop;

import org.apache.log4j.Logger;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class ReviewOrderForAccuracyAndAcceptance extends WebtopPage {

	Logger log = Logger.getLogger(ReviewOrderForAccuracyAndAcceptance.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public ReviewOrderForAccuracyAndAcceptance(FrameworkContext context) {
		super(context, "ReviewOrderForAccuracyAndAcceptance");
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
		public static final String lnkReviewOrderforAccuracyandAcceptance = "lnkReviewOrderforAccuracyandAcceptance";
		public static final String listAcceptReject = "listAcceptReject";
		public static final String txtParentAccID = "txtParentAccID";
		public static final String txtChildAccID = "txtChildAccID";
		public static final String lstResult = "lstResult";
		public static final String btnSave = "btnSave";
		public static final String btnSaveTray = "btnSaveTray";

	}

	public boolean reviewOrderForAccuracyAndAcceptance() {
		mstatus = true;
		try {
			if (!isLocatorVisible(Locators.lnkReviewOrderforAccuracyandAcceptance, 5)) {
				iClick(Locators.tabHistory, null, "Click on History Tab");
			}
			if (isLocatorVisible(Locators.lnkReviewOrderforAccuracyandAcceptance, 1)) {
				iClick(Locators.lnkReviewOrderforAccuracyandAcceptance, null,
						"Click on Review Order for Accuracy and Acceptance link");
				iSelectValue(Locators.listAcceptReject, "Accept Order");
				String bcpchildAccId = dataDump.getValue("ChildAccountID");
				String parAccId = null, strchildAccId;
				int childAccId = 0;
				if (!bcpchildAccId.equalsIgnoreCase("")) {
					strchildAccId = bcpchildAccId;
					if(!dataDump.getValue("ParentAccountID_RT").isEmpty())
						parAccId = Integer.toString(Integer.parseInt(strchildAccId) - 1);
					dataDump.setValue("ParentAccountID_RT", parAccId);
				} else {
					parAccId = "998" + randomNumber(6);
					dataDump.setValue("ParentAccountID_RT", parAccId);
					childAccId = (Integer.parseInt(parAccId) + 1);
					strchildAccId = Integer.toString(childAccId);
					dataDump.setValue("ChildAccountID", strchildAccId);
				}
				if (!dataDump.getValue("SFOpportunityId_Site1").isEmpty()) {
					strchildAccId = String.valueOf(Integer.parseInt(parAccId) + 2);
					dataDump.setValue("ChildAccountID", strchildAccId);
				}
				iEnterText(Locators.txtParentAccID, parAccId, "Enter Parent ID");
				iEnterText(Locators.txtChildAccID, strchildAccId, "Enter Child ID");
				iClick(Locators.btnSave, null, "Click on Save button");
				if (!isLocatorVisible(Locators.lnkReviewOrderforAccuracyandAcceptance, 1))
					report.reportPassEvent("Review Order for Accuracy and Acceptance",
							"Review Order for Accuracy and Acceptance completed successfully");
				else
					report.reportHardFailEvent("Review Order for Accuracy and Acceptance",
							"Review Order for Accuracy and Acceptance Failed!!!", Status.FAIL);
			} else {
				report.reportHardFailEvent("Review Order for Accuracy and Acceptance",
						"Review Order for Accuracy and Acceptance tray is not displayed!!!", Status.FAIL);
				String eMsg = "Review Order for Accuracy and Acceptance tray is not displayed--- ";
				log.error(eMsg);
				mstatus = false;
			}
		} catch (Exception e) {
			report.reportHardFailEvent("Review Order for Accuracy and Acceptance",
					"Review Order for Accuracy and Acceptance Failed!!!", Status.FAIL);
			String eMsg = "Error in Review Order for Accuracy and Acceptance --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}

	public boolean result() {
		mstatus = true;
		try {
			if (!isLocatorVisible(Locators.lnkReviewOrderforAccuracyandAcceptance, 5)) {
				iClick(Locators.tabHistory, null, "Click on History Tab");
			}
			if (waitForElement(testLocatorClickable(Locators.lnkReviewOrderforAccuracyandAcceptance, 2))) {
				iSelectValue(Locators.lstResult, "Order Accepted");
				iClick(Locators.btnSaveTray, null, "Click on Save button");
				if (!isLocatorVisible(Locators.lnkReviewOrderforAccuracyandAcceptance, 1))
					report.reportPassEvent("Review Order for Accuracy and Acceptance",
							"Review Order for Accuracy and Acceptance completed successfully");
				else
					report.reportHardFailEvent("Review Order for Accuracy and Acceptance",
							"Review Order for Accuracy and Acceptance Failed!!!", Status.FAIL);
			}
			else {
				report.reportHardFailEvent("Review Order for Accuracy and Acceptance",
						"Review Order for Accuracy and Acceptance tray is not displayed!!!", Status.FAIL);
				String eMsg = "Review Order for Accuracy and Acceptance tray is not displayed--- ";
				log.error(eMsg);
				mstatus = false;
			}

		} catch (Exception e) {
			report.reportHardFailEvent("Review Order for Accuracy and Acceptance",
					"Review Order for Accuracy and Acceptance Failed!!!", Status.FAIL);
			String eMsg = "Error in Review Order for Accuracy and Acceptance --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}

}
