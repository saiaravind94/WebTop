package com.comcast.pages.webtop;

import java.sql.Connection;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.Assert;

import com.comcast.commons.WebtopPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.DataTable;
import com.comcast.utils.DatabaseDAO;
import com.comcast.utils.TestSettings;
import com.comcast.utils.ComcastTestMain.FrameworkContext;

public class CreateAndEnterDSTOrDSGAccountNumber extends WebtopPage {

	Logger log = Logger.getLogger(CreateAndEnterDSTOrDSGAccountNumber.class);
	SeleniumReport report;
	FrameworkContext context;
	DataTable dataSet;
	private TestSettings settings;
	public CreateAndEnterDSTOrDSGAccountNumber(FrameworkContext context) {
		super(context, "CreateAndEnterDSTOrDSGAccountNumber");
		report = context.getReport();
		this.context = context;
		this.dataSet = context.getDataTable();
		this.settings = context.getSettings();
	}

	@Override
	protected boolean isValidPage() {
		return true;
	}

	@Override
	protected void waitForPageLoad() {

	}

	public static class Locators {
		public static final String tabHistory = "tabHistory";
		public static final String lnkCreateAndEnterDSTOrCSGAccountNumber = "lnkCreateAndEnterDSTOrCSGAccountNumber";
		public static final String txtCSGAccNumber = "txtCSGAccNumber";
		public static final String txtDSTAccNumber = "txtDSTAccNumber";
		public static final String txtHouseKeyNumber = "txtHouseKeyNumber";
		public static final String lstTimeZone = "lstTimeZone";
		public static final String txtCustomerLocatioRateCenter = "txtCustomerLocatioRateCenter";
		public static final String btnSave = "btnSave";
	}

	public boolean createAndEnterDSTOrDSGAccountNumber() {
		mstatus = true;
		try {
			if (!isLocatorVisible(Locators.lnkCreateAndEnterDSTOrCSGAccountNumber, 5)) {
				iClick(Locators.tabHistory, null, "Click on History Tab");
			}
			iClick(Locators.lnkCreateAndEnterDSTOrCSGAccountNumber, null, "Click on Create Design");
			String LocalBillerAccountId = "", houseKey = "";
			if(dataSet.getValue("BCP Enabled").equalsIgnoreCase("Yes") || dataSet.getValue("BCP Enabled").equalsIgnoreCase("Y")){
				DatabaseDAO dbObj = new DatabaseDAO();
				Connection con = null;
				Map<String, String> results;
				con = dbObj.getConnection(settings.get("DBConnectionString"), settings.get("DBUserName"),
						settings.get("DBPassword"), settings.get("DBType"));
				if (con != null) {				
					String query = "SELECT HouseKey, AccountNumber FROM testaccounts WHERE IsAlreadyUsed = 'No' LIMIT 1";
					log.info("Select query to query the test accounts from DB is : " + query);
					results = dbObj.execute(con, query);
					if (results.size() >= 1) {
						log.info("Test Account details");
						for (Map.Entry<String, String> entry : results.entrySet()) {
							dataDump.setValue(entry.getKey(), entry.getValue());
							log.info(entry.getKey() + ": " + entry.getValue());
						}
						LocalBillerAccountId = dataDump.getValue("AccountNumber");
						iEnterText(Locators.txtCSGAccNumber, LocalBillerAccountId, "Enter CSGAcc number");
						houseKey = dataDump.getValue("HouseKey");
						con = dbObj.getConnection(settings.get("DBConnectionString"), settings.get("DBUserName"),
								settings.get("DBPassword"), settings.get("DBType"));
						dbObj.updateMySqlDB(con, "UPDATE testaccounts SET IsAlreadyUsed='Yes' WHERE HouseKey='" + dataDump.getValue("HouseKey") + "'");					
					} else {
						log.error("Test Accounts is not there in DB. Please create a new set of test data in testdata portal and update the db");
						Assert.fail("Test Accounts is not there in DB");
					}
				}
			}
			else
			{				
				if (!dataSet.getValue("CSGAcc Number").isEmpty()) {					
					LocalBillerAccountId = dataSet.getValue("CSGAcc Number");
					iEnterText(Locators.txtCSGAccNumber, LocalBillerAccountId, "Enter CSGAcc number");
					houseKey = dataSet.getValue("HouseKey");
				} else {					
					LocalBillerAccountId = dataSet.getValue("DSTAccountNumber");
					iEnterText(Locators.txtDSTAccNumber, LocalBillerAccountId, "Enter DSTAcc number");
					houseKey = dataSet.getValue("HouseKey");
				}
			}
			dataDump.setValue("DSTorCSGAccNo", LocalBillerAccountId);
			dataDump.setValue("HouseKey", houseKey);			
			iEnterText(Locators.txtHouseKeyNumber, houseKey, "Enter House Key");			
			iEnterText(Locators.txtCustomerLocatioRateCenter, dataSet.getValue("Rate"), "Enter Rate ID");
			iSelectValue(Locators.lstTimeZone, "(GMT-04:00) (US) Eastern Time");
			iClick(Locators.btnSave, null, "Click on Save button");
			if (!isLocatorVisible(Locators.lnkCreateAndEnterDSTOrCSGAccountNumber, 1))
				report.reportPassEvent("Create and Enter DST/CSG Account Number Tray",
						"Create and Enter DST/CSG Account Number completed successfully");
			else
				report.reportHardFailEvent("Create and Enter DST/CSG Account Number",
						"Create and Enter DST/CSG Account Number Failed!!!", Status.FAIL);
		} catch (Exception e) {
			mstatus = false;
			report.reportHardFailEvent("Create and Enter DST/CSG Account Number",
					"Create and Enter DST/CSG Account Number Failed!!!", Status.FAIL);
			String eMsg = "Error in Create and Enter DST/CSG Account Number --- " + e.getMessage();
			log.error(eMsg);
		}
		return mstatus;
	}
	
	public boolean saveTray() {
		mstatus = true;
		try {
			if (!isLocatorVisible(Locators.lnkCreateAndEnterDSTOrCSGAccountNumber, 5)) {
				iClick(Locators.tabHistory, null, "Click on History Tab");
			}
			iClick(Locators.lnkCreateAndEnterDSTOrCSGAccountNumber, null, "Click on Create Design");			
			iClick(Locators.btnSave, null, "Click on Save button");
			if (!isLocatorVisible(Locators.lnkCreateAndEnterDSTOrCSGAccountNumber, 1))
				report.reportPassEvent("Create and Enter DST/CSG Account Number Tray",
						"Create and Enter DST/CSG Account Number completed successfully");
			else
				report.reportHardFailEvent("Create and Enter DST/CSG Account Number",
						"Create and Enter DST/CSG Account Number Failed!!!", Status.FAIL);
		} catch (Exception e) {
			mstatus = false;
			report.reportHardFailEvent("Create and Enter DST/CSG Account Number",
					"Create and Enter DST/CSG Account Number Failed!!!", Status.FAIL);
			String eMsg = "Error in Create and Enter DST/CSG Account Number --- " + e.getMessage();
			log.error(eMsg);
		}
		return mstatus;
	}

}
