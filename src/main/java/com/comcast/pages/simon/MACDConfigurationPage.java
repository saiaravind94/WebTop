package com.comcast.pages.simon;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.server.handler.interactions.MouseMoveToLocation;

import com.comcast.commons.WebtopPage;
import com.comcast.pages.simon.DirectoryListings.Locators;
import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.utils.TestSettings;
import com.comcast.utils.ComparisonType;
import com.comcast.utils.DataTable;
import com.comcast.utils.IUserDetails;

/**
 * Represents default page of the application
 * 
 */
public class MACDConfigurationPage extends WebtopPage {
	FrameworkContext context;
	private IUserDetails userDetails;
	DataTable dataSet;
	private TestSettings settings;

	Logger log = Logger.getLogger(MACDConfigurationPage.class);

	private boolean mstatus;

	public static class Locators {
		public static final String txtMACDType = "txtMACDType";
		public static final String txtOrderDescription = "txtOrderDescription";
		public static final String inpTTSTicketID = "inpTTSTicketID";
		public static final String btnSave = "btnSave";
		public static final String btnServiceDetails = "btnServiceDetails";
		public static final String lnkClickAddButton = "lnkClickAddButton";
		public static final String drpChangeType = "drpChangeType";
		public static final String drpEntityType = "drpEntityType";
		public static final String btnAddItem = "btnAddItem";
		public static final String btnSeatType = "btnSeatType";
		public static final String btnSeatPurpose = "btnSeatPurpose";
		public static final String inpFirstName = "inpFirstName";
		public static final String inpLastName = "inpLastName";
		public static final String btnOrderSummary = "btnOrderSummary";
		public static final String inpEditFirstName = "inpEditFirstName";
		public static final String inpEditlastName = "inpEditlastName";
		public static final String inpEditTN = "inpEditTN";
		public static final String drpSeatTypeChangeAction = "drpSeatTypeChangeAction";
		public static final String drpCurrentSeatType = "drpCurrentSeatType";
		public static final String drpNewSeatType = "drpNewSeatType";
		public static final String inRMFirstName = "inRMFirstName";
		public static final String inRMlastName = "inRMlastName";
		public static final String lblMACDMessage = "lblMACDMessage";
		public static final String inpCallQueueName = "inpCallQueueName";
		public static final String drpbtnCallQueuePortType = "drpbtnCallQueuePortType";
		public static final String drpbtnCallQueueTimeZone = "drpbtnCallQueueTimeZone";
		public static final String inpRemoveCallQueueName = "inpRemoveCallQueueName";
		public static final String inpTollFreeTN = "inpTollFreeTN";
		public static final String drpAreaOfService = "drpAreaOfService";
		public static final String drpPortType = "drpPortType";
		public static final String inpTFTNEdit = "inpTFTNEdit";
		public static final String inpTFTNEditCurrentFName = "inpTFTNEditCurrentFName";
		public static final String inpTFTNEditCurrentLName = "inpTFTNEditCurrentLName";
		public static final String inpTFTNEditNewFName = "inpTFTNEditNewFName";
		public static final String inpTFTNEditNewLName = "inpTFTNEditNewLName";
		public static final String inpRemoveTFTN = "inpRemoveTFTN";
		public static final String inpRCFTn = "inpRCFTn";
		public static final String inpTranslationTN = "inpTranslationTN";
		public static final String drpRFTNPortType = "drpRFTNPortType";
		public static final String drpRFTNState = "drpRFTNState";
		public static final String inpTNEditRCF = "inpTNEditRCF";
		public static final String inpTranslationTNEditRCF = "inpTranslationTNEditRCF";
		public static final String inpCFNEditRCF = "inpCFNEditRCF";
		public static final String inpCLNEditRCF = "inpCLNEditRCF";
		public static final String inpNFNEditRCF = "inpNFNEditRCF";
		public static final String inpNLNEditRCF = "inpNLNEditRCF";
		public static final String inpRemoveRFTN = "inpRemoveRFTN";
		public static final String inpReceptionistName = "inpReceptionistName";
		public static final String inpRecpUsers = "inpRecpUsers";
		public static final String inpRecpNameRemove = "inpRecpNameRemove";
		public static final String inpRecpTNRemove = "inpRecpTNRemove";
		public static final String spinner = "spinner";	
		public static final String inpAutoAttendentName = "inpAutoAttendentName";
		public static final String inpCurrentTNAutoattendent = "inpCurrentTNAutoattendent";
		public static final String drpPortTypeAutoAttendent = "drpPortTypeAutoAttendent";
		public static final String inpTXTtimescheduleDesc = "inpTXTtimescheduleDesc";
		public static final String inpHolidayScheduleDesc = "inpHolidayScheduleDesc";
		public static final String drpAutoattTimezone = "drpAutoattTimezone";
		public static final String inpAutoAttendentRMName = "inpAutoAttendentRMName";
		public static final String inpRMAutoAtt = "inpRMAutoAtt";
		public static final String inpAddTN = "inpAddTN";
		public static final String drpPortTNType = "drpPortTNType";
		public static final String inpEditTNMACD = "inpEditTNMACD";
		public static final String inpEditTNCFN = "inpEditTNCFN";
		public static final String inpEditTNCLN = "inpEditTNCLN";
		public static final String inpEditTNNFN = "inpEditTNNFN";
		public static final String inpEditTNNLN = "inpEditTNNLN";
		public static final String inpRemoveTNMACD = "inpRemoveTNMACD";
		public static final String inpHGName = "inpHGName";
		public static final String inpHGNamermv = "inpHGNamermv";
		public static final String inpHGTnrmv = "inpHGTnrmv";
		public static final String expandItem = "expandItem";
	}

	public MACDConfigurationPage(FrameworkContext context) {
		super(context, "MACDConfigurationPage");
		report = context.getReport();
		this.context = context;
		this.dataSet = context.getDataTable();
		this.userDetails = context.getUserDetail();
		this.settings = context.getSettings();
	}

	public boolean customerDetails(String MACDType, String OrderDescription, String Ticket) {
		mstatus = true;
		try {
			if (testLocatorClickable(Locators.inpTTSTicketID, 60) != null) {
				iValidateLocatorValue(Locators.txtMACDType, MACDType, ComparisonType.EQUAL,
						"Verify the Text Value in the MACD Type Feild");
				iValidateLocatorValue(Locators.txtOrderDescription, OrderDescription, ComparisonType.EQUAL,
						"Verify the Text Value in the Order Description Feild");
				iValidateAttributeValue(Locators.inpTTSTicketID, "value", Ticket, ComparisonType.EQUAL,
						"Verify the Text Value in the TTS Ticket Feild");
				iClick(Locators.btnServiceDetails);
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verify Customer details in New MACD",
					"Verify Customer details in New MACD page Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Verify Customer details in New MACD Failed!! " + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public boolean serviceDetails(String ChangeType, String EntityType) {
		mstatus = true;
		try {
			if (testLocatorClickable(Locators.drpChangeType, 20) != null) {
				iClick(Locators.drpChangeType);
				selectvalue_dropdown("div", ChangeType, false);
				iClick(Locators.drpEntityType);
				selectvalue_dropdown("div", EntityType, false);
				iClick(Locators.btnAddItem);
				try{
					while(isElementDisplayed(getLocatorWEList(Locators.spinner).get(0)))
					{
						log.info("Spinner loading is inprogress.");
					}
				}
				catch(IndexOutOfBoundsException ex)
				{
					log.info("Spinner loading is done.");
				}							
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Configure Service details in New MACD",
					"Configure Service details in New MACD page Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Configure Service details in New MACD Failed!! " + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;

	}

	public boolean addSeatForMACDInSimon(String SeatType, String SeatPurpose, String FirstName, String LastName) {
		mstatus = true;
		try {
			if(!isLocatorVisible(Locators.btnSeatType, 10)){
				iClick(Locators.expandItem);
			}			
			if (testLocatorClickable(Locators.btnSeatType, 20) != null) {
				iClick(Locators.btnSeatType);
				selectvalue_dropdown("Div", SeatType, false);
				iClick(Locators.btnSeatPurpose);
				selectvalue_dropdown("Div", SeatPurpose, false);
				iEnterText(Locators.inpFirstName, FirstName);
				iEnterText(Locators.inpLastName, LastName);
				iClick(Locators.btnSave);
				try{
					while(isElementDisplayed(getLocatorWEList(Locators.spinner).get(0)))
					{
						log.info("Spinner loading is inprogress.");
					}
				}
				catch(IndexOutOfBoundsException ex)
				{
					log.info("Spinner loading is done.");
				}	
				jsClick(getLocatorWEList(Locators.btnOrderSummary).get(0));
			}

		} catch (Exception Ex) {
			report.reportHardFailEvent("MACD Add Seat in SIMON", "MACD Add Seat in SIMON Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "MACD Add Seat in SIMON Failed!! " + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;

	}

	public boolean editSeatTypeForMACDInSimon(String FirstName, String LastName, String SeatTypeAction,
			String CurrentSeatType, String NewSeatType) {
		mstatus = true;
		try {
			if (testLocatorClickable(Locators.inpEditTN, 20) != null) {
				iEnterText(Locators.inpEditFirstName, FirstName);
				iEnterText(Locators.inpEditlastName, LastName);
				iClick(Locators.drpSeatTypeChangeAction);
				selectvalue_dropdown("div", SeatTypeAction, true);
				iClick(Locators.drpCurrentSeatType);
				selectvalue_dropdown("div", CurrentSeatType, true);
				iClick(Locators.drpNewSeatType);
				selectvalue_dropdown("div", NewSeatType, true);
				iClick(Locators.btnSave);
				try{
					while(isElementDisplayed(getLocatorWEList(Locators.spinner).get(0)))
					{
						log.info("Spinner loading is inprogress.");
					}
				}
				catch(IndexOutOfBoundsException ex)
				{
					log.info("Spinner loading is done.");
				}	
				jsClick(getLocatorWEList(Locators.btnOrderSummary).get(0));
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("MACD Edit/Change Seat in SIMON",
					"MACD Edit/Change Seat in SIMON Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "MACD Edit/Change Seat in SIMON Failed!! " + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;

	}

	public boolean clickServiceDetails() {
		mstatus = true;
		try {
			if (testLocatorClickable(Locators.btnServiceDetails, 20) != null) {
				iClick(Locators.btnServiceDetails);
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verify is the Service Details is Selected",
					"The Service Details is not Selected" + Ex.getMessage());
			eMsg = report.getMessage() + "Selecting Service Details in SIMON Failed!! " + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;

	}

	public boolean clickHereToAddanItem() {
		mstatus = true;
		try {
			if (testLocatorClickable(Locators.lnkClickAddButton, 20) != null) {
				iClick(Locators.lnkClickAddButton);
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verify is the Link to Add an Item is selected",
					"The Link to Add an Item is selected is not Selected" + Ex.getMessage());
			eMsg = report.getMessage() + "The Link to Add an Item in SIMON Failed!! " + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;

	}

	public boolean enterRemoveDetailsinServiceDetails(String FirstName, String LastName) {
		mstatus = true;
		try {
			if (testLocatorClickable(Locators.inRMFirstName, 20) != null) {
				iEnterText(Locators.inRMFirstName, FirstName);
				iEnterText(Locators.inRMlastName, LastName);
				iClick(Locators.btnSave);
				try{
					while(isElementDisplayed(getLocatorWEList(Locators.spinner).get(0)))
					{
						log.info("Spinner loading is inprogress.");
					}
				}
				catch(IndexOutOfBoundsException ex)
				{
					log.info("Spinner loading is done.");
				}	
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verify is the Link to Add an Item is selected",
					"The Link to Add an Item is selected is not Selected" + Ex.getMessage());
			eMsg = report.getMessage() + "The Link to Add an Item in SIMON Failed!! " + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;

	}

	public boolean enterCallQueueDetails(String CallQueueName) {
		mstatus = true;
		try {
			if (testLocatorClickable(Locators.inpCallQueueName, 20) != null) {
				iEnterText(Locators.inpCallQueueName, CallQueueName);
				iClick(Locators.drpbtnCallQueuePortType);
				selectvalue_dropdown("div", "New", true);
				iClick(Locators.drpbtnCallQueueTimeZone);
				selectvalue_dropdown("div", "Eastern", true);
				iClick(Locators.btnSave);
				try{
					while(isElementDisplayed(getLocatorWEList(Locators.spinner).get(0)))
					{
						log.info("Spinner loading is inprogress.");
					}
				}
				catch(IndexOutOfBoundsException ex)
				{
					log.info("Spinner loading is done.");
				}	
				
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Select the details in Call Queue and Save",
					"Call Queue Details are not saved" + Ex.getMessage());
			eMsg = report.getMessage() + "Call Queue Details are not saved!! " + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;

	}

	public boolean removeCallQueue(String RemoveCallQueueName) {
		mstatus = true;
		try {
			if (testLocatorClickable(Locators.btnSave, 20) != null) {
				iEnterText(Locators.inpCallQueueName, RemoveCallQueueName);
				iClick(Locators.btnSave);
				try{
					while(isElementDisplayed(getLocatorWEList(Locators.spinner).get(0)))
					{
						log.info("Spinner loading is inprogress.");
					}
				}
				catch(IndexOutOfBoundsException ex)
				{
					log.info("Spinner loading is done.");
				}	
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Enter the Remove Call Queue Name and Save",
					"Remove Call Queue Details are not saved" + Ex.getMessage());
			eMsg = report.getMessage() + "Remove Call Queue Details are not saved!! " + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;

	}

	public boolean addTollFreeTNDetails(String TN) {
		mstatus = true;
		try {
			if (testLocatorClickable(Locators.btnSave, 20) != null) {
				iEnterText_withoutVerify(Locators.inpTollFreeTN, TN, "Enter the Telephone Details");
				iClick(Locators.drpAreaOfService);
				selectvalue_dropdown("div", "US", false);
				iClick(Locators.drpPortType);
				selectvalue_dropdown("div", "New", true);
				iClick(Locators.btnSave);
				try{
					while(isElementDisplayed(getLocatorWEList(Locators.spinner).get(0)))
					{
						log.info("Spinner loading is inprogress.");
					}
				}
				catch(IndexOutOfBoundsException ex)
				{
					log.info("Spinner loading is done.");
				}	
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Enter the Details for Adding Toll Free TN",
					"Toll Free TN Details are not saved" + Ex.getMessage());
			eMsg = report.getMessage() + "Toll Free TN Details are not saved!! " + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;

	}

	public boolean editTollFreeTNDetails(String TN, String CurrentFirstName, String CurrentLastName,
			String NewFirstName, String NewLastName) {
		mstatus = true;
		try {
			if (testLocatorClickable(Locators.btnSave, 20) != null) {
				iEnterText_withoutVerify(Locators.inpEditTN, TN, "Enter the Telephone Details in the Edit TN Section");
				iEnterText(Locators.inpTFTNEditCurrentFName, CurrentFirstName);
				iEnterText(Locators.inpTFTNEditCurrentLName, CurrentLastName);
				iEnterText(Locators.inpTFTNEditNewFName, NewFirstName);
				iEnterText(Locators.inpTFTNEditNewLName, NewLastName);
				iClick(Locators.btnSave);
				try{
					while(isElementDisplayed(getLocatorWEList(Locators.spinner).get(0)))
					{
						log.info("Spinner loading is inprogress.");
					}
				}
				catch(IndexOutOfBoundsException ex)
				{
					log.info("Spinner loading is done.");
				}	
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Enter the Details for Editing Toll Free TN",
					"Toll Free TN Details edited are not saved" + Ex.getMessage());
			eMsg = report.getMessage() + "Toll Free TN Details edited are not saved" + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;

	}

	public boolean removeTollFreeTN(String TN) {
		mstatus = true;
		try {
			if (testLocatorClickable(Locators.btnSave, 20) != null) {
				iEnterText_withoutVerify(Locators.inpRemoveTFTN, TN, "Enter the TN which Needs to be removed");
				iClick(Locators.btnSave);
				try{
					while(isElementDisplayed(getLocatorWEList(Locators.spinner).get(0)))
					{
						log.info("Spinner loading is inprogress.");
					}
				}
				catch(IndexOutOfBoundsException ex)
				{
					log.info("Spinner loading is done.");
				}	
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Enter the Toll Free TN Which Needs to be removed ",
					"Toll Free TN Details Removed are not updated are not saved" + Ex.getMessage());
			eMsg = report.getMessage() + "Toll Free TN Details Removed are not updated are not saved" + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;

	}

	public boolean addRCFTN(String TN, String TranslationTN, String PortType, String State) {
		mstatus = true;
		try {
			if (testLocatorClickable(Locators.btnSave, 20) != null) {
				iEnterText_withoutVerify(Locators.inpRCFTn, TN, "Enter the RCF TN");
				iEnterText_withoutVerify(Locators.inpTranslationTN, TranslationTN, "Enter the RCF Translation TN");
				iClick(Locators.drpRFTNPortType);
				selectvalue_dropdown("div", PortType, false);
				iClick(Locators.drpRFTNState);
				selectvalue_dropdown("div", State, false);
				iClick(Locators.btnSave);
				try{
					while(isElementDisplayed(getLocatorWEList(Locators.spinner).get(0)))
					{
						log.info("Spinner loading is inprogress.");
					}
				}
				catch(IndexOutOfBoundsException ex)
				{
					log.info("Spinner loading is done.");
				}	
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Enter the RCF TN Which Needs to be Added ",
					"RCF TN Details added are not updated and is not saved" + Ex.getMessage());
			eMsg = report.getMessage() + "RCF TN Details added are not updated and is not saved" + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;

	}

	public boolean editRCFTN(String TN, String TranslationTN, String CFN, String CLN, String NFN, String NLN) {
		mstatus = true;
		try {
			if (testLocatorClickable(Locators.btnSave, 20) != null) {
				iEnterText_withoutVerify(Locators.inpTNEditRCF, TN, "Enter the TN Number");
				iEnterText_withoutVerify(Locators.inpTranslationTNEditRCF, TranslationTN, "Enter the Translation TN");
				iEnterText(Locators.inpCFNEditRCF, CFN);
				iEnterText(Locators.inpCLNEditRCF, CLN);
				iEnterText(Locators.inpNFNEditRCF, NFN);
				iEnterText(Locators.inpNLNEditRCF, NLN);
				iClick(Locators.btnSave);
				try{
					while(isElementDisplayed(getLocatorWEList(Locators.spinner).get(0)))
					{
						log.info("Spinner loading is inprogress.");
					}
				}
				catch(IndexOutOfBoundsException ex)
				{
					log.info("Spinner loading is done.");
				}	
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Enter the RCF TN Which Needs to be Edited ",
					"RCF TN Details Edited are not updated and is not saved" + Ex.getMessage());
			eMsg = report.getMessage() + "RCF TN Details Edited are not updated and is not saved" + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;

	}

	public boolean removeRCFTN(String TN) {
		mstatus = true;
		try {
			if (testLocatorClickable(Locators.btnSave, 20) != null) {
				iEnterText_withoutVerify(Locators.inpRemoveRFTN, TN, "Enter the RCF TN Number identified to Remove");
				iClick(Locators.btnSave);
				try{
					while(isElementDisplayed(getLocatorWEList(Locators.spinner).get(0)))
					{
						log.info("Spinner loading is inprogress.");
					}
				}
				catch(IndexOutOfBoundsException ex)
				{
					log.info("Spinner loading is done.");
				}	
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Enter the RCF TN Which Needs to be Removed ",
					"RCF TN Details Removed  are not updated and is not saved" + Ex.getMessage());
			eMsg = report.getMessage() + "RCF TN Details Removed  are not updated and is not saved" + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;

	}
	public boolean addReceptionist(String name,String users) {
		mstatus = true;
		try {
			if (testLocatorClickable(Locators.btnSave, 20) != null) {
				iEnterText(Locators.inpReceptionistName, name);
				iEnterText(Locators.inpRecpUsers, users);
				iClick(Locators.btnSave);
				try{
					while(isElementDisplayed(getLocatorWEList(Locators.spinner).get(0)))
					{
						log.info("Spinner loading is inprogress.");
					}
				}
				catch(IndexOutOfBoundsException ex)
				{
					log.info("Spinner loading is done.");
				}	
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Enter the Receptionist Name and User Details ",
					"Receptionist Name and User Details are not updated and saved" + Ex.getMessage());
			eMsg = report.getMessage() + "Receptionist Name and User Details are not updated and saved" + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;

	}
	public boolean removeReceptionist(String name,String TN) {
		mstatus = true;
		try {
			if (testLocatorClickable(Locators.btnSave, 20) != null) {
				iEnterText(Locators.inpRecpNameRemove, name);
				iEnterText_withoutVerify(Locators.inpRecpTNRemove, TN, "Enter the TN Details for Remove Receptionist");
				iClick(Locators.btnSave);
				try{
					while(isElementDisplayed(getLocatorWEList(Locators.spinner).get(0)))
					{
						log.info("Spinner loading is inprogress.");
					}
				}
				catch(IndexOutOfBoundsException ex)
				{
					log.info("Spinner loading is done.");
				}	
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Enter the Receptionist Name and TN Details which needs to be removed ",
					"Receptionist Name and TN Details are not updated and saved" + Ex.getMessage());
			eMsg = report.getMessage() + "Receptionist Name and TN Details  are not updated and saved" + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;

	}
	
	public boolean addAutoAttendent(String name,String TN,String timeSchedule,String holidaySchedule) {
		mstatus = true;
		try {
			if (testLocatorClickable(Locators.btnSave, 20) != null) {
				iEnterText(Locators.inpAutoAttendentName, name);
				iEnterText_withoutVerify(Locators.inpCurrentTNAutoattendent, TN, "Enter the TN Details for AutoAttendent");
				iClick(Locators.drpPortTypeAutoAttendent);
				selectvalue_dropdown("div", "New", false);
				iEnterText(Locators.inpTXTtimescheduleDesc, timeSchedule);
				iEnterText(Locators.inpHolidayScheduleDesc, holidaySchedule);
				iClick(Locators.drpAutoattTimezone);
				selectvalue_dropdown("div", "Eastern", false);
				iClick(Locators.btnSave);
				try{
					while(isElementDisplayed(getLocatorWEList(Locators.spinner).get(0)))
					{
						log.info("Spinner loading is inprogress.");
					}
				}
				catch(IndexOutOfBoundsException ex)
				{
					log.info("Spinner loading is done.");
				}	
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Enter the Auto Attendent Details which needs to be Added ",
					"Auto Attendent Details are not updated and saved" + Ex.getMessage());
			eMsg = report.getMessage() + "Auto Attendent Details are not updated and saved" + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;

	}
	public boolean removeAutoAttendent(String name,String TN) {
		mstatus = true;
		try {
			if (testLocatorClickable(Locators.btnSave, 20) != null) {
				iEnterText(Locators.inpAutoAttendentRMName, name);
				iEnterText_withoutVerify(Locators.inpRMAutoAtt, TN, "Enter the TN  for AutoAttendent-Remove");
				iClick(Locators.btnSave);
				try{
					while(isElementDisplayed(getLocatorWEList(Locators.spinner).get(0)))
					{
						log.info("Spinner loading is inprogress.");
					}
				}
				catch(IndexOutOfBoundsException ex)
				{
					log.info("Spinner loading is done.");
				}	
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Enter the Auto Attendent Details which needs to be Removed ",
					"Auto Attendent Details for Remove are not updated and saved" + Ex.getMessage());
			eMsg = report.getMessage() + "Auto Attendent Details for Remove are not updated and saved" + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;

	}
	public boolean addTN(String TN) {
		mstatus = true;
		try {
			if (testLocatorClickable(Locators.btnSave, 20) != null) {
				iEnterText_withoutVerify(Locators.inpAddTN, TN, "Enter the TN  for Add TN");
				iClick(Locators.drpPortTNType);
				selectvalue_dropdown("div", "New", false);
				iClick(Locators.btnSave);
				try{
					while(isElementDisplayed(getLocatorWEList(Locators.spinner).get(0)))
					{
						log.info("Spinner loading is inprogress.");
					}
				}
				catch(IndexOutOfBoundsException ex)
				{
					log.info("Spinner loading is done.");
				}	
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Enter the TN and Port Type in Add TN",
					"TN and Port Type in Add TN are not updated and saved" + Ex.getMessage());
			eMsg = report.getMessage() + "TN and Port Type in Add TN are not updated and saved" + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;

	}	
	public boolean editTN(String TN,String CFN,String CLN,String NFN,String NLN) {
		mstatus = true;
		try {
			if (testLocatorClickable(Locators.btnSave, 20) != null) {			
				iEnterText_withoutVerify(Locators.inpEditTNMACD, TN, "Enter the TN  for Edit TN");
				iEnterText(Locators.inpEditTNCFN, CFN);
				iEnterText(Locators.inpEditTNCLN, CLN);
				iEnterText(Locators.inpEditTNNFN, NFN);
				iEnterText(Locators.inpEditTNNLN, NLN);
				iClick(Locators.btnSave);
				try{
					while(isElementDisplayed(getLocatorWEList(Locators.spinner).get(0)))
					{
						log.info("Spinner loading is inprogress.");
					}
				}
				catch(IndexOutOfBoundsException ex)
				{
					log.info("Spinner loading is done.");
				}	
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Enter the TN, Current and New First/Last Names in Edit TN",
					"TN, Current and New First/Last Names in Edit TN are not updated and saved" + Ex.getMessage());
			eMsg = report.getMessage() + "TN, Current and New First/Last Names in Edit TN are not updated and saved" + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;

	}	
	public boolean removeTN(String TN){
		mstatus = true;
		try {
			if (testLocatorClickable(Locators.btnSave, 20) != null) {	
				iEnterText_withoutVerify(Locators.inpRemoveTNMACD, TN, "Enter the TN  for Remove TN");
				iClick(Locators.btnSave);
				waitForSpinnerComplete();
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Enter the TN in Remove TN",
					"TN entered for Remove TN is not updated and saved" + Ex.getMessage());
			eMsg = report.getMessage() + "TN entered for Remove TN is not updated and saved" + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;

	}
	public boolean addHuntGroup(String HG){
		mstatus = true;
		try {
			if (testLocatorClickable(Locators.btnSave, 20) != null) {	
				iEnterText(Locators.inpHGName, HG);	
				iClick(Locators.btnSave);
				waitForSpinnerComplete();
				report.reportPassEvent("Adding Hunt Group", "Adding Hunt Group is successful");
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Adding Hunt Group",
					"Adding Hunt Group Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Adding Hunt Group Failed" + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;

	}
	public boolean removeHuntGroup(String HGName,String TN){
		mstatus = true;
		try {
			if (testLocatorClickable(Locators.btnSave, 20) != null) {	
				iEnterText(Locators.inpHGNamermv, HGName);
				iEnterText_withoutVerify(Locators.inpHGTnrmv, TN, "Enter the TN assocaiated with the Hunt Group that needs to be removed");
				iClick(Locators.btnSave);
				waitForSpinnerComplete();
				report.reportPassEvent("Removing Hunt Group", "Removing Hunt Group is successful");
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Removing  Hunt Group",
					"Removing Hunt Group Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Removing Hunt Group Failed" + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;

	}
}
