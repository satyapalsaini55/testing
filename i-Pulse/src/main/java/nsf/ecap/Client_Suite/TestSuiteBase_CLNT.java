package nsf.ecap.Client_Suite;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;

import nsf.ecap.base.TestBase;
import java.sql.Connection;
import java.sql.Statement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import java.util.Date;
import java.util.HashMap;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;
import com.google.common.base.Throwables;
import nsf.ecap.util.TestUtil;
import nsf.ecap.util.Xls_Reader;

public class TestSuiteBase_CLNT extends TestBase {
	public static DesiredCapabilities caps;
	public static ChromeOptions options;
	public static ArrayList<String> Loading_Xpath_Array = new ArrayList<String>();
	public static String Loading_Image_Xpath = null;
	public static boolean LoadingImageFlag = true;
	public static String ErrorMsgText = null;
	public static Integer Actual_Loading_Time;
	public static String PageSourceText = null;
	public static String Connection_Name = null;
	public static String validation_error_msg_text;
	static boolean fail = false;
	public static String SearchResult_Facility_link_Xpath;
	public static String BrowserName = null;
	public static boolean ScreenShotFlagWithOR_CLNT = false;
	public static Integer tries = null;
	public static boolean ApplicationVersion_Flag = true;
	public static String runmodes[] = null;
	public static String Autoit_scriptExeFile_Path = "";

	// ######################################################### Common Functions
	// #######################################################################

	/*
	 * //Function to Take Screen Shot.
	 * public static void fnsTake_Screen_Shot(String message) throws Exception {
	 * String MessageAfterFormat=fnsRemoveFormatting_for_FileName(message);
	 * try{
	 * FileUtils.forceMkdir(new File((System.getProperty("user.dir") +
	 * screenshots_path+ "//screenshots_Client//"+currentScriptCode +"//")));
	 * Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	 * Rectangle screenRectangle = new Rectangle(screenSize);
	 * Robot robot = new Robot();
	 * BufferedImage image = robot.createScreenCapture(screenRectangle);
	 * //ImageIO.write(image, "png", new File((System.getProperty("user.dir") +
	 * CONFIG.getProperty("screenshots_path")+"//Client//"+message+"_"+
	 * fnsScreenShot_Date_format()+".PNG")));
	 * ImageIO.write(image, "png", new File((System.getProperty("user.dir") +
	 * screenshots_path+ "//screenshots_Client//"+currentScriptCode
	 * +"//"+MessageAfterFormat+"_"+fnsScreenShot_Date_format()+".PNG")));
	 * }catch(java.lang.NullPointerException n){
	 * fnsApps_Report_Logs("ScreenShotNullPointerException >> "+n.getMessage());
	 * throw new Exception("NullPointerException Unable To take Screen Shots.");}
	 * catch(java.io.IOException e){
	 * fnsApps_Report_Logs("ScreenShotIOException >> "+Throwables.
	 * getStackTraceAsString(e));
	 * throw new Exception("IOException Unable To take Screen Shots.");}
	 * }
	 */

	public static String fnpRemoveInvalidCharactersFromSavingFileName(String fileName) {

		fileName = fileName.replace(".", " ");
		fileName = fileName.replace("/", " ");
		fileName = fileName.replace("//", " ");
		fileName = fileName.replace("*", " ");
		fileName = fileName.replace(",", " ");

		return fileName;
	}

	public static void fnpDesireScreenshot(String ImageName) {

		try {
			SShots = SShots + 1;
			ImageName = fnpRemoveInvalidCharactersFromSavingFileName(ImageName);
			fnsApps_Report_Logs("error screenshot name is---" + ImageName);
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

	// Function to Take Screen Shot.
	public static void fnsTake_Screen_Shot(String message) throws Exception {
		String MessageAfterFormat = fnsRemoveFormatting_for_FileName(message);
		try {
			String Suite_Foler_Name = "screenshots_Client";
			String File_Name = MessageAfterFormat + "_" + fnsScreenShot_Date_format() + ".PNG";
			FileUtils.forceMkdir(new File((System.getProperty("user.dir") + screenshots_path + "//" + Suite_Foler_Name
					+ "//" + currentScriptCode + "//")));
			File image = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(image, new File((System.getProperty("user.dir") + screenshots_path + "//"
					+ Suite_Foler_Name + "//" + currentScriptCode + "//" + File_Name)));
		} catch (java.lang.NullPointerException n) {
			fnsApps_Report_Logs("ScreenShotNullPointerException >> " + n.getMessage());
			throw new Exception("NullPointerException Unable To take Screen Shots.");
		} catch (java.io.IOException e) {
			fnsApps_Report_Logs("ScreenShotIOException >> " + Throwables.getStackTraceAsString(e));
			throw new Exception("IOException Unable To take Screen Shots.");
		}
	}

	// Function to wait for element and then type
	public static void fnGet_Element_Visible_and_Enabled(String XpathKey) throws Exception {
		tries = 1;

		try {
			WebDriverWait elementwaitvar2 = new WebDriverWait(driver,
					Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
			elementwaitvar2
					.until(ExpectedConditions.refreshed(
							ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_CLNT.getProperty(XpathKey)))))
					.isEnabled();

		} catch (Throwable e) {
			while (tries < 5) {
				fnGet_Element_Visible_and_Enabled(XpathKey);
				tries++;
				System.out.println("tries  " + tries);
			}
			isTestCasePass = false;
			fnsTake_Screen_Shot("ElementNotVisible" + XpathKey);
			fnsApps_Report_Logs("FAILED == Element3 is not Visible having Xpath  >> " + XpathKey
					+ ", plz see screenshot [ " + "ElementNotVisible" + XpathKey + fnsScreenShot_Date_format()
					+ " ]. Getting Exception >> " + Throwables.getStackTraceAsString(e));
			throw new Exception("FAILED == Element3 is not Visible having Xpath  [ " + XpathKey
					+ " ] , plz see screenshot [ " + "ElementNotVisible" + XpathKey + fnsScreenShot_Date_format()
					+ " ]. Getting Exception >> " + Throwables.getStackTraceAsString(e));
		}

	}

	// Function for Application Log Date format
	public static String fnsLOGS_Date_format() {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date date = new Date();
		return (dateFormat.format(date));
	}

	// Function for Client Creation Text field Date Format
	public static String fnsClientCreationText_Date_format() {
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
	public static String fnsEditClient_Date_format() {
		DateFormat dateFormat = new SimpleDateFormat("MMddyyyy");
		Date date = new Date();
		return (dateFormat.format(date));
	}

	// Function For Application Log
	public static void fnsApps_Report_Logs(String LogMessage) {

		APP_LOGS.debug(LogMessage);
		System.out.println(LogMessage);
		Reporter.log(fnsLOGS_Date_format() + "  " + LogMessage);
	}

	public static String fnCreateClient_Date_format() {
		DateFormat dateFormat = new SimpleDateFormat("ddMMMyy/HH:mm z");
		Date date = new Date();
		return (dateFormat.format(date));
	}

	// Function to find and return the object using Xpath
	public static WebElement fnsGet_OR_CLNT_ObjectX(String XpathKey) throws Exception {

		try {
			// driver.manage().timeouts().implicitlyWait((Long.parseLong(CONFIG.getProperty("ElementWaitTime"))),
			// TimeUnit.SECONDS);
			for (int waits = 0; waits < Long.parseLong(CONFIG.getProperty("ElementWaitTime")); waits++) {

				if (driver.findElements(By.xpath(OR_CLNT.getProperty(XpathKey))).size() > 0) {
					break;
				} else {
					Thread.sleep(500);
				}

			}
			return driver.findElement(By.xpath(OR_CLNT.getProperty(XpathKey)));

		} catch (StaleElementReferenceException e) {
			WebDriverWait stalewaitvar = new WebDriverWait(driver, 10);
			stalewaitvar.until(ExpectedConditions.presenceOfElementLocated(By.xpath(OR_CLNT.getProperty(XpathKey))));
			WebElement webelemnt = driver.findElement(By.xpath(OR_CLNT.getProperty(XpathKey)));
			stalewaitvar.until(ExpectedConditions.stalenessOf(webelemnt));
			return driver.findElement(By.xpath(OR_CLNT.getProperty(XpathKey)));
		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (NoSuchElementException e) {
			isTestCasePass = false;
			fnsTake_Screen_Shot("NoSuchElementException" + XpathKey);
			fnsApps_Report_Logs(
					"FAILED == Element is not found >> " + XpathKey + " ] , plz see screenshot [NoSuchElementException"
							+ XpathKey + fnsScreenShot_Date_format() + " ]");
			throw new Exception(
					"FAILED == Element is not found >> " + XpathKey + " ] , plz see screenshot [NoSuchElementException"
							+ XpathKey + fnsScreenShot_Date_format() + " ]");
		} catch (TimeoutException e) {
			isTestCasePass = false;
			fnsTake_Screen_Shot("TimeOut" + XpathKey);
			fnsApps_Report_Logs("FAILED == Element is not Present, getting TimeOut >> " + XpathKey
					+ " ] , plz see screenshot [TimeOut" + XpathKey + fnsScreenShot_Date_format() + " ]");
			throw new Exception("FAILED == Element is not Present, getting TimeOut >> " + XpathKey
					+ " ] , plz see screenshot [TimeOut" + XpathKey + fnsScreenShot_Date_format() + " ]");
		} catch (Throwable e) {
			isTestCasePass = false;
			fnsTake_Screen_Shot("NotPresent" + XpathKey);
			fnsApps_Report_Logs("FAILED == Element is not found >> " + XpathKey + " ] , plz see screenshot [NotPresent"
					+ XpathKey + fnsScreenShot_Date_format() + " ]" + ". Getting Exception  >> "
					+ Throwables.getStackTraceAsString(e));
			throw new Exception("FAILED == Element is not found >> " + XpathKey + " ] , plz see screenshot [NotPresent"
					+ XpathKey + fnsScreenShot_Date_format() + " ]");
		}
	}

	public static WebElement fnsGet_OR_CLNT_ObjectX_ForISR(String XpathKey) throws Exception {

		try {
			// driver.manage().timeouts().implicitlyWait((Long.parseLong(CONFIG.getProperty("ElementWaitTime"))),
			// TimeUnit.SECONDS);
			for (int waits = 0; waits < Long.parseLong(CONFIG.getProperty("ElementWaitTime")); waits++) {

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
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (NoSuchElementException e) {
			isTestCasePass = false;
			fnsTake_Screen_Shot("NoSuchElementException" + XpathKey);
			fnsApps_Report_Logs(
					"FAILED == Element is not found >> " + XpathKey + " ] , plz see screenshot [NoSuchElementException"
							+ XpathKey + fnsScreenShot_Date_format() + " ]");
			throw new Exception(
					"FAILED == Element is not found >> " + XpathKey + " ] , plz see screenshot [NoSuchElementException"
							+ XpathKey + fnsScreenShot_Date_format() + " ]");
		} catch (TimeoutException e) {
			isTestCasePass = false;
			fnsTake_Screen_Shot("TimeOut" + XpathKey);
			fnsApps_Report_Logs("FAILED == Element is not Present, getting TimeOut >> " + XpathKey
					+ " ] , plz see screenshot [TimeOut" + XpathKey + fnsScreenShot_Date_format() + " ]");
			throw new Exception("FAILED == Element is not Present, getting TimeOut >> " + XpathKey
					+ " ] , plz see screenshot [TimeOut" + XpathKey + fnsScreenShot_Date_format() + " ]");
		} catch (Throwable e) {
			isTestCasePass = false;
			fnsTake_Screen_Shot("NotPresent" + XpathKey);
			fnsApps_Report_Logs("FAILED == Element is not found >> " + XpathKey + " ] , plz see screenshot [NotPresent"
					+ XpathKey + fnsScreenShot_Date_format() + " ]" + ". Getting Exception  >> "
					+ Throwables.getStackTraceAsString(e));
			throw new Exception("FAILED == Element is not found >> " + XpathKey + " ] , plz see screenshot [NotPresent"
					+ XpathKey + fnsScreenShot_Date_format() + " ]");
		}
	}

	// Function to click on Object
	public static void fnsWait_and_Click(String XpathKey) throws Exception {

		try {
			// fnsPage_Loading_wait();
			/*
			 * WebDriverWait waitvar = new WebDriverWait(driver,
			 * Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
			 * waitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_CLNT.
			 * getProperty(XpathKey))));
			 */
			fnsGet_OR_CLNT_ObjectX(XpathKey).click();
			fnsApps_Report_Logs("PASSED == Successfully Click on Element having Xpath >> " + XpathKey);
		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable e) {
			isTestCasePass = false;
			fnsTake_Screen_Shot("UnableToClick_" + XpathKey);
			fnsApps_Report_Logs("FAILED == Unable To Click on Element having Xpath >> " + XpathKey
					+ ", plz see screenshot [ UnableToClick_" + XpathKey + fnsScreenShot_Date_format()
					+ " ].Getting Exception >> " + Throwables.getStackTraceAsString(e));
			throw new Exception("Unable To Click on element [ " + XpathKey + " ] , plz see screenshot [ UnableToClick_"
					+ XpathKey + fnsScreenShot_Date_format() + " ].Getting Exception >> "
					+ Throwables.getStackTraceAsString(e));
		}
	}

	// Function to wait for element and then type
	public static void fnsWait_and_Type(String XpathKey, String value) throws Exception {

		try {
			// fnsPage_Loading_wait();
			/*
			 * WebDriverWait waitvar = new WebDriverWait(driver,
			 * Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
			 * waitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_CLNT.
			 * getProperty(XpathKey))));
			 */
			fnsGet_OR_CLNT_ObjectX(XpathKey).sendKeys("");
			fnsGet_OR_CLNT_ObjectX(XpathKey).sendKeys(value);
			fnsApps_Report_Logs(
					"PASSED == Successfully Type Value<" + value + "> on Element having Xpath  >> " + XpathKey);
		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable e) {
			isTestCasePass = false;
			fnsTake_Screen_Shot("UnableToType_" + XpathKey);
			fnsApps_Report_Logs("FAILED == Unable To Type on Element having Xpath  >> " + XpathKey
					+ ", plz see screenshot [ UnableToType_" + XpathKey + fnsScreenShot_Date_format()
					+ " ].Getting Exception >> " + Throwables.getStackTraceAsString(e));
			throw new Exception("Unable To Type on element [ " + XpathKey + " ] , plz see screenshot [ UnableToType_"
					+ XpathKey + fnsScreenShot_Date_format() + " ].Getting Exception >> "
					+ Throwables.getStackTraceAsString(e));
		}
	}

	// Function to wait for element and then type
	public static void fnsWait_and_Clear(String XpathKey) throws Exception {

		try {
			fnsGet_OR_CLNT_ObjectX(XpathKey).clear();
			fnsApps_Report_Logs("PASSED == Successfully Clear Field on Element having Xpath  >> " + XpathKey);
		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable e) {
			isTestCasePass = false;
			fnsTake_Screen_Shot("UnableToClear_" + XpathKey);
			fnsApps_Report_Logs("FAILED == Unable To Clear Field on Element having Xpath  >> " + XpathKey
					+ ", plz see screenshot [ UnableToClear_" + XpathKey + fnsScreenShot_Date_format()
					+ " ].Getting Exception >> " + Throwables.getStackTraceAsString(e));
			throw new Exception("Unable To Clear Field on element [ " + XpathKey
					+ " ] , plz see screenshot [ UnableToClear_" + XpathKey + fnsScreenShot_Date_format()
					+ " ].Getting Exception >> " + Throwables.getStackTraceAsString(e));
		}
	}

	// Function to wait for element
	public static void fnsGet_Element_Enabled(String XpathKey) throws Exception {

		try {
			for (int wait = 0; wait < 3; wait++) {
				if (driver.findElements(By.xpath(OR_CLNT.getProperty(XpathKey))).size() > 0) {
					// fnsGet_OR_CLNT_ObjectX(XpathKey);
					WebDriverWait elementwaitvar = new WebDriverWait(driver,
							Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
					elementwaitvar.until(
							ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_CLNT.getProperty(XpathKey))));

					WebDriverWait elementwaitvar1 = new WebDriverWait(driver,
							Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
					elementwaitvar1.until(
							ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_CLNT.getProperty(XpathKey))))
							.isEnabled();

					WebDriverWait elementwaitvar2 = new WebDriverWait(driver,
							Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
					elementwaitvar2.until(
							ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_CLNT.getProperty(XpathKey))))
							.isDisplayed();

					fnsApps_Report_Logs("PASSED == Element is Visible having Xpath  >> " + XpathKey);
					break;
				} // if loop closed
				else {
					throw new Exception();
				} // else loop closed
			} // for loop Closed

		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (TimeoutException to) {
			throw new Exception("TimeOut : " + Throwables.getStackTraceAsString(to));
		} catch (Throwable t) {
			try {
				Thread.sleep(3000);
				WebDriverWait elementwaitvar3 = new WebDriverWait(driver,
						Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
				elementwaitvar3
						.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_CLNT.getProperty(XpathKey))))
						.isEnabled();// }
				fnsApps_Report_Logs("PASSED == Element is Visible having Xpath  >> " + XpathKey);
			} catch (NoSuchWindowException W) {
				isTestCasePass = false;
				throw new Exception(W.getMessage());
			} catch (Throwable e) {
				isTestCasePass = false;
				fnsTake_Screen_Shot(XpathKey);
				fnsApps_Report_Logs("FAILED == Element is not Visible having Xpath  >> " + XpathKey
						+ ", plz see screenshot [ " + "" + XpathKey + fnsScreenShot_Date_format()
						+ " ]. Getting Exception >> " + Throwables.getStackTraceAsString(e));
				throw new Exception("FAILED == Element is not Visible having Xpath  [ " + XpathKey
						+ " ] , plz see screenshot [ " + "" + XpathKey + fnsScreenShot_Date_format()
						+ " ]. Getting Exception >> " + Throwables.getStackTraceAsString(e));
			}
		}
	}

	// Function to wait for element and then type
	public static void fnsGet_Element_Tobe_Clickable(String XpathKey) throws Exception {

		try {
			WebDriverWait elementwaitvar = new WebDriverWait(driver,
					Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
			elementwaitvar.until(ExpectedConditions.elementToBeClickable(By.xpath(OR_CLNT.getProperty(XpathKey))));
		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable e) {
			isTestCasePass = false;
			fnsTake_Screen_Shot("" + XpathKey);
			fnsApps_Report_Logs("FAILED == Clickable, Element is not Present having Xpath  >> " + XpathKey
					+ ", plz see screenshot [ " + "" + XpathKey + fnsScreenShot_Date_format() + " ]");
			throw new Exception("FAILED == Clickable, Element is not Present having Xpath  [ " + XpathKey
					+ " ] , plz see screenshot [ " + "" + XpathKey + fnsScreenShot_Date_format() + " ]");
		}
	}

	// Function to wait for element and then type
	public static void fnsMove_To_Element_and_Click(String XpathKey) throws Exception {

		try {
			Actions action = new Actions(driver);
			WebElement ClientTypeElement = fnsGet_OR_CLNT_ObjectX(XpathKey);
			action.moveToElement(ClientTypeElement).build().perform();
		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable e) {
			isTestCasePass = false;
			fnsTake_Screen_Shot("" + XpathKey);
			fnsApps_Report_Logs("FAILED == Unable to MoveToElement, having Xpath  >> " + XpathKey
					+ ", plz see screenshot [ " + "" + XpathKey + fnsScreenShot_Date_format() + " ]");
			throw new Exception("FAILED == Unable to MoveToElement, having Xpath  [ " + XpathKey
					+ " ] , plz see screenshot [ " + "" + XpathKey + fnsScreenShot_Date_format() + " ]");
		}
	}

	// Function to wait for element and then type
	public static void fnsMove_To_Element_and_DoubleClick_WithoutOR(String XpathKey) throws Exception {

		try {
			Actions action = new Actions(driver);
			WebElement ClientTypeElement = driver.findElement(By.xpath(XpathKey));
			action.moveToElement(ClientTypeElement).doubleClick().build().perform();
		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable e) {
			isTestCasePass = false;
			fnsTake_Screen_Shot("" + XpathKey);
			fnsApps_Report_Logs("FAILED == Unable to doubleClick on element, having Xpath  >> " + XpathKey
					+ ", plz see screenshot [ " + "" + XpathKey + fnsScreenShot_Date_format() + " ]");
			throw new Exception("FAILED == Unable to doubleClick on element, having Xpath  [ " + XpathKey
					+ " ] , plz see screenshot [ " + "" + XpathKey + fnsScreenShot_Date_format() + " ]");
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

	// Function to Return current system time
	public static void fnsPage_load_Wait() throws Exception {

		try {
			for (int wait = 0; wait < Long.parseLong(CONFIG.getProperty("ElementWaitTime")); wait++) {
				System.out.println("Before Displayed" + fnsGet_Current_Time());
				if (driver.findElement(By.xpath(OR_CLNT.getProperty("LookupLoadingImg"))).isDisplayed()) {
					System.out.println("Inner Displayed" + fnsGet_Current_Time());

					Thread.sleep(1000);
				} else {
					break;
				}

				System.out.println("Displayed+" + wait);
			}
			System.out.println("Outer Displayed" + fnsGet_Current_Time());
		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot("PageLoadFail");
			fnsApps_Report_Logs("FAILED == Page is still loading, Plase refer screenshot [" + "PageLoadFail"
					+ fnsScreenShot_Date_format() + " ]." + ". Getting Exception  >> " + t.getMessage());
			throw new Exception("FAILED == Page is still loading, Plase refer screenshot [" + "PageLoadFail"
					+ fnsScreenShot_Date_format() + " ]." + ". Getting Exception  >> " + t.getMessage());
		}
	}

	// Function to delete links in Correspondence TAB in Edit Client.
	public static void fnsDeleteQuery_for_CorrespondenceTAB_in_EditClient() throws Throwable {

		Connection connection = null;
		String DeleteQuery = "";
		String userName = "";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = fnpGetDBConnection();
			Statement stmt = connection.createStatement();

			if (env.toLowerCase().equalsIgnoreCase("test")) {
				userName = CONFIG.getProperty("Client_LoginAs");
			} else if (env.toLowerCase().equalsIgnoreCase("Staging")) {
				userName = CONFIG.getProperty("Client_LoginAs");// adminUsername
			}
			DeleteQuery = "delete FROM EC_CORRES_HISTORY WHERE OBJECT_TYPE = 'CUSTOMERS' and object_ref ='17788' and Upper(modify_user) = upper('"
					+ userName + "') and Upper(reply_to) like upper('%AutomationTest@replyto.com%')";
			stmt.executeUpdate(DeleteQuery);
			connection.commit();
			fnsApps_Report_Logs("**** Delete Query Executed Successfully for (Correspondence TAB in Edit Client).");

			connection.close();

		} catch (Throwable e) {
			fnsApps_Report_Logs("Getting Database Error >>  " + Throwables.getStackTraceAsString(e).trim());
			throw new Exception("Getting Database Error  >>  " + Throwables.getStackTraceAsString(e).trim());
		} finally {
			if (!(connection == null)) {
				connection.close();
			}
		}
		fnsApps_Report_Logs(
				"=========================================================================================================================================");
	}

	// ##########################################################################
	// Application Functions
	// ########################################################################################
	public static String fnsGet_Field_TEXT(String XpathKey) throws Exception {
		try {
			WebDriverWait elementwaitvar = new WebDriverWait(driver,
					Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
			elementwaitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_CLNT.getProperty(XpathKey))))
					.isEnabled();
			Thread.sleep(500);
			String TextFetch = fnsGet_OR_CLNT_ObjectX(XpathKey).getText().trim();
			fnsApps_Report_Logs("PASSED == Successfully Fetch the Text[" + TextFetch + "] on Element having xpath [ "
					+ XpathKey + " ].");

			return TextFetch;
		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot("TextFetchFail" + XpathKey);
			fnsApps_Report_Logs("FAILED == Unable to Fetch Text on Element having xpath [ " + "TextFetchFail" + XpathKey
					+ fnsScreenShot_Date_format() + " ]." + ". Getting Exception  >> "
					+ Throwables.getStackTraceAsString(t));
			throw new Exception("FAILED == Unable to Fetch Text on Element having xpath [ " + "TextFetchFail" + XpathKey
					+ fnsScreenShot_Date_format() + " ]." + ". Getting Exception  >> "
					+ Throwables.getStackTraceAsString(t));
		}
	}

	public static String fnsGet_Field_TEXT_ForISR(String XpathKey) throws Exception {
		try {
			WebDriverWait elementwaitvar = new WebDriverWait(driver,
					Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
			elementwaitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XpathKey))).isEnabled();
			Thread.sleep(500);
			String TextFetch = fnsGet_OR_CLNT_ObjectX_ForISR(XpathKey).getText().trim();
			fnsApps_Report_Logs("PASSED == Successfully Fetch the Text[" + TextFetch + "] on Element having xpath [ "
					+ XpathKey + " ].");

			return TextFetch;
		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot("TextFetchFail" + XpathKey);
			fnsApps_Report_Logs("FAILED == Unable to Fetch Text on Element having xpath [ " + "TextFetchFail" + XpathKey
					+ fnsScreenShot_Date_format() + " ]." + ". Getting Exception  >> "
					+ Throwables.getStackTraceAsString(t));
			throw new Exception("FAILED == Unable to Fetch Text on Element having xpath [ " + "TextFetchFail" + XpathKey
					+ fnsScreenShot_Date_format() + " ]." + ". Getting Exception  >> "
					+ Throwables.getStackTraceAsString(t));
		}
	}

	// Clicking on Search Client Ajax link.
	public static void fnCreateClient_Ajax_Link_Click() throws Exception {
		try {
			// fnsLoading_Progressingwait(1);
			fnsGet_Element_Enabled("Menu_Ajax_Link");
			WebElement Menu_Element = fnsGet_OR_CLNT_ObjectX("Menu_Ajax_Link");

			// New line added to run script in chrome.
			WebElement VersionLogoImage = fnsGet_OR_CLNT_ObjectX("VersionLogoImage");
			VersionLogoImage.click();
			Thread.sleep(500);

			Actions action1 = new Actions(driver);
			action1.moveToElement(Menu_Element).build().perform();

			Thread.sleep(500);

			Actions action2 = new Actions(driver);
			fnsGet_Element_Enabled("CreateClient_Ajax_Link");
			WebElement CreateClient = fnsGet_OR_CLNT_ObjectX("CreateClient_Ajax_Link");
			action2.moveToElement(CreateClient).click().build().perform();
			fnsApps_Report_Logs("PASSED == Successfully Click on 'Create Client' Ajax Link.");
		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot("Create_Client_Ajax");
			fnsApps_Report_Logs(
					"FAILED == Clicking on Create Client Ajex Link Failed, plz see screenshot [ " + "Create_Client_Ajax"
							+ fnsScreenShot_Date_format() + "]" + ". Getting Exception  >> " + t.getMessage().trim());
			throw new Exception(
					"Clicking on Search Create Client Link Failed , plz see screenshot [ " + "Create_Client_Ajax"
							+ fnsScreenShot_Date_format() + "]" + ". Getting Exception  >> " + t.getMessage().trim());
		}
	}

	// Clicking on Search Client Ajax link.
	public static void fnSearchClient_Ajax_Link_Click() throws Exception {
		try {
			fnsLoading_Progressingwait(1);
			fnsGet_Element_Enabled("Menu_Ajax_Link");
			WebElement Menu_Element = fnsGet_OR_CLNT_ObjectX("Menu_Ajax_Link");

			// New line added to run script in chrome.
			WebElement VersionLogoImage = fnsGet_OR_CLNT_ObjectX("VersionLogoImage");
			VersionLogoImage.click();
			Thread.sleep(500);

			Actions action1 = new Actions(driver);
			action1.moveToElement(Menu_Element).build().perform();

			Thread.sleep(500);

			Actions action2 = new Actions(driver);
			fnsGet_Element_Enabled("SearchClient_Ajax_Link");
			WebElement SearchClient = fnsGet_OR_CLNT_ObjectX("SearchClient_Ajax_Link");
			action2.moveToElement(SearchClient).click().build().perform();
			fnsApps_Report_Logs("PASSED == Successfully Click on 'Search Client' Ajax Link.");
		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot("Search_Client_Ajax");
			fnsApps_Report_Logs(
					"FAILED == Clicking on Search Client Ajex Link Failed, plz see screenshot [ " + "Search_Client_Ajax"
							+ fnsScreenShot_Date_format() + "]" + ". Getting Exception  >> " + t.getMessage().trim());
			throw new Exception(
					"FAILED == Clicking on Search Client Link Failed , plz see screenshot [ " + "Search_Client_Ajax"
							+ fnsScreenShot_Date_format() + "]" + ". Getting Exception  >> " + t.getMessage().trim());
		}
	}

	// function to select drop down value
	public static void fnsDD_value_Select_By_MatchingText(String ddClickXpathKey, String ddListXpathKey, String TagName,
			String Value) throws Exception {

		boolean ValueNotMatched = true;

		try {
			fnsWait_and_Click(ddClickXpathKey);
			fnsGet_Element_Enabled(ddListXpathKey);
			List<WebElement> DDobjectlists = fnsGet_OR_CLNT_ObjectX(ddListXpathKey).findElements(By.tagName(TagName));
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

			fnsApps_Report_Logs("PASSED == Successfully select value [ " + Value
					+ " ] from drop down, having xpath >>  " + ddClickXpathKey);
		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot("DdValueSelectFail" + ddClickXpathKey);
			fnsApps_Report_Logs("FAILED == Unable to select value from drop down [ " + ddClickXpathKey
					+ fnsScreenShot_Date_format() + " ]" + ". Getting Exception  >> " + t.getMessage().trim());
			throw new Exception("Unable to select value from drop down [ " + ddClickXpathKey
					+ " ],plz see screenshot [ DdValueSelectFail" + ddClickXpathKey + fnsScreenShot_Date_format() + " ]"
					+ Throwables.getStackTraceAsString(t));
		}
	}

	// Verify Text fetched and matched with expected text.
	public static void fnsText_Fetch_and_Assert(String XpathKey, String MatchingText) throws Exception {

		String GetText = fnsGet_Field_TEXT(XpathKey).toLowerCase().trim();
		try {
			assert GetText.contains(MatchingText.toLowerCase()) : "FAILED == Text (" + MatchingText
					+ ") is not matched with the text fetched from element having xpath >>" + XpathKey;
			fnsApps_Report_Logs("PASSED ==  Text (" + MatchingText
					+ ") is matched with the text fetched from element having xpath >>" + XpathKey);

		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot(XpathKey);
			fnsApps_Report_Logs("FAILED == Text (" + MatchingText
					+ ") is not matched with the text fetched from element having xpath >>" + XpathKey
					+ "  ,Please refer ScreenShot [ " + XpathKey + fnsScreenShot_Date_format() + " ]."
					+ ". Getting Exception  >> " + t.getMessage());
			throw new Exception("FAILED == Text (" + MatchingText
					+ ") is not matched with the text fetched from element having xpath >>" + XpathKey
					+ "  ,Please refer ScreenShot [ " + XpathKey + fnsScreenShot_Date_format() + " ]."
					+ Throwables.getStackTraceAsString(t));
		}
	}

	// Count the no rows in lookup table
	public static int fnCount_table_Rows_BeforeTableRefresh(String LookuptableXpathKey) throws Exception {

		int row_count = 1;
		try {
			for (int i = 0; i < Long.parseLong(CONFIG.getProperty("ElementWaitTime")); i++) {
				Thread.sleep(1000);
				List<WebElement> table = driver.findElements(By.xpath(OR_CLNT.getProperty(LookuptableXpathKey)));

				int no_of_rows_count = table.size();
				if (no_of_rows_count > 2) {
					row_count = no_of_rows_count;
					break;
				}
			}
			return row_count;
		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot("CountRowFailFrom" + LookuptableXpathKey);
			fnsApps_Report_Logs("FAILED == Unable to count rows from  Table having xpath [ " + "CountRowFailFrom"
					+ LookuptableXpathKey + fnsScreenShot_Date_format() + " ]." + ". Getting Exception  >> "
					+ t.getMessage().trim());
			throw new Exception("Unable to count rows from  Table having xpath [" + LookuptableXpathKey
					+ "],plz see screenshot [CountRowFailFrom" + LookuptableXpathKey + fnsScreenShot_Date_format() + "]"
					+ Throwables.getStackTraceAsString(t));
		}
	}

	// Count the no rows in lookup table
	public static int fnCount_table_Rows_AfterTableRefresh(String LookuptableXpathKey) throws Exception {
		int row_count = 1;

		try {
			for (int i = 0; i < Long.parseLong(CONFIG.getProperty("ElementWaitTime")); i++) {
				Thread.sleep(1000);
				List<WebElement> table = driver.findElements(By.xpath(OR_CLNT.getProperty(LookuptableXpathKey)));
				int no_of_rows_count = table.size();
				if (no_of_rows_count == 1) {
					row_count = no_of_rows_count;
					break;
				}
			}
			return row_count;
		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot("CountRowFailFrom" + LookuptableXpathKey);
			fnsApps_Report_Logs("FAILED == Unable to count rows from  Table having xpath [ " + "CountRowFailFrom"
					+ LookuptableXpathKey + fnsScreenShot_Date_format() + " ]." + ". Getting Exception  >> "
					+ t.getMessage().trim());
			throw new Exception("Unable to count rows from  Table having xpath [" + LookuptableXpathKey
					+ "],plz see screenshot [CountRowFailFrom" + LookuptableXpathKey + fnsScreenShot_Date_format() + "]"
					+ Throwables.getStackTraceAsString(t));
		}
	}

	public static void fnsTableVerify_MatchingText_of_SearchCriteria_from_ResultTable(String TableHeaderXpath,
			String TableCellHeaderXpath, String ColumnValue1, String ColumnValue2, String ColumnValue3)
			throws Throwable {
		try {
			int i = 0;
			fnsLoading_Progressingwait(1);
			fnsGet_Element_Enabled(TableHeaderXpath);
			Integer FindColumnNo = 1;
			Integer Type_ColumnNo = 2;
			Integer OrgUnit_ColumnNo = 7;
			Integer Status_ColumnNo = 14;

			String HeaderTableXpath = "//*[@id='mainForm:stdSearchC_head']/tr[1]";
			WebElement HeaderTableAllObj = WithOut_OR_fnGet_ObjectX(HeaderTableXpath);
			List<WebElement> NoOfHeadinglist = HeaderTableAllObj.findElements(By.tagName("th"));
			for (WebElement HeaderTableEle : NoOfHeadinglist) {
				String ColumnHeadingText = HeaderTableEle.getText().toLowerCase().trim();
				if (ColumnHeadingText.contains("type")) {
					Type_ColumnNo = FindColumnNo;
				}
				if (ColumnHeadingText.contains("org unit")) {
					OrgUnit_ColumnNo = FindColumnNo;
				}
				if (ColumnHeadingText.contains("status")) {
					Status_ColumnNo = FindColumnNo;
				}
				FindColumnNo++;
			}
			String Pagination_Total_Records_Count_String = "";
			Integer Pagination_Next_button_Click_Count = 0;
			Integer Pagination_button_Click = 0;
			for (int l = 0; l < 5; l++) {
				Pagination_Total_Records_Count_String = fnsGet_OR_CLNT_ObjectX(
						"SearchClient_Pagination_Total_Records_Count").getText().trim();
				if (Pagination_Total_Records_Count_String.length() > 5) {
					break;
				} else {
					Thread.sleep(1000);
				}
			}
			if (Pagination_Total_Records_Count_String.contains("of")) {
				Pagination_Total_Records_Count_String = Pagination_Total_Records_Count_String.split("of")[1].trim();
				Integer Pagination_Total_Records_Count = Integer.parseInt(Pagination_Total_Records_Count_String);
				Pagination_Next_button_Click_Count = Pagination_Total_Records_Count / 25;
			}

			while (true) {
				WebElement stdtable = fnsGet_OR_CLNT_ObjectX(TableHeaderXpath);
				List<WebElement> no_of_rows_in_list = stdtable.findElements(By.tagName("tr"));

				for (WebElement countrows : no_of_rows_in_list) {

					if (i > 0) {
						String First_Row_Xpath = OR_CLNT.getProperty(TableCellHeaderXpath) + "/tr[" + (i) + "]";
						String First_Row_Text = driver.findElement(By.xpath(First_Row_Xpath)).getText().toLowerCase()
								.trim();
						if (First_Row_Text.contains("no records found")) {
							fnsApps_Report_Logs("There are no records found for applied filter.");
							break;
						}

						String XpathColumn1 = OR_CLNT.getProperty(TableCellHeaderXpath) + "/tr[" + (i) + "]" + "/td["
								+ Type_ColumnNo + "]";
						String XpathColumn2 = OR_CLNT.getProperty(TableCellHeaderXpath) + "/tr[" + (i) + "]" + "/td["
								+ OrgUnit_ColumnNo + "]";
						String XpathColumn3 = OR_CLNT.getProperty(TableCellHeaderXpath) + "/tr[" + (i) + "]" + "/td["
								+ Status_ColumnNo + "]";

						String Column1Text = driver.findElement(By.xpath(XpathColumn1)).getText().trim();
						String Column2Text = driver.findElement(By.xpath(XpathColumn2)).getText().trim();
						String Column3Text = driver.findElement(By.xpath(XpathColumn3)).getText().trim();

						// Thread.sleep(1000);//Ghanshyam
						// fnsMove_To_Element_and_DoubleClick_WithoutOR(XpathColumn1);
						assert (Column1Text.contains(ColumnValue1)) : "FAILED == Actual value<" + Column1Text
								+ "> is not Matched with Expected value<" + ColumnValue1 + "> in RowNo" + i
								+ " , Please refer screen shot [" + "ValueNotMatch" + fnsScreenShot_Date_format() + "]";
						// Thread.sleep(1000);//Ghanshyam
						// fnsMove_To_Element_and_DoubleClick_WithoutOR(XpathColumn2);
						assert (Column2Text.contains(ColumnValue2)) : "FAILED == Actual value<" + Column2Text
								+ "> is not Matched with Expected value<" + ColumnValue2 + "> in RowNo" + i
								+ " , Please refer screen shot [" + "ValueNotMatch" + fnsScreenShot_Date_format() + "]";
						// Thread.sleep(1000);//Ghanshyam
						// fnsMove_To_Element_and_DoubleClick_WithoutOR(XpathColumn3);
						assert (Column3Text.contains(ColumnValue3)) : "FAILED == Actual value<" + Column3Text
								+ "> is not Matched with Expected value<" + ColumnValue3 + "> in RowNo" + i
								+ " , Please refer screen shot [" + "ValueNotMatch" + fnsScreenShot_Date_format() + "]";
					}
					/*
					 * if(i>1){ // only for Testing
					 * break;
					 * }
					 */
					i++; // i value is 11 here
				}

				// if(i==26 && Pagination_button_Click<Pagination_Next_button_Click_Count){
				if (driver.findElements(By.xpath(OR_CLNT.getProperty("SearchClient_Pagination_Next"))).size() > 0) {
					fnsWait_and_Click("SearchClient_Pagination_Next");
					fnsLoading_Progressingwait(1);
					Thread.sleep(1000);
					Pagination_button_Click++;
				} else {
					break;
				}
				i = 0;
			}
		} catch (StaleElementReferenceException s) {
			fnsTableVerify_MatchingText_of_SearchCriteria_from_ResultTable(TableHeaderXpath, TableCellHeaderXpath,
					ColumnValue1, ColumnValue2, ColumnValue3);
		} catch (Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot("ValueNotMatch");
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}

	public static int fnsTableVerify_MatchingText_of_SearchCriteria_from_ResultTable_GetLastRowNumForISR(
			String TableHeaderXpath, String TableCellHeaderXpath, String ColumnValue1, String ColumnValue2,
			String ColumnValue3) throws Throwable {
		int lastRowNo = 0;
		try {
			int i = 0;
			fnsLoading_Progressingwait(1);
			fnsGet_Element_Enabled(TableHeaderXpath);
			Integer FindColumnNo = 1;
			Integer Type_ColumnNo = 2;
			Integer OrgUnit_ColumnNo = 7;
			Integer Status_ColumnNo = 14;

			String HeaderTableXpath = "//*[@id='mainForm:stdSearchC_head']/tr[1]";
			WebElement HeaderTableAllObj = WithOut_OR_fnGet_ObjectX(HeaderTableXpath);
			List<WebElement> NoOfHeadinglist = HeaderTableAllObj.findElements(By.tagName("th"));
			for (WebElement HeaderTableEle : NoOfHeadinglist) {
				String ColumnHeadingText = HeaderTableEle.getText().toLowerCase().trim();
				if (ColumnHeadingText.contains("type")) {
					Type_ColumnNo = FindColumnNo;
				}
				if (ColumnHeadingText.contains("org unit")) {
					OrgUnit_ColumnNo = FindColumnNo;
				}
				if (ColumnHeadingText.contains("status")) {
					Status_ColumnNo = FindColumnNo;
				}
				FindColumnNo++;
			}
			String Pagination_Total_Records_Count_String = "";
			Integer Pagination_Next_button_Click_Count = 0;
			Integer Pagination_button_Click = 0;
			for (int l = 0; l < 5; l++) {
				Pagination_Total_Records_Count_String = fnsGet_OR_CLNT_ObjectX(
						"SearchClient_Pagination_Total_Records_Count").getText().trim();
				if (Pagination_Total_Records_Count_String.length() > 5) {
					break;
				} else {
					Thread.sleep(1000);
				}
			}
			if (Pagination_Total_Records_Count_String.contains("of")) {
				Pagination_Total_Records_Count_String = Pagination_Total_Records_Count_String.split("of")[1].trim();
				Integer Pagination_Total_Records_Count = Integer.parseInt(Pagination_Total_Records_Count_String);
				Pagination_Next_button_Click_Count = Pagination_Total_Records_Count / 25;
			}

			while (true) {
				WebElement stdtable = fnsGet_OR_CLNT_ObjectX(TableHeaderXpath);
				List<WebElement> no_of_rows_in_list = stdtable.findElements(By.tagName("tr"));

				for (WebElement countrows : no_of_rows_in_list) {

					if (i > 0) {
						String First_Row_Xpath = OR_CLNT.getProperty(TableCellHeaderXpath) + "/tr[" + (i) + "]";
						String First_Row_Text = driver.findElement(By.xpath(First_Row_Xpath)).getText().toLowerCase()
								.trim();
						if (First_Row_Text.contains("no records found")) {
							fnsApps_Report_Logs("There are no records found for applied filter.");
							break;
						}

						String XpathColumn1 = OR_CLNT.getProperty(TableCellHeaderXpath) + "/tr[" + (i) + "]" + "/td["
								+ Type_ColumnNo + "]";
						String XpathColumn2 = OR_CLNT.getProperty(TableCellHeaderXpath) + "/tr[" + (i) + "]" + "/td["
								+ OrgUnit_ColumnNo + "]";
						String XpathColumn3 = OR_CLNT.getProperty(TableCellHeaderXpath) + "/tr[" + (i) + "]" + "/td["
								+ Status_ColumnNo + "]";

						String Column1Text = driver.findElement(By.xpath(XpathColumn1)).getText().trim();
						String Column2Text = driver.findElement(By.xpath(XpathColumn2)).getText().trim();
						String Column3Text = driver.findElement(By.xpath(XpathColumn3)).getText().trim();

						fnsMove_To_Element_and_DoubleClick_WithoutOR(XpathColumn1);
						assert (Column1Text.contains(ColumnValue1)) : "FAILED == Actual value<" + Column1Text
								+ "> is not Matched with Expected value<" + ColumnValue1 + "> in RowNo" + i
								+ " , Please refer screen shot [" + "ValueNotMatch" + fnsScreenShot_Date_format() + "]";

						fnsMove_To_Element_and_DoubleClick_WithoutOR(XpathColumn2);
						assert (Column2Text.contains(ColumnValue2)) : "FAILED == Actual value<" + Column2Text
								+ "> is not Matched with Expected value<" + ColumnValue2 + "> in RowNo" + i
								+ " , Please refer screen shot [" + "ValueNotMatch" + fnsScreenShot_Date_format() + "]";

						fnsMove_To_Element_and_DoubleClick_WithoutOR(XpathColumn3);
						assert (Column3Text.contains(ColumnValue3)) : "FAILED == Actual value<" + Column3Text
								+ "> is not Matched with Expected value<" + ColumnValue3 + "> in RowNo" + i
								+ " , Please refer screen shot [" + "ValueNotMatch" + fnsScreenShot_Date_format() + "]";
					}
					/*
					 * if(i>1){ // only for Testing
					 * break;
					 * }
					 */
					i++; // i value is 11 here
				}
				lastRowNo = i;
				// if(i==26 && Pagination_button_Click<Pagination_Next_button_Click_Count){
				if (driver.findElements(By.xpath(OR_CLNT.getProperty("SearchClient_Pagination_Next"))).size() > 0) {
					fnsWait_and_Click("SearchClient_Pagination_Next");
					fnsLoading_Progressingwait(1);
					Thread.sleep(1000);
					Pagination_button_Click++;
				} else {
					Thread.sleep(1000);
					break;
				}
				i = 0;
			}
		} catch (StaleElementReferenceException s) {
			fnsTableVerify_MatchingText_of_SearchCriteria_from_ResultTable(TableHeaderXpath, TableCellHeaderXpath,
					ColumnValue1, ColumnValue2, ColumnValue3);
		} catch (Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot("ValueNotMatch");
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		return lastRowNo - 1;
	}

	public static void fnsTableVerify_SingleColumnMatchingText_of_WebTableWithoutPagination(String TableHeaderXpath,
			String TableCellHeaderXpath, String ColumnTextValue1, String ColumnNumber) throws Throwable {

		int i = 0;

		fnsLoading_Progressingwait(1);
		fnsGet_Element_Enabled(TableHeaderXpath);

		WebElement stdtable = fnsGet_OR_CLNT_ObjectX(TableHeaderXpath);
		List<WebElement> no_of_rows_in_list = stdtable.findElements(By.tagName("tr"));

		for (WebElement countrows : no_of_rows_in_list) {
			if (i > 0) {
				String XpathRow = OR_CLNT.getProperty(TableCellHeaderXpath) + "/tr[" + (i) + "]";
				String RowText = driver.findElement(By.xpath(XpathRow)).getText().trim();

				if (RowText.contains(ColumnTextValue1)) {
					String XpathColumn1 = OR_CLNT.getProperty(TableCellHeaderXpath) + "/tr[" + (i) + "]" + "/td["
							+ ColumnNumber + "]";
					String Column1Text = driver.findElement(By.xpath(XpathColumn1)).getText().trim();

					try {
						assert (Column1Text.contains(ColumnTextValue1)) : "FAILED == Data(" + ColumnTextValue1
								+ ") are not  updated into Table, Please refer screen shot [" + ColumnTextValue1
								+ fnsScreenShot_Date_format() + " ]";
						fnsApps_Report_Logs(
								"PASSED == Data(" + ColumnTextValue1 + ") are successfully updated into Table");
						break;
					} catch (Throwable t) {
						isTestCasePass = false;
						fnsTake_Screen_Shot(ColumnTextValue1);
						fnsApps_Report_Logs("FAILED == Data(" + ColumnTextValue1
								+ ") are not  updated into Table, Please refer screen shot [" + ColumnTextValue1
								+ fnsScreenShot_Date_format() + " ]");
						throw new Exception("FAILED == Data(" + ColumnTextValue1
								+ ") are not  updated into Table, Please refer screen shot [" + ColumnTextValue1
								+ fnsScreenShot_Date_format() + " ]");
					}
				} // if loop closed

			} // outer if loop
			i++; // i value is 11 here
		} // for loop closed
	}

	public static void fnsTableClicking_on_Table_Bttn_like_EditDelete(String TableHeaderXpath,
			String TableCellHeaderXpath, String ColumnTextValue1, String Bttnxpaths1, String Bttnxpaths2)
			throws Throwable {

		try {
			int i = 0;

			fnsLoading_Progressingwait(1);
			fnsGet_Element_Enabled(TableHeaderXpath);

			WebElement stdtable = fnsGet_OR_CLNT_ObjectX(TableHeaderXpath);
			List<WebElement> no_of_rows_in_list = stdtable.findElements(By.tagName("tr"));

			if (stdtable.getText().contains(ColumnTextValue1)) {

				for (WebElement countrows : no_of_rows_in_list) {

					if (i > 0) {
						String XpathRow = OR_CLNT.getProperty(TableCellHeaderXpath) + "/tr[" + (i) + "]";
						String RowText = driver.findElement(By.xpath(XpathRow)).getText().trim();

						if (RowText.contains(ColumnTextValue1)) {
							// System.out.println("Before I value ================== "+i);
							i--;
							// System.out.println("I value ================== "+i);
							String ButtonXpath = OR_CLNT.getProperty(Bttnxpaths1) + (i)
									+ OR_CLNT.getProperty(Bttnxpaths2);
							// System.out.println("Bttn Xpaths >>>> "+ButtonXpath);
							driver.findElement(By.xpath(ButtonXpath)).click();

							break;
						} // if loop closed

					} // outer if loop
					i++; // i value is 11 here
				} // for loop closed
			} else {
				fnsApps_Report_Logs("FAILED == Records are not exists into Table.");
			}

		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot(Bttnxpaths1);
			fnsApps_Report_Logs("FAILED == Unable to click on Table Button, having xpaths >> " + Bttnxpaths1
					+ " , Please refer screen shot [" + Bttnxpaths1 + fnsScreenShot_Date_format() + " ]"
					+ Throwables.getStackTraceAsString(t));
			throw new Exception("FAILED == Unable to click on Table Button, having xpaths >> " + Bttnxpaths1
					+ " , Please refer screen shot [" + Bttnxpaths1 + fnsScreenShot_Date_format() + " ]"
					+ Throwables.getStackTraceAsString(t));
		}
	}

	// Click on Yes button by Making xpath from table TotalRowCount
	public static void fnsTableClicking_on_Delete_YES_Bttn(String TableHeaderXpath, String TableCellHeaderXpath,
			String Bttnxpaths1, String Bttnxpaths2) throws Throwable {

		try {
			int i = 0;
			fnsLoading_Progressingwait(1);
			fnsGet_Element_Enabled(TableHeaderXpath);

			WebElement stdtable = fnsGet_OR_CLNT_ObjectX(TableHeaderXpath);
			List<WebElement> no_of_rows_in_list = stdtable.findElements(By.tagName("tr"));

			for (WebElement countrows : no_of_rows_in_list) {

				i++;

			} // for loop closed
			i = i - 2;
			// System.out.println("I value ===== "+i);
			String ButtonXpath = OR_CLNT.getProperty(Bttnxpaths1) + (i) + OR_CLNT.getProperty(Bttnxpaths2);
			System.out.println("Yes Button Xpath  " + ButtonXpath);

			WebDriverWait elementwaitvar = new WebDriverWait(driver,
					Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
			elementwaitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ButtonXpath))).isEnabled();

			driver.findElement(By.xpath(ButtonXpath)).click();
			fnsApps_Report_Logs("PASSED == Successfully Click on Yes Button having xpaths >> " + ButtonXpath);
			fnsLoading_Progressingwait(1);

		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot(Bttnxpaths1);
			fnsApps_Report_Logs("FAILED == Unable to click on YES Button, having xpaths >> " + Bttnxpaths1
					+ " , Please refer screen shot [" + Bttnxpaths1 + fnsScreenShot_Date_format() + " ]"
					+ Throwables.getStackTraceAsString(t));
			throw new Exception("FAILED == Unable to click on YES Button, having xpaths >> " + Bttnxpaths1
					+ " , Please refer screen shot [" + Bttnxpaths1 + fnsScreenShot_Date_format() + " ]"
					+ Throwables.getStackTraceAsString(t));
		}
	}

	// Return Row Number of matching TEXT in Table Cell
	public static Integer fnsTableFetch_RowNumber_For_Matching_TEXT(String TableHeaderXpath,
			String TableCellHeaderXpath, String MatchingText) throws Throwable {

		try {
			int RowNumber = 0;
			fnsLoading_Progressingwait(1);
			fnsGet_Element_Enabled(TableHeaderXpath);

			WebElement stdtable = fnsGet_OR_CLNT_ObjectX(TableHeaderXpath);
			List<WebElement> no_of_rows_in_list = stdtable.findElements(By.tagName("tr"));

			for (WebElement countrows : no_of_rows_in_list) {
				if (RowNumber > 0) {
					String XpathRow = OR_CLNT.getProperty(TableCellHeaderXpath) + "/tr[" + (RowNumber) + "]";
					String RowText = driver.findElement(By.xpath(XpathRow)).getText().trim();

					if (RowText.contains(MatchingText)) {
						break;
					} // if loop closed
				} // outer if loop
				RowNumber++; // i value is 11 here
			} // for loop closed
			System.out.println("Fetching I Value ==== " + RowNumber);
			return RowNumber;
		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot(MatchingText);
			fnsApps_Report_Logs("FAILED == Fetching RowNumber is getting failed for table having xpaths >> "
					+ TableHeaderXpath + ", Please refer screen shot [" + MatchingText + fnsScreenShot_Date_format()
					+ " ]" + Throwables.getStackTraceAsString(t));
			throw new Exception("FAILED == Fetching RowNumber is getting failed for table having xpaths >> "
					+ TableHeaderXpath + ", Please refer screen shot [" + MatchingText + fnsScreenShot_Date_format()
					+ " ]" + Throwables.getStackTraceAsString(t));
		}

	}

	/*
	 * //Click on Link by matchingText Xpath
	 * public static void fnsTable_ClickOn_LINK_ByMatchingText(String
	 * LinkMatchingText) throws Throwable{
	 * 
	 * try{
	 * SearchResult_Code_link_Xpath = ".//*[contains(text(),'" + LinkMatchingText +
	 * "')]"; // 'a' remove from xpath
	 * WebDriverWait elementwaitvar1 = new WebDriverWait(driver,
	 * Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
	 * elementwaitvar1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
	 * SearchResult_Code_link_Xpath))).isEnabled();
	 * driver.findElement(By.xpath(SearchResult_Code_link_Xpath)).click();
	 * fnsApps_Report_Logs("PASSED == Successfully click on the Link having xpath >> "
	 * +SearchResult_Code_link_Xpath);
	 * TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
	 * }catch(NoSuchWindowException W){
	 * isTestCasePass = false;
	 * throw new Exception(W.getMessage()); }
	 * catch(Throwable t){
	 * isTestCasePass = false;
	 * fnsTake_Screen_Shot("LinkClickFail");
	 * fnsApps_Report_Logs("FAILED == Unable to Click on Link having Xpath >> "
	 * +SearchResult_Code_link_Xpath+", Please refer ScreenShot [ "+"LinkClickFail"+
	 * fnsScreenShot_Date_format()
	 * +" ]."+". Getting Exception  >> "+t.getMessage());
	 * throw new Exception("FAILED == Unable to Click on Link having Xpath >> "
	 * +SearchResult_Code_link_Xpath+", Please refer ScreenShot [ "+"LinkClickFail"+
	 * fnsScreenShot_Date_format() +" ]."+Throwables.getStackTraceAsString(t));}
	 * }
	 */

	// Click on Link by matchingText Xpath // used in link tab =dd and facility link
	// click //above method commented as new fucntionality came 'Recent menu'
	public static void fnsTable_ClickOn_LINK_ByMatchingText(String LinkMatchingText) throws Throwable {
		try {
			boolean Faility_Link_Click_Done = false;
			// String Facility_link_Xpath = "//*[text()='" + LinkMatchingText + "']";
			String Facility_link_Xpath = "//td/a[text()='" + LinkMatchingText + "']";
			// driver.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS);
			Integer Links_Obj_Size = driver.findElements(By.xpath(Facility_link_Xpath)).size();

			for (int i = 1; i <= Links_Obj_Size; i++) {
				SearchResult_Facility_link_Xpath = "(" + Facility_link_Xpath + ")[" + i + "]";
				if (driver.findElements(By.xpath(SearchResult_Facility_link_Xpath)).size() > 0) {
					if (driver.findElement(By.xpath(SearchResult_Facility_link_Xpath)).isDisplayed()) {
						driver.findElement(By.xpath(SearchResult_Facility_link_Xpath)).click();
						Faility_Link_Click_Done = true;
						break;
					}
				}
				if (i == Links_Obj_Size && Faility_Link_Click_Done == false) {
					throw new Exception();
				}
			}

			fnsApps_Report_Logs(
					"PASSED == Successfully click on the Link having xpath >> " + SearchResult_Facility_link_Xpath);
			fnsLoading_Progressingwait(1);

		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot("LinkClickFail");
			fnsApps_Report_Logs("FAILED == Unable to Click on Link having Xpath >> " + SearchResult_Facility_link_Xpath
					+ ", Please refer ScreenShot [ " + "LinkClickFail" + fnsScreenShot_Date_format() + " ]."
					+ ". Getting Exception  >> " + Throwables.getStackTraceAsString(t));
			throw new Exception("FAILED == Unable to Click on Link having Xpath >> " + SearchResult_Facility_link_Xpath
					+ ", Please refer ScreenShot [ " + "LinkClickFail" + fnsScreenShot_Date_format() + " ]."
					+ Throwables.getStackTraceAsString(t));
		}
	}

	public static void fnsTable_Select_ByMatchingText(String LinkMatchingText) throws Throwable {
		try {
			boolean Faility_Link_Click_Done = false;
			String Facility_link_Xpath = "//*[text()='" + LinkMatchingText + "']";
			// driver.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS);
			Integer Links_Obj_Size = driver.findElements(By.xpath(Facility_link_Xpath)).size();

			for (int i = 1; i <= Links_Obj_Size; i++) {
				SearchResult_Facility_link_Xpath = "(" + Facility_link_Xpath + ")[" + i + "]";
				if (driver.findElements(By.xpath(SearchResult_Facility_link_Xpath)).size() > 0) {
					if (driver.findElement(By.xpath(SearchResult_Facility_link_Xpath)).isDisplayed()) {
						driver.findElement(By.xpath(SearchResult_Facility_link_Xpath)).click();
						Faility_Link_Click_Done = true;
						break;
					}
				}
				if (i == Links_Obj_Size && Faility_Link_Click_Done == false) {
					throw new Exception();
				}
			}

			fnsApps_Report_Logs(
					"PASSED == Successfully click on the Link having xpath >> " + SearchResult_Facility_link_Xpath);
			fnsLoading_Progressingwait(1);

		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot("LinkClickFail");
			fnsApps_Report_Logs("FAILED == Unable to Click on Link having Xpath >> " + SearchResult_Facility_link_Xpath
					+ ", Please refer ScreenShot [ " + "LinkClickFail" + fnsScreenShot_Date_format() + " ]."
					+ ". Getting Exception  >> " + Throwables.getStackTraceAsString(t));
			throw new Exception("FAILED == Unable to Click on Link having Xpath >> " + SearchResult_Facility_link_Xpath
					+ ", Please refer ScreenShot [ " + "LinkClickFail" + fnsScreenShot_Date_format() + " ]."
					+ Throwables.getStackTraceAsString(t));
		}
	}

	// Function to Click_on button UnTill Navigate to NextPage
	public static void fnClick_UnTill_Navigate_to_NextPage(String ButtonXpathKey, String NextPageElementXpathKey)
			throws Exception {
		try {
			for (int i = 0; i < 3; i++) {
				if (driver.findElements(By.xpath(OR_IM.getProperty(NextPageElementXpathKey))).size() > 0) {
					break;
				} else {
					driver.findElement(By.xpath(ButtonXpathKey)).click();
					Thread.sleep(5000);
				}
			}

			fnsApps_Report_Logs("PASSED == Successfully Click on Element having Xpath  >> " + ButtonXpathKey);
		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (StaleElementReferenceException e) {
			isTestCasePass = false;
			fnsTake_Screen_Shot("UnableToClick_" + ButtonXpathKey);
			fnsApps_Report_Logs("FAILED == Unable To Click on Element having Xpath >> " + ButtonXpathKey
					+ ", plz see screenshot [ UnableToClick_" + ButtonXpathKey + fnsScreenShot_Date_format() + " ]");
			throw new Exception("Unable To Click on element [ " + ButtonXpathKey
					+ " ] , plz see screenshot [ UnableToClick_" + ButtonXpathKey + fnsScreenShot_Date_format() + " ]");
		}
	}

	// Click on Yes button by Making xpath from table TotalRowCount
	public static Integer fnsTable_Total_RowCount_Fetch(String TableHeaderXpath) throws Throwable {
		int TotalrowCount = 0;
		try {
			fnsGet_Element_Enabled(TableHeaderXpath);
			WebElement stdtable = fnsGet_OR_CLNT_ObjectX(TableHeaderXpath);
			List<WebElement> no_of_rows_in_list = stdtable.findElements(By.tagName("tr"));

			for (WebElement countrows : no_of_rows_in_list) {
				TotalrowCount++;
			}

		} catch (StaleElementReferenceException s) {
			fnsTable_Total_RowCount_Fetch(TableHeaderXpath);
		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot(TableHeaderXpath);
			fnsApps_Report_Logs("FAILED == Unable to fetch table total rowcount for table having xpaths >> "
					+ TableHeaderXpath + " , Please refer screen shot [" + TableHeaderXpath
					+ fnsScreenShot_Date_format() + " ]" + Throwables.getStackTraceAsString(t));
			throw new Exception("FAILED == Unable to fetch table total rowcount for table having xpaths >> "
					+ TableHeaderXpath + " , Please refer screen shot [" + TableHeaderXpath
					+ fnsScreenShot_Date_format() + " ]" + Throwables.getStackTraceAsString(t));
		}

		return TotalrowCount;
	}

	/*
	 * //###################################################################
	 * Configuration Function
	 * ############################################################################
	 * //Initialising the Tests
	 * public void fnsInitialize() throws Exception {
	 * 
	 * fnsApps_Report_Logs("Pradeep--we are in fnsInitialize");
	 * fnsApps_Report_Logs("Pradeep--we are in fnsInitialize"+isInitalized);
	 * if (!isInitalized) {
	 * fnsApps_Report_Logs("Pradeep--we are in fnsInitialize"+isInitalized);
	 * InputStream loggerStream = new FileInputStream(System.getProperty("user.dir")
	 * + "//src//log4j.properties");
	 * Properties prop = new Properties();
	 * prop.load(loggerStream);
	 * PropertyConfigurator.configure(prop);
	 * APP_LOGS = Logger.getLogger("devpinoyLogger");
	 * 
	 * fnsApps_Report_Logs("Pradeep--we are in fnsInitialize");
	 * CONFIG = new Properties();
	 * FileInputStream ip = new FileInputStream(System.getProperty("user.dir") +
	 * "//src//nsf//ecap//config/config.properties");
	 * CONFIG.load(ip);
	 * 
	 * 
	 * OR_CLNT = new Properties();
	 * ip = new FileInputStream(System.getProperty("user.dir") +
	 * "//src//nsf//ecap//config/OR_CLNT.properties");
	 * OR_CLNT.load(ip);
	 * fnsApps_Report_Logs("Loaded Property files successfully");
	 * 
	 * 
	 * OR_IM = new Properties();
	 * ip = new FileInputStream(System.getProperty("user.dir") +
	 * "//src//nsf//ecap//config/OR_IM.properties");
	 * OR_IM.load(ip);
	 * //fnsApps_Report_Logs("Loaded Property files successfully");
	 * 
	 * fnsApps_Report_Logs("Pradeep---"+System.getProperty("user.dir") );
	 * Client_Suitexls = new Xls_Reader(System.getProperty("user.dir") +
	 * "//src//nsf//ecap//xls//Client_Suite.xlsx");
	 * fnsApps_Report_Logs("Pradeep---"+System.getProperty("user.dir") );
	 * fnsApps_Report_Logs(Client_Suitexls.filename);
	 * 
	 * suiteXls = new Xls_Reader(System.getProperty("user.dir") +
	 * "\\src\\nsf\\ecap\\xls\\Suite.xlsx");
	 * fnsApps_Report_Logs("Loaded Excel Files successfully");
	 * 
	 * BrowserCheck();
	 * 
	 * 
	 * 
	 * isInitalized = true;
	 * startExecutionTime = fnsLOGS_Date_format();
	 * hashXlData = new HashMap();
	 * }
	 * 
	 * 
	 * }
	 * 
	 * 
	 */

	// Function to Launch browser and login
	public static boolean fnsLaunchBrowserAndLogin() throws Throwable {
		boolean present;

		startExecutionTime = fnpTimestamp();
		ScreenShotFlagWithOR_CLNT = true;

		try {
			fns_Launch_Browser_Only();
			// Commented this function for SSO Switch User Implementation
			// fnsIpulse_Login_Split_Excel_Urls("secureipulse",
			// CONFIG.getProperty("Client_LoginAs"), CONFIG.getProperty("adminUsername"),
			// CONFIG.getProperty("adminPassword")); //MainUserForSSO_withCompleteEmailID
			// Added this function for SSO Switch User Implementation
			/*
			 * fnsIpulse_Login_Split_Excel_Urls("SSO_SwitchUser",
			 * CONFIG.getProperty("Client_LoginAs"),
			 * CONFIG.getProperty("MainUserForSSO_withCompleteEmailID"),
			 * CONFIG.getProperty("adminPassword"));
			 */
			fnsIpulse_Login_SSO("ipulse", CONFIG.getProperty("Client_LoginAs"), CONFIG.getProperty("adminUsername"),
					CONFIG.getProperty("adminPassword"));

			if (!veryFirstTimeAfterLogin) {
				TestSuiteBase_NSFOnline.fnsIPulse_Application_Version("Client");
				veryFirstTimeAfterLogin = true;

			}
			if (ApplicationVersion_Flag) {
				fnsIPulse_Application_Version("Client");
				ApplicationVersion_Flag = false;
			}

			present = true;
		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			isTestCasePass = false;
			// fnpDesireScreenshot("LaunchBrowserAndLoginFail");
			fnsTake_Screen_Shot("LaunchBrowserAndLoginFail");
			present = false;
			// ErrorUtil.addVerificationFailure(t); //throwing multiple exception error.
			fnsApps_Report_Logs("");
			fnsApps_Report_Logs(
					"'Launch Browser And Login' is getting fail, Please refer screen shot [LaunchBrowserAndLoginFail"
							+ fnsScreenShot_Date_format() + "]." + " Getting Exception >> "
							+ Throwables.getStackTraceAsString(t));
			throw new Exception(
					"'Launch Browser And Login' is getting fail, Please refer screen shot [LaunchBrowserAndLoginFail"
							+ fnsScreenShot_Date_format() + "]." + " Getting Exception >> "
							+ Throwables.getStackTraceAsString(t));
		}

		return present;
	}

	// Check for Browser Type defined in Suits Excel
	public static void BrowserCheck() {

		for (int i = 2; i <= suiteXls.getRowCount("Test Suite"); i++) {

			if (suiteXls.getCellData("Test Suite", "TSID", i).equalsIgnoreCase("Client_Suite")) {
				BrowserName = suiteXls.getCellData("Test Suite", "Browser", i);

				if (BrowserName == "") {
					BrowserName = "IE";
				}

				break;
			}
		}
	}

	@SuppressWarnings("unchecked")
	public static HashMap<String, String> fnLoadHashData(HashMap hashXlData, Xls_Reader xls, String sheetName,
			int startRow) {

		String hashkey;
		String hashValue;
		int cols = xls.getColumnCount(sheetName);

		for (int i = 0; i < cols; i++) {
			hashkey = xls.getCellData(sheetName, i, startRow);
			hashValue = xls.getCellData(sheetName, i, startRow + 1);

			if (!hashkey.isEmpty()) {
				hashXlData.put(hashkey, hashValue);
			}
		}

		return hashXlData;
	}

	// check if the suite has to be skip
	@BeforeSuite
	public void checkSuiteSkip() throws Throwable {
		/*
		 * currentSuiteName = "Client_Suite";
		 * TestSuiteBase_MonitorPlan.fnsMoveMousePointerAtCenterBottomOfScreen();
		 * fnInitialize();
		 * BrowserCheck();
		 * if(TestUtil.isSuiteRunnable(suiteXls, "Client_Suite")){
		 * FileUtils.deleteDirectory(new File((System.getProperty("user.dir") +
		 * CONFIG.getProperty("screenshots_path")+"//Client//")));
		 * browserName=TestUtil.getBrowserName(suiteXls, "Client_Suite");
		 * excelSiteURL= TestUtil.getExcelSiteURL(suiteXls, currentSuiteName);
		 * 
		 * int rowNum = suiteXls.getCellRowNum("Test Suite", "TSID", currentSuiteName);
		 * suiteXls.setCellData("Test Suite", "Last_Execution", rowNum,
		 * fnReturnDateWithTimeForExcel());
		 * 
		 * }
		 * if(!TestUtil.isSuiteRunnable(suiteXls, "Client_Suite")){
		 * fnsApps_Report_Logs("Skipped Client_Suite as the runmode was set to NO");
		 * throw new
		 * SkipException("Runmode of Client_Suite set to no. So Skipping all tests in Client_Suite"
		 * );}
		 */

		fns_CheckSiteSkip("Client_Suite");
	}

	// wait till element get visible
	public static void fnpWaitForVisible(String XpathKey) throws Throwable {

		fnsLoading_Progressingwait(1);
		int i = 0;

		WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(CONFIG.getProperty("implicitWaitTime")));
		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(XpathKey))));

		int retries = 0;
		while (true) {
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_CLNT.getProperty(XpathKey))));
				return;
			} catch (org.openqa.selenium.StaleElementReferenceException e) {
				if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					retries++;
					// fnpMymsg(retries + "In staleElementException catch block of fnpWaitForVisible
					// function for " + XpathKey);
					continue;
				} else {
					throw e;
				}
			}

		}

	}

	// Get Excel Cell value by column name
	public static String fnsReturn_ExcelCellValue_ByMatchingColumnName(String SheetName, String ColumnName)
			throws Throwable {
		try {
			String CellValue = null;
			for (int i = 5; i < 50; i++) {
				for (int j = 0; j < 16; j++) {
					String ExcelCellValue = Client_Suitexls.getCellData(SheetName, j, i);
					if (ExcelCellValue.equalsIgnoreCase(ColumnName)) {
						CellValue = Client_Suitexls.getCellData(SheetName, j, i + 1);
						break;
					}
				}
				if (CellValue != null) {
					break;
				}
			}
			if (CellValue == null) {
				throw new Exception("Column<" + ColumnName + "> Does not exists into sheet<" + SheetName + ">");
			}
			// fnsApps_Report_Logs("Excel Value = "+CellValue);
			return CellValue.trim();
		} catch (Throwable t) {
			isTestCasePass = false;
			fnsApps_Report_Logs(t.getMessage());
			throw new Exception(t.getMessage());
		}
	}

	public static void TestCaseSkip(Integer counts, String customervalue) throws SkipException {
		try {
			// counts++;
			if (!(runmodes[counts].equalsIgnoreCase("Y"))) {
				fnsApps_Report_Logs(
						"****************************************************************************************************************************************");
				fnsApps_Report_Logs("################################## Runmode of Case::" + (counts + 1)
						+ " is set to NO, So Skipping this Case.");
				throw new SkipException("Skipping Test Case " + (counts + 1) + " as runmode set to NO");
			}
		} catch (SkipException sk) {
			// System.out.println("SkipException TCSkipReturnValue ="+TCSkipReturnValue+"
			// count"+counts);
		}

	}

	// return the test data from a test in a 2 dim array
	public static Object[][] getExcelData_for_DataProvider(Xls_Reader xls, String testCaseName) {
		Integer rows = null;
		Integer cols = null;
		// if the sheet is not present
		if (!xls.isSheetExist(testCaseName)) {
			xls = null;
			return new Object[1][0];
		}
		for (int rowNum = 0; rowNum <= rowNum + 1; rowNum++) {
			String CellText = xls.getCellData(testCaseName, 1, rowNum + 1);
			if (CellText.equalsIgnoreCase("")) {
				rows = rowNum;
				break;
			}
		}
		cols = xls.getColumnCount(testCaseName);
		Object[][] data = new Object[rows - 1][cols - 3];
		for (int rowNum = 2; rowNum <= rows; rowNum++) {
			for (int colNum = 0; colNum < cols - 3; colNum++) {
				System.out.print(xls.getCellData(testCaseName, colNum, rowNum) + " -- ");
				data[rowNum - 2][colNum] = xls.getCellData(testCaseName, colNum, rowNum);
			}
		}
		return data;

	}

	public String getUniqueDateTime() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		Date date = new Date(System.currentTimeMillis());
		String uniqueNum = formatter.format(date);
		uniqueNum = uniqueNum.replace("-", "");
		uniqueNum = uniqueNum.replace(":", "");
		uniqueNum = uniqueNum.replace(" ", "");
		return uniqueNum;
	}

	// Always run before suite
	@AfterSuite(alwaysRun = true)
	public void Finishing_IM_Suite_Script() throws Throwable {
		ScreenShotFlagWithOR_CLNT = false;
		fnsApps_Report_Logs("");
		fnsApps_Report_Logs(
				"########################################################### Client Suite END ########################################################## ");
		fnsApps_Report_Logs(
				"=========================================================================================================================================");
		fnsApps_Report_Logs("");
	}
	// "########################################################### Outside client
	// Methods ########################################################## "

	@SuppressWarnings("finally")
	public static boolean fns_Launch_Browser_Only() throws Throwable {
		boolean present = false;
		try {
			String ip_Address = fns_GetCurrentSystemIPAddress();

			NewNsfOnline_Element_Max_Wait_Time = Integer.parseInt("600");// (Long.parseLong(CONFIG.getProperty("ElementWaitTime")))*5;
			Listings_Element_Max_Wait_Time = Integer.parseInt("120");
			start = new Date();
			if (browserName.equalsIgnoreCase("IE")) {

				System.setProperty("webdriver.ie.driver", CONFIG.getProperty("IEDriverPath"));

				DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
				caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				// caps.setCapability("IGNORE_ZOOM_SETTING", true); // Not Working
				caps.setCapability("IE_ENSURE_CLEAN_SESSION", true);
				caps.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
				caps.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
				caps.setCapability(InternetExplorerDriver.NATIVE_EVENTS, true);
				driver = new InternetExplorerDriver(caps);
				// fnsApps_Report_Logs("Browser type is IE");
				present = true;
			}

			if (browserName.equalsIgnoreCase("chrome")) {

				System.setProperty("webdriver.chrome.driver", CONFIG.getProperty("ChromeDriverPath"));

				// DesiredCapabilities caps = DesiredCapabilities.chrome();
				// ChromeOptions options = new ChromeOptions();
				caps = DesiredCapabilities.chrome();
				options = new ChromeOptions();

				if (!((ip_Address.equalsIgnoreCase("192.168.102.40")) || (ip_Address.equalsIgnoreCase("10.223.241.14"))
						|| (ip_Address.equalsIgnoreCase("10.223.241.15"))
						|| (ip_Address.equalsIgnoreCase("10.223.241.16")))) {
					options.addArguments("--incognito");
				}

				options.addArguments("--disable-infobars");
				options.addArguments("--start-maximized");
				// options.addArguments("--disable-web-security"); // Blocking 3rd party cookies
				// in listing and nsfconnectaccountsetup
				options.addArguments("--no-proxy-server");
				options.addArguments("--headless");

				// Working fine
				/*
				 * options.addArguments(Arrays.asList("--no-sandbox",
				 * "--ignore-certificate-errors","--homepage=about:blank","--no-first-run"));
				 * options.addArguments("--test-type");
				 * options.addArguments("disable-extensions");
				 * options.addArguments("chrome.switches","--disable-extensions");
				 * options.addArguments("--disable-notifications");
				 */

				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("credentials_enable_service", false);
				prefs.put("profile.password_manager_enabled", false);
				options.setExperimentalOption("prefs", prefs);

				/*
				 * options.addArguments("enable-nacl");
				 * options.addArguments("nacl-broker");
				 * options.addArguments("nacl-gdb");
				 * options.addArguments("test-type");
				 * options.addArguments("nacl-loader");
				 * options.addArguments("nacl-loader-nonsfi");
				 * options.addArguments("v8-natives-passed-by-fd");
				 * options.addArguments("--start-maximized");
				 * options.addArguments("--disable-popup-blocking");
				 */

				// options.addArguments("--disable-captive-portal-bypass-proxy");
				// options.addArguments("--use-fake-ui-for-media-stream");
				// options.addArguments("--bypass-app-banner-engagement-checks");
				// options.addArguments("--disable-captive-portal-bypass-proxy");
				// options.addArguments("--demo");
				// options.addArguments("--override-plugin-power-saver-for-testing");
				// options.addArguments("--disable-datasaver-prompt");
				// options.addArguments("--allow-cross-origin-auth-prompt");
				// options.addArguments("--disable-prompt-on-repost");
				// options.addArguments("--disable-password-generation");
				// options.addArguments("--local-heuristics-only-for-password-generation");
				// options.addArguments("--password-store");
				// options.addArguments("--disable-ios-password-suggestions");
				// options.addArguments("test-type");
				// options.addArguments("--start-maximized");
				// options.addArguments("-incognito"); // Not Working
				// options.addArguments("system-developer-mode"); // Not Working
				// options.addArguments("force-dev-mode-highlighting"); // Not Working
				// options.addArguments("disable-about-in-settings ");
				// options.addArguments("enable-tracing");
				// options.addArguments("enable-tracing-output");
				// options.addArguments("trace-startup");
				// options.addArguments("trace-startup-duration");
				// options.addArguments("trace-export-events-to-etw");
				// options.addArguments("auto");
				// options.addArguments("ash-debug-shortcuts ");
				// options.addArguments("ui-show-paint-rects");
				// options.addArguments("ui-show-replica-screenspace-rects");
				// options.addArguments("--enable-text-input-focus-manager");
				// options.addArguments("new-window");
				// caps.setCapability(ChromeOptions.CAPABILITY, options);
				// caps.setCapability("chrome.binary","C:\\Program Files
				// (x86)\\Google\\Chrome\\Application\\chrome.exe");
				// caps.setCapability(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, true);
				// caps.setCapability(CapabilityType.ENABLE_PROFILING_CAPABILITY, false );
				// caps.setCapability(ChromeOptions.CAPABILITY, true);
				// caps.setCapability("", true);
				// enable-text-input-focus-manager // new-window
				// //allow-running-insecure-content //test-name //enable-nacl //version
				// //nacl-broker

				options.setExperimentalOption("useAutomationExtension", false);
				options.setExperimentalOption("excludeSwitches",
						Collections.singletonList("enable-automation"));

				caps.setCapability(ChromeOptions.CAPABILITY, options);

				driver = new ChromeDriver(caps);

				// fnsApps_Report_Logs("Browser type is CHROME");
				present = true;
			}

			if (browserName.equalsIgnoreCase("firefox")) {

				FirefoxProfile profile = new FirefoxProfile();
				DesiredCapabilities dc = DesiredCapabilities.firefox();
				// profile.setAcceptUntrustedCertificates(false);
				// profile.setAssumeUntrustedCertificateIssuer(true);
				// profile.setPreference("browser.download.folderList", 2);
				// profile.setPreference("browser.helperApps.alwaysAsk.force", false);
				profile.setPreference("security.mixed_content.block_active_content", false);
				profile.setPreference("security.mixed_content.block_display_content", false);
				dc = DesiredCapabilities.firefox();
				dc.setCapability(FirefoxDriver.PROFILE, profile);
				driver = new FirefoxDriver(dc);
				// fnsApps_Report_Logs("Browser type is Firefox");
				present = true;
			}

			if (browserName.equalsIgnoreCase("edge")) {

				System.setProperty("webdriver.edge.driver",
						System.getProperty("user.dir") + "\\driver\\msedgedriver_86.0.622.56.exe");

				/*
				 * DesiredCapabilities caps = DesiredCapabilities.edge();
				 * EdgeOptions edgeOptions = new EdgeOptions();
				 * caps.setCapability("platform", Platform.WINDOWS);
				 * caps.setCapability("nativeEvents", false);
				 * caps.setCapability("applicationCacheEnabled", false);
				 * caps.setCapability("cssSelectorsEnabled", true);
				 * caps.setCapability("browserConnectionEnabled", true);
				 * caps.setCapability("javascriptEnabled", true);
				 * caps.setCapability("EDGE_ENSURE_CLEAN_SESSION", true);
				 * caps.setCapability("useAutomationExtension", false);
				 * caps.setCapability("excludeSwitches",
				 * Collections.singletonList("enable-automation"));
				 * 
				 * edgeOptions.setCapability("useAutomationExtension", false);
				 * edgeOptions.setCapability("excludeSwitches",
				 * Collections.singletonList("enable-automation"));
				 * edgeOptions.setCapability("--disable-infobars", true);
				 * edgeOptions.setCapability("--start-maximized", true);
				 * edgeOptions.setCapability("--disable-web-security", true);
				 * edgeOptions.setCapability("--no-proxy-server", true);
				 * caps.setCapability(ChromeOptions.CAPABILITY, edgeOptions);
				 * driver = new EdgeDriver(edgeOptions);
				 */

				driver = new EdgeDriver();
				driver.manage().window().maximize();

				present = true;
			}

		} catch (Throwable t) {
			present = false;
			throw new Exception(Throwables.getStackTraceAsString(t));
		} finally {
			return present;
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

	public static void fnsIpulse_Login_Split_Excel_Urls(String PortalNamne, String LoginAs, String userName,
			String password) throws Throwable {

		try {
			// Commented because of SSO Switch User Implementation
			// iPulse_SecureLogin_Flag=true
			/*
			 * if(
			 * !(PortalNamne.toLowerCase().trim().equalsIgnoreCase("ipulse_newnsfonline"))
			 * ){
			 * if ( (LoginAs.toUpperCase().trim().equalsIgnoreCase("JHUGHES")) || (
			 * CONFIG.getProperty("iPulse_SecureLogin_Flag").toLowerCase().trim().
			 * equalsIgnoreCase("true") )){
			 * PortalNamne = "secureipulse";
			 * }else{
			 * PortalNamne = "ipulse";
			 * }
			 * }
			 */
			String siteUrl = null;

			if ((PortalNamne.toLowerCase().trim()).equalsIgnoreCase("ipulse")) {

				if (excelSiteURL != null) {
					if (excelSiteURL.equalsIgnoreCase("")) {
						siteUrl = CONFIG.getProperty("testSiteName");
					} else {

						String SplitExcelUrlValue = excelSiteURL.split("SecureiPulse:")[0];

						String iPulseSiteUrl = SplitExcelUrlValue.split("iPulse:")[1].trim();
						System.out.println(iPulseSiteUrl + " = " + iPulseSiteUrl);
						siteUrl = iPulseSiteUrl;

					}
				} else {
					siteUrl = CONFIG.getProperty("testSiteName");
				}
			}
			// Condition has been added for SSO Switch User Implementation
			if ((PortalNamne.toLowerCase().trim()).equalsIgnoreCase("SSO_SwitchUser")) {
				if (excelSiteURL != null) {
					if (excelSiteURL.equalsIgnoreCase("")) {
						siteUrl = CONFIG.getProperty("testSiteName");
					} else {

						String SplitExcelUrlValue = excelSiteURL.split("SecureiPulse:")[0];

						String iPulseSiteUrl = SplitExcelUrlValue.split("iPulse:")[1].trim();
						System.out.println(iPulseSiteUrl + " = " + iPulseSiteUrl);
						siteUrl = iPulseSiteUrl;

					}
				} else {
					siteUrl = CONFIG.getProperty("testSiteName");
				}
			}

			if ((PortalNamne.toLowerCase().trim()).equalsIgnoreCase("secureipulse")) {

				if (excelSiteURL != null) {
					if (excelSiteURL.equalsIgnoreCase("")) {
						siteUrl = CONFIG.getProperty("SecuretestSiteName");
					} else {

						String iPulseSecureSiteUrl = excelSiteURL.split("SecureiPulse:")[1].trim();
						System.out.println();
						System.out.println(iPulseSecureSiteUrl + " = " + iPulseSecureSiteUrl);
						siteUrl = iPulseSecureSiteUrl;

					}
				} else {
					siteUrl = CONFIG.getProperty("SecuretestSiteName");
				}
			}

			if ((PortalNamne.toLowerCase().trim()).equalsIgnoreCase("ipulse_newnsfonline")) {
				// LoginAs = CONFIG.getProperty("Client_LoginAs");
				LoginAs = CONFIG.getProperty("Grip_LoginAs");
				if (env.toLowerCase().equalsIgnoreCase("test")) {
					siteUrl = "https://testapps.nsf.org/ecap/jsp/login/securelogin.xhtml";
					// siteUrl = "https://testapps.nsf.org/ecap/";
				} else if (env.toLowerCase().equalsIgnoreCase("staging")) {
					siteUrl = "https://stgapps.nsf.org/ecap/";
				}
			}

			fnsApps_Report_Logs("Application credentials are URL[ " + siteUrl + " ], UserName[ " + userName
					+ " ] & Password[ " + password + " ]");
			driver.get(siteUrl);

			// below line related to SSO Switch User Implementation
			fnsComman_BrowserLogin_ElementWait(OR_CLNT.getProperty("SSO_UserName"),
					CONFIG.getProperty("Element_MAX_WaitTime"));
			// Condition has been added for SSO Switch User Implementation
			if (PortalNamne.toLowerCase().trim().equalsIgnoreCase("SSO_SwitchUser")) {
				WithOut_OR_fnGet_ObjectX(OR_CLNT.getProperty("SSO_UserName")).sendKeys(userName);
				Thread.sleep(5000);
				WithOut_OR_fnGet_ObjectX(OR_CLNT.getProperty("SSO_NexButton")).click();
				fnsLoading_Progressingwait(1);
				Runtime.getRuntime().exec(System.getProperty("user.dir") + "\\AutoIt_scripts\\sso_chrome.exe");
			}
			// Commented because of SSO Switch User Implementation
			// fnsComman_BrowserLogin_ElementWait(OR_CLNT.getProperty("UserName"),
			// CONFIG.getProperty("Element_MAX_WaitTime"));
			/*
			 * if( ((PortalNamne.toLowerCase().trim()).equalsIgnoreCase("secureipulse")) ||
			 * (
			 * ((PortalNamne.toLowerCase().trim()).equalsIgnoreCase("ipulse_newnsfonline"))
			 * && ((siteUrl.toLowerCase().trim()).contains("securelogin")))){
			 * WithOut_OR_fnGet_ObjectX(OR_CLNT.getProperty("LoginAs")).clear();
			 * WithOut_OR_fnGet_ObjectX(OR_CLNT.getProperty("LoginAs")).sendKeys("");
			 * WithOut_OR_fnGet_ObjectX(OR_CLNT.getProperty("LoginAs")).sendKeys(LoginAs);
			 * }
			 */
			/*
			 * WithOut_OR_fnGet_ObjectX(OR_CLNT.getProperty("UserName")).clear();
			 * WithOut_OR_fnGet_ObjectX(OR_CLNT.getProperty("UserName")).sendKeys("");
			 * WithOut_OR_fnGet_ObjectX(OR_CLNT.getProperty("UserName")).sendKeys(userName);
			 * WithOut_OR_fnGet_ObjectX(OR_CLNT.getProperty("password")).sendKeys("");
			 * WithOut_OR_fnGet_ObjectX(OR_CLNT.getProperty("password")).sendKeys(password);
			 * WithOut_OR_fnGet_ObjectX(OR_CLNT.getProperty("Login")).click();
			 */
			fnsLoading_Progressingwait(1);

			/**
			 * Handling acknowledge Alert, Just coming after login.
			 */
			WithOut_OR_fnGet_ObjectX(OR_CLNT.getProperty("xpathForAck")).click();
			Thread.sleep(1000);
			// Function Added to SSO Switch User Implementation
			switchUser(LoginAs);
			for (int i = 0; i <= Long.parseLong(CONFIG.getProperty("Element_MAX_WaitTime")); i++) {
				if (i == Long.parseLong(CONFIG.getProperty("Element_MAX_WaitTime"))) {
					throw new Exception("After Login, Home Page is not getting load after <"
							+ Long.parseLong(CONFIG.getProperty("Element_MAX_WaitTime")) + ">seconds wait.");
				} else {
					Thread.sleep(1000);
				}
				// driver.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS);
				if (driver.findElements(By.xpath(OR_CLNT.getProperty("Login_errorMessage"))).size() > 0) {
					String ErrorMsg = WithOut_OR_fnGet_ObjectX(OR_CLNT.getProperty("Login_errorMessage")).getText();
					throw new Exception("I-pulse Login Failed, Application Error <" + ErrorMsg + ">");
				} else {
					fnsComman_BrowserLogin_ElementWait(OR_CLNT.getProperty("logOutBtnCLNT"),
							CONFIG.getProperty("Element_MAX_WaitTime"));
					break;
				}
			}
		} catch (Throwable t) {
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}

	// Function to wait for element Visible
	public static void fnsComman_BrowserLogin_ElementWait(String XpathKeyWithoutOR, String WaitTime) throws Exception {
		try {
			WebDriverWait elementwaitvar1 = new WebDriverWait(driver, Long.parseLong(WaitTime));
			elementwaitvar1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XpathKeyWithoutOR)))
					.isEnabled();
			// fnsApps_Report_Logs("PASSED == Element is Visible having Xpath >>
			// "+XpathKeyWithoutOR);
		} catch (NoSuchWindowException W) {
			// isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable e) {
			// isTestCasePass = false;
			// fnsApps_Report_Logs("FAILED == Element is not Visible having Xpath
			// >>"+XpathKeyWithoutOR+" , Getting Exception >>
			// "+Throwables.getStackTraceAsString(e));
			throw new Exception("FAILED == Element is not Visible having Xpath >>" + XpathKeyWithoutOR
					+ " , Getting Exception >> " + Throwables.getStackTraceAsString(e));
		}
	}

	// Function to find and return the object using Xpath
	public static WebElement WithOut_OR_fnGet_ObjectX(String XpathKey) throws Exception {

		try {
			for (int waits = 0; waits < Long.parseLong(CONFIG.getProperty("ElementWaitTime")); waits++) {

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
			// isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (NoSuchElementException e) {
			// isTestCasePass = false;
			// fnsTake_Screen_Shot_Without_OR("NoSuchElementException");
			fnsTake_Screen_Shot("NoSuchElementException");
			fnsApps_Report_Logs("FAILED == Element is not found >> " + XpathKey
					+ " ] , plz see screenshot [NoSuchElementException" + fnsScreenShot_Date_format() + " ]");
			throw new Exception("FAILED == Element is not found >> " + XpathKey
					+ " ] , plz see screenshot [NoSuchElementException" + fnsScreenShot_Date_format() + " ]");
		} catch (TimeoutException e) {
			// isTestCasePass = false;
			// fnsTake_Screen_Shot_Without_OR("TimeOut" + XpathKey);
			fnsTake_Screen_Shot("TimeOut" + XpathKey);
			fnsApps_Report_Logs("FAILED == Element is not Present, getting TimeOut >> " + XpathKey
					+ " ] , plz see screenshot [TimeOut" + fnsScreenShot_Date_format() + " ]");
			throw new Exception("FAILED == Element is not Present, getting TimeOut >> " + XpathKey
					+ " ] , plz see screenshot [TimeOut" + fnsScreenShot_Date_format() + " ]");
		} catch (Throwable e) {
			// isTestCasePass = false;
			// fnsTake_Screen_Shot_Without_OR("NotPresent" + XpathKey);
			fnsTake_Screen_Shot("NotPresent" + XpathKey);
			fnsApps_Report_Logs("FAILED == Element is not found >> " + XpathKey + " ] , plz see screenshot [NotPresent"
					+ fnsScreenShot_Date_format() + " ]" + ". Getting Exception  >> "
					+ Throwables.getStackTraceAsString(e));
			throw new Exception("FAILED == Element is not found >> " + XpathKey + " ] , plz see screenshot [NotPresent"
					+ fnsScreenShot_Date_format() + " ]" + ". Getting Exception  >> "
					+ Throwables.getStackTraceAsString(e));
		}
	}

	// Function of loading image appear on the screen (Block UI).
	public static void fnsLoading_Progressingwait(int waitcount) throws Throwable {
		try {
			LoadingImageFlag = false;
			Actual_Loading_Time = 1;
			Loading_Image_Xpath = null;
			PageSourceText = null;
			String Loading_Classes_From_OR = OR_CLNT.getProperty("Loading_Progressing_New").trim();
			Integer PageLoadWait = fnsLoading_PageLoad();
			for (int wait = PageLoadWait; wait <= ((Long
					.parseLong(CONFIG.getProperty("Element_MAX_WaitTime")))); wait++) {
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

				if (Loading_Image_Xpath.contains("ui-messages-error-summary")) {
					PageSourceText = driver.getPageSource().toLowerCase();
					if (PageSourceText.contains("ui-messages-error-summary")) {
						if (driver.findElement(By.xpath(Loading_Image_Xpath)).isDisplayed()) {
							WebElement Element = driver.findElement(By.xpath(Loading_Image_Xpath));
							Actions act = new Actions(driver);
							act.moveToElement(Element).build().perform();
							ErrorMsgText = driver.findElement(By.xpath(Loading_Image_Xpath)).getText().trim();
							throw new IllegalArgumentException();
						} else { // added on 4.4.2018
							fnsApps_Report_Logs(
									"*********  Pause/Wait is for < " + Actual_Loading_Time + " > seconds.  *****  "); // To
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
					}
				}

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
						Loading_Xpath_Array.add(Loading_Image_Xpath + "&&");
						fnsApps_Report_Logs("*********  Progressing Loading image is displayed for < "
								+ (Actual_Loading_Time) + " > seconds.");
						break;
					} else if (!(driver.findElement(By.xpath(Loading_Image_Xpath)).isDisplayed()) && wait > waitcount
							&& LoadingImageFlag == false) {
						fnsApps_Report_Logs(
								"*********  Pause/Wait is for < " + Actual_Loading_Time + " > seconds.  *****  "); // To
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
						Thread.sleep(250); // differ from new nsfonline
					}
				} catch (Throwable n) {
					if (wait > waitcount) {
						if (LoadingImageFlag == true) {
							Loading_Xpath_Array.add(Loading_Image_Xpath + "&&");
							fnsApps_Report_Logs("*********  Progressing Loading image is displayed for < "
									+ (Actual_Loading_Time) + " > seconds.");
							break;
						} else if (LoadingImageFlag == false) {
							fnsApps_Report_Logs(
									"*********  Pause/Wait is for < " + Actual_Loading_Time + " > seconds.  *****  "); // To
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
					} else { // differ from new nsfonline
						if (LoadingImageFlag == false) {
							Thread.sleep(250); // differ from new nsfonline
						} else {
							Loading_Xpath_Array.add(Loading_Image_Xpath + "&&");
							fnsApps_Report_Logs("*********  Progressing Loading image is displayed for < "
									+ (Actual_Loading_Time) + " > seconds.");
							break;
						}
					}
				}

				if (wait == (Long.parseLong(CONFIG.getProperty("Element_MAX_WaitTime")))) {
					throw new InterruptedException("Loading Issue : After < "
							+ (Long.parseLong(CONFIG.getProperty("Element_MAX_WaitTime")))
							+ " > Seconds wait page is not getting Load, please refer screenshot >> PageLoadFail"
							+ fnsScreenShot_Date_format() + "*****  < " + Loading_Image_Xpath + " >");
				}
				Actual_Loading_Time++;
			}

			/*
			 * PageSourceText = driver.getPageSource().toLowerCase();
			 * /if(PageSourceText.contains("ui-messages-error-summary")){
			 * if (driver.findElement(By.xpath(Loading_Image_Xpath)).isDisplayed()) {
			 * WebElement Element = driver.findElement(By.xpath(Loading_Image_Xpath));
			 * Actions act = new Actions(driver);
			 * act.moveToElement(Element).build().perform();
			 * ErrorMsgText =
			 * driver.findElement(By.xpath(Loading_Image_Xpath)).getText().trim();
			 * throw new IllegalArgumentException();
			 * }
			 * }
			 */
			PageSourceText = driver.getPageSource().toLowerCase();
			if (PageSourceText.contains("we are sorry")) {
				ErrorMsgText = "We are sorry...an unexpected error has occurred. Details have been forwarded to the Application Support Team for investigation. If the problem persists, please submit an issue on the Apps Portal.";
				throw new IllegalArgumentException();
			}

		} catch (IllegalArgumentException i) {
			// isTestCasePass = false;
			fnsTake_Screen_Shot("UnExpectedError");
			fnsApps_Report_Logs("Getting Un-Expected Application Error <" + ErrorMsgText
					+ ">, please refer screenshot >>UnExpectedError" + fnsScreenShot_Date_format() + "*****  < "
					+ Loading_Image_Xpath + " >" + "  Exception (" + Throwables.getStackTraceAsString(i));
			throw new Exception("Getting Un-Expected Application Error <" + ErrorMsgText
					+ ">, please refer screenshot >> UnExpectedError" + fnsScreenShot_Date_format() + "*****  < "
					+ Loading_Image_Xpath + " >" + "  Exception (" + Throwables.getStackTraceAsString(i));
		} catch (NoSuchElementException n) {
			fnsApps_Report_Logs("***** Progressing Loading image displayed for < " + Actual_Loading_Time
					+ " > seconds. **************** NS");
			try {
				fnsApps_Report_Logs("***** Progressing Loading image displayed for < " + Actual_Loading_Time
						+ " > seconds. **************** NS");
				// fnsLoading_Progressing_wait(waitcount);
			} catch (Throwable tt) {
				isTestCasePass = false;
				fnsTake_Screen_Shot("PageLoadFail_NS");
				fnsApps_Report_Logs(Throwables.getStackTraceAsString(tt));
				throw new Exception(Throwables.getStackTraceAsString(tt));
			}
		} catch (StaleElementReferenceException n) {
			fnsApps_Report_Logs("***** Progressing Loading image displayed for < " + Actual_Loading_Time
					+ " > seconds. **************** Stale");
		} catch (NoSuchWindowException W) {
			throw new Exception(W.getMessage());
		} catch (InterruptedException ie) {
			System.out.println("Interrupted-----Exception");
			fnsTake_Screen_Shot("PageLoadFail");
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(ie));
			throw new Exception(Throwables.getStackTraceAsString(ie));
		} catch (Throwable tt) {
			// isTestCasePass = false;
			fnsTake_Screen_Shot("PageLoadFail");
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(tt));
			throw new Exception(Throwables.getStackTraceAsString(tt));
		}
	}

	public static String fnsRemoveFormatting_for_FileName(String s) {

		s = s.replace("\\", " ");
		s = s.replace("/", " ");
		s = s.replace(":", " ");
		s = s.replace("*", " ");
		s = s.replace("?", " ");
		s = s.replace("<", " ");
		s = s.replace(">", " ");
		s = s.replace("|", " ");
		s = s.trim();

		return s;
	}

	// Function to wait for element Visible
	public static void WithOut_OR_fnGet_Element_Enabled(String XpathKey) throws Exception {

		try {
			for (int wait = 0; wait < 3; wait++) {
				if (driver.findElements(By.xpath(XpathKey)).size() > 0) {
					WebDriverWait elementwaitvar = new WebDriverWait(driver,
							Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
					elementwaitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XpathKey)));

					WebDriverWait elementwaitvar1 = new WebDriverWait(driver,
							Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
					elementwaitvar1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XpathKey)))
							.isEnabled();

					WebDriverWait elementwaitvar2 = new WebDriverWait(driver,
							Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
					elementwaitvar2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XpathKey)))
							.isDisplayed();

					break;
				} else {
					throw new Exception();
				} // else loop closed
			} // for loop Closed
			fnsApps_Report_Logs("PASSED == Element is Visible having Xpath  >> " + XpathKey);
		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (TimeoutException to) {
			throw new Exception("TimeOut : " + Throwables.getStackTraceAsString(to));
		} catch (Throwable t) {
			try {
				Thread.sleep(3000);
				WebDriverWait elementwaitvar3 = new WebDriverWait(driver,
						Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
				elementwaitvar3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XpathKey))).isEnabled();
				fnsApps_Report_Logs("PASSED == Element is Visible in 2nd attampt having Xpath  >> " + XpathKey);
			} catch (NoSuchWindowException W) {
				isTestCasePass = false;
				throw new Exception(W.getMessage());
			} catch (Throwable e) {
				isTestCasePass = false;
				fnsTake_Screen_Shot(XpathKey);
				fnsApps_Report_Logs("FAILED == Element is not Visible having Xpath  >> " + XpathKey
						+ ", plz see screenshot [ " + "" + XpathKey + fnsScreenShot_Date_format()
						+ " ]. Getting Exception >> " + Throwables.getStackTraceAsString(e));
				throw new Exception("FAILED == Element is not Visible having Xpath  [ " + XpathKey
						+ " ] , plz see screenshot [ " + "" + XpathKey + fnsScreenShot_Date_format()
						+ " ]. Getting Exception >> " + Throwables.getStackTraceAsString(e));
			}
		}
	}

	// Function to Take Screen Shot of I-Pulse Application Version
	public static void fnsIPulse_Application_Version(String message) throws Exception {
		try {
			WithOut_OR_fnGet_Element_Enabled(OR_CLNT.getProperty("Footer_IPulse"));
			Thread.sleep(1500);

			WithOut_OR_fnGet_Element_Enabled(OR_CLNT.getProperty("Ipulse_Version"));
			WebElement VersionWE = WithOut_OR_fnGet_ObjectX(OR_CLNT.getProperty("Ipulse_Version"));
			Actions action = new Actions(driver);
			action.moveToElement(VersionWE).sendKeys(VersionWE, Keys.CONTROL).click().build().perform();
			action.keyDown(Keys.CONTROL).build().perform();

			Thread.sleep(1500);

			FileUtils.forceMkdir(
					new File((System.getProperty("user.dir") + screenshots_path + "//applicationVersion//")));
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			Rectangle screenRectangle = new Rectangle(screenSize);
			Robot robot = new Robot();
			BufferedImage image = robot.createScreenCapture(screenRectangle);
			ImageIO.write(image, "png", new File((System.getProperty("user.dir") + screenshots_path
					+ "//applicationVersion//" + message + "_ApplicationVersion.PNG")));

			fnsWait_and_Click("Ipulse_Version_Dialog_Close_Bttn");
			Thread.sleep(1000);
		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (java.lang.NullPointerException n) {
			fnsApps_Report_Logs("ScreenShotNullPointerException >> " + n.getMessage());
			throw new Exception("NullPointerException Unable To take Screen Shots.");
		} catch (java.io.IOException e) {
			fnsApps_Report_Logs("ScreenShotIOException >> " + e.getMessage());
			throw new Exception("IOException Unable To take Screen Shots.");
		}
	}

	// function to select drop down value by using filter functionality
	public static void WithOut_OR_fnDD_SelectValue_Through_Filter(String ddClickXpathKey, String FilterXpath,
			String ExcetMatchingTextValue) throws Exception {

		boolean ValueNotMatched = true;
		try {
			WithOut_OR_fnGet_Element_Enabled(ddClickXpathKey);
			WithOut_OR_fnClick(ddClickXpathKey);
			Thread.sleep(1000);
			for (int i = 0; i < Long.parseLong(CONFIG.getProperty("ElementWaitTime")); i++) {
				if (driver.findElements(By.xpath(FilterXpath)).size() > 0) {
					// Thread.sleep(500);
					String MatchingValueXpath = "//li[text()='" + ExcetMatchingTextValue + "']";
					// WithOut_OR_fnGet_Element_Enabled(FilterXpath);
					WithOut_OR_fnGet_ObjectX(FilterXpath).sendKeys(ExcetMatchingTextValue);
					Thread.sleep(750);
					WithOut_OR_fnGet_Element_Enabled(MatchingValueXpath);
					WithOut_OR_fnGet_ObjectX(MatchingValueXpath).click();
					ValueNotMatched = false;
					break;
				} else {
					Thread.sleep(500);
				}
			}

			if (ValueNotMatched == true) {
				throw new Exception("Excel value is not matched with DropDown Value.");
			}
			fnsApps_Report_Logs("PASSED == select value [ " + ExcetMatchingTextValue
					+ " ] from drop down done, having xpath >>  " + ddClickXpathKey);
		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot("DdValueSelectFailWOR");
			fnsApps_Report_Logs("FAILED == Unable to select value from drop down [ " + ddClickXpathKey
					+ " ],plz see screenshot [ DdValueSelectFailWOR" + fnsScreenShot_Date_format() + " ]"
					+ ". Getting Exception  >> " + Throwables.getStackTraceAsString(t).trim());
			throw new Exception("FAILED == Unable to select value from drop down [ " + ddClickXpathKey
					+ " ],plz see screenshot [ DdValueSelectFailWOR" + fnsScreenShot_Date_format() + " ]"
					+ ". Getting Exception  >> " + Throwables.getStackTraceAsString(t).trim());
		}
	}

	// Return Year depends on Passing Value
	public static String fnsReturn_Requried_cal(String Requried_cal_Format) {
		Calendar cal = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat(Requried_cal_Format);
		String Demandcal = dateFormat.format(cal.getTime());
		return Demandcal;
	}

	// Function to click on lookup radio button ---- Satya Pal
	public static void fnsLookup_RadioBttn_Select() throws Exception {
		boolean TableSizeFlag = true;
		String lookup_title = "Lookup";
		try {
			try {
				lookup_title = driver.findElement(By.xpath("//span[@id='mainForm:lkpDialog_title']")).getText().trim();
			} catch (Throwable t) {
				// nothing to do
			}

			int beforeSearchRowCount = fnCount_table_Rows_BeforeTableRefresh("lookup_table_div");

			if (beforeSearchRowCount == 1) {
				fnsLoading_Progressingwait(1);
				driver.findElement(By.xpath(OR_CLNT.getProperty("lookup_select_radio_bttn"))).click();
				fnsLoading_Progressingwait(3);
				Thread.sleep(500);
				fnsApps_Report_Logs("PASSED == Successfully Select LookUp Radio Button");
				TableSizeFlag = false;
			}

			if (TableSizeFlag) {
				fnsLoading_Progressingwait(1);
				driver.findElement(By.xpath(OR_CLNT.getProperty("lookup_search_bttn"))).click();
				fnsLoading_Progressingwait(1);
				int afterSearchRow = beforeSearchRowCount;

				for (int waits = 0; beforeSearchRowCount == afterSearchRow; waits++) {
					afterSearchRow = fnCount_table_Rows_AfterTableRefresh("lookup_table_div");
					if (waits > (Long.parseLong(CONFIG.getProperty("Element_MAX_WaitTime")))) {
						break;
					}
				}

				fnsGet_Element_Enabled("lookup_select_radio_bttn");
				Thread.sleep(500);
				fnsLoading_Progressingwait(1);

				WithOut_OR_fnGet_ObjectX(OR_CLNT.getProperty("lookup_select_radio_bttn")).click();
				fnsLoading_Progressingwait(1);
				Thread.sleep(500);
			}
			fnsApps_Report_Logs("PASSED == Successfully Select " + lookup_title + "  Radio Button");
		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			isTestCasePass = false;
			// fnsTake_Screen_Shot_Without_OR("Lookup_Fail");
			fnsTake_Screen_Shot("Lookup_Fail");
			fnsApps_Report_Logs("FAILED == " + lookup_title + " : " + Throwables.getStackTraceAsString(t).trim()
					+ ", Please refer the screenshot >> Lookup_Fail" + fnsScreenShot_Date_format());
			throw new Exception("FAILED == " + lookup_title + " : " + Throwables.getStackTraceAsString(t).trim()
					+ ", Please refer the screenshot >> Lookup_Fail" + fnsScreenShot_Date_format());
		}
	}

	// Comman method to wirte pass/fail into excel/log file.
	public static void fns_ReportTestResult_atClassLevel(Xls_Reader Suite_Excel_Variable_Name, String Class_Name)
			throws Throwable {

		int rowNum = Suite_Excel_Variable_Name.getCellRowNum("Test Cases", "TCID", Class_Name);
		Suite_Excel_Variable_Name.setCellData("Test Cases", "Last_Execution", rowNum, fnReturnDateWithTimeForExcel());
		if (isTestCasePass) {
			TestUtil.reportDataSetResult(Suite_Excel_Variable_Name, "Test Cases",
					TestUtil.getRowNum(Suite_Excel_Variable_Name, Class_Name), "PASS");
			Suite_Excel_Variable_Name.setCellData("Test Cases", "Last_Successful_Execution", rowNum,
					fnReturnDateWithTimeForExcel());
			fnpElapsedTime(currentSuiteName, Class_Name, currentScriptCode, "PASS");
		} else {
			TestUtil.reportDataSetResult(Suite_Excel_Variable_Name, "Test Cases",
					TestUtil.getRowNum(Suite_Excel_Variable_Name, Class_Name), "FAIL");
			fnpElapsedTime(currentSuiteName, Class_Name, currentScriptCode, "FAIL");
		}
		fnsApps_Report_Logs(
				"=========================================================================================================================================");

		hashXlData.clear();
	}

	public static void MoveMouseCursorToTaskBar() {

		Actions testact = new Actions(driver);
		testact.moveByOffset(-1500, -1500).build().perform();
	}

	// Function to click on Object Without OR
	public static void WithOut_OR_fnClick(String XpathKey) throws Exception {

		try {
			try {
				WithOut_OR_fnGet_ObjectX(XpathKey).click();
				fnsApps_Report_Logs("PASSED == Click done on Element having Xpath >> " + XpathKey);
			} catch (Throwable tt) {
				Thread.sleep(3000);
				WithOut_OR_fnGet_ObjectX(XpathKey).click();
				fnsApps_Report_Logs(
						"(((((( 2nd Attampt ))))))---PASSED == Click done on Element having Xpath >> " + XpathKey);
			}
		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable e) {
			isTestCasePass = false;
			fnsTake_Screen_Shot("UnableToClick_");
			fnsApps_Report_Logs("FAILED == Unable To Click on Element having Xpath >> " + XpathKey
					+ ", plz see screenshot [ UnableToClick_" + fnsScreenShot_Date_format() + " ]"
					+ ". Getting Exception  >> " + Throwables.getStackTraceAsString(e));
			throw new Exception("Unable To Click on element [ " + XpathKey + " ] , plz see screenshot [ UnableToClick_"
					+ fnsScreenShot_Date_format() + " ]" + ". Getting Exception  >> "
					+ Throwables.getStackTraceAsString(e));
		}
	}

	// clicking on 'TAB Pagination NEXT link' until it will get disappear
	public static void fsnClickOn_Screen_TAB_Pagination_NEXT_Link_Till_DisAppear() throws Exception {
		try {
			if (driver.findElements(By.xpath(OR_CLNT.getProperty("Screen_TAB_PAgination_NEXT_Link"))).size() > 0) {
				WebElement NextLink = driver
						.findElement(By.xpath(OR_CLNT.getProperty("Screen_TAB_PAgination_NEXT_Link")));
				for (int i = 0; i < 10; i++) {
					Actions act = new Actions(driver);
					act.moveToElement(NextLink).click().build().perform();
					Thread.sleep(250);
				}
				Thread.sleep(1000);
			}
			fnsApps_Report_Logs("PASSED == Click done on 'TAB Pagination NEXT link'.");
		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable e) {
			isTestCasePass = false;
			fnsTake_Screen_Shot("TAB_Next_Link_");
			fnsApps_Report_Logs(
					"FAILED == Click fail on 'TAB Pagination NEXT link', please refer screenshot >> TAB_Next_Link__"
							+ fnsScreenShot_Date_format() + ". Getting Exception  >> "
							+ Throwables.getStackTraceAsString(e));
			throw new Exception(
					"FAILED == Click fail on 'TAB Pagination NEXT link', please refer screenshot >> TAB_Next_Link__"
							+ fnsScreenShot_Date_format() + ". Getting Exception  >> "
							+ Throwables.getStackTraceAsString(e));
		}
	}

	// function to select drop down value
	public static void WithOut_OR_fnsDD_value_Select_From_Filter_CheckBox(String ddClickXpathKey, String Value)
			throws Exception {

		try {
			boolean CheckBox_Click_Done = false;
			WithOut_OR_fnGet_Element_Enabled(ddClickXpathKey);
			WithOut_OR_fnGet_ObjectX(ddClickXpathKey).click();
			Thread.sleep(1000);

			String DD_Filter_Xpath = "//div[@class='ui-selectcheckboxmenu-filter-container']/input";
			for (int i = 1; i <= driver.findElements(By.xpath(DD_Filter_Xpath)).size(); i++) {
				String DD_Visible_Filter_Xpath = "(" + DD_Filter_Xpath + ")[" + i + "]";
				try {
					if (driver.findElement(By.xpath(DD_Visible_Filter_Xpath)).isDisplayed()) {
						WithOut_OR_fnGet_ObjectX(DD_Visible_Filter_Xpath).sendKeys(Value);
						Thread.sleep(1500);
						break;
					}
				} catch (Throwable t) {
					// nothing to do
				}
			}

			String DD_Value_Checkbox_Xpath = "//*[text()='" + Value + "']";
			for (int i = 1; i <= driver.findElements(By.xpath(DD_Value_Checkbox_Xpath)).size(); i++) {
				String DD_Visible_Checkbox_Xpath = "(" + DD_Value_Checkbox_Xpath + ")[" + i + "]";
				try {
					if (driver.findElement(By.xpath(DD_Visible_Checkbox_Xpath)).isDisplayed()) {
						WithOut_OR_fnGet_ObjectX(DD_Visible_Checkbox_Xpath).click();
						fnsLoading_Progressingwait(1);
						CheckBox_Click_Done = true;
						break;
					}
				} catch (Throwable t) {
					// nothing to do
				}
			}

			if (CheckBox_Click_Done == false) {
				fnsTake_Screen_Shot("DDValue_Select_Fail");
				throw new Exception("FAILED == Check Box Value <" + Value
						+ "> is not coming , after using FILTER, please refer screen shot >> DDValue_Select_Fail"
						+ fnsScreenShot_Date_format());
			}

			String DD_OK_Bttn_Xpath = "//a[@class='ui-selectcheckboxmenu-close ui-corner-all']";
			for (int i = 1; i <= driver.findElements(By.xpath(DD_OK_Bttn_Xpath)).size(); i++) {
				String DD_Visible_OK_Bttn_Xpath = "(" + DD_OK_Bttn_Xpath + ")[" + i + "]";
				try {
					if (driver.findElement(By.xpath(DD_Visible_OK_Bttn_Xpath)).isDisplayed()) {
						WithOut_OR_fnGet_ObjectX(DD_Visible_OK_Bttn_Xpath).click();
						fnsLoading_Progressingwait(1);
						break;
					}
				} catch (Throwable t) {
					// nothing to do
				}
			}

			fnsApps_Report_Logs("PASSED == Value < " + Value + " > selection done from drop down.");

		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			isTestCasePass = false;
			fnsApps_Report_Logs("Drop Down Value Selection Fail. Getting Exception  >> "
					+ Throwables.getStackTraceAsString(t).trim());
			throw new Exception("Drop Down Value Selection Fail. Getting Exception  >> "
					+ Throwables.getStackTraceAsString(t).trim());
		}
	}

	public static void switchUser(String switchTo) throws Throwable {
		fnsGet_Element_Enabled("Menu_Ajax_Link");
		WebElement Menu_Element = fnsGet_OR_CLNT_ObjectX("Menu_Ajax_Link");

		Actions action1 = new Actions(driver);
		action1.moveToElement(Menu_Element).build().perform();
		Thread.sleep(500);

		Actions action2 = new Actions(driver);
		fnsGet_Element_Enabled("SwitchUser_Ajax_Link");
		WebElement switchUserLinkElement = fnsGet_OR_CLNT_ObjectX("SwitchUser_Ajax_Link");
		action2.moveToElement(switchUserLinkElement).click().build().perform();

		fnsApps_Report_Logs("PASSED == Successfully Click on 'Switch User' Ajax Link.");
		fnsWait_and_Type("SwitchUser_inputTxt", switchTo);
		Thread.sleep(4000);
		WebElement GoButtonElement = fnsGet_OR_CLNT_ObjectX("GoButton_ForSwitchUser");
		action2.moveToElement(GoButtonElement).click().build().perform();
		fnsLoading_Progressingwait(1);
	}

	// ###################################################################
	// Configuration Function
	// ############################################################################
	// Function to Launch browser and login
	public static void fnsIpulse_Login_SSO(String PortalNamne, String LoginAs, String userName, String password)
			throws Throwable {
		LoginAs = LoginAs.trim();
		if (browserName.equalsIgnoreCase("edge")) {
			Autoit_scriptExeFile_Path = System.getProperty("user.dir") + "\\AutoIt_scripts\\sso_edge.exe";
		} else {
			Autoit_scriptExeFile_Path = System.getProperty("user.dir") + "\\AutoIt_scripts\\sso_chrome.exe";
		}

		try {
			String siteUrl = null;

			if (excelSiteURL == null) {
				siteUrl = CONFIG.getProperty("testSiteName");
			} else {
				siteUrl = excelSiteURL;
			}

			if ((PortalNamne.toLowerCase().trim()).equalsIgnoreCase("iPulse_NsfConnect")) {
				if (env.toLowerCase().equalsIgnoreCase("test")) {
					siteUrl = "https://testapps.nsf.org/trunkecap";
				} else if (env.toLowerCase().equalsIgnoreCase("staging")) {
					siteUrl = "https://stgapps.nsf.org/ecap/";
				}
			}

			fnsApps_Report_Logs("Application credentials are URL[ " + siteUrl + " ], UserName[ " + userName + " ].");
			driver.get(siteUrl);

			fnsLoading_PageLoad();
			WithOut_OR_fnGet_Element_Enabled(OR_MtrPlan.getProperty("MicrosoftOnline_Signin_Input"));
			fnsWait_and_Type("MicrosoftOnline_Signin_Input", userName + "@nsf.org");

			WithOut_OR_fnGet_Element_Enabled(OR_MtrPlan.getProperty("MicrosoftOnline_Signin_Next"));
			WithOut_OR_fnClick(OR_MtrPlan.getProperty("MicrosoftOnline_Signin_Next"));
			fnsLoading_PageLoad();
			fnsLoading_PageLoad();
			String Screen_URL = "";
			for (int i = 1; i <= 120; i++) {
				Screen_URL = driver.getCurrentUrl().toLowerCase().trim();
				System.out.println(Screen_URL);
				if (Screen_URL.contains("login.microsoftonline.com")) {
					Thread.sleep(1000);
				} else {
					break;
				}
				if (i == 120) {
					fnsTake_Screen_Shot("SSO_Not_Working");
					fnsApps_Report_Logs(
							"FAILED == After Clicking on NEXT button from 'login.microsoftonline.com' screen, the application is not navigated to next screen (Wait Time = 120 seconds), please refer the screenshot >> SSO_Not_Working "
									+ fnsScreenShot_Date_format());
					throw new Exception(
							"FAILED == After Clicking on NEXT button from 'login.microsoftonline.com' screen, the application is not navigated to next screen (Wait Time = 120 seconds), please refer the screenshot >> SSO_Not_Working "
									+ fnsScreenShot_Date_format());
				}
			}
			fnsLoading_PageLoad();
			// Screen_URL = driver.getCurrentUrl().toLowerCase().trim();
			// if(Screen_URL.contains("auth.nsf.org")){
			// Runtime.getRuntime().exec(Autoit_scriptExeFile_Path);
			// fnsApps_Report_Logs("PASSED == ********* Successfulluy Executed the Autoit
			// script for SSO login having exe file path [ "+Autoit_scriptExeFile_Path+"
			// ]");
			//
			// for(int i=1; i<=120; i++){
			// String Screen_Title = driver.getTitle().trim();
			// System.out.println(Screen_Title);
			// if(Screen_Title.equalsIgnoreCase("i-pulse")){
			// break;
			// }else{
			// Thread.sleep(1000);
			// }
			// if(i==120){
			// fnsTake_Screen_Shot("SSO_Not_Working");
			// fnsApps_Report_Logs("FAILED == After Clicking on login button from
			// 'auth.nsf.org' screen, the application is not navigated to i-Pulse screen
			// (Wait Time = 120 seconds), please refer the screenshot >> SSO_Not_Working
			// "+fnsScreenShot_Date_format());
			// throw new Exception ("FAILED == After Clicking on login button from
			// 'auth.nsf.org' screen, the application is not navigated to i-Pulse screen
			// (Wait Time = 120 seconds), please refer the screenshot >> SSO_Not_Working
			// "+fnsScreenShot_Date_format());
			// }
			// }
			// }else{
			// for(int i=1; i<=120; i++){
			// String Screen_Title = driver.getTitle().trim();
			// System.out.println(Screen_Title);
			// if(Screen_Title.equalsIgnoreCase("i-pulse")){
			// break;
			// }else{
			// Thread.sleep(1000);
			// }
			// if(i==120){
			// fnsTake_Screen_Shot("SSO_Not_Working");
			// fnsApps_Report_Logs("FAILED == After Clicking on NEXT button from
			// 'login.microsoftonline.com' screen, the application is not navigated to
			// i-Pulse screen (Wait Time = 120 seconds), please refer the screenshot >>
			// SSO_Not_Working "+fnsScreenShot_Date_format());
			// throw new Exception ("FAILED == After Clicking on NEXT button from
			// 'login.microsoftonline.com' screen, the application is not navigated to
			// i-Pulse screen (Wait Time = 120 seconds), please refer the screenshot >>
			// SSO_Not_Working "+fnsScreenShot_Date_format());
			// }
			// }
			// }

			fnsGet_Element_Enabled("UserNametext_Xpath");
			fnsWait_and_Type("passwordtext_Xpath", password);

			fnsWait_and_Click("SignInBtn_xpath");

			// Thread.sleep(10000);

			if (driver.findElements(By.xpath(OR.getProperty("StaySignedInYes_Btn"))).size() > 0) {
				fnsApps_Report_Logs("Stay signed in Pop up is displayed.");
				fnsWait_and_Click("StaySignedInYes_Btn");
			}

			Thread.sleep(1000);

			fnsLoading_PageLoad();
			fnsLoading_Progressingwait(1);
			fnsComman_BrowserLogin_ElementWait(OR_MtrPlan.getProperty("Acknowledge_bttn"),
					CONFIG.getProperty("Element_MAX_WaitTime"));
			fnsGet_Element_Enabled("Acknowledge_bttn");
			Thread.sleep(2000);
			fnsWait_and_Click("Acknowledge_bttn");
			fnsLoading_Progressingwait(4);

			if (LoginAs.equalsIgnoreCase("testscriptuser")) {
				LoginAs = "";
			}
			if ((!(LoginAs.equalsIgnoreCase("")))) {

				try {
					WithOut_OR_fnGet_Element_Enabled(OR_MtrPlan.getProperty("Menu_Ajax_Link"));
					WebElement Menu_Element = WithOut_OR_fnGet_ObjectX(OR_MtrPlan.getProperty("Menu_Ajax_Link"));

					WebElement VersionLogoImage = WithOut_OR_fnGet_ObjectX(OR_MtrPlan.getProperty("VersionLogoImage"));
					VersionLogoImage.click();
					Thread.sleep(500);

					Actions action1 = new Actions(driver);
					action1.moveToElement(Menu_Element).build().perform();

					Thread.sleep(500);

					Actions act1 = new Actions(driver);
					WithOut_OR_fnGet_Element_Enabled(OR_MtrPlan.getProperty("Admin_Switch_User_Menu"));
					WebElement SwitchUserMenu = WithOut_OR_fnGet_ObjectX(
							OR_MtrPlan.getProperty("Admin_Switch_User_Menu"));
					act1.moveToElement(SwitchUserMenu).click().build().perform();
					fnsApps_Report_Logs("PASSED == Successfully click on 'Switch User' menu link");

				} catch (NoSuchWindowException W) {
					isTestCasePass = false;
					throw new Exception(W.getMessage());
				} catch (Throwable t) {
					isTestCasePass = false;
					fnsTake_Screen_Shot("SwitchUserMenu_Click_Fail");
					fnsApps_Report_Logs("FAILED == Clicking on 'Switch User' menu link Failed, plz see screenshot [ "
							+ "SwitchUserMenu_Click_Fail" + fnsScreenShot_Date_format() + "]"
							+ ". Getting Exception  >> " + Throwables.getStackTraceAsString(t).trim());
					throw new Exception("Clicking on 'Switch User' menu link Failed, plz see screenshot [ "
							+ "SwitchUserMenu_Click_Fail" + fnsScreenShot_Date_format() + "]"
							+ ". Getting Exception  >> " + Throwables.getStackTraceAsString(t).trim());
				}

				fnsLoading_PageLoad();
				fnsLoading_Progressingwait(1);
				WithOut_OR_fnGet_Element_Enabled(OR_MtrPlan.getProperty("ipulse_SwitchUser_inpit"));
				fnsWait_and_Type("ipulse_SwitchUser_inpit", LoginAs);

				WithOut_OR_fnGet_Element_Enabled(OR_MtrPlan.getProperty("ipulse_SwitchUser_GO_button"));
				WithOut_OR_fnClick(OR_MtrPlan.getProperty("ipulse_SwitchUser_GO_button"));
				fnsLoading_PageLoad();
				fnsLoading_Progressingwait(2);
				fnsLoading_Progressingwait(1);
			}
		} catch (Throwable t) {
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}

	public static Integer fnsLoading_PageLoad() throws Throwable {
		Integer PageWait = 0;
		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			for (int k = 2; k <= Long.parseLong(CONFIG.getProperty("Element_MAX_WaitTime")); k++) {
				String Text = (String) jse.executeScript("return document.readyState");// .equals("complete");
				if (Text.equals("complete")) {
					// fnsApps_Report_Logs("Page is loaded in '"+k+"' seconds.");
					PageWait = k;
					break;
				} else {
					Thread.sleep(1000);
				}
				if (k == Long.parseLong(CONFIG.getProperty("Element_MAX_WaitTime"))) {
					fnsTake_Screen_Shot("PageLoad_Fail");
					throw new Exception("FAIL == after '" + Long.parseLong(CONFIG.getProperty("Element_MAX_WaitTime"))
							+ "' seconds wait page is not loading.");
				}
			}
		} catch (Throwable t) {
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		return PageWait;
	}

}
