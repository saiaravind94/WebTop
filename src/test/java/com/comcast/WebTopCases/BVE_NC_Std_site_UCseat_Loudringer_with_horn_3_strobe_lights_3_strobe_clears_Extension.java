package com.comcast.WebTopCases;

import org.testng.annotations.Test;

import com.comcast.pages.broadsoft.BroadsoftGroupPage;
import com.comcast.pages.broadsoft.BroadsoftLogInPage;
import com.comcast.pages.simon.EquipmentConfiguration;
import com.comcast.pages.simon.SIMONMainPage;
import com.comcast.pages.simon.SimonLogInPage;
import com.comcast.reporting.Status;

public class BVE_NC_Std_site_UCseat_Loudringer_with_horn_3_strobe_lights_3_strobe_clears_Extension extends MACDFlow{

	@Test(priority = 10100, groups={"New Connect", "All"})
	public void loudRingerSettings() throws Exception {
		
		try {
			SIMONMainPage simonMainPage = new SIMONMainPage(frameworkContext);
			EquipmentConfiguration equipmentConfiguration = new EquipmentConfiguration(frameworkContext);
			if (!SimonLogInPage.simonLogedIn) {
				SIMONLogin();
				simonMainPage.SFOpportunityIDSearch();
			}
			equipmentConfiguration.loudRingerSettings("3", "3", true);
		} catch (Exception Ex) {
			report.reportHardFailEvent("Loud Ringer Configuration",
					"Loud Ringer Configuration Failed" + Ex.getMessage());
			eMsg = report.getMessage() + "Loud Ringer Configuration in SIMON Failed!!! " + Ex.getMessage();
			
			log.error(eMsg);
		}
	}
	
	@Test(priority = 23000, groups={"New Connect", "All"})
	public void verifyGroupServices() throws Exception {
		
		BroadsoftGroupPage broadsoftGroupPage = new BroadsoftGroupPage(frameworkContext);
		try {
			if (!BroadsoftLogInPage.broadsoftLogedIn) {
				broadsoftLogin();				
			}
			broadsoftGroupPage.searchGroups("Group ID", "Equal To", dataDump.getValue("GroupID"));
			broadsoftGroupPage.verifyGroupServices("Simultaneous Ring Personal", 1, 1, 1);
			log.info("abc");
		} catch (Exception Ex) {
			report.reportHardFailEvent("Broadsoft Simultaneous Ring Personals verification", "Broadsoft Simultaneous Ring Personals verification failed",
					Status.FAIL);
			String eMsg = "Error in Broadsoft Simultaneous Ring Personals verification failed" + Ex.getMessage();
			log.error(eMsg);
			
		}
	}
}
