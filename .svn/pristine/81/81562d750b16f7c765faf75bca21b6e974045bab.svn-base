package nsf.ecap.Listings_Suite;

import static org.testng.Assert.assertTrue;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Connection;
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

import javax.imageio.ImageIO;
import javax.naming.NotContextException;

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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.google.common.base.Throwables;
/*import com.thoughtworks.selenium.Wait;*/

import nsf.ecap.base.TestBase;
import nsf.ecap.util.ErrorUtil;
import nsf.ecap.util.TestUtil;
import nsf.ecap.util.Xls_Reader;

public class TestSuiteBase_Listings extends TestBase  {

	public String classNameText = null;
	public long TC_Step = 0;
	public long Log_Step = 0;
	public ArrayList<String> Loading_Xpath_Array = new ArrayList<String>() ;
	public boolean LoadingImageFlag = true;
	public Integer TimeOut = 0;
	public String PageSourceText = null;
	public Integer Actual_Loading_Time;
	
	public String Fetched_Text = null;
	public WebElement Element;
	public String ErrorMsgText = "";
	public String SuccessMsgText = "";
	public Integer String_To_Integer = null;
	
	public boolean isTestCasePass = true;
	public boolean IsBrowserPresentAlready = false;
	boolean fail = false;
	public boolean skip = true;
	public String SearchResult_Code_link_Xpath;
	public String BrowserName="IE";
	public boolean BrowserDriver = false;
	public String TextAfterRemvoalSpeChar;
	public String runmodes[] = null;
	public static boolean ScreenShotFlagWithOR_Listings = false;
	public boolean ApplicationVersion_Flag = true;
	
	
	public Integer GetColumnNo = null; ;
	public Integer After_DataAdded = null; ;

	public String CurrentMonth = null;
	public String CalendarCompare_MonthYear = null;
	public String CurrentDay = null;
	public String GetCalendar_DateTitle = null;
	
	
	public String View_Filter_Input_Xpath = null;
	
	public String ToolTip_Text = "";
	public String Text_on_Graphs = null;
	
	public String Login_UserName = null;
	public String Login_Password = null;
	public String Login_Application_Name = null;
	public String Loading_Image_Xpath = null;
	
	public boolean Verify_Login_Authentication_Done = false;
	public String RunningClassName = null;
	public boolean BS_03_Runmode = false;
	public String Running_Class_BS_Description = null;
	public String User_COCL_Account = null;
	public String Listings_Window_Handle = null;
	public String iPulse_Original_WindowHandle = null;
	public String User_DefaultCustomerAccount_COCL = null;
	
	
//######################################################### Common Functions #######################################################################

	public void fnsDB_Fetch_or_Update_LoginTC_Query(String Update_Query) throws Throwable {
		Connection connection = null;
		try{
			fnsApps_Report_Logs("=========================================================================================================================================");			
			Class.forName("oracle.jdbc.driver.OracleDriver");	    
			
			connection = fnpGetDBConnection(); 
			 
			Statement stmt= connection.createStatement();
			
			stmt.executeUpdate(Update_Query);
			connection.commit();
			fnsApps_Report_Logs("**** Update Query Executed Successfully. >> "+Update_Query); 
			
			connection.close();		   
		}catch (SQLException e) {
			fnsApps_Report_Logs("Data base Error : "+ Throwables.getStackTraceAsString(e).trim());
			throw new Exception ("Data base Error : "+ Throwables.getStackTraceAsString(e).trim());
		}finally {
			if( !(connection==null) ){
				connection.close();
			}
		}
	}	

	public void fnsDB_Fetch_or_Update_LoginTC_Query(String Update_Query, Connection DBconnection) throws Throwable {
		Connection connection = null;
		try{
			fnsApps_Report_Logs("=========================================================================================================================================");			
			Class.forName("oracle.jdbc.driver.OracleDriver");	    
			
			connection = DBconnection; 
			 
			Statement stmt= connection.createStatement();
			
			stmt.executeUpdate(Update_Query);
			connection.commit();
			fnsApps_Report_Logs("**** Update Query Executed Successfully. >> "+Update_Query); 
			
		//	connection.close();		   
		}catch (SQLException e) {
			fnsApps_Report_Logs("Data base Error : "+ Throwables.getStackTraceAsString(e).trim());
			throw new Exception ("Data base Error : "+ Throwables.getStackTraceAsString(e).trim());
		}/*finally {
			if( !(connection==null) ){
				connection.close();
			}
		}*/
	}		
	
//Function to Take Screen Shot.
public void fnsTake_Screen_Shot(String message) throws Exception {
	String MessageAfterFormat=fnsRemoveFormatting_for_FileName(message);
		try{
			String Suite_Foler_Name = "screenshots_Listings";
			String File_Name = MessageAfterFormat+"_"+fnsScreenShot_Date_format()+".PNG";
			FileUtils.forceMkdir(new File((System.getProperty("user.dir") +  screenshots_path+ "//"+Suite_Foler_Name+"//"+currentScriptCode +"//")));
			File image = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);			
			FileUtils.copyFile(image, new File((System.getProperty("user.dir") +  screenshots_path+ "//"+Suite_Foler_Name+"//"+currentScriptCode +"//"+File_Name)));
			
			
		  /* FileUtils.forceMkdir(new File((System.getProperty("user.dir") +  screenshots_path+ "//screenshots_Listings//"+currentScriptCode +"//")));
		   Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		   Rectangle screenRectangle = new Rectangle(screenSize);
		   Robot robot = new Robot();
		   BufferedImage image = robot.createScreenCapture(screenRectangle);
		   ImageIO.write(image, "png", new File((System.getProperty("user.dir") +  screenshots_path+ "//screenshots_Listings//"+currentScriptCode +"//"+MessageAfterFormat+"_"+fnsScreenShot_Date_format()+".PNG")));*/
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



//Function of loading image appear on the screen (Block UI). 
public void fnsLoading_Progressing_wait(int waitcount) throws Throwable{
	try{
		LoadingImageFlag = false;
		Actual_Loading_Time = 1;
		String Loading_Classes_From_OR = OR_Listings.getProperty("Listings_LoadingProgressing").trim();
		Integer PageLoadWait = TestSuiteBase_MonitorPlan.fnsLoading_PageLoad();		
		for(int wait=PageLoadWait; wait<=((Listings_Element_Max_Wait_Time)); wait++){
			if(!LoadingImageFlag){
			//	Thread.sleep(500);
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
			
			

		/*	boolean ContentPopupDisplay = false;
			PageSourceText = driver.getPageSource().toLowerCase();
			if(PageSourceText.contains("alert-dismissible")){
				try{
					if(driver.findElement(By.xpath(OR_Listings.getProperty("Listings_Error_div"))).isDisplayed()){
						ContentPopupDisplay = true;
					}
				}catch( Throwable n){  
					System.out.println("TOP - - modal-content - error - Page source");
				}
				
				if (ContentPopupDisplay) {
					WebElement Element = fnsGet_OR_Listings_ObjectX("Listings_Error_div");
					Actions act = new Actions(driver);
					act.moveToElement(Element).build().perform();
					ErrorMsgText = fnsGet_OR_Listings_ObjectX("Listings_Error_div").getText().trim();
					throw new IllegalArgumentException();
				}
			}
		*/
			
			
			try{
				if(driver.findElement(By.xpath(Loading_Image_Xpath)).isDisplayed()){
					Thread.sleep(1000);
					LoadingImageFlag = true;
				}
			}catch( Throwable n){  Thread.sleep(250); }
			
			
			
			try{	
				if(  !(driver.findElement(By.xpath(Loading_Image_Xpath)).isDisplayed()) && wait > waitcount &&  LoadingImageFlag == true ){
					Loading_Xpath_Array.add(Loading_Image_Xpath+"&&");
					fnsApps_Report_Logs("Progressing Loading image is displayed for < "+(Actual_Loading_Time)+" > seconds.");
					break;	
				}else if(  !(driver.findElement(By.xpath(Loading_Image_Xpath)).isDisplayed()) && wait > waitcount &&  LoadingImageFlag == false ){
					fnsApps_Report_Logs("*********  Pause/Wait is for < "+Actual_Loading_Time+" > seconds.  *****  "); //To increase performance, Pause/Wait time can set to 1 sec
					break;
				}else if(  wait < waitcount &&  LoadingImageFlag == false ){
					Thread.sleep(500);
				}
			}catch(Throwable n){
				if(  wait > waitcount ){
					if( LoadingImageFlag == true ){
						Loading_Xpath_Array.add(Loading_Image_Xpath+"&&");
						fnsApps_Report_Logs("Progressing Loading image is displayed for < "+(Actual_Loading_Time)+" > seconds.");
						break;	
					}else if( LoadingImageFlag == false ){
						fnsApps_Report_Logs("*********  Pause/Wait is for < "+Actual_Loading_Time+" > seconds.  *****  "); //To increase performance, Pause/Wait time can set to 1 sec
						break;
					}
				}else if(  wait < waitcount &&  LoadingImageFlag == false ){
					Thread.sleep(500);
				}
			}
						
			if(wait==(Listings_Element_Max_Wait_Time)){
				throw new InterruptedException("Loading Issue : After < "+(Listings_Element_Max_Wait_Time)+" > Seconds wait page is not getting Load, please refer screenshot >> PageLoadFail" + fnsScreenShot_Date_format() +"*****  < "+Loading_Image_Xpath+" >");
			}
			Actual_Loading_Time++;
		}
				
		/*PageSourceText = driver.getPageSource().toLowerCase();
				
		if(PageSourceText.contains("modal-content")){	
			boolean ContentPopupDisplay = false;
			try{
				if(driver.findElement(By.xpath(OR_Listings.getProperty("Listings_Content_Model_Popup"))).isDisplayed()){
					ContentPopupDisplay = true;
				}
			}catch( Throwable n){  
				System.out.println("DOWN - - modal-content - error - Page source");
			}
			if (ContentPopupDisplay) {
				WebElement Element = fnsGet_OR_Listings_ObjectX("Listings_Content_Model_Popup");
				Actions act = new Actions(driver);
				act.moveToElement(Element).build().perform();
				ErrorMsgText = fnsGet_OR_Listings_ObjectX("Listings_Content_Model_Popup").getText().trim();
				throw new IllegalArgumentException();
			}
		}*/
	
			
	}catch(IllegalArgumentException i){
		//isTestCasePass = false;
		TestSuiteBase_MonitorPlan.fnsTake_Screen_Shot_Without_OR("UnExpectedError");
		fnsApps_Report_Logs("Getting Un-Expected Application Error <"+ErrorMsgText+">, please refer screenshot >>UnExpectedError" + fnsScreenShot_Date_format()+"*****  < "+Loading_Image_Xpath+" >"+"  Exception ("+Throwables.getStackTraceAsString(i));
		throw new Exception("Getting Un-Expected Application Error <"+ErrorMsgText+">, please refer screenshot >> UnExpectedError" + fnsScreenShot_Date_format()+"*****  < "+Loading_Image_Xpath+" >"+"  Exception ("+Throwables.getStackTraceAsString(i));	
	}catch(NoSuchElementException n){
		fnsApps_Report_Logs("***** Progressing Loading image displayed for < "+Actual_Loading_Time+" > seconds. **************** NS");
		try{
			fnsApps_Report_Logs("***** Progressing Loading image displayed for < "+Actual_Loading_Time+" > seconds. **************** NS");
			fnsLoading_Progressing_wait(waitcount);			
		}catch(Throwable tt){
			isTestCasePass = false;
			TestSuiteBase_MonitorPlan.fnsTake_Screen_Shot_Without_OR("PageLoadFail_NS");
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(tt));
			throw new Exception(Throwables.getStackTraceAsString(tt));		
		}
	}catch(StaleElementReferenceException n){
		fnsApps_Report_Logs("***** Progressing Loading image displayed for < "+Actual_Loading_Time+" > seconds. **************** Stale");
	} catch(NoSuchWindowException W){
		throw new Exception(W.getMessage());
	}catch(InterruptedException ie){
		System.out.println("Interrupted-----Exception");
		TestSuiteBase_MonitorPlan.fnsTake_Screen_Shot_Without_OR("PageLoadFail");
		fnsApps_Report_Logs(Throwables.getStackTraceAsString(ie));
		throw new Exception(Throwables.getStackTraceAsString(ie));		
	}catch(Throwable tt){
		//isTestCasePass = false;
		TestSuiteBase_MonitorPlan.fnsTake_Screen_Shot_Without_OR("PageLoadFail");
		fnsApps_Report_Logs(Throwables.getStackTraceAsString(tt));
		throw new Exception(Throwables.getStackTraceAsString(tt));		
	}
}




public void fnsVerify_PopupOpened_byTitle(String ClickedButtonName) throws Throwable{
	try{
		boolean Popup_Opened = false;
		for(int i=0; i<=120; i++){
			String title = "";
			try{
				title = fnsGet_Field_TEXT("Listings_Popup_Title").toLowerCase().trim();
			}catch(Throwable t){
				//nothing to do
			}
			if(title.length()>8){
				Popup_Opened = true;
				fnsApps_Report_Logs("PASSED == Opup is opened.");
				break;
			}else{
				Thread.sleep(1000);
			}
		}
		
		if(Popup_Opened == false){
			fnsTake_Screen_Shot("Popup_Open_Fail");
			throw new Exception ("FAILED == After clicking on  , the Popup is not getting open, Please refer the screenshot >> Popup_Open_Fail"+fnsScreenShot_Date_format());
		}	
	}catch(Throwable t){
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
	
	
	
	
}











public void fnsLoading_UnwantedPopupError() throws Throwable{
	try{
		boolean ContentPopupDisplay = false;
		PageSourceText = driver.getPageSource().toLowerCase();
		if(PageSourceText.contains("modal-content")){
			try{
				if(driver.findElement(By.xpath(OR_Listings.getProperty("Listings_Content_Model_Popup"))).isDisplayed()){
					ContentPopupDisplay = true;
				}
			}catch( Throwable n){  
				//nothing to do
			}
			
			if (ContentPopupDisplay) {
				WebElement Element = fnsGet_OR_Listings_ObjectX("Listings_Content_Model_Popup");
				Actions act = new Actions(driver);
				act.moveToElement(Element).build().perform();
				ErrorMsgText = fnsGet_OR_Listings_ObjectX("Listings_Content_Model_Popup").getText().trim();
				fnsTake_Screen_Shot("UnExpectedError");
				fnsApps_Report_Logs("Getting Un-Expected Application Error <"+ErrorMsgText+">, please refer screenshot >>UnExpectedError" + fnsScreenShot_Date_format()+"*****  < "+Loading_Image_Xpath+" >");
				throw new Exception("Getting Un-Expected Application Error <"+ErrorMsgText+">, please refer screenshot >> UnExpectedError" + fnsScreenShot_Date_format()+"*****  < "+Loading_Image_Xpath+" >");	
			}
		}
	}catch(Throwable t){
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
}









public String fnsRemoveFormatting(String s) {

	s = s.replace("}", " ");
	s = s.replace("(", " ");
	s = s.replace(")", " ");
	s = s.replace("{", " ");
	s = s.replaceAll("\\s+"," ");
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
public String fnsTime_Return_DemandedFormatCalendar(String Date_format, Integer Year, Integer Month, Integer Day, Integer Hour, Integer Minute) {
	Calendar cal = Calendar.getInstance();
	DateFormat dateFormat = new SimpleDateFormat(Date_format);
	cal.add(Calendar.YEAR, Year);
	cal.add(Calendar.MONTH, Month);
	cal.add(Calendar.DAY_OF_MONTH, Day);
	cal.add(Calendar.HOUR_OF_DAY, Hour);
	cal.add(Calendar.MINUTE, Minute);
	String DemandDate=dateFormat.format(cal.getTime()).trim();
	return DemandDate;
}

//Application date format 
public String fnsTime_CheckList_Requried_Date_format(String Date_format, Integer Day_Increment, Integer Year) {
	Calendar cal = Calendar.getInstance();
	DateFormat dateFormat = new SimpleDateFormat(Date_format);
	cal.add(Calendar.YEAR, Year);
	cal.add(Calendar.DAY_OF_MONTH, Day_Increment);
	String DemandDate=dateFormat.format(cal.getTime()).trim();
	return DemandDate;
}

/*//Application date format 
public String fnsTime_Requried_Date_format(String Date_format, Integer Year, Integer Month, Integer Day, Integer Hour, Integer Minute) {
	Calendar cal = Calendar.getInstance();
	DateFormat dateFormat = new SimpleDateFormat(Date_format);
	cal.add(Calendar.YEAR, Year);
	cal.add(Calendar.MONTH, Month);
	cal.add(Calendar.DAY_OF_MONTH, Day);
	cal.add(Calendar.HOUR_OF_DAY, Hour);
	cal.add(Calendar.MINUTE, Minute);
	String DemandDate=dateFormat.format(cal.getTime()).trim();
	return DemandDate;
}*/




//Function for Screen date format 
public String fnsTime_Return_CurrentTime() {
	DateFormat dateFormat = new SimpleDateFormat("HHmmss");
	Date date = new Date();
	return (dateFormat.format(date));
}

//Function For Application Log 
public long fnsApps_Report_Logs(String LogMessage) {
		
	LogMessage = LogMessage.replaceAll("°", "");
	LogMessage = LogMessage.replaceAll("©", "");

	if( (LogMessage.toLowerCase().contains("failed")) || (LogMessage.toLowerCase().contains("java")) || (LogMessage.toLowerCase().contains("webdriver")) || (LogMessage.toLowerCase().contains("assert")) ){ 
		Reporter.log(" |");
		Reporter.log(" |");
		Reporter.log(" | Script has been failed, after performing the Step <"+(TC_Step-1)+">, Please refer the below error....");
		Reporter.log(" |");
		Reporter.log(fnsLOGS_Date_format() + " | " + LogMessage);
	}else if( ( (LogMessage.toLowerCase().contains("success")) || (LogMessage.toLowerCase().contains("load"))  || (LogMessage.toLowerCase().contains("click")) || (LogMessage.toLowerCase().contains("type")) || (LogMessage.toLowerCase().contains("select")) || (LogMessage.toLowerCase().contains("button")) || (LogMessage.toLowerCase().contains("query"))) && !(LogMessage.toLowerCase().contains("move")) || (LogMessage.toLowerCase().contains("url")) || (LogMessage.toLowerCase().contains("automation")) ){ //Steps Log
		if( !((LogMessage.toLowerCase().contains("success")) || (LogMessage.toLowerCase().contains("load")) || (LogMessage.toLowerCase().contains("test case")) ) || (LogMessage.toLowerCase().contains("select")) || (LogMessage.toLowerCase().contains("menu"))  || (LogMessage.toLowerCase().contains("url")) || (LogMessage.toLowerCase().contains("button"))){
			LogMessage = "STEP "+TC_Step+" == "+LogMessage;
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
	return TC_Step;
}


//Get Excel Cell value by column name
public String fnsReturn_ExcelCellValue_ByMatchingColumnName(String SheetName, String ColumnName) throws Throwable{
	try{
		String CellValue = null;
		for(int i=5; i<50; i++){
			for(int j=1; j<16; j++){
				String ExcelCellValue = Listings_Suitexls.getCellData(SheetName, j, i);
				if(ExcelCellValue.equalsIgnoreCase(ColumnName)){
					CellValue = Listings_Suitexls.getCellData(SheetName, j, i+1).trim();
					break;
				}
			}
			if(CellValue!=null){
				break;
			}
		}
		
		if(CellValue == null){
			throw new Exception("FAILED == Column < "+ColumnName+" > entry is Missing/Incorrect into the sheet < "+SheetName+" > of the suite Excel 'Listings'.")	;
		}else if(CellValue.equals("")){
			fnsApps_Report_Logs("@@@@@@@@@@@  Value of Column < "+ColumnName+" > is   'BLANK'   into the sheet < "+SheetName+" >.");
		}
		
		System.out.println(ColumnName+"   =============================    >>  "+CellValue);
		return CellValue;
	}catch(Throwable t){
	//	fnsApps_Report_Logs(t.getMessage());
		throw new Exception(t.getMessage());	}
}
	

//Function to find and return the object using Xpath
public WebElement fnsGet_OR_Listings_ObjectX(String XpathKey) throws Exception{
			
	try {
		for (int waits=0; waits<Listings_Element_Max_Wait_Time; waits++) {
			
			if (driver.findElements(By.xpath(OR_Listings.getProperty(XpathKey))).size() > 0) {break;}
			else{Thread.sleep(500);}
			
		}return driver.findElement(By.xpath(OR_Listings.getProperty(XpathKey))); 
		
	}catch(StaleElementReferenceException e){
		WebDriverWait stalewaitvar = new WebDriverWait(driver, 10);
		stalewaitvar.until(ExpectedConditions.presenceOfElementLocated(By.xpath(OR_Listings.getProperty(XpathKey))));
		WebElement webelemnt=driver.findElement(By.xpath(OR_Listings.getProperty(XpathKey)));
		stalewaitvar.until(ExpectedConditions.stalenessOf(webelemnt));
		return driver.findElement(By.xpath(OR_Listings.getProperty(XpathKey)));}
	catch(NoSuchWindowException W){
		//isTestCasePass = false;
		throw new Exception(W.getMessage());			}
	catch(NoSuchElementException e){
		//isTestCasePass = false;
		fnsTake_Screen_Shot("NoSuchElementException" + XpathKey);
		fnsApps_Report_Logs("FAILED == Element is not found >> "+XpathKey + " ] , plz see screenshot [NoSuchElementException" + XpathKey + fnsScreenShot_Date_format() + " ]");
		throw new Exception("FAILED == Element is not found >> "+XpathKey + " ] , plz see screenshot [NoSuchElementException" + XpathKey + fnsScreenShot_Date_format() + " ]");}
	catch(TimeoutException e){
		//isTestCasePass = false;
		fnsTake_Screen_Shot("TimeOut" + XpathKey);
		fnsApps_Report_Logs("FAILED == Element is not Present, getting TimeOut >> "+XpathKey + " ] , plz see screenshot [TimeOut" + XpathKey + fnsScreenShot_Date_format() + " ]");
		throw new Exception("FAILED == Element is not Present, getting TimeOut >> "+XpathKey + " ] , plz see screenshot [TimeOut" + XpathKey + fnsScreenShot_Date_format() + " ]");}
	catch(Throwable e){
		//isTestCasePass = false;
		fnsTake_Screen_Shot("NotPresent" + XpathKey);
		fnsApps_Report_Logs("FAILED == Element is not found >> "+XpathKey + " ] , plz see screenshot [NotPresent" + XpathKey + fnsScreenShot_Date_format() + " ]"+". Getting Exception  >> "+e.getMessage());
		throw new Exception("FAILED == Element is not found >> "+XpathKey + " ] , plz see screenshot [NotPresent" + XpathKey + fnsScreenShot_Date_format() + " ]");}
}

//Function to click through JS
public void fnsWait_and_Click_Through_JS(String Xpath_Without_OR) throws Exception{
	try{
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse = (JavascriptExecutor) driver;
		WebElement Webelements = driver.findElement(By.xpath(Xpath_Without_OR));
		jse.executeScript("arguments[0].click();", Webelements);
	
		fnsApps_Report_Logs("PASSED == Click done on Element throught JS, having Xpath >> "+Xpath_Without_OR);
	}catch(Throwable t){
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
}


//Function to click 
public void fnsWait_and_Click(String XpathKey) throws Exception {
	try{
		try{
			fnsGet_OR_Listings_ObjectX(XpathKey).click();
			fnsApps_Report_Logs("PASSED == Click done on Element having Xpath >> "+XpathKey);
		}catch(Throwable tt){
			Thread.sleep(3000);
			fnsGet_OR_Listings_ObjectX(XpathKey).click();
			fnsApps_Report_Logs("(((((( 2nd Attampt ))))))---PASSED == Click done on Element having Xpath >> "+XpathKey);
		}
	}catch(NoSuchWindowException W){
		//isTestCasePass = false;
		throw new Exception(W.getMessage());			}
	catch(Throwable e){
		//isTestCasePass = false;
		fnsTake_Screen_Shot("UnableToClick_" + XpathKey);
		fnsApps_Report_Logs("FAILED == Unable To Click on Element having Xpath >> "+XpathKey+", plz see screenshot [ UnableToClick_" + XpathKey + fnsScreenShot_Date_format() + " ]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(e));
		throw new Exception("FAILED == Unable To Click on element [ " + XpathKey + " ] , plz see screenshot [ UnableToClick_" + XpathKey + fnsScreenShot_Date_format() + " ]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(e));}
}

	
//Function to click 
public void fnsWait_Click_on_First_Visible_Element_if_More_than_One_Coming(String Element_Name, String WithoutOR_XpathKey) throws Exception {
	int Max_Click = 1;
	boolean Click_Done = false;
	while(true){
		Integer Element_Size = driver.findElements(By.xpath(WithoutOR_XpathKey)).size();
		Click_Done = false;
		try{
			if(Element_Size>0){
				for(int i=1; i<=Element_Size; i++){
					String Element_Xpath = "("+WithoutOR_XpathKey+")["+i+"]"; 
					WebElement Element = driver.findElement(By.xpath(Element_Xpath));
					if(Element.isDisplayed()){
						Element.click();
						Click_Done = true;
						fnsApps_Report_Logs("PASSED == Click done on Element_"+i+" >> "+Element_Name);
						break;
					}
					if( i==Element_Size && Click_Done==false ){
						throw new Exception("FAILED == Clicking on element_"+Element_Size+" with Name >> "+Element_Name+" is getting fail (Not displayed), please see screenshot >> Click_FAIL_" + Element_Name + fnsScreenShot_Date_format() );
					}
				}
				
			}else{
				throw new Exception("FAILED == There is no such element"+Element_Size+" with Name >> "+Element_Name+", please see screenshot >> Click_FAIL_" + Element_Name + fnsScreenShot_Date_format() );
			}
			
		}catch(NoSuchWindowException W){
			throw new Exception(W.getMessage());			
		}catch(StaleElementReferenceException e){
			if(Max_Click==5){
				fnsTake_Screen_Shot("Click_FAIL_");
				fnsApps_Report_Logs("After 5 try the click is still getting fail on element ("+Element_Name+"), as the element is not present into the DOM, please see screenshot >> Click_FAIL_" + Element_Name + fnsScreenShot_Date_format() +Throwables.getStackTraceAsString(e));
				throw new Exception("After 5 try the click is still getting fail on element ("+Element_Name+"), as the element is not present into the DOM, please see screenshot >> Click_FAIL_" + Element_Name + fnsScreenShot_Date_format() +Throwables.getStackTraceAsString(e));
			}else{
				Thread.sleep(5000);
			}
		}catch(Throwable t){
			fnsTake_Screen_Shot("Click_FAIL_");
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		if(Click_Done){
			break;
		}
		Max_Click++;
	}
	try{
		assertTrue (Click_Done);
	}catch(Throwable t){
		fnsTake_Screen_Shot("Element_Not_Display_");
		throw new Exception("FAILED, '"+Element_Name+"' is not display, please see screenshot >> Element_Not_Display_" + Element_Name + fnsScreenShot_Date_format() +Throwables.getStackTraceAsString(t));
	
	}
}


public String fnsRetun_Xpath_of_First_Visible_Element_if_More_than_One_Coming(String WithoutOR_XpathKey) throws Exception {
	boolean Element_Visible =false;
	String Element_Xpath = "";
	Integer Max_Click = 1;
	while(true){
		Integer Element_Size = driver.findElements(By.xpath(WithoutOR_XpathKey)).size();
		try{
			if(Element_Size>0){
				for(int i=1; i<=Element_Size; i++){
					Element_Xpath = "("+WithoutOR_XpathKey+")["+i+"]"; 
					WebElement Element = driver.findElement(By.xpath(Element_Xpath));
					if(Element.isDisplayed()){
						Element_Visible = true;
						break;
					}
					if( i==Element_Size && Element_Visible==false ){
						throw new Exception("FAILED == Xpath ("+WithoutOR_XpathKey+") is not displayed, please see screenshot >> Xpath_FAIL_" +  fnsScreenShot_Date_format() );
					}
				}
				
			}else{
				throw new Exception("FAILED == There is no such xpath ("+WithoutOR_XpathKey+"), please see screenshot >> Xpath_FAIL_" +  fnsScreenShot_Date_format() );
			}
			
		}catch(NoSuchWindowException W){
			throw new Exception(W.getMessage());			
		}catch(Throwable e){
			if(Max_Click==10){
				fnsTake_Screen_Shot("Click_FAIL_");
				throw new Exception(Throwables.getStackTraceAsString(e));
			}else{
				Thread.sleep(1000);
			}
		}
		if(Element_Visible){
			break;
		}
		Max_Click++;
	}
	return Element_Xpath;
}
//Function to type
public void fnsWait_and_Type(String XpathKey, String value) throws Exception {
	try{
		try{
			fnsGet_OR_Listings_ObjectX(XpathKey).sendKeys("");
			fnsGet_OR_Listings_ObjectX(XpathKey).sendKeys(value);
			fnsApps_Report_Logs("PASSED == Type Value [ "+value+" ] done on Element having Xpath  >> "+XpathKey);
		}catch(Throwable tt){
			Thread.sleep(3000);
			fnsGet_OR_Listings_ObjectX(XpathKey).sendKeys("");
			fnsGet_OR_Listings_ObjectX(XpathKey).sendKeys(value);
			fnsApps_Report_Logs("(((((( 2nd Attampt ))))))---PASSED == Type Value [ "+value+" ] done on Element having Xpath  >> "+XpathKey);
		}	
	}catch(NoSuchWindowException W){
		//isTestCasePass = false;
		throw new Exception(W.getMessage());			}
	catch(Throwable e){
		//isTestCasePass = false;
		fnsTake_Screen_Shot("UnableToType_" + XpathKey);
		fnsApps_Report_Logs("FAILED == Unable To Type on Element having Xpath  >> "+XpathKey+", plz see screenshot [ UnableToType_" + XpathKey + fnsScreenShot_Date_format() + " ]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(e));
		throw new Exception("FAILED == Unable To Type on element [ " + XpathKey + " ] , plz see screenshot [ UnableToType_" + XpathKey + fnsScreenShot_Date_format() + " ]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(e));	}
}


//Type into the input field by passing label name
public void fnsWait_Click_or_Type_By_LabelName(String NextAboveDiv_form_LableDiv_Xpath, String LabelName, String if_Type_then_ValueFromExcel_else_ClickString_to_Click_button) throws Throwable{
	try{
		boolean Type_done = false;
		boolean Click_done = false;
		
		fnsGet_Element_Enabled(NextAboveDiv_form_LableDiv_Xpath);
		int i=0;
		List<WebElement>  Outer_Div_Objects = fnsGet_OR_Listings_ObjectX(NextAboveDiv_form_LableDiv_Xpath).findElements(By.tagName("div"));
		while( (!Type_done) && (!Click_done) ){
			if(Outer_Div_Objects.size()>1){
				for(WebElement Outer_Div_Elements : Outer_Div_Objects){
					String Application_Label_Text = Outer_Div_Elements.getText().toLowerCase().trim();
					if(Application_Label_Text.equalsIgnoreCase(LabelName.toLowerCase().trim())){
						if(if_Type_then_ValueFromExcel_else_ClickString_to_Click_button.toLowerCase().trim().equals("click")){
							List<WebElement>  button_Objects = Outer_Div_Elements.findElements(By.tagName("button"));
							for(WebElement button_elements : button_Objects){
								button_elements.click();
								Click_done = true;
								fnsApps_Report_Logs("PASSED == Click done on '"+LabelName+"' button.");
								break;
							}
						}else{
							List<WebElement>  Input_Objects = Outer_Div_Elements.findElements(By.tagName("input"));
							for(WebElement Input_elements : Input_Objects){
								Input_elements.clear();
								Input_elements.sendKeys(if_Type_then_ValueFromExcel_else_ClickString_to_Click_button);
								Type_done = true;
								fnsApps_Report_Logs("PASSED == <"+if_Type_then_ValueFromExcel_else_ClickString_to_Click_button+"> Type done on element >> "+LabelName);
								break;
							}
						}
					}else{
						Outer_Div_Objects = Outer_Div_Elements.findElements(By.tagName("div"));
					}
					if( (Type_done==true) || (Click_done==true) ){
						break;
					}
				}
			}
		
			if( (Type_done==false) && i==100){
				fnsTake_Screen_Shot("Type_Fail");
				throw new Exception ("FAILED == Unable to type into the <"+LabelName+"> field, please refer the screen shot >> Type_Fail"+fnsScreenShot_Date_format());
			}
			if( (Click_done==false) && i==100){
				fnsTake_Screen_Shot("Click_Fail");
				throw new Exception ("FAILED == Unable to Click on <"+LabelName+"> button, please refer the screen shot >> Click_Fail"+fnsScreenShot_Date_format());
			}
			i++;
		}	
		
	}catch(Throwable t){
		isTestCasePass = false;
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
}

//Function to Clear Field
public void fnsWait_and_Clear(String XpathKey) throws Exception {
	
	try{
		WebElement Elements = fnsGet_OR_Listings_ObjectX(XpathKey);
		Elements.clear();
		Elements.clear();
		
		//fnsGet_OR_Listings_ObjectX(XpathKey).clear();
		fnsApps_Report_Logs("PASSED == Clear done Element having Xpath  >> "+XpathKey);
	}catch(NoSuchWindowException W){
		//isTestCasePass = false;
		throw new Exception(W.getMessage());			}
	catch(Throwable e){
		//isTestCasePass = false;
		fnsTake_Screen_Shot("UnableToClear_" + XpathKey);
		fnsApps_Report_Logs("FAILED == Unable To performe Clear on Element having Xpath  >> "+XpathKey+", plz see screenshot [ UnableToClear_" + XpathKey + fnsScreenShot_Date_format() + " ]"+". Getting Exception  >> "+e.getMessage());
		throw new Exception("Unable To performe Clear on element [ " + XpathKey + " ] , plz see screenshot [ UnableToClear_" + XpathKey + fnsScreenShot_Date_format() + " ]"+". Getting Exception  >> "+e.getMessage());	}
}


//SElect matching value from lookup
public void fnsSelect_Value_from_LookupPopup_By_Clicking_on_LookUpButton(String Popuptitle, String LookupButtonLabelName, String Lookup_Input_LabelName, String Lookup_Search_and_Matching_Value_from_Excel, String NextAboveDiv_form_LK_LableDiv_Xpath) throws Throwable{
try{
	boolean Result_are_Coming = false;
	boolean Matching_Value_found = false;
	List<WebElement> SearchResult_Rows_Objs = null;
	fnsWait_Click_or_Type_By_LabelName(NextAboveDiv_form_LK_LableDiv_Xpath, LookupButtonLabelName, "Click");
	fnsLoading_Progressing_wait(2);
	
	fnsGet_Element_Enabled("Lookup_Search_Bttn");
	String Lookup_Title_Xpath = TestSuiteBase_MonitorPlan.WithOut_OR_Return_XPATH_if_More_than_One_Coming(OR_Listings.getProperty("Lookup_title"));
	
	String Lookup_title = driver.findElement(By.xpath(Lookup_Title_Xpath)).getText().toLowerCase().trim();
	assert Lookup_title.contains(Popuptitle.toLowerCase().trim()):"FAILED == Look up Title '"+Popuptitle+"' is not matched, please refer the screen shot >> Lookup_Title_Not_Match"+fnsScreenShot_Date_format();
	
	if( !(Lookup_Input_LabelName.equals("")) ){
		fnsWait_Click_or_Type_By_LabelName("Lookup_SearchCriteria", Lookup_Input_LabelName, Lookup_Search_and_Matching_Value_from_Excel);
		fnsLoading_Progressing_wait(1);
	}
	
	
	
	
	
	
	fnsWait_and_Click("Lookup_Search_Bttn");
	fnsLoading_Progressing_wait(2);
	
	fnsGet_Element_Enabled("View_Result_Table");
	for(int rowcount=1; rowcount<=60; rowcount++){
		SearchResult_Rows_Objs = fnsGet_OR_Listings_ObjectX("View_Result_Table").findElements(By.tagName("tr"));
		if(SearchResult_Rows_Objs.size()>0){
			Result_are_Coming=true;
			break;
		}else{
			Thread.sleep(1000);
		}			
	}
	if(Result_are_Coming==false){
		fnsTake_Screen_Shot("LK_SearchRecords_are_not_coming");
		throw new Exception("FAILED == Records are not displayed, after clicking on search button, plese refer the screen shot >> LK_SearchRecords_are_not_coming"+fnsScreenShot_Date_format());
	}		

	for(WebElement SearchResult_Rows_Elements : SearchResult_Rows_Objs){
		String SearchResultRow_Text = SearchResult_Rows_Elements.getText().toLowerCase().trim();
		if(SearchResultRow_Text.contains(Lookup_Search_and_Matching_Value_from_Excel.toLowerCase().trim())){
			Matching_Value_found = true;
			List<WebElement> Radio_button_objs = SearchResult_Rows_Elements.findElements(By.tagName("input"));
			if(Radio_button_objs.size()==1){
				for(WebElement Radio_button_element : Radio_button_objs){
					Radio_button_element.click();
					fnsLoading_Progressing_wait(1);
					break;
				}
			}else{
				fnsTake_Screen_Shot("LK_Radio_Button_Click_Fail");
				throw new Exception("FAILED == Radio button selection is fail for the record '"+Lookup_Search_and_Matching_Value_from_Excel+"' as <"+Radio_button_objs.size()+"> radio button is coming, plese refer the screen shot >> LK_Radio_Button_Click_Fail"+fnsScreenShot_Date_format());
			}
		}
		if(Matching_Value_found==true){
			break;	
		}
	}
	
	if(Matching_Value_found==false){
		fnsTake_Screen_Shot("LK_Matching_Record_Not_Found");
		throw new Exception("FAILED == '"+Lookup_Search_and_Matching_Value_from_Excel+"' is not found into the search results, after clicking on search button, plese refer the screen shot >> LK_Matching_Record_Not_Found"+fnsScreenShot_Date_format());
	}
			
	fnsGet_Element_Displayed("Lookup_Select_and_Save_Bttn");
	fnsWait_Click_on_First_Visible_Element_if_More_than_One_Coming("Select & Save", OR_Listings.getProperty("Lookup_Select_and_Save_Bttn"));
	fnsLoading_Progressing_wait(2);
		
	fnsApps_Report_Logs("PASSED == Successfully select the value <"+Lookup_Search_and_Matching_Value_from_Excel+"> from look up '"+Popuptitle+"'.");
}catch(AssertionError a){	
	fnsTake_Screen_Shot("Lookup_Title_Not_Match");
	isTestCasePass = false;
	throw new Exception (Throwables.getStackTraceAsString(a));
}catch(Throwable t){
	isTestCasePass = false;
	throw new Exception(Throwables.getStackTraceAsString(t));
}
}





//Function to wait for element
public void fnsGet_Element_Enabled(String XpathKey) throws Exception {
	
	try{
		for(int wait=0; wait<3; wait++){
			if(driver.findElements(By.xpath(OR_Listings.getProperty(XpathKey))).size()>0){
			//	fnsGet_OR_Listings_ObjectX(XpathKey);
				WebDriverWait elementwaitvar = new WebDriverWait(driver, Listings_Element_Max_Wait_Time);
				elementwaitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_Listings.getProperty(XpathKey))));
		
				WebDriverWait elementwaitvar1 = new WebDriverWait(driver, Listings_Element_Max_Wait_Time);
				elementwaitvar1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_Listings.getProperty(XpathKey)))).isEnabled();
				
				WebDriverWait elementwaitvar2 = new WebDriverWait(driver, Listings_Element_Max_Wait_Time);
				elementwaitvar2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_Listings.getProperty(XpathKey)))).isDisplayed();
				
				break;	} //if loop closed 
			else{
				throw new Exception();
			}
		} 
		fnsApps_Report_Logs("PASSED == Element is Visible having Xpath  >> "+XpathKey);	
	}catch(NoSuchWindowException W){
		//isTestCasePass = false;
		throw new Exception(W.getMessage());
	}catch(TimeoutException to){
		fnsTake_Screen_Shot("TimeOut_Element_Not_Coming");
		throw new Exception ("TimeOut : "+Throwables.getStackTraceAsString(to));
	}catch(Throwable t){
		try{
			//fnsApps_Report_Logs(t.getMessage()); // Not working
			Thread.sleep(3000);
			WebDriverWait elementwaitvar3 = new WebDriverWait(driver, (Listings_Element_Max_Wait_Time));
			elementwaitvar3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_Listings.getProperty(XpathKey)))).isEnabled();//}
			fnsApps_Report_Logs("PASSED == Element is Visible having Xpath  >> "+XpathKey);
		}catch(NoSuchWindowException W){
			//isTestCasePass = false;
			throw new Exception(W.getMessage());			}
		catch(Throwable e){
			//isTestCasePass = false;
			fnsTake_Screen_Shot(XpathKey);
			fnsApps_Report_Logs("FAILED == Element is not Visible having Xpath  >> "+XpathKey+", plz see screenshot [ " + ""+XpathKey + fnsScreenShot_Date_format() + " ]. Getting Exception >> "+Throwables.getStackTraceAsString(e));
			throw new Exception("FAILED == Element is not Visible having Xpath  [ " + XpathKey + " ] , plz see screenshot [ " + ""+XpathKey + fnsScreenShot_Date_format() + " ]. Getting Exception >> "+Throwables.getStackTraceAsString(e));
		}
	}
}


//Function to wait for element
public void fnsGet_Element_Displayed(String XpathKey) throws Exception {
	
	try{
		for(int wait=0; wait<=( (Listings_Element_Max_Wait_Time)*2 ); wait++){
			try{
				if(fnsGet_OR_Listings_ObjectX(XpathKey).isDisplayed()){
					break;
				}else{
					Thread.sleep(500);
				}
			}catch(Throwable t){
				Thread.sleep(500);
				//nothing to do
			}
			if(wait==Listings_Element_Max_Wait_Time){
				throw new Exception("FAILED == Element is not displayed, after <"+( (Listings_Element_Max_Wait_Time) )+"> seconds wait, please refer screenshot >> "+fnsScreenShot_Date_format());
			}
		} 
		fnsApps_Report_Logs("PASSED == Element is displayed having Xpath  >> "+XpathKey);	
	}catch(NoSuchWindowException W){
		//isTestCasePass = false;
		throw new Exception(W.getMessage());
	}catch(Throwable t){
		//isTestCasePass = false;
		fnsTake_Screen_Shot(XpathKey);
		fnsApps_Report_Logs("FAILED == Element is not displayed having Xpath  >> "+XpathKey+", plz see screenshot [ " + ""+XpathKey + fnsScreenShot_Date_format() + " ]. Getting Exception >> "+Throwables.getStackTraceAsString(t));
		throw new Exception("FAILED == Element is not displayed having Xpath  [ " + XpathKey + " ] , plz see screenshot [ " + ""+XpathKey + fnsScreenShot_Date_format() + " ]. Getting Exception >> "+Throwables.getStackTraceAsString(t));
	
	}
}



public void fnsGet_Element_Enabled_By_Passing_Wait_and_Screenshot_Condition(long waitTime, boolean TakeScreenshot, String XpathKey) throws Exception {
	try{
		WebDriverWait elementwaitvar = new WebDriverWait(driver, waitTime);
		elementwaitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_Listings.getProperty(XpathKey))));

		WebDriverWait elementwaitvar1 = new WebDriverWait(driver, waitTime);
		elementwaitvar1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_Listings.getProperty(XpathKey)))).isEnabled();
		
		WebDriverWait elementwaitvar2 = new WebDriverWait(driver, waitTime);
		elementwaitvar2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_Listings.getProperty(XpathKey)))).isDisplayed();
	}catch(Throwable t){
		//isTestCasePass = false;
		if(TakeScreenshot==true){
			fnsTake_Screen_Shot(XpathKey);
		}
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
}




//Fetch field text
public String fnsGet_Field_TEXT(String XpathKey) throws Exception {
	try{
		
		//fnsGet_Element_Enabled(XpathKey);
		String TextFetch=fnsGet_OR_Listings_ObjectX(XpathKey).getText().trim();
		System.out.println("PASSED == Text["+TextFetch+"] fetch done on Element having xpath [ " +XpathKey+" ].");
		fnsApps_Report_Logs("PASSED == Text fetch done on Element having xpath [ " +XpathKey+" ].");
		return TextFetch;
	}catch(NoSuchWindowException W){
		//isTestCasePass = false;
		throw new Exception(W.getMessage());			} 
	catch (Throwable t) {
			//isTestCasePass = false;
			fnsTake_Screen_Shot("TextFetchFail" + XpathKey);
			fnsApps_Report_Logs("FAILED == Unable to Fetch Text on Element having xpath, Please refer screenshot [" +"TextFetchFail" + XpathKey + fnsScreenShot_Date_format() +"]."+". Getting Exception  >> "+t.getMessage());
			throw new Exception("FAILED == Unable to Fetch Text on Element having xpath, Please refer screenshot [" +"TextFetchFail" + XpathKey + fnsScreenShot_Date_format() +"]."+". Getting Exception  >> "+t.getMessage());}
}


//It is Not working if value is in lower/upper case changed 
//function to select drop down value
public void fnaDD_value_Select_TagOPTION_DDTypeSelect(String DD_Xpath_Select, String Matching_Value_from_Excel, long Wait_Time) throws Exception {
	
	try{
		fnsGet_Element_Enabled(DD_Xpath_Select);
		for(int i=1; i<=Wait_Time; i++){
			try{
				Select DropDown = new Select(fnsGet_OR_Listings_ObjectX(DD_Xpath_Select));
				DropDown.selectByVisibleText(Matching_Value_from_Excel); //if Not working use above method.
				fnsApps_Report_Logs("PASSED == Value ["+Matching_Value_from_Excel+"] selection from drop down is done in <"+(i)+">seconds wait, DD having xpath >>  " + DD_Xpath_Select);
				break;
			}catch(Throwable t){
				Thread.sleep(1000);
				//nothing to do
			}
			if(i==Listings_Element_Max_Wait_Time){
				throw new Exception (i+" Attampted.");
			}
		}
		
	}catch(NoSuchWindowException W){
		//isTestCasePass = false;
		throw new Exception(W.getMessage());
	}catch(NoSuchElementException n){
		throw new Exception("FAILED == Excel value < "+Matching_Value_from_Excel+" > is not exists into the drop down ' " + DD_Xpath_Select +" ', please refer screenshot >> DdValueSelect_Fail" +  fnsScreenShot_Date_format() +". Getting Exception  >> "+Throwables.getStackTraceAsString(n).trim() );
	}catch(Throwable t) {
		//isTestCasePass = false;
		fnsTake_Screen_Shot("DdValueSelect_Fail");
		throw new Exception("FAILED == '" + DD_Xpath_Select +"' value <"+Matching_Value_from_Excel+"> selection fail, please refer screenshot >> DdValueSelect_Fail" +  fnsScreenShot_Date_format() +". Getting Exception  >> "+Throwables.getStackTraceAsString(t).trim() );
	}
}

//Return second label value
public String fnsReturn_SecondLabelValue_by_Passing_FirstLabelName(String Top_Div_Contains_Labels_Xpath_WithoutOR, String First_Label_Name) throws Throwable{
	try{
		boolean First_Label_Found =false;
		boolean Second_Label_Value_Found =false;
		String Second_Label_Value = "";
		List<WebElement> Label_Objects = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(Top_Div_Contains_Labels_Xpath_WithoutOR).findElements(By.tagName("label"));
		for(WebElement Label_Elements:Label_Objects){
			String Label_Element_Text = Label_Elements.getText().trim();
			if(First_Label_Found==true){
				Second_Label_Value = Label_Element_Text;
				Second_Label_Value_Found = true;
				break;
			}
			if(Label_Element_Text.toLowerCase().contains(First_Label_Name.toLowerCase().trim())){
				First_Label_Found= true;		
			}
		}
		
		if(First_Label_Found==false){
			fnsTake_Screen_Shot("Label_Not_Found");
			throw new Exception("FAILED == Label is not found, please refer the screen shot >> Label_Not_Found"+fnsScreenShot_Date_format());
		}
		
		fnsApps_Report_Logs("PASSED == Value <"+Second_Label_Value+"> fetch is done from label '"+First_Label_Name+"'.");
		
		return Second_Label_Value;
	
	}catch(NoSuchWindowException W){
		
		throw new Exception(W.getMessage());			}
	catch(Throwable e){		
		fnsTake_Screen_Shot("Label_Not_Found");
		throw new Exception(Throwables.getStackTraceAsString(e));
	}
	
	
}







//function to select drop down value
public void fnsDD_value_Select_TagOPTION_DDTypeSelect(String DD_Xpath_Select, String Matching_Value_from_Excel, long Wait_Time) throws Exception {
	try{
		fnsGet_Element_Enabled(DD_Xpath_Select);
		for(int i=0; i<=Wait_Time; i++){
			boolean Value_Matched = false;
			try{
				List<WebElement> DDobjectlists=fnsGet_OR_Listings_ObjectX(DD_Xpath_Select).findElements(By.tagName("option"));
				for(WebElement dd_value:DDobjectlists){
					String dd_TEXT = dd_value.getText().toLowerCase().trim();
					if(dd_TEXT.equals(Matching_Value_from_Excel.toLowerCase().trim())){
						dd_value.click();
						Value_Matched = true;
						break;
					}
				}
				if(Value_Matched){
					fnsApps_Report_Logs("PASSED == Value ["+Matching_Value_from_Excel+"] selection from drop down is done in <"+(i)+">seconds wait, DD having xpath >>  " + DD_Xpath_Select);
					break;
				}else{
					throw new NotContextException("FAILED == Excel value < "+Matching_Value_from_Excel+" > is not exists into the drop down ' " + DD_Xpath_Select +" ', please refer screenshot >> DdValueSelect_Fail" +  fnsScreenShot_Date_format() );
				}	
				
			}catch(NotContextException NC){
				if(i==Wait_Time){
					throw new Exception(Throwables.getStackTraceAsString(NC));
				}else{
					Thread.sleep(800);
				}
			}catch(NoSuchWindowException W){
				isTestCasePass = false;
				throw new Exception(W.getMessage());
			}catch(Throwable t) {
				if(i==Wait_Time){
					throw new Exception(Throwables.getStackTraceAsString(t));
				}else{
					Thread.sleep(800);
				}
			}
		}
	}catch(Throwable t){
		isTestCasePass = false;
		fnsTake_Screen_Shot("DdValueSelect_Fail");
		fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
		throw new Exception (Throwables.getStackTraceAsString(t));
	}
}


public void fnsDD_Verify_SelectedValue_TagOPTION_DDTypeSelect(String DD_Xpath_Select, String DD_Selected_Value) throws Exception {
	try{
		fnsGet_Element_Enabled(DD_Xpath_Select);
		boolean Option_Selected = false;
		boolean Option_Selected_Matched = false;
	
		List<WebElement> DDobjectlists=fnsGet_OR_Listings_ObjectX(DD_Xpath_Select).findElements(By.tagName("option"));
		for(WebElement dd_value:DDobjectlists){
			String Option_Slected = dd_value.getAttribute("selected");
			if( !(Option_Slected==null) ){
				Option_Selected = true;
				String dd_TEXT = dd_value.getText().toLowerCase().trim();
				if(dd_TEXT.equals(DD_Selected_Value.toLowerCase().trim())){
					Option_Selected_Matched = true;
					break;
				}
			}
		}
		if(Option_Selected==false){
			throw new NotContextException("FAILED == none of the value is selected into the drop down ' " + DD_Xpath_Select +" ', please refer screenshot >> DdValueSelected_Fail" +  fnsScreenShot_Date_format() );
		}		
		
		if(Option_Selected_Matched){
			fnsApps_Report_Logs("PASSED == Expected value < "+DD_Selected_Value+" > is Matched with the Actual Value < "+DD_Selected_Value+" > from the drop down >> " + DD_Xpath_Select );
		}else{
			throw new NotContextException("FAILED == Expected value < "+DD_Selected_Value+" > is NOT matched with the Actual Value < "+DD_Selected_Value+" > from the drop down ' " + DD_Xpath_Select +" ', please refer screenshot >> DdValueSelected_Fail" +  fnsScreenShot_Date_format() );
		}	
	}catch(Throwable t){
		isTestCasePass = false;
		fnsTake_Screen_Shot("DdValueSelected_Fail");
		fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
		throw new Exception (Throwables.getStackTraceAsString(t));
	}
}



//function to select drop down value
public void fnsDD_value_Select_TagOPTION_DDTypeSelect_ContainsMatch(String DD_Xpath_Select, String Matching_Value_from_Excel, long Wait_Time) throws Exception {
	try{
		fnsGet_Element_Enabled(DD_Xpath_Select);
		for(int i=0; i<=Wait_Time; i++){
			boolean Value_Matched = false;
			try{
				List<WebElement> DDobjectlists=fnsGet_OR_Listings_ObjectX(DD_Xpath_Select).findElements(By.tagName("option"));
				for(WebElement dd_value:DDobjectlists){
					String dd_TEXT = dd_value.getText().toLowerCase().trim();
					if(dd_TEXT.contains(Matching_Value_from_Excel.toLowerCase().trim())){
						dd_value.click();
						Value_Matched = true;
						break;
					}
				}
				if(Value_Matched){
					fnsApps_Report_Logs("PASSED == Value ["+Matching_Value_from_Excel+"] selection from drop down is done in <"+(i)+">seconds wait, DD having xpath >>  " + DD_Xpath_Select);
					break;
				}else{
					throw new NotContextException("FAILED == Excel value < "+Matching_Value_from_Excel+" > is not exists into the drop down ' " + DD_Xpath_Select +" ', please refer screenshot >> DdValueSelect_Fail" +  fnsScreenShot_Date_format() );
				}	
				
			}catch(NotContextException NC){
				if(i==Wait_Time){
					throw new Exception(Throwables.getStackTraceAsString(NC));
				}else{
					Thread.sleep(800);
				}
			}catch(NoSuchWindowException W){
				isTestCasePass = false;
				throw new Exception(W.getMessage());
			}catch(Throwable t) {
				if(i==Wait_Time){
					throw new Exception(Throwables.getStackTraceAsString(t));
				}else{
					Thread.sleep(800);
				}
			}
		}
	}catch(Throwable t){
		isTestCasePass = false;
		TestSuiteBase_MonitorPlan.fnsTake_Screen_Shot_Without_OR("DdValueSelect_Fail");
	//	fnsTake_Screen_Shot("DdValueSelect_Fail");
		fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
		throw new Exception (Throwables.getStackTraceAsString(t));
	}
}


	public void fnsDD_Value_select_by_PassingDDLabelName(String DD_Label_Name, String Value) throws Throwable{
		try{
			boolean DD_Label_Found = false;
			boolean Got_DDList = false;
			String DD_SelectedLabel_Xpath = "(//label[text()='"+DD_Label_Name+"']/following::span[@class='k-input'])[1]";
			String DD_List = "";
			
			for(int DD_Label_try=1; DD_Label_try<=Listings_Element_Max_Wait_Time; DD_Label_try++){
				try{
					if(driver.findElements(By.xpath(DD_SelectedLabel_Xpath)).size()>0){
						DD_Label_Found = true;	
						break;
					}else{
						Thread.sleep(1000);
					}
				}catch(Throwable t){
						//nothing to do
				}
				
			}
			
			if(DD_Label_Found==false){
				throw new Exception ("FAILED == Drop down '"+DD_Label_Name+"' is not found on the screen, please refer the screen shot >> DdValueSelect_Fail"+fnsScreenShot_Date_format());
			}
			
			TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(DD_SelectedLabel_Xpath);
			TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(DD_SelectedLabel_Xpath);
			Thread.sleep(1500);
			
			for(int i=0; i<=60; i++){
				DD_List = "//label[text()='"+DD_Label_Name+"']/following::ul[1]";
				if(driver.findElements(By.xpath(DD_List)).size()>0){
					Got_DDList = true;
					break;
				}
				DD_List = "//label[text()='"+DD_Label_Name+"']/preceding::ul[1]";
				if(driver.findElements(By.xpath(DD_List)).size()>0){
					Got_DDList = true;
					break;
				}
				
				if(Got_DDList == false){
					Thread.sleep(1000);
				}
			}
			
			
			
			
			
			TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(DD_List);
			
			for(int i=0; i<=Listings_Element_Max_Wait_Time; i++){
				boolean Value_Matched = false;
				try{
					List<WebElement> DDListObjs=TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(DD_List).findElements(By.tagName("li"));
					for(WebElement DDListEles:DDListObjs){
						String dd_TEXT = DDListEles.getText().toLowerCase().trim();
						if(dd_TEXT.equals(Value.toLowerCase().trim())){
							DDListEles.click();
							Value_Matched = true;
							fnsApps_Report_Logs("PASSED == value < "+Value+" > is exists into the drop down '" + DD_Label_Name +"'");
							break;
						}
					}
					if(Value_Matched){
						String SelectValue = TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(DD_SelectedLabel_Xpath).toLowerCase();
						if(SelectValue.equalsIgnoreCase(Value)){
							fnsApps_Report_Logs("PASSED == Value ["+Value+"] selection from drop down '"+DD_Label_Name+"' is done in <"+(i)+">seconds wait.");
							break;
						}else{
							throw new Exception("FAILED == Selection of value < "+Value+" > from drop down '"+DD_Label_Name+"' is getting, please refer screenshot >> DdValueSelect_Fail" +  fnsScreenShot_Date_format() );
						}
					}else{
						throw new Exception("FAILED == value < "+Value+" > is not exists into the drop down '" + DD_Label_Name +"', please refer screenshot >> DdValueSelect_Fail" +  fnsScreenShot_Date_format() );
					}	
					
				}catch(Throwable t) {
					throw new Exception(Throwables.getStackTraceAsString(t).trim());
				}
			}			
		}catch(NoSuchWindowException W){
			throw new Exception(W.getMessage());
		}catch(Throwable t) {
			fnsTake_Screen_Shot("DdValueSelect_Fail");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	
	}
//############################################################## Applications Methods ##################################################################

//Clicking on Menu Ajax Link	
	public void fncSub_Menu_Ajax_Link_Click_By_PassingAjaxPath(String Menu_Ajax_Xpath, String Sub_Menu_Ajax_Xpath) throws Exception {
		try{
			fnsGet_Element_Enabled("Main_Menu_Ajax_Link");
			WebElement MainMenu = fnsGet_OR_Listings_ObjectX("Main_Menu_Ajax_Link");
			Actions action = new Actions(driver);
			action.moveToElement(MainMenu).click().build().perform();
			Thread.sleep(500);
			Thread.sleep(1500); //temp
			if(driver.findElements(By.xpath(OR_Listings.getProperty(Menu_Ajax_Xpath))).size()>0){
				fnsWait_Click_on_First_Visible_Element_if_More_than_One_Coming(Menu_Ajax_Xpath, OR_Listings.getProperty(Menu_Ajax_Xpath));
				Thread.sleep(1000);
				fnsWait_Click_on_First_Visible_Element_if_More_than_One_Coming(Sub_Menu_Ajax_Xpath, OR_Listings.getProperty(Sub_Menu_Ajax_Xpath));
			}else if (driver.findElements(By.xpath(OR_Listings.getProperty(Sub_Menu_Ajax_Xpath))).size()>0){
				fnsWait_Click_on_First_Visible_Element_if_More_than_One_Coming(Sub_Menu_Ajax_Xpath, OR_Listings.getProperty(Sub_Menu_Ajax_Xpath));
			} else {
				fnsTake_Screen_Shot("Menu_are_not_coming_");
				throw new Exception ("FAILED == Both Menus '"+Menu_Ajax_Xpath+"' and '"+Sub_Menu_Ajax_Xpath+"' are not coming, plz see screenshot >> Menu_are_not_coming_" + fnsScreenShot_Date_format() );
			}
		}catch(NoSuchWindowException W){
			throw new Exception(W.getMessage());			}
		catch(Throwable t) {
			throw new Exception(Throwables.getStackTraceAsString(t).trim());}
}	
	

	
//Clicking on Menu Ajax Link	
	public void fncMenu_Ajax_Link_Click_By_PassingAjaxPath(String Menu_Ajax_Xpath) throws Exception {
		try{
			fnsGet_Element_Enabled("Main_Menu_Ajax_Link");
			WebElement MainMenu = fnsGet_OR_Listings_ObjectX("Main_Menu_Ajax_Link");
			
			Actions action = new Actions(driver);
			action.moveToElement(MainMenu).click().build().perform();
					
			Thread.sleep(500);
			Actions action1 = new Actions(driver);
			try{
				
				if(driver.findElements(By.xpath(OR_Listings.getProperty(Menu_Ajax_Xpath))).size()>0){
					WebDriverWait elementwaitvar1 = new WebDriverWait(driver, 5);
					elementwaitvar1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_Listings.getProperty(Menu_Ajax_Xpath)))).isEnabled();
					
					WebDriverWait elementwaitvar2 = new WebDriverWait(driver, 5);
					elementwaitvar2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_Listings.getProperty(Menu_Ajax_Xpath)))).isDisplayed();
				} 
				
			}catch(Throwable t){
				action.moveToElement(MainMenu).click().build().perform();
			}
			WebElement Menu = fnsGet_OR_Listings_ObjectX(Menu_Ajax_Xpath);
			action1.moveToElement(Menu).click().build().perform();
			
			fnsApps_Report_Logs("PASSED == Successfully Click on Menu <"+(Menu_Ajax_Xpath)+">.");
		//	fnsLoading_Progressing_wait(5); //Not Working here as loading type changes
		}catch(NoSuchWindowException W){
			//isTestCasePass = false;
			throw new Exception(W.getMessage());			}
		catch(Throwable t) {
			//isTestCasePass = false;
			fnsTake_Screen_Shot(Menu_Ajax_Xpath+"_Fail");
			fnsApps_Report_Logs("FAILED == Clicking on Menu <"+(Menu_Ajax_Xpath)+"> Failed, plz see screenshot [" +Menu_Ajax_Xpath+"_Fail" + fnsScreenShot_Date_format() + "]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(t).trim());
			throw new Exception("FAILED == Clicking on Menu <"+(Menu_Ajax_Xpath)+"> Failed, plz see screenshot [" +Menu_Ajax_Xpath+"_Fail" + fnsScreenShot_Date_format() + "]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(t).trim());
		}
}	
		
		
		

	//Clicking on Menu Ajax Link	
	public void fnsMenu_Ajax_Link_Click_By_PassingAjaxPath_iPulse(String AjaxLinkXpath) throws Exception {
		try{
			fnsGet_Element_Enabled("iPulse_Main_Menu_Ajax");
			WebElement Menu_Element = fnsGet_OR_Listings_ObjectX("iPulse_Main_Menu_Ajax");
			
			WebElement VersionLogoImage = fnsGet_OR_Listings_ObjectX("iPulse_VersionLogoImage");
			VersionLogoImage.click();
			Thread.sleep(500);
			
			Actions action = new Actions(driver);
			action.moveToElement(Menu_Element).build().perform();
					
			Thread.sleep(500);
			Actions action1 = new Actions(driver);
			fnsGet_Element_Enabled(AjaxLinkXpath);
			WebElement AjaxLinkXpath_Element = fnsGet_OR_Listings_ObjectX(AjaxLinkXpath);
			action1.moveToElement(AjaxLinkXpath_Element).click().build().perform();
			fnsApps_Report_Logs("PASSED == Successfully Click on Menu <"+(AjaxLinkXpath)+">.");
		}catch(NoSuchWindowException W){
			throw new Exception(W.getMessage());			}
		catch(Throwable t) {
			fnsTake_Screen_Shot(AjaxLinkXpath+"_Fail");
			fnsApps_Report_Logs("FAILED == Clicking on Menu <"+(AjaxLinkXpath)+"> Failed, plz see screenshot [" +AjaxLinkXpath+"_Fail" + fnsScreenShot_Date_format() + "]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(t).trim());
			throw new Exception("FAILED == Clicking on Menu <"+(AjaxLinkXpath)+"> Failed, plz see screenshot [" + AjaxLinkXpath+"_Fail" + fnsScreenShot_Date_format() + "]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(t).trim());
		}
	}	
		
		
		
		//Verify View exists into the view list and conditional click on it.
		public String fncMyView_SharedView_NewlyCreatedView_Display_and_Conditional_Click(Integer Share_is_1_and_MyView_is_2, boolean View_Must_Exists,  String ViewName, String Click_OnView_Yes_No) throws Throwable{
			try{
				String Return_View_Exists_in_which_List = null;
				boolean View_Found_into_Menu_DD = false;
				boolean View_List_Paginaton_NEXT_Link = false;
				boolean List_Loaded = false;
				boolean View_List_Paginaton_Previous_Link_disAppear =false;
							
				fnsGet_Element_Enabled("ActiveView_DD");
				for(int i=0; i<5; i++){
					if( driver.findElement(By.xpath(OR_Listings.getProperty("ActiveView_ShareView_Link"))).isDisplayed() ){
						break;
					}else{
						TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element_and_Click(OR_Listings.getProperty("ActiveView_DD"));
						Thread.sleep(1000);
					}
				}
					
				Thread.sleep(500);
						
				List<WebElement> Menu_DDobjectlists = null;
				
				for(int Shared_MyView_Link_Click_Counter = Share_is_1_and_MyView_is_2; Shared_MyView_Link_Click_Counter<=Share_is_1_and_MyView_is_2; Shared_MyView_Link_Click_Counter++){
					System.out.println("Satya Counter value = "+Shared_MyView_Link_Click_Counter);
					if( !(View_Found_into_Menu_DD) ){
						if(List_Loaded == false){
							if(Shared_MyView_Link_Click_Counter==1){
								fnsGet_Element_Enabled("ActiveView_ShareView_Link");
								fnsWait_and_Click("ActiveView_ShareView_Link");
								//Thread.sleep(500);
								fnsGet_Element_Enabled("ActiveView_SharedView_DD_Data_Div");
								
								for(int i=0; i<=5; i++){
									List_Loaded = false;
									Menu_DDobjectlists=fnsGet_OR_Listings_ObjectX("ActiveView_SharedView_DD_Data_Div").findElements(By.tagName("div"));
									for(WebElement Menu_Element:Menu_DDobjectlists){
										String Menu_Element_Text = Menu_Element.getText().trim();
										//System.out.println(Menu_Element_Text);
										if(Menu_Element_Text.length()>5){		List_Loaded = true; break;	}else{	break;	}
									}
									
									if(List_Loaded==true){   break;		}else{		Thread.sleep(750);		}
									
									if(i==5 && List_Loaded==false){
										fnsApps_Report_Logs("View Menu is not getting load (List coming Blank), after <"+10+"> seconds wait,  please refer screen shot >> PASS_Please_ignore_View_List_Blank"+fnsScreenShot_Date_format());
										break;
									}
										
								}
								
								Return_View_Exists_in_which_List = "shared view";
							}else if (Shared_MyView_Link_Click_Counter==2){
								fnsGet_Element_Enabled("ActiveView_MyView_Link");
								fnsWait_and_Click("ActiveView_MyView_Link");
								Thread.sleep(500);
								fnsGet_Element_Enabled("ActiveView_MyView_DD_Data_Div");
								
								for(int i=0; i<=5; i++){
									List_Loaded = false;
									Menu_DDobjectlists=fnsGet_OR_Listings_ObjectX("ActiveView_MyView_DD_Data_Div").findElements(By.tagName("div"));
									for(WebElement Menu_Element:Menu_DDobjectlists){
										String Menu_Element_Text = Menu_Element.getText().trim();
										System.out.println(Menu_Element_Text);
										if(Menu_Element_Text.length()>5){		List_Loaded = true; 	}else{	break;	}
									}
									
									if(List_Loaded==true){   break;		}else{		Thread.sleep(750);		}
									
									if(i==5 && List_Loaded==false){
										fnsApps_Report_Logs("View Menu is not getting load (List coming Blank), after <"+10+"> seconds wait,  please refer screen shot >> PASS_Please_ignore_View_List_Blank"+fnsScreenShot_Date_format());
										break;
									}
									
								}
								Return_View_Exists_in_which_List = "my view";
							}
						}
			
							
						
						
						if(List_Loaded){
								for(WebElement Menu_Element:Menu_DDobjectlists){
									String abc = Menu_Element.getText();
									System.out.println(abc);
									if(Menu_Element.getText().contains(ViewName)){
										
										
										if(Click_OnView_Yes_No.equals("Yes")){
											Actions action = new Actions(driver);
											action.moveToElement(Menu_Element).click(Menu_Element).build().perform();
											fnsApps_Report_Logs("PASSED == Click done on View Name ( "+ViewName+" ) from MyView/SharedView  Menu list on '"+ViewName.split("-")[0].toUpperCase()+"' screen.");
											fnsLoading_Progressing_wait(2);
										}else if (Click_OnView_Yes_No.equals("No")){
											fnsGet_Element_Enabled("ActiveView_Header_Label_Link");
											fnsWait_and_Click_Through_JS(OR_Listings.getProperty("ActiveView_Header_Label_Link"));
											fnsApps_Report_Logs("PASSED == View Name ( "+ViewName+" ) is exists into the MyView/SharedView Menu list on '"+ViewName.split("-")[0].toUpperCase()+"' screen.");
										}
										View_Found_into_Menu_DD=true;
										break;
									}
								}
								if( View_Found_into_Menu_DD==false ){
									
									if( View_List_Paginaton_Previous_Link_disAppear==true ){
										
										for(int Displaytry=1; Displaytry<=5; Displaytry++){
												try{
													if(fnsGet_OR_Listings_ObjectX("ActiveView_Pagination_Next_link").isDisplayed()){
														fnsGet_OR_Listings_ObjectX("ActiveView_Pagination_Next_link").click();
														Shared_MyView_Link_Click_Counter = Share_is_1_and_MyView_is_2;
														fnsLoading_Progressing_wait(2);
														View_List_Paginaton_NEXT_Link = true;
														break;	
													}else{
														View_List_Paginaton_NEXT_Link = false;
													}
												}catch(Throwable t){
													if(Displaytry==5){
														View_List_Paginaton_NEXT_Link = false;
														throw new Exception(Throwables.getStackTraceAsString(t));
													}else{
														Thread.sleep(500);
													}
												}		
											}
									}
									try{
										for(int i=1; i<=10; i++){
											if(View_List_Paginaton_Previous_Link_disAppear == false){
												if(driver.findElements(By.xpath(OR_Listings.getProperty("ActiveView_Pagination_Previous_link"))).size()>0){
													if(fnsGet_OR_Listings_ObjectX("ActiveView_Pagination_Previous_link").isDisplayed()){
														fnsGet_OR_Listings_ObjectX("ActiveView_Pagination_Previous_link").click();
														Shared_MyView_Link_Click_Counter = Share_is_1_and_MyView_is_2;
														fnsLoading_Progressing_wait(2);
													}
												}else{
													View_List_Paginaton_Previous_Link_disAppear = true;
													break;
												}
											}
										}
									}catch(Throwable t){
										//nothing to do
									}
									
								}
													
								if( View_Found_into_Menu_DD==false  && View_List_Paginaton_NEXT_Link == false){
									if(View_Must_Exists==true){
										throw new Exception("FAILED == View is not exists into the MyView/SharedView  Menu list on '"+ViewName.split("-")[0].toUpperCase()+"' screen, please refer screen shot >> ViewNmae_Fail"+fnsScreenShot_Date_format());
									}else{
										fnsApps_Report_Logs("PASSED == View Name ( "+ViewName+" ) is not exists into the MyView/SharedView Menu list on '"+ViewName.split("-")[0].toUpperCase()+"' screen.");
										
										fnsGet_Element_Enabled("ActiveView_Header_Label_Link");
										fnsWait_and_Click_Through_JS(OR_Listings.getProperty("ActiveView_Header_Label_Link"));
																
										fnsLoading_Progressing_wait(2);
										
										Return_View_Exists_in_which_List = "View Not Exists";
									}
								}
						}else{
						//	fnsTake_Screen_Shot("PASS_Please_ignore_View_List_Blank");
							fnsWait_and_Click_Through_JS(OR_Listings.getProperty("ActiveView_Header_Label_Link"));
							fnsLoading_Progressing_wait(2);
							Return_View_Exists_in_which_List = "View Not Exists";
							break;
							
						}
					}	
				}
				fnsLoading_Progressing_wait(1);
			return Return_View_Exists_in_which_List;
				
			}catch(NoSuchWindowException W){
				//isTestCasePass = false;
				throw new Exception(W.getMessage());			}
			catch(Throwable t) {
				//isTestCasePass = false;
				fnsTake_Screen_Shot("ViewNmae_Fail");
				throw new Exception("View Name ( "+ViewName+" ) : "+Throwables.getStackTraceAsString(t));
			}

		}
		
		
	
/*	
	//Verify View exists into the view list and conditional click on it.
	public String fncMyView_SharedView_NewlyCreatedView_Display_and_Conditional_Click(Integer Share_is_1_and_MyView_is_2, boolean View_Must_Exists,  String ViewName, String Click_OnView_Yes_No) throws Throwable{
		try{
			String Return_View_Exists_in_which_List = null;
			boolean View_Found_into_Menu_DD = false;
			boolean View_List_Paginaton_NEXT_Link = false;
			boolean List_Loaded = false;
			boolean View_List_Paginaton_Previous_Link_disAppear =false;
						
			fnsGet_Element_Enabled("ActiveView_DD");
			for(int i=0; i<5; i++){
				if( driver.findElement(By.xpath(OR_Listings.getProperty("ActiveView_ShareView_Link"))).isDisplayed() ){
					break;
				}else{
					TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element_and_Click(OR_Listings.getProperty("ActiveView_DD"));
					Thread.sleep(1000);
				}
			}
				
			Thread.sleep(500);
					
			List<WebElement> Menu_DDobjectlists = null;
			
			for(int Shared_MyView_Link_Click_Counter = Share_is_1_and_MyView_is_2; Shared_MyView_Link_Click_Counter<=Share_is_1_and_MyView_is_2; Shared_MyView_Link_Click_Counter++){
				System.out.println("Satya Counter value = "+Shared_MyView_Link_Click_Counter);
				if( !(View_Found_into_Menu_DD) ){
					if(List_Loaded == false){
						if(Shared_MyView_Link_Click_Counter==1){
							fnsGet_Element_Enabled("ActiveView_ShareView_Link");
							fnsWait_and_Click("ActiveView_ShareView_Link");
							//Thread.sleep(500);
							fnsGet_Element_Enabled("ActiveView_SharedView_DD_Data_Div");
							
							for(int i=0; i<=Listings_Element_Max_Wait_Time; i++){
								List_Loaded = false;
								Menu_DDobjectlists=fnsGet_OR_Listings_ObjectX("ActiveView_SharedView_DD_Data_Div").findElements(By.tagName("div"));
								for(WebElement Menu_Element:Menu_DDobjectlists){
									String Menu_Element_Text = Menu_Element.getText().trim();
									//System.out.println(Menu_Element_Text);
									if(Menu_Element_Text.length()>5){		List_Loaded = true; break;	}else{	break;	}
								}
								
								if(List_Loaded==true){   break;		}else{		Thread.sleep(750);		}
								
								if(i==Listings_Element_Max_Wait_Time && List_Loaded==false){
									throw new Exception("FAILED == View Menu is not getting load, after <"+Listings_Element_Max_Wait_Time+"> seconds wait,  please refer screen shot >> ViewNmae_Fail"+fnsScreenShot_Date_format());
								}
								
								
								
								
								
								
								
								
								
								
								
								
							}
							
							Return_View_Exists_in_which_List = "shared view";
						}else if (Shared_MyView_Link_Click_Counter==2){
							fnsGet_Element_Enabled("ActiveView_MyView_Link");
							fnsWait_and_Click("ActiveView_MyView_Link");
							Thread.sleep(500);
							fnsGet_Element_Enabled("ActiveView_MyView_DD_Data_Div");
							
							for(int i=0; i<=Listings_Element_Max_Wait_Time; i++){
								List_Loaded = false;
								Menu_DDobjectlists=fnsGet_OR_Listings_ObjectX("ActiveView_MyView_DD_Data_Div").findElements(By.tagName("div"));
								for(WebElement Menu_Element:Menu_DDobjectlists){
									String Menu_Element_Text = Menu_Element.getText().trim();
									System.out.println(Menu_Element_Text);
									if(Menu_Element_Text.length()>5){		List_Loaded = true; 	}else{	break;	}
								}
								
								if(List_Loaded==true){   break;		}else{		Thread.sleep(750);		}
								
								if(i==Listings_Element_Max_Wait_Time && List_Loaded==false){
									throw new Exception("FAILED == View Menu is not getting load, after <"+Listings_Element_Max_Wait_Time+"> seconds wait,  please refer screen shot >> ViewNmae_Fail"+fnsScreenShot_Date_format());
								}
								
							}
							Return_View_Exists_in_which_List = "my view";
						}
					}
		
					for(WebElement Menu_Element:Menu_DDobjectlists){
						String abc = Menu_Element.getText();
						System.out.println(abc);
						if(Menu_Element.getText().contains(ViewName)){
							
							
							if(Click_OnView_Yes_No.equals("Yes")){
								Actions action = new Actions(driver);
								action.moveToElement(Menu_Element).click(Menu_Element).build().perform();
								fnsLoading_Progressing_wait(2);
								fnsApps_Report_Logs("PASSED == Click done on View Name ( "+ViewName+" ) from MyView/SharedView  Menu list on '"+ViewName.split("-")[0].toUpperCase()+"' screen.");
							}else{
								fnsApps_Report_Logs("PASSED == View Name ( "+ViewName+" ) is exists into the MyView/SharedView Menu list on '"+ViewName.split("-")[0].toUpperCase()+"' screen.");
							}
							View_Found_into_Menu_DD=true;
							break;
						}
					}
					if( View_Found_into_Menu_DD==false ){
						
						if( View_List_Paginaton_Previous_Link_disAppear==true ){
							
							for(int Displaytry=1; Displaytry<=5; Displaytry++){
									try{
										if(fnsGet_OR_Listings_ObjectX("ActiveView_Pagination_Next_link").isDisplayed()){
											fnsGet_OR_Listings_ObjectX("ActiveView_Pagination_Next_link").click();
											Shared_MyView_Link_Click_Counter = Share_is_1_and_MyView_is_2;
											fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
											View_List_Paginaton_NEXT_Link = true;
											break;	
										}else{
											View_List_Paginaton_NEXT_Link = false;
										}
									}catch(Throwable t){
										if(Displaytry==5){
											View_List_Paginaton_NEXT_Link = false;
											throw new Exception(Throwables.getStackTraceAsString(t));
										}else{
											Thread.sleep(500);
										}
									}		
								}
						}
						try{
							for(int i=1; i<=10; i++){
								if(View_List_Paginaton_Previous_Link_disAppear == false){
									if(driver.findElements(By.xpath(OR_Listings.getProperty("ActiveView_Pagination_Previous_link"))).size()>0){
										if(fnsGet_OR_Listings_ObjectX("ActiveView_Pagination_Previous_link").isDisplayed()){
											fnsGet_OR_Listings_ObjectX("ActiveView_Pagination_Previous_link").click();
											Shared_MyView_Link_Click_Counter = Share_is_1_and_MyView_is_2;
											fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
										}
									}else{
										View_List_Paginaton_Previous_Link_disAppear = true;
										break;
									}
								}
							}
						}catch(Throwable t){
							//nothing to do
						}
						
					}
										
					if( View_Found_into_Menu_DD==false  && View_List_Paginaton_NEXT_Link == false){
						if(View_Must_Exists==true){
							throw new Exception("FAILED == View is not exists into the MyView/SharedView  Menu list on '"+ViewName.split("-")[0].toUpperCase()+"' screen, please refer screen shot >> ViewNmae_Fail"+fnsScreenShot_Date_format());
						}else{
							fnsApps_Report_Logs("PASSED == View Name ( "+ViewName+" ) is not exists into the MyView/SharedView Menu list on '"+ViewName.split("-")[0].toUpperCase()+"' screen.");
							
							fnsGet_Element_Enabled("ActiveView_Header_Label_Link");
							for(int i=0; i<=5; i++){
								try{
									fnsGet_OR_Listings_ObjectX("ActiveView_Header_Label_Link").click();
									System.out.println("Click done on ActiveView_Header_Label_Link");
									break;
								}catch(Throwable t){
									Thread.sleep(1000);
									if(i==5){
										if(Shared_MyView_Link_Click_Counter==2){
											fnsGet_OR_Listings_ObjectX("ActiveView_MyView_FirstView_from_List").click();
										}else if (Shared_MyView_Link_Click_Counter==1){
											fnsGet_OR_Listings_ObjectX("ActiveView_SharedView_FirstView_from_List").click();
										}
										
									}
								}
							}							
							fnsLoading_Progressing_wait(2);
							
							Return_View_Exists_in_which_List = "View Not Exists";
						}
					}
					
				}	
			}
		
		return Return_View_Exists_in_which_List;
			
		}catch(NoSuchWindowException W){
			//isTestCasePass = false;
			throw new Exception(W.getMessage());			}
		catch(Throwable t) {
			//isTestCasePass = false;
			fnsTake_Screen_Shot("ViewNmae_Fail");
			throw new Exception("View Name ( "+ViewName+" ) : "+Throwables.getStackTraceAsString(t));
		}

	}

*/
//Verify View exists into the view list and conditional click on it.
	public void fncMyView_SharedView_NewlyCreatedView_Click_from_PopUp(String ViewName) throws Throwable{
		try{
			boolean View_Found_into_Menu_DD = false;
			boolean View_List_Paginaton_NEXT_Link = false;
			boolean List_Loaded = false;
			fnsGet_Element_Enabled("PopUp_ActiveView_DD");
			fnsLoading_Progressing_wait(2);
			fnsWait_and_Click("PopUp_ActiveView_DD");
			//Thread.sleep(4000);
			
			List<WebElement> DDobjectlists = null;
			
			//Start counter with one if want to clicking on shared menu list
			for(int Shared_My_View_Link_Click_Counter = 2; Shared_My_View_Link_Click_Counter<=2; Shared_My_View_Link_Click_Counter++){
				
				if( !(View_Found_into_Menu_DD) ){
					if(List_Loaded == false){
						if(Shared_My_View_Link_Click_Counter==1){
							fnsGet_Element_Enabled("PopUp_ActiveView_ShareView_Link");
							fnsWait_and_Click("PopUp_ActiveView_ShareView_Link");
							//Thread.sleep(500);
							fnsGet_Element_Enabled("PopUp_ActiveView_SharedView_DD_Data_Div");
							DDobjectlists=fnsGet_OR_Listings_ObjectX("PopUp_ActiveView_SharedView_DD_Data_Div").findElements(By.tagName("div"));
						}else{
							fnsGet_Element_Enabled("PopUp_ActiveView_MyView_Link");
							fnsWait_and_Click("PopUp_ActiveView_MyView_Link");
							Thread.sleep(2000);
							fnsGet_Element_Enabled("PopUp_ActiveView_MyView_DD_Data_Div");
							
							
							for(int i=0; i<=Listings_Element_Max_Wait_Time; i++){
								List_Loaded = false;
								DDobjectlists=fnsGet_OR_Listings_ObjectX("PopUp_ActiveView_MyView_DD_Data_Div").findElements(By.tagName("div"));
								for(WebElement Menu_Element:DDobjectlists){
									String Menu_Element_Text = Menu_Element.getText().trim();
									if(Menu_Element_Text.length()>5){		List_Loaded = true; 	}else{	break;	}
								}
								
								if(List_Loaded==true){   break;		}else{		Thread.sleep(750);		}
								
								if(i==Listings_Element_Max_Wait_Time && List_Loaded==false){
									throw new Exception("FAILED == PopUp View Menu is not getting load, after <"+Listings_Element_Max_Wait_Time+"> seconds wait,  please refer screen shot >> ViewNmae_Fail"+fnsScreenShot_Date_format());
								}			
							}
						}
					}
					
					for(WebElement dd_value:DDobjectlists){
						if(dd_value.getText().contains(ViewName)){

							Actions action = new Actions(driver);
							action.moveToElement(dd_value).click(dd_value).build().perform();
							
							fnsLoading_Progressing_wait(2);
														
							fnsApps_Report_Logs("PASSED == Click done on View Name ( "+ViewName+" ) from MyView/SharedView Menu list on '"+ViewName.split("-")[0].toUpperCase()+"' Popup.");
							View_Found_into_Menu_DD=true;
							break;
						}
					}
					if( View_Found_into_Menu_DD==false ){
						for(int Displaytry=1; Displaytry<=5; Displaytry++){
							try{
								if(fnsGet_OR_Listings_ObjectX("ActiveView_Pagination_Next_link").isDisplayed()){
									fnsGet_OR_Listings_ObjectX("ActiveView_Pagination_Next_link").click();
									fnsLoading_Progressing_wait(2);
									Shared_My_View_Link_Click_Counter = 1;
									View_List_Paginaton_NEXT_Link = true;
									break;	
								}else{
									View_List_Paginaton_NEXT_Link = false;
								}
							}catch(Throwable t){
								if(Displaytry==5){
									View_List_Paginaton_NEXT_Link = false;
									throw new Exception(Throwables.getStackTraceAsString(t));
								}else{
									Thread.sleep(500);
								}
							}		
						}
					}
					if( View_Found_into_Menu_DD == false && Shared_My_View_Link_Click_Counter!=1 && View_List_Paginaton_NEXT_Link == false){
						throw new Exception("FAILED == View is not exists into the MyView/SharedView Menu list on '"+ViewName.split("-")[0].toUpperCase()+"' Popup, please refer screen shot >> ViewNmae_Click_Fail"+fnsScreenShot_Date_format());
					}
				}	
			}
		}catch(NoSuchWindowException W){
			//isTestCasePass = false;
			throw new Exception(W.getMessage());			}
		catch(Throwable t) {
			//isTestCasePass = false;
			fnsTake_Screen_Shot("ViewNmae_Click_Fail");
			throw new Exception("View Name ( "+ViewName+" ) : "+Throwables.getStackTraceAsString(t));
		}

	}

	
	
	
	
	
	public void fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(boolean Type_true_False,  String Field_Type_like_input_or_TextArea, String LabelName, String Value) throws Throwable{
		try{
			String field_Type = "";
			String Input_Field_Xpath = "";
			String Label_xpath = "";
			if(Field_Type_like_input_or_TextArea.contains("_")){
				field_Type = Field_Type_like_input_or_TextArea.split("_")[0].trim();
				Field_Type_like_input_or_TextArea = Field_Type_like_input_or_TextArea.split("_")[1].trim();
				Input_Field_Xpath = "//"+field_Type+"[text()='"+LabelName+"']/following::"+Field_Type_like_input_or_TextArea.toLowerCase()+"[1]";
				Label_xpath = "(//"+field_Type+"[text()='"+LabelName+"'])[1]";
			}else{
				Input_Field_Xpath = "//label[text()='"+LabelName+"']/following::"+Field_Type_like_input_or_TextArea.toLowerCase()+"[1]";
				Label_xpath = "(//label[text()='"+LabelName+"'])[1]";
			}
			
		
			for(int i=0; i<=Listings_Element_Max_Wait_Time; i++){
				try{
					if(driver.findElements(By.xpath(Input_Field_Xpath)).size()>0){
						break;
					}else{
						Thread.sleep(1000);
					}
				}catch(Throwable t){
					if(i==Listings_Element_Max_Wait_Time){
						fnsTake_Screen_Shot("Field_Not_Coming");
						throw new Exception("FAILED '"+LabelName+"' field is not coming, please refer the screen shot >> Field_Not_Coming"+fnsScreenShot_Date_format());
					}else{
						Thread.sleep(1000);
					}					
				}
			}			
			TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(Input_Field_Xpath);
			
			
			
			if(Type_true_False==true){
				TestSuiteBase_MonitorPlan.WithOut_OR_fnClear(Input_Field_Xpath);
				TestSuiteBase_MonitorPlan.WithOut_OR_fnClear(Input_Field_Xpath);
				TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(Input_Field_Xpath).sendKeys(Value);
				TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element_and_Click(Label_xpath);
				Thread.sleep(500);
			}else{
				String Input_Entered_Value = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(Input_Field_Xpath).getAttribute("value").trim();
				
				if(Value.equalsIgnoreCase("")){
					try{
						assert Input_Entered_Value.length()>0: "FAILED == Data is not displayed into the '"+LabelName+"' field,  please refer the screen shot >> Empty_Field"+fnsScreenShot_Date_format();
						fnsApps_Report_Logs("PASSED == Data is displayed into the '"+LabelName+"' field.  Automation");
					}catch(Throwable t){
						fnsTake_Screen_Shot("Empty_Field");
						throw new Exception(Throwables.getStackTraceAsString(t));	
					}
				}else{			
					try{
						assert Input_Entered_Value.equalsIgnoreCase(Value): "FAILED == '"+LabelName+"' field value <"+Input_Entered_Value+"> is NOT matched with expected value <"+Value+">,  please refer the screen shot >> Expected_Value_Not_Match"+fnsScreenShot_Date_format();
						fnsApps_Report_Logs("PASSED == '"+LabelName+"' field value <"+Input_Entered_Value+"> is matched with expected value <"+Value+">.  Automation");
					}catch(Throwable t){
						fnsTake_Screen_Shot("Expected_Value_Not_Match");
						throw new Exception(Throwables.getStackTraceAsString(t));	
					}
				}
			}
		}catch(NoSuchWindowException W){
			throw new Exception(W.getMessage());			}
		catch(Throwable t) {
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}
	
	
	
	public void fnsVerify_FieldValue_Text_by_OR(String ValueFetch_Text_or_ValueAttribute, String LabelName, String Xpath, String Value) throws Throwable{
		try{
			String Input_Entered_Value =  "";
			for(int i=0; i<=Listings_Element_Max_Wait_Time; i++){
				try{
					if(driver.findElements(By.xpath(Xpath)).size()>0){
						break;
					}else{
						Thread.sleep(1000);
					}
				}catch(Throwable t){
					if(i==Listings_Element_Max_Wait_Time){
						fnsTake_Screen_Shot("Field_Not_Coming");
						throw new Exception("FAILED '"+LabelName+"' field is not coming, please refer the screen shot >> Field_Not_Coming"+fnsScreenShot_Date_format());
					}else{
						Thread.sleep(1000);
					}					
				}
			}			
			TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(Xpath);
			
			
			if(ValueFetch_Text_or_ValueAttribute.equalsIgnoreCase("Value")){
				Input_Entered_Value = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(Xpath).getAttribute("value").trim();
			}else {
				Input_Entered_Value = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(Xpath).getText().trim();
			}
			try{
				assert Input_Entered_Value.equalsIgnoreCase(Value): "FAILED == '"+LabelName+"' field value <"+Input_Entered_Value+"> is NOT matched with expected value <"+Value+">,  please refer the screen shot >> Expected_Value_Not_Match"+fnsScreenShot_Date_format();
				fnsApps_Report_Logs("PASSED == '"+LabelName+"' field value <"+Input_Entered_Value+"> is matched with expected value <"+Value+">.  Automation");
			}catch(Throwable t){
				fnsTake_Screen_Shot("Expected_Value_Not_Match");
				throw new Exception(Throwables.getStackTraceAsString(t));	
			}
		}catch(NoSuchWindowException W){
			throw new Exception(W.getMessage());			}
		catch(Throwable t) {
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}
	
	
	
	
	public void fnsWait_and_Click_on_RadioButton_by_LabelName(boolean Will_Select_Radio_bttn, String LabelName, String Radio_bttn_Seq) throws Throwable{
		try{
			String Radio_bttn_Xpath = "//label[text()='"+LabelName+"']/following::input["+Radio_bttn_Seq+"]";	
			for(int i=0; i<=Listings_Element_Max_Wait_Time; i++){
				try{
					if(driver.findElements(By.xpath(Radio_bttn_Xpath)).size()>0){
						break;
					}else{
						Thread.sleep(1000);
					}
				}catch(Throwable t){
					if(i==Listings_Element_Max_Wait_Time){
						fnsTake_Screen_Shot("RadioBttn_Not_Coming");
						throw new Exception("FAILED "+LabelName+"'s RADIO button is not coming, please refer the screen shot >> RadioBttn_Not_Coming"+fnsScreenShot_Date_format());
					}else{
						Thread.sleep(1000);
					}					
				}
			}			
			TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(Radio_bttn_Xpath);
			
			if(Will_Select_Radio_bttn){
				TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(Radio_bttn_Xpath);
				fnsLoading_Progressing_wait(1);
			}else{
				try{
					String Radio_bttn_SelectAttribute = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(Radio_bttn_Xpath).getAttribute("style");
					assert ( !(Radio_bttn_SelectAttribute==null) ):LabelName+"'s RADIO button is not coming as SELECTED, please refer the screen shot >> Radio_bttn_NOT_Selected"+fnsScreenShot_Date_format();
					fnsApps_Report_Logs("PASSED == successfully verified that "+LabelName+"'s RADIO button is oming as SELECTED.  Automation");
				}catch(Throwable t) {
					fnsTake_Screen_Shot("Radio_bttn_NOT_Selected");
					throw new Exception(Throwables.getStackTraceAsString(t));
				}			
			}	
		}catch(NoSuchWindowException W){
			throw new Exception(W.getMessage());			}
		catch(Throwable t) {
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		
		
	}
	public void fns_Assert_equalsIgnoreCase_Without_OR(String BY__Text_or_Value, String Field_Name, String Xpath_Without_OR, String Value) throws Throwable{
		try{
			String Field_Value = "";
			for(int i=0; i<=Listings_Element_Max_Wait_Time; i++){
				try{
					if(driver.findElements(By.xpath(Xpath_Without_OR)).size()>0){
						break;
					}else{
						Thread.sleep(1000);
					}
				}catch(Throwable t){
					if(i==Listings_Element_Max_Wait_Time){
						fnsTake_Screen_Shot("Field_Not_Coming");
						throw new Exception("FAILED '"+Field_Name+"' field is not coming, please refer the screen shot >> Field_Not_Coming"+fnsScreenShot_Date_format());
					}else{
						Thread.sleep(1000);
					}					
				}
			}			
			TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(Xpath_Without_OR);
			WebElement Field_Element = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(Xpath_Without_OR);
			
			if(BY__Text_or_Value.equalsIgnoreCase("Value")){
				Field_Value = Field_Element.getAttribute("value").trim();
			}else{
				Field_Value = Field_Element.getText().trim();
			}
			try{
				assert Field_Value.equalsIgnoreCase(Value): "FAILED == '"+Field_Name+"' field value <"+Field_Value+"> is NOT matched with expected value <"+Value+">,  please refer the screen shot >> Expected_Value_Not_Match"+fnsScreenShot_Date_format();
				fnsApps_Report_Logs("PASSED == '"+Field_Name+"' field value <"+Field_Value+"> is matched with expected value <"+Value+">.");
			}catch(Throwable t){
				fnsTake_Screen_Shot("Expected_Value_Not_Match");
				throw new Exception(Throwables.getStackTraceAsString(t));	
			}
		}catch(NoSuchWindowException W){
			throw new Exception(W.getMessage());			}
		catch(Throwable t) {
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}	
	
	
	public void fnsCalendar_Pick_TodayDate_by_LabelName(boolean Verify_TodayDate_Defaulted_into_the_Calendar, boolean Will_SelectDate, String LabelName, Integer Date) throws Throwable{
		try{
			String Calendar_DatePick_Format_Date = fnsTime_Return_DemandedFormatCalendar("EEEE, MMMM dd, yyyy", 0, 0, Date, 0, 0);
			String Calendar_Button_Xpath = "//label[text()='"+LabelName+"']/following::span[@class='k-icon k-i-calendar'][1]";
//			String Calendar_Day_Xpath = "//label[text()='"+LabelName+"']/following::td[contains(@class, 'k-today')]/a";
			String Calendar_Day_Xpath = "//label[text()='"+LabelName+"']/following::td/a[@title='"+Calendar_DatePick_Format_Date+"']";
			String Calendar_Footer_xpath = "//div[@class='k-footer']/a[@title='"+Calendar_DatePick_Format_Date+"']";
			String Value = fnsTime_Return_DemandedFormatCalendar("dd-MM-yyyy", 0, 0, Date, 0, 0);
			for(int i=0; i<=Listings_Element_Max_Wait_Time; i++){
				try{
					if(driver.findElements(By.xpath(Calendar_Button_Xpath)).size()>0){
						break;
					}else{
						Thread.sleep(1000);
					}
				}catch(Throwable t){
					if(i==Listings_Element_Max_Wait_Time){
						fnsTake_Screen_Shot("Field_Not_Coming");
						throw new Exception("FAILED == Calendar button is not coming for '"+LabelName+"' field, please refer the screen shot >> Field_Not_Coming"+fnsScreenShot_Date_format());
					}else{
						Thread.sleep(1000);
					}					
				}
			}			
			TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(Calendar_Button_Xpath);
			TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element_and_Click(Calendar_Button_Xpath);
			Thread.sleep(500);
			if(Verify_TodayDate_Defaulted_into_the_Calendar){
				Calendar_Footer_xpath = fnsRetun_Xpath_of_First_Visible_Element_if_More_than_One_Coming(Calendar_Footer_xpath);				
				
				String Calendar_footer_Text = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(Calendar_Footer_xpath).getText().trim();
				try{
					assert Calendar_footer_Text.equalsIgnoreCase(Calendar_DatePick_Format_Date): "FAILED == Today date is not displayed as highlighted into the calendar,  please refer the screen shot >> TodayDate_Not_Highlighted"+fnsScreenShot_Date_format();
					fnsApps_Report_Logs("PASSED == Today date is displayed as highlighted into the calendar.  Automation");
				}catch(Throwable t){
					fnsTake_Screen_Shot("TodayDate_Not_Highlighted");
					throw new Exception(Throwables.getStackTraceAsString(t));	
				}
			}
			
			if(Will_SelectDate){
				fnsWait_Click_on_First_Visible_Element_if_More_than_One_Coming(Value, Calendar_Day_Xpath);
				Thread.sleep(500);
				TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element_and_Click("//label[text()='"+LabelName+"']");
				Thread.sleep(500);
				
				String Input_Field_Xpath = "//label[text()='"+LabelName+"']/following::input[1]";
				String Input_Entered_Value = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(Input_Field_Xpath).getAttribute("value").trim();
				try{
					assert Input_Entered_Value.equalsIgnoreCase(Value): "FAILED == '"+LabelName+"' field value <"+Input_Entered_Value+"> is NOT matched with expected value <"+Value+">,  please refer the screen shot >> Expected_Value_Not_Match"+fnsScreenShot_Date_format();
					fnsApps_Report_Logs("PASSED == '"+LabelName+"' field value <"+Input_Entered_Value+"> is matched with expected value <"+Value+">.  Automation");
				}catch(Throwable t){
					fnsTake_Screen_Shot("Expected_Value_Not_Match");
					throw new Exception(Throwables.getStackTraceAsString(t));	
				}
			}else{
				TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element_and_Click("//label[text()='"+LabelName+"']");
			}		
			
		}catch(NoSuchWindowException W){
			throw new Exception(W.getMessage());			}
		catch(Throwable t) {
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}
	public void fnsCalendar_Pick_TodayDate_by_LabelName_1(boolean Verify_TodayDate_Defaulted_into_the_Calendar, boolean Will_SelectDate, String LabelName, Integer Date) throws Throwable{
		try{
			String Calendar_DatePick_Format_Date = fnsTime_Return_DemandedFormatCalendar("EEEE, MMMM dd, yyyy", 0, 0, Date, 0, 0);
			String Calendar_Button_Xpath = "//label[text()='"+LabelName+"']/following::span[@class='k-icon k-i-calendar'][1]";
			String Calendar_Day_Xpath = "//label[text()='"+LabelName+"']/following::td/a[@title='"+Calendar_DatePick_Format_Date+"']";
			String Calendar_Footer_xpath = "//div[@class='k-footer']/a[@title='"+Calendar_DatePick_Format_Date+"']";
			String Value = fnsTime_Return_DemandedFormatCalendar("dd-MMM-yyyy", 0, 0, Date, 0, 0);
			for(int i=0; i<=Listings_Element_Max_Wait_Time; i++){
				try{
					if(driver.findElements(By.xpath(Calendar_Button_Xpath)).size()>0){
						break;
					}else{
						Thread.sleep(1000);
					}
				}catch(Throwable t){
					if(i==Listings_Element_Max_Wait_Time){
						fnsTake_Screen_Shot("Field_Not_Coming");
						throw new Exception("FAILED == Calendar button is not coming for '"+LabelName+"' field, please refer the screen shot >> Field_Not_Coming"+fnsScreenShot_Date_format());
					}else{
						Thread.sleep(1000);
					}					
				}
			}			
			TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(Calendar_Button_Xpath);
			TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element_and_Click(Calendar_Button_Xpath);
			Thread.sleep(500);
			if(Verify_TodayDate_Defaulted_into_the_Calendar){
				Calendar_Footer_xpath = fnsRetun_Xpath_of_First_Visible_Element_if_More_than_One_Coming(Calendar_Footer_xpath);				
				
				String Calendar_footer_Text = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(Calendar_Footer_xpath).getText().trim();
				try{
					assert Calendar_footer_Text.equalsIgnoreCase(Calendar_DatePick_Format_Date): "FAILED == Today date is not displayed as highlighted into the calendar,  please refer the screen shot >> TodayDate_Not_Highlighted"+fnsScreenShot_Date_format();
					fnsApps_Report_Logs("PASSED == Today date is displayed as highlighted into the calendar.  Automation");
				}catch(Throwable t){
					fnsTake_Screen_Shot("TodayDate_Not_Highlighted");
					throw new Exception(Throwables.getStackTraceAsString(t));	
				}
			}
			
			if(Will_SelectDate){
				fnsWait_Click_on_First_Visible_Element_if_More_than_One_Coming(Value, Calendar_Day_Xpath);
				Thread.sleep(500);
				TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element_and_Click("//label[text()='"+LabelName+"']");
				Thread.sleep(500);
				
				String Input_Field_Xpath = "//label[text()='"+LabelName+"']/following::input[1]";
				String Input_Entered_Value = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(Input_Field_Xpath).getAttribute("value").trim();
				try{
					assert Input_Entered_Value.equalsIgnoreCase(Value): "FAILED == '"+LabelName+"' field value <"+Input_Entered_Value+"> is NOT matched with expected value <"+Value+">,  please refer the screen shot >> Expected_Value_Not_Match"+fnsScreenShot_Date_format();
					fnsApps_Report_Logs("PASSED == '"+LabelName+"' field value <"+Input_Entered_Value+"> is matched with expected value <"+Value+">.  Automation");
				}catch(Throwable t){
					fnsTake_Screen_Shot("Expected_Value_Not_Match");
					throw new Exception(Throwables.getStackTraceAsString(t));	
				}
			}else{
				TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element_and_Click("//label[text()='"+LabelName+"']");
			}		
			
		}catch(NoSuchWindowException W){
			throw new Exception(W.getMessage());			}
		catch(Throwable t) {
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}
			
	
	//Verify View exists into the view list and conditional click on it.
	public void fncVerify_View_Deleted_Successfully(String ViewName) throws Throwable{
		try{
			boolean View_Deleted_Successfully = true;
			fnsGet_Element_Enabled("ActiveView_DD");
			for(int i=0; i<5; i++){
				if( driver.findElement(By.xpath(OR_Listings.getProperty("ActiveView_ShareView_Link"))).isDisplayed() ){
					break;
				}else{
					TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element_and_Click(OR_Listings.getProperty("ActiveView_DD"));
					Thread.sleep(1000);
				}
			}
			
			Thread.sleep(500);
			List<WebElement> Menu_DDobjectlists = null;
			
			//Start counter with one if want to clicking on shared menu list
			for(int Shared_My_View_Link_Click_Counter = 2; Shared_My_View_Link_Click_Counter<=2; Shared_My_View_Link_Click_Counter++){
				
				if(Shared_My_View_Link_Click_Counter==1){
					fnsGet_Element_Enabled("ActiveView_ShareView_Link");
					fnsWait_and_Click("ActiveView_ShareView_Link");
				//	Thread.sleep(500);
					fnsGet_Element_Enabled("ActiveView_SharedView_DD_Data_Div");
					Menu_DDobjectlists=fnsGet_OR_Listings_ObjectX("ActiveView_SharedView_DD_Data_Div").findElements(By.tagName("div"));
				}else{
					fnsGet_Element_Enabled("ActiveView_MyView_Link");
					fnsWait_and_Click("ActiveView_MyView_Link");
					Thread.sleep(500);
					fnsGet_Element_Enabled("ActiveView_MyView_DD_Data_Div");
					Menu_DDobjectlists=fnsGet_OR_Listings_ObjectX("ActiveView_MyView_DD_Data_Div").findElements(By.tagName("div"));
				}
				
				for(WebElement Menu_Element:Menu_DDobjectlists){
					if(Menu_Element.getText().contains(ViewName)){
						View_Deleted_Successfully =  false;
						throw new Exception("FAILED ==  After deleting View, It is still exists into the MyView/SharedView  Menu list on '"+ViewName.split("-")[0].toUpperCase()+"' screen, please refer screen shot >> View_Delete_Fail"+fnsScreenShot_Date_format());
					}
				}
			}
			
			if(View_Deleted_Successfully){
				fnsGet_Element_Enabled("ActiveView_Header_Label_Link");
				fnsWait_and_Click_Through_JS(OR_Listings.getProperty("ActiveView_Header_Label_Link"));
				/*for(int i=0; i<=5; i++){
					try{
						fnsGet_OR_Listings_ObjectX("ActiveView_Header_Label_Link").click();
						break;
					}catch(Throwable t){
						Thread.sleep(1000);
						if(i==5){
							fnsGet_OR_Listings_ObjectX("ActiveView_MyView_FirstView_from_List").click();
						}
					}
				}*/
				fnsLoading_Progressing_wait(1);
				fnsApps_Report_Logs("PASSED == View Name ( "+ViewName+" ) is deleted successfully form MyView/SharedView Menu list on '"+ViewName.split("-")[0].toUpperCase()+"' screen [means It is not any more exists into the MyView/SharedView list].");
			}
		
		}catch(NoSuchWindowException W){
			//isTestCasePass = false;
			throw new Exception(W.getMessage());			}
		catch(Throwable t) {
			//isTestCasePass = false;
			fnsTake_Screen_Shot("View_Delete_Fail");
			throw new Exception("View Name ( "+ViewName+" ) : "+Throwables.getStackTraceAsString(t));
		}

	}



	//function to select drop down value
	public void fnsDD_value_Select_By_MatchingText_WithoutClick(String ddListXpathKey, String TagName, String Value) throws Exception {		
		boolean ValueNotMatched=true;
		try{
			fnsGet_Element_Enabled(ddListXpathKey);
			List<WebElement> DDobjectlists=fnsGet_OR_Listings_ObjectX(ddListXpathKey).findElements(By.tagName(TagName));
				for(WebElement dd_value:DDobjectlists){
					if(dd_value.getText().equalsIgnoreCase(Value)){
						ValueNotMatched=false;
						Actions act = new Actions(driver);
						act.moveToElement(dd_value).click().build().perform();
						//dd_value.click();						
						break;}
				}
			if(ValueNotMatched==true){
				throw new Exception("Excel value is not matched with DropDown Value.");
			}	
			fnsApps_Report_Logs("PASSED == select value [ "+Value+" ] from drop down is done, having xpath >>  " + ddListXpathKey);
		}catch(NoSuchWindowException W){
			isTestCasePass = false;
			throw new Exception(W.getMessage());			}
		catch(Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot("DdValueSelectFail");
			fnsApps_Report_Logs("FAILED == Unable to select value from drop down [ " + ddListXpathKey +" ],plz see screenshot [ DdValueSelectFail" +  fnsScreenShot_Date_format() + " ]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(t).trim());
			throw new Exception("FAILED == Unable to select value from drop down [ " + ddListXpathKey +" ],plz see screenshot [ DdValueSelectFail" +  fnsScreenShot_Date_format() + " ]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(t).trim());}
	}




	//Verify View exists into the view list then open and Delete it. Verify Deleted successfully
	public String fncVerify_View_Display_Open_and_Delete_it(Integer Share_is_1_and_MyView_is_2, String ViewName, String Delete_Bttn_Xpath, String Remove_Bttn_Xpath) throws Throwable{
		try{
			String View_Exsits = fncMyView_SharedView_NewlyCreatedView_Display_and_Conditional_Click(Share_is_1_and_MyView_is_2, false, ViewName, "Yes");
					
			if(View_Exsits.equalsIgnoreCase("my view")){
				fnsGet_Element_Enabled(Delete_Bttn_Xpath);
				fnsWait_and_Click(Delete_Bttn_Xpath);	
			}
			
			if(View_Exsits.equalsIgnoreCase("shared view")){
				fnsGet_Element_Enabled(Remove_Bttn_Xpath);
				fnsWait_and_Click(Remove_Bttn_Xpath);	
			}
			
			if( !(View_Exsits.equalsIgnoreCase("View Not Exists")) ){
				boolean View_Simple_Delete_Popup = false;
				boolean View_SelectDefault_Delete_Popup = false;
				
				for(int i=0; i<=60; i++){
					try{
						if(driver.findElements(By.xpath(OR_Listings.getProperty("Model_Popup_DefaultViewSelect_Delete_Bttn"))).size()>0){
							View_SelectDefault_Delete_Popup = true;
							break;
						}else if(driver.findElement(By.xpath(OR_Listings.getProperty("Model_Popup_Delete_Bttn"))).isDisplayed()){
							System.out.println(driver.findElements(By.xpath(OR_Listings.getProperty("Model_Popup_Delete_Bttn"))).size());
							View_Simple_Delete_Popup = true;
							break;						
						}else{
							Thread.sleep(2000);
						}						
					}catch(Throwable t){
						if(i==60){
							fnsTake_Screen_Shot("DeleteView_Popup_not_Coming");
							throw new Exception("FAILED == 'Delete View' Popup is not coming to delete view, after 120 seconds wait, please refer the screen shot >> DeleteView_Popup_not_Coming"+fnsScreenShot_Date_format());
						}else{
							fnsLoading_Progressing_wait(2);
						}			
					}					
				}
				
				
				if(View_Simple_Delete_Popup){
					Thread.sleep(2000);
					boolean Click_and_Message_Verify = false;
					for(int c=1; c<=10; c++){
						try{
							if(fnsGet_OR_Listings_ObjectX("Model_Popup_Delete_Bttn").isDisplayed()){
								fnsGet_Element_Displayed("Model_Popup_Delete_Bttn");
								fnsWait_and_Click("Model_Popup_Delete_Bttn");								
							}
							fns_Verify_Success_message_coming_OR_Error_message_Coming(false, 60,  "Deleted Successfully", 15);
							Click_and_Message_Verify = true;
							break;
						}catch(Throwable t){
							if(c==10){
								fns_Verify_Success_message_coming_OR_Error_message_Coming(true, 2,  "Deleted Successfully", 15);
							}else{
								Thread.sleep(1000);
							}
						}
					}
					if( (Click_and_Message_Verify==false) ) { 
						fnsTake_Screen_Shot("Delete_Not_Working");
						throw new Exception("FAILED == Deleteing of view functionality is not working after 10 attacmpts, please refer the screen shot >> Delete_Not_Working"+fnsScreenShot_Date_format());
					}
				}else if(View_SelectDefault_Delete_Popup){
					fnsLoading_Progressing_wait(1);					
					String SharedView_FirstView_Text = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(OR_Listings.getProperty("Model_Popup_DefaultViewSelect_SharedView_First_Row")).getAttribute("title").toLowerCase().trim();
					if( !(SharedView_FirstView_Text.contains(ViewName.toLowerCase())) && !(SharedView_FirstView_Text.equals("")) ){
						fnsWait_and_Click_Through_JS(OR_Listings.getProperty("Model_Popup_DefaultViewSelect_SharedView_First_Row"));
					}else{
						fnsWait_and_Click_Through_JS(OR_Listings.getProperty("Model_Popup_DefaultViewSelect_MyView_Header"));
						Thread.sleep(2000);
						String MyView_FirstView_Text = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(OR_Listings.getProperty("Model_Popup_DefaultViewSelect_MyView_First_Row")).getAttribute("title").toLowerCase().trim();
						if( !(MyView_FirstView_Text.contains(ViewName.toLowerCase())) && !(MyView_FirstView_Text.equals("")) ){
							fnsWait_and_Click_Through_JS(OR_Listings.getProperty("Model_Popup_DefaultViewSelect_MyView_First_Row"));
						}else{
							fnsTake_Screen_Shot("View_Not_Exists_To_Make_Default");
							throw new Exception ("FAILED == there are no view exists to make default view, please refer the screen shot >> View_Not_Exists_To_Make_Default"+fnsScreenShot_Date_format());
						}
					}
					fnsGet_Element_Enabled("Model_Popup_DefaultViewSelect_Delete_Bttn");
					fnsWait_and_Click("Model_Popup_DefaultViewSelect_Delete_Bttn");	
					fns_Verify_Success_message_coming_OR_Error_message_Coming(true, 60,  "Deleted Successfully", 15);
				}					
				
				
				
				/*fnsGet_Element_Enabled("Model_Popup_OK_Bttn");
				fnsLoading_Progressing_on_Popup_wait_for_Popup(1);
				fnsWait_and_Click("Model_Popup_OK_Bttn");
				fnsLoading_Progressing_wait(2);	
				fnsLoading_Progressing_wait(2);	
				fnsLoading_Progressing_wait(2);	*/			
				
				fncVerify_View_Deleted_Successfully(ViewName);
			}
			
			return View_Exsits;
		}catch(NoSuchWindowException W){
			throw new Exception(W.getMessage());			}
		catch(Throwable t) {
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}



	
	
	
	
		
		
		
//Verify Column value exists into the "Selected Field" DD if it's not exists then move it into the DD from "Search Fields" DD.		
	public void fnsCreateView_Step3_Verify_ColumnExists_into_DD_ifNot_Then_Select(String ColumnName_NoCase) throws Throwable{
		boolean SelectedFields_DD_ColumnValue_Not_Matched = true;
		boolean SearchFields_DD_ColumnValue_Not_Matched = true;
		try{
			fnsGet_Element_Enabled("CreateView_Step3_SelectedFields_Multi_DD");
			TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element(OR_Listings.getProperty("CreateView_Step3_SelectedFields_Multi_DD"));
			List<WebElement> DDobjectlists1=fnsGet_OR_Listings_ObjectX("CreateView_Step3_SelectedFields_Multi_DD").findElements(By.tagName("div"));
			for(WebElement dd_value : DDobjectlists1){
				if( (dd_value.getText().toLowerCase().trim()).equals(ColumnName_NoCase.toLowerCase().trim())){
					fnsApps_Report_Logs("PASSED == Step 3 : Column < "+ColumnName_NoCase+" > exists into the 'Selected Fields' multi select drop down.");
					SelectedFields_DD_ColumnValue_Not_Matched=false;
					break;
				}				
			}
			
			
			if(SelectedFields_DD_ColumnValue_Not_Matched==true){
				fnsGet_Element_Enabled("CreateView_Step3_SearchFields_Multi_DD");
				List<WebElement> DDobjectlists2=fnsGet_OR_Listings_ObjectX("CreateView_Step3_SearchFields_Multi_DD").findElements(By.tagName("div"));
				for(WebElement dd_value:DDobjectlists2){
					if( (dd_value.getText().toLowerCase().trim()).equals(ColumnName_NoCase.toLowerCase().trim())){
						Thread.sleep(500);
						dd_value.click();
						Thread.sleep(250);
						fnsGet_Element_Enabled("CreateView_Step3_DD_RightSelected_bttn");
						fnsWait_and_Click("CreateView_Step3_DD_RightSelected_bttn");
						fnsApps_Report_Logs("PASSED == Step 3 : Column < "+ColumnName_NoCase+" > exists into the 'Search Fields' multi select drop down and moved to 'Selected Fields' multi select drop down.");
						SearchFields_DD_ColumnValue_Not_Matched=false;
						break;
					}				
				}
				
				if(SearchFields_DD_ColumnValue_Not_Matched==true){
					throw new Exception("FAILED == Create View > Step 3 : Column < "+ColumnName_NoCase+" > is not found."+
										" NEITHER into 'Search Fields' multi select drop down NOR into 'Selected Fields' multi select drop down, Please refer screen shot >> Step3_Column_"+ColumnName_NoCase+"Fail_"+fnsScreenShot_Date_format());
				}
					
			}	
		
		}catch(NoSuchWindowException W){
			//isTestCasePass = false;
			throw new Exception(W.getMessage());			}
		catch(Throwable t) {
				//isTestCasePass = false;
				fnsTake_Screen_Shot("Step3_Column_"+ColumnName_NoCase+"Fail_");
				throw new Exception(Throwables.getStackTraceAsString(t));
			}
		
	}
/*	
	//Verify success/error message coming
		public void fns_Verify_Success_message_coming_OR_Error_message_Coming(boolean TakeScreenShot, long WaitTime, String Successs_Message_Text, Integer MessageLength) throws Throwable{
			try{
			
				TimeOut = 0;
				ErrorMsgText = "";
				SuccessMsgText = "";
				Integer Success_Element_Count = 0;
				for(int wait=1; wait<=(WaitTime*2); wait++){
					boolean Validation_Message_Found = false;
					Success_Element_Count = driver.findElements(By.xpath(OR_Listings.getProperty("Listings_Success_Message"))).size();
					System.out.println("Success_Element_Count = "+Success_Element_Count);
					if((Success_Element_Count>0) ){
						for(int Success_Ele_Count=Success_Element_Count; Success_Ele_Count>=1; Success_Ele_Count--){
							String Success_Ele_Xpath = "("+OR_Listings.getProperty("Listings_Success_Message")+")["+Success_Ele_Count+"]";
							
							for(int i=1; i<=5; i++){
								try{
									if(driver.findElement(By.xpath(Success_Ele_Xpath)).isDisplayed()){
										SuccessMsgText = driver.findElement(By.xpath(Success_Ele_Xpath)).getText().trim();
										System.out.println("Success TEXT  = "+SuccessMsgText);
									}
								}catch(Throwable t){
									//nothing to do
								}
								if(SuccessMsgText.length()>MessageLength){
									Validation_Message_Found = true;
									break;
								}else{
									Thread.sleep(100);
								}
							}
							
							if(Validation_Message_Found==true){
								assert (SuccessMsgText.toLowerCase().contains(Successs_Message_Text.toLowerCase())):"FAILED == Success message is not coming, please refer screenshot >>Success_Message_Fail" + fnsScreenShot_Date_format()+"Text >> "+SuccessMsgText;
								fnsApps_Report_Logs("PASSED == Success ( "+Successs_Message_Text+" ) message is coming.");
								break;
							}
						}
					} 
				
					if( Validation_Message_Found==false || (driver.findElements(By.xpath(OR_Listings.getProperty("Listings_Error_Image"))).size()>0) ){
						Integer Error_Element_Count = driver.findElements(By.xpath(OR_Listings.getProperty("Listings_Error_Message"))).size();
						if(Error_Element_Count>0){
							for(int i=Error_Element_Count; i>=1; i--){
								String Error_message_Xpath = "("+OR_Listings.getProperty("Listings_Error_Message")+")["+i+"]";
								try{
									if(driver.findElement(By.xpath(Error_message_Xpath)).isDisplayed()){
										ErrorMsgText = driver.findElement(By.xpath(Error_message_Xpath)).getText().trim();
									}
								}catch(Throwable t){
									//nothing to do
								}
								if(ErrorMsgText.length()>5){
									throw new Exception("FAILED == Application display Error < "+ErrorMsgText+" >, please refer screen shot >> Success_Message_Fail"+fnsScreenShot_Date_format());
								}
							}	
						}	
											
					}
					
					if(Validation_Message_Found==true){
						//nothing to do
					}else{
						Thread.sleep(250);
					}
					
								
					TimeOut = wait;	
					
					if(Validation_Message_Found==true){
						break;
					}
				}
				if(TimeOut.equals(WaitTime*2) ){
					throw new Exception("FAILED == After < "+(WaitTime) 
							+" > seconds wait, NIETHER 'success' message display NOR any 'error' message display, please refer screenshot >>Success_Message_Fail" + fnsScreenShot_Date_format());
				}	
				if(Success_Element_Count==0 && TimeOut.equals(WaitTime*2) ){
					throw new Exception("FAILED == Success Messge <"+Successs_Message_Text+"> is not coming, please refer screen shot >> Success_Message_Fail"+fnsScreenShot_Date_format());
				}
				if(TakeScreenShot==true){
					fnsLoading_Progressing_wait(3);
				}else{
					Thread.sleep(2000);
				}
			}catch(Throwable t) {
				if(TakeScreenShot==true){
					fnsTake_Screen_Shot("Success_Message_Fail");
				}
				fnsLoading_Progressing_wait(1);
				throw new Exception(Throwables.getStackTraceAsString(t));
			}
		
			
		}
			
	*/
	
	
	
	
	/*//Requried change (fnsLoading_Progressing_wait(1);) into the method fncNavigation_Verify_Application_Navigated_To_ViewScreen -- NSFOII-3249 
	//Commented on 14.2.2018 as new success message through unexpected_loading error, below loading method will not captured unknown error.
	//Verify success/error message coming
	public void fns_Verify_Success_message_coming_OR_Error_message_Coming(boolean TakeScreenShot, long WaitTime, String Successs_Message_Text, Integer MessageLength) throws Throwable{
		try{
			ErrorMsgText = "";
			SuccessMsgText = "";
			TimeOut = 0;
			Integer Success_Element_Count = 0;
			for(int wait=1; wait<=(WaitTime*2); wait++){
				boolean Validation_Message_Found = false;
				Success_Element_Count = driver.findElements(By.xpath(OR_Listings.getProperty("Listings_Success_Message"))).size();
				System.out.println("Success_Element_Count = "+Success_Element_Count);
				if((Success_Element_Count>0) ){
					for(int Success_Ele_Count=Success_Element_Count; Success_Ele_Count>=1; Success_Ele_Count--){	
						String Success_Ele_Xpath = "("+OR_Listings.getProperty("Listings_Success_Message")+")["+Success_Ele_Count+"]";
						for(int i=1; i<=5; i++){
							try{
								SuccessMsgText = driver.findElement(By.xpath(Success_Ele_Xpath)).getText().trim();
								System.out.println("Success TEXT  = "+SuccessMsgText);
							}catch(Throwable t){
								//nothing to do
							}
							if(SuccessMsgText.length()>MessageLength){
								Validation_Message_Found = true;
								break;
							}else{
								Thread.sleep(100);
							}
						}
						if(Validation_Message_Found==true){
							assert (SuccessMsgText.toLowerCase().contains(Successs_Message_Text.toLowerCase())):"FAILED == Success message is not coming, please refer screenshot >>Success_Message_Fail" + fnsScreenShot_Date_format()+"Text >> "+SuccessMsgText;
							fnsApps_Report_Logs("PASSED == Success ( "+Successs_Message_Text+" ) message is coming.");
							break;
						}
					}
				} 
			
				if( Validation_Message_Found==false || (driver.findElements(By.xpath(OR_Listings.getProperty("Listings_Error_Image"))).size()>0) ){
					Integer Error_Element_Count = driver.findElements(By.xpath(OR_Listings.getProperty("Listings_Error_Message"))).size();
					if(Error_Element_Count>0){
						for(int i=Error_Element_Count; i>=1; i--){
							String Error_message_Xpath = "("+OR_Listings.getProperty("Listings_Error_Message")+")["+i+"]";
							try{
								ErrorMsgText = driver.findElement(By.xpath(Error_message_Xpath)).getText().trim();
							}catch(Throwable t){
								//nothing to do
							}
							if(ErrorMsgText.length()>5){
								throw new Exception("FAILED == Application display Error < "+ErrorMsgText+" >, please refer screen shot >> Success_Message_Fail"+fnsScreenShot_Date_format());
							}
						}	
					}	
										
				}
				
				if(Validation_Message_Found==true){
					//nothing to do
				}else{
					Thread.sleep(250);
				}
				
							
				TimeOut = wait;	
				if(TimeOut.equals(WaitTime*2) ){
					throw new Exception("FAILED == After < "+(WaitTime/2) 
							+" > seconds wait, NIETHER 'success' message display NOR any 'error' message display, please refer screenshot >>Success_Message_Fail" + fnsScreenShot_Date_format());
				}	
				if(Validation_Message_Found==true){
					break;
				}
			}
			if(Success_Element_Count==0 && TimeOut.equals(WaitTime*2) ){
				throw new Exception("FAILED == Success Messge <"+Successs_Message_Text+"> is not coming, please refer screen shot >> Success_Message_Fail"+fnsScreenShot_Date_format());
			}
			if(TakeScreenShot==true){
				//Commented on 14.2.2018 as new success message through unexpected_loading error, below loading method will not captured unknown error.
				fnsLoading_Progressing_wait(3);
				//fnsLoading_Progressing_on_Popup_wait_for_Popup(3);
			}else{
				Thread.sleep(2000);
			}
		}catch(Throwable t) {
			if(TakeScreenShot==true){
				fnsTake_Screen_Shot("Success_Message_Fail");
			}
			//Requried change (fnsLoading_Progressing_wait(1);) into the method fncNavigation_Verify_Application_Navigated_To_ViewScreen
			//Commented on 14.2.2018 as new success message through unexpected_loading error, below loading method will not captured unknown error.
			fnsLoading_Progressing_wait(1);
			//fnsLoading_Progressing_on_Popup_wait_for_Popup(1);
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}
		*/
	

	//Verify success/error message coming
	public String fns_Verify_Success_message_coming_OR_Error_message_Coming(boolean TakeScreenShot, Integer MinWaitTime, String Successs_Message_Text, Integer MessageLength) throws Throwable{
		try{
			ErrorMsgText = "";
			SuccessMsgText = "";
			TimeOut = 0;
			Integer WaitTime = MinWaitTime*2;
			String Success_Ele_Xpath = "";
			Integer Success_Element_Count = 0;
			boolean Validation_Message_Found = false;
			for(int wait=1; wait<=(WaitTime); wait++){
				Validation_Message_Found = false;
				Success_Element_Count = driver.findElements(By.xpath(OR_Listings.getProperty("Listings_Success_Message"))).size();
				System.out.println("Success_Element_Count = "+Success_Element_Count);
				if((Success_Element_Count>0) ){
					for(int Success_Ele_Count=Success_Element_Count; Success_Ele_Count>=1; Success_Ele_Count--){	
						Success_Ele_Xpath = "("+OR_Listings.getProperty("Listings_Success_Message")+")["+Success_Ele_Count+"]";
						for(int i=1; i<=5; i++){
							try{
								SuccessMsgText = driver.findElement(By.xpath(Success_Ele_Xpath)).getText().trim();
								System.out.println("Success TEXT  = "+SuccessMsgText);
							}catch(Throwable t){
								//nothing to do
							}
							if(SuccessMsgText.length()>MessageLength){
								Validation_Message_Found = true;
								break;
							}else{
								Thread.sleep(100);
							}
						}
						if(Validation_Message_Found==true){
							assert (SuccessMsgText.toLowerCase().contains(Successs_Message_Text.toLowerCase())):"FAILED == Success message is not coming, please refer screenshot >>Success_Message_Fail" + fnsScreenShot_Date_format()+"Text >> "+SuccessMsgText;
							fnsApps_Report_Logs("PASSED == Success ( "+Successs_Message_Text+" ) message is coming.");
							break;
						}
					}
				} 
			
				if( Validation_Message_Found==false || (driver.findElements(By.xpath(OR_Listings.getProperty("Listings_Error_Image"))).size()>0) ){
					Integer Error_Element_Count = driver.findElements(By.xpath(OR_Listings.getProperty("Listings_Error_Message"))).size();
					if(Error_Element_Count>0){
						for(int i=Error_Element_Count; i>=1; i--){
							String Error_message_Xpath = "("+OR_Listings.getProperty("Listings_Error_Message")+")["+i+"]";
							try{
								ErrorMsgText = driver.findElement(By.xpath(Error_message_Xpath)).getText().trim();
							}catch(Throwable t){
								//nothing to do
							}
							if(ErrorMsgText.length()>5){
								throw new Exception("FAILED == Application display Error < "+ErrorMsgText+" >, please refer screen shot >> Success_Message_Fail"+fnsScreenShot_Date_format());
							}
						}	
					}	
										
				}
				
				if(Validation_Message_Found==true){
					//nothing to do
				}else{
					Thread.sleep(250);
				}
				
							
				TimeOut = wait;	
				if(TimeOut.equals(WaitTime) ){
					fnsApps_Report_Logs("FAILED == After < "+(MinWaitTime) 
							+" > seconds wait, NIETHER 'success' message display NOR any 'error' message display, please refer screenshot >>Success_Message_Fail" + fnsScreenShot_Date_format());
					throw new Exception("FAILED == After < "+(MinWaitTime) 
							+" > seconds wait, NIETHER 'success' message display NOR any 'error' message display, please refer screenshot >>Success_Message_Fail" + fnsScreenShot_Date_format());
				}	
				if(Validation_Message_Found==true){
					break;
				}
			}
			if(Success_Element_Count.equals(0) && (TimeOut.equals(WaitTime)) ){
				throw new Exception("FAILED == Success Messge <"+Successs_Message_Text+"> is not coming, please refer screen shot >> Success_Message_Fail"+fnsScreenShot_Date_format());
			}
			
			
			
			
			for(int i=1; i<=60; i++){
				try{
					if(driver.findElements(By.xpath(Success_Ele_Xpath)).size()>0){
						if(driver.findElement(By.xpath(Success_Ele_Xpath)).isDisplayed()){
							Thread.sleep(1000);
						}
					}else{
						break;
					}					
				}catch(Throwable t){
					Thread.sleep(1000);
				}
				if( (i==60) && (driver.findElements(By.xpath(Success_Ele_Xpath)).size()==0) ){
					throw new Exception("FAILED == Success Messge <"+Successs_Message_Text+"> is not getting disappear, after 60 seconds wait, please refer screen shot >> Success_Message_Fail"+fnsScreenShot_Date_format());
				}
			}
			if(Validation_Message_Found==false){
				System.out.println("FAILED == Success Messge <"+Successs_Message_Text+"> is not coming.");
				throw new Exception("FAILED == Success Messge <"+Successs_Message_Text+"> is not coming, please refer screen shot >> Success_Message_Fail"+fnsScreenShot_Date_format());
			}
			
			Thread.sleep(1000);
		}catch(Throwable t) {
			if(TakeScreenShot==true){
				fnsTake_Screen_Shot("Success_Message_Fail");
			}
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		return SuccessMsgText;
	}
			
	
	//Verify View exists into the view list then open and Delete it. Verify Deleted successfully
	public String fns_Verify_Success_or_Error_Message_on_ModelPopup_Generation(boolean TakeScreenShot, long WaitTime, String Success_Message, Integer Success_Message_Length) throws Throwable{
		try{
			boolean Validation_Message_Found = false;
			TimeOut = 0;
			ErrorMsgText = "";
			SuccessMsgText = "";
			for(int wait=1; wait<=(WaitTime); wait++){		
				Validation_Message_Found = false;
				
				if((driver.findElements(By.xpath(OR_Listings.getProperty("Listings_Content_Model_Popup"))).size()>0) ){

					Integer Success_Element_Count = driver.findElements(By.xpath(OR_Listings.getProperty("Listings_Content_Model_Popup"))).size();
					System.out.println("Success_Element_Count SIZE =  "+Success_Element_Count);
					if(Success_Element_Count>0){
						for(int i=1; i<=Success_Element_Count; i++){
							String Success_message_Xpath = "("+OR_Listings.getProperty("Listings_Content_Model_Popup")+")["+i+"]";
							try{
								SuccessMsgText = driver.findElement(By.xpath(Success_message_Xpath)).getText().trim();
							//	System.out.println(SuccessMsgText);
							}catch(Throwable t){
								//nothing to do
							}
						
							if(SuccessMsgText.length()>Success_Message_Length){
								System.out.println("Success Xpath = "+Success_message_Xpath+"     -   Text = "+SuccessMsgText+"       ----- Length = "+SuccessMsgText.length());
								if( SuccessMsgText.toLowerCase().contains(Success_Message.toLowerCase().trim()) ){
									assert (SuccessMsgText.toLowerCase().contains( Success_Message.toLowerCase().trim()) ):"FAILED == Popup Validation message < "+Success_Message+" > is not coming, please refer screenshot >> Success_Message_Fail" + fnsScreenShot_Date_format();
									fnsApps_Report_Logs("PASSED == Popup Validation message < "+Success_Message+" > is coming.");
									Validation_Message_Found = true;
									break;
								}
								
								if( (SuccessMsgText.toLowerCase().contains("cancel")) || ( SuccessMsgText.toLowerCase().contains("alert") ) ){
									throw new Exception("FAILED == Validation Error <"+SuccessMsgText+"> is coming instead of displaying success message, please refer screenshot >>Success_Message_Fail" + fnsScreenShot_Date_format());
								}
							}
						}	
					}	
				} else	if(driver.findElements(By.xpath(OR_Listings.getProperty("Model_Popup_Not_Coming_Error"))).size()>0){
					Integer Error_Element_Count = driver.findElements(By.xpath(OR_Listings.getProperty("Model_Popup_Not_Coming_Error"))).size();
					if(Error_Element_Count>0){
						for(int i=1; i<=Error_Element_Count; i++){
							String Error_message_Xpath = "("+OR_Listings.getProperty("Model_Popup_Not_Coming_Error")+")["+i+"]";
							try{
								ErrorMsgText = driver.findElement(By.xpath(Error_message_Xpath)).getText().trim();
								Validation_Message_Found = true;
							}catch(Throwable t){
								//nothing to do
							}
							if(ErrorMsgText.length()>5){
								throw new Exception("FAILED == Application display Error < "+ErrorMsgText+" >, please refer screen shot >> Success_Message_Fail"+fnsScreenShot_Date_format());
							}
						}	
					}	
										
				}
				
				if(Validation_Message_Found==true){
					break;
				}else{
					Thread.sleep(1000);
				}
				
							
				TimeOut = wait;	

				if( TimeOut==WaitTime ){
				
						throw new Exception("FAILED == After < "+( TimeOut ) 
								+" > seconds wait, NIETHER message '"+Success_Message+"' is display NOR any 'error' message is coming, please refer screenshot >>Success_Message_Fail" + fnsScreenShot_Date_format());
					
				}
			}
			
			if(TakeScreenShot==true && Validation_Message_Found==false){
				throw new Exception("FAILED == After < "+( TimeOut ) 
						+" > seconds wait, NIETHER message '"+Success_Message+"' is display NOR any 'error' message is coming, please refer screenshot >>Success_Message_Fail" + fnsScreenShot_Date_format());
			}			
			
			return SuccessMsgText;
		}catch(NoSuchWindowException W){
			//isTestCasePass = false;
			throw new Exception(W.getMessage());			}
		catch(Throwable t) {
			if(TakeScreenShot==true){
				fnsTake_Screen_Shot("Success_Message_Fail");
			}
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}

	
	
	
	public Integer fnsReturn_View_Results_Reords_Total_Count(String Section_Name, String Table_Xpath, Integer No_of_Records_Display_Per_Screen) throws Throwable{
		try{
			Integer Total_no_of_Count = null; 
						
			fnsGet_Element_Enabled(Table_Xpath);
			fnsGet_Element_Displayed(Table_Xpath); 
			WebElement View_Results_Table = fnsGet_OR_Listings_ObjectX(Table_Xpath);
			List<WebElement> View_Results_Table_Tr_Objs = View_Results_Table.findElements(By.tagName("tr"));
			Total_no_of_Count = View_Results_Table_Tr_Objs.size();
			
			if( (Total_no_of_Count>=No_of_Records_Display_Per_Screen) && (driver.findElements(By.xpath(OR_Listings.getProperty("View_Pagination_Total_Records"))).size()>0) ){
				Total_no_of_Count = fnsReturn_Pagination_Reords_Total_Count (Section_Name);
			}else{
				if(Total_no_of_Count==1){
					String View_Result_Table_Text_Fetch = View_Results_Table.getText().trim();
					//if(View_Result_Table_Text_Fetch.toLowerCase().contains("sorry, no information available")){//commented on 22.6.2018 as the message changed
					if(View_Result_Table_Text_Fetch.toLowerCase().contains("no records found")){
						Total_no_of_Count = 0;
						fnsApps_Report_Logs("PASSED == "+Section_Name+" : Records are not found for applied filter. Total no of records are = "+Total_no_of_Count );
						return Total_no_of_Count;
					}
				}
			}
									
			fnsApps_Report_Logs("PASSED == "+Section_Name+" : Total no of records are = "+Total_no_of_Count);
			
			return Total_no_of_Count;
			
		}catch(Throwable t) {
			//isTestCasePass = false;
			throw new Exception(Section_Name+" : "+Throwables.getStackTraceAsString(t));
		}
		
	}	
	
	

	
	public Integer fnsReturn_Pagination_Reords_Total_Count(String Section_Name) throws Throwable{
		try{
			Integer Total_no_of_Records_Count = null; 
			fnsGet_Element_Enabled("View_Pagination_Total_Records");
			String Total_no_of_Records_Count_String = fnsGet_Field_TEXT("View_Pagination_Total_Records").trim();
			Total_no_of_Records_Count_String = Total_no_of_Records_Count_String.split("of")[1].trim();
			Total_no_of_Records_Count = Integer.parseInt(Total_no_of_Records_Count_String);
			
			return Total_no_of_Records_Count;
			
		}catch(Throwable t) {
			//isTestCasePass = false;
			throw new Exception(Section_Name+" : "+Throwables.getStackTraceAsString(t));
		}
		
	}	
	
	
	
	
	
	public Integer fnsFilter_ApplyFilter_and_Return_Total_Filtered_Record_Count(String FilterName, String Filter_DataFieldAttribute_Value_from_HTML, String Filter_Value_Enter) throws Throwable{
		try{
			//String Filter_Value_Enter = Filter_Value.toLowerCase().trim();
			Integer Filter_Record_Total_Count = null; 
			boolean Filter_Exists = false;
			View_Filter_Input_Xpath = null;
			
			fnsGet_Element_Enabled("Sites_SearchFilter_Table_FilterRow");
			
			List<WebElement> All_Filter_Objects = fnsGet_OR_Listings_ObjectX("Sites_SearchFilter_Table_FilterRow").findElements(By.tagName("th"));
			
			for(int RowNo=1; RowNo<=All_Filter_Objects.size(); RowNo++){
				String View_Filter_Xpath = OR_Listings.getProperty("Sites_SearchFilter_Table_FilterRow")+"/th["+RowNo+"]/span";
				if(driver.findElements(By.xpath(View_Filter_Xpath)).size()>0){
					String Filter_Name = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(View_Filter_Xpath).getAttribute("data-field").trim();
					if( (Filter_Name.toLowerCase()).equals(Filter_DataFieldAttribute_Value_from_HTML.toLowerCase().trim())){
						//View_Filter_Input_Xpath = OR_Listings.getProperty("Sites_SearchFilter_Table_FilterRow")+"/th["+RowNo+"]/following::input[1]"; Not Working
						
						View_Filter_Input_Xpath = View_Filter_Xpath+"/span/input";
						try{
							if(driver.findElements(By.xpath(View_Filter_Input_Xpath)).size()>0){
								//nothing to do
							}else{
								View_Filter_Input_Xpath = View_Filter_Xpath+"/span/span/span/input";
							}
						}catch(Throwable t){
							View_Filter_Input_Xpath = View_Filter_Xpath+"/span/span/span/input";
						}
						
						Filter_Exists=true;
						break;
					}
				}
			}
			
			if(Filter_Exists){
				//Code has been commented as filters are working fine and now the text are getting remove from filter
				/*/TestSuiteBase_MonitorPlan.WithOut_OR_fnClear_Field_By_Pressing_BackspaceKey(View_Filter_Input_Xpath, 15);
				TestSuiteBase_MonitorPlan.WithOut_OR_fnClear(View_Filter_Input_Xpath);
				TestSuiteBase_MonitorPlan.WithOut_OR_fnClear_Field_By_Pressing_BackspaceKey(View_Filter_Input_Xpath, 15);
				TestSuiteBase_MonitorPlan.WithOut_OR_fnClear(View_Filter_Input_Xpath);
				//TestSuiteBase_MonitorPlan.WithOut_OR_fnClear(View_Filter_Input_Xpath);
				fnsLoading_Progressing_wait(2);
				TestSuiteBase_MonitorPlan.WithOut_OR_fnType(View_Filter_Input_Xpath, Filter_Value_Enter);
				fnsLoading_Progressing_wait(3);
				Thread.sleep(1000);*/
				if(FilterName.toLowerCase().contains("popup")){
					TestSuiteBase_MonitorPlan.WithOut_OR_fnClear(View_Filter_Input_Xpath);
					fnsLoading_Progressing_wait(2);
					//Thread.sleep(1000); //Commented on 15.9.2017 as not working while loading
					TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(View_Filter_Input_Xpath).sendKeys(Filter_Value_Enter);
					fnsLoading_Progressing_wait(3);
					//Thread.sleep(5000); //Commented on 15.9.2017 as not working while loading
				}else if(FilterName.toLowerCase().contains("date")){
					/*TestSuiteBase_MonitorPlan.WithOut_OR_fnClear(View_Filter_Input_Xpath);
					fnsLoading_Progressing_wait(2);*/
					//TestSuiteBase_MonitorPlan.WithOut_OR_fnType(View_Filter_Input_Xpath, Filter_Value_Enter);
					TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(View_Filter_Input_Xpath).sendKeys(Filter_Value_Enter);
					//Thread.sleep(1000);
					/*Actions EnterKeyAct = new Actions(driver); //temp as filter work after enter press
					EnterKeyAct.sendKeys(TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(View_Filter_Input_Xpath), Keys.ENTER).build().perform();
					*/
					fnsLoading_Progressing_wait(3);
				}else{
					TestSuiteBase_MonitorPlan.WithOut_OR_fnClear(View_Filter_Input_Xpath);
					fnsLoading_Progressing_wait(2);
					TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(View_Filter_Input_Xpath).sendKeys(Filter_Value_Enter);
					fnsLoading_Progressing_wait(2);
				}
				
			}else{
				throw new Exception("FAILED == Records for ( "+FilterName.split(":")[1]+" ) are not found into the view, please refer screen shot >> Filter_Fail"+fnsScreenShot_Date_format());
			}
			
				
			Filter_Record_Total_Count = fnsReturn_View_Results_Reords_Total_Count("Automation View Filter ( "+FilterName+" )", "View_Result_Table", 20 );			
			return Filter_Record_Total_Count;
			
		}catch(Throwable t) {
			//isTestCasePass = false;
			fnsTake_Screen_Shot("Filter_Fail");
			throw new Exception("Filter ( "+FilterName.split(":")[0]+" ) : "+Throwables.getStackTraceAsString(t));
		}
		
	}

	public Integer fnsReturn_ColumnNumber_from_TableHeaderXpath_WithoutOR(String TableHeaderXpath_Without_OR, String Column_Name_From_Data_Title) throws Throwable{
		try{
			Integer Column_Number = 1; 
			boolean Column_Exists = false;
			
			TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(TableHeaderXpath_Without_OR);
			
			List<WebElement> All_Header_Objects = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(TableHeaderXpath_Without_OR).findElements(By.tagName("th"));
			for(WebElement All_Header_Elements:All_Header_Objects ){
				String ColumnText_Fetch = All_Header_Elements.getAttribute("data-title").trim();
				if(ColumnText_Fetch.toLowerCase().equals(Column_Name_From_Data_Title.toLowerCase().trim())){
					Column_Exists = true;
					break;
				}
				Column_Number++;
			}
			
			if(Column_Exists==false){
				throw new Exception ("FAILED == Column is not Exists into the view, please refer the screen shot >> Column_Not_Exists"+fnsScreenShot_Date_format());
			}
					
			return Column_Number;
			
		}catch(Throwable t) {
		//	//isTestCasePass = false; //Not working in for looping
			fnsTake_Screen_Shot("Column_Not_Exists");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		
	}

	
	
	public void fncVerify_GraphDisplay_And_RecordsCount_Validate_Bia_ToolTip(Integer View_TotalRecordsCount, ArrayList<String> ViewColumn_Values_Text_List, ArrayList<Integer> ViewColumn_Records_Count_List, boolean Take_Screenshot ) throws Throwable{
		try{
			boolean TotalRecordsCount_Verification_Enabled_Flag = false;
			Integer TotalRecordsCount_Verification_Enabled = View_TotalRecordsCount;
			boolean Graph_indiviudual_Value_Verified = false;
			Integer Graph_indiviudual_Value_Count = 0;
			Integer Graph_Total_Value_Count = 0;
			View_Filter_Input_Xpath = null;
			Element = null;
						
			for(int i=0; i<ViewColumn_Records_Count_List.size(); i++){
				TotalRecordsCount_Verification_Enabled = TotalRecordsCount_Verification_Enabled-ViewColumn_Records_Count_List.get(i);
				
				if(TotalRecordsCount_Verification_Enabled == 0){
					TotalRecordsCount_Verification_Enabled_Flag = true;
				}
			}
			
			
						
			List<WebElement> Graphs_Text_allObjs=fnsGet_OR_Listings_ObjectX("Snapshot_View_PieChart_Graph").findElements(By.tagName("text"));
		
			for(int i=0; i<ViewColumn_Values_Text_List.size(); i++){
				Graph_indiviudual_Value_Verified = false;
				for(WebElement Graphs_Text_Element:Graphs_Text_allObjs){
					Text_on_Graphs = Graphs_Text_Element.getText().toLowerCase().trim();
					Actions act = new Actions(driver);
					act.moveToElement(Graphs_Text_Element).build().perform();
					Thread.sleep(500);
					ToolTip_Text = "";
	
					for(int GV=1; GV<=10; GV++){
						try{
							Element = driver.findElement(By.xpath(OR_Listings.getProperty("Snapshot_View_PieChart_Graph_ToolTip")));
							ToolTip_Text = Element.getText().toLowerCase().trim();
						}catch(Throwable t){
							//Nothing to do
						}
						if( (ToolTip_Text.length()>0) ){
							break;
						}else{
							Thread.sleep(250);
						}
					}
					
					if( (ToolTip_Text.contains(ViewColumn_Values_Text_List.get(i).toLowerCase().trim())) ){
						ToolTip_Text = ToolTip_Text.split("-")[1].trim();
						for(int j=0; j<=5; j++){
							if(ToolTip_Text.contains(",")){
								ToolTip_Text=ToolTip_Text.replace(",", "");
							}else{
								break;
							}
						}
						Graph_indiviudual_Value_Count = Integer.parseInt(ToolTip_Text);
						assert Graph_indiviudual_Value_Count.equals(ViewColumn_Records_Count_List.get(i)):"FAILED == '"+ViewColumn_Values_Text_List.get(i).toUpperCase()+"' Total no of Records < "+ViewColumn_Records_Count_List.get(i)+" > from view is not matched with the Graph Total no of records < "+Graph_indiviudual_Value_Count+" >, please refer screen shot >> Graph_Records_Verification_Fail"+fnsScreenShot_Date_format();
						fnsApps_Report_Logs("PASSED == '"+ViewColumn_Values_Text_List.get(i).toUpperCase()+"' Total no of Records < "+ViewColumn_Records_Count_List.get(i)+" > from view is matched with the Graph Total no of records < "+Graph_indiviudual_Value_Count+" > automation");
						Graph_Total_Value_Count = Graph_Total_Value_Count+Graph_indiviudual_Value_Count;
						Graph_indiviudual_Value_Verified = true;
						break;
						
					}
										
				}
		
				if(Graph_indiviudual_Value_Verified==false){
					if(ViewColumn_Records_Count_List.get(i)==0){
						fnsApps_Report_Logs("PASSED == '"+ViewColumn_Values_Text_List.get(i).toUpperCase()+"' records count is not coming into the Graph as count is zero.");
					}else{					
						throw new Exception ("FAILED == '"+ViewColumn_Values_Text_List.get(i).toUpperCase()+"' records count is not coming into the Graph, please refer screen shot >> Graph_Records_Verification_Fail"+fnsScreenShot_Date_format());
					}
				}
					
				
			}
		
			if(TotalRecordsCount_Verification_Enabled_Flag){
				try{
					assert Graph_Total_Value_Count.equals(View_TotalRecordsCount):"FAILED == View's Total no of Records < "+View_TotalRecordsCount+" > is not matched with the Graph Total no of records< "+Graph_Total_Value_Count+" > , please refer screen shot >> Graph_Records_Verification_Fail"+fnsScreenShot_Date_format();
					fnsApps_Report_Logs("PASSED == View's Total no of Records < "+View_TotalRecordsCount+" > is matched with the Graph Total no of records < "+Graph_Total_Value_Count+" >. automation");		
				}catch(Throwable t){
					throw new Exception(Throwables.getStackTraceAsString(t));
				}
			}
					
			
			
			
			
			if(driver.findElements(By.xpath(OR_Listings.getProperty("Snapshot_View_PieChart_Graph_Print_Icon"))).size()>0){
				fnsApps_Report_Logs("PASSED == Successfully verified that export 'PRINT' Icon is displayed.");
			}else{
				throw new Exception("FAILED == Export 'PRINT' Icon is not displayed, please refer screen shot >> Graph_Records_Verification_Fail"+fnsScreenShot_Date_format());
			}

			if(driver.findElements(By.xpath(OR_Listings.getProperty("Snapshot_View_PieChart_Graph_Pdf_Icon"))).size()>0){
				fnsApps_Report_Logs("PASSED == Successfully verified that export 'PDF' Icon is displayed.");
			}else{
				throw new Exception("FAILED == Export 'PDF' Icon is not displayed, please refer screen shot >> Graph_Records_Verification_Fail"+fnsScreenShot_Date_format());
			}
				
			
			
			
		}catch(NoSuchWindowException W){
			throw new Exception(W.getMessage());			
		}catch(NullPointerException n){
			throw new Exception("Pie Chart graph is not coming... "+Throwables.getStackTraceAsString(n));			
		}catch(Throwable t) {
			if(Take_Screenshot){
				fnsTake_Screen_Shot("Graph_Records_Verification_Fail");
			}
			throw new Exception("Pie Chart graph : "+Throwables.getStackTraceAsString(t));
		}

}
	
	


	
	public void fncVerify_GraphDisplay_And_RecordsCount_Validate(Integer View_TotalRecordsCount, ArrayList<String> ViewColumn_Values_Text_List, ArrayList<Integer> ViewColumn_Records_Count_List, boolean Take_Screenshot ) throws Throwable{
		try{
			boolean TotalRecordsCount_Verification_Enabled_Flag = false;
			Integer TotalRecordsCount_Verification_Enabled = View_TotalRecordsCount;
			boolean Graph_indiviudual_Value_Verified = false;			
			Integer Graph_Total_Value_Count = 0;
			View_Filter_Input_Xpath = null;
			Element = null;
						
			for(int i=0; i<ViewColumn_Records_Count_List.size(); i++){
				TotalRecordsCount_Verification_Enabled = TotalRecordsCount_Verification_Enabled-ViewColumn_Records_Count_List.get(i);
				
				if(TotalRecordsCount_Verification_Enabled == 0){
					TotalRecordsCount_Verification_Enabled_Flag = true;
				}
			}
			
			
						
			List<WebElement> Graphs_Text_allObjs=fnsGet_OR_Listings_ObjectX("Snapshot_View_PieChart_Graph").findElements(By.tagName("text"));
		
			for(int i=0; i<ViewColumn_Values_Text_List.size(); i++){
				Graph_indiviudual_Value_Verified = false;
				boolean Indiviudual_Count_Verified = false;
				boolean Indiviudual_Text_Verified = false;
				String ViewColumn_Records_Count_from_View = ViewColumn_Records_Count_List.get(i).toString();
				for(WebElement Graphs_Text_Element:Graphs_Text_allObjs){
					Text_on_Graphs = Graphs_Text_Element.getText().toLowerCase().trim();					
					if( (Text_on_Graphs.contains(",")) ){
						for(int j=0; j<=5; j++){
							if(Text_on_Graphs.contains(",")){
								Text_on_Graphs=Text_on_Graphs.replace(",", "");
							}else{
								break;
							}
						}
						if(Text_on_Graphs.equalsIgnoreCase(ViewColumn_Records_Count_from_View)){
							Indiviudual_Count_Verified = true;
							Graph_Total_Value_Count = Integer.parseInt(Text_on_Graphs);
							TotalRecordsCount_Verification_Enabled = TotalRecordsCount_Verification_Enabled+Graph_Total_Value_Count;
						}
					}else if( (Text_on_Graphs.contains(ViewColumn_Values_Text_List.get(i).toLowerCase())) ){
						Indiviudual_Text_Verified = true;
					}else if (Text_on_Graphs.equalsIgnoreCase(ViewColumn_Records_Count_from_View)){
						try{
							Graph_Total_Value_Count = Integer.parseInt(Text_on_Graphs);
							TotalRecordsCount_Verification_Enabled = TotalRecordsCount_Verification_Enabled+Graph_Total_Value_Count;
						}catch(Throwable t){
							//nothing to do
						}
						Indiviudual_Count_Verified = true;
					}
					if(Indiviudual_Count_Verified && Indiviudual_Text_Verified){
						Graph_indiviudual_Value_Verified = true;
						break;
					}					
				}
		

				if(Graph_indiviudual_Value_Verified){
					fnsApps_Report_Logs("PASSED == '"+ViewColumn_Values_Text_List.get(i).toUpperCase()+"' Total no of Records < "+ViewColumn_Records_Count_List.get(i)+" > from view is matched with the Graph Total no of records < "+Text_on_Graphs+" > automation");
				}else{
					if(ViewColumn_Records_Count_List.get(i)==0){
						fnsApps_Report_Logs("PASSED == '"+ViewColumn_Values_Text_List.get(i).toUpperCase()+"' records count is not coming into the Graph as count is zero.");
					}else if (Indiviudual_Text_Verified==false){					
						throw new Exception ("FAILED == '"+ViewColumn_Values_Text_List.get(i).toUpperCase()+"' records count is not coming into the Graph, please refer screen shot >> Graph_Records_Verification_Fail"+fnsScreenShot_Date_format());
					}else{
						throw new Exception("FAILED == '"+ViewColumn_Values_Text_List.get(i).toUpperCase()+"' Total no of Records < "+ViewColumn_Records_Count_List.get(i)+" > from view is not matched with the Graph Total no of records < "+Text_on_Graphs+" >, please refer screen shot >> Graph_Records_Verification_Fail"+fnsScreenShot_Date_format());
					}
				}				
				
			}
		
			if(TotalRecordsCount_Verification_Enabled_Flag){
				try{
					assert TotalRecordsCount_Verification_Enabled.equals(View_TotalRecordsCount):"FAILED == View's Total no of Records < "+View_TotalRecordsCount+" > is not matched with the Graph Total no of records< "+TotalRecordsCount_Verification_Enabled+" > , please refer screen shot >> Graph_Records_Verification_Fail"+fnsScreenShot_Date_format();
					fnsApps_Report_Logs("PASSED == View's Total no of Records < "+View_TotalRecordsCount+" > is matched with the Graph Total no of records < "+TotalRecordsCount_Verification_Enabled+" >. automation");		
				}catch(Throwable t){
					throw new Exception(Throwables.getStackTraceAsString(t));
				}
			}
					
			
			
			
			
			if(driver.findElements(By.xpath(OR_Listings.getProperty("Snapshot_View_PieChart_Graph_Print_Icon"))).size()>0){
				fnsApps_Report_Logs("PASSED == Successfully verified that export 'PRINT' Icon is displayed.");
			}else{
				throw new Exception("FAILED == Export 'PRINT' Icon is not displayed, please refer screen shot >> Graph_Records_Verification_Fail"+fnsScreenShot_Date_format());
			}

			if(driver.findElements(By.xpath(OR_Listings.getProperty("Snapshot_View_PieChart_Graph_Pdf_Icon"))).size()>0){
				fnsApps_Report_Logs("PASSED == Successfully verified that export 'PDF' Icon is displayed.");
			}else{
				throw new Exception("FAILED == Export 'PDF' Icon is not displayed, please refer screen shot >> Graph_Records_Verification_Fail"+fnsScreenShot_Date_format());
			}
				
			
			
			
		}catch(NoSuchWindowException W){
			throw new Exception(W.getMessage());			
		}catch(NullPointerException n){
			throw new Exception("Pie Chart graph is not coming... "+Throwables.getStackTraceAsString(n));			
		}catch(Throwable t) {
			if(Take_Screenshot){
				fnsTake_Screen_Shot("Graph_Records_Verification_Fail");
			}
			throw new Exception("Pie Chart graph : "+Throwables.getStackTraceAsString(t));
		}

}
	
	
public void fncClickonFirstFacilityfromViewResultTable_n_ReturnBackonViewScreen(String View_Home_ScreenName, String Link_Column_Name_From_DataTitle) throws Throwable{
	try{
		
		String Link_Text = null;
		Integer Link_Column_Number = fnsReturn_ColumnNumber_from_TableHeaderXpath_WithoutOR(OR_Listings.getProperty("View_Result_TableHeader"), Link_Column_Name_From_DataTitle);
		String First_Row_MatchingColumn_Xpath = OR_Listings.getProperty("View_Result_Table")+"/tr[1]/td["+Link_Column_Number+"]";
		for(int i=0; i<=Listings_Element_Max_Wait_Time; i++){
			if(driver.findElements(By.xpath(First_Row_MatchingColumn_Xpath)).size()>0){
				break;
			}else{
				Thread.sleep(100);
			}
			if(i==Listings_Element_Max_Wait_Time){
				throw new Exception ("FAILED == Records are not coming into the view on '"+View_Home_ScreenName+"' screen, please refer screen shot >> Opening_First_Facility_and_ReturnBack_to_View_FAIL"+fnsScreenShot_Date_format()+"--Xpath >> "+First_Row_MatchingColumn_Xpath);
			}
		}
		List<WebElement> First_Row_td_Objects = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(First_Row_MatchingColumn_Xpath).findElements(By.tagName("a"));
		if(First_Row_td_Objects.size()==1){
			for(WebElement First_Row_td_Elements : First_Row_td_Objects){
				Link_Text = First_Row_td_Elements.getText().trim();
				First_Row_td_Elements.click();
				fnsApps_Report_Logs("PASSED == Click done on the '"+Link_Text+"' link");
				fnsLoading_Progressing_wait(4);
				break;
			}
		}else{
			throw new Exception("FAILED == More than one link is present into the cell row 1 of column '"+Link_Column_Name_From_DataTitle+"', please refer screen shot >> Opening_First_Facility_and_ReturnBack_to_View_FAIL"+fnsScreenShot_Date_format());
		}
		
		try{
			for(int i=0; i<=Listings_Element_Max_Wait_Time; i++){
				if(driver.findElements(By.xpath(OR_Listings.getProperty("ActiveView_DD"))).size()>0){
					Thread.sleep(50);
				}else{
					break;
				}
				if(i==Listings_Element_Max_Wait_Time){
					String Facility_Screen_Table_First_Record = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(First_Row_MatchingColumn_Xpath).getText().trim();
					if( !(Link_Text.equals(Facility_Screen_Table_First_Record)) ){
						break;
					}else{
						throw new Exception ("FAILED == After clicking on '"+Link_Text+"' link, Application is not getting navigate to Facility info screen, please refer screen shot >> Opening_First_Facility_and_ReturnBack_to_View_FAIL"+fnsScreenShot_Date_format());
					}
				}
			}
		}catch(Throwable t){
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		fnsLoading_Progressing_wait(2);
		
		if(View_Home_ScreenName.equalsIgnoreCase("Site Documents")){
			
			fnsWait_and_Click("Site_Document_Back_Button");
			fnsLoading_Progressing_wait(3);
			
		}else{
			
		fnsWait_and_Click("Listings_Back_Bttn");
		fnsLoading_Progressing_wait(3);
		}
		
		try{
			fnsGet_Element_Enabled("View_Result_Table");
		}catch (Throwable t){
			throw new Exception ("FAILED == After clicking on 'BACK' button, Application is not getting navigate to '"+View_Home_ScreenName+"' screen, please refer screen shot >> Opening_First_Facility_and_ReturnBack_to_View_FAIL"+fnsScreenShot_Date_format());
		}
		
		fnsApps_Report_Logs("PASSED == Clicking on '"+Link_Text+"' Link and return back to '"+View_Home_ScreenName+"' view screen is successfully done.");
	}catch (Throwable t){
		//isTestCasePass = false;
		fnsTake_Screen_Shot("Opening_First_Facility_and_ReturnBack_to_View_FAIL");
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
	
}
	
	


	
//To Download file
public void fnsDownload_File(String File_Download_bttn_Xpath) throws Throwable{
	String File_Type = File_Download_bttn_Xpath.split("_")[1].trim();
	long AllFileSize_AfterFileSave = 0;
	boolean File_Download_Success = false;
	String FileLocation = System.getProperty("user.home")+"\\Downloads";
	try{
		long AllFileSize_BeforeFileSave = FileUtils.sizeOfDirectory(new File(FileLocation));
				
		fnsGet_Element_Enabled(File_Download_bttn_Xpath);
		fnsWait_and_Click(File_Download_bttn_Xpath);
		//fnsLoading_Progressing_wait(3); //Not working as Ok Pupup Block UI and stuck into loading >> html/body/div[1]/div[1]
			
		for(int waitloop=1; waitloop<=( (Listings_Element_Max_Wait_Time)/5 ) ; waitloop++){
			
			try{
				if(  (driver.findElements(By.xpath(OR_Listings.getProperty("Model_Confirmation_Popup_OK_Bttn"))).size()>0) ){
					if( (driver.findElement(By.xpath(OR_Listings.getProperty("Model_Confirmation_Popup_OK_Bttn"))).isDisplayed()) ){
						fnsGet_Element_Enabled("Model_Confirmation_Popup_OK_Bttn");
						fnsWait_and_Click("Model_Confirmation_Popup_OK_Bttn");
						fnsLoading_Progressing_wait(3);
						//fnsLoading_Progressing_wait(3); //Not working as Ok Pupup Block UI and stuck into loading >> html/body/div[1]/div[1]
					}
				}
			}catch(Throwable t){
				if( !(waitloop==1) ){
					fnsLoading_Progressing_wait(1);
				}
				System.out.println("'Model_Confirmation_Popup_OK_Bttn' display fail = "+waitloop);
			}
			//driver.findElement(By.xpath("//button[text()='OK']")).isDisplayed();	
			fnsLoading_Progressing_wait(1);
			Actions action = new Actions(driver);
			action.keyDown(Keys.ALT);
			action.sendKeys("s");
			action.keyUp(Keys.ALT);
			action.build().perform();
					    
			for(int i=1; i<=5; i++){
		
				try{
					AllFileSize_AfterFileSave = FileUtils.sizeOfDirectory(new File((FileLocation)));
					
					if(AllFileSize_BeforeFileSave<AllFileSize_AfterFileSave){
						File_Download_Success =true;
						break;
					}else{
						Thread.sleep(1000);
					}
					
				}catch(IllegalArgumentException K){ 	
					//nothing to do
				}
			}
			/*
			if( (waitloop == ( (Listings_Element_Max_Wait_Time)/5 )) &&  File_Download_Success == false){
				
			}	*/
			if(File_Download_Success==true){
				break;
			}
		}
		
		System.out.println("File Size Before = "+AllFileSize_BeforeFileSave+" ... After = "+AllFileSize_AfterFileSave);
		assert (AllFileSize_BeforeFileSave<AllFileSize_AfterFileSave):
				"FAILED == File is not getting download. After <"+ (Listings_Element_Max_Wait_Time)*2 +"> seconds wait, Please refer screenshot >> "+File_Type+"_FileDownloadFail"+fnsScreenShot_Date_format();
		      
	    
		Actions action = new Actions(driver);
		action.keyDown(Keys.ALT);
		action.sendKeys("q");
		action.keyUp(Keys.ALT);
		action.build().perform();
        Thread.sleep(2000);
        
        fnsApps_Report_Logs("PASSED == '"+File_Type+"' File successfully downloaded.");
		
		
	}catch(Throwable t){
		isTestCasePass = false;
		fnsTake_Screen_Shot(File_Type+"_FileDownloadFail");
		throw new Exception("FAILED == '"+File_Type+"' "+Throwables.getStackTraceAsString(t));
	}
		
}
	


public void fnsDownload_File_or_Verify_Validation_Message (String DownloadLinkName_TEXT, String Download_File_Link_Xpath_withoutOR) throws Throwable{
	try{
		long AllFileSize_AfterFileSave = 0;
		boolean File_Download_Success = false;
		String FileLocation = System.getProperty("user.home")+"\\Downloads";
		long AllFileSize_BeforeFileSave = FileUtils.sizeOfDirectory(new File(FileLocation));
	
		

		TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(Download_File_Link_Xpath_withoutOR);
		fnsLoading_Progressing_wait(3);
		
		for(int wait=1; wait<=((Listings_Element_Max_Wait_Time)); wait++){
		
			if(  (driver.findElements(By.xpath(OR_Listings.getProperty("Model_Popup_OK_Bttn"))).size()>0) ){
				if( (driver.findElement(By.xpath(OR_Listings.getProperty("Model_Popup_OK_Bttn"))).isDisplayed()) ){
					
					fns_Verify_Success_or_Error_Message_on_ModelPopup_Generation(true, 10, "File not available", 15);
					fnsApps_Report_Logs("PASSED == 'File not available' validation message is coming for file < "+DownloadLinkName_TEXT+" > download link.");
					fnsGet_Element_Enabled("Model_Popup_OK_Bttn");
					fnsGet_Element_Displayed("Model_Popup_OK_Bttn");
					Thread.sleep(1000);
					fnsWait_and_Click("Model_Popup_OK_Bttn");
					fnsLoading_Progressing_wait(3);
					break;
				}
			}else{				

				Actions action = new Actions(driver);
				action.keyDown(Keys.ALT);
				action.sendKeys("s");
				action.keyUp(Keys.ALT);
				action.build().perform();
				
				try{
					AllFileSize_AfterFileSave = FileUtils.sizeOfDirectory(new File((FileLocation)));
					if(AllFileSize_BeforeFileSave<AllFileSize_AfterFileSave){
						File_Download_Success =true;
						fnsApps_Report_Logs("PASSED ==  ********  Successfully download file for  file < "+DownloadLinkName_TEXT+" > download link.");
						break;
					}
				}catch(IllegalArgumentException K){ 	
					//nothing to do
				}	
			
				
			}	
			if(File_Download_Success==true){
				break;
			}else if(wait==Listings_Element_Max_Wait_Time){
				fnsTake_Screen_Shot("File_Not_Download");
				throw new Exception ("FAILED == File is not getting download for  file < "+DownloadLinkName_TEXT+" > download link, please refer screen shot >> File_Not_Download"+fnsScreenShot_Date_format());
			}else {
				Thread.sleep(400);
			}
		}
				
	}catch(Throwable t){
		isTestCasePass = false;
		fnsTake_Screen_Shot("FileDownloadFail");
		throw new Exception(DownloadLinkName_TEXT+" fail :: "+Throwables.getStackTraceAsString(t));
	}
	
}
//Verify records by moving on all screen of last pagination Dropdown
public void fns_Verify_Record_Present_By_Clicking_LastPagination_AllScreens(String Pagination_DD_Xpath) throws Throwable{
	try{
		fnsGet_Element_Enabled(Pagination_DD_Xpath);
		fnsWait_and_Click(Pagination_DD_Xpath);
		
		for(int i=0; i<10; i++){
			fnsGet_OR_Listings_ObjectX(Pagination_DD_Xpath).click();
			Thread.sleep(500);
			if(fnsGet_OR_Listings_ObjectX(Pagination_DD_Xpath).isDisplayed()){
				
			}
		}
		
		//Pending method of checklist pagination	
		
		
	}catch(Throwable t){
		isTestCasePass = false;
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
}



public String fnsw_Click_on_Table_Link_by_Passing_Cell_Xpath__and__Return_CellValue(String Matching_Row_and_Cell_Xpath_withoutOR) throws Throwable{
	try{
		String Matching_Row_and_Cell__Text = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(Matching_Row_and_Cell_Xpath_withoutOR).getText().trim();
		String Matching_Row_and_Cell__Link_Xpath = "//a[text()='"+Matching_Row_and_Cell__Text+"']";
		fnsWait_Click_on_First_Visible_Element_if_More_than_One_Coming(Matching_Row_and_Cell__Text, Matching_Row_and_Cell__Link_Xpath);
		fnsApps_Report_Logs("PASSED == Click done on the link <"+Matching_Row_and_Cell__Text+">.");
		fnsLoading_Progressing_wait(2);
		fnsLoading_Progressing_wait(1);
		return Matching_Row_and_Cell__Text;
	}catch(Throwable t){
		isTestCasePass = false;
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
}

public void fnsGet_Element_Display_byPassing_ElementName_and_Xpath_WithoutOR(String ScreenName, String ElementName, String ElementXpath) throws Throwable{
	try{
		for(int i=0; i<=Listings_Element_Max_Wait_Time; i++){
			try{			
				if(driver.findElement(By.xpath((ElementXpath))).isDisplayed()){
					fnsApps_Report_Logs("PASSED == ************ "+ElementName+" is present on the "+ScreenName+".");
					break;
				}else{
					Thread.sleep(1000);
				}
			}catch(Throwable t){
				if(i==Listings_Element_Max_Wait_Time){
					fnsTake_Screen_Shot(ElementName+"_Not_Present");
					throw new Exception ("FAILED == "+ElementName+" is NOT present on the "+ScreenName+", please refer the screen shot >> "+ElementName+"_Not_Present"+fnsScreenShot_Date_format());
				}
			}
			
		}
		
	}catch(Throwable t){
	//	isTestCasePass = false;
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
}



public boolean fnsGet_Element_NOT_Display_WithoutOR(String ElementName, String ElementXpath) throws Throwable{
	boolean Element_NOT_Display = false;
	try{
		for(int i=0; i<=10; i++){
			try{
				if(driver.findElements(By.xpath(ElementXpath)).size()>0){
					if( !(driver.findElement(By.xpath((ElementXpath))).isDisplayed()) ){
						fnsApps_Report_Logs("PASSED == ************ "+ElementName+" is NOT present. Automation");
						Element_NOT_Display = true;
						break;
					}else{
						Thread.sleep(1000);
					}
				}else{
					fnsApps_Report_Logs("PASSED == ************ "+ElementName+" is NOT present.");
					Element_NOT_Display = true;
					break;
				}
			}catch(Throwable t){
				//nothing to do
			}			
		}	
		if(Element_NOT_Display==false){
			fnsTake_Screen_Shot(ElementName+"_Present");
			throw new Exception ("FAILED == "+ElementName+" is present, which is not expected, please refer the screen shot >> "+ElementName+"_Present"+fnsScreenShot_Date_format());
		}
		return Element_NOT_Display;
	}catch(Throwable t){
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
}


public void fnsNavigation_Verify_Application_Navigation_fromHeaderText(String Clicked_ElementName, String Navigated_ScreenName_fromHeader) throws Throwable{
	try{
		for(int wait=0; wait<=((Listings_Element_Max_Wait_Time)); wait++){
			try{
				String HeaderText = fnsGet_Field_TEXT("Listings_ScreenHeader").toLowerCase();
				if(HeaderText.contains(Navigated_ScreenName_fromHeader.toLowerCase().trim())){
					fnsApps_Report_Logs("PASSED == After clicking on "+Clicked_ElementName+", application successfully navigated to '"+Navigated_ScreenName_fromHeader+"' screen.");
					break;
				}else{
					Thread.sleep(1000);
				}
			}catch(Throwable tt){
				Thread.sleep(1000);
			}
			if(wait==((Listings_Element_Max_Wait_Time))){
				throw new Exception("FAILED == After clicking on "+Clicked_ElementName+", application  is NOT  navigated to '"+Navigated_ScreenName_fromHeader+"' screen, [ Wait Time ("+((Listings_Element_Max_Wait_Time))+") seconds ], please refer screen shot >> Navigation_Fail"+fnsScreenShot_Date_format());
			}
		}
		}catch(Throwable t){
			isTestCasePass = false;
			fnsTake_Screen_Shot("Navigation_Fail");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		
	}
	




public boolean fns_Verify_and_Return_ScreenName(String ScreenName, boolean JustAfterLogin) throws Throwable{
	String Get_Redirected_ScreenName = "";
	boolean CorrectScreenRedirectDone = false;
	if(ScreenName.toLowerCase().trim().contains("alert")){
		fnsLoading_Progressing_wait(2);
	}
	fnsLoading_Progressing_wait(2);
	try{
		for(int wait=0; wait<=((Listings_Element_Max_Wait_Time)); wait++){
			if( CorrectScreenRedirectDone || (wait==Listings_Element_Max_Wait_Time) ){
				break;
			}else{
				if( (ScreenName.toLowerCase().trim().equals("facility_snapshot")) || (ScreenName.toLowerCase().trim().equals("multifacility_acess")) ){
					Get_Redirected_ScreenName = driver.getCurrentUrl().toLowerCase().trim();
				}else{
					Get_Redirected_ScreenName = TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(OR_Listings.getProperty("View_ScreenName_title"));
				}
				if(Get_Redirected_ScreenName.length()>2){
					if(Get_Redirected_ScreenName.toLowerCase().contains(ScreenName.toLowerCase())){						
						fnsLoading_Progressing_wait(2);
						//fnsLoading_Progressing_wait_AlertLaoder(2);
						CorrectScreenRedirectDone = true;
					//	break;
					}else{
						CorrectScreenRedirectDone = false;
					//	break;
					}
				}else{
					fnsLoading_Progressing_wait(1);
				}	
			}				
		}
		if(JustAfterLogin){
			if(CorrectScreenRedirectDone == true){
				fnsApps_Report_Logs("PASSED == After Login, the Landing ("+Get_Redirected_ScreenName+") Screen is coming  without any error and which is match with the Expected ("+ScreenName+") screen. - automation");
			}else{
				fnsTake_Screen_Shot(ScreenName+"_Screen_Not_Open");
				throw new Exception("FAILED == After Login, the Landing '"+Get_Redirected_ScreenName+"' Screen is coming instead of displaying Expected ("+ScreenName+") screen, please refer screen shot >> "+ScreenName+"_Screen_Not_Open"+fnsScreenShot_Date_format());
			}	
		}
		
	}catch(Throwable t){
		throw new Exception (Throwables.getStackTraceAsString(t));
	}
	return CorrectScreenRedirectDone;
}


public void fns_UploadFile(String Upload_Bttn, String BrowseBttnXpath) throws Throwable{
	try{
		fnsGet_Element_Enabled(Upload_Bttn);
		fnsWait_and_Click(Upload_Bttn);
		fnsLoading_Progressing_wait(2);		
		
		String FileUploadPath = System.getProperty("user.dir") + "\\docs\\This is test pdf document.pdf";
		WebElement Browser = fnsGet_OR_Listings_ObjectX(BrowseBttnXpath);
		Browser.sendKeys(FileUploadPath);
		
		Thread.sleep(1000);
		fnsWait_and_Click_on_RadioButton_by_LabelName(true, "Does the document contains any personal Information?", "1");	
		
		fnsGet_Element_Enabled("CreateIssue_DocumentInfo_Tab_Upload_Add_and_Close_bttn");
		fnsWait_and_Click("CreateIssue_DocumentInfo_Tab_Upload_Add_and_Close_bttn");
			
		Thread.sleep(2000);
		fnsLoading_Progressing_wait(2);
		fnsApps_Report_Logs("PASSED == File uploaded successfully.");	
	}catch(NoSuchWindowException W){
		isTestCasePass = false;
		throw new SkipException(W.getMessage());
	}catch(Throwable t){
		isTestCasePass = false;
		fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
		throw new Exception(Throwables.getStackTraceAsString(t));	}
}	


//Function to Only verification of screen navigation without clicking.
public void fnsVerifyScreenNavigation_afterClickingOnElement(String Current_Click_Element_Name, String Next_Screen_Name, String Next_Screen_Element_Xpath_WithoutOR) throws Throwable{
	try{
		for(int wait=0; wait<=((Listings_Element_Max_Wait_Time)); wait++){
			try{
				if(driver.findElements(By.xpath(Next_Screen_Element_Xpath_WithoutOR)).size()>0){
					if(driver.findElement(By.xpath(Next_Screen_Element_Xpath_WithoutOR)).isDisplayed()){
						fnsApps_Report_Logs("PASSED == After clicking on "+Current_Click_Element_Name+" , application successfully navigated to '"+Next_Screen_Name+"' .");
						fnsLoading_Progressing_wait(1);
						fnsLoading_Progressing_wait(1);
						break;
					}else{
						Thread.sleep(1000);
					}
				}else{
					Thread.sleep(1000);
				}
				if(wait==((Listings_Element_Max_Wait_Time))){
					throw new Exception();
				}
				
			}catch(Throwable t){
				if(wait==((Listings_Element_Max_Wait_Time))){
					fnsTake_Screen_Shot(""+Next_Screen_Name+"_Navigation_Fail");
					throw new Exception("FAILED == After clicking on "+Current_Click_Element_Name+" , application  NOT  navigated to '"+Next_Screen_Name+"' , [ Wait Time ("+((Listings_Element_Max_Wait_Time))+") seconds ], please refer screen shot >> "+Next_Screen_Name+"_Navigation_Fail"+fnsScreenShot_Date_format()+Throwables.getStackTraceAsString(t));
				}else{
					fnsLoading_Progressing_wait(1);
					//Thread.sleep(1000);
				}
			}
		}
	}catch(Throwable t){
	//	isTestCasePass = false;
		fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
}

public void fnsDD_value_Select_By_MatchingText(String ddClickXpathKey, String ddListXpathKey, String Value) throws Throwable {
	boolean ValueNotMatched=true;
	List<WebElement> objectlist5 = null;
	for(int i=1; i<=(5); i++){
		try{
			for(int k=1; k<=(20); k++){
				fnsGet_Element_Enabled(ddClickXpathKey);
				fnsWait_and_Click(ddClickXpathKey);
				
				objectlist5=fnsGet_OR_Listings_ObjectX(ddListXpathKey).findElements(By.tagName("li"));
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
				if(dd_value.getText().equals(Value)){
					dd_value.click();
					ValueNotMatched=false;
					break;
				}
			}
			if(ValueNotMatched==true){
				throw new Exception("Excel value is not matched with DropDown Value.");
			}else{
				fnsApps_Report_Logs("PASSED == Successfully select value [ "+Value+" ] from drop down, having xpath >>  " + ddClickXpathKey);
				break;
			}
		}catch(NoSuchWindowException W){
			isTestCasePass = false;
				throw new Exception(W.getMessage());			
		}catch(Throwable t) {
			if(i==5){
				isTestCasePass = false;
				fnsTake_Screen_Shot("DdValueSelectFail" + ddClickXpathKey);
				fnsApps_Report_Logs("FAILED == Unable to select value from drop down [ " + ddClickXpathKey +fnsScreenShot_Date_format() +" ]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(t).trim());
				throw new Exception("FAILED == Unable to select value from drop down [ " + ddClickXpathKey +" ],plz see screenshot [ DdValueSelectFail" + ddClickXpathKey + fnsScreenShot_Date_format() + " ]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(t).trim());
			}else{
				Thread.sleep(2000);;
			}
		}	
	}
}








public void fnsDD_value_Select_By_MatchingText_and_SelectVerification(String ddClickXpathKey, String ddListXpathKey, String Value) throws Throwable {
	boolean ValueNotMatched=true;
	boolean DD_Selected_Verification = false;
	List<WebElement> objectlist5 = null;
	
	for(int i=1; i<=10; i++){		
		try{
			for(int k=1; k<=(20); k++){
				fnsGet_Element_Enabled(ddClickXpathKey);
				fnsWait_and_Click(ddClickXpathKey);
				Thread.sleep(1000);
				fnsGet_Element_Enabled(ddListXpathKey);
				fnsGet_Element_Displayed(ddListXpathKey);
				objectlist5=fnsGet_OR_Listings_ObjectX(ddListXpathKey).findElements(By.tagName("li"));
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
				if(dd_value.getText().equals(Value)){
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
			objectlist5=fnsGet_OR_Listings_ObjectX(ddListXpathKey).findElements(By.tagName("li"));
			
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









//################################################################### Configuration Function ############################################################################	
	// Function to Launch browser and login
	@SuppressWarnings("finally")
	public boolean fnsBrowserLaunch_LoginIntoiPulse() throws Throwable {
		boolean present = false;
		try{
			present = TestSuiteBase_MonitorPlan.fns_Launch_Browser_Only();			
			TestSuiteBase_MonitorPlan.fnsIpulse_Login_Split_Excel_Urls("iPulse_Listings", null, CONFIG.getProperty("adminUsername"), CONFIG.getProperty("adminPassword"));
			fnsApps_Report_Logs("Browser is launched and Successfully login into the 'iPulse' Application");	
			iPulse_Original_WindowHandle = driver.getWindowHandle();
		}catch(Throwable t)	{
			fnsTake_Screen_Shot("iPulse_Login_Fail");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}	
		return present;
}

	
// Function to Launch browser and login -- iPulse
	@SuppressWarnings("finally")
	public boolean fnsBrowserLaunch_LoginIntoiPulse_thenClickOnListingApplicationMenu_AndSwitchTo_ListingsApplication() throws Throwable {
		boolean present = false;
		ScreenShotFlagWithOR_Listings = true;
		startExecutionTime = fnpTimestamp();
		
		try{
			try{
				present = TestSuiteBase_MonitorPlan.fns_Launch_Browser_Only();		
				TestSuiteBase_MonitorPlan.fnsIpulse_Login_SSO("Listing", "", CONFIG.getProperty("adminUsername"), CONFIG.getProperty("adminPassword"));
			//	TestSuiteBase_MonitorPlan.fnsIpulse_Login_Split_Excel_Urls("ipulse_Listings", CONFIG.getProperty("Grip_LoginAs"), CONFIG.getProperty("adminUsername"), CONFIG.getProperty("adminPassword"));
				fnsApps_Report_Logs("Browser is launched and Successfully login into the 'iPulse' Application");	
			}catch(Throwable t)	{
				fnsTake_Screen_Shot("iPulse_Login_Fail");
				throw new Exception(Throwables.getStackTraceAsString(t));
			}
			
			
			iPulse_Original_WindowHandle = driver.getWindowHandle();			
			fnsMenu_Ajax_Link_Click_By_PassingAjaxPath_iPulse("iPulse_ListingsApplication_Ajax");						
			Listings_Window_Handle = fnsWindowSwitch_Switch_and_return_Listings_windowHandle_OpenThrough_iPulse(iPulse_Original_WindowHandle);
			
			fnsLoading_Progressing_wait(5);
			fnsLoading_Progressing_wait(2);
			TestSuiteBase_MonitorPlan.fnsComman_BrowserLogin_ElementWait(OR_Listings.getProperty("Listings_Footer"), CONFIG.getProperty("Element_MAX_WaitTime"));
			
			return present;	
		} catch(NoSuchWindowException W){
			throw new Exception(W.getMessage());
		}catch (Throwable t) {
			fnsTake_Screen_Shot("Login Fail");
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));
		}		
}

	
	
	
	public String fnsWindowSwitch_Switch_and_return_Listings_windowHandle_OpenThrough_iPulse(String iPulseWindowHandle) throws Throwable{
		try{
			String ListingsApplication_Window = null;
			for(int a=0; a<=60; a++){ 
				ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
				Integer tabsCount = tabs.size();
				if(tabsCount==2){
					for (int i = 0; i < tabsCount; i++) {
						if( !(iPulseWindowHandle.equalsIgnoreCase(tabs.get(i))) ){
							driver.switchTo().window(tabs.get(i));
							ListingsApplication_Window = driver.getWindowHandle();
							
							fnsApps_Report_Logs("PASSED == Successfully Switch to Listings Application Window.");
							break;
						}
					}
					break;
				}else{
					Thread.sleep(1000);
				}
				if(a==Long.parseLong(CONFIG.getProperty("ElementWaitTime"))){
					throw new Exception(" Listings Application window is not getting open after 60 Seconds wait, please refer the screen shot >> SwitchListingsApplicationWindowFail"+fnsScreenShot_Date_format());
				}
			}
			return ListingsApplication_Window;
		}catch(Throwable t){
			fnsTake_Screen_Shot("SwitchListingsApplicationWindowFail");
			throw new Exception(Throwables.getStackTraceAsString(t));	
		}
	}
		
	
	
	
	
	
//Check for Browser Type defined in Suits Excel 
public void BrowserCheck(){
		for(int i=2; i <= suiteXls.getRowCount("Test Suite") ;i++ ){
			
			if(suiteXls.getCellData("Test Suite", "TSID", i).equalsIgnoreCase("Listings_Suite")){
				BrowserName=suiteXls.getCellData("Test Suite", "Browser", i);
				
				if(BrowserName.equals("")){
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
		TC_Step=1;
		Login_Application_Name = "";
		
		try {
			classNameText=className;
			currentScriptCode=fnpMappingClassNameWithScenarioCode(classNameText).trim();
			
			if (!TestUtil.isTestCaseRunnable(Listings_Suitexls, className)) {
				fnsApps_Report_Logs("Skipping Test Case " + className + " as runmode set to NO");
				throw new SkipException("Skipping Test Case " + className + " as runmode set to NO");
			}
			isTestCasePass = true;
			runmodes = TestUtil.getDataSetRunmodes(Listings_Suitexls, className);	
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
	
	
	public void fnsLoading_PageLoad(long Wait) throws Throwable{
		try{
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			for(int k=1;k<=Wait; k++){
				String Text = (String) jse.executeScript("return document.readyState");//.equals("complete");
				if(Text.equals("complete")){
					fnsApps_Report_Logs("Page is loaded in '"+k+"' seconds.");
					break;
				}else{
					Thread.sleep(1000);
				}
				if(k==Wait){
					fnsTake_Screen_Shot("PageLoad_Fail");
					throw new Exception("FAIL == after '"+Wait+"' seconds wait page is not loading.");
				}
			}
		}catch(Throwable t){
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}

	
	
	public void fnaDD_value_Select_In_Suggestion_Search(String DD_Xpath_Select, String Value_from_Excel, long Wait_Time) throws Exception {
		
		
		try{
			
			fnsGet_Element_Enabled(DD_Xpath_Select);
			fnsWait_and_Type(DD_Xpath_Select, Value_from_Excel);
			
			for(int i=0; i<=Wait_Time; i++){
			
				boolean Value_Matched = false;
				
				try{
					List<WebElement> DDobjectlists=driver.findElements(By.xpath("//div[@id='ex1_dropdown']/div[@class='autocomplete-row ng-scope']/div[1]"));
					fnsApps_Report_Logs("Total no of search results: " + DDobjectlists.size());
					for(WebElement dd_value:DDobjectlists){
						String dd_TEXT = dd_value.getText().toLowerCase().trim();
						if(dd_TEXT.contains(Value_from_Excel.toLowerCase().trim())){
							dd_value.click();
							Value_Matched = true;
							break;
						}
					}
					if(Value_Matched){
						fnsApps_Report_Logs("PASSED == Value ["+Value_from_Excel+"] selection from drop down is done in <"+(i)+">seconds wait, DD having xpath >>  " + DD_Xpath_Select);
						break;
					}else{
						throw new NotContextException("FAILED == Excel value < "+Value_from_Excel+" > is not exists into the drop down ' " + DD_Xpath_Select +" ', please refer screenshot >> DdValueSelect_Fail" +  fnsScreenShot_Date_format() );
					}	
					
				}catch(NotContextException NC){
					if(i==Wait_Time){
						throw new Exception(Throwables.getStackTraceAsString(NC));
					}else{
						Thread.sleep(800);
					}
				}catch(NoSuchWindowException W){
					isTestCasePass = false;
					throw new Exception(W.getMessage());
				}catch(Throwable t) {
					if(i==Wait_Time){
						throw new Exception(Throwables.getStackTraceAsString(t));
					}else{
						Thread.sleep(800);
					}
				}
		}
		}catch(Throwable t){
			isTestCasePass = false;
			TestSuiteBase_MonitorPlan.fnsTake_Screen_Shot_Without_OR("DdValueSelect_Fail");
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception (Throwables.getStackTraceAsString(t));
		}
	}
	
	
	public void fna_UploadFile(String Upload_Bttn) throws Throwable{
		try{
			
			String FileUploadPath = System.getProperty("user.dir") + "\\docs\\This is test pdf document.pdf";
			WebElement Browser = fnsGet_OR_Listings_ObjectX(Upload_Bttn);
			Browser.sendKeys(FileUploadPath);
			fnsLoading_Progressing_wait(2);
			fnsApps_Report_Logs("PASSED == File uploaded successfully.");
			
		}catch(NoSuchWindowException W){
			isTestCasePass = false;
			throw new SkipException(W.getMessage());
		}catch(Throwable t){
			isTestCasePass = false;
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));	}
	}	
	
	

	
	
//check if the suite has to be skip
	@BeforeSuite
	public void checkSuiteSkip() throws Throwable{
		fns_CheckSiteSkip("Listings_Suite");
	}
	
		
	
//Always run after suite
	@AfterSuite(alwaysRun=true)
	public void Finishing_Listings_Suite_Script() throws Throwable {
		ScreenShotFlagWithOR_Listings = false;
		
		/*fnsApps_Report_Logs(Loading_Xpath_Array.toString());*/
		
		fnsApps_Report_Logs("");
		fnsApps_Report_Logs("######################################################## Listings Suite END ######################################################## ");
		fnsApps_Report_Logs("=========================================================================================================================================");
		fnsApps_Report_Logs("");
}	

}
