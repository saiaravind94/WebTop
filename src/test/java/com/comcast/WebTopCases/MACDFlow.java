package com.comcast.WebTopCases;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.comcast.pages.webtop.*;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;

public class MACDFlow extends MultiSiteFlow {
	DataTable dataSet;
	String eMsg = "";	

	Logger log = Logger.getLogger(MACDFlow.class);

	@Test(priority = 71000, groups={"MACD", "All"})
	public void errorDeterminingOrderType() throws Exception {
		
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		ErrorDeterminingOrderType errorDeterminingOrderType = new ErrorDeterminingOrderType(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
			}
			webTopMainPage.searchFor("Group ID");
			errorDeterminingOrderType.errorDeterminingOrderType();
		} catch (Exception Ex) {
			report.reportHardFailEvent("Retry Determining Order Type", "Retry Determining Order Type Failed!!!",
					Status.FAIL);
			String eMsg = "Error in Retry Determining Order Type --- " + Ex.getMessage();
			log.error(eMsg);
			
		}
	}

	
	@Test(priority = 90100, groups={"MACD", "All"})
	public void SOA_Or_SDW_Required_MACDFlow() throws Exception {
		SOA_Or_SDW_Required();
	}

	@Test(priority = 90200, groups={"MACD", "All"})
	public void prequalCheckList_MACDFlow() throws Exception {
		prequalCheckList();
	}

	@Test(priority = 90300, groups={"MACD", "All"})
	public void reviewOrderForAccuracyAndAcceptance_MACDFlow() throws Exception {
		reviewOrderForAccuracyAndAcceptance();
	}

	@Test(priority = 90400, groups={"MACD", "All"})
	public void SDPMCompleteDesignReview_MACDFlow() throws Exception {
		SDPMCompleteDesignReview();
	}

	@Test(priority = 90500, groups={"MACD", "All"})
	public void createAndEnterDSTOrDSGAccountNumber_MACDFlow() throws Exception {
		createAndEnterDSTOrDSGAccountNumber();
	}

	@Test(priority = 90600, groups={"MACD", "All"})
	public void errorValidatingCSGOrDSTFormData_MACDFlow() throws Exception {
		errorValidatingCSGOrDSTFormData();
	}
	
	@Test(priority = 90650, groups={"MACD", "All"})
	public void reviewAndCorrectSFID_MACDFlow() throws Exception {
		reviewAndCorrectSFID();
	}

	@Test(priority = 90700, groups={"MACD", "All"})
	public void scheduleDOIOrDOC_MACDFlow() throws Exception {
		scheduleDOIOrDOC();
	}

	@Test(priority = 90800, groups={"MACD", "All"})
	public void SOAOrderComplete_MACDFlow() throws Exception {
		SOAOrderComplete();
	}

	@Test(priority = 90900, groups={"MACD", "All"})
	public void webTopLogout_MACDFlow() throws Exception {
		webTopLogout();
	}

	@Test(priority = 91000, groups={"MACD", "All"})
	public void SIMONLogin_MACDFlow() throws Exception {
		SIMONLogin();
	}

	@Test(priority = 91100, groups={"MACD", "All"})
	public void SFOpportunityIDSearch_MACDFlow() throws Exception {
		SFOpportunityIDSearch();
	}

	@Test(priority = 91200, groups={"MACD", "All"})
	public void customerDetails_MACDFlow() throws Exception {
		customerDetails();
	}

	@Test(priority = 91300, groups={"MACD", "All"})
	public void orderInformation_MACDFlow() throws Exception {
		orderInformation();
	}

	@Test(priority = 91400, groups={"MACD", "All"})
	public void serviceInformation_MACDFlow() throws Exception {
		serviceInformation();
	}

	@Test(priority = 91500, groups={"MACD", "All"})
	public void callerIDInformation_MACDFlow() throws Exception {
		callerIDInformation();
	}

	@Test(priority = 91600, groups={"MACD", "All"})
	public void TNManagement_MACDFlow() throws Exception {
		TNManagement();
	}

	@Test(priority = 91700, groups={"MACD", "All"})
	public void directoryListing_MACDFlow() throws Exception {
		directoryListing();
	}

	@Test(priority = 91800, groups={"MACD", "All"})
	public void equipmentConfiguration_MACDFlow() throws Exception {
		equipmentConfiguration();
	}

	@Test(priority = 91900, groups={"MACD", "All"})
	public void AABusinessHours_MACDFlow() throws Exception {
		AABusinessHours();
	}

	@Test(priority = 92000, groups={"MACD", "All"})
	public void generateWebTopFile_MACDFlow() throws Exception {
		generateWebTopFile();
	}

	@Test(priority = 92100, groups={"MACD", "All"})
	public void SIMONLogout_MACDFlow() throws Exception {
		SIMONLogout();
	}

	@Test(priority = 92200, groups={"MACD", "All"})
	public void webTopLoginAfterSIMON_MACDFlow() throws Exception {
		webTopLogin();
	}

	@Test(priority = 92300, groups={"MACD", "All"})
	public void bulkLoadFileImportAfterSIMON_MACDFlow() throws Exception {
		bulkLoadFileImport();
	}

	@Test(priority = 92400, groups={"MACD", "All"})
	public void SOA_Or_SDW_RequiredAfterSIMON_MACDFlow() throws Exception {
		SOA_Or_SDW_Required();
	}

	@Test(priority = 92500, groups={"MACD", "All"})
	public void reviewOrderForAccuracyAndAcceptanceAfterSIMON_MACDFlow() throws Exception {
		reviewOrderForAccuracyAndAcceptanceAfterSIMON();
	}

	@Test(priority = 92600, groups={"MACD", "All"})
	public void chooseShippingMethodForCPEOrder_MACDFlow() throws Exception {
		chooseShippingMethodForCPEOrder();
	}

	@Test(priority = 92700, groups={"MACD", "All"})
	public void errorReservingNativeTNs_MACDFlow() throws Exception {
		errorReservingNativeTNs();
	}

	@Test(priority = 92800, groups={"MACD", "All"})
	public void CPEOrderWillBeSubmittedAutomatically_MACDFlow() throws Exception {
		CPEOrderWillBeSubmittedAutomatically();
	}

	@Test(priority = 92900, groups={"MACD", "All"})
	public void provisionPortedTNsForOrder_MACDFlow() throws Exception {
		provisionPortedTNsForOrder();
	}

	@Test(priority = 93000, groups={"MACD", "All"})
	public void enterFOCDateForPortedTNs_MACDFlow() throws Exception {
		enterFOCDateForPortedTNs();
	}

	@Test(priority = 93100, groups={"MACD", "All"})
	public void waitingForOrderAcceptRejectResponseFromSNAP_MACDFlow() throws Exception {
		waitingForOrderAcceptRejectResponseFromSNAP();
	}

	@Test(priority = 93200, groups={"MACD", "All"})
	public void waitingForSNAPFileUploadStatusNotification_MACDFlow() throws Exception {
		waitingForSNAPFileUploadStatusNotification();
	}

	@Test(priority = 93300, groups={"MACD", "All"})
	public void webTopLogoutAfterShippingMethodForCPEOrder_MACDFlow() throws Exception {
		webTopLogout_MACDFlow();
	}

	@Test(priority = 93400, groups={"MACD", "All"})
	public void NetXLogin_MACDFlow() throws Exception {
		NetXLogin();
	}

	@Test(priority = 93500, groups={"MACD", "All"})
	public void processTestOrder_MACDFlow() throws Exception {
		processTestOrder();
	}

	@Test(priority = 93600, groups={"MACD", "All"})
	public void wait24HoursThenRecheckForCPEMACAddress_MACDFlow() throws Exception {
		wait24HoursThenRecheckForCPEMACAddress();
	}

	@Test(priority = 93700, groups={"MACD", "All"})
	public void verifyCPEShipmentOrder_MACDFlow() throws Exception {
		verifyCPEShipmentOrder();
	}

	@Test(priority = 93800, groups={"MACD", "All"})
	public void cPEInfoNotReturnedAfterTwoAttempts_MACDFlow() throws Exception {
		cPEInfoNotReturnedAfterTwoAttempts();
	}

	@Test(priority = 93900, groups={"MACD", "All"})
	public void e911IncompleteUpdateErrorsOnOrder_MACDFlow() throws Exception {
		e911IncompleteUpdateErrorsOnOrder();
	}

	@Test(priority = 94000, groups={"MACD", "All"})
	public void installTechPreProvisionESG_MACDFlow() throws Exception {
		installTechPreProvisionESG();
	}

	@Test(priority = 94100, groups={"MACD", "All"})
	public void errorProvisioningE911ForApplicableTNs_MACDFlow() throws Exception {
		errorProvisioningE911ForApplicableTNs();
	}

	@Test(priority = 94200, groups={"MACD", "All"})
	public void errorProvisioningBroadworksUsersOrEnterprise_MACDFlow() throws Exception {
		errorProvisioningBroadworksUsersOrEnterprise();
	}

	@Test(priority = 94300, groups={"MACD", "All"})
	public void configureAdvancedBroadworksFeatures_MACDFlow() throws Exception {
		configureAdvancedBroadworksFeatures();
	}

	@Test(priority = 94400, groups={"MACD", "All"})
	public void configureAutoAttendantsAndHuntGroups_MACDFlow() throws Exception {
		configureAutoAttendantsAndHuntGroups();
	}

	@Test(priority = 94500, groups={"MACD", "All"})
	public void dataIssueExistsWithTollFreeTranslationTNAndOrANIFIDValue_MACDFlow() throws Exception {
		dataIssueExistsWithTollFreeTranslationTNAndOrANIFIDValue();
	}

	@Test(priority = 94600, groups={"MACD", "All"})
	public void broadsoftLogin_MACDFlow() throws Exception {
		broadsoftLogin();
	}

	@Test(priority = 94700, groups={"MACD", "All"})
	public void broadsoftGroupAndUsersVerification_MACDFlow() throws Exception {
		broadsoftGroupAndUsersVerification();
	}

	@Test(priority = 94800, groups={"MACD", "All"})
	public void broadsoftGroupAndExtensionDialingDigitVerification_MACDFlow() throws Exception {
		broadsoftGroupAndExtensionDialingDigitVerification();
	}

	@Test(priority = 94900, groups={"MACD", "All"})
	public void waitForResponseFromBCPPortal_MACDFlow() throws Exception {
		waitForResponseFromBCPPortal();
	}

	@Test(priority = 95000, groups={"MACD", "All"})
	public void performCPEInstallation_DOI_MACDFlow() throws Exception {
		performCPEInstallation_DOI();
	}

	@Test(priority = 95100, groups={"MACD", "All"})
	public void waitForDOCDate_MACDFlow() throws Exception {
		waitForDOCDate();
	}

	@Test(priority = 95200, groups={"MACD", "All"})
	public void cutoverBVE_MACDFlow() throws Exception {
		cutoverBVE();
	}

	@Test(priority = 95300, groups={"MACD", "All"})
	public void waitForResponseFromNeustar_MACDFlow() throws Exception {
		waitForResponseFromNeustar();
	}

	@Test(priority = 95400, groups={"MACD", "All"})
	public void errorProvisioningDAOrDLOrCNAMatNeustar_MACDFlow() throws Exception {
		errorProvisioningDAOrDLOrCNAMatNeustar();
	}

	@Test(priority = 95500, groups={"MACD", "All"})
	public void provisionDAOrDLOrCNAMatNeustarManually_MACDFlow() throws Exception {
		provisionDAOrDLOrCNAMatNeustarManually();
	}

	@Test(priority = 95600, groups={"MACD", "All"})
	public void waitAtLeast2MinutesBeforeCheckingCEMPStatus_MACDFlow() throws Exception {
		waitAtLeast2MinutesBeforeCheckingCEMPStatus();
	}

	@Test(priority = 95700, groups={"MACD", "All"})
	public void performTollFreeCIC_MACDFlow() throws Exception {
		performTollFreeCIC();
	}

	@Test(priority = 95800, groups={"MACD", "All"})
	public void governmentProgramOrder_CustomerAcceptsServiceStart_MACDFlow() throws Exception {
		governmentProgramOrder_CustomerAcceptsServiceStart();
	}

	@Test(priority = 95900, groups={"MACD", "All"})
	public void performBillingUpdate_MACDFlow() throws Exception {
		performBillingUpdate();
	}
	
	@Test(priority = 96000, groups={"MACD", "All"})
	public void terminateOldSeatAssociatedCustomerLevelMRCs_MACDFlow() throws Exception {
		
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		TerminateOldSeatAssociatedCustomerLevelMRCs terminateOldSeatAssociatedCustomerLevelMRCs = new TerminateOldSeatAssociatedCustomerLevelMRCs(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}			
			terminateOldSeatAssociatedCustomerLevelMRCs.terminateOldSeatAssociatedCustomerLevelMRCs();
		} catch (Exception Ex) {
			report.reportHardFailEvent("CARE: Terminate Old Seat Associated Customer Level MRC(s)", "CARE: Terminate Old Seat Associated Customer Level MRC(s) Failed!!!",
					Status.FAIL);
			String eMsg = "Error in CARE: Terminate Old Seat Associated Customer Level MRC(s) --- " + Ex.getMessage();
			log.error(eMsg);
			
		}
	}
	
	@Test(priority = 96500, groups={"MACD", "All"})
	public void addMRCChargesToOrderForNewSeats_MACDFlow() throws Exception {
		
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		AddMRCChargesToOrderForNewSeats addMRCChargesToOrderForNewSeats = new AddMRCChargesToOrderForNewSeats(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}			
			addMRCChargesToOrderForNewSeats.addMRCChargesToOrderForNewSeats();
		} catch (Exception Ex) {
			report.reportHardFailEvent("CARE: Add MRC Charge(s) to Order for New Seat(s)", "CARE: Add MRC Charge(s) to Order for New Seat(s) Failed!!!",
					Status.FAIL);
			String eMsg = "Error in CARE: Add MRC Charge(s) to Order for New Seat(s) --- " + Ex.getMessage();
			log.error(eMsg);
			
		}
	}
	
	@Test(priority = 97000, groups={"MACD", "All"})
	public void updateBillingForSeatChangeOrder_MACDFlow() throws Exception {
		
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		UpdateBillingForSeatChangeOrder updateBillingForSeatChangeOrder = new UpdateBillingForSeatChangeOrder(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}			
			updateBillingForSeatChangeOrder.updateBillingForSeatChangeOrder();
		} catch (Exception Ex) {
			report.reportHardFailEvent("BILLING: Update Billing for Seat Change Order", "BILLING: Update Billing for Seat Change Order Failed!!!",
					Status.FAIL);
			String eMsg = "Error in BILLING: Update Billing for Seat Change Order --- " + Ex.getMessage();
			log.error(eMsg);
			
		}
	}
}
