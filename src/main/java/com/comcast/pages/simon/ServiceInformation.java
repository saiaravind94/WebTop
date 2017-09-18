package com.comcast.pages.simon;

import org.apache.log4j.Logger;
import com.comcast.commons.WebtopPage;
import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.utils.ComparisonType;
import com.comcast.utils.TestSettings;
import com.comcast.utils.ValidationType;
import com.comcast.utils.DataTable;
import com.comcast.utils.IUserDetails;

/**
 * Represents default page of the application
 * 
 */
public class ServiceInformation extends WebtopPage {
	FrameworkContext context;
	DataTable dataSet;
	Logger log = Logger.getLogger(ServiceInformation.class);
	private boolean mstatus;

	public static class Locators {
		public static final String drpESGLicenseQty = "drpESGLicenseQty";
		public static final String drpInternationalLongDistance = "drpInternationalLongDistance";
		public static final String drpGrpbtn = "drpGrpbtn";
		public static final String btnEnterpriseDialling = "btnEnterpriseDialling";
		public static final String txtGenerationSpeed = "txtGenerationSpeed";
		public static final String btnGenerateExtn = "btnGenerateExtn";
		public static final String btnUseTNavailable = "btnUseTNavailable";
		public static final String txtContactName = "txtContactName";
		public static final String txtContactPhone = "txtContactPhone";
		public static final String txtContactEmail = "txtContactEmail";
		public static final String lnkServicesInformation = "lnkServicesInformation";
		public static final String elmtSavesuccessful = "elmtSavesuccessful";
		public static final String elmtGenerateextnssuccessful = "elmtGenerateextnssuccessful";
		public static final String btnNext = "btnNext";
		public static final String btnSave = "btnSave";
		public static final String drpExtnDialingDigits = "drpExtnDialingDigits";
		public static final String txtNoOfStdSeats = "txtNoOfStdSeats";
		public static final String txtNoOfUCSeats = "txtNoOfUCSeats";
		public static final String totalNewLines = "totalNewLines";
		public static final String drpESGModel = "drpESGModel";
		public static final String btnSettings = "btnSettings";
		public static final String lnkOverideSBLines = "lnkOverideSBLines";
		public static final String btnAddVMbox = "btnAddVMbox";
		public static final String txtVoicemailName = "txtVoicemailName";
		public static final String btnDelVMBox = "btnDelVMBox";
		public static final String btnDelVMRule = "btnDelVMRule";
		public static final String inpPrimaryContactName = "inpPrimaryContactName";
		public static final String inpPrimBusinessPhone = "inpPrimBusinessPhone";
		public static final String inpPrimEmail = "inpPrimEmail";
		public static final String VMExpand = "VMExpand";
		public static final String txtFName = "txtFName";
		public static final String txtLName = "txtLName";
		public static final String txtExtension = "txtExtension";
		public static final String btnSaveVM = "btnSaveVM";
	}

	public ServiceInformation(FrameworkContext context) {
		super(context, "ServiceInformation");
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

	public boolean Signout() {
		return mstatus;
	}

	public boolean serviceInformation() {
		mstatus = true;
		try {
			if (waitForElement(testLocatorClickable(Locators.lnkServicesInformation), 10)) {
				iClick(Locators.lnkServicesInformation, null, "Click Services Information");
			}

			if (!testLocatorClickable(Locators.drpESGLicenseQty, 2).getAttribute("class").contains("disabled")) {
				testLocatorClickable(Locators.drpESGLicenseQty);
				iClick(Locators.drpESGLicenseQty, null, "Choose ESG License quantity");
				sleep(1000);
				selectvalue_dropdown("div", dataSet.getValue("ESGLicenseQuantity"), false);
			}
			dataDump.setValue("NoOfStandardSeats", getLocatorAttribute(Locators.txtNoOfStdSeats, "value"));
			dataDump.setValue("NoOfUCSeats", getLocatorAttribute(Locators.txtNoOfUCSeats, "value"));
			iClick(Locators.drpInternationalLongDistance, null, "Click internation long distance");
			sleep(1000);
			selectvalue_dropdown("div", "Domestic Only", false);
			iClick(Locators.drpGrpbtn, null, "Click Group button");
			selectvalue_dropdown("span", "TABTN", true);
			Scroll_Page_Down();
			iClick(Locators.btnEnterpriseDialling, null, "Click on Enterprise Dialling");
			if (!iVerifyText(Locators.drpExtnDialingDigits, dataSet.getValue("ExtensionDialingDigits"))) {
				iClick(Locators.drpExtnDialingDigits, null, "Click on Extension Dialling Drop down");
				selectvalue_dropdown("div", dataSet.getValue("ExtensionDialingDigits"), false);
			}
			iEnterText(Locators.txtGenerationSpeed, dataSet.getValue("ExtensionGenerationSeed"),
					"Enter Extension Generation seed");
			iClick(Locators.btnGenerateExtn, null, "Click on Generate Extensions");
			testLocatorVisible(Locators.elmtGenerateextnssuccessful);
			iClick(Locators.btnUseTNavailable, null, "Click on Use TN Available");
			scrollDown();
			iClick(Locators.btnSave, null, "Click on Save");
			testLocatorVisible(Locators.elmtSavesuccessful);
			report.reportPassEvent("Service Information", "Service Information Entered");
		} catch (Exception Ex) {
			report.reportHardFailEvent("Service Information", "Service Information Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Service Information in SIMON Failed!!! " + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public boolean verifyESGModelAndESGLicQtyAndLinesAreDisabled() {
		try {
			if (waitForElement(testLocatorClickable(Locators.lnkServicesInformation), 10)) {
				iClick(Locators.lnkServicesInformation, null, "Click Services Information");
			}
			String esgModel = getLocatorAttribute(Locators.drpESGModel, "class");
			String esgLicQty = getLocatorAttribute(Locators.drpESGLicenseQty, "class");
			String newLines = getLocatorAttribute(Locators.totalNewLines, "class");
			if (esgModel.contains("disabled") && esgLicQty.contains("disabled") && newLines.contains("disabled")) {
				report.reportPassEvent("Verification of the ESG Model,ESG license qty,Total new Lines are Disabled",
						"ESG Model,ESG license qty,Total new Lines are Disabled as expected");

			} else {
				report.reportHardFailEvent("Verification of the ESG Model,ESG license qty,Total new Lines are Disabled",
						"ESG Model,ESG license qty,Total new Lines are not Disabled as expected");
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verification of the ESG Model,ESG license qty,Total new Lines are Disabled",
					"ESG Model,ESG license qty,Total new Lines are not Disabled as expected" + Ex.getMessage());
			eMsg = report.getMessage()
					+ "Verification of the ESG Model,ESG license qty,Total new Lines are Disabled in SIMON Failed!!! "
					+ Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public boolean updateESGModelAndESGLicQtyAndLines(String newLines, String esgModel, String esgLicense) {
		mstatus = true;
		try {

			iClick(Locators.btnSettings);
			iClick(Locators.lnkOverideSBLines);
			iEnterText(Locators.totalNewLines, newLines);
			iClick(Locators.drpESGModel);
			selectvalue_dropdown("div", esgModel, false);
			iClick(Locators.drpESGLicenseQty);
			selectvalue_dropdown("div", esgLicense, false);
			iClick(Locators.btnSave, null, "Click on Save");
			testLocatorVisible(Locators.elmtSavesuccessful);
			report.reportPassEvent("Updating ESGModel,ESGLicQty,Lines fields",
					"ESGModel,ESGLicQty,Lines fields are updated");

		} catch (Exception Ex) {
			report.reportHardFailEvent("Updating ESGModel,ESGLicQty,Lines fields",
					"Update ESGModel,ESGLicQty,Lines fields Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Updating ESGModel,ESGLicQty,Lines fields in SIMON Failed!!! "
					+ Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public boolean addVoicemailinServiceInformation(String VMName) {
		try {
			if (waitForElement(testLocatorClickable(Locators.lnkServicesInformation), 10)) {
				iClick(Locators.lnkServicesInformation, null, "Click Services Information");
				waitForSpinnerComplete();
			}
			if (testLocatorClickable(Locators.btnAddVMbox, 20) != null) {
				jsClick(getLocatorWEList(Locators.btnAddVMbox).get(0));
			}
			sleep(5000);
			iValidateLocatorValue(Locators.txtVoicemailName, VMName, ComparisonType.EQUAL,
					"Add VM in Service Information is successful");

		} catch (Exception Ex) {
			report.reportHardFailEvent("Verification of Add VM in Service Information",
					"Verification of Add VM in Service Information Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Verification of Add VM in Service Information Failed!!! " + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}
	
	public boolean addVoiceMailInServiceInformation() {
		try {
			if (waitForElement(testLocatorClickable(Locators.lnkServicesInformation), 10)) {
				iClick(Locators.lnkServicesInformation, null, "Click Services Information");
				waitForSpinnerComplete();
			}
			if (testLocatorClickable(Locators.btnAddVMbox, 20) != null) {
				jsClick(getLocatorWEList(Locators.btnAddVMbox).get(0));
			}
			sleep(5000);
			if(isLocatorVisible(Locators.VMExpand, 1))
			{
				iClick(Locators.VMExpand);
				iEnterText(Locators.txtFName, "VMFName");
				iEnterText(Locators.txtLName, "VMLName");
				checkLocatorClickable(Locators.txtExtension).sendKeys(dataSet.getValue("ExtensionGenerationSeed") + randomNumber(2));
				iClick(Locators.btnSaveVM, null, "Click on Save");
				testLocatorVisible(Locators.elmtSavesuccessful);
				report.reportPassEvent("Adding additional Voice Mail Box",
						"Adding additional Voice Mail Box Passed");				
				
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verification of Add VM in Service Information",
					"Verification of Add VM in Service Information Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Verification of Add VM in Service Information Failed!!! " + Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}

	public boolean deleteVoicemailinServiceInformation(String VMName) {
		try {
			if (testLocatorClickable(Locators.btnDelVMBox, 20) != null) {
				jsClick(getLocatorWEList(Locators.btnDelVMBox).get(0));
			} else {
				if (waitForElement(testLocatorClickable(Locators.lnkServicesInformation), 10)) {
					iClick(Locators.lnkServicesInformation, null, "Click Services Information");
					waitForSpinnerComplete();
					if (testLocatorClickable(Locators.btnDelVMBox, 20) != null) {
						jsClick(getLocatorWEList(Locators.btnDelVMBox).get(0));
					}
				}
			}
			iClick(Locators.btnDelVMRule);
			sleep(5000);
			iValidateLocatorProperty(Locators.txtVoicemailName, ValidationType.NOTVISIBLE,
					"Voicemail Delete Operation is successful");

		} catch (Exception Ex) {
			report.reportHardFailEvent("Verification of Delete VM Functionality in Service Information",
					"Verification of Delete VM Functionality in Service Information Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Verification of Delete VM in Service Information Failed!!! "
					+ Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}
	public boolean verifyParametersforCustomerContactforRecording(String contactName) {
		try {
			if (waitForElement(testLocatorClickable(Locators.lnkServicesInformation), 10)) {
				iClick(Locators.lnkServicesInformation, null, "Click Services Information");
			}			
			iValidateAttributeValue(Locators.inpPrimaryContactName, "value",contactName, ComparisonType.SUBSTR, "Verify ContactName displayed for Customer Contact for Recording");
			iValidateAttributeValue(Locators.inpPrimBusinessPhone,"value", "", ComparisonType.NOTEQUAL, "Verify ContactPhone displayed for Customer Contact for Recording");	
			iValidateAttributeValue(Locators.inpPrimEmail,"value", "", ComparisonType.NOTEQUAL, "Verify Email displayed for Customer Contact for Recording");
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verification of Auto-populated Field Parameters for Customer Contact for Recording Section in Service Information ",
					"" + Ex.getMessage());
			eMsg = report.getMessage()
					+ "Verification of Auto-populated Field Parameters for Customer Contact for Recording Section in Service Information Failed!!! "
					+ Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}
	public boolean voicemailNegetiveScenario() {
		try {
			
			if (waitForElement(testLocatorClickable(Locators.lnkServicesInformation), 10)) {
					iClick(Locators.lnkServicesInformation, null, "Click Services Information");
					waitForSpinnerComplete();
					iValidateLocatorProperty(Locators.txtVoicemailName, ValidationType.NOTVISIBLE,
							"Voicemail Box  is not available");
				}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verify if Voicemail Box  is displayed by default in Service Information for SOA without any VM BOX",
					"Voicemail Box  is displayed by default in Service Information for SOA without any VM BOX-Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Voicemail Box  is displayed by default in Service Information for SOA without any VM BOX-Failed!!! "
					+ Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}
	public boolean verifyExetentionGenerationSeed() {
		try {
			
			if (waitForElement(testLocatorClickable(Locators.lnkServicesInformation), 10)) {
					iClick(Locators.lnkServicesInformation, null, "Click Services Information");
					waitForSpinnerComplete();				
					iValidateLocatorProperty(Locators.txtGenerationSpeed, ValidationType.DISPLAYED,
							"Extention Generation Seed is displayed as expected");		
					
				}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verify if the Extention Generation Seed is displayed in Service Information",
					"Extention Generation Seed is not displayed-Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Extention Generation Seed is not displayed in Service Information-Failed!!!"
					+ Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}
	public boolean verifyUseTNIfAvailableInServiceInformation() {
		try {	
			if (waitForElement(testLocatorClickable(Locators.lnkServicesInformation), 10)) {
					iClick(Locators.lnkServicesInformation, null, "Click Services Information");
					waitForSpinnerComplete();				
					iValidateLocatorProperty(Locators.btnUseTNavailable, ValidationType.DISPLAYED,
							"Verify use TN if available is displayed in Service Information");							
				}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verify use TN if available is displayed in Service Information",
					"Use TN if available is displayed in Service Information-Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Use TN if available is displayed in Service Information-Failed"
					+ Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}
	public boolean verifyTotalNewLines(String newLines) {
		try {	
			if (waitForElement(testLocatorClickable(Locators.lnkServicesInformation), 10)) {
					iClick(Locators.lnkServicesInformation, null, "Click Services Information");
					waitForSpinnerComplete();	
					iValidateAttributeValue(Locators.totalNewLines, "value", newLines, ComparisonType.EQUAL, "Verify the new lines Displayed in the Service Information");		
				}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Verify the new lines Displayed in the Service Information",
					"New lines is not displayed as expected in the Service Information-Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "New lines is not displayed as expected in the Service Information-Failed"
					+ Ex.getMessage();
			mstatus = false;
			log.error(eMsg);
		}
		return mstatus;
	}	
}
