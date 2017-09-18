package com.comcast.pages.webtop;

import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.comcast.commons.WebtopPage;
import com.comcast.pages.webtop.ServicesPage.Locators;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.utils.ComparisonType;

public class AccountProfileTab extends WebtopPage {

	Logger log = Logger.getLogger(AccountProfileTab.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;

	public AccountProfileTab(FrameworkContext context) {
		super(context, "AccountProfileTab");
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
		public static final String tabAccountProfile = "tabAccountProfile";
		public static final String tabOrderProfile = "tabOrderProfile";
		public static final String lnkExpandAll = "lnkExpandAll";
		public static final String lnkEditESG = "lnkEditESG";
		public static final String chkboxUpdateFields = "chkboxUpdateFields";
		public static final String txtMACAddress = "txtMACAddress";
		public static final String btnSave = "btnSave";
		public static final String btnSaveAllAndContinue = "btnSaveAllAndContinue";
		public static final String lnkGroupID = "lnkGroupID";
		public static final String lnkInprogressOrder = "lnkInprogressOrder";
		public static final String BTNNo = "BTNNo";
		public static final String TollFreeTNEditLink = "";
		public static final String txtTerminatingANI = "txtTerminatingANI";
		public static final String btnUpdate = "btnUpdate";
		public static final String lnkStdANIList = "lnkStdANIList";
		public static final String lnkUCANIList = "lnkUCANIList";
		public static final String lnkANIList = "lnkANIList";
		public static final String txtE911StreetNo = "txtE911StreetNo";
		public static final String txtE911StreeName = "txtE911StreeName";
		public static final String txtE911City = "txtE911City";
		public static final String txtE911Zip = "txtE911Zip";
		public static final String txtE911State = "txtE911State";
		public static final String txtE911UnitValue = "txtE911UnitValue";
		public static final String txtE911Unit = "txtE911Unit";
		public static final String btnCancel = "btnCancel";
		public static final String btnReturntoInventory = "btnReturntoInventory";
		public static final String btnEditInventory = "btnEditInventory";
		public static final String expandANIs = "expandANIs";
		public static final String lnkANIEdit = "lnkANIEdit";
		public static final String chkboxUpdateAddress = "chkboxUpdateAddress";
		public static final String ANICheckBox = "";
		public static final String lnkVOIPList = "lnkVOIPList";
		public static final String btnAddInventory = "btnAddInventory";
		public static final String lnkDeleteOrder = "lnkDeleteOrder";
		public static final String lnkSubmitThisOrder = "lnkSubmitThisOrder";
		public static final String lnkAddStdSeat = "lnkAddStdSeat";
		public static final String lnkAddUCSeat = "lnkAddUCSeat";
		public static final String lnkDeviceEdit = "lnkDeviceEdit";
		public static final String lnkEditStdSeat = "lnkEditStdSeat";
		public static final String lnkEditUCSeat = "lnkEditUCSeat";
		public static final String txtTerminatingDate = "txtTerminatingDate";
		public static final String lnkEditUCSeatDevice = "lnkEditUCSeatDevice";
		public static final String lnkEditStdSeatDevice = "lnkEditStdSeatDevice";
		public static final String txtDECTHandSetIPEI = "txtDECTHandSetIPEI";
		public static final String txtDECTBaseName = "txtDECTBaseName";
		public static final String lnkEditEAO = "lnkEditEAO";
		public static final String dropdwnTypeOfUser = "dropdwnTypeOfUser";
		public static final String lblUCBundle1To9 = "lblUCBundle1To9";
		public static final String lblUCBundle10To19 = "lblUCBundle10To19";
		public static final String lnkEditBundle10To19 = "lnkEditBundle10To19";
		public static final String lblEdgeMarc = "";
		public static final String lblLineCount = "lblLineCount";
		public static final String txtEmailAddressForUser = "txtEmailAddressForUser";
		public static final String txtproductCatalog = "txtproductCatalog";
		public static final String txtproductCatalogAmount ="txtproductCatalogAmount";
		public static final String txtAAVOIPList ="txtAAVOIPList";
		public static final String txtVMBundle = "txtVMBundle";
		public static final String txtCQBAgentBundle = "txtCQBAgentBundle";
		public static String txtlineItemList = "";
	}

	public boolean updateESGMACAddress(String esgMACAddress) {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.tabAccountProfile, 2)) {
				iClick(Locators.tabAccountProfile, null,
						"Click on Entended Info button:Webtop Home Page:Click on Entended Info button");
				iClick(Locators.lnkExpandAll, null, "Click on Expand All Link:Account Profile Page:Expand All Link");
				Scroll_Page_Down();
				iClick(Locators.lnkEditESG, null,
						"Click on ESG Device Edit Link:Account Profile Page:ESG Device Edit Link");
				iClick(Locators.chkboxUpdateFields, null,
						"Enable on Enable Update Fields check box:Account Profile Page:Enable Update Fields");
				iEnterText(Locators.txtMACAddress, esgMACAddress);
				iClick(Locators.btnSave, null, "Click on Save button:Account Profile Page:Save button");
				iClick(Locators.btnSaveAllAndContinue, null,
						"Click on Save All and continue button:Account Profile Page:Save All and continue button");
				iClick(Locators.lnkGroupID, null, "Click on Group ID:Account Profile Tab:Group ID");
				iClick(Locators.lnkInprogressOrder, null, "Click on the Open Order link");
			}

		} catch (Exception e) {
			report.reportHardFailEvent("Update ESG MAC Address", "Update ESG MAC Address Failed!!!", Status.FAIL);
			String eMsg = "Error in Update ESG MAC Address --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean validateLineItemCount(String lineItemName, int expectedCount) {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.tabAccountProfile, 2)) {
				iClick(Locators.tabAccountProfile, null,
						"Click on Entended Info button:Webtop Home Page:Click on Entended Info button");
				iClick(Locators.lnkExpandAll, null, "Click on Expand All Link:Account Profile Page:Expand All Link");
				Scroll_Page_Down();
				addLocator(Locators.txtlineItemList, "xpath", "//span[contains(.,'"+lineItemName+"')]", lineItemName + " Line Item");				
				boolean res = (getLocatorWEList(Locators.txtlineItemList).size()==expectedCount);
				iReport(Locators.txtlineItemList, res?"Line Item '"+lineItemName+"' is displayed as expected":"Line Item '"+lineItemName+"' is NOT displayed as expected. Actual Count: " +getLocatorWEList(Locators.txtlineItemList).size()+ ". Expected count: " + expectedCount, "Verify if only " + expectedCount +" "+lineItemName + " is displayed", res);
			}

		} catch (Exception e) {
			report.reportHardFailEvent("Verification of '"+lineItemName+"' in account profile Tab page",
					"Number of '"+lineItemName+"' is NOT displayed as expected");
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean addESGMACAddress() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.tabAccountProfile, 2)) {
				iClick(Locators.tabAccountProfile, null,
						"Click on Entended Info button:Webtop Home Page:Click on Entended Info button");
				iClick(Locators.lnkExpandAll, null, "Click on Expand All Link:Account Profile Page:Expand All Link");
				Scroll_Page_Down();
				iClick(Locators.lnkEditESG, null,
						"Click on ESG Device Edit Link:Account Profile Page:ESG Device Edit Link");
				iClick(Locators.chkboxUpdateFields, null,
						"Enable on Enable Update Fields check box:Account Profile Page:Enable Update Fields");
				String uuid = UUID.randomUUID().toString();				
				String EsgMACAddress = uuid.substring(uuid.lastIndexOf("-") + 1);
				log.info("ESG MAC Address is:" + EsgMACAddress);
				iEnterText(Locators.txtMACAddress, EsgMACAddress);
				iClick(Locators.btnSave, null, "Click on Save button:Account Profile Page:Save button");
				iClick(Locators.btnSaveAllAndContinue, null,
						"Click on Save All and continue button:Account Profile Page:Save All and continue button");				
			}

		} catch (Exception e) {
			report.reportHardFailEvent("Update ESG MAC Address", "Update ESG MAC Address Failed!!!", Status.FAIL);
			String eMsg = "Error in Update ESG MAC Address --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
	public boolean updateMACAddressForAllDevice() {
		mstatus = true;
		try {
			if (!isLocatorVisible(Locators.lnkExpandAll, 2)) {
				iClick(Locators.tabAccountProfile, null,
						"Click on Account Profile Tab:Webtop Home Page:Click on Account Profile Tab");
			}
			iClick(Locators.lnkExpandAll, null, "Click on Expand All Link:Account Profile Page:Expand All Link");
			sleep(4000);
			Scroll_Page_Down();
			for (int idx = 0; idx < getLocatorWEList(Locators.lnkDeviceEdit).size(); idx++) {
				jsClick(getLocatorWEList(Locators.lnkDeviceEdit).get(idx));					
				iClick(Locators.chkboxUpdateFields, null,
						"Enable on Enable Update Fields check box:Account Profile Page:Enable Update Fields");
				String uuid = UUID.randomUUID().toString();
				String MACAddress = uuid.substring(uuid.lastIndexOf("-") + 1);
				iEnterText(Locators.txtMACAddress, MACAddress);
				iClick(Locators.btnSave, null, "Click on Save button:Account Profile Page:Save button");
				iClick(Locators.btnSaveAllAndContinue, null,
						"Click on Save All and continue button:Account Profile Page:Save All and continue button");
			}
			iClick(Locators.lnkGroupID, null, "Click on Group ID:Account Profile Tab:Group ID");
			iClick(Locators.lnkInprogressOrder, null, "Click on the Open Order link");
		} catch (Exception e) {
			report.reportHardFailEvent("Update MAC Address", "Update MAC Address Failed!!!", Status.FAIL);
			String eMsg = "Error in Update MAC Address --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}

	public boolean updateTerminatingANI() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.tabAccountProfile, 3)) {
				iClick(Locators.tabAccountProfile, null,
						"Click on Account Profile Tab:Webtop Home Page:Account Profile Tab button");
				iClick(Locators.lnkExpandAll, null, "Click on Expand All Link:Account Profile Page:Expand All Link");
				String BTNNo = getLocatorWEList(Locators.BTNNo).get(0).getText();
				addLocator(Locators.TollFreeTNEditLink, "xpath",
						"//span[contains(.,'" + dataDump.getValue("TollFreeTNNumber")
								+ "')]/../../../../../table[2]/tbody/tr/td/table/tbody/tr/td[2]/a",
						"Edit Link for Toll Free TN");
				iClick(Locators.TollFreeTNEditLink, null,
						"Click on Toll Free TN Edit Link:Account Profile Page:Toll Free TN Edit Link");
				iEnterText_withoutVerify(Locators.txtTerminatingANI, BTNNo,
						"Enter Terminating ANI:Account Profile Page:Terminating ANI");
				iClick(Locators.btnUpdate, null, "Click on Update button:Account Profile Page:Update button");
				iClick(Locators.btnSaveAllAndContinue, null,
						"Click on Save All and continue button:Account Profile Page:Save All and continue button");
				iClick(Locators.lnkGroupID, null, "Click on Group ID:Account Profile Tab:Group ID");
			} else {
				report.reportHardFailEvent("Update Terminating ANI for Toll Free TN",
						"Update Terminating ANI for Toll Free TN failed");
			}
		} catch (Exception e) {
			report.reportHardFailEvent("Update Terminating ANI for Toll Free TN",
					"Update Terminating ANI for Toll Free TN Failed!!!", Status.FAIL);
			String eMsg = "Error in Update Terminating ANI for Toll Free TN --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}

	public boolean getE911Address(String beforeOrAfter) {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.tabAccountProfile, 2)) {
				iClick(Locators.tabAccountProfile, null,
						"Click on account profile tab:Webtop Home Page:Click on account profile tab");
			}
			iClick(Locators.lnkExpandAll, null, "Click on Expand All Link:Account Profile Page:Expand All Link");
			sleep(5000);
			Scroll_Page_Down();
			List<WebElement> ANIList = getLocatorWEList(Locators.lnkANIList);
			String ANINos = "";
			for (int idx = 0; idx < ANIList.size(); idx++) {
				if (idx == ANIList.size() - 1) {
					ANINos = ANINos + ANIList.get(idx).getText();
					dataDump.setValue("ANIList", ANINos);
					log.info("ANI Numbers: " + ANINos);
				} else {
					ANINos = ANINos + ANIList.get(idx).getText() + "|";
				}
			}
			List<WebElement> lnkANIEdit = browser.findElements(By.xpath(
					".//img[contains(@src,'ANI')]/../following-sibling::td/table/tbody/tr/td/table/tbody/tr/td[.='Edit ']"));
			for (int idx = 0; idx < ANIList.size(); idx++) {
				lnkANIEdit = browser.findElements(By.xpath(
						".//img[contains(@src,'ANI')]/../following-sibling::td/table/tbody/tr/td/table/tbody/tr/td[.='Edit ']"));
				String ANIAddressList = "";
				Scroll_Page_Down();
				highlightGreenWE(lnkANIEdit.get(idx));
				iClick(lnkANIEdit.get(idx), null, "Click on EDIT button:Account profile page: Edit Link");
				Scroll_Page_Down();
				Scroll_Page_Down();
				ANIAddressList = ANIAddressList
						+ getLocatorWEList(Locators.txtE911StreetNo).get(0).getAttribute("value") + "|";
				ANIAddressList = ANIAddressList
						+ getLocatorWEList(Locators.txtE911StreeName).get(0).getAttribute("value") + "|";
				ANIAddressList = ANIAddressList + getLocatorWEList(Locators.txtE911Unit).get(0).getAttribute("value")
						+ "|";
				ANIAddressList = ANIAddressList
						+ getLocatorWEList(Locators.txtE911UnitValue).get(0).getAttribute("value") + "|";
				ANIAddressList = ANIAddressList + getLocatorWEList(Locators.txtE911City).get(0).getAttribute("value")
						+ "|";
				ANIAddressList = ANIAddressList + getLocatorWEList(Locators.txtE911Zip).get(0).getAttribute("value");
				log.info("ANI" + (idx + 1) + " Address: " + ANIAddressList);
				dataDump.setValue("ANI" + (idx + 1) + "_Address_" + beforeOrAfter, ANIAddressList);
				iClick(Locators.btnCancel);
				iClick(Locators.btnReturntoInventory);
			}

		} catch (Exception e) {
			report.reportHardFailEvent("Get E-911 Address from ANI", "Getting E-911 Address from ANI is Failed!!!",
					Status.FAIL);
			String eMsg = "Error in Getting E-911 Address from ANI is --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}

	public boolean batchUpdateE911Address(String noOfANIs) {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.tabAccountProfile, 2)) {
				iClick(Locators.tabAccountProfile, null,
						"Click on account profile tab: Home Page:Click on account profile tab");
			}
			iClick(Locators.btnEditInventory, Locators.expandANIs,
					"Click on Edit Inventory Button:Account Profile Page:Edit Inventory Button");
			iClick(Locators.expandANIs, null,
					"Click on Expand ANI Button:Account Profile Page:Expand ANI Button Button");
			String[] ANIList = dataDump.getValue("ANIList").split("\\|");

			for (int idx = 0; idx < Integer.parseInt(noOfANIs); idx++) {
				addLocator(Locators.ANICheckBox, "xpath", "//span[.='" + ANIList[idx] + "']/../input", "ANI Check Box");
				List<WebElement> chkboxList = getLocatorWEList(Locators.ANICheckBox);
				for (int i = 0; i < chkboxList.size(); i++) {

					if (waitForElement(chkboxList.get(i), 1)) {
						iClick(chkboxList.get(i), null, "Enable ANI check box:Account Profile Page:ANI Check Box");
					}
				}
			}
			iClick(Locators.lnkANIEdit, null, "Click on ANI Edit Link:Account Profile Page: ANI Edit Link");

			iClick(Locators.chkboxUpdateAddress, null,
					"Enable Update Address Check box: account Profile Page: Update Address");
			String[] addressInfo = dataSet.getValue("BulkE911AddressUpdateValues").split("\\|");
			iEnterText(Locators.txtE911StreetNo, addressInfo[0]);
			iEnterText(Locators.txtE911StreeName, addressInfo[1]);
			iEnterText(Locators.txtE911Unit, addressInfo[2]);
			iEnterText(Locators.txtE911UnitValue, addressInfo[3]);
			iEnterText(Locators.txtE911City, addressInfo[4]);
			String ZipCode;
			if (addressInfo[5].length() == 4) {
				ZipCode = "0" + addressInfo[5];
			} else
				ZipCode = addressInfo[5];
			iEnterText(Locators.txtE911Zip, ZipCode);
			dataDump.setValue("E911NewAddress", addressInfo[0] + "|" + addressInfo[1] + "|" + addressInfo[2] + "|"
					+ addressInfo[3] + "|" + addressInfo[4] + "|" + ZipCode);
			iClick(Locators.btnUpdate, null, "Click on Update Button:Account Profile Page: Update Button");
		}

		catch (Exception e) {
			report.reportHardFailEvent("Get E-911 Address from ANI", "Getting E-911 Address from ANI is Failed!!!",
					Status.FAIL);
			String eMsg = "Error in Getting E-911 Address from ANI is --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}

	public void verifyE911AddressAfterBulkUpdate() throws InterruptedException {
		if (dataDump.getValue("ANI1_Address_after").equals(dataDump.getValue("E911NewAddress"))
				&& dataDump.getValue("ANI2_Address_after").equals(dataDump.getValue("E911NewAddress"))
				&& dataDump.getValue("ANI3_Address_after").equals(dataDump.getValue("E911NewAddress"))
				&& dataDump.getValue("ANI4_Address_after").equals(dataDump.getValue("ANI4_Address_before"))
				&& dataDump.getValue("ANI5_Address_after").equals(dataDump.getValue("ANI5_Address_before"))) {
			report.reportPassEvent("Verify E911 Address After Bulk Update",
					"Verification of E911 Address After Bulk Update is Passed");
		} else {
			report.reportHardFailEvent("Verify E911 Address After Bulk Update",
					"Verification of E911 Address After Bulk Update is Failed");
		}
	}

	public void verifyNewlyAddedVoiPonExistingUnusedAA() throws InterruptedException {
		if (dataDump.getValue("VOIPList_after").contains(dataDump.getValue("VOIPList_before"))
				&& dataDump.getValue("VOIPList_after").contains(dataDump.getValue("NewVoIPID_RT"))) {
			report.reportPassEvent("Verify newly added AA on Existing Unused AA",
					"Verification of newly added AA on Existing Unused AA is Passed");
		} else {
			report.reportHardFailEvent("Verify newly added AA on Existing Unused AA",
					"Verification of newly added AA on Existing Unused AA is Failed");
		}
	}

	public boolean getVoipList(String beforeOrAfter) {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.tabAccountProfile, 2)) {
				iClick(Locators.tabAccountProfile, null,
						"Click on account profile tab:Webtop Home Page:Click on account profile tab");
			}
			iClick(Locators.lnkExpandAll, null, "Click on Expand All Link:Account Profile Page:Expand All Link");
			sleep(5000);
			Scroll_Page_Down();
			List<WebElement> VOIPList = getLocatorWEList(Locators.lnkVOIPList);
			String VOIPNos = "";
			for (int idx = 0; idx < VOIPList.size(); idx++) {
				if (idx == VOIPList.size() - 1) {
					VOIPNos = VOIPNos + VOIPList.get(idx).getText().trim();
					dataDump.setValue("VOIPList_" + beforeOrAfter, VOIPNos);
					log.info("VOIP Numbers: " + VOIPNos);
				} else {
					VOIPNos = VOIPNos + VOIPList.get(idx).getText().trim() + "|";
				}
			}

		} catch (Exception e) {
			report.reportHardFailEvent("Get VOIP Numbers", "Getting VOIP Numbers from AA is Failed!!!", Status.FAIL);
			String eMsg = "Error in Getting VOIP Numbers --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}

	public boolean getANIList(String beforeOrAfter) {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.tabAccountProfile, 2)) {
				iClick(Locators.tabAccountProfile, null,
						"Click on account profile tab:Webtop Home Page:Click on account profile tab");
			}
			iClick(Locators.lnkExpandAll, null, "Click on Expand All Link:Account Profile Page:Expand All Link");
			sleep(5000);
			Scroll_Page_Down();

			List<WebElement> StdANIList = getLocatorWEList(Locators.lnkStdANIList);
			String ANINos = "";
			for (int idx = 0; idx < StdANIList.size(); idx++) {
				if (idx == StdANIList.size() - 1) {
					ANINos = ANINos + StdANIList.get(idx).getText();
					dataDump.setValue("StdANIList_" + beforeOrAfter, ANINos);
					log.info("ANI Numbers: " + ANINos);
				} else {
					ANINos = ANINos + StdANIList.get(idx).getText() + "|";
				}
			}

			List<WebElement> UCANIList = getLocatorWEList(Locators.lnkUCANIList);
			ANINos = "";
			for (int idx = 0; idx < UCANIList.size(); idx++) {
				if (idx == UCANIList.size() - 1) {
					ANINos = ANINos + UCANIList.get(idx).getText();
					dataDump.setValue("UCANIList_" + beforeOrAfter, ANINos);
					log.info("ANI Numbers: " + ANINos);
				} else {
					ANINos = ANINos + UCANIList.get(idx).getText() + "|";
				}
			}
		} catch (Exception e) {
			report.reportHardFailEvent("Get ANI Numbers", "Getting ANI Numbers is Failed!!!", Status.FAIL);
			String eMsg = "Error in Getting ANI Numbers --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}

	public boolean launchAddInventory() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.tabAccountProfile, 2)) {
				iClick(Locators.tabAccountProfile, null,
						"Click on account profile tab:Webtop Home Page:Click on account profile tab");
			}
			iClick(Locators.lnkExpandAll, null, "Click on Expand All Link:Account Profile Page:Expand All Link");
			sleep(10000);
			Scroll_Page_Down();			
			iClick(Locators.btnAddInventory, null,
					"Click On Add Inventory Button:Account Profile Page:Add Inventory Button");

		} catch (Exception e) {
			report.reportHardFailEvent("Click on Add Inventory", "Click on Add Inventory Failed!!!", Status.FAIL);
			String eMsg = "Error in Click on Add Inventory --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}

	public boolean submitTheOrder() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.btnSaveAllAndContinue, 1)) {
				iClick(Locators.btnSaveAllAndContinue, null,
						"Click on Save All and continue button:Account Profile Page:Save All and continue button");
			}
			iClick(Locators.lnkSubmitThisOrder, null,
					"Click On Submit this Order link:Account Profile Page: Submit this order");
			report.reportPassEvent("Submit the order", "MACD Order Submitted!", Status.PASS);
		} catch (Exception e) {
			report.reportHardFailEvent("Submit the order", "Submit order Failed!!!", Status.FAIL);
			String eMsg = "Error in Submitting order --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}

	public boolean addSeat(String stdOrUCSeat, int seatNo) {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.tabAccountProfile, 2)) {
				iClick(Locators.tabAccountProfile, null,
						"Click on account profile tab:Webtop Home Page:Click on account profile tab");
			}
			iClick(Locators.lnkExpandAll, null, "Click on Expand All Link:Account Profile Page:Expand All Link");
			sleep(5000);
			Scroll_Page_Down();
			// Delete the already initiated MACD order if exists
			/*
			 * if (isLocatorVisible(Locators.lnkDeleteOrder, 2)) {
			 * iClick(Locators.lnkDeleteOrder, null,
			 * "Click On Delete Link:Account Profile Page:Delete Link"); }
			 */
			if (stdOrUCSeat.equalsIgnoreCase("standard")) {
				iClick(getLocatorWEList(Locators.lnkAddStdSeat).get(seatNo - 1), null,
						"Click on Add Link of Standard Seat for Seat number: " + seatNo);
			} else if (stdOrUCSeat.equalsIgnoreCase("UC")) {
				iClick(getLocatorWEList(Locators.lnkAddUCSeat).get(seatNo - 1), null,
						"Click on Add Link of UC Seat for Seat number: " + seatNo);
			}

		} catch (Exception e) {
			report.reportHardFailEvent("Add Seat on standard/UC seat", "Add Seat on standard/UC seat Failed!!!",
					Status.FAIL);
			String eMsg = "Error in Add Seat on standard/UC seat --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}

	public String getBTNNo() {
		try {
			String BTNNo = getLocatorWEList(Locators.BTNNo).get(0).getText();
			dataDump.setValue("BTNNo_RT", BTNNo);
			return BTNNo;
		} catch (Exception e) {
			return null;
		}
	}

	public boolean terminateSeat(String stdOrUCSeat, int seatNo) {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.tabAccountProfile, 2)) {
				iClick(Locators.tabAccountProfile, null,
						"Click on account profile tab:Webtop Home Page:Click on account profile tab");
			}
			iClick(Locators.lnkExpandAll, null, "Click on Expand All Link:Account Profile Page:Expand All Link");
			sleep(5000);
			Scroll_Page_Down();
			// Delete the already initiated MACD order if exists
			/*
			 * if (isLocatorVisible(Locators.lnkDeleteOrder, 2)) {
			 * iClick(Locators.lnkDeleteOrder, null,
			 * "Click On Delete Link:Account Profile Page:Delete Link"); }
			 */
			if (stdOrUCSeat.equalsIgnoreCase("standard")) {
				iClick(getLocatorWEList(Locators.lnkEditStdSeat).get(seatNo - 1), null,
						"Click on Edit Link of Standard Seat for Seat number: " + seatNo);
			} else if (stdOrUCSeat.equalsIgnoreCase("UC")) {
				iClick(getLocatorWEList(Locators.lnkEditUCSeat).get(seatNo - 1), null,
						"Click on Edit Link of UC Seat for Seat number: " + seatNo);
			}
			iEnterText(Locators.txtTerminatingDate, getCurrentDate());
			iClick(Locators.btnUpdate);
			dataDump.setValue("OrderStatus", "Partialy-Disconnected");
		} catch (Exception e) {
			report.reportHardFailEvent("Terminate a standard/UC seat", "Terminate a standard/UC seat Failed!!!",
					Status.FAIL);
			String eMsg = "Error in Terminate a standard/UC seat --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}

	public boolean batchDisconnect(String ANINosList) {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.tabAccountProfile, 2)) {
				iClick(Locators.tabAccountProfile, null,
						"Click on account profile tab: Home Page:Click on account profile tab");
			}
			iClick(Locators.btnEditInventory, Locators.expandANIs,
					"Click on Edit Inventory Button:Account Profile Page:Edit Inventory Button");
			iClick(Locators.expandANIs, null,
					"Click on Expand ANI Button:Account Profile Page:Expand ANI Button Button");
			String[] ANIList = ANINosList.split("\\|");

			for (int idx = 0; idx < ANIList.length; idx++) {
				addLocator(Locators.ANICheckBox, "xpath", "//span[.='" + ANIList[idx] + "']/../input", "ANI Check Box");
				List<WebElement> chkboxList = getLocatorWEList(Locators.ANICheckBox);
				for (int i = 0; i < chkboxList.size(); i++) {
					if (waitForElement(chkboxList.get(i), 1)) {
						iClick(chkboxList.get(i), null, "Enable ANI check box:Account Profile Page:ANI Check Box");
					}
				}
			}
			iClick(Locators.lnkANIEdit, null, "Click on ANI Edit Link:Account Profile Page: ANI Edit Link");
			iEnterText(Locators.txtTerminatingDate, getCurrentDate());
			iClick(Locators.btnUpdate, null, "Click on Update Button:Account Profile Page: Update Button");
		} catch (Exception e) {
			report.reportHardFailEvent("Batch disconnect", "Batch disconnect is Failed!!!", Status.FAIL);
			String eMsg = "Error in Batch disconnect --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}

	public boolean getDECTBaseNameAndHandsetIPEI(String stdOrUCSeat, int seatNo) {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.tabAccountProfile, 1)) {
				iClick(Locators.tabAccountProfile, null,
						"Click on account profile tab:Webtop Home Page:Click on account profile tab");
			}
			iClick(Locators.lnkExpandAll, null, "Click on Expand All Link:Account Profile Page:Expand All Link");
			sleep(5000);
			Scroll_Page_Down();
			if (stdOrUCSeat.equalsIgnoreCase("standard")) {
				iClick(getLocatorWEList(Locators.lnkEditStdSeatDevice).get(seatNo - 1), null,
						"Click on Edit Link of Standard Seat Devcie for Seat number: " + seatNo);
			} else if (stdOrUCSeat.equalsIgnoreCase("UC")) {
				iClick(getLocatorWEList(Locators.lnkEditUCSeatDevice).get(seatNo - 1), null,
						"Click on Edit Link of UC Seat Device for Seat number: " + seatNo);
			}
			iClick(Locators.chkboxUpdateFields);
			dataDump.setValue("DECTHandSetIPEI", getLocatorAttribute(Locators.txtDECTHandSetIPEI, "value"));
			dataDump.setValue("DECTBaseName", getLocatorAttribute(Locators.txtDECTBaseName, "value"));
			iClick(Locators.btnCancel);
			iClick(Locators.btnReturntoInventory);
		} catch (Exception e) {
			report.reportHardFailEvent("Get DECT Bane Name and Handset IPEI standard/UC seat",
					"Get DECT Bane Name and Handset IPEI standard/UC seat Failed!!!", Status.FAIL);
			String eMsg = "Error in Getting DECT Bane Name and Handset IPEI standard/UC seat --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}

	public boolean removeEAOnly() {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.tabAccountProfile, 1)) {
				iClick(Locators.tabAccountProfile, null,
						"Click on account profile tab:Webtop Home Page:Click on account profile tab");
			}
			iClick(Locators.lnkExpandAll, null, "Click on Expand All Link:Account Profile Page:Expand All Link");
			sleep(5000);
			Scroll_Page_Down();
			iClick(Locators.lnkEditEAO);
			iEnterText(Locators.txtTerminatingDate, getCurrentDate());
			report.reportPassEvent("Entering the termination date for EAO User",
					"Entering the termination date for EAO User");
			iClick(Locators.btnUpdate);
			iClick(Locators.btnReturntoInventory);
		} catch (Exception e) {
			report.reportHardFailEvent("Removing EAO user", "Removing EAO user Failed!!!", Status.FAIL);
			String eMsg = "Error in Removing EAO user --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}

	public boolean editSeat(String stdOrUCSeat, int seatNo) {
		mstatus = true;
		try {
			if (isLocatorVisible(Locators.tabAccountProfile, 1)) {
				iClick(Locators.tabAccountProfile, null,
						"Click on account profile tab:Webtop Home Page:Click on account profile tab");
			}
			iClick(Locators.lnkExpandAll, null, "Click on Expand All Link:Account Profile Page:Expand All Link");
			sleep(5000);
			Scroll_Page_Down();
			if (stdOrUCSeat.equalsIgnoreCase("standard")) {
				iClick(getLocatorWEList(Locators.lnkEditStdSeat).get(seatNo - 1), null,
						"Click on Edit Link of Standard Seat for Seat number: " + seatNo);
			} else if (stdOrUCSeat.equalsIgnoreCase("UC")) {
				iClick(getLocatorWEList(Locators.lnkEditUCSeat).get(seatNo - 1), null,
						"Click on Edit Link of UC Seat for Seat number: " + seatNo);
			}
		} catch (Exception e) {
			report.reportHardFailEvent("Edit Seat(ANI)", "Edit Seat(ANI) Failed!!!", Status.FAIL);
			String eMsg = "Error in Edit Seat(ANI) --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}

	public boolean changeUser(String typeOfUser) {
		mstatus = true;
		try {
			// Change User
			iSelectValue(Locators.dropdwnTypeOfUser, typeOfUser);
			report.reportPassEvent("Change from 'TA/EA' to: " + typeOfUser,
					"Changed successfully from 'TA/EA' to: " + typeOfUser);
			iClick(Locators.btnUpdate, null, "Click on Update Button:Services Page:Update");
			iClick(Locators.btnReturntoInventory);
		} catch (Exception e) {
			report.reportHardFailEvent("Change User", "Change from 'TA/EA' to: " + typeOfUser + " Failed!!!",
					Status.FAIL);
			String eMsg = "Error in Change User --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean changeEmailId(String emailId) {
		mstatus = true;
		try {
			// Change Email Id
			iEnterText(Locators.txtEmailAddressForUser, emailId);
			report.reportPassEvent("Changing the Email Address for the user",
					"Changed successfully Email address for User to : " + emailId);
			iClick(Locators.btnUpdate, null, "Click on Update Button:Services Page:Update");
			iClick(Locators.btnReturntoInventory);
		} catch (Exception e) {
			report.reportHardFailEvent("Changing the Email Address for the user", "Changing the Email Address for the user Failed!!!",
					Status.FAIL);
			String eMsg = "Error in Changing the Email Address for the user --- " + e.getMessage();
			log.error(eMsg);
			mstatus = false;
		}
		return mstatus;
	}

	public boolean verifyBundle(String bundleRange) throws InterruptedException {
		String loc = null;
		switch (bundleRange) {
		case "1-9":
			loc = Locators.lblUCBundle1To9;
			break;
		case "10-19":
			loc = Locators.lblUCBundle10To19;
			break;

		}
		iClick(Locators.tabAccountProfile);
		if (isLocatorVisible(loc, 2)) {
			report.reportPassEvent("Bundle verification in Account profile page",
					"Bundle verification passed as expected");
			return true;
		} else {
			report.reportSoftFailEvent("Bundle verification in Account profile page",
					"Bundle verification Failed and it is not expected");
			return false;
		}
	}
	
	public boolean verifyAdditionalVMBundle() throws InterruptedException {			
		iClick(Locators.tabAccountProfile);		
		if (waitForElement(getLocatorWEList(Locators.txtVMBundle).get(0), 2)) {
			report.reportPassEvent("Bundle verification in Account profile page for Voice Mail",
					"Bundle verification in Account profile page for Voice Mail passed as expected");
			return true;
		} else {
			report.reportSoftFailEvent("Bundle verification in Account profile page for Voice Mail",
					"Bundle verification in Account profile page for Voice Mail Failed");
			return false;
		}
	}
	
	public boolean verifyCallQueueAgentBasicBundle() throws InterruptedException {			
		iClick(Locators.tabAccountProfile);		
		if (waitForElement(getLocatorWEList(Locators.txtCQBAgentBundle).get(0), 2)) {
			report.reportPassEvent("Bundle verification in Account profile page for CQB Agent",
					"Bundle verification in Account profile page for  CQB Agent passed as expected");
			return true;
		} else {
			report.reportSoftFailEvent("Bundle verification in Account profile page for CQB Agent",
					"Bundle verification in Account profile page for CQB Agent Failed");
			return false;
		}
	}

	public void editBundle(String bundleName, String BundlePrice) throws Exception {
		if (isLocatorVisible(Locators.tabAccountProfile, 1)) {
			iClick(Locators.tabAccountProfile, null,
					"Click on account profile tab:Webtop Home Page:Click on account profile tab");
		}
		iClick(Locators.lnkExpandAll, null, "Click on Expand All Link:Account Profile Page:Expand All Link");
		sleep(5000);
		Scroll_Page_Down();
		if (bundleName.equals("10-19")) {
			iClick(getLocatorWEList(Locators.lnkEditBundle10To19).get(0), null,
					"Click On Edit Button: Account Profile Page: Edit Button");
		}
		new ServicesPage(context).updateBundlePrice(BundlePrice);
	}

	public boolean verifyBundleIfTerminated(String bundleRange) throws InterruptedException {
		String loc = null;
		switch (bundleRange) {
		case "1-9":
			loc = Locators.lblUCBundle1To9;
			break;
		case "10-19":
			loc = Locators.lblUCBundle10To19;
			break;

		}
		iClick(Locators.tabAccountProfile);
		if (isLocatorVisible(loc, 2) & getLocatorAttribute(loc, "class").equals("greyback")) {
			report.reportPassEvent("Bundle: " + bundleRange + " terminated in Account profile page",
					"Bundle:" + bundleRange + " is termianted as expected");
			return true;
		} else {
			report.reportSoftFailEvent("Bundle: " + bundleRange + " not terminated in Account profile page",
					"Bundle:" + bundleRange + " is not termianted as expected");
			return false;
		}
	}

	public boolean verifyBundleMRCAndNRCValue(String bundleName, String MRCValue, String NRCValue)
			throws InterruptedException {
		try {
			if (!isLocatorVisible(Locators.lnkExpandAll, 1)) {
				iClick(Locators.tabAccountProfile);
				iClick(Locators.lnkExpandAll);
				sleep(5000);
			}

			WebElement txtMRC = browser.findElement(By
					.xpath("//img[contains(@src,'B.bmp')]//following::span[contains(.,'Seat Based Unified Communication Seats "
							+ bundleName + " (OrderId:')]//following::tr[1]/td[2]"));
			WebElement txtNRC = browser.findElement(By
					.xpath("//img[contains(@src,'B.bmp')]//following::span[contains(.,'Seat Based Unified Communication Seats "
							+ bundleName
							+ " (OrderId:')]//following::span[contains(.,'Seat Fee - NRC ')][1]//following::tr[1]/td[2]"));
			if (iVerifyText(txtMRC, MRCValue) && iVerifyText(txtNRC, NRCValue)) {
				report.reportPassEvent("Bundle: " + bundleName + " MRC and NRC verification in Account profile page",
						"Bundle:" + bundleName + " MRC/NRC Values is diplayed as expected");
				return true;
			} else {
				report.reportSoftFailEvent(
						"Bundle: " + bundleName + " MRC and NRC verification in Account profile page",
						"Bundle:" + bundleName + " MRC/NRC Values is not diplayed as expected");
				return false;
			}
		} catch (Exception ex) {
			log.error("NRC MRC values verification failed in Account profile Page");
			report.reportSoftFailEvent("Bundle: " + bundleName + " MRC and NRC verification in Account profile page",
					"Bundle:" + bundleName + " MRC/NRC Value verification failed");
			return false;
		}
	}

	public void verifyEdgeDevice(String edgeDeviceName, String licQty) throws Exception {
		try {
			if (isLocatorVisible(Locators.tabAccountProfile, 1)) {
				iClick(Locators.tabAccountProfile, null,
						"Click on account profile tab:Webtop Home Page:Click on account profile tab");
			}
			iClick(Locators.lnkExpandAll, null, "Click on Expand All Link:Account Profile Page:Expand All Link");
			Scroll_Page_Down();

			addLocator(Locators.lblEdgeMarc, "xpath",
					"//span[contains(text(),'" + edgeDeviceName + "-" + licQty + " WAN Calls (OrderId:')]",
					"Edit Link for Toll Free TN");
			if (isLocatorVisible(Locators.lblEdgeMarc, 20)) {
				report.reportPassEvent("Verification of Edge Device in Webtop", "Edge Device is displayed as expected");
			} else
				report.reportHardFailEvent("Verification of Edge Device in Webtop",
						"Edge Device is not displayed as expected");
		} catch (Exception ex) {
			log.error("Verification of Line Quantity in Webtop Failed");
			report.reportHardFailEvent("Verification of Edge Device in Webtop",
					"EdgeDevice is not displayed as expected");
		}
	}

	public void verifyLineQuantity(String quantity) throws Exception {
		try {
			if (isLocatorVisible(Locators.tabAccountProfile, 1)) {
				iClick(Locators.tabAccountProfile, null,
						"Click on account profile tab:Webtop Home Page:Click on account profile tab");
			}
			iClick(Locators.lnkExpandAll, null, "Click on Expand All Link:Account Profile Page:Expand All Link");
			Scroll_Page_Down();
			if (iVerifyText(Locators.lblLineCount, quantity)) {
				report.reportPassEvent("Verification of Line Quantity in Webtop",
						"Line quantity  is displayed as expected");
			} else
				report.reportHardFailEvent("Verification of Line Quantity in Webtop",
						"Line Quantity is not displayed as expected");
		} catch (Exception ex) {
			log.error("Verification of Line Quantity in Webtop Failed");
			report.reportHardFailEvent("Verification of Line Quantity in Webtop",
					"Line Quantity is not displayed as expected");

		}

	}
	
	public void verifyProductCatalogAmount(String expectedAmount) throws Exception {
		try {
			if (testLocatorClickable(Locators.tabOrderProfile, 1)!=null) {
				iClick(Locators.tabOrderProfile, null,
						"Click on account profile tab:Webtop Home Page:Click on account profile tab");
			}
			iValidateLocatorValue(Locators.txtproductCatalog, "Polycom VVX 311 HD 3 or 5 yr Term (OrderId:", ComparisonType.SUBSTR, "Product catalog verification for Polycom 311 device");
			iValidateLocatorValue(Locators.txtproductCatalogAmount, expectedAmount, ComparisonType.EQUAL, "Product catalog amount verification for Polycom 311 device");
		} catch (Exception ex) {
			log.error("Verification of product catalog in account profile page Failed");
			report.reportHardFailEvent("Verification of product catalog in account profile page",
					"Verification of product catalog in account profile page not displayed as expected");

		}

	}
	
	public void verifyUnUsedAAVOIPCount(int expectedCount) throws Exception {
		Boolean  res ;
		try {			
			if (isLocatorVisible(Locators.tabAccountProfile, 1)) {
				iClick(Locators.tabAccountProfile, null,
						"Click on account profile tab:Webtop Home Page:Click on account profile tab");
			}
			iClick(Locators.lnkExpandAll, null, "Click on Expand All Link:Account Profile Page:Expand All Link");
			Scroll_Page_Down();			
			List<WebElement> unUsedAAList = getLocatorWEList(Locators.txtAAVOIPList);
			res = (unUsedAAList.size() == expectedCount) ;                             
            iReport("", "Validate the Number of VOIP Extensions with Unused AA in account profile Tab", res ? "Verification of VOIP Extension AA count is Passed":"Verification of VOIP Extension, actual: " + unUsedAAList.size() +", But expected: " + expectedCount , res);
		} catch (Exception ex) {
			log.error("Verification of Number of VOIP Extensions with Unused AA in account profile Tab page Failed");
			report.reportHardFailEvent("Verification Number of VOIP Extensions with Unused AA in account profile Tab page",
					"Number of VOIP Extensions with Unused AA in account profile Tab is not displayed as expected");

		}

	}
}

