package nsf.ecap.Alerts_iPulse_Suite;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import nsf.ecap.MonitorPlan_Suite.TestSuiteBase_MonitorPlan;
import nsf.ecap.base.TestBase;
import nsf.ecap.util.ErrorUtil;
import nsf.ecap.util.TestUtil;
import nsf.ecap.util.Xls_Reader;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import com.google.common.base.Throwables;

public class TestSuiteBase_Alerts extends TestBase  {

	public String classNameText = null;
	public ArrayList<String> Loading_Xpath_Array = new ArrayList<String>() ;
	public TestSuiteBase_MonitorPlan TestSuiteBase_MonitorPlan = new TestSuiteBase_MonitorPlan();
	public boolean LoadingImageFlag = true;
	public boolean fail = false;
	public boolean skip = true;
	public boolean BrowserOpen = false;
	public  static boolean ScreenShotFlagWithOR_Alerts_iPulse = false;
	public Integer Actual_Loading_Time;
	public Integer Total_Alerts_Run_Count = 1;
	public Integer TimeOut;	
	public int count = -1;
	public long TC_Step = 0;
	public long Log_Step = 0;	
	public String Login_As_UserName = "";
	public String Fetched_Text = null;
	public String SuccessMsgText = "";
	public  String RunningClassName = null;
	public  String Running_Class_BS_Description = null;
	public String AlertSourceText = null;	
	public String ErrorMsgText = "";
	public String runmodes[] = null;	
	public String Loading_Image_Xpath = null;	
	public static DesiredCapabilities caps;
	public static ChromeOptions options;
		
//######################################################### Common Functions #######################################################################

//Function to Take Screen Shot.
public void fnsTake_Screen_Shot(String message) throws Exception {
	String MessageAfterFormat=fnsRemoveFormatting_for_FileName(message);
		try{
			String Suite_Foler_Name = "screenshots_Alerts_iPulse";
			String File_Name = MessageAfterFormat+"_"+fnsScreenShot_Date_format()+".PNG";
			FileUtils.forceMkdir(new File((System.getProperty("user.dir") +  screenshots_path+ "//"+Suite_Foler_Name+"//"+currentScriptCode +"//")));
			File image = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);			
			FileUtils.copyFile(image, new File((System.getProperty("user.dir") +  screenshots_path+ "//"+Suite_Foler_Name+"//"+currentScriptCode +"//"+File_Name)));
			/*
		   FileUtils.forceMkdir(new File((System.getProperty("user.dir") +  screenshots_path+ "//screenshots_Alerts_iPulse//"+currentScriptCode +"//")));
		   Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		   Rectangle screenRectangle = new Rectangle(screenSize);
		   Robot robot = new Robot();
		   BufferedImage image = robot.createScreenCapture(screenRectangle);
		   ImageIO.write(image, "png", new File((System.getProperty("user.dir") +  screenshots_path+ "//screenshots_Alerts_iPulse//"+currentScriptCode +"//"+MessageAfterFormat+"_"+fnsScreenShot_Date_format()+".PNG")));*/
		}catch(java.lang.NullPointerException n){
			fnsApps_Report_Logs("ScreenShotNullPointerException >> "+n.getMessage());
			throw new Exception("NullPointerException Unable To take Screen Shots.");}
		 catch(java.io.IOException e){
			fnsApps_Report_Logs("ScreenShotIOException >> "+e.getMessage());
			throw new Exception("IOException Unable To take Screen Shots.");}
}
	
	

//Function used for format Screen shot name
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


//Function for Application Log Date format
public static String fnsLOGS_Date_format() {
	DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	Date date = new Date();
	return (dateFormat.format(date));
}



//Function for Screen date format 
public static String fnsScreenShot_Date_format() {
	DateFormat dateFormat = new SimpleDateFormat("_dd.MM.yyyy_HH.mm.ss");
	Date date = new Date();
	return (dateFormat.format(date));
}

//Function For Application Log 
public void fnsApps_Report_Logs(String LogMessage) {
		
	LogMessage = LogMessage.replaceAll("°", "");
	LogMessage = LogMessage.replaceAll("©", "");

	if( (LogMessage.toLowerCase().contains("pause/wait")) ){
		//noting to do
	}else if( ( (LogMessage.toLowerCase().contains("failed")) || (LogMessage.toLowerCase().contains("java")) || (LogMessage.toLowerCase().contains("webdriver")) || (LogMessage.toLowerCase().contains("assert")) ) && !(LogMessage.toLowerCase().contains("with failed")) ){ 
		Reporter.log(" |");
		Reporter.log(" |");
		Reporter.log(" | Script has been failed, after performing the Step "+(TC_Step-1)+" , Please refer the below error....");
		Reporter.log(" |");
		Reporter.log(fnsLOGS_Date_format() + " | " + LogMessage);
	}else if( ( (LogMessage.toLowerCase().contains("success")) || (LogMessage.toLowerCase().contains("load"))  || (LogMessage.toLowerCase().contains("click")) || (LogMessage.toLowerCase().contains("type")) || (LogMessage.toLowerCase().contains("select")) || (LogMessage.toLowerCase().contains("button")) || (LogMessage.toLowerCase().contains("query"))) && !(LogMessage.toLowerCase().contains("move")) || (LogMessage.toLowerCase().contains("url")) || (LogMessage.toLowerCase().contains("automation")) ){ //Steps Log
		if( !((LogMessage.toLowerCase().contains("success")) || (LogMessage.toLowerCase().contains("loading")) || (LogMessage.toLowerCase().contains("test case")) ) || (LogMessage.toLowerCase().contains("select")) || (LogMessage.toLowerCase().contains("menu"))  || (LogMessage.toLowerCase().contains("url")) || (LogMessage.toLowerCase().contains("button"))){
			LogMessage = "STEP "+TC_Step+" *** "+LogMessage;
			Reporter.log(fnsLOGS_Date_format() + " | ");
			Reporter.log(fnsLOGS_Date_format() + " | " + LogMessage);
			TC_Step++;
		}else{
			Reporter.log(fnsLOGS_Date_format() + " | ********** " + LogMessage);
		}
		
	}else if( (LogMessage.toLowerCase().contains("[bs-")) || (LogMessage.toLowerCase().contains("execution")) || (LogMessage.toLowerCase().contains("runmode")) ){
		Reporter.log("========================================================================================================================================");
		Reporter.log(LogMessage);
		Reporter.log(" ");
	}
	APP_LOGS.debug(LogMessage);
	System.out.println(LogMessage);
}


//Get Excel Cell value by column name
public String fnsReturn_ExcelCellValue_ByMatchingColumnName(String SheetName, String ColumnName) throws Throwable{
	try{
		String CellValue = null;
		for(int i=5; i<50; i++){
			for(int j=1; j<16; j++){
				String ExcelCellValue = Alerts_iPulse_suitexls.getCellData(SheetName, j, i);
				if(ExcelCellValue.equalsIgnoreCase(ColumnName)){
					CellValue = Alerts_iPulse_suitexls.getCellData(SheetName, j, i+1).trim();
					break;
				}
			}
			if(CellValue!=null){
				break;
			}
		}
		
		if(CellValue == null){
			throw new Exception("FAILED == Column < "+ColumnName+" > entry is Missing/Incorrect into the sheet < "+SheetName+" > of the suite Excel 'Alerts_iPulse'.")	;
		}else if(CellValue.equals("")){
			fnsApps_Report_Logs("@@@@@@@@@@@  Value of Column < "+ColumnName+" > is   'BLANK'   into the sheet < "+SheetName+" >.");
		}
		
		System.out.println(ColumnName+"   =============================    >>  "+CellValue);
		return CellValue;
	}catch(Throwable t){
	//	fnsApps_Report_Logs(t.getMessage());
		throw new Exception(t.getMessage());	}
}




//Function of loading image appear on the screen (Block UI). 
public Integer fnsLoading_Progressingwait_Alert_iPulse(int waitcount, int Max_wait_Time) throws Throwable{
	try{
		long Loading_Wait_time = Max_wait_Time;
		LoadingImageFlag = false;
		Actual_Loading_Time = 1;
		Loading_Image_Xpath = null;
		AlertSourceText = null;
		String Loading_Classes_From_OR = OR_MtrPlan.getProperty("Loading_Progressing").trim();
		
		for(int wait=2; wait<=((Loading_Wait_time)); wait++){
			if(!LoadingImageFlag){
				Thread.sleep(500);
				for(int i=0; i<Loading_Classes_From_OR.split("&&").length; i++){
					Loading_Image_Xpath = Loading_Classes_From_OR.split("&&")[i].trim();
					try{
						if(driver.findElements(By.xpath(Loading_Image_Xpath)).size()>1){
							for(int display=1; display<=driver.findElements(By.xpath(Loading_Image_Xpath)).size(); display++){
								String Loading_Image_Xpath_Display = "("+Loading_Image_Xpath+")["+display+"]";
								if(driver.findElement(By.xpath(Loading_Image_Xpath_Display)).isDisplayed()){
									Loading_Image_Xpath = Loading_Image_Xpath_Display;
									break;
								}
							}
						}else if(driver.findElements(By.xpath(Loading_Image_Xpath)).size()>0){
							if(driver.findElement(By.xpath(Loading_Image_Xpath)).isDisplayed()){
								break;
							}
						}
					}catch(Throwable t){ /*/nothing to do*/	}	
				}
			}
			
			
			if(Loading_Image_Xpath.contains("ui-messages-error-summary")){
				AlertSourceText = driver.getPageSource().toLowerCase();
				if(AlertSourceText.contains("ui-messages-error-summary")){		
					if (driver.findElement(By.xpath(Loading_Image_Xpath)).isDisplayed()) {
						WebElement Element = driver.findElement(By.xpath(Loading_Image_Xpath));
						Actions act = new Actions(driver);
						act.moveToElement(Element).build().perform();
						ErrorMsgText = driver.findElement(By.xpath(Loading_Image_Xpath)).getText().trim();
						throw new IllegalArgumentException();
					}else { // added on 4.4.2018
						fnsApps_Report_Logs("*********  Pause/Wait is for < "+Actual_Loading_Time+" > seconds.  *****  "); //To increase performance, Pause/Wait time can set to 1 sec
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
					//System.out.println("*********  Progressing Loading image is displayed for < "+(Actual_Loading_Time)+" > seconds.");
					break;	
				}else if(  !(driver.findElement(By.xpath(Loading_Image_Xpath)).isDisplayed()) && wait > waitcount &&  LoadingImageFlag == false ){
					System.out.println("*********  Pause/Wait is for < "+Actual_Loading_Time+" > seconds.  *****  "); //To increase performance, Pause/Wait time can set to 1 sec
					break;
				}else if(  wait < waitcount &&  LoadingImageFlag == false ){
					Thread.sleep(250); //differ from new nsfonline
				}
			}catch(Throwable n){
				if(  wait > waitcount ){
					if( LoadingImageFlag == true ){
						Loading_Xpath_Array.add(Loading_Image_Xpath+"&&");
					//	fnsApps_Report_Logs("*********  Progressing Loading image is displayed for < "+(Actual_Loading_Time)+" > seconds.");
						break;	
					}else if( LoadingImageFlag == false ){
						System.out.println("*********  Pause/Wait is for < "+Actual_Loading_Time+" > seconds.  *****  "); //To increase performance, Pause/Wait time can set to 1 sec
						break;
					}
				}else { //differ from new nsfonline
					if( LoadingImageFlag == false ){
						Thread.sleep(250); //differ from new nsfonline
					}else{
						Loading_Xpath_Array.add(Loading_Image_Xpath+"&&");
					//	System.out.println("*********  Progressing Loading image is displayed for < "+(Actual_Loading_Time)+" > seconds.");
						break;	
					}
				}
			}
						
			if(wait==(Loading_Wait_time)){
				throw new InterruptedException("Loading Issue : After < "+(Loading_Wait_time)+" > Seconds wait Alert is not getting Load, please refer screenshot >> AlertLoadFail" + fnsScreenShot_Date_format() +"*****  < "+Loading_Image_Xpath+" >");
			}
			Actual_Loading_Time++;
		}
				
		
		AlertSourceText = driver.getPageSource().toLowerCase();
		if(AlertSourceText.contains("we are sorry")){		
			ErrorMsgText = "We are sorry...an unexpected error has occurred. Details have been forwarded to the Application Support Team for investigation. If the problem persists, please submit an issue on the Apps Portal.";
			throw new IllegalArgumentException();
		}
	
			
	}catch(IllegalArgumentException i){
		fnsTake_Screen_Shot("UnExpectedError");
		fnsApps_Report_Logs("Getting Un-Expected Application Error <"+ErrorMsgText+">, please refer screenshot >>UnExpectedError" + fnsScreenShot_Date_format()+"*****  < "+Loading_Image_Xpath+" >"+"  Exception ("+Throwables.getStackTraceAsString(i));
		throw new Exception("Getting Un-Expected Application Error <"+ErrorMsgText+">, please refer screenshot >> UnExpectedError" + fnsScreenShot_Date_format()+"*****  < "+Loading_Image_Xpath+" >"+"  Exception ("+Throwables.getStackTraceAsString(i));	
	}catch(NoSuchElementException n){
		System.out.println("***** Progressing Loading image displayed for < "+Actual_Loading_Time+" > seconds. **************** NS");
		try{
			System.out.println("***** Progressing Loading image displayed for < "+Actual_Loading_Time+" > seconds. **************** NS");
		}catch(Throwable tt){
			isTestCasePass = false;
			fnsTake_Screen_Shot("AlertLoadFail_NS");
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(tt));
			throw new Exception(Throwables.getStackTraceAsString(tt));		
		}
	}catch(StaleElementReferenceException n){
		System.out.println("***** Progressing Loading image displayed for < "+Actual_Loading_Time+" > seconds. **************** Stale");
	} catch(NoSuchWindowException W){
		throw new Exception(W.getMessage());
	}catch(InterruptedException ie){
		System.out.println("Interrupted-----Exception");
		fnsTake_Screen_Shot("AlertLoadFail");
		fnsApps_Report_Logs(Throwables.getStackTraceAsString(ie));
		throw new Exception(Throwables.getStackTraceAsString(ie));		
	}catch(Throwable tt){
		//isTestCasePass = false;
		fnsTake_Screen_Shot("AlertLoadFail");
		fnsApps_Report_Logs(Throwables.getStackTraceAsString(tt));
		throw new Exception(Throwables.getStackTraceAsString(tt));		
	}
	return Actual_Loading_Time;
}


//Function to find and return the object using Xpath
public WebElement WithOut_OR_fnGet_ObjectX(String XpathKey) throws Exception{
			
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
		fnsTake_Screen_Shot("NoSuchElementException");
		fnsApps_Report_Logs("FAILED == Element is not found >> "+XpathKey + " ] , plz see screenshot [NoSuchElementException" +   fnsScreenShot_Date_format() + " ]");
		throw new Exception("FAILED == Element is not found >> "+XpathKey + " ] , plz see screenshot [NoSuchElementException" +  fnsScreenShot_Date_format() + " ]");}
	catch(TimeoutException e){
	//	isTestCasePass = false;
		fnsTake_Screen_Shot("TimeOut" + XpathKey);
		fnsApps_Report_Logs("FAILED == Element is not Present, getting TimeOut >> "+XpathKey + " ] , plz see screenshot [TimeOut" +  fnsScreenShot_Date_format() + " ]");
		throw new Exception("FAILED == Element is not Present, getting TimeOut >> "+XpathKey + " ] , plz see screenshot [TimeOut" +  fnsScreenShot_Date_format() + " ]");}
	catch(Throwable e){
	//	isTestCasePass = false;
		fnsTake_Screen_Shot("NotPresent" + XpathKey);
		fnsApps_Report_Logs("FAILED == Element is not found >> "+XpathKey + " ] , plz see screenshot [NotPresent" +  fnsScreenShot_Date_format() + " ]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(e));
		throw new Exception("FAILED == Element is not found >> "+XpathKey + " ] , plz see screenshot [NotPresent" +  fnsScreenShot_Date_format() + " ]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(e));}
}

//Function to wait for element Visible
	public void WithOut_OR_fnGet_Element_Enabled(String XpathKey) throws Exception {
					
		try{
			for(int wait=0; wait<3; wait++){
				if(driver.findElements(By.xpath(XpathKey)).size()>0){
					WebDriverWait elementwaitvar = new WebDriverWait(driver,Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
					elementwaitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XpathKey)));
					
					WebDriverWait elementwaitvar1 = new WebDriverWait(driver,Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
					elementwaitvar1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XpathKey))).isEnabled();
					

					WebDriverWait elementwaitvar2 = new WebDriverWait(driver,Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
					elementwaitvar2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XpathKey))).isDisplayed();
					
					break;}
				else{
					throw new Exception();
				} // else loop closed
			} // for loop Closed
			fnsApps_Report_Logs("PASSED == Element is Visible having Xpath  >> "+XpathKey);		
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
				fnsApps_Report_Logs("PASSED == Element is Visible in 2nd attampt having Xpath  >> "+XpathKey);		
			}catch(NoSuchWindowException W){
				isTestCasePass = false;
				throw new Exception(W.getMessage());			}
			catch(Throwable e){
				isTestCasePass = false;
				fnsTake_Screen_Shot(XpathKey);
				fnsApps_Report_Logs("FAILED == Element is not Visible having Xpath  >> "+XpathKey+", plz see screenshot [ " + ""+XpathKey + fnsScreenShot_Date_format() + " ]. Getting Exception >> "+Throwables.getStackTraceAsString(e));
				throw new Exception("FAILED == Element is not Visible having Xpath  [ " + XpathKey + " ] , plz see screenshot [ " + ""+XpathKey + fnsScreenShot_Date_format() + " ]. Getting Exception >> "+Throwables.getStackTraceAsString(e));
			}
		}
}
	
//function to select drop down value
public void WithOut_OR_fnDD_SelectValue_By_ContainsText(String ddClickXpathKey, String ddListXpathKey, String TagName, String Value) throws Exception {
	
	for(int i=0; i<=10; i++){
		try{
			boolean ValueMatched=false;
			WithOut_OR_fnGet_Element_Enabled(ddClickXpathKey);
			WithOut_OR_fnGet_ObjectX(ddClickXpathKey).click();	
			WithOut_OR_fnGet_Element_Enabled(ddListXpathKey);
			List<WebElement> DDobjectlists=WithOut_OR_fnGet_ObjectX(ddListXpathKey).findElements(By.tagName(TagName));
				for(WebElement dd_value:DDobjectlists){
					if(dd_value.getText().contains(Value)){
						dd_value.click();
						ValueMatched=true;
						break;
					}
				}				
			if(ValueMatched==true){
				fnsApps_Report_Logs("PASSED == select value [ "+Value+" ] from drop down done, having xpath >>  " + ddClickXpathKey);
				break;
			}else{
				throw new Exception("Excel value is not matched with DropDown Value.");
			}			
		}catch(StaleElementReferenceException st){
			Thread.sleep(1000);
		}catch(NoSuchWindowException W){
			isTestCasePass = false;
			throw new Exception(W.getMessage());			}
		catch(Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot("DdValueSelectFailWOR");
			fnsApps_Report_Logs("FAILED == Unable to select value from drop down [ " + ddClickXpathKey +" ],plz see screenshot [ DdValueSelectFailWOR" +  fnsScreenShot_Date_format() + " ]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(t).trim());
			throw new Exception("FAILED == Unable to select value from drop down [ " + ddClickXpathKey +" ],plz see screenshot [ DdValueSelectFailWOR" +  fnsScreenShot_Date_format() + " ]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(t).trim());
		}
	}
}

public String fnc_return_Alerts_PaginationCount(String Alert_Name, String Alert_RecordCount_fromAlertName, Integer LastAlertContentTable) throws Throwable{
	String Per_Alert_Pagination_RecordCount = "";
	String Per_Alert_Pagination_RecordsCount_Xpath = "";
	
	for(int j=1; j<=30; j++){
		try{
			String Pagination_Xpath = "//span[@class='ui-paginator-current']";
			Integer All_Pagination_Elements = driver.findElements(By.xpath(Pagination_Xpath)).size();					
			Per_Alert_Pagination_RecordsCount_Xpath = "("+Pagination_Xpath+")["+All_Pagination_Elements+"]";
			
			WebElement Per_Alert_Pagination_RecordsCount_Element = driver.findElement(By.xpath(Per_Alert_Pagination_RecordsCount_Xpath));
			Actions act1 = new Actions(driver);
			act1.moveToElement(Per_Alert_Pagination_RecordsCount_Element).build().perform();
			Per_Alert_Pagination_RecordCount = Per_Alert_Pagination_RecordsCount_Element.getText().trim();
			Per_Alert_Pagination_RecordCount = Per_Alert_Pagination_RecordCount.split("of")[1].trim();
		}catch(Throwable t){
			//nothing to do
		}
		if(Alert_RecordCount_fromAlertName.equals(Per_Alert_Pagination_RecordCount)){
			break;
		}else{
			Thread.sleep(500);
		}
		try{
			if(j==30){
				String Per_alert_Records_Table_Xpath = "(//tbody[@class='ui-datatable-data ui-widget-content'])["+LastAlertContentTable+"]";
				List<WebElement> Per_Alert_DataTable_Objects = driver.findElement(By.xpath(Per_alert_Records_Table_Xpath)).findElements(By.tagName("tr"));
							
				for(WebElement Per_Alert_DataTable_Elememnt : Per_Alert_DataTable_Objects){
					Actions act2 = new Actions(driver);
					act2.moveToElement(Per_Alert_DataTable_Elememnt).build().perform();
					try{
						assert !( Per_Alert_DataTable_Elememnt.getText().toLowerCase().trim().contains("loading") || Per_Alert_DataTable_Elememnt.getText().toLowerCase().trim().contains("no result found") ):
							"FAILED == 'No Result Found' or 'Loading' is displayed instead of data ------- ["+Alert_Name+"], please refer the screenshot >> NoResultFound_Fail"+fnsScreenShot_Date_format();
						break;
					}catch(Throwable t){
						fnsTake_Screen_Shot("NoResultFound_Fail");
						throw new Exception(Throwables.getStackTraceAsString(t));
					}
				}
			}
		}catch (Throwable t){
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}
	return Per_Alert_Pagination_RecordCount;
}


//################################################################### Configuration Function ############################################################################	
	// Function to Launch browser and login
	public boolean fnsBrowserLaunchAndLogin(String LoginAs_UserName) throws Throwable {
		boolean present = false;
		ScreenShotFlagWithOR_Alerts_iPulse = true;
		startExecutionTime = fnpTimestamp();
		try{
			try{
				present = TestSuiteBase_MonitorPlan.fns_Launch_Browser_Only();			
				TestSuiteBase_MonitorPlan.fnsIpulse_Login_SSO("Alerts_ipulse", LoginAs_UserName, CONFIG.getProperty("adminUsername"), CONFIG.getProperty("adminPassword"));
				fnsApps_Report_Logs("Browser is launched and Successfully login into the 'iPulse' Application");	
			}catch(Throwable t)	{
				fnsTake_Screen_Shot("iPulse_Login_Fail");
				throw new Exception(Throwables.getStackTraceAsString(t));
			}
			return present;	
		} catch(NoSuchWindowException W){
			throw new Exception(W.getMessage());
		}catch (Throwable t) {
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));
		}		
}
	
	//Check class(Y/N) and Launch browser and Login 
	public void fnsCheckClassLevelTestSkip(String className) throws Exception {
		TC_Step=1;
		try {
			classNameText=className;
			currentScriptCode=fnpMappingClassNameWithScenarioCode(classNameText).trim();
			if (!TestUtil.isTestCaseRunnable(Alerts_iPulse_suitexls, className)) {
				fnsApps_Report_Logs("Skipping Test Case " + className + " as runmode set to NO");
				throw new SkipException("Skipping Test Case " + className + " as runmode set to NO");
			}
			isTestCasePass = true;
			runmodes = TestUtil.getDataSetRunmodes(Alerts_iPulse_suitexls, className);	
			
		
		}catch(SkipException sk){
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
		fns_CheckSiteSkip("Alerts_iPulse_Suite");
	}
	
		
	
//Always run after suite
	@AfterSuite(alwaysRun=true)
	public void Finishing_Alerts_iPulse_Suite_Script() throws Throwable {
		ScreenShotFlagWithOR_Alerts_iPulse = false;
		
		fnsApps_Report_Logs("=========================================================================================================================================");
		fnsApps_Report_Logs("*****#########*********#######>>>>>>>>>>>Automation Total alerts counts for all the executed cases is = "+Total_Alerts_Run_Count );
		fnsApps_Report_Logs("=========================================================================================================================================");
		fnsApps_Report_Logs("");
		fnsApps_Report_Logs("######################################################## Alerts iPulse Suite END ######################################################## ");
		fnsApps_Report_Logs("=========================================================================================================================================");
		fnsApps_Report_Logs("");
}	
	//################################################################ Client Navigation Function ##########################################
		public String fnsReturn_ExcelCellValue_ByMatchingColumnName1(String SheetName, String ColumnName) throws Throwable{
			try{
				String CellValue = null;
				for(int i=5; i<50; i++){
					for(int j=0; j<16; j++){
						String ExcelCellValue = Alerts_iPulse_suitexls.getCellData(SheetName, j, i);
						if(ExcelCellValue.equalsIgnoreCase(ColumnName)){
							CellValue = Alerts_iPulse_suitexls.getCellData(SheetName, j, i+1);
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
				fnsApps_Report_Logs("Excel Value = "+CellValue);
				return CellValue.trim();
			}catch(Throwable t){
				isTestCasePass = false;
				fnsApps_Report_Logs(t.getMessage());
				throw new Exception(t.getMessage());	}
		}
		
		
		public void fngClickTopMainMenuAndFacilityLookup(String FirstMouseHoverMenuName, String searchClientAjax, String facilityLookupButton) throws Throwable {
			int whileCounter = 0;
			while (true) {
				whileCounter++;
				try {
					Thread.sleep(1000);
					fngWaitForVisible(FirstMouseHoverMenuName);
					WebElement firstElement=fngGetORObjectX_WhenMultipleObjectsHavingSameXpath_NOR(FirstMouseHoverMenuName);
	 				Thread.sleep(1000);
					Actions action = new Actions(driver);
					action.moveToElement(firstElement);
					action.click().build().perform();
					fnsApps_Report_Logs("Clicked on the MENU");
					fngCheckErrMsgTest("FAILED-AfterClickMENU");
					Thread.sleep(1000);
					/***********************************************************************/
					fngWaitForVisible(searchClientAjax);
					WebElement third = fngGetORObjectX_WhenMultipleObjectsHavingSameXpath_NOR(searchClientAjax);
					action.moveToElement(third);
					action.click().build().perform();
					fnsApps_Report_Logs("Clicked on the Search Client Ajax");
					//fngLoading_wait();
					Thread.sleep(1000);
					fngCheckErrMsgTest("FAILED-AfterClickSearchClientAjax");
					/***************** ******************************************************/
					fngWaitForVisible(facilityLookupButton);
					WebElement clientLookup = fngGetORObjectX_WhenMultipleObjectsHavingSameXpath_NOR(facilityLookupButton);
					clientLookup.click();
					fnsApps_Report_Logs("Clicked on the Search Facility lookup Button");
					fngCheckErrMsgTest("FAILED-AfterClickSearchFacilityLookupButton");
					fngLoading_wait();
					break;
				} catch (Throwable t) {
					if (whileCounter > 10) {
						throw new Exception("Failed in method fnpCommonClickTopMainMenu .  Reason is ---" + t.getMessage());
					} else {
						fnsApps_Report_Logs("Going to refresh browser in fnpCommonClickTopMainMenu due to this exception -" + t.getMessage());
						driver.navigate().refresh();
						Thread.sleep(10000);
					}
				}
			}
		}
		
		public void fngEnterValueInTextField(String xpath, String value) throws Exception{
			try{
				WebElement element=fngGetORObjectX___NOR(xpath);
				Thread.sleep(1000);
				element.sendKeys(value);
				Thread.sleep(1000);
			}catch(NoSuchElementException e){
				fnsApps_Report_Logs("FAILED == Element is not found >> "+xpath );
				throw new Exception("FAILED == Element is not found >> "+xpath );
			}catch(TimeoutException e){
				fnsApps_Report_Logs("FAILED == Element is not Present, getting TimeOut >> "+xpath);
				throw new Exception("FAILED == Element is not Present, getting TimeOut >> "+xpath);
			}catch(StaleElementReferenceException e){
				fnsApps_Report_Logs("FAILED == Element is not found >> "+xpath +" "+Throwables.getStackTraceAsString(e));
				throw new Exception("FAILED == Element is not found >> "+xpath);
			}catch(Throwable e){
				fnsApps_Report_Logs("FAILED == Unable To Enter value into the Element having Xpath >> "+xpath);
				throw new Exception("FAILED == Unable To Enter value into the Element having Xpath >> "+xpath);
			}
		}
		public String fngGetTextValueByXpath(String xpath) throws Exception{ 
			String txtValue="";
			try{
				fngWaitForVisible(xpath);
				WebElement element=fngGetORObjectX___NOR(xpath);
				Thread.sleep(1000);
				txtValue=fngReturnText_notCallItDirectly(element);
				Thread.sleep(1000);
			}catch(NoSuchElementException e){
				fnsApps_Report_Logs("FAILED == Element is not found >> "+xpath );
				throw new Exception("FAILED == Element is not found >> "+xpath );
			}catch(TimeoutException e){
				fnsApps_Report_Logs("FAILED == Element is not Present, getting TimeOut >> "+xpath);
				throw new Exception("FAILED == Element is not Present, getting TimeOut >> "+xpath);
			}catch(StaleElementReferenceException e){
				fnsApps_Report_Logs("FAILED == Element is not found >> "+xpath +" "+Throwables.getStackTraceAsString(e));
				throw new Exception("FAILED == Element is not found >> "+xpath);
			}catch(Throwable e){
				fnsApps_Report_Logs("FAILED == Unable To Get value From the Element having Xpath >> "+xpath);
				throw new Exception("FAILED == Unable To Get value From the Element having Xpath >> "+xpath);
			}return txtValue;
		}
		public void fngClickByXpath(String xpath) throws Exception{
			try{
				fngWaitForVisible(xpath);
				WebElement element=fngGetORObjectX___NOR(xpath);
				element.click();
				//fngLoading_wait();
				//fngCheckErrMsgTest("AfterClickBackButton");
			}catch(NoSuchElementException e){
				fnsApps_Report_Logs("FAILED == Element is not found >> "+xpath );
				throw new Exception("FAILED == Element is not found >> "+xpath );
			}catch(TimeoutException e){
				fnsApps_Report_Logs("FAILED == Element is not Present, getting TimeOut >> "+xpath);
				throw new Exception("FAILED == Element is not Present, getting TimeOut >> "+xpath);
			}catch(StaleElementReferenceException e){
				fnsApps_Report_Logs("FAILED == Element is not found >> "+xpath +" "+Throwables.getStackTraceAsString(e));
				throw new Exception("FAILED == Element is not found >> "+xpath);
			}catch(Throwable e){
				fnsApps_Report_Logs("FAILED == Unable To click the Element having Xpath >> "+xpath);
				throw new Exception("FAILED == Unable To click the Element having Xpath >> "+xpath);
			}
		}
		public boolean fngIsElementVisible(String xpath) throws Exception{
			boolean flag=false;
			try{
				fngWaitForVisible(xpath);
				Thread.sleep(1000);
				WebElement element=fngGetORObjectX___NOR(xpath);
				Thread.sleep(1000);
				if(element.isEnabled()){
					flag=true;
				}
				Thread.sleep(1000);
			}catch(NoSuchElementException e){
				fnsApps_Report_Logs("FAILED == Element is not found >> "+xpath );
				throw new Exception("FAILED == Element is not found >> "+xpath );
			}catch(TimeoutException e){
				fnsApps_Report_Logs("FAILED == Element is not Present, getting TimeOut >> "+xpath);
				throw new Exception("FAILED == Element is not Present, getting TimeOut >> "+xpath);
			}catch(StaleElementReferenceException e){
				fnsApps_Report_Logs("FAILED == Element is not found >> "+xpath +" "+Throwables.getStackTraceAsString(e));
				throw new Exception("FAILED == Element is not found >> "+xpath);
			}catch(Throwable e){
				fnsApps_Report_Logs("FAILED == The Element not visible having Xpath >> "+xpath);
				throw new Exception("FAILED == The Element not visible having Xpath >> "+xpath);
			}return flag;
		}
		public boolean fngIsSelectedTab(String xpath,String tabName) throws Exception{
			boolean flag=false;
			try{
				
				int elementNo=driver.findElements(By.xpath(xpath)).size();
				if(elementNo>0){
					fngWaitForVisible(xpath);
					Thread.sleep(300);
					
					WebElement element=fngGetORObjectX___NOR(xpath);
					Thread.sleep(300);
					if(element.isDisplayed()){
						flag=true;
						Thread.sleep(300);
						fnsApps_Report_Logs(tabName+ " tab is Opened!!");
					}
				}
			}catch(NoSuchElementException e){
				fnsApps_Report_Logs("FAILED == Element is not found >> "+xpath );
				throw new Exception("FAILED == Element is not found >> "+xpath );
			}catch(TimeoutException e){
				fnsApps_Report_Logs("FAILED == Element is not Present, getting TimeOut >> "+xpath);
				throw new Exception("FAILED == Element is not Present, getting TimeOut >> "+xpath);
			}catch(StaleElementReferenceException e){
				fnsApps_Report_Logs("FAILED == Element is not found >> "+xpath +" "+Throwables.getStackTraceAsString(e));
				throw new Exception("FAILED == Element is not found >> "+xpath);
			}catch(Throwable e){
				fnsApps_Report_Logs("FAILED == Unable To Select the Element having Xpath >> "+xpath);
				throw new Exception("FAILED == Unable To Select the Element having Xpath >> "+xpath);
			}return flag;
		}
		public String fngCheckAndClickLink(String linkXpath,String tableXpath) throws Throwable{
			boolean flag = false;
			String linkValue="";
			int size=driver.findElements(By.xpath(tableXpath)).size();
			fnsApps_Report_Logs(tableXpath+" Element size is : "+size);
			if(size>0){
				WebElement TableElement=fngGetORObjectX___NOR(tableXpath);
				flag = fngIsElementVisible(tableXpath);
				if(flag){
					int rows = driver.findElements(By.xpath(tableXpath)).size();
					linkValue = TableElement.getText();
					//if(rows>0){
					if(!linkValue.contains("No records found")&& !linkValue.isEmpty()){
						fngClickByXpath(linkXpath);
						fngLoading_wait();
						fngCheckErrMsgTest("afterClickOnLink");
						fnsApps_Report_Logs("Clicked on the Link!!!");
					}else{
						//fngCheckErrMsgTest(" Link is not available into the table");
						fnsApps_Report_Logs("Links are not available into the Table.");
						//throw new Exception();
					}
				}else{
					fnsApps_Report_Logs(" Element is not visible ");
					//fngCheckErrMsgTest(" Element is not visible  ");
					//throw new Exception();
				}
			}else{
				fnsApps_Report_Logs("Xpath of the element or element not present !!");
			}return linkValue;
			
			
			/*boolean flag = false;
			String linkValue="";
			int size=driver.findElements(By.xpath(linkXpath)).size();
			fnsApps_Report_Logs(linkXpath+" Element size is : "+size);
			if(size>0){
				WebElement linkElement=fngGetORObjectX___NOR(linkXpath);
				flag = fngIsElementVisible(linkXpath);
				if(flag){
					int rows = driver.findElements(By.xpath(tableXpath)).size();
					linkValue = linkElement.getText();
					//if(rows>0){
					if(!linkValue.contains("No records found")&& !linkValue.isEmpty()){
						fngClickByXpath(linkXpath);// fngLoading_wait();
						fngCheckErrMsgTest("afterClickOnLink");
						fnsApps_Report_Logs("Clicked on the Link!!!");
					}else{
						//fngCheckErrMsgTest(" Link is not available into the table");
						fnsApps_Report_Logs("Links are not available into the Table.");
						//throw new Exception();
					}
				}else{
					fnsApps_Report_Logs(" Element is not visible ");
					//fngCheckErrMsgTest(" Element is not visible  ");
					//throw new Exception();
				}
			}else{
				fnsApps_Report_Logs("Xpath of the element or element not present !!");
			}return linkValue;*/
		}
		public String fngCheckAndClickInvoiceLink(String linkXpath,String tableXpath) throws Throwable{
			boolean flag = false;
			String linkValue="";
			int size=driver.findElements(By.xpath(linkXpath)).size();
			fnsApps_Report_Logs(linkXpath+" Element size is : "+size);
			if(size>0){
				WebElement linkElement=fngGetORObjectX___NOR(linkXpath);
				flag = fngIsElementVisible(linkXpath);
				if(flag){
					int rows = driver.findElements(By.xpath(tableXpath)).size();
					linkValue = linkElement.getText();
					if(rows>0){
						fngClickByXpath(linkXpath);
						fngCheckErrMsgForInvoice("FAILED-afterClickOnInvoiceLink");
						fnsApps_Report_Logs("Clicked Invoice# Link in Open Invoices Tab ");
					}else{
						//fngCheckErrMsgTest(" Link is not available into the table");
						fnsApps_Report_Logs("Invoice# Link is not available into Open Invoices Tab");
						//throw new Exception();
					}
				}else{
					fnsApps_Report_Logs(" Element is not visible ");
					//fngCheckErrMsgTest(" Element is not visible  ");
					//throw new Exception();
				}
			}else{
				fnsApps_Report_Logs("Xpath of the element or element not present !!");
			}return linkValue;
		}
		/***********
		 * Function to find and return the object using Xpath which are present in
		 * OR
		 *************/
		public WebElement fngGetORObjectX_WhenMultipleObjectsHavingSameXpath_NOR(String Xpath) throws Exception {
			int retries = 0;
			WebElement requiredObj = null;
			try {
				if (driver.findElements(By.xpath(Xpath)).size() > 0) {
					List<WebElement> objList = fngGetORObject_list_NOR(Xpath, 2); 
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
					fnsApps_Report_Logs(retries + "In staleElementException catch block of fnpGetORObjectX_WhenMultipleObjectsHavingSameXpath function for " + Xpath);
					// continue;
				}
			}
			catch (org.openqa.selenium.InvalidSelectorException is) {
				if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					Thread.sleep(1000);
					retries++;
					fnsApps_Report_Logs(retries + "In org.openqa.selenium.InvalidSelectorException catch block of fnpGetORObjectX_WhenMultipleObjectsHavingSameXpath function for " + Xpath);
					// continue;
				} else {
					throw is;
				}
			}
			catch (Throwable t) {
				fnsTake_Screen_Shot("NotPresent");
				throw new Exception(t.getMessage() + "   So,Unable to find element with name [" + Xpath + "] ,plz see screenshot [NotPresent" + Xpath + SShots + "]");
			}
			return requiredObj;
		}
		public List<WebElement> fngGetORObject_list_NOR(String Xpath, int maxTimeInSeconds) throws Exception {

			int retries = 0;
			while (true) {
				try {

					driver.manage().timeouts().implicitlyWait(maxTimeInSeconds, TimeUnit.SECONDS);
					return driver.findElements(By.xpath(Xpath));

				} catch (org.openqa.selenium.StaleElementReferenceException s) {
					if (retries < maxTimeInSeconds) {
						Thread.sleep(1000);
						retries++;
						fnsApps_Report_Logs(retries + "In staleElementException catch block of fnpGetORObject_list 2 arguments  function for " + Xpath);
						// continue;
					} else {
						throw s;
					}
				}
				catch (org.openqa.selenium.InvalidSelectorException is) {
					if (retries < 10) {
						Thread.sleep(1000);
						retries++;
						fnsApps_Report_Logs(retries + "In InvalidSelectorException catch block of fnpGetORObject_list 2 arguments  function for " + Xpath);
						// continue;
					} else {
						throw is;
					}
				} catch (Throwable t) {
					if (retries < maxTimeInSeconds) {
						// Thread.sleep(1000);
						retries++;
						fnsApps_Report_Logs(retries + "In Throwable catch block of fnpGetORObject_list 2 arguments  function for " + Xpath);
					} else {
						throw new Exception(t.getMessage() + "   So,Unable to find element with name [" + Xpath + "] ,plz see screenshot [NotPresent" + Xpath + SShots + "]");
					}
				}
				finally {
					driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);
				}
			}
		}
		
		
		/*********** wait till element get visible using Link Name ************/
		public void fngWaitForVisible_usingLinkNameInOR(String ORLinkName) throws Throwable {
			int i = 0;
			WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(CONFIG.getProperty("genMax_waitTime")));
			int retries = 0;
			while (true) {
				i++;
				try {

					if (OR_Navi.getProperty(ORLinkName).contains("~")) {

						String[] s1 = OR_Navi.getProperty(ORLinkName).split("~");
						String locatorValue = s1[1];
						String locator = s1[0];
						if (locator.contains("id")) {// id
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
						}else{
							if (locator.toLowerCase().contains("linktext")) {// linkText
								wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locatorValue)));
							} else {
								if (locator.toLowerCase().contains("xpath")) {// xpath
									wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
								} else {
									wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
								}
							}
						}
					} else {
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(OR_Navi.getProperty(ORLinkName))));
					}
					return;
				} catch (org.openqa.selenium.StaleElementReferenceException e) {
					if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
						retries++;
						fnsApps_Report_Logs(retries + "In staleElementException catch block of fnpWaitForVisible_usingLinkNameInOR function for " + ORLinkName);
						continue;
					} else {
						throw e;
					}
				} catch (Throwable t) {
					if (retries < 2) {
						retries++;
						fnsApps_Report_Logs(retries + "In fnpWaitForVisible_usingLinkNameInOR 's try catch block  for " + ORLinkName);
						continue;
					} else {
						// throw t;

						if ((t.getMessage().contains("waiting for visibility of element located by By.xpath")) || (t.getMessage().contains("Timed out after"))) {
							throw new Exception(" Unable to find element with name [" + ORLinkName + "]  \n\n\n\n  " + t.getMessage());
						} else {
							throw new Exception(t.getMessage());
						}

					}
				}
			}
		}
		
		/**** To mouse hover on an element having a link Name present in OR ****/
		public  void fnpMouseHover_LinkNameInOR(String ORObjectName) throws Throwable {
			fngWaitForVisible_usingLinkNameInOR(ORObjectName);
			WebElement hoverElement =fngGetORObjectX_WhenMultipleObjectsHavingSameXpath_NOR(ORObjectName);
			Actions action = new Actions(driver);
			action.moveToElement(hoverElement).build().perform();
			if (browserName.equalsIgnoreCase("firefox")) {
				Thread.sleep(1000);
			}
		}
		/*********** Wait till main loading icon overs *************/
		public void fngLoading_wait() throws Throwable {
			int i = 0;
			int maxloopDependOnBrowser = 0;
			int totalSecond=0;
			boolean loadinImageTakingMuchTime=false;
			try{
				while (true) {
					i++;
					if( !(fngCheckElementDisplayedImmediately("ProgressImageIcon")) ){
						totalSecond+=i;
						break;
					} else {
						maxloopDependOnBrowser = Integer.parseInt(CONFIG.getProperty("loadingIcon_Max_waitTime"));//LoadingIconMaxWaitForNavigation
					}
					if (i > maxloopDependOnBrowser) {
						totalSecond=maxloopDependOnBrowser + 1;
						loadinImageTakingMuchTime=true;
						break;
					} else {
						totalSecond=i;
						Thread.sleep(1000);
					}
					fnsApps_Report_Logs("Loading is visible - sqsLoading_wait()--" + totalSecond+ " seconds");
				}
				Thread.sleep(1000); 
				if(loadinImageTakingMuchTime){
					throw new Throwable();
				}
			}catch (Throwable t) {
				// code for snapshot
				fnsApps_Report_Logs("FAILED:>>> Loading image is taking too much time :>>" + totalSecond+ " seconds, That is why below exception is coming:>>");
				throw new Exception("FAILED:>>> Loading image is taking too much time :>>"+Throwables.getStackTraceAsString(t));
			}
		}
			
		
		public  void fngIpulseDuringLoading() throws Throwable {
			int i = 0;
			while (fngCheckElementDisplayedImmediately("ProgressImageIcon")) {
				Thread.sleep(1000);
				i++;
				fnsApps_Report_Logs("@  ------fnpCheckElementDisplayedImmediately 'ProgressImageIcon'------" + i);
				fnsApps_Report_Logs("@@@    loading is visible ---" + i);
				if (i > Integer.parseInt(CONFIG.getProperty("genMax_waitTime")) * 2) {
					break;
				}
			}		
			i = 0;
			while (fngCheckElementDisplayedImmediately("LoadingImg")) {
				fnsApps_Report_Logs("@@@   while loop when loading is visible now inside - fnpLoading_waitInsideDialogBox--" + i);
				Thread.sleep(1000);
				i++;
				int maxT=Integer.parseInt(CONFIG.getProperty("loadingIcon_Max_waitTime")) ;
				if (i > maxT) {
					throw new Exception("Loading is not getting over even after waiting approx.  "+maxT+" seconds");
					//break;
				}
			}
		}
		/*********** To check element is displayed immediately or not *************/
		public boolean fngCheckElementDisplayedImmediately(String XpathKey) {
			int i = 0;
			try {
				driver.manage().timeouts().implicitlyWait(1, TimeUnit.MILLISECONDS);
				List<WebElement> elementList = fngGetORObject_list(XpathKey, 0,500);
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
				driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);
			}
			if (i == 0) {
				return false;
			} else {
				return true;
			}
		}
		public List<WebElement> fngGetORObject_list(String XpathKey, int maxTimeInSeconds, long maxTimeInMilliSeconds) throws Throwable {
			int retries = 0;
			while (true) {
				try {
					if ((XpathKey.trim().toLowerCase().contains("error")) || (XpathKey.trim().toLowerCase().contains("ProgressImageIcon"))
							|| (XpathKey.trim().toLowerCase().contains("BlockedUIScreen"))) {
						driver.manage().timeouts().implicitlyWait(1, TimeUnit.MILLISECONDS);

					} else {
						driver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS);

					}
					if (OR_Navi.getProperty(XpathKey).contains("~")) {
						return fngReturnWebElementAfterExtractLocatorandValue(OR_Navi.getProperty(XpathKey));
					} else {
						/****** By default Xpath will be assumed *****/
						return driver.findElements(By.xpath(OR_Navi.getProperty(XpathKey)));
					}

				} catch (org.openqa.selenium.StaleElementReferenceException s) {
					if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
						// if (retries < 3) {
						Thread.sleep(1000);
						retries++;
						fnsApps_Report_Logs(retries + "In staleElementException catch block of fnpGetORObject_list 3 arguments function for " + XpathKey);
						// continue;
					} else {
						throw s;
					}
				}

				catch (org.openqa.selenium.InvalidSelectorException is) {

					if (retries < 10) {
						Thread.sleep(2000);
						retries++;
						fnsApps_Report_Logs(retries + "In InvalidSelectorException catch block of fnpGetORObject_list 3 arguments  function for " + XpathKey);
						// continue;
					} else {
						fnsApps_Report_Logs("");
						throw is;
					}
				} catch (Throwable t) {
					//fnpDesireScreenshot("NotPresent" + XpathKey);
					throw new Exception(t.getMessage() + "   So,Unable to find element with name [" + XpathKey + "] ,plz see screenshot [NotPresent" + XpathKey + SShots + "]");
				}finally {
					driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);
				}
			}
		}
		public static List<WebElement> fngReturnWebElementAfterExtractLocatorandValue(String s) throws Exception {
			try{
				String[] s1 = s.split("~");
				String locatorValue = s1[1];
				String locator = s1[0];
				driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
				if (locator.equalsIgnoreCase("id")) {
					return driver.findElements(By.id(locatorValue));
				} else {
					if (locator.toLowerCase().equalsIgnoreCase("xpath")) {
						return driver.findElements(By.xpath(locatorValue));
					} else {
						/****** By default Xpath will be assumed *****/
						return driver.findElements(By.xpath(locatorValue));
					}
				}
			}catch(Throwable t){
				throw new Exception();
			}finally{
				driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);
			}
		}
		/*********** To check element is displayed immediately or not *************/
		public  boolean fngCheckElementDisplayedImmediately_NOR(String Xpath) {

			int i = 0;
			try {

				driver.manage().timeouts().implicitlyWait(1, TimeUnit.MILLISECONDS);

				List<WebElement> elementList = fngGetORObject_list_NOR(Xpath, 1);

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
				driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);
			}
			if (i == 0) {
				return false;
			} else {
				return true;
			}
		}
		
		/***** check error in application page ***//*
		public void fnpCheckError(String msg) throws Throwable {
			if (fngCheckElementDisplayedImmediately_NOR("ErrorMessage")) {
				fngMouseHover_NotInOR("ErrorMessage");
				String errMsg = fngGetText_NOR_EvenMultipleObjectsHavingSameXpath("ErrorMessage").trim();
				int errorLength = errMsg.length();
				fnsApps_Report_Logs("@  ----Error length is---" + errorLength);
				if (errorLength > 1) {
					fnsApps_Report_Logs(" Error is thrown by application " + msg);
					throw new Exception(" Error is thrown by application " + msg + "\n\n Error is --->" + errMsg);
				}
			}
			// for Error Page
				if (fngCheckElementDisplayedImmediately_NOR("Error_PageIniPulse")) {
					fngMouseHover_NotInOR("Error_PageIniPulse");
					String errMsg = fngGetText_NOR_EvenMultipleObjectsHavingSameXpath("Content_Of_Error_PageIniPulse").trim();
				int errorLength = errMsg.length();
				fnsApps_Report_Logs("@  ----Error length is---" + errorLength);
				if (errorLength > 1) {
					fnsApps_Report_Logs(" Error is thrown by application " + msg);
					throw new Exception(" Error is thrown by application " + msg + "\n\n Error is --->" + errMsg);
				}
			}
		}*/

		/***** check error in application page ***//*
		public void fnpCheckErrMsg(String errCustomMsg) throws Throwable {
			if (fngCheckElementDisplayedImmediately_NOR("ErrorMessage")) {
				fngMouseHover_NotInOR("ErrorMessage");
				String errmsg = fngGetText_NOR_EvenMultipleObjectsHavingSameXpath("ErrorMessage");
				int errorLength = errmsg.length();
				fnsApps_Report_Logs("@  ----Error length is---" + errorLength);
				if (errorLength > 1) {
					fnsApps_Report_Logs(errCustomMsg + " i.e --" + errmsg);
					String pageSource = driver.getPageSource().toString();
					// pageSource = pageSource.replace("@", "");
					pageSource = fngFormatReplaceSpecailCharacters(pageSource);
					// fnpMymsg("@  ----Page source is \n\n" + pageSource);
					fnsApps_Report_Logs("@   ---*********COMMENTING PAGE SOURCE For Time being before release on 4th March********************");
					throw new Exception(errCustomMsg + " i.e --" + errmsg);

				}
				//ErrorMessage
			}

			//if (fnpCheckElementPresenceImmediately("Error_PageIniPulse")) {
			if (fngCheckElementDisplayedImmediately_NOR("Error_PageIniPulse")) {

				// fnpMouseHover_OR_EvenMultipleObjectsHavingSameXpath("Error_PageIniPulse");
				//fnpMouseHover_OR_EvenMultipleObjectsHavingSameXpath_usingSpecialForErrorClassOnly("Error_PageIniPulse");
				fngMouseHover_NotInOR("Error_PageIniPulse");
				//String errmsg = fnpGetText_OR_EvenMultipleObjectsHavingSameXpath_specialForExtractingErrorMsgTextOnlyEvenIfNotVisible("Content_Of_Error_PageIniPulse");
				String errmsg = fngGetText_NOR_EvenMultipleObjectsHavingSameXpath("Content_Of_Error_PageIniPulse");
				int errorLength = errmsg.length();

				fnsApps_Report_Logs("@  ----Error length is---" + errorLength);
				if (errorLength > 1) {
					fnsApps_Report_Logs(errCustomMsg + " i.e --" + errmsg);
					String pageSource = driver.getPageSource().toString();
					// pageSource = pageSource.replace("@", "");
					pageSource = fngFormatReplaceSpecailCharacters(pageSource);
					// fnpMymsg("@  ----Page source is \n\n" + pageSource);
					fnsApps_Report_Logs("@   ---*********COMMENTING PAGE SOURCE For Time being before release on 4th March********************");
					throw new Exception(errCustomMsg + " i.e --" + errmsg);

				}

			}

		}*/
		
		public void fngMouseHover_NotInOR(String Xpath) throws Throwable {

			fnsApps_Report_Logs("@  ---just before mouse hover " + Xpath);

			WebElement requiredObj = null;

			if (fngGetORObject_list_NOR(Xpath, Integer.parseInt(CONFIG.getProperty("implicitWaitTime"))).size() > 0) {

				List<WebElement> objList = fngGetORObject_list_NOR(Xpath, Integer.parseInt(CONFIG.getProperty("implicitWaitTime")));

				for (int i = 0; i < objList.size(); i++) {
					if ((objList.get(i).isDisplayed())) {
						requiredObj = objList.get(i);
						break;
					}
				}
			} else {
				requiredObj = fngGetORObjectX___NOR(Xpath);
			}

			WebElement hoverElement = requiredObj;
			Actions action = new Actions(driver);
			action.moveToElement(hoverElement).perform();
			Thread.sleep(500);

			fnsApps_Report_Logs("@  ---just after mouse hover " + Xpath);

		}

		public WebElement fngGetORObjectX___NOR(String Xpath) throws Throwable {

			int retries = 0;
			int throwablecount = 0;
			while (true) {
				try {
					driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
					Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout((Long.parseLong(CONFIG.getProperty("genMax_waitTime"))), TimeUnit.SECONDS)
							.pollingEvery(2, TimeUnit.SECONDS).ignoring(org.openqa.selenium.NoSuchElementException.class).ignoring(org.openqa.selenium.InvalidSelectorException.class)
							.ignoring(org.openqa.selenium.StaleElementReferenceException.class).ignoring(org.openqa.selenium.WebDriverException.class);

					WebElement foo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
					foo = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Xpath)));
					return driver.findElement(By.xpath(Xpath));

				} catch (org.openqa.selenium.StaleElementReferenceException s) {
					if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
						Thread.sleep(1000);
						retries++;
						fnsApps_Report_Logs(retries + " In staleElementException catch block of fnpGetORObjectX___NOR function for " + Xpath);
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
						fnsApps_Report_Logs(retries + "In Throwable catch block of fnpGetORObjectX___NOR function for " + Xpath);
						// continue;
					} else {
						//fnpDesireScreenshot("NotPresent" + Xpath); Ghanshyam
						throw new Exception("   So,Unable to find element with name [" + Xpath + "] ,plz see screenshot [NotPresent" + Xpath + SShots + "].  Expception is ---"
								+ t.getMessage());
					}
				}

				finally {
					driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);
				}
			}

		}
		
		public String fngGetText_NOR_EvenMultipleObjectsHavingSameXpath(String Xpath) throws Exception {
			WebElement requiredObj = null;
			String returnText = "";
			if (fngGetORObject_list_NOR(Xpath, Integer.parseInt(CONFIG.getProperty("implicitWaitTime"))).size() > 0) {

				List<WebElement> objList = fngGetORObject_list_NOR(Xpath, Integer.parseInt(CONFIG.getProperty("implicitWaitTime")));

				String currentText = "";
				for (int i = 0; i < objList.size(); i++) {
					currentText = fngReturnText_notCallItDirectly(objList.get(i));
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
		
		public static String fngReturnText_notCallItDirectly(WebElement w) {
			String returnText = "";
			try {
				returnText = w.getText();
			} catch (org.openqa.selenium.WebDriverException we) {
				String exceptMessage = we.getMessage();
				if (exceptMessage.contains("Unable to get element text")) {
					returnText = "";

				} else {
					//throw we; //commenting this on 03-05-2018 as sometime w element throw staleElementException
				}
			}

			return returnText;
		}
		public static String fngFormatReplaceSpecailCharacters(String msg) {

			String afterFormat = "";
			try {

				  msg = msg.replaceAll("ï¿½", "");
				  msg = msg.replaceAll("ï¿½", "");
				  msg= msg.replaceAll("ï¿½", "");
				  msg = msg.replaceAll("@", "");
				  msg = msg.replaceAll("ï¿½", "");

				afterFormat = msg;

			} catch (Throwable t) {

			}

			return afterFormat;
		}
		
		public void fngWaitForVisible(String elementXpath) throws Throwable { 
			int whileCounter = 0;
			while (true) {
				driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
				try {
					Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout((Long.parseLong(CONFIG.getProperty("genMax_waitTime"))), TimeUnit.SECONDS)
							.pollingEvery(1, TimeUnit.SECONDS).ignoring(org.openqa.selenium.StaleElementReferenceException.class)
							.ignoring(org.openqa.selenium.WebDriverException.class).ignoring(org.openqa.selenium.InvalidSelectorException.class)
							.ignoring(org.openqa.selenium.NoSuchElementException.class);
					
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementXpath)));
					break;
				} catch (Throwable t) {
					whileCounter++;
					fnsApps_Report_Logs("@  ---In catch Throwbale block for whilecounter value--" + whileCounter);
					if (whileCounter > 1) {

						if ((t.getMessage().contains("waiting for visibility of element located by By.xpath")) || (t.getMessage().contains("Timed out after"))) {
							throw new Exception(" Unable to find element with name [" + elementXpath + "]  \n\n\n\n  " + t.getMessage());
						} else {
							throw new Exception(t.getMessage());
						}

					}
				}
				finally {
					driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);
				}
			}
		}
		//Comman method to wirte pass/fail into excel/log file.	
			public void fns_ReportTestResult_atClassLevel(Xls_Reader Suite_Excel_Variable_Name, String Class_Name) throws Throwable {

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
				fnsApps_Report_Logs("=========================================================================================================================================");

				hashXlData.clear();
			}
			@SuppressWarnings("finally")
			public static boolean fns_Launch_Browser_Only() throws Throwable{
				boolean present = false;
				try{
					NewNsfOnline_Element_Max_Wait_Time = (int) Long.parseLong("600");//(Long.parseLong(CONFIG.getProperty("ElementWaitTime")))*5;
					start = new Date();
					if (browserName.equalsIgnoreCase("IE")) {

						System.setProperty("webdriver.ie.driver", CONFIG.getProperty("IEDriverPath"));

						DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
						caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
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
						caps = DesiredCapabilities.chrome();
						options = new ChromeOptions();
						options.addArguments("--disable-infobars");
						options.addArguments("--start-maximized");
					    options.addArguments("--disable-web-security");
					    options.addArguments("--no-proxy-server");
					    Map<String, Object> prefs = new HashMap<String, Object>();
					    prefs.put("credentials_enable_service", false);
					    prefs.put("profile.password_manager_enabled", false);
					    options.setExperimentalOption("prefs", prefs);
					   
					   
						caps.setCapability(ChromeOptions.CAPABILITY, options);																																												
						
						driver=new ChromeDriver(caps);				
					    
					//	fnsApps_Report_Logs("Browser type is CHROME");
						 present=true;
					}
					if (browserName.equalsIgnoreCase("firefox")) {
						FirefoxProfile profile = new FirefoxProfile();
						DesiredCapabilities dc=DesiredCapabilities.firefox();
						profile.setPreference("security.mixed_content.block_active_content", false);
						profile.setPreference("security.mixed_content.block_display_content", false); 
						dc = DesiredCapabilities.firefox();
						dc.setCapability(FirefoxDriver.PROFILE, profile);
						driver = new FirefoxDriver(dc);
					//	fnsApps_Report_Logs("Browser type is Firefox");
						 present=true;
					}
				}catch(Throwable t){
					present = false;
					throw new Exception (Throwables.getStackTraceAsString(t));
				}finally{
					return present;
				}	
				
			}
			public void fnsIpulse_Login_Split_Excel_Urls(String PortalNamne, String LoginAs, String userName, String password) throws Throwable{
				try{
					driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
				//	iPulse_SecureLogin_Flag=true
					if  (PortalNamne.toLowerCase().trim().equalsIgnoreCase("Alerts_ipulse")){
						if(LoginAs.toLowerCase().equalsIgnoreCase("testscriptuser")){
							PortalNamne = "ipulse";
						}else{
							PortalNamne = "secureipulse";
						}				
					}/*else if( !( (PortalNamne.toLowerCase().trim().equalsIgnoreCase("ipulse_newnsfonline")) )){
						if ( (LoginAs.toUpperCase().trim().equalsIgnoreCase("JHUGHES")) || TestSuiteBase_Grip.SecureLoginAs_Flag || ( CONFIG.getProperty("iPulse_SecureLogin_Flag").toLowerCase().trim().equalsIgnoreCase("true") )){
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
								System.out.println(iPulseSecureSiteUrl+" = "+iPulseSecureSiteUrl);			
								siteUrl=iPulseSecureSiteUrl;
							}
						} else {
							siteUrl = CONFIG.getProperty("SecuretestSiteName");
						}
					}	
					fnsApps_Report_Logs("Application credentials are URL[ "+siteUrl+" ], UserName[ "+userName+" ] & Password[ "+password+" ]");
					driver.get(siteUrl);
					//below line related to SSO Switch User Implementation
					fnsComman_BrowserLogin_ElementWait(OR_CLNT.getProperty("SSO_UserName"), CONFIG.getProperty("Element_MAX_WaitTime"));
					//Condition has been added for SSO Switch User Implementation
					if(PortalNamne.toLowerCase().trim().equalsIgnoreCase("SSO_SwitchUser")){
						WithOut_OR_fnGet_ObjectX(OR_CLNT.getProperty("SSO_UserName")).sendKeys(userName);
						Thread.sleep(5000);
						WithOut_OR_fnGet_ObjectX(OR_CLNT.getProperty("SSO_NexButton")).click();
						Thread.sleep(5000);
						Runtime.getRuntime().exec("D:\\TEST_full_scripts_2020\\i-Pulse\\AutoIt_scripts\\sso_testscriptuser.exe");
						Thread.sleep(10000);
					}
					/*fnsComman_BrowserLogin_ElementWait(OR_Navi.getProperty("UserName"), CONFIG.getProperty("Element_MAX_WaitTime"));
					if( ((PortalNamne.toLowerCase().trim()).equalsIgnoreCase("secureipulse")) || ( ((PortalNamne.toLowerCase().trim()).equalsIgnoreCase("ipulse_newnsfonline")) && ((siteUrl.toLowerCase().trim()).contains("securelogin")))){
						fngGetORObjectX___NOR(OR_Navi.getProperty("LoginAs")).clear();
						fngGetORObjectX___NOR(OR_Navi.getProperty("LoginAs")).sendKeys("");
						fngGetORObjectX___NOR(OR_Navi.getProperty("LoginAs")).sendKeys(LoginAs);
					}
					fngGetORObjectX___NOR(OR_Navi.getProperty("UserName")).clear();
					fngGetORObjectX___NOR(OR_Navi.getProperty("UserName")).sendKeys("");
					fngGetORObjectX___NOR(OR_Navi.getProperty("UserName")).sendKeys(userName);
					fngGetORObjectX___NOR(OR_Navi.getProperty("password")).sendKeys("");
					fngGetORObjectX___NOR(OR_Navi.getProperty("password")).sendKeys(password);
					fngGetORObjectX___NOR(OR_Navi.getProperty("Login")).click();*/
					Thread.sleep(10000);
					/**
					 * Handling acknowledge Alert, Just coming after login.
					 */
					WithOut_OR_fnGet_ObjectX(OR_CLNT.getProperty("xpathForAck")).click();
					Thread.sleep(1000);
					//Function Added to SSO Switch User Implementation
					//switchUser(LoginAs);
					fngLoading_wait();
					for(int i=0; i<=Long.parseLong(CONFIG.getProperty("Element_MAX_WaitTime")); i++){
						if(i==Long.parseLong(CONFIG.getProperty("Element_MAX_WaitTime"))){
							throw new Exception("After Login, Home Page is not getting load after <"+Long.parseLong(CONFIG.getProperty("Element_MAX_WaitTime"))+">seconds wait.");
						}else{
							Thread.sleep(1000);
						}
						driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
						if(driver.findElements(By.xpath(OR_Navi.getProperty("Login_errorMessage"))).size()>0){
							String ErrorMsg = fngGetORObjectX___NOR(OR_Navi.getProperty("Login_errorMessage")).getText();
							throw new Exception("I-pulse Login Failed, Application Error <"+ErrorMsg+">");
						}else{
							fnsComman_BrowserLogin_ElementWait(OR_Navi.getProperty("logOutBtn"), CONFIG.getProperty("Element_MAX_WaitTime"));
							break;
						}
					}
				}catch (Throwable t) {
					throw new Exception(Throwables.getStackTraceAsString(t));
				}finally {
					driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);
				}
		}
		public static void fnsComman_BrowserLogin_ElementWait(String XpathKeyWithoutOR, String WaitTime) throws Exception {
			try{
				WebDriverWait elementwaitvar1 = new WebDriverWait(driver,Long.parseLong(WaitTime));
				elementwaitvar1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XpathKeyWithoutOR))).isEnabled();
			}catch(NoSuchWindowException W){
				throw new Exception(W.getMessage());			
			}catch(Throwable e){
				throw new Exception("FAILED == Element is not Visible having Xpath >>"+XpathKeyWithoutOR+" , Getting Exception >> "+Throwables.getStackTraceAsString(e));						
			}
		}
		// return the test data from a test in a 2 dim array
		public static Object[][] getExcelData_for_DataProviderTest(Xls_Reader xls , String testCaseName){
			//Integer rows = null;
			//Integer cols = null;
			Integer rows = 0;
			Integer cols = 0;
			// if the sheet is not present
			if(! xls.isSheetExist(testCaseName)){
				xls=null;
				return new Object[1][0];
			}
			for(int rowNum=0;rowNum<=rowNum+1;rowNum++){
				String CellText= xls.getCellData(testCaseName, 1, rowNum+1);
				if(CellText.equalsIgnoreCase("")){
					rows = rowNum;
					break;
				}
			}
			cols=xls.getColumnCount(testCaseName);
			Object[][] data =new Object[rows-1][cols-5];
			for(int rowNum=2;rowNum<=rows;rowNum++){
				for(int colNum=0;colNum<cols-5;colNum++){
					System.out.print(xls.getCellData(testCaseName, colNum, rowNum) + " -- ");
					data[rowNum-2][colNum] = xls.getCellData(testCaseName, colNum, rowNum);
				}
			}
			return data;
		}	

		
		
		
		//######################################################################################################
		
		
		/***** check error in application page ***/
		public void fngCheckErrMsgTest(String errCustomMsg) throws Throwable {
			if (fngCheckElementPresenceImmediately("ErrorMessage")) {
				fngMouseHover_OR_EvenMultipleObjectsHavingSameXpath_usingSpecialForErrorClassOnly("ErrorMessage");
				String errmsg = fngGetText_OR_EvenMultipleObjectsHavingSameXpath_specialForExtractingErrorMsgTextOnlyEvenIfNotVisible("ErrorMessage");
				int errorLength = errmsg.length();
				if (errorLength > 1) {
					fnsApps_Report_Logs(errCustomMsg + " i.e --" + errmsg);
					throw new Exception(errCustomMsg + " i.e --" + errmsg);
				}
			}
		}
		/***** check error in application page ***/
		public void fngCheckErrMsgForInvoice(String errCustomMsg) throws Throwable {
			if (fngCheckElementPresenceImmediately("ErrorMessage")) {
				fngMouseHover_OR_EvenMultipleObjectsHavingSameXpath_usingSpecialForErrorClassOnly("ErrorMessage");
				String errmsg = fngGetText_OR_EvenMultipleObjectsHavingSameXpath_specialForExtractingErrorMsgTextOnlyEvenIfNotVisible("ErrorMessage");
				int errorLength = errmsg.length();
				if (errorLength > 1) {
					if(errmsg.contains("We are sorry")){
						fnsApps_Report_Logs(errCustomMsg + " i.e --" + errmsg);
						throw new Exception(errCustomMsg + " i.e --" + errmsg);
					}else{
						fnsApps_Report_Logs(errCustomMsg + " i.e --" + errmsg);
					}
				}
			}
		}
		/***********
		 * To check element is present or not immmediately
		 * 
		 * @throws Throwable
		 ************/
		@SuppressWarnings("finally")
		public boolean fngCheckElementPresenceImmediately(String XpathKey) {
			boolean result = false;
			try {
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.MILLISECONDS);   
				if (fngGetORObject_list(XpathKey, 0, 1).size() > 0) {
					result = true;
				} else {
					result = false;
				}
			}catch (Throwable t){
				fnsApps_Report_Logs("FAILED == Element is getting Null >> "+Throwables.getStackTraceAsString(t));
				result = false;
			}
			finally {
				driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);
				return result;
			}
		}
		
		public void fngMouseHover_OR_EvenMultipleObjectsHavingSameXpath_usingSpecialForErrorClassOnly(String XpathKey) throws Exception {
			WebElement requiredObj = null;
			try {
				for (int wait = 0; wait < 2; wait++) {
					if (fngGetORObject_list(XpathKey, 2,0).size() > 0) {
						List<WebElement> objList = fngGetORObject_list(XpathKey, 1,0);
						for (int i = 0; i < objList.size(); i++) {
							if ((objList.get(i).isDisplayed()) & (!((fngReturnText_notCallItDirectly(objList.get(i)).equalsIgnoreCase(""))))) {
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
						// Exception("Element is not Visible having Xpath  [ "
						// + XpathKey + " ]");
					}
				}

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
		public String fngGetText_OR_EvenMultipleObjectsHavingSameXpath_specialForExtractingErrorMsgTextOnlyEvenIfNotVisible(String XpathKey) throws Throwable {
			WebElement requiredObj = null;
			String returnText = "";
			if (fngGetORObject_list(XpathKey, 0, 1).size() > 0) {
				List<WebElement> objList = fngGetORObject_list(XpathKey, 0, 1);
				String currentText = "";
				int somecounter=0;
				for (int i = 0; i < objList.size(); i++) {
					try{
					currentText = fngReturnText_notCallItDirectly(objList.get(i));
					}catch(org.openqa.selenium.StaleElementReferenceException s){ 
						//nothing to do
						if (somecounter<2) {
							somecounter++;
							Thread.sleep(300);
							objList = fngGetORObject_list(XpathKey, 0, 100);
							i=-1;
						}
					}
					if (!(currentText.equalsIgnoreCase(""))) {
						requiredObj = objList.get(i);
						if (!(returnText.contains(currentText))) {
							returnText = returnText + "  " + currentText;
						}
						}
				}
			} else {
				/******
				 * This below throw line can be commented only in function using for
				 * error specially i.e.
				 * fnpGetText_OR_EvenMultipleObjectsHavingSameXpath_specialForExtractingErrorMsgTextOnlyEvenIfNotVisible
				 *****/
			}
			return returnText.trim();
		}
		/*public static void switchUser(String switchTo) throws Throwable{
			fngGet_Element_Enabled("Menu_Ajax_Link");
			WebElement Menu_Element = fnsGet_OR_CLNT_ObjectX("Menu_Ajax_Link");
									
			Actions action1 = new Actions(driver);
			action1.moveToElement(Menu_Element).build().perform();
			Thread.sleep(500);		
				
			Actions action2 = new Actions(driver);
			fnsGet_Element_Enabled("SwitchUser_Ajax_Link");
			WebElement switchUserLinkElement = fnsGet_OR_CLNT_ObjectX("SwitchUser_Ajax_Link");
			action2.moveToElement(switchUserLinkElement).click().build().perform();

			fnsApps_Report_Logs("PASSED == Successfully Click on 'Switch User' Ajax Link.");
			fngWait_and_Type("SwitchUser_inputTxt",switchTo);
			Thread.sleep(4000);
			WebElement GoButtonElement = fnsGet_OR_CLNT_ObjectX("GoButton_ForSwitchUser");
			action2.moveToElement(GoButtonElement).click().build().perform();
			//fnsLoading_Progressingwait(1);
		}*/
}




