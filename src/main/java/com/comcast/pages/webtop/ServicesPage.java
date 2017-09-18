package com.comcast.pages.webtop;

import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.eclipse.persistence.internal.libraries.asm.commons.GeneratorAdapter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class ServicesPage extends WebtopPage {

	Logger log = Logger.getLogger(ServicesPage.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public ServicesPage(FrameworkContext context) {
		super(context, "ServicesPage");
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
		public static final String txtDescription = "txtDescription";
		public static final String btnSaveAndContinue = "btnSaveAndContinue";
		public static String ServiceType = "";
		public static final String dropDwnBTN = "dropDwnBTN";
		public static final String txtVoIpID = "txtVoIpID";
		public static final String dropDwnCustomerLineType = "dropDwnCustomerLineType";
		public static final String stxtEmailAddressForUser = "txtEmailAddressForUser";
		public static final String txtCNAMLastName = "txtCNAMLastName";
		public static final String txtCNAMFirstName = "txtCNAMFirstName";
		public static final String txtCallingName = "txtCallingName";
		public static final String txtEffectiveDate = "txtEffectiveDate";
		public static final String btnAddToOrder = "btnAddToOrder";
		public static final String chkboxVoIP = "chkboxVoIP";
		public static final String chkboxOrderInventoryANI = "chkboxOrderInventoryANI";
		public static final String btnItem = "btnItem";
		public static final String btnBundle = "btnBundle";
		public static final String dropDownItem = "dropDownItem";
		public static final String dropDownFeatureType = "dropDownFeatureType";
		public static final String dropDownLanguage = "dropDownLanguage";
		public static final String txtRecContactName = "txtRecContactName";
		public static final String txtRecContactEMail = "txtRecContactEMail";
		public static final String txtRecContactPhnoeNo = "txtRecContactPhnoeNo";
		public static final String dropDownAnnouncementType = "dropDownAnnouncementType";
		public static final String dropDownVoiceTone = "dropDownVoiceTone";
		public static final String dropDownAAItemStatus = "dropDownAAItemStatus";
		public static final String dropDownSpokenBusinessLegalType = "dropDownSpokenBusinessLegalType";
		public static final String btnSave = "btnSave";
		public static final String chkboxANI = "chkboxANI";
		public static final String txt10DigitTNToConvert = "txt10DigitTNToConvert";
		public static final String txtUserMacAddress = "txtUserMacAddress";
		public static final String txtTrunkGroupId = "txtTrunkGroupId";
		public static final String txtFName = "txtFName";
		public static final String txtLName = "txtLName";
		public static final String dropdwnTypeOfUser = "dropdwnTypeOfUser";
		public static final String chkboxTrk = "chkboxTrk";
		public static final String txtDECTBaseName = "txtDECTBaseName";
		public static final String txtDECTHandSetIPEI = "txtDECTHandSetIPEI";
		public static final String txtANI = "txtANI";
		public static final String dropdownPorted = "dropdownPorted";
		public static final String dropDownEvent = "dropDownEvent";
		public static final String txtDLProvider = "txtDLProvider";
		public static final String txtSIC = "txtSIC";
		public static final String txtYellowPageHeaderText = "txtYellowPageHeaderText";
		public static final String dropDownUserILD = "dropDownUserILD";
		public static final String txtYellowPageCode = "txtYellowPageCode";
		public static final String dropDownRecordType = "dropDownRecordType";
		public static final String txtAmount = "txtAmount";
		public static final String chkboxCustomerLevel = "chkboxCustomerLevel";
		public static final String txtQuantity = "txtQuantity";


	}

	public boolean addServices(String description, String ServiceType) {
		mstatus = true;
		try {
			if(isLocatorVisible(Locators.txtDescription, 1)){
				iEnterText(Locators.txtDescription, description);
				iClick(Locators.btnSaveAndContinue, null,
					"Click On Save All And Continue button:Service Page:Save All And Continue Button");
			}
			if (!ServiceType.isEmpty()) {
				addLocator(Locators.ServiceType, "xpath", ".//input[@value='" + ServiceType + "']",
						ServiceType + " Tab Button");
				iClick(Locators.ServiceType, null,
						"Click on " + ServiceType + "Tab Button:Services Page:" + ServiceType + " Tab Button");
			}
			report.reportPassEvent("Add Services to Inventory", "Add Services to Inventory", Status.PASS);
		} catch (Exception e) {
			report.reportHardFailEvent("Add Services to Inventory", "Add Services to Inventory Failed!!!", Status.FAIL);
			String eMsg = "Error in Add Services to Inventory --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}

	public boolean addANNOUNCE() {
		mstatus = true;
		try {
			// VOIP ADD
			iSelectValueByIndex(Locators.dropDwnBTN, 1);
			String[] VoipList = dataDump.getValue("VOIPList_before").split("\\|");
			int size = VoipList.length - 1;
			String newVoIpNo = VoipList[size].split("\\-")[0] + "-"
					+ String.valueOf(Integer.parseInt(VoipList[size].split("\\-")[1]) + 1);
			dataDump.setValue("NewVoIPID_RT", newVoIpNo);
			iEnterText(Locators.txtVoIpID, newVoIpNo);
			iSelectValue(Locators.dropDwnCustomerLineType, "NTV - Native");
			iEnterText(Locators.stxtEmailAddressForUser, "NewAnnounce_" + getTimestamp() + "@comcast.com");
			// PROVISIONING
			iEnterText(Locators.txtCNAMFirstName, "FName");
			iEnterText(Locators.txtCNAMLastName, "LName_" + getTimestamp());
			iEnterText(Locators.txtCallingName, "FN " + newVoIpNo);
			iEnterText(Locators.txtEffectiveDate, getCurrentDate());
			iClick(Locators.btnAddToOrder, null, "Click on Add To Order Button:Services Page:Add To Order");
		} catch (Exception e) {
			report.reportHardFailEvent("Add Services to Inventory", "Add Services to Inventory Failed!!!", Status.FAIL);
			String eMsg = "Error in Add Services to Inventory --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}

	public boolean addANI() {
		mstatus = true;
		try {
			// ANI ADD
			iSelectValueByIndex(Locators.dropDwnBTN, 1);
			iEnterText(Locators.txtVoIpID, "0");
			iSelectValue(Locators.dropDwnCustomerLineType, "NTV - Native");
			iSelectValue(Locators.dropdownPorted, "No");
			iEnterText(Locators.stxtEmailAddressForUser, "NewANI_" + getTimestamp() + "@comcast.com");
			iSelectValue(Locators.dropDownEvent, "New");
			iEnterText(Locators.txtDLProvider, "Comcast");
			iEnterText(Locators.txtSIC, randomNumber(5));
			iEnterText(Locators.txtYellowPageHeaderText, "YellowPageText");
			iSelectValue(Locators.dropdwnTypeOfUser, "Standard");
			iSelectValue(Locators.dropDownUserILD, "No");
			iEnterText(Locators.txtCNAMFirstName, "FName");
			iEnterText(Locators.txtCNAMLastName, "LName_" + getTimestamp());
			iEnterText(Locators.txtYellowPageCode, randomNumber(6));
			iSelectValue(Locators.dropDownRecordType, "Local Additional Listing");
			report.reportPassEvent("Adding new ANI", "Adding New ANi is Done");
			iClick(Locators.btnAddToOrder, null, "Click on Add To Order Button:Services Page:Add To Order");

		} catch (Exception e) {
			report.reportHardFailEvent("Add ANI to Inventory", "Add ANI to Inventory Failed!!!", Status.FAIL);
			String eMsg = "Error in Add ANI to Inventory --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean addANI(String eMailId, String typeOfUser) {
		mstatus = true;
		try {
			// ANI ADD
			iSelectValueByIndex(Locators.dropDwnBTN, 1);
			iEnterText(Locators.txtVoIpID, "0");
			iSelectValue(Locators.dropDwnCustomerLineType, "NTV - Native");
			iSelectValue(Locators.dropdownPorted, "No");
			iEnterText(Locators.stxtEmailAddressForUser, eMailId);
			iSelectValue(Locators.dropDownEvent, "New");
			iEnterText(Locators.txtDLProvider, "Comcast");
			iEnterText(Locators.txtSIC, randomNumber(5));
			iEnterText(Locators.txtYellowPageHeaderText, "YellowPageText");
			iSelectValue(Locators.dropdwnTypeOfUser, typeOfUser);
			iSelectValue(Locators.dropDownUserILD, "No");
			iEnterText(Locators.txtCNAMFirstName, "FName");
			iEnterText(Locators.txtCNAMLastName, "LName_" + getTimestamp());
			iEnterText(Locators.txtYellowPageCode, randomNumber(6));
			iSelectValue(Locators.dropDownRecordType, "Local Additional Listing");
			report.reportPassEvent("Adding new ANI", "Adding New ANi is Done");
			iClick(Locators.btnAddToOrder, null, "Click on Add To Order Button:Services Page:Add To Order");

		} catch (Exception e) {
			report.reportHardFailEvent("Add ANI to Inventory", "Add ANI to Inventory Failed!!!", Status.FAIL);
			String eMsg = "Error in Add ANI to Inventory --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}

	public boolean addVoIP() {
		mstatus = true;
		try {
			// VOIP ADD
			iSelectValueByIndex(Locators.dropDwnBTN, 1);
			iEnterText(Locators.txtVoIpID, "0");
			iClick(Locators.btnAddToOrder, null, "Click on Add To Order Button:Services Page:Add To Order");
		} catch (Exception e) {
			report.reportHardFailEvent("Add VoIP Services to Inventory", "Add VoIP Services to Inventory Failed!!!",
					Status.FAIL);
			String eMsg = "Error in Add VoIP Services to Inventory --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean addVoIPWithExtension() {
		mstatus = true;
		try {
			// VOIP ADD
			iSelectValueByIndex(Locators.dropDwnBTN, 1);
			iEnterText(Locators.txtVoIpID, dataDump.getValue("GroupID") +"-"+ "69553" + randomNumber(1));
			iSelectValue(Locators.dropDwnCustomerLineType, "NTV - Native");
			iEnterText(Locators.txtCNAMFirstName, "FName");
			iEnterText(Locators.txtCNAMLastName, "LName_" + getTimestamp());
			iClick(Locators.btnAddToOrder, null, "Click on Add To Order Button:Services Page:Add To Order");
		} catch (Exception e) {
			report.reportHardFailEvent("Add VoIP Services to Inventory", "Add VoIP Services to Inventory Failed!!!",
					Status.FAIL);
			String eMsg = "Error in Add VoIP Services to Inventory --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}

	public boolean addTrunk(String typeOfUser) {
		mstatus = true;
		try {
			// Trunk ADD
			iSelectValueByIndex(Locators.dropDwnBTN, 1);
			iEnterText(Locators.txtTrunkGroupId, "0");
			iEnterText(Locators.txtFName, "AutoFName");
			iEnterText(Locators.txtLName, "AutoLName" + getTimestamp());
			iEnterText(Locators.stxtEmailAddressForUser, dataSet.getValue("EAO_EmailId"));
			iSelectValue(Locators.dropdwnTypeOfUser, typeOfUser);
			iClick(Locators.btnAddToOrder, null, "Click on Add To Order Button:Services Page:Add To Order");
		} catch (Exception e) {
			report.reportHardFailEvent("Add Trunk Services to Inventory", "Add Trunk Services to Inventory Failed!!!",
					Status.FAIL);
			String eMsg = "Error in Add Trunk Services to Inventory --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}

	public boolean addItemOrBundle(String bundleOrItem, String addBundleOrItemOn, String ItemOrBundleName, int quantity) {
		mstatus = true;
		try {
			List<WebElement> chkBoxList = null;
			switch (addBundleOrItemOn) {
			case "VIP":
				chkBoxList = getLocatorWEList(Locators.chkboxVoIP);
				for (int idx = 0; idx < chkBoxList.size(); idx++) {
					if (waitForElement(chkBoxList.get(idx), 1)) {
						iSetCheckBox(chkBoxList.get(idx), true);
					}
				}
				break;
			case "ANI":
				chkBoxList = getLocatorWEList(Locators.chkboxANI);
				for (int idx = 0; idx < chkBoxList.size(); idx++) {
					if (waitForElement(chkBoxList.get(idx), 1)) {
						iSetCheckBox(chkBoxList.get(idx), true);
					}
				}
				break;
			case "OrderInvertoryANI":
				chkBoxList = getLocatorWEList(Locators.chkboxOrderInventoryANI);
				for (int idx = 0; idx < chkBoxList.size(); idx++) {
					if (waitForElement(chkBoxList.get(idx), 1)) {
						iSetCheckBox(chkBoxList.get(idx), true);
					}
				}
				break;
			case "TRK":
				chkBoxList = getLocatorWEList(Locators.chkboxTrk);
				for (int idx = 0; idx < chkBoxList.size(); idx++) {
					if (waitForElement(chkBoxList.get(idx), 1)) {
						iSetCheckBox(chkBoxList.get(idx), true);
					}
				}
				break;
			case "CustomerLevel":
				chkBoxList = getLocatorWEList(Locators.chkboxCustomerLevel);
				for (int idx = 0; idx < chkBoxList.size(); idx++) {
					if (waitForElement(chkBoxList.get(idx), 1)) {
						iSetCheckBox(chkBoxList.get(idx), true);
					}
				}
				break;
			case "BTN":
				break;
			case "Bundle":
				break;
			}

			switch (bundleOrItem) {
			case "Item":
				iClick(Locators.btnItem, null, "Click On Item Button:Services Page:Item Button");
				break;
			case "Bundle":
				iClick(Locators.btnBundle, null, "Click On Item Button:Services Page:Item Button");
				break;
			}
			iSelectValue(Locators.dropDownItem, ItemOrBundleName);
			switch (ItemOrBundleName) {
			case "ANNOUNCE-ANNOUNCE | 0.00":
				iSelectValue(Locators.dropDownFeatureType, "Auto Attendant");
				iSelectValue(Locators.dropDownLanguage, "English");
				iEnterText(Locators.txtRecContactName, "NewAA_" + getTimestamp());
				iEnterText(Locators.txtRecContactEMail, "NewAA_" + getTimestamp() + "@comcast.com");
				iEnterText(Locators.txtRecContactPhnoeNo, "9" + randomNumber(9));
				iSelectValue(Locators.dropDownAnnouncementType, "Day");
				iSelectValue(Locators.dropDownVoiceTone, "Professional");
				iSelectValue(Locators.dropDownSpokenBusinessLegalType, "Say short form");
				iSelectValue(Locators.dropDownAAItemStatus, "Unused");
				break;
			case "BVEAATN-Auto Attendant | 0.00":
			case "BVEUCS-Unified Communications Seat | 0.00":
			case "BVEADMNUSR-Admin-Only User | 0.00":
				break;
			case "BVEUC10-19-Seat Based Unified Communication Seats 10-19 | 39.95":
				iEnterText(Locators.txtQuantity, String.valueOf(quantity));				
				break;
			case "BVEVVX500H-Polycom SoundPoint VVX 500 HD | 0.00":
				String uuid = UUID.randomUUID().toString();
				String MACAddress = uuid.substring(uuid.lastIndexOf("-") + 1);
				iEnterText(Locators.txtUserMacAddress, MACAddress);
				dataDump.setValue("MACAddressForNewDevice", MACAddress);
				break;
			case "BVEVVX311-Polycom VVX 311 HD | 0.00":
			case "BVEVVX411-Polycom VVX 411 HD | 0.00":
			case "BVEVVX501-Polycom VVX 501 HD | 0.00":
			case "BVEVVX601-Polycom VVX 601 HD | 0.00":
				break;
			case "BVEHTPA60I-Panasonic TPA60 Smart IP Cordless Handset | 0.00":
				if(!dataDump.getValue("DECTHandSetIPEI").isEmpty()){
					iEnterText(Locators.txtDECTHandSetIPEI, dataDump.getValue("DECTHandSetIPEI"));
				}
				else
				{
					iEnterText(Locators.txtDECTHandSetIPEI, "TESTSERIAL" + randomNumber(5));
				}
				if(!dataDump.getValue("DECTBaseName").isEmpty()){
					iEnterText(Locators.txtDECTBaseName, dataDump.getValue("DECTBaseName"));
				}
				else
				{
					iEnterText(Locators.txtDECTBaseName, "DECTbase 1-" + randomNumber(5));
				}				
				break;
			case "CONVERTANI-Convert ANI Record to VoIP | 0.00":
				iEnterText(Locators.txt10DigitTNToConvert, dataDump.getValue("ANINumberToConvert_RT"));
				break;
			}
			report.reportPassEvent("Add Item/Bundle to Inventory", "Add Item/Bundle to Inventory", Status.PASS);
			iClick(Locators.btnSave, null, "Click On Save button:Service Page:Save Button");
		} catch (Exception e) {
			report.reportHardFailEvent("Add Item/Bundle to Inventory", "Add Item/Bundle to Inventory Failed!!!",
					Status.FAIL);
			String eMsg = "Error in Add Item/Bundle to Inventory --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}

	public void updateBundlePrice(String BundlePrice) {
		try {
			iEnterText(Locators.txtAmount, BundlePrice);
			report.reportPassEvent("Update the price with promotional discount",
					"Update the price with promotional discount");
			iClick(Locators.btnSave);
			iClick(Locators.btnSaveAndContinue);
		} catch (Exception ex) {
			log.error("Updating Bundle price failed");
			report.reportHardFailEvent("Updating Bundle price", "Updating Bundle price failed");
		}

	}

}
