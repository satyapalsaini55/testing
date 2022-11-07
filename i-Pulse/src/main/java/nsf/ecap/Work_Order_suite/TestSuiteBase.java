package nsf.ecap.Work_Order_suite;

//import test.disappearingMessageInPopUp.psingh.DisappearingMessage;
//import test.disappearingMessageInPopUp.psingh.DisappearingMessage_usingDialog;

import java.awt.AWTException;
import org.apache.commons.lang3.StringUtils;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.JPasswordField;
import javax.swing.KeyStroke;

import nsf.ecap.ISR_suite.ISO9001_Single;
import nsf.ecap.ISR_suite.TestSuiteBase_ISR_suite;
import nsf.ecap.MonitorPlan_Suite.TestSuiteBase_MonitorPlan;
import nsf.ecap.Non_Food_Compounds_suite.NFC_ExistingNew_Draft_Complete;
import nsf.ecap.base.TestBase;
import nsf.ecap.config.IPULSE_CONSTANTS;
import nsf.ecap.util.ErrorUtil;
import nsf.ecap.util.TestUtil;
import nsf.ecap.util.Xls_Reader;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.os.WindowsUtils;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
//import org.testng.internal.Nullable;

//import test.DisappearingMessage;
//import test.disappearingMessageInPopUp.psingh.DisappearingMessage;
//import test.DisappearingMessage;

import com.google.common.base.Predicate;
import com.google.common.base.Throwables;

import io.github.bonigarcia.wdm.WebDriverManager;

//import io.github.bonigarcia.wdm.WebDriverManager;

import static nsf.ecap.config.IPULSE_CONSTANTS.*;
import static org.testng.Assert.assertTrue;

public class TestSuiteBase extends TestBase {

	public static String loginAs = "";
	public static String loginAsFullName = "";
	public static String loginUser_code = "";

	public String runmodes[] = null;
	public static int count = -1;// put static on 23-08-2021

	public static boolean fail = false;
	public static boolean isTestPass = true;
	public static boolean skip = false;// put static on 23-08-2021

	public static String workOrderNo;

	public NewNew_Draft_InReview_No_Fac BS01;
	public NewNew_Draft_InReview_With_Fac BS02;
	public NewNew_Draft_InProcess_No_Fac BS03;
	public NewNew_Draft_InProcess_With_Fac BS04;
	public NewNew_InProc_Completed_No_Fac BS05;
	public NewNew_InProc_Complete_With_Fac BS06;

	public Modbrack_Not_Certified BS16;

	public WO_Annual BS10;
	public WO_Custom BS11;
	public WO_Failure_Resolution BS12;
	public BulkAssign_Unassign_To_Assign BS13;

	public NFC_ExistingNew_Draft_Complete nfcWo;

	public static String msg;

	// This flag use in Dietary supplement, and its one of method is present in
	// TestSuiteBase.java,thats why declaring here
	public static boolean delistedGMPSStandardAI_alreadyProcessedFlag = false;
	public static boolean LoadingImageFlag = false;
	public static Integer Actual_Loading_Time = 1;
	public static String PageSourceText = "";
	public static String ErrorMsgText = "";
	public static Integer Element_Max_Wait_Time_SAM = 240;

	/*********** check if the suite has to be skipped or not *************/
	@BeforeSuite
	public void checkSuiteSkip() throws Throwable {
		// currentSuiteName = "Work_Order_suite";
		setCurrentSuiteName("Work_Order_suite");

		// currentSuiteXls = Work_Order_suitexls;
		setCurrentSuiteExcel(Work_Order_suitexls);
		fnInitialize();

		fnpMymsg("             ");
		fnpMymsg("             ");

		fnpMymsg(
				"=========================================================================================================================================");
		fnpMymsg(
				"####################Work Order Suite Start ############################################################");
		fnpMymsg("Checking Runmode of Work_Order_suite");

		if (!TestUtil.isSuiteRunnable(suiteXls, "Work_Order_suite")) {
			fnpMymsg("Skipped Work_Order_suite as the runmode was set to NO");
			// fnpMymsg("####################Work Order Suite End
			// ############################################################");
			// fnpMymsg("=========================================================================================================================================");
			throw new SkipException("Runmode of Work_Order_suite set to no. So Skipping all tests in Work_Order_suite");
		}

		if (TestUtil.isSuiteRunnable(suiteXls, "Work_Order_suite")) {

			browserName = TestUtil.getBrowserName(suiteXls, currentSuiteName);
			excelSiteURL = TestUtil.getExcelSiteURL(suiteXls, currentSuiteName);
			fnpMymsg("Browser Name is :" + browserName);
			fnpMymsg("======================");
			currentSuiteCode = "WO";

			int rowNum = suiteXls.getCellRowNum("Test Suite", "TSID", currentSuiteName);

			suiteXls.setCellData("Test Suite", "Last_Execution", rowNum, fnReturnDateWithTimeForExcel());

		}

		// fnpDeleteSMTPMessages();

	}

	/*********** To remove IEDriver.exe from Windows Task Manager *************/
	@AfterSuite(alwaysRun = true)
	public void cleanUp() {
		try {

			/*
			 * loginAs="";
			 * loginAsFullName="";
			 */
			setLoginAsAndLoginAsFullName_blank();

			referenceSuite = currentSuiteName;
			fnpMymsg("#################### " + currentSuiteName
					+ " Suite End ############################################################");
			if (driver != null) {
				driver.quit();
			}
			IsBrowserPresentAlready = false;
			if (checkRunningProcess("IEDriverServer.exe")) {
				// WindowsUtils.killByName("IEDriverServer.exe");
			}

		} catch (NullPointerException n) {
			// Nothing to do
		}

		catch (Throwable t) {
			// Nothing to do
		}

	}

	/*********** initializing the Tests *************/
	public void fnpInitialize() throws Exception {

		if (!isInitalized) {
			InputStream loggerStream = new FileInputStream(System.getProperty("user.dir") + "//src//log4j.properties");
			Properties prop = new Properties();
			prop.load(loggerStream);
			PropertyConfigurator.configure(prop);

			APP_LOGS = Logger.getLogger("devpinoyLogger");

			fnpMymsg("");
			fnpMymsg("");
			fnpMymsg("");
			fnpMymsg("");

			fnpMymsg("************************************");
			fnpMymsg("Loading Property files");
			CONFIG = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "//src//nsf//ecap//config/config.properties");
			CONFIG.load(ip);

			OR = new Properties();
			ip = new FileInputStream(System.getProperty("user.dir") + "//src//nsf//ecap//config/OR.properties");
			OR.load(ip);
			fnpMymsg("Loaded Property files successfully");
			fnpMymsg("Loading XLS Files");
			Work_Order_suitexls = new Xls_Reader(
					System.getProperty("user.dir") + "//src//nsf//ecap//xls//Work_Order_suite.xlsx");
			Proposals_suitexls = new Xls_Reader(
					System.getProperty("user.dir") + "//src//nsf//ecap//xls//Proposals_suite.xlsx");

			suiteXls = new Xls_Reader(System.getProperty("user.dir") + "\\src\\nsf\\ecap\\xls\\Suite.xlsx");
			fnpMymsg("Loaded XLS Files successfully");

			isInitalized = true;

			hashXlData = new HashMap();

			fnppradeepKillIEProcess();

		}

	}

	public static String fns_GetCurrentSystemIPAddress() throws Throwable {
		byte[] ip = new byte[0];
		try {
			InetAddress address = InetAddress.getLocalHost();
			ip = address.getAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		int i = 4;
		StringBuilder ipAddress = new StringBuilder();
		for (byte raw : ip) {
			ipAddress.append(raw & 0xFF);
			if (--i > 0) {
				ipAddress.append(".");
			}
		}
		return ipAddress.toString();

	}

	/*********** Launching the Browser *************/
	public static void fnpLaunchBrowser() throws Throwable {
		// @ also used this in DNSFO
		startExecutionTime = fnpTimestamp();
		// fnpFirstTimeInitializationMethod();
		String ip_Address = fns_GetCurrentSystemIPAddress();
		fnpMymsg("Checking Browser type");

		String browser = browserName;

		if (browser.equalsIgnoreCase("IE")) {

			System.setProperty("webdriver.ie.driver", CONFIG.getProperty("IEDriverPath"));
			// WebDriverManager
			// WebDriverManager.iedriver().setup();

			/*
			 * DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
			 * 
			 *//***** Creating problem sometime for IE 11, so commenting lines *******/
			/*
			 * caps.setCapability(InternetExplorerDriver.
			 * INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			 * caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			 * 
			 * // caps.setCapability(CapabilityType.VERSION,"9");
			 * 
			 * // caps.setCapability("IGNORE_ZOOM_SETTING", true); //
			 * caps.setCapability("IE_ENSURE_CLEAN_SESSION", true);
			 *//***** Creating problem sometime for IE 11, so commenting lines *******//*
																						 * 
																						 * caps.setCapability(
																						 * InternetExplorerDriver.
																						 * REQUIRE_WINDOW_FOCUS, true);
																						 * 
																						 * 
																						 * caps.setCapability(
																						 * InternetExplorerDriver.
																						 * IE_SWITCHES, "-private");
																						 * 
																						 * driver = new
																						 * InternetExplorerDriver(caps);
																						 * 
																						 */

			InternetExplorerOptions ieOptions = new InternetExplorerOptions();
			ieOptions.setCapability(InternetExplorerDriver.FORCE_CREATE_PROCESS, true);
			ieOptions.addCommandSwitches("-private");

			ieOptions.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			ieOptions.setCapability("IE_ENSURE_CLEAN_SESSION", true);
			ieOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

			driver = new InternetExplorerDriver(ieOptions);

			// driver = new InternetExplorerDriver();

			driver.manage().window().maximize();

			driver.manage().timeouts().pageLoadTimeout(Long.parseLong(CONFIG.getProperty("pageLoadTime")),
					TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")),
					TimeUnit.SECONDS);
			fnpMymsg("Browser type is IE");
		}

		if (browser.equalsIgnoreCase("firefox")) {

			/*
			 * //WebDriverManager
			 * WebDriverManager.firefoxdriver().setup();
			 */
			// System.setProperty("webdriver.firefox.marionette",
			// CONFIG.getProperty("geckoDriverPath"));
			System.setProperty("webdriver.gecko.driver", CONFIG.getProperty("geckoDriverPath"));

			FirefoxProfile fprofile = new FirefoxProfile();
			// Set Location to store files after downloading.
			fprofile.setPreference("browser.download.dir", System.getProperty("user.dir") + "\\download");
			fprofile.setPreference("browser.download.folderList", 2);

			fprofile.setPreference("browser.helperApps.neverAsk.saveToDisk",
					"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;"// MIME
																						// types
																						// Of
																						// MS
																						// Excel
																						// File.
							+ "application/pdf;" // MIME types Of PDF File.
							+ "application/vnd.openxmlformats-officedocument.wordprocessingml.document;" // MIME
																											// types
																											// Of
																											// MS
																											// doc
																											// File.
							+ "text/plain;" // MIME types Of text File.
							+ "text/csv"); // MIME types Of CSV File.
			fprofile.setPreference("browser.download.manager.showWhenStarting", false);
			fprofile.setPreference("pdfjs.disabled", true);
			// Pass fprofile parameter In webdriver to use preferences to
			// download file.

			// fprofile.setLegacy(true);

			fprofile.setPreference("browser.private.browsing.autostart", true);

			// System.setProperty("webdriver.gecko.driver", "C:\\Selenium Versions and
			// drivers\\Gecko drivers\\geckodriver-v0.20.1-win64\\geckodriver.exe");
			// driver = new FirefoxDriver();

			DesiredCapabilities dc = DesiredCapabilities.firefox();
			dc.setCapability("marionette", true);
			// dc.setCapability("profile", fprofile);
			driver = new FirefoxDriver(dc);

			/*
			 * DesiredCapabilities dc = DesiredCapabilities.firefox();
			 * dc.setCapability("marionette", true);
			 * dc.setCapability("profile", fprofile);
			 * 
			 * 
			 */
			// driver = new FirefoxDriver();

			driver.manage().window().maximize();
			fnpMymsg("Browser type is firefox");
		}

		if (browser.equalsIgnoreCase("chrome")) {

			fnpMymsg("Debug 1");

			// System.setProperty("webdriver.chrome.driver",
			// CONFIG.getProperty("ChromeDriverPath"));

			// System.setProperty("webdriver.chrome.logfile", System.getProperty("user.dir")
			// +"\\log\\Driverlogs.txt");

			// System.setProperty("webdriver.chrome.silentOutput", "true");//How to Suppress
			// Console Logs in Selenium

			// WebDriverManager
			WebDriverManager.chromedriver().setup();

			String FileLocation = System.getProperty("user.dir") + "\\download";
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();

			chromePrefs.put("profile.default_content_settings.popups", 1);
			chromePrefs.put("profile.default_content_settings.notifications", 1);
			chromePrefs.put("download.default_directory", FileLocation);
			chromePrefs.put("credentials_enable_service", false);
			chromePrefs.put("profile.password_manager_enabled", false);

			fnpMymsg("Debug 2");

			ChromeOptions options = new ChromeOptions();

			fnpMymsg("Debug 3");

			options.addArguments("--start-maximized");
			options.addArguments("--enable-text-input-focus-manager");
			options.addArguments("--disable-popup-blocking");
			options.addArguments("chrome.switches", "--disable-extensions");
			options.addArguments("disable-infobars");

			// options.addArguments("--disable-web-security");
			options.addArguments("--no-proxy-server");

			// below three lines are added because in new chrome browsers 'disable-infobars'
			// is not working for new browser version, so using these from today onwards
			// 08-08-2019
			// problem identified on 40 machine
			options.setExperimentalOption("useAutomationExtension", false);
			options.setExperimentalOption("excludeSwitches",
					Collections.singletonList("enable-automation"));

			options.addArguments("--headless");

			fnpMymsg("Debug 4");

			options.setExperimentalOption("prefs", chromePrefs);
			DesiredCapabilities caps = DesiredCapabilities.chrome();

			/*
			 * caps.setCapability("IGNORE_CERTIFICATE_ERRORS", true);
			 * caps.setCapability(ChromeOptions.CAPABILITY, options);
			 * caps.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION,
			 * true);
			 * 
			 * 
			 */
			caps.setCapability(ChromeOptions.CAPABILITY, options);

			// cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			fnpMymsg("Debug 5");
			driver = new ChromeDriver(caps);
			fnpMymsg("Debug 6");

			/*
			 * WebDriverManager.chromedriver().setup();
			 * driver = new ChromeDriver(caps);
			 * 
			 */

			// HtmlUnitDriver unitDriver = new HtmlUnitDriver();

			// HtmlUnitDriver unitDriver = new HtmlUnitDriver();

			fnpMymsg("Browser type is chrome");
		}

		if (browser.equalsIgnoreCase("edge")) {

			System.setProperty("webdriver.edge.driver", CONFIG.getProperty("EdgeDriverPath"));
			// System.setProperty("webdriver.edge.driver",
			// System.getProperty("user.dir")+"\\driver\\msedgedriver_87.0.664.0.exe");

			// driver = new EdgeDriver();

			/*
			 * DesiredCapabilities capabilities = DesiredCapabilities.edge();
			 * capabilities.setCapability("ms:inPrivate", true);
			 * driver = new EdgeDriver(capabilities);
			 */

			EdgeOptions op = new EdgeOptions();
			// op.setCapability("InPrivate", true);
			// op.addArguments("-inprivate")
			op.setCapability("ms:inPrivate", true);

			driver = new EdgeDriver(op);

			// driver = new InternetExplorerDriver();

			// driver.manage().window().maximize();

			fnpMymsg("Browser type is Edge");

			String parentWindowHandler = driver.getWindowHandle();

			Robot robot;
			try {
				// This is the actual code that opens the InPrivate window
				robot = new Robot();
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_N);

				robot.keyRelease(KeyEvent.VK_CONTROL);
				robot.keyRelease(KeyEvent.VK_SHIFT);
				robot.keyRelease(KeyEvent.VK_N);

				Thread.sleep(3000);
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			String subWindowHandler = null;

			Set<String> handles = driver.getWindowHandles();
			System.out.println("Total opened browsers are --" + handles.size());

			// driver.close();

			Iterator<String> iterator = handles.iterator();
			while (iterator.hasNext()) {
				subWindowHandler = iterator.next();
				if (!(parentWindowHandler.equalsIgnoreCase(subWindowHandler))) {
					System.out.println(subWindowHandler);
					driver.switchTo().window(subWindowHandler);
					break;
				}

			}

		}

		driver.manage().timeouts().pageLoadTimeout(Long.parseLong(CONFIG.getProperty("pageLoadTime")),
				TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")),
				TimeUnit.SECONDS);

		driver.manage().deleteAllCookies();

		// fnpMoveMouseAtCenterBottomOfScreen();

	}

	/***********
	 * Function to Launch the browser and login with user credential details
	 * 
	 * @throws Throwable
	 *************/
	public static boolean fnpLaunchBrowserAndLogin() throws Throwable {
		boolean present;
		try {

			killprocess();

			// just for try as so many chromedriver.exe instances left on 14 m/c and
			// windowUtil class is deprecated / no longer present in selenium after 3.11
			// version
			// fnptryingKillProcess_usingLoop();

			fnpLaunchBrowser();

			String userName = CONFIG.getProperty("adminUsername");
			String password = CONFIG.getProperty("adminPassword");
			String siteUrl = null;

			if (excelSiteURL != null) {
				if (excelSiteURL.equalsIgnoreCase("")) {
					siteUrl = CONFIG.getProperty("testSiteName");
				} else {
					// siteUrl = excelSiteURL;

					if (excelSiteURL.contains("Oasis_Url")) {// for Oasis site
																// in WO ISR
						siteUrl = excelSiteURL.split("Oasis_Url:")[0];
					} else {
						// rest for all
						siteUrl = excelSiteURL.split("LoginAsTestSiteName:")[0];
					}
					// siteUrl = excelSiteURL.split("LoginAsTestSiteName:")[0];

				}
			} else {
				siteUrl = CONFIG.getProperty("testSiteName");
			}

			driver.get(siteUrl);

			/*
			 * Thread.sleep(5000);
			 * WebElement wb = fnpGetORObjectX("UserName_id");
			 * // String selectAll = Keys.chord(Keys.ALT, Keys.SHIFT,"s");
			 * // String selectAll = Keys.chord(Keys.SHIFT, Keys.TAB);
			 * //String selectAll = Keys.chord(Keys.CONTROL, "a");
			 * 
			 * String selectAll = Keys.chord(Keys.SHIFT, "1","2","3");
			 * wb.sendKeys(selectAll);
			 * 
			 * Thread.sleep(5000);
			 */

			// Not using below after implementing SSO
			/*
			 * if (siteUrl.contains("securelogin")) {
			 * 
			 * //loginAs="";
			 * switch (currentSuiteCode) {
			 * case "PO":
			 * loginAs=CONFIG.getProperty("QuoteGeneratorScenarios").split(":")[0];
			 * loginAsFullName=CONFIG.getProperty("QuoteGeneratorScenarios").split(":")[1];
			 * loginUser_code=CONFIG.getProperty("QuoteGeneratorScenarios").split(":")[2];
			 * break;
			 * 
			 * 
			 * case "WO":
			 * loginAs=CONFIG.getProperty("WorkOrderscenarios").split(":")[0];
			 * loginAsFullName=CONFIG.getProperty("WorkOrderscenarios").split(":")[1];
			 * loginUser_code=CONFIG.getProperty("WorkOrderscenarios").split(":")[2];
			 * break;
			 * 
			 * 
			 * case "NFC":
			 * loginAs=CONFIG.getProperty("NFSscenarios").split(":")[0];
			 * loginAsFullName=CONFIG.getProperty("NFSscenarios").split(":")[1];
			 * loginUser_code=CONFIG.getProperty("NFSscenarios").split(":")[2];
			 * break;
			 * 
			 * 
			 * case "DS":
			 * loginAs=CONFIG.getProperty("DietarySupplementscenarios").split(":")[0];
			 * loginAsFullName=CONFIG.getProperty("DietarySupplementscenarios").split(":")[1
			 * ];
			 * loginUser_code=CONFIG.getProperty("DietarySupplementscenarios").split(":")[2]
			 * ;
			 * break;
			 * 
			 * case "ISR":
			 * loginAs=CONFIG.getProperty("ISRscenarios").split(":")[0];
			 * loginAsFullName=CONFIG.getProperty("ISRscenarios").split(":")[1];
			 * loginUser_code=CONFIG.getProperty("ISRscenarios").split(":")[2];
			 * break;
			 * 
			 * 
			 * case "SCFS":
			 * loginAs=CONFIG.getProperty("SCFSScenarios").split(":")[0];
			 * loginAsFullName=CONFIG.getProperty("SCFSScenarios").split(":")[1];
			 * loginUser_code=CONFIG.getProperty("SCFSScenarios").split(":")[2];
			 * break;
			 * 
			 * 
			 * case "TP":
			 * loginAs=CONFIG.getProperty("TestPlanscenarios").split(":")[0];
			 * loginAsFullName=CONFIG.getProperty("TestPlanscenarios").split(":")[1];
			 * loginUser_code=CONFIG.getProperty("TestPlanscenarios").split(":")[2];
			 * break;
			 * 
			 * case "Agriculture":
			 * loginAs=CONFIG.getProperty("iAGscenarios").split(":")[0];
			 * loginAsFullName=CONFIG.getProperty("iAGscenarios").split(":")[1];
			 * loginUser_code=CONFIG.getProperty("iAGscenarios").split(":")[2];
			 * break;
			 * 
			 * case "Invoice":
			 * loginAs=CONFIG.getProperty("Invoicescenarios").split(":")[0];
			 * loginAsFullName=CONFIG.getProperty("Invoicescenarios").split(":")[1];
			 * loginUser_code=CONFIG.getProperty("Invoicescenarios").split(":")[2];
			 * break;
			 * 
			 * default:
			 * loginAs=CONFIG.getProperty("Default").split(":")[1];
			 * loginAsFullName=CONFIG.getProperty("Default").split(":")[1];
			 * loginUser_code=CONFIG.getProperty("Default").split(":")[2];
			 * 
			 * }
			 * 
			 * }
			 * 
			 */

			// if (!(siteUrl.contains("securelogin"))) {

			// loginAs="";
			switch (currentSuiteCode) {

				case "WO":
					loginAs = CONFIG.getProperty("WorkOrderscenarios").split(":")[0];
					loginAsFullName = CONFIG.getProperty("WorkOrderscenarios").split(":")[1];
					loginUser_code = CONFIG.getProperty("WorkOrderscenarios").split(":")[2];
					break;

				case "FPC_WO":
					loginAs = CONFIG.getProperty("WorkOrderscenarios").split(":")[0];
					loginAsFullName = CONFIG.getProperty("WorkOrderscenarios").split(":")[1];
					loginUser_code = CONFIG.getProperty("WorkOrderscenarios").split(":")[2];
					break;
				/*
				 * case "Wales_WO":
				 * loginAs=null;//null becasue we are passing user in excel as it is data driven
				 * and may be some case is for different user
				 * loginAsFullName=null;//CONFIG.getProperty("WorkOrderscenarios").split(":")[1]
				 * ;
				 * loginUser_code=null;//CONFIG.getProperty("WorkOrderscenarios").split(":")[2];
				 * break;
				 */

				case "PO":
					loginAs = CONFIG.getProperty("QuoteGeneratorScenarios").split(":")[0];
					loginAsFullName = CONFIG.getProperty("QuoteGeneratorScenarios").split(":")[1];
					loginUser_code = CONFIG.getProperty("QuoteGeneratorScenarios").split(":")[2];
					break;

				case "ISR":
					loginAs = CONFIG.getProperty("ISRscenarios").split(":")[0];
					loginAsFullName = CONFIG.getProperty("ISRscenarios").split(":")[1];
					loginUser_code = CONFIG.getProperty("ISRscenarios").split(":")[2];
					break;

				/*
				 * case "SCFS":
				 * loginAs=CONFIG.getProperty("SCFSScenarios").split(":")[0];
				 * loginAsFullName=CONFIG.getProperty("SCFSScenarios").split(":")[1];
				 * loginUser_code=CONFIG.getProperty("SCFSScenarios").split(":")[2];
				 * break;
				 * 
				 */

				case "NFC":
					loginAs = CONFIG.getProperty("NFCscenarios").split(":")[0];
					loginAsFullName = CONFIG.getProperty("NFCscenarios").split(":")[1];
					loginUser_code = CONFIG.getProperty("NFCscenarios").split(":")[2];
					break;

				case "DS":
					loginAs = CONFIG.getProperty("DietarySupplementscenarios").split(":")[0];
					loginAsFullName = CONFIG.getProperty("DietarySupplementscenarios").split(":")[1];
					loginUser_code = CONFIG.getProperty("DietarySupplementscenarios").split(":")[2];
					break;

				case "Agriculture":
					loginAs = CONFIG.getProperty("iAGscenarios").split(":")[0];
					loginAsFullName = CONFIG.getProperty("iAGscenarios").split(":")[1];
					loginUser_code = CONFIG.getProperty("iAGscenarios").split(":")[2];
					break;

				case "TP":
					loginAs = CONFIG.getProperty("TestPlanscenarios").split(":")[0];
					loginAsFullName = CONFIG.getProperty("TestPlanscenarios").split(":")[1];
					loginUser_code = CONFIG.getProperty("TestPlanscenarios").split(":")[2];
					break;

				case "HS":
					loginAs = CONFIG.getProperty("HealthScienceScenarios").split(":")[0];
					// loginAsFullName=CONFIG.getProperty("TestPlanscenarios").split(":")[1];
					// loginUser_code=CONFIG.getProperty("TestPlanscenarios").split(":")[2];
					break;

				default:
					loginAs = CONFIG.getProperty("Default").split(":")[0];
					loginAsFullName = CONFIG.getProperty("Default").split(":")[1];
					loginUser_code = CONFIG.getProperty("Default").split(":")[2];

			}

			// }

			fnpMymsg("Navigating to url --->" + siteUrl);
			// fnpCheckErrorPageNotFound();

			// fnpWithout_SSO_login( siteUrl, loginAs, userName, password ) ;
			// fnpSSO_login("psingh@nsf.org", userName);
			fnpMymsg("Before sso login.");
			String mainUser = CONFIG.getProperty("MainUserForSSO_withCompleteEmailID").trim();
			// if (mainUser.equalsIgnoreCase(loginAs)) {
			if (mainUser.contains(loginAs)) {
				fnpSSO_login(mainUser, "");
			} else {
				fnpSSO_login(mainUser, loginAs);
			}
			fnpMymsg("After sso login.");
			present = true;

			if (!referenceSuite.equalsIgnoreCase(currentSuiteName)) {
				fnpFetchApplicationVersion(currentSuiteName);
				referenceSuite = currentSuiteName;
			}

			// fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
			fnpLoading_wait_CheckLoadingIconOnlyIniPulse();

			fnpMymsg("Just before topmenu clicking.");
			fnpClickAtTopWorkAroundForClickingMenu();
			fnpMymsg("Just after topmenu clicking.");
			// fnpLoading_wait();

		} catch (Throwable t) {

			fnpDesireScreenshot("LoginFailed");
			fnpDesireScreenshot_old("LoginFailed");
			present = false;
			String stackTrace = Throwables.getStackTraceAsString(t);
			String errorMsg = t.getMessage();
			errorMsg = "\n\n\n\n Login failed.See screenshot 'LoginFailed' \n\n\n\n" + errorMsg + stackTrace;
			Exception c = new Exception(errorMsg);
			ErrorUtil.addVerificationFailure(c);

			killprocess();
			IsBrowserPresentAlready = false;

			throw new Exception(errorMsg);// reports

		}

		return present;
	}

	/***********
	 * Function to Launch the browser and login with user credential details
	 *************/
	public static void fnpDeleteSMTPMessages() throws Exception {

		try {

			killprocess();

			fnpLaunchBrowser();
			driver.get("http://devsmtp.nsf.org:8080/");
			String deleteAllxpath = ".//a[@ng-click='deleteAll()']";
			// fnpWaitForVisible_NotInOR(deleteAllxpath, 10);
			fnpWaitForVisible_NotInOR(deleteAllxpath);

			// fnpDesireScreenshot("SMTPMessageBeforeClick");
			String imageName = "SMTPMessageBeforeClick";

			/*			*//************ Taking screenshots of more size *************************/
			/*
			 * 
			 * FileUtils.forceMkdir(new File((System.getProperty("user.dir") +
			 * screenshots_path + "//applicationVersion//")));
			 * Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			 * Rectangle screenRectangle = new Rectangle(screenSize);
			 * Robot robot = new Robot();
			 * BufferedImage image = robot.createScreenCapture(screenRectangle);
			 * 
			 * ImageIO.write(image, "png", new File((System.getProperty("user.dir") +
			 * screenshots_path + "//applicationVersion//" + "//" + (imageName+(SShots++)) +
			 * "_" + ".PNG")));
			 *//**********************************************************************//*
																						*/

			fnpDesireScreenshot_Advance("SMTPMessageAfterClick",
					screenshots_path + "//applicationVersion//" + "//" + (imageName + (SShots++)) + "_" + ".PNG");

			driver.findElement(By.xpath(deleteAllxpath)).click();
			// Thread.sleep(2000);
			String deleteAllConfirmationButton_xpath = ".//button[@ng-click='deleteAllConfirm()']";
			fnpWaitForVisible_NotInOR(deleteAllConfirmationButton_xpath);
			driver.findElement(By.xpath(deleteAllConfirmationButton_xpath)).click();
			// Thread.sleep(2000);
			fnpWaitForVisible_NotInOR(deleteAllxpath);
			// fnpDesireScreenshot("SMTPMessageAfterClick");

			imageName = "SMTPMessageAfterClick";

			/*			 
						*//************ Taking screenshots of more size *************************/
			/*
			 * 
			 * FileUtils.forceMkdir(new File((System.getProperty("user.dir") +
			 * screenshots_path + "//applicationVersion//")));
			 * Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			 * Rectangle screenRectangle = new Rectangle(screenSize);
			 * Robot robot = new Robot();
			 * BufferedImage image = robot.createScreenCapture(screenRectangle);
			 * 
			 * ImageIO.write(image, "png", new File((System.getProperty("user.dir") +
			 * screenshots_path + "//applicationVersion//" + "//" + (imageName+(SShots++)) +
			 * "_" + ".PNG")));
			 *//**********************************************************************//*
																						*/

			fnpDesireScreenshot_Advance("SMTPMessageAfterClick",
					screenshots_path + "//applicationVersion//" + "//" + (imageName + (SShots++)) + "_" + ".PNG");

			killprocess();

		} catch (Throwable t) {

			fnpDesireScreenshot("fnpDeleteSMTPMessagesFailed");

			String stackTrace = Throwables.getStackTraceAsString(t);
			String errorMsg = t.getMessage();
			errorMsg = "\n\n\n\n Login failed.See screenshot 'LoginFailed' \n\n\n\n" + errorMsg + stackTrace;
			Exception c = new Exception(errorMsg);
			ErrorUtil.addVerificationFailure(c);

			killprocess();
			IsBrowserPresentAlready = false;

			throw new Exception(errorMsg);// reports

		}

	}

	/***********
	 * Function to Launch the browser and login with user credential details
	 *************/
	public static boolean fnpLaunchBrowserAndLogin(String userName, String password) throws Exception {
		boolean present;
		try {

			killprocess();
			// just for try as so many chromedriver.exe instances left on 14 m/c and
			// windowUtil class is deprecated / no longer present in selenium after 3.11
			// version
			// fnptryingKillProcess_usingLoop();

			fnpLaunchBrowser();

			String siteUrl = null;

			if (excelSiteURL != null) {
				if (excelSiteURL.equalsIgnoreCase("")) {
					siteUrl = CONFIG.getProperty("testSiteName");
				} else {
					// siteUrl = excelSiteURL;

					if (excelSiteURL.contains("Oasis_Url")) {// for Oasis site
																// in WO ISR
						siteUrl = excelSiteURL.split("Oasis_Url:")[0];
					} else {
						// rest for all
						siteUrl = excelSiteURL.split("LoginAsTestSiteName:")[0];
					}

				}
			} else {
				siteUrl = CONFIG.getProperty("testSiteName");
			}

			driver.get(siteUrl);

			fnpMymsg("PASSED == Navigating to url --->" + siteUrl);

			try {
				fnpCheckErrorPageNotFound();
			} catch (Throwable t) {
				if (t.getMessage().contains("Error 404--Not Found")) {
					throw new Exception(" Site '" + siteUrl
							+ "' is down as script is getting this 404 error while navigating to this url.");

				} else {
					throw t;
				}
			}

			fnpWaitTillVisiblityOfElementNClickable_OR("Login_id");

			// Thread.sleep(1000);
			String handle1 = driver.getWindowHandle();

			String originalHandle1 = driver.getWindowHandle();

			if (!iPulse_Invalid_Password_Verification) {

				fnpMymsg("First going to check login with invalid/wrong password.");
				fnpDisplayingMessageInFrame_fnpMymsg("First going to check login with invalid/wrong password.", 10);
				fnpType("OR", "UserName_id", userName);

				fnpType("OR", "password_id", "wrong_password");

				// fnpClick_OR("Login");
				fnpGetORObjectX("Login_id").click();
				fnpMymsg("PASSED == Clicked done on login button.");
				// Thread.sleep(2000);
				fnpLoading_wait();

				/*
				 * if (fnpCheckElementDisplayed("errorMessage_id", 30)) {
				 * // throw new Exception("Login is not successfull.");
				 * fnpMymsg("Login is failed as expected due to wrong password");
				 * fnpMouseHover_OR_EvenMultipleObjectsHavingSameXpath_usingSpecialForErrorClassOnly
				 * ("errorMessage_id");
				 * String errMsg =
				 * fnpGetText_OR_EvenMultipleObjectsHavingSameXpath_specialForExtractingErrorMsgTextOnlyEvenIfNotVisible
				 * ("errorMessage_id");
				 * fnpMymsg(" Error  thrown by application after inserting wrong password is --- "
				 * + errMsg);
				 * 
				 * } else {
				 * if (fnpCheckElementDisplayedImmediately("logOutBtn2_id")) {
				 * throw new Exception("Login should not be successfull with wrong password.");
				 * }
				 * }
				 */

				if (fnpCheckElementDisplayed("errorMessage_id", 30)) {
					// throw new Exception("Login is not successfull.");
					fnpMymsg("Login is failed as expected due to wrong password");
					fnpMouseHover_OR_EvenMultipleObjectsHavingSameXpath_usingSpecialForErrorClassOnly(
							"errorMessage_id");
					String errMsg = fnpGetText_OR_EvenMultipleObjectsHavingSameXpath_specialForExtractingErrorMsgTextOnlyEvenIfNotVisible(
							"errorMessage_id");
					fnpMymsg(" Error  thrown by application after inserting wrong password is --- " + errMsg);

				} else {
					String text;
					if (fnpCheckElementDisplayedImmediately("logOutBtn2_linkText")) {
						text = "Login should not be successfull with wrong password.";
						fnpDisplayingMessageInFrame_fnpMymsg(text, 10);
						Thread.sleep(1000);
						throw new Exception(text);
					} else {
						text = "Error message on inserting either wrong username or wrong password is not coming, hence failed as error message should come on wrong credential details.";
						fnpMymsg(text);
						fnpDisplayingMessageInFrame_fnpMymsg(text, 10);
						Thread.sleep(1000);
						throw new Exception(text);

					}
				}

				fnpMymsg("Now going to refresh the screen and again login with valid user credential details.");
				driver.navigate().refresh();
				// Thread.sleep(2000);
				iPulse_Invalid_Password_Verification = true;
			}

			fnpType("OR", "UserName_id", userName);

			fnpType("OR", "password_id", password);

			fnpMouseHover("Login_id");
			fnpClick_OR("Login_id");

			fnpMymsg("PASSED == Clicked done on Login button.");

			Set<String> handle2 = driver.getWindowHandles();
			int a = handle2.size();
			if (fnpCheckElementDisplayedImmediately("errorMessage_id")) {
				// throw new Exception("Login is not successfull.");

				String errMsg = fnpGetText_OR_EvenMultipleObjectsHavingSameXpath_specialForExtractingErrorMsgTextOnlyEvenIfNotVisible(
						"errorMessage_id");
				throw new Exception("Login is not successfull. Error thrown by application is : " + errMsg);
			}

			// Thread.sleep(1000*10);
			fnpClick_OR("xpathForAck");
			fnpMymsg("Just after clicking Acknowledge button");
			fnpWaitTillThisElementIsNoLongerDisplayedTillMaxWait_OR("xpathForAck",
					"Acknowledge button is still visible after clicking it", 120);
			fnpMymsg("Just after waiting for Acknowledge button visiblity over");

			try {

				fnpWaitTillVisiblityOfElementNClickable_OR("logOutBtn2_linkText");

			} catch (Throwable t) {
				fnpDesireScreenshot("justBeforeRefreshing");
				fnpMymsg(
						"@  -----due to invalid selector , logoutout link not identified, so refreshing it again. See screenshot 'justBeforeRefreshing' .");
				driver.navigate().refresh();
				Thread.sleep(8000);
				fnpWaitForVisible("logOutBtn2_linkText");
			}

			fnpWaitTillVisiblityOfElementNClickable_OR("logOutBtn2_linkText");

			present = true;

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
			errorMsg = "\n\n\n\n Login failed.See screenshot 'LoginFailed' \n\n\n\n" + errorMsg + stackTrace;
			Exception c = new Exception(errorMsg);
			ErrorUtil.addVerificationFailure(c);

			// killprocess();
			IsBrowserPresentAlready = false;

			throw new Exception(errorMsg);// reports

		}

		return present;
	}

	/***********
	 * Function to find and return the object using Xpath which are not present
	 * in OR
	 *************/
	public static WebElement fnpGetORObjectX___NOR2(String Xpath) throws Exception {
		int retries = 0;

		while (true) {
			try {

				return driver.findElement(By.xpath(Xpath));

			} catch (org.openqa.selenium.StaleElementReferenceException s) {
				if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					Thread.sleep(1000);
					retries++;
					fnpMymsg(retries + "In staleElementException catch block of fnpGetORObjectX___NOR function for "
							+ Xpath + "\n\n\n and Exception thrown is-" + "->'"
							+ s.getMessage() + "' .\n\n\n");
					continue;
				} else {
					throw s;
				}
			}

			catch (Throwable t) {
				// fnpDesireScreenshot("NotPresentElement");
				throw new Exception(t.getMessage() + "   So,Unable to fetch getText for  element [" + Xpath
						+ "] ,plz see screenshot [NotPresentElement" + SShots + "]");

			}
		}

	}

	/***********
	 * Function to find and return the object using Xpath which are present in
	 * OR
	 * 
	 * @throws Throwable
	 *************/
	public static WebElement fnpGetORObjectX___NOR(String Xpath) throws Throwable {

		// fnpLoading_wait_CheckLoadingIconOnlyIniPulse(); // Added on 4 -11-16

		int retries = 0;
		int throwablecount = 0;
		while (true) {
			try {
				driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
				Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
						.withTimeout((Long.parseLong(CONFIG.getProperty("genMax_waitTime"))), TimeUnit.SECONDS)
						.pollingEvery(2, TimeUnit.SECONDS)
						.ignoring(org.openqa.selenium.NoSuchElementException.class)
						.ignoring(org.openqa.selenium.InvalidSelectorException.class)
						.ignoring(org.openqa.selenium.StaleElementReferenceException.class)
						.ignoring(org.openqa.selenium.WebDriverException.class);

				WebElement foo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
				foo = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Xpath)));
				return driver.findElement(By.xpath(Xpath));

			} catch (org.openqa.selenium.StaleElementReferenceException s) {
				if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					Thread.sleep(1000);
					retries++;
					fnpMymsg(retries + "In staleElementException catch block of fnpGetORObjectX___NOR function for "
							+ Xpath);
					// continue;
				} else {
					throw s;
				}
			}

			catch (Throwable t) {

				if (throwablecount < 1) {
					throwablecount++;
					Thread.sleep(1000);
					retries++;
					fnpMymsg(retries + "In Throwable catch block of fnpGetORObjectX___NOR function for " + Xpath);
					// continue;
				} else {
					// fnpDesireScreenshot("NotPresent" + Xpath);
					throw new Exception("   So,Unable to find element with name [" + Xpath
							+ "] ,plz see screenshot [NotPresent" + Xpath + SShots + "].  Expception is ---"
							+ t.getMessage());
				}
			}

			finally {
				driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")),
						TimeUnit.SECONDS);
			}
		}

	}

	public static WebElement fnpGetORObjectX___NOR_withoutCheckingElementClickable(String Xpath) throws Throwable {

		// fnpLoading_wait_CheckLoadingIconOnlyIniPulse(); // Added on 4 -11-16

		int retries = 0;
		int throwablecount = 0;
		while (true) {
			try {
				driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
				Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
						.withTimeout((Long.parseLong(CONFIG.getProperty("genMax_waitTime"))), TimeUnit.SECONDS)
						.pollingEvery(2, TimeUnit.SECONDS).ignoring(org.openqa.selenium.NoSuchElementException.class)
						.ignoring(org.openqa.selenium.InvalidSelectorException.class)
						.ignoring(org.openqa.selenium.StaleElementReferenceException.class)
						.ignoring(org.openqa.selenium.WebDriverException.class);

				WebElement foo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
				// foo = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Xpath)));
				return driver.findElement(By.xpath(Xpath));

			} catch (org.openqa.selenium.StaleElementReferenceException s) {
				if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					Thread.sleep(1000);
					retries++;
					fnpMymsg(retries + "In staleElementException catch block of fnpGetORObjectX___NOR function for "
							+ Xpath);
					// continue;
				} else {
					throw s;
				}
			}

			catch (Throwable t) {

				if (throwablecount < 1) {
					throwablecount++;
					Thread.sleep(1000);
					retries++;
					fnpMymsg(retries + "In Throwable catch block of fnpGetORObjectX___NOR function for " + Xpath);
					// continue;
				} else {
					// fnpDesireScreenshot("NotPresent" + Xpath);
					throw new Exception("   So,Unable to find element with name [" + Xpath
							+ "] ,plz see screenshot [NotPresent" + Xpath + SShots + "].  Expception is ---"
							+ t.getMessage());
				}
			}

			finally {
				driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")),
						TimeUnit.SECONDS);
			}
		}

	}

	/***********
	 * Function to find and return the object using Xpath which are present in
	 * OR
	 * 
	 * @throws Throwable
	 *************/
	public static WebElement fnpGetORObjectX_deprecatedOn_19_12_2017(String XpathKey) throws Throwable {

		// fnpLoading_wait_CheckLoadingIconOnlyIniPulse(); // Added on 4 -11-16

		int retries = 0;
		while (true) {
			try {
				driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
				Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
						.withTimeout((Long.parseLong(CONFIG.getProperty("genMax_waitTime"))), TimeUnit.SECONDS)
						.pollingEvery(2, TimeUnit.SECONDS).ignoring(org.openqa.selenium.NoSuchElementException.class)
						.ignoring(org.openqa.selenium.InvalidSelectorException.class)
						.ignoring(org.openqa.selenium.StaleElementReferenceException.class)
						.ignoring(org.openqa.selenium.WebDriverException.class)
						.ignoring(org.openqa.selenium.StaleElementReferenceException.class);

				if (OR.getProperty(XpathKey).contains("~")) {
					String[] s1 = OR.getProperty(XpathKey).split("~");
					String locatorValue = s1[1];
					String locator = s1[0];

					if (locator.contains("id")) {// id
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));// xpath
						wait.until(ExpectedConditions.elementToBeClickable(By.id(locatorValue)));
						return driver.findElement(By.id(locatorValue));
					} else {
						if (locator.contains("name")) {// name
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue)));// id
							wait.until(ExpectedConditions.elementToBeClickable(By.name(locatorValue)));
							return driver.findElement(By.name(locatorValue));
						} else {
							if (locator.toLowerCase().contains("linktext")) {// linkText
								wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locatorValue)));
								wait.until(ExpectedConditions.elementToBeClickable(By.linkText(locatorValue)));
								return driver.findElement(By.linkText(locatorValue));
							} else {
								if (locator.toLowerCase().contains("partialLinkText")) {// partialLinkText
									wait.until(ExpectedConditions
											.visibilityOfElementLocated(By.partialLinkText(locatorValue)));
									wait.until(
											ExpectedConditions.elementToBeClickable(By.partialLinkText(locatorValue)));
									return driver.findElement(By.partialLinkText(locatorValue));
								} else {
									if (locator.toLowerCase().contains("tagName")) {// tagName
										wait.until(ExpectedConditions
												.visibilityOfElementLocated(By.tagName(locatorValue)));
										wait.until(ExpectedConditions.elementToBeClickable(By.tagName(locatorValue)));
										return driver.findElement(By.tagName(locatorValue));
									} else {
										if (locator.toLowerCase().contains("className")) {// className
											wait.until(ExpectedConditions
													.visibilityOfElementLocated(By.className(locatorValue)));
											wait.until(ExpectedConditions
													.elementToBeClickable(By.className(locatorValue)));
											return driver.findElement(By.className(locatorValue));
										} else {
											if (locator.toLowerCase().contains("css")) {// cssSelector
												wait.until(ExpectedConditions
														.visibilityOfElementLocated(By.cssSelector(locatorValue)));
												wait.until(ExpectedConditions
														.elementToBeClickable(By.cssSelector(locatorValue)));
												return driver.findElement(By.cssSelector(locatorValue));
											} else {

												if (locator.toLowerCase().contains("xpath")) {// xpath
													wait.until(ExpectedConditions
															.visibilityOfElementLocated(By.xpath(locatorValue)));
													wait.until(ExpectedConditions
															.elementToBeClickable(By.xpath(locatorValue)));
													return driver.findElement(By.xpath(locatorValue));
												} else {

													wait.until(ExpectedConditions
															.visibilityOfElementLocated(By.xpath(locatorValue)));
													wait.until(ExpectedConditions
															.elementToBeClickable(By.xpath(locatorValue)));
													return driver.findElement(By.xpath(locatorValue));
												}
											}
										}
									}
								}
							}
						}
					}
				} else {

					/****** By default Xpath will be assumed *****/
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(XpathKey))));
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(XpathKey))));
					return driver.findElement(By.xpath(OR.getProperty(XpathKey)));

				}

			} catch (org.openqa.selenium.StaleElementReferenceException s) {
				if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					Thread.sleep(1000);
					retries++;
					fnpMymsg(retries + "In staleElementException catch block of fnpGetORObjectX function for "
							+ XpathKey);
					// continue;
				} else {
					throw s;
				}
			}

			catch (org.openqa.selenium.InvalidSelectorException is) {
				if (retries < 3) {
					Thread.sleep(1000);
					retries++;
					fnpMymsg(retries + "In InvalidSelectorException catch block of fnpGetORObjectX function for "
							+ XpathKey);
					// continue;
				} else {
					throw is;
				}
			} catch (Throwable t) {

				// fnpDesireScreenshot("NotPresent" + XpathKey);
				throw new Exception(t.getMessage() + "   So,Unable to find element with name [" + XpathKey
						+ "] ,plz see screenshot [NotPresent" + XpathKey + SShots + "]");

			}

			finally {
				driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")),
						TimeUnit.SECONDS);
			}
		}

	}

	/***********
	 * Function to find and return the object using Xpath which are present in
	 * OR
	 * 
	 * @throws Throwable
	 *************/
	public static WebElement fnpGetORObjectX(String XpathKey) throws Throwable {

		// fnpLoading_wait_CheckLoadingIconOnlyIniPulse(); // Added on 4 -11-16

		int retries = 0;
		while (true) {
			try {
				driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

				Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
						.withTimeout((Long.parseLong(CONFIG.getProperty("genMax_waitTime"))), TimeUnit.SECONDS)
						.pollingEvery(2, TimeUnit.SECONDS).ignoring(org.openqa.selenium.NoSuchElementException.class)
						.ignoring(org.openqa.selenium.InvalidSelectorException.class)
						.ignoring(org.openqa.selenium.StaleElementReferenceException.class)
						.ignoring(org.openqa.selenium.WebDriverException.class)
						.ignoring(org.openqa.selenium.StaleElementReferenceException.class);

				if (OR.getProperty(XpathKey).contains("~")) {

					String[] s1 = OR.getProperty(XpathKey).split("~");
					String locatorValue = s1[1];
					String locator = s1[0];

					switch (locator) {
						case "id":
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));// xpath
							wait.until(ExpectedConditions.elementToBeClickable(By.id(locatorValue)));
							return driver.findElement(By.id(locatorValue));

						case "name":
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue)));// id
							wait.until(ExpectedConditions.elementToBeClickable(By.name(locatorValue)));
							return driver.findElement(By.name(locatorValue));

						case "linkText":
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locatorValue)));
							wait.until(ExpectedConditions.elementToBeClickable(By.linkText(locatorValue)));
							return driver.findElement(By.linkText(locatorValue));

						case "partialLinkText":
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(locatorValue)));
							wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(locatorValue)));
							return driver.findElement(By.partialLinkText(locatorValue));

						case "tagName":
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(locatorValue)));
							wait.until(ExpectedConditions.elementToBeClickable(By.tagName(locatorValue)));
							return driver.findElement(By.tagName(locatorValue));

						case "className":
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(locatorValue)));
							wait.until(ExpectedConditions.elementToBeClickable(By.className(locatorValue)));
							return driver.findElement(By.className(locatorValue));

						case "cssSelector":
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locatorValue)));
							wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(locatorValue)));
							return driver.findElement(By.cssSelector(locatorValue));

						case "xpath":
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorValue)));
							return driver.findElement(By.xpath(locatorValue));

						default:

							/****** By default Xpath will be assumed *****/
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorValue)));
							return driver.findElement(By.xpath(locatorValue));

					}

				} else {

					/****** By default Xpath will be assumed *****/
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(XpathKey))));
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(XpathKey))));
					return driver.findElement(By.xpath(OR.getProperty(XpathKey)));

				}

			} catch (org.openqa.selenium.StaleElementReferenceException s) {
				if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					Thread.sleep(1000);
					retries++;
					fnpMymsg(retries + "In staleElementException catch block of fnpGetORObjectX function for "
							+ XpathKey);
					// continue;
				} else {
					throw s;
				}
			}

			catch (org.openqa.selenium.InvalidSelectorException is) {
				if (retries < 3) {
					Thread.sleep(1000);
					retries++;
					fnpMymsg(retries + "In InvalidSelectorException catch block of fnpGetORObjectX function for "
							+ XpathKey);
					// continue;
				} else {
					throw is;
				}
			} catch (Throwable t) {

				// fnpDesireScreenshot("NotPresent" + XpathKey);
				throw new Exception(t.getMessage() + "   So,Unable to find element with name [" + XpathKey
						+ "] ,plz see screenshot [NotPresent" + XpathKey + SShots + "]");

			}

			finally {
				driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")),
						TimeUnit.SECONDS);
			}
		}

	}

	public static WebElement fnpGetORObjectX_withoutCheckingElementClickable(String XpathKey) throws Throwable {

		// fnpLoading_wait_CheckLoadingIconOnlyIniPulse(); // Added on 4 -11-16

		int retries = 0;
		while (true) {
			try {
				driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

				Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
						.withTimeout((Long.parseLong(CONFIG.getProperty("genMax_waitTime"))), TimeUnit.SECONDS)
						.pollingEvery(2, TimeUnit.SECONDS).ignoring(org.openqa.selenium.NoSuchElementException.class)
						.ignoring(org.openqa.selenium.InvalidSelectorException.class)
						.ignoring(org.openqa.selenium.StaleElementReferenceException.class)
						.ignoring(org.openqa.selenium.WebDriverException.class)
						.ignoring(org.openqa.selenium.StaleElementReferenceException.class);

				if (OR.getProperty(XpathKey).contains("~")) {

					String[] s1 = OR.getProperty(XpathKey).split("~");
					String locatorValue = s1[1];
					String locator = s1[0];

					switch (locator) {
						case "id":
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));// xpath
							// wait.until(ExpectedConditions.elementToBeClickable(By.id(locatorValue)));
							return driver.findElement(By.id(locatorValue));

						case "name":
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue)));// id
							// wait.until(ExpectedConditions.elementToBeClickable(By.name(locatorValue)));
							return driver.findElement(By.name(locatorValue));

						case "linkText":
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locatorValue)));
							// wait.until(ExpectedConditions.elementToBeClickable(By.linkText(locatorValue)));
							return driver.findElement(By.linkText(locatorValue));

						case "partialLinkText":
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(locatorValue)));
							// wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(locatorValue)));
							return driver.findElement(By.partialLinkText(locatorValue));

						case "tagName":
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(locatorValue)));
							// wait.until(ExpectedConditions.elementToBeClickable(By.tagName(locatorValue)));
							return driver.findElement(By.tagName(locatorValue));

						case "className":
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(locatorValue)));
							// wait.until(ExpectedConditions.elementToBeClickable(By.className(locatorValue)));
							return driver.findElement(By.className(locatorValue));

						case "cssSelector":
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locatorValue)));
							// wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(locatorValue)));
							return driver.findElement(By.cssSelector(locatorValue));

						case "xpath":
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
							// wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorValue)));
							return driver.findElement(By.xpath(locatorValue));

						default:

							/****** By default Xpath will be assumed *****/
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
							// wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorValue)));
							return driver.findElement(By.xpath(locatorValue));

					}

				} else {

					/****** By default Xpath will be assumed *****/
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(XpathKey))));
					// wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(XpathKey))));
					return driver.findElement(By.xpath(OR.getProperty(XpathKey)));

				}

			} catch (org.openqa.selenium.StaleElementReferenceException s) {
				if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					Thread.sleep(1000);
					retries++;
					fnpMymsg(retries + "In staleElementException catch block of fnpGetORObjectX function for "
							+ XpathKey);
					// continue;
				} else {
					throw s;
				}
			}

			catch (org.openqa.selenium.InvalidSelectorException is) {
				if (retries < 3) {
					Thread.sleep(1000);
					retries++;
					fnpMymsg(retries + "In InvalidSelectorException catch block of fnpGetORObjectX function for "
							+ XpathKey);
					// continue;
				} else {
					throw is;
				}
			} catch (Throwable t) {

				// fnpDesireScreenshot("NotPresent" + XpathKey);
				throw new Exception(t.getMessage() + "   So,Unable to find element with name [" + XpathKey
						+ "] ,plz see screenshot [NotPresent" + XpathKey + SShots + "]");

			}

			finally {
				driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")),
						TimeUnit.SECONDS);
			}
		}

	}

	/***********
	 * Function to find and return the object using Xpath which are present in
	 * OR
	 * 
	 * @return
	 *************/
	public static List<WebElement> fnpGetORObject_list0(String Xpath, int maxTimeInSeconds) throws Exception {

		int retries = 0;
		while (true) {
			try {
				driver.manage().timeouts().implicitlyWait(maxTimeInSeconds, TimeUnit.SECONDS);
				return driver.findElements(By.xpath(Xpath));

			} catch (org.openqa.selenium.StaleElementReferenceException s) {

				if (retries < 3) {
					Thread.sleep(1000);
					retries++;
					fnpMymsg(retries + "In staleElementException catch block of fnpGetORObject_list function for "
							+ Xpath);
					// continue;
				} else {
					throw s;
				}
			}

			catch (org.openqa.selenium.InvalidSelectorException is) {
				if (retries < 3) {
					Thread.sleep(1000);
					retries++;
					fnpMymsg(retries + "In InvalidSelectorException catch block of fnpGetORObject_list function for "
							+ Xpath);
					// continue;
				} else {
					throw is;

					// break;
				}
			} catch (Throwable t) {

				// fnpDesireScreenshot("NotPresent" + Xpath);
				throw new Exception(t.getMessage() + "   So,Unable to find element with name [" + Xpath
						+ "] ,plz see screenshot [NotPresent" + Xpath + SShots + "]");

			}

			finally {

				driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")),
						TimeUnit.SECONDS);
				// return null;
			}
		}

	}

	/***********
	 * Function to find and return the object using Xpath which are present in
	 * OR
	 * 
	 * @return
	 *************/

	public static List<WebElement> fnpGetORObject_list(String XpathKey, int maxTimeInSeconds) throws Exception {

		int retries = 0;
		while (true) {
			try {

				driver.manage().timeouts().implicitlyWait(maxTimeInSeconds, TimeUnit.SECONDS);

				if (OR.getProperty(XpathKey).contains("~")) {
					return fnpReturnWebElementAfterExtractLocatorandValue(OR.getProperty(XpathKey));
				} else {

					/****** By default Xpath will be assumed *****/
					return driver.findElements(By.xpath(OR.getProperty(XpathKey)));
				}

			} catch (org.openqa.selenium.StaleElementReferenceException s) {

				// Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
				if (retries < maxTimeInSeconds) {
					Thread.sleep(1000);
					retries++;
					fnpMymsg(retries
							+ "In staleElementException catch block of fnpGetORObject_list 2 arguments  function for "
							+ XpathKey);
					// continue;
				} else {
					throw s;
				}
			}

			catch (org.openqa.selenium.InvalidSelectorException is) {

				if (retries < 10) {
					Thread.sleep(1000);
					retries++;
					fnpMymsg(retries
							+ "In InvalidSelectorException catch block of fnpGetORObject_list 2 arguments  function for "
							+ XpathKey);
					// continue;
				} else {
					throw is;
				}
			} catch (Throwable t) {
				if (retries < maxTimeInSeconds) {
					// Thread.sleep(1000);
					retries++;
					fnpMymsg(retries + "In Throwable catch block of fnpGetORObject_list 2 arguments  function for "
							+ XpathKey);

				} else {
					// fnpDesireScreenshot("NotPresent" + XpathKey);
					throw new Exception(t.getMessage() + "   So,Unable to find element with name [" + XpathKey
							+ "] ,plz see screenshot [NotPresent" + XpathKey + SShots + "]");
				}
			}

			finally {
				driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")),
						TimeUnit.SECONDS);
			}
		}

	}

	/***************
	 * Returning list of objects having the same xpath, xpath is not present in OR
	 ****************/
	public static List<WebElement> fnpGetORObject_list_NOR(String Xpath, int maxTimeInSeconds) throws Exception {

		int retries = 0;
		while (true) {
			try {

				driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
				return driver.findElements(By.xpath(Xpath));

			} catch (org.openqa.selenium.StaleElementReferenceException s) {
				if (retries < maxTimeInSeconds) {
					Thread.sleep(1000);
					retries++;
					fnpMymsg(retries
							+ "In staleElementException catch block of fnpGetORObject_list 2 arguments  function for "
							+ Xpath);
					// continue;
				} else {
					throw s;
				}
			}

			catch (org.openqa.selenium.InvalidSelectorException is) {
				if (retries < 10) {
					Thread.sleep(1000);
					retries++;
					fnpMymsg(retries
							+ "In InvalidSelectorException catch block of fnpGetORObject_list 2 arguments  function for "
							+ Xpath);
					// continue;
				} else {
					throw is;
				}
			} catch (Throwable t) {
				if (retries < maxTimeInSeconds) {
					// Thread.sleep(1000);
					retries++;
					fnpMymsg(retries + "In Throwable catch block of fnpGetORObject_list 2 arguments  function for "
							+ Xpath);

				} else {
					// fnpDesireScreenshot("NotPresent" + Xpath);
					throw new Exception(t.getMessage() + "   So,Unable to find element with name [" + Xpath
							+ "] ,plz see screenshot [NotPresent" + Xpath + SShots + "]");
				}
			}

			finally {
				driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")),
						TimeUnit.SECONDS);
			}
		}

	}

	/***************
	 * Returning list of objects having the same xpath, xpath is present in OR
	 ****************/
	public static List<WebElement> fnpGetORObject_list(String XpathKey, int maxTimeInSeconds,
			long maxTimeInMilliSeconds) throws Throwable {

		int retries = 0;
		while (true) {
			try {
				/*
				 * if ((XpathKey.trim().toLowerCase().contains("error")) ||
				 * (XpathKey.trim().toLowerCase().contains("ProgressImageIcon"))
				 * || (XpathKey.trim().toLowerCase().contains("BlockedUIScreen"))) {
				 */
				if ((XpathKey.trim().toLowerCase().contains("error"))
						|| (XpathKey.trim().toLowerCase().contains("progressimageicon"))
						|| (XpathKey.trim().toLowerCase().contains("blockeduiscreen"))) {
					driver.manage().timeouts().implicitlyWait(1, TimeUnit.MILLISECONDS);

				} else {
					driver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS);

				}

				if (OR.getProperty(XpathKey).contains("~")) {
					return fnpReturnWebElementAfterExtractLocatorandValue(OR.getProperty(XpathKey));

				} else {

					/****** By default Xpath will be assumed *****/
					return driver.findElements(By.xpath(OR.getProperty(XpathKey)));
				}

			} catch (org.openqa.selenium.StaleElementReferenceException s) {
				if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					// if (retries < 3) {
					Thread.sleep(1000);
					retries++;
					fnpMymsg(retries
							+ "In staleElementException catch block of fnpGetORObject_list 3 arguments function for "
							+ XpathKey);
					// continue;
				} else {
					throw s;
				}
			}

			catch (org.openqa.selenium.InvalidSelectorException is) {

				if (retries < 10) {
					Thread.sleep(2000);

					fnpMymsg(" @  ---In InvalidSelectorException in ip addresss---" + fnpGetIPAddress()
							+ " machine  for retries --" + retries);

					retries++;
					fnpMymsg(retries
							+ "In InvalidSelectorException catch block of fnpGetORObject_list 3 arguments  function for "
							+ XpathKey);
					// continue;
				} else {

					fnpMymsg("");
					fnpMymsg("");
					fnpMymsg("");
					fnpMymsg("");
					fnpMymsg("");
					fnpMymsg("@   ---********Exception thrown in " + fnpGetIPAddress()
							+ " machine************************************************");

					// fnpMymsg(is.getMessage());
					fnpMymsg("");
					fnpMymsg("");
					fnpMymsg("");
					fnpMymsg("");
					fnpMymsg("@   ---********************************************************");
					// String PageSourceText = driver.getPageSource().toLowerCase();
					fnpMymsg("@   ---********************************************************");

					// fnpMymsg(fnpFormatReplaceSpecailCharacters(PageSourceText));
					fnpMymsg(
							"@   ---*********COMMENTING PAGE SOURCE For Time being before release on 4th March********************");

					fnpMymsg("@   ---********************************************************");
					fnpMymsg("");
					fnpMymsg("");
					fnpMymsg("");
					fnpMymsg("");
					fnpMymsg("");
					fnpMymsg("");

					throw is;
				}
			} catch (Throwable t) {

				// fnpDesireScreenshot("NotPresent" + XpathKey);
				throw new Exception(t.getMessage() + "   So,Unable to find element with name [" + XpathKey
						+ "] ,plz see screenshot [NotPresent" + XpathKey + SShots + "]");

			}

			finally {
				driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")),
						TimeUnit.SECONDS);
			}
		}

	}

	public static List<WebElement> fnpGetORObject_list_NOR(String Xpath, int maxTimeInSeconds,
			long maxTimeInMilliSeconds) throws Throwable {

		int retries = 0;
		while (true) {
			try {

				/*
				 * if ((Xpath.trim().toLowerCase().contains("error")) ||
				 * (Xpath.trim().toLowerCase().contains("ProgressImageIcon"))
				 * || (Xpath.trim().toLowerCase().contains("BlockedUIScreen"))) {
				 */

				if ((Xpath.trim().toLowerCase().contains("error"))
						|| (Xpath.trim().toLowerCase().contains("progressimageicon"))
						|| (Xpath.trim().toLowerCase().contains("blockeduiscreen"))) {

					driver.manage().timeouts().implicitlyWait(1, TimeUnit.MILLISECONDS);

				} else {
					driver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS);

				}

				return driver.findElements(By.xpath(Xpath));

			} catch (org.openqa.selenium.StaleElementReferenceException s) {
				if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					// if (retries < 3) {
					Thread.sleep(1000);
					retries++;
					fnpMymsg(retries
							+ "In staleElementException catch block of fnpGetORObject_list 3 arguments function for "
							+ Xpath);
					// continue;
				} else {
					throw s;
				}
			}

			catch (org.openqa.selenium.InvalidSelectorException is) {

				if (retries < 10) {
					Thread.sleep(2000);

					fnpMymsg(" @  ---In InvalidSelectorException in ip addresss---" + fnpGetIPAddress()
							+ " machine  for retries --" + retries);

					retries++;
					fnpMymsg(retries
							+ "In InvalidSelectorException catch block of fnpGetORObject_list 3 arguments  function for "
							+ Xpath);
					// continue;
				} else {

					fnpMymsg("");
					fnpMymsg("");
					fnpMymsg("");
					fnpMymsg("");
					fnpMymsg("");
					fnpMymsg("@   ---********Exception thrown in " + fnpGetIPAddress()
							+ " machine************************************************");

					fnpMymsg(is.getMessage());
					fnpMymsg("");
					fnpMymsg("");
					fnpMymsg("");
					fnpMymsg("");
					fnpMymsg("@   ---********************************************************");
					String PageSourceText = driver.getPageSource().toLowerCase();
					fnpMymsg("@   ---********************************************************");

					// fnpMymsg(fnpFormatReplaceSpecailCharacters(PageSourceText));
					fnpMymsg(
							"@   ---*********COMMENTING PAGE SOURCE For Time being before release on 4th March********************");

					fnpMymsg("@   ---********************************************************");
					fnpMymsg("");
					fnpMymsg("");
					fnpMymsg("");
					fnpMymsg("");
					fnpMymsg("");
					fnpMymsg("");

					throw is;
				}
			} catch (Throwable t) {

				// fnpDesireScreenshot("NotPresent" + Xpath);
				throw new Exception(t.getMessage() + "   So,Unable to find element with name [" + Xpath
						+ "] ,plz see screenshot [NotPresent" + Xpath + SShots + "]");

			}

			finally {
				driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")),
						TimeUnit.SECONDS);
			}
		}

	}

	public static List<WebElement> fnpGetORObject_list_NOR(String Xpath) throws Throwable {

		int retries = 0;
		while (true) {
			try {

				/*
				 * if ((Xpath.trim().toLowerCase().contains("error")) ||
				 * (Xpath.trim().toLowerCase().contains("ProgressImageIcon"))
				 * || (Xpath.trim().toLowerCase().contains("BlockedUIScreen"))) {
				 */

				if ((Xpath.trim().toLowerCase().contains("error"))
						|| (Xpath.trim().toLowerCase().contains("progressimageicon"))
						|| (Xpath.trim().toLowerCase().contains("blockeduiscreen"))) {

					driver.manage().timeouts().implicitlyWait(1, TimeUnit.MILLISECONDS);

				} else {
					driver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS);

				}

				return driver.findElements(By.xpath(Xpath));

			} catch (org.openqa.selenium.StaleElementReferenceException s) {
				if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					// if (retries < 3) {
					Thread.sleep(1000);
					retries++;
					fnpMymsg(retries
							+ "In staleElementException catch block of fnpGetORObject_list 3 arguments function for "
							+ Xpath);
					// continue;
				} else {
					throw s;
				}
			}

			catch (org.openqa.selenium.InvalidSelectorException is) {

				if (retries < 10) {
					Thread.sleep(2000);

					fnpMymsg(" @  ---In InvalidSelectorException in ip addresss---" + fnpGetIPAddress()
							+ " machine  for retries --" + retries);

					retries++;
					fnpMymsg(retries
							+ "In InvalidSelectorException catch block of fnpGetORObject_list 3 arguments  function for "
							+ Xpath);
					// continue;
				} else {

					fnpMymsg("");
					fnpMymsg("");
					fnpMymsg("");
					fnpMymsg("");
					fnpMymsg("");
					fnpMymsg("@   ---********Exception thrown in " + fnpGetIPAddress()
							+ " machine************************************************");

					fnpMymsg(is.getMessage());
					fnpMymsg("");
					fnpMymsg("");
					fnpMymsg("");
					fnpMymsg("");
					fnpMymsg("@   ---********************************************************");
					String PageSourceText = driver.getPageSource().toLowerCase();
					fnpMymsg("@   ---********************************************************");

					// fnpMymsg(fnpFormatReplaceSpecailCharacters(PageSourceText));
					fnpMymsg(
							"@   ---*********COMMENTING PAGE SOURCE For Time being before release on 4th March********************");

					fnpMymsg("@   ---********************************************************");
					fnpMymsg("");
					fnpMymsg("");
					fnpMymsg("");
					fnpMymsg("");
					fnpMymsg("");
					fnpMymsg("");

					throw is;
				}
			} catch (Throwable t) {

				// fnpDesireScreenshot("NotPresent" + Xpath);
				throw new Exception(t.getMessage() + "   So,Unable to find element with name [" + Xpath
						+ "] ,plz see screenshot [NotPresent" + Xpath + SShots + "]");

			}

			finally {
				driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")),
						TimeUnit.SECONDS);
			}
		}

	}

	/***********
	 * Function to find and return the object using Xpath which are present in
	 * OR
	 *************/
	public static WebElement fnpGetORObjectX_WhenMultipleObjectsHavingSameXpath(String XpathKey) throws Exception {
		int retries = 0;
		WebElement requiredObj = null;
		try {

			if (fnpGetORObject_list(XpathKey, 60).size() > 0) {

				List<WebElement> objList = fnpGetORObject_list(XpathKey, 2); // chhavi
				for (int i = 0; i < objList.size(); i++) {
					if ((objList.get(i).isDisplayed()) & (!((objList.get(i).getText()).equalsIgnoreCase("")))) {

						requiredObj = objList.get(i);
						break;
					}
				}

			} else {
				throw new Exception("Element is not Visible having Xpath  [ " + XpathKey + " ]");
			}

		} catch (org.openqa.selenium.StaleElementReferenceException s) {
			if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
				Thread.sleep(1000);
				retries++;
				fnpMymsg(retries
						+ "In staleElementException catch block of fnpGetORObjectX_WhenMultipleObjectsHavingSameXpath function for "
						+ XpathKey);
				// continue;
			}
		}

		catch (org.openqa.selenium.InvalidSelectorException is) {
			if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
				Thread.sleep(1000);
				retries++;
				fnpMymsg(retries
						+ "In org.openqa.selenium.InvalidSelectorException catch block of fnpGetORObjectX_WhenMultipleObjectsHavingSameXpath function for "
						+ XpathKey);
				// continue;
			} else {
				throw is;
			}
		}

		catch (Throwable t) {
			// fnpDesireScreenshot("NotPresent" + XpathKey);
			throw new Exception(t.getMessage() + "   So,Unable to find element with name [" + XpathKey
					+ "] ,plz see screenshot [NotPresent" + XpathKey + SShots + "]");
		}

		return requiredObj;
	}

	/***********
	 * Function to find and return the object using Xpath which are present in
	 * OR
	 *************/
	public static WebElement fnpGetORObjectX_WhenMultipleObjectsHavingSameXpath_NOR(String Xpath) throws Exception {
		int retries = 0;
		WebElement requiredObj = null;
		try {

			if (driver.findElements(By.xpath(Xpath)).size() > 0) {

				List<WebElement> objList = fnpGetORObject_list_NOR(Xpath, 2);

				for (int i = 0; i < objList.size(); i++) {
					if ((objList.get(i).isDisplayed()) & (!((objList.get(i).getText()).equalsIgnoreCase("")))) {

						requiredObj = objList.get(i);
						break;
					}
				}

			} else {
				throw new Exception("Element is not Visible having Xpath  [ " + Xpath + " ]");
			}

		} catch (org.openqa.selenium.StaleElementReferenceException s) {
			if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
				Thread.sleep(1000);
				retries++;
				fnpMymsg(retries
						+ "In staleElementException catch block of fnpGetORObjectX_WhenMultipleObjectsHavingSameXpath function for "
						+ Xpath);
				// continue;
			}
		}

		catch (org.openqa.selenium.InvalidSelectorException is) {
			if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
				Thread.sleep(1000);
				retries++;
				fnpMymsg(retries
						+ "In org.openqa.selenium.InvalidSelectorException catch block of fnpGetORObjectX_WhenMultipleObjectsHavingSameXpath function for "
						+ Xpath);
				// continue;
			} else {
				throw is;
			}
		}

		catch (Throwable t) {
			// fnpDesireScreenshot("NotPresent" + Xpath); //commented on
			// 27-09-2017 because image is not png as xpath have some non
			// // acceptable value
			// fnpDesireScreenshot("NotPresent");
			throw new Exception(t.getMessage() + "   So,Unable to find element with name [" + Xpath
					+ "] ,plz see screenshot [NotPresent" + Xpath + SShots + "]");

		}

		return requiredObj;
	}

	/***********
	 * Function to find and return the object using Xpath which are present in
	 * OR
	 *************/
	public static WebElement fnpGetORObjectX_WhenMultipleObjectsHavingSameXpath_NOR_old(String Xpath,
			String nameOfField) throws Exception {
		int retries = 0;
		WebElement requiredObj = null;
		try {

			if (driver.findElements(By.xpath(Xpath)).size() > 0) {

				List<WebElement> objList = fnpGetORObject_list_NOR(Xpath, 2);

				for (int i = 0; i < objList.size(); i++) {
					if ((objList.get(i).isDisplayed()) & (!((objList.get(i).getText()).equalsIgnoreCase("")))) {

						requiredObj = objList.get(i);
						break;
					}
				}

			} else {
				throw new Exception("Element is not Visible having Xpath  [ " + Xpath + " ]");
			}

		} catch (org.openqa.selenium.StaleElementReferenceException s) {
			if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
				Thread.sleep(1000);
				retries++;
				fnpMymsg(retries
						+ "In staleElementException catch block of fnpGetORObjectX_WhenMultipleObjectsHavingSameXpath function for "
						+ Xpath);
				// continue;
			}
		}

		catch (org.openqa.selenium.InvalidSelectorException is) {
			if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
				Thread.sleep(1000);
				retries++;
				fnpMymsg(retries
						+ "In org.openqa.selenium.InvalidSelectorException catch block of fnpGetORObjectX_WhenMultipleObjectsHavingSameXpath function for "
						+ Xpath);
				// continue;
			} else {
				throw is;
			}
		}

		catch (Throwable t) {
			// fnpDesireScreenshot("NotPresent" + nameOfField);
			throw new Exception(t.getMessage() + "   So,Unable to find element with name [" + nameOfField
					+ "] ,plz see screenshot [NotPresent" + nameOfField + SShots + "]");
		}

		return requiredObj;
	}

	/***********
	 * Function to find and return the object using Xpath which are present in
	 * OR
	 *************/
	public static WebElement fnpGetORObjectX_WhenMultipleObjectsHavingSameXpath_NOR_immediately(String Xpath,
			String nameOfField) throws Exception {
		int retries = 0;
		WebElement requiredObj = null;
		try {

			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			if (driver.findElements(By.xpath(Xpath)).size() > 0) {

				List<WebElement> objList = fnpGetORObject_list_NOR(Xpath, 2);

				for (int i = 0; i < objList.size(); i++) {
					if ((objList.get(i).isDisplayed()) & (!((objList.get(i).getText()).equalsIgnoreCase("")))) {

						requiredObj = objList.get(i);
						break;
					}
				}

			} else {
				throw new Exception("Element is not Visible having Xpath  [ " + Xpath + " ]");
			}

		} catch (org.openqa.selenium.StaleElementReferenceException s) {
			if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
				Thread.sleep(1000);
				retries++;
				fnpMymsg(retries
						+ "In staleElementException catch block of fnpGetORObjectX_WhenMultipleObjectsHavingSameXpath function for "
						+ Xpath);
				// continue;
			}
		}

		catch (org.openqa.selenium.InvalidSelectorException is) {
			if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
				Thread.sleep(1000);
				retries++;
				fnpMymsg(retries
						+ "In org.openqa.selenium.InvalidSelectorException catch block of fnpGetORObjectX_WhenMultipleObjectsHavingSameXpath function for "
						+ Xpath);
				// continue;
			} else {
				throw is;
			}
		}

		catch (Throwable t) {

			throw new Exception(t.getMessage() + "   So,Unable to find element with name [" + nameOfField
					+ "] ,plz see screenshot [NotPresent" + nameOfField + SShots + "]");
		}

		finally {
			driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")),
					TimeUnit.SECONDS);

			return requiredObj;
		}

	}

	/***********
	 * Function to find and return the object using LinkText which are present
	 * in OR
	 *************/
	public static WebElement fnpGetORObjectX_usingLinkText(String ORlinkName) throws Exception {
		int retries = 0;

		while (true) {
			try {
				if (OR.getProperty(ORlinkName).contains("~")) {
					return fnpReturnWebElementAfterExtractLocatorandValue(OR.getProperty(ORlinkName)).get(0);
				} else {
					return driver.findElement(By.linkText(OR.getProperty(ORlinkName)));
				}

			} catch (org.openqa.selenium.StaleElementReferenceException s) {
				if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					retries++;
					fnpMymsg(retries
							+ "In staleElementException catch block of fnpGetORObjectX_usingLinkText function for "
							+ ORlinkName);
					// continue;
				} else {
					throw s;
				}
			}

			catch (Throwable t) {
				// fnpDesireScreenshot("NotPresent" + ORlinkName);
				throw new Exception("Unable to find element [" + ORlinkName + "] ,plz see screenshot [NotPresent"
						+ ORlinkName + SShots + "]");
			}
		}

	}

	/***********
	 * Function to find and return the object using Xpath which are present in
	 * OR
	 *************/
	public static String fnpGetText_OR(String XpathKey) throws Exception {
		int retries = 0;
		int maxTry = 1;

		while (true) {
			try {

				return fnpGetText_OR_EvenMultipleObjectsHavingSameXpath(XpathKey);
			} catch (org.openqa.selenium.StaleElementReferenceException s) {
				if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					retries++;
					fnpMymsg(
							retries + "In staleElementException catch block of fnpGetText_OR function for " + XpathKey);
					// continue;
				} else {
					throw s;
				}
			}

			catch (org.openqa.selenium.InvalidSelectorException is) {
				// if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
				if (retries < maxTry) {
					retries++;
					fnpMymsg(retries + "In InvalidSelectorException catch block of fnpGetText_NOR function for "
							+ XpathKey);
					// continue;
				} else {
					throw is;
				}
			}

			catch (Throwable t) {
				fnpDesireScreenshot("UnableToFetchUsingGetText" + XpathKey);
				throw new Exception(t.getMessage() + "   So,Unable to fetch getText for  element [" + XpathKey
						+ "] ,plz see screenshot [UnableToFetchUsingGetText" + XpathKey
						+ SShots + "]");

			}
		}

	}

	/**************
	 * Work Even when multiple Elements having same xpath, will return the text
	 * from one having text present first
	 *************/
	public static String fnpGetText_OR_EvenMultipleObjectsHavingSameXpath(String XpathKey) throws Exception {
		WebElement requiredObj = null;
		String returnText = "";

		driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")),
				TimeUnit.SECONDS);

		if (fnpGetORObject_list(XpathKey, Integer.parseInt(CONFIG.getProperty("implicitWaitTime"))).size() > 0) {
			List<WebElement> objList = fnpGetORObject_list(XpathKey,
					Integer.parseInt(CONFIG.getProperty("implicitWaitTime")));
			String currentText = "";
			for (int i = 0; i < objList.size(); i++) {
				currentText = fnpReturnText_notCallItDirectly(objList.get(i));
				// fnpMymsg("@ --no. of "+(i+1)+" error class have text ----"+returnText);

				if ((objList.get(i).isDisplayed()) & (!(currentText.equalsIgnoreCase("")))) {
					requiredObj = objList.get(i);
					returnText = returnText + currentText;
					// break;
				}

			}

		} else {

			throw new Exception("Element is not Visible having Xpath  [ " + XpathKey + " ]");
		}

		return returnText.trim();

	}

	/****************
	 * Work Even when multiple Elements having same xpath, will return the all error
	 * messages text after
	 ***************/
	public static String fnpGetText_OR_EvenMultipleObjectsHavingSameXpath_specialForExtractingErrorMsgTextOnlyEvenIfNotVisible(
			String XpathKey) throws Throwable {
		WebElement requiredObj = null;
		String returnText = "";

		if (fnpGetORObject_list(XpathKey, 0, 1).size() > 0) {

			List<WebElement> objList = fnpGetORObject_list(XpathKey, 0, 100);

			fnpMymsg("@  --no. of error class is ----" + objList.size());
			String currentText = "";
			int somecounter = 0;
			for (int i = 0; i < objList.size(); i++) {

				try {
					fnpMymsg("@  --no. of " + (i + 1) + "error class displayed ----" + (objList.get(i).isDisplayed()));
					currentText = fnpReturnText_notCallItDirectly(objList.get(i));
				} catch (org.openqa.selenium.StaleElementReferenceException s) { // This catch block added on 03-05-2018
																					// as sometime getting
																					// staleelementexception
					// nothing to do

					if (somecounter < 2) {
						somecounter++;
						Thread.sleep(300);
						objList = fnpGetORObject_list(XpathKey, 0, 100);
						fnpMymsg("@  --no. of error class after staleElement or screen refreshed is ----"
								+ objList.size());
						i = -1;
					}
				}

				/*
				 * try{
				 * currentText = fnpReturnText_notCallItDirectly(objList.get(i));
				 * }catch(org.openqa.selenium.StaleElementReferenceException s){
				 * currentText = "";
				 * }
				 */
				fnpMymsg("@  --no. of " + (i + 1) + " error class have text ----" + currentText);

				fnpMymsg("@  --no. of " + (i + 1) + "error class have text ----" + currentText);

				if (!(currentText.equalsIgnoreCase(""))) {
					requiredObj = objList.get(i);
					fnpMymsg("@  -- " + (i + 1) + "error class is our required object.");

					if (!(returnText.contains(currentText))) {
						returnText = returnText + "  " + currentText;
					}
					// returnText=returnText+" "+currentText;
					// break;
				}

			}

		} else {
			/******
			 * This below throw line can be commented only in function using for
			 * error specially i.e.
			 * fnpGetText_OR_EvenMultipleObjectsHavingSameXpath_specialForExtractingErrorMsgTextOnlyEvenIfNotVisible
			 *****/
			// throw new Exception("Element is not Visible having Xpath [ " +
			// XpathKey + " ]");
		}

		return returnText.trim();

	}

	/****************
	 * Work Even when multiple Elements having same xpath, will return the text
	 * from one having text present first
	 ******************/
	public static String fnpGetText_NOR_EvenMultipleObjectsHavingSameXpath(String Xpath) throws Exception {
		WebElement requiredObj = null;
		String returnText = "";
		if (fnpGetORObject_list_NOR(Xpath, Integer.parseInt(CONFIG.getProperty("implicitWaitTime"))).size() > 0) {

			List<WebElement> objList = fnpGetORObject_list_NOR(Xpath,
					Integer.parseInt(CONFIG.getProperty("implicitWaitTime")));

			String currentText = "";
			for (int i = 0; i < objList.size(); i++) {
				currentText = fnpReturnText_notCallItDirectly(objList.get(i));
				if ((objList.get(i).isDisplayed()) & (!(currentText.equalsIgnoreCase("")))) {
					requiredObj = objList.get(i);
					returnText = returnText + "  " + currentText;
					// break;
				}

			}

		} else {
			/******
			 * This below throw line can be commented only in function using for
			 * error specially i.e.
			 * fnpGetText_OR_EvenMultipleObjectsHavingSameXpath_specialForExtractingErrorMsgTextOnlyEvenIfNotVisible
			 *****/
			throw new Exception("Element is not Visible having Xpath  [ " + Xpath + " ]");
		}

		// return requiredObj.getText();
		return returnText.trim();
	}

	/***********
	 * Function to find and return the object using Xpath which are present in
	 * OR
	 *************/
	public static String fnpGetText_WebElement(String webElementName, WebElement wb) throws Exception {
		int retries = 0;

		while (true) {
			try {
				return wb.getText().trim();
			} catch (org.openqa.selenium.StaleElementReferenceException s) {
				if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					retries++;
					fnpMymsg(retries + "In staleElementException catch block of fnpGetText_WebElement function for "
							+ webElementName);
					// continue;
				} else {
					throw s;
				}
			}

			catch (org.openqa.selenium.InvalidSelectorException is) {
				if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					retries++;
					fnpMymsg(retries + "In InvalidSelectorException catch block of fnpGetText_WebElement function for "
							+ webElementName);
					// continue;
				} else {
					throw new Exception(is);
				}
			}

			catch (Throwable t) {
				fnpDesireScreenshot("UnableToFetchUsingGetText" + webElementName);
				throw new Exception(t.getMessage() + "   So,Unable to fetch getText for  element [" + webElementName
						+ "] ,plz see screenshot [UnableToFetchUsingGetText"
						+ webElementName + SShots + "]");

			}
		}

	}

	/***********
	 * Function to find and return the object using Xpath which are present in
	 * OR
	 *************/
	public static String fnpGetAttribute_OR(String XpathKey, String attributeName) throws Exception {
		int retries = 0;

		while (true) {
			try {
				return fnpGetORObjectX(XpathKey).getAttribute(attributeName);
			} catch (org.openqa.selenium.StaleElementReferenceException s) {
				if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					retries++;
					fnpMymsg(
							retries + "In staleElementException catch block of fnpGetText_OR function for " + XpathKey);
					// continue;
				} else {
					throw s;
				}
			}

			catch (Throwable t) {
				fnpDesireScreenshot("UnableToFetchUsingGetText" + XpathKey);
				throw new Exception(t.getMessage() + "   So,Unable to fetch getText for  element [" + XpathKey
						+ "] ,plz see screenshot [UnableToFetchUsingGetText" + XpathKey
						+ SShots + "]");

			}
		}

	}

	/***********
	 * Function to return the text of objects which are not present in OR
	 *************/

	public static String fnpGetText_NOR(String Xpath) throws Exception {
		int retries = 0;

		while (true) {
			try {

				return fnpGetText_NOR_EvenMultipleObjectsHavingSameXpath(Xpath);
			} catch (org.openqa.selenium.StaleElementReferenceException s) {
				if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					retries++;
					fnpMymsg(retries + "In staleElementException catch block of fnpGetText_NOR function for " + Xpath);
					// continue;
				} else {
					throw s;
				}
			}

			catch (org.openqa.selenium.InvalidSelectorException is) {
				if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					retries++;
					fnpMymsg(retries + "In InvalidSelectorException catch block of fnpGetText_NOR function for "
							+ Xpath);
					// continue;
				} else {
					throw new Exception(is);
				}
			}

			catch (Throwable t) {
				fnpDesireScreenshot("UnableToFetchUsingGetText" + Xpath);
				throw new Exception(t.getMessage() + "   So,Unable to fetch getText for  element [" + Xpath
						+ "] ,plz see screenshot [UnableToFetchUsingGetText" + Xpath + SShots
						+ "]");

			}
		}

	}

	/***********
	 * To select the value from prime faces list that contains matching value
	 *************/
	public static void fnpPFList0(String XpathKey, String XpathOptionsKey, String value) throws Throwable {
		try {
			fnpWaitForVisible(XpathKey);
			fnpClick_OR_WithoutWait(XpathKey);
			Thread.sleep(500);
			String listValue = OR.getProperty(XpathOptionsKey) + "/div/ul/li[contains(@data-label,'" + value + "')]";
			fnpWaitTillVisiblityOfElementNClickableInsidePFList(listValue);

			fnpClick_NOR_withoutWait(listValue);
			Thread.sleep(500);

		} catch (Exception e) {
			fnpDesireScreenshot("ValueMissingInList" + value);
			String errorMsg = e.getMessage();
			throw new Exception(errorMsg + "\n\n\n   And/OR  might be Value [" + value
					+ "] is not present in List ,plz see screenshot [ValueMissingInList" + value + SShots + "]");
		}
	}

	/***********
	 * To select the value from prime faces list that contains matching value
	 *************/
	public static void fnpPFList_usingForCaseInsensitive(String XpathKey, String XpathOptionsKey, String value)
			throws Throwable {

		String tagname = null;
		boolean ValueMatched = false;
		boolean found = false;

		/***********
		 * As some options which are selected or need to be selected but not
		 * visible i.e. out of scope and those are not properly selected in IE
		 * only
		 ******/

		fnpWaitForVisible(XpathKey);
		fnpClick_OR_WithoutWait(XpathKey);
		Thread.sleep(1000);
		// String listValue = OR.getProperty(XpathOptionsKey) +
		// "/div/ul/li[contains(@data-label,'" + value + "')]";

		if (fnpCountChildElementPresenceImmediately_NotInOR(fnpGetORObjectX___NOR(OR.getProperty(XpathOptionsKey)),
				"li") > 0) {
			List<WebElement> objectlist5 = fnpGetORObjectX___NOR(OR.getProperty(XpathOptionsKey))
					.findElements(By.tagName("li"));
			tagname = "li";
			fnpMymsg(" li tag is present for this list. ");
			int totalvaluesinDropDown = objectlist5.size();

			fnpMymsg("@  ---going to fetch visible selected value");

			String VisibleselectedOption = driver
					.findElement(
							By.xpath(OR.getProperty(XpathOptionsKey) + "//li[contains(@class,'ui-state-highlight')]"))
					.getText();

			fnpMymsg("@  ---selected value is ---" + VisibleselectedOption);
			int iCurrentPosition = 1;
			String whileloopCurrentValue = "";
			while (true) {
				whileloopCurrentValue = driver
						.findElement(By.xpath(OR.getProperty(XpathOptionsKey) + "//li[" + iCurrentPosition + "]"))
						.getText();

				if ((VisibleselectedOption.toLowerCase().equalsIgnoreCase(whileloopCurrentValue.toLowerCase()))) {
					break;
				}
				iCurrentPosition++;

				if (iCurrentPosition > totalvaluesinDropDown) {
					break;
				}
			}

			fnpMymsg("@  ---iCurrentPosition is ----" + iCurrentPosition);

			int iExpectedPosition = 1;
			whileloopCurrentValue = "";
			while (true) {

				whileloopCurrentValue = driver
						.findElement(By.xpath(OR.getProperty(XpathOptionsKey) + "//li[" + iExpectedPosition + "]"))
						.getText();

				if ((whileloopCurrentValue.toLowerCase().contains(value.toLowerCase()))) {
					break;
				}
				iExpectedPosition++;
				if (iCurrentPosition > totalvaluesinDropDown) {
					break;
				}
			}

			fnpMymsg("@  ---iExpectedPosition is ----" + iExpectedPosition);

			if (iExpectedPosition > iCurrentPosition) {

				fnpMymsg("@  --- Expected position is higher");

				int innerwhilecounter = 0;
				while (iExpectedPosition != (iCurrentPosition + innerwhilecounter)) {
					new Actions(driver).moveToElement(objectlist5.get(iCurrentPosition + innerwhilecounter))
							.sendKeys(Keys.ARROW_DOWN).build().perform();
					Thread.sleep(100);

					fnpMymsg("@  - arrow down ---" + innerwhilecounter);
					innerwhilecounter++;

					if (innerwhilecounter > totalvaluesinDropDown) {
						break;
					}
				}
				fnpGetORObjectX___NOR(OR.getProperty(XpathOptionsKey) + "//li[" + iExpectedPosition + "]").click();
				fnpMymsg("@  - Value clicked successfully");
				found = true;
				Thread.sleep(2000);
			} else {

				fnpMymsg("@  --- Expected position is lower");
				int innerwhilecounter = 0;
				while (iExpectedPosition != (iCurrentPosition - innerwhilecounter)) {
					new Actions(driver).moveToElement(objectlist5.get(iCurrentPosition - innerwhilecounter))
							.sendKeys(Keys.ARROW_UP).build().perform();
					Thread.sleep(100);

					fnpMymsg("@  - arrow UP ---" + innerwhilecounter);
					innerwhilecounter++;
					if (innerwhilecounter > totalvaluesinDropDown) {
						break;
					}
				}
				fnpGetORObjectX___NOR(OR.getProperty(XpathOptionsKey) + "//li[" + iExpectedPosition + "]").click();
				fnpMymsg("@  - Value clicked successfully");
				found = true;
				Thread.sleep(2000);

			}

			if (found == false) {
				msg = "@  - Excel value --'" + value + "'  is not matched with DropDown Value.";
				fnpMymsg(msg);
				throw new Exception(msg);

			}

		} else {
			fnpDesireScreenshot("ValueMissingInList" + value);
			throw new Exception("\n\n\n   And/OR  might be Value [" + value
					+ "] is not present in List ,plz see screenshot [ValueMissingInList" + value + SShots + "]");

		}

	}

	/***********
	 * As some options which are selected or need to be selected but not visible
	 * i.e. out of scope and those are not properly selected in IE only
	 ******/

	/***********
	 * To select the value from prime faces list that contains matching value
	 *************/
	public static void fnpPFList_notUsingThis_usingBelowOneTakenFromGrid(String XpathKey, String XpathOptionsKey,
			String value) throws Throwable {

		value = value.trim();

		String defaultValue = fnpGetORObjectX(XpathKey).getText().trim();

		if ((!defaultValue.equalsIgnoreCase("")) & (defaultValue.contains(value))) {
			fnpMymsg("@  - default value is same as expected, so returning back.");
			return;
		}

		try {
			String temp = value;
			fnpMymsg("Going to select this value ---'" + value + "'  .");
			fnpWaitForVisible(XpathKey);
			fnpWaitTillVisiblityOfElementNClickable_OR(XpathKey);

			fnpClick_OR_WithoutWait(XpathKey);
			Thread.sleep(500);
			String listValue = OR.getProperty(XpathOptionsKey) + "/div/ul/li[contains(@data-label,'" + value + "')]";

			String tagname = null;
			boolean ValueMatched = false;
			boolean found = false;

			try {

				if (fnpCheckElementDisplayed_NOR(listValue, 1)) {
					// nothing to do
				} else {
					fnpMymsg("This value '" + value + "'  is not present as it is -- so going to lower its case");
					String new_value = value.toLowerCase();
					listValue = OR.getProperty(XpathOptionsKey) + "/div/ul/li[contains(@data-label,'" + new_value
							+ "')]";
					if (fnpCheckElementDisplayedImmediately_NOR(listValue)) {
						// nothing to do
						value = new_value;
					} else {
						fnpMymsg("Now again this value '" + new_value
								+ "'  is not present as it is -- so going to make its first digit in uppercase.");

						new_value = fnpReturnAStringWholeFirstCharacterIsInUpperCase(new_value);

						listValue = OR.getProperty(XpathOptionsKey) + "/div/ul/li[contains(@data-label,'" + new_value
								+ "')]";
						if (fnpCheckElementDisplayedImmediately_NOR(listValue)) {
							value = new_value;
						} else {
							fnpMymsg("Now again this value '" + new_value
									+ "'  is not present as it is -- so going to make its all letters in upper case.");
							new_value = value.toUpperCase();
							value = new_value;
							listValue = OR.getProperty(XpathOptionsKey) + "/div/ul/li[contains(@data-label,'"
									+ new_value + "')]";
							if (fnpCheckElementDisplayedImmediately_NOR(listValue)) {
								value = new_value;
							} else {
								fnpMymsg("Now again this value '" + new_value
										+ "'  is not present as it is -- so going to check once again (once more time)  the initial value as it is i.e. '"
										+ temp + "'  .");
								value = temp; // to check once again (once more
												// time) the initial value as it
												// is
							}
						}

					}

				}

				listValue = OR.getProperty(XpathOptionsKey) + "/div/ul/li[contains(@data-label,'" + value + "')]";
				fnpMymsg("@  - going to click expected option value --" + value);

				listValue = OR.getProperty(XpathOptionsKey) + "/div/ul/li[contains(@data-label,'" + value + "')]";

				fnpGetORObjectX_WhenMultipleObjectsHavingSameXpath_NOR_old(listValue, value).click();// new
																										// change
																										// on
																										// 31-08-16

				found = true;
				fnpMymsg("@  -  clicked expected option value  in try block.");

				Thread.sleep(1000);

				try {
					if (fnpCheckElementEnabledImmediately("FooterElement_id")) {
						fnpMymsg("@  - Footer element is enabled.");
						fnpMouseHover("FooterElement_id");
						fnpGetORObjectX("FooterElement_id").click();
					}
				} catch (Throwable t) {
					// nothing to do
				}

				fnpMymsg("@  -  Now going to fetch the selected value.");

				String FinalSelectedValue = fnpGetText_OR(XpathKey).trim();
				fnpMymsg("@  -  Fetched/Selected value is ---" + FinalSelectedValue);
				String value2 = StringUtils.normalizeSpace(value);

				if (!(((FinalSelectedValue.contains(value))) || ((FinalSelectedValue.contains(value2))))) {
					msg = "@  - '" + value + "'  is NOT selected properly in  DropDown as expected is" + "--'" + value
							+ "' and actual is --'" + FinalSelectedValue + "' .";
					fnpMymsg(msg);
					throw new Exception(msg);
				} else {
					fnpMymsg("@  - '" + value + "'  is  selected properly in  DropDown as expected is" + "--'" + value
							+ "' and actual is --'" + FinalSelectedValue + "' .");
				}

			} catch (Throwable t) {

				listValue = OR.getProperty(XpathOptionsKey) + "/div/ul/li[contains(@data-label,'" + value + "')]";

				fnpMymsg(
						"@  - fnpPFList is failed due to some error but try again in catch block. Previous error caught was ---"
								+ t.getMessage());

				fnpMymsg("@  - So processing further in catch block again. ");

				// To close the expanded drop down first then opened again
				if (fnpCheckElementDisplayedImmediately_NOR(XpathOptionsKey)) {
					fnpMymsg("@  - list values are displayed already, so first closing it to open again it");
					Thread.sleep(1000);
					fnpClick_OR_WithoutWait(XpathKey);
					Thread.sleep(1000);

					fnpClick_OR_WithoutWait(XpathKey);
				} else {
					fnpMymsg("List values are not opened/displayed already, so going to opened it..");

					Thread.sleep(1000);
					fnpClick_OR_WithoutWait(XpathKey);
					Thread.sleep(1000);
				}

				fnpMymsg("@  ---in catch  block of fnpPFList");

				if (fnpCountChildElementPresenceImmediately_NotInOR(
						fnpGetORObjectX___NOR(OR.getProperty(XpathOptionsKey)), "li") > 0) {
					List<WebElement> objectlist5 = fnpGetORObjectX___NOR(OR.getProperty(XpathOptionsKey))
							.findElements(By.tagName("li"));
					fnpMymsg("@  ---total list elements are--" + objectlist5.size());
					tagname = "li";
					fnpMymsg(" li tag is present for this list. ");
					int totalvaluesinDropDown = objectlist5.size();

					fnpMymsg("@  ---going to fetch visible selected value");

					String VisibleselectedOption = driver
							.findElement(By.xpath(
									OR.getProperty(XpathOptionsKey) + "//li[contains(@class,'ui-state-highlight')]"))
							.getText();

					fnpMymsg("@  ---selected value is ---" + VisibleselectedOption);
					int iCurrentPositionIndex = 0;
					String whileloopCurrentValue = "";
					while (true) {

						whileloopCurrentValue = driver
								.findElement(By.xpath(
										OR.getProperty(XpathOptionsKey) + "//li[" + (iCurrentPositionIndex + 1) + "]"))
								.getText();

						if ((VisibleselectedOption.equalsIgnoreCase(whileloopCurrentValue))) {
							break;
						}
						iCurrentPositionIndex++;

						if ((iCurrentPositionIndex + 1) > totalvaluesinDropDown) {
							break;
						}
					}

					fnpMymsg("@  ---iCurrentPosition is ----" + (iCurrentPositionIndex + 1));

					int iExpectedPositionIndex = 0;
					whileloopCurrentValue = "";
					while (true) {

						whileloopCurrentValue = objectlist5.get(iExpectedPositionIndex).getText();

						if ((value.equalsIgnoreCase(whileloopCurrentValue))) {
							break;
						}
						iExpectedPositionIndex++;
						if ((iCurrentPositionIndex + 1) > totalvaluesinDropDown) {
							break;
						}
					}

					fnpMymsg("@  ---iExpectedPosition is ----" + (iExpectedPositionIndex + 1));

					if (iExpectedPositionIndex > (iCurrentPositionIndex + 1)) {

						fnpMymsg("@  --- Expected position is higher");

						int innerwhilecounter = 0;
						while (iExpectedPositionIndex != (iCurrentPositionIndex + innerwhilecounter + 2)) {

							new Actions(driver)
									.moveToElement(objectlist5.get(iCurrentPositionIndex + innerwhilecounter - 2))
									.build().perform();
							new Actions(driver)
									.moveToElement(objectlist5.get(iCurrentPositionIndex + innerwhilecounter - 2))
									.sendKeys(Keys.ARROW_DOWN).build().perform();

							fnpMymsg("@  -iCurrentPosition value is --" + (iCurrentPositionIndex + 1));
							fnpMymsg("@  -innerwhilecounter value is --" + innerwhilecounter);

							int sum = iCurrentPositionIndex + innerwhilecounter;
							String pp = driver
									.findElement(By.xpath(OR.getProperty(XpathOptionsKey) + "//li[" + sum + "]"))
									.getText();
							fnpMymsg("@  -pp value is ---" + pp);

							Thread.sleep(100);

							fnpMymsg("@  - arrow down ---" + innerwhilecounter);
							innerwhilecounter++;

						}
						fnpMymsg("@  - iExpectedPosition ---" + (iExpectedPositionIndex + 1));
						new Actions(driver).moveToElement(objectlist5.get((iExpectedPositionIndex))).build().perform();
						fnpGetORObjectX___NOR(
								OR.getProperty(XpathOptionsKey) + "//li[" + (iExpectedPositionIndex + 1) + "]").click();

						fnpMymsg("@  - Value clicked successfully");
						found = true;
						Thread.sleep(2000);
					} else {

						fnpMymsg("@  --- Expected position is lower");
						int innerwhilecounter = 0;
						while (iExpectedPositionIndex != (iCurrentPositionIndex - innerwhilecounter)) {

							new Actions(driver)
									.moveToElement(objectlist5.get(iCurrentPositionIndex - innerwhilecounter + 1))
									.sendKeys(Keys.ARROW_UP).build().perform();
							Thread.sleep(100);

							fnpMymsg("@  - arrow UP ---" + innerwhilecounter);
							innerwhilecounter++;

						}
						fnpGetORObjectX___NOR(
								OR.getProperty(XpathOptionsKey) + "//li[" + (iExpectedPositionIndex + 1) + "]").click();

						fnpMymsg("@  - Value clicked successfully");
						found = true;
						Thread.sleep(2000);

					}

					if (found == false) {
						msg = "@  - Excel value --'" + value + "'  is not matched with DropDown Value.";
						fnpMymsg(msg);
						throw new Exception(msg);

					}

					try {
						if (fnpCheckElementEnabledImmediately("FooterElement")) {
							fnpMymsg("@  - Footer element is enabled.");
							fnpMouseHover("FooterElement");
							fnpGetORObjectX("FooterElement").click();
						}
					} catch (Throwable tt) {
						// nothing to do
					}

					String FinalSelectedValue = fnpGetText_OR(XpathKey).trim();

					String value2 = StringUtils.normalizeSpace(value);

					if (!(((FinalSelectedValue.contains(value))) || ((FinalSelectedValue.contains(value2))))) {
						msg = "@  - '" + value + "'  is NOT selected properly in  DropDown as expected is" + "--'"
								+ value + "' and actual is --'" + FinalSelectedValue + "' .";
						fnpMymsg(msg);
						throw new Exception(msg);
					} else {
						fnpMymsg("@  - '" + value + "'  is  selected properly in  DropDown as expected is" + "--'"
								+ value + "' and actual is --'" + FinalSelectedValue + "' .");
					}

				}
			}

		} catch (Throwable t) {
			fnpDesireScreenshot("ValueMissingInList" + value);
			String errorMsg = t.getMessage();
			throw new Exception(errorMsg + "\n\n\n   And/OR  might be Value [" + value
					+ "] is not present in List ,plz see screenshot [ValueMissingInList" + value + SShots + "]");
		}
	}

	/***********
	 * To select the value from prime faces list that contains matching value
	 *************/
	// using this taken from grid
	public static void fnpPFList(String XpathKey, String XpathOptionsKey, String value) throws Throwable {

		value = value.trim();
		String originalValue = value;

		String defaultValue = fnpGetText_OR(XpathKey);
		if ((!defaultValue.equalsIgnoreCase("")) & (defaultValue.contains(value))) {
			fnpMymsg("@ - default value is same as expected, so returning back.");
			return;
		}

		String tagname = null;
		boolean ValueMatched = false;
		boolean found = false;
		boolean selectedProperly = false;

		int intWhileCounter = 0;
		while (true) {
			intWhileCounter++;
			try {

				tagname = null;
				ValueMatched = false;
				found = false;

				String temp = originalValue;
				value = originalValue;
				fnpMymsg("Going to select this value ---'" + value + "'  .");
				fnpWaitForVisible(XpathKey);
				fnpWaitTillVisiblityOfElementNClickable_OR(XpathKey);

				if (!(fnpCheckElementDisplayedImmediately(XpathOptionsKey))) {
					fnpMymsg("Here assumption is that list is not expanded.");
					fnpClick_OR_WithoutWait(XpathKey);
				} else {
					// This will work when try again in 2nd chance, first close
					// already opened one and then click again to expand it.
					fnpMymsg("Here assumption is that list is already expanded so first close and then open it again.");
					fnpClick_OR_WithoutWait(XpathKey);
					Thread.sleep(1000);
					fnpClick_OR_WithoutWait(XpathKey);
				}

				Thread.sleep(500);
				String listValue = OR.getProperty(XpathOptionsKey) + "/div/ul/li[contains(@data-label,'" + value
						+ "')]";

				if (fnpCheckElementDisplayed_NOR(listValue, 1)) {
					// nothing to do
				} else {
					fnpMymsg("This value '" + value + "'  is not present as it is -- so going to lower its case");
					String new_value = value.toLowerCase();
					listValue = OR.getProperty(XpathOptionsKey) + "/div/ul/li[contains(@data-label,'" + new_value
							+ "')]";
					if (fnpCheckElementDisplayedImmediately_NOR(listValue)) {
						// nothing to do
						value = new_value;
					} else {
						fnpMymsg("Now again this value '" + new_value
								+ "'  is not present as it is -- so going to make its first digit in uppercase.");

						new_value = fnpReturnAStringWholeFirstCharacterIsInUpperCase(new_value);
						value = new_value;
						listValue = OR.getProperty(XpathOptionsKey) + "/div/ul/li[contains(@data-label,'" + new_value
								+ "')]";
						if (fnpCheckElementDisplayedImmediately_NOR(listValue)) {
							value = new_value;
						} else {
							fnpMymsg("Now again this value '" + new_value
									+ "'  is not present as it is -- so going to make its all letters in upper case.");
							new_value = value.toUpperCase();
							listValue = OR.getProperty(XpathOptionsKey) + "/div/ul/li[contains(@data-label,'"
									+ new_value + "')]";
							if (fnpCheckElementDisplayedImmediately_NOR(listValue)) {
								value = new_value;
							} else {
								fnpMymsg("Now again this value '" + new_value
										+ "'  is not present as it is -- so going to check once again (once more time)  the initial value as it is i.e. '"
										+ temp + "'  .");
								value = temp; // to check once again (once more
												// time) the initial value as it
												// is
							}
						}

					}

				}

				listValue = OR.getProperty(XpathOptionsKey) + "/div/ul/li[contains(@data-label,'" + value + "')]";
				fnpMymsg("@ - going to click expected option value --" + value);

				listValue = OR.getProperty(XpathOptionsKey) + "/div/ul/li[contains(@data-label,'" + value + "')]";

				fnpGetORObjectX_WhenMultipleObjectsHavingSameXpath_NOR_immediately(listValue, value).click();// new
																												// change
																												// on
																												// 11-09-17

				found = true;
				fnpMymsg("@ -  clicked expected option value  in try block.");

				Thread.sleep(1000);

				fnpWorkAroundToClickbottomFooter();

				fnpMymsg("@ -  Now going to fetch the selected value.");
				String FinalSelectedValue = fnpGetText_OR(XpathKey).trim();
				fnpMymsg("@ -  Fetched/Selected value is ---" + FinalSelectedValue);
				String value2 = StringUtils.normalizeSpace(value);

				if (!(((FinalSelectedValue.contains(value))) || ((FinalSelectedValue.contains(value2))))) {
					msg = "@ - '" + value + "'  is NOT selected properly in  DropDown as expected is" + "--'" + value
							+ "' and actual is --'" + FinalSelectedValue + "' .";
					fnpMymsg(msg);
					throw new Exception(msg);
				} else {
					fnpMymsg("@ - '" + value + "'  is  selected properly in  DropDown as expected is" + "--'" + value
							+ "' and actual is --'" + FinalSelectedValue + "' .");
					selectedProperly = true;
					break;
				}

			} catch (Throwable t) {
				fnpMymsg(
						"@ - fnpPFList is failed due to some error but try again in catch block. Previous error caught was ---"
								+ t.getMessage());
				Thread.sleep(2000);
				if (intWhileCounter > 3) {
					fnpMymsg("@ - NOT try again and gong to exit after fnpPFList is failed for chance ----"
							+ intWhileCounter);
					break;
				} else {
					// again in while loop
					fnpMymsg("@ - try again after fnpPFList is failed for chance ----" + intWhileCounter);
				}

			}

		}

		if (!selectedProperly) {

			throw new Exception("\n\n\n   OR  might be Value [" + value
					+ "] is not present in List ,plz see screenshot [ValueMissingInList" + value + SShots + "]");
		} else {
			// nothing for now...
		}

	}

	/***********
	 * To select the value from prime faces list that contains matching value
	 *************/
	// using this taken from grid
	public static void fnpPFList_NFS_StandardVersionType(String triangleXpath, String XpathKey, String XpathOptionsKey,
			String value) throws Throwable {

		value = value.trim();
		String originalValue = value;

		// String defaultValue = fnpGetText_OR(XpathKey);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		String defaultValue = (String) js
				.executeScript("return document.getElementById('mainForm:tabView:aistdvrsndd_hinput').value;");
		System.out.println("pradeep hidden value is --" + defaultValue);

		if ((!defaultValue.equalsIgnoreCase("")) & (defaultValue.contains(value))) {
			fnpMymsg("@ - default value is same as expected, so returning back.");
			return;
		}

		String tagname = null;
		boolean ValueMatched = false;
		boolean found = false;
		boolean selectedProperly = false;

		int intWhileCounter = 0;
		while (true) {
			intWhileCounter++;
			try {

				tagname = null;
				ValueMatched = false;
				found = false;

				String temp = originalValue;
				value = originalValue;
				fnpMymsg("Going to select this value ---'" + value + "'  .");
				// fnpWaitForVisible(XpathKey);
				// fnpWaitTillVisiblityOfElementNClickable_OR(XpathKey);

				/*
				 * if (!(fnpCheckElementDisplayedImmediately(XpathOptionsKey))) {
				 * fnpMymsg("Here assumption is that list is not expanded.");
				 * fnpClick_OR_WithoutWait(triangleXpath);
				 * } else {
				 * // This will work when try again in 2nd chance, first close
				 * // already opened one and then click again to expand it.
				 * fnpMymsg("Here assumption is that list is already expanded so first close and then open it again."
				 * );
				 * fnpClick_OR_WithoutWait(triangleXpath);
				 * Thread.sleep(1000);
				 * fnpClick_OR_WithoutWait(triangleXpath);
				 * }
				 * 
				 */

				WebElement wb = fnpGetORObjectX("NFS_StandardVersionPFList_SuggestionBox");
				wb.clear();
				wb.sendKeys(value.substring(0, (value.length() - 5)));
				Thread.sleep(5000);

				Thread.sleep(500);
				// String listValue = OR.getProperty(XpathOptionsKey) +
				// "/div/ul/li[contains(@data-label,'" + value + "')]";
				String listValue = OR.getProperty(XpathOptionsKey) + "/ul/li[contains(@data-item-label,'" + value
						+ "')]";

				// if (fnpCheckElementDisplayed_NOR(listValue, 1)) {
				if (fnpCheckElementDisplayed_NOR(listValue, 60 * 2)) {
					// nothing to do
				} else {
					fnpMymsg("This value '" + value + "'  is not present as it is -- so going to lower its case");
					String new_value = value.toLowerCase();
					// listValue = OR.getProperty(XpathOptionsKey) +
					// "/div/ul/li[contains(@data-label,'" + new_value + "')]";
					listValue = OR.getProperty(XpathOptionsKey) + "/ul/li[contains(@data-item-label,'" + new_value
							+ "')]";
					if (fnpCheckElementDisplayedImmediately_NOR(listValue)) {
						// nothing to do
						value = new_value;
					} else {
						fnpMymsg("Now again this value '" + new_value
								+ "'  is not present as it is -- so going to make its first digit in uppercase.");

						new_value = fnpReturnAStringWholeFirstCharacterIsInUpperCase(new_value);
						value = new_value;
						// listValue = OR.getProperty(XpathOptionsKey) +
						// "/div/ul/li[contains(@data-label,'" + new_value + "')]";
						listValue = OR.getProperty(XpathOptionsKey) + "/ul/li[contains(@data-item-label,'" + new_value
								+ "')]";
						if (fnpCheckElementDisplayedImmediately_NOR(listValue)) {
							value = new_value;
						} else {
							fnpMymsg("Now again this value '" + new_value
									+ "'  is not present as it is -- so going to make its all letters in upper case.");
							new_value = value.toUpperCase();
							// listValue = OR.getProperty(XpathOptionsKey) +
							// "/div/ul/li[contains(@data-label,'" + new_value + "')]";
							listValue = OR.getProperty(XpathOptionsKey) + "/ul/li[contains(@data-item-label,'"
									+ new_value + "')]";
							if (fnpCheckElementDisplayedImmediately_NOR(listValue)) {
								value = new_value;
							} else {
								fnpMymsg("Now again this value '" + new_value
										+ "'  is not present as it is -- so going to check once again (once more time)  the initial value as it is i.e. '"
										+ temp + "'  .");
								value = temp; // to check once again (once more
												// time) the initial value as it
												// is
							}
						}

					}

				}

				// listValue = OR.getProperty(XpathOptionsKey) +
				// "/div/ul/li[contains(@data-label,'" + value + "')]";
				listValue = OR.getProperty(XpathOptionsKey) + "/ul/li[contains(@data-item-label,'" + value + "')]";
				fnpMymsg("@ - going to click expected option value --" + value);

				// listValue = OR.getProperty(XpathOptionsKey) +
				// "/div/ul/li[contains(@data-label,'" + value + "')]";
				listValue = OR.getProperty(XpathOptionsKey) + "/ul/li[contains(@data-item-label,'" + value + "')]";

				fnpGetORObjectX_WhenMultipleObjectsHavingSameXpath_NOR_immediately(listValue, value).click();// new
																												// change
																												// on
																												// 11-09-17

				found = true;
				fnpMymsg("@ -  clicked expected option value  in try block.");

				Thread.sleep(1000);

				fnpWorkAroundToClickbottomFooter();

				fnpMymsg("@ -  Now going to fetch the selected value.");
				// String FinalSelectedValue = fnpGetText_OR(XpathKey).trim();
				String FinalSelectedValue = (String) js
						.executeScript("return document.getElementById('mainForm:tabView:aistdvrsndd_hinput').value;");
				System.out.println("pradeep FinalSelectedValue hidden value is --" + defaultValue);
				fnpMymsg("@ -  Fetched/Selected value is ---" + FinalSelectedValue);
				String value2 = StringUtils.normalizeSpace(value);

				if (!(((FinalSelectedValue.contains(value))) || ((FinalSelectedValue.contains(value2))))) {
					msg = "@ - '" + value + "'  is NOT selected properly in  DropDown as expected is" + "--'" + value
							+ "' and actual is --'" + FinalSelectedValue + "' .";
					fnpMymsg(msg);
					throw new Exception(msg);
				} else {
					fnpMymsg("@ - '" + value + "'  is  selected properly in  DropDown as expected is" + "--'" + value
							+ "' and actual is --'" + FinalSelectedValue + "' .");
					selectedProperly = true;
					break;
				}

			} catch (Throwable t) {
				fnpMymsg(
						"@ - fnpPFList is failed due to some error but try again in catch block. Previous error caught was ---"
								+ t.getMessage());
				Thread.sleep(2000);
				if (intWhileCounter > 3) {
					fnpMymsg("@ - NOT try again and gong to exit after fnpPFList is failed for chance ----"
							+ intWhileCounter);
					break;
				} else {
					// again in while loop
					fnpMymsg("@ - try again after fnpPFList is failed for chance ----" + intWhileCounter);
				}

			}

		}

		if (!selectedProperly) {

			throw new Exception("\n\n\n   OR  might be Value [" + value
					+ "] is not present in List ,plz see screenshot [ValueMissingInList" + value + SShots + "]");
		} else {
			// nothing for now...
		}

	}

	public static void fnpPFList_usingFilterBox(String triangleXpath, String fiterboxxpath, String XpathKey,
			String XpathOptionsKey, String value) throws Throwable {

		value = value.trim();
		String originalValue = value;

		String defaultValue = fnpGetText_OR(XpathKey);

		if ((!defaultValue.equalsIgnoreCase("")) & (defaultValue.contains(value))) {
			fnpMymsg("@ - default value is same as expected, so returning back.");
			return;
		}

		String tagname = null;
		boolean ValueMatched = false;
		boolean found = false;
		boolean selectedProperly = false;

		int intWhileCounter = 0;
		while (true) {
			intWhileCounter++;
			try {

				tagname = null;
				ValueMatched = false;
				found = false;

				String temp = originalValue;
				value = originalValue;
				fnpMymsg("Going to select this value ---'" + value + "'  .");

				if (!(fnpCheckElementDisplayedImmediately(XpathOptionsKey))) {
					fnpMymsg("Here assumption is that list is not expanded.");
					fnpClick_OR_WithoutWait(triangleXpath);
				} else {
					// This will work when try again in 2nd chance, first close
					// already opened one and then click again to expand it.
					fnpMymsg("Here assumption is that list is already expanded so first close and then open it again.");
					fnpClick_OR_WithoutWait(triangleXpath);
					Thread.sleep(1000);
					fnpClick_OR_WithoutWait(triangleXpath);
				}

				WebElement wb = fnpGetORObjectX(fiterboxxpath);
				wb.clear();
				// wb.sendKeys(value.substring(0, (value.length()-5)));
				wb.sendKeys(value);

				Thread.sleep(500);
				// String listValue = OR.getProperty(XpathOptionsKey) +
				// "/div/ul/li[contains(@data-label,'" + value + "')]";
				// String listValue = OR.getProperty(XpathOptionsKey) +
				// "/ul/li[contains(@data-item-label,'" + value + "')]";
				String listValue = OR.getProperty(XpathOptionsKey) + "//ul/li[contains(text(),'" + value + "')]";

				// if (fnpCheckElementDisplayed_NOR(listValue, 1)) {
				if (fnpCheckElementDisplayed_NOR(listValue, 60 * 2)) {
					// nothing to do
				} else {
					fnpMymsg("This value '" + value + "'  is not present as it is -- so going to lower its case");
					String new_value = value.toLowerCase();
					// listValue = OR.getProperty(XpathOptionsKey) +
					// "/div/ul/li[contains(@data-label,'" + new_value + "')]";
					listValue = OR.getProperty(XpathOptionsKey) + "//ul/li[contains(text(),'" + new_value + "')]";
					if (fnpCheckElementDisplayedImmediately_NOR(listValue)) {
						// nothing to do
						value = new_value;
					} else {
						fnpMymsg("Now again this value '" + new_value
								+ "'  is not present as it is -- so going to make its first digit in uppercase.");

						new_value = fnpReturnAStringWholeFirstCharacterIsInUpperCase(new_value);
						value = new_value;
						// listValue = OR.getProperty(XpathOptionsKey) +
						// "/div/ul/li[contains(@data-label,'" + new_value + "')]";
						listValue = OR.getProperty(XpathOptionsKey) + "//ul/li[contains(text(),'" + new_value + "')]";
						if (fnpCheckElementDisplayedImmediately_NOR(listValue)) {
							value = new_value;
						} else {
							fnpMymsg("Now again this value '" + new_value
									+ "'  is not present as it is -- so going to make its all letters in upper case.");
							new_value = value.toUpperCase();
							// listValue = OR.getProperty(XpathOptionsKey) +
							// "/div/ul/li[contains(@data-label,'" + new_value + "')]";
							listValue = OR.getProperty(XpathOptionsKey) + "//ul/li[contains(text(),'" + new_value
									+ "')]";
							if (fnpCheckElementDisplayedImmediately_NOR(listValue)) {
								value = new_value;
							} else {
								fnpMymsg("Now again this value '" + new_value
										+ "'  is not present as it is -- so going to check once again (once more time)  the initial value as it is i.e. '"
										+ temp + "'  .");
								value = temp; // to check once again (once more
												// time) the initial value as it
												// is
							}
						}

					}

				}

				// listValue = OR.getProperty(XpathOptionsKey) +
				// "/div/ul/li[contains(@data-label,'" + value + "')]";
				listValue = OR.getProperty(XpathOptionsKey) + "//ul/li[contains(text(),'" + value + "')]";

				fnpGetORObjectX_WhenMultipleObjectsHavingSameXpath_NOR_immediately(listValue, value).click();// new
																												// change
																												// on
																												// 11-09-17

				found = true;
				fnpMymsg("@ -  clicked expected option value  in try block.");

				Thread.sleep(1000);

				// fnpWorkAroundToClickbottomFooter();

				fnpMymsg("@ -  Now going to fetch the selected value.");
				// String FinalSelectedValue = fnpGetText_OR(XpathKey).trim();
				String FinalSelectedValue = fnpGetText_OR(XpathKey);
				// System.out.println("pradeep FinalSelectedValue value is --"+defaultValue);
				fnpMymsg("@ -  Fetched/Selected value is ---" + FinalSelectedValue);
				String value2 = StringUtils.normalizeSpace(value);

				if (!(((FinalSelectedValue.contains(value))) || ((FinalSelectedValue.contains(value2))))) {
					msg = "@ - '" + value + "'  is NOT selected properly in  DropDown as expected is" + "--'" + value
							+ "' and actual is --'" + FinalSelectedValue + "' .";
					fnpMymsg(msg);
					throw new Exception(msg);
				} else {
					fnpMymsg("@ - '" + value + "'  is  selected properly in  DropDown as expected is" + "--'" + value
							+ "' and actual is --'" + FinalSelectedValue + "' .");
					selectedProperly = true;
					break;
				}

			} catch (Throwable t) {
				fnpMymsg(
						"@ - fnpPFList is failed due to some error but try again in catch block. Previous error caught was ---"
								+ t.getMessage());
				Thread.sleep(2000);
				if (intWhileCounter > 3) {
					fnpMymsg("@ - NOT try again and gong to exit after fnpPFList is failed for chance ----"
							+ intWhileCounter);
					break;
				} else {
					// again in while loop
					fnpMymsg("@ - try again after fnpPFList is failed for chance ----" + intWhileCounter);
				}

			}

		}

		if (!selectedProperly) {

			throw new Exception("\n\n\n   OR  might be Value [" + value
					+ "] is not present in List ,plz see screenshot [ValueMissingInList" + value + SShots + "]");
		} else {
			// nothing for now...
		}

	}

	/***********
	 * To select the value from prime faces list that contains matching value
	 *************/
	// using this taken from grid
	public static void fnpPFList_NotCheckingDefaultIsSameOrNot_specialCaseForiAg(String XpathKey,
			String XpathOptionsKey, String value) throws Throwable {

		value = value.trim();
		String originalValue = value;

		/*
		 * String defaultValue = fnpGetText_OR(XpathKey);
		 * if ((!defaultValue.equalsIgnoreCase("")) & (defaultValue.contains(value))) {
		 * fnpMymsg("@ - default value is same as expected, so returning back.");
		 * return;
		 * }
		 */

		String tagname = null;
		boolean ValueMatched = false;
		boolean found = false;
		boolean selectedProperly = false;

		int intWhileCounter = 0;
		while (true) {
			intWhileCounter++;
			try {

				tagname = null;
				ValueMatched = false;
				found = false;

				String temp = originalValue;
				value = originalValue;
				fnpMymsg("Going to select this value ---'" + value + "'  .");
				fnpWaitForVisible(XpathKey);
				fnpWaitTillVisiblityOfElementNClickable_OR(XpathKey);

				if (!(fnpCheckElementDisplayedImmediately(XpathOptionsKey))) {
					fnpMymsg("Here assumption is that list is not expanded.");
					fnpClick_OR_WithoutWait(XpathKey);
				} else {
					// This will work when try again in 2nd chance, first close
					// already opened one and then click again to expand it.
					fnpMymsg("Here assumption is that list is already expanded so first close and then open it again.");
					fnpClick_OR_WithoutWait(XpathKey);
					Thread.sleep(1000);
					fnpClick_OR_WithoutWait(XpathKey);
				}

				Thread.sleep(500);
				String listValue = OR.getProperty(XpathOptionsKey) + "/div/ul/li[contains(@data-label,'" + value
						+ "')]";

				if (fnpCheckElementDisplayed_NOR(listValue, 1)) {
					// nothing to do
				} else {
					fnpMymsg("This value '" + value + "'  is not present as it is -- so going to lower its case");
					String new_value = value.toLowerCase();
					listValue = OR.getProperty(XpathOptionsKey) + "/div/ul/li[contains(@data-label,'" + new_value
							+ "')]";
					if (fnpCheckElementDisplayedImmediately_NOR(listValue)) {
						// nothing to do
						value = new_value;
					} else {
						fnpMymsg("Now again this value '" + new_value
								+ "'  is not present as it is -- so going to make its first digit in uppercase.");

						new_value = fnpReturnAStringWholeFirstCharacterIsInUpperCase(new_value);
						value = new_value;
						listValue = OR.getProperty(XpathOptionsKey) + "/div/ul/li[contains(@data-label,'" + new_value
								+ "')]";
						if (fnpCheckElementDisplayedImmediately_NOR(listValue)) {
							value = new_value;
						} else {
							fnpMymsg("Now again this value '" + new_value
									+ "'  is not present as it is -- so going to make its all letters in upper case.");
							new_value = value.toUpperCase();
							listValue = OR.getProperty(XpathOptionsKey) + "/div/ul/li[contains(@data-label,'"
									+ new_value + "')]";
							if (fnpCheckElementDisplayedImmediately_NOR(listValue)) {
								value = new_value;
							} else {
								fnpMymsg("Now again this value '" + new_value
										+ "'  is not present as it is -- so going to check once again (once more time)  the initial value as it is i.e. '"
										+ temp + "'  .");
								value = temp; // to check once again (once more
												// time) the initial value as it
												// is
							}
						}

					}

				}

				listValue = OR.getProperty(XpathOptionsKey) + "/div/ul/li[contains(@data-label,'" + value + "')]";
				fnpMymsg("@ - going to click expected option value --" + value);

				listValue = OR.getProperty(XpathOptionsKey) + "/div/ul/li[contains(@data-label,'" + value + "')]";

				fnpGetORObjectX_WhenMultipleObjectsHavingSameXpath_NOR_immediately(listValue, value).click();// new
																												// change
																												// on
																												// 11-09-17

				found = true;
				fnpMymsg("@ -  clicked expected option value  in try block.");

				Thread.sleep(1000);

				fnpWorkAroundToClickbottomFooter();

				fnpMymsg("@ -  Now going to fetch the selected value.");
				String FinalSelectedValue = fnpGetText_OR(XpathKey).trim();
				fnpMymsg("@ -  Fetched/Selected value is ---" + FinalSelectedValue);
				String value2 = StringUtils.normalizeSpace(value);

				if (!(((FinalSelectedValue.contains(value))) || ((FinalSelectedValue.contains(value2))))) {
					msg = "@ - '" + value + "'  is NOT selected properly in  DropDown as expected is" + "--'" + value
							+ "' and actual is --'" + FinalSelectedValue + "' .";
					fnpMymsg(msg);
					throw new Exception(msg);
				} else {
					fnpMymsg("@ - '" + value + "'  is  selected properly in  DropDown as expected is" + "--'" + value
							+ "' and actual is --'" + FinalSelectedValue + "' .");
					selectedProperly = true;
					break;
				}

			} catch (Throwable t) {
				fnpMymsg(
						"@ - fnpPFList is failed due to some error but try again in catch block. Previous error caught was ---"
								+ t.getMessage());
				Thread.sleep(2000);
				if (intWhileCounter > 3) {
					fnpMymsg("@ - NOT try again and gong to exit after fnpPFList is failed for chance ----"
							+ intWhileCounter);
					break;
				} else {
					// again in while loop
					fnpMymsg("@ - try again after fnpPFList is failed for chance ----" + intWhileCounter);
				}

			}

		}

		if (!selectedProperly) {

			throw new Exception("\n\n\n   OR  might be Value [" + value
					+ "] is not present in List ,plz see screenshot [ValueMissingInList" + value + SShots + "]");
		} else {
			// nothing for now...
		}

	}

	/***********
	 * To select the value from prime faces list that contains matching value
	 *************/
	// using this taken from grid
	public static void fnpPFList_NOR(String Xpath, String XpathOptions, String value) throws Throwable {

		value = value.trim();
		String originalValue = value;

		String defaultValue = fnpGetText_NOR(Xpath);
		if ((!defaultValue.equalsIgnoreCase("")) & (defaultValue.contains(value))) {
			fnpMymsg("@ - default value is same as expected, so returning back.");
			return;
		}

		String tagname = null;
		boolean ValueMatched = false;
		boolean found = false;
		boolean selectedProperly = false;

		int intWhileCounter = 0;
		while (true) {
			intWhileCounter++;
			try {

				tagname = null;
				ValueMatched = false;
				found = false;

				String temp = originalValue;
				value = originalValue;
				fnpMymsg("Going to select this value ---'" + value + "'  .");
				// fnpWaitForVisible_NOR(Xpath);
				fnpWaitTillVisiblityOfElementNClickable_NOR(Xpath);

				if (!(fnpCheckElementDisplayedImmediately_NOR(XpathOptions))) {
					fnpMymsg("Here assumption is that list is not expanded.");
					fnpClick_NOR_withoutWait(Xpath);
				} else {
					// This will work when try again in 2nd chance, first close
					// already opened one and then click again to expand it.
					fnpMymsg("Here assumption is that list is already expanded so first close and then open it again.");
					fnpClick_NOR_withoutWait(Xpath);
					Thread.sleep(1000);
					fnpClick_NOR_withoutWait(Xpath);
				}

				Thread.sleep(500);
				String listValue = XpathOptions + "/div/ul/li[contains(@data-label,'" + value + "')]";

				if (fnpCheckElementDisplayed_NOR(listValue, 1)) {
					// nothing to do
				} else {
					fnpMymsg("This value '" + value + "'  is not present as it is -- so going to lower its case");
					String new_value = value.toLowerCase();
					listValue = XpathOptions + "/div/ul/li[contains(@data-label,'" + new_value + "')]";
					if (fnpCheckElementDisplayedImmediately_NOR(listValue)) {
						// nothing to do
						value = new_value;
					} else {
						fnpMymsg("Now again this value '" + new_value
								+ "'  is not present as it is -- so going to make its first digit in uppercase.");

						new_value = fnpReturnAStringWholeFirstCharacterIsInUpperCase(new_value);
						value = new_value;
						listValue = XpathOptions + "/div/ul/li[contains(@data-label,'" + new_value + "')]";
						if (fnpCheckElementDisplayedImmediately_NOR(listValue)) {
							value = new_value;
						} else {
							fnpMymsg("Now again this value '" + new_value
									+ "'  is not present as it is -- so going to make its all letters in upper case.");
							new_value = value.toUpperCase();
							listValue = XpathOptions + "/div/ul/li[contains(@data-label,'" + new_value + "')]";
							if (fnpCheckElementDisplayedImmediately_NOR(listValue)) {
								value = new_value;
							} else {
								fnpMymsg("Now again this value '" + new_value
										+ "'  is not present as it is -- so going to check once again (once more time)  the initial value as it is i.e. '"
										+ temp + "'  .");
								value = temp; // to check once again (once more
												// time) the initial value as it
												// is
							}
						}

					}

				}

				listValue = XpathOptions + "/div/ul/li[contains(@data-label,'" + value + "')]";
				fnpMymsg("@ - going to click expected option value --" + value);

				listValue = XpathOptions + "/div/ul/li[contains(@data-label,'" + value + "')]";

				fnpGetORObjectX_WhenMultipleObjectsHavingSameXpath_NOR_immediately(listValue, value).click();// new
																												// change
																												// on
																												// 11-09-17

				found = true;
				fnpMymsg("@ -  clicked expected option value  in try block.");

				Thread.sleep(1000);

				try {
					if (fnpCheckElementEnabledImmediately("FooterElement_id")) {
						fnpMymsg("@ - Footer element is enabled.");
						fnpMouseHover("FooterElement_id");
						fnpGetORObjectX("FooterElement_id").click();
					}
				} catch (Throwable t) {
					// nothing to do
				}

				fnpMymsg("@ -  Now going to fetch the selected value.");
				String FinalSelectedValue = fnpGetText_NOR(Xpath).trim();
				fnpMymsg("@ -  Fetched/Selected value is ---" + FinalSelectedValue);
				String value2 = StringUtils.normalizeSpace(value);

				if (!(((FinalSelectedValue.contains(value))) || ((FinalSelectedValue.contains(value2))))) {
					msg = "@ - '" + value + "'  is NOT selected properly in  DropDown as expected is" + "--'" + value
							+ "' and actual is --'" + FinalSelectedValue + "' .";
					fnpMymsg(msg);
					throw new Exception(msg);
				} else {
					fnpMymsg("@ - '" + value + "'  is  selected properly in  DropDown as expected is" + "--'" + value
							+ "' and actual is --'" + FinalSelectedValue + "' .");
					selectedProperly = true;
					break;
				}

			} catch (Throwable t) {
				fnpMymsg(
						"@ - fnpPFList is failed due to some error but try again in catch block. Previous error caught was ---"
								+ t.getMessage());
				Thread.sleep(2000);
				if (intWhileCounter > 3) {
					fnpMymsg("@ - NOT try again and gong to exit after fnpPFList is failed for chance ----"
							+ intWhileCounter);
					break;
				} else {
					// again in while loop
					fnpMymsg("@ - try again after fnpPFList is failed for chance ----" + intWhileCounter);
				}

			}

		}

		if (!selectedProperly) {

			// String errorMsg = t.getMessage();
			throw new Exception("\n\n\n   And/OR  might be Value [" + value
					+ "] is not present in List ,plz see screenshot [ValueMissingInList" + value + SShots + "]");
		} else {
			// nothing for now...
		}

	}

	/***********
	 * To select the value from prime faces list that contains matching value
	 *************/
	public static void fnpPFList3(String XpathKey, String XpathOptionsKey, String value) throws Throwable {
		try {

			fnpWaitForVisible(XpathKey);
			fnpWaitTillVisiblityOfElementNClickable_OR(XpathKey);

			fnpClick_OR_WithoutWait(XpathKey);
			Thread.sleep(500);
			String listValue = OR.getProperty(XpathOptionsKey) + "/div/ul/li[contains(@data-label,'" + value + "')]";

			String tagname = null;
			boolean ValueMatched = false;
			boolean found = false;

			try {

				fnpMymsg("@  - going to click expected option value.");
				fnpWaitTillVisiblityOfElementNClickableInsidePFList(listValue);

				fnpClick_NOR_withoutWait(listValue);
				found = true;
				fnpMymsg("@  -  clicked expected option value successfully in try block.");

				Thread.sleep(1000);

				String FinalSelectedValue = fnpGetORObjectX(XpathKey).getText();

				if (!(FinalSelectedValue.contains(value))) {
					msg = "@  - '" + value + "'  is NOT selected properly in  DropDown as expected is" + "--'" + value
							+ "' and actual is --'" + FinalSelectedValue + "' .";
					fnpMymsg(msg);
					throw new Exception(msg);
				} else {
					fnpMymsg("@  - '" + value + "'  is  selected properly in  DropDown as expected is" + "--'" + value
							+ "' and actual is --'" + FinalSelectedValue + "' .");
				}

			} catch (Throwable t) {

				fnpMymsg(
						"@  - fnpPFList is failed due to some error but try again in catch block. Previous error caught was ---"
								+ t.getMessage());

				fnpMymsg("@  - So processing further in catch block again. ");

				if (fnpCheckElementDisplayedImmediately_NOR(listValue)) {
					Thread.sleep(1000);
					fnpClick_OR_WithoutWait(XpathKey);
					Thread.sleep(1000);
				}

				if (fnpCountChildElementPresenceImmediately_NotInOR(
						fnpGetORObjectX___NOR(OR.getProperty(XpathOptionsKey)), "li") > 0) {
					List<WebElement> objectlist5 = fnpGetORObjectX___NOR(OR.getProperty(XpathOptionsKey))
							.findElements(By.tagName("li"));
					tagname = "li";
					fnpMymsg(" li tag is present for this list. ");
					int totalvaluesinDropDown = objectlist5.size();

					fnpMymsg("@  ---going to fetch visible selected value");

					String VisibleselectedOption = driver
							.findElement(By.xpath(
									OR.getProperty(XpathOptionsKey) + "//li[contains(@class,'ui-state-highlight')]"))
							.getText();

					fnpMymsg("@  ---selected value is ---" + VisibleselectedOption);
					int iCurrentPosition = 1;
					String whileloopCurrentValue = "";
					while (true) {

						whileloopCurrentValue = driver
								.findElement(
										By.xpath(OR.getProperty(XpathOptionsKey) + "//li[" + iCurrentPosition + "]"))
								.getText();

						if ((VisibleselectedOption.equalsIgnoreCase(whileloopCurrentValue))) {
							break;
						}
						iCurrentPosition++;

						if (iCurrentPosition > totalvaluesinDropDown) {
							break;
						}
					}

					fnpMymsg("@  ---iCurrentPosition is ----" + iCurrentPosition);

					int iExpectedPosition = 1;
					whileloopCurrentValue = "";
					while (true) {

						whileloopCurrentValue = driver
								.findElement(
										By.xpath(OR.getProperty(XpathOptionsKey) + "//li[" + iExpectedPosition + "]"))
								.getText();

						if ((value.equalsIgnoreCase(whileloopCurrentValue))) {
							break;
						}
						iExpectedPosition++;
						if (iCurrentPosition > totalvaluesinDropDown) {
							break;
						}
					}

					fnpMymsg("@  ---iExpectedPosition is ----" + iExpectedPosition);

					if (iExpectedPosition > iCurrentPosition) {

						fnpMymsg("@  --- Expected position is higher");

						int innerwhilecounter = 0;
						while (iExpectedPosition != (iCurrentPosition + innerwhilecounter)) {
							new Actions(driver).moveToElement(objectlist5.get(iCurrentPosition + innerwhilecounter))
									.sendKeys(Keys.ARROW_DOWN).build().perform();

							Thread.sleep(100);

							fnpMymsg("@  - arrow down ---" + innerwhilecounter);
							innerwhilecounter++;

							if (innerwhilecounter > totalvaluesinDropDown) {
								break;
							}
						}
						fnpGetORObjectX___NOR(OR.getProperty(XpathOptionsKey) + "//li[" + iExpectedPosition + "]")
								.click();

						fnpMymsg("@  - Value clicked successfully");
						found = true;
						Thread.sleep(2000);
					} else {

						fnpMymsg("@  --- Expected position is lower");
						int innerwhilecounter = 0;
						while (iExpectedPosition != (iCurrentPosition - innerwhilecounter)) {
							new Actions(driver).moveToElement(objectlist5.get(iCurrentPosition - innerwhilecounter))
									.sendKeys(Keys.ARROW_UP).build().perform();

							Thread.sleep(100);

							fnpMymsg("@  - arrow UP ---" + innerwhilecounter);
							innerwhilecounter++;
							if (innerwhilecounter > totalvaluesinDropDown) {
								break;
							}
						}
						fnpGetORObjectX___NOR(OR.getProperty(XpathOptionsKey) + "//li[" + iExpectedPosition + "]")
								.click();
						// System.out.println("@ - Value clicked successfully");
						fnpMymsg("@  - Value clicked successfully");
						found = true;
						Thread.sleep(2000);

					}

					if (found == false) {
						msg = "@  - Excel value --'" + value + "'  is not matched with DropDown Value.";
						fnpMymsg(msg);
						throw new Exception(msg);

					}

					String FinalSelectedValue = fnpGetORObjectX(XpathKey).getText();

					if (!(FinalSelectedValue.contains(value))) {
						msg = "@  - '" + value + "'  is NOT selected properly in  DropDown as expected is" + "--'"
								+ value + "' and actual is --'" + FinalSelectedValue + "' .";
						fnpMymsg(msg);
						throw new Exception(msg);
					} else {
						fnpMymsg("@  - '" + value + "'  is  selected properly in  DropDown as expected is" + "--'"
								+ value + "' and actual is --'" + FinalSelectedValue + "' .");
					}

				}
			}

		} catch (Throwable t) {
			fnpDesireScreenshot("ValueMissingInList" + value);
			String errorMsg = t.getMessage();
			throw new Exception(errorMsg + "\n\n\n   And/OR  might be Value [" + value
					+ "] is not present in List ,plz see screenshot [ValueMissingInList" + value + SShots + "]");
		}
	}

	/***********
	 * To select the value from prime faces list that contains matching value
	 *************/
	public static void fnpPFListModify(String XpathKey, String value) throws Throwable {

		int maxTry = 2;
		int init = 0;
		while (true) {
			init++;

			try {

				fnpWaitForVisible(XpathKey);
				fnpWaitTillVisiblityOfElementNClickable_OR(XpathKey);
				// fnpGetORObjectX(XpathKey).click();

				String xpathforLabel = OR.getProperty(XpathKey);
				// String xpathforLabel = OR.getProperty(XpathKey);
				if (xpathforLabel.contains("~")) {
					xpathforLabel = xpathforLabel.split("~")[1];
				}
				String xpathforPanel = xpathforLabel.replace("_label", "_panel");

				if (!(fnpCheckElementDisplayedImmediately_NOR(xpathforPanel))) {
					fnpMymsg("Here assumption is that list is not expanded.");
					// fnpClick_NOR_withoutWait(xpathforLabel);
					fnpGetORObjectX___NOR(xpathforLabel).click();
				} else {
					// This will work when try again in 2nd chance, first close
					// already opened one and then click again to expand it.
					fnpMymsg("Here assumption is that list is already expanded so first close and then open it again.");
					// fnpClick_NOR_withoutWait(xpathforLabel);
					fnpGetORObjectX___NOR(xpathforLabel).click();
					Thread.sleep(1000);
					// fnpClick_NOR_withoutWait(xpathforLabel);
					fnpGetORObjectX___NOR(xpathforLabel).click();
				}

				Thread.sleep(500);
				String listValue = xpathforPanel + "/div/ul/li[contains(@data-label,'" + value + "')]";

				fnpWaitForVisible_NotInOR(listValue);

				fnpWaitTillVisiblityOfElementNClickableInsidePFList(listValue);
				driver.findElement(By.xpath(listValue)).click();
				fnpMymsg("Clicked this value --'" + value + "'.");
				Thread.sleep(500);
				String FinalSelectedValue = fnpGetORObjectX(XpathKey).getText();

				if (!(FinalSelectedValue.contains(value))) {
					msg = "@  - '" + value + "'  is NOT selected properly in  DropDown as expected is" + "--'" + value
							+ "' and actual is --'" + FinalSelectedValue + "' .";
					fnpMymsg(msg);
					throw new Exception(msg);
				} else {
					fnpMymsg("@  - '" + value + "'  is  selected properly in  DropDown as expected is" + "--'" + value
							+ "' and actual is --'" + FinalSelectedValue + "' .");
				}

				break;

			} catch (Exception e) {
				if (init < maxTry) {
					// try again
				} else {
					fnpDesireScreenshot("ValueMissingInList" + value);
					String errorMsg = e.getMessage();
					throw new Exception(errorMsg + "   And/OR  might be Value [" + value
							+ "] is not present in List ,plz see screenshot [ValueMissingInList" + value + SShots
							+ "]");
				}

			}

		}

	}

	/***********
	 * To select the value from prime faces list that contains matching value
	 *************/
	public static void fnpPFListModify_usingWhenIgnoringErrorOnSelectingValues(String XpathKey, String value)
			throws Throwable {
		// try {

		String xpathforLabel = OR.getProperty(XpathKey);
		if (xpathforLabel.contains("~")) {
			xpathforLabel = xpathforLabel.split("~")[1];
		}

		String xpathforPanel = xpathforLabel.replace("_label", "_panel");

		fnpWaitForVisible(XpathKey);
		fnpWaitTillVisiblityOfElementNClickable_OR(XpathKey);
		// fnpGetORObjectX(XpathKey).click();

		if (!(fnpCheckElementDisplayedImmediately_NOR(xpathforPanel))) {
			fnpMymsg("Here assumption is that list is not expanded.");
			// fnpClick_NOR_withoutWait(xpathforLabel);
			fnpGetORObjectX___NOR(xpathforLabel).click();
		} else {
			// This will work when try again in 2nd chance, first close
			// already opened one and then click again to expand it.
			fnpMymsg("Here assumption is that list is already expanded so first close and then open it again.");
			// fnpClick_NOR_withoutWait(xpathforLabel);
			fnpGetORObjectX___NOR(xpathforLabel).click();
			Thread.sleep(1000);
			// fnpClick_NOR_withoutWait(xpathforLabel);
			fnpGetORObjectX___NOR(xpathforLabel).click();
		}

		// fnpWaitForVisible(XpathOptionsKey);
		fnpWaitForVisible_NotInOR(xpathforPanel);

		/*
		 * //Thread.sleep(500);
		 * String listValue = xpathforPanel + "/div/ul/li[contains(@data-label,'" +
		 * value + "')]";
		 * fnpWaitForVisible_NotInOR(listValue);
		 * fnpWaitTillVisiblityOfElementNClickableInsidePFList(listValue);
		 * driver.findElement(By.xpath(listValue)).click();
		 * Thread.sleep(500);
		 * 
		 */

		List<WebElement> allActualValuesList = driver.findElements(By.xpath(xpathforPanel + "/div/ul/li"));
		int totalValues = allActualValuesList.size();

		String expectedTemp;
		String actualTemp;
		boolean found;
		Actions act = new Actions(driver);

		found = false;
		value = value.toLowerCase();

		for (int j = 0; j < totalValues; j++) {
			actualTemp = allActualValuesList.get(j).getText().trim();
			actualTemp = actualTemp.toLowerCase();
			fnpMymsg("Current value is -'" + actualTemp + "' and expected value is -'" + value + "'.");
			if (actualTemp.contains(value)) {
				found = true;
				fnpMymsg("Current and expected values are matched.");
				allActualValuesList.get(j).click();
				break;
			} else {
				if ((j + 1) != totalValues) {
					fnpMymsg("Current and expected values are not matched, so moving to next value in drop down.");
					act.moveToElement(allActualValuesList.get(j)).sendKeys(Keys.ARROW_DOWN).build().perform();
					Thread.sleep(500);
				}

			}
		}

		if (found) {
			msg = " Finally,this value '" + value + "' is present in the drop down, henced clicked it. ";
			fnpMymsg(msg);
		} else {
			msg = " Finally,this value '" + value + "' is NOT present in the drop down.";
			fnpMymsg(msg);
			throw new Exception(msg);
		}

		/*
		 * } catch (Exception e) {
		 * fnpDesireScreenshot("ValueMissingInList" + value);
		 * String errorMsg = e.getMessage();
		 * throw new Exception(errorMsg + "   And/OR  might be Value [" + value +
		 * "] is not present in List ,plz see screenshot [ValueMissingInList" + value +
		 * SShots + "]");
		 * }
		 * 
		 */

	}

	/***********
	 * To select the value from prime faces list that contains matching value
	 *************/
	public static void fnpPFListModify_usingWhenIgnoringErrorOnSelectingValues_2(String XpathKey, String value)
			throws Throwable {
		boolean selectedProperly = false;

		int intWhileCounter = 0;
		while (true) {
			intWhileCounter++;
			try {

				String xpathforLabel = OR.getProperty(XpathKey);
				if (xpathforLabel.contains("~")) {
					xpathforLabel = xpathforLabel.split("~")[1];
				}

				String xpathforPanel = xpathforLabel.replace("_label", "_panel");

				fnpWaitForVisible(XpathKey);
				fnpWaitTillVisiblityOfElementNClickable_OR(XpathKey);
				// fnpGetORObjectX(XpathKey).click();

				if (!(fnpCheckElementDisplayedImmediately_NOR(xpathforPanel))) {
					fnpMymsg("Here assumption is that list is not expanded.");
					// fnpClick_NOR_withoutWait(xpathforLabel);
					fnpGetORObjectX___NOR(xpathforLabel).click();
				} else {
					// This will work when try again in 2nd chance, first close
					// already opened one and then click again to expand it.
					fnpMymsg("Here assumption is that list is already expanded so first close and then open it again.");
					// fnpClick_NOR_withoutWait(xpathforLabel);
					fnpGetORObjectX___NOR(xpathforLabel).click();
					Thread.sleep(1000);
					// fnpClick_NOR_withoutWait(xpathforLabel);
					fnpGetORObjectX___NOR(xpathforLabel).click();
				}

				// fnpWaitForVisible(XpathOptionsKey);
				fnpWaitForVisible_NotInOR(xpathforPanel);

				/*
				 * //Thread.sleep(500);
				 * String listValue = xpathforPanel + "/div/ul/li[contains(@data-label,'" +
				 * value + "')]";
				 * fnpWaitForVisible_NotInOR(listValue);
				 * fnpWaitTillVisiblityOfElementNClickableInsidePFList(listValue);
				 * driver.findElement(By.xpath(listValue)).click();
				 * Thread.sleep(500);
				 * 
				 */

				List<WebElement> allActualValuesList = driver.findElements(By.xpath(xpathforPanel + "/div/ul/li"));
				int totalValues = allActualValuesList.size();

				String expectedTemp;
				String actualTemp;
				boolean found;
				Actions act = new Actions(driver);

				found = false;
				value = value.toLowerCase();

				for (int j = 0; j < totalValues; j++) {
					actualTemp = allActualValuesList.get(j).getText().trim();
					actualTemp = actualTemp.toLowerCase();
					fnpMymsg("Current value is -'" + actualTemp + "' and expected value is -'" + value + "'.");
					if (actualTemp.contains(value)) {
						found = true;
						fnpMymsg("Current and expected values are matched.");
						allActualValuesList.get(j).click();
						break;
					} else {
						if ((j + 1) != totalValues) {
							fnpMymsg(
									"Current and expected values are not matched, so moving to next value in drop down.");
							act.moveToElement(allActualValuesList.get(j)).sendKeys(Keys.ARROW_DOWN).build().perform();
							Thread.sleep(500);
						}

					}
				}

				/*
				 * if (found) {
				 * msg=" Finally,this value '"
				 * +value+"' is present in the drop down, henced clicked it. ";
				 * fnpMymsg(msg);
				 * } else {
				 * msg=" Finally,this value '"+value+"' is NOT present in the drop down.";
				 * fnpMymsg(msg);
				 * throw new Exception(msg);
				 * }
				 */
				found = true;
				fnpMymsg("@ -  clicked expected option value  in try block.");

				Thread.sleep(1000);

				try {
					if (fnpCheckElementEnabledImmediately("FooterElement_id")) {
						fnpMymsg("@ - Footer element is enabled.");
						fnpMouseHover("FooterElement_id");
						fnpGetORObjectX("FooterElement_id").click();
					}
				} catch (Throwable t) {
					// nothing to do
				}

				fnpMymsg("@ -  Now going to fetch the selected value.");
				String FinalSelectedValue = fnpGetText_NOR(xpathforLabel).trim();
				fnpMymsg("@ -  Fetched/Selected value is ---" + FinalSelectedValue);
				String value2 = StringUtils.normalizeSpace(value);

				// below line is changed on 23-08-2021 as I am using this same in WRAS at one
				// place also
				// if (!(((FinalSelectedValue.contains(value))) ||
				// ((FinalSelectedValue.contains(value2))))) {
				if (!(((FinalSelectedValue.toLowerCase().contains(value.toLowerCase())))
						|| ((FinalSelectedValue.toLowerCase().contains(value2.toLowerCase()))))) {
					msg = "@ - '" + value + "'  is NOT selected properly in  DropDown as expected is" + "--'" + value
							+ "' and actual is --'" + FinalSelectedValue + "' .";
					fnpMymsg(msg);
					throw new Exception(msg);
				} else {
					fnpMymsg("@ - '" + value + "'  is  selected properly in  DropDown as expected is" + "--'" + value
							+ "' and actual is --'" + FinalSelectedValue + "' .");
					selectedProperly = true;
					break;
				}

			} catch (Throwable t) {
				fnpMymsg(
						"@ - fnpPFList is failed due to some error but try again in catch block. Previous error caught was ---"
								+ t.getMessage());
				Thread.sleep(2000);
				if (intWhileCounter > 3) {
					fnpMymsg("@ - NOT try again and gong to exit after fnpPFList is failed for chance ----"
							+ intWhileCounter);
					break;
				} else {
					// again in while loop
					fnpMymsg("@ - try again after fnpPFList is failed for chance ----" + intWhileCounter);
				}

			}
		}

		if (!selectedProperly) {

			// String errorMsg = t.getMessage();
			throw new Exception("\n\n\n  Either  this  value [" + value
					+ "] is not present in List OR it is not getting selected properly ,plz see screenshot [ValueMissingInList"
					+ value + SShots + "]");
		} else {
			// nothing for now...
		}
	}

	/***********
	 * To select the value from prime faces list that contains matching value
	 *************/
	public static void fnpPFListContains_ifMatchingMultipleThenSelectFirstOne(String XpathKey, String value)
			throws Throwable {
		try {
			fnpWaitForVisible(XpathKey);
			fnpWaitTillVisiblityOfElementNClickable_OR(XpathKey);
			fnpGetORObjectX(XpathKey).click();
			Thread.sleep(500);
			String xpathforLabel = OR.getProperty(XpathKey);
			String xpathforPanel = xpathforLabel.replace("_label", "_panel");

			String listValue = xpathforPanel + "/div/ul/li[contains(@data-label,'" + value + "')]";

			List<WebElement> totalElements = fnpGetORObject_list_NOR(listValue, 5);

			totalElements.get(0).click();

			Thread.sleep(500);

		} catch (Exception e) {
			fnpDesireScreenshot("ValueMissingInList" + value);
			String errorMsg = e.getMessage();
			throw new Exception(errorMsg + "   And/OR  might be Value [" + value
					+ "] is not present in List ,plz see screenshot [ValueMissingInList" + value + SShots + "]");
		}
	}

	/***********
	 * To select the value from prime faces list that contains matching value in
	 * li
	 *************/
	public static void fnpPFListByLiNo_old(String XpathKey, String XpathOptionsKey, int liNo) throws Throwable {
		try {
			fnpWaitForVisible(XpathKey);

			fnpGetORObjectX(XpathKey).click();
			Thread.sleep(500);

			int totalValues = driver.findElements(By.xpath(OR.getProperty(XpathOptionsKey) + "/div/ul/li")).size();
			if (!(totalValues > 0)) {
				throw new Exception("Drop down is blank --" + XpathKey);

			}

			if ((totalValues == 1)) {
				String onlyGivenValue = driver.findElement(By.xpath(OR.getProperty(XpathOptionsKey) + "/div/ul/li[1]"))
						.getText();
				if ((onlyGivenValue.toLowerCase().contains("select")) || (onlyGivenValue.trim().equalsIgnoreCase(""))) {
					throw new Exception("Drop down is blank --" + XpathKey);
				}

			}

			String listValue = OR.getProperty(XpathOptionsKey) + "/div/ul/li[" + liNo + "]";
			fnpWaitTillVisiblityOfElementNClickable_NOR_Immediately(listValue);

			String valueToBeSelect = fnpGetText_NOR(listValue);
			if ((valueToBeSelect.toLowerCase().contains("select")) || (valueToBeSelect.trim().equalsIgnoreCase(""))) {
				listValue = OR.getProperty(XpathOptionsKey) + "/div/ul/li[" + (liNo + 1) + "]";
			} else {
				listValue = OR.getProperty(XpathOptionsKey) + "/div/ul/li[" + liNo + "]";
			}

			driver.findElement(By.xpath(listValue)).click();

			Thread.sleep(500);

		} catch (Exception e) {
			fnpDesireScreenshot("ValueMissingInList");
			String errorMsg = e.getMessage();
			throw new Exception(errorMsg + "   And/OR  might be Value for this index --'" + liNo
					+ "' is not present in List ,plz see screenshot [ValueMissingInList]");
		}
	}

	/***********
	 * To select the value from prime faces list that contains matching value in
	 * li
	 *************/
	public static void fnpPFListByLiNo(String XpathKey, String XpathOptionsKey, int liNo) throws Throwable {

		boolean selectedProperly = false;
		String finalValueToBeSelect = "";

		int intWhileCounter = 0;
		while (true) {
			intWhileCounter++;

			try {
				fnpWaitForVisible(XpathKey);

				if (!(fnpCheckElementDisplayedImmediately(XpathOptionsKey))) {
					fnpMymsg("Here assumption is that list is not expanded.");
					fnpClick_OR_WithoutWait(XpathKey);
					Thread.sleep(500);
				} else {

					fnpMymsg("Here assumption is that list is already expanded so first close and then open it again.");
					fnpClick_OR_WithoutWait(XpathKey); // closing here
					Thread.sleep(1000);
					fnpClick_OR_WithoutWait(XpathKey); // re-opening here
				}

				int totalValues = driver.findElements(By.xpath(OR.getProperty(XpathOptionsKey) + "/div/ul/li")).size();
				if (!(totalValues > 0)) {
					throw new Exception("Drop down is blank --" + XpathKey);

				}

				if ((totalValues == 1)) {
					String onlyGivenValue = driver
							.findElement(By.xpath(OR.getProperty(XpathOptionsKey) + "/div/ul/li[1]")).getText();
					if ((onlyGivenValue.toLowerCase().contains("select"))
							|| (onlyGivenValue.trim().equalsIgnoreCase(""))) {
						throw new Exception("Drop down is blank --" + XpathKey);
					}

				}

				String listValue = OR.getProperty(XpathOptionsKey) + "/div/ul/li[" + liNo + "]";

				// fnpWaitTillVisiblityOfElementNClickable_NOR_Immediately(listValue);
				fnpWaitTillVisiblityOfElementNClickable_NOR(listValue);

				String valueToBeSelect = fnpGetText_NOR(listValue);
				if ((valueToBeSelect.toLowerCase().contains("select"))
						|| (valueToBeSelect.trim().equalsIgnoreCase(""))) {
					listValue = OR.getProperty(XpathOptionsKey) + "/div/ul/li[" + (liNo + 1) + "]";
				} else {
					listValue = OR.getProperty(XpathOptionsKey) + "/div/ul/li[" + liNo + "]";
				}

				finalValueToBeSelect = driver.findElement(By.xpath(listValue)).getText();
				driver.findElement(By.xpath(listValue)).click();

				Thread.sleep(1000);

				fnpMymsg("@ -  Now going to fetch the selected value.");
				String FinalSelectedValue = fnpGetText_OR(XpathKey).trim();
				fnpMymsg("@ -  Fetched/Selected value is ---" + FinalSelectedValue);
				String value2 = StringUtils.normalizeSpace(FinalSelectedValue);

				if (!(((finalValueToBeSelect.contains(FinalSelectedValue)))
						|| ((finalValueToBeSelect.contains(value2))))) {
					msg = "@ - '" + finalValueToBeSelect + "'  is NOT selected properly in  DropDown as expected is"
							+ "--'" + finalValueToBeSelect + "' and actual is --'"
							+ FinalSelectedValue + "' .";
					fnpMymsg(msg);
					throw new Exception(msg);
				} else {
					fnpMymsg("@ - '" + finalValueToBeSelect + "'  is  selected properly in  DropDown as expected is"
							+ "--'" + finalValueToBeSelect + "' and actual is --'"
							+ FinalSelectedValue + "' .");
					selectedProperly = true;
					break;
				}

			} catch (Throwable t) {
				fnpMymsg(
						"@ - fnpPFList is failed due to some error but try again in catch block. Previous error caught was ---"
								+ t.getMessage());
				Thread.sleep(2000);
				if (intWhileCounter > 3) {
					fnpMymsg("@ - NOT try again and gong to exit after fnpPFList is failed for chance ----"
							+ intWhileCounter);
					break;
				} else {
					// again in while loop
					fnpMymsg("@ - try again after fnpPFList is failed for chance ----" + intWhileCounter);
				}

			}

		}

		if (!selectedProperly) {
			throw new Exception("\n\n\n   And/OR  might be Value [" + finalValueToBeSelect
					+ "] is not present in List ,plz see screenshot [ValueMissingInList"
					+ finalValueToBeSelect + SShots + "]");
		} else {
			// nothing for now...
		}

	}

	/***********
	 * To select the value from prime faces list that contains exactly same
	 * value
	 *************/
	public static void fnpPFListExactly_old(String XpathKey, String XpathOptionsKey, String value) throws Throwable {

		fnpMymsg(" Going to select value  --" + value);
		fnpWaitForVisible(XpathKey);

		fnpGetORObjectX(XpathKey).click();
		Thread.sleep(1000);

		String listValue = OR.getProperty(XpathOptionsKey) + "/div/ul/li[@data-label='" + value + "'][1]";

		fnpWaitTillVisiblityOfElementNClickable_NOR(listValue);
		driver.findElement(By.xpath(listValue)).click();

		String selectedValue = fnpGetText_OR(XpathKey).trim();
		fnpMymsg("@  ---Actual value is--'" + selectedValue + "'.");
		fnpMymsg("@  ---Expect value is--'" + value.trim() + "'.");

		Assert.assertTrue(selectedValue.equalsIgnoreCase(value.trim()),
				"Value is not selected properly i.e. --" + value);
		fnpMymsg(" Value '" + value + "' is selected properly.");

	}

	/***********
	 * To select the value from prime faces list that EXACT matching value
	 *************/
	// using this taken from grid
	public static void fnpPFListExactly(String XpathKey, String XpathOptionsKey, String value) throws Throwable {

		value = value.trim();
		String originalValue = value;

		String defaultValue = fnpGetText_OR(XpathKey);
		if ((!defaultValue.equalsIgnoreCase("")) & (defaultValue.contains(value))) {
			fnpMymsg("@ - default value is same as expected, so returning back.");
			return;
		}

		String tagname = null;
		boolean ValueMatched = false;
		boolean found = false;
		boolean selectedProperly = false;

		int intWhileCounter = 0;
		while (true) {
			intWhileCounter++;
			try {

				tagname = null;
				ValueMatched = false;
				found = false;

				String temp = originalValue;
				value = originalValue;
				fnpMymsg("Going to select this value ---'" + value + "'  .");
				fnpWaitForVisible(XpathKey);
				fnpWaitTillVisiblityOfElementNClickable_OR(XpathKey);

				if (!(fnpCheckElementDisplayedImmediately(XpathOptionsKey))) {
					fnpMymsg("Here assumption is that list is not expanded.");
					fnpClick_OR_WithoutWait(XpathKey);
				} else {
					// This will work when try again in 2nd chance, first close
					// already opened one and then click again to expand it.
					fnpMymsg("Here assumption is that list is already expanded so first close and then open it again.");
					fnpClick_OR_WithoutWait(XpathKey);
					Thread.sleep(1000);
					fnpClick_OR_WithoutWait(XpathKey);
				}

				Thread.sleep(500);
				String listValue = OR.getProperty(XpathOptionsKey) + "/div/ul/li[@data-label='" + value + "']";

				if (fnpCheckElementDisplayed_NOR(listValue, 1)) {
					// nothing to do
				} else {
					fnpMymsg("This value '" + value + "'  is not present as it is -- so going to lower its case");
					String new_value = value.toLowerCase();
					listValue = OR.getProperty(XpathOptionsKey) + "/div/ul/li[@data-label='" + new_value + "']";
					if (fnpCheckElementDisplayedImmediately_NOR(listValue)) {
						// nothing to do
						value = new_value;
					} else {
						fnpMymsg("Now again this value '" + new_value
								+ "'  is not present as it is -- so going to make its first digit in uppercase.");

						new_value = fnpReturnAStringWholeFirstCharacterIsInUpperCase(new_value);
						value = new_value;
						listValue = OR.getProperty(XpathOptionsKey) + "/div/ul/li[@data-label='" + new_value + "']";
						if (fnpCheckElementDisplayedImmediately_NOR(listValue)) {
							value = new_value;
						} else {
							fnpMymsg("Now again this value '" + new_value
									+ "'  is not present as it is -- so going to make its all letters in upper case.");
							new_value = value.toUpperCase();
							listValue = OR.getProperty(XpathOptionsKey) + "/div/ul/li[@data-label='" + new_value + "']";
							if (fnpCheckElementDisplayedImmediately_NOR(listValue)) {
								value = new_value;
							} else {
								fnpMymsg("Now again this value '" + new_value
										+ "'  is not present as it is -- so going to check once again (once more time)  the initial value as it is i.e. '"
										+ temp + "'  .");
								value = temp; // to check once again (once more
												// time) the initial value as it
												// is
							}
						}

					}

				}

				listValue = OR.getProperty(XpathOptionsKey) + "/div/ul/li[@data-label='" + value + "']";
				fnpMymsg("@ - going to click expected option value --" + value);

				listValue = OR.getProperty(XpathOptionsKey) + "/div/ul/li[@data-label='" + value + "']";

				fnpGetORObjectX_WhenMultipleObjectsHavingSameXpath_NOR_immediately(listValue, value).click();// new
																												// change
																												// on
																												// 11-09-17

				found = true;
				fnpMymsg("@ -  clicked expected option value  in try block.");

				Thread.sleep(1000);

				fnpWorkAroundToClickbottomFooter();

				fnpMymsg("@ -  Now going to fetch the selected value.");
				String FinalSelectedValue = fnpGetText_OR(XpathKey).trim();
				fnpMymsg("@ -  Fetched/Selected value is ---" + FinalSelectedValue);
				String value2 = StringUtils.normalizeSpace(value);

				// if (!(((FinalSelectedValue.contains(value))) |
				// ((FinalSelectedValue.contains(value2))))) {
				if (!(((FinalSelectedValue.equalsIgnoreCase(value)))
						|| ((FinalSelectedValue.equalsIgnoreCase(value2))))) {
					msg = "@ - '" + value + "'  is NOT selected properly in  DropDown as expected is" + "--'" + value
							+ "' and actual is --'" + FinalSelectedValue + "' .";
					fnpMymsg(msg);
					throw new Exception(msg);
				} else {
					fnpMymsg("@ - '" + value + "'  is  selected properly in  DropDown as expected is" + "--'" + value
							+ "' and actual is --'" + FinalSelectedValue + "' .");
					selectedProperly = true;
					break;
				}

			} catch (Throwable t) {
				fnpMymsg(
						"@ - fnpPFList is failed due to some error but try again in catch block. Previous error caught was ---"
								+ t.getMessage());
				Thread.sleep(2000);
				if (intWhileCounter > 3) {
					fnpMymsg("@ - NOT try again and gong to exit after fnpPFList is failed for chance ----"
							+ intWhileCounter);
					break;
				} else {
					// again in while loop
					fnpMymsg("@ - try again after fnpPFList is failed for chance ----" + intWhileCounter);
				}

			}

		}

		if (!selectedProperly) {

			throw new Exception("\n\n\n   OR  might be Value [" + value
					+ "] is not present in List ,plz see screenshot [ValueMissingInList" + value + SShots + "]");
		} else {
			// nothing for now...
		}

	}

	public static void fnpPFList_VerifyOptionsValues(String XpathKey, String XpathOptionsKey, List<String> values)
			throws Throwable {

		fnpWaitForVisible(XpathKey);
		Thread.sleep(1);

		fnpGetORObjectX(XpathKey).click();

		/*
		 * String label_xpath = null;
		 * if (OR.getProperty(XpathKey).contains("~")) {
		 * String[] s1 = OR.getProperty(XpathKey).split("~");
		 * String locatorValue = s1[1];
		 * String locator = s1[0];
		 * label_xpath=locatorValue;
		 * }else{
		 * label_xpath=OR.getProperty(XpathKey);
		 * }
		 * 
		 * String dropdown_expandTriangleIcon=
		 * label_xpath+"/following-sibling::div/span[contains(@class,'ui-icon ui-icon-triangle-1-s ui-c')]"
		 * ;
		 * try{
		 * fnpClick_NOR(dropdown_expandTriangleIcon);
		 * }catch(Throwable t){
		 * System.out.println( "Error is --"+t.getMessage());
		 * }
		 */

		Thread.sleep(500);

		List<WebElement> allActualValuesList = driver
				.findElements(By.xpath(OR.getProperty(XpathOptionsKey) + "/div/ul/li"));
		int totalValues = allActualValuesList.size();
		if (!(totalValues > 0)) {
			throw new Exception("Drop down is blank --" + XpathKey);

		}

		if ((totalValues == 1)) {
			String onlyGivenValue = driver.findElement(By.xpath(OR.getProperty(XpathOptionsKey) + "/div/ul/li[1]"))
					.getText();
			if ((onlyGivenValue.toLowerCase().contains("select")) || (onlyGivenValue.trim().equalsIgnoreCase(""))) {
				throw new Exception("Drop down is blank --" + XpathKey);
			}

		}

		// String listValue = OR.getProperty(XpathOptionsKey) + "/div/ul/li[" + liNo +
		// "]";

		int valuesSize = values.size();

		String expectedTemp;
		String actualTemp;
		boolean found;
		Actions act = new Actions(driver);
		for (int i = 0; i < valuesSize; i++) {
			found = false;
			expectedTemp = values.get(i).trim();
			for (int j = 0; j < totalValues; j++) {
				actualTemp = allActualValuesList.get(j).getText();
				if (actualTemp.equalsIgnoreCase(expectedTemp)) {
					found = true;
					break;
				} else {
					if ((j + 1) != valuesSize) {
						act.moveToElement(allActualValuesList.get(j)).sendKeys(Keys.ARROW_DOWN).build().perform();
					}

				}
			}

			if (found) {
				msg = "This value '" + expectedTemp + "' is present in the drop down.";
				fnpMymsg(msg);
			} else {
				msg = "This value '" + expectedTemp + "' is NOT present in the drop down.";
				fnpMymsg(msg);
				throw new Exception(msg);
			}

		}

	}

	public static void fnpPFList_VerifyOptionsValues_Contains(String XpathKey, String XpathOptionsKey,
			List<String> values) throws Throwable {

		fnpWaitForVisible(XpathKey);

		// fnpGetORObjectX(XpathKey).click();
		// Thread.sleep(500);

		if (!(fnpCheckElementDisplayedImmediately(XpathOptionsKey))) {
			fnpMymsg("Here assumption is that list is not expanded.");
			fnpGetORObjectX(XpathKey).click();
		} else {
			// This will work when try again in 2nd chance, first close
			// already opened one and then click again to expand it.
			fnpMymsg("Here assumption is that list is already expanded so first close and then open it again.");
			fnpGetORObjectX(XpathKey).click();
			Thread.sleep(1000);
			fnpGetORObjectX(XpathKey).click();
		}

		fnpWaitForVisible(XpathOptionsKey);

		List<WebElement> allActualValuesList = driver
				.findElements(By.xpath(OR.getProperty(XpathOptionsKey) + "/div/ul/li"));
		int totalValues = allActualValuesList.size();
		if (!(totalValues > 0)) {
			throw new Exception("Drop down is blank --" + XpathKey);

		}

		if ((totalValues == 1)) {
			String onlyGivenValue = driver.findElement(By.xpath(OR.getProperty(XpathOptionsKey) + "/div/ul/li[1]"))
					.getText();
			if ((onlyGivenValue.toLowerCase().contains("select")) || (onlyGivenValue.trim().equalsIgnoreCase(""))) {
				throw new Exception("Drop down is blank --" + XpathKey);
			}

		}

		// String listValue = OR.getProperty(XpathOptionsKey) + "/div/ul/li[" + liNo +
		// "]";

		int valuesSize = values.size();

		String expectedTemp;
		String actualTemp;
		boolean found;
		Actions act = new Actions(driver);
		for (int i = 0; i < valuesSize; i++) {
			found = false;
			expectedTemp = values.get(i).trim();
			for (int j = 0; j < totalValues; j++) {
				actualTemp = allActualValuesList.get(j).getText().trim();
				fnpMymsg("Current value is -'" + actualTemp + "' and expected value is -'" + expectedTemp + "'.");
				if (actualTemp.contains(expectedTemp)) {
					found = true;
					fnpMymsg("Current and expected values are matched.");
					break;
				} else {
					if ((j + 1) != valuesSize) {
						fnpMymsg("Current and expected values are not matched, so moving to next value in drop down.");
						act.moveToElement(allActualValuesList.get(j)).sendKeys(Keys.ARROW_DOWN).build().perform();
						Thread.sleep(500);
					}

				}
			}

			if (found) {
				msg = " Finally,this value '" + expectedTemp + "' is present in the drop down. ";
				fnpMymsg(msg);
			} else {
				msg = " Finally,this value '" + expectedTemp + "' is NOT present in the drop down.";
				fnpMymsg(msg);
				throw new Exception(msg);
			}

		}

	}

	/***********
	 * To select the value from prime faces list that contains matching value
	 *************/
	public static void fnpPFListModify_VerifyNoOfValuesInTheDropDown(String XpathKey, int count) throws Throwable {

		String xpathforLabel = OR.getProperty(XpathKey);
		if (xpathforLabel.contains("~")) {
			xpathforLabel = xpathforLabel.split("~")[1];
		}

		String xpathforPanel = xpathforLabel.replace("_label", "_panel");

		fnpWaitForVisible(XpathKey);
		fnpWaitTillVisiblityOfElementNClickable_OR(XpathKey);
		// fnpGetORObjectX(XpathKey).click();

		if (!(fnpCheckElementDisplayedImmediately_NOR(xpathforPanel))) {
			fnpMymsg("Here assumption is that list is not expanded.");
			fnpGetORObjectX___NOR(xpathforLabel).click();
		} else {
			// This will work when try again in 2nd chance, first close
			// already opened one and then click again to expand it.
			fnpMymsg("Here assumption is that list is already expanded so first close and then open it again.");
			// fnpClick_NOR_withoutWait(xpathforLabel);
			fnpGetORObjectX___NOR(xpathforLabel).click();
			Thread.sleep(1000);
			// fnpClick_NOR_withoutWait(xpathforLabel);
			fnpGetORObjectX___NOR(xpathforLabel).click();
		}

		// fnpWaitForVisible(XpathOptionsKey);
		fnpWaitForVisible_NotInOR(xpathforPanel);

		// Thread.sleep(500);

		String listValuesXpath = xpathforPanel + "/div/ul/li";
		int listValuesSize = driver.findElements(By.xpath(listValuesXpath)).size();

		if (listValuesSize != count) {
			msg = "No. of values in the drop down are not equal to '" + count + "'. Expected count should be '" + count
					+ "' but actual is '" + listValuesSize + "'. ";
			fnpMymsg(msg);
			throw new Exception(msg);

		} else {
			msg = "No. of values in the drop down are  equal to '" + count + "'. Expected count should be '" + count
					+ "' but actual is '" + listValuesSize + "'. ";
			fnpMymsg(msg);
		}

	}

	/*********** To count no. of values in the prime faces list *************/
	public static int fnpPFListModify_CountNoOfValuesInTheDropDown(String XpathKey) throws Throwable {

		String xpathforLabel = OR.getProperty(XpathKey);
		if (xpathforLabel.contains("~")) {
			xpathforLabel = xpathforLabel.split("~")[1];
		}

		String xpathforPanel = xpathforLabel.replace("_label", "_panel");

		fnpWaitForVisible(XpathKey);
		fnpWaitTillVisiblityOfElementNClickable_OR(XpathKey);
		// fnpGetORObjectX(XpathKey).click();

		if (!(fnpCheckElementDisplayedImmediately_NOR(xpathforPanel))) {
			fnpMymsg("Here assumption is that list is not expanded.");
			fnpGetORObjectX___NOR(xpathforLabel).click();
		} else {
			// This will work when try again in 2nd chance, first close
			// already opened one and then click again to expand it.
			fnpMymsg("Here assumption is that list is already expanded so first close and then open it again.");
			// fnpClick_NOR_withoutWait(xpathforLabel);
			fnpGetORObjectX___NOR(xpathforLabel).click();
			Thread.sleep(1000);
			// fnpClick_NOR_withoutWait(xpathforLabel);
			fnpGetORObjectX___NOR(xpathforLabel).click();
		}

		// fnpWaitForVisible(XpathOptionsKey);
		fnpWaitForVisible_NotInOR(xpathforPanel);

		// Thread.sleep(500);

		String listValuesXpath = xpathforPanel + "/div/ul/li";
		// int listValuesSize = driver.findElements(By.xpath(listValuesXpath)).size();

		List<WebElement> listValues = driver.findElements(By.xpath(listValuesXpath));
		int listValuesSize = listValues.size();

		if (listValues.get(0).getText().trim().toLowerCase().contains("select")) {
			listValuesSize = listValuesSize - 1;
		}

		return listValuesSize;

	}

	/*********** To count no. of values in the prime faces list *************/
	public static ArrayList fnpPFList_FetchAllValuesPresent(String XpathKey) throws Throwable {

		String xpathforLabel = OR.getProperty(XpathKey);
		if (xpathforLabel.contains("~")) {
			xpathforLabel = xpathforLabel.split("~")[1];
		}

		String xpathforPanel = xpathforLabel.replace("_label", "_panel");

		fnpWaitForVisible(XpathKey);
		fnpWaitTillVisiblityOfElementNClickable_OR(XpathKey);
		// fnpGetORObjectX(XpathKey).click();

		if (!(fnpCheckElementDisplayedImmediately_NOR(xpathforPanel))) {
			fnpMymsg("Here assumption is that list is not expanded.");
			fnpGetORObjectX___NOR(xpathforLabel).click();
		} else {
			// This will work when try again in 2nd chance, first close
			// already opened one and then click again to expand it.
			fnpMymsg("Here assumption is that list is already expanded so first close and then open it again.");
			// fnpClick_NOR_withoutWait(xpathforLabel);
			fnpGetORObjectX___NOR(xpathforLabel).click();
			Thread.sleep(1000);
			// fnpClick_NOR_withoutWait(xpathforLabel);
			fnpGetORObjectX___NOR(xpathforLabel).click();
		}

		// fnpWaitForVisible(XpathOptionsKey);
		fnpWaitForVisible_NotInOR(xpathforPanel);

		// Thread.sleep(500);

		String listValuesXpath = xpathforPanel + "/div/ul/li";
		// int listValuesSize = driver.findElements(By.xpath(listValuesXpath)).size();

		ArrayList<String> stringListValues = new ArrayList<String>();
		List<WebElement> listValuesElement = driver.findElements(By.xpath(listValuesXpath));
		int listValuesSize = listValuesElement.size();

		if (listValuesElement.get(0).getText().trim().toLowerCase().contains("select")) {
			listValuesSize = listValuesSize - 1;
		}

		if (listValuesSize == 0) {
			msg = "This drop down is blank.";
			fnpMymsg(msg);
			throw new Exception(msg);

		} else {
			fnpMymsg("No. of values present are --" + listValuesSize);
		}

		String temp;
		for (int i = 0; i < listValuesElement.size(); i++) {
			temp = listValuesElement.get(i).getText().trim();
			if (temp.toLowerCase().contains("select")) {
				// ignore this value
			} else {
				stringListValues.add(temp);
			}
		}

		return stringListValues;

	}

	/*********** Function to take screenshots *************/
	public static void fnpDesireScreenshot1(String ImageName) {

		try {
			SShots = SShots + 1;
			ImageName = fnpRemoveInvalidCharactersFromSavingFileName(ImageName);
			String updatedImageName = ImageName + SShots + ".png";
			File scrnsht = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrnsht,
					new File(System.getProperty("user.dir") + screenshots_path + "//screenshots_" + currentSuiteCode
							+ "//" + currentScriptCode + "//"
							+ updatedImageName));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/****************
	 * Taking full screenshot with url, otherwise if we use this
	 * ((TakesScreenshot) driver).getScreenshotAs then url top section is not
	 * being captured 24-03-2017
	 ***************/
	public static void fnpDesireScreenshot(String ImageName) {

		try {
			SShots = SShots + 1;
			ImageName = fnpRemoveInvalidCharactersFromSavingFileName(ImageName);
			fnpMymsg("error screenshot name is---" + ImageName);
			String updatedImageName = ImageName + "--" + SShots + ".png";
			// FileUtils.forceMkdir(new File((System.getProperty("user.dir") +
			// screenshots_path + "//screenshots_" + currentSuiteCode + "//" +
			// currentScriptCode + "//")));

			File screenshot_folder = new File((System.getProperty("user.dir") + screenshots_path + "//screenshots_"
					+ currentSuiteCode + "//" + currentScriptCode));
			if (!screenshot_folder.exists()) {
				FileUtils.forceMkdir(screenshot_folder);
			}

			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			Rectangle screenRectangle = new Rectangle(screenSize);
			Robot robot = new Robot();
			BufferedImage image = robot.createScreenCapture(screenRectangle);

			ImageIO.write(image, "png", new File(
					(System.getProperty("user.dir") + screenshots_path + "//screenshots_" + currentSuiteCode + "//"
							+ currentScriptCode + "//" + updatedImageName)));
		} catch (Throwable t) {
			t.printStackTrace();
		}

	}

	//
	// url section is not coming, so not going to use this since 24-03-2017
	/***********
	 * Function to take screenshots
	 * 
	 * @throws Throwable
	 *************/
	public static void fnpDesireScreenshot_old(String ImageName) throws Throwable {

		try {
			SShots = SShots + 1;

			ImageName = fnpRemoveInvalidCharactersFromSavingFileName(ImageName);
			String updatedImageName = ImageName + SShots + ".png";
			fnpMymsg("error screenshot_old name is---" + updatedImageName);

			File screenshot_folder = new File((System.getProperty("user.dir") + screenshots_path + "//screenshots_"
					+ currentSuiteCode + "//" + currentScriptCode));
			if (!screenshot_folder.exists()) {
				FileUtils.forceMkdir(screenshot_folder);
			}

			File scrnsht = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrnsht,
					new File(System.getProperty("user.dir") + screenshots_path + "//screenshots_" + currentSuiteCode
							+ "//" + currentScriptCode + "//"
							+ updatedImageName));

			updatedImageName = ImageName + (SShots + 1) + "_using_AShot.png";
			Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
					.takeScreenshot(driver);
			ImageIO.write(screenshot.getImage(), "PNG",
					new File(System.getProperty("user.dir") + screenshots_path + "//screenshots_" + currentSuiteCode
							+ "//" + currentScriptCode + "//"
							+ updatedImageName));

		} catch (Exception e) {
			e.printStackTrace();
			// throw new Exception(e.getMessage()+" --->\n\n\n\n \"" + e.getStackTrace());

		}

	}

	/*********** Function to take screenshots in Proposal *************/
	public static void fnpDesireScreenshotProposal(String ImageName) {

		try {
			SShots = SShots + 1;
			ImageName = fnpRemoveInvalidCharactersFromSavingFileName(ImageName);
			String updatedImageName = ImageName + SShots + ".png";
			File scrnsht = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrnsht,
					new File(System.getProperty("user.dir") + screenshots_path + "//screenshots_" + currentSuiteCode
							+ "//" + currentScriptCode + "//"
							+ updatedImageName));

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	/*********** Function to take screenshots *************/
	public static void fnpDesireScreenshot_Advance(String ImageName, String folderPath) {

		try {
			ImageName = fnpRemoveInvalidCharactersFromSavingFileName(ImageName);
			String updatedImageName = ImageName + ".png";
			File scrnsht = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String CompleteName = System.getProperty("user.dir") + folderPath + updatedImageName;
			FileUtils.copyFile(scrnsht, new File(CompleteName));

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	/***********
	 * Function to delete screenshots directory as soon as execution starts
	 *************/
	public void fnpDeleteScreenshotsDirectory() throws IOException {
		FileUtils.deleteDirectory(new File(System.getProperty("user.dir") + screenshots_path + "//screenshots_WO//"));
		FileUtils.deleteDirectory(new File(System.getProperty("user.dir") + screenshots_path + "//screenshots_PO//"));

	}

	/***********
	 * Function to fetch and return data from Standard Search Table
	 *************/
	public static String fnpFetchFromStSearchTable(int row, int col) throws Exception {
		int i = 1;
		String errorMsg = null;

		while (i < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {

			i++;

			try {

				return driver
						.findElement(
								By.xpath(OR.getProperty("StandardSearchTable") + "/tr[" + row + "]/td[" + col + "]"))
						.getText();

			} catch (Throwable t) {
				errorMsg = t.getMessage();
				continue;
			}

		}

		fnpDesireScreenshot("FetchingDataFail");
		errorMsg = errorMsg + "Unable to fetch data from Standard Table element [StandardSearchTable] for row and col ["
				+ row + "," + col
				+ "],plz see screenshot [FetchingDataFail" + SShots + "]";
		throw new Exception(errorMsg);

	}

	/***********
	 * Function to fetch and return data from Standard Search Table
	 *************/
	public static String fnpFetchFromStSearchTable(int row, String colName) throws Exception {
		int i = 1;
		int col = 0;
		String errorMsg = null;

		while (i < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {

			i++;

			try {
				col = fnpFindColumnIndex("StandardSearchTableHeader", colName);

				return driver
						.findElement(
								By.xpath(OR.getProperty("StandardSearchTable") + "/tr[" + row + "]/td[" + col + "]"))
						.getText();

			} catch (Throwable t) {
				errorMsg = t.getMessage();
				continue;
			}

		}

		fnpDesireScreenshot("FetchingDataFail");
		errorMsg = errorMsg + "Unable to fetch data from Standard Table element [StandardSearchTable] for row and col ["
				+ row + "," + col
				+ "],plz see screenshot [FetchingDataFail" + SShots + "]";
		throw new Exception(errorMsg);

	}

	/*********** Function to fetch and return data from a Table *************/
	public static String fnpFetchFromTable(String TableXpathKey, int row, int col) throws Exception {

		int retries = 0;
		while (true) {

			try {

				WebDriverWait wait3 = new WebDriverWait(driver,
						Integer.parseInt(CONFIG.getProperty("genMax_waitTime")));
				WebElement element3 = wait3
						.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(TableXpathKey))));

				String[] idString = OR.getProperty(TableXpathKey).split("'", -1);
				String CssExpression = "tbody[id='" + idString[1] + "'] tr:nth-child(" + row + ") td:nth-child(" + col
						+ ")";

				String value = driver.findElement(By.cssSelector(CssExpression)).getText().trim();
				return value;

			}

			catch (org.openqa.selenium.StaleElementReferenceException e) {
				if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					retries++;
					fnpMymsg(retries + "In staleElementException catch block of fnpFetchFromTable function for "
							+ TableXpathKey);
					continue;
				} else {
					throw e;
				}
			}

			catch (Throwable t) {
				fnpDesireScreenshot("FetchingDataFail");
				String errorMsg = t.getMessage();
				throw new Exception("Unable to fetch data from  Table having xpath [" + TableXpathKey
						+ "] for row and col [" + row + "," + col
						+ "],plz see screenshot [FetchingDataFail" + SShots + "]  and this is due to --" + errorMsg);

			}

		}
	}

	/*********** Function to fetch and return data from a Table *************/
	public static String fnpFetchFromTable_NOR(String TableXpath, int row, int col) throws Exception {

		int retries = 0;
		while (true) {

			try {

				WebDriverWait wait3 = new WebDriverWait(driver,
						Integer.parseInt(CONFIG.getProperty("genMax_waitTime")));
				WebElement element3 = wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(TableXpath)));

				String[] idString = TableXpath.split("'", -1);
				String CssExpression = "tbody[id='" + idString[1] + "'] tr:nth-child(" + row + ") td:nth-child(" + col
						+ ")";

				String value = driver.findElement(By.cssSelector(CssExpression)).getText().trim();
				return value;

			}

			catch (org.openqa.selenium.StaleElementReferenceException e) {
				if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					retries++;
					fnpMymsg(retries + "In staleElementException catch block of fnpFetchFromTable function for "
							+ TableXpath);
					continue;
				} else {
					throw e;
				}
			}

			catch (Throwable t) {
				fnpDesireScreenshot("FetchingDataFail");
				String errorMsg = t.getMessage();
				throw new Exception("Unable to fetch data from  Table having xpath [" + TableXpath
						+ "] for row and col [" + row + "," + col
						+ "],plz see screenshot [FetchingDataFail" + SShots + "]  and this is due to --" + errorMsg);

			}

		}
	}

	public static void fnpClickInTable_NOR(String TableXpath, int row, int col) throws Exception {

		int retries = 0;
		while (true) {

			try {

				WebDriverWait wait3 = new WebDriverWait(driver,
						Integer.parseInt(CONFIG.getProperty("genMax_waitTime")));
				WebElement element3 = wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(TableXpath)));

				String[] idString = TableXpath.split("'", -1);
				String CssExpression = "tbody[id='" + idString[1] + "'] tr:nth-child(" + row + ") td:nth-child(" + col
						+ ")";

				WebElement wb = driver.findElement(By.cssSelector(CssExpression));
				List<WebElement> anchorLinks = wb.findElements(By.xpath(".//a"));
				if (anchorLinks.size() > 0) {
					anchorLinks.get(0).click();

				} else {
					driver.findElement(By.cssSelector(CssExpression)).click();
				}

				break;

			}

			catch (org.openqa.selenium.StaleElementReferenceException e) {
				if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					retries++;
					fnpMymsg(retries + "In staleElementException catch block of fnpFetchFromTable function for "
							+ TableXpath);
					continue;
				} else {
					throw e;
				}
			}

			catch (Throwable t) {
				fnpDesireScreenshot("FetchingDataFail");
				String errorMsg = t.getMessage();
				throw new Exception("Unable to fetch data from  Table having xpath [" + TableXpath
						+ "] for row and col [" + row + "," + col
						+ "],plz see screenshot [FetchingDataFail" + SShots + "]  and this is due to --" + errorMsg);

			}

		}
	}

	/*********** Function to fetch and return data from a Table *************/
	public static String fnpFetchFromTable_NOR_TraversingFromVariousNodes(String TableXpath, int row, int col)
			throws Exception {

		int retries = 0;
		while (true) {

			try {

				WebDriverWait wait3 = new WebDriverWait(driver,
						Integer.parseInt(CONFIG.getProperty("genMax_waitTime")));
				WebElement element3 = wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(TableXpath)));

				String xpath = TableXpath + "/tr[" + row + "]/td[" + col + "]";
				String value = driver.findElement(By.xpath(xpath)).getText().trim();
				return value;

			}

			catch (org.openqa.selenium.StaleElementReferenceException e) {
				if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					retries++;
					fnpMymsg(retries + "In staleElementException catch block of fnpFetchFromTable function for "
							+ TableXpath);
					continue;
				} else {
					throw e;
				}
			}

			catch (Throwable t) {
				fnpDesireScreenshot("FetchingDataFail");
				String errorMsg = t.getMessage();
				throw new Exception("Unable to fetch data from  Table having xpath [" + TableXpath
						+ "] for row and col [" + row + "," + col
						+ "],plz see screenshot [FetchingDataFail" + SShots + "]  and this is due to --" + errorMsg);

			}

		}
	}

	/*********** Function to count no. of rows in a table *************/
	public static int fnpCountRowsInTable(String TableXpathKey) throws Exception {
		try {

			String newXpath = OR.getProperty(TableXpathKey) + "/tr";
			int NoOfRows = fnpGetORObject_list_NOR(newXpath, Integer.parseInt(CONFIG.getProperty("implicitWaitTime")))
					.size();

			if (NoOfRows == 1) {

				if (fnpCheckElementPresenceImmediately_NotInOR(OR.getProperty(TableXpathKey) + "/tr/td[2]")) {
					// nothing to do

				} else {
					NoOfRows = NoOfRows - 1;

				}

			}

			fnpMymsg("@  ---Total rows are---" + NoOfRows);
			return NoOfRows;

		} catch (Throwable t) {
			fnpDesireScreenshot("CountRowFailFrom" + TableXpathKey);
			String errorMsg = t.getMessage();
			throw new Exception("Unable to count rows from  Table having xpath [" + TableXpathKey
					+ "],plz see screenshot [CountRowFailFrom" + TableXpathKey + SShots
					+ "]and this is due to --" + errorMsg);

		}
	}

	/*********** Function to count no. of rows in a table *************/
	public static int fnpCountRowsInTable_NOR(String TableXpath) throws Exception {
		try {

			String newXpath = TableXpath + "/tr";
			int NoOfRows = fnpGetORObject_list_NOR(newXpath, Integer.parseInt(CONFIG.getProperty("implicitWaitTime")))
					.size();

			if (NoOfRows == 1) {

				if (fnpCheckElementPresenceImmediately_NotInOR(TableXpath + "/tr/td[2]")) {
					// nothing to do

				} else {
					NoOfRows = NoOfRows - 1;

				}

			}

			fnpMymsg("@  ---Total rows are---" + NoOfRows);
			return NoOfRows;

		} catch (Throwable t) {
			fnpDesireScreenshot("CountRowFailFrom" + TableXpath);
			String errorMsg = t.getMessage();
			throw new Exception("Unable to count rows from  Table having xpath [" + TableXpath
					+ "],plz see screenshot [CountRowFailFrom" + TableXpath + SShots
					+ "]and this is due to --" + errorMsg);

		}
	}

	/*********** Function to count no. of rows in a Lookup table *************/
	public static int fnpCountRowsIn_LOOKUPTable(String LookupRowsXpathKey) throws Exception {
		try {

			List<WebElement> elements = fnpGetORObject_list(LookupRowsXpathKey,
					Integer.parseInt(CONFIG.getProperty("implicitWaitTime")));

			int NoOfRows = elements.size();
			return NoOfRows;

		} catch (Throwable t) {
			fnpDesireScreenshot("CountRowFailFrom" + LookupRowsXpathKey);
			throw new Exception("Unable to count rows from  Table having xpath [" + LookupRowsXpathKey
					+ "],plz see screenshot [CountRowFailFrom" + LookupRowsXpathKey + SShots
					+ "]");

		}
	}

	/*********** Function to count no. of columns in a table *************/
	public static int fnpCountColumnssInTable(String TableXpathKey) throws Exception {
		try {

			String newXpath = OR.getProperty(TableXpathKey) + "/tbody/tr/td";
			return fnpGetORObject_list_NOR(newXpath, Integer.parseInt(CONFIG.getProperty("implicitWaitTime"))).size();

		} catch (Throwable t) {
			fnpDesireScreenshot("CountColumnFailFrom" + TableXpathKey);
			throw new Exception("Unable to count columns from  Table having xpath [" + TableXpathKey
					+ "],plz see screenshot [CountColumnFailFrom" + TableXpathKey + SShots + "]");

		}
	}

	/*********** To check element's presence *************/
	public static boolean fnpCheckElementPresence(String XpathKey) {

		try {

			if (fnpGetORObject_list(XpathKey, Integer.parseInt(CONFIG.getProperty("implicitWaitTime"))).size() > 0) {
				return true;
			} else {
				return false;
			}

		} catch (Throwable t) {
			return false;
		}

	}

	/***********
	 * To check element's presence when object is not stored in OR
	 *************/
	public static boolean fnpCheckElementPresence_NOR(String Xpath) {

		try {

			if (fnpGetORObject_list_NOR(Xpath, Integer.parseInt(CONFIG.getProperty("implicitWaitTime"))).size() > 0) {
				return true;
			} else {
				return false;
			}

		} catch (Throwable t) {
			return false;
		}

	}

	/*********** To check element is displayed or not *************/
	public static boolean fnpCheckElementDisplayed(String XpathKey) {

		int i = 0;
		try {

			List<WebElement> elementList = fnpGetORObject_list(XpathKey,
					Integer.parseInt(CONFIG.getProperty("implicitWaitTime")));

			for (Iterator iterator = elementList.iterator(); iterator.hasNext();) {

				WebElement webElement = (WebElement) iterator.next();
				if (webElement.isDisplayed()) {
					// return true;
					i = 1;
					break;
				} else {
					// return false;
				}

			}

		} catch (Throwable t) {
			// return false;

		}

		finally {
			driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")),
					TimeUnit.SECONDS);
		}

		if (i == 0) {
			return false;

		} else {
			return true;
		}

	}

	/*********** To check element is displayed immediately or not *************/
	public static boolean fnpCheckElementDisplayedImmediately(String XpathKey) {

		int i = 0;
		try {

			driver.manage().timeouts().implicitlyWait(1, TimeUnit.MILLISECONDS);

			List<WebElement> elementList = fnpGetORObject_list(XpathKey, 0, 500);
			for (Iterator iterator = elementList.iterator(); iterator.hasNext();) {

				WebElement webElement = (WebElement) iterator.next();
				if (webElement.isDisplayed()) {
					// return true;
					i = 1;
					break;
				} else {
					// return false;
				}

			}

		} catch (Throwable t) {
			// return false;

		}

		finally {
			driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")),
					TimeUnit.SECONDS);
		}

		if (i == 0) {
			return false;

		} else {
			return true;
		}

	}

	/*********** To check element is displayed immediately or not *************/
	public static boolean fnpCheckElementDisplayedImmediately_NOR(String Xpath) {

		int i = 0;
		try {

			driver.manage().timeouts().implicitlyWait(1, TimeUnit.MILLISECONDS);

			List<WebElement> elementList = fnpGetORObject_list_NOR(Xpath, 0, 1);

			for (Iterator iterator = elementList.iterator(); iterator.hasNext();) {

				WebElement webElement = (WebElement) iterator.next();
				if (webElement.isDisplayed()) {
					// return true;
					i = 1;
					break;
				} else {
					// return false;
				}

			}

		} catch (Throwable t) {
			// return false;

		}

		finally {
			driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")),
					TimeUnit.SECONDS);
		}

		if (i == 0) {
			return false;

		} else {
			return true;
		}

	}

	/*********** To check element is displayed immediately or not *************/
	public static boolean fnpCheckElementDisplayed(String XpathKey, int maxtimeInSeconds) {

		int i = 0;
		try {

			/*
			 * driver.manage().timeouts().implicitlyWait(maxtimeInSeconds,
			 * TimeUnit.SECONDS);
			 * List<WebElement> elementList = fnpGetORObject_list(XpathKey,
			 * maxtimeInSeconds);
			 * for (Iterator iterator = elementList.iterator(); iterator.hasNext();) {
			 * 
			 * WebElement webElement = (WebElement) iterator.next();
			 * if (webElement.isDisplayed()) {
			 * // return true;
			 * i = 1;
			 * break;
			 * } else {
			 * // return false;
			 * }
			 * 
			 * }
			 * 
			 * 
			 */

			int iWhileCounter = 0;
			while (iWhileCounter < maxtimeInSeconds) {
				iWhileCounter++;
				if (fnpCheckElementDisplayedImmediately(XpathKey)) {
					fnpMymsg("Element with this xpathkey '" + XpathKey + "' is displayed.");
					i = 1;
					break;
				} else {
					Thread.sleep(1000);
					fnpMymsg("Element with this xpathkey '" + XpathKey + "' is not displayed--" + iWhileCounter);
				}
			}

		} catch (Throwable t) {
			// return false;

		}

		finally {
			driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")),
					TimeUnit.SECONDS);
		}

		if (i == 0) {
			return false;

		} else {
			return true;
		}

	}

	/*********** To check element is displayed immediately or not *************/
	public static boolean fnpCheckElementDisplayed2(String XpathKey, int maxtimeInSeconds) {

		int i = 0;
		try {
			fnpWaitForVisible(XpathKey, maxtimeInSeconds);
			i = 1;

		} catch (Throwable t) {
			// return false;

		}

		finally {
			driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")),
					TimeUnit.SECONDS);
		}

		if (i == 0) {
			return false;

		} else {
			return true;
		}

	}

	/*********** To check element is displayed immediately or not *************/
	public static boolean fnpCheckElementDisplayed_NOR(String Xpath, int maxtimeInSeconds) {

		int i = 0;
		try {

			driver.manage().timeouts().implicitlyWait(maxtimeInSeconds, TimeUnit.SECONDS);
			List<WebElement> elementList = fnpGetORObject_list_NOR(Xpath, maxtimeInSeconds);

			for (Iterator iterator = elementList.iterator(); iterator.hasNext();) {

				WebElement webElement = (WebElement) iterator.next();
				if (webElement.isDisplayed()) {
					// return true;
					i = 1;
					break;
				} else {
					// return false;
				}

			}

		} catch (Throwable t) {
			// return false;

		}

		finally {
			driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")),
					TimeUnit.SECONDS);
		}

		if (i == 0) {
			return false;

		} else {
			return true;
		}

	}

	/***************** To check element is displayed or not ******************/
	public static boolean fnpCheckElementEnabledImmediately(String XpathKey) {

		int i = 0;
		try {

			driver.manage().timeouts().implicitlyWait(1, TimeUnit.MILLISECONDS);

			List<WebElement> elementList = fnpGetORObject_list(XpathKey, 0, 1);

			for (Iterator iterator = elementList.iterator(); iterator.hasNext();) {

				WebElement webElement = (WebElement) iterator.next();
				if (webElement.isEnabled()) {
					// return true;
					i = 1;
					break;
				} else {
					// return false;
				}

			}

		} catch (Throwable t) {
			// return false;

		}

		finally {
			driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")),
					TimeUnit.SECONDS);
		}

		if (i == 0) {
			return false;

		} else {
			return true;
		}

	}

	/**************** To check element is displayed or not *************/
	public static boolean fnpCheckElementEnabledImmediately_NOR(String Xpath) {

		int i = 0;
		try {

			driver.manage().timeouts().implicitlyWait(1, TimeUnit.MILLISECONDS);

			List<WebElement> elementList = fnpGetORObject_list_NOR(Xpath, 0, 1);

			for (Iterator iterator = elementList.iterator(); iterator.hasNext();) {

				WebElement webElement = (WebElement) iterator.next();
				if (webElement.isEnabled()) {
					// return true;
					i = 1;
					break;
				} else {
					// return false;
				}

			}

		} catch (Throwable t) {
			// return false;

		}

		finally {
			driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")),
					TimeUnit.SECONDS);
		}

		if (i == 0) {
			return false;

		} else {
			return true;
		}

	}

	/*********** To check element not in OR is displayed or not *************/
	public static boolean fnpCheckElementDisplayed_NOR(String Xpath) {

		int i = 0;
		try {

			List<WebElement> elementList = fnpGetORObject_list_NOR(Xpath, 0, 1);

			for (Iterator iterator = elementList.iterator(); iterator.hasNext();) {

				WebElement webElement = (WebElement) iterator.next();
				if (webElement.isDisplayed()) {
					// return true;
					i = 1;
					break;
				} else {
					// return false;
				}

			}

		} catch (Throwable t) {
			// return false;

		}

		finally {
			// driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")),
			// TimeUnit.SECONDS);
		}

		if (i == 0) {
			return false;

		} else {
			return true;
		}

	}

	/*********** Wait till main loading icon overs *************/
	public static void fnpLoading_wait() throws Throwable {

		fnpLoading_wait_withoutErrorChecking();
		fnpCheckError(" after loading ");

	}

	public static void fnpLoading_wait_specialCase(int maxWaitseconds) throws Throwable {

		fnpLoading_wait_withoutErrorChecking(maxWaitseconds);
		fnpCheckError(" after loading ");

	}

	/*********** Wait till main loading icon overs *************/
	public static void fnpLoading_wait_withoutErrorChecking() throws Throwable {
		int i = 0;

		while (true) {
			if ((fnpCheckElementDisplayedImmediately("ProgressImageIcon"))
					| (fnpCheckElementDisplayedImmediately("LoadingImg"))) {
				fnpMymsg("@@@   main loading is visible - fnpLoading_wait--" + i);

				break;
			} else {

				int maxloopDependOnBrowser = 0;

				if (browserName.equalsIgnoreCase("IE")) {
					// maxloopDependOnBrowser=6;
					// maxloopDependOnBrowser = 4;
					maxloopDependOnBrowser = Integer.parseInt(CONFIG.getProperty("LoadingIconMaxWait"));
					// maxloopDependOnBrowser=Integer.parseInt(CONFIG.getProperty("LoadingIconMaxWait"))+4;
				} else {
					// maxloopDependOnBrowser=3;
					// maxloopDependOnBrowser = 1;
					maxloopDependOnBrowser = Integer.parseInt(CONFIG.getProperty("LoadingIconMaxWait"));
				}

				if (i > maxloopDependOnBrowser) {

					fnpMymsg("@@@    main loading icon is not visible after " + (maxloopDependOnBrowser + 1)
							+ " seconds");
					break;
				} else {
					fnpMymsg("@@@    waiting for main  loading to be visible inside - fnpLoading_wait--" + i);
					Thread.sleep(1000);
				}

				// Thread.sleep(1000); //commenting on 11-07-2018 when we got new user and so to
				// make script run fast, commenting it here and put inside in above else
				// condition
				i++;
			}
		}

		i = 0;
		while (fnpCheckElementPresenceImmediately("BlockedUIScreen")) {
			Thread.sleep(1000);
			i++;
			fnpMymsg("@  ------fnpCheckElementPresenceImmediately 'BlockedUIScreen'------" + i);
			// int maxT=Integer.parseInt(CONFIG.getProperty("loading_Max_waitTime")) ;
			int maxT = Integer.parseInt(CONFIG.getProperty("loadingIcon_Max_waitTime"));
			if (i > maxT) {
				// throw new Exception("Loading is not getting over even after waiting approx.
				// "+maxT+" seconds");
				throw new Exception(CustomizeErrorMsgWhenLoadingNotOver + maxT);
				// break;
			}

		}

		i = 0;
		while (fnpCheckElementDisplayedImmediately("ProgressImageIcon")) {
			Thread.sleep(1000);
			i++;
			fnpMymsg("@  ------fnpCheckElementDisplayedImmediately 'ProgressImageIcon'------" + i);
			fnpMymsg("@@@   -- in loop of loading is visible ---" + i);
			int maxT = Integer.parseInt(CONFIG.getProperty("loadingIcon_Max_waitTime"));
			if (i > maxT) {
				// throw new Exception("Loading is not getting over even after waiting approx.
				// "+maxT+" seconds");
				throw new Exception(CustomizeErrorMsgWhenLoadingNotOver + maxT);
				// break;
			}

		}

		i = 0;
		while (fnpCheckElementDisplayedImmediately("LoadingImg")) {
			fnpMymsg("@@@   while loop when loading is visible now inside - fnpLoading_waitInsideDialogBox--" + i);
			Thread.sleep(1000);
			i++;
			int maxT = Integer.parseInt(CONFIG.getProperty("loadingIcon_Max_waitTime"));
			if (i > maxT) {
				// throw new Exception("Loading is not getting over even after waiting approx.
				// "+maxT+" seconds");
				throw new Exception(CustomizeErrorMsgWhenLoadingNotOver + maxT);
				// break;
			}

		}

	}

	/*********** Wait till main loading icon overs *************/
	public static void fnpLoading_wait_withoutErrorChecking(int maxWaitseconds) throws Throwable {
		int i = 0;

		while (true) {
			if ((fnpCheckElementDisplayedImmediately("ProgressImageIcon"))
					| (fnpCheckElementDisplayedImmediately("LoadingImg"))) {
				fnpMymsg("@@@   main loading is visible - fnpLoading_wait_withoutErrorChecking--" + i);

				break;
			} else {

				int maxloopDependOnBrowser = maxWaitseconds;

				if (i > maxloopDependOnBrowser) {

					fnpMymsg("@@@    main loading icon is not visible after " + (maxloopDependOnBrowser + 1)
							+ " seconds");
					break;
				} else {
					fnpMymsg(
							"@@@    waiting for main  loading to be visible inside - fnpLoading_wait_withoutErrorChecking--"
									+ i);
					Thread.sleep(1000);
				}

				// Thread.sleep(1000); //commenting on 11-07-2018 when we got new user and so to
				// make script run fast, commenting it here and put inside in above else
				// condition
				i++;
			}
		}

		i = 0;
		while (fnpCheckElementPresenceImmediately("BlockedUIScreen")) {
			Thread.sleep(1000);
			i++;
			fnpMymsg("@  ------fnpLoading_wait_withoutErrorChecking 'BlockedUIScreen'------" + i);
			// int maxT=Integer.parseInt(CONFIG.getProperty("loading_Max_waitTime")) ;
			int maxT = Integer.parseInt(CONFIG.getProperty("loadingIcon_Max_waitTime"));
			if (i > maxT) {
				// throw new Exception("Loading is not getting over even after waiting approx.
				// "+maxT+" seconds");
				throw new Exception(CustomizeErrorMsgWhenLoadingNotOver + maxT);
				// break;
			}

		}

		i = 0;
		while (fnpCheckElementDisplayedImmediately("ProgressImageIcon")) {
			Thread.sleep(1000);
			i++;
			fnpMymsg("@  ------fnpLoading_wait_withoutErrorChecking 'ProgressImageIcon'------" + i);
			fnpMymsg("@@@   -- in loop of loading is visible ---" + i);
			int maxT = Integer.parseInt(CONFIG.getProperty("loadingIcon_Max_waitTime"));
			if (i > maxT) {
				// throw new Exception("Loading is not getting over even after waiting approx.
				// "+maxT+" seconds");
				throw new Exception(CustomizeErrorMsgWhenLoadingNotOver + maxT);
				// break;
			}

		}

		i = 0;
		while (fnpCheckElementDisplayedImmediately("LoadingImg")) {
			fnpMymsg(
					"@@@   while loop when loading is visible now inside - fnpLoading_wait_withoutErrorChecking--" + i);
			Thread.sleep(1000);
			i++;
			int maxT = Integer.parseInt(CONFIG.getProperty("loadingIcon_Max_waitTime"));
			if (i > maxT) {
				// throw new Exception("Loading is not getting over even after waiting approx.
				// "+maxT+" seconds");
				throw new Exception(CustomizeErrorMsgWhenLoadingNotOver + maxT);
				// break;
			}

		}

	}

	/*************
	 * only check for loading icon (not waits for it) and only for iPulse loading
	 * icon
	 *****************************************/
	public static void fnpLoading_wait_CheckLoadingIconOnlyIniPulse() throws Throwable {
		int i = 0;
		while (fnpCheckElementPresenceImmediately("BlockedUIScreen")) {
			Thread.sleep(1000);
			i++;
			fnpMymsg("@  ------fnpCheckElementPresenceImmediately 'BlockedUIScreen'------" + i);
			int maxT = Integer.parseInt(CONFIG.getProperty("loadingIcon_Max_waitTime"));
			if (i > maxT) {
				// throw new Exception("Loading is not getting over even after waiting approx.
				// "+maxT+" seconds");
				throw new Exception(CustomizeErrorMsgWhenLoadingNotOver + maxT);
				// break;
			}

		}

		i = 0;
		while (fnpCheckElementDisplayedImmediately("ProgressImageIcon")) {
			Thread.sleep(1000);
			i++;
			fnpMymsg("@  ------fnpCheckElementDisplayedImmediately 'ProgressImageIcon'------" + i);
			fnpMymsg("@@@   -- in loop of loading is visible ---" + i);
			int maxT = Integer.parseInt(CONFIG.getProperty("loadingIcon_Max_waitTime"));
			if (i > maxT) {
				// throw new Exception("Loading is not getting over even after waiting approx.
				// "+maxT+" seconds");
				throw new Exception(CustomizeErrorMsgWhenLoadingNotOver + maxT);
				// break;
			}

		}

		i = 0;
		while (fnpCheckElementDisplayedImmediately("LoadingImg")) {
			fnpMymsg(
					"@@@   while loop when loading is visible now inside - fnpLoading_wait_CheckLoadingIconOnlyIniPulse--"
							+ i);
			Thread.sleep(1000);
			i++;
			int maxT = Integer.parseInt(CONFIG.getProperty("loadingIcon_Max_waitTime"));
			if (i > maxT) {
				// throw new Exception("Loading is not getting over even after waiting approx.
				// "+maxT+" seconds");
				throw new Exception(CustomizeErrorMsgWhenLoadingNotOver + maxT);
				// break;
			}

		}

	}

	/*********** Wait till inner loading icon overs *************/
	public static void fnpLoading_waitInsideDialogBox() throws Throwable {
		int i = 0;

		while (true) {
			if (fnpCheckElementDisplayedImmediately("LoadingImg")) {

				fnpMymsg("@@@   loading is visible - fnpLoading_waitInsideDialogBox--" + i);

				break;
			} else {
				fnpMymsg("@@@    waiting for loading visible inside - fnpLoading_waitInsideDialogBox--" + i);

				Thread.sleep(1000);
				i++;
				if (i > 3) {

					break;
				}
			}
		}

		i = 0;
		while (fnpCheckElementDisplayedImmediately("LoadingImg")) {
			fnpMymsg("@@@   while loop when loading is visible now inside - fnpLoading_waitInsideDialogBox--" + i);
			Thread.sleep(1000);
			i++;
			int maxT = Integer.parseInt(CONFIG.getProperty("loadingIcon_Max_waitTime"));
			if (i > maxT) {
				// throw new Exception("Loading is not getting over even after waiting approx.
				// "+maxT+" seconds");
				throw new Exception(CustomizeErrorMsgWhenLoadingNotOver + maxT);
				// break;
			}

		}

		i = 0;
		while (fnpCheckElementDisplayedImmediately("ProgressImageIcon")) {
			Thread.sleep(1000);
			i++;
			fnpMymsg("@  ---while loop when---fnpCheckElementDisplayedImmediately 'ProgressImageIcon'------" + i);
			int maxT = Integer.parseInt(CONFIG.getProperty("loadingIcon_Max_waitTime"));
			if (i > maxT) {
				// throw new Exception("Loading is not getting over even after waiting approx.
				// "+maxT+" seconds");
				throw new Exception(CustomizeErrorMsgWhenLoadingNotOver + maxT);
				// break;
			}

		}

		fnpCheckError(" after loading ");

	}

	// Wait in lookup loading icon
	/***********
	 * Wait in lookup loading icon
	 * 
	 * @throws Throwable
	 *************/
	public static void fnpLKPloading_wait() throws Throwable {
		int i = 0;
		int j = 0;
		// Thread.sleep(10000);

		while (!fnpGetORObjectX("LookupLoadingImg").isDisplayed() & j < 200) {
			Thread.sleep(10);
			j++;

		}

		while (fnpGetORObjectX("LookupLoadingImg").isDisplayed() && i < 100) {
			Thread.sleep(1000);
			i++;
		}

		Thread.sleep(1000);

	}

	/***********
	 * To check element is present or not immmediately
	 * 
	 * @throws Throwable
	 ************/
	// @SuppressWarnings({"finally","deprecation"})
	@SuppressWarnings({ "deprecation" })
	public static boolean fnpCheckElementPresenceImmediately(String XpathKey) {
		boolean result = false;

		try {

			driver.manage().timeouts().implicitlyWait(1, TimeUnit.MILLISECONDS);

			if (fnpGetORObject_list(XpathKey, 0, 1).size() > 0) {
				result = true;

			} else {
				result = false;
			}

		} catch (Throwable t) {
			if (t.getMessage().contains("null")) {
				fnpMymsg("@@@@@@   ...some null value is passing.....may be this --" + XpathKey);
			}
			result = false;
		}

		finally {
			driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")),
					TimeUnit.SECONDS);

			return result;
		}
	}

	/*********** To check element is present immmediately or not in OR ************/

	public static boolean fnpCheckElementPresenceImmediately_NotInOR(String Xpath) {
		boolean result = false;

		try {
			driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
			if (fnpGetORObject_list_NOR(Xpath, 0, 500).size() > 0) {
				result = true;

			} else {
				result = false;
			}

		} catch (Throwable t) {
			if (t.getMessage().contains("null")) {
				fnpMymsg("@@@@@@   ...some null value is passing.....may be this --" + Xpath);
			}
			result = false;
		}

		finally {
			driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")),
					TimeUnit.SECONDS);

			return result;
		}
	}

	/*********** Wait till elemenet comes into clickable state ************/
	public static void fnpWaitTillClickable_deprecatedOn_19_12_2017(String XpathKey) throws Throwable {

		fnpWaitForVisible(XpathKey);
		try {

			WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(CONFIG.getProperty("genMax_waitTime")));

			if (OR.getProperty(XpathKey).contains("~")) {

				String[] s1 = OR.getProperty(XpathKey).split("~");
				String locatorValue = s1[1];
				String locator = s1[0];
				if (locator.contains("id")) {// id
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
					wait.until(ExpectedConditions.elementToBeClickable(By.id(locatorValue)));
				} else {
					if (locator.contains("name")) {// name
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue)));
						wait.until(ExpectedConditions.elementToBeClickable(By.name(locatorValue)));

					} else {
						if (locator.toLowerCase().contains("linktext")) {// linkText
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locatorValue)));
							wait.until(ExpectedConditions.elementToBeClickable(By.linkText(locatorValue)));

						} else {
							if (locator.toLowerCase().contains("partialLinkText")) {// partialLinkText
								wait.until(ExpectedConditions
										.visibilityOfElementLocated(By.partialLinkText(locatorValue)));
								wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(locatorValue)));

							} else {
								if (locator.toLowerCase().contains("tagName")) {// tagName
									wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(locatorValue)));
									wait.until(ExpectedConditions.elementToBeClickable(By.tagName(locatorValue)));

								} else {
									if (locator.toLowerCase().contains("className")) {// className
										wait.until(ExpectedConditions
												.visibilityOfElementLocated(By.className(locatorValue)));
										wait.until(ExpectedConditions.elementToBeClickable(By.className(locatorValue)));

									} else {
										if (locator.toLowerCase().contains("css")) {// cssSelector
											wait.until(ExpectedConditions
													.visibilityOfElementLocated(By.cssSelector(locatorValue)));
											wait.until(ExpectedConditions
													.elementToBeClickable(By.cssSelector(locatorValue)));

										} else {

											if (locator.toLowerCase().contains("xpath")) {// xpath
												wait.until(ExpectedConditions
														.visibilityOfElementLocated(By.xpath(locatorValue)));
												wait.until(ExpectedConditions
														.elementToBeClickable(By.xpath(locatorValue)));

											} else {

												/******
												 * By default Xpath will be
												 * assumed
												 *****/
												wait.until(ExpectedConditions
														.visibilityOfElementLocated(By.xpath(locatorValue)));
												wait.until(ExpectedConditions
														.elementToBeClickable(By.xpath(locatorValue)));

											}
										}
									}
								}
							}
						}
					}
				}

			} else {

				WebDriverWait wait3 = new WebDriverWait(driver,
						Integer.parseInt(CONFIG.getProperty("genMax_waitTime")));
				WebElement element3 = wait3
						.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(XpathKey))));
			}
		} catch (Exception e) {
			throw new Exception("fnpWaitTillClickable is failed for xpathKey [" + XpathKey + "]. ");
		}
	}

	/*********** Wait till elemenet comes into clickable state ************/
	public static void fnpWaitTillClickable(String XpathKey) throws Throwable {

		fnpWaitForVisible(XpathKey);
		try {

			WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(CONFIG.getProperty("genMax_waitTime")));

			if (OR.getProperty(XpathKey).contains("~")) {

				String[] s1 = OR.getProperty(XpathKey).split("~");
				String locatorValue = s1[1];
				String locator = s1[0];

				switch (locator) {
					case "id":
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
						wait.until(ExpectedConditions.elementToBeClickable(By.id(locatorValue)));
						break;

					case "name":
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue)));
						wait.until(ExpectedConditions.elementToBeClickable(By.name(locatorValue)));
						break;

					case "linkText":
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locatorValue)));
						wait.until(ExpectedConditions.elementToBeClickable(By.linkText(locatorValue)));
						break;

					case "partialLinkText":
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(locatorValue)));
						wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(locatorValue)));
						break;

					case "tagName":
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(locatorValue)));
						wait.until(ExpectedConditions.elementToBeClickable(By.tagName(locatorValue)));
						break;

					case "className":
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(locatorValue)));
						wait.until(ExpectedConditions.elementToBeClickable(By.className(locatorValue)));
						break;

					case "cssSelector":
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locatorValue)));
						wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(locatorValue)));
						break;

					case "xpath":
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorValue)));
						break;

					default:
						/****** By default Xpath will be assumed *****/
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorValue)));
						break;

				}

			} else {

				WebDriverWait wait3 = new WebDriverWait(driver,
						Integer.parseInt(CONFIG.getProperty("genMax_waitTime")));
				WebElement element3 = wait3
						.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(XpathKey))));

			}

		} catch (Exception e) {
			throw new Exception("fnpWaitTillClickable is failed for xpathKey [" + XpathKey + "]. ");
		}
	}

	/*********** wait till element get visible ************/
	public static void fnpWaitForVisible_deprecatedOn_19_12_2017(String XpathKey) throws Throwable {

		fnpIpulseDuringLoading();

		int whileCounter = 0;
		while (true) {
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			try {
				fnpMymsg("@  ---starting of fnpWaitForVisible for element ---" + XpathKey + " ---for chance --"
						+ whileCounter);

				Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
						.withTimeout((Long.parseLong(CONFIG.getProperty("genMax_waitTime"))), TimeUnit.SECONDS)
						.pollingEvery(1, TimeUnit.SECONDS)
						.ignoring(org.openqa.selenium.StaleElementReferenceException.class)
						.ignoring(org.openqa.selenium.WebDriverException.class)
						.ignoring(org.openqa.selenium.InvalidSelectorException.class)
						.ignoring(org.openqa.selenium.NoSuchElementException.class);

				if (OR.getProperty(XpathKey).contains("~")) {

					String[] s1 = OR.getProperty(XpathKey).split("~");
					String locatorValue = s1[1];
					String locator = s1[0];
					if (locator.contains("id")) {// id
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
						wait.until(ExpectedConditions.elementToBeClickable(By.id(locatorValue)));
					} else {
						if (locator.contains("name")) {// name
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue)));
							wait.until(ExpectedConditions.elementToBeClickable(By.name(locatorValue)));

						} else {
							if (locator.toLowerCase().contains("linktext")) {// linkText
								wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locatorValue)));
								wait.until(ExpectedConditions.elementToBeClickable(By.linkText(locatorValue)));

							} else {
								if (locator.toLowerCase().contains("partialLinkText")) {// partialLinkText
									wait.until(ExpectedConditions
											.visibilityOfElementLocated(By.partialLinkText(locatorValue)));
									wait.until(
											ExpectedConditions.elementToBeClickable(By.partialLinkText(locatorValue)));

								} else {
									if (locator.toLowerCase().contains("tagName")) {// tagName
										wait.until(ExpectedConditions
												.visibilityOfElementLocated(By.tagName(locatorValue)));
										wait.until(ExpectedConditions.elementToBeClickable(By.tagName(locatorValue)));

									} else {
										if (locator.toLowerCase().contains("className")) {// className
											wait.until(ExpectedConditions
													.visibilityOfElementLocated(By.className(locatorValue)));
											wait.until(ExpectedConditions
													.elementToBeClickable(By.className(locatorValue)));

										} else {
											if (locator.toLowerCase().contains("css")) {// cssSelector
												wait.until(ExpectedConditions
														.visibilityOfElementLocated(By.cssSelector(locatorValue)));
												wait.until(ExpectedConditions
														.elementToBeClickable(By.cssSelector(locatorValue)));

											} else {

												if (locator.toLowerCase().contains("xpath")) {// xpath
													wait.until(ExpectedConditions
															.visibilityOfElementLocated(By.xpath(locatorValue)));
													wait.until(ExpectedConditions
															.elementToBeClickable(By.xpath(locatorValue)));

												} else {

													/******
													 * By default Xpath will be
													 * assumed
													 *****/
													wait.until(ExpectedConditions
															.visibilityOfElementLocated(By.xpath(locatorValue)));
													wait.until(ExpectedConditions
															.elementToBeClickable(By.xpath(locatorValue)));

												}
											}
										}
									}
								}
							}
						}
					}

				} else {

					/****** By default Xpath will be assumed *****/
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(XpathKey))));
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(XpathKey))));

				}

				fnpMymsg("@  ---after of fnpWaitForVisible for element ---" + XpathKey);
				break;
			} catch (Throwable t) {
				whileCounter++;
				fnpMymsg("@  ---In catch Throwbale block for whilecounter value--" + whileCounter);
				if (whileCounter > 1) {

					if ((t.getMessage().contains("waiting for visibility of element located by By.xpath"))
							|| (t.getMessage().contains("Timed out after"))) {
						throw new Exception(
								" Unable to find element with name [" + XpathKey + "]  \n\n\n\n  " + t.getMessage());
					} else {
						throw new Exception(t.getMessage());
					}

				}
			}

			finally {
				driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")),
						TimeUnit.SECONDS);
			}

		}

	}

	/*********** wait till element get visible ************/
	public static void fnpWaitForVisible(String XpathKey) throws Throwable {

		// fnpIpulseDuringLoading();
		fnpLoading_wait_CheckLoadingIconOnlyIniPulse();

		int whileCounter = 0;
		while (true) {
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			try {
				fnpMymsg("@  ---starting of fnpWaitForVisible for element ---" + XpathKey + " ---for chance --"
						+ whileCounter);

				Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
						.withTimeout((Long.parseLong(CONFIG.getProperty("genMax_waitTime"))), TimeUnit.SECONDS)
						.pollingEvery(1, TimeUnit.SECONDS)
						.ignoring(org.openqa.selenium.StaleElementReferenceException.class)
						.ignoring(org.openqa.selenium.WebDriverException.class)
						.ignoring(org.openqa.selenium.InvalidSelectorException.class)
						.ignoring(org.openqa.selenium.NoSuchElementException.class);

				if (OR.getProperty(XpathKey).contains("~")) {

					String[] s1 = OR.getProperty(XpathKey).split("~");
					String locatorValue = s1[1];
					String locator = s1[0];

					switch (locator) {
						case "id":
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
							wait.until(ExpectedConditions.elementToBeClickable(By.id(locatorValue)));
							break;

						case "name":
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue)));
							wait.until(ExpectedConditions.elementToBeClickable(By.name(locatorValue)));
							break;

						case "linkText":
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locatorValue)));
							wait.until(ExpectedConditions.elementToBeClickable(By.linkText(locatorValue)));
							break;

						case "partialLinkText":
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(locatorValue)));
							wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(locatorValue)));
							break;

						case "tagName":
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(locatorValue)));
							wait.until(ExpectedConditions.elementToBeClickable(By.tagName(locatorValue)));
							break;

						case "className":
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(locatorValue)));
							wait.until(ExpectedConditions.elementToBeClickable(By.className(locatorValue)));
							break;

						case "cssSelector":
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locatorValue)));
							wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(locatorValue)));
							break;

						case "xpath":
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorValue)));
							break;

						default:
							/****** By default Xpath will be assumed *****/
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorValue)));
							break;

					}

				} else {

					/****** By default Xpath will be assumed *****/
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(XpathKey))));
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(XpathKey))));

				}

				fnpMymsg("@  ---after of fnpWaitForVisible for element ---" + XpathKey);
				break;
			} catch (Throwable t) {
				whileCounter++;
				fnpMymsg("@  ---In catch Throwbale block for whilecounter value--" + whileCounter);
				if (whileCounter > 1) {

					if ((t.getMessage().contains("waiting for visibility of element located by By.xpath"))
							|| (t.getMessage().contains("Timed out after"))) {
						throw new Exception(
								" Unable to find element with name [" + XpathKey + "]  \n\n\n\n  " + t.getMessage());
					} else {
						throw new Exception(t.getMessage());
					}

				}
			}

			finally {
				driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")),
						TimeUnit.SECONDS);
			}

		}

	}

	/*********** wait till element get visible ************/
	public static void fnpWaitForVisible(String XpathKey, int MaxTimeInSeconds) throws Throwable {

		fnpIpulseDuringLoading();

		int whileCounter = 0;
		while (true) {
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			try {
				fnpMymsg("@  ---starting of fnpWaitForVisible for element ---" + XpathKey + " ---for chance --"
						+ whileCounter);

				Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(MaxTimeInSeconds, TimeUnit.SECONDS)
						.pollingEvery(1, TimeUnit.SECONDS)
						.ignoring(org.openqa.selenium.NoSuchElementException.class)
						.ignoring(org.openqa.selenium.StaleElementReferenceException.class)
						.ignoring(org.openqa.selenium.WebDriverException.class)
						.ignoring(org.openqa.selenium.InvalidSelectorException.class);

				if (OR.getProperty(XpathKey).contains("~")) {

					String[] s1 = OR.getProperty(XpathKey).split("~");
					String locatorValue = s1[1];
					String locator = s1[0];
					if (locator.contains("id")) {// id
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
						wait.until(ExpectedConditions.elementToBeClickable(By.id(locatorValue)));
					} else {
						if (locator.contains("name")) {// name
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue)));
							wait.until(ExpectedConditions.elementToBeClickable(By.name(locatorValue)));

						} else {
							if (locator.toLowerCase().contains("linktext")) {// linkText
								wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locatorValue)));
								wait.until(ExpectedConditions.elementToBeClickable(By.linkText(locatorValue)));

							} else {
								if (locator.toLowerCase().contains("partialLinkText")) {// partialLinkText
									wait.until(ExpectedConditions
											.visibilityOfElementLocated(By.partialLinkText(locatorValue)));
									wait.until(
											ExpectedConditions.elementToBeClickable(By.partialLinkText(locatorValue)));

								} else {
									if (locator.toLowerCase().contains("tagName")) {// tagName
										wait.until(ExpectedConditions
												.visibilityOfElementLocated(By.tagName(locatorValue)));
										wait.until(ExpectedConditions.elementToBeClickable(By.tagName(locatorValue)));

									} else {
										if (locator.toLowerCase().contains("className")) {// className
											wait.until(ExpectedConditions
													.visibilityOfElementLocated(By.className(locatorValue)));
											wait.until(ExpectedConditions
													.elementToBeClickable(By.className(locatorValue)));

										} else {
											if (locator.toLowerCase().contains("css")) {// cssSelector
												wait.until(ExpectedConditions
														.visibilityOfElementLocated(By.cssSelector(locatorValue)));
												wait.until(ExpectedConditions
														.elementToBeClickable(By.cssSelector(locatorValue)));

											} else {

												if (locator.toLowerCase().contains("xpath")) {// xpath
													wait.until(ExpectedConditions
															.visibilityOfElementLocated(By.xpath(locatorValue)));
													wait.until(ExpectedConditions
															.elementToBeClickable(By.xpath(locatorValue)));

												} else {

													/******
													 * By default Xpath will be
													 * assumed
													 *****/
													wait.until(ExpectedConditions
															.visibilityOfElementLocated(By.xpath(locatorValue)));
													wait.until(ExpectedConditions
															.elementToBeClickable(By.xpath(locatorValue)));

												}
											}
										}
									}
								}
							}
						}
					}

				} else {

					/****** By default Xpath will be assumed *****/
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(XpathKey))));
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(XpathKey))));

				}

				fnpMymsg("@  ---after of fnpWaitForVisible for element ---" + XpathKey);
				break;
			} catch (Throwable t) {
				whileCounter++;
				fnpMymsg("@  ---In catch Throwbale block for whilecounter value--" + whileCounter);
				if (whileCounter > 1) {
					fnpMymsg("@  ---throwing exception after catch Throwbale block for whilecounter value--"
							+ whileCounter);
					// throw new Exception(t.getMessage());

					if ((t.getMessage().contains("waiting for visibility of element located by By.xpath"))
							|| (t.getMessage().contains("Timed out after"))) {
						throw new Exception(
								" Unable to find element with name [" + XpathKey + "]  \n\n\n\n  " + t.getMessage());
					} else {
						throw new Exception(t.getMessage());
					}

				}

			}

			finally {
				driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")),
						TimeUnit.SECONDS);
			}

		}

	}

	/*********** wait till element get visible using Link Name ************/
	public static void fnpWaitForVisible_usingLinkNameInOR(String ORLinkName) throws Throwable {

		int i = 0;

		WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(CONFIG.getProperty("genMax_waitTime")));

		int retries = 0;
		while (true) {
			i++;
			try {

				if (OR.getProperty(ORLinkName).contains("~")) {

					String[] s1 = OR.getProperty(ORLinkName).split("~");
					String locatorValue = s1[1];
					String locator = s1[0];
					if (locator.contains("id")) {// id
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
						// wait.until(ExpectedConditions.elementToBeClickable(By.id(locatorValue)));
					} else {
						if (locator.contains("name")) {// name
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue)));
							// wait.until(ExpectedConditions.elementToBeClickable(By.name(locatorValue)));

						} else {
							if (locator.toLowerCase().contains("linktext")) {// linkText
								wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locatorValue)));
								// wait.until(ExpectedConditions.elementToBeClickable(By.linkText(locatorValue)));

							} else {
								if (locator.toLowerCase().contains("partialLinkText")) {// partialLinkText
									wait.until(ExpectedConditions
											.visibilityOfElementLocated(By.partialLinkText(locatorValue)));
									// wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(locatorValue)));

								} else {
									if (locator.toLowerCase().contains("tagName")) {// tagName
										wait.until(ExpectedConditions
												.visibilityOfElementLocated(By.tagName(locatorValue)));
										// wait.until(ExpectedConditions.elementToBeClickable(By.tagName(locatorValue)));

									} else {
										if (locator.toLowerCase().contains("className")) {// className
											wait.until(ExpectedConditions
													.visibilityOfElementLocated(By.className(locatorValue)));
											// wait.until(ExpectedConditions.elementToBeClickable(By.className(locatorValue)));

										} else {
											if (locator.toLowerCase().contains("css")) {// cssSelector
												wait.until(ExpectedConditions
														.visibilityOfElementLocated(By.cssSelector(locatorValue)));
												// wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(locatorValue)));

											} else {

												if (locator.toLowerCase().contains("xpath")) {// xpath
													wait.until(ExpectedConditions
															.visibilityOfElementLocated(By.xpath(locatorValue)));
													// wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorValue)));

												} else {

													/******
													 * By default Xpath will be
													 * assumed
													 *****/
													wait.until(ExpectedConditions
															.visibilityOfElementLocated(By.xpath(locatorValue)));
													// wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorValue)));

												}
											}
										}
									}
								}
							}
						}
					}

				} else {

					wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(OR.getProperty(ORLinkName))));
				}

				return;
			} catch (org.openqa.selenium.StaleElementReferenceException e) {
				if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					retries++;
					fnpMymsg(retries
							+ "In staleElementException catch block of fnpWaitForVisible_usingLinkNameInOR function for "
							+ ORLinkName);
					continue;
				} else {
					throw e;
				}
			} catch (Throwable t) {
				if (retries < 2) {
					retries++;
					fnpMymsg(retries + "In fnpWaitForVisible_usingLinkNameInOR 's try catch block  for " + ORLinkName);
					continue;
				} else {
					// throw t;

					if ((t.getMessage().contains("waiting for visibility of element located by By.xpath"))
							|| (t.getMessage().contains("Timed out after"))) {
						throw new Exception(
								" Unable to find element with name [" + ORLinkName + "]  \n\n\n\n  " + t.getMessage());
					} else {
						throw new Exception(t.getMessage());
					}

				}
			}
		}

	}

	/*********** wait till element get visible when element not in OR ************/
	public static void fnpWaitForVisible_NotInOR(String Xpath) throws Throwable {

		// fnpLoading_wait();
		int i = 0;

		WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(CONFIG.getProperty("genMax_waitTime")));
		int retries = 0;
		while (true) {
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Xpath)));// added on 01-06-2020 can remove
																						// later if it creates any
																						// problem
				return;
			} catch (org.openqa.selenium.StaleElementReferenceException e) {
				if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					retries++;
					fnpMymsg(retries + "In staleElementException catch block of fnpWaitForVisible_NotInOR function for "
							+ Xpath);
					continue;
				} else {
					throw e;
				}
			} catch (Throwable t) {
				if (retries < 2) {
					retries++;
					fnpMymsg(retries + "In try catch block of fnpWaitForVisible_NotInOR function for " + Xpath);
					continue;
				} else {
					throw t;

				}

			}
		}

	}

	/*********** wait till element get visible ************/
	public static void fnpWaitForVisible_NotInOR(String Xpath, int MaxTimeInSeconds) throws Throwable {

		int whileCounter = 0;
		while (true) {
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			try {
				fnpMymsg("@  ---starting of fnpWaitForVisible_NotInOR for element ---" + Xpath + " ---for chance --"
						+ whileCounter);

				Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(MaxTimeInSeconds, TimeUnit.SECONDS)
						.pollingEvery(1, TimeUnit.SECONDS)
						.ignoring(org.openqa.selenium.NoSuchElementException.class)
						.ignoring(org.openqa.selenium.StaleElementReferenceException.class)
						.ignoring(org.openqa.selenium.WebDriverException.class)
						.ignoring(org.openqa.selenium.InvalidSelectorException.class);

				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Xpath)));

				fnpMymsg("@  ---after of fnpWaitForVisible for element ---" + Xpath);
				break;
			} catch (Throwable t) {
				whileCounter++;
				fnpMymsg("@  ---In catch Throwbale block for whilecounter value--" + whileCounter);
				if (whileCounter > 1) {
					fnpMymsg("@  ---throwing exception after catch Throwbale block for whilecounter value--"
							+ whileCounter);
					throw new Exception(t.getMessage());

				}

			}

			finally {
				driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")),
						TimeUnit.SECONDS);
			}

		}

	}

	/**** May be not using this ****/
	/***********
	 * wait till element get visible , here wait for dynamic time pass as an
	 * argument
	 * 
	 * @throws Throwable
	 ************/
	public static void fnpWaitForElementVisibility(String XpathKey, String timedata) throws Throwable {
		fnpMymsg("Waiting for an element to be visible");
		int start = 0;
		int time = (int) Double.parseDouble(timedata);

		while (time == start) {

			if (fnpGetORObject_list(XpathKey, 1).size() == 0) {

				fnpMymsg("In fnpWaitForElementVisibility function waiting for " + XpathKey + start + 1);

				start++;
			} else {
				break;
			}
		}

		if (fnpGetORObject_list(XpathKey, 0, 1).size() == 0) {
			fnpDesireScreenshot("MissingElement" + XpathKey);
			throw new Exception("Unable to find/locate element  having xpath [" + XpathKey
					+ "],plz see screenshot [MissingElement" + XpathKey + SShots + "]");
		}

	}

	/****
	 * Wait till Element gets visible and in clickable state which is not in OR
	 ****/
	public static void fnpWaitTillVisiblityOfElementNClickable_OR_deprecatedOn_19_12_2017(String XpathKey)
			throws Throwable {

		int whileCounter = 0;
		while (true) {
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			try {
				fnpMymsg("@  ---starting of fnpWaitTillVisiblityOfElementNClickable_OR for element ---" + XpathKey
						+ " ---for chance --" + whileCounter);

				Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
						.withTimeout((Long.parseLong(CONFIG.getProperty("genMax_waitTime"))), TimeUnit.SECONDS)
						.pollingEvery(1, TimeUnit.SECONDS).ignoring(org.openqa.selenium.NoSuchElementException.class)
						.ignoring(org.openqa.selenium.StaleElementReferenceException.class)
						.ignoring(org.openqa.selenium.WebDriverException.class)
						.ignoring(org.openqa.selenium.InvalidSelectorException.class);

				if (OR.getProperty(XpathKey).contains("~")) {

					String[] s1 = OR.getProperty(XpathKey).split("~");
					String locatorValue = s1[1];
					String locator = s1[0];
					if (locator.contains("id")) {// id
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
						wait.until(ExpectedConditions.elementToBeClickable(By.id(locatorValue)));
					} else {
						if (locator.contains("name")) {// name
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue)));
							wait.until(ExpectedConditions.elementToBeClickable(By.name(locatorValue)));

						} else {
							if (locator.toLowerCase().contains("linktext")) {// linkText
								wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locatorValue)));
								wait.until(ExpectedConditions.elementToBeClickable(By.linkText(locatorValue)));

							} else {
								if (locator.toLowerCase().contains("partialLinkText")) {// partialLinkText
									wait.until(ExpectedConditions
											.visibilityOfElementLocated(By.partialLinkText(locatorValue)));
									wait.until(
											ExpectedConditions.elementToBeClickable(By.partialLinkText(locatorValue)));

								} else {
									if (locator.toLowerCase().contains("tagName")) {// tagName
										wait.until(ExpectedConditions
												.visibilityOfElementLocated(By.tagName(locatorValue)));
										wait.until(ExpectedConditions.elementToBeClickable(By.tagName(locatorValue)));

									} else {
										if (locator.toLowerCase().contains("className")) {// className
											wait.until(ExpectedConditions
													.visibilityOfElementLocated(By.className(locatorValue)));
											wait.until(ExpectedConditions
													.elementToBeClickable(By.className(locatorValue)));

										} else {
											if (locator.toLowerCase().contains("css")) {// cssSelector
												wait.until(ExpectedConditions
														.visibilityOfElementLocated(By.cssSelector(locatorValue)));
												wait.until(ExpectedConditions
														.elementToBeClickable(By.cssSelector(locatorValue)));

											} else {

												if (locator.toLowerCase().contains("xpath")) {// xpath
													wait.until(ExpectedConditions
															.visibilityOfElementLocated(By.xpath(locatorValue)));
													wait.until(ExpectedConditions
															.elementToBeClickable(By.xpath(locatorValue)));

												} else {

													/******
													 * By default Xpath will be
													 * assumed
													 *****/
													wait.until(ExpectedConditions
															.visibilityOfElementLocated(By.xpath(locatorValue)));
													wait.until(ExpectedConditions
															.elementToBeClickable(By.xpath(locatorValue)));

												}
											}
										}
									}
								}
							}
						}
					}

				} else {

					/****** By default Xpath will be assumed *****/
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(XpathKey))));
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(XpathKey))));

				}

				fnpMymsg("@  ---after of fnpWaitTillVisiblityOfElementNClickable_OR for element ---" + XpathKey);
				break;
			} catch (Throwable t) {
				whileCounter++;
				if (whileCounter > 1) {

					if ((t.getMessage().contains("waiting for visibility of element located by By.xpath"))
							|| (t.getMessage().contains("Timed out after"))) {
						throw new Exception(
								" Unable to find element with name [" + XpathKey + "]  \n\n\n\n  " + t.getMessage());
					} else {
						throw new Exception(t.getMessage());
					}

					// throw new Exception(t.getMessage());
				}
			}

			finally {
				driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")),
						TimeUnit.SECONDS);
			}

		}

	}

	/****
	 * Wait till Element gets visible and in clickable state which is not in OR
	 ****/
	public static void fnpWaitTillVisiblityOfElementNClickable_OR(String XpathKey) throws Throwable {

		int whileCounter = 0;
		while (true) {
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			try {
				fnpMymsg("@  ---starting of fnpWaitTillVisiblityOfElementNClickable_OR for element ---" + XpathKey
						+ " ---for chance --" + whileCounter);

				Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
						.withTimeout((Long.parseLong(CONFIG.getProperty("genMax_waitTime"))), TimeUnit.SECONDS)
						.pollingEvery(1, TimeUnit.SECONDS).ignoring(org.openqa.selenium.NoSuchElementException.class)
						.ignoring(org.openqa.selenium.StaleElementReferenceException.class)
						.ignoring(org.openqa.selenium.WebDriverException.class)
						.ignoring(org.openqa.selenium.InvalidSelectorException.class);

				if (OR.getProperty(XpathKey).contains("~")) {

					String[] s1 = OR.getProperty(XpathKey).split("~");
					String locatorValue = s1[1];
					String locator = s1[0];

					switch (locator) {
						case "id":
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
							wait.until(ExpectedConditions.elementToBeClickable(By.id(locatorValue)));
							break;

						case "name":
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue)));
							wait.until(ExpectedConditions.elementToBeClickable(By.name(locatorValue)));
							break;

						case "linkText":
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locatorValue)));
							wait.until(ExpectedConditions.elementToBeClickable(By.linkText(locatorValue)));
							break;

						case "partialLinkText":
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(locatorValue)));
							wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(locatorValue)));
							break;

						case "tagName":
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(locatorValue)));
							wait.until(ExpectedConditions.elementToBeClickable(By.tagName(locatorValue)));
							break;

						case "className":
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(locatorValue)));
							wait.until(ExpectedConditions.elementToBeClickable(By.className(locatorValue)));
							break;

						case "cssSelector":
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locatorValue)));
							wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(locatorValue)));
							break;

						case "xpath":
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorValue)));
							break;

						default:
							/****** By default Xpath will be assumed *****/
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorValue)));
							break;

					}

				} else {

					/****** By default Xpath will be assumed *****/
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(XpathKey))));
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(XpathKey))));

				}

				fnpMymsg("@  ---after of fnpWaitTillVisiblityOfElementNClickable_OR for element ---" + XpathKey);
				break;
			} catch (Throwable t) {
				whileCounter++;
				if (whileCounter > 1) {

					if ((t.getMessage().contains("waiting for visibility of element located by By.xpath"))
							|| (t.getMessage().contains("Timed out after"))) {
						throw new Exception(
								" Unable to find element with name [" + XpathKey + "]  \n\n\n\n  " + t.getMessage());
					} else {
						throw new Exception(t.getMessage());
					}

					// throw new Exception(t.getMessage());
				}
			}

			finally {
				driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")),
						TimeUnit.SECONDS);
			}

		}

	}

	/****
	 * Wait till Element gets visible and in clickable state when object not in
	 * OR
	 ****/
	public static void fnpWaitTillVisiblityOfElementNClickableInsidePFList(String XpathKey) throws Throwable {

		WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(CONFIG.getProperty("genMax_waitTime")));
		int retries = 0;
		while (true) {
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XpathKey)));
				return;
			} catch (org.openqa.selenium.StaleElementReferenceException e) {
				if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					retries++;
					fnpMymsg(retries
							+ "In staleElementException catch block of fnpWaitTillVisiblityOfElementNClickable function for "
							+ XpathKey);
					continue;
				} else {
					throw e;
				}
			} catch (Throwable t) {
				if (retries < 2) {
					retries++;
					fnpMymsg(retries + "In try catch block of fnpWaitTillVisiblityOfElementNClickable function for "
							+ XpathKey);
					continue;
				} else {
					throw t;
				}

			}

		}

	}

	/****
	 * Wait till Element gets visible and in clickable state when object not in
	 * OR
	 ****/

	public static void fnpWaitTillVisiblityOfElementNClickable(String XpathKey) throws Throwable {

		WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(CONFIG.getProperty("genMax_waitTime")));
		int retries = 0;
		while (true) {
			try {

				wait.until(ExpectedConditions
						.elementToBeClickable(fnpGetORObjectX_WhenMultipleObjectsHavingSameXpath(XpathKey)));
				return;
			} catch (org.openqa.selenium.StaleElementReferenceException e) {
				if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					retries++;
					fnpMymsg(retries
							+ "In staleElementException catch block of fnpWaitTillVisiblityOfElementNClickable function for "
							+ XpathKey);
					continue;
				} else {
					throw e;
				}
			} catch (Throwable t) {
				if (retries < 2) {
					retries++;
					fnpMymsg(retries + "In try catch block of fnpWaitTillVisiblityOfElementNClickable function for "
							+ XpathKey);
					continue;
				} else {
					throw t;
				}

			}

		}

	}

	/****
	 * Wait till Element gets visible and in clickable state when object not in
	 * OR
	 ****/

	public static void fnpWaitTillVisiblityOfElementNClickable_NOR(String Xpath) throws Throwable {

		WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(CONFIG.getProperty("genMax_waitTime")));
		int retries = 0;
		while (true) {
			try {

				wait.until(ExpectedConditions
						.elementToBeClickable(fnpGetORObjectX_WhenMultipleObjectsHavingSameXpath_NOR(Xpath)));
				return;
			} catch (org.openqa.selenium.StaleElementReferenceException e) {
				if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					retries++;
					fnpMymsg(retries
							+ "In staleElementException catch block of fnpWaitTillVisiblityOfElementNClickable_NOR function for "
							+ Xpath);
					continue;
				} else {
					throw e;
				}
			} catch (Throwable t) {
				if (retries < 2) {
					retries++;
					fnpMymsg(retries + "In try catch block of fnpWaitTillVisiblityOfElementNClickable_NOR function for "
							+ Xpath);
					continue;
				} else {
					throw t;
				}

			}

		}

	}

	/****
	 * Check IMMEDIATELY Element is visible or not and in clickable state when
	 * object not in
	 * OR
	 ****/
	public static void fnpWaitTillVisiblityOfElementNClickable_NOR_Immediately(String Xpath) throws Throwable {

		int retries = 0;
		while (true) {
			try {
				WebDriverWait wait = new WebDriverWait(driver, 5);
				wait.until(ExpectedConditions.elementToBeClickable(
						fnpGetORObjectX_WhenMultipleObjectsHavingSameXpath_NOR_immediately(Xpath, " ")));
				return;
			} catch (org.openqa.selenium.StaleElementReferenceException e) {
				if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					retries++;
					fnpMymsg(retries
							+ "In staleElementException catch block of fnpWaitTillVisiblityOfElementNClickable_NOR function for "
							+ Xpath);
					continue;
				} else {
					throw e;
				}
			} catch (Throwable t) {
				if (retries < 2) {
					retries++;
					fnpMymsg(retries + "In try catch block of fnpWaitTillVisiblityOfElementNClickable_NOR function for "
							+ Xpath);
					continue;
				} else {
					throw t;
				}

			}

			/*
			 * finally{
			 * driver.manage().timeouts().implicitlyWait(Integer.parseInt
			 * (CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS); }
			 */
		}

	}

	/**** First search then select first radio button in Lookup ****/
	public static void fnpSearchNSelectFirstRadioBtn(int LKPTextBoxNo, String value, int level) throws Throwable {

		/************** commented on 23-03-15 ********************/
		fnpWaitForVisible("LookupFirstTextBox_id");
		/************** commented on 23-03-15 ********************/

		if (value.trim().isEmpty()) {
			fnpClick_OR("FirstRadioBtnInLkpAFSch");

			return;
		}

		int beforeSearchRow = fnpCountRowsIn_LOOKUPTable("LookupRowsForCountPurpose");

		if (LKPTextBoxNo == 1) {
			fnpPFListModify("FirstSelectConditionInLookup", "Equals");

			fnpGetORObjectX("LookupFirstTextBox_id").sendKeys("");

			fnpGetORObjectX("LookupFirstTextBox_id").sendKeys(value);
			fnpMymsg("Inserted this value in LookupFirstTextBox---" + value);

		}
		if (LKPTextBoxNo == 2) {
			fnpPFListModify("SecondSelectConditionInLookup", "Equals");
			fnpGetORObjectX("LookkupSecondTextBox_id").sendKeys("");
			fnpGetORObjectX("LookkupSecondTextBox_id").sendKeys(value);
			fnpMymsg("Inserted this value in LookkupSecondTextBox---" + value);
		}

		fnpClick_OR("LookupSearchBtn_id");

		try {
			fnpWaitTillClickable("FirstRadioBtnInLkpAFSch");
		} catch (Throwable t) {
			String searchedText = "";
			try {
				searchedText = driver.findElement(By.xpath(".//*[@id='mainForm:lookuptableid_data']/tr[1]/td[1]"))
						.getText();
			} catch (Throwable tsearchText) {
				// nothing to do
			}
			if (searchedText.toLowerCase().contains("no records found")) {
				throw new Exception("The data '" + value
						+ "' which is to be searched in lookup is not present in the lookup after search");
			} else {
				throw t;
			}
		}

		fnpClick_OR_WithoutWait("FirstRadioBtnInLkpAFSch");
		fnpLoading_waitInsideDialogBox();

	}

	/**** First search then select first radio button in Lookup ****/
	public static void fnpSearchNSelectFirstRadioBtn(String completeSearchCriteria) throws Throwable {

		fnpCommonCodeForSearchInLookupFor_radiobtn_alsofor_checkbox(completeSearchCriteria);

		try {
			fnpWaitTillClickable("FirstRadioBtnInLkpAFSch");
		} catch (Throwable t) {
			String searchedText = "";
			try {
				searchedText = driver.findElement(By.xpath(".//*[@id='mainForm:lookuptableid_data']/tr[1]/td[1]"))
						.getText();
			} catch (Throwable tsearchText) {
				// nothing to do
			}
			if (searchedText.toLowerCase().contains("no records found")) {
				throw new Exception(
						"The data  which is to be searched in lookup is not present in the lookup after search");
			} else {
				throw t;
			}
		}

		fnpClick_OR_WithoutWait("FirstRadioBtnInLkpAFSch");
		fnpLoading_waitInsideDialogBox();

	}

	/**** First search then select first radio button in Lookup ****/
	public static void fnpSearchNSelectFirstCheckBox(String completeSearchCriteria) throws Throwable {

		fnpCommonCodeForSearchInLookupFor_radiobtn_alsofor_checkbox(completeSearchCriteria);

		try {
			fnpWaitTillClickable("FirstCheckBoxInLkpAFSch");
		} catch (Throwable t) {
			String searchedText = "";
			try {
				searchedText = driver.findElement(By.xpath(".//*[@id='mainForm:lookuptableid_data']/tr[1]/td[1]"))
						.getText();
			} catch (Throwable tsearchText) {
				// nothing to do
			}
			if (searchedText.toLowerCase().contains("no records found")) {
				throw new Exception("The data '" + completeSearchCriteria
						+ "' which is to be searched in lookup is not present in the lookup after search");
			} else {
				throw t;
			}
		}

		fnpClick_OR_WithoutWait("FirstCheckBoxInLkpAFSch");
		fnpLoading_waitInsideDialogBox();
	}

	public static void fnpCommonCodeForSearchInLookupFor_radiobtn_alsofor_checkbox(String completeSearchCriteria)
			throws Throwable {

		/************** commented on 23-03-15 ********************/
		fnpWaitForVisible("LookupFirstTextBox_id");
		/************** commented on 23-03-15 ********************/

		// completeSearchCriteria="(Type Name:Starts With:TechnicalReview),(Business
		// Profile:Starts With:ISR_Profile)";

		String selectField_dropdown_xpath_part1 = ".//*[@id='mainForm:newfdt:";
		String selectField_dropdown_xpath_part2 = ":slLk1_label']";

		String conditions_dropdown_xpath_part1 = ".//*[@id='mainForm:newfdt:";
		String conditions_dropdown_xpath_part2 = ":slLk2_label']";

		String value_textbox_xpath_part1 = ".//*[@id='mainForm:newfdt:";
		String value_textbox_xpath_part2 = ":newcriInpNm']";

		String[] allRowFilterDetails = completeSearchCriteria.split(",");

		String[] singleRowFilterDetails;
		String searchFieldValue;
		String condition;
		String valueToBeInsert;
		for (int i = 0; i < allRowFilterDetails.length; i++) {
			singleRowFilterDetails = allRowFilterDetails[i].split(":");
			searchFieldValue = singleRowFilterDetails[0].trim();
			condition = singleRowFilterDetails[1].trim();
			valueToBeInsert = singleRowFilterDetails[2].trim();

			fnpPFListModify_NOR(selectField_dropdown_xpath_part1 + i + selectField_dropdown_xpath_part2,
					fnpremoveFormatting(searchFieldValue).trim());
			// fnpClick_OR_WithoutWait("lookupDialogTitle");
			fnpPFListModify_NOR(conditions_dropdown_xpath_part1 + i + conditions_dropdown_xpath_part2,
					fnpremoveFormatting(condition).trim());

			// fnpGetORObjectX___NOR(value_textbox_xpath_part1+i+value_textbox_xpath_part2).sendKeys(fnpremoveFormatting(valueToBeInsert).trim());
			// fnpType("", value_textbox_xpath_part1+i+value_textbox_xpath_part2,
			// fnpremoveFormatting(valueToBeInsert).trim());

			// fnpType2_afterClearingTextGivingMuchTime("",
			// value_textbox_xpath_part1+i+value_textbox_xpath_part2,
			// fnpremoveFormatting(valueToBeInsert).trim());

			/*
			 * WebElement wb=fnpGetORObjectX___NOR(value_textbox_xpath_part1+i+
			 * value_textbox_xpath_part2);
			 * JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			 * //set the text
			 * jsExecutor.executeScript("arguments[0].value='"+fnpremoveFormatting(
			 * valueToBeInsert).trim()+"'", wb);
			 * 
			 */

			/*
			 * Thread.sleep(5000);
			 * fnpGetORObjectX___NOR(value_textbox_xpath_part1+(i+1)+
			 * value_textbox_xpath_part2).click();
			 * Thread.sleep(5000);
			 * fnpGetORObjectX___NOR(value_textbox_xpath_part1+(i+1)+
			 * value_textbox_xpath_part2).sendKeys(Keys.ENTER);
			 * Thread.sleep(5000);
			 * 
			 */

			/*
			 * Thread.sleep(5000);
			 * fnpPFListModify_NOR(conditions_dropdown_xpath_part1+i+
			 * conditions_dropdown_xpath_part2, fnpremoveFormatting(condition).trim());
			 * Thread.sleep(5000);
			 * 
			 * 
			 * fnpGetORObjectX___NOR(conditions_dropdown_xpath_part1+i+
			 * conditions_dropdown_xpath_part2).click();
			 * Thread.sleep(5000);
			 */
			// fnpClick_OR_WithoutWait("lookupDialogTitle");

		}

		for (int i = 0; i < allRowFilterDetails.length; i++) {
			singleRowFilterDetails = allRowFilterDetails[i].split(":");
			// searchFieldValue=singleRowFilterDetails[0].trim();
			// condition=singleRowFilterDetails[1].trim();
			valueToBeInsert = singleRowFilterDetails[2].trim();

			fnpType("", value_textbox_xpath_part1 + i + value_textbox_xpath_part2,
					fnpremoveFormatting(valueToBeInsert).trim());

			Thread.sleep(5000);
			fnpGetORObjectX___NOR(value_textbox_xpath_part1 + (i + 1) + value_textbox_xpath_part2).click();
			fnpClick_OR_WithoutWait("lookupDialogTitle");

		}

		int beforeSearchRow = fnpCountRowsIn_LOOKUPTable("LookupRowsForCountPurpose");

		int start = 0;
		int afterSearchRow = beforeSearchRow;
		fnpClick_OR("LookupSearchBtn_id");
		while ((beforeSearchRow == afterSearchRow) && (beforeSearchRow != 1)) {
			Thread.sleep(1000);
			// fnpLoading_wait();
			fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
			afterSearchRow = fnpCountRowsIn_LOOKUPTable("LookupRowsForCountPurpose");
			start++;
			// if (start > (Integer.parseInt(CONFIG.getProperty("genMax_waitTime")) * 2)) {
			if (start > 2) {
				break;

			}
		}

	}

	/**** First search then select first radio button in Lookup ****/
	public static void fnpSearchNSelectFirstRadioBtn(int LKPTextBoxNo, String condition, String value, int level)
			throws Throwable {

		/************** commented on 23-03-15 ********************/
		fnpWaitForVisible("LookupFirstTextBox_id");
		/************** commented on 23-03-15 ********************/

		if (value.trim().isEmpty()) {
			fnpClick_OR("FirstRadioBtnInLkpAFSch");

			return;
		}

		int beforeSearchRow = fnpCountRowsIn_LOOKUPTable("LookupRowsForCountPurpose");

		if (LKPTextBoxNo == 1) {
			// fnpPFListModify("FirstSelectConditionInLookup", "Equals");

			switch (condition) {
				case "Equals":
					fnpPFListModify("FirstSelectConditionInLookup", "Equals");
					break;

				case "Contains":
					fnpPFListModify("FirstSelectConditionInLookup", "Contains");
					break;

				default:

					/****** By default Xpath will be assumed *****/
					throw new Exception("Condition is not provided from selection from lookup.");
			}

			fnpGetORObjectX("LookupFirstTextBox_id").sendKeys("");

			fnpGetORObjectX("LookupFirstTextBox_id").sendKeys(value);

		}
		if (LKPTextBoxNo == 2) {
			// fnpPFListModify("SecondSelectConditionInLookup", "Equals");

			switch (condition) {
				case "Equals":
					fnpPFListModify("SecondSelectConditionInLookup", "Equals");
					break;

				case "Contains":
					fnpPFListModify("SecondSelectConditionInLookup", "Contains");
					break;

				default:

					/****** By default Xpath will be assumed *****/
					throw new Exception("Condition is not provided from selection from lookup.");
			}

			fnpGetORObjectX("LookkupSecondTextBox_id").sendKeys("");
			fnpGetORObjectX("LookkupSecondTextBox_id").sendKeys(value);
		}

		fnpClick_OR("LookupSearchBtn_id");

		try {
			fnpWaitTillClickable("FirstRadioBtnInLkpAFSch");
		} catch (Throwable t) {
			String searchedText = "";
			try {
				searchedText = driver.findElement(By.xpath(".//*[@id='mainForm:lookuptableid_data']/tr[1]/td[1]"))
						.getText();
			} catch (Throwable tsearchText) {
				// nothing to do
			}
			if (searchedText.toLowerCase().contains("no records found")) {
				throw new Exception("The data '" + value
						+ "' which is to be searched in lookup is not present in the lookup after search");
			} else {
				throw t;
			}
		}

		fnpClick_OR_WithoutWait("FirstRadioBtnInLkpAFSch");
		fnpLoading_waitInsideDialogBox();

	}

	public static String fnpremoveFormatting(String s) {

		s = s.replace("[", " ");
		s = s.replace("]", " ");
		s = s.replace("(", " ");
		s = s.replace(")", " ");
		s = s.trim();

		return s;

	}

	public static String fnpremoveFormatting_forISR(String s) {

		s = s.replace("[", " ");
		s = s.replace("]", " ");
		s = s.replace("{", " ");
		s = s.replace("}", " ");
		s = s.trim();

		return s;

	}

	public String fnpRemoveOtherCharactersFromAmount(String s) {
		s = s.replace(",", "");
		s = s.replace("[", "");
		s = s.replace("[", " ");
		s = s.replace("]", " ");
		s = s.trim();
		return s;
	}

	/**** Using in WO ISR *****************************************************/
	/**** First search then select first radio button in Lookup ****/
	public static void fnpSearchNSelectFirstRadioBtn(String[][] lookup, int level) throws Throwable {

		/************** commented on 23-03-15 ********************/
		fnpWaitForVisible("LookupFirstTextBox");
		/************** commented on 23-03-15 ********************/

		int lookupSelectionSize = lookup.length;
		String searchField_xpath;
		String conditionField_xpath;
		String valueField_xpath;
		String value;

		String goingToInsertThisValue = "";

		for (int j = 0; j < lookupSelectionSize; j++) {

			searchField_xpath = OR.getProperty("SearchFieldsInLookup_firstPart") + j
					+ OR.getProperty("SearchFieldsInLookup_secondPart");
			conditionField_xpath = OR.getProperty("SelectConditionInLookup_firstPart") + j
					+ OR.getProperty("SelectConditionInLookup_secondPart");
			valueField_xpath = OR.getProperty("ValueFieldsInLookup_firstPart") + j
					+ OR.getProperty("ValueFieldsInLookup_secondPart").trim();

			fnpPFListModify_NOR(searchField_xpath, fnpremoveFormatting(lookup[j][0]));

			Thread.sleep(500);

			fnpGetORObjectX("lookupDialogTitle").click();

			Thread.sleep(500);

			fnpPFListModify_NOR(conditionField_xpath, fnpremoveFormatting(lookup[j][1]));

			Thread.sleep(500);

			fnpGetORObjectX("lookupDialogTitle").click();

			Thread.sleep(500);

			fnpGetORObjectX___NOR(valueField_xpath).sendKeys("");

			goingToInsertThisValue = fnpremoveFormatting(lookup[j][2]).trim();

			fnpType("", valueField_xpath, goingToInsertThisValue);
			if (lookupSelectionSize > 1) {
				Thread.sleep(10000); // /commented on 17-11-16 as it taking lot
										// of time...if failed then uncomment
										// this only
				// Thread.sleep(4000);
			} else {
				Thread.sleep(4000);
			}

			valueField_xpath = OR.getProperty("ValueFieldsInLookup_firstPart") + (j + 1)
					+ OR.getProperty("ValueFieldsInLookup_secondPart").trim();
			Thread.sleep(500);
			driver.findElement(By.xpath(valueField_xpath)).click();
			Thread.sleep(500);

			Thread.sleep(500);

		}

		int beforeSearchRow = fnpCountRowsIn_LOOKUPTable("LookupRowsForCountPurpose");

		fnpClick_OR("LookupSearchBtn");

		try {
			fnpWaitTillClickable("FirstRadioBtnInLkpAFSch");
		} catch (Throwable t) {
			String searchedText = "";
			try {
				searchedText = driver.findElement(By.xpath(".//*[@id='mainForm:lookuptableid_data']/tr[1]/td[1]"))
						.getText();
			} catch (Throwable tsearchText) {
				// nothing to do
			}
			if (searchedText.toLowerCase().contains("no records found")) {
				throw new Exception("The data '" + goingToInsertThisValue
						+ "' which is to be searched in lookup is not present in the lookup after search");
			} else {
				throw t;
			}
		}

		fnpClick_OR_WithoutWait("FirstRadioBtnInLkpAFSch");

		fnpLoading_wait();

	}

	/**** First search then select first checkbox in Lookup ****/
	public static void fnpSearchNSelectFirstCheckBox(int LKPTextBoxNo, String value, int notusinglevel)
			throws Throwable {

		// fnpLoading_wait();
		/************** commented on 23-03-15 ********************/
		fnpWaitForVisible("LookupFirstTextBox");
		/************** commented on 23-03-15 ********************/

		if (value.trim().isEmpty()) {
			fnpClick_OR("FirstRadioBtnInLkpAFSch");

			return;
		}

		int beforeSearchRow = fnpCountRowsIn_LOOKUPTable("LookupRowsForCountPurpose");

		if (LKPTextBoxNo == 1) {
			// driver.findElement(By.xpath(".//*[@id='mainForm:newfdt:0:newco']")).sendKeys("Equals");
			fnpPFListModify("FirstSelectConditionInLookup", "Equals");
			fnpGetORObjectX("LookupFirstTextBox").sendKeys("");
			fnpGetORObjectX("LookupFirstTextBox").sendKeys(value);
		}
		if (LKPTextBoxNo == 2) {
			// driver.findElement(By.xpath(".//*[@id='mainForm:newfdt:1:newco']")).sendKeys("Equals");
			fnpPFListModify("SecondSelectConditionInLookup", "Equals");
			fnpGetORObjectX("LookkupSecondTextBox").sendKeys("");
			fnpGetORObjectX("LookkupSecondTextBox").sendKeys(value);
		}

		fnpClick_OR("LookupSearchBtn");

		try {
			fnpWaitTillClickable("FirstCheckBoxInLkpAFSch");
		} catch (Throwable t) {
			String searchedText = "";
			try {
				searchedText = driver.findElement(By.xpath(".//*[@id='mainForm:lookuptableid_data']/tr[1]/td[1]"))
						.getText();
			} catch (Throwable tsearchText) {
				// nothing to do
			}
			if (searchedText.toLowerCase().contains("no records found")) {
				throw new Exception("The data '" + value
						+ "' which is to be searched in lookup is not present in the lookup after search");
			} else {
				throw t;
			}
		}

		fnpClick_OR_WithoutWait("FirstCheckBoxInLkpAFSch");
		fnpLoading_waitInsideDialogBox();

	}

	/**** First search then select first checkbox in Lookup ****/
	public static void fnpSearchNSelectAllCheckBox(int LKPTextBoxNo, String value, int notusinglevel) throws Throwable {

		// fnpLoading_wait();
		/************** commented on 23-03-15 ********************/
		fnpWaitForVisible("LookupFirstTextBox");
		/************** commented on 23-03-15 ********************/

		if (value.trim().isEmpty()) {
			fnpClick_OR("FirstRadioBtnInLkpAFSch");

			return;
		}

		int beforeSearchRow = fnpCountRowsIn_LOOKUPTable("LookupRowsForCountPurpose");

		if (LKPTextBoxNo == 1) {
			// driver.findElement(By.xpath(".//*[@id='mainForm:newfdt:0:newco']")).sendKeys("Equals");
			fnpPFListModify("FirstSelectConditionInLookup", "Equals");
			fnpGetORObjectX("LookupFirstTextBox").sendKeys("");
			fnpGetORObjectX("LookupFirstTextBox").sendKeys(value);
		}
		if (LKPTextBoxNo == 2) {
			// driver.findElement(By.xpath(".//*[@id='mainForm:newfdt:1:newco']")).sendKeys("Equals");
			fnpPFListModify("SecondSelectConditionInLookup", "Equals");
			fnpGetORObjectX("LookkupSecondTextBox").sendKeys("");
			fnpGetORObjectX("LookkupSecondTextBox").sendKeys(value);
		}

		if (LKPTextBoxNo == 3) {
			// driver.findElement(By.xpath(".//*[@id='mainForm:newfdt:1:newco']")).sendKeys("Equals");
			fnpPFList("lookup_ThirdSelectSearchFielList", "lookup_ThirdSelectSearchFielListOptions", "Standard");
			fnpGetORObjectX("LookkupThirdTextBox").sendKeys("");
			fnpGetORObjectX("LookkupThirdTextBox").sendKeys(value);
		}
		fnpClick_OR("LookupSearchBtn");

		try {
			fnpWaitTillClickable("FirstCheckBoxInLkpAFSch");
		} catch (Throwable t) {
			String searchedText = "";
			try {
				searchedText = driver.findElement(By.xpath(".//*[@id='mainForm:lookuptableid_data']/tr[1]/td[1]"))
						.getText();
			} catch (Throwable tsearchText) {
				// nothing to do
			}
			if (searchedText.toLowerCase().contains("no records found")) {
				throw new Exception("The data '" + value
						+ "' which is to be searched in lookup is not present in the lookup after search");
			} else {
				throw t;
			}
		}

		// fnpClick_OR_WithoutWait("FirstCheckBoxInLkpAFSch");
		fnpClick_OR_WithoutWait("SelectAll_TopCheckBoxInLkpAFSch");
		fnpLoading_waitInsideDialogBox();

	}

	/**** Select first checkbox in Lookup without search ****/
	public static void fnpWithoutSearchSelectFirstRadioBtn() throws Throwable {

		// fnpLoading_wait();
		fnpWaitForVisible("LookupFirstTextBox");

		fnpClick_OR("FirstRadioBtnInLkpAFSch");

	}

	/**** May be not using this ****/
	/**** Wait till table gets refreshed when searching ****/
	public static void fnpTableRefreshedWait(String TableXpathKey) throws Throwable {
		int beforeSearchRow = fnpCountRowsInTable(TableXpathKey);
		int start = 0;
		int afterSearchRow = beforeSearchRow;

		while (beforeSearchRow == afterSearchRow) {
			Thread.sleep(1000);
			fnpLoading_wait();
			afterSearchRow = fnpCountRowsInTable(TableXpathKey);
			start++;
			// if (start > (Integer.parseInt(CONFIG.getProperty("genMax_waitTime")) * 2)) {
			if (start > 2) {
				break;

			}
		}

		Thread.sleep(500);
	}

	/****
	 * Search workd order and click Edit button (This is used when you come back
	 * after alerts checking)
	 ****/
	public static void fnpSearchWorkOrderAndClickEditAlso(String workOrderNo) throws Throwable {
		String p = (String) hashXlData.get("Alerts_Verify_Flag");

		try {
			if (((String) hashXlData.get("Alerts_Verify_Flag")).equalsIgnoreCase("No")) {
				return;
			}
		} catch (NullPointerException e) {
			return;
		}

		fnpSearchWorkOrderOnly(workOrderNo);

	}

	/****
	 * Search workd order and click Edit button (This is used when you come back
	 * after alerts checking) And after Restart the browser
	 ****/
	public static void fnpSearchWorkOrderAndClickEditAlso_AfterRestartWhenAlertsNo(String workOrderNo)
			throws Throwable {

		try {
			if (((String) hashXlData.get("Alerts_Verify_Flag")).equalsIgnoreCase("Yes")) {
				return;
			}
		} catch (NullPointerException e) {
			// nothing to do just ignore it. Null pointer exception will be
			// thrown when variable is not present.
			fnpMymsg("  ---caught NullPointerExceptin in fnpSearchWorkOrderAndClickEditAlso_AfterRestartWhenAlertsNo");
			return;
		}

		fnpSearchWorkOrderOnly(workOrderNo);

	}

	/**** Search workd order and click Edit button ****/
	public static void fnpSearchWorkOrderOnly(String workOrderNo) throws Throwable {

		if ((currentSuiteName.equalsIgnoreCase(iAg_suite_Name))) {
			// fnpCommonClickTopMainMenu("Menu", "FirstRefrenceLinkJustBelowToMenu_foriAG",
			// "SearchMembershipLink", "SearchCorpFacilityTxtBx");
			fnpCommonClickTopMainMenu("Menu", "FirstRefrenceLinkJustBelowToMenu_foriAG", "SearchMembershipLink",
					"WorkOrderNoField");
		} else {
			fnpCommonClickTopMainMenu("Menu", "FirstRefrenceLinkJustBelowToMenu", "SearchWorkOrderLink",
					"WorkOrderNoField");
		}

		fnpMymsg("Just before waiting for clickable workorderfield");
		fnpWaitTillVisiblityOfElementNClickable_OR("WorkOrderNoField");
		fnpWaitTillVisiblityOfElementNClickable_OR("MainSearchButton");
		fnpWaitForVisible("MainSearchButton");
		fnpMymsg("Just before inserting value in workorderfield");
		fnpType("OR", "WorkOrderNoField", workOrderNo);
		fnpMymsg("Just after inserting value in workorderfield");

		fnpWaitTillVisiblityOfElementNClickable_OR("MainSearchButton");
		fnpClick_OR("MainSearchButton");

		String s = fnpFetchFromStSearchTable(1, 1);
		int j = 0;
		// fnpLoading_wait();
		while (s.contains("No records found") && j < 15) {
			j++;
			Thread.sleep(1000);
			s = fnpFetchFromStSearchTable(1, 1);
		}

		fnpClickALinkInATable(s);

		fnpLoading_wait();
		fnpIpulseDuringLoading();

	}

	public static void fnpSearchActionItemsOnly(String actionItemNo) throws Throwable {

		fnpCommonClickTopMainMenu("Menu", "FirstRefrenceLinkJustBelowToMenu", "SearchActionItemsLink",
				"SearchAIScreen_AINoField");

		fnpMymsg("Just before waiting for clickable SearchAIScreen_AINoField");
		fnpWaitTillVisiblityOfElementNClickable_OR("SearchAIScreen_AINoField");
		fnpWaitTillVisiblityOfElementNClickable_OR("MainSearchButton");
		fnpWaitForVisible("MainSearchButton");
		fnpMymsg("Just before inserting value in SearchAIScreen_AINoField");
		fnpType("OR", "SearchAIScreen_AINoField", actionItemNo);
		fnpMymsg("Just after inserting value in SearchAIScreen_AINoField");

		fnpWaitTillVisiblityOfElementNClickable_OR("MainSearchButton");
		fnpClick_OR("MainSearchButton");

		String s = fnpFetchFromStSearchTable(1, 1);
		int j = 0;
		// fnpLoading_wait();
		while (s.contains("No records found") && j < 15) {
			j++;
			Thread.sleep(1000);
			s = fnpFetchFromStSearchTable(1, 1);
		}

		Assert.assertEquals(s, actionItemNo,
				"Search Action item functionality is not working as script searching for this '" + actionItemNo
						+ "' but it got this '" + s + "' in searched result.");

		fnpClickALinkInATable(s);

		fnpLoading_wait();
		fnpIpulseDuringLoading();

	}

	/**** Search Membership and click Edit button ****/
	public static void fnpSearchMembershipOnly(String workOrderNo) throws Throwable {

		fnpCommonClickTopMainMenu("Menu", "FirstRefrenceLinkJustBelowToMenu", "SearchMembershipLink",
				"WorkOrderNoField");

		fnpMymsg("Just before waiting for clickable workorderfield");
		fnpWaitTillVisiblityOfElementNClickable_OR("WorkOrderNoField");
		fnpWaitTillVisiblityOfElementNClickable_OR("MainSearchButton");
		fnpWaitForVisible("MainSearchButton");
		fnpMymsg("Just before inserting value in workorderfield");
		fnpType("OR", "WorkOrderNoField", workOrderNo);
		fnpMymsg("Just after inserting value in workorderfield");

		fnpWaitTillVisiblityOfElementNClickable_OR("MainSearchButton");
		fnpClick_OR("MainSearchButton");

		String s = fnpFetchFromStSearchTable(1, 1);
		int j = 0;
		// fnpLoading_wait();
		while (s.contains("No records found") && j < 15) {
			j++;
			Thread.sleep(1000);
			s = fnpFetchFromStSearchTable(1, 1);
		}

		fnpClickALinkInATable(s);

		fnpLoading_wait();
		fnpIpulseDuringLoading();

	}

	/**** It is using xpath not CSS Selector ******/
	/****
	 * To find sequence number of a column having a name
	 * 
	 * @throws Throwable
	 ****/
	public static int fnpFindColumnIndex2(String TableHeaderXpathName, String columnName) throws Throwable {
		int colIndex = 0;
		String xpathHeader = OR.getProperty(TableHeaderXpathName) + "/tr/th";

		// int HeaderCount = fnpGetORObject_list(xpathHeader,
		// Integer.parseInt(CONFIG.getProperty("implicitWaitTime"))).size();
		int HeaderCount = fnpGetORObject_list_NOR(xpathHeader, Integer.parseInt(CONFIG.getProperty("implicitWaitTime")),
				1).size();

		for (int i = 1; i < HeaderCount + 1; i++) {

			String xpathExpression = OR.getProperty(TableHeaderXpathName) + "/tr/th[" + i + "]";
			String headerValue = driver.findElement(By.xpath(xpathExpression)).getText().trim();
			if (columnName.equalsIgnoreCase(headerValue)) {
				colIndex = i;
				break;

			}

		}
		return colIndex;

	}

	/*
	 * public static int fnpFindColumnIndex_headerValueInSpan(String
	 * TableHeaderXpathName, String columnName) throws Throwable {
	 * int colIndex = 0;
	 * String xpathHeader = OR.getProperty(TableHeaderXpathName) + "/tr/th";
	 * 
	 * int HeaderCount = fnpGetORObject_list(xpathHeader,
	 * Integer.parseInt(CONFIG.getProperty("implicitWaitTime"))).size();
	 * 
	 * for (int i = 1; i < HeaderCount + 1; i++) {
	 * 
	 * String xpathExpression = OR.getProperty(TableHeaderXpathName) + "/tr/th[" + i
	 * + "]";
	 * String headerValue =
	 * driver.findElement(By.xpath(xpathExpression)).getText().trim();
	 * if (columnName.equalsIgnoreCase(headerValue)) {
	 * colIndex = i;
	 * break;
	 * 
	 * }
	 * 
	 * }
	 * return colIndex;
	 * 
	 * }
	 */
	/**** It is using CSS Selector not xpath ******/
	/********* Basically it return column sequence number not index *******/
	public static int fnpFindColumnIndex(String TableHeaderXpathName, String columnName) throws Throwable {
		int retries = 0;

		while (true) {

			retries++;

			try {
				int colIndex = 0;
				// String[] idString = OR.getProperty(TableHeaderXpathName).split("'", -1);
				String[] idString = null;
				if (OR.getProperty(TableHeaderXpathName).contains("'")) {
					idString = OR.getProperty(TableHeaderXpathName).split("'", -1);
				} else {
					if (OR.getProperty(TableHeaderXpathName).contains("\"")) {
						idString = OR.getProperty(TableHeaderXpathName).split("\"", -1);
					}
				}

				String xpathHeaderCss = "table thead[id='" + idString[1] + "'] tr th";
				int HeaderCount = driver.findElements(By.cssSelector(xpathHeaderCss)).size();
				for (int i = 1; i < HeaderCount + 1; i++) {
					String CssExpression = "table thead[id='" + idString[1] + "'] tr th:nth-child(" + i + ")";
					String headerValue = driver.findElement(By.cssSelector(CssExpression)).getText().trim();
					String headerValueWithoutTrim = driver.findElement(By.cssSelector(CssExpression)).getText();
					if (columnName.equalsIgnoreCase(headerValue) || columnName.equalsIgnoreCase(headerValueWithoutTrim)
							|| columnName.trim().equalsIgnoreCase(headerValue.trim())) {
						colIndex = i;
						fnpMymsg("@@@@@@@   -----col no is ---for '" + headerValue + "' is -----" + colIndex);
						break;

					}

				}

				if (colIndex > 0) {
					return colIndex;
				} else {

					if (retries > 4) {
						throw new Exception("Col no. is not calculated for -- '" + columnName
								+ "' for table having xpath ---'" + TableHeaderXpathName + "'.");
					} else {
						// going again in loop to calculate again
						// fnpLoading_wait();
					}
				}

			} catch (org.openqa.selenium.StaleElementReferenceException s) {
				if (retries > 4) {
					throw new Exception("StaleElementReferenceException is thrown ---Col no. is not calculated for -- '"
							+ columnName + "' for table having xpath ---'"
							+ TableHeaderXpathName + "'.");
				} else {
					// going again in loop to calculate again
					// fnpLoading_wait();
				}
			}

			catch (Throwable t) {
				if (retries < 2) {
					fnpMymsg("In catch block for try chance -" + retries);
					Thread.sleep(1000);
					// fnpLoading_wait();
				} else {
					String msg = t.getMessage();
					throw new Exception(msg);
				}

			}

		}
	}

	/**** It is using CSS Selector not xpath ******/
	/********* Basically it return column sequence number not index *******/
	public static int fnpFindColumnIndex_NOR(String TableHeaderXpath, String columnName) throws Throwable {
		int retries = 0;

		while (true) {

			retries++;

			try {
				int colIndex = 0;
				String[] idString = TableHeaderXpath.split("'", -1);
				String xpathHeaderCss = "table thead[id='" + idString[1] + "'] tr th";
				int HeaderCount = driver.findElements(By.cssSelector(xpathHeaderCss)).size();
				for (int i = 1; i < HeaderCount + 1; i++) {
					String CssExpression = "table thead[id='" + idString[1] + "'] tr th:nth-child(" + i + ")";
					String headerValue = driver.findElement(By.cssSelector(CssExpression)).getText().trim();
					String headerValueWithoutTrim = driver.findElement(By.cssSelector(CssExpression)).getText();
					if (columnName.equalsIgnoreCase(headerValue) || columnName.equalsIgnoreCase(headerValueWithoutTrim)
							|| columnName.trim().equalsIgnoreCase(headerValue.trim())) {
						colIndex = i;
						fnpMymsg("@@@@@@@   -----col no is ---for '" + headerValue + "' is -----" + colIndex);
						break;

					}

				}

				if (colIndex > 0) {
					return colIndex;
				} else {

					if (retries > 4) {
						throw new Exception("Col no. is not calculated for -- '" + columnName
								+ "' for table having xpath ---'" + TableHeaderXpath + "'.");
					} else {
						// going again in loop to calculate again
						// fnpLoading_wait();
					}
				}

			} catch (org.openqa.selenium.StaleElementReferenceException s) {
				if (retries > 4) {
					throw new Exception("StaleElementReferenceException is thrown ---Col no. is not calculated for -- '"
							+ columnName + "' for table having xpath ---'"
							+ TableHeaderXpath + "'.");
				} else {
					// going again in loop to calculate again
					// fnpLoading_wait();
				}
			}

			catch (Throwable t) {
				if (retries < 2) {
					fnpMymsg("In catch block for try chance -" + retries);
					Thread.sleep(1000);
					// fnpLoading_wait();
				} else {
					String msg = t.getMessage();
					throw new Exception(msg);
				}

			}

		}
	}

	/**** It is using xpath ******/
	/********* Basically it return column sequence number not index *******/
	public static int fnpFindColumnIndex_NOR_TraversingFromVariousNodes(String TableHeaderXpath, String columnName)
			throws Throwable {
		int retries = 0;

		while (true) {

			retries++;

			try {
				int colIndex = 0;
				String xpathHeader = TableHeaderXpath + "/tr/th";
				int HeaderCount = driver.findElements(By.xpath(xpathHeader)).size();

				for (int i = 1; i < HeaderCount + 1; i++) {
					String xpathExpression = TableHeaderXpath + "/tr/th[" + i + "]";
					String headerValue = driver.findElement(By.xpath(xpathExpression)).getText().trim();
					String headerValueWithoutTrim = driver.findElement(By.xpath(xpathExpression)).getText();
					if (columnName.equalsIgnoreCase(headerValue) || columnName.equalsIgnoreCase(headerValueWithoutTrim)
							|| columnName.trim().equalsIgnoreCase(headerValue.trim())) {
						colIndex = i;
						fnpMymsg("@@@@@@@   -----col no is ---for '" + headerValue + "' is -----" + colIndex);
						break;

					}

				}

				if (colIndex > 0) {
					return colIndex;
				} else {

					if (retries > 4) {
						throw new Exception("Col no. is not calculated for -- '" + columnName
								+ "' for table having xpath ---'" + TableHeaderXpath + "'.");
					} else {
						// going again in loop to calculate again
						// fnpLoading_wait();
					}
				}

			} catch (org.openqa.selenium.StaleElementReferenceException s) {
				if (retries > 4) {
					throw new Exception("StaleElementReferenceException is thrown ---Col no. is not calculated for -- '"
							+ columnName + "' for table having xpath ---'"
							+ TableHeaderXpath + "'.");
				} else {
					// going again in loop to calculate again
					// fnpLoading_wait();
				}
			}

			catch (Throwable t) {
				if (retries < 2) {
					fnpMymsg("In catch block for try chance -" + retries);
					Thread.sleep(1000);
					// fnpLoading_wait();
				} else {
					String msg = t.getMessage();
					throw new Exception(msg);
				}

			}

		}
	}

	/**** It is using xpath ******/
	/********* Basically it return column sequence number not index *******/
	public static int fnpFindColumnIndex_NOR_TraversingFromVariousNodes_SAM(String TableHeaderXpath, String columnName,
			int startingFromColNo) throws Throwable {
		int retries = 0;

		while (true) {

			retries++;

			try {
				int colIndex = 0;
				String xpathHeader = TableHeaderXpath + "/tr/th";
				int HeaderCount = driver.findElements(By.xpath(xpathHeader)).size();

				for (int i = startingFromColNo; i < HeaderCount + 1; i++) {
					String xpathExpression = TableHeaderXpath + "/tr/th[" + i + "]/a[2]";
					String headerValue = driver.findElement(By.xpath(xpathExpression)).getText().trim();
					String headerValueWithoutTrim = driver.findElement(By.xpath(xpathExpression)).getText();
					if (columnName.equalsIgnoreCase(headerValue) || columnName.equalsIgnoreCase(headerValueWithoutTrim)
							|| columnName.trim().equalsIgnoreCase(headerValue.trim())) {
						colIndex = i;
						fnpMymsg("@@@@@@@   -----col no is ---for '" + headerValue + "' is -----" + colIndex);
						break;

					}

				}

				if (colIndex > 0) {
					return colIndex;
				} else {

					if (retries > 4) {
						throw new Exception("Col no. is not calculated for -- '" + columnName
								+ "' for table having xpath ---'" + TableHeaderXpath + "'.");
					} else {
						// going again in loop to calculate again
						// fnpLoading_wait();
					}
				}

			} catch (org.openqa.selenium.StaleElementReferenceException s) {
				if (retries > 4) {
					throw new Exception("StaleElementReferenceException is thrown ---Col no. is not calculated for -- '"
							+ columnName + "' for table having xpath ---'"
							+ TableHeaderXpath + "'.");
				} else {
					// going again in loop to calculate again
					// fnpLoading_wait();
				}
			}

			catch (Throwable t) {
				if (retries < 2) {
					fnpMymsg("In catch block for try chance -" + retries);
					Thread.sleep(1000);
					// fnpLoading_wait();
				} else {
					String msg = t.getMessage();
					throw new Exception(msg);
				}

			}

		}
	}

	/**** To find the Row no in a table ****/
	public static int fnpFindRow(String TableDataXpathName, String matchingName, int colNo) throws Throwable {

		int retries = 0;
		while (true) {
			try {

				retries++;
				fnpWaitForVisible(TableDataXpathName);
				String xpathRow = OR.getProperty(TableDataXpathName) + "/tr";
				int TotalRowCount = driver.findElements(By.xpath(xpathRow)).size();
				int rowcountJ = 0;
				while (TotalRowCount == 0) {
					rowcountJ++;
					Thread.sleep(1000);
					TotalRowCount = driver.findElements(By.xpath(xpathRow)).size();
					if (rowcountJ > 2) {
						break;
					}

				}

				int resultRowNo = 0;
				// int resultColNo = 0;
				String xpathExpression = null;
				String Value = null;
				for (int i = 1; i < TotalRowCount + 1; i++) {
					xpathExpression = xpathRow + "[" + i + "]/td[" + colNo + "]";
					// Value = driver.findElement(By.xpath(xpathExpression)).getText().trim();

					// 12-04-2018 using below in try block because sometime (as in case of iAg at
					// snapshot tab) in middle tr does not have td tag directly
					try {
						driver.manage().timeouts().implicitlyWait(1, TimeUnit.MILLISECONDS);
						Value = driver.findElement(By.xpath(xpathExpression)).getText().trim();
					} catch (Throwable t) {
						Value = "";
						fnpMymsg("************************************************************");
						fnpMymsg("Getting some error for getting data. Error is ---" + t.getMessage());
						fnpMymsg("************************************************************");
					} finally {
						driver.manage().timeouts().implicitlyWait(
								Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);
					}
					// Value=Value.replace("?", "");//only for 'CertDecUpdate ? Ext' ai in wras
					Value = Value.replaceAll("[^a-zA-Z0-9/ ]", "");
					matchingName = matchingName.replaceAll("[^a-zA-Z0-9/ ]", "");
					msg = "Debug:: Actual value is --'" + Value + "' and expected value is --'" + matchingName
							+ "' and are they equal --'" + Value.equalsIgnoreCase(matchingName) + "' .";
					// fnpMymsg(msg);
					APP_LOGS.debug(msg);
					if (Value.equalsIgnoreCase(matchingName)) {
						resultRowNo = i;
						break;

					}

				}

				if (resultRowNo > 0) {
					return resultRowNo;
				} else {

					if (retries > 4) {
						throw new Exception("Row and Col no. is not calculated for '" + matchingName
								+ "' . Row no. is '" + resultRowNo + "' and Column no. is '" + colNo
								+ "' . Either it is not present in table or may be some error in code.");
					} else {
						// going again in loop to calculate again
						// fnpLoading_wait();
						fnpMymsg("Row and Col no. is not calculated for '" + matchingName + "' . Row no. is '"
								+ resultRowNo + "' and Column no. is '" + colNo
								+ "' . So going to calculate again");
						Thread.sleep(2000);
					}
				}

			}

			catch (org.openqa.selenium.StaleElementReferenceException e) {
				if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					retries++;
					fnpMymsg(retries + "In staleElementException catch block of fnpFindRow function for "
							+ matchingName);
					continue;
				} else {
					throw e;
				}

			} catch (org.openqa.selenium.InvalidSelectorException is) {
				if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					retries++;
					fnpMymsg(retries + "In InvalidSelectorException catch block of fnpFindRow function for "
							+ matchingName);
					continue;
				} else {
					throw is;
				}
			} catch (Throwable t) {
				if (retries < 2) {
					fnpMymsg("In catch block of fnpFindRow for try chance -" + retries);
					Thread.sleep(1000);
					// fnpLoading_wait();
				} else {
					String msg = t.getMessage();
					throw new Exception(msg);
				}

			}
		}
	}

	public static int fnpFindRow_contains(String TableDataXpathName, String matchingName, int colNo) throws Throwable {

		int retries = 0;
		while (true) {
			try {

				retries++;
				fnpWaitForVisible(TableDataXpathName);
				String xpathRow = OR.getProperty(TableDataXpathName) + "/tr";
				int TotalRowCount = driver.findElements(By.xpath(xpathRow)).size();
				int rowcountJ = 0;
				while (TotalRowCount == 0) {
					rowcountJ++;
					Thread.sleep(1000);
					TotalRowCount = driver.findElements(By.xpath(xpathRow)).size();
					if (rowcountJ > 2) {
						break;
					}

				}

				int resultRowNo = 0;
				// int resultColNo = 0;
				String xpathExpression = null;
				String Value = null;
				for (int i = 1; i < TotalRowCount + 1; i++) {
					xpathExpression = xpathRow + "[" + i + "]/td[" + colNo + "]";
					// Value = driver.findElement(By.xpath(xpathExpression)).getText().trim();

					// 12-04-2018 using below in try block because sometime (as in case of iAg at
					// snapshot tab) in middle tr does not have td tag directly
					try {
						driver.manage().timeouts().implicitlyWait(1, TimeUnit.MILLISECONDS);
						Value = driver.findElement(By.xpath(xpathExpression)).getText().trim();
					} catch (Throwable t) {
						Value = "";
						fnpMymsg("************************************************************");
						fnpMymsg("Getting some error for getting data. Error is ---" + t.getMessage());
						fnpMymsg("************************************************************");
					} finally {
						driver.manage().timeouts().implicitlyWait(
								Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);
					}

					if (Value.contains(matchingName)) {
						resultRowNo = i;
						break;

					}

				}

				if (resultRowNo > 0) {
					return resultRowNo;
				} else {

					if (retries > 4) {
						throw new Exception("Row and Col no. is not calculated for '" + matchingName
								+ "' . Row no. is '" + resultRowNo + "' and Column no. is '" + colNo
								+ "' . Either it is not present in table or may be some error in code.");
					} else {
						// going again in loop to calculate again
						// fnpLoading_wait();
						fnpMymsg("Row and Col no. is not calculated for '" + matchingName + "' . Row no. is '"
								+ resultRowNo + "' and Column no. is '" + colNo
								+ "' . So going to calculate again");
						Thread.sleep(2000);
					}
				}

			}

			catch (org.openqa.selenium.StaleElementReferenceException e) {
				if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					retries++;
					fnpMymsg(retries + "In staleElementException catch block of fnpFindRow function for "
							+ matchingName);
					continue;
				} else {
					throw e;
				}

			} catch (org.openqa.selenium.InvalidSelectorException is) {
				if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					retries++;
					fnpMymsg(retries + "In InvalidSelectorException catch block of fnpFindRow function for "
							+ matchingName);
					continue;
				} else {
					throw is;
				}
			} catch (Throwable t) {
				if (retries < 2) {
					fnpMymsg("In catch block of fnpFindRow for try chance -" + retries);
					Thread.sleep(1000);
					// fnpLoading_wait();
				} else {
					String msg = t.getMessage();
					throw new Exception(msg);
				}

			}
		}
	}

	/**** To find the Row no in a table ****/
	public static int fnpFindRow_NOR(String TableDataXpath, String matchingName, int colNo) throws Throwable {

		int retries = 0;
		while (true) {
			try {

				retries++;
				fnpWaitForVisible_NotInOR(TableDataXpath);
				String xpathRow = TableDataXpath + "/tr";
				int TotalRowCount = driver.findElements(By.xpath(xpathRow)).size();
				int rowcountJ = 0;
				while (TotalRowCount == 0) {
					rowcountJ++;
					Thread.sleep(1000);
					TotalRowCount = driver.findElements(By.xpath(xpathRow)).size();
					if (rowcountJ > 2) {
						break;
					}

				}

				int resultRowNo = 0;
				// int resultColNo = 0;
				String xpathExpression = null;
				String Value = null;
				for (int i = 1; i < TotalRowCount + 1; i++) {
					xpathExpression = xpathRow + "[" + i + "]/td[" + colNo + "]";
					Value = driver.findElement(By.xpath(xpathExpression)).getText().trim();
					if (Value.equalsIgnoreCase(matchingName)) {
						resultRowNo = i;
						break;

					}

				}

				if (resultRowNo > 0) {
					return resultRowNo;
				} else {

					if (retries > 4) {
						throw new Exception("Row and Col no. is not calculated for '" + matchingName
								+ "' . Row no. is '" + resultRowNo + "' and Column no. is '" + colNo + "' .");
					} else {
						// going again in loop to calculate again
						// fnpLoading_wait();
						fnpMymsg("Row and Col no. is not calculated for '" + matchingName + "' . Row no. is '"
								+ resultRowNo + "' and Column no. is '" + colNo
								+ "' . So going to calculate again");
						Thread.sleep(2000);
					}
				}

			}

			catch (org.openqa.selenium.StaleElementReferenceException e) {
				if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					retries++;
					fnpMymsg(retries + "In staleElementException catch block of fnpFindRow_NOR function for "
							+ matchingName);
					continue;
				} else {
					throw e;
				}

			} catch (org.openqa.selenium.InvalidSelectorException is) {
				if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					retries++;
					fnpMymsg(retries + "In InvalidSelectorException catch block of fnpFindRow_NOR function for "
							+ matchingName);
					continue;
				} else {
					throw is;
				}
			} catch (Throwable t) {
				if (retries < 2) {
					fnpMymsg("In catch block of fnpFindRow for try chance -" + retries);
					Thread.sleep(1000);
					// fnpLoading_wait();
				} else {
					String msg = t.getMessage();
					throw new Exception(msg);
				}

			}
		}
	}

	/**** To find the Row no in a table ****/
	public static int fnpFindRow_ActionItem(String TableDataXpathName, String matchingName, int colNo)
			throws Throwable {

		int retries = 0;
		while (true) {
			try {

				retries++;
				fnpWaitForVisible(TableDataXpathName);
				String xpathRow = OR.getProperty(TableDataXpathName) + "/tr";
				int TotalRowCount = driver.findElements(By.xpath(xpathRow)).size();
				int rowcountJ = 0;
				while (TotalRowCount == 0) {
					rowcountJ++;
					Thread.sleep(1000);
					TotalRowCount = driver.findElements(By.xpath(xpathRow)).size();
					if (rowcountJ > 2) {
						break;
					}

				}

				int resultRowNo = 0;
				// int resultColNo = 0;
				String xpathExpression = null;
				String Value = null;
				for (int i = 1; i < TotalRowCount + 1; i++) {
					xpathExpression = xpathRow + "[" + i + "]/td[" + colNo + "]";
					Value = driver.findElement(By.xpath(xpathExpression)).getText().trim();
					// Value=Value.replace("?", "");//only for 'CertDecUpdate ? Ext' ai in wras
					Value = Value.replaceAll("[^a-zA-Z0-9/ ]", "");
					matchingName = matchingName.replaceAll("[^a-zA-Z0-9/ ]", "");
					msg = "Debug:: Actual value is --'" + Value + "' and expected value is --'" + matchingName
							+ "' and are they equal --'" + Value.equalsIgnoreCase(matchingName) + "' .";
					// fnpMymsg(msg);
					APP_LOGS.debug(msg);

					if (Value.equalsIgnoreCase(matchingName)) {
						resultRowNo = i;
						break;

					}

				}

				if (resultRowNo > 0) {
					return resultRowNo;
				} else {

					if (retries > 4) {
						throw new Exception("Either Action item '" + matchingName + "' has not been generated or "
								+ "Row and Col no. is not calculated for '" + matchingName
								+ "' . Row no. is '" + resultRowNo + "' and Column no. is '" + colNo + "' .");
					} else {
						// going again in loop to calculate again
						// fnpLoading_wait();
						fnpMymsg("Row and Col no. is not calculated for '" + matchingName + "' . Row no. is '"
								+ resultRowNo + "' and Column no. is '" + colNo
								+ "' . So going to calculate again");
						Thread.sleep(2000);
					}
				}

			}

			catch (org.openqa.selenium.StaleElementReferenceException e) {
				if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					retries++;
					fnpMymsg(retries + "In staleElementException catch block of fnpFindRow function for "
							+ matchingName);
					continue;
				} else {
					throw e;
				}

			} catch (org.openqa.selenium.InvalidSelectorException is) {
				if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					retries++;
					fnpMymsg(retries + "In InvalidSelectorException catch block of fnpFindRow function for "
							+ matchingName);
					continue;
				} else {
					throw is;
				}
			} catch (Throwable t) {
				if (retries < 2) {
					fnpMymsg("In catch block of fnpFindRow for try chance -" + retries);
					Thread.sleep(1000);
					// fnpLoading_wait();
				} else {
					String msg = t.getMessage();
					throw new Exception(msg);
				}

			}
		}
	}

	/**** To find the Row no in a table ****/
	public static int fnpFindRowContainsName(String TableDataXpathName, String containsName, int colNo)
			throws Throwable {

		int retries = 0;
		while (true) {
			try {

				retries++;
				String xpathRow = OR.getProperty(TableDataXpathName) + "/tr";
				int TotalRowCount = driver.findElements(By.xpath(xpathRow)).size();
				int rowcountJ = 0;
				while (TotalRowCount == 0) {
					rowcountJ++;
					Thread.sleep(1000);
					TotalRowCount = driver.findElements(By.xpath(xpathRow)).size();
					if (rowcountJ > 5) {
						break;
					}

				}

				int resultRowNo = 0;
				// int resultColNo = 0;
				String xpathExpression = null;
				String Value = null;
				for (int i = 1; i < TotalRowCount + 1; i++) {
					xpathExpression = xpathRow + "[" + i + "]/td[" + colNo + "]";
					Value = driver.findElement(By.xpath(xpathExpression)).getText().trim();
					if (Value.toLowerCase().contains(containsName.toLowerCase())) {
						resultRowNo = i;
						break;

					}

				}

				if (resultRowNo > 0) {
					return resultRowNo;
				} else {

					if (retries > 4) {
						throw new Exception("Row and Col no. is not calculated for '" + containsName
								+ "' . Row no. is '" + resultRowNo + "' and Column no. is '" + colNo + "' .");
					} else {
						// going again in loop to calculate again
						// fnpLoading_wait();
						fnpMymsg("Row and Col no. is not calculated for '" + containsName + "' . Row no. is '"
								+ resultRowNo + "' and Column no. is '" + colNo
								+ "' . So going to calculate again");
						Thread.sleep(2000);
					}
				}

			}

			catch (org.openqa.selenium.StaleElementReferenceException e) {
				if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					retries++;
					fnpMymsg(retries + "In staleElementException catch block of fnpFindRowContainsName function for "
							+ containsName);
					continue;
				} else {
					throw e;
				}

			} catch (org.openqa.selenium.InvalidSelectorException is) {
				if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					retries++;
					fnpMymsg(retries + "In InvalidSelectorException catch block of fnpFindRowContainsName function for "
							+ containsName);
					continue;
				} else {
					throw is;
				}

			} catch (Throwable t) {
				if (retries < 4) {
					fnpMymsg("In catch block of fnpFindRowContainsName for try chance -" + retries);
					Thread.sleep(1000);
					// fnpLoading_wait();
				} else {
					String msg = t.getMessage();
					throw new Exception(msg);
				}

			}
		}
	}

	/**** To find the Row no in a table ****/
	public static int fnpFindRowContainsName_NOR(String tableDataXpath, String containsName, int colNo)
			throws Throwable {

		int resultRowNo = 0;
		int retries = 0;
		while (true) {
			try {

				retries++;
				String xpathRow = tableDataXpath + "/tr";
				int TotalRowCount = driver.findElements(By.xpath(xpathRow)).size();
				int rowcountJ = 0;
				while (TotalRowCount == 0) {
					rowcountJ++;
					Thread.sleep(1000);
					TotalRowCount = driver.findElements(By.xpath(xpathRow)).size();
					if (rowcountJ > 5) {
						break;
					}

				}

				// int resultColNo = 0;
				String xpathExpression = null;
				String Value = null;
				for (int i = 1; i < TotalRowCount + 1; i++) {
					xpathExpression = xpathRow + "[" + i + "]/td[" + colNo + "]";
					Value = driver.findElement(By.xpath(xpathExpression)).getText().trim();
					if (Value.toLowerCase().contains(containsName.toLowerCase())) {
						resultRowNo = i;
						break;

					}

				}

				if (resultRowNo > 0) {
					return resultRowNo;
				} else {

					if (retries > 4) {
						throw new Exception("Row and Col no. is not calculated for '" + containsName
								+ "' . Row no. is '" + resultRowNo + "' and Column no. is '" + colNo + "' .");
					} else {
						// going again in loop to calculate again
						// fnpLoading_wait();
						fnpMymsg("Row and Col no. is not calculated for '" + containsName + "' . Row no. is '"
								+ resultRowNo + "' and Column no. is '" + colNo
								+ "' . So going to calculate again");
						Thread.sleep(2000);
					}
				}

			}

			catch (org.openqa.selenium.StaleElementReferenceException e) {
				if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					retries++;
					fnpMymsg(retries + "In staleElementException catch block of fnpFindRow function for "
							+ containsName);
					continue;
				} else {
					throw e;
				}

			} catch (org.openqa.selenium.InvalidSelectorException is) {
				if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					retries++;
					fnpMymsg(retries + "In InvalidSelectorException catch block of fnpFindRow function for "
							+ containsName);
					continue;
				} else {
					throw is;
				}

			} catch (Throwable t) {
				if (retries < 4) {
					fnpMymsg("In catch block of fnpFindRow for try chance -" + retries);
					Thread.sleep(1000);
					// fnpLoading_wait();
				} else {

					return resultRowNo;

					/*
					 * String msg = t.getMessage(); throw new Exception(msg);
					 */
				}

			}
		}
	}

	/****
	 * To find the Row no in a table having paging + Also not throw error when
	 * not found as if not present in one page then may be present in next page
	 * , so not throw any error
	 ****/
	public static int fnpFindRow_usingForPagingToNotThrowErrorIfNotFoundInCurrentPage(String TableDataXpathName,
			String matchingName, int colNo) throws Throwable {

		int retries = 0;
		int resultRowNo = 0;
		while (true) {
			try {

				retries++;
				String xpathRow = OR.getProperty(TableDataXpathName) + "/tr";

				int TotalRowCount = fnpGetORObject_list_NOR(xpathRow, 1).size();
				int rowcountJ = 0;
				while (TotalRowCount == 0) {
					rowcountJ++;
					Thread.sleep(1000);

					TotalRowCount = fnpGetORObject_list_NOR(xpathRow, 1).size();
					if (rowcountJ > 5) {
						break;
					}

				}

				driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

				// int resultColNo = 0;
				String xpathExpression = null;
				String Value = null;
				for (int i = 1; i < TotalRowCount + 1; i++) {
					xpathExpression = xpathRow + "[" + i + "]/td[" + colNo + "]";
					Value = driver.findElement(By.xpath(xpathExpression)).getText().trim();
					if (Value.equalsIgnoreCase(matchingName)) {
						resultRowNo = i;
						break;

					}

				}

				if (resultRowNo > 0) {
					return resultRowNo;
				} else {

					if (retries > 2) {
						fnpMymsg("Row and Col no. is not calculated for '" + matchingName + "' . Row no. is '"
								+ resultRowNo + "' and Column no. is '" + colNo + "' .");

					} else {

						fnpMymsg("Row and Col no. is not calculated for '" + matchingName + "' . Row no. is '"
								+ resultRowNo + "' and Column no. is '" + colNo
								+ "' . So going to calculate again");
						// Thread.sleep(2000);
					}
				}
				return resultRowNo;

			}

			catch (org.openqa.selenium.StaleElementReferenceException e) {
				if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					retries++;
					fnpMymsg(retries + "In staleElementException catch block of fnpFindRow function for "
							+ matchingName);
					continue;
				} else {
					throw e;
				}
			} catch (Throwable t) {
				if (retries < 2) {
					fnpMymsg("In catch block of fnpFindRow for try chance -" + retries);

				} else {
					// String msg = t.getMessage();
					// throw new Exception(msg);
					fnpMymsg("@  ---resultRowNo is ---" + resultRowNo);
					fnpMymsg("@  ---this is not present in any row ---" + matchingName);

					return resultRowNo;
				}

			}

			finally {
				driver.manage().timeouts().implicitlyWait(Integer.parseInt(CONFIG.getProperty("implicitWaitTime")),
						TimeUnit.SECONDS);
			}
		}
	}

	/****
	 * To find the Row no in a table having paging + Also not throw error when
	 * not found as if not present in one page then may be present in next page
	 * , so not throw any error
	 ****/
	public static int fnpFindRowContainsName_usingForPagingToNotThrowErrorIfNotFoundInCurrentPage(
			String TableDataXpathName, String matchingName, int colNo) throws Throwable {

		int retries = 0;
		int resultRowNo = 0;
		while (true) {
			try {

				retries++;
				String xpathRow = OR.getProperty(TableDataXpathName) + "/tr";

				int TotalRowCount = fnpGetORObject_list_NOR(xpathRow, 1).size();
				int rowcountJ = 0;
				while (TotalRowCount == 0) {
					rowcountJ++;
					Thread.sleep(1000);

					TotalRowCount = fnpGetORObject_list_NOR(xpathRow, 1).size();
					if (rowcountJ > 5) {
						break;
					}

				}

				driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

				// int resultColNo = 0;
				String xpathExpression = null;
				String Value = null;
				for (int i = 1; i < TotalRowCount + 1; i++) {
					xpathExpression = xpathRow + "[" + i + "]/td[" + colNo + "]";
					Value = driver.findElement(By.xpath(xpathExpression)).getText().trim();
					if (Value.contains(matchingName)) {
						resultRowNo = i;
						break;

					}

				}

				if (resultRowNo > 0) {
					return resultRowNo;
				} else {

					if (retries > 2) {
						fnpMymsg("Row and Col no. is not calculated for '" + matchingName + "' . Row no. is '"
								+ resultRowNo + "' and Column no. is '" + colNo + "' .");

					} else {

						fnpMymsg("Row and Col no. is not calculated for '" + matchingName + "' . Row no. is '"
								+ resultRowNo + "' and Column no. is '" + colNo
								+ "' . So going to calculate again");
						// Thread.sleep(2000);
					}
				}
				return resultRowNo;

			}

			catch (org.openqa.selenium.StaleElementReferenceException e) {
				if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					retries++;
					fnpMymsg(retries + "In staleElementException catch block of fnpFindRow function for "
							+ matchingName);
					continue;
				} else {
					throw e;
				}
			} catch (Throwable t) {
				if (retries < 2) {
					fnpMymsg("In catch block of fnpFindRow for try chance -" + retries);

				} else {
					// String msg = t.getMessage();
					// throw new Exception(msg);
					fnpMymsg("@  ---resultRowNo is ---" + resultRowNo);
					fnpMymsg("@  ---this is not present in any row ---" + matchingName);

					return resultRowNo;
				}

			}

			finally {
				driver.manage().timeouts().implicitlyWait(Integer.parseInt(CONFIG.getProperty("implicitWaitTime")),
						TimeUnit.SECONDS);
			}
		}
	}

	/**** To check the alert is coming or not ****/
	public static void fnpCommonAlertGeneratedVerification(String hashXLKeyForAlertName,
			String AlertHeaderTableXpath_OR, String columnName, String AlertDataTableXpath_OR,
			String AlertWOFilterBoxXpath_OR, String workOrderNo) throws Throwable {

		try {
			if (((String) hashXlData.get("Alerts_Verify_Flag")).equalsIgnoreCase("No")) {
				return;
			}
		} catch (NullPointerException e) {
			return;
		}

		String AlertName = (String) hashXlData.get(hashXLKeyForAlertName);
		fnpMymsg("Going to verify the alert'" + AlertName + "' whether it is coming/generated or not.");

		AlertName = fnpFetchExactAlertName(AlertName);
		// if (AlertName == "") {
		if (AlertName.equalsIgnoreCase("")) {
			throw new Exception("Alert '" + hashXLKeyForAlertName + " ' is either not present or not visible. ");

		}

		String AlertNameXpath = ".//legend[contains(text(),'" + AlertName + "')]";

		WebElement wbElement = driver.findElement(By.xpath(OR.getProperty("FooterElement")));
		new Actions(driver).moveToElement(wbElement).perform();

		try {

			driver.findElement(By.xpath(AlertNameXpath)).click();
		} catch (Throwable t) {
			msg = "Alert '" + hashXLKeyForAlertName + " ' is either not present or not visible. ";
			fnpMymsg(msg);
			throw new Exception(msg);
		}

		fnpMymsg("Clicked '" + AlertName + "' alert link");

		fnpWaitForVisible(AlertWOFilterBoxXpath_OR);

		int colIndex = fnpFindColumnIndex(AlertHeaderTableXpath_OR, columnName);

		fnpType("OR", AlertWOFilterBoxXpath_OR, workOrderNo);

		WebDriverWait waitvar = new WebDriverWait(driver, Long.parseLong(CONFIG.getProperty("genMax_waitTime")));
		waitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(AlertDataTableXpath_OR))))
				.isEnabled();

		// fnpLoading_wait();
		String actualFirstColValue = fnpFetchFromTable(AlertDataTableXpath_OR, 1, 1);
		Assert.assertFalse(actualFirstColValue.contains("No records found"),
				"Alert in '" + AlertName + "' has NOT been generated");

		int retries = 0;
		while (true) {

			try {
				retries++;
				String s = fnpFetchFromTable(AlertDataTableXpath_OR, 1, colIndex);
				if (s.equalsIgnoreCase(workOrderNo)) {
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
					fnpMymsg(retries
							+ "In staleElementException catch block of fnpCommonAlertGeneratedVerification function. ");
					continue;
				} else {
					throw s;
				}
			}

			catch (Throwable t) {
				fnpDesireScreenshot("fnpCommonAlertGeneratedVerification_failed");
				String stackTrace = Throwables.getStackTraceAsString(t);
				String errorMsg = t.getMessage();
				errorMsg = errorMsg
						+ "failed in fnpCommonAlertGeneratedVerification ,plz see screenshot 'fnpCommonAlertGeneratedVerification_failed'.\n\n"
						+ stackTrace;
				throw new Exception(errorMsg);
			}

		}

		fnpMymsg("Inserted  '" + workOrderNo + "' work order no in '" + AlertName + "' WO filter box");

		actualFirstColValue = fnpFetchFromTable(AlertDataTableXpath_OR, 1, 1);

		Assert.assertFalse(actualFirstColValue.contains("No records found"),
				"Alert in '" + AlertName + "' has NOT been generated");

		String ActualWONo = fnpFetchFromTable(AlertDataTableXpath_OR, 1, colIndex);
		Assert.assertEquals(ActualWONo, workOrderNo, "Alert in '" + AlertName + "' has NOT been generated");
		fnpMymsg("++++++++++++++++++++++++++++++++++++++++++++++++");
		fnpMymsg(" Hence , Alert  '" + AlertName + "' has been generated successfully.");
		fnpMymsg("++++++++++++++++++++++++++++++++++++++++++++++++");
		fnpMymsg("");

		// ++++++++++++++++++++++++++++++++++++++++++++++++

	}

	public static void fnpCommonAlertGeneratedVerification_AI(String hashXLKeyForAlertName,
			String AlertHeaderTableXpath_OR, String columnName, String AlertDataTableXpath_OR,
			String AlertFilterBoxXpath_OR, String AI_NO) throws Throwable {

		String AlertName = (String) hashXlData.get(hashXLKeyForAlertName);
		fnpMymsg("Going to verify the alert'" + AlertName + "' whether it is coming/generated or not.");

		AlertName = fnpFetchExactAlertName(AlertName);
		// if (AlertName == "") {
		if (AlertName.equalsIgnoreCase("")) {
			throw new Exception("Alert '" + hashXLKeyForAlertName + " ' is either not present or not visible. ");

		}

		String AlertNameXpath = ".//legend[contains(text(),'" + AlertName + "')]";

		WebElement wbElement = driver.findElement(By.xpath(OR.getProperty("FooterElement")));
		new Actions(driver).moveToElement(wbElement).perform();

		try {

			driver.findElement(By.xpath(AlertNameXpath)).click();
		} catch (Throwable t) {
			msg = "Alert '" + hashXLKeyForAlertName + " ' is either not present or not visible. ";
			fnpMymsg(msg);
			throw new Exception(msg);
		}

		fnpMymsg("Clicked '" + AlertName + "' alert link");

		fnpWaitForVisible(AlertFilterBoxXpath_OR);

		int colIndex = fnpFindColumnIndex(AlertHeaderTableXpath_OR, columnName);

		fnpType("OR", AlertFilterBoxXpath_OR, AI_NO);

		WebDriverWait waitvar = new WebDriverWait(driver, Long.parseLong(CONFIG.getProperty("genMax_waitTime")));
		waitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(AlertDataTableXpath_OR))))
				.isEnabled();

		// fnpLoading_wait();
		String actualFirstColValue = fnpFetchFromTable(AlertDataTableXpath_OR, 1, 1);
		Assert.assertFalse(actualFirstColValue.contains("No records found"),
				"Alert in '" + AlertName + "' has NOT been generated");

		int retries = 0;
		while (true) {

			try {
				retries++;
				String s = fnpFetchFromTable(AlertDataTableXpath_OR, 1, colIndex);
				if (s.equalsIgnoreCase(AI_NO)) {
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
					fnpMymsg(retries
							+ "In staleElementException catch block of fnpCommonAlertGeneratedVerification function. ");
					continue;
				} else {
					throw s;
				}
			}

			catch (Throwable t) {
				fnpDesireScreenshot("fnpCommonAlertGeneratedVerification_failed");
				String stackTrace = Throwables.getStackTraceAsString(t);
				String errorMsg = t.getMessage();
				errorMsg = errorMsg
						+ "failed in fnpCommonAlertGeneratedVerification ,plz see screenshot 'fnpCommonAlertGeneratedVerification_failed'.\n\n"
						+ stackTrace;
				throw new Exception(errorMsg);
			}

		}

		fnpMymsg("Inserted  '" + AI_NO + "' no in '" + AlertName + "' filter box");

		actualFirstColValue = fnpFetchFromTable(AlertDataTableXpath_OR, 1, 1);

		Assert.assertFalse(actualFirstColValue.contains("No records found"),
				"Alert in '" + AlertName + "' has NOT been generated");

		String Actual_No = fnpFetchFromTable(AlertDataTableXpath_OR, 1, colIndex);
		Assert.assertEquals(Actual_No, AI_NO, "Alert in '" + AlertName + "' has NOT been generated");
		fnpMymsg("++++++++++++++++++++++++++++++++++++++++++++++++");
		fnpMymsg(" Hence , Alert  '" + AlertName + "' has been generated successfully.");
		fnpMymsg("++++++++++++++++++++++++++++++++++++++++++++++++");
		fnpMymsg("");

		// ++++++++++++++++++++++++++++++++++++++++++++++++

	}

	/**** Special case (used once only) for a specific alert ****/
	public static void fnpCommonAlertGeneratedVerification_SpecialCaseActionItemAssignedVerify2Rows(
			String hashXLKeyForAlertName, String AlertHeaderTableXpath_OR,
			String columnName, String AlertDataTableXpath_OR, String AlertWOFilterBoxXpath_OR, String workOrderNo)
			throws Throwable {

		try {
			if (((String) hashXlData.get("Alerts_Verify_Flag")).equalsIgnoreCase("No")) {
				return;
			}
		} catch (NullPointerException e) {
			// nothing to do just ignore it. Null pointer exception will be
			// thrown when variable is not present.
			return;
		}

		String AlertName = (String) hashXlData.get(hashXLKeyForAlertName);
		fnpMymsg("Going to verify the alert'" + AlertName + "' whether it is coming/generated or not.");

		AlertName = fnpFetchExactAlertName(AlertName);
		// if (AlertName == "") {
		if (AlertName.equalsIgnoreCase("")) {
			throw new Exception("Alert '" + hashXLKeyForAlertName + " ' is either not present or not visible. ");

		}

		String AlertNameXpath = ".//legend[contains(text(),'" + AlertName + "')]";

		try {
			fnpWaitForVisible_NotInOR(AlertNameXpath);
			fnpWaitTillVisiblityOfElementNClickable_NOR(AlertNameXpath);
		} catch (Throwable t) {
			msg = "Alert '" + hashXLKeyForAlertName + " ' is either not present or not visible. ";
			fnpMymsg(msg);
			throw new Exception(msg);
		}

		WebElement wbElement = driver.findElement(By.xpath(OR.getProperty("FooterElement")));
		new Actions(driver).moveToElement(wbElement).perform();

		driver.findElement(By.xpath(AlertNameXpath)).click();
		fnpLoading_wait();

		fnpMymsg("Clicked '" + AlertName + "' alert link");

		fnpType("OR", AlertWOFilterBoxXpath_OR, workOrderNo);

		fnpTableRefreshedWait(AlertDataTableXpath_OR);
		fnpMymsg("Inserted  '" + workOrderNo + "' work order no in '" + AlertName + "' WO filter box");

		String s = fnpFetchFromTable(AlertDataTableXpath_OR, 1, 1);

		String AI_ITEMS_ASSIGNED_AlertWONo = fnpFetchFromTable("AI_ITEMS_ASSIGNED_AlertTable", 1, 3);
		Assert.assertEquals(AI_ITEMS_ASSIGNED_AlertWONo, workOrderNo,
				"Alert in '" + AlertName + "' has NOT been generated");
		fnpMymsg("++++++++++++++++++++++++++++++++++++++++++++++++");
		fnpMymsg("Hence,Alert  '" + AlertName + "' has been generated successfully.");

		// +++++++++++Just for timebeing commented to avoid alert unChecked as
		// here is bug , so to proceed further these are commented++++++++++
		int totalActual_AIItemsAssignedRows = fnpCountRowsInTable("AI_ITEMS_ASSIGNED_AlertTable");
		int totalExpected_AIItemsAssignedRows = 2;
		Assert.assertEquals(totalActual_AIItemsAssignedRows, totalExpected_AIItemsAssignedRows,
				"Here only 1 row is present so either alert for Item name 'ClientDocRequest' or 'Initial Client Education' is missing.  ");

		String bothItemName = (String) hashXlData.get("Action_item_assigned_having2ItemName");

		String AI_ITEMS_ASSIGNED_ItemName1 = fnpFetchFromTable("AI_ITEMS_ASSIGNED_AlertTable", 1, 2);
		Assert.assertTrue(bothItemName.contains(AI_ITEMS_ASSIGNED_ItemName1),
				"This '" + AI_ITEMS_ASSIGNED_ItemName1 + "' Item name is not expected as not given in data sheet.");
		fnpMymsg("Assigned Action Item Name 1 is  '" + AI_ITEMS_ASSIGNED_ItemName1 + "' . ");

		String AI_ITEMS_ASSIGNED_ItemName2 = fnpFetchFromTable("AI_ITEMS_ASSIGNED_AlertTable", 2, 2);
		Assert.assertTrue(bothItemName.contains(AI_ITEMS_ASSIGNED_ItemName2),
				"This '" + AI_ITEMS_ASSIGNED_ItemName2 + "' Item name is not expected as not given in data sheet.");
		fnpMymsg("Assigned Action Item Name 2 is  '" + AI_ITEMS_ASSIGNED_ItemName2 + "' . ");
		fnpMymsg("++++++++++++++++++++++++++++++++++++++++++++++++");
		fnpMymsg("");
		// ++++++++++++++++++++++++++++++++++++++++++++++++

	}

	/**** Verify alert has been deleted or not ****/
	public static void fnpCommonAlertDeletedVerification(String hashXLKeyForAlertName, String AlertHeaderTableXpath_OR,
			String columnName, String AlertDataTableXpath_OR,
			String AlertWOFilterBoxXpath_OR, String workOrderNo) throws Throwable {

		try {
			if (((String) hashXlData.get("Alerts_Verify_Flag")).equalsIgnoreCase("No")) {
				return;
			}
		} catch (NullPointerException e) {
			// nothing to do just ignore it. Null pointer exception will be
			// thrown when variable is not present.
			return;
		}

		String AlertName = (String) hashXlData.get(hashXLKeyForAlertName);
		fnpMymsg("Going to verify the alert'" + AlertName + "' whether it has been deleted or not.");

		AlertName = fnpFetchExactAlertName(AlertName);
		// if (AlertName == "") {
		if (AlertName.equalsIgnoreCase("")) {
			throw new Exception("Alert '" + hashXLKeyForAlertName + " ' is either not present or not visible. ");

		}

		String AlertNameXpath = ".//legend[contains(text(),'" + AlertName + "')]";

		try {
			fnpWaitTillVisiblityOfElementNClickable_NOR(AlertNameXpath);
		} catch (Throwable t) {
			msg = "Alert '" + hashXLKeyForAlertName + " ' is either not present or not visible. ";
			fnpMymsg(msg);
			throw new Exception(t.getMessage() + "-------" + msg);
		}
		WebElement wbElement = driver.findElement(By.xpath(OR.getProperty("FooterElement")));
		new Actions(driver).moveToElement(wbElement).perform();
		driver.findElement(By.xpath(AlertNameXpath)).click();
		fnpLoading_wait();

		fnpMymsg("Clicked '" + AlertName + "' alert link");

		fnpType("OR", AlertWOFilterBoxXpath_OR, workOrderNo);
		fnpTableRefreshedWait(AlertDataTableXpath_OR);
		fnpMymsg("Inserted  '" + workOrderNo + "' work order no in '" + AlertName + "' WO filter box");

		String s = fnpFetchFromTable(AlertDataTableXpath_OR, 1, 1);

		Assert.assertNotEquals(s, workOrderNo, "Alert in '" + AlertName + "' has NOT been REMOVED");

		Assert.assertTrue(s.toLowerCase().contains("no records found"),
				"Alert in '" + AlertName + "' has NOT been REMOVED as 'no records found' is not present after search");
		fnpMymsg("++++++++++++++++++++++++++++++++++++++++++++++++");
		fnpMymsg("Hence,Alert  '" + AlertName + "' has been REMOVED successfully.");
		fnpMymsg("++++++++++++++++++++++++++++++++++++++++++++++++");
		fnpMymsg("");
		// ++++++++++++++++++++++++++++++++++++++++++++++++

	}

	/**** To click 'Home' at top after verification of alerts ****/
	public static void fnpCommonGoToHomeNClick() throws Throwable {

		try {
			if (((String) hashXlData.get("Alerts_Verify_Flag")).equalsIgnoreCase("No")) {
				return;
			}
		} catch (NullPointerException e) {
			// nothing to do just ignore it. Null pointer exception will be
			// thrown when variable is not present.
			return;
		}

		fnpCommonGoToHomePage();
	}

	/*************
	 * Takes you to the Home Page after clicking HOME link at top
	 ********************************/
	public static void fnpCommonGoToHomePage() throws Throwable {
		// fnpLoading_wait();

		fnpClickAtTopWorkAroundForClickingMenu();
		fnpWaitForVisible("Home_topLink_id");

		WebElement Home = fnpGetORObjectX("Home_topLink_id");
		Actions action = new Actions(driver);
		action.moveToElement(Home).perform();
		// Thread.sleep(500);

		// fnpWaitForVisible("Home_topLink");
		fnpWaitTillVisiblityOfElementNClickable_OR("Home_topLink_id");
		fnpGetORObjectX("Home_topLink_id").click();
		fnpMymsg("Clicked Home link.  ");
		/***********
		 * added on 29-05-2017 as sometime someone can change/or set the
		 * preference for summarized alrert tab and hence showAlertBtn then will
		 * not visible
		 *************/
		// if (!(fnpCheckElementDisplayedImmediately("ShowAlertsBtn")) ){
		if (!(fnpCheckElementDisplayed("ShowAlertsBtn", 10))) {
			fnpClick_OR("AlertTabLink");
		}
		/*********************************************************/

		fnpWaitForVisible("ShowAlertsBtn");
		fnpWaitTillVisiblityOfElementNClickable_OR("ShowAlertsBtn");
		fnpLoading_wait();
		// fnpLoading_wait();
	}

	/**** To mouse hover on an element ****/
	public static void fnpMouseHover(String ORObjectName) throws Exception {

		fnpMymsg("@  ---just before mouse hover " + ORObjectName);

		int j = 0;
		while (true) {
			try {
				j++;
				WebElement requiredObj = null;

				if (fnpGetORObject_list(ORObjectName, Integer.parseInt(CONFIG.getProperty("implicitWaitTime")))
						.size() > 1) {

					List<WebElement> objList = fnpGetORObject_list(ORObjectName,
							Integer.parseInt(CONFIG.getProperty("implicitWaitTime")));

					for (int i = 0; i < objList.size(); i++) {
						if ((objList.get(i).isDisplayed())) {
							requiredObj = objList.get(i);
							break;
						}

					}
				} else {
					requiredObj = fnpGetORObjectX(ORObjectName);
				}

				WebElement hoverElement = requiredObj;
				Actions action = new Actions(driver);
				action.moveToElement(hoverElement).perform();
				Thread.sleep(500);
				break;
			} catch (Throwable t) {
				// Thread.sleep(1000);//chhavi commented on 12-02-16
				fnpMymsg("@  ---in throw loop--" + j);
				if (j > 5) {
					break;

				}

			}
		}
		fnpMymsg("@  ---just after mouse hover " + ORObjectName);

	}

	public static void fnpMouseHover_OR_EvenMultipleObjectsHavingSameXpath(String XpathKey) throws Exception {
		WebElement requiredObj = null;

		for (int wait = 0; wait < 2; wait++) {

			if (fnpGetORObject_list(XpathKey, Integer.parseInt(CONFIG.getProperty("implicitWaitTime"))).size() > 0) {

				List<WebElement> objList = fnpGetORObject_list(XpathKey,
						Integer.parseInt(CONFIG.getProperty("implicitWaitTime")));

				for (int i = 0; i < objList.size(); i++) {
					if ((objList.get(i).isDisplayed()) & (!((objList.get(i).getText()).equalsIgnoreCase("")))) {

						requiredObj = objList.get(i);
						break;
					}

				}

			} else {
				throw new Exception("Element is not Visible having Xpath  [ " + XpathKey + " ]");
			}
		} // for loop Closed

		WebElement hoverElement = requiredObj;
		Actions action = new Actions(driver);
		try {
			action.moveToElement(hoverElement).perform();
		} catch (Throwable t) {
			// nothing to do
		}

		Thread.sleep(500);

	}

	public static void fnpMouseHover_OR_EvenMultipleObjectsHavingSameXpath_usingSpecialForErrorClassOnly(
			String XpathKey) throws Exception {
		WebElement requiredObj = null;
		try {
			for (int wait = 0; wait < 2; wait++) {

				if (fnpGetORObject_list(XpathKey, 2).size() > 0) {

					List<WebElement> objList = fnpGetORObject_list(XpathKey, 1);

					for (int i = 0; i < objList.size(); i++) {

						if ((objList.get(i).isDisplayed())
								& (!((fnpReturnText_notCallItDirectly(objList.get(i)).equalsIgnoreCase(""))))) {
							requiredObj = objList.get(i);
							break;
						}

					}

				} else {
					/*******
					 * we donot want to throw error for mouse hover on error.
					 * This is special method
					 *******/
					// throw new
					// Exception("Element is not Visible having Xpath [ "
					// + XpathKey + " ]");
				}
			} // for loop Closed

			if (requiredObj != null) {
				WebElement hoverElement = requiredObj;
				Actions action = new Actions(driver);
				try {
					action.moveToElement(hoverElement).perform();
				} catch (Throwable t) {
					// nothing to do
				}

				Thread.sleep(500);
			}
		} catch (Throwable t) {
			// nothing to do
		}
	}

	public static void fnpMouseHover_NotInOR(String Xpath) throws Throwable {

		fnpMymsg("@  ---just before mouse hover " + Xpath);

		WebElement requiredObj = null;

		if (fnpGetORObject_list_NOR(Xpath, Integer.parseInt(CONFIG.getProperty("implicitWaitTime"))).size() > 0) {

			List<WebElement> objList = fnpGetORObject_list_NOR(Xpath,
					Integer.parseInt(CONFIG.getProperty("implicitWaitTime")));

			for (int i = 0; i < objList.size(); i++) {
				if ((objList.get(i).isDisplayed())) {
					requiredObj = objList.get(i);
					break;
				}

			}
		} else {
			requiredObj = fnpGetORObjectX___NOR(Xpath);
		}

		WebElement hoverElement = requiredObj;
		Actions action = new Actions(driver);
		action.moveToElement(hoverElement).perform();
		Thread.sleep(500);

		fnpMymsg("@  ---just after mouse hover " + Xpath);

	}

	/**** To mouse hover on an element having a link Name present in OR ****/
	public static void fnpMouseHover_LinkNameInOR(String ORObjectName) throws Throwable {
		fnpWaitForVisible_usingLinkNameInOR(ORObjectName);
		WebElement hoverElement = fnpGetORObjectX_usingLinkText(ORObjectName);
		Actions action = new Actions(driver);
		// action.moveToElement(hoverElement).perform();
		action.moveToElement(hoverElement).build().perform();
		// Thread.sleep(1000);
		if (browserName.equalsIgnoreCase("firefox")) {
			Thread.sleep(1000);

		}
	}

	/**** To switch to different user for Alerts verification ****/
	public static void fnpCommonSwitchToUserForAlerts(String userName) throws Throwable {

		try {
			if (((String) hashXlData.get("Alerts_Verify_Flag")).equalsIgnoreCase("No")) {
				return;
			}
		} catch (NullPointerException e) {
			// nothing to do just ignore it. Null pointer exception will be
			// thrown when variable is not present.
			return;
		}

		fnpMymsg("Now going to see the alerts for different person i.e. --" + userName);
		// fnpLoading_wait();
		fnpWaitTillClickable("SpecificUser_label");
		fnpWaitTillVisiblityOfElementNClickable_OR("SpecificUser_label");
		fnpGetORObjectX("SpecificUser_label").click();
		fnpWaitForVisible("SelectUserTxtBox");
		fnpWaitTillVisiblityOfElementNClickable_OR("SearchUserLKPBtn");
		fnpGetORObjectX("SearchUserLKPBtn").click();

		fnpSearchNSelectFirstRadioBtn(2, userName, 1);
		fnpWaitTillVisiblityOfElementNClickable_OR("ShowAlertsBtn");
		fnpGetORObjectX("ShowAlertsBtn").click();
		fnpLoading_wait();
		// fnpLoading_wait();
	}

	/**** Common code for clicking Task tab ****/
	public static void fnpCommonClickTaskTab() throws Throwable {

		// if (fnpCheckElementPresenceImmediately("Task_ShowAllLink")) {
		if (fnpCheckElementDisplayedImmediately("Task_ShowAllLink")) {
			return;
		} else {
			fnpWorkAroundToClickbottomFooter();
			fnpClick_OR("EditTaskTabLink");

			fnpWaitForVisible("Task_ShowAllLink");
			fnpWaitTillVisiblityOfElementNClickable_OR("Task_ShowAllLink");

			if (fnpCheckElementPresenceImmediately("EditWOBtn")) {
				fnpWaitTillClickable("EditWOBtn");
				fnpGetORObjectX("EditWOBtn").click();
				fnpLoading_wait();

				fnpWaitForVisible("Task_ShowAllLink");
				fnpWaitTillVisiblityOfElementNClickable_OR("Task_ShowAllLink");
			}
		}
	}

	/**** Common code for clicking Finance tab ****/
	public static void fnpCommonClickFinancialTab() throws Throwable {

		// fnpWorkAroundToClickbottomFooter();
		fnpClick_OR("FinancialTab_EditWO");

		fnpWaitForVisible("FinancialTab_InvoiceBillToLabel");
	}

	/**** Common code for clicking Info tab ****/
	public static void fnpCommonClickInfoTab() throws Throwable {

		fnpWorkAroundToClickbottomFooter();
		fnpClick_OR("InfoTab_EditWO");

		if (fnpCheckElementPresenceImmediately("EditWOBtn")) {
			fnpGetORObjectX("EditWOBtn").click();
			fnpLoading_wait();
		}
		fnpWaitForVisible("InfoTab_WOStatusLabel");

	}

	/**** Common code for clicking Snapshot tab ****/
	public static void fnpCommonClickSnapShotTab() throws Throwable {

		if (fnpCheckElementPresenceImmediately("SnapshotTab_AlertsHeading")) {
			// fnpMymsg(" ----@@@@@@ in fnpCommonClickSnapShotTab");

			if (fnpIfORElementDisplayed("SnapshotTab_AlertsHeading", 3)) {
				// fnpMymsg(" ----SnapshotTab_AlertsHeading is displayed so going to return
				// back.");
				return;
			} else {
				// fnpMymsg(" ----SnapshotTab_AlertsHeading is NOT displayed ");
				if (fnpCheckElementPresenceImmediately("EditWOBtn")) {
					fnpGetORObjectX("EditWOBtn").click();
					fnpLoading_wait();
					fnpWaitForVisible("SnapshotTab_AlertsHeading");
				}
			}

		}

		fnpMymsg("Going to click snapshot tab. ");

		fnpWorkAroundToClickbottomFooter();
		fnpClick_OR("SnapshotTabLink");

		fnpWaitForVisible("SnapshotTab_AlertsHeading");

		if (fnpCheckElementPresenceImmediately("EditWOBtn")) {
			fnpGetORObjectX("EditWOBtn").click();
			fnpLoading_wait();
			fnpWaitForVisible("SnapshotTab_AlertsHeading");
		}

	}

	/**** Common code for clicking Action tab ****/
	public static void fnpCommonClickActionTab() throws Throwable {

		fnpWaitForVisible("ActionItemTab_EditWO");
		fnpWaitTillVisiblityOfElementNClickable_OR("ActionItemTab_EditWO");
		fnpWaitTillClickable("ActionItemTab_EditWO");
		fnpGetORObjectX("ActionItemTab_EditWO").click();

		fnpWaitForVisible("ActionItemTable_EditWO");

	}

	/**** Common code first click Back to View button then back button ****/
	public static void fnpCommonBackToViewNBackBtnClick() throws Throwable {

		fnpMymsg("Checking BackToViewBtn is present or not, if present then click it ");
		if (fnpCheckElementPresenceImmediately("TaskTab_ScopeValidation_BackToViewBtn")) {
			/*
			 * fnpGetORObjectX("TaskTab_ScopeValidation_BackToViewBtn").click();
			 * fnpLoading_wait();
			 */
			fnpClick_OR("TaskTab_ScopeValidation_BackToViewBtn");
			fnpMymsg("BackToViewBtn is present, so clicked it.");
		}

		fnpWaitForVisible("TaskTab_ScopeValidation_BackBtn");

		/*
		 * fnpWaitTillVisiblityOfElementNClickable_OR("TaskTab_ScopeValidation_BackBtn")
		 * ;
		 * fnpGetORObjectX("TaskTab_ScopeValidation_BackBtn").click();
		 * fnpLoading_wait();
		 */
		fnpClick_OR("TaskTab_ScopeValidation_BackBtn");

	}

	/**** Common code first click Back to View button then back button ****/
	public static void fnpCommonBackToViewNBackBtnClick_2() throws Throwable {

		fnpClick_OR("WO_Main_BackToViewBtn");
		fnpClick_OR("WO_BackBtn");

	}

	/**** Common code for clicking Edit button if present ****/
	public static void fnpCommonClickEditBtnIfPresent() throws Throwable {

		if (fnpCheckElementPresenceImmediately("EditWOBtn")) {

			fnpWaitTillVisiblityOfElementNClickable_OR("EditWOBtn");
			fnpGetORObjectX("EditWOBtn").click();
			fnpLoading_wait();
		}

	}

	public static void fnpClickBackToViewNBackBtnWhenNullPointerExceptionWhenAlertFlagNotPresent() throws Throwable {

		if (fnpCheckElementPresenceImmediately("TaskTab_ScopeValidation_BackToViewBtn")) {
			fnpGetORObjectX("TaskTab_ScopeValidation_BackToViewBtn").click();
			fnpLoading_wait();

		}

		if (fnpCheckElementPresenceImmediately("TaskTab_ScopeValidation_BackBtn")) {
			fnpGetORObjectX("TaskTab_ScopeValidation_BackBtn").click();
			fnpLoading_wait();

		}

	}

	/****
	 * To print the message into logs
	 * 
	 * @throws Throwable
	 ****/
	public static void fnpMymsg(String msg) {

		try {

			msg = msg.replaceAll("ï¿½", "");
			msg = msg.replaceAll("ï¿½", "");
			msg = msg.replaceAll("ï¿½", "");
			// msg = msg.replaceAll("@", "");
			msg = msg.replaceAll("ï¿½", "");
			if ((msg.contains("@"))) {
				// nothing to do
			} else if ((msg.toLowerCase().contains("visible")) || (msg.toLowerCase().contains("going to"))
					|| (msg.toLowerCase().contains("cleared")) || (msg.toLowerCase().contains("throwable"))
					|| (msg.toLowerCase().contains("function"))) {
				System.out.println(msg);
				APP_LOGS.debug(msg);
			} else {
				if ((msg.toLowerCase().contains("progressing")) || (msg.toLowerCase().contains("catch block"))) {
					System.out.println("************ == " + msg);
					APP_LOGS.debug("************ == " + msg);
					Reporter.log(fnpTimestamp() + "  " + "************ == " + msg);
				} else if ((msg.toLowerCase().contains("click")) || (msg.toLowerCase().contains("inserted"))
						|| (msg.toLowerCase().contains("successfully")) || (msg.toLowerCase().contains("progressing"))
						|| (msg.toLowerCase().contains("catch block"))) {
					System.out.println("---------- PASSED == " + msg);
					APP_LOGS.debug("---------- PASSED == " + msg);
					Reporter.log(fnpTimestamp() + "  " + "---------- PASSED == " + msg);
				} else if ((msg.toLowerCase().contains("fail")) || (msg.toLowerCase().contains("java"))
						|| (msg.toLowerCase().contains("webdriver")) || (msg.toLowerCase().contains("assert"))) {
					System.out.println("---------- FAILED == " + msg);
					APP_LOGS.debug("---------- FAILED == " + msg);
					Reporter.log(fnpTimestamp() + "  " + "---------- FAILED == " + msg);
				} else {
					System.out.println(msg);
					APP_LOGS.debug(msg);
				}
			}

		} catch (Throwable t) {

		}

		try {

			if (Boolean.parseBoolean((CONFIG.getProperty("ShowinglogMessagesinPopupFrameFlag")))) {

				if ((!(msg.trim().isEmpty())) | (!(msg.trim().equalsIgnoreCase("")))) {

					msg = msg.replaceAll("Pradeep", "");
					msg = msg.replaceAll("pradeep", "");
					fnpDisplayingMessageInFrame_fnpMymsg(msg, 5);

				}
			}

		} catch (Throwable t) {
			APP_LOGS.debug("  ---in catch block of ShowinglogMessagesinPopupFrameFlag , error is --" + t.getMessage());
		}

	}

	/**** To find data in a table having paginator ****/
	public static int fnpFindDataInTableWithPaging_passingPaginatorName(String Tablexpath, String vNo, int ColNo,
			String TablePaginatorBottomName) throws Throwable {

		String vPagingXpath = ".//*[contains(@id,'" + TablePaginatorBottomName
				+ "')]/span[contains(@class,'ui-paginator-pages')]/span";

		int rowNo = 0;
		if (!fnpCheckElementPresenceImmediately_NotInOR(
				".//*[contains(@id,'" + TablePaginatorBottomName + "')]/span[contains(@class,'ui-paginator-pages')]")) {

			try {
				rowNo = fnpFindRow(Tablexpath, vNo, ColNo);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return rowNo;

		} else {

			List<WebElement> pagesCount = fnpGetORObject_list_NOR(vPagingXpath,
					Integer.parseInt(CONFIG.getProperty("implicitWaitTime")));

			int pageCountsize = pagesCount.size();

			int j1 = 0;
			int iCounter = 1;

			for (int p = 0; p < pageCountsize; p++) {

				pagesCount = fnpGetORObject_list_NOR(vPagingXpath,
						Integer.parseInt(CONFIG.getProperty("implicitWaitTime")));

				rowNo = fnpFindRow_usingForPagingToNotThrowErrorIfNotFoundInCurrentPage(Tablexpath, vNo, ColNo);

				if (rowNo > 0) {
					// rowNo = 20 * (p) + rowNo - 1;
					if (p == 0) {
						rowNo = rowNo;
					} else {
						// rowNo = 20 * (p) + rowNo - 1;
						rowNo = 20 * (p) + rowNo;
					}

					return rowNo;
				}
				pagesCount.get(p + 1).click();
				fnpLoading_wait();
				Thread.sleep(4000);
			}

		}
		return rowNo;

	}

	//
	/**** To Kill IE browser ****/
	public void ieKiller() throws Exception {
		try {
			final String KILL = "taskkill /IM ";
			String processName = "IEDriverServer.exe"; // IE process
			String processName2 = "iexplore.exe"; // IE process
			Runtime.getRuntime().exec(KILL + processName);
			Runtime.getRuntime().exec(KILL + processName2);
			Thread.sleep(5000); // Allow OS to kill the process

		} catch (Exception e) {
			// //System.out.println("HI");
		}
	}

	/**** Take sceenshot of App Memory Stack Trace ****/

	public void fnpApp_Memory_StackTrace_notusingAsIndrajeetToldUsToCommentIt() {
		fnpMymsg(" Executing App_Memory_StackTrace");
		try {

			fnpCommonStackTracePageOpen();

			String screenshotImageName = fnpTimestamp() + "   "
					+ fnpMappingClassNameWithScenarioCode(this.getClass().getSimpleName());

			screenshotImageName = screenshotImageName.replaceAll(":", "_");
			String folderPath = screenshots_path + "//screenshots_WO_ApplicationMemoryStackTrace//";
			fnpDesireScreenshot_Advance(screenshotImageName, folderPath);

		} catch (Throwable t) {
			fail = true;
			isTestPass = false;
			fnpMymsg("  App_Memory_StackTrace  method is failed . " + " . Error is ---" + t.getMessage());
			fnpDesireScreenshot("Failed_App_Memory_StackTrace_Failed");
			String stackTrace = Throwables.getStackTraceAsString(t);
			String errorMsg = t.getMessage();
			errorMsg = errorMsg
					+ "\n\n\n\n App_Memory_StackTrace  method is failed   .See screenshot 'Failed_App_Memory_StackTrace_Failed'\n\n"
					+ stackTrace;
			Exception c = new Exception(errorMsg);
			ErrorUtil.addVerificationFailure(c);

		}

	}

	/**** Open App Memory Stack Trace page ****/
	public static void fnpCommonStackTracePageOpen() throws Throwable {

		fnpCommonClickTopMainMenu("Menu", "FirstRefrenceLinkJustBelowToMenu", "Application_Memory_Stack_TraceLink",
				"Application_Memory_Stack_Trace_TopPageHeading");

	}

	/**** Function clicking of an object (button etc) which is present in OR ****/
	public static void fnpClick_OR(String xpathkey) throws Throwable {

		int i = 0;
		while (fnpCheckElementPresenceImmediately("BlockedUIScreen")) {
			Thread.sleep(1000);
			i++;
			if (i > Integer.parseInt(CONFIG.getProperty("genMax_waitTime")) * 2) {
				break;

			}

		}

		fnpCheckError("");

		fnpClick_OR_WithoutWait(xpathkey);
		fnpLoading_wait();
		fnpIpulseDuringLoading();
		// fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
		fnpMymsg("PASSED == click done on  '" + xpathkey + "' .");

	}

	public static void fnsClick_OR(String XpathKey) throws Throwable {
		try {
			try {
				fnpGetORObjectX(XpathKey).click();
				fnpMymsg("PASSED == Click done on Element having Xpath >> " + XpathKey);
			} catch (Throwable tt) {
				Thread.sleep(3000);
				fnpGetORObjectX(XpathKey).click();
				fnpMymsg("(((((( 2nd Attampt ))))))---PASSED == Click done on Element having Xpath >> " + XpathKey);
			}
		} catch (NoSuchWindowException W) {
			throw new Exception(W.getMessage());
		} catch (Throwable e) {
			fnpMymsg("FAILED == Unable To Click on Element having Xpath >> " + XpathKey
					+ ", plz see screenshot [ UnableToClick_" + XpathKey + " ]" + ". Getting Exception  >> "
					+ Throwables.getStackTraceAsString(e));
			throw new Exception(
					"FAILED == Unable To Click on element [ " + XpathKey + " ] , plz see screenshot [ UnableToClick_"
							+ XpathKey + " ]" + ". Getting Exception  >> " + Throwables.getStackTraceAsString(e));
		}
	}

	public static void fnsClick_NOR(String XpathKey) throws Throwable {
		try {
			try {
				fnsGet_ObjectX_NOR(XpathKey).click();
				fnpMymsg("PASSED == Click done on Element having Xpath >> " + XpathKey);
			} catch (Throwable tt) {
				Thread.sleep(3000);
				fnsGet_ObjectX_NOR(XpathKey).click();
				fnpMymsg("(((((( 2nd Attampt ))))))---PASSED == Click done on Element having Xpath >> " + XpathKey);
			}
		} catch (NoSuchWindowException W) {
			throw new Exception(W.getMessage());
		} catch (Throwable e) {
			fnpMymsg("FAILED == Unable To Click on Element having Xpath >> " + XpathKey
					+ ", plz see screenshot [ UnableToClick_" + XpathKey + " ]" + ". Getting Exception  >> "
					+ Throwables.getStackTraceAsString(e));
			throw new Exception(
					"FAILED == Unable To Click on element [ " + XpathKey + " ] , plz see screenshot [ UnableToClick_"
							+ XpathKey + " ]" + ". Getting Exception  >> " + Throwables.getStackTraceAsString(e));
		}
	}

	// Function to wait for element
	public static void fnsGet_Element_Enabled(String XpathKey) throws Exception {
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		try {
			for (int wait = 0; wait < 3; wait++) {
				if (driver.findElements(By.xpath(OR.getProperty(XpathKey))).size() > 0) {
					// fnsGet_OR_New_NSFOnline_ObjectX(XpathKey);
					WebDriverWait elementwaitvar = new WebDriverWait(driver, Element_Max_Wait_Time_SAM);
					elementwaitvar
							.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(XpathKey))));

					WebDriverWait elementwaitvar1 = new WebDriverWait(driver, Element_Max_Wait_Time_SAM);
					elementwaitvar1
							.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(XpathKey))))
							.isEnabled();

					WebDriverWait elementwaitvar2 = new WebDriverWait(driver, Element_Max_Wait_Time_SAM);
					elementwaitvar2
							.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(XpathKey))))
							.isDisplayed();

					break;
				} // if loop closed
				else {
					throw new Exception();
				}
			}
			driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")),
					TimeUnit.SECONDS);
			fnpMymsg("PASSED == Element is Visible having Xpath  >> " + XpathKey);
		} catch (NoSuchWindowException W) {
			throw new Exception(W.getMessage());
		} catch (TimeoutException to) {
			throw new Exception("TimeOut : " + Throwables.getStackTraceAsString(to));
		} catch (Throwable t) {
			try {
				Thread.sleep(3000);
				WebDriverWait elementwaitvar3 = new WebDriverWait(driver, (Element_Max_Wait_Time_SAM));
				elementwaitvar3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(XpathKey))))
						.isEnabled();// }
				fnpMymsg("PASSED == Element is Visible having Xpath  >> " + XpathKey);
			} catch (NoSuchWindowException W) {
				// isTestCasePass = false;
				throw new Exception(W.getMessage());
			} catch (Throwable e) {
				fnpMymsg("FAILED == Element is not Visible having Xpath  >> " + XpathKey + ", plz see screenshot [ "
						+ "" + XpathKey + " ]. Getting Exception >> " + Throwables.getStackTraceAsString(e));
				throw new Exception(
						"FAILED == Element is not Visible having Xpath  [ " + XpathKey + " ] , plz see screenshot [ "
								+ "" + XpathKey + " ]. Getting Exception >> " + Throwables.getStackTraceAsString(e));
			}
		}
	}

	public static void fnsGet_Element_Enabled_NOR(String XpathKey) throws Exception {
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		try {
			for (int wait = 0; wait < 3; wait++) {
				if (driver.findElements(By.xpath(XpathKey)).size() > 0) {
					WebDriverWait elementwaitvar = new WebDriverWait(driver, Element_Max_Wait_Time_SAM);
					elementwaitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XpathKey)));

					WebDriverWait elementwaitvar1 = new WebDriverWait(driver, Element_Max_Wait_Time_SAM);
					elementwaitvar1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XpathKey)))
							.isEnabled();

					WebDriverWait elementwaitvar2 = new WebDriverWait(driver, Element_Max_Wait_Time_SAM);
					elementwaitvar2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XpathKey)))
							.isDisplayed();

					break;
				} else {
					throw new Exception();
				}
			}
			fnpMymsg("PASSED == Element is Visible having Xpath  >> " + XpathKey);
		} catch (NoSuchWindowException W) {
			throw new Exception(W.getMessage());
		} catch (TimeoutException to) {
			throw new Exception("TimeOut : " + Throwables.getStackTraceAsString(to));
		} catch (Throwable t) {
			try {
				Thread.sleep(3000);
				WebDriverWait elementwaitvar3 = new WebDriverWait(driver, (Element_Max_Wait_Time_SAM));
				elementwaitvar3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XpathKey))).isEnabled();// }
				fnpMymsg("PASSED == Element is Visible having Xpath  >> " + XpathKey);
			} catch (NoSuchWindowException W) {
				// isTestCasePass = false;
				throw new Exception(W.getMessage());
			} catch (Throwable e) {
				fnpMymsg("FAILED == Element is not Visible having Xpath  >> " + XpathKey + ", plz see screenshot [ "
						+ "" + XpathKey + " ]. Getting Exception >> " + Throwables.getStackTraceAsString(e));
				throw new Exception(
						"FAILED == Element is not Visible having Xpath  [ " + XpathKey + " ] , plz see screenshot [ "
								+ "" + XpathKey + " ]. Getting Exception >> " + Throwables.getStackTraceAsString(e));
			}
		} finally {
			driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")),
					TimeUnit.SECONDS);
		}
	}

	// Function to wait for element
	public static void fnsGet_Element_Displayed_NOR(String XpathKey) throws Throwable {
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		try {
			for (int wait = 0; wait <= (Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))); wait++) {
				try {
					if (fnsGet_ObjectX_NOR(XpathKey).isDisplayed()) {
						break;
					} else {
						Thread.sleep(500);
					}
				} catch (Throwable t) {
					Thread.sleep(500);
					// nothing to do
				}
				if (wait == Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					throw new Exception("FAILED == Element is not displayed, after <"
							+ ((Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))))
							+ "> seconds wait, please refer screenshot >> ");
				}
			}
			fnpMymsg("PASSED == Element is displayed having Xpath  >> " + XpathKey);
		} catch (NoSuchWindowException W) {
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			fnpDesireScreenshot_old(XpathKey);
			fnpMymsg("FAILED == Element is not displayed having Xpath  >> " + XpathKey + ", plz see screenshot [ " + ""
					+ XpathKey + " ]. Getting Exception >> " + Throwables.getStackTraceAsString(t));
			throw new Exception(
					"FAILED == Element is not displayed having Xpath  [ " + XpathKey + " ] , plz see screenshot [ " + ""
							+ XpathKey + " ]. Getting Exception >> " + Throwables.getStackTraceAsString(t));

		} finally {
			driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")),
					TimeUnit.SECONDS);
		}
	}

	public static WebElement fnsGet_ObjectX_NOR(String XpathKey) throws Throwable {

		try {
			for (int waits = 0; waits < Integer.parseInt(CONFIG.getProperty("genMax_waitTime")); waits++) {

				if (driver.findElements(By.xpath(XpathKey)).size() > 0) {
					break;
				} else {
					Thread.sleep(500);
				}

			}
			return driver.findElement(By.xpath(XpathKey));

		} catch (StaleElementReferenceException e) {
			WebDriverWait stalewaitvar = new WebDriverWait(driver, 10);
			stalewaitvar.until(ExpectedConditions.presenceOfElementLocated(By.xpath(XpathKey)));
			WebElement webelemnt = driver.findElement(By.xpath(XpathKey));
			stalewaitvar.until(ExpectedConditions.stalenessOf(webelemnt));
			return driver.findElement(By.xpath(XpathKey));
		} catch (NoSuchWindowException W) {
			throw new Exception(W.getMessage());
		} catch (NoSuchElementException e) {
			// fnpDesireScreenshot_old("NoSuchElementException");
			fnpMymsg("FAILED == Element is not found >> " + XpathKey + " ] , plz see screenshot [NoSuchElementException"
					+ XpathKey + " ]");
			throw new Exception("FAILED == Element is not found >> " + XpathKey
					+ " ] , plz see screenshot [NoSuchElementException" + XpathKey + " ]");
		} catch (TimeoutException e) {
			// fnpDesireScreenshot_old("TimeOut");
			fnpMymsg("FAILED == Element is not Present, getting TimeOut >> " + XpathKey
					+ " ] , plz see screenshot [TimeOut" + XpathKey + " ]");
			throw new Exception("FAILED == Element is not Present, getting TimeOut >> " + XpathKey
					+ " ] , plz see screenshot [TimeOut" + XpathKey + " ]");
		} catch (Throwable e) {
			// fnpDesireScreenshot_old("NotPresent" );
			fnpMymsg("FAILED == Element is not found >> " + XpathKey + " ] , plz see screenshot [NotPresent" + XpathKey
					+ " ]" + ". Getting Exception  >> " + e.getMessage());
			throw new Exception("FAILED == Element is not found >> " + XpathKey + " ] , plz see screenshot [NotPresent"
					+ XpathKey + " ]");
		}
	}

	/****
	 * Function clicking of an object (button etc) which is present in OR
	 * without handling loading Wait
	 ****/
	public static void fnpClick_OR_WithoutWait(String xpathkey) throws Throwable {
		int retries = 0;

		int i = 0;
		while (fnpCheckElementPresenceImmediately("BlockedUIScreen")) {
			Thread.sleep(1000);
			i++;
			if (i > Integer.parseInt(CONFIG.getProperty("genMax_waitTime")) * 2) {
				break;

			}

		}
		fnpCheckError("");
		fnpWaitForVisible(xpathkey);

		fnpWaitTillVisiblityOfElementNClickable_OR(xpathkey);

		while (true) {
			try {
				fnpGetORObjectX(xpathkey).click();
				fnpMymsg(" Clicked '" + xpathkey + "' .");
				break;

			} catch (org.openqa.selenium.StaleElementReferenceException e) {
				if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					retries++;
					fnpMymsg(retries + "In staleElementException catch block of fnpClick_OR function for " + xpathkey);
					// continue;
				} else {
					throw e;
				}
			}

			catch (org.openqa.selenium.WebDriverException w) {

				if ((w.getMessage().contains("not clickable at point"))
						|| (w.getMessage().contains("unknown error:"))) {
					if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
						retries++;
						fnpWorkAroundToClickbottomFooter();
						Thread.sleep(1000);
						fnpMymsg(retries + "In WebDriverException catch block of fnpClick_OR function because this  "
								+ xpathkey + "' element is not clickable.");
						// continue;
					} else {
						throw w;
					}
				} else {
					throw w;
				}
			}

		}
		fnpMymsg("PASSED == click done on element >> " + xpathkey);// logs
		fnpCheckError("");

	}

	/****
	 * Function clicking of an object (button etc) which is present in inner
	 * dialog boxes in application
	 ****/
	public static void fnpClickInDialog_OR(String xpathkey) throws Throwable {
		int retries = 0;

		int i = 0;
		while (fnpCheckElementPresenceImmediately("BlockedUIScreen")) {
			Thread.sleep(1000);

			i++;
			if (i > Integer.parseInt(CONFIG.getProperty("genMax_waitTime")) * 2) {
				break;

			}

		}
		// fnpCheckError("");
		fnpWaitTillVisiblityOfElementNClickable_OR(xpathkey);

		while (true) {
			try {
				Thread.sleep(1000);// put this on 4-3-16 to guesss to avoid we
									// are sorry in dialog, if later it solved
									// or still sometime showing we are sorry
									// error then remove it later
				fnpGetORObjectX(xpathkey).click();
				fnpLoading_waitInsideDialogBox();
				fnpLoading_wait_CheckLoadingIconOnlyIniPulse();

				i = 0;
				while (fnpCheckElementPresenceImmediately("BlockedUIScreen")) {
					Thread.sleep(1000);
					i++;
					if (i > Integer.parseInt(CONFIG.getProperty("genMax_waitTime")) * 2) {

						break;

					}

				}

				fnpMymsg(" Clicked '" + xpathkey + "' .");
				break;

			} catch (org.openqa.selenium.StaleElementReferenceException e) {
				if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					retries++;
					fnpMymsg(retries + "In staleElementException catch block of fnpClickInDialog_OR function for "
							+ xpathkey);
					// continue;
				} else {
					throw e;
				}
			}

			catch (org.openqa.selenium.WebDriverException w) {

				if ((w.getMessage().contains("not clickable at point"))
						|| (w.getMessage().contains("unknown error:"))) {
					if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
						// if (retries <1) {
						retries++;
						Thread.sleep(1000);
						fnpMymsg(retries + "   In WebDriverException catch block of fnpClickInDialog_OR function for "
								+ xpathkey);
						// continue;
					} else {
						throw w;
					}
				} else {
					throw w;
				}
			}

		}

		fnpCheckError("");
		driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")),
				TimeUnit.SECONDS);

	}

	/****
	 * Function clicking of an object (button etc) which is NOT present in OR
	 ****/

	public static void fnpClick_NOR(String xpath) throws Throwable {
		fnpClick_NOR_withoutWait(xpath);

		fnpLoading_wait();

		fnpCheckError("");

	}

	public void fnsWait_Click_on_First_Visible_Element_if_More_than_One_Coming(String Element_Name,
			String WithoutOR_XpathKey) throws Exception {
		int Max_Click = 1;
		boolean Click_Done = false;
		while (true) {
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			Integer Element_Size = driver.findElements(By.xpath(WithoutOR_XpathKey)).size();
			Click_Done = false;
			try {
				if (Element_Size > 0) {
					for (int i = 1; i <= Element_Size; i++) {
						String Element_Xpath = "(" + WithoutOR_XpathKey + ")[" + i + "]";
						WebElement Element = driver.findElement(By.xpath(Element_Xpath));
						if (Element.isDisplayed()) {
							Element.click();
							Click_Done = true;
							fnpMymsg("PASSED == Click done on Element_" + i + " >> " + Element_Name);
							break;
						}
						if (i == Element_Size && Click_Done == false) {
							throw new Exception("FAILED == Clicking on element_" + Element_Size + " with Name >> "
									+ Element_Name + " is getting fail (Not displayed), please see screenshot >> ");
						}
					}

				} else {
					throw new Exception("FAILED == There is no such element" + Element_Size + " with Name >> "
							+ Element_Name + ", please see screenshot ");
				}
				driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")),
						TimeUnit.SECONDS);
			} catch (NoSuchWindowException W) {
				throw new Exception(W.getMessage());
			} catch (StaleElementReferenceException e) {
				if (Max_Click == 5) {
					fnpMymsg(Throwables.getStackTraceAsString(e));
					throw new Exception(Throwables.getStackTraceAsString(e));
				} else {
					Thread.sleep(5000);
				}
			} catch (Throwable t) {
				fnpMymsg(Throwables.getStackTraceAsString(t));
				throw new Exception(Throwables.getStackTraceAsString(t));
			}
			if (Click_Done) {
				break;
			}
			Max_Click++;
		}
		try {
			assertTrue(Click_Done);
		} catch (Throwable t) {
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}

	/****
	 * Function clicking of an object (button etc) which is NOT present in OR
	 * without handling loading Wait
	 ****/
	public static void fnpClick_NOR_withoutWait(String xpath) throws Throwable {
		int retries = 0;
		while (true) {
			try {
				// fnpCheckError("");
				fnpGetORObjectX___NOR(xpath).click();
				fnpMymsg("PASSED == Click done on " + xpath);
				break;

			} catch (org.openqa.selenium.StaleElementReferenceException e) {
				if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					retries++;
					fnpMymsg(retries + "In staleElementException catch block of fnpClick_NOR function for " + xpath);
					// continue;
				} else {
					throw e;
				}
			} catch (org.openqa.selenium.WebDriverException w) {

				if ((w.getMessage().contains("not clickable at point"))
						|| (w.getMessage().contains("unknown error:"))
						|| (w.getMessage().contains("element not interactable"))) {
					if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
						retries++;
						Thread.sleep(1000);
						fnpMymsg(retries + "In WebDriverException catch block of fnpClick_NOR function for " + xpath);
						// continue;
					} else {
						throw w;
					}
				} else {
					throw w;
				}
			}
		}

		fnpCheckError("");

	}

	//
	/**** To fetch value from Excel ****/
	public static HashMap<String, String> fnpLoadHashData(HashMap hashXlData1, Xls_Reader xls, String sheetName,
			int startRow) {

		// we are no longer using hashXlData1 which is given in function
		// arguments/parameters
		HashMap<String, String> hashXlData2 = new HashMap();

		String hashkey;
		String hashValue;
		// int cols = xls.getColumnCount(sheetName);
		int cols = xls.getColumnCount(sheetName, startRow);

		for (int i = 0; i < cols; i++) {
			hashkey = xls.getCellData(sheetName, i, startRow);
			hashValue = xls.getCellData(sheetName, i, startRow + 1);

			if (!hashkey.isEmpty()) {
				// hashXlData.put(hashkey, hashValue);
				hashXlData2.put(hashkey, hashValue);
			}

		}

		hashXlData = hashXlData2;

		return hashXlData;

	}

	/**** To fetch value from Excel ****/
	public static HashMap<String, String> fnpLoadHashData(HashMap hashXlData, Xls_Reader xls, String sheetName,
			int startRow, int dataRow) {

		String hashkey;
		String hashValue;
		int cols = xls.getColumnCount(sheetName);

		for (int i = 0; i < cols; i++) {
			hashkey = xls.getCellData(sheetName, i, startRow);
			hashValue = xls.getCellData(sheetName, i, dataRow);

			if (!hashkey.isEmpty()) {
				hashXlData.put(hashkey, hashValue);
			}

		}

		return hashXlData;

	}

	/**** common code of Draft to InReview Code ****/
	public void fnpCommonDraftToInReviewCode() throws Throwable {

		fnpMymsg(" **************************************************************");
		fnpMymsg(" Executing Verifying__DRAFT_INREVIEW");

		fnpSearchWorkOrderAndClickEditAlso(workOrderNo);

		if (!fnpCheckElementPresenceImmediately("InfoTab_WOStatusPFList")) {
			fnpWaitTillClickable("InfoTab_EditWO");
			fnpGetORObjectX("InfoTab_EditWO").click();
			fnpLoading_wait();
		}

		fnpWaitForVisible("InfoTab_WOStatusPFList");

		/*
		 * if ( !(classNameText.equalsIgnoreCase("NewNew_InProc_Completed_No_Fac"))){
		 * 
		 *//********
			 * IPM-9433**** 10.1 sprint CAR Resolution new mandatory field 12-09-2018 ,
			 * except in New -New
			 ***********************************************/
		/*
		 * //NO value is mentioned in the xpath already, if want to change it to Yes
		 * then change in xpath
		 * fnpGetORObjectX("CAR_Resolution_RadioBtn_No").click();
		 * 
		 *//**********************************************************//*
																		 * 
																		 * }
																		 * 
																		 */

		// if ((currentSuiteName.equalsIgnoreCase(Dietary_Supplement_suite_Name)) ){
		if (((currentSuiteName.equalsIgnoreCase(Dietary_Supplement_suite_Name)))
				| ((currentSuiteName.equalsIgnoreCase(Non_Food_Compounds_suite_Name)))) {

			fnpClick_OR("InfoTab_TechReviLKPBtn");
			// fnpSearchNSelectFirstRadioBtn(2, (String) hashXlData.get("Tech_Reviewer"),
			// 1);

			if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking"))) {
				fnpSearchNSelectFirstRadioBtn(1, (String) hashXlData.get("Tech_Reviewer_Code"), 1);
			} else {
				fnpSearchNSelectFirstRadioBtn(2, (String) hashXlData.get("Tech_Reviewer"), 1);
			}

			String techReviewer = fnpWaitTillTextBoxDontHaveValue("InfoTab_TechReviTxtBox", "value");
			// Assert.assertTrue(techReviewer.contains((String)
			// hashXlData.get("Tech_Reviewer")), "Tech Reviewer Value is not selected
			// properly from lookup");

			if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking"))) {
				Assert.assertTrue(techReviewer.contains((String) hashXlData.get("Tech_Reviewer_Code")),
						"Tech Reviewer Value is not selected properly from lookup because actual  value is ---'"
								+ techReviewer + "' and expected is --'" + (String) hashXlData.get("Tech_Reviewer_Code")
								+ "'.");
			} else {
				Assert.assertTrue(techReviewer.contains((String) hashXlData.get("Tech_Reviewer")),
						"Tech Reviewer Value is not selected properly from lookup because actual  value is ---'"
								+ techReviewer + "' and expected is --'" + (String) hashXlData.get("Tech_Reviewer")
								+ "'.");
			}

			fnpMymsg(" Tech Reviewer value is properly selected from  lookup");

			fnpClick_OR("InfoTab_CTDeciMakerLKPBtn");

			// fnpSearchNSelectFirstRadioBtn(2, (String) hashXlData.get("QC"), 1);
			if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking"))) {
				fnpSearchNSelectFirstRadioBtn(1, (String) hashXlData.get("QC_Code"), 1);
			} else {
				fnpSearchNSelectFirstRadioBtn(2, (String) hashXlData.get("QC"), 1);
			}

			String certDeciMaker = fnpWaitTillTextBoxDontHaveValue("InfoTab_CTDeciMakerTxtBox", "value");
			// Assert.assertTrue(certDeciMaker.contains((String) hashXlData.get("QC")), "QC
			// Value is not selected properly from lookup");
			if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking"))) {
				Assert.assertTrue(certDeciMaker.contains((String) hashXlData.get("QC_Code")),
						"QC Value is not selected properly from lookup because actual  value is ---'" + certDeciMaker
								+ "' and expected is --'" + (String) hashXlData.get("QC_Code") + "'.");
			} else {
				Assert.assertTrue(certDeciMaker.contains((String) hashXlData.get("QC")),
						"QC Value is not selected properly from lookup because actual  value is ---'" + certDeciMaker
								+ "' and expected is --'" + (String) hashXlData.get("QC") + "'.");
			}

			fnpMymsg(" QC value is properly selected from  lookup");

			if ((currentSuiteName.equalsIgnoreCase(Dietary_Supplement_suite_Name))) {
				fnpType("OR", "SalesForceOpportunityID_TxtBox_id",
						(String) hashXlData.get("Salesforce_Opportunity_ID"));
				// commenting below as it type string and we match string inserted successfully
				// or not, but in this text box int converted to the double and hence matching
				// will failed
				// fnpType("OR", "Initial_Quote_Value_TxtBox_id", (String)
				// hashXlData.get("Initial_Quote_Value"));
				fnpGetORObjectX("Initial_Quote_Value_TxtBox_id")
						.sendKeys((String) hashXlData.get("Initial_Quote_Value"));
				fnpType("OR", "Initial_of_Products_TxtBox_id", (String) hashXlData.get("Initial_of_Products"));
				fnpType("OR", "Initial_of_Facilities_TxtBox_id", (String) hashXlData.get("Initial_of_Facilities"));
			}
		}

		if (!(currentSuiteName.equalsIgnoreCase(Dietary_Supplement_suite_Name))) {
			if (!(((String) hashXlData.get("WOType")).equalsIgnoreCase("New Client-New Product"))) {

				/********
				 * IPM-9433**** 10.1 sprint CAR Resolution new mandatory field 12-09-2018 ,
				 * except in New -New
				 ***********************************************/
				// NO value is mentioned in the xpath already, if want to change it to Yes then
				// change in xpath
				fnpGetORObjectX("CAR_Resolution_RadioBtn_No").click();

				/**********************************************************/

			}
		}

		/************
		 * 9.2 sprint Cert decider new mandatory field
		 ***********************************************/

		// fnpPFList("InfoTab_CertDeciderPFList", "InfoTab_CertDeciderPFListOptions",
		// (String) hashXlData.get("Cert_Decider"));

		/**********************************************************/

		if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking"))) {
			fnpPFList("InfoTab_CertDeciderPFList", "InfoTab_CertDeciderPFListOptions",
					(String) hashXlData.get("Cert_Decider_Code"));

		} else {
			fnpPFList("InfoTab_CertDeciderPFList", "InfoTab_CertDeciderPFListOptions",
					(String) hashXlData.get("Cert_Decider"));
		}

		String status = "InReview";

		fnpPFList("InfoTab_WOStatusPFList", "InfoTab_WOStatusPFListOptions", status);

		// IPM 3668
		// fnpInsertStandardVersionAtInfoTab((String)
		// hashXlData.get("Standard_Version"));

		if ((currentSuiteName.equalsIgnoreCase(Non_Food_Compounds_suite_Name))) {

			// jira FPC-86
			// fnpPFList_NFS_StandardVersionType( "NFS_StandardVersion_TriangleBtn",
			// "NFS_StandardVersionPFList", "NFS_StandardVersionPFListOptions", (String)
			// hashXlData.get("Standard_Version"));
			fnsDD_Value_select_by_DDLabelName_and_Filter("Standard Version",
					(String) hashXlData.get("Standard_Version"));
			// Thread.sleep(1000);
			// fnpLoading_wait();
			fnpClick_OR("ProAddDocSaveBtn");
			// jira FPC-86
			// fnpPFList_NFS_StandardVersionType( "NFS_StandardVersion_TriangleBtn",
			// "NFS_StandardVersionPFList", "NFS_StandardVersionPFListOptions", (String)
			// hashXlData.get("Second_Standard_Version"));
			fnsDD_Value_select_by_DDLabelName_and_Filter("Standard Version",
					(String) hashXlData.get("Second_Standard_Version"));
			fnpClick_OR("ProAddDocSaveBtn");
		} else {
			fnpInsertStandardVersionAtInfoTab((String) hashXlData.get("Standard_Version"));
		}

		fnpCheckFacilityDropStatusAndThenRunSQLQueries();

		fnpCheckErrMsg("Error is thrown by application while changing status from DRAFT to InReview in Info tab");

		fnpWaitForVisibleHavingMultipleElements("TopMessage3");
		String SuccessfulMsg = fnpGetText_OR("TopMessage3");
		fnpMymsg("Top Message after changing status from DRAFT to InReview in Info tab ----" + SuccessfulMsg);

		Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"),
				"Top message does not contain 'success' word, so might be changing status from DRAFT to InReview in Info tab is NOT successful.");

		fnpWaitTillClickable("NewlyCrWOTopBannerInfo");
		fnpGetORObjectX("NewlyCrWOTopBannerInfo").click();
		fnpWaitForVisible("TopBannerWOStatus");
		String changedWOStatus = fnpGetORObjectX("TopBannerWOStatus").getText();
		Assert.assertEquals(changedWOStatus, "INREVIEW", " WO status is not changed from 'DRAFT' to 'INREVIEW'.");
		fnpMymsg("Now  WO status has been changed from 'DRAFT' to 'INREVIEW' status.");

		fnpCommonClickSnapShotTab();

		fnpWaitForVisible("ActionItemTable_EditWO"); // Not worked

		fnpMymsg("Action Item table is visible");
		String FirstValueInTable = fnpFetchFromTable("ActionItemTable_EditWO", 1, 1);
		Assert.assertFalse(FirstValueInTable.contains("No Data Found"),
				"Action items should be generated under the Action items tab but here not generated. ");

	}

	/*	
		*//***** May be not using this ***//*
											 * public static void fnpClickWhenChancesOfStaleElementException(String
											 * xpathvalue) {
											 * final String xpath = xpathvalue;
											 * new WebDriverWait(driver,
											 * Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))).ignoring(org.
											 * openqa.selenium.StaleElementReferenceException.class).until(
											 * new Predicate<WebDriver>() {
											 * 
											 * @Override
											 * public boolean apply(@Nullable WebDriver driver) {
											 * driver.findElement(By.xpath(xpath)).click();
											 * return true;
											 * }
											 * });
											 * 
											 * }
											 * 
											 * 
											 */

	/***** Fetch and Extract Exact Alert Name ***/
	public static String fnpFetchExactAlertName(String vagueAlertName) {
		List<WebElement> alerts = driver
				.findElements(By.xpath(".//legend[contains(@class,'ui-fieldset-legend ui-corner-all')]"));
		int i = 0;

		String expectedAlert = vagueAlertName;
		String lowerExpectedAlert = expectedAlert.toLowerCase();
		int found = 0;
		String exactAlertName = "";
		for (Iterator iterator = alerts.iterator(); iterator.hasNext();) {
			WebElement webElement = (WebElement) iterator.next();
			String alertName = webElement.getText();
			String lowerCasealertName = alertName.toLowerCase();
			if (lowerCasealertName.contains(lowerExpectedAlert)) {
				found = 1;

				String BeforeRemoveNo = alertName.trim();

				String alertNameArray[] = BeforeRemoveNo.split(" ");
				String afterRemove = "";
				for (int j = 1; j < alertNameArray.length; j++) {
					afterRemove = afterRemove + " " + alertNameArray[j];
				}

				afterRemove = afterRemove.trim();
				exactAlertName = afterRemove;
				break;

			}

		}

		if (found == 1) {
			fnpMymsg("Alert text string is found.");
			fnpMymsg("Alert is:--" + exactAlertName);

		} else {
			fnpMymsg("Alert is NOT matched or found");

		}

		return exactAlertName;
	}

	/***** Wait till text box dont have value entered correctly ***/
	public static String fnpWaitTillTextBoxDontHaveValue(String OR_objectName, String attributeName) throws Throwable {
		String EnteredClient = "";
		int i = 1;
		while (true) {
			i++;
			if (EnteredClient.equalsIgnoreCase("")) {
				Thread.sleep(500);

				EnteredClient = fnpGetORObjectX(OR_objectName).getAttribute(attributeName).trim();
				// fnpMymsg(" ---Singh----value is---"+EnteredClient);
				if (i > (Integer.parseInt(CONFIG.getProperty("genMax_waitTime")) * 2)) {

					break;
				}
			} else {
				break;
			}
		}

		return EnteredClient;
	}

	/*****
	 * Select the value from a Simple list (for both Li and option tag) not
	 * prime faces list
	 ***/

	public static void fnpSimpleSelectList(String ORXpath, String expvalue) throws Throwable {

		String tempDefaultValueOf_ShowinglogMessagesinPopupFrameFlag = CONFIG
				.getProperty("ShowinglogMessagesinPopupFrameFlag");
		CONFIG.setProperty("ShowinglogMessagesinPopupFrameFlag", "false");

		fnpWaitForVisible(ORXpath);
		fnpWaitTillVisiblityOfElementNClickable_OR(ORXpath);
		fnpMymsg(" Expected i.e. going to select value in  " + ORXpath + "list is ==> " + expvalue);
		expvalue = expvalue.trim();

		// String tagname = null;
		String tagname = "";
		boolean ValueMatched = false;
		fnpGetORObjectX(ORXpath).click();
		Thread.sleep(2000);
		fnpMymsg("  --After clicking list to expend it");

		fnpMymsg("Going to count li tags");
		int totalCountforLi = fnpCountChildElementPresenceImmediately_NotInOR(fnpGetORObjectX(ORXpath), "li");
		fnpMymsg("  --li tags are ---" + totalCountforLi);

		if (totalCountforLi > 0) {
			List<WebElement> objectlist5 = fnpGetORObjectX(ORXpath).findElements(By.tagName("li"));
			tagname = "li";
			fnpMymsg(" li tag is present for this list. ");
			for (WebElement dd_value : objectlist5) {
				Actions act = new Actions(driver);
				act.moveToElement(dd_value).sendKeys(Keys.ARROW_DOWN).build().perform();
				if (dd_value.getText().trim().equals(expvalue)) {
					Thread.sleep(1000);
					dd_value.click();
					Thread.sleep(3000);
					ValueMatched = true;
					break;
				} else {
					// Thread.sleep(500);
					Thread.sleep(100);
				}
			}

			if (ValueMatched != true) {
				throw new Exception("Excel value --'" + expvalue + "'  is not matched with DropDown Value.");
			}

		} else {
			fnpMymsg("Going to count option tags");
			int totalCountforOption = fnpCountChildElementPresenceImmediately_NotInOR(fnpGetORObjectX(ORXpath),
					"option");
			fnpMymsg("  --option tags are ---" + totalCountforOption);

			if (totalCountforOption > 0) {
				List<WebElement> objectlistOptions = fnpGetORObjectX(ORXpath).findElements(By.tagName("option"));
				fnpMymsg(" option tag is present for this list. ");
				tagname = "option";

				for (WebElement dd_value : objectlistOptions) {
					Actions act = new Actions(driver);
					act.moveToElement(dd_value).sendKeys(Keys.ARROW_DOWN).build().perform();
					Thread.sleep(1000);

					if (Boolean.parseBoolean((CONFIG.getProperty("ShowinglogMessagesinPopupFrameFlag")))) {
						APP_LOGS.debug("@@@   current value is ---" + dd_value.getText());
					} else {
						fnpMymsg("@@@   current value is ---" + dd_value.getText());

					}

					String pradeep = dd_value.getText();
					fnpMymsg(" current text is ---" + pradeep);
					fnpMymsg(" expected text is ---" + expvalue);

					if (dd_value.getText().trim().equals(expvalue)) {
						fnpMymsg("  --Both above values are matched, so going to click it.");
						act.moveToElement(dd_value).sendKeys(Keys.ARROW_UP).build().perform();
						Thread.sleep(1000);
						dd_value.click();
						Thread.sleep(3000);
						// Thread.sleep(10000);
						ValueMatched = true;
						fnpMymsg("  --clicked ...");
						break;
					} else {
						fnpMymsg("  --Both above values are not matched, so going to check next value.");
						// Thread.sleep(500);
						Thread.sleep(100);

					}
				}

				if (ValueMatched != true) {
					throw new Exception("Excel value --'" + expvalue + "'  is not matched with DropDown Value.");
				}
			}
		}

		if (tagname.equalsIgnoreCase("li")) {
			Thread.sleep(1000);
			// getText is not workin in option tag because in getText it will
			// return everything i.e. all values
			String selectedListValue = fnpGetORObjectX(ORXpath).getText();

			fnpMymsg(" Selected " + ORXpath + "value is ==> " + selectedListValue);
			Assert.assertEquals(selectedListValue, expvalue,
					"Value inserted in " + ORXpath + " is not correct. Inserted value is --> " + selectedListValue);
			fnpMymsg(" Correct value of " + ORXpath + "  inserted in list successfully");

		}

		if (tagname.equalsIgnoreCase("option")) {
			Thread.sleep(1000);
			fnpMymsg("  ---Now going to match selected value.");
			WebElement element99 = fnpGetORObjectX(ORXpath);
			Select se = new Select(element99);

			String selectedListValue = se.getFirstSelectedOption().getText();

			fnpMymsg(" Selected value is ==> " + selectedListValue);
			Assert.assertEquals(selectedListValue, expvalue,
					"Value inserted in " + ORXpath + " is not correct. Inserted value is --> " + selectedListValue);
			fnpMymsg(" Correct value has been  inserted in list successfully");

		}

		CONFIG.setProperty("ShowinglogMessagesinPopupFrameFlag", tempDefaultValueOf_ShowinglogMessagesinPopupFrameFlag);

	}

	public static void fnpSimpleSelectList_contains(String ORXpath, String expvalue) throws Throwable {

		String tempDefaultValueOf_ShowinglogMessagesinPopupFrameFlag = CONFIG
				.getProperty("ShowinglogMessagesinPopupFrameFlag");
		CONFIG.setProperty("ShowinglogMessagesinPopupFrameFlag", "false");

		fnpWaitForVisible(ORXpath);
		fnpWaitTillVisiblityOfElementNClickable_OR(ORXpath);
		fnpMymsg(" Expected i.e. going to select value in  " + ORXpath + "list is ==> " + expvalue);
		expvalue = expvalue.trim();

		// String tagname = null;
		String tagname = "";
		boolean ValueMatched = false;
		fnpGetORObjectX(ORXpath).click();
		Thread.sleep(2000);
		fnpMymsg("  --After clicking list to expend it");

		fnpMymsg("Going to count li tags");
		int totalCountforLi = fnpCountChildElementPresenceImmediately_NotInOR(fnpGetORObjectX(ORXpath), "li");
		fnpMymsg("  --li tags are ---" + totalCountforLi);

		if (totalCountforLi > 0) {
			List<WebElement> objectlist5 = fnpGetORObjectX(ORXpath).findElements(By.tagName("li"));
			tagname = "li";
			fnpMymsg(" li tag is present for this list. ");
			for (WebElement dd_value : objectlist5) {
				Actions act = new Actions(driver);
				act.moveToElement(dd_value).sendKeys(Keys.ARROW_DOWN).build().perform();
				// if (dd_value.getText().trim().equals(expvalue)) {
				if (dd_value.getText().trim().contains(expvalue.trim())) {
					Thread.sleep(1000);
					dd_value.click();
					Thread.sleep(3000);
					ValueMatched = true;
					break;
				} else {
					// Thread.sleep(500);
					Thread.sleep(100);
				}
			}

			if (ValueMatched != true) {
				throw new Exception("Excel value --'" + expvalue + "'  is not matched with DropDown Value.");
			}

		} else {
			fnpMymsg("Going to count option tags");
			int totalCountforOption = fnpCountChildElementPresenceImmediately_NotInOR(fnpGetORObjectX(ORXpath),
					"option");
			fnpMymsg("  --option tags are ---" + totalCountforOption);

			if (totalCountforOption > 0) {
				List<WebElement> objectlistOptions = fnpGetORObjectX(ORXpath).findElements(By.tagName("option"));
				fnpMymsg(" option tag is present for this list. ");
				tagname = "option";

				for (WebElement dd_value : objectlistOptions) {
					Actions act = new Actions(driver);
					act.moveToElement(dd_value).sendKeys(Keys.ARROW_DOWN).build().perform();
					Thread.sleep(1000);

					if (Boolean.parseBoolean((CONFIG.getProperty("ShowinglogMessagesinPopupFrameFlag")))) {
						APP_LOGS.debug("@@@   current value is ---" + dd_value.getText());
					} else {
						fnpMymsg("@@@   current value is ---" + dd_value.getText());

					}

					String pradeep = dd_value.getText();
					fnpMymsg(" current text is ---" + pradeep);
					fnpMymsg(" expectedk text is ---" + expvalue);

					if (dd_value.getText().trim().contains(expvalue.trim())) {
						fnpMymsg("  --Both above values are matched, so going to click it.");
						act.moveToElement(dd_value).sendKeys(Keys.ARROW_UP).build().perform();
						Thread.sleep(1000);
						dd_value.click();
						Thread.sleep(3000);
						// Thread.sleep(10000);
						ValueMatched = true;
						fnpMymsg("  --clicked ...");
						break;
					} else {
						fnpMymsg("  --Both above values are not matched, so going to check next value.");
						// Thread.sleep(500);
						Thread.sleep(100);

					}
				}

				if (ValueMatched != true) {
					throw new Exception("Excel value --'" + expvalue + "'  is not matched with DropDown Value.");
				}
			}
		}

		if (tagname.equalsIgnoreCase("li")) {
			Thread.sleep(1000);

			String selectedListValue = fnpGetORObjectX(ORXpath).getText();

			fnpMymsg(" Selected " + ORXpath + "value is ==> " + selectedListValue);

			Assert.assertTrue(selectedListValue.trim().contains(expvalue.trim()),
					"Value inserted in " + ORXpath + " is not correct. Inserted value is --> " + selectedListValue);
			fnpMymsg(" Correct value of " + ORXpath + "  inserted in list successfully");

		}

		if (tagname.equalsIgnoreCase("option")) {
			Thread.sleep(1000);
			fnpMymsg("  ---Now going to match selected value.");
			WebElement element99 = fnpGetORObjectX(ORXpath);
			Select se = new Select(element99);

			String selectedListValue = se.getFirstSelectedOption().getText();

			fnpMymsg(" Selected value is ==> " + selectedListValue);

			Assert.assertTrue(selectedListValue.trim().contains(expvalue.trim()),
					"Value inserted in " + ORXpath + " is not correct. Inserted value is --> " + selectedListValue);
			fnpMymsg(" Correct value has been  inserted in list successfully");

		}

		CONFIG.setProperty("ShowinglogMessagesinPopupFrameFlag", tempDefaultValueOf_ShowinglogMessagesinPopupFrameFlag);

	}

	public static void fnpSimpleSelectList_contains_old(String ORXpath, String expvalue) throws Throwable {

		String tempDefaultValueOf_ShowinglogMessagesinPopupFrameFlag = CONFIG
				.getProperty("ShowinglogMessagesinPopupFrameFlag");
		CONFIG.setProperty("ShowinglogMessagesinPopupFrameFlag", "false");

		fnpWaitForVisible(ORXpath);
		fnpWaitTillVisiblityOfElementNClickable_OR(ORXpath);
		fnpMymsg(" Expected i.e. going to select value in  " + ORXpath + "list is ==> " + expvalue);
		expvalue = expvalue.trim();

		// String tagname = null;
		String tagname = "";
		boolean ValueMatched = false;
		fnpGetORObjectX(ORXpath).click();
		Thread.sleep(2000);

		if (fnpCountChildElementPresenceImmediately_NotInOR(fnpGetORObjectX(ORXpath), "li") > 0) {
			List<WebElement> objectlist5 = fnpGetORObjectX(ORXpath).findElements(By.tagName("li"));
			tagname = "li";
			fnpMymsg(" li tag is present for this list. ");
			for (WebElement dd_value : objectlist5) {
				Actions act = new Actions(driver);
				act.moveToElement(dd_value).sendKeys(Keys.ARROW_DOWN).build().perform();

				if (dd_value.getText().trim().contains(expvalue.trim())) {
					Thread.sleep(1000);
					dd_value.click();
					Thread.sleep(3000);
					ValueMatched = true;
					break;
				} else {
					// Thread.sleep(500);
					Thread.sleep(100);
				}
			}

			if (ValueMatched != true) {
				throw new Exception("Excel value --'" + expvalue + "'  is not matched with DropDown Value.");
			}

		} else {

			if (fnpCountChildElementPresenceImmediately_NotInOR(fnpGetORObjectX(ORXpath), "option") > 0) {
				List<WebElement> objectlistOptions = fnpGetORObjectX(ORXpath).findElements(By.tagName("option"));
				fnpMymsg(" option tag is present for this list. ");
				tagname = "options";

				for (WebElement dd_value : objectlistOptions) {
					Actions act = new Actions(driver);
					act.moveToElement(dd_value).sendKeys(Keys.ARROW_DOWN).build().perform();
					Thread.sleep(1000);

					if (Boolean.parseBoolean((CONFIG.getProperty("ShowinglogMessagesinPopupFrameFlag")))) {
						APP_LOGS.debug("@@@   current value is ---" + dd_value.getText());
					} else {
						fnpMymsg("@@@   current value is ---" + dd_value.getText());

					}

					if (dd_value.getText().trim().contains(expvalue.trim())) {
						Thread.sleep(1000);
						dd_value.click();
						Thread.sleep(3000);
						ValueMatched = true;
						break;
					} else {
						// Thread.sleep(500);
						Thread.sleep(100);

					}
				}

				if (ValueMatched != true) {
					throw new Exception("Excel value --'" + expvalue + "'  is not matched with DropDown Value.");
				}
			}
		}

		if (tagname.equalsIgnoreCase("li")) {
			Thread.sleep(1000);

			String selectedListValue = fnpGetORObjectX(ORXpath).getText();

			fnpMymsg(" Selected " + ORXpath + "value is ==> " + selectedListValue);

			Assert.assertTrue(selectedListValue.trim().contains(expvalue.trim()),
					"Value inserted in " + ORXpath + " is not correct. Inserted value is --> " + selectedListValue);
			fnpMymsg(" Correct value of " + ORXpath + "  inserted in list successfully");

		}

		if (tagname.equalsIgnoreCase("option")) {
			Thread.sleep(1000);
			WebElement element99 = fnpGetORObjectX(ORXpath);
			Select se = new Select(element99);

			String selectedListValue = se.getFirstSelectedOption().getText();

			fnpMymsg(" Selected value is ==> " + selectedListValue);

			Assert.assertTrue(selectedListValue.trim().contains(expvalue.trim()),
					"Value inserted in " + ORXpath + " is not correct. Inserted value is --> " + selectedListValue);
			fnpMymsg(" Correct value has been  inserted in list successfully");

		}

		CONFIG.setProperty("ShowinglogMessagesinPopupFrameFlag", tempDefaultValueOf_ShowinglogMessagesinPopupFrameFlag);

	}

	public static void fnpSimpleSelectList_usingSelectClass(String ORXpathKey, String expvalue) throws Throwable {

		WebElement element = fnpGetORObjectX(ORXpathKey);
		Select se = new Select(element);
		se.selectByVisibleText(expvalue);
	}

	/*****
	 * Select the value from a Simple list not in OR (for both Li and option
	 * tag) not prime faces list
	 ***/
	public static void fnpSimpleSelectList_NOR(String Xpath, String expvalue) throws Throwable {

		String tempDefaultValueOf_ShowinglogMessagesinPopupFrameFlag = CONFIG
				.getProperty("ShowinglogMessagesinPopupFrameFlag");
		CONFIG.setProperty("ShowinglogMessagesinPopupFrameFlag", "false");

		fnpWaitForVisible_NotInOR(Xpath);

		fnpWaitTillVisiblityOfElementNClickable_NOR(Xpath);
		fnpMymsg(" Expected i.e. going to select value in  " + Xpath + "list is ==> " + expvalue);

		// String tagname = null;
		String tagname = "";
		boolean ValueMatched = false;
		fnpGetORObjectX___NOR(Xpath).click();
		Thread.sleep(2000);

		if (fnpCountChildElementPresenceImmediately_NotInOR(fnpGetORObjectX___NOR(Xpath), "li") > 0) {
			List<WebElement> objectlist5 = fnpGetORObjectX___NOR(Xpath).findElements(By.tagName("li"));
			tagname = "li";
			fnpMymsg(" li tag is present for this list. ");
			for (WebElement dd_value : objectlist5) {
				Actions act = new Actions(driver);
				act.moveToElement(dd_value).sendKeys(Keys.ARROW_DOWN).build().perform();
				if (dd_value.getText().equals(expvalue)) {
					Thread.sleep(1000);
					dd_value.click();
					Thread.sleep(3000);
					ValueMatched = true;
					break;
				}
			}

			if (ValueMatched != true) {
				throw new Exception("Excel value --'" + expvalue + "'  is not matched with DropDown Value.");
			}

		} else {

			if (fnpCountChildElementPresenceImmediately_NotInOR(fnpGetORObjectX___NOR(Xpath), "option") > 0) {
				List<WebElement> objectlistOptions = fnpGetORObjectX___NOR(Xpath).findElements(By.tagName("option"));
				fnpMymsg(" option tag is present for this list. ");
				tagname = "options";
				for (WebElement dd_value : objectlistOptions) {
					Actions act = new Actions(driver);
					act.moveToElement(dd_value).sendKeys(Keys.ARROW_DOWN).build().perform();
					if (dd_value.getText().equals(expvalue)) {
						Thread.sleep(1000);
						dd_value.click();
						Thread.sleep(3000);
						ValueMatched = true;
						break;
					}
				}

				if (ValueMatched != true) {
					throw new Exception("Excel value --'" + expvalue + "'  is not matched with DropDown Value.");
				}
			}
		}

		if (tagname.equalsIgnoreCase("li")) {
			Thread.sleep(1000);

			String selectedListValue = fnpGetORObjectX___NOR(Xpath).getText();

			fnpMymsg(" Selected " + Xpath + "value is ==> " + selectedListValue);
			Assert.assertEquals(selectedListValue, expvalue,
					"Value inserted in " + Xpath + " is not correct. Inserted value is --> " + selectedListValue);
			fnpMymsg(" Correct value of " + Xpath + "  inserted in list successfully");

		}

		if (tagname.equalsIgnoreCase("option")) {
			Thread.sleep(1000);
			WebElement element99 = fnpGetORObjectX___NOR(Xpath);
			Select se = new Select(element99);

			String selectedListValue = se.getFirstSelectedOption().getText();

			fnpMymsg(" Selected value is ==> " + selectedListValue);
			Assert.assertEquals(selectedListValue, expvalue,
					"Value inserted in " + Xpath + " is not correct. Inserted value is --> " + selectedListValue);
			fnpMymsg(" Correct value has been  inserted in list successfully");

		}

		CONFIG.setProperty("ShowinglogMessagesinPopupFrameFlag", tempDefaultValueOf_ShowinglogMessagesinPopupFrameFlag);
	}

	/***** check error in application page ***/
	public static void fnpCheckError(String msg) throws Throwable {

		fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
		if (fnpCheckElementPresenceImmediately("ErrorMessage")) {

			fnpMouseHover_OR_EvenMultipleObjectsHavingSameXpath_usingSpecialForErrorClassOnly("ErrorMessage");
			String errMsg = fnpGetText_OR_EvenMultipleObjectsHavingSameXpath_specialForExtractingErrorMsgTextOnlyEvenIfNotVisible(
					"ErrorMessage").trim();

			int errorLength = errMsg.length();

			fnpMymsg("@  ----Error length is---" + errorLength);
			if (errorLength > 1) {
				fnpMymsg(" Error is thrown by application " + msg);

				throw new Exception(" Error is thrown by application " + msg + "\n\n Error is --->" + errMsg);

			}

		}

		// for Error Page
		if (fnpCheckElementPresenceImmediately("Error_PageIniPulse")) {

			fnpMouseHover_OR_EvenMultipleObjectsHavingSameXpath_usingSpecialForErrorClassOnly("Error_PageIniPulse");
			String errMsg = fnpGetText_OR_EvenMultipleObjectsHavingSameXpath_specialForExtractingErrorMsgTextOnlyEvenIfNotVisible(
					"Content_Of_Error_PageIniPulse").trim();

			int errorLength = errMsg.length();
			fnpMymsg("@  ----Error length is---" + errorLength);
			if (errorLength > 1) {
				fnpMymsg(" Error is thrown by application " + msg);

				throw new Exception(" Error is thrown by application " + msg + "\n\n Error is --->" + errMsg);

			}

		}

		driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")),
				TimeUnit.SECONDS);

	}

	/***** check error in application page ***/
	public static void fnpCheckErrMsg(String errCustomMsg) throws Throwable {

		if (fnpCheckElementPresenceImmediately("ErrorMessage")) {

			fnpMouseHover_OR_EvenMultipleObjectsHavingSameXpath_usingSpecialForErrorClassOnly("ErrorMessage");
			String errmsg = fnpGetText_OR_EvenMultipleObjectsHavingSameXpath_specialForExtractingErrorMsgTextOnlyEvenIfNotVisible(
					"ErrorMessage");

			int errorLength = errmsg.length();

			fnpMymsg("@  ----Error length is---" + errorLength);
			if (errorLength > 1) {
				fnpMymsg(errCustomMsg + " i.e --" + errmsg);
				String pageSource = driver.getPageSource().toString();
				// pageSource = pageSource.replace("@", "");
				pageSource = fnpFormatReplaceSpecailCharacters(pageSource);
				// fnpMymsg("@ ----Page source is \n\n" + pageSource);
				fnpMymsg(
						"@   ---*********COMMENTING PAGE SOURCE For Time being before release on 4th March********************");
				throw new Exception(errCustomMsg + " i.e --" + errmsg);

			}

		}

		if (fnpCheckElementPresenceImmediately("Error_PageIniPulse")) {

			// fnpMouseHover_OR_EvenMultipleObjectsHavingSameXpath("Error_PageIniPulse");
			fnpMouseHover_OR_EvenMultipleObjectsHavingSameXpath_usingSpecialForErrorClassOnly("Error_PageIniPulse");
			String errmsg = fnpGetText_OR_EvenMultipleObjectsHavingSameXpath_specialForExtractingErrorMsgTextOnlyEvenIfNotVisible(
					"Content_Of_Error_PageIniPulse");

			int errorLength = errmsg.length();

			fnpMymsg("@  ----Error length is---" + errorLength);
			if (errorLength > 1) {
				fnpMymsg(errCustomMsg + " i.e --" + errmsg);
				String pageSource = driver.getPageSource().toString();
				// pageSource = pageSource.replace("@", "");
				pageSource = fnpFormatReplaceSpecailCharacters(pageSource);
				// fnpMymsg("@ ----Page source is \n\n" + pageSource);
				fnpMymsg(
						"@   ---*********COMMENTING PAGE SOURCE For Time being before release on 4th March********************");
				throw new Exception(errCustomMsg + " i.e --" + errmsg);

			}

		}

	}

	/***** close the current running browse and login in IE browser for Oasis ***/
	public static void fnpCloseBroserAndLoginInIE() throws Throwable {

		driver.quit();

		fnppradeepKillIEProcess();
		killprocess();

		String oldBrowser = browserName;
		browserName = "IE";
		try {
			fnpLaunchBrowserAndLogin();
		} catch (Throwable t) {
			throw t;
		} finally {
			browserName = oldBrowser;
		}
		// fnpLoading_wait();

		// browserName = oldBrowser;
	}

	/*****
	 * @AfterTest annotation code ---->close the current running browse and kill
	 *            other process
	 ***/
	public static void fnpCommonCloseBrowsersAndKillProcess() throws Throwable {

		try {
			driver.quit();
			IsBrowserPresentAlready = false;

			fnppradeepKillIEProcess();
			killprocess();
		} catch (Throwable t) {
			// nothing to do
		}
	}

	/***** Function which return the current running browser name ***/
	public static String fnpGetCurrRunningBrowserName() {
		String sss = (String) ((JavascriptExecutor) driver).executeScript("return navigator.userAgent;");

		String CurrentRunningBrowserName = null;
		if ((sss.contains("MSIE")) || (sss.contains("rv:11.0"))) {

			CurrentRunningBrowserName = "IE";
		} else {
			if (sss.contains("Firefox")) {

				CurrentRunningBrowserName = "firefox";
			} else {
				if (sss.contains("Chrome")) {

					CurrentRunningBrowserName = "chrome";
				}
			}
		}

		return CurrentRunningBrowserName;

	}

	/***** Common final catch block for @Test ***/
	public static void fnpCommonFinalCatchBlock(Throwable t, String errCustomMsg, String screenshotName)
			throws Throwable {

		fail = true;
		isTestPass = false;
		String errMsg = "";
		try {
			if (fnpCheckElementPresenceImmediately("ErrorMessage")) {

				fnpMouseHover_OR_EvenMultipleObjectsHavingSameXpath_usingSpecialForErrorClassOnly("ErrorMessage");
				errMsg = fnpGetText_OR_EvenMultipleObjectsHavingSameXpath_specialForExtractingErrorMsgTextOnlyEvenIfNotVisible(
						"ErrorMessage");
			}
		} catch (Throwable tt) {
			// nothing to mention here for now
		}

		fnpMymsg(errCustomMsg + " . Error is -->" + t.getMessage() + "'\n\n" + errMsg);

		String textMessage = errCustomMsg + " . Error is -->" + t.getMessage() + "'\n\n" + errMsg;

		if (Boolean.parseBoolean((CONFIG.getProperty("ShowinglogMessagesinPopupFrameFlag")))) {
			fnpDisplayingMessageInFrame(textMessage, 10);
		}

		fnpDesireScreenshot(screenshotName);
		fnpDesireScreenshot_old(screenshotName + "usingOldMethod");

		File a = new File(".\\..\\screenshots" + "//screenshots_" + currentSuiteCode + "//" + currentScriptCode + "//"
				+ screenshotName + "--" + SShots + ".png");
		Reporter.log("<a href=\"" + a
				+ "\">  Click here to see screenshot (if going through email attachment or log history folder)</a>");

		fnpMymsg("");

		File b = new File(".\\..\\..\\" + screenshots_path + "//screenshots_" + currentSuiteCode + "//"
				+ currentScriptCode + "//" + screenshotName + "--" + SShots + ".png");

		Reporter.log("<a href=\"" + b + "\">  Click here to see screenshot (if going through local XSLT folder) </a>");

		String stackTrace = Throwables.getStackTraceAsString(t);
		String errorMsg = t.getMessage();

		errorMsg = errCustomMsg + "   .See screenshot '" + screenshotName + "'\n\n" + errorMsg + " --->\n\n\n\n \""
				+ errMsg + "\" \n\n\n\n " + stackTrace;
		fnpMymsg(errorMsg);
		Exception c = new Exception(errorMsg);
		ErrorUtil.addVerificationFailure(c);

		// killprocess();
		IsBrowserPresentAlready = false;
		throw new Exception(c);

	}

	/*****
	 * Move down if Element not visible (may be using in Proposal questionnaire)
	 ***/
	public static boolean fnpMoveDownIfElementNotVisible(String XpathKey, String refrenceORElement) throws Throwable {
		boolean present = false;

		int i = 0;

		WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(CONFIG.getProperty("genMax_waitTime")));

		int retries = 0;
		while (true) {
			i++;
			try {

				if (OR.getProperty(XpathKey).contains("~")) {

					String[] s1 = OR.getProperty(XpathKey).split("~");
					String locatorValue = s1[1];
					String locator = s1[0];
					if (locator.contains("id")) {// id
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
						wait.until(ExpectedConditions.elementToBeClickable(By.id(locatorValue)));
					} else {
						if (locator.contains("name")) {// name
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue)));
							wait.until(ExpectedConditions.elementToBeClickable(By.name(locatorValue)));

						} else {
							if (locator.toLowerCase().contains("linktext")) {// linkText
								wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locatorValue)));
								wait.until(ExpectedConditions.elementToBeClickable(By.linkText(locatorValue)));

							} else {
								if (locator.toLowerCase().contains("partialLinkText")) {// partialLinkText
									wait.until(ExpectedConditions
											.visibilityOfElementLocated(By.partialLinkText(locatorValue)));
									wait.until(
											ExpectedConditions.elementToBeClickable(By.partialLinkText(locatorValue)));

								} else {
									if (locator.toLowerCase().contains("tagName")) {// tagName
										wait.until(ExpectedConditions
												.visibilityOfElementLocated(By.tagName(locatorValue)));
										wait.until(ExpectedConditions.elementToBeClickable(By.tagName(locatorValue)));

									} else {
										if (locator.toLowerCase().contains("className")) {// className
											wait.until(ExpectedConditions
													.visibilityOfElementLocated(By.className(locatorValue)));
											wait.until(ExpectedConditions
													.elementToBeClickable(By.className(locatorValue)));

										} else {
											if (locator.toLowerCase().contains("css")) {// cssSelector
												wait.until(ExpectedConditions
														.visibilityOfElementLocated(By.cssSelector(locatorValue)));
												wait.until(ExpectedConditions
														.elementToBeClickable(By.cssSelector(locatorValue)));

											} else {

												if (locator.toLowerCase().contains("xpath")) {// xpath
													wait.until(ExpectedConditions
															.visibilityOfElementLocated(By.xpath(locatorValue)));
													wait.until(ExpectedConditions
															.elementToBeClickable(By.xpath(locatorValue)));

												} else {

													// throw new Exception
													// ("Object with name '"+XpathKey+"' is not present in OR");
													/******
													 * By default Xpath will be
													 * assumed
													 *****/
													wait.until(ExpectedConditions
															.visibilityOfElementLocated(By.xpath(locatorValue)));
													wait.until(ExpectedConditions
															.elementToBeClickable(By.xpath(locatorValue)));

												}
											}
										}
									}
								}
							}
						}
					}

				} else {

					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XpathKey)));
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XpathKey)));
				}
				present = true;
				return present;
			} catch (org.openqa.selenium.StaleElementReferenceException e) {
				if (retries < 3) {
					retries++;
					fnpMymsg(retries
							+ "In staleElementException catch block of fnpMoveDownIfElementNotVisible function for "
							+ XpathKey);

					WebElement refElement = fnpGetORObjectX(refrenceORElement);
					fnpMymsg("As element with xpath--" + XpathKey
							+ "  is not visible properly, so going to move down in If block");
					Actions action = new Actions(driver);
					action.moveToElement(refElement).build().perform();
					refElement.sendKeys(Keys.ARROW_DOWN);
					Thread.sleep(2000);
					continue;
				} else {
					throw e;
				}
			} catch (Throwable t) {
				if (retries < 3) {
					retries++;
					fnpMymsg(retries + "In fnpWaitForVisible 's try catch block  for " + XpathKey);
					continue;
				} else {
					String stackTrace = Throwables.getStackTraceAsString(t);
					String errorMsg = t.getMessage();
					errorMsg = errorMsg + "\n\n\n\n Object with name '" + XpathKey + " is not present/found. \n\n"
							+ stackTrace;

					Exception c = new Exception(errorMsg);
					throw c;
				}
			}
		}

	}

	/**** Check Element not present in OR is clickable or not *****/
	public static boolean fnpCheckElementClickableOrNot_notInOR(String Xpath) {
		boolean clickable = false;

		WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(CONFIG.getProperty("genMax_waitTime")));
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Xpath)));
			clickable = true;
			return clickable;
		} catch (Throwable t) {
			return clickable;
		}

	}

	/****
	 * Check Element not present in OR is clickable or not, time in milliseconds
	 *****/
	public static boolean fnpCheckElementClickableOrNot_NOR(String Xpath, int timeInMilliseconds) {
		boolean clickable = false;

		WebDriverWait wait = new WebDriverWait(driver, timeInMilliseconds);
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Xpath)));
			clickable = true;
			return clickable;
		} catch (Throwable t) {
			return clickable;
		}

	}

	/**** Check Element not present in OR is clickable or not *****/
	public static boolean fnpCheckElementClickableOrNot_notInOR(WebElement element) {
		boolean clickable = false;

		WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(CONFIG.getProperty("genMax_waitTime")));
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element));
			clickable = true;
			return clickable;
		} catch (Throwable t) {
			return clickable;
		}

	}

	//
	/**** function to select drop down value ---- *****/
	public static void fnpSelect_By_DownKey_simpleList(String ClickXpathKey, String ListXpathKey, String TagName,
			String Value) throws Throwable {
		boolean ValueNotMatched = true;
		try {

			driver.findElement(By.xpath(ClickXpathKey)).click();
			Thread.sleep(1000);

			List<WebElement> objectlist5 = driver.findElement(By.xpath(ListXpathKey)).findElements(By.tagName(TagName));

			for (WebElement dd_value : objectlist5) {
				Actions act = new Actions(driver);
				act.moveToElement(dd_value).sendKeys(Keys.ARROW_DOWN).build().perform();
				if (dd_value.getText().equals(Value)) {
					Thread.sleep(1000);
					dd_value.click();
					ValueNotMatched = false;
					break;
				}

			}
			if (ValueNotMatched == true) {
				throw new Exception("Excel value --'" + Value + "'  is not matched with DropDown Value.");
			}

			fnpMymsg("Successfully selected the  value [ " + Value + " ] from drop down, having xpath >>  "
					+ ClickXpathKey);
		} catch (NoSuchWindowException W) {
			isTestPass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			isTestPass = false;
			// fnpDesireScreenshot(Value + "--NotPresent");

			throw new Exception("Unable to select value from drop down [ " + ClickXpathKey + " ],plz see screenshot ["
					+ Value + "--NotPresent ]");
		}
	}

	public static int fnpCountChildElementPresenceImmediately_NotInOR(WebElement wb, String tagName) {
		int size = 0;

		try {
			driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
			List<WebElement> objectlist5 = wb.findElements(By.tagName(tagName));
			size = objectlist5.size();
		} catch (Throwable t) {
			// later
		}

		finally {
			driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")),
					TimeUnit.SECONDS);

			return size;
		}
	}

	/*******
	 * function for processing Action item ***This is common code for all action
	 * items
	 ****/

	public static void fnpProcessAI(String ActionItemName, String statusToChange) throws Throwable {
		try {
			fnpCommonClickSnapShotTab();

			// ***************Process the pending Action item***********

			//
			fnpMymsg(" Going to " + statusToChange + "  the pending Action item ---" + ActionItemName);
			fnpWaitForVisible("ActionItemTable_EditWO");
			int ActionItemColIndex = fnpFindColumnIndex("ActionItemTable_HeaderRow", actionItemTableActionItemColName);
			int itemDescColIndex = fnpFindColumnIndex("ActionItemTable_HeaderRow", actionItemTableItemDescColName);
			int actionItemInfoRowNo = fnpFindRow("ActionItemTable_EditWO", ActionItemName, itemDescColIndex);
			String actionItemNo = fnpFetchFromTable("ActionItemTable_EditWO", actionItemInfoRowNo, ActionItemColIndex);
			int start = 1;
			while (actionItemNo.isEmpty()) {
				Thread.sleep(1000);
				actionItemNo = fnpFetchFromTable("ActionItemTable_EditWO", actionItemInfoRowNo, ActionItemColIndex);

				if (start > 50) {
					break;
				}
			}
			Thread.sleep(500);

			fnpClickALinkInATable(actionItemNo);

			fnpMymsg(
					"Clicked on  Action Item '" + ActionItemName + "' in Table having no. is '" + actionItemNo + "' .");

			fnpLoading_wait();

			// In Dietary Supplement this ai is not opened in a dialog/popup
			if ((ActionItemName.equalsIgnoreCase(actionItemDesc_DelistedGMPStandard))) {

				/*
				 * if (fnpCheckElementPresenceImmediately("EditWOBtn_InDelightedActionItem")) {
				 * fnpClick_OR("EditWOBtn_InDelightedActionItem");
				 * }
				 */
				fnpClick_OR("EditWOBtn_InDelightedActionItem");
			}

			if (!(currentSuiteName.equalsIgnoreCase(Dietary_Supplement_suite_Name))
					&& (!(currentSuiteName.equalsIgnoreCase(Non_Food_Compounds_suite_Name)))
					&& (!(currentSuiteName.equalsIgnoreCase(Wales_Work_Order_suite_Name)))) {

				// ----IPM-9473
				// ============================================================================
				String reassignee;
				if ((loginAsFullName == null) || loginAsFullName.equalsIgnoreCase("")) {
					// reassignee=(String) hashXlData.get("AccountManager").trim();
					if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking"))) {
						reassignee = (String) hashXlData.get("AccountManager_Code").trim();
					} else {
						reassignee = (String) hashXlData.get("AccountManager").trim();
					}

				} else {
					// reassignee=loginAsFullName;
					if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking"))) {
						reassignee = loginUser_code;
					} else {
						reassignee = loginAsFullName;
					}
				}
				String assignedTo = fnpGetText_OR("AssignedToLabelValueInAI");

				// if (assignedTo.equalsIgnoreCase(reassignee)) {
				if (assignedTo.contains(reassignee)) {
					fnpMymsg("@  - default value is same as expected, so returning back.");
				} else {
					// fnpClick_OR("ReassignToAccountManager");
					fnpMymsg("Going to reassign this action item to Account Manager.");
					fnpGetORObjectX("ReassignToAccountManager").click();
					Thread.sleep(1000);

				}

				// ==============================================================================================

			}

			// commented below as per IPM-15431
			/*
			 * if (ActionItemName.equalsIgnoreCase(actionItemDesc_CertDecUpdateExt)) {
			 * fnpGetORObjectX("ReassignToExternalReviewer").click();
			 * }
			 * 
			 */

			if ((currentSuiteName.equalsIgnoreCase(Dietary_Supplement_suite_Name))
					&& (ActionItemName.equalsIgnoreCase(actionItemDesc_CertDecUpdate))
					&& (statusToChange.equalsIgnoreCase("Submitted"))) {
				addDocsToCertDecUpdate();
				fnpGetORObjectX("EditWO_SaveBtn").click();
				fnpLoading_wait();
				fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
			}

			// fnpWaitForVisible("ActionItemTab_ChangeStatusPFList");
			fnpPFList("ActionItemTab_ChangeStatusPFList", "ActionItemTab_ChangeStatusPFListOptions", statusToChange);

			/****
			 * In clientQuoteReview in Custom work order, a radio button is to
			 * be click also
			 *****/
			if (ActionItemName.equalsIgnoreCase(actionItemDesc_ClientQuoteReview)) {
				fnpWaitForVisible("Financial_AcceptQuoteRadioBtn");
				fnpGetORObjectX("Financial_AcceptQuoteRadioBtn").click();
			} else {
				if (fnpCheckElementPresenceImmediately("Financial_AcceptQuoteRadioBtn")) {
					fnpGetORObjectX("Financial_AcceptQuoteRadioBtn").click();

				}
			}

			/****
			 * In actionItemDesc_FailureResolution in ModBrack work order
			 * [Select doc type and upload a file]
			 *****/
			if (ActionItemName.equalsIgnoreCase(actionItemDesc_FailureResolution)) {

				String fileNames = (String) hashXlData.get("FailureResolutin_AI_FileName");
				String[] fileCount = fileNames.split(",");
				int fileCountSize = fileCount.length;

				if (!(fileCountSize > 0)) {
					throw new Exception("Upload file names should be given in excel.");
				}

				String fileTypes = (String) hashXlData.get("FailureResolutin_AI_DocType");
				String[] fileTypeArray = fileTypes.split(",");
				int fileTypesCountSize = fileTypeArray.length;

				if (!(fileTypesCountSize == fileCountSize)) {
					throw new Exception("Upload file type should be equal to the no. of files in excel.");
				}

				String fileDesc = (String) hashXlData.get("FailureResolutin_AI_Description");
				String[] fileDescArray = fileDesc.split(",");
				int fileDescCountSize = fileDescArray.length;

				if (!(fileDescCountSize == fileCountSize)) {
					throw new Exception("Upload file access should be equal to the no. of files  in excel.");
				}

				String fileName = "";
				String fname;
				String typelist;
				String descXpath;

				// if ((fnpGetCurrRunningBrowserName().equalsIgnoreCase("firefox"))) {
				if ((fnpGetCurrRunningBrowserName().equalsIgnoreCase("firefox"))) {

					for (int f = 0; f < fileCountSize; f++) {

						Thread.sleep(2000);

						fileName = System.getProperty("user.dir") + "\\docs\\" + fileCount[f];
						driver.findElement(By.xpath(OR.getProperty("FailureResoutionAIAddDocBtn"))).sendKeys(fileName);
						Thread.sleep(1000);
						while (fnpCheckElementPresenceImmediately("BrowseCancelUploadBtn")) {
							Thread.sleep(1000);
						}

						typelist = OR.getProperty("FailResoAddDocsType_PFList_part1") + f
								+ OR.getProperty("FailResoAddDocsType_PFList_part2");
						fnpPFListModify_NOR(typelist, fileTypeArray[f]);

						descXpath = OR.getProperty("FailResoAddDocsDesc_part1") + f
								+ OR.getProperty("FailResoAddDocsDesc_part2");
						fnpType("", descXpath, fileDescArray[f]);

						Thread.sleep(2000);

					}

				} else {

					for (int f = 0; f < fileCountSize; f++) {
						Thread.sleep(2000);
						fname = System.getProperty("user.dir") + "\\docs\\" + fileCount[f];
						if (fnpGetCurrRunningBrowserName().equalsIgnoreCase("chrome")) {
							if (f != fileCountSize - 1) {
								fileName = fileName + fname + "\n";
							} else {
								fileName = fileName + fname;
							}

						} else {
							if (fnpGetCurrRunningBrowserName().equalsIgnoreCase("IE")) {
								fileName = fileName + "\"" + fname + "\"";
							}
						}
					}

					driver.findElement(By.xpath(OR.getProperty("FailureResoutionAIAddDocBtn"))).sendKeys(fileName);
					Thread.sleep(1000);
					while (fnpCheckElementPresenceImmediately("BrowseCancelUploadBtn")) {
						Thread.sleep(1000);
					}

					for (int f = 0; f < fileCountSize; f++) {
						typelist = OR.getProperty("FailResoAddDocsType_PFList_part1") + f
								+ OR.getProperty("FailResoAddDocsType_PFList_part2");
						fnpPFListModify_NOR(typelist, fileTypeArray[f]);

						descXpath = OR.getProperty("FailResoAddDocsDesc_part1") + f
								+ OR.getProperty("FailResoAddDocsDesc_part2");
						fnpType("", descXpath, fileDescArray[f]);
					}

					fnpCheckError("Error is thrown by application while adding data in Document Tab");
				}

			}
			/****
			 * In actionItemDesc_FailureResolution in ModBrack work order
			 * [Select doc type and upload a file]
			 *****/

			/******
			 * refresh specially failed just after clicking save button in 28
			 * new machine, throwing we are sorry sometime, so giving 1 sec.
			 * wait before clicking save button
			 */

			if (ActionItemName.equalsIgnoreCase(actionItemDesc_DocumentFinal)) {
				String documentCheckOption = (String) hashXlData.get("DocumentCheckOptions_DocumentFinalAI").trim();
				String chkBxLabelXpath;
				String chkBxXpath;
				String checkedStatusClass;
				chkBxLabelXpath = OR.getProperty("documentCheckRadioBtn_part1") + documentCheckOption
						+ OR.getProperty("documentCheckRadioBtn_part2");

				chkBxXpath = chkBxLabelXpath + "/../../td[1]/div/div[2]";
				checkedStatusClass = driver.findElement(By.xpath(chkBxXpath)).getAttribute("class");
				if (!(checkedStatusClass.contains("ui-state-active"))) {
					driver.findElement(By.xpath(chkBxLabelXpath)).click();
					Thread.sleep(2000);
				}

			}

			// Thread.sleep(1000);
			if (!(ActionItemName.equalsIgnoreCase(actionItemDesc_DelistedGMPStandard))) {
				fnpMouseHover("ActionItem_dialog_headerTitle_xpath");
				fnpGetORObjectX("ActionItem_dialog_headerTitle_xpath").click();
				// Thread.sleep(4000);
				Thread.sleep(1000);
			}
			/******
			 * refresh specially failed just after clicking save button in 28
			 * new machine, throwing we are sorry sometime, so giving 1 sec.
			 * wait before clicking save button
			 */

			/*
			 * if ((ActionItemName.equalsIgnoreCase(actionItemDesc_AssessPnlReview)) &&
			 * (statusToChange.equalsIgnoreCase("Completed")) ){
			 * 
			 * String additionalInfoCheckOption = (String)
			 * hashXlData.get("Additional_Info_section").trim();
			 * String chkBxLabelXpath;
			 * String chkBxXpath;
			 * String checkedStatusClass;
			 * chkBxLabelXpath =
			 * OR.getProperty("AssessPnlReview_additionalInfo_RadioBtn_part1") +
			 * additionalInfoCheckOption +
			 * OR.getProperty("AssessPnlReview_additionalInfo_RadioBtn_part2");
			 * 
			 * chkBxXpath = chkBxLabelXpath + "/../../td[1]/div/div[2]";
			 * checkedStatusClass =
			 * driver.findElement(By.xpath(chkBxXpath)).getAttribute("class");
			 * if (!(checkedStatusClass.contains("ui-state-active"))) {
			 * driver.findElement(By.xpath(chkBxLabelXpath)).click();
			 * Thread.sleep(2000);
			 * }
			 * 
			 * }
			 * 
			 * 
			 * if ((ActionItemName.equalsIgnoreCase(actionItemDesc_ProdAssessReview)) &&
			 * (statusToChange.equalsIgnoreCase("Completed")) ){
			 * 
			 * String additionalInfoCheckOption = (String)
			 * hashXlData.get("Additional_Info_section").trim();
			 * String chkBxLabelXpath;
			 * String chkBxXpath;
			 * String checkedStatusClass;
			 * chkBxLabelXpath =
			 * OR.getProperty("AssessPnlReview_additionalInfo_RadioBtn_part1") +
			 * additionalInfoCheckOption +
			 * OR.getProperty("AssessPnlReview_additionalInfo_RadioBtn_part2");
			 * 
			 * chkBxXpath = chkBxLabelXpath + "/../../td[1]/div/div[2]";
			 * checkedStatusClass =
			 * driver.findElement(By.xpath(chkBxXpath)).getAttribute("class");
			 * if (!(checkedStatusClass.contains("ui-state-active"))) {
			 * driver.findElement(By.xpath(chkBxLabelXpath)).click();
			 * Thread.sleep(2000);
			 * }
			 */

			if (((ActionItemName.equalsIgnoreCase(actionItemDesc_AssessPnlReview))
					|| (ActionItemName.equalsIgnoreCase(actionItemDesc_ProdAssessReview))) &&
					(statusToChange.equalsIgnoreCase("Completed"))

			) {

				String additionalInfoCheckOption = null;
				if (ActionItemName.equalsIgnoreCase(actionItemDesc_AssessPnlReview)) {
					additionalInfoCheckOption = ((String) hashXlData.get("Additional_Info_section_AssessPnlReviewAI"))
							.trim();
				}
				if (ActionItemName.equalsIgnoreCase(actionItemDesc_ProdAssessReview)) {
					additionalInfoCheckOption = (String) hashXlData.get("Additional_Info_section_ProdAssessReviewAI")
							.trim();
				}
				String chkBxLabelXpath = null;
				String chkBxXpath;
				String checkedStatusClass;
				// chkBxLabelXpath =
				// OR.getProperty("AssessPnlReview_additionalInfo_RadioBtn_part1") +
				// additionalInfoCheckOption +
				// OR.getProperty("AssessPnlReview_additionalInfo_RadioBtn_part2");

				if (ActionItemName.equalsIgnoreCase(actionItemDesc_AssessPnlReview)) {
					chkBxLabelXpath = OR.getProperty("AssessPnlReview_additionalInfo_RadioBtn_part1")
							+ additionalInfoCheckOption
							+ OR.getProperty("AssessPnlReview_additionalInfo_RadioBtn_part2");
				}
				if (ActionItemName.equalsIgnoreCase(actionItemDesc_ProdAssessReview)) {
					chkBxLabelXpath = OR.getProperty("ProdAssessReviewAI_additionalInfo_RadioBtn_part1")
							+ additionalInfoCheckOption
							+ OR.getProperty("ProdAssessReviewAI_additionalInfo_RadioBtn_part2");
				}

				chkBxXpath = chkBxLabelXpath + "/../../td[1]/div/div[2]";
				checkedStatusClass = driver.findElement(By.xpath(chkBxXpath)).getAttribute("class");
				if (!(checkedStatusClass.contains("ui-state-active"))) {
					driver.findElement(By.xpath(chkBxLabelXpath)).click();
					Thread.sleep(2000);
				}

				if ((ActionItemName.equalsIgnoreCase(actionItemDesc_ProdAssessReview))) {

					if ((classNameText.equalsIgnoreCase(ModBrack_WRAS_Approved_ClassName))
							|| (classNameText.equalsIgnoreCase(ModBrack_TMV_Certified_ClassName))
							|| (classNameText.equalsIgnoreCase(Resolution_WRAS_Approved_ClassName))) {
						fnpGetORObjectX("EditWO_SaveBtn").click();
						fnpWaitingForExpectedErrorMsg(
								IPULSE_CONSTANTS.ProdAssessReviewAIValidationMsgOnCompletingWithoutApprovalNo);
						String Msg = fnpGetText_OR("ErrorMessage");
						String expectedErrorMsg = IPULSE_CONSTANTS.ProdAssessReviewAIValidationMsgOnCompletingWithoutApprovalNo;
						Assert.assertTrue(Msg.toLowerCase().contains(expectedErrorMsg.toLowerCase()),
								"Expected top error message does not contain '" + expectedErrorMsg + "  ' message.");
						fnpMymsg(
								"Expected top error Message is coming while completing ProdAssessReview action item without giving approval no. is ----"
										+ Msg);

						fnpType("OR", "Wales_AI_ApprovalNo_txtBox",
								((String) hashXlData.get("Approval_Number")).trim());
					}

				}

				/*
				 * fnpGetORObjectX("EditWO_SaveBtn").click();
				 * fnpLoading_wait();
				 */

			}

			// fnpGetORObjectX("EditWO_SaveBtn").click();
			if ((ActionItemName.equalsIgnoreCase(actionItemDesc_DelistedGMPStandard))) {
				fnpGetORObjectX("EditWO_SaveBtn_ConsilatatedView").click();
			} else {
				fnpGetORObjectX("EditWO_SaveBtn").click();
			}

			fnpLoading_wait_withoutErrorChecking();
			fnpLoading_wait();

			/**** In actionItemDesc_FailureResolution in ModBrack work order *****/
			if (ActionItemName.equalsIgnoreCase(actionItemDesc_FailureResolution)) {
				fnpWaitForVisible("YesRadioBtnInFailureResolutinAIInModBrack");
				fnpGetORObjectX("YesRadioBtnInFailureResolutinAIInModBrack").click();
				fnpClickInDialog_OR("SaveBtn_inAIInModBrack");
			}
			/****
			 * In actionItemDesc_FailureResolution in ModBrack work order
			 * [Select doc type and upload a file]
			 *****/

			fnpCheckError("while Going to " + statusToChange + "  the pending Action item ---" + ActionItemName);

			// fnpWaitForVisible("TopMessage3");
			fnpWaitForVisibleHavingMultipleElements("TopMessage3");
			String SuccessfulMsg = fnpGetText_OR("TopMessage3");
			fnpMymsg("Top Message after " + statusToChange + "  the pending Action item '" + ActionItemName + "' ----"
					+ SuccessfulMsg);

			Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"),
					"Top message does not contain 'success' word, so might be action of " + statusToChange
							+ "  the pending Action item '" + ActionItemName + "'  is NOT successful.");

			// In Dietary Supplement this ai is not opened in a dialog/popup
			if ((ActionItemName.equalsIgnoreCase(actionItemDesc_DelistedGMPStandard))) {
				fnpClick_OR("WO_BackToViewBtn");
				fnpClick_OR("WO_BackBtn");
			} else {

				fnpGetORObjectX("ActionItemDialogCloseSign").click();
				fnpMymsg("Just after clicking successfully on Close sign");
			}

			// fnpManuallyRefresh();
			fnpLoading_wait(); // now loading icon is getting come from 15-05-15
								// on trun , and soon come on branch as well

			int StatusColIndex = 0;
			String Status_ActionItemTable = null;
			int whileIchance = 0;
			while (true) {

				try {
					whileIchance++;
					StatusColIndex = fnpFindColumnIndex("ActionItemTable_HeaderRow", actionItemTableStatusColName);
					Status_ActionItemTable = fnpFetchFromTable("ActionItemTable_EditWO", actionItemInfoRowNo,
							StatusColIndex);
					fnpMymsg("Current Status of this action item '" + ActionItemName + "' is ---"
							+ Status_ActionItemTable + "  ---- after chances ---" + whileIchance);
					if (Status_ActionItemTable.equalsIgnoreCase(statusToChange)) {
						break;

					} else {
						if (whileIchance > Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
							break;
						} else {
							Thread.sleep(1000);
							// nothing to do...in loop
						}
					}

				} catch (org.openqa.selenium.StaleElementReferenceException s) {
					// nothing to do
					if (whileIchance > Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
						break;
					} else {
						Thread.sleep(1000);
						// nothing to do...in loop
					}
				}

			}
			Assert.assertTrue(Status_ActionItemTable.equalsIgnoreCase(statusToChange),
					"After " + statusToChange + " the '" + ActionItemName
							+ "' action item , status has not been changed from 'PENDING' to '" + statusToChange
							+ "' . ");

			fnpMymsg(statusToChange + "  the pending Action item for ---" + ActionItemName);
			// ***************Complete the pending Actionitem***********

		} catch (Throwable t) {

			fnpMymsg("  Failed while '" + statusToChange + "'  the pending Action item  action item '" + ActionItemName
					+ "  . Error is ---" + t.getMessage());
			fnpDesireScreenshot(ActionItemName + "Failed");

			String stackTrace = Throwables.getStackTraceAsString(t);
			String errorMsg = t.getMessage();
			errorMsg = errorMsg + "\n\n\n\n Failed while completing action item '" + ActionItemName
					+ "    .See screenshot '" + ActionItemName + "Failed.' \n\n" + stackTrace;
			Exception c = new Exception(errorMsg);
			ErrorUtil.addVerificationFailure(c);
			throw c;

		}

	}

	public static void fnpProcessAI_ISR_old(String dataTableXpathNameInOR, String headerTableXpathNameInOR,
			String ActionItemName, String statusToChange, String saveNCloseBtnInOR)
			throws Throwable {
		try {

			// ***************Process the pending Action item***********

			//
			fnpMymsg(" Going to " + statusToChange + "  the pending Action item ---" + ActionItemName);
			fnpWaitForVisible(dataTableXpathNameInOR);

			int ActionItemColIndex = fnpFindColumnIndex(headerTableXpathNameInOR, "Action Items");

			int itemDescColIndex = fnpFindColumnIndex(headerTableXpathNameInOR, "Action Item Name");

			int actionItemInfoRowNo = fnpFindRow(dataTableXpathNameInOR, ActionItemName, itemDescColIndex);
			String actionItemNo = fnpFetchFromTable(dataTableXpathNameInOR, actionItemInfoRowNo, ActionItemColIndex);
			int start = 1;
			while (actionItemNo.isEmpty()) {
				Thread.sleep(1000);
				actionItemNo = fnpFetchFromTable(dataTableXpathNameInOR, actionItemInfoRowNo, ActionItemColIndex);

				if (start > 50) {
					break;
				}
			}
			Thread.sleep(500);

			fnpClickALinkInATable(actionItemNo);

			fnpMymsg(
					"Clicked on  Action Item '" + ActionItemName + "' in Table having no. is '" + actionItemNo + "' .");

			fnpLoading_wait();

			fnpWaitForVisible("ActionItemTab_ChangeStatusPFList");
			String defaultValue = fnpGetORObjectX("ActionItemTab_ChangeStatusPFList").getText().trim().toLowerCase();
			fnpPFList("ActionItemTab_ChangeStatusPFList", "ActionItemTab_ChangeStatusPFListOptions", statusToChange);

			// fnpGetORObjectX(saveNCloseBtnInOR).click(); //commented on
			// 03-10-2017 as error message on click after loading is not
			// capturing
			fnpClick_OR(saveNCloseBtnInOR);

			/************
			 * It will come always for changing from created to completed from
			 * today onwards 11-07-2017 against IPM-6594
			 ********************/

			if (statusToChange.equalsIgnoreCase("completed")) {

				fnpClickInDialog_OR("WOISR_AIConfirmationBtn");
			}

			/***************************************************************************************/

			fnpCheckError("while Going to " + statusToChange + "  the pending Action item ---" + ActionItemName);

			fnpWaitForVisibleHavingMultipleElements("TopMessage3");
			String SuccessfulMsg = fnpGetText_OR("TopMessage3");
			fnpMymsg("Top Message after " + statusToChange + "  the pending Action item '" + ActionItemName + "' ----"
					+ SuccessfulMsg);

			Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"),
					"Top message does not contain 'success' word, so might be action of " + statusToChange
							+ "  the pending Action item '" + ActionItemName + "'  is NOT successful.");

			int StatusColIndex = 0;
			String Status_ActionItemTable = null;
			int whileIchance = 0;
			while (true) {

				try {
					whileIchance++;
					StatusColIndex = fnpFindColumnIndex(headerTableXpathNameInOR, actionItemTableStatusColName);
					Status_ActionItemTable = fnpFetchFromTable(dataTableXpathNameInOR, actionItemInfoRowNo,
							StatusColIndex);
					fnpMymsg("Current Status of this action item '" + ActionItemName + "' is ---"
							+ Status_ActionItemTable + "  ---- after chances ---" + whileIchance);

					if (Status_ActionItemTable.toLowerCase().contains(statusToChange.toLowerCase())) {
						break;

					} else {
						if (whileIchance > Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
							break;
						} else {
							Thread.sleep(1000);
							// nothing to do...in loop
						}
					}

				} catch (org.openqa.selenium.StaleElementReferenceException s) {
					// nothing to do
					if (whileIchance > Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
						break;
					} else {
						Thread.sleep(1000);
						// nothing to do...in loop
					}
				}

				fnpCheckError("while Going to " + statusToChange + "  the pending Action item ---" + ActionItemName);// adding
																														// this
																														// on
																														// 03-10-2017
																														// as
																														// error
																														// message
																														// on
																														// click
																														// after
																														// loading
																														// is
																														// not
																														// capturing

			}

			Assert.assertTrue(Status_ActionItemTable.toLowerCase().contains(statusToChange.toLowerCase()),
					"After " + statusToChange + " the '" + ActionItemName
							+ "' action item , status has not been changed from 'PENDING' to '" + Status_ActionItemTable
							+ "' . ");

			fnpMymsg(Status_ActionItemTable + "  is the current status of  Action item  ---" + ActionItemName);
			// ***************Complete the pending Actionitem***********

		} catch (Throwable t) {

			fnpMymsg("  Failed while completing action item '" + ActionItemName + "  . Error is ---" + t.getMessage());
			fnpDesireScreenshot(ActionItemName + "Failed");

			String stackTrace = Throwables.getStackTraceAsString(t);
			String errorMsg = t.getMessage();
			errorMsg = errorMsg + "\n\n\n\n Failed while completing action item '" + ActionItemName
					+ "    .See screenshot '" + ActionItemName + "Failed.' \n\n" + stackTrace;
			Exception c = new Exception(errorMsg);
			ErrorUtil.addVerificationFailure(c);
			throw c;

		}

	}

	public static void fnpProcessAI_ISR(String dataTableXpathNameInOR, String headerTableXpathNameInOR,
			String ActionItemName, String statusToChange, String saveNCloseBtnInOR,
			String ReassignTo) throws Throwable {
		try {

			// ***************Process the pending Action item***********

			//
			fnpMymsg(" Going to " + statusToChange + "  the pending Action item ---" + ActionItemName);
			fnpWaitForVisible(dataTableXpathNameInOR);

			int ActionItemColIndex = fnpFindColumnIndex(headerTableXpathNameInOR, "Action Items");

			int itemDescColIndex = fnpFindColumnIndex(headerTableXpathNameInOR, "Action Item Name");

			int actionItemInfoRowNo = fnpFindRow(dataTableXpathNameInOR, ActionItemName, itemDescColIndex);
			String actionItemNo = fnpFetchFromTable(dataTableXpathNameInOR, actionItemInfoRowNo, ActionItemColIndex);
			int start = 1;
			while (actionItemNo.isEmpty()) {
				Thread.sleep(1000);
				actionItemNo = fnpFetchFromTable(dataTableXpathNameInOR, actionItemInfoRowNo, ActionItemColIndex);

				if (start > 50) {
					break;
				}
			}
			Thread.sleep(500);

			fnpClickALinkInATable(actionItemNo);

			fnpMymsg(
					"Clicked on  Action Item '" + ActionItemName + "' in Table having no. is '" + actionItemNo + "' .");

			fnpLoading_wait();

			if (!(ReassignTo.equalsIgnoreCase(""))) {
				String alreadyAssingee = fnpGetORObjectX("AssignedToLabelValue").getText().trim();
				// if (alreadyAssingee.equalsIgnoreCase(ReassignTo)) {
				if (alreadyAssingee.contains(ReassignTo)) {
					fnpMymsg("@  - default value is same as expected, so returning back.");
				} else {
					fnpClick_OR("ReassignAILkpBtn");
					// fnpSearchNSelectFirstRadioBtn(String[][] lookup , int
					// level) //we can use this later when making independent of
					// lookup text box no.
					// fnpSearchNSelectFirstRadioBtn(2, ReassignTo, 1);
					if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking"))) {
						fnpSearchNSelectFirstRadioBtn(1, ReassignTo, 1);
					} else {
						fnpSearchNSelectFirstRadioBtn(2, ReassignTo, 1);
					}
				}

			}

			fnpWaitForVisible("ActionItemTab_ChangeStatusPFList");
			String defaultValue = fnpGetORObjectX("ActionItemTab_ChangeStatusPFList").getText().trim().toLowerCase();
			fnpPFList("ActionItemTab_ChangeStatusPFList", "ActionItemTab_ChangeStatusPFListOptions", statusToChange);

			// fnpGetORObjectX(saveNCloseBtnInOR).click(); //commented on
			// 03-10-2017 as error message on click after loading is not
			// capturing
			fnpClick_OR(saveNCloseBtnInOR);

			/************
			 * It will come always for changing from created to completed from
			 * today onwards 11-07-2017 against IPM-6594
			 ********************/

			if (statusToChange.equalsIgnoreCase("completed")) {

				fnpClickInDialog_OR("WOISR_AIConfirmationBtn");
			}

			/***************************************************************************************/

			fnpCheckError("while Going to " + statusToChange + "  the pending Action item ---" + ActionItemName);

			fnpWaitForVisibleHavingMultipleElements("TopMessage3");
			String SuccessfulMsg = fnpGetText_OR("TopMessage3");
			fnpMymsg("Top Message after " + statusToChange + "  the pending Action item '" + ActionItemName + "' ----"
					+ SuccessfulMsg);

			Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"),
					"Top message does not contain 'success' word, so might be action of " + statusToChange
							+ "  the pending Action item '" + ActionItemName + "'  is NOT successful.");

			int StatusColIndex = 0;
			String Status_ActionItemTable = null;
			int whileIchance = 0;
			while (true) {

				try {
					whileIchance++;
					StatusColIndex = fnpFindColumnIndex(headerTableXpathNameInOR, actionItemTableStatusColName);
					Status_ActionItemTable = fnpFetchFromTable(dataTableXpathNameInOR, actionItemInfoRowNo,
							StatusColIndex);
					fnpMymsg("Current Status of this action item '" + ActionItemName + "' is ---"
							+ Status_ActionItemTable + "  ---- after chances ---" + whileIchance);

					if (Status_ActionItemTable.toLowerCase().contains(statusToChange.toLowerCase())) {
						break;

					} else {
						if (whileIchance > Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
							break;
						} else {
							Thread.sleep(1000);
							// nothing to do...in loop
						}
					}

				} catch (org.openqa.selenium.StaleElementReferenceException s) {
					// nothing to do
					if (whileIchance > Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
						break;
					} else {
						Thread.sleep(1000);
						// nothing to do...in loop
					}
				}

				fnpCheckError("while Going to " + statusToChange + "  the pending Action item ---" + ActionItemName);

			}

			Assert.assertTrue(Status_ActionItemTable.toLowerCase().contains(statusToChange.toLowerCase()),
					"After " + statusToChange + " the '" + ActionItemName
							+ "' action item , status has not been changed  to '" + Status_ActionItemTable + "' . ");

			fnpMymsg(Status_ActionItemTable + "  is the current status of  Action item  ---" + ActionItemName);
			// ***************Complete the pending Actionitem***********

		} catch (Throwable t) {

			fnpMymsg("  Failed while completing action item '" + ActionItemName + "  . Error is ---" + t.getMessage());
			fnpDesireScreenshot(ActionItemName + "Failed");

			String stackTrace = Throwables.getStackTraceAsString(t);
			String errorMsg = t.getMessage();
			errorMsg = errorMsg + "\n\n\n\n Failed while completing action item '" + ActionItemName
					+ "    .See screenshot '" + ActionItemName + "Failed.' \n\n" + stackTrace;
			Exception c = new Exception(errorMsg);
			ErrorUtil.addVerificationFailure(c);
			throw c;

		}

	}

	public static void fnpProcessAI_ISR_NOR(String dataTableXpath, String headerTableXpath, String ActionItemName,
			String statusToChange, String saveNCloseBtnInOR,
			String ReassignTo) throws Throwable {
		try {

			// ***************Process the pending Action item***********

			//
			fnpMymsg(" Going to " + statusToChange + "  the pending Action item ---" + ActionItemName);
			// fnpWaitForVisible_NOR(dataTableXpath);

			int ActionItemColIndex = fnpFindColumnIndex_NOR_TraversingFromVariousNodes(headerTableXpath,
					"Action Items");

			int itemDescColIndex = fnpFindColumnIndex_NOR_TraversingFromVariousNodes(headerTableXpath,
					"Action Item Name");

			int actionItemInfoRowNo = fnpFindRow_NOR(dataTableXpath, ActionItemName, itemDescColIndex);
			String actionItemNo = fnpFetchFromTable_NOR_TraversingFromVariousNodes(dataTableXpath, actionItemInfoRowNo,
					ActionItemColIndex);
			int start = 1;
			while (actionItemNo.isEmpty()) {
				Thread.sleep(1000);
				actionItemNo = fnpFetchFromTable_NOR_TraversingFromVariousNodes(dataTableXpath, actionItemInfoRowNo,
						ActionItemColIndex);

				if (start > 50) {
					break;
				}
			}
			Thread.sleep(500);

			fnpClickALinkInATable(actionItemNo);

			fnpMymsg(
					"Clicked on  Action Item '" + ActionItemName + "' in Table having no. is '" + actionItemNo + "' .");

			fnpLoading_wait();

			if (!(ReassignTo.equalsIgnoreCase(""))) {
				String alreadyAssingee = fnpGetORObjectX("AssignedToLabelValue").getText().trim();
				// if (alreadyAssingee.equalsIgnoreCase(ReassignTo)) {
				if (alreadyAssingee.contains(ReassignTo)) {
					fnpMymsg("@  - default value is same as expected, so returning back.");
				} else {
					fnpClick_OR("ReassignAILkpBtn");
					// fnpSearchNSelectFirstRadioBtn(String[][] lookup , int
					// level) //we can use this later when making independent of
					// lookup text box no.
					// fnpSearchNSelectFirstRadioBtn(2, ReassignTo, 1);

					if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking"))) {
						fnpSearchNSelectFirstRadioBtn(1, ReassignTo, 1);
					} else {
						fnpSearchNSelectFirstRadioBtn(2, ReassignTo, 1);
					}
				}

			}

			fnpWaitForVisible("ActionItemTab_ChangeStatusPFList");
			String defaultValue = fnpGetORObjectX("ActionItemTab_ChangeStatusPFList").getText().trim().toLowerCase();
			fnpPFList("ActionItemTab_ChangeStatusPFList", "ActionItemTab_ChangeStatusPFListOptions", statusToChange);

			// fnpGetORObjectX(saveNCloseBtnInOR).click(); //commented on
			// 03-10-2017 as error message on click after loading is not
			// capturing
			fnpClick_OR(saveNCloseBtnInOR);

			/*			
			*//************
				 * It will come always for changing from created to completed
				 * from today onwards 11-07-2017 against IPM-6594
				 ********************/
			/*
			 * if (! (classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.
			 * WOISOCampusWOTillInProcessTestCaseName)) ){ if
			 * (statusToChange.equalsIgnoreCase("completed")) {
			 * fnpClickInDialog_OR("WOISR_AIConfirmationBtn"); } }
			 *//***************************************************************************************/
			/*
			
			*/

			/************
			 * It will come always for changing from created to completed from
			 * today onwards 11-07-2017 against IPM-6594
			 ********************/

			if (statusToChange.equalsIgnoreCase("completed")) {

				fnpClickInDialog_OR("WOISR_AIConfirmationBtn");
			}

			/***************************************************************************************/

			fnpCheckError("while Going to " + statusToChange + "  the pending Action item ---" + ActionItemName);

			fnpWaitForVisibleHavingMultipleElements("TopMessage3");
			String SuccessfulMsg = fnpGetText_OR("TopMessage3");
			fnpMymsg("Top Message after " + statusToChange + "  the pending Action item '" + ActionItemName + "' ----"
					+ SuccessfulMsg);

			Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"),
					"Top message does not contain 'success' word, so might be action of " + statusToChange
							+ "  the pending Action item '" + ActionItemName + "'  is NOT successful.");

			int StatusColIndex = 0;
			String Status_ActionItemTable = null;
			int whileIchance = 0;
			while (true) {

				try {
					whileIchance++;
					StatusColIndex = fnpFindColumnIndex_NOR_TraversingFromVariousNodes(headerTableXpath,
							actionItemTableStatusColName);
					Status_ActionItemTable = fnpFetchFromTable_NOR_TraversingFromVariousNodes(dataTableXpath,
							actionItemInfoRowNo, StatusColIndex);
					fnpMymsg("Current Status of this action item '" + ActionItemName + "' is ---"
							+ Status_ActionItemTable + "  ---- after chances ---" + whileIchance);
					// if
					// (Status_ActionItemTable.equalsIgnoreCase(statusToChange))
					// {
					if (Status_ActionItemTable.toLowerCase().contains(statusToChange.toLowerCase())) {
						break;

					} else {
						if (whileIchance > Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
							break;
						} else {
							Thread.sleep(1000);
							// nothing to do...in loop
						}
					}

				} catch (org.openqa.selenium.StaleElementReferenceException s) {
					// nothing to do
					if (whileIchance > Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
						break;
					} else {
						Thread.sleep(1000);
						// nothing to do...in loop
					}
				}

				fnpCheckError("while Going to " + statusToChange + "  the pending Action item ---" + ActionItemName);

			}

			Assert.assertTrue(Status_ActionItemTable.toLowerCase().contains(statusToChange.toLowerCase()),
					"After " + statusToChange + " the '" + ActionItemName
							+ "' action item , status has not been changed  to '" + Status_ActionItemTable + "' . ");

			fnpMymsg(Status_ActionItemTable + "  is the current status of  Action item  ---" + ActionItemName);
			// ***************Complete the pending Actionitem***********

		} catch (Throwable t) {

			fnpMymsg("  Failed while completing action item '" + ActionItemName + "  . Error is ---" + t.getMessage());
			fnpDesireScreenshot(ActionItemName + "Failed");

			String stackTrace = Throwables.getStackTraceAsString(t);
			String errorMsg = t.getMessage();
			errorMsg = errorMsg + "\n\n\n\n Failed while completing action item '" + ActionItemName
					+ "    .See screenshot '" + ActionItemName + "Failed.' \n\n" + stackTrace;
			Exception c = new Exception(errorMsg);
			ErrorUtil.addVerificationFailure(c);
			throw c;

		}

	}

	/*	*//**** incomplete , not using now ****//*
												 * public static void fnpChooseDateFromCalender(Date vdate) {
												 * 
												 * Date date = new Date();
												 * 
												 * DateFormat dateFormat = new
												 * SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
												 * 
												 * Calendar cal = Calendar.getInstance();
												 * 
												 * int currDate = cal.get(Calendar.DATE);
												 * 
												 * }
												 */

	// commented because of jira FPC-86 and added new code for this method
	/******* Enter value in suggestion box ****/
	public static void fnpCommonSuggestionBox(String ORObjectName, String value, String optionsORXpath,
			String OptionsListORXpath) throws Throwable {

		fnpWaitForVisible(ORObjectName);

		fnpWaitTillVisiblityOfElementNClickable_OR(ORObjectName);

		fnpType("OR", ORObjectName, value);

		fnpWaitForVisible(optionsORXpath);

		// String xpathExpectedValue = OR.getProperty(OptionsListORXpath) +
		// "[@data-item-label='" + value + "']";
		String xpathExpectedValue = OR.getProperty(OptionsListORXpath) + "[contains(@data-item-label,'" + value + "')]";

		fnpWaitForVisible_NotInOR(xpathExpectedValue);
		fnpMouseHover_NotInOR(xpathExpectedValue);
		driver.findElement(By.xpath(xpathExpectedValue)).click();
		Thread.sleep(2000);
		// fnpLoading_wait(); //commented because //IPM 3668 for standard
		// version , error is present already on this page and thats why script
		// failed here as it catch this error message
	}

	// IPM 3668
	public static void fnpInsertStandardVersionAtInfoTab(String standardVersionTxtValue) throws Throwable {
		// Standard Version is no longer mandatory for time being, may be
		// mandatory later so commentted that part only.

		// Not mandatory for Dietary supplement and hence no error message comes for
		// saving wo without inserting standard version
		if (!(currentSuiteName.equalsIgnoreCase(Dietary_Supplement_suite_Name))) {
			fnpGetORObjectX("ProAddDocSaveBtn").click();
			fnpWaitingForExpectedErrorMsg(IPULSE_CONSTANTS.StandardVersionMandatoryValidationMsg);
			String Msg = fnpGetText_OR("ErrorMessage");
			String expectedErrorMsg = IPULSE_CONSTANTS.StandardVersionMandatoryValidationMsg;
			Assert.assertTrue(Msg.toLowerCase().contains(expectedErrorMsg.toLowerCase()),
					"Expected top error message does not contain '" + expectedErrorMsg + "  ' message.");
			fnpMymsg(
					"Expected top error Message is coming while changing status from DRAFT to InReview in Info tab  is ----"
							+ Msg);
		}

		// fnpCommonSuggestionBox("StandardVersionTxt", standardVersionTxtValue,
		// "StandardVersionOptionsXpath", "StandardVersionOptions"); //jira FPC-86
		fnsDD_Value_select_by_DDLabelName_and_Filter("Standard Version", standardVersionTxtValue);
		fnpGetORObjectX("ProAddDocSaveBtn").click();
		fnpLoading_wait();
		fnpCheckError(" after inserting Standard Version and saving it ");

	}

	/******* Check error using page source ****/
	public static void fnpCheckErrorUsingPageSource() throws Throwable {
		try {

			if (driver.getPageSource().contains("ui-messages-error-summary")) {

				List<WebElement> errMsgList;

				errMsgList = fnpGetORObject_list("ErrorMessage", 1);

				int sizeErrMsgList = errMsgList.size();
				fnpMymsg("  ---error size is---" + sizeErrMsgList);
				int i = 0;

				driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);
				for (Iterator iterator = errMsgList.iterator(); iterator.hasNext();) {

					try {
						WebElement webElement = (WebElement) iterator.next();

						if (webElement.isDisplayed()) {
							fnpCheckError("");
							fnpMymsg("@  ---Error is displayed");

							break;
						}
					} catch (org.openqa.selenium.StaleElementReferenceException s) {
						// nothing to do
					}

					i = i + 1;
				}

			}
		} catch (org.openqa.selenium.NoSuchWindowException N) {
			// nothing to do
			// Catching for this special error becuase in Oasis when window
			// close then its pagesource is also not available.
		}

		finally {
			driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")),
					TimeUnit.SECONDS);

		}

	}

	/******* Insert value in text box ***/
	public static void fnpType(String ORName, String xpath, String value1) throws Throwable {

		WebElement wb = null;
		String value = value1;
		int iwhileCounter = 0;
		while (true) {
			iwhileCounter++;
			// if (ORName == "OR") {

			if (ORName == null || (ORName.equalsIgnoreCase(""))) {
				// wb=driver.findElement(By.xpath(xpath));
				wb = fnpGetORObjectX___NOR(xpath);
				// wb.sendKeys("");
				try {
					wb.sendKeys("");

					wb = fnpGetORObjectX___NOR(xpath);
					wb.clear();
					fnpMymsg("Values has been cleared.");
					// Thread.sleep(2000);
					fnpMymsg("Going to insert value in '" + xpath + "' text box is--" + value);
					// Thread.sleep(500);
					wb = fnpGetORObjectX___NOR(xpath);
					wb.sendKeys(value);
					fnpMymsg("PASSED == '" + value + "' Type done into the field >> " + xpath);
					// Thread.sleep(1000);
					break;
				} catch (org.openqa.selenium.InvalidElementStateException ie) {
					/****
					 * we are using this fnpType function of a drop down in
					 * Oasis where inserting time, so in those cases clear
					 * will not work..
					 */

					if (iwhileCounter > 5) {
						throw ie;
					}

				} catch (Throwable t) {
					if (iwhileCounter > 5) {
						throw t;
					}
				}

				// wb = fnpGetORObjectX___NOR(xpath);
			}

			if (ORName.equalsIgnoreCase("OR")) {
				wb = fnpGetORObjectX(xpath);
				// wb.sendKeys("");
				try {
					wb.sendKeys("");
					// Thread.sleep(500);
					wb = fnpGetORObjectX(xpath);
					wb.clear();
					fnpMymsg("Values has been cleared.");

					fnpMymsg("Going to insert value in '" + xpath + "' text box is--" + value1);
					// Thread.sleep(500);
					wb = fnpGetORObjectX(xpath);

					/*
					 * try{
					 * int
					 * maxPermittedLength=Integer.parseInt(wb.getAttribute("maxlength").toString());
					 * value=(value1.substring(0, (maxPermittedLength-1))).trim();
					 * fnpMymsg("As this text box has some maxLength, so we can insert the value in'"
					 * + xpath + "' text box is--" + value);
					 * }catch(Throwable t){
					 * 
					 * }
					 */

					wb.sendKeys(value);
					fnpMymsg("PASSED == '" + value + "' Type done into the field >> " + xpath);
					// Thread.sleep(1000);
					// wb.sendKeys(value,Keys.ARROW_UP);

					/*
									*//************* insert one character at a time ***************/
					/*
					 * for (int i = 0; i < value.length(); i++){
					 * char c = value.charAt(i);
					 * // String s = new StringBuilder().append(c).toString();//This is also correct
					 * String s = Character.toString(c);//This is a easy way
					 * wb.sendKeys(s);
					 * // wb.sendKeys(s,Keys.ARROW_UP);//type in reverse order
					 * // wb.sendKeys(s,Keys.ARROW_RIGHT);//type in correct order
					 * Thread.sleep(500);
					 * }
					 *//********************************************************//*					
																					*/

					break;
				} catch (org.openqa.selenium.InvalidElementStateException ie) {
					/****
					 * we are using this fnpType function of a drop down in
					 * Oasis where inserting time, so in those cases clear will
					 * not work..
					 */
					if (iwhileCounter > 5) {
						throw ie;
					}

				} catch (Throwable t) {
					if (iwhileCounter > 5) {
						throw t;
					}

				}
				// wb = fnpGetORObjectX(xpath);
			}
		}

		/*
		 * iwhileCounter = 0;
		 * while (true) {
		 * iwhileCounter++;
		 * String insertedText = "";
		 * try{
		 */
		String insertedText = "";
		if (ORName == null || ORName.equalsIgnoreCase("")) {

			insertedText = fnpGetORObjectX___NOR(xpath).getAttribute("value").trim();
			if (insertedText.equalsIgnoreCase(value)) {
				fnpMymsg("@  -NOR--inserted text is matched . Expected is '" + value + "' and actual is--'"
						+ insertedText + "'.");
				// break;
			} else {
				msg = "@  -NOR--inserted text is Not  matched . Expected is '" + value + "' and actual is--'"
						+ insertedText + "'.";
				fnpMymsg(msg);
				// wb.clear();
				throw new Exception(msg);
			}
		}
		if (ORName.equalsIgnoreCase("OR")) {

			insertedText = fnpGetAttribute_OR(xpath, "value");
			if (insertedText.equalsIgnoreCase(value)) {
				fnpMymsg("@  -OR--inserted text is matched . Expected is '" + value + "' and actual is--'"
						+ insertedText + "'.");
				// break;
			} else {
				msg = "@  -OR--inserted text is NOT matched . Expected is '" + value + "' and actual is--'"
						+ insertedText + "'.";
				fnpMymsg(msg);
				throw new Exception(msg);

			}

		}

		/*
		 * }catch(Throwable t){
		 * if (iwhileCounter > 5) {
		 * msg="@ - after trying 5 times in inserting text by function fnpType, now exiting the while loop and error is --"
		 * +t.getMessage();
		 * fnpMymsg(msg);
		 * //break;
		 * 
		 * throw new Exception(msg);
		 * 
		 * 
		 * }
		 * }
		 * 
		 * 
		 * }
		 */

	}

	/******* Insert value in text box ***/
	public static void fnpType2_afterClearingTextGivingMuchTime(String ORName, String xpath, String value1)
			throws Throwable {

		WebElement wb = null;
		String value = value1;
		int iwhileCounter = 0;
		while (true) {
			iwhileCounter++;
			// if (ORName == "OR") {

			if (ORName == null || (ORName.equalsIgnoreCase(""))) {
				// wb=driver.findElement(By.xpath(xpath));
				wb = fnpGetORObjectX___NOR(xpath);
				// wb.sendKeys("");
				try {
					wb.sendKeys("");

					wb = fnpGetORObjectX___NOR(xpath);
					wb.clear();
					fnpMymsg("Values has been cleared.");
					Thread.sleep(5000);
					fnpMymsg("Going to insert value in '" + xpath + "' text box is--" + value);
					// Thread.sleep(500);
					wb = fnpGetORObjectX___NOR(xpath);
					wb.sendKeys(value);
					Thread.sleep(5000);
					break;
				} catch (org.openqa.selenium.InvalidElementStateException ie) {
					/****
					 * we are using this fnpType function of a drop down in
					 * Oasis where inserting time, so in those cases clear
					 * will not work..
					 */

					if (iwhileCounter > 5) {
						throw ie;
					}

				} catch (Throwable t) {
					if (iwhileCounter > 5) {
						throw t;
					}
				}

				// wb = fnpGetORObjectX___NOR(xpath);
			}

			if (ORName.equalsIgnoreCase("OR")) {
				wb = fnpGetORObjectX(xpath);
				// wb.sendKeys("");
				try {
					wb.sendKeys("");
					// Thread.sleep(500);
					wb = fnpGetORObjectX(xpath);
					wb.clear();
					fnpMymsg("Values has been cleared.");
					Thread.sleep(5000);

					fnpMymsg("Going to insert value in '" + xpath + "' text box is--" + value1);
					// Thread.sleep(500);
					wb = fnpGetORObjectX(xpath);
					wb.sendKeys(value);
					Thread.sleep(5000);

					break;
				} catch (org.openqa.selenium.InvalidElementStateException ie) {
					/****
					 * we are using this fnpType function of a drop down in
					 * Oasis where inserting time, so in those cases clear will
					 * not work..
					 */
					if (iwhileCounter > 5) {
						throw ie;
					}

				} catch (Throwable t) {
					if (iwhileCounter > 5) {
						throw t;
					}

				}
				// wb = fnpGetORObjectX(xpath);
			}
		}

		String insertedText = "";
		if (ORName == null || ORName.equalsIgnoreCase("")) {

			insertedText = fnpGetORObjectX___NOR(xpath).getAttribute("value").trim();
			if (insertedText.equalsIgnoreCase(value)) {
				fnpMymsg("@  -NOR--inserted text is matched . Expected is '" + value + "' and actual is--'"
						+ insertedText + "'.");
				// break;
			} else {
				msg = "@  -NOR--inserted text is Not  matched . Expected is '" + value + "' and actual is--'"
						+ insertedText + "'.";
				fnpMymsg(msg);
				// wb.clear();
				throw new Exception(msg);
			}
		}
		if (ORName.equalsIgnoreCase("OR")) {

			insertedText = fnpGetAttribute_OR(xpath, "value");
			if (insertedText.equalsIgnoreCase(value)) {
				fnpMymsg("@  -OR--inserted text is matched . Expected is '" + value + "' and actual is--'"
						+ insertedText + "'.");
				// break;
			} else {
				msg = "@  -OR--inserted text is NOT matched . Expected is '" + value + "' and actual is--'"
						+ insertedText + "'.";
				fnpMymsg(msg);
				throw new Exception(msg);

			}

		}

		/*
		 * }catch(Throwable t){
		 * if (iwhileCounter > 5) {
		 * msg="@ - after trying 5 times in inserting text by function fnpType, now exiting the while loop and error is --"
		 * +t.getMessage();
		 * fnpMymsg(msg);
		 * //break;
		 * 
		 * throw new Exception(msg);
		 * 
		 * 
		 * }
		 * }
		 * 
		 * 
		 * }
		 */

	}

	/******* Insert value in text box in Oasis ***/
	public static void fnpTypeTimeInOasis(String ORName, String xpath, String value) throws Throwable {
		WebElement wb = null;
		/*
		 * if (ORName.equalsIgnoreCase( "OR")) {
		 * 
		 * try {
		 * wb = fnpGetORObjectX(xpath);
		 * 
		 * } catch (org.openqa.selenium.InvalidElementStateException ie) {
		 *//****
			 * we are using this fnpType function of a drop down in Oasis
			 * where inserting time, so in those cases clear will not work..
			 */
		/*
		 * Thread.sleep(2000);
		 * wb = fnpGetORObjectX(xpath);
		 * 
		 * } catch (Throwable t) {
		 * Thread.sleep(2000);
		 * wb = fnpGetORObjectX(xpath);
		 * }
		 * 
		 * } else {
		 * 
		 * if ( ORName == null || (ORName.equalsIgnoreCase( "")) ) {
		 * 
		 * try {
		 * wb = fnpGetORObjectX___NOR(xpath);
		 * } catch (org.openqa.selenium.InvalidElementStateException ie) {
		 *//****
			 * we are using this fnpType function of a drop down in
			 * Oasis where inserting time, so in those cases clear will
			 * not work..
			 *//*
				 * Thread.sleep(2000);
				 * wb = fnpGetORObjectX___NOR(xpath);
				 * } catch (Throwable t) {
				 * Thread.sleep(2000);
				 * wb = fnpGetORObjectX___NOR(xpath);
				 * }
				 * 
				 * } else {
				 * // for later use for new OR
				 * }
				 * }
				 * 
				 */

		if (ORName == null || (ORName.equalsIgnoreCase(""))) {

			try {
				wb = fnpGetORObjectX___NOR(xpath);
			} catch (org.openqa.selenium.InvalidElementStateException ie) {
				/****
				 * we are using this fnpType function of a drop down in
				 * Oasis where inserting time, so in those cases clear will
				 * not work..
				 */
				Thread.sleep(2000);
				wb = fnpGetORObjectX___NOR(xpath);
			} catch (Throwable t) {
				Thread.sleep(2000);
				wb = fnpGetORObjectX___NOR(xpath);
			}

		} else {
			if (ORName.equalsIgnoreCase("OR")) {

				try {
					wb = fnpGetORObjectX(xpath);

				} catch (org.openqa.selenium.InvalidElementStateException ie) {
					/****
					 * we are using this fnpType function of a drop down in Oasis
					 * where inserting time, so in those cases clear will not work..
					 */
					Thread.sleep(2000);
					wb = fnpGetORObjectX(xpath);

				} catch (Throwable t) {
					Thread.sleep(2000);
					wb = fnpGetORObjectX(xpath);
				}

			}
		}

		fnpMymsg("Going to insert value in '" + xpath + "' text box is--" + value);

		// wb.sendKeys(value);
		if (wb != null) {
			wb.sendKeys(value);
		}
		Thread.sleep(100);

	}

	// }

	/******* waiting for expected error message ***/
	public static void fnpWaitingForExpectedErrorMsg(String ExpectedErrorMsg) throws Throwable {

		/*********** Waiting for expected error message ****/
		int whileI = 0;
		while (!fnpCheckElementPresenceImmediately("ErrorMessage")) {
			if (whileI < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
				whileI = whileI + 1;
				Thread.sleep(1000);
				fnpMymsg("Waiting for expected error message --" + whileI);

			} else {
				fnpDesireScreenshot("MissingExpectedErrorMessage");
				throw new Exception("   Missing Expected Error Message '" + ExpectedErrorMsg + "' after '"
						+ Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))
						+ "' ,plz see screenshot [MissingExpectedErrorMessage]");
			}
		}
		/*********** Waiting for expected error message ****/
	}

	public static void fnpWaitingForElementWithoutUsingLoading(String xpathKey) throws Throwable {

		int whileI = 0;
		while (!fnpCheckElementPresenceImmediately(xpathKey)) {
			if (whileI < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
				whileI = whileI + 1;
				Thread.sleep(1000);
				fnpMymsg(" Waiting for expected object --'" + xpathKey + "' ---" + whileI);

			} else {
				fnpDesireScreenshot("MissingObject " + xpathKey);
				throw new Exception("   Missing Expected object '" + xpathKey + "' after '"
						+ Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))
						+ "' ,plz see screenshot [MissingObject " + xpathKey + "]");
			}
		}

	}

	public static void fnpManuallyRefresh() throws Throwable {
		/*
		 * WebElement wbElement =
		 * driver.findElement(By.xpath(OR.getProperty("FooterElement"))); new
		 * Actions(driver).moveToElement(wbElement).perform();
		 * driver.findElement
		 * (By.xpath(OR.getProperty("FooterElement"))).click();
		 * driver.navigate().refresh();
		 */
		Thread.sleep(5000);
	}

	public static boolean fnpIfORElementDisplayed(String xpathKey, int timeInSeconds) {

		try {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(timeInSeconds, TimeUnit.SECONDS)
					.pollingEvery(2, TimeUnit.SECONDS)
					// .ignoring(NoSuchElementException.class);
					.ignoring(org.openqa.selenium.NoSuchElementException.class)
					.ignoring(org.openqa.selenium.InvalidSelectorException.class)
					.ignoring(org.openqa.selenium.StaleElementReferenceException.class)
					.ignoring(org.openqa.selenium.WebDriverException.class);

			if (OR.getProperty(xpathKey).contains("~")) {

				String[] s1 = OR.getProperty(xpathKey).split("~");
				String locatorValue = s1[1];
				String locator = s1[0];
				if (locator.contains("id")) {// id
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
					// wait.until(ExpectedConditions.elementToBeClickable(By.id(locatorValue)));
				} else {
					if (locator.contains("name")) {// name
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue)));
						// wait.until(ExpectedConditions.elementToBeClickable(By.name(locatorValue)));

					} else {
						if (locator.toLowerCase().contains("linktext")) {// linkText
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locatorValue)));
							// wait.until(ExpectedConditions.elementToBeClickable(By.linkText(locatorValue)));

						} else {
							if (locator.toLowerCase().contains("partialLinkText")) {// partialLinkText
								wait.until(ExpectedConditions
										.visibilityOfElementLocated(By.partialLinkText(locatorValue)));
								// wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(locatorValue)));

							} else {
								if (locator.toLowerCase().contains("tagName")) {// tagName
									wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(locatorValue)));
									// wait.until(ExpectedConditions.elementToBeClickable(By.tagName(locatorValue)));

								} else {
									if (locator.toLowerCase().contains("className")) {// className
										wait.until(ExpectedConditions
												.visibilityOfElementLocated(By.className(locatorValue)));
										// wait.until(ExpectedConditions.elementToBeClickable(By.className(locatorValue)));

									} else {
										if (locator.toLowerCase().contains("css")) {// cssSelector
											wait.until(ExpectedConditions
													.visibilityOfElementLocated(By.cssSelector(locatorValue)));
											// wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(locatorValue)));

										} else {

											if (locator.toLowerCase().contains("xpath")) {// xpath
												wait.until(ExpectedConditions
														.visibilityOfElementLocated(By.xpath(locatorValue)));
												// wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorValue)));

											} else {

												// throw new Exception
												// ("Object with name '"+XpathKey+"' is not present in OR");
												/******
												 * By default Xpath will be
												 * assumed
												 *****/
												wait.until(ExpectedConditions
														.visibilityOfElementLocated(By.xpath(locatorValue)));
												// wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorValue)));

											}
										}
									}
								}
							}
						}
					}
				}

			} else {

				WebElement foo = wait
						.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(xpathKey))));
			}

			return true;
		} catch (Throwable t) {

			return false;
		}
	}

	/********** Common code for Oasis *******/
	public static void fnpCommonOasisCode(String taskName) throws Throwable {

		String originalHandle = driver.getWindowHandle();

		fnpMymsg(
				" Now going to Evaluate the info  by going to the OASIS system by clicking 'PerformAudit' icon  and filling forms.");
		fnpWaitForVisible("TaskTab_PerformAuditIcon");
		fnpWaitTillVisiblityOfElementNClickable_OR("TaskTab_PerformAuditIcon");
		ArrayList<String> listoldtabs2 = new ArrayList<String>(driver.getWindowHandles());
		fnpGetORObjectX("TaskTab_PerformAuditIcon").click();

		Thread.sleep(20000);

		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());

		int oldtabscount2 = listoldtabs2.size();
		int newTotal = tabs.size();
		int ii = 0;
		while (newTotal != (oldtabscount2 + 1)) {
			Thread.sleep(2000);
			tabs = new ArrayList<String>(driver.getWindowHandles());
			newTotal = tabs.size();
			ii = ii + 1;
			if (ii > Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
				break;
			}

		}

		int tabsCount = tabs.size();
		for (int i = 0; i < tabsCount; i++) {

			fnpMymsg((i + 1) + "  window handle is:" + tabs.get(i));

			if (originalHandle.equalsIgnoreCase(tabs.get(i))) {
				// nothing to do

			} else {

				driver.switchTo().window(tabs.get(i));
				// Thread.sleep(5000);
				Thread.sleep(1000);
				break;
			}

		}
		Thread.sleep(1000);
		fnpWaitForVisible("CompleteAuditFom");// It throws timeout exception b/c
												// button not
		// visible as in oasis it is on same page and little fraction of time
		// page load something like that

		fnpCheckErrorUsingPageSource_Oasis();

		// new code as on 20-6-16 due to very slow, it failed due to error
		// message --Unable to get browser exception....
		int iCounter = 0;

		while (iCounter < (Integer.parseInt(CONFIG.getProperty("genMax_waitTime")) * 5)) {
			iCounter++;
			try {
				fnpWaitTillVisiblityOfElementNClickable_OR("CompleteAuditFom");
				break;
			} catch (Throwable t) {
				if ((t.getMessage().contains("Unable to get browser"))) {
					// nothing to do
					Thread.sleep(1000);
					fnpMymsg("  ---Unable to get browser exception is coming ---" + iCounter);
				} else {
					fnpMymsg("  ---Unable to get browser exception is NOT coming ---" + iCounter);
					throw new Exception(t);
				}

			}
		}

		fnpGetORObjectX("CompleteAuditFom").click();
		fnpMymsg("Clicked 'Complete Audit' button");
		fnpCheckErrorUsingPageSource_Oasis();
		fnpMymsg("After checking page source error---no error");

		Thread.sleep(5000);

		String SecondHandle = driver.getWindowHandle();

		if (taskName.equalsIgnoreCase("ModBrack_liteEval")) {

			Thread.sleep(1000);
			fnpWaitForVisible("LiteratureEvalNo");// It throws timeout exception
													// b/c button not
			// visible as in oasis it is on same page and little fraction of
			// time page load something like that
			fnpWaitTillVisiblityOfElementNClickable_OR("LiteratureEvalNo");
			fnpCheckErrorUsingPageSource_Oasis();
			fnpGetORObjectX("LiteratureEvalNo").click();
			fnpCheckErrorUsingPageSource_Oasis();
			Thread.sleep(2000);
			fnpWaitForVisible("QuestionNotesTxtBox");// It throws timeout
														// exception b/c button
														// not
			// visible as in oasis it is on same page and little fraction of
			// time page load something like that
			fnpGetORObjectX("QuestionNotesTxtBox").clear();

			fnpType("OR", "QuestionNotesTxtBox", (String) hashXlData.get("Question_Notes"));

			fnpGetORObjectX("SectionNotesTxtBox").clear();

			fnpType("OR", "SectionNotesTxtBox", (String) hashXlData.get("Section_Notes"));

			java.util.List<WebElement> oasisSaveNextBtn = driver
					.findElements(By.xpath(OR.getProperty("Oasis_SaveNNextBtn")));
			oasisSaveNextBtn.get(0).click();
			Thread.sleep(2000);
			fnpCheckErrorUsingPageSource_Oasis();
			fnpType("OR", "SectionNotesTxtBox", (String) hashXlData.get("Section_Notes"));
			oasisSaveNextBtn = driver.findElements(By.xpath(OR.getProperty("Oasis_SaveNNextBtn")));
			oasisSaveNextBtn.get(0).click();
			Thread.sleep(2000);
			fnpCheckErrorUsingPageSource_Oasis();

			fnpClick_OR_WithoutWait("Oasis_NoFindingRadioBtn");
			Thread.sleep(200);

			fnpCheckErrorUsingPageSource_Oasis();
			oasisSaveNextBtn = driver.findElements(By.xpath(OR.getProperty("Oasis_SaveNNextBtn")));
			oasisSaveNextBtn.get(0).click();
			Thread.sleep(2000);
			fnpCheckErrorUsingPageSource_Oasis();

			fnpClick_OR_WithoutWait("Oasis_NoFindingRadioBtn");
			Thread.sleep(200);

			List<WebElement> oasisSaveNClosseBtn = driver.findElements(By.xpath(OR.getProperty("Oasis_SaveNCloseBtn")));
			oasisSaveNClosseBtn.get(0).click();
			Thread.sleep(2000);
			fnpCheckErrorUsingPageSource_Oasis();

		}

		if (taskName.equalsIgnoreCase("ModBrack_phyEval")) {

			fnpClick_OR_WithoutWait("PhysicalEvalNo");

			// Thread.sleep(4000);
			Thread.sleep(10000);// sometime screen refresh specially failed here
								// in 28 new machine
			fnpCheckErrorUsingPageSource_Oasis();

			try {// sometime screen refresh
				fnpWaitForVisible("INFO1_answerTxtBox");
			} catch (Throwable t) {
				// sometime screen refresh
			}

			fnpType("OR", "INFO1_answerTxtBox", (String) hashXlData.get("PhyEval_INFO1_answer"));

			fnpType("OR", "QuestionNotesTxtBox", (String) hashXlData.get("PhyEval_Question_Notes"));

			fnpType("OR", "PhyEval_SectionNotesTxtBox", (String) hashXlData.get("PhyEval_Section_Notes"));

			java.util.List<WebElement> oasisSaveNNextBtn = driver
					.findElements(By.xpath(OR.getProperty("Oasis_SaveNNextBtn")));
			oasisSaveNNextBtn.get(0).click();
			Thread.sleep(3000);
			fnpCheckErrorUsingPageSource_Oasis();

			fnpGetORObjectX("INFO1_answerTxtBox").clear();
			fnpType("OR", "INFO1_answerTxtBox", (String) hashXlData.get("PhyEval_INFO1_answer"));

			fnpGetORObjectX("QuestionNotesTxtBox").clear();
			fnpType("OR", "QuestionNotesTxtBox", (String) hashXlData.get("PhyEval_Question_Notes"));

			fnpGetORObjectX("PhyEval_SectionNotesTxtBox").clear();
			fnpType("OR", "PhyEval_SectionNotesTxtBox", (String) hashXlData.get("PhyEval_Section_Notes"));

			oasisSaveNNextBtn = driver.findElements(By.xpath(OR.getProperty("Oasis_SaveNNextBtn")));
			oasisSaveNNextBtn.get(0).click();
			Thread.sleep(3000);
			fnpCheckErrorUsingPageSource_Oasis();

			fnpClick_OR_WithoutWait("Oasis_FindingRadioBtn");
			Thread.sleep(200);
			fnpGetORObjectX("QuestionNotesTxtBox").clear();
			fnpType("OR", "QuestionNotesTxtBox", (String) hashXlData.get("PhyEval_Question_Notes"));

			fnpGetORObjectX("PhyEval_SectionNotesTxtBox").clear();
			fnpType("OR", "PhyEval_SectionNotesTxtBox", (String) hashXlData.get("PhyEval_Section_Notes"));

			oasisSaveNNextBtn = driver.findElements(By.xpath(OR.getProperty("Oasis_SaveNNextBtn")));
			oasisSaveNNextBtn.get(0).click();
			Thread.sleep(3000);
			fnpCheckErrorUsingPageSource_Oasis();

			fnpClick_OR_WithoutWait("Oasis_FindingRadioBtn");
			Thread.sleep(200);
			fnpGetORObjectX("QuestionNotesTxtBox").clear();
			fnpType("OR", "QuestionNotesTxtBox", (String) hashXlData.get("PhyEval_Question_Notes"));

			fnpGetORObjectX("PhyEval_SectionNotesTxtBox").clear();
			fnpType("OR", "PhyEval_SectionNotesTxtBox", (String) hashXlData.get("PhyEval_Section_Notes"));

			oasisSaveNNextBtn = driver.findElements(By.xpath(OR.getProperty("Oasis_SaveNNextBtn")));
			oasisSaveNNextBtn.get(0).click();
			Thread.sleep(3000);
			fnpCheckErrorUsingPageSource_Oasis();

			fnpClick_OR_WithoutWait("Oasis_NotApplicableRadioBtn");
			Thread.sleep(200);
			fnpGetORObjectX("QuestionNotesTxtBox").clear();
			fnpType("OR", "QuestionNotesTxtBox", (String) hashXlData.get("PhyEval_Question_Notes"));

			fnpGetORObjectX("PhyEval_SectionNotesTxtBox").clear();
			fnpType("OR", "PhyEval_SectionNotesTxtBox", (String) hashXlData.get("PhyEval_Section_Notes"));

			oasisSaveNNextBtn = driver.findElements(By.xpath(OR.getProperty("Oasis_SaveNNextBtn")));
			oasisSaveNNextBtn.get(0).click();
			Thread.sleep(3000);
			fnpCheckErrorUsingPageSource_Oasis();

			fnpClick_OR_WithoutWait("Oasis_NotApplicableRadioBtn");
			Thread.sleep(200);
			fnpGetORObjectX("QuestionNotesTxtBox").clear();
			fnpType("OR", "QuestionNotesTxtBox", (String) hashXlData.get("PhyEval_Question_Notes"));

			fnpGetORObjectX("PhyEval_SectionNotesTxtBox").clear();
			fnpType("OR", "PhyEval_SectionNotesTxtBox", (String) hashXlData.get("PhyEval_Section_Notes"));

			oasisSaveNNextBtn = driver.findElements(By.xpath(OR.getProperty("Oasis_SaveNNextBtn")));
			oasisSaveNNextBtn.get(0).click();
			Thread.sleep(3000);
			fnpCheckErrorUsingPageSource_Oasis();

		}

		Thread.sleep(1000);
		fnpWaitForVisible("Oasis_SubmitBtn");// It throws timeout exception b/c
												// button not
		// visible as in oasis it is on same page and little fraction of time
		// page load something like that
		fnpWaitTillVisiblityOfElementNClickable_OR("Oasis_SubmitBtn");

		ArrayList<String> listoldtabs3 = new ArrayList<String>(driver.getWindowHandles());
		fnpGetORObjectX("Oasis_SubmitBtn").click();
		fnpCheckErrorUsingPageSource_Oasis();

		tabs = new ArrayList<String>(driver.getWindowHandles());

		int oldtabscount3 = listoldtabs3.size();
		newTotal = tabs.size();
		ii = 0;
		while (newTotal != (oldtabscount3 + 1)) {
			Thread.sleep(1000);
			tabs = new ArrayList<String>(driver.getWindowHandles());
			newTotal = tabs.size();
			ii = ii + 1;
			if (ii > Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
				break;
			}
		}

		Thread.sleep(5000);
		fnpCheckErrorUsingPageSource_Oasis();

		// fnpMymsg("we are in Literature Eval");
		for (int i = 0; i < tabs.size(); i++) {

			fnpMymsg("This is Window Id - " + i + "--->" + tabs.get(i));
			if ((tabs.get(i).equalsIgnoreCase(originalHandle)) || (tabs.get(i).equalsIgnoreCase(SecondHandle))) {

			} else {
				driver.switchTo().window(tabs.get(i));
				Thread.sleep(1000);
				break;

			}

		}
		Thread.sleep(1000);
		fnpCheckErrorUsingPageSource_Oasis();
		fnpWaitForVisible("Oasis_VisitStartTimeHour");// It throws timeout
														// exception b/c button
														// not
		// visible as in oasis it is on same page and little fraction of time
		// page load something like that

		fnpTypeTimeInOasis("OR", "Oasis_VisitStartTimeHour", (String) hashXlData.get("Oasis_VisitStartTimeHour"));

		fnpTypeTimeInOasis("OR", "Oasis_VisitStartMinute", (String) hashXlData.get("Oasis_VisitStartMinute"));

		fnpTypeTimeInOasis("OR", "Oasis_VisitEndTimeHour", (String) hashXlData.get("Oasis_VisitEndTimeHour"));

		fnpTypeTimeInOasis("OR", "Oasis_VisitEndMinute", (String) hashXlData.get("Oasis_VisitEndMinute"));

		fnpGetORObjectX("Oasis_UpdateVisitTime_SaveBtn").click();

		// Thread.sleep(2000);

		driver.switchTo().window(SecondHandle);
		fnpCheckErrorUsingPageSource_Oasis();
		// fnpWaitForVisible("Oasis_VisitSuccessfullySubmittedMsg");
		fnpWaitForVisible("Oasis_VisitSuccessfullySubmittedMsg", 360);
		fnpGetORObjectX("Oasis_ExitBtn").click();
		fnpCheckErrorUsingPageSource_Oasis();

		driver.close();
		// driver.quit();

		driver.switchTo().window(originalHandle);

		fnpCommonCodeAfterComingBackFromOasis(taskName);

	}

	public static void fnpCommonCodeAfterComingBackFromOasis(String taskName) throws Throwable {

		fnpMymsg(" Now we have coming back from Oasis and now going to click 'Refresh Data' button in iPulse");

		if (taskName.equalsIgnoreCase("ModBrack_liteEval")) {
			fnpClick_OR("LitEvalRefreshDataBtn");
			fnpMymsg(" clicked 'Refresh Data' button.");

			// through golden procedure sometime refresh button does not work...
			// it is for timebeing...remove it later 11-04-2017
			String usingGoldenProcedureOrOasis = (String) hashXlData.get("Audit_usingGoldenProcedureOrOasis");
			if (usingGoldenProcedureOrOasis.equalsIgnoreCase("golden")) {
				driver.navigate().refresh();
			}

			fnpWaitForVisible("LiteEval_EvaluationInfoTable_header");
			int EvalInfoStatusColNo = fnpFindColumnIndex("LiteEval_EvaluationInfoTable_header", "Status");
			String EvalInfoStatusValue = fnpFetchFromTable("LiteEval_EvaluationInfoTable", 1, EvalInfoStatusColNo);

			Assert.assertTrue(EvalInfoStatusValue.toLowerCase().contains("submitted"),
					"In Evaluation Info Table , Evaluation info's  status has not become  to 'SUBMITTED' . It is  showing '"
							+ EvalInfoStatusValue + "'.");

			fnpMymsg("  Evaluation info's  status has become  to 'SUBMITTED'  successfully.");

			fnpClick_OR("TaskTab_ScopeValidation_CompleteTaskIcon");

			fnpClickInDialog_OR("TaskTab_ScopeValidation_CompleteTaskSaveBtn");

			fnpCheckError("in Literature_Eval Task  method while completing the task");

			/******
			 * 28-04-15 commented for time being ****Again today 15-06-15
			 * uncommenting b/c it need to click to see findings
			 ***/
			// fnpClick_OR("LitEvalRefreshDataBtn");
			/****** 28-04-15 commented for time being *****/

			if (usingGoldenProcedureOrOasis.equalsIgnoreCase("golden")) {
				driver.navigate().refresh();
			} else {
				fnpClick_OR("LitEvalRefreshDataBtn");
			}

			fnpWaitForVisible("LiteEval_FindingsTable");

			List<WebElement> AssignFindingIcons = fnpGetORObject_list("FindingsTable_AssignFindingIcon",
					Integer.parseInt(CONFIG.getProperty("implicitWaitTime")));

			int countAssignFindingIcons = AssignFindingIcons.size();

			String[] findingStatus = ((String) hashXlData.get("LitFinding_ChangeStatus")).split(",");
			int expectedFindingCount = findingStatus.length;

			Assert.assertTrue(countAssignFindingIcons == expectedFindingCount, expectedFindingCount
					+ " Findings are not getting generated as visible AssignFinding icons count are '"
					+ countAssignFindingIcons + "' .");
			fnpMymsg("Findings are present or visible and their count is '" + countAssignFindingIcons + "'");

			int FindingStausColIndex = fnpFindColumnIndex("LiteEval_FindingsTable_header", "Status");
			int CountTotalRows = fnpCountRowsInTable("LiteEval_FindingsTable");

			for (int i = 1; i < CountTotalRows + 1; i++) {

				String status = fnpFetchFromTable("LiteEval_FindingsTable", i, FindingStausColIndex);

				Assert.assertTrue(status.equalsIgnoreCase("pending"),
						"Status of findings  are not in 'PENDING' status in row '" + i + "' .");
			}

			fnpMymsg("All findings are in 'PENDING' status");

			int TaskSummaryTable_StausColIndex = fnpFindColumnIndex("LiteEval_TaskSummaryTable_header", "Status");
			String LitEvalTask_status = fnpFetchFromTable("LiteEval_TaskSummaryTable", 1,
					TaskSummaryTable_StausColIndex);

			Assert.assertTrue(LitEvalTask_status.equalsIgnoreCase("completed"),
					" status for the Literature Eval task should be completed but it is coming  '" + LitEvalTask_status
							+ "' ");
			fnpMymsg(" Status for the Literature Eval task has become  to 'COMPLETED'  successfully.");

			driver.navigate().refresh();
			// Thread.sleep(4000);

			Thread.sleep(2000);

			fnpWaitForVisible("LiteEval_ActionItemsTab_ActionItemTable");
			int LEval_AItemsTab_AItemTable_StatusColIndex = fnpFindColumnIndex(
					"LiteEval_ActionItemsTab_ActionItemTable_header", "Status");

			String LiteEval_ActionItemsTab_ActionItemTable_status = fnpFetchFromTable(
					"LiteEval_ActionItemsTab_ActionItemTable", 1, LEval_AItemsTab_AItemTable_StatusColIndex);

			Assert.assertTrue(LiteEval_ActionItemsTab_ActionItemTable_status.equalsIgnoreCase("completed"),
					" Action item for Literature eval should also get changed from SUBMITTED to COMPLETED but it is coming  '"
							+ LiteEval_ActionItemsTab_ActionItemTable_status
							+ "' ");
			fnpMymsg(" Action item for Literature eval task has become  to 'COMPLETED'  successfully.");

			fnpCheckError(" while Going to Complete the Literature Eval Task");
			fnpMymsg("  Completed the Literature Eval Task successfully ");

		}

		if (taskName.equalsIgnoreCase("ModBrack_phyEval")) {

			fnpClick_OR("PhyEvalRefreshDataBtn");
			fnpMymsg(" clicked 'Refresh Data' button.");

			// through golden procedure sometime refresh button does not work...
			// it is for timebeing...remove it later 11-04-2017
			String usingGoldenProcedureOrOasis = (String) hashXlData.get("Audit_usingGoldenProcedureOrOasis");
			if (usingGoldenProcedureOrOasis.equalsIgnoreCase("golden")) {
				driver.navigate().refresh();
			}

			fnpWaitForVisible("PhyEval_EvaluationInfoTable_header");
			int EvalInfoStatusColNo = fnpFindColumnIndex("PhyEval_EvaluationInfoTable_header", "Status");
			String EvalInfoStatusValue = fnpFetchFromTable("PhyEval_EvaluationInfoTable", 1, EvalInfoStatusColNo);

			Assert.assertTrue(EvalInfoStatusValue.toLowerCase().contains("submitted"),
					"In Evaluation Info Table , Evaluation info's  status has not become  to 'SUBMITTED' . It is  showing '"
							+ EvalInfoStatusValue + "'.");

			fnpMymsg("  Evaluation info's  status has become  to 'SUBMITTED'  successfully.");

			/****
			 * To run further in chorme to make it fast to complete the flow as
			 * it is very slow in IE
			 ******/
			/*
			 * if ((fnpGetCurrRunningBrowserName().equalsIgnoreCase("IE"))) {
			 * fnpLaunchBrowserAndLogin(); fnpSearchWorkOrderOnly(workOrderNo);
			 * } else { // nothing to do }
			 */
			// String usingGoldenProcedureOrOasis = (String)
			// hashXlData.get("Audit_usingGoldenProcedureOrOasis");
			if (usingGoldenProcedureOrOasis.equalsIgnoreCase("golden")) {
				// fnpClick_OR("TaskTab_ScopeValidation_BackToViewBtn");
				fnpClick_OR("TaskTab_ScopeValidation_BackBtn");

			} else {
				if ((fnpGetCurrRunningBrowserName().equalsIgnoreCase("IE"))) {
					fnpLaunchBrowserAndLogin();
					fnpSearchWorkOrderOnly(workOrderNo);
				} else {
					// nothing to do
				}
			}

			int taskDescColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow",
					SnapShot_taskTable_TaskDescColName);
			int phyEvalTaskRowNo = fnpFindRow("Snapshot_TasksSummaryTable", SnapShot_taskDesc_PhysicalEval,
					taskDescColIndex);

			int taskColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", SnapShot_taskTable_TaskColName);
			String phyEvalTaskNo = fnpFetchFromTable("Snapshot_TasksSummaryTable", phyEvalTaskRowNo, taskColIndex);

			Thread.sleep(500);

			fnpClickALinkInATable(phyEvalTaskNo);

			fnpLoading_wait();
			fnpMymsg("Clicked on  Phy Eval Task no. in Snapshot task Table i.e. '" + phyEvalTaskNo + "' .");

			/*
			 * if
			 * (fnpCheckElementPresenceImmediately("TaskTab_ScopeValidation_PerformTaskIcon"
			 * )) {
			 * fnpGetORObjectX("TaskTab_ScopeValidation_PerformTaskIcon").click();
			 * fnpLoading_wait();
			 * }
			 * 
			 */

			fnpClick_OR("TaskTab_ScopeValidation_PerformTaskIcon");

			fnpClick_OR("PhyEval_CheckIcon");

			fnpClickInDialog_OR("PhyEval_CompleteTaskSaveBtn");
			Thread.sleep(1000);

			fnpCheckError(" in ModBrack_phyEval   method while completing the task");

			int TaskSummaryTable_StausColIndex = fnpFindColumnIndex("LiteEval_TaskSummaryTable_header", "Status");
			String PhyEvalTask_status = fnpFetchFromTable("LiteEval_TaskSummaryTable", 1,
					TaskSummaryTable_StausColIndex);

			Assert.assertTrue(PhyEvalTask_status.equalsIgnoreCase("ready"),
					" status for the Physical Eval task should be in Ready status but it is coming  '"
							+ PhyEvalTask_status
							+ "' ");
			fnpMymsg(" Status for the Physical Eval task has become  to 'READY'  successfully.");

			fnpGetORObjectX("TaskTab_ScopeValidation_AssignTaskIcon").click();

			fnpClickInDialog_OR("TaskTab_ScopeValidation_AssignTask_SaveBtn");

			fnpCheckErrMsg("Error is thrown by application after clicking AssignTask_SaveBtn");
			fnpClick_OR("TaskTab_ScopeValidation_PerformTaskIcon");

			// fnpLoading_wait();
			fnpCheckErrMsg("Error is thrown by application after clicking PerformTaskIcon");
			TaskSummaryTable_StausColIndex = fnpFindColumnIndex("LiteEval_TaskSummaryTable_header", "Status");
			PhyEvalTask_status = fnpFetchFromTable("LiteEval_TaskSummaryTable", 1, TaskSummaryTable_StausColIndex);

			Assert.assertTrue(PhyEvalTask_status.equalsIgnoreCase("inperform"),
					" status for the Physical Eval task should be in INPERFORM status but it is coming  '"
							+ PhyEvalTask_status + "' ");
			fnpMymsg(" Status for the Physical Eval task has become  to 'INPERFORM'  successfully.");

			fnpWaitForVisible("PhyEval_ReviewInfoTable_SaveBtn");
			fnpMymsg(" Review Info gets generated successfully as Save button is visible in Review Info table");

			if (fnpCheckElementPresenceImmediately("TaskTab_ScopeValidation_PerformTaskIcon")) {
				fnpClick_OR("TaskTab_ScopeValidation_PerformTaskIcon");
			}

			fnpClick_OR("PhyEval_ReviewInfoTable_CompletePhyEvalBtn");

			fnpClickInDialog_OR("PhyEval_CompleteTaskSaveBtn2");

			fnpCheckError(" in ModBrack_phyEval   method while completing the task");
			// fnpLoading_wait();
			fnpWaitForVisible("LiteEval_TaskSummaryTable_header");

			/****** 28-04-15 commented for time being *****/
			// fnpClick_OR("PhyEvalRefreshDataBtn");
			if (fnpCheckElementPresenceImmediately("PhyEvalRefreshDataBtn")) {
				fnpClick_OR("PhyEvalRefreshDataBtn");
			}
			/****** 28-04-15 commented for time being *****/

			TaskSummaryTable_StausColIndex = fnpFindColumnIndex("LiteEval_TaskSummaryTable_header", "Status");
			PhyEvalTask_status = fnpFetchFromTable("LiteEval_TaskSummaryTable", 1, TaskSummaryTable_StausColIndex);

			Assert.assertTrue(PhyEvalTask_status.equalsIgnoreCase("completed"),
					" status for the Physical Eval task should be COMPLETED status but it is coming  '"
							+ PhyEvalTask_status + "' ");
			fnpMymsg(" Status for the Physical Eval task has become  to 'COMPLETED'  successfully.");

			// fnpClick_OR("PhyEvalRefreshDataBtn");
			// Thread.sleep(2000);

			fnpWaitForVisible("PhyEval_EvaluationInfoTable_header");
			int EvalInfoTable_StausColIndex = fnpFindColumnIndex("PhyEval_EvaluationInfoTable_header", "Status");
			String EvalINfoTable_status = fnpFetchFromTable("PhyEval_EvaluationInfoTable", 1,
					EvalInfoTable_StausColIndex);

			// change in below 2 line
			Assert.assertTrue(EvalINfoTable_status.equalsIgnoreCase("submitted"),
					" status in Evaluation Info table should be submitted status but it is coming  '"
							+ EvalINfoTable_status + "' ");
			fnpMymsg(" status in Evaluation Info table has become  to 'submitted'  successfully.");

			List<WebElement> AssignFindingIcons = fnpGetORObject_list("FindingsTable_AssignFindingIcon",
					Integer.parseInt(CONFIG.getProperty("implicitWaitTime")));

			int countAssignFindingIcons = AssignFindingIcons.size();
			Assert.assertTrue(countAssignFindingIcons > 1,
					"AssignFinding icons are not visible as their count is '" + countAssignFindingIcons + "' .");
			fnpMymsg("Findings are present or visible and their count is '" + countAssignFindingIcons + "'");

			int FindingStausColIndex = fnpFindColumnIndex("PhyEval_FindingsTable_header", "Status");
			int CountTotalRows = fnpCountRowsInTable("PhyEval_FindingsTable");

			for (int i = 1; i < CountTotalRows + 1; i++) {

				String status = fnpFetchFromTable("PhyEval_FindingsTable", i, FindingStausColIndex);

				Assert.assertTrue(status.equalsIgnoreCase("pending"),
						"Status of findings  are not in 'PENDING' status in row '" + i + "' .");
				fnpMymsg("Status of findings  is in 'PENDING' status in row '" + i + "' .");
			}

			fnpMymsg("All findings are in 'PENDING' status");

		}
	}

	public static void fnpCommonProcessLiteEvalFindings() throws Throwable {

		fnpWaitForVisible("LiteEval_FindingsTable");
		fnpMymsg("Now we are going to process findings in Lite Eval task");

		String[] findingStatus = ((String) hashXlData.get("LitFinding_ChangeStatus")).split(",");

		List<WebElement> AssignFindingIcons_LiteEval = fnpGetORObject_list("FindingsTable_AssignFindingIcon",
				Integer.parseInt(CONFIG.getProperty("implicitWaitTime")));

		int countAssignFindingIcons_LiteEval = AssignFindingIcons_LiteEval.size();

		int expectedFindingCount = findingStatus.length;

		Assert.assertTrue(countAssignFindingIcons_LiteEval == expectedFindingCount, expectedFindingCount
				+ " Findings are not getting generated as visible AssignFinding icons count are '"
				+ countAssignFindingIcons_LiteEval + "' .");

		fnpMymsg("Findings are present or visible and their count is '" + countAssignFindingIcons_LiteEval + "'");

		java.util.List<WebElement> EditFindingIcons_LiteEval;

		int FindingStausColIndex = fnpFindColumnIndex("LiteEval_FindingsTable_header", "Status");
		int CountTotalRows = fnpCountRowsInTable("LiteEval_FindingsTable");
		String status;

		String xpathFindingIcon;
		String xpathEditFindingIcon;
		for (int i = 0; i < CountTotalRows; i++) {

			status = fnpFetchFromTable("LiteEval_FindingsTable", i + 1, FindingStausColIndex);

			Assert.assertTrue(status.equalsIgnoreCase("pending"),
					"Status of  finding  is not in 'PENDING' status in row '" + (i + 1) + "' .");
			fnpMymsg("Status of finding is  'PENDING' status in row '" + (i + 1) + "' .");

			xpathFindingIcon = (OR.getProperty("FindingsTable_AssignFindingIconFirstPart")) + i
					+ (OR.getProperty("FindingsTable_AssignFindingIconSecondPart"));

			fnpClick_NOR_withoutWait(xpathFindingIcon);

			fnpMymsg("Clicked 'Assign Finding' in row" + (i + 1) + "' .");

			fnpClickInDialog_OR("AssignFinding_empLookup_btn");

			fnpWaitTillVisiblityOfElementNClickable_OR("LookkupSecondTextBox");
			// fnpSearchNSelectFirstRadioBtn(2, (String)
			// hashXlData.get("AssignFinding_ToUser"), 2);

			/*
			 * String AssignFinding_ToUser;
			 * if (loginAsFullName.equalsIgnoreCase("") | (loginAsFullName==null) ) {
			 * AssignFinding_ToUser=(String) hashXlData.get("AssignFinding_ToUser").trim();p
			 * } else {
			 * AssignFinding_ToUser=loginAsFullName;
			 * }
			 * fnpSearchNSelectFirstRadioBtn(2, AssignFinding_ToUser, 2);
			 */

			String AssignFinding_ToUser;
			if ((loginAsFullName == null) || loginAsFullName.equalsIgnoreCase("")) {
				// reassignee=(String) hashXlData.get("AccountManager").trim();
				if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking"))) {
					AssignFinding_ToUser = (String) hashXlData.get("AssignFinding_ToUser_Code").trim();
					fnpSearchNSelectFirstRadioBtn(1, (String) hashXlData.get("AssignFinding_ToUser_Code"), 1);
				} else {
					AssignFinding_ToUser = (String) hashXlData.get("AssignFinding_ToUser").trim();
					fnpSearchNSelectFirstRadioBtn(2, AssignFinding_ToUser, 2);
				}

			} else {
				// reassignee=loginAsFullName;
				if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking"))) {
					// AssignFinding_ToUser=loginUser_code;
					fnpSearchNSelectFirstRadioBtn(1, loginUser_code, 1);
				} else {
					AssignFinding_ToUser = loginAsFullName;
					fnpSearchNSelectFirstRadioBtn(2, AssignFinding_ToUser, 2);
				}
			}

			fnpClickInDialog_OR("AssignFinding_saveBtn");

			xpathEditFindingIcon = (OR.getProperty("Finding_editIconFirstPart")) + i
					+ (OR.getProperty("Finding_editIconSecondPart"));

			fnpClick_NOR_withoutWait(xpathEditFindingIcon);
			// fnpLoading_wait();

			fnpWaitForVisible("FindingEdit_ChangeStatusPFList");
			fnpPFList("FindingEdit_ChangeStatusPFList", "FindingEdit_ChangeStatusPFListOptions", findingStatus[i]);

			fnpType("OR", "FindingEdit_ReviewNotestxtbox", (String) hashXlData.get("EditFinding_ReviewNotes"));

			fnpClickInDialog_OR("FindingEdit_SaveBtn");

			fnpCheckErrMsg("Error is thrown by application in ModBrack_Lite_Eval  method while " + findingStatus[i]
					+ " the findings");

			fnpWaitForVisibleHavingMultipleElements("TopMessage3");
			String SuccessfulMsg = fnpGetText_OR("TopMessage3");
			fnpMymsg("Top Message after  " + findingStatus[i] + "   the finding in row '" + (i + 1) + "  ----"
					+ SuccessfulMsg);

			Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"),
					"Top message does not contain 'success' word, so might be action of  " + findingStatus[i]
							+ "   the finding  is NOT success");

			fnpClick_OR_WithoutWait("FindingEdit_CloseLink");

			Thread.sleep(2000);

			status = fnpFetchFromTable("LiteEval_FindingsTable", i + 1, FindingStausColIndex);

			Assert.assertTrue(status.equalsIgnoreCase(findingStatus[i]),
					"Status of  finding  is not  '" + findingStatus[i] + "' status in row '" + (i + 1) + "' .");
			fnpMymsg("Status of finding is '" + status + "' status in row '" + (i + 1) + "' .");

		}

	}

	public static void fnpCommonProcessPhyEvalFindings() throws Throwable {

		fnpLoading_wait();

		fnpWaitForVisible("PhyEval_FindingsTable");
		fnpMymsg("Now we are going to process findings in Phy Eval task");

		String[] findingStatus = ((String) hashXlData.get("PhyEvalEditFinding_ChangeStatus")).split(",");

		List<WebElement> AssignFindingIcons_LiteEval = fnpGetORObject_list("FindingsTable_AssignFindingIcon",
				Integer.parseInt(CONFIG.getProperty("implicitWaitTime")));
		int countAssignFindingIcons_LiteEval = AssignFindingIcons_LiteEval.size();
		Assert.assertTrue(countAssignFindingIcons_LiteEval > 0,
				"AssignFinding icons are not visible as their count is '" + countAssignFindingIcons_LiteEval + "' .");
		fnpMymsg("Findings are present or visible and their count is '" + countAssignFindingIcons_LiteEval + "'");

		java.util.List<WebElement> EditFindingIcons_LiteEval;

		int FindingStausColIndex = fnpFindColumnIndex("PhyEval_FindingsTable_header", "Status");
		int CountTotalRows = fnpCountRowsInTable("PhyEval_FindingsTable");
		String status;

		String xpathFindingIcon;
		String xpathEditFindingIcon;
		for (int i = 0; i < CountTotalRows; i++) {

			status = fnpFetchFromTable("PhyEval_FindingsTable", i + 1, FindingStausColIndex);

			Assert.assertTrue(status.equalsIgnoreCase("pending"),
					"Status of  finding  is not in 'PENDING' status in row '" + (i + 1) + "' .");
			fnpMymsg("Status of finding is  'pending' status in row '" + (i + 1) + "' .");

			xpathFindingIcon = (OR.getProperty("FindingsTable_AssignFindingIconPHYEVALFirstPart")) + i
					+ (OR.getProperty("FindingsTable_AssignFindingIconPHYEVALSecondPart"));

			fnpClick_NOR_withoutWait(xpathFindingIcon);

			/*
			 * fnpLoading_wait();
			 * WebElement wb =fnpGetORObjectX___NOR(xpathFindingIcon);
			 * JavascriptExecutor executor = (JavascriptExecutor)driver;
			 * executor.executeScript("arguments[0].click();", wb);
			 * fnpLoading_wait();
			 * 
			 */

			fnpMymsg("Clicked 'Assign Finding' in row" + (i + 1) + "' .");

			fnpClickInDialog_OR("AssignFinding_empLookup_btn");

			fnpWaitTillVisiblityOfElementNClickable_OR("LookkupSecondTextBox");
			// fnpSearchNSelectFirstRadioBtn(2, (String)
			// hashXlData.get("PhyEvalAssignFinding_ToUser"), 2);
			/*
			 * String PhyEvalAssignFinding_ToUser;
			 * if (loginAsFullName.equalsIgnoreCase("") | (loginAsFullName==null) ) {
			 * PhyEvalAssignFinding_ToUser=(String)
			 * hashXlData.get("PhyEvalAssignFinding_ToUser").trim();p
			 * } else {
			 * PhyEvalAssignFinding_ToUser=loginAsFullName;
			 * }
			 * fnpSearchNSelectFirstRadioBtn(2, PhyEvalAssignFinding_ToUser, 2);
			 * 
			 */

			String PhyEvalAssignFinding_ToUser;
			if ((loginAsFullName == null) || loginAsFullName.equalsIgnoreCase("")) {
				// reassignee=(String) hashXlData.get("AccountManager").trim();
				if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking"))) {
					PhyEvalAssignFinding_ToUser = (String) hashXlData.get("PhyEvalAssignFinding_ToUser_Code").trim();
					fnpSearchNSelectFirstRadioBtn(1, (String) hashXlData.get("PhyEvalAssignFinding_ToUser_Code"), 1);
				} else {
					PhyEvalAssignFinding_ToUser = (String) hashXlData.get("PhyEvalAssignFinding_ToUser").trim();
					fnpSearchNSelectFirstRadioBtn(2, PhyEvalAssignFinding_ToUser, 2);
				}

			} else {
				// reassignee=loginAsFullName;
				if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking"))) {
					PhyEvalAssignFinding_ToUser = loginUser_code;
					fnpSearchNSelectFirstRadioBtn(1, loginUser_code, 1);
				} else {
					PhyEvalAssignFinding_ToUser = loginAsFullName;
					fnpSearchNSelectFirstRadioBtn(2, PhyEvalAssignFinding_ToUser, 2);
				}
			}

			fnpClickInDialog_OR("AssignFinding_saveBtn");

			xpathEditFindingIcon = (OR.getProperty("Finding_editIconPHYEVALFirstPart")) + i
					+ (OR.getProperty("Finding_editIconPHYEVALSecondPart"));

			fnpClick_NOR_withoutWait(xpathEditFindingIcon);

			fnpWaitForVisible("FindingEdit_ChangeStatusPFList");
			fnpPFList("FindingEdit_ChangeStatusPFList", "FindingEdit_ChangeStatusPFListOptions", findingStatus[i]);

			fnpType("OR", "FindingEdit_ReviewNotestxtbox", (String) hashXlData.get("PhyEvalEditFinding_ReviewNotes"));

			fnpClickInDialog_OR("FindingEdit_SaveBtn");

			fnpCheckErrMsg("Error is thrown by application in ModBrack_Lite_Eval  method while " + findingStatus[i]
					+ " the findings");

			fnpWaitForVisibleHavingMultipleElements("TopMessage3");
			String SuccessfulMsg = fnpGetText_OR("TopMessage3");
			fnpMymsg("Top Message after  " + findingStatus[i] + "   the finding in row '" + (i + 1) + "  ----"
					+ SuccessfulMsg);

			Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"),
					"Top message does not contain 'success' word, so might be action of  " + findingStatus[i]
							+ "   the finding  is NOT success");

			fnpClick_OR_WithoutWait("FindingEdit_CloseLink");

			Thread.sleep(2000);

			status = fnpFetchFromTable("PhyEval_FindingsTable", i + 1, FindingStausColIndex);

			Assert.assertTrue(status.equalsIgnoreCase(findingStatus[i]),
					"Status of  finding  is not  '" + findingStatus[i] + "' status in row '" + (i + 1) + "' .");
			fnpMymsg("Status of finding is '" + status + "' status in row '" + (i + 1) + "' .");

		}

		fnpMymsg(" Now completed all findings of PhyEval task ");

		fnpClick_OR("PhyEvalRefreshDataBtn");

		if (!(classNameText.toLowerCase().contains("modbrack"))) {
			int PhyEvalDetailTable_ResultColIndex = fnpFindColumnIndex("LiteEval_TaskSummaryTable_header", "Result");
			String PhyEvalDetailTable_Result = fnpFetchFromTable("LiteEval_TaskSummaryTable", 1,
					PhyEvalDetailTable_ResultColIndex);

			String expectedResult = (String) hashXlData.get("PhyEvalTask_Result").trim();
			Assert.assertTrue(PhyEvalDetailTable_Result.equalsIgnoreCase(expectedResult),
					" Result in Phy Eval detail table should become " + expectedResult
							+ "  but it is coming  '" + PhyEvalDetailTable_Result + "' ");
			fnpMymsg(" Result in Phy Eval detail table is now become ---" + PhyEvalDetailTable_Result);

		}

		int EvalInfoTable_StausColIndex = fnpFindColumnIndex("PhyEval_EvaluationInfoTable_header", "Status");
		String EvalINfoTable_status = fnpFetchFromTable("PhyEval_EvaluationInfoTable", 1, EvalInfoTable_StausColIndex);

		Assert.assertTrue(EvalINfoTable_status.equalsIgnoreCase("reviewed"),
				" status in Evaluation Info table should become REVIEWED status but it is coming  '"
						+ EvalINfoTable_status + "' ");
		fnpMymsg(" status in Evaluation Info table is now become REVIEWED .");
	}

	/****** Common code for Cert Decision ******/
	public static void fnpCommonCodeCertDecisionWaitTillNotReady_till_inperform() throws Throwable {

		fnpCommonClickTaskTab();

		int TaskTypeColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskTypeColName);
		int TaskStatusColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_StatusColName);
		int RowNoForCertDecison = fnpFindRow("TasksTable_EditWO", taskType_CertDecision, TaskTypeColIndex);

		int waitCount = 0;
		String certDecisionStatus;
		while (true) {
			waitCount++;
			fnpWaitForVisible("Task_ShowAllLink");
			fnpWaitTillVisiblityOfElementNClickable_OR("Task_ShowAllLink");

			TaskTypeColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskTypeColName);
			TaskStatusColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_StatusColName);
			RowNoForCertDecison = fnpFindRow("TasksTable_EditWO", taskType_CertDecision, TaskTypeColIndex);

			certDecisionStatus = fnpFetchFromTable("TasksTable_EditWO", RowNoForCertDecison, TaskStatusColIndex);
			fnpMymsg("certDecisionStatus ----" + certDecisionStatus);

			if (certDecisionStatus.equalsIgnoreCase("Ready")) {
				fnpMymsg("Cert Decision status has become to 'Ready' state. ");
				break;
			} else {
				Thread.sleep(1000 * 20 * 1);
				driver.navigate().refresh();
				// fnpLoading_wait();
				Thread.sleep(8000);

				// fnpLoading_wait();

				if (!fnpCheckElementPresenceImmediately("Task_ShowAllLink")) {
					fnpCommonClickTaskTab();
				}

				fnpMymsg(" we are in  waiting loop for cert become in Ready ----" + waitCount);
			}

			if (waitCount > 60) {
				msg = "Script waited for approx. 30 min to become 'READY' state of cert decision but it is still showing '"
						+ certDecisionStatus + "' .";
				fnpMymsg(msg);
				throw new Exception(msg);

			}

		}

		/**************
		 * using if condition here b/c this current method(@Test) is being used
		 * in Failure Resolution and we dont want this to check assigner in cert
		 * decision so using if condition
		 **************/
		if (!(currentScriptCode.trim().equalsIgnoreCase("WO_BS-12") ||
				currentScriptCode.trim().equalsIgnoreCase("WO_BS-16") ||
				(currentSuiteName.equalsIgnoreCase(Non_Food_Compounds_suite_Name)) ||
				(currentSuiteName.equalsIgnoreCase(Wales_Work_Order_suite_Name)))) {
			fnpCommonClickSnapShotTab();

			int assignedToColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow",
					SnapShot_taskTable_AssignedToColName);
			int TaskDescColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow",
					SnapShot_taskTable_TaskDescColName);
			int certDecirowNo = fnpFindRow("Snapshot_TasksSummaryTable", SnapShot_taskDesc_CertDeci, TaskDescColIndex);
			String certDeciAssigner = fnpFetchFromTable("Snapshot_TasksSummaryTable", certDecirowNo,
					assignedToColIndex);

			String expectedCertDecisionMaker;
			String expectedCertDecisionMakerCode;
			if (currentSuiteName.equalsIgnoreCase(Dietary_Supplement_suite_Name)) {
				expectedCertDecisionMaker = (String) hashXlData.get("QC").trim();
				expectedCertDecisionMakerCode = (String) hashXlData.get("QC_Code");
			} else {
				expectedCertDecisionMaker = (String) hashXlData.get("QCCertDecision_Maker").trim();
				expectedCertDecisionMakerCode = (String) hashXlData.get("QCCertDecision_Maker_Code");
			}

			/****** 28-04-15 commented for time being *****/
			// Assert.assertEquals(certDeciAssigner.trim(), (String)
			// hashXlData.get("QCCertDecision_Maker").trim(), "Cert Decision is not assigned
			// to QCCertDecision_Maker");
			if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking"))) {
				// Assert.assertEquals(certDeciAssigner.trim(), (String)
				// hashXlData.get("QCCertDecision_Maker_Code").trim(),
				// "QCCertDecision_Maker_Code");
				Assert.assertTrue(certDeciAssigner.contains(expectedCertDecisionMakerCode),
						"Cert Decision is not assigned to QCCertDecision_Maker because actual  value is ---'"
								+ certDeciAssigner + "' and expected is --'" + expectedCertDecisionMakerCode + "'.");

			} else {
				Assert.assertEquals(certDeciAssigner.trim(), expectedCertDecisionMaker,
						"Cert Decision is not assigned to QCCertDecision_Maker because actual  value is ---'"
								+ certDeciAssigner + "' and expected is --'" + expectedCertDecisionMaker + "'.");
			}

			fnpMymsg(" Cert Decision task assigned to '" + expectedCertDecisionMaker + "'  successfully.");

			/****** 28-04-15 commented for time being *****/
		}

		// Not requied as not given in test case
		/*
		 * if ((currentSuiteName.equalsIgnoreCase(Non_Food_Compounds_suite_Name)) ){
		 * fnpCommonClickSnapShotTab();
		 * int assignedToColIndex =
		 * fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow",
		 * SnapShot_taskTable_AssignedToColName);
		 * int TaskDescColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow",
		 * SnapShot_taskTable_TaskDescColName);
		 * int certDecirowNo = fnpFindRow("Snapshot_TasksSummaryTable",
		 * SnapShot_taskDesc_CertDeci, TaskDescColIndex);
		 * String certDeciAssigner = fnpFetchFromTable("Snapshot_TasksSummaryTable",
		 * certDecirowNo, assignedToColIndex);
		 * if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
		 * Assert.assertTrue(certDeciAssigner.contains((String)
		 * hashXlData.get("QC_Code")),
		 * "Cert Decision is not assigned to QC i.e. '"+(String)
		 * hashXlData.get("QC_Code")+"'.");
		 * }else{
		 * Assert.assertEquals(certDeciAssigner.trim(), (String)
		 * hashXlData.get("QC").trim(),
		 * "Cert Decision is not assigned to QC i.e. '"+(String)
		 * hashXlData.get("QC_Code")+"'.");
		 * }
		 * fnpMymsg(" Cert Decision task assigned to '" + (String)
		 * hashXlData.get("QC").trim() + "'  successfully.");
		 * 
		 * }
		 * 
		 */

		// ++++++++++++++++++Verify Alerts+++++++++++++++++++++++++

		fnpCommonGoToHomeNClick();

		// -------Alert Work Order in InProcess status -------------------
		fnpCommonAlertGeneratedVerification("Work_order_in_INPROCESS_status", "WO_InProcess_Status_AlertTable_header",
				"WO #", "WO_InProcess_Status_AlertTable",
				"WO_InProcess_Status_Alert_WO_filterTxtBox", workOrderNo);
		// ---------------------------------------------

		/*
		 * // -------Alert Work order(s) with no tasks ready to perform
		 * ------------------- fnpCommonAlertGeneratedVerification(
		 * "Work_order_with_no_tasks_ready_to_perform",
		 * "Work_order_with_no_tasks_ready_to_perform_AlertTable_header",
		 * "WO #", "Work_order_with_no_tasks_ready_to_perform_AlertTable",
		 * "Work_order_with_no_tasks_ready_to_perform_Alert_WO_filterTxtBox",
		 * workOrderNo); // ---------------------------------------------
		 */

		fnpCommonSwitchToUserForAlerts((String) hashXlData.get("TechOpsManager"));

		// -------Alert Task ready to be assigned -------------------
		fnpCommonAlertGeneratedVerification("Task_ready_to_be_assigned_AlertName",
				"Task_ready_to_be_assigned_AlertTable_header", "WO #", "Task_ready_to_be_assigned_AlertTable",
				"Task_ready_to_be_assigned_Alert_WO_filterTxtBox", workOrderNo);
		// ---------------------------------------------

		// ++++++++++++++++++Verify Alerts+++++++++++++++++++++++++

		fnpSearchWorkOrderAndClickEditAlso(workOrderNo);

		/********************
		 * New changes for sprint 9.2
		 ***********************************/

		fnpCommonClickSnapShotTab();

		fnpWaitForVisible("ActionItemsTopLabel");
		fnpMymsg(" Going to verify DocumentFinal action item should be generated in Pending status. ");
		// fnpWaitForVisible("ActionItemTable_EditWO");
		int ActionItemNoColIndex = fnpFindColumnIndex("ActionItemTable_HeaderRow", actionItemTableStatusColName);
		int AItem_ItemDesc_ColIndex = fnpFindColumnIndex("ActionItemTable_HeaderRow", actionItemTableItemDescColName);
		// int AI_DocumentFinalRowNo = fnpFindRow("ActionItemTable_EditWO",
		// actionItemDesc_DocumentFinal, AItem_ItemDesc_ColIndex);
		int AI_DocumentFinalRowNo = fnpFindRow_ActionItem("ActionItemTable_EditWO", actionItemDesc_DocumentFinal,
				AItem_ItemDesc_ColIndex);

		// Assert.assertTrue(AI_DocumentFinalRowNo > 0, "Action Item 'DocumentFinal' has
		// not been generated. ");
		fnpMymsg("Action Item 'DocumentFinal' has  been generated successfully.  ");

		String documentFinalStatusinActionItemTable = fnpFetchFromTable("ActionItemTable_EditWO", AI_DocumentFinalRowNo,
				ActionItemNoColIndex);

		Assert.assertTrue(documentFinalStatusinActionItemTable.toLowerCase().contains("pending"),
				"After Cert Decision task become in Ready status, DocumentFinal action item should be generated in Pending status. But it is not as It is still showing '"
						+ documentFinalStatusinActionItemTable + "'.");

		fnpMymsg(
				"After Cert Decision task become in Ready status, DocumentFinal action item should be generated in Pending status successfully. ");
		String documentFinalStatusChanged = "COMPLETED";

		int AItem_ItemAssignedTo_ColIndex = fnpFindColumnIndex("ActionItemTable_HeaderRow",
				actionItemTableAssignedToColName);
		int documentFinalAiRowNo = fnpFindRow("ActionItemTable_EditWO", actionItemDesc_DocumentFinal,
				AItem_ItemDesc_ColIndex);
		String docFinalAssigner = fnpFetchFromTable("ActionItemTable_EditWO", documentFinalAiRowNo,
				AItem_ItemAssignedTo_ColIndex);

		if ((currentSuiteName.equalsIgnoreCase(Dietary_Supplement_suite_Name)) ||
				(currentSuiteName.equalsIgnoreCase(Non_Food_Compounds_suite_Name)) ||
				(currentSuiteName.equalsIgnoreCase(Wales_Work_Order_suite_Name))) {
			String expectedDocumentFinalAIAssigner = null;
			String expectedDocumentFinalAIAssignerCode = null;
			String expectedAssigneeProfile = null;

			if ((currentSuiteName.equalsIgnoreCase(Dietary_Supplement_suite_Name))) {
				expectedDocumentFinalAIAssigner = (String) hashXlData.get("QCCertDecision_Maker").trim();
				expectedDocumentFinalAIAssignerCode = (String) hashXlData.get("QCCertDecision_Maker_Code").trim();
				expectedAssigneeProfile = "QCCertDecision_Maker";
			}

			if ((currentSuiteName.equalsIgnoreCase(Non_Food_Compounds_suite_Name))) {
				expectedDocumentFinalAIAssigner = (String) hashXlData.get("QC").trim();
				expectedDocumentFinalAIAssignerCode = (String) hashXlData.get("QC_Code").trim();
				expectedAssigneeProfile = "QC";
			}

			if ((currentSuiteName.equalsIgnoreCase(Wales_Work_Order_suite_Name))) {
				/*
				 * expectedDocumentFinalAIAssigner=(String)
				 * hashXlData.get("Tech_Reviewer").trim();
				 * expectedDocumentFinalAIAssignerCode= (String)
				 * hashXlData.get("Tech_Reviewer_Code").trim();
				 * expectedAssigneeProfile="TechReviewer";
				 */

				/*
				 * expectedDocumentFinalAIAssigner=(String) hashXlData.get("QC").trim();
				 * expectedDocumentFinalAIAssignerCode= (String)
				 * hashXlData.get("QC_Code").trim();
				 * expectedAssigneeProfile="QC";
				 */

				if ((classNameText.equalsIgnoreCase(ModBrack_TMV_Certified_ClassName))
						|| (classNameText.equalsIgnoreCase(ModBrack_BS6920_Certified_ClassName))) {
					expectedDocumentFinalAIAssigner = (String) hashXlData.get("AccountManager").trim();
					expectedDocumentFinalAIAssignerCode = (String) hashXlData.get("AccountManager_Code").trim();
					expectedAssigneeProfile = "QC";
				} else {
					expectedDocumentFinalAIAssigner = (String) hashXlData.get("QC").trim();
					expectedDocumentFinalAIAssignerCode = (String) hashXlData.get("QC_Code").trim();
					expectedAssigneeProfile = "QC";
				}

			}

			if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking"))) {
				Assert.assertTrue(docFinalAssigner.contains(expectedDocumentFinalAIAssignerCode),
						"Document Final AI is not assigned to " + expectedAssigneeProfile + " i.e. '"
								+ expectedDocumentFinalAIAssignerCode + "'"
								+ "but actual is this '" + docFinalAssigner + "'.");
			} else {
				Assert.assertEquals(docFinalAssigner.trim(), expectedDocumentFinalAIAssigner,
						"Document Final AI  is not assigned to  " + expectedAssigneeProfile + " i.e. '"
								+ expectedDocumentFinalAIAssigner + "'");
			}

			fnpMymsg(" Document Final AI is  assigned to  " + expectedAssigneeProfile + " i.e. '"
					+ expectedDocumentFinalAIAssigner + "'  successfully.");

		} else {
			// nothing to do now
		}

		fnpProcessAI(actionItemDesc_DocumentFinal, documentFinalStatusChanged);

		/***************
		 * new changes for sprint 9.2
		 *****************************************/

		/*
		 * Ã¯Â¿Â½ DO NOT create the Cert Decision AI it if all of the following are
		 * true: o the WO type is Mod/Brack, Resolution, or Custom o the
		 * Listing/FRS Update indicator is Ã¯Â¿Â½NoÃ¯Â¿Â½ o On the ModBrackReview or
		 * TechReview task, the checkbox Ã¯Â¿Â½None of the above applyÃ¯Â¿Â½ is selected
		 * under Ã¯Â¿Â½Annual Update Required?Ã¯Â¿Â½ section.
		 */

		/************
		 * This AI CertDecUpdate will be generated only when all 3 conditions
		 * true ==>1. wo not modbrack,not custom not resolution 2. Listing FRS
		 * update =Yes 3. Annual Update Required != None of the above
		 *********/
		// if (!(currentScriptCode.trim().equalsIgnoreCase("WO_BS-16") )) {
		/*
		 * if ( !( ((classNameText.equalsIgnoreCase("Modbrack_Not_Certified")))
		 * & ( ((String)
		 * hashXlData.get("Listing_FRS_Update")).equalsIgnoreCase("No")) & (
		 * ((String) hashXlData.get("Annual_Update_Required")).equalsIgnoreCase(
		 * "None of the above apply")) ) ){
		 */

		String annualUpdateRequired = (String) hashXlData.get("Annual_Update_Required");
		if (annualUpdateRequired == null) {
			annualUpdateRequired = "";
		}

		String Listing_FRS_Update = (String) hashXlData.get("Listing_FRS_Update");
		if (Listing_FRS_Update == null) {
			Listing_FRS_Update = "";
		}

		if (!(classNameText.equalsIgnoreCase(ModBrack_BS6920_Certified_ClassName))) {
			// in WRAS, only this class 'ModBrack_BS6920_Certified' does not have this
			// certDecUpdate AI 10-09-2021
			if (!(((classNameText.equalsIgnoreCase("Modbrack_Not_Certified")) ||
					(classNameText.toLowerCase().contains("custom")) ||
					(classNameText.toLowerCase().contains("resolution"))
			// || (classNameText.equalsIgnoreCase(ModBrack_BS6920_Certified_ClassName))
			)
					&& (Listing_FRS_Update.equalsIgnoreCase("No"))
					&& (annualUpdateRequired.equalsIgnoreCase("None of the above apply")))) {

				fnpMymsg("CertDecUpdate will be generated");

				// int certDecUpdateRowNo = fnpFindRow("ActionItemTable_EditWO",
				// actionItemDesc_CertDecUpdate, AItem_ItemDesc_ColIndex);
				int certDecUpdateRowNo = fnpFindRow_ActionItem("ActionItemTable_EditWO", actionItemDesc_CertDecUpdate,
						AItem_ItemDesc_ColIndex);

				// Assert.assertTrue(certDecUpdateRowNo > 0, "Action Item 'CertDecUpdate' has
				// not been generated. ");
				fnpMymsg("Action Item 'CertDecUpdate' has  been generated successfully.  ");

				if ((currentSuiteName.equalsIgnoreCase(Dietary_Supplement_suite_Name)) ||
						(currentSuiteName.equalsIgnoreCase(Non_Food_Compounds_suite_Name)) ||
						(currentSuiteName.equalsIgnoreCase(Wales_Work_Order_suite_Name))) {

					AItem_ItemAssignedTo_ColIndex = fnpFindColumnIndex("ActionItemTable_HeaderRow",
							actionItemTableAssignedToColName);
					String certDecisionUpdateAIAssigner = fnpFetchFromTable("ActionItemTable_EditWO",
							certDecUpdateRowNo, AItem_ItemAssignedTo_ColIndex);

					/*
					 * if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
					 * //Assert.assertEquals(certDeciAssigner.trim(), (String)
					 * hashXlData.get("QCCertDecision_Maker_Code").trim(),
					 * "QCCertDecision_Maker_Code");
					 * Assert.assertTrue(certDecisionAssigner.contains((String)
					 * hashXlData.get("QCCertDecision_Maker_Code")),
					 * "Document Final AI is not assigned to QC/QCCertDecision_Maker i.e. '" +
					 * (String) hashXlData.get("QCCertDecision_Maker_Code").trim() + "'");
					 * }else{
					 * Assert.assertEquals(docFinalAssigner.trim(), (String)
					 * hashXlData.get("QCCertDecision_Maker").trim(),
					 * "Document Final AI  is not assigned to QC/QCCertDecision_Maker i.e. '" +
					 * (String) hashXlData.get("QCCertDecision_Maker").trim() + "'");
					 * }
					 */

					String expectedCertDeciUpdateAIAssignee = null;

					/*
					 * if ( (loginAsFullName==null) ||loginAsFullName.equalsIgnoreCase("") ) {
					 * if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
					 * expectedCertDeciUpdateAIAssignee=(String)
					 * hashXlData.get("AccountManager_Code").trim();
					 * }else{
					 * expectedCertDeciUpdateAIAssignee=(String)
					 * hashXlData.get("AccountManager").trim();
					 * }
					 * 
					 * } else {
					 * if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
					 * expectedCertDeciUpdateAIAssignee=loginUser_code;
					 * }else{
					 * expectedCertDeciUpdateAIAssignee=loginAsFullName;
					 * }
					 * }
					 */

					// IPM-11990
					if (currentSuiteName.equalsIgnoreCase(Dietary_Supplement_suite_Name)) {
						if ((loginAsFullName == null) || loginAsFullName.equalsIgnoreCase("")) {
							if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking"))) {
								expectedCertDeciUpdateAIAssignee = (String) hashXlData.get("AccountManager_Code")
										.trim();
							} else {
								expectedCertDeciUpdateAIAssignee = (String) hashXlData.get("AccountManager").trim();
							}

						} else {
							if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking"))) {
								expectedCertDeciUpdateAIAssignee = loginUser_code;
							} else {
								expectedCertDeciUpdateAIAssignee = loginAsFullName;
							}
						}
					} else {
						if (currentSuiteName.equalsIgnoreCase(Non_Food_Compounds_suite_Name)) {
							if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking"))) {
								expectedCertDeciUpdateAIAssignee = (String) hashXlData.get("Cert_Decider_Code").trim();
							} else {
								expectedCertDeciUpdateAIAssignee = (String) hashXlData.get("Cert_Decider").trim();
							}
						} else {
							if (currentSuiteName.equalsIgnoreCase(Wales_Work_Order_suite_Name)) {
								if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking"))) {
									expectedCertDeciUpdateAIAssignee = (String) hashXlData.get("AccountManager_Code")
											.trim();
								} else {
									expectedCertDeciUpdateAIAssignee = (String) hashXlData.get("AccountManager").trim();
								}
							}
						}
					}

					Assert.assertTrue(certDecisionUpdateAIAssigner.contains(expectedCertDeciUpdateAIAssignee),
							actionItemDesc_CertDecUpdate + " AI is not assigned to account manager i.e. '"
									+ expectedCertDeciUpdateAIAssignee + "'");
					fnpMymsg(actionItemDesc_CertDecUpdate + " AI is  assigned to '" + expectedCertDeciUpdateAIAssignee
							+ "'  successfully.");

				}

				String certDecuUpdateStatusChanged;
				if (!(currentSuiteName.equalsIgnoreCase(Wales_Work_Order_suite_Name))) {

					certDecuUpdateStatusChanged = "SUBMITTED";
					fnpProcessAI(actionItemDesc_CertDecUpdate, certDecuUpdateStatusChanged);

					if ((currentSuiteName.equalsIgnoreCase(Dietary_Supplement_suite_Name))
							|| (currentSuiteName.equalsIgnoreCase(Non_Food_Compounds_suite_Name))) {

						AItem_ItemAssignedTo_ColIndex = fnpFindColumnIndex("ActionItemTable_HeaderRow",
								actionItemTableAssignedToColName);
						String certDecisionUpdateAIAssigner = fnpFetchFromTable("ActionItemTable_EditWO",
								certDecUpdateRowNo, AItem_ItemAssignedTo_ColIndex);

						if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking"))) {
							Assert.assertTrue(
									certDecisionUpdateAIAssigner.contains((String) hashXlData.get("Cert_Decider_Code")),
									actionItemDesc_CertDecUpdate
											+ " AI is not assigned to Cert Decider after submitting CertDecUpdate AI i.e. '"
											+ (String) hashXlData.get("Cert_Decider_Code").trim() + "'");
						} else {
							Assert.assertEquals(certDecisionUpdateAIAssigner.trim(),
									(String) hashXlData.get("Cert_Decider").trim(),
									actionItemDesc_CertDecUpdate
											+ " AI  is not assigned to Cert Decider after submitting CertDecUpdate AI  i.e. '"
											+ (String) hashXlData.get("Cert_Decider").trim() + "'");
						}

						fnpMymsg(actionItemDesc_CertDecUpdate
								+ " AI  is  assigned to Cert Decider after submitting CertDecUpdate AI  i.e. '"
								+ (String) hashXlData.get("Cert_Decider").trim() + "' successfully.");

					}

				} else {

				}

				certDecuUpdateStatusChanged = "COMPLETED";
				fnpProcessAI(actionItemDesc_CertDecUpdate, certDecuUpdateStatusChanged);

			}
		}

		/****************************************************************************/

		/*****************************************************/

		/******************
		 * New changes due to new field cert decider
		 ***************************/

		/*
		 * driver.close();
		 * // fnpLaunchBrowserAndSecureLogin("srisley");
		 * fnpLaunchBrowserAndSecureLogin((String)
		 * hashXlData.get("SecureLoginUserNameForCertTask"));
		 */

		String certDecuUpdateStatusChanged = "";

		if (classNameText.equalsIgnoreCase(Resolution_WRAS_Approved_ClassName)) {
			certDecuUpdateStatusChanged = "COMPLETED";
			fnpProcessAI(actionItemDesc_CertDecUpdate, certDecuUpdateStatusChanged);
		}

		fnpCommonClickTopMainMenu("Menu", "FirstRefrenceLinkJustBelowToMenu", "SwitchUser_Link", "SwitchUser_Go_btn");
		fnpType("OR", "SwitchUser_txtBx", (String) hashXlData.get("SecureLoginUserNameForCertTask"));
		fnpLoading_wait();
		fnpIpulseDuringLoading();

		String Ajax_UserName_Xpath = "//li[@data-item-label='"
				+ (String) hashXlData.get("SecureLoginUserNameForCertTask").toUpperCase().trim() + "']";
		fnpCheckElementEnabledImmediately_NOR(Ajax_UserName_Xpath);
		Thread.sleep(1000);
		fnpClick_NOR(Ajax_UserName_Xpath);
		fnpLoading_wait();
		fnpIpulseDuringLoading();
		fnpClick_OR("SwitchUser_Go_btn");

		fnpSearchWorkOrderOnly(workOrderNo);
		fnpWaitTillClickable("EditTaskTabLink");
		fnpGetORObjectX("EditTaskTabLink").click();
		fnpLoading_wait();
		fnpWaitForVisible("Task_ShowAllLink");

		/****************************************************************** ***************************/

		/******************
		 * Commented below few lines due to New changes due to new field cert
		 * decider
		 ***************************/
		/*
		 * fnpCommonClickTaskTab(); fnpWaitForVisible("Task_ShowAllLink");
		 * fnpWaitTillVisiblityOfElementNClickable_OR("Task_ShowAllLink"); //
		 * fnpLoading_wait();
		 */
		/****************************************************************** ***************************/

		TaskTypeColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskTypeColName);
		int RowNoForCertDecision = fnpFindRow("TasksTable_EditWO", taskType_CertDecision, TaskTypeColIndex);

		fnpMymsg(" Going to click Cert Decision task no. ");
		int ColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskNoColName);
		String CertDecisionTaskNo = fnpFetchFromTable("TasksTable_EditWO", RowNoForCertDecision, ColIndex);
		// Thread.sleep(1000);
		Thread.sleep(500);

		fnpClickALinkInATable(CertDecisionTaskNo);

		fnpMymsg("Clicked on Cert Decision task no. in Task Table i.e. '" + CertDecisionTaskNo + "' .");

		fnpWaitForVisible("TaskTab_CertDeci_AssignTaskIcon");
		fnpCheckError("");
		fnpWaitTillVisiblityOfElementNClickable_OR("TaskTab_CertDeci_AssignTaskIcon");
		fnpGetORObjectX("TaskTab_CertDeci_AssignTaskIcon").click();

		fnpClickInDialog_OR("TaskTab_ScopeValidation_AssignTask_SaveBtn");

		fnpCheckErrMsg(
				"Error is thrown by application in Certification_Decision_Task  method while Assigning the task");

		fnpWaitForVisible("TaskTab_CertDeci_PerformTaskIcon");
		fnpWaitTillVisiblityOfElementNClickable_OR("TaskTab_CertDeci_PerformTaskIcon");
		fnpGetORObjectX("TaskTab_CertDeci_PerformTaskIcon").click();
		fnpMymsg("Clicked on Cert Decision's Perform Task Icon.");

		fnpLoading_wait();
		fnpCheckErrMsg(
				" Error is thrown by application while performing the task in Certification_Decision_Task  method ");
		fnpWaitForVisible("CertDeci_DecisionPFList");

		if (currentSuiteName.equalsIgnoreCase(Wales_Work_Order_suite_Name)) {
			String decisionValuesAll = (String) hashXlData.get("CertDecisionTask_Decision_DropDownValues").trim();
			String[] decisionValuesArray = decisionValuesAll.split(",");
			List<String> decisionValues = Arrays.asList(decisionValuesArray);
			fnpPFList_VerifyOptionsValues_Contains("CertDeci_DecisionPFList", "CertDeci_DecisionPFListOptions",
					decisionValues);
			// fnpLoading_wait();
			fnpClick_OR_WithoutWait("CertDeci_DecisionPFList");//
			fnpLoading_wait();
		}

		fnpPFList("CertDeci_DecisionPFList", "CertDeci_DecisionPFListOptions",
				(String) hashXlData.get("Decision_Option"));

		Thread.sleep(2000);
		if (fnpCheckElementPresenceImmediately("EditWOBtnTaskInside")) {
			fnpClick_OR("EditWOBtnTaskInside");

		}
		fnpClick_OR("CertDeci_TaskDetails_SaveBtn"); // taking some time

		fnpCheckErrMsg(
				" Error is thrown by application while saving (Certification Decision list or other details data) the task in Certification_Decision_Task  method ");

		if (!(currentSuiteName.equalsIgnoreCase(Wales_Work_Order_suite_Name))) {

			fnpClick_OR("TaskTab_ScopeValidation_BackToViewBtn");
			// Thread.sleep(2000);

			fnpClick_OR("TaskTab_ScopeValidation_BackBtn");

			fnpWaitForVisible("Task_ShowAllLink");
			fnpWaitTillVisiblityOfElementNClickable_OR("Task_ShowAllLink");

			fnpWaitForElementVisibility("TasksTable_EditWO", CONFIG.getProperty("genMax_waitTime"));
			int StatusColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_StatusColName);
			int taskTypeColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskTypeColName);
			RowNoForCertDecision = fnpFindRow("TasksTable_EditWO", taskType_CertDecision, taskTypeColIndex);
			String certDeciStatus_TaskTable = fnpFetchFromTable("TasksTable_EditWO", RowNoForCertDecision,
					StatusColIndex);

			Assert.assertTrue(certDeciStatus_TaskTable.toLowerCase().contains("inperform"),
					"In Task Table , status has not been changed  to 'INPERFORM' . It is still showing '"
							+ certDeciStatus_TaskTable + "'.");

			fnpMymsg(
					" After assigning and perform , status in Task Table has  been changed  to 'INPERFORM' successfully.");

			fnpCheckErrMsg("Error is thrown by application in Certification_Decision_Task  method");
		} else {

			/*
			 * //click top banner wo no. to return to snapshot tab, but currently no id on
			 * wo no. so clicking back to view button for time being
			 * fnpCommonBackToViewNBackBtnClick();
			 * // fnpCommonClickSnapShotTab();
			 * 
			 */

			fnpClick_OR("TaskDetailPage_wolink_atTop_xpath");

			if ((classNameText.equalsIgnoreCase(ModBrack_Reg4_Certified_ClassName))) {
				fnpCommonClickSnapShotTab();
				fnpMymsg(" Going to verify '" + actionItemDesc_ListingUpdate
						+ "' action item should be generated in Pending status and it is assigned to '	Marie S. Zueski'");
				fnpVerifyActionItemGeneratedOrNot(actionItemDesc_ListingUpdate);
				fnpVerifyAI_isAssignedToThisPerson(actionItemDesc_ListingUpdate,
						(String) hashXlData.get("ListingUpdateAIAssignee_Name"),
						(String) hashXlData.get("ListingUpdateAIAssignee_Code"), "Marie S. Zueski");

				// to login back to normal user to complete this listing update as this ai is
				// can not be completed by cert decider as completed option is not present when
				// login with cert decider
				driver.close();
				fnpLaunchBrowserAndLogin();
				fnpSearchWorkOrderOnly(workOrderNo);
				fnpProcessAI(actionItemDesc_ListingUpdate, ((String) hashXlData.get("ListingUpdateAIStatus")).trim());

				/*
				 * fnpCommonClickTopMainMenu("Menu", "FirstRefrenceLinkJustBelowToMenu",
				 * "SwitchUser_Link", "SwitchUser_Go_btn");
				 * fnpType("OR","SwitchUser_txtBx",(String)
				 * hashXlData.get("SecureLoginUserNameForCertTask"));
				 * fnpClick_OR("SwitchUser_Go_btn");
				 * fnpSearchWorkOrderOnly(workOrderNo);
				 */

			}

			fnpCommonClickTaskTab();

			TaskTypeColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskTypeColName);
			TaskStatusColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_StatusColName);
			RowNoForCertDecision = fnpFindRow("TasksTable_EditWO", taskType_CertDecision, TaskTypeColIndex);

			certDecisionStatus = fnpFetchFromTable("TasksTable_EditWO", RowNoForCertDecision, TaskStatusColIndex);

			Assert.assertTrue(certDecisionStatus.toLowerCase().contains("completed"),
					"After completed the Cert Decision Task after assign,perform,complete , status has not been changed  to 'COMPLETED' . It is still showing '"
							+ certDecisionStatus + "'.");

			fnpMymsg(
					" After completed the Cert Decision Task after assign,perform,complete , status has  been changed  to 'COMPLETED' successfully.");

			if (!(classNameText.equalsIgnoreCase(ModBrack_BS6920_Certified_ClassName)
					|| classNameText.equalsIgnoreCase(ModBrack_Reg4_Certified_ClassName)

			)) {
				int taskStatusColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_StatusColName);
				int taskTypeColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskTypeColName);
				int externalReviewRowNo = fnpFindRow("TasksTable_EditWO", taskType_ExternalReview_Task,
						taskTypeColIndex);
				String externalReviewTaksStatus = fnpFetchFromTable("TasksTable_EditWO", externalReviewRowNo,
						taskStatusColIndex);
				Assert.assertTrue(externalReviewTaksStatus.toLowerCase().contains("ready"),
						"The '" + taskType_ExternalReview_Task
								+ "' task should have become Ready , but it has not  become Ready ");
				fnpMymsg("The '" + taskType_ExternalReview_Task + "' task has become Ready now.");
			}

			fnpCommonClickSnapShotTab();

			/*
			 * //code for 'COMPLETE_APPRVD'.
			 * if ((classNameText.equalsIgnoreCase(ModBrack_WRAS_Approved_ClassName)) ){
			 * fnpVerifyTask_ColValue_at_SnapshotTab(SnapShot_taskTable_ResultColName,
			 * SnapShot_taskTable_TaskDescColName,SnapShot_taskDesc_CertDeci,
			 * "COMPLETE_APPRVD") ;
			 * }
			 * p
			 * if
			 * ((classNameText.equalsIgnoreCase(ModBrack_WRAS_Cancelled_By_Client_ClassName)
			 * ) ){
			 * fnpVerifyTask_ColValue_at_SnapshotTab(SnapShot_taskTable_ResultColName,
			 * SnapShot_taskTable_TaskDescColName,SnapShot_taskDesc_CertDeci,
			 * "COMPLETE_APPRVD") ;
			 * }
			 */
			/*
			 * String expectedTaskResult;
			 * switch (classNameText) {
			 * 
			 * case ModBrack_WRAS_Approved_ClassName:
			 * // fnpVerifyTask_ColValue_at_SnapshotTab(SnapShot_taskTable_ResultColName,
			 * SnapShot_taskTable_TaskDescColName,SnapShot_taskDesc_CertDeci,
			 * "COMPLETE_APPRVD") ;
			 * expectedTaskResult=(String) hashXlData.get("ExpectedCertTask_Result");
			 * break;
			 * 
			 * case ModBrack_WRAS_Rejected_ClassName:
			 * //fnpVerifyTask_ColValue_at_SnapshotTab(SnapShot_taskTable_ResultColName,
			 * SnapShot_taskTable_TaskDescColName,SnapShot_taskDesc_CertDeci,
			 * "COMPLETE_APPRVD") ;
			 * expectedTaskResult=(String) hashXlData.get("ExpectedCertTask_Result");
			 * break;
			 * 
			 * case ModBrack_WRAS_Cancelled_By_Client_ClassName:
			 * //fnpVerifyTask_ColValue_at_SnapshotTab(SnapShot_taskTable_ResultColName,
			 * SnapShot_taskTable_TaskDescColName,SnapShot_taskDesc_CertDeci,
			 * "COMPLETE_APPRVD") ;
			 * expectedTaskResult=(String) hashXlData.get("ExpectedCertTask_Result");
			 * break;
			 * 
			 * 
			 * 
			 * default:
			 * //fnpVerifyTask_ColValue_at_SnapshotTab(SnapShot_taskTable_ResultColName,
			 * SnapShot_taskTable_TaskDescColName,SnapShot_taskDesc_CertDeci,
			 * "COMPLETE_APPRVD") ;
			 * expectedTaskResult=(String) hashXlData.get("ExpectedCertTask_Result");
			 * 
			 * }
			 */

			if (currentSuiteName.equalsIgnoreCase(Wales_Work_Order_suite_Name)) {
				fnpVerifyTask_ColValue_at_SnapshotTab(SnapShot_taskTable_ResultColName,
						SnapShot_taskTable_TaskDescColName, SnapShot_taskDesc_CertDeci,
						(String) hashXlData.get("ExpectedCertTask_Result"));
			}

			// not for ModBrack_WRAS_Cancelled_By_Client_ClassName
			if ((classNameText.equalsIgnoreCase(ModBrack_WRAS_Approved_ClassName))
					|| (classNameText.equalsIgnoreCase(ModBrack_WRAS_Rejected_ClassName))) {

				fnpWaitForVisible("ActionItemsTopLabel");

				ActionItemNoColIndex = fnpFindColumnIndex("ActionItemTable_HeaderRow", actionItemTableStatusColName);
				AItem_ItemDesc_ColIndex = fnpFindColumnIndex("ActionItemTable_HeaderRow",
						actionItemTableItemDescColName);
				int AI_AssessPnlReviewRowNo = fnpFindRow("ActionItemTable_EditWO", actionItemDesc_AssessPnlReview,
						AItem_ItemDesc_ColIndex);
				String AssessPnlReviewStatusinActionItemTable = fnpFetchFromTable("ActionItemTable_EditWO",
						AI_AssessPnlReviewRowNo, ActionItemNoColIndex);

				Assert.assertTrue(AssessPnlReviewStatusinActionItemTable.toLowerCase().contains("pending"),
						"After completing Cert Decision task, '" + actionItemDesc_AssessPnlReview
								+ "' action item should be generated in Pending status. But it is not as It is still showing '"
								+ AssessPnlReviewStatusinActionItemTable + "'.");

				fnpMymsg(" After completing Cert Decision task, '" + actionItemDesc_AssessPnlReview
						+ "' action item has been generated in Pending status successfully. ");

				int AssessPnlReviewRowNo = fnpFindRow_ActionItem("ActionItemTable_EditWO",
						actionItemDesc_AssessPnlReview, AItem_ItemDesc_ColIndex);
				AItem_ItemAssignedTo_ColIndex = fnpFindColumnIndex("ActionItemTable_HeaderRow",
						actionItemTableAssignedToColName);
				String AssessPnlReviewAIAssigner = fnpFetchFromTable("ActionItemTable_EditWO", AssessPnlReviewRowNo,
						AItem_ItemAssignedTo_ColIndex);

				String expectedAssessPnlReviewAssignee = null;

				if (currentSuiteName.equalsIgnoreCase(Wales_Work_Order_suite_Name)) {
					if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking"))) {
						expectedAssessPnlReviewAssignee = (String) hashXlData.get("AccountManager_Code").trim();
					} else {
						expectedAssessPnlReviewAssignee = (String) hashXlData.get("AccountManager").trim();
					}
				}

				Assert.assertTrue(AssessPnlReviewAIAssigner.contains(expectedAssessPnlReviewAssignee),
						actionItemDesc_AssessPnlReview + " AI is not assigned to account manager i.e. '"
								+ expectedAssessPnlReviewAssignee + "'");
				fnpMymsg(actionItemDesc_AssessPnlReview + " AI is  assigned to '" + expectedAssessPnlReviewAssignee
						+ "'  successfully.");

			}

		}

	}

	/**********
	 * Using this function we can click or mouse hover easily because some time
	 * it is unable to click on Menu due to java script
	 ******/
	public static void fnpClickAtTopWorkAroundForClickingMenu() throws Throwable {

		// fnpMouseHover("logOutBtn");
		// fnpMouseHover("FooterElement");

		// Thread.sleep(500);
		Thread.sleep(100);

		fnWaitForVisible_NotInOR(".//*[@id='headerForm:logImg']");
		WebElement wb = driver.findElement(By.xpath(".//*[@id='headerForm:logImg']"));
		// wb.click();
		fnpClick_NOR(".//*[@id='headerForm:logImg']");

		// fnpMouseHover_NotInOR(".//*[@id='headerForm:logImg']");

		// fnpGetORObjectX_usingLinkText("Menu").click();

	}

	public static void fnpSampleSelectionInsightCode() throws Throwable {

		String originalHandle = driver.getWindowHandle();
		fnpMymsg(" Now going to generate EPSF No. by clicking 'Create' button  and filling forms.");
		Thread.sleep(1000);

		ArrayList<String> listoldtabs1 = new ArrayList<String>(driver.getWindowHandles());
		fnpGetORObjectX("SampleSelectionCreateBtn").click();
		fnpMymsg("Just after clicking Create Button");

		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());

		int oldtabscount1 = listoldtabs1.size();
		int newTotal = tabs.size();
		int ii = 0;
		while (newTotal != (oldtabscount1 + 1)) {
			Thread.sleep(1000);
			tabs = new ArrayList<String>(driver.getWindowHandles());
			newTotal = tabs.size();
			ii = ii + 1;
			if (ii > Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
				break;
			}
		}

		Thread.sleep(4000);
		fnpMymsg("Just before switching to insight tab");
		int tabsCount = tabs.size();
		for (int i = 0; i < tabsCount; i++) {

			if (originalHandle.equalsIgnoreCase(tabs.get(i))) {
				// nothing to do

			} else {
				driver.switchTo().window(tabs.get(i));
				Thread.sleep(1000);
				fnpMymsg("Just after switching to insight tab");
				break;
			}

		}

		fnpMymsg("waiting for Insight Next button");
		fnpWaitForVisible("Insight_NextBtn");
		fnpMymsg("waiting for collection type list");

		fnpWaitForVisible("Insight_CollectionTypeList");
		fnpMouseHover("Insight_CollectionTypeList");
		fnpMymsg(" Insight Application has been opened successfully. Now going to fill data");
		Thread.sleep(500);

		String expCollType = (String) hashXlData.get("Collection_Type").trim();
		WebElement element99 = fnpGetORObjectX("Insight_CollectionTypeList");
		Select se = new Select(element99);
		se.selectByVisibleText(expCollType);

		String selectedcollTypeValue = se.getFirstSelectedOption().getText();

		fnpMymsg(" Selected Collective Type value is ===" + selectedcollTypeValue);
		Assert.assertEquals(selectedcollTypeValue, (String) hashXlData.get("Collection_Type"),
				"Value inserted in collection type is not correct. Inserted value is--"
						+ selectedcollTypeValue);
		fnpMymsg(" Correct value of collection type inserted in list successfully");

		fnpType("OR", "TestCategoryList", (String) hashXlData.get("Test_Category"));
		Thread.sleep(500);

		Thread.sleep(500);
		fnpType("OR", "StandardList", (String) hashXlData.get("Standard"));
		Thread.sleep(1500);
		fnpType("OR", "TestLocationList", (String) hashXlData.get("Test_Location"));
		Thread.sleep(1500);
		fnpType("OR", "ShipToLocationList", (String) hashXlData.get("Ship_to_Location"));
		Thread.sleep(500);
		fnpGetORObjectX("Insight_NextBtn").click();

		fnpWaitForVisible("TestDescriptionTxtBox");
		fnpType("OR", "TestDescriptionTxtBox", (String) hashXlData.get("Test_Description"));
		fnpType("OR", "ProductNameModelNoTxtBox", (String) hashXlData.get("ProductName_ModelNumber"));

		fnpGetORObjectX("Insight_NextBtn").click();

		fnpWaitForVisible("Insight_NextBtn");
		fnpWaitTillVisiblityOfElementNClickable_OR("Insight_NextBtn");
		fnpGetORObjectX("Insight_NextBtn").click();
		fnpWaitForVisible("CreateEPSFBtn");
		fnpGetORObjectX("CreateEPSFBtn").click();

		driver.close();

		driver.switchTo().window(originalHandle);

		fnpClick_OR("RefreshDataBtn");

	}

	public static void fnpClickTopHomeMenu() throws Throwable {
		fnpWaitForVisible("Home_topLink_id");

		WebElement Home = fnpGetORObjectX("Home_topLink_id");
		Actions action = new Actions(driver);
		action.moveToElement(Home).perform();

		fnpWaitTillVisiblityOfElementNClickable_OR("Home_topLink_id");
		fnpGetORObjectX("Home_topLink_id").click();
		/***********
		 * added on 29-05-2017 as sometime someone can change/or set the
		 * preference for summarized alrert tab and hence showAlertBtn then will
		 * not visible
		 *************/
		if (!(fnpCheckElementDisplayed("ShowAlertsBtn", 20))) {
			fnpClick_OR("AlertTabLink");
		}
		/*********************************************************/

		fnpWaitForVisible("ShowAlertsBtn");
		fnpWaitTillVisiblityOfElementNClickable_OR("ShowAlertsBtn");

	}

	public static void fnpSampleSelectionEPSFCode() throws Throwable {

		fnpClick_OR("SampleSelectionCreateBtn");

		String finProgram = (String) hashXlData.get("RevenueProgram").trim();
		fnpPFList("EPSF_FinancialProgramList", "EPSF_FinancialProgramListOptions", finProgram);

		fnpLoading_wait();
		String expCollType = (String) hashXlData.get("Collection_Type").trim();
		// value in excel and drop down is not matched , 'Qualification( QQ )'
		// and 'Qualification (QQ)'
		fnpPFList("EPSF_CollectionTypeList", "EPSF_CollectionTypeListOptions", expCollType);
		// fnpPFList("EPSF_CollectionTypeList",
		// "EPSF_CollectionTypeListOptions", "Qualification( QQ )");

		Thread.sleep(3000);

		fnpPFList("EPSF_TestCategoryList", "EPSF_TestCategoryListOptions", (String) hashXlData.get("Test_Category"));

		fnpPFList("EPSF_StandardList", "EPSF_StandardListOptions", (String) hashXlData.get("Standard"));

		fnpPFList("EPSF_TestLocationList", "EPSF_TestLocationListOptions", (String) hashXlData.get("Test_Location"));

		fnpPFList("EPSF_ShipToLocationList", "EPSF_ShipToLocationListOptions",
				(String) hashXlData.get("Ship_to_Location"));

		fnpClick_OR("EPSF_CreateNextBtn");

		fnpType("OR", "EPSF_ProductNameModalNoTxtBox", (String) hashXlData.get("ProductName_ModelNumber"));
		fnpType("OR", "EPSF_TestDescriptionTxtBox", (String) hashXlData.get("Test_Description"));
		fnpType("OR", "EPSF_PerformanceStandardYearTxtBox", "2018");

		/***** New changes as 4 tabs added in epsf ******/
		fnpClick_OR("EPSF_CreateNextBtn");// Click on Next for EPSF fields tab.
		fnpLoading_wait();
		fnpWaitForVisible("EPSF_INFO_highlightedTab");
		fnpLoading_wait();
		fnpClick_OR("EPSF_CreateNextBtn");// Click on Next for EPSF Info tab.
		fnpLoading_wait();

		fnpClick_OR("CreateEPSFFirstBtn");
		/***** New changes as 4 tabs added in epsf ******/

		// fnpClick_OR("EPSF_CreateEPSFBtn"); /// this is old one when 2 tab
		// present only.

		fnpClick_OR("EPSF_SaveBtn");

		fnpClickTopHomeMenu();
		fnpSearchWorkOrderOnly(workOrderNo);

		int taskColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", SnapShot_taskTable_TaskColName);

		int TaskDescColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow",
				SnapShot_taskTable_TaskDescColName);

		if (classNameText.equalsIgnoreCase("Modbrack_Not_Certified")) {
			int modBrackrowNo = fnpFindRow("Snapshot_TasksSummaryTable", SnapShot_ModBrack_Review_Task,
					TaskDescColIndex);
			String modBrackTaskNo = fnpFetchFromTable("Snapshot_TasksSummaryTable", modBrackrowNo, taskColIndex);
			Thread.sleep(500);

			fnpClickALinkInATable(modBrackTaskNo);
		} else {
			int sampleSelectionrowNo = fnpFindRow("Snapshot_TasksSummaryTable", SnapShot_taskDesc_SampleSelection,
					TaskDescColIndex);
			String sampleTaskNo = fnpFetchFromTable("Snapshot_TasksSummaryTable", sampleSelectionrowNo, taskColIndex);
			Thread.sleep(500);

			fnpClickALinkInATable(sampleTaskNo);
			fnpLoading_wait();
		}
	}

	public static void fnpCommonClickTopMainMenu(String FirstMouseHoverLinkName, String SecondMouseHoverLinkName,
			String ClickedLinkName, String objName) throws Throwable {

		int whileCounter = 0;
		while (true) {
			whileCounter++;
			try {

				fnpMoveMouseAtCenterBottomOfScreen();

				fnpSetEnglishLanguage();

				fnpClickAtTopWorkAroundForClickingMenu();
				/*
				*//***********************************************************************/
				/*
				 * //Worked till now but fail today on 8/3/16 Thread.sleep(100);
				 * WebElement firstElement =
				 * fnpGetORObjectX_usingLinkText(FirstMouseHoverLinkName);
				 * Actions action = new Actions(driver);
				 * action.moveToElement(firstElement).build().perform();
				 *//***********************************************************************/
				/*		
				*/

				/***********************************************************************/
				Thread.sleep(1000);
				WebElement firstElement = fnpGetORObjectX_usingLinkText(FirstMouseHoverLinkName);
				// fnpGetORObjectX_usingLinkText(FirstMouseHoverLinkName).click();
				Thread.sleep(1000);
				fnpWaitForVisible_usingLinkNameInOR(FirstMouseHoverLinkName);
				fnpMouseHover_LinkNameInOR(FirstMouseHoverLinkName);

				Thread.sleep(1000); // Can comment later

				/***********************************************************************/

				if (fnpGetCurrRunningBrowserName().equalsIgnoreCase("IE")) {
					Thread.sleep(1000);
				}

				/*
				 * Actions action3 = new Actions(driver); WebElement
				 * FirstMouseHoverElement =
				 * fnpGetORObjectX_usingLinkText(FirstMouseHoverLinkName);
				 * WebElement SecondMouseHoverElement =
				 * fnpGetORObjectX_usingLinkText(SecondMouseHoverLinkName);
				 * WebElement ToBeClickedElement =
				 * fnpGetORObjectX_usingLinkText(ClickedLinkName);
				 * action3.moveToElement
				 * (FirstMouseHoverElement).moveToElement(SecondMouseHoverElement
				 * )
				 * .moveToElement(ToBeClickedElement).click().build().perform();
				 */

				/*				*//************ Commented on 31-01-2017 ********************/
				/*
				 * if (fnpGetCurrRunningBrowserName().equalsIgnoreCase("IE")) {
				 * firstElement.sendKeys(Keys.ARROW_DOWN); Thread.sleep(1000); }
				 *//************ ********************/
				/*
				*/

				/*				
				*//*****************
					 * Added on 8/3/16
					 ******************************************************/
				/*
				 * WebElement secondElement =
				 * fnpGetORObjectX_usingLinkText(ClickedLinkName); Actions
				 * action = new Actions(driver);
				 * action.moveToElement(secondElement).build().perform();
				 * Thread.sleep(1000);
				 *//***********************************************************************/
				/*
				 * fnpGetORObjectX_usingLinkText(ClickedLinkName).click();
				 */

				/*****************
				 * Added on 19/7/16
				 ******************************************************/

				Actions action = new Actions(driver);

				WebElement first = fnpGetORObjectX_usingLinkText(FirstMouseHoverLinkName);
				action.moveToElement(first);

				WebElement second = fnpGetORObjectX_usingLinkText(SecondMouseHoverLinkName);
				action.moveToElement(second);

				WebElement third = fnpGetORObjectX_usingLinkText(ClickedLinkName);
				action.moveToElement(third);

				action.click().build().perform();

				/***************** ******************************************************/

				fnpLoading_wait();

				fnpWaitForVisible(objName, 15);

				break;

			} catch (Throwable t) {

				if (whileCounter > 10) {
					throw new Exception("Failed in method fnpCommonClickTopMainMenu .  Reason is ---" + t.getMessage());
				} else {
					fnpMymsg("");
					fnpMymsg("");
					fnpMymsg("");
					fnpMymsg("");
					fnpMymsg("");
					fnpMymsg("@  ----Going to refresh browser in fnpCommonClickTopMainMenu due to this exception -"
							+ t.getMessage());
					fnpMymsg("");
					fnpMymsg("");
					fnpMymsg("");
					fnpMymsg("");
					fnpMymsg("");

					driver.navigate().refresh();
					Thread.sleep(10000);
				}

			}
		}
	}

	/***********
	 * Wait till main loading icon 'Generating Quote' in Proposals overs
	 *************/
	public static void fnpLoadingGeneratingQuote() throws Throwable {
		int i = 0;

		while (true) {
			if (fnpCheckElementDisplayedImmediately("LoadingImg")) {
				fnpMymsg("@@@   first while Generating Quote loading is visible - fnpLoadingGeneratingQuote--" + i);

				break;
			} else {
				fnpMymsg("@@@    waiting for Generating Quote  loading visible inside - fnpLoadingGeneratingQuote--"
						+ i);

				Thread.sleep(1000);
				i++;
				if (i > 3) {
					// throw new Exception
					// ("loading image not visible after 3 seconds");

					// fnpMymsg("@@@ main loading icon not visible ,so trying again --"+
					// i);
					break;

				}
			}
		}

		i = 0;
		while (fnpCheckElementDisplayedImmediately("LoadingImg")) {
			Thread.sleep(1000);
			i++;
			// fnpMymsg("@@@ Generating Quote main loading image is present having src
			// contains loading - LoadingImg--"
			// + i);
			fnpMymsg("@@@   Generating Quote main  loading image is present  - LoadingImg--" + i);
			if (i > Integer.parseInt(CONFIG.getProperty("genMax_waitTime")) * 2) {
				break;

			}

		}

		fnpCheckError("");

	}

	public static int fnpFindNoOfElementsWithThisXpath(String xpathKey) {

		int size = 0;
		try {
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.MILLISECONDS);

			size = fnpGetORObject_list(xpathKey, 0, 1).size();
		} catch (Throwable t) {
			//
			size = 0;
		}

		driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")),
				TimeUnit.SECONDS);
		return size;
	}

	public static int fnpFindNoOfElementsWithThisXpath_NOR(String xpath) {

		int size = 0;
		try {
			driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
			// size = driver.findElements(By.xpath(xpath)).size();
			size = fnpGetORObject_list_NOR(xpath, 1).size();
		} catch (Throwable t) {
			//
			size = 0;
		}

		driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")),
				TimeUnit.SECONDS);
		return size;
	}

	public static int fnpFindNoOfMessage(String xpat) {

		int size = 0;
		try {
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.MILLISECONDS);
			size = driver.findElements(By.xpath(OR.getProperty(xpat))).size();
		} catch (Throwable t) {
			//
			size = 0;
		}

		driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")),
				TimeUnit.SECONDS);
		return size;
	}

	// Function to wait for element
	public static void fnpWaitForVisibleHavingMultipleElements(String XpathKey) throws Exception {

		try {

			for (int wait = 0; wait < Integer.parseInt(CONFIG.getProperty("genMax_waitTime")); wait++) {
				try {
					if (fnpCheckElementDisplayedImmediately(XpathKey)) {
						fnpMymsg("Element is Visible having Xpath  >> " + XpathKey);
						break;
					} else {
						Thread.sleep(1000);
					}
				} catch (Throwable t) {
					// nothing to do
				}
			} // for loop Closed

		}

		catch (Throwable e) {
			isTestCasePass = false;
			fnpDesireScreenshot(XpathKey);
			msg = "Element is not Visible having Xpath  >> " + XpathKey + ", plz see screenshot [ " + "" + XpathKey
					+ " ]. Getting Exception >> " + e.getMessage();
			fnpMymsg(msg);
			throw new Exception(msg);

		}
	}

	public static void fnpMove_To_Element_and_DoubleClick_NOR(String XpathKey) throws Exception {

		int whileloopcounter = 0;
		while (true) {
			try {
				fnpMymsg("@  ---Going to  Double Click on Element having Xpath >> " + XpathKey);
				whileloopcounter++;
				Actions action = new Actions(driver);
				WebElement Element = fnpGetORObjectX___NOR(XpathKey);
				action.moveToElement(Element).doubleClick().build().perform();
				fnpMymsg("PASSED == Double Click done on Element having Xpath >> " + XpathKey);
				break;
			} catch (org.openqa.selenium.InvalidSelectorException is) {
				fnpMymsg(is.getMessage());
				Thread.sleep(1000);
				if (whileloopcounter > Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					throw new Exception(is.getMessage());

				}
			} catch (org.openqa.selenium.StaleElementReferenceException s) {
				if (whileloopcounter < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					Thread.sleep(1000);
					fnpMymsg(whileloopcounter
							+ "In staleElementException catch block of fnpMove_To_Element_and_DoubleClick_NOR function for "
							+ XpathKey);
					continue;
				} else {
					throw s;
				}

			} catch (Throwable e) {
				isTestCasePass = false;
				fnpDesireScreenshot("DoubleClickFail");
				msg = "FAILED == Unable to MoveToElement and Click, having Xpath  >> " + XpathKey
						+ ", plz see screenshot [DoubleClickFail" + "]. Getting Exception >> "
						+ e.getMessage();
				fnpMymsg(msg);
				throw new Exception(msg);
			}
		}

	}

	public static List<WebElement> fnpReturnFindElementsList_NOR(String xpath) throws Throwable {

		List<WebElement> elementList1 = new ArrayList<WebElement>();
		int whileloopcounter = 0;
		while (true) {
			whileloopcounter++;
			try {
				driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

				elementList1 = fnpGetORObject_list_NOR(xpath, 1);
				return elementList1;
			} catch (Throwable t) {

				if (whileloopcounter > Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					throw new Exception(t.getMessage());

				}
				// Thread.sleep(1000); because it will take default implicit
				// wait for wait in findElements function , so not need to
				// mention here

			} finally {
				driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")),
						TimeUnit.SECONDS);
			}
		}

	}

	// Clicking on Menu Ajax Link
	public static void fnpAjax_Link_Click_By_Passing_TwoAjaxXPathx(String FirstAjaxXpaths, String SecondAjaxXpaths)
			throws Exception {
		int iwhileCounter = 0;

		while (true) {
			try {

				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				iwhileCounter++;

				Thread.sleep(500);
				WebElement First_Element = driver.findElement(By.xpath(OR.getProperty(FirstAjaxXpaths)));
				Actions action = new Actions(driver);
				action.moveToElement(First_Element).build().perform();

				Thread.sleep(500);

				WebElement CreateContractorApplicant = driver.findElement(By.xpath(OR.getProperty(SecondAjaxXpaths)));
				action.moveToElement(CreateContractorApplicant).click().build().perform();

				fnpMymsg("PASSED == Successfully Click on <" + (SecondAjaxXpaths) + ">.");
				driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")),
						TimeUnit.SECONDS);

				fnpLoading_wait();
				break;
			} catch (Throwable t) {
				if (iwhileCounter > 5) {
					isTestCasePass = false;
					isTestPass = false;
					fnpDesireScreenshot(SecondAjaxXpaths + "_Fail");
					msg = "FAILED == Clicking on <" + (SecondAjaxXpaths) + "> Failed, plz see screenshot ["
							+ SecondAjaxXpaths + "_Fail" + "]" + ". Getting Exception  >> "
							+ t.getMessage().trim();
					fnpMymsg(msg);
					throw new Exception(msg);

				} else {

					fnpMymsg("@  --- going to refresh because due to throwable exception caught ie. ---"
							+ t.getMessage());
					driver.navigate().refresh();
				}
			}

			finally {
				driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")),
						TimeUnit.SECONDS);
			}
		}

	}

	public static void fnpCommoniPulseSearchFunctionality2(String testCaseName, String testCaseNameLinkOR,
			String searchFieldOR, String searchColName,
			String viewSearchTopHeadingOR, String constantViewSearchHeading) throws Throwable {

		fnpMymsg(" **************************************************************");
		fnpMymsg(" Executing " + testCaseName);
		fnpMymsg("****************************************************************");

		try {

			int rowNum = currentSuiteXls.getCellRowNum("Test Cases", "TCID", classNameText);

			// fnpCommonClickTopMainMenu("Menu","FirstRefrenceLinkJustBelowToMenu",testCaseNameLinkOR);

			fnpClick_OR("MainSearchButton");

			String noToBeSearch = fnpFetchFromStSearchTable(1, 1);
			int j = 0;
			// fnpLoading_wait();
			while (noToBeSearch.contains("No records found") && j < 15) {
				j++;
				Thread.sleep(1000);
				noToBeSearch = fnpFetchFromStSearchTable(1, 1);
			}

			noToBeSearch = fnpFetchFromStSearchTable(1, searchColName);

			String afterRemovingSearchFromTestCaseName = testCaseName.replace("Search", "");

			// fnpMymsg("just before picking value from table");
			fnpMymsg(" Picked first " + afterRemovingSearchFromTestCaseName
					+ " no. from the default search table to be searched and currently it is ---" + noToBeSearch
					+ " .  ");

			fnpType("OR", searchFieldOR, noToBeSearch);
			fnpMymsg("Just after inserting value in " + searchFieldOR);
			fnpClick_OR("MainSearchButton");

			int countRows = fnpCountRowsInTable("StandardSearchTable");

			j = 1;
			while (countRows < 1) {
				if (j > 15) {
					break;
				}
				j++;
				Thread.sleep(1000);
				countRows = fnpCountRowsInTable("StandardSearchTable");
			}

			String s = null;
			String firstResult = null;
			for (int i = 1; i < countRows + 1; i++) {

				s = fnpFetchFromStSearchTable(i, searchColName);
				fnpMymsg(i + " row  for column name '" + searchColName + "' has value --- i.e. --'" + s + "'. ");

				if (i == 1) {
					firstResult = s;
				}
				if (!(s.contains(noToBeSearch))) {
					msg = testCaseName + " is not run successfully as retrieved results are not proper because " + i
							+ " row  for column name '" + searchColName + "' i.e. --'"
							+ s + "' does not contain '" + noToBeSearch + "'. ";
					fnpMymsg(msg);
					throw new Exception(msg);
				}
			}

			fnpMymsg(testCaseName + " is run successfully as it retrieved the search result successfully.");

			fnpMymsg("Now going to click hyperlinked searched result.");

			fnpClickALinkInATable(firstResult);

			fnpLoading_wait();
			fnpCheckError("");

			String viewHeading = fnpGetText_OR(viewSearchTopHeadingOR);

			try {
				Assert.assertTrue(viewHeading.contains(constantViewSearchHeading),
						"View " + afterRemovingSearchFromTestCaseName + " Page heading does not contain '"
								+ constantViewSearchHeading + "' word, so might be View "
								+ afterRemovingSearchFromTestCaseName + "  page is not opened correctly.");
			} catch (Throwable t) {
				throw new Exception(t);
			}

			fnpMymsg("After clicking hyperlink searched " + afterRemovingSearchFromTestCaseName
					+ "  no. from searched result page , View " + afterRemovingSearchFromTestCaseName
					+ "  page is opened correctly. ");

		} catch (Throwable t) {

			fail = true;
			isTestPass = false;
			fnpCommonFinalCatchBlock(t, "  iPulse or " + testCaseName + "  is failed . ", testCaseName + "_Fail");

		}

	}

	public static void fnpCommoniPulseSearchFunctionality(String testCaseName, String testCaseNameLinkOR,
			String searchFieldOR, String searchColName) throws Throwable {

		fnpMymsg(" **************************************************************");
		fnpMymsg(" Executing " + testCaseName);
		fnpMymsg("****************************************************************");

		try {

			int rowNum = currentSuiteXls.getCellRowNum("Test Cases", "TCID", classNameText);

			// fnpCommonClickTopMainMenu("Menu","FirstRefrenceLinkJustBelowToMenu",testCaseNameLinkOR);

			fnpClick_OR("MainSearchButton");

			String noToBeSearch = fnpFetchFromStSearchTable(1, 1);
			int j = 0;
			// fnpLoading_wait();
			while (noToBeSearch.contains("No records found") && j < 15) {
				j++;
				Thread.sleep(1000);
				noToBeSearch = fnpFetchFromStSearchTable(1, 1);
			}

			noToBeSearch = fnpFetchFromStSearchTable(1, searchColName);

			String afterRemovingSearchFromTestCaseName = testCaseName.replace("Search", "");

			// fnpMymsg("just before picking value from table");
			fnpMymsg(" Picked first " + afterRemovingSearchFromTestCaseName
					+ " no. from the default search table to be searched and currently it is ---" + noToBeSearch
					+ " .  ");

			fnpType("OR", searchFieldOR, noToBeSearch);
			fnpMymsg("Just after inserting value in " + searchFieldOR);
			fnpClick_OR("MainSearchButton");

			int countRows = fnpCountRowsInTable("StandardSearchTable");

			j = 1;
			while (countRows < 1) {
				if (j > 15) {
					break;
				}
				j++;
				Thread.sleep(1000);
				countRows = fnpCountRowsInTable("StandardSearchTable");
			}

			String s = null;
			String firstResult = null;
			for (int i = 1; i < countRows + 1; i++) {

				s = fnpFetchFromStSearchTable(i, searchColName);
				fnpMymsg(i + " row  for column name '" + searchColName + "' has value --- i.e. --'" + s + "'. ");

				if (i == 1) {
					firstResult = s;
				}
				if (!(s.contains(noToBeSearch))) {
					msg = testCaseName + " is not run successfully as retrieved results are not proper because " + i
							+ " row  for column name '" + searchColName + "' i.e. --'"
							+ s + "' does not contain '" + noToBeSearch + "'. ";
					fnpMymsg(msg);
					throw new Exception(msg);
				}
			}

			fnpMymsg(testCaseName + " is run successfully as it retrieved the search result successfully.");

			fnpCheckError("");

		} catch (Throwable t) {

			fail = true;
			isTestPass = false;
			fnpCommonFinalCatchBlock(t, "  iPulse or " + testCaseName + "  is failed . ", testCaseName + "_Fail");

		}

	}

	public static void fnpCommonLoginPart_old(String className) throws Throwable {

		start = new Date();

		if (!IsBrowserPresentAlready) {
			IsBrowserPresentAlready = fnpLaunchBrowserAndLogin();

		} else {
			fnpMymsg("@  ----Browser is not already present, something error happened.");
		}

		fnpClickAtTopWorkAroundForClickingMenu();

		fnpWaitForVisible_usingLinkNameInOR("Menu");

		WebElement menu = fnpGetORObjectX_usingLinkText("Menu");
		Actions action = new Actions(driver);
		action.moveToElement(menu).build().perform();

		if (className.equalsIgnoreCase("BulkAssign_Unassign_To_Assign")
				|| className.equalsIgnoreCase("BulkAssign_ChangeAssignTo")) {

			if (fnpGetCurrRunningBrowserName().equalsIgnoreCase("IE")) {
				menu.sendKeys(Keys.ARROW_DOWN);
				Thread.sleep(1000);
			}

			WebElement menu3 = fnpGetORObjectX_usingLinkText("BulkAssignmentLink");
			Actions action3 = new Actions(driver);
			action3.moveToElement(menu3).build().perform();

			fnpGetORObjectX_usingLinkText("BulkAssignmentLink").click();
			fnpLoading_wait();
			// fnpLoading_wait();

			fnpLoadHashData(hashXlData, Work_Order_suitexls, className, 2);
			return;

		}

		if (fnpGetCurrRunningBrowserName().equalsIgnoreCase("IE")) {
			menu.sendKeys(Keys.ARROW_DOWN);
			Thread.sleep(1000);
		}
		fnpGetORObjectX_usingLinkText("CreateWorkOrderLink").click();

		fnpLoading_wait();
		fnpLoadHashData(hashXlData, Work_Order_suitexls, className, 2);

	}

	public static void fnpCommonLoginPart(String className) throws Throwable {

		start = new Date();
		fnpMymsg("Start date is --" + start);

		if (!IsBrowserPresentAlready) {
			IsBrowserPresentAlready = fnpLaunchBrowserAndLogin();
			// fnpMymsg(" Browser is launched");
		} else {
			fnpMymsg("@  ----Browser is not already present, something error happened.");
		}

		if (className.equalsIgnoreCase("BulkAssign_Unassign_To_Assign")
				|| className.equalsIgnoreCase("BulkAssign_ChangeAssignTo")) {
			fnpCommonClickTopMainMenu("Menu", "FirstRefrenceLinkJustBelowToMenu", "BulkAssignmentLink",
					"WorkLoadPlannerList");

			fnpLoadHashData(hashXlData, currentSuiteXls, className, 2);
			return;

		}

		fnpCommonClickTopMainMenu("Menu", "FirstRefrenceLinkJustBelowToMenu", "CreateWorkOrderLink", "ClientLKPBtn");

		// fnpLoadHashData(hashXlData, currentSuiteXls, className, 2);

	}

	/******* Check error using page source ****/
	public static void fnpCheckErrorUsingPageSource_Oasis() throws Throwable {

		try {
			String PageSourceText = driver.getPageSource().toLowerCase();

			if (PageSourceText.contains("#ff0000")) {

				List<WebElement> errMsgList;

				errMsgList = fnpGetORObject_list("Oasis_error_message_xpath", 5);
				int sizeErrMsgList = errMsgList.size();

				driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);
				for (Iterator iterator = errMsgList.iterator(); iterator.hasNext();) {

					try {
						WebElement webElement = (WebElement) iterator.next();

						if (webElement.isDisplayed()) {
							// fnpCheckError_Oasis("");

							fnpMymsg("@  ---running function fnpCheckError_Oasis");
							if (fnpCheckElementDisplayedImmediately("Oasis_error_message_xpath")) {

								fnpMouseHover_OR_EvenMultipleObjectsHavingSameXpath_usingSpecialForErrorClassOnly(
										"Oasis_error_message_xpath");
								String errMsg = fnpGetText_OR_EvenMultipleObjectsHavingSameXpath_specialForExtractingErrorMsgTextOnlyEvenIfNotVisible(
										"Oasis_error_message_xpath");

								if ((errMsg.length() > 3) & (!(errMsg.equalsIgnoreCase("* Lead Audit")))) {
									fnpMymsg(" Error is thrown by Oasis  application " + errMsg);
									throw new Exception(
											" Error is thrown by application. " + "\n\n Error is --->" + errMsg);
								}

							}

						}
					} catch (org.openqa.selenium.StaleElementReferenceException s) {
						// nothing to do
					} catch (org.openqa.selenium.NoSuchWindowException N) {
						// nothing to do

					} finally {
						driver.manage().timeouts().implicitlyWait(
								Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);

					}

				}

			}

			if (PageSourceText.contains("#ff0000")) {

				List<WebElement> errMsgList;

				errMsgList = fnpGetORObject_list("Oasis_error_message_xpath2", 5);

				int sizeErrMsgList = errMsgList.size();
				fnpMymsg("   --sizeErrMsgList of Oasis (Oasis error count) Oasis_error_message_xpath2 --"
						+ sizeErrMsgList);

				driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);
				for (Iterator iterator = errMsgList.iterator(); iterator.hasNext();) {

					try {
						WebElement webElement = (WebElement) iterator.next();

						fnpMymsg("   ---Oasis error ----is displayed? --" + webElement.isDisplayed());
						if (webElement.isDisplayed()) {

							fnpMymsg("@  ---running function fnpCheckError_Oasis");
							if (fnpCheckElementDisplayedImmediately("Oasis_error_message_xpath2")) {

								fnpMouseHover_OR_EvenMultipleObjectsHavingSameXpath_usingSpecialForErrorClassOnly(
										"Oasis_error_message_xpath2");
								String errMsg = fnpGetText_OR_EvenMultipleObjectsHavingSameXpath_specialForExtractingErrorMsgTextOnlyEvenIfNotVisible(
										"Oasis_error_message_xpath2");

								// if (errMsg.length() > 3) {
								if ((errMsg.length() > 3) & (!(errMsg.equalsIgnoreCase("* Lead Audit")))) {
									fnpMymsg(" Error is thrown by Oasis  application " + errMsg);
									// htmlSubMessage=htmlSubMessage+"<Font Color=Red> Error is thrown by
									// application in --"+classNameText+" i.e. '"+errMsg+"' </Font> ";
									throw new Exception(
											" Error is thrown by application. " + "\n\n Error is --->" + errMsg);

								}

							}

						}
					} catch (org.openqa.selenium.StaleElementReferenceException s) {
						// nothing to do
					} catch (org.openqa.selenium.NoSuchWindowException N) {
						// nothing to do

					} finally {
						driver.manage().timeouts().implicitlyWait(
								Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);

					}

				}

			}

			if (PageSourceText.contains("diverror")) {

				List<WebElement> errMsgList;

				errMsgList = fnpGetORObject_list("Oasis_error_inDiv", 5);

				int sizeErrMsgList = errMsgList.size();

				driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);
				for (Iterator iterator = errMsgList.iterator(); iterator.hasNext();) {

					try {
						WebElement webElement = (WebElement) iterator.next();

						if (webElement.isDisplayed()) {
							// fnpCheckError_Oasis("");

							fnpMymsg("@  ---running function fnpCheckError_Oasis");
							if (fnpCheckElementDisplayedImmediately("Oasis_error_inDiv")) {

								fnpMouseHover_OR_EvenMultipleObjectsHavingSameXpath_usingSpecialForErrorClassOnly(
										"Oasis_error_inDiv");
								String errMsg = fnpGetText_OR_EvenMultipleObjectsHavingSameXpath_specialForExtractingErrorMsgTextOnlyEvenIfNotVisible(
										"Oasis_error_inDiv");

								if (errMsg.length() > 3) {
									fnpMymsg(" Error is thrown by Oasis  application " + errMsg);
									// htmlSubMessage=htmlSubMessage+"<Font Color=Red> Error is thrown by
									// application in --"+classNameText+" i.e. '"+errMsg+"' </Font> ";
									throw new Exception(
											" Error is thrown by application. " + "\n\n Error is --->" + errMsg);

								}

							}

						}
					} catch (org.openqa.selenium.StaleElementReferenceException s) {
						// nothing to do
					} catch (org.openqa.selenium.NoSuchWindowException N) {
						// nothing to do

					} finally {
						driver.manage().timeouts().implicitlyWait(
								Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);

					}

				}

			}
		} catch (org.openqa.selenium.WebDriverException we) {
			String errorMessagg = we.getMessage();
			if (errorMessagg.contains("Error determining if element is displayed")) {
				// nothing to do
				fnpMymsg("@  ---@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
				fnpMymsg(
						"@  ---Error is thrown by webDriver in function fnpCheckErrorUsingPageSource_Oasis. And error is --"
								+ errorMessagg);
				fnpMymsg("@  ---@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			} else {
				throw we;
			}

		}

	}

	public static void fnpIpulseDuringLoading() throws Throwable {

		int i = 0;
		while (fnpCheckElementDisplayedImmediately("ProgressImageIcon")) {
			Thread.sleep(1000);
			i++;
			fnpMymsg("@  ------fnpCheckElementDisplayedImmediately 'ProgressImageIcon'------" + i);
			fnpMymsg("@@@    loading is visible ---" + i);
			if (i > Integer.parseInt(CONFIG.getProperty("genMax_waitTime")) * 2) {
				break;

			}

		}

		i = 0;
		while (fnpCheckElementDisplayedImmediately("LoadingImg")) {
			fnpMymsg("@@@   while loop when loading is visible now inside - fnpLoading_waitInsideDialogBox--" + i);
			Thread.sleep(1000);
			i++;
			int maxT = Integer.parseInt(CONFIG.getProperty("loadingIcon_Max_waitTime"));
			if (i > maxT) {
				throw new Exception("Loading is not getting over even after waiting approx.  " + maxT + " seconds");
				// break;
			}

		}

	}

	public static String fnpReturnText_notCallItDirectly(WebElement w) {
		String returnText = "";
		try {
			returnText = w.getText();
		} catch (org.openqa.selenium.WebDriverException we) {
			String exceptMessage = we.getMessage();
			if (exceptMessage.contains("Unable to get element text")) {
				returnText = "";

			} else {
				// throw we; //commenting this on 03-05-2018 as sometime w element throw
				// staleElementException
			}
		}

		return returnText;

	}

	public static void fnpCheckErrorPageNotFound() throws Throwable {
		String pageSource = driver.getPageSource().toString().toLowerCase();
		String notFoundError = "Error 404--Not Found".toLowerCase();
		String notFoundError2 = "Error 404".toLowerCase();
		String notFoundError3 = "404 Not Found".toLowerCase();
		if (pageSource.contains(notFoundError) || pageSource.contains(notFoundError2)
				|| pageSource.contains(notFoundError3)) {
			Thread.sleep(2000);
			throw new Exception("Error 404--Not Found");
		}
	}

	public static String fnpGetIPAddress() throws Throwable {

		InetAddress ipAddr = InetAddress.getLocalHost();
		int ipAddrLen = ipAddr.toString().length();
		String last2CharactersOfIpAddress = ipAddr.toString().substring((ipAddrLen - 2), ipAddrLen);
		// Reporter.log("Current running IP address of machine is--" +
		// last2CharactersOfIpAddress);
		return last2CharactersOfIpAddress;

	}

	public static String fnpFormatReplaceSpecailCharacters(String msg) {

		String afterFormat = "";
		try {

			msg = msg.replaceAll("ï¿½", "");
			msg = msg.replaceAll("ï¿½", "");
			msg = msg.replaceAll("ï¿½", "");
			msg = msg.replaceAll("@", "");
			msg = msg.replaceAll("ï¿½", "");

			afterFormat = msg;

		} catch (Throwable t) {

		}

		return afterFormat;

	}

	/***********
	 * To select the value from prime faces list that contains matching value
	 *************/
	public static void fnpPFListModify_NOR_version1(String Xpath, String value) throws Throwable {

		int whileCounter = 0;
		String listValue = null;
		String FinalSelectedValue = null;

		while (true) {
			whileCounter++;

			try {
				fnpWaitForVisible_NotInOR(Xpath);
				fnpWaitTillVisiblityOfElementNClickable_NOR(Xpath);

				String defaultValue = fnpGetORObjectX___NOR(Xpath).getText().trim();

				if ((!defaultValue.equalsIgnoreCase("")) & (defaultValue.contains(value))) {
					// throw new Exception("Excel value --'" + value +
					// "' is not matched with DropDown Value.");
					fnpMymsg("@  - default value is same as expected, so returning back.");
					return;
				}

				fnpGetORObjectX___NOR(Xpath).click();
				Thread.sleep(1500);

				String xpathforPanel = Xpath.replace("_label", "_panel");

				listValue = xpathforPanel + "/div/ul/li[contains(@data-label,'" + value + "')]";

				fnpWaitForVisible_NotInOR(listValue, 2);

				fnpWaitTillVisiblityOfElementNClickableInsidePFList(listValue);
				driver.findElement(By.xpath(listValue)).click();
				Thread.sleep(500);

				if (whileCounter > 1) {
					Thread.sleep(3000);
				}

				try {
					if (fnpCheckElementEnabledImmediately("FooterElement")) {
						fnpMymsg("@  - Footer element is enabled.");
						fnpMouseHover("FooterElement");
						fnpGetORObjectX("FooterElement").click();
					}
				} catch (Throwable t) {
					// nothing to do
				}

				fnpMymsg("@  -  Now going to fetch the selected value.");

				FinalSelectedValue = fnpGetORObjectX___NOR(Xpath).getText().trim();
				fnpMymsg("@  -  Fetched/Selected value is ---" + FinalSelectedValue);

				if (!(FinalSelectedValue.contains(value))) {
					msg = "@  - '" + value + "'  is NOT selected properly in  DropDown as expected is" + "--'" + value
							+ "' and actual is --'" + FinalSelectedValue + "' .";
					fnpMymsg(msg);
					throw new Exception(msg);
				} else {
					fnpMymsg("@  - '" + value + "'  is  selected properly in  DropDown as expected is" + "--'" + value
							+ "' and actual is --'" + FinalSelectedValue + "' .");
				}

				break;

			} catch (Exception e) {
				fnpMymsg("@  -Going to check conditions that message contains 'is NOT selected properly in  DropDown'");
				fnpMymsg("@  ---condition value is ---"
						+ e.getMessage().contains("is NOT selected properly in  DropDown"));
				fnpMymsg("@  ---condition value is ---"
						+ e.getMessage().toString().contains("is NOT selected properly in  DropDown"));
				fnpMymsg(
						"@  =%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
				fnpMymsg(e.getMessage());
				fnpMymsg(
						"@  =%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");

				if (whileCounter > 5) {
					fnpDesireScreenshot("NotSelectingValueProperly--" + value);
					throw new Exception("After trying " + whileCounter + " times," + value
							+ "'  is NOT selected properly in  DropDown as expected is" + "--'" + value
							+ "' and actual is --'" + FinalSelectedValue
							+ "' ,plz see screenshot [NotSelectingValueProperly--" + value + SShots + "]");
				} else {
					fnpMymsg("@  - trying again " + whileCounter + " time  to select properly");
					// if list is opened, then first close it and try again
					if (fnpCheckElementDisplayedImmediately_NOR(listValue)) {
						fnpGetORObjectX___NOR(Xpath).click();
					}

				}

			}
		}

	}

	/***********
	 * To select the value from prime faces list that contains matching value
	 *************/
	public static void fnpPFListModify_NOR(String Xpath, String value) throws Throwable {

		value = value.trim();

		String defaultValue = fnpGetORObjectX___NOR(Xpath).getText().trim();

		if ((!defaultValue.equalsIgnoreCase("")) & (defaultValue.contains(value))) {

			fnpMymsg("@  - default value is same as expected, so returning back.");
			return;
		}

		try {
			String temp = value;
			fnpMymsg("Going to select this value ---'" + value + "'  .");
			// fnpWaitForVisible_NOR(Xpath);
			fnpWaitTillVisiblityOfElementNClickable_NOR(Xpath);
			fnpGetORObjectX___NOR(Xpath).click();
			Thread.sleep(500);

			String xpathforPanel = Xpath.replace("_label", "_panel");

			String listValue = xpathforPanel + "/div/ul/li[contains(@data-label,'" + value + "')]";

			String tagname = null;
			boolean ValueMatched = false;
			boolean found = false;

			try {
				// throw new Exception();

				// if (fnpCheckElementDisplayedImmediately_NOR(listValue)) {
				if (fnpCheckElementDisplayed_NOR(listValue, 1)) {
					// nothing to do
				} else {
					fnpMymsg("This value '" + value + "'  is not present as it is -- so going to lower its case");
					String new_value = value.toLowerCase();
					listValue = xpathforPanel + "/div/ul/li[contains(@data-label,'" + new_value + "')]";
					if (fnpCheckElementDisplayedImmediately_NOR(listValue)) {
						// nothing to do
						value = new_value;
					} else {
						fnpMymsg("Now again this value '" + new_value
								+ "'  is not present as it is -- so going to make its first digit in uppercase.");

						new_value = fnpReturnAStringWholeFirstCharacterIsInUpperCase(new_value);

						listValue = xpathforPanel + "/div/ul/li[contains(@data-label,'" + new_value + "')]";
						if (fnpCheckElementDisplayedImmediately_NOR(listValue)) {
							value = new_value;
						} else {
							fnpMymsg("Now again this value '" + new_value
									+ "'  is not present as it is -- so going to make its all letters in upper case.");
							new_value = value.toUpperCase();
							value = new_value;
							listValue = xpathforPanel + "/div/ul/li[contains(@data-label,'" + new_value + "')]";
							if (fnpCheckElementDisplayedImmediately_NOR(listValue)) {
								value = new_value;
							} else {
								fnpMymsg("Now again this value '" + new_value
										+ "'  is not present as it is -- so going to check once again (once more time)  the initial value as it is i.e. '"
										+ temp + "'  .");
								value = temp; // to check once again (once more
												// time) the initial value as it
												// is
							}
						}

					}

				}

				listValue = xpathforPanel + "/div/ul/li[contains(@data-label,'" + value + "')]";
				fnpMymsg("@  - going to click expected option value --" + value);

				fnpWaitTillVisiblityOfElementNClickable_NOR(listValue);// new
																		// change
																		// on
																		// 31-08-16

				listValue = xpathforPanel + "/div/ul/li[contains(@data-label,'" + value + "')]";
				// fnpClick_NOR_withoutWait(listValue);//Commented on 31-8-16
				// due to multiple status list in certDecUpdate AI

				// fnpGetORObjectX_WhenMultipleObjectsHavingSameXpath_NOR(listValue,value).click();//
				// new change on 31-08-16
				fnpGetORObjectX_WhenMultipleObjectsHavingSameXpath_NOR_immediately(listValue, value).click();// new
																												// change
																												// on
																												// 11-09-17

				found = true;
				fnpMymsg("@  -  clicked expected option value  in try block.");

				Thread.sleep(1000);
				// fnpWaitTillVisiblityOfElementNClickable_OR(XpathKey);

				try {
					if (fnpCheckElementEnabledImmediately("FooterElement_id")) {
						fnpMymsg("@  - Footer element is enabled.");
						fnpMouseHover("FooterElement_id");
						fnpGetORObjectX("FooterElement_id").click();
					}
				} catch (Throwable t) {
					// nothing to do
				}

				fnpMymsg("@  -  Now going to fetch the selected value.");

				String FinalSelectedValue = fnpGetText_NOR(Xpath).trim();

				fnpMymsg("@  -  Fetched/Selected value is ---" + FinalSelectedValue);

				if (!(FinalSelectedValue.contains(value))) {
					// throw new Exception("Excel value --'" + value +
					// "' is not matched with DropDown Value.");
					msg = "@  - '" + value + "'  is NOT selected properly in  DropDown as expected is" + "--'" + value
							+ "' and actual is --'" + FinalSelectedValue + "' .";
					fnpMymsg(msg);
					throw new Exception(msg);
				} else {
					fnpMymsg("@  - '" + value + "'  is  selected properly in  DropDown as expected is" + "--'" + value
							+ "' and actual is --'" + FinalSelectedValue + "' .");
				}

			} catch (Throwable t) {

				listValue = xpathforPanel + "/div/ul/li[contains(@data-label,'" + value + "')]";

				fnpMymsg(
						"@  - fnpPFList is failed due to some error but try again in catch block. Previous error caught was ---"
								+ t.getMessage());

				fnpMymsg("@  - So processing further in catch block again. ");

				if (!fnpCheckElementDisplayedImmediately_NOR(listValue)) {
					fnpMymsg("@  - list value '" + listValue + "' is not displayed, so clicking again the PF List.");
					Thread.sleep(1000);
					fnpClick_NOR_withoutWait(Xpath);
					Thread.sleep(1000);
				} else {
					fnpMymsg("@  - list value '" + listValue + "' is  displayed, so NOT clicking again the PF List.");
				}

				fnpMymsg("@  ---in catch  block of fnpPFList");

				if (fnpCountChildElementPresenceImmediately_NotInOR(fnpGetORObjectX___NOR(xpathforPanel), "li") > 0) {
					List<WebElement> objectlist5 = fnpGetORObjectX___NOR(xpathforPanel).findElements(By.tagName("li"));
					fnpMymsg("@  ---total list elements are--" + objectlist5.size());
					tagname = "li";
					fnpMymsg(" li tag is present for this list. ");
					int totalvaluesinDropDown = objectlist5.size();
					// System.out.println("@ ---going to fetch visible selected value");
					fnpMymsg("@  ---going to fetch visible selected value");

					String VisibleselectedOption = driver
							.findElement(By.xpath(xpathforPanel + "//li[contains(@class,'ui-state-highlight')]"))
							.getText();

					// System.out.println("@ ---selected value is ---" +
					// VisibleselectedOption);
					fnpMymsg("@  ---selected value is ---" + VisibleselectedOption);
					int iCurrentPositionIndex = 0;
					String whileloopCurrentValue = "";
					while (true) {

						whileloopCurrentValue = driver
								.findElement(By.xpath(xpathforPanel + "//li[" + (iCurrentPositionIndex + 1) + "]"))
								.getText();

						if ((VisibleselectedOption.equalsIgnoreCase(whileloopCurrentValue))) {
							break;
						}
						iCurrentPositionIndex++;

						if ((iCurrentPositionIndex + 1) > totalvaluesinDropDown) {
							break;
						}
					}
					// System.out.println("@ ---iCurrentPosition is ----" +
					// (iCurrentPositionIndex + 1));
					fnpMymsg("@  ---iCurrentPosition is ----" + (iCurrentPositionIndex + 1));

					int iExpectedPositionIndex = 0;
					whileloopCurrentValue = "";
					while (true) {

						whileloopCurrentValue = objectlist5.get(iExpectedPositionIndex).getText();

						if ((value.equalsIgnoreCase(whileloopCurrentValue))) {
							break;
						}
						iExpectedPositionIndex++;
						if ((iCurrentPositionIndex + 1) > totalvaluesinDropDown) {
							break;
						}
					}

					fnpMymsg("@  ---iExpectedPosition is ----" + (iExpectedPositionIndex + 1));

					if (iExpectedPositionIndex > (iCurrentPositionIndex + 1)) {

						fnpMymsg("@  --- Expected position is higher");

						int innerwhilecounter = 0;
						while (iExpectedPositionIndex != (iCurrentPositionIndex + innerwhilecounter + 2)) {

							new Actions(driver)
									.moveToElement(objectlist5.get(iCurrentPositionIndex + innerwhilecounter - 2))
									.build().perform();
							new Actions(driver)
									.moveToElement(objectlist5.get(iCurrentPositionIndex + innerwhilecounter - 2))
									.sendKeys(Keys.ARROW_DOWN).build().perform();
							// Thread.sleep(100);

							fnpMymsg("@  -iCurrentPosition value is --" + (iCurrentPositionIndex + 1));
							fnpMymsg("@  -innerwhilecounter value is --" + innerwhilecounter);

							int sum = iCurrentPositionIndex + innerwhilecounter;
							String pp = driver.findElement(By.xpath(xpathforPanel + "//li[" + sum + "]")).getText();
							fnpMymsg("@  -pp value is ---" + pp);

							Thread.sleep(100);

							fnpMymsg("@  - arrow down ---" + innerwhilecounter);
							innerwhilecounter++;

						}
						fnpMymsg("@  - iExpectedPosition ---" + (iExpectedPositionIndex + 1));
						new Actions(driver).moveToElement(objectlist5.get((iExpectedPositionIndex))).build().perform();
						fnpGetORObjectX___NOR(xpathforPanel + "//li[" + (iExpectedPositionIndex + 1) + "]").click();

						fnpMymsg("@  - Value clicked successfully");
						found = true;
						Thread.sleep(2000);
					} else {

						fnpMymsg("@  --- Expected position is lower");
						int innerwhilecounter = 0;
						while (iExpectedPositionIndex != (iCurrentPositionIndex - innerwhilecounter)) {

							new Actions(driver)
									.moveToElement(objectlist5.get(iCurrentPositionIndex - innerwhilecounter + 1))
									.sendKeys(Keys.ARROW_UP).build().perform();
							Thread.sleep(100);

							fnpMymsg("@  - arrow UP ---" + innerwhilecounter);
							innerwhilecounter++;

						}
						fnpGetORObjectX___NOR(xpathforPanel + "//li[" + (iExpectedPositionIndex + 1) + "]").click();

						fnpMymsg("@  - Value clicked successfully");
						found = true;
						Thread.sleep(2000);

					}

					if (found == false) {
						msg = "@  - Excel value --'" + value + "'  is not matched with DropDown Value.";
						fnpMymsg(msg);
						throw new Exception(msg);

					}

					try {
						if (fnpCheckElementEnabledImmediately("FooterElement")) {
							fnpMymsg("@  - Footer element is enabled.");
							fnpMouseHover("FooterElement");
							fnpGetORObjectX("FooterElement").click();
						}
					} catch (Throwable tt) {
						// nothing to do
					}

					String FinalSelectedValue = fnpGetText_NOR(Xpath).trim();

					if (!(FinalSelectedValue.contains(value))) {
						msg = "@  - '" + value + "'  is NOT selected properly in  DropDown as expected is" + "--'"
								+ value + "' and actual is --'" + FinalSelectedValue + "' .";
						fnpMymsg(msg);
						throw new Exception(msg);
					} else {
						fnpMymsg("@  - '" + value + "'  is  selected properly in  DropDown as expected is" + "--'"
								+ value + "' and actual is --'" + FinalSelectedValue + "' .");
					}

				}
			}

		} catch (Throwable t) {
			fnpDesireScreenshot("ValueMissingInList" + value);
			String errorMsg = t.getMessage();
			throw new Exception(errorMsg + "\n\n\n   And/OR  might be Value [" + value
					+ "] is not present in List ,plz see screenshot [ValueMissingInList" + value + SShots + "]");
		}
	}

	/*
	 * private static void fnpWaitForVisible_NOR(String xpath) {
	 * // TODO Auto-generated method stub
	 * 
	 * }
	 */
	public String[][] lookup_criteria_processing_old(String value) throws Throwable {

		String NoOfSearchCriteriaArray[] = value.split(":");

		int NoOfSearchCriteriaSize = NoOfSearchCriteriaArray.length;

		String[][] lookup = new String[NoOfSearchCriteriaSize][3];

		fnpMymsg("No. of NoOfSearchCriteriaSize are ---" + NoOfSearchCriteriaSize);
		fnpMymsg("");
		fnpMymsg("");
		String eachSearchCriteria;
		String searchCriteriaParts[];
		for (int i = 0; i < NoOfSearchCriteriaSize; i++) {
			fnpMymsg("");
			fnpMymsg("");

			eachSearchCriteria = TestSuiteBase_ISR_suite.fnpremoveFormatting(NoOfSearchCriteriaArray[i]);

			fnpMymsg("Each search criteria and its value are--" + eachSearchCriteria);

			searchCriteriaParts = eachSearchCriteria.split(",");

			for (int j = 0; j < searchCriteriaParts.length; j++) {
				lookup[i][j] = searchCriteriaParts[j];
			}

		}

		return lookup;

	}

	public static String[][] lookup_criteria_processing(String value) throws Throwable {

		String NoOfSearchCriteriaArray[] = value.split("::");

		int NoOfSearchCriteriaSize = NoOfSearchCriteriaArray.length;

		String[][] lookup = new String[NoOfSearchCriteriaSize][3];

		fnpMymsg("No. of NoOfSearchCriteriaSize are ---" + NoOfSearchCriteriaSize);
		fnpMymsg("");
		fnpMymsg("");
		String eachSearchCriteria;
		String searchCriteriaParts[];
		for (int i = 0; i < NoOfSearchCriteriaSize; i++) {
			fnpMymsg("");
			fnpMymsg("");

			eachSearchCriteria = TestSuiteBase_ISR_suite.fnpremoveFormatting(NoOfSearchCriteriaArray[i]);

			fnpMymsg("Each search criteria and its value are--" + eachSearchCriteria);

			searchCriteriaParts = eachSearchCriteria.split(":");

			for (int j = 0; j < searchCriteriaParts.length; j++) {
				lookup[i][j] = searchCriteriaParts[j];
			}

		}

		return lookup;

	}

	public static void fnpDisplayingMessageInFrame(String message, int a) throws Throwable {
		// DisappearingMessage2 dm = new DisappearingMessage2(message, a);
		// DisappearingMessage dm = new DisappearingMessage(message, a, 800, 300);
		// dm.start();
		// Thread.sleep(a*1000);
		Thread.sleep(1000);
	}

	public static void fnpDisplayingMessageInFrame_fnpMymsg(String message, int a) throws Throwable {
		// DisappearingMessage2 dm = new DisappearingMessage2(message, a);
		// DisappearingMessage dm = new DisappearingMessage(message, a, 800, 300);
		// dm.start();

	}

	public static void fnpDisplayingMessageInFrame_fnpMymsg_usingDialog(String message, int a) throws Throwable {
		// DisappearingMessage2 dm = new DisappearingMessage2(message, a);
		// DisappearingMessage_usingDialog dm = new
		// DisappearingMessage_usingDialog(message, a, 800, 300);
		// dm.start();

	}

	// Function to change the status of facility from Drop to appply from DB
	public static void fnpChangeStatusOfDropFacilityToApply(String facility, String standardCode) throws Throwable {
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

			connection = fnpGetDBConnection();

			Statement stmt = connection.createStatement();

			// String deleteQuery1 = "DELETE FROM EC_TIMESHEET_DETAILS td" +
			// " WHERE TD.TIMESHEET_SEQ IN (SELECT SEQ FROM EC_TIMESHEETS t WHERE T.EMP_NO
			// ='"
			// + empNO + "')";

			// @SuppressWarnings("SQL_PREPARED_STATEMENT_GENERATED_FROM_NONCONSTANT_STRING")

			/*
			 * String updateQuery1 = "UPDATE cus_std_xref" +
			 * "  SET LIST_STATUS_TYPE='APPLY' " +
			 * " where cus_Seq =(select seq from customers where code='" + facility + "')"
			 * + "		and std_code='" + standardCode + "' and LIST_STATUS_TYPE = 'DROP'";
			 * 
			 */
			String updateQuery1 = String.format(
					"UPDATE cus_std_xref  SET LIST_STATUS_TYPE='APPLY'  where cus_Seq =(select seq from customers where code='%s')	and std_code='%s' and LIST_STATUS_TYPE = 'DROP'",
					facility, standardCode);

			int a = stmt.executeUpdate(updateQuery1);
			fnpMymsg("@  ---updated rows for changing status of facility from DROP to APPLY are--" + a);
			connection.commit();

			/*********
			 * To run again above query twice
			 **********************************************************************/
			// @SuppressWarnings("SQL_PREPARED_STATEMENT_GENERATED_FROM_NONCONSTANT_STRING")

			/*
			 * String updateQuery2 = "UPDATE cus_std_xref" +
			 * "  SET LIST_STATUS_TYPE='APPLY' " +
			 * " where cus_Seq =(select seq from customers where code='" + facility + "')"
			 * + "		and std_code='" + standardCode + "' and LIST_STATUS_TYPE = 'DROP'";
			 * 
			 */
			/*
			 * String updateQuery2 = String.
			 * format("UPDATE cus_std_xref  SET LIST_STATUS_TYPE='APPLY'  where cus_Seq =(select seq from customers where code='%s')	and std_code='%s' and LIST_STATUS_TYPE = 'DROP'"
			 * ,facility,standardCode);
			 * 
			 * 
			 * Thread.sleep(2000);
			 * int a2 = stmt.executeUpdate(updateQuery2);
			 * fnpMymsg("@  ---updated rows for changing status of facility from DROP to APPLY are (2nd time) --"
			 * + a2);
			 * connection.commit();
			 * 
			 * Thread.sleep(2000);
			 * int a3 = stmt.executeUpdate(updateQuery2);
			 * fnpMymsg("@  ---updated rows for changing status of facility from DROP to APPLY are (3rd time) --"
			 * + a3);
			 * connection.commit();
			 * 
			 */

			/*******************************************************************************/

			// connection.close();

		} catch (SQLException e) {
			fnpMymsg("*********************************************** Failed  Database query .,   Getting Error >>  "
					+ e.getMessage().trim());
			fnpMymsg(
					"=========================================================================================================================================");
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}

		finally {
			connection.close();
		}
		fnpMymsg("******************");

		// return rate;
	}

	// Function to Launch the browser and login with user credential details
	public static boolean fnpLaunchBrowserAndSecureLogin(String loginAs) throws Exception {
		boolean present;
		try {

			fnpLaunchBrowser();
			String secureURL;

			String userName = CONFIG.getProperty("adminUsername");
			String password = CONFIG.getProperty("adminPassword");

			String siteUrl = null;

			if (excelSiteURL != null) {
				if (excelSiteURL.equalsIgnoreCase("")) {
					siteUrl = CONFIG.getProperty("WOSecureLoginSiteName");
				} else {
					// siteUrl=excelSiteURL;
					secureURL = excelSiteURL.split("LoginAsTestSiteName:")[1];
					siteUrl = secureURL;

				}
			} else {
				siteUrl = CONFIG.getProperty("WOSecureLoginSiteName");
			}

			driver.get(siteUrl);
			fnpMymsg("Navigating to url --->" + siteUrl);
			driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")),
					TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(Long.parseLong(CONFIG.getProperty("pageLoadTime")),
					TimeUnit.SECONDS);

			fnpWaitForVisible("UserName");

			fnpGetORObjectX("UserName").clear();
			fnpGetORObjectX("loginAs").clear();

			fnpType("OR", "loginAs", loginAs);

			fnpType("OR", "UserName", userName);
			Thread.sleep(500);

			fnpType("OR", "password", password);
			Thread.sleep(500);

			Thread.sleep(1000);
			fnpMouseHover("Login");
			fnpClick_OR("Login");
			fnpMymsg("Just login clicked");

			if (fnpCheckElementDisplayedImmediately("errorMessage")) {
				throw new Exception("Login is not successfull.");
			}

			fnpLoading_wait();
			// Thread.sleep(1000*10);
			fnpClick_OR("xpathForAck");
			fnpMymsg("Just after clicking Acknowledge button");
			fnpWaitTillThisElementIsNoLongerDisplayedTillMaxWait_OR("xpathForAck",
					"Acknowledge button is still visible after clicking it", 120);
			fnpMymsg("Just after waiting for Acknowledge button visiblity over");
			fnpWaitForVisible("logOutBtn2");

			present = true;

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

	/*****************
	 * New changes for sprint 9.2***using in Custom wo , modbrack wo
	 * 
	 * @throws Throwable
	 ***************************/
	public static void fnpAnnualUpdateRequiredChkbxesUsingInReviewTask() throws Throwable {

		/***************** New changes for sprint 9.2 ******************************/

		fnpLoading_wait_CheckLoadingIconOnlyIniPulse();

		String annualUpdateRequireOptions = (String) hashXlData.get("Annual_Update_Required").trim();
		String annualUpdateRequireOptionsArray[] = annualUpdateRequireOptions.split(":");
		int annualUpdateRequireOptionsArraySize = annualUpdateRequireOptionsArray.length;
		String chkBxLabelXpath;
		String chkBxXpath;
		String checkedStatusClass;
		for (int i = 0; i < annualUpdateRequireOptionsArraySize; i++) {
			chkBxLabelXpath = OR.getProperty("AnnualUpdateRequiredChkBxLabel_part1")
					+ annualUpdateRequireOptionsArray[i] + OR.getProperty("AnnualUpdateRequiredChkBxLabel_part2");

			// chkBxXpath = chkBxLabelXpath + "/../../td[1]/div/div[2]/span";
			chkBxXpath = chkBxLabelXpath + "/../div/div[2]/span";
			checkedStatusClass = driver.findElement(By.xpath(chkBxXpath)).getAttribute("class");

			fnpMymsg("Before analyzing the status of check box and class is---" + checkedStatusClass);
			if (!(checkedStatusClass.contains("ui-icon-check"))) {
				// Thread.sleep(1000);
				// driver.findElement(By.xpath(chkBxLabelXpath)).click();
				fnpMymsg("Just before clicking");
				fnpClick_NOR_withoutWait(chkBxXpath);
				fnpMymsg("Just after clicking");
				Thread.sleep(2000);

				fnpMymsg("After clicking the checkbox, its class is---" + checkedStatusClass);
				checkedStatusClass = driver.findElement(By.xpath(chkBxXpath)).getAttribute("class");
				if (!(checkedStatusClass.contains("ui-icon-check"))) {
					msg = "Checkbox is NOT selected properly for option --" + annualUpdateRequireOptionsArray[i];
					fnpMymsg(msg);
					throw new Exception(msg);
				} else {
					fnpMymsg("Checkbox is  selected properly for option --" + annualUpdateRequireOptionsArray[i]);
				}
			}

		}

		/***************** New changes for sprint 9.2 ******************************/
	}

	/********************
	 * Picking date current day and Current Month for String Passing year By
	 * Selecting values from months and years DropDpwn.
	 ************************/
	public static void fnpCalendarDatePicker_BySelectValues_From_DropDown(String DatePickForWhichDay,
			String DatePickForWhichMonth_MMMM, String DatePickForWhichYear_YYYY,
			String selectYearClassXpath, String selectMonthClassXpath) throws Throwable {
		try {
			String CurrentDay = null;

			if (DatePickForWhichYear_YYYY != null) {
				fnpSelectValue_By_SelectClass(selectYearClassXpath, DatePickForWhichYear_YYYY);
				Thread.sleep(2000);
			}

			if (DatePickForWhichMonth_MMMM != null) {
				fnpSelectValue_By_SelectClass(selectMonthClassXpath, DatePickForWhichMonth_MMMM);
			}

			if (DatePickForWhichDay != null) {
				CurrentDay = DatePickForWhichDay;
			} else {
				Calendar cal = Calendar.getInstance();
				DateFormat dayFormat = new SimpleDateFormat("d");
				CurrentDay = dayFormat.format(cal.getTime());
			}
			// String CurrentDayXpath = "//a[text()='" + CurrentDay + "']";
			String CurrentDayXpath = "//td[@data-handler='selectDay']/a[text()='" + CurrentDay + "']";
			Thread.sleep(1000);
			if (CurrentDay.equalsIgnoreCase("31")) {
				CurrentDayXpath = "//a[text()='30']";
			}

			fnpClick_NOR_withoutWait(CurrentDayXpath);
			Thread.sleep(500);

		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new SkipException(W.getMessage());
		} catch (Throwable t) {
			fnpDesireScreenshot("DatePickFail");
			isTestCasePass = false;
			msg = "FAILED == Calendar Date Pick is getting fail, Please refer screen shot >> DatePickFail. Getting Exception >>"
					+ t.getMessage();
			fnpMymsg(msg);
			throw new Exception(msg);
		}
	}

	/******************************
	 * Picking date current day and Current Month for String Passing year By
	 * Selecting values from months and years DropDpwn.
	 *********************/
	public static void fnpCalendarDatePicker_BySelectValues_From_DropDown2(String stringDate,
			String selectYearClassXpath, String selectMonthClassXpath) throws Throwable {
		try {

			String CurrentDay = null;

			SimpleDateFormat sdfmt1 = new SimpleDateFormat("dd-MMM-yyyy");

			Date dat = sdfmt1.parse(stringDate);
			Calendar c = Calendar.getInstance();
			c.setTime(dat);

			int year = c.get(Calendar.YEAR);
			fnpMymsg("  ---year is ---" + year);

			int dom = c.get(Calendar.DAY_OF_MONTH);
			fnpMymsg("  ---day of month is ---" + dom);

			SimpleDateFormat sdfmt2 = new SimpleDateFormat("MMM");
			String monthName = sdfmt2.format(dat);

			fnpMymsg("  --- month is ---" + monthName);

			String DatePickForWhichYear_YYYY = Integer.toString(year);
			if (DatePickForWhichYear_YYYY != null) {
				fnpSelectValue_By_SelectClass(selectYearClassXpath, DatePickForWhichYear_YYYY);
				Thread.sleep(2000);
			}

			String DatePickForWhichMonth_MMMM = monthName;
			if (DatePickForWhichMonth_MMMM != null) {
				fnpSelectValue_By_SelectClass(selectMonthClassXpath, DatePickForWhichMonth_MMMM);
			}

			String DatePickForWhichDay = Integer.toString(dom);
			if (DatePickForWhichDay != null) {
				CurrentDay = DatePickForWhichDay;
			} else {
				Calendar cal = Calendar.getInstance();
				DateFormat dayFormat = new SimpleDateFormat("d");
				CurrentDay = dayFormat.format(cal.getTime());
			}
			// String CurrentDayXpath = "//a[text()='" + CurrentDay + "']";
			String CurrentDayXpath = "//td[@data-handler='selectDay']/a[text()='" + CurrentDay + "']";

			Thread.sleep(1000);

			fnpClick_NOR(CurrentDayXpath);
			Thread.sleep(500);

		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new SkipException(W.getMessage());
		} catch (Throwable t) {
			fnpDesireScreenshot("DatePickFail");
			isTestCasePass = false;
			msg = "FAILED == Calendar Date Pick is getting fail, Please refer screen shot >> DatePickFail. Getting Exception >>"
					+ t.getMessage();
			fnpMymsg(msg);
			throw new Exception(msg);
		}
	}

	/***************** function to select drop down value *********************/
	public static void fnpSelectValue_By_SelectClass(String dropdownXpath, String MatchingValue) throws Exception {

		try {
			fnpWaitForVisible_NotInOR(dropdownXpath, 10);
			Select Dropdown = new Select(fnpGetORObjectX___NOR(dropdownXpath));
			Dropdown.selectByVisibleText(MatchingValue);

			fnpMymsg("PASSED == select value [ " + MatchingValue + " ] from drop down done, having xpath >> "
					+ dropdownXpath);
		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			isTestCasePass = false;
			fnpDesireScreenshot("DdValueSelectFailWOR");
			msg = "Exception thrown is this --'" + t.getMessage() + "'.  \n\n\n FAILED == Unable to select value[ "
					+ MatchingValue + " ] from drop down [ " + dropdownXpath
					+ " ],plz see screenshot [ DdValueSelectFailWOR ]" + ". Getting Exception  >> "
					+ t.getMessage().trim();
			fnpMymsg(msg);
			throw new Exception(msg);
		}
	}

	/***********************
	 * Picking date current day and Current Month for String Passing year By
	 * Selecting values from months and years DropDpwn.
	 *********************/
	public static void fnpCalendarDatePicker_BySelectValues_From_NextAndBack(String stringDate) throws Throwable {
		try {

			Date todayDate = new Date();
			SimpleDateFormat todayDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
			String todayDateInStringFormat = todayDateFormat.format(todayDate);

			String CurrentDay = null;

			SimpleDateFormat sdfmt1 = new SimpleDateFormat("dd-MMM-yyyy");

			Date dat = sdfmt1.parse(stringDate);
			Calendar c = Calendar.getInstance();
			c.setTime(dat);

			int year = c.get(Calendar.YEAR);

			int dom = c.get(Calendar.DAY_OF_MONTH);

			SimpleDateFormat sdfmt2 = new SimpleDateFormat("MMM");
			String monthName = sdfmt2.format(dat);

			String actualYearInStringFormat = fnpGetText_OR("CalendarHeaderYear_xpath");
			int actualyear = Integer.parseInt(actualYearInStringFormat);

			while (actualyear < year) {
				fnpClick_OR_WithoutWait("CalendarNextIcon");
				Thread.sleep(1000);
				actualYearInStringFormat = fnpGetText_OR("CalendarHeaderYear_xpath");
				actualyear = Integer.parseInt(actualYearInStringFormat);
			}

			while (actualyear > year) {
				fnpClick_OR_WithoutWait("CalendarPrevIcon");
				Thread.sleep(1000);
				actualYearInStringFormat = fnpGetText_OR("CalendarHeaderYear_xpath");
				actualyear = Integer.parseInt(actualYearInStringFormat);
			}

			String actualMonthInStringFormat = fnpGetText_OR("CalendarHeaderMonth_xpath");
			SimpleDateFormat monthFormat = new SimpleDateFormat("MMM");
			Date actualMonthInDateFormat = monthFormat.parse(actualMonthInStringFormat);

			SimpleDateFormat formonth = new SimpleDateFormat("dd-MMM-yyyy");
			Date datformonth = formonth.parse(stringDate);
			// Date datformonth=monthFormat.parse(stringDate);
			String datformonthString = monthFormat.format(datformonth);
			Date datformonth2 = monthFormat.parse(datformonthString);
			Date expectedMonthInDateFormat = datformonth2;

			while (expectedMonthInDateFormat.compareTo(actualMonthInDateFormat) > 0) {
				fnpClick_OR_WithoutWait("CalendarNextIcon");
				Thread.sleep(1000);
				actualMonthInStringFormat = fnpGetText_OR("CalendarHeaderMonth_xpath");
				actualMonthInDateFormat = monthFormat.parse(actualMonthInStringFormat);
			}

			while (expectedMonthInDateFormat.compareTo(actualMonthInDateFormat) < 0) {
				fnpClick_OR_WithoutWait("CalendarPrevIcon");
				Thread.sleep(1000);
				actualMonthInStringFormat = fnpGetText_OR("CalendarHeaderMonth_xpath");
				actualMonthInDateFormat = monthFormat.parse(actualMonthInStringFormat);
			}

			String DatePickForWhichDay = Integer.toString(dom);
			if (DatePickForWhichDay != null) {
				CurrentDay = DatePickForWhichDay;
			} else {
				Calendar cal = Calendar.getInstance();
				DateFormat dayFormat = new SimpleDateFormat("d");
				CurrentDay = dayFormat.format(cal.getTime());
			}
			// String CurrentDayXpath = "//a[text()='" + CurrentDay + "']";
			String CurrentDayXpath = "//td[@data-handler='selectDay']/a[text()='" + CurrentDay + "']";
			Thread.sleep(1000);

			fnpClick_NOR_withoutWait(CurrentDayXpath);
			Thread.sleep(500);

		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new SkipException(W.getMessage());
		} catch (Throwable t) {
			fnpDesireScreenshot("DatePickFail");
			isTestCasePass = false;
			msg = "FAILED == Calendar Date Pick is getting fail, Please refer screen shot >> DatePickFail. Getting Exception >>"
					+ t.getMessage();
			fnpMymsg(msg);
			throw new Exception(msg);
		}
	}

	/*****************************
	 * Picking date current day and Current Month for String Passing year By
	 * Selecting values from months and years DropDpwn.
	 ***************************/
	public static void fnpCalendarDatePicker_BySelectValues_From_NextAndBack_SAM(String stringDate) throws Throwable {
		String CurrentDayXpath = null;
		String CurrentDay = null;

		try {

			Date todayDate = new Date();

			SimpleDateFormat todayDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
			String todayDateInStringFormat = todayDateFormat.format(todayDate);

			// String CurrentDay = null;

			SimpleDateFormat sdfmt1 = new SimpleDateFormat("dd-MMM-yyyy");

			Date dat = sdfmt1.parse(stringDate);
			Calendar c = Calendar.getInstance();
			c.setTime(dat);

			int year = c.get(Calendar.YEAR);

			int dom = c.get(Calendar.DAY_OF_MONTH);

			SimpleDateFormat sdfmt2 = new SimpleDateFormat("MMM");
			String monthName = sdfmt2.format(dat);

			// String actualYearInStringFormat = fnpGetText_OR("CalendarHeaderYear_xpath");
			String actualDateInStringFormat = fnpGetText_OR("CurrentDateXpath");
			String actualYearInStringFormat = actualDateInStringFormat.split(" ")[1];
			int actualyear = Integer.parseInt(actualYearInStringFormat);

			while (actualyear < year) {
				// fnpMymsg("Pradeep---1");
				fnpClick_OR_WithoutWait("NextIconXpath");
				Thread.sleep(1000);
				actualDateInStringFormat = fnpGetText_OR("CurrentDateXpath");
				actualYearInStringFormat = actualDateInStringFormat.split(" ")[1];
				actualyear = Integer.parseInt(actualYearInStringFormat);
			}

			while (actualyear > year) {
				// fnpMymsg("Pradeep---2");

				fnpClick_OR_WithoutWait("PrevIconXpath");
				Thread.sleep(1000);

				actualDateInStringFormat = fnpGetText_OR("CurrentDateXpath");
				actualYearInStringFormat = actualDateInStringFormat.split(" ")[1];
				actualyear = Integer.parseInt(actualYearInStringFormat);
			}

			// String actualMonthInStringFormat =
			// fnpGetText_OR("CalendarHeaderMonth_xpath");
			actualDateInStringFormat = fnpGetText_OR("CurrentDateXpath");
			String actualMonthInStringFormat = actualDateInStringFormat.split(" ")[0];

			SimpleDateFormat monthFormat = new SimpleDateFormat("MMM");
			Date actualMonthInDateFormat = monthFormat.parse(actualMonthInStringFormat);

			SimpleDateFormat formonth = new SimpleDateFormat("dd-MMM-yyyy");
			Date datformonth = formonth.parse(stringDate);
			// Date datformonth=monthFormat.parse(stringDate);
			String datformonthString = monthFormat.format(datformonth);
			Date datformonth2 = monthFormat.parse(datformonthString);
			Date expectedMonthInDateFormat = datformonth2;

			// fnpMymsg("Pradeep---3");
			while (expectedMonthInDateFormat.compareTo(actualMonthInDateFormat) > 0) {
				// fnpMymsg("Pradeep---4");
				fnpClick_OR_WithoutWait("NextIconXpath");
				Thread.sleep(1000);
				actualDateInStringFormat = fnpGetText_OR("CurrentDateXpath");
				actualMonthInStringFormat = actualDateInStringFormat.split(" ")[0];
				actualMonthInDateFormat = monthFormat.parse(actualMonthInStringFormat);
			}

			while (expectedMonthInDateFormat.compareTo(actualMonthInDateFormat) < 0) {
				// fnpMymsg("Pradeep---5");
				fnpClick_OR_WithoutWait("PrevIconXpath");
				Thread.sleep(1000);
				actualDateInStringFormat = fnpGetText_OR("CurrentDateXpath");
				actualMonthInStringFormat = actualDateInStringFormat.split(" ")[0];
				actualMonthInDateFormat = monthFormat.parse(actualMonthInStringFormat);
			}

			String DatePickForWhichDay = Integer.toString(dom);
			if (DatePickForWhichDay != null) {
				CurrentDay = DatePickForWhichDay;
			} else {
				Calendar cal = Calendar.getInstance();
				DateFormat dayFormat = new SimpleDateFormat("d");
				CurrentDay = dayFormat.format(cal.getTime());
			}

			// fnpMymsg("Pradeep---6");
			CurrentDayXpath = "//td/a[text()='" + CurrentDay + "']";
			Thread.sleep(1000);

			// fnpMymsg("Pradeep---7");

			// fnpClick_NOR_withoutWait(CurrentDayXpath);

			/*
			 * int p=fnpFindNoOfElementsWithThisXpath_NOR(CurrentDayXpath);
			 * fnpMymsg("Pradeep--total no. of elements visible with this xpath '"
			 * +CurrentDayXpath+"' are ---"+p);
			 * System.out.println("Pradeep--total no. of elements visible with this xpath '"
			 * +CurrentDayXpath+"' are ---"+p);
			 * fnpGetORObjectX_WhenMultipleObjectsHavingSameXpath_NOR(CurrentDayXpath).click
			 * ();
			 * Thread.sleep(500);
			 * 
			 */

			List<WebElement> totalElements = fnpGetORObject_list_NOR(CurrentDayXpath, 1);
			Date dat1 = null;
			for (Iterator iterator = totalElements.iterator(); iterator.hasNext();) {
				WebElement webElement = (WebElement) iterator.next();

				String title = webElement.getAttribute("data-value");
				// sdfmt1 = new SimpleDateFormat("dd-MMM-yyyy");
				sdfmt1 = new SimpleDateFormat("yyyy/MM/dd");

				dat1 = sdfmt1.parse(title);

				if (dat.compareTo(dat1) == 0) {
					System.out.println("Pradeep--Date matched with this '" + title + "'.");
					if (webElement.isDisplayed()) {
						System.out.println("Pradeep--Element '" + title + "' is visible.");
					} else {
						// return false;
						System.out.println("Pradeep--Element '" + title + "' is NOT visible.");
					}

					if (fnpCheckElementClickableOrNot_notInOR(webElement)) {
						System.out.println("Pradeep--Element '" + title + "' is Clickable.");
						webElement.click();
						Thread.sleep(500);
						System.out.println("Pradeep--Element  '" + title + "'has been clicked.");
					} else {
						// return false;
						System.out.println("Pradeep--Element '" + title + "' is NOT Clickable.");
						throw new Exception("Date '" + title + "' is not clickable");
					}

				} else {
					// nothing to do..check for next element in loop
					fnpMymsg("Pradeep--This '" + title + "' is not our expected date '" + stringDate
							+ "' , so going to check next.");
					System.out.println("Pradeep--This '" + title + "' is not our expected date '" + stringDate
							+ "' , so going to check next.");
				}

			}

			// fnpMymsg("Pradeep---8");

		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new SkipException(W.getMessage());
		} catch (Throwable t) {
			fnpDesireScreenshot("DatePickFail");
			isTestCasePass = false;
			if (t.getMessage().contains("visibility of element located by By.xpath: " + CurrentDayXpath)) {
				String txtmsg = "FAILED == Date '" + CurrentDay
						+ "' is disabled in calendar, hence calendar date picking is getting failed, Please refer screen shot >> DatePickFail. Getting Exception >>"
						+ t.getMessage();
				fnpMymsg(txtmsg);
				throw new Exception(txtmsg);

			} else {
				msg = "FAILED == Calendar Date Pick is getting fail, Please refer screen shot >> DatePickFail. Getting Exception >>"
						+ t.getMessage();
				fnpMymsg(msg);
				throw new Exception(msg);

			}
		}
	}

	public static void fnpCalendarDatePicker_BySelectValues_From_NextAndBack_SAM_SchedulingBound(String stringDate,
			String currentDateXpathKey, String previousIconXpathKey, String NextIconXpathKey) throws Throwable {
		String CurrentDayXpath = null;
		String CurrentDay = null;
		Thread.sleep(2000);
		try {

			// System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			// System.out.println("Pradeep---stringDate is ---"+stringDate);
			// System.out.println("Pradeep---currentDateXpathKey is
			// ---"+currentDateXpathKey);

			Date todayDate = new Date();

			SimpleDateFormat todayDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
			String todayDateInStringFormat = todayDateFormat.format(todayDate);

			// String CurrentDay = null;

			SimpleDateFormat sdfmt1 = new SimpleDateFormat("dd-MMM-yyyy");

			Date dat = sdfmt1.parse(stringDate);
			Calendar c = Calendar.getInstance();
			c.setTime(dat);

			int year = c.get(Calendar.YEAR);

			int dom = c.get(Calendar.DAY_OF_MONTH);

			SimpleDateFormat sdfmt2 = new SimpleDateFormat("MMM");
			String monthName = sdfmt2.format(dat);

			// String actualYearInStringFormat = fnpGetText_OR("CalendarHeaderYear_xpath");
			String actualDateInStringFormat = fnpGetText_OR(currentDateXpathKey);

			// System.out.println("Pradeep---actualDateInStringFormat is
			// ---"+actualDateInStringFormat);
			String actualYearInStringFormat = actualDateInStringFormat.split(" ")[1];
			// System.out.println("Pradeep---actualYearInStringFormat is
			// ---"+actualYearInStringFormat);

			int actualyear = Integer.parseInt(actualYearInStringFormat);

			while (actualyear < year) {
				// System.out.println("Pradeep---1");
				fnpClick_OR_WithoutWait(NextIconXpathKey);
				Thread.sleep(2000);
				actualDateInStringFormat = fnpGetText_OR(currentDateXpathKey);
				actualYearInStringFormat = actualDateInStringFormat.split(" ")[1];
				actualyear = Integer.parseInt(actualYearInStringFormat);
			}

			while (actualyear > year) {
				// System.out.println("Pradeep---2");

				fnpClick_OR_WithoutWait(previousIconXpathKey);
				Thread.sleep(2000);

				actualDateInStringFormat = fnpGetText_OR(currentDateXpathKey);
				actualYearInStringFormat = actualDateInStringFormat.split(" ")[1];
				actualyear = Integer.parseInt(actualYearInStringFormat);
			}

			// String actualMonthInStringFormat =
			// fnpGetText_OR("CalendarHeaderMonth_xpath");
			actualDateInStringFormat = fnpGetText_OR(currentDateXpathKey);
			String actualMonthInStringFormat = actualDateInStringFormat.split(" ")[0];
			// System.out.println("Pradeep---3");

			SimpleDateFormat monthFormat = new SimpleDateFormat("MMM");
			Date actualMonthInDateFormat = monthFormat.parse(actualMonthInStringFormat);

			SimpleDateFormat formonth = new SimpleDateFormat("dd-MMM-yyyy");
			Date datformonth = formonth.parse(stringDate);
			// Date datformonth=monthFormat.parse(stringDate);
			String datformonthString = monthFormat.format(datformonth);
			Date datformonth2 = monthFormat.parse(datformonthString);
			Date expectedMonthInDateFormat = datformonth2;

			// System.out.println("Pradeep---4");
			WebElement wb;
			while (expectedMonthInDateFormat.compareTo(actualMonthInDateFormat) > 0) {
				// System.out.println("Pradeep---5");
				// fnpClick_OR_WithoutWait(NextIconXpathKey);
				wb = driver.findElement(By.xpath(OR.getProperty(NextIconXpathKey)));
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", wb);

				Thread.sleep(2000);
				actualDateInStringFormat = fnpGetText_OR(currentDateXpathKey);
				actualMonthInStringFormat = actualDateInStringFormat.split(" ")[0];
				actualMonthInDateFormat = monthFormat.parse(actualMonthInStringFormat);
			}

			while (expectedMonthInDateFormat.compareTo(actualMonthInDateFormat) < 0) {
				// System.out.println("Pradeep---6");
				// fnpClick_OR_WithoutWait(previousIconXpathKey);
				wb = driver.findElement(By.xpath(OR.getProperty(previousIconXpathKey)));
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", wb);
				Thread.sleep(2000);
				actualDateInStringFormat = fnpGetText_OR(currentDateXpathKey);
				wb = fnpGetORObjectX_WhenMultipleObjectsHavingSameXpath_NOR_immediately(currentDateXpathKey,
						"Current Date in Calendar");
				// actualDateInStringFormat=wb.getText();

				actualMonthInStringFormat = actualDateInStringFormat.split(" ")[0];
				actualMonthInDateFormat = monthFormat.parse(actualMonthInStringFormat);
			}

			String DatePickForWhichDay = Integer.toString(dom);
			if (DatePickForWhichDay != null) {
				CurrentDay = DatePickForWhichDay;
			} else {
				Calendar cal = Calendar.getInstance();
				DateFormat dayFormat = new SimpleDateFormat("d");
				CurrentDay = dayFormat.format(cal.getTime());
			}

			// System.out.println("Pradeep---7");
			CurrentDayXpath = "//td/a[text()='" + CurrentDay + "']";
			Thread.sleep(2000);

			// fnpMymsg("Pradeep---7");

			// fnpClick_NOR_withoutWait(CurrentDayXpath);

			/*
			 * int p=fnpFindNoOfElementsWithThisXpath_NOR(CurrentDayXpath);
			 * fnpMymsg("Pradeep--total no. of elements visible with this xpath '"
			 * +CurrentDayXpath+"' are ---"+p);
			 * System.out.println("Pradeep--total no. of elements visible with this xpath '"
			 * +CurrentDayXpath+"' are ---"+p);
			 * fnpGetORObjectX_WhenMultipleObjectsHavingSameXpath_NOR(CurrentDayXpath).click
			 * ();
			 * Thread.sleep(500);
			 * 
			 */

			List<WebElement> totalElements = fnpGetORObject_list_NOR(CurrentDayXpath, 1);

			fnpMymsg("Pradeep--total elements are--" + totalElements.size());
			Date dat1 = null;
			boolean ElementDisplayed = false;
			System.out.println("Pradeep---8");
			for (Iterator iterator = totalElements.iterator(); iterator.hasNext();) {
				// System.out.println("Pradeep---9");
				ElementDisplayed = false;
				WebElement webElement = null;

				/*
				 * String title=webElement.getAttribute("data-value");
				 * sdfmt1 = new SimpleDateFormat("yyyy/MM/dd");
				 */
				try {
					webElement = (WebElement) iterator.next();
					// System.out.println("Pradeep---10");
					if (webElement.isDisplayed()) {
						ElementDisplayed = true;
						System.out.println("Pradeep---11");
					}
				} catch (Throwable t) {
					// nothing to do
				}
				if (ElementDisplayed) {
					// System.out.println("Pradeep---12");
					String title = webElement.getAttribute("title");
					sdfmt1 = new SimpleDateFormat("E, MMMM dd, yyyy");

					dat1 = sdfmt1.parse(title);

					// System.out.println("Pradeep---dat value is---"+dat);
					// System.out.println("Pradeep---dat1 value is---"+dat1);
					// System.out.println("Pradeep---compare value is---"+dat.compareTo(dat1) );

					if (dat.compareTo(dat1) == 0) {
						// System.out.println("Pradeep--Date matched with this '"+title+"'.");

						/*
						 * if (webElement.isDisplayed()) {
						 * System.out.println("Pradeep--Element '"+title+"' is visible.");
						 * } else {
						 * // return false;
						 * System.out.println("Pradeep--Element '"+title+"' is NOT visible.");
						 * }
						 * 
						 * if (fnpCheckElementClickableOrNot_notInOR(webElement)) {
						 * System.out.println("Pradeep--Element '"+title+"' is Clickable.");
						 * webElement.click();
						 * Thread.sleep(500);
						 * System.out.println("Pradeep--Element  '"+title+"'has been clicked.");
						 * } else {
						 * // return false;
						 * System.out.println("Pradeep--Element '"+title+"' is NOT Clickable.");
						 * throw new Exception("Date '"+title+"' is not clickable");
						 * }
						 */

						// webElement.click();

						Thread.sleep(2000);
						JavascriptExecutor executor = (JavascriptExecutor) driver;
						executor.executeScript("arguments[0].click();", webElement);
						break;

					} else {
						// nothing to do..check for next element in loop
						fnpMymsg("Pradeep--This '" + title + "' is not our expected date '" + stringDate
								+ "' , so going to check next.");
						// System.out.println("Pradeep--This '"+title+"' is not our expected date
						// '"+stringDate+"' , so going to check next.");
					}
				}
				/*
				 * else{
				 * throw new Exception("Date is not visible.");
				 * }
				 */ }

			if (!ElementDisplayed) {
				throw new Exception("Date is not visible.");
			}

			// fnpMymsg("Pradeep---8");

		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new SkipException(W.getMessage());
		} catch (Throwable t) {
			fnpDesireScreenshot("DatePickFail");
			isTestCasePass = false;
			if (t.getMessage().contains("visibility of element located by By.xpath: " + currentDateXpathKey)) {
				String txtmsg = "FAILED == Date '" + CurrentDay
						+ "' is disabled in calendar, hence calendar date picking is getting failed, Please refer screen shot >> DatePickFail. Getting Exception >>"
						+ t.getMessage();
				fnpMymsg(txtmsg);
				throw new Exception(txtmsg);

			} else {
				msg = "FAILED == Calendar Date Pick is getting fail, Please refer screen shot >> DatePickFail. Getting Exception >>"
						+ t.getMessage();
				fnpMymsg(msg);
				throw new Exception(msg);

			}
		}
	}

	public static void fnpTypeInField(String xpath, String value) throws Throwable {
		String val = value;
		WebElement element = driver.findElement(By.xpath(xpath));
		element.clear();

		for (int i = 0; i < val.length(); i++) {
			char c = val.charAt(i);
			String s = new StringBuilder().append(c).toString();
			element.sendKeys(s);
			Thread.sleep(1000);
		}
	}

	/******************
	 * Function to find and return the object using Xpath
	 **************/
	public static WebElement fnpGetORObject(String XpathKey) throws Exception {
		int retries = 0;
		while (true) {
			try {

				Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
						.withTimeout((Long.parseLong(CONFIG.getProperty("genMax_waitTime"))), TimeUnit.SECONDS)
						.pollingEvery(2, TimeUnit.SECONDS)
						// .ignoring(NoSuchElementException.class);
						.ignoring(org.openqa.selenium.NoSuchElementException.class)
						.ignoring(org.openqa.selenium.InvalidSelectorException.class)
						.ignoring(org.openqa.selenium.WebDriverException.class)
						.ignoring(org.openqa.selenium.StaleElementReferenceException.class);

				if (OR.getProperty(XpathKey).contains("~")) {
					return fnpReturnWebElementAfterExtractLocatorandValue(OR.getProperty(XpathKey)).get(0);
				} else {

					/****** By default Xpath will be assumed *****/
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(XpathKey))));
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(XpathKey))));
					return driver.findElement(By.xpath(OR.getProperty(XpathKey)));

				}

			} catch (org.openqa.selenium.StaleElementReferenceException s) {
				if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					Thread.sleep(1000);
					retries++;
					fnpMymsg(retries + "In staleElementException catch block of fnpGetORObjectX function for "
							+ XpathKey);
					continue;
				} else {
					throw s;
				}
			}

			catch (Throwable t) {
				// fnpDesireScreenshot("NotPresent" + XpathKey);
				throw new Exception(t.getMessage() + "   So,Unable to find element with name [" + XpathKey
						+ "] ,plz see screenshot [NotPresent" + XpathKey + SShots + "]");
			}

			finally {
				driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")),
						TimeUnit.SECONDS);
			}
		}

	}

	public static String fnpReturnAStringWholeFirstCharacterIsInUpperCase(String s) {
		String newValue = "";
		String lowercaseString = s.toLowerCase();
		String lowercaseArray[] = lowercaseString.split(" ");

		if (lowercaseArray.length > 1) {
			for (int i = 0; i < lowercaseArray.length; i++) {
				if (lowercaseArray[i].equalsIgnoreCase(" ") || lowercaseArray[i].equalsIgnoreCase("")) {
					newValue = newValue + " " + lowercaseArray[i];
				} else {
					String new_value_firstCharacter = lowercaseArray[i].substring(0, 1);
					String new_value_firstCharacterUpperCase = new_value_firstCharacter.toUpperCase();
					lowercaseArray[i] = lowercaseArray[i].replaceFirst(new_value_firstCharacter,
							new_value_firstCharacterUpperCase);
					newValue = newValue + " " + lowercaseArray[i];
				}

			}

			newValue = newValue.trim();

		} else {
			String new_value_firstCharacter = lowercaseString.substring(0, 1);
			String new_value_firstCharacterUpperCase = new_value_firstCharacter.toUpperCase();
			newValue = lowercaseString.replaceFirst(new_value_firstCharacter, new_value_firstCharacterUpperCase);
		}

		return newValue;
	}

	public static void fnpMoveMouseAtCenterBottomOfScreen() throws Throwable {

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();

		fnpMymsg("width is ---" + width);
		fnpMymsg("hight is ---" + height);

		Robot robot = new Robot();
		robot.mouseMove((int) width / 2, ((int) height + 200));
		Thread.sleep(500);
	}

	public static int fnpFindTechnicalReviewOfNonLinkedWOInCustomWO() throws Throwable {

		int taskTypeColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskTypeColName);
		int linkedWOColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", "Linked WO");
		int totalRows = fnpCountRowsInTable("TasksTable_EditWO");

		String linkedWONo = null;
		String taskDesc = null;
		int taskDescCounter = 0;
		int expectedRowNo = 0;
		int linkedWORowNo = 0;
		for (int i = 1; i < totalRows; i++) {
			linkedWONo = fnpFetchFromTable("TasksTable_EditWO", i, linkedWOColIndex).trim();
			if (!(linkedWONo.equalsIgnoreCase(""))) {
				linkedWORowNo = i;
				// break;
			}

			taskDesc = fnpFetchFromTable("TasksTable_EditWO", i, taskTypeColIndex);
			if (taskDesc.equalsIgnoreCase(taskType_TechnicalReview)) {
				taskDescCounter++;
			}

		}

		if ((classNameText.equalsIgnoreCase("Linking_Custom_Custom_WO")) && (taskDescCounter > 1)) {
			if (linkedWORowNo < 1) {
				msg = " Linked WO no. is missing in Linked WO column. ";
				fnpMymsg(msg);
				throw new Exception(msg);
			}
		}

		for (int i = 1; i < totalRows; i++) {
			linkedWONo = fnpFetchFromTable("TasksTable_EditWO", i, linkedWOColIndex).trim();
			taskDesc = fnpFetchFromTable("TasksTable_EditWO", i, taskTypeColIndex);
			if ((taskDesc.equalsIgnoreCase(taskType_TechnicalReview)) && ((linkedWONo.equalsIgnoreCase("")))) {
				expectedRowNo = i;
				break;
			}

		}

		int taskStatusColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_StatusColName);
		int taskTechnicalReviewRowNo = expectedRowNo;

		return expectedRowNo;
	}

	public static int fnpCheckDateEqual(String dateToCompareInStringFormat, Date withCompareDate, String dateFormat)
			throws Throwable {
		int i;

		SimpleDateFormat sdfmt1 = new SimpleDateFormat(dateFormat);
		java.util.Date dateToCompareInDateFormat = sdfmt1.parse(dateToCompareInStringFormat);

		String textMessage = "";
		if (dateToCompareInDateFormat.compareTo(withCompareDate) > 0) {
			textMessage = "dateToCompareInStringFormat '" + dateToCompareInStringFormat + "' is after withCompareDate '"
					+ withCompareDate + "', hence failed. ";
			fnpMymsg(textMessage);
			fnpDisplayingMessageInFrame(textMessage, 5);
			i = 1;
			throw new Exception(textMessage);
		} else if (dateToCompareInDateFormat.compareTo(withCompareDate) < 0) {
			textMessage = "dateToCompareInStringFormat '" + dateToCompareInStringFormat
					+ "' is before withCompareDate '" + withCompareDate + "', hence failed. ";
			fnpMymsg(textMessage);
			fnpDisplayingMessageInFrame(textMessage, 5);
			i = -1;
			throw new Exception(textMessage);
		} else {
			textMessage = "dateToCompareInStringFormat '" + dateToCompareInStringFormat
					+ "' is equal to  withCompareDate '" + withCompareDate + "', hence failed. ";
			fnpMymsg(textMessage);
			fnpDisplayingMessageInFrame(textMessage, 5);
			i = 0;
		}

		return i;
	}

	public static void fnpCheckFileIsDownloadedOrNotIniPulse(String xpathOfButtonOrLinkToClickForDownloading)
			throws Throwable {
		String directoryName = System.getProperty("user.dir") + "\\download";

		File downloadFolderUnderRootDirectory = new File(directoryName);
		if (!downloadFolderUnderRootDirectory.exists()) {
			APP_LOGS.debug("DIR '" + downloadFolderUnderRootDirectory
					+ "' has not been present already, so going to create it first.");
			FileUtils.forceMkdir(downloadFolderUnderRootDirectory);
			// timefolderinLogHistory.mkdir();
			APP_LOGS.debug("DIR '" + downloadFolderUnderRootDirectory + "'  has been created");
		}

		try {
			File folder = new File(directoryName);
			File[] listOfFiles = folder.listFiles();
			// int filecount = listOfFiles.length;
			int filecount = 0;
			if (listOfFiles != null) {
				filecount = listOfFiles.length;
			}
			fnpMymsg("Before starting downloading total files present right now in this folder is ----" + filecount);

			if (filecount > 0) {
				fnpMymsg("Folder is not empty before downloading the files, so going to empty it.");
				fnpDeleteAllFilesInAFolder(directoryName);
				cleanDirectory(folder);
				Thread.sleep(5000);
			} else {
				fnpMymsg("Folder is already  empty.");
			}
		} catch (Throwable t) {
			// nothing to do
		}

		fnpClick_NOR(xpathOfButtonOrLinkToClickForDownloading);

		File folder = new File(directoryName);
		File[] listOfFiles = folder.listFiles();
		// int filecount = listOfFiles.length;
		int filecount = 0;
		if (listOfFiles != null) {
			filecount = listOfFiles.length;
		}

		int iDownloadFile = 0;
		int maxTimeGiveForDownloading = 60;
		while (filecount != 1) {
			iDownloadFile++;
			Thread.sleep(1000);
			folder = new File(directoryName);
			listOfFiles = folder.listFiles();
			// filecount = listOfFiles.length;

			if (listOfFiles != null) {
				filecount = listOfFiles.length;
			}
			if (iDownloadFile > maxTimeGiveForDownloading) {
				break;
			}

		}

		if (filecount == 1) {
			fnpMymsg(" File  is downloaded successfully as file count is ---" + filecount + " and its name is --- '"
					+ listOfFiles[0].getName() + "'.   ");

		} else {
			msg = " File  is NOT downloaded successfully  after clicking of download link as file count is ---"
					+ filecount + " . ";
			fnpMymsg(msg);

			throw new Exception(msg);
		}

		fnpDeleteAllFilesInAFolder(directoryName);
		listOfFiles = folder.listFiles();
		// filecount = listOfFiles.length;
		if (listOfFiles != null) {
			filecount = listOfFiles.length;
		}
		fnpMymsg(" Downloaded file deleted successfully now as file count is ---" + filecount + " . ");

	}

	public static void fnpDeleteAllFilesInAFolder(String pathOfFolder) throws Throwable {
		File downloadFolderUnderRootDirectory = new File(pathOfFolder);
		if (!downloadFolderUnderRootDirectory.exists()) {
			APP_LOGS.debug("DIR '" + downloadFolderUnderRootDirectory + "' has not been present already");
			FileUtils.forceMkdir(downloadFolderUnderRootDirectory);
			// timefolderinLogHistory.mkdir();
			APP_LOGS.debug("DIR '" + downloadFolderUnderRootDirectory + "'  has been created");
		}

		File[] files = downloadFolderUnderRootDirectory.listFiles();
		if (files != null) {
			for (File downloadedFiles : files)
				if (!downloadedFiles.isDirectory()) {
					downloadedFiles.delete();
				}
		}

	}

	public static void fnpCallGoldenProcedure_COPY_AUDIT_TRANSACTION_SUBMIT(int copyFromAudit, int copyToAudit,
			String user) throws Throwable {
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

			connection = fnpGetDBConnection();

			String stringOFCallingGoldenProcedure2 = "{call ECAP.EC_PKG_AUDIT_UTIL.COPY_AUDIT_TRANSACTION_SUBMIT(?,?,?,?)}";
			CallableStatement cStmt = connection.prepareCall(stringOFCallingGoldenProcedure2);

			cStmt.setInt(1, copyFromAudit);
			cStmt.setInt(2, copyToAudit);
			cStmt.setString(3, user);
			cStmt.registerOutParameter(4, java.sql.Types.VARCHAR);

			// execute COPY_AUDIT_TRANSACTION store procedure
			cStmt.executeUpdate();
			String outResult = cStmt.getString(4);
			// System.out.println("Stored Procedure 2 Result : " + outResult);
			fnpMymsg("Stored Procedure 2 Result : " + outResult);

			connection.commit();

			// connection.close();

		} catch (SQLException e) {
			fnpMymsg(
					"*********************************************** Either connection Failed or some different error with Database,   Getting Error >>  "
							+ e.getMessage().trim());
			fnpMymsg(
					"=========================================================================================================================================");
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}

		finally {
			connection.close();
		}
		fnpMymsg("******************");

		// return rate;
	}

	public static String fnpRemoveInvalidCharactersFromSavingFileName(String fileName) {

		fileName = fileName.replace(".", " ");
		fileName = fileName.replace("/", " ");
		fileName = fileName.replace("//", " ");
		fileName = fileName.replace("*", " ");
		fileName = fileName.replace(",", " ");

		return fileName;
	}

	// Taken from Production script
	/*****
	 * Select the value from prime faces Multiple Select Drop Down (for both Li
	 * and option tag)
	 *****/
	public static void fnpMultipleSelectDropDown2_old(String ORXpath, String expvalue) throws Throwable {

		try {
			fnpWaitForVisible(ORXpath);
			fnpWaitTillVisiblityOfElementNClickable_OR(ORXpath);
			fnpMymsg(" Expected i.e. going to select value in  " + ORXpath + "list is ==> " + expvalue);
			expvalue = expvalue.trim();

			String tagname = null;
			boolean ValueMatched = false;

			if (fnpCountChildElementPresenceImmediately_NotInOR(fnpGetORObjectX(ORXpath), "li") > 0) {
				List<WebElement> objectlist5 = fnpGetORObjectX(ORXpath).findElements(By.tagName("li"));
				tagname = "li";
				fnpMymsg(" li tag is present for this list. ");
				int forloopcounter = -1;
				for (WebElement dd_value : objectlist5) {
					forloopcounter++;

					// dd_value.click();
					Actions act = new Actions(driver);
					act.moveToElement(dd_value).sendKeys(Keys.ARROW_DOWN).build().perform();
					Thread.sleep(500);

					// String texValue2=fnpGetText_WebElement( "Multiple Selection Drop
					// down",dd_value);

					if (dd_value.getText().trim().equals(expvalue)) {

						dd_value.click();
						Thread.sleep(1000);

						ValueMatched = true;
						break;
					} else {
						Thread.sleep(500);
						act.moveToElement(dd_value).sendKeys(Keys.ARROW_DOWN).build().perform();

						// Thread.sleep(1000);
						Thread.sleep(500);
					}
				}

				if (ValueMatched != true) {
					fnpDesireScreenshot("ValueMissingInList" + expvalue);
					throw new Exception("Excel value --'" + expvalue + "'  is not matched with DropDown Value.");
				}

			}
		} catch (Exception e) {

			String errorMsg = e.getMessage();

			if ((errorMsg.contains("Excel value")) && (errorMsg.contains("is not matched with DropDown Value."))) {
				throw new Exception(errorMsg + "\n\n\n   May be Value [" + expvalue
						+ "] is not present in List ,plz see screenshot [ValueMissingInList" + expvalue + SShots + "]");
			} else {
				fnpDesireScreenshot("fnpMultipleSelectDropDown2" + expvalue);
				// throw e;
				throw new Exception(
						"Failed while selecting value from drop down.  See attached screenshot fnpMultipleSelectDropDown2... and Eror is ---\n\n\n"
								+ e);
			}

		}
	}

	// Taken from Production script
	/*****
	 * Select the value from prime faces Multiple Select Drop Down (for both Li
	 * and option tag)
	 *****/
	public static void fnpMultipleSelectDropDown2(String ORXpath, String expvalue) throws Throwable {

		try {
			fnpWaitForVisible(ORXpath);
			fnpWaitTillVisiblityOfElementNClickable_OR(ORXpath);
			fnpMymsg(" Expected i.e. going to select value in  " + ORXpath + "list is ==> " + expvalue);
			expvalue = expvalue.trim();

			String tagname = null;
			boolean ValueMatched = false;

			if (fnpCountChildElementPresenceImmediately_NotInOR(fnpGetORObjectX(ORXpath), "li") > 0) {
				List<WebElement> objectlist5 = fnpGetORObjectX(ORXpath).findElements(By.tagName("li"));
				tagname = "li";
				fnpMymsg(" li tag is present for this list. ");
				int forloopcounter = -1;

				int listSize = objectlist5.size();
				for (int i = 0; i < listSize; i++) {
					forloopcounter++;

					// dd_value.click();
					Actions act = new Actions(driver);
					act.moveToElement(objectlist5.get(i)).sendKeys(Keys.ARROW_DOWN).build().perform();
					Thread.sleep(500);

					objectlist5 = fnpGetORObjectX(ORXpath).findElements(By.tagName("li"));
					String texValue = fnpGetText_WebElement("Multiple Selection Drop down", objectlist5.get(i)).trim();

					if (texValue.equals(expvalue)) {

						objectlist5.get(i).click();
						Thread.sleep(1000);

						ValueMatched = true;
						break;
					} else {
						Thread.sleep(500);
						objectlist5 = fnpGetORObjectX(ORXpath).findElements(By.tagName("li"));
						act.moveToElement(objectlist5.get(i)).sendKeys(Keys.ARROW_DOWN).build().perform();

						// Thread.sleep(1000);
						Thread.sleep(500);
					}
				}

				if (ValueMatched != true) {
					fnpDesireScreenshot("ValueMissingInList" + expvalue);
					throw new Exception("Excel value --'" + expvalue + "'  is not matched with DropDown Value.");
				}

			}
		} catch (Exception e) {

			String errorMsg = e.getMessage();

			if ((errorMsg.contains("Excel value")) && (errorMsg.contains("is not matched with DropDown Value."))) {
				throw new Exception(errorMsg + "\n\n\n   May be Value [" + expvalue
						+ "] is not present in List ,plz see screenshot [ValueMissingInList" + expvalue + SShots + "]");
			} else {
				fnpDesireScreenshot("fnpMultipleSelectDropDown2" + expvalue);
				// throw e;
				throw new Exception(
						"Failed while selecting value from drop down.  See attached screenshot fnpMultipleSelectDropDown2... and Eror is ---\n\n\n"
								+ e);
			}

		}
	}

	/********************
	 * without using much wait, to make it fast than fnpMultipleSelectDropDown2
	 ***************************/
	public static void fnpMultipleSelectDropDown3(String ORXpath, String expvalue) throws Throwable {

		try {
			fnpWaitForVisible(ORXpath);
			fnpWaitTillVisiblityOfElementNClickable_OR(ORXpath);
			fnpMymsg(" Expected i.e. going to select value in  " + ORXpath + "list is ==> " + expvalue);
			expvalue = expvalue.trim();

			String tagname = null;
			boolean ValueMatched = false;

			if (fnpCountChildElementPresenceImmediately_NotInOR(fnpGetORObjectX(ORXpath), "li") > 0) {
				List<WebElement> objectlist5 = fnpGetORObjectX(ORXpath).findElements(By.tagName("li"));
				tagname = "li";
				fnpMymsg(" li tag is present for this list. ");
				int forloopcounter = -1;

				int listSize = objectlist5.size();
				for (int i = 0; i < listSize; i++) {
					forloopcounter++;

					// dd_value.click();
					Actions act = new Actions(driver);
					act.moveToElement(objectlist5.get(i)).sendKeys(Keys.ARROW_DOWN).build().perform();
					// Thread.sleep(500);

					objectlist5 = fnpGetORObjectX(ORXpath).findElements(By.tagName("li"));
					String texValue = fnpGetText_WebElement("Multiple Selection Drop down", objectlist5.get(i)).trim();

					if (texValue.equals(expvalue)) {

						objectlist5.get(i).click();
						Thread.sleep(1000);

						ValueMatched = true;
						break;
					} else {
						// Thread.sleep(500);
						objectlist5 = fnpGetORObjectX(ORXpath).findElements(By.tagName("li"));
						act.moveToElement(objectlist5.get(i)).sendKeys(Keys.ARROW_DOWN).build().perform();

						// Thread.sleep(1000);
						// Thread.sleep(500);
					}
				}

				if (ValueMatched != true) {
					fnpDesireScreenshot("ValueMissingInList" + expvalue);
					throw new Exception("Excel value --'" + expvalue + "'  is not matched with DropDown Value.");
				}

			}
		} catch (Exception e) {

			String errorMsg = e.getMessage();

			if ((errorMsg.contains("Excel value")) && (errorMsg.contains("is not matched with DropDown Value."))) {
				throw new Exception(errorMsg + "\n\n\n   May be Value [" + expvalue
						+ "] is not present in List ,plz see screenshot [ValueMissingInList" + expvalue + SShots + "]");
			} else {
				fnpDesireScreenshot("fnpMultipleSelectDropDown2" + expvalue);
				// throw e;
				throw new Exception(
						"Failed while selecting value from drop down.  See attached screenshot fnpMultipleSelectDropDown2... and Eror is ---\n\n\n"
								+ e);
			}

		}
	}

	/****************
	 * like Org Unit in Search Tasks screen
	 ****************************/
	public static void fnpMultipleSelectDropDown_havingFilterBox_and_checkboxes_old(String dropdown_orXpath,
			String filterCheckBox_orXpath, String ok_link_orXpath, String values, String label_orXpath)
			throws Throwable {

		fnpClick_OR(dropdown_orXpath);
		Thread.sleep(2000);
		if (!(filterCheckBox_orXpath.trim().equalsIgnoreCase(""))) {
			fnpClick_OR(filterCheckBox_orXpath);
			Thread.sleep(2000);
			fnpClick_OR(filterCheckBox_orXpath);

			fnpClick_OR(ok_link_orXpath);

			fnpClick_OR(dropdown_orXpath);
		}

		String valueList = values;
		String valuesArray[];
		if (valueList.contains(":")) {
			valuesArray = valueList.split(":");
		} else {
			if (valueList.contains(",")) {
				valuesArray = valueList.split(",");
			} else {

				/*
				 * msg="Please either mention ':' or ',' as a separator in values";
				 * fnpMymsg(msg);
				 * throw new Exception(msg);
				 */
				valuesArray = valueList.split(",");
			}
		}

		int valueArraySize = valuesArray.length;
		String labelXpath;

		List<WebElement> valuesElement = driver.findElements(By.xpath(OR.getProperty(label_orXpath)));
		int valuesElementSize = valuesElement.size();

		WebElement hoverElement;
		Actions action;
		String currentValue;
		int selectionCounter = 0;
		for (int i1 = 0; i1 < valuesElementSize; i1++) {

			hoverElement = valuesElement.get(i1);
			action = new Actions(driver);
			action.moveToElement(hoverElement).perform();
			// Thread.sleep(500);
			action.moveToElement(hoverElement).sendKeys(Keys.ARROW_DOWN).build().perform();
			// Thread.sleep(500);

			currentValue = hoverElement.getText();
			if (valueList.contains(currentValue)) {
				fnpMymsg("@  --now going to select/click on ---" + currentValue);
				Thread.sleep(1000);
				hoverElement.click();
				Thread.sleep(1000);
				selectionCounter++;
				fnpMymsg("@  -- selected/clicked on ---" + currentValue);
				fnpMymsg("@  -- selectionCounter value is just after clicked '" + currentValue + "' is -- -"
						+ selectionCounter);
			}

			if (selectionCounter == (valueArraySize)) {
				fnpMymsg("@  -- selectionCounter value is--" + selectionCounter);
				fnpMymsg("@  -- Values array size is--" + valueArraySize);
				fnpMymsg("both are equal");
				fnpClick_OR(ok_link_orXpath);
				break;
			}

		}
	}

	/****************
	 * like Org Unit in Search Tasks screen
	 ****************************/
	public static void fnpMultipleSelectDropDown_havingFilterBox_and_checkboxes(String dropdown_orXpath,
			String filterCheckBox_orXpath, String ok_link_orXpath, String values, String label_orXpath)
			throws Throwable {

		fnpClick_OR(dropdown_orXpath);
		Thread.sleep(2000);
		if (!(filterCheckBox_orXpath.trim().equalsIgnoreCase(""))) {
			fnpClick_OR(filterCheckBox_orXpath);
			Thread.sleep(2000);
			fnpClick_OR(filterCheckBox_orXpath);

			fnpClick_OR(ok_link_orXpath);

			fnpClick_OR(dropdown_orXpath);
		}

		String valueList = values;
		String valuesArray[];
		if (valueList.contains(":")) {
			valuesArray = valueList.split(":");
		} else {
			if (valueList.contains(",")) {
				valuesArray = valueList.split(",");
			} else {

				/*
				 * msg="Please either mention ':' or ',' as a separator in values";
				 * fnpMymsg(msg);
				 * throw new Exception(msg);
				 */
				valuesArray = valueList.split(",");
			}
		}

		int valueArraySize = valuesArray.length;
		String labelXpath;

		List<WebElement> valuesElement = driver.findElements(By.xpath(OR.getProperty(label_orXpath)));
		int valuesElementSize = valuesElement.size();

		WebElement hoverElement;
		Actions action;
		String currentValue;
		int selectionCounter = 0;
		boolean found = false;

		for (int j = 0; j < valueArraySize; j++) {
			found = false;

			fnpType("OR", "OrgFilterTextBoxXpath", valuesArray[j]);

			valuesElement = driver.findElements(By.xpath(OR.getProperty(label_orXpath)));
			valuesElementSize = valuesElement.size();

			for (int i1 = 0; i1 < valuesElementSize; i1++) {

				hoverElement = valuesElement.get(i1);
				action = new Actions(driver);
				action.moveToElement(hoverElement).perform();
				// Thread.sleep(500);
				action.moveToElement(hoverElement).sendKeys(Keys.ARROW_DOWN).build().perform();

				// Thread.sleep(500);

				currentValue = hoverElement.getText();
				// if (valuesArray[j].contains(currentValue)) {
				if (valuesArray[j].equalsIgnoreCase(currentValue)) {
					fnpMymsg("@  --now going to select/click on ---" + currentValue);
					Thread.sleep(1000);
					hoverElement.click();
					Thread.sleep(1000);
					selectionCounter++;
					fnpMymsg("@  -- selected/clicked on ---" + currentValue);
					fnpMymsg("@  -- selectionCounter value is just after clicked '" + currentValue + "' is -- -"
							+ selectionCounter);

					found = true;
				}

			}

			if (!(found)) {
				msg = "This value '" + valuesArray[j] + "' is not present in the drop down.";
				fnpMymsg(msg);
				throw new Exception(msg);

			}

		}

		/*
		 * if (selectionCounter == (valueArraySize)) {
		 * fnpMymsg("@  -- selectionCounter value is--" + selectionCounter);
		 * fnpMymsg("@  -- Values array size is--" + valueArraySize);
		 * fnpMymsg("both are equal");
		 * fnpClick_OR(ok_link_orXpath);
		 * 
		 * }
		 */

		fnpClick_OR(ok_link_orXpath);
	}

	public static void fnpClickALinkInATable(String linkNo) throws Throwable {

		fnpLoading_wait();
		fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
		String Link_Xpath = "//td[@role='gridcell']/a[text()='" + linkNo + "']";
		fnpWaitTillVisiblityOfElementNClickable_NOR(Link_Xpath);
		fnpGetORObjectX_WhenMultipleObjectsHavingSameXpath_NOR(Link_Xpath).click();
		fnpCheckError("");
		fnpLoading_wait();

	}

	public static void fnpClickALinkInATable(String partialTableDataId, String linkNo) throws Throwable {

		String Link_Xpath = "/tbody[contains(@id,'" + partialTableDataId + "')]//td[@role='gridcell']/a[text()='"
				+ linkNo + "']";
		fnpWaitTillVisiblityOfElementNClickable_NOR(Link_Xpath);

		fnpGetORObjectX_WhenMultipleObjectsHavingSameXpath_NOR(Link_Xpath).click();
		fnpCheckError("");
	}

	public static void fnpClickALinkInATable(String partialTableDataId, String linkNo, int rowNo) throws Throwable {

		String Link_Xpath = "/tbody[contains(@id,'" + partialTableDataId + "')]/tr[" + rowNo
				+ "]/td[@role='gridcell']/a[text()='" + linkNo + "']";
		fnpWaitTillVisiblityOfElementNClickable_NOR(Link_Xpath);

		fnpGetORObjectX_WhenMultipleObjectsHavingSameXpath_NOR(Link_Xpath).click();
		fnpCheckError("");
	}

	public static void fnpClickALinkInATable_advance(String partialTableDataId, String linkNo, int rowNo)
			throws Throwable {
		String Link_Xpath = "//tbody[contains(@id,'" + partialTableDataId + "')]/tr[" + rowNo + "]";
		List<WebElement> elems = driver.findElements(By.xpath(Link_Xpath));
		for (WebElement rowElem : elems) {
			List<WebElement> cells = rowElem.findElements(By.xpath("td"));
			for (WebElement cellsElem : cells) {
				if (cellsElem.getText().equalsIgnoreCase(linkNo)) {
					System.out.println("pradeep--Going to click in a Table inside on this ---" + cellsElem.getText());//
					cellsElem.click();
					fnpCheckError("");

				}
			}
		}
	}

	public static void fnpClickALinkInATable_advance_havingATagInside(String partialTableDataId, String linkNo,
			int rowNo) throws Throwable {
		boolean booleanFlag = false;
		String Link_Xpath = ".//tbody[contains(@id,'" + partialTableDataId + "')]/tr[" + rowNo + "]";
		List<WebElement> elems = driver.findElements(By.xpath(Link_Xpath));
		for (WebElement rowElem : elems) {
			List<WebElement> cells = rowElem.findElements(By.xpath("td/a"));
			for (WebElement cellsElem : cells) {
				if (cellsElem.getText().equalsIgnoreCase(linkNo)) {
					fnpMymsg("---");
					fnpMymsg("pradeep--Going to click in a Table inside on this ---" + cellsElem.getText());//
					cellsElem.click();
					booleanFlag = true;
					fnpCheckError("");
					break;

				}
			}
		}

		if (booleanFlag) {
			fnpMymsg("Found and Clicked successfully.");
		} else {
			String msg = "This link no. '" + linkNo + "' could not found.";
			fnpMymsg(msg);
			throw new Exception(msg);
		}
	}

	public static List<WebElement> fnpReturnWebElementAfterExtractLocatorandValue(String s) {

		String[] s1 = s.split("~");
		String locatorValue = s1[1];

		String locator = s1[0];

		if (locator.equalsIgnoreCase("id")) {// id
			return driver.findElements(By.id(locatorValue));
		} else {
			if (locator.equalsIgnoreCase("name")) {// name
				return driver.findElements(By.name(locatorValue));
			} else {
				if (locator.toLowerCase().equalsIgnoreCase("linktext")) {// linkText
					return driver.findElements(By.linkText(locatorValue));
				} else {
					if (locator.toLowerCase().equalsIgnoreCase("partialLinkText")) {// partialLinkText
						return driver.findElements(By.partialLinkText(locatorValue));
					} else {
						if (locator.toLowerCase().equalsIgnoreCase("tagName")) {// tagName
							return driver.findElements(By.tagName(locatorValue));
						} else {
							if (locator.toLowerCase().equalsIgnoreCase("classname")) {// className
								return driver.findElements(By.className(locatorValue));
							} else {
								if (locator.toLowerCase().equalsIgnoreCase("css")) {// cssSelector
									return driver.findElements(By.cssSelector(locatorValue));
								} else {

									if (locator.toLowerCase().equalsIgnoreCase("xpath")) {// xpath
										return driver.findElements(By.xpath(locatorValue));
									} else {

										// throw new Exception
										// ("Object with name '"+XpathKey+"' is not present in OR");
										/****** By default Xpath will be assumed *****/
										return driver.findElements(By.xpath(locatorValue));
									}
								}
							}
						}
					}
				}
			}
		}

	}

	/********************
	 * Work around before clicking as sometime top menu AJAX open and scirpt
	 * failed when unable to click the link behind
	 **********************/
	public static void fnpWorkAroundToClickbottomFooter() throws Throwable {
		try {
			if (fnpCheckElementEnabledImmediately("FooterElement")) {
				fnpMymsg("@  - Footer element is enabled.");
				fnpMouseHover("FooterElement");
				fnpGetORObjectX("FooterElement").click();
			}
		} catch (Throwable t) {

		}

	}

	public static void fnpPFListUsingFieldName(String fieldName, String value) throws Throwable {

		String ListXpath = "";

		String ListXpath1 = ".//tr/td/label[normalize-space(text())='" + fieldName
				+ "']/../following-sibling::td/div[contains(@class,'ui-selectonemenu ui-widget')]/label";
		String ListXpath2 = ".//tr/td/label[starts-with(normalize-space(text()),'" + fieldName
				+ "')]/../following-sibling::td/div[contains(@class,'ui-selectonemenu ui-widget')]/label";

		String ListXpath3 = ".//tr/td/label[normalize-space(text())='" + fieldName
				+ "']/../../following-sibling::tr/td/div[contains(@class,'ui-selectonemenu ui-widget')]/label";
		String ListXpath4 = ".//tr/td/label[starts-with(normalize-space(text()),'" + fieldName
				+ "')]/../../following-sibling::tr/td/div[contains(@class,'ui-selectonemenu ui-widget')]/label";

		// int total = driver.findElements(By.xpath(ListXpath)).size();

		// fnpCheckElementPresenceImmediately_NotInOR(ListXpath1)
		// fnpCheckElementDisplayed_NOR(Xpath, maxtimeInSeconds)

		if (fnpCheckElementDisplayed_NOR(ListXpath1, 5)) {
			ListXpath = ListXpath1;
		} else if (fnpCheckElementPresenceImmediately_NotInOR(ListXpath2)) {
			ListXpath = ListXpath2;
		} else if (fnpCheckElementPresenceImmediately_NotInOR(ListXpath3)) {
			ListXpath = ListXpath3;
		} else if (fnpCheckElementPresenceImmediately_NotInOR(ListXpath4)) {
			ListXpath = ListXpath4;
		} else {
			throw new Exception("Either there is no drop down field with this name '" + fieldName
					+ "' or we need to create a new xpath for this.");
		}

		if (fnpCheckElementPresenceImmediately_NotInOR(ListXpath)) {

			fnpMymsg("List is present for this field--" + fieldName);
			String p;
			WebElement oList;

			oList = driver.findElement(By.xpath(ListXpath));
			p = oList.getAttribute("id");

			String pp = p.replace("_label", "");
			pp = pp.trim();
			String labelid = pp.trim() + "_label";

			int timer = 0;
			while (true) {
				try {

					oList = driver.findElement(By.xpath(ListXpath));

					oList.click();

					break;
				} catch (StaleElementReferenceException s) {
					Thread.sleep(1000);
					timer = timer + 1;
					if (timer > Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
						break;
					}
				}
			}
			Thread.sleep(1500);

			String panelId = pp.trim() + "_panel";
			String listValue = ".//*[@id='" + panelId + "']" + "/div/ul/li[@data-label='" + value + "']";
			int iCounter = 1;
			while (true) {
				try {
					List<WebElement> objectlistValues = driver.findElement(By.xpath(".//*[@id='" + panelId + "']"))
							.findElements(By.tagName("li"));
					boolean ValueMatched = false;
					;
					for (WebElement dd_value : objectlistValues) {
						Actions act = new Actions(driver);
						// move to current element
						act.moveToElement(dd_value).build().perform();
						// move to next element
						// act.moveToElement(dd_value).sendKeys(Keys.ARROW_DOWN).build().perform();
						// //System.out.println(" ------" + dd_value.getText());
						if (dd_value.getText().equals(value)) {

							Thread.sleep(1000);

							if (browserName.equalsIgnoreCase("IE")) {
								act.moveToElement(dd_value).sendKeys(Keys.ARROW_DOWN).build().perform();
								Thread.sleep(200);
								act.moveToElement(dd_value).sendKeys(Keys.ARROW_DOWN).build().perform();

							}

							dd_value.click();
							fnpLoading_wait();
							ValueMatched = true;
							break;
						}

					}
					if (ValueMatched == false) {
						throw new Exception("Excel value --'" + value + "'  is not matched with DropDown Value.");
					}

					break;
				} catch (Throwable t) {

					if (iCounter < 3) {
						iCounter++;
					} else {
						break;

					}
				}
			}

			String selectedlistValue = "";
			try {
				selectedlistValue = driver.findElement(By.xpath(ListXpath)).getText();
			} catch (Throwable t) {
				Thread.sleep(2000);
				selectedlistValue = driver.findElement(By.xpath(ListXpath)).getText();
			}

			Assert.assertEquals(selectedlistValue, value,
					"Fail due to Actual '" + selectedlistValue + "' and expected '" + value + "' are not matched.");

			fnpMymsg("");
			fnpMymsg("");

		}

	}

	public static void fnpPFListUsingFieldName(String fieldName, int liNo) throws Throwable {

		boolean selectedProperly = false;
		String finalValueToBeSelect = "";

		String ListXpath = "";

		String ListXpath1 = ".//tr/td/label[normalize-space(text())='" + fieldName
				+ "']/../following-sibling::td/div[contains(@class,'ui-selectonemenu ui-widget')]/label";
		String ListXpath2 = ".//tr/td/label[starts-with(normalize-space(text()),'" + fieldName
				+ "')]/../following-sibling::td/div[contains(@class,'ui-selectonemenu ui-widget')]/label";

		String ListXpath3 = ".//tr/td/label[normalize-space(text())='" + fieldName
				+ "']/../../following-sibling::tr/td/div[contains(@class,'ui-selectonemenu ui-widget')]/label";
		String ListXpath4 = ".//tr/td/label[starts-with(normalize-space(text()),'" + fieldName
				+ "')]/../../following-sibling::tr/td/div[contains(@class,'ui-selectonemenu ui-widget')]/label";

		int intWhileCounter = 0;
		while (true) {
			intWhileCounter++;

			try {

				// int total = driver.findElements(By.xpath(ListXpath)).size();

				// fnpCheckElementPresenceImmediately_NotInOR(ListXpath1)
				// fnpCheckElementDisplayed_NOR(Xpath, maxtimeInSeconds)

				if (fnpCheckElementDisplayed_NOR(ListXpath1, 5)) {
					ListXpath = ListXpath1;
				} else if (fnpCheckElementPresenceImmediately_NotInOR(ListXpath2)) {
					ListXpath = ListXpath2;
				} else if (fnpCheckElementPresenceImmediately_NotInOR(ListXpath3)) {
					ListXpath = ListXpath3;
				} else if (fnpCheckElementPresenceImmediately_NotInOR(ListXpath4)) {
					ListXpath = ListXpath4;
				} else {
					throw new Exception("Either there is no drop down field with this name '" + fieldName
							+ "' or we need to create a new xpath for this.");
				}

				if (fnpCheckElementPresenceImmediately_NotInOR(ListXpath)) {

					fnpMymsg("List is present for this field--" + fieldName);
					String p;
					WebElement oList;

					oList = driver.findElement(By.xpath(ListXpath));
					p = oList.getAttribute("id");

					String pp = p.replace("_label", "");
					pp = pp.trim();
					String labelid = pp.trim() + "_label";

					int timer = 0;
					while (true) {
						try {

							oList = driver.findElement(By.xpath(ListXpath));

							oList.click();

							break;
						} catch (StaleElementReferenceException s) {
							Thread.sleep(1000);
							timer = timer + 1;
							if (timer > Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
								break;
							}
						}
					}
					Thread.sleep(1500);

					String panelId = pp.trim() + "_panel";

					// String listValue = ".//*[@id='" + panelId + "']" + "/div/ul/li[@data-label='"
					// + value + "']";

					int totalValues = driver.findElements(By.xpath(".//*[@id='" + panelId + "']" + "/div/ul/li"))
							.size();
					if (!(totalValues > 0)) {
						throw new Exception("Drop down is blank --");

					}

					if ((totalValues == 1)) {
						String onlyGivenValue = driver
								.findElement(By.xpath(".//*[@id='" + panelId + "']" + "/div/ul/li[1]")).getText();
						if ((onlyGivenValue.toLowerCase().contains("select"))
								|| (onlyGivenValue.trim().equalsIgnoreCase(""))) {
							throw new Exception("Drop down is blank --");
						}

					}

					String listValue = ".//*[@id='" + panelId + "']" + "/div/ul/li[" + liNo + "]";

					fnpWaitTillVisiblityOfElementNClickable_NOR(listValue);

					String valueToBeSelect = fnpGetText_NOR(listValue);
					if ((valueToBeSelect.toLowerCase().contains("select"))
							|| (valueToBeSelect.trim().equalsIgnoreCase(""))) {
						listValue = ".//*[@id='" + panelId + "']" + "/div/ul/li[" + (liNo + 1) + "]";
					} else {
						listValue = ".//*[@id='" + panelId + "']" + "/div/ul/li[" + liNo + "]";
					}

					finalValueToBeSelect = driver.findElement(By.xpath(listValue)).getText();
					driver.findElement(By.xpath(listValue)).click();

					Thread.sleep(1000);

					fnpMymsg("@ -  Now going to fetch the selected value.");
					// String FinalSelectedValue = fnpGetText_OR(XpathKey).trim();
					String FinalSelectedValue = fnpGetText_NOR(ListXpath);

					fnpMymsg("@ -  Fetched/Selected value is ---" + FinalSelectedValue);
					String value2 = StringUtils.normalizeSpace(FinalSelectedValue);

					if (!(((finalValueToBeSelect.contains(FinalSelectedValue)))
							|| ((finalValueToBeSelect.contains(value2))))) {
						msg = "@ - '" + finalValueToBeSelect + "'  is NOT selected properly in  DropDown as expected is"
								+ "--'" + finalValueToBeSelect + "' and actual is --'"
								+ FinalSelectedValue + "' .";
						fnpMymsg(msg);
						throw new Exception(msg);
					} else {
						fnpMymsg("@ - '" + finalValueToBeSelect + "'  is  selected properly in  DropDown as expected is"
								+ "--'" + finalValueToBeSelect + "' and actual is --'"
								+ FinalSelectedValue + "' .");
						selectedProperly = true;
						break;
					}

				} else {
					msg = "This list (drop down) is not present.";
					fnpMymsg(msg);
					throw new Exception(msg);
				}

			} catch (Throwable t) {
				fnpMymsg(
						"@ - fnpPFList is failed due to some error but try again in catch block. Previous error caught was ---"
								+ t.getMessage());
				Thread.sleep(2000);
				if (intWhileCounter > 3) {
					fnpMymsg("@ - NOT try again and gong to exit after fnpPFList is failed for chance ----"
							+ intWhileCounter);
					break;
				} else {
					// again in while loop
					fnpMymsg("@ - try again after fnpPFList is failed for chance ----" + intWhileCounter);
				}

			}

		}

	}

	/*********** Wait till main loading icon overs *************/
	public static void fnpLoading_wait_In_SAM_OLD() throws Throwable {
		int i = 0;

		fnpCheckError_In_SAM(" ");

		int iwhileCounter = 0;
		while (true) {
			iwhileCounter++;
			if (fnpCheckElementDisplayedImmediately("SAM_loadingtxtXpath1")
					|| fnpCheckElementDisplayedImmediately("SAM_loadingtxtXpath2")
					|| fnpCheckElementDisplayedImmediately("SAM_loadingImageXpath")) {
				fnpMymsg("@@@   main loading is visible in SAM - fnpLoading_wait_In_SAM--" + i);
				break;
			} else {

				if (iwhileCounter > 2) {
					break;
				}

				fnpMymsg("@@@    main loading icon is not visible  in SAM after " + (iwhileCounter) + " seconds");
				Thread.sleep(1000);
				i++;

			}
		}

		i = 0;
		while (fnpCheckElementPresenceImmediately("SAM_loadingtxtXpath1")) {
			Thread.sleep(1000);
			// Thread.sleep(500);
			i++;
			fnpMymsg("@  ------fnpCheckElementPresenceImmediately 'SAM_loadingtxtXpath1'------" + i);
			/*
			 * if (i > Integer.parseInt(CONFIG.getProperty("genMax_waitTime")) * 4) {
			 * break;
			 * 
			 * }
			 */

			int maxT = Integer.parseInt(CONFIG.getProperty("loadingIcon_Max_waitTime"));
			if (i > maxT) {
				throw new Exception("Loading is not getting over even after waiting approx.  " + maxT + " seconds");
				// break;
			}

		}

		i = 0;
		while (fnpCheckElementDisplayedImmediately("SAM_loadingtxtXpath2")) {
			Thread.sleep(1000);
			// Thread.sleep(500);
			i++;
			fnpMymsg("@  ------fnpCheckElementDisplayedImmediately 'SAM_loadingtxtXpath2'------" + i);
			fnpMymsg("@@@   -- in loop of loading is visible ---" + i);
			/*
			 * if (i > Integer.parseInt(CONFIG.getProperty("genMax_waitTime")) * 4) {
			 * break;
			 * 
			 * }
			 */

			int maxT = Integer.parseInt(CONFIG.getProperty("loadingIcon_Max_waitTime"));
			if (i > maxT) {
				throw new Exception("Loading is not getting over even after waiting approx.  " + maxT + " seconds");
				// break;
			}

		}

		i = 0;
		while (fnpCheckElementDisplayedImmediately("SAM_loadingImageXpath")) {
			fnpMymsg("@@@   while loop when loading is visible now inside - SAM_loadingImageXpath--" + i);
			Thread.sleep(1000);
			// Thread.sleep(500);
			i++;
			/*
			 * if (i > Integer.parseInt(CONFIG.getProperty("genMax_waitTime")) * 4) {
			 * break;
			 * 
			 * }
			 */

			int maxT = Integer.parseInt(CONFIG.getProperty("loadingIcon_Max_waitTime"));
			if (i > maxT) {
				throw new Exception("Loading is not getting over even after waiting approx.  " + maxT + " seconds");
				// break;
			}

		}

		fnpCheckError_In_SAM(" after loading ");
		Thread.sleep(2000);

	}

	/*********** Just for SAM loading, not waiting for it to come *************/
	public static void fnpLoading_wait_In_SAM_JustCheckLoadingIcon_NotWaitForIt_OLD() throws Throwable {
		int i = 0;

		fnpCheckError_In_SAM(" ");

		i = 0;
		while (fnpCheckElementPresenceImmediately("SAM_loadingtxtXpath1")) {
			Thread.sleep(1000);
			i++;
			fnpMymsg("@  ------fnpCheckElementPresenceImmediately 'SAM_loadingtxtXpath1'------" + i);

			int maxT = Integer.parseInt(CONFIG.getProperty("loadingIcon_Max_waitTime"));
			if (i > maxT) {
				throw new Exception("Loading is not getting over even after waiting approx.  " + maxT + " seconds");
				// break;
			}

		}

		i = 0;
		while (fnpCheckElementDisplayedImmediately("SAM_loadingtxtXpath2")) {
			Thread.sleep(1000);
			i++;
			fnpMymsg("@  ------fnpCheckElementDisplayedImmediately 'SAM_loadingtxtXpath2'------" + i);
			fnpMymsg("@@@   -- in loop of loading is visible ---" + i);

			int maxT = Integer.parseInt(CONFIG.getProperty("loadingIcon_Max_waitTime"));
			if (i > maxT) {
				throw new Exception("Loading is not getting over even after waiting approx.  " + maxT + " seconds");
				// break;
			}

		}

		i = 0;
		while (fnpCheckElementDisplayedImmediately("SAM_loadingImageXpath")) {
			fnpMymsg("@@@   while loop when loading is visible now inside - SAM_loadingImageXpath--" + i);
			Thread.sleep(1000);
			i++;
			int maxT = Integer.parseInt(CONFIG.getProperty("loadingIcon_Max_waitTime"));
			if (i > maxT) {
				throw new Exception("Loading is not getting over even after waiting approx.  " + maxT + " seconds");
				// break;
			}

		}

		fnpCheckError_In_SAM(" after loading ");

	}

	/***** check error in application page ***/
	public static void fnpCheckError_In_SAM(String msg) throws Throwable {

		// fnpMymsg("@ ---running function fnpCheckError");
		if (fnpCheckElementPresenceImmediately("SAM_errorMessage")) {

			fnpMouseHover_OR_EvenMultipleObjectsHavingSameXpath_usingSpecialForErrorClassOnly("SAM_errorMessage");
			String errMsg = fnpGetText_OR_EvenMultipleObjectsHavingSameXpath_specialForExtractingErrorMsgTextOnlyEvenIfNotVisible(
					"SAM_errorMessage").trim();

			int errorLength = errMsg.length();

			fnpMymsg("@  ----Error length is---" + errorLength);
			if (errorLength > 1) {
				fnpMymsg(" Error is thrown by application " + msg);

				throw new Exception(" Error is thrown by application " + msg + "\n\n Error is --->" + errMsg);

			}

		}

		// fnpMymsg("@ ---running function fnpCheckError");
		if (fnpCheckElementPresenceImmediately("SAM_errorImage")) {

			fnpMouseHover_OR_EvenMultipleObjectsHavingSameXpath_usingSpecialForErrorClassOnly("SAM_errorMessage2");
			String errMsg = fnpGetText_OR_EvenMultipleObjectsHavingSameXpath_specialForExtractingErrorMsgTextOnlyEvenIfNotVisible(
					"SAM_errorMessage2").trim();

			int errorLength = errMsg.length();

			fnpMymsg("@  ----Error length is---" + errorLength);
			if (errorLength > 1) {
				fnpMymsg(" Error is thrown by application " + msg);

				throw new Exception(" Error is thrown by application " + msg + "\n\n Error is --->" + errMsg);

			}

		}

	}

	/**** Function clicking of an object (button etc) which is present in OR ****/
	public static void fnpClick_InSAM_OR(String xpathkey) throws Throwable {

		fnpCheckError_In_SAM(" before clicking -- " + xpathkey);

		int i = 0;

		/*
		 * while (fnpCheckElementPresenceImmediately("BlockedUIScreen")) {
		 * Thread.sleep(1000);
		 * i++;
		 * if (i > Integer.parseInt(CONFIG.getProperty("genMax_waitTime")) * 2) {
		 * break;
		 * 
		 * }
		 * 
		 * }
		 * 
		 */

		i = 0;
		while (fnpCheckElementPresenceImmediately("SAM_loadingtxtXpath1")) {
			Thread.sleep(1000);
			i++;
			fnpMymsg("@  ------fnpCheckElementPresenceImmediately 'SAM_loadingtxtXpath1'------" + i);
			int maxT = Integer.parseInt(CONFIG.getProperty("loadingIcon_Max_waitTime"));
			if (i > maxT) {
				throw new Exception("Loading is not getting over even after waiting approx.  " + maxT + " seconds");
				// break;
			}

		}

		i = 0;
		while (fnpCheckElementDisplayedImmediately("SAM_loadingtxtXpath2")) {
			// Thread.sleep(1000);
			Thread.sleep(1000);
			i++;
			fnpMymsg("@  ------fnpCheckElementDisplayedImmediately 'SAM_loadingtxtXpath2'------" + i);
			fnpMymsg("@@@   -- in loop of loading is visible ---" + i);
			int maxT = Integer.parseInt(CONFIG.getProperty("loadingIcon_Max_waitTime"));
			if (i > maxT) {
				throw new Exception("Loading is not getting over even after waiting approx.  " + maxT + " seconds");
				// break;
			}

		}

		fnpClick_OR_WithoutWait(xpathkey);
		fnsLoading_wait_In_SAM(2);

		i = 0;
		while (fnpCheckElementPresenceImmediately("SAM_loadingtxtXpath1")) {
			Thread.sleep(1000);
			i++;
			fnpMymsg("@  ------fnpCheckElementPresenceImmediately 'SAM_loadingtxtXpath1'------" + i);
			int maxT = Integer.parseInt(CONFIG.getProperty("loadingIcon_Max_waitTime"));
			if (i > maxT) {
				throw new Exception("Loading is not getting over even after waiting approx.  " + maxT + " seconds");
				// break;
			}

		}

	}

	public static String fnpCreateClientOnly(Hashtable<String, String> table) throws Throwable {

		fnpMymsg("Going to create client");

		fnpCommonClickTopMainMenu("Menu", "FirstRefrenceLinkJustBelowToMenu", "Create_Client", "ClientNameTxtBx");

		// fnpLoading_wait();

		fnpPFList("OrgUnit_PFList", "OrgUnit_PFListOptions", table.get("Org_Unit"));

		fnpLoading_wait();
		fnpWaitForVisible("ClientNameTxtBx");
		String clienttxt = table.get("Client") + "  " + fnTimestampDateWithTime();
		fnpType("OR", "ClientNameTxtBx", clienttxt);

		// if default value is not expected then select otherwise leave this field
		if (!(fnpGetText_OR("Type_PFList").equalsIgnoreCase(table.get("Type")))) {
			fnpPFList("Type_PFList", "Type_PFListOptions", table.get("Type"));
			Thread.sleep(1000);
		}

		if (table.get("Auto_Create_PL_same_as_CO").equalsIgnoreCase("Yes")) {
			Thread.sleep(1000);
			fnpGetORObjectX("AutoCreatePLsameasCO_ChkBox").click();
			Thread.sleep(1000);
		}

		String addLine1 = table.get("Address_Line_1");
		fnpGetORObject("Address_Line1_TxtBx_id").sendKeys(addLine1);

		if ((classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.iAg_Create_MemebershipTestCaseName))
				|| (classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.iAg_Certification_AutotestTestCaseName))) {
			fnpGetORObject("ClientAddress_City").sendKeys(table.get("City"));
		}

		fnpPFList("Country_PFList", "Country_PFListOptions", table.get("Country"));
		fnpLoading_wait();
		fnpPFList("State_PFList", "State_PFListOptions", table.get("State"));
		fnpLoading_wait();

		if ((classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.iAg_Create_MemebershipTestCaseName))
				|| (classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.iAg_Certification_AutotestTestCaseName))) {
			// int total=fnpFindNoOfElementsWithThisXpath("iAg_PostalCodeTxtBx");
			fnpType("OR", "iAg_PostalCodeTxtBx", table.get("Postal_Code"));
		} else {
			fnpType("OR", "PostalCodeTxtBx", table.get("Postal_Code"));
		}

		// fnpType("OR", "PostalCodeTxtBx", table.get("Postal_Code"));

		/*
		 * if ((classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.
		 * iAg_Create_MemebershipTestCaseName))
		 * || (classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.
		 * iAg_Certification_AutotestTestCaseName)) ){
		 * fnpType("OR", "Contact_Email", table.get("Contact_Email"));
		 * }
		 * 
		 * if ((classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.
		 * iAg_Create_MemebershipTestCaseName))
		 * || (classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.
		 * iAg_Certification_AutotestTestCaseName)) ){
		 * fnpPFList("TimeZone_PFList", "TimeZone_PFListOptions",
		 * table.get("Time_Zone"));
		 * }
		 * 
		 * 
		 * if ((classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.
		 * iAg_Create_MemebershipTestCaseName))
		 * || (classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.
		 * iAg_Certification_AutotestTestCaseName)) ){
		 * fnpPFList("Salutation_PFList", "Salutation_PFListOptions",
		 * table.get("Salutation"));
		 * }
		 * 
		 */

		if ((classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.iAg_Create_MemebershipTestCaseName))
				|| (classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.iAg_Certification_AutotestTestCaseName))) {
			fnpType("OR", "Contact_Email", table.get("Contact_Email"));
			fnpPFList("TimeZone_PFList", "TimeZone_PFListOptions", table.get("Time_Zone"));
			fnpPFList("Salutation_PFList", "Salutation_PFListOptions", table.get("Salutation"));

			fnpType("OR", "CreateClient_latitude_xpath", table.get("Latitude"));
			fnpType("OR", "CreateClient_longitude_xpath", table.get("Longitude"));

			/*
			 * fnpGetORObjectX("CreateClient_latitude_xpath").sendKeys(table.get("Latitude")
			 * );
			 * fnpGetORObjectX("CreateClient_longitude_xpath").sendKeys(table.get(
			 * "Longitude"));
			 */

		}

		fnpType("OR", "First_NameTxtBx", table.get("First_Name"));
		fnpType("OR", "Last_Name_TxtBx", table.get("Last_Name"));

		fnpType("OR", "Email_TxtBx", table.get("Email"));
		// String phone=table.get("PrimaryContactDetails_Phone");
		// System.out.println("Pradeep phone no. is ---"+phone);

		fnpType("OR", "PrimaryContactDetails_Phone", table.get("PrimaryContactDetails_Phone"));

		if ((classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.iAg_Create_MemebershipTestCaseName))
				|| (classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.iAg_Certification_AutotestTestCaseName))) {
			fnpPFList("iAg_BillToOffice_PFList", "iAg_BillToOffice_PFListOptions", table.get("BillToOffice"));
		} else {
			fnpPFList("BillToOffice_PFList", "BillToOffice_PFListOptions", table.get("BillToOffice"));
		}

		// fnpType("OR","ClientNameTxtBx",clienttxt );

		fnpMouseHover("Create_Btn");

		fnpClick_OR("Create_Btn");
		fnpCheckError(" after clicking  Create Button");

		fnpMymsg("Client has been created.");
		fnpWaitForVisibleHavingMultipleElements("TopMessage3");
		fnpMymsg("Top Message after create ----" + fnpGetText_OR("TopMessage3"));

		String SuccessfulMsg = fnpGetText_OR("TopMessage3");

		Assert.assertTrue(SuccessfulMsg.contains("success"),
				"Top message does not contain 'success' word, so might be client has not been created successfully");

		String ClientNo_text = fnpGetText_OR("ClientNo");

		fnpMymsg("Newly created Client no. is:" + ClientNo_text);

		return ClientNo_text;

	}

	public void fnpReportTestResult(HashMap<String, String> hashXlData, String currentSuiteName,
			Xls_Reader currentSuiteXls, String currentScriptCode, String classNameText, boolean isTestPass)
			throws Throwable {

		int rowNum = currentSuiteXls.getCellRowNum("Test Cases", "TCID", classNameText);
		currentSuiteXls.setCellData("Test Cases", "Last_Execution", rowNum, fnReturnDateWithTimeForExcel());

		// Add Results column to test case sheet
		if (isTestPass) {
			TestUtil.reportDataSetResult(currentSuiteXls, "Test Cases",
					TestUtil.getRowNum(currentSuiteXls, classNameText), "PASS");
			currentSuiteXls.setCellData("Test Cases", "Last_Successful_Execution", rowNum,
					fnReturnDateWithTimeForExcel());
			fnpElapsedTime(currentSuiteName, classNameText, currentScriptCode, "PASS");
		} else {
			TestUtil.reportDataSetResult(currentSuiteXls, "Test Cases",
					TestUtil.getRowNum(currentSuiteXls, classNameText), "FAIL");
			fnpElapsedTime(currentSuiteName, classNameText, currentScriptCode, "FAIL");
		}
		fnpMymsg(
				"=========================================================================================================================================");

		hashXlData.clear();

		fnpCommonCloseBrowsersAndKillProcess();

	}

	public static void fnpWaitTillThisElementIsNoLongerDisplayedTillMaxWait_OR(String ORKey,
			String messageOnThrowingError, int maxTimeInSeconds) throws Exception {
		int maxWait = maxTimeInSeconds;
		int iwhilecounter = 0;
		while (true) {
			iwhilecounter++;
			// if (fnpCheckElementDisplayedImmediately_NOR(identifier)) {
			if (fnpCheckElementDisplayedImmediately(ORKey)) {
				fnpMymsg("This " + ORKey + " is still visible after --" + iwhilecounter + " seconds.");
				Thread.sleep(1000);
				if (iwhilecounter > maxWait) {
					throw new Exception(messageOnThrowingError + maxWait + " seconds.");

				}

			} else {
				break;
			}
		}

	}

	public static void fnpCommoniPulseSearchTestCase(String searchTopMenuLinkOR,
			String textsearchFieldOR, String noToBeSearch, String searchColName, String viewSearchTopHeadingOR,
			String constantViewSearchHeading) throws Throwable {

		fnpCommonClickTopMainMenu("Menu", "FirstRefrenceLinkJustBelowToMenu", searchTopMenuLinkOR, textsearchFieldOR);

		fnpWaitTillVisiblityOfElementNClickable_OR("MainSearchButton");
		fnpWaitForVisible("MainSearchButton");

		fnpType("OR", textsearchFieldOR, noToBeSearch);
		// driver.findElement(By.xpath(".//*[@id='mainForm:itcustno']")).sendKeys(noToBeSearch);
		fnpMymsg("Just after inserting value in " + textsearchFieldOR);

		fnpWaitTillVisiblityOfElementNClickable_OR("MainSearchButton");
		fnpClick_OR("MainSearchButton");

		int searchColNo = fnpFindColumnIndex("StandardSearchTableHeader", searchColName);

		String s = fnpFetchFromStSearchTable(1, searchColNo);
		int j = 0;
		// fnpLoading_wait();
		while (s.contains("No records found") && j < 15) {
			j++;
			Thread.sleep(1000);
			s = fnpFetchFromStSearchTable(1, searchColNo);
		}

		fnpClickALinkInATable(s);

		fnpLoading_wait();
		fnpIpulseDuringLoading();

		String viewHeading = fnpGetText_OR(viewSearchTopHeadingOR);
		// String viewHeading=fnpGetText_OR("GreenTopheadingOfDetailPage");

		Assert.assertTrue(viewHeading.contains(constantViewSearchHeading), "View Page heading does not contain '" +
				constantViewSearchHeading + "' word, so might be View  page is not opened correctly.");

	}

	public static void fnpSetEnglishLanguage() throws Throwable {

		String textp = fnpGetText_OR("DefaultSelectedLanguage");
		if (!(textp.equalsIgnoreCase("English"))) {
			fnpClick_OR("LanguageSelectionDropDown");
			Thread.sleep(5000);
			fnpClick_OR("LanguageSelectionDropDown_ListValues");
			Thread.sleep(5000);
			fnpLoading_wait();
		}

	}

	public void fnpPrintAllValuesInHashMap(HashMap<String, String> hashXlData1) {
		System.out.println(
				"**************************************************************************************************");
		fnpMymsg("**************************************************************************************************");
		int counter = 0;
		for (Map.Entry<String, String> entry : hashXlData1.entrySet()) {
			counter++;
			String key = entry.getKey();
			String value = entry.getValue();
			// do stuff

			System.out.println(counter + ".    " + key + "------" + value);
			fnpMymsg(counter + ".    " + key + "------" + value);

		}

		System.out.println(
				"**************************************************************************************************");
		fnpMymsg("**************************************************************************************************");

		System.out.println("Total keys are --" + counter);
		fnpMymsg("Total keys are --" + counter);
	}

	/************
	 * This AI sometime comes and sometime not and also its location when it comes
	 * is also not confirm, it is in Dietary Supplement
	 ******************/
	public static void fnpCommonForProcessDelistedGMPStandardAI_ifPresent() throws Throwable {

		if (!(delistedGMPSStandardAI_alreadyProcessedFlag)) {
			fnpCommonClickSnapShotTab();
			fnpWaitForVisible("ActionItemTable_EditWO");
			int ActionItemColIndex = fnpFindColumnIndex("ActionItemTable_HeaderRow", actionItemTableActionItemColName);
			int itemDescColIndex = fnpFindColumnIndex("ActionItemTable_HeaderRow", actionItemTableItemDescColName);
			// int actionItemInfoRowNo = fnpFindRow("ActionItemTable_EditWO",
			// actionItemDesc_DelistedGMPStandard, itemDescColIndex);
			int actionItemInfoRowNo = fnpFindRowContainsName_usingForPagingToNotThrowErrorIfNotFoundInCurrentPage(
					"ActionItemTable_EditWO", actionItemDesc_DelistedGMPStandard, itemDescColIndex);
			if (actionItemInfoRowNo > 0) {
				fnpMymsg(" new  action item delighed is coming.");
				fnpProcessAI(actionItemDesc_DelistedGMPStandard, "Completed");
				delistedGMPSStandardAI_alreadyProcessedFlag = true;
			} else {
				fnpMymsg(" new  action item delighed is NOT  coming.");
			}
		}

	}

	/*	
	*//********
		 * X==>. How many pixels to scroll by, along the x-axis (horizontal). Positive
		 * values will scroll to the right, while negative values will scroll to the
		 * left
		 * Y ==>. How many pixels to scroll by, along the y-axis (vertical). Positive
		 * values will scroll down, while negative values scroll up
		 * //https://www.w3schools.com/jsref/met_win_scrollby.asp
		 *****/

	/*
	 * 
	 * JavascriptExecutor js=(JavascriptExecutor)driver;
	 * js.executeScript("window.scrollBy(500,1000)");
	 * Thread.sleep(5000);
	 * js.executeScript("window.scrollBy(500,0)");
	 * Thread.sleep(5000);
	 * js.executeScript("window.scrollBy(0,1000)");
	 * 
	 * Thread.sleep(5000);
	 * js.executeScript("window.scrollBy(0,-1000)");
	 * 
	 * 
	 * 
	 *//*******************************//*	
										
										
										*/

	public static void setWONo(String woNo) {
		workOrderNo = woNo;
	}

	public static void setCurrentSuiteName(String curSuiteName) {
		currentSuiteName = curSuiteName;
	}

	public static void setCurrentSuiteExcel(Xls_Reader curSuiteExcel) {
		currentSuiteXls = curSuiteExcel;
	}

	public static void setLoginAsAndLoginAsFullName_blank() {
		loginAs = "";
		loginAsFullName = "";
	}

	public static void setIsBrowserPresentAlready_false() {
		IsBrowserPresentAlready = false;
	}

	/*
	 * //It will capture blank(black color) screenshot if screen is locked
	 * public static void testIsCaptureScreenshotWhenscreenlocked() throws
	 * Throwable{
	 * String
	 * destLocation="D://WRAS_DATA_MINING_scripts//Result need to send//05-07-2019";
	 * FileUtils.forceMkdir(new File(destLocation));
	 * Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	 * Rectangle screenRectangle = new Rectangle(screenSize);
	 * Robot robot = new Robot();
	 * BufferedImage image = robot.createScreenCapture(screenRectangle);
	 * 
	 * ImageIO.write(image, "png", new File(destLocation+ "//" + "test"));
	 * }
	 * 
	 */
	public void fnpCommonSelectingTasks_InWO(String taskNames) throws Throwable {
		// fnpWaitForVisible("ContinueBtnInTaskTab");

		try {
			fnpWaitForVisible("ContinueBtnInTaskTab");
		} catch (Throwable t) {
			msg = "Either template dialog box is not opened after clicking 'Apply Task Template' button or its xpath has been changed.";
			fnpMymsg(msg);
			throw new Exception(msg);
		}

		String[] TaskTypeArray;
		String TaskTypeString = taskNames.trim();
		if (!TaskTypeString.isEmpty()) {
			// TaskTypeArray = ((String) hashXlData.get("TaskTypeName")).split(",");
			TaskTypeArray = TaskTypeString.split(",");

			for (int i = 0; i < TaskTypeArray.length; i++) {
				String val = TaskTypeArray[i];

				/*
				 * //String TaskTypeCheckboxXpath = "//td[contains(text(),'" + val +
				 * "')]/preceding-sibling::td/div/div[contains(@class,'ui-chkbox-box')]";
				 * String TaskTypeCheckboxXpath = "//td[contains(text(),'" + val +
				 * "')]/preceding-sibling::td/div/div/span[contains(@class,'ui-chkbox-icon ui-icon')]"
				 * ;
				 * driver.findElement(By.xpath(TaskTypeCheckboxXpath)).click(); //
				 * Thread.sleep(1000);
				 */
				// String TaskTypeCheckboxXpath = "//td[contains(text(),'" + val +
				// "')]/preceding-sibling::td/div/div[contains(@class,'ui-chkbox-box')]";
				String TaskTypeCheckboxXpath = "//td[contains(text(),'" + val
						+ "')]/preceding-sibling::td/div/div/span[contains(@class,'ui-chkbox-icon ui-icon')]";
				if (fnpCheckElementPresenceImmediately_NotInOR(TaskTypeCheckboxXpath)) {
					String isitBlank = fnpGetORObjectX___NOR(TaskTypeCheckboxXpath).getAttribute("class");
					if (isitBlank.contains("blank")) {
						try {
							driver.findElement(By.xpath(TaskTypeCheckboxXpath)).click();
							Thread.sleep(1000);
						} catch (Throwable t) {
							throw new Exception("Unable to click on Task Type '" + val + "' in available tasks . ");
						}
					} else {
						fnpMymsg(
								"This task Task Type '" + val + "' is already checked, so not going to check it again");
					}
				} else {
					throw new Exception("May be this  Task Type '" + val + "' is not present in available tasks . ");
				}

			}

		}

		// fnpGetORObjectX("ContinueBtnInTaskTab").click();
		fnpClickInDialog_OR("ContinueBtnInTaskTab");
		fnpWaitTillThisElementIsNoLongerDisplayedTillMaxWait_OR("ContinueBtnInTaskTab",
				"After clicking Continue button and waited 30 seconds, this popup is not getting disappeared", 30);

	}

	public void fnpVerifyTasksAreAlreadySelected_InWO(String alreadySelectedtaskNames, String needToSelect)
			throws Throwable {
		fnpWaitForVisible("ContinueBtnInTaskTab");

		String[] TaskTypeArray;
		String TaskTypeString = alreadySelectedtaskNames.trim();
		if (!TaskTypeString.isEmpty()) {
			TaskTypeArray = alreadySelectedtaskNames.split(",");

			for (int i = 0; i < TaskTypeArray.length; i++) {
				String val = TaskTypeArray[i];

				/*
				 * //String TaskTypeCheckboxXpath = "//td[contains(text(),'" + val +
				 * "')]/preceding-sibling::td/div/div[contains(@class,'ui-chkbox-box')]";
				 * String TaskTypeCheckboxXpath = "//td[contains(text(),'" + val +
				 * "')]/preceding-sibling::td/div/div/span[contains(@class,'ui-chkbox-icon ui-icon')]"
				 * ;
				 * driver.findElement(By.xpath(TaskTypeCheckboxXpath)).click(); //
				 * Thread.sleep(1000);
				 */
				// String TaskTypeCheckboxXpath = "//td[contains(text(),'" + val +
				// "')]/preceding-sibling::td/div/div[contains(@class,'ui-chkbox-box')]";
				String TaskTypeCheckboxXpath = "//td[contains(text(),'" + val
						+ "')]/preceding-sibling::td/div/div/span[contains(@class,'ui-chkbox-icon ui-icon')]";
				if (fnpCheckElementPresenceImmediately_NotInOR(TaskTypeCheckboxXpath)) {
					String isitBlank = fnpGetORObjectX___NOR(TaskTypeCheckboxXpath).getAttribute("class");
					if (isitBlank.contains("blank")) {
						msg = "This task type '" + val
								+ "' is not already selected, but it should have been selected already as per test case.";
						fnpMymsg(msg);
						throw new Exception(msg);

					} else {
						fnpMymsg(
								"This task Task Type '" + val + "' is already checked, as expected  as per test case.");
					}
				} else {
					throw new Exception("May be this  Task Type '" + val + "' is not present in available tasks . ");
				}

			}

		}

		if (needToSelect.trim().equalsIgnoreCase("")) {
			// fnpGetORObjectX("ContinueBtnInTaskTab").click();
			fnpClickInDialog_OR("ContinueBtnInTaskTab");
			fnpWaitTillThisElementIsNoLongerDisplayedTillMaxWait_OR("ContinueBtnInTaskTab",
					"After clicking Continue button and waited 30 seconds, this popup is not getting disappeared", 30);
		} else {
			// nothing to do here, we need to call other method to select the tasks
		}

	}

	/**
	 * x-pixels is the number at x-axis, it moves to the left if number is positive
	 * and it move to the right if number is negative .
	 * y-pixels is the number at y-axis, it moves to the down if number is positive
	 * and it move to the up if number is in negative .
	 * 
	 * @param xAxisPixel
	 * @param yAxisPixel
	 * @throws InterruptedException
	 *                              Example:
	 *                              js.executeScript("window.scrollBy(0,1000)");
	 *                              //Scroll vertically down by 1000 pixels
	 */
	public static void fnpScroll(int xAxisPixel, int yAxisPixel) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(" + xAxisPixel + "," + yAxisPixel + ")");
		Thread.sleep(2000);
	}

	public static void fnpScroll_usingArrowKey_DOWN(WebElement wb, int noOfTimesArroyKeyDownToBeUse) throws Throwable {

		for (int i = 0; i < noOfTimesArroyKeyDownToBeUse; i++) {
			wb.sendKeys(Keys.ARROW_DOWN);
			fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
			Thread.sleep(500);
		}

	}

	public static void fnpScrollToBottom() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(2000);
	}

	public static void fnpScrollToUp() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0," + "-" + " document.body.scrollHeight)");
		Thread.sleep(2000);
	}

	public static void fnpScrollToElement(WebElement wb) throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", wb);
		Thread.sleep(2000);
	}

	public static void fnpBrowseLoading() throws Throwable {
		int iloop = 0;
		int maxloopWait = 10;
		while (true) {
			if (fnpCheckElementDisplayedImmediately("BrowseCancelUploadBtn")) {
				fnpMymsg("@@@   BrowseCancelUploadBtn is visible--" + iloop);
				break;
			} else {

				if (iloop > maxloopWait) {
					fnpMymsg("@@@    BrowseCancelUploadBtn is not visible after " + (maxloopWait + 1) + " seconds");
					break;
				} else {
					fnpMymsg("@@@    BrowseCancelUploadBtn is  visible --" + iloop);
					Thread.sleep(1000);
				}

				iloop++;
			}
		}

		int whileloopcounter = 0;
		while (fnpCheckElementPresenceImmediately("BrowseCancelUploadBtn")) {
			whileloopcounter++;
			if ((whileloopcounter > Integer.parseInt(CONFIG.getProperty("genMax_waitTime")))) {
				fnpMymsg("@  ---still BrowseCancelUploadBtn is present after too much time ---" + whileloopcounter
						+ " seconds");
				break;
			} else {
				fnpMymsg("@  --- BrowseCancelUploadBtn is present after  time ---" + whileloopcounter + " seconds");
			}
			Thread.sleep(1000);
		}

		Thread.sleep(2000);
	}

	public HashMap<String, String> fnpCopyFile_Save_Somewhere_AppendWithCurrentDate(String Upload_fileName)
			throws Throwable {

		// fileName = System.getProperty("user.dir") + "\\docs\\" + (String)
		// hashXlData.get("Upload_fileName");
		String pathforSave = "\\docs\\Temp_using_for_general_suite";

		// String Upload_fileName_WithoutExtension = Upload_fileName.replace(".pdf",
		// "").trim();

		String[] fileNameSplitArray = Upload_fileName.split("\\.");
		String Upload_fileName_WithoutExtension = fileNameSplitArray[0].trim();
		String Upload_fileName_Extension = fileNameSplitArray[1].trim();

		// create temp folder
		String directoryName = System.getProperty("user.dir") + pathforSave;
		File tempFolder = new File(directoryName);
		if (!tempFolder.exists()) {
			APP_LOGS.debug("DIR '" + tempFolder + "' has not been present already");
			FileUtils.forceMkdir(tempFolder);
			// timefolderinLogHistory.mkdir();
			APP_LOGS.debug("DIR '" + tempFolder + "'  has been created");
		} else {
			APP_LOGS.debug("DEBUG: DIR '" + tempFolder + "'  is already  created.");
		}

		// empty the folder
		try {
			File folder = new File(directoryName);
			File[] listOfFiles = folder.listFiles();
			// int filecount = listOfFiles.length;
			int filecount = 0;
			if (listOfFiles != null) {
				filecount = listOfFiles.length;
			}
			// fnpMymsg("Before starting downloading total files present right now in this
			// folder is ----" + filecount);

			if (filecount > 0) {
				fnpMymsg("Folder is not empty, so going to empty it.");
				fnpDeleteAllFilesInAFolder(directoryName);
				cleanDirectory(folder);
				Thread.sleep(5000);
			} else {
				fnpMymsg("Folder is already  empty.");
			}
		} catch (Throwable t) {
			// nothing to do
		}

		// copy file and appending current time stamp
		String copyFileName = System.getProperty("user.dir") + "\\docs\\" + Upload_fileName;
		File file = new File(copyFileName);
		String batchStartExecutionTime1 = batchStartExecutionTime.replaceAll("  ", "_");

		/*
		 * String newFileName_withCompletePath=directoryName+"//"+
		 * Upload_fileName_WithoutExtension+"_"+(batchStartExecutionTime1.replaceAll(
		 * ":", "-"))+".pdf";
		 * String
		 * fileNameOnly=Upload_fileName_WithoutExtension+"_"+(batchStartExecutionTime1.
		 * replaceAll(":", "-"))+".pdf";
		 */

		String newFileName_withCompletePath = directoryName + "//" + Upload_fileName_WithoutExtension + "_"
				+ (batchStartExecutionTime1.replaceAll(":", "-")) + "." + Upload_fileName_Extension;
		String fileNameOnly = Upload_fileName_WithoutExtension + "_" + (batchStartExecutionTime1.replaceAll(":", "-"))
				+ "." + Upload_fileName_Extension;

		if (file.exists()) {
			FileUtils.copyFile(file,
					new File(newFileName_withCompletePath));
		}

		// return newFileName_withCompletePath;

		HashMap<String, String> copyFileNameHashMap = new HashMap();
		copyFileNameHashMap.put("NameWithCompletePath", newFileName_withCompletePath);
		copyFileNameHashMap.put("FileNameOnly", fileNameOnly);
		return copyFileNameHashMap;

	}

	public static void fnpCallProcedure_DELETE_WO_AUTOMATION_DATA_WaterProfile(String woNo) throws Throwable {
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

			connection = fnpGetDBConnection();
			fnpMymsg("Here work order no. is --'" + woNo + "'.");

			// String stringOFCallingGoldenProcedure2 = "{call
			// ECAP.EC_PKG_AUDIT_UTIL.COPY_AUDIT_TRANSACTION_SUBMIT(?,?,?,?)}";
			String stringOFCallingProcedure = "{call ECAP.CLEANUP_WO_AUTOMATION_DATA(?)}";
			CallableStatement cStmt = connection.prepareCall(stringOFCallingProcedure);

			// cStmt.setInt(1, copyFromAudit);
			cStmt.setString(1, woNo);
			// cStmt.registerOutParameter(4, java.sql.Types.VARCHAR);

			// execute COPY_AUDIT_TRANSACTION store procedure
			cStmt.executeUpdate();
			// String outResult = cStmt.getString(4);
			// System.out.println("Stored Procedure 2 Result : " + outResult);
			// fnpMymsg("Stored Procedure Result : " + outResult);

			connection.commit();

			// connection.close();

		} catch (SQLException e) {
			fnpMymsg(
					"*********************************************** Either connection Failed or some different error with Database,   Getting Error >>  "
							+ e.getMessage().trim());
			fnpMymsg(
					"=========================================================================================================================================");
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}

		finally {
			connection.close();
		}
		fnpMymsg("******************");

		// return rate;
	}

	public static void fnpSelectingValueInOrgUnitInIpulse(String value) throws Throwable {

		fnpClick_OR("Org_Unit");
		WebElement expectedElement = null;

		String orgUnitValues = value.trim();
		String orgUnitValue[] = orgUnitValues.split(":");
		int orgUnitValueArraySize = orgUnitValue.length;

		List<WebElement> orgUnitElements = driver.findElements(By.xpath(OR.getProperty("orgUnitLabelXpath")));
		int orgUnitElementSize = orgUnitElements.size();
		// System.out.println("Before search --Total elements are
		// ---"+orgUnitElementSize);

		if (orgUnitElementSize == 0) {
			throw new Exception("Org Unit drop down is blank.");
		}

		WebElement hoverElement;
		Actions action;
		String currentValue;
		int selectionCounter = 0;

		String selectionWay = "UsingFilterBox";// "UsingFilterBox" or "WithougUsingFilterBox"
		String firstValue = "";// using filter box, it is first value
		for (int i1 = 0; i1 < orgUnitElementSize; i1++) {

			if (selectionWay.equalsIgnoreCase("WithougUsingFilterBox")) {

				hoverElement = orgUnitElements.get(i1);
				action = new Actions(driver);
				action.moveToElement(hoverElement).perform();
				// Thread.sleep(500);
				action.moveToElement(hoverElement).sendKeys(Keys.ARROW_DOWN).build().perform();
				// Thread.sleep(500);

				currentValue = hoverElement.getText();
				if (orgUnitValues.contains(currentValue)) {
					fnpMymsg("@  --now going to select/click on ---" + currentValue);
					Thread.sleep(1000);
					hoverElement.click();
					Thread.sleep(1000);
					selectionCounter++;
					fnpMymsg("@  -- selected/clicked on ---" + currentValue);
					fnpMymsg("@  -- selectionCounter value is just after clicked '" + currentValue + "' is -- -"
							+ selectionCounter);
				}

			} else {

				// fnpType("OR", "OrgUnitFilterBoxXpath", orgUnitValue[i1]);
				fnpGetORObjectX("OrgUnitFilterBoxXpath").clear();
				fnpGetORObjectX("OrgUnitFilterBoxXpath").sendKeys(orgUnitValue[i1]);
				Thread.sleep(1000);

				List<WebElement> afterSearchElements = driver
						.findElements(By.xpath(".//*[@id='mainForm:prgmappsrhdd_panel']/div[2]/ul/li"));
				// System.out.println("After search --Total elements are
				// ---"+afterSearchElements.size());

				String expectedValue = "";
				String currentStyle = "";
				expectedElement = null;
				for (int i = 0; i < afterSearchElements.size(); i++) {
					currentStyle = afterSearchElements.get(i).getAttribute("style");
					// System.out.println((i+1) +" ---"+currentStyle);

					if ((currentStyle.contains("display: none"))) {
						// System.out.println("This is NOT our expected text.");

					} else {
						// System.out.println("This is our expected text.");
						expectedValue = afterSearchElements.get(i).getText();
						firstValue = expectedValue;
						expectedElement = afterSearchElements.get(i);
						break;
					}

				}

				if (!(firstValue.equalsIgnoreCase(orgUnitValue[i1]))) {
					throw new Exception(
							"This value '" + orgUnitValue[i1] + "' is not present in the Org Unit drop down.");

				} else {
					// fnpClick_OR("orgUnitFirstLabelXpath");
					expectedElement.click();
					Thread.sleep(1000);
					selectionCounter++;

				}

			}

			if (selectionCounter == (orgUnitValueArraySize)) {
				fnpMymsg("@  -- selectionCounter value is--" + selectionCounter);
				fnpMymsg("@  -- orgUnitValueArraySize value is--" + orgUnitValueArraySize);
				fnpMymsg("both are equal");
				fnpClick_OR("orgUnit_OKLink");
				break;
			}

		}

	}

	public static void fnpSelectingValueInOrgUnit_SearchAI_InIpulse(String value) throws Throwable {

		fnpClick_OR("SearchAI_OrgUnit_expandIcon");
		WebElement expectedElement = null;

		String orgUnitValues = value.trim();
		String orgUnitValue[] = orgUnitValues.split(":");
		int orgUnitValueArraySize = orgUnitValue.length;

		List<WebElement> orgUnitElements = driver.findElements(By.xpath(OR.getProperty("orgUnitLabelXpath")));
		int orgUnitElementSize = orgUnitElements.size();
		// System.out.println("Before search --Total elements are
		// ---"+orgUnitElementSize);

		if (orgUnitElementSize == 0) {
			throw new Exception("Org Unit drop down is blank.");
		}

		WebElement hoverElement;
		Actions action;
		String currentValue;
		int selectionCounter = 0;

		String selectionWay = "UsingFilterBox";// "UsingFilterBox" or "WithougUsingFilterBox"
		String firstValue = "";// using filter box, it is first value
		for (int i1 = 0; i1 < orgUnitElementSize; i1++) {

			if (selectionWay.equalsIgnoreCase("WithougUsingFilterBox")) {

				hoverElement = orgUnitElements.get(i1);
				action = new Actions(driver);
				action.moveToElement(hoverElement).perform();
				// Thread.sleep(500);
				action.moveToElement(hoverElement).sendKeys(Keys.ARROW_DOWN).build().perform();
				// Thread.sleep(500);

				currentValue = hoverElement.getText();
				if (orgUnitValues.contains(currentValue)) {
					fnpMymsg("@  --now going to select/click on ---" + currentValue);
					Thread.sleep(1000);
					hoverElement.click();
					Thread.sleep(1000);
					selectionCounter++;
					fnpMymsg("@  -- selected/clicked on ---" + currentValue);
					fnpMymsg("@  -- selectionCounter value is just after clicked '" + currentValue + "' is -- -"
							+ selectionCounter);
				}

			} else {

				// fnpType("OR", "OrgUnitFilterBoxXpath", orgUnitValue[i1]);
				fnpGetORObjectX("OrgUnitFilterBoxXpath").clear();
				fnpGetORObjectX("OrgUnitFilterBoxXpath").click();
				// fnpGetORObjectX("OrgUnitFilterBoxXpath").sendKeys(orgUnitValue[i1]);

				WebElement wb = fnpGetORObjectX("OrgUnitFilterBoxXpath");
				JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
				// set the text
				jsExecutor.executeScript("arguments[0].value='" + orgUnitValue[i1] + "'", wb);
				Thread.sleep(1000);
				fnpGetORObjectX("OrgUnitFilterBoxXpath").sendKeys(Keys.ENTER);
				Thread.sleep(1000);

				List<WebElement> afterSearchElements = driver
						.findElements(By.xpath(".//*[@id='mainForm:prgmappsrhdd_panel']/div[2]/ul/li"));
				// System.out.println("After search --Total elements are
				// ---"+afterSearchElements.size());

				String expectedValue = "";
				String currentStyle = "";
				expectedElement = null;
				for (int i = 0; i < afterSearchElements.size(); i++) {
					currentStyle = afterSearchElements.get(i).getAttribute("style");
					// System.out.println((i+1) +" ---"+currentStyle);

					if ((currentStyle.contains("display: none"))) {
						// System.out.println("This is NOT our expected text.");

					} else {
						// System.out.println("This is our expected text.");
						expectedValue = afterSearchElements.get(i).getText();
						firstValue = expectedValue;
						expectedElement = afterSearchElements.get(i);
						break;
					}

				}

				if (!(firstValue.equalsIgnoreCase(orgUnitValue[i1]))) {
					throw new Exception(
							"This value '" + orgUnitValue[i1] + "' is not present in the Org Unit drop down.");

				} else {
					// fnpClick_OR("orgUnitFirstLabelXpath");
					expectedElement.click();
					Thread.sleep(1000);
					selectionCounter++;

				}

			}

			if (selectionCounter == (orgUnitValueArraySize)) {
				fnpMymsg("@  -- selectionCounter value is--" + selectionCounter);
				fnpMymsg("@  -- orgUnitValueArraySize value is--" + orgUnitValueArraySize);
				fnpMymsg("both are equal");
				fnpClick_OR("orgUnit_OKLink");
				break;
			}

		}

	}

	public static void fnpIsSubTestCaseRunnable(String classNameString, String subTestCaseName) {
		if (!TestUtil.isSubTestCaseRunnable(currentSuiteXls, classNameString, subTestCaseName)) {

			fnpMymsg("Skipping Subtest Case" + subTestCaseName + " as runmode set to NO");// logs
			fnpMymsg(
					"=========================================================================================================================================");
			throw new SkipException("Skipping Subtest Case" + subTestCaseName + " as runmode set to NO");// reports
		}

		fnpMymsg("Going to execute the script for  '" + subTestCaseName + "'  as runmode set to Yes");// logs

	}

	public static void fnpDoubleClick(WebElement wb) throws Throwable {
		Actions action0 = new Actions(driver);
		action0.moveToElement(wb).doubleClick().build().perform();
		fnpMymsg("PASSED == Doubleclicked done on element >> " + wb);// logs
		Thread.sleep(1000);
		fnpLoading_wait();

	}

	public static void selectTasks() throws Throwable {

		String[] TaskTypeArray = ((String) hashXlData.get("TaskTypeName")).split(",");

		for (int i = 0; i < TaskTypeArray.length; i++) {
			String val = TaskTypeArray[i];
			// String TaskTypeCheckboxXpath = "//td[contains(text(),'" + val +
			// "')]/preceding-sibling::td/div/div[contains(@class,'ui-chkbox-box')]";
			String TaskTypeCheckboxXpath = "//td[contains(text(),'" + val
					+ "')]/preceding-sibling::td/div/div/span[contains(@class,'ui-chkbox-icon ui-icon')]";
			if (fnpCheckElementPresenceImmediately_NotInOR(TaskTypeCheckboxXpath)) {
				String isitBlank = fnpGetORObjectX___NOR(TaskTypeCheckboxXpath).getAttribute("class");
				if (isitBlank.contains("blank")) {
					try {
						driver.findElement(By.xpath(TaskTypeCheckboxXpath)).click();
						Thread.sleep(1000);
					} catch (Throwable t) {
						throw new Exception("Unable to click on Task Type '" + val + "' in available tasks . ");
					}
				} else {
					fnpMymsg("This task Task Type '" + val + "' is already checked, so not going to check it again");
				}
			} else {
				throw new Exception("May be this  Task Type '" + val + "' is not present in available tasks . ");
			}

		}
	}

	public static void fnpCommonLogout() throws Throwable {
		if (fnpCheckElementDisplayedImmediately("logOutBtn2_linkText")) {
			fnpClick_OR("logOutBtn2_linkText");
			Thread.sleep(1000 * 30);
		}
	}

	public static String getTableData_fromAlertName(String alertName) {

		String AlertNameXpath = ".//legend[contains(text(),'" + alertName + "')]";

		String updatedTableHeaderXpath = getTableHeader_fromAlertName(alertName);

		String tableDataXpath = updatedTableHeaderXpath + "/../tbody";
		String table_Data_id = driver.findElement(By.xpath(tableDataXpath)).getAttribute("id");
		String updatedTableDataXpath = ".//*[@id='" + table_Data_id + "']";

		return updatedTableDataXpath;
	}

	public static String getTableHeader_fromAlertName(String alertName) {

		String AlertNameXpath = ".//legend[contains(text(),'" + alertName + "')]";
		String tableHeaderXpath = AlertNameXpath + "/following-sibling::div//thead";
		String table_header_id = driver.findElement(By.xpath(tableHeaderXpath)).getAttribute("id");
		String updatedTableHeaderXpath = ".//*[@id='" + table_header_id + "']";

		return updatedTableHeaderXpath;
	}

	public static void fnpSSO_login(String mainUser, String loginAsUser) throws Throwable {

		fnpType("OR", "SSO_SignIn_Email_txt_field", mainUser);
		fnpClick_OR("SSO_SignIn_Next_btn_field");

		// Thread.sleep(10000);
		// System.out.println("current url is --"+driver.getCurrentUrl());

		// int iWhileCounter = 0;
		// String temp;
		// while(true){
		// iWhileCounter++;
		// temp=driver.getCurrentUrl();

		// if (temp.contains("auth.nsf.org")) {
		// msg="window authentication popup is coming.";
		// System.out.println(msg);
		// fnpMymsg(msg);
		// Thread.sleep(2000);
		// break;
		// }else{
		// msg="window authentication popup is not coming for chance --"+iWhileCounter;
		// System.out.println(msg);
		// fnpMymsg(msg);
		// }

		// if (iWhileCounter >Long.parseLong(CONFIG.getProperty("pageLoadTime"))) {
		// msg="window authentication popup did not come for a long time, script waited
		// for max. "+iWhileCounter+" seocnds before failing it.";
		// fnpMymsg(msg);
		// throw new Exception(msg);
		// }else{
		// Thread.sleep(1000);
		// }

		// }

		// Thread.sleep(5000);

		// Thread.sleep(10000);
		// Runtime.getRuntime().exec(System.getProperty("user.dir") +
		// "\\AutoIt_scripts\\sso_testscriptuser.exe");
		// Runtime.getRuntime().exec(System.getProperty("user.dir") +
		// "\\AutoIt_scripts\\sso_chrome.exe");

		if (browserName.equalsIgnoreCase("chrome")) {
			// Runtime.getRuntime().exec(System.getProperty("user.dir") +
			// "\\AutoIt_scripts\\sso_chrome.exe");
			// Runtime.getRuntime().exec(System.getProperty("user.dir") +
			// "\\AutoIt_scripts\\sso_testscriptuser.exe");

			/*
			 * InetAddress ipAddr = InetAddress.getLocalHost();
			 * fnpMymsg("IP address of this machine is --"+ipAddr.toString());
			 * if ( (ipAddr.toString().contains("10.223.241.14") ) |
			 * (ipAddr.toString().contains("10.223.241.15")) |
			 * (ipAddr.toString().contains("10.223.241.16") )
			 * ) {
			 * 
			 * fnpMymsg("This is remote machine.");
			 * // Runtime.getRuntime().exec(System.getProperty("user.dir") +
			 * "\\AutoIt_scripts\\sso_chrome.exe");
			 * 
			 * } else {
			 * fnpMymsg("This is local machine.");
			 * // Runtime.getRuntime().exec(System.getProperty("user.dir") +
			 * "\\AutoIt_scripts\\sso_testscriptuser.exe");
			 * // Runtime.getRuntime().exec(System.getProperty("user.dir") +
			 * "\\AutoIt_scripts\\sso_chrome.exe");
			 * 
			 * }
			 */

			fnpSSOLoginWorkAround();

		} else {
			if (browserName.equalsIgnoreCase("edge")) {
				Runtime.getRuntime().exec(System.getProperty("user.dir") + "\\AutoIt_scripts\\sso_edge.exe");
			} else {
				msg = "SSO for this browser is not yet implemented";
				fnpMymsg(msg);
				throw new Exception(msg);
			}
		}

		Thread.sleep(5000);

		fnpCheckElementDisplayed(OR.getProperty("UserNametext_Xpath"));
		fnpType("OR", "passwordtext_Xpath", CONFIG.getProperty("adminPassword"));

		fnpClick_OR("SignInBtn_xpath");

		// Thread.sleep(10000);

		if (fnpCheckElementDisplayed("StaySignedInYes_Btn", 5)) {
			fnpMymsg("Stay signed in Pop up is displayed.");
			fnpClick_OR_WithoutWait("StaySignedInYes_Btn");
		}

		Thread.sleep(1000);

		// if (fnpCheckElementDisplayed("PermissionRequested_Accept_btn", 2)) {
		// fnpClick_OR("PermissionRequested_Accept_btn");
		// }

		// Thread.sleep(10000);

		/*
		 * if (fnpCheckElementDisplayed("Pick_an_account_screen_TestScriptUser_link",
		 * 10)) {
		 * fnpMymsg("Pick an account screen is displayed.");
		 * //fnpClick_OR("Pick_an_account_screen_TestScriptUser_link");
		 * fnpClick_OR_WithoutWait("Pick_an_account_screen_TestScriptUser_link");
		 * }else{
		 * fnpMymsg("Pick an account screen is not displayed.");
		 * }
		 * 
		 */

		fnpClick_OR("xpathForAck");
		fnpMymsg("Just after clicking Acknowledge button");
		fnpWaitTillThisElementIsNoLongerDisplayedTillMaxWait_OR("xpathForAck",
				"Acknowledge button is still visible after clicking it", 120);
		fnpMymsg("Just after waiting for Acknowledge button visiblity over");

		if (loginAsUser.equalsIgnoreCase("")) {
			// nothing for now
		} else {
			fnpCommonClickTopMainMenu("Menu", "FirstRefrenceLinkJustBelowToMenu", "SwitchUser_Link",
					"SwitchUser_Go_btn");
			fnpType("OR", "SwitchUser_txtBx", loginAsUser);
			fnpLoading_wait();
			fnpIpulseDuringLoading();

			String Ajax_UserName_Xpath = "//li[@data-item-label='" + loginAsUser.toUpperCase().trim() + "']";
			fnpCheckElementEnabledImmediately_NOR(Ajax_UserName_Xpath);
			Thread.sleep(1000);
			fnpClick_NOR(Ajax_UserName_Xpath);
			fnpLoading_wait();
			fnpIpulseDuringLoading();
			fnpClick_OR("SwitchUser_Go_btn");
		}

	}

	public static void fnpWithout_SSO_login(String siteUrl, String loginAs, String userName, String password)
			throws Throwable {

		fnpWaitTillVisiblityOfElementNClickable_OR("Login_id");
		String handle1 = driver.getWindowHandle();
		String originalHandle1 = driver.getWindowHandle();
		if (!iPulse_Invalid_Password_Verification) {
			if (siteUrl.contains("securelogin")) {
				fnpType("OR", "loginAs", loginAs);
			}
			fnpMymsg("First going to check login with invalid/wrong password.");
			fnpDisplayingMessageInFrame_fnpMymsg("First going to check login with invalid/wrong password.", 10);

			fnpType("OR", "UserName_id", userName);

			fnpType("OR", "password_id", "wrong_password");
			fnpMymsg("Just before login clicked");

			// fnpClick_OR("Login");
			fnpGetORObjectX("Login_id").click();
			// Thread.sleep(2000);
			fnpLoading_wait_withoutErrorChecking();

			fnpMymsg("Just login clicked");
			fnpMymsg("Just after loading wait after login clicked");

			// if (fnpCheckElementDisplayed("errorMessage_id", 30)) {
			if (fnpCheckElementDisplayed("errorMessage_id", 30)) {
				// throw new Exception("Login is not successfull.");
				fnpMymsg("Login is failed as expected due to wrong password");
				fnpMouseHover_OR_EvenMultipleObjectsHavingSameXpath_usingSpecialForErrorClassOnly("errorMessage_id");
				String errMsg = fnpGetText_OR_EvenMultipleObjectsHavingSameXpath_specialForExtractingErrorMsgTextOnlyEvenIfNotVisible(
						"errorMessage_id");
				fnpMymsg(" Error  thrown by application after inserting wrong password is --- " + errMsg);

			} else {

				if (fnpCheckElementDisplayedImmediately("logOutBtn2_linkText")) {
					msg = "Login should not be successfull with wrong password.";
					fnpDisplayingMessageInFrame_fnpMymsg(msg, 10);
					Thread.sleep(1000);
					throw new Exception(msg);
				} else {
					msg = "Error message on inserting either wrong username or wrong password is not coming, hence failed as error message should come on wrong credential details.";
					fnpMymsg(msg);
					fnpDisplayingMessageInFrame_fnpMymsg(msg, 10);
					Thread.sleep(1000);
					throw new Exception(msg);

				}
			}

			fnpMymsg("Now going to refresh the screen and again login with valid user credential details.");
			driver.navigate().refresh();
			iPulse_Invalid_Password_Verification = true;
		}

		if (siteUrl.contains("securelogin")) {
			fnpType("OR", "loginAs", loginAs);
		}

		fnpType("OR", "UserName_id", userName);

		fnpType("OR", "password_id", password);
		fnpMymsg("Just before login clicked");

		fnpMouseHover("Login_id");

		fnpClick_OR("Login_id");
		fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
		fnpLoading_wait();
		fnpLoading_wait_CheckLoadingIconOnlyIniPulse();

		fnpMymsg("Just login clicked");
		fnpMymsg("Just after loading wait after login clicked");

		Set<String> handle2 = driver.getWindowHandles();
		int a = handle2.size();
		if (fnpCheckElementDisplayedImmediately("errorMessage_id")) {
			throw new Exception("Login is not successfull.");
		}

		fnpClick_OR("xpathForAck");
		fnpMymsg("Just after clicking Acknowledge button");
		fnpWaitTillThisElementIsNoLongerDisplayedTillMaxWait_OR("xpathForAck",
				"Acknowledge button is still visible after clicking it", 120);
		fnpMymsg("Just after waiting for Acknowledge button visiblity over");
		try {

			fnpWaitTillVisiblityOfElementNClickable_OR("logOutBtn2_linkText");

		} catch (Throwable t) {
			fnpDesireScreenshot("justBeforeRefreshing");
			fnpMymsg(
					"@  -----due to invalid selector , logoutout link not identified, so refreshing it again. See screenshot 'justBeforeRefreshing' .");
			driver.navigate().refresh();
			Thread.sleep(8000);
			fnpWaitForVisible("logOutBtn2_linkText");
		}
	}

	public static void fnpSwitchUser(String user) throws Throwable {
		fnpCommonClickTopMainMenu("Menu", "FirstRefrenceLinkJustBelowToMenu", "SwitchUser_Link", "SwitchUser_Go_btn");
		fnpType("OR", "SwitchUser_txtBx", user);
		fnpLoading_wait();
		fnpIpulseDuringLoading();

		String Ajax_UserName_Xpath = "//li[@data-item-label='" + user.toUpperCase().trim() + "']";
		fnpCheckElementEnabledImmediately_NOR(Ajax_UserName_Xpath);
		Thread.sleep(1000);
		fnpClick_NOR(Ajax_UserName_Xpath);
		fnpLoading_wait();
		fnpIpulseDuringLoading();
		fnpClick_OR("SwitchUser_Go_btn");
	}

	public static void addDocsToCertDecUpdate() throws Throwable {

		String fileNames = (String) hashXlData.get("CertDecUpdate_AI_FileName");
		String[] fileCount = fileNames.split(",");
		int fileCountSize = fileCount.length;

		if (!(fileCountSize > 0)) {
			throw new Exception("Upload file names should be given in excel.");
		}

		String fileTypes = (String) hashXlData.get("CertDecUpdate_AI_DocType");
		String[] fileTypeArray = fileTypes.split(",");
		int fileTypesCountSize = fileTypeArray.length;

		if (!(fileTypesCountSize == fileCountSize)) {
			throw new Exception("Upload file type should be equal to the no. of files in excel.");
		}

		String fileDesc = (String) hashXlData.get("CertDecUpdate_AI_Description");
		String[] fileDescArray = fileDesc.split(",");
		int fileDescCountSize = fileDescArray.length;

		if (!(fileDescCountSize == fileCountSize)) {
			throw new Exception("Upload file access should be equal to the no. of files  in excel.");
		}

		String fileName = "";
		String fname;
		String typelist;
		String descXpath;

		// if ((fnpGetCurrRunningBrowserName().equalsIgnoreCase("firefox"))) {
		if ((fnpGetCurrRunningBrowserName().equalsIgnoreCase("firefox"))) {

			for (int f = 0; f < fileCountSize; f++) {

				/*
				 * Thread.sleep(2000);
				 * 
				 * fileName = System.getProperty("user.dir") + "\\docs\\" + fileCount[f];
				 * driver.findElement(By.xpath(OR.getProperty("FailureResoutionAIAddDocBtn"))).
				 * sendKeys(fileName);
				 * Thread.sleep(1000);
				 * while (fnpCheckElementPresenceImmediately("BrowseCancelUploadBtn")) {
				 * Thread.sleep(1000);
				 * }
				 * 
				 * typelist = OR.getProperty("FailResoAddDocsType_PFList_part1") + f +
				 * OR.getProperty("FailResoAddDocsType_PFList_part2");
				 * fnpPFListModify_NOR(typelist, fileTypeArray[f]);
				 * 
				 * descXpath = OR.getProperty("FailResoAddDocsDesc_part1") + f +
				 * OR.getProperty("FailResoAddDocsDesc_part2");
				 * fnpType("", descXpath, fileDescArray[f]);
				 * 
				 * Thread.sleep(2000);
				 */

			}

		} else {

			for (int f = 0; f < fileCountSize; f++) {
				Thread.sleep(2000);
				fname = System.getProperty("user.dir") + "\\docs\\" + fileCount[f];
				if (fnpGetCurrRunningBrowserName().equalsIgnoreCase("chrome")) {
					if (f != fileCountSize - 1) {
						fileName = fileName + fname + "\n";
					} else {
						fileName = fileName + fname;
					}

				} else {
					if (fnpGetCurrRunningBrowserName().equalsIgnoreCase("IE")) {
						fileName = fileName + "\"" + fname + "\"";
					}
				}
			}

			WebElement wb = driver.findElement(By.xpath(OR.getProperty("CertDecUpdateAIAddDocBtn")));
			fnpScrollToElement(wb);

			driver.findElement(By.xpath(OR.getProperty("CertDecUpdateAIAddDocBtn"))).sendKeys(fileName);
			Thread.sleep(1000);
			fnpBrowseLoading();
			fnpScroll(500, 200);

			while (fnpCheckElementPresenceImmediately("BrowseCancelUploadBtn")) {
				Thread.sleep(1000);
			}

			WebElement wb1;
			for (int f = 0; f < fileCountSize; f++) {
				typelist = OR.getProperty("FailResoAddDocsType_PFList_part1") + f
						+ OR.getProperty("FailResoAddDocsType_PFList_part2");
				fnpScrollToElement(driver.findElement(By.xpath(typelist)));
				fnpPFListModify_NOR(typelist, fileTypeArray[f]);

				fnpLoading_wait();
				fnpLoading_wait_CheckLoadingIconOnlyIniPulse();

				descXpath = OR.getProperty("FailResoAddDocsDesc_part1") + f
						+ OR.getProperty("FailResoAddDocsDesc_part2");
				// fnpType("", descXpath, fileDescArray[f]);

				fnpScroll(500, 200);

				wb1 = driver.findElement(By.xpath(descXpath));
				// wb1.sendKeys(Keys.ARROW_DOWN);
				fnpScroll_usingArrowKey_DOWN(wb1, 2);
				// Thread.sleep(1000);
			}

			fnpCheckError("Error is thrown by application while adding data in Document Tab");
		}

	}

	public static void fnptryingKillProcess_usingLoop() {
		try {

			final String KILL = "taskkill /IM ";

			/*
			 * String processName2 = "iexplore.exe"; // IE process
			 * Runtime.getRuntime().exec(KILL + processName2);
			 */

			String processName1 = "chrome.exe"; // chrome process
			Runtime.getRuntime().exec(KILL + processName1);
			for (int i = 0; i < 10; i++) {
				try {
					Runtime.getRuntime().exec(KILL + processName1);
					Thread.sleep(500);
				} catch (Throwable t) {

				}
			}

			String processName3 = "chromedriver.exe"; // chrome process
			Runtime.getRuntime().exec(KILL + processName3);
			for (int i = 0; i < 10; i++) {
				try {
					Runtime.getRuntime().exec(KILL + processName1);
					Thread.sleep(1000);
				} catch (Throwable t) {

				}
			}

			/*
			 * String processName4 = "firefox.exe"; // firefox process
			 * Runtime.getRuntime().exec(KILL + processName4);
			 */
			/*
			 * String processName5 = "netsession_win.exe"; // IE process
			 * Runtime.getRuntime().exec(KILL + processName5);
			 */
			// Thread.sleep(5000); // Allow OS to kill the process

		}

		/*
		 * catch (org.openqa.selenium.os.WindowsRegistryException e) {
		 * // nothing to do
		 * }
		 */
		catch (java.lang.RuntimeException r) {
		}

		catch (Exception ex) {
			// nothing to do
		} catch (Throwable t) {
			// nothing to do
		}
	}

	public static void fnpCheckFacilityDropStatusAndThenRunSQLQueries() throws Throwable {

		// if
		// (fnpCheckElementPresenceImmediately_NotInOR(".//*[@id='mainForm:tabView:appfac']//span[contains(text(),'Unacceptable
		// Facility/Status Combination')]")) {
		if (fnpCheckElementPresenceImmediately("WoInfoTab_facilityStatusDialogBox")) {
			fnpChangeStatusOfDropFacilityToApply((String) hashXlData.get("FacCode"),
					(String) hashXlData.get("StandardCode"));
			msg = "As facility is in drop status,I have run the sql queries to change its status, so please re-run the script. See attached screenshot for reference.";
			fnpMymsg(msg);
			fnpDesireScreenshot("FacilityDropStatus");
			throw new Exception(msg);

			/*
			 * fnpClick_NOR_withoutWait(".//*[@id='mainForm:tabView:appfac']//span[@class='ui-icon ui-icon-closethick']"
			 * );
			 * fnpLoading_wait();
			 * driver.navigate().refresh();
			 */

		} else {
			// nothing to do just proceed
		}

	}

	public static void fnpCheckFacilityDropStatusAndThenRunSQLQueries_atFacilitiesTab() throws Throwable {

		int facilityStatusColIndex = fnpFindColumnIndex("Wo_FacilityTab_FacilityHeader",
				facilityTab_facilityStatusColName);
		// int facilityRowNo = fnpFindRow("ActionItemTable_EditWO", ActionItemName,
		// itemDescColIndex);
		String facilityStatus = fnpFetchFromTable("Wo_FacilityTab_FacilityTable", 1, facilityStatusColIndex);

		if (facilityStatus.equalsIgnoreCase("DROP")) {
			fnpChangeStatusOfDropFacilityToApply((String) hashXlData.get("FacCode"),
					(String) hashXlData.get("StandardCode"));
			msg = "As facility is in drop status,I have run the sql queries to change its status. See attached screenshot for reference.";
			fnpDesireScreenshot("FacilityDropStatus");
			fnpMymsg(msg);
			// throw new Exception(msg);

		} else {
			// nothing to do just proceed
		}

	}

	public void fnpCreateWO() throws Throwable {

		try {

			fnpClick_OR("ClientLKPBtn_id");
			fnpSearchNSelectFirstRadioBtn(1, (String) hashXlData.get("Client"), 1);

			String EnteredClient = fnpWaitTillTextBoxDontHaveValue("ClientTxtBox_id", "value");
			Assert.assertTrue(EnteredClient.contains((String) hashXlData.get("Client")),
					"Client Value is not selected properly from lookup");
			fnpMymsg(" Client value is properly selected from client lookup");

			Thread.sleep(100);

			if ((currentSuiteName.equalsIgnoreCase(Dietary_Supplement_suite_Name))) {
				fnpPFList("SolutionTypeList", "SolutionTypeListOptions", (String) hashXlData.get("SolutionType"));
			}

			fnpPFList("WOTypeList", "WOTypeListOptions", (String) hashXlData.get("WOType"));

			fnpClick_OR("NextBtn_id");

			fnpCheckErrMsg("Error thrown by applciation After Click Next Button  ");
			// -------Second Create Work Order Page ----------------------------

			// if (!((currentSuiteName.equalsIgnoreCase(Wales_Work_Order_suite_Name))) ){

			fnpMymsg("Just before going to click AccountManagerLkpBtn");

			fnpWaitForVisible("AccountManagerTxtBox_id");
			String AlreadyAccountManager = fnpGetORObjectX("AccountManagerTxtBox_id").getAttribute("value");

			// if (AlreadyAccountManager.contains((String)
			// hashXlData.get("AccountManager").trim())) {
			String accountManager = null;
			String accountManager_code = null;

			if (!(currentSuiteName.equalsIgnoreCase(Non_Food_Compounds_suite_Name))) {

				if ((loginAsFullName == null)
						|| loginAsFullName.equalsIgnoreCase("")
						|| (currentSuiteName.equalsIgnoreCase(Wales_Work_Order_suite_Name))) {
					accountManager = (String) hashXlData.get("AccountManager").trim();
					accountManager_code = (String) hashXlData.get("AccountManager_Code").trim();

				} else {
					accountManager = loginAsFullName;
					accountManager_code = loginUser_code;
				}

				// if (AlreadyAccountManager.contains(accountManager)) {
				if ((AlreadyAccountManager.contains(accountManager))
						|| (AlreadyAccountManager.contains(accountManager_code))) {
					// nothing to do now

				} else {
					fnpClick_OR("AccountManagerLkpBtn_id");

					fnpMymsg("Just after  click AccountManagerLkpBtn");
					fnpMymsg("Just before going to insert value of Account Manger");
					// fnpSearchNSelectFirstRadioBtn(2, accountManager, 1);
					// fnpSearchNSelectFirstRadioBtn(2, "Contains",accountManager, 1);

					if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking"))) {
						fnpSearchNSelectFirstRadioBtn(1, accountManager_code, 1);
					} else {
						fnpSearchNSelectFirstRadioBtn(2, accountManager, 1);
					}

					fnpMymsg("Just after  insert value of Account Manger");
					String EnteredAccountManager = fnpGetORObjectX("AccountManagerTxtBox_id").getAttribute("value");
					EnteredAccountManager = fnpWaitTillTextBoxDontHaveValue("AccountManagerTxtBox_id", "value");
					// Assert.assertTrue(EnteredAccountManager.contains(accountManager), "Account
					// Manager is not selected properly from lookup");
					// Assert.assertTrue(EnteredAccountManager.contains(accountManager_code),
					// "Account Manager is not selected properly from lookup");
					if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking"))) {
						// Assert.assertTrue(EnteredAccountManager.contains(accountManager_code),
						// "Account Manager is not selected properly from lookup");
						Assert.assertTrue(EnteredAccountManager.contains(accountManager_code),
								"Account Manager is not selected properly from lookup because actual value is ---'"
										+ EnteredAccountManager + "' and expected is --'" + accountManager_code + "'.");
					} else {
						Assert.assertTrue(EnteredAccountManager.contains(accountManager),
								"Account Manager is not selected properly from lookup because actual value is ---'"
										+ EnteredAccountManager + "' and expected is --'" + accountManager + "'.");
					}

					fnpMymsg(" Account Manager is properly selected from client lookup");
				}

			} else {
				String expectedDefaultAccountManager = (String) hashXlData.get("AccountManager").trim();
				String expectedDefaultAccountManager_code = (String) hashXlData.get("AccountManager_Code").trim();
				if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking"))) {
					Assert.assertTrue(AlreadyAccountManager.contains(expectedDefaultAccountManager_code),
							"Expected default account manager is not present. Actual is this '" + AlreadyAccountManager
									+ "' but expected is this  '" + expectedDefaultAccountManager_code + "'.");
				} else {
					Assert.assertTrue(AlreadyAccountManager.contains(expectedDefaultAccountManager),
							"Expected default account manager is not present. Actual is this '" + AlreadyAccountManager
									+ "' but expected is this  '" + expectedDefaultAccountManager + "'.");
				}
			}

			// }

			// if (!(currentSuiteName.equalsIgnoreCase(Dietary_Supplement_suite_Name)) ){
			if ((!(currentSuiteName.equalsIgnoreCase(Dietary_Supplement_suite_Name)))
					& (!(currentSuiteName.equalsIgnoreCase(Non_Food_Compounds_suite_Name)))) {
				fnpClick_OR("BDMLkpBtn_id");
				// fnpSearchNSelectFirstRadioBtn(2, (String) hashXlData.get("BDM"), 1);
				// fnpSearchNSelectFirstRadioBtn(1, (String) hashXlData.get("BDM_Code"), 1);

				if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking"))) {
					fnpSearchNSelectFirstRadioBtn(1, (String) hashXlData.get("BDM_Code"), 1);
				} else {
					fnpSearchNSelectFirstRadioBtn(2, (String) hashXlData.get("BDM"), 1);
				}

				String EnteredBDM = fnpGetORObjectX("BDMTxtBox_id").getAttribute("value");
				EnteredBDM = fnpWaitTillTextBoxDontHaveValue("BDMTxtBox_id", "value");
				// Assert.assertTrue(EnteredBDM.contains((String) hashXlData.get("BDM")), "BDM
				// is not selected properly from lookup");
				// Assert.assertTrue(EnteredBDM.contains((String) hashXlData.get("BDM_Code")),
				// "BDM is not selected properly from lookup");
				if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking"))) {
					Assert.assertTrue(EnteredBDM.contains((String) hashXlData.get("BDM_Code")),
							"BDM is not selected properly from lookup  because actual value is ---'" + EnteredBDM
									+ "' and expected is --'" + (String) hashXlData.get("BDM_Code") + "'.");
				} else {
					Assert.assertTrue(EnteredBDM.contains((String) hashXlData.get("BDM")),
							"BDM is not selected properly from lookup  because actual value is ---'" + EnteredBDM
									+ "' and expected is --'" + (String) hashXlData.get("BDM") + "'.");
				}
				fnpMymsg(" BDM is properly selected from client lookup");
			}

			/*
			 * if (!(currentSuiteName.equalsIgnoreCase(Dietary_Supplement_suite_Name)) ){
			 * fnpPFList("WoPrimContactList", "WoPrimContactListOptions", (String)
			 * hashXlData.get("WOPrimaryContact"));
			 * }else{
			 * fnpPFListByLiNo("WoPrimContactList", "WoPrimContactListOptions", 1);
			 * }
			 */
			fnpPFListByLiNo("WoPrimContactList", "WoPrimContactListOptions", 1);

			String subType = (String) hashXlData.get("WO_Sub_Type").trim();
			fnpGetORObjectX("WOSummaryTxtBox_id")
					.sendKeys(subType + "--" + (String) hashXlData.get("WOSummary") + fnTimestampDateWithTime());

			fnpType("OR", "WOScopeTxtBox_id", (String) hashXlData.get("WOScope"));

			if (!(classNameText.equalsIgnoreCase("WO_Annual"))) {
				if (!(currentSuiteName.equalsIgnoreCase(Non_Food_Compounds_suite_Name))) {
					fnpType("OR", "CreateWOProductType", (String) hashXlData.get("ProductType"));
					fnpWaitForVisible("CreateWO_ProductTypeOptions");
					String xpathProductTypeValue = OR.getProperty("CreateWO_ProductTypeOptionsList")
							+ "[@data-item-label='" + (String) hashXlData.get("ProductType").trim() + "']";
					fnpWaitForVisible_NotInOR(xpathProductTypeValue);
					fnpMouseHover_NotInOR(xpathProductTypeValue);
					driver.findElement(By.xpath(xpathProductTypeValue)).click();
					// fnpLoading_wait();
					fnpMymsg("Product type has been inserted is " + fnpGetAttribute_OR("CreateWOProductType", "value"));
				} else {
					fnpPFList("NFC_ProductTypePFList", "NFC_ProductTypePFListOptions",
							(String) hashXlData.get("ProductType"));
					fnpPFList("NFC_ProductTypePFList", "NFC_ProductTypePFListOptions",
							(String) hashXlData.get("Second_ProductType"));
				}

			}

			fnpLoading_wait();

			/*
			 * if ( (currentSuiteName.equalsIgnoreCase(Non_Food_Compounds_suite_Name))
			 * | (currentSuiteName.equalsIgnoreCase(Wales_Work_Order_suite_Name)) ){
			 * Thread.sleep(10000);
			 * fnpLoading_wait_specialCase(5);// putting hard code 5 seconds wait because
			 * below element is dependent on previous one and after selecting values in
			 * previous once, other elements little bit refreshed.
			 * fnpPFList("WO_SubTypePFList", "WO_SubTypePFListOptions", (String)
			 * hashXlData.get("WO_Sub_Type"));
			 * }
			 */

			switch (currentSuiteName) {
				case Non_Food_Compounds_suite_Name:
					fnpPFList("WO_SubTypePFList", "WO_SubTypePFListOptions", (String) hashXlData.get("WO_Sub_Type"));
					break;

				case Wales_Work_Order_suite_Name:
					fnpPFList("Wales_WoSubTypeList", "Wales_WoSubTypeOptions", (String) hashXlData.get("WO_Sub_Type"));
					break;

				default:
					break;

			}

			/**********
			 * Commented on Karen Request to pick first one only, subject of mail
			 * is--requirements too specific
			 ***/
			/*
			 * fnpPFList("InvoiceBillToPFList", "InvoiceBillToPFListOptions", (String)
			 * hashXlData.get("FinancialTab_BillToInvoice"));
			 * String insertedBillToInvice = fnpGetText_OR("InvoiceBillToPFList");
			 * Assert.assertEquals(insertedBillToInvice, (String)
			 * hashXlData.get("FinancialTab_BillToInvoice"),
			 * "Bill To Invice value has not been inserted properly ");
			 * 
			 */

			// fnpPFListByLiNo("InvoiceBillToPFList", "InvoiceBillToPFListOptions", 1);

			/*
			 * if ((currentSuiteName.equalsIgnoreCase(Non_Food_Compounds_suite_Name)) ){
			 * fnpPFListContains_ifMatchingMultipleThenSelectFirstOne("InvoiceBillToPFList",
			 * (String) hashXlData.get("Invoice_Bill_To_contains")) ;
			 * }else{
			 * //fnpPFListByLiNo("InvoiceBillToPFList", "InvoiceBillToPFListOptions", 1);
			 * 
			 * //commenting below on 09-09-2019 as some validation error related to currency
			 * is started coming while adding facility
			 * //fnpPFListByLiNo("InvoiceBillToPFList", "InvoiceBillToPFListOptions", 2);
			 * fnpPFListContains_ifMatchingMultipleThenSelectFirstOne("InvoiceBillToPFList",
			 * "NSFUSA") ;
			 * 
			 * }
			 * 
			 */
			switch (currentSuiteName) {

				case Non_Food_Compounds_suite_Name:
					fnpPFListContains_ifMatchingMultipleThenSelectFirstOne("InvoiceBillToPFList",
							(String) hashXlData.get("Invoice_Bill_To_contains"));
					break;

				case Wales_Work_Order_suite_Name:
					// fnpPFListByLiNo("InvoiceBillToPFList", "InvoiceBillToPFListOptions", 1);
					fnpPFListContains_ifMatchingMultipleThenSelectFirstOne("InvoiceBillToPFList", "NSFWRC");// IPM-15415
					break;

				default:
					fnpPFListContains_ifMatchingMultipleThenSelectFirstOne("InvoiceBillToPFList", "NSFUSA");

			}

			fnpLoading_wait();
			// fnpIpulseDuringLoading();
			fnpLoading_wait_CheckLoadingIconOnlyIniPulse();

			// for custom here , added for new sprint of 2017 i.e. 2.1
			// if ( (classNameText.equalsIgnoreCase("Modbrack_Not_Certified")) |
			// (classNameText.equalsIgnoreCase("WO_Custom")) ) {
			if ((classNameText.equalsIgnoreCase("Modbrack_Not_Certified"))
					|| (classNameText.contains("Custom"))
					|| (classNameText.contains("ModBrack_Certified"))
					// || (classNameText.contains("Wales_Approved"))
					|| (currentSuiteName.contains(Wales_Work_Order_suite_Name))) {
				String ListingFRSUpdateRadioBtnValue = (String) hashXlData.get("Listing_FRS_Update");

				String ListingFRSUpdateRadioBtnXpath = OR.getProperty("ListingFRSUpdateValuePart1") + "'"
						+ ListingFRSUpdateRadioBtnValue + "']/preceding-sibling::input";
				driver.findElement(By.xpath(ListingFRSUpdateRadioBtnXpath)).click();
				// Thread.sleep(500);

			}

			if ((currentSuiteName.equalsIgnoreCase(Non_Food_Compounds_suite_Name))
					|| (classNameText.contains(ModBrack_Reg4_Certified_ClassName))) {
				fnpGetORObjectX("NFC_CAR_Resolution_RadioBtn_No").click();
			}

			if (!(currentSuiteName.equalsIgnoreCase(Non_Food_Compounds_suite_Name))) {
				fnpPFList("RevenueProgramList", "RevenueProgramListOptions", (String) hashXlData.get("RevenueProgram"));
			}

			if ((classNameText.contains(Resolution_WRAS_Approved_ClassName))) {
				fnpClick_OR("Add_Edit_WOs_TasksBtn");
				fnpType("OR", "OracleProjectTxtBx", "9999999");
				fnpClick_OR("SaveAndCloseBtn_InDailog_Link_Work_Order_Monitor_Testing_Tasks_Oracle_Project");
			}

			fnpClick_OR("CreateWOBtn_id");

			fnpCheckError(" while creating new WO");

			fnpWaitForVisibleHavingMultipleElements("TopMessage3_classname");

			fnpMymsg("Top Message after create ----" + fnpGetText_OR("TopMessage3_classname"));

			String SuccessfulMsg = fnpGetText_OR("TopMessage3_classname");

			Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"),
					"Top message does not contain 'success' word, so might be work order has not been created successfully");

			fnpMymsg("Work Order created successfully as success message is getting flashed.");
			String workOrderNo_text = fnpGetText_OR("NewlyCreatedWorkOrderNo");

			// workOrderNo = ((workOrderNo_text.split(" "))[0]).trim();

			setWONo(((workOrderNo_text.split(" "))[0]).trim());

			fnpMymsg("Newly created WO no. is:" + workOrderNo);

			fnpWaitTillClickable("NewlyCrWOTopBannerInfo");

			fnpGetORObjectX("NewlyCrWOTopBannerInfo").click();
			fnpWaitForVisible("TopBannerWOStatus");
			String NewlyWOStatus = fnpGetText_OR("TopBannerWOStatus");
			Assert.assertEquals(NewlyWOStatus, "DRAFT",
					"Newly created WO status is not 'DRAFT'.The WO should get created in DRAFT status. ");
			fnpMymsg("Newly created WO status is 'DRAFT'");

			isTestPass = true;
			fnpMymsg(" **************************************************************");

		} catch (Throwable t) {
			System.out.println("Error is --" + t.getMessage());
			throw t;
		}

	}

	public static void fnpVerifyAI_and_ClientAppReviewTask_afterDraftToInreview() throws Throwable {

		fnpVerifyAIs();

		fnpWaitTillClickable("EditTaskTabLink");
		fnpGetORObjectX("EditTaskTabLink").click();
		fnpLoading_wait();

		fnpWaitForVisible("Task_ShowAllLink");

		String expectedClientAppReviewStatus = "INPERFORM";
		int taskStatusColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_StatusColName);
		int taskTypeColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskTypeColName);
		int ClientAppReviewRowNo = fnpFindRow("TasksTable_EditWO", taskType_ClientAPPReview, taskTypeColIndex);
		String actualClientAppReviewStatus = fnpFetchFromTable("TasksTable_EditWO", ClientAppReviewRowNo,
				taskStatusColIndex);

		Assert.assertTrue(actualClientAppReviewStatus.equalsIgnoreCase(expectedClientAppReviewStatus),
				"Client App Review Status is not [" + expectedClientAppReviewStatus
						+ "] .");

		fnpMymsg(" Client App Review 's Task Status in Task tab now becomes in INPERFORM status ");

		fnpMymsg("Just before counting the rows in task table");
		int totalRowTaskTable = fnpCountRowsInTable("TasksTable_EditWO");
		taskStatusColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_StatusColName);

		fnpMymsg("Task Table rows are :" + totalRowTaskTable);
		for (int i = 2; i < totalRowTaskTable; i++) {
			String taskStatus = fnpFetchFromTable("TasksTable_EditWO", i, taskStatusColIndex);
			fnpMymsg("@  ----task status for row --" + i + "  is---" + taskStatus);
			Assert.assertTrue(taskStatus.equalsIgnoreCase("CREATED"),
					"All other tasks statuses except 'Client App Review' are not having 'CREATED' status.");

		}
		fnpMymsg("All other tasks statuses except 'Client App Review' have 'CREATED' status.");

		fnpMymsg(
				"  VerifyDRAFT_INREVIEW is Pass as status changed to 'INREVIEW' and Action Items has been generated with Pending status and Client App Review's tasks status becomes 'INPERFORM' now. ");
		isTestPass = true;
		fnpMymsg(" **************************************************************");

		fnpCheckError(" while  VerifyDRAFT_INREVIEW");
	}

	public static void fnpVerifyAIs() throws Throwable {

		int itemDescColIndex = fnpFindColumnIndex("ActionItemTable_HeaderRow", "Item Desc.");

		int StatusColIndex = fnpFindColumnIndex("ActionItemTable_HeaderRow", "Status");

		/*
		 * String ItemDesc_FirstValue = fnpFetchFromTable("ActionItemTable_EditWO", 1,
		 * itemDescColIndex);
		 * String ItemDesc_SecondValue = fnpFetchFromTable("ActionItemTable_EditWO", 2,
		 * itemDescColIndex);
		 * String ItemDesc_ThirdValue = fnpFetchFromTable("ActionItemTable_EditWO", 3,
		 * itemDescColIndex);
		 * 
		 */

		String ItemDesc_Value;
		String status_Value;

		String expectedAIsString = (String) hashXlData.get("AItem_ItemDesc_INREVIEW");
		String[] expectedItemDescArray = expectedAIsString.split(",");

		int totalRowGenerated = fnpCountRowsInTable("ActionItemTable_EditWO");
		Assert.assertTrue(totalRowGenerated == expectedItemDescArray.length,
				"Total Action Items generated must be '" + expectedItemDescArray.length + "' but here they are only '"
						+ totalRowGenerated + "'  . Expected AIs are --" + expectedAIsString);

		boolean found = false;
		for (int i = 0; i < expectedItemDescArray.length; i++) {

			for (int j = 1; j <= totalRowGenerated; j++) {
				ItemDesc_Value = fnpFetchFromTable("ActionItemTable_EditWO", (j), itemDescColIndex);
				if (expectedItemDescArray[i].equalsIgnoreCase(ItemDesc_Value)) {
					fnpMymsg(" Action Item '" + expectedItemDescArray[i] + "' is presenst at row no '1' .");

					status_Value = fnpFetchFromTable("ActionItemTable_EditWO", j, StatusColIndex);
					String expectedStatus = "PENDING";
					Assert.assertTrue(status_Value.equalsIgnoreCase(expectedStatus),
							" Status of this AI '" + expectedItemDescArray[i] + "'  is not [" + expectedStatus + "] .");
					found = true;
				}
			}

			if (!(found)) {
				msg = "This action item '" + expectedItemDescArray[i]
						+ "' is not present in any row of Action itmes table .";
				fnpMymsg(msg);
				throw new Exception(msg);
			}

		}

		fnpMymsg("Action Items has been generated with Pending status. ");
	}

	public static void fnpVerifyAllTasksInCreatedStatus_TasksTab() throws Throwable {

		fnpWaitTillClickable("EditTaskTabLink");
		fnpGetORObjectX("EditTaskTabLink").click();
		fnpLoading_wait();

		fnpWaitForVisible("Task_ShowAllLink");

		int taskStatusColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_StatusColName);
		int taskTypeColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskTypeColName);

		fnpMymsg("Just before counting the rows in task table");
		int totalRowTaskTable = fnpCountRowsInTable("TasksTable_EditWO");
		taskStatusColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_StatusColName);

		fnpMymsg("Task Table rows are :" + totalRowTaskTable);
		for (int i = 1; i < totalRowTaskTable; i++) {
			String taskStatus = fnpFetchFromTable("TasksTable_EditWO", i, taskStatusColIndex);
			fnpMymsg("@  ----task status for row --" + i + "  is---" + taskStatus);
			Assert.assertTrue(taskStatus.equalsIgnoreCase("CREATED"),
					"All  tasks do not have  'CREATED' status after wo moving from draft to InReview.");

		}
		fnpMymsg("All  tasks  have  'CREATED' status after wo moving from draft to InReview.");
	}

	public static void fnpVerifyClientAppReviewTaskInCompletedAndOtherInCreatedStatus_TasksTab() throws Throwable {

		fnpWaitTillClickable("EditTaskTabLink");
		fnpGetORObjectX("EditTaskTabLink").click();
		fnpLoading_wait();

		fnpWaitForVisible("Task_ShowAllLink");

		int taskStatusColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_StatusColName);
		int taskTypeColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskTypeColName);

		String expectedClientAppReviewStatus = "COMPLETED";

		int ClientAppReviewRowNo = fnpFindRow("TasksTable_EditWO", taskType_ClientAPPReview, taskTypeColIndex);
		String actualClientAppReviewStatus = fnpFetchFromTable("TasksTable_EditWO", ClientAppReviewRowNo,
				taskStatusColIndex);

		Assert.assertTrue(actualClientAppReviewStatus.equalsIgnoreCase(expectedClientAppReviewStatus),
				"Client App Review Status is not [" + expectedClientAppReviewStatus + "] .");

		fnpMymsg(" Client App Review 's Task Status in Task tab now becomes in COMPLETED status ");

		fnpMymsg("Just before counting the rows in task table");
		int totalRowTaskTable = fnpCountRowsInTable("TasksTable_EditWO");
		taskStatusColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_StatusColName);

		fnpMymsg("Task Table rows are :" + totalRowTaskTable);
		for (int i = 2; i < totalRowTaskTable; i++) {

			String taskStatus = fnpFetchFromTable("TasksTable_EditWO", i, taskStatusColIndex);
			fnpMymsg("@  ----task status for row --" + i + "  is---" + taskStatus);
			Assert.assertTrue(taskStatus.equalsIgnoreCase("CREATED"),
					"All  tasks do not have  'CREATED' status after wo moving from draft to InReview.");

		}
		fnpMymsg(
				"All other tasks status except 'Client App Review' have 'CREATED' status after wo moving from draft to InReview.");
	}

	public static void fnpClickUsingJavascript(String xpathOfElement) {
		WebElement wb = driver.findElement(By.xpath(xpathOfElement));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", wb);
	}

	public void justtest() throws InterruptedException {
		Thread.sleep(1);
		try {

			String invoiceRuleValue = fnpGetText_OR("WO_FinancialsTab_InvoiceRule_label_value_xpath");
			System.out.println(invoiceRuleValue);

		} catch (Throwable t) {
			Thread.sleep(1);
		}
	}

	public static void fnpMultipleSelectionFilter_dropdown_with_Ok_button(String fieldtriangleExpandIcon,
			String Ok_link, String value, String AllLabels_xpath) throws Throwable {
		fnpClick_OR(fieldtriangleExpandIcon);
		Thread.sleep(500);

		/*
		 * fnpClick_OR("AuditReportForLanguageFilterChkBx");
		 * Thread.sleep(2000);
		 * fnpClick_OR("AuditReportForLanguageFilterChkBx");
		 */

		// fnpClick_OR("AddIndustryCod_Ok_link");

		// String
		// OK_link=OR.getProperty("MultipleSelectionFilter_Ok_link_part1")+panelid+OR.getProperty("MultipleSelectionFilter_Ok_link_part2");
		// fnpClick_NOR(OK_link);

		// fnpClick_OR("AuditReportForLanguage");

		/*
		 * String AuditReport_Language = (String)
		 * hashXlData.get("AuditReport_Language").trim();
		 * String AuditReport_LanguageArray[] = AuditReport_Language.split(":");
		 * int AuditReport_LanguageArraySize = AuditReport_LanguageArray.length;
		 * 
		 */
		String valueCompleteValue = value.trim();
		String valueArray[] = valueCompleteValue.split(":");
		int valueArraySize = valueArray.length;

		String allLabelXpath = OR.getProperty(AllLabels_xpath);
		List<WebElement> allLabelElements = driver.findElements(By.xpath(allLabelXpath));
		int allLabelElementsSize = allLabelElements.size();

		WebElement hoverElement;
		Actions action;
		String currentValue;
		int selectionCounter = 0;
		for (int i1 = 0; i1 < allLabelElementsSize; i1++) {

			hoverElement = allLabelElements.get(i1);
			action = new Actions(driver);
			action.moveToElement(hoverElement).perform();
			// Thread.sleep(500);
			action.moveToElement(hoverElement).sendKeys(Keys.ARROW_DOWN).build().perform();
			// Thread.sleep(500);

			currentValue = hoverElement.getText();
			if (value.contains(currentValue)) {
				fnpMymsg("@  --now going to select/click on ---" + currentValue);
				Thread.sleep(1000);
				hoverElement.click();
				Thread.sleep(1000);
				selectionCounter++;
				fnpMymsg("@  -- selected/clicked on ---" + currentValue);
				fnpMymsg("@  -- selectionCounter value is just after clicked '" + currentValue + "' is -- -"
						+ selectionCounter);
			}

			if (selectionCounter == (valueArraySize)) {
				fnpMymsg("@  -- selectionCounter value is--" + selectionCounter);
				fnpMymsg("@  -- valueArraySize value is--" + valueArraySize);
				fnpMymsg("both are equal");
				fnpClick_OR(Ok_link);
				break;
			}

		}

	}

	public static void fnpVerifyActionItemGeneratedOrNot(String actionItemName) throws Throwable {

		fnpMymsg(" Going to verify '" + actionItemName + "' action item should be generated in Pending status. ");

		int ActionItemStatusColIndex = fnpFindColumnIndex("ActionItemTable_HeaderRow", actionItemTableStatusColName);
		int AItem_ItemDesc_ColIndex = fnpFindColumnIndex("ActionItemTable_HeaderRow", actionItemTableItemDescColName);
		int AI_RowNo;
		try {
			AI_RowNo = fnpFindRow("ActionItemTable_EditWO", actionItemName, AItem_ItemDesc_ColIndex);
			// String partialAIName=actionItemName.split(" " )[1];
			// AI_RowNo = fnpFindRow_contains("ActionItemTable_EditWO", partialAIName,
			// AItem_ItemDesc_ColIndex);
			if (AI_RowNo > 0) {
				fnpMymsg("This action item '" + actionItemName + "' has  been generated. ");
			} else {
				msg = " This action item '" + actionItemName
						+ "' has not been present/generated or may be some error is present in code. ";
				fnpMymsg(msg);
				throw new Exception(msg);
			}

		} catch (Throwable t) {
			msg = "Either this action item '" + actionItemName
					+ "' has not been present/generated or may be some error is present in code. Error is --"
					+ t.getMessage();
			fnpMymsg(msg);
			throw new Exception(msg);
		}

		String aiStatus_ActionItemTable = fnpFetchFromTable("ActionItemTable_EditWO", AI_RowNo,
				ActionItemStatusColIndex);

		Assert.assertTrue(aiStatus_ActionItemTable.toLowerCase().contains("pending"),
				actionItemName
						+ "' action item should be generated in Pending status. But it is not as It is still showing '"
						+ aiStatus_ActionItemTable + "'.");

		fnpMymsg(actionItemDesc_ProdAssessReview + "' action item has been generated in Pending status successfully. ");

	}

	public static void fnpVerifyAI_isAssignedToThisPerson(String aiName, String assignee, String assigneCode,
			String profileName) throws Throwable {

		int AItem_ItemDesc_ColIndex = fnpFindColumnIndex("ActionItemTable_HeaderRow", actionItemTableItemDescColName);
		int aiRowNo = fnpFindRow_ActionItem("ActionItemTable_EditWO", aiName, AItem_ItemDesc_ColIndex);
		int AItem_ItemAssignedTo_ColIndex = fnpFindColumnIndex("ActionItemTable_HeaderRow",
				actionItemTableAssignedToColName);
		String AIAssigner = fnpFetchFromTable("ActionItemTable_EditWO", aiRowNo, AItem_ItemAssignedTo_ColIndex);

		if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking"))) {
			Assert.assertTrue(AIAssigner.contains(assigneCode),
					"This '" + aiName + "' AI is not assigned to " + profileName + " i.e. '" + assigneCode + "'");
		} else {
			// Assert.assertEquals(AIAssigner.trim(), assignee, "This '"+aiName+"' AI is not
			// assigned to "+profileName+" i.e. '" +assignee + "'");
			Assert.assertTrue(AIAssigner.contains(assignee),
					"This '" + aiName + "' AI  is not assigned to  " + profileName + " i.e. '" + assignee + "'");
		}

		fnpMymsg("This '" + aiName + "' AI  is  assigned to  " + profileName + " i.e. '" + assignee
				+ "'  successfully.");

	}

	public static void fnpVerifyTask_isAssignedToThisPerson_atSnapshotTab(String taskName, String assignee,
			String assigneCode, String profileName) throws Throwable {
		fnpCommonClickSnapShotTab();

		int assignedToColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow",
				SnapShot_taskTable_AssignedToColName);
		int TaskDescColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow",
				SnapShot_taskTable_TaskDescColName);
		int taskRowNo = fnpFindRow("Snapshot_TasksSummaryTable", taskName, TaskDescColIndex);
		String actualTaskAssigner = fnpFetchFromTable("Snapshot_TasksSummaryTable", taskRowNo, assignedToColIndex);

		String expectedAssigneeName = assignee;
		String expectedAssigneeCode = assigneCode;

		if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking"))) {
			Assert.assertTrue(actualTaskAssigner.contains(expectedAssigneeCode),
					taskName + " is not assigned to " + profileName + " because actual  value is ---'"
							+ actualTaskAssigner + "' and expected is --'" + expectedAssigneeCode + "'.");

		} else {
			// Assert.assertEquals(actualTaskAssigner.trim(), expectedAssigneeName,
			// taskName+" is not assigned to "+profileName+" because actual value is
			// ---'"+actualTaskAssigner+"' and expected is --'"+expectedAssigneeName+"'.");
			Assert.assertTrue(actualTaskAssigner.trim().contains(expectedAssigneeName),
					taskName + " is not assigned to  " + profileName + "  because actual  value is ---'"
							+ actualTaskAssigner + "' and expected is --'" + expectedAssigneeName + "'.");
		}

		fnpMymsg(taskName + "  task is assigned to '" + profileName + "'  successfully.");

	}

	public static void fnpActionItemReassignedTo_normalAI(String assigneeFullName, String assigneeCode, String profile)
			throws Throwable {
		fnpWaitForVisible("ActionItemScreen_AIAssignedTo_lkp_btn");
		String alreadyAssingee = fnpGetORObjectX("ActionItemScreen_AssignedToLabelValue").getText().trim();
		String reassignee = null;

		if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking"))) {
			// reassignee=(String) hashXlData.get("TechReviewer_Code").trim();
			reassignee = assigneeCode;
		} else {
			// reassignee=(String) hashXlData.get("TechReviewer_fullName").trim();
			reassignee = assigneeFullName;
		}

		if (!(alreadyAssingee.contains(reassignee))) {
			String defaultValue = fnpGetORObjectX("ActionItemScreen_ReassignAITxtBx").getAttribute("value").trim();
			// if (defaultValue.equalsIgnoreCase(reassignee)) {
			if (defaultValue.contains(reassignee)) {
				fnpMymsg("@  - default value is same as expected, so returning back.");
			} else {
				fnpClick_OR("ActionItemScreen_AIAssignedTo_lkp_btn");

				fnpMymsg("Just after  click ReassignAILkpBtn");
				fnpMymsg("Just before going to insert value of  user i.e. '" + assigneeFullName + "'.");
				// fnpSearchNSelectFirstRadioBtn(2, reassignee, 1);
				if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking"))) {
					fnpSearchNSelectFirstRadioBtn(1, reassignee, 1);
					// fnpMymsg("Debug: 6");
				} else {
					fnpSearchNSelectFirstRadioBtn(2, reassignee, 1);
					// fnpMymsg("Debug: 7");
				}
				fnpMymsg("Just after  inserting value of  user i.e. '" + assigneeFullName + "'.");

				String EnteredAccountManager = fnpWaitTillTextBoxDontHaveValue("ActionItemScreen_ReassignAITxtBx",
						"value");
				Assert.assertTrue(EnteredAccountManager.contains(reassignee),
						"Reassignee is not selected properly from lookup");
				fnpMymsg(" Reassignee is properly selected from  lookup");
			}

			fnpClick_OR("SaveBtnOnTransferReviewAIScreen");

		}

	}

	public static void fnpUploadMultipleDocs(String fileNamesString, String docTypeString, String accessString,
			String addButtonInOR, String docSaveNCloseBtnInOR, String uploadOrBrowseButton,
			String addDocTypePFList_part1, String addDocTypePFList_part2,
			String addDoc_AccessPFList_part1, String addDoc_AccessPFList_part2) throws Throwable {

		String fileNames = fileNamesString;// (String) hashXlData.get("ProAddDocToWOFileName");
		String[] fileCount = fileNames.split(",");
		int fileCountSize = fileCount.length;

		if (!(fileCountSize > 0)) {
			throw new Exception("Upload file names should be given in excel.");
		}

		String fileTypes = docTypeString;// (String) hashXlData.get("WOProductDocType");
		String[] fileTypeCount = fileTypes.split(",");
		int fileTypesCountSize = fileCount.length;

		if (!(fileTypesCountSize == fileCountSize)) {
			throw new Exception("Upload file type should be equal to the no. of files in excel.");
		}

		String fileAccessNames = accessString;// (String) hashXlData.get("Access");
		String[] fileAccessCount = fileAccessNames.split(",");
		int fileAccessCountSize = fileAccessCount.length;

		if (!(fileAccessCountSize == fileCountSize)) {
			throw new Exception("Upload file access should be equal to the no. of files  in excel.");
		}

		String fileName = "";

		String fname;

		if ((fnpGetCurrRunningBrowserName().equalsIgnoreCase("firefox"))) {
			// need to reimplement again
			throw new Exception("Need to reimplement firefox upload doc functionality.");
		} else {

			for (int f = 0; f < fileCountSize; f++) {
				// Thread.sleep(2000);
				fname = System.getProperty("user.dir") + "\\docs\\" + fileCount[f];
				if (fnpGetCurrRunningBrowserName().equalsIgnoreCase("chrome")) {
					if (f != fileCountSize - 1) {
						fileName = fileName + fname + "\n";
					} else {
						fileName = fileName + fname;
					}

				} else {
					if (fnpGetCurrRunningBrowserName().equalsIgnoreCase("IE")) {
						fileName = fileName + "\"" + fname + "\"";
					}
				}
			}

			fnpClick_OR(addButtonInOR);
			fnpWaitForVisible(docSaveNCloseBtnInOR);
			fnpMymsg("Going to upload files.");
			// driver.findElement(By.xpath(OR.getProperty("ProAddDocumentSelectFilesBtn"))).sendKeys(fileName);

			String fname1;
			for (int f = 0; f < fileCountSize; f++) {
				// Thread.sleep(2000);
				fname1 = System.getProperty("user.dir") + "\\docs\\" + fileCount[f];
				driver.findElement(By.xpath(OR.getProperty(uploadOrBrowseButton))).sendKeys(fname1);
				Thread.sleep(1000);
				fnpLoading_wait();
				/*
				 * Thread.sleep(3000);
				 * fnpLoading_wait();
				 */
				int whileloopcounter = 0;
				while (fnpCheckElementPresenceImmediately("BrowseCancelUploadBtn")) {
					whileloopcounter++;
					if ((whileloopcounter > (Integer.parseInt(CONFIG.getProperty("genMax_waitTime")) * 2))) {
						fnpMymsg("@  ---still BrowseCancelUploadBtn is present after too much time ---"
								+ whileloopcounter + " seconds");
						break;
					} else {
						fnpMymsg("@  --- BrowseCancelUploadBtn is present after  time ---" + whileloopcounter
								+ " seconds");
					}
					Thread.sleep(1000);
				}

			}

			/*
			 * //Pradeep--You can comment below lines later for wait
			 *//***********************************************************/
			/*
			 * fnpMymsg("Hit the file Names.");
			 * Thread.sleep(1000);
			 * fnpLoading_wait();
			 * fnpLoading_waitInsideDialogBox();
			 * Thread.sleep(1000);
			 *//***********************************************************//*	
																			
																			*/
			int whileloopcounter = 0;
			while (fnpCheckElementPresenceImmediately("BrowseCancelUploadBtn")) {
				whileloopcounter++;
				if ((whileloopcounter > Integer.parseInt(CONFIG.getProperty("genMax_waitTime")))) {
					fnpMymsg("@  ---still BrowseCancelUploadBtn is present after too much time ---" + whileloopcounter
							+ " seconds");
					break;
				} else {
					fnpMymsg("@  --- BrowseCancelUploadBtn is present after  time ---" + whileloopcounter + " seconds");
				}
				Thread.sleep(1000);
			}

			// Thread.sleep(2000);

			String typelist;
			String accessList;
			int uploadedFilesCount = 0;
			for (int f = 0; f < fileCountSize; f++) {
				typelist = OR.getProperty(addDocTypePFList_part1) + f + OR.getProperty(addDocTypePFList_part2);
				accessList = OR.getProperty(addDoc_AccessPFList_part1) + f + OR.getProperty(addDoc_AccessPFList_part2);

				if (fnpCheckElementDisplayedImmediately_NOR(typelist)) {
					fnpPFListModify_NOR(typelist, fileTypeCount[f]);
					Thread.sleep(2000);
					fnpPFListModify_NOR(accessList, fileAccessCount[f]);
					Thread.sleep(2000);
					uploadedFilesCount++;
				} else {
					fnpMymsg(
							"@@@@   ---will fail at product multiple doc functionality otherwise if not going to wait for all type list in script --"
									+ classNameText);
				}

			}

			if (!(uploadedFilesCount > 0)) {

				if (fileCountSize > 1) {
					throw new Exception(
							"Not a single document is uploaded successfully out of '" + fileCountSize + "' documents.");
				} else {
					throw new Exception("Document is not uploaded successfully.");
				}

			}

			fnpClickInDialog_OR(docSaveNCloseBtnInOR);

			fnpCheckError("Error is thrown by application while adding data in Document Tab");

		}

		fnpWaitTillThisElementIsNoLongerDisplayedTillMaxWait_OR(docSaveNCloseBtnInOR,
				"AddDocSaveNCloseBtn button is still visible after clicking it and waited 120 seconds.", 120);

	}

	public static void fnpVerifyTask_Result_at_SnapshotTab(String taskName, String expectedValueIn_Result_column)
			throws Throwable {
		int taskDescColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow",
				SnapShot_taskTable_TaskDescColName);
		// int statusColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow",
		// SnapShot_taskTable_StatusColName);
		int resultColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", SnapShot_taskTable_ResultColName);

		int taskRowNo = fnpFindRow("Snapshot_TasksSummaryTable", taskName, taskDescColIndex);

		String taskResultValue = fnpFetchFromTable("Snapshot_TasksSummaryTable", taskRowNo, resultColIndex);
		// String expectedrevExecQuoteTaskStatus = "Completed";
		Assert.assertTrue(taskResultValue.equalsIgnoreCase(expectedValueIn_Result_column),
				" This task '" + taskName + "' does not have this value '" + expectedValueIn_Result_column
						+ "' in Result column"
						+ "as it is expected but actual value is this -'" + taskResultValue + "'.");
		fnpMymsg("This task '" + taskName + "' does  have this value '" + expectedValueIn_Result_column
				+ "' in Result column as it is expected.");
	}

	public static void fnpVerifyTask_ColValue_at_SnapshotTab(String colNameForWhichValueToBeExtract,
			String taskDescColumnName, String taskName, String expectedColValue) throws Throwable {
		// Assumption we are here at Snapshot tab already
		int colIndex_forWhichValueToBeExtract = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow",
				colNameForWhichValueToBeExtract);
		int taskDesc_ColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", taskDescColumnName);
		int taskRowNo = fnpFindRow("Snapshot_TasksSummaryTable", taskName, taskDesc_ColIndex);
		String actualTaskColValue = fnpFetchFromTable("Snapshot_TasksSummaryTable", taskRowNo,
				colIndex_forWhichValueToBeExtract);
		// String expectedTaskColValue="WIN";

		// Assert.assertEquals(actualTaskColValue, expectedColValue, "Here expected Task
		// '"+taskName+"' status should have '"+expectedColValue+"' but actual is this
		// --'"+actualTaskColValue+"' , hence failed.");
		Assert.assertTrue(actualTaskColValue.equalsIgnoreCase(expectedColValue),
				"Here expected Task '" + taskName + "' status should have '" + expectedColValue
						+ "' but actual is this --'" + actualTaskColValue + "' , hence failed.");

		fnpMymsg(" Task '" + taskName + "' has  this value '" + expectedColValue + "' for this column name '"
				+ colNameForWhichValueToBeExtract + "'.");
	}

	public static void fnpVerifyTask_ColValue_at_SnapshotTab_ContainsMatch(String colNameForWhichValueToBeExtract,
			String taskDescColumnName, String taskName, String expectedColValue) throws Throwable {
		// Assumption we are here at Snapshot tab already
		int colIndex_forWhichValueToBeExtract = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow",
				colNameForWhichValueToBeExtract);
		int taskDesc_ColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", taskDescColumnName);
		int taskRowNo = fnpFindRow("Snapshot_TasksSummaryTable", taskName, taskDesc_ColIndex);
		String actualTaskColValue = fnpFetchFromTable("Snapshot_TasksSummaryTable", taskRowNo,
				colIndex_forWhichValueToBeExtract);
		// String expectedTaskColValue="WIN";

		// Assert.assertEquals(actualTaskColValue, expectedColValue, "Here expected Task
		// '"+taskName+"' status should have '"+expectedColValue+"' but actual is this
		// --'"+actualTaskColValue+"' , hence failed.");
		Assert.assertTrue(actualTaskColValue.toLowerCase().trim().contains(expectedColValue.toLowerCase().trim()),
				"Here expected Task '" + taskName + "' status should have '" + expectedColValue
						+ "' but actual is this --'" + actualTaskColValue + "' , hence failed.");

		fnpMymsg(" Task '" + taskName + "' has  this value '" + expectedColValue + "' for this column name '"
				+ colNameForWhichValueToBeExtract + "'.");
	}

	public static void fnpVerifyWoStatus(String expectedWOStatus) throws Throwable {
		fnpWaitTillClickable("NewlyCrWOTopBannerInfo");

		if (!(fnpCheckElementDisplayedImmediately("TopBannerWOStatus"))) {
			fnpGetORObjectX("NewlyCrWOTopBannerInfo").click();
		}

		fnpWaitForVisible("TopBannerWOStatus");
		String changedWOStatus = fnpGetORObjectX("TopBannerWOStatus").getText();
		Assert.assertTrue(changedWOStatus.equalsIgnoreCase(expectedWOStatus),
				" WO status is not changed to '" + expectedWOStatus + "', as current status is --" + changedWOStatus);
		fnpMymsg("Now  WO status has been changed to '" + expectedWOStatus + "'.");
	}

	public static void fnpSSOLoginWorkAround() {

		String userName = CONFIG.getProperty("adminUsername");
		String password = CONFIG.getProperty("adminPassword");

		String url = driver.getCurrentUrl();
		String[] urlArray = url.split("//");
		// String newURL=urlArray+"//testscriptuser:welcome123@"+urlArray[1];
		String newURL = urlArray[0] + "//" + userName + ":" + password + "@" + urlArray[1];
		// fnpMymsg("New url is --"+newURL);
		System.out.println("New url is --" + newURL);
		driver.get(newURL);

	}

	public static void fnpWaitTillWOStatusGetCompleteAutomatically() throws Throwable {

		Date startTimeOfWait = new Date();
		Date tempEndTime;
		long durationInMilliseconds;
		long diffMinutes;

		int maxWait = 100;// approx 30-35 minutes
		int waitCount = 0;
		String changedWOStatus;
		while (true) {
			waitCount++;
			fnpWaitForVisible("NewlyCrWOTopBannerInfo");
			fnpGetORObjectX("NewlyCrWOTopBannerInfo").click();
			// fnpLoading_wait();

			fnpWaitForVisible("TopBannerWOStatus");
			changedWOStatus = fnpGetORObjectX("TopBannerWOStatus").getText();

			if (changedWOStatus.equalsIgnoreCase("complete")) {
				fnpMymsg("WO status has become to 'COMPLETE' state. ");
				break;
			} else {
				Thread.sleep(1000 * 30 * 1);
				driver.navigate().refresh();
				Thread.sleep(8000);

				tempEndTime = new Date();
				durationInMilliseconds = tempEndTime.getTime() - startTimeOfWait.getTime();
				diffMinutes = durationInMilliseconds / (60 * 1000) % 60;

				// fnpMymsg("Waiting for Complete wo status ----" + ((waitCount * 40) / 60) + "
				// minutes");
				fnpMymsg("Waiting for Complete wo status ----" + diffMinutes + " minutes");
				fnpMymsg("It is still   ----" + changedWOStatus);
			}

			if (waitCount > maxWait) {
				// msg="Script waited for approx. "+((waitCount * 40) / 60)+" min. to become
				// 'COMPLETE' state of WO status but it is still showing '" + changedWOStatus +
				// "' .";
				msg = "Script waited for approx. " + diffMinutes
						+ " min. to become 'COMPLETE' state of WO status but it is still showing '" + changedWOStatus
						+ "' .";
				fnpMymsg(msg);
				throw new Exception(msg);

			}

			fnpCheckErrMsg(
					"Error is thrown by application while waiting for wo status get change to 'Complete' autoamatically and refreshing again n again.");

		}
		fnpMymsg("  Wo status is changed to 'Complete' successfully.");
	}

	public static void fnpVerifyAICreated(String AIName, String expectedAIStatus, String expectedAssigner)
			throws Throwable {
		fnpMymsg(
				" Going to verify " + AIName + " action item should be generated in " + expectedAIStatus + " status. ");
		// fnpWaitForVisible("ActionItemTable_EditWO");
		int ActionItemNoColIndex = fnpFindColumnIndex("ActionItemTable_HeaderRow", actionItemTableStatusColName);
		int AItem_ItemDesc_ColIndex = fnpFindColumnIndex("ActionItemTable_HeaderRow", actionItemTableItemDescColName);
		// int AI_DocumentFinalRowNo = fnpFindRow("ActionItemTable_EditWO",
		// actionItemDesc_DocumentFinal, AItem_ItemDesc_ColIndex);
		int AIRowNo = fnpFindRow_ActionItem("ActionItemTable_EditWO", AIName, AItem_ItemDesc_ColIndex);

		// Assert.assertTrue(AI_DocumentFinalRowNo > 0, "Action Item 'DocumentFinal' has
		// not been generated. ");
		fnpMymsg("Action Item " + AIName + " has  been generated successfully.  ");

		if (!(expectedAIStatus.equalsIgnoreCase(""))) {
			String actualAIStatusinActionItemTable = fnpFetchFromTable("ActionItemTable_EditWO", AIRowNo,
					ActionItemNoColIndex);
			Assert.assertTrue(actualAIStatusinActionItemTable.equalsIgnoreCase(expectedAIStatus),
					AIName + " action item should be generated in " + expectedAIStatus
							+ " status. But it is not as It is still showing '"
							+ actualAIStatusinActionItemTable + "'.");
			fnpMymsg(AIName + " action item is present in " + expectedAIStatus + " status . ");
		}

		if (!(expectedAssigner.equalsIgnoreCase(""))) {
			int AItem_ItemAssignedTo_ColIndex = fnpFindColumnIndex("ActionItemTable_HeaderRow",
					actionItemTableAssignedToColName);
			// int AIRowNo = fnpFindRow("ActionItemTable_EditWO", AIName,
			// AItem_ItemDesc_ColIndex);
			String actualAssigner = fnpFetchFromTable("ActionItemTable_EditWO", AIRowNo, AItem_ItemAssignedTo_ColIndex);

			// Assert.assertEquals(actualAssigner.trim(), expectedAssigner, "Document Final
			// AI is not assigned to "+expectedAssigneeProfile+" i.e. '"
			// +expectedDocumentFinalAIAssigner + "'");
			Assert.assertTrue(actualAssigner.contains(expectedAssigner),
					AIName + " is not assigned to  " + expectedAssigner + " and actual assigner value is --'"
							+ actualAssigner + "'.");
			fnpMymsg(" Document Final AI is  assigned to  " + expectedAssigner + "'  successfully.");
		}

	}

	public static void fnpPFList_EditFinopsTable(String taskName, String value) throws Throwable {

		String ListXpath = "//td/span[contains(@id,':editFinOpsTable:')][contains(@id,':editFinOpsJobType')][text()='"
				+ taskName + "']/../following-sibling::td//label[contains(@id,':isAuditCostFlg2_label')]";

		fnpMymsg("List is present for this field--" + taskName);
		String p;
		WebElement oList;

		oList = driver.findElement(By.xpath(ListXpath));
		p = oList.getAttribute("id");

		String pp = p.replace("_label", "");
		pp = pp.trim();
		String labelid = pp.trim() + "_label";

		int timer = 0;
		while (true) {
			try {

				oList = driver.findElement(By.xpath(ListXpath));

				oList.click();

				break;
			} catch (StaleElementReferenceException s) {
				Thread.sleep(1000);
				timer = timer + 1;
				if (timer > Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					break;
				}
			}
		}
		Thread.sleep(1500);

		String panelId = pp.trim() + "_panel";
		String listValue = ".//*[@id='" + panelId + "']" + "/div/ul/li[@data-label='" + value + "']";
		int iCounter = 1;
		while (true) {
			try {
				List<WebElement> objectlistValues = driver.findElement(By.xpath(".//*[@id='" + panelId + "']"))
						.findElements(By.tagName("li"));
				boolean ValueMatched = false;
				;
				for (WebElement dd_value : objectlistValues) {
					Actions act = new Actions(driver);
					// move to current element
					act.moveToElement(dd_value).build().perform();
					// move to next element
					// act.moveToElement(dd_value).sendKeys(Keys.ARROW_DOWN).build().perform();
					// //System.out.println(" ------" + dd_value.getText());
					if (dd_value.getText().equals(value)) {

						Thread.sleep(1000);

						dd_value.click();
						fnpLoading_wait();
						ValueMatched = true;
						break;
					}

				}
				if (ValueMatched == false) {
					throw new Exception("Excel value --'" + value + "'  is not matched with DropDown Value.");
				}

				break;
			} catch (Throwable t) {

				if (iCounter < 3) {
					iCounter++;
				} else {
					break;

				}
			}
		}

		String selectedlistValue = "";
		try {
			selectedlistValue = driver.findElement(By.xpath(ListXpath)).getText();
		} catch (Throwable t) {
			Thread.sleep(2000);
			selectedlistValue = driver.findElement(By.xpath(ListXpath)).getText();
		}

		Assert.assertEquals(selectedlistValue, value,
				"Fail due to Actual '" + selectedlistValue + "' and expected '" + value + "' are not matched.");

		fnpMymsg("");
		fnpMymsg("");

	}

	public static void fnsDD_Value_select_by_DDLabelName_and_Filter(String DD_Label_Name, String DD_Value)
			throws Throwable {
		try {
			String LabelXpath = "//label[contains(text(), '" + DD_Label_Name.trim() + "')]";
			String DownArrowXpath = "(" + LabelXpath + "/following::span[@class='ui-icon ui-icon-triangle-1-s'])[1]";
			String FilterXpath = "(" + LabelXpath
					+ "/following::div[@class='ui-selectcheckboxmenu-filter-container']/input)[1]";
			String ValueXpath = "(" + LabelXpath + "/following::label[contains(text(), '" + DD_Value.trim() + "')])[2]";
			String OKButtonXpath = "(" + LabelXpath + "/following::span[@class='ui-icon ui-icon-circle-close'])[1]";

			String Individual_DD_Label_Xpath = null;
			boolean DD_Label_Found = false;

			for (int DD_Label_try = 1; DD_Label_try <= Integer
					.parseInt(CONFIG.getProperty("genMax_waitTime")); DD_Label_try++) {
				try {
					if (driver.findElements(By.xpath(LabelXpath)).size() > 0) {
						for (int i = 1; i <= driver.findElements(By.xpath(LabelXpath)).size(); i++) {
							Individual_DD_Label_Xpath = "(" + LabelXpath + ")[" + i + "]";

							if (driver.findElements(By.xpath(Individual_DD_Label_Xpath)).size() > 0) {
								String Individual_DD_Label_TEXT = fnpGetORObjectX___NOR(Individual_DD_Label_Xpath)
										.getText().toLowerCase().trim();
								if (Individual_DD_Label_TEXT.contains(DD_Label_Name.toLowerCase().trim())) {
									DD_Label_Found = true;
									break;
								}
							}
						}
					}
					// fnpLoading_wait();
					if (DD_Label_Found == true) {
						break;
					}
				} catch (Throwable t) {
					// nothing to do
				}

			}

			if (DD_Label_Found == false) {
				throw new Exception("FAILED == Drop down '" + DD_Label_Name
						+ "' is not found on the screen, please refer the screen shot >> DdValueSelect_Fail");
			}

			fnpWaitForVisible_NotInOR(DownArrowXpath);
			fnpGetORObjectX___NOR(DownArrowXpath).click();
			Thread.sleep(1000);

			fnpWaitForVisible_NotInOR(FilterXpath);
			fnpGetORObjectX___NOR(FilterXpath).sendKeys(DD_Value);
			Thread.sleep(1000);

			fnpWaitForVisible_NotInOR(ValueXpath);
			fnpGetORObjectX___NOR(ValueXpath).click();
			Thread.sleep(1000);
			Thread.sleep(1000);

			/*
			 * fnsGet_Element_Enabled_NOR(OKButtonXpath);
			 * fnpGetORObjectX___NOR(OKButtonXpath).click();
			 * Thread.sleep(1000);
			 * Thread.sleep(1000);
			 */
			System.out.println("test");
			fnpMymsg("PASSED == Value <" + DD_Value + "> selection done from drop down '" + DD_Label_Name + "'.");
		} catch (NoSuchWindowException W) {
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			fnpDesireScreenshot_old("DdValueSelect_Fail");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}

	}

	// Application date format
	public static String fns_Return_RequriedDateTime(String Date_format, Integer Year, Integer Month, Integer Day,
			Integer Hour, Integer Minute) {
		Calendar cal = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat(Date_format);
		cal.add(Calendar.YEAR, Year);
		cal.add(Calendar.MONTH, Month);
		cal.add(Calendar.DAY_OF_MONTH, Day);
		cal.add(Calendar.HOUR_OF_DAY, Hour);
		cal.add(Calendar.MINUTE, Minute);
		String DemandDate = dateFormat.format(cal.getTime()).trim();
		return DemandDate;
	}

	// Function of loading image appear on the screen (Block UI).
	public static void fnsLoading_wait_In_SAM(int waitcount) throws Throwable {
		try {
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			String Loading_Image_Xpath = "";
			String Loading_Classes_From_OR = OR.getProperty("SAM_Loading_Progressing").trim();
			Integer PageLoadWait = TestSuiteBase_MonitorPlan.fnsLoading_PageLoad();
			for (int wait = PageLoadWait; wait <= ((Element_Max_Wait_Time_SAM)); wait++) {
				if (!LoadingImageFlag) {
					Thread.sleep(500);
					for (int i = 0; i < Loading_Classes_From_OR.split("&&").length; i++) {
						Loading_Image_Xpath = Loading_Classes_From_OR.split("&&")[i].trim();
						try {
							if (driver.findElements(By.xpath(Loading_Image_Xpath)).size() > 1) {
								// fnsApps_Report_Logs("Xpath 1 = "+Loading_Image_Xpath+"..... Size =
								// "+driver.findElements(By.xpath(Loading_Image_Xpath)).size());
								for (int display = 1; display <= driver.findElements(By.xpath(Loading_Image_Xpath))
										.size(); display++) {
									String Loading_Image_Xpath_Display = "(" + Loading_Image_Xpath + ")[" + display
											+ "]";
									if (driver.findElement(By.xpath(Loading_Image_Xpath_Display)).isDisplayed()) {
										Loading_Image_Xpath = Loading_Image_Xpath_Display;
										break;
									}
								}
							} else if (driver.findElements(By.xpath(Loading_Image_Xpath)).size() > 0) {
								// fnsApps_Report_Logs("Xpath 0 = "+Loading_Image_Xpath+"..... Size =
								// "+driver.findElements(By.xpath(Loading_Image_Xpath)).size());
								if (driver.findElement(By.xpath(Loading_Image_Xpath)).isDisplayed()) {
									break;
								}
							}
						} catch (Throwable t) {
							/* /nothing to do */ }
					}
				}

				/*
				 * boolean ContentPopupDisplay = false;
				 * PageSourceText = driver.getPageSource().toLowerCase();
				 * if(PageSourceText.contains("modal-content")){
				 * try{
				 * if(driver.findElement(By.xpath(OR.getProperty("SAM_Content_Model_Popup"))).
				 * isDisplayed()){
				 * ContentPopupDisplay = true;
				 * }
				 * }catch( Throwable n){
				 * System.out.println("TOP - - modal-content - error - Page source");
				 * }
				 * 
				 * if (ContentPopupDisplay) {
				 * WebElement Element = fnpGetORObject("SAM_Content_Model_Popup");
				 * Actions act = new Actions(driver);
				 * act.moveToElement(Element).build().perform();
				 * ErrorMsgText = fnpGetORObject("SAM_Content_Model_Popup").getText().trim();
				 * throw new IllegalArgumentException();
				 * }
				 * }
				 */

				try {
					if (driver.findElement(By.xpath(Loading_Image_Xpath)).isDisplayed()) {
						Thread.sleep(1000);
						LoadingImageFlag = true;
					}
				} catch (Throwable n) {
					Thread.sleep(250);
				}

				try {
					if (!(driver.findElement(By.xpath(Loading_Image_Xpath)).isDisplayed()) && wait > waitcount
							&& LoadingImageFlag == true) {
						fnpMymsg("Progressing Loading image is displayed for < " + (Actual_Loading_Time)
								+ " > seconds.");
						break;
					} else if (!(driver.findElement(By.xpath(Loading_Image_Xpath)).isDisplayed()) && wait > waitcount
							&& LoadingImageFlag == false) {
						fnpMymsg("*********  Pause/Wait is for < " + Actual_Loading_Time + " > seconds.  *****  "); // To
																													// increase
																													// performance,
																													// Pause/Wait
																													// time
																													// can
																													// set
																													// to
																													// 1
																													// sec
						break;
					} else if (wait < waitcount && LoadingImageFlag == false) {
						Thread.sleep(500);
					}
				} catch (Throwable n) {
					if (wait > waitcount) {
						if (LoadingImageFlag == true) {
							// Loading_Xpath_Array.add(Loading_Image_Xpath+"&&");
							fnpMymsg("Progressing Loading image is displayed for < " + (Actual_Loading_Time)
									+ " > seconds.");
							break;
						} else if (LoadingImageFlag == false) {
							fnpMymsg("*********  Pause/Wait is for < " + Actual_Loading_Time + " > seconds.  *****  "); // To
																														// increase
																														// performance,
																														// Pause/Wait
																														// time
																														// can
																														// set
																														// to
																														// 1
																														// sec
							break;
						}
					} else if (wait < waitcount && LoadingImageFlag == false) {
						Thread.sleep(500);
					}
				}

				if (wait == (Element_Max_Wait_Time_SAM)) {
					throw new InterruptedException("Loading Issue : After < " + (Element_Max_Wait_Time_SAM)
							+ " > Seconds wait page is not getting Load, please refer screenshot >> PageLoadFail"
							+ "*****  < " + Loading_Image_Xpath + " >");
				}
				Actual_Loading_Time++;
			}

			/*
			 * PageSourceText = driver.getPageSource().toLowerCase();
			 * if(PageSourceText.contains("modal-content")){
			 * boolean ContentPopupDisplay = false;
			 * try{
			 * if(driver.findElement(By.xpath(OR.getProperty("SAM_Content_Model_Popup"))).
			 * isDisplayed()){
			 * ContentPopupDisplay = true;
			 * }
			 * }catch( Throwable n){
			 * System.out.println("TOP - - modal-content - error - Page source");
			 * }
			 * 
			 * if (ContentPopupDisplay) {
			 * WebElement Element = fnpGetORObject("SAM_Content_Model_Popup");
			 * Actions act = new Actions(driver);
			 * act.moveToElement(Element).build().perform();
			 * ErrorMsgText = fnpGetORObject("SAM_Content_Model_Popup").getText().trim();
			 * throw new IllegalArgumentException();
			 * }
			 * }
			 */

			driver.manage().timeouts().implicitlyWait(Element_Max_Wait_Time_SAM, TimeUnit.SECONDS);
		} catch (IllegalArgumentException i) {
			driver.manage().timeouts().implicitlyWait(Element_Max_Wait_Time_SAM, TimeUnit.SECONDS);
			// isTestCasePass = false;
			TestSuiteBase_MonitorPlan.fnsTake_Screen_Shot_Without_OR("UnExpectedError");
			fnpMymsg("Getting Un-Expected Application Error <" + ErrorMsgText
					+ ">, please refer screenshot >>UnExpectedError >>" + "  Exception ("
					+ Throwables.getStackTraceAsString(i));
			throw new Exception(Throwables.getStackTraceAsString(i));
		} catch (NoSuchElementException n) {
			driver.manage().timeouts().implicitlyWait(Element_Max_Wait_Time_SAM, TimeUnit.SECONDS);
			fnpMymsg("***** Progressing Loading image displayed for < " + Actual_Loading_Time
					+ " > seconds. **************** NS");
			try {
				fnpMymsg("***** Progressing Loading image displayed for < " + Actual_Loading_Time
						+ " > seconds. **************** NS");
				fnsLoading_wait_In_SAM(waitcount);
			} catch (Throwable tt) {
				isTestCasePass = false;
				TestSuiteBase_MonitorPlan.fnsTake_Screen_Shot_Without_OR("PageLoadFail_NS");
				fnpMymsg(Throwables.getStackTraceAsString(tt));
				throw new Exception(Throwables.getStackTraceAsString(tt));
			}
		} catch (StaleElementReferenceException n) {
			driver.manage().timeouts().implicitlyWait(Element_Max_Wait_Time_SAM, TimeUnit.SECONDS);
			fnpMymsg("***** Progressing Loading image displayed for < " + Actual_Loading_Time
					+ " > seconds. **************** Stale");
		} catch (NoSuchWindowException W) {
			driver.manage().timeouts().implicitlyWait(Element_Max_Wait_Time_SAM, TimeUnit.SECONDS);
			throw new Exception(W.getMessage());
		} catch (InterruptedException ie) {
			driver.manage().timeouts().implicitlyWait(Element_Max_Wait_Time_SAM, TimeUnit.SECONDS);
			System.out.println("Interrupted-----Exception");
			TestSuiteBase_MonitorPlan.fnsTake_Screen_Shot_Without_OR("PageLoadFail");
			fnpMymsg(Throwables.getStackTraceAsString(ie));
			throw new Exception(Throwables.getStackTraceAsString(ie));
		} catch (Throwable tt) {
			driver.manage().timeouts().implicitlyWait(Element_Max_Wait_Time_SAM, TimeUnit.SECONDS);
			// isTestCasePass = false;
			TestSuiteBase_MonitorPlan.fnsTake_Screen_Shot_Without_OR("PageLoadFail");
			fnpMymsg(Throwables.getStackTraceAsString(tt));
			throw new Exception(Throwables.getStackTraceAsString(tt));
		} finally {
			driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")),
					TimeUnit.SECONDS);
		}
	}

	public static void fns_SelectDateFromCalendar_InSAM(String Cal_LabelName, String DisplayDate_Seq) throws Throwable {
		try {
			String DateCalButton_Xpath = "(//label[contains(text(), '" + Cal_LabelName
					+ "')]/following::span[@class='k-icon k-i-calendar'])[1]";
			String DateXpath = "(//td[@aria-disabled='false']/span)[" + DisplayDate_Seq + "]";
			String NOW_button_Xpath = "//button[@title='Select now' and text()='NOW']";
			String Set_Button_Xpath = "//button[@title='Set' and contains(text(), 'Set')]";

			fnpCheckElementDisplayed_NOR(DateCalButton_Xpath);
			fnpClick_NOR(DateCalButton_Xpath);
			fnsLoading_wait_In_SAM(1);
			fnsLoading_wait_In_SAM(1);

			fnpCheckElementDisplayed_NOR(DateXpath);
			fnpClick_NOR(DateXpath);
			fnsLoading_wait_In_SAM(1);
			fnsLoading_wait_In_SAM(1);

			fnpCheckElementDisplayed_NOR(NOW_button_Xpath);
			fnpClick_NOR(NOW_button_Xpath);
			fnsLoading_wait_In_SAM(1);
			fnsLoading_wait_In_SAM(1);

			fnpCheckElementDisplayed_NOR(Set_Button_Xpath);
			fnpClick_NOR(Set_Button_Xpath);
			fnsLoading_wait_In_SAM(2);
			fnsLoading_wait_In_SAM(1);
		} catch (Throwable t) {
			fnpMymsg(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}

	public static void fns_SelectDateFromCalendar_InSAM_New(String Cal_LabelName) throws Throwable {
		try {
			String DateCalButton_Xpath = "(//label[contains(text(), '" + Cal_LabelName
					+ "')]/following::span[@class='k-icon k-i-calendar'])[1]";
			// String DateXpath = "(//td[@class='k-calendar-td k-today']/span)[1]";
			// Sam calender today link xpath change
			String DateXpath = "//span[@class='k-today k-nav-today' and contains(text(), 'Today')]";

			fnpCheckElementDisplayed_NOR(DateCalButton_Xpath);
			fnpClick_NOR(DateCalButton_Xpath);
			fnsLoading_wait_In_SAM(1);
			fnsLoading_wait_In_SAM(1);

			fnpCheckElementDisplayed_NOR(DateXpath);
			fnpClick_NOR(DateXpath);
			fnsLoading_wait_In_SAM(1);
			fnsLoading_wait_In_SAM(1);
		} catch (Throwable t) {
			fnpMymsg(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}

}
