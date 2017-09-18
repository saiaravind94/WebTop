package com.comcast.WebTopCases;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.comcast.pages.broadsoft.BroadsoftGroupPage;
import com.comcast.pages.broadsoft.BroadsoftLogInPage;
import com.comcast.pages.simon.AA_BusinessHours;
import com.comcast.pages.simon.SIMONMainPage;
import com.comcast.pages.simon.SeatConfiguration;
import com.comcast.pages.simon.SimonLogInPage;
import com.comcast.pages.webtop.AccountProfileTab;
import com.comcast.pages.webtop.BusinessCentralTab;
import com.comcast.pages.webtop.ChooseActionForGroupCallCapacityQuantity;
import com.comcast.pages.webtop.ErrorCheckingIfTodayIsWithin5BusinessDaysOfDOI;
import com.comcast.pages.webtop.ErrorReservingNativeTNs;
import com.comcast.pages.webtop.PerformBillingUpdate;
import com.comcast.pages.webtop.ScheduleDOIOrDOC;
import com.comcast.pages.webtop.ServiceOrderInitiatedViaSQL;
import com.comcast.pages.webtop.ServicesPage;
import com.comcast.pages.webtop.UpdatePriceTier;
import com.comcast.pages.webtop.ValidationErrorsFoundonSOAOrder;
import com.comcast.pages.webtop.WebTopLogInPage;
import com.comcast.pages.webtop.WebTopMainPage;
import com.comcast.pages.webtop.WorkOrderComments;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.PageBase;

public class Incremental_add_MACD_Support_four_new_phone_models_with_switch_and_Netx extends DisconnectFlow {
	DataTable dataSet;
	String eMsg = "";
	
	
	Logger log = Logger.getLogger(Incremental_add_MACD_Support_four_new_phone_models_with_switch_and_Netx.class);
	
	@Test(priority = 30000, groups={"MACD", "All"})	
	public void addNewUCSeatWithBundleFor311() throws Exception {
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		AccountProfileTab accountProfileTab = new AccountProfileTab(frameworkContext);
		ServicesPage servicesPage = new ServicesPage(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
			}
			webTopMainPage.searchOrder("Group ID");			
			accountProfileTab.launchAddInventory();
			servicesPage.addServices("AddNewANI", "ANI");	
			servicesPage.addANI();
			servicesPage.addItemOrBundle("Item", "OrderInvertoryANI", "BVEUCS-Unified Communications Seat | 0.00", 1);
			servicesPage.addItemOrBundle("Item", "OrderInvertoryANI", "BVEVVX311-Polycom VVX 311 HD | 0.00", 1);
			accountProfileTab.submitTheOrder();
			dataDump.setValue("OrderCategory", "MACD");
			dataDump.setValue("OrderStatus", "In-Progress");
			dataDump.setValue("IsMACDPlaced", "Yes");			
		} catch (Exception Ex) {
			report.reportHardFailEvent("Adding New UC Seat ANI With Bundle",
					"Adding New UC Seat ANI With Bundle is Failed!!!", Status.FAIL);
			String eMsg = "Error in Adding New UC Seat ANI With Bundle --- " + Ex.getMessage();
			log.error(eMsg);
			
		}			
	}
	
	@Test(priority = 30100, groups={"MACD", "All"})	
	public void errorDeterminingOrderTypeFor311() throws Exception {
		errorDeterminingOrderType();	
	}	
	
	@Test(priority = 30200, groups={"MACD", "All"})
	public void chooseActionForGroupCallCapacityQuantityFor311() throws Exception {
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		ChooseActionForGroupCallCapacityQuantity chooseActionForGroupCallCapacityQuantity = new ChooseActionForGroupCallCapacityQuantity(
				frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
				webTopMainPage.searchFor("Group ID");
			}
			chooseActionForGroupCallCapacityQuantity.chooseActionForGroupCallCapacityQuantity();
		} catch (Exception Ex) {
			report.reportHardFailEvent("CARE: Choose action for group Call Capacity quantity ", "CARE: Choose action for group Call Capacity quantity  Failed!!!",
					Status.FAIL);
			String eMsg = "Error in CARE: Choose action for group Call Capacity quantity  --- " + Ex.getMessage();
			log.error(eMsg);
		}
	}
	
	@Test(priority = 30300, groups={"MACD", "All"})
	public void errorReservingNativeTNsFor311() throws Exception {
		errorReservingNativeTNs();
	}
		
	@Test(priority = 30400, groups={"MACD", "All"})
	public void chooseShippingMethodForCPEOrderFor311() throws Exception {
		chooseShippingMethodForCPEOrder();
	}
	
	@Test(priority = 30500, groups={"MACD", "All"})
	public void CPEOrderWillBeSubmittedAutomaticallyFor311() throws Exception {
		CPEOrderWillBeSubmittedAutomatically();
	}
	
	@Test(priority = 30600, groups={"MACD", "All"})
	public void webTopLogoutAfterShippingMethodForCPEOrderFor311() throws Exception {
		webTopLogoutAfterShippingMethodForCPEOrder();
	}
	
	@Test(priority = 30700, groups={"MACD", "All"})
	public void NetXLoginFor311() throws Exception {
		NetXLogin();
	}
	
	@Test(priority = 30800, groups={"MACD", "All"})
	public void processTestOrderFor311() throws Exception {
		processTestOrder();
	}
	
	@Test(priority = 30900, groups={"MACD", "All"})
	public void wait24HoursThenRecheckForCPEMACAddressFor311() throws Exception {
		wait24HoursThenRecheckForCPEMACAddress();
	}
	
	@Test(priority = 31000, groups={"MACD", "All"})
	public void scheduleDOIOrDOCFor311() throws Exception {
		scheduleDOIOrDOC();
		ErrorCheckingIfTodayIsWithin5BusinessDaysOfDOI errorCheckingIfTodayIsWithin5BusinessDaysOfDOI = new ErrorCheckingIfTodayIsWithin5BusinessDaysOfDOI(frameworkContext);
		errorCheckingIfTodayIsWithin5BusinessDaysOfDOI.errorCheckingIfTodayIsWithin5BusinessDaysOfDOI();
	}
	
	@Test(priority = 31100, groups={"MACD", "All"})
	public void e911IncompleteUpdateErrorsOnOrderFor311() throws Exception {
		e911IncompleteUpdateErrorsOnOrder();
	}
	
	@Test(priority = 31150, groups={"MACD", "All"})
	public void verifyCPEShipmentOrderFor311() throws Exception {
		verifyCPEShipmentOrder();
	}
	
	@Test(priority = 31200, groups={"MACD", "All"})
	public void installTechPreProvisionESGFor311() throws Exception {
		installTechPreProvisionESG();
	}
	
	@Test(priority = 31300, groups={"MACD", "All"})
	public void errorProvisioningBroadworksUsersOrEnterpriseFor311() throws Exception {
		errorProvisioningBroadworksUsersOrEnterprise();
	}
	
	@Test(priority = 31400, groups={"MACD", "All"})
	public void configureAdvancedBroadworksFeaturesFor311() throws Exception {
		configureAdvancedBroadworksFeatures();
	}
	
	@Test(priority = 31500, groups={"MACD", "All"})
	public void configureAutoAttendantsAndHuntGroupsFor311() throws Exception {
		configureAutoAttendantsAndHuntGroups();
	}
	
	@Test(priority = 31500, groups={"MACD", "All"})
	public void performCPEInstallation_DOIFor311() throws Exception {
		performCPEInstallation_DOI();
	}
	
	@Test(priority = 31600, groups={"MACD", "All"})
	public void waitForDOCDateFor311() throws Exception {
		waitForDOCDate();
	}
	
	@Test(priority = 31700, groups={"MACD", "All"})
	public void cutoverBVEFor311() throws Exception {
		cutoverBVE();
	}
	
	@Test(priority = 31800, groups={"MACD", "All"})
	public void waitForResponseFromNeustarFor311() throws Exception {
		waitForResponseFromNeustar();
	}
	
	@Test(priority = 31810, groups={"MACD", "All"})
	public void errorProvisioningDAOrDLOrCNAMatNeustarFor311() throws Exception {
		errorDeprovisioningDAOrDLOrCNAMAtNeustar();
	}
	
	@Test(priority = 31820, groups={"MACD", "All"})
	public void provisionDAOrDLOrCNAMatNeustarManuallyFor311() throws Exception {
		provisionDAOrDLOrCNAMatNeustarManually();
	}
	
	@Test(priority = 31900, groups={"MACD", "All"})
	public void waitAtLeast2MinutesBeforeCheckingCEMPStatusFor311() throws Exception {
		waitAtLeast2MinutesBeforeCheckingCEMPStatus();
	}
	
	@Test(priority = 32000, groups={"MACD", "All"})
	public void performBillingUpdateFor311() throws Exception {
		performBillingUpdate();
	}
	
	
	@Test(priority = 40000, groups={"MACD", "All"})	
	public void addNewUCSeatWithBundleFor411() throws Exception {
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		AccountProfileTab accountProfileTab = new AccountProfileTab(frameworkContext);
		ServicesPage servicesPage = new ServicesPage(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
			}
			webTopMainPage.searchOrder("Group ID");			
			accountProfileTab.launchAddInventory();
			servicesPage.addServices("AddNewANI", "ANI");	
			servicesPage.addANI();
			servicesPage.addItemOrBundle("Item", "OrderInvertoryANI", "BVEUCS-Unified Communications Seat | 0.00", 1);
			servicesPage.addItemOrBundle("Item", "OrderInvertoryANI", "BVEVVX411-Polycom VVX 411 HD | 0.00", 1);
			accountProfileTab.submitTheOrder();
			dataDump.setValue("OrderCategory", "MACD");
			dataDump.setValue("OrderStatus", "In-Progress");
			dataDump.setValue("IsMACDPlaced", "Yes");			
		} catch (Exception Ex) {
			report.reportHardFailEvent("Adding New UC Seat ANI With Bundle",
					"Adding New UC Seat ANI With Bundle is Failed!!!", Status.FAIL);
			String eMsg = "Error in Adding New UC Seat ANI With Bundle --- " + Ex.getMessage();
			log.error(eMsg);
			
		}			
	}
	
	@Test(priority = 40100, groups={"MACD", "All"})	
	public void errorDeterminingOrderTypeFor411() throws Exception {
		errorDeterminingOrderType();	
	}	
	
	@Test(priority = 40200, groups={"MACD", "All"})
	public void chooseActionForGroupCallCapacityQuantityFor411() throws Exception {
		chooseActionForGroupCallCapacityQuantityFor311();
	}
	
	@Test(priority = 40300, groups={"MACD", "All"})
	public void errorReservingNativeTNsFor411() throws Exception {
		errorReservingNativeTNs();
	}
		
	@Test(priority = 40400, groups={"MACD", "All"})
	public void chooseShippingMethodForCPEOrderFor411() throws Exception {
		chooseShippingMethodForCPEOrder();
	}
	
	@Test(priority = 40500, groups={"MACD", "All"})
	public void CPEOrderWillBeSubmittedAutomaticallyFor411() throws Exception {
		CPEOrderWillBeSubmittedAutomatically();
	}
	
	@Test(priority = 40600, groups={"MACD", "All"})
	public void webTopLogoutAfterShippingMethodForCPEOrderFor411() throws Exception {
		webTopLogoutAfterShippingMethodForCPEOrder();
	}
	
	@Test(priority = 40700, groups={"MACD", "All"})
	public void NetXLoginFor411() throws Exception {
		NetXLogin();
	}
	
	@Test(priority = 40800, groups={"MACD", "All"})
	public void processTestOrderFor411() throws Exception {
		processTestOrder();
	}
	
	@Test(priority = 40900, groups={"MACD", "All"})
	public void wait24HoursThenRecheckForCPEMACAddressFor411() throws Exception {
		wait24HoursThenRecheckForCPEMACAddress();
	}
	
	@Test(priority = 41000, groups={"MACD", "All"})
	public void scheduleDOIOrDOCFor411() throws Exception {
		scheduleDOIOrDOC();
		ErrorCheckingIfTodayIsWithin5BusinessDaysOfDOI errorCheckingIfTodayIsWithin5BusinessDaysOfDOI = new ErrorCheckingIfTodayIsWithin5BusinessDaysOfDOI(frameworkContext);
		errorCheckingIfTodayIsWithin5BusinessDaysOfDOI.errorCheckingIfTodayIsWithin5BusinessDaysOfDOI();
	}
	
	@Test(priority = 41100, groups={"MACD", "All"})
	public void e911IncompleteUpdateErrorsOnOrderFor411() throws Exception {
		e911IncompleteUpdateErrorsOnOrder();
	}
	
	@Test(priority = 41150, groups={"MACD", "All"})
	public void verifyCPEShipmentOrderFor411() throws Exception {
		verifyCPEShipmentOrder();
	}
	
	@Test(priority = 41200, groups={"MACD", "All"})
	public void installTechPreProvisionESGFor411() throws Exception {
		installTechPreProvisionESG();
	}
	
	@Test(priority = 41300, groups={"MACD", "All"})
	public void errorProvisioningBroadworksUsersOrEnterpriseFor411() throws Exception {
		errorProvisioningBroadworksUsersOrEnterprise();
	}
	
	@Test(priority = 41400, groups={"MACD", "All"})
	public void configureAdvancedBroadworksFeaturesFor411() throws Exception {
		configureAdvancedBroadworksFeatures();
	}
	
	@Test(priority = 41500, groups={"MACD", "All"})
	public void performCPEInstallation_DOIFor411() throws Exception {
		performCPEInstallation_DOI();
	}
	
	@Test(priority = 41600, groups={"MACD", "All"})
	public void waitForDOCDateFor411() throws Exception {
		waitForDOCDate();
	}
	
	@Test(priority = 41700, groups={"MACD", "All"})
	public void cutoverBVEFor411() throws Exception {
		cutoverBVE();
	}
	
	@Test(priority = 41800, groups={"MACD", "All"})
	public void waitForResponseFromNeustarFor411() throws Exception {
		waitForResponseFromNeustar();
	}	
	
	@Test(priority = 41810, groups={"MACD", "All"})
	public void errorProvisioningDAOrDLOrCNAMatNeustarFor411() throws Exception {
		errorDeprovisioningDAOrDLOrCNAMAtNeustar();
	}
	
	@Test(priority = 41820, groups={"MACD", "All"})
	public void provisionDAOrDLOrCNAMatNeustarManuallyFor411() throws Exception {
		provisionDAOrDLOrCNAMatNeustarManually();
	}
	
	@Test(priority = 41900, groups={"MACD", "All"})
	public void waitAtLeast2MinutesBeforeCheckingCEMPStatusFor411() throws Exception {
		waitAtLeast2MinutesBeforeCheckingCEMPStatus();
	}
	
	@Test(priority = 42000, groups={"MACD", "All"})
	public void performBillingUpdateFor411() throws Exception {
		performBillingUpdate();
	}
	

	
	@Test(priority = 50000, groups={"MACD", "All"})	
	public void addNewUCSeatWithBundleFor501() throws Exception {
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		AccountProfileTab accountProfileTab = new AccountProfileTab(frameworkContext);
		ServicesPage servicesPage = new ServicesPage(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
			}
			webTopMainPage.searchOrder("Group ID");			
			accountProfileTab.launchAddInventory();
			servicesPage.addServices("AddNewANI", "ANI");	
			servicesPage.addANI();
			servicesPage.addItemOrBundle("Item", "OrderInvertoryANI", "BVEUCS-Unified Communications Seat | 0.00", 1);
			servicesPage.addItemOrBundle("Item", "OrderInvertoryANI", "BVEVVX501-Polycom VVX 501 HD | 0.00", 1);
			accountProfileTab.submitTheOrder();
			dataDump.setValue("OrderCategory", "MACD");
			dataDump.setValue("OrderStatus", "In-Progress");
			dataDump.setValue("IsMACDPlaced", "Yes");			
		} catch (Exception Ex) {
			report.reportHardFailEvent("Adding New UC Seat ANI With Bundle",
					"Adding New UC Seat ANI With Bundle is Failed!!!", Status.FAIL);
			String eMsg = "Error in Adding New UC Seat ANI With Bundle --- " + Ex.getMessage();
			log.error(eMsg);
			
		}			
	}
	
	@Test(priority = 50100, groups={"MACD", "All"})	
	public void errorDeterminingOrderTypeFor501() throws Exception {
		errorDeterminingOrderType();	
	}	
	
	@Test(priority = 50200, groups={"MACD", "All"})
	public void chooseActionForGroupCallCapacityQuantityFor501() throws Exception {
		chooseActionForGroupCallCapacityQuantityFor311();
	}
	
	@Test(priority = 50300, groups={"MACD", "All"})
	public void errorReservingNativeTNsFor501() throws Exception {
		errorReservingNativeTNs();
	}
		
	@Test(priority = 50400, groups={"MACD", "All"})
	public void chooseShippingMethodForCPEOrderFor501() throws Exception {
		chooseShippingMethodForCPEOrder();
	}
	
	@Test(priority = 50500, groups={"MACD", "All"})
	public void CPEOrderWillBeSubmittedAutomaticallyFor501() throws Exception {
		CPEOrderWillBeSubmittedAutomatically();
	}
	
	@Test(priority = 50600, groups={"MACD", "All"})
	public void webTopLogoutAfterShippingMethodForCPEOrderFor501() throws Exception {
		webTopLogoutAfterShippingMethodForCPEOrder();
	}
	
	@Test(priority = 50700, groups={"MACD", "All"})
	public void NetXLoginFor501() throws Exception {
		NetXLogin();
	}
	
	@Test(priority = 50800, groups={"MACD", "All"})
	public void processTestOrderFor501() throws Exception {
		processTestOrder();
	}
	
	@Test(priority = 50900, groups={"MACD", "All"})
	public void wait24HoursThenRecheckForCPEMACAddressFor501() throws Exception {
		wait24HoursThenRecheckForCPEMACAddress();
	}
	
	@Test(priority = 51000, groups={"MACD", "All"})
	public void scheduleDOIOrDOCFor501() throws Exception {
		scheduleDOIOrDOC();
		ErrorCheckingIfTodayIsWithin5BusinessDaysOfDOI errorCheckingIfTodayIsWithin5BusinessDaysOfDOI = new ErrorCheckingIfTodayIsWithin5BusinessDaysOfDOI(frameworkContext);
		errorCheckingIfTodayIsWithin5BusinessDaysOfDOI.errorCheckingIfTodayIsWithin5BusinessDaysOfDOI();
	}
	
	@Test(priority = 51100, groups={"MACD", "All"})
	public void e911IncompleteUpdateErrorsOnOrderFor501() throws Exception {
		e911IncompleteUpdateErrorsOnOrder();
	}
	
	@Test(priority = 51150, groups={"MACD", "All"})
	public void verifyCPEShipmentOrderFor501() throws Exception {
		verifyCPEShipmentOrder();
	}
	
	@Test(priority = 51200, groups={"MACD", "All"})
	public void installTechPreProvisionESGFor501() throws Exception {
		installTechPreProvisionESG();
	}
	
	@Test(priority = 51300, groups={"MACD", "All"})
	public void errorProvisioningBroadworksUsersOrEnterpriseFor501() throws Exception {
		errorProvisioningBroadworksUsersOrEnterprise();
	}
	
	@Test(priority = 51400, groups={"MACD", "All"})
	public void configureAdvancedBroadworksFeaturesFor501() throws Exception {
		configureAdvancedBroadworksFeatures();
	}
	
	@Test(priority = 51500, groups={"MACD", "All"})
	public void performCPEInstallation_DOIFor501() throws Exception {
		performCPEInstallation_DOI();
	}
	
	@Test(priority = 51600, groups={"MACD", "All"})
	public void waitForDOCDateFor501() throws Exception {
		waitForDOCDate();
	}
	
	@Test(priority = 51700, groups={"MACD", "All"})
	public void cutoverBVEFor501() throws Exception {
		cutoverBVE();
	}
	
	@Test(priority = 51800, groups={"MACD", "All"})
	public void waitForResponseFromNeustarFor501() throws Exception {
		waitForResponseFromNeustar();
	}
	
	@Test(priority = 51810, groups={"MACD", "All"})
	public void errorProvisioningDAOrDLOrCNAMatNeustarFor501() throws Exception {
		errorDeprovisioningDAOrDLOrCNAMAtNeustar();
	}
	
	@Test(priority = 51820, groups={"MACD", "All"})
	public void provisionDAOrDLOrCNAMatNeustarManuallyFor501() throws Exception {
		provisionDAOrDLOrCNAMatNeustarManually();
	}
	
	@Test(priority = 51900, groups={"MACD", "All"})
	public void waitAtLeast2MinutesBeforeCheckingCEMPStatusFor501() throws Exception {
		waitAtLeast2MinutesBeforeCheckingCEMPStatus();
	}
	
	@Test(priority = 52000, groups={"MACD", "All"})
	public void performBillingUpdateFor501() throws Exception {
		performBillingUpdate();
	}
	

	
	@Test(priority = 60000, groups={"MACD", "All"})	
	public void addNewUCSeatWithBundleFor601() throws Exception {
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		AccountProfileTab accountProfileTab = new AccountProfileTab(frameworkContext);
		ServicesPage servicesPage = new ServicesPage(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
			}
			webTopMainPage.searchOrder("Group ID");			
			accountProfileTab.launchAddInventory();
			servicesPage.addServices("AddNewANI", "ANI");	
			servicesPage.addANI();
			servicesPage.addItemOrBundle("Item", "OrderInvertoryANI", "BVEUCS-Unified Communications Seat | 0.00", 1);
			servicesPage.addItemOrBundle("Item", "OrderInvertoryANI", "BVEVVX601-Polycom VVX 601 HD | 0.00", 1);
			accountProfileTab.submitTheOrder();
			dataDump.setValue("OrderCategory", "MACD");
			dataDump.setValue("OrderStatus", "In-Progress");
			dataDump.setValue("IsMACDPlaced", "Yes");			
		} catch (Exception Ex) {
			report.reportHardFailEvent("Adding New UC Seat ANI With Bundle",
					"Adding New UC Seat ANI With Bundle is Failed!!!", Status.FAIL);
			String eMsg = "Error in Adding New UC Seat ANI With Bundle --- " + Ex.getMessage();
			log.error(eMsg);
			
		}			
	}
	
	@Test(priority = 60100, groups={"MACD", "All"})	
	public void errorDeterminingOrderTypeFor601() throws Exception {
		errorDeterminingOrderType();	
	}	
	
	@Test(priority = 60200, groups={"MACD", "All"})
	public void chooseActionForGroupCallCapacityQuantityFor601() throws Exception {
		chooseActionForGroupCallCapacityQuantityFor311();
	}
	
	@Test(priority = 60300, groups={"MACD", "All"})
	public void errorReservingNativeTNsFor601() throws Exception {
		errorReservingNativeTNs();
	}
		
	@Test(priority = 60400, groups={"MACD", "All"})
	public void chooseShippingMethodForCPEOrderFor601() throws Exception {
		chooseShippingMethodForCPEOrder();
	}
	
	@Test(priority = 60500, groups={"MACD", "All"})
	public void CPEOrderWillBeSubmittedAutomaticallyFor601() throws Exception {
		CPEOrderWillBeSubmittedAutomatically();
	}
	
	@Test(priority = 60600, groups={"MACD", "All"})
	public void webTopLogoutAfterShippingMethodForCPEOrderFor601() throws Exception {
		webTopLogoutAfterShippingMethodForCPEOrder();
	}
	
	@Test(priority = 60700, groups={"MACD", "All"})
	public void NetXLoginFor601() throws Exception {
		NetXLogin();
	}
	
	@Test(priority = 60800, groups={"MACD", "All"})
	public void processTestOrderFor601() throws Exception {
		processTestOrder();
	}
	
	@Test(priority = 60900, groups={"MACD", "All"})
	public void wait24HoursThenRecheckForCPEMACAddressFor601() throws Exception {
		wait24HoursThenRecheckForCPEMACAddress();
	}
	
	@Test(priority = 61000, groups={"MACD", "All"})
	public void scheduleDOIOrDOCFor601() throws Exception {
		scheduleDOIOrDOC();
		ErrorCheckingIfTodayIsWithin5BusinessDaysOfDOI errorCheckingIfTodayIsWithin5BusinessDaysOfDOI = new ErrorCheckingIfTodayIsWithin5BusinessDaysOfDOI(frameworkContext);
		errorCheckingIfTodayIsWithin5BusinessDaysOfDOI.errorCheckingIfTodayIsWithin5BusinessDaysOfDOI();
	}
	
	@Test(priority = 61100, groups={"MACD", "All"})
	public void e911IncompleteUpdateErrorsOnOrderFor601() throws Exception {
		e911IncompleteUpdateErrorsOnOrder();
	}
	
	@Test(priority = 61150, groups={"MACD", "All"})
	public void verifyCPEShipmentOrderFor601() throws Exception {
		verifyCPEShipmentOrder();
	}
	
	@Test(priority = 61200, groups={"MACD", "All"})
	public void installTechPreProvisionESGFor601() throws Exception {
		installTechPreProvisionESG();
	}
	
	@Test(priority = 61300, groups={"MACD", "All"})
	public void errorProvisioningBroadworksUsersOrEnterpriseFor601() throws Exception {
		errorProvisioningBroadworksUsersOrEnterprise();
	}
	
	@Test(priority = 61400, groups={"MACD", "All"})
	public void configureAdvancedBroadworksFeaturesFor601() throws Exception {
		configureAdvancedBroadworksFeatures();
	}
	
	@Test(priority = 61500, groups={"MACD", "All"})
	public void performCPEInstallation_DOIFor601() throws Exception {
		performCPEInstallation_DOI();
	}
	
	@Test(priority = 61600, groups={"MACD", "All"})
	public void waitForDOCDateFor601() throws Exception {
		waitForDOCDate();
	}
	
	@Test(priority = 61700, groups={"MACD", "All"})
	public void cutoverBVEFor601() throws Exception {
		cutoverBVE();
	}
	
	@Test(priority = 61800, groups={"MACD", "All"})
	public void waitForResponseFromNeustarFor601() throws Exception {
		waitForResponseFromNeustar();
	}
	
	@Test(priority = 61810, groups={"MACD", "All"})
	public void errorProvisioningDAOrDLOrCNAMatNeustarFor601() throws Exception {
		errorDeprovisioningDAOrDLOrCNAMAtNeustar();
	}
	
	@Test(priority = 61820, groups={"MACD", "All"})
	public void provisionDAOrDLOrCNAMatNeustarManuallyFor601() throws Exception {
		provisionDAOrDLOrCNAMatNeustarManually();
	}
	
	@Test(priority = 61900, groups={"MACD", "All"})
	public void waitAtLeast2MinutesBeforeCheckingCEMPStatusFor601() throws Exception {
		waitAtLeast2MinutesBeforeCheckingCEMPStatus();
	}
	
	@Test(priority = 62000, groups={"MACD", "All"})
	public void performBillingUpdateFor601() throws Exception {
		performBillingUpdate();
	}
	
	@Test(priority = 62100, groups={"MACD", "All"})
	public void verifyNewDevicesInSwitch() throws Exception {		
		BroadsoftGroupPage broadsoftGroupPage = new BroadsoftGroupPage(frameworkContext);
		try {
			if (!BroadsoftLogInPage.broadsoftLogedIn) {
				broadsoftLogin();
			}
			broadsoftGroupPage.searchGroups("Group ID", "Equal To", dataDump.getValue("GroupID"));
			broadsoftGroupPage.verifyUsers("First Name", "Equal To", "FName", 4);			
		} catch (Exception Ex) {
			report.reportHardFailEvent("User verification Failed", "User verification Failed!!!", Status.FAIL);
			String eMsg = "Error in User verification  --- " + Ex.getMessage();
			log.error(eMsg);
		}
	}
	
}
