package com.comcast.WebTopCases;

import org.testng.annotations.Test;

import com.comcast.pages.webtop.AccountProfileTab;
import com.comcast.pages.webtop.WebTopLogInPage;
import com.comcast.pages.webtop.WebTopMainPage;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;

public class TC_003_WebTop_NONBCPSite_SeatsBased_EmailID_change_through_WT extends CommonE2EFlow {
	
	
	@Test(priority = 50000, groups={"MACD", "All"})
	public void changeEmailAddressForASeat() throws Exception {
		WebTopMainPage webTopMainPage = new WebTopMainPage(frameworkContext);
		AccountProfileTab accountProfileTab = new AccountProfileTab(frameworkContext);
		try {
			if (!WebTopLogInPage.webTopLogedIn) {
				webTopLogin();
			}
			webTopMainPage.searchOrder("Group ID");
			accountProfileTab.getANIList("before");
			String BTNNo = accountProfileTab.getBTNNo();
			//Get the ANI number and index of the ANI which is not linked to BTN ANI
			int UCSeatIdxToEdit = 0;
			for(int idx = 0; idx<dataDump.getValue("UCANIList_before").split("\\|").length;idx++)
			{
				if(!BTNNo.equalsIgnoreCase(dataDump.getValue("UCANIList_before").split("\\|")[idx])){
					dataDump.setValue("ANINumberToTerminate_RT", dataDump.getValue("UCANIList_before").split("\\|")[idx]);
					UCSeatIdxToEdit = idx;
					break;
				}				
			}			
			accountProfileTab.editSeat("UC", UCSeatIdxToEdit + 1);
			DataTable dataSet = frameworkContext.getDataTable();
			accountProfileTab.changeEmailId(dataSet.getValue("ContactEmail"));	
			if(!webTopMainPage.verifyIfOpenOrderGenerated())
			{
				report.reportPassEvent("Verify If Open order generated or not", "Open order is not generated as expected");
			}
			else
			{
				report.reportPassEvent("Verify If Open order generated or not", "Open order is generated and it is not expected");
			}
		} catch (Exception Ex) {
			report.reportHardFailEvent("Changing UC Seat Email address", "Changing UC Seat Email address is Failed!!!", Status.FAIL);
			String eMsg = "Error in Changing UC Seat Email address --- " + Ex.getMessage();
			log.error(eMsg);			
		}
	}	

}
