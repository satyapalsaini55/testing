package nsf.ecap.Direct_NSFOnline_suite;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import nsf.ecap.IssueMgt_Suite.TestSuiteBase_IM;
import nsf.ecap.MonitorPlan_Suite.TestSuiteBase_MonitorPlan;
import nsf.ecap.Work_Order_suite.TestSuiteBase;
import nsf.ecap.util.ErrorUtil;
import nsf.ecap.util.TestUtil;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.google.common.base.Throwables;

public class TestSuiteBase_Direct_NSFOnline extends TestSuiteBase {

	public static Direct_NSFOnline_Search_Docs BS_NSFO_01;

	public static String currentClassNameInSimpleText = "";

	public static boolean NSFOnlineVersionScreenshot = true;

	// public static boolean IsBrowserPresentAlready = false;
	public static String InsightoriginalHandle = null;
	public static String MainWindowHandle = null;

	public static String validation_error_msg_text;
	// public static boolean isTestPass = true;
	static boolean fail = false;
	public static String SearchResult_Code_link_Xpath;
	public static String BrowserName = "IE";

	public static String runmodes[] = null;
	public static Integer ColumnNo;
	public static Integer RowCount;

	public static boolean TCSkipReturnValue = false;
	public static boolean ScreenShotFlagWithOR_NsfOnline = false;

	// ######################################################### Common
	// Functions
	// #######################################################################

	// check if the suite has to be skip
	@BeforeSuite
	public void checkSuiteSkip() throws Throwable {
		currentSuiteName = "Direct_NSFOnline_suite";
		fnInitialize();
		BrowserCheck();
		if (TestUtil.isSuiteRunnable(suiteXls, currentSuiteName)) {
			FileUtils.deleteDirectory(new File((System.getProperty("user.dir") + CONFIG.getProperty("screenshots_path") + "//currentSuiteName//")));
			browserName = TestUtil.getBrowserName(suiteXls, currentSuiteName);
			excelSiteURL = TestUtil.getExcelSiteURL(suiteXls, currentSuiteName);

			int rowNum = suiteXls.getCellRowNum("Test Suite", "TSID", currentSuiteName);
			suiteXls.setCellData("Test Suite", "Last_Execution", rowNum, fnReturnDateWithTimeForExcel());

			currentSuiteCode = "Direct_NSFO";
			currentSuiteXls = Direct_NSFOnline_suitexls;

		}
		if (!TestUtil.isSuiteRunnable(suiteXls, currentSuiteName)) {
			fnpMymsg("Skipped NSFOnline_Suite as the runmode was set to NO");
			throw new SkipException("Runmode of NSFOnline_Suite set to no. So Skipping all tests in NSFOnline_Suite");
		}

		/* fnpMymsg("Skipped "+currentSuiteName+" as the runmode was set to NO");
		 * fnpMymsg("####################"+currentSuiteName+
		 * " End ############################################################");
		 * fnpMymsg(
		 * "========================================================================================================================================="
		 * ); throw new SkipException("Runmode of "+currentSuiteName+
		 * " set to no. So Skipping all tests in "+currentSuiteName); */
	}

	// Always run after suite
	@AfterSuite(alwaysRun = true)
	public static void Finishing_NSFOnline_Suite_Script() throws Throwable {

		ScreenShotFlagWithOR_NsfOnline = false;
		fnpMymsg("");
		fnpMymsg("########################################################### Direct_NSFOnline Suite END ########################################################## ");
		fnpMymsg("=========================================================================================================================================");
		fnpMymsg("");
	}

	/*********** Function to Launch the browser and login with user credential details *************/
	public static boolean fnpLaunchBrowserAndLoginDirectNSFOnline(String userName, String password) throws Exception {
		boolean present;
		try {

			killprocess();

			fnpLaunchBrowser();

			// String siteUrl = CONFIG.getProperty("testSiteName");

			String siteUrl = null;

			if (excelSiteURL != null) {
				if (excelSiteURL.equalsIgnoreCase("")) {
					siteUrl = CONFIG.getProperty("DirectNSFOnlineTestSiteName");
				} else {
					siteUrl = excelSiteURL;

				}
			} else {
				siteUrl = CONFIG.getProperty("DirectNSFOnlineTestSiteName");
			}

			driver.get(siteUrl);
			//driver.manage().deleteAllCookies();//special case , till now we did not login
			TestSuiteBase.fnpMymsg("Navigating to url --->" + siteUrl);

			driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(Long.parseLong(CONFIG.getProperty("pageLoadTime")), TimeUnit.SECONDS);

			if (browserName.equalsIgnoreCase("IE")) {

				Thread.sleep(2000);
				
				//new Actions(driver).sendKeys(Keys.chord(Keys.ALT, Keys.NUMPAD9)).perform();
				//new Actions(driver).sendKeys(Keys.chord(Keys.ALT, "9")).perform();
				
/*				new Actions(driver).sendKeys(Keys.F12);
				Thread.sleep(5000);
				 new Actions(driver).keyDown(Keys.ALT).sendKeys("9").perform();				
				Thread.sleep(8000);*/

				List<WebElement> totalElements = driver.findElements(By.xpath(".//input[@id='_id133']"));
				int p = totalElements.size();

				WebElement loginBtn = driver.findElement(By.xpath(".//*[@id='loginClient']"));

				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("document.getElementsByName('_id133')[0].value='" + userName + "';");

				jse.executeScript("document.getElementsByName('_id137')[0].value='" + password + "';");

				Thread.sleep(1000);
				// Thread.sleep(1000);
				WebElement element = driver.findElement(By.id("loginClient"));
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", element);

				// Thread.sleep(10000);
				// fnpLoading_wait_InNSFOnline();

			} else {

				WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(CONFIG.getProperty("genMax_waitTime")));
				WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("DirectNSFOnlineUsernameTxt"))));

				fnpWaitForVisible("DirectNSFOnlineUsernameTxt");
				fnpWaitTillVisiblityOfElementNClickable_OR("DirectNSFOnlineUsernameTxt");

				Thread.sleep(1000);

				fnpWaitForVisible("DirectNSFOnlineLoginBtn");
				TestSuiteBase.fnpWaitTillVisiblityOfElementNClickable_OR("DirectNSFOnlineLoginBtn");

				fnpGetORObjectX("DirectNSFOnlineUsernameTxt").sendKeys("");
				fnpGetORObjectX("DirectNSFOnlineUsernameTxt").sendKeys(userName);

				fnpGetORObjectX("DirectNSFOnlinePasswordTxt").sendKeys("");
				fnpGetORObjectX("DirectNSFOnlinePasswordTxt").sendKeys(password);

				fnpMymsg("Just before login clicked");
				fnpGetORObjectX("DirectNSFOnlineLoginBtn").click();

			}

			fnpMymsg("Just login clicked");
			Thread.sleep(3000); // for time being ...plz remove it later
			fnpLoading_wait_InDirectNSFOnline();
			Thread.sleep(1000); // for time being ...plz remove it later
			fnpMymsg("Just after NSF ONline loading wait after login clicked");

			if (fnpCheckElementDisplayedImmediately("DirectNSFOnlineErrorMessage")) {
				String errMsg = fnpGetText_OR_EvenMultipleObjectsHavingSameXpath_specialForExtractingErrorMsgTextOnlyEvenIfNotVisible("DirectNSFOnlineErrorMessage");
				fnpMymsg(" Error is thrown by application ----" + errMsg);
				throw new Exception(" Login is not successfull. Error is thrown by application --- " + errMsg + "\n\n Error is --->" + errMsg);

				//throw new Exception("Login is not successfull.");
			}

			fnpCheckDirectNSFOnlineErrorMsg();
			driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);

			/*					*//**** Because in this case landing page 's controls not identified
			 * in IE ***********/
			/* if (!(currentClassNameInSimpleText.equalsIgnoreCase(
			 * "Direct_NSFO_Verify_CARs_Audits"))) { //Thread.sleep(2000); try{
			 * fnpWaitForVisible("DirectNSFOnlineLogout");
			 * fnpWaitTillVisiblityOfElementNClickable_OR
			 * ("DirectNSFOnlineLogout"); }catch(Throwable t){
			 * Thread.sleep(2000); fnpWaitForVisible("DirectNSFOnlineLogout"); }
			 * } */

			present = true;

		} catch (Throwable t) {
			fnpMymsg("Login Failed");
			fnpDesireScreenshot("LoginFailed");
			present = false;
			String stackTrace = Throwables.getStackTraceAsString(t);
			String errorMsg = t.getMessage();
			errorMsg = errorMsg + "\n\n\n\n Login failed.See screenshot 'LoginFailed' \n\n" + stackTrace;
			Exception c = new Exception(errorMsg);
			ErrorUtil.addVerificationFailure(c);

			killprocess();
			// IsBrowserPresentAlready = false;

			throw new Exception(errorMsg);// reports

		}

		return present;
	}

	// Function to Take Screen Shot of NSF Online Application Version
	public static void fnpDesireScreenshot_NSFOnlineVersion(String imageName) throws Exception {
		try {
			String path1 = System.getProperty("user.dir") + screenshots_path + "//screenshots_" + currentSuiteCode + "//" + currentScriptCode + "//" + "//applicationVersion_NSFOnline//";
			String path2 = System.getProperty("user.dir") + screenshots_path + "//screenshots_" + currentSuiteCode + "//applicationVersion_NSFOnline//";
			//FileUtils.forceMkdir(new File(path1));
			FileUtils.forceMkdir(new File(path2));
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			Rectangle screenRectangle = new Rectangle(screenSize);
			Robot robot = new Robot();
			BufferedImage image = robot.createScreenCapture(screenRectangle);

			//ImageIO.write(image, "png", new File((path1 + imageName + ".PNG")));
			ImageIO.write(image, "png", new File((path2 + imageName + ".PNG")));
		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			isTestPass = false;
			throw new Exception(W.getMessage());
		}

		catch (java.lang.NullPointerException n) {
			fnpMymsg("ScreenShotNullPointerException >> " + n.getMessage());
			throw new Exception("NullPointerException Unable To take Screen Shots.");
		} catch (java.io.IOException e) {
			fnpMymsg("ScreenShotIOException >> " + e.getMessage());
			throw new Exception("IOException Unable To take Screen Shots.");
		}
	}

	/* public static void fnpDesireScreenshot(String message) {
	 * 
	 * try{ FileUtils.forceMkdir(new File((System.getProperty("user.dir") +
	 * screenshots_path+ "//screenshots_"+currentSuiteCode + "//" +
	 * currentScriptCode + "//"))); Dimension screenSize =
	 * Toolkit.getDefaultToolkit().getScreenSize(); Rectangle screenRectangle =
	 * new Rectangle(screenSize); Robot robot = new Robot(); BufferedImage image
	 * = robot.createScreenCapture(screenRectangle); //
	 * System.out.println("IMAGE PATH = "+((System.getProperty("user.dir")
	 * +screenshots_path+ "//screenshots_Insight//"+currentScriptCode
	 * +"//"+message+"_"+fnsScreenShot_Date_format()+".PNG")));
	 * ImageIO.write(image, "png", new File((System.getProperty("user.dir") +
	 * screenshots_path+ "//screenshots_"+currentSuiteCode + "//" +
	 * currentScriptCode +"//"+message+"_"+".PNG"))); }catch(Throwable t) {
	 * t.printStackTrace(); } } */

	// Function to Take Screen Shot of Aspirago Online Application Version
	public static void fnpDesireScreenshot_AspiragoOnlineVersion(String message) throws Exception {
		try {
			FileUtils.forceMkdir(new File((System.getProperty("user.dir") + screenshots_path + "//applicationVersion_AspiragoOnline//")));
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			Rectangle screenRectangle = new Rectangle(screenSize);
			Robot robot = new Robot();
			BufferedImage image = robot.createScreenCapture(screenRectangle);
			// System.out.println("IMAGE PATH = "+((System.getProperty("user.dir")
			// + screenshots_path+ "//applicationVersion//"+message+".PNG")));
			ImageIO.write(image, "png", new File((System.getProperty("user.dir") + screenshots_path + "//applicationVersion_AspiragoOnline//" + message + ".PNG")));
		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			isTestPass = false;
			throw new Exception(W.getMessage());
		}

		catch (java.lang.NullPointerException n) {
			// System.out.println("IMAGE PATH = "+((System.getProperty("user.dir")
			// + screenshots_path+ "//applicationVersion_NSFOnline//"
			// +message+"_"+fnsScreenShot_Date_format()+".PNG")));
			fnpMymsg("ScreenShotNullPointerException >> " + n.getMessage());
			throw new Exception("NullPointerException Unable To take Screen Shots.");
		} catch (java.io.IOException e) {
			fnpMymsg("ScreenShotIOException >> " + e.getMessage());
			throw new Exception("IOException Unable To take Screen Shots.");
		}
	}

	// Function to Take Screen Shot of I-Pulse Application Version
	public static void fnsIPulse_Application_Version(String message) throws Exception {

		try {

			/*// Thread.sleep(10000);
			TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(OR_NsfOnline.getProperty("Footer_IPulse"));
	//		Thread.sleep(1500);
		//	TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(OR_NsfOnline.getProperty("Ipulse_Version"));
	//		WebElement VersionWE = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(OR_NsfOnline.getProperty("Ipulse_Version"));
			Actions action = new Actions(driver);
			action.moveToElement(VersionWE).sendKeys(VersionWE, Keys.CONTROL).click().build().perform();
			action.keyDown(Keys.CONTROL).build().perform();
*/
			Thread.sleep(1500);

			FileUtils.forceMkdir(new File((System.getProperty("user.dir") + screenshots_path + "//applicationVersion//")));
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			Rectangle screenRectangle = new Rectangle(screenSize);
			Robot robot = new Robot();
			BufferedImage image = robot.createScreenCapture(screenRectangle);
			// System.out.println("IMAGE PATH = "+((System.getProperty("user.dir")
			// + screenshots_path+
			// "//applicationVersion//"+message+"_"+fnsScreenShot_Date_format()+".PNG")));
			ImageIO.write(image, "png", new File((System.getProperty("user.dir") + screenshots_path + "//applicationVersion//" + message + "_ApplicationVersion.PNG")));

			fnsWait_and_Click("Ipulse_Version_Dialog_Close_Bttn");
			Thread.sleep(1000);

			// driver.findElement(By.xpath(""));

		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			isTestPass = false;
			throw new Exception(W.getMessage());
		}

		catch (java.lang.NullPointerException n) {
			// System.out.println("IMAGE PATH = "+((System.getProperty("user.dir")
			// + screenshots_path+
			// "//applicationVersion//"+message+"_"+fnsScreenShot_Date_format()+".PNG")));
			fnpMymsg("ScreenShotNullPointerException >> " + n.getMessage());
			throw new Exception("NullPointerException Unable To take Screen Shots.");
		} catch (java.io.IOException e) {
			fnpMymsg("ScreenShotIOException >> " + e.getMessage());
			throw new Exception("IOException Unable To take Screen Shots.");
		}
	}

	// Function for Application Log Date format
	public static String fnsLOGS_Date_format() {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date date = new Date();
		return (dateFormat.format(date));
	}

	// Function for Insight Creation Text field Date Format
	public static String fnsInsightCreationText_Date_format() {
		DateFormat dateFormat = new SimpleDateFormat("ddMMMyyyy/HH:mm z");
		Date date = new Date();
		return (dateFormat.format(date));
	}

	// Function for Screen date format
	public static String fnsScreenShot_Date_format() {
		DateFormat dateFormat = new SimpleDateFormat("_dd.MM.yyyy_HH.mm.ss");
		Date date = new Date();
		return (dateFormat.format(date));
	}

	// Function for Screen date format
	public static String fnsEditInsight_Date_format() {
		DateFormat dateFormat = new SimpleDateFormat("MMddyyyy");
		Date date = new Date();
		return (dateFormat.format(date));
	}

	// Function to find and return the object using Xpath
	public static WebElement fnsGet_OR_NsfOnline_ObjectX(String XpathKey) throws Exception {

		try {
			// driver.manage().timeouts().implicitlyWait((Long.parseLong(CONFIG.getProperty("ElementWaitTime"))),
			// TimeUnit.SECONDS);
			for (int waits = 0; waits < Long.parseLong(CONFIG.getProperty("ElementWaitTime")); waits++) {

				if (driver.findElements(By.xpath(OR_NsfOnline.getProperty(XpathKey))).size() > 0) {
					break;
				} else {
					Thread.sleep(500);
				}

			}
			return driver.findElement(By.xpath(OR_NsfOnline.getProperty(XpathKey)));

		} catch (StaleElementReferenceException e) {
			WebDriverWait stalewaitvar = new WebDriverWait(driver, 10);
			stalewaitvar.until(ExpectedConditions.presenceOfElementLocated(By.xpath(OR_NsfOnline.getProperty(XpathKey))));
			WebElement webelemnt = driver.findElement(By.xpath(OR_NsfOnline.getProperty(XpathKey)));
			stalewaitvar.until(ExpectedConditions.stalenessOf(webelemnt));
			return driver.findElement(By.xpath(OR_NsfOnline.getProperty(XpathKey)));
		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			isTestPass = false;
			throw new Exception(W.getMessage());
		} catch (NoSuchElementException e) {
			isTestCasePass = false;
			isTestPass = false;
			fnpDesireScreenshot("NoSuchElementException" + XpathKey);
			fnpMymsg("FAILED == Element is not found >> " + XpathKey + " ] , plz see screenshot [NoSuchElementException" + XpathKey + fnsScreenShot_Date_format() + " ]");
			throw new Exception("FAILED == Element is not found >> " + XpathKey + " ] , plz see screenshot [NoSuchElementException" + XpathKey + fnsScreenShot_Date_format() + " ]");
		} catch (TimeoutException e) {
			isTestCasePass = false;
			isTestPass = false;
			fnpDesireScreenshot("TimeOut" + XpathKey);
			fnpMymsg("FAILED == Element is not Present, getting TimeOut >> " + XpathKey + " ] , plz see screenshot [TimeOut" + XpathKey + fnsScreenShot_Date_format() + " ]");
			throw new Exception("FAILED == Element is not Present, getting TimeOut >> " + XpathKey + " ] , plz see screenshot [TimeOut" + XpathKey + fnsScreenShot_Date_format() + " ]");
		} catch (Throwable e) {
			isTestCasePass = false;
			isTestPass = false;
			fnpDesireScreenshot("NotPresent" + XpathKey);
			fnpMymsg("FAILED == Element is not found >> " + XpathKey + " ] , plz see screenshot [NotPresent" + XpathKey + fnsScreenShot_Date_format() + " ]" + ". Getting Exception  >> " + e.getMessage());
			throw new Exception("FAILED == Element is not found >> " + XpathKey + " ] , plz see screenshot [NotPresent" + XpathKey + fnsScreenShot_Date_format() + " ]");
		}
	}

	// Function to click on Object
	public static void fnsWait_and_Click(String XpathKey) throws Exception {

		try {
			// fnsPage_Loading_wait();
			/* WebDriverWait waitvar = new WebDriverWait(driver,
			 * Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
			 * waitvar.until
			 * (ExpectedConditions.visibilityOfElementLocated(By.xpath
			 * (OR_NsfOnline.getProperty(XpathKey)))); */
			fnsGet_OR_NsfOnline_ObjectX(XpathKey).click();
			fnpMymsg("PASSED == Click on Element done having Xpath >> " + XpathKey);
		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			isTestPass = false;
			throw new Exception(W.getMessage());
		} catch (StaleElementReferenceException e) {
			isTestCasePass = false;
			isTestPass = false;
			fnpDesireScreenshot("UnableToClick_" + XpathKey);
			fnpMymsg("FAILED == Unable To Click on Element having Xpath >> " + XpathKey + ", plz see screenshot [ UnableToClick_" + XpathKey + fnsScreenShot_Date_format() + " ]");
			throw new Exception("Unable To Click on element [ " + XpathKey + " ] , plz see screenshot [ UnableToClick_" + XpathKey + fnsScreenShot_Date_format() + " ]");
		}
	}

	/**** Function clicking of an object (button etc) which is present in OR ****/
	public static void fnpClick_OR_DirectNSFOnline(String xpathkey) throws Throwable {

		int i = 0;

		while (fnpCheckDirectNSFOnlineLoadingDisplayedImmediately("NSFOnline_LoadingImg")) {
			Thread.sleep(1000);
			i++;
			fnpMymsg("@Pradeep NSFONLine loading icon is visible in fnpClick_OR_NSFOnline  ---" + i);
			if (i > Integer.parseInt(CONFIG.getProperty("genMax_waitTime")) * 2) {
				break;

			}

		}
		Thread.sleep(500);
		fnpClick_OR_WithoutWait_DirectNSFOnline(xpathkey);
		// driver.findElement(By.xpath(OR.getProperty(xpathkey))).click();
		// Thread.sleep(2000);
		fnpLoading_wait_InDirectNSFOnline();

	}

	/**** Function clicking of an object (button etc) which is present in OR
	 * without handling loading Wait ****/
	public static void fnpClick_OR_WithoutWait_DirectNSFOnline(String xpathkey) throws Throwable {
		int retries = 0;

		int i = 0;
		while (fnpCheckDirectNSFOnlineLoadingDisplayedImmediately("NSFOnline_LoadingImg")) {
			Thread.sleep(1000);
			i++;
			fnpMymsg("@Pradeep NSFONLine loading icon is visible in fnpClick_OR_WithoutWait_NSFOnline ---" + i);
			if (i > Integer.parseInt(CONFIG.getProperty("genMax_waitTime")) * 2) {
				break;

			}

		}

		while (true) {
			try {
				// fnpWaitForVisible(xpathkey); //these functions not working
				// properly in NSF ONline
				// fnpWaitTillVisiblityOfElementNClickable_OR_DirectNSFOnline(xpathkey);
				// fnpGetORObjectX(xpathkey).click();
				fnpMymsg(" Going to click '" + xpathkey + "' .");
				driver.findElement(By.xpath(OR.getProperty(xpathkey))).click();
				fnpMymsg(" Clicked '" + xpathkey + "' .");
				break;

			} catch (org.openqa.selenium.StaleElementReferenceException e) {
				// if (retries <
				// Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
				if (retries < 3) {
					// if (retries <2) {
					Thread.sleep(1000);
					retries++;
					fnpMymsg(retries + "In staleElementException catch block of fnpClick_OR_WithoutWait_DirectNSFOnline function for " + xpathkey);
					// continue;
				} else {
					throw e;
				}
			}

			catch (org.openqa.selenium.InvalidSelectorException is) {
				// if (retries <
				// Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
				if (retries < 3) {
					// if (retries <2) {
					Thread.sleep(1000);
					retries++;
					fnpMymsg(retries + "In org.openqa.selenium.InvalidSelectorException catch block of fnpClick_OR_WithoutWait_DirectNSFOnline function for " + xpathkey);
					// continue;
				} else {
					throw is;
				}
			}

			catch (org.openqa.selenium.WebDriverException w) {
				if (w.getMessage().contains("Element is not clickable at point")) {
					// if (retries <
					// Integer.parseInt(CONFIG.getProperty("genMax_waitTime")))
					// {
					if (retries < 3) {
						// if (retries < 2) {
						retries++;
						Thread.sleep(1000);
						fnpMymsg(retries + "In WebDriverException catch block of fnpClick_OR function for " + xpathkey);
						// continue;
					} else {
						throw w;
					}
				} else {
					throw w;
				}
			}

			catch (Throwable t) {
				if (retries < 1) {
					// fnpMymsg("@Pradeep---Going to refresh the page in fnpClick_OR_WithoutWait_DirectNSFOnline for "
					// + xpathkey);
					// driver.navigate().refresh();
					retries++;
					fnpMymsg(retries + "In fnpClick_OR_WithoutWait_DirectNSFOnline 's try catch block of Throwable for " + xpathkey);
					continue;
				} else {
					throw t;
				}
			}

		}

		// fnpCheckError("");
		fnpCheckDirectNSFOnlineErrorMsg();

	}

	/* public static void fnpClick_DNSFO_OR(String xpathkey) throws Throwable {
	 * int retries = 0;
	 * 
	 * int i = 0; while
	 * (fnpCheckDirectNSFOnlineLoadingDisplayedImmediately("NSFOnline_LoadingImg"
	 * )) { Thread.sleep(1000); i++;
	 * fnpMymsg("@Pradeep NSFONLine loading icon is visible in fnpClick_DNSFO ---"
	 * + i); if (i > Integer.parseInt(CONFIG.getProperty("genMax_waitTime")) *
	 * 2) { break;
	 * 
	 * }
	 * 
	 * }
	 * 
	 * while (true) { try {
	 * driver.findElement(By.xpath(OR.getProperty(xpathkey))).click();
	 * fnpMymsg(" Clicked '" + xpathkey + "' ."); break;
	 * 
	 * } catch (org.openqa.selenium.StaleElementReferenceException e) { // if
	 * (retries < // Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
	 * if (retries < 3) { // if (retries <2) { Thread.sleep(1000); retries++;
	 * fnpMymsg(retries +
	 * "In staleElementException catch block of fnpClick_DNSFO function for " +
	 * xpathkey); // continue; } else { throw e; } }
	 * 
	 * catch (org.openqa.selenium.InvalidSelectorException is) { // if (retries
	 * < // Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) { if
	 * (retries < 3) { // if (retries <2) { Thread.sleep(1000); retries++;
	 * fnpMymsg(retries +
	 * "In org.openqa.selenium.InvalidSelectorException catch block of fnpClick_DNSFO function for "
	 * + xpathkey); // continue; } else { throw is; } }
	 * 
	 * catch (org.openqa.selenium.WebDriverException w) { if
	 * (w.getMessage().contains("Element is not clickable at point")) { // if
	 * (retries < // Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) //
	 * { if (retries < 3) { // if (retries < 2) { retries++; Thread.sleep(1000);
	 * fnpMymsg(retries +
	 * "In WebDriverException catch block of fnpClick_OR function for " +
	 * xpathkey); // continue; } else { throw w; } } else { throw w; } }
	 * 
	 * catch (Throwable t) { if (retries < 1) {
	 * fnpMymsg("@Pradeep---Going to refresh the page in fnpClick_DNSFO for " +
	 * xpathkey); driver.navigate().refresh(); retries++; fnpMymsg(retries +
	 * "In fnpClick_DNSFO 's try catch block of Throwable for " + xpathkey);
	 * continue; } else { throw t; } }
	 * 
	 * }
	 * 
	 * // fnpCheckError(""); fnpCheckDirectNSFOnlineErrorMsg();
	 * 
	 * } */
	/* public static void fnpInsert_DNSFO_OR(String xpathkey, String values)
	 * throws Throwable { int retries = 0;
	 * 
	 * int i = 0;
	 * 
	 * while (true) { try {
	 * 
	 * driver.findElement(By.xpath(OR.getProperty(xpathkey))).clear();
	 * driver.findElement(By.xpath(OR.getProperty(xpathkey))).sendKeys(values);
	 * fnpMymsg(" Clicked '" + xpathkey + "' ."); break;
	 * 
	 * } catch (org.openqa.selenium.StaleElementReferenceException e) { // if
	 * (retries < // Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
	 * if (retries < 3) { // if (retries <2) { Thread.sleep(1000); retries++;
	 * fnpMymsg(retries +
	 * "In staleElementException catch block of fnpClick_DNSFO function for " +
	 * xpathkey); // continue; } else { throw e; } }
	 * 
	 * catch (org.openqa.selenium.InvalidSelectorException is) { // if (retries
	 * < // Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) { if
	 * (retries < 3) { // if (retries <2) { Thread.sleep(1000); retries++;
	 * fnpMymsg(retries +
	 * "In org.openqa.selenium.InvalidSelectorException catch block of fnpClick_DNSFO function for "
	 * + xpathkey); // continue; } else { throw is; } }
	 * 
	 * catch (org.openqa.selenium.WebDriverException w) { if
	 * (w.getMessage().contains("Element is not clickable at point")) { // if
	 * (retries < // Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) //
	 * { if (retries < 3) { // if (retries < 2) { retries++; Thread.sleep(1000);
	 * fnpMymsg(retries +
	 * "In WebDriverException catch block of fnpClick_OR function for " +
	 * xpathkey); // continue; } else { throw w; } } else { throw w; } }
	 * 
	 * catch (Throwable t) { if (retries < 1) {
	 * fnpMymsg("@Pradeep---Going to refresh the page in fnpClick_DNSFO for " +
	 * xpathkey); driver.navigate().refresh(); retries++; fnpMymsg(retries +
	 * "In fnpClick_DNSFO 's try catch block of Throwable for " + xpathkey);
	 * continue; } else { throw t; } }
	 * 
	 * }
	 * 
	 * // fnpCheckError(""); fnpCheckDirectNSFOnlineErrorMsg();
	 * 
	 * } */
	/******* Check error using page source ****/
	public static void fnpCheckErrorUsingPageSource_DirectNSFONLINE2() throws Throwable {
		try {

			// driver.manage().timeouts().implicitlyWait(500,
			// TimeUnit.MILLISECONDS);
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.MILLISECONDS);
			String PageSourceText = driver.getPageSource().toLowerCase();

			// List<WebElement> errMsgList=null;
			List<WebElement> errMsgList = new ArrayList<WebElement>();
			// if (driver.getPageSource().contains("validationmessages") |
			// driver.getPageSource().contains("validationMessages")) {
			if (PageSourceText.contains("validationmessages") | PageSourceText.contains("validation")) {
				// errMsgList =
				// driver.findElements(By.xpath(OR.getProperty("DirectNSFOnlineErrorMessage")));
				int p = 0;
				while (true) {
					p++;
					try {
						fnpMymsg("@Pradeep : in try loop for errMsgList for chance---" + p);
						// errMsgList =
						// driver.findElements(By.xpath(OR.getProperty("DirectNSFOnlineErrorMessage")));
						errMsgList = fnpReturnFindElementsList_NOR(OR.getProperty("DirectNSFOnlineErrorMessage"));
						break;
					} catch (Throwable t) {
						fnpMymsg("@Pradeep : in catch loop for errMsgList for chance---" + p);
						if (p > Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
							break;
						}
					}
				}
				int sizeErrMsgList = errMsgList.size();

				int i = 0;

				driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);
				for (Iterator iterator = errMsgList.iterator(); iterator.hasNext();) {

					try {
						WebElement webElement = (WebElement) iterator.next();
						// System.out.println("Pradeep ---"+i+" ----is displayed? --"+webElement.isDisplayed()
						// );
						// fnpMymsg("Pradeep ---"+i+" ----is displayed? --"+webElement.isDisplayed()
						// );
						if (webElement.isDisplayed()) {
							fnpCheckError_NSFONLINE("");
							// throw new
							// Exception("Error is present in page source as find by method.  Error is thrown by application . Error is --->"
							// + fnpGetORObjectX("ErrorMessage").getText());
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
			driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);

		}

	}

	/******* Check error using page source ****/
	public static void fnpCheckErrorUsingPageSource_DirectNSFONLINE1() throws Throwable {
		try {

			// driver.manage().timeouts().implicitlyWait(500,
			// TimeUnit.MILLISECONDS);
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.MILLISECONDS);
			String PageSourceText = driver.getPageSource().toLowerCase();

			// List<WebElement> errMsgList=null;
			List<WebElement> errMsgList = new ArrayList<WebElement>();
			// if (driver.getPageSource().contains("validationmessages") |
			// driver.getPageSource().contains("validationMessages")) {
			if (PageSourceText.contains("validation")) {
				// errMsgList =
				// driver.findElements(By.xpath(OR.getProperty("DirectNSFOnlineErrorMessage")));
				int p = 0;
				while (true) {
					p++;
					try {
						fnpMymsg("@Pradeep : in try loop for errMsgList for chance---" + p);
						// errMsgList =
						// driver.findElements(By.xpath(OR.getProperty("DirectNSFOnlineErrorMessage")));
						errMsgList = fnpReturnFindElementsList_NOR(OR.getProperty("DirectNSFOnlineErrorMessage"));
						break;
					} catch (Throwable t) {
						fnpMymsg("@Pradeep : in catch loop for errMsgList for chance---" + p);
						//if (p > Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
						if (p > 10) {
							break;
						}
					}
				}
				int sizeErrMsgList = errMsgList.size();

				int i = 0;

				driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);
				for (Iterator iterator = errMsgList.iterator(); iterator.hasNext();) {

					try {
						WebElement webElement = (WebElement) iterator.next();
						// System.out.println("Pradeep ---"+i+" ----is displayed? --"+webElement.isDisplayed()
						// );
						// fnpMymsg("Pradeep ---"+i+" ----is displayed? --"+webElement.isDisplayed()
						// );
						if (webElement.isDisplayed()) {
							fnpCheckError_DirectNSFONLINE("");
							// throw new
							// Exception("Error is present in page source as find by method.  Error is thrown by application . Error is --->"
							// + fnpGetORObjectX("ErrorMessage").getText());
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

		catch (Throwable t) {
			// nothing to do
		} finally {
			driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);

		}

	}

	/***** check error in application page ***/
	public static void fnpCheckError_NSFONLINE(String msg) throws Throwable {

		fnpMymsg("@Pradeep---running function fnpCheckError_NSFONLINE");
		if (fnpCheckElementDisplayedImmediately("NSFOnline_ErrorMsg")) {
			// fnpMouseHover("ErrorMessage");
			fnpMouseHover_OR_EvenMultipleObjectsHavingSameXpath("NSFOnline_ErrorMsg");
			String errMsg = fnpGetText_OR_EvenMultipleObjectsHavingSameXpath_specialForExtractingErrorMsgTextOnlyEvenIfNotVisible("NSFOnline_ErrorMsg");
			fnpMymsg(" Error is thrown by NSF Online  application " + msg);

			throw new Exception(" Error is thrown by application " + msg + "\n\n Error is --->" + errMsg);
		}

	}

	/***** check error in application page ***/
	public static void fnpCheckError_DirectNSFONLINE(String msg) throws Throwable {

		fnpMymsg("@Pradeep---running function fnpCheckError_NSFONLINE");
		if (fnpCheckElementDisplayedImmediately("DirectNSFOnlineErrorMessage")) {
			// fnpMouseHover("ErrorMessage");
			fnpMouseHover_OR_EvenMultipleObjectsHavingSameXpath("DirectNSFOnlineErrorMessage");
			String errMsg = fnpGetText_OR_EvenMultipleObjectsHavingSameXpath_specialForExtractingErrorMsgTextOnlyEvenIfNotVisible("DirectNSFOnlineErrorMessage");
			fnpMymsg(" Error is thrown by Direct NSF Online  application " + msg);

			throw new Exception(" Error is thrown by application " + msg + "\n\n Error is --->" + errMsg);
		}

	}

	/*********** Wait till main loading icon overs *************/
	public static void fnpLoading_wait_InDirectNSFOnline() throws Throwable {
		int i = 0;

		while (true) {
			if (fnpCheckDirectNSFOnlineLoadingDisplayedImmediately("NSFOnline_LoadingImg")) {
				fnpMymsg("@@@Pradeep NSFOnline_Loading Img is visible - fnpLoading_wait_InNSFOnline--" + i);
				break;
			} else {
				fnpMymsg("@@@Pradeep  waiting for NSFOnline_Loading Img is visible inside - fnpLoading_wait_InNSFOnline--" + i);
				Thread.sleep(1000);
				// Thread.sleep(1);
				i++;
				if (i > 2) {
					// throw new Exception
					// ("loading image not visible after 3 seconds");

					// fnpMymsg("@@@Pradeep  main loading icon not visible ,so trying again --"+
					// i);
					break;

				}
			}
		}

		i = 0;
		while (fnpCheckDirectNSFOnlineLoadingDisplayedImmediately("NSFOnline_LoadingImg")) {
			Thread.sleep(1000);
			i++;
			fnpMymsg("@Pradeep------in NSF ONline loading icon visible block'------" + i);
			if (i > Integer.parseInt(CONFIG.getProperty("genMax_waitTime")) * 2) {
				break;

			}

		}
		Thread.sleep(500);
		fnpMymsg("@Pradeep--Now come out from fnpLoading_wait_InDirectNSFOnline");
		fnpCheckDirectNSFOnlineErrorMsg();

	}

	/*********** Wait till main loading icon overs *************/
	public static void fnpLoading_wait_InDirectNSFOnline(int waitTime) throws Throwable {
		int i = 0;

		while (true) {
			if (fnpCheckDirectNSFOnlineLoadingDisplayedImmediately("NSFOnline_LoadingImg")) {
				fnpMymsg("@@@Pradeep NSFOnline_Loading Img is visible - fnpLoading_wait_InNSFOnline--" + i);
				break;
			} else {
				fnpMymsg("@@@Pradeep  waiting for NSFOnline_Loading Img is visible inside - fnpLoading_wait_InNSFOnline--" + i);
				Thread.sleep(1000);
				// Thread.sleep(1);
				i++;
				if (i > waitTime) {
					// throw new Exception
					// ("loading image not visible after 3 seconds");

					// fnpMymsg("@@@Pradeep  main loading icon not visible ,so trying again --"+
					// i);
					break;

				}
			}
		}

		i = 0;
		while (fnpCheckDirectNSFOnlineLoadingDisplayedImmediately("NSFOnline_LoadingImg")) {
			Thread.sleep(1000);
			i++;
			fnpMymsg("@Pradeep------in NSF ONline loading icon visible block'------" + i);
			if (i > Integer.parseInt(CONFIG.getProperty("genMax_waitTime")) * 2) {
				break;

			}

		}
		Thread.sleep(500);
		fnpMymsg("@Pradeep--Now come out from fnpLoading_wait_InDirectNSFOnline");
		fnpCheckDirectNSFOnlineErrorMsg();

	}

	// Function to wait for element and then type
	public static void fnsWait_and_Type(String XpathKey, String value) throws Exception {

		try {
			// fnsPage_Loading_wait();
			/* WebDriverWait waitvar = new WebDriverWait(driver,
			 * Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
			 * waitvar.until
			 * (ExpectedConditions.visibilityOfElementLocated(By.xpath
			 * (OR_NsfOnline.getProperty(XpathKey)))); */
			fnsGet_OR_NsfOnline_ObjectX(XpathKey).sendKeys("");
			fnsGet_OR_NsfOnline_ObjectX(XpathKey).sendKeys(value);
			fnpMymsg("PASSED == Type done Value<" + value + "> on Element having Xpath  >> " + XpathKey);
		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			isTestPass = false;
			throw new Exception(W.getMessage());
		} catch (StaleElementReferenceException e) {
			isTestCasePass = false;
			isTestPass = false;
			fnpDesireScreenshot("UnableToType_" + XpathKey);
			fnpMymsg("FAILED == Unable To Type on Element having Xpath  >> " + XpathKey + ", plz see screenshot [ UnableToType_" + XpathKey + fnsScreenShot_Date_format() + " ]");
			throw new Exception("Unable To Type on element [ " + XpathKey + " ] , plz see screenshot [ UnableToType_" + XpathKey + fnsScreenShot_Date_format() + " ]");
		}
	}

	// Function to wait for element
	public static void fnsGet_Element_Displayed(String XpathKey) throws Exception {

		try {
			for (int wait = 0; wait < 3; wait++) {
				if (driver.findElements(By.xpath(OR_NsfOnline.getProperty(XpathKey))).size() > 0) {
					// fnsGet_OR_NsfOnline_ObjectX(XpathKey);
					WebDriverWait elementwaitvar = new WebDriverWait(driver, Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
					elementwaitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_NsfOnline.getProperty(XpathKey))));

					WebDriverWait elementwaitvar1 = new WebDriverWait(driver, Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
					elementwaitvar1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_NsfOnline.getProperty(XpathKey)))).isDisplayed();
					fnpMymsg("PASSED == Element is Visible having Xpath  >> " + XpathKey);
					break;
				} // if loop closed
				else {
					Thread.sleep(3000);
					if (wait == 2) {
						WebDriverWait elementwaitvar2 = new WebDriverWait(driver, Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
						elementwaitvar2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_NsfOnline.getProperty(XpathKey)))).isDisplayed();
					} // if loop closed
				} // else loop closed
			} // for loop Closed

		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			isTestPass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			try {
				Thread.sleep(3000);
				WebDriverWait elementwaitvar3 = new WebDriverWait(driver, Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
				elementwaitvar3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_NsfOnline.getProperty(XpathKey)))).isDisplayed();// }
				fnpMymsg("PASSED == Element is Visible having Xpath  >> " + XpathKey);
			} catch (NoSuchWindowException W) {
				isTestCasePass = false;
				isTestPass = false;
				throw new Exception(W.getMessage());
			} catch (Throwable e) {
				isTestCasePass = false;
				isTestPass = false;
				fnpDesireScreenshot(XpathKey);
				fnpMymsg("FAILED == Element is not Visible having Xpath  >> " + XpathKey + ", plz see screenshot [ " + XpathKey + fnsScreenShot_Date_format() + " ]. Getting Exception >> " + e.getMessage());
				throw new Exception("FAILED == Element is not Visible having Xpath  [ " + XpathKey + " ] , plz see screenshot [ " + XpathKey + fnsScreenShot_Date_format() + " ]. Getting Exception >> " + e.getMessage());
			}
		}
	}

	// Function to Return 2days back date from past (day before Yesterday)
	public static String fnsGet_2Days_Past_date() throws Exception {

		Calendar cal = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy - HH:mm");
		cal.add(Calendar.DATE, -2);
		String Past_date = dateFormat.format(cal.getTime()).split("-")[0].trim();

		return Past_date;
	}

	// Function to Return current system time
	public static String fnsGet_Current_Time() throws Exception {
		Calendar cal = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy - HH:mm");
		cal.add(Calendar.DATE, -2);
		String current_time = dateFormat.format(cal.getTime()).split("-")[1].trim();

		return current_time;
	}

	// Click on Link by matchingText Xpath
	public static void fnsTable_ClickOn_LINK_ByMatchingText(String LinkMatchingText) throws Throwable {

		try {
			SearchResult_Code_link_Xpath = ".//a[text()='" + LinkMatchingText.trim() + "']";

		//	TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(SearchResult_Code_link_Xpath);

			/* WebDriverWait elementwaitvar1 = new WebDriverWait(driver,
			 * Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
			 * elementwaitvar1
			 * .until(ExpectedConditions.visibilityOfElementLocated
			 * (By.xpath(SearchResult_Code_link_Xpath))).isEnabled(); */
			driver.findElement(By.xpath(SearchResult_Code_link_Xpath)).click();
			fnpMymsg("PASSED == click on the Link done having xpath >> " + SearchResult_Code_link_Xpath);
		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			isTestPass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			isTestCasePass = false;
			isTestPass = false;
			fnpDesireScreenshot("LinkClickFail");
			fnpMymsg("FAILED == Unable to Click on Link having Xpath >> " + SearchResult_Code_link_Xpath + ", Please refer ScreenShot [ " + "LinkClickFail" + fnsScreenShot_Date_format() + " ]." + ". Getting Exception  >> " + t.getMessage());
			throw new Exception("FAILED == Unable to Click on Link having Xpath >> " + SearchResult_Code_link_Xpath + ", Please refer ScreenShot [ " + "LinkClickFail" + fnsScreenShot_Date_format() + " ].");
		}
	}

	// ##########################################################################
	// Application Functions
	// ########################################################################################
	public static String fnsGet_Field_TEXT(String XpathKey) throws Exception {
		try {

			WebDriverWait elementwaitvar = new WebDriverWait(driver, Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
			elementwaitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_NsfOnline.getProperty(XpathKey)))).isEnabled();
			String TextFetch = fnsGet_OR_NsfOnline_ObjectX(XpathKey).getText();
			fnpMymsg("PASSED == Fetch the Text[" + TextFetch + "] on Element having xpath [ " + XpathKey + " ].");

			return TextFetch;
		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			isTestPass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			isTestCasePass = false;
			isTestPass = false;
			fnpDesireScreenshot("TextFetchFail" + XpathKey);
			fnpMymsg("FAILED == Unable to Fetch Text on Element having xpath [ " + "TextFetchFail" + XpathKey + fnsScreenShot_Date_format() + " ]." + ". Getting Exception  >> " + t.getMessage());
			throw new Exception("FAILED == Unable to Fetch Text on Element having xpath [ " + "TextFetchFail" + XpathKey + fnsScreenShot_Date_format() + " ].");
		}
	}

	// function to select drop down value
	public static void fnsDD_value_Select_By_MatchingText(String ddClickXpathKey, String ddListXpathKey, String TagName, String Value) throws Exception {

		boolean ValueNotMatched = true;

		try {
			fnsWait_and_Click(ddClickXpathKey);
			// fnsGet_Element_Enabled(ddListXpathKey);
			List<WebElement> DDobjectlists = fnsGet_OR_NsfOnline_ObjectX(ddListXpathKey).findElements(By.tagName(TagName));
			for (WebElement dd_value : DDobjectlists) {
				if (dd_value.getText().equals(Value)) {
					dd_value.click();
					ValueNotMatched = false;
					break;
				}

			}
			if (ValueNotMatched == true) {
				throw new Exception("Excel value is not matched with DropDown Value.");
			}

			fnpMymsg("PASSED == select value [ " + Value + " ] from drop down  done, having xpath >>  " + ddClickXpathKey);

		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			isTestPass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			isTestCasePass = false;
			isTestPass = false;
			fnpDesireScreenshot("DdValueSelectFail" + ddClickXpathKey);
			fnpMymsg("FAILED == Unable to select value from drop down [ " + ddClickXpathKey + fnsScreenShot_Date_format() + " ]" + ". Getting Exception  >> " + t.getMessage().trim());
			throw new Exception("Unable to select value from drop down [ " + ddClickXpathKey + " ],plz see screenshot [ DdValueSelectFail" + ddClickXpathKey + fnsScreenShot_Date_format() + " ]");
		}
	}

	// Verify Text fetched and matched with expected text.
	public static void fnsText_Fetch_and_Assert(String XpathKey, String MatchingText) throws Exception {

		fnsMove_To_MouseOver(XpathKey);
		String GetText = fnsGet_Field_TEXT(XpathKey).trim().toLowerCase();
		try {
			assert GetText.contains(MatchingText.toLowerCase()) : "FAILED == Expected Text (" + MatchingText + ") is not matched with the Actual Text (" + GetText + "), Please refer Screen shot >> TextNotMatch" + fnsScreenShot_Date_format();
			fnpMymsg("PASSED ==  Expected Text (" + MatchingText + ") is matched with the Actual Text (" + GetText + "), from element having xpath >> " + XpathKey);

		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			isTestPass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			isTestCasePass = false;
			isTestPass = false;
			fnpDesireScreenshot("TextNotMatch");
			fnpMymsg(t.getMessage());
			throw new Exception(t.getMessage());
		}
	}

	// Clicking on Search Customer Ajax link.
	public static void fnsMove_To_MouseOver(String Xpath) throws Exception {

		try {

			// fnsGet_Element_Enabled(Xpath);
			WebElement Customer = fnsGet_OR_NsfOnline_ObjectX(Xpath);

			Actions act = new Actions(driver);
			act.moveToElement(Customer).build().perform();

			fnpMymsg("PASSED == MouseOver to Element done having Xpath >> " + Xpath);
		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			isTestPass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			isTestCasePass = false;
			isTestPass = false;
			fnpDesireScreenshot("SearchCustomer_Ajax");
			fnpMymsg("FAILED == Clicking on Element Failed having Xpath[" + Xpath + "] , plz see screenshot [ " + "SearchCustomer_Ajax" + fnsScreenShot_Date_format() + "]" + ". Getting Exception  >> " + t.getMessage().trim());
			throw new Exception("FAILED == Clicking on Element Failed having Xpath[" + Xpath + "] , plz see screenshot [ " + "SearchCustomer_Ajax" + fnsScreenShot_Date_format() + "]" + ". Getting Exception  >> " + t.getMessage().trim());
		}
	}

	// Return Customer Column No. in search Table
	public static Integer fnsReturn_ColumnNo_By_MatchingColumnNameText(String TableFirstRowXpath, String ColumnName, String tagname) throws Exception {
		ColumnNo = 0;
		try {
			boolean ValueNotMatched = true;
			// fnsGet_Element_Enabled(TableFirstRowXpath);
			fnpMymsg("@Pradeep---just before fnpWaitForVisible(" + TableFirstRowXpath + ")");
			fnpWaitForVisible(TableFirstRowXpath);
			fnpMymsg("@Pradeep---just after fnpWaitForVisible(" + TableFirstRowXpath + ")");
			WebElement SearchtableFirstRow = fnpGetORObjectX(TableFirstRowXpath);
			List<WebElement> FirstRowAllObj = SearchtableFirstRow.findElements(By.tagName(tagname));
			fnpMymsg("@Pradeep---just after list");
			for (WebElement FirstRowObj : FirstRowAllObj) {
				ColumnNo++;
				String ColumnText = (FirstRowObj.getText()).toLowerCase().trim();
				fnpMymsg("ColumnText=" + ColumnText);
				String MatchingColumnName = ColumnName.toLowerCase().trim();

				if (ColumnText.contains(MatchingColumnName)) {
					ValueNotMatched = false;
					break;
				}
			}

			if (ValueNotMatched == true) {
				throw new Exception("Column Name (" + ColumnName + ") is not Exists into Search Table");
			}

			fnpMymsg("PASSED == Successfully get the Column No. and it is=" + ColumnNo);

			return ColumnNo;

		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			isTestPass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			isTestCasePass = false;
			isTestPass = false;
			fnpDesireScreenshot("ColumnNoFail");
			fnpMymsg("FAILED == Fetching Column No. of ColumnName(" + ColumnName + ") is getting Fail for table having Xpath >> " + TableFirstRowXpath + " ,Please refer screenshot [ColumnNoFail" + fnsScreenShot_Date_format() + "]" + ", Getting Exception  >> " + t.getMessage().trim());
			throw new Exception("FAILED == Fetching Column No. of ColumnName(" + ColumnName + ") is getting Fail for table having Xpath >> " + TableFirstRowXpath + " ,Please refer screenshot [ColumnNoFail" + fnsScreenShot_Date_format() + "]" + ", Getting Exception  >> " + t.getMessage().trim());
		}
	}

	// Get Total Row Count of search Table
	public static Integer fnsReturn_TotalRowCount(String TableFirstRowXpath) throws Exception {
		try {
			RowCount = 0;
			// fnsGet_Element_Enabled(TableFirstRowXpath);
			WebElement Searchtable = fnsGet_OR_NsfOnline_ObjectX(TableFirstRowXpath);
			List<WebElement> AllRowObj = Searchtable.findElements(By.tagName("tr"));

			for (WebElement countrows : AllRowObj) {
				RowCount++;
			}
			RowCount--;
			fnpMymsg("PASSED == Successfully Get Total Row Count of table having xpath >> " + TableFirstRowXpath + "and it is=" + RowCount);

			return RowCount;
		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			isTestPass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			isTestCasePass = false;
			isTestPass = false;
			fnpDesireScreenshot("TotalRowCountFail");
			fnpMymsg("FAILED == Total Row Count is getting Fail of table having xpath >> " + TableFirstRowXpath + " ,Please refer screenshot [TotalRowCountFail" + fnsScreenShot_Date_format() + "]" + ", Getting Exception  >> " + t.getMessage().trim());
			throw new Exception("FAILED == Total Row Count is getting Fail of table having xpath >> " + TableFirstRowXpath + " ,Please refer screenshot [TotalRowCountFail" + fnsScreenShot_Date_format() + "]" + ", Getting Exception  >> " + t.getMessage().trim());
		}
	}

	// Return Customer Column No. in search Table
	public static String fnpReturn_IntegerValue_From_AduitHomeAccountSummary_WebTableByMatchingRowText(String TableFirstRowXpath, String RowText) throws Exception {
		String AccountSummaryTableValue = "";
		try {
			RowCount = 0;
			Thread.sleep(1000);
			// fnsGet_Element_Enabled(TableFirstRowXpath);
			WebElement Searchtable = fnpGetORObjectX(TableFirstRowXpath);
			List<WebElement> AllRowObj = Searchtable.findElements(By.tagName("tr"));
			fnpMymsg("@Pradeep---Total AllRowObj are ---"+AllRowObj);
			String MatchingRowText = RowText.toLowerCase().trim();
			fnpMymsg("@Pradeep-matchingRowtext is ----"+MatchingRowText);

			for (WebElement countrows : AllRowObj) {
				RowCount++;
				String Rowxpath = (OR_NsfOnline.getProperty(TableFirstRowXpath) + "/tbody/tr[" + RowCount + "]");
				fnpMymsg("@Pradeep-Rowxpath is---"+Rowxpath);
				// String
				// RowFristColumnText=TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(Rowxpath).trim().toLowerCase();
				String RowFristColumnText = fnpGetText_NOR(Rowxpath).trim().toLowerCase();
				fnpMymsg("RowFirstColumnText=" + RowFristColumnText);

				if (RowFristColumnText.contains(MatchingRowText)) {
					String RowSecondColumnXpath = Rowxpath + "/td[" + 2 + "]";
					// String
					// RowSecondColumnText=TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(RowSecondColumnXpath);
					String RowSecondColumnText = fnpGetText_NOR(RowSecondColumnXpath);
					fnpMymsg("RowSecondColumnText=" + RowSecondColumnText);
					// TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element_and_DoubleClick(RowSecondColumnXpath);
					fnpMove_To_Element_and_DoubleClick_NOR(RowSecondColumnXpath);
					if (RowSecondColumnText.contains("%")) {
						AccountSummaryTableValue = RowSecondColumnText.replace("%", " ").trim();
						fnpMymsg("AccountSummaryTableValue=" + AccountSummaryTableValue);
						break;
					} else {
						AccountSummaryTableValue = RowSecondColumnText.trim();
						fnpMymsg("AccountSummaryTableValue=" + AccountSummaryTableValue);
						break;
					}
				}
			}

			return AccountSummaryTableValue;
		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			isTestPass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			isTestCasePass = false;
			isTestPass = false;
			fnpDesireScreenshot("FetchValueFail");
			fnpMymsg("FAILED == Fetching Column Value is getting fail from matching Row(" + RowText + "), Please refer screenshot [FetchValueFail" + fnsScreenShot_Date_format() + "]" + ", Getting Exception  >> " + t.getMessage().trim());
			throw new Exception("FAILED == Fetching Column Value is getting fail from matching Row(" + RowText + "), Please refer screenshot [FetchValueFail" + fnsScreenShot_Date_format() + "]" + ", Getting Exception  >> " + t.getMessage().trim());
		}
	}

	// Clicking on Menu Ajax Link
	public static void fnpAjax_Link_Click_By_Passing_TwoAjaxXPathx(String FirstAjaxXpaths, String SecondAjaxXpaths) throws Exception {
		int iwhileCounter = 0;

		while (true) {
			try {

				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				iwhileCounter++;

				Thread.sleep(500);
				WebElement First_Element = driver.findElement(By.xpath(OR.getProperty(FirstAjaxXpaths)));
				Actions action = new Actions(driver);
				action.moveToElement(First_Element).build().perform();
				//action.moveToElement(First_Element, 10, 10).build().perform();

				Thread.sleep(500);

				WebElement CreateContractorApplicant = driver.findElement(By.xpath(OR.getProperty(SecondAjaxXpaths)));
				action.moveToElement(CreateContractorApplicant).click().build().perform();
				//action.moveToElement(CreateContractorApplicant, 10, 10).click().build().perform();
				fnpMymsg("PASSED == Successfully Click on <" + (SecondAjaxXpaths) + ">.");
				driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);

				fnpLoading_wait_InDirectNSFOnline();
				break;
			} catch (Throwable t) {
				//if (iwhileCounter > 5) {
				if (iwhileCounter > 3) {
					isTestCasePass = false;
					isTestPass = false;
					fnpDesireScreenshot(SecondAjaxXpaths + "_Fail");
					fnpMymsg("FAILED == Clicking on <" + (SecondAjaxXpaths) + "> Failed, plz see screenshot [" + SecondAjaxXpaths + "_Fail" + fnsScreenShot_Date_format() + "]" + ". Getting Exception  >> " + t.getMessage().trim());
					throw new Exception("FAILED == Clicking on <" + (SecondAjaxXpaths) + "> Failed, plz see screenshot [" + SecondAjaxXpaths + "_Fail" + fnsScreenShot_Date_format() + "]" + ". Getting Exception  >> " + t.getMessage().trim());

				} else {
					// fnpMymsg("@Pradeep--- going to refresh because due to throwable exception caught ie. ---"
					// + t.getStackTrace());
					fnpMymsg("@Pradeep--- going to refresh because due to throwable exception caught ie. ---" + t.getMessage());
					driver.navigate().refresh();
				}
			}

			finally {
				driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);
			}
		}

	}

	/* public static String fnsInsight_SerchCustomer_UserLinkClick(String
	 * CustomerNumberString, String SearchUserNameString) throws Throwable {
	 * String Customer_Name = null; try{ MainWindowHandle =
	 * driver.getWindowHandle();
	 * 
	 * // fncAjax_Link_Click_By_Passing_TwoAjaxXPathx("CustomersTopMenuLink",
	 * "Customer_SearchCustomer_MenuAjaxLink");
	 * fnsGet_Element_Displayed("CustomersTopMenuLink"); Thread.sleep(2000);
	 * fnsGet_Element_Displayed("CustomersTopMenuLink");
	 * TestSuiteBase_MonitorPlan
	 * .WithOut_OR_fnMove_To_Element(OR_NsfOnline.getProperty
	 * ("CustomersTopMenuLink")); Thread.sleep(500);
	 * TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element_and_Click
	 * (OR_NsfOnline.getProperty("Customer_SearchCustomer_MenuAjaxLink"));
	 * 
	 * 
	 * fnsGet_Element_Enabled("CustomerTxtBox");
	 * fnsWait_and_Type("CustomerTxtBox", CustomerNumberString);
	 * 
	 * fnsWait_and_Click("SearchBtn");
	 * 
	 * fnsGet_Element_Enabled("FirstUserSetUPLink");
	 * fnsWait_and_Click("FirstUserSetUPLink");
	 * 
	 * 
	 * fnsGet_Element_Enabled("Insight_Customer_name_Text");
	 * Customer_Name=(fnsGet_Field_TEXT
	 * ("Insight_Customer_name_Text").split("\\-")[1]).toLowerCase().trim();
	 * 
	 * 
	 * 
	 * fnsGet_Element_Displayed("UserNameTxtBox"); Thread.sleep(3000);
	 * //fnsGet_Element_Enabled("UserNameTxtBox");
	 * fnsWait_and_Type("UserNameTxtBox", SearchUserNameString);
	 * fnsWait_and_Click("SearchUserBtn");
	 * 
	 * InsightoriginalHandle = driver.getWindowHandle();
	 * 
	 * Thread.sleep(1000);
	 * fnsTable_ClickOn_LINK_ByMatchingText(SearchUserNameString);
	 * Thread.sleep(20000);
	 * 
	 * }catch(Throwable t){ throw new Exception(t.getMessage()); } return
	 * Customer_Name; } */

	// ###################################################################
	// Configuration Function
	// ############################################################################
	/**/

	// Function to Launch browser and login
	public static boolean fnsLaunchBrowserAndLogin() throws Throwable {
		boolean present;
		startExecutionTime = fnpTimestamp();
		// fnpFirstTimeInitializationMethod();

		ScreenShotFlagWithOR_NsfOnline = true;

		try {

			// BrowserName=BrowserCheck();
			System.out.println("BrowserName  " + BrowserName);
			if (BrowserName.equalsIgnoreCase("IE")) {

				System.setProperty("webdriver.ie.driver", CONFIG.getProperty("IEDriverPath"));

				DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
				caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				// caps.setCapability("IGNORE_ZOOM_SETTING", true); // Not
				// Working
				caps.setCapability("IE_ENSURE_CLEAN_SESSION", true);
				caps.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
				caps.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
				caps.setCapability(InternetExplorerDriver.NATIVE_EVENTS, true);

				driver = new InternetExplorerDriver(caps);
				fnpMymsg("Browser type is IE");
			}

			if (BrowserName.equalsIgnoreCase("chrome")) {

				System.setProperty("webdriver.chrome.driver", CONFIG.getProperty("ChromeDriverPath"));
				driver = new ChromeDriver();
				fnpMymsg("Browser type is CHROME");
			}

			if (BrowserName.equalsIgnoreCase("firefox")) {

				FirefoxProfile profile = new FirefoxProfile();
				DesiredCapabilities dc = DesiredCapabilities.firefox();
				// profile.setAcceptUntrustedCertificates(false);
				// profile.setAssumeUntrustedCertificateIssuer(true);
				// profile.setPreference("browser.download.folderList", 2);
				// profile.setPreference("browser.helperApps.alwaysAsk.force",
				// false);
				profile.setPreference("security.mixed_content.block_active_content", false);
				profile.setPreference("security.mixed_content.block_display_content", false);

				dc = DesiredCapabilities.firefox();
				dc.setCapability(FirefoxDriver.PROFILE, profile);
				driver = new FirefoxDriver(dc);

				fnpMymsg("Browser type is Firefox");
			}

			String userName = CONFIG.getProperty("adminUsername");
			String password = CONFIG.getProperty("adminPassword");
			// String siteUrl = CONFIG.getProperty("insightTestsiteName");

			String siteUrl = null;

		/*	if (TestSuiteBase_IM.ClassName_IM_Bs18) {
				siteUrl = CONFIG.getProperty("insightTestsiteName");
				TestSuiteBase_IM.ClassName_IM_Bs18 = false;
				ScreenShotFlagWithOR_NsfOnline = false;
				TestSuiteBase_IM.ScreenShotFlagWithOR_IM = true;
			} else {
				if (excelSiteURL != null) {
					if (excelSiteURL.equalsIgnoreCase("")) {
						siteUrl = CONFIG.getProperty("insightTestsiteName");
					} else {
						siteUrl = excelSiteURL;

					}
				} else {
					siteUrl = CONFIG.getProperty("insightTestsiteName");
				}
			}
*/
			driver.get(siteUrl);
			driver.manage().window().maximize();
			Thread.sleep(1500);
			// driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("ElementWaitTime")),
			// TimeUnit.SECONDS);

		//	TestSuiteBase_MonitorPlan.fnsComman_BrowserLogin_ElementWait(OR_NsfOnline.getProperty("UserName"), CONFIG.getProperty("Element_MAX_WaitTime"));

			fnsGet_OR_NsfOnline_ObjectX("UserName").clear();
			fnsGet_OR_NsfOnline_ObjectX("UserName").sendKeys("");
			fnsGet_OR_NsfOnline_ObjectX("UserName").sendKeys(userName);
			fnsGet_OR_NsfOnline_ObjectX("password").sendKeys("");
			fnsGet_OR_NsfOnline_ObjectX("password").sendKeys(password);
			fnsGet_OR_NsfOnline_ObjectX("loginBtn").click();

			// Assert.assertEquals(false,
			// driver.findElement(By.xpath(OR_NsfOnline.getProperty("errorMessage"))).isDisplayed());

		//	TestSuiteBase_MonitorPlan.fnsComman_BrowserLogin_ElementWait(OR_NsfOnline.getProperty("logoutLink"), CONFIG.getProperty("Element_MAX_WaitTime"));
			WebElement logOutBtn = fnsGet_OR_NsfOnline_ObjectX("logoutLink");
			Assert.assertEquals(true, logOutBtn.isDisplayed());

			present = true;

		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			isTestPass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			isTestCasePass = false;
			isTestPass = false;
			fnpDesireScreenshot("LaunchBrowserAndLoginFail");
			present = false;
			// ErrorUtil.addVerificationFailure(t);
			// driver.quit();
			fnpMymsg("'Launch Browser And Login' is getting fail, Please refer screen shot [LaunchBrowserAndLoginFail" + fnsScreenShot_Date_format() + "]." + " Getting Exception >> " + t.getMessage());
			throw new Exception("'Launch Browser And Login' is getting fail, Please refer screen shot [LaunchBrowserAndLoginFail" + fnsScreenShot_Date_format() + "]." + " Getting Exception >> " + t.getMessage());
		}

		return present;
	}

	// Check for Browser Type defined in Suits Excel
	public static void BrowserCheck() {
		String Browser = null;

		for (int i = 2; i <= suiteXls.getRowCount("Test Suite"); i++) {

			if (suiteXls.getCellData("Test Suite", "TSID", i).equalsIgnoreCase("NSFOnline_Suite")) {
				BrowserName = suiteXls.getCellData("Test Suite", "Browser", i);

				if (BrowserName == "") {
					BrowserName = "IE";
				}

				break;
			}
		}
	}

	public static String fnsRemoveFormatting(String s) {

		s = s.replace("[", " ");
		s = s.replace("]", " ");
		s = s.replace("(", " ");
		s = s.replace(")", " ");
		s = s.trim();

		return s;
	}

	public static void TestCaseSkip(Integer counts, String customervalue) throws SkipException {
		try {
			// counts++;
			if (!(runmodes[counts].equalsIgnoreCase("Y"))) {
				fnpMymsg("****************************************************************************************************************************************");
				fnpMymsg("################################## Runmode of Case::" + (counts + 1) + " for Customer[" + customervalue + "]  is set to NO, So Skipping this Case.");
				// skip=true;
				System.out.println("if TCSkipReturnValue =" + TCSkipReturnValue + " count" + counts);
				throw new SkipException("Skipping Test Case " + " as runmode set to NO");
			}
		} catch (SkipException sk) {
			System.out.println("SkipException TCSkipReturnValue =" + TCSkipReturnValue + " count" + counts);
		}

	}

	//
	public static void MoveMouseCursorToTaskBar() {

		Actions testact = new Actions(driver);
		testact.moveByOffset(-1500, -1500).build().perform();
	}

	// fnpCheckNSFOnlineLoadingDisplayedImmediately
	/*********** To check element is displayed immediately or not
	 * 
	 * @throws Throwable *************/
	public static boolean fnpCheckDirectNSFOnlineLoadingDisplayedImmediately(String XpathKey) throws Throwable {

		int i = 0;
		try {
			fnpMymsg("@Pradeep---in fnpCheckDirectNSFOnlineLoadingDisplayedImmediately for--" + XpathKey);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);
			fnpMymsg("@Pradeep----Going to check whether style  contains 'Display block'  or NOT  ");
			// List<WebElement> elementList =
			// driver.findElements(By.xpath(OR.getProperty(XpathKey)));
			List<WebElement> elementList = fnpReturnFindElementsList_NOR(OR.getProperty(XpathKey));
			fnpMymsg("@Pradeep----" + XpathKey + "---Total element count for 'Display block' is ---" + elementList.size());
			for (Iterator iterator = elementList.iterator(); iterator.hasNext();) {

				WebElement webElement = (WebElement) iterator.next();
				// fnpMymsg("@Pradeep----"+XpathKey+"---'s attribute display is  ---"+webElement.getAttribute("style"));

				// if
				// (webElement.getAttribute("style").contains("DISPLAY: block"))
				// {
				if (webElement.getAttribute("style").toLowerCase().contains("display: block")) {
					fnpMymsg("@Pradeep----style contains 'Display block'   ");
					// return true;
					i = 1;
					break;
				} else {
					fnpMymsg("@Pradeep----style NOT contains 'Display block'   ");
					// return false;
				}

			}

		} catch (Throwable t) {
			// throw new Exception(t);

		}

		finally {
			driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);
		}

		if (i == 0) {
			return false;

		} else {
			return true;
		}

	}

	/***** Common final catch block for @Test ***/
	public static void fnpCommonFinalCatchBlock_DirectNSFOnline(Throwable t, String errCustomMsg, String screenshotName) throws Throwable {

		fail = true;
		isTestPass = false;
		String errMsg = "";
		if (fnpCheckElementDisplayedImmediately("NSFOnline_ErrorMsg")) {
			/* fnpMouseHover("ErrorMessage"); errMsg =
			 * fnpGetText_OR("ErrorMessage"); */
			fnpMouseHover_OR_EvenMultipleObjectsHavingSameXpath("NSFOnline_ErrorMsg");
			errMsg = fnpGetText_OR_EvenMultipleObjectsHavingSameXpath_specialForExtractingErrorMsgTextOnlyEvenIfNotVisible("NSFOnline_ErrorMsg");
		}

		fnpMymsg(errCustomMsg + " . Error is -->" + t.getMessage() + "'\n\n" + errMsg);
		fnpDesireScreenshot(screenshotName);
		String stackTrace = Throwables.getStackTraceAsString(t);
		String errorMsg = t.getMessage();
		errorMsg = errorMsg + " --->\n\n\n\n \"" + errMsg + "\" \n\n\n\n " + errCustomMsg + "   .See screenshot '" + screenshotName + "'\n\n" + stackTrace;
		Exception c = new Exception(errorMsg);
		ErrorUtil.addVerificationFailure(c);

		killprocess();
		// IsBrowserPresentAlready = false;

	}

	public static void fnpCheckDirectNSFOnlineErrorMsg() throws Throwable {
		// fnpCheckError_NSFONLINE("");
		fnpCheckErrorUsingPageSource_DirectNSFONLINE1();
		fnpCheckErrorUsingPageSource_DirectNSFONLINE2();
	}

	public static void fnpGetDirectNSFOnineVersion(String imageName) throws Throwable {

		String NSFOnlineoriginalHandle = driver.getWindowHandle();

		// Code to capture Screen shot of NSFOnline Application version
		try {
			if (NSFOnlineVersionScreenshot) {

				Thread.sleep(6000);

				fnpWaitForVisible("NsfOnline_Version");

				/**** work fine for img tag, but in some cases img tag not present
				 * but a tag present *****/
				// WebElement
				// wb=driver.findElement(By.xpath(".//img[@onclick='viewVersion()']"));
				
			

				
				
				
				
				WebElement wb = fnpGetORObjectX("NsfOnline_Version");
				Actions action = new Actions(driver);
				action.moveToElement(wb).keyUp(Keys.CONTROL).click().keyDown(Keys.CONTROL).build().perform();
				
				
				
				// Thread.sleep(5000);

				Thread.sleep(2000);

				for (int v = 0; v <= Long.parseLong(CONFIG.getProperty("ElementWaitTime")); v++) {

					ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
					int tabsCount = tabs.size();
					if (tabsCount == 2) {
						for (int i = 0; i < tabsCount; i++) {
							fnpMymsg("@@Pradeep---current window handle is---" + tabs.get(i));
							if (NSFOnlineoriginalHandle.equalsIgnoreCase(tabs.get(i))) {
								fnpMymsg("@@Pradeep---comes here b/c this is original window handle---");
								fnpMymsg("@@Pradeep-Inner If loop tabs.get(i)>> " + tabs.get(i));// nothing
																									// to
																									// do

							} else {
								// Thread.sleep(5000);
								
								String browser = browserName;

								//Thread.sleep(10000);
								if (browser.equalsIgnoreCase("chrome")) {
									WebElement wb2 = fnpGetORObjectX("NsfOnline_Version");
									Actions action2 = new Actions(driver);
									//action2.moveToElement(wb).sendKeys(Keys.CONTROL+ "\t");								
									new Actions(driver).sendKeys(Keys.chord(Keys.CONTROL, Keys.TAB)).perform();
									Thread.sleep(1000);
									driver.switchTo().window(tabs.get(i));
								} else {
									driver.switchTo().window(tabs.get(i));
								}
								
								
								//driver.switchTo().window(tabs.get(i));
								
								
								
								Thread.sleep(1000);
								fnpWaitForVisible("NsfOnline_Version_BuildInfo_Element");
								fnpMymsg("PASSED == Successfully switch to NSFOnline Version Window.");
								break;
							}
						} // for loop end
						break;
					} else {
						Thread.sleep(1000);
					}

					if (v == Long.parseLong(CONFIG.getProperty("ElementWaitTime"))) {
						throw new Exception(" NSF Online Version window is not getting open after 60 seconds wait");
					}
				}

				
				
				
				
				
				
				
				
				
				Thread.sleep(3000);
				fnpDesireScreenshot_NSFOnlineVersion(imageName);
				Thread.sleep(500);

				driver.close();
				Thread.sleep(2000);
				fnpMymsg("PASSED == Successfully Closed NSFOnlineVersion Window.");

				driver.switchTo().window(NSFOnlineoriginalHandle);
				fnpMymsg("PASSED == Successfully switch to NSF Online Window");

				NSFOnlineVersionScreenshot = false;

			}

		} catch (Throwable t) {
			fnpDesireScreenshot("NSFOnlineVersionScreenShotFail");
			fnpMymsg("Failed due to ---" + t.getMessage());
			fnpMymsg("FAILED ==  NSFOnline Version Window is not getting display, plz refer screenshot [NSFOnlineVersionScreenShotFail" + fnsScreenShot_Date_format() + "].  Getting Exception >> " + t.getMessage());
			driver.close();
			driver.switchTo().window(NSFOnlineoriginalHandle);
			throw new Throwable(t);
		}
	}

	/***** Select the value from a Simple list (for both Li and option tag) not
	 * prime faces list ***/
	public static void fnpSimpleSelectList_DirectNSFOnline(String ORXpath, String expvalue) throws Throwable {

		fnpWaitForVisible(ORXpath);
		fnpWaitTillVisiblityOfElementNClickable_OR(ORXpath);
		fnpMymsg(" Expected i.e. going to select value in  " + ORXpath + "list is ==> " + expvalue);
		expvalue = expvalue.trim();
		/* WebElement element99=fnpGetORObjectX(ORXpath); Select se=new
		 * Select(element99); se.selectByVisibleText(expvalue); */

		String tagname = null;
		boolean ValueMatched = false;
		fnpGetORObjectX(ORXpath).click();
		// Thread.sleep(2000);
		Thread.sleep(1000);

		// List<WebElement>
		// objectlist5=fnpGetORObjectX(ORXpath).findElements(By.tagName("li"));

		if (fnpCountChildElementPresenceImmediately_NotInOR(fnpGetORObjectX(ORXpath), "li") > 0) {
			List<WebElement> objectlist5 = fnpGetORObjectX(ORXpath).findElements(By.tagName("li"));
			tagname = "li";
			fnpMymsg(" li tag is present for this list. ");
			for (WebElement dd_value : objectlist5) {
				Actions act = new Actions(driver);
				act.moveToElement(dd_value).sendKeys(Keys.ARROW_DOWN).build().perform();
				if (dd_value.getText().trim().equals(expvalue)) {
					// Thread.sleep(1000);
					dd_value.click();
					// Thread.sleep(3000);
					Thread.sleep(1000);
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

				String selectedOption = new Select(fnpGetORObjectX(ORXpath)).getFirstSelectedOption().getText();
				String xpathOfFirstElement = OR.getProperty(ORXpath) + "/option[1]";
				String firstvalueinSelectList = driver.findElement(By.xpath(xpathOfFirstElement)).getText();
				if (!(selectedOption.equalsIgnoreCase(firstvalueinSelectList))) {
					driver.findElement(By.xpath(xpathOfFirstElement)).click();
					Thread.sleep(1000);
					fnpGetORObjectX(ORXpath).sendKeys(Keys.ENTER);
					Thread.sleep(1000);
					fnpGetORObjectX(ORXpath).click();
					Thread.sleep(1000);
				}
				// WebElement
				// firstElement=driver.findElement(By.xpath(xpathOfFirstElement));
				// Actions act = new Actions(driver);
				// act.moveToElement(objectlistOptions.get(0)).build().perform();
				// act.moveToElement(firstElement).build().perform();
				// fnpMouseHover_NotInOR(xpathOfFirstElement);
				// Thread.sleep(1000);

				objectlistOptions = fnpGetORObjectX(ORXpath).findElements(By.tagName("option"));
				fnpMymsg(" option tag is present for this list. ");
				tagname = "options";

				int forloopcounter = -1;
				for (WebElement dd_value : objectlistOptions) {
					forloopcounter++;
					Actions act = new Actions(driver);
					act.moveToElement(dd_value).sendKeys(Keys.ARROW_DOWN).build().perform();
					// fnpMymsg("@@@Pradeep current value is ---"+dd_value.getText());
					if (dd_value.getText().trim().equals(expvalue)) {
						// Thread.sleep(1000);
						act.moveToElement(objectlistOptions.get(forloopcounter + 1)).sendKeys(Keys.ARROW_UP).build().perform();
						act.moveToElement(dd_value).build().perform();
						Thread.sleep(1000);
						dd_value.click();

						// Thread.sleep(3000);
						Thread.sleep(1000);
						ValueMatched = true;
						break;
					} else {
						// Thread.sleep(500);
						Thread.sleep(100);
						// fnpMymsg("@@@Pradeep current value  ---'"+dd_value.getText()
						// +"'   is not matched to expected" +
						// "value i.e. --'"+expvalue+"'.");
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
			Assert.assertEquals(selectedListValue, expvalue, "Value inserted in " + ORXpath + " is not correct. Inserted value is --> " + selectedListValue);
			fnpMymsg(" Correct value of " + ORXpath + "  inserted in list successfully");

		}

		if (tagname.equalsIgnoreCase("option")) {
			Thread.sleep(1000);
			WebElement element99 = fnpGetORObjectX(ORXpath);
			Select se = new Select(element99);
			/* se.selectByVisibleText(expvalue); Thread.sleep(20000);
			 * Thread.sleep(2000); */
			String selectedListValue = se.getFirstSelectedOption().getText();

			fnpMymsg(" Selected value is ==> " + selectedListValue);
			Assert.assertEquals(selectedListValue, expvalue, "Value inserted in " + ORXpath + " is not correct. Inserted value is --> " + selectedListValue);
			fnpMymsg(" Correct value has been  inserted in list successfully");

		}

	}

	/**** Wait till Element gets visible and in clickable state which is not in OR ****/
	public static void fnpWaitTillVisiblityOfElementNClickable_OR_DirectNSFOnline(String XpathKey) throws Throwable {

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Long.parseLong(CONFIG.getProperty("genMax_waitTime")), TimeUnit.SECONDS).pollingEvery(100, TimeUnit.MILLISECONDS)
		// .ignoring(NoSuchElementException.class);
		.ignoring(NoSuchElementException.class).ignoring(org.openqa.selenium.InvalidSelectorException.class).ignoring(org.openqa.selenium.WebDriverException.class).ignoring(StaleElementReferenceException.class);

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(XpathKey))));

		/* WebDriverWait wait = new WebDriverWait(driver,
		 * Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))); int retries
		 * = 0; while (true) { try {
		 * wait.until(ExpectedConditions.elementToBeClickable
		 * (By.xpath(OR.getProperty(XpathKey)))); return; } catch
		 * (org.openqa.selenium.StaleElementReferenceException e) { if (retries
		 * < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
		 * retries++; fnpMymsg(retries +
		 * "In staleElementException catch block of fnpWaitTillVisiblityOfElementNClickable_OR function for "
		 * + XpathKey); continue; } else { throw e; } }
		 * 
		 * catch (Throwable t) { if (retries < 2) { retries++; fnpMymsg(retries
		 * +
		 * "In try catch block of fnpWaitTillVisiblityOfElementNClickable_OR function for "
		 * + XpathKey); continue; } else { throw t; }
		 * 
		 * }
		 * 
		 * } */

	}

	public static boolean fnpCheckRecordsPresentInTableInNSFOnline(String tableBodyXpath) throws Throwable {

		boolean found = true;
		// fnpMouseHover_NotInOR(tableBodyXpath);
		fnpMouseHover_NotInOR(tableBodyXpath + "/tr[1]");
		String text = fnpGetText_NOR(tableBodyXpath).trim();

		// covered--"no items were found" ----in Direct_NSFO_SCA_IM_Validations
		// covered --"no records to display" ----in Direct_NSFOnline_Search_Docs

		if (text.contains("No Records Found")) {
			found = false;
			fnpMymsg("@Pradeep--No Record Present as text found 'No Records Found'");
		}

		if (text.toLowerCase().contains("No Records Found".toLowerCase())) {
			found = false;
			fnpMymsg("@Pradeep--No Record Present as text found 'No Records Found'");
		}

		if (text.toLowerCase().contains("no records to display".toLowerCase())) {
			found = false;
			fnpMymsg("@Pradeep--No Record Present as text found 'no records to display'");
		}

		if (text.contains("Found")) {
			found = false;
			fnpMymsg("@Pradeep--No Record Present as text found 'Found'");
		}
		if (text.contains("found")) {
			fnpMymsg("@Pradeep--No Record Present as text found 'found'");
			found = false;
		}

		
		
		// this is special case for BS-03's 2nd case where data row is 2nd but it is coming blank
		if (found) {
		String row2xpath=tableBodyXpath + "/tr[2]".trim();
	//	text = fnpGetText_NOR(row2xpath).trim();
		text = driver.findElement(By.xpath(row2xpath)).getText().trim();
		if (text.length()<40) {
			fnpMymsg("@Pradeep--No Record Present as data 's lenght in row 2 is less than 40");
			found = false;
		}
		}
		
		
		return found;

	}

	/**** It is using xpath not CSS Selector ******/
	/**** To find sequence number of a column having a name
	 * 
	 * @throws Throwable ****/
	public static int fnpFindColumnIndex_InNSFOnline_NOR(String TableHeaderXpath, String columnName) throws Throwable {
		int colIndex = 0;
		String xpathHeader = TableHeaderXpath + "/th";
		// int HeaderCount = driver.findElements(By.xpath(xpathHeader)).size();
		int HeaderCount = fnpReturnFindElementsList_NOR(xpathHeader).size();

		for (int i = 1; i < HeaderCount + 1; i++) {

			String xpathExpression = TableHeaderXpath + "/th[" + i + "]";
			String headerValue = driver.findElement(By.xpath(xpathExpression)).getText().trim();
			if (columnName.equalsIgnoreCase(headerValue)) {
				colIndex = i;
				break;

			}

		}
		return colIndex;

	}

	
	
	
	public static void fnpNsfOnline_Open_Link_Clik_Through_JS(String customerNumber) throws Exception{

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		int whileLoopCount = 0;
		while (true) {
			whileLoopCount++;
			try {

				fnpMymsg("@Pradeep---in try block for clicking customer--" + customerNumber + " for chance --" + whileLoopCount);
				jse = (JavascriptExecutor) driver;
				WebElement openLink = driver.findElement(By.xpath(".//tr/td[text()='" + customerNumber + "']/../td[1]/a"));
				jse.executeScript("arguments[0].click();", openLink);
				fnpMymsg("@Pradeep---clicked successfully on 'OPEN' link of customer  --" + customerNumber);
				break;
			} catch (Throwable t) {
				fnpMymsg("@Pradeep---in catch block for clicking customer--" + customerNumber + " for chance --" + whileLoopCount);
				// if (whileLoopCount
				// >Integer.parseInt(CONFIG.getProperty("genMax_waitTime")))
				// {
				if (whileLoopCount > 5) {
					throw new Exception(t);
					// break;
				}
				Thread.sleep(1000);
			}

		}

		Thread.sleep(10000);
	
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*

*//*********** Launching the Browser *************/
	/* public static void fnpLaunchBrowser() throws Throwable {
	 * 
	 * startExecutionTime = fnpTimestamp(); //
	 * fnpFirstTimeInitializationMethod();
	 * 
	 * fnpMymsg("Checking Browser type");
	 * 
	 * String browser = browserName;
	 * 
	 * if (browser.equalsIgnoreCase("IE")) {
	 * 
	 * System.setProperty("webdriver.ie.driver",
	 * CONFIG.getProperty("IEDriverPathForDirectNSFOnline"));
	 * 
	 * DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
	 * caps.setCapability
	 * (InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
	 * true);
	 * 
	 * // caps.setCapability("IGNORE_ZOOM_SETTING", true);
	 * caps.setCapability("IE_ENSURE_CLEAN_SESSION", true);
	 * 
	 * caps.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
	 * 
	 * driver = new InternetExplorerDriver(caps);
	 * 
	 * driver.manage().window().maximize();
	 * driver.manage().timeouts().pageLoadTimeout
	 * (Long.parseLong(CONFIG.getProperty("pageLoadTime")), TimeUnit.SECONDS);
	 * driver
	 * .manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty
	 * ("implicitWaitTime")), TimeUnit.SECONDS);
	 * 
	 * fnpMymsg("Browser type is IE"); }
	 * 
	 * if (browser.equalsIgnoreCase("firefox")) {
	 * 
	 * 
	 * DesiredCapabilities caps = DesiredCapabilities.firefox();
	 * caps.setCapability("DEFAULT_ENABLE_NATIVE_EVENTS", true);
	 * caps.setCapability("REQUIRE_WINDOW_FOCUS","true"); driver = new
	 * FirefoxDriver(caps);
	 * 
	 * 
	 * driver = new FirefoxDriver(); driver.manage().window().maximize();
	 * driver.
	 * manage().timeouts().pageLoadTimeout(Long.parseLong(CONFIG.getProperty
	 * ("pageLoadTime")), TimeUnit.SECONDS);
	 * driver.manage().timeouts().implicitlyWait
	 * (Long.parseLong(CONFIG.getProperty("implicitWaitTime")),
	 * TimeUnit.SECONDS); fnpMymsg("Browser type is firefox"); }
	 * 
	 * if (browser.equalsIgnoreCase("chrome")) {
	 * 
	 * System.setProperty("webdriver.chrome.driver",
	 * CONFIG.getProperty("ChromeDriverPath"));
	 * 
	 * DesiredCapabilities caps = DesiredCapabilities.chrome(); ChromeOptions
	 * options = new ChromeOptions();
	 * 
	 * options.addArguments("--start-maximized");
	 * options.addArguments("--enable-text-input-focus-manager");
	 * 
	 * caps.setCapability("IGNORE_CERTIFICATE_ERRORS", true);
	 * caps.setCapability(ChromeOptions.CAPABILITY, options);
	 * 
	 * caps.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION
	 * , true);
	 * 
	 * caps.setCapability(ChromeOptions.CAPABILITY, options);
	 * 
	 * //driver = new ChromeDriver(); driver=new ChromeDriver(caps); //
	 * driver.manage().window().maximize();
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * driver.manage().timeouts().pageLoadTimeout(Long.parseLong(CONFIG.getProperty
	 * ("pageLoadTime")), TimeUnit.SECONDS);
	 * driver.manage().timeouts().implicitlyWait
	 * (Long.parseLong(CONFIG.getProperty("implicitWaitTime")),
	 * TimeUnit.SECONDS); fnpMymsg("Browser type is chrome"); } } */

}