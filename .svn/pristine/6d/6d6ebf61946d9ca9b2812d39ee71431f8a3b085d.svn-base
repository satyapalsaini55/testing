package nsf.ecap.IssueMgt_Suite;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import java.io.File;

import nsf.ecap.Grip_Suite.TestSuiteBase_Grip;

//
import nsf.ecap.NSFOnline_Suite.TestSuiteBase_NSFOnline;
//
import nsf.ecap.Work_Order_suite.TestSuiteBase;
import nsf.ecap.base.TestBase;
/*import net.sourceforge.htmlunit.corejs.javascript.ast.WhileLoop;*/
import nsf.ecap.util.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



/*import org.ini4j.Config;
import org.junit.AfterClass;*/
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
//import org.openqa.selenium.os.WindowsUtils;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import com.google.common.base.Throwables;
import com.mysql.jdbc.TimeUtil;

import net.sf.saxon.expr.IfExpression;
import net.sf.saxon.instruct.ForEach;




public class TestSuiteBase_IM extends TestBase {
	
	public static String Connection_Name = null;
	public static String validation_error_msg_text;
	static boolean fail = false;
	public static boolean BrowserDriver = false;
	public static boolean ElementVisible = true;
	public static String SearchResult_Code_link_Xpath;
	public static String BrowserName="IE";
	public static boolean ScreenShotFlagWithOR_IM = false;
	
	public static boolean ApplicationVersion_Flag = true;
	
	
	public  static boolean ClassName_IM_Bs18 = false;
	
	public static boolean Technologist_CheckBox_Flag = false;
	public static String First_TechnologistName_From_DD = null;
	
	//################################################################Copied property from Monitor Plan file ##################################################################
	public static ChromeOptions options;
	public static DesiredCapabilities caps;
	public static boolean LoadingImageFlag = true;
	public static Integer Actual_Loading_Time;
	public static String Loading_Image_Xpath = null;
	public static String PageSourceText = null;
	public static String ErrorMsgText = null;
	public static ArrayList<String> Loading_Xpath_Array = new ArrayList<String>() ;
	public static Integer RowCount;
	public static String Autoit_scriptExeFile_Path ="";
	
//###############################################################################################################################################################################################	

	/*//Function to Take Screen Shot.
	public static void fnTake_Screen_Shot(String message) throws Exception {
		String MessageAfterFormat=fnRemoveFormatting_for_FileName(message);
			try{
			   FileUtils.forceMkdir(new File((System.getProperty("user.dir") + screenshots_path+ "//screenshots_IM//"+currentScriptCode +"//")));
			   Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			   Rectangle screenRectangle = new Rectangle(screenSize);
			   Robot robot = new Robot();
			   BufferedImage image = robot.createScreenCapture(screenRectangle);
			   ImageIO.write(image, "png", new File((System.getProperty("user.dir") + screenshots_path+ "//screenshots_IM//"+currentScriptCode +"//"+MessageAfterFormat+"_"+fnScreenShot_Date_format()+".PNG")));
			}catch(java.lang.NullPointerException n){
				fnApps_Report_Logs("ScreenShotNullPointerException >> "+n.getMessage());
				throw new Exception("NullPointerException Unable To take Screen Shots.");}
			 catch(java.io.IOException e){
				fnApps_Report_Logs("ScreenShotIOException >> "+Throwables.getStackTraceAsString(e));
				throw new Exception("IOException Unable To take Screen Shots.");}
}*/


	//Function to Take Screen Shot.
	public static void fnTake_Screen_Shot(String message) throws Exception {
		String MessageAfterFormat=fnsRemoveFormatting_for_FileName(message);
			try{
				String Suite_Foler_Name = "screenshots_IM";
				String File_Name = MessageAfterFormat+"_"+fnScreenShot_Date_format()+".PNG";
				FileUtils.forceMkdir(new File((System.getProperty("user.dir") +  screenshots_path+ "//"+Suite_Foler_Name+"//"+currentScriptCode +"//")));
				File image = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);			
				FileUtils.copyFile(image, new File((System.getProperty("user.dir") +  screenshots_path+ "//"+Suite_Foler_Name+"//"+currentScriptCode +"//"+File_Name)));
			}catch(java.lang.NullPointerException n){
				fnApps_Report_Logs("ScreenShotNullPointerException >> "+n.getMessage());
				throw new Exception("NullPointerException Unable To take Screen Shots.");}
			 catch(java.io.IOException e){
				fnApps_Report_Logs("ScreenShotIOException >> "+Throwables.getStackTraceAsString(e));
				throw new Exception("IOException Unable To take Screen Shots.");}
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

public static String fnLOGS_Date_format() {
	DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	Date date = new Date();
	return (dateFormat.format(date));
}




public static String fnIssueCreationText_Date_format() {
	DateFormat dateFormat = new SimpleDateFormat("ddMMMyyyy/HH:mm z");
	Date date = new Date();
	return (dateFormat.format(date));
}



//Return Time depends on Passing Value
public static String fnReturn_Requried_Time_HHmm(int WhichTime_forCurrentMinute) {
	Calendar cal = Calendar.getInstance();
	DateFormat minuteFormat = new SimpleDateFormat("HH:mm");
	cal.add(Calendar.MINUTE, WhichTime_forCurrentMinute);
	String time = minuteFormat.format(cal.getTime()).trim();
	
	return time;
}




public static String fnScreenShot_Date_format() {
	DateFormat dateFormat = new SimpleDateFormat("_dd.MM.yyyy_HH.mm.ss");
	Date date = new Date();
	return (dateFormat.format(date));
}



public static void fnApps_Report_Logs(String LogMessage) {

	APP_LOGS.debug(LogMessage);
	System.out.println(LogMessage);
	Reporter.log(fnLOGS_Date_format() + "  " + LogMessage);

}


public static void fnWait(long time1) throws Throwable {
	if(BrowserDriver==true){
	Thread.sleep(time1);}
	else{ /*Nothing to Do*/ }
}





//Click on Link by matchingText Xpath
public static void fnsTable_ClickOn_LINK_ByMatchingText(String LinkMatchingText) throws Throwable{
	
	try{
		SearchResult_Code_link_Xpath = ".//a[(text()='" + LinkMatchingText.trim() + "')]"; 
		String Displayed_Xpath = WithOut_OR_Return_XPATH_if_More_than_One_Coming(SearchResult_Code_link_Xpath);
		
		WithOut_OR_fnGet_Element_Enabled(Displayed_Xpath);
		
		driver.findElement(By.xpath(Displayed_Xpath)).click();
		fnApps_Report_Logs("PASSED == Successfully click on the Link having xpath >> "+Displayed_Xpath);
	}catch(NoSuchWindowException W){
		isTestCasePass = false;
		throw new Exception(W.getMessage());			}
	catch(Throwable t){
		isTestCasePass = false;
		fnTake_Screen_Shot("LinkClickFail");
		fnApps_Report_Logs("FAILED == Unable to Click on Link having Xpath >> "+SearchResult_Code_link_Xpath+", Please refer ScreenShot [ "+"LinkClickFail"+fnScreenShot_Date_format() +" ]."+". Getting Exception  >> "+Throwables.getStackTraceAsString(t));
		throw new Exception("FAILED == Unable to Click on Link having Xpath >> "+SearchResult_Code_link_Xpath+", Please refer ScreenShot [ "+"LinkClickFail"+fnScreenShot_Date_format() +" ]."+". Getting Exception  >> "+Throwables.getStackTraceAsString(t));}	
}






//Clicking on Search Customer Ajax link.
	public static void fnMove_To_MouseOver(String Xpath) throws Exception {
					
				try{
					
					fnGet_Element_Enabled(Xpath);
					WebElement Customer = fnGet_OR_IM_ObjectX(Xpath);
				
					Actions act = new Actions(driver);
					act.moveToElement(Customer).build().perform();
					
				}catch(NoSuchWindowException W){
					isTestCasePass = false;
						throw new Exception(W.getMessage());			}
				catch(Throwable t) {
						isTestCasePass = false;
							fnTake_Screen_Shot("SearchCustomer_Ajax");
							fnApps_Report_Logs("FAILED == Clicking on Element Failed having Xpath["+Xpath+"] , plz see screenshot [ " + "SearchCustomer_Ajax" + fnScreenShot_Date_format() + "]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(t).trim());
							throw new Exception("FAILED == Clicking on Element Failed having Xpath["+Xpath+"] , plz see screenshot [ " + "SearchCustomer_Ajax" + fnScreenShot_Date_format() + "]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(t).trim());}
}	





//Function to Execute Query in DataBase to close all Issues for specified product code(25253).
public static void fnUpdateDB_to_Close_issues() throws Throwable {
	
	fnApps_Report_Logs("=========================================================================================================================================");
	  try {
		   Class.forName("oracle.jdbc.driver.OracleDriver");
	  }catch (ClassNotFoundException e) {
		  fnApps_Report_Logs("*********************************************** Oracle JDBC Driver is not found ******************************************");
		  throw new Exception ("*********************************************** Oracle JDBC Driver is not found ******************************************");
	  }
	  fnApps_Report_Logs("*********************************************** Oracle JDBC Driver Registered *********************************************"); 
	
	 
	  Connection connection = null;
	 
	  try {
		 /* if (env.toLowerCase().equalsIgnoreCase("test")){
			  connection = DriverManager.getConnection("jdbc:oracle:thin:@dbserv13vm:1521:certtest", "testscriptuser", "test4nsf");
		  }else if(env.toLowerCase().equalsIgnoreCase("staging")){
			  connection = DriverManager.getConnection("jdbc:oracle:thin:@dbserv30:1521:CERTSTAG", "testscriptuser", "welcome1010");
		  }else{
			  throw new Exception ("FAILED == Environment variable is not defined.");
		  }*/
	  
		   connection = fnpGetDBConnection(); 
		  
		   Statement stmt= connection.createStatement();
		   
		  
			   
		   //Query1.
		   String query1="update ec_alert_transaction set status = 'I', inactive_date = sysdate where entity_id in (select to_char(esc.seq) from im_issue iss inner join im_escalation esc on iss.im_escalation_seq = esc.seq where iss.cus_product_master_seq = 98984 and esc.substatus <> 'CLOSED') and alem_seq in (select seq from ec_alert_master where entity_master_seq = 4) and status = 'A'";
		 
		   //Query2
           String query2 ="update im_escalation set status = 527, substatus = 'CLOSED' where seq in (select iss.im_escalation_seq from im_issue iss where iss.cus_product_master_seq = 98984 and iss.im_escalation_seq is not null) and substatus <> 'CLOSED'";
           
           //Query3
           String query3="update ec_alert_transaction set status = 'I', inactive_date = sysdate where entity_id in (select to_char(iss.seq) from im_issue iss where iss.cus_product_master_seq = 98984 and parent_status <> 'CLOSED') and alem_seq in (select seq from ec_alert_master where entity_master_seq = 3) and status = 'A'";

		   //Query4
           String query4="update im_issue set issue_status = 100, parent_status = 'CLOSED', ocurred_date = to_date('24-06-2010', 'DD-MM-YYYY') where seq in (select iss.seq from im_issue iss where iss.cus_product_master_seq = 98984 and parent_status <> 'CLOSED')";
           
           //Query5
           String query5="update im_issue set ocurred_date = to_date('24-06-2010', 'DD-MM-YYYY') where cus_product_master_seq = 98984 and parent_status = 'CLOSED' and ocurred_date <> to_date('24-06-2010', 'DD-MM-YYYY')";
            		 
			 
			 stmt.executeUpdate(query1);
			 connection.commit();
			 fnApps_Report_Logs("**** Query1 Executed Successfully."); 
			 
			 
			 stmt.executeUpdate(query2);
		     connection.commit();
		     fnApps_Report_Logs("**** Query2 Executed Successfully."); 
		     
		     
		     stmt.executeUpdate(query3);
			 connection.commit();
			 fnApps_Report_Logs("**** Query3 Executed Successfully."); 
			 
			 
			 stmt.executeUpdate(query4);
			 connection.commit();
			 fnApps_Report_Logs("**** Query4 Executed Successfully."); 
			 
			 
			 stmt.executeUpdate(query5);
			 connection.commit();
			 fnApps_Report_Logs("**** Query5 Executed Successfully."); 
		    
		 
		   connection.close();
		   
	   
	  }catch (SQLException e) {
		  fnApps_Report_Logs("*********************************************** Connection Failed! with Database,   Getting Error >>  "+Throwables.getStackTraceAsString(e).trim());
		  throw new Exception ("*********************************************** Connection Failed! with Database,   Getting Error >>  "+Throwables.getStackTraceAsString(e).trim());
	  }finally {
		  if( !(connection==null) ){
			  connection.close();
		  }
	  }
	  fnApps_Report_Logs("=========================================================================================================================================");
}



	
//Function to click on Object
public static void fnWait_and_Click(String XpathKey) throws Exception {
	try{
		try{
			fnGet_OR_IM_ObjectX(XpathKey).click();
			fnApps_Report_Logs("PASSED == Click done on Element having Xpath >> "+XpathKey);
		}catch(Throwable tt){
			Thread.sleep(3000);
			fnGet_OR_IM_ObjectX(XpathKey).click();
			fnApps_Report_Logs("(((((( 2nd Attampt ))))))---PASSED == Click done on Element having Xpath >> "+XpathKey);
		}
	}catch(NoSuchWindowException W){
		//isTestCasePass = false;
		throw new Exception(W.getMessage());			}
	catch(Throwable e){
		//isTestCasePass = false;
		fnTake_Screen_Shot("UnableToClick_" + XpathKey);
		fnApps_Report_Logs("FAILED == Unable To Click on Element having Xpath >> "+XpathKey+", plz see screenshot [ UnableToClick_" + XpathKey + fnScreenShot_Date_format() + " ]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(e));
		throw new Exception("FAILED == Unable To Click on element [ " + XpathKey + " ] , plz see screenshot [ UnableToClick_" + XpathKey + fnScreenShot_Date_format() + " ]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(e));}
}
	
	

	
//Function to wait for element and then type
public static void fnWait_and_Type(String XpathKey, String value) throws Exception {
	try{
		/*fnLoading_wait();
		WebDriverWait waitvar = new WebDriverWait(driver, Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
		fnGet_OR_IM_ObjectX(XpathKey);
		waitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_IM.getProperty(XpathKey))));*/
		fnGet_Element_Enabled(XpathKey);
		fnGet_OR_IM_ObjectX(XpathKey).sendKeys("");
		fnGet_OR_IM_ObjectX(XpathKey).sendKeys(value);
		fnApps_Report_Logs("PASSED == Type Value<"+value+"> on Element having Xpath  >> "+XpathKey);
	}catch(NoSuchWindowException W){
		isTestCasePass = false;
		throw new Exception(W.getMessage());			}
	catch(Throwable e){
		isTestCasePass = false;
		fnTake_Screen_Shot("UnableToType_" + XpathKey);
		fnApps_Report_Logs("FAILED == Unable To Type on Element having Xpath  >> "+XpathKey+", plz see screenshot [ UnableToType_" + XpathKey + fnScreenShot_Date_format() + " ]. Getting Exception >> "+Throwables.getStackTraceAsString(e));
		throw new Exception("FAILED == Unable To Type on element [ " + XpathKey + " ] , plz see screenshot [ UnableToType_" + XpathKey + fnScreenShot_Date_format() + " ]. Getting Exception >> "+Throwables.getStackTraceAsString(e));
		}
	}





/**/


//Function to wait for visibility of element and till enabled.
public static void fnGet_Element_Visible_and_Enabled(String XpathKey) throws Exception {
	
	try{
		
		WebDriverWait elementwaitvar = new WebDriverWait(driver,Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
		elementwaitvar.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(OR_IM.getProperty(XpathKey)))));
		
		WebDriverWait elementwaitvar1 = new WebDriverWait(driver,Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
		elementwaitvar1.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(OR_IM.getProperty(XpathKey))))).isEnabled();
						
	}catch(NoSuchWindowException W){
		isTestCasePass = false;
		throw new Exception(W.getMessage());			}
	catch(Throwable e){
		isTestCasePass = false;
		fnTake_Screen_Shot("ElementNotVisible"+XpathKey);
		fnApps_Report_Logs("FAILED == Element is not Visible having Xpath  >> "+XpathKey+", plz see screenshot [ " + "ElementNotVisible"+XpathKey + fnScreenShot_Date_format() + " ]. Getting Exception >> "+Throwables.getStackTraceAsString(e));
		throw new Exception("FAILED == Element is not Visible having Xpath  [ " + XpathKey + " ] , plz see screenshot [ " + "ElementNotVisible"+XpathKey + fnScreenShot_Date_format() + " ]. Getting Exception >> "+Throwables.getStackTraceAsString(e));}
}



	
//Function to wait for element
public static void fnGet_Element_Enabled(String XpathKey) throws Exception {
	
	try{		
		if(driver.findElements(By.xpath(OR_IM.getProperty(XpathKey))).size()>0){
			WebDriverWait elementwaitvar = new WebDriverWait(driver, Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
			elementwaitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_IM.getProperty(XpathKey))));
	
			WebDriverWait elementwaitvar1 = new WebDriverWait(driver, Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
			elementwaitvar1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_IM.getProperty(XpathKey)))).isEnabled();
			
			WebDriverWait elementwaitvar2 = new WebDriverWait(driver, Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
			elementwaitvar2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_IM.getProperty(XpathKey)))).isDisplayed();
			
			
			fnApps_Report_Logs("PASSED == Element is Visible having Xpath  >> "+XpathKey);
		}else{
			throw new Exception();
		}
	
	}catch(NoSuchWindowException W){
		isTestCasePass = false;
		throw new Exception(W.getMessage());	
	}catch(TimeoutException to){
		isTestCasePass = false;
		fnTake_Screen_Shot("ElementNoVisible_"+XpathKey);
		fnApps_Report_Logs("FAILED == Element(TimeoutException) is not Visible having Xpath  >> "+XpathKey+", plz see screenshot [ " + ""+"ElementNoVisible_"+XpathKey + fnScreenShot_Date_format() + " ]. Getting Exception >> "+to.getMessage());
		throw new Exception("FAILED == Element is not Visible having Xpath  [ " + XpathKey + " ] , plz see screenshot [ " + "ElementNoVisible_"+XpathKey + fnScreenShot_Date_format() + " ]. Getting Exception >> "+to.getMessage());		
	}catch(Throwable t){
		try{
			Thread.sleep(3000);
			WebDriverWait elementwaitvar3 = new WebDriverWait(driver, Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
			elementwaitvar3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_IM.getProperty(XpathKey)))).isEnabled();//}
			fnApps_Report_Logs("PASSED == Element is Visible  after exception enter having Xpath >> "+XpathKey);
		}catch(NoSuchWindowException W){
			isTestCasePass = false;
			throw new Exception(W.getMessage());			}
		catch(Throwable e){
			isTestCasePass = false;
			fnTake_Screen_Shot("ElementNoVisible_"+XpathKey);
			fnApps_Report_Logs("FAILED == Element(Throwable) is not Visible having Xpath  >> "+XpathKey+", plz see screenshot [ " + "ElementNoVisible_"+XpathKey + fnScreenShot_Date_format() + " ]. Getting Exception >> "+Throwables.getStackTraceAsString(e));
			throw new Exception("FAILED == Element is not Visible having Xpath  [ " + XpathKey + " ] , plz see screenshot [ " + "ElementNoVisible_"+XpathKey + fnScreenShot_Date_format() + " ]. Getting Exception >> "+Throwables.getStackTraceAsString(e));
		}
	}
}
	
	
	

//Get Excel Cell value by column name
public static String fnReturn_ExcelCellValue_ByMatchingColumnName(String SheetName, String ColumnName) throws Throwable{
	try{
		String CellValue = null;
		for(int i=4; i<50; i++){
			for(int j=1; j<14; j++){
				String ExcelCellValue = IssueMgt_Suitexls.getCellData(SheetName, j, i);
				if(ExcelCellValue.equalsIgnoreCase(ColumnName)){
					CellValue = IssueMgt_Suitexls.getCellData(SheetName, j, i+1);
					break;
				}
			}
			if(CellValue!=null){
				break;
			}
		}
		if(CellValue==null){
		throw new Exception("Column<"+ColumnName+"> Does not exists into sheet<"+SheetName+">")	;
		}
	//	fnsApps_Report_Logs("Excel Value = "+CellValue);
		return CellValue;
	}catch(Throwable t){
		isTestCasePass = false;
		fnApps_Report_Logs(Throwables.getStackTraceAsString(t));
		throw new Exception(Throwables.getStackTraceAsString(t));	}
}




public static String fnGet_Field_TEXT(String XpathKey) throws Exception {
	try{
		
		/*WebDriverWait elementwaitvar = new WebDriverWait(driver,Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
		elementwaitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_IM.getProperty(XpathKey)))).isEnabled();*/
		fnGet_Element_Enabled(XpathKey);
		String TextFetch=fnGet_OR_IM_ObjectX(XpathKey).getText();
		fnApps_Report_Logs("PASSED == Successfully Fetch the Text< "+TextFetch+" > on Element having xpath [ " +XpathKey+" ].");
		
		return TextFetch;
	
	} catch(NoSuchWindowException W){
		isTestCasePass = false;
		throw new Exception(W.getMessage());			}
	catch (Throwable t) {
			isTestCasePass = false;
			fnTake_Screen_Shot("TextFetchFail" + XpathKey);
			fnApps_Report_Logs("FAILED == Unable to Fetch Text on Element having xpath [ "+ XpathKey +",please refer screenshot >> TextFetchFail" +  fnScreenShot_Date_format() +" . Getting Exception  >> "+Throwables.getStackTraceAsString(t));
			throw new Exception("FAILED == Unable to Fetch Text on Element having xpath [ "+ XpathKey +",please refer screenshot >> TextFetchFail" +  fnScreenShot_Date_format() +" . Getting Exception  >> "+Throwables.getStackTraceAsString(t));}
	}



//Verify issue ID Generated and Color is as per Expected and returned IssueID into String
public static String fnVerified_IssueID_Generated_and_Color_Verification(Integer IssueNumberValue, String IssueIDColor) throws Exception {
	
	String[] Issue_SEQ_Name = {"First","Second","Third","Four","Five","Six","Seven","Eight"};
	int IssueSEQNumber=IssueNumberValue-1;
	String FetchIssueIdText="";
	
	for(int i=0; i<=120; i++){
		try{		
			if(driver.findElements(By.xpath(OR_IM.getProperty("issue_id"))).size()>0){
				fnGet_Element_Enabled("issue_id");
				FetchIssueIdText=fnGet_Field_TEXT("issue_id");
				break;
			}else if(driver.findElements(By.xpath(OR_IM.getProperty("AFP_IssueID_GetText"))).size()>0){
				fnGet_Element_Enabled("AFP_IssueID_GetText");
				FetchIssueIdText=fnGet_Field_TEXT("AFP_IssueID_GetText");
				break;
			}else{
				Thread.sleep(1000);
			}
		}catch(Throwable t){
			if(i==120){
				fnTake_Screen_Shot("Issue_ID_Not_Found");
				throw new Exception("FAILED == Issue id is not found, please refer the screenshot >> "+fnScreenShot_Date_format());
			}else{
				Thread.sleep(1000);
			}
		}
	}
	
	String IssueId=(FetchIssueIdText.split("\\[")[0]).trim();
	
	
	
	try{
		assert FetchIssueIdText.contains(IssueId):"FAILED == "+Issue_SEQ_Name[IssueSEQNumber]+"issue id  not Generated >> "+FetchIssueIdText+"  ,Please refer ScreenShot [ "+IssueId+fnScreenShot_Date_format()+" ].";
		fnApps_Report_Logs("PASSED ==  "+Issue_SEQ_Name[IssueSEQNumber]+" Issue ID has been generated  === "+IssueId);
		
	}
	catch(Throwable t){
		isTestCasePass = false;
		fnTake_Screen_Shot(IssueId);
		fnApps_Report_Logs(Throwables.getStackTraceAsString(t));
		throw new Exception(Throwables.getStackTraceAsString(t));}
	
	
	
	
	try{
		assert (FetchIssueIdText.contains(IssueIDColor) ):"FAILED ==  "+Issue_SEQ_Name[IssueSEQNumber]+" issue id Generated, but color is not "+IssueIDColor+" >> "+FetchIssueIdText+"  ,Please refer ScreenShot [ "+IssueId+fnScreenShot_Date_format()+" ].";
		fnApps_Report_Logs("PASSED ==  "+Issue_SEQ_Name[IssueSEQNumber]+" Issue ID has been generated and Color is  "+IssueIDColor+" === "+FetchIssueIdText);
		}
	catch(Throwable t){
		isTestCasePass = false;
		fnTake_Screen_Shot(IssueId);
		fnApps_Report_Logs(Throwables.getStackTraceAsString(t));
		throw new Exception(Throwables.getStackTraceAsString(t));}
	
	return IssueId;
	
}







//Function to Click_on button UnTill Navigate to NextPage
public static void fnClick_UnTill_Navigate_to_NextPage(String ButtonXpathKey, String ButtonPageOtherElementXpathKey, String NextPageElementXpathKey) throws Exception {
	try{
		boolean SuccessClick = false;
		for(int i=1; i<4; i++){
			if(driver.findElements(By.xpath(OR_IM.getProperty(ButtonXpathKey))).size()>0 && driver.findElements(By.xpath(OR_IM.getProperty(ButtonPageOtherElementXpathKey))).size()>0 ){
				fnGet_OR_IM_ObjectX(ButtonXpathKey).click();
				fnApps_Report_Logs("PASSED == <"+i+">Time Click done on Element having Xpath  >> "+ButtonXpathKey);
			}
			
			for(int j=0; j<10; j++){
			
				if(driver.findElements(By.xpath(OR_IM.getProperty(NextPageElementXpathKey))).size()>0){
					SuccessClick = true;
					break;
				}else if (driver.findElements(By.xpath(OR_IM.getProperty("Validation_errorMessage"))).size()>0){
					throw new Exception("Error < "+fnGet_Field_TEXT("Validation_errorMessage").trim()+" > is coming.");
				}else{
					Thread.sleep(2000);
				}
			}
			if(SuccessClick){
				break;
			}
		}
		fnApps_Report_Logs("PASSED == Successfully click on Element having xpath >> "+ButtonXpathKey);
	}catch(NoSuchWindowException W){
		isTestCasePass = false;
		throw new Exception(W.getMessage());			}
	catch(Throwable t){
		isTestCasePass = false;
		fnTake_Screen_Shot("Click_Fail_On_" + ButtonXpathKey);
		fnApps_Report_Logs("FAILED == Unable To Click on Element having Xpath >> "+ButtonXpathKey+", plz see screenshot [ Click_Fail_On_" + ButtonXpathKey + fnScreenShot_Date_format() + " ], Getting Exception >>"+Throwables.getStackTraceAsString(t));
		throw new Exception("FAILED == Unable To Click on Element having Xpath >> "+ButtonXpathKey+", plz see screenshot [ Click_Fail_On_" + ButtonXpathKey + fnScreenShot_Date_format() + " ], Getting Exception >>"+Throwables.getStackTraceAsString(t));
		}
	}












public static int fnGet_RowIndex_by_CellTextValue(String TableHeaderXpath, String TableCellHeaderXpath, String RowName, String ColumnName) throws Throwable{
	//WebDriverWait waitvar = new WebDriverWait(driver, Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
	
	boolean breakcondition=false;
	int rowindex=0;
	int i=0;
	int buttoncount=-1;
		
	try{
		for(int pagecount=1; rowindex<1; pagecount++){
			
			fnGet_Element_Enabled("Pagination_PageChangeTEXT");
		/*	waitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_IM.getProperty("Pagination_PageChangeTEXT")))).isEnabled();*/
		
			
			
			/*fnLoading_wait();
			waitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_IM.getProperty(TableHeaderXpath)))).isEnabled();*/
			fnGet_Element_Enabled(TableHeaderXpath);
			fnGet_OR_IM_ObjectX(TableHeaderXpath);
			
			WebElement stdtable=driver.findElement(By.xpath(OR_IM.getProperty(TableHeaderXpath)));
			List<WebElement> no_of_rows_in_list = stdtable.findElements(By.tagName("tr"));
		

			
			for(WebElement countrows:no_of_rows_in_list){
				
				if(breakcondition==false){
					

				if(countrows.getText().contains(RowName)){
					
					String xpathsrow=OR_IM.getProperty(TableCellHeaderXpath)+"/tr["+(i)+"]";
					
					/*fnLoading_wait();
					waitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathsrow))).isEnabled();*/
					//TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(xpathsrow);
//					fnGet_Element_Enabled(xpathsrow);
					WebElement changerowtable=driver.findElement(By.xpath(xpathsrow));
					List<WebElement> no_of_columns_in_list = changerowtable.findElements(By.tagName("td"));
					
					
						for(WebElement countcolumns:no_of_columns_in_list){
							System.out.println("countcolumns.getText()  "+countcolumns.getText());
						if(countcolumns.getText().contains(ColumnName)){
							System.out.println("countcolumns.getText  >>  "+countcolumns.getText());
								rowindex= i;
								breakcondition=true;
								break;
						} 
		
						}
						
						
				
			
					} 
				
						i++;	
						buttoncount++;
				}else{
				break;
				}
		} 
		
	
			if(rowindex==i-1){
				
				buttoncount--;
				
			}else{
			/*	waitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_IM.getProperty("Pagination_MoveToNextPage")))).isEnabled();*/
				fnGet_Element_Enabled("Pagination_MoveToNextPage");
				fnGet_OR_IM_ObjectX("Pagination_MoveToNextPage").click();
				fnsLoading_Progressingwait(1);
				fnsLoading_Progressingwait(2);
				
				for(int m=0; !driver.findElement(By.xpath(OR_IM.getProperty("Pagination_PageChangeTEXT"))).getText().contains(""+(pagecount+1)); m++){
					Thread.sleep(500);
					
					if(m>Long.parseLong(CONFIG.getProperty("ElementWaitTime"))){
						break;
					}
					
				}
				
				buttoncount--;
				i=0;
			}
		
			
			
		}
		System.out.println("buttoncount  "+buttoncount);
			return buttoncount;
	}catch(NoSuchWindowException W){
		isTestCasePass = false;
		throw new Exception(W.getMessage());			}
	catch (Throwable t) {
		isTestCasePass = false;
		fnApps_Report_Logs(Throwables.getStackTraceAsString(t).trim());
		throw new Exception(Throwables.getStackTraceAsString(t).trim());
		}
}







//Function to find and return the object using Xpath  ---- Satya Pal
public static WebElement fnGet_OR_IM_ObjectX(String XpathKey) throws Exception{
	
	
	try {
		//driver.manage().timeouts().implicitlyWait((Long.parseLong(CONFIG.getProperty("ElementWaitTime"))), TimeUnit.SECONDS);
		for (int waits=0; waits<Long.parseLong(CONFIG.getProperty("ElementWaitTime")); waits++) {
			
			if (driver.findElements(By.xpath(OR_IM.getProperty(XpathKey))).size() > 0) {break;}
			else{Thread.sleep(500);}
			
		}
		return driver.findElement(By.xpath(OR_IM.getProperty(XpathKey))); 
		
	}
	catch(StaleElementReferenceException e){
		fnApps_Report_Logs("Get-Object --- StaleElementReferenceException");
		Thread.sleep(2000);
		WebDriverWait stalewaitvar = new WebDriverWait(driver, 10);
		stalewaitvar.until(ExpectedConditions.presenceOfElementLocated(By.xpath(OR_IM.getProperty(XpathKey))));
		WebElement webelemnt=driver.findElement(By.xpath(OR_IM.getProperty(XpathKey)));
		stalewaitvar.until(ExpectedConditions.stalenessOf(webelemnt));
		return driver.findElement(By.xpath(OR_IM.getProperty(XpathKey)));}
	catch(NoSuchWindowException W){
		isTestCasePass = false;
		throw new Exception(W.getMessage());			}
	catch(NoSuchElementException e){
		isTestCasePass = false;
		fnTake_Screen_Shot("NoSuchElementException" + XpathKey);
		fnApps_Report_Logs("FAILED == Element is not found >> "+XpathKey + " ] , plz see screenshot [NoSuchElementException" + XpathKey + fnScreenShot_Date_format() + " ]");
		throw new Exception("FAILED == Element is not found >> "+XpathKey + " ] , plz see screenshot [NoSuchElementException" + XpathKey + fnScreenShot_Date_format() + " ]");}
	catch(TimeoutException e){
		isTestCasePass = false;
		fnTake_Screen_Shot("TimeOut" + XpathKey);
		fnApps_Report_Logs("FAILED == Element is not Present, getting TimeOut >> "+XpathKey + " ] , plz see screenshot [TimeOut" + XpathKey + fnScreenShot_Date_format() + " ]");
		throw new Exception("FAILED == Element is not Present, getting TimeOut >> "+XpathKey + " ] , plz see screenshot [TimeOut" + XpathKey + fnScreenShot_Date_format() + " ]");}
	catch(Throwable e){
		isTestCasePass = false;
		fnTake_Screen_Shot("NotPresent" + XpathKey);
		fnApps_Report_Logs("FAILED == Element is not found >> "+XpathKey + " ] , plz see screenshot [NotPresent" + XpathKey + fnScreenShot_Date_format() + " ]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(e));
		throw new Exception("FAILED == Element is not found >> "+XpathKey + " ] , plz see screenshot [NotPresent" + XpathKey + fnScreenShot_Date_format() + " ]. Getting Exception >> "+Throwables.getStackTraceAsString(e));}
				
}





// Return 2 days back date from past (day before Yesterday) ---- Satya Pal
public static String fnGet_2Days_Past_date() throws Exception {
		   Calendar cal = Calendar.getInstance();
		   DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		   cal.add(Calendar.DATE, -2);
		   String Past_date=dateFormat.format(cal.getTime()).trim();
		  
		   return Past_date;
	}
	



	
// Return current system time ---- Satya Pal
public static String fnGet_Current_Time() throws Exception {
		   Calendar cal = Calendar.getInstance();
		   DateFormat dateFormat = new SimpleDateFormat("HH:mm");
		   cal.add(Calendar.DATE, -2);
		   String current_time=dateFormat.format(cal.getTime()).trim();
		   
		   return current_time;
	}
	
	
	

//Count the no rows in lookup table ---- Satya Pal
public static int fnCount_table_Rows_BeforeTableRefresh(String LookuptableXpathKey) throws Exception {
	int row_count=1;
	
	try {						
		for(int i=0; i<Long.parseLong(CONFIG.getProperty("ElementWaitTime")); i++){
			Thread.sleep(1000);
			List<WebElement> table=driver.findElements(By.xpath(OR_IM.getProperty(LookuptableXpathKey)));
			//List<WebElement> no_of_rows_in_list = table.findElements(By.tagName("tr"));
			
			//int no_of_rows_count=no_of_rows_in_list.size();
			int no_of_rows_count=table.size();
			if(no_of_rows_count>=1){
				if(no_of_rows_count==1){
					Thread.sleep(2000);
				}
				row_count=no_of_rows_count;
				break;
				}
			}
		
		//System.out.println("no_of_rows_count      "+row_count+"  end");
		return row_count;
	
	}catch(NoSuchWindowException W){
		isTestCasePass = false;
		throw new Exception(W.getMessage());			}
	catch (Throwable t) {
		isTestCasePass = false;
		//fnTake_Screen_Shot("CountRowFailFrom" + LookuptableXpathKey);
	//	fnApps_Report_Logs(Throwables.getStackTraceAsString(t).trim());
		throw new Exception(Throwables.getStackTraceAsString(t).trim());
		}
}

	
//Count the no rows in lookup table ---- Satya Pal
public static int fnCount_table_Rows_AfterTableRefresh(String LookuptableXpathKey) throws Exception {
	int row_count=1;
	
		try {						
			for(int i=0; i<Long.parseLong(CONFIG.getProperty("ElementWaitTime")); i++){
				fnsLoading_Progressingwait(1);
				//Thread.sleep(1000);
				List<WebElement> table=driver.findElements(By.xpath(OR_IM.getProperty(LookuptableXpathKey)));
				//List<WebElement> no_of_rows_in_list = table.findElements(By.tagName("tr"));
				
				//int no_of_rows_count=no_of_rows_in_list.size();
				int no_of_rows_count=table.size();
				//System.out.println("Before no_of_rows_count        "+no_of_rows_count);
				if(no_of_rows_count<=5){
					
					//System.out.println("no_of_rows_count        "+no_of_rows_count);
					row_count=no_of_rows_count;
					break;
					}
				}
			
			//System.out.println("no_of_rows_count      "+row_count+"  end");
			return row_count;
		
		} catch(NoSuchWindowException W){
			isTestCasePass = false;
			throw new Exception(W.getMessage());			}
		catch (Throwable t) {
			isTestCasePass = false;
		//	fnTake_Screen_Shot("CountRowFailFrom" + LookuptableXpathKey);
		//	fnApps_Report_Logs(Throwables.getStackTraceAsString(t).trim());
			throw new Exception(Throwables.getStackTraceAsString(t).trim());
			}
	}





	



//function to select drop down value ---- Satya Pal
public static void fnDD_value_Select(String ddClickXpathKey, String ddListXpathKey, String TagName, String Value) throws Throwable {
	boolean ValueNotMatched=true;
	List<WebElement> objectlist5 = null;
	for(int i=1; i<=(2); i++){
		try{
			for(int k=1; k<=(20); k++){
				fnGet_Element_Enabled(ddClickXpathKey);
				fnWait_and_Click(ddClickXpathKey);
				//fnGet_OR_IM_ObjectX(ddClickXpathKey).click();
				fnApps_Report_Logs("PASSED == Click done on element having xpath >> "+ddClickXpathKey);
				
				objectlist5=fnGet_OR_IM_ObjectX(ddListXpathKey).findElements(By.tagName(TagName));
				if(objectlist5.size()>1){
					break;
				}else{
					fnsLoading_Progressingwait(1);
				}
				if(k==20){
					throw new Exception ("Drop down is not getting load, after 40 seconds wait.");
				}
			}
			fnApps_Report_Logs("PASSED == Count of No. of values coming in Drop down list is = "+objectlist5.size());
			for(WebElement dd_value:objectlist5){
				Thread.sleep(250);
				if(dd_value.getText().equals(Value)){
					Thread.sleep(250);
					JavascriptExecutor jse = (JavascriptExecutor) driver;
					jse = (JavascriptExecutor) driver;
					jse.executeScript("arguments[0].click();", dd_value);
					Thread.sleep(1000);
					dd_value = driver.switchTo().activeElement();
					dd_value.sendKeys(Keys.ESCAPE);
					ValueNotMatched=false;
					break;
				}
			}
			if(ValueNotMatched==true){
				throw new Exception("Excel value is not matched with DropDown Value.");
			}else{
				fnApps_Report_Logs("PASSED == Successfully select value [ "+Value+" ] from drop down, having xpath >>  " + ddClickXpathKey);
				break;
			}
			
		}catch(NoSuchWindowException W){
			isTestCasePass = false;
			throw new Exception(W.getMessage());			
		}catch(Throwable t) {
			isTestCasePass = true;
			if(i==2){
				isTestCasePass = false;
				fnTake_Screen_Shot("DdValueSelectFail" + ddClickXpathKey);
				fnApps_Report_Logs("FAILED == Unable to select value from drop down [ " + ddClickXpathKey +fnScreenShot_Date_format() +" ]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(t).trim());
				throw new Exception("FAILED == Unable to select value from drop down [ " + ddClickXpathKey +" ],plz see screenshot [ DdValueSelectFail" + ddClickXpathKey + fnScreenShot_Date_format() + " ]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(t).trim());
			}else{
				fnsLoading_Progressingwait(1);
			}
		}	
	}
	
	
	
}
	







//function to select drop down value ---- Satya Pal
public static void fnDD_value_Select_By_DownKey(String ddClickXpathKey, String ddListXpathKey, String TagName, String Value) throws Throwable {
	boolean ValueNotMatched=true;
	List<WebElement> objectlist5 = null;
	for(int i=1; i<=(2); i++){
		try{
			for(int k=1; k<=(20); k++){
				fnGet_Element_Enabled(ddClickXpathKey);
				fnWait_and_Click(ddClickXpathKey);
				
				objectlist5=fnGet_OR_IM_ObjectX(ddListXpathKey).findElements(By.tagName(TagName));
				if(objectlist5.size()>1){
					break;
				}else{
					fnsLoading_Progressingwait(1);
				}
				if(k==20){
					throw new Exception ("Drop down is not getting load, after 40 seconds wait.");
				}
			}
			fnApps_Report_Logs("PASSED == Count of No. of values coming in Drop down list is = "+objectlist5.size());
			for(WebElement dd_value:objectlist5){
				Thread.sleep(200);
				Actions act = new Actions(driver);
				act.sendKeys(Keys.ARROW_DOWN).build().perform();
				//act.moveToElement(dd_value).sendKeys(Keys.ARROW_DOWN).build().perform();
				Thread.sleep(200);
				if(dd_value.getText().equals(Value)){
					Actions act1 = new Actions(driver);
					Thread.sleep(200);
					act1.moveToElement(dd_value).click().build().perform();
					ValueNotMatched=false;
					break;
				}
			}
			if(ValueNotMatched==true){
				throw new Exception("Excel value is not matched with DropDown Value.");
			}else{
				fnApps_Report_Logs("PASSED == Successfully select value [ "+Value+" ] from drop down, having xpath >>  " + ddClickXpathKey);
				break;
			}
		}catch(NoSuchWindowException W){
			isTestCasePass = false;
				throw new Exception(W.getMessage());			
		}catch(Throwable t) {
			if(i==2){
				isTestCasePass = false;
				fnTake_Screen_Shot("DdValueSelectFail" + ddClickXpathKey);
				fnApps_Report_Logs("FAILED == Unable to select value from drop down [ " + ddClickXpathKey +fnScreenShot_Date_format() +" ]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(t).trim());
				throw new Exception("FAILED == Unable to select value from drop down [ " + ddClickXpathKey +" ],plz see screenshot [ DdValueSelectFail" + ddClickXpathKey + fnScreenShot_Date_format() + " ]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(t).trim());
			}else{
				fnsLoading_Progressingwait(1);
			}
		}	
	}
}



//function to select drop down value ---- Satya Pal
public static void fnDD_value_Select_By_UpKey(String ddClickXpathKey, String ddListXpathKey, String TagName, String Value) throws Exception {
	boolean ValueNotMatched=true;
	try{
			fnGet_Element_Enabled(ddClickXpathKey);
			fnWait_and_Click(ddClickXpathKey);
						
			fnGet_Element_Enabled(ddListXpathKey);
			List<WebElement> objectlist5=fnGet_OR_IM_ObjectX(ddListXpathKey).findElements(By.tagName(TagName));
		
				for(WebElement dd_value:objectlist5){
					Actions act = new Actions(driver);
					act.sendKeys(Keys.ARROW_UP).sendKeys(Keys.ARROW_UP).build().perform();
					//act.moveToElement(dd_value).sendKeys(Keys.ARROW_UP).sendKeys(Keys.ARROW_UP).build().perform();
					if(dd_value.getText().equals(Value)){
						fnWait(1000);
						dd_value.click();
						ValueNotMatched=false;
						break;}
				}
				
				if(ValueNotMatched==true){
					throw new Exception("Excel value is not matched with DropDown Value.");
					}	
				
			fnApps_Report_Logs("PASSED == Successfully select value [ "+Value+" ] from drop down, having xpath >>  " + ddClickXpathKey);
	}catch(NoSuchWindowException W){
		isTestCasePass = false;
			throw new Exception(W.getMessage());			}
	catch(Throwable t) {
			isTestCasePass = false;
			fnTake_Screen_Shot("DdValueSelectFail" + ddClickXpathKey);
			fnApps_Report_Logs("FAILED == Unable to select value from drop down [ " + ddClickXpathKey +fnScreenShot_Date_format() +" ]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(t).trim());
			throw new Exception("FAILED == Unable to select value from drop down [ " + ddClickXpathKey +" ],plz see screenshot [ DdValueSelectFail" + ddClickXpathKey + fnScreenShot_Date_format() + " ]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(t).trim());
			}
	}
	





//function to select drop down value ---- Satya Pal
public static void fnIEBrowser_Relaunched() throws Exception {

	try{
		
		driver.quit();
		//WindowsUtils.tryToKillByName("IEDriverServer.exe");
		//Thread.sleep(3000);
		
		fnLaunchBrowserAndLogin();
	
		
	}catch(NoSuchWindowException W){
		isTestCasePass = false;
		throw new Exception(W.getMessage());			}
	catch(Throwable t) {
			isTestCasePass = false;
			fnTake_Screen_Shot("LoginFailed");
			fnApps_Report_Logs("FAILED == Unable to Login, Please refer Screen shot [ " + "LoginFailed" +fnScreenShot_Date_format() +" ]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(t).trim());
			throw new Exception("FAILED == Unable to Login, Please refer Screen shot [ " + "LoginFailed" +fnScreenShot_Date_format() +" ]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(t).trim());
			}
	}



//Clicking on Menu Ajax Link	
	public static void fncMenu_Ajax_Link_Click_By_PassingAjaxPath(String AjaxLinkXpath) throws Exception {
		try{
			fnGet_Element_Enabled("Menu");
			WebElement Menu_Element = fnGet_OR_IM_ObjectX("Menu");
			
			
			//New line added to run script in chrome.
			WebElement VersionLogoImage = fnGet_OR_IM_ObjectX("VersionLogoImage");
			VersionLogoImage.click();
			Thread.sleep(500);
			
			Actions action1 = new Actions(driver);
			action1.moveToElement(Menu_Element).build().perform();
					
			Thread.sleep(500);
			Actions action2 = new Actions(driver);
			fnGet_Element_Enabled(AjaxLinkXpath);
			WebElement CreateContractorApplicant = fnGet_OR_IM_ObjectX(AjaxLinkXpath);
			action2.moveToElement(CreateContractorApplicant).click().build().perform();
			fnsLoading_Progressingwait(3);
		}catch(NoSuchWindowException W){
			isTestCasePass = false;
			throw new Exception(W.getMessage());			}
		catch(Throwable t) {
			isTestCasePass = false;
			fnTake_Screen_Shot(AjaxLinkXpath+"_Fail");
			fnApps_Report_Logs("FAILED == Clicking on <"+(AjaxLinkXpath)+"> Failed, plz see screenshot [" +AjaxLinkXpath+"_Fail" + fnScreenShot_Date_format() + "]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(t).trim());
			throw new Exception("FAILED == Clicking on <"+(AjaxLinkXpath)+"> Failed, plz see screenshot [" + AjaxLinkXpath+"_Fail" + fnScreenShot_Date_format() + "]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(t).trim());}
}	
	



	
//Clicking on Create Issue Ajax link.
public static void fnCreateIssue_Ajax_Link_Click() throws Exception {
	try{
		
		fncMenu_Ajax_Link_Click_By_PassingAjaxPath("Create_Issue");
		/*// Taking mouse over to menu
		fnLoading_wait();
		WebElement menu1 = fnGet_OR_IM_ObjectX("Menu");
		WebElement issue_management1=fnGet_OR_IM_ObjectX("Issue_Management");
		WebElement create_issue1=fnGet_OR_IM_ObjectX("Create_Issue");
		
		Actions act = new Actions(driver);
		act.moveToElement(menu1).moveToElement(issue_management1).moveToElement(create_issue1).click().build().perform();
		
		
	 	fnGet_Element_Enabled("Menu");
		WebElement menu1 = fnGet_OR_IM_ObjectX("Menu");
		Actions act = new Actions(driver);
		act.moveToElement(menu1).build().perform();
		Thread.sleep(500);
		
				
		// Taking mouse over to Issue Management
		fnGet_Element_Enabled("Issue_Management");
		WebElement issue_management1=fnGet_OR_IM_ObjectX("Issue_Management");
		act.moveToElement(issue_management1).build().perform();
					
			
		// Taking mouse over to Create Issue
		fnGet_Element_Enabled("Create_Issue");
		WebElement create_issue1=fnGet_OR_IM_ObjectX("Create_Issue");
		act.moveToElement(create_issue1).click().build().perform();*/
		fnApps_Report_Logs("PASSED == Successfully click on 'Create Issue' Ajax link");
		
	}catch(NoSuchWindowException W){
		isTestCasePass = false;
		throw new Exception(W.getMessage());			}
	catch(Throwable t) {
		isTestCasePass = false;
		throw new Exception(Throwables.getStackTraceAsString(t).trim());
	}
}	
		





//Clicking on Search Issue Ajax link.
public static void fnSearchIssue_Ajax_Link_Click() throws Exception {
	try{
		fncMenu_Ajax_Link_Click_By_PassingAjaxPath("Search_Issues");
		/*fnGet_Element_Enabled("Menu");
		WebElement menu1 = fnGet_OR_IM_ObjectX("Menu"); 
		Thread.sleep(500);
		fnGet_Element_Enabled("Issue_Management");
		WebElement issue_management1=fnGet_OR_IM_ObjectX("Issue_Management");	
		fnGet_Element_Enabled("Create_Issue");
		WebElement CreateIssue=fnGet_OR_IM_ObjectX("Create_Issue");
		fnGet_Element_Enabled("Search_Issues");
		WebElement SearchIssue=fnGet_OR_IM_ObjectX("Search_Issues");
		Actions act = new Actions(driver);
		act.moveToElement(menu1).moveToElement(issue_management1).moveToElement(CreateIssue).moveToElement(SearchIssue).click().build().perform();*/
		fnApps_Report_Logs("PASSED == Successfully click on Search Issues Ajax link.")	;
	}catch(NoSuchWindowException W){
		isTestCasePass = false;
		throw new Exception(W.getMessage());			}
	catch(Throwable t) {
		isTestCasePass = false;
		fnTake_Screen_Shot("SearchIssuesClickFail");
		fnApps_Report_Logs("FAILED == 'Search Issue' Ajax Link click Failed, plz see screenshot >> SearchIssuesClickFail" + fnScreenShot_Date_format() + " , Getting Exception  >> "+Throwables.getStackTraceAsString(t).trim());
		throw new Exception("FAILED == 'Search Issue' Ajax Link click Failed, plz see screenshot >> SearchIssuesClickFail" + fnScreenShot_Date_format() + " , Getting Exception  >> "+Throwables.getStackTraceAsString(t).trim());
	}
}	
			



//Clicking on Search Issue Ajax link.
public static void fnCreateInvoice_Ajax_Link_Click() throws Exception {
				
			try{
				fncMenu_Ajax_Link_Click_By_PassingAjaxPath("Create_Invoices");
				/*fnGet_Element_Enabled("Menu");
				WebElement menu1 = fnGet_OR_IM_ObjectX("Menu"); 
				Thread.sleep(500);
				fnGet_Element_Enabled("Issue_Management");
				WebElement IssueManagement=fnGet_OR_IM_ObjectX("Issue_Management");	
				WebElement CreateIssue=fnGet_OR_IM_ObjectX("Create_Issue");
				WebElement CreateInvoice=fnGet_OR_IM_ObjectX("Create_Invoices");
				Actions act = new Actions(driver);
				act.moveToElement(menu1).moveToElement(IssueManagement).moveToElement(CreateIssue).moveToElement(CreateInvoice).click().build().perform();*/
				fnApps_Report_Logs("PASSED == Successfully click on Create Invoices Ajax link.")	;	
			}catch(NoSuchWindowException W){
				isTestCasePass = false;
				throw new Exception(W.getMessage());			}
			catch(Throwable t) {
					isTestCasePass = false;
						fnTake_Screen_Shot("Create_Invoices");
						fnApps_Report_Logs("FAILED == Clicking on Search Issue Ajex Link Failed, plz see screenshot [ " + "Create_Invoices" + fnScreenShot_Date_format() + "]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(t).trim());
						throw new Exception("FAILED == Clicking on Search Issue Ajex Link Failed , plz see screenshot [ " + "Create_Invoices" + fnScreenShot_Date_format() + "]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(t).trim());
			}
	}	
		


//Clicking on Search_escalation Ajax link.
public static void fnSearchEscalation_Ajax_Link_Click() throws Exception {
			try{			
				fncMenu_Ajax_Link_Click_By_PassingAjaxPath("Search_Escalations");
				/*fnGet_Element_Enabled("Menu");
				WebElement menu1 = fnGet_OR_IM_ObjectX("Menu"); 
				Thread.sleep(500);
				fnGet_Element_Enabled("Issue_Management");
				WebElement issue_management1=fnGet_OR_IM_ObjectX("Issue_Management");	
				WebElement create_issue1=fnGet_OR_IM_ObjectX("Create_Issue");
				WebElement Search_escalation=fnGet_OR_IM_ObjectX("Search_Escalations");
				Actions act = new Actions(driver);
				act.moveToElement(menu1).moveToElement(issue_management1).moveToElement(create_issue1).moveToElement(Search_escalation).click().build().perform();*/
				fnApps_Report_Logs("PASSED == Successfully click on Search Escalations Ajax link.")	;	
				
			}catch(NoSuchWindowException W){
				isTestCasePass = false;
					throw new Exception(W.getMessage());			}
			catch(Throwable t) {
					isTestCasePass = false;
						fnTake_Screen_Shot("Search_Escalations");
						fnApps_Report_Logs("FAILED == Clicking on search_escalation Ajex Link Failed, plz see screenshot [ " + "Search_Escalations" + fnScreenShot_Date_format() + "]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(t).trim());
						throw new Exception("FAILED == Clicking on search_escalation Ajex Link Failed , plz see screenshot [ " + "Search_Escalations" + fnScreenShot_Date_format() + "]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(t).trim());
				}
		}	
			



//Clicking on Client Charges Configuration Ajax link.
public static void fnClientChargesConfiguration_Ajax_Link_Click() throws Exception {
			try{		
				fncMenu_Ajax_Link_Click_By_PassingAjaxPath("Client_Charges_Configuration");
				/*fnGet_Element_Enabled("Menu");
				WebElement menu1 = fnGet_OR_IM_ObjectX("Menu"); 
				Thread.sleep(500);
				fnGet_Element_Enabled("Issue_Management");
				WebElement issue_management1=fnGet_OR_IM_ObjectX("Issue_Management");	
				WebElement create_issue1=fnGet_OR_IM_ObjectX("Create_Issue");
				WebElement Client_Charges_Config=fnGet_OR_IM_ObjectX("Client_Charges_Configuration");
				Actions act = new Actions(driver);
				act.moveToElement(menu1).moveToElement(issue_management1).moveToElement(create_issue1).moveToElement(Client_Charges_Config).click().build().perform();*/
				fnApps_Report_Logs("PASSED == Successfully click on Client_Charges_Configuration Ajax link.")	;	
				
			}catch(NoSuchWindowException W){
				isTestCasePass = false;
					throw new Exception(W.getMessage());			}
			catch(Throwable t) {
					isTestCasePass = false;
						fnTake_Screen_Shot("Client_Charges_Configuration");
						fnApps_Report_Logs("FAILED == Clicking on Client_Charges_Configuration Ajex Link Failed , plz see screenshot [ " + "Client_Charges_Configuration" + fnScreenShot_Date_format() + "]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(t).trim());
						throw new Exception("FAILED == Clicking on Client_Charges_Configuration Ajex Link Failed , plz see screenshot [ " + "Client_Charges_Configuration" + fnScreenShot_Date_format() + "]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(t).trim());
				}
		}	
			


	
//Update Site Details page and verify navigate to Summary details page.
public static void fnVerify_SiteDetailsPage_Data_Entered_and_NavigateTo_SummaryDetailsPage(String Client_lookup_Search_value, String Site_lookup_Search_value) throws Exception {
							
		//	IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//src//nsf//ecap//xls//IssueMgt_Suite.xlsx");
		//	WebDriverWait waitvar = new WebDriverWait(driver, Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
							
		try{
			//fnGet_Element_Enabled("footer");
		
			//Clicking on Site details Client Button
			fnGet_Element_Enabled("CreateIssue_SiteDetails_client_name_bttn");
			//waitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_IM.getProperty("CreateIssue_SiteDetails_client_name_bttn"))));
			fnWait_and_Click("CreateIssue_SiteDetails_client_name_bttn");
			fnsLoading_Progressingwait(1);
										
			// Enter client name into search filed on client lookup
			fnGet_Element_Enabled("CreateIssue_SiteDetails_client_lookup_value_text_field");
			
			fnWait_and_Type("CreateIssue_SiteDetails_client_lookup_value_text_field", Client_lookup_Search_value);
											
			// Selecting first radio button from search result table
			/*fnLoading_wait();*/
			fnsLookup_RadioBttn_Select(); 
						
			//Clicking on Site details Site Button
			fnGet_Element_Enabled("CreateIssue_SiteDetails_site_name_bttn");
			fnWait_and_Click("CreateIssue_SiteDetails_site_name_bttn");
			fnsLoading_Progressingwait(1);
			// Enter Site name into search filed on Site lookup
			fnGet_Element_Enabled("CreateIssue_SiteDetails_site_lookup_value_text_field");
			fnWait_and_Type("CreateIssue_SiteDetails_site_lookup_value_text_field", Site_lookup_Search_value);
											
			// Selecting first radio button from search result table
			/*fnLoading_wait();*/
			fnsLookup_RadioBttn_Select();
			
			fnWait(1500);
			//Clicking on NEXT Button
			fnClick_UnTill_Navigate_to_NextPage("CreateIssue_SiteDetails_site_details_next_bttn","CreateIssue_SiteDetails_site_name_bttn","CreateIssue_SummaryDetails_date_time_issue_date");
			
		}catch(NoSuchWindowException W){
			isTestCasePass = false;
			throw new Exception(W.getMessage());			}
		catch(Throwable t) {
			isTestCasePass = false;
			throw new Exception(Throwables.getStackTraceAsString(t).trim());
		}
	}	
		
				



		
	//Enter data in all the mandatory field and click on next button.
	public static void fnVerify_SummaryDetailsPage_Data_Entered_and_NavigateTo_CustomerDetailsPage(String how_recieved_DD_value, String issue_type_DD_value, String issue_sub_type_DD_value, String Calander_date_picker, String TitleShortDescription) throws Exception {
	//	IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//src//nsf//ecap//xls//IssueMgt_Suite.xlsx");
		try{
					
			//Entered issue create date
			fnGet_Element_Enabled("CreateIssue_SummaryDetails_date_time_issue_date");
			fnWait_and_Type("CreateIssue_SummaryDetails_date_time_issue_date", Calander_date_picker);
			Thread.sleep(1500);			
			//Entered issue create Time
			fnGet_Element_Enabled("CreateIssue_SummaryDetails_date_time_issue_time");
			fnWait_and_Type("CreateIssue_SummaryDetails_date_time_issue_time", fnGet_Current_Time());
			Thread.sleep(1500);				
			//Entered Reported date
			fnGet_Element_Enabled("CreateIssue_SummaryDetails_date_time_reported_date");
			fnWait_and_Type("CreateIssue_SummaryDetails_date_time_reported_date", Calander_date_picker);
			Thread.sleep(1500);			
			//Entered Reported Time
			fnGet_Element_Enabled("CreateIssue_SummaryDetails_date_time_reported_time");
			fnWait_and_Type("CreateIssue_SummaryDetails_date_time_reported_time", fnGet_Current_Time());
							
			Thread.sleep(1500);
			fnGet_Element_Enabled("CreateIssue_SummaryDetails_SiteContactName");
			fnWait_and_Type("CreateIssue_SummaryDetails_SiteContactName", "Testscriptuser");
			Thread.sleep(1500);
			//**************** Select value from How Received Drop Down
			//fnDD_value_Select("CreateIssue_SummaryDetails_how_recieved_dd_click", "CreateIssue_SummaryDetails_how_recieved_dd_value", "li", how_recieved_DD_value);
			
			Thread.sleep(1500);
			//**************** Select value from Issue Type Drop Down
			fnDD_value_Select("CreateIssue_SummaryDetails_issue_type_dd_click", "CreateIssue_SummaryDetails_issue_type_dd_value", "li", issue_type_DD_value);
			
			Thread.sleep(1500);	
			//**************** Select value from Issue Sub Type Drop Down
			fnDD_value_Select_By_DownKey("CreateIssue_SummaryDetails_issue_sub_type_dd_click", "CreateIssue_SummaryDetails_issue_sub_type_dd_value", "li", issue_sub_type_DD_value);
			//fnpPFList("CreateIssue_SummaryDetails_issue_sub_type_dd_click", "CreateIssue_SummaryDetails_issue_sub_type_dd_value",  issue_sub_type_DD_value);
			Thread.sleep(1500);
			//Enter free TEXT date into TilteShortDescription Field	
			fnGet_Element_Enabled("CreateIssue_SummaryDetails_title_short_description");
			fnWait_and_Type("CreateIssue_SummaryDetails_title_short_description", TitleShortDescription+fnIssueCreationText_Date_format());
			Thread.sleep(1500);
			//**************** Select value from How Received Drop Down
			fnDD_value_Select("CreateIssue_SummaryDetails_how_recieved_dd_click", "CreateIssue_SummaryDetails_how_recieved_dd_value", "li", how_recieved_DD_value);
			
			//Clicking on Next Button
			fnClick_UnTill_Navigate_to_NextPage("CreateIssue_SummaryDetails_summary_next_bttn","CreateIssue_SummaryDetails_date_time_issue_date","CreateIssue_Customer_Name_TextField");
		
			fnsLoading_Progressingwait(1);
			
			fnWait_and_Click("CreateIssue_detailsTab_Have_You_Obtained_Consent_Radio_Yes_button");
			fnsLoading_Progressingwait(1);
			
			fnWait_and_Click("CreateIssue_detailsTab_In_Case_Of_A_Minor_Radio_Yes_button");
			fnsLoading_Progressingwait(1);
			
		}catch(Throwable t) {
			isTestCasePass = false;
			fnApps_Report_Logs(Throwables.getStackTraceAsString(t).trim());
			throw new Exception(Throwables.getStackTraceAsString(t).trim());
		}
	}	
		





//Select Product from ProductLookUp Screen and Enter data in all the mandatory field and click on next button.
public static void fnVerify_ProductDetailsPage_Data_Entered_and_NavigateTo_DocumentsDetailsPage(String product_lookup_Search_value) throws Exception {
					
				//	IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//src//nsf//ecap//xls//IssueMgt_Suite.xlsx");
				//	WebDriverWait waitvar = new WebDriverWait(driver, Long.parseLong(CONFIG.getProperty("ElementWaitTime")));				
					try{

						// Click on ProductCode button
						fnGet_Element_Enabled("CreateIssue_product_code_bttn");
						/*waitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_IM.getProperty("CreateIssue_product_code_bttn")))).isEnabled();*/
						fnWait_and_Click("CreateIssue_product_code_bttn");
												
						//Enter value in TEXT field on ProdultLookUp Screen to search for Product	
						fnGet_Element_Enabled("CreateIssue_product_lookup_value");
						fnWait_and_Type("CreateIssue_product_lookup_value", product_lookup_Search_value);
											
						// Selecting first radio button from search result table
					/*	fnLoading_wait();*/
						fnsLookup_RadioBttn_Select();
					/*	
						//Entered quantity in ProductQuantity TEXTBox
						fnWait_and_Type("CreateIssue_product_quantity", IssueMgt_Suitexls.getCellData("CreateIssue_PRQ", 2, 13));
									
						//Select Value from ProductUnitDD
						fnDD_value_Select("CreateIssue_product_unit_dd_click", "CreateIssue_product_unit_dd_value_select", "li", IssueMgt_Suitexls.getCellData("CreateIssue_PRQ", 2, 14));
						*/
						fnWait(1500);
						//Clicking on next button on product page
						fnClick_UnTill_Navigate_to_NextPage("CreateIssue_product_next_bttn","CreateIssue_product_code_bttn","CreateIssue_document_Add_Attachement");
						
						//Tried clicking on NEXT  Button
						/*//for(int i=0; i<5; i++){
							if(driver.findElements(By.xpath(OR_IM.getProperty("CreateIssue_product_next_bttn"))).size()>0 && driver.findElements(By.xpath(OR_IM.getProperty("CreateIssue_product_code_bttn"))).size()>0 ){
								fnWait_and_Click("CreateIssue_product_next_bttn");
							//Thread.sleep(1000);}
							if(driver.findElements(By.xpath(OR_IM.getProperty("CreateIssue_document_Add_Attachement"))).size()>0){break;}
						}*/
								
				
					}catch(NoSuchWindowException W){
						isTestCasePass = false;
						throw new Exception(W.getMessage());			}
					catch(Throwable t) {
						isTestCasePass = false;
							fnTake_Screen_Shot("UnableToNavigateDocumentsDetailsPage");
							fnApps_Report_Logs("FAILED == ProductDetailsPage update failed and unable to Navigate DocumentsDetailsPage, plz see screenshot [UnableToNavigateDocumentsDetailsPage" + fnScreenShot_Date_format() + "]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(t).trim());
							throw new Exception("FAILED == ProductDetailsPage update failed and unable to Navigate DocumentsDetailsPage, plz see screenshot [UnableToNavigateDocumentsDetailsPage" + fnScreenShot_Date_format() + "]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(t).trim());
					}
		}	
	
	





	
//ADMIN ALERT Verification Function ---- Issue Management Alert verification re-direct from summary details after updating details. ---- Satya Pal
public static void fnVerify_ADMIN_Alert_redirect_from_summarydetailsPage(String AlertLinkTextClickXpathKey, String AlertIssueFilterXpathKey, String IssueIDvalue) throws Exception {
		
		//	WebDriverWait waitvar = new WebDriverWait(driver, Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
			//IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//src//nsf//ecap//xls//IssueMgt_Suite.xlsx");
			
			//Clicking on HOME link
			fnGet_Element_Enabled("Home_topLink");
			fnWait_and_Click("Home_topLink");
			
			//Waiting for Footer till page load
		/*	fnLoading_wait();*/
			fnGet_Element_Enabled("footer");
			Thread.sleep(1000);
			fnGet_Element_Enabled("footer");
			fnMove_To_MouseOver("footer");
			//waitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_IM.getProperty("footer")))).isEnabled();
			//fnGet_OR_IM_ObjectX("footer");
			
			
			//Splitting AlertLinkTextClickXpathKey TEXT from Xpaths
			String Alert_Link_Text=(OR_IM.getProperty(AlertLinkTextClickXpathKey).split("\\'")[1]).trim();
			
			
			
			//
			try{
				/*fnLoading_wait();
				fnWait(1000);*/
				if(driver.getPageSource().contains(Alert_Link_Text)){
					
					/*fnLoading_wait();*/
					fnGet_Element_Enabled(AlertLinkTextClickXpathKey);
				//	waitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_IM.getProperty(AlertLinkTextClickXpathKey)))).isEnabled();
					fnWait_and_Click(AlertLinkTextClickXpathKey);
					fnsLoading_Progressingwait(1);
					fnsLoading_Progressingwait(2);
					
					for(int i=0; i<3; i++){
					
						if(driver.findElements(By.xpath(OR_IM.getProperty(AlertIssueFilterXpathKey))).size()>0){
							fnGet_Element_Enabled(AlertIssueFilterXpathKey);
							fnWait_and_Type(AlertIssueFilterXpathKey, IssueIDvalue);
							fnsLoading_Progressingwait(1);
							break;
						}
						else{
						/*fnLoading_wait();*/
						fnGet_Element_Enabled(AlertLinkTextClickXpathKey);
						//waitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_IM.getProperty(AlertLinkTextClickXpathKey)))).isEnabled();
						fnWait_and_Click(AlertLinkTextClickXpathKey);
						fnsLoading_Progressingwait(2);
						}
					}	
					
				}
				else{
					//fnWait_and_Click("Home_topLink");
					fnApps_Report_Logs("PASSED == Alert Link Text >> [ "+Alert_Link_Text+" ]  is not present, as there is no IDs for Alert"+" for issue id > "+IssueIDvalue);
					}
			}catch(NoSuchWindowException W){
				isTestCasePass = false;
				throw new Exception(W.getMessage());			}
			
			catch(Throwable t) {
				isTestCasePass = false;
						fnTake_Screen_Shot("AlertClickinFailFrom" + AlertLinkTextClickXpathKey);
						fnApps_Report_Logs("FAILED == Unable to click on Alert link [" +AlertLinkTextClickXpathKey+ "],  plz see screenshot [AlertClickinFailFrom" + AlertLinkTextClickXpathKey + fnScreenShot_Date_format() + "]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(t).trim());
						throw new Exception("FAILED == Unable to click on Alert link [" +AlertLinkTextClickXpathKey+ "],  plz see screenshot [AlertClickinFailFrom" + AlertLinkTextClickXpathKey + fnScreenShot_Date_format() + "]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(t).trim());	}
	}
			
	






	
//TECH ALERT Verification Function ---- Issue Management TECH Alert verification by Filtering user through specific user radio button. ---- Satya Pal
public static void fnVerify_TECH_Alert_redirect_from_AlertPage_by_SpecificUserRadioBttn(String AlertLinkTextClickXpathKey, String AlertIssueFilterXpathKey, String IssueIDvalue) throws Exception {
		
		//	WebDriverWait waitvar = new WebDriverWait(driver, Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
			
		try{
			/*fnLoading_wait();*/
			fnGet_Element_Enabled("Home_topLink");
			//waitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_IM.getProperty("Home_topLink")))).isEnabled();
			fnWait_and_Click("Home_topLink");	
			
			
			//Waiting for Footer till page load
			/*fnLoading_wait();*/
			fnGet_Element_Enabled("footer");
			Thread.sleep(1000);
			//waitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_IM.getProperty("footer")))).isEnabled();
			
			/*fnLoading_wait();*/
			fnGet_Element_Enabled("specific_user_radio_button");
			Thread.sleep(1000);
			//waitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_IM.getProperty("specific_user_radio_button")))).isEnabled();
			fnGet_OR_IM_ObjectX("specific_user_radio_button").click();
			
			
			
			/*fnLoading_wait();*/
			fnGet_Element_Enabled("alert_lookup_bttn");
			Thread.sleep(250);
			//waitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_IM.getProperty("alert_lookup_bttn")))).isEnabled();
			fnWait_and_Click("alert_lookup_bttn");
			fnsLoading_Progressingwait(2);
			
			fnGet_Element_Enabled("alert_lookup_text_value_field");
			fnWait_and_Type("alert_lookup_text_value_field", First_TechnologistName_From_DD);
				
			/*fnLoading_wait();*/
			fnsLookup_RadioBttn_Select(); // Selecting first radio button from lookup table
			
			fnGet_Element_Enabled("alert_showsAlerts");
			fnsLoading_Progressingwait(1);
			//Thread.sleep(1000);  // Not working if loading coming
			fnWait_and_Click("alert_showsAlerts");	
			fnsLoading_Progressingwait(2);
			
			//Waiting for Footer till page load
			/*fnLoading_wait();*/
			fnGet_Element_Enabled("footer");
			Thread.sleep(1000);
			fnGet_Element_Enabled("footer");
			fnMove_To_MouseOver("footer");
			//waitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_IM.getProperty("footer")))).isEnabled();
						
		}catch(NoSuchWindowException W){
			isTestCasePass = false;
			throw new Exception(W.getMessage());			}
		catch(Throwable t) {
				isTestCasePass = false;
				fnTake_Screen_Shot("UserSelectFailedFromLU");
				fnApps_Report_Logs("FAILED == Unable to select user from look Up, Please refer screen shot [ "+"UserSelectFailedFromLU"+fnScreenShot_Date_format()+ " ]."+". Getting Exception  >> "+Throwables.getStackTraceAsString(t).trim());
				throw new Exception("FAILED == Unable to select user from look Up, Please refer screen shot [ "+"UserSelectFailedFromLU"+ fnScreenShot_Date_format()+" ]."+". Getting Exception  >> "+Throwables.getStackTraceAsString(t).trim());}
			
		
		
		
		//Splitting AlertLinkTextClickXpathKey TEXT from Xpaths
		String Alert_Link_Text=(OR_IM.getProperty(AlertLinkTextClickXpathKey).split("\\'")[1]).trim();
			
		try{
				/*fnLoading_wait();
				fnWait(1000);*/
				if(driver.getPageSource().contains(Alert_Link_Text)){
					
					/*fnLoading_wait();*/
					fnGet_Element_Enabled(AlertLinkTextClickXpathKey);
					//waitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_IM.getProperty(AlertLinkTextClickXpathKey)))).isEnabled();
					fnWait_and_Click(AlertLinkTextClickXpathKey);
					fnsLoading_Progressingwait(2);
					
					for(int i=0; i<3; i++){
					
						if(driver.findElements(By.xpath(OR_IM.getProperty(AlertIssueFilterXpathKey))).size()>0){
							fnGet_Element_Enabled(AlertIssueFilterXpathKey);
							fnWait_and_Type(AlertIssueFilterXpathKey, IssueIDvalue);
							break;
						}
						else{
						/*fnLoading_wait();*/
						fnGet_Element_Enabled(AlertLinkTextClickXpathKey);
						//waitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_IM.getProperty(AlertLinkTextClickXpathKey)))).isEnabled();
						fnWait_and_Click(AlertLinkTextClickXpathKey);
						fnsLoading_Progressingwait(2);
						}
					}	
					
				}
				else{
					//fnWait_and_Click("alert_showsAlerts");
					fnApps_Report_Logs("PASSED == Alert Link Text >> [ "+Alert_Link_Text+" ]  is not present, as there is no IDs for Alert"+" for issue id > "+IssueIDvalue);
					}
		}catch(NoSuchWindowException W){
			isTestCasePass = false;
			throw new Exception(W.getMessage());			}
		catch(Throwable t) {
				isTestCasePass = false;
				fnTake_Screen_Shot("AlertClickinFailFrom" + AlertLinkTextClickXpathKey);
				fnApps_Report_Logs("FAILED == Unable to click on Alert link [ " +AlertLinkTextClickXpathKey+ " ]"+" for issue id > "+IssueIDvalue+",  plz see screenshot [AlertClickinFailFrom" + AlertLinkTextClickXpathKey + fnScreenShot_Date_format() + "]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(t).trim());
				throw new Exception("FAILED == Unable to click on Alert link [" +AlertLinkTextClickXpathKey+ "],  plz see screenshot [AlertClickinFailFrom" + AlertLinkTextClickXpathKey + fnScreenShot_Date_format() + "]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(t).trim());		}
	}
			
	





//TECH ALERT Verification Function ---- Issue Management Verify TECH Alert by clicking Show Alert Button from TECH Alert Page. ---- Satya Pal
public static void fnVerify_TECH_Alert_by_clicking_ShowAlertBttn_from_TECHAlertPage(String AlertLinkTextClickXpathKey, String AlertIssueFilterXpathKey, String IssueIDvalue) throws Throwable {
				
		//	WebDriverWait waitvar = new WebDriverWait(driver, Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
				fnGet_Element_Enabled("alert_showsAlerts");
				fnsLoading_Progressingwait(1);
				fnWait_and_Click("alert_showsAlerts");
				fnsLoading_Progressingwait(2);
				
				//Waiting for Footer till page load
				/*fnLoading_wait();*/
				fnGet_Element_Enabled("footer");
				Thread.sleep(1000);
				fnGet_Element_Enabled("footer");
				fnMove_To_MouseOver("footer");
				//waitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_IM.getProperty("footer")))).isEnabled();
				//fnGet_OR_IM_ObjectX("footer");

				//Splitting AlertLinkTextClickXpathKey TEXT from Xpaths
				String Alert_Link_Text=(OR_IM.getProperty(AlertLinkTextClickXpathKey).split("\\'")[1]).trim();
				
				
				
				//
				try{
					/*fnLoading_wait();
					fnWait(1000);*/
					if(driver.getPageSource().contains(Alert_Link_Text)){
						
						/*fnLoading_wait();*/
						fnGet_Element_Enabled(AlertLinkTextClickXpathKey);
					//	waitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_IM.getProperty(AlertLinkTextClickXpathKey)))).isEnabled();
						fnWait_and_Click(AlertLinkTextClickXpathKey);
						fnsLoading_Progressingwait(2);
						
						for(int i=0; i<3; i++){
						
							if(driver.findElements(By.xpath(OR_IM.getProperty(AlertIssueFilterXpathKey))).size()>0){
								fnGet_Element_Enabled(AlertIssueFilterXpathKey);
								fnWait_and_Type(AlertIssueFilterXpathKey, IssueIDvalue);
								fnsLoading_Progressingwait(1);
								break;
							}
							else{
							/*fnLoading_wait();*/
							fnGet_Element_Enabled(AlertLinkTextClickXpathKey);
							//waitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_IM.getProperty(AlertLinkTextClickXpathKey)))).isEnabled();
							fnWait_and_Click(AlertLinkTextClickXpathKey);
							fnsLoading_Progressingwait(2);
							}
						}	
						
					}
				else{
					//fnWait_and_Click("alert_showsAlerts");
					fnApps_Report_Logs("PASSED == Alert Link Text >> [ "+Alert_Link_Text+" ]  is not present, as there is no IDs for Alert"+" for issue id > "+IssueIDvalue);
					}
			}catch(NoSuchWindowException W){
				isTestCasePass = false;
				throw new Exception(W.getMessage());			}
			catch(Throwable t) {
				isTestCasePass = false;
				fnTake_Screen_Shot("AlertClickinFailFrom" + AlertLinkTextClickXpathKey);
				fnApps_Report_Logs("FAILED == Unable to click on Alert link [ " +AlertLinkTextClickXpathKey+ " ]"+" for issue id > "+IssueIDvalue+ " ],  plz see screenshot [AlertClickinFailFrom" + AlertLinkTextClickXpathKey + fnScreenShot_Date_format() + "]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(t).trim());
				throw new Exception("FAILED == Unable to click on Alert link [" +AlertLinkTextClickXpathKey+ "],  plz see screenshot [AlertClickinFailFrom" + AlertLinkTextClickXpathKey + fnScreenShot_Date_format() + "]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(t).trim());		}
	}
	
		
		
	



//Issue Management verify issue ID exists in Alert Table. ---- Satya Pal
public static void fnVerify_ID_Exist_inAlert_Table(String AlertTable1stCellXpathKey, String AlertLinkTextClickXpathKey, String IssueIDvalue ) throws Exception {
		
		fnGet_Element_Enabled("footer");
		
		String Alert_Link_Text=(OR_IM.getProperty(AlertLinkTextClickXpathKey).split("\\'")[1]).trim();
		String Alert_Fliter_Table_Xpath = (OR_IM.getProperty(AlertTable1stCellXpathKey).split("]")[0]).trim()+"]";
			

		if(driver.getPageSource().contains(Alert_Link_Text)){
			
			fnGet_Element_Enabled(AlertLinkTextClickXpathKey);
			try{
				boolean Alert_Filter_Work = true;
				Integer Row_Count = WithOut_OR_Retrun_TotalRowCount_OfTable(Alert_Fliter_Table_Xpath);
				if(Row_Count==0){
					Thread.sleep(500);
					Alert_Filter_Work =false;
				}
				if(Alert_Filter_Work){
					for(int i=0; i<10; i++){
						Row_Count = WithOut_OR_Retrun_TotalRowCount_OfTable(Alert_Fliter_Table_Xpath);
						
						if(Row_Count==0){
							break;
						}else{
							Thread.sleep(500);
						}	
					}
				}
				
				fnGet_Element_Enabled(AlertTable1stCellXpathKey);
				//TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
				Thread.sleep(500);
				String issue_id_table_column_text=fnGet_Field_TEXT(AlertTable1stCellXpathKey).trim();
				assert issue_id_table_column_text.equals(IssueIDvalue):"FAILED == Alert >>> ["+Alert_Link_Text+" ] for  id >> ( "+IssueIDvalue+" ) has NOT been generated, plz see screenshot [ " + AlertTable1stCellXpathKey + fnScreenShot_Date_format() + "]";
				fnApps_Report_Logs("PASSED == Alert >>> ["+Alert_Link_Text+" ] for  id >> ( "+IssueIDvalue+" ) has been generated successfully.");
					
			}catch(Throwable t) {
				isTestCasePass = false;
				fnTake_Screen_Shot(AlertTable1stCellXpathKey);
				fnApps_Report_Logs(Throwables.getStackTraceAsString(t).trim());
				throw new Exception(Throwables.getStackTraceAsString(t).trim());
			}
		
		}
}




//Issue Management Verify "No records found" Message coming for IssueID when alert is Not Assigned to IssueID as Issue ID Closed. ---- Satya Pal
public static void fnVerify_NoRecordsFound_Msg_for_ID_When_AlertRemoved(String NoRecordFoundXpathKey, String AlertLinkTextClickXpathKey, String IssueIDvalue) throws Exception {
			
		//	WebDriverWait waitvar = new WebDriverWait(driver, Long.parseLong(CONFIG.getProperty("ElementWaitTime")));	
			//Waiting for Footer till page load
			/*fnLoading_wait();*/
			fnGet_Element_Enabled("footer");
			//waitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_IM.getProperty("footer")))).isEnabled();
		//	fnGet_OR_IM_ObjectX("footer");
			
			
				String Alert_Link_Text=(OR_IM.getProperty(AlertLinkTextClickXpathKey).split("\\'")[1]).trim();
				String Alert_Fliter_Table_Xpath = (OR_IM.getProperty(NoRecordFoundXpathKey).split("]")[0]).trim()+"]";
				
				if(driver.getPageSource().contains(Alert_Link_Text)){
					fnGet_Element_Enabled(AlertLinkTextClickXpathKey);
					try{
						boolean Alert_Filter_Work = true;
						Integer Row_Count =/* TestSuiteBase_MonitorPlan.*/WithOut_OR_Retrun_TotalRowCount_OfTable(Alert_Fliter_Table_Xpath);
						if(Row_Count==0){
							Thread.sleep(2000);
							fnApps_Report_Logs("Enter Row Count Thread.sleep 2000");
							Alert_Filter_Work =false;
						}
						if(Alert_Filter_Work){
							for(int i=0; i<10; i++){
								Row_Count =/* TestSuiteBase_MonitorPlan.*/WithOut_OR_Retrun_TotalRowCount_OfTable(Alert_Fliter_Table_Xpath);
								
								if(Row_Count==0){
									fnApps_Report_Logs("Enter Row Count success");
									break;
								}else{
									fnApps_Report_Logs("Enter Row Count Thread.sleep 500");
									Thread.sleep(500);
								}	
							}
						}
						
						fnGet_Element_Enabled(NoRecordFoundXpathKey);
					//	Thread.sleep(250);
						String NoRecordFound_column_text=fnGet_Field_TEXT(NoRecordFoundXpathKey).trim();
						assert !(NoRecordFound_column_text.toLowerCase().contains(IssueIDvalue.toLowerCase())):"FAILED == Alert >>> ["+Alert_Link_Text+" ] for  id >> ( "+IssueIDvalue+" ) is still Present evenwhen the ID has been Closed, Please see screenshot [ ErrorMsgNotComing" + NoRecordFoundXpathKey + fnScreenShot_Date_format() + "]";
						assert (NoRecordFound_column_text.toLowerCase().contains("record")):"FAILED == Alert >>> ["+Alert_Link_Text+" ] for  id >> ( "+IssueIDvalue+" ) is displayed loading instead of 'no record' evenwhen the ID has been Closed, Please see screenshot [ ErrorMsgNotComing" + NoRecordFoundXpathKey + fnScreenShot_Date_format() + "]";
						fnApps_Report_Logs("PASSED == Alert >>> ["+Alert_Link_Text+" ] for  id >> ( "+IssueIDvalue+" ) is not displayed in Alert Table. As ID has been Closed");
											
					}catch(Throwable t) {
						isTestCasePass = false;
						fnTake_Screen_Shot("ErrorMsgNotComing" + NoRecordFoundXpathKey);
						fnApps_Report_Logs(Throwables.getStackTraceAsString(t).trim());
						throw new Exception(Throwables.getStackTraceAsString(t).trim());		
						}
		}
}				






				
					
	//Issue Management Verify Summary details page updated after changing NEW STATUS drop down value. ---- Satya Pal
	public static void fnIssue_VerifyChange_Status_on_SummaryDetailsPage(String StatusChangeValue, String IssueIDvalue) throws Exception {
		try{
			fncIssueID_SearchIssueID_and_Navigate_to_ViewIssueSummaryDetailsPageComes(IssueIDvalue, true);
			
			//Clicking on Summary TAB on View issue page
			fnGet_Element_Enabled("ViewIssueSummaryDetails_summary_detail_tab");
			fnWait_and_Click("ViewIssueSummaryDetails_summary_detail_tab");
			
			
			fnGet_Element_Enabled("ViewIssueSummaryDetails_new_status_dd_click");
			String NewStatusLabelTEXT=fnGet_OR_IM_ObjectX("ViewIssueSummaryDetails_new_status_label").getText().trim();
			fnDD_value_Select("ViewIssueSummaryDetails_new_status_dd_click", "ViewIssueSummaryDetails_new_status_dd_value", "li", StatusChangeValue);
			
			//Waiting for Astric(*) Image to display
			fnGet_Element_Enabled("ViewIssueSummaryDetails_new_status_response_Astric_Image");	
			
			// Enter free text value into New Status Response field		
			fnGet_Element_Enabled("ViewIssueSummaryDetails_new_status_response_textfield");
			fnWait_and_Type("ViewIssueSummaryDetails_new_status_response_textfield", "Edit Issue, Changing Status from ("+NewStatusLabelTEXT+") to ("+StatusChangeValue+")." );
									
			// Clicking on save button
			fnGet_Element_Enabled("ViewIssueSummaryDetails_summary_details_save_button");
			fnWait_and_Click("ViewIssueSummaryDetails_summary_details_save_button");
			fnsLoading_Progressingwait(3);
						
			fnGet_Element_Enabled("validation_error_msg");
			Thread.sleep(1000);
			fnGet_Element_Enabled("validation_error_msg");
			fncVerify_DataUpdated_Successfully("validation_error_msg", "Summary TAB");
		
		}catch(NoSuchWindowException W){
			isTestCasePass = false;
			throw new Exception(W.getMessage());			}
		catch(Throwable t) {
			isTestCasePass = false;
			fnApps_Report_Logs(Throwables.getStackTraceAsString(t).trim());
			throw new Exception(Throwables.getStackTraceAsString(t).trim());
		}
	}	



	
		
	
		
		

//Function to Execute Query in DataBase to close all Issues for specified product code(25253).
public static void fnUpdateDB_to_Close_AFP_issues() throws Throwable {
	
	fnApps_Report_Logs("=========================================================================================================================================");
	  try {
		   Class.forName("oracle.jdbc.driver.OracleDriver");
	  }catch (ClassNotFoundException e) {
		  fnApps_Report_Logs("*********************************************** Oracle JDBC Driver is not found ******************************************");
		 throw new Exception ("*********************************************** Oracle JDBC Driver is not found ******************************************");
	  }
	  fnApps_Report_Logs("*********************************************** Oracle JDBC Driver Registered *********************************************"); 
	
	 
	  Connection connection = null;
	 
	  try {
		 /* if (env.toLowerCase().equalsIgnoreCase("test")){
			  connection = DriverManager.getConnection("jdbc:oracle:thin:@dbserv13vm:1521:certtest", "testscriptuser", "test4nsf");
		  }else if(env.toLowerCase().equalsIgnoreCase("staging")){
			  connection = DriverManager.getConnection("jdbc:oracle:thin:@dbserv30:1521:CERTSTAG", "testscriptuser", "welcome1010");
		  }else{
			  throw new Exception ("FAILED == Environment variable is not defined.");
		  }*/
		   
	 
		   connection = fnpGetDBConnection();
		  
		   Statement stmt= connection.createStatement();
		   
		  
		   
		  /* //Query1.
		   String query1="update ec_alert_transaction set status = 'I', inactive_date = sysdate where entity_id in (select to_char(esc.seq)  from im_issue iss inner join im_escalation esc on iss.im_escalation_seq = esc.seq where iss.issue_type_customer_seq = 623 and iss.site_seq=129853 and iss.ocurred_date >= to_date('01-10-2013', 'DD-MM-YYYY') and iss.ocurred_date <= to_date('15-12-2013', 'DD-MM-YYYY') and esc.substatus <> 'CLOSED') and alem_seq in (select seq from ec_alert_master where entity_master_seq = 4) and status = 'A'";
		 
		   //Query2
         String query2 ="update im_escalation set status = 527, substatus = 'CLOSED' where seq in (select iss.im_escalation_seq from im_issue iss where iss.issue_type_customer_seq = 623 and iss.site_seq=129853 and iss.ocurred_date >= to_date('01-10-2013', 'DD-MM-YYYY') and iss.ocurred_date <= to_date('15-12-2013', 'DD-MM-YYYY') and iss.im_escalation_seq is not null) and substatus <> 'CLOSED'";
         
         //Query3
         String query3="update ec_alert_transaction set status = 'I', inactive_date = sysdate where entity_id in (select to_char(iss.seq) from im_issue iss where iss.issue_type_customer_seq = 623 and iss.site_seq=129853 and parent_status <> 'CLOSED' and iss.ocurred_date >= to_date('01-10-2013', 'DD-MM-YYYY') and iss.ocurred_date <= to_date('15-12-2013', 'DD-MM-YYYY')) and alem_seq in (select seq from ec_alert_master where entity_master_seq = 3) and status = 'A'";

		   //Query4
         String query4="update im_issue set issue_status = 100, parent_status = 'CLOSED', ocurred_date = to_date('01-01-2013', 'DD-MM-YYYY') where seq in (select iss.seq from im_issue iss where iss.issue_type_customer_seq = 623 and iss.site_seq=129853 and parent_status <> 'CLOSED' and iss.ocurred_date >= to_date('01-10-2013', 'DD-MM-YYYY') and iss.ocurred_date <= to_date('15-12-2013', 'DD-MM-YYYY'))";
         
         //Query5
         String query5="update im_issue set ocurred_date = to_date('01-01-2013', 'DD-MM-YYYY') where issue_type_customer_seq = 623 and site_seq=129853 and parent_status = 'CLOSED' and ocurred_date >= to_date('01-10-2013', 'DD-MM-YYYY') and ocurred_date <= to_date('15-12-2013', 'DD-MM-YYYY')";
         */
         
		   //Query1.
		   String query1="update ec_alert_transaction set status = 'I', inactive_date = sysdate where entity_id in (select to_char(esc.seq) from im_issue iss inner join im_escalation esc on iss.im_escalation_seq = esc.seq where iss.site_seq=129776 and esc.substatus <> 'CLOSED') and alem_seq in (select seq from ec_alert_master where entity_master_seq = 4) and status = 'A'";
		 
		   //Query2
         String query2 ="update im_escalation set status = 527, substatus = 'CLOSED' where seq in (select iss.im_escalation_seq from im_issue iss where iss.site_seq=129776 and iss.im_escalation_seq is not null) and substatus <> 'CLOSED'";
         
         //Query3
         String query3="update ec_alert_transaction set status = 'I', inactive_date = sysdate where entity_id in (select to_char(iss.seq) from im_issue iss where iss.site_seq=129776 and parent_status <> 'CLOSED') and alem_seq in (select seq from ec_alert_master where entity_master_seq = 3) and status = 'A'";

		   //Query4
         String query4="update im_issue set issue_status = 100, parent_status = 'CLOSED', ocurred_date = to_date('24-06-2010', 'DD-MM-YYYY') where seq in (select iss.seq from im_issue iss where iss.site_seq=129776 and parent_status <> 'CLOSED')";
         
         //Query5
         String query5="update im_issue set ocurred_date = to_date('24-06-2010', 'DD-MM-YYYY') where site_seq=129776 and parent_status = 'CLOSED' and ocurred_date <> to_date('24-06-2010', 'DD-MM-YYYY')";
          		 
			 
			 stmt.executeUpdate(query1);
			 connection.commit();
			 fnApps_Report_Logs("**** Query1 Executed Successfully."); 
			 
			 
			 stmt.executeUpdate(query2);
		     connection.commit();
		     fnApps_Report_Logs("**** Query2 Executed Successfully."); 
		     
		     
		     stmt.executeUpdate(query3);
			 connection.commit();
			 fnApps_Report_Logs("**** Query3 Executed Successfully."); 
			 
			 
			 stmt.executeUpdate(query4);
			 connection.commit();
			 fnApps_Report_Logs("**** Query4 Executed Successfully."); 
			 
			 
			 stmt.executeUpdate(query5);
			 connection.commit();
			 fnApps_Report_Logs("**** Query5 Executed Successfully."); 
		    
		 
		   connection.close();
		   
	   
	  }catch (SQLException e) {
		  fnApps_Report_Logs("*********************************************** Connection Failed! with Database,   Getting Error >>  "+Throwables.getStackTraceAsString(e).trim());
		  throw new Exception ("*********************************************** Connection Failed! with Database,   Getting Error >>  "+Throwables.getStackTraceAsString(e).trim());
	  }finally {
		  if( !(connection==null) ){
			  connection.close();
		  }
	  }
	  fnApps_Report_Logs("=========================================================================================================================================");
}










public static void Create_AFP_Issue_Without_IssueID_Verification(String No_of_Sick_People, String IssueCreateDate, String IssueReportDate, String IssueTitleFiledText) throws Exception{
	try{	
	//	IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//src//nsf//ecap//xls//IssueMgt_Suite.xlsx");
		
		//Clicking on CreateIssue Ajax Link
		fnCreateIssue_Ajax_Link_Click();
		
		//Site Details page data entered and clicking on next button
		fnVerify_SiteDetailsPage_Data_Entered_and_NavigateTo_SummaryDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_AFP", 2, 2), IssueMgt_Suitexls.getCellData("Escalation_AFP", 2, 3));
	
		
		
		
		
		
		
												//Summary Details page data entered and clicking on next button
				
		//Entered issue create date
		fnGet_Element_Enabled("CreateIssue_SummaryDetails_date_time_issue_date");
		fnWait_and_Type("CreateIssue_SummaryDetails_date_time_issue_date", IssueCreateDate);
						
		//Entered issue create Time
		fnWait_and_Type("CreateIssue_SummaryDetails_date_time_issue_time", fnGet_Current_Time());
						
		//Entered Reported date
		fnWait_and_Type("CreateIssue_SummaryDetails_date_time_reported_date", IssueReportDate);
						
		//Entered Reported Time
		fnWait_and_Type("CreateIssue_SummaryDetails_date_time_reported_time", fnGet_Current_Time());
		
		
		fnGet_Element_Enabled("CreateIssue_SummaryDetails_SiteContactName");
		fnWait_and_Type("CreateIssue_SummaryDetails_SiteContactName", "Testscriptuser");
			
		//**************** Select value from How Received Drop Down 
		fnDD_value_Select("CreateIssue_SummaryDetails_how_recieved_dd_click", "CreateIssue_SummaryDetails_how_recieved_dd_value", "li", IssueMgt_Suitexls.getCellData("Escalation_AFP", 2, 4));
		
									
		//**************** Select value from Issue Type Drop Down
		fnDD_value_Select("CreateIssue_SummaryDetails_issue_type_dd_click", "CreateIssue_SummaryDetails_issue_type_dd_value", "li", IssueMgt_Suitexls.getCellData("Escalation_AFP", 2, 5));
		
		Thread.sleep(1500);	
		//**************** Select value from Issue Sub Type Drop Down
		fnDD_value_Select("CreateIssue_SummaryDetails_issue_sub_type_dd_click", "CreateIssue_SummaryDetails_issue_sub_type_dd_value", "li", IssueMgt_Suitexls.getCellData("Escalation_AFP", 2, 6));
		
			
		//Enter free TEXT date into TilteShortDescription Field	
		fnWait_and_Type("CreateIssue_SummaryDetails_title_short_description", IssueTitleFiledText+fnIssueCreationText_Date_format());
					
		//Clicking on Next Button
		fnClick_UnTill_Navigate_to_NextPage("CreateIssue_SummaryDetails_summary_next_bttn","CreateIssue_SummaryDetails_date_time_issue_date","CreateIssue_Details_No_Of_People_Sick_TextField");
			
		
		
		
		
		
		
		
							//Customer Details page
		//fnGet_Element_Enabled("CreateIssue_Details_No_Of_People_Sick_TextField");
		/*fnGet_Element_Enabled("CreateIssue_Details_No_Of_People_Sick_TextField");
		fnWait_and_Type("CreateIssue_Details_No_Of_People_Sick_TextField", "1");
		
		fnWait_and_Type("CreateIssue_Details_Total_similar_meals_served_TextField", "2                                             (Automation Test Data)");
		
		fnWait_and_Type("CreateIssue_Details_72_hours_before_TextField", "Normal Daily Diet                    (Automation Test Data)");
		
		fnWait_and_Type("CreateIssue_Details_24_hours_before_TextField", "Normal Daily Diet                    (Automation Test Data)");
		
		fnWait_and_Type("CreateIssue_Details_12_hours_before_TextField", "Normal Daily Diet                    (Automation Test Data)");
		
		fnWait_and_Type("CreateIssue_Details_Food_suspected_TextField", "2                                             (Automation Test Data)");*/
		
		
		//Added on 3.12.2019
		fnGet_Element_Enabled("CreateIssue_Details_No_Of_People_Sick_TextField");
		fnWait_and_Type("CreateIssue_Details_No_Of_People_Sick_TextField", No_of_Sick_People);		
		fnWait_and_Type("CreateIssue_Details_Total_similar_meals_served_TextField", "2                                             (Automation Test Data)");
		fnWait_and_Type("CreateIssue_Details_Food_suspected_TextField", "2                                             (Automation Test Data)");
		fnsWait_and_Click("CreateIssue_Details_AttendedGPOrHospital");
		fnsWait_and_Click("CreateIssue_Details_StoolTestDone");
		fnsWait_and_Click("CreateIssue_Details_EHOContactedInvolved");
		fnsWait_and_Click("CreateIssue_Details_AllegedUndercookedFood");
		fnsWait_and_Click("CreateIssue_Details_PressSocialMediaInvolvement");
		fnsWait_and_Click("CreateIssue_Details_AtRiskGroup_Under5_Elderly_Pregnant_WeakenedImmuneSystem");		
		fnDD_value_Select("CreateIssue_Details_ApproxHowLongAfterEatingUntilAppearanceFirstSymptoms_dd_click", "CreateIssue_Details_ApproxHowLongAfterEatingUntilAppearanceFirstSymptoms_DD_Label", "li", "0-2 hrs");
		fnWait_and_Click("CreateIssue_details_HaveYouObtainedConsent");				
		fnWait_and_Click("CreateIssue_details_InCaseOfAMinor");
		fnsLoading_Progressingwait(1);
		
		
		fnClick_UnTill_Navigate_to_NextPage("CreateIssue_Customer_next_bttn","CreateIssue_Details_No_Of_People_Sick_TextField","CreateIssue_document_Add_Attachement");	
		
		
	
		// Clicking on Document next button
		fnClick_UnTill_Navigate_to_NextPage("CreateIssue_document_next_bttn","CreateIssue_document_Add_Attachement","CreateIssue_confirmation_submit_bttn");		
		
		// Clicking on Confirmation Submit button
		fnGet_Element_Enabled("CreateIssue_confirmation_submit_bttn");
		fnWait_and_Click("CreateIssue_confirmation_submit_bttn");
		
	}catch(NoSuchWindowException W){
		isTestCasePass = false;
		throw new SkipException(W.getMessage());
	}catch(Throwable t){
		isTestCasePass = false;
		fnApps_Report_Logs(Throwables.getStackTraceAsString(t));
		throw new Exception(Throwables.getStackTraceAsString(t));	}
	
}











public static void Create_DIS_Issue_Without_IssueID_Verification(String SiteName, String IssueCreateDate, String IssueTitleFiledText) throws Throwable{
	try{
	//	IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//src//nsf//ecap//xls//IssueMgt_Suite.xlsx");
			
		//Clicking on CreateIssue Ajax Link
		fnCreateIssue_Ajax_Link_Click();
		
		
		
		
		
												//Site Details page data entered and clicking on next button
		fnVerify_SiteDetailsPage_Data_Entered_and_NavigateTo_SummaryDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_DIS", 2, 2), SiteName);
	
		
		
		
		
		
												//Summary Details page data entered and clicking on next button
				
		//Entered issue create date
		fnGet_Element_Enabled("CreateIssue_SummaryDetails_date_time_issue_date");
		fnWait_and_Type("CreateIssue_SummaryDetails_date_time_issue_date", IssueCreateDate);
		//Entered issue create Time
		fnWait_and_Type("CreateIssue_SummaryDetails_date_time_issue_time", fnGet_Current_Time());
						
		//Entered Reported date
		fnWait_and_Type("CreateIssue_SummaryDetails_date_time_reported_date", IssueCreateDate);
		//Entered Reported Time
		fnWait_and_Type("CreateIssue_SummaryDetails_date_time_reported_time", fnGet_Current_Time());
						
	
		fnGet_Element_Enabled("CreateIssue_SummaryDetails_SiteContactName");
		fnWait_and_Type("CreateIssue_SummaryDetails_SiteContactName", "Testscriptuser");
						
		
									
		//**************** Select value from Issue Type Drop Down
		fnDD_value_Select("CreateIssue_SummaryDetails_issue_type_dd_click", "CreateIssue_SummaryDetails_issue_type_dd_value", "li", IssueMgt_Suitexls.getCellData("Escalation_DIS", 2, 19));
		
		Thread.sleep(1000);
		//**************** Select value from Issue Sub Type Drop Down
		fnDD_value_Select("CreateIssue_SummaryDetails_issue_sub_type_dd_click", "CreateIssue_SummaryDetails_issue_sub_type_dd_value", "li", IssueMgt_Suitexls.getCellData("Escalation_DIS", 2, 20));
		
		
		
		
		
		
		//Enter free TEXT date into TilteShortDescription Field	
		fnWait_and_Type("CreateIssue_SummaryDetails_title_short_description", IssueTitleFiledText+fnIssueCreationText_Date_format());
		
		//**************** Select value from How Received Drop Down
		fnDD_value_Select("CreateIssue_SummaryDetails_how_recieved_dd_click", "CreateIssue_SummaryDetails_how_recieved_dd_value", "li", IssueMgt_Suitexls.getCellData("Escalation_DIS", 2, 18));
				
					
		//Clicking on Next Button
		fnClick_UnTill_Navigate_to_NextPage("CreateIssue_SummaryDetails_summary_next_bttn","CreateIssue_SummaryDetails_date_time_issue_date","CreateIssue_Customer_Name_TextField");
			
		
		fnsLoading_Progressingwait(1);
		
		fnWait_and_Click("CreateIssue_detailsTab_Have_You_Obtained_Consent_Radio_Yes_button");
		fnsLoading_Progressingwait(1);
		
		fnWait_and_Click("CreateIssue_detailsTab_In_Case_Of_A_Minor_Radio_Yes_button");
		fnsLoading_Progressingwait(1);
		
		
		
		
											//Customer Details page
		//Clicking on Customer  NEXT Button
		fnClick_UnTill_Navigate_to_NextPage("CreateIssue_Customer_next_bttn","CreateIssue_detailsTab_In_Case_Of_A_Minor_Radio_Yes_button","CreateIssue_product_code_bttn");
		
		
		
		
		
		
		
											//Product Details page data entered and clicking on next button
		// Click on ProductCode button
		fnGet_Element_Enabled("CreateIssue_product_code_bttn"); //waitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_IM.getProperty("CreateIssue_product_code_bttn")))).isEnabled();
		fnWait_and_Click("CreateIssue_product_code_bttn");
		//Enter value in TEXT field on ProdultLookUp Screen to search for Product	
		fnGet_Element_Enabled("CreateIssue_product_lookup_value");
		fnWait_and_Type("CreateIssue_product_lookup_value", IssueMgt_Suitexls.getCellData("Escalation_DIS", 2, 22));
		// Selecting first radio button from search result table
		fnsLookup_RadioBttn_Select();
		
		fnWait(2000);
		//Entered quantity in ProductQuantity TEXTBox
		fnGet_Element_Enabled("CreateIssue_product_quantity");
		fnWait_and_Type("CreateIssue_product_quantity", IssueMgt_Suitexls.getCellData("Escalation_DIS", 2, 23));
		//Select Value from ProductUnitDD
		fnDD_value_Select("CreateIssue_product_unit_dd_click", "CreateIssue_product_unit_dd_value_select", "li", IssueMgt_Suitexls.getCellData("Escalation_DIS", 2, 24));
		
		//Click on product_Distributor_Lookup_bttn
		fnWait_and_Click("CreateIssue_product_Distributor_Lookup_bttn");
		
		
		fnGet_Element_Enabled("CreateIssue_product_Distributor_Lookup_Value_InputField");
		fnWait_and_Type("CreateIssue_product_Distributor_Lookup_Value_InputField", IssueMgt_Suitexls.getCellData("Escalation_DIS", 2, 25));
		fnsLookup_RadioBttn_Select();		
		
		fnWait(2000);
		
		
		//Clicking on next button on product page
		fnClick_UnTill_Navigate_to_NextPage("CreateIssue_product_next_bttn","CreateIssue_product_code_bttn","CreateIssue_document_Add_Attachement");
		
		
	
												// Clicking on Document next button
		fnClick_UnTill_Navigate_to_NextPage("CreateIssue_document_next_bttn","CreateIssue_document_Add_Attachement","CreateIssue_confirmation_submit_bttn");		
		
		
												// Clicking on Confirmation Submit button
		fnGet_Element_Enabled("CreateIssue_confirmation_submit_bttn");
		fnWait_and_Click("CreateIssue_confirmation_submit_bttn");
	}catch(NoSuchWindowException W){
		isTestCasePass = false;
		throw new SkipException(W.getMessage());
	}catch(Throwable t){
		isTestCasePass = false;
		fnApps_Report_Logs(Throwables.getStackTraceAsString(t));
		throw new Exception(Throwables.getStackTraceAsString(t));	}
	
	
}





//Picking date current day and Current Month for String Passing year By Selecting values from months and years DropDpwn.
public static void fncCalendarDatePicker_BySelectValues_From_DropDown_NSFOnline(String DatePickForWhichDay, String DatePickForWhichMonth_MMMM, String DatePickForWhichYear_YYYY) throws Throwable{
	try{
		String CurrentDay = null;
		
		if(DatePickForWhichMonth_MMMM!=null){
			WithOut_OR_fnDD_SelectValue_By_SelectClass(OR_IM.getProperty("NSFOnline_Calander_Month_DD"), DatePickForWhichMonth_MMMM);
			Thread.sleep(2000);
		}
		
		if(DatePickForWhichYear_YYYY!=null){
			WithOut_OR_fnDD_SelectValue_By_SelectClass(OR_IM.getProperty("NSFOnline_Calander_Year_DD"), DatePickForWhichYear_YYYY);
			Thread.sleep(2000);
		}
		
		if(DatePickForWhichDay!=null){
			CurrentDay = DatePickForWhichDay;
		}else{
			Calendar cal = Calendar.getInstance();
			DateFormat dayFormat = new SimpleDateFormat("d");
			CurrentDay = dayFormat.format(cal.getTime());
		}
		String CurrentDayXpath = "//a[text()='"+CurrentDay+"']";
		Thread.sleep(1000);
		if(CurrentDay.equalsIgnoreCase("31")){
			CurrentDayXpath = "//a[text()='30']";
		}
		
		//TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(CurrentDayXpath);
		fnGet_Element_Enabled(CurrentDayXpath);
		WithOut_OR_fnClick(CurrentDayXpath);
		
		Thread.sleep(500);

	}catch(NoSuchWindowException W){
		isTestCasePass = false;
		throw new SkipException(W.getMessage());
	}catch(Throwable t){
		//TestSuiteBase_MonitorPlan.fnsTake_Screen_Shot_Without_OR("DatePickFail");
		fnTake_Screen_Shot("DatePickFail");
		isTestCasePass = false;
		fnApps_Report_Logs("FAILED == Calendar Date Pick is getting fail, Please refer screen shot >> DatePickFail"+fnScreenShot_Date_format()+"   Getting Exception >>"+Throwables.getStackTraceAsString(t));
		throw new Exception("FAILED == Calendar Date Pick is getting fail, Please refer screen shot >> DatePickFail"+fnScreenShot_Date_format()+"   Getting Exception >>"+Throwables.getStackTraceAsString(t));	}
}







//#########################################################################################################################################################################
//*******************************************************************   NEW Methods (25th August, 2015)  ****************************************************************
//##########################################################################################################################################################################

//Verify data save successfully.
public static void fncVerify_DataUpdated_Successfully(String UpdateMsgXpath, String SectionName) throws Throwable{
	try{
		String Fetched_Text = fnGet_Field_TEXT(UpdateMsgXpath).toLowerCase().trim();
		WithOut_OR_fnMove_To_Element_BY_OFFSET(OR_IM.getProperty(UpdateMsgXpath), 30, 10);
		try{
			assert (Fetched_Text.contains("success")):"FAILED == "+SectionName+": Data are not updated, Getting Error<"+Fetched_Text+">, Please refer screenshot >> UpdateFail"+fnScreenShot_Date_format();
			fnApps_Report_Logs("PASSED == "+SectionName+": Data are updated successfully.");
		}catch(Throwable t){
			fnTake_Screen_Shot("UpdateFail");
			throw new Exception(Throwables.getStackTraceAsString(t));		}
	
	}catch(NoSuchWindowException W){
		isTestCasePass = false;
		throw new SkipException(W.getMessage());
	}catch(Throwable t){
		isTestCasePass = false;
		fnApps_Report_Logs(Throwables.getStackTraceAsString(t));
		throw new Exception(Throwables.getStackTraceAsString(t));	}
}	


//Search IssueID and also navigate to ViewIssue Summary Details page ---- Satya Pal
public static void fncEscalation_Verify_First_IssueClosed_after_EscalationClosed(String IssueStatusXpathKey, String IssueIDvalue, String VerifyTEXTXpathKey, boolean SearchIssue_AjaxClick) throws Exception {
	try{
		fncIssueID_SearchIssueID_and_Navigate_to_ViewIssueSummaryDetailsPageComes(IssueIDvalue, SearchIssue_AjaxClick);		
		
		fnGet_Element_Enabled(IssueStatusXpathKey);
		WithOut_OR_fnMove_To_Element_and_DoubleClick(OR_IM.getProperty(IssueStatusXpathKey));
		Thread.sleep(500);
		
		String IssueStatus_text=fnGet_Field_TEXT(IssueStatusXpathKey).trim();
		try{
			assert IssueStatus_text.contains(VerifyTEXTXpathKey):"FAILED == IssueID ("+IssueIDvalue+") Status is not coming as ( "+IssueStatus_text+" ), which is not matche with expected<"+VerifyTEXTXpathKey+">,  Please refer screen shot"+"IssueIdStatus_Fail" + fnScreenShot_Date_format();
			fnApps_Report_Logs("PASSED == ************ IssueID ("+IssueIDvalue+") is successfully Verified and It is "+VerifyTEXTXpathKey);
		}catch(Throwable t) {
			fnTake_Screen_Shot("IssueIdStatus_Fail");
			fnApps_Report_Logs(Throwables.getStackTraceAsString(t).trim());
			throw new Exception(Throwables.getStackTraceAsString(t).trim());
		}
	}catch(NoSuchWindowException W){
		isTestCasePass = false;
		throw new Exception(W.getMessage());			}	
	catch(Throwable t) {
		isTestCasePass = false;
		fnApps_Report_Logs(Throwables.getStackTraceAsString(t).trim());
		throw new Exception(Throwables.getStackTraceAsString(t).trim());
	}
}



//Search IssueID and also navigate to ViewIssue Summary Details page ---- 
public static void fncIssueID_SearchIssueID_and_Navigate_to_ViewIssueSummaryDetailsPageComes(String IssueIDvalue, boolean SearchIssue_AjaxClick) throws Exception {
					
	try{
		Thread.sleep(2000);
		if(SearchIssue_AjaxClick){
			fnSearchIssue_Ajax_Link_Click();
			//TestSuiteBase_Aspirago.fnsLoading_Progressingwait(3);
			fnGet_Element_Enabled("footer");
			fnsLoading_Progressingwait(1);
		}
		
		fnGet_Element_Enabled("SearchIssue_issue_id_text_field");
		WithOut_OR_fnClear(OR_IM.getProperty("SearchIssue_issue_id_text_field"));
		fnWait_and_Type("SearchIssue_issue_id_text_field", IssueIDvalue);
		
		fnGet_Element_Enabled("SearchIssue_search_bttn");
		fnWait_and_Click("SearchIssue_search_bttn");
		fnsLoading_Progressingwait(3);
							
		try{
			for(int start=0; start<Long.parseLong(CONFIG.getProperty("ElementWaitTime")); start++){
				Thread.sleep(1000);
				
				fnGet_Element_Enabled("SearchIssue_Table_1st_Cell");
				String FirstResultCellValue= fnGet_Field_TEXT("SearchIssue_Table_1st_Cell").trim();
									
				if ( !(FirstResultCellValue.contains(IssueIDvalue)) ){
					Thread.sleep(1000);
					fnGet_Element_Enabled("SearchIssue_issue_id_text_field");
					WithOut_OR_fnClear(OR_IM.getProperty("SearchIssue_issue_id_text_field"));
					fnGet_Element_Enabled("SearchIssue_issue_id_text_field");
					fnWait_and_Type("SearchIssue_issue_id_text_field", IssueIDvalue);
					
					fnGet_Element_Enabled("SearchIssue_search_bttn");					
					fnWait_and_Click("SearchIssue_search_bttn");
					fnsLoading_Progressingwait(2); // new line
					break;
				}
				
				if (FirstResultCellValue.contains(IssueIDvalue)){
					break;
				}
	
			}
		}catch(Throwable t){
			fnTake_Screen_Shot("SearchIssueIdFail");
			throw new Exception("FAILED == Issue ID Search is getting failed, please refer screenshot >> SearchIssueIdFail"+fnScreenShot_Date_format()+". Getting Exception  >> "+Throwables.getStackTraceAsString(t).trim());
		}
				
		//DUE TO RECENT VIEW FUNCTIONALITY, IT'S NOT WORKING SO COMMENTED BELOW LINES AND ADDED BELOW LINES 8.6.2017 
		/*if(SearchIssue_AjaxClick){		
			String SearchResult_IssueID_link_Xpath = ".//a[contains(text(),'" + IssueIDvalue + "')]";
			TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(SearchResult_IssueID_link_Xpath);
			TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(SearchResult_IssueID_link_Xpath);
			TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(3);
		}*/
		
		
		if(SearchIssue_AjaxClick){
			fnsTable_ClickOn_LINK_ByMatchingText(IssueIDvalue);
			fnsLoading_Progressingwait(3);
		}
		
	}catch(NoSuchWindowException W){
		isTestCasePass = false;
		throw new Exception(W.getMessage());			}
	catch(Throwable t) {
		isTestCasePass = false;
		fnApps_Report_Logs(Throwables.getStackTraceAsString(t).trim());
		throw new Exception(Throwables.getStackTraceAsString(t).trim());
	}
								
}
		
//Search Escalation and also navigate to ViewEscalation EscalationDetails page ---- Satya Pal
public static void fncEscalation_SearchEscalation_and_Navigate_till_ViewEscalationDetailsPageComes(String EscalationIDvalue) throws Exception {
	try{
				
		fncMenu_Ajax_Link_Click_By_PassingAjaxPath("Search_Escalations");
		
		/*// Clicking on search mouse over
		fnGet_Element_Enabled("Menu");
		WebElement menu1 = fnGet_OR_IM_ObjectX("Menu"); 
		WebElement issue_management1=fnGet_OR_IM_ObjectX("Issue_Management");	
		WebElement create_issue1=fnGet_OR_IM_ObjectX("Create_Issue");
		WebElement Search_escalation=fnGet_OR_IM_ObjectX("Search_Escalations");
		Actions act = new Actions(driver);
		act.moveToElement(menu1).moveToElement(issue_management1).moveToElement(create_issue1).moveToElement(Search_escalation).click().build().perform();
		fnApps_Report_Logs("PASSED == Successfully click on 'Search Escalation' Ajax link.");
		TestSuiteBase_Aspirago.fnsLoading_Progressingwait(3);*/
		
		fnGet_Element_Enabled("footer");
					
		fnGet_Element_Enabled("SearchEscalation_escalation_id_text_field");
		fnWait_and_Type("SearchEscalation_escalation_id_text_field", EscalationIDvalue);
		
		fnGet_Element_Enabled("SearchEscalation_search_bttn");
		fnWait_and_Click("SearchEscalation_search_bttn");
		fnsLoading_Progressingwait(3);
				
		
		
		
		try{
			for(int start=0; start<Long.parseLong(CONFIG.getProperty("ElementWaitTime")); start++){
				Thread.sleep(1000);
				
				fnGet_Element_Enabled("SearchIssue_Table_1st_Cell");
				String FirstResultCellValue= fnGet_Field_TEXT("SearchIssue_Table_1st_Cell").trim();
				
				if ( !(FirstResultCellValue.contains(EscalationIDvalue)) ){
					Thread.sleep(1000);
					fnGet_Element_Enabled("SearchEscalation_escalation_id_text_field");
					WithOut_OR_fnClear(OR_IM.getProperty("SearchEscalation_escalation_id_text_field"));
					fnGet_Element_Enabled("SearchEscalation_escalation_id_text_field");
					fnWait_and_Type("SearchEscalation_escalation_id_text_field", EscalationIDvalue);
					
					fnGet_Element_Enabled("SearchEscalation_search_bttn");					
					fnWait_and_Click("SearchEscalation_search_bttn");
				}
				
				if (FirstResultCellValue.contains(EscalationIDvalue)){
					break;
				}
			}
		}catch(Throwable t){
			fnTake_Screen_Shot("SearchEscalationIdFail");
			throw new Exception("FAILED == Escalation ID Search is getting failed, please refer screenshot >> SearchEscalationIdFail"+fnScreenShot_Date_format()+". Getting Exception  >> "+Throwables.getStackTraceAsString(t).trim());
		
				
			}
				
		//DUE TO RECENT VIEW FUNCTIONALITY, IT'S NOT WORKING SO COMMENTED BELOW LINES AND ADDED BELOW LINES  								
		/*String SearchResult_EscalationID_link_Xpath = ".//a[contains(text(),'" + EscalationIDvalue + "')]";
		
		TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(SearchResult_EscalationID_link_Xpath);
		TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(SearchResult_EscalationID_link_Xpath);*/
		
		
		fnsTable_ClickOn_LINK_ByMatchingText(EscalationIDvalue);
		fnsLoading_Progressingwait(3);
		
						
	}catch(NoSuchWindowException W){
		isTestCasePass = false;
		throw new Exception(W.getMessage());			}
	catch(Throwable t) {
		isTestCasePass = false;
		fnApps_Report_Logs(Throwables.getStackTraceAsString(t).trim());
		throw new Exception(Throwables.getStackTraceAsString(t).trim());
	}			
}






//Issue Management Verify Summary details page updated after changing NEW STATUS drop down value. ---- Satya Pal
public static void fncEscalation_VerifyChange_Status_on_SummaryDetailsPage(String StatusChangeValue) throws Exception {
	try{
		fnGet_Element_Enabled("Escalation_Summary_NewStatus_DD_click");
		String NewStatusLabelTEXT=fnGet_OR_IM_ObjectX("Escalation_Summary_New_Status_DD_Label").getText().trim();
		fnDD_value_Select("Escalation_Summary_NewStatus_DD_click", "Escalation_Summary_NewStatus_DD_Value", "li", StatusChangeValue);
		
		//Waiting for Astric(*) Image to display
		fnGet_Element_Enabled("Escalation_Summary_NewStatus_Astric_Image");	
		
		// Enter free text value into New Status Response field		
		fnGet_Element_Enabled("Escalation_Summary_New_Status_Response_TextBox");
		fnWait_and_Type("Escalation_Summary_New_Status_Response_TextBox", "Edit Escalation, Changing Status from ("+NewStatusLabelTEXT+") to ("+StatusChangeValue+")." );
			
		// Select Assign_To_Technologist_CheckBox
		if(Technologist_CheckBox_Flag){
			fnGet_Element_Enabled("Escalation_Summary_Assign_To_Technologist_CheckBox");
			fnWait_and_Click("Escalation_Summary_Assign_To_Technologist_CheckBox");
			Technologist_CheckBox_Flag = false;
		}
		
		// Clicking on save button
		fnGet_Element_Enabled("Escalation_summary_details_save_button");
		fnWait_and_Click("Escalation_summary_details_save_button");
		fnsLoading_Progressingwait(3);
		
	}catch(NoSuchWindowException W){
		isTestCasePass = false;
		throw new Exception(W.getMessage());			}
	catch(Throwable t) {
		isTestCasePass = false;
		throw new Exception(Throwables.getStackTraceAsString(t).trim());
	}
			
}	


//Issue Management Verify Summary details page updated after changing NEW STATUS drop down value. ---- Satya Pal
public static void fncIssue_VerifyChange_Status_on_SummaryDetailsPage(String StatusChangeValue, String IssueIDvalue) throws Exception {
	try{
		fnGet_Element_Enabled("ViewIssueSummaryDetails_new_status_dd_click");
		String NewStatusLabelTEXT=fnGet_OR_IM_ObjectX("ViewIssueSummaryDetails_new_status_label").getText().trim();
		fnDD_value_Select("ViewIssueSummaryDetails_new_status_dd_click", "ViewIssueSummaryDetails_new_status_dd_value", "li", StatusChangeValue);
		
		//Waiting for Astric(*) Image to display
		fnGet_Element_Enabled("ViewIssueSummaryDetails_new_status_response_Astric_Image");	
		
		// Enter free text value into New Status Response field		
		fnGet_Element_Enabled("ViewIssueSummaryDetails_new_status_response_textfield");
		fnWait_and_Type("ViewIssueSummaryDetails_new_status_response_textfield", "Edit Issue, Changing Status from ("+NewStatusLabelTEXT+") to ("+StatusChangeValue+")." );
								
		// Clicking on save button
		fnGet_Element_Enabled("ViewIssueSummaryDetails_summary_details_save_button");
		fnWait_and_Click("ViewIssueSummaryDetails_summary_details_save_button");
		fnsLoading_Progressingwait(3);
		
	}catch(NoSuchWindowException W){
		isTestCasePass = false;
		throw new Exception(W.getMessage());			}
	catch(Throwable t) {
		isTestCasePass = false;
		throw new Exception(Throwables.getStackTraceAsString(t).trim());
	}
			
}


//Search Escalation and also navigate to ViewEscalation EscalationDetails page ---- Satya Pal
public static void fncSearchEscalation_and__VerifyChange_Status_on_SummaryDetailsPage(String StatusChangeValue, String EscalationIDvalue ) throws Exception {
	try{
		fncEscalation_SearchEscalation_and_Navigate_till_ViewEscalationDetailsPageComes(EscalationIDvalue);
		
		fncEscalation_VerifyChange_Status_on_SummaryDetailsPage(StatusChangeValue);
		
	}catch(NoSuchWindowException W){
		isTestCasePass = false;
		throw new Exception(W.getMessage());			}
	catch(Throwable t) {
		isTestCasePass = false;
		throw new Exception(Throwables.getStackTraceAsString(t).trim());
	}
		
}



// Click on Technologist Type Radio Button and then Select first Technologist Name from DD
public static void fnc_ClickOn_TechnologistTypeRadioBttn_AndThen_Select_TechnologistNameFromDD(String TechnologistTypeRadio_bttn, String TechnologistName_ddClick, String TechnologistName_ddValue) throws Throwable{
	try{
		List<WebElement> DD_li_All_Objects = null;
		Integer DD_li_All_Objects_Size = 1;
		First_TechnologistName_From_DD = null;
			System.out.println("TEST");	
		for(int DD_Value_Load_Try=1; DD_Value_Load_Try <= ( (Long.parseLong(CONFIG.getProperty("ElementWaitTime")))/5 ); DD_Value_Load_Try++){
			fnWait_and_Click(TechnologistTypeRadio_bttn);
			Thread.sleep(5000);
			fnGet_OR_IM_ObjectX(TechnologistName_ddClick).click();
			System.out.println("Click done = "+DD_Value_Load_Try);
			try{
				fnGet_Element_Enabled(TechnologistName_ddValue);
				DD_li_All_Objects = driver.findElement(By.xpath(OR_IM.getProperty(TechnologistName_ddValue))).findElements(By.tagName("li"));
				DD_li_All_Objects_Size = DD_li_All_Objects.size();
			}catch(Throwable t){
				System.out.println("catch exception");
				//nothing to do 
			}
			if( DD_li_All_Objects_Size > 1 ){
				break;
			}
			if( DD_Value_Load_Try == ( (Long.parseLong(CONFIG.getProperty("ElementWaitTime")))/5 ) && DD_li_All_Objects_Size==1 ){
				throw new Exception ("FAILED == 'Technologist Name' Drop down is not getting load, after <"+( (Long.parseLong(CONFIG.getProperty("ElementWaitTime"))))+"> seconds wait, please refer screen shot >> TechnologistName_DD_Value_Select_Fail"+fnScreenShot_Date_format());
			}
		}
		
		for(WebElement DD_li_Element:DD_li_All_Objects){
			String DD_Element_Text = DD_li_Element.getText().trim();
			if( !(DD_Element_Text.toLowerCase().contains("select one")) && DD_Element_Text.length()>2){
				First_TechnologistName_From_DD = DD_Element_Text;
				Actions act = new Actions(driver);
				act.moveToElement(DD_li_Element).click(DD_li_Element).build().perform();
				fnApps_Report_Logs("PASSED == Successfully select < "+DD_Element_Text+" > from 'Technologist Name' Drop down.");
				break;
			}
		}
		//Below step is added here because above selection from DD is not working.
		if(driver.findElements(By.xpath(OR_IM.getProperty("ViewIssueSummaryDetails_summary_details_save_button"))).size()>0){
			fnGet_Element_Enabled("ViewIssueSummaryDetails_summary_details_save_button");
			fnWait_and_Click("ViewIssueSummaryDetails_summary_details_save_button");
		}else if(driver.findElements(By.xpath(OR_IM.getProperty("Escalation_summary_details_save_button"))).size()>0){
			fnGet_Element_Enabled("Escalation_summary_details_save_button");
			fnWait_and_Click("Escalation_summary_details_save_button");
		}
		
		fnsLoading_Progressingwait(2);
		fnsLoading_Progressingwait(1);
	}catch(Throwable t){
		fnTake_Screen_Shot("TechnologistName_DD_Value_Select_Fail");
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
	
}

//Search IssueID and also navigate to ViewIssue Summary Details page ---- 
public static void fncIssue_DetailsTAB_RadioButtonsSelect_and_Verify_SaveSuccessfully() throws Exception {
	try{
		fnWait_and_Click("ViewIssue_details_tab");
		fnsLoading_Progressingwait(1);
		
		/*fnWait_and_Click("ViewIssue_detailsTab_In_Case_Of_A_Minor_Radio_Yes_button");
		fnsLoading_Progressingwait(1);
		
		fnWait_and_Click("ViewIssue_detailsTab_Have_You_Obtained_Consent_Radio_Yes_button");
		fnsLoading_Progressingwait(1);
		
				
		fnWait_and_Click("ViewIssueSummaryDetails_summary_details_save_button");
		fnsLoading_Progressingwait(3);
		
		fncVerify_DataUpdated_Successfully("validation_error_msg", "Details TAB : Radio buttons select");	*/
		
	}catch(NoSuchWindowException W){
		isTestCasePass = false;
		throw new Exception(W.getMessage());			}
	catch(Throwable t) {
		isTestCasePass = false;
		fnApps_Report_Logs("Details Tab : "+Throwables.getStackTraceAsString(t).trim());
		throw new Exception("Details Tab : "+Throwables.getStackTraceAsString(t).trim());
	}
								
}

//#########################################################################################################################################################################
//**********************************************************************   Method not using Start  ************************************************************************
//##########################################################################################################################################################################


	public static void fnLoading_wait() throws InterruptedException {
		int i = 0;
		

		while (fnCheckElementPresenceImmediately("BlockedUIScreen")) {
			Thread.sleep(250);
			i++;
			
			if (i > Integer.parseInt(CONFIG.getProperty("ElementWaitTime"))) {
				break;

			}

		}
	
	}

	
	

	@SuppressWarnings("finally")
	public static boolean fnCheckElementPresenceImmediately(String XpathKey) {
		boolean result = false;
		

		try {
		
			driver.manage().timeouts().implicitlyWait(500,TimeUnit.MILLISECONDS);

			if (driver.findElements(By.xpath(OR.getProperty(XpathKey))).size()>0) {
			
				result = true;
			} else {
				result = false;
			}

		} catch (Throwable t) {
			result = false;
		}

		finally {
		//	driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("ElementWaitTime")), TimeUnit.SECONDS);

			return result;
		}
	}

	
	
	





/*

//Function to Click_on button UnTill Navigate to NextPage
public static void fnClick_UnTill_Navigate_to_NextPage(String ButtonXpathKey, String ButtonPageOtherElementXpathKey, String NextPageElementXpathKey) throws Exception {
	try{
		for(int i=0; i<5; i++){
			
			if(driver.findElements(By.xpath(OR_IM.getProperty(NextPageElementXpathKey))).size()>0){
				break;
			}			
			
			if(driver.findElements(By.xpath(OR_IM.getProperty(ButtonXpathKey))).size()>0 && driver.findElements(By.xpath(OR_IM.getProperty(ButtonPageOtherElementXpathKey))).size()>0 ){
				fnGet_OR_IM_ObjectX(ButtonXpathKey).click();
				Thread.sleep(2000);
			}
			
			if(driver.findElements(By.xpath(OR_IM.getProperty(NextPageElementXpathKey))).size()>0){
				break;
			}else{
				Thread.sleep(2000);
			}
		}
		fnApps_Report_Logs("PASSED == Successfully Click on Element having Xpath  >> "+ButtonXpathKey);
		
	}catch(NoSuchWindowException W){
		isTestCasePass = false;
		throw new Exception(W.getMessage());			}
	catch(Throwable t){
		isTestCasePass = false;
		fnTake_Screen_Shot("UnableToClick_" + ButtonXpathKey);
		fnApps_Report_Logs("FAILED == Unable To Click on Element having Xpath >> "+ButtonXpathKey+", plz see screenshot [ UnableToClick_" + ButtonXpathKey + fnScreenShot_Date_format() + " ], Getting Exception >>"+Throwables.getStackTraceAsString(t));
		throw new Exception("FAILED == Unable To Click on Element having Xpath >> "+ButtonXpathKey+", plz see screenshot [ UnableToClick_" + ButtonXpathKey + fnScreenShot_Date_format() + " ], Getting Exception >>"+Throwables.getStackTraceAsString(t));
		}
	}



*/

	
//Search IssueID and also navigate to ViewIssue Summary Details page ---- 
public static void fnIssueID_SearchIssueID_and_Navigate_to_ViewIssueSummaryDetailsPageComes(String IssueIDvalue) throws Exception {
	
	WebDriverWait waitvar = new WebDriverWait(driver, Long.parseLong(CONFIG.getProperty("ElementWaitTime")));				
					
	try{		
		fnSearchIssue_Ajax_Link_Click();
				
		//Waiting for Footer till page load
		fnLoading_wait();
		waitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_IM.getProperty("footer")))).isEnabled();
		
		fnLoading_wait();	
		fnGet_OR_IM_ObjectX("SearchIssue_search_bttn");
		waitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_IM.getProperty("SearchIssue_search_bttn")))).isEnabled();
		
		
		fnLoading_wait();
		waitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_IM.getProperty("SearchIssue_issue_id_text_field")))).isEnabled();
		fnWait_and_Type("SearchIssue_issue_id_text_field", IssueIDvalue);
		
		
		fnLoading_wait();	
		waitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_IM.getProperty("SearchIssue_search_bttn")))).isEnabled();
		fnWait_and_Click("SearchIssue_search_bttn");
		
		Thread.sleep(5000);
				
		try{
			for(int start=0; start<Long.parseLong(CONFIG.getProperty("ElementWaitTime")); start++){
				Thread.sleep(1000);
					
				fnLoading_wait();
				waitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_IM.getProperty("SearchIssue_Table_1st_Cell")))).isEnabled();
				String FirstResultCellValue= fnGet_OR_IM_ObjectX("SearchIssue_Table_1st_Cell").getText().trim();
				System.out.println("FirstResultCellValue    "+FirstResultCellValue);
				
				if (!FirstResultCellValue.contains(IssueIDvalue)){
					
					Thread.sleep(1000);
					fnGet_OR_IM_ObjectX("SearchIssue_issue_id_text_field").clear();
					waitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_IM.getProperty("SearchIssue_issue_id_text_field")))).isEnabled();
					fnWait_and_Type("SearchIssue_issue_id_text_field", IssueIDvalue);
					waitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_IM.getProperty("SearchIssue_search_bttn")))).isEnabled();					
					fnWait_and_Click("SearchIssue_search_bttn");
					
				}
				
				if (FirstResultCellValue.contains(IssueIDvalue)){
					break;
				}
	
			}
		}catch(Throwable t){
				fnApps_Report_Logs("FAILED == Issue ID Search is getting failed."+". Getting Exception  >> "+Throwables.getStackTraceAsString(t).trim());
				throw new Exception("FAILED == Issue ID Search is getting failed.");
				
			}
		
								
				
		String SearchResult_IssueIDGreen_link = IssueIDvalue; /// issue id need to change as GREEN
		String SearchResult_IssueIDGreen_link_Xpath = ".//a[contains(text(),'" + SearchResult_IssueIDGreen_link + "')]";
		
		fnLoading_wait();	
		waitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SearchResult_IssueIDGreen_link_Xpath))).isEnabled();
		driver.findElement(By.xpath(SearchResult_IssueIDGreen_link_Xpath)).click();
		fnApps_Report_Logs("PASSED == Successfully click on the element having xpath >> SearchResult_IssueID_link_Xpath");	
			
	}catch(NoSuchWindowException W){
		isTestCasePass = false;
			throw new Exception(W.getMessage());			}
	catch(Throwable t) {
			isTestCasePass = false;
			fnTake_Screen_Shot(IssueIDvalue);
			fnApps_Report_Logs("FAILED == Unable to Search IssueID and also navigate to ViewIssue Summary Details page --> ["+IssueIDvalue +" ]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(t).trim());
			throw new Exception("FAILED == Unable to Search IssueID and also navigate to ViewIssue Summary Details page  --> ["+IssueIDvalue +" ] Screen Shot[ "+IssueIDvalue+ fnScreenShot_Date_format()+" ]");
		}
								
}
	

	






//Search IssueID and also navigate to ViewIssue Summary Details page ---- Satya Pal
public static void fnEscalation_Verify_First_IssueClosed_after_EscalationClosed(String IssueStatusXpathKey, String IssueIDvalue, String VerifyTEXTXpathKey) throws Exception {
	try{
		WebDriverWait waitvar = new WebDriverWait(driver, Long.parseLong(CONFIG.getProperty("ElementWaitTime")));				
				
		fnSearchIssue_Ajax_Link_Click();
		
		
		//Waiting for Footer till page load
		fnLoading_wait();
		waitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_IM.getProperty("footer")))).isEnabled();
				

		fnLoading_wait();	
		fnGet_OR_IM_ObjectX("SearchIssue_search_bttn");
		waitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_IM.getProperty("SearchIssue_search_bttn")))).isEnabled();
						
		
		fnLoading_wait();
		waitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_IM.getProperty("SearchIssue_issue_id_text_field")))).isEnabled();
		driver.findElement(By.xpath(OR_IM.getProperty("SearchIssue_issue_id_text_field"))).sendKeys(IssueIDvalue);
		
		
		
		fnLoading_wait();	
		waitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_IM.getProperty("SearchIssue_search_bttn")))).isEnabled();
		driver.findElement(By.xpath(OR_IM.getProperty("SearchIssue_search_bttn"))).click();
		
						
						
		//	Thread.sleep(5000);
				
		try{
			for(int start=0; start<Long.parseLong(CONFIG.getProperty("ElementWaitTime")); start++){
				Thread.sleep(1000);
					
				fnLoading_wait();
				waitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_IM.getProperty("SearchIssue_Table_1st_Cell")))).isEnabled();
				String FirstResultCellValue= fnGet_OR_IM_ObjectX("SearchIssue_Table_1st_Cell").getText().trim();
			
				
				if (!FirstResultCellValue.contains(IssueIDvalue)){
					
					Thread.sleep(1000);
					fnGet_OR_IM_ObjectX("SearchIssue_issue_id_text_field").clear();
					waitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_IM.getProperty("SearchIssue_issue_id_text_field")))).isEnabled();
					driver.findElement(By.xpath(OR_IM.getProperty("SearchIssue_issue_id_text_field"))).sendKeys(IssueIDvalue);
					
					waitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_IM.getProperty("SearchIssue_search_bttn")))).isEnabled();					
					driver.findElement(By.xpath(OR_IM.getProperty("SearchIssue_search_bttn"))).click();
					
				}
				
				if (FirstResultCellValue.contains(IssueIDvalue)){
					break;
				}
	
			}
		}catch(Throwable t){
			isTestCasePass = false;
			fnTake_Screen_Shot(IssueIDvalue);
				fnApps_Report_Logs("FAILED == Issue ID Search is getting failed, Please refer Screen shot [ "+IssueIDvalue+ fnScreenShot_Date_format()+" ]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(t).trim());
				throw new Exception("FAILED == Issue ID Search is getting failed.");
				
			}
						
		try{
		


			//Waiting for Footer till page load
			fnLoading_wait();
			waitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_IM.getProperty("footer")))).isEnabled();
			//fnGet_OR_IM_ObjectX("footer");
			
			fnLoading_wait();
			waitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_IM.getProperty(IssueStatusXpathKey)))).isEnabled();
			WebElement IssueStatus=driver.findElement(By.xpath(OR_IM.getProperty(IssueStatusXpathKey)));
			
			Actions act = new Actions(driver);
			act.moveToElement(IssueStatus).doubleClick().build().perform();
			Thread.sleep(500);
			
			String IssueStatus_text=IssueStatus.getText().trim();
	
			
			assert IssueStatus_text.contains(VerifyTEXTXpathKey):"IssueID ("+IssueIDvalue+") is not CLOSED. Issue Status is coming as ( "+IssueStatus_text+" )";
			fnApps_Report_Logs("PASSED == IssueID ("+IssueIDvalue+") is successfully Verified and It is "+VerifyTEXTXpathKey);
	
		}catch(Throwable t) {
			isTestCasePass = false;
			fnTake_Screen_Shot("IssueIdStatusNotClosed");
			fnApps_Report_Logs("FAILED == IssueID ("+IssueIDvalue+") is successfully Verified and It is not "+VerifyTEXTXpathKey+", Please refer screen shot"+"IssueIdStatusNotClosed" + fnScreenShot_Date_format()+". Getting Exception  >> "+Throwables.getStackTraceAsString(t).trim());
			throw new Exception("FAILED == IssueID ("+IssueIDvalue+") is successfully Verified and It is not "+VerifyTEXTXpathKey+", Please refer screen shot"+"IssueIdStatusNotClosed" + fnScreenShot_Date_format());
		}
	}catch(NoSuchWindowException W){
		isTestCasePass = false;
		throw new Exception(W.getMessage());			}		
						
}
	






//Search IssueID and also navigate to ViewIssue Summary Details page ---- Satya Pal
public static void fnEscalation_Verify_IssueClosed_AfterFirstIssueVerification_after_EscalationClosed(String IssueStatusXpathKey, String IssueIDvalue, String VerifyTEXTXpathKey) throws Exception {
		try{
			WebDriverWait waitvar = new WebDriverWait(driver, Long.parseLong(CONFIG.getProperty("ElementWaitTime")));				
		
				
	
						fnGet_OR_IM_ObjectX("SearchIssue_issue_id_text_field").clear();
						
						fnLoading_wait();
						waitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_IM.getProperty("SearchIssue_issue_id_text_field"))));
						driver.findElement(By.xpath(OR_IM.getProperty("SearchIssue_issue_id_text_field"))).sendKeys(IssueIDvalue);
						
						
						fnLoading_wait();	
						waitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_IM.getProperty("SearchIssue_search_bttn"))));
						driver.findElement(By.xpath(OR_IM.getProperty("SearchIssue_search_bttn"))).click();
						
						
						
						//	Thread.sleep(5000);
						
				try{
					for(int start=0; start<Long.parseLong(CONFIG.getProperty("ElementWaitTime")); start++){
						Thread.sleep(1000);
							
						fnLoading_wait();
						waitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_IM.getProperty("SearchIssue_Table_1st_Cell")))).isEnabled();
						String FirstResultCellValue= fnGet_OR_IM_ObjectX("SearchIssue_Table_1st_Cell").getText().trim();
					
						
						if (!FirstResultCellValue.contains(IssueIDvalue)){
							
							Thread.sleep(1000);
							fnGet_OR_IM_ObjectX("SearchIssue_issue_id_text_field").clear();
							waitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_IM.getProperty("SearchIssue_issue_id_text_field")))).isEnabled();
							driver.findElement(By.xpath(OR_IM.getProperty("SearchIssue_issue_id_text_field"))).sendKeys(IssueIDvalue);
							
							waitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_IM.getProperty("SearchIssue_search_bttn")))).isEnabled();					
							driver.findElement(By.xpath(OR_IM.getProperty("SearchIssue_search_bttn"))).click();
							
						}
						
						if (FirstResultCellValue.contains(IssueIDvalue)){
							break;
						}
			
					}
				}catch(Throwable t){
					isTestCasePass = false;
					fnTake_Screen_Shot(IssueIDvalue);
						fnApps_Report_Logs("FAILED == Issue ID Search is getting failed, Please refer Screen shot [ "+IssueIDvalue+ fnScreenShot_Date_format()+" ]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(t).trim());
						throw new Exception("FAILED == Issue ID Search is getting failed.");
						
					}
						
				try{
					


					//Waiting for Footer till page load
					fnLoading_wait();
					waitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_IM.getProperty("footer")))).isEnabled();
					//fnGet_OR_IM_ObjectX("footer");
					
					fnLoading_wait();
					waitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_IM.getProperty(IssueStatusXpathKey)))).isEnabled();
					WebElement IssueStatus=driver.findElement(By.xpath(OR_IM.getProperty(IssueStatusXpathKey)));
					
					Actions act = new Actions(driver);
					act.moveToElement(IssueStatus).doubleClick().build().perform();
					Thread.sleep(500);
					
					String IssueStatus_text=IssueStatus.getText().trim();
			
					
					assert IssueStatus_text.contains(VerifyTEXTXpathKey):"IssueID ("+IssueIDvalue+") is not CLOSED. Issue Status is coming as ( "+IssueStatus_text+" )";
					fnApps_Report_Logs("PASSED == IssueID ("+IssueIDvalue+") is successfully Verified and It is "+VerifyTEXTXpathKey);
			
			}catch(Throwable t) {
				isTestCasePass = false;
					fnTake_Screen_Shot("IssueIdStatusNotClosed");
					fnApps_Report_Logs("FAILED == IssueID ("+IssueIDvalue+") is successfully Verified and It is not "+VerifyTEXTXpathKey+"Please refer screen shot"+"IssueIdStatusNotClosed" + fnScreenShot_Date_format()+". Getting Exception  >> "+Throwables.getStackTraceAsString(t).trim());
					throw new Exception("FAILED == IssueID ("+IssueIDvalue+") is successfully Verified and It is not "+VerifyTEXTXpathKey+"Please refer screen shot"+"IssueIdStatusNotClosed" + fnScreenShot_Date_format());
			}
		}catch(NoSuchWindowException W){
			isTestCasePass = false;
					throw new Exception(W.getMessage());			}				
}











		
//Escalation Verify "No records found" Message coming for IssueID when Escalation is Closed. ---- Satya Pal
public static void fnEscalation_Verify_IssueClosed_after_EscalationClosed(String IssueStatusXpathKey, String IssueIDvalue) throws Exception {
		
				try{
						WebDriverWait waitvar = new WebDriverWait(driver, Long.parseLong(CONFIG.getProperty("ElementWaitTime")));


						//Waiting for Footer till page load
						fnLoading_wait();
						waitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_IM.getProperty("footer")))).isEnabled();
						//fnGet_OR_IM_ObjectX("footer");
						
						fnLoading_wait();
						waitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_IM.getProperty(IssueStatusXpathKey)))).isEnabled();
						WebElement IssueStatus=driver.findElement(By.xpath(OR_IM.getProperty(IssueStatusXpathKey)));
						
						Actions act = new Actions(driver);
						act.moveToElement(IssueStatus).doubleClick().build().perform();
						
						String IssueStatus_text=IssueStatus.getText().trim();
						System.out.println("IssueStatus_text  ..... "+IssueStatus_text);
						
						assert IssueStatus_text.contains("CLOSED"):"IssueID ("+IssueIDvalue+") is not CLOSED. Issue Status is coming as ( "+IssueStatus_text+" )";
						fnApps_Report_Logs("PASSED == IssueID ("+IssueIDvalue+") is successfully CLOSED.");
				
				}catch(NoSuchWindowException W){
					isTestCasePass = false;
					throw new Exception(W.getMessage());			}
				catch(Throwable t) {
					isTestCasePass = false;
						fnTake_Screen_Shot("ErrorMsgNotComing" + IssueStatusXpathKey);
						fnApps_Report_Logs("FAILED == IssueID ("+IssueIDvalue+") is not CLOSED."+"Please refer screen shot"+"ErrorMsgNotComing" + IssueStatusXpathKey+fnScreenShot_Date_format()+". Getting Exception  >> "+Throwables.getStackTraceAsString(t).trim());
						throw new Exception("IssueID ("+IssueIDvalue+") is not CLOSED, Please refer screenshot [ "+"ErrorMsgNotComing"+IssueStatusXpathKey + fnScreenShot_Date_format() + " ]");
				}
		}
	
		
		














//Search Escalation and also navigate to ViewEscalation EscalationDetails page ---- Satya Pal
public static void fnEscalation_SearchEscalation_and_Navigate_till_ViewEscalationDetailsPageComes(String EscalationIDvalue) throws Exception {
					
	WebDriverWait waitvar = new WebDriverWait(driver, Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
					try{
						
													
						// Clicking on search mouse over
						fnLoading_wait();
						WebElement menu1 = fnGet_OR_IM_ObjectX("Menu"); 
						WebElement issue_management1=fnGet_OR_IM_ObjectX("Issue_Management");	
						WebElement create_issue1=fnGet_OR_IM_ObjectX("Create_Issue");
						WebElement Search_escalation=fnGet_OR_IM_ObjectX("Search_Escalations");
						Actions act = new Actions(driver);
						act.moveToElement(menu1).moveToElement(issue_management1).moveToElement(create_issue1).moveToElement(Search_escalation).click().build().perform();
								
						
						//Waiting for Footer till page load
						fnLoading_wait();
						waitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_IM.getProperty("footer")))).isEnabled();

									

						fnLoading_wait();	
						fnGet_OR_IM_ObjectX("SearchEscalation_search_bttn");
						waitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_IM.getProperty("SearchEscalation_search_bttn")))).isEnabled();
										
						
						fnLoading_wait();
						waitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_IM.getProperty("SearchEscalation_escalation_id_text_field")))).isEnabled();
						fnWait_and_Type("SearchEscalation_escalation_id_text_field", EscalationIDvalue);
						
						
						fnLoading_wait();	
						waitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_IM.getProperty("SearchEscalation_search_bttn")))).isEnabled();
						fnWait_and_Click("SearchEscalation_search_bttn");
						
						
						
						
						try{
							for(int start=0; start<Long.parseLong(CONFIG.getProperty("ElementWaitTime")); start++){
								Thread.sleep(1000);
									
								fnLoading_wait();
								waitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_IM.getProperty("SearchIssue_Table_1st_Cell")))).isEnabled();
								Thread.sleep(500);
								String FirstResultCellValue= fnGet_OR_IM_ObjectX("SearchIssue_Table_1st_Cell").getText().trim();
								System.out.println("FirstResultCellValue    "+FirstResultCellValue);
								
								if (!FirstResultCellValue.contains(EscalationIDvalue)){
									
									Thread.sleep(1000);
									fnGet_OR_IM_ObjectX("SearchEscalation_escalation_id_text_field").clear();
									waitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_IM.getProperty("SearchEscalation_escalation_id_text_field")))).isEnabled();
									fnWait_and_Type("SearchEscalation_escalation_id_text_field", EscalationIDvalue);
									waitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_IM.getProperty("SearchEscalation_search_bttn")))).isEnabled();					
									fnWait_and_Click("SearchEscalation_search_bttn");
									
								}
								
								if (FirstResultCellValue.contains(EscalationIDvalue)){
									break;
								}
					
							}
						}catch(StaleElementReferenceException se){
								fnApps_Report_Logs("FAILED == Issue ID Search is getting failed."+". Getting Exception  >> "+Throwables.getStackTraceAsString(se).trim());
								throw new Exception("FAILED == Issue ID Search is getting failed.");
								
							}
								
														
								
						String SearchResult_EscalationID_link = EscalationIDvalue; 
						String SearchResult_EscalationID_link_Xpath = ".//a[contains(text(),'" + SearchResult_EscalationID_link + "')]";
						
						fnLoading_wait();	
						waitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SearchResult_EscalationID_link_Xpath))).isEnabled();
						driver.findElement(By.xpath(SearchResult_EscalationID_link_Xpath)).click();
						fnApps_Report_Logs("PASSED == Successfully click on the element having xpath >> SearchResult_EscalationID_link_Xpath");	
						
					}catch(NoSuchWindowException W){
						isTestCasePass = false;
						throw new Exception(W.getMessage());			}
					catch(Throwable t) {
						isTestCasePass = false;
						fnTake_Screen_Shot(EscalationIDvalue);
						fnApps_Report_Logs("FAILED == Unable to Search Escalation and also navigate to ViewEscalation EscalationDetails page --> ["+EscalationIDvalue +" ]"+"Please refer screen shot"+EscalationIDvalue+fnScreenShot_Date_format()+". Getting Exception  >> "+Throwables.getStackTraceAsString(t).trim());
						throw new Exception("Unable to Search Escalation and also navigate to ViewEscalation EscalationDetails page  --> ["+EscalationIDvalue +" ] Screen Shot[ "+EscalationIDvalue+ fnScreenShot_Date_format()+" ]");
					}			
}
		

//Click on Save Button from ViewEscalationDetailsPage and verify updated message.  ---- Satya Pal
public static void fnEscalation_SaveButtonClick_and_VerifyUpdateMessage_ViewEscalationDetailsPage() throws Exception {
				try{
					WebDriverWait waitvar = new WebDriverWait(driver, Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
					
					// Clicking on save button
					fnWait_and_Click("Escalation_summary_details_save_button");
					
						fnLoading_wait();
						fnGet_OR_IM_ObjectX("Escalation_Summary_validation_error_msg");
						waitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_IM.getProperty("Escalation_Summary_validation_error_msg")))).isEnabled();
						WebElement validation_error_msg=driver.findElement(By.xpath(OR_IM.getProperty("Escalation_Summary_validation_error_msg")));
						
						Thread.sleep(500);
						validation_error_msg_text=validation_error_msg.getText();
						
						assert validation_error_msg_text.contains("Success"):"Escalation Summary details page is not updated, Getting Error message -- "+validation_error_msg_text+"Please refer screen shot"+validation_error_msg_text+fnScreenShot_Date_format();
						//fnApps_Report_Logs("Escalation Summary details page updated");
			
								
								
				}catch(NoSuchWindowException W){
					isTestCasePass = false;
					throw new Exception(W.getMessage());			}
				catch(Throwable t) {
					isTestCasePass = false;
								fnTake_Screen_Shot(validation_error_msg_text);
								fnApps_Report_Logs("FAILED == Escalation Summary details page is not updated, Getting Error message ----> ["+validation_error_msg_text +" ]"+"Please refer screen shot"+validation_error_msg_text+fnScreenShot_Date_format()+". Getting Exception  >> "+Throwables.getStackTraceAsString(t).trim());
								throw new Exception("Escalation Summary details page is not updated, Getting Error message ----> ["+validation_error_msg_text +" ], Please refer screen shot [ "+ validation_error_msg_text+fnScreenShot_Date_format()+" ]");
							}
										
				}		

		
		

//#########################################################################################################################################################################
//*************************************************************************   Method not using END  ***********************************************************************
//#########################################################################################################################################################################






//####################################################################################################################################################################################
//**************************************************************************  Configure Method ***************************************************************************************
//####################################################################################################################################################################################

//Function to Launch the browser
	public static boolean fnLaunchBrowserAndLogin() throws Throwable {
		boolean present;
		startExecutionTime = fnpTimestamp();
		ScreenShotFlagWithOR_IM = true;
		
		try {
			
			fns_Launch_Browser_Only();
			
			//TestSuiteBase_MonitorPlan.fnsIpulse_Login_Split_Excel_Urls("ipulse", CONFIG.getProperty("IM_LoginAs"), CONFIG.getProperty("adminUsername"), CONFIG.getProperty("adminPassword"));
			//Commented this function for SSO Switch User Implementation
			//fnsIpulse_Login_Split_Excel_Urls("secureipulse", CONFIG.getProperty("IM_LoginAs"), CONFIG.getProperty("adminUsername"), CONFIG.getProperty("adminPassword"));
			//Added this function for SSO Switch User Implementation
			//fnsIpulse_Login_Split_Excel_Urls("SSO_SwitchUser", CONFIG.getProperty("IM_LoginAs"), CONFIG.getProperty("MainUserForSSO_withCompleteEmailID"), CONFIG.getProperty("adminPassword"));
			fnsIpulse_Login_SSO("ipulse", CONFIG.getProperty("IM_LoginAs"), CONFIG.getProperty("adminUsername"), CONFIG.getProperty("adminPassword"));
			
			
			
			present = true;
					
			if (!veryFirstTimeAfterLogin) {
				veryFirstTimeAfterLogin=true;
			}
			if(ApplicationVersion_Flag){
				fnsIPulse_Application_Version("IssueMgt");
				ApplicationVersion_Flag = false;
			}
			
		
		}catch(NoSuchWindowException W){
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		}
		catch (Throwable t) {
			fnTake_Screen_Shot("LaunchBrowserAndLoginFail");
			isTestCasePass = false;
			present = false;
			fnApps_Report_Logs("");
			fnApps_Report_Logs("'Launch Browser And Login' is getting fail, Please refer screen shot [LaunchBrowserAndLoginFail"+fnScreenShot_Date_format()+"]."+" Getting Exception >> "+Throwables.getStackTraceAsString(t));
			throw new Exception("'Launch Browser And Login' is getting fail, Please refer screen shot [LaunchBrowserAndLoginFail"+fnScreenShot_Date_format()+"]."+" Getting Exception >> "+Throwables.getStackTraceAsString(t));
		}
		return present;
	}



//Check for Browser Type defined in Suits Excel 
public static void BrowserCheck(){
	
	for(int i=2; i <= suiteXls.getRowCount("Test Suite") ;i++ ){
		
		if(suiteXls.getCellData("Test Suite", "TSID", i).equalsIgnoreCase("IssueMgt_Suite")){
			BrowserName=suiteXls.getCellData("Test Suite", "Browser", i);
			
			if(BrowserName==""){
				BrowserName="IE";
			}
			
			break;
		}
	}
}



	
		
	
	

    // check if the suite has to be skip
	@BeforeSuite
	public void checkSuiteSkip() throws Throwable{
		/*currentSuiteName = "IssueMgt_Suite";
		TestSuiteBase_MonitorPlan.fnsMoveMousePointerAtCenterBottomOfScreen();
		fnInitialize();
		BrowserCheck();
			
		if(TestUtil.isSuiteRunnable(suiteXls, "IssueMgt_Suite")){
			FileUtils.deleteDirectory(new File((System.getProperty("user.dir") + CONFIG.getProperty("screenshots_path")+"//IssueMgt//")));
			browserName=TestUtil.getBrowserName(suiteXls, "IssueMgt_Suite");
			excelSiteURL= TestUtil.getExcelSiteURL(suiteXls, currentSuiteName);
			
			int rowNum = suiteXls.getCellRowNum("Test Suite", "TSID", currentSuiteName);
			suiteXls.setCellData("Test Suite", "Last_Execution", rowNum, fnReturnDateWithTimeForExcel());
			
		}
		
		if(!TestUtil.isSuiteRunnable(suiteXls, "IssueMgt_Suite")){
			fnApps_Report_Logs("Skipped IssueMgt_Suite as the runmode was set to NO");
			throw new SkipException("Runmode of IssueMgt_Suite set to no. So Skipping all tests in IssueMgt_Suite");
		}*/
		fns_CheckSiteSkip("IssueMgt_Suite");	
	}
	
	
	@AfterSuite(alwaysRun=true)
	public void Finishing_IM_Suite_Script() throws Throwable {
		ScreenShotFlagWithOR_IM = false;
		fnApps_Report_Logs("");
		fnApps_Report_Logs("############################################################# IM Suite END ############################################################ ");
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("");
	}	
	
	
	//############################################################# Functionality copied from Monitor Plan  ############################################################ 
	
	@SuppressWarnings("finally")
	public static boolean fns_Launch_Browser_Only() throws Throwable{
		boolean present = false;
		try{
			String ip_Address = fns_GetCurrentSystemIPAddress();
			
			NewNsfOnline_Element_Max_Wait_Time = Integer.parseInt("600");//(Long.parseLong(CONFIG.getProperty("ElementWaitTime")))*5;
			Listings_Element_Max_Wait_Time = Integer.parseInt("120");
			start = new Date();
			if (browserName.equalsIgnoreCase("IE")) {

				System.setProperty("webdriver.ie.driver", CONFIG.getProperty("IEDriverPath"));

				DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
				caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			//	caps.setCapability("IGNORE_ZOOM_SETTING", true);  // Not Working
			    caps.setCapability("IE_ENSURE_CLEAN_SESSION", true);
			    caps.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
			    caps.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
			    caps.setCapability(InternetExplorerDriver.NATIVE_EVENTS, true);
			    driver=new InternetExplorerDriver(caps);
			 //   fnsApps_Report_Logs("Browser type is IE");
			    present=true;
			 }
		
			
			if (browserName.equalsIgnoreCase("chrome")) {
				
				System.setProperty("webdriver.chrome.driver", CONFIG.getProperty("ChromeDriverPath"));
				
			//	DesiredCapabilities caps = DesiredCapabilities.chrome();
			//	ChromeOptions options = new ChromeOptions();
				caps = DesiredCapabilities.chrome();
				options = new ChromeOptions();
				
				if( ! ( (ip_Address.equalsIgnoreCase("192.168.102.40") ) ||  (ip_Address.equalsIgnoreCase("10.223.241.14"))  ||  (ip_Address.equalsIgnoreCase("10.223.241.15")) ||  (ip_Address.equalsIgnoreCase("10.223.241.16")) ) ){
					options.addArguments("--incognito");
				}
				
				options.addArguments("--disable-infobars");
				options.addArguments("--start-maximized");
			  //  options.addArguments("--disable-web-security"); // Blocking 3rd party cookies in listing and nsfconnectaccountsetup
			    options.addArguments("--no-proxy-server");
			    
			    //Working fine
			   /* options.addArguments(Arrays.asList("--no-sandbox","--ignore-certificate-errors","--homepage=about:blank","--no-first-run"));
			    options.addArguments("--test-type");
			    options.addArguments("disable-extensions");
			    options.addArguments("chrome.switches","--disable-extensions");
			    options.addArguments("--disable-notifications");*/
			   
			    Map<String, Object> prefs = new HashMap<String, Object>();
			    prefs.put("credentials_enable_service", false);
			    prefs.put("profile.password_manager_enabled", false);
			    options.setExperimentalOption("prefs", prefs);
			   
			    
				/*
				options.addArguments("enable-nacl");
				options.addArguments("nacl-broker");
				options.addArguments("nacl-gdb");
				options.addArguments("test-type");
				options.addArguments("nacl-loader");
				options.addArguments("nacl-loader-nonsfi");
				options.addArguments("v8-natives-passed-by-fd");
				options.addArguments("--start-maximized");
				options.addArguments("--disable-popup-blocking");
				*/
				
				//options.addArguments("--disable-captive-portal-bypass-proxy");
				//options.addArguments("--use-fake-ui-for-media-stream");
				//	options.addArguments("--bypass-app-banner-engagement-checks");
				//	options.addArguments("--disable-captive-portal-bypass-proxy");
				//	options.addArguments("--demo");
				//	options.addArguments("--override-plugin-power-saver-for-testing");
				//options.addArguments("--disable-datasaver-prompt");
				//	options.addArguments("--allow-cross-origin-auth-prompt");
				//options.addArguments("--disable-prompt-on-repost");
				//	options.addArguments("--disable-password-generation");
				//	options.addArguments("--local-heuristics-only-for-password-generation");
				//options.addArguments("--password-store");
				//options.addArguments("--disable-ios-password-suggestions");
				//options.addArguments("test-type");
				//	options.addArguments("--start-maximized");
				//	options.addArguments("-incognito"); // Not Working
				//	options.addArguments("system-developer-mode");	// Not Working
				//	options.addArguments("force-dev-mode-highlighting"); // Not Working
				//	options.addArguments("disable-about-in-settings ");
				//	options.addArguments("enable-tracing");
				//	options.addArguments("enable-tracing-output");
				//	options.addArguments("trace-startup");
				//	options.addArguments("trace-startup-duration");
				//	options.addArguments("trace-export-events-to-etw");
				//	options.addArguments("auto");
				//	options.addArguments("ash-debug-shortcuts ");
				//	options.addArguments("ui-show-paint-rects");
				//	options.addArguments("ui-show-replica-screenspace-rects");
				//	options.addArguments("--enable-text-input-focus-manager");
				//	options.addArguments("new-window");
				//	caps.setCapability(ChromeOptions.CAPABILITY, options);
				//	caps.setCapability("chrome.binary","C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
				//	caps.setCapability(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, true);
				//	caps.setCapability(CapabilityType.ENABLE_PROFILING_CAPABILITY, false );
				//	caps.setCapability(ChromeOptions.CAPABILITY, true);
				//	caps.setCapability("", true);
				//	enable-text-input-focus-manager 	// new-window  //allow-running-insecure-content		//test-name		//enable-nacl	//version		//nacl-broker
				
			    options.setExperimentalOption("useAutomationExtension", false);
		        options.setExperimentalOption("excludeSwitches", 
		        Collections.singletonList("enable-automation"));
			    
			    
				caps.setCapability(ChromeOptions.CAPABILITY, options);																																												
				
				driver=new ChromeDriver(caps);				
			    
			//	fnsApps_Report_Logs("Browser type is CHROME");
				 present=true;
			}
			
			
			
			
			
			if (browserName.equalsIgnoreCase("firefox")) {

				FirefoxProfile profile = new FirefoxProfile();
				DesiredCapabilities dc=DesiredCapabilities.firefox();
			//	profile.setAcceptUntrustedCertificates(false);
			//	profile.setAssumeUntrustedCertificateIssuer(true);
			//	profile.setPreference("browser.download.folderList", 2);
			//	profile.setPreference("browser.helperApps.alwaysAsk.force", false); 
				profile.setPreference("security.mixed_content.block_active_content", false);
				profile.setPreference("security.mixed_content.block_display_content", false); 
				dc = DesiredCapabilities.firefox();
				dc.setCapability(FirefoxDriver.PROFILE, profile);
				driver = new FirefoxDriver(dc);
			//	fnsApps_Report_Logs("Browser type is Firefox");
				 present=true;
			}
			
			if (browserName.equalsIgnoreCase("edge")) {
			
				System.setProperty("webdriver.edge.driver", System.getProperty("user.dir")+"\\driver\\msedgedriver_86.0.622.56.exe");
				
		        
		       /* DesiredCapabilities caps = DesiredCapabilities.edge();
		        EdgeOptions edgeOptions = new EdgeOptions();
				caps.setCapability("platform", Platform.WINDOWS);
				caps.setCapability("nativeEvents", false);
				caps.setCapability("applicationCacheEnabled", false);
				caps.setCapability("cssSelectorsEnabled", true);
				caps.setCapability("browserConnectionEnabled", true);
				caps.setCapability("javascriptEnabled", true);
				caps.setCapability("EDGE_ENSURE_CLEAN_SESSION", true);
				caps.setCapability("useAutomationExtension", false);
				caps.setCapability("excludeSwitches", Collections.singletonList("enable-automation"));
			
				edgeOptions.setCapability("useAutomationExtension", false);
				edgeOptions.setCapability("excludeSwitches", Collections.singletonList("enable-automation"));
				edgeOptions.setCapability("--disable-infobars", true);
		        edgeOptions.setCapability("--start-maximized", true);
		        edgeOptions.setCapability("--disable-web-security", true);
		        edgeOptions.setCapability("--no-proxy-server", true);
		        caps.setCapability(ChromeOptions.CAPABILITY, edgeOptions);	
			    driver = new EdgeDriver(edgeOptions);*/
			   
			    
			    driver = new EdgeDriver();
			    driver.manage().window().maximize();
			   
			    present=true;
			 }
			
		}catch(Throwable t){
			present = false;
			throw new Exception (Throwables.getStackTraceAsString(t));
		}finally{
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
	
public static void fnsIpulse_Login_Split_Excel_Urls(String PortalNamne, String LoginAs, String userName, String password) throws Throwable{
		
		try{
		//	iPulse_SecureLogin_Flag=true
			//Commented because of SSO Switch User Implementation
			/*if( !(PortalNamne.toLowerCase().trim().equalsIgnoreCase("ipulse_newnsfonline")) ){
				if ( (LoginAs.toUpperCase().trim().equalsIgnoreCase("JHUGHES")) || ( CONFIG.getProperty("iPulse_SecureLogin_Flag").toLowerCase().trim().equalsIgnoreCase("true") )){
					PortalNamne = "secureipulse";
				}else{
					PortalNamne = "ipulse";
				}
			}*/
			String siteUrl = null;
			if( (PortalNamne.toLowerCase().trim()).equalsIgnoreCase("ipulse")){
				
				if (excelSiteURL!=null) {
					if (excelSiteURL.equalsIgnoreCase("")) {
						siteUrl = CONFIG.getProperty("testSiteName");
					} else {
						
						String SplitExcelUrlValue = excelSiteURL.split("SecureiPulse:")[0];
						
						String iPulseSiteUrl = SplitExcelUrlValue.split("iPulse:")[1].trim();
						System.out.println(iPulseSiteUrl+" = "+iPulseSiteUrl);			
						siteUrl=iPulseSiteUrl;
						
					}
				} else {
					siteUrl = CONFIG.getProperty("testSiteName");
				}
			}	
			
			//Condition has been added for SSO Switch User Implementation
			if( (PortalNamne.toLowerCase().trim()).equalsIgnoreCase("SSO_SwitchUser")){
				if (excelSiteURL!=null) {
					if (excelSiteURL.equalsIgnoreCase("")) {
						siteUrl = CONFIG.getProperty("testSiteName");
					} else {
						
						String SplitExcelUrlValue = excelSiteURL.split("SecureiPulse:")[0];
						
						String iPulseSiteUrl = SplitExcelUrlValue.split("iPulse:")[1].trim();
						System.out.println(iPulseSiteUrl+" = "+iPulseSiteUrl);			
						siteUrl=iPulseSiteUrl;
						
					}
				} else {
					siteUrl = CONFIG.getProperty("testSiteName");
				}
			}
			
			if( (PortalNamne.toLowerCase().trim()).equalsIgnoreCase("secureipulse")){
				
				if (excelSiteURL!=null) {
					if (excelSiteURL.equalsIgnoreCase("")) {
						siteUrl = CONFIG.getProperty("SecuretestSiteName");
					} else {
						
						String iPulseSecureSiteUrl = excelSiteURL.split("SecureiPulse:")[1].trim();
						System.out.println();
						System.out.println(iPulseSecureSiteUrl+" = "+iPulseSecureSiteUrl);			
						siteUrl=iPulseSecureSiteUrl;
						
					}
				} else {
					siteUrl = CONFIG.getProperty("SecuretestSiteName");
				}
			}	
			
			
			if( (PortalNamne.toLowerCase().trim()).equalsIgnoreCase("ipulse_newnsfonline")){	
				//LoginAs = CONFIG.getProperty("Client_LoginAs");
				LoginAs = CONFIG.getProperty("Grip_LoginAs");
				 if (env.toLowerCase().equalsIgnoreCase("test")){
					siteUrl = "https://testapps.nsf.org/ecap/jsp/login/securelogin.xhtml";
					//siteUrl = "https://testapps.nsf.org/ecap/";
				  }else if(env.toLowerCase().equalsIgnoreCase("staging")){
					//  siteUrl = "https://stgapps.nsf.org/ecap/jsp/login/securelogin.xhtml";
					  siteUrl  = "https://stgapps.nsf.org/ecap/";
				  }
			}
			
			  fnApps_Report_Logs("Application credentials are URL[ "+siteUrl+" ], UserName[ "+userName+" ] & Password[ "+password+" ]");
			driver.get(siteUrl);
			  
			//below line related to SSO Switch User Implementation
			fnsComman_BrowserLogin_ElementWait(OR_IM.getProperty("SSO_UserName"), CONFIG.getProperty("Element_MAX_WaitTime"));
			//Condition has been added for SSO Switch User Implementation
			if(PortalNamne.toLowerCase().trim().equalsIgnoreCase("SSO_SwitchUser")){
				WithOut_OR_fnGet_ObjectX(OR_CLNT.getProperty("SSO_UserName")).sendKeys(userName);
				Thread.sleep(5000);
				WithOut_OR_fnGet_ObjectX(OR_CLNT.getProperty("SSO_NexButton")).click();
				Thread.sleep(5000);
				Runtime.getRuntime().exec(System.getProperty("user.dir") + "\\AutoIt_scripts\\sso_chrome.exe");
				//Thread.sleep(10000);
			}
			//fnsComman_BrowserLogin_ElementWait(OR_IM.getProperty("UserName"), CONFIG.getProperty("Element_MAX_WaitTime"));
			/*if( ((PortalNamne.toLowerCase().trim()).equalsIgnoreCase("secureipulse")) || ( ((PortalNamne.toLowerCase().trim()).equalsIgnoreCase("ipulse_newnsfonline")) && ((siteUrl.toLowerCase().trim()).contains("securelogin")))){
				
				WithOut_OR_fnGet_ObjectX(OR_IM.getProperty("LoginAs")).clear();
				WithOut_OR_fnGet_ObjectX(OR_IM.getProperty("LoginAs")).sendKeys("");
				WithOut_OR_fnGet_ObjectX(OR_IM.getProperty("LoginAs")).sendKeys(LoginAs);
			}	
		
			WithOut_OR_fnGet_ObjectX(OR_IM.getProperty("UserName")).clear();
			WithOut_OR_fnGet_ObjectX(OR_IM.getProperty("UserName")).sendKeys("");
			WithOut_OR_fnGet_ObjectX(OR_IM.getProperty("UserName")).sendKeys(userName);
			WithOut_OR_fnGet_ObjectX(OR_IM.getProperty("password")).sendKeys("");
			WithOut_OR_fnGet_ObjectX(OR_IM.getProperty("password")).sendKeys(password);
			WithOut_OR_fnGet_ObjectX(OR_IM.getProperty("Login")).click();
			*/
			
			fnsLoading_Progressingwait(20);
			//Thread.sleep(10000);
			
			/**
			 * Handling acknowledge alert, just coming after login 
			 */
			WithOut_OR_fnGet_ObjectX(OR_IM.getProperty("xpathForAck")).click();
			Thread.sleep(3000);
			switchUser(LoginAs);
			for(int i=0; i<=Long.parseLong(CONFIG.getProperty("Element_MAX_WaitTime")); i++){
			
				if(i==Long.parseLong(CONFIG.getProperty("Element_MAX_WaitTime"))){
					throw new Exception("After Login, Home Page is not getting load after <"+Long.parseLong(CONFIG.getProperty("Element_MAX_WaitTime"))+">seconds wait.");
				}else{
					Thread.sleep(1000);
				}
				
			if(driver.findElements(By.xpath(OR_IM.getProperty("Login_errorMessage"))).size()>0){
					String ErrorMsg = WithOut_OR_fnGet_ObjectX(OR_IM.getProperty("Login_errorMessage")).getText();
					throw new Exception("I-pulse Login Failed, Application Error <"+ErrorMsg+">");
				}else{
					fnsComman_BrowserLogin_ElementWait(OR_IM.getProperty("logOutBtnByText"), CONFIG.getProperty("Element_MAX_WaitTime"));
					break;
				}
				
			}
		}catch (Throwable t) {
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
}
//Function to wait for element Visible
	public static void fnsComman_BrowserLogin_ElementWait(String XpathKeyWithoutOR, String WaitTime) throws Exception {
			try{
					
				WebDriverWait elementwaitvar1 = new WebDriverWait(driver,Long.parseLong(WaitTime));
				elementwaitvar1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XpathKeyWithoutOR))).isEnabled();
			//	fnsApps_Report_Logs("PASSED == Element is Visible having Xpath  >> "+XpathKeyWithoutOR);	
			}catch(NoSuchWindowException W){
				//isTestCasePass = false;
				throw new Exception(W.getMessage());			
			}catch(Throwable e){
				//	isTestCasePass = false;
				//	fnsApps_Report_Logs("FAILED == Element is not Visible having Xpath >>"+XpathKeyWithoutOR+" , Getting Exception >> "+Throwables.getStackTraceAsString(e));
					throw new Exception("FAILED == Element is not Visible having Xpath >>"+XpathKeyWithoutOR+" , Getting Exception >> "+Throwables.getStackTraceAsString(e));						
			}
	}
	
	//Function of loading image appear on the screen (Block UI). 
	public static void fnsLoading_Progressingwait(int waitcount) throws Throwable{
		try{
			LoadingImageFlag = false;
			Actual_Loading_Time = 1;
			Loading_Image_Xpath = null;
			PageSourceText = null;
			String Loading_Classes_From_OR = OR_IM.getProperty("Loading_Progressing_New").trim();
			for(int wait=2; wait<=((Long.parseLong(CONFIG.getProperty("Element_MAX_WaitTime")))); wait++){
				if(!LoadingImageFlag){
					Thread.sleep(500);
					for(int i=0; i<Loading_Classes_From_OR.split("&&").length; i++){
						Loading_Image_Xpath = Loading_Classes_From_OR.split("&&")[i].trim();
						try{
							if(driver.findElements(By.xpath(Loading_Image_Xpath)).size()>1){
							//	fnsApps_Report_Logs("Xpath 1 = "+Loading_Image_Xpath+"..... Size = "+driver.findElements(By.xpath(Loading_Image_Xpath)).size());
								for(int display=1; display<=driver.findElements(By.xpath(Loading_Image_Xpath)).size(); display++){
									String Loading_Image_Xpath_Display = "("+Loading_Image_Xpath+")["+display+"]";
									if(driver.findElement(By.xpath(Loading_Image_Xpath_Display)).isDisplayed()){
										Loading_Image_Xpath = Loading_Image_Xpath_Display;
										break;
									}
								}
							}else if(driver.findElements(By.xpath(Loading_Image_Xpath)).size()>0){
							//	fnsApps_Report_Logs("Xpath 0 = "+Loading_Image_Xpath+"..... Size = "+driver.findElements(By.xpath(Loading_Image_Xpath)).size());
								if(driver.findElement(By.xpath(Loading_Image_Xpath)).isDisplayed()){
									break;
								}
							}
						}catch(Throwable t){ /*/nothing to do*/	}	
					}
				}
				
				
				if(Loading_Image_Xpath.contains("ui-messages-error-summary")){
					PageSourceText = driver.getPageSource().toLowerCase();
					if(PageSourceText.contains("ui-messages-error-summary")){		
						if (driver.findElement(By.xpath(Loading_Image_Xpath)).isDisplayed()) {
							WebElement Element = driver.findElement(By.xpath(Loading_Image_Xpath));
							Actions act = new Actions(driver);
							act.moveToElement(Element).build().perform();
							ErrorMsgText = driver.findElement(By.xpath(Loading_Image_Xpath)).getText().trim();
							throw new IllegalArgumentException();
						}else { // added on 4.4.2018
							fnApps_Report_Logs("*********  Pause/Wait is for < "+Actual_Loading_Time+" > seconds.  *****  ");//To increase performance, Pause/Wait time can set to 1 sec
							break;
						}
					}
				}
				
				
				try{
					if(driver.findElement(By.xpath(Loading_Image_Xpath)).isDisplayed()){
						Thread.sleep(1000);
						LoadingImageFlag = true;
					}
				}catch( Throwable n){  Thread.sleep(250); }
				
				
				
				try{	
					if(  !(driver.findElement(By.xpath(Loading_Image_Xpath)).isDisplayed()) && wait > waitcount &&  LoadingImageFlag == true ){
						Loading_Xpath_Array.add(Loading_Image_Xpath+"&&");
						fnApps_Report_Logs("*********  Progressing Loading image is displayed for < "+(Actual_Loading_Time)+" > seconds.");
						break;	
					}else if(  !(driver.findElement(By.xpath(Loading_Image_Xpath)).isDisplayed()) && wait > waitcount &&  LoadingImageFlag == false ){
						fnApps_Report_Logs("*********  Pause/Wait is for < "+Actual_Loading_Time+" > seconds.  *****  ");
						break;
					}else if(  wait < waitcount &&  LoadingImageFlag == false ){
						Thread.sleep(250); //differ from new nsfonline
					}
				}catch(Throwable n){
					if(  wait > waitcount ){
						if( LoadingImageFlag == true ){
							Loading_Xpath_Array.add(Loading_Image_Xpath+"&&");
							fnApps_Report_Logs("*********  Progressing Loading image is displayed for < "+(Actual_Loading_Time)+" > seconds.");
							break;	
						}else if( LoadingImageFlag == false ){
							fnApps_Report_Logs("*********  Pause/Wait is for < "+Actual_Loading_Time+" > seconds.  *****  ");
							break;
						}
					}else { //differ from new nsfonline
						if( LoadingImageFlag == false ){
							Thread.sleep(250); //differ from new nsfonline
						}else{
							Loading_Xpath_Array.add(Loading_Image_Xpath+"&&");
							fnApps_Report_Logs("*********  Progressing Loading image is displayed for < "+(Actual_Loading_Time)+" > seconds.");
							break;	
						}
					}
				}
							
				if(wait==(Long.parseLong(CONFIG.getProperty("Element_MAX_WaitTime")))){
					throw new InterruptedException("Loading Issue : After < "+(Long.parseLong(CONFIG.getProperty("Element_MAX_WaitTime")))+" > Seconds wait page is not getting Load, please refer screenshot >> PageLoadFail" + fnScreenShot_Date_format() +"*****  < "+Loading_Image_Xpath+" >");
				}
				Actual_Loading_Time++;
			}
					
			/*PageSourceText = driver.getPageSource().toLowerCase();
			/if(PageSourceText.contains("ui-messages-error-summary")){		
				if (driver.findElement(By.xpath(Loading_Image_Xpath)).isDisplayed()) {
					WebElement Element = driver.findElement(By.xpath(Loading_Image_Xpath));
					Actions act = new Actions(driver);
					act.moveToElement(Element).build().perform();
					ErrorMsgText = driver.findElement(By.xpath(Loading_Image_Xpath)).getText().trim();
					throw new IllegalArgumentException();
				}
			}*/
			PageSourceText = driver.getPageSource().toLowerCase();
			if(PageSourceText.contains("we are sorry")){		
				ErrorMsgText = "We are sorry...an unexpected error has occurred. Details have been forwarded to the Application Support Team for investigation. If the problem persists, please submit an issue on the Apps Portal.";
				throw new IllegalArgumentException();
			}
		
				
		}catch(IllegalArgumentException i){
			//isTestCasePass = false;
			//TestSuiteBase_MonitorPlan.fnsTake_Screen_Shot_Without_OR("UnExpectedError");
			fnTake_Screen_Shot("UnExpectedError");
			//fnsApps_Report_Logs("Getting Un-Expected Application Error <"+ErrorMsgText+">, please refer screenshot >>UnExpectedError" + fnScreenShot_Date_format()+"*****  < "+Loading_Image_Xpath+" >"+"  Exception ("+Throwables.getStackTraceAsString(i));
			fnApps_Report_Logs("Getting Un-Expected Application Error <"+ErrorMsgText+">, please refer screenshot >>UnExpectedError" + fnScreenShot_Date_format()+"*****  < "+Loading_Image_Xpath+" >"+"  Exception ("+Throwables.getStackTraceAsString(i));
			throw new Exception("Getting Un-Expected Application Error <"+ErrorMsgText+">, please refer screenshot >> UnExpectedError" + fnScreenShot_Date_format()+"*****  < "+Loading_Image_Xpath+" >"+"  Exception ("+Throwables.getStackTraceAsString(i));	
		}catch(NoSuchElementException n){
			fnApps_Report_Logs("***** Progressing Loading image displayed for < "+Actual_Loading_Time+" > seconds. **************** NS");
			try{
				fnApps_Report_Logs("***** Progressing Loading image displayed for < "+Actual_Loading_Time+" > seconds. **************** NS");
				//fnsLoading_Progressing_wait(waitcount);			
			}catch(Throwable tt){
				isTestCasePass = false;
				//TestSuiteBase_MonitorPlan.fnsTake_Screen_Shot_Without_OR("PageLoadFail_NS");
				fnTake_Screen_Shot("PageLoadFail_NS");
				fnApps_Report_Logs(Throwables.getStackTraceAsString(tt));
				throw new Exception(Throwables.getStackTraceAsString(tt));		
			}
		}catch(StaleElementReferenceException n){
			fnApps_Report_Logs("***** Progressing Loading image displayed for < "+Actual_Loading_Time+" > seconds. **************** Stale");
		} catch(NoSuchWindowException W){
			throw new Exception(W.getMessage());
		}catch(InterruptedException ie){
			System.out.println("Interrupted-----Exception");
			//TestSuiteBase_MonitorPlan.fnsTake_Screen_Shot_Without_OR("PageLoadFail");
			fnTake_Screen_Shot("PageLoadFail");
			fnApps_Report_Logs(Throwables.getStackTraceAsString(ie));
			throw new Exception(Throwables.getStackTraceAsString(ie));		
		}catch(Throwable tt){
			//isTestCasePass = false;
			//TestSuiteBase_MonitorPlan.fnsTake_Screen_Shot_Without_OR("PageLoadFail");
			fnTake_Screen_Shot("PageLoadFail");
			fnApps_Report_Logs(Throwables.getStackTraceAsString(tt));
			throw new Exception(Throwables.getStackTraceAsString(tt));		
		}
	}
	public static String fnRemoveFormatting_for_FileName(String s) {

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
	//Function to find and return the object using Xpath
	public static String WithOut_OR_Return_XPATH_if_More_than_One_Coming(String WithoutOR_XpathKey) throws Exception{
			
		try {
			String Displayed_Element_Xpath = null;
			Integer Element_Size = driver.findElements(By.xpath(WithoutOR_XpathKey)).size();
			boolean Element_Displayed = false;
			
			for(int i=1; i<=Element_Size; i++){
				Displayed_Element_Xpath = "("+WithoutOR_XpathKey+")["+i+"]"; 
				WebElement Element = driver.findElement(By.xpath(Displayed_Element_Xpath));
				try{
					if(Element.isDisplayed()){
						Element_Displayed = true;
						break;
					}
				}catch(Throwable t){
					//nothin to do
				}
				
				if( i==Element_Size && Element_Displayed==false ){
					throw new Exception("<"+Element_Size+"> Element with xpath ["+WithoutOR_XpathKey +"] are present, but none of them is display.");
				}
			}
			System.out.println("Displayed_Element_Xpath = "+Displayed_Element_Xpath);
			if(Displayed_Element_Xpath==null){
				throw new Exception("Element with xpath ["+WithoutOR_XpathKey +"] is not exists.");
			}		
			return Displayed_Element_Xpath; 
			
		}catch(Throwable e){
			isTestCasePass = false;
			//fnsTake_Screen_Shot_Without_OR("NotPresent" + WithoutOR_XpathKey);
			fnTake_Screen_Shot("NotPresent");
			fnApps_Report_Logs("FAILED == Fetching Xpath fail >> "+WithoutOR_XpathKey + " ] , plz see screenshot [NotPresent" +  fnScreenShot_Date_format() + " ]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(e));
			throw new Exception("FAILED == Fetching Xpath fail >> "+WithoutOR_XpathKey + " ] , plz see screenshot [NotPresent" +  fnScreenShot_Date_format() + " ]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(e));}
	}
	//Function to click on lookup radio button ---- Satya Pal
	public static void fnsLookup_RadioBttn_Select() throws Exception {
		boolean TableSizeFlag = true;
		String lookup_title = "Lookup";	
		try{
			try{
				lookup_title = driver.findElement(By.xpath("//span[@id='mainForm:lkpDialog_title']")).getText().trim();
			}catch(Throwable t){
				//nothing to do
			}

			int beforeSearchRowCount = fnCount_table_Rows_BeforeTableRefresh("lookup_table_div");
						
			if(beforeSearchRowCount==1){
				fnsLoading_Progressingwait(1);
				driver.findElement(By.xpath(OR_IM.getProperty("lookup_select_radio_bttn"))).click();
				fnsLoading_Progressingwait(3);
				Thread.sleep(500);	
				fnApps_Report_Logs("PASSED == Successfully Select LookUp Radio Button");
				TableSizeFlag = false;
			}
			
			if(TableSizeFlag){
				fnsLoading_Progressingwait(1);
				driver.findElement(By.xpath(OR_IM.getProperty("lookup_search_bttn"))).click();
				fnsLoading_Progressingwait(3);
				int afterSearchRow = beforeSearchRowCount;
					
				for (int waits=0;beforeSearchRowCount == afterSearchRow; waits++) {
					afterSearchRow = fnCount_table_Rows_AfterTableRefresh("lookup_table_div");
					if (waits > (Long.parseLong(CONFIG.getProperty("Element_MAX_WaitTime")))){
						break;
					}
				}
				
				//fnsGet_Element_Enabled("lookup_select_radio_bttn");
				fnGet_Element_Enabled("lookup_select_radio_bttn");
				Thread.sleep(500);
				fnsLoading_Progressingwait(1);
					
				WithOut_OR_fnGet_ObjectX(OR_IM.getProperty("lookup_select_radio_bttn")).click();
				
				fnsLoading_Progressingwait(3);
				Thread.sleep(500);	
			}
			//fnsApps_Report_Logs("PASSED == Successfully Select "+lookup_title+"  Radio Button");
			fnApps_Report_Logs("PASSED == Successfully Select "+lookup_title+"  Radio Button");
		}catch(NoSuchWindowException W){
			isTestCasePass = false;
			throw new Exception(W.getMessage());			}
		catch(Throwable t) {
			isTestCasePass = false;
			//fnsTake_Screen_Shot_Without_OR("Lookup_Fail");
			fnTake_Screen_Shot("Lookup_Fail");
			fnApps_Report_Logs("FAILED == "+lookup_title+" : "+Throwables.getStackTraceAsString(t).trim()+", Please refer the screenshot >> Lookup_Fail"+ fnScreenShot_Date_format());
			throw new Exception("FAILED == "+lookup_title+" : "+Throwables.getStackTraceAsString(t).trim()+", Please refer the screenshot >> Lookup_Fail"+ fnScreenShot_Date_format());
			}
	}
	//Count the Total no rows in Table
	public static int WithOut_OR_Retrun_TotalRowCount_OfTable(String TableXpathKey) throws Exception {
		
		try{
			RowCount=0;
			WithOut_OR_fnGet_Element_Enabled(TableXpathKey);
			WebElement stdtable=WithOut_OR_fnGet_ObjectX(TableXpathKey);
			
			List<WebElement> no_of_rows_in_list = stdtable.findElements(By.tagName("tr"));
			
			for(WebElement countrows:no_of_rows_in_list){
				RowCount++;
			}
			RowCount--;
			fnApps_Report_Logs("PASSED == Succesfully retrun Total Row Count("+RowCount+") for table having xpaths >> "+TableXpathKey);
			return RowCount;
			
		}catch(NoSuchWindowException W){
			isTestCasePass = false;
			throw new Exception(W.getMessage());			}
		catch (Throwable t) {
			isTestCasePass = false;
			//fnsTake_Screen_Shot_Without_OR("CountRowFailFrom" + TableXpathKey);
			fnTake_Screen_Shot("CountRowFailFrom");// + TableXpathKey
			fnApps_Report_Logs("FAILED == Unable to count rows from  Table having xpath [ " +"CountRowFailFrom" + TableXpathKey + fnScreenShot_Date_format() +" ]."+". Getting Exception  >> "+Throwables.getStackTraceAsString(t).trim());
			throw new Exception("Unable to count rows from  Table having xpath [" + TableXpathKey + "],plz see screenshot [CountRowFailFrom" + TableXpathKey + fnScreenShot_Date_format() + "]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(t).trim());}
	}
	//function to select drop down value
	public static void WithOut_OR_fnDD_SelectValue_By_SelectClass(String dropdownXpath, String MatchingValue) throws Exception {
		
		try{
			WithOut_OR_fnGet_Element_Enabled(dropdownXpath);
			//Select Dropdown = new Select(WithOut_OR_fnGet_ObjectX(dropdownXpath));
			Select Dropdown = new Select(fnGet_OR_IM_ObjectX(dropdownXpath));
			Dropdown.selectByVisibleText(MatchingValue);
			
			fnApps_Report_Logs("PASSED == select value [ "+MatchingValue+" ] from drop down done, having xpath >> "+ dropdownXpath);
		}catch(NoSuchWindowException W){
			isTestCasePass = false;
			throw new Exception(W.getMessage());			}
		catch(Throwable t) {
			isTestCasePass = false;
			//fnsTake_Screen_Shot_Without_OR("DdValueSelectFailWOR");
			fnTake_Screen_Shot("DdValueSelectFailWOR");
			fnApps_Report_Logs("FAILED == Unable to select value[ "+MatchingValue+" ] from drop down [ " + dropdownXpath +" ],plz see screenshot [ DdValueSelectFailWOR" +  fnScreenShot_Date_format() + " ]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(t).trim());
			throw new Exception("FAILED == Unable to select value[ "+MatchingValue+" ] from drop down [ " + dropdownXpath +" ],plz see screenshot [ DdValueSelectFailWOR" +  fnScreenShot_Date_format() + " ]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(t).trim());}
	}
	//Function to click on Object Without OR
	public static void WithOut_OR_fnClick(String XpathKey) throws Exception {
		
		try{
			try{
				WithOut_OR_fnGet_ObjectX(XpathKey).click();
				fnApps_Report_Logs("PASSED == Click done on Element having Xpath >> "+XpathKey);
			}catch(Throwable tt){
				Thread.sleep(3000);
				WithOut_OR_fnGet_ObjectX(XpathKey).click();
				fnApps_Report_Logs("(((((( 2nd Attampt ))))))---PASSED == Click done on Element having Xpath >> "+XpathKey);
			}
		}catch(NoSuchWindowException W){
			isTestCasePass = false;
			throw new Exception(W.getMessage());			}
		catch(Throwable e){
			isTestCasePass = false;
			//fnsTake_Screen_Shot_Without_OR();
			fnTake_Screen_Shot("UnableToClick");
			fnApps_Report_Logs("FAILED == Unable To Click on Element having Xpath >> "+XpathKey+", plz see screenshot [ UnableToClick_" +  fnScreenShot_Date_format() + " ]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(e));
			throw new Exception("Unable To Click on element [ " + XpathKey + " ] , plz see screenshot [ UnableToClick_" +  fnScreenShot_Date_format() + " ]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(e));}
	}
	//Function to find and return the object using Xpath
	public static WebElement WithOut_OR_fnGet_ObjectX(String XpathKey) throws Exception{
				
		try {
			for (int waits=0; waits<Long.parseLong(CONFIG.getProperty("ElementWaitTime")); waits++) {
				
				if (driver.findElements(By.xpath(XpathKey)).size() > 0) {break;}
				else{Thread.sleep(500);}
				
			}return driver.findElement(By.xpath(XpathKey)); 
			
		}catch(StaleElementReferenceException e){
			WebDriverWait stalewaitvar = new WebDriverWait(driver, 10);
			stalewaitvar.until(ExpectedConditions.presenceOfElementLocated(By.xpath(XpathKey))); 
			WebElement webelemnt=driver.findElement(By.xpath(XpathKey)); 
			stalewaitvar.until(ExpectedConditions.stalenessOf(webelemnt));
			return driver.findElement(By.xpath(XpathKey));}
		catch(NoSuchWindowException W){
		//	isTestCasePass = false;
			throw new Exception(W.getMessage());			}
		catch(NoSuchElementException e){
		//	isTestCasePass = false;
			//fnTake_Screen_Shot_Without_OR("NoSuchElementException");
			fnTake_Screen_Shot("NoSuchElementException");
			fnApps_Report_Logs("FAILED == Element is not found >> "+XpathKey + " ] , plz see screenshot [NoSuchElementException" +   fnScreenShot_Date_format() + " ]");
			throw new Exception("FAILED == Element is not found >> "+XpathKey + " ] , plz see screenshot [NoSuchElementException" +  fnScreenShot_Date_format() + " ]");}
		catch(TimeoutException e){
		//	isTestCasePass = false;
			//fnsTake_Screen_Shot_Without_OR("TimeOut" + XpathKey);
			fnTake_Screen_Shot("TimeOut" + XpathKey);
			fnApps_Report_Logs("FAILED == Element is not Present, getting TimeOut >> "+XpathKey + " ] , plz see screenshot [TimeOut" +  fnScreenShot_Date_format() + " ]");
			throw new Exception("FAILED == Element is not Present, getting TimeOut >> "+XpathKey + " ] , plz see screenshot [TimeOut" +  fnScreenShot_Date_format() + " ]");}
		catch(Throwable e){
		//	isTestCasePass = false;
			//fnsTake_Screen_Shot_Without_OR("NotPresent" + XpathKey);
			fnTake_Screen_Shot("NotPresent" + XpathKey);
			fnApps_Report_Logs("FAILED == Element is not found >> "+XpathKey + " ] , plz see screenshot [NotPresent" +  fnScreenShot_Date_format() + " ]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(e));
			throw new Exception("FAILED == Element is not found >> "+XpathKey + " ] , plz see screenshot [NotPresent" +  fnScreenShot_Date_format() + " ]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(e));}
	}
	//Function move to element
			public static void WithOut_OR_fnMove_To_Element_BY_OFFSET(String XpathKey, Integer XX, Integer YY) throws Exception {
							
				try{
					Actions action=new Actions(driver);	
					WebElement MonitorPlanTypeElement=WithOut_OR_fnGet_ObjectX(XpathKey);
					action.moveToElement(MonitorPlanTypeElement, XX, YY).build().perform();
					fnApps_Report_Logs("PASSED == OffSet Move to Element having Xpath >> "+XpathKey);
				}catch(NoSuchWindowException W){
					isTestCasePass = false;
					throw new Exception(W.getMessage());			}
				catch(Throwable e){
					isTestCasePass = false;
					//fnsTake_Screen_Shot_Without_OR("ElementMoveFail");
					fnTake_Screen_Shot("ElementMoveFail");
					fnApps_Report_Logs("FAILED == Unable to MoveToElement, having Xpath  >> "+XpathKey+", plz see screenshot [ElementMoveFail " +  fnScreenShot_Date_format() + " ]. Getting Exception >> "+Throwables.getStackTraceAsString(e));
					throw new Exception("FAILED == Unable to MoveToElement, having Xpath  [ " + XpathKey + " ] , plz see screenshot [ElementMoveFail " + fnScreenShot_Date_format() + " ]. Getting Exception >> "+Throwables.getStackTraceAsString(e));}
	}
	//Function to wait for element and then type
	public static void WithOut_OR_fnMove_To_Element_and_DoubleClick(String XpathKey) throws Exception {
		try{
			Actions action=new Actions(driver);	
			WebElement MonitorPlanTypeElement=WithOut_OR_fnGet_ObjectX(XpathKey);
			action.moveToElement(MonitorPlanTypeElement).doubleClick().build().perform();
			fnApps_Report_Logs("PASSED == Double Click done on Element having Xpath >> "+XpathKey);
		}catch(NoSuchWindowException W){
			isTestCasePass = false;
			throw new Exception(W.getMessage());			
		}catch(Throwable e){
			isTestCasePass = false;
			//fnsTake_Screen_Shot_Without_OR("DoubleClickFail");
			fnTake_Screen_Shot("DoubleClickFail");
			fnApps_Report_Logs("FAILED == Unable to MoveToElement and Click, having Xpath  >> "+XpathKey+", plz see screenshot [DoubleClickFail" + fnScreenShot_Date_format() + "]. Getting Exception >> "+Throwables.getStackTraceAsString(e));
			throw new Exception("FAILED == Unable to MoveToElement and Click, having Xpath  [ " + XpathKey + " ] , plz see screenshot [DoubleClickFail"+ fnScreenShot_Date_format() + "]. Getting Exception >> "+Throwables.getStackTraceAsString(e));}
	}
	
	
	//Function to Clear Field
	public void fnsWait_and_Clear(String XpathKey) throws Exception {
		
		try{
			Thread.sleep(500);
			fnGet_OR_IM_ObjectX(XpathKey).clear();
			fnApps_Report_Logs("PASSED == Clear Element done, having Xpath  >> "+XpathKey);
		}catch(NoSuchWindowException W){
			isTestCasePass = false;
			throw new Exception(W.getMessage());			}
		catch(Throwable e){
			isTestCasePass = false;
			fnTake_Screen_Shot("UnableToClear_" + XpathKey);
			fnApps_Report_Logs("FAILED == Unable To performe Clear on Element having Xpath  >> "+XpathKey+", plz see screenshot [ UnableToClear_" + XpathKey + fnScreenShot_Date_format() + " ]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(e));
			throw new Exception("Unable To performe Clear on element [ " + XpathKey + " ] , plz see screenshot [ UnableToClear_" + XpathKey + fnScreenShot_Date_format() + " ]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(e));	}
	}
	
	
	
	//Function to Clear Field
	public static void WithOut_OR_fnClear(String XpathKey) throws Exception {
		
		try{
			WithOut_OR_fnGet_ObjectX(XpathKey).clear();
			fnApps_Report_Logs("PASSED == Clear done Element having Xpath  >> "+XpathKey);
		}catch(NoSuchWindowException W){
			isTestCasePass = false;
			throw new Exception(W.getMessage());			}
		catch(Throwable e){
			isTestCasePass = false;
			//fnsTake_Screen_Shot_Without_OR("UnableToClear_");
			fnTake_Screen_Shot("UnableToClearBcksp_");
			fnApps_Report_Logs("FAILED == Unable To performe Clear on Element having Xpath  >> "+XpathKey+", plz see screenshot [ UnableToClear_"+ fnScreenShot_Date_format() + " ]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(e));
			throw new Exception("Unable To performe Clear on element [ " + XpathKey + " ] , plz see screenshot [ UnableToClear_" + fnScreenShot_Date_format() + " ]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(e));	}
	}
	//Comman method to wirte pass/fail into excel/log file.	
		public static void fns_ReportTestResult_atClassLevel(Xls_Reader Suite_Excel_Variable_Name, String Class_Name) throws Throwable {

			int rowNum = Suite_Excel_Variable_Name.getCellRowNum("Test Cases", "TCID", Class_Name);
			Suite_Excel_Variable_Name.setCellData("Test Cases", "Last_Execution", rowNum, fnReturnDateWithTimeForExcel());
			if (isTestCasePass){
				TestUtil.reportDataSetResult(Suite_Excel_Variable_Name, "Test Cases", TestUtil.getRowNum(Suite_Excel_Variable_Name, Class_Name), "PASS");
				Suite_Excel_Variable_Name.setCellData("Test Cases", "Last_Successful_Execution", rowNum, fnReturnDateWithTimeForExcel());
				fnpElapsedTime( currentSuiteName, Class_Name,currentScriptCode,"PASS");
			}else{
				TestUtil.reportDataSetResult(Suite_Excel_Variable_Name, "Test Cases", TestUtil.getRowNum(Suite_Excel_Variable_Name, Class_Name), "FAIL");
				fnpElapsedTime( currentSuiteName, Class_Name,currentScriptCode,"FAIL");
			}
			fnApps_Report_Logs("=========================================================================================================================================");

			hashXlData.clear();
		}
		//Function to Take Screen Shot of I-Pulse Application Version
		public static void fnsIPulse_Application_Version(String message) throws Exception {
			try{
				WithOut_OR_fnGet_Element_Enabled(OR_IM.getProperty("Footer_IPulse"));
				Thread.sleep(1500);
				WithOut_OR_fnGet_Element_Enabled(OR_IM.getProperty("Ipulse_Version"));
				WebElement VersionWE=WithOut_OR_fnGet_ObjectX(OR_IM.getProperty("Ipulse_Version"));
			    Actions action = new Actions(driver);
			    action.moveToElement(VersionWE).sendKeys(VersionWE, Keys.CONTROL).click().build().perform();	
			    action.keyDown(Keys.CONTROL).build().perform();
			
				Thread.sleep(1500);
					
				FileUtils.forceMkdir(new File((System.getProperty("user.dir") +  screenshots_path+ "//applicationVersion//")));
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				Rectangle screenRectangle = new Rectangle(screenSize);
				Robot robot = new Robot();
				BufferedImage image = robot.createScreenCapture(screenRectangle);
				ImageIO.write(image, "png", new File((System.getProperty("user.dir") +  screenshots_path+"//applicationVersion//"+message+"_ApplicationVersion.PNG")));
				
				
				fnsWait_and_Click("Ipulse_Version_Dialog_Close_Bttn");
				Thread.sleep(1000);
			}catch(NoSuchWindowException W){
				isTestCasePass = false;
				throw new Exception(W.getMessage());			}
			catch(java.lang.NullPointerException n){
				fnApps_Report_Logs("ScreenShotNullPointerException >> "+n.getMessage());
				throw new Exception("NullPointerException Unable To take Screen Shots.");}
			 catch(java.io.IOException e){
				fnApps_Report_Logs("ScreenShotIOException >> "+e.getMessage());
				throw new Exception("IOException Unable To take Screen Shots.");}
		}
		//Function to wait for element Visible
	public static void WithOut_OR_fnGet_Element_Enabled(String XpathKey) throws Exception {
		try{
				for(int wait=0; wait<3; wait++){
				if(driver.findElements(By.xpath(XpathKey)).size()>0){
					WebDriverWait elementwaitvar = new WebDriverWait(driver,Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
					elementwaitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XpathKey)));
					
					WebDriverWait elementwaitvar1 = new WebDriverWait(driver,Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
					elementwaitvar1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XpathKey))).isEnabled();
							

					WebDriverWait elementwaitvar2 = new WebDriverWait(driver,Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
					elementwaitvar2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XpathKey))).isDisplayed();
							
					break;
				}else{
					throw new Exception();
				} // else loop closed
			} // for loop Closed
			fnApps_Report_Logs("PASSED == Element is Visible having Xpath  >> "+XpathKey);		
		}catch(NoSuchWindowException W){
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		}catch(TimeoutException to){
			throw new Exception ("TimeOut : "+Throwables.getStackTraceAsString(to));
		}catch(Throwable t){
			try{
				Thread.sleep(3000);
				WebDriverWait elementwaitvar3 = new WebDriverWait(driver, Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
				elementwaitvar3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XpathKey))).isEnabled();
				fnApps_Report_Logs("PASSED == Element is Visible in 2nd attampt having Xpath  >> "+XpathKey);		
			}catch(NoSuchWindowException W){
				isTestCasePass = false;
				throw new Exception(W.getMessage());			}
			catch(Throwable e){
				isTestCasePass = false;
				//fnsTake_Screen_Shot_Without_OR(XpathKey);
				fnTake_Screen_Shot(XpathKey);
				fnApps_Report_Logs("FAILED == Element is not Visible having Xpath  >> "+XpathKey+", plz see screenshot [ " + ""+XpathKey + fnScreenShot_Date_format() + " ]. Getting Exception >> "+Throwables.getStackTraceAsString(e));
				throw new Exception("FAILED == Element is not Visible having Xpath  [ " + XpathKey + " ] , plz see screenshot [ " + ""+XpathKey + fnScreenShot_Date_format() + " ]. Getting Exception >> "+Throwables.getStackTraceAsString(e));
			}
		}
	}
	//Function to click on Object
	public static void fnsWait_and_Click(String XpathKey) throws Exception {
		try{
			//fnsGet_OR_NsfOnline_ObjectX(XpathKey).click();
			fnGet_OR_IM_ObjectX(XpathKey).click();
			fnApps_Report_Logs("PASSED == Click on Element done having Xpath >> "+XpathKey);
		}catch(NoSuchWindowException W){
			isTestCasePass = false;
			throw new Exception(W.getMessage());			}	
		catch(StaleElementReferenceException e){
			isTestCasePass = false;
			fnTake_Screen_Shot("UnableToClick_" + XpathKey);
			fnApps_Report_Logs("FAILED == Unable To Click on Element having Xpath >> "+XpathKey+", plz see screenshot [ UnableToClick_" + XpathKey + fnScreenShot_Date_format() + " ]");
			throw new Exception("Unable To Click on element [ " + XpathKey + " ] , plz see screenshot [ UnableToClick_" + XpathKey + fnScreenShot_Date_format() + " ]");}
	}
	
	
	public void fnWait_and_Click_Through_JS(String Xpath_Without_OR) throws Exception{
		try{
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse = (JavascriptExecutor) driver;
			WebElement Webelements = driver.findElement(By.xpath(Xpath_Without_OR));
			jse.executeScript("arguments[0].click();", Webelements);
		
			fnApps_Report_Logs("PASSED == Click done on Element throught JS, having Xpath >> "+Xpath_Without_OR);
		}catch(Throwable t){
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}
	
	public void fnWait_and_Type_by_LabelName(String Field_Type_like_input_or_TextArea, String LabelName, String Value) throws Throwable{
		try{
			String field_Type = "";
			String Input_Field_Xpath = "";
			if(Field_Type_like_input_or_TextArea.contains("_")){
				field_Type = Field_Type_like_input_or_TextArea.split("_")[0].trim();
				Field_Type_like_input_or_TextArea = Field_Type_like_input_or_TextArea.split("_")[1].trim();
				Input_Field_Xpath = "//"+field_Type+"[text()='"+LabelName+"']/following::"+Field_Type_like_input_or_TextArea.toLowerCase()+"[1]";
			}else{
				Input_Field_Xpath = "//label[text()='"+LabelName+"']/following::"+Field_Type_like_input_or_TextArea.toLowerCase()+"[1]";
			}
			
		
			for(int i=0; i<=NewNsfOnline_Element_Max_Wait_Time; i++){
				try{
					if(driver.findElements(By.xpath(Input_Field_Xpath)).size()>0){
						break;
					}else{
						Thread.sleep(1000);
					}
				}catch(Throwable t){
					if(i==NewNsfOnline_Element_Max_Wait_Time){
						fnTake_Screen_Shot("Field_Not_Coming");
						throw new Exception("FAILED '"+LabelName+"' field is not coming, please refer the screen shot >> Field_Not_Coming"+fnScreenShot_Date_format());
					}else{
						Thread.sleep(1000);
					}					
				}
			}			
			TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(Input_Field_Xpath);
			
			
			
			TestSuiteBase_MonitorPlan.WithOut_OR_fnClear(Input_Field_Xpath);
			TestSuiteBase_MonitorPlan.WithOut_OR_fnClear(Input_Field_Xpath);
			TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(Input_Field_Xpath).sendKeys(Value);
			fnApps_Report_Logs("PASSED == Successfully type done into the field '"+LabelName+"'.");
		}catch(NoSuchWindowException W){
			throw new Exception(W.getMessage());			}
		catch(Throwable t) {
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}
	public static void switchUser(String switchTo) throws Throwable{
		fnGet_Element_Enabled("Menu");
		WebElement Menu_Element = fnGet_OR_IM_ObjectX("Menu");
								
		Actions action1 = new Actions(driver);
		action1.moveToElement(Menu_Element).build().perform();
		Thread.sleep(500);		
			
		Actions action2 = new Actions(driver);
		fnGet_Element_Enabled("SwitchUser_Ajax_Link");
		WebElement switchUserLinkElement = fnGet_OR_IM_ObjectX("SwitchUser_Ajax_Link");
		action2.moveToElement(switchUserLinkElement).click().build().perform();

		fnApps_Report_Logs("PASSED == Successfully Click on 'Switch User' Ajax Link.");
		fnWait_and_Type("SwitchUser_inputTxt",switchTo);
		Thread.sleep(4000);
		WebElement GoButtonElement = fnGet_OR_IM_ObjectX("GoButton_ForSwitchUser");
		action2.moveToElement(GoButtonElement).click().build().perform();
		fnsLoading_Progressingwait(1);
	}
	// Function to Launch browser and login
	public static void fnsIpulse_Login_SSO(String PortalNamne, String LoginAs, String userName, String password) throws Throwable{
		LoginAs = LoginAs.trim();
		if (browserName.equalsIgnoreCase("edge")) {
			Autoit_scriptExeFile_Path =System.getProperty("user.dir")+"\\AutoIt_scripts\\sso_edge.exe";
		}else{
			Autoit_scriptExeFile_Path =System.getProperty("user.dir")+"\\AutoIt_scripts\\sso_chrome.exe";
		}
		
		try{
			String siteUrl = null;
			
			if (excelSiteURL==null) {
				siteUrl = CONFIG.getProperty("testSiteName");
			} else{
				siteUrl = excelSiteURL;
			}
			
			
			
			if( (PortalNamne.toLowerCase().trim()).equalsIgnoreCase("iPulse_NsfConnect")){	
				if (env.toLowerCase().equalsIgnoreCase("test")){
					siteUrl = "https://testapps.nsf.org/trunkecap";
				 }else if(env.toLowerCase().equalsIgnoreCase("staging")){
					 siteUrl  = "https://stgapps.nsf.org/ecap/";
				 }
			}
			
			fnApps_Report_Logs("Application credentials are URL[ "+siteUrl+" ], UserName[ "+userName+" ].");
			driver.get(siteUrl);
			
			
			
			fnsLoading_PageLoad();
			WithOut_OR_fnGet_Element_Enabled(OR_MtrPlan.getProperty("MicrosoftOnline_Signin_Input"));
			fnWait_and_Type("MicrosoftOnline_Signin_Input", userName+"@nsf.org");
			
			WithOut_OR_fnGet_Element_Enabled(OR_MtrPlan.getProperty("MicrosoftOnline_Signin_Next"));
			WithOut_OR_fnClick(OR_MtrPlan.getProperty("MicrosoftOnline_Signin_Next"));
			fnsLoading_PageLoad();
			fnsLoading_PageLoad();
			String Screen_URL =  "";
			for(int i=1; i<=120; i++){
				Screen_URL =  driver.getCurrentUrl().toLowerCase().trim();
				System.out.println(Screen_URL);
				if(Screen_URL.contains("login.microsoftonline.com")){
					Thread.sleep(1000);
				}else{
					break;
				}
				if(i==120){
					fnTake_Screen_Shot("SSO_Not_Working");
					fnApps_Report_Logs("FAILED == After Clicking on NEXT button from 'login.microsoftonline.com' screen, the application is not navigated to next screen (Wait Time = 120 seconds), please refer the screenshot >> SSO_Not_Working "+fnScreenShot_Date_format());
					throw new Exception ("FAILED == After Clicking on NEXT button from 'login.microsoftonline.com' screen, the application is not navigated to next screen (Wait Time = 120 seconds), please refer the screenshot >> SSO_Not_Working "+fnScreenShot_Date_format());
				}
			}
			fnsLoading_PageLoad();
			Screen_URL =  driver.getCurrentUrl().toLowerCase().trim();
			if(Screen_URL.contains("auth.nsf.org")){	
				Runtime.getRuntime().exec(Autoit_scriptExeFile_Path);
			    fnApps_Report_Logs("PASSED == ********* Successfulluy Executed the Autoit script for SSO login having exe file path [ "+Autoit_scriptExeFile_Path+" ]");
				
			    for(int i=1; i<=120; i++){
					String Screen_Title =  driver.getTitle().trim();
					System.out.println(Screen_Title);
					if(Screen_Title.equalsIgnoreCase("i-pulse")){
						break;
					}else{
						Thread.sleep(1000);
					}
					if(i==120){
						fnTake_Screen_Shot("SSO_Not_Working");
						fnApps_Report_Logs("FAILED == After Clicking on login button from 'auth.nsf.org' screen, the application is not navigated to i-Pulse screen (Wait Time = 120 seconds), please refer the screenshot >> SSO_Not_Working "+fnScreenShot_Date_format());
						throw new Exception ("FAILED == After Clicking on login button from 'auth.nsf.org' screen, the application is not navigated to i-Pulse screen (Wait Time = 120 seconds), please refer the screenshot >> SSO_Not_Working "+fnScreenShot_Date_format());
					}
				}
			}else{
				for(int i=1; i<=120; i++){
					String Screen_Title =  driver.getTitle().trim();
					System.out.println(Screen_Title);
					if(Screen_Title.equalsIgnoreCase("i-pulse")){
						break;
					}else{
						Thread.sleep(1000);
					}
					if(i==120){
						fnTake_Screen_Shot("SSO_Not_Working");
						fnApps_Report_Logs("FAILED == After Clicking on NEXT button from 'login.microsoftonline.com' screen, the application is not navigated to i-Pulse screen (Wait Time = 120 seconds), please refer the screenshot >> SSO_Not_Working "+fnScreenShot_Date_format());
						throw new Exception ("FAILED == After Clicking on NEXT button from 'login.microsoftonline.com' screen, the application is not navigated to i-Pulse screen (Wait Time = 120 seconds), please refer the screenshot >> SSO_Not_Working "+fnScreenShot_Date_format());
					}
				}				
			}
			
			fnsLoading_PageLoad();	
			fnsLoading_Progressingwait(1);
			fnsComman_BrowserLogin_ElementWait(OR_MtrPlan.getProperty("Acknowledge_bttn"), CONFIG.getProperty("Element_MAX_WaitTime"));
			fnGet_Element_Enabled("Acknowledge_bttn");
			Thread.sleep(2000);
			fnsWait_and_Click("Acknowledge_bttn");
			fnsLoading_Progressingwait(4);
			
			
			if(LoginAs.equalsIgnoreCase("testscriptuser")){
				LoginAs = "";
			}
			if (( !(LoginAs.equalsIgnoreCase(""))) ){
				
				try{
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
					WebElement SwitchUserMenu = WithOut_OR_fnGet_ObjectX(OR_MtrPlan.getProperty("Admin_Switch_User_Menu"));
					act1.moveToElement(SwitchUserMenu).click().build().perform();
					fnApps_Report_Logs("PASSED == Successfully click on 'Switch User' menu link");
						
				}catch(NoSuchWindowException W){
					isTestCasePass = false;
					throw new Exception(W.getMessage());			}
				catch(Throwable t) {
					isTestCasePass = false;
					fnTake_Screen_Shot("SwitchUserMenu_Click_Fail");
					fnApps_Report_Logs("FAILED == Clicking on 'Switch User' menu link Failed, plz see screenshot [ " + "SwitchUserMenu_Click_Fail" + fnScreenShot_Date_format() + "]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(t).trim());
					throw new Exception("Clicking on 'Switch User' menu link Failed, plz see screenshot [ " + "SwitchUserMenu_Click_Fail" + fnScreenShot_Date_format() + "]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(t).trim());
				}

				fnsLoading_PageLoad();
				fnsLoading_Progressingwait(1);
				WithOut_OR_fnGet_Element_Enabled(OR_MtrPlan.getProperty("ipulse_SwitchUser_inpit"));
				fnWait_and_Type("ipulse_SwitchUser_inpit", LoginAs);	
				
				WithOut_OR_fnGet_Element_Enabled(OR_MtrPlan.getProperty("ipulse_SwitchUser_GO_button"));
				WithOut_OR_fnClick(OR_MtrPlan.getProperty("ipulse_SwitchUser_GO_button"));
				fnsLoading_PageLoad();
				fnsLoading_Progressingwait(2);
				fnsLoading_Progressingwait(1);
			}	
		}catch (Throwable t) {
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}
		public static Integer fnsLoading_PageLoad() throws Throwable{
			Integer PageWait = 0;
			try{
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				for(int k=2;k<=Long.parseLong(CONFIG.getProperty("Element_MAX_WaitTime")); k++){
					String Text = (String) jse.executeScript("return document.readyState");//.equals("complete");
					if(Text.equals("complete")){
						//fnsApps_Report_Logs("Page is loaded in '"+k+"' seconds.");
						PageWait = k;
						break;
					}else{
						Thread.sleep(1000);
					}
					if(k==Long.parseLong(CONFIG.getProperty("Element_MAX_WaitTime"))){
						fnTake_Screen_Shot("PageLoad_Fail");
						throw new Exception("FAIL == after '"+Long.parseLong(CONFIG.getProperty("Element_MAX_WaitTime"))+"' seconds wait page is not loading.");
					}
				}
			}catch(Throwable t){
				throw new Exception(Throwables.getStackTraceAsString(t));
			} return PageWait;
		}
}
