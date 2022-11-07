package nsf.ecap.MonitorPlan_Suite;

import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.google.common.base.Throwables;

import io.github.bonigarcia.wdm.WebDriverManager;
import nsf.ecap.base.TestBase;
import nsf.ecap.util.ErrorUtil;
import nsf.ecap.util.TestUtil;
import nsf.ecap.util.Xls_Reader;

public class TestSuiteBase_MonitorPlan extends TestBase /* TestListenerAdapter */ {
	public String Autoit_scriptExeFile_Path = "";
	public String classNameText = null;
	public String Connection_Name = null;
	public String validation_error_msg_text;
	// public boolean isTestPass = true;
	boolean fail = false;
	public String SearchResult_Code_link_Xpath;
	public String TextAfterRemvoalSpeChar;
	public String runmodes[] = null;
	public static boolean ScreenShotFlagWithOR_MP = false;
	public boolean WO_Drop_Bs_02_Flag = true;
	// public Xls_Reader MonitorPlan_suitexls = null;

	public boolean ApplicationVersion_Flag = true;
	public boolean isTestCasePass = true;
	// public Properties OR_MtrPlan = null;
	public Integer RowCount;
	public Integer Column_Number;

	public String Fetched_Text = null;

	String Facility_ID = null;

	public ArrayList<String> Loading_Xpath_Array = new ArrayList<String>();
	public String Loading_Image_Xpath = null;
	public boolean LoadingImageFlag = true;
	public String ErrorMsgText = null;
	public Integer Actual_Loading_Time;
	public Integer TimeOut;
	public String PageSourceText = null;
	public boolean TraQtionOnline_LoadingImageFlag = true;
	public ChromeOptions options;
	public DesiredCapabilities caps;
	public static double ScreenWidth;
	public static double ScreenHeight;
	public long TC_Step = 0;

	// Excel Variables
	public String ANNLIST_Task_Details = null;
	public String First_PROFSVC_Task_Details = null;
	public String Second_PROFSVC_Task_Details = null;
	public String Third_PROFSVC_Task_Details = null;
	public String First_PROFSVC_BillingCode = null;
	public String First_PROFSVC_QuanityValue = null;
	public String Add_BillCode_Data = null;
	public String Testing_Task_Details;// =null;
	public String EPSF_CollectionNotesSource_DD = null;
	public String TaskTab_TaskList_DropReason_TextBox = null;
	public String InfoTab_StatusChangeNote_TextField = null;
	public String SearchWorkOrder_WOStatus_DDValue = null;
	public String CertDecider_DDValue = null;
	public String Testing_Task_DD_Default_Value_Match = null;
	// ######################################################### Common Functions
	// #######################################################################

	// Function to Close monitor Plan for specific client.
	public void fnUpdateDB_By_Executing_Procedure_ToCloseMonitorPlan(String Facility_ID) throws Throwable {

		fnsApps_Report_Logs(
				"=========================================================================================================================================");
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			fnsApps_Report_Logs(
					"*********************************************** Oracle JDBC Driver Registered *********************************************");
		} catch (ClassNotFoundException e) {
			fnsApps_Report_Logs(
					"*********************************************** Oracle JDBC Driver is not found ******************************************");
			throw new Exception(
					"*********************************************** Oracle JDBC Driver is not found ******************************************");
		}

		Connection connection = null;
		try {
			/*
			 * if (env.toLowerCase().equalsIgnoreCase("test")){
			 * connection =
			 * DriverManager.getConnection("jdbc:oracle:thin:@dbserv13vm:1521:certtest",
			 * "testscriptuser", "test4nsf");
			 * }else if(env.toLowerCase().equalsIgnoreCase("staging")){
			 * connection =
			 * DriverManager.getConnection("jdbc:oracle:thin:@dbserv30:1521:CERTSTAG",
			 * "testscriptuser", "welcome1010");
			 * }else{
			 * throw new Exception ("FAILED == Environment variable is not defined.");
			 * }
			 */

			connection = fnpGetDBConnection();

			Statement stmt = connection.createStatement();
			CallableStatement stproc_stmt = connection.prepareCall("{call EC_PKG_MONITOR_PLAN.PRO_DELETE_PLAN(?)}");
			// stproc_stmt.setInt(1, 36440); //Commented on 3.11.2016 also commented
			// EPSFFields_ProdTrack_LK_Bttn in create MP
			stproc_stmt.setString(1, Facility_ID);
			stproc_stmt.executeUpdate();
			fnsApps_Report_Logs("Successfully executed DB Procedure:- To Close Monitor Plan");
			connection.close();

		} catch (SQLException e) {
			fnsApps_Report_Logs("***********************************************  Getting Error >>  "
					+ Throwables.getStackTraceAsString(e).trim());
			throw new Exception("***********************************************  Getting Error >>  "
					+ Throwables.getStackTraceAsString(e).trim());
		} finally {
			if (!(connection == null)) {
				connection.close();
			}
		}
		fnsApps_Report_Logs(
				"=========================================================================================================================================");
	}

	/*
	 * 
	 * public void fnWait(long time1) throws Throwable {
	 * if(BrowserDriver==true){
	 * Thread.sleep(time1);}
	 * else{ Nothing to Do }
	 * }
	 */

	public String fnsRemoveFormatting_for_FileName(String s) {

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

	// Function to Take Screen Shot.
	public void fnsTake_Screen_Shot(String message) throws Exception {
		String MessageAfterFormat = fnsRemoveFormatting_for_FileName(message);
		try {
			String Suite_Foler_Name = "screenshots_MP";
			String File_Name = MessageAfterFormat + "_" + fnsScreenShot_Date_format() + ".PNG";
			FileUtils.forceMkdir(new File((System.getProperty("user.dir") + screenshots_path + "//" + Suite_Foler_Name
					+ "//" + currentScriptCode + "//")));
			File image = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(image, new File((System.getProperty("user.dir") + screenshots_path + "//"
					+ Suite_Foler_Name + "//" + currentScriptCode + "//" + File_Name)));

			/*
			 * FileUtils.forceMkdir(new File((System.getProperty("user.dir") +
			 * screenshots_path+ "//screenshots_MP//"+currentScriptCode +"//")));
			 * Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			 * Rectangle screenRectangle = new Rectangle(screenSize);
			 * Robot robot = new Robot();
			 * BufferedImage image = robot.createScreenCapture(screenRectangle);
			 * ImageIO.write(image, "png", new File((System.getProperty("user.dir") +
			 * screenshots_path+ "//screenshots_MP//"+currentScriptCode
			 * +"//"+MessageAfterFormat+"_"+fnsScreenShot_Date_format()+".PNG")));
			 */
		} catch (java.lang.NullPointerException n) {
			fnsApps_Report_Logs("ScreenShotNullPointerException >> " + n.getMessage());
			throw new Exception("NullPointerException Unable To take Screen Shots.");
		} catch (java.io.IOException e) {
			fnsApps_Report_Logs("ScreenShotIOException >> " + Throwables.getStackTraceAsString(e));
			throw new Exception("IOException Unable To take Screen Shots.");
		}
	}

	// Take screen without for OR Elements
	@SuppressWarnings("static-access")
	public void fnsTake_Screen_Shot_Without_OR(String message) throws Exception {
		try {
			if (ScreenShotFlagWithOR_MP) {
				fnsTake_Screen_Shot(message);
			}

			/*
			 * if(TestSuiteBase_IM.ScreenShotFlagWithOR_IM){
			 * TestSuiteBase_IM.fnTake_Screen_Shot(message); }
			 * 
			 * if(TestSuiteBase_CLNT.ScreenShotFlagWithOR_CLNT){
			 * TestSuiteBase_CLNT.fnsTake_Screen_Shot(message); }
			 */

			if (TestSuiteBase_NSFOnline.ScreenShotFlagWithOR_NsfOnline) {
				TestSuiteBase_NSFOnline.fnsTake_Screen_Shot(message);
			}

			if (TestSuiteBase_TraQtion.ScreenShotFlagWithOR_TraQtion) {
				TestSuiteBase_TraQtion.fnsTake_Screen_Shot(message);
			}

			if (TestSuiteBase_Grip.ScreenShotFlagWithOR_Grip) {
				TestSuiteBase_Grip.fnsTake_Screen_Shot(message);
			}

			if (TestSuiteBase_New_NSFOnline.ScreenShotFlagWithOR_New_NSFOnline) {
				TestSuiteBase_New_NSFOnline.fnsTake_Screen_Shot(message);
			}

			if (TestSuiteBase_Alerts.ScreenShotFlagWithOR_Alerts_iPulse) {
				TestSuiteBase_Alerts.fnsTake_Screen_Shot(message);
			}

			if (TestSuiteBase_Listings.ScreenShotFlagWithOR_Listings) {
				TestSuiteBase_Listings.fnsTake_Screen_Shot(message);
			}

		} catch (Throwable n) {
			fnsApps_Report_Logs(n.getMessage());
			throw new Exception(n.getMessage());
		}

	}

	// Function for Application Log Date format
	public String fnsLOGS_Date_format() {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date date = new Date();
		return (dateFormat.format(date));
	}

	// Function for MonitorPlan Creation Text field Date Format
	public String fnsMonitorPlanCreationText_Date_format() {
		DateFormat dateFormat = new SimpleDateFormat("ddMMMyyyy/HH:mm z");
		Date date = new Date();
		return (dateFormat.format(date));
	}

	// Function for Screen date format
	public String fnsScreenShot_Date_format() {
		DateFormat dateFormat = new SimpleDateFormat("_dd.MM.yyyy_HH.mm.ss");
		Date date = new Date();
		return (dateFormat.format(date));
	}

	// Function for Screen date format
	public String fnsEditMonitorPlan_Date_format() {
		DateFormat dateFormat = new SimpleDateFormat("MMddyyyy");
		Date date = new Date();
		return (dateFormat.format(date));
	}

	/*
	 * //Function For Application Log
	 * public void fnsApps_Report_Logs(String LogMessage) {
	 * if(TestSuiteBase_New_NSFOnline.ScreenShotFlagWithOR_New_NSFOnline){
	 * TestSuiteBase_New_NSFOnline.fnsApps_Report_Logs(LogMessage);
	 * }else{
	 * LogMessage = LogMessage.replaceAll("�", "");
	 * LogMessage = LogMessage.replaceAll("�", "");
	 * 
	 * APP_LOGS.debug(LogMessage);
	 * System.out.println(LogMessage);
	 * Reporter.log(fnsLOGS_Date_format() + "  " + LogMessage);
	 * }
	 * }
	 */

	// Function For Application Log
	public void fnsApps_Report_Logs(String LogMessage) {

		LogMessage = LogMessage.replaceAll("�", "");
		LogMessage = LogMessage.replaceAll("�", "");

		if ((LogMessage.toLowerCase().contains("pause/wait"))) {
			// noting to do
		} else if (((LogMessage.toLowerCase().contains("failed")) || (LogMessage.toLowerCase().contains("java"))
				|| (LogMessage.toLowerCase().contains("webdriver")) || (LogMessage.toLowerCase().contains("assert")))
				&& !(LogMessage.toLowerCase().contains("with failed"))) {
			Reporter.log(" |");
			Reporter.log(" |");
			Reporter.log(" | Script has been failed, after performing the Step " + (TC_Step - 1)
					+ " , Please refer the below error....");
			Reporter.log(" |");
			Reporter.log(fnsLOGS_Date_format() + " | " + LogMessage);
		} else if (((LogMessage.toLowerCase().contains("success")) || (LogMessage.toLowerCase().contains("load"))
				|| (LogMessage.toLowerCase().contains("click")) || (LogMessage.toLowerCase().contains("type"))
				|| (LogMessage.toLowerCase().contains("select")) || (LogMessage.toLowerCase().contains("button"))
				|| (LogMessage.toLowerCase().contains("query"))) && !(LogMessage.toLowerCase().contains("move"))
				|| (LogMessage.toLowerCase().contains("url")) || (LogMessage.toLowerCase().contains("automation"))) { // Steps
																														// Log
			if (!((LogMessage.toLowerCase().contains("success")) || (LogMessage.toLowerCase().contains("loading"))
					|| (LogMessage.toLowerCase().contains("test case")))
					|| (LogMessage.toLowerCase().contains("select")) || (LogMessage.toLowerCase().contains("menu"))
					|| (LogMessage.toLowerCase().contains("url")) || (LogMessage.toLowerCase().contains("button"))) {
				LogMessage = "STEP " + TC_Step + " *** " + LogMessage;
				Reporter.log(fnsLOGS_Date_format() + " | ");
				Reporter.log(fnsLOGS_Date_format() + " | " + LogMessage);
				TC_Step++;
			} else {
				Reporter.log(fnsLOGS_Date_format() + " | ********** " + LogMessage);
			}

		} else if ((LogMessage.toLowerCase().contains("[bs-")) || (LogMessage.toLowerCase().contains("execution"))
				|| (LogMessage.toLowerCase().contains("runmode"))) {
			Reporter.log(
					"========================================================================================================================================");
			Reporter.log(LogMessage);
			Reporter.log(" ");
		}
		APP_LOGS.debug(LogMessage);
		System.out.println(LogMessage);
	}

	// Function to Return 2days back date from past (day before Yesterday)
	public String fnsGet_2Days_Past_date() throws Exception {

		Calendar cal = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy - HH:mm");
		cal.add(Calendar.DATE, -2);
		String Past_date = dateFormat.format(cal.getTime()).split("-")[0].trim();

		return Past_date;
	}

	// Function to Return current system time
	public String fnsGet_Current_Time() throws Exception {
		Calendar cal = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy - HH:mm");
		cal.add(Calendar.DATE, -2);
		String current_time = dateFormat.format(cal.getTime()).split("-")[1].trim();

		return current_time;
	}

	// Return Year depends on Passing Value
	public String fnsReturn_Requried_cal(String Requried_cal_Format) {
		Calendar cal = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat(Requried_cal_Format);
		String Demandcal = dateFormat.format(cal.getTime());
		return Demandcal;
	}

	// Function to find and return the object using Xpath
	public WebElement fnsGet_OR_MtrPlan_ObjectX(String XpathKey) throws Exception {

		try {
			for (int waits = 0; waits < Long.parseLong(CONFIG.getProperty("ElementWaitTime")); waits++) {

				if (driver.findElements(By.xpath(OR_MtrPlan.getProperty(XpathKey))).size() > 0) {
					break;
				} else {
					Thread.sleep(500);
				}

			}
			return driver.findElement(By.xpath(OR_MtrPlan.getProperty(XpathKey)));

		} catch (StaleElementReferenceException e) {
			Thread.sleep(2000);
			WebDriverWait stalewaitvar = new WebDriverWait(driver, 10);
			stalewaitvar.until(ExpectedConditions.presenceOfElementLocated(By.xpath(OR_MtrPlan.getProperty(XpathKey))));
			WebElement webelemnt = driver.findElement(By.xpath(OR_MtrPlan.getProperty(XpathKey)));
			stalewaitvar.until(ExpectedConditions.stalenessOf(webelemnt));
			return driver.findElement(By.xpath(OR_MtrPlan.getProperty(XpathKey)));
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
	public void fnsWait_and_Click(String XpathKey) throws Exception {

		try {
			try {
				fnsGet_OR_MtrPlan_ObjectX(XpathKey).click();
				fnsApps_Report_Logs("PASSED == Click done on Element having Xpath >> " + XpathKey);
			} catch (Throwable tt) {
				Thread.sleep(3000);
				fnsGet_OR_MtrPlan_ObjectX(XpathKey).click();
				fnsApps_Report_Logs(
						"(((((( 2nd Attampt ))))))---PASSED == Click done on Element having Xpath >> " + XpathKey);
			}
		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable e) {
			isTestCasePass = false;
			fnsTake_Screen_Shot("UnableToClick_" + XpathKey);
			fnsApps_Report_Logs("FAILED == Unable To Click on Element having Xpath >> " + XpathKey
					+ ", plz see screenshot [ UnableToClick_" + XpathKey + fnsScreenShot_Date_format() + " ]"
					+ ". Getting Exception  >> " + Throwables.getStackTraceAsString(e));
			throw new Exception("Unable To Click on element [ " + XpathKey + " ] , plz see screenshot [ UnableToClick_"
					+ XpathKey + fnsScreenShot_Date_format() + " ]" + ". Getting Exception  >> "
					+ Throwables.getStackTraceAsString(e));
		}
	}

	// Function to wait for element and then type
	public void fnsWait_and_Type(String XpathKey, String value) throws Exception {

		try {
			try {
				fnsGet_OR_MtrPlan_ObjectX(XpathKey).sendKeys("");
				fnsGet_OR_MtrPlan_ObjectX(XpathKey).sendKeys(value);
				fnsApps_Report_Logs(
						"PASSED == Type Value [ " + value + " ] done on Element having Xpath  >> " + XpathKey);
			} catch (Throwable tt) {
				Thread.sleep(3000);
				fnsGet_OR_MtrPlan_ObjectX(XpathKey).sendKeys("");
				fnsGet_OR_MtrPlan_ObjectX(XpathKey).sendKeys(value);
				fnsApps_Report_Logs("(((((( 2nd Attampt ))))))---PASSED == Type Value [ " + value
						+ " ] done on Element having Xpath  >> " + XpathKey);
			}
		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable e) {
			isTestCasePass = false;
			fnsTake_Screen_Shot("UnableToType_" + XpathKey);
			fnsApps_Report_Logs("FAILED == Unable To Type Value<" + value + "> on Element having Xpath  >> " + XpathKey
					+ ", plz see screenshot [ UnableToType_" + XpathKey + fnsScreenShot_Date_format() + " ]"
					+ ". Getting Exception  >> " + Throwables.getStackTraceAsString(e));
			throw new Exception("Unable To Type Value<" + value + "> on element [ " + XpathKey
					+ " ] , plz see screenshot [ UnableToType_" + XpathKey + fnsScreenShot_Date_format() + " ]"
					+ ". Getting Exception  >> " + Throwables.getStackTraceAsString(e));
		}
	}

	// Function to Clear Field
	public void fnsWait_and_Clear(String XpathKey) throws Exception {

		try {
			Thread.sleep(500);
			fnsGet_OR_MtrPlan_ObjectX(XpathKey).clear();
			fnsApps_Report_Logs("PASSED == Clear Element done, having Xpath  >> " + XpathKey);
		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable e) {
			isTestCasePass = false;
			fnsTake_Screen_Shot("UnableToClear_" + XpathKey);
			fnsApps_Report_Logs("FAILED == Unable To performe Clear on Element having Xpath  >> " + XpathKey
					+ ", plz see screenshot [ UnableToClear_" + XpathKey + fnsScreenShot_Date_format() + " ]"
					+ ". Getting Exception  >> " + Throwables.getStackTraceAsString(e));
			throw new Exception("Unable To performe Clear on element [ " + XpathKey
					+ " ] , plz see screenshot [ UnableToClear_" + XpathKey + fnsScreenShot_Date_format() + " ]"
					+ ". Getting Exception  >> " + Throwables.getStackTraceAsString(e));
		}
	}

	// Function to wait for element
	public void fnsGet_Element_Enabled(String XpathKey) throws Exception {

		try {
			for (int wait = 0; wait < 2; wait++) {
				if (driver.findElements(By.xpath(OR_MtrPlan.getProperty(XpathKey))).size() > 0) {
					fnsGet_OR_MtrPlan_ObjectX(XpathKey);
					WebDriverWait elementwaitvar = new WebDriverWait(driver,
							Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
					elementwaitvar.until(
							ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_MtrPlan.getProperty(XpathKey))));

					WebDriverWait elementwaitvar1 = new WebDriverWait(driver,
							Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
					elementwaitvar1
							.until(ExpectedConditions
									.visibilityOfElementLocated(By.xpath(OR_MtrPlan.getProperty(XpathKey))))
							.isEnabled();

					WebDriverWait elementwaitvar2 = new WebDriverWait(driver,
							Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
					elementwaitvar2
							.until(ExpectedConditions
									.visibilityOfElementLocated(By.xpath(OR_MtrPlan.getProperty(XpathKey))))
							.isDisplayed();

					break;
				} // if loop closed
				else {
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
				elementwaitvar3.until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_MtrPlan.getProperty(XpathKey))))
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
	public void fnsMove_To_Element_and_Click(String XpathKey) throws Exception {

		try {
			Actions action = new Actions(driver);
			WebElement MonitorPlanTypeElement = fnsGet_OR_MtrPlan_ObjectX(XpathKey);
			action.moveToElement(MonitorPlanTypeElement).click().build().perform();
			fnsApps_Report_Logs("PASSED == Move and Click done over Element having Xpath >> " + XpathKey);

		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable e) {
			isTestCasePass = false;
			fnsTake_Screen_Shot("" + XpathKey);
			fnsApps_Report_Logs("FAILED == Unable to MoveToElement and Click, having Xpath  >> " + XpathKey
					+ ", plz see screenshot [ " + "" + XpathKey + fnsScreenShot_Date_format() + " ]");
			throw new Exception("FAILED == Unable to MoveToElement and Click, having Xpath  [ " + XpathKey
					+ " ] , plz see screenshot [ " + "" + XpathKey + fnsScreenShot_Date_format() + " ]");
		}
	}

	// Function to wait for element and then type
	public void fnsMove_To_Element(String XpathKey) throws Exception {

		try {
			Actions action = new Actions(driver);
			WebElement MonitorPlanTypeElement = fnsGet_OR_MtrPlan_ObjectX(XpathKey);
			action.moveToElement(MonitorPlanTypeElement).build().perform();
			fnsApps_Report_Logs("PASSED == Move over Element done having Xpath >> " + XpathKey);
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
	public void fnsMove_To_Element_and_DoubleClick(String XpathKey) throws Exception {

		try {
			Actions action = new Actions(driver);
			WebElement MonitorPlanTypeElement = fnsGet_OR_MtrPlan_ObjectX(XpathKey);
			action.moveToElement(MonitorPlanTypeElement).doubleClick().build().perform();
			fnsApps_Report_Logs("PASSED == Double Click done over Element having Xpath >> " + XpathKey);
		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable e) {
			isTestCasePass = false;
			fnsTake_Screen_Shot("" + XpathKey);
			fnsApps_Report_Logs("FAILED == Unable to MoveToElement and Click, having Xpath  >> " + XpathKey
					+ ", plz see screenshot [ " + "" + XpathKey + fnsScreenShot_Date_format() + " ]");
			throw new Exception("FAILED == Unable to MoveToElement and Click, having Xpath  [ " + XpathKey
					+ " ] , plz see screenshot [ " + "" + XpathKey + fnsScreenShot_Date_format() + " ]");
		}
	}

	// Function to Return current system time
	public void fnsPage_load_Wait() throws Exception {

		try {
			for (int wait = 0; wait < Long.parseLong(CONFIG.getProperty("ElementWaitTime")); wait++) {
				System.out.println("Before Displayed" + fnsGet_Current_Time());
				if (driver.findElement(By.xpath(OR_MtrPlan.getProperty("LookupLoadingImg"))).isDisplayed()) {
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
					+ fnsScreenShot_Date_format() + " ]." + ". Getting Exception  >> "
					+ Throwables.getStackTraceAsString(t));
			throw new Exception("FAILED == Page is still loading, Plase refer screenshot [" + "PageLoadFail"
					+ fnsScreenShot_Date_format() + " ]." + ". Getting Exception  >> "
					+ Throwables.getStackTraceAsString(t));
		}
	}

	/*
	 * //Click on Link by matchingText Xpath
	 * public void fnsTable_ClickOn_LINK_ByMatchingText(String LinkMatchingText)
	 * throws Throwable{
	 * 
	 * try{
	 * SearchResult_Code_link_Xpath = ".//a[(text()='" + LinkMatchingText.trim() +
	 * "')]";
	 * WithOut_OR_fnGet_Element_Enabled(SearchResult_Code_link_Xpath);
	 * WithOut_OR_fnGet_ObjectX(SearchResult_Code_link_Xpath).click();
	 * fnsApps_Report_Logs("PASSED == click done on the Link having xpath >> "
	 * +SearchResult_Code_link_Xpath);
	 * }catch(NoSuchWindowException W){
	 * isTestCasePass = false;
	 * throw new Exception(W.getMessage()); }
	 * catch(Throwable t){
	 * isTestCasePass = false;
	 * fnsTake_Screen_Shot_Without_OR("LinkClickFail");
	 * fnsApps_Report_Logs("FAILED == Unable to Click on Link having Xpath >> "
	 * +SearchResult_Code_link_Xpath+", Please refer ScreenShot [ "+"LinkClickFail"+
	 * fnsScreenShot_Date_format()
	 * +" ]."+". Getting Exception  >> "+Throwables.getStackTraceAsString(t));
	 * throw new Exception("FAILED == Unable to Click on Link having Xpath >> "
	 * +SearchResult_Code_link_Xpath+", Please refer ScreenShot [ "+"LinkClickFail"+
	 * fnsScreenShot_Date_format() +" ]."+Throwables.getStackTraceAsString(t));}
	 * }
	 */

	// Click on Link by matchingText Xpath // used in link tab =dd and facility link
	// click //above method commented as new fucntionality came 'Recent menu'
	public void fnsTable_ClickOn_LINK_ByMatchingText(String LinkMatchingText) throws Throwable {
		String SearchResult_Facility_link_Xpath = "";
		try {
			boolean Faility_Link_Click_Done = false;
			String Facility_link_Xpath = "//a[text()='" + LinkMatchingText + "']";
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
			fnsLoading_Progressingwait(2);

		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot_Without_OR("LinkClickFail");
			fnsApps_Report_Logs("FAILED == Unable to Click on Link having Xpath >> " + SearchResult_Facility_link_Xpath
					+ ", Please refer ScreenShot [ " + "LinkClickFail" + fnsScreenShot_Date_format() + " ]."
					+ ". Getting Exception  >> " + Throwables.getStackTraceAsString(t));
			throw new Exception("FAILED == Unable to Click on Link having Xpath >> " + SearchResult_Facility_link_Xpath
					+ ", Please refer ScreenShot [ " + "LinkClickFail" + fnsScreenShot_Date_format() + " ]."
					+ Throwables.getStackTraceAsString(t));
		}
	}

	public String fnsGet_Field_TEXT(String XpathKey) throws Exception {
		try {

			fnsGet_Element_Enabled(XpathKey);
			String TextFetch = fnsGet_OR_MtrPlan_ObjectX(XpathKey).getText();
			fnsApps_Report_Logs(
					"PASSED == Fetch the Text[" + TextFetch + "] on Element having xpath [" + XpathKey + "].");

			return TextFetch;
		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot("TextFetchFail" + XpathKey);
			fnsApps_Report_Logs("FAILED == Unable to Fetch Text on Element having xpath, Please refer screenshot ["
					+ "TextFetchFail" + XpathKey + fnsScreenShot_Date_format() + "]." + ". Getting Exception  >> "
					+ Throwables.getStackTraceAsString(t));
			throw new Exception("FAILED == Unable to Fetch Text on Element having xpath, Please refer screenshot ["
					+ "TextFetchFail" + XpathKey + fnsScreenShot_Date_format() + "]." + ". Getting Exception  >> "
					+ Throwables.getStackTraceAsString(t));
		}
	}

	// function to select drop down value
	public void fnsDD_value_Select_By_MatchingText(String ddClickXpathKey, String ddListXpathKey, String TagName,
			String Value) throws Exception {

		boolean ValueNotMatched = true;
		try {
			fnsWait_and_Click(ddClickXpathKey);
			Thread.sleep(1000);
			fnsGet_Element_Enabled(ddListXpathKey);
			List<WebElement> DDobjectlists = fnsGet_OR_MtrPlan_ObjectX(ddListXpathKey)
					.findElements(By.tagName(TagName));
			for (WebElement dd_value : DDobjectlists) {
				if (dd_value.getText().equals(Value.trim())) {
					dd_value.click();
					ValueNotMatched = false;
					break;
				}
			}
			if (ValueNotMatched == true) {
				// Thread.sleep(2000);
				throw new Exception("Excel value is not matched with DropDown Value.");
			}
			fnsApps_Report_Logs("PASSED == select value [ " + Value + " ] from drop down done, having xpath >>  "
					+ ddClickXpathKey);
		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot("DdValueSelectFail" + ddClickXpathKey);
			fnsApps_Report_Logs("FAILED == Unable to select value from drop down [ " + ddClickXpathKey
					+ " ],plz see screenshot [ DdValueSelectFail" + ddClickXpathKey + fnsScreenShot_Date_format() + " ]"
					+ ". Getting Exception  >> " + Throwables.getStackTraceAsString(t).trim());
			throw new Exception("FAILED == Unable to select value from drop down [ " + ddClickXpathKey
					+ " ],plz see screenshot [ DdValueSelectFail" + ddClickXpathKey + fnsScreenShot_Date_format() + " ]"
					+ ". Getting Exception  >> " + Throwables.getStackTraceAsString(t).trim());
		}
	}

	// Verify Text fetched and matched with expected text.
	public void fnsText_Fetch_and_Assert(String XpathKey, String MatchingText) throws Exception {

		String GetText = fnsGet_Field_TEXT(XpathKey).trim().toLowerCase();
		WithOut_OR_fnMove_To_Element_BY_OFFSET(OR_MtrPlan.getProperty(XpathKey), 20, 10);
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
					+ ". Getting Exception  >> " + Throwables.getStackTraceAsString(t));
			throw new Exception("FAILED == Text (" + MatchingText
					+ ") is not matched with the text fetched from element having xpath >>" + XpathKey
					+ "  ,Please refer ScreenShot [ " + XpathKey + fnsScreenShot_Date_format() + " ].");
		}
	}

	// Count the no rows in lookup table
	public Integer fnCount_table_Rows_BeforeTableRefresh(String LookuptableXpathKey) throws Exception {

		Integer row_count = null;
		try {
			for (int i = 0; i < Long.parseLong(CONFIG.getProperty("ElementWaitTime")); i++) {
				Thread.sleep(1000);
				List<WebElement> table = driver.findElements(By.xpath(OR_MtrPlan.getProperty(LookuptableXpathKey)));
				row_count = table.size();
				if (row_count > 0) {
					break;
				}
			}
			return row_count;
		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			isTestCasePass = false;
			// fnsApps_Report_Logs("LookUp Table Count Rows Fail, Before search bttn
			// click"+Throwables.getStackTraceAsString(t).trim());
			throw new Exception(Throwables.getStackTraceAsString(t).trim());
		}
	}

	// Count the no rows in lookup table
	public Integer fnCount_table_Rows_AfterTableRefresh(String LookuptableXpathKey) throws Exception {
		Integer row_count = null;

		try {
			for (int i = 0; i < Long.parseLong(CONFIG.getProperty("ElementWaitTime")); i++) {
				Thread.sleep(1000);
				List<WebElement> table = driver.findElements(By.xpath(OR_MtrPlan.getProperty(LookuptableXpathKey)));
				Thread.sleep(250);
				row_count = table.size();
				if (row_count > 0) {
					break;
				}
				if (row_count == 0) {
					throw new Exception("Lookup Search record is not coming into Lookup data");
				}
			}
			return row_count;
		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			isTestCasePass = false;
			// fnsApps_Report_Logs("LookUp Table Counts Row Fail, After search bttn
			// click"+Throwables.getStackTraceAsString(t).trim());
			throw new Exception(Throwables.getStackTraceAsString(t).trim());
		}
	}

	// Function to click on lookup CheckBox
	public void fnsSelect_lookup_FirstCheckBox_and_Return() throws Exception {

		try {
			boolean TableSizeFlag = true;
			int beforeSearchRowCount = fnCount_table_Rows_BeforeTableRefresh("lookup_table_div");

			if (beforeSearchRowCount == 1) {
				Thread.sleep(2000);
				driver.findElement(By.xpath(OR_MtrPlan.getProperty("lookup_select_CheckBox"))).click();
				fnsLoading_Progressingwait(2);

				WithOut_OR_fnGet_Element_Enabled(OR_MtrPlan.getProperty("lookup_select_and_return"));
				driver.findElement(By.xpath(OR_MtrPlan.getProperty("lookup_select_and_return"))).click();
				fnsLoading_Progressingwait(2);
				fnsApps_Report_Logs("PASSED == Select LookUp Check Box done");
				TableSizeFlag = false;
			}
			if (beforeSearchRowCount == 0) {
				throw new Exception("Lookup data are not coming");
			}
			if (TableSizeFlag) {
				driver.findElement(By.xpath(OR_MtrPlan.getProperty("lookup_search_bttn"))).click();
				fnsLoading_Progressingwait(10);

				int afterSearchRow = beforeSearchRowCount;
				afterSearchRow = fnCount_table_Rows_AfterTableRefresh("lookup_table_div");

				if (beforeSearchRowCount == afterSearchRow) {
					throw new Exception("LookUp search filter is not working");
				}

				if (driver.findElements(By.xpath(OR_MtrPlan.getProperty("lookup_select_CheckBox"))).size() > 0) {
					driver.findElement(By.xpath(OR_MtrPlan.getProperty("lookup_select_CheckBox"))).click();
					fnsLoading_Progressingwait(2);
				} else {
					throw new Exception("LookUp search filter is not working 'No Record Found.' coming");
				}

				WithOut_OR_fnGet_Element_Enabled(OR_MtrPlan.getProperty("lookup_select_and_return"));
				driver.findElement(By.xpath(OR_MtrPlan.getProperty("lookup_select_and_return"))).click();
				fnsLoading_Progressingwait(2);

				fnsApps_Report_Logs("PASSED == Select LookUp  Check Box done");
			}
		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		}

		catch (Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot_Without_OR("LookUpFail");
			fnsApps_Report_Logs("FAILED ==  " + Throwables.getStackTraceAsString(t).trim()
					+ ", Please refer screenshot >>" + "LookUpFail" + fnsScreenShot_Date_format());
			throw new Exception("FAILED ==  " + Throwables.getStackTraceAsString(t).trim()
					+ ", Please refer screenshot >>" + "LookUpFail" + fnsScreenShot_Date_format());
		}
	}

	// #################################################################### WithOut
	// OR
	// ############################################################################################
	// Function to find and return the object using Xpath
	public WebElement WithOut_OR_fnGet_ObjectX(String XpathKey) throws Exception {

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
			fnsTake_Screen_Shot_Without_OR("NoSuchElementException");
			fnsApps_Report_Logs("FAILED == Element is not found >> " + XpathKey
					+ " ] , plz see screenshot [NoSuchElementException" + fnsScreenShot_Date_format() + " ]");
			throw new Exception("FAILED == Element is not found >> " + XpathKey
					+ " ] , plz see screenshot [NoSuchElementException" + fnsScreenShot_Date_format() + " ]");
		} catch (TimeoutException e) {
			// isTestCasePass = false;
			fnsTake_Screen_Shot_Without_OR("TimeOut" + XpathKey);
			fnsApps_Report_Logs("FAILED == Element is not Present, getting TimeOut >> " + XpathKey
					+ " ] , plz see screenshot [TimeOut" + fnsScreenShot_Date_format() + " ]");
			throw new Exception("FAILED == Element is not Present, getting TimeOut >> " + XpathKey
					+ " ] , plz see screenshot [TimeOut" + fnsScreenShot_Date_format() + " ]");
		} catch (Throwable e) {
			// isTestCasePass = false;
			fnsTake_Screen_Shot_Without_OR("NotPresent" + XpathKey);
			fnsApps_Report_Logs("FAILED == Element is not found >> " + XpathKey + " ] , plz see screenshot [NotPresent"
					+ fnsScreenShot_Date_format() + " ]" + ". Getting Exception  >> "
					+ Throwables.getStackTraceAsString(e));
			throw new Exception("FAILED == Element is not found >> " + XpathKey + " ] , plz see screenshot [NotPresent"
					+ fnsScreenShot_Date_format() + " ]" + ". Getting Exception  >> "
					+ Throwables.getStackTraceAsString(e));
		}
	}

	// Function to find and return the object using Xpath
	public String WithOut_OR_Return_XPATH_if_More_than_One_Coming(String WithoutOR_XpathKey) throws Exception {

		try {
			String Displayed_Element_Xpath = null;
			Integer Element_Size = driver.findElements(By.xpath(WithoutOR_XpathKey)).size();
			boolean Element_Displayed = false;

			for (int i = 1; i <= Element_Size; i++) {
				Displayed_Element_Xpath = "(" + WithoutOR_XpathKey + ")[" + i + "]";
				WebElement Element = driver.findElement(By.xpath(Displayed_Element_Xpath));
				try {
					if (Element.isDisplayed()) {
						Element_Displayed = true;
						break;
					}
				} catch (Throwable t) {
					// nothin to do
				}

				if (i == Element_Size && Element_Displayed == false) {
					throw new Exception("<" + Element_Size + "> Element with xpath [" + WithoutOR_XpathKey
							+ "] are present, but none of them is display.");
				}
			}
			System.out.println("Displayed_Element_Xpath = " + Displayed_Element_Xpath);
			if (Displayed_Element_Xpath == null) {
				throw new Exception("Element with xpath [" + WithoutOR_XpathKey + "] is not exists.");
			}
			return Displayed_Element_Xpath;

		} catch (Throwable e) {
			isTestCasePass = false;
			fnsTake_Screen_Shot_Without_OR("NotPresent" + WithoutOR_XpathKey);
			fnsApps_Report_Logs("FAILED == Fetching Xpath fail >> " + WithoutOR_XpathKey
					+ " ] , plz see screenshot [NotPresent" + fnsScreenShot_Date_format() + " ]"
					+ ". Getting Exception  >> " + Throwables.getStackTraceAsString(e));
			throw new Exception("FAILED == Fetching Xpath fail >> " + WithoutOR_XpathKey
					+ " ] , plz see screenshot [NotPresent" + fnsScreenShot_Date_format() + " ]"
					+ ". Getting Exception  >> " + Throwables.getStackTraceAsString(e));
		}
	}

	// Function to find and return the object using Xpath
	public Integer WithOut_OR_Return_DisplayElementNumber_if_More_than_One_Coming(String WithoutOR_XpathKey)
			throws Exception {

		try {
			String Displayed_Element_Xpath = null;
			Integer Element_Size = driver.findElements(By.xpath(WithoutOR_XpathKey)).size();
			boolean Element_Displayed = false;
			Integer Display_Element_Number = null;

			for (int i = 1; i <= Element_Size; i++) {
				Displayed_Element_Xpath = "(" + WithoutOR_XpathKey + ")[" + i + "]";
				WebElement Element = driver.findElement(By.xpath(Displayed_Element_Xpath));
				for (int j = 0; j <= 10; j++) {
					try {
						if (Element.isDisplayed()) {
							Display_Element_Number = i;
							Element_Displayed = true;
							break;
						}
					} catch (Throwable t) {
						Thread.sleep(500);
					}
				}
				if (Element_Displayed == true) {
					break;
				}
				if (i == Element_Size && Element_Displayed == false) {
					throw new Exception("<" + Element_Size + "> Element with xpath [" + WithoutOR_XpathKey
							+ "] are present, but none of them is display.");
				}
			}
			System.out.println("Display_Element_Number = " + Display_Element_Number);
			return Display_Element_Number;

		} catch (Throwable e) {
			isTestCasePass = false;
			fnsTake_Screen_Shot_Without_OR("NotPresent" + WithoutOR_XpathKey);
			fnsApps_Report_Logs("FAILED == Fetching display element number fail >> " + WithoutOR_XpathKey
					+ " ] , plz see screenshot [NotPresent" + fnsScreenShot_Date_format() + " ]"
					+ ". Getting Exception  >> " + Throwables.getStackTraceAsString(e));
			throw new Exception("FAILED == Fetching display element number fail >> " + WithoutOR_XpathKey
					+ " ] , plz see screenshot [NotPresent" + fnsScreenShot_Date_format() + " ]"
					+ ". Getting Exception  >> " + Throwables.getStackTraceAsString(e));
		}
	}

	// function to select drop down value
	public void WithOut_OR_fnDD_SelectValue_By_MatchingText(String ddClickXpathKey, String ddListXpathKey,
			String TagName, String Value) throws Exception {
		for (int i = 0; i <= 10; i++) {
			try {
				boolean ValueMatched = false;
				WithOut_OR_fnGet_Element_Enabled(ddClickXpathKey);
				WithOut_OR_fnClick(ddClickXpathKey);
				Thread.sleep(1000);
				WithOut_OR_fnGet_Element_Enabled(ddListXpathKey);
				List<WebElement> DDobjectlists = WithOut_OR_fnGet_ObjectX(ddListXpathKey)
						.findElements(By.tagName(TagName));
				for (WebElement dd_value : DDobjectlists) {
					if (dd_value.getText().equals(Value)) {
						dd_value.click();
						ValueMatched = true;
						break;
					}
				}
				if (ValueMatched == true) {
					fnsApps_Report_Logs("PASSED == select value [ " + Value
							+ " ] from drop down done, having xpath >>  " + ddClickXpathKey);
					break;
				} else {
					throw new Exception("Excel value is not matched with DropDown Value.");
				}
			} catch (StaleElementReferenceException st) {
				Thread.sleep(1000);
			} catch (NoSuchWindowException W) {
				isTestCasePass = false;
				throw new Exception(W.getMessage());
			} catch (Throwable t) {
				isTestCasePass = false;
				fnsTake_Screen_Shot_Without_OR("DdValueSelectFailWOR");
				fnsApps_Report_Logs("FAILED == Unable to select value from drop down [ " + ddClickXpathKey
						+ " ],plz see screenshot [ DdValueSelectFailWOR" + fnsScreenShot_Date_format() + " ]"
						+ ". Getting Exception  >> " + Throwables.getStackTraceAsString(t).trim());
				throw new Exception("FAILED == Unable to select value from drop down [ " + ddClickXpathKey
						+ " ],plz see screenshot [ DdValueSelectFailWOR" + fnsScreenShot_Date_format() + " ]"
						+ ". Getting Exception  >> " + Throwables.getStackTraceAsString(t).trim());
			}
		}
	}

	// function to select drop down value
	public void WithOut_OR_fnDD_SelectValue_By_ContainsText(String ddClickXpathKey, String ddListXpathKey,
			String TagName, String Value) throws Exception {

		boolean ValueNotMatched = true;
		try {
			WithOut_OR_fnGet_Element_Enabled(ddClickXpathKey);
			WithOut_OR_fnClick(ddClickXpathKey);
			WithOut_OR_fnGet_Element_Enabled(ddListXpathKey);
			List<WebElement> DDobjectlists = WithOut_OR_fnGet_ObjectX(ddListXpathKey).findElements(By.tagName(TagName));
			for (WebElement dd_value : DDobjectlists) {
				if (dd_value.getText().contains(Value)) {
					dd_value.click();
					ValueNotMatched = false;
					break;
				}
			}
			if (ValueNotMatched == true) {
				throw new Exception("Excel value is not matched with DropDown Value.");
			}
			fnsApps_Report_Logs("PASSED == select value [ " + Value + " ] from drop down done, having xpath >>  "
					+ ddClickXpathKey);
		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot_Without_OR("DdValueSelectFailWOR");
			fnsApps_Report_Logs("FAILED == Unable to select value from drop down [ " + ddClickXpathKey
					+ " ],plz see screenshot [ DdValueSelectFailWOR" + fnsScreenShot_Date_format() + " ]"
					+ ". Getting Exception  >> " + Throwables.getStackTraceAsString(t).trim());
			throw new Exception("FAILED == Unable to select value from drop down [ " + ddClickXpathKey
					+ " ],plz see screenshot [ DdValueSelectFailWOR" + fnsScreenShot_Date_format() + " ]"
					+ ". Getting Exception  >> " + Throwables.getStackTraceAsString(t).trim());
		}
	}

	// function to select drop down value by using filter functionality
	public void WithOut_OR_fnDD_SelectValue_Through_Filter(String ddClickXpathKey, String FilterXpath,
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
			fnsTake_Screen_Shot_Without_OR("DdValueSelectFailWOR");
			fnsApps_Report_Logs("FAILED == Unable to select value from drop down [ " + ddClickXpathKey
					+ " ],plz see screenshot [ DdValueSelectFailWOR" + fnsScreenShot_Date_format() + " ]"
					+ ". Getting Exception  >> " + Throwables.getStackTraceAsString(t).trim());
			throw new Exception("FAILED == Unable to select value from drop down [ " + ddClickXpathKey
					+ " ],plz see screenshot [ DdValueSelectFailWOR" + fnsScreenShot_Date_format() + " ]"
					+ ". Getting Exception  >> " + Throwables.getStackTraceAsString(t).trim());
		}
	}

	// function to select drop down value
	public void WithOut_OR_fnDD_SelectValue_By_SelectClass(String dropdownXpath, String MatchingValue)
			throws Exception {

		try {
			WithOut_OR_fnGet_Element_Enabled(dropdownXpath);
			Select Dropdown = new Select(WithOut_OR_fnGet_ObjectX(dropdownXpath));
			Dropdown.selectByVisibleText(MatchingValue);

			fnsApps_Report_Logs("PASSED == select value [ " + MatchingValue + " ] from drop down done, having xpath >> "
					+ dropdownXpath);
		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot_Without_OR("DdValueSelectFailWOR");
			fnsApps_Report_Logs("FAILED == Unable to select value[ " + MatchingValue + " ] from drop down [ "
					+ dropdownXpath + " ],plz see screenshot [ DdValueSelectFailWOR" + fnsScreenShot_Date_format()
					+ " ]" + ". Getting Exception  >> " + Throwables.getStackTraceAsString(t).trim());
			throw new Exception("FAILED == Unable to select value[ " + MatchingValue + " ] from drop down [ "
					+ dropdownXpath + " ],plz see screenshot [ DdValueSelectFailWOR" + fnsScreenShot_Date_format()
					+ " ]" + ". Getting Exception  >> " + Throwables.getStackTraceAsString(t).trim());
		}
	}

	// function to select drop down value
	public void WithOut_OR_fnDD_SelectValue_By_SelectClass_WithoutEnable(String dropdownXpath, String MatchingValue)
			throws Exception {
		try {
			Select Dropdown = new Select(WithOut_OR_fnGet_ObjectX(dropdownXpath));
			Dropdown.selectByVisibleText(MatchingValue);

			fnsApps_Report_Logs("PASSED == select value [ " + MatchingValue + " ] from drop down done, having xpath >> "
					+ dropdownXpath);
		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot_Without_OR("DdValueSelectFailWOR");
			fnsApps_Report_Logs("FAILED == Unable to select value[ " + MatchingValue + " ] from drop down [ "
					+ dropdownXpath + " ],plz see screenshot [ DdValueSelectFailWOR" + fnsScreenShot_Date_format()
					+ " ]" + ". Getting Exception  >> " + Throwables.getStackTraceAsString(t).trim());
			throw new Exception("FAILED == Unable to select value[ " + MatchingValue + " ] from drop down [ "
					+ dropdownXpath + " ],plz see screenshot [ DdValueSelectFailWOR" + fnsScreenShot_Date_format()
					+ " ]" + ". Getting Exception  >> " + Throwables.getStackTraceAsString(t).trim());
		}
	}

	// function to select drop down value
	public void WithOut_OR_fnDD_SelectValue_By_Contain_Text_and_by_KEYS(String ddListXpathKey, String TagName,
			String Value) throws Exception {

		boolean ValueNotMatched = true;
		try {
			/*
			 * WithOut_OR_fnGet_Element_Enabled(ddListXpathKey);
			 * WithOut_OR_fnClick(ddListXpathKey) ;
			 */
			Thread.sleep(500);
			WithOut_OR_fnGet_Element_Enabled(ddListXpathKey);

			// Earlier it was working, but commented on 16.9.2016 as it is also not working
			// on chrome/ie
			/*
			 * Actions act = new Actions(driver);
			 * act.sendKeys(Keys.ARROW_UP).sendKeys(Keys.ARROW_UP).sendKeys(Keys.ARROW_UP).
			 * sendKeys(Keys.ARROW_UP).sendKeys(Keys.ARROW_UP).sendKeys(Keys.ARROW_UP).
			 * sendKeys(Keys.ARROW_UP).sendKeys(Keys.ARROW_UP).sendKeys(Keys.ARROW_UP).
			 * sendKeys(Keys.ARROW_UP).sendKeys(Keys.ARROW_UP).build().perform();
			 */

			// Not working in this case
			/*
			 * Actions act = new Actions(driver);
			 * act.keyDown(Keys.ARROW_UP).keyDown(Keys.ARROW_UP).keyDown(Keys.ARROW_UP).
			 * keyDown(Keys.ARROW_UP).keyDown(Keys.ARROW_UP).keyDown(Keys.ARROW_UP).keyDown(
			 * Keys.ARROW_UP).keyDown(Keys.ARROW_UP).keyUp(Keys.ARROW_UP).build().perform();
			 */

			List<WebElement> DDobjectlists = WithOut_OR_fnGet_ObjectX(ddListXpathKey).findElements(By.tagName(TagName));
			int option_No = 1;
			for (WebElement dd_value : DDobjectlists) {

				String option_Text_Xpath = ddListXpathKey + "/" + TagName + "[" + option_No + "]";
				String Text_fetched = WithOut_OR_fnsGet_Field_TEXT(option_Text_Xpath);
				System.out.println("Text " + option_No + " === " + Text_fetched);

				if (Text_fetched.contains(Value)) {

					WebElement mySelectElement = WithOut_OR_fnGet_ObjectX(ddListXpathKey);
					Select dropdown = new Select(mySelectElement);
					try {
						dropdown.selectByVisibleText(Text_fetched);
					} catch (Throwable t) {
						dropdown.selectByIndex((option_No - 1));
					}

					// Earlier it was working, but commented on 16.9.2016 as it is also not working
					// on chrome/ie
					/*
					 * Thread.sleep(250);
					 * Actions act2 = new Actions(driver);
					 * act2.sendKeys(Keys.ENTER).build().perform();
					 */

					// WithOut_OR_fnMove_To_Element_and_Click(option_Text_Xpath); //Not working in
					// IE
					// WithOut_OR_fnClick(option_Text_Xpath); //Not working in IE
					// WithOut_OR_fnDD_SelectValue_By_SelectClass(ddListXpathKey,
					// Text_fetched.trim()); //Not working in IE
					Thread.sleep(500);
					ValueNotMatched = false;
					break;
				}

				Actions act1 = new Actions(driver);
				act1.sendKeys(Keys.ARROW_DOWN).build().perform();

				option_No++;
			}
			if (ValueNotMatched == true) {
				throw new Exception("Excel value is not matched with DropDown Value.");
			}
			fnsApps_Report_Logs(
					"PASSED == select value [ " + Value + " ] from drop down done, having xpath >>  " + ddListXpathKey);
		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot_Without_OR("DdValueSelectFailWOR");
			fnsApps_Report_Logs("FAILED == Unable to select value from drop down [ " + ddListXpathKey
					+ " ],plz see screenshot [ DdValueSelectFailWOR" + fnsScreenShot_Date_format() + " ]"
					+ ". Getting Exception  >> " + Throwables.getStackTraceAsString(t).trim());
			throw new Exception("FAILED == Unable to select value from drop down [ " + ddListXpathKey
					+ " ],plz see screenshot [ DdValueSelectFailWOR" + fnsScreenShot_Date_format() + " ]"
					+ ". Getting Exception  >> " + Throwables.getStackTraceAsString(t).trim());
		}
	}

	// function to select drop down value
	public void WithOut_OR_fnsDD_value_Select_From_Filter_CheckBox(String ddClickXpathKey, String Value)
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
						fnsLoading_Progressingwait(2);
						CheckBox_Click_Done = true;
						break;
					}
				} catch (Throwable t) {
					// nothing to do
				}
			}

			if (CheckBox_Click_Done == false) {
				fnsTake_Screen_Shot_Without_OR("DDValue_Select_Fail");
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
						fnsLoading_Progressingwait(2);
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

	// Function to click on Object Without OR
	public void WithOut_OR_fnClick(String XpathKey) throws Exception {

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
			fnsTake_Screen_Shot_Without_OR("UnableToClick_");
			fnsApps_Report_Logs("FAILED == Unable To Click on Element having Xpath >> " + XpathKey
					+ ", plz see screenshot [ UnableToClick_" + fnsScreenShot_Date_format() + " ]"
					+ ". Getting Exception  >> " + Throwables.getStackTraceAsString(e));
			throw new Exception("Unable To Click on element [ " + XpathKey + " ] , plz see screenshot [ UnableToClick_"
					+ fnsScreenShot_Date_format() + " ]" + ". Getting Exception  >> "
					+ Throwables.getStackTraceAsString(e));
		}
	}

	// Function to wait for element and then type
	public void WithOut_OR_fnType(String XpathKey, String value) throws Exception {

		try {
			try {
				WithOut_OR_fnGet_ObjectX(XpathKey).sendKeys("");
				WithOut_OR_fnGet_ObjectX(XpathKey).sendKeys(value);
				fnsApps_Report_Logs(
						"PASSED == Type Value [ " + value + " ] done on Element having Xpath  >> " + XpathKey);
			} catch (Throwable tt) {
				Thread.sleep(3000);
				WithOut_OR_fnGet_ObjectX(XpathKey).sendKeys("");
				WithOut_OR_fnGet_ObjectX(XpathKey).sendKeys(value);
				fnsApps_Report_Logs("(((((( 2nd Attampt ))))))---PASSED == Type Value [ " + value
						+ " ] done on Element having Xpath  >> " + XpathKey);
			}
		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable e) {
			isTestCasePass = false;
			fnsTake_Screen_Shot_Without_OR("UnableToType_");
			fnsApps_Report_Logs("FAILED == Unable To Type on Element having Xpath  >> " + XpathKey
					+ ", plz see screenshot [ UnableToType_" + fnsScreenShot_Date_format() + " ]"
					+ ". Getting Exception  >> " + Throwables.getStackTraceAsString(e));
			throw new Exception("Unable To Type on element [ " + XpathKey + " ] , plz see screenshot [ UnableToType_"
					+ fnsScreenShot_Date_format() + " ]" + ". Getting Exception  >> "
					+ Throwables.getStackTraceAsString(e));
		}
	}

	// Function to Clear Field
	public void WithOut_OR_fnClear(String XpathKey) throws Exception {

		try {
			WithOut_OR_fnGet_ObjectX(XpathKey).clear();
			fnsApps_Report_Logs("PASSED == Clear done Element having Xpath  >> " + XpathKey);
		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable e) {
			isTestCasePass = false;
			fnsTake_Screen_Shot_Without_OR("UnableToClear_");
			fnsApps_Report_Logs("FAILED == Unable To performe Clear on Element having Xpath  >> " + XpathKey
					+ ", plz see screenshot [ UnableToClear_" + fnsScreenShot_Date_format() + " ]"
					+ ". Getting Exception  >> " + Throwables.getStackTraceAsString(e));
			throw new Exception("Unable To performe Clear on element [ " + XpathKey
					+ " ] , plz see screenshot [ UnableToClear_" + fnsScreenShot_Date_format() + " ]"
					+ ". Getting Exception  >> " + Throwables.getStackTraceAsString(e));
		}
	}

	// Function move to element
	public void WithOut_OR_fnMove_To_Element(String XpathKey) throws Exception {

		try {
			Actions action = new Actions(driver);
			WebElement MonitorPlanTypeElement = WithOut_OR_fnGet_ObjectX(XpathKey);
			action.moveToElement(MonitorPlanTypeElement).build().perform();
			/*
			 * fnsApps_Report_Logs("PASSED == Move to Element done having Xpath >> "
			 * +XpathKey);
			 */
		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable e) {
			isTestCasePass = false;
			fnsTake_Screen_Shot_Without_OR("ElementMoveFail");
			fnsApps_Report_Logs("FAILED == Unable to MoveToElement, having Xpath  >> " + XpathKey
					+ ", plz see screenshot [ElementMoveFail " + fnsScreenShot_Date_format()
					+ " ]. Getting Exception >> " + Throwables.getStackTraceAsString(e));
			throw new Exception("FAILED == Unable to MoveToElement, having Xpath  [ " + XpathKey
					+ " ] , plz see screenshot [ElementMoveFail " + fnsScreenShot_Date_format()
					+ " ]. Getting Exception >> " + Throwables.getStackTraceAsString(e));
		}
	}

	// Function move to element
	public void WithOut_OR_fnMove_To_Element_BY_OFFSET(String XpathKey, Integer XX, Integer YY) throws Exception {

		try {
			Actions action = new Actions(driver);
			WebElement MonitorPlanTypeElement = WithOut_OR_fnGet_ObjectX(XpathKey);
			action.moveToElement(MonitorPlanTypeElement, XX, YY).build().perform();
			fnsApps_Report_Logs("PASSED == OffSet Move to Element having Xpath >> " + XpathKey);
		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable e) {
			isTestCasePass = false;
			fnsTake_Screen_Shot_Without_OR("ElementMoveFail");
			fnsApps_Report_Logs("FAILED == Unable to MoveToElement, having Xpath  >> " + XpathKey
					+ ", plz see screenshot [ElementMoveFail " + fnsScreenShot_Date_format()
					+ " ]. Getting Exception >> " + Throwables.getStackTraceAsString(e));
			throw new Exception("FAILED == Unable to MoveToElement, having Xpath  [ " + XpathKey
					+ " ] , plz see screenshot [ElementMoveFail " + fnsScreenShot_Date_format()
					+ " ]. Getting Exception >> " + Throwables.getStackTraceAsString(e));
		}
	}

	// Function to wait for element and then type
	public void WithOut_OR_fnMove_To_Element_and_Click(String XpathKey) throws Exception {

		try {
			Actions action = new Actions(driver);
			WebElement MonitorPlanTypeElement = WithOut_OR_fnGet_ObjectX(XpathKey);
			action.moveToElement(MonitorPlanTypeElement).click().build().perform();
			fnsApps_Report_Logs("PASSED == Move to Element and Click done having Xpath >> " + XpathKey);
		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable e) {
			isTestCasePass = false;
			fnsTake_Screen_Shot_Without_OR("ElementMoveFail");
			fnsApps_Report_Logs("FAILED == Unable to MoveToElement and Click, having Xpath  >> " + XpathKey
					+ ", plz see screenshot [ElementMoveFail " + fnsScreenShot_Date_format()
					+ " ]. Getting Exception >> " + Throwables.getStackTraceAsString(e));
			throw new Exception("FAILED == Unable to MoveToElement and Click, having Xpath  [ " + XpathKey
					+ " ] , plz see screenshot [ElementMoveFail " + fnsScreenShot_Date_format()
					+ " ]. Getting Exception >> " + Throwables.getStackTraceAsString(e));
		}
	}

	// Function to wait for element and then type
	public void WithOut_OR_fnMove_To_Element_and_DoubleClick(String XpathKey) throws Exception {

		try {
			Actions action = new Actions(driver);
			WebElement MonitorPlanTypeElement = WithOut_OR_fnGet_ObjectX(XpathKey);
			action.moveToElement(MonitorPlanTypeElement).doubleClick().build().perform();
			fnsApps_Report_Logs("PASSED == Double Click done on Element having Xpath >> " + XpathKey);
		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable e) {
			isTestCasePass = false;
			fnsTake_Screen_Shot_Without_OR("DoubleClickFail");
			fnsApps_Report_Logs("FAILED == Unable to MoveToElement and Click, having Xpath  >> " + XpathKey
					+ ", plz see screenshot [DoubleClickFail" + fnsScreenShot_Date_format() + "]. Getting Exception >> "
					+ Throwables.getStackTraceAsString(e));
			throw new Exception("FAILED == Unable to MoveToElement and Click, having Xpath  [ " + XpathKey
					+ " ] , plz see screenshot [DoubleClickFail" + fnsScreenShot_Date_format()
					+ "]. Getting Exception >> " + Throwables.getStackTraceAsString(e));
		}
	}

	// First clear the data from input field then type the value -- Approach comes
	// in feature as input field is not working in chrome.
	public void WithOut_OR_fnClear_Field_By_Pressing_BackspaceKey(String XpathKey, Integer BackSpaceKeyPressCount)
			throws Exception {

		try {
			WithOut_OR_fnGet_Element_Enabled(XpathKey);
			WebElement Element = WithOut_OR_fnGet_ObjectX(XpathKey);

			for (int i = 0; i < BackSpaceKeyPressCount; i++) {
				Element.sendKeys(Keys.BACK_SPACE);
			}
			fnsApps_Report_Logs(
					"PASSED == Backspace Clear performe successfully on Element having Xpath  >> " + XpathKey);
		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable e) {
			isTestCasePass = false;
			fnsTake_Screen_Shot_Without_OR("UnableToClearBcksp_");
			fnsApps_Report_Logs("FAILED == Unable To  Clear on Element having Xpath  >> " + XpathKey
					+ ", plz see screenshot [ UnableToClearBcksp_" + fnsScreenShot_Date_format() + " ]"
					+ ". Getting Exception  >> " + Throwables.getStackTraceAsString(e));
			throw new Exception("FAILED == Unable To  Clear on element [ " + XpathKey
					+ " ] , plz see screenshot [ UnableToClearBcksp_" + fnsScreenShot_Date_format() + " ]"
					+ ". Getting Exception  >> " + Throwables.getStackTraceAsString(e));
		}
	}

	// Function to wait for element Visible
	public void WithOut_OR_fnGet_Element_Enabled(String XpathKey) throws Exception {

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
				fnsTake_Screen_Shot_Without_OR(XpathKey);
				fnsApps_Report_Logs("FAILED == Element is not Visible having Xpath  >> " + XpathKey
						+ ", plz see screenshot [ " + "" + XpathKey + fnsScreenShot_Date_format()
						+ " ]. Getting Exception >> " + Throwables.getStackTraceAsString(e));
				throw new Exception("FAILED == Element is not Visible having Xpath  [ " + XpathKey
						+ " ] , plz see screenshot [ " + "" + XpathKey + fnsScreenShot_Date_format()
						+ " ]. Getting Exception >> " + Throwables.getStackTraceAsString(e));
			}
		}
	}

	// clicking on 'TAB Pagination NEXT link' until it will get disappear
	public void fsnClickOn_Screen_TAB_Pagination_NEXT_Link_Till_DisAppear() throws Exception {
		try {
			if (driver.findElements(By.xpath(OR_MtrPlan.getProperty("Screen_TAB_PAgination_NEXT_Link"))).size() > 0) {
				WebElement NextLink = driver
						.findElement(By.xpath(OR_MtrPlan.getProperty("Screen_TAB_PAgination_NEXT_Link")));
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
		} catch (Throwable e) {/*
								 * isTestCasePass = false;
								 * fnsTake_Screen_Shot("TAB_Next_Link_");
								 * fnsApps_Report_Logs("FAILED == Click fail on 'TAB Pagination NEXT link', please refer screenshot >> TAB_Next_Link__"
								 * + fnsScreenShot_Date_format() +
								 * ". Getting Exception  >> "+Throwables.getStackTraceAsString(e));
								 * throw new
								 * Exception("FAILED == Click fail on 'TAB Pagination NEXT link', please refer screenshot >> TAB_Next_Link__"
								 * + fnsScreenShot_Date_format() +
								 * ". Getting Exception  >> "+Throwables.getStackTraceAsString(e));
								 */
			// commented because scripts is getting fail on edge
		}
	}

	// Function to wait for element Visible
	public void fnsComman_BrowserLogin_ElementWait(String XpathKeyWithoutOR, String WaitTime) throws Exception {
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

	public String WithOut_OR_fnsGet_Field_TEXT(String XpathKey) throws Exception {
		try {

			WithOut_OR_fnGet_Element_Enabled(XpathKey);

			String TextFetch = WithOut_OR_fnGet_ObjectX(XpathKey).getText().trim();
			// fnsApps_Report_Logs("PASSED == Fetch the Text["+TextFetch+"] on Element
			// having xpath[" +XpathKey+"].");
			fnsApps_Report_Logs("PASSED == Text Fetch done on Element having xpath[" + XpathKey + "].");

			return TextFetch;
		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot_Without_OR(XpathKey);
			fnsApps_Report_Logs(
					"FAILED == Unable to Fetch Text on Element having xpath [" + XpathKey + fnsScreenShot_Date_format()
							+ "]." + ". Getting Exception >> " + Throwables.getStackTraceAsString(t));
			throw new Exception(
					"FAILED == Unable to Fetch Text on Element having xpath [" + XpathKey + fnsScreenShot_Date_format()
							+ "]." + ". Getting Exception >> " + Throwables.getStackTraceAsString(t));
		}
	}

	// Verify Text fetched and matched with expected text.
	public void Without_OR_fnText_Fetch_and_Assert(String XpathKey, String MatchingText) throws Exception {

		String GetText = WithOut_OR_fnsGet_Field_TEXT(XpathKey).trim().toLowerCase();
		try {
			assert GetText.contains(MatchingText.toLowerCase())
					: "FAILED == Actual Text<" + GetText + "> is not matched with the Expected text<" + MatchingText
							+ "> fetched from element having xpath >>" + XpathKey
							+ "  ,Please refer ScreenShot [ TextMatchFail" + fnsScreenShot_Date_format() + " ].";
			fnsApps_Report_Logs("PASSED ==  Actual Text<" + GetText + "> is matched with the Expected text<"
					+ MatchingText + ">, fetched from element having xpath >>" + XpathKey);

		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot_Without_OR("TextMatchFail");
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}

	// Verify Text fetched and matched with expected text.
	public void Without_OR_fnText_Fetch_and_Assert_With_SpecficChar(String XpathKey, String MatchingText)
			throws Exception {

		String GetText = WithOut_OR_fnsGet_Field_TEXT(XpathKey);

		if (GetText.contains(",")) {
			TextAfterRemvoalSpeChar = (GetText.split("\\,")[0]).trim() + (GetText.split("\\,")[1]).trim();
		} else {
			TextAfterRemvoalSpeChar = GetText.trim();
		}

		System.out.println("TextAfterRemvoalSpeChar =" + TextAfterRemvoalSpeChar);
		try {
			assert (TextAfterRemvoalSpeChar.toLowerCase()).contains(MatchingText.toLowerCase())
					: "FAILED == Actual Text<" + TextAfterRemvoalSpeChar + "> is not matched with the Expected text <"
							+ MatchingText + ">, fetched from element having xpath >>" + XpathKey
							+ "  ,Please refer ScreenShot [ TextMatchFail" + fnsScreenShot_Date_format() + " ].";
			fnsApps_Report_Logs(
					"PASSED ==  Actual Text<" + TextAfterRemvoalSpeChar + "> is matched with the Expected text <"
							+ MatchingText + "> fetched from element having xpath >>" + XpathKey);

		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot_Without_OR("TextMatchFail");
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}

	// Return Row Number of matching TEXT in Table Cell
	public Integer WithOut_OR_fnsReturn_RowNumber_By_TableMatching_TEXT(String TableHeaderXpath,
			String TableCellHeaderXpath, String MatchingText) throws Throwable {

		try {

			RowCount = 0;
			boolean Matched_Flag = true;

			WithOut_OR_fnGet_Element_Enabled(TableHeaderXpath);

			WebElement stdtable = WithOut_OR_fnGet_ObjectX(TableHeaderXpath);
			List<WebElement> no_of_rows_in_list = stdtable.findElements(By.tagName("tr"));

			for (WebElement countrows : no_of_rows_in_list) {
				if (RowCount > 0) {
					String XpathRow = TableCellHeaderXpath + "/tr[" + (RowCount) + "]";
					String RowText = driver.findElement(By.xpath(XpathRow)).getText().trim().toLowerCase();
					String MatchingTextValue = MatchingText.trim().toLowerCase();

					if (RowText.contains(MatchingTextValue)) {
						Matched_Flag = false;
						break;
					}
				}
				RowCount++;
			}
			if (Matched_Flag) {
				throw new Exception("Matching Text<[" + MatchingText + "> is not found in the table.");
			}
			fnsApps_Report_Logs("PASSED == Succesfully Fetch RowNumber(" + RowCount + ") for Matching Text<"
					+ MatchingText + ">, table having xpaths >> " + TableCellHeaderXpath);
			return RowCount;
		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot_Without_OR(MatchingText);
			fnsApps_Report_Logs("FAILED == Fetching RowNumber is getting failed for table having xpaths >> "
					+ TableCellHeaderXpath + ", Please refer screen shot [" + MatchingText + fnsScreenShot_Date_format()
					+ " ], Getting Exception >>" + Throwables.getStackTraceAsString(t));
			throw new Exception("FAILED == Fetching RowNumber is getting failed for table having xpaths >> "
					+ TableCellHeaderXpath + ", Please refer screen shot [" + MatchingText + fnsScreenShot_Date_format()
					+ " ], Getting Exception >>" + Throwables.getStackTraceAsString(t));
		}

	}

	// Return Row Number of matching TEXT in Table Cell
	public void WithOut_OR_fnVerify_TaskList_TableData(Integer TaskTableXpathNo, String BillCode, String Quanity,
			String Price) throws Throwable {

		try {

			int RowNumber = 1;
			String BillCodeTableHeader = "//*[@id='mainForm:tabView:dataTable:" + TaskTableXpathNo
					+ ":jobExpence']/div/table";
			String BillCodeTableCell = "//*[@id='mainForm:tabView:dataTable:" + TaskTableXpathNo + ":jobExpence_data']";

			WithOut_OR_fnGet_Element_Enabled(BillCodeTableHeader);

			WebElement stdtable = WithOut_OR_fnGet_ObjectX(BillCodeTableHeader);
			List<WebElement> no_of_rows_in_list = stdtable.findElements(By.tagName("tr"));

			for (WebElement countrows : no_of_rows_in_list) {

				if (RowNumber > 1) {
					String XpathRow = BillCodeTableCell + "/tr[" + (RowNumber) + "]";
					if (driver.findElements(By.xpath(XpathRow)).size() > 0) {
						String RowText = WithOut_OR_fnsGet_Field_TEXT(XpathRow).trim().toLowerCase();
						String MatchingTextValue = BillCode.trim().toLowerCase();

						if (RowText.contains(MatchingTextValue)) {
							String QuantityXpath = BillCodeTableCell + "/tr[" + (RowNumber) + "]/td[5]";
							String PriceXpath = BillCodeTableCell + "/tr[" + (RowNumber) + "]/td[8]";
							String QuantityValue = Quanity.trim();
							String PriceValue = Price.trim();

							WithOut_OR_fnMove_To_Element_and_DoubleClick(QuantityXpath);
							Without_OR_fnText_Fetch_and_Assert(QuantityXpath, QuantityValue);

							WithOut_OR_fnMove_To_Element_and_DoubleClick(PriceXpath);
							Without_OR_fnText_Fetch_and_Assert_With_SpecficChar(PriceXpath, PriceValue);

							break;
						}
					}
				}
				RowNumber++;
			}

		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			isTestCasePass = false;
			// fnsTake_Screen_Shot_Without_OR(BillCode);
			fnsApps_Report_Logs(
					"FAILED == Data Verification for BillingCode(" + BillCode + ") is getting failed,for table No<"
							+ (TaskTableXpathNo + 1) + "> , Getting Exception" + Throwables.getStackTraceAsString(t));
			throw new Exception(
					"FAILED == Data Verification for BillingCode(" + BillCode + ") is getting failed,for table No<"
							+ (TaskTableXpathNo + 1) + "> , Getting Exception" + Throwables.getStackTraceAsString(t));
		}

	}

	// Count the Total no rows in Table
	public int WithOut_OR_Retrun_TotalRowCount_OfTable(String TableXpathKey) throws Exception {

		try {
			RowCount = 0;
			WithOut_OR_fnGet_Element_Enabled(TableXpathKey);
			WebElement stdtable = WithOut_OR_fnGet_ObjectX(TableXpathKey);
			List<WebElement> no_of_rows_in_list = stdtable.findElements(By.tagName("tr"));

			for (WebElement countrows : no_of_rows_in_list) {
				RowCount++;
			}
			RowCount--;
			fnsApps_Report_Logs("PASSED == Succesfully retrun Total Row Count(" + RowCount
					+ ") for table having xpaths >> " + TableXpathKey);
			return RowCount;

		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot_Without_OR("CountRowFailFrom" + TableXpathKey);
			fnsApps_Report_Logs("FAILED == Unable to count rows from  Table having xpath [ " + "CountRowFailFrom"
					+ TableXpathKey + fnsScreenShot_Date_format() + " ]." + ". Getting Exception  >> "
					+ Throwables.getStackTraceAsString(t).trim());
			throw new Exception("Unable to count rows from  Table having xpath [" + TableXpathKey
					+ "],plz see screenshot [CountRowFailFrom" + TableXpathKey + fnsScreenShot_Date_format() + "]"
					+ ". Getting Exception  >> " + Throwables.getStackTraceAsString(t).trim());
		}
	}

	// Count the Total no rows in Table
	public Integer WithOut_OR_Retrun_TableColumnNo_of_MatchingColumnName(String TableColumnHeaderRowXpath,
			String MatchingColumnNameText) throws Exception {
		try {
			Column_Number = 1;
			WebElement HeaderTableAllObj = WithOut_OR_fnGet_ObjectX(TableColumnHeaderRowXpath);
			List<WebElement> NoOfHeadinglist = HeaderTableAllObj.findElements(By.tagName("th"));
			for (WebElement HeaderTableEle : NoOfHeadinglist) {
				String ColumnHeadingText = HeaderTableEle.getText().toLowerCase().trim();
				if (ColumnHeadingText.contains(MatchingColumnNameText.toLowerCase().trim())) {
					break;
				}
				Column_Number++;
			}
			return Column_Number;
		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot_Without_OR(MatchingColumnNameText + "_ColumnNoFetchFail");
			fnsApps_Report_Logs("FAILED == Unable to fetch Column number for Column(" + MatchingColumnNameText
					+ "), Please refer screen shot >> " + MatchingColumnNameText + "_ColumnNoFetchFail"
					+ fnsScreenShot_Date_format() + ". Getting Exception  >> "
					+ Throwables.getStackTraceAsString(t).trim());
			throw new Exception("FAILED == Unable to fetch Column number for Column(" + MatchingColumnNameText
					+ "), Please refer screen shot >> " + MatchingColumnNameText + "_ColumnNoFetchFail"
					+ fnsScreenShot_Date_format() + ". Getting Exception  >> "
					+ Throwables.getStackTraceAsString(t).trim());
		}
	}

	// Count the Total no rows in Table
	public Integer WithOut_OR_Retrun_TableColumnNo_of_MatchingColumnName_BY_EqualsIgnoreCase(
			String TableColumnHeaderRowXpath, String MatchingColumnNameText) throws Exception {
		try {
			Column_Number = 1;
			WebElement HeaderTableAllObj = WithOut_OR_fnGet_ObjectX(TableColumnHeaderRowXpath);
			List<WebElement> NoOfHeadinglist = HeaderTableAllObj.findElements(By.tagName("th"));
			for (WebElement HeaderTableEle : NoOfHeadinglist) {
				String ColumnHeadingText = HeaderTableEle.getText().toLowerCase().trim();
				if (ColumnHeadingText.equalsIgnoreCase(MatchingColumnNameText.toLowerCase().trim())) {
					break;
				}
				Column_Number++;
			}
			return Column_Number;
		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot_Without_OR(MatchingColumnNameText + "_ColumnNoFetchFail");
			fnsApps_Report_Logs("FAILED == Unable to fetch Column number for Column(" + MatchingColumnNameText
					+ "), Please refer screen shot >> " + MatchingColumnNameText + "_ColumnNoFetchFail"
					+ fnsScreenShot_Date_format() + ". Getting Exception  >> "
					+ Throwables.getStackTraceAsString(t).trim());
			throw new Exception("FAILED == Unable to fetch Column number for Column(" + MatchingColumnNameText
					+ "), Please refer screen shot >> " + MatchingColumnNameText + "_ColumnNoFetchFail"
					+ fnsScreenShot_Date_format() + ". Getting Exception  >> "
					+ Throwables.getStackTraceAsString(t).trim());
		}
	}

	// To delete File.
	public void fnsDelete_File(String filePath) {
		boolean FileDeleteFlag = FileUtils.deleteQuietly(new File((filePath)));
		if (FileDeleteFlag == true) {
			fnsApps_Report_Logs("PASSED == Successfully Deleted the file, having path[ " + filePath + " ]");
		}
		if (FileDeleteFlag == false) {
			fnsApps_Report_Logs("PASSED == file, having path[ " + filePath + " ] is not exists for Deletion.");
		}

	}

	// ##########################################################################
	// Application Functions
	// ########################################################################################

	// Clicking on CreateAnnualMonitorPlan_Ajax_Link.
	public void fnsCreateAnnualMonitorPlan_Ajax_Link_Click() throws Exception {

		try {
			fnsGet_Element_Enabled("Menu_Ajax_Link");
			WebElement Menu_Element = fnsGet_OR_MtrPlan_ObjectX("Menu_Ajax_Link");

			// New line added to run script in chrome.
			WebElement VersionLogoImage = fnsGet_OR_MtrPlan_ObjectX("VersionLogoImage");
			VersionLogoImage.click();
			Thread.sleep(500);

			Actions action1 = new Actions(driver);
			action1.moveToElement(Menu_Element).build().perform();

			Thread.sleep(500);

			Actions act1 = new Actions(driver);
			fnsGet_Element_Enabled("CreateAnnualMonitorPlan_Ajax_Link");
			WebElement CreateAnnualMonitorPlan = fnsGet_OR_MtrPlan_ObjectX("CreateAnnualMonitorPlan_Ajax_Link");
			act1.moveToElement(CreateAnnualMonitorPlan).click().build().perform();
			fnsApps_Report_Logs("PASSED == Successfully Click on 'Create Annual Monitor Plan' Ajax Link.");
		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot("CreateAnnualMonitorPlanAjaxClickFail");
			fnsApps_Report_Logs("FAILED == Clicking on CreateAnnualMonitorPlan Ajex Link Failed, plz see screenshot [ "
					+ "CreateAnnualMonitorPlanAjaxClickFail" + fnsScreenShot_Date_format() + "]"
					+ ". Getting Exception  >> " + Throwables.getStackTraceAsString(t).trim());
			throw new Exception("Clicking on Search CreateAnnualMonitorPlan Link Failed, plz see screenshot [ "
					+ "CreateAnnualMonitorPlanAjaxClickFail" + fnsScreenShot_Date_format() + "]"
					+ ". Getting Exception  >> " + Throwables.getStackTraceAsString(t).trim());
		}
	}

	// Clicking on CreateAnnualMonitorPlan_Ajax_Link.
	public void fnsSearchWorkOrder_Ajax_Link_Click() throws Exception {

		try {
			fnsGet_Element_Enabled("Menu_Ajax_Link");
			WebElement Menu_Element = fnsGet_OR_MtrPlan_ObjectX("Menu_Ajax_Link");

			// New line added to run script in chrome.
			WebElement VersionLogoImage = fnsGet_OR_MtrPlan_ObjectX("VersionLogoImage");
			VersionLogoImage.click();
			Thread.sleep(500);

			Actions action1 = new Actions(driver);
			action1.moveToElement(Menu_Element).build().perform();

			Thread.sleep(500);

			Actions act1 = new Actions(driver);
			fnsGet_Element_Enabled("SearchWorkOrder_Ajax_Link");
			WebElement SearchWorkOrder = fnsGet_OR_MtrPlan_ObjectX("SearchWorkOrder_Ajax_Link");
			act1.moveToElement(SearchWorkOrder).click().build().perform();
			fnsApps_Report_Logs("PASSED == Successfully click over Search Work Order Ajax link");

		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot("SearchWorkOrderAjaxClickFail");
			fnsApps_Report_Logs("FAILED == Clicking on SearchWorkOrder Ajex Link Failed, plz see screenshot [ "
					+ "SearchWorkOrderAjaxClickFail" + fnsScreenShot_Date_format() + "]" + ". Getting Exception  >> "
					+ Throwables.getStackTraceAsString(t).trim());
			throw new Exception("Clicking on Search SearchWorkOrder Link Failed, plz see screenshot [ "
					+ "SearchWorkOrderAjaxClickFail" + fnsScreenShot_Date_format() + "]" + ". Getting Exception  >> "
					+ Throwables.getStackTraceAsString(t).trim());
		}
	}

	// Return Row Number of matching TEXT in Table Cell
	public Integer fnsReturn_RowNumber_By_TableMatching_TEXT(String Table_Data_HeaderXpath, String MatchingText)
			throws Throwable {

		try {
			int RowNumber = 1;

			fnsGet_Element_Enabled(Table_Data_HeaderXpath);

			WebElement stdtable = fnsGet_OR_MtrPlan_ObjectX(Table_Data_HeaderXpath);
			List<WebElement> no_of_rows_in_list = stdtable.findElements(By.tagName("tr"));

			for (WebElement countrows : no_of_rows_in_list) {
				String RowText = countrows.getText().toLowerCase().trim();
				if (RowText.contains(MatchingText.toLowerCase().trim())) {
					break;
				}

				RowNumber++;
			}
			fnsApps_Report_Logs("PASSED == Returned Row No is<" + RowNumber + "> for matching TEXT<" + MatchingText
					+ "> for table having xpath >> " + Table_Data_HeaderXpath);
			return RowNumber;
		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot(MatchingText);
			fnsApps_Report_Logs("FAILED == Fetching Row No for matching TEXT<" + MatchingText
					+ "> from table having xpath < " + Table_Data_HeaderXpath
					+ " > is getting failed, Please refer screen shot >>" + MatchingText + fnsScreenShot_Date_format()
					+ "    Getting Exception >>" + Throwables.getStackTraceAsString(t));
			throw new Exception("FAILED == Fetching Row No for matching TEXT<" + MatchingText
					+ "> from table having xpath < " + Table_Data_HeaderXpath
					+ " > is getting failed, Please refer screen shot >>" + MatchingText + fnsScreenShot_Date_format()
					+ "    Getting Exception >>" + Throwables.getStackTraceAsString(t));
		}

	}

	/*
	 * //Count the Total no rows in Table
	 * public int fnsRetrun_TotalRowCount_OfTable(String TableXpathKey) throws
	 * Exception {
	 * 
	 * try{
	 * int RowCount=0;
	 * for(int count=0; count<10; count++){
	 * List<WebElement>
	 * table=driver.findElements(By.xpath(OR_MtrPlan.getProperty(TableXpathKey)));
	 * int RowCountValue=table.size();
	 * RowCount=RowCountValue;
	 * System.out.println("Outer RowCount    >> "+RowCount);
	 * if(RowCount>0){
	 * 
	 * System.out.println("Inner RowCount    >> "+RowCount);
	 * break;}
	 * else{Thread.sleep(500);}
	 * }
	 * System.out.println(" RowCount    >> "+RowCount);
	 * return RowCount;
	 * 
	 * }catch (Throwable t) {
	 * isTestPass = false;
	 * fnsTake_Screen_Shot("CountRowFailFrom" + TableXpathKey);
	 * fnsApps_Report_Logs("FAILED == Unable to count rows from  Table having xpath [ "
	 * +"CountRowFailFrom" + TableXpathKey + fnsScreenShot_Date_format()
	 * +" ]."+". Getting Exception  >> "+Throwables.getStackTraceAsString(t).trim())
	 * ;
	 * throw new Exception("Unable to count rows from  Table having xpath [" +
	 * TableXpathKey + "],plz see screenshot [CountRowFailFrom" + TableXpathKey +
	 * fnsScreenShot_Date_format() +
	 * "]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(t).trim());}
	 * }
	 */

	// Count the Total no rows in Table
	public int fnsRetrun_TotalRowCount_OfTable(String TableXpathKey) throws Exception {

		try {
			RowCount = 0;
			fnsGet_Element_Enabled(TableXpathKey);
			WebElement stdtable = fnsGet_OR_MtrPlan_ObjectX(TableXpathKey);
			List<WebElement> no_of_rows_in_list = stdtable.findElements(By.tagName("tr"));

			for (WebElement countrows : no_of_rows_in_list) {
				RowCount++;
			}
			RowCount--;
			fnsApps_Report_Logs("PASSED == Table Row Count is == " + RowCount);
			return RowCount;

		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot("CountRowFailFrom" + TableXpathKey);
			fnsApps_Report_Logs("FAILED == Unable to count rows from  Table having xpath [ " + "CountRowFailFrom"
					+ TableXpathKey + fnsScreenShot_Date_format() + " ]." + ". Getting Exception  >> "
					+ Throwables.getStackTraceAsString(t).trim());
			throw new Exception("Unable to count rows from  Table having xpath [" + TableXpathKey
					+ "],plz see screenshot [CountRowFailFrom" + TableXpathKey + fnsScreenShot_Date_format() + "]"
					+ ". Getting Exception  >> " + Throwables.getStackTraceAsString(t).trim());
		}
	}

	// Add Billing Code and other data by clicking on ADD Single Button
	public void fnVerify_BillingCode_Updated_through_AddSingle_Bttn(Integer AddSingleDataXpathvalue,
			String BillingLKValue, String QuanityValue, String DiscountValue, String DiscountTyprDDValue,
			String DiscountExpirationYearDDValue) throws Exception {

		try {
			String BillingLookUpEntryXpath = "//*[@id='mainForm:tabView:mpbillcodesdt:" + AddSingleDataXpathvalue
					+ ":biilCode']";
			String QuantityTextFieldXpath = "//input[@id='mainForm:tabView:mpbillcodesdt:" + AddSingleDataXpathvalue
					+ ":mptbillqty']";
			String DiscountXpath = "//*[@id='mainForm:tabView:mpbillcodesdt:" + AddSingleDataXpathvalue
					+ ":mptbilldiscnt']";
			String DiscountType_DDClickXpath = "//*[@id='mainForm:tabView:mpbillcodesdt:" + AddSingleDataXpathvalue
					+ ":mptbilldistype']/div[3]/span";
			String DiscountType_DDValueXpath = "//*[@id='mainForm:tabView:mpbillcodesdt:" + AddSingleDataXpathvalue
					+ ":mptbilldistype_panel']/div/ul";
			String DiscountExpirationYear_DDClickXpath = "//*[@id='mainForm:tabView:mpbillcodesdt:"
					+ AddSingleDataXpathvalue + ":mptdisendyr']/div[3]/span";
			String DiscountExpirationYear_DDValueXpath = "//*[@id='mainForm:tabView:mpbillcodesdt:"
					+ AddSingleDataXpathvalue + ":mptdisendyr_panel']/div/ul";
			String BillCodeSaveBttn = "//*[@id='mainForm:tabView:mpbillcodesdt:" + AddSingleDataXpathvalue
					+ ":savemptbill']";

			fnsGet_Element_Enabled("TasksTab_EditMonitorPlanTask_Popup_AddSingle_Bttn");
			fnsWait_and_Click("TasksTab_EditMonitorPlanTask_Popup_AddSingle_Bttn");
			fnsLoading_Progressingwait(2);

			WithOut_OR_fnGet_Element_Enabled(BillingLookUpEntryXpath);
			WithOut_OR_fnType(BillingLookUpEntryXpath, BillingLKValue);

			WithOut_OR_fnClear(QuantityTextFieldXpath);
			WithOut_OR_fnType(QuantityTextFieldXpath, QuanityValue);

			WithOut_OR_fnClear(DiscountXpath);
			WithOut_OR_fnType(DiscountXpath, DiscountValue);

			try {
				boolean ValueNotMatched = true;
				WithOut_OR_fnGet_Element_Enabled(DiscountType_DDClickXpath);
				Thread.sleep(500);
				WithOut_OR_fnClick(DiscountType_DDClickXpath);
				WithOut_OR_fnGet_Element_Enabled(DiscountType_DDValueXpath);
				Thread.sleep(500);
				List<WebElement> DDobjectlists = WithOut_OR_fnGet_ObjectX(DiscountType_DDValueXpath)
						.findElements(By.tagName("li"));
				for (WebElement dd_value : DDobjectlists) {
					if (dd_value.getText().equals(DiscountTyprDDValue)) {
						dd_value.click();
						ValueNotMatched = false;
						break;
					}
				}
				if (ValueNotMatched == true) {
					throw new Exception("Excel value is not matched with DropDown Value.");
				}
				fnsApps_Report_Logs("PASSED == Successfully select value [ " + DiscountTyprDDValue
						+ " ] from DD, having xpath >>  " + DiscountType_DDValueXpath);
			} catch (Throwable t) {
				fnsTake_Screen_Shot("DD_Value_Select_Fail");
				throw new Exception("Unable to select value from drop down [ " + DiscountType_DDValueXpath
						+ " ], Please refer screen shot >> DD_Value_Select_Fail" + fnsScreenShot_Date_format()
						+ ". Getting Exception  >> " + Throwables.getStackTraceAsString(t).trim());
			}

			try {
				boolean ValueNotMatched = true;
				WithOut_OR_fnGet_Element_Enabled(DiscountExpirationYear_DDClickXpath);
				Thread.sleep(500);
				WithOut_OR_fnClick(DiscountExpirationYear_DDClickXpath);
				WithOut_OR_fnGet_Element_Enabled(DiscountExpirationYear_DDValueXpath);
				Thread.sleep(500);
				List<WebElement> DDobjectlists = WithOut_OR_fnGet_ObjectX(DiscountExpirationYear_DDValueXpath)
						.findElements(By.tagName("li"));
				for (WebElement dd_value : DDobjectlists) {
					if (dd_value.getText().equals(DiscountExpirationYearDDValue)) {
						dd_value.click();
						ValueNotMatched = false;
						break;
					}
				}
				if (ValueNotMatched == true) {
					throw new Exception("Excel value is not matched with DropDown Value.");
				}
				fnsApps_Report_Logs("PASSED == Successfully select value [ " + DiscountExpirationYearDDValue
						+ " ] from DD, having xpath >>  " + DiscountExpirationYear_DDValueXpath);
			} catch (Throwable t) {
				fnsTake_Screen_Shot("DD_Value_Select_Fail");
				throw new Exception("Unable to select value from drop down [ " + DiscountExpirationYear_DDValueXpath
						+ " ], Please refer screen shot >> DD_Value_Select_Fail" + fnsScreenShot_Date_format()
						+ ". Getting Exception  >> " + Throwables.getStackTraceAsString(t).trim());
			}

			WithOut_OR_fnClick(BillCodeSaveBttn);
			fnsLoading_Progressingwait(2);

			// fnsGet_Element_Enabled("TasksTab_EditMonitorPlanTask_Popup_Success_Message_Div");

			try {
				for (int loopwait = 0; loopwait < 20; loopwait++) {
					String GetText = fnsGet_Field_TEXT("TasksTab_EditMonitorPlanTask_Popup_Success_Message_Div").trim()
							.toLowerCase();
					Integer Success_Message_Length = GetText.length();
					if (Success_Message_Length > 2 && (GetText.contains(BillingLKValue.toLowerCase()))) {
						WithOut_OR_fnMove_To_Element_BY_OFFSET(
								OR_MtrPlan.getProperty("TasksTab_EditMonitorPlanTask_Popup_Success_Message_Div"), 20,
								10);
						assert ((GetText.contains("success")))
								: "FAILED == Bill Code updated message is not coming, please refer screen shot >> Success_Message_Fail"
										+ fnsScreenShot_Date_format();
						fnsApps_Report_Logs("PASSED ==  Bill Code updated successfully.");
						break;
					} else {
						Thread.sleep(1000);
					}
					if (Success_Message_Length == 19) {
						throw new Exception(
								"FAILED == after 20 sec wait, Bill Code updated message is not coming, please refer screen shot >> Success_Message_Fail"
										+ fnsScreenShot_Date_format());
					}
				}
			} catch (Throwable t) {
				fnsTake_Screen_Shot("Success_Message_Fail");
				throw new Exception(Throwables.getStackTraceAsString(t));
			}

		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			isTestCasePass = false;
			fnsApps_Report_Logs("FAILED == Billing Update is getting Fail, Getting Exception  >> "
					+ Throwables.getStackTraceAsString(t).trim());
			throw new Exception("FAILED == Billing Update is getting Fail, Getting Exception  >> "
					+ Throwables.getStackTraceAsString(t).trim());
		}
	}

	// Add Billing Code and other data by clicking on ADD Single Button
	public void fnVerify_TestingTaskTab_BillingCode_Updated_through_AddSingle_Bttn(Integer AddSingleDataXpathvalue,
			String BillingLKValue, String QuanityValue, String DiscountValue, String DiscountTyprDDValue,
			String DiscountExpirationYearDDValue) throws Exception {

		try {
			String BillingLookUpEntryXpath = ".//*[@id='mainForm:tabView:mpttbilldt:" + AddSingleDataXpathvalue
					+ ":txtbillCode']";
			String QuantityTextFieldXpath = "//*[@id='mainForm:tabView:mpttbilldt:" + AddSingleDataXpathvalue
					+ ":txtBillCodeQuantity']";
			String DiscountXpath = ".//*[@id='mainForm:tabView:mpttbilldt:" + AddSingleDataXpathvalue
					+ ":txtBillCodeDiscount']";
			String DiscountType_DDClickXpath = "//*[@id='mainForm:tabView:mpttbilldt:" + AddSingleDataXpathvalue
					+ ":txtBillCodeDiscountType']/div[3]/span";
			String DiscountType_DDValueXpath = ".//*[@id='mainForm:tabView:mpttbilldt:" + AddSingleDataXpathvalue
					+ ":txtBillCodeDiscountType_panel']/div/ul";
			String DiscountExpirationYear_DDClickXpath = ".//*[@id='mainForm:tabView:mpttbilldt:"
					+ AddSingleDataXpathvalue + ":txtBillCodeDisEndYear']/div[3]/span";
			String DiscountExpirationYear_DDValueXpath = ".//*[@id='mainForm:tabView:mpttbilldt:"
					+ AddSingleDataXpathvalue + ":txtBillCodeDisEndYear_panel']/div/ul";
			String BillCodeSaveBttn = ".//*[@id='mainForm:tabView:mpttbilldt:" + AddSingleDataXpathvalue
					+ ":savettbill']";

			fnsGet_Element_Enabled("TestingTaskTab_EditMonitorPlanTask_Popup_AddSingle_Bttn");
			fnsWait_and_Click("TestingTaskTab_EditMonitorPlanTask_Popup_AddSingle_Bttn");
			fnsLoading_Progressingwait(2);

			WithOut_OR_fnGet_Element_Enabled(BillingLookUpEntryXpath);
			WithOut_OR_fnType(BillingLookUpEntryXpath, BillingLKValue);

			/*
			 * if(QuanityValue==null){
			 * //nothing to do
			 * }else{
			 * Thread.sleep(2000);
			 * WithOut_OR_fnClear(QuantityTextFieldXpath);
			 * WithOut_OR_fnType(QuantityTextFieldXpath, QuanityValue);
			 * //
			 * WithOut_OR_fnClear_then_Type_for_InputNotWorkingInIE(QuantityTextFieldXpath,
			 * QuanityValue);
			 * }
			 * 
			 * if(QuanityValue==null){
			 * //nothing to do
			 * }else{
			 * Thread.sleep(1000);
			 * WithOut_OR_fnClear(DiscountXpath);
			 * WithOut_OR_fnType(DiscountXpath, DiscountValue);
			 * //WithOut_OR_fnClear_then_Type_for_InputNotWorkingInIE(DiscountXpath,
			 * DiscountValue);
			 * }
			 */
			Thread.sleep(2000);

			/*
			 * try{
			 * boolean ValueNotMatched=true;
			 * WithOut_OR_fnGet_Element_Enabled(DiscountType_DDClickXpath);
			 * Thread.sleep(1000);
			 * WithOut_OR_fnClick(DiscountType_DDClickXpath);
			 * WithOut_OR_fnGet_Element_Enabled(DiscountType_DDValueXpath);
			 * Thread.sleep(1000);
			 * List<WebElement>
			 * DDobjectlists=WithOut_OR_fnGet_ObjectX(DiscountType_DDValueXpath).
			 * findElements(By.tagName("li"));
			 * for(WebElement dd_value:DDobjectlists){
			 * if(dd_value.getText().equals(DiscountTyprDDValue)){
			 * dd_value.click();
			 * ValueNotMatched=false;
			 * break;}
			 * }
			 * if(ValueNotMatched==true){
			 * throw new Exception("Excel value is not matched with DropDown Value.");
			 * }
			 * fnsApps_Report_Logs("PASSED == Successfully select value [ "
			 * +DiscountTyprDDValue+" ] from DD, having xpath >>  " +
			 * DiscountType_DDValueXpath);
			 * }catch(Throwable t) {
			 * fnsTake_Screen_Shot("DD_Value_Select_Fail");
			 * throw new Exception("Unable to select value from drop down [ "
			 * +DiscountType_DDValueXpath
			 * +" ], Please refer screen shot >> DD_Value_Select_Fail"+
			 * fnsScreenShot_Date_format()+". Getting Exception  >> "+Throwables.
			 * getStackTraceAsString(t).trim());}
			 * 
			 * 
			 * 
			 * 
			 * 
			 * try{
			 * boolean ValueNotMatched=true;
			 * WithOut_OR_fnGet_Element_Enabled(DiscountExpirationYear_DDClickXpath);
			 * Thread.sleep(1000);
			 * WithOut_OR_fnClick(DiscountExpirationYear_DDClickXpath);
			 * WithOut_OR_fnGet_Element_Enabled(DiscountExpirationYear_DDValueXpath);
			 * Thread.sleep(1000);
			 * List<WebElement>
			 * DDobjectlists=WithOut_OR_fnGet_ObjectX(DiscountExpirationYear_DDValueXpath).
			 * findElements(By.tagName("li"));
			 * for(WebElement dd_value:DDobjectlists){
			 * if(dd_value.getText().equals(DiscountExpirationYearDDValue)){
			 * dd_value.click();
			 * ValueNotMatched=false;
			 * break;}
			 * }
			 * if(ValueNotMatched==true){
			 * throw new Exception("Excel value is not matched with DropDown Value.");
			 * }
			 * fnsApps_Report_Logs("PASSED == Successfully select value [ "
			 * +DiscountExpirationYearDDValue+" ] from DD, having xpath >>  " +
			 * DiscountExpirationYear_DDValueXpath);
			 * }catch(Throwable t) {
			 * fnsTake_Screen_Shot("DD_Value_Select_Fail");
			 * throw new Exception("Unable to select value from drop down [ "
			 * +DiscountType_DDValueXpath
			 * +" ], Please refer screen shot >> DD_Value_Select_Fail"+
			 * fnsScreenShot_Date_format()+". Getting Exception  >> "+Throwables.
			 * getStackTraceAsString(t).trim());}
			 * 
			 */

			WithOut_OR_fnClick(BillCodeSaveBttn);
			fnsLoading_Progressingwait(2);

			// fnsGet_Element_Enabled("TestingTaskTab_EditMonitorPlanTask_Popup_Success_Message_Div");

			try {
				for (int loopwait = 0; loopwait < 20; loopwait++) {
					String GetText = fnsGet_Field_TEXT("TestingTaskTab_EditMonitorPlanTask_Popup_Success_Message_Div")
							.trim().toLowerCase();
					Integer Success_Message_Length = GetText.length();
					if (Success_Message_Length > 2 && (GetText.contains(BillingLKValue.toLowerCase()))) {
						WithOut_OR_fnMove_To_Element_BY_OFFSET(
								OR_MtrPlan.getProperty("TestingTaskTab_EditMonitorPlanTask_Popup_Success_Message_Div"),
								20, 10);
						assert ((GetText.contains("success")))
								: "FAILED == Bill Code updated message is not coming, please refer screen shot >> Success_Message_Fail";
						fnsApps_Report_Logs("PASSED ==  Bill Code updated successfully.");
						break;
					} else {
						Thread.sleep(1000);
					}
					if (Success_Message_Length == 19) {
						throw new Exception(
								"FAILED == after 20 sec wait, Bill Code updated message is not coming, please refer screen shot >> Success_Message_Fail");
					}
				}
			} catch (Throwable t) {
				fnsTake_Screen_Shot("Success_Message_Fail");
				throw new Exception(Throwables.getStackTraceAsString(t));
			}

		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			isTestCasePass = false;
			fnsApps_Report_Logs("FAILED == Billing Update is getting Fail, Getting Exception  >> "
					+ Throwables.getStackTraceAsString(t).trim());
			throw new Exception("FAILED == Billing Update is getting Fail, Getting Exception  >> "
					+ Throwables.getStackTraceAsString(t).trim());
		}
	}

	public void fnVerify_UpdateMsg_By_ClickingOnSaveAll_Bttn(Integer TaskDetailXpathvalue) throws Exception {

		try {
			// Xpaths
			String SaveAllBttnXpath = "//*[@id='mainForm:tabView:mptasksdt:" + (TaskDetailXpathvalue - 1)
					+ ":savealltaskwo']";

			WithOut_OR_fnGet_Element_Enabled(SaveAllBttnXpath);
			WithOut_OR_fnClick(SaveAllBttnXpath);
			fnsLoading_Progressingwait(2);

			// fnsGet_Element_Enabled("Footer");
			fnsGet_Element_Enabled("Success_Message_Div");

			try {
				for (int loopwait = 0; loopwait < 20; loopwait++) {
					String GetText = fnsGet_Field_TEXT("Success_Message_Div").trim().toLowerCase();
					Integer Success_Message_Length = GetText.length();
					if (Success_Message_Length > 2) {
						WithOut_OR_fnMove_To_Element_BY_OFFSET(OR_MtrPlan.getProperty("Success_Message_Div"), 20, 10);
						assert GetText.contains("success")
								: "FAILED == Data updated message is not coming, please refer screen shot >> Success_Message_Fail";
						fnsApps_Report_Logs("PASSED ==  Data updated message is displayed");
						break;
					} else {
						Thread.sleep(1000);
					}
					if (loopwait == 19) {
						throw new Exception(
								"FAILED == after 20 sec wait, Data updated message is not coming, please refer screen shot >> Success_Message_Fail");
					}
				}
			} catch (Throwable t) {
				fnsTake_Screen_Shot("Success_Message_Fail");
				fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
				throw new Exception(Throwables.getStackTraceAsString(t));
			}

			// fnsText_Fetch_and_Assert("Success_Message_Div", "success"); // Not working
			// after new fixes of bug ipm-3118
			// fnWait(3000);

		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot("TasksUpdateFail");
			fnsApps_Report_Logs("FAILED == Tasks Update is getting Fail, Please refer screenshot[TasksUpdateFail"
					+ fnsScreenShot_Date_format() + "]." + ". Getting Exception  >> "
					+ Throwables.getStackTraceAsString(t).trim());
			throw new Exception("FAILED == Tasks Update is getting Fail, Please refer screenshot[TasksUpdateFail"
					+ fnsScreenShot_Date_format() + "]." + ". Getting Exception  >> "
					+ Throwables.getStackTraceAsString(t).trim());
		}
	}

	// First clear the data from input field then type the value -- Approach comes
	// in feature as input field is not working in chrome.

	// Function to wait for element and then type
	public void fnsClear_then_Type_for_InputNotWorkingInIE(String XpathKey, String value) throws Exception {

		try {
			fnsGet_Element_Enabled(XpathKey);
			// fnsGet_OR_MtrPlan_ObjectX(XpathKey).sendKeys(Keys.chord(Keys.END,
			// Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE,
			// Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE), value);
			WebElement Element = fnsGet_OR_MtrPlan_ObjectX(XpathKey);
			Element.sendKeys(Keys.BACK_SPACE);
			Element.sendKeys(Keys.BACK_SPACE);
			Element.sendKeys(Keys.BACK_SPACE);
			Element.sendKeys(Keys.BACK_SPACE);
			Element.sendKeys(Keys.BACK_SPACE);
			Element.sendKeys(Keys.BACK_SPACE);
			Element.sendKeys(Keys.BACK_SPACE);
			Element.sendKeys(Keys.BACK_SPACE);
			Element.sendKeys(Keys.BACK_SPACE);
			Element.sendKeys(Keys.BACK_SPACE);
			Element.sendKeys(value);
			fnsApps_Report_Logs(
					"PASSED == Clear and Type done with Value<" + value + "> on Element having Xpath  >> " + XpathKey);
		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable e) {
			isTestCasePass = false;
			fnsTake_Screen_Shot("UnableToClearAndType_");
			fnsApps_Report_Logs("FAILED == Unable To  Clear and Type on Element having Xpath  >> " + XpathKey
					+ ", plz see screenshot [ UnableToClearAndType_" + fnsScreenShot_Date_format() + " ]"
					+ ". Getting Exception  >> " + Throwables.getStackTraceAsString(e));
			throw new Exception("FAILED == Unable To  Clear and Type on element [ " + XpathKey
					+ " ] , plz see screenshot [ UnableToClearAndType_" + fnsScreenShot_Date_format() + " ]"
					+ ". Getting Exception  >> " + Throwables.getStackTraceAsString(e));
		}
	}

	// Function to wait for element and then type
	public void WithOut_OR_fnClear_then_Type_for_InputNotWorkingInIE(String XpathKey, String value) throws Exception {

		try {
			WithOut_OR_fnGet_Element_Enabled(XpathKey);

			WebElement Element = WithOut_OR_fnGet_ObjectX(XpathKey);
			Element.sendKeys(Keys.BACK_SPACE);
			Element.sendKeys(Keys.BACK_SPACE);
			Element.sendKeys(Keys.BACK_SPACE);
			Element.sendKeys(Keys.BACK_SPACE);
			Element.sendKeys(Keys.BACK_SPACE);
			Element.sendKeys(Keys.BACK_SPACE);
			Element.sendKeys(Keys.BACK_SPACE);
			Element.sendKeys(Keys.BACK_SPACE);
			Element.sendKeys(Keys.BACK_SPACE);
			Element.sendKeys(Keys.BACK_SPACE);
			Element.sendKeys(value);
			fnsApps_Report_Logs(
					"PASSED == Clear and Type done with Value<" + value + "> on Element having Xpath  >> " + XpathKey);
		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable e) {
			isTestCasePass = false;
			fnsTake_Screen_Shot_Without_OR("UnableToClearAndType_");
			fnsApps_Report_Logs("FAILED == Unable To  Clear and Type on Element having Xpath  >> " + XpathKey
					+ ", plz see screenshot [ UnableToClearAndType_" + fnsScreenShot_Date_format() + " ]"
					+ ". Getting Exception  >> " + Throwables.getStackTraceAsString(e));
			throw new Exception("FAILED == Unable To  Clear and Type on element [ " + XpathKey
					+ " ] , plz see screenshot [ UnableToClearAndType_" + fnsScreenShot_Date_format() + " ]"
					+ ". Getting Exception  >> " + Throwables.getStackTraceAsString(e));
		}
	}

	// Add Billing Code and other data by clicking on ADD Single Button
	public String fnVerify_Fetch_PriceValues_of_BillingCode(Integer TaskDetailXpathvalue, String BillCodeValue)
			throws Exception {

		try {
			String TableHeaderXpath = ".//*[@id='mainForm:tabView:mptasksdt:" + (TaskDetailXpathvalue - 1)
					+ ":mpbillcodesdt']/div/table";

			String TableCellHeaderXpath = ".//*[@id='mainForm:tabView:mptasksdt:" + (TaskDetailXpathvalue - 1)
					+ ":mpbillcodesdt_data']";

			int BillCode_RowNo = WithOut_OR_fnsReturn_RowNumber_By_TableMatching_TEXT(TableHeaderXpath,
					TableCellHeaderXpath, BillCodeValue);

			Integer PriceColumnNo = WithOut_OR_Retrun_TableColumnNo_of_MatchingColumnName(TableHeaderXpath,
					"Final Unit Price");

			String BillCode_Price_Xpath = "//*[@id='mainForm:tabView:mptasksdt:" + (TaskDetailXpathvalue - 1)
					+ ":mpbillcodesdt_data']/tr[" + BillCode_RowNo + "]/td[" + PriceColumnNo + "]";
			String BillCode_PriceValue = WithOut_OR_fnsGet_Field_TEXT(BillCode_Price_Xpath).trim();
			WithOut_OR_fnMove_To_Element_and_DoubleClick(BillCode_Price_Xpath);

			System.out.println(BillCodeValue + " >> PriceValue=" + BillCode_PriceValue);
			return BillCode_PriceValue;

		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot("BillPriceValueFail");
			fnsApps_Report_Logs("FAILED == Unable to Fetch BillPriceValue, Please refer screenshot[BillPriceValueFail"
					+ fnsScreenShot_Date_format() + "]." + ". Getting Exception  >> "
					+ Throwables.getStackTraceAsString(t).trim());
			throw new Exception("FAILED == Unable to Fetch BillPriceValue, Please refer screenshot[BillPriceValueFail"
					+ fnsScreenShot_Date_format() + "]." + ". Getting Exception  >> "
					+ Throwables.getStackTraceAsString(t).trim());
		}
	}

	public List fnFetchBillCodeRate_from_DB(String BillCode, String Year, String OrgUnit) throws Throwable {

		List<String> BillCodePriceStringList = new ArrayList<String>();

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			fnsApps_Report_Logs(
					"=========================================================================================================================================");
			fnsApps_Report_Logs(
					"*********************************************** Oracle JDBC Driver is not found ******************************************");
			throw new Exception(
					"*********************************************** Oracle JDBC Driver is not found ******************************************");
		}
		Connection connection = null;

		try {
			/*
			 * if (env.toLowerCase().equalsIgnoreCase("test")){
			 * connection =
			 * DriverManager.getConnection("jdbc:oracle:thin:@dbserv13vm:1521:certtest",
			 * "testscriptuser", "test4nsf");
			 * }else if(env.toLowerCase().equalsIgnoreCase("staging")){
			 * connection =
			 * DriverManager.getConnection("jdbc:oracle:thin:@dbserv30:1521:CERTSTAG",
			 * "testscriptuser", "welcome1010");
			 * }else{
			 * throw new Exception ("FAILED == Environment variable is not defined.");
			 * }
			 */

			connection = fnpGetDBConnection();

			Statement stmt = connection.createStatement();

			// Query.
			String query = "SELECT rate FROM nsf_ipulse_bill_codes_rate_mvw v " +
					"WHERE TRIM (UPPER (v.bill_code)) = TRIM (UPPER ('" + BillCode + "')) " +
					"and rev_prog_code = '" + OrgUnit + "' " +
					"AND DECODE ('" + Year + "' " +
					", TO_CHAR (SYSDATE, 'RRRR'), SYSDATE, TO_DATE ('1/1/' || " +
					"'" + Year + "', 'mm/dd/yyyy')) BETWEEN v.start_date_active " +
					"AND DECODE (v.end_date_active, NULL, TO_DATE ('1/1/2200', 'mm/dd/yyyy'), v.end_date_active)";

			ResultSet rst = stmt.executeQuery(query);

			while (rst.next()) {
				BillCodePriceStringList.add(rst.getString("rate"));
				System.out.println("satya test = " + BillCodePriceStringList);
			}
			rst.close();

			connection.close();

		} catch (SQLException e) {
			fnsApps_Report_Logs(
					"=========================================================================================================================================");
			fnsApps_Report_Logs(
					"*********************************************** Connection Failed! with Database,   Getting Error >>  "
							+ Throwables.getStackTraceAsString(e).trim());
			throw new Exception(
					"*********************************************** Connection Failed! with Database,   Getting Error >>  "
							+ Throwables.getStackTraceAsString(e).trim());
		} finally {
			if (!(connection == null)) {
				connection.close();
			}
		}

		fnsApps_Report_Logs("PASSED == Successfully Retrieve Bill Rate Value from Oracle Data Base.");
		System.out.println(" Satya  BillCodePriceStringList = " + BillCodePriceStringList);
		return BillCodePriceStringList;

	}

	// ###################################################################
	// Configuration Function
	// ############################################################################
	// Function to Launch browser and login
	public boolean fnsLaunchBrowserAndLogin() throws Throwable {
		boolean present;
		startExecutionTime = fnpTimestamp();
		// fnpFirstTimeInitializationMethod();
		ScreenShotFlagWithOR_MP = true;

		try {
			fns_Launch_Browser_Only();

			// fnsIpulse_Login_Split_Excel_Urls("secureipulse",
			// CONFIG.getProperty("MP_LoginAs"), CONFIG.getProperty("adminUsername"),
			// CONFIG.getProperty("adminPassword"));
			/*
			 * fnsIpulse_Login_Split_Excel_Urls("ipulse", CONFIG.getProperty("MP_LoginAs"),
			 * CONFIG.getProperty("adminUsername"), CONFIG.getProperty("adminPassword"));
			 */
			fnsIpulse_Login_SSO("ipulse", CONFIG.getProperty("MP_LoginAs"), CONFIG.getProperty("adminUsername"),
					CONFIG.getProperty("adminPassword"));

			present = true;

			if (!veryFirstTimeAfterLogin) {

				veryFirstTimeAfterLogin = true;
			}
			if (ApplicationVersion_Flag) {
				TestSuiteBase_NSFOnline.fnsIPulse_Application_Version("MP");
				ApplicationVersion_Flag = false;
			}

		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot_Without_OR("LaunchBrowserAndLoginFail");
			present = false;
			// ErrorUtil.addVerificationFailure(t);
			// driver.quit();
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

	public void fnsIpulse_Login(String SiteUrlVariableName_FromConfig) throws Throwable {

		try {
			// start = new Date();
			String userName = CONFIG.getProperty("adminUsername");
			String password = CONFIG.getProperty("adminPassword");

			String siteUrl = null;

			if (excelSiteURL != null) {
				if (excelSiteURL.equalsIgnoreCase("")) {
					siteUrl = CONFIG.getProperty(SiteUrlVariableName_FromConfig);
				} else {
					siteUrl = excelSiteURL;

				}
			} else {
				siteUrl = CONFIG.getProperty(SiteUrlVariableName_FromConfig);
			}

			fnsApps_Report_Logs("Application URL is == [ " + siteUrl + " ].");
			driver.get(siteUrl);
			//

			try {
				fnsComman_BrowserLogin_ElementWait(OR_MtrPlan.getProperty("UserName"), "60");
			} catch (Throwable t) {
				throw new Exception(".... Application [ " + siteUrl
						+ " ] is not getting open, after 60 seconds wait, please refer the screen shot >> LoginFail"
						+ fnsScreenShot_Date_format());
			}

			// Added on 7.6.2016
			// Step 1. Login with testscriptuser and enter the invalid password -> The
			// application should throw Invalid message to the user.
			// Step 2. Again login with valid password and the system should be logged in
			// and proceed with further steps.
			if (!iPulse_Invalid_Password_Verification) {
				WithOut_OR_fnGet_ObjectX(OR_MtrPlan.getProperty("UserName")).clear();
				WithOut_OR_fnGet_ObjectX(OR_MtrPlan.getProperty("UserName")).sendKeys("");
				WithOut_OR_fnGet_ObjectX(OR_MtrPlan.getProperty("UserName")).sendKeys(userName);
				WithOut_OR_fnGet_ObjectX(OR_MtrPlan.getProperty("password")).sendKeys("");
				WithOut_OR_fnGet_ObjectX(OR_MtrPlan.getProperty("password")).sendKeys(password + "Test");
				WithOut_OR_fnGet_ObjectX(OR_MtrPlan.getProperty("Login")).click();

				try {
					for (int i = 0; i <= Long.parseLong(CONFIG.getProperty("Element_MAX_WaitTime")); i++) {

						if (i == Long.parseLong(CONFIG.getProperty("Element_MAX_WaitTime"))) {
							throw new Exception(
									"FAILED == For invalid password ,validation message is not coming after <"
											+ Long.parseLong(CONFIG.getProperty("Element_MAX_WaitTime"))
											+ ">seconds wait");
						} else {
							Thread.sleep(1000);
						}

						if (driver.findElements(By.xpath(OR_MtrPlan.getProperty("logOutBtn"))).size() > 0) {
							fnsApps_Report_Logs(
									"FAILED == Verification of iPulse Login for Invalid Password is failed");
							throw new Exception(
									"FAILED == Verification of iPulse Login for Invalid Password is failed");
						} else if (driver.findElements(By.xpath(OR_MtrPlan.getProperty("Login_errorMessage")))
								.size() > 0) {
							fnsApps_Report_Logs("PASSED == Successfully verified iPulse login for invalid password.");
							break;
						}
					}
				} catch (Throwable t) {
					fnsTake_Screen_Shot_Without_OR("Invalid_Password_Verification_Fail");
					fnsApps_Report_Logs(Throwables.getStackTraceAsString(t)
							+ ", please refer screen shot >> Invalid_Password_Verification_Fail"
							+ fnsScreenShot_Date_format());
					throw new Exception(Throwables.getStackTraceAsString(t)
							+ ", please refer screen shot >> Invalid_Password_Verification_Fail"
							+ fnsScreenShot_Date_format());
				}

			}
			Thread.sleep(1500);

			WithOut_OR_fnGet_ObjectX(OR_MtrPlan.getProperty("UserName")).clear();
			WithOut_OR_fnGet_ObjectX(OR_MtrPlan.getProperty("UserName")).sendKeys("");
			WithOut_OR_fnGet_ObjectX(OR_MtrPlan.getProperty("UserName")).sendKeys(userName);
			WithOut_OR_fnGet_ObjectX(OR_MtrPlan.getProperty("password")).sendKeys("");
			WithOut_OR_fnGet_ObjectX(OR_MtrPlan.getProperty("password")).sendKeys(password);
			WithOut_OR_fnGet_ObjectX(OR_MtrPlan.getProperty("Login")).click();

			if (!iPulse_Invalid_Password_Verification) {
				Thread.sleep(5000);
				fnsComman_BrowserLogin_ElementWait(OR_MtrPlan.getProperty("logOutBtn"),
						CONFIG.getProperty("Element_MAX_WaitTime"));
				iPulse_Invalid_Password_Verification = true;
			} else {
				fnsLoading_Progressingwait(7);
			}
			Thread.sleep(1000);

			for (int i = 0; i <= Long.parseLong(CONFIG.getProperty("Element_MAX_WaitTime")); i++) {

				if (i == Long.parseLong(CONFIG.getProperty("Element_MAX_WaitTime"))) {
					throw new Exception("After Login, Home Page is not getting load after <"
							+ Long.parseLong(CONFIG.getProperty("Element_MAX_WaitTime")) + ">seconds wait.");
				} else {
					Thread.sleep(1000);
				}

				if (driver.findElements(By.xpath(OR_MtrPlan.getProperty("Login_errorMessage"))).size() > 0) {
					String ErrorMsg = WithOut_OR_fnGet_ObjectX(OR_MtrPlan.getProperty("Login_errorMessage")).getText();
					throw new Exception("I-pulse Login Failed, Application Error <" + ErrorMsg + ">");
				} else

				/*
				 * //if(driver.findElements(By.xpath(OR_MtrPlan.getProperty("logOutBtn"))).size(
				 * )>0)
				 */ {
					fnsComman_BrowserLogin_ElementWait(OR_MtrPlan.getProperty("logOutBtn"),
							CONFIG.getProperty("Element_MAX_WaitTime"));
					break;
				}

			}
		} catch (Throwable t) {
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}

	/*
	 * iPulse: https://testapps.nsf.org/trunkecap/index.jsp
	 * SecureiPulse: https://testapps.nsf.org/trunkecap/jsp/login/securelogin.xhtml
	 */ public void fnsIpulse_Login_Split_Excel_Urls(String PortalNamne, String LoginAs, String userName,
			String password) throws Throwable {

		try {

			// iPulse_SecureLogin_Flag=true
			if (PortalNamne.toLowerCase().trim().equalsIgnoreCase("Alerts_ipulse")) {
				if (LoginAs.toLowerCase().equalsIgnoreCase("testscriptuser")) {
					PortalNamne = "ipulse";
				} else {
					PortalNamne = "secureipulse";
				}
			} else if (((PortalNamne.toLowerCase().trim().equalsIgnoreCase("ipulse_Listings")))) {
				/* PortalNamne = "ipulse"; */
				PortalNamne = "secureipulse";
			} else if (!((PortalNamne.toLowerCase().trim().equalsIgnoreCase("ipulse_newnsfonline")))) {
				if ((PortalNamne.toLowerCase().trim().contains("grip"))) {
					PortalNamne = "secureipulse";
				} else if ((CONFIG.getProperty("iPulse_SecureLogin_Flag").toLowerCase().trim()
						.equalsIgnoreCase("true"))) {
					PortalNamne = "secureipulse";
				} else {
					PortalNamne = "ipulse";
				}
			}
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

			if ((PortalNamne.toLowerCase().trim()).equalsIgnoreCase("secureipulse")) {

				if (excelSiteURL != null) {
					if (excelSiteURL.equalsIgnoreCase("")) {
						siteUrl = CONFIG.getProperty("SecuretestSiteName");
					} else {

						String iPulseSecureSiteUrl = excelSiteURL.split("SecureiPulse:")[1].trim();

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
					siteUrl = "https://testapps.nsf.org/trunkecap/jsp/login/securelogin.xhtml";
					// siteUrl = "http://oraapp10.nsf.org:8070/ecap/";
				} else if (env.toLowerCase().equalsIgnoreCase("staging")) {
					// siteUrl = "https://stgapps.nsf.org/ecap/jsp/login/securelogin.xhtml";
					siteUrl = "https://stgapps.nsf.org/ecap/";
				}
			}

			fnsApps_Report_Logs("Application credentials are URL[ " + siteUrl + " ], UserName[ " + userName + " ].");
			driver.get(siteUrl);

			fnsComman_BrowserLogin_ElementWait(OR_MtrPlan.getProperty("UserName"),
					CONFIG.getProperty("Element_MAX_WaitTime"));

			if (((PortalNamne.toLowerCase().trim()).equalsIgnoreCase("secureipulse"))
					|| (((PortalNamne.toLowerCase().trim()).equalsIgnoreCase("ipulse_newnsfonline"))
							&& ((siteUrl.toLowerCase().trim()).contains("securelogin")))) {
				WithOut_OR_fnGet_ObjectX(OR_MtrPlan.getProperty("LoginAs")).clear();
				WithOut_OR_fnGet_ObjectX(OR_MtrPlan.getProperty("LoginAs")).sendKeys("");
				WithOut_OR_fnGet_ObjectX(OR_MtrPlan.getProperty("LoginAs")).sendKeys(LoginAs);
			}

			WithOut_OR_fnGet_ObjectX(OR_MtrPlan.getProperty("UserName")).clear();
			WithOut_OR_fnGet_ObjectX(OR_MtrPlan.getProperty("UserName")).sendKeys("");
			WithOut_OR_fnGet_ObjectX(OR_MtrPlan.getProperty("UserName")).sendKeys(userName);
			WithOut_OR_fnGet_ObjectX(OR_MtrPlan.getProperty("password")).sendKeys("");
			WithOut_OR_fnGet_ObjectX(OR_MtrPlan.getProperty("password")).sendKeys(password);
			WithOut_OR_fnGet_ObjectX(OR_MtrPlan.getProperty("Login")).click();

			fnsLoading_Progressingwait(4);
			Thread.sleep(1000);
			// WithOut_OR_fnGet_ObjectX(OR_MtrPlan.getProperty("xpathForAck")).click();
			for (int i = 0; i <= Long.parseLong(CONFIG.getProperty("Element_MAX_WaitTime")); i++) {

				if (i == Long.parseLong(CONFIG.getProperty("Element_MAX_WaitTime"))) {
					throw new Exception("After Login, Home Page is not getting load after <"
							+ Long.parseLong(CONFIG.getProperty("Element_MAX_WaitTime")) + ">seconds wait.");
				} else {
					Thread.sleep(1000);
				}

				if (driver.findElements(By.xpath(OR_MtrPlan.getProperty("Login_errorMessage"))).size() > 0) {
					String ErrorMsg = WithOut_OR_fnGet_ObjectX(OR_MtrPlan.getProperty("Login_errorMessage")).getText();
					throw new Exception("I-pulse Login Failed, Application Error <" + ErrorMsg + ">");
				} else

				/*
				 * //if(driver.findElements(By.xpath(OR_MtrPlan.getProperty("logOutBtn"))).size(
				 * )>0)
				 */ {
					// fnsComman_BrowserLogin_ElementWait(OR_MtrPlan.getProperty("logOutBtn"),
					// CONFIG.getProperty("Element_MAX_WaitTime")); //commented because new alert
					// 'Acknowledge' is coming now -13.3.2020
					/*
					 * fnsComman_BrowserLogin_ElementWait(OR_MtrPlan.getProperty("Acknowledge_bttn")
					 * , CONFIG.getProperty("Element_MAX_WaitTime"));
					 * fnsGet_Element_Enabled("Acknowledge_bttn");
					 * Thread.sleep(2000);
					 * WithOut_OR_fnGet_ObjectX(OR_MtrPlan.getProperty("Acknowledge_bttn")).click();
					 * Thread.sleep(1000);
					 */

					fnsComman_BrowserLogin_ElementWait(OR_MtrPlan.getProperty("Acknowledge_bttn"),
							CONFIG.getProperty("Element_MAX_WaitTime"));
					TestSuiteBase_New_NSFOnline.fnsLoading_PageLoad(600);
					fnsGet_Element_Enabled("Acknowledge_bttn");
					Thread.sleep(2000);
					fnsWait_and_Click("Acknowledge_bttn");
					fnsLoading_Progressingwait(4);
					break;
				}

			}
		} catch (Throwable t) {
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}

	@SuppressWarnings("finally")
	public boolean fns_Launch_Browser_Only() throws Throwable {
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

				// System.setProperty("webdriver.chrome.driver",
				// CONFIG.getProperty("ChromeDriverPath"));
				WebDriverManager.chromedriver().setup();
				// DesiredCapabilities caps = DesiredCapabilities.chrome();
				// ChromeOptions options = new ChromeOptions();
				caps = DesiredCapabilities.chrome();
				options = new ChromeOptions();

				// if( ! ( (ip_Address.equalsIgnoreCase("192.168.102.40") ) ||
				// (ip_Address.equalsIgnoreCase("10.223.241.14")) ||
				// (ip_Address.equalsIgnoreCase("10.223.241.15")) ||
				// (ip_Address.equalsIgnoreCase("10.223.241.16")) ||
				// (ip_Address.equalsIgnoreCase("10.223.241.58")) ) ){
				options.addArguments("--incognito");
				// }

				options.addArguments("--disable-infobars");
				options.addArguments("--start-maximized");
				/* options.addArguments("--disable-popup-blocking"); */
				// options.addArguments("--disable-web-security"); // Blocking 3rd party cookies
				// in listing and nsfconnectaccountsetup
				options.addArguments("--no-proxy-server");

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

				fnsApps_Report_Logs("Browser type is CHROME");
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

	@SuppressWarnings("unchecked")
	public HashMap<String, String> fnLoadHashData(HashMap hashXlData, Xls_Reader xls, String sheetName, int startRow) {

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
		fns_CheckSiteSkip("MonitorPlan_suite");
	}

	// Always run after suite
	@AfterSuite(alwaysRun = true)
	public void Finishing_MonitorPlan_suite_Script() throws Throwable {
		ScreenShotFlagWithOR_MP = false;
		fnsApps_Report_Logs("");
		fnsApps_Report_Logs(
				"######################################################## MonitorPlan Suite END ######################################################## ");
		fnsApps_Report_Logs(
				"=========================================================================================================================================");
		fnsApps_Report_Logs("");
	}

	// Get Excel Cell value by column name
	public String fnsReturn_ExcelCellValue_ByMatchingColumnName(String SheetName, String ColumnName) throws Throwable {
		try {
			String CellValue = null;
			for (int i = 5; i < 50; i++) {
				for (int j = 1; j < 20; j++) {
					String ExcelCellValue = MonitorPlan_suitexls.getCellData(SheetName, j, i);
					if (ExcelCellValue.equalsIgnoreCase(ColumnName)) {
						CellValue = MonitorPlan_suitexls.getCellData(SheetName, j, i + 1);
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
			fnsApps_Report_Logs("Excel Column (" + ColumnName + ") value is = " + CellValue);
			return CellValue;
		} catch (Throwable t) {
			isTestCasePass = false;
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}

	// Comman method to wirte pass/fail into excel/log file.
	public void fns_ReportTestResult_atClassLevel(Xls_Reader Suite_Excel_Variable_Name, String Class_Name,
			boolean isTestCasePass) throws Throwable {

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

	// Useless
	// Comman method to wirte pass/fail into excel/log file.
	public void fns_ReportTestResult_atClassLevel(Xls_Reader Suite_Excel_Variable_Name, String Class_Name)
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

	public void fnsMoveMousePointerAtCenterBottomOfScreen() throws Throwable {

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		ScreenWidth = screenSize.getWidth();
		ScreenHeight = screenSize.getHeight();

		Robot robot = new Robot();
		robot.mouseMove((int) ScreenWidth / 2, ((int) ScreenHeight + 200));
		Thread.sleep(1000);
	}
	// #################################################################################
	// OLD Methods for Aspirago (Loading, mouse over, data provider)
	// ############################################################################
	// ################################################################################
	// ##########################################################
	/*
	 * public void fnsLoading_Progressingwait(int waitcount) throws Throwable{
	 * try{
	 * LoadingImageFlag = true;
	 * Actual_Loading_Time = 1;
	 * TimeOut = 0;
	 * for(int wait=2;
	 * wait<=((Long.parseLong(CONFIG.getProperty("Element_MAX_WaitTime"))));
	 * wait++){
	 * if(LoadingImageFlag){
	 * Thread.sleep(1000);
	 * // System.out.println("====================Enter Thread"+wait);
	 * }
	 * if( (driver.findElement(By.xpath(OR_MtrPlan.getProperty("IPulse_Loading3"))).
	 * isDisplayed()) ||
	 * (driver.findElements(By.xpath(OR_MtrPlan.getProperty("IPulse_Loading"))).size
	 * ()>0) ||
	 * (driver.findElements(By.xpath(OR_MtrPlan.getProperty("IPulse_Loading2"))).
	 * size()>0) ){ ||
	 * (driver.findElement(By.xpath(OR_MtrPlan.getProperty("IPulse_Loading"))).
	 * isDisplayed())Not working
	 * Actual_Loading_Time++;
	 * Thread.sleep(1000);
	 * LoadingImageFlag = false;
	 * // System.out.println(driver.findElement(By.xpath(OR_MtrPlan.getProperty(
	 * "IPulse_Loading3"))).isDisplayed());
	 * }
	 * if( (
	 * !(driver.findElement(By.xpath(OR_MtrPlan.getProperty("IPulse_Loading3"))).
	 * isDisplayed()) ) &&
	 * ((driver.findElements(By.xpath(OR_MtrPlan.getProperty("IPulse_Loading"))).
	 * size())==0) &&
	 * ((driver.findElements(By.xpath(OR_MtrPlan.getProperty("IPulse_Loading2"))).
	 * size())==0) && wait > waitcount ){
	 * fnsApps_Report_Logs("***** Loading image displayed for < "
	 * +Actual_Loading_Time+" > seconds. *****");
	 * break;
	 * }
	 * 
	 * TimeOut = wait;
	 * if(TimeOut==(Long.parseLong(CONFIG.getProperty("Element_MAX_WaitTime")))){
	 * // System.out.println("====================Enter timeout"+TimeOut);
	 * throw new Exception("Loading Issue : After <"+(TimeOut)
	 * +">Seconds wait page is not getting Load, please refer screenshot >>PageLoadFail1"
	 * + fnsScreenShot_Date_format());
	 * }
	 * }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * // System.out.
	 * println("Before Page Source ======================================================== "
	 * +fnsLOGS_Date_format());
	 * PageSourceText = driver.getPageSource().toLowerCase();
	 * if(PageSourceText.contains("we are sorry")){
	 * try{
	 * WithOut_OR_fnMove_To_Element(OR_MtrPlan.getProperty("IPulse_MainErrorMsg"));
	 * throw new IllegalArgumentException();
	 * }catch(Throwable t){
	 * ErrorMsgText =
	 * "We are sorry...an unexpected error has occurred. Details have been forwarded to the Application Support Team for investigation. If the problem persists, please submit an issue on the Apps Portal."
	 * ;
	 * throw new IllegalArgumentException();
	 * }
	 * // ErrorMsgText =
	 * "We are sorry...an unexpected error has occurred. Details have been forwarded to the Application Support Team for investigation. If the problem persists, please submit an issue on the Apps Portal."
	 * ;
	 * // throw new IllegalArgumentException();
	 * }
	 * 
	 * if(PageSourceText.contains("ui-messages-error-summary")){
	 * if (fnsGet_OR_MtrPlan_ObjectX("IPulse_MainErrorMsg").isDisplayed()) {
	 * WithOut_OR_fnMove_To_Element(OR_MtrPlan.getProperty("IPulse_MainErrorMsg"));
	 * ErrorMsgText = fnsGet_OR_MtrPlan_ObjectX("IPulse_MainErrorMsg").getText();
	 * throw new IllegalArgumentException();
	 * }
	 * }
	 * // System.out.
	 * println("after Page Source ======================================================== "
	 * +fnsLOGS_Date_format());
	 * }catch(IllegalArgumentException i){
	 * isTestCasePass = false;
	 * fnsTake_Screen_Shot_Without_OR("UnExpectedError");
	 * fnsApps_Report_Logs("Getting Un-Expected Application Error<"
	 * +ErrorMsgText+">, please refer screenshot >>UnExpectedError" +
	 * fnsScreenShot_Date_format()+"  Exception ("+Throwables.getStackTraceAsString(
	 * i));
	 * throw new Exception("Getting Un-Expected Application Error<"
	 * +ErrorMsgText+">, please refer screenshot >> UnExpectedError" +
	 * fnsScreenShot_Date_format()+"  Exception ("+Throwables.getStackTraceAsString(
	 * i));
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * }catch(Throwable t){
	 * try{
	 * if(TimeOut==(Long.parseLong(CONFIG.getProperty("Element_MAX_WaitTime")))){
	 * throw new Exception("Loading Issue : After <"+(TimeOut)
	 * +">Seconds wait page is not getting Load, please refer screenshot >>PageLoadFail2"
	 * +
	 * fnsScreenShot_Date_format()+"  Exception ("+Throwables.getStackTraceAsString(
	 * t)+")");
	 * }else{
	 * TimeOut = 0;
	 * }
	 * Thread.sleep(2000);
	 * if(driver.findElements(By.xpath(OR_MtrPlan.getProperty("IPulse_Loading"))).
	 * size()>0){
	 * for(int wait=1;
	 * wait<Long.parseLong(CONFIG.getProperty("Element_MAX_WaitTime")); wait++){
	 * if(
	 * (driver.findElements(By.xpath(OR_MtrPlan.getProperty("IPulse_Loading"))).size
	 * ()>0) ||
	 * (driver.findElements(By.xpath(OR_MtrPlan.getProperty("IPulse_Loading2"))).
	 * size()>0) ){
	 * Actual_Loading_Time++;
	 * Thread.sleep(1000); }
	 * if(((driver.findElements(By.xpath(OR_MtrPlan.getProperty("IPulse_Loading"))).
	 * size())==0)){
	 * fnsApps_Report_Logs("##### Loading image displayed for < "
	 * +Actual_Loading_Time+" > seconds. #######");
	 * break; }
	 * TimeOut = wait;
	 * }
	 * if(TimeOut==10){
	 * throw new Exception("Loading Issue : After <"
	 * +TimeOut+">Seconds wait page is not getting Load, please refer screenshot >>PageLoadFail3"
	 * +
	 * fnsScreenShot_Date_format()+"  Exception ("+Throwables.getStackTraceAsString(
	 * t)+")");
	 * }
	 * }
	 * 
	 * }catch(NullPointerException N){
	 * fnsApps_Report_Logs("Getting loading NullPointer Exception.");
	 * Thread.sleep(waitcount*1000);
	 * 
	 * }catch(Throwable tt){
	 * isTestCasePass = false;
	 * fnsTake_Screen_Shot_Without_OR("PageLoadFail");
	 * fnsApps_Report_Logs(Throwables.getStackTraceAsString(tt));
	 * throw new Exception(Throwables.getStackTraceAsString(tt)); }
	 * }
	 * }
	 */

	public void fnsLoading_Progressingwait_NsfOnline(int waitcount) throws Throwable {
		try {
			TraQtionOnline_LoadingImageFlag = true;
			TimeOut = 0;
			Integer PageLoadWait = TestSuiteBase_MonitorPlan.fnsLoading_PageLoad();
			for (int wait = PageLoadWait; wait <= ((Long
					.parseLong(CONFIG.getProperty("Element_MAX_WaitTime")))); wait++) {
				if (TraQtionOnline_LoadingImageFlag) {
					Thread.sleep(1000);
					// System.out.println("====================Loading thread"+wait);
				}
				if (driver.findElement(By.id("progressLayer")).isDisplayed()) {
					Thread.sleep(1000);
					TraQtionOnline_LoadingImageFlag = false;
					// System.out.println("====================Loading Enabled"+wait);
					// System.out.println("Loading Img value ========================
					// "+driver.findElement(By.xpath(OR_MtrPlan.getProperty("LoadingImg"))).isDisplayed());
				}
				if (!(driver.findElement(By.id("progressLayer")).isDisplayed()) && wait > waitcount) {
					// System.out.println("====================Loading Completed"+wait);
					break;
				}

				TimeOut = wait;
				if (TimeOut == (Long.parseLong(CONFIG.getProperty("Element_MAX_WaitTime")))) {
					// System.out.println("====================Enter timeout"+TimeOut);
					throw new Exception("Loading Issue : After <" + (TimeOut)
							+ ">Seconds wait page is not getting Load, please refer screenshot >>PageLoadFail1"
							+ fnsScreenShot_Date_format());
				}
			}

			PageSourceText = driver.getPageSource().toLowerCase();
			if (PageSourceText.contains("validationmessages")) {
				if (fnsGet_OR_MtrPlan_ObjectX("NSFOnline_ErrorMsg").isDisplayed()) {
					WebElement Element = fnsGet_OR_MtrPlan_ObjectX("NSFOnline_ErrorMsg");
					Actions act = new Actions(driver);
					act.moveToElement(Element).build().perform();
					ErrorMsgText = fnsGet_OR_MtrPlan_ObjectX("NSFOnline_ErrorMsg").getText();
					throw new IllegalArgumentException();
				}
			}
		} catch (IllegalArgumentException i) {
			isTestCasePass = false;
			fnsTake_Screen_Shot_Without_OR("UnExpectedError");
			fnsApps_Report_Logs("Getting Un-Expected Application Error<" + ErrorMsgText
					+ ">, please refer screenshot >>UnExpectedError" + fnsScreenShot_Date_format() + "  Exception ("
					+ i.getMessage() + ")");
			throw new Exception("Getting Un-Expected Application Error<" + ErrorMsgText
					+ ">, please refer screenshot >> UnExpectedError" + fnsScreenShot_Date_format() + "  Exception ("
					+ i.getMessage() + ")");

		} catch (Throwable t) {
			try {
				// System.out.println("=============================== Laoding throwable
				// =============");
				if (TimeOut == (Long.parseLong(CONFIG.getProperty("Element_MAX_WaitTime")))) {
					throw new Exception("Loading Issue : After <" + (TimeOut)
							+ ">Seconds wait page is not getting Load, please refer screenshot >>PageLoadFail2"
							+ fnsScreenShot_Date_format() + "  Exception (" + t.getMessage() + ")");
				} else {
					TimeOut = 0;
				}
				Thread.sleep(5000);
				for (int wait = 1; wait < 11; wait++) {
					if (driver.findElement(By.id("progressLayer")).isDisplayed()) {
						Thread.sleep(1000);
					} else {
						break;
					}
					TimeOut = wait;
				}
				if (TimeOut == 10) {
					throw new Exception("Loading Issue : After <"
							+ ((Long.parseLong(CONFIG.getProperty("Element_MAX_WaitTime"))) + TimeOut)
							+ ">Seconds wait page is not getting Load, please refer screenshot >>PageLoadFail3"
							+ fnsScreenShot_Date_format() + "  Exception (" + t.getMessage() + ")");
				}

			} catch (NullPointerException N) {
				fnsApps_Report_Logs("Getting loading NullPointer Exception.");
				Thread.sleep(waitcount * 1000);

			} catch (InvalidSelectorException k) {
				fnsApps_Report_Logs("Getting loading InvalidSelector Exception.");
				Thread.sleep(waitcount * 1000);
			} catch (NoSuchElementException NE) {
				fnsApps_Report_Logs("Getting loading NoSuchElement Exception.");
				Thread.sleep(waitcount * 1000);
			} catch (Throwable tt) {
				isTestCasePass = false;
				fnsTake_Screen_Shot_Without_OR("PageLoadFail");
				fnsApps_Report_Logs(tt.getMessage());
				throw new Exception(tt.getMessage());
			}
		}
	}

	public void MoveMouseCursorToTaskBar() {
		try {
			Actions testact = new Actions(driver);
			testact.moveByOffset(-1500, -1500).build().perform();
		} catch (Throwable tt) {
			// nothing to do
		}
	}

	// return the test data from a test in a 2 dim array
	public Object[][] getExcelData_for_DataProvider(Xls_Reader xls, String testCaseName) {
		Integer rows = 0;
		Integer cols = 0;

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
				// System.out.print(xls.getCellData(testCaseName, colNum, rowNum) + " -- ");
				data[rowNum - 2][colNum] = xls.getCellData(testCaseName, colNum, rowNum);

			}
			// System.out.println();
		}
		return data;

	}

	public Integer fnsLoading_PageLoad() throws Throwable {
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

	// Function of loading image appear on the screen (Block UI).
	public void fnsLoading_Progressingwait(int waitcount) throws Throwable {
		try {
			LoadingImageFlag = false;
			Actual_Loading_Time = 1;
			Loading_Image_Xpath = null;
			PageSourceText = null;
			String Loading_Classes_From_OR = OR_MtrPlan.getProperty("Loading_Progressing").trim();
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
			fnsTake_Screen_Shot_Without_OR("UnExpectedError");
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
				fnsTake_Screen_Shot_Without_OR("PageLoadFail_NS");
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
			fnsTake_Screen_Shot_Without_OR("PageLoadFail");
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(ie));
			throw new Exception(Throwables.getStackTraceAsString(ie));
		} catch (Throwable tt) {
			// isTestCasePass = false;
			fnsTake_Screen_Shot_Without_OR("PageLoadFail");
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(tt));
			throw new Exception(Throwables.getStackTraceAsString(tt));
		}
	}

	// Function of loading image appear on the screen (Block UI).
	// public void fnsLoading_Progressing_wait_iPulse(int waitcount) throws
	// Throwable{
	public void fnsLoading_Progressingwait_OLDTookDoubleTime(int waitcount) throws Throwable {
		try {
			LoadingImageFlag = false;
			boolean LoadingImageComing = false;
			Actual_Loading_Time = 1;
			String Loading_Classes_From_OR = OR_MtrPlan.getProperty("Loading_Progressing").trim();

			for (int wait = 2; wait <= ((Long.parseLong(CONFIG.getProperty("Element_MAX_WaitTime")))); wait++) {
				if (!LoadingImageFlag) {
					for (int ImageSize = 0; ImageSize < waitcount; ImageSize++) {
						for (int i = 0; i < Loading_Classes_From_OR.split("&&").length; i++) {
							Loading_Image_Xpath = Loading_Classes_From_OR.split("&&")[i].trim();
							try {
								System.out.println("Xpath = " + Loading_Image_Xpath + "....Size = "
										+ driver.findElements(By.xpath(Loading_Image_Xpath)).size());
								if (driver.findElements(By.xpath(Loading_Image_Xpath)).size() > 1) {
									// fnsApps_Report_Logs("Xpath 1 = "+Loading_Image_Xpath+"..... Size =
									// "+driver.findElements(By.xpath(Loading_Image_Xpath)).size());
									for (int display = 1; display <= driver.findElements(By.xpath(Loading_Image_Xpath))
											.size(); display++) {
										String Loading_Image_Xpath_Display = "(" + Loading_Image_Xpath + ")[" + display
												+ "]";
										if (driver.findElement(By.xpath(Loading_Image_Xpath_Display)).isDisplayed()) {
											Loading_Image_Xpath = Loading_Image_Xpath_Display;
											System.out.println("....Loading display = " + Loading_Image_Xpath);
											LoadingImageComing = true;
											break;
										}
									}
									if (LoadingImageComing == true) {
										break;
									}
								} else if (driver.findElements(By.xpath(Loading_Image_Xpath)).size() > 0) {
									// fnsApps_Report_Logs("Xpath 0 = "+Loading_Image_Xpath+"..... Size =
									// "+driver.findElements(By.xpath(Loading_Image_Xpath)).size());
									if (driver.findElement(By.xpath(Loading_Image_Xpath)).isDisplayed()) {
										System.out.println("....Loading display = " + Loading_Image_Xpath);
										LoadingImageComing = true;
										break;
									}
								}
							} catch (Throwable t) {
								/* nothing to do */ }
						}
						if (LoadingImageComing == true) {
							break;
						} else {
							Thread.sleep(750);
						}
					}
				}
				System.out.println(LoadingImageComing + "-----Xpath = " + Loading_Image_Xpath);
				if (LoadingImageComing == false) {
					fnsApps_Report_Logs("*********  Pause/Wait is for < " + waitcount + " > seconds.  *****  size 0 ");
					break;
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
						}
					}
				}

				try {
					if (driver.findElement(By.xpath(Loading_Image_Xpath)).isDisplayed()) {
						Thread.sleep(1000);
						LoadingImageFlag = true;
					}
				} catch (/* NoSuchElementException */ Throwable n) {
					Thread.sleep(250);
				}

				try {
					if (!(driver.findElement(By.xpath(Loading_Image_Xpath)).isDisplayed()) && wait > waitcount
							&& LoadingImageFlag == true) {
						Loading_Xpath_Array.add(Loading_Image_Xpath + "&&");
						Thread.sleep(500);
						fnsApps_Report_Logs("********* Progressing Loading image is displayed for < "
								+ (Actual_Loading_Time) + " > seconds. *****  < " + Loading_Image_Xpath + " >");
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
					} /*
						 * else if( wait < waitcount && LoadingImageFlag == false ){
						 * Thread.sleep(750);
						 * }
						 */
				} catch (/* NoSuchElementException */ Throwable n) {
					if (wait > waitcount) {
						if (LoadingImageFlag == true) {
							Loading_Xpath_Array.add(Loading_Image_Xpath + "&&");
							Thread.sleep(500);
							fnsApps_Report_Logs("********* Progressing Loading image is displayed for < "
									+ (Actual_Loading_Time) + " > seconds. *****  < " + Loading_Image_Xpath + " >");
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
					} else if (wait < waitcount && LoadingImageFlag == false) {
						Thread.sleep(750);
					}

				}

				if (wait == (Long.parseLong(CONFIG.getProperty("Element_MAX_WaitTime")))) {
					throw new Exception("Loading Issue : After < "
							+ (Long.parseLong(CONFIG.getProperty("Element_MAX_WaitTime")))
							+ " > Seconds wait page is not getting Load, please refer screenshot >> PageLoadFail"
							+ fnsScreenShot_Date_format() + "*****  < " + Loading_Image_Xpath + " >");
				}
				Actual_Loading_Time++;
			}

			PageSourceText = driver.getPageSource().toLowerCase();
			if (PageSourceText.contains("ui-messages-error-summary")) {
				if (driver.findElement(By.xpath(Loading_Image_Xpath)).isDisplayed()) {
					WebElement Element = driver.findElement(By.xpath(Loading_Image_Xpath));
					Actions act = new Actions(driver);
					act.moveToElement(Element).build().perform();
					ErrorMsgText = driver.findElement(By.xpath(Loading_Image_Xpath)).getText().trim();
					throw new IllegalArgumentException();
				}
			}
			if (PageSourceText.contains("we are sorry")) {
				ErrorMsgText = "We are sorry...an unexpected error has occurred. Details have been forwarded to the Application Support Team for investigation. If the problem persists, please submit an issue on the Apps Portal.";
				throw new IllegalArgumentException();
			}

		} catch (IllegalArgumentException i) {
			isTestCasePass = false;
			fnsTake_Screen_Shot_Without_OR("UnExpectedError");
			fnsApps_Report_Logs("Getting Un-Expected Application Error <" + ErrorMsgText
					+ ">, please refer screenshot >>UnExpectedError" + fnsScreenShot_Date_format() + "*****  < "
					+ Loading_Image_Xpath + " >" + "  Exception (" + Throwables.getStackTraceAsString(i));
			throw new Exception("Getting Un-Expected Application Error <" + ErrorMsgText
					+ ">, please refer screenshot >> UnExpectedError" + fnsScreenShot_Date_format() + "*****  < "
					+ Loading_Image_Xpath + " >" + "  Exception (" + Throwables.getStackTraceAsString(i));
		} catch (NoSuchElementException n) {
			Thread.sleep(waitcount * 1000);
			// fnsApps_Report_Logs("***** Progressing Loading image displayed for <
			// "+Actual_Loading_Time+" > seconds. **************** NS");
			/*
			 * try{ //not working caught in looing
			 * fnsApps_Report_Logs("***** Progressing Loading image displayed for < "
			 * +Actual_Loading_Time+" > seconds. **************** NS");
			 * fnsLoading_Progressingwait(waitcount);
			 * }catch(Throwable tt){
			 * isTestCasePass = false;
			 * fnsTake_Screen_Shot_Without_OR("PageLoadFail");
			 * fnsApps_Report_Logs(Throwables.getStackTraceAsString(tt));
			 * throw new Exception(Throwables.getStackTraceAsString(tt));
			 * }
			 */
		} catch (StaleElementReferenceException n) {
			fnsApps_Report_Logs("***** Progressing Loading image displayed for < " + Actual_Loading_Time
					+ " > seconds. **************** Stale");
		} catch (Throwable tt) {
			isTestCasePass = false;
			fnsTake_Screen_Shot_Without_OR("PageLoadFail");
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(tt));
			throw new Exception(Throwables.getStackTraceAsString(tt));
		}
	}

	// Function to click on lookup radio button ---- Satya Pal
	public void fnsLookup_RadioBttn_Select() throws Exception {
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
				driver.findElement(By.xpath(OR_MtrPlan.getProperty("lookup_select_radio_bttn"))).click();
				fnsLoading_Progressingwait(3);
				Thread.sleep(500);
				fnsApps_Report_Logs("PASSED == Successfully Select LookUp Radio Button");
				TableSizeFlag = false;
			}

			if (TableSizeFlag) {
				fnsLoading_Progressingwait(1);
				driver.findElement(By.xpath(OR_MtrPlan.getProperty("lookup_search_bttn"))).click();
				fnsLoading_Progressingwait(3);
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

				WithOut_OR_fnGet_ObjectX(OR_MtrPlan.getProperty("lookup_select_radio_bttn")).click();
				fnsLoading_Progressingwait(3);
				Thread.sleep(500);
			}
			fnsApps_Report_Logs("PASSED == Successfully Select " + lookup_title + "  Radio Button");
		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot_Without_OR("Lookup_Fail");
			fnsApps_Report_Logs("FAILED == " + lookup_title + " : " + Throwables.getStackTraceAsString(t).trim()
					+ ", Please refer the screenshot >> Lookup_Fail" + fnsScreenShot_Date_format());
			throw new Exception("FAILED == " + lookup_title + " : " + Throwables.getStackTraceAsString(t).trim()
					+ ", Please refer the screenshot >> Lookup_Fail" + fnsScreenShot_Date_format());
		}
	}

	// Check class(Y/N) and Launch browser and Login
	public void fnsCheckClassLevelTestSkip(String className) throws Exception {
		TC_Step = 1;
		try {
			classNameText = className;
			currentScriptCode = fnpMappingClassNameWithScenarioCode(classNameText).trim();

			if (!TestUtil.isTestCaseRunnable(MonitorPlan_suitexls, className)) {
				fnsApps_Report_Logs("Skipping Test Case " + className + " as runmode set to NO");
				throw new SkipException("Skipping Test Case " + className + " as runmode set to NO");
			}
			isTestCasePass = true;
			runmodes = TestUtil.getDataSetRunmodes(MonitorPlan_suitexls, className);

		} catch (SkipException sk) {
			String stackTrace = Throwables.getStackTraceAsString(sk);
			String errorMsg = sk.getMessage();
			Exception c = new Exception(errorMsg);
			ErrorUtil.addVerificationFailure(c);
			throw new SkipException(errorMsg + " \n\nSo,Skipping Test Case" + className);
		} catch (Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot(className);
			String stackTrace = Throwables.getStackTraceAsString(t);
			String errorMsg = t.getMessage();
			errorMsg = errorMsg + "\n\n\n\nSee screenshot '" + className + "'\n\n" + stackTrace;
			Exception c = new Exception(errorMsg);
			ErrorUtil.addVerificationFailure(c);
			throw new Exception(errorMsg);
		}
	}

	// ###################################################################
	// Configuration Function
	// ############################################################################
	// Function to Launch browser and login
	public void fnsIpulse_Login_SSO(String PortalNamne, String LoginAs, String userName, String password)
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
			WithOut_OR_fnType(OR_MtrPlan.getProperty("MicrosoftOnline_Signin_Input"), userName + "@nsf.org");

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
					fnsTake_Screen_Shot_Without_OR("SSO_Not_Working");
					fnsApps_Report_Logs(
							"FAILED == After Clicking on NEXT button from 'login.microsoftonline.com' screen, the application is not navigated to next screen (Wait Time = 120 seconds), please refer the screenshot >> SSO_Not_Working "
									+ fnsScreenShot_Date_format());
					throw new Exception(
							"FAILED == After Clicking on NEXT button from 'login.microsoftonline.com' screen, the application is not navigated to next screen (Wait Time = 120 seconds), please refer the screenshot >> SSO_Not_Working "
									+ fnsScreenShot_Date_format());
				}
			}
			fnsLoading_PageLoad();
			Screen_URL = driver.getCurrentUrl().toLowerCase().trim();
			if (Screen_URL.contains("auth.nsf.org")) {
				Runtime.getRuntime().exec(Autoit_scriptExeFile_Path);
				fnsApps_Report_Logs(
						"PASSED == ********* Successfulluy Executed the Autoit script for SSO login having exe file path [ "
								+ Autoit_scriptExeFile_Path + " ]");

				for (int i = 1; i <= 120; i++) {
					String Screen_Title = driver.getTitle().trim();
					System.out.println(Screen_Title);
					if (Screen_Title.equalsIgnoreCase("i-pulse")) {
						break;
					} else {
						Thread.sleep(1000);
					}
					if (i == 120) {
						fnsTake_Screen_Shot_Without_OR("SSO_Not_Working");
						fnsApps_Report_Logs(
								"FAILED == After Clicking on login button from 'auth.nsf.org' screen, the application is not navigated to i-Pulse screen (Wait Time = 120 seconds), please refer the screenshot >> SSO_Not_Working "
										+ fnsScreenShot_Date_format());
						throw new Exception(
								"FAILED == After Clicking on login button from 'auth.nsf.org' screen, the application is not navigated to i-Pulse screen (Wait Time = 120 seconds), please refer the screenshot >> SSO_Not_Working "
										+ fnsScreenShot_Date_format());
					}
				}
			} else {
				for (int i = 1; i <= 120; i++) {
					String Screen_Title = driver.getTitle().trim();
					System.out.println(Screen_Title);
					if (Screen_Title.equalsIgnoreCase("i-pulse")) {
						break;
					} else {
						Thread.sleep(1000);
					}
					if (i == 120) {
						fnsTake_Screen_Shot_Without_OR("SSO_Not_Working");
						fnsApps_Report_Logs(
								"FAILED == After Clicking on NEXT button from 'login.microsoftonline.com' screen, the application is not navigated to i-Pulse screen (Wait Time = 120 seconds), please refer the screenshot >> SSO_Not_Working "
										+ fnsScreenShot_Date_format());
						throw new Exception(
								"FAILED == After Clicking on NEXT button from 'login.microsoftonline.com' screen, the application is not navigated to i-Pulse screen (Wait Time = 120 seconds), please refer the screenshot >> SSO_Not_Working "
										+ fnsScreenShot_Date_format());
					}
				}
			}

			fnsLoading_PageLoad();
			fnsLoading_Progressingwait(1);
			fnsComman_BrowserLogin_ElementWait(OR_MtrPlan.getProperty("Acknowledge_bttn"),
					CONFIG.getProperty("Element_MAX_WaitTime"));
			fnsGet_Element_Enabled("Acknowledge_bttn");
			Thread.sleep(2000);
			fnsWait_and_Click("Acknowledge_bttn");
			for (int wait = 0; wait <= ((Long.parseLong(CONFIG.getProperty("Element_MAX_WaitTime")))); wait++) {
				try {
					if (driver.findElements(By.xpath(OR_MtrPlan.getProperty("Acknowledge_bttn"))).size() == 0) {
						fnsLoading_PageLoad();
						fnsLoading_Progressingwait(1);
						break;
					} else {
						Thread.sleep(1000);
					}
				} catch (Throwable t) {
					throw new Exception("Acknowledge alert popup is not getting disappeared after "
							+ CONFIG.getProperty("Element_MAX_WaitTime") + " secs wait.");
				}
			}

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
				WithOut_OR_fnType(OR_MtrPlan.getProperty("ipulse_SwitchUser_inpit"), LoginAs);
				fnsLoading_Progressingwait(1);

				String Ajax_UserName_Xpath = "//li[@data-item-label='" + LoginAs.toUpperCase().trim() + "']";
				WithOut_OR_fnGet_Element_Enabled(Ajax_UserName_Xpath);
				fnsLoading_Progressingwait(1);
				WithOut_OR_fnClick(Ajax_UserName_Xpath);
				fnsLoading_Progressingwait(1);

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

	public String fns_GetCurrentSystemIPAddress() throws Throwable {
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

}