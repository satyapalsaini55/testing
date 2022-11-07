package nsf.ecap.base;

//tst1

//ttt

import java.io.File;    
import java.io.FileInputStream;       
import java.io.FileNotFoundException;   
import java.io.FileOutputStream;   
import java.io.IOException;   
import java.util.Iterator;    

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFPalette;    
import org.apache.poi.hssf.usermodel.HSSFSheet;    
import org.apache.poi.hssf.usermodel.HSSFWorkbook;    
import org.apache.poi.hssf.util.HSSFColor;    
import org.apache.poi.ss.usermodel.Cell;    
import org.apache.poi.ss.usermodel.CellStyle;    
import org.apache.poi.ss.usermodel.Row; 







import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
//import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import nsf.ecap.Alerts_iPulse_Suite.TestSuiteBase_Alerts;
import nsf.ecap.Grip_Suite.TestSuiteBase_Grip;
import nsf.ecap.MonitorPlan_Suite.TestSuiteBase_MonitorPlan;
import nsf.ecap.NSFOnline_Suite.TestSuiteBase_NSFOnline;
import nsf.ecap.New_NSFOnline_Suite.TestSuiteBase_New_NSFOnline;
import nsf.ecap.Listings_Suite.TestSuiteBase_Listings;
import nsf.ecap.TraQtion_Suite.TestSuiteBase_TraQtion;
import nsf.ecap.Work_Order_suite.TestSuiteBase;
import nsf.ecap.config.IPULSE_CONSTANTS;
import nsf.ecap.util.Xls_Reader;

import org.apache.commons.io.FileDeleteStrategy;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.os.WindowsUtils;
import org.openqa.selenium.remote.DesiredCapabilities;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.FluentWait;
//import org.openqa.selenium.support.ui.Wait;
//import org.openqa.selenium.support.ui.WebDriverWait;


import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;

import com.google.common.base.Throwables;

import net.sf.saxon.expr.IfExpression;
import net.sf.saxon.instruct.ForEach;
//import net.sourceforge.htmlunit.corejs.javascript.ast.WhileLoop;
import nsf.ecap.util.ErrorUtil;
import nsf.ecap.util.TestUtil;
import nsf.ecap.util.Xls_Reader;

public class TestBase {
	
	public static String dbPassword=null;
	
	
	//Element_MAX_WaitTime=300   ---- Need to updated in Config.properties file.
	public static Integer NewNsfOnline_Element_Max_Wait_Time = null;
	public static Integer Listings_Element_Max_Wait_Time = null;
	public static boolean iPulse_Invalid_Password_Verification = false;
	//public static boolean iPulse_Invalid_Password_Verification = true;
	
	public static TestSuiteBase_NSFOnline TestSuiteBase_NSFOnline = new TestSuiteBase_NSFOnline();
	public static TestSuiteBase_MonitorPlan TestSuiteBase_MonitorPlan = new TestSuiteBase_MonitorPlan();
	public static TestSuiteBase_TraQtion TestSuiteBase_TraQtion = new TestSuiteBase_TraQtion();
	public static TestSuiteBase_New_NSFOnline TestSuiteBase_New_NSFOnline = new TestSuiteBase_New_NSFOnline();
	public static TestSuiteBase_Grip TestSuiteBase_Grip = new TestSuiteBase_Grip();
	public static TestSuiteBase_Alerts TestSuiteBase_Alerts = new TestSuiteBase_Alerts();
	public static TestSuiteBase_Listings TestSuiteBase_Listings = new TestSuiteBase_Listings();
	public static int SShots = 0;
	
	public static String env;
	public static String logHistoryFoldername;
	
	public static boolean isTestCasePass = true;
	public static boolean IsExecutionAlreadyStarted = false;
	public static String startExecutionTime = null;	
	public static String batchStartExecutionTime = null;
	public static String todayFolder = null;
	public static String dateWithTimeInExcel=null;
	public static String overRideScreenshotspath = null;
	public static String screenshots_path = null;
	public static String currentScriptCode = "";
	public static String currentSuiteCode = "";
	public static String currentSuiteName = "";
	public static String referenceSuite="";
	
	public static Date start;

	public static boolean veryFirstTimeAfterLogin = false;

	public static Logger APP_LOGS = null;
	public static Properties CONFIG = null;

	public static Xls_Reader suiteXls = null;

	public static Xls_Reader currentSuiteXls = null;

	public static Xls_Reader Quartz_Jobs_suitexls = null;
	public static Xls_Reader Work_Order_suitexls = null;
	public static Xls_Reader FPC_Work_Order_suitexls = null;
	public static Xls_Reader Wales_Work_Order_suitexls = null;
	public static Xls_Reader Non_Food_Compounds_suitexls = null;
	public static Xls_Reader Dietary_Supplement_suitexls = null;
	
	public static Xls_Reader Test_Plan_suitexls = null;
	
	public static Xls_Reader ISR_suitexls = null;
	public static Xls_Reader Sustainability_suitexls = null;
	public static Xls_Reader Navigation_suitexls = null;
	public static Xls_Reader SCFS_suitexls = null;
	public static Xls_Reader Proposals_suitexls = null;
	public static Xls_Reader Health_Science_suitexls = null;
	public static Xls_Reader Agriculture_suitexls = null;
	public static Xls_Reader Audit_SAM_SPA_suitexls = null;
	public static Xls_Reader Generic_suitexls = null;
	public static Xls_Reader Invoice_Function_suitexls = null;
	
	
	
	public static Xls_Reader EPSF_suitexls = null;
	public static Xls_Reader Direct_NSFOnline_suitexls = null;
	public static Xls_Reader Audit_Functional_suitexls = null;
	
	public static Xls_Reader IssueMgt_Suitexls = null;
	public static Xls_Reader MonitorPlan_suitexls = null;
	public static Xls_Reader Client_Suitexls = null;
	public static Xls_Reader NSFOnline_suitexls = null;
	public static Xls_Reader TraQtion_suitexls = null;
	public static Xls_Reader Grip_Suitexls = null;
	public static Xls_Reader Automotive_Suitexls = null;
	public static Xls_Reader SearchAudits_Suitexls = null;
	public static Xls_Reader New_NSFOnline_Suitexls = null;
	public static Xls_Reader Alerts_iPulse_suitexls = null;
	public static Xls_Reader Listings_Suitexls = null;
	
	public static Properties OR_CLNT = null;
	public static Properties OR_IM = null;
	public static Properties OR_MtrPlan = null;
	public static Properties OR = null;
	public static Properties OR_SUS = null;
	public static Properties OR_Navi = null;
	
	public static Properties OR_NM = null;
	public static Properties OR_HS = null;

	public static Properties OR_Grip = null;
	public static Properties OR_NsfOnline = null;
	public static Properties OR_TraQtion = null;
	public static Properties OR_Automotive = null;
	public static Properties OR_SearchAudits = null;
	public static Properties OR_New_NSFOnline = null;
	public static Properties OR_Listings = null;

	public static Xls_Reader suiteCxls = null;
	public static boolean isInitalized = false;

	public static WebDriver driver = null;
	public static boolean IsBrowserPresentAlready = false;

	public static String browserName = null;
	public static String excelSiteURL = null;
	public static String classNameText; // using this only for Custom wo
	//public  String classNameText; // using this only for Custom wo

	public static String emailIDToSendResultFolder = "";

	public static HashMap<String, String> hashXlData = null;

	
	
	 public static void cleanDirectory(File dir) throws Throwable {
		 File[] files = dir.listFiles();
		 if (files!=null) {
		     for (File file: files) {
			      try{
			       //////System.out.println("File name is--"+file.getName());
			       // to not delete snagIT recording video
			         if(file.getName().contains("snag")) {
			             //do nothing
			         } else {
			             //delete file
			             file.delete();
			         }
			      }catch(Throwable t){
			       //nothing to do
			      }
			         
			     }
		}

	     

	//  File fin = new File(System.getenv("TEMP"));
	  File fin = dir;
	  files=fin.listFiles();
	  if (files!=null) {
		  for (File file : files) {
			   
		      try{
		       ////System.out.println("File name is--"+file.getName());
		       // to not delete snagIT recording video
		         if(file.getName().contains("snag")) {
		             //do nothing
		         } else {
		             //delete file
		          FileDeleteStrategy.FORCE.delete(file);
		         }
		      }catch(Throwable t){
		       //nothing to do
		      }
		   
		   
		  } 
	}
 
	  //Thread.sleep(50);
	     }
	
	
	
	
	
	

	public static Connection fnpGetDBConnection() throws Throwable{
		
		Connection localconnection = null;
		
		String userName = CONFIG.getProperty("adminUsername");
		String password = CONFIG.getProperty("adminPassword");//dbPassword;//

/*		  if (env.toLowerCase().equalsIgnoreCase("test")){
			  localconnection = DriverManager.getConnection("jdbc:oracle:thin:@dbserv13vm.nsf.org:1521:certtest", userName, password);
		  }else if(env.toLowerCase().equalsIgnoreCase("Dev")){
			  localconnection = DriverManager.getConnection("jdbc:oracle:thin:@dbserv1.nsf.org:1521:certdev", userName, password);
		  }else if(env.toLowerCase().equalsIgnoreCase("staging")){
			  localconnection = DriverManager.getConnection("jdbc:oracle:thin:@dbserv30.nsf.org:1521:CERTSTAG", userName, password);
		  }else{
			  throw new Exception ("FAILED == Environment variable is not defined.");
		  }
		 */  
		  
		  
			switch (env) {
			case "Dev":
				localconnection = DriverManager.getConnection("jdbc:oracle:thin:@dbserv1.nsf.org:1521:certdev", userName, password);
				break;
			

			case "Test":
				 localconnection = DriverManager.getConnection("jdbc:oracle:thin:@usaad-ordb21.nsf.org:1521:certtest", userName, password);
				break;
			
			case "Staging":
				localconnection = DriverManager.getConnection("jdbc:oracle:thin:@usaad-ordb22.nsf.org:1521:CERTSTAG", userName, password);
				break;
				
			default:
				 throw new Exception ("FAILED == Environment variable is not defined.");

			}
		  
		  
		  
		  return localconnection;
	}
	
	
	

	public static Connection fnpGetDBConnection(String userName, String password) throws Throwable{
		
		Connection localconnection = null;

/*		  if (env.toLowerCase().equalsIgnoreCase("test")){
			  localconnection = DriverManager.getConnection("jdbc:oracle:thin:@dbserv13vm.nsf.org:1521:certtest", username, password);
		  }else if(env.toLowerCase().equalsIgnoreCase("Dev")){
			  localconnection = DriverManager.getConnection("jdbc:oracle:thin:@dbserv1.nsf.org:1521:certdev", username, password);
		  }else if(env.toLowerCase().equalsIgnoreCase("staging")){
			  localconnection = DriverManager.getConnection("jdbc:oracle:thin:@dbserv30.nsf.org:1521:CERTSTAG", username, password);
		  }else{
			  throw new Exception ("FAILED == Environment variable is not defined.");
		  }
		   
		  */
		
		
		switch (env) {
		case "Dev":
			localconnection = DriverManager.getConnection("jdbc:oracle:thin:@dbserv1.nsf.org:1521:certdev", userName, password);
			break;
		

		case "Test":
			 localconnection = DriverManager.getConnection("jdbc:oracle:thin:@usaad-ordb21.nsf.org:1521:certtest", userName, password);
			break;
		
		case "Staging":
			localconnection = DriverManager.getConnection("jdbc:oracle:thin:@usaad-ordb22.nsf.org:1521:CERTSTAG", userName, password);
			break;
			
		default:
			 throw new Exception ("FAILED == Environment variable is not defined.");

		}
		
		  return localconnection;
	}
	
	
	
	
	
	
	public static void fnpDeletingResultZipFolder() throws Throwable {

		FileUtils.deleteDirectory(new File(System.getProperty("user.dir") + "//XSLT_Reports//zip-result"));
		

	}

	public static void fnpCopyExecutionDetailsFileForBackup() throws Throwable {


		
		String pathforExecution=System.getProperty("user.dir")+"\\log\\ExecutionDetails.xlsx";

		File file = new File(pathforExecution);


		
		if (file.exists()) {

			FileUtils.copyFile(new File(System.getProperty("user.dir") + "//log//ExecutionDetails.xlsx"), 
					new File(System.getProperty("user.dir") + "//docs//ExecutionDetails_backup//"+todayFolder+"//"+(batchStartExecutionTime.replaceAll(":", "-"))+"//ExecutionDetails.xlsx"));
			


		}
		

		

	}

	
	public static void fnpFirstTimeInitializationMethod() throws Throwable {
		if (!IsExecutionAlreadyStarted) {
			killprocess();

			IsExecutionAlreadyStarted = true;

			fnpDeletingResultZipFolder();
			
/*			try{
			FileDeleteStrategy.FORCE.delete(new File(System.getProperty("user.dir") + "\\log\\Driverlogs.txt"));
			}catch(Throwable t){
				//
			}
			*/
			
			
			startExecutionTime = fnpTimeOnly();
			
	
			batchStartExecutionTime=fnpTimestampDateOnly()+"  "+startExecutionTime;

			Reporter.log(fnpTimestamp() + "  " + "@@  ----batchStartExecutionTime 's value is --"+batchStartExecutionTime);

            InetAddress ipAddr = InetAddress.getLocalHost();
            int ipAddrLen=ipAddr.toString().length();
            String last2CharactersOfIpAddress=ipAddr.toString().substring((ipAddrLen-2), ipAddrLen);
     
            Reporter.log("Current running IP address of machine is--" + ipAddr.toString());
			
            APP_LOGS.debug("BatchStartExecutionTime 's value is --"+batchStartExecutionTime);
   
            APP_LOGS.debug("Current running IP address of machine is--" + ipAddr.toString());
			
			todayFolder = fnpTimestampDateOnly();
			
/*			if (env.equalsIgnoreCase("Test")){
				logHistoryFoldername="TEST_full_scripts_log_history";
				//overRideScreenshotspath = "\\TEST_full_scripts_log_history\\" + todayFolder + "\\" + startExecutionTime + "\\screenshots";
			} else {
				if (env.equalsIgnoreCase("Staging")){
					logHistoryFoldername="STAGING_full_scripts_log_history";
				 
				} else{
					//code for saying no environment defined
				}
			}
			*/
			
			switch (env) {
			case "Dev":
				logHistoryFoldername="TEST_full_scripts_log_history";
				break;
			

			case "Test":
				logHistoryFoldername="TEST_full_scripts_log_history";
				break;
			
			case "Staging":
				logHistoryFoldername="STAGING_full_scripts_log_history";
				break;
				
			default:
				

			}
			
			
			
			
			
			
			
			overRideScreenshotspath = "\\"+logHistoryFoldername+"\\" + todayFolder + "\\" + startExecutionTime + "\\screenshots";
			
			
			String overRideScreenshotspath1 = overRideScreenshotspath.substring(0, 2);
			String overRideScreenshotspath2 = overRideScreenshotspath.substring(2, overRideScreenshotspath.length());
			overRideScreenshotspath2 = overRideScreenshotspath2.replace(":", "_");

			overRideScreenshotspath = overRideScreenshotspath1.trim() + overRideScreenshotspath2.trim();
			overRideScreenshotspath = overRideScreenshotspath.replace("\\\\", "\\");

			screenshots_path = overRideScreenshotspath;

			String praTime = startExecutionTime.replace(":", "_");
			setEmailIdToSendResult(suiteXls);
			fnpCreateXMLFile(praTime.trim(), emailIDToSendResultFolder);
			Reporter.log("Start Execution time passed in fnpCreateXMLFile is--" + praTime.trim());
			

			dateWithTimeInExcel = " " + todayFolder + "---- " + praTime.trim().replace("_", ":");
			
			fnpCopyExecutionDetailsFileForBackup(); //copy ExecutionDetailFile for backup and save it in doc folder because sometime files get corrupted.

		}

	}

	// To get current date and time
	public static String fnpTimestamp() {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
		Date date = new Date();
		return (dateFormat.format(date));
	}

	public static String fnpTimeOnly() {
		DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss a");
		Date date = new Date();
		return (dateFormat.format(date));
	}

	// To get current date and time
	public static String fnpTimestampOnlyHour() {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh a");
		Date date = new Date();
		return (dateFormat.format(date));
	}

	public static String fnpTimestampDateOnly() {

		int style = DateFormat.SHORT;
		DateFormat dateFormat;
		dateFormat = DateFormat.getDateInstance(style, Locale.getDefault());
		String locale = Locale.getDefault().toString();

		if (locale.equalsIgnoreCase("en_IN")) {
			dateFormat = new SimpleDateFormat("dd-MM-yyyy");

		}

		if (locale.equalsIgnoreCase("en_US")) {
			dateFormat = new SimpleDateFormat("MM-dd-yyyy");

		}

		Date date = new Date();
		String dateReplaceslash = dateFormat.format(date).replace("/", "-");
		return dateReplaceslash;

	}

	
	
	  public static String formatTime(Date time, String format) {
		   SimpleDateFormat form = new SimpleDateFormat(format);  
		   return form.format(time);
		  }  
	
	  public static Date convertStringIntoDate(String dateWithTime) throws Throwable {
		  
		  int style = DateFormat.SHORT;
		  String locale = Locale.getDefault().toString();
			DateFormat formatter;
			formatter = DateFormat.getDateInstance(style, Locale.getDefault());
			
			if (locale.equalsIgnoreCase("en_IN")) {
				formatter = new SimpleDateFormat("dd-MM-yyyy");

			}

			if (locale.equalsIgnoreCase("en_US")) {
				formatter = new SimpleDateFormat("MM-dd-yyyy");

			}
		  
			Date date = formatter.parse(dateWithTime);
		   return date;
		  }  
	
	
	  public static Date convertStringIntoDateWithTime(String dateWithTime) throws Throwable {
		  
		  int style = DateFormat.SHORT;
		  String locale = Locale.getDefault().toString();
			DateFormat formatter;
			formatter = DateFormat.getDateInstance(style, Locale.getDefault());
			
			if (locale.equalsIgnoreCase("en_IN")) {
				formatter = new SimpleDateFormat("dd-MM-yyyy  hh:mm:ss a");

			}

			if (locale.equalsIgnoreCase("en_US")) {
				formatter = new SimpleDateFormat("MM-dd-yyyy  hh:mm:ss a");

			}
		  
			Date date = formatter.parse(dateWithTime);
		   return date;
		  }  
	  
	  
	  
	  
	  
	  
	  
	  
	public static String fnTimestampDateWithTime() {

		int style = DateFormat.SHORT;
		DateFormat dateFormat;
		dateFormat = DateFormat.getDateInstance(style, Locale.getDefault());
		String locale = Locale.getDefault().toString();

		if (locale.equalsIgnoreCase("en_IN")) {
			dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");

		}

		if (locale.equalsIgnoreCase("en_US")) {
			dateFormat = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss a");

		}

		Date date = new Date();
		String dateReplaceslash = dateFormat.format(date).replace("/", "-");
		return dateReplaceslash;

	}
	
	  
	public static String fnTimestampDateWithTime_2DigitYear() {

		int style = DateFormat.SHORT;
		DateFormat dateFormat;
		dateFormat = DateFormat.getDateInstance(style, Locale.getDefault());
		String locale = Locale.getDefault().toString();

		if (locale.equalsIgnoreCase("en_IN")) {
			dateFormat = new SimpleDateFormat("dd-MM-yy hh:mm:ss a");

		}

		if (locale.equalsIgnoreCase("en_US")) {
			dateFormat = new SimpleDateFormat("MM-dd-yy hh:mm:ss a");

		}
		
		if (locale.equalsIgnoreCase("en_GB")) {
			dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");

		}

		Date date = new Date();
		String dateReplaceslash = dateFormat.format(date).replace("/", "-");
		return dateReplaceslash;

	}
	
	
	 public static String getCurrentTime(String timeFormat, TimeZone timeZone)
	 {
	    /* Specifying the format */ 
	    DateFormat dateFormat = new SimpleDateFormat(timeFormat);
	    /* Setting the Timezone */
	    Calendar cal = Calendar.getInstance(timeZone);
	    dateFormat.setTimeZone(cal.getTimeZone());
	    /* Picking the time value in the required Format */
	    String currentTime = dateFormat.format(cal.getTime());
	    return currentTime;
	 }
	
	public static String getCurrent_US_Time(){
		
		TimeZone timeZone = TimeZone.getTimeZone("US/Eastern");
	    String timeFormat = "dd-MMM-yyyy hh:mm a";		
		return getCurrentTime(timeFormat,timeZone);
		
	}
	
	
	public static String formatDateToString(Date date, String format,
			String timeZone) {
		// null check
		if (date == null) return null;
		// create SimpleDateFormat object with input format
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		// default system timezone if passed null or empty
		if (timeZone == null || "".equalsIgnoreCase(timeZone.trim())) {
			timeZone = Calendar.getInstance().getTimeZone().getID();
		}
		// set timezone to SimpleDateFormat
		sdf.setTimeZone(TimeZone.getTimeZone(timeZone));
		// return Date in required format with timezone as String
		return sdf.format(date);
	}

	
	

	public static void fnpCreateXMLFile(String praTime, String emailID) throws Throwable {
		
		String userName = CONFIG.getProperty("adminUsername");
		String password = CONFIG.getProperty("adminPassword");
		
		String pattern = "";
		if (Locale.getDefault().toString().equalsIgnoreCase("en_IN")) {
			pattern = "dd-MM-yyyy";

		}

		if (Locale.getDefault().toString().equalsIgnoreCase("en_US")) {
			pattern = "MM-dd-yyyy";

		}

		String text = "Hello world";
		String resultEmailSubjectFirstPart="";
		
/*		if (env.equalsIgnoreCase("Test")){
			resultEmailSubjectFirstPart = "TEST_full_scripts";
		} else {
			if (env.equalsIgnoreCase("Staging")){
				resultEmailSubjectFirstPart = "STAGING_full_scripts";
			} else{
				if (env.equalsIgnoreCase("dev")){
					resultEmailSubjectFirstPart = "Dev_full_scripts";
				}else{
					//code for saying no environment defined
				}
				
			}
		}
		*/
		
		switch (env) {
		case "Dev":
			resultEmailSubjectFirstPart = "Dev_full_scripts";
			break;
		

		case "Test":
			resultEmailSubjectFirstPart = "TEST_full_scripts";
			break;
		
		case "Staging":
			resultEmailSubjectFirstPart = "STAGING_full_scripts";
			break;
			
		default:
			throw new Exception("Environment not defined.");

		}
		
		
		
		try {
			File file = new File(System.getProperty("user.dir") + "\\StartExecution.xml");
			BufferedWriter output = new BufferedWriter(new FileWriter(file));

			output.write("<?xml version='1.0' encoding='UTF-8'?>");
			output.newLine();
			output.write("    <Information>");
			output.newLine();
			output.write("            <SystemUserName>" + System.getProperty("user.name") + "</SystemUserName>");
			output.newLine();
			
			
			
            InetAddress ipAddr = InetAddress.getLocalHost();
           // String ipAddress=ipAddr.toString().split("/")[1];
            
            String ipAddress="";
            String[] ipAddressStringArray=ipAddr.toString().split("/");
            
            if (ipAddressStringArray.length>1) {
            	ipAddress=ipAddressStringArray[1];
			}else {
				ipAddress=ipAddressStringArray[0];
			}
			
		
			output.write("            <SystemIpAddress>" + ipAddress+ "</SystemIpAddress>");
			output.newLine();
			
		
			
			
			output.write("            <resultEmailSubjectFirstPartTag>" + resultEmailSubjectFirstPart + "</resultEmailSubjectFirstPartTag>");
			output.newLine();
			
			output.write("            <TodayDate>" + todayFolder + "</TodayDate>");
			output.newLine();
			output.write("            <StartTime>" + praTime + "</StartTime>");
			output.newLine();
			output.write("			<locale>" + Locale.getDefault() + "</locale>");
			output.newLine();
			output.write("			<email>" + emailID + "</email>");
			output.newLine();
			
			
			output.write("			<fromEmail>" + userName+"@nsf.org" + "</fromEmail>");
			output.newLine();
			
			output.write("			<fromEmailPassword>" + password + "</fromEmailPassword>");
			output.newLine();
			
			
			
			output.write("			<pattern>" + pattern + "</pattern>");
			output.newLine();

			output.write("			<logHistoryFolderNameTag>" + logHistoryFoldername + "</logHistoryFolderNameTag>");
			output.newLine();
			
			
			
			
			
			output.write("    </Information>");

			output.newLine();
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



	public static void fnpFetchApplicationVersion() throws Throwable {

		//Thread.sleep(2000);
		fnWaitForVisible_NotInOR(".//*[@id='headerForm:logImg']");
		WebElement wb = driver.findElement(By.xpath(".//*[@id='headerForm:logImg']"));
		String xpath12=".//*[@id='headerForm:logImg']";

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Long.parseLong(CONFIG.getProperty("genMax_waitTime")), TimeUnit.SECONDS).pollingEvery(2, TimeUnit.SECONDS)
		.ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class);

		wait.until(ExpectedConditions.elementToBeClickable(wb));

		//wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath12);
		
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath12)));
		
		Actions action = new Actions(driver);
		action.moveToElement(wb).build().perform();
		action.moveToElement(wb).click().keyDown(Keys.CONTROL).build().perform();

		String xpathBasicVersionDailogBox = ".//*[@id='basicDialog']";
		fnWaitForVisible_NotInOR(xpathBasicVersionDailogBox);

		String updatedImageName = "ApplicationVersion.png";

		FileUtils.forceMkdir(new File((System.getProperty("user.dir") + screenshots_path + "//applicationVersion//")));
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle screenRectangle = new Rectangle(screenSize);
		Robot robot = new Robot();
		BufferedImage image = robot.createScreenCapture(screenRectangle);
		
		ImageIO.write(image, "png", new File((System.getProperty("user.dir") + screenshots_path + "//applicationVersion//" + "//" + updatedImageName + "_" + ".PNG")));

		Thread.sleep(1000);
		driver.findElement(By.xpath(".//*[@id='basicDialog']/div/a/span[@class='ui-icon ui-icon-closethick']")).click();
		 Thread.sleep(500);
		 
		if ((TestSuiteBase.fnpGetCurrRunningBrowserName().equalsIgnoreCase("IE"))) {	
			action.moveToElement(wb).click().keyDown(Keys.CONTROL).build().perform();
			}

	}

	public static void fnpFetchApplicationVersion(String imageName) throws Throwable {

		//Thread.sleep(2000);
		fnWaitForVisible_NotInOR(".//*[@id='headerForm:logImg']");
		WebElement wb = driver.findElement(By.xpath(".//*[@id='headerForm:logImg']"));

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Long.parseLong(CONFIG.getProperty("genMax_waitTime")), TimeUnit.SECONDS).pollingEvery(2, TimeUnit.SECONDS)
		.ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class);

		wait.until(ExpectedConditions.elementToBeClickable(wb));

		Actions action = new Actions(driver);
		action.moveToElement(wb).build().perform();
		action.moveToElement(wb).click().keyDown(Keys.CONTROL).build().perform();

		String xpathBasicVersionDailogBox = ".//*[@id='basicDialog']";
		fnWaitForVisible_NotInOR(xpathBasicVersionDailogBox);

		/************Taking screenshots of more size   *************************/
		
		FileUtils.forceMkdir(new File((System.getProperty("user.dir") + screenshots_path + "//applicationVersion//")));
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle screenRectangle = new Rectangle(screenSize);
		Robot robot = new Robot();
		BufferedImage image = robot.createScreenCapture(screenRectangle);
		
		ImageIO.write(image, "png", new File((System.getProperty("user.dir") + screenshots_path + "//applicationVersion//" + "//" + (imageName+(SShots++)) + "_" + ".PNG")));
		/**********************************************************************/
		

		
		
		File a=new File(".\\..\\screenshots"+ "//applicationVersion//" + "//" + imageName + "_" + ".PNG");
		Reporter.log("<a href=\"" + a  + "\">  Click here  to see application version screenshot (if going through email attachment or log history folder)</a>"  );
		

		File b=new File(".\\..\\..\\"+   screenshots_path + "//applicationVersion//" + "//" + imageName + "_" + ".PNG");

		Reporter.log("<a href=\"" + b  + "\">  Click here  to see application version screenshot (if going through local XSLT folder) </a>"  );
		
		/************Taking screenshots using 2nd way  *************************/

		//String updatedImageName =imageName+(SShots++)+ ".png";
		File scrnsht = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);			
		FileUtils.copyFile(scrnsht, new File(System.getProperty("user.dir") + screenshots_path + "//applicationVersion//" + "//" + (imageName+(SShots++)) + "_" + ".PNG"));
		/*************************************************************************/
		
		
		Thread.sleep(1000);
		driver.findElement(By.xpath(".//*[@id='basicDialog']/div/a/span[@class='ui-icon ui-icon-closethick']")).click();
		 Thread.sleep(500);
		
/*		if ((TestSuiteBase.fnpGetCurrRunningBrowserName().equalsIgnoreCase("IE"))) {	
		//action.moveToElement(wb).click().keyDown(Keys.CONTROL).build().perform();
		}
		*/
		//action.moveToElement(wb).click().keyDown(Keys.CONTROL).build().perform();
		//action.click().keyUp(Keys.CONTROL).build().perform();// need to this for latest chrome browser 78
		
		
		//For DriverManager
		//action.click().keyUp(Keys.CONTROL).build().perform();


	}

	public static String fnpMappingClassNameWithScenarioCode(String className) {

		String scenarioCode = null;

		
		// ##########################Quartz_Jobs ######################################
		if (className.equalsIgnoreCase("Quartz_Jobs")) {
			scenarioCode = "Quartz_Jobs_BS-01 ";
		}
		// ##############################################################################
		
		
		// ########################## Work Order ######################################
		if (className.equalsIgnoreCase("NewNew_Draft_InReview_No_Fac")) {
			scenarioCode = "WO_BS-01 ";
		}

		if (className.equalsIgnoreCase("NewNew_Draft_InReview_With_Fac")) {
			scenarioCode = "WO_BS-02 ";
		}

		if (className.equalsIgnoreCase("NewNew_Draft_InProcess_No_Fac")) {
			scenarioCode = "WO_BS-03 ";
		}

		if (className.equalsIgnoreCase("NewNew_Draft_InProcess_With_Fac")) {
			scenarioCode = "WO_BS-04 ";
		}

		if (className.equalsIgnoreCase("NewNew_InProc_Completed_No_Fac")) {
			scenarioCode = "WO_BS-05 ";
		}

		if (className.equalsIgnoreCase("NewNew_InProc_Complete_With_Fac")) {
			scenarioCode = "WO_BS-06 ";
		}

		if (className.equalsIgnoreCase("NN_Complete_With_Fac_N_BatchJob")) {
			scenarioCode = "WO_BS-07 ";
		}

		if (className.equalsIgnoreCase("NN_Upto_Lit_PEval_Ready_No_Fac")) {
			scenarioCode = "WO_BS-08 ";
		}

		if (className.equalsIgnoreCase("NN_Upto_Fac_Created")) {
			scenarioCode = "WO_BS-09 ";
		}

		if (className.equalsIgnoreCase("WO_Annual")) {
			scenarioCode = "WO_BS-10 ";
		}

		if (className.equalsIgnoreCase("WO_Custom")) {
			scenarioCode = "WO_BS-11 ";
		}

		if (className.equalsIgnoreCase("WO_Failure_Resolution")) {
			scenarioCode = "WO_BS-12 ";
		}

		if (className.equalsIgnoreCase("BulkAssign_Unassign_To_Assign")) {
			scenarioCode = "WO_BS-13 ";
		}

		if (className.equalsIgnoreCase("BulkAssign_ChangeAssignTo")) {
			scenarioCode = "WO_BS-14 ";
		}

		if (className.equalsIgnoreCase("Linking_Custom_Custom_WO")) {
			scenarioCode = "WO_BS-15 ";
		}

		if (className.equalsIgnoreCase("Modbrack_Not_Certified")) {
			scenarioCode = "WO_BS-16 ";
		}

		
		
		// ########################## Non_Food_Compounds_suite ######################################

		if (className.equalsIgnoreCase("ModBrack_WRAS_Approved")) {
			scenarioCode = "ModBrack_WRAS_Approved_BS-01";
		}

		if (className.equalsIgnoreCase("ModBrack_WRAS_Rejected")) {
			scenarioCode = "ModBrack_WRAS_Rejected_BS-02";
		}
		
		if (className.equalsIgnoreCase("ModBrack_WRAS_CancelledByClient")) {
			scenarioCode = "ModBrack_WRAS_CancelledByClient_BS-03";
		}
		
		
		if (className.equalsIgnoreCase("ModBrack_TMV_Certified")) {
			scenarioCode = "ModBrack_TMV_Certified_BS-04";
		}
		
		
		if (className.equalsIgnoreCase("ModBrack_BS6920_Certified")) {
			scenarioCode = "ModBrack_BS6920_Certified_BS-05";
		}
		
		if (className.equalsIgnoreCase("ModBrack_Reg4_Certified")) {
			scenarioCode = "ModBrack_Reg4_Certified_BS-06";
		}
		
		if (className.equalsIgnoreCase("Resolution_WRAS_Approved")) {
			scenarioCode = "Resolution_WRAS_Approved_BS-07";
		}
		
		
		
		// ########################## Non_Food_Compounds_suite ######################################

		if (className.equalsIgnoreCase("NFC_ExistingNew_Draft_Complete")) {
			scenarioCode = "NFC_BS-01";
		}

		
		// ########################## Dietary Supplement ######################################

		if (className.equalsIgnoreCase("ModBrack_Certified")) {
			scenarioCode = "DS_BS-01";
		}

		
		
		
		
		
		// ########################## ISR_suite ######################################
		if (className.equalsIgnoreCase("ISO9001_Single")) {
			scenarioCode = "ISO9001_Single_BS-01 ";
		}
		
		
		if (className.equalsIgnoreCase("ISO9001_Corporate")) {
			scenarioCode = "ISO9001_Corporate_BS-02 ";
		}
		
		if (className.equalsIgnoreCase("ISO9001_Campus")) {
			scenarioCode = "ISO9001_Campus_BS-03 ";
		}
		
		
		// ########################## Sus_suite ######################################
		if (className.equalsIgnoreCase("CreateWOforSustainabilitySingle")) {
			scenarioCode = "Sus_Single_BS-01 ";
		}
		if (className.equalsIgnoreCase("CreateWOforSustainabilityCampus")) {
			scenarioCode = "Sus_Campus_BS-02 ";
		}
		
/*		// ########################## Navigation_suite ######################################
		if (className.equalsIgnoreCase("IpulseNavigationMatrix")) {
			scenarioCode = "Navigation_BS-01 ";
		}
*/
		// ########################## Navigation_suite ######################################
		if (className.equalsIgnoreCase("SearchClientForISR")) {
			scenarioCode = "SearchClientForISR_BS-01 ";
		}
		if (className.equalsIgnoreCase("SearchClientForFoodEquipment")) {
			scenarioCode = "searchClientFoodEquipment_BS02 ";
		}
		if (className.equalsIgnoreCase("NavigationMatrixForAlertTab")) {
			scenarioCode = "navigationMatrixForAlertTab_BS03 ";
		}
		if (className.equalsIgnoreCase("NavigationMatrixForTasksTab")) {
			scenarioCode = "navigationMatrixForTasksTab_BS04 ";
		}
		if (className.equalsIgnoreCase("NavigationMatrixForSearchWO")) {
			scenarioCode = "NavigationMatrixForSearchWO_BS05 ";
		}
		// ########################## SCFS_suite ######################################

		
		if (className.equalsIgnoreCase("Cert_Standards")) {
			scenarioCode = "SCFS_Cert_Standards_BS-01 ";
		}
		
		if (className.equalsIgnoreCase("Non_Cert_Standards")) {
			scenarioCode = "SCFS_Non_Cert_Standards_BS-02 ";
		}
		

		
		
		
		
		
		
		
		
		
		
		
		
		// ########################## Proposal ######################################

		if (className.equalsIgnoreCase("Proposal_Won")) {
			scenarioCode = "Prop_BS-01 ";
		}

		if (className.equalsIgnoreCase("Proposal_Lost")) {
			scenarioCode = "Prop_BS-02 ";
		}

		if (className.equalsIgnoreCase("Proposal_RequoteWon")) {
			scenarioCode = "Prop_BS-03 ";
		}

		if (className.equalsIgnoreCase("Proposal_Won_Acetic_Acid")) {
			scenarioCode = "Prop_BS-04 ";
		}

		if (className.equalsIgnoreCase("Proposal_VerifyBillRate")) {
			scenarioCode = "Prop_BS-05 ";
		}
		
		
		if (className.equalsIgnoreCase("Create_WO_from_Proposal")) {
			scenarioCode = "Prop_BS-06 ";
		}

		// ########################## Health Science ######################################
		if (className.equalsIgnoreCase("HS_lead_proposal_project_cycle")) {
			scenarioCode = "HS_BS-01 ";
		}
		
		
		
		// ##########################Agriculture ######################################
		if (className.equalsIgnoreCase("Create_Membership")) {
			scenarioCode = "AG_Create_Membership-01 ";
		}
		
		if (className.equalsIgnoreCase("Certification_Autotest")) {
			scenarioCode = "AG_Certification_Autotest-02 ";
		}
		
		
		if (className.equalsIgnoreCase("Create_Membership_With_Option2")) {
			scenarioCode = "Create_Membership_With_Option2-03 ";
		}
		
		if (className.equalsIgnoreCase(IPULSE_CONSTANTS.GRASP_scheme_TestCase_Name)) {
			scenarioCode = IPULSE_CONSTANTS.GRASP_scheme_TestCase_Name+"-04 ";
		}
		
		
		if (className.equalsIgnoreCase(IPULSE_CONSTANTS.AH2_scheme_TestCase_Name)) {
			scenarioCode = IPULSE_CONSTANTS.AH2_scheme_TestCase_Name+"-05 ";
		}
		
		if (className.equalsIgnoreCase(IPULSE_CONSTANTS.Alerts_testing_App_Form_Option2_TestCase_Name)) {
			scenarioCode = IPULSE_CONSTANTS.Alerts_testing_App_Form_Option2_TestCase_Name+"-06 ";
		}
		
		
		
		// ##########################Audit_SAM_SPA_suitexls ######################################
		if (className.equalsIgnoreCase("SAM_assuring_all_screens_load")) {
			scenarioCode = "SAM_assuring_all_screens_load-01 ";
		}
		
		
		
		
		// ########################## Test_Plan_suitexls ######################################
		if (className.equalsIgnoreCase("Create_Test_Plan")) {
			scenarioCode = "TP-01 ";
		}
		
		
		// ########################## Generic_suitexls ######################################
		if (className.equalsIgnoreCase("Uploading_Downloading_Docs")) {
			scenarioCode = "Uploading_Downloading_Docs-BS01 ";
		}
		
		
		if (className.equalsIgnoreCase("Basic_Search_Functionality")) {
			scenarioCode = "Basic_Search_Functionality-BS02 ";
		}
		
		
		// ########################## Invoice_suitexls ######################################
		if (className.equalsIgnoreCase("Create_Invoice")) {
			scenarioCode = "Create_Invoice-BS01 ";
		}
		
		if (className.equalsIgnoreCase("Exp_Allocation")) {
			scenarioCode = "Exp_Allocation-BS02 ";
		}

		// ########################## EPSF Suite ######################################
		if (className.equalsIgnoreCase("EPSF_Draft_Req_Recvd")) {
			scenarioCode = "EPSF_BS-01 ";
		}
		if (className.equalsIgnoreCase("Draft_tossed_returned")) {
			scenarioCode = "EPSF_BS-02 ";
		}
		if (className.equalsIgnoreCase("Draft_Hold_LoggedIn")) {
			scenarioCode = "EPSF_BS-03 ";
		}
		if (className.equalsIgnoreCase("EPSF_Draft_Req_Void")) {
			scenarioCode = "EPSF_BS-04 ";
		}
		if (className.equalsIgnoreCase("EPSF_Draft_Req_Expired")) {
			scenarioCode = "EPSF_BS-05 ";
		}

		if (className.equalsIgnoreCase("EPSF_Draft_Req_Admin_Login")) {
			scenarioCode = "EPSF_BS-06 ";
		}

		if (className.equalsIgnoreCase("EPSF_Complete_Flow")) {
			scenarioCode = "EPSF_BS-07 ";
		}

		// ########################## Direct_NSFOnline######################################
		if (className.equalsIgnoreCase("Direct_NSFOnline_Search_Docs")) {
			scenarioCode = "Direct_NSFOnline_Search_Docs_BS-01 ";
		}

		if (className.equalsIgnoreCase("Direct_NSFO_Search_Suppliers")) {
			scenarioCode = "Direct_NSFO_Search_Suppliers_BS-02 ";
		}

		if (className.equalsIgnoreCase("Direct_NSFO_Search_Audits_Test")) {

			scenarioCode = "Direct_NSFO_Search_Audits_Test_BS-03 ";
		}

		if (className.equalsIgnoreCase("Direct_NSFO_Verify_CARs_Audits")) {
			scenarioCode = "Direct_NSFO_Verify_CARs_Audits_BS-04 ";
		}

		if (className.equalsIgnoreCase("Direct_NSFO_SCA_IM_Validations")) {
			scenarioCode = "Direct_NSFO_SCA_IM_Validations_BS-05 ";
		}

		// ########################## Audit_Functional ######################################
		if (className.equalsIgnoreCase("Search_Audit")) {
			scenarioCode = "Search_Audit_BS-01 ";
		}
		
		if (className.equalsIgnoreCase("View_Audit")) {
			scenarioCode = "View_Audit_BS-02 ";
		}
		
		if (className.equalsIgnoreCase("View_All_Tabs_In_View_Audit")) {
			scenarioCode = "View_All_Tabs_In_View_Audit_BS-03 ";
		}
		
		
		// ########################## FPC Work Order ######################################
		if (className.equalsIgnoreCase("BS_01_FPC_Custom_Certified")) {
			scenarioCode = "FPC_BS-01 ";
		}
		
		
		// ########################## Issue Management ######################################
		if (className.equalsIgnoreCase("CreateIssue_PRQ")) {
			scenarioCode = "IM_BS-01 ";
		}

		if (className.equalsIgnoreCase("CreateEscalation_PRQ")) {
			scenarioCode = "IM_BS-02 ";
		}

		if (className.equalsIgnoreCase("CreateEscalation_ChargesVerify")) {
			scenarioCode = "IM_BS-03 ";
		}

		if (className.equalsIgnoreCase("Escalation_FourIssues")) {
			scenarioCode = "IM_BS-04 ";
		}

		if (className.equalsIgnoreCase("Escalation_PRQ_8Issues_Scenario1")) {
			scenarioCode = "IM_BS-05 ";
		}

		if (className.equalsIgnoreCase("Escalation_PRQ_8Issues_Scenario2")) {
			scenarioCode = "IM_BS-06 ";
		}

		if (className.equalsIgnoreCase("CreateIssue_NC")) {
			scenarioCode = "IM_BS-07 ";
		}

		if (className.equalsIgnoreCase("CreateEscalation_NC")) {
			scenarioCode = "IM_BS-08 ";
		}

		if (className.equalsIgnoreCase("Escalation_NC_8Issues_Scenario1")) {
			scenarioCode = "IM_BS-09 ";
		}

		if (className.equalsIgnoreCase("CreateIssue_FBY")) {
			scenarioCode = "IM_BS-10 ";
		}

		if (className.equalsIgnoreCase("CreateEscalation_FBY")) {
			scenarioCode = "IM_BS-11 ";
		}

		if (className.equalsIgnoreCase("Escalation_FBY_8Issues_Scenario1")) {
			scenarioCode = "IM_BS-12 ";
		}

		if (className.equalsIgnoreCase("CreateEscalation_AFP")) {
			scenarioCode = "IM_BS-13 ";
		}

		if (className.equalsIgnoreCase("CreateEscalation_AFP_Diff_Issues")) {
			scenarioCode = "IM_BS-14 ";
		}

		if (className.equalsIgnoreCase("CreateEscalation_DIS")) {
			scenarioCode = "IM_BS-15 ";
		}

		if (className.equalsIgnoreCase("CloseEscalation_Messages")) {
			scenarioCode = "IM_BS-16 ";
		}

		if (className.equalsIgnoreCase("Escalation_PRQ_DeleteIssue")) {
			scenarioCode = "IM_BS-17 ";
		}

		if (className.equalsIgnoreCase("CreateIssue_From_NsfOnline")) {
			scenarioCode = "IM_BS-18 ";
		}
				
		if (className.equalsIgnoreCase("Escalation_PRQ_WithOutAlerts")) {
			scenarioCode = "IM_BS-19 ";
		}
		if (className.equalsIgnoreCase("DesiredOptions_Duplicates_Check")) {
			scenarioCode = "IM_BS-20 ";
		}
		
		
		// ########################## Client ######################################
		if (className.equalsIgnoreCase("CreateClient")) {
			scenarioCode = "Client_BS-01 ";
		}

		if (className.equalsIgnoreCase("SearchClient")) {
			scenarioCode = "Client_BS-02 ";
		}
		
		if (className.equalsIgnoreCase("Run_Algorithm")) {
			scenarioCode = "Client_BS-03 ";
		}		
		
		if (className.equalsIgnoreCase("Check_iPulse_Login_Time")) {
			scenarioCode = "Client_BS-04 ";
		}

		// ########################## Monitor Plan ######################################
		if (className.equalsIgnoreCase("CreateActiveMonitorPlan")) {
			scenarioCode = "MP_BS-01 ";
		}

		if (className.equalsIgnoreCase("MonitorPlan_DifferentStates")) {
			scenarioCode = "MP_BS-02 ";
		}

		if (className.equalsIgnoreCase("BillCodePriceCheck_PastYear")) {
			scenarioCode = "MP_BS-03 ";
		}

		// ########################## NSF-Online ######################################
		if (className.equalsIgnoreCase("NSFOnline_Search_Documents_Test")) {
			scenarioCode = "NSFOnline_BS-01 ";
		}

		if (className.equalsIgnoreCase("NSFOnline_Search_Suppliers_Test")) {
			scenarioCode = "NSFOnline_BS-02 ";
		}

		if (className.equalsIgnoreCase("NSFOnline_Search_Audits_Test")) {

			scenarioCode = "NSFOnline_BS-03 ";
		}

		if (className.equalsIgnoreCase("NSFOnline_Verify_CARs_audits")) {
			scenarioCode = "NSFOnline_BS-04 ";
		}

		if (className.equalsIgnoreCase("NSFOnline_CSA_IM")) {
			scenarioCode = "NSFOnline_BS-05 ";
		}
		if (className.equalsIgnoreCase("NSFOnline_Search_GlobalDocument")) {
			scenarioCode= "NSFOnline_BS-06 ";			
		}		


		// ########################## TraQtion ######################################
		if (className.equalsIgnoreCase("Search_Screens_1")) {
			scenarioCode = "TraQtion_BS-01 ";
		}
		if (className.equalsIgnoreCase("SER_Test_2")) {
			scenarioCode = "TraQtion_BS-02 ";
		}
		if (className.equalsIgnoreCase("Complaint_Management_3")) {
			scenarioCode = "TraQtion_BS-03 ";
		}
		if (className.equalsIgnoreCase("CreateAssetsForHooters_4")) {
			scenarioCode = "TraQtion_BS-04 ";
		}
		if (className.equalsIgnoreCase("Vendor_Registration_5")) {
			scenarioCode = "TraQtion_BS-05 ";
		}
		if (className.equalsIgnoreCase("All_Module_Screen_for_NoError_6")) {
			scenarioCode = "TraQtion_BS-06 ";
		}
		if (className.equalsIgnoreCase("TraQtion_Costco_Registration")) {
			scenarioCode = "TraQtion_BS-07 ";
		}
		
		//############################ Grip #############################################
		if (className.equalsIgnoreCase("SearchApplicant")) {
			scenarioCode = "Grip_BS-01 ";
		}

		if (className.equalsIgnoreCase("SearchResource")) {
			scenarioCode = "Grip_BS-02 ";
		}
		
		if (className.equalsIgnoreCase("CreateApplicant_ContractYES")) {
			scenarioCode = "Grip_BS-03 ";
		}

		if (className.equalsIgnoreCase("Resource_Master_Data_Setup")) {
			scenarioCode = "Grip_BS-04 ";
		}

		if (className.equalsIgnoreCase("SearchResource_VerifyStandards")) {
			scenarioCode = "Grip_BS-05 ";
		}
		
		
		//############################ Automotive #############################################
		if (className.equalsIgnoreCase("Create_Submission")) {
			scenarioCode = "Automotive_BS-01 ";
		}
		
		//############################ Search Audits #############################################
		if (className.equalsIgnoreCase("Search_Audits_and_Vists")) {
			scenarioCode = "SearchAudits_BS-01 ";
		}
		
		
		//############################ New_NSFOnline #############################################
		if (className.equalsIgnoreCase("BS_01_Site_Snpsht_RecordMatch")) {
			scenarioCode = "NsfConnect_BS-01 ";
		}
		if (className.equalsIgnoreCase("BS_02_Audit_Snpsht_RecordMatch")) {
			scenarioCode = "NsfConnect_BS-02 ";
		}
		if (className.equalsIgnoreCase("BS_03_CheckList")) {
			scenarioCode = "NsfConnect_BS-03 ";
		}
		if (className.equalsIgnoreCase("BS_04_CA_Alert_AdvSrch_Snpshot")) {
			scenarioCode = "NsfConnect_BS-04 ";
		}
		if (className.equalsIgnoreCase("BS_05_Document_AdvSrch_SrchDoc")) {
			scenarioCode = "NsfConnect_BS-05 ";
		}
		if (className.equalsIgnoreCase("BS_06_Suppliers_SearchSupplier")) {
			scenarioCode = "NsfConnect_BS-06 ";
		}
		
		if (className.equalsIgnoreCase("BS_07_Audit_Standard_AdvSearch")) {
			scenarioCode = "NsfConnect_BS-07 ";
		}
		if (className.equalsIgnoreCase("BS_08_CheckList_SingleUsrLogin")) {
			scenarioCode = "NsfConnect_BS-08 ";
		}
		
		if (className.equalsIgnoreCase("BS_09_Invoice_n_Certificate")) {
			scenarioCode = "NsfConnect_BS-09 ";
		}
		

		if (className.equalsIgnoreCase("BS_10_BkAdmin")) {
			scenarioCode = "NsfConnect_BS-10 ";
		}
		

		if (className.equalsIgnoreCase("BS_11_Appeal_Create_To_Review")) {
			scenarioCode = "NsfConnect_BS-11 ";
		}
		
		if (className.equalsIgnoreCase("BS_12_BkAdmin_Mgt_Reports")) {
			scenarioCode = "NsfConnect_BS-12 ";
		}
		
		if (className.equalsIgnoreCase("BS_13_IM_CreateIssue_AdvSearch")) {
			scenarioCode = "NsfConnect_BS-13 ";
		}
		

		if (className.equalsIgnoreCase("BS_14_Login_Scenarios")) {
			scenarioCode = "NsfConnect_BS-14 ";
		}
		
		if (className.equalsIgnoreCase("BS_15_NSFOnlineAccountSetup")) {
			scenarioCode = "NsfConnect_BS-15 ";
		}
		if (className.equalsIgnoreCase("BS_16_Alerts_SortOder")) {
			scenarioCode = "NsfConnect_BS-16 ";
			
		}
		
		if (className.equalsIgnoreCase("BS_17_Testing_Reports")) {
			scenarioCode = "NsfConnect_BS-17 ";
		}
		
		if (className.equalsIgnoreCase("BS_18_Product_Doc_Report")) {
			scenarioCode = "NsfConnect_BS-18 ";
		}
		if (className.equalsIgnoreCase("BS_19_Client_Resource_Reports")) {
			scenarioCode = "NsfConnect_BS-19 ";
		}
		if (className.equalsIgnoreCase("BS_20_Listings")) {
			scenarioCode = "NsfConnect_BS-20 ";
		}
		if (className.equalsIgnoreCase("BS_21_Submission_Create_Submit")) {
			scenarioCode = "NsfConnect_BS-21 ";
		}
		if (className.equalsIgnoreCase("BS_22_Requalification")) {
			scenarioCode = "NsfConnect_BS-22 ";
		}
		if (className.equalsIgnoreCase("BS_23_Questionnaire")) {
			scenarioCode = "NsfConnect_BS-23 ";
		}
		if (className.equalsIgnoreCase("BS_24_Users")) {
			scenarioCode = "NsfConnect_BS-24 ";
		}
		if (className.equalsIgnoreCase("BS_25_Audit_Component")) {
			scenarioCode = "NsfConnect_BS-25 ";
		}
		if (className.equalsIgnoreCase("BS_26_Standard_Members")) {
			scenarioCode = "NsfConnect_BS-26 ";
		}
		if (className.equalsIgnoreCase("BS_27_Standard_Member_Audits")) {
			scenarioCode = "NsfConnect_BS-27 ";
		}
		if (className.equalsIgnoreCase("BS_28_Findings")) {
			scenarioCode = "NsfConnect_BS-28 ";
		}
		if (className.equalsIgnoreCase("BS_29_Food_Managers")) {
			scenarioCode = "NsfConnect_BS-29 ";
		}
		if (className.equalsIgnoreCase("BS_30_Applications")) {
			scenarioCode = "NsfConnect_BS-30 ";
		}
		if (className.equalsIgnoreCase("BS_31_Product_Review")) {
			scenarioCode = "NsfConnect_BS-31 ";
		}
		if (className.equalsIgnoreCase("BS_32_Site_Documents")) {
			scenarioCode = "NsfConnect_BS-32 ";
		}
		if (className.equalsIgnoreCase("BS_33_Pre_CARs")) {
			scenarioCode = "NsfConnect_BS-33 ";
		}
		if (className.equalsIgnoreCase("BS_34_Audit_CA")) {
			scenarioCode = "NsfConnect_BS-34 ";
		}
		if (className.equalsIgnoreCase("BS_35_User_Registration")) {
			scenarioCode = "NsfConnect_BS-35 ";
		}
		if (className.equalsIgnoreCase("BS_36_Service_Request")) {
			scenarioCode = "NsfConnect_BS-36 ";
		}
		if (className.equalsIgnoreCase("BS_37_IM_Integration_Test")) {
			scenarioCode = "NsfConnect_BS-37 ";
		}
		if (className.equalsIgnoreCase("BS_38_AdvSrch_ExportFunctional")) {
			scenarioCode = "NsfConnect_BS-38 ";
		}
		if (className.equalsIgnoreCase("BS_39_Subway_Dashbord_CA_Report")) {
			scenarioCode = "NsfConnect_BS-39 ";
		}
		//############################ ipulse Alerts #############################################
		if (className.equalsIgnoreCase("Alerts_WithOut_ORG_1")) {
			scenarioCode = "iPulseAlerts_BS-01 ";
		}
		if (className.equalsIgnoreCase("Alerts_With_ORG_2")) {
			scenarioCode = "iPulseAlerts_BS-02 ";
		}
		if (className.equalsIgnoreCase("Alerts_Navigation_3")) {
			scenarioCode = "iPulseAlerts_BS-03 ";
		}
		if (className.equalsIgnoreCase("ClientNavigation_4")) {
			scenarioCode = "iPulseAlerts_BS-04 ";
		}
		//############################ Listings Application #############################################
		if (className.equalsIgnoreCase("BS_01_VerifyStandards")) {
			scenarioCode = "Listings_BS-01 ";
		}
		if (className.equalsIgnoreCase("BS_02_CreateProgram")) {
			scenarioCode = "Listings_BS-02 ";
		}
		if (className.equalsIgnoreCase("BS_03_VerifyListings")) {
			scenarioCode = "Listings_BS-03 ";
		}
		if (className.equalsIgnoreCase("Sanity_testing")) {
			scenarioCode = "Sanity_testing ";
		}
		return scenarioCode;

	}

	// ############## Configuration Function#########################################################
	// Initialising the Tests
	public void fnInitialize() throws Throwable {

		if (!isInitalized) {
			
			
			//fnpSetDBPassword();
			
			//Our customized method
	        cleanDirectory(new File(System.getenv("TEMP")));

			InputStream loggerStream = new FileInputStream(System.getProperty("user.dir") + "//src//log4j.properties");
			Properties prop = new Properties();
			prop.load(loggerStream);
			PropertyConfigurator.configure(prop);
			APP_LOGS = Logger.getLogger("devpinoyLogger");

			CONFIG = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//nsf//ecap//config/config.properties"); 
			CONFIG.load(ip);

			OR = new Properties();
			ip = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//nsf//ecap//config/OR.properties");
			OR.load(ip);

			OR_SUS = new Properties();
			ip = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//nsf//ecap//config/OR_SUS.properties");
			OR_SUS.load(ip);
			
			// property file for Navigation Matrix
			OR_NM = new Properties();
			ip = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//nsf//ecap//config/OR_NM.properties");
			OR_NM.load(ip);
			
			OR_Navi = new Properties();
			ip = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//nsf//ecap//config/OR_Navi.properties");
			OR_Navi.load(ip);
			/* OR_HS = new Properties(); ip = new
			 * FileInputStream(System.getProperty("user.dir") +
			 * "//src//nsf//ecap//config/OR_HS.properties"); OR_HS.load(ip); */

			OR_CLNT = new Properties();
			ip = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//nsf//ecap//config/OR_CLNT.properties");
			OR_CLNT.load(ip);

			OR_IM = new Properties();
			ip = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//nsf//ecap//config/OR_IM.properties");
			OR_IM.load(ip);

			OR_MtrPlan = new Properties();
			ip = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//nsf//ecap//config/OR_MtrPlan.properties");
			OR_MtrPlan.load(ip);

			OR_NsfOnline = new Properties();
			ip = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//nsf//ecap//config/OR_NsfOnline.properties");
			OR_NsfOnline.load(ip);

			OR_TraQtion = new Properties();
			ip = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//nsf//ecap//config/OR_TraQtion.properties");
			OR_TraQtion.load(ip);

			OR_Grip = new Properties();
			ip = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//nsf//ecap//config/OR_Grip.properties");
			OR_Grip.load(ip);

			OR_Automotive = new Properties();
			ip = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//nsf//ecap//config/OR_Automotive.properties");
			OR_Automotive.load(ip);
			OR_SearchAudits = new Properties();
			ip = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//nsf//ecap//config/OR_SearchAudits.properties");
			OR_SearchAudits.load(ip);
			
			
			OR_New_NSFOnline = new Properties();
			ip = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//nsf//ecap//config/OR_New_NSFOnline.properties");
			OR_New_NSFOnline.load(ip);
			
			OR_Listings = new Properties();
			ip = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//nsf//ecap//config/OR_Listings.properties");
			OR_Listings.load(ip);
			

			
			env=CONFIG.getProperty("Environment");
			if (env.equalsIgnoreCase("Test") |env.equalsIgnoreCase("Dev")) {
				suiteXls = new Xls_Reader(System.getProperty("user.dir") + "\\TEST_full_scripts_xls\\TEST_full_scripts_Suites.xlsm");
				Alerts_iPulse_suitexls=new Xls_Reader(System.getProperty("user.dir") + "\\TEST_full_scripts_xls\\TEST_full_scripts_Alerts_iPulse_suite.xlsx");
				Work_Order_suitexls = new Xls_Reader(System.getProperty("user.dir") + "//TEST_full_scripts_xls\\TEST_full_scripts_Work_Order_suite.xlsx");
				FPC_Work_Order_suitexls = new Xls_Reader(System.getProperty("user.dir") + "//TEST_full_scripts_xls\\TEST_full_scripts_FPC_Work_Order_suite.xlsx");
				Wales_Work_Order_suitexls = new Xls_Reader(System.getProperty("user.dir") + "//TEST_full_scripts_xls\\TEST_full_scripts_Wales_Work_Order_suite.xlsx");
				Non_Food_Compounds_suitexls = new Xls_Reader(System.getProperty("user.dir") + "//TEST_full_scripts_xls\\TEST_full_scripts_Non_Food_Compounds_suite.xlsx");
				
				Dietary_Supplement_suitexls = new Xls_Reader(System.getProperty("user.dir") + "//TEST_full_scripts_xls\\TEST_full_scripts_Dietary_Supplement_suite.xlsx");
				
				Sustainability_suitexls = new Xls_Reader(System.getProperty("user.dir") + "//TEST_full_scripts_xls\\TEST_full_scripts_Sustainability_Suite.xlsx");
				Navigation_suitexls = new Xls_Reader(System.getProperty("user.dir") + "//TEST_full_scripts_xls\\TEST_full_scripts_Navigate_matrix_Suite.xlsx");
				ISR_suitexls = new Xls_Reader(System.getProperty("user.dir") + "//TEST_full_scripts_xls\\TEST_full_scripts_ISR_suite.xlsx");
				SCFS_suitexls = new Xls_Reader(System.getProperty("user.dir") + "//TEST_full_scripts_xls\\TEST_full_scripts_SCFS_suite.xlsx");
				
				Proposals_suitexls = new Xls_Reader(System.getProperty("user.dir") + "\\TEST_full_scripts_xls\\TEST_full_scripts_Proposals_suite.xlsx");
				Health_Science_suitexls = new Xls_Reader(System.getProperty("user.dir") + "//TEST_full_scripts_xls\\TEST_full_scripts_Health_Science_suite.xlsx");
				
				Agriculture_suitexls = new Xls_Reader(System.getProperty("user.dir") + "//TEST_full_scripts_xls\\TEST_full_scripts_Agriculture_suite.xlsx");
				
				Audit_SAM_SPA_suitexls = new Xls_Reader(System.getProperty("user.dir") + "//TEST_full_scripts_xls\\TEST_full_scripts_Audit_SAM_SPA_suite.xlsx");
				
				Test_Plan_suitexls = new Xls_Reader(System.getProperty("user.dir") + "//TEST_full_scripts_xls\\TEST_full_scripts_Test_Plan_suite.xlsx");
				Generic_suitexls = new Xls_Reader(System.getProperty("user.dir") + "//TEST_full_scripts_xls\\TEST_full_scripts_Generic_suite.xlsx");
				Invoice_Function_suitexls=new Xls_Reader(System.getProperty("user.dir") + "//TEST_full_scripts_xls\\TEST_full_scripts_Invoice_Function_suite.xlsx");
				
				EPSF_suitexls = new Xls_Reader(System.getProperty("user.dir") + "//TEST_full_scripts_xls\\TEST_full_scripts_EPSF_suite.xlsx");
				Direct_NSFOnline_suitexls = new Xls_Reader(System.getProperty("user.dir") + "//TEST_full_scripts_xls\\TEST_full_scripts_Direct_NSFOnline_suite.xlsx");
				//Audit_Functional_suitexls = new Xls_Reader(System.getProperty("user.dir") + "//TEST_full_scripts_xls\\TEST_full_scripts_Audit_Functional_suite.xlsx");

				IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//TEST_full_scripts_xls\\TEST_full_scripts_IssueMgt_Suite.xlsx");
				NSFOnline_suitexls = new Xls_Reader(System.getProperty("user.dir") + "//TEST_full_scripts_xls\\TEST_full_scripts_NSFOnline_Suite.xlsx");
				Client_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//TEST_full_scripts_xls\\TEST_full_scripts_Client_Suite.xlsx");
				MonitorPlan_suitexls = new Xls_Reader(System.getProperty("user.dir") + "//TEST_full_scripts_xls\\TEST_full_scripts_MonitorPlan_suite.xlsx");
				TraQtion_suitexls = new Xls_Reader(System.getProperty("user.dir") + "//TEST_full_scripts_xls\\TEST_full_scripts_TraQtion_suite.xlsx");
				Grip_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//TEST_full_scripts_xls\\TEST_full_scripts_Grip_Suite.xlsx");
				/*Automotive_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//TEST_full_scripts_xls\\TEST_full_scripts_Automotive_Suite.xlsx");*/
			/*	SearchAudits_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//TEST_full_scripts_xls\\TEST_full_scripts_SearchAudits_Suite.xlsx");*/
				New_NSFOnline_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//TEST_full_scripts_xls\\TEST_full_scripts_NSF_Connect_Suite.xlsx");
				Listings_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//TEST_full_scripts_xls\\TEST_full_scripts_Listings_Suite.xlsx");

			} else {
					if (env.equalsIgnoreCase("Staging")) {
						suiteXls = new Xls_Reader(System.getProperty("user.dir") + "\\STAGING_full_scripts_xls\\STAGING_full_scripts_Suites.xlsm");
						Sustainability_suitexls = new Xls_Reader(System.getProperty("user.dir") + "//STAGING_full_scripts_xls\\STAGING_full_scripts_Sustainability_Suite.xlsx");
						Quartz_Jobs_suitexls= new Xls_Reader(System.getProperty("user.dir") + "//STAGING_full_scripts_xls\\STAGING_full_scripts_Quartz_Jobs_suite.xlsx");
						Work_Order_suitexls = new Xls_Reader(System.getProperty("user.dir") + "//STAGING_full_scripts_xls\\STAGING_full_scripts_Work_Order_suite.xlsx");
						FPC_Work_Order_suitexls = new Xls_Reader(System.getProperty("user.dir") + "//STAGING_full_scripts_xls\\STAGING_full_scripts_FPC_Work_Order_suite.xlsx");
						Wales_Work_Order_suitexls = new Xls_Reader(System.getProperty("user.dir") + "//STAGING_full_scripts_xls\\STAGING_full_scripts_Wales_Work_Order_suite.xlsx");
						Non_Food_Compounds_suitexls = new Xls_Reader(System.getProperty("user.dir") + "//STAGING_full_scripts_xls\\STAGING_full_scripts_Non_Food_Compounds_suite.xlsx");
						Dietary_Supplement_suitexls = new Xls_Reader(System.getProperty("user.dir") + "//STAGING_full_scripts_xls\\STAGING_full_scripts_Dietary_Supplement_suite.xlsx");
						ISR_suitexls = new Xls_Reader(System.getProperty("user.dir") + "//STAGING_full_scripts_xls\\STAGING_full_scripts_ISR_suite.xlsx");
						SCFS_suitexls = new Xls_Reader(System.getProperty("user.dir") + "//STAGING_full_scripts_xls\\STAGING_full_scripts_SCFS_suite.xlsx");
						
						Proposals_suitexls = new Xls_Reader(System.getProperty("user.dir") + "\\STAGING_full_scripts_xls\\STAGING_full_scripts_Proposals_suite.xlsx");
						Health_Science_suitexls = new Xls_Reader(System.getProperty("user.dir") + "//STAGING_full_scripts_xls\\STAGING_full_scripts_Health_Science_suite.xlsx");
						
						Agriculture_suitexls = new Xls_Reader(System.getProperty("user.dir") + "//STAGING_full_scripts_xls\\STAGING_full_scripts_Agriculture_suite.xlsx");
						
						Audit_SAM_SPA_suitexls = new Xls_Reader(System.getProperty("user.dir") + "//STAGING_full_scripts_xls\\STAGING_full_scripts_Audit_SAM_SPA_suite.xlsx");
						
						Test_Plan_suitexls = new Xls_Reader(System.getProperty("user.dir") + "//STAGING_full_scripts_xls\\STAGING_full_scripts_Test_Plan_suite.xlsx");
						Generic_suitexls = new Xls_Reader(System.getProperty("user.dir") + "//STAGING_full_scripts_xls\\STAGING_full_scripts_Generic_suite.xlsx");
						Invoice_Function_suitexls=new Xls_Reader(System.getProperty("user.dir") + "//STAGING_full_scripts_xls\\STAGING_full_scripts_Invoice_Function_suite.xlsx");
						
						EPSF_suitexls = new Xls_Reader(System.getProperty("user.dir") + "//STAGING_full_scripts_xls\\STAGING_full_scripts_EPSF_suite.xlsx");
						Direct_NSFOnline_suitexls = new Xls_Reader(System.getProperty("user.dir") + "//STAGING_full_scripts_xls\\STAGING_full_scripts_Direct_NSFOnline_suite.xlsx");
						//Audit_Functional_suitexls = new Xls_Reader(System.getProperty("user.dir") + "//STAGING_full_scripts_xls\\STAGING_full_scripts_Audit_Functional_suite.xlsx");

						IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//STAGING_full_scripts_xls\\STAGING_full_scripts_IssueMgt_Suite.xlsx");
						NSFOnline_suitexls = new Xls_Reader(System.getProperty("user.dir") + "//STAGING_full_scripts_xls\\STAGING_full_scripts_NSFOnline_Suite.xlsx");
						Client_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//STAGING_full_scripts_xls\\STAGING_full_scripts_Client_Suite.xlsx");
						MonitorPlan_suitexls = new Xls_Reader(System.getProperty("user.dir") + "//STAGING_full_scripts_xls\\STAGING_full_scripts_MonitorPlan_suite.xlsx");
						Grip_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//STAGING_full_scripts_xls\\STAGING_full_scripts_Grip_Suite.xlsx");
						/*Automotive_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//STAGING_full_scripts_xls\\STAGING_full_scripts_Automotive_Suite.xlsx");*/
					/*	SearchAudits_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//STAGING_full_scripts_xls\\STAGING_full_scripts_SearchAudits_Suite.xlsx");*/
						New_NSFOnline_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//STAGING_full_scripts_xls\\STAGING_full_scripts_NSF_Connect_Suite.xlsx");
						TraQtion_suitexls = new Xls_Reader(System.getProperty("user.dir") + "//STAGING_full_scripts_xls\\STAGING_full_scripts_TraQtion_suite.xlsx");
						Alerts_iPulse_suitexls = new Xls_Reader(System.getProperty("user.dir") + "//STAGING_full_scripts_xls\\STAGING_full_scripts_Alerts_iPulse_suite.xlsx");
						Listings_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//STAGING_full_scripts_xls\\STAGING_full_scripts_Listings_Suite.xlsx");
					} else {
						// code for saying no env variable defined
					}
			}
			
			
			
			
			
			
			isInitalized = true;
			hashXlData = new HashMap();

			fnpFirstTimeInitializationMethod();
			
		}

	}

	public static void fnppradeepKillIEProcess() {
		try {

			final String KILL = "taskkill /IM ";
			/*String processName2 = "iexplore.exe"; // IE process
			Runtime.getRuntime().exec(KILL + processName2);*/

			String processName1 = "chrome.exe"; // chrome process
			Runtime.getRuntime().exec(KILL + processName1);

			String processName3 = "chromedriver.exe"; // chrome process
			Runtime.getRuntime().exec(KILL + processName3);

/*			 String processName4 = "firefox.exe"; // firefox process
			  Runtime.getRuntime().exec(KILL + processName4); 
*/
			
			
			Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
			
			Runtime.getRuntime().exec("taskkill /F /IM iexplore.exe");

			
			
			 String processName5 = "netsession_win.exe"; // IE process
			  Runtime.getRuntime().exec(KILL + processName5); 
			//Thread.sleep(5000); // Allow OS to kill the process

		}

/*		catch (org.openqa.selenium.os.WindowsRegistryException e) {
			// nothing to do
		}
*/
		catch (java.lang.RuntimeException r) {
		}

		catch (Exception ex) {
			// nothing to do
		} catch (Throwable t) {
			// nothing to do
		}
	}

	public static void killprocess() throws InterruptedException {

		try {
			fnppradeepKillIEProcess();
			if (checkRunningProcess("IEDriverServer.exe")) {
				try {
					//WindowsUtils.killByName("IEDriverServer.exe");
					//WindowsUtils.tryToKillByName("netsession_win.exe");
					
					

					
					
					
				}  catch (java.lang.RuntimeException r) {
					//nothing to do
					//only to suppress unwanted exception
				} catch (Exception e) {

				} catch (Throwable t) {


				}
			}

			if (checkRunningProcess("chrome.exe")) {
				//WindowsUtils.tryToKillByName("chrome.exe");	
				try {
					//WindowsUtils.killByName("chrome.exe");
				} catch (java.lang.RuntimeException r) {
					//nothing to do
					//only to suppress unwanted exception
				}

			/*	 catch(org.openqa.selenium.os.WindowsRegistryException w){
				 * //nothing to do //only to suppress unwanted exception } 
*/			}

			if (checkRunningProcess("chromedriver.exe")) {
				//	WindowsUtils.tryToKillByName("chromedriver.exe");	
				try {
				//	WindowsUtils.killByName("chromedriver.exe");
				} catch (Throwable t) {
					//nothing to do
					//only to suppress unwanted exception
				}
			}
			
			
			
			 if (checkRunningProcess("geckodriver.exe")) { //
			  
			  try{
			//  WindowsUtils.killByName("geckodriver.exe");
			  }catch(Throwable t){
			  //nothing to do //only to suppress unwanted exception 
				  } 
			  } 

			
			
/*
			 if (checkRunningProcess("firefox.exe")) { //
			  
			  try{
			  WindowsUtils.tryToKillByName("firefox.exe");
			  }catch(org.openqa.selenium.os.WindowsRegistryException w){
			  //nothing to do //only to suppress unwanted exception 
				  } 
			  } */

		}



		catch (Throwable t) {
			//nothing to do
			//only to suppress unwanted exception
		}
	}

	public static void setEmailIdToSendResult(Xls_Reader xls) {

		if (emailIDToSendResultFolder.equalsIgnoreCase("")) {
			for (int i = 2; i <= xls.getRowCount("Test Suite"); i++) {
				if (xls.getCellData("Test Suite", "Email_id_ToSendResult", i).trim().equalsIgnoreCase("")) {
				} else {
					emailIDToSendResultFolder = xls.getCellData("Test Suite", "Email_id_ToSendResult", i).trim();
					break;

				}

			}

			xls = null; // release memory

		}

	}

	// wait till element get visible when element not in OR
	public static void fnWaitForVisible_NotInOR(String XpathKey) throws Throwable {
		int i = 0;
		WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(CONFIG.getProperty("implicitWaitTime")));

		int retries = 0;
		while (true) {
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XpathKey)));
				return;
			} catch (org.openqa.selenium.StaleElementReferenceException e) {
				if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					retries++;

					continue;
				} else {
					throw e;
				}
			} catch (Throwable t) {
				if (retries < 2) {
					retries++;
					continue;
				} else {
					throw t;
				}

			}
		}

	}

	public static boolean checkRunningProcess(String s) {
		boolean RunningProcess = false;

		List<String> processes = listRunningProcesses();
		String result = "";

		// display the result
		Iterator<String> it = processes.iterator();
		int i = 0;
		while (it.hasNext()) {
			result += it.next() + ",";
			i++;
			if (i == 10) {
				result += "\n";
				i = 0;
			}
		}

		if (result.contains(s)) {
			RunningProcess = true;
		} else {
			RunningProcess = false;
		}

		return RunningProcess;

	}

	public static List<String> listRunningProcesses() {
		List<String> processes = new ArrayList<String>();
		try {
			String line;
			// Process p = Runtime.getRuntime().exec("tasklist.exe /fo csv /nh");
			Process p = Runtime.getRuntime().exec("tasklist.exe");
			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((line = input.readLine()) != null) {
				if (!line.trim().equals("")) {
					// keep only the process name
					line = line.substring(0);
					processes.add(line.substring(0, line.indexOf(" ")));
				}

			}
			input.close();
		} catch (Exception err) {
			err.printStackTrace();
		}
		return processes;
	}



	public static String fnReturnDateWithTimeForExcel() {

		/* if (dateWithTimeInExcel==null) { String a=null; String b=null; String
		 * t=null; Reporter.log(fnpTimestamp() + "  " +
		 * "  @@@@@@@@@@@@@@@@In IF true condition@@@@@@@@@@@@@@@@@@@@@@@@"
		 * );
		 * 
		 * a=todayFolder; b=startExecutionTime; t=a + "--" + b;
		 * Reporter.log(fnpTimestamp() + "  "
		 * +"   in fnReturnDateWithTimeForExcel--value is---"+ t);
		 * dateWithTimeInExcel=t; Reporter.log(fnpTimestamp() + "  " +
		 * "  @@@@@@@@@@@@@@@@In IF true condition@@@@@@@@@@@@@@@@@@@@@@@@ value is "
		 * +dateWithTimeInExcel); return t; } else { Reporter.log(fnpTimestamp()
		 * + "  " +
		 * "  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ IN Else loop, value is ---"
		 * +dateWithTimeInExcel); return dateWithTimeInExcel; } */

		return dateWithTimeInExcel;
	}


	
	
	
	
	
	
	public static void fnpCreateXlsxFile(String fullFileNameWithPath,String sheetName,Object [] header){
		
			   	
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(sheetName);

        int rowCount = 0;
              
        Row row = sheet.createRow(rowCount++);
             
        int columnCount = 0;
             
         for (Object headerData :header ) {
                Cell cell = row.createCell(columnCount++);
                if (headerData instanceof String) {
          	      cell.setCellType(Cell.CELL_TYPE_STRING);
                  CellStyle style = workbook.createCellStyle();
                //  style.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
                  style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
                  style.setFillPattern(CellStyle.SOLID_FOREGROUND);   
                  cell.setCellStyle(style); 
                	
                	
                	
                    cell.setCellValue((String) headerData);
                } else if (headerData instanceof Integer) {
                	
          	      cell.setCellType(Cell.CELL_TYPE_STRING);
                  CellStyle style = workbook.createCellStyle();
                //  style.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
                  style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
                  style.setFillPattern(CellStyle.SOLID_FOREGROUND);   
                  cell.setCellStyle(style); 
                	
                	
                    cell.setCellValue((Integer) headerData);
                }
            }
     
         
  
        try{
        	FileOutputStream outputStream = new FileOutputStream(fullFileNameWithPath);
        	workbook.write(outputStream);
        }catch(Throwable t){
        	
        }
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


public static void fnpElapsedTime(String currentSuiteName,String testCaseName,String scriptCode,String result) throws Throwable{
	
	
	try{	
	String pathforExecution1=overRideScreenshotspath.replace("\\screenshots", "").trim();
	
	File timefolderinLogHistory = new File(System.getProperty("user.dir")+pathforExecution1);
	if (!timefolderinLogHistory.exists()) {
		APP_LOGS.debug("DIR '"+pathforExecution1+"' has not been present already"); 
		FileUtils.forceMkdir(timefolderinLogHistory);
		//timefolderinLogHistory.mkdir();
		APP_LOGS.debug("DIR '"+pathforExecution1+"'  has been created"); 
	}else{
		APP_LOGS.debug("DEBUG: DIR '"+pathforExecution1+"'  is already  created."); 
	}
	
	

	String pathforExecution=System.getProperty("user.dir")+pathforExecution1+"\\Current_ExecutionDetails.txt";
	

	
	String pathforExecution2=System.getProperty("user.dir")+"\\log\\log_executionDetails.txt";

	File file = new File(pathforExecution);


	// if file doesnt exists, then create it
	if (!file.exists()) {
		file.createNewFile();	

	}
	APP_LOGS.debug("DEBUG:2");

	String pathforExcelExecution3=System.getProperty("user.dir")+"\\log\\ExecutionDetails.xlsx";
	File file3 = new File(pathforExcelExecution3);
	String sheetName="Execution_Details";
	if (!file3.exists()) {
        Object[] header = {"S.No.","Date","Suite_Name","Test_Case_Name","Test_Case_Code","Browser","Start_Time","End_Time","Time_taken","Result"};
		fnpCreateXlsxFile( pathforExcelExecution3,sheetName,header);		
	}
	
	
	APP_LOGS.debug("DEBUG:3");
	
	Date endTime = new Date();
	long durationInMilliseconds = endTime.getTime()-start.getTime();
	
	
	
	long diffSeconds = durationInMilliseconds / 1000 % 60;
	long diffMinutes = durationInMilliseconds / (60 * 1000) % 60;
	long diffHours = durationInMilliseconds / (60 * 60 * 1000) % 24;
	long diffDays = durationInMilliseconds / (24 * 60 * 60 * 1000);
	
	
	
	FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);

	
	BufferedWriter bw = new BufferedWriter(fw);

	

	bw.write("******************Batch Started -->"+batchStartExecutionTime+"**********************************************");

	
	bw.newLine();

	
	bw.write("Suite name ---->"+currentSuiteName);

	
	bw.newLine();
	
	
	bw.write("Test case name ---->"+testCaseName);

	
	bw.newLine();

	
	bw.write("Test case code ---->"+scriptCode);
	
	
	bw.newLine();
	
	
	browserName = TestUtil.getBrowserName(suiteXls, currentSuiteName);
	bw.write("Browser name ---->"+browserName);
	
	
	bw.newLine();
	
	
	bw.write("Start Time ---->"+formatTime(start,"hh:mm:ss a"));

	
	bw.newLine();

	
	bw.write("End Time ---->"+formatTime(endTime,"hh:mm:ss a"));

	
	bw.newLine();

	
	bw.write("This execution tooks " + durationInMilliseconds + "ms ");
	
	
	bw.newLine();

	
	bw.write(diffHours + " hours, "+diffMinutes + " minutes, "+diffSeconds + " seconds.");

	
	bw.newLine();

	
	bw.newLine();

	
	bw.write("Result ---->"+result);

	
	bw.newLine();


	bw.write("****************************************************************");

	
	bw.newLine();

	
	bw.write("");

	
	bw.newLine();

	
	bw.write("");

	
	bw.newLine();

	
	
	bw.write("");

	
	bw.newLine();

	
	bw.close();

	
	APP_LOGS.debug("DEBUG:4");
	
	//writing in excel
	Xls_Reader excel_ExecutionDetailsXls = new Xls_Reader(pathforExcelExecution3);
	int totalRow = excel_ExecutionDetailsXls.getRowCount(sheetName);

	//int currentRowNo=totalRow+1;
	
	int currentRowNo;
	String serialNo;
	
	if (totalRow > 1) {
		serialNo=excel_ExecutionDetailsXls.getCellData(sheetName, "S.No.", totalRow).trim();
		String lastDate=excel_ExecutionDetailsXls.getCellData(sheetName, "Date", totalRow);
		if ((serialNo.equalsIgnoreCase("")) ||(lastDate.equalsIgnoreCase("")) ) {
			totalRow=totalRow-1;
			serialNo=excel_ExecutionDetailsXls.getCellData(sheetName, "S.No.", totalRow).trim();
			lastDate=excel_ExecutionDetailsXls.getCellData(sheetName, "Date", totalRow);
			
			if ((serialNo.equalsIgnoreCase("")) ||(lastDate.equalsIgnoreCase("")) ) {
				totalRow=totalRow-1;
				serialNo=excel_ExecutionDetailsXls.getCellData(sheetName, "S.No.", totalRow).trim();
				lastDate=excel_ExecutionDetailsXls.getCellData(sheetName, "Date", totalRow);
			}
		}
		 lastDate=excel_ExecutionDetailsXls.getCellData(sheetName, "Date", totalRow);
		Date pastDateInExcel=convertStringIntoDate(lastDate);
		Date currentDate=convertStringIntoDate(batchStartExecutionTime);
		
		if (currentDate.compareTo(pastDateInExcel)>0) {
			 currentRowNo=totalRow+2;
			 
			// setColorInRow(pathforExcelExecution3, sheetName,(totalRow+1));	
			 excel_ExecutionDetailsXls.fillRowColor(sheetName,(totalRow+1), "");
			 
		} else {
			//currentRowNo=totalRow+1;
			
			Date pastDateWithTimeInExcel=convertStringIntoDateWithTime(lastDate);
			Date currentDateWithTime=convertStringIntoDateWithTime(batchStartExecutionTime);
			
			if (currentDateWithTime.compareTo(pastDateWithTimeInExcel)>0) {
				currentRowNo=totalRow+2;
			}else{
				currentRowNo=totalRow+1;
			}
			
		}
		
		
		
		
		
		
		
/*		if(currentDate.after(pastDateInExcel)){
			////System.out.println("Date1 is after Date2");
		}
		*/
	} else {
		serialNo="0";
		currentRowNo=totalRow+1;
	}

	APP_LOGS.debug("DEBUG:5");
	
	//int integerSerialNo=Integer.valueOf(serialNo);
	double dserialNO = Double.parseDouble(serialNo);
	int integerSerialNo=(int)dserialNO;

	APP_LOGS.debug("DEBUG:6");
	int expectedSNo=integerSerialNo+1;
	
	
	Reporter.log(fnpTimestamp() + "  " + "S.No."+"----"+Integer.toString(expectedSNo));
	Reporter.log(fnpTimestamp() + "  " + "Date"+"----"+batchStartExecutionTime);
	Reporter.log(fnpTimestamp() + "  " + "Suite_Name"+"----"+currentSuiteName);
	Reporter.log(fnpTimestamp() + "  " + "Test_Case_Name"+"----"+testCaseName);
	Reporter.log(fnpTimestamp() + "  " + "Test_Case_Code"+"----"+scriptCode);
	Reporter.log(fnpTimestamp() + "  " + "Browser"+"----"+browserName);
	Reporter.log(fnpTimestamp() + "  " + "Start_Time"+"----"+formatTime(start,"hh:mm:ss a"));
	Reporter.log(fnpTimestamp() + "  " + "End_Time"+"----"+formatTime(endTime,"hh:mm:ss a"));
	Reporter.log(fnpTimestamp() + "  " + "Time_taken"+"----"+diffHours + " hours, "+diffMinutes + " minutes, "+diffSeconds + " seconds");
	Reporter.log(fnpTimestamp() + "  " + "Result"+"----"+result);

	APP_LOGS.debug("DEBUG:7");
	
	APP_LOGS.debug("S.No."+"----"+Integer.toString(expectedSNo));
	APP_LOGS.debug("Date"+"----"+batchStartExecutionTime);
	APP_LOGS.debug("Suite_Name"+"----"+currentSuiteName);
	APP_LOGS.debug("Test_Case_Name"+"----"+testCaseName);
	APP_LOGS.debug("Test_Case_Code"+"----"+scriptCode);
	APP_LOGS.debug("Browser"+"----"+browserName);
	APP_LOGS.debug("Start_Time"+"----"+formatTime(start,"hh:mm:ss a"));
	APP_LOGS.debug("End_Time"+"----"+formatTime(endTime,"hh:mm:ss a"));
	APP_LOGS.debug("Time_taken"+"----"+diffHours + " hours, "+diffMinutes + " minutes, "+diffSeconds + " seconds");
	APP_LOGS.debug("Result"+"----"+result);

	APP_LOGS.debug("DEBUG:8");
	
	excel_ExecutionDetailsXls.setCellData(sheetName,"S.No.",currentRowNo, Integer.toString(expectedSNo));
	Thread.sleep(500);
	excel_ExecutionDetailsXls.setCellData(sheetName,"Date",currentRowNo, batchStartExecutionTime);
	Thread.sleep(500);
	excel_ExecutionDetailsXls.setCellData(sheetName,"Suite_Name",currentRowNo, currentSuiteName);
	Thread.sleep(500);
	excel_ExecutionDetailsXls.setCellData(sheetName,"Test_Case_Name",currentRowNo, testCaseName);
	Thread.sleep(500);
	excel_ExecutionDetailsXls.setCellData(sheetName,"Test_Case_Code",currentRowNo, scriptCode);
	Thread.sleep(500);
	excel_ExecutionDetailsXls.setCellData(sheetName,"Browser",currentRowNo, browserName);
	Thread.sleep(500);
	excel_ExecutionDetailsXls.setCellData(sheetName,"Start_Time",currentRowNo, formatTime(start,"hh:mm:ss a"));
	Thread.sleep(500);
	excel_ExecutionDetailsXls.setCellData(sheetName,"End_Time",currentRowNo, formatTime(endTime,"hh:mm:ss a"));
	Thread.sleep(500);

	excel_ExecutionDetailsXls.setCellData(sheetName,"Time_taken",currentRowNo, diffHours + " hours, "+diffMinutes + " minutes, "+diffSeconds + " seconds");
	Thread.sleep(500);
	excel_ExecutionDetailsXls.setCellData(sheetName,"Result",currentRowNo, result);
	Thread.sleep(500);
	
	APP_LOGS.debug("DEBUG:9");
	
	
	}catch(Throwable t){
		String s=t.getMessage();
		//APP_LOGS.debug(" Error is --"+t.getStackTrace());
		APP_LOGS.debug(t.getStackTrace());
		APP_LOGS.debug(" IMPORTANT==>Either 'ExecutionDetails.xlsx'  file got corrupted in log folder or may be some other issue and  error is this --"+t.getMessage());
		//throw new Exception (t);
	}

}
	
	
//Added by Satya to check Suite skip	
public void fns_CheckSiteSkip(String SuiteName_from_Excel) throws Throwable{
	try{		
		TestSuiteBase_MonitorPlan TestSuiteBase_MonitorPlan = new TestSuiteBase_MonitorPlan();
		currentSuiteName = SuiteName_from_Excel;		
		fnInitialize();
		if(TestUtil.isSuiteRunnable(suiteXls, currentSuiteName)){
			killprocess(); //Added on 5.4.2018 as the scripts execution time got increased.
			Thread.sleep(3000);
			TestSuiteBase_MonitorPlan.fnsMoveMousePointerAtCenterBottomOfScreen();
			browserName=TestUtil.getBrowserName(suiteXls, currentSuiteName);
			excelSiteURL= TestUtil.getExcelSiteURL(suiteXls, currentSuiteName);
			
			int rowNum = suiteXls.getCellRowNum("Test Suite", "TSID", currentSuiteName);
			suiteXls.setCellData("Test Suite", "Last_Execution", rowNum, fnReturnDateWithTimeForExcel());
		}
		if(!TestUtil.isSuiteRunnable(suiteXls, currentSuiteName)){
			TestSuiteBase_MonitorPlan.fnsApps_Report_Logs("Skipped '"+currentSuiteName+"' as the runmode was set to NO");
			throw new SkipException("Runmode of '"+currentSuiteName+"' set to no. So Skipping all tests in '"+currentSuiteName+"'");
		}	
	
	}catch(SkipException sk){
		throw new SkipException (sk.getMessage());
	}catch(Throwable t){
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
}	
	
	//test
	
	
	
	public static void setClassNameText(String className){
		classNameText = className;
	}


	public static void setStartDate(){
		 start = new Date();//This start  time here refer from created membership onwards
	}

	
	public static void fnpSetDBPassword(){
		String pw = null;		
		
		
	//	pw = JOptionPane.showInputDialog("DB Password");
		
		JPasswordField pf = new JPasswordField();
		int okCxl = JOptionPane.showConfirmDialog(null, pf, "Enter DB Password", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

		if (okCxl == JOptionPane.OK_OPTION) {
		   pw = new String(pf.getPassword());
		 // System.err.println("You entered: " + pw);
		}
		
		
		
		
		dbPassword=pw;
		

	}

}