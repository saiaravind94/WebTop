package com.comcast.commons;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.comcast.pages.broadsoft.BroadsoftLogInPage;
import com.comcast.pages.netxusa.NetXUSALogInPage;
import com.comcast.pages.simon.SimonLogInPage;
import com.comcast.pages.webtop.WebTopLogInPage;
import com.comcast.reporting.ReportPath;
import com.comcast.utils.ComcastTestMain;
import com.comcast.utils.DataTable;
import com.comcast.utils.DatabaseDAO;

public class ComcastTest extends ComcastTestMain {

	Logger log = Logger.getLogger(ComcastTest.class);
	

	@BeforeTest
	public synchronized void beforeTestMainComcast(ITestContext context) throws FileNotFoundException, IOException {		
		DataTable dataSet = frameworkContext.getDataTable();
		if (!dataSet.getValue("queryTheOrder").isEmpty() & (settings.get("APP_TESTDATA_REUSE").equalsIgnoreCase("true"))) {
			if (dataDump.getValue("groupsToRun").isEmpty()) {
				loadWebTopDBData();
			}
			else
			{
				System.setProperty("groupsToRun", dataDump.getValue("groupsToRun"));
			}
		}		
		log.info("inside before test");		
		WebTopLogInPage.webTopLogedIn = false;
		SimonLogInPage.simonLogedIn = false;
		NetXUSALogInPage.NetXLogedIn = false;
		BroadsoftLogInPage.broadsoftLogedIn = false;
	}

	// Added by Kesavan for Conditional execution of tests
	public void loadWebTopDBData() {
		DatabaseDAO dbObj = new DatabaseDAO();
		Map<String, String> groupId;
		Connection con = null;
		try {
			con = dbObj.getConnection(settings.get("DBConnectionString"), settings.get("DBUserName"),
					settings.get("DBPassword"), settings.get("DBType"));
			if (con != null) {
				DataTable dataSet = frameworkContext.getDataTable();
				String query = dataSet.getValue("queryTheOrder") + "and OrderLockedForMACD='No' and Environment='"
						+ settings.getEnvironmentToTest() + "' ORDER BY `GroupID` DESC LIMIT 1";
				log.info("Select query to query the order from DB in Before Test is : " + query);
				groupId = dbObj.execute(con, query);
				if (groupId.size() >= 1) {
					log.info("Order Details");
					for (Map.Entry<String, String> entry : groupId.entrySet()) {
						dataDump.setValue(entry.getKey(), entry.getValue());
						log.info(entry.getKey() + ": " + entry.getValue());
					}
					log.info("Lock this order For MACD Process: " + dataDump.getValue("GroupID"));
					con = dbObj.getConnection(settings.get("DBConnectionString"), settings.get("DBUserName"),
							settings.get("DBPassword"), settings.get("DBType"));
					dbObj.updateMySqlDB(con, "Update webtoporders set OrderLockedForMACD='Yes' where GroupID='"+dataDump.getValue("GroupID")+"'");
					System.setProperty("groupsToRun", "MACD");
					dataDump.setValue("groupsToRun", "MACD");
				} else {
					log.info("Order already not there in DB");
					System.setProperty("groupsToRun", "All");
					dataDump.setValue("groupsToRun", "All");
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e.getMessage());
		}
	}

	@BeforeMethod
	public synchronized void setupDataApplication(Method testName) {
		log.info("\n");
		log.info("In Before Method : " + testName.getName());
		
	}

	@AfterMethod
	public synchronized void tearDownApplication(ITestResult result) {
		String methodStatus;
		if (result.getStatus() == 1) {
			methodStatus = "PASS";
		} else {
			methodStatus = "FAIL";
		}

		dataDump.setValue(result.getMethod().getMethodName() + "_status", methodStatus);
		if (methodStatus.equalsIgnoreCase("fail")) {
			// Add code for test clean up

		}
	}

	@AfterTest(alwaysRun = true)
	public synchronized void afterTestMainApplication() throws IOException, ClassNotFoundException, SQLException {
		System.clearProperty("groupsToRun");
		DataTable dataSet = frameworkContext.getDataTable();
		DatabaseDAO dbObj = new DatabaseDAO();
		ResultSet groupId, opportunityID;
		Connection con = dbObj.getConnection(settings.get("DBConnectionString"), settings.get("DBUserName"),
				settings.get("DBPassword"), settings.get("DBType"));
		if (con != null) {
			groupId = dbObj.queryDBTable(con,
					"Select GroupID from webtoporders where GroupID = '" + dataDump.getValue("GroupID") + "'");

			opportunityID = dbObj.queryDBTable(con, "Select GroupID from webtoporders where SFOpportunityId = '"
					+ dataDump.getValue("SFOpportunityId") + "'");

			if (!groupId.next() && !opportunityID.next()) {
				String query = "insert into webtoporders (GroupID, SFOpportunityId, DSTorCSGAccNo, ChildAccountID, PricingBase, SiteType, OrderType, NoOfStandardSeats, NoOfUCSeats, NoOfTechOrEnterpriseAdmins, OrderCategory, HasAutoAttendents, HasHuntGroups, OrderStatus, IsMACDPlaced, Environment, CustomerName, `BCP Enabled`, MailIdList) values ('"
						+ dataDump.getValue("GroupID") + "', '" + dataDump.getValue("SFOpportunityId") + "', '"
						+ dataDump.getValue("DSTorCSGAccNo") + "', '" + dataDump.getValue("ChildAccountID") + "', '"
						+ dataDump.getValue("PricingBase") + "', '" + dataDump.getValue("SiteType") + "', '"
						+ dataDump.getValue("OrderType") + "', '" + dataDump.getValue("NoOfStandardSeats") + "', '"
						+ dataDump.getValue("NoOfUCSeats") + "', '" + dataDump.getValue("NoOfTechOrEnterpriseAdmins")
						+ "', '" + dataDump.getValue("OrderCategory") + "', '" + dataDump.getValue("HasAutoAttendents")
						+ "', '" + dataDump.getValue("HasHuntGroups") + "', '" + dataDump.getValue("OrderStatus")
						+ "', '" + dataDump.getValue("IsMACDPlaced") + "', '" + settings.getEnvironmentToTest() + "', '"
						+ dataDump.getValue("CustomerName") + "', '" + dataSet.getValue("BCP Enabled") + "', '"
						+ dataDump.getValue("MailIdList") + "')";
				log.info("SQL query to insert the record: " + query);
				dbObj.updateMySqlDB(con, query);
			} else {
				String query = "Update webtoporders set GroupID='" + dataDump.getValue("GroupID") + "', DSTorCSGAccNo='"
						+ dataDump.getValue("DSTorCSGAccNo") + "'," + "ChildAccountID='"
						+ dataDump.getValue("ChildAccountID") + "', " + "PricingBase='"
						+ dataDump.getValue("PricingBase") + "', " + "SiteType='" + dataDump.getValue("SiteType")
						+ "', " + "OrderType='" + dataDump.getValue("OrderType") + "', " + "NoOfStandardSeats='"
						+ dataDump.getValue("NoOfStandardSeats") + "', " + "NoOfUCSeats='"
						+ dataDump.getValue("NoOfUCSeats") + "', " + "NoOfTechOrEnterpriseAdmins='"
						+ dataDump.getValue("NoOfTechOrEnterpriseAdmins") + "', " + "OrderCategory='"
						+ dataDump.getValue("OrderCategory") + "', " + "HasAutoAttendents='"
						+ dataDump.getValue("HasAutoAttendents") + "', " + "HasHuntGroups='"
						+ dataDump.getValue("HasHuntGroups") + "', " + "OrderStatus='"
						+ dataDump.getValue("OrderStatus") + "', " + "IsMACDPlaced='"
						+ dataDump.getValue("IsMACDPlaced") + "', " + "Environment='" + settings.getEnvironmentToTest()
						+ "', " + "CustomerName='" + dataDump.getValue("CustomerName") + "' , " + "`BCP Enabled`='"
						+ dataSet.getValue("BCP Enabled") + "', " + "MailIdList='" + dataDump.getValue("MailIdList") + "' where GroupID='" + dataDump.getValue("GroupID") + "' or "
						+ "SFOpportunityId='" + dataDump.getValue("SFOpportunityId") + "'";
				log.info("SQL query to update the existing record: " + query);
				try{
					dbObj.updateMySqlDB(con, query);
				}
				catch (Exception ex){
					
				}
			}
			if (con != null) {
				log.info("Closing the DB connection in After Test");
				con.close();
			}
		}
		//Reverting the OrderLockedForMACD field in DB to NO. So that same order can be used for any other MACD
		// NOTE: This Field will be updated only when the test test case is PASSED. 
		if(report.getStepsFailed()==0)
		{
			con = dbObj.getConnection(settings.get("DBConnectionString"), settings.get("DBUserName"),
						settings.get("DBPassword"), settings.get("DBType"));
			dbObj.updateMySqlDB(con, "Update webtoporders set OrderLockedForMACD='Yes' where GroupID='"+dataDump.getValue("GroupID")+"'");
			con.close();		
		}
		String reportPath = ReportPath.getInstance().getReportPath();
		String testcaseName = frameworkContext.getTestCaseName();
		log.info("Copying all the test data files to results folder");
		if (!dataDump.getValue("ImportFileName").equalsIgnoreCase("")) {
			File ImportFileName = new File(dataDump.getValue("ImportFileName"));
			FileUtils.copyFile(ImportFileName,
					new File(reportPath + "\\" + testcaseName + "_" + ImportFileName.getName()));
		}
		if (!dataDump.getValue("dataFileName").equalsIgnoreCase("")) {
			File dataFileName = new File(dataDump.getValue("dataFileName"));
			FileUtils.copyFile(dataFileName, new File(reportPath + "\\" + testcaseName + "_" + dataFileName.getName()));
		}
		if (!dataDump.getValue("WebTopImportFilePath_RT").equalsIgnoreCase("")) {
			File WebTopImportFilePath_RT = new File(dataDump.getValue("WebTopImportFilePath_RT"));
			FileUtils.copyFile(WebTopImportFilePath_RT,
					new File(reportPath + "\\" + testcaseName + "_" + WebTopImportFilePath_RT.getName()));
		}
	}
}
