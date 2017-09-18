package com.comcast.pages.simon;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import com.comcast.commons.WebtopPage;
import com.comcast.pages.simon.ServiceInformation.Locators;
import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.utils.ComparisonType;
import com.comcast.utils.DataTable;
import com.comcast.utils.ValidationType;

/**
 * Represents default page of the application
 * 
 */
public class SeatConfiguration extends WebtopPage {
	FrameworkContext context;
	DataTable dataSet;
	Logger log = Logger.getLogger(SeatConfiguration.class);

	private boolean mstatus;

	public static class Locators {
		public static final String navAllSeats = "navAllSeats";
		public static final String btnEquipmentFeatures = "btnEquipmentFeatures";
		public static final String btnBLFToggle = "btnBLFToggle";
		public static final String btnAddSeat = "btnAddSeat";
		public static final String dropDownSeatType = "dropDownSeatType";
		public static final String dropDownSeatPurpose = "dropDownSeatPurpose";
		public static final String txtFirstName = "txtFirstName";
		public static final String txtLastName = "txtLastName";
		public static final String txtExtension = "txtExtension";
		public static final String txtEmail = "txtEmail";
		public static final String dropDownEquipmentType = "dropDownEquipmentType";
		public static final String lnkConfigureSeat = "lnkConfigureSeat";
		public static final String btnSave = "btnSave";
		public static final String elmtSavesuccessful = "elmtSavesuccessful";
		public static final String btnMkSeatReceptionist = "btnMkSeatReceptionist";
		public static String lnkSeatName = "";
		public static final String spinner = "spinner";
		public static final String txtEquipmentType = "txtEquipmentType";
		public static final String btnmkSeatAvailablefrAgent = "btnmkSeatAvailablefrAgent";
		public static final String drpSeatPurpose = "drpSeatPurpose";
		public static final String btnSeatDetails = "btnSeatDetails";
		public static String txtSeatPurposeBeforeUpdate = "";
		public static String drpChooseeqpType = "";
		public static String drpSideCars = "";
		public static String imgValidation = "";
	}

	public SeatConfiguration(FrameworkContext context) {
		super(context, "SeatConfiguration");
		report = context.getReport();
		this.context = context;
		this.dataSet = context.getDataTable();
		context.getUserDetail();
		context.getSettings();
	}

	@Override
	protected boolean isValidPage() {
		return true;
	}

	@Override
	protected void waitForPageLoad() {

	}

	public boolean Signout() {
		return mstatus;
	}

	public boolean enableBLFFeature() {
		mstatus = true;
		String[] SeatNameToEnableBLF = dataSet.getValue("SeatNameToEnableBLF").split("\\|");
		try {
			if (waitForElement(testLocatorClickable(Locators.navAllSeats), 20)) {
				iClick(Locators.navAllSeats, null, "Click on All Seats");
			}
			sleep(4000);
			for (int idx = 0; idx < SeatNameToEnableBLF.length; idx++) {
				By by = By.xpath("//div[contains(@title,'" + SeatNameToEnableBLF[idx] + "')]");
				browser.findElement(by).click();
				waitForSpinnerComplete();
				iClick(Locators.btnEquipmentFeatures, null, "Click on Equipment Features");
				Scroll_Page_Down();
				if (isLocatorVisible(Locators.btnBLFToggle, 10)) {
					iClick(Locators.btnBLFToggle, null, "Enable BLF Feature");
				}
				waitForSpinnerComplete();
				sleep(2000);
				report.reportPassEvent("Enable BLF Feature for UC Seat" + SeatNameToEnableBLF[idx],
						"Enabled BLF Feature");
				if (isLocatorVisible(Locators.btnMkSeatReceptionist, 20)) {
					Scroll_Page_Down();
					iClick(Locators.btnMkSeatReceptionist, null, "Make the seat Selected as Receptionist");

					report.reportPassEvent("Make the seat Selected as Receptionist",
							"Make the seat Selected as Receptionist-Successful");
					waitForSpinnerComplete();
					int size = getLocatorWEList(Locators.btnSave).size();
					log.info("Save Button count is: " + size);
					if (size > 1) {
						iClick(getLocatorWEList(Locators.btnSave).get(size - 1));
					} else {
						iClick(Locators.btnSave);
					}
					waitForSpinnerComplete();
				}
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Enable BLF Feature for UC Seat",
					"Enable BLF Feature and Shared Call App for UC Seat Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Enable BLF Feature and Shared Call App for UC Seat in SIMON Failed!!! "
					+ Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public boolean addSeat(String seatType, String seatPurpose, String firstName, String lastName, String equipType) {
		mstatus = true;
		try {
			if (!isLocatorVisible(Locators.btnAddSeat, 5)) {
				iClick(Locators.navAllSeats, null, "Click on All Seats");
			}
			sleep(4000);
			scrollUp();
			testLocatorClickable(Locators.btnAddSeat);
			iClick(Locators.btnAddSeat);
			iClick(Locators.lnkConfigureSeat);
			iClick(Locators.dropDownSeatType);
			selectvalue_dropdown("div", seatType, false);
			iClick(Locators.dropDownSeatPurpose);
			selectvalue_dropdown("div", seatPurpose, false);
			iEnterText(Locators.txtFirstName, firstName);
			iEnterText(Locators.txtLastName, lastName + "_" + getTimestamp());
			int extnDigits = Integer.parseInt(dataSet.getValue("ExtensionDialingDigits"));
			iEnterText(Locators.txtExtension, randomNumber(extnDigits));
			iEnterText(getLocatorWEList(Locators.txtEmail).get(0), lastName + "_" + getTimestamp() + "@comcast.com");
			iClick(Locators.btnEquipmentFeatures, null, "Click on Equipment Features");
			waitforPageLoadComplete();
			iClick(Locators.dropDownEquipmentType);
			selectvalue_dropdown("div", equipType, false);
			iClick(Locators.btnSave);
			testLocatorClickable(Locators.elmtSavesuccessful);
			sleep(1000);
			report.reportPassEvent("Add Seat: " + seatType, "Add seat completed successfully");
			log.info("");
		} catch (Exception Ex) {
			report.reportHardFailEvent("Adding Seat: " + seatType, "Adding seat Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Adding Seat: " + seatType + " in SIMON Failed!!! " + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public boolean verifyDeviceTypeSelected(String[] deviceType, String SeatsToVerifyDevices) {
		mstatus = true;
		String[] seatsToVerifyDevices = SeatsToVerifyDevices.split("\\|");
		try {
			if (waitForElement(testLocatorClickable(Locators.navAllSeats), 20)) {
				iClick(Locators.navAllSeats, null, "Click on All Seats");
			}
			sleep(4000);
			for (int idx = 0; idx < seatsToVerifyDevices.length; idx++) {
				By by = By.xpath("//div[contains(@title,'" + seatsToVerifyDevices[idx] + "')]");
				browser.findElement(by).click();
				waitForSpinnerComplete();
				iClick(Locators.btnEquipmentFeatures, null, "Click on Equipment Features");
				if (isLocatorVisible(Locators.txtEquipmentType, 10)) {
					iValidateLocatorValue(Locators.txtEquipmentType, deviceType[idx], ComparisonType.EQUAL,
							"Verification of Device Name: " + deviceType[idx] + " in All seats successful");
				}
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verification of Device Type in All Seats-Equipment Section",
					"Verification of Device Type in All Seats-Equipment Section Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Verification of Device Type in All Seats-Equipment Section Failed!!! "
					+ Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public boolean enableCallQueueFeature() {
		mstatus = true;
		String[] MakeSeatAvailableAsAgent = dataSet.getValue("MakeSeatAvailableAsAgent").split("\\|");
		try {
			if (waitForElement(testLocatorClickable(Locators.navAllSeats), 20)) {
				iClick(Locators.navAllSeats, null, "Click on All Seats");
			}
			sleep(4000);
			for (int idx = 0; idx < MakeSeatAvailableAsAgent.length; idx++) {
				By by = By.xpath("//div[contains(@title,'" + MakeSeatAvailableAsAgent[idx] + "')]");
				browser.findElement(by).click();
				waitForSpinnerComplete();
				iClick(Locators.btnEquipmentFeatures, null, "Click on Equipment Features");
				Scroll_Page_Down();
				if (isLocatorVisible(Locators.btnBLFToggle, 10)) {
					iClick(Locators.btnmkSeatAvailablefrAgent, null, "Enable make Seat Available for Agent Feature");
				}
				iClick(Locators.btnSave);
				waitForSpinnerComplete();
				sleep(2000);
				report.reportPassEvent("Enable make Seat Available for Agent Feature" + MakeSeatAvailableAsAgent[idx],
						"Enable make Seat Available for Agent Feature is Successful");
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Enable BLF Feature for UC Seat",
					"Enable BLF Feature and Shared Call App for UC Seat Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Enable BLF Feature and Shared Call App for UC Seat in SIMON Failed!!! "
					+ Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public boolean verifySeatPurposeChange(String seatPurposeChangedFrom, String seatPurposeChangedTo) {
		mstatus = true;

		try {
			if (waitForElement(testLocatorClickable(Locators.navAllSeats), 20)) {
				iClick(Locators.navAllSeats, null, "Click on All Seats");
			}
			sleep(4000);
			By by = By.xpath("//div[contains(@title,'" + dataSet.getValue("ChangeSeatPurpose") + "')]");
			browser.findElement(by).click();
			waitForSpinnerComplete();
			if (isLocatorVisible(Locators.btnSeatDetails, 10)) {
				addLocator(Locators.txtSeatPurposeBeforeUpdate, "xpath", "//div[.='" + seatPurposeChangedFrom + "']",
						"Verify the text of in the SeatPurpose");
				iValidateLocatorValue(Locators.txtSeatPurposeBeforeUpdate, seatPurposeChangedFrom, ComparisonType.EQUAL,
						"Verify if the initial Seat Type is Enterprise Admin");
				iClick(Locators.dropDownSeatPurpose);
				selectvalue_dropdown("div", seatPurposeChangedTo, true);
				iClick(Locators.btnSave);
				//waitForSpinnerComplete();
				if (isLocatorVisible(Locators.elmtSavesuccessful, 20)) {
					report.reportPassEvent("Verify if the Seat Purpose is successfully updated",
							"Seat Purpose is successfully updated to:" + seatPurposeChangedTo);
				} else
					report.reportHardFailEvent("Verify if the Seat Purpose is successfully updated",
							"Seat Purpose is not successfully updated");
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verify if the Seat Purpose is successfully updated",
					"The Seat Purpose is not updated/Saved- Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "The Seat Purpose is not updated/Saved- Failed!!! " + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public boolean VerifyDevicedisplayedinEquipmentSettings(String [] SeatName,String [] equipmentType,String[] sideCars) {
		mstatus = true;
		try {

			if (waitForElement(testLocatorClickable(Locators.navAllSeats), 20)) {
				iClick(Locators.navAllSeats, null, "Click on All Seats");
			}
			sleep(4000);
			for (int idx = 0; idx < SeatName.length; idx++) {
				By by = By.xpath("//div[contains(@title,'"+SeatName[idx]+"')]");
				browser.findElement(by).click();
				waitForSpinnerComplete();
				iClick(Locators.btnEquipmentFeatures, null, "Click on Equipment Features");
				addLocator(Locators.drpChooseeqpType, "xpath", "//span[.='Choose Equipment']/../div[@class='js-equipment-type']//following::div[.='"+equipmentType[idx]+"']", "adding locators for Equipment Type");
				iValidateLocatorValue(Locators.drpChooseeqpType, equipmentType[idx], ComparisonType.EQUAL,
						"Verify if Equipment type displayed in the Choose Equipment Section");
				addLocator(Locators.drpSideCars, "xpath", "//span[.='Number of side cars']//following::div[.='"+sideCars[idx]+"']", "adding locators for Side Cars");
				iValidateLocatorValue(Locators.drpSideCars, sideCars[idx], ComparisonType.EQUAL,
						"Verify if Side Cars displayed for the Seats Selected");
				addLocator(Locators.imgValidation, "xpath", "//img[@alt='"+equipmentType[idx]+"']", "adding locators for image Validation");					
				iValidateLocatorProperty(Locators.imgValidation, ValidationType.DISPLAYED, "Verfication of image type displayed for the the Equipment ");
				report.reportPassEvent("Equipment Type displayed for " +SeatName[idx],
						"The  Device type set in SOA for "+SeatName[idx]+" is displayed accordingly");
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Equipment Type displayed for the selected seats",
					"incorrect equipment configuration displayed for the Seats Selected- Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "incorrect equipment configuration displayed for the Seats Selected- Failed"
					+ Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}
	
	public boolean updateEmailId(String seatName) {
		mstatus = true;
		try {
			if (waitForElement(testLocatorClickable(Locators.navAllSeats), 20)) {
				iClick(Locators.navAllSeats, null, "Click on All Seats");
			}
			sleep(4000);
			By by = By.xpath("//div[contains(@title,'" + seatName + "')]");
			browser.findElement(by).click();
			waitForSpinnerComplete();
			if (isLocatorVisible(Locators.btnSeatDetails, 10)) {
				String emailId = "uniqueMailId@maildrop.cc";
				iEnterText(Locators.txtEmail, emailId);
				iClick(Locators.btnSave);
				if (isLocatorVisible(Locators.elmtSavesuccessful, 30)) {
					report.reportPassEvent("Verify if the email id is successfully updated",
							"EMail Id is successfully updated to:" + emailId);
				} else
					report.reportHardFailEvent("Verify if the email id is successfully updated",
							"EMail Id is NOT updated as expected");
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verify if the Email Id is successfully updated",
					"The EMail ID is not updated/Saved- Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "The EMail Id is not updated/Saved- Failed!!! " + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

}
