package com.comcast.pages.simon;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.Report;
import com.comcast.reporting.ReportPath;
import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.utils.ComparisonType;
import com.comcast.utils.DataDump;
import com.comcast.utils.DataTable;

public class OrderSummaryPage extends WebtopPage {
	FrameworkContext context;
	DataTable dataSet;
	Logger log = Logger.getLogger(OrderSummaryPage.class);

	private boolean mstatus;

	public static class Locators {
		public static final String txtMACDType = "txtMACDType";
		public static final String txtOrderDescription = "txtOrderDescription";
		public static final String txtTTSTicket = "txtTTSTicket";
		public static final String btnSave = "btnSave";
		public static final String txtChangeType = "txtChangeType";
		public static final String lnkClickAddButton = "lnkClickAddButton";
		public static final String txtEntityType = "txtEntityType";
		public static final String txtEntityName = "txtEntityName";
		public static final String txtSeatName = "txtSeatName";
		public static final String txtFirstName = "txtFirstName";
		public static final String txtLastName = "txtLastName";
		public static final String txtSeatType = "txtSeatType";
		public static final String txtSeatPurpose = "txtSeatPurpose";
		public static final String txtChangeTypeEdit = "txtChangeTypeEdit";
		public static final String txtEntityTypeEdit = "txtEntityTypeEdit";
		public static final String txtActionTypeEdit = "txtActionTypeEdit";
		public static final String txtItemEdit = "txtItemEdit";
		public static final String txtchangetoEdit = "txtchangetoEdit";
		public static final String closeOrderSummary = "closeOrderSummary";
		public static final String txtrmSeatName = "txtrmSeatName";
		public static final String btnDownloadPDF = "btnDownloadPDF";
		public static final String txtChangeTypeQueue = "txtChangeTypeQueue";
		public static final String txtEntityCallQueue = "txtEntityCallQueue";
		public static final String txtCallQueueNameNameCallQueue = "txtCallQueueNameNameCallQueue";
		public static final String txtTimeZoneCallQueue = "txtTimeZoneCallQueue";
		public static final String txtRemoveCallQueueName = "txtRemoveCallQueueName";
		public static final String txtAddTFTN = "txtAddTFTN";
		public static final String txtAddTFTNPortType = "txtAddTFTNPortType";
		public static final String txtAddTFTNAreaOfService = "txtAddTFTNAreaOfService";
		public static final String txtEditTN = "txtEditTN";
		public static final String txtNewFNEditTN = "txtNewFNEditTN";
		public static final String txtCurrentFNEditTN = "txtCurrentFNEditTN";
		public static final String txtNewLNEditTN = "txtNewLNEditTN";
		public static final String txtCurrentLNEditTN = "txtCurrentLNEditTN";
		public static final String txtTFTNRemove = "txtTFTNRemove";
		public static final String btnOrderSummary = "btnOrderSummary";
		public static final String dropDownOrderSummaryType = "dropDownOrderSummaryType";
		public static final String txtESGLicenseQuantity = "txtESGLicenseQuantity";
		public static final String txtSiteType = "txtSiteType";
		public static final String txtTNRCF = "txtTNRCF";
		public static final String txtTnlaRCF = "txtTnlaRCF";
		public static final String txtRCFState = "txtRCFState";
		public static final String txtRCFPort = "txtRCFPort";
		public static final String txtEditRCFTN = "txtEditRCFTN";
		public static final String txtEditRCFTransTN = "txtEditRCFTransTN";
		public static final String txtEditRCFNFN = "txtEditRCFNFN";
		public static final String txtEditRCFNLN = "txtEditRCFNLN";
		public static final String txtEditRCFCFN = "txtEditRCFCFN";
		public static final String txtEditRCFCLN = "txtEditRCFCLN";
		public static final String txtRCTRemTN = "txtRCTRemTN";
		public static final String txtRecptionistName = "txtRecptionistName";
		public static final String txtRecptionistUsers = "txtRecptionistUsers";
		public static final String txtRecpNameRemove = "txtRecpNameRemove";
		public static final String txtRecpTNRemove = "txtRecpTNRemove";
		public static final String txtAutoAttName = "txtAutoAttName";
		public static final String txtAutoAttTimeZone = "txtAutoAttTimeZone";
		public static final String txtAutoAttPhoneNo = "txtAutoAttPhoneNo";
		public static final String txtTimeSchedulentsAutoAtt = "txtTimeSchedulentsAutoAtt";
		public static final String txtHolidayScheduleAutoAtt = "txtHolidayScheduleAutoAtt";
		public static final String txtAutoAttRmName = "txtAutoAttRmName";
		public static final String txtAutoAttRnPhoneNo = "txtAutoAttRnPhoneNo";
		public static final String txtAddTN = "txtAddTN";
		public static final String txtPortTNAdd = "txtPortTNAdd";
		public static final String txtEditTNMACD = "txtEditTNMACD";
		public static final String txtEditTNNFN = "txtEditTNNFN";
		public static final String txtEditTNNLN = "txtEditTNNLN";
		public static final String txtEditTNCFN = "txtEditTNCFN";
		public static final String txtEditTNCLN = "txtEditTNCLN";
		public static final String txtTNRemove = "txtTNRemove";
		public static final String txtShareCallAppSeatName = "txtShareCallAppSeatName";
		public static final String txtShareCallAppDeviceType = "txtShareCallAppDeviceType";
		public static final String txtUCSeatReceptionist = "txtUCSeatReceptionist";
		public static String txtAutoAttendent = "";
		public static String txtHuntGroup = "";
		public static String txtVoiceSelectionAttributes = "";
		public static final String txtAddHuntGroup = "txtAddHuntGroup";
		public static final String txtCallQueueName = "txtCallQueueName";
		public static final String txtHGName = "txtHGName";
		public static final String txtHGrnName = "txtHGrnName";
		public static final String txtHGphoneNo = "txtHGphoneNo";
		public static String txtDeviceType = "";
		public static final String txtCallQueueNameAVI = "txtCallQueueNameAVI";
		public static final String txtCallQueueExt = "txtCallQueueExt";
		public static final String txtCallQueueNotes = "txtCallQueueNotes";
		public static final String txtCallIDLN = "txtCallIDLN";
		public static final String txtCallIDFN = "txtCallIDFN";
		public static final String txtCQTimeZone = "txtCQTimeZone";
		public static final String txtCQGrpouPolicy = "txtCQGrpouPolicy";
		public static final String txtCQQueueLength = "txtCQQueueLength";
		public static final String txtCQAllowEscapefromQueue = "txtCQAllowEscapefromQueue";
		public static final String txtCQEscDigit = "txtCQEscDigit";
		public static final String txtCQAgents = "txtCQAgents";
		public static final String txtOverflowAction = "txtOverflowAction";
		public static final String txtTransferTo = "txtTransferTo";
		public static final String txtOverFlowCallAfter = "txtOverFlowCallAfter";
		public static final String txtCQTNAVI = "txtCQTNAVI";
		public static String txtHGAviOrderSummary = "";
		public static String txtAAAviOrderSummary = "";
		public static String txtHGUsersConfigured = "";
		public static String txtSeatDetailsAttributes = "";
		public static final String txtSCASeatName = "txtSCASeatName";
		public static final String txtSCADeviceType = "txtSCADeviceType";
		public static final String txSideCars = "txSideCars";
		public static final String txtLine = "txtLine";
		public static final String txtUser = "txtUser";
		public static final String txtType = "txtType";
		public static final String txtSCBLFName = "txtSCBLFName";
		public static final String txtBLFWho = "txtBLFWho";
		public static final String txtBLFType = "txtBLFType";
		public static String txtcqUserConfiguredinSimon = "";
		public static String txtcqIndicator = "";
		public static String txtHGIndicator = "";
		public static final String txtCustDetailsGroupName = "txtCustDetailsGroupName";
		public static final String txtAutoTestSDPMName = "txtAutoTestSDPMName";
		public static final String txtgrpIDCustDetals = "txtgrpIDCustDetals";
		public static final String txtgrpID = "txtgrpID";
		public static final String txtLeadID = "txtLeadID";
		public static final String txtEnterpiseID = "txtEnterpiseID";
		public static final String txtESGLicenseQTY = "txtESGLicenseQTY";
		public static final String txtDirectoryDisplayName = "txtDirectoryDisplayName";
		public static final String btnEXPDirectoryName = "btnEXPDirectoryName";
		public static String txtSeatDirectoryName = "";
		public static final String txtCallerIdName = "txtCallerIdName";
		public static final String txtPlaceListingAs = "txtPlaceListingAs";
		public static final String txtHeaderText = "txtHeaderText";
		public static final String txtHeaderCode = "txtHeaderCode";
		public static final String txtSICCode = "txtSICCode";
		public static final String txtDirectoryListingNum = "txtDirectoryListingNum";
		public static final String txtOmitAddress = "txtOmitAddress";
		public static final String txtEquipmentType = "txtEquipmentType";
		public static final String txtEquipmentAmount = "txtEquipmentAmount";
		public static final String txtUserAAName = "txtUserAAName";
		public static final String AAType = "AAType";
		public static final String txtAATypeUserSummary = "txtAATypeUserSummary";
		public static final String txtTNPurpose = "txtTNPurpose";
		public static final String txtTNEXT = "txtTNEXT";
		public static final String txtTNInvName = "txtTNInvName";
		public static final String txtTNInvTN = "txtTNInvTN";
		public static final String txtSeatDetails = "txtSeatDetails";
		public static final String txtSeatDetailsPurpose = "txtSeatDetailsPurpose";
		public static final String txtSeatDetailsName = "txtSeatDetailsName";
		public static final String txtSeatDetailsPHModel = "txtSeatDetailsPHModel";
		public static final String txtILD = "txtILD";
		public static final String txtgrpIDTollFree = "txtgrpIDTollFree";
		public static final String txtSiteDetailsGroupName = "txtSiteDetailsGroupName";
		public static final String txtScopeExtnDialing = "txtScopeExtnDialing";
		public static final String txtScopeNameDialing = "txtScopeNameDialing";
		public static final String txtNameDialingEntries = "txtNameDialingEntries";

	}

	public OrderSummaryPage(FrameworkContext context) {
		super(context, "OrderSummaryPage");
		report = context.getReport();
		this.context = context;
		this.dataSet = context.getDataTable();
		context.getUserDetail();
		context.getSettings();
		this.browser = context.getDriver();
	}

	public boolean verifyOrderDetails(String MACDType, String orderDescription, String TTSTicket) {
		mstatus = true;
		try {
			if (testLocatorClickable(Locators.txtMACDType, 15) != null) {
				iValidateLocatorValue(Locators.txtMACDType, MACDType, ComparisonType.EQUAL,
						"Verify the MACDType in Order Summary Section");
				iValidateLocatorValue(Locators.txtOrderDescription, orderDescription, ComparisonType.EQUAL,
						"Verify the Order Description in Order Summary Section");
				iValidateLocatorValue(Locators.txtTTSTicket, TTSTicket, ComparisonType.EQUAL,
						"Verify the TTS Ticket value in Order Summary Section");
			}

		} catch (Exception Ex) {
			report.reportHardFailEvent("Verify Order details in OS section",
					"Verify Order details in OS section Failed" + Ex.getMessage());
			eMsg = report.getMessage()
					+ "Verify Order details in OS section Failed!! and Details not seen in Order Summary Section"
					+ Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;

	}

	public boolean verifySeatSettingsInSeatAddDetails(String changeType, String entityType, String seatName,
			String seatType, String firstName, String lastName, String seatPurpose) {
		mstatus = true;
		try {
			if (testLocatorClickable(Locators.txtMACDType, 15) != null) {
				iValidateLocatorValue(Locators.txtChangeType, changeType, ComparisonType.EQUAL,
						"Verify the ChangeType in Order Summary Section");
				iValidateLocatorValue(Locators.txtEntityType, entityType, ComparisonType.EQUAL,
						"Verify the EntityTypen in Order Summary Section");
				iValidateLocatorValue(Locators.txtEntityName, seatName, ComparisonType.EQUAL,
						"Verify the EntityName Entered in Order Summary Section");
				iValidateLocatorValue(Locators.txtSeatName, seatName, ComparisonType.EQUAL,
						"Verify the SeatName Entered in Order Summary Section");
				iValidateLocatorValue(Locators.txtFirstName, firstName, ComparisonType.EQUAL,
						"Verify the FirstName Entered in Order Summary Section");
				iValidateLocatorValue(Locators.txtLastName, lastName, ComparisonType.EQUAL,
						"Verify the LastName Entered in Order Summary Section");
				iValidateLocatorValue(Locators.txtSeatType, seatType, ComparisonType.EQUAL,
						"Verify the SeatType Entered in Order Summary Section");
				iValidateLocatorValue(Locators.txtSeatPurpose, seatPurpose, ComparisonType.EQUAL,
						"Verify the SeatPurpose Entered in Order Summary Section");
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verify Seat settings under seat add details section",
					"Verify Seat settings under seat add details section Failed" + Ex.getMessage());
			eMsg = report.getMessage()
					+ "Verify Seat settings under seat add details section is Failed!! and Details not seen in Order Summary Section"
					+ Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;

	}

	public boolean verifySeatEditDetails(String changeType, String entityType, String changeTo) {
		mstatus = true;
		try {
			if (testLocatorClickable(Locators.txtChangeTypeEdit, 15) != null) {
				Scroll_Page_Down();
				iValidateLocatorValue(Locators.txtChangeTypeEdit, changeType, ComparisonType.EQUAL,
						"Verify the ChangeType for the Edit Seat in Order Summary Section");
				iValidateLocatorValue(Locators.txtEntityTypeEdit, entityType, ComparisonType.EQUAL,
						"Verify the EntityTypen for the Edit Seat in Order Summary Section");
				iValidateLocatorValue(Locators.txtchangetoEdit, changeTo, ComparisonType.EQUAL,
						"Verify the Seat type changed as part of Edit in Order Summary Section");

			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verify Seat settings under seat Edit details section",
					"Verify Seat settings under seat Edit details section Failed" + Ex.getMessage());
			eMsg = report.getMessage()
					+ "Verify Seat settings under seat add details section is Failed!! and Details not seen in Order Summary Section"
					+ Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;

	}

	public boolean closeOrderSummary() {
		mstatus = true;
		try {
			if (testLocatorClickable(Locators.closeOrderSummary, 15) != null) {
				iClick(Locators.closeOrderSummary);
				waitForSpinnerComplete();
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Close the Order Summary",
					"Unable to close the Order Summary" + Ex.getMessage());
			eMsg = report.getMessage() + "Close the Order Summary-Closing the Order Summary closed" + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public boolean removeSeatOrderSummary(String seatName) {
		mstatus = true;
		try {
			if (testLocatorClickable(Locators.txtrmSeatName, 15) != null) {
				scrollDown();
				iValidateLocatorValue(Locators.txtrmSeatName, seatName, ComparisonType.EQUAL,
						"The seatname removed is displayed in the Order Summary ");
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verification of the Removed seat in the order summary",
					"The seatname removed is not displayed in the Order Summary" + Ex.getMessage());
			eMsg = report.getMessage() + "Verification of the Removed seat in the order summary-Failed"
					+ Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public boolean downloadPDF() {
		mstatus = true;
		try {
			if (testLocatorClickable(Locators.btnDownloadPDF, 30) != null) {
				jsClick(getLocatorWEList(Locators.btnDownloadPDF).get(0));
				sleep(2000);
				waitForSpinnerComplete();
				sleep(15000);
				String latestOSPDFFile = getLatestFile(System.getProperty("user.home") + "\\Downloads\\",
						"OrderSummary_.*.pdf");
				String reportPath = ReportPath.getInstance().getReportPath();
				FileUtils.copyFile(new File(latestOSPDFFile), new File(
						reportPath + "\\" + context.getTestCaseName() + "_" + new File(latestOSPDFFile).getName()));
				log.info("Order Summary File path: " + latestOSPDFFile);
				dataDump.setValue("OrderSummaryPDF", latestOSPDFFile);
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Download order summary PDF",
					"Download order summary PDF failed. " + Ex.getMessage());
			eMsg = report.getMessage() + "Download order summary PDF Failed" + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public boolean addCallQueueOrderSummary(String changeType, String entityType, String callQueueName,
			String timeZone) {
		mstatus = true;
		try {
			if (testLocatorClickable(Locators.txtChangeTypeQueue, 15) != null) {
				iValidateLocatorValue(Locators.txtChangeTypeQueue, changeType, ComparisonType.EQUAL,
						"Validate of the CallQueueChangeType");
				iValidateLocatorValue(Locators.txtEntityCallQueue, entityType, ComparisonType.EQUAL,
						"Validate of the CallQueueEntityType");
				iValidateLocatorValue(Locators.txtCallQueueNameNameCallQueue, callQueueName, ComparisonType.EQUAL,
						"Validate of the CallQueueName");
				iValidateLocatorValue(Locators.txtTimeZoneCallQueue, timeZone, ComparisonType.EQUAL,
						"Validate of the CallQueue-Selected Time Zone");
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verification of Order Summary for Add-CallQueue",
					"The Call Queue Details entered are not updated in Order Summary " + Ex.getMessage());
			eMsg = report.getMessage() + "The Call Queue Details entered are not updated in Order Summary"
					+ Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public boolean removeCallQueueOrderSummary(String removeCallQueueName) {
		mstatus = true;
		try {
			if (testLocatorClickable(Locators.txtRemoveCallQueueName, 15) != null) {
				iValidateLocatorValue(Locators.txtRemoveCallQueueName, removeCallQueueName, ComparisonType.EQUAL,
						"Verify the RemoveCallQueueName");
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verify the Remove Call Queue Name",
					"The Remove Call Queue Name Details entered are not updated in Order Summary " + Ex.getMessage());
			eMsg = report.getMessage() + "The Remove Call Queue Name Details are not updated in Order Summary"
					+ Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public boolean tollFreeAddOrderSummary(String TN, String areaOfService, String portType) {
		mstatus = true;
		try {
			if (testLocatorClickable(Locators.btnDownloadPDF, 15) != null) {
				iValidateLocatorValue(Locators.txtAddTFTN, TN, ComparisonType.EQUAL, "Verify the Toll FreeTN Added");
				iValidateLocatorValue(Locators.txtAddTFTNAreaOfService, areaOfService, ComparisonType.EQUAL,
						"Verify the Area of Service for the Toll Free Number");
				iValidateLocatorValue(Locators.txtAddTFTNPortType, portType, ComparisonType.EQUAL,
						"Verify the Port Type Added");
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verify the Remove Call Queue Name",
					"The Remove Call Queue Name Details entered are not updated in Order Summary " + Ex.getMessage());
			eMsg = report.getMessage() + "The Remove Call Queue Name Details are not updated in Order Summary"
					+ Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public boolean tollFreeEditOrderSummary(String TN, String C_FN, String C_LN, String N_FN, String N_LN) {
		mstatus = true;
		try {
			if (testLocatorClickable(Locators.btnDownloadPDF, 15) != null) {
				iValidateLocatorValue(Locators.txtEditTN, TN, ComparisonType.EQUAL, "Verify if the Edit TN is entered");
				iValidateLocatorValue(Locators.txtCurrentFNEditTN, C_FN, ComparisonType.EQUAL,
						"Verify if the Current First Name is Displayed in Order Summary");
				iValidateLocatorValue(Locators.txtCurrentLNEditTN, C_LN, ComparisonType.EQUAL,
						"Verify if the Current Last Name is Displayed in Order Summary");
				iValidateLocatorValue(Locators.txtNewFNEditTN, N_FN, ComparisonType.EQUAL,
						"Verify if the New First Name is Displayed in Order Summary");
				iValidateLocatorValue(Locators.txtNewLNEditTN, N_LN, ComparisonType.EQUAL,
						"Verify if the New Last Name is Displayed in Order Summary");
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verify the Edit Toll Free TN",
					"The Edit Toll Free TN is not updated in Order Summary " + Ex.getMessage());
			eMsg = report.getMessage() + "The Edit Toll Free TN is not updated in Order Summary" + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public boolean tollFreeRemoveOrderSummary(String TN) {
		mstatus = true;
		try {
			if (testLocatorClickable(Locators.btnDownloadPDF, 15) != null) {
				iValidateLocatorValue(Locators.txtTFTNRemove, TN, ComparisonType.EQUAL,
						"Verify if the Toll Free Number Removed is Displayed in Order Summary");

			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verify the Edit Toll Free TN",
					"The Toll Free TN Removed is not updated in Order Summary " + Ex.getMessage());
			eMsg = report.getMessage() + "The Toll Free TN removed is not updated in Order Summary" + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public boolean clickOrderSummary() {
		mstatus = true;
		try {
			if (testLocatorClickable(Locators.btnOrderSummary, 20) != null) {
				jsClick(getLocatorWEList(Locators.btnOrderSummary).get(0));
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Click on Order Summary", "Order Summary is not not Selected" + Ex.getMessage());
			eMsg = report.getMessage() + "Unable to Select the Order Summary!! " + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public boolean launchOrderSumaryType(String orderSummaryType) {
		mstatus = true;
		try {
			if (testLocatorClickable(Locators.btnOrderSummary, 30) != null) {
				if (!isLocatorVisible(Locators.btnDownloadPDF, 1)) {
					jsClick(getLocatorWEList(Locators.btnOrderSummary).get(0));
				}
				if (isLocatorVisible(Locators.dropDownOrderSummaryType, 30)) {
					iClick(Locators.dropDownOrderSummaryType);
					selectvalue_dropdown("div", orderSummaryType, true);
					testLocatorClickable(Locators.closeOrderSummary);
				}
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Launch Order Summary Type: " + orderSummaryType,
					"Launch Order Summary Type" + Ex.getMessage());
			eMsg = report.getMessage() + "Unable to Launch Order Summary Type: " + orderSummaryType + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public boolean verifyESGLicenceQuantity(String expected) {
		mstatus = true;
		try {
			if (testLocatorClickable(Locators.btnDownloadPDF, 15) != null) {
				iValidateLocatorValue(Locators.txtESGLicenseQuantity, expected, ComparisonType.EQUAL,
						"Verify if the ESG Licence quantity is Displayed in Order Summary as expected");
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verify the ESG License quantity",
					"The ESG License quantity is not displayed in Order Summary " + Ex.getMessage());
			eMsg = report.getMessage() + "The ESG License quantity is not displayed in Order Summary" + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public boolean verifySiteType(String expected) {
		mstatus = true;
		try {
			if (testLocatorClickable(Locators.btnDownloadPDF, 15) != null) {
				iValidateLocatorValue(Locators.txtSiteType, expected, ComparisonType.EQUAL,
						"Verify if the Site Type is Displayed in Order Summary as expected");
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verify the Site Type",
					"The Site Type is not displayed in Order Summary as expected. \n" + Ex.getMessage());
			eMsg = report.getMessage() + "The Site Type is not displayed in Order Summary as expected. \n"
					+ Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public boolean rcfAddDetailsSummary(String TN, String translation, String portType, String state) {
		mstatus = true;
		try {
			if (testLocatorClickable(Locators.btnDownloadPDF, 15) != null) {
				iValidateLocatorValue(Locators.txtTNRCF, TN, ComparisonType.EQUAL,
						"Verify the RCF TN Added in Order Summary");
				iValidateLocatorValue(Locators.txtTnlaRCF, translation, ComparisonType.EQUAL,
						"Verify the Translation TN Added in Order Summary");
				iValidateLocatorValue(Locators.txtRCFPort, portType, ComparisonType.EQUAL,
						"Verify the Port Type Added in Order Summary");
				iValidateLocatorValue(Locators.txtRCFState, state, ComparisonType.EQUAL,
						"Verify the State Added in Order Summary");
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verify the Add RCF OrderSummary Page",
					"The RCF Details added are not updated in Order Summary " + Ex.getMessage());
			eMsg = report.getMessage() + "The RCF Details added are not updated in Order Summary" + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public boolean rcfEditDetailsSummary(String TN, String translationTN, String CFN, String CLN, String NFN,
			String NLN) {
		mstatus = true;
		try {
			if (testLocatorClickable(Locators.btnDownloadPDF, 15) != null) {
				iValidateLocatorValue(Locators.txtEditRCFTN, TN, ComparisonType.EQUAL,
						"Verify the RCF TN Edited in Order Summary");
				iValidateLocatorValue(Locators.txtEditRCFTransTN, translationTN, ComparisonType.EQUAL,
						"Verify the Translation TN for Edit RCF in Order Summary");
				iValidateLocatorValue(Locators.txtEditRCFCFN, CFN, ComparisonType.EQUAL,
						"Verify the Current First Name for Edit RCF in Order Summary");
				iValidateLocatorValue(Locators.txtEditRCFCLN, CLN, ComparisonType.EQUAL,
						"Verify the Current Last Name for Edit RCF in Order Summary");
				iValidateLocatorValue(Locators.txtEditRCFNFN, NFN, ComparisonType.EQUAL,
						"Verify the New First Name for Edit RCF in Order Summary");
				iValidateLocatorValue(Locators.txtEditRCFNLN, NLN, ComparisonType.EQUAL,
						"Verify the New Last Name for Edit RCF in Order Summary");
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verify the Edit RCF OrderSummary Page",
					"The Edit RCF Details  are not updated in Order Summary " + Ex.getMessage());
			eMsg = report.getMessage() + "The Edit RCF Details  are not updated in Order Summary" + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public boolean rcfRemoveDetailsSummary(String TN) {
		mstatus = true;
		try {
			if (testLocatorClickable(Locators.btnDownloadPDF, 15) != null) {
				iValidateLocatorValue(Locators.txtRCTRemTN, TN, ComparisonType.EQUAL,
						"Verify the Remove RCF TN is displayed in Order Summary");
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verify the Remove RCF TN is displayed in Order Summary",
					"Remove RCF TN is not displayed in Order Summary " + Ex.getMessage());
			eMsg = report.getMessage() + "Remove RCF TN is not displayed in Order Summary" + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public boolean addReceptionistOrderSummary(String name, String users) {
		mstatus = true;
		try {
			if (testLocatorClickable(Locators.btnDownloadPDF, 15) != null) {
				iValidateLocatorValue(Locators.txtRecptionistName, name, ComparisonType.EQUAL,
						"Verify the Receptionist Name added is displayed in Order Summary");
				iValidateLocatorValue(Locators.txtRecptionistUsers, users, ComparisonType.EQUAL,
						"Verify the Users added for the Receptionist is displayed in Order Summary");
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verify the Receptionist Name and Users are displayed in Order Summary",
					"Receptionist Name and Users are not displayed in Order Summary " + Ex.getMessage());
			eMsg = report.getMessage() + "Receptionist Name and Users are not displayed in Order Summary"
					+ Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public boolean removeReceptionistOrderSummary(String name, String TN) {
		mstatus = true;
		try {
			if (testLocatorClickable(Locators.btnDownloadPDF, 15) != null) {
				iValidateLocatorValue(Locators.txtRecpNameRemove, name, ComparisonType.EQUAL,
						"Verify the Receptionist Name to be removed is displayed in Order Summary");
				iValidateLocatorValue(Locators.txtRecpTNRemove, TN, ComparisonType.EQUAL,
						"Verify the TN added for the Receptionist Removal is displayed in Order Summary");
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verify the Receptionist Name and TN are displayed in Order Summary",
					"Receptionist Name and TN are not displayed in Order Summary " + Ex.getMessage());
			eMsg = report.getMessage() + "Receptionist Name and TN are not displayed in Order Summary"
					+ Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public boolean addAutoAttendentOrderSummary(String name, String TN, String timeZone, String timeSchedule,
			String holidayDescription) {
		mstatus = true;
		try {
			if (testLocatorClickable(Locators.btnDownloadPDF, 15) != null) {
				iValidateLocatorValue(Locators.txtAutoAttName, name, ComparisonType.EQUAL,
						"Verify the Auto Attendent Name to be added in Order Summary");
				iValidateLocatorValue(Locators.txtAutoAttPhoneNo, TN, ComparisonType.EQUAL,
						"Verify the Auto Attendent Phone Number to be added in Order Summary");
				iValidateLocatorValue(Locators.txtAutoAttTimeZone, timeZone, ComparisonType.EQUAL,
						"Verify the Time Zone Selected for the Auto Attendent Added in Order Summary");
				iValidateLocatorValue(Locators.txtTimeSchedulentsAutoAtt, timeSchedule, ComparisonType.EQUAL,
						"Verify the TimeSchedule notes added for the Auto Attendent  in Order Summary");
				iValidateLocatorValue(Locators.txtHolidayScheduleAutoAtt, holidayDescription, ComparisonType.EQUAL,
						"Verify the Holiday Schedule notes added for the Auto Attendent in Order Summary");
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verify the AutoAttendent Details added in Order Summary",
					"The AutoAttendent Details are not displayed in Order Summary" + Ex.getMessage());
			eMsg = report.getMessage() + "The AutoAttendent Details are not displayed in Order Summary"
					+ Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public boolean removeAutoAttendentOrderSummary(String name, String TN) {
		mstatus = true;
		try {
			if (testLocatorClickable(Locators.btnDownloadPDF, 15) != null) {
				iValidateLocatorValue(Locators.txtAutoAttRmName, name, ComparisonType.EQUAL,
						"Verify the Auto Attendent Name to be removed in Order Summary");
				iValidateLocatorValue(Locators.txtAutoAttRnPhoneNo, TN, ComparisonType.EQUAL,
						"Verify the Auto Attendent Phone Number to be Removed in Order Summary");
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verify the AutoAttendent Details removed in Order Summary",
					"The Auto-Attendent Details[Remove Auto Attend] are not displayed in Order Summary"
							+ Ex.getMessage());
			eMsg = report.getMessage()
					+ "The AutoAttendent Details[Remove Auto Attend] are not displayed in Order Summary"
					+ Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public boolean addTNOrderSummary(String TN, String portType) {
		mstatus = true;
		try {
			if (testLocatorClickable(Locators.btnDownloadPDF, 15) != null) {
				iValidateLocatorValue(Locators.txtAddTN, TN, ComparisonType.EQUAL,
						"Verify the TN Added in Order Summary");
				iValidateLocatorValue(Locators.txtPortTNAdd, portType, ComparisonType.EQUAL,
						"Verify the Port Type added for TN in Order Summary");
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verify the TN and Port Type  added in Order Summary",
					"TN and Port Type  are not added in Order Summary" + Ex.getMessage());
			eMsg = report.getMessage() + "TN and Port Type  are not added in Order Summary" + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public boolean editTNOrderSummary(String TN, String NFN, String NLN, String CFN, String CLN) {
		mstatus = true;
		try {
			if (testLocatorClickable(Locators.btnDownloadPDF, 15) != null) {
				iValidateLocatorValue(Locators.txtEditTNMACD, TN, ComparisonType.EQUAL,
						"Verify the Edit-TN Added in Order Summary");
				iValidateLocatorValue(Locators.txtEditTNNFN, NFN, ComparisonType.EQUAL,
						"Verify the New First Name for Edit TN in Order Summary");
				iValidateLocatorValue(Locators.txtEditTNNLN, NLN, ComparisonType.EQUAL,
						"Verify the New Last Name for Edit TN in Order Summary");
				iValidateLocatorValue(Locators.txtEditTNCFN, CFN, ComparisonType.EQUAL,
						"Verify the Current First Name for Edit TN in Order Summary");
				iValidateLocatorValue(Locators.txtEditTNCLN, CLN, ComparisonType.EQUAL,
						"Verify the Current Last Name for Edit TN in Order Summary");
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verify the TN Current and New FirstName/Last Name  added in Order Summary",
					"TN Current and New FirstName/Last Name are not added in Order Summary" + Ex.getMessage());
			eMsg = report.getMessage() + "TN Current and New FirstName/Last Name are not added in Order Summary"
					+ Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public boolean removeTNOrderSummary(String TN) {
		mstatus = true;
		try {
			if (testLocatorClickable(Locators.btnDownloadPDF, 15) != null) {
				iValidateLocatorValue(Locators.txtTNRemove, TN, ComparisonType.EQUAL,
						"Verify the TN Identified for Remove is displayed in Order Summary");
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verify the TN identified for remove is added in Order Summary",
					"Verify the TN identified for remove is added in Order Summary" + Ex.getMessage());
			eMsg = report.getMessage() + "Verify the TN identified for remove is added in Order Summary"
					+ Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public boolean verifyVoiceSelection(String[] voiceSelectionFieldList, String[] voiceSelectionExpectedValue) {
		mstatus = true;
		try {
			if (testLocatorClickable(Locators.btnDownloadPDF, 15) != null) {
				int idx = 0;
				for (String VoiceSelectionName : voiceSelectionFieldList) {
					addLocator(Locators.txtVoiceSelectionAttributes, "xpath",
							"//div[.='" + VoiceSelectionName + "']//following::div[2]",
							"Validate the Voice Lines Fields");
					iValidateLocatorValue(Locators.txtVoiceSelectionAttributes, voiceSelectionExpectedValue[idx],
							ComparisonType.EQUAL, "Verify the Expected Values in the Voice Selections All Sites Total");
					idx++;
				}
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent(
					"Verify the Key parameters displayed in the Voice Selections Section of Detailed Order Summary Page",
					"Key parameters is not displayed in the Voice Selections Section of Detailed Order Summary Page"
							+ Ex.getMessage());
			eMsg = report.getMessage()
					+ "Key parameters is not displayed in the Voice Selections Section of Detailed Order Summary Page"
					+ Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public boolean verifySeatDetails(String[] seatDetailsFieldList) {
		mstatus = true;
		try {
			if (testLocatorClickable(Locators.btnDownloadPDF, 15) != null) {
				for (int i = 0; i < seatDetailsFieldList.length; i++) {
					addLocator(Locators.txtSeatDetailsAttributes, "xpath",
							"//h3[.='Seat Details']//following::div[.='" + seatDetailsFieldList[i] + "']",
							"Validate the Seat Details Fields");
					iValidateLocatorValue(Locators.txtSeatDetailsAttributes, seatDetailsFieldList[i],
							ComparisonType.EQUAL,
							"Verify the Expected Values in Seat Details section of the Detailed Order Summary");
				}
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent(
					"Verify the Key parameters displayed in the Seat Detailed Section of Detailed Order Summary Page",
					"Key parameters is not displayed in the Seat Detailed Section of Detailed Order Summary Page"
							+ Ex.getMessage());
			eMsg = report.getMessage()
					+ "Key parameters is not displayed in the Seat Detailed Section of Detailed Order Summary Page"
					+ Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public boolean verifySharedCallAppearance(String seatName, String deviceType) {
		mstatus = true;
		try {
			if (testLocatorClickable(Locators.btnDownloadPDF, 15) != null) {
				iValidateLocatorValue(Locators.txtShareCallAppSeatName, seatName, ComparisonType.SUBSTR,
						"Verify the SeatName displayed in the Shared Call Appearance Section of Detailed Order Summary Page");

				iValidateLocatorValue(Locators.txtShareCallAppDeviceType, deviceType, ComparisonType.EQUAL,
						"Verify the Device Type displayed in the Shared Call Appearance Section of Detailed Order Summary Page");
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent(
					"Verify the Key parameters displayed in the Shared Call Appearance Section of Detailed Order Summary Page",
					"Key parameters is not displayed in the Shared Call Appearance Section of Detailed Order Summary Page"
							+ Ex.getMessage());
			eMsg = report.getMessage()
					+ "Key parameters is not displayed in the Shared Call Appearance Section of Detailed Order Summary Page"
					+ Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public boolean receptionistConsoleDetails(String uCSeatReceptionsistName) {
		mstatus = true;
		try {
			if (testLocatorClickable(Locators.btnDownloadPDF, 15) != null) {
				iValidateLocatorValue(Locators.txtUCSeatReceptionist, uCSeatReceptionsistName, ComparisonType.SUBSTR,
						"Verify the UC Seat Receptionist Name displayed in the Receptionsist Console Details Section of Detailed Order Summary Page");
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent(
					"Verify the UC Seat Receptionist Name displayed in the Receptionsist Console Details Section of Detailed Order Summary Page",
					"UC Seat Receptionist Name is not displayed in the Receptionsist Console Details Section of Detailed Order Summary Page"
							+ Ex.getMessage());
			eMsg = report.getMessage()
					+ "UC Seat Receptionist Name is not displayed in the Receptionsist Console Details Section of Detailed Order Summary Page"
					+ Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public boolean verifyCallQueueDetails(String callQueue) {
		mstatus = true;
		try {
			if (testLocatorClickable(Locators.btnDownloadPDF, 15) != null) {
				iValidateLocatorValue(Locators.txtCallQueueName, callQueue, ComparisonType.SUBSTR,
						"Verify the CallQueue Name displayed in the Call Queue Section of Detailed Order Summary Page");

			}
		} catch (Exception Ex) {
			report.reportHardFailEvent(
					"Verify the CallQueue Name displayed in the Call Queue Section of Detailed Order Summary Page",
					"CallQueue Name is not displayed in the Call Queue Section of Detailed Order Summary Page"
							+ Ex.getMessage());
			eMsg = report.getMessage()
					+ "CallQueue Name is not displayed in the Call Queue Section of Detailed Order Summary Page"
					+ Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public boolean verifyAutoAttendents(String[] AutoAttendents) {
		mstatus = true;
		try {
			if (testLocatorClickable(Locators.btnDownloadPDF, 15) != null) {
				addLocator(Locators.txtAutoAttendent, "xpath", "//h4[contains(text(),'Auto Attendant #')]",
						"AutoAttendentName");
				List<WebElement> AAList = getLocatorWEList(Locators.txtAutoAttendent);
				for (WebElement elem : AAList) {
					for (int i = 0; i < AutoAttendents.length; i++) {
						boolean matches = false;
						if (elem.getText().contains(AutoAttendents[i])) {
							matches = true;
							report.reportPassEvent("AA verification in OS page",
									"AA: " + AutoAttendents[i] + " is displayed as expected");
							break;
						}
						if (((i + 1) == AutoAttendents.length) && !matches) {
							report.reportSoftFailEvent("AA verification in OS page",
									"AA: " + AutoAttendents[i] + " is not displayed as expected");
						}
					}
				}

			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verify the Auto-Attendents in the Detailed Order Summary Page",
					"Auto Attendents are not displayed in the Detailed  Order Summary Page" + Ex.getMessage());
			eMsg = report.getMessage() + "Auto Attendents are not displayed in the Detailed  Order Summary Page"
					+ Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public boolean verifyHuntGroup(String[] HuntGroup) {
		mstatus = true;
		try {
			if (testLocatorClickable(Locators.btnDownloadPDF, 15) != null) {
				addLocator(Locators.txtHuntGroup, "xpath", "//h4[contains(text(),'Hunt Group #')]", "HuntGroupName");
				List<WebElement> HGList = getLocatorWEList(Locators.txtHuntGroup);
				for (WebElement elem : HGList) {
					for (int i = 0; i < HuntGroup.length; i++) {

						boolean matches = false;
						if (elem.getText().contains(HuntGroup[i])) {
							matches = true;
							report.reportPassEvent("Hunt Group verification in Detailed Order Summary page",
									"AA: " + HuntGroup[i] + " is displayed as expected");
							break;
						}
						if (((i + 1) == HuntGroup.length) && !matches) {
							report.reportSoftFailEvent("Hunt Group verification in Detailed Order Summary page",
									"AA: " + HuntGroup[i] + " is not displayed as expected");
						}
					}
				}
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verify the Hunt Group in the Detailed Order Summary Page",
					"Hunt Groups are not displayed in the Detailed  Order Summary Page" + Ex.getMessage());
			eMsg = report.getMessage() + "Hunt Groups are not displayed in the Detailed  Order Summary Page"
					+ Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public void PDFVerification(Boolean status, String description) {
		report.getReportSettings().takeScreenshotPassedStep = false;
		report.getReportSettings().takeScreenshotFailedStep = false;
		iReport("", description, status ? description + " Passed" : description + " Failed", status);
		report.getReportSettings().takeScreenshotPassedStep = true;
		report.getReportSettings().takeScreenshotFailedStep = true;
	}

	public boolean verifyHuntGroupName(String HGName) {
		mstatus = true;
		try {
			if (testLocatorClickable(Locators.btnDownloadPDF, 15) != null) {
				iValidateLocatorValue(Locators.txtHGName, HGName, ComparisonType.EQUAL,
						"Verify the Hunt Group Name displayed in the Hunt Group Section of Order Summary Page");

			}
		} catch (Exception Ex) {
			report.reportHardFailEvent(
					"Verify the Hunt Group Name displayed in the Hunt Group Section of Order Summary Page",
					"Hunt Group Name is not displayed in the Hunt Group Section of  Order Summary Page"
							+ Ex.getMessage());
			eMsg = report.getMessage()
					+ "Hunt Group Name is not displayed in the Hunt Group Section of  Order Summary Page"
					+ Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public boolean verifyHuntRemove(String HGName, String TN) {
		mstatus = true;
		try {
			if (testLocatorClickable(Locators.btnDownloadPDF, 15) != null) {
				iValidateLocatorValue(Locators.txtHGrnName, HGName, ComparisonType.EQUAL,
						"Verify the Hunt Group Name for Removal displayed in the Hunt Group Section of Order Summary Page");
				iValidateLocatorValue(Locators.txtHGphoneNo, TN, ComparisonType.EQUAL,
						"Verify if the Phone Number associated with the Hunt Group identified for Removal is displayed in the Hunt Group Section of Order Summary Page");

			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verify the section Hunt Group Removal in Order Summary Page",
					"Hunt Group Name and Phone Number for Removal is not displayed in the Hunt Group Section of Order Summary Page"
							+ Ex.getMessage());
			eMsg = report.getMessage()
					+ "Hunt Group Name and Phone Number for Removal is not displayed in the Hunt Group Section of  Order Summary Page"
					+ Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public boolean verifyDeviceTypes(String[] OrderSummaryDeviceTypes) {
		mstatus = true;
		try {
			for (int i = 0; i < OrderSummaryDeviceTypes.length; i++)
				if (testLocatorClickable(Locators.btnDownloadPDF, 15) != null) {
					addLocator(Locators.txtDeviceType, "xpath",
							"//div[.='Phone Model']//following::div[.='" + OrderSummaryDeviceTypes[i] + "']",
							"Verify if all the New Polycom Devices are listed");
					iValidateLocatorValue(Locators.txtDeviceType, OrderSummaryDeviceTypes[i], ComparisonType.EQUAL,
							"Verify the Device Type:" + OrderSummaryDeviceTypes[i]
									+ " displayed in the Detailed Order Summary Page");
				}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verify the Device Type displayed in the Detailed Order Summary Page",
					"Device Types are not displayed in the Detailed Order Summary Page" + Ex.getMessage());
			eMsg = report.getMessage() + "Device Types are not displayed in the Detailed Order Summary Page"
					+ Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public boolean verifyCallQueueAVIOS(String CQName, String extension, String notes, String callIDLN, String callIDFN,
			String timeZone, String groupPolicy, String queryLength, String escDigits, String agents, String transferTo,
			String overflowCalls) {
		mstatus = true;
		try {
			if (testLocatorClickable(Locators.btnDownloadPDF, 15) != null) {

				iValidateLocatorValue(Locators.txtCQTNAVI, "Unassigned", ComparisonType.EQUAL,
						"Verify the CallQueue Name displayed in the AVI Detailed Order Summary Order Summary Page");
				iValidateLocatorValue(Locators.txtCallQueueNameAVI, CQName, ComparisonType.SUBSTR,
						"Verify the CallQueue Name displayed in the AVI Detailed Order Summary Order Summary Page");
				iValidateLocatorValue(Locators.txtCallQueueExt, extension, ComparisonType.EQUAL,
						"Verify the CallQueue Extention displayed in the AVI Detailed Order Summary Order Summary Page");
				iValidateLocatorValue(Locators.txtCallQueueNotes, notes, ComparisonType.EQUAL,
						"Verify the CallQueue Notes displayed in the AVI Detailed Order Summart Order Summary Page");
				iValidateLocatorValue(Locators.txtCallIDLN, callIDLN, ComparisonType.SUBSTR,
						"Verify the CallQueue Last Name displayed in the AVI Detailed Order Summary Order Summary Page");
				iValidateLocatorValue(Locators.txtCallIDFN, callIDFN, ComparisonType.SUBSTR,
						"Verify the CallQueue FirstName displayed in the AVI Detailed Order Summary Order Summary Page");

				iValidateLocatorValue(Locators.txtCQTimeZone, timeZone, ComparisonType.EQUAL,
						"Verify the CallQueue TimeZone displayed in the AVI Detailed Order Summary Order Summary Page");

				iValidateLocatorValue(Locators.txtCQGrpouPolicy, groupPolicy, ComparisonType.EQUAL,
						"Verify the CallQueue Group Policy displayed in the AVI Detailed Order Summary Order Summary Page");

				iValidateLocatorValue(Locators.txtCQQueueLength, queryLength, ComparisonType.EQUAL,
						"Verify the CallQueue Queue Length displayed in the AVI Detailed Order Summary Order Summary Page");

				iValidateLocatorValue(Locators.txtCQAllowEscapefromQueue, "On", ComparisonType.EQUAL,
						"Verify if the field parameters for Allow Escape from Call Queue Section in the AVI Detailed Order Summary Order Summary Page");

				iValidateLocatorValue(Locators.txtCQEscDigit, escDigits, ComparisonType.EQUAL,
						"Verify if the  Escape Digit Value from Call Queue Section in the AVI Detailed Order Summary Order Summary Page");

				iValidateLocatorValue(Locators.txtCQAgents, agents, ComparisonType.SUBSTR,
						"Verify if the  Agents from Call QueueQueue in the AVI Detailed Order Summary Order Summary Page");
				iValidateLocatorValue(Locators.txtOverflowAction, "Overflow to Escape Destination Phone Number",
						ComparisonType.EQUAL,
						"Verify if the text Value Overflow Action Field from Call QueueQueue Section in the AVI Detailed Order Summary Order Summary Page");

				iValidateLocatorValue(Locators.txtTransferTo, transferTo, ComparisonType.EQUAL,
						"Verify the Transfer To Field from Call QueueQueue Section in the AVI Detailed Order Summary Order Summary Page");
				iValidateLocatorValue(Locators.txtOverFlowCallAfter, overflowCalls, ComparisonType.EQUAL,
						"Verify the OverflowCalls from Call QueueQueue Section in the AVI Detailed Order Summary Order Summary Page");
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verify the Field parameters for Call Queue Section in AVI Order Summary Page",
					"The Field parameters for Call Queue Section in AVI Order Summary Page is not displayed"
							+ Ex.getMessage());
			eMsg = report.getMessage()
					+ "The Field parameters for Call Queue Section in AVI Order Summary Page is not displayed"
					+ Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public boolean verifyHuntGroupAVIOrderSummary(String[] HuntGroups) {
		mstatus = true;
		Boolean found;
		try {

			if (testLocatorClickable(Locators.btnDownloadPDF, 15) != null) {
				addLocator(Locators.txtHGAviOrderSummary, "xpath",
						"//h2[.='Call Flow']//following::h3[.='Hunt Group']/../div[1]/table/tbody/tr[2]/td[1]/div",
						"Verify if the Hunt Groups are Displayed in the Call Flow-AVI Order Summary");
				List<WebElement> HuntGroupDisplayed = getLocatorWEList(Locators.txtHGAviOrderSummary);
				int j = 0;
				for (int i = 0; i < HuntGroups.length; i++) {
					found = false;
					for (WebElement elem : HuntGroupDisplayed) {
						j++;
						String HG = elem.getText();
						if (HG.contains(HuntGroups[i])) {
							found = true;
							report.reportPassEvent("Verification of Hunt Group Field: " + HuntGroups[i],
									"Verification of the Hunt Group Field is successful");
							break;
						}
						if ((j == HuntGroupDisplayed.size()) && !found) {
							report.reportSoftFailEvent("Hunt Group verification in AVI OS page",
									"Hunt Group: " + HuntGroups[i] + " is not displayed as expected");
						}
					}

				}
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verify if the Hunt Groups are Displayed in the Call Flow-AVI Order Summary",
					"Hunt Groups are not displayed in the Call queue Section of the AVI Order Summary Page"
							+ Ex.getMessage());
			eMsg = report.getMessage()
					+ "Hunt Groups are not displayed in the Call queue Section of the AVI Order Summary Page"
					+ Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public boolean verifyAutoAttendentAVIOrderSummary(String[] AutoAttendents) {
		mstatus = true;
		Boolean found;
		try {
			if (testLocatorClickable(Locators.btnDownloadPDF, 15) != null) {

				addLocator(Locators.txtAAAviOrderSummary, "xpath",
						"//h2[.='Call Flow']//following::h3[.='Auto Attendant']//following::tr[2]/td[1]/div",
						"Verify if the Auto Attendents are Displayed in the Call Flow-AVI Order Summary");
				List<WebElement> elements = getLocatorWEList(Locators.txtAAAviOrderSummary);
				for (int i = 0; i < AutoAttendents.length; i++) {
					found = false;
					int j = 0;
					for (WebElement elem : elements) {
						j++;
						String AA = elem.getText();
						if (AA.contains(AutoAttendents[i])) {
							found = true;
							report.reportPassEvent("Verification of Auto Attendent Field: " + AutoAttendents[i],
									"Verification of the Auto-Attendent field is successful");
							break;
						}
						if ((j == elements.size()) && !found) {
							report.reportSoftFailEvent("AA verification in AVI OS page",
									"Auto Attendents" + AutoAttendents[i] + " is not displayed as expected");
						}
					}

				}
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verify if the Auto Attendents are Displayed in the Call Flow-AVI Order Summary",
					"Auto Attendents are not displayed in the Call queue Section of the AVI Order Summary Page"
							+ Ex.getMessage());
			eMsg = report.getMessage()
					+ "Auto Attendents are not displayed in the Call queue Section of the AVI Order Summary Page"
					+ Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public boolean verifySCAInOrderSummary(String seatName, String deviceType, String numberOfSideCars, String line,
			String user, String Type) {
		mstatus = true;
		try {
			if (testLocatorClickable(Locators.btnDownloadPDF, 15) != null) {
				iValidateLocatorValue(Locators.txtSCASeatName, seatName, ComparisonType.SUBSTR,
						"Verify the Seat Name for Shared Call Appearance displayed in the Detailed  Order Summary Page");
				iValidateLocatorValue(Locators.txtSCADeviceType, deviceType, ComparisonType.EQUAL,
						"Verify the Device Type for Shared Call Appearance displayed in the Detailed  Order Summary Page");
				iValidateLocatorValue(Locators.txSideCars, numberOfSideCars, ComparisonType.EQUAL,
						"Verify the number Of SideCars for Shared Call Appearance displayed in the Detailed  Order Summary Page");
				iValidateLocatorValue(Locators.txtLine, line, ComparisonType.EQUAL,
						"Verify the number Of Lines for Shared Call Appearance displayed in the Detailed  Order Summary Page");
				iValidateLocatorValue(Locators.txtUser, user, ComparisonType.SUBSTR,
						"Verify the user for Shared Call Appearance displayed in the Detailed  Order Summary Page");
				iValidateLocatorValue(Locators.txtType, Type, ComparisonType.EQUAL,
						"Verify the Type for Shared Call Appearance displayed in the Detailed  Order Summary Page");
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent(
					"Verify the Field Parameters for Shared Call Appearance in Detailed Order Summary Page",
					"Key Field Parameters are not displayed in the Shared Call Appearance Section of Detailed Order Summary Page"
							+ Ex.getMessage());
			eMsg = report.getMessage()
					+ "Key Field Parameters are not displayed in the Shared Call Appearance Section of Detailed Order Summary Page"
					+ Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public boolean verifySCABLFInAVIOrderSummary(String seatName, String scaName, String type) {
		mstatus = true;
		try {
			if (testLocatorClickable(Locators.btnDownloadPDF, 15) != null) {
				iValidateLocatorValue(Locators.txtSCBLFName, seatName, ComparisonType.SUBSTR,
						"Verify the Seat Name for BLF in the AVI Detailed  Order Summary Page");
				iValidateLocatorValue(Locators.txtBLFWho, scaName, ComparisonType.SUBSTR,
						"Verify the Seat Name selected for  Shared Call Appearance displayed in the AVI Order Summary Page");
				iValidateLocatorValue(Locators.txtBLFType, type, ComparisonType.EQUAL,
						"Verify the Type configured for Shared Call Appearance  in the AVI Order Summary Page");
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verify the Field Parameters for BLF/SCA in AVI Order Summary Page",
					"Key Field Parameters are not displayed in the BLF/SCA Section of AVI Order Summary Page"
							+ Ex.getMessage());
			eMsg = report.getMessage()
					+ "Key Field Parameters are not displayed in the BLF/SCA Section of AVI Order Summary Page"
					+ Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public boolean verifyCQInUserOrderSummary(String cqUserConfiguredinSimon, String cqIndicator) {
		mstatus = true;
		try {
			if (testLocatorClickable(Locators.btnDownloadPDF, 15) != null) {

				addLocator(Locators.txtcqUserConfiguredinSimon, "xpath",
						"//div[.='User/Service']//following::div[contains(.,'" + cqUserConfiguredinSimon + "')]",
						"Add locator for the CQ user Configured");
				iValidateLocatorValue(Locators.txtcqUserConfiguredinSimon, cqUserConfiguredinSimon,
						ComparisonType.SUBSTR,
						"Verify the Seat Name displayed for  Call Quality displayed in the user Service  Summary Page");
				addLocator(
						Locators.txtcqIndicator, "xpath", "//div[.='User/Service']//following::div[contains(.,'"
								+ cqUserConfiguredinSimon + "')]//following::div[.='" + cqIndicator + "'][1]",
						"Verify the CQ Indicator");
				iValidateLocatorValue(Locators.txtcqIndicator, cqIndicator, ComparisonType.EQUAL,
						"Verify the Call Queue Indicator in the User Service Summary Page");
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verify the Field Parameters for Call Queue in user Service  Summary Page",
					"Key Field Parameters are not displayed in the Call Queue Section of user Service  Summary Page"
							+ Ex.getMessage());
			eMsg = report.getMessage()
					+ "Key Field Parameters are not displayed in the Call Queue Section of user Service  Summary Page"
					+ Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public boolean verifyHGInUserOrderSummary(String hgNameCongigured, String hgIndicator) {
		mstatus = true;
		try {
			if (testLocatorClickable(Locators.btnDownloadPDF, 15) != null) {

				addLocator(Locators.txtHGUsersConfigured, "xpath",
						"//div[.='User/Service']//following::div[contains(.,'" + hgNameCongigured + "')]",
						"Add locator for the HG user Configured");
				iValidateLocatorValue(Locators.txtHGUsersConfigured, hgNameCongigured, ComparisonType.SUBSTR,
						"Verify the Seat Name displayed for  Hunt Group Configured in the user Service  Summary Page");

				addLocator(Locators.txtHGIndicator, "xpath",
						"//div[.='Indicators']//following::div[.='" + hgIndicator + "']",
						"Verify the HG Indicator Displayed");
				iValidateLocatorValue(Locators.txtHGIndicator, hgIndicator, ComparisonType.EQUAL,
						"Verify the HG Indicator in the User Summary Page");
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verify the Field Parameters for Hunt Group in user Service  Summary Page",
					"Key Field Parameters for Hunt Group configured are not displayed in the user Service  Summary Page"
							+ Ex.getMessage());
			eMsg = report.getMessage()
					+ "Key Field Parameters for Hunt Group configured are not displayed in the user Service  Summary Page"
					+ Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public boolean verifyLNPOrderSummaryFieldValues() {
		mstatus = true;
		try {
			if (testLocatorClickable(Locators.btnDownloadPDF, 15) != null) {

				iValidateLocatorValue(Locators.txtCustDetailsGroupName, dataDump.getValue("CustomerName"),
						ComparisonType.EQUAL, "Verify the Customer Details Group Name  in the User Summary Page");

				iValidateLocatorValue(Locators.txtAutoTestSDPMName, dataSet.getValue("CustomerName"),
						ComparisonType.EQUAL, "Verify the Auto Test SDPMN Name  in the User Summary Page");

				iValidateLocatorValue(Locators.txtgrpIDCustDetals, dataDump.getValue("GroupID"), ComparisonType.EQUAL,
						"Verify the Auto Test Customer Details Group ID  in the User Summary Page");
				iValidateLocatorValue(Locators.txtLeadID, dataDump.getValue("LeadID"), ComparisonType.EQUAL,
						"Verify the Auto Test Lead ID  in the User Summary Page");
				iValidateLocatorValue(Locators.txtEnterpiseID, dataDump.getValue("ParentAccountID_RT"),
						ComparisonType.EQUAL,
						"Verify the Single View Parent ID/EnterpriseID  in the User Summary Page");
				iValidateLocatorValue(Locators.txtESGLicenseQTY, dataSet.getValue("ESGLicenseQuantity"),
						ComparisonType.EQUAL, "Verify the ESG License Quantity in the User Summary Page");
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verify the Field Parameters in LNP Order Summary Page",
					"Key Field Parameters for LNP Order Summary Page" + Ex.getMessage());
			eMsg = report.getMessage()
					+ "Key Field Parameters for LNP Order Summary Page not displayed in the user Service  Summary Page"
					+ Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public boolean verifyDirectoryListingFieldValues(String SeatDirectoryName, String directoryListingNo,
			String omitAddress) {
		mstatus = true;
		try {
			if (testLocatorClickable(Locators.btnDownloadPDF, 15) != null) {

				iValidateLocatorValue(Locators.txtCustDetailsGroupName, dataDump.getValue("CustomerName"),
						ComparisonType.EQUAL,
						"Verify the Customer Details Group Name  in Directory Listing Order Summary");

				iValidateLocatorValue(Locators.txtAutoTestSDPMName, dataSet.getValue("CustomerName"),
						ComparisonType.EQUAL, "Verify the Auto Test SDPMN Name  in the User Summary Page");

				iValidateLocatorValue(Locators.txtgrpIDCustDetals, dataDump.getValue("GroupID"), ComparisonType.EQUAL,
						"Verify the Auto Test Customer Details Group ID  in the User Summary Page");
				iValidateLocatorValue(Locators.txtLeadID, dataDump.getValue("LeadID"), ComparisonType.EQUAL,
						"Verify the Auto Test Lead ID in Directory Listing Order Summary");
				iValidateLocatorValue(Locators.txtEnterpiseID, dataDump.getValue("ParentAccountID_RT"),
						ComparisonType.EQUAL,
						"Verify the Single View Parent ID/EnterpriseID  in the User Summary Page");
				iValidateLocatorValue(Locators.txtESGLicenseQTY, dataSet.getValue("ESGLicenseQuantity"),
						ComparisonType.EQUAL, "Verify the ESG License Quantity in Directory Listing Order Summary");
				iValidateLocatorValue(Locators.txtDirectoryDisplayName, dataSet.getValue("DirectoryDisplayName"),
						ComparisonType.EQUAL, "Verify the ESG License Quantity in Directory Listing Order Summary");
				iClick(Locators.btnEXPDirectoryName);
				addLocator(Locators.txtSeatDirectoryName, "Xpath",
						"//div[.='Display Name']//following::div[contains(.,'" + SeatDirectoryName + "')]",
						"Adding Locators for Seat name-Directory");
				iValidateLocatorValue(Locators.txtSeatDirectoryName, SeatDirectoryName, ComparisonType.SUBSTR,
						"Verify the Display Name feild and its populated data in Directory Listing Order Summary");
				iValidateLocatorValue(Locators.txtCallerIdName, dataSet.getValue("CustomerName"), ComparisonType.EQUAL,
						"Verify the Caller ID field in Directory Listing Order Summary");
				iValidateLocatorValue(Locators.txtPlaceListingAs, dataSet.getValue("PlaceListingAs"),
						ComparisonType.EQUAL, "Verify the Place Listing As field in Directory Listing Order Summary");
				iValidateLocatorValue(Locators.txtHeaderText, dataSet.getValue("HeaderText"), ComparisonType.EQUAL,
						"Verify the Header Text field in Directory Listing Order Summary");
				iValidateLocatorValue(Locators.txtHeaderCode, dataSet.getValue("HeaderCode"), ComparisonType.EQUAL,
						"Verify the Header Text field in Directory Listing Order Summary");
				iValidateLocatorValue(Locators.txtSICCode, dataSet.getValue("SICCode"), ComparisonType.EQUAL,
						"Verify the SIC Code field in Directory Listing Order Summary");
				iValidateLocatorValue(Locators.txtDirectoryListingNum, directoryListingNo, ComparisonType.EQUAL,
						"Verify the Directory Listing Address field in Directory Listing Order Summary");

				iValidateLocatorValue(Locators.txtOmitAddress, omitAddress, ComparisonType.EQUAL,
						"Verify the Omit Address field in Directory Listing Order Summary");

			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verify the Field Parameters in Directory Listing Order Summary Page",
					"Key Field Parameters for Directory Listing Order Summary Page" + Ex.getMessage());
			eMsg = report.getMessage() + "Key Field Parameters for Directory Listing Order Summary Page not displayed"
					+ Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public boolean verifyEquipmentSummaryInAVIOS(String[] equipmentName, String[] equipmentAmount) {
		mstatus = true;
		boolean status = false;
		try {
			if (testLocatorClickable(Locators.btnDownloadPDF, 15) != null) {
				List<WebElement> equipmentList = getLocatorWEList(Locators.txtEquipmentType);
				List<WebElement> equipmentAmounts = getLocatorWEList(Locators.txtEquipmentAmount);
				for (int idx = 0; idx < equipmentName.length; idx++) {
					status = false;
					for (int i = 0; i < equipmentList.size(); i++) {
						if (equipmentList.get(i).getText().equals(equipmentName[idx])
								&& equipmentAmounts.get(i).getText().equals(equipmentAmount[idx])) {
							status = true;
							break;
						}
					}
					iReport("", "Equipment Type and Amount verification in AVI Order Summary Page",
							status ? equipmentName[idx] + " is displayed with amount: " + equipmentAmount[idx]
									+ " as expected"
									: equipmentName[idx] + " is NOT displayed with amount: " + equipmentAmount[idx]
											+ " as expected",
							status);
				}
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verify the Field Parameters for Equipment Summary in AVI Order  Summary Page",
					"Key Field Parameters for Equipment Summary in the AVI Order  Summary Page" + Ex.getMessage());
			eMsg = report.getMessage() + "Key Field Parameters for Equipment Summary in the AVI Order  Summary Page"
					+ Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public boolean verifyUserServiceInOS(String[] userService, String[] userType) {
		mstatus = true;
		boolean status = false;
		try {
			if (testLocatorClickable(Locators.btnDownloadPDF, 15) != null) {
				List<WebElement> User_Service = getLocatorWEList(Locators.txtUserAAName);
				List<WebElement> Type = getLocatorWEList(Locators.AAType);
				status = false;
				for (int idx = 0; idx < userService.length; idx++) {
					for (int i = 0; i < User_Service.size(); i++) {
						if (User_Service.get(i).getText().contains(userService[idx])
								&& Type.get(i).getText().equals(userType[idx])) {
							status = true;
							break;
						}
					}
					iReport("", "Verification of Auto Attendent Fields in User Summary Page", status
							? userService[idx] + " is displayed with Type: " + userType[idx] + " as expected"
							: userService[idx] + " is NOT displayed with Type: " + userType[idx] + " as expected",
							status);
				}
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verify the Field Parameters for User/Service in User Service Summary Page",
					"Key Field Parameters for User/Serviceconfigured are not displayed in the user Service Summary Page"
							+ Ex.getMessage());
			eMsg = report.getMessage()
					+ "Key Field Parameters for User/Service configured is not displayed in the User Service Summary Page"
					+ Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public boolean verifyTNInventoryDetails(String[] Purpose, String[] Ext, String[] InvName, String[] InvTN) {
		mstatus = true;
		Boolean status, condition;
		condition = true;
		try {
			if (testLocatorClickable(Locators.btnDownloadPDF, 15) != null) {
				List<WebElement> purpose = getLocatorWEList(Locators.txtTNPurpose);
				List<WebElement> extention = getLocatorWEList(Locators.txtTNEXT);
				List<WebElement> TNInvName = getLocatorWEList(Locators.txtTNInvName);
				List<WebElement> TnInvTN = getLocatorWEList(Locators.txtTNInvTN);
				status = false;
				for (int idx = 0; idx < Purpose.length; idx++) {
					condition = true;
					for (int i = 0; i < purpose.size(); i++) {
						if (InvName.length > 1 && InvTN.length > 1) {
							condition = (TNInvName.get(i).getText().contains(InvName[idx])
									&& TnInvTN.get(i).getText().contains(InvTN[idx]));
						}
						if (purpose.get(i).getText().contains(Purpose[idx])
								&& extention.get(i).getText().matches(Ext[idx] + "|") && condition) {
							status = true;
							break;
						}
					}
					iReport("", "Verification of TN Number Inventory Details in Detailed Order Summary",
							status ? Purpose[idx] + " is displayed with Type: " + Ext[idx] + " as expected"
									: Purpose[idx] + " is NOT displayed with Type: " + Ext[idx] + " as expected",
							status);
				}

			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verification of TN Number Inventory Details in Detailed Order Summary",
					"Verification of TN Number Inventory Details in Detailed Order Summary" + Ex.getMessage());
			eMsg = report.getMessage() + "Verification of TN Number Inventory Details in Detailed Order Summary"
					+ Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public boolean verifySeatDetailsinDetailedOrderSummary(String[] seat, String[] purpose, String[] name,
			String[] phoneModel) {
		mstatus = true;
		boolean status = false;
		try {
			if (testLocatorClickable(Locators.btnDownloadPDF, 15) != null) {
				List<WebElement> Seat = getLocatorWEList(Locators.txtSeatDetails);
				List<WebElement> Purpose = getLocatorWEList(Locators.txtSeatDetailsPurpose);
				List<WebElement> Name = getLocatorWEList(Locators.txtSeatDetailsName);
				List<WebElement> PHModel = getLocatorWEList(Locators.txtSeatDetailsPHModel);
				for (int idx = 0; idx < seat.length; idx++) {
					status = false;
					for (int i = 0; i < Seat.size(); i++) {
						if (Seat.get(i).getText().contains(seat[idx]) && Purpose.get(i).getText().equals(purpose[idx])
								&& Name.get(i).getText().contains(name[idx])
								&& PHModel.get(i).getText().equals(phoneModel[idx])) {
							status = true;
							break;
						}
					}
					iReport("", "Verification of Seat Details Fields in Detailed Order Summary Page",
							status ? seat[idx] + " is displayed with purpose: " + purpose[idx] + "and Name :"
									+ name[idx] + "and PhoneModel" + phoneModel[idx]
									: seat[idx] + " is not displayed with purpose: " + purpose[idx] + "and Name :"
											+ name[idx] + "and PhoneModel" + phoneModel[idx],
							status);
				}
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verification of Seat Details Fields in Detailed Order Summary Page",
					"The Seat Details Fields in Detailed Order Summary Page are not displayed as expected"
							+ Ex.getMessage());
			eMsg = report.getMessage()
					+ "The Seat Details Fields in Detailed Order Summary Page are not displayed as expected"
					+ Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public boolean verifyTollFreeNumbersOrderSummaryFieldValues(String ILD) {
		mstatus = true;
		try {
			if (testLocatorClickable(Locators.btnDownloadPDF, 15) != null) {

				iValidateLocatorValue(Locators.txtCustDetailsGroupName, dataDump.getValue("CustomerName"),
						ComparisonType.EQUAL,
						"Verify the Customer Details Group Name in the Toll Free Numbers OS Page");

				iValidateLocatorValue(Locators.txtgrpIDTollFree, dataDump.getValue("GroupID"), ComparisonType.EQUAL,
						"Verify the Auto Test Customer Details Group ID in the Toll Free Numbers OS Page");

				iValidateLocatorValue(Locators.txtEnterpiseID, dataDump.getValue("ParentAccountID_RT"),
						ComparisonType.EQUAL,
						"Verify the Single View Parent ID/EnterpriseID in the Toll Free Numbers OS Page");

				iValidateLocatorValue(Locators.txtAutoTestSDPMName, dataSet.getValue("CustomerName"),
						ComparisonType.EQUAL, "Verify the Auto Test SDPMN Name in the Toll Free Numbers OS Page");

				iValidateLocatorValue(Locators.txtLeadID, dataDump.getValue("LeadID"), ComparisonType.EQUAL,
						"Verify the Auto Test Lead ID  in the Toll Free Numbers OS Page");

				iValidateLocatorValue(Locators.txtESGLicenseQTY, dataSet.getValue("ESGLicenseQuantity"),
						ComparisonType.EQUAL, "Verify the ESG License Quantity in the Toll Free Numbers OS Page");

				iValidateLocatorValue(Locators.txtILD, ILD, ComparisonType.EQUAL,
						"Verify the Value displayed for International Long Distance in the Toll Free Numbers OS Page");
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verify the Field Parameters for the Toll Free Numbers OS Page",
					"Key Field Parameters for the Toll Free Numbers OS Page" + Ex.getMessage());
			eMsg = report.getMessage()
					+ "Key Field Parameters for the Toll Free Numbers OS Page is not displayed in the user Service Summary Page"
					+ Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public boolean verifySiteNameIsDisplayedInAllOSPage(String[] OSType) {
		mstatus = true;
		try {
			for (String osType : OSType) {
				this.browser = context.getDriver();
				launchOrderSumaryType(osType);
				if (isLocatorVisible(Locators.txtSiteDetailsGroupName, 60)) {
					iValidateLocatorValue(Locators.txtSiteDetailsGroupName, dataDump.getValue("SITE_NAME"),
							ComparisonType.EQUAL,
							"Verify the Site Name is displayed in '" + osType + "' OS Page with Read Only User");
				}
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verify the Site Name is displayed in OS Page with Read Only User",
					"Verify the Site Name is displayed in OS Page with Read Only User" + Ex.getMessage());
			eMsg = report.getMessage() + "Verify the Site Name is displayed in OS Page with Read Only User"
					+ Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public boolean verifyScopeDialingDetails() {
		mstatus = true;
		try {			
			if (testLocatorClickable(Locators.btnDownloadPDF, 15) != null) {
				int size = getLocatorWEList(Locators.txtScopeExtnDialing).size();
				for (int i = 0; i < size; i++) {
					boolean res = (getLocatorWEList(Locators.txtScopeExtnDialing).get(i).getAttribute("outerHTML").contains("Enterprise")
							&& getLocatorWEList(Locators.txtScopeNameDialing).get(i).getAttribute("outerHTML").contains("Enterprise")
							&& getLocatorWEList(Locators.txtNameDialingEntries).get(i).getAttribute("outerHTML")
									.contains("LastName + FirstName"));
					iReport("", "Verification of Scope Dialing Extn/Name/NameDialing Entries for AA-" + (i + 1) + " in OS Page",
							res ? "Verification of Scope Dialing Extn/Name/NameDialing Entries for AA-" + (i + 1)
									+ " in OS Page is Passed"
									: "Verification of Scope Dialing Extn/Name/NameDialing Entries for AA-" + (i + 1)
											+ " in OS Page is Failed",
							res);
				}
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent(
					"Verification of Scope Dialing Extn/Name/NameDialing Entries for AA Order Summary Page",
					"Verification of Scope Dialing Extn/Name/NameDialing Entries for AA Order Summary Page Failed"
							+ Ex.getMessage());
			eMsg = report.getMessage()
					+ "Verification of Scope Dialing Extn/Name/NameDialing Entries for AA Order Summary Page Failed"
					+ Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}
}
