package com.comcast.commons;

import java.awt.Robot;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.apache.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.pages.simon.OrderSummaryPage.Locators;
import com.comcast.pages.simon.SeatConfiguration;
import com.comcast.utils.DataTable;
import com.comcast.utils.Page;

public class WebtopPage extends Page {

	WebDriver driver;
	protected String eMsg = "";
	protected Boolean mstatus = true;
	DataTable dataSet;

	static Logger log = Logger.getLogger(WebtopPage.class.getName());

	public WebtopPage(FrameworkContext context) {
		super(context);
		this.dataSet = context.getDataTable();
	}

	protected WebtopPage(FrameworkContext context, String classname) {
		super(context, classname);
		this.driver = context.getDriver();
	}

	/**
	 * Waits for the page load to be completed
	 * 
	 * @returns Gives the document ready state
	 */
	public void waitforPageLoadCompletemillisec() {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver1) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};
		try {
			Thread.sleep(1000);
			WebDriverWait wait = new WebDriverWait(driver, 150);
			wait.until(expectation);
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}

	}

	@Override
	protected boolean isValidPage() {
		return true;
	}

	@Override
	protected void waitForPageLoad() {
	}

	/**
	 * 
	 * @param locId
	 *            - First menu items loc id
	 * @param locId2
	 *            - Second menu items loc id
	 * @param locId3
	 *            - Third menu items loc id
	 * @return Click the menu item
	 */
	public void iMenuClick(String locId, String locId2, String locId3) {
		Actions builder = new Actions(this.driver);
		int tryCount = 0;
		String iMsg = "";
		String lstr = "??";

		String transIdUI = "Click Menu";
		String trDescr = null;
		try {
			Robot r = new Robot();
			r.mouseMove(1, 1);
			By lby = getByForLocator(locId);
			lstr = getLocStrForLocator(locId);
			WebDriverWait wait = new WebDriverWait(driver, 50);
			iMsg = "Finding clickable WebElement: locId=[" + locId + "], locStr=[" + lstr + "]  by=" + lby;
			log.trace(iMsg);
			builder.moveToElement(driver.findElement(getByForLocator(locId))).build().perform();
			if (locId3 != null) {
				builder.moveToElement(driver.findElement(getByForLocator(locId2))).build().perform();
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(getLocStrForLocator(locId3))));
				testLocatorClickable(locId3).click();
			} else
				testLocatorClickable(locId2).click();
			log.debug("Successfully clicked [" + locId + "] - " + trDescr + "\n\n");
			endTransaction(transIdUI, trDescr, "Pass", null);
			return;
		} catch (Exception e) {
			String eMsg = e.getMessage();
			String cMsg = "Failed to successfully click [" + locId + "] and check --- ";
			if (!(this.pInteractive)) {
				endTransaction(transIdUI, trDescr, "Fail", null);
				throw new RuntimeException(cMsg + " --- " + iMsg + "---" + eMsg);
			}

			endTransaction("Action", trDescr, "Retry", null);
			++tryCount;
			log.error("Retry iClick #" + tryCount);
		}
		throw new RuntimeException("Unable to find/click we for locator " + locId);
	}

	public String getFileName(String absPath) {
		try {
			Path p = Paths.get(absPath);
			return p.getFileName().toString().trim();
		} catch (Exception e) {
			log.error("Geting file name failed." + e.getMessage());
		}
		return "";
	}

	public void browserDependentClick(WebElement we, String clickType) {
		try {
			testElementClickable(we);
			switch (clickType) {
			case "click":
				we.click();
				break;
			case "sendKeys":
				we.sendKeys(Keys.ENTER);
				break;
			case "jsClick":
				jsClick(we);
				break;
			case "actionClick":
				actionClick(we);
				break;
			case "doubleClick":
				doubleClick(we);
				break;
			}
		} catch (Exception ex) {
			String eMsg = "Error in browserDependentClick method: " + ex.getMessage();
			log.error(eMsg);
		}
	}

	protected void actionClick(WebElement element) {
		try {
			waitForElement(element);
			Actions builder = new Actions(this.browser);
			builder.moveToElement(element);
			builder.click();
			builder.sendKeys(Keys.ENTER);
			builder.build().perform();

		}

		catch (RuntimeException ex) {
			log.error(ex.getMessage());
			throw ex;
		}
	}

	/**
	 * 
	 * @param locId
	 *            - Location ID for the the element text to be entered
	 * @param sText
	 *            - Text to be entered
	 * @param transactionDescription
	 *            Description of the transactions
	 * @return - Enters the text in the element. It will not match the text to
	 *         be entered and text entered.
	 */
	public void iEnterText_withoutVerify(String locId, String sText, String transactionDescription) {
		log.debug("\n**********************    enterText locId=[" + locId + "]  ***************\n");

		WebElement we = null;
		boolean edone = false;
		int tryCount = 0;
		String iMsg = "";
		String lstr = "??";

		while (!(edone))
			try {
				By lby = getByForLocator(locId);
				lstr = getLocStrForLocator(locId);
				iMsg = "Finding WebElement to enter text: locId=[" + locId + "], locStr=[" + lstr + "]  by=" + lby;
				log.trace(iMsg);
				we = testLocatorClickable(locId);
				we.clear();
				we.sendKeys(sText);
				log.debug("Successfully entered text [" + sText + "], locId=" + locId);
				highlightWE(we);
				return;
			} catch (Exception e) {
				String cMsg = "Failed to successfully enterText for  [" + locId + "] and check --- ";
				if (!(this.pInteractive)) {
					throw new RuntimeException(cMsg + " --- " + iMsg + "---" + e.getMessage());
				}
				++tryCount;
				log.error("Retry enterText #" + tryCount);
			}
	}

	public void fileUploadSikuli(String filePath) throws FindFailed, Exception {
		// TODO - File Upload currently working fine in local execution and it
		// fails in remote machine
		// We have to find a way to activate the window popup in remote machine
		// instead of local machine

		try {
			sleep(1000);
			Screen screen = new Screen();
			Pattern name = new Pattern(
					System.getProperty("user.dir") + "\\src\\test\\resources\\SikuliImgs\\FileName.png");
			Pattern open = new Pattern(System.getProperty("user.dir") + "\\src\\test\\resources\\SikuliImgs\\Open.png");
			String[] command = { "cscript.exe",
					System.getProperty("user.dir") + "\\src\\test\\resources\\SikuliImgs\\ActivateWindowPopUp.vbs" };
			Runtime.getRuntime().exec(command);
			screen.wait(name, 10);
			if (filePath.isEmpty()) {
				filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\attachements.txt";
			}
			screen.type(name, filePath);
			sleep(2000);
			screen.click(open);
		} catch (FindFailed e) {
			log.error(e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	public void fileUpload(String filePath) throws FindFailed, Exception {
		try {
			String autoITScriptPath = System.getProperty("user.dir")
					+ "\\src\\test\\resources\\SikuliImgs\\FileUpload.exe";
			String[] command = { "cscript.exe",
					System.getProperty("user.dir") + "\\src\\test\\resources\\SikuliImgs\\ActivateWindowPopUp.vbs" };
			Runtime.getRuntime().exec(command);
			if (filePath.isEmpty()) {
				filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\attachements.txt";
			}
			Runtime.getRuntime().exec(autoITScriptPath + " " + filePath);
		} catch (Exception ex) {
			log.error("Exception caught during the File Upload. \n" + ex.getMessage());
		}
	}

	public synchronized void SOAExecutor() throws FindFailed, Exception {
		try {
			String testName = "";
			Process p;
			testName = dataSet.getValue("SOAUpdatorTestName");
			String SOAFolderPath = System.getProperty("user.dir") + "\\src\\test\\resources\\WebTopSOADataFiles\\";
			String SOAFilePattern = dataSet.getValue("SOAFileNamePattern");
			if(!dataDump.getValue("MailIdList").isEmpty() && dataSet.getValue("BCP Enabled").equals("Y")){
				String uniqueEmail = dataDump.getValue("MailIdList");
				String[] command = { "cscript.exe", "//t:180", System.getProperty("user.dir")
					+ "\\src\\test\\resources\\SOAFileUpdatorScripts\\SOAScript.vbs", SOAFolderPath,
					SOAFilePattern, uniqueEmail, testName};
				p = Runtime.getRuntime().exec(command);
			}
			else
			{
				String[] command = { "cscript.exe", "//t:180", System.getProperty("user.dir")
						+ "\\src\\test\\resources\\SOAFileUpdatorScripts\\SOAScript.vbs", SOAFolderPath,
						SOAFilePattern, " ", testName};
				p = Runtime.getRuntime().exec(command);
			}			
			int exitVal = p.waitFor();
			p = Runtime.getRuntime().exec("TASKKILL /IM EXCEL.EXE /IM WSCRIPT.EXE /F ");
			exitVal = p.waitFor();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	public void updateCustDetailsInSOA() throws FindFailed, Exception {
		try {
			Process p;
			String SOAFolderPath = System.getProperty("user.dir") + "\\src\\test\\resources\\WebTopSOADataFiles\\";
			String SOAFilePattern = dataSet.getValue("SOAFileNamePattern");
			String[] command = { "cscript.exe", "//t:180",
					System.getProperty("user.dir")
							+ "\\src\\test\\resources\\SOAFileUpdatorScripts\\UpdateCustomerContactDetails.vbs",
					SOAFolderPath, SOAFilePattern };
			p = Runtime.getRuntime().exec(command);
			int exitVal = p.waitFor();
			p = Runtime.getRuntime().exec("TASKKILL /IM EXCEL.EXE /IM WSCRIPT.EXE /F ");
			exitVal = p.waitFor();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	

	/**
	 * It will scroll down the page by 250 p vertically
	 * 
	 * @throws Exception
	 */
	public void Scroll_Page_Down() throws Exception {

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)", "");
		sleep(2000);
	}

	/**
	 * Return the date and time based on the format specified *
	 * 
	 * @param dateformat
	 *            -- mmddyyyy or mmddyyyy_timestamp
	 * @param operation
	 *            -- Plus or Minus
	 * @param days
	 *            - no. of day needs to be added or subtracted
	 * @param mins
	 *            - no. of minutes needs to be added or subtracted
	 * @param addminutes
	 *            - boolean to add minutes
	 * @return
	 */
	public String getCurrentDateTime_Operation(String dateformat, String operation, int days, int mins,
			boolean addminutes) {
		SimpleDateFormat sdf = null;
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		try {
			if (dateformat.equalsIgnoreCase("mmddyyyy"))
				sdf = new SimpleDateFormat("MM/dd/yyyy");
			else if (dateformat.equalsIgnoreCase("mmddyyyy_timestamp"))
				sdf = new SimpleDateFormat("MM/dd/YYYY hh:mm a");

			if (days != 0) {

				if (operation.equalsIgnoreCase("plus")) {
					c.add(Calendar.DATE, days);
					if (addminutes && dateformat.equalsIgnoreCase("mmddyyyy_timestamp"))
						c.add(Calendar.MINUTE, mins);

				} else if (operation.equalsIgnoreCase("minus")) {
					c.add(Calendar.DATE, -days);
					if (addminutes && dateformat.equalsIgnoreCase("mmddyyyy_timestamp"))
						c.add(Calendar.MINUTE, -mins);

				}
			}
			String output = sdf.format(c.getTime());
			System.out.println("The calculated date is :" + output);
			return output;
		} catch (Exception e) {
			String cMsg = "Failed to Calculate date --- ";
		}
		return null;
	}

	/**
	 * Selects the value in the drop down thru finding the text
	 * 
	 * @param tag
	 *            - Name of the tag which is to be selected in the dropdown
	 * @param value
	 *            - Text contained in the tag to be searched
	 * @param contains
	 *            - True or false value. To verify the contains text or exact
	 *            text
	 */
	public void selectvalue_dropdown(String tag, String value, boolean contains) {
		List<WebElement> we;
		if (contains)
			we = driver.findElements(By.xpath("//" + tag + "[contains(text(),\"" + value + "\")]"));
		else
			we = driver.findElements(By.xpath("//" + tag + "[text()='" + value + "']"));
		int size = we.size();
		if (size > 1) {
			waitForElement(we.get(size-1), 30);
			highlightBlueWE(we.get(size-1));
			we.get(size-1).click();
		} else {
			waitForElement(we.get(0), 30);
			highlightBlueWE(we.get(0));
			we.get(0).click();
		}
	}

	/**
	 * 
	 * @return Current URL of the browser
	 */
	public String getCurrentURL() {
		return browser.getCurrentUrl();
	}

	/**
	 * Unzips the file and place it in a mentioned folder
	 * 
	 * @param zipFile
	 *            - Path of the zipfile to be unzipped
	 * @param outputFolder
	 *            - Path to place the unzipped folder
	 */
	public void unZipIt(String zipFile, String outputFolder) {

		byte[] buffer = new byte[1024];

		try {

			File folder = new File(outputFolder);
			if (!folder.exists()) {
				folder.mkdir();
			}

			ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile));
			ZipEntry ze = zis.getNextEntry();

			while (ze != null) {

				String fileName = ze.getName();
				File newFile = new File(outputFolder + File.separator + fileName);

				log.info("File unzip : " + newFile.getAbsoluteFile());
				new File(newFile.getParent()).mkdirs();

				FileOutputStream fos = new FileOutputStream(newFile);

				int len;
				while ((len = zis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}

				fos.close();
				ze = zis.getNextEntry();
			}

			zis.closeEntry();
			zis.close();

			log.info("Done");

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public String getLatestFile(String Folderpath, String FilePattern) {
		String retPath = null;
		File folder = new File(Folderpath);
		File[] listOfFiles = folder.listFiles();
		long dataModified = 0;
		File reqFile = null;
		for (File file : listOfFiles) {
			if (file.isFile()) {
				if (file.getName().toLowerCase().matches(FilePattern.toLowerCase())) {
					if (dataModified < file.lastModified()) {
						dataModified = file.lastModified();
						reqFile = file;
					}
					retPath = reqFile.getAbsolutePath();
				}
			}
		}
		return retPath;
	}
	
	public boolean checkIfFileExists(String fileName) {		
		try{
			File file = new File(fileName);
			log.info("SOA File check if exists or not-->" + file.exists());
			return file.exists();
		}
		catch (Exception e) {
			log.error("File does not exists: " + fileName + "\n"+ e.getMessage());
			return false;
		}		
	}

	public int getTableRows(String locId) {
		List<WebElement> we = pageORLocator.getLocatorWEList(browser, locId);
		log.info("TOTAL NUMBER OF ROWS IN THIS TABLE = " + we.size());
		report.reportDoneEvent("Get table rows", "No of Rows: " + we.size());
		return we.size();
	}

	public String getPDFContent(String filePath) {
		String pdfText = "";
		try {
			PDDocument pdfFIle;
			pdfFIle = PDDocument.load(new File(filePath));
			PDFTextStripper pdf = new PDFTextStripper();
			pdfText = pdf.getText(pdfFIle).replaceAll("\\r\\n", "##");			
			log.debug("********************** PDF Contents *************** \n\n" + pdfText);
			log.debug("****************************************************** \n\n");
			pdfFIle.close();
			return pdfText;
		} catch (Exception e) {
			log.error("Reading the PDF Contents failed");
			report.reportHardFailEvent("Reading the PDF Contents", "Reading the PDF Contents failed");
		}
		return pdfText;
	}

	protected void jsUpdateDOMProperty(WebElement we, String attribute, String value) {
		JavascriptExecutor executor = (JavascriptExecutor) browser;
		executor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);", we, attribute, value);
	}
	
	public void waitForSpinnerComplete() {		
		try {
			while (isElementPresent(getLocatorWEList(SeatConfiguration.Locators.spinner).get(0))) {
				log.info("Spinner loading is inprogress.");
			}
		} catch (IndexOutOfBoundsException ex) {
			log.info("Spinner loading is done.");
		}
			
	}

	

}
