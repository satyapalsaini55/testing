package nsf.ecap.TraQtion_Suite;

/*TraQtionVendorRegistration: https://testapps.traqtion.com/cusweb/faces/registration/vendor_registration.jspx?partitionOrgUnit=qNms0vA5omgkrc6VawvkCA%3D%3D 
TraQtionPortalTestsiteName: https://testapps.traqtion.com/traqtionportal/traqtionsecureloginmain.jsp
TraQtionCostcoTestsiteName: https://testapps.traqtion.com/traqtionportal/qhportal.jsp
*/import java.awt.Dimension;
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
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import nsf.ecap.base.TestBase;
import nsf.ecap.util.ErrorUtil;
import nsf.ecap.util.TestUtil;
import nsf.ecap.util.Xls_Reader;

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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.google.common.base.Throwables;

public class TestSuiteBase_TraQtion extends TestBase{
	public String classNameText = null;
	public long TC_Step = 0;
	public boolean fail = false;
	public boolean skip = true;
	public String Login_UserName = null;
	public String Login_Password = null;
	public String Login_As = null;
	public boolean ApplicationVersion_Flag = true;
	public String validation_error_msg_text = null;
	public String BrowserName="IE";
	public boolean BrowserDriver = false;
	public boolean LoadingImageFlag = true;
	public boolean TraQtionOnline_LoadingImageFlag = true;
	public Integer TimeOut;
	public Integer TraQtion_tabsCount;
	public boolean isTestCasePass = true;
	public boolean IsBrowserPresentAlready = false;
	
	public String runmodes[] = null;
	public Integer ColumnNo;
	public Integer RowCount;

	public boolean TCSkipReturnValue=false;
	public  static  boolean ScreenShotFlagWithOR_TraQtion = false;
	public String TraQtionPortal_Window;
	public String TraQtion_Window;
	public String TraQtionCostcoRegistration;
	
	public String PageSourceText = null;
	public String ErrorMsgText = null;
	
	public Integer Actual_Loading_Time;
	
	public String TraQtionRegistration_Registration_Step1_CorporateInfo_CompanyName = null; 
	public String PortalNamne = "";


	
//######################################################### Common Functions #######################################################################		
	
	
//Function to Take Screen Shot.
public void fnsTake_Screen_Shot(String message) throws Exception {
	String MessageAfterFormat=TestSuiteBase_MonitorPlan.fnsRemoveFormatting_for_FileName(message);
		try{
			String Suite_Foler_Name = "screenshots_TraQtion";
			String File_Name = MessageAfterFormat+"_"+fnsScreenShot_Date_format()+".PNG";
			FileUtils.forceMkdir(new File((System.getProperty("user.dir") +  screenshots_path+ "//"+Suite_Foler_Name+"//"+currentScriptCode +"//")));
			File image = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);			
			FileUtils.copyFile(image, new File((System.getProperty("user.dir") +  screenshots_path+ "//"+Suite_Foler_Name+"//"+currentScriptCode +"//"+File_Name)));
			
			/*
		   FileUtils.forceMkdir(new File((System.getProperty("user.dir") +  screenshots_path+ "//screenshots_TraQtion//"+currentScriptCode +"//")));
		   Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		   Rectangle screenRectangle = new Rectangle(screenSize);
		   Robot robot = new Robot();
		   BufferedImage image = robot.createScreenCapture(screenRectangle);
		   ImageIO.write(image, "png", new File((System.getProperty("user.dir") +  screenshots_path+ "//screenshots_TraQtion//"+currentScriptCode +"//"+MessageAfterFormat+"_"+fnsScreenShot_Date_format()+".PNG")));*/
		}catch(java.lang.NullPointerException n){
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





//Function for TraQtion Creation Text field Date Format
public String fnsTraQtionCreationText_Date_format() {
	DateFormat dateFormat = new SimpleDateFormat("ddMMMyyyy/HH:mm z");
	Date date = new Date();
	return (dateFormat.format(date));
}






//Function for Screen date format 
public String fnsScreenShot_Date_format() {
	DateFormat dateFormat = new SimpleDateFormat("_dd.MM.yyyy_HH.mm.ss");
	Date date = new Date();
	return (dateFormat.format(date));
}




//Function for Screen date format 
public String fnsEditTraQtion_Date_format() {
	DateFormat dateFormat = new SimpleDateFormat("MMddyyyy");
	Date date = new Date();
	return (dateFormat.format(date));
}

//Function for Screen date format 
public String fnsRequried_Current_Date_format(String DateFormat) {
	DateFormat dateFormat = new SimpleDateFormat(DateFormat);
	Date date = new Date();
	return (dateFormat.format(date));
}




//Function For Application Log 
public void fnsApps_Report_Logs(String LogMessage) {
		
	LogMessage = LogMessage.replaceAll("°", "");
	LogMessage = LogMessage.replaceAll("©", "");

	if((LogMessage.toLowerCase().contains("pause"))){
		//nothing to do
	}else if( (LogMessage.toLowerCase().contains("failed")) || (LogMessage.toLowerCase().contains("java")) || (LogMessage.toLowerCase().contains("webdriver")) || (LogMessage.toLowerCase().contains("assert")) ){ 
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
		
	}else if( (LogMessage.toLowerCase().contains("[bs-")) || (LogMessage.toLowerCase().contains("execution")) || (LogMessage.toLowerCase().contains("runmode")) || (LogMessage.toLowerCase().contains("test case"))){
		Reporter.log("========================================================================================================================================");
		Reporter.log(LogMessage);
		Reporter.log(" ");
	}
	APP_LOGS.debug(LogMessage);
	System.out.println(LogMessage);
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
	





public void fnsLoading_Progressingwait(int waitcount) throws Throwable{
	try{
		LoadingImageFlag = true;
		Actual_Loading_Time = 1;
		TimeOut = 0;
		for(int wait=2; wait<=((Long.parseLong(CONFIG.getProperty("Element_MAX_WaitTime")))); wait++){
			if(LoadingImageFlag){
				Thread.sleep(1000);
			//	System.out.println("====================Enter Thread"+wait);
			}
			if( (driver.findElement(By.xpath(OR_TraQtion.getProperty("IPulse_Loading3"))).isDisplayed()) || (driver.findElements(By.xpath(OR_TraQtion.getProperty("IPulse_Loading"))).size()>0) || (driver.findElements(By.xpath(OR_TraQtion.getProperty("IPulse_Loading2"))).size()>0)   ){ //|| (driver.findElement(By.xpath(OR_TraQtion.getProperty("IPulse_Loading"))).isDisplayed())Not working
				Actual_Loading_Time++;
				Thread.sleep(1000);
				LoadingImageFlag = false;
			//	System.out.println(driver.findElement(By.xpath(OR_TraQtion.getProperty("IPulse_Loading3"))).isDisplayed());
			}
			if( (  !(driver.findElement(By.xpath(OR_TraQtion.getProperty("IPulse_Loading3"))).isDisplayed()) ) &&  ((driver.findElements(By.xpath(OR_TraQtion.getProperty("IPulse_Loading"))).size())==0)  && ((driver.findElements(By.xpath(OR_TraQtion.getProperty("IPulse_Loading2"))).size())==0) && wait > waitcount ){
				fnsApps_Report_Logs("***** Loading image displayed for < "+Actual_Loading_Time+" > seconds. *****");
				break;
			}
						
			TimeOut = wait;	
			if(TimeOut==(Long.parseLong(CONFIG.getProperty("Element_MAX_WaitTime")))){
			//	System.out.println("====================Enter timeout"+TimeOut);
				throw new Exception("Loading Issue : After <"+(TimeOut)+">Seconds wait page is not getting Load, please refer screenshot >>PageLoadFail1" + fnsScreenShot_Date_format());
			}		
		}
		
	
		
		
		
		
	//	System.out.println("Before Page Source ======================================================== "+fnsLOGS_Date_format());
		PageSourceText = driver.getPageSource().toLowerCase();
		if(PageSourceText.contains("we are sorry")){
			try{
				TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element(OR_TraQtion.getProperty("IPulse_MainErrorMsg"));
				throw new IllegalArgumentException();
			}catch(Throwable t){
				ErrorMsgText = "We are sorry...an unexpected error has occurred. Details have been forwarded to the Application Support Team for investigation. If the problem persists, please submit an issue on the Apps Portal.";
				throw new IllegalArgumentException();
			}
		//	ErrorMsgText = "We are sorry...an unexpected error has occurred. Details have been forwarded to the Application Support Team for investigation. If the problem persists, please submit an issue on the Apps Portal.";
		//	throw new IllegalArgumentException();
		}

		if(PageSourceText.contains("ui-messages-error-summary")){		
			if (fnsGet_OR_TraQtion_ObjectX("IPulse_MainErrorMsg").isDisplayed()) {
				TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element(OR_TraQtion.getProperty("IPulse_MainErrorMsg"));
				ErrorMsgText = fnsGet_OR_TraQtion_ObjectX("IPulse_MainErrorMsg").getText();
				throw new IllegalArgumentException();
			}
		}
	//	System.out.println("after Page Source ======================================================== "+fnsLOGS_Date_format());
	}catch(IllegalArgumentException i){
		isTestCasePass = false;
		TestSuiteBase_MonitorPlan.fnsTake_Screen_Shot_Without_OR("UnExpectedError");
		fnsApps_Report_Logs("Getting Un-Expected Application Error<"+ErrorMsgText+">, please refer screenshot >>UnExpectedError" + fnsScreenShot_Date_format()+"  Exception ("+Throwables.getStackTraceAsString(i));
		throw new Exception("Getting Un-Expected Application Error<"+ErrorMsgText+">, please refer screenshot >> UnExpectedError" + fnsScreenShot_Date_format()+"  Exception ("+Throwables.getStackTraceAsString(i));	
	
		
	
		
		
		
	}catch(Throwable t){
		try{	
			if(TimeOut==(Long.parseLong(CONFIG.getProperty("Element_MAX_WaitTime")))){
				throw new Exception("Loading Issue : After <"+(TimeOut)+">Seconds wait page is not getting Load, please refer screenshot >>PageLoadFail2" + fnsScreenShot_Date_format()+"  Exception ("+Throwables.getStackTraceAsString(t)+")");
			}else{
				TimeOut = 0;
			}
			Thread.sleep(2000);
			if(driver.findElements(By.xpath(OR_TraQtion.getProperty("IPulse_Loading"))).size()>0){
				for(int wait=1; wait<Long.parseLong(CONFIG.getProperty("Element_MAX_WaitTime")); wait++){
					if(  (driver.findElements(By.xpath(OR_TraQtion.getProperty("IPulse_Loading"))).size()>0)   || (driver.findElements(By.xpath(OR_TraQtion.getProperty("IPulse_Loading2"))).size()>0)  ){
						Actual_Loading_Time++;
						Thread.sleep(1000);			}
					if(((driver.findElements(By.xpath(OR_TraQtion.getProperty("IPulse_Loading"))).size())==0)){
						fnsApps_Report_Logs("##### Loading image displayed for < "+Actual_Loading_Time+" > seconds. #######");
						break;					}
					TimeOut = wait;		
				}
				if(TimeOut==10){
					throw new Exception("Loading Issue : After <"+TimeOut+">Seconds wait page is not getting Load, please refer screenshot >>PageLoadFail3" + fnsScreenShot_Date_format()+"  Exception ("+Throwables.getStackTraceAsString(t)+")");
				}
			}
		
		}catch(NullPointerException N){
			fnsApps_Report_Logs("Getting loading NullPointer Exception.");
			Thread.sleep(waitcount*1000);
	
		}catch(Throwable tt){
			isTestCasePass = false;
			TestSuiteBase_MonitorPlan.fnsTake_Screen_Shot_Without_OR("PageLoadFail");
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(tt));
			throw new Exception(Throwables.getStackTraceAsString(tt));		}
	}
}







//Function of loading image appear on the screen (Block UI). 
public void fnsLoading_Progressing_TraQtion_All(int waitcount) throws Throwable{
	try{
		String Loading_Image_Xpath = "";
		String Loading_Classes_From_OR = "";
		String Error_Xpath = "";
		String Error_Search_in_PageSource = "messages";
		LoadingImageFlag = false;
		Actual_Loading_Time = 1;
		ArrayList<String> Loading_Xpath_Array = new ArrayList<String>();
		
		
		if( (PortalNamne.toLowerCase().trim()).equalsIgnoreCase("vendor registration")){
			Loading_Classes_From_OR = OR_TraQtion.getProperty("Vendor_Loading").trim();
			Error_Xpath = OR_TraQtion.getProperty("Vendor_Message");
			Error_Search_in_PageSource = "messages";
		}else{
			Loading_Classes_From_OR = OR_TraQtion.getProperty("Vendor_Loading").trim();
			Error_Xpath = OR_TraQtion.getProperty("Vendor_Message");
			Error_Search_in_PageSource = "messages";
		}
		
		for(int wait=2; wait<=((NewNsfOnline_Element_Max_Wait_Time)); wait++){
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
			

			try{
				if(driver.findElement(By.xpath(Loading_Image_Xpath)).isDisplayed()){
					Thread.sleep(1000);
					LoadingImageFlag = true;
				}
			}catch(Throwable n){  Thread.sleep(250); }
			
			
			
			try{	
				if(  !(driver.findElement(By.xpath(Loading_Image_Xpath)).isDisplayed()) && wait > waitcount &&  LoadingImageFlag == true ){
					Loading_Xpath_Array.add(Loading_Image_Xpath+"&&");
					fnsApps_Report_Logs("Progressing Loading image is displayed for < "+(Actual_Loading_Time)+" > seconds.");
					break;	
				}else if(  !(driver.findElement(By.xpath(Loading_Image_Xpath)).isDisplayed()) && wait > waitcount &&  LoadingImageFlag == false ){
					fnsApps_Report_Logs("******  Pause/Wait is for < "+Actual_Loading_Time+" > seconds.  *****  "); //To increase performance, Pause/Wait time can set to 1 sec
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
						
			if(wait==(NewNsfOnline_Element_Max_Wait_Time)){
				throw new InterruptedException("Loading Issue : After < "+(NewNsfOnline_Element_Max_Wait_Time)+" > Seconds wait page is not getting Load, please refer screenshot >> PageLoadFail" + fnsScreenShot_Date_format() +"*****  < "+Loading_Image_Xpath+" >");
			}
			Actual_Loading_Time++;
		}
		
		if( (PortalNamne.toLowerCase().trim()).contains("vendor registration")){
			if(driver.findElements(By.xpath(Error_Xpath)).size()>0){
				WebElement Error = driver.findElement(By.xpath(Error_Xpath));
				if (Error.isDisplayed()) {
					Actions act = new Actions(driver);
					act.moveToElement(Error).build().perform();
					ErrorMsgText = Error.getText().trim();
					throw new IllegalArgumentException();
				}
			}
		}else {
			PageSourceText = driver.getPageSource().toLowerCase();
			if(PageSourceText.contains(Error_Search_in_PageSource.toLowerCase())){	
				WebElement Error = driver.findElement(By.xpath(Error_Xpath));
				if (Error.isDisplayed()) {
					Actions act = new Actions(driver);
					act.moveToElement(Error).build().perform();
					ErrorMsgText = Error.getText().trim();
					throw new IllegalArgumentException();
				}
			}
		}
	}catch(IllegalArgumentException i){
		TestSuiteBase_MonitorPlan.fnsTake_Screen_Shot_Without_OR("UnExpectedError");
		fnsApps_Report_Logs("Getting Un-Expected Application Error <"+ErrorMsgText+">, please refer screenshot >>UnExpectedError" + fnsScreenShot_Date_format()+"*****  <   Exception ("+Throwables.getStackTraceAsString(i));
		throw new Exception("Getting Un-Expected Application Error <"+ErrorMsgText+">, please refer screenshot >> UnExpectedError" + fnsScreenShot_Date_format()+"*****  <  Exception ("+Throwables.getStackTraceAsString(i));	
	}catch(NoSuchElementException n){
		fnsApps_Report_Logs("***** Progressing Loading image displayed for < "+Actual_Loading_Time+" > seconds. **************** NS");
		try{
			fnsApps_Report_Logs("***** Progressing Loading image displayed for < "+Actual_Loading_Time+" > seconds. **************** NS");			
		}catch(Throwable tt){
			isTestCasePass = false;
			TestSuiteBase_MonitorPlan.fnsTake_Screen_Shot_Without_OR("PageLoadFail");
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(tt));
			throw new Exception(Throwables.getStackTraceAsString(tt));		
		}
	}catch(StaleElementReferenceException n){
		fnsApps_Report_Logs("***** Progressing Loading image displayed for < "+Actual_Loading_Time+" > seconds. **************** Stale");
	} catch(NoSuchWindowException W){
		throw new Exception(W.getMessage());
	}catch(InterruptedException ie){
		System.out.println("InterruptedException");
		TestSuiteBase_MonitorPlan.fnsTake_Screen_Shot_Without_OR("PageLoadFail");
		fnsApps_Report_Logs(Throwables.getStackTraceAsString(ie));
		throw new Exception(Throwables.getStackTraceAsString(ie));		
	}catch(Throwable tt){
		TestSuiteBase_MonitorPlan.fnsTake_Screen_Shot_Without_OR("PageLoadFail");
		fnsApps_Report_Logs(Throwables.getStackTraceAsString(tt));
		throw new Exception(Throwables.getStackTraceAsString(tt));		
	}
}





public void fnsLoading_Progressingwait_TraQtionOnline(int waitcount) throws Throwable{
	try{
		TraQtionOnline_LoadingImageFlag = true;
		TimeOut = 0;
		for(int wait=2; wait<=((Long.parseLong(CONFIG.getProperty("Element_MAX_WaitTime")))); wait++){
			if(TraQtionOnline_LoadingImageFlag){
				Thread.sleep(1000);
			//	System.out.println("====================Loading thread"+wait);
			}
			if(driver.findElement(By.id("progressLayer")).isDisplayed()){
				Thread.sleep(1000);
				TraQtionOnline_LoadingImageFlag = false;
				//System.out.println("====================Loading Enabled"+wait);
				//System.out.println("Loading Img value ======================== "+driver.findElement(By.xpath(OR_TraQtion.getProperty("LoadingImg"))).isDisplayed());
			}
			if(!(driver.findElement(By.id("progressLayer")).isDisplayed()) && wait > waitcount){
			//	System.out.println("====================Loading Completed"+wait);
				break;
			}
						
			TimeOut = wait;	
			if(TimeOut==(Long.parseLong(CONFIG.getProperty("Element_MAX_WaitTime")))){
			//	System.out.println("====================Enter timeout"+TimeOut);
				throw new Exception("Loading Issue : After <"+(TimeOut)+">Seconds wait page is not getting Load, please refer screenshot >>PageLoadFail1" + fnsScreenShot_Date_format());
			}		
		}
	
		
		
		PageSourceText = driver.getPageSource().toLowerCase();
		if(PageSourceText.contains("validationmessages")){		
			if (fnsGet_OR_TraQtion_ObjectX("NSFOnline_ErrorMsg").isDisplayed()) {
				WebElement Element = fnsGet_OR_TraQtion_ObjectX("NSFOnline_ErrorMsg");
				Actions act = new Actions(driver);
				act.moveToElement(Element).build().perform();
				ErrorMsgText = fnsGet_OR_TraQtion_ObjectX("NSFOnline_ErrorMsg").getText();
				throw new IllegalArgumentException();
			}
		}
	}catch(IllegalArgumentException i){
		isTestCasePass = false;
		TestSuiteBase_MonitorPlan.fnsTake_Screen_Shot_Without_OR("UnExpectedError");
		fnsApps_Report_Logs("Getting Un-Expected Application Error<"+ErrorMsgText+">, please refer screenshot >>UnExpectedError" + fnsScreenShot_Date_format()+"  Exception ("+i.getMessage()+")");
		throw new Exception("Getting Un-Expected Application Error<"+ErrorMsgText+">, please refer screenshot >> UnExpectedError" + fnsScreenShot_Date_format()+"  Exception ("+i.getMessage()+")");	
		
			
		
		
	}catch(Throwable t){
		try{
		//	System.out.println("=============================== Laoding throwable =============");
			if(TimeOut==(Long.parseLong(CONFIG.getProperty("Element_MAX_WaitTime")))){
				throw new Exception("Loading Issue : After <"+(TimeOut)+">Seconds wait page is not getting Load, please refer screenshot >>PageLoadFail2" + fnsScreenShot_Date_format()+"  Exception ("+t.getMessage()+")");
			}else{
				TimeOut = 0;
			}
			Thread.sleep(5000);
			for(int wait=1; wait<11; wait++){
				if(driver.findElement(By.id("progressLayer")).isDisplayed()){
					Thread.sleep(1000);	
				}else{
					break;					
				}
				TimeOut = wait;		
			}
			if(TimeOut==10){
				throw new Exception("Loading Issue : After <"+((Long.parseLong(CONFIG.getProperty("Element_MAX_WaitTime")))+TimeOut)+">Seconds wait page is not getting Load, please refer screenshot >>PageLoadFail3" + fnsScreenShot_Date_format()+"  Exception ("+t.getMessage()+")");
			}
		

		}catch(NullPointerException N){
			fnsApps_Report_Logs("Getting loading NullPointer Exception.");
			Thread.sleep(waitcount*1000);
	
		}catch(InvalidSelectorException k){
			fnsApps_Report_Logs("Getting loading InvalidSelector Exception.");
			Thread.sleep(waitcount*1000);
		}catch(NoSuchElementException NE){
			fnsApps_Report_Logs("Getting loading NoSuchElement Exception.");
			Thread.sleep(waitcount*1000);
		}catch(Throwable tt){
			isTestCasePass = false;
			TestSuiteBase_MonitorPlan.fnsTake_Screen_Shot_Without_OR("PageLoadFail");
			fnsApps_Report_Logs(tt.getMessage());
			throw new Exception(tt.getMessage());		}
	}
}




//Function to find and return the object using Xpath
public WebElement fnsGet_OR_TraQtion_ObjectX(String XpathKey) throws Exception{
			
	try {
		for (int waits=0; waits<NewNsfOnline_Element_Max_Wait_Time; waits++) {
			
			if (driver.findElements(By.xpath(OR_TraQtion.getProperty(XpathKey))).size() > 0) {break;}
			else{Thread.sleep(500);}
			
		}return driver.findElement(By.xpath(OR_TraQtion.getProperty(XpathKey))); 
		
	}catch(StaleElementReferenceException e){
		WebDriverWait stalewaitvar = new WebDriverWait(driver, 10);
		stalewaitvar.until(ExpectedConditions.presenceOfElementLocated(By.xpath(OR_TraQtion.getProperty(XpathKey))));
		WebElement webelemnt=driver.findElement(By.xpath(OR_TraQtion.getProperty(XpathKey)));
		stalewaitvar.until(ExpectedConditions.stalenessOf(webelemnt));
		return driver.findElement(By.xpath(OR_TraQtion.getProperty(XpathKey)));}
	catch(NoSuchWindowException W){
		isTestCasePass = false;
		throw new Exception( W.getMessage());		}
	catch(NoSuchElementException e){
		isTestCasePass = false;
		fnsTake_Screen_Shot("NoSuchElementException" + XpathKey);
		fnsApps_Report_Logs("FAILED == Element is not found >> "+XpathKey + " ] , plz see screenshot [NoSuchElementException" + XpathKey + fnsScreenShot_Date_format() + " ]"+". Getting Exception  >> "+e.getMessage());
		throw new Exception("FAILED == Element is not found >> "+XpathKey + " ] , plz see screenshot [NoSuchElementException" + XpathKey + fnsScreenShot_Date_format() + " ]"+". Getting Exception  >> "+e.getMessage());}
	catch(TimeoutException e){
		isTestCasePass = false;
		fnsTake_Screen_Shot("TimeOut" + XpathKey);
		fnsApps_Report_Logs("FAILED == Element is not Present, getting TimeOut >> "+XpathKey + " ] , plz see screenshot [TimeOut" + XpathKey + fnsScreenShot_Date_format() + " ]"+". Getting Exception  >> "+e.getMessage());
		throw new Exception("FAILED == Element is not Present, getting TimeOut >> "+XpathKey + " ] , plz see screenshot [TimeOut" + XpathKey + fnsScreenShot_Date_format() + " ]"+". Getting Exception  >> "+e.getMessage());}
	catch(Throwable e){
		isTestCasePass = false;
		fnsTake_Screen_Shot("NotPresent" + XpathKey);
		fnsApps_Report_Logs("FAILED == Element is not found >> "+XpathKey + " ] , plz see screenshot [NotPresent" + XpathKey + fnsScreenShot_Date_format() + " ]"+". Getting Exception  >> "+e.getMessage());
		throw new Exception("FAILED == Element is not found >> "+XpathKey + " ] , plz see screenshot [NotPresent" + XpathKey + fnsScreenShot_Date_format() + " ]"+". Getting Exception  >> "+e.getMessage());}
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
			fnsGet_OR_TraQtion_ObjectX(XpathKey).click();
			fnsApps_Report_Logs("PASSED == Click done on Element having Xpath >> "+XpathKey);
		}catch(Throwable tt){
			Thread.sleep(3000);
			fnsGet_OR_TraQtion_ObjectX(XpathKey).click();
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
	while(true){
		Integer Element_Size = driver.findElements(By.xpath(WithoutOR_XpathKey)).size();
		boolean Click_Done = false;
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
				fnsApps_Report_Logs("After 5 try the click is still getting fail on element (), as the element is not present into the DOM, please see screenshot >> Click_FAIL_" + Element_Name + fnsScreenShot_Date_format() +Throwables.getStackTraceAsString(e));
				throw new Exception("After 5 try the click is still getting fail on element (), as the element is not present into the DOM, please see screenshot >> Click_FAIL_" + Element_Name + fnsScreenShot_Date_format() +Throwables.getStackTraceAsString(e));
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
}





/*//Function to wait for element and then type
	public void fnsGet_Element_Tobe_Clickable(String XpathKey) throws Exception {
				
		try{
			WebDriverWait elementwaitvar = new WebDriverWait(driver, NewNsfOnline_Element_Max_Wait_Time);
			fnsGet_OR_TraQtion_ObjectX(XpathKey);
			elementwaitvar.until(ExpectedConditions.elementToBeClickable(By.xpath(OR_TraQtion.getProperty(XpathKey)))).wait();
		}catch(TimeoutException e){
			isTestCasePass = false;
			fnsTake_Screen_Shot(""+XpathKey);
			fnsApps_Report_Logs("FAILED == Clickable, Element is not Present having Xpath  >> "+XpathKey+", plz see screenshot [ " + ""+XpathKey + fnsScreenShot_Date_format() + " ]");
			throw new Exception("FAILED == Clickable, Element is not Present having Xpath  [ " + XpathKey + " ] , plz see screenshot [ " + ""+XpathKey + fnsScreenShot_Date_format() + " ]");}
		catch(NoSuchWindowException W){
			isTestCasePass = false;
			throw new Exception( W.getMessage());		}
}	*/

	
	
	
	
	

//Click on Link by matchingText Xpath
public void fnsTable_ClickOn_LINK_ByMatchingText(String LinkMatchingText) throws Throwable{
	
	try{
		String SearchResult_Code_link_Xpath = ".//a[text()='" + LinkMatchingText.trim() + "']"; 
		
		TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(SearchResult_Code_link_Xpath);
		
		driver.findElement(By.xpath(SearchResult_Code_link_Xpath)).click();
		fnsApps_Report_Logs("PASSED == click done on the Link >> "+LinkMatchingText);
	}catch(NoSuchWindowException W){
		isTestCasePass = false;
		throw new Exception( W.getMessage());		}
	
	catch(Throwable t){
		isTestCasePass = false;
		fnsTake_Screen_Shot("LinkClickFail");
		fnsApps_Report_Logs("FAILED == Unable to Click on Link >> "+LinkMatchingText+", Please refer ScreenShot [ "+"LinkClickFail"+fnsScreenShot_Date_format() +" ]."+". Getting Exception  >> "+t.getMessage());
		throw new Exception("FAILED == Unable to Click on Link >> "+LinkMatchingText+", Please refer ScreenShot [ "+"LinkClickFail"+fnsScreenShot_Date_format() +" ]. Getting Exception  >> "+t.getMessage());}	
}




//Function to type
public void fnsWait_and_Type(String XpathKey, String value) throws Exception {
	try{
		try{
			//fnsGet_OR_TraQtion_ObjectX(XpathKey).sendKeys("");
			fnsGet_OR_TraQtion_ObjectX(XpathKey).sendKeys(value);
			fnsApps_Report_Logs("PASSED == Type Value [ "+value+" ] done on Element having Xpath  >> "+XpathKey);
		}catch(Throwable tt){
			Thread.sleep(3000);
			fnsGet_OR_TraQtion_ObjectX(XpathKey).sendKeys("");
			fnsGet_OR_TraQtion_ObjectX(XpathKey).sendKeys(value);
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





	


//Function to wait for element
public void fnsGet_Element_Enabled(String XpathKey) throws Exception {
	
	try{
		for(int wait=0; wait<3; wait++){
			if(driver.findElements(By.xpath(OR_TraQtion.getProperty(XpathKey))).size()>0){
			//	fnsGet_OR_TraQtion_ObjectX(XpathKey);
				WebDriverWait elementwaitvar = new WebDriverWait(driver, NewNsfOnline_Element_Max_Wait_Time);
				elementwaitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_TraQtion.getProperty(XpathKey))));
		
				WebDriverWait elementwaitvar1 = new WebDriverWait(driver, NewNsfOnline_Element_Max_Wait_Time);
				elementwaitvar1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_TraQtion.getProperty(XpathKey)))).isEnabled();
				
				WebDriverWait elementwaitvar2 = new WebDriverWait(driver, NewNsfOnline_Element_Max_Wait_Time);
				elementwaitvar2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_TraQtion.getProperty(XpathKey)))).isDisplayed();
				
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
			WebDriverWait elementwaitvar3 = new WebDriverWait(driver, (NewNsfOnline_Element_Max_Wait_Time));
			elementwaitvar3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_TraQtion.getProperty(XpathKey)))).isEnabled();//}
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
		for(int wait=0; wait<=( (NewNsfOnline_Element_Max_Wait_Time)*2 ); wait++){
			try{
				if(fnsGet_OR_TraQtion_ObjectX(XpathKey).isDisplayed()){
					break;
				}else{
					Thread.sleep(500);
				}
			}catch(Throwable t){
				Thread.sleep(500);
				//nothing to do
			}
			if(wait==NewNsfOnline_Element_Max_Wait_Time){
				throw new Exception("FAILED == Element is not displayed, after <"+( (NewNsfOnline_Element_Max_Wait_Time) )+"> seconds wait, please refer screenshot >> "+fnsScreenShot_Date_format());
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


//function to select drop down value ---- Satya Pal
public void fnsDD_value_Select_By_MatchingText_DownKeyPress(String ddClickXpathKey, String ddListXpathKey, String TagName, String Value) throws Throwable {
	boolean ValueNotMatched=true;
	List<WebElement> objectlist5 = null;
	for(int i=1; i<=(2); i++){
		try{
			for(int k=1; k<=(20); k++){
				fnsGet_Element_Enabled(ddClickXpathKey);
				Actions move = new Actions(driver);
				move.moveToElement( fnsGet_OR_TraQtion_ObjectX(ddClickXpathKey) ).click().build().perform();
				fnsApps_Report_Logs("PASSED == Click done on element >> "+ddClickXpathKey);
				
				objectlist5=fnsGet_OR_TraQtion_ObjectX(ddListXpathKey).findElements(By.tagName(TagName));
				if(objectlist5.size()>1){
					Thread.sleep(1000);
					break;
				}else{
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
				}
				if(k==20){
					throw new Exception ("Drop down is not getting load, after 40 seconds wait.");
				}
			}
			fnsApps_Report_Logs("PASSED == Count of No. of values coming in Drop down list is = "+objectlist5.size());
			for(WebElement dd_value:objectlist5){
				Actions act = new Actions(driver);
				act.sendKeys(Keys.ARROW_DOWN).build().perform();
				if(dd_value.getText().equals(Value)){
					Actions act1 = new Actions(driver);
					act1.moveToElement(dd_value).click().build().perform();
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
			if(i==2){
				isTestCasePass = false;
				fnsTake_Screen_Shot("DdValueSelectFail" + ddClickXpathKey);
				fnsApps_Report_Logs("FAILED == Unable to select value from drop down [ " + ddClickXpathKey +" ],plz see screenshot [ DdValueSelectFail" + ddClickXpathKey + fnsScreenShot_Date_format() +" ]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(t).trim());
				throw new Exception("FAILED == Unable to select value from drop down [ " + ddClickXpathKey +" ],plz see screenshot [ DdValueSelectFail" + ddClickXpathKey + fnsScreenShot_Date_format() + " ]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(t).trim());
			}else{
				TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
			}
		}	
	}
}


//############################################################## Application Functions ###########################################################################################
public void fnsVerify_Application_Navigated_To_Next_Designated_Screen_WithoutOR(String Click_Element_Name, String Navigate_Screen_Name, String Navigated_Screen_Element_Xpath) throws Throwable{
	try{
		for(int wait=0; wait<=((NewNsfOnline_Element_Max_Wait_Time)); wait++){
			try{
				if(driver.findElements(By.xpath(Navigated_Screen_Element_Xpath)).size()>0){
					fnsApps_Report_Logs("PASSED == After clicking on "+Click_Element_Name+", application is successfully navigated to '"+Navigate_Screen_Name+"' screen.");
					break;
				}else{
					Thread.sleep(1000);
				}
			}catch(Throwable tt){
				Thread.sleep(1000);
			}
			if(wait==((NewNsfOnline_Element_Max_Wait_Time))){
				throw new Exception("FAILED == After clicking on "+Click_Element_Name+", application is NOT  navigated to '"+Navigate_Screen_Name+"' screen, [ Wait Time ("+((NewNsfOnline_Element_Max_Wait_Time))+") seconds ], please refer screen shot >> "+Navigate_Screen_Name+"_Screen_Not_Open"+fnsScreenShot_Date_format());
			}
		}
		}catch(Throwable t){
			isTestCasePass = false;
			fnsTake_Screen_Shot(Navigate_Screen_Name+"_Screen_Not_Open");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		
	}






public String fnsVerify_Validation_Message_TraQtion() throws Throwable{
	try{
		fnsGet_Element_Enabled("TraQtion_Message_Div");
		String Message_Text = fnsGet_Field_TEXT("TraQtion_Message_Div");
		assert Message_Text.toLowerCase().trim().contains("success") : "FAILED == ''Successful message is NOT coming, some other message ("+Message_Text+") validation message is coming, please refer the screen shot >> Success_Verification_Fail"+fnsScreenShot_Date_format();
		fnsApps_Report_Logs("PASSED == successful message is coming ("+Message_Text+")");
		return Message_Text;
	}catch(Throwable t){
			isTestCasePass = false;
			fnsTake_Screen_Shot("Success_Verification_Fail");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		
	}

















//########################################################################## Application Functions ########################################################################################	
public String fnsGet_Field_TEXT(String XpathKey) throws Exception {
	try{
		
		WebDriverWait elementwaitvar = new WebDriverWait(driver,Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
		elementwaitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_TraQtion.getProperty(XpathKey)))).isEnabled();
		String TextFetch=fnsGet_OR_TraQtion_ObjectX(XpathKey).getText().trim();
		fnsApps_Report_Logs("PASSED == Fetch the Text["+TextFetch+"] on Element having xpath [ " +XpathKey+" ].");
		
		return TextFetch;
	}catch(NoSuchWindowException W){
		isTestCasePass = false;
		throw new Exception( W.getMessage());		}
	
	catch (Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot("TextFetchFail" + XpathKey);
			fnsApps_Report_Logs("FAILED == Unable to Fetch Text on Element having xpath [ " +"TextFetchFail" + XpathKey + fnsScreenShot_Date_format() +" ]."+". Getting Exception  >> "+t.getMessage());
			throw new Exception("FAILED == Unable to Fetch Text on Element having xpath [ " +"TextFetchFail" + XpathKey + fnsScreenShot_Date_format() +" ].");}
	}



	
//function to select drop down value
public void fnsDD_value_Select_By_MatchingText(String ddClickXpathKey, String ddListXpathKey, String TagName, String Value) throws Exception {
	
	boolean ValueNotMatched=true;
	
	try{
			fnsWait_and_Click(ddClickXpathKey);
			fnsGet_Element_Enabled(ddListXpathKey);
			List<WebElement> DDobjectlists=fnsGet_OR_TraQtion_ObjectX(ddListXpathKey).findElements(By.tagName(TagName));
			for(WebElement dd_value:DDobjectlists){
				if(dd_value.getText().equals(Value)){
					dd_value.click();
					ValueNotMatched=false;	
					break;	}
				
			}
			if(ValueNotMatched==true){
				throw new Exception("Excel value is not matched with DropDown Value.");
			}	
						
			fnsApps_Report_Logs("PASSED == select value [ "+Value+" ] from drop down done, having xpath >>  " + ddClickXpathKey);
			
	}catch(NoSuchWindowException W){
			isTestCasePass = false;
			throw new Exception( W.getMessage());		}
	
	catch(Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot("DdValueSelectFail" + ddClickXpathKey);
			fnsApps_Report_Logs("FAILED == Unable to select value from drop down [ " + ddClickXpathKey +fnsScreenShot_Date_format() +" ]"+". Getting Exception  >> "+t.getMessage().trim());
			throw new Exception("Unable to select value from drop down [ " + ddClickXpathKey +" ],plz see screenshot [ DdValueSelectFail" + ddClickXpathKey + fnsScreenShot_Date_format() + " ]");}
}





//Verify Text fetched and matched with expected text.
public void fnsText_Fetch_and_Assert(String XpathKey, String MatchingText) throws Exception {
	
	TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element(OR_TraQtion.getProperty(XpathKey));
	String GetText=fnsGet_Field_TEXT(XpathKey).trim().toLowerCase();	
	try{
		assert GetText.contains(MatchingText.toLowerCase()):"FAILED == Expected Text ("+MatchingText+") is not matched with the Actual Text ("+GetText+"), Please refer Screen shot >> TextNotMatch"+fnsScreenShot_Date_format();
		fnsApps_Report_Logs("PASSED ==  Expected Text ("+MatchingText+") is matched with the Actual Text ("+GetText+"), from element having xpath >> "+XpathKey);
		
	}catch(NoSuchWindowException W){
		isTestCasePass = false;
		throw new Exception( W.getMessage());		}
	
	catch(Throwable t){
		isTestCasePass = false;
		fnsTake_Screen_Shot("TextNotMatch");
		fnsApps_Report_Logs(t.getMessage());
		throw new Exception(t.getMessage());		}
}	



/**/


	//Return Row Number of matching TEXT in Table Cell
	public Integer fnsReturn_RowNumber_By_Matching_TEXT(String TableHeaderXpath,  String MatchingText) throws Throwable{
			
	try{	
			int RowNumber=0;
			fnsGet_Element_Enabled(TableHeaderXpath);
			
			WebElement stdtable=fnsGet_OR_TraQtion_ObjectX(TableHeaderXpath);
			List<WebElement> no_of_rows_in_list = stdtable.findElements(By.tagName("tr"));
			//System.out.println("no_of_rows_in_list.size()="+no_of_rows_in_list.size());
			
			for(WebElement countrows:no_of_rows_in_list){
				RowNumber++;
				String RowText=countrows.getText();
				if(RowText.contains(MatchingText)){
					break;
					}else if(no_of_rows_in_list.size()==RowNumber){
						throw new Exception("("+MatchingText+")Text is not found into the Table");
					}
					
				
			}
			
			//System.out.println("Fetching I Value ==== "+RowNumber);
			return RowNumber;
	}catch(NoSuchWindowException W){
		isTestCasePass = false;
		throw new Exception(W.getMessage());			}
	catch(Throwable t) {
		isTestCasePass = false;
		fnsTake_Screen_Shot(MatchingText);
		fnsApps_Report_Logs("FAILED == Fetching RowNumber is getting failed for table having xpaths >> "+TableHeaderXpath+", Please refer screen shot ["+ MatchingText + fnsScreenShot_Date_format() + " ]. Getting Exception >> "+t.getMessage());
		throw new Exception("FAILED == Fetching RowNumber is getting failed for table having xpaths >> "+TableHeaderXpath+", Please refer screen shot ["+ MatchingText + fnsScreenShot_Date_format() + " ]. Getting Exception >> "+t.getMessage());}
		
	}



	//Clicking on Menu Ajax Link	
		public void fncAjax_Link_Click_By_Passing_TwoAjaxXPathx(String FirstAjaxXpaths, String SecondAjaxXpaths, String HeaderElementXpath) throws Exception {
			try{
				fnsGet_Element_Enabled(FirstAjaxXpaths);
				WebElement First_Element = fnsGet_OR_TraQtion_ObjectX(FirstAjaxXpaths);
				
				if(HeaderElementXpath==null){
					//nothing to do
				}else{
					fnsWait_and_Click(HeaderElementXpath);	
				}
				
				Actions action = new Actions(driver);
				action.moveToElement(First_Element).build().perform();
						
				Thread.sleep(500);
				Actions action1 = new Actions(driver);
				fnsGet_Element_Enabled(SecondAjaxXpaths);
				WebElement CreateContractorApplicant = fnsGet_OR_TraQtion_ObjectX(SecondAjaxXpaths);
				action1.moveToElement(CreateContractorApplicant).click().build().perform();
				fnsApps_Report_Logs("PASSED == Successfully Click on <"+(SecondAjaxXpaths)+">.");	
			}catch(NoSuchWindowException W){
				isTestCasePass = false;
				throw new Exception(W.getMessage());			}
			catch(Throwable t) {
				isTestCasePass = false;
				fnsTake_Screen_Shot(SecondAjaxXpaths+"_Fail");
				fnsApps_Report_Logs("FAILED == Clicking on <"+(SecondAjaxXpaths)+"> Failed, plz see screenshot [" +SecondAjaxXpaths+"_Fail" + fnsScreenShot_Date_format() + "]"+". Getting Exception  >> "+t.getMessage().trim());
				throw new Exception("FAILED == Clicking on <"+(SecondAjaxXpaths)+"> Failed, plz see screenshot [" + SecondAjaxXpaths+"_Fail" + fnsScreenShot_Date_format() + "]"+". Getting Exception  >> "+t.getMessage().trim());}
	}

	//Clicking on Menu Ajax Link	
	public void fnsMenu_Ajax_Link_Click_By_PassingAjaxPath_TraQtion_WithoutOR(String Menu_Name, String MenuAjaxLink_Xpath) throws Exception {
		try{
			fnsGet_Element_Enabled("TraQtion_Main_Menu_Ajax_Link");
			TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
			WebElement Menu_Element = fnsGet_OR_TraQtion_ObjectX("TraQtion_Main_Menu_Ajax_Link");
			
			WebElement VersionLogoImage = fnsGet_OR_TraQtion_ObjectX("TraQtion_VersionLogoImage");
			VersionLogoImage.click();
			Thread.sleep(500);
			
			Actions action = new Actions(driver);
			action.moveToElement(Menu_Element).build().perform();
					
			Thread.sleep(500);
			Actions action1 = new Actions(driver);
			TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(MenuAjaxLink_Xpath);
			WebElement AjaxLinkXpath_Element = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(MenuAjaxLink_Xpath);
			action1.moveToElement(AjaxLinkXpath_Element).click().build().perform();
			fnsApps_Report_Logs("PASSED == Successfully Click on Menu <"+(Menu_Name)+">.");
		}catch(NoSuchWindowException W){
			throw new Exception(W.getMessage());			}
		catch(Throwable t) {
			fnsTake_Screen_Shot(Menu_Name+"_Fail");
			fnsApps_Report_Logs("FAILED == Clicking on <"+(Menu_Name)+"> Menu Failed, plz see screenshot [" +Menu_Name+"_Fail" + fnsScreenShot_Date_format() + "]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(t).trim());
			throw new Exception("FAILED == Clicking on <"+(Menu_Name)+"> Menu Failed, plz see screenshot [" + Menu_Name+"_Fail" + fnsScreenShot_Date_format() + "]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(t).trim());
		}
	}	
	
//################################################################### Configuration Function ############################################################################	
/*	
	// Function to Launch browser and login
	public boolean fnsTraQtionPortal_LaunchBrowserAndLogin() throws Throwable {
		boolean present;
		startExecutionTime = fnpTimestamp();
		ScreenShotFlagWithOR_TraQtion = true;
		
		try {
			if (BrowserName.equalsIgnoreCase("IE")) {
				System.setProperty("webdriver.ie.driver", CONFIG.getProperty("IEDriverPath"));
				
				DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
				caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				//caps.setCapability("IGNORE_ZOOM_SETTING", true);  // Not Working
			    caps.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
			    caps.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
			    caps.setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP, true);
			    caps.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
			   			
			    driver=new InternetExplorerDriver(caps);
			    fnsApps_Report_Logs("Browser type is IE");}
			
			
			if (BrowserName.equalsIgnoreCase("chrome")) {
				
				System.setProperty("webdriver.chrome.driver", CONFIG.getProperty("ChromeDriverPath"));
				driver=new ChromeDriver();
				fnsApps_Report_Logs("Browser type is CHROME");}
			
			

			if (BrowserName.equalsIgnoreCase("firefox")) {
				
				
				DesiredCapabilities FFcaps = DesiredCapabilities.firefox();
				FFcaps.setCapability("security.mixed_content.block_active_content", false);
				FFcaps.setCapability("security.mixed_content.block_display_content", true);
				
				driver = new FirefoxDriver();
				fnsApps_Report_Logs("Browser type is Firefox");}


			String userName = CONFIG.getProperty("TraQtionPortalUserName");
			String password = CONFIG.getProperty("TraQtionPortalPassword");
		//	String siteUrl = CONFIG.getProperty("TraQtionPortalTestsiteName");
			
			
			String siteUrl = null;

			if (excelSiteURL!=null) {
				if (excelSiteURL.equalsIgnoreCase("")) {
					siteUrl = CONFIG.getProperty("TraQtionPortalTestsiteName");
				} else {
					
					String SplitExcelUrlValue = excelSiteURL.split("TraQtionSurveyTestsiteName:")[1];
					
					String TraQtionPortalUrl = SplitExcelUrlValue.split("TraQtionPortalTestsiteName:")[1].trim();
								
					siteUrl=TraQtionPortalUrl;
					
				}
			} else {
				siteUrl = CONFIG.getProperty("TraQtionPortalTestsiteName");
			}
			
			
			driver.manage().deleteAllCookies();
			driver.get(siteUrl);
			  
			TraQtionPortalWindow = driver.getWindowHandle();

			driver.switchTo().frame("loginframe");
			TestSuiteBase_MonitorPlan.fnsComman_BrowserLogin_ElementWait(OR_TraQtion.getProperty("TraQtionPortal_UserName"), CONFIG.getProperty("Element_MAX_WaitTime"));
			
			fnsGet_OR_TraQtion_ObjectX("TraQtionPortal_UserName").clear();
			Thread.sleep(250);
			fnsGet_OR_TraQtion_ObjectX("TraQtionPortal_UserName").sendKeys("");
			Thread.sleep(250);
			fnsGet_OR_TraQtion_ObjectX("TraQtionPortal_UserName").sendKeys(userName);
			Thread.sleep(250);
			fnsGet_OR_TraQtion_ObjectX("TraQtionPortal_password").sendKeys("");
			Thread.sleep(250);
			fnsGet_OR_TraQtion_ObjectX("TraQtionPortal_password").sendKeys(password);
			fnsGet_OR_TraQtion_ObjectX("TraQtionPortal_Login").click();
			Thread.sleep(2000);
			
			
			
			for(int Loginwait=0; Loginwait<Long.parseLong(CONFIG.getProperty("Element_MAX_WaitTime")); Loginwait++){
				driver.switchTo().window(TraQtionPortalWindow);
				if(driver.findElements(By.xpath(OR_TraQtion.getProperty("TraQtionPortal_IpulseLink"))).size()>0){
					TestSuiteBase_MonitorPlan.fnsComman_BrowserLogin_ElementWait(OR_TraQtion.getProperty("TraQtionPortal_IpulseLink"), CONFIG.getProperty("Element_MAX_WaitTime"));
					break;
				}else{
					Thread.sleep(1000);
				}
				if(driver.findElements(By.xpath(OR_TraQtion.getProperty("TraQtionPortal_IFrameXpath"))).size()>0){
					driver.switchTo().frame("loginframe");
				}
				if(driver.findElements(By.xpath(OR_TraQtion.getProperty("TraQtionPortal_LoginErroMsg"))).size()>0){
					throw new Exception("FAILED == 'TraQtion Portal' Login fail, Getting error <"+fnsGet_Field_TEXT("TraQtionPortal_LoginErroMsg").trim()+">");
				}
				if(Loginwait==(Long.parseLong(CONFIG.getProperty("Element_MAX_WaitTime")))-1){
					throw new Exception("FAILED == 'TraQtion Portal' Login fail, Getting TimeOut after <"+Long.parseLong(CONFIG.getProperty("Element_MAX_WaitTime"))+"> Minutes wait.");
				}
			}
			
					
			present = true;
			
		} catch(NoSuchWindowException W){
			isTestPass = false;
			throw new Exception(W.getMessage());			}
		catch (Throwable t) {
			isTestPass = false;
			fnsTake_Screen_Shot("LaunchBrowserAndLoginFail");
			present = false;
			ErrorUtil.addVerificationFailure(t);
			driver.quit();
			fnsApps_Report_Logs(t.getMessage()+", Please refer screen shot [LaunchBrowserAndLoginFail"+fnsScreenShot_Date_format());
			throw new Exception(t.getMessage()+", Please refer screen shot [LaunchBrowserAndLoginFail"+fnsScreenShot_Date_format());		}
	
		return present;
}*/
	
	
	
	
// Function to Launch browser
	public boolean fnsLaunchBrowser() throws Throwable {
		boolean present;
		ScreenShotFlagWithOR_TraQtion = true;
		
		try {

			/*//BrowserName=BrowserCheck();
			if (BrowserName.equalsIgnoreCase("IE")) {
				System.setProperty("webdriver.ie.driver", CONFIG.getProperty("IEDriverPath"));

				DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
				caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				//caps.setCapability("IGNORE_ZOOM_SETTING", true);  // Not Working
			    caps.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
			    caps.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
			    caps.setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP, true);
			    caps.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
			   			
			    driver=new InternetExplorerDriver(caps);
			    fnsApps_Report_Logs("Browser type is IE");}
			
			
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
				
				
				DesiredCapabilities FFcaps = DesiredCapabilities.firefox();
				FFcaps.setCapability("security.mixed_content.block_active_content", false);
				FFcaps.setCapability("security.mixed_content.block_display_content", true);
				
				driver = new FirefoxDriver();
				fnsApps_Report_Logs("Browser type is Firefox");}*/

			TestSuiteBase_MonitorPlan.fns_Launch_Browser_Only();			
			//String siteUrl = CONFIG.getProperty("TraQtionSurveyTestsiteName");
			
			String siteUrl = null;

			if (excelSiteURL!=null) {
				if (excelSiteURL.equalsIgnoreCase("")) {
					siteUrl = CONFIG.getProperty("TraQtionSurveyTestsiteName");
				} else {
					
					String SplitExcelUrlValue = excelSiteURL.split("TraQtionSurveyTestsiteName:")[1];
					
					String TraQtionSurveyUrl = SplitExcelUrlValue.split("TraQtionPortalTestsiteName:")[0].trim();
										
					siteUrl=TraQtionSurveyUrl;
					
				}
			} else {
				siteUrl = CONFIG.getProperty("TraQtionSurveyTestsiteName");
			}
			
			
			driver.get(siteUrl);
			  
					
			present = true;
			fnsApps_Report_Logs("Launch Browser done.");
			
		}catch(NoSuchWindowException W){
			isTestCasePass = false;
			throw new Exception(W.getMessage());
			
		} catch (Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot("LaunchBrowserFail");
			present = false;
		//	ErrorUtil.addVerificationFailure(t);	
			fnsApps_Report_Logs("");
			fnsApps_Report_Logs("'Launch Browser' is getting fail, Please refer screen shot [LaunchBrowserFail"+fnsScreenShot_Date_format()+"]."+" Getting Exception >> "+t.getMessage());
			throw new Exception("'Launch Browser' is getting fail, Please refer screen shot [LaunchBrowserFail"+fnsScreenShot_Date_format()+"]."+" Getting Exception >> "+t.getMessage());}

		return present;
}




	
	
	
	
	// Function to Launch browser and login
	public boolean fnsTraQtionPortal_LaunchBrowserAndLogin(String Portal_Namne, String userName, String password) throws Throwable {
		boolean present;
		startExecutionTime = fnpTimestamp();
		ScreenShotFlagWithOR_TraQtion = true;
		PortalNamne = Portal_Namne.trim();
		try {
			
			TestSuiteBase_MonitorPlan.fns_Launch_Browser_Only();

			String siteUrl = null;
			
			
			if( (PortalNamne.toLowerCase().trim()).equalsIgnoreCase("TraQtion portal")  ){
			
				if (excelSiteURL!=null) {
					if (excelSiteURL.equalsIgnoreCase("")) {
						siteUrl = CONFIG.getProperty("TraQtionPortalTestsiteName");
					} else {
						
						String SplitExcelUrlValue = excelSiteURL.split("TraQtionCostcoTestsiteName:")[0];
						
						String TraQtionPortalUrl = SplitExcelUrlValue.split("TraQtionPortalTestsiteName:")[1].trim();
						System.out.println(PortalNamne+" = "+TraQtionPortalUrl);			
						siteUrl=TraQtionPortalUrl;
						
					}
				} else {
					siteUrl = CONFIG.getProperty("TraQtionPortalTestsiteName");
				}
			}	
			
			if( (PortalNamne.toLowerCase().trim()).equalsIgnoreCase("TraQtion costco") || (PortalNamne.toLowerCase().trim()).contains("register")){
				
				if (excelSiteURL!=null) {
					if (excelSiteURL.equalsIgnoreCase("")) {
						siteUrl = CONFIG.getProperty("TraQtionCostcoTestsiteName");
					} else {
						
						String TraQtionPortalUrl = excelSiteURL.split("TraQtionCostcoTestsiteName:")[1].trim();
						System.out.println(PortalNamne+" = "+TraQtionPortalUrl);		
						siteUrl=TraQtionPortalUrl;
						
					}
				} else {
					siteUrl = CONFIG.getProperty("TraQtionCostcoTestsiteName");
				}
			}	
			if((PortalNamne.toLowerCase().trim()).equalsIgnoreCase("vendor registration") ){
				
				if (excelSiteURL!=null) {
					if (excelSiteURL.equalsIgnoreCase("")) {
						siteUrl = CONFIG.getProperty("TraQtionVendorRegistration:");
					} else {
						
						String SplitExcelUrlValue = excelSiteURL.split("TraQtionPortalTestsiteName:")[0];
						
						String TraQtionPortalUrl = SplitExcelUrlValue.split("TraQtionVendorRegistration:")[1].trim();
						System.out.println(PortalNamne+" = "+TraQtionPortalUrl);			
						siteUrl=TraQtionPortalUrl;
						
					}
				} else {
					siteUrl = CONFIG.getProperty("TraQtionVendorRegistration:");
				}
			}
			
			driver.manage().deleteAllCookies();
			fnsApps_Report_Logs("Application credentials are URL[ "+siteUrl+" ], UserName[ "+userName+" ]");
			driver.get(siteUrl);
			//  
			TraQtionPortal_Window = driver.getWindowHandle();
			
			if( (PortalNamne.toLowerCase().trim()).equalsIgnoreCase("vendor registration") ){
				
				TestSuiteBase_MonitorPlan.fnsComman_BrowserLogin_ElementWait(OR_TraQtion.getProperty("Vendor_FullName"), CONFIG.getProperty("Element_MAX_WaitTime"));
				
				fnsGet_OR_TraQtion_ObjectX("Vendor_FullName").clear();
				Thread.sleep(250);
				fnsGet_OR_TraQtion_ObjectX("Vendor_FullName").sendKeys("");
				Thread.sleep(250);
				fnsGet_OR_TraQtion_ObjectX("Vendor_FullName").sendKeys(Login_As+" AutomationUser");
				Thread.sleep(250);
				
				
				fnsGet_OR_TraQtion_ObjectX("Vendor_EmailAddress").clear();
				Thread.sleep(250);
				fnsGet_OR_TraQtion_ObjectX("Vendor_EmailAddress").sendKeys("");
				Thread.sleep(250);
				fnsGet_OR_TraQtion_ObjectX("Vendor_EmailAddress").sendKeys("Automation@"+Login_As+".com");
				Thread.sleep(250);
								
				fnsGet_OR_TraQtion_ObjectX("Vendor_StartRegistration_bttn").click();
				Thread.sleep(2000);
				
				
				
				for(int Loginwait=0; Loginwait<NewNsfOnline_Element_Max_Wait_Time; Loginwait++){
					if(driver.findElements(By.xpath(OR_TraQtion.getProperty("Vendor_Registration_Agree_bttn"))).size()>0){
						TestSuiteBase_MonitorPlan.fnsComman_BrowserLogin_ElementWait(OR_TraQtion.getProperty("Vendor_Registration_Agree_bttn"), CONFIG.getProperty("Element_MAX_WaitTime"));
						break;
					}else if(driver.findElements(By.xpath(OR_TraQtion.getProperty("Vendor_Message"))).size()>0){
						throw new Exception("FAILED == '"+PortalNamne+"' Login fail, Getting error <"+fnsGet_Field_TEXT("Vendor_Message").trim()+">");
					}else{
						Thread.sleep(1000);
					}
					if(Loginwait==(NewNsfOnline_Element_Max_Wait_Time)-1){
						throw new Exception("FAILED == '"+PortalNamne+"' Login fail, Getting TimeOut after <"+NewNsfOnline_Element_Max_Wait_Time+"> seconds wait.");
					}
				}
			
				
			}else if( (PortalNamne.toLowerCase().trim()).contains("register") ){
			
				TestSuiteBase_MonitorPlan.fnsComman_BrowserLogin_ElementWait(OR_TraQtion.getProperty("TraQtionOnline_RegisterNewSupplierOrFacility_LinkBttn"), CONFIG.getProperty("Element_MAX_WaitTime"));
				fnsGet_OR_TraQtion_ObjectX("TraQtionOnline_RegisterNewSupplierOrFacility_LinkBttn").click();
				
				//SWitch to TraQtion Costco Registration Window
				try{
					Thread.sleep(5000);
					for(int a=0; a<=NewNsfOnline_Element_Max_Wait_Time; a++){ 
						ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
							
						TraQtion_tabsCount = tabs.size();
						
						if(TraQtion_tabsCount==2){
							for (int i = 0; i < TraQtion_tabsCount; i++) {
								if (TraQtionPortal_Window.equalsIgnoreCase(tabs.get(i))) {
										//Nothing to Do
								} else {
										driver.switchTo().window(tabs.get(i));
										TraQtionCostcoRegistration = driver.getWindowHandle();
										fnsApps_Report_Logs("PASSED == Successfully Switch to TraQtion Costco Registration Window.");
										break;
									}
							}
							break;
						}else{
							Thread.sleep(1000);
						}
						
						if(a==NewNsfOnline_Element_Max_Wait_Time){
							throw new Exception("TraQtion Costco Registration window is not getting open after <"+NewNsfOnline_Element_Max_Wait_Time+"Seconds> wait.");
						}
					}
					
				//	  
						
				}catch(Throwable t){
					fnsTake_Screen_Shot("SwitchTraQtionCostcoRegistrationFail");
					throw new Exception("FAILED == TraQtion Costco Registration Window is not getting Open, plz refer screenshot [SwitchTraQtionCostcoRegistrationFail"+ fnsScreenShot_Date_format() + "].  Getting Exception >> "+t.getMessage());	}
				
				
				TestSuiteBase_MonitorPlan.fnsComman_BrowserLogin_ElementWait(OR_TraQtion.getProperty("TraQtionRegistration_Login_FullName"), CONFIG.getProperty("Element_MAX_WaitTime"));
				
				fnsGet_OR_TraQtion_ObjectX("TraQtionRegistration_Login_FullName").clear();
				Thread.sleep(250);
				fnsGet_OR_TraQtion_ObjectX("TraQtionRegistration_Login_FullName").sendKeys("");
				Thread.sleep(250);
				fnsGet_OR_TraQtion_ObjectX("TraQtionRegistration_Login_FullName").sendKeys(userName);
				Thread.sleep(250);
				fnsGet_OR_TraQtion_ObjectX("TraQtionRegistration_Login_EmailAddress").sendKeys("");
				Thread.sleep(250);
				fnsGet_OR_TraQtion_ObjectX("TraQtionRegistration_Login_EmailAddress").sendKeys(password);
				Thread.sleep(250);
				
				if( (PortalNamne.toLowerCase().trim()).equalsIgnoreCase("Register_New_Facility_for_Existing_Supplier".toLowerCase()) ){
					fnsGet_OR_TraQtion_ObjectX("TraQtionRegistration_Login_RegisterNewFacilityForExistingSupplier_Radio_Bttn").click();
					
					fnsGet_Element_Enabled("TraQtionRegistration_Login_ExistingSupplierName_Input");
					fnsGet_OR_TraQtion_ObjectX("TraQtionRegistration_Login_ExistingSupplierName_Input").sendKeys(TraQtionRegistration_Registration_Step1_CorporateInfo_CompanyName);
					Thread.sleep(250);
					
					String TraQtionRegistration_Login_ExistingSupplierName_DD_Value_Xpath = "//li[contains(text(), '"+TraQtionRegistration_Registration_Step1_CorporateInfo_CompanyName+"')]";
					
					
					TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(TraQtionRegistration_Login_ExistingSupplierName_DD_Value_Xpath);
					Thread.sleep(2000);
					TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(TraQtionRegistration_Login_ExistingSupplierName_DD_Value_Xpath).click();
					Thread.sleep(500);
				}
				
				fnsGet_Element_Enabled("TraQtionRegistration_Login_StartRegistration_Bttn");
				fnsGet_OR_TraQtion_ObjectX("TraQtionRegistration_Login_StartRegistration_Bttn").click();
				Thread.sleep(2000);
				
				
				for(int Loginwait=0; Loginwait<NewNsfOnline_Element_Max_Wait_Time; Loginwait++){
					if(driver.findElements(By.xpath(OR_TraQtion.getProperty("TraQtionRegistration_Registration_Agree_Bttn"))).size()>0){
						TestSuiteBase_MonitorPlan.fnsComman_BrowserLogin_ElementWait(OR_TraQtion.getProperty("TraQtionRegistration_Registration_Agree_Bttn"), CONFIG.getProperty("Element_MAX_WaitTime"));
						break;
					}else{
						Thread.sleep(1000);
					}
					if(driver.findElements(By.xpath(OR_TraQtion.getProperty("TraQtion_Questioner_Validation_Msg"))).size()>0){
						throw new Exception("FAILED == '"+PortalNamne+"' Login fail, Getting error <"+fnsGet_Field_TEXT("TraQtion_Questioner_Validation_Msg").trim()+">");
					}
					if(Loginwait==(Long.parseLong(CONFIG.getProperty("Element_MAX_WaitTime")))-1){
						throw new Exception("FAILED == '"+PortalNamne+"' Login fail, Getting TimeOut after <"+NewNsfOnline_Element_Max_Wait_Time+"> seconds wait.");
					}
				}
				
			}else{
				
				driver.switchTo().frame("loginframe");
				TestSuiteBase_MonitorPlan.fnsComman_BrowserLogin_ElementWait(OR_TraQtion.getProperty("TraQtionPortal_UserName"), CONFIG.getProperty("Element_MAX_WaitTime"));
				
				
				fnsGet_OR_TraQtion_ObjectX("TraQtionPortal_LoginAs").clear();
				Thread.sleep(250);
				fnsGet_OR_TraQtion_ObjectX("TraQtionPortal_LoginAs").sendKeys("");
				Thread.sleep(250);
				fnsGet_OR_TraQtion_ObjectX("TraQtionPortal_LoginAs").sendKeys(Login_As);
				Thread.sleep(250);
				
				
				fnsGet_OR_TraQtion_ObjectX("TraQtionPortal_UserName").clear();
				Thread.sleep(250);
				fnsGet_OR_TraQtion_ObjectX("TraQtionPortal_UserName").sendKeys("");
				Thread.sleep(250);
				fnsGet_OR_TraQtion_ObjectX("TraQtionPortal_UserName").sendKeys(userName);
				Thread.sleep(250);
				fnsGet_OR_TraQtion_ObjectX("TraQtionPortal_password").sendKeys("");
				Thread.sleep(250);
				fnsGet_OR_TraQtion_ObjectX("TraQtionPortal_password").sendKeys(password);
				fnsGet_OR_TraQtion_ObjectX("TraQtionPortal_Login").click();
				Thread.sleep(2000);
				
				
				
				for(int Loginwait=0; Loginwait<NewNsfOnline_Element_Max_Wait_Time; Loginwait++){
					driver.switchTo().window(TraQtionPortal_Window);
					if(driver.findElements(By.xpath(OR_TraQtion.getProperty("TraQtionPortal_logOutBtn"))).size()>0){
						TestSuiteBase_MonitorPlan.fnsComman_BrowserLogin_ElementWait(OR_TraQtion.getProperty("TraQtionPortal_logOutBtn"), CONFIG.getProperty("Element_MAX_WaitTime"));
						break;
					}else{
						Thread.sleep(1000);
					}
					if(driver.findElements(By.xpath(OR_TraQtion.getProperty("TraQtionPortal_IFrameXpath"))).size()>0){
						driver.switchTo().frame("loginframe");
						if(driver.findElements(By.xpath(OR_TraQtion.getProperty("TraQtionPortal_LoginErroMsg"))).size()>0){
							throw new Exception("FAILED == '"+PortalNamne+"' Login fail, Getting error <"+fnsGet_Field_TEXT("TraQtionPortal_LoginErroMsg").trim()+">");
						}
					}
					
					if(Loginwait==(NewNsfOnline_Element_Max_Wait_Time)-1){
						throw new Exception("FAILED == '"+PortalNamne+"' Login fail, Getting TimeOut after <"+NewNsfOnline_Element_Max_Wait_Time+"> seconds wait.");
					}
				}
			}
			
			fnsApps_Report_Logs("Browser is launched and Successfully login into the 'iPulse' Application");		
			present = true;
			
		} catch(NoSuchWindowException W){
			isTestCasePass = false;
			throw new Exception(W.getMessage());			}
		catch (Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot("LaunchBrowserAndLoginFail");
			present = false;
			fnsApps_Report_Logs("");
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t)+", Please refer screen shot [LaunchBrowserAndLoginFail"+fnsScreenShot_Date_format());
			throw new Exception(Throwables.getStackTraceAsString(t)+", Please refer screen shot [LaunchBrowserAndLoginFail"+fnsScreenShot_Date_format());		}
	
		return present;
}	
	
	
	
	
//Clicking on Search Customer Ajax link.
	public void fnsTraQtion_SurveyLogin(String UserNamneString) throws Exception {
		boolean SuccessLoginFlag = false;
		try{
			TestSuiteBase_MonitorPlan.fnsComman_BrowserLogin_ElementWait(OR_TraQtion.getProperty("TraQtion_UserName"), CONFIG.getProperty("Element_MAX_WaitTime"));
			fnsWait_and_Type("TraQtion_UserName", UserNamneString);
			
			fnsWait_and_Click("TraQtion_Login_Bttn");
			//Thread.sleep(2000);
			
			for(int pageredirectloop=0; pageredirectloop<Integer.parseInt(CONFIG.getProperty("Element_MAX_WaitTime")); pageredirectloop++){
				if(driver.findElements(By.xpath(OR_TraQtion.getProperty("TraQtion_Questioner_Validation_Msg"))).size()>0){
					TestSuiteBase_MonitorPlan.WithOut_OR_fnClear(OR_TraQtion.getProperty("TraQtion_UserName"));
					fnsWait_and_Type("TraQtion_UserName", UserNamneString);
					fnsWait_and_Click("TraQtion_Login_Bttn");
					Thread.sleep(5000);
					SuccessLoginFlag = true;
				}else if (driver.findElements(By.xpath(OR_TraQtion.getProperty("TraQtion_SupplierList_Text"))).size()>0){
					fnsGet_Element_Enabled("TraQtion_SupplierList_Text");
					fnsApps_Report_Logs("Successfully Login into TraQtion Survey for UserName("+UserNamneString+").");
					break;
				}else{
					Thread.sleep(1000);
				}
				if((driver.findElements(By.xpath(OR_TraQtion.getProperty("TraQtion_Questioner_Validation_Msg"))).size()>0) && SuccessLoginFlag){
					throw new Exception(fnsGet_Field_TEXT("TraQtion_Questioner_Validation_Msg"));
				}
			}
					
		}catch(NoSuchWindowException W){
			isTestCasePass = false;
			throw new Exception( W.getMessage());		}
		catch(Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot("TraQtionLoginFail");
		//	driver.quit();
			fnsApps_Report_Logs("");
			fnsApps_Report_Logs("FAILED == TraQtion Survey Login Fail for UserName("+UserNamneString+"), plz see screenshot [TraQtionLoginFail" + fnsScreenShot_Date_format() + "]"+". Getting Exception  >> "+t.getMessage().trim());
			throw new Exception("FAILED == TraQtion Survey Login Fail for UserName("+UserNamneString+"), plz see screenshot [TraQtionLoginFail" + fnsScreenShot_Date_format() + "]"+". Getting Exception  >> "+t.getMessage().trim());		}
	}	
	
	
	

//Check for Browser Type defined in Suits Excel 
public void BrowserCheck(){
		String Browser=null;
		
		for(int i=2; i <= suiteXls.getRowCount("Test Suite") ;i++ ){
			
			if(suiteXls.getCellData("Test Suite", "TSID", i).equalsIgnoreCase("TraQtion_suite")){
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
	
	

//check if the suite has to be skip
	@BeforeSuite
	public void checkSuiteSkip() throws Throwable{
		fns_CheckSiteSkip("TraQtion_suite");
	}
	
	
	
	//Check class(Y/N) and Launch browser and Login 
	public void fnsCheckClassLevelTestSkip(String className) throws Exception {		
		try {
			classNameText=className;
			currentScriptCode=fnpMappingClassNameWithScenarioCode(classNameText).trim();
			
			if (!TestUtil.isTestCaseRunnable(TraQtion_suitexls, className)) {
				fnsApps_Report_Logs("Skipping Test Case " + className + " as runmode set to NO");
				throw new SkipException("Skipping Test Case " + className + " as runmode set to NO");
			}
			isTestCasePass = true;
			runmodes = TestUtil.getDataSetRunmodes(TraQtion_suitexls, className);	
			Login_UserName = fns_Return_LoginUserPassword_From_TestCaseSheet(TraQtion_suitexls, className);
			if(Login_UserName==null){
				throw new Exception ("FAILED == For Test Case <" + className + ">, Unable to fetch UserName and Password from Excel.");
			}else{
				Login_As = Login_UserName.split(":")[0].trim();
				Login_Password = Login_UserName.split(":")[2].trim();
				Login_UserName = Login_UserName.split(":")[1].trim();
			}
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
			throw new SkipException("Skipping Test Case " + " as runmode set to NO");}
		}catch(SkipException sk){
			System.out.println("SkipException TCSkipReturnValue ="+TCSkipReturnValue+" count"+counts);}
		
		
	}
	
	
	
	
//Always run after suite
	@AfterSuite(alwaysRun=true)
	public void Finishing_TraQtion_Suite_Script() throws Throwable {
		
		ScreenShotFlagWithOR_TraQtion = false;
		
		fnsApps_Report_Logs("");
		fnsApps_Report_Logs("########################################################### TraQtion Suite END ########################################################## ");
		fnsApps_Report_Logs("=========================================================================================================================================");
		fnsApps_Report_Logs("");
}	
	
	
	//
	public void MoveMouseCursorToTaskBar(){
		
		Actions testact=new Actions(driver);
		testact.moveByOffset(-1500, -1500).build().perform();
	}
	
	
	//Get Excel Cell value by column name
	public String fnsReturn_ExcelCellValue_ByMatchingColumnName(String SheetName, String ColumnName) throws Throwable{
		try{
			String CellValue = null;
			for(int i=5; i<50; i++){
				for(int j=1; j<14; j++){
					String ExcelCellValue = TraQtion_suitexls.getCellData(SheetName, j, i);
					if(ExcelCellValue.equalsIgnoreCase(ColumnName)){
						CellValue = TraQtion_suitexls.getCellData(SheetName, j, i+1);
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
			fnsApps_Report_Logs(t.getMessage());
			throw new Exception(t.getMessage());	}
	}
	//Get Excel Cell value by column name
		public String fngReturn_ExcelCellValue_ByMatchingColumnName(String SheetName, String ColumnName) throws Throwable{
			try{
				String CellValue = null;
				for(int i=1; i<50; i++){
					for(int j=1; j<50; j++){
						String ExcelCellValue = TraQtion_suitexls.getCellData(SheetName, j, i);
						if(ExcelCellValue.equalsIgnoreCase(ColumnName)){
							CellValue = TraQtion_suitexls.getCellData(SheetName, j, i+1);
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
				fnsApps_Report_Logs(t.getMessage());
				throw new Exception(t.getMessage());	}
		}
	
	// return the test data from a test in a 2 dim array
	public Object[][] getExcelData_for_DataProvider(Xls_Reader xls , String testCaseName){
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
		
	    Object[][] data =new Object[rows-1][cols-3];
		for(int rowNum=2;rowNum<=rows;rowNum++){
			for(int colNum=0;colNum<cols-3;colNum++){
			//	System.out.print(xls.getCellData(testCaseName, colNum, rowNum) + " -- ");
				data[rowNum-2][colNum] = xls.getCellData(testCaseName, colNum, rowNum);
				
			}
			//System.out.println();
		}
		return data;
		
	}
	
	
	
// Return Login User Name and Password from Test Case Sheet
	public String fns_Return_LoginUserPassword_From_TestCaseSheet(Xls_Reader xls, String testCaseName){
		String LoginUserPassword = null;
		for(int i=2; i<= xls.getRowCount("Test Cases") ; i++){
			if(xls.getCellData("Test Cases", "TCID", i).equalsIgnoreCase(testCaseName)){
				LoginUserPassword = xls.getCellData("Test Cases", "LoginAs_User_Password", i).trim();
			}
		}
		return LoginUserPassword;
	}	
	
	//function to select drop down value ---- Satya Pal
	public void fnsDD_value_Select_By_ContainsText_DownKeyPress(String ddClickXpathKey, String ddListXpathKey, String TagName, String Value) throws Throwable {
		boolean ValueNotMatched=true;
		List<WebElement> objectlist5 = null;
		for(int i=1; i<=(2); i++){
			try{
				for(int k=1; k<=(20); k++){
					fnsGet_Element_Enabled(ddClickXpathKey);
					Actions move = new Actions(driver);
					move.moveToElement( fnsGet_OR_TraQtion_ObjectX(ddClickXpathKey) ).click().build().perform();
					fnsApps_Report_Logs("PASSED == Click done on element >> "+ddClickXpathKey);
					
					objectlist5=fnsGet_OR_TraQtion_ObjectX(ddListXpathKey).findElements(By.tagName(TagName));
					if(objectlist5.size()>1){
						Thread.sleep(2000);
						break;
					}else{
						TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
					}
					if(k==20){
						throw new Exception ("Drop down is not getting load, after 40 seconds wait.");
					}
				}
				fnsApps_Report_Logs("PASSED == Count of No. of values coming in Drop down list is = "+objectlist5.size());
				for(WebElement dd_value:objectlist5){
					Actions act = new Actions(driver);
					act.sendKeys(Keys.ARROW_DOWN).build().perform();
					if(dd_value.getText().contains(Value)){
						Actions act1 = new Actions(driver);
						act1.moveToElement(dd_value).click().build().perform();
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
				if(i==2){
					isTestCasePass = false;
					fnsTake_Screen_Shot("DdValueSelectFail" + ddClickXpathKey);
					fnsApps_Report_Logs("FAILED == Unable to select value from drop down [ " + ddClickXpathKey +" ],plz see screenshot [ DdValueSelectFail" + ddClickXpathKey + fnsScreenShot_Date_format() +" ]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(t).trim());
					throw new Exception("FAILED == Unable to select value from drop down [ " + ddClickXpathKey +" ],plz see screenshot [ DdValueSelectFail" + ddClickXpathKey + fnsScreenShot_Date_format() + " ]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(t).trim());
				}else{
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
				}
			}	
		}
	}
	
	
	
	
	
	
}
