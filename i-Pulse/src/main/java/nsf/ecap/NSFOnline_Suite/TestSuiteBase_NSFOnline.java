package nsf.ecap.NSFOnline_Suite;

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
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
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
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.google.common.base.Throwables;

import nsf.ecap.base.TestBase;
import nsf.ecap.util.ErrorUtil;
import nsf.ecap.util.TestUtil;



public class TestSuiteBase_NSFOnline extends TestBase{
	
	
	public String classNameText = null;
	public boolean Insight_Invalid_Password_Verification = false;
	public String InsightoriginalHandle = null;
	public String MainWindowHandle = null;
	public String validation_error_msg_text;
	public String SearchResult_Code_link_Xpath;
	public String BrowserName="IE";
	public String runmodes[] = null;
	public String NSFOnlineoriginalHandle = null;
	public boolean isTestCasePass = true;
	public boolean IsBrowserPresentAlready = false;
	
	
	
	boolean fail = false;
	public boolean TCSkipReturnValue=false;
	public  static  boolean ScreenShotFlagWithOR_NsfOnline = false;
	
	public Integer ColumnNo;
	public Integer RowCount;
	public Integer tabsCount;
	
	public boolean NsfOnline_New_Version_RUN = false;
	public boolean Got_NsfOnline_URL = true; 
	public boolean Default_Value_Selection ;
	
	
	
	
	public String Errormsg;
	public String CustomerName;
	public String TextFetch = null;
	public String CaseSerialNo = null;
	public String CustomerName_Sorting = null;
	public String Customer_No = null;
	public String Insight_SearchUserName = null;
	public String NsfOnline_Documents_SubMenu = null;
	

	
//######################################################### Common Functions #######################################################################		
	
	
//Function to Take Screen Shot.
public void fnsTake_Screen_Shot(String message) throws Exception {
	String MessageAfterFormat=TestSuiteBase_MonitorPlan.fnsRemoveFormatting_for_FileName(message);
		try{
			String Suite_Foler_Name = "screenshots_NSFOnline";
			String File_Name = MessageAfterFormat+"_"+fnsScreenShot_Date_format()+".PNG";
			FileUtils.forceMkdir(new File((System.getProperty("user.dir") +  screenshots_path+ "//"+Suite_Foler_Name+"//"+currentScriptCode +"//")));
			File image = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);			
			FileUtils.copyFile(image, new File((System.getProperty("user.dir") +  screenshots_path+ "//"+Suite_Foler_Name+"//"+currentScriptCode +"//"+File_Name)));
			
		 /*  FileUtils.forceMkdir(new File((System.getProperty("user.dir") +  screenshots_path+ "//screenshots_NSFOnline//"+currentScriptCode +"//")));
		   Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		   Rectangle screenRectangle = new Rectangle(screenSize);
		   Robot robot = new Robot();
		   BufferedImage image = robot.createScreenCapture(screenRectangle);
		   ImageIO.write(image, "png", new File((System.getProperty("user.dir") +  screenshots_path+ "//screenshots_NSFOnline//"+currentScriptCode +"//"+MessageAfterFormat+"_"+fnsScreenShot_Date_format()+".PNG")));*/
		}catch(java.lang.NullPointerException n){
			fnsApps_Report_Logs("ScreenShotNullPointerException >> "+n.getMessage());
			throw new Exception("NullPointerException Unable To take Screen Shots.");}
		 catch(java.io.IOException e){
			fnsApps_Report_Logs("ScreenShotIOException >> "+e.getMessage());
			throw new Exception("IOException Unable To take Screen Shots.");}
}




//Function to Take Screen Shot of NSF Online Application Version
public void fnsTake_Screen_Shot_NSFOnlineVersion(String message) throws Exception {
		try{
			String Suite_Foler_Name = "//applicationVersion_NSFOnline//";
			String File_Name = message+".PNG";
			FileUtils.forceMkdir(new File((System.getProperty("user.dir") +  screenshots_path+ Suite_Foler_Name)));
			File image = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);			
			FileUtils.copyFile(image, new File((System.getProperty("user.dir") +  screenshots_path+ Suite_Foler_Name+File_Name)));
			
			
		  /* FileUtils.forceMkdir(new File((System.getProperty("user.dir") +  screenshots_path+ "//applicationVersion_NSFOnline//")));
		   Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		   Rectangle screenRectangle = new Rectangle(screenSize);
		   Robot robot = new Robot();
		   BufferedImage image = robot.createScreenCapture(screenRectangle);
		   ImageIO.write(image, "png", new File((System.getProperty("user.dir") +  screenshots_path+ "//applicationVersion_NSFOnline//"+message+".PNG")));*/
		}catch(NoSuchWindowException W){
			isTestCasePass = false;
			throw new Exception(W.getMessage());			}
		catch(java.lang.NullPointerException n){
			fnsApps_Report_Logs("ScreenShotNullPointerException >> "+n.getMessage());
			throw new Exception("NullPointerException Unable To take Screen Shots.");}
		 catch(java.io.IOException e){
			fnsApps_Report_Logs("ScreenShotIOException >> "+e.getMessage());
			throw new Exception("IOException Unable To take Screen Shots.");}
}



//Function to Take Screen Shot of TraQtion Online Application Version
public void fnsTake_Screen_Shot_TraQtionOnlineVersion(String message) throws Exception {
		try{
		   FileUtils.forceMkdir(new File((System.getProperty("user.dir") +  screenshots_path+ "//applicationVersion_TraQtionOnline//")));
		   Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		   Rectangle screenRectangle = new Rectangle(screenSize);
		   Robot robot = new Robot();
		   BufferedImage image = robot.createScreenCapture(screenRectangle);
		   ImageIO.write(image, "png", new File((System.getProperty("user.dir") +  screenshots_path+ "//applicationVersion_TraQtionOnline//"+message+".PNG")));
		}catch(NoSuchWindowException W){
			isTestCasePass = false;
			throw new Exception(W.getMessage());			}
		catch(java.lang.NullPointerException n){
			fnsApps_Report_Logs("ScreenShotNullPointerException >> "+n.getMessage());
			throw new Exception("NullPointerException Unable To take Screen Shots.");}
		 catch(java.io.IOException e){
			fnsApps_Report_Logs("ScreenShotIOException >> "+e.getMessage());
			throw new Exception("IOException Unable To take Screen Shots.");}
}


//Function to Take Screen Shot of I-Pulse Application Version
public void fnsIPulse_Application_Version(String message) throws Exception {
	try{
		TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(OR_NsfOnline.getProperty("Footer_IPulse"));
		Thread.sleep(1500);
		TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(OR_NsfOnline.getProperty("Ipulse_Version"));
		WebElement VersionWE=TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(OR_NsfOnline.getProperty("Ipulse_Version"));
	    Actions action = new Actions(driver);
	    action.moveToElement(VersionWE).sendKeys(VersionWE, Keys.CONTROL).click().build().perform();	
	    action.keyDown(Keys.CONTROL).build().perform();
	
		Thread.sleep(1500);
			
		/*FileUtils.forceMkdir(new File((System.getProperty("user.dir") +  screenshots_path+ "//applicationVersion//")));
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle screenRectangle = new Rectangle(screenSize);
		Robot robot = new Robot();
		BufferedImage image = robot.createScreenCapture(screenRectangle);
		ImageIO.write(image, "png", new File((System.getProperty("user.dir") +  screenshots_path+"//applicationVersion//"+message+"_ApplicationVersion.PNG")));*/
		
		String Suite_Foler_Name = "//applicationVersion//";
		String File_Name = message+"_ApplicationVersion.PNG";
		FileUtils.forceMkdir(new File((System.getProperty("user.dir") +  screenshots_path+ Suite_Foler_Name)));
		File image = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);			
		FileUtils.copyFile(image, new File((System.getProperty("user.dir") +  screenshots_path+ Suite_Foler_Name+File_Name)));
		
		
		fnsWait_and_Click("Ipulse_Version_Dialog_Close_Bttn");
		Thread.sleep(1000);
	}catch(NoSuchWindowException W){
		isTestCasePass = false;
		throw new Exception(W.getMessage());			}
	catch(java.lang.NullPointerException n){
		fnsApps_Report_Logs("ScreenShotNullPointerException >> "+n.getMessage());
		throw new Exception("NullPointerException Unable To take Screen Shots.");}
	 catch(java.io.IOException e){
		fnsApps_Report_Logs("ScreenShotIOException >> "+e.getMessage());
		throw new Exception("IOException Unable To take Screen Shots.");}
}


//Function to Take Screen Shot of I-Pulse Application Version
public void fnsNew_NsfOnline_Application_Version() throws Exception {
	try{
		FileUtils.forceMkdir(new File((System.getProperty("user.dir") +  screenshots_path+ "//applicationVersion//")));
		
	}catch(NoSuchWindowException W){
		isTestCasePass = false;
		throw new Exception(W.getMessage());			}
	catch(java.lang.NullPointerException n){
		fnsApps_Report_Logs("ScreenShotNullPointerException >> "+n.getMessage());
		throw new Exception("NullPointerException Unable To take Screen Shots.");}
	 catch(java.io.IOException e){
		fnsApps_Report_Logs("ScreenShotIOException >> "+e.getMessage());
		throw new Exception("IOException Unable To take Screen Shots.");}
}

//Function for Application Log Date format
public String fnsLOGS_Date_format() {
	DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	Date date = new Date();
	return (dateFormat.format(date));
}

public String fnIssueCreationText_Date_format() {
	DateFormat dateFormat = new SimpleDateFormat("ddMMMyyyy/HH:mm z");
	Date date = new Date();
	return (dateFormat.format(date));
}
//Function for Insight Creation Text field Date Format
public String fnsInsightCreationText_Date_format() {
	DateFormat dateFormat = new SimpleDateFormat("ddMMMyyyy/HH:mm z");
	Date date = new Date();
	return (dateFormat.format(date));
}

//Return Time depends on Passing Value
public String fnReturn_Requried_Time_HHmm(int WhichTime_forCurrentMinute) {
	Calendar cal = Calendar.getInstance();
	DateFormat minuteFormat = new SimpleDateFormat("HH:mm");
	cal.add(Calendar.MINUTE, WhichTime_forCurrentMinute);
	String time = minuteFormat.format(cal.getTime()).trim();
	
	return time;
}


//Picking date current day and Current Month for String Passing year By Selecting values from months and years DropDpwn.
public void fncCalendarDatePicker_BySelectValues_From_DropDown_NSFOnline(String DatePickForWhichDay, String DatePickForWhichMonth_MMMM, String DatePickForWhichYear_YYYY) throws Throwable{
	try{
		String CurrentDay = null;
		
		if(DatePickForWhichMonth_MMMM!=null){
			TestSuiteBase_MonitorPlan.WithOut_OR_fnDD_SelectValue_By_SelectClass(OR_IM.getProperty("NSFOnline_Calander_Month_DD"), DatePickForWhichMonth_MMMM);
			Thread.sleep(2000);
		}
		
		if(DatePickForWhichYear_YYYY!=null){
			TestSuiteBase_MonitorPlan.WithOut_OR_fnDD_SelectValue_By_SelectClass(OR_IM.getProperty("NSFOnline_Calander_Year_DD"), DatePickForWhichYear_YYYY);
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
		
		TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(CurrentDayXpath);
		TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(CurrentDayXpath);
		Thread.sleep(500);

	}catch(NoSuchWindowException W){
		isTestCasePass = false;
		throw new SkipException(W.getMessage());
	}catch(Throwable t){
		TestSuiteBase_MonitorPlan.fnsTake_Screen_Shot_Without_OR("DatePickFail");
		isTestCasePass = false;
		fnsApps_Report_Logs("FAILED == Calendar Date Pick is getting fail, Please refer screen shot >> DatePickFail"+fnsScreenShot_Date_format()+"   Getting Exception >>"+Throwables.getStackTraceAsString(t));
		throw new Exception("FAILED == Calendar Date Pick is getting fail, Please refer screen shot >> DatePickFail"+fnsScreenShot_Date_format()+"   Getting Exception >>"+Throwables.getStackTraceAsString(t));	}
}
//Function for Screen date format 
public String fnsScreenShot_Date_format() {
	DateFormat dateFormat = new SimpleDateFormat("_dd.MM.yyyy_HH.mm.ss");
	Date date = new Date();
	return (dateFormat.format(date));
}

//Return Month depends on Passing Value
public String fnsReturn_Requried_Month(String MonthFormat, Integer WhichMonth_O_forCurrentM) {
	Calendar cal = Calendar.getInstance();
	DateFormat dateFormat = new SimpleDateFormat(MonthFormat);
	cal.add(Calendar.MONTH, WhichMonth_O_forCurrentM);
	String DemandMonth = dateFormat.format(cal.getTime());
	return DemandMonth;
}
//Function for Screen date format 
public String fnsEditInsight_Date_format() {
	DateFormat dateFormat = new SimpleDateFormat("MMddyyyy");
	Date date = new Date();
	return (dateFormat.format(date));
}


//Function For Application Log 
public void fnsApps_Report_Logs(String LogMessage) {

	APP_LOGS.debug(LogMessage);
	System.out.println(LogMessage);
	Reporter.log(fnsLOGS_Date_format() + "  " + LogMessage);
}



//Function to find and return the object using Xpath
public WebElement fnsGet_OR_NsfOnline_ObjectX(String XpathKey) throws Exception{
	try {
		for (int waits=0; waits<Long.parseLong(CONFIG.getProperty("ElementWaitTime")); waits++) {
			
			if (driver.findElements(By.xpath(OR_NsfOnline.getProperty(XpathKey))).size() > 0) {break;}
			else{Thread.sleep(500);}
			
		}return driver.findElement(By.xpath(OR_NsfOnline.getProperty(XpathKey))); 
		
	}catch(StaleElementReferenceException e){
		WebDriverWait stalewaitvar = new WebDriverWait(driver, 10);
		stalewaitvar.until(ExpectedConditions.presenceOfElementLocated(By.xpath(OR_NsfOnline.getProperty(XpathKey))));
		WebElement webelemnt=driver.findElement(By.xpath(OR_NsfOnline.getProperty(XpathKey)));
		stalewaitvar.until(ExpectedConditions.stalenessOf(webelemnt));
		return driver.findElement(By.xpath(OR_NsfOnline.getProperty(XpathKey)));}
	catch(NoSuchWindowException W){
		isTestCasePass = false;
		throw new Exception(W.getMessage());			}
	catch(NoSuchElementException e){
		isTestCasePass = false;
		fnsTake_Screen_Shot("NoSuchElementException" + XpathKey);
		fnsApps_Report_Logs("FAILED == Element is not found >> "+XpathKey + " ] , plz see screenshot [NoSuchElementException" + XpathKey + fnsScreenShot_Date_format() + " ]");
		throw new Exception("FAILED == Element is not found >> "+XpathKey + " ] , plz see screenshot [NoSuchElementException" + XpathKey + fnsScreenShot_Date_format() + " ]");}
	catch(TimeoutException e){
		isTestCasePass = false;
		fnsTake_Screen_Shot("TimeOut" + XpathKey);
		fnsApps_Report_Logs("FAILED == Element is not Present, getting TimeOut >> "+XpathKey + " ] , plz see screenshot [TimeOut" + XpathKey + fnsScreenShot_Date_format() + " ]");
		throw new Exception("FAILED == Element is not Present, getting TimeOut >> "+XpathKey + " ] , plz see screenshot [TimeOut" + XpathKey + fnsScreenShot_Date_format() + " ]");}
	catch(Throwable e){
		isTestCasePass = false;
		fnsTake_Screen_Shot("NotPresent" + XpathKey);
		fnsApps_Report_Logs("FAILED == Element is not found >> "+XpathKey + " ] , plz see screenshot [NotPresent" + XpathKey + fnsScreenShot_Date_format() + " ]"+". Getting Exception  >> "+e.getMessage());
		throw new Exception("FAILED == Element is not found >> "+XpathKey + " ] , plz see screenshot [NotPresent" + XpathKey + fnsScreenShot_Date_format() + " ]");}
}






//Function to click on Object
public void fnsWait_and_Click(String XpathKey) throws Exception {
	try{
		fnsGet_OR_NsfOnline_ObjectX(XpathKey).click();
		fnsApps_Report_Logs("PASSED == Click on Element done having Xpath >> "+XpathKey);
	}catch(NoSuchWindowException W){
		isTestCasePass = false;
		throw new Exception(W.getMessage());			}	
	catch(StaleElementReferenceException e){
		isTestCasePass = false;
		fnsTake_Screen_Shot("UnableToClick_" + XpathKey);
		fnsApps_Report_Logs("FAILED == Unable To Click on Element having Xpath >> "+XpathKey+", plz see screenshot [ UnableToClick_" + XpathKey + fnsScreenShot_Date_format() + " ]");
		throw new Exception("Unable To Click on element [ " + XpathKey + " ] , plz see screenshot [ UnableToClick_" + XpathKey + fnsScreenShot_Date_format() + " ]");}
}

	




//Function to wait for element and then type
public void fnsWait_and_Type(String XpathKey, String value) throws Exception {
	try{
		fnsGet_OR_NsfOnline_ObjectX(XpathKey).sendKeys("");
		fnsGet_OR_NsfOnline_ObjectX(XpathKey).sendKeys(value);
		fnsApps_Report_Logs("PASSED == Type done Value<"+value+"> on Element having Xpath  >> "+XpathKey);
	}catch(NoSuchWindowException W){
		isTestCasePass = false;
		throw new Exception(W.getMessage());			}
	catch(StaleElementReferenceException e){
		isTestCasePass = false;
		fnsTake_Screen_Shot("UnableToType_" + XpathKey);
		fnsApps_Report_Logs("FAILED == Unable To Type on Element having Xpath  >> "+XpathKey+", plz see screenshot [ UnableToType_" + XpathKey + fnsScreenShot_Date_format() + " ]");
		throw new Exception("Unable To Type on element [ " + XpathKey + " ] , plz see screenshot [ UnableToType_" + XpathKey + fnsScreenShot_Date_format() + " ]");	}
}



//Function to wait for element
public void fnsGet_Element_Enabled(String XpathKey) throws Exception {
	
	try{
		for(int wait=0; wait<2; wait++){
			if(driver.findElements(By.xpath(OR_NsfOnline.getProperty(XpathKey))).size()>0){
			//	fnsGet_OR_NsfOnline_ObjectX(XpathKey);
				WebDriverWait elementwaitvar = new WebDriverWait(driver, Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
				elementwaitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_NsfOnline.getProperty(XpathKey))));
		
				WebDriverWait elementwaitvar1 = new WebDriverWait(driver, Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
				elementwaitvar1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_NsfOnline.getProperty(XpathKey)))).isEnabled();
				fnsApps_Report_Logs("PASSED == Element is Visible having Xpath  >> "+XpathKey);
				break;	} //if loop closed 
			else{
				throw new Exception();
			} // else loop closed
		} // for loop Closed
		
	}catch(NoSuchWindowException W){
		isTestCasePass = false;
		throw new Exception(W.getMessage());
	}catch(TimeoutException to){
		throw new Exception ("TimeOut : "+Throwables.getStackTraceAsString(to));
	}catch(Throwable t){
		try{
			Thread.sleep(3000);
			WebDriverWait elementwaitvar3 = new WebDriverWait(driver, Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
			elementwaitvar3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_NsfOnline.getProperty(XpathKey)))).isEnabled();//}
			fnsApps_Report_Logs("PASSED == Element is Visible having Xpath  >> "+XpathKey);
		}catch(NoSuchWindowException W){
			isTestCasePass = false;
			throw new Exception(W.getMessage());			}
		catch(Throwable e){
			isTestCasePass = false;
			fnsTake_Screen_Shot(XpathKey);
			fnsApps_Report_Logs("FAILED == Element is not Visible having Xpath  >> "+XpathKey+", plz see screenshot [ " + XpathKey + fnsScreenShot_Date_format() + " ]. Getting Exception >> "+e.getMessage());
			throw new Exception("FAILED == Element is not Visible having Xpath  [ " + XpathKey + " ] , plz see screenshot [ " +XpathKey + fnsScreenShot_Date_format() + " ]. Getting Exception >> "+e.getMessage());
		}
	}
}
		

		
				
//Function to wait for element
public void fnsGet_Element_Displayed(String XpathKey) throws Exception {
	
	try{
		for(int wait=0; wait<3; wait++){
			if(driver.findElements(By.xpath(OR_NsfOnline.getProperty(XpathKey))).size()>0){
			//	fnsGet_OR_NsfOnline_ObjectX(XpathKey);
				WebDriverWait elementwaitvar = new WebDriverWait(driver, Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
				elementwaitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_NsfOnline.getProperty(XpathKey))));
		
				WebDriverWait elementwaitvar1 = new WebDriverWait(driver, Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
				elementwaitvar1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_NsfOnline.getProperty(XpathKey)))).isDisplayed();
				fnsApps_Report_Logs("PASSED == Element is Visible having Xpath  >> "+XpathKey);
				break;	} //if loop closed 
			else{
				Thread.sleep(3000);
				if(wait==2){
					WebDriverWait elementwaitvar2 = new WebDriverWait(driver, Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
					elementwaitvar2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_NsfOnline.getProperty(XpathKey)))).isDisplayed();	} // if loop closed
			} // else loop closed
		} // for loop Closed
		
	}catch(NoSuchWindowException W){
		isTestCasePass = false;
		throw new Exception(W.getMessage());			}
	catch(Throwable t){
		try{
			Thread.sleep(3000);
			WebDriverWait elementwaitvar3 = new WebDriverWait(driver, Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
			elementwaitvar3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_NsfOnline.getProperty(XpathKey)))).isDisplayed();//}
			fnsApps_Report_Logs("PASSED == Element is Visible having Xpath  >> "+XpathKey);
		}catch(NoSuchWindowException W){
			isTestCasePass = false;
			throw new Exception(W.getMessage());			}
		catch(Throwable e){
			isTestCasePass = false;
			fnsTake_Screen_Shot(XpathKey);
			fnsApps_Report_Logs("FAILED == Element is not Visible having Xpath  >> "+XpathKey+", plz see screenshot [ " + XpathKey + fnsScreenShot_Date_format() + " ]. Getting Exception >> "+e.getMessage());
			throw new Exception("FAILED == Element is not Visible having Xpath  [ " + XpathKey + " ] , plz see screenshot [ " +XpathKey + fnsScreenShot_Date_format() + " ]. Getting Exception >> "+e.getMessage());
		}
	}
}


//Function to Return 2days back date from past (day before Yesterday)
public String fnsGet_2Days_Past_date() throws Exception {
	
		   Calendar cal = Calendar.getInstance();
		   DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy - HH:mm");
		   cal.add(Calendar.DATE, -2);
		   String Past_date=dateFormat.format(cal.getTime()).split("-")[0].trim();
		  
		   return Past_date;
}


	
//Function to Return current system time
public String fnsGet_Current_Time() throws Exception {
		   Calendar cal = Calendar.getInstance();
		   DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy - HH:mm");
		   cal.add(Calendar.DATE, -2);
		   String current_time=dateFormat.format(cal.getTime()).split("-")[1].trim();
		   
		   return current_time;
}
	

//Click on Link by matchingText Xpath
public void fnsTable_ClickOn_LINK_ByMatchingText(String LinkMatchingText) throws Throwable{
	try{
		SearchResult_Code_link_Xpath = ".//a[text()='" + LinkMatchingText.trim() + "']"; 
		
		TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(SearchResult_Code_link_Xpath);
		driver.findElement(By.xpath(SearchResult_Code_link_Xpath)).click();
		fnsApps_Report_Logs("PASSED == click on the Link done having xpath >> "+SearchResult_Code_link_Xpath);
	}catch(NoSuchWindowException W){
		isTestCasePass = false;
		throw new Exception(W.getMessage());			}
	catch(Throwable t){
		isTestCasePass = false;
		fnsTake_Screen_Shot("LinkClickFail");
		fnsApps_Report_Logs("FAILED == Unable to Click on Link having Xpath >> "+SearchResult_Code_link_Xpath+", Please refer ScreenShot [ "+"LinkClickFail"+fnsScreenShot_Date_format() +" ]."+". Getting Exception  >> "+Throwables.getStackTraceAsString(t));
		throw new Exception("FAILED == Unable to Click on Link having Xpath >> "+SearchResult_Code_link_Xpath+", Please refer ScreenShot [ "+"LinkClickFail"+fnsScreenShot_Date_format() +" ].");}	
}






//########################################################################## Application Functions ########################################################################################	
public String fnsGet_Field_TEXT(String XpathKey) throws Exception {
	try{
		
		WebDriverWait elementwaitvar = new WebDriverWait(driver,Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
		elementwaitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_NsfOnline.getProperty(XpathKey)))).isEnabled();
		String TextFetch=fnsGet_OR_NsfOnline_ObjectX(XpathKey).getText();
		fnsApps_Report_Logs("PASSED == Fetch the Text["+TextFetch+"] on Element having xpath [ " +XpathKey+" ].");
		
		return TextFetch;
	}catch(NoSuchWindowException W){
		isTestCasePass = false;
		throw new Exception(W.getMessage());			}
	catch (Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot("TextFetchFail" + XpathKey);
			fnsApps_Report_Logs("FAILED == Unable to Fetch Text on Element having xpath [ " +"TextFetchFail" + XpathKey + fnsScreenShot_Date_format() +" ]."+". Getting Exception  >> "+Throwables.getStackTraceAsString(t)+"......................");
			throw new Exception("FAILED == Unable to Fetch Text on Element having xpath [ " +"TextFetchFail" + XpathKey + fnsScreenShot_Date_format() +" ]."+". Getting Exception  >> "+Throwables.getStackTraceAsString(t)+"......................");}
	}

	
	
//function to select drop down value
public void fnsDD_value_Select_By_MatchingText(String ddClickXpathKey, String ddListXpathKey, String TagName, String Value) throws Exception {
	
	boolean ValueNotMatched=true;
	
	try{
			fnsWait_and_Click(ddClickXpathKey);
			fnsGet_Element_Enabled(ddListXpathKey);
			List<WebElement> DDobjectlists=fnsGet_OR_NsfOnline_ObjectX(ddListXpathKey).findElements(By.tagName(TagName));
			for(WebElement dd_value:DDobjectlists){
				if(dd_value.getText().equals(Value)){
					dd_value.click();
					ValueNotMatched=false;	
					break;	}
				
			}
			if(ValueNotMatched==true){
				throw new Exception("Excel value is not matched with DropDown Value.");
			}	
						
			fnsApps_Report_Logs("PASSED == select value [ "+Value+" ] from drop down  done, having xpath >>  " + ddClickXpathKey);
			
	}catch(NoSuchWindowException W){
		isTestCasePass = false;
			throw new Exception(W.getMessage());			}
	catch(Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot("DdValueSelectFail" + ddClickXpathKey);
			fnsApps_Report_Logs("FAILED == Unable to select value from drop down [ " + ddClickXpathKey +fnsScreenShot_Date_format() +" ]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(t).trim()+"......................");
			throw new Exception("Unable to select value from drop down [ " + ddClickXpathKey +" ],plz see screenshot [ DdValueSelectFail" + ddClickXpathKey + fnsScreenShot_Date_format() + " ]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(t)+"......................");}
}



//Verify Text fetched and matched with expected text.
public void fnsText_Fetch_and_Assert(String XpathKey, String MatchingText) throws Exception {
	
	fnsMove_To_MouseOver(XpathKey);
	String GetText=fnsGet_Field_TEXT(XpathKey).trim().toLowerCase();	
	try{
		assert GetText.contains(MatchingText.toLowerCase()):"FAILED == Expected Text ("+MatchingText+") is not matched with the Actual Text ("+GetText+"), Please refer Screen shot >> TextNotMatch"+fnsScreenShot_Date_format();
		fnsApps_Report_Logs("PASSED ==  Expected Text ("+MatchingText+") is matched with the Actual Text ("+GetText+"), from element having xpath >> "+XpathKey);
		
	}catch(NoSuchWindowException W){
		isTestCasePass = false;
		throw new Exception(W.getMessage());			}
	catch(Throwable t){
		isTestCasePass = false;
		fnsTake_Screen_Shot("TextNotMatch");
		fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
		throw new Exception(Throwables.getStackTraceAsString(t));		}
}	



//Clicking on Search Customer Ajax link.
	public void fnsMove_To_MouseOver(String Xpath) throws Exception {
					
				try{
				//	System.out.println(driver.findElements(By.xpath(OR_NsfOnline.getProperty(Xpath))).size());
					fnsGet_Element_Enabled(Xpath);
					WebElement Customer = fnsGet_OR_NsfOnline_ObjectX(Xpath);
				
					Actions act = new Actions(driver);
					act.moveToElement(Customer).build().perform();
					
					fnsApps_Report_Logs("PASSED == MouseOver to Element done having Xpath >> "+Xpath);					
				}catch(NoSuchWindowException W){
					isTestCasePass = false;
					throw new Exception(W.getMessage());			}
				catch(Throwable t) {
						isTestCasePass = false;
						fnsTake_Screen_Shot("SearchCustomer_Ajax");
						fnsApps_Report_Logs("FAILED == Clicking on Element Failed having Xpath["+Xpath+"] , plz see screenshot [ " + "SearchCustomer_Ajax" + fnsScreenShot_Date_format() + "]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(t).trim());
						throw new Exception("FAILED == Clicking on Element Failed having Xpath["+Xpath+"] , plz see screenshot [ " + "SearchCustomer_Ajax" + fnsScreenShot_Date_format() + "]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(t).trim());}
}	
	

		
	//Return Customer Column No. in search Table
	public Integer fnsReturn_ColumnNo_By_MatchingColumnNameText(String TableFirstRowXpath, String ColumnName, String tagname) throws Exception {
		ColumnNo=0;		
		try{
			boolean ValueNotMatched=true;
			fnsGet_Element_Enabled(TableFirstRowXpath);
			WebElement SearchtableFirstRow=fnsGet_OR_NsfOnline_ObjectX(TableFirstRowXpath);
			List<WebElement> FirstRowAllObj = SearchtableFirstRow.findElements(By.tagName(tagname));
					
			for(WebElement FirstRowObj:FirstRowAllObj){
				ColumnNo++;
				String ColumnText=(FirstRowObj.getText()).toLowerCase().trim();
				System.out.println("ColumnText="+ColumnText);
				String MatchingColumnName=ColumnName.toLowerCase().trim();
				
				if(ColumnText.contains(MatchingColumnName)){
					ValueNotMatched=false;
					break;	}
			}
			
			if(ValueNotMatched==true){
				throw new Exception("Column Name ("+ColumnName+") is not Exists into Search Table");
			}
						
			fnsApps_Report_Logs("PASSED == Successfully get the Column No. and it is="+ColumnNo);
			
			return ColumnNo;
										
		}catch(NoSuchWindowException W){
			isTestCasePass = false;
			throw new Exception(W.getMessage());			}
		catch (Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot("ColumnNoFail" );
			fnsApps_Report_Logs("FAILED == Fetching CoulmnNo. of ColumnName("+ColumnName+") is getting Fail for table having Xpath >> "+TableFirstRowXpath+" ,Please refer screenshot [ColumnNoFail" +   fnsScreenShot_Date_format() +"]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(t)+"......................");
			throw new Exception("FAILED == Fetching CoulmnNo. of ColumnName("+ColumnName+") is getting Fail for table having Xpath >> "+TableFirstRowXpath+" ,Please refer screenshot [ColumnNoFail" +   fnsScreenShot_Date_format() +"]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(t)+"......................");		}
}		
		
		
		
	//Get Total Row Count of search Table
	public Integer fnsReturn_TotalRowCount(String TableFirstRowXpath) throws Exception {
		try{
			RowCount=0;
			fnsGet_Element_Enabled(TableFirstRowXpath);
			WebElement Searchtable=fnsGet_OR_NsfOnline_ObjectX(TableFirstRowXpath);
			List<WebElement> AllRowObj = Searchtable.findElements(By.tagName("tr"));
				
			for(WebElement countrows:AllRowObj){
				RowCount++;
			}
			RowCount--;
			fnsApps_Report_Logs("PASSED == Successfully Get Total Row Count of table having xpath >> "+TableFirstRowXpath+"and it is="+RowCount);
				
			return RowCount;
		}catch(NoSuchWindowException W){
			isTestCasePass = false;
			throw new Exception(W.getMessage());			}
		catch (Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot("TotalRowCountFail" );
			fnsApps_Report_Logs("FAILED == Total Row Count is getting Fail of table having xpath >> "+TableFirstRowXpath+" ,Please refer screenshot [TotalRowCountFail" +   fnsScreenShot_Date_format() +"]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(t)+"......................");
			throw new Exception("FAILED == Total Row Count is getting Fail of table having xpath >> "+TableFirstRowXpath+" ,Please refer screenshot [TotalRowCountFail" +   fnsScreenShot_Date_format() +"]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(t)+"......................");		}
}			
		
	
	//Return Customer Column No. in search Table
	public String fnsReturn_IntegerValue_From_AduitHomeAccountSummary_WebTableByMatchingRowText(String TableFirstRowXpath, String RowText) throws Exception {
		String AccountSummaryTableValue = "";
		try{
			RowCount=0;
			fnsGet_Element_Enabled(TableFirstRowXpath);
			WebElement Searchtable=fnsGet_OR_NsfOnline_ObjectX(TableFirstRowXpath);
			List<WebElement> AllRowObj = Searchtable.findElements(By.tagName("tr"));
			String MatchingRowText=RowText.toLowerCase().trim();
			
			for(WebElement countrows:AllRowObj){
				RowCount++;
				String Rowxpath=(OR_NsfOnline.getProperty(TableFirstRowXpath)+"/tbody/tr["+RowCount+"]");
				String RowFristColumnText=TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(Rowxpath).trim().toLowerCase();
				System.out.println("RowFristColumnText="+RowFristColumnText);
				
				if(RowFristColumnText.contains(MatchingRowText)){
					String RowSecondColumnXpath=Rowxpath+"/td["+2+"]";
					String RowSecondColumnText=TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(RowSecondColumnXpath);
					TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element_and_DoubleClick(RowSecondColumnXpath);
					if(RowSecondColumnText.contains("%")){
						AccountSummaryTableValue=RowSecondColumnText.replace("%", " ").trim();
						System.out.println("AccountSummaryTableValue="+AccountSummaryTableValue);
						break;
					}else{
						AccountSummaryTableValue=RowSecondColumnText.trim();
						break;
					}
				}
			}
			
			return AccountSummaryTableValue;							
		}catch(NoSuchWindowException W){
			isTestCasePass = false;
			throw new Exception(W.getMessage());			}
		catch (Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot("FetchValueFail" );
			fnsApps_Report_Logs("FAILED == Fetching Coulmn Value is getting fail from matching Row("+RowText+"), Please refer screenshot [FetchValueFail" +   fnsScreenShot_Date_format() +"]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(t)+"......................");
			throw new Exception("FAILED == Fetching Coulmn Value is getting fail from matching Row("+RowText+"), Please refer screenshot [FetchValueFail" +   fnsScreenShot_Date_format() +"]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(t)+"......................");		}
}		
	
		
		
		
	//Clicking on Menu Ajax Link	
	public void fncAjax_Link_Click_By_Passing_TwoAjaxXPathx(String FirstAjaxXpaths, String SecondAjaxXpaths) throws Exception {
		try{
			fnsGet_Element_Enabled(FirstAjaxXpaths);
			WebElement First_Element = fnsGet_OR_NsfOnline_ObjectX(FirstAjaxXpaths);
			Actions action = new Actions(driver);
			action.moveToElement(First_Element).build().perform();
					
			Thread.sleep(500);
			Actions action1 = new Actions(driver);
			fnsGet_Element_Enabled(SecondAjaxXpaths);
			WebElement Second_Element = fnsGet_OR_NsfOnline_ObjectX(SecondAjaxXpaths);
			action1.moveToElement(Second_Element).click().build().perform();
			fnsApps_Report_Logs("PASSED == Successfully Click on <"+(SecondAjaxXpaths)+">.");	
		}catch(NoSuchWindowException W){
			isTestCasePass = false;
			throw new Exception(W.getMessage());			}
		catch(Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot(SecondAjaxXpaths+"_Fail");
			fnsApps_Report_Logs("FAILED == Clicking on <"+(SecondAjaxXpaths)+"> Failed, plz see screenshot [" +SecondAjaxXpaths+"_Fail" + fnsScreenShot_Date_format() + "]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(t)+"......................");
			throw new Exception("FAILED == Clicking on <"+(SecondAjaxXpaths)+"> Failed, plz see screenshot [" + SecondAjaxXpaths+"_Fail" + fnsScreenShot_Date_format() + "]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(t)+"......................");}
}	
		
		

	public String fnsInsight_SerchCustomer_UserLinkClick(String CustomerNumberString, String SearchUserNameString) throws Throwable {
		String Customer_Name = null;
		try{
			MainWindowHandle = driver.getWindowHandle();
			

			fnsGet_Element_Enabled("CustomersTopMenuLink");
			TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element(OR_NsfOnline.getProperty("CustomersTopMenuLink"));
			Thread.sleep(500);
			TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element_and_Click(OR_NsfOnline.getProperty("Customer_SearchCustomer_MenuAjaxLink"));		
		
				
			fnsGet_Element_Enabled("CustomerTxtBox");
			fnsWait_and_Type("CustomerTxtBox", CustomerNumberString);
	
			fnsWait_and_Click("SearchBtn");
	
			fnsGet_Element_Enabled("FirstUserSetUPLink");
			fnsWait_and_Click("FirstUserSetUPLink");
	
	
			fnsGet_Element_Enabled("Insight_Customer_name_Text");
			Customer_Name=(fnsGet_Field_TEXT("Insight_Customer_name_Text").split("\\-")[1]).toLowerCase().trim();

			
			
			fnsGet_Element_Enabled("UserNameTxtBox");
			Thread.sleep(1000);
			//fnsGet_Element_Enabled("UserNameTxtBox");
			fnsWait_and_Type("UserNameTxtBox", SearchUserNameString);
			fnsWait_and_Click("SearchUserBtn");
	
			InsightoriginalHandle = driver.getWindowHandle();
			
			Thread.sleep(1000);
			fnsTable_ClickOn_LINK_ByMatchingText(SearchUserNameString);
			Thread.sleep(5000);
		
		
		}catch(Throwable t){
			throw new Exception(Throwables.getStackTraceAsString(t)+"......................");
		}
		return Customer_Name;
	}

	//Switching to NsfOnline window from Insight
	public void fncSwitchingTo_NsfOnline_from_Insight_AfterClickingOnUserName() throws Throwable{
		try{
			for(int a=0; a<=Long.parseLong(CONFIG.getProperty("ElementWaitTime")); a++){ 
				ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
								
				tabsCount = tabs.size();
								
				if(tabsCount==2){
					for (int i = 0; i < tabsCount; i++) {
						if (InsightoriginalHandle.equalsIgnoreCase(tabs.get(i))) {
							// nothing to do
						} else {
							
							driver.switchTo().window(tabs.get(i));
							Thread.sleep(2000);
							NSFOnlineoriginalHandle = driver.getWindowHandle();
							
							
							JavascriptExecutor jse = (JavascriptExecutor) driver;
							jse = (JavascriptExecutor) driver;
							jse.executeScript("window.resizeTo("+TestSuiteBase_MonitorPlan.ScreenWidth+", "+TestSuiteBase_MonitorPlan.ScreenHeight+")");
							
							
							fnsApps_Report_Logs("PASSED == Successfully Switch to NSFOnline Window.");
							break;
						}
					}
					break;
				}else{
					Thread.sleep(1000);
				}
				if(a==Long.parseLong(CONFIG.getProperty("ElementWaitTime"))){
					throw new Exception(" NSF Online window is not getting open after 60Seconds wait");
				}
			}
			  
			Thread.sleep(1500);
			
			if(Got_NsfOnline_URL){
				Thread.sleep(1500);
				String GetUrl = driver.getCurrentUrl().toLowerCase().trim();
				if(GetUrl.contains("nsfointerface")){
					NsfOnline_New_Version_RUN = false;
				}
				Got_NsfOnline_URL = false;
			}
					
			
			if(NsfOnline_New_Version_RUN){
				Thread.sleep(1500);
				fnsSwitching_into_First_Frame();
			}
			
			
			
		}catch(Throwable t){
			fnsTake_Screen_Shot("SwitchNSFOnlineWindowFail");
			throw new Exception("FAILED == NSF Online Window is not getting Open, plz refer screenshot [SwitchNSFOnlineWindowFail"+ fnsScreenShot_Date_format() +". Getting Exception  >> "+Throwables.getStackTraceAsString(t)+"......................");	
		}
	}
	
	
	//Taking NsfOnline version screen shot.
	public void fncNsfOnline_Version_Screenshot(String ScreenshotName) throws Throwable{/*
		try{
			fnsGet_Element_Enabled("NsfOnline_Version");
			WebElement NSFOnlineVersionWE=fnsGet_OR_NsfOnline_ObjectX("NsfOnline_Version");
			
			if(NsfOnline_New_Version_RUN){
				Actions action = new Actions(driver);
				action.moveToElement(NSFOnlineVersionWE).sendKeys(NSFOnlineVersionWE, Keys.CONTROL).click().build().perform();
			}else{
				Actions action = new Actions(driver);
				action.moveToElement(NSFOnlineVersionWE).keyUp(Keys.CONTROL).click().keyDown(Keys.CONTROL).build().perform();
			}
			
			
			
			Thread.sleep(8000);
			for(int v=0; v<=Long.parseLong(CONFIG.getProperty("ElementWaitTime")); v++){
				ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
								
				tabsCount = tabs.size();
									
				if(tabsCount==3){
					for (int i = 0; i < tabsCount; i++) {
						if (NSFOnlineoriginalHandle.equalsIgnoreCase(tabs.get(i))) {
							// nothing to do
						} else if(InsightoriginalHandle.equalsIgnoreCase(tabs.get(i))) {
							// nothing to do
						}else{
							driver.switchTo().window(tabs.get(i));
							fnsGet_Element_Enabled("NsfOnline_Version_BuildInfo_Element");
							fnsApps_Report_Logs("PASSED == Successfully switch to NSFOnline Version Window.");
							break;
						}
					}
					break;
				}else{
					Thread.sleep(1000);			}
				
				if(v==Long.parseLong(CONFIG.getProperty("ElementWaitTime"))){
					throw new Exception(" NSF Online Version window is not getting open after 60 Seconds wait");
				}
			}
			Thread.sleep(1000);
			fnsTake_Screen_Shot_NSFOnlineVersion(ScreenshotName);
			Thread.sleep(500);
			
			if(NsfOnline_New_Version_RUN){
				Actions action1 = new Actions(driver);
				action1.keyUp(Keys.CONTROL).build().perform();
			}
			
			driver.close();
			fnsApps_Report_Logs("PASSED == Successfully Closed NSFOnlineVersion Window.");
			Thread.sleep(1000);
			driver.switchTo().window(NSFOnlineoriginalHandle);
			fnsApps_Report_Logs("PASSED == Successfully switch to NSF Online Window");
			
			if(NsfOnline_New_Version_RUN){
				fnsSwitching_into_First_Frame();
			}
			
			fnsGet_Element_Enabled("Footer");
			Thread.sleep(1000);
			fnsGet_Element_Enabled("Footer");
		}catch(Throwable t){
			fnsTake_Screen_Shot("NSFOnlineVersionScreenShotFail");
			throw new Exception("FAILED == NSFOnline Version Window is not getting display, plz refer screenshot [NSFOnlineVersionScreenShotFail"+ fnsScreenShot_Date_format() +". Getting Exception  >> "+Throwables.getStackTraceAsString(t)+"......................");
		}
	*/}
	
	
	
	
	public void fnsSwitching_into_First_Frame() throws Throwable{
		try{
			driver.switchTo().frame(0);
			fnsApps_Report_Logs("PASSED == Frame switch done.");
			Thread.sleep(1500);
		}catch(Throwable t){
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}
	
	public void fnsSwitching_Back_to_Default() throws Throwable{
		try{
			driver.switchTo().defaultContent();
			fnsApps_Report_Logs("PASSED == default Content switch done.");
			Thread.sleep(1500);
		}catch(Throwable t){
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}	
	
	
	public void fnsNsfOnline_Open_Link_Clik_Through_JS(String Customer_Number) throws Exception{
		try{
			Thread.sleep(1500);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse = (JavascriptExecutor) driver;
			WebElement openLink = driver.findElement(By.xpath(".//tr/td[text()='" + Customer_Number + "']/../td[1]/a"));
			jse.executeScript("arguments[0].click();", openLink);
		
			fnsApps_Report_Logs("PASSED == Successfullyu click on 'OPEN' link of customer<"+Customer_Number+">.");
		Thread.sleep(10000);
	}catch(Throwable t){
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
		
	}
	
	
	
	
	//Sorting customer Name : Verify insight and nsfonline customer name is same (added on 20.4.2017)
	public void fnc_Verify_NsfOnline_CustomerName_ComingSameAsIn_Insight() throws Throwable{
		try{
			boolean CustomerName__RESTAURANT_BRAND_INTERNATIONAL__on_Staging = false;
			boolean CustomerName__BurgerKingCorporation = false;
			if( (CustomerName.toUpperCase().trim().contains("RESTAURANT BRAND INTERNATIONAL")) && (env.toLowerCase().trim().equalsIgnoreCase("staging")) ){
				CustomerName__RESTAURANT_BRAND_INTERNATIONAL__on_Staging = true;
			}
			if( (CustomerName.toLowerCase().trim().contains("burger king corporation"))  ){
				CustomerName__BurgerKingCorporation = true;
			}
			
			
			if(CustomerName_Sorting.equalsIgnoreCase("Yes")){
				String BurgerKingCop_DocumentsType = "Additional Audit Information, Additional Facility Info, Previous Audit Report, Name Change, General Information, Food Safety Audit Report, Product Claims, Product Specification";
				String Text_On_Previous_Page_Row = null;
				String Text_On_Next_Page_Row = null;
				boolean first_Row_Text_Fetched_Flag = true;
				
				fnsGet_Element_Enabled("NSFOnline_Documents_SearchResult_TableHeader");
				String Table_ColumnHeader_Row_Xpath = OR_NsfOnline.getProperty("NSFOnline_Documents_SearchResult_TableHeader")+"/tbody/tr[1]";
				Integer ClientName_ColumnNumber = TestSuiteBase_MonitorPlan.WithOut_OR_Retrun_TableColumnNo_of_MatchingColumnName(Table_ColumnHeader_Row_Xpath, "Client Name");
				Integer DocumentType_ColumnNumber = TestSuiteBase_MonitorPlan.WithOut_OR_Retrun_TableColumnNo_of_MatchingColumnName(Table_ColumnHeader_Row_Xpath, "Document Type");
								
				for(int CoulmnNo_Start = 2; CoulmnNo_Start<=26; CoulmnNo_Start++){
				
					String Text_On_Page_Row_Xpath = OR_NsfOnline.getProperty("NSFOnline_Documents_SearchResult_TableHeader")+"/tbody/tr[2]";
					String DocumentType_ColumnRow_Xpath = OR_NsfOnline.getProperty("NSFOnline_Documents_SearchResult_TableHeader")+"/tbody/tr["+CoulmnNo_Start+"]/td["+ClientName_ColumnNumber+"]";
					
					if(driver.findElements(By.xpath(DocumentType_ColumnRow_Xpath)).size()>0){
						
						if(first_Row_Text_Fetched_Flag){
							Text_On_Next_Page_Row = TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(Text_On_Page_Row_Xpath);
							first_Row_Text_Fetched_Flag = false;
						}
						
						if(Text_On_Next_Page_Row.equals(Text_On_Previous_Page_Row)){
							break;
						}else{
							String 	ClientName_Column_Text = TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(DocumentType_ColumnRow_Xpath).toLowerCase().trim();
							TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element_and_DoubleClick(DocumentType_ColumnRow_Xpath);
							
							if(ClientName_Column_Text.length()>0){
								try{
									assert (ClientName_Column_Text.contains(CustomerName.toLowerCase())):"FAILED == Insight Expected CustomerName("+CustomerName+") is not Matched with NsfOnline ActualClientName("+ClientName_Column_Text+"), Please refer screen shot ["+ CustomerName + fnsScreenShot_Date_format() + "]";
									fnsApps_Report_Logs("PASSED == Insight Expected CustomerName("+CustomerName+") is Matched with NsfOnline ActualClientName("+ClientName_Column_Text+")");
								}catch(Throwable t) {
									throw new Exception(t.getMessage());			
								}
							}else if(CustomerName__RESTAURANT_BRAND_INTERNATIONAL__on_Staging){
								Integer Cus_Number_ColumnNumber = TestSuiteBase_MonitorPlan.WithOut_OR_Retrun_TableColumnNo_of_MatchingColumnName(Table_ColumnHeader_Row_Xpath, "Cus #");
								String Cus_Number_Blank_Customer_Row_Xpath = OR_NsfOnline.getProperty("NSFOnline_Documents_SearchResult_TableHeader")+"/tbody/tr["+CoulmnNo_Start+"]/td["+Cus_Number_ColumnNumber+"]";
								String Cus_Number_ColumnText = TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(Cus_Number_Blank_Customer_Row_Xpath);

								try{
									//In Case Blank Customer Name, Script fail if Application customer number is not matched with givern customer number (added on 08.02.2018)
									assert ( (Cus_Number_ColumnText.toLowerCase().trim()).contains(Customer_No.toLowerCase().trim()) ):"FAILED == In case of Blank CustomerName ' "+CustomerName+" ', the Excel Customer Number< "+Customer_No+" > is not matched with the NsfOnline customer Number < "+Cus_Number_ColumnText+" > in row no <"+CoulmnNo_Start+">, Please refer screen shot ["+ CustomerName + fnsScreenShot_Date_format() + "]";
									fnsApps_Report_Logs("PASSED ==  In case of Blank CustomerName' "+CustomerName+" ', the Excel Customer Number< "+Customer_No+" > is matched with the NsfOnline customer Number < "+Cus_Number_ColumnText+" > in row no <"+CoulmnNo_Start+">.");
								}catch(Throwable t) {
									throw new Exception(t.getMessage());	
								}
							}else if (CustomerName__BurgerKingCorporation){
								String DocumentType_Blank_Customer_Row_Xpath = OR_NsfOnline.getProperty("NSFOnline_Documents_SearchResult_TableHeader")+"/tbody/tr["+CoulmnNo_Start+"]/td["+DocumentType_ColumnNumber+"]";
								String DocumentType_ColumnText = TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(DocumentType_Blank_Customer_Row_Xpath).toLowerCase().trim();
																					
								try{
									//In case of Blank Customer Name, Script fail if Document Type is NOT matched with the mentioned Burger King Cop. documents type an it should be "Food Safety Certificate" (added on 20.4.2017)
									assert ( !((BurgerKingCop_DocumentsType.toLowerCase().trim()).contains(DocumentType_ColumnText)) && DocumentType_ColumnText.contains("food safety certificate")):"FAILED == In case of Blank CustomerName ' "+CustomerName+" ', the DcoumentType ("+DocumentType_ColumnText+") is not coming as (Food Safety Certificate), Please refer screen shot ["+ CustomerName + fnsScreenShot_Date_format() + "]";
									fnsApps_Report_Logs("PASSED == In case of Blank CustomerName ' "+CustomerName+" ', the DcoumentType ("+DocumentType_ColumnText+") is coming as (Food Safety Certificate).");
								}catch(Throwable t) {
									throw new Exception(t.getMessage());	
								}
							}		
						}		
					}else{
						break;
					}
					
					
					if(CoulmnNo_Start==26){
						DocumentType_ColumnRow_Xpath = OR_NsfOnline.getProperty("NSFOnline_Documents_SearchResult_TableHeader")+"/tbody/tr["+(CoulmnNo_Start+1)+"]/td["+ClientName_ColumnNumber+"]";
						if(driver.findElements(By.xpath(DocumentType_ColumnRow_Xpath)).size()>0){
							throw new Exception("Pagination Failed == More than 25 records are displayed in search results on a single page");
						}else if(driver.findElements(By.xpath(OR_NsfOnline.getProperty("NSFOnline_Standards_SearchResult_NEXT_Link"))).size()>0){
							Text_On_Previous_Page_Row = Text_On_Next_Page_Row;
							fnsWait_and_Click("NSFOnline_Standards_SearchResult_NEXT_Link");
							TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(3);
							first_Row_Text_Fetched_Flag = true;
							CoulmnNo_Start =1;
						}
					}
				}
				Thread.sleep(2000);				
			} 
		}catch(Throwable t) {
			fnsTake_Screen_Shot(CustomerName);
			driver.close();
			fnsApps_Report_Logs("PASSED == Successfully Close NSFOnline Window.");
			driver.switchTo().window(MainWindowHandle);
			fnsApps_Report_Logs("PASSED == Successfully Switch to Insight Window.");
			fnsApps_Report_Logs("Sorting: "+Throwables.getStackTraceAsString(t));
			throw new Exception("Sorting: "+Throwables.getStackTraceAsString(t));			
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//################################################################### Configuration Function ############################################################################	

/*	// Function to Launch browser and login
	public boolean fnsLaunchBrowserAndLogin() throws Throwable {
		killprocess(); //Added on 17.2.2017 as some chrome browser remains opened and script took common screenshot 
		
		start = new Date();
		boolean present;
		startExecutionTime = fnpTimestamp();
		//fnpFirstTimeInitializationMethod();
		
		ScreenShotFlagWithOR_NsfOnline = true;
				
		try {

			TestSuiteBase_MonitorPlan.fns_Launch_Browser_Only();
			
			
			String userName = CONFIG.getProperty("adminUsername");
			String password = CONFIG.getProperty("adminPassword");
		//	String siteUrl = CONFIG.getProperty("insightTestsiteName");
			
						
			String siteUrl = null;
			

			if (excelSiteURL!=null) {
				if (excelSiteURL.equalsIgnoreCase("")) {
					siteUrl = CONFIG.getProperty("insightTestsiteName");
				} else {
					siteUrl=excelSiteURL;

				}
			} else {
				siteUrl = CONFIG.getProperty("insightTestsiteName");
			}
		
			
			
			driver.get(siteUrl);
		//	  
			Thread.sleep(1500);
		//	driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("ElementWaitTime")), TimeUnit.SECONDS);
			
			TestSuiteBase_MonitorPlan.fnsComman_BrowserLogin_ElementWait(OR_NsfOnline.getProperty("UserName"), CONFIG.getProperty("Element_MAX_WaitTime"));
		
			
			
			
			
			
			
			
			
			
			
			
			
			//Added on 7.6.2016
			//Step 1. Login with testscriptuser and enter the invalid password -> The application should throw Invalid message to the user.     
			//Step 2. Again login with valid password and the system should be logged in and proceed with further steps.
			if( ! Insight_Invalid_Password_Verification ){
				fnsGet_OR_NsfOnline_ObjectX("UserName").clear();
				fnsGet_OR_NsfOnline_ObjectX("UserName").sendKeys("");
				fnsGet_OR_NsfOnline_ObjectX("UserName").sendKeys(userName);
				fnsGet_OR_NsfOnline_ObjectX("password").sendKeys("");
				fnsGet_OR_NsfOnline_ObjectX("password").sendKeys(password+"Test");
				fnsGet_OR_NsfOnline_ObjectX("loginBtn").click();
				
				try{
					for(int i=0; i<=Long.parseLong(CONFIG.getProperty("Element_MAX_WaitTime")); i++){
						
						if(i==Long.parseLong(CONFIG.getProperty("Element_MAX_WaitTime"))){
							throw new Exception("FAILED == For invalid password, validation message is not coming after <"+Long.parseLong(CONFIG.getProperty("Element_MAX_WaitTime"))+">seconds wait");
						}else{
							Thread.sleep(1000);
						}
						
						if(driver.findElements(By.xpath(OR_NsfOnline.getProperty("logoutLink"))).size()>0){
							fnsApps_Report_Logs("FAILED == Verification of Insight Login for Invalid Password is failed");
							throw new Exception("FAILED == Verification of Insight Login for Invalid Password is failed");
						}else if(driver.findElements(By.xpath(OR_NsfOnline.getProperty("errorMessage"))).size()>0){

							fnsApps_Report_Logs("");fnsApps_Report_Logs("");
							fnsApps_Report_Logs("PASSED == Successfully verified Insight login for invalid password.");
							fnsApps_Report_Logs("");
							fnsApps_Report_Logs("");
							break;
						}
					}
				}catch(Throwable t){
					fnsTake_Screen_Shot("InvalidPassword_Verification_Fail");
					fnsApps_Report_Logs(Throwables.getStackTraceAsString(t)+", please refer screen shot >> InvalidPassword_Verification_Fail"+fnsScreenShot_Date_format());
					throw new Exception(Throwables.getStackTraceAsString(t)+", please refer screen shot >> InvalidPassword_Verification_Fail"+fnsScreenShot_Date_format());
				}
				Insight_Invalid_Password_Verification = true;
			}
			Thread.sleep(1500);
			
	
						
			fnsGet_OR_NsfOnline_ObjectX("UserName").clear();
			fnsGet_OR_NsfOnline_ObjectX("UserName").sendKeys("");
			fnsGet_OR_NsfOnline_ObjectX("UserName").sendKeys(userName);
			fnsGet_OR_NsfOnline_ObjectX("password").sendKeys("");
			fnsGet_OR_NsfOnline_ObjectX("password").sendKeys(password);
			fnsGet_OR_NsfOnline_ObjectX("loginBtn").click();
			
						
		//	Assert.assertEquals(false, driver.findElement(By.xpath(OR_NsfOnline.getProperty("errorMessage"))).isDisplayed());
			
			TestSuiteBase_MonitorPlan.fnsComman_BrowserLogin_ElementWait(OR_NsfOnline.getProperty("logoutLink"), CONFIG.getProperty("Element_MAX_WaitTime"));
			WebElement logOutBtn = fnsGet_OR_NsfOnline_ObjectX("logoutLink");
			Assert.assertEquals(true, logOutBtn.isDisplayed());
		
			present = true;
		
			
		}catch(NoSuchWindowException W){
			isTestCasePass = false;
			throw new Exception(W.getMessage());			}
		catch (Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot("LaunchBrowserAndLoginFail");
			present = false;
		//	ErrorUtil.addVerificationFailure(t);
		//	driver.quit();
			fnsApps_Report_Logs("'Launch Browser And Login' is getting fail, Please refer screen shot [LaunchBrowserAndLoginFail"+fnsScreenShot_Date_format()+"]."+". Getting Exception  >> "+Throwables.getStackTraceAsString(t)+"......................");
			throw new Exception("'Launch Browser And Login' is getting fail, Please refer screen shot [LaunchBrowserAndLoginFail"+fnsScreenShot_Date_format()+"]."+". Getting Exception  >> "+Throwables.getStackTraceAsString(t)+"......................");}

		return present;
}
*/

	// Function to Launch browser and login
	public boolean fnsLaunchBrowserAndLogin() throws Throwable {
		String Autoit_scriptExeFile_Path = "";
		if (browserName.equalsIgnoreCase("edge")) {
			Autoit_scriptExeFile_Path =System.getProperty("user.dir")+"\\AutoIt_scripts\\sso_edge.exe";
		}else{
			Autoit_scriptExeFile_Path =System.getProperty("user.dir")+"\\AutoIt_scripts\\sso_chrome.exe";
		}
		String userName = CONFIG.getProperty("adminUsername");
		killprocess(); 		
		start = new Date();
		boolean present;
		startExecutionTime = fnpTimestamp();
	
		ScreenShotFlagWithOR_NsfOnline = true;
				
		try {
			present = TestSuiteBase_MonitorPlan.fns_Launch_Browser_Only();			
			
			String siteUrl = null;
			
			if (excelSiteURL==null) {
				siteUrl = CONFIG.getProperty("testSiteName");
			} else{
				siteUrl = excelSiteURL;
			}
			
			
			
			fnsApps_Report_Logs("Application credentials are URL[ "+siteUrl+" ], UserName[ "+userName+" ].");
			driver.get(siteUrl);
			
			
			
			TestSuiteBase_MonitorPlan.fnsLoading_PageLoad();
			TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(OR_MtrPlan.getProperty("MicrosoftOnline_Signin_Input"));
			TestSuiteBase_MonitorPlan.WithOut_OR_fnType(OR_MtrPlan.getProperty("MicrosoftOnline_Signin_Input"), userName+"@nsf.org");
			
			TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(OR_MtrPlan.getProperty("MicrosoftOnline_Signin_Next"));
			TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(OR_MtrPlan.getProperty("MicrosoftOnline_Signin_Next"));
			TestSuiteBase_MonitorPlan.fnsLoading_PageLoad();
			TestSuiteBase_MonitorPlan.fnsLoading_PageLoad();
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
					TestSuiteBase_MonitorPlan.fnsTake_Screen_Shot_Without_OR("SSO_Not_Working");
					fnsApps_Report_Logs("FAILED == After Clicking on NEXT button from 'login.microsoftonline.com' screen, the application is not navigated to next screen (Wait Time = 120 seconds), please refer the screenshot >> SSO_Not_Working "+fnsScreenShot_Date_format());
					throw new Exception ("FAILED == After Clicking on NEXT button from 'login.microsoftonline.com' screen, the application is not navigated to next screen (Wait Time = 120 seconds), please refer the screenshot >> SSO_Not_Working "+fnsScreenShot_Date_format());
				}
			}
			TestSuiteBase_MonitorPlan.fnsLoading_PageLoad();
			Screen_URL =  driver.getCurrentUrl().toLowerCase().trim();
			if(Screen_URL.contains("auth.nsf.org")){	
				Runtime.getRuntime().exec(Autoit_scriptExeFile_Path);
			    fnsApps_Report_Logs("PASSED == ********* Successfulluy Executed the Autoit script for SSO login having exe file path [ "+Autoit_scriptExeFile_Path+" ]");
				
			    for(int i=1; i<=120; i++){
					String Screen_Title =  driver.getTitle().trim();
					System.out.println(Screen_Title);
					if(Screen_Title.equalsIgnoreCase("insight")){
						break;
					}else{
						Thread.sleep(1000);
					}
					if(i==120){
						TestSuiteBase_MonitorPlan.fnsTake_Screen_Shot_Without_OR("SSO_Not_Working");
						fnsApps_Report_Logs("FAILED == After Clicking on login button from 'auth.nsf.org' screen, the application is not navigated to insight screen (Wait Time = 120 seconds), please refer the screenshot >> SSO_Not_Working "+fnsScreenShot_Date_format());
						throw new Exception ("FAILED == After Clicking on login button from 'auth.nsf.org' screen, the application is not navigated to insight screen (Wait Time = 120 seconds), please refer the screenshot >> SSO_Not_Working "+fnsScreenShot_Date_format());
					}
				}
			}else{
				for(int i=1; i<=120; i++){
					String Screen_Title =  driver.getTitle().trim();
					System.out.println(Screen_Title);
					if(Screen_Title.equalsIgnoreCase("insight")){
						break;
					}else{
						Thread.sleep(1000);
					}
					if(i==120){
						TestSuiteBase_MonitorPlan.fnsTake_Screen_Shot_Without_OR("SSO_Not_Working");
						fnsApps_Report_Logs("FAILED == After Clicking on NEXT button from 'login.microsoftonline.com' screen, the application is not navigated to insight screen (Wait Time = 120 seconds), please refer the screenshot >> SSO_Not_Working "+fnsScreenShot_Date_format());
						throw new Exception ("FAILED == After Clicking on NEXT button from 'login.microsoftonline.com' screen, the application is not navigated to insight screen (Wait Time = 120 seconds), please refer the screenshot >> SSO_Not_Working "+fnsScreenShot_Date_format());
					}
				}				
			}
			
			TestSuiteBase_MonitorPlan.fnsLoading_PageLoad();	
			TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
			TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(4);
			
						
			TestSuiteBase_MonitorPlan.fnsComman_BrowserLogin_ElementWait(OR_NsfOnline.getProperty("logoutLink"), CONFIG.getProperty("Element_MAX_WaitTime"));
			WebElement logOutBtn = fnsGet_OR_NsfOnline_ObjectX("logoutLink");
			Assert.assertEquals(true, logOutBtn.isDisplayed());
		
			present = true;
			fnsApps_Report_Logs("Browser is launched and Successfully login into the 'insight' Application");	
			
		}catch(NoSuchWindowException W){
			isTestCasePass = false;
			throw new Exception(W.getMessage());			}
		catch (Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot("LaunchBrowserAndLoginFail");
			present = false;
			fnsApps_Report_Logs("'Launch Browser And Login' is getting fail, Please refer screen shot [LaunchBrowserAndLoginFail"+fnsScreenShot_Date_format()+"]."+". Getting Exception  >> "+Throwables.getStackTraceAsString(t)+"......................");
			throw new Exception("'Launch Browser And Login' is getting fail, Please refer screen shot [LaunchBrowserAndLoginFail"+fnsScreenShot_Date_format()+"]."+". Getting Exception  >> "+Throwables.getStackTraceAsString(t)+"......................");}

		return present;
}	
	
	
//Check for Browser Type defined in Suits Excel 
public void BrowserCheck(){
		String Browser=null;
		
		for(int i=2; i <= suiteXls.getRowCount("Test Suite") ;i++ ){
			
			if(suiteXls.getCellData("Test Suite", "TSID", i).equalsIgnoreCase("NSFOnline_Suite")){
				BrowserName=suiteXls.getCellData("Test Suite", "Browser", i);
				
				if(BrowserName.equals("")){
					BrowserName="IE";
				}
				
				break;
			}
		}
}
		
	

//check if the suite has to be skip
	@BeforeSuite
	public void checkSuiteSkip() throws Throwable{
		/*currentSuiteName = "NSFOnline_Suite";
		TestSuiteBase_MonitorPlan.fnsMoveMousePointerAtCenterBottomOfScreen();
		fnInitialize();
		BrowserCheck();
		if(TestUtil.isSuiteRunnable(suiteXls, "NSFOnline_Suite")){
			FileUtils.deleteDirectory(new File((System.getProperty("user.dir") + CONFIG.getProperty("screenshots_path")+"//Insight//")));
			browserName=TestUtil.getBrowserName(suiteXls, "NSFOnline_Suite");
			excelSiteURL= TestUtil.getExcelSiteURL(suiteXls, currentSuiteName);
			
			int rowNum = suiteXls.getCellRowNum("Test Suite", "TSID", currentSuiteName);
			suiteXls.setCellData("Test Suite", "Last_Execution", rowNum, fnReturnDateWithTimeForExcel());
			
		}
		if(!TestUtil.isSuiteRunnable(suiteXls, "NSFOnline_Suite")){
			fnsApps_Report_Logs("Skipped NSFOnline_Suite as the runmode was set to NO");
			throw new SkipException("Runmode of NSFOnline_Suite set to no. So Skipping all tests in NSFOnline_Suite");}	*/	
		fns_CheckSiteSkip("NSFOnline_Suite");	
}


	public String fnsRemoveFormatting(String s) {

			s = s.replace("[", " ");
			s = s.replace("]", " ");
			s = s.replace("(", " ");
			s = s.replace(")", " ");
			s = s.trim();

			return s;
}
		

	
	
	public void TestCaseSkip(Integer counts, String customervalue)throws SkipException {
		try{
			//counts++;
			if (!(runmodes[counts].equalsIgnoreCase("Y"))) {
				fnsApps_Report_Logs("****************************************************************************************************************************************");
				fnsApps_Report_Logs("################################## Runmode of Case::" + (counts + 1)+" for Customer["+customervalue+"]  is set to NO, So Skipping this Case.");
				//skip=true;
				System.out.println("if TCSkipReturnValue ="+TCSkipReturnValue +" count"+counts);
				throw new SkipException("Skipping Test Case " + " as runmode set to NO");}
		}catch(SkipException sk){
			System.out.println("SkipException TCSkipReturnValue ="+TCSkipReturnValue+" count"+counts);}
	}
	
	
	
	
//Always run after suite
	@AfterSuite(alwaysRun=true)
	public void Finishing_NSFOnline_Suite_Script() throws Throwable {
		ScreenShotFlagWithOR_NsfOnline = false;
		fnsApps_Report_Logs("");
		fnsApps_Report_Logs("########################################################### NSFOnline Suite END ########################################################## ");
		fnsApps_Report_Logs("=========================================================================================================================================");
		fnsApps_Report_Logs("");
}	
	
	
	//
	public void MoveMouseCursorToTaskBar(){
		try{
			Actions testact=new Actions(driver);
			testact.moveByOffset(-1500, -1500).build().perform();
		}catch(Throwable t){
			//nothing to do
		}
	}
	
	
/*	// Function to Launch the browser
	public void fnLaunch_Ipulse_BrowserAndLogin() throws Throwable {
		
		try {
			if (BrowserName.equalsIgnoreCase("IE")) {
				System.setProperty("webdriver.ie.driver", CONFIG.getProperty("IEDriverPath"));
				DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
				caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				caps.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
				caps.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
			    driver=new InternetExplorerDriver(caps);
				fnsApps_Report_Logs("Browser type is IE");
			}
			
			if (BrowserName.equalsIgnoreCase("chrome")) {
				
				System.setProperty("webdriver.chrome.driver", CONFIG.getProperty("ChromeDriverPath"));
				DesiredCapabilities caps = DesiredCapabilities.chrome();
				ChromeOptions options = new ChromeOptions();
				options.addArguments("test-type");
				caps.setCapability(ChromeOptions.CAPABILITY, options);																																												
                driver=new ChromeDriver(caps);				
				fnsApps_Report_Logs("Browser type is CHROME");
			}

			if (BrowserName.equalsIgnoreCase("firefox")) {
				driver = new FirefoxDriver();
				fnsApps_Report_Logs("Browser type is firefox");
			}



			String userName = CONFIG.getProperty("adminUsername");
			String password = CONFIG.getProperty("adminPassword");
			String siteUrl = CONFIG.getProperty("testSiteName");
			
			
		
			driver.get(siteUrl);
			  
			
			TestSuiteBase_MonitorPlan.fnsComman_BrowserLogin_ElementWait(OR_MtrPlan.getProperty("UserName"), CONFIG.getProperty("Element_MAX_WaitTime"));
			
			TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(OR_MtrPlan.getProperty("UserName")).clear();
			TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(OR_MtrPlan.getProperty("UserName")).sendKeys("");
			TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(OR_MtrPlan.getProperty("UserName")).sendKeys(userName);
			TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(OR_MtrPlan.getProperty("password")).sendKeys("");
			TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(OR_MtrPlan.getProperty("password")).sendKeys(password);
			TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(OR_MtrPlan.getProperty("Login")).click();
			
			TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(10);
			
			for(int i=0; i<=Long.parseLong(CONFIG.getProperty("Element_MAX_WaitTime")); i++){
			
				if(driver.findElements(By.xpath(OR_MtrPlan.getProperty("Login_errorMessage"))).size()>0){
					String ErrorMsg = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(OR_MtrPlan.getProperty("Login_errorMessage")).getText();
					throw new Exception("I-pulse Login Failed, Application Error <"+ErrorMsg+">");
				}
				
				if(driver.findElements(By.xpath(OR_MtrPlan.getProperty("logOutBtn"))).size()>0){
					TestSuiteBase_MonitorPlan.fnsComman_BrowserLogin_ElementWait(OR_MtrPlan.getProperty("logOutBtn"), CONFIG.getProperty("Element_MAX_WaitTime"));
					break;
				}
				if(i==Long.parseLong(CONFIG.getProperty("Element_MAX_WaitTime"))){
					throw new Exception("After Login, Home Page is not getting load after <"+Long.parseLong(CONFIG.getProperty("Element_MAX_WaitTime"))+">seconds wait.");
				}
			}
							
			TestSuiteBase_MonitorPlan.fnsComman_BrowserLogin_ElementWait(OR.getProperty("logOutBtn"), CONFIG.getProperty("Element_MAX_WaitTime"));
			
		}catch(NoSuchWindowException W){
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		}
		catch (Throwable t) {
			fnsTake_Screen_Shot("LaunchBrowserAndLoginFail");
			isTestCasePass = false;
			fnsApps_Report_Logs("'Launch Browser And Login' is getting fail, Please refer screen shot [LaunchBrowserAndLoginFail"+fnsScreenShot_Date_format()+"]."+". Getting Exception  >> "+Throwables.getStackTraceAsString(t)+"......................");
			throw new Exception("'Launch Browser And Login' is getting fail, Please refer screen shot [LaunchBrowserAndLoginFail"+fnsScreenShot_Date_format()+"]."+". Getting Exception  >> "+Throwables.getStackTraceAsString(t)+"......................");
		}
		
		
	}*/

	
	//Get Excel Cell value by column name
	public String fnsReturn_ExcelCellValue_ByMatchingColumnName(String SheetName, String ColumnName) throws Throwable{
		try{
			String CellValue = null;
			for(int i=10; i<50; i++){
				for(int j=0; j<10; j++){
					String ExcelCellValue = NSFOnline_suitexls.getCellData(SheetName, j, i);
					if(ExcelCellValue.equalsIgnoreCase(ColumnName)){
						CellValue = NSFOnline_suitexls.getCellData(SheetName, j, i+1);
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
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));	}
	}
	
	
/*//New Method to click or not on OPEN link and verify Default view coming in switch view DD
	public void fncClicking_or_not_on_OPEN_Link_and_Verify_SwitchView_DD_default_View(String Customer_No, String User_Multi_Access) throws Throwable{
		try{

			String Open_Link_Screen_PageSource = driver.getPageSource().trim();
			Default_Value_Selection = false;
			
			if( (User_Multi_Access.trim()).equalsIgnoreCase("Yes") ){
				if( (Open_Link_Screen_PageSource.toLowerCase()).contains("please click on the 'open' link to see the account details")  ) {
					
					fnsNsfOnline_Open_Link_Clik_Through_JS(Customer_No);
					
					try{
						fnsGet_Element_Enabled("NSFOnline_HomeMenu_Link");
					}catch(Throwable t){
						throw new Exception ("FAILED == After clicking on the 'open' link, NsfOnline page is not getting load after "+( (Long.parseLong(CONFIG.getProperty("ElementWaitTime")))*2)+" seconds wait. Getting Exception >>"+Throwables.getStackTraceAsString(t));
					}
					
					try{	
						fnsGet_Element_Enabled("NsfOnline_Switch_View_DropDown");
					}catch (Throwable t){
						throw new Exception ("FAILED == After Clicking on 'OPEN' Link, 'Switch View' drop down is not coming on NsfOnline Home Screen, plz see screenshot >> NsfOnline_Switch_View_DropDown"  + fnsScreenShot_Date_format() );
					}	
					
					// validation for default customer
					WebElement SwitchView_DD_Elements = fnsGet_OR_NsfOnline_ObjectX("NsfOnline_Switch_View_DropDown");
					List<WebElement> SwitchView_DD_Attributes = SwitchView_DD_Elements.findElements(By.tagName("option"));
					boolean Default_Value_Found = false;
					
					for(WebElement SwitchView_DD_Attributes_Elements:SwitchView_DD_Attributes){
						String Attribute_Element_Text = SwitchView_DD_Attributes_Elements.getText().trim();
						if( (Attribute_Element_Text.toLowerCase()).contains(Customer_No.toLowerCase()) ){
							String Get_Selected_Attribute_Value = SwitchView_DD_Attributes_Elements.getAttribute("selected");
							try{
								assert !( Get_Selected_Attribute_Value==null ):"FAILED == By default View for customer <"+Customer_No+"> is not coming into the 'Switch View' Drop Down, Please refer attached screen shot >> Defalut_Value_Display_Fail"+fnsScreenShot_Date_format();
								fnsApps_Report_Logs("PASSED == Successfully verified that the default value coming into the 'Switch View' Drop Down for customer <"+Customer_No+">.");
								Default_Value_Found = true;
								Default_Value_Selection = true;
								break;
							}catch (Throwable t){
								fnsTake_Screen_Shot("Defalut_Value_Display_Fail");
								throw new Exception (Throwables.getStackTraceAsString(t));
							}	
						}
						
						if(Default_Value_Found){
							fnsTake_Screen_Shot("Defalut_Value_Display_Fail");
							throw new Exception ("FAILED == By default View for customer <"+Customer_No+"> is not coming into the 'Switch View' Drop Down, Please refer attached screen shot >> Defalut_Value_Display_Fail"+fnsScreenShot_Date_format());
						}
					}	
				} 
				
				
				try{
					fnsGet_Element_Enabled("NSFOnline_HomeMenu_Link");
				}catch(Throwable t){
					throw new Exception ("FAILED == NsfOnline page is not getting load after "+( (Long.parseLong(CONFIG.getProperty("ElementWaitTime")))*2)+" seconds wait. Getting Exception >>"+Throwables.getStackTraceAsString(t));
				}
				
				
				if(	! Default_Value_Selection){
					if(driver.findElements(By.xpath(OR_NsfOnline.getProperty("NsfOnline_Switch_View_DropDown"))).size()>0){
						TestSuiteBase_MonitorPlan.WithOut_OR_fnDD_SelectValue_By_Contain_Text_and_by_KEYS( OR_NsfOnline.getProperty("NsfOnline_Switch_View_DropDown"), "option", Customer_No);
						fnsGet_Element_Enabled("NsfOnline_Switch_View_GO_Bttn");
						fnsWait_and_Click("NsfOnline_Switch_View_GO_Bttn");
						TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(4);
					}else {
						fnsTake_Screen_Shot("SwitchView_DD_Not_display_for_MultiAccess_user");
						throw new Exception ("FAILED == 'Switch View' DropDown is not displayed eventhough the user have the multi access rights, please refer screen shot >> SwitchView_DD_Not_display_for_MultiAccess_user"+fnsScreenShot_Date_format());
					}
				}
			}else {
				if( (Open_Link_Screen_PageSource.toLowerCase()).contains("please click on the 'open' link to see the account details") || (driver.findElements(By.xpath(OR_NsfOnline.getProperty("NsfOnline_Switch_View_DropDown"))).size()>0) ){
					fnsTake_Screen_Shot("User_Not_Have_MultiAccess");
					throw new Exception ("FAILED == As Expected 'Switch View' DropDown/AccountList Screen should not display as user doesn't have multi access rights, please refer screen shot >> User_Not_Have_MultiAccess"+fnsScreenShot_Date_format());
				}else try{
					fnsGet_Element_Enabled("NSFOnline_HomeMenu_Link");
				}catch(Throwable t){
					throw new Exception ("FAILED == NsfOnline page is not getting load after "+( (Long.parseLong(CONFIG.getProperty("ElementWaitTime")))*2)+" seconds wait. Getting Exception >>"+Throwables.getStackTraceAsString(t));
				}
				
				if( (driver.findElements(By.xpath(OR_NsfOnline.getProperty("NsfOnline_Switch_View_DropDown"))).size()>0) ){
					fnsTake_Screen_Shot("User_Not_Have_MultiAccess");
					throw new Exception ("FAILED == As Expected 'Switch View' DropDown/AccountList Screen should not display as user doesn't have multi access rights, please refer screen shot >> User_Not_Have_MultiAccess"+fnsScreenShot_Date_format());
				}
				
				
				
			}	
		}catch(Throwable t){
			isTestCasePass = false;
			driver.close();
			fnsApps_Report_Logs("PASSED == Successfully Close NSFOnline Window.");
			driver.switchTo().window(MainWindowHandle);
			fnsApps_Report_Logs("PASSED == Successfully Switch to Insight Window.");
			throw new Exception (Throwables.getStackTraceAsString(t)+"......");
		}
		
	}*/
	
	public void fnsDD_value_Select_By_MatchingText_and_SelectVerification(String ddClickXpathKey, String ddListXpathKey, String Value) throws Throwable {
		boolean ValueNotMatched=true;
		boolean DD_Selected_Verification = false;
		List<WebElement> objectlist5 = null;
		
		for(int i=1; i<=10; i++){		
			try{
				for(int k=1; k<=(20); k++){
					fnsGet_Element_Enabled(ddClickXpathKey);
					fnsWait_and_Click(ddClickXpathKey);
					
					fnsGet_Element_Enabled(ddListXpathKey);
					fnsGet_Element_Displayed(ddListXpathKey);
					objectlist5=fnsGet_OR_NsfOnline_ObjectX(ddListXpathKey).findElements(By.tagName("li"));
					if(objectlist5.size()>1){
						break;
					}else{
						Thread.sleep(2000);;
					}
					if(k==20){
						throw new Exception ("Drop down is not getting load, after 40 seconds wait.");
					}
				}
				fnsApps_Report_Logs("PASSED == Count of No. of values coming in Drop down list is = "+objectlist5.size());
				
				for(WebElement dd_value:objectlist5){
					if(dd_value.getText().toLowerCase().trim().contains(Value.toLowerCase())){
						dd_value.click();
						Thread.sleep(500);
						ValueNotMatched=false;
						break;
					}
				}
				if(ValueNotMatched==true){
					throw new Exception("Excel value is not matched with DropDown Value.");
				}else{
					fnsApps_Report_Logs("PASSED == Successfully select value [ "+Value+" ] from drop down, having xpath >>  " + ddClickXpathKey);
				}
				
				
				fnsGet_Element_Enabled(ddClickXpathKey);
				fnsWait_and_Click(ddClickXpathKey);
				fnsGet_Element_Enabled(ddListXpathKey);
				fnsGet_Element_Displayed(ddListXpathKey);
				objectlist5=fnsGet_OR_NsfOnline_ObjectX(ddListXpathKey).findElements(By.tagName("li"));
				
				for(WebElement dd_value:objectlist5){
					String Selected_Attribute = dd_value.getAttribute("class").trim();
					
					if(Selected_Attribute.equalsIgnoreCase("k-item ng-scope k-state-selected k-state-focused")){
						DD_Selected_Verification=true;
						break;
					}
				}
				if(DD_Selected_Verification==false){
					throw new Exception("Selected value is not displaying into the DropDown.");
				}else{
					fnsApps_Report_Logs("PASSED == Successfully Verified that the selected value [ "+Value+" ] is displayed into the drop down.");
					break;
				}
				
				
			}catch(NoSuchWindowException W){
				isTestCasePass = false;
					throw new Exception(W.getMessage());			
			}catch(Throwable t) {
				if(i==10){
					isTestCasePass = false;
					fnsTake_Screen_Shot("DdValueSelectFail" + ddClickXpathKey);
					fnsApps_Report_Logs("FAILED == Unable to select value from drop down [ " + ddClickXpathKey +" ],plz see screenshot [ DdValueSelectFail" + ddClickXpathKey + fnsScreenShot_Date_format() + " ]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(t).trim());
					throw new Exception("FAILED == Unable to select value from drop down [ " + ddClickXpathKey +" ],plz see screenshot [ DdValueSelectFail" + ddClickXpathKey + fnsScreenShot_Date_format() + " ]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(t).trim());
				}else{
					Thread.sleep(2000);;
				}
			}	
		}
	}
	
//New Method to click or not on OPEN link and verify Default view coming in switch view DD
	public void fnsSwitchViewAccount_Functionality(String Customer_No, String Customer_Name, String User_Multi_Access) throws Throwable{
		try{
			if(Customer_No.toLowerCase().trim().equals("c0196359")){
				//Customer_Name = "Global Food Safety Initiative - Whitbread Plc (C0196359)";
			//	Customer_Name = Customer_No;
			}
			Customer_Name = Customer_No;
			boolean Acknowledge_popup_Click_done = false;
			boolean Acknowledge_popup_is_Coming = false;	
			NewNsfOnline_Element_Max_Wait_Time = 600;
			TestSuiteBase_New_NSFOnline.fnsLoading_PageLoad(300);
			TestSuiteBase_New_NSFOnline.fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
			for(int i=0; i<=Long.parseLong(CONFIG.getProperty("ElementWaitTime")); i++){
				try{
					if( (driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("NewNsfOnline_Content_Model_Popup"))).size()>0)  && (Acknowledge_popup_Click_done==false)){
						System.out.println("Enter into modle loop");
						//Thread.sleep(3000);
						for(int j=0; j<=Long.parseLong(CONFIG.getProperty("ElementWaitTime")); j++){
							try{
								if( (driver.findElement(By.xpath(OR_New_NSFOnline.getProperty("NewNsfOnline_Content_Model_Popup"))).isDisplayed()) ){
									/*TestSuiteBase_New_NSFOnline.fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
									TestSuiteBase_New_NSFOnline.fnsLoading_Progressing_on_Popup_wait_for_Popup(2);*/
									Thread.sleep(2000);
									String ErrorMsg = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(OR_New_NSFOnline.getProperty("NewNsfOnline_Content_Model_Popup")).getText().trim();
									if(ErrorMsg.toLowerCase().contains("This is a non-production environment - Do NOT upload sensitive, confidential, NSF or customer data".toLowerCase())){
										fnsApps_Report_Logs("PASSED == Acknowledge Popup is coming.");
										Acknowledge_popup_is_Coming = true;
										break;
									}else{
										throw new Exception("Getting Popup Error <"+ErrorMsg+">"+" , Please refer the screen shot >> LoginFail"+fnsScreenShot_Date_format());
									}
								}/*else{
									Thread.sleep(1000);
								}*/
							}catch(Throwable t){
								if(driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("Sign_Out"))).size()>0){						
									break;
								}else if(driver.findElements(By.xpath(OR_NsfOnline.getProperty("NSFOnline_HomeMenu_Link"))).size()>0){								
									break;
								}else if(driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("NewNsfOnline_ClassicView_Link"))).size()>0){
									break;
								}else{
									Thread.sleep(1000);
								}
								System.out.println("Modle exception");
							}								
							/*if(j==Long.parseLong(CONFIG.getProperty("ElementWaitTime"))){
								throw new Exception("Acknowledge Alert Popup is NOT coming just after login through ipulse, Please refer the screen shot >> LoginFail"+fnsScreenShot_Date_format());
							}*/
						}
					
						if(Acknowledge_popup_is_Coming){
							TestSuiteBase_New_NSFOnline.fnsGet_Element_Enabled_By_Passing_Wait_and_Screenshot_Condition(60, false, "Acknowledge_Acknowledge_bttn");
							TestSuiteBase_New_NSFOnline.fnsWait_and_Click("Acknowledge_Acknowledge_bttn");
							Acknowledge_popup_Click_done = true;
							Thread.sleep(4000);
							break;
						}						
					}else if(driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("Sign_Out"))).size()>0){	
						if(Acknowledge_popup_Click_done==false){
							for(int j=0; j<=Long.parseLong(CONFIG.getProperty("ElementWaitTime")); j++){
								try{
									if( (driver.findElement(By.xpath(OR_New_NSFOnline.getProperty("NewNsfOnline_Content_Model_Popup"))).isDisplayed()) ){
										/*TestSuiteBase_New_NSFOnline.fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
										TestSuiteBase_New_NSFOnline.fnsLoading_Progressing_on_Popup_wait_for_Popup(2);*/
										Thread.sleep(2000);
										String ErrorMsg = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(OR_New_NSFOnline.getProperty("NewNsfOnline_Content_Model_Popup")).getText().trim();
										if(ErrorMsg.toLowerCase().contains("This is a non-production environment - Do NOT upload sensitive, confidential, NSF or customer data".toLowerCase())){
											fnsApps_Report_Logs("PASSED == Acknowledge Popup is coming.");
											Acknowledge_popup_is_Coming = true;
											break;
										}else{
											throw new Exception("Getting Popup Error <"+ErrorMsg+">"+" , Please refer the screen shot >> LoginFail"+fnsScreenShot_Date_format());
										}
									}else{
										Thread.sleep(1000);
									}
								}catch(Throwable t){
									Thread.sleep(500);
								}								
								if(j==Long.parseLong(CONFIG.getProperty("ElementWaitTime"))){
									throw new Exception("Acknowledge Alert Popup is NOT coming just after login through ipulse, Please refer the screen shot >> LoginFail"+fnsScreenShot_Date_format());
								}
							}
						
							if(Acknowledge_popup_is_Coming){
								TestSuiteBase_New_NSFOnline.fnsGet_Element_Enabled_By_Passing_Wait_and_Screenshot_Condition(60, false, "Acknowledge_Acknowledge_bttn");
								TestSuiteBase_New_NSFOnline.fnsWait_and_Click("Acknowledge_Acknowledge_bttn");
								Acknowledge_popup_Click_done = true;
								Thread.sleep(4000);
							}
						}
						
						Thread.sleep(1000);
						break;
					}else if(driver.findElements(By.xpath(OR_NsfOnline.getProperty("NSFOnline_HomeMenu_Link"))).size()>0){								
						break;
					}else if(driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("NewNsfOnline_ClassicView_Link"))).size()>0){
						Thread.sleep(3000);
						if(Acknowledge_popup_Click_done==false){
							for(int j=0; j<=Long.parseLong(CONFIG.getProperty("ElementWaitTime")); j++){
								try{
									if( (driver.findElement(By.xpath(OR_New_NSFOnline.getProperty("NewNsfOnline_Content_Model_Popup"))).isDisplayed()) ){
										/*TestSuiteBase_New_NSFOnline.fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
										TestSuiteBase_New_NSFOnline.fnsLoading_Progressing_on_Popup_wait_for_Popup(2);*/
										Thread.sleep(2000);
										String ErrorMsg = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(OR_New_NSFOnline.getProperty("NewNsfOnline_Content_Model_Popup")).getText().trim();
										if(ErrorMsg.toLowerCase().contains("This is a non-production environment - Do NOT upload sensitive, confidential, NSF or customer data".toLowerCase())){
											fnsApps_Report_Logs("PASSED == Acknowledge Popup is coming.");
											Acknowledge_popup_is_Coming = true;
											break;
										}else{
											throw new Exception("Getting Popup Error <"+ErrorMsg+">"+" , Please refer the screen shot >> LoginFail"+fnsScreenShot_Date_format());
										}
									}else{
										Thread.sleep(1000);
									}
								}catch(Throwable t){
									Thread.sleep(500);
								}								
								if(j==Long.parseLong(CONFIG.getProperty("ElementWaitTime"))){
									throw new Exception("Acknowledge Alert Popup is NOT coming just after login through ipulse, Please refer the screen shot >> LoginFail"+fnsScreenShot_Date_format());
								}
							}
						
							if(Acknowledge_popup_is_Coming){
								TestSuiteBase_New_NSFOnline.fnsGet_Element_Enabled_By_Passing_Wait_and_Screenshot_Condition(60, false, "Acknowledge_Acknowledge_bttn");
								TestSuiteBase_New_NSFOnline.fnsWait_and_Click("Acknowledge_Acknowledge_bttn");
								Acknowledge_popup_Click_done = true;
								Thread.sleep(4000);
							}
						}
						
						Thread.sleep(1000);
						try{
							if(driver.findElement(By.xpath(OR_New_NSFOnline.getProperty("NewNsfOnline_ClassicView_Link"))).isDisplayed()){
								TestSuiteBase_New_NSFOnline.fnsLoading_Progressing_wait_AlertLaoder(2);
								TestSuiteBase_New_NSFOnline.fnsLoading_Progressing_wait(2);
								//driver.findElement(By.linkText("Classic View")).click();
								TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(OR_New_NSFOnline.getProperty("NewNsfOnline_ClassicView_Link"));
								TestSuiteBase_New_NSFOnline.fnsLoading_Progressing_wait(2);
							}
						}catch(Throwable t){
							//nothing to do
						}
						break;
					}else{					
						Thread.sleep(1000);
					}
				}catch(Throwable t){
					if(i==Long.parseLong(CONFIG.getProperty("ElementWaitTime"))){
						fnsTake_Screen_Shot("LandingScreen_Not_display");
						throw new Exception("FAILED == Landing screen is not getting display, please refer the screen shot >> LandingScreen_Not_display"+fnsScreenShot_Date_format());
					}else{
						Thread.sleep(1000);
					}
					/*fnsTake_Screen_Shot("LandingScreen_Not_display");
					throw new Exception("FAILED == Landing screen is not getting display, please refer the screen shot >> LandingScreen_Not_display"+fnsScreenShot_Date_format()+Throwables.getStackTraceAsString(t));
				*/}						
			}
			TestSuiteBase_New_NSFOnline.fnsLoading_Progressing_on_Popup_wait_for_Popup(1);
			TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(1);
			String Open_Link_Screen_PageSource = driver.getPageSource().trim();
			boolean Default_ExpectedCorrectAccount_Selected = false;
			
			if( (User_Multi_Access.trim()).equalsIgnoreCase("Yes") ){
				if( (Open_Link_Screen_PageSource.toLowerCase()).contains("please click on the 'open' link to see the account details")  ) {
					fnsTake_Screen_Shot("Open_Link_Screen_is_Coming");
					throw new Exception("FAILED == 'NSFO-1' switch account (OPEN link) screen is coming instead of 'NSFO-2' switch account screen, please refer the screen shot >> Open_Link_Screen_is_Coming"+fnsScreenShot_Date_format());
				}else if( (driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("SwitchAccount_DD_Label"))).size()>0) ){
					if(Acknowledge_popup_Click_done==false){
						for(int i=0; i<=Long.parseLong(CONFIG.getProperty("ElementWaitTime")); i++){
							try{
								if( (driver.findElement(By.xpath(OR_New_NSFOnline.getProperty("NewNsfOnline_Content_Model_Popup"))).isDisplayed()) ){
									/*TestSuiteBase_New_NSFOnline.fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
									TestSuiteBase_New_NSFOnline.fnsLoading_Progressing_on_Popup_wait_for_Popup(2);*/
									Thread.sleep(2000);
									String ErrorMsg = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(OR_New_NSFOnline.getProperty("NewNsfOnline_Content_Model_Popup")).getText().trim();
									if(ErrorMsg.toLowerCase().contains("This is a non-production environment - Do NOT upload sensitive, confidential, NSF or customer data".toLowerCase())){
										fnsApps_Report_Logs("PASSED == Acknowledge Popup is coming.");
										Acknowledge_popup_is_Coming = true;
										break;
									}else{
										throw new Exception("Getting Popup Error <"+ErrorMsg+">"+" , Please refer the screen shot >> LoginFail"+fnsScreenShot_Date_format());
									}
								}else{
									Thread.sleep(1000);
								}
							}catch(Throwable t){
								Thread.sleep(500);
							}								
							if(i==Long.parseLong(CONFIG.getProperty("ElementWaitTime"))){
								throw new Exception("Acknowledge Alert Popup is NOT coming just after login through ipulse, Please refer the screen shot >> LoginFail"+fnsScreenShot_Date_format());
							}
						}
					
						if(Acknowledge_popup_is_Coming){
							TestSuiteBase_New_NSFOnline.fnsGet_Element_Enabled_By_Passing_Wait_and_Screenshot_Condition(60, false, "Acknowledge_Acknowledge_bttn");
							TestSuiteBase_New_NSFOnline.fnsWait_and_Click("Acknowledge_Acknowledge_bttn");
							Acknowledge_popup_Click_done = true;
							Thread.sleep(4000);
						}
					}
					fnsDD_value_Select_By_MatchingText_and_SelectVerification("SwitchAccount_DD_Label", "SwitchAccount_DD", Customer_No);
					TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(OR_New_NSFOnline.getProperty("WelcomeScreen_SwitchHeaderBlue_Section"));
					Thread.sleep(1500);
					//TestSuiteBase_MonitorPlan.WithOut_OR_fnDD_SelectValue_By_ContainsText(OR_New_NSFOnline.getProperty("SwitchAccount_DD_Label"), OR_New_NSFOnline.getProperty("SwitchAccount_DD"), "li", Customer_No);//Stop Working on 4.11.2019
					TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(OR_New_NSFOnline.getProperty("WelcomeScreen_SwitchAccount_GO_Bttn"));
					/*TestSuiteBase_New_NSFOnline.fnsLoading_Progressing_wait(3);*/
					Thread.sleep(4000);
					for(int i=1; i<=2; i++){
						try{
							if(driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("SwitchAccount_button"))).size()>0){
								TestSuiteBase_New_NSFOnline.fnsLoading_Progressing_wait_AlertLaoder(2);
								TestSuiteBase_New_NSFOnline.fnsLoading_Progressing_wait(2);
								//driver.findElement(By.linkText("Classic View")).click();
								TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(OR_New_NSFOnline.getProperty("NewNsfOnline_ClassicView_Link"));
								/*TestSuiteBase_New_NSFOnline.fnsLoading_Progressing_wait(3);*/
								Thread.sleep(4000);
								break;
							}else if(driver.findElements(By.xpath(OR_NsfOnline.getProperty("NsfOnline_Switch_View_DropDown"))).size()>0){								
								break;
							}else{
								Thread.sleep(10000);
							}
						}catch(Throwable t){
							if(i==2){
								fnsTake_Screen_Shot("SwitchAccount_not_Coming_After_SwitchedAccount");
								throw new Exception("FAILED == After selection <"+Customer_Name+"> from switch account drop down on 'welcome screen' and click on GO then at the next screen 'Switch Account' is not coming, Please refer the screen shot >> SwitchAccount_not_Coming_After_SwitchedAccount"+fnsScreenShot_Date_format()+fnsScreenShot_Date_format()+Throwables.getStackTraceAsString(t));	
							
							}else{
								Thread.sleep(10000);
							}
						}						
					}
					Default_ExpectedCorrectAccount_Selected = true;
				}else if(driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("SwitchAccount_button"))).size()>0){
					if(Acknowledge_popup_Click_done==false){	
						for(int i=0; i<=Long.parseLong(CONFIG.getProperty("ElementWaitTime")); i++){
							try{
								if( (driver.findElement(By.xpath(OR_New_NSFOnline.getProperty("NewNsfOnline_Content_Model_Popup"))).isDisplayed()) ){
									/*TestSuiteBase_New_NSFOnline.fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
									TestSuiteBase_New_NSFOnline.fnsLoading_Progressing_on_Popup_wait_for_Popup(2);*/
									Thread.sleep(4000);
									String ErrorMsg = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(OR_New_NSFOnline.getProperty("NewNsfOnline_Content_Model_Popup")).getText().trim();
									if(ErrorMsg.toLowerCase().contains("This is a non-production environment - Do NOT upload sensitive, confidential, NSF or customer data".toLowerCase())){
										fnsApps_Report_Logs("PASSED == Acknowledge Popup is coming.");
										Acknowledge_popup_is_Coming = true;
										break;
									}else{
										throw new Exception("Getting Popup Error <"+ErrorMsg+">"+" , Please refer the screen shot >> LoginFail"+fnsScreenShot_Date_format());
									}
								}else{
									Thread.sleep(1000);
								}
							}catch(Throwable t){
								Thread.sleep(500);
							}								
							if(i==Long.parseLong(CONFIG.getProperty("ElementWaitTime"))){
								throw new Exception("Acknowledge Alert Popup is NOT coming just after login through ipulse, Please refer the screen shot >> LoginFail"+fnsScreenShot_Date_format());
							}
						}
						
						if(Acknowledge_popup_is_Coming){
							TestSuiteBase_New_NSFOnline.fnsGet_Element_Enabled_By_Passing_Wait_and_Screenshot_Condition(60, false, "Acknowledge_Acknowledge_bttn");
							TestSuiteBase_New_NSFOnline.fnsWait_and_Click("Acknowledge_Acknowledge_bttn");
							Acknowledge_popup_Click_done = true;
							Thread.sleep(4000);
						}
				}
					
					
					
					TestSuiteBase_New_NSFOnline.fnsLoading_Progressing_wait_AlertLaoder(2);
					TestSuiteBase_New_NSFOnline.fnsLoading_Progressing_wait(2);
					//driver.findElement(By.linkText("Classic View")).click();
					TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(OR_New_NSFOnline.getProperty("NewNsfOnline_ClassicView_Link"));
					/*TestSuiteBase_New_NSFOnline.fnsLoading_Progressing_wait(3);*/
					Thread.sleep(4000);
					Default_ExpectedCorrectAccount_Selected = false;
				}else if(driver.findElements(By.xpath(OR_NsfOnline.getProperty("NsfOnline_Switch_View_DropDown"))).size()>0){
					Default_ExpectedCorrectAccount_Selected = false;
				}else {
					fnsTake_Screen_Shot("SwitchView_DD_Not_display_for_MultiAccess_user");
					throw new Exception ("FAILED == 'Switch View Account' Drop Down is not coming eventhough the user have the multi access rights, please refer screen shot >> SwitchView_DD_Not_display_for_MultiAccess_user"+fnsScreenShot_Date_format());
				}
				
				
				
				if(Default_ExpectedCorrectAccount_Selected){
					fnsGet_Element_Enabled("NSFOnline_HomeMenu_Link");
					WebElement SwitchView_DD_Elements = fnsGet_OR_NsfOnline_ObjectX("NsfOnline_Switch_View_DropDown");
					List<WebElement> SwitchView_DD_Attributes = SwitchView_DD_Elements.findElements(By.tagName("option"));
					boolean Default_Value_Found = false;
					
					for(WebElement SwitchView_DD_Attributes_Elements:SwitchView_DD_Attributes){
						String Attribute_Element_Text = SwitchView_DD_Attributes_Elements.getText().trim();
						if( (Attribute_Element_Text.toLowerCase()).contains(Customer_No.toLowerCase()) ){
							String Get_Selected_Attribute_Value = SwitchView_DD_Attributes_Elements.getAttribute("selected");
							try{
								assert !( Get_Selected_Attribute_Value==null ):"FAILED == account <"+Customer_Name+"> selected from nsfo2 screen for customer <"+Customer_No+"> is not coming the selected account into the 'Switch View' Drop Down at nsfo1 screen, Please refer attached screen shot >> Defalut_Account_Display_Fail"+fnsScreenShot_Date_format();
								fnsApps_Report_Logs("PASSED == Successfully verified that the default value coming into the 'Switch View' Drop Down for customer <"+Customer_No+">.");
								Default_Value_Found = true;
								Default_Value_Selection = true;
								break;
							}catch (Throwable t){
								fnsTake_Screen_Shot("Defalut_Account_Display_Fail");
								throw new Exception (Throwables.getStackTraceAsString(t));
							}	
						}
						
						if(Default_Value_Found){
							fnsTake_Screen_Shot("Defalut_Account_Display_Fail");
							throw new Exception ("FAILED == account <"+Customer_Name+"> selected from nsfo2 screen for customer <"+Customer_No+"> is not coming the selected account into the 'Switch View' Drop Down at nsfo1 screen, Please refer attached screen shot >> Defalut_Account_Display_Fail"+fnsScreenShot_Date_format());
						}
					}
				}else{
					TestSuiteBase_MonitorPlan.WithOut_OR_fnDD_SelectValue_By_Contain_Text_and_by_KEYS( OR_NsfOnline.getProperty("NsfOnline_Switch_View_DropDown"), "option", Customer_No);
					fnsGet_Element_Enabled("NsfOnline_Switch_View_GO_Bttn");
					fnsWait_and_Click("NsfOnline_Switch_View_GO_Bttn");
					Thread.sleep(3000);
					TestSuiteBase_New_NSFOnline.fnsLoading_PageLoad(300);
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(1);
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(1);
					
					try{
						if( (driver.findElement(By.xpath(OR_New_NSFOnline.getProperty("NewNsfOnline_Content_Model_Popup"))).isDisplayed()) ){
							TestSuiteBase_New_NSFOnline.fnsGet_Element_Enabled_By_Passing_Wait_and_Screenshot_Condition(60, false, "Acknowledge_Acknowledge_bttn");
							TestSuiteBase_New_NSFOnline.fnsWait_and_Click("Acknowledge_Acknowledge_bttn");
							Acknowledge_popup_Click_done = true;
							Thread.sleep(4000);
						}
					}catch(Throwable t){
						//nothing to do
					}
					
				
					try{
						if(driver.findElement(By.xpath(OR_New_NSFOnline.getProperty("NewNsfOnline_ClassicView_Link"))).isDisplayed()){
							TestSuiteBase_New_NSFOnline.fnsLoading_Progressing_wait_AlertLaoder(2);
							TestSuiteBase_New_NSFOnline.fnsLoading_Progressing_wait(2);
						//	driver.findElement(By.linkText("Classic View")).click();
						TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(OR_New_NSFOnline.getProperty("NewNsfOnline_ClassicView_Link"));
							TestSuiteBase_New_NSFOnline.fnsLoading_Progressing_wait(2);
						}
					}catch(Throwable t){
						//nothing to do
					}	
				}
				
				
				
				
				
				
				
				
				try{
					fnsGet_Element_Enabled("NSFOnline_HomeMenu_Link");
				}catch(Throwable t){
					throw new Exception ("FAILED == 'NSFO-1' landing screen is not getting load after "+( (Long.parseLong(CONFIG.getProperty("ElementWaitTime")))*2)+" seconds wait. Getting Exception >>"+Throwables.getStackTraceAsString(t));
				}
				
			}else {
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				boolean ClassicView_displayed = false;
				for(int i=1; i<=3; i++){
					try{
						if(driver.findElement(By.xpath(OR_New_NSFOnline.getProperty("NewNsfOnline_ClassicView_Link"))).isDisplayed()){
							ClassicView_displayed = true;
							break;
						}else{
							Thread.sleep(1000);
						}
					}catch(Throwable t){
						Thread.sleep(1000);
					}
				}
				
				if(ClassicView_displayed){
					TestSuiteBase_New_NSFOnline.fnsLoading_Progressing_wait_AlertLaoder(2);
					TestSuiteBase_New_NSFOnline.fnsLoading_Progressing_wait(2);
					//driver.findElement(By.linkText("Classic View")).click();
					TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(OR_New_NSFOnline.getProperty("NewNsfOnline_ClassicView_Link"));
					TestSuiteBase_New_NSFOnline.fnsLoading_Progressing_wait(2);
				}
				TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(1);
				try{
					if( (Open_Link_Screen_PageSource.toLowerCase()).contains("please click on the 'open' link to see the account details") || (driver.findElements(By.xpath(OR_NsfOnline.getProperty("NsfOnline_Switch_View_DropDown"))).size()>0) ){
						throw new Exception ("NSFO-1 Open link");
					}else if(driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("SwitchAccount_DD_Label"))).size()>0){
						throw new Exception ("NSFO-2 Switch Account drop down");
					}else if(driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("SwitchAccount_button"))).size()>0){
						throw new Exception ("NSFO-2 Switch Account button");
					}else if(driver.findElements(By.xpath(OR_NsfOnline.getProperty("NsfOnline_Switch_View_DropDown"))).size()>0){
						throw new Exception ("NSFO-1 SwitchAccount drop down");
					}					
				}catch(Throwable t){
					fnsTake_Screen_Shot("User_Not_Have_MultiAccess");
					throw new Exception ("FAILED == 'Switch View Account' DropDown/AccountList is coming BUT as Expected it should not display as the user doesn't have multi access rights, please refer screen shot >> User_Not_Have_MultiAccess"+fnsScreenShot_Date_format()+fnsScreenShot_Date_format()+Throwables.getStackTraceAsString(t));
				}
			}	
		}catch(Throwable t){
			isTestCasePass = false;
			driver.close();
			fnsApps_Report_Logs("PASSED == Successfully Close NSFOnline Window.");
			driver.switchTo().window(MainWindowHandle);
			fnsApps_Report_Logs("PASSED == Successfully Switch to Insight Window.");
			throw new Exception (Throwables.getStackTraceAsString(t)+"......");
		}
		
	}	

	//Check class(Y/N) and Launch browser and Login 
	public void fnsCheckClassLevelTestSkip(String className) throws Exception {
	//	TC_Step=1;
		try {
			classNameText=className;
			currentScriptCode=fnpMappingClassNameWithScenarioCode(classNameText).trim();
			
			if (!TestUtil.isTestCaseRunnable(NSFOnline_suitexls, className)) {
				fnsApps_Report_Logs("Skipping Test Case " + className + " as runmode set to NO");
				throw new SkipException("Skipping Test Case " + className + " as runmode set to NO");
			}
			isTestCasePass = true;
			runmodes = TestUtil.getDataSetRunmodes(NSFOnline_suitexls, className);	
			
		
		}catch(SkipException sk){
			String stackTrace = Throwables.getStackTraceAsString(sk);
			String errorMsg = sk.getMessage();
			Exception c = new Exception(errorMsg);
			ErrorUtil.addVerificationFailure(c);
			throw new SkipException(errorMsg + " \n\nSo,Skipping Test Case" + className);
		}catch (Throwable t) {	
			isTestCasePass = false;
			fnsTake_Screen_Shot(className);				
			String stackTrace = Throwables.getStackTraceAsString(t);
			String errorMsg = t.getMessage();
			errorMsg = errorMsg + "\n\n\n\nSee screenshot '" + className + "'\n\n" + stackTrace;
			Exception c = new Exception(errorMsg);
			ErrorUtil.addVerificationFailure(c);
			throw new Exception(errorMsg );
		}
	}		
	
	
	
	
	

}