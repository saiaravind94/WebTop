package com.comcast.WebTopCases;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.comcast.utils.DataTable;

public class SBP_MACD_Multi_Site_Full_Disconnect  extends DisconnectFlow{
	
	DataTable dataSet;
	String eMsg = "";
	

	Logger log = Logger.getLogger(SBP_MACD_Multi_Site_Full_Disconnect.class);

	@Test(priority = 128050, groups={"MACD", "All"})	
	public void updatePrimarySiteGroupIdToPlaceDisconnect() throws Exception {
		dataDump.setValue("GroupID_Site2", dataDump.getValue("GroupID"));
		dataDump.setValue("GroupID", dataDump.getValue("GroupID_Site1"));
	}
	
	@Test(priority = 128100, groups={"MACD", "All"})
	public void terminateAccount_MSFlow() throws Exception {
		terminateAccount();
	}
	
	@Test(priority = 128200, groups={"MACD", "All"})
	public void attachDocumentsToDisconnectOrder_MSFlow() throws Exception {
		attachDocumentsToDisconnectOrder();
	}
	
	@Test(priority = 128300, groups={"MACD", "All"})
	public void acceptOrRejectTNDisconnectionOrder_MSFlow() throws Exception {
		acceptOrRejectTNDisconnectionOrder();
	}
	
	@Test(priority = 128400, groups={"MACD", "All"})
	public void captureDisconnectOrderType_MSFlow() throws Exception {
		captureDisconnectOrderType();
	}	

	@Test(priority = 128500, groups={"MACD", "All"})
	public void captureDisconnectOrderReason_MSFlow() throws Exception {
		captureDisconnectOrderReason();
	}
	
	@Test(priority = 128600, groups={"MACD", "All"})
	public void requestRMAForCPE_DisconnectOrder_MSFlow() throws Exception {
		requestRMAForCPE_DisconnectOrder();
	}
	
	@Test(priority = 128700, groups={"MACD", "All"})
	public void scheduleDispatch_MSFlow() throws Exception {
		scheduleDispatch();
	}
	
	@Test(priority = 128800, groups={"MACD", "All"})
	public void reconfigureSwitchForTNDisconnectOrder_MSFlow() throws Exception {
		reconfigureSwitchForTNDisconnectOrder();
	}
	
	@Test(priority = 128900, groups={"MACD", "All"})
	public void errorDeprovisioningDAOrDLOrCNAMAtNeustar_MSFlow() throws Exception {
		errorDeprovisioningDAOrDLOrCNAMAtNeustar();
	}
	
	@Test(priority = 129000, groups={"MACD", "All"})
	public void terminateApplicableBundlesAtCustomerLevel_MSFlow() throws Exception {
		terminateApplicableBundlesAtCustomerLevel();
	}
	
	@Test(priority = 130000, groups={"MACD", "All"})
	public void addMRCChargesToOrderForNewDevices_MSFlow() throws Exception {
		addMRCChargesToOrderForNewDevices();
	}
		
	@Test(priority = 130100, groups={"MACD", "All"})
	public void uninstallCableModem_MSFlow() throws Exception {
		uninstallCableModem();
	}
	
	@Test(priority = 130200, groups={"MACD", "All"})
	public void decommissionESG_MSFlow() throws Exception {
		decommissionESG();
	}
	
	@Test(priority = 130300, groups={"MACD", "All"})
	public void deprovisionDAOrDLOrCNAMAtNeustarManually_MSFlow() throws Exception {
		deprovisionDAOrDLOrCNAMAtNeustarManually();
	}
	
	@Test(priority = 130400, groups={"MACD", "All"})
	public void updateBillingForTNDisconnectOrder_MSFlow() throws Exception {
		updateBillingForTNDisconnectOrder();
	}
	
	@Test(priority = 130500, groups={"MACD", "All"})
	public void deleteBVETopologyRecords_MSFlow() throws Exception {
		deleteBVETopologyRecords();
	}
	
	@Test(priority = 130600, groups={"MACD", "All"})
	public void errorOccurredWhenRemovingDevicesFromCEMP_MSFlow() throws Exception {
		errorOccurredWhenRemovingDevicesFromCEMP();
	}
	
	@Test(priority = 130700, groups={"MACD", "All"})
	public void notifySNAPofGreetingDisconnect_MSFlow() throws Exception {
		notifySNAPofGreetingDisconnect();
	}
	
	@Test(priority = 130800, groups={"MACD", "All"})
	public void broadsoftLogin_AfterMACD_MSFlow() throws Exception {
		broadsoftLogin();
	}
	
	@Test(priority = 130900, groups={"MACD", "All"})
	public void verifyGroupDisconnectedFromSwitch_MSFlow() throws Exception {
		verifyGroupDisconnectedFromSwitch();
	}

}
