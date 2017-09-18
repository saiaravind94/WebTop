package com.comcast.WebTopCases;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.comcast.utils.DataTable;

public class MultiSiteFlow extends CommonE2EFlow {
	DataTable dataSet;
	String eMsg = "";
	
	
	Logger log = Logger.getLogger(MultiSiteFlow.class);
	
	
	@Test(priority = 40000, groups={"New Connect", "All"})	
	public void updateDumpValuesAfterPrimarySiteCompleted() throws Exception {
		dataDump.setValue("SFOpportunityId_Site1", dataDump.getValue("SFOpportunityId"));
		dataDump.setValue("GroupID_Site1", dataDump.getValue("GroupID"));
		dataDump.setValue("OrderID_RT_Site1", dataDump.getValue("OrderID_RT"));
		dataDump.setValue("WebTopImportFilePath_RT_Site1", dataDump.getValue("WebTopImportFilePath_RT"));
		
		dataDump.setValue("SFOpportunityId", Integer.toString((Integer.parseInt(dataDump.getValue("SFOpportunityId")) + 1)));
		dataDump.setValue("GroupID", "");
		dataDump.setValue("OrderID_RT", "");
		dataDump.setValue("WebTopImportFilePath_RT", "");		
	}
	@Test(priority = 40100, groups={"New Connect", "All"})
	
	public void SOA_Or_SDW_Required_MSFlow() throws Exception {		
		SOA_Or_SDW_Required();
		dataDump.setValue("SiteType", "MultiSite");
	}

	
	@Test(priority = 40200, groups={"New Connect", "All"})
	public void prequalCheckList_MSFlow() throws Exception {
		prequalCheckList();
	}

	@Test(priority = 40300, groups={"New Connect", "All"})
	public void reviewOrderForAccuracyAndAcceptance_MSFlow() throws Exception {
		reviewOrderForAccuracyAndAcceptance();
	}

	@Test(priority = 40400, groups={"New Connect", "All"})
	public void SDPMCompleteDesignReview_MSFlow() throws Exception {
		SDPMCompleteDesignReview();
	}
	
	@Test(priority = 40450, groups={"New Connect", "All"})
	public void reviewAndCorrectSFID_MSFlow() throws Exception {
		reviewAndCorrectSFID();
	}
	@Test(priority = 40500, groups={"New Connect", "All"})
	public void createAndEnterDSTOrDSGAccountNumber_MSFlow() throws Exception {
		createAndEnterDSTOrDSGAccountNumber();
	}
	
	@Test(priority = 40600, groups={"New Connect", "All"})
	public void errorValidatingCSGOrDSTFormData_MSFlow() throws Exception {
		errorValidatingCSGOrDSTFormData();
	}	

	@Test(priority = 40700, groups={"New Connect", "All"})
	public void scheduleDOIOrDOC_MSFlow() throws Exception {
		scheduleDOIOrDOC();
	}

	@Test(priority = 40800, groups={"New Connect", "All"})
	public void SOAOrderComplete_MSFlow() throws Exception {
		SOAOrderComplete();
	}

	@Test(priority = 40900, groups={"New Connect", "All"})
	public void webTopLogout_MSFlow() throws Exception {
		webTopLogout();
	}

	@Test(priority = 41000, groups={"New Connect", "All"})
	public void SIMONLogin_MSFlow() throws Exception {
		SIMONLogin();
	}
	

	@Test(priority = 41100, groups={"New Connect", "All"})
	public void SFOpportunityIDSearch_MSFlow() throws Exception {
		SFOpportunityIDSearch();
	}

	@Test(priority = 41200, groups={"New Connect", "All"})
	public void customerDetails_MSFlow() throws Exception {
		customerDetails();
	}

	@Test(priority = 41300, groups={"New Connect", "All"})
	public void orderInformation_MSFlow() throws Exception {
		orderInformation();
	}

	@Test(priority = 41400, groups={"New Connect", "All"})
	public void serviceInformation_MSFlow() throws Exception {
		serviceInformation();
	}

	@Test(priority = 41500, groups={"New Connect", "All"})
	public void callerIDInformation_MSFlow() throws Exception {
		callerIDInformation();
	}

	@Test(priority = 41600, groups={"New Connect", "All"})
	public void TNManagement_MSFlow() throws Exception {
		TNManagement();
	}	

	@Test(priority = 41700, groups={"New Connect", "All"})
	public void directoryListing_MSFlow() throws Exception {
		directoryListing();
	}
	
	
	@Test(priority = 41800, groups={"New Connect", "All"})
	public void equipmentConfiguration_MSFlow() throws Exception {
		equipmentConfiguration();
	}
	
	
	@Test(priority = 41900, groups={"New Connect", "All"})
	public void AABusinessHours_MSFlow() throws Exception {
		AABusinessHours();
	}

	@Test(priority = 42000, groups={"New Connect", "All"})
	public void generateWebTopFile_MSFlow() throws Exception {
		generateWebTopFile();
	}

	@Test(priority = 42100, groups={"New Connect", "All"})
	public void SIMONLogout_MSFlow() throws Exception {
		SIMONLogout();
	}

	@Test(priority = 42200, groups={"New Connect", "All"})
	public void webTopLoginAfterSIMON_MSFlow() throws Exception {
		webTopLogin();
	}

	@Test(priority = 42300, groups={"New Connect", "All"})
	public void bulkLoadFileImportAfterSIMON_MSFlow() throws Exception {
		bulkLoadFileImport();
	}

	@Test(priority = 42400, groups={"New Connect", "All"})
	public void SOA_Or_SDW_RequiredAfterSIMON_MSFlow() throws Exception {
		SOA_Or_SDW_Required();
	}

	@Test(priority = 42500, groups={"New Connect", "All"})
	public void reviewOrderForAccuracyAndAcceptanceAfterSIMON_MSFlow() throws Exception {
		reviewOrderForAccuracyAndAcceptanceAfterSIMON();
	}


	@Test(priority = 42600, groups={"New Connect", "All"})
	public void chooseShippingMethodForCPEOrder_MSFlow() throws Exception {
		chooseShippingMethodForCPEOrder();
	}
	
	@Test(priority = 42700, groups={"New Connect", "All"})
	public void errorReservingNativeTNs_MSFlow() throws Exception {
		errorReservingNativeTNs();
	}
	
	
	@Test(priority = 42800, groups={"New Connect", "All"})
	public void CPEOrderWillBeSubmittedAutomatically_MSFlow() throws Exception {
		CPEOrderWillBeSubmittedAutomatically();
	}
	
	@Test(priority = 42900, groups={"New Connect", "All"})
	public void provisionPortedTNsForOrder_MSFlow() throws Exception {
		provisionPortedTNsForOrder();
	}
	
	@Test(priority = 43000, groups={"New Connect", "All"})
	public void enterFOCDateForPortedTNs_MSFlow() throws Exception {
		enterFOCDateForPortedTNs();
	}
	
	@Test(priority = 43100, groups={"New Connect", "All"})
	public void waitingForOrderAcceptRejectResponseFromSNAP_MSFlow() throws Exception {
		waitingForOrderAcceptRejectResponseFromSNAP();
	}
	
	@Test(priority = 43200, groups={"New Connect", "All"})
	public void waitingForSNAPFileUploadStatusNotification_MSFlow() throws Exception {
		waitingForSNAPFileUploadStatusNotification();
	}

	@Test(priority = 43300, groups={"New Connect", "All"})
	public void webTopLogoutAfterShippingMethodForCPEOrder_MSFlow() throws Exception {
		webTopLogout_MSFlow();
	}

	@Test(priority = 43400, groups={"New Connect", "All"})
	public void NetXLogin_MSFlow() throws Exception {
		NetXLogin();
	}

	@Test(priority = 43500, groups={"New Connect", "All"})
	public void processTestOrder_MSFlow() throws Exception {
		processTestOrder();
	}

	@Test(priority = 43600, groups={"New Connect", "All"})
	public void wait24HoursThenRecheckForCPEMACAddress_MSFlow() throws Exception {
		wait24HoursThenRecheckForCPEMACAddress();
	}

	@Test(priority = 43700, groups={"New Connect", "All"})
	public void verifyCPEShipmentOrder_MSFlow() throws Exception {
		verifyCPEShipmentOrder();
	}
	
	@Test(priority = 43800, groups={"New Connect", "All"})
	public void cPEInfoNotReturnedAfterTwoAttempts_MSFlow() throws Exception {
		cPEInfoNotReturnedAfterTwoAttempts();
	}
	
	@Test(priority = 43900, groups={"New Connect", "All"})
	public void e911IncompleteUpdateErrorsOnOrder_MSFlow() throws Exception {
		e911IncompleteUpdateErrorsOnOrder();
	}

	@Test(priority = 44000, groups={"New Connect", "All"})
	public void installTechPreProvisionESG_MSFlow() throws Exception {
		installTechPreProvisionESG();
	}
	
	@Test(priority = 44100, groups={"New Connect", "All"})
	public void errorProvisioningE911ForApplicableTNs_MSFlow() throws Exception {
		errorProvisioningE911ForApplicableTNs();
	}

	@Test(priority = 44200, groups={"New Connect", "All"})
	public void errorProvisioningBroadworksUsersOrEnterprise_MSFlow() throws Exception {
		errorProvisioningBroadworksUsersOrEnterprise();
	}

	@Test(priority = 44300, groups={"New Connect", "All"})
	public void configureAdvancedBroadworksFeatures_MSFlow() throws Exception {
		configureAdvancedBroadworksFeatures();
	}
	
	@Test(priority = 44400, groups={"New Connect", "All"})
	public void configureAutoAttendantsAndHuntGroups_MSFlow() throws Exception {
		configureAutoAttendantsAndHuntGroups();
	}
	
	@Test(priority = 44500, groups={"New Connect", "All"})
	public void dataIssueExistsWithTollFreeTranslationTNAndOrANIFIDValue_MSFlow() throws Exception {
		dataIssueExistsWithTollFreeTranslationTNAndOrANIFIDValue();
	}
	
	@Test(priority = 44600, groups={"New Connect", "All"})
	public void broadsoftLogin_MSFlow() throws Exception {
		broadsoftLogin();
	}
	
	@Test(priority = 44700, groups={"New Connect", "All"})
	public void broadsoftGroupAndUsersVerification_MSFlow() throws Exception {
		broadsoftGroupAndUsersVerification();
	}
	
	@Test(priority = 44800, groups={"New Connect", "All"})
	public void broadsoftGroupAndExtensionDialingDigitVerification_MSFlow() throws Exception {
		broadsoftGroupAndExtensionDialingDigitVerification();
	}
	
	@Test(priority = 44900, groups={"New Connect", "All"})
	public void waitForResponseFromBCPPortal_MSFlow() throws Exception {
		waitForResponseFromBCPPortal();
	}

	@Test(priority = 45000, groups={"New Connect", "All"})
	public void performCPEInstallation_DOI_MSFlow() throws Exception {
		performCPEInstallation_DOI();
	}

	@Test(priority = 45100, groups={"New Connect", "All"})
	public void waitForDOCDate_MSFlow() throws Exception {
		waitForDOCDate();
	}

	@Test(priority = 45200, groups={"New Connect", "All"})
	public void cutoverBVE_MSFlow() throws Exception {
		cutoverBVE();
	}	
	
	@Test(priority = 45300, groups={"New Connect", "All"})
	public void waitForResponseFromNeustar_MSFlow() throws Exception {
		waitForResponseFromNeustar();
	}
	
	@Test(priority = 45400, groups={"New Connect", "All"})
	public void errorProvisioningDAOrDLOrCNAMatNeustar_MSFlow() throws Exception {
		errorProvisioningDAOrDLOrCNAMatNeustar();
	}
	
	@Test(priority = 45500, groups={"New Connect", "All"})
	public void provisionDAOrDLOrCNAMatNeustarManually_MSFlow() throws Exception {
		provisionDAOrDLOrCNAMatNeustarManually();
	}

	@Test(priority = 45600, groups={"New Connect", "All"})
	public void waitAtLeast2MinutesBeforeCheckingCEMPStatus_MSFlow() throws Exception {
		waitAtLeast2MinutesBeforeCheckingCEMPStatus();
	}
	
	@Test(priority = 45700, groups={"New Connect", "All"})
	public void performTollFreeCIC_MSFlow() throws Exception {
		performTollFreeCIC();
	}

	
	@Test(priority = 45800, groups={"New Connect", "All"})
	public void governmentProgramOrder_CustomerAcceptsServiceStart_MSFlow() throws Exception {
		governmentProgramOrder_CustomerAcceptsServiceStart();
	}
	
	@Test(priority = 45900, groups={"New Connect", "All"})
	public void performBillingUpdate_MSFlow() throws Exception {
		performBillingUpdate();
	}

}
