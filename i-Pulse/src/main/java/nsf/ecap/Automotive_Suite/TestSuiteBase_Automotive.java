package nsf.ecap.Automotive_Suite;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.imageio.ImageIO;

import net.sf.saxon.functions.Parse;
import nsf.ecap.Alerts_iPulse_Suite.TestSuiteBase_Alerts;
import nsf.ecap.Client_Suite.TestSuiteBase_CLNT;
import nsf.ecap.Grip_Suite.TestSuiteBase_Grip;
import nsf.ecap.IssueMgt_Suite.TestSuiteBase_IM;
import nsf.ecap.MonitorPlan_Suite.TestSuiteBase_MonitorPlan;
import nsf.ecap.NSFOnline_Suite.TestSuiteBase_NSFOnline;
import nsf.ecap.TraQtion_Suite.TestSuiteBase_TraQtion;
import nsf.ecap.base.TestBase;
import nsf.ecap.util.ErrorUtil;
import nsf.ecap.util.TestUtil;
import nsf.ecap.util.Xls_Reader;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;

public class TestSuiteBase_Automotive extends TestBase {

	public TestSuiteBase_MonitorPlan TestSuiteBase_MonitorPlan = new TestSuiteBase_MonitorPlan();
	public TestSuiteBase_NSFOnline TestSuiteBase_NSFOnline = new TestSuiteBase_NSFOnline();
	public TestSuiteBase_Alerts TestSuiteBase_Alerts = new TestSuiteBase_Alerts();
	public TestSuiteBase_Grip TestSuiteBase_Grip = new TestSuiteBase_Grip();
	public TestSuiteBase_TraQtion TestSuiteBase_TraQtion = new TestSuiteBase_TraQtion();
	
	//public boolean isTestPass = true;
	boolean fail = false;
	public String SearchResult_Code_link_Xpath;
	public String BrowserName="IE";
	public boolean BrowserDriver = false;
	public String TextAfterRemvoalSpeChar;
	public String runmodes[] = null;
	public boolean ScreenShotFlagWithOR_Automotive = false;
	public boolean ApplicationVersion_Flag = true;
	
	
	public Integer GetColumnNo = null; ;
	public Integer After_DataAdded = null; ;

	public String Fetched_Text = null;
	public String CurrentMonth = null;
	public String CalendarCompare_MonthYear = null;
	public String CurrentDay = null;
	public String GetCalendar_DateTitle = null;
	
	
	
//######################################################### Common Functions #######################################################################

//Function to Take Screen Shot.
public void fnsTake_Screen_Shot(String message) throws Exception {
	String MessageAfterFormat=fnsRemoveFormatting_for_FileName(message);
		try{
		   FileUtils.forceMkdir(new File((System.getProperty("user.dir") +  screenshots_path+ "//screenshots_Automotive//"+currentScriptCode +"//")));
		   Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		   Rectangle screenRectangle = new Rectangle(screenSize);
		   Robot robot = new Robot();
		   BufferedImage image = robot.createScreenCapture(screenRectangle);
		   ImageIO.write(image, "png", new File((System.getProperty("user.dir") +  screenshots_path+ "//screenshots_Automotive//"+currentScriptCode +"//"+MessageAfterFormat+"_"+fnsScreenShot_Date_format()+".PNG")));
		}catch(java.lang.NullPointerException n){
			fnsApps_Report_Logs("ScreenShotNullPointerException >> "+n.getMessage());
			throw new Exception("NullPointerException Unable To take Screen Shots.");}
		 catch(java.io.IOException e){
			fnsApps_Report_Logs("ScreenShotIOException >> "+e.getMessage());
			throw new Exception("IOException Unable To take Screen Shots.");}
}
	
	
//Worked only for Chrome Browser 	
public void fnWait(long time1) throws Throwable {
	if(BrowserDriver==true){
	Thread.sleep(time1);}
	else{ /*Nothing to Do*/ }
}


//Function used for format Screen shot name
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



//Function for Application Log Date format
public String fnsLOGS_Date_format() {
	DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	Date date = new Date();
	return (dateFormat.format(date));
}



//Function for Screen date format 
public String fnsScreenShot_Date_format() {
	DateFormat dateFormat = new SimpleDateFormat("_dd.MM.yyyy_HH.mm.ss");
	Date date = new Date();
	return (dateFormat.format(date));
}

//Application date format 
public String fnsApplication_Date_format(Integer WhichYear) {
	Calendar cal = Calendar.getInstance();
	DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy");
	cal.add(Calendar.YEAR, WhichYear);
	String DemandDate=dateFormat.format(cal.getTime()).split("-")[0].trim();
	return DemandDate;
}

//Return Year depends on Passing Value
public String fnsReturn_Requried_YearDate(Integer WhichYear_O_forCurrentY) {
	Calendar cal = Calendar.getInstance();
	DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
	cal.add(Calendar.YEAR, WhichYear_O_forCurrentY);
	String DemandDate = dateFormat.format(cal.getTime());
	return DemandDate;
}

//Return Time depends on Passing Value
public String fnsReturn_Requried_Time_HHmm(int WhichTime_forCurrentMinute) {
	Calendar cal = Calendar.getInstance();
		
	DateFormat minuteFormat = new SimpleDateFormat("HH:mm a");
	cal.add(Calendar.MINUTE, WhichTime_forCurrentMinute);
	String minutes = minuteFormat.format(cal.getTime()).trim();
	
	return minutes;
}

//Return Day depends on Passing Value
public String fnsReturn_Requried_Day(int WhichDay_O_forCurrentDay) {
	Calendar cal = Calendar.getInstance();
	DateFormat dayFormat = new SimpleDateFormat("d");
	cal.add(Calendar.DAY_OF_MONTH, WhichDay_O_forCurrentDay);
	String DemandDay = dayFormat.format(cal.getTime());
	return DemandDay;
}

//Return Month depends on Passing Value
public String fnsReturn_Requried_Month(String MonthFormat, Integer WhichMonth_O_forCurrentM) {
	Calendar cal = Calendar.getInstance();
	DateFormat dateFormat = new SimpleDateFormat(MonthFormat);
	cal.add(Calendar.MONTH, WhichMonth_O_forCurrentM);
	String DemandMonth = dateFormat.format(cal.getTime());
	return DemandMonth;
}


//Return Year depends on Passing Value
public String fnsReturn_Requried_Year(Integer WhichYear_O_forCurrentY) {
	Calendar cal = Calendar.getInstance();
	DateFormat dateFormat = new SimpleDateFormat("yyyy");
	cal.add(Calendar.YEAR, WhichYear_O_forCurrentY);
	String DemandYear = dateFormat.format(cal.getTime());
	return DemandYear;
}



//Function For Application Log 
public void fnsApps_Report_Logs(String LogMessage) {
		
	LogMessage = LogMessage.replaceAll("°", "");
	LogMessage = LogMessage.replaceAll("©", "");

	APP_LOGS.debug(LogMessage);
	System.out.println(LogMessage);
	Reporter.log(fnsLOGS_Date_format() + "  " + LogMessage);
}


//Get Excel Cell value by column name
public String fnsReturn_ExcelCellValue_ByMatchingColumnName(String SheetName, String ColumnName) throws Throwable{
	try{
		String CellValue = null;
		for(int i=5; i<50; i++){
			for(int j=1; j<16; j++){
				String ExcelCellValue = Automotive_Suitexls.getCellData(SheetName, j, i);
				if(ExcelCellValue.equalsIgnoreCase(ColumnName)){
					CellValue = Automotive_Suitexls.getCellData(SheetName, j, i+1);
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
		return CellValue.trim();
	}catch(Throwable t){
		isTestCasePass = false;
		fnsApps_Report_Logs(t.getMessage());
		throw new Exception(t.getMessage());	}
}
	

//Function to find and return the object using Xpath
public WebElement fnsGet_OR_Automotive_ObjectX(String XpathKey) throws Exception{
			
	try {
		for (int waits=0; waits<Long.parseLong(CONFIG.getProperty("ElementWaitTime")); waits++) {
			
			if (driver.findElements(By.xpath(OR_Automotive.getProperty(XpathKey))).size() > 0) {break;}
			else{Thread.sleep(500);}
			
		}return driver.findElement(By.xpath(OR_Automotive.getProperty(XpathKey))); 
		
	}catch(StaleElementReferenceException e){
		WebDriverWait stalewaitvar = new WebDriverWait(driver, 10);
		stalewaitvar.until(ExpectedConditions.presenceOfElementLocated(By.xpath(OR_Automotive.getProperty(XpathKey))));
		WebElement webelemnt=driver.findElement(By.xpath(OR_Automotive.getProperty(XpathKey)));
		stalewaitvar.until(ExpectedConditions.stalenessOf(webelemnt));
		return driver.findElement(By.xpath(OR_Automotive.getProperty(XpathKey)));}
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




//Function to click 
public void fnsWait_and_Click(String XpathKey) throws Exception {
	
	try{
		fnsGet_OR_Automotive_ObjectX(XpathKey).click();
		fnsApps_Report_Logs("PASSED == Click done on Element having Xpath >> "+XpathKey);
	}catch(NoSuchWindowException W){
		isTestCasePass = false;
		throw new Exception(W.getMessage());			}
	catch(Throwable e){
		isTestCasePass = false;
		fnsTake_Screen_Shot("UnableToClick_" + XpathKey);
		fnsApps_Report_Logs("FAILED == Unable To Click on Element having Xpath >> "+XpathKey+", plz see screenshot [ UnableToClick_" + XpathKey + fnsScreenShot_Date_format() + " ]"+". Getting Exception  >> "+e.getMessage());
		throw new Exception("Unable To Click on element [ " + XpathKey + " ] , plz see screenshot [ UnableToClick_" + XpathKey + fnsScreenShot_Date_format() + " ]"+". Getting Exception  >> "+e.getMessage());}
}

	


//Function to type
public void fnsWait_and_Type(String XpathKey, String value) throws Exception {
	
	try{
		fnsGet_OR_Automotive_ObjectX(XpathKey).sendKeys("");
		fnsGet_OR_Automotive_ObjectX(XpathKey).sendKeys(value);
		fnsApps_Report_Logs("PASSED == Type Value [ "+value+" ] done on Element having Xpath  >> "+XpathKey);
	}catch(NoSuchWindowException W){
		isTestCasePass = false;
		throw new Exception(W.getMessage());			}
	catch(Throwable e){
		isTestCasePass = false;
		fnsTake_Screen_Shot("UnableToType_" + XpathKey);
		fnsApps_Report_Logs("FAILED == Unable To Type on Element having Xpath  >> "+XpathKey+", plz see screenshot [ UnableToType_" + XpathKey + fnsScreenShot_Date_format() + " ]"+". Getting Exception  >> "+e.getMessage());
		throw new Exception("Unable To Type on element [ " + XpathKey + " ] , plz see screenshot [ UnableToType_" + XpathKey + fnsScreenShot_Date_format() + " ]"+". Getting Exception  >> "+e.getMessage());	}
}




//Function to Clear Field
public void fnsWait_and_Clear(String XpathKey) throws Exception {
	
	try{
		WebElement Elements = fnsGet_OR_Automotive_ObjectX(XpathKey);
		Elements.clear();
		
		//fnsGet_OR_Automotive_ObjectX(XpathKey).clear();
		fnsApps_Report_Logs("PASSED == Clear done Element having Xpath  >> "+XpathKey);
	}catch(NoSuchWindowException W){
		isTestCasePass = false;
		throw new Exception(W.getMessage());			}
	catch(Throwable e){
		isTestCasePass = false;
		fnsTake_Screen_Shot("UnableToClear_" + XpathKey);
		fnsApps_Report_Logs("FAILED == Unable To performe Clear on Element having Xpath  >> "+XpathKey+", plz see screenshot [ UnableToClear_" + XpathKey + fnsScreenShot_Date_format() + " ]"+". Getting Exception  >> "+e.getMessage());
		throw new Exception("Unable To performe Clear on element [ " + XpathKey + " ] , plz see screenshot [ UnableToClear_" + XpathKey + fnsScreenShot_Date_format() + " ]"+". Getting Exception  >> "+e.getMessage());	}
}







//Function to wait for element
public void fnsGet_Element_Enabled(String XpathKey) throws Exception {
	
	try{
		for(int wait=0; wait<3; wait++){
			if(driver.findElements(By.xpath(OR_Automotive.getProperty(XpathKey))).size()>0){
			//	fnsGet_OR_Automotive_ObjectX(XpathKey);
				WebDriverWait elementwaitvar = new WebDriverWait(driver, Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
				elementwaitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_Automotive.getProperty(XpathKey))));
		
				WebDriverWait elementwaitvar1 = new WebDriverWait(driver, Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
				elementwaitvar1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_Automotive.getProperty(XpathKey)))).isEnabled();
				
				break;	} //if loop closed 
			else{
				throw new Exception();
			}
		} 
		fnsApps_Report_Logs("PASSED == Element is Visible having Xpath  >> "+XpathKey);	
	}catch(NoSuchWindowException W){
		isTestCasePass = false;
		throw new Exception(W.getMessage());			}
	catch(Throwable t){
		try{
			Thread.sleep(3000);
			WebDriverWait elementwaitvar3 = new WebDriverWait(driver, Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
			elementwaitvar3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_Automotive.getProperty(XpathKey)))).isEnabled();//}
			fnsApps_Report_Logs("PASSED == Element is Visible having Xpath  >> "+XpathKey);
		}catch(NoSuchWindowException W){
			isTestCasePass = false;
			throw new Exception(W.getMessage());			}
		catch(Throwable e){
			isTestCasePass = false;
			fnsTake_Screen_Shot(XpathKey);
			fnsApps_Report_Logs("FAILED == Element is not Visible having Xpath  >> "+XpathKey+", plz see screenshot [ " + ""+XpathKey + fnsScreenShot_Date_format() + " ]. Getting Exception >> "+e.getMessage());
			throw new Exception("FAILED == Element is not Visible having Xpath  [ " + XpathKey + " ] , plz see screenshot [ " + ""+XpathKey + fnsScreenShot_Date_format() + " ]. Getting Exception >> "+e.getMessage());
		}
	}
}



//Fetch field text
public String fnsGet_Field_TEXT(String XpathKey) throws Exception {
	try{
		
		fnsGet_Element_Enabled(XpathKey);
		String TextFetch=fnsGet_OR_Automotive_ObjectX(XpathKey).getText().trim();
		System.out.println("PASSED == Text["+TextFetch+"] fetch done on Element having xpath [ " +XpathKey+" ].");
		fnsApps_Report_Logs("PASSED == Text fetch done on Element having xpath [ " +XpathKey+" ].");
		return TextFetch;
	}catch(NoSuchWindowException W){
		isTestCasePass = false;
		throw new Exception(W.getMessage());			} 
	catch (Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot("TextFetchFail" + XpathKey);
			fnsApps_Report_Logs("FAILED == Unable to Fetch Text on Element having xpath, Please refer screenshot [" +"TextFetchFail" + XpathKey + fnsScreenShot_Date_format() +"]."+". Getting Exception  >> "+t.getMessage());
			throw new Exception("FAILED == Unable to Fetch Text on Element having xpath, Please refer screenshot [" +"TextFetchFail" + XpathKey + fnsScreenShot_Date_format() +"]."+". Getting Exception  >> "+t.getMessage());}
}





//function to select drop down value
public void fnsDD_value_Select_By_MatchingText_DownKeyPress(String ddClickXpathKey, String ddListXpathKey, String TagName, String Value) throws Exception {
	
	boolean ValueNotMatched=true;
	try{
			fnsWait_and_Click(ddClickXpathKey);
			fnsGet_Element_Enabled(ddListXpathKey);
			List<WebElement> DDobjectlists=fnsGet_OR_Automotive_ObjectX(ddListXpathKey).findElements(By.tagName(TagName));
			Actions action = new Actions(driver);
				for(WebElement dd_value:DDobjectlists){
					action.sendKeys(Keys.ARROW_DOWN).build().perform();		
				//	action.moveToElement(dd_value).build().perform();
					if(dd_value.getText().equals(Value)){
						Thread.sleep(250);
					//	action.moveToElement(dd_value).sendKeys(Keys.ARROW_UP).click(dd_value).build().perform();
						action.moveToElement(dd_value).click(dd_value).build().perform();
						ValueNotMatched=false;
						break;}
					//action.sendKeys(Keys.ARROW_DOWN).build().perform();					
				}
			if(ValueNotMatched==true){
			//	Thread.sleep(2000);
				throw new Exception("Excel value is not matched with DropDown Value.");
				}	
			fnsApps_Report_Logs("PASSED == Select value [ "+Value+" ] from drop down, having xpath >>  " + ddClickXpathKey);
	}catch(NoSuchWindowException W){
		isTestCasePass = false;
		throw new Exception(W.getMessage());			}
	catch(Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot("DdValueSelectFail");
			fnsApps_Report_Logs("FAILED == Unable to select value from drop down [ " + ddClickXpathKey +" ],plz see screenshot [ DdValueSelectFail" +  fnsScreenShot_Date_format() + " ]"+". Getting Exception  >> "+t.getMessage().trim());
			throw new Exception("FAILED == Unable to select value from drop down [ " + ddClickXpathKey +" ],plz see screenshot [ DdValueSelectFail" +  fnsScreenShot_Date_format() + " ]"+". Getting Exception  >> "+t.getMessage().trim());}
}
















//############################################################## Applications Methods ##################################################################

//Clicking on Menu Ajax Link	
	public void fncMenu_Ajax_Link_Click_By_PassingAjaxPath(String AjaxLinkXpath) throws Exception {
		try{
			fnsGet_Element_Enabled("Menu_Ajax_Link");
			WebElement Menu_Element = fnsGet_OR_Automotive_ObjectX("Menu_Ajax_Link");
			
			//New line added to run script in chrome. 26.4.2016
			WebElement VersionLogoImage = fnsGet_OR_Automotive_ObjectX("VersionLogoImage");
			VersionLogoImage.click();
			Thread.sleep(500);
			
			Actions action = new Actions(driver);
			action.moveToElement(Menu_Element).build().perform();
					
			Thread.sleep(500);
			Actions action1 = new Actions(driver);
			fnsGet_Element_Enabled(AjaxLinkXpath);
			WebElement CreateContractorApplicant = fnsGet_OR_Automotive_ObjectX(AjaxLinkXpath);
			action1.moveToElement(CreateContractorApplicant).click().build().perform();
			fnsApps_Report_Logs("PASSED == Successfully Click on <"+(AjaxLinkXpath)+">.");	
			TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(3);
		}catch(NoSuchWindowException W){
			isTestCasePass = false;
			throw new Exception(W.getMessage());			}
		catch(Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot(AjaxLinkXpath+"_Fail");
			fnsApps_Report_Logs("FAILED == Clicking on <"+(AjaxLinkXpath)+"> Failed, plz see screenshot [" +AjaxLinkXpath+"_Fail" + fnsScreenShot_Date_format() + "]"+". Getting Exception  >> "+t.getMessage().trim());
			throw new Exception("FAILED == Clicking on <"+(AjaxLinkXpath)+"> Failed, plz see screenshot [" + AjaxLinkXpath+"_Fail" + fnsScreenShot_Date_format() + "]"+". Getting Exception  >> "+t.getMessage().trim());}
}	
	

	
	
	
	
//Find Column number by Column Name.
	public Integer fncFind_HeaderColumnNo_ByColumnName(String ColumnHeaderTableFirstRowXpath, String ColumnName) throws Throwable{
		try{			
			GetColumnNo = 1;
			fnsGet_Element_Enabled(ColumnHeaderTableFirstRowXpath);
			try{
				WebElement HeaderTableAllObj=fnsGet_OR_Automotive_ObjectX(ColumnHeaderTableFirstRowXpath);
				List<WebElement> NoOfHeadinglist = HeaderTableAllObj.findElements(By.tagName("th"));
				for(WebElement HeaderTableEle:NoOfHeadinglist){
					Fetched_Text = HeaderTableEle.getText().toLowerCase().trim();
					if(Fetched_Text.contains(ColumnName.toLowerCase().trim())){
						break;				}
					GetColumnNo++;
				}
			}catch(Throwable t){
				fnsTake_Screen_Shot("ColumnNoFetchFail");
				fnsApps_Report_Logs("FAILED == Column Name<"+ColumnName+"> does not exists into search ScoreCards result table, Please refer screenshot >> ColumnNoFetchFail"+fnsScreenShot_Date_format()+", Getting Exception >> "+t.getMessage());
				throw new Exception("FAILED == Column Name<"+ColumnName+"> does not exists into search ScoreCards result table, Please refer screenshot >> ColumnNoFetchFail"+fnsScreenShot_Date_format()+", Getting Exception >> "+t.getMessage());	}
			
			return GetColumnNo;
		}catch(NoSuchWindowException W){
			isTestCasePass = false;
			throw new SkipException(W.getMessage());
		}catch(Throwable t){
			isTestCasePass = false;
			fnsApps_Report_Logs(t.getMessage());
			throw new Exception(t.getMessage());	}
	}	



	public void fncAssert_Contains(String TextFetchFieldXpath, String MatchingText, String AssertFailMessageWithOutFailedText) throws Throwable{
		try{
			TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(TextFetchFieldXpath);
			Fetched_Text = TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(TextFetchFieldXpath).toLowerCase().trim();
			TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element_BY_OFFSET(TextFetchFieldXpath, 15, 10);
			
			try{
				assert (Fetched_Text.contains(MatchingText.toLowerCase().trim())):"FAILED == "+AssertFailMessageWithOutFailedText+", Please refer screenshot >>"+MatchingText+"Fail"+fnsScreenShot_Date_format();
				fnsApps_Report_Logs("PASSED == Matching data<"+MatchingText+"> exists into the Fetched data<"+Fetched_Text+">, from Element having xpath >> "+TextFetchFieldXpath);
			}catch(Throwable t){
				TestSuiteBase_MonitorPlan.fnsTake_Screen_Shot_Without_OR(MatchingText+"Fail");
				throw new Exception(t.getMessage());		}
		
		}catch(NoSuchWindowException W){
			isTestCasePass = false;
			throw new SkipException(W.getMessage());
		}catch(Throwable t){
			isTestCasePass = false;
			fnsApps_Report_Logs(t.getMessage());
			throw new Exception(t.getMessage());	}
	}

//Clicking on TAB after every 2Sec till it is opened.
	public void fncClicking_on_TAB(String TabName, String TABXpath, String TabSectionAnyElementXpath) throws Throwable{
		try{
			for(int clicktry=1; clicktry<=(Long.parseLong(CONFIG.getProperty("ElementWaitTime"))/4); clicktry++){
				fnsGet_OR_Automotive_ObjectX(TABXpath).click();
				//Thread.sleep(2000);
				TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
				if(driver.findElements(By.xpath(OR_Automotive.getProperty(TabSectionAnyElementXpath))).size()>0){
					break;		}
				Thread.sleep(2000);
				if(driver.findElements(By.xpath(OR_Automotive.getProperty(TabSectionAnyElementXpath))).size()>0){
					break;		}
				if(clicktry==(Long.parseLong(CONFIG.getProperty("ElementWaitTime"))/4)){
					fnsTake_Screen_Shot("TabOpenFail");
					throw new Exception("FAILED == '"+TabName+"': TAB is not getting opened. TimeOut after <"+(Long.parseLong(CONFIG.getProperty("ElementWaitTime")))+">Seconds wait, Please refer screen shot >> TabOpenFail"+fnsScreenShot_Date_format());		}
			}
		fnsApps_Report_Logs("PASSED == Successfully Click and Opened '"+TabName+"': TAB.");
		}catch(NoSuchWindowException W){
			isTestCasePass = false;
			throw new SkipException(W.getMessage());
		}catch(Throwable t){
			isTestCasePass = false;
			fnsApps_Report_Logs(t.getMessage());
			throw new Exception(t.getMessage());	}
}
	

//Verify data save successfully.
	public void fncVerify_DataUpdated_Successfully(String UpdateMsgXpath, String SectionName) throws Throwable{
		try{
			//TestSuiteBase_Aspirago.fnsLoading_Progressingwait(2);
			//fnsGet_Element_Enabled(UpdateMsgXpath);
			//Thread.sleep(2000);
			Fetched_Text = fnsGet_Field_TEXT(UpdateMsgXpath).toLowerCase().trim();
			TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element_BY_OFFSET(OR_Automotive.getProperty(UpdateMsgXpath), 30, 10);
			try{
				assert (Fetched_Text.contains("success")):"FAILED == "+SectionName+": Data are not updated, Getting Error<"+Fetched_Text+">, Please refer screenshot >> UpdateFail"+fnsScreenShot_Date_format();
				fnsApps_Report_Logs("PASSED == "+SectionName+": Data are updated successfully.");
			}catch(Throwable t){
				fnsTake_Screen_Shot("UpdateFail");
			//	fnsApps_Report_Logs(t.getMessage());
				throw new Exception(t.getMessage());		}
		
		}catch(NoSuchWindowException W){
			isTestCasePass = false;
			throw new SkipException(W.getMessage());
		}catch(Throwable t){
			isTestCasePass = false;
			fnsApps_Report_Logs(t.getMessage());
			throw new Exception(t.getMessage());	}
}	
//Delete message Verify
	public void fncVerify_DataDeleted_Successfully(String UpdateMsgXpath, String SectionName) throws Throwable{
		try{
			fnsGet_Element_Enabled(UpdateMsgXpath);
			Fetched_Text = fnsGet_Field_TEXT(UpdateMsgXpath).toLowerCase().trim();
		//	TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element(OR_Automotive.getProperty(UpdateMsgXpath));
			TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element_BY_OFFSET(OR_Automotive.getProperty(UpdateMsgXpath), 30, 10);
			try{
				assert (Fetched_Text.contains("delete")):"FAILED == "+SectionName+": Data are not Deleted, Getting Error<"+Fetched_Text+">, Please refer screenshot >> DeleteFail"+fnsScreenShot_Date_format();
				fnsApps_Report_Logs("PASSED == "+SectionName+": Data are successfully Deleted.");
			}catch(Throwable t){
				fnsTake_Screen_Shot("DeleteFail");
				fnsApps_Report_Logs(t.getMessage());
				throw new Exception(t.getMessage());		}
		
		}catch(NoSuchWindowException W){
			isTestCasePass = false;
			throw new SkipException(W.getMessage());
		}catch(Throwable t){
			isTestCasePass = false;
			fnsApps_Report_Logs(t.getMessage());
			throw new Exception(t.getMessage());	}
}	
	//Verify New Row Added into the Table.
		public void fncVerify_NewRow_Added_into_Table(String TableName, String TableHeaderXpath, Integer RowCountBeforeNewDataAdded) throws Throwable{
			try{
				for(int wait=0; wait<=(Long.parseLong(CONFIG.getProperty("ElementWaitTime"))/2); wait++){
					After_DataAdded = TestSuiteBase_MonitorPlan.WithOut_OR_Retrun_TotalRowCount_OfTable(TableHeaderXpath);
					if(After_DataAdded>RowCountBeforeNewDataAdded){
						fnsApps_Report_Logs("PASSED == New Data are successfully added into the table<"+TableName+">. RowCount Before/After<"+RowCountBeforeNewDataAdded+"/"+After_DataAdded+">");
						break;
					}else{
						Thread.sleep(1000);
					}
					if(wait==(Long.parseLong(CONFIG.getProperty("ElementWaitTime"))/2)){
						fnsTake_Screen_Shot("NewDataAddedFail");
						throw new Exception("FAILED == New Data are not added into the table<"+TableName+">, TimeOut after <"+(Long.parseLong(CONFIG.getProperty("ElementWaitTime"))/2)+">Seconds, Please refer screenshot >> NewDataAddedFail"+fnsScreenShot_Date_format());
					}
				}
			
			}catch(NoSuchWindowException W){
				isTestCasePass = false;
				throw new SkipException(W.getMessage());
			}catch(Throwable t){
				isTestCasePass = false;
				fnsApps_Report_Logs(t.getMessage());
				throw new Exception(t.getMessage());	}
	}		
	

		public void fncUploadFile(String BrowseBttnXpath,String BrowsePopupName) throws Throwable{
			try{
				String FileUploadPath = System.getProperty("user.dir") + "\\docs\\This is test pdf document.pdf";
				WebElement Browser = fnsGet_OR_Automotive_ObjectX(BrowseBttnXpath);
				Browser.sendKeys(FileUploadPath);
				Thread.sleep(2000);
				for(int wait=0; wait<=(Long.parseLong(CONFIG.getProperty("ElementWaitTime"))/10); wait++){
					if(driver.findElements(By.xpath(OR_Automotive.getProperty("BrowseFile_Progress"))).size()>0){
						Thread.sleep(1000);
					}else{
						Thread.sleep(1000);
						fnsApps_Report_Logs("PASSED == "+BrowsePopupName+": File is Successfully Upload.");
						break;
					}
					if(wait==(Long.parseLong(CONFIG.getProperty("ElementWaitTime"))/2)){
						fnsTake_Screen_Shot("FileUploadFail");
						throw new Exception("FAILED == "+BrowsePopupName+": File is not getting Upload, TimeOut after <"+(Long.parseLong(CONFIG.getProperty("ElementWaitTime"))/2)+">Seconds, Please refer screenshot >> FileUploadFail"+fnsScreenShot_Date_format());
					}
					if(driver.findElements(By.xpath(OR_Automotive.getProperty("BrowseFile_ErrorMsg"))).size()>0){
						fnsTake_Screen_Shot("FileUploadFail");
						throw new Exception("FAILED == "+BrowsePopupName+": File is not getting Upload, Getting Error<"+(fnsGet_Field_TEXT("BrowseFile_ErrorMsg").trim())+">, Please refer screenshot >> FileUploadFail"+fnsScreenShot_Date_format());
					}
				}
			
			}catch(NoSuchWindowException W){
				isTestCasePass = false;
				throw new SkipException(W.getMessage());
			}catch(Throwable t){
				isTestCasePass = false;
				fnsApps_Report_Logs(t.getMessage());
				throw new Exception(t.getMessage());	}
	}	
	
	
	
		//Picking date current day and Current Month for String Passing year.
		public void fncCalendarDatePicker_NullMeansCurrent(String CalenderButtonXpath, String DatePickForWhichDay_FormatD, String DatePickForWhichMonth_FormatMMMMMM, String DatePickForWhichYear_FormatYYYY) throws Throwable{
			try{
				String CurrentYear = null;
								
				if(DatePickForWhichDay_FormatD==null){
					CurrentDay = fnsReturn_Requried_Day(0);
				}else{
					CurrentDay = DatePickForWhichDay_FormatD;
				}
				
				if(DatePickForWhichMonth_FormatMMMMMM==null){
					CurrentMonth = fnsReturn_Requried_Month("MMMM", 0);
				}else{
					CurrentMonth = DatePickForWhichMonth_FormatMMMMMM;
				}
			
				if(DatePickForWhichYear_FormatYYYY==null){
					CurrentYear = fnsReturn_Requried_Year(0);
				}else{
					CurrentYear = DatePickForWhichYear_FormatYYYY;
				}
			
				
				CalendarCompare_MonthYear = CurrentMonth+" "+CurrentYear;
				TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(CalenderButtonXpath);
				TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(CalenderButtonXpath);
								
				String CurrentDayXpath = "//a[text()='"+CurrentDay+"']";
				
				for(int start=0; start<14; start++){
					TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(OR_Automotive.getProperty("Calendar_MonthYear_Title"));
					GetCalendar_DateTitle = TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(OR_Automotive.getProperty("Calendar_MonthYear_Title")).trim();
				
					if(GetCalendar_DateTitle.equalsIgnoreCase(CalendarCompare_MonthYear)){
						TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(CurrentDayXpath);
						Thread.sleep(500);
						break;
					}else{
						TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(OR_Automotive.getProperty("Calendar_Next_Bttn"));
						Thread.sleep(500);
					}
					
					if(start==13){
						TestSuiteBase_MonitorPlan.fnsTake_Screen_Shot_Without_OR("DatePickFail");
						throw new Exception("FAILED == Calendar Date Pick is getting fail, Please refer screen shot >> DatePickFail"+fnsScreenShot_Date_format());
					}
				}
			}catch(NoSuchWindowException W){
				isTestCasePass = false;
				throw new SkipException(W.getMessage());
			}catch(Throwable t){
				isTestCasePass = false;
				fnsApps_Report_Logs(t.getMessage());
				throw new Exception(t.getMessage());	}
	}		
	
		//Picking date current day and Current Month for String Passing year By Selecting values from months and years DropDpwn.
		public void fncCalendarDatePicker_BySelectValues_From_DropDown(String CalenderButtonXpath, String DatePickForWhichMonth_Format_Mmm, String DatePickForWhichYear_Format_YYYY) throws Throwable{
			try{
				Calendar cal = Calendar.getInstance();
				DateFormat dayFormat = new SimpleDateFormat("d");
				CurrentDay = dayFormat.format(cal.getTime());
				
				TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(CalenderButtonXpath);
				TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(CalenderButtonXpath);
				TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
			
												
				String CurrentDayXpath = "//a[text()='"+CurrentDay+"']";
				
				if(DatePickForWhichMonth_Format_Mmm!=null){
					TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(OR_Automotive.getProperty("Calendar_Month_DD"));
					TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(OR_Automotive.getProperty("Calendar_Month_DD"));
					WebElement MonthElement = fnsGet_OR_Automotive_ObjectX("Calendar_Month_DD");
					List<WebElement> MonthElement_AllObj = MonthElement.findElements(By.tagName("option"));
					for(WebElement MonthElement_Obj:MonthElement_AllObj){
						String MonthElement_Text = MonthElement_Obj.getText().toLowerCase().trim();
						System.out.println("MonthElement_Text = "+MonthElement_Text);
						if(MonthElement_Text.equalsIgnoreCase(DatePickForWhichMonth_Format_Mmm.toLowerCase().trim())){
							Thread.sleep(250);
							MonthElement_Obj.click();
							System.out.println("Month success select");
							Thread.sleep(1000);
							break;
						}
					}
				}
				
				if(DatePickForWhichYear_Format_YYYY!=null){
					TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(OR_Automotive.getProperty("Calendar_Year_DD"));
					TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(OR_Automotive.getProperty("Calendar_Year_DD"));
					WebElement YearElement = fnsGet_OR_Automotive_ObjectX("Calendar_Year_DD");
					List<WebElement> YearElement_AllObj = YearElement.findElements(By.tagName("option"));
					for(WebElement YearElement_Obj:YearElement_AllObj){
						String YearElement_Text = YearElement_Obj.getText().toLowerCase().trim();
						System.out.println("YearElement_Text = "+YearElement_Text);
						if(YearElement_Text.equalsIgnoreCase(DatePickForWhichYear_Format_YYYY.toLowerCase().trim())){
							Thread.sleep(250);
							YearElement_Obj.click();
							System.out.println("Year success select");
							Thread.sleep(1000);
							break;
						}
					}
				}
				Thread.sleep(2000);
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
				fnsApps_Report_Logs("FAILED == Calendar Date Pick is getting fail, Please refer screen shot >> DatePickFail"+fnsScreenShot_Date_format()+"   Getting Exception >>"+t.getMessage());
				throw new Exception("FAILED == Calendar Date Pick is getting fail, Please refer screen shot >> DatePickFail"+fnsScreenShot_Date_format()+"   Getting Exception >>"+t.getMessage());	}
	}
	
	
		
	
		//Clicking on Save/Edit Button by moving on element with XY coordinate through action class.	
		public void fncEdit_and_Save_ButtonClick_WithOut_OR(String TableHeader_DataXpath, Integer RowNumber, String Bttn_Name_LikeSaveEdit) throws Throwable{
			try{
				if(Bttn_Name_LikeSaveEdit.toLowerCase().trim().equalsIgnoreCase("save")){	
					String SaveBttn_Xpath = OR_Automotive.getProperty(TableHeader_DataXpath)+"/tr["+RowNumber+"]/td[1]/div[1]/span[2]";
					TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(SaveBttn_Xpath);
					TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(SaveBttn_Xpath);
				}
				if(Bttn_Name_LikeSaveEdit.toLowerCase().trim().equalsIgnoreCase("edit")){
					String EditBttn_Xpath = OR_Automotive.getProperty(TableHeader_DataXpath)+"/tr["+RowNumber+"]/td[1]/div[1]/span[1]";
					TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(EditBttn_Xpath);
					TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(EditBttn_Xpath);
				}
				
				fnsApps_Report_Logs("PASSED == Successfully click on '"+Bttn_Name_LikeSaveEdit.toUpperCase()+"' Button in Row<"+RowNumber+"> of table having Xpath <"+TableHeader_DataXpath+">");
		}catch(NoSuchWindowException W){
			isTestCasePass = false;
			throw new SkipException(W.getMessage());
		}catch(Throwable t){
			fnsTake_Screen_Shot(Bttn_Name_LikeSaveEdit+"ClickFail");
			isTestCasePass = false;
			fnsApps_Report_Logs("FAILED == clicking on '"+Bttn_Name_LikeSaveEdit.toUpperCase()+"' Button is getting fail in Row<"+RowNumber+"> of table having Xpath <"+TableHeader_DataXpath+">, Please refer screen shot >> "+Bttn_Name_LikeSaveEdit+"ClickFail"+fnsScreenShot_Date_format()+"   Getting Exception >>"+t.getMessage());
			throw new Exception("FAILED == clicking on '"+Bttn_Name_LikeSaveEdit.toUpperCase()+"' Button is getting fail in Row<"+RowNumber+"> of table having Xpath <"+TableHeader_DataXpath+">, Please refer screen shot >> "+Bttn_Name_LikeSaveEdit+"ClickFail"+fnsScreenShot_Date_format()+"   Getting Exception >>"+t.getMessage());	}
	}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


//################################################################### Configuration Function ############################################################################	
	// Function to Launch browser and login
	public boolean fnsLaunchBrowserAndLogin() throws Throwable {
		boolean present;
		startExecutionTime = fnpTimestamp();
		ScreenShotFlagWithOR_Automotive = true;
		
		try {
		
			/*if (BrowserName.equalsIgnoreCase("IE")) {
				System.setProperty("webdriver.ie.driver", CONFIG.getProperty("IEDriverPath"));

				DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
				caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				caps.setCapability("IGNORE_ZOOM_SETTING", true);
			    caps.setCapability("IE_ENSURE_CLEAN_SESSION", true);
			   caps.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
			    			
			    driver=new InternetExplorerDriver(caps);
  			fnsApps_Report_Logs("Browser type is IE");}
			
			if (BrowserName.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", CONFIG.getProperty("ChromeDriverPath"));
				DesiredCapabilities caps = DesiredCapabilities.chrome();
				
				ChromeOptions options = new ChromeOptions();
				options.addArguments("test-type");
				caps.setCapability(ChromeOptions.CAPABILITY, options);																																												
																																																				
				driver=new ChromeDriver(caps);				
				BrowserDriver=true;
				fnsApps_Report_Logs("Browser type is CHROME");
			}
			

			if (BrowserName.equalsIgnoreCase("firefox")) {
				driver = new FirefoxDriver();
				fnsApps_Report_Logs("Browser type is Firefox");}*/

			TestSuiteBase_MonitorPlan.fns_Launch_Browser_Only();
			
			//TestSuiteBase_MonitorPlan.fnsIpulse_Login("TrunktestSiteName");
			TestSuiteBase_MonitorPlan.fnsIpulse_Login_Split_Excel_Urls("ipulse", null, CONFIG.getProperty("adminUsername"), CONFIG.getProperty("adminPassword"));
			
			present = true;
					
			if (!veryFirstTimeAfterLogin) {
			//	TestSuiteBase_NSFOnline.fnsIPulse_Application_Version("Automotive");
				veryFirstTimeAfterLogin=true;
			}
			
			if(ApplicationVersion_Flag){
		//		TestSuiteBase_NSFOnline.fnsIPulse_Application_Version("Automotive");
				ApplicationVersion_Flag = false;
			}
		
		} catch(NoSuchWindowException W){
			isTestCasePass = false;
			throw new Exception(W.getMessage());			}
		catch (Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot("LaunchBrowserAndLoginFail");
			present = false;
		//	ErrorUtil.addVerificationFailure(t);
		//	driver.quit();
			fnsApps_Report_Logs("");
			fnsApps_Report_Logs("'Launch Browser And Login' is getting fail, Please refer screen shot [LaunchBrowserAndLoginFail"+fnsScreenShot_Date_format()+"]."+" Getting Exception >> "+t.getMessage());
			throw new Exception("'Launch Browser And Login' is getting fail, Please refer screen shot [LaunchBrowserAndLoginFail"+fnsScreenShot_Date_format()+"]."+" Getting Exception >> "+t.getMessage());		}
	
		return present;
}


	
	
	// Function to Launch browser and login
	public boolean fnsLaunchBrowser_And_SecureLogin() throws Throwable {
		boolean present;
		startExecutionTime = fnpTimestamp();
		ScreenShotFlagWithOR_Automotive = true;
		
		try {
		
			/*if (BrowserName.equalsIgnoreCase("IE")) {
				System.setProperty("webdriver.ie.driver", CONFIG.getProperty("IEDriverPath"));

				DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
				caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				caps.setCapability("IGNORE_ZOOM_SETTING", true);
			    caps.setCapability("IE_ENSURE_CLEAN_SESSION", true);
			   caps.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
			    			
			    driver=new InternetExplorerDriver(caps);
  			fnsApps_Report_Logs("Browser type is IE");}
			
			if (BrowserName.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", CONFIG.getProperty("ChromeDriverPath"));
				DesiredCapabilities caps = DesiredCapabilities.chrome();
				
				ChromeOptions options = new ChromeOptions();
				options.addArguments("test-type");
				caps.setCapability(ChromeOptions.CAPABILITY, options);																																												
																																																				
				driver=new ChromeDriver(caps);				
				BrowserDriver=true;
				fnsApps_Report_Logs("Browser type is CHROME");
			}
			

			if (BrowserName.equalsIgnoreCase("firefox")) {
				driver = new FirefoxDriver();
				fnsApps_Report_Logs("Browser type is Firefox");}*/

			TestSuiteBase_MonitorPlan.fns_Launch_Browser_Only();
			TestSuiteBase_MonitorPlan.fnsIpulse_Login_Split_Excel_Urls("secureipulse", CONFIG.getProperty("LoginAs_JHUGHES"), CONFIG.getProperty("adminUsername"), CONFIG.getProperty("adminPassword"));
			
			present = true;
					
			if (!veryFirstTimeAfterLogin) {
			//	TestSuiteBase_NSFOnline.fnsIPulse_Application_Version("Automotive");
				veryFirstTimeAfterLogin=true;
			}
			
			if(ApplicationVersion_Flag){
			//	TestSuiteBase_NSFOnline.fnsIPulse_Application_Version("Automotive");
				ApplicationVersion_Flag = false;
			}
		
		} catch(NoSuchWindowException W){
			isTestCasePass = false;
			throw new Exception(W.getMessage());			}
		catch (Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot("LaunchBrowserAndLoginFail");
			present = false;
			fnsApps_Report_Logs("");
			fnsApps_Report_Logs("'Launch Browser And Secure Login' is getting fail, Please refer screen shot [LaunchBrowserAndLoginFail"+fnsScreenShot_Date_format()+"]."+" Getting Exception >> "+t.getMessage());
			throw new Exception("'Launch Browser And Secure Login' is getting fail, Please refer screen shot [LaunchBrowserAndLoginFail"+fnsScreenShot_Date_format()+"]."+" Getting Exception >> "+t.getMessage());		}
	
		return present;
}
	
	
	
	
	
	

//Check for Browser Type defined in Suits Excel 
public void BrowserCheck(){
		for(int i=2; i <= suiteXls.getRowCount("Test Suite") ;i++ ){
			
			if(suiteXls.getCellData("Test Suite", "TSID", i).equalsIgnoreCase("Automotive_Suite")){
				BrowserName=suiteXls.getCellData("Test Suite", "Browser", i);
				
				if(BrowserName==""){
					BrowserName="IE";
				}
				
				break;
			}
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
				hashXlData.put(hashkey, hashValue);	}
		}

		return hashXlData;
}
	

	
	
	//Check class(Y/N) and Launch browser and Login 
	public void fnsCheckClassLevelTestSkip(String className) throws Exception {
		
		isTestCasePass = true;
		
		try {
			classNameText=className;
			currentScriptCode=fnpMappingClassNameWithScenarioCode(classNameText).trim();
			
			if (!TestUtil.isTestCaseRunnable(Automotive_Suitexls, className)) {
				fnsApps_Report_Logs("Skipping Test Case " + className + " as runmode set to NO");
				throw new SkipException("Skipping Test Case " + className + " as runmode set to NO");}

			/*/if (!IsBrowserPresentAlready) {
				IsBrowserPresentAlready = fnsLaunchBrowserAndLogin();
				fnsApps_Report_Logs("Browser is launched and Successfully login into Application");	}
		
			fnsApps_Report_Logs("=========================================================================================================================================");*/
		
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
	
	
	

//check if the suite has to be skip
	@BeforeSuite
	public void checkSuiteSkip() throws Throwable{
		/*currentSuiteName = "Automotive_Suite";
		fnInitialize();
		BrowserCheck();
		if(TestUtil.isSuiteRunnable(suiteXls, "Automotive_Suite")){
			FileUtils.deleteDirectory(new File((System.getProperty("user.dir") + CONFIG.getProperty("screenshots_path")+"//Automotive//")));
			browserName=TestUtil.getBrowserName(suiteXls, "Automotive_Suite");
			excelSiteURL= TestUtil.getExcelSiteURL(suiteXls, currentSuiteName);
			int rowNum = suiteXls.getCellRowNum("Test Suite", "TSID", currentSuiteName);
			suiteXls.setCellData("Test Suite", "Last_Execution", rowNum, fnReturnDateWithTimeForExcel());
			
			
		}
		if(!TestUtil.isSuiteRunnable(suiteXls, "Automotive_Suite")){
			fnsApps_Report_Logs("Skipped Automotive_Suite as the runmode was set to NO");
			throw new SkipException("Runmode of Automotive_Suite set to no. So Skipping all tests in Automotive_Suite");}	*/	
		fns_CheckSiteSkip("Automotive_Suite");	
}
	
	

	
	
	
	
//Always run after suite
	@AfterSuite(alwaysRun=true)
	public void Finishing_Automotive_Suite_Script() throws Throwable {
		ScreenShotFlagWithOR_Automotive = false;
		fnsApps_Report_Logs("");
		fnsApps_Report_Logs("######################################################## Automotive Suite END ######################################################## ");
		fnsApps_Report_Logs("=========================================================================================================================================");
		fnsApps_Report_Logs("");
}	
	

}
