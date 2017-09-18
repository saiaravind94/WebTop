package com.comcast.pages.webtop;

import com.comcast.commons.WebtopPage;
import com.comcast.pages.webtop.WebTopLogInPage.Locators;
import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.utils.ComparisonType;
import com.comcast.utils.DataTable;
import com.comcast.utils.Page;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import java.awt.AWTException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class WorkOrderComments extends WebtopPage {
	Logger log = Logger.getLogger(WorkOrderComments.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public WorkOrderComments(FrameworkContext context) {
		super(context, "WorkOrderComments");
		report = context.getReport();
		this.context = context;
		this.dataSet = context.getDataTable();
		log.debug("Done calling WebToppage");
	}

	@Override
	protected boolean isValidPage() {
		return true;
	}

	public static class Locators {
		public static final String elemWorkOrderComments = "elemWorkOrderComments";
		public static final String row_Auto_Provisioning_DA_DL_CNAM_At_Neustar = "row_Auto_Provisioning_DA_DL_CNAM_At_Neustar";
		public static final String row_ValidateETSCUpdate = "row_ValidateETSCUpdate";
		public static final String row_DeviceSwapOrderErrorMessage = "row_DeviceSwapOrderErrorMessage";
		public static final String row_ValidateFOCDateEntered="row_ValidateFOCDateEntered";
	}

	public boolean PONVerification() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.elemWorkOrderComments, 2)) {
				iClick(Locators.elemWorkOrderComments, null,
						"Click on Entended Info button:Webtop Home Page:Click on Entended Info button");
				if (!isLocatorVisible(Locators.row_Auto_Provisioning_DA_DL_CNAM_At_Neustar, 2)) {
					jsClick(testLocatorClickable(Locators.elemWorkOrderComments));				
					String actualTest = testLocatorClickable(Locators.row_Auto_Provisioning_DA_DL_CNAM_At_Neustar, 5)
							.getText();
					if (actualTest.contains("PON: BVE")) {
						log.debug("Actual text is: " + actualTest + "Expected pattern message is: PON: BVE");
						report.reportPassEvent("PON verification from work order comments",
								"PON verification from work order comments is done");
					} else {
						log.debug("Actual text is: " + actualTest + "Expected pattern message is: PON: BVE");
						report.reportSoftFailEvent("PON verification from work order comments",
								"PON verification from work order comments failed. Actual text is: " + actualTest
										+ "Expected pattern message is: *.PON: BVE\\d$");
					}
				}				
			}
		} catch (Exception e) {
			report.reportSoftFailEvent("PON verification from work order comments",
					"PON verification from work order comments failed!!!");
			String eMsg = "PON verification from work order comments failed. --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}

	public boolean ETSCCountVerification(String Expected) {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.elemWorkOrderComments, 2)) {
				iClick(Locators.elemWorkOrderComments, null,
						"Click on Entended Info button:Webtop Home Page:Click on Entended Info button");
				if (!isLocatorVisible(Locators.row_ValidateETSCUpdate, 2)) {
					jsClick(testLocatorClickable(Locators.elemWorkOrderComments));
				}
				String actualTest = testLocatorClickable(Locators.row_ValidateETSCUpdate, 5).getText();
				if (actualTest.contains("ETSC is " + Expected)) {
					log.debug("Actual text is: " + actualTest + "Expected pattern message is: ETSC is " + Expected);
					report.reportPassEvent("ETSC Count verification from work order comments",
							"ETSC Count verification from work order comments is done");
				} else {
					log.debug("Actual text is: " + actualTest + "Expected pattern message is: ETSC is " + Expected);
					report.reportSoftFailEvent("ETSC Count verification from work order comments",
							"ETSC Count verification from work order comments failed. Actual text is: " + actualTest
									+ "Expected pattern message is: ETSC Count is " + Expected);
				}
			}
		} catch (Exception e) {
			report.reportHardFailEvent("ETSC Count  verification from work order comments",
					"ETSC Count  verification from work order comments failed!!!");
			String eMsg = "ETSC Count  verification from work order comments failed. --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean deviceSwapErrorMsgValidation(String Expected) {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.elemWorkOrderComments, 2)) {
				iClick(Locators.elemWorkOrderComments, null,
						"Click on Entended Info button:Webtop Home Page:Click on Entended Info button");
				if (!isLocatorVisible(Locators.row_DeviceSwapOrderErrorMessage, 2)) {
					jsClick(testLocatorClickable(Locators.elemWorkOrderComments));
				}
				String actualTest = testLocatorClickable(Locators.row_DeviceSwapOrderErrorMessage, 5).getText();
				if (actualTest.contains(Expected)) {
					log.debug("Actual text is: " + actualTest + "Expected message is: " + Expected);
					report.reportPassEvent("Device Swap Error Message Validation from work order comments",
							"Device Swap Error Message Validation from work order comments is done");
				} else {
					log.debug("Actual text is: " + actualTest + "Expected message is: " + Expected);
					report.reportSoftFailEvent("Device Swap Error Message Validation from work order comments",
							"Device Swap Error Message Validation from work order comments failed. Actual text is: " + actualTest
									+ "Expected message is: " + Expected);
				}
			}
		} catch (Exception e) {
			report.reportHardFailEvent("Device Swap Error Message Validation from work order comments",
					"Device Swap Error Message Validation from work order comments failed!!!");
			String eMsg = "Device Swap Error Message Validation from work order comments failed. --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
	public boolean focValidation(String date){
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.elemWorkOrderComments, 2)) {
				iClick(Locators.elemWorkOrderComments, null,
						"Click on Entended Info button:Webtop Home Page:Click on Entended Info button");
				
				if (!isLocatorVisible(Locators.row_ValidateFOCDateEntered, 2)) {
					jsClick(testLocatorClickable(Locators.elemWorkOrderComments));
				}
				
				iValidateLocatorValue(Locators.row_ValidateFOCDateEntered, "FOC Date Entered in invalid format: "+ date +". Please use MM/DD/YYYY.", ComparisonType.EQUAL, "Validation of FOC date field with invalid date");
			}
		} catch (Exception e) {
			report.reportHardFailEvent("FOC Date Error validation from Work Order Comments for  Ported TN",
					"FOC Date Error validation from Work Order Comments for  Ported TN failed!!!");
			String eMsg = "FOC Date Error validation from Work Order Comments for  Ported TN failed!!!" + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
		
	}
