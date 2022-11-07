package nsf.ecap.Health_Science_suite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import nsf.ecap.Proposals_suite.Proposal_Won;
import nsf.ecap.Work_Order_suite.NewNew_Draft_InReview_No_Fac;
import nsf.ecap.Work_Order_suite.TestSuiteBase;
import nsf.ecap.base.TestBase;
import nsf.ecap.util.ErrorUtil;
import nsf.ecap.util.TestUtil;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.os.WindowsUtils;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.google.common.base.Throwables;

public class TestSuiteBase_HealthScience extends TestSuiteBase {

	public static Proposal_Won BS_P01;
	public static String runmodes[] = null;
	public static int count = -1;

	public static HS_lead_proposal_project_cycle BS01;

	public static String taskType_Audit = "AUDIT";

	// check if the suite has to be skipped or not
	@BeforeSuite
	public void checkSuiteSkip() throws Throwable {

		//currentSuiteName = "Health_Science_suite";
		setCurrentSuiteName("Health_Science_suite");
		fnInitialize();

		fnpMymsg("             ");
		fnpMymsg("             ");

		fnpMymsg("=========================================================================================================================================");
		fnpMymsg("#################### Health Science Suite Start ############################################################");
		fnpMymsg("Checking Runmode of Health_Science_suite");
		if (!TestUtil.isSuiteRunnable(suiteXls, "Health_Science_suite")) {
			fnpMymsg("Skipped Health_Science_suite as the runmode was set to NO");
			//fnpMymsg("#################### Health Science Suite End ############################################################");
			//fnpMymsg("=========================================================================================================================================");
			throw new SkipException("Runmode of Health_Science_suite set to no. So Skipping all tests in Health_Science_suite");
		}

		if (TestUtil.isSuiteRunnable(suiteXls, "Health_Science_suite")) {
			browserName = TestUtil.getBrowserName(suiteXls, "Health_Science_suite");
			excelSiteURL = TestUtil.getExcelSiteURL(suiteXls, currentSuiteName);
			fnpMymsg("Browser Name is :" + browserName);
			fnpMymsg("======================");
			currentSuiteCode = "HS";

			int rowNum = suiteXls.getCellRowNum("Test Suite", "TSID", currentSuiteName);

			suiteXls.setCellData("Test Suite", "Last_Execution", rowNum, fnReturnDateWithTimeForExcel());

		}

	}

	 @AfterSuite(alwaysRun=true)
	//@AfterSuite
	public void cleanUp() {
		try {
			referenceSuite = currentSuiteName;
			fnpMymsg("#################### "+currentSuiteName+" Suite End ############################################################");
			if (driver!=null) {
				driver.quit();
			}
			IsBrowserPresentAlready = false;
			killprocess();
		} catch (Throwable t) {
			// Nothing to do
		}

	}
	
	// Function to Launch the browser and login with user credential details
	public static boolean fnpLaunchBrowserAndLogin(String loginAs) throws Exception {
		boolean present;
		try {

			fnpLaunchBrowser();
			/*
			 * String userName = CONFIG.getProperty("adminUsername"); String
			 * password = CONFIG.getProperty("adminPassword");
			 */
			// String loginAs = CONFIG.getProperty("HSLoginAsName");
			String userName = CONFIG.getProperty("HSUsername");
			String password = CONFIG.getProperty("HSPassword");

			// String siteUrl = CONFIG.getProperty("HSSiteName");

			String siteUrl = null;

			if (excelSiteURL != null) {
				if (excelSiteURL.equalsIgnoreCase("")) {
					siteUrl = CONFIG.getProperty("HSSiteName");
				} else {
					siteUrl = excelSiteURL;

				}
			} else {
				siteUrl = CONFIG.getProperty("HSSiteName");
			}

			//////System.out.println("Hi    Site name is----" + siteUrl);

			driver.get(siteUrl);
			fnpMymsg("Navigating to url --->" + siteUrl);
			driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(Long.parseLong(CONFIG.getProperty("pageLoadTime")), TimeUnit.SECONDS);

/*			
			WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(CONFIG.getProperty("genMax_waitTime")));
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("UserName"))));

*/			
			
			fnpWaitForVisible("UserName");
			
			
			
			if (! iPulse_Invalid_Password_Verification) {
				
				fnpMymsg("First going to check login with invalid/wrong password.");
				fnpDisplayingMessageInFrame_fnpMymsg("First going to check login with invalid/wrong password.",10);
				
				fnpType("OR", "loginAs", loginAs);
				
				fnpType("OR", "UserName_id", userName);

				fnpType("OR", "password_id", "wrong_password");			
				fnpMymsg("Just before login clicked");

				fnpGetORObjectX("Login_id").click();

				fnpLoading_wait();
				fnpMymsg("Just login clicked");
				fnpMymsg("Just after loading wait after login clicked");

/*				if (fnpCheckElementDisplayed("errorMessage_id", 30) ) {
					//throw new Exception("Login is not successfull.");
					fnpMymsg("Login is failed as expected due to wrong password");				
					fnpMouseHover_OR_EvenMultipleObjectsHavingSameXpath_usingSpecialForErrorClassOnly("errorMessage_id");
					String errMsg = fnpGetText_OR_EvenMultipleObjectsHavingSameXpath_specialForExtractingErrorMsgTextOnlyEvenIfNotVisible("errorMessage_id");
					fnpMymsg(" Error  thrown by application after inserting wrong password is --- " + errMsg);


					
				}else{
					if (fnpCheckElementDisplayedImmediately("logOutBtn2_id")) {
						throw new Exception("Login should not be successfull with wrong password.");
					}
				}
				*/
				
				if (fnpCheckElementDisplayed("errorMessage_id", 30)) {
					// throw new Exception("Login is not successfull.");
					fnpMymsg("Login is failed as expected due to wrong password");
					fnpMouseHover_OR_EvenMultipleObjectsHavingSameXpath_usingSpecialForErrorClassOnly("errorMessage_id");
					String errMsg = fnpGetText_OR_EvenMultipleObjectsHavingSameXpath_specialForExtractingErrorMsgTextOnlyEvenIfNotVisible("errorMessage_id");
					fnpMymsg(" Error  thrown by application after inserting wrong password is --- " + errMsg);

				} else {
					String text;
					if (fnpCheckElementDisplayedImmediately("logOutBtn2_linkText")) {
						text="Login should not be successfull with wrong password.";
						fnpDisplayingMessageInFrame_fnpMymsg(text, 10);
						Thread.sleep(1000);
						throw new Exception(text);
					}else{
						text="Error message on inserting either wrong username or wrong password is not coming, hence failed as error message should come on wrong credential details.";
						fnpMymsg(text);
						fnpDisplayingMessageInFrame_fnpMymsg(text, 10);
						Thread.sleep(1000);
						throw new Exception(text);

					}
				}
				
				
				
				
				
				fnpMymsg("Now going to refresh the screen and again login with valid user credential details.");
				driver.navigate().refresh();
			//	Thread.sleep(2000);
				iPulse_Invalid_Password_Verification=true;
			}
			
			
			
			
			
			
			
			
			fnpGetORObjectX("UserName").clear();
			fnpGetORObjectX("loginAs").clear();

			// fnpGetORObjectX("loginAs").sendKeys(loginAs);
			fnpType("OR", "loginAs", loginAs);
			// fnpGetORObjectX("UserName").sendKeys(userName);
			fnpType("OR", "UserName", userName);
			Thread.sleep(500);
			// fnpGetORObjectX("password").sendKeys(password);
			fnpType("OR", "password", password);
			Thread.sleep(500);

			Thread.sleep(1000);
			fnpMouseHover("Login");
			fnpClick_OR("Login");
			fnpMymsg("Just login clicked");

			// fnpLoading_wait(); //not worked
			// fnpLoading_waitInsideDialogBox(); //not worked

			// Assert.assertEquals(false,
			// driver.findElement(By.xpath(OR.getProperty("errorMessage"))).isDisplayed());

			/*
			 * if (fnpCheckElementPresenceImmediately("errorMessage")) {
			 * fnpMouseHover("errorMessage"); Thread.sleep(1000); String
			 * errmsg=fnpGetORObjectX("errorMessage").getText();
			 * fnpMymsg("Error while loging " +" i.e --"+errmsg); throw new
			 * Exception("Error while loging " +" i.e --"+errmsg); }
			 */

			if (fnpCheckElementDisplayedImmediately("errorMessage")) {
				throw new Exception("Login is not successfull.");
			}

			// fnpLoading_wait();
			// fnpLoading_wait();
			/*
			 * WebDriverWait wait3 = new WebDriverWait(driver,
			 * Integer.parseInt(CONFIG.getProperty("genMax_waitTime")));
			 * WebElement element3 =
			 * wait3.until(ExpectedConditions.visibilityOfElementLocated
			 * (By.xpath(OR.getProperty("logOutBtn")))); WebElement logOutBtn =
			 * fnpGetORObjectX("logOutBtn"); Assert.assertEquals(true,
			 * logOutBtn.isDisplayed());
			 */

			// fnpWaitForVisible("logOutBtn");
			
			fnpClick_OR("xpathForAck");
			
			fnpWaitForVisible("logOutBtn2");

			present = true;
			// fnpLoading_wait();

			/*
			 * if (!veryFirstTimeAfterLogin) { fnpFetchApplicationVersion();
			 * veryFirstTimeAfterLogin = true; }
			 */

			// fnpFetchApplicationVersion("HS_Application_Version");

			if (!referenceSuite.equalsIgnoreCase(currentSuiteName)) {
				fnpFetchApplicationVersion(currentSuiteName);
				referenceSuite = currentSuiteName;
			}

			fnpClickAtTopWorkAroundForClickingMenu();

		} catch (Throwable t) {

			fnpDesireScreenshot("LoginFailed");
			present = false;
			String stackTrace = Throwables.getStackTraceAsString(t);
			String errorMsg = t.getMessage();
			errorMsg = errorMsg + "\n\n\n\n Login failed.See screenshot 'LoginFailed' \n\n" + stackTrace;
			Exception c = new Exception(errorMsg);
			ErrorUtil.addVerificationFailure(c);
			throw new Exception(errorMsg);// reports

		}

		return present;
	}

	/*
	 * 
	 * 
	 * 
	 * // wait till element get visible public static void fnpWaitForVisible(
	 * String XpathKey) throws Throwable {
	 * 
	 * fnpLoading_wait(); int i = 0;
	 * 
	 * driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty
	 * ("implicitWaitTime")), TimeUnit.SECONDS); WebDriverWait wait = new
	 * WebDriverWait(driver,
	 * Integer.parseInt(CONFIG.getProperty("implicitWaitTime")));
	 * 
	 * int retries = 0; while (true) { i++; try {
	 * wait.until(ExpectedConditions.visibilityOfElementLocated
	 * (By.xpath(OR_HS.getProperty(XpathKey)))); return; } catch
	 * (org.openqa.selenium.StaleElementReferenceException e) { if (retries <
	 * Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) { retries++;
	 * fnpMymsg(retries +
	 * "In staleElementException catch block of fnpWaitForVisible function for "
	 * + XpathKey); continue; } else { throw e; } } catch (Throwable t) { if
	 * (retries < 2) { retries++; fnpMymsg(retries +
	 * "In fnpWaitForVisible 's try catch block  for " + XpathKey); continue; }
	 * else { String stackTrace = Throwables.getStackTraceAsString(t); String
	 * errorMsg = t.getMessage(); errorMsg = errorMsg +
	 * "\n\n\n\n Object with name '"+XpathKey+" is not present/found. \n\n" +
	 * stackTrace;
	 * 
	 * Exception c = new Exception(errorMsg); throw c; } } }
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * // Function to find and return the object using Xpath public static
	 * WebElement fnpGetORObjectX_usingLinkText( String ORlinkName) throws
	 * Exception { int retries = 0;
	 * 
	 * while (true) { try { // fnpLoading_wait();
	 * driver.manage().timeouts().implicitlyWait
	 * (Long.parseLong(CONFIG.getProperty("implicitWaitTime")),
	 * TimeUnit.SECONDS); return
	 * driver.findElement(By.linkText(OR.getProperty(ORlinkName))); } catch
	 * (org.openqa.selenium.StaleElementReferenceException s) { if (retries <
	 * Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) { retries++;
	 * fnpMymsg(retries +
	 * "In staleElementException catch block of fnpGetORObjectX_usingLinkText function for "
	 * + ORlinkName); continue; } }
	 * 
	 * catch (Throwable t) { // fnpDesireScreenshot("NotPresent" + ORlinkName);
	 * throw new Exception("Unable to find element [" + ORlinkName +
	 * "] ,plz see screenshot [NotPresent" + ORlinkName + "]"); } }
	 * 
	 * }
	 * 
	 * 
	 * // Function to find and return the object using Xpath public static
	 * WebElement fnpGetORObjectX( String XpathKey,Properties ORName) throws
	 * Exception { int retries = 0; while (true) { try { // fnpLoading_wait();
	 * driver
	 * .manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty
	 * ("implicitWaitTime")), TimeUnit.SECONDS); return
	 * driver.findElement(By.xpath(ORName.getProperty(XpathKey)));
	 * 
	 * } catch (org.openqa.selenium.StaleElementReferenceException s) { if
	 * (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
	 * retries++; fnpMymsg(retries +
	 * "In staleElementException catch block of fnpGetORObjectX function for " +
	 * XpathKey); continue; } }
	 * 
	 * catch (Throwable t) { // fnpDesireScreenshot("NotPresent" + XpathKey);
	 * throw new Exception(t.getMessage() +
	 * "   So,Unable to find element with name [" + XpathKey +
	 * "] ,plz see screenshot [NotPresent" + XpathKey + "]"); } }
	 * 
	 * }
	 */

	// Search workd order and click Edit button
	public static void fnpSearchClientOnly(String clientNo) throws Throwable {

		fnpWaitForVisible_usingLinkNameInOR("Menu");

/*		
		WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(CONFIG.getProperty("genMax_waitTime")));
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(OR.getProperty("Menu"))));

	*/	
		
/*		
		WebDriverWait wait1 = new WebDriverWait(driver, Integer.parseInt(CONFIG.getProperty("genMax_waitTime")));
		WebElement element1 = wait1.until(ExpectedConditions.visibilityOf(fnpGetORObjectX_usingLinkText("Menu")));
		
	*/	
		

		WebElement menu = fnpGetORObjectX_usingLinkText("Menu");
		Actions action = new Actions(driver);
		action.moveToElement(menu).perform();
		Thread.sleep(500);
		fnpMouseHover_LinkNameInOR("Search_Resource");
		Thread.sleep(500);

/*		WebDriverWait wait5 = new WebDriverWait(driver, Integer.parseInt(CONFIG.getProperty("genMax_waitTime")));
		WebElement element5 = wait5.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(OR.getProperty("SearchClient_LinkName"))));
		
*/		
		Thread.sleep(500);
		// Thread.sleep(1000);

		WebElement menu2 = fnpGetORObjectX_usingLinkText("SearchClient_LinkName");
		Actions action2 = new Actions(driver);
		action2.moveToElement(menu2).perform();

		fnpGetORObjectX_usingLinkText("SearchClient_LinkName").click();
		fnpLoading_wait();
		// fnpLoading_wait();

		fnpWaitForVisible("Corp_FacilityTxtBx");
		// fnpLoading_wait();
		// fnpLoading_wait();
		fnpWaitForVisible("Corp_FacilityTxtBx");
		// fnpGetORObjectX("Corp_FacilityTxtBx").sendKeys(clientNo);
		fnpType("OR", "Corp_FacilityTxtBx", clientNo);

		/*
		 * fnpGetORObjectX("MainSearchButton").click(); fnpLoading_wait();
		 */
		fnpClick_OR("MainSearchButton");
		String s = fnpFetchFromStSearchTable(1, 1);
		int j = 0;
		fnpLoading_wait();
		while (s.contains("No records found") && j < 100) {
			j++;
			Thread.sleep(1000);
			s = fnpFetchFromStSearchTable(1, 1);
		}
		driver.findElement(By.linkText(s)).click();

		// fnpLoading_wait();

		fnpClick_OR("EditClientBtn");

		fnpLoading_wait();
		fnpMymsg("Edit button has been clicked");

	}

	// Function to fetch bill rate from DB
	public static String fnpDeleteTimeSheetFromDB(String empNO) throws Throwable {
		String rate = null;

		fnpMymsg("******************");
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			fnpMymsg("******* Oracle JDBC Driver Registered *******");
		} catch (ClassNotFoundException e) {
			fnpMymsg("****************Oracle JDBC Driver is NOT found *************");
			fnpMymsg("########################################################################");
			e.printStackTrace();
			throw new Exception(e.getMessage());
			// return;
		}

		Connection connection = null;
		try {

				
			  connection=fnpGetDBConnection();
			
			Statement stmt = connection.createStatement();
			@SuppressWarnings("SQL_PREPARED_STATEMENT_GENERATED_FROM_NONCONSTANT_STRING")
			//String deleteQuery0 = "DELETE FROM ECAP.EC_TIMESHEET_STATUS_HISTORY th" + "  WHERE Th.TIMESHEET_SEQ IN (SELECT SEQ FROM EC_TIMESHEETS t WHERE T.EMP_NO ='" + empNO + "')";
			String deleteQuery0 = String.format("DELETE FROM ECAP.EC_TIMESHEET_STATUS_HISTORY th WHERE Th.TIMESHEET_SEQ IN (SELECT SEQ FROM EC_TIMESHEETS t WHERE T.EMP_NO  = '%s') ", empNO);
			
			@SuppressWarnings("SQL_PREPARED_STATEMENT_GENERATED_FROM_NONCONSTANT_STRING")
			//String deleteQuery1 = "DELETE FROM EC_TIMESHEET_DETAILS td" + "  WHERE TD.TIMESHEET_SEQ IN (SELECT SEQ FROM EC_TIMESHEETS t WHERE T.EMP_NO ='" + empNO + "')";
			String deleteQuery1 = String.format("DELETE FROM EC_TIMESHEET_DETAILS td  WHERE TD.TIMESHEET_SEQ IN (SELECT SEQ FROM EC_TIMESHEETS t WHERE T.EMP_NO ='%s')",empNO);
			
			@SuppressWarnings("SQL_PREPARED_STATEMENT_GENERATED_FROM_NONCONSTANT_STRING")
			//String deleteQuery2 = "DELETE FROM EC_TIMESHEETS t" + "  WHERE T.EMP_NO ='" + empNO + "'";
			String deleteQuery2 = String.format("DELETE FROM EC_TIMESHEETS t  WHERE T.EMP_NO ='%s'",empNO);
			
			@SuppressWarnings("SQL_PREPARED_STATEMENT_GENERATED_FROM_NONCONSTANT_STRING")
			
/*			String deleteQuery3 = "DELETE FROM EC_ALERT_TRANSACTION " + "WHERE SEQ IN (" + "select A.SEQ from EC_ALERT_TRANSACTION a , EC_ALERT_MASTER b, EC_ENTITY_MASTER c "
					+ "where a.alem_seq= b.seq " + "and b.entity_master_seq=c.seq " + "and c.entity_primary_table ='EC_TIMESHEETS' "
					+ "and a.ENTITY_ID IN (SELECT to_char(SEQ) FROM EC_TIMESHEETS t WHERE T.EMP_NO ='" + empNO + "')" + ")";
			
			*/

			String deleteQuery3 = String.format("DELETE FROM EC_ALERT_TRANSACTION WHERE SEQ IN (select A.SEQ from EC_ALERT_TRANSACTION a , EC_ALERT_MASTER b, EC_ENTITY_MASTER c where a.alem_seq= b.seq and b.entity_master_seq=c.seq and c.entity_primary_table ='EC_TIMESHEETS' and a.ENTITY_ID IN (SELECT to_char(SEQ) FROM EC_TIMESHEETS t WHERE T.EMP_NO ='%s'))",empNO);
			
			
			
		//	stmt.executeUpdate(deleteQuery0);
			
/*			stmt.executeUpdate(deleteQuery3);
			stmt.executeUpdate(deleteQuery1);
			stmt.executeUpdate(deleteQuery2);
*/
			

						




			
			int a3=stmt.executeUpdate(deleteQuery3);
			fnpMymsg("@  --- Rows deleted (using this query-- DELETE FROM EC_ALERT_TRANSACTION  )--"+a3);
			//System.out.println("@  --- Rows deleted (using this query-- DELETE FROM EC_ALERT_TRANSACTION  )--"+a3);
			
			
			
			int a1=stmt.executeUpdate(deleteQuery1);
			fnpMymsg("@  --- Rows deleted (using this query-- DELETE FROM EC_TIMESHEET_DETAILS td )--"+a1);
			//System.out.println("@  --- Rows deleted (using this query-- DELETE FROM EC_TIMESHEET_DETAILS td )--"+a1);
			
			
			
			int a0=stmt.executeUpdate(deleteQuery0);
			fnpMymsg("@  --- Rows deleted (using this query-- DELETE FROM EC_TIMESHEET_STATUS_HISTORY th )--"+a0);
			//System.out.println("@  --- Rows deleted (using this query-- DELETE FROM EC_TIMESHEET_STATUS_HISTORY th )--"+a0);
			
			
			int a2=stmt.executeUpdate(deleteQuery2);
			fnpMymsg("@  --- Rows deleted (using this query-- DELETE FROM EC_TIMESHEETS t )--"+a2);
			//System.out.println("@  --- Rows deleted (using this query-- DELETE FROM EC_TIMESHEETS t )--"+a2);
			
			
			connection.commit();

		//	connection.close();

		} catch (SQLException e) {
			fnpMymsg("*********************************************** Connection Failed! with Database,   Getting Error >>  " + e.getMessage().trim());
			fnpMymsg("=========================================================================================================================================");
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		
		finally{
			connection.close();
		}
		fnpMymsg("******************");

		return rate;
	}

/*	
	
	
	public static void fnpLaunchBrowser() throws Throwable {

		startExecutionTime = fnpTimestamp();
		// fnpFirstTimeInitializationMethod();

		fnpMymsg("Checking Browser type");

		String browser = browserName;

		if (browser.equalsIgnoreCase("IE")) {

			System.setProperty("webdriver.ie.driver", CONFIG.getProperty("IEDriverPath"));

			DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
			caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);

			// caps.setCapability("IGNORE_ZOOM_SETTING", true);
			// caps.setCapability("IE_ENSURE_CLEAN_SESSION", true);

			caps.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);

			driver = new InternetExplorerDriver(caps);

			driver.manage().window().maximize();

			fnpMymsg("Browser type is IE");
		}

		if (browser.equalsIgnoreCase("firefox")) {

			
			 * FirefoxProfile profile = new FirefoxProfile();
			 * profile.setEnableNativeEvents(true); driver = new
			 * FirefoxDriver(profile);
			 
			
			 * DesiredCapabilities caps = DesiredCapabilities.firefox();
			 * caps.setCapability("DEFAULT_ENABLE_NATIVE_EVENTS", true);
			 * 
			 * caps.setCapability("REQUIRE_WINDOW_FOCUS", true); driver = new
			 * FirefoxDriver(caps);
			 
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			fnpMymsg("Browser type is firefox");
		}

		if (browser.equalsIgnoreCase("chrome")) {

			
			 * System.setProperty("webdriver.chrome.driver",
			 * CONFIG.getProperty("ChromeDriverPath")); driver = new
			 * ChromeDriver(); // driver.manage().window().maximize();
			 
			System.setProperty("webdriver.chrome.driver", CONFIG.getProperty("ChromeDriverPath"));

			DesiredCapabilities caps = DesiredCapabilities.chrome();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("start-maximized");
			
			options.addArguments("--enable-text-input-focus-manager");
			options.addArguments("--disable-popup-blocking");
			options.addArguments("chrome.switches","--disable-extensions");
			
			
			caps.setCapability(ChromeOptions.CAPABILITY, options);
			// driver = new ChromeDriver();
			driver = new ChromeDriver(caps);

			driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);
			fnpMymsg("Browser type is chrome");
		}

	}
	
	*/

	// To check the alert is coming or not
	public static void fnpCommonAlertGeneratedVerificationHS(Hashtable table, String AlertNameInExcel, String AlertHeaderTableXpath_OR, String columnName,
			String AlertDataTableXpath_OR, String AlertFilterBoxXpath_OR, String searchingElement) throws Throwable {

		
		
		String AlertName = (String) table.get(AlertNameInExcel);
		fnpMymsg("Going to verify the alert'" + AlertName + "' whether it is coming/generated or not.");
		

		if ( AlertName==null) {
			throw new Exception("Alert '" + (String) table.get(AlertNameInExcel) + " ' is either not present in excel or not passed to the hashTable/hashMap. ");
		}
		
		if (AlertName.equalsIgnoreCase("") ) {
			throw new Exception("Alert '" + (String) table.get(AlertNameInExcel) + " ' is either not present in excel or not passed to the hashTable/hashMap. ");
		}
		


		AlertName = fnpFetchExactAlertName(AlertName);
		if (AlertName.equalsIgnoreCase("") ) {
			throw new Exception("Alert '" + (String) table.get(AlertNameInExcel) + " ' is either not present or not visible. ");
		}


		String AlertNameXpath = ".//legend[contains(text(),'" + AlertName + "')]";
		String AlertExpandIconXpath = ".//legend[contains(text(),'" + AlertName + "')]/span";

		
		
		
	/**********Below code is being committed on 15-06-2017  as from now onwards ...alert will be generated on the spot, not waiting time, so commenting below loop for waiting alert to be generated *********/	
/*		
		
		int waitCount=0;
		int maxWait=12; // max wait in minutes is half of this maxWait 
		while (true) {
			waitCount++;	
			fnpCheckError(" after loading ");
			if (fnpCheckElementDisplayedImmediately_NOR(AlertNameXpath)) {
			//if (fnpCheckElementPresenceImmediately_NotInOR(Xpath)(AlertNameXpath)) {
				fnpMymsg("Alert '" + AlertName + "' is displayed.");
				break;
			} else {
				fnpMymsg("Alert '" + AlertName + "' is NOT displayed in chance ---"+waitCount);
				fnpCheckError(" after loading ");
				fnpMymsg(" We are in  waiting loop for waiting Alert '"+AlertName+"' displayed  (by batch job behind) ----" + waitCount);
				fnpDisplayingMessageInFrame("  We are in  waiting loop for waiting Alert '"+AlertName+"' displayed  (by batch job behind) ----" + ((double)waitCount/2)+" approx. min.", 8);
				Thread.sleep(1000 * 20 * 1);
				fnpCheckError(" after loading ");				
				fnpMymsg("Just before refreshing--   plz see how much time it take to refresh");
				driver.navigate().refresh();
				fnpMymsg("After refreshing--   plz see how much time it take to refresh");
				fnpLoading_wait();	
			}

			if (waitCount > maxWait) { 
				fnpMymsg(" Script waited for approx. "+((double)waitCount/2)+"  minutes for Alert  '"+AlertName+"' to visible .");
				throw new Exception("  Script waited for approx. "+((double)waitCount/2)+"  minutes for Alert  '"+AlertName+"' to visible .");

			}

		}
		
		
		*/
		
/********************************************************************************************************************************************************/
		
		
		
		
		
		
		
		
		// Thread.sleep(2000);
		//WebElement wbElement = driver.findElement(By.xpath(OR.getProperty("FooterElement")));
		//new Actions(driver).moveToElement(wbElement).perform();

		try {
			// fnpWaitForVisible_NotInOR(AlertNameXpath);
			// fnpWaitTillVisiblityOfElementNClickable(AlertNameXpath);
			
			
			//driver.findElement(By.xpath(AlertNameXpath)).click();
			
		//	WebElement alertElement=fnpGetORObjectX___NOR(AlertNameXpath);
			
		//	Actions act = new Actions(driver);
			

/*			
			int iwhileCounter=0;
			while(!(fnpCheckElementDisplayedImmediately_NOR(AlertExpandIconXpath))){
				iwhileCounter++;
				fnpMymsg("Before scrolling in firefox ---"+iwhileCounter);
				act.moveToElement(alertElement).sendKeys(Keys.ARROW_DOWN).build().perform();
				Thread.sleep(1000);
				//act.moveToElement(alertElement).sendKeys(Keys.ARROW_DOWN).build().perform();
				if (iwhileCounter>10) {
					break;
				}
			}
			
			*/
			
			fnpMymsg("Debug: before scrolling loop");
			fnpMymsg("AlertExpandIcon is visible or not --"+fnpCheckElementDisplayedImmediately_NOR(AlertExpandIconXpath));
			
			JavascriptExecutor js=(JavascriptExecutor)driver;
			int iwhileCounter=0;
			while(!(fnpCheckElementDisplayedImmediately_NOR(AlertExpandIconXpath))){
				iwhileCounter++;
				fnpMymsg("Before scrolling in firefox ---"+iwhileCounter);
				js.executeScript("window.scrollBy(0,300)");
				Thread.sleep(1000);
				//act.moveToElement(alertElement).sendKeys(Keys.ARROW_DOWN).build().perform();
				if (iwhileCounter>10) {
					break;
				}
			}
			
			fnpMymsg("Debug: after scrolling loop");
			
			
			
			
			fnpMymsg("Before clicking alert.");
			//fnpClick_NOR(AlertNameXpath);
			//fnpClick_NOR(AlertExpandIconXpath);
			fnpClick_NOR_withoutWait(AlertExpandIconXpath);
			fnpLoading_wait_withoutErrorChecking(10);
			fnpMymsg("After clicking alert.");
			
		} catch (Throwable t) {
			fnpMymsg("Either Alert '" + AlertName + " ' is  not present/visible/generated or failed due to this error -- "+t.getMessage());
			throw new Exception("Either Alert '" + AlertName + " ' is  not present/visible/generated or failed due to this error -- "+t.getMessage());
		}

		
		//fnpLoading_wait();
		fnpLoading_wait();

		fnpMymsg("Clicked '" + AlertName + "' alert link");

	//	fnpWaitForVisible(AlertFilterBoxXpath_OR);

		int colIndex = fnpFindColumnIndex(AlertHeaderTableXpath_OR, columnName);

/*		// fnpGetORObjectX(AlertFilterBoxXpath_OR).sendKeys(searchingElement);
		fnpType("OR", AlertFilterBoxXpath_OR, searchingElement);
	*/	
		
		String filterbox_xpath=OR.getProperty(AlertHeaderTableXpath_OR)+"/tr/th["+colIndex+"]/input";
		fnpType("", filterbox_xpath, searchingElement);

		// Thread.sleep(1000);
		// fnpLoading_wait();
		// fnpLoading_wait();
		fnpLoading_wait();
		Thread.sleep(2000);

		WebDriverWait waitvar = new WebDriverWait(driver, Long.parseLong(CONFIG.getProperty("implicitWaitTime")));
		waitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(AlertDataTableXpath_OR)))).isEnabled();

		// fnpLoading_wait();
		String actualFirstColValue = fnpFetchFromTable(AlertDataTableXpath_OR, 1, 1);
		Assert.assertFalse(actualFirstColValue.contains("No records found"), "Alert in '" + AlertName + "' has NOT been generated");
		
		
/*		try{
		Assert.assertFalse(actualFirstColValue.contains("Loading"), "Alert in '" + AlertName + "' has NOT been generated");
		}catch(Throwable t){
			try
		}
		*/
		
		int whileLoopCounter=0;
		int maxWaitTime=Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))*4;
		while(true)	{
			try{
				whileLoopCounter++;
			
				actualFirstColValue = fnpFetchFromTable(AlertDataTableXpath_OR, 1, 1);
				if ((actualFirstColValue.contains("Loading") || (actualFirstColValue.contains("loading") ) )) {
					Thread.sleep(1000);
					if (whileLoopCounter>maxWaitTime) {
						throw new Exception("Still Loading the alert even after ---"+maxWaitTime+" seconds.");
					}
				
					
				} else {
					break;
				}
			
		}catch(Throwable tt){
			throw tt;
		}
		}
		
		

		int retries = 0;
		while (true) {

			try {
				retries++;
				String s = fnpFetchFromTable(AlertDataTableXpath_OR, 1, colIndex);
				if (s.equalsIgnoreCase(searchingElement)) {
					break;

				} else {
					if ((retries > Integer.parseInt(CONFIG.getProperty("genMax_waitTime")))) {
						break;

					} else {
						Thread.sleep(1000);
						continue;
					}
				}
			} catch (org.openqa.selenium.StaleElementReferenceException s) {
				if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					retries++;
					fnpMymsg(retries + "In staleElementException catch block of fnpCommonAlertGeneratedVerification function. ");
					continue;
				} else {
					throw s;
				}
			}

			catch (Throwable t) {
				fnpDesireScreenshot("fnpCommonAlertGeneratedVerification_failed");
				String stackTrace = Throwables.getStackTraceAsString(t);
				String errorMsg = t.getMessage();
				errorMsg = errorMsg + "\n\n\n\n\n\nfailed in fnpCommonAlertGeneratedVerification ,plz see screenshot 'fnpCommonAlertGeneratedVerification_failed'.\n\n" + stackTrace;
				throw new Exception(errorMsg);
			}

		}

		// fnpTableRefreshedWait(AlertDataTableXpath_OR);
		//fnpMymsg("Inserted  '" + workOrderNo + "' work order no in '" + AlertName + "' WO filter box");

		actualFirstColValue = fnpFetchFromTable(AlertDataTableXpath_OR, 1, 1);

		Assert.assertFalse(actualFirstColValue.contains("No records found"), "Alert in '" + AlertName + "' has NOT been generated");

		String ActualSearchedResult = fnpFetchFromTable(AlertDataTableXpath_OR, 1, colIndex);
		Assert.assertEquals(ActualSearchedResult, searchingElement, "Alert in '" + AlertName + "' has NOT been generated");
		fnpMymsg("++++++++++++++++++++++++++++++++++++++++++++++++");
		fnpMymsg(" Hence , Alert  '" + AlertName + "' has been generated successfully.");
		fnpMymsg("++++++++++++++++++++++++++++++++++++++++++++++++");
		fnpMymsg("");

		// ++++++++++++++++++++++++++++++++++++++++++++++++

	}

	

	// To check the alert is coming or not //passing col name of excel having alert
	public static void fnpCommonAlertClickedFirstRecord_in_specifiedColumnName_withoutSearching_wasworkingfinebutnotusingnow(Hashtable table, String AlertNameInExcel, String AlertHeaderTableXpath_OR, String columnName,
			String AlertDataTableXpath_OR) throws Throwable {

		
		
		String AlertName = (String) table.get(AlertNameInExcel);
		fnpMymsg("Going to verify the alert'" + AlertName + "' whether it is coming/generated or not.");
		

		if ( AlertName==null) {
			throw new Exception("Alert '" + (String) table.get(AlertNameInExcel) + " ' is either not present in excel or not passed to the hashTable/hashMap. ");
		}
		
		if (AlertName.equalsIgnoreCase("") ) {
			throw new Exception("Alert '" + (String) table.get(AlertNameInExcel) + " ' is either not present in excel or not passed to the hashTable/hashMap. ");
		}
		


		AlertName = fnpFetchExactAlertName(AlertName);
		if (AlertName.equalsIgnoreCase("") ) {
			throw new Exception("Alert '" + (String) table.get(AlertNameInExcel) + " ' is either not present or not visible. ");
		}


		String AlertNameXpath = ".//legend[contains(text(),'" + AlertName + "')]";
		String AlertExpandIconXpath = ".//legend[contains(text(),'" + AlertName + "')]/span";



		try {

			
			fnpMymsg("Debug: before scrolling loop");
			fnpMymsg("AlertExpandIcon is visible or not --"+fnpCheckElementDisplayedImmediately_NOR(AlertExpandIconXpath));
			
			JavascriptExecutor js=(JavascriptExecutor)driver;
			int iwhileCounter=0;
			while(!(fnpCheckElementDisplayedImmediately_NOR(AlertExpandIconXpath))){
				iwhileCounter++;
				fnpMymsg("Before scrolling in firefox ---"+iwhileCounter);
				js.executeScript("window.scrollBy(0,300)");
				Thread.sleep(1000);
				//act.moveToElement(alertElement).sendKeys(Keys.ARROW_DOWN).build().perform();
				if (iwhileCounter>10) {
					break;
				}
			}
			
			fnpMymsg("Debug: after scrolling loop");
			
			
			
			
			fnpMymsg("Before clicking alert.");
			//fnpClick_NOR(AlertNameXpath);
			//fnpClick_NOR(AlertExpandIconXpath);
			fnpClick_NOR_withoutWait(AlertExpandIconXpath);
			fnpLoading_wait_withoutErrorChecking(10);
			fnpMymsg("After clicking alert.");
			
		} catch (Throwable t) {
			fnpMymsg("Either Alert '" + AlertName + " ' is  not present/visible/generated or failed due to this error -- "+t.getMessage());
			throw new Exception("Either Alert '" + AlertName + " ' is  not present/visible/generated or failed due to this error -- "+t.getMessage());
		}

		
		fnpLoading_wait();
		//fnpLoading_wait();

		fnpMymsg("Clicked '" + AlertName + "' alert link");


		int colIndex = fnpFindColumnIndex(AlertHeaderTableXpath_OR, columnName);

		
		String filterbox_xpath=OR.getProperty(AlertHeaderTableXpath_OR)+"/tr/th["+colIndex+"]/input";
		//fnpType("", filterbox_xpath, searchingElement);
		//fnpWaitTillVisiblityOfElementNClickable_NOR(filterbox_xpath);
		fnpWaitForVisible_NotInOR(filterbox_xpath);

		fnpLoading_wait();
		Thread.sleep(2000);

		WebDriverWait waitvar = new WebDriverWait(driver, Long.parseLong(CONFIG.getProperty("implicitWaitTime")));
		waitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(AlertDataTableXpath_OR)))).isEnabled();

		// fnpLoading_wait();
		String actualFirstColValue = fnpFetchFromTable(AlertDataTableXpath_OR, 1, 1);
		Assert.assertFalse(actualFirstColValue.contains("No records found"), "Alert in '" + AlertName + "' has NOT been generated");
		
		
/*		try{
		Assert.assertFalse(actualFirstColValue.contains("Loading"), "Alert in '" + AlertName + "' has NOT been generated");
		}catch(Throwable t){
			try
		}
		*/
		
		int whileLoopCounter=0;
		int maxWaitTime=Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))*4;
		while(true)	{
			try{
				whileLoopCounter++;
			
				actualFirstColValue = fnpFetchFromTable(AlertDataTableXpath_OR, 1, 1);
				if ((actualFirstColValue.contains("Loading") || (actualFirstColValue.contains("loading") ) )) {
					Thread.sleep(1000);
					if (whileLoopCounter>maxWaitTime) {
						throw new Exception("Still Loading the alert even after ---"+maxWaitTime+" seconds.");
					}
				
					
				} else {
					break;
				}
			
		}catch(Throwable tt){
			throw tt;
		}
		}
		
		

		actualFirstColValue = fnpFetchFromTable(AlertDataTableXpath_OR, 1, 1);

		Assert.assertFalse(actualFirstColValue.contains("No records found"), "Alert in '" + AlertName + "' has NOT been generated");

		String linkValueInSpecifiedColumn = fnpFetchFromTable(AlertDataTableXpath_OR, 1, colIndex);
		//Assert.assertEquals(ActualSearchedResult, searchingElement, "Alert in '" + AlertName + "' has NOT been generated");
		fnpMymsg("++++++++++++++++++++++++++++++++++++++++++++++++");
		fnpMymsg(" Hence , Alert  '" + AlertName + "' has been generated successfully.");
		fnpMymsg("++++++++++++++++++++++++++++++++++++++++++++++++");
		fnpMymsg("");

		// ++++++++++++++++++++++++++++++++++++++++++++++++
		
		
		fnpMymsg("Going to click this link '" + linkValueInSpecifiedColumn + "' in Alert '" + AlertName + "'.");
		
		driver.findElement(By.linkText(linkValueInSpecifiedColumn)).click();
		
		fnpLoading_wait();

	}
	
	
	/**** To check the alert is coming or not ****/
	public static void fnpCommonAlertGeneratedVerification(Hashtable table, String alertName, String columnName,String searchValue) throws Throwable {

		
		
		//String AlertName = (String) table.get(AlertNameInExcel);
		fnpMymsg("Going to verify the alert'" + alertName + "' whether it is coming/generated or not.");
		

/*		if ( alertName==null) {
			throw new Exception("Alert '" + (String) table.get(AlertNameInExcel) + " ' is either not present in excel or not passed to the hashTable/hashMap. ");
		}
		*/
		if (alertName.equalsIgnoreCase("") ) {
			throw new Exception("Alert '" + alertName + " ' is either not present in excel or not passed to the hashTable/hashMap. ");
		}
		


		//AlertName = fnpFetchExactAlertName(AlertName);
		if (alertName.equalsIgnoreCase("") ) {
			throw new Exception("Alert '" + alertName + " ' is either not present or not visible. ");
		}


		String AlertNameXpath = ".//legend[contains(text(),'" + alertName + "')]";
		String AlertExpandIconXpath = ".//legend[contains(text(),'" + alertName + "')]/span";



		try {

			
			fnpMymsg("Debug: before scrolling loop");
			fnpMymsg("AlertExpandIcon is visible or not --"+fnpCheckElementDisplayedImmediately_NOR(AlertExpandIconXpath));
			
			JavascriptExecutor js=(JavascriptExecutor)driver;
			int iwhileCounter=0;
			while(!(fnpCheckElementDisplayedImmediately_NOR(AlertExpandIconXpath))){
				iwhileCounter++;
				fnpMymsg("Before scrolling in firefox ---"+iwhileCounter);
				js.executeScript("window.scrollBy(0,300)");
				Thread.sleep(1000);
				//act.moveToElement(alertElement).sendKeys(Keys.ARROW_DOWN).build().perform();
				if (iwhileCounter>10) {
					break;
				}
			}
			
			js.executeScript("window.scrollBy(0,600)");
			fnpMymsg("Debug: after scrolling loop");
			fnpLoading_wait();
			
			
			
			fnpMymsg("Before clicking alert.");
			//fnpClick_NOR(AlertNameXpath);
			//fnpClick_NOR(AlertExpandIconXpath);
			fnpClick_NOR_withoutWait(AlertExpandIconXpath);
			fnpLoading_wait_withoutErrorChecking(10);
			fnpMymsg("After clicking alert.");
			
		} catch (Throwable t) {
			fnpMymsg("Either Alert '" + alertName + " ' is  not present/visible/generated or failed due to this error -- "+t.getMessage());
			throw new Exception("Either Alert '" + alertName + " ' is  not present/visible/generated or failed due to this error -- "+t.getMessage());
		}

		
	//	fnpLoading_wait();
	//	fnpLoading_wait();

		fnpMymsg("Clicked '" + alertName + "' alert link");
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,800)");
		Thread.sleep(1000);
		
		

		//int colIndex = fnpFindColumnIndex(AlertHeaderTableXpath_OR, columnName);
		String tableHeaderXpath=AlertNameXpath+"/following-sibling::div//thead";
		String table_header_id=driver.findElement(By.xpath(tableHeaderXpath)).getAttribute("id");
		String updatedTableHeaderXpath=".//*[@id='"+table_header_id+"']";
		int colIndex =fnpFindColumnIndex_NOR(updatedTableHeaderXpath, columnName);

		
		//String filterbox_xpath=OR.getProperty(AlertHeaderTableXpath_OR)+"/tr/th["+colIndex+"]/input";
		String filterbox_xpath=updatedTableHeaderXpath+"/tr/th["+colIndex+"]/input";

		fnpWaitForVisible_NotInOR(filterbox_xpath);


		fnpType("", filterbox_xpath, searchValue);
		//driver.findElement(By.xpath(filterbox_xpath)).sendKeys(searchValue);
		fnpLoading_wait_specialCase(5);
		fnpLoading_wait_CheckLoadingIconOnlyIniPulse();

/*		
		String tableDataXpath=tableHeaderXpath+"/../tbody";
		String table_Data_id=driver.findElement(By.xpath(tableDataXpath)).getAttribute("id");
		String updatedTableDataXpath=".//*[@id='"+table_Data_id+"']";*/
		
		String updatedTableDataXpath=getTableData_fromAlertName( alertName);
		
		
		
		WebDriverWait waitvar = new WebDriverWait(driver, Long.parseLong(CONFIG.getProperty("implicitWaitTime")));
		waitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(updatedTableDataXpath))).isEnabled();

		// fnpLoading_wait();
		//String actualFirstColValue = fnpFetchFromTable(AlertDataTableXpath_OR, 1, 1);
		String actualFirstColValue = fnpFetchFromTable_NOR(updatedTableDataXpath, 1, 1);
		Assert.assertFalse(actualFirstColValue.contains("No records found"), "Alert in '" + alertName + "' has NOT been generated");
		
		
/*		try{
		Assert.assertFalse(actualFirstColValue.contains("Loading"), "Alert in '" + AlertName + "' has NOT been generated");
		}catch(Throwable t){
			try
		}
		*/
		
		int whileLoopCounter=0;
		int maxWaitTime=Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))*4;
		while(true)	{
			try{
				whileLoopCounter++;
			
				//actualFirstColValue = fnpFetchFromTable(AlertDataTableXpath_OR, 1, 1);
				actualFirstColValue = fnpFetchFromTable_NOR(updatedTableDataXpath, 1, 1);
				if ((actualFirstColValue.contains("Loading") || (actualFirstColValue.contains("loading") ) )) {
					Thread.sleep(1000);
					if (whileLoopCounter>maxWaitTime) {
						throw new Exception("Still Loading the alert even after ---"+maxWaitTime+" seconds.");
					}
				
					
				} else {
					break;
				}
			
		}catch(Throwable tt){
			throw tt;
		}
		}
		
		

		//actualFirstColValue = fnpFetchFromTable(AlertDataTableXpath_OR, 1, 1);
		actualFirstColValue = fnpFetchFromTable_NOR(updatedTableDataXpath, 1, 1);

		Assert.assertFalse(actualFirstColValue.contains("No records found"), "Alert in '" + alertName + "' has NOT been generated");

		//String linkValueInSpecifiedColumn = fnpFetchFromTable(AlertDataTableXpath_OR, 1, colIndex);
		
		//Assert.assertEquals(ActualSearchedResult, searchingElement, "Alert in '" + AlertName + "' has NOT been generated");
		fnpMymsg("++++++++++++++++++++++++++++++++++++++++++++++++");
		fnpMymsg(" Hence , Alert  '" + alertName + "' has been generated successfully.");
		fnpMymsg("++++++++++++++++++++++++++++++++++++++++++++++++");
		fnpMymsg("");

		// ++++++++++++++++++++++++++++++++++++++++++++++++
		

		

	}
	
	/**** To check the alert is coming or not ****/
	public static void fnpCommonAlertClickedFirstRecord_in_specifiedColumnName_afterSearching(Hashtable table, String alertName, int rowNo, String columnName,String searchValue) throws Throwable {

		 fnpCommonAlertGeneratedVerification( table,  alertName,  columnName, searchValue);
		 
		 String updatedTableDataXpath=getTableData_fromAlertName( alertName);	
		 String updatedHHeaderXpath=getTableHeader_fromAlertName( alertName);	
		 int colIndex =fnpFindColumnIndex_NOR(updatedHHeaderXpath, columnName);
		 
		 
		 
		String linkValueInSpecifiedColumn = fnpFetchFromTable_NOR(updatedTableDataXpath, rowNo, colIndex);
		fnpMymsg("Going to click this link '" + linkValueInSpecifiedColumn + "' in row '"+rowNo+"' in this Alert '" + alertName + "'.");
			
		
		//driver.findElement(By.linkText(linkValueInSpecifiedColumn)).click();
		fnpClickInTable_NOR(updatedTableDataXpath, rowNo, colIndex);
		
		fnpLoading_wait();
			
			
			

	}
	
	

	
	
	
	//alert name is passing, instead of col name having alert
	public static void fnpCommonAlertClickedFirstRecord_in_specifiedColumnName_withoutSearching(Hashtable table, String alertName, String AlertHeaderTableXpath_OR, String columnName,
			String AlertDataTableXpath_OR) throws Throwable {

		
		
		//String AlertName = (String) table.get(AlertNameInExcel);
		fnpMymsg("Going to verify the alert'" + alertName + "' whether it is coming/generated or not.");
		

/*		if ( alertName==null) {
			throw new Exception("Alert '" + (String) table.get(AlertNameInExcel) + " ' is either not present in excel or not passed to the hashTable/hashMap. ");
		}
		*/
		if (alertName.equalsIgnoreCase("") ) {
			throw new Exception("Alert '" + alertName + " ' is either not present in excel or not passed to the hashTable/hashMap. ");
		}
		


		//AlertName = fnpFetchExactAlertName(AlertName);
		if (alertName.equalsIgnoreCase("") ) {
			throw new Exception("Alert '" + alertName + " ' is either not present or not visible. ");
		}


		String AlertNameXpath = ".//legend[contains(text(),'" + alertName + "')]";
		String AlertExpandIconXpath = ".//legend[contains(text(),'" + alertName + "')]/span";



		try {

			
			fnpMymsg("Debug: before scrolling loop");
			fnpMymsg("AlertExpandIcon is visible or not --"+fnpCheckElementDisplayedImmediately_NOR(AlertExpandIconXpath));
			
			JavascriptExecutor js=(JavascriptExecutor)driver;
			int iwhileCounter=0;
			while(!(fnpCheckElementDisplayedImmediately_NOR(AlertExpandIconXpath))){
				iwhileCounter++;
				fnpMymsg("Before scrolling in firefox ---"+iwhileCounter);
				js.executeScript("window.scrollBy(0,300)");
				Thread.sleep(1000);
				//act.moveToElement(alertElement).sendKeys(Keys.ARROW_DOWN).build().perform();
				if (iwhileCounter>10) {
					break;
				}
			}
			
			fnpMymsg("Debug: after scrolling loop");
			
			
			
			
			fnpMymsg("Before clicking alert.");
			//fnpClick_NOR(AlertNameXpath);
			//fnpClick_NOR(AlertExpandIconXpath);
			fnpClick_NOR_withoutWait(AlertExpandIconXpath);
			fnpLoading_wait_withoutErrorChecking(10);
			fnpMymsg("After clicking alert.");
			
		} catch (Throwable t) {
			fnpMymsg("Either Alert '" + alertName + " ' is  not present/visible/generated or failed due to this error -- "+t.getMessage());
			throw new Exception("Either Alert '" + alertName + " ' is  not present/visible/generated or failed due to this error -- "+t.getMessage());
		}

		
	//	fnpLoading_wait();
	//	fnpLoading_wait();

		fnpMymsg("Clicked '" + alertName + "' alert link");


		int colIndex = fnpFindColumnIndex(AlertHeaderTableXpath_OR, columnName);

		
		String filterbox_xpath=OR.getProperty(AlertHeaderTableXpath_OR)+"/tr/th["+colIndex+"]/input";
		//fnpType("", filterbox_xpath, searchingElement);
		//fnpWaitTillVisiblityOfElementNClickable_NOR(filterbox_xpath);
		fnpWaitForVisible_NotInOR(filterbox_xpath);

		fnpLoading_wait();
		//Thread.sleep(2000);

		WebDriverWait waitvar = new WebDriverWait(driver, Long.parseLong(CONFIG.getProperty("implicitWaitTime")));
		waitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(AlertDataTableXpath_OR)))).isEnabled();

		// fnpLoading_wait();
		String actualFirstColValue = fnpFetchFromTable(AlertDataTableXpath_OR, 1, 1);
		Assert.assertFalse(actualFirstColValue.contains("No records found"), "Alert in '" + alertName + "' has NOT been generated");
		
		
/*		try{
		Assert.assertFalse(actualFirstColValue.contains("Loading"), "Alert in '" + AlertName + "' has NOT been generated");
		}catch(Throwable t){
			try
		}
		*/
		
		int whileLoopCounter=0;
		int maxWaitTime=Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))*4;
		while(true)	{
			try{
				whileLoopCounter++;
			
				actualFirstColValue = fnpFetchFromTable(AlertDataTableXpath_OR, 1, 1);
				if ((actualFirstColValue.contains("Loading") || (actualFirstColValue.contains("loading") ) )) {
					Thread.sleep(1000);
					if (whileLoopCounter>maxWaitTime) {
						throw new Exception("Still Loading the alert even after ---"+maxWaitTime+" seconds.");
					}
				
					
				} else {
					break;
				}
			
		}catch(Throwable tt){
			throw tt;
		}
		}
		
		

		actualFirstColValue = fnpFetchFromTable(AlertDataTableXpath_OR, 1, 1);

		Assert.assertFalse(actualFirstColValue.contains("No records found"), "Alert in '" + alertName + "' has NOT been generated");

		String linkValueInSpecifiedColumn = fnpFetchFromTable(AlertDataTableXpath_OR, 1, colIndex);
		//Assert.assertEquals(ActualSearchedResult, searchingElement, "Alert in '" + AlertName + "' has NOT been generated");
		fnpMymsg("++++++++++++++++++++++++++++++++++++++++++++++++");
		fnpMymsg(" Hence , Alert  '" + alertName + "' has been generated successfully.");
		fnpMymsg("++++++++++++++++++++++++++++++++++++++++++++++++");
		fnpMymsg("");

		// ++++++++++++++++++++++++++++++++++++++++++++++++
		
		
		fnpMymsg("Going to click this link '" + linkValueInSpecifiedColumn + "' in Alert '" + alertName + "'.");
		
		driver.findElement(By.linkText(linkValueInSpecifiedColumn)).click();
		
		fnpLoading_wait();

	}

	
	
	//alert name is passing, instead of col name having alert
	public static void fnpCommonAlertClickedFirstRecord_in_specifiedColumnName_withoutSearching_BasedOnAlertNameContains(Hashtable table, String alertName,  String columnName) throws Throwable {

		
		
		//String AlertName = (String) table.get(AlertNameInExcel);
		fnpMymsg("Going to verify the alert'" + alertName + "' whether it is coming/generated or not.");
		

/*		if ( alertName==null) {
			throw new Exception("Alert '" + (String) table.get(AlertNameInExcel) + " ' is either not present in excel or not passed to the hashTable/hashMap. ");
		}
		*/
		if (alertName.equalsIgnoreCase("") ) {
			throw new Exception("Alert '" + alertName + " ' is either not present in excel or not passed to the hashTable/hashMap. ");
		}
		


		//AlertName = fnpFetchExactAlertName(AlertName);
		if (alertName.equalsIgnoreCase("") ) {
			throw new Exception("Alert '" + alertName + " ' is either not present or not visible. ");
		}


		String AlertNameXpath = ".//legend[contains(text(),'" + alertName + "')]";
		String AlertExpandIconXpath = ".//legend[contains(text(),'" + alertName + "')]/span";



		try {

			
			fnpMymsg("Debug: before scrolling loop");
			fnpMymsg("AlertExpandIcon is visible or not --"+fnpCheckElementDisplayedImmediately_NOR(AlertExpandIconXpath));
			
			JavascriptExecutor js=(JavascriptExecutor)driver;
			int iwhileCounter=0;
			while(!(fnpCheckElementDisplayedImmediately_NOR(AlertExpandIconXpath))){
				iwhileCounter++;
				fnpMymsg("Before scrolling in firefox ---"+iwhileCounter);
				js.executeScript("window.scrollBy(0,300)");
				Thread.sleep(1000);
				//act.moveToElement(alertElement).sendKeys(Keys.ARROW_DOWN).build().perform();
				if (iwhileCounter>10) {
					break;
				}
			}
			
			fnpMymsg("Debug: after scrolling loop");
			js.executeScript("window.scrollBy(0,600)");
			fnpLoading_wait();
			
			
			fnpMymsg("Before clicking alert.");
			//fnpClick_NOR(AlertNameXpath);
			//fnpClick_NOR(AlertExpandIconXpath);
			fnpClick_NOR_withoutWait(AlertExpandIconXpath);
			fnpLoading_wait_withoutErrorChecking(10);
			fnpMymsg("After clicking alert.");
			
		} catch (Throwable t) {
			fnpMymsg("Either Alert '" + alertName + " ' is  not present/visible/generated or failed due to this error -- "+t.getMessage());
			throw new Exception("Either Alert '" + alertName + " ' is  not present/visible/generated or failed due to this error -- "+t.getMessage());
		}

		
	//	fnpLoading_wait();
	//	fnpLoading_wait();

		fnpMymsg("Clicked '" + alertName + "' alert link");
		
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,800)");
		Thread.sleep(1000);


		//int colIndex = fnpFindColumnIndex(AlertHeaderTableXpath_OR, columnName);
		String tableHeaderXpath=AlertNameXpath+"/following-sibling::div//thead";
		String table_header_id=driver.findElement(By.xpath(tableHeaderXpath)).getAttribute("id");
		String updatedTableHeaderXpath=".//*[@id='"+table_header_id+"']";
		int colIndex =fnpFindColumnIndex_NOR(updatedTableHeaderXpath, columnName);

		
		//String filterbox_xpath=OR.getProperty(AlertHeaderTableXpath_OR)+"/tr/th["+colIndex+"]/input";
		String filterbox_xpath=updatedTableHeaderXpath+"/tr/th["+colIndex+"]/input";

		fnpWaitForVisible_NotInOR(filterbox_xpath);

		fnpLoading_wait();
		//Thread.sleep(2000);

		
		String tableDataXpath=tableHeaderXpath+"/../tbody";
		String table_Data_id=driver.findElement(By.xpath(tableDataXpath)).getAttribute("id");
		String updatedTableDataXpath=".//*[@id='"+table_Data_id+"']";
		
		
		
		WebDriverWait waitvar = new WebDriverWait(driver, Long.parseLong(CONFIG.getProperty("implicitWaitTime")));
		waitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(updatedTableDataXpath))).isEnabled();

		// fnpLoading_wait();
		//String actualFirstColValue = fnpFetchFromTable(AlertDataTableXpath_OR, 1, 1);
		String actualFirstColValue = fnpFetchFromTable_NOR(updatedTableDataXpath, 1, 1);
		Assert.assertFalse(actualFirstColValue.contains("No records found"), "Alert in '" + alertName + "' has NOT been generated");
		
		
/*		try{
		Assert.assertFalse(actualFirstColValue.contains("Loading"), "Alert in '" + AlertName + "' has NOT been generated");
		}catch(Throwable t){
			try
		}
		*/
		
		int whileLoopCounter=0;
		int maxWaitTime=Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))*4;
		while(true)	{
			try{
				whileLoopCounter++;
			
				//actualFirstColValue = fnpFetchFromTable(AlertDataTableXpath_OR, 1, 1);
				actualFirstColValue = fnpFetchFromTable_NOR(updatedTableDataXpath, 1, 1);
				if ((actualFirstColValue.contains("Loading") || (actualFirstColValue.contains("loading") ) )) {
					Thread.sleep(1000);
					if (whileLoopCounter>maxWaitTime) {
						throw new Exception("Still Loading the alert even after ---"+maxWaitTime+" seconds.");
					}
				
					
				} else {
					break;
				}
			
		}catch(Throwable tt){
			throw tt;
		}
		}
		
		

		//actualFirstColValue = fnpFetchFromTable(AlertDataTableXpath_OR, 1, 1);
		actualFirstColValue = fnpFetchFromTable_NOR(updatedTableDataXpath, 1, 1);

		Assert.assertFalse(actualFirstColValue.contains("No records found"), "Alert in '" + alertName + "' has NOT been generated");

		//String linkValueInSpecifiedColumn = fnpFetchFromTable(AlertDataTableXpath_OR, 1, colIndex);
		String linkValueInSpecifiedColumn = fnpFetchFromTable_NOR(updatedTableDataXpath, 1, colIndex);
		//Assert.assertEquals(ActualSearchedResult, searchingElement, "Alert in '" + AlertName + "' has NOT been generated");
		fnpMymsg("++++++++++++++++++++++++++++++++++++++++++++++++");
		fnpMymsg(" Hence , Alert  '" + alertName + "' has been generated successfully.");
		fnpMymsg("++++++++++++++++++++++++++++++++++++++++++++++++");
		fnpMymsg("");

		// ++++++++++++++++++++++++++++++++++++++++++++++++
		
		
		fnpMymsg("Going to click this link '" + linkValueInSpecifiedColumn + "' in Alert '" + alertName + "'.");
		
		driver.findElement(By.linkText(linkValueInSpecifiedColumn)).click();
		
		fnpLoading_wait();

	}
	
	
	//alert name is passing, instead of col name having alert
	public static int fnpCommonAlertClickedRecord_in_specifiedRow_specifiedColumnName_withoutSearching_BasedOnAlertNameContains(Hashtable table, String alertName, int rowNo, String columnName) throws Throwable {

		
		
		//String AlertName = (String) table.get(AlertNameInExcel);
		fnpMymsg("Going to verify the alert'" + alertName + "' whether it is coming/generated or not.");
		

/*		if ( alertName==null) {
			throw new Exception("Alert '" + (String) table.get(AlertNameInExcel) + " ' is either not present in excel or not passed to the hashTable/hashMap. ");
		}
		*/
		if (alertName.equalsIgnoreCase("") ) {
			throw new Exception("Alert '" + alertName + " ' is either not present in excel or not passed to the hashTable/hashMap. ");
		}
		


		//AlertName = fnpFetchExactAlertName(AlertName);
		if (alertName.equalsIgnoreCase("") ) {
			throw new Exception("Alert '" + alertName + " ' is either not present or not visible. ");
		}


		String AlertNameXpath = ".//legend[contains(text(),'" + alertName + "')]";
		String AlertExpandIconXpath = ".//legend[contains(text(),'" + alertName + "')]/span";



		try {

			
			fnpMymsg("Debug: before scrolling loop");
			fnpMymsg("AlertExpandIcon is visible or not --"+fnpCheckElementDisplayedImmediately_NOR(AlertExpandIconXpath));
			
			JavascriptExecutor js=(JavascriptExecutor)driver;
			int iwhileCounter=0;
			while(!(fnpCheckElementDisplayedImmediately_NOR(AlertExpandIconXpath))){
				iwhileCounter++;
				fnpMymsg("Before scrolling in firefox ---"+iwhileCounter);
				js.executeScript("window.scrollBy(0,300)");
				Thread.sleep(1000);
				//act.moveToElement(alertElement).sendKeys(Keys.ARROW_DOWN).build().perform();
				if (iwhileCounter>10) {
					break;
				}
			}
			
			js.executeScript("window.scrollBy(0,600)");
			fnpMymsg("Debug: after scrolling loop");
			
			fnpLoading_wait();
			
			
			fnpMymsg("Before clicking alert.");
			//fnpClick_NOR(AlertNameXpath);
			//fnpClick_NOR(AlertExpandIconXpath);
			fnpClick_NOR_withoutWait(AlertExpandIconXpath);
			fnpLoading_wait_withoutErrorChecking(10);
			fnpMymsg("After clicking alert.");
			
		} catch (Throwable t) {
			fnpMymsg("Either Alert '" + alertName + " ' is  not present/visible/generated or failed due to this error -- "+t.getMessage());
			throw new Exception("Either Alert '" + alertName + " ' is  not present/visible/generated or failed due to this error -- "+t.getMessage());
		}

		
	//	fnpLoading_wait();
	//	fnpLoading_wait();

		fnpMymsg("Clicked '" + alertName + "' alert link");
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,800)");
		Thread.sleep(1000);
		
		

		//int colIndex = fnpFindColumnIndex(AlertHeaderTableXpath_OR, columnName);
		String tableHeaderXpath=AlertNameXpath+"/following-sibling::div//thead";
		String table_header_id=driver.findElement(By.xpath(tableHeaderXpath)).getAttribute("id");
		String updatedTableHeaderXpath=".//*[@id='"+table_header_id+"']";
		int colIndex =fnpFindColumnIndex_NOR(updatedTableHeaderXpath, columnName);

		
		//String filterbox_xpath=OR.getProperty(AlertHeaderTableXpath_OR)+"/tr/th["+colIndex+"]/input";
		String filterbox_xpath=updatedTableHeaderXpath+"/tr/th["+colIndex+"]/input";

		fnpWaitForVisible_NotInOR(filterbox_xpath);

		fnpLoading_wait();
		//Thread.sleep(2000);

		
		String tableDataXpath=tableHeaderXpath+"/../tbody";
		String table_Data_id=driver.findElement(By.xpath(tableDataXpath)).getAttribute("id");
		String updatedTableDataXpath=".//*[@id='"+table_Data_id+"']";
		
		
		
		WebDriverWait waitvar = new WebDriverWait(driver, Long.parseLong(CONFIG.getProperty("implicitWaitTime")));
		waitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(updatedTableDataXpath))).isEnabled();

		// fnpLoading_wait();
		//String actualFirstColValue = fnpFetchFromTable(AlertDataTableXpath_OR, 1, 1);
		String actualFirstColValue = fnpFetchFromTable_NOR(updatedTableDataXpath, 1, 1);
		Assert.assertFalse(actualFirstColValue.contains("No records found"), "Alert in '" + alertName + "' has NOT been generated");
		
		
/*		try{
		Assert.assertFalse(actualFirstColValue.contains("Loading"), "Alert in '" + AlertName + "' has NOT been generated");
		}catch(Throwable t){
			try
		}
		*/
		
		int whileLoopCounter=0;
		int maxWaitTime=Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))*4;
		while(true)	{
			try{
				whileLoopCounter++;
			
				//actualFirstColValue = fnpFetchFromTable(AlertDataTableXpath_OR, 1, 1);
				actualFirstColValue = fnpFetchFromTable_NOR(updatedTableDataXpath, 1, 1);
				if ((actualFirstColValue.contains("Loading") || (actualFirstColValue.contains("loading") ) )) {
					Thread.sleep(1000);
					if (whileLoopCounter>maxWaitTime) {
						throw new Exception("Still Loading the alert even after ---"+maxWaitTime+" seconds.");
					}
				
					
				} else {
					break;
				}
			
		}catch(Throwable tt){
			throw tt;
		}
		}
		
		

		//actualFirstColValue = fnpFetchFromTable(AlertDataTableXpath_OR, 1, 1);
		actualFirstColValue = fnpFetchFromTable_NOR(updatedTableDataXpath, 1, 1);

		Assert.assertFalse(actualFirstColValue.contains("No records found"), "Alert in '" + alertName + "' has NOT been generated");

		//String linkValueInSpecifiedColumn = fnpFetchFromTable(AlertDataTableXpath_OR, 1, colIndex);
		String linkValueInSpecifiedColumn = fnpFetchFromTable_NOR(updatedTableDataXpath, rowNo, colIndex);
		//Assert.assertEquals(ActualSearchedResult, searchingElement, "Alert in '" + AlertName + "' has NOT been generated");
		fnpMymsg("++++++++++++++++++++++++++++++++++++++++++++++++");
		fnpMymsg(" Hence , Alert  '" + alertName + "' has been generated successfully.");
		fnpMymsg("++++++++++++++++++++++++++++++++++++++++++++++++");
		fnpMymsg("");

		// ++++++++++++++++++++++++++++++++++++++++++++++++
		
		
		fnpMymsg("Going to click this link '" + linkValueInSpecifiedColumn + "' in row '"+rowNo+"' in this Alert '" + alertName + "'.");
		
		int totalRows=fnpCountRowsInTable_NOR(updatedTableDataXpath);
		
		//driver.findElement(By.linkText(linkValueInSpecifiedColumn)).click();
		fnpClickInTable_NOR(updatedTableDataXpath, rowNo, colIndex);
		
		fnpLoading_wait();
		
		return totalRows;

	}
	
	
	
	//alert name is passing, instead of col name having alert
	public static void fnpCommonAlertClickedFirstRecord_in_specifiedColumnName_withoutSearching_But_AfterClickingAscendingIcon_BasedOnAlertNameContains(Hashtable table, String alertName,  String columnNameToPick,String columnNameToSort) throws Throwable {

		
		
		//String AlertName = (String) table.get(AlertNameInExcel);
		fnpMymsg("Going to verify the alert'" + alertName + "' whether it is coming/generated or not.");
		

/*		if ( alertName==null) {
			throw new Exception("Alert '" + (String) table.get(AlertNameInExcel) + " ' is either not present in excel or not passed to the hashTable/hashMap. ");
		}
		*/
		if (alertName.equalsIgnoreCase("") ) {
			throw new Exception("Alert '" + alertName + " ' is either not present in excel or not passed to the hashTable/hashMap. ");
		}
		


		//AlertName = fnpFetchExactAlertName(AlertName);
		if (alertName.equalsIgnoreCase("") ) {
			throw new Exception("Alert '" + alertName + " ' is either not present or not visible. ");
		}


		String AlertNameXpath = ".//legend[contains(text(),'" + alertName + "')]";
		String AlertExpandIconXpath = ".//legend[contains(text(),'" + alertName + "')]/span";



		try {

			
			fnpMymsg("Debug: before scrolling loop");
			fnpMymsg("AlertExpandIcon is visible or not --"+fnpCheckElementDisplayedImmediately_NOR(AlertExpandIconXpath));
			
			JavascriptExecutor js=(JavascriptExecutor)driver;
			int iwhileCounter=0;
			while(!(fnpCheckElementDisplayedImmediately_NOR(AlertExpandIconXpath))){
				iwhileCounter++;
				fnpMymsg("Before scrolling in firefox ---"+iwhileCounter);
				js.executeScript("window.scrollBy(0,300)");
				Thread.sleep(1000);
				//act.moveToElement(alertElement).sendKeys(Keys.ARROW_DOWN).build().perform();
				if (iwhileCounter>10) {
					break;
				}
			}
			
			fnpMymsg("Debug: after scrolling loop");
			
			js.executeScript("window.scrollBy(0,600)");
			fnpLoading_wait();
			
			fnpMymsg("Before clicking alert.");
			//fnpClick_NOR(AlertNameXpath);
			//fnpClick_NOR(AlertExpandIconXpath);
			fnpClick_NOR_withoutWait(AlertExpandIconXpath);
			fnpLoading_wait_withoutErrorChecking(10);
			fnpMymsg("After clicking alert.");
			
		} catch (Throwable t) {
			fnpMymsg("Either Alert '" + alertName + " ' is  not present/visible/generated or failed due to this error -- "+t.getMessage());
			throw new Exception("Either Alert '" + alertName + " ' is  not present/visible/generated or failed due to this error -- "+t.getMessage());
		}
		
		
		
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,800)");
		Thread.sleep(1000);

		
	//	fnpLoading_wait();
	//	fnpLoading_wait();

		fnpMymsg("Clicked '" + alertName + "' alert link");


		//int colIndex = fnpFindColumnIndex(AlertHeaderTableXpath_OR, columnName);
		String tableHeaderXpath=AlertNameXpath+"/following-sibling::div//thead";
		String table_header_id=driver.findElement(By.xpath(tableHeaderXpath)).getAttribute("id");
		String updatedTableHeaderXpath=".//*[@id='"+table_header_id+"']";
		int colIndex =fnpFindColumnIndex_NOR(updatedTableHeaderXpath, columnNameToPick);

		
		//String filterbox_xpath=OR.getProperty(AlertHeaderTableXpath_OR)+"/tr/th["+colIndex+"]/input";
		String filterbox_xpath=updatedTableHeaderXpath+"/tr/th["+colIndex+"]/input";

		fnpWaitForVisible_NotInOR(filterbox_xpath);

		fnpLoading_wait();
		//Thread.sleep(2000);

		
		String tableDataXpath=tableHeaderXpath+"/../tbody";
		String table_Data_id=driver.findElement(By.xpath(tableDataXpath)).getAttribute("id");
		String updatedTableDataXpath=".//*[@id='"+table_Data_id+"']";
		
		
		
		WebDriverWait waitvar = new WebDriverWait(driver, Long.parseLong(CONFIG.getProperty("implicitWaitTime")));
		waitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(updatedTableDataXpath))).isEnabled();

		// fnpLoading_wait();
		//String actualFirstColValue = fnpFetchFromTable(AlertDataTableXpath_OR, 1, 1);
		String actualFirstColValue = fnpFetchFromTable_NOR(updatedTableDataXpath, 1, 1);
		Assert.assertFalse(actualFirstColValue.contains("No records found"), "Alert in '" + alertName + "' has NOT been generated");
		
		
/*		try{
		Assert.assertFalse(actualFirstColValue.contains("Loading"), "Alert in '" + AlertName + "' has NOT been generated");
		}catch(Throwable t){
			try
		}
		*/
		
		int whileLoopCounter=0;
		int maxWaitTime=Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))*4;
		while(true)	{
			try{
				whileLoopCounter++;
			
				//actualFirstColValue = fnpFetchFromTable(AlertDataTableXpath_OR, 1, 1);
				actualFirstColValue = fnpFetchFromTable_NOR(updatedTableDataXpath, 1, 1);
				if ((actualFirstColValue.contains("Loading") || (actualFirstColValue.contains("loading") ) )) {
					Thread.sleep(1000);
					if (whileLoopCounter>maxWaitTime) {
						throw new Exception("Still Loading the alert even after ---"+maxWaitTime+" seconds.");
					}
				
					
				} else {
					break;
				}
			
		}catch(Throwable tt){
			throw tt;
		}
		}
		
		

		//actualFirstColValue = fnpFetchFromTable(AlertDataTableXpath_OR, 1, 1);
		actualFirstColValue = fnpFetchFromTable_NOR(updatedTableDataXpath, 1, 1);

		Assert.assertFalse(actualFirstColValue.contains("No records found"), "Alert in '" + alertName + "' has NOT been generated");

		//String linkValueInSpecifiedColumn = fnpFetchFromTable(AlertDataTableXpath_OR, 1, colIndex);
		//String linkValueInSpecifiedColumn = fnpFetchFromTable_NOR(updatedTableDataXpath, 1, colIndex);
		//Assert.assertEquals(ActualSearchedResult, searchingElement, "Alert in '" + AlertName + "' has NOT been generated");
		fnpMymsg("++++++++++++++++++++++++++++++++++++++++++++++++");
		fnpMymsg(" Hence , Alert  '" + alertName + "' has been generated successfully.");
		fnpMymsg("++++++++++++++++++++++++++++++++++++++++++++++++");
		fnpMymsg("");

		// ++++++++++++++++++++++++++++++++++++++++++++++++
		
		
		
		String xpath_forAscending_ScheduleAuditEndDate=tableHeaderXpath+"/tr/th/span/label[text()='"+columnNameToSort+"']/../following-sibling::span[contains(@class,'ui-sortable-column-icon ui-icon ui-icon-carat-2-n-s')]";
		fnpClick_NOR(xpath_forAscending_ScheduleAuditEndDate);
		fnpLoading_wait();
		
		
		String linkValueInSpecifiedColumn = fnpFetchFromTable_NOR(updatedTableDataXpath, 1, colIndex);
		fnpMymsg("Going to click this link '" + linkValueInSpecifiedColumn + "' in Alert '" + alertName + "'.");
		
		//driver.findElement(By.linkText(linkValueInSpecifiedColumn)).click();
		
		//fnpClickALinkInATable_advance_havingATagInside(table_Data_id, "GO TO INVOICE", rowNo);
		fnpClickALinkInATable_advance_havingATagInside(table_Data_id, linkValueInSpecifiedColumn, 1);
		
		fnpLoading_wait();

	}

	
	
	/**** Verify alert has been deleted or not ****/
	public static void fnpCommonAlertDeletedVerification(Hashtable table, String alertName, int rowNo, String columnName,String searchValue) throws Throwable {


		fnpMymsg("Going to verify the alert'" + alertName + "' whether it is coming/generated or not.");

		if (alertName.equalsIgnoreCase("") ) {
			throw new Exception("Alert '" + alertName + " ' is either not present in excel or not passed to the hashTable/hashMap. ");
		}
		


		//AlertName = fnpFetchExactAlertName(AlertName);
		if (alertName.equalsIgnoreCase("") ) {
			throw new Exception("Alert '" + alertName + " ' is either not present or not visible. ");
		}


		String AlertNameXpath = ".//legend[contains(text(),'" + alertName + "')]";
		String AlertExpandIconXpath = ".//legend[contains(text(),'" + alertName + "')]/span";



		try {

			
			fnpMymsg("Debug: before scrolling loop");
			fnpMymsg("AlertExpandIcon is visible or not --"+fnpCheckElementDisplayedImmediately_NOR(AlertExpandIconXpath));
			
			JavascriptExecutor js=(JavascriptExecutor)driver;
			int iwhileCounter=0;
			while(!(fnpCheckElementDisplayedImmediately_NOR(AlertExpandIconXpath))){
				iwhileCounter++;
				fnpMymsg("Before scrolling in firefox ---"+iwhileCounter);
				js.executeScript("window.scrollBy(0,300)");
				Thread.sleep(1000);
				//act.moveToElement(alertElement).sendKeys(Keys.ARROW_DOWN).build().perform();
				if (iwhileCounter>10) {
					break;
				}
			}
			
			js.executeScript("window.scrollBy(0,600)");
			fnpMymsg("Debug: after scrolling loop");
			fnpLoading_wait();
			
			
			
			fnpMymsg("Before clicking alert.");
			//fnpClick_NOR(AlertNameXpath);
			//fnpClick_NOR(AlertExpandIconXpath);
			fnpClick_NOR_withoutWait(AlertExpandIconXpath);
			fnpLoading_wait_withoutErrorChecking(10);
			//fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
			fnpMymsg("After clicking alert.");
			
		} catch (Throwable t) {
			fnpMymsg("Either Alert '" + alertName + " ' is  not present/visible/generated or failed due to this error -- "+t.getMessage());
			throw new Exception("Either Alert '" + alertName + " ' is  not present/visible/generated or failed due to this error -- "+t.getMessage());
		}



		fnpMymsg("Clicked '" + alertName + "' alert link");
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,800)");
		Thread.sleep(1000);
		

		//int colIndex = fnpFindColumnIndex(AlertHeaderTableXpath_OR, columnName);
		String tableHeaderXpath=AlertNameXpath+"/following-sibling::div//thead";
		String table_header_id=driver.findElement(By.xpath(tableHeaderXpath)).getAttribute("id");
		String updatedTableHeaderXpath=".//*[@id='"+table_header_id+"']";
		int colIndex =fnpFindColumnIndex_NOR(updatedTableHeaderXpath, columnName);

		
		//String filterbox_xpath=OR.getProperty(AlertHeaderTableXpath_OR)+"/tr/th["+colIndex+"]/input";
		String filterbox_xpath=updatedTableHeaderXpath+"/tr/th["+colIndex+"]/input";

		fnpWaitForVisible_NotInOR(filterbox_xpath);


		fnpType("", filterbox_xpath, searchValue);
		//driver.findElement(By.xpath(filterbox_xpath)).sendKeys(searchValue);
		fnpLoading_wait_specialCase(5);
		fnpLoading_wait_CheckLoadingIconOnlyIniPulse();

		
		String tableDataXpath=tableHeaderXpath+"/../tbody";
		String table_Data_id=driver.findElement(By.xpath(tableDataXpath)).getAttribute("id");
		String updatedTableDataXpath=".//*[@id='"+table_Data_id+"']";
		
		
		
		WebDriverWait waitvar = new WebDriverWait(driver, Long.parseLong(CONFIG.getProperty("implicitWaitTime")));
		waitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(updatedTableDataXpath))).isEnabled();
	

		String s = fnpFetchFromTable_NOR(updatedTableDataXpath, 1, 1);

		Assert.assertNotEquals(s, searchValue, "Alert in '" + alertName + "' has NOT been REMOVED");

		Assert.assertTrue(s.toLowerCase().contains("no records found"), "Alert in '" + alertName + "' has NOT been REMOVED as 'no records found' is not present after search");
		fnpMymsg("++++++++++++++++++++++++++++++++++++++++++++++++");
		fnpMymsg("Hence,Alert  '" + alertName + "' has been REMOVED successfully.");
		fnpMymsg("++++++++++++++++++++++++++++++++++++++++++++++++");
		fnpMymsg("");
		// ++++++++++++++++++++++++++++++++++++++++++++++++

	}

}
