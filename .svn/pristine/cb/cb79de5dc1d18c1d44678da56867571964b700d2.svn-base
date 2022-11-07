package nsf.ecap.New_NSFOnline_Suite;

import static org.testng.Assert.assertTrue;

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
import java.util.TimeZone;

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

public class TestSuiteBase_New_NSFOnline extends TestBase {

	public String classNameText = null;
	public long TC_Step = 0;
	public long Log_Step = 0;
	public ArrayList<String> Loading_Xpath_Array = new ArrayList<String>();
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
	public String BrowserName = "IE";
	public boolean BrowserDriver = false;
	public String TextAfterRemvoalSpeChar;
	public String runmodes[] = null;
	public static boolean ScreenShotFlagWithOR_New_NSFOnline = false;
	public boolean ApplicationVersion_Flag = true;

	public Integer GetColumnNo = null;;
	public Integer After_DataAdded = null;;

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
	public String RunningClassName = "";
	public boolean BS_03_Runmode = false;
	public String Running_Class_BS_Description = null;
	public String User_COCL_Account = null;
	public String NsfConncet_Window_Handle = null;
	public String iPulse_Original_WindowHandle = null;
	public String User_DefaultCustomerAccount_COCL = null;
	public boolean CARespond_KendoEditorDisplayed = false;

	// ######################################################### Common Functions
	// #######################################################################

	// Function to Take Screen Shot.
	public void fnsTake_Screen_Shot(String message) throws Exception {
		String MessageAfterFormat = fnsRemoveFormatting_for_FileName(message);
		try {
			String Suite_Foler_Name = "screenshots_NSF_Connect";
			String File_Name = MessageAfterFormat + "_" + fnsScreenShot_Date_format() + ".PNG";
			FileUtils.forceMkdir(new File((System.getProperty("user.dir") + screenshots_path + "//" + Suite_Foler_Name
					+ "//" + currentScriptCode + "//")));
			File image = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(image, new File((System.getProperty("user.dir") + screenshots_path + "//"
					+ Suite_Foler_Name + "//" + currentScriptCode + "//" + File_Name)));

			/*
			 * Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			 * Rectangle screenRectangle = new Rectangle(screenSize);
			 * Robot robot = new Robot();
			 * BufferedImage image = robot.createScreenCapture(screenRectangle);
			 * ImageIO.write(image, "png", new File((System.getProperty("user.dir") +
			 * screenshots_path+ "//"+Suite_Foler_Name+"//"+currentScriptCode
			 * +"//"+File_Name)));
			 */

			/*
			 * Screenshot image=new
			 * AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).
			 * takeScreenshot(driver);
			 * ImageIO.write(image.getImage(),"PNG",new File((System.getProperty("user.dir")
			 * + screenshots_path+ "//"+Suite_Foler_Name+"//"+currentScriptCode
			 * +"//Copy_"+File_Name)));
			 */
		} catch (java.lang.NullPointerException n) {
			fnsApps_Report_Logs("ScreenShotNullPointerException >> " + n.getMessage());
			throw new Exception("NullPointerException Unable To take Screen Shots.");
		} catch (java.io.IOException e) {
			fnsApps_Report_Logs("ScreenShotIOException >> " + e.getMessage());
			throw new Exception("IOException Unable To take Screen Shots.");
		}
	}

	// Worked only for Chrome Browser
	public void fnWait(long time1) throws Throwable {
		if (BrowserDriver == true) {
			Thread.sleep(time1);
		} else {
			/* Nothing to Do */ }
	}

	// Function used for format Screen shot name
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

	// Alert Loading disappear function
	public void fnsLoading_Progressing_wait_AlertLaoder(int waitcount) throws Throwable {
		boolean Aler_Loader_Coming = false;
		int ActualLoading_Time = 0;
		String Alert_Loader_Xpath = OR_New_NSFOnline.getProperty("Loading_AlertLoading");
		try {
			for (int s = 0; s <= (waitcount * 2); s++) {
				if (driver.findElements(By.xpath(Alert_Loader_Xpath)).size() > 0) {
					Aler_Loader_Coming = true;
					break;
				} else if (s == (waitcount * 2)) {
					break;
				} else {
					ActualLoading_Time++;
					Thread.sleep(500);
				}
			}
			if (Aler_Loader_Coming) {
				for (int wait = 2; wait <= ((NewNsfOnline_Element_Max_Wait_Time * 2)); wait++) {
					if (driver.findElements(By.xpath(Alert_Loader_Xpath)).size() == 0) {
						fnsApps_Report_Logs(
								"Progressing Count Loader is displayed for ( " + ActualLoading_Time + " ) seconds.");
						break;
					} else {
						ActualLoading_Time++;
						Thread.sleep(500);
					}
					if (ActualLoading_Time == (NewNsfOnline_Element_Max_Wait_Time * 2)) {
						throw new Exception("Loading Issue : After  ( " + (NewNsfOnline_Element_Max_Wait_Time)
								+ " ) Seconds wait Count Loader is not getting disappear, please refer screenshot >> PageLoadFail"
								+ fnsScreenShot_Date_format() + "*****  < " + Loading_Image_Xpath + " >");
					}
				}
			} else {
				fnsApps_Report_Logs("Wait/Pause for ( " + waitcount + " ) seconds. -- count Laoder");
			}
		} catch (StaleElementReferenceException n) {
			fnsApps_Report_Logs(
					"Progressing Count Loader is displayed for ( " + ActualLoading_Time + " ) seconds. - stale");
		} catch (NoSuchWindowException W) {
			throw new Exception(W.getMessage());
		} catch (Throwable tt) {
			TestSuiteBase_MonitorPlan.fnsTake_Screen_Shot_Without_OR("PageLoadFail");
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(tt));
			throw new Exception(Throwables.getStackTraceAsString(tt));
		}
	}

	/*
	 * //Function of loading image appear on the screen (Block UI).
	 * public void fnsLoading_Progressing_wait(int waitcount) throws Throwable{
	 * try{
	 * LoadingImageFlag = false;
	 * Actual_Loading_Time = 1;
	 * String Loading_Classes_From_OR =
	 * OR_New_NSFOnline.getProperty("Loading_Progressing").trim();
	 * 
	 * for(int wait=2; wait<=((NewNsfOnline_Element_Max_Wait_Time)); wait++){
	 * if(!LoadingImageFlag){
	 * Thread.sleep(500);
	 * for(int i=0; i<Loading_Classes_From_OR.split("&&").length; i++){
	 * Loading_Image_Xpath = Loading_Classes_From_OR.split("&&")[i].trim();
	 * try{
	 * if(driver.findElements(By.xpath(Loading_Image_Xpath)).size()>1){
	 * //
	 * fnsApps_Report_Logs("Xpath 1 = "+Loading_Image_Xpath+"..... Size = "+driver.
	 * findElements(By.xpath(Loading_Image_Xpath)).size());
	 * for(int display=1;
	 * display<=driver.findElements(By.xpath(Loading_Image_Xpath)).size();
	 * display++){
	 * String Loading_Image_Xpath_Display =
	 * "("+Loading_Image_Xpath+")["+display+"]";
	 * if(driver.findElement(By.xpath(Loading_Image_Xpath_Display)).isDisplayed()){
	 * Loading_Image_Xpath = Loading_Image_Xpath_Display;
	 * break;
	 * }
	 * }
	 * }else if(driver.findElements(By.xpath(Loading_Image_Xpath)).size()>0){
	 * //
	 * fnsApps_Report_Logs("Xpath 0 = "+Loading_Image_Xpath+"..... Size = "+driver.
	 * findElements(By.xpath(Loading_Image_Xpath)).size());
	 * if(driver.findElement(By.xpath(Loading_Image_Xpath)).isDisplayed()){
	 * break;
	 * }
	 * }
	 * }catch(Throwable t){ /nothing to do }
	 * }
	 * }
	 * 
	 * 
	 * if(Loading_Image_Xpath.contains("html/body/div[1]/div[1]")){
	 * PageSourceText = driver.getPageSource().toLowerCase();
	 * if(PageSourceText.contains("modal-content")){
	 * if (fnsGet_OR_New_NSFOnline_ObjectX("NewNsfOnline_Content_Model_Popup").
	 * isDisplayed()) {
	 * WebElement Element =
	 * fnsGet_OR_New_NSFOnline_ObjectX("NewNsfOnline_Content_Model_Popup");
	 * Actions act = new Actions(driver);
	 * act.moveToElement(Element).build().perform();
	 * ErrorMsgText =
	 * fnsGet_OR_New_NSFOnline_ObjectX("NewNsfOnline_Content_Model_Popup").getText()
	 * .trim();
	 * throw new IllegalArgumentException();
	 * }
	 * }
	 * }
	 * 
	 * 
	 * try{
	 * if(driver.findElement(By.xpath(Loading_Image_Xpath)).isDisplayed()){
	 * Thread.sleep(1000);
	 * LoadingImageFlag = true;
	 * }
	 * }catch( Throwable n){ Thread.sleep(250); }
	 * 
	 * 
	 * 
	 * try{
	 * if( !(driver.findElement(By.xpath(Loading_Image_Xpath)).isDisplayed()) &&
	 * wait > waitcount && LoadingImageFlag == true ){
	 * Loading_Xpath_Array.add(Loading_Image_Xpath+"&&");
	 * fnsApps_Report_Logs("Progressing Loading image is displayed for < "+(
	 * Actual_Loading_Time)+" > seconds.");
	 * break;
	 * }else if( !(driver.findElement(By.xpath(Loading_Image_Xpath)).isDisplayed())
	 * && wait > waitcount && LoadingImageFlag == false ){
	 * fnsApps_Report_Logs("*********  Pause/Wait is for < "
	 * +Actual_Loading_Time+" > seconds.  *****  "); //To increase performance,
	 * Pause/Wait time can set to 1 sec
	 * break;
	 * }else if( wait < waitcount && LoadingImageFlag == false ){
	 * Thread.sleep(500);
	 * }
	 * }catch(Throwable n){
	 * if( wait > waitcount ){
	 * if( LoadingImageFlag == true ){
	 * Loading_Xpath_Array.add(Loading_Image_Xpath+"&&");
	 * fnsApps_Report_Logs("Progressing Loading image is displayed for < "+(
	 * Actual_Loading_Time)+" > seconds.");
	 * break;
	 * }else if( LoadingImageFlag == false ){
	 * fnsApps_Report_Logs("*********  Pause/Wait is for < "
	 * +Actual_Loading_Time+" > seconds.  *****  "); //To increase performance,
	 * Pause/Wait time can set to 1 sec
	 * break;
	 * }
	 * }else if( wait < waitcount && LoadingImageFlag == false ){
	 * Thread.sleep(500);
	 * }
	 * }
	 * 
	 * if(wait==(NewNsfOnline_Element_Max_Wait_Time)){
	 * throw new
	 * Exception("Loading Issue : After < "+(NewNsfOnline_Element_Max_Wait_Time)
	 * +" > Seconds wait page is not getting Load, please refer screenshot >> PageLoadFail"
	 * + fnsScreenShot_Date_format() +"*****  < "+Loading_Image_Xpath+" >");
	 * }
	 * Actual_Loading_Time++;
	 * }
	 * 
	 * PageSourceText = driver.getPageSource().toLowerCase();
	 * 
	 * if(PageSourceText.contains("modal-content")){
	 * if (fnsGet_OR_New_NSFOnline_ObjectX("NewNsfOnline_Content_Model_Popup").
	 * isDisplayed()) {
	 * WebElement Element =
	 * fnsGet_OR_New_NSFOnline_ObjectX("NewNsfOnline_Content_Model_Popup");
	 * Actions act = new Actions(driver);
	 * act.moveToElement(Element).build().perform();
	 * ErrorMsgText =
	 * fnsGet_OR_New_NSFOnline_ObjectX("NewNsfOnline_Content_Model_Popup").getText()
	 * .trim();
	 * throw new IllegalArgumentException();
	 * }
	 * }
	 * 
	 * 
	 * }catch(IllegalArgumentException i){
	 * //isTestCasePass = false;
	 * TestSuiteBase_MonitorPlan.fnsTake_Screen_Shot_Without_OR("UnExpectedError");
	 * fnsApps_Report_Logs("Getting Un-Expected Application Error <"
	 * +ErrorMsgText+">, please refer screenshot >>UnExpectedError" +
	 * fnsScreenShot_Date_format()+"*****  < "+Loading_Image_Xpath+" >"
	 * +"  Exception ("+Throwables.getStackTraceAsString(i));
	 * throw new Exception("Getting Un-Expected Application Error <"
	 * +ErrorMsgText+">, please refer screenshot >> UnExpectedError" +
	 * fnsScreenShot_Date_format()+"*****  < "+Loading_Image_Xpath+" >"
	 * +"  Exception ("+Throwables.getStackTraceAsString(i));
	 * }catch(NoSuchElementException n){
	 * fnsApps_Report_Logs("***** Progressing Loading image displayed for < "
	 * +Actual_Loading_Time+" > seconds. **************** NS");
	 * try{
	 * fnsApps_Report_Logs("***** Progressing Loading image displayed for < "
	 * +Actual_Loading_Time+" > seconds. **************** NS");
	 * fnsLoading_Progressing_wait(waitcount);
	 * }catch(Throwable tt){
	 * isTestCasePass = false;
	 * TestSuiteBase_MonitorPlan.fnsTake_Screen_Shot_Without_OR("PageLoadFail_NS");
	 * fnsApps_Report_Logs(Throwables.getStackTraceAsString(tt));
	 * throw new Exception(Throwables.getStackTraceAsString(tt));
	 * }
	 * }catch(StaleElementReferenceException n){
	 * fnsApps_Report_Logs("***** Progressing Loading image displayed for < "
	 * +Actual_Loading_Time+" > seconds. **************** Stale");
	 * } catch(NoSuchWindowException W){
	 * throw new Exception(W.getMessage());
	 * }catch(Throwable tt){
	 * //isTestCasePass = false;
	 * TestSuiteBase_MonitorPlan.fnsTake_Screen_Shot_Without_OR("PageLoadFail");
	 * fnsApps_Report_Logs(Throwables.getStackTraceAsString(tt));
	 * throw new Exception(Throwables.getStackTraceAsString(tt));
	 * }
	 * }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * //Function of loading image appear on the screen (Block UI).
	 * public void fnsLoading_Progressing_on_Popup_wait_for_Popup(int waitcount)
	 * throws Throwable{
	 * try{
	 * 
	 * LoadingImageFlag = false;
	 * Actual_Loading_Time = 1;
	 * String Loading_Classes_From_OR =
	 * OR_New_NSFOnline.getProperty("Loading_Progressing_on_Popup").trim();
	 * 
	 * for(int wait=2; wait<=((NewNsfOnline_Element_Max_Wait_Time)); wait++){
	 * if(!LoadingImageFlag){
	 * Thread.sleep(500);
	 * for(int i=0; i<Loading_Classes_From_OR.split("&&").length; i++){
	 * Loading_Image_Xpath = Loading_Classes_From_OR.split("&&")[i].trim();
	 * try{
	 * if(driver.findElements(By.xpath(Loading_Image_Xpath)).size()>1){
	 * //fnsApps_Report_Logs("Xpath 1 = "+Loading_Image_Xpath+"..... Size = "+driver
	 * .findElements(By.xpath(Loading_Image_Xpath)).size());
	 * for(int display=1;
	 * display<=driver.findElements(By.xpath(Loading_Image_Xpath)).size();
	 * display++){
	 * String Loading_Image_Xpath_Display =
	 * "("+Loading_Image_Xpath+")["+display+"]";
	 * if(driver.findElement(By.xpath(Loading_Image_Xpath_Display)).isDisplayed()){
	 * Loading_Image_Xpath = Loading_Image_Xpath_Display;
	 * break;
	 * }
	 * }
	 * }else if(driver.findElements(By.xpath(Loading_Image_Xpath)).size()>0){
	 * //
	 * fnsApps_Report_Logs("Xpath 0 = "+Loading_Image_Xpath+"..... Size = "+driver.
	 * findElements(By.xpath(Loading_Image_Xpath)).size());
	 * if(driver.findElement(By.xpath(Loading_Image_Xpath)).isDisplayed()){
	 * break;
	 * }
	 * }
	 * }catch(Throwable t){ /nothing to do }
	 * }
	 * }
	 * 
	 * 
	 * try{
	 * if(driver.findElement(By.xpath(Loading_Image_Xpath)).isDisplayed()){
	 * Thread.sleep(1000);
	 * LoadingImageFlag = true;
	 * }
	 * }catch(Throwable n){ Thread.sleep(250); }
	 * 
	 * 
	 * 
	 * try{
	 * if( !(driver.findElement(By.xpath(Loading_Image_Xpath)).isDisplayed()) &&
	 * wait > waitcount && LoadingImageFlag == true ){
	 * Loading_Xpath_Array.add(Loading_Image_Xpath+"&&");
	 * fnsApps_Report_Logs("Progressing Loading image is displayed for < "+(
	 * Actual_Loading_Time)+" > seconds.");
	 * break;
	 * }else if( !(driver.findElement(By.xpath(Loading_Image_Xpath)).isDisplayed())
	 * && wait > waitcount && LoadingImageFlag == false ){
	 * fnsApps_Report_Logs("******  Pause/Wait is for < "
	 * +Actual_Loading_Time+" > seconds.  *****  "); //To increase performance,
	 * Pause/Wait time can set to 1 sec
	 * break;
	 * }else if( wait < waitcount && LoadingImageFlag == false ){
	 * Thread.sleep(500);
	 * }
	 * }catch(Throwable n){
	 * if( wait > waitcount ){
	 * if( LoadingImageFlag == true ){
	 * Loading_Xpath_Array.add(Loading_Image_Xpath+"&&");
	 * fnsApps_Report_Logs("Progressing Loading image is displayed for < "+(
	 * Actual_Loading_Time)+" > seconds.");
	 * break;
	 * }else if( LoadingImageFlag == false ){
	 * fnsApps_Report_Logs("*********  Pause/Wait is for < "
	 * +Actual_Loading_Time+" > seconds.  *****  "); //To increase performance,
	 * Pause/Wait time can set to 1 sec
	 * break;
	 * }
	 * }else if( wait < waitcount && LoadingImageFlag == false ){
	 * Thread.sleep(500);
	 * }
	 * }
	 * 
	 * if(wait==(NewNsfOnline_Element_Max_Wait_Time)){
	 * throw new
	 * Exception("Loading Issue : After < "+(NewNsfOnline_Element_Max_Wait_Time)
	 * +" > Seconds wait page is not getting Load, please refer screenshot >> PageLoadFail"
	 * + fnsScreenShot_Date_format() +"*****  < "+Loading_Image_Xpath+" >");
	 * }
	 * Actual_Loading_Time++;
	 * }
	 * 
	 * 
	 * }catch(IllegalArgumentException i){
	 * //isTestCasePass = false;
	 * TestSuiteBase_MonitorPlan.fnsTake_Screen_Shot_Without_OR("UnExpectedError");
	 * fnsApps_Report_Logs("Getting Un-Expected Application Error <"
	 * +ErrorMsgText+">, please refer screenshot >>UnExpectedError" +
	 * fnsScreenShot_Date_format()+"*****  < "+Loading_Image_Xpath+" >"
	 * +"  Exception ("+Throwables.getStackTraceAsString(i));
	 * throw new Exception("Getting Un-Expected Application Error <"
	 * +ErrorMsgText+">, please refer screenshot >> UnExpectedError" +
	 * fnsScreenShot_Date_format()+"*****  < "+Loading_Image_Xpath+" >"
	 * +"  Exception ("+Throwables.getStackTraceAsString(i));
	 * }catch(NoSuchElementException n){
	 * fnsApps_Report_Logs("***** Progressing Loading image displayed for < "
	 * +Actual_Loading_Time+" > seconds. **************** NS");
	 * try{
	 * fnsApps_Report_Logs("***** Progressing Loading image displayed for < "
	 * +Actual_Loading_Time+" > seconds. **************** NS");
	 * fnsLoading_Progressing_on_Popup_wait_for_Popup(waitcount);
	 * }catch(Throwable tt){
	 * isTestCasePass = false;
	 * TestSuiteBase_MonitorPlan.fnsTake_Screen_Shot_Without_OR("PageLoadFail");
	 * fnsApps_Report_Logs(Throwables.getStackTraceAsString(tt));
	 * throw new Exception(Throwables.getStackTraceAsString(tt));
	 * }
	 * }catch(StaleElementReferenceException n){
	 * fnsApps_Report_Logs("***** Progressing Loading image displayed for < "
	 * +Actual_Loading_Time+" > seconds. **************** Stale");
	 * } catch(NoSuchWindowException W){
	 * throw new Exception(W.getMessage());
	 * }catch(Throwable tt){
	 * //isTestCasePass = false;
	 * TestSuiteBase_MonitorPlan.fnsTake_Screen_Shot_Without_OR("PageLoadFail");
	 * fnsApps_Report_Logs(Throwables.getStackTraceAsString(tt));
	 * throw new Exception(Throwables.getStackTraceAsString(tt));
	 * }
	 * }
	 */

	// Function of loading image appear on the screen (Block UI).
	public void fnsLoading_Progressing_wait(int waitcount) throws Throwable {
		try {
			LoadingImageFlag = false;
			Actual_Loading_Time = 1;
			String Loading_Classes_From_OR = OR_New_NSFOnline.getProperty("Loading_Progressing").trim();
			Integer PageLoadWait = TestSuiteBase_MonitorPlan.fnsLoading_PageLoad();
			for (int wait = PageLoadWait; wait <= ((NewNsfOnline_Element_Max_Wait_Time)); wait++) {
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

				if (Loading_Image_Xpath.contains("html/body/div[1]/div[1]")) {
					boolean ContentPopupDisplay = false;
					PageSourceText = driver.getPageSource().toLowerCase();
					if (PageSourceText.contains("modal-content")) {
						try {
							if (driver
									.findElement(
											By.xpath(OR_New_NSFOnline.getProperty("NewNsfOnline_Content_Model_Popup")))
									.isDisplayed()) {
								ContentPopupDisplay = true;
							}
						} catch (Throwable n) {
							System.out.println("TOP - - modal-content - error - Page source");
						}

						if (ContentPopupDisplay) {
							WebElement Element = fnsGet_OR_New_NSFOnline_ObjectX("NewNsfOnline_Content_Model_Popup");
							Actions act = new Actions(driver);
							act.moveToElement(Element).build().perform();
							ErrorMsgText = fnsGet_OR_New_NSFOnline_ObjectX("NewNsfOnline_Content_Model_Popup").getText()
									.trim();
							throw new IllegalArgumentException();
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
						fnsApps_Report_Logs("Progressing Loading image is displayed for < " + (Actual_Loading_Time)
								+ " > seconds.");
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
						Thread.sleep(500);
					}
				} catch (Throwable n) {
					if (wait > waitcount) {
						if (LoadingImageFlag == true) {
							Loading_Xpath_Array.add(Loading_Image_Xpath + "&&");
							fnsApps_Report_Logs("Progressing Loading image is displayed for < " + (Actual_Loading_Time)
									+ " > seconds.");
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
						Thread.sleep(500);
					}
				}

				if (wait == (NewNsfOnline_Element_Max_Wait_Time)) {
					throw new InterruptedException("Loading Issue : After < " + (NewNsfOnline_Element_Max_Wait_Time)
							+ " > Seconds wait page is not getting Load, please refer screenshot >> PageLoadFail"
							+ fnsScreenShot_Date_format() + "*****  < " + Loading_Image_Xpath + " >");
				}
				Actual_Loading_Time++;
			}

			PageSourceText = driver.getPageSource().toLowerCase();

			if (PageSourceText.contains("modal-content")) {
				boolean ContentPopupDisplay = false;
				try {
					if (driver.findElement(By.xpath(OR_New_NSFOnline.getProperty("NewNsfOnline_Content_Model_Popup")))
							.isDisplayed()) {
						ContentPopupDisplay = true;
					}
				} catch (Throwable n) {
					System.out.println("DOWN - - modal-content - error - Page source");
				}
				if (ContentPopupDisplay) {
					WebElement Element = fnsGet_OR_New_NSFOnline_ObjectX("NewNsfOnline_Content_Model_Popup");
					Actions act = new Actions(driver);
					act.moveToElement(Element).build().perform();
					ErrorMsgText = fnsGet_OR_New_NSFOnline_ObjectX("NewNsfOnline_Content_Model_Popup").getText().trim();
					throw new IllegalArgumentException();
				}
			}

		} catch (IllegalArgumentException i) {
			// isTestCasePass = false;
			TestSuiteBase_MonitorPlan.fnsTake_Screen_Shot_Without_OR("UnExpectedError");
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
				fnsLoading_Progressing_wait(waitcount);
			} catch (Throwable tt) {
				isTestCasePass = false;
				TestSuiteBase_MonitorPlan.fnsTake_Screen_Shot_Without_OR("PageLoadFail_NS");
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
			TestSuiteBase_MonitorPlan.fnsTake_Screen_Shot_Without_OR("PageLoadFail");
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(ie));
			throw new Exception(Throwables.getStackTraceAsString(ie));
		} catch (Throwable tt) {
			// isTestCasePass = false;
			TestSuiteBase_MonitorPlan.fnsTake_Screen_Shot_Without_OR("PageLoadFail");
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(tt));
			throw new Exception(Throwables.getStackTraceAsString(tt));
		}
	}

	// Function of loading image appear on the screen (Block UI).
	public void fnsLoading_Progressing_on_Popup_wait_for_Popup(int waitcount) throws Throwable {
		try {

			LoadingImageFlag = false;
			Actual_Loading_Time = 1;
			String Loading_Classes_From_OR = OR_New_NSFOnline.getProperty("Loading_Progressing_on_Popup").trim();
			Integer PageLoadWait = TestSuiteBase_MonitorPlan.fnsLoading_PageLoad();
			for (int wait = PageLoadWait; wait <= ((NewNsfOnline_Element_Max_Wait_Time)); wait++) {
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
						fnsApps_Report_Logs("Progressing Loading image is displayed for < " + (Actual_Loading_Time)
								+ " > seconds.");
						break;
					} else if (!(driver.findElement(By.xpath(Loading_Image_Xpath)).isDisplayed()) && wait > waitcount
							&& LoadingImageFlag == false) {
						fnsApps_Report_Logs(
								"******  Pause/Wait is for < " + Actual_Loading_Time + " > seconds.  *****  "); // To
																												// increase
																												// performance,
																												// Pause/Wait
																												// time
																												// can
																												// set
																												// to 1
																												// sec
						break;
					} else if (wait < waitcount && LoadingImageFlag == false) {
						Thread.sleep(500);
					}
				} catch (Throwable n) {
					if (wait > waitcount) {
						if (LoadingImageFlag == true) {
							Loading_Xpath_Array.add(Loading_Image_Xpath + "&&");
							fnsApps_Report_Logs("Progressing Loading image is displayed for < " + (Actual_Loading_Time)
									+ " > seconds.");
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
						Thread.sleep(500);
					}
				}

				if (wait == (NewNsfOnline_Element_Max_Wait_Time)) {
					throw new InterruptedException("Loading Issue : After < " + (NewNsfOnline_Element_Max_Wait_Time)
							+ " > Seconds wait page is not getting Load, please refer screenshot >> PageLoadFail"
							+ fnsScreenShot_Date_format() + "*****  < " + Loading_Image_Xpath + " >");
				}
				Actual_Loading_Time++;
			}

		} catch (IllegalArgumentException i) {
			TestSuiteBase_MonitorPlan.fnsTake_Screen_Shot_Without_OR("UnExpectedError");
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
				fnsLoading_Progressing_on_Popup_wait_for_Popup(waitcount);
			} catch (Throwable tt) {
				isTestCasePass = false;
				TestSuiteBase_MonitorPlan.fnsTake_Screen_Shot_Without_OR("PageLoadFail");
				fnsApps_Report_Logs(Throwables.getStackTraceAsString(tt));
				throw new Exception(Throwables.getStackTraceAsString(tt));
			}
		} catch (StaleElementReferenceException n) {
			fnsApps_Report_Logs("***** Progressing Loading image displayed for < " + Actual_Loading_Time
					+ " > seconds. **************** Stale");
		} catch (NoSuchWindowException W) {
			throw new Exception(W.getMessage());
		} catch (InterruptedException ie) {
			System.out.println("InterruptedException");
			TestSuiteBase_MonitorPlan.fnsTake_Screen_Shot_Without_OR("PageLoadFail");
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(ie));
			throw new Exception(Throwables.getStackTraceAsString(ie));
		} catch (Throwable tt) {
			TestSuiteBase_MonitorPlan.fnsTake_Screen_Shot_Without_OR("PageLoadFail");
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(tt));
			throw new Exception(Throwables.getStackTraceAsString(tt));
		}
	}

	public void fnsLoading_UnwantedPopupError() throws Throwable {
		try {
			boolean ContentPopupDisplay = false;
			PageSourceText = driver.getPageSource().toLowerCase();
			if (PageSourceText.contains("modal-content")) {
				try {
					if (driver.findElement(By.xpath(OR_New_NSFOnline.getProperty("NewNsfOnline_Content_Model_Popup")))
							.isDisplayed()) {
						ContentPopupDisplay = true;
					}
				} catch (Throwable n) {
					// nothing to do
				}

				if (ContentPopupDisplay) {
					WebElement Element = fnsGet_OR_New_NSFOnline_ObjectX("NewNsfOnline_Content_Model_Popup");
					Actions act = new Actions(driver);
					act.moveToElement(Element).build().perform();
					ErrorMsgText = fnsGet_OR_New_NSFOnline_ObjectX("NewNsfOnline_Content_Model_Popup").getText().trim();
					fnsTake_Screen_Shot("UnExpectedError");
					fnsApps_Report_Logs("Getting Un-Expected Application Error <" + ErrorMsgText
							+ ">, please refer screenshot >>UnExpectedError" + fnsScreenShot_Date_format() + "*****  < "
							+ Loading_Image_Xpath + " >");
					throw new Exception("Getting Un-Expected Application Error <" + ErrorMsgText
							+ ">, please refer screenshot >> UnExpectedError" + fnsScreenShot_Date_format()
							+ "*****  < " + Loading_Image_Xpath + " >");
				}
			}
		} catch (Throwable t) {
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}

	public String fnsRemoveFormatting(String s) {

		s = s.replace("}", " ");
		s = s.replace("(", " ");
		s = s.replace(")", " ");
		s = s.replace("{", " ");
		s = s.replaceAll("\\s+", " ");
		s = s.trim();

		return s;
	}

	// Function for Application Log Date format
	public String fnsLOGS_Date_format() {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date date = new Date();
		return (dateFormat.format(date));
	}

	// Function for Screen date format
	public String fnsScreenShot_Date_format() {
		DateFormat dateFormat = new SimpleDateFormat("_dd.MM.yyyy_HH.mm.ss");
		Date date = new Date();
		return (dateFormat.format(date));
	}

	// Application date format
	public String fns_Return_UK_London_Time(String Date_format, Integer Year, Integer Month, Integer Day, Integer Hour,
			Integer Minute) {
		Calendar cal = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat(Date_format);
		dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/London"));
		cal.add(Calendar.YEAR, Year);
		cal.add(Calendar.MONTH, Month);
		cal.add(Calendar.DAY_OF_MONTH, Day);
		cal.add(Calendar.HOUR_OF_DAY, Hour);
		cal.add(Calendar.MINUTE, Minute);
		String DemandDate = dateFormat.format(cal.getTime()).trim();
		return DemandDate;
	}

	// Application date format
	public String fns_CheckList_Requried_Date_format(String Date_format, Integer Day_Increment, Integer Year) {
		Calendar cal = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat(Date_format);
		cal.add(Calendar.YEAR, Year);
		cal.add(Calendar.DAY_OF_MONTH, Day_Increment);
		String DemandDate = dateFormat.format(cal.getTime()).trim();
		return DemandDate;
	}

	/*
	 * //Application date format
	 * public String fns_Requried_Date_format(String Date_format, Integer Year,
	 * Integer Month, Integer Day, Integer Hour, Integer Minute) {
	 * Calendar cal = Calendar.getInstance();
	 * DateFormat dateFormat = new SimpleDateFormat(Date_format);
	 * cal.add(Calendar.YEAR, Year);
	 * cal.add(Calendar.MONTH, Month);
	 * cal.add(Calendar.DAY_OF_MONTH, Day);
	 * cal.add(Calendar.HOUR_OF_DAY, Hour);
	 * cal.add(Calendar.MINUTE, Minute);
	 * String DemandDate=dateFormat.format(cal.getTime()).trim();
	 * return DemandDate;
	 * }
	 */

	// Function for Screen date format
	public String fnsReturn_CurrentTime() {
		DateFormat dateFormat = new SimpleDateFormat("HHmmss");
		Date date = new Date();
		return (dateFormat.format(date));
	}

	// Function For Application Log
	public long fnsApps_Report_Logs(String LogMessage) {

		LogMessage = LogMessage.replaceAll("�", "");
		LogMessage = LogMessage.replaceAll("�", "");

		if ((LogMessage.toLowerCase().contains("failed")) || (LogMessage.toLowerCase().contains("java"))
				|| (LogMessage.toLowerCase().contains("webdriver")) || (LogMessage.toLowerCase().contains("assert"))) {
			Reporter.log(" |");
			Reporter.log(" |");
			Reporter.log(" | Script has been failed, after performing the Step <" + (TC_Step - 1)
					+ ">, Please refer the below error....");
			Reporter.log(" |");
			Reporter.log(fnsLOGS_Date_format() + " | " + LogMessage);
		} else if (((LogMessage.toLowerCase().contains("success")) || (LogMessage.toLowerCase().contains("load"))
				|| (LogMessage.toLowerCase().contains("click")) || (LogMessage.toLowerCase().contains("type"))
				|| (LogMessage.toLowerCase().contains("select")) || (LogMessage.toLowerCase().contains("button"))
				|| (LogMessage.toLowerCase().contains("query"))) && !(LogMessage.toLowerCase().contains("move"))
				|| (LogMessage.toLowerCase().contains("url")) || (LogMessage.toLowerCase().contains("automation"))) { // Steps
																														// Log
			if (!((LogMessage.toLowerCase().contains("success")) || (LogMessage.toLowerCase().contains("load"))
					|| (LogMessage.toLowerCase().contains("test case")))
					|| (LogMessage.toLowerCase().contains("select")) || (LogMessage.toLowerCase().contains("menu"))
					|| (LogMessage.toLowerCase().contains("url")) || (LogMessage.toLowerCase().contains("button"))) {
				LogMessage = "STEP " + TC_Step + " == " + LogMessage;
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
		return TC_Step;
	}

	// Get Excel Cell value by column name
	public String fnsReturn_ExcelCellValue_ByMatchingColumnName(String SheetName, String ColumnName) throws Throwable {
		try {
			String CellValue = null;
			for (int i = 5; i < 50; i++) {
				for (int j = 1; j < 16; j++) {
					String ExcelCellValue = New_NSFOnline_Suitexls.getCellData(SheetName, j, i);
					if (ExcelCellValue.equalsIgnoreCase(ColumnName)) {
						CellValue = New_NSFOnline_Suitexls.getCellData(SheetName, j, i + 1).trim();
						break;
					}
				}
				if (CellValue != null) {
					break;
				}
			}

			if (CellValue == null) {
				throw new Exception(
						"FAILED == Column < " + ColumnName + " > entry is Missing/Incorrect into the sheet < "
								+ SheetName + " > of the suite Excel 'New_NSFOnline'.");
			} else if (CellValue.equals("")) {
				fnsApps_Report_Logs("@@@@@@@@@@@  Value of Column < " + ColumnName
						+ " > is   'BLANK'   into the sheet < " + SheetName + " >.");
			}

			System.out.println(ColumnName + "   =============================    >>  " + CellValue);
			return CellValue;
		} catch (Throwable t) {
			// fnsApps_Report_Logs(t.getMessage());
			throw new Exception(t.getMessage());
		}
	}

	// Function to find and return the object using Xpath
	public WebElement fnsGet_OR_New_NSFOnline_ObjectX(String XpathKey) throws Exception {

		try {
			for (int waits = 0; waits < NewNsfOnline_Element_Max_Wait_Time; waits++) {

				if (driver.findElements(By.xpath(OR_New_NSFOnline.getProperty(XpathKey))).size() > 0) {
					break;
				} else {
					Thread.sleep(500);
				}

			}
			return driver.findElement(By.xpath(OR_New_NSFOnline.getProperty(XpathKey)));

		} catch (StaleElementReferenceException e) {
			WebDriverWait stalewaitvar = new WebDriverWait(driver, 10);
			stalewaitvar.until(
					ExpectedConditions.presenceOfElementLocated(By.xpath(OR_New_NSFOnline.getProperty(XpathKey))));
			WebElement webelemnt = driver.findElement(By.xpath(OR_New_NSFOnline.getProperty(XpathKey)));
			stalewaitvar.until(ExpectedConditions.stalenessOf(webelemnt));
			return driver.findElement(By.xpath(OR_New_NSFOnline.getProperty(XpathKey)));
		} catch (NoSuchWindowException W) {
			// isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (NoSuchElementException e) {
			// isTestCasePass = false;
			fnsTake_Screen_Shot("NoSuchElementException" + XpathKey);
			fnsApps_Report_Logs(
					"FAILED == Element is not found >> " + XpathKey + " ] , plz see screenshot [NoSuchElementException"
							+ XpathKey + fnsScreenShot_Date_format() + " ]");
			throw new Exception(
					"FAILED == Element is not found >> " + XpathKey + " ] , plz see screenshot [NoSuchElementException"
							+ XpathKey + fnsScreenShot_Date_format() + " ]");
		} catch (TimeoutException e) {
			// isTestCasePass = false;
			fnsTake_Screen_Shot("TimeOut" + XpathKey);
			fnsApps_Report_Logs("FAILED == Element is not Present, getting TimeOut >> " + XpathKey
					+ " ] , plz see screenshot [TimeOut" + XpathKey + fnsScreenShot_Date_format() + " ]");
			throw new Exception("FAILED == Element is not Present, getting TimeOut >> " + XpathKey
					+ " ] , plz see screenshot [TimeOut" + XpathKey + fnsScreenShot_Date_format() + " ]");
		} catch (Throwable e) {
			// isTestCasePass = false;
			fnsTake_Screen_Shot("NotPresent" + XpathKey);
			fnsApps_Report_Logs("FAILED == Element is not found >> " + XpathKey + " ] , plz see screenshot [NotPresent"
					+ XpathKey + fnsScreenShot_Date_format() + " ]" + ". Getting Exception  >> " + e.getMessage());
			throw new Exception("FAILED == Element is not found >> " + XpathKey + " ] , plz see screenshot [NotPresent"
					+ XpathKey + fnsScreenShot_Date_format() + " ]");
		}
	}

	// Function to click through JS
	public void fnsWait_and_Click_Through_JS(String Xpath_Without_OR) throws Exception {
		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse = (JavascriptExecutor) driver;
			WebElement Webelements = driver.findElement(By.xpath(Xpath_Without_OR));
			jse.executeScript("arguments[0].click();", Webelements);

			fnsApps_Report_Logs("PASSED == Click done on Element throught JS, having Xpath >> " + Xpath_Without_OR);
		} catch (Throwable t) {
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}

	// Function to click through JS
	public void fnsWait_and_Type_Through_JS(String Xpath_Without_OR, String InputValue) throws Exception {
		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse = (JavascriptExecutor) driver;
			WebElement Webelements = driver.findElement(By.xpath(Xpath_Without_OR));
			jse.executeScript("arguments[0].value='" + InputValue + "';", Webelements);

			fnsApps_Report_Logs("PASSED == Type done on Element throught JS, having Xpath >> " + Xpath_Without_OR);
		} catch (Throwable t) {
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}

	// Function to click
	public void fnsWait_and_Click(String XpathKey) throws Exception {
		try {
			try {
				fnsGet_OR_New_NSFOnline_ObjectX(XpathKey).click();
				fnsApps_Report_Logs("PASSED == Click done on Element having Xpath >> " + XpathKey);
			} catch (Throwable tt) {
				Thread.sleep(3000);
				fnsGet_OR_New_NSFOnline_ObjectX(XpathKey).click();
				fnsApps_Report_Logs(
						"(((((( 2nd Attampt ))))))---PASSED == Click done on Element having Xpath >> " + XpathKey);
			}
		} catch (NoSuchWindowException W) {
			// isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable e) {
			// isTestCasePass = false;
			fnsTake_Screen_Shot("UnableToClick_" + XpathKey);
			fnsApps_Report_Logs("FAILED == Unable To Click on Element having Xpath >> " + XpathKey
					+ ", plz see screenshot [ UnableToClick_" + XpathKey + fnsScreenShot_Date_format() + " ]"
					+ ". Getting Exception  >> " + Throwables.getStackTraceAsString(e));
			throw new Exception("FAILED == Unable To Click on element [ " + XpathKey
					+ " ] , plz see screenshot [ UnableToClick_" + XpathKey + fnsScreenShot_Date_format() + " ]"
					+ ". Getting Exception  >> " + Throwables.getStackTraceAsString(e));
		}
	}

	// Function to click
	public void fnsWait_Click_on_First_Visible_Element_if_More_than_One_Coming(String Element_Name,
			String WithoutOR_XpathKey) throws Exception {
		int Max_Click = 1;
		boolean Click_Done = false;
		while (true) {
			Integer Element_Size = driver.findElements(By.xpath(WithoutOR_XpathKey)).size();
			Click_Done = false;
			try {
				if (Element_Size > 0) {
					for (int i = 1; i <= Element_Size; i++) {
						String Element_Xpath = "(" + WithoutOR_XpathKey + ")[" + i + "]";
						WebElement Element = driver.findElement(By.xpath(Element_Xpath));
						if (Element.isDisplayed()) {
							Element.click();
							Click_Done = true;
							fnsApps_Report_Logs("PASSED == Click done on Element_" + i + " >> " + Element_Name);
							break;
						}
						if (i == Element_Size && Click_Done == false) {
							throw new Exception(
									"FAILED == Clicking on element_" + Element_Size + " with Name >> " + Element_Name
											+ " is getting fail (Not displayed), please see screenshot >> Click_FAIL_"
											+ Element_Name + fnsScreenShot_Date_format());
						}
					}

				} else {
					throw new Exception("FAILED == There is no such element" + Element_Size + " with Name >> "
							+ Element_Name + ", please see screenshot >> Click_FAIL_" + Element_Name
							+ fnsScreenShot_Date_format());
				}

			} catch (NoSuchWindowException W) {
				throw new Exception(W.getMessage());
			} catch (StaleElementReferenceException e) {
				if (Max_Click == 5) {
					fnsTake_Screen_Shot("Click_FAIL_");
					fnsApps_Report_Logs("After 5 try the click is still getting fail on element (" + Element_Name
							+ "), as the element is not present into the DOM, please see screenshot >> Click_FAIL_"
							+ Element_Name + fnsScreenShot_Date_format() + Throwables.getStackTraceAsString(e));
					throw new Exception("After 5 try the click is still getting fail on element (" + Element_Name
							+ "), as the element is not present into the DOM, please see screenshot >> Click_FAIL_"
							+ Element_Name + fnsScreenShot_Date_format() + Throwables.getStackTraceAsString(e));
				} else {
					Thread.sleep(5000);
				}
			} catch (Throwable t) {
				fnsTake_Screen_Shot("Click_FAIL_");
				fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
				throw new Exception(Throwables.getStackTraceAsString(t));
			}
			if (Click_Done) {
				break;
			}
			Max_Click++;
		}
		try {
			assertTrue(Click_Done);
		} catch (Throwable t) {
			fnsTake_Screen_Shot("Element_Not_Display_");
			throw new Exception(
					"FAILED, '" + Element_Name + "' is not display, please see screenshot >> Element_Not_Display_"
							+ Element_Name + fnsScreenShot_Date_format() + Throwables.getStackTraceAsString(t));

		}
	}

	public String fnsRetun_Xpath_of_First_Visible_Element_if_More_than_One_Coming(String WithoutOR_XpathKey)
			throws Exception {
		boolean Element_Visible = false;
		String Element_Xpath = "";
		Integer Max_Click = 1;
		while (true) {
			Integer Element_Size = driver.findElements(By.xpath(WithoutOR_XpathKey)).size();
			try {
				if (Element_Size > 0) {
					for (int i = 1; i <= Element_Size; i++) {
						Element_Xpath = "(" + WithoutOR_XpathKey + ")[" + i + "]";
						WebElement Element = driver.findElement(By.xpath(Element_Xpath));
						if (Element.isDisplayed()) {
							Element_Visible = true;
							break;
						}
						if (i == Element_Size && Element_Visible == false) {
							throw new Exception("FAILED == Xpath (" + WithoutOR_XpathKey
									+ ") is not displayed, please see screenshot >> Xpath_FAIL_"
									+ fnsScreenShot_Date_format());
						}
					}

				} else {
					throw new Exception("FAILED == There is no such xpath (" + WithoutOR_XpathKey
							+ "), please see screenshot >> Xpath_FAIL_" + fnsScreenShot_Date_format());
				}

			} catch (NoSuchWindowException W) {
				throw new Exception(W.getMessage());
			} catch (Throwable e) {
				if (Max_Click == 10) {
					fnsTake_Screen_Shot("Click_FAIL_");
					throw new Exception(Throwables.getStackTraceAsString(e));
				} else {
					Thread.sleep(1000);
				}
			}
			if (Element_Visible) {
				break;
			}
			Max_Click++;
		}
		return Element_Xpath;
	}

	// Function to type
	public void fnsWait_and_Type(String XpathKey, String value) throws Exception {
		try {
			try {
				fnsGet_OR_New_NSFOnline_ObjectX(XpathKey).sendKeys("");
				fnsGet_OR_New_NSFOnline_ObjectX(XpathKey).sendKeys(value);
				fnsApps_Report_Logs(
						"PASSED == Type Value [ " + value + " ] done on Element having Xpath  >> " + XpathKey);
			} catch (Throwable tt) {
				Thread.sleep(3000);
				fnsGet_OR_New_NSFOnline_ObjectX(XpathKey).sendKeys("");
				fnsGet_OR_New_NSFOnline_ObjectX(XpathKey).sendKeys(value);
				fnsApps_Report_Logs("(((((( 2nd Attampt ))))))---PASSED == Type Value [ " + value
						+ " ] done on Element having Xpath  >> " + XpathKey);
			}
		} catch (NoSuchWindowException W) {
			// isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable e) {
			// isTestCasePass = false;
			fnsTake_Screen_Shot("UnableToType_" + XpathKey);
			fnsApps_Report_Logs("FAILED == Unable To Type on Element having Xpath  >> " + XpathKey
					+ ", plz see screenshot [ UnableToType_" + XpathKey + fnsScreenShot_Date_format() + " ]"
					+ ". Getting Exception  >> " + Throwables.getStackTraceAsString(e));
			throw new Exception("FAILED == Unable To Type on element [ " + XpathKey
					+ " ] , plz see screenshot [ UnableToType_" + XpathKey + fnsScreenShot_Date_format() + " ]"
					+ ". Getting Exception  >> " + Throwables.getStackTraceAsString(e));
		}
	}

	// Type into the input field by passing label name
	public void fnsWait_Click_or_Type_By_LabelName(String NextAboveDiv_form_LableDiv_Xpath, String LabelName,
			String if_Type_then_ValueFromExcel_else_ClickString_to_Click_button) throws Throwable {
		try {
			boolean Type_done = false;
			boolean Click_done = false;

			fnsGet_Element_Enabled(NextAboveDiv_form_LableDiv_Xpath);
			int i = 0;
			List<WebElement> Outer_Div_Objects = fnsGet_OR_New_NSFOnline_ObjectX(NextAboveDiv_form_LableDiv_Xpath)
					.findElements(By.tagName("div"));
			while ((!Type_done) && (!Click_done)) {
				if (Outer_Div_Objects.size() > 1) {
					for (WebElement Outer_Div_Elements : Outer_Div_Objects) {
						String Application_Label_Text = Outer_Div_Elements.getText().toLowerCase().trim();
						if (Application_Label_Text.equalsIgnoreCase(LabelName.toLowerCase().trim())) {
							if (if_Type_then_ValueFromExcel_else_ClickString_to_Click_button.toLowerCase().trim()
									.equals("click")) {
								List<WebElement> button_Objects = Outer_Div_Elements.findElements(By.tagName("button"));
								for (WebElement button_elements : button_Objects) {
									button_elements.click();
									Click_done = true;
									fnsApps_Report_Logs("PASSED == Click done on '" + LabelName + "' button.");
									break;
								}
							} else {
								List<WebElement> Input_Objects = Outer_Div_Elements.findElements(By.tagName("input"));
								for (WebElement Input_elements : Input_Objects) {
									Input_elements.clear();
									Input_elements
											.sendKeys(if_Type_then_ValueFromExcel_else_ClickString_to_Click_button);
									Type_done = true;
									fnsApps_Report_Logs(
											"PASSED == <" + if_Type_then_ValueFromExcel_else_ClickString_to_Click_button
													+ "> Type done on element >> " + LabelName);
									break;
								}
							}
						} else {
							Outer_Div_Objects = Outer_Div_Elements.findElements(By.tagName("div"));
						}
						if ((Type_done == true) || (Click_done == true)) {
							break;
						}
					}
				}

				if ((Type_done == false) && i == 100) {
					fnsTake_Screen_Shot("Type_Fail");
					throw new Exception("FAILED == Unable to type into the <" + LabelName
							+ "> field, please refer the screen shot >> Type_Fail" + fnsScreenShot_Date_format());
				}
				if ((Click_done == false) && i == 100) {
					fnsTake_Screen_Shot("Click_Fail");
					throw new Exception("FAILED == Unable to Click on <" + LabelName
							+ "> button, please refer the screen shot >> Click_Fail" + fnsScreenShot_Date_format());
				}
				i++;
			}

		} catch (Throwable t) {
			isTestCasePass = false;
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}

	// Function to Clear Field
	public void fnsWait_and_Clear(String XpathKey) throws Exception {

		try {
			WebElement Elements = fnsGet_OR_New_NSFOnline_ObjectX(XpathKey);
			Elements.clear();
			Elements.clear();

			// fnsGet_OR_New_NSFOnline_ObjectX(XpathKey).clear();
			fnsApps_Report_Logs("PASSED == Clear done Element having Xpath  >> " + XpathKey);
		} catch (NoSuchWindowException W) {
			// isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable e) {
			// isTestCasePass = false;
			fnsTake_Screen_Shot("UnableToClear_" + XpathKey);
			fnsApps_Report_Logs("FAILED == Unable To performe Clear on Element having Xpath  >> " + XpathKey
					+ ", plz see screenshot [ UnableToClear_" + XpathKey + fnsScreenShot_Date_format() + " ]"
					+ ". Getting Exception  >> " + e.getMessage());
			throw new Exception("Unable To performe Clear on element [ " + XpathKey
					+ " ] , plz see screenshot [ UnableToClear_" + XpathKey + fnsScreenShot_Date_format() + " ]"
					+ ". Getting Exception  >> " + e.getMessage());
		}
	}

	// SElect matching value from lookup
	public void fnsSelect_Value_from_LookupPopup_By_Clicking_on_LookUpButton(String Popuptitle,
			String LookupButtonLabelName, String Lookup_Input_LabelName,
			String Lookup_Search_and_Matching_Value_from_Excel, String NextAboveDiv_form_LK_LableDiv_Xpath)
			throws Throwable {
		try {
			boolean Result_are_Coming = false;
			boolean Matching_Value_found = false;
			List<WebElement> SearchResult_Rows_Objs = null;
			fnsWait_Click_or_Type_By_LabelName(NextAboveDiv_form_LK_LableDiv_Xpath, LookupButtonLabelName, "Click");
			fnsLoading_Progressing_on_Popup_wait_for_Popup(2);

			fnsGet_Element_Enabled("Lookup_Search_Bttn");
			String Lookup_Title_Xpath = TestSuiteBase_MonitorPlan
					.WithOut_OR_Return_XPATH_if_More_than_One_Coming(OR_New_NSFOnline.getProperty("Lookup_title"));

			String Lookup_title = driver.findElement(By.xpath(Lookup_Title_Xpath)).getText().toLowerCase().trim();
			assert Lookup_title.contains(Popuptitle.toLowerCase().trim()) : "FAILED == Look up Title '" + Popuptitle
					+ "' is not matched, please refer the screen shot >> Lookup_Title_Not_Match"
					+ fnsScreenShot_Date_format();

			if (!(Lookup_Input_LabelName.equals(""))) {
				fnsWait_Click_or_Type_By_LabelName("Lookup_SearchCriteria", Lookup_Input_LabelName,
						Lookup_Search_and_Matching_Value_from_Excel);
				fnsLoading_Progressing_on_Popup_wait_for_Popup(1);
			}

			fnsWait_and_Click("Lookup_Search_Bttn");
			fnsLoading_Progressing_on_Popup_wait_for_Popup(2);

			fnsGet_Element_Enabled("View_Result_Table");
			for (int rowcount = 1; rowcount <= 60; rowcount++) {
				SearchResult_Rows_Objs = fnsGet_OR_New_NSFOnline_ObjectX("View_Result_Table")
						.findElements(By.tagName("tr"));
				if (SearchResult_Rows_Objs.size() > 0) {
					Result_are_Coming = true;
					break;
				} else {
					Thread.sleep(1000);
				}
			}
			if (Result_are_Coming == false) {
				fnsTake_Screen_Shot("LK_SearchRecords_are_not_coming");
				throw new Exception(
						"FAILED == Records are not displayed, after clicking on search button, plese refer the screen shot >> LK_SearchRecords_are_not_coming"
								+ fnsScreenShot_Date_format());
			}

			for (WebElement SearchResult_Rows_Elements : SearchResult_Rows_Objs) {
				String SearchResultRow_Text = SearchResult_Rows_Elements.getText().toLowerCase().trim();
				if (SearchResultRow_Text.contains(Lookup_Search_and_Matching_Value_from_Excel.toLowerCase().trim())) {
					Matching_Value_found = true;
					List<WebElement> Radio_button_objs = SearchResult_Rows_Elements.findElements(By.tagName("input"));
					if (Radio_button_objs.size() == 1) {
						for (WebElement Radio_button_element : Radio_button_objs) {
							Radio_button_element.click();
							fnsLoading_Progressing_on_Popup_wait_for_Popup(1);
							break;
						}
					} else {
						fnsTake_Screen_Shot("LK_Radio_Button_Click_Fail");
						throw new Exception("FAILED == Radio button selection is fail for the record '"
								+ Lookup_Search_and_Matching_Value_from_Excel + "' as <" + Radio_button_objs.size()
								+ "> radio button is coming, plese refer the screen shot >> LK_Radio_Button_Click_Fail"
								+ fnsScreenShot_Date_format());
					}
				}
				if (Matching_Value_found == true) {
					break;
				}
			}

			if (Matching_Value_found == false) {
				fnsTake_Screen_Shot("LK_Matching_Record_Not_Found");
				throw new Exception("FAILED == '" + Lookup_Search_and_Matching_Value_from_Excel
						+ "' is not found into the search results, after clicking on search button, plese refer the screen shot >> LK_Matching_Record_Not_Found"
						+ fnsScreenShot_Date_format());
			}

			fnsGet_Element_Displayed("Lookup_Select_and_Save_Bttn");
			fnsWait_Click_on_First_Visible_Element_if_More_than_One_Coming("Select & Save",
					OR_New_NSFOnline.getProperty("Lookup_Select_and_Save_Bttn"));
			fnsLoading_Progressing_on_Popup_wait_for_Popup(2);

			fnsApps_Report_Logs("PASSED == Successfully select the value <"
					+ Lookup_Search_and_Matching_Value_from_Excel + "> from look up '" + Popuptitle + "'.");
		} catch (AssertionError a) {
			fnsTake_Screen_Shot("Lookup_Title_Not_Match");
			isTestCasePass = false;
			throw new Exception(Throwables.getStackTraceAsString(a));
		} catch (Throwable t) {
			isTestCasePass = false;
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}

	// Function to wait for element
	public void fnsGet_Element_Enabled(String XpathKey) throws Exception {

		try {
			for (int wait = 0; wait < 3; wait++) {
				if (driver.findElements(By.xpath(OR_New_NSFOnline.getProperty(XpathKey))).size() > 0) {
					// fnsGet_OR_New_NSFOnline_ObjectX(XpathKey);
					WebDriverWait elementwaitvar = new WebDriverWait(driver, NewNsfOnline_Element_Max_Wait_Time);
					elementwaitvar.until(ExpectedConditions
							.visibilityOfElementLocated(By.xpath(OR_New_NSFOnline.getProperty(XpathKey))));

					WebDriverWait elementwaitvar1 = new WebDriverWait(driver, NewNsfOnline_Element_Max_Wait_Time);
					elementwaitvar1
							.until(ExpectedConditions
									.visibilityOfElementLocated(By.xpath(OR_New_NSFOnline.getProperty(XpathKey))))
							.isEnabled();

					WebDriverWait elementwaitvar2 = new WebDriverWait(driver, NewNsfOnline_Element_Max_Wait_Time);
					elementwaitvar2
							.until(ExpectedConditions
									.visibilityOfElementLocated(By.xpath(OR_New_NSFOnline.getProperty(XpathKey))))
							.isDisplayed();

					break;
				} // if loop closed
				else {
					throw new Exception();
				}
			}
			fnsApps_Report_Logs("PASSED == Element is Visible having Xpath  >> " + XpathKey);
		} catch (NoSuchWindowException W) {
			// isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (TimeoutException to) {
			fnsTake_Screen_Shot("TimeOut_Element_Not_Coming");
			throw new Exception("TimeOut : " + Throwables.getStackTraceAsString(to));
		} catch (Throwable t) {
			try {
				// fnsApps_Report_Logs(t.getMessage()); // Not working
				Thread.sleep(3000);
				WebDriverWait elementwaitvar3 = new WebDriverWait(driver, (NewNsfOnline_Element_Max_Wait_Time));
				elementwaitvar3
						.until(ExpectedConditions
								.visibilityOfElementLocated(By.xpath(OR_New_NSFOnline.getProperty(XpathKey))))
						.isEnabled();// }
				fnsApps_Report_Logs("PASSED == Element is Visible having Xpath  >> " + XpathKey);
			} catch (NoSuchWindowException W) {
				// isTestCasePass = false;
				throw new Exception(W.getMessage());
			} catch (Throwable e) {
				// isTestCasePass = false;
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

	// Function to wait for element
	public void fnsGet_Element_Displayed(String XpathKey) throws Exception {

		try {
			for (int wait = 0; wait <= ((NewNsfOnline_Element_Max_Wait_Time) * 2); wait++) {
				try {
					if (fnsGet_OR_New_NSFOnline_ObjectX(XpathKey).isDisplayed()) {
						break;
					} else {
						Thread.sleep(500);
					}
				} catch (Throwable t) {
					Thread.sleep(500);
					// nothing to do
				}
				if (wait == NewNsfOnline_Element_Max_Wait_Time) {
					throw new Exception(
							"FAILED == Element is not displayed, after <" + ((NewNsfOnline_Element_Max_Wait_Time))
									+ "> seconds wait, please refer screenshot >> " + fnsScreenShot_Date_format());
				}
			}
			fnsApps_Report_Logs("PASSED == Element is displayed having Xpath  >> " + XpathKey);
		} catch (NoSuchWindowException W) {
			// isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			// isTestCasePass = false;
			fnsTake_Screen_Shot(XpathKey);
			fnsApps_Report_Logs("FAILED == Element is not displayed having Xpath  >> " + XpathKey
					+ ", plz see screenshot [ " + "" + XpathKey + fnsScreenShot_Date_format()
					+ " ]. Getting Exception >> " + Throwables.getStackTraceAsString(t));
			throw new Exception("FAILED == Element is not displayed having Xpath  [ " + XpathKey
					+ " ] , plz see screenshot [ " + "" + XpathKey + fnsScreenShot_Date_format()
					+ " ]. Getting Exception >> " + Throwables.getStackTraceAsString(t));

		}
	}

	public void fnsGet_Element_Enabled_By_Passing_Wait_and_Screenshot_Condition(long waitTime, boolean TakeScreenshot,
			String XpathKey) throws Exception {
		try {
			WebDriverWait elementwaitvar = new WebDriverWait(driver, waitTime);
			elementwaitvar.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_New_NSFOnline.getProperty(XpathKey))));

			WebDriverWait elementwaitvar1 = new WebDriverWait(driver, waitTime);
			elementwaitvar1.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_New_NSFOnline.getProperty(XpathKey))))
					.isEnabled();

			WebDriverWait elementwaitvar2 = new WebDriverWait(driver, waitTime);
			elementwaitvar2
					.until(ExpectedConditions
							.visibilityOfElementLocated(By.xpath(OR_New_NSFOnline.getProperty(XpathKey))))
					.isDisplayed();
		} catch (Throwable t) {
			// isTestCasePass = false;
			if (TakeScreenshot == true) {
				fnsTake_Screen_Shot(XpathKey);
			}
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}

	// Fetch field text
	public String fnsGet_Field_TEXT(String XpathKey) throws Exception {
		try {

			// fnsGet_Element_Enabled(XpathKey);
			String TextFetch = fnsGet_OR_New_NSFOnline_ObjectX(XpathKey).getText().trim();
			System.out.println(
					"PASSED == Text[" + TextFetch + "] fetch done on Element having xpath [ " + XpathKey + " ].");
			fnsApps_Report_Logs("PASSED == Text fetch done on Element having xpath [ " + XpathKey + " ].");
			return TextFetch;
		} catch (NoSuchWindowException W) {
			// isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			// isTestCasePass = false;
			fnsTake_Screen_Shot("TextFetchFail" + XpathKey);
			fnsApps_Report_Logs("FAILED == Unable to Fetch Text on Element having xpath, Please refer screenshot ["
					+ "TextFetchFail" + XpathKey + fnsScreenShot_Date_format() + "]." + ". Getting Exception  >> "
					+ t.getMessage());
			throw new Exception("FAILED == Unable to Fetch Text on Element having xpath, Please refer screenshot ["
					+ "TextFetchFail" + XpathKey + fnsScreenShot_Date_format() + "]." + ". Getting Exception  >> "
					+ t.getMessage());
		}
	}

	// It is Not working if value is in lower/upper case changed
	// function to select drop down value
	public void fnaDD_value_Select_TagOPTION_DDTypeSelect(String DD_Xpath_Select, String Matching_Value_from_Excel,
			long Wait_Time) throws Exception {

		try {
			fnsGet_Element_Enabled(DD_Xpath_Select);
			for (int i = 1; i <= Wait_Time; i++) {
				try {
					Select DropDown = new Select(fnsGet_OR_New_NSFOnline_ObjectX(DD_Xpath_Select));
					DropDown.selectByVisibleText(Matching_Value_from_Excel); // if Not working use above method.
					fnsApps_Report_Logs(
							"PASSED == Value [" + Matching_Value_from_Excel + "] selection from drop down is done in <"
									+ (i) + ">seconds wait, DD having xpath >>  " + DD_Xpath_Select);
					break;
				} catch (Throwable t) {
					Thread.sleep(1000);
					// nothing to do
				}
				if (i == NewNsfOnline_Element_Max_Wait_Time) {
					throw new Exception(i + " Attampted.");
				}
			}

		} catch (NoSuchWindowException W) {
			// isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (NoSuchElementException n) {
			throw new Exception("FAILED == Excel value < " + Matching_Value_from_Excel
					+ " > is not exists into the drop down ' " + DD_Xpath_Select
					+ " ', please refer screenshot >> DdValueSelect_Fail" + fnsScreenShot_Date_format()
					+ ". Getting Exception  >> " + Throwables.getStackTraceAsString(n).trim());
		} catch (Throwable t) {
			// isTestCasePass = false;
			fnsTake_Screen_Shot("DdValueSelect_Fail");
			throw new Exception("FAILED == '" + DD_Xpath_Select + "' value <" + Matching_Value_from_Excel
					+ "> selection fail, please refer screenshot >> DdValueSelect_Fail" + fnsScreenShot_Date_format()
					+ ". Getting Exception  >> " + Throwables.getStackTraceAsString(t).trim());
		}
	}

	// Return second label value
	public String fnsReturn_SecondLabelValue_by_Passing_FirstLabelName(String Top_Div_Contains_Labels_Xpath_WithoutOR,
			String First_Label_Name) throws Throwable {
		try {
			boolean First_Label_Found = false;
			boolean Second_Label_Value_Found = false;
			String Second_Label_Value = "";
			List<WebElement> Label_Objects = TestSuiteBase_MonitorPlan
					.WithOut_OR_fnGet_ObjectX(Top_Div_Contains_Labels_Xpath_WithoutOR)
					.findElements(By.tagName("label"));
			for (WebElement Label_Elements : Label_Objects) {
				String Label_Element_Text = Label_Elements.getText().trim();
				if (First_Label_Found == true) {
					Second_Label_Value = Label_Element_Text;
					Second_Label_Value_Found = true;
					break;
				}
				if (Label_Element_Text.toLowerCase().contains(First_Label_Name.toLowerCase().trim())) {
					First_Label_Found = true;
				}
			}

			if (First_Label_Found == false) {
				fnsTake_Screen_Shot("Label_Not_Found");
				throw new Exception("FAILED == Label is not found, please refer the screen shot >> Label_Not_Found"
						+ fnsScreenShot_Date_format());
			}

			fnsApps_Report_Logs("PASSED == Value <" + Second_Label_Value + "> fetch is done from label '"
					+ First_Label_Name + "'.");

			return Second_Label_Value;

		} catch (NoSuchWindowException W) {

			throw new Exception(W.getMessage());
		} catch (Throwable e) {
			fnsTake_Screen_Shot("Label_Not_Found");
			throw new Exception(Throwables.getStackTraceAsString(e));
		}

	}

	// function to select drop down value
	public void fnsDD_value_Select_TagOPTION_DDTypeSelect(String DD_Xpath_Select, String Matching_Value_from_Excel,
			long Wait_Time) throws Exception {
		try {
			fnsGet_Element_Enabled(DD_Xpath_Select);
			for (int i = 0; i <= Wait_Time; i++) {
				boolean Value_Matched = false;
				try {
					List<WebElement> DDobjectlists = fnsGet_OR_New_NSFOnline_ObjectX(DD_Xpath_Select)
							.findElements(By.tagName("option"));
					for (WebElement dd_value : DDobjectlists) {
						String dd_TEXT = dd_value.getText().toLowerCase().trim();
						if (dd_TEXT.equals(Matching_Value_from_Excel.toLowerCase().trim())) {
							dd_value.click();
							Value_Matched = true;
							break;
						}
					}
					if (Value_Matched) {
						fnsApps_Report_Logs("PASSED == Value [" + Matching_Value_from_Excel
								+ "] selection from drop down is done in <" + (i)
								+ ">seconds wait, DD having xpath >>  " + DD_Xpath_Select);
						break;
					} else {
						throw new NotContextException("FAILED == Excel value < " + Matching_Value_from_Excel
								+ " > is not exists into the drop down ' " + DD_Xpath_Select
								+ " ', please refer screenshot >> DdValueSelect_Fail" + fnsScreenShot_Date_format());
					}

				} catch (NotContextException NC) {
					if (i == Wait_Time) {
						throw new Exception(Throwables.getStackTraceAsString(NC));
					} else {
						Thread.sleep(800);
					}
				} catch (NoSuchWindowException W) {
					isTestCasePass = false;
					throw new Exception(W.getMessage());
				} catch (Throwable t) {
					if (i == Wait_Time) {
						throw new Exception(Throwables.getStackTraceAsString(t));
					} else {
						Thread.sleep(800);
					}
				}
			}
		} catch (Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot("DdValueSelect_Fail");
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}

	public void fnsDD_Verify_SelectedValue_TagOPTION_DDTypeSelect(String DD_Xpath_Select, String DD_Selected_Value)
			throws Exception {
		try {
			fnsGet_Element_Enabled(DD_Xpath_Select);
			boolean Option_Selected = false;
			boolean Option_Selected_Matched = false;

			List<WebElement> DDobjectlists = fnsGet_OR_New_NSFOnline_ObjectX(DD_Xpath_Select)
					.findElements(By.tagName("option"));
			for (WebElement dd_value : DDobjectlists) {
				String Option_Slected = dd_value.getAttribute("selected");
				if (!(Option_Slected == null)) {
					Option_Selected = true;
					String dd_TEXT = dd_value.getText().toLowerCase().trim();
					if (dd_TEXT.equals(DD_Selected_Value.toLowerCase().trim())) {
						Option_Selected_Matched = true;
						break;
					}
				}
			}
			if (Option_Selected == false) {
				throw new NotContextException(
						"FAILED == none of the value is selected into the drop down ' " + DD_Xpath_Select
								+ " ', please refer screenshot >> DdValueSelected_Fail" + fnsScreenShot_Date_format());
			}

			if (Option_Selected_Matched) {
				fnsApps_Report_Logs(
						"PASSED == Expected value < " + DD_Selected_Value + " > is Matched with the Actual Value < "
								+ DD_Selected_Value + " > from the drop down >> " + DD_Xpath_Select);
			} else {
				throw new NotContextException(
						"FAILED == Expected value < " + DD_Selected_Value + " > is NOT matched with the Actual Value < "
								+ DD_Selected_Value + " > from the drop down ' " + DD_Xpath_Select
								+ " ', please refer screenshot >> DdValueSelected_Fail" + fnsScreenShot_Date_format());
			}
		} catch (Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot("DdValueSelected_Fail");
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}

	// function to select drop down value
	public void fnsDD_value_Select_TagOPTION_DDTypeSelect_ContainsMatch(String DD_Xpath_Select,
			String Matching_Value_from_Excel, long Wait_Time) throws Exception {
		try {
			fnsGet_Element_Enabled(DD_Xpath_Select);
			for (int i = 0; i <= Wait_Time; i++) {
				boolean Value_Matched = false;
				try {
					List<WebElement> DDobjectlists = fnsGet_OR_New_NSFOnline_ObjectX(DD_Xpath_Select)
							.findElements(By.tagName("option"));
					for (WebElement dd_value : DDobjectlists) {
						String dd_TEXT = dd_value.getText().toLowerCase().trim();
						if (dd_TEXT.contains(Matching_Value_from_Excel.toLowerCase().trim())) {
							dd_value.click();
							Value_Matched = true;
							break;
						}
					}
					if (Value_Matched) {
						fnsApps_Report_Logs("PASSED == Value [" + Matching_Value_from_Excel
								+ "] selection from drop down is done in <" + (i)
								+ ">seconds wait, DD having xpath >>  " + DD_Xpath_Select);
						break;
					} else {
						throw new NotContextException("FAILED == Excel value < " + Matching_Value_from_Excel
								+ " > is not exists into the drop down ' " + DD_Xpath_Select
								+ " ', please refer screenshot >> DdValueSelect_Fail" + fnsScreenShot_Date_format());
					}

				} catch (NotContextException NC) {
					if (i == Wait_Time) {
						throw new Exception(Throwables.getStackTraceAsString(NC));
					} else {
						Thread.sleep(800);
					}
				} catch (NoSuchWindowException W) {
					isTestCasePass = false;
					throw new Exception(W.getMessage());
				} catch (Throwable t) {
					if (i == Wait_Time) {
						throw new Exception(Throwables.getStackTraceAsString(t));
					} else {
						Thread.sleep(800);
					}
				}
			}
		} catch (Throwable t) {
			isTestCasePass = false;
			TestSuiteBase_MonitorPlan.fnsTake_Screen_Shot_Without_OR("DdValueSelect_Fail");
			// fnsTake_Screen_Shot("DdValueSelect_Fail");
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}

	// function to select drop down value
	// need to work on it as label not found due to late page load
	public void fnsDD_Value_select_by_DDLabelName_MultiselectDD(String DD_Label_Name_ifDependent_CaseSen,
			String DD_Selection_Value_Text) throws Throwable {
		try {
			String DD_Main_Div_Xpath = "";
			String DD_Label_Name = "";
			if (DD_Label_Name_ifDependent_CaseSen.contains("Dependent")) {
				DD_Main_Div_Xpath = "//div[@class='form-group ng-scope']";
				if (!(DD_Label_Name_ifDependent_CaseSen.contains("_"))) {
					throw new Exception(
							"FAILED == In case of dependent DD, '_' is missing from label name, please add '_' into the label name.");
				}
				DD_Label_Name = DD_Label_Name_ifDependent_CaseSen.split("_")[0];
			} else {
				DD_Main_Div_Xpath = "//div[@class='form-group']";
				if (RunningClassName.trim().equalsIgnoreCase("BS_17_Testing_Reports")) {
					DD_Main_Div_Xpath = "//div[contains(@class, 'form-group')]";
				} else if (RunningClassName.trim().equalsIgnoreCase("BS_36_Service_Request")) {
					DD_Main_Div_Xpath = "//div[contains(@class, 'form-group')]";
					// DD_Main_Div_Xpath = "//div[@class='col-lg-4 col-md-4 col-sm-4 col-xs-12']";
				}
				DD_Label_Name = DD_Label_Name_ifDependent_CaseSen;
			}
			String DD_Label_Xpath = DD_Main_Div_Xpath + "/label";
			String Individual_DD_Label_Xpath = null;
			boolean DD_Label_Found = false;
			boolean DD_list_Loaded_After_Input_Enter = false;
			boolean DD_list_Matching_Value_Selection_Done = false;
			Integer DD_Position_in_HTML = null;
			WebElement DD_Input_Element = null;

			for (int DD_Label_try = 1; DD_Label_try <= NewNsfOnline_Element_Max_Wait_Time; DD_Label_try++) {
				try {
					if (driver.findElements(By.xpath(DD_Label_Xpath)).size() > 0) {
						for (int i = 1; i <= driver.findElements(By.xpath(DD_Label_Xpath)).size(); i++) {
							Individual_DD_Label_Xpath = "(" + DD_Label_Xpath + ")[" + i + "]";

							if (driver.findElements(By.xpath(Individual_DD_Label_Xpath)).size() > 0) {
								String Individual_DD_Label_TEXT = TestSuiteBase_MonitorPlan
										.WithOut_OR_fnGet_ObjectX(Individual_DD_Label_Xpath).getText().toLowerCase()
										.trim();
								if (Individual_DD_Label_TEXT.contains(DD_Label_Name.toLowerCase().trim())) {
									DD_Position_in_HTML = i;
									DD_Label_Found = true;
									break;
								}
							}
						}
					}
					fnsLoading_Progressing_wait(1);
					if (DD_Label_Found == true) {
						break;
					}
				} catch (Throwable t) {
					// nothing to do
				}

			}

			if (DD_Label_Found == false) {
				throw new Exception("FAILED == Drop down '" + DD_Label_Name
						+ "' is not found on the screen, please refer the screen shot >> DdValueSelect_Fail"
						+ fnsScreenShot_Date_format());
			}

			System.out.println(DD_Main_Div_Xpath);
			for (int Selection_try = 1; Selection_try <= 3; Selection_try++) {
				try {
					// Thread.sleep(500);
					String DD_Input_Xpath = "(" + DD_Main_Div_Xpath + ")[" + DD_Position_in_HTML
							+ "]/div/div/div/input";
					DD_Input_Element = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(DD_Input_Xpath);

					DD_Input_Element.sendKeys(DD_Selection_Value_Text);
					Thread.sleep(1000);
					String DD_All_HiddenVisible_UL_Xpath = "//ul[@class='k-list k-reset' and @data-role='staticlist']";

					for (int k = 1; k <= driver.findElements(By.xpath(DD_All_HiddenVisible_UL_Xpath)).size(); k++) {
						String DD_RunTime_Visible_UL_Xpath = "(" + DD_All_HiddenVisible_UL_Xpath + ")[" + k + "]";

						if (TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(DD_RunTime_Visible_UL_Xpath)
								.isDisplayed()) {
							List<WebElement> DD_li_objectlists = TestSuiteBase_MonitorPlan
									.WithOut_OR_fnGet_ObjectX(DD_RunTime_Visible_UL_Xpath)
									.findElements(By.tagName("li"));
							for (WebElement DD_li_elements : DD_li_objectlists) {
								if ((DD_li_elements.getText().toLowerCase().trim())
										.equals(DD_Selection_Value_Text.toLowerCase().trim())) {
									DD_li_elements.click();
									fnsLoading_Progressing_wait(2);
									TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(Individual_DD_Label_Xpath)
											.click();
									DD_list_Matching_Value_Selection_Done = true;
									break;
								}
							}
							DD_list_Loaded_After_Input_Enter = true;
							if (DD_list_Matching_Value_Selection_Done == false) {
								throw new Exception("FAILED == Value <" + DD_Selection_Value_Text
										+ "> is not exists into the drop down '" + DD_Label_Name
										+ "', please refer screen shot >> AuditsScreen_Open_Fail"
										+ fnsScreenShot_Date_format());
							} else {
								break;
							}
						}
					}

					if (DD_list_Loaded_After_Input_Enter == false) {
						throw new Exception("FAILED == Drop down '" + DD_Label_Name
								+ "' list is not getting load even though the value entered into the dd's filter, please refer screen shot >> AuditsScreen_Open_Fail"
								+ fnsScreenShot_Date_format());
					} else {
						break;
					}

				} catch (Throwable t) {
					if (Selection_try == 3) {
						throw new Exception(Throwables.getStackTraceAsString(t));
					} else {
						fnsLoading_Progressing_wait(5);
						DD_Input_Element.clear();
						Thread.sleep(250);
					}
				}
			}
			fnsApps_Report_Logs("PASSED == Value <" + DD_Selection_Value_Text + "> selection done from drop down '"
					+ DD_Label_Name + "'.");
		} catch (NoSuchWindowException W) {
			// isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			// isTestCasePass = false;
			fnsTake_Screen_Shot("DdValueSelect_Fail");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}

	}
	// ############################################################## Applications
	// Methods ##################################################################

	// Clicking on Menu Ajax Link
	public void fncSub_Menu_Ajax_Link_Click_By_PassingAjaxPath(String Menu_Ajax_Xpath, String Sub_Menu_Ajax_Xpath)
			throws Exception {
		try {
			fnsGet_Element_Enabled("Main_Menu_Ajax_Link");
			WebElement MainMenu = fnsGet_OR_New_NSFOnline_ObjectX("Main_Menu_Ajax_Link");
			Actions action = new Actions(driver);
			action.moveToElement(MainMenu).click().build().perform();
			Thread.sleep(500);
			Thread.sleep(1500); // temp
			if (driver.findElements(By.xpath(OR_New_NSFOnline.getProperty(Menu_Ajax_Xpath))).size() > 0) {
				fnsWait_Click_on_First_Visible_Element_if_More_than_One_Coming(Menu_Ajax_Xpath,
						OR_New_NSFOnline.getProperty(Menu_Ajax_Xpath));
				Thread.sleep(1000);
				fnsWait_Click_on_First_Visible_Element_if_More_than_One_Coming(Sub_Menu_Ajax_Xpath,
						OR_New_NSFOnline.getProperty(Sub_Menu_Ajax_Xpath));
			} else if (driver.findElements(By.xpath(OR_New_NSFOnline.getProperty(Sub_Menu_Ajax_Xpath))).size() > 0) {
				fnsWait_Click_on_First_Visible_Element_if_More_than_One_Coming(Sub_Menu_Ajax_Xpath,
						OR_New_NSFOnline.getProperty(Sub_Menu_Ajax_Xpath));
			} else {
				fnsTake_Screen_Shot("Menu_are_not_coming_");
				throw new Exception("FAILED == Both Menus '" + Menu_Ajax_Xpath + "' and '" + Sub_Menu_Ajax_Xpath
						+ "' are not coming, plz see screenshot >> Menu_are_not_coming_" + fnsScreenShot_Date_format());
			}
			// fnsLoading_Progressing_wait(5); //Not Working here as loading type changes
		} catch (NoSuchWindowException W) {
			// isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			// isTestCasePass = false;
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t).trim());
			throw new Exception(Throwables.getStackTraceAsString(t).trim());
		}
	}

	// Clicking on Menu Ajax Link
	public void fncMenu_Ajax_Link_Click_By_PassingAjaxPath(String Menu_Ajax_Xpath) throws Exception {
		try {
			fnsGet_Element_Enabled("Main_Menu_Ajax_Link");
			WebElement MainMenu = fnsGet_OR_New_NSFOnline_ObjectX("Main_Menu_Ajax_Link");

			Actions action = new Actions(driver);
			action.moveToElement(MainMenu).click().build().perform();

			Thread.sleep(500);
			Actions action1 = new Actions(driver);
			try {

				if (driver.findElements(By.xpath(OR_New_NSFOnline.getProperty(Menu_Ajax_Xpath))).size() > 0) {
					WebDriverWait elementwaitvar1 = new WebDriverWait(driver, 5);
					elementwaitvar1.until(ExpectedConditions
							.visibilityOfElementLocated(By.xpath(OR_New_NSFOnline.getProperty(Menu_Ajax_Xpath))))
							.isEnabled();

					WebDriverWait elementwaitvar2 = new WebDriverWait(driver, 5);
					elementwaitvar2.until(ExpectedConditions
							.visibilityOfElementLocated(By.xpath(OR_New_NSFOnline.getProperty(Menu_Ajax_Xpath))))
							.isDisplayed();
				}

			} catch (Throwable t) {
				action.moveToElement(MainMenu).click().build().perform();
			}
			WebElement Menu = fnsGet_OR_New_NSFOnline_ObjectX(Menu_Ajax_Xpath);
			action1.moveToElement(Menu).click().build().perform();

			fnsApps_Report_Logs("PASSED == Successfully Click on Menu <" + (Menu_Ajax_Xpath) + ">.");
			// fnsLoading_Progressing_wait(5); //Not Working here as loading type changes
		} catch (NoSuchWindowException W) {
			// isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			// isTestCasePass = false;
			fnsTake_Screen_Shot(Menu_Ajax_Xpath + "_Fail");
			fnsApps_Report_Logs("FAILED == Clicking on Menu <" + (Menu_Ajax_Xpath) + "> Failed, plz see screenshot ["
					+ Menu_Ajax_Xpath + "_Fail" + fnsScreenShot_Date_format() + "]" + ". Getting Exception  >> "
					+ Throwables.getStackTraceAsString(t).trim());
			throw new Exception("FAILED == Clicking on Menu <" + (Menu_Ajax_Xpath) + "> Failed, plz see screenshot ["
					+ Menu_Ajax_Xpath + "_Fail" + fnsScreenShot_Date_format() + "]" + ". Getting Exception  >> "
					+ Throwables.getStackTraceAsString(t).trim());
		}
	}

	// Clicking on Menu Ajax Link
	public void fnsMenu_Ajax_Link_Click_By_PassingAjaxPath_iPulse(String AjaxLinkXpath) throws Exception {
		try {
			fnsGet_Element_Enabled("Main_Menu_Ajax");
			WebElement Menu_Element = fnsGet_OR_New_NSFOnline_ObjectX("Main_Menu_Ajax");

			WebElement VersionLogoImage = fnsGet_OR_New_NSFOnline_ObjectX("iPulse_VersionLogoImage");
			VersionLogoImage.click();
			Thread.sleep(500);

			Actions action = new Actions(driver);
			action.moveToElement(Menu_Element).build().perform();

			Thread.sleep(500);
			Actions action1 = new Actions(driver);
			fnsGet_Element_Enabled(AjaxLinkXpath);
			WebElement AjaxLinkXpath_Element = fnsGet_OR_New_NSFOnline_ObjectX(AjaxLinkXpath);
			action1.moveToElement(AjaxLinkXpath_Element).click().build().perform();
			fnsApps_Report_Logs("PASSED == Successfully Click on Menu <" + (AjaxLinkXpath) + ">.");
		} catch (NoSuchWindowException W) {
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			fnsTake_Screen_Shot(AjaxLinkXpath + "_Fail");
			fnsApps_Report_Logs("FAILED == Clicking on Menu <" + (AjaxLinkXpath) + "> Failed, plz see screenshot ["
					+ AjaxLinkXpath + "_Fail" + fnsScreenShot_Date_format() + "]" + ". Getting Exception  >> "
					+ Throwables.getStackTraceAsString(t).trim());
			throw new Exception("FAILED == Clicking on Menu <" + (AjaxLinkXpath) + "> Failed, plz see screenshot ["
					+ AjaxLinkXpath + "_Fail" + fnsScreenShot_Date_format() + "]" + ". Getting Exception  >> "
					+ Throwables.getStackTraceAsString(t).trim());
		}
	}

	// Verify View exists into the view list and conditional click on it.
	public String fncMyView_SharedView_NewlyCreatedView_Display_and_Conditional_Click(
			Integer Share_is_1_and_MyView_is_2, boolean View_Must_Exists, String ViewName, String Click_OnView_Yes_No)
			throws Throwable {
		try {
			String Return_View_Exists_in_which_List = null;
			boolean View_Found_into_Menu_DD = false;
			boolean View_List_Paginaton_NEXT_Link = false;
			boolean List_Loaded = false;
			boolean View_List_Paginaton_Previous_Link_disAppear = false;

			fnsGet_Element_Enabled("ActiveView_DD");
			for (int i = 0; i < 5; i++) {
				if (driver.findElement(By.xpath(OR_New_NSFOnline.getProperty("ActiveView_ShareView_Link")))
						.isDisplayed()) {
					break;
				} else {
					TestSuiteBase_MonitorPlan
							.WithOut_OR_fnMove_To_Element_and_Click(OR_New_NSFOnline.getProperty("ActiveView_DD"));
					Thread.sleep(1000);
				}
			}

			Thread.sleep(500);

			List<WebElement> Menu_DDobjectlists = null;

			for (int Shared_MyView_Link_Click_Counter = Share_is_1_and_MyView_is_2; Shared_MyView_Link_Click_Counter <= Share_is_1_and_MyView_is_2; Shared_MyView_Link_Click_Counter++) {
				System.out.println("Satya Counter value = " + Shared_MyView_Link_Click_Counter);
				if (!(View_Found_into_Menu_DD)) {
					if (List_Loaded == false) {
						if (Shared_MyView_Link_Click_Counter == 1) {
							fnsGet_Element_Enabled("ActiveView_ShareView_Link");
							fnsWait_and_Click("ActiveView_ShareView_Link");
							// Thread.sleep(500);
							fnsGet_Element_Enabled("ActiveView_SharedView_DD_Data_Div");

							for (int i = 0; i <= 5; i++) {
								List_Loaded = false;
								Menu_DDobjectlists = fnsGet_OR_New_NSFOnline_ObjectX(
										"ActiveView_SharedView_DD_Data_Div").findElements(By.tagName("div"));
								for (WebElement Menu_Element : Menu_DDobjectlists) {
									String Menu_Element_Text = Menu_Element.getText().trim();
									// System.out.println(Menu_Element_Text);
									if (Menu_Element_Text.length() > 5) {
										List_Loaded = true;
										break;
									} else {
										break;
									}
								}

								if (List_Loaded == true) {
									break;
								} else {
									Thread.sleep(750);
								}

								if (i == 5 && List_Loaded == false) {
									fnsApps_Report_Logs("View Menu is not getting load (List coming Blank), after <"
											+ 10
											+ "> seconds wait,  please refer screen shot >> PASS_Please_ignore_View_List_Blank"
											+ fnsScreenShot_Date_format());
									break;
								}

							}

							Return_View_Exists_in_which_List = "shared view";
						} else if (Shared_MyView_Link_Click_Counter == 2) {
							fnsGet_Element_Enabled("ActiveView_MyView_Link");
							fnsWait_and_Click("ActiveView_MyView_Link");
							Thread.sleep(500);
							fnsGet_Element_Enabled("ActiveView_MyView_DD_Data_Div");

							for (int i = 0; i <= 5; i++) {
								List_Loaded = false;
								Menu_DDobjectlists = fnsGet_OR_New_NSFOnline_ObjectX("ActiveView_MyView_DD_Data_Div")
										.findElements(By.tagName("div"));
								for (WebElement Menu_Element : Menu_DDobjectlists) {
									String Menu_Element_Text = Menu_Element.getText().trim();
									System.out.println(Menu_Element_Text);
									if (Menu_Element_Text.length() > 5) {
										List_Loaded = true;
									} else {
										break;
									}
								}

								if (List_Loaded == true) {
									break;
								} else {
									Thread.sleep(750);
								}

								if (i == 5 && List_Loaded == false) {
									fnsApps_Report_Logs("View Menu is not getting load (List coming Blank), after <"
											+ 10
											+ "> seconds wait,  please refer screen shot >> PASS_Please_ignore_View_List_Blank"
											+ fnsScreenShot_Date_format());
									break;
								}

							}
							Return_View_Exists_in_which_List = "my view";
						}
					}

					if (List_Loaded) {
						for (WebElement Menu_Element : Menu_DDobjectlists) {
							String abc = Menu_Element.getText();
							System.out.println(abc);
							if (Menu_Element.getText().contains(ViewName)) {

								if (Click_OnView_Yes_No.equals("Yes")) {
									Thread.sleep(1000);
									Actions action = new Actions(driver);
									action.moveToElement(Menu_Element).click(Menu_Element).build().perform();
									fnsApps_Report_Logs("PASSED == Click done on View Name ( " + ViewName
											+ " ) from MyView/SharedView  Menu list on '"
											+ ViewName.split("-")[0].toUpperCase() + "' screen.");
									fnsLoading_Progressing_wait(2);
								} else if (Click_OnView_Yes_No.equals("No")) {
									fnsGet_Element_Enabled("ActiveView_Header_Label_Link");
									fnsWait_and_Click_Through_JS(
											OR_New_NSFOnline.getProperty("ActiveView_Header_Label_Link"));
									fnsApps_Report_Logs("PASSED == View Name ( " + ViewName
											+ " ) is exists into the MyView/SharedView Menu list on '"
											+ ViewName.split("-")[0].toUpperCase() + "' screen.");
								}
								View_Found_into_Menu_DD = true;
								break;
							}
						}
						if (View_Found_into_Menu_DD == false) {

							if (View_List_Paginaton_Previous_Link_disAppear == true) {

								for (int Displaytry = 1; Displaytry <= 5; Displaytry++) {
									try {
										if (fnsGet_OR_New_NSFOnline_ObjectX("ActiveView_Pagination_Next_link")
												.isDisplayed()) {
											fnsGet_OR_New_NSFOnline_ObjectX("ActiveView_Pagination_Next_link").click();
											Shared_MyView_Link_Click_Counter = Share_is_1_and_MyView_is_2;
											fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
											View_List_Paginaton_NEXT_Link = true;
											break;
										} else {
											View_List_Paginaton_NEXT_Link = false;
										}
									} catch (Throwable t) {
										if (Displaytry == 5) {
											View_List_Paginaton_NEXT_Link = false;
											throw new Exception(Throwables.getStackTraceAsString(t));
										} else {
											Thread.sleep(500);
										}
									}
								}
							}
							try {
								for (int i = 1; i <= 10; i++) {
									if (View_List_Paginaton_Previous_Link_disAppear == false) {
										if (driver
												.findElements(By.xpath(OR_New_NSFOnline
														.getProperty("ActiveView_Pagination_Previous_link")))
												.size() > 0) {
											if (fnsGet_OR_New_NSFOnline_ObjectX("ActiveView_Pagination_Previous_link")
													.isDisplayed()) {
												fnsGet_OR_New_NSFOnline_ObjectX("ActiveView_Pagination_Previous_link")
														.click();
												Shared_MyView_Link_Click_Counter = Share_is_1_and_MyView_is_2;
												fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
											}
										} else {
											View_List_Paginaton_Previous_Link_disAppear = true;
											break;
										}
									}
								}
							} catch (Throwable t) {
								// nothing to do
							}

						}

						if (View_Found_into_Menu_DD == false && View_List_Paginaton_NEXT_Link == false) {
							if (View_Must_Exists == true) {
								throw new Exception(
										"FAILED == View is not exists into the MyView/SharedView  Menu list on '"
												+ ViewName.split("-")[0].toUpperCase()
												+ "' screen, please refer screen shot >> ViewNmae_Fail"
												+ fnsScreenShot_Date_format());
							} else {
								fnsApps_Report_Logs("PASSED == View Name ( " + ViewName
										+ " ) is not exists into the MyView/SharedView Menu list on '"
										+ ViewName.split("-")[0].toUpperCase() + "' screen.");

								fnsGet_Element_Enabled("ActiveView_Header_Label_Link");
								fnsWait_and_Click_Through_JS(
										OR_New_NSFOnline.getProperty("ActiveView_Header_Label_Link"));

								fnsLoading_Progressing_wait(2);

								Return_View_Exists_in_which_List = "View Not Exists";
							}
						}
					} else {
						// fnsTake_Screen_Shot("PASS_Please_ignore_View_List_Blank");
						fnsWait_and_Click_Through_JS(OR_New_NSFOnline.getProperty("ActiveView_Header_Label_Link"));
						fnsLoading_Progressing_wait(2);
						Return_View_Exists_in_which_List = "View Not Exists";
						break;

					}
				}
			}
			fnsLoading_Progressing_wait(1);
			return Return_View_Exists_in_which_List;

		} catch (NoSuchWindowException W) {
			// isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			// isTestCasePass = false;
			fnsTake_Screen_Shot("ViewNmae_Fail");
			throw new Exception("View Name ( " + ViewName + " ) : " + Throwables.getStackTraceAsString(t));
		}

	}

	/*
	 * //Verify View exists into the view list and conditional click on it.
	 * public String
	 * fncMyView_SharedView_NewlyCreatedView_Display_and_Conditional_Click(Integer
	 * Share_is_1_and_MyView_is_2, boolean View_Must_Exists, String ViewName, String
	 * Click_OnView_Yes_No) throws Throwable{
	 * try{
	 * String Return_View_Exists_in_which_List = null;
	 * boolean View_Found_into_Menu_DD = false;
	 * boolean View_List_Paginaton_NEXT_Link = false;
	 * boolean List_Loaded = false;
	 * boolean View_List_Paginaton_Previous_Link_disAppear =false;
	 * 
	 * fnsGet_Element_Enabled("ActiveView_DD");
	 * for(int i=0; i<5; i++){
	 * if( driver.findElement(By.xpath(OR_New_NSFOnline.getProperty(
	 * "ActiveView_ShareView_Link"))).isDisplayed() ){
	 * break;
	 * }else{
	 * TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element_and_Click(
	 * OR_New_NSFOnline.getProperty("ActiveView_DD"));
	 * Thread.sleep(1000);
	 * }
	 * }
	 * 
	 * Thread.sleep(500);
	 * 
	 * List<WebElement> Menu_DDobjectlists = null;
	 * 
	 * for(int Shared_MyView_Link_Click_Counter = Share_is_1_and_MyView_is_2;
	 * Shared_MyView_Link_Click_Counter<=Share_is_1_and_MyView_is_2;
	 * Shared_MyView_Link_Click_Counter++){
	 * System.out.println("Satya Counter value = "+Shared_MyView_Link_Click_Counter)
	 * ;
	 * if( !(View_Found_into_Menu_DD) ){
	 * if(List_Loaded == false){
	 * if(Shared_MyView_Link_Click_Counter==1){
	 * fnsGet_Element_Enabled("ActiveView_ShareView_Link");
	 * fnsWait_and_Click("ActiveView_ShareView_Link");
	 * //Thread.sleep(500);
	 * fnsGet_Element_Enabled("ActiveView_SharedView_DD_Data_Div");
	 * 
	 * for(int i=0; i<=NewNsfOnline_Element_Max_Wait_Time; i++){
	 * List_Loaded = false;
	 * Menu_DDobjectlists=fnsGet_OR_New_NSFOnline_ObjectX(
	 * "ActiveView_SharedView_DD_Data_Div").findElements(By.tagName("div"));
	 * for(WebElement Menu_Element:Menu_DDobjectlists){
	 * String Menu_Element_Text = Menu_Element.getText().trim();
	 * //System.out.println(Menu_Element_Text);
	 * if(Menu_Element_Text.length()>5){ List_Loaded = true; break; }else{ break; }
	 * }
	 * 
	 * if(List_Loaded==true){ break; }else{ Thread.sleep(750); }
	 * 
	 * if(i==NewNsfOnline_Element_Max_Wait_Time && List_Loaded==false){
	 * throw new Exception("FAILED == View Menu is not getting load, after <"
	 * +NewNsfOnline_Element_Max_Wait_Time+"> seconds wait,  please refer screen shot >> ViewNmae_Fail"
	 * +fnsScreenShot_Date_format());
	 * }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * }
	 * 
	 * Return_View_Exists_in_which_List = "shared view";
	 * }else if (Shared_MyView_Link_Click_Counter==2){
	 * fnsGet_Element_Enabled("ActiveView_MyView_Link");
	 * fnsWait_and_Click("ActiveView_MyView_Link");
	 * Thread.sleep(500);
	 * fnsGet_Element_Enabled("ActiveView_MyView_DD_Data_Div");
	 * 
	 * for(int i=0; i<=NewNsfOnline_Element_Max_Wait_Time; i++){
	 * List_Loaded = false;
	 * Menu_DDobjectlists=fnsGet_OR_New_NSFOnline_ObjectX(
	 * "ActiveView_MyView_DD_Data_Div").findElements(By.tagName("div"));
	 * for(WebElement Menu_Element:Menu_DDobjectlists){
	 * String Menu_Element_Text = Menu_Element.getText().trim();
	 * System.out.println(Menu_Element_Text);
	 * if(Menu_Element_Text.length()>5){ List_Loaded = true; }else{ break; }
	 * }
	 * 
	 * if(List_Loaded==true){ break; }else{ Thread.sleep(750); }
	 * 
	 * if(i==NewNsfOnline_Element_Max_Wait_Time && List_Loaded==false){
	 * throw new Exception("FAILED == View Menu is not getting load, after <"
	 * +NewNsfOnline_Element_Max_Wait_Time+"> seconds wait,  please refer screen shot >> ViewNmae_Fail"
	 * +fnsScreenShot_Date_format());
	 * }
	 * 
	 * }
	 * Return_View_Exists_in_which_List = "my view";
	 * }
	 * }
	 * 
	 * for(WebElement Menu_Element:Menu_DDobjectlists){
	 * String abc = Menu_Element.getText();
	 * System.out.println(abc);
	 * if(Menu_Element.getText().contains(ViewName)){
	 * 
	 * 
	 * if(Click_OnView_Yes_No.equals("Yes")){
	 * Actions action = new Actions(driver);
	 * action.moveToElement(Menu_Element).click(Menu_Element).build().perform();
	 * fnsLoading_Progressing_wait(2);
	 * fnsApps_Report_Logs("PASSED == Click done on View Name ( "
	 * +ViewName+" ) from MyView/SharedView  Menu list on '"+ViewName.split("-")[0].
	 * toUpperCase()+"' screen.");
	 * }else{
	 * fnsApps_Report_Logs("PASSED == View Name ( "
	 * +ViewName+" ) is exists into the MyView/SharedView Menu list on '"+ViewName.
	 * split("-")[0].toUpperCase()+"' screen.");
	 * }
	 * View_Found_into_Menu_DD=true;
	 * break;
	 * }
	 * }
	 * if( View_Found_into_Menu_DD==false ){
	 * 
	 * if( View_List_Paginaton_Previous_Link_disAppear==true ){
	 * 
	 * for(int Displaytry=1; Displaytry<=5; Displaytry++){
	 * try{
	 * if(fnsGet_OR_New_NSFOnline_ObjectX("ActiveView_Pagination_Next_link").
	 * isDisplayed()){
	 * fnsGet_OR_New_NSFOnline_ObjectX("ActiveView_Pagination_Next_link").click();
	 * Shared_MyView_Link_Click_Counter = Share_is_1_and_MyView_is_2;
	 * fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
	 * View_List_Paginaton_NEXT_Link = true;
	 * break;
	 * }else{
	 * View_List_Paginaton_NEXT_Link = false;
	 * }
	 * }catch(Throwable t){
	 * if(Displaytry==5){
	 * View_List_Paginaton_NEXT_Link = false;
	 * throw new Exception(Throwables.getStackTraceAsString(t));
	 * }else{
	 * Thread.sleep(500);
	 * }
	 * }
	 * }
	 * }
	 * try{
	 * for(int i=1; i<=10; i++){
	 * if(View_List_Paginaton_Previous_Link_disAppear == false){
	 * if(driver.findElements(By.xpath(OR_New_NSFOnline.getProperty(
	 * "ActiveView_Pagination_Previous_link"))).size()>0){
	 * if(fnsGet_OR_New_NSFOnline_ObjectX("ActiveView_Pagination_Previous_link").
	 * isDisplayed()){
	 * fnsGet_OR_New_NSFOnline_ObjectX("ActiveView_Pagination_Previous_link").click(
	 * );
	 * Shared_MyView_Link_Click_Counter = Share_is_1_and_MyView_is_2;
	 * fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
	 * }
	 * }else{
	 * View_List_Paginaton_Previous_Link_disAppear = true;
	 * break;
	 * }
	 * }
	 * }
	 * }catch(Throwable t){
	 * //nothing to do
	 * }
	 * 
	 * }
	 * 
	 * if( View_Found_into_Menu_DD==false && View_List_Paginaton_NEXT_Link ==
	 * false){
	 * if(View_Must_Exists==true){
	 * throw new
	 * Exception("FAILED == View is not exists into the MyView/SharedView  Menu list on '"
	 * +ViewName.split("-")[0].toUpperCase()
	 * +"' screen, please refer screen shot >> ViewNmae_Fail"
	 * +fnsScreenShot_Date_format());
	 * }else{
	 * fnsApps_Report_Logs("PASSED == View Name ( "
	 * +ViewName+" ) is not exists into the MyView/SharedView Menu list on '"
	 * +ViewName.split("-")[0].toUpperCase()+"' screen.");
	 * 
	 * fnsGet_Element_Enabled("ActiveView_Header_Label_Link");
	 * for(int i=0; i<=5; i++){
	 * try{
	 * fnsGet_OR_New_NSFOnline_ObjectX("ActiveView_Header_Label_Link").click();
	 * System.out.println("Click done on ActiveView_Header_Label_Link");
	 * break;
	 * }catch(Throwable t){
	 * Thread.sleep(1000);
	 * if(i==5){
	 * if(Shared_MyView_Link_Click_Counter==2){
	 * fnsGet_OR_New_NSFOnline_ObjectX("ActiveView_MyView_FirstView_from_List").
	 * click();
	 * }else if (Shared_MyView_Link_Click_Counter==1){
	 * fnsGet_OR_New_NSFOnline_ObjectX("ActiveView_SharedView_FirstView_from_List").
	 * click();
	 * }
	 * 
	 * }
	 * }
	 * }
	 * fnsLoading_Progressing_wait(2);
	 * 
	 * Return_View_Exists_in_which_List = "View Not Exists";
	 * }
	 * }
	 * 
	 * }
	 * }
	 * 
	 * return Return_View_Exists_in_which_List;
	 * 
	 * }catch(NoSuchWindowException W){
	 * //isTestCasePass = false;
	 * throw new Exception(W.getMessage()); }
	 * catch(Throwable t) {
	 * //isTestCasePass = false;
	 * fnsTake_Screen_Shot("ViewNmae_Fail");
	 * throw new
	 * Exception("View Name ( "+ViewName+" ) : "+Throwables.getStackTraceAsString(t)
	 * );
	 * }
	 * 
	 * }
	 * 
	 */
	// Verify View exists into the view list and conditional click on it.
	public void fncMyView_SharedView_NewlyCreatedView_Click_from_PopUp(String ViewName) throws Throwable {
		try {
			boolean View_Found_into_Menu_DD = false;
			boolean View_List_Paginaton_NEXT_Link = false;
			boolean List_Loaded = false;
			fnsGet_Element_Enabled("PopUp_ActiveView_DD");
			fnsLoading_Progressing_wait(2);
			fnsWait_and_Click("PopUp_ActiveView_DD");
			// Thread.sleep(4000);

			List<WebElement> DDobjectlists = null;

			// Start counter with one if want to clicking on shared menu list
			for (int Shared_My_View_Link_Click_Counter = 2; Shared_My_View_Link_Click_Counter <= 2; Shared_My_View_Link_Click_Counter++) {

				if (!(View_Found_into_Menu_DD)) {
					if (List_Loaded == false) {
						if (Shared_My_View_Link_Click_Counter == 1) {
							fnsGet_Element_Enabled("PopUp_ActiveView_ShareView_Link");
							fnsWait_and_Click("PopUp_ActiveView_ShareView_Link");
							// Thread.sleep(500);
							fnsGet_Element_Enabled("PopUp_ActiveView_SharedView_DD_Data_Div");
							DDobjectlists = fnsGet_OR_New_NSFOnline_ObjectX("PopUp_ActiveView_SharedView_DD_Data_Div")
									.findElements(By.tagName("div"));
						} else {
							fnsGet_Element_Enabled("PopUp_ActiveView_MyView_Link");
							fnsWait_and_Click("PopUp_ActiveView_MyView_Link");
							Thread.sleep(2000);
							fnsGet_Element_Enabled("PopUp_ActiveView_MyView_DD_Data_Div");

							for (int i = 0; i <= NewNsfOnline_Element_Max_Wait_Time; i++) {
								List_Loaded = false;
								DDobjectlists = fnsGet_OR_New_NSFOnline_ObjectX("PopUp_ActiveView_MyView_DD_Data_Div")
										.findElements(By.tagName("div"));
								for (WebElement Menu_Element : DDobjectlists) {
									String Menu_Element_Text = Menu_Element.getText().trim();
									if (Menu_Element_Text.length() > 5) {
										List_Loaded = true;
									} else {
										break;
									}
								}

								if (List_Loaded == true) {
									break;
								} else {
									Thread.sleep(750);
								}

								if (i == NewNsfOnline_Element_Max_Wait_Time && List_Loaded == false) {
									throw new Exception("FAILED == PopUp View Menu is not getting load, after <"
											+ NewNsfOnline_Element_Max_Wait_Time
											+ "> seconds wait,  please refer screen shot >> ViewNmae_Fail"
											+ fnsScreenShot_Date_format());
								}
							}
						}
					}

					for (WebElement dd_value : DDobjectlists) {
						if (dd_value.getText().contains(ViewName)) {

							Actions action = new Actions(driver);
							action.moveToElement(dd_value).click(dd_value).build().perform();

							fnsLoading_Progressing_wait(2);

							fnsApps_Report_Logs("PASSED == Click done on View Name ( " + ViewName
									+ " ) from MyView/SharedView Menu list on '" + ViewName.split("-")[0].toUpperCase()
									+ "' Popup.");
							View_Found_into_Menu_DD = true;
							break;
						}
					}
					if (View_Found_into_Menu_DD == false) {
						for (int Displaytry = 1; Displaytry <= 5; Displaytry++) {
							try {
								if (fnsGet_OR_New_NSFOnline_ObjectX("ActiveView_Pagination_Next_link").isDisplayed()) {
									fnsGet_OR_New_NSFOnline_ObjectX("ActiveView_Pagination_Next_link").click();
									fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
									Shared_My_View_Link_Click_Counter = 1;
									View_List_Paginaton_NEXT_Link = true;
									break;
								} else {
									View_List_Paginaton_NEXT_Link = false;
								}
							} catch (Throwable t) {
								if (Displaytry == 5) {
									View_List_Paginaton_NEXT_Link = false;
									throw new Exception(Throwables.getStackTraceAsString(t));
								} else {
									Thread.sleep(500);
								}
							}
						}
					}
					if (View_Found_into_Menu_DD == false && Shared_My_View_Link_Click_Counter != 1
							&& View_List_Paginaton_NEXT_Link == false) {
						throw new Exception("FAILED == View is not exists into the MyView/SharedView Menu list on '"
								+ ViewName.split("-")[0].toUpperCase()
								+ "' Popup, please refer screen shot >> ViewNmae_Click_Fail"
								+ fnsScreenShot_Date_format());
					}
				}
			}
		} catch (NoSuchWindowException W) {
			// isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			// isTestCasePass = false;
			fnsTake_Screen_Shot("ViewNmae_Click_Fail");
			throw new Exception("View Name ( " + ViewName + " ) : " + Throwables.getStackTraceAsString(t));
		}

	}

	public void fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(boolean Type_true_False,
			String Field_Type_like_input_or_TextArea, String LabelName, String Value) throws Throwable {
		try {
			String field_Type = "";
			String Input_Field_Xpath = "";
			if (Field_Type_like_input_or_TextArea.contains("_")) {
				field_Type = Field_Type_like_input_or_TextArea.split("_")[0].trim();
				Field_Type_like_input_or_TextArea = Field_Type_like_input_or_TextArea.split("_")[1].trim();
				Input_Field_Xpath = "//" + field_Type + "[text()='" + LabelName + "']/following::"
						+ Field_Type_like_input_or_TextArea.toLowerCase() + "[1]";
			} else {
				Input_Field_Xpath = "//label[text()='" + LabelName + "']/following::"
						+ Field_Type_like_input_or_TextArea.toLowerCase() + "[1]";
			}

			for (int i = 0; i <= NewNsfOnline_Element_Max_Wait_Time; i++) {
				try {
					if (driver.findElements(By.xpath(Input_Field_Xpath)).size() > 0) {
						break;
					} else {
						Thread.sleep(1000);
					}
				} catch (Throwable t) {
					if (i == NewNsfOnline_Element_Max_Wait_Time) {
						fnsTake_Screen_Shot("Field_Not_Coming");
						throw new Exception("FAILED '" + LabelName
								+ "' field is not coming, please refer the screen shot >> Field_Not_Coming"
								+ fnsScreenShot_Date_format());
					} else {
						Thread.sleep(1000);
					}
				}
			}
			TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(Input_Field_Xpath);

			if (Type_true_False == true) {
				TestSuiteBase_MonitorPlan.WithOut_OR_fnClear(Input_Field_Xpath);
				TestSuiteBase_MonitorPlan.WithOut_OR_fnClear(Input_Field_Xpath);
				TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(Input_Field_Xpath).sendKeys(Value);
				TestSuiteBase_MonitorPlan
						.WithOut_OR_fnMove_To_Element_and_Click("//span[contains(@class, 'footer_username')]");
				Thread.sleep(500);
			}

			String Input_Entered_Value = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(Input_Field_Xpath)
					.getAttribute("value").trim();

			if (Value.equalsIgnoreCase("")) {
				try {
					assert Input_Entered_Value.length() > 0 : "FAILED == Data is not displayed into the '" + LabelName
							+ "' field,  please refer the screen shot >> Empty_Field" + fnsScreenShot_Date_format();
					fnsApps_Report_Logs("PASSED == Data is displayed into the '" + LabelName + "' field.  Automation");
				} catch (Throwable t) {
					fnsTake_Screen_Shot("Empty_Field");
					throw new Exception(Throwables.getStackTraceAsString(t));
				}
			} else {
				try {
					assert Input_Entered_Value.equalsIgnoreCase(Value) : "FAILED == '" + LabelName + "' field value <"
							+ Input_Entered_Value + "> is NOT matched with expected value <" + Value
							+ ">,  please refer the screen shot >> Expected_Value_Not_Match"
							+ fnsScreenShot_Date_format();
					fnsApps_Report_Logs("PASSED == '" + LabelName + "' field value <" + Input_Entered_Value
							+ "> is matched with expected value <" + Value + ">.  Automation");
				} catch (Throwable t) {
					fnsTake_Screen_Shot("Expected_Value_Not_Match");
					throw new Exception(Throwables.getStackTraceAsString(t));
				}
			}
		} catch (NoSuchWindowException W) {
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}

	public void fnsVerify_FieldValue_Text_by_OR(String ValueFetch_Text_or_ValueAttribute, String LabelName,
			String Xpath, String Value) throws Throwable {
		try {
			String Input_Entered_Value = "";
			for (int i = 0; i <= NewNsfOnline_Element_Max_Wait_Time; i++) {
				try {
					if (driver.findElements(By.xpath(Xpath)).size() > 0) {
						break;
					} else {
						Thread.sleep(1000);
					}
				} catch (Throwable t) {
					if (i == NewNsfOnline_Element_Max_Wait_Time) {
						fnsTake_Screen_Shot("Field_Not_Coming");
						throw new Exception("FAILED '" + LabelName
								+ "' field is not coming, please refer the screen shot >> Field_Not_Coming"
								+ fnsScreenShot_Date_format());
					} else {
						Thread.sleep(1000);
					}
				}
			}
			TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(Xpath);

			if (ValueFetch_Text_or_ValueAttribute.equalsIgnoreCase("Value")) {
				Input_Entered_Value = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(Xpath).getAttribute("value")
						.trim();
			} else {
				Input_Entered_Value = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(Xpath).getText().trim();
			}
			try {
				assert Input_Entered_Value.equalsIgnoreCase(Value) : "FAILED == '" + LabelName + "' field value <"
						+ Input_Entered_Value + "> is NOT matched with expected value <" + Value
						+ ">,  please refer the screen shot >> Expected_Value_Not_Match" + fnsScreenShot_Date_format();
				fnsApps_Report_Logs("PASSED == '" + LabelName + "' field value <" + Input_Entered_Value
						+ "> is matched with expected value <" + Value + ">.  Automation");
			} catch (Throwable t) {
				fnsTake_Screen_Shot("Expected_Value_Not_Match");
				throw new Exception(Throwables.getStackTraceAsString(t));
			}
		} catch (NoSuchWindowException W) {
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}

	public void fnsWait_and_Click_on_RadioButton_by_LabelName(boolean Will_Select_Radio_bttn, String LabelName,
			String Radio_bttn_Seq) throws Throwable {
		try {
			String Radio_bttn_Xpath = "//label[text()='" + LabelName + "']/following::input[" + Radio_bttn_Seq + "]";
			for (int i = 0; i <= NewNsfOnline_Element_Max_Wait_Time; i++) {
				try {
					if (driver.findElements(By.xpath(Radio_bttn_Xpath)).size() > 0) {
						break;
					} else {
						Thread.sleep(1000);
					}
				} catch (Throwable t) {
					if (i == NewNsfOnline_Element_Max_Wait_Time) {
						fnsTake_Screen_Shot("RadioBttn_Not_Coming");
						throw new Exception("FAILED " + LabelName
								+ "'s RADIO button is not coming, please refer the screen shot >> RadioBttn_Not_Coming"
								+ fnsScreenShot_Date_format());
					} else {
						Thread.sleep(1000);
					}
				}
			}
			TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(Radio_bttn_Xpath);

			if (Will_Select_Radio_bttn) {
				TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(Radio_bttn_Xpath);
				fnsLoading_Progressing_on_Popup_wait_for_Popup(1);
			} else {
				try {
					String Radio_bttn_SelectAttribute = TestSuiteBase_MonitorPlan
							.WithOut_OR_fnGet_ObjectX(Radio_bttn_Xpath).getAttribute("style");
					assert (!(Radio_bttn_SelectAttribute == null)) : LabelName
							+ "'s RADIO button is not coming as SELECTED, please refer the screen shot >> Radio_bttn_NOT_Selected"
							+ fnsScreenShot_Date_format();
					fnsApps_Report_Logs("PASSED == successfully verified that " + LabelName
							+ "'s RADIO button is oming as SELECTED.  Automation");
				} catch (Throwable t) {
					fnsTake_Screen_Shot("Radio_bttn_NOT_Selected");
					throw new Exception(Throwables.getStackTraceAsString(t));
				}
			}
		} catch (NoSuchWindowException W) {
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			throw new Exception(Throwables.getStackTraceAsString(t));
		}

	}

	public void fns_Assert_equalsIgnoreCase_Without_OR(String BY__Text_or_Value, String Field_Name,
			String Xpath_Without_OR, String Value) throws Throwable {
		try {
			String Field_Value = "";
			for (int i = 0; i <= NewNsfOnline_Element_Max_Wait_Time; i++) {
				try {
					if (driver.findElements(By.xpath(Xpath_Without_OR)).size() > 0) {
						break;
					} else {
						Thread.sleep(1000);
					}
				} catch (Throwable t) {
					if (i == NewNsfOnline_Element_Max_Wait_Time) {
						fnsTake_Screen_Shot("Field_Not_Coming");
						throw new Exception("FAILED '" + Field_Name
								+ "' field is not coming, please refer the screen shot >> Field_Not_Coming"
								+ fnsScreenShot_Date_format());
					} else {
						Thread.sleep(1000);
					}
				}
			}
			TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(Xpath_Without_OR);
			WebElement Field_Element = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(Xpath_Without_OR);

			if (BY__Text_or_Value.equalsIgnoreCase("Value")) {
				Field_Value = Field_Element.getAttribute("value").trim();
			} else {
				Field_Value = Field_Element.getText().trim();
			}
			try {
				assert Field_Value.equalsIgnoreCase(Value) : "FAILED == '" + Field_Name + "' field value <"
						+ Field_Value + "> is NOT matched with expected value <" + Value
						+ ">,  please refer the screen shot >> Expected_Value_Not_Match" + fnsScreenShot_Date_format();
				fnsApps_Report_Logs("PASSED == '" + Field_Name + "' field value <" + Field_Value
						+ "> is matched with expected value <" + Value + ">.");
			} catch (Throwable t) {
				fnsTake_Screen_Shot("Expected_Value_Not_Match");
				throw new Exception(Throwables.getStackTraceAsString(t));
			}
		} catch (NoSuchWindowException W) {
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}

	public void fnsCalendar_Pick_TodayDate_by_LabelName(boolean Verify_TodayDate_Defaulted_into_the_Calendar,
			boolean Will_SelectDate, String LabelName, Integer Date) throws Throwable {
		try {
			String Calendar_DatePick_Format_Date = fns_Return_UK_London_Time("EEEE, MMMM dd, yyyy", 0, 0, Date, 0, 0);
			String Calendar_Button_Xpath = "//label[text()='" + LabelName
					+ "']/following::span[@class='k-icon k-i-calendar'][1]";
			// String Calendar_Day_Xpath =
			// "//label[text()='"+LabelName+"']/following::td[contains(@class,
			// 'k-today')]/a";
			String Calendar_Day_Xpath = "//label[text()='" + LabelName + "']/following::td/a[@title='"
					+ Calendar_DatePick_Format_Date + "']";
			String Calendar_Footer_xpath = "//div[@class='k-footer']/a[@title='" + Calendar_DatePick_Format_Date + "']";
			String Value = fns_Return_UK_London_Time("dd-MM-yyyy", 0, 0, Date, 0, 0);
			for (int i = 0; i <= NewNsfOnline_Element_Max_Wait_Time; i++) {
				try {
					if (driver.findElements(By.xpath(Calendar_Button_Xpath)).size() > 0) {
						break;
					} else {
						Thread.sleep(1000);
					}
				} catch (Throwable t) {
					if (i == NewNsfOnline_Element_Max_Wait_Time) {
						fnsTake_Screen_Shot("Field_Not_Coming");
						throw new Exception("FAILED == Calendar button is not coming for '" + LabelName
								+ "' field, please refer the screen shot >> Field_Not_Coming"
								+ fnsScreenShot_Date_format());
					} else {
						Thread.sleep(1000);
					}
				}
			}
			TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(Calendar_Button_Xpath);
			TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element_and_Click(Calendar_Button_Xpath);
			Thread.sleep(500);
			if (Verify_TodayDate_Defaulted_into_the_Calendar) {
				Calendar_Footer_xpath = fnsRetun_Xpath_of_First_Visible_Element_if_More_than_One_Coming(
						Calendar_Footer_xpath);

				String Calendar_footer_Text = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(Calendar_Footer_xpath)
						.getText().trim();
				try {
					assert Calendar_footer_Text.equalsIgnoreCase(Calendar_DatePick_Format_Date)
							: "FAILED == Today date is not displayed as highlighted into the calendar,  please refer the screen shot >> TodayDate_Not_Highlighted"
									+ fnsScreenShot_Date_format();
					fnsApps_Report_Logs(
							"PASSED == Today date is displayed as highlighted into the calendar.  Automation");
				} catch (Throwable t) {
					fnsTake_Screen_Shot("TodayDate_Not_Highlighted");
					throw new Exception(Throwables.getStackTraceAsString(t));
				}
			}

			if (Will_SelectDate) {
				fnsWait_Click_on_First_Visible_Element_if_More_than_One_Coming(Value, Calendar_Day_Xpath);
				Thread.sleep(500);
				TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element_and_Click("//label[text()='" + LabelName + "']");
				Thread.sleep(500);

				String Input_Field_Xpath = "//label[text()='" + LabelName + "']/following::input[1]";
				String Input_Entered_Value = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(Input_Field_Xpath)
						.getAttribute("value").trim();
				try {
					assert Input_Entered_Value.equalsIgnoreCase(Value) : "FAILED == '" + LabelName + "' field value <"
							+ Input_Entered_Value + "> is NOT matched with expected value <" + Value
							+ ">,  please refer the screen shot >> Expected_Value_Not_Match"
							+ fnsScreenShot_Date_format();
					fnsApps_Report_Logs("PASSED == '" + LabelName + "' field value <" + Input_Entered_Value
							+ "> is matched with expected value <" + Value + ">.  Automation");
				} catch (Throwable t) {
					fnsTake_Screen_Shot("Expected_Value_Not_Match");
					throw new Exception(Throwables.getStackTraceAsString(t));
				}
			} else {
				TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element_and_Click("//label[text()='" + LabelName + "']");
			}

		} catch (NoSuchWindowException W) {
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}

	public void fnsCalendar_Pick_TodayDate_by_LabelName_1(boolean Verify_TodayDate_Defaulted_into_the_Calendar,
			boolean Will_SelectDate, String LabelName, Integer Date) throws Throwable {
		try {
			String Calendar_DatePick_Format_Date = fns_Return_UK_London_Time("EEEE, MMMM dd, yyyy", 0, 0, Date, 0, 0);
			String Calendar_Button_Xpath = "//label[text()='" + LabelName
					+ "']/following::span[@class='k-icon k-i-calendar'][1]";
			String Calendar_Day_Xpath = "//label[text()='" + LabelName + "']/following::td/a[@title='"
					+ Calendar_DatePick_Format_Date + "']";
			String Calendar_Footer_xpath = "//div[@class='k-footer']/a[@title='" + Calendar_DatePick_Format_Date + "']";
			String Value = fns_Return_UK_London_Time("dd-MMM-yyyy", 0, 0, Date, 0, 0);
			for (int i = 0; i <= NewNsfOnline_Element_Max_Wait_Time; i++) {
				try {
					if (driver.findElements(By.xpath(Calendar_Button_Xpath)).size() > 0) {
						break;
					} else {
						Thread.sleep(1000);
					}
				} catch (Throwable t) {
					if (i == NewNsfOnline_Element_Max_Wait_Time) {
						fnsTake_Screen_Shot("Field_Not_Coming");
						throw new Exception("FAILED == Calendar button is not coming for '" + LabelName
								+ "' field, please refer the screen shot >> Field_Not_Coming"
								+ fnsScreenShot_Date_format());
					} else {
						Thread.sleep(1000);
					}
				}
			}
			TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(Calendar_Button_Xpath);
			TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element_and_Click(Calendar_Button_Xpath);
			Thread.sleep(500);
			if (Verify_TodayDate_Defaulted_into_the_Calendar) {
				Calendar_Footer_xpath = fnsRetun_Xpath_of_First_Visible_Element_if_More_than_One_Coming(
						Calendar_Footer_xpath);

				String Calendar_footer_Text = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(Calendar_Footer_xpath)
						.getText().trim();
				try {
					assert Calendar_footer_Text.equalsIgnoreCase(Calendar_DatePick_Format_Date)
							: "FAILED == Today date is not displayed as highlighted into the calendar,  please refer the screen shot >> TodayDate_Not_Highlighted"
									+ fnsScreenShot_Date_format();
					fnsApps_Report_Logs(
							"PASSED == Today date is displayed as highlighted into the calendar.  Automation");
				} catch (Throwable t) {
					fnsTake_Screen_Shot("TodayDate_Not_Highlighted");
					throw new Exception(Throwables.getStackTraceAsString(t));
				}
			}

			if (Will_SelectDate) {
				fnsWait_Click_on_First_Visible_Element_if_More_than_One_Coming(Value, Calendar_Day_Xpath);
				Thread.sleep(500);
				TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element_and_Click("//label[text()='" + LabelName + "']");
				Thread.sleep(500);

				String Input_Field_Xpath = "//label[text()='" + LabelName + "']/following::input[1]";
				String Input_Entered_Value = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(Input_Field_Xpath)
						.getAttribute("value").trim();
				try {
					assert Input_Entered_Value.equalsIgnoreCase(Value) : "FAILED == '" + LabelName + "' field value <"
							+ Input_Entered_Value + "> is NOT matched with expected value <" + Value
							+ ">,  please refer the screen shot >> Expected_Value_Not_Match"
							+ fnsScreenShot_Date_format();
					fnsApps_Report_Logs("PASSED == '" + LabelName + "' field value <" + Input_Entered_Value
							+ "> is matched with expected value <" + Value + ">.  Automation");
				} catch (Throwable t) {
					fnsTake_Screen_Shot("Expected_Value_Not_Match");
					throw new Exception(Throwables.getStackTraceAsString(t));
				}
			} else {
				TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element_and_Click("//label[text()='" + LabelName + "']");
			}

		} catch (NoSuchWindowException W) {
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}

	// Verify View exists into the view list and conditional click on it.
	public void fncVerify_View_Deleted_Successfully(String ViewName) throws Throwable {
		try {
			boolean View_Deleted_Successfully = true;
			fnsGet_Element_Enabled("ActiveView_DD");
			for (int i = 0; i < 5; i++) {
				if (driver.findElement(By.xpath(OR_New_NSFOnline.getProperty("ActiveView_ShareView_Link")))
						.isDisplayed()) {
					break;
				} else {
					TestSuiteBase_MonitorPlan
							.WithOut_OR_fnMove_To_Element_and_Click(OR_New_NSFOnline.getProperty("ActiveView_DD"));
					Thread.sleep(1000);
				}
			}

			Thread.sleep(500);
			List<WebElement> Menu_DDobjectlists = null;

			// Start counter with one if want to clicking on shared menu list
			for (int Shared_My_View_Link_Click_Counter = 2; Shared_My_View_Link_Click_Counter <= 2; Shared_My_View_Link_Click_Counter++) {

				if (Shared_My_View_Link_Click_Counter == 1) {
					fnsGet_Element_Enabled("ActiveView_ShareView_Link");
					fnsWait_and_Click("ActiveView_ShareView_Link");
					// Thread.sleep(500);
					fnsGet_Element_Enabled("ActiveView_SharedView_DD_Data_Div");
					Menu_DDobjectlists = fnsGet_OR_New_NSFOnline_ObjectX("ActiveView_SharedView_DD_Data_Div")
							.findElements(By.tagName("div"));
				} else {
					fnsGet_Element_Enabled("ActiveView_MyView_Link");
					fnsWait_and_Click("ActiveView_MyView_Link");
					Thread.sleep(500);
					fnsGet_Element_Enabled("ActiveView_MyView_DD_Data_Div");
					Menu_DDobjectlists = fnsGet_OR_New_NSFOnline_ObjectX("ActiveView_MyView_DD_Data_Div")
							.findElements(By.tagName("div"));
				}

				for (WebElement Menu_Element : Menu_DDobjectlists) {
					if (Menu_Element.getText().contains(ViewName)) {
						View_Deleted_Successfully = false;
						throw new Exception(
								"FAILED ==  After deleting View, It is still exists into the MyView/SharedView  Menu list on '"
										+ ViewName.split("-")[0].toUpperCase()
										+ "' screen, please refer screen shot >> View_Delete_Fail"
										+ fnsScreenShot_Date_format());
					}
				}
			}

			if (View_Deleted_Successfully) {
				fnsGet_Element_Enabled("ActiveView_Header_Label_Link");
				fnsWait_and_Click_Through_JS(OR_New_NSFOnline.getProperty("ActiveView_Header_Label_Link"));
				/*
				 * for(int i=0; i<=5; i++){
				 * try{
				 * fnsGet_OR_New_NSFOnline_ObjectX("ActiveView_Header_Label_Link").click();
				 * break;
				 * }catch(Throwable t){
				 * Thread.sleep(1000);
				 * if(i==5){
				 * fnsGet_OR_New_NSFOnline_ObjectX("ActiveView_MyView_FirstView_from_List").
				 * click();
				 * }
				 * }
				 * }
				 */
				fnsLoading_Progressing_wait(1);
				fnsApps_Report_Logs("PASSED == View Name ( " + ViewName
						+ " ) is deleted successfully form MyView/SharedView Menu list on '"
						+ ViewName.split("-")[0].toUpperCase()
						+ "' screen [means It is not any more exists into the MyView/SharedView list].");
			}

		} catch (NoSuchWindowException W) {
			// isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			// isTestCasePass = false;
			fnsTake_Screen_Shot("View_Delete_Fail");
			throw new Exception("View Name ( " + ViewName + " ) : " + Throwables.getStackTraceAsString(t));
		}

	}

	// function to select drop down value
	public void fnsDD_value_Select_By_MatchingText_WithoutClick(String ddListXpathKey, String TagName, String Value)
			throws Exception {
		boolean ValueNotMatched = true;
		try {
			fnsGet_Element_Enabled(ddListXpathKey);
			List<WebElement> DDobjectlists = fnsGet_OR_New_NSFOnline_ObjectX(ddListXpathKey)
					.findElements(By.tagName(TagName));
			for (WebElement dd_value : DDobjectlists) {
				if (dd_value.getText().equalsIgnoreCase(Value)) {
					ValueNotMatched = false;
					Actions act = new Actions(driver);
					act.moveToElement(dd_value).click().build().perform();
					// dd_value.click();
					break;
				}
			}
			if (ValueNotMatched == true) {
				throw new Exception("Excel value is not matched with DropDown Value.");
			}
			fnsApps_Report_Logs("PASSED == select value [ " + Value + " ] from drop down is done, having xpath >>  "
					+ ddListXpathKey);
		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot("DdValueSelectFail");
			fnsApps_Report_Logs("FAILED == Unable to select value from drop down [ " + ddListXpathKey
					+ " ],plz see screenshot [ DdValueSelectFail" + fnsScreenShot_Date_format() + " ]"
					+ ". Getting Exception  >> " + Throwables.getStackTraceAsString(t).trim());
			throw new Exception("FAILED == Unable to select value from drop down [ " + ddListXpathKey
					+ " ],plz see screenshot [ DdValueSelectFail" + fnsScreenShot_Date_format() + " ]"
					+ ". Getting Exception  >> " + Throwables.getStackTraceAsString(t).trim());
		}
	}

	// Verify View exists into the view list then open and Delete it. Verify Deleted
	// successfully
	public String fncVerify_View_Display_Open_and_Delete_it(Integer Share_is_1_and_MyView_is_2, String ViewName,
			String Delete_Bttn_Xpath, String Remove_Bttn_Xpath) throws Throwable {
		try {
			String View_Exsits = fncMyView_SharedView_NewlyCreatedView_Display_and_Conditional_Click(
					Share_is_1_and_MyView_is_2, false, ViewName, "Yes");

			if (View_Exsits.equalsIgnoreCase("my view")) {
				fnsGet_Element_Enabled(Delete_Bttn_Xpath);
				fnsWait_and_Click(Delete_Bttn_Xpath);
			}

			if (View_Exsits.equalsIgnoreCase("shared view")) {
				fnsGet_Element_Enabled(Remove_Bttn_Xpath);
				fnsWait_and_Click(Remove_Bttn_Xpath);
			}

			if (!(View_Exsits.equalsIgnoreCase("View Not Exists"))) {
				boolean View_Simple_Delete_Popup = false;
				boolean View_SelectDefault_Delete_Popup = false;

				for (int i = 0; i <= 60; i++) {
					try {
						if (driver
								.findElements(By.xpath(
										OR_New_NSFOnline.getProperty("Model_Popup_DefaultViewSelect_Delete_Bttn")))
								.size() > 0) {
							View_SelectDefault_Delete_Popup = true;
							break;
						} else if (driver.findElement(By.xpath(OR_New_NSFOnline.getProperty("Model_Popup_Delete_Bttn")))
								.isDisplayed()) {
							System.out.println(driver
									.findElements(By.xpath(OR_New_NSFOnline.getProperty("Model_Popup_Delete_Bttn")))
									.size());
							View_Simple_Delete_Popup = true;
							break;
						} else {
							Thread.sleep(2000);
						}
					} catch (Throwable t) {
						if (i == 60) {
							fnsTake_Screen_Shot("DeleteView_Popup_not_Coming");
							throw new Exception(
									"FAILED == 'Delete View' Popup is not coming to delete view, after 120 seconds wait, please refer the screen shot >> DeleteView_Popup_not_Coming"
											+ fnsScreenShot_Date_format());
						} else {
							fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
						}
					}
				}

				if (View_Simple_Delete_Popup) {
					Thread.sleep(2000);
					boolean Click_and_Message_Verify = false;
					for (int c = 1; c <= 10; c++) {
						try {
							if (fnsGet_OR_New_NSFOnline_ObjectX("Model_Popup_Delete_Bttn").isDisplayed()) {
								fnsGet_Element_Displayed("Model_Popup_Delete_Bttn");
								fnsWait_and_Click("Model_Popup_Delete_Bttn");
							}
							fns_Verify_Success_message_coming_OR_Error_message_Coming(false, 60, "Deleted Successfully",
									15);
							Click_and_Message_Verify = true;
							break;
						} catch (Throwable t) {
							if (c == 10) {
								fns_Verify_Success_message_coming_OR_Error_message_Coming(true, 2,
										"Deleted Successfully", 15);
							} else {
								Thread.sleep(1000);
							}
						}
					}
					if ((Click_and_Message_Verify == false)) {
						fnsTake_Screen_Shot("Delete_Not_Working");
						throw new Exception(
								"FAILED == Deleteing of view functionality is not working after 10 attacmpts, please refer the screen shot >> Delete_Not_Working"
										+ fnsScreenShot_Date_format());
					}
				} else if (View_SelectDefault_Delete_Popup) {
					fnsLoading_Progressing_on_Popup_wait_for_Popup(1);
					String SharedView_FirstView_Text = TestSuiteBase_MonitorPlan
							.WithOut_OR_fnGet_ObjectX(
									OR_New_NSFOnline.getProperty("Model_Popup_DefaultViewSelect_SharedView_First_Row"))
							.getAttribute("title").toLowerCase().trim();
					if (!(SharedView_FirstView_Text.contains(ViewName.toLowerCase()))
							&& !(SharedView_FirstView_Text.equals(""))) {
						fnsWait_and_Click_Through_JS(
								OR_New_NSFOnline.getProperty("Model_Popup_DefaultViewSelect_SharedView_First_Row"));
					} else {
						fnsWait_and_Click_Through_JS(
								OR_New_NSFOnline.getProperty("Model_Popup_DefaultViewSelect_MyView_Header"));
						Thread.sleep(2000);
						String MyView_FirstView_Text = TestSuiteBase_MonitorPlan
								.WithOut_OR_fnGet_ObjectX(
										OR_New_NSFOnline.getProperty("Model_Popup_DefaultViewSelect_MyView_First_Row"))
								.getAttribute("title").toLowerCase().trim();
						if (!(MyView_FirstView_Text.contains(ViewName.toLowerCase()))
								&& !(MyView_FirstView_Text.equals(""))) {
							fnsWait_and_Click_Through_JS(
									OR_New_NSFOnline.getProperty("Model_Popup_DefaultViewSelect_MyView_First_Row"));
						} else {
							fnsTake_Screen_Shot("View_Not_Exists_To_Make_Default");
							throw new Exception(
									"FAILED == there are no view exists to make default view, please refer the screen shot >> View_Not_Exists_To_Make_Default"
											+ fnsScreenShot_Date_format());
						}
					}
					fnsGet_Element_Enabled("Model_Popup_DefaultViewSelect_Delete_Bttn");
					fnsWait_and_Click("Model_Popup_DefaultViewSelect_Delete_Bttn");
					fns_Verify_Success_message_coming_OR_Error_message_Coming(true, 60, "Deleted Successfully", 15);
				}

				/*
				 * fnsGet_Element_Enabled("Model_Popup_OK_Bttn");
				 * fnsLoading_Progressing_on_Popup_wait_for_Popup(1);
				 * fnsWait_and_Click("Model_Popup_OK_Bttn");
				 * fnsLoading_Progressing_wait(2);
				 * fnsLoading_Progressing_wait(2);
				 * fnsLoading_Progressing_wait(2);
				 */

				fncVerify_View_Deleted_Successfully(ViewName);
			}

			return View_Exsits;
		} catch (NoSuchWindowException W) {
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}

	// Verify Column value exists into the "Selected Field" DD if it's not exists
	// then move it into the DD from "Search Fields" DD.
	public void fnsCreateView_Step3_Verify_ColumnExists_into_DD_ifNot_Then_Select(String ColumnName_NoCase)
			throws Throwable {
		boolean SelectedFields_DD_ColumnValue_Not_Matched = true;
		boolean SearchFields_DD_ColumnValue_Not_Matched = true;
		try {
			fnsGet_Element_Enabled("CreateView_Step3_SelectedFields_Multi_DD");
			TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element(
					OR_New_NSFOnline.getProperty("CreateView_Step3_SelectedFields_Multi_DD"));
			List<WebElement> DDobjectlists1 = fnsGet_OR_New_NSFOnline_ObjectX(
					"CreateView_Step3_SelectedFields_Multi_DD").findElements(By.tagName("div"));
			for (WebElement dd_value : DDobjectlists1) {
				if ((dd_value.getText().toLowerCase().trim()).equals(ColumnName_NoCase.toLowerCase().trim())) {
					fnsApps_Report_Logs("PASSED == Step 3 : Column < " + ColumnName_NoCase
							+ " > exists into the 'Selected Fields' multi select drop down.");
					SelectedFields_DD_ColumnValue_Not_Matched = false;
					break;
				}
			}

			if (SelectedFields_DD_ColumnValue_Not_Matched == true) {
				fnsGet_Element_Enabled("CreateView_Step3_SearchFields_Multi_DD");
				List<WebElement> DDobjectlists2 = fnsGet_OR_New_NSFOnline_ObjectX(
						"CreateView_Step3_SearchFields_Multi_DD").findElements(By.tagName("div"));
				for (WebElement dd_value : DDobjectlists2) {
					if ((dd_value.getText().toLowerCase().trim()).equals(ColumnName_NoCase.toLowerCase().trim())) {
						Thread.sleep(500);
						dd_value.click();
						Thread.sleep(250);
						fnsGet_Element_Enabled("CreateView_Step3_DD_RightSelected_bttn");
						fnsWait_and_Click("CreateView_Step3_DD_RightSelected_bttn");
						fnsApps_Report_Logs("PASSED == Step 3 : Column < " + ColumnName_NoCase
								+ " > exists into the 'Search Fields' multi select drop down and moved to 'Selected Fields' multi select drop down.");
						SearchFields_DD_ColumnValue_Not_Matched = false;
						break;
					}
				}

				if (SearchFields_DD_ColumnValue_Not_Matched == true) {
					throw new Exception("FAILED == Create View > Step 3 : Column < " + ColumnName_NoCase
							+ " > is not found." +
							" NEITHER into 'Search Fields' multi select drop down NOR into 'Selected Fields' multi select drop down, Please refer screen shot >> Step3_Column_"
							+ ColumnName_NoCase + "Fail_" + fnsScreenShot_Date_format());
				}

			}

		} catch (NoSuchWindowException W) {
			// isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			// isTestCasePass = false;
			fnsTake_Screen_Shot("Step3_Column_" + ColumnName_NoCase + "Fail_");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}

	}
	/*
	 * //Verify success/error message coming
	 * public void fns_Verify_Success_message_coming_OR_Error_message_Coming(boolean
	 * TakeScreenShot, long WaitTime, String Successs_Message_Text, Integer
	 * MessageLength) throws Throwable{
	 * try{
	 * 
	 * TimeOut = 0;
	 * ErrorMsgText = "";
	 * SuccessMsgText = "";
	 * Integer Success_Element_Count = 0;
	 * for(int wait=1; wait<=(WaitTime*2); wait++){
	 * boolean Validation_Message_Found = false;
	 * Success_Element_Count =
	 * driver.findElements(By.xpath(OR_New_NSFOnline.getProperty(
	 * "NewNsfOnline_Success_Message"))).size();
	 * System.out.println("Success_Element_Count = "+Success_Element_Count);
	 * if((Success_Element_Count>0) ){
	 * for(int Success_Ele_Count=Success_Element_Count; Success_Ele_Count>=1;
	 * Success_Ele_Count--){
	 * String Success_Ele_Xpath =
	 * "("+OR_New_NSFOnline.getProperty("NewNsfOnline_Success_Message")+")["+
	 * Success_Ele_Count+"]";
	 * 
	 * for(int i=1; i<=5; i++){
	 * try{
	 * if(driver.findElement(By.xpath(Success_Ele_Xpath)).isDisplayed()){
	 * SuccessMsgText =
	 * driver.findElement(By.xpath(Success_Ele_Xpath)).getText().trim();
	 * System.out.println("Success TEXT  = "+SuccessMsgText);
	 * }
	 * }catch(Throwable t){
	 * //nothing to do
	 * }
	 * if(SuccessMsgText.length()>MessageLength){
	 * Validation_Message_Found = true;
	 * break;
	 * }else{
	 * Thread.sleep(100);
	 * }
	 * }
	 * 
	 * if(Validation_Message_Found==true){
	 * assert
	 * (SuccessMsgText.toLowerCase().contains(Successs_Message_Text.toLowerCase()))
	 * :"FAILED == Success message is not coming, please refer screenshot >>Success_Message_Fail"
	 * + fnsScreenShot_Date_format()+"Text >> "+SuccessMsgText;
	 * fnsApps_Report_Logs("PASSED == Success ( "
	 * +Successs_Message_Text+" ) message is coming.");
	 * break;
	 * }
	 * }
	 * }
	 * 
	 * if( Validation_Message_Found==false ||
	 * (driver.findElements(By.xpath(OR_New_NSFOnline.getProperty(
	 * "NewNsfOnline_Error_Image"))).size()>0) ){
	 * Integer Error_Element_Count =
	 * driver.findElements(By.xpath(OR_New_NSFOnline.getProperty(
	 * "NewNsfOnline_Error_Message"))).size();
	 * if(Error_Element_Count>0){
	 * for(int i=Error_Element_Count; i>=1; i--){
	 * String Error_message_Xpath =
	 * "("+OR_New_NSFOnline.getProperty("NewNsfOnline_Error_Message")+")["+i+"]";
	 * try{
	 * if(driver.findElement(By.xpath(Error_message_Xpath)).isDisplayed()){
	 * ErrorMsgText =
	 * driver.findElement(By.xpath(Error_message_Xpath)).getText().trim();
	 * }
	 * }catch(Throwable t){
	 * //nothing to do
	 * }
	 * if(ErrorMsgText.length()>5){
	 * throw new Exception("FAILED == Application display Error < "
	 * +ErrorMsgText+" >, please refer screen shot >> Success_Message_Fail"
	 * +fnsScreenShot_Date_format());
	 * }
	 * }
	 * }
	 * 
	 * }
	 * 
	 * if(Validation_Message_Found==true){
	 * //nothing to do
	 * }else{
	 * Thread.sleep(250);
	 * }
	 * 
	 * 
	 * TimeOut = wait;
	 * 
	 * if(Validation_Message_Found==true){
	 * break;
	 * }
	 * }
	 * if(TimeOut.equals(WaitTime*2) ){
	 * throw new Exception("FAILED == After < "+(WaitTime)
	 * +" > seconds wait, NIETHER 'success' message display NOR any 'error' message display, please refer screenshot >>Success_Message_Fail"
	 * + fnsScreenShot_Date_format());
	 * }
	 * if(Success_Element_Count==0 && TimeOut.equals(WaitTime*2) ){
	 * throw new Exception("FAILED == Success Messge <"
	 * +Successs_Message_Text+"> is not coming, please refer screen shot >> Success_Message_Fail"
	 * +fnsScreenShot_Date_format());
	 * }
	 * if(TakeScreenShot==true){
	 * fnsLoading_Progressing_wait(3);
	 * }else{
	 * Thread.sleep(2000);
	 * }
	 * }catch(Throwable t) {
	 * if(TakeScreenShot==true){
	 * fnsTake_Screen_Shot("Success_Message_Fail");
	 * }
	 * fnsLoading_Progressing_wait(1);
	 * throw new Exception(Throwables.getStackTraceAsString(t));
	 * }
	 * 
	 * 
	 * }
	 * 
	 */

	/*
	 * //Requried change (fnsLoading_Progressing_wait(1);) into the method
	 * fncNavigation_Verify_Application_Navigated_To_ViewScreen -- NSFOII-3249
	 * //Commented on 14.2.2018 as new success message through unexpected_loading
	 * error, below loading method will not captured unknown error.
	 * //Verify success/error message coming
	 * public void fns_Verify_Success_message_coming_OR_Error_message_Coming(boolean
	 * TakeScreenShot, long WaitTime, String Successs_Message_Text, Integer
	 * MessageLength) throws Throwable{
	 * try{
	 * ErrorMsgText = "";
	 * SuccessMsgText = "";
	 * TimeOut = 0;
	 * Integer Success_Element_Count = 0;
	 * for(int wait=1; wait<=(WaitTime*2); wait++){
	 * boolean Validation_Message_Found = false;
	 * Success_Element_Count =
	 * driver.findElements(By.xpath(OR_New_NSFOnline.getProperty(
	 * "NewNsfOnline_Success_Message"))).size();
	 * System.out.println("Success_Element_Count = "+Success_Element_Count);
	 * if((Success_Element_Count>0) ){
	 * for(int Success_Ele_Count=Success_Element_Count; Success_Ele_Count>=1;
	 * Success_Ele_Count--){
	 * String Success_Ele_Xpath =
	 * "("+OR_New_NSFOnline.getProperty("NewNsfOnline_Success_Message")+")["+
	 * Success_Ele_Count+"]";
	 * for(int i=1; i<=5; i++){
	 * try{
	 * SuccessMsgText =
	 * driver.findElement(By.xpath(Success_Ele_Xpath)).getText().trim();
	 * System.out.println("Success TEXT  = "+SuccessMsgText);
	 * }catch(Throwable t){
	 * //nothing to do
	 * }
	 * if(SuccessMsgText.length()>MessageLength){
	 * Validation_Message_Found = true;
	 * break;
	 * }else{
	 * Thread.sleep(100);
	 * }
	 * }
	 * if(Validation_Message_Found==true){
	 * assert
	 * (SuccessMsgText.toLowerCase().contains(Successs_Message_Text.toLowerCase()))
	 * :"FAILED == Success message is not coming, please refer screenshot >>Success_Message_Fail"
	 * + fnsScreenShot_Date_format()+"Text >> "+SuccessMsgText;
	 * fnsApps_Report_Logs("PASSED == Success ( "
	 * +Successs_Message_Text+" ) message is coming.");
	 * break;
	 * }
	 * }
	 * }
	 * 
	 * if( Validation_Message_Found==false ||
	 * (driver.findElements(By.xpath(OR_New_NSFOnline.getProperty(
	 * "NewNsfOnline_Error_Image"))).size()>0) ){
	 * Integer Error_Element_Count =
	 * driver.findElements(By.xpath(OR_New_NSFOnline.getProperty(
	 * "NewNsfOnline_Error_Message"))).size();
	 * if(Error_Element_Count>0){
	 * for(int i=Error_Element_Count; i>=1; i--){
	 * String Error_message_Xpath =
	 * "("+OR_New_NSFOnline.getProperty("NewNsfOnline_Error_Message")+")["+i+"]";
	 * try{
	 * ErrorMsgText =
	 * driver.findElement(By.xpath(Error_message_Xpath)).getText().trim();
	 * }catch(Throwable t){
	 * //nothing to do
	 * }
	 * if(ErrorMsgText.length()>5){
	 * throw new Exception("FAILED == Application display Error < "
	 * +ErrorMsgText+" >, please refer screen shot >> Success_Message_Fail"
	 * +fnsScreenShot_Date_format());
	 * }
	 * }
	 * }
	 * 
	 * }
	 * 
	 * if(Validation_Message_Found==true){
	 * //nothing to do
	 * }else{
	 * Thread.sleep(250);
	 * }
	 * 
	 * 
	 * TimeOut = wait;
	 * if(TimeOut.equals(WaitTime*2) ){
	 * throw new Exception("FAILED == After < "+(WaitTime/2)
	 * +" > seconds wait, NIETHER 'success' message display NOR any 'error' message display, please refer screenshot >>Success_Message_Fail"
	 * + fnsScreenShot_Date_format());
	 * }
	 * if(Validation_Message_Found==true){
	 * break;
	 * }
	 * }
	 * if(Success_Element_Count==0 && TimeOut.equals(WaitTime*2) ){
	 * throw new Exception("FAILED == Success Messge <"
	 * +Successs_Message_Text+"> is not coming, please refer screen shot >> Success_Message_Fail"
	 * +fnsScreenShot_Date_format());
	 * }
	 * if(TakeScreenShot==true){
	 * //Commented on 14.2.2018 as new success message through unexpected_loading
	 * error, below loading method will not captured unknown error.
	 * fnsLoading_Progressing_wait(3);
	 * //fnsLoading_Progressing_on_Popup_wait_for_Popup(3);
	 * }else{
	 * Thread.sleep(2000);
	 * }
	 * }catch(Throwable t) {
	 * if(TakeScreenShot==true){
	 * fnsTake_Screen_Shot("Success_Message_Fail");
	 * }
	 * //Requried change (fnsLoading_Progressing_wait(1);) into the method
	 * fncNavigation_Verify_Application_Navigated_To_ViewScreen
	 * //Commented on 14.2.2018 as new success message through unexpected_loading
	 * error, below loading method will not captured unknown error.
	 * fnsLoading_Progressing_wait(1);
	 * //fnsLoading_Progressing_on_Popup_wait_for_Popup(1);
	 * throw new Exception(Throwables.getStackTraceAsString(t));
	 * }
	 * }
	 */

	// Verify success/error message coming
	public String fns_Verify_Success_message_coming_OR_Error_message_Coming(boolean TakeScreenShot, Integer MinWaitTime,
			String Successs_Message_Text, Integer MessageLength) throws Throwable {
		try {
			ErrorMsgText = "";
			SuccessMsgText = "";
			TimeOut = 0;
			Integer WaitTime = MinWaitTime * 2;
			String Success_Ele_Xpath = "";
			Integer Success_Element_Count = 0;
			boolean Validation_Message_Found = false;
			for (int wait = 1; wait <= (WaitTime); wait++) {
				Validation_Message_Found = false;
				Success_Element_Count = driver
						.findElements(By.xpath(OR_New_NSFOnline.getProperty("NewNsfOnline_Success_Message"))).size();
				System.out.println("Success_Element_Count = " + Success_Element_Count);
				if ((Success_Element_Count > 0)) {
					for (int Success_Ele_Count = Success_Element_Count; Success_Ele_Count >= 1; Success_Ele_Count--) {
						Success_Ele_Xpath = "(" + OR_New_NSFOnline.getProperty("NewNsfOnline_Success_Message") + ")["
								+ Success_Ele_Count + "]";
						for (int i = 1; i <= 5; i++) {
							try {
								SuccessMsgText = driver.findElement(By.xpath(Success_Ele_Xpath)).getText().trim();
								System.out.println("Success TEXT  = " + SuccessMsgText);
							} catch (Throwable t) {
								// nothing to do
							}
							if (SuccessMsgText.length() > MessageLength) {
								Validation_Message_Found = true;
								break;
							} else {
								Thread.sleep(100);
							}
						}
						if (Validation_Message_Found == true) {
							assert (SuccessMsgText.toLowerCase().contains(Successs_Message_Text.toLowerCase()))
									: "FAILED == Success message is not coming, please refer screenshot >>Success_Message_Fail"
											+ fnsScreenShot_Date_format() + "Text >> " + SuccessMsgText;
							fnsApps_Report_Logs(
									"PASSED == Success ( " + Successs_Message_Text + " ) message is coming.");
							break;
						}
					}
				}

				if (Validation_Message_Found == false || (driver
						.findElements(By.xpath(OR_New_NSFOnline.getProperty("NewNsfOnline_Error_Image"))).size() > 0)) {
					Integer Error_Element_Count = driver
							.findElements(By.xpath(OR_New_NSFOnline.getProperty("NewNsfOnline_Error_Message"))).size();
					if (Error_Element_Count > 0) {
						for (int i = Error_Element_Count; i >= 1; i--) {
							String Error_message_Xpath = "("
									+ OR_New_NSFOnline.getProperty("NewNsfOnline_Error_Message") + ")[" + i + "]";
							try {
								ErrorMsgText = driver.findElement(By.xpath(Error_message_Xpath)).getText().trim();
							} catch (Throwable t) {
								// nothing to do
							}
							if (ErrorMsgText.length() > 5) {
								throw new Exception("FAILED == Application display Error < " + ErrorMsgText
										+ " >, please refer screen shot >> Success_Message_Fail"
										+ fnsScreenShot_Date_format());
							}
						}
					}

				}

				if (Validation_Message_Found == true) {
					// nothing to do
				} else {
					Thread.sleep(250);
				}

				TimeOut = wait;
				if (TimeOut.equals(WaitTime)) {
					fnsApps_Report_Logs("FAILED == After < " + (MinWaitTime)
							+ " > seconds wait, NIETHER 'success' message display NOR any 'error' message display, please refer screenshot >>Success_Message_Fail"
							+ fnsScreenShot_Date_format());
					throw new Exception("FAILED == After < " + (MinWaitTime)
							+ " > seconds wait, NIETHER 'success' message display NOR any 'error' message display, please refer screenshot >>Success_Message_Fail"
							+ fnsScreenShot_Date_format());
				}
				if (Validation_Message_Found == true) {
					break;
				}
			}
			if (Success_Element_Count.equals(0) && (TimeOut.equals(WaitTime))) {
				throw new Exception("FAILED == Success Messge <" + Successs_Message_Text
						+ "> is not coming, please refer screen shot >> Success_Message_Fail"
						+ fnsScreenShot_Date_format());
			}

			for (int i = 1; i <= 60; i++) {
				try {
					if (driver.findElements(By.xpath(Success_Ele_Xpath)).size() > 0) {
						if (driver.findElement(By.xpath(Success_Ele_Xpath)).isDisplayed()) {
							Thread.sleep(1000);
						}
					} else {
						break;
					}
				} catch (Throwable t) {
					Thread.sleep(1000);
				}
				if ((i == 60) && (driver.findElements(By.xpath(Success_Ele_Xpath)).size() == 0)) {
					throw new Exception("FAILED == Success Messge <" + Successs_Message_Text
							+ "> is not getting disappear, after 60 seconds wait, please refer screen shot >> Success_Message_Fail"
							+ fnsScreenShot_Date_format());
				}
			}
			if (Validation_Message_Found == false) {
				System.out.println("FAILED == Success Messge <" + Successs_Message_Text + "> is not coming.");
				throw new Exception("FAILED == Success Messge <" + Successs_Message_Text
						+ "> is not coming, please refer screen shot >> Success_Message_Fail"
						+ fnsScreenShot_Date_format());
			}

			Thread.sleep(1000);
		} catch (Throwable t) {
			if (TakeScreenShot == true) {
				fnsTake_Screen_Shot("Success_Message_Fail");
			}
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		return SuccessMsgText;
	}

	// Verify View exists into the view list then open and Delete it. Verify Deleted
	// successfully
	public String fns_Verify_Success_or_Error_Message_on_ModelPopup_Generation(boolean TakeScreenShot, long WaitTime,
			String Success_Message, Integer Success_Message_Length) throws Throwable {
		try {
			boolean Validation_Message_Found = false;
			TimeOut = 0;
			ErrorMsgText = "";
			SuccessMsgText = "";
			for (int wait = 1; wait <= (WaitTime); wait++) {
				Validation_Message_Found = false;

				if ((driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("NewNsfOnline_Content_Model_Popup")))
						.size() > 0)) {

					Integer Success_Element_Count = driver
							.findElements(By.xpath(OR_New_NSFOnline.getProperty("NewNsfOnline_Content_Model_Popup")))
							.size();
					System.out.println("Success_Element_Count SIZE =  " + Success_Element_Count);
					if (Success_Element_Count > 0) {
						for (int i = 1; i <= Success_Element_Count; i++) {
							String Success_message_Xpath = "("
									+ OR_New_NSFOnline.getProperty("NewNsfOnline_Content_Model_Popup") + ")[" + i + "]";
							try {
								SuccessMsgText = driver.findElement(By.xpath(Success_message_Xpath)).getText().trim();
								// System.out.println(SuccessMsgText);
							} catch (Throwable t) {
								// nothing to do
							}

							if (SuccessMsgText.length() > Success_Message_Length) {
								System.out.println("Success Xpath = " + Success_message_Xpath + "     -   Text = "
										+ SuccessMsgText + "       ----- Length = " + SuccessMsgText.length());
								if (SuccessMsgText.toLowerCase().contains(Success_Message.toLowerCase().trim())) {
									assert (SuccessMsgText.toLowerCase().contains(Success_Message.toLowerCase().trim()))
											: "FAILED == Popup Validation message < " + Success_Message
													+ " > is not coming, please refer screenshot >> Success_Message_Fail"
													+ fnsScreenShot_Date_format();
									fnsApps_Report_Logs("PASSED == Popup Validation message < " + Success_Message
											+ " > is coming.");
									Validation_Message_Found = true;
									break;
								}

								if ((SuccessMsgText.toLowerCase().contains("cancel"))
										|| (SuccessMsgText.toLowerCase().contains("alert"))) {
									throw new Exception("FAILED == Validation Error <" + SuccessMsgText
											+ "> is coming instead of displaying success message, please refer screenshot >>Success_Message_Fail"
											+ fnsScreenShot_Date_format());
								}
							}
						}
					}
				} else if (driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("Model_Popup_Not_Coming_Error")))
						.size() > 0) {
					Integer Error_Element_Count = driver
							.findElements(By.xpath(OR_New_NSFOnline.getProperty("Model_Popup_Not_Coming_Error")))
							.size();
					if (Error_Element_Count > 0) {
						for (int i = 1; i <= Error_Element_Count; i++) {
							String Error_message_Xpath = "("
									+ OR_New_NSFOnline.getProperty("Model_Popup_Not_Coming_Error") + ")[" + i + "]";
							try {
								ErrorMsgText = driver.findElement(By.xpath(Error_message_Xpath)).getText().trim();
								Validation_Message_Found = true;
							} catch (Throwable t) {
								// nothing to do
							}
							if (ErrorMsgText.length() > 5) {
								throw new Exception("FAILED == Application display Error < " + ErrorMsgText
										+ " >, please refer screen shot >> Success_Message_Fail"
										+ fnsScreenShot_Date_format());
							}
						}
					}

				}

				if (Validation_Message_Found == true) {
					break;
				} else {
					Thread.sleep(1000);
				}

				TimeOut = wait;

				if (TimeOut == WaitTime) {

					throw new Exception("FAILED == After < " + (TimeOut)
							+ " > seconds wait, NIETHER message '" + Success_Message
							+ "' is display NOR any 'error' message is coming, please refer screenshot >>Success_Message_Fail"
							+ fnsScreenShot_Date_format());

				}
			}

			if (TakeScreenShot == true && Validation_Message_Found == false) {
				throw new Exception("FAILED == After < " + (TimeOut)
						+ " > seconds wait, NIETHER message '" + Success_Message
						+ "' is display NOR any 'error' message is coming, please refer screenshot >>Success_Message_Fail"
						+ fnsScreenShot_Date_format());
			}

			return SuccessMsgText;
		} catch (NoSuchWindowException W) {
			// isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			if (TakeScreenShot == true) {
				fnsTake_Screen_Shot("Success_Message_Fail");
			}
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}

	public Integer fnsReturn_View_Results_Reords_Total_Count(String Section_Name, String Table_Xpath,
			Integer No_of_Records_Display_Per_Screen) throws Throwable {
		try {
			Integer Total_no_of_Count = null;

			fnsGet_Element_Enabled(Table_Xpath);
			fnsGet_Element_Displayed(Table_Xpath);
			WebElement View_Results_Table = fnsGet_OR_New_NSFOnline_ObjectX(Table_Xpath);
			List<WebElement> View_Results_Table_Tr_Objs = View_Results_Table.findElements(By.tagName("tr"));
			Total_no_of_Count = View_Results_Table_Tr_Objs.size();

			if ((Total_no_of_Count >= No_of_Records_Display_Per_Screen)
					&& (driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("View_Pagination_Total_Records")))
							.size() > 0)) {
				Total_no_of_Count = fnsReturn_Pagination_Reords_Total_Count(Section_Name);
			} else {
				if (Total_no_of_Count == 1) {
					String View_Result_Table_Text_Fetch = View_Results_Table.getText().trim();
					// if(View_Result_Table_Text_Fetch.toLowerCase().contains("sorry, no information
					// available")){//commented on 22.6.2018 as the message changed
					if (View_Result_Table_Text_Fetch.toLowerCase().contains("no records found")) {
						Total_no_of_Count = 0;
						fnsApps_Report_Logs("PASSED == " + Section_Name
								+ " : Records are not found for applied filter. Total no of records are = "
								+ Total_no_of_Count);
						return Total_no_of_Count;
					}
				}
			}

			fnsApps_Report_Logs("PASSED == " + Section_Name + " : Total no of records are = " + Total_no_of_Count);

			return Total_no_of_Count;

		} catch (Throwable t) {
			// isTestCasePass = false;
			throw new Exception(Section_Name + " : " + Throwables.getStackTraceAsString(t));
		}

	}

	public Integer fnsReturn_Pagination_Reords_Total_Count(String Section_Name) throws Throwable {
		try {
			Integer Total_no_of_Records_Count = null;
			fnsGet_Element_Enabled("View_Pagination_Total_Records");
			String Total_no_of_Records_Count_String = fnsGet_Field_TEXT("View_Pagination_Total_Records").trim();
			Total_no_of_Records_Count_String = Total_no_of_Records_Count_String.split("of")[1].trim();
			Total_no_of_Records_Count = Integer.parseInt(Total_no_of_Records_Count_String);

			return Total_no_of_Records_Count;

		} catch (Throwable t) {
			// isTestCasePass = false;
			throw new Exception(Section_Name + " : " + Throwables.getStackTraceAsString(t));
		}

	}

	public Integer fnsFilter_ApplyFilter_and_Return_Total_Filtered_Record_Count(String FilterName,
			String Filter_DataFieldAttribute_Value_from_HTML, String Filter_Value_Enter) throws Throwable {
		try {
			// String Filter_Value_Enter = Filter_Value.toLowerCase().trim();
			Integer Filter_Record_Total_Count = null;
			boolean Filter_Exists = false;
			View_Filter_Input_Xpath = null;

			fnsGet_Element_Enabled("Sites_SearchFilter_Table_FilterRow");

			List<WebElement> All_Filter_Objects = fnsGet_OR_New_NSFOnline_ObjectX("Sites_SearchFilter_Table_FilterRow")
					.findElements(By.tagName("th"));

			for (int RowNo = 1; RowNo <= All_Filter_Objects.size(); RowNo++) {
				String View_Filter_Xpath = OR_New_NSFOnline.getProperty("Sites_SearchFilter_Table_FilterRow") + "/th["
						+ RowNo + "]/span";
				if (driver.findElements(By.xpath(View_Filter_Xpath)).size() > 0) {
					String Filter_Name = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(View_Filter_Xpath)
							.getAttribute("data-field").trim();
					if ((Filter_Name.toLowerCase())
							.equals(Filter_DataFieldAttribute_Value_from_HTML.toLowerCase().trim())) {
						// View_Filter_Input_Xpath =
						// OR_New_NSFOnline.getProperty("Sites_SearchFilter_Table_FilterRow")+"/th["+RowNo+"]/following::input[1]";
						// Not Working

						View_Filter_Input_Xpath = View_Filter_Xpath + "/span/input";
						try {
							if (driver.findElements(By.xpath(View_Filter_Input_Xpath)).size() > 0) {
								// nothing to do
							} else {
								View_Filter_Input_Xpath = View_Filter_Xpath + "/span/span/span/input";
							}
						} catch (Throwable t) {
							View_Filter_Input_Xpath = View_Filter_Xpath + "/span/span/span/input";
						}

						Filter_Exists = true;
						break;
					}
				}
			}

			if (Filter_Exists) {
				// Code has been commented as filters are working fine and now the text are
				// getting remove from filter
				/*
				 * /TestSuiteBase_MonitorPlan.WithOut_OR_fnClear_Field_By_Pressing_BackspaceKey(
				 * View_Filter_Input_Xpath, 15);
				 * TestSuiteBase_MonitorPlan.WithOut_OR_fnClear(View_Filter_Input_Xpath);
				 * TestSuiteBase_MonitorPlan.WithOut_OR_fnClear_Field_By_Pressing_BackspaceKey(
				 * View_Filter_Input_Xpath, 15);
				 * TestSuiteBase_MonitorPlan.WithOut_OR_fnClear(View_Filter_Input_Xpath);
				 * //TestSuiteBase_MonitorPlan.WithOut_OR_fnClear(View_Filter_Input_Xpath);
				 * fnsLoading_Progressing_wait(2);
				 * TestSuiteBase_MonitorPlan.WithOut_OR_fnType(View_Filter_Input_Xpath,
				 * Filter_Value_Enter);
				 * fnsLoading_Progressing_wait(3);
				 * Thread.sleep(1000);
				 */
				if (FilterName.toLowerCase().contains("popup")) {
					TestSuiteBase_MonitorPlan.WithOut_OR_fnClear(View_Filter_Input_Xpath);
					fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
					// Thread.sleep(1000); //Commented on 15.9.2017 as not working while loading
					TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(View_Filter_Input_Xpath)
							.sendKeys(Filter_Value_Enter);
					fnsLoading_Progressing_on_Popup_wait_for_Popup(3);
					// Thread.sleep(5000); //Commented on 15.9.2017 as not working while loading
				} else if (FilterName.toLowerCase().contains("date")) {
					/*
					 * TestSuiteBase_MonitorPlan.WithOut_OR_fnClear(View_Filter_Input_Xpath);
					 * fnsLoading_Progressing_wait(2);
					 */
					// TestSuiteBase_MonitorPlan.WithOut_OR_fnType(View_Filter_Input_Xpath,
					// Filter_Value_Enter);
					TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(View_Filter_Input_Xpath)
							.sendKeys(Filter_Value_Enter);
					// Thread.sleep(1000);
					/*
					 * Actions EnterKeyAct = new Actions(driver); //temp as filter work after enter
					 * press
					 * EnterKeyAct.sendKeys(TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(
					 * View_Filter_Input_Xpath), Keys.ENTER).build().perform();
					 */
					fnsLoading_Progressing_wait(3);
				} else {
					TestSuiteBase_MonitorPlan.WithOut_OR_fnClear(View_Filter_Input_Xpath);
					fnsLoading_Progressing_wait(2);
					TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(View_Filter_Input_Xpath)
							.sendKeys(Filter_Value_Enter);
					fnsLoading_Progressing_wait(2);
				}

			} else {
				throw new Exception("FAILED == Records for ( " + FilterName.split(":")[1]
						+ " ) are not found into the view, please refer screen shot >> Filter_Fail"
						+ fnsScreenShot_Date_format());
			}

			Filter_Record_Total_Count = fnsReturn_View_Results_Reords_Total_Count(
					"Automation View Filter ( " + FilterName + " )", "View_Result_Table", 20);
			return Filter_Record_Total_Count;

		} catch (Throwable t) {
			// isTestCasePass = false;
			fnsTake_Screen_Shot("Filter_Fail");
			throw new Exception("Filter ( " + FilterName.split(":")[0] + " ) : " + Throwables.getStackTraceAsString(t));
		}

	}

	public Integer fnsReturn_ColumnNumber_from_TableHeaderXpath_WithoutOR(String TableHeaderXpath_Without_OR,
			String Column_Name_From_Data_Title) throws Throwable {
		try {
			Integer Column_Number = 1;
			boolean Column_Exists = false;

			TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(TableHeaderXpath_Without_OR);

			List<WebElement> All_Header_Objects = TestSuiteBase_MonitorPlan
					.WithOut_OR_fnGet_ObjectX(TableHeaderXpath_Without_OR).findElements(By.tagName("th"));
			for (WebElement All_Header_Elements : All_Header_Objects) {
				String ColumnText_Fetch = "";
				try {
					ColumnText_Fetch = All_Header_Elements.getAttribute("data-title").trim();
				} catch (NullPointerException nl) {
					// nothing to do
				}
				if (ColumnText_Fetch.toLowerCase().equals(Column_Name_From_Data_Title.toLowerCase().trim())) {
					Column_Exists = true;
					break;
				}
				Column_Number++;
			}

			if (Column_Exists == false) {
				throw new Exception(
						"FAILED == Column is not Exists into the view, please refer the screen shot >> Column_Not_Exists"
								+ fnsScreenShot_Date_format());
			}

			return Column_Number;

		} catch (Throwable t) {
			// //isTestCasePass = false; //Not working in for looping
			fnsTake_Screen_Shot("Column_Not_Exists");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}

	}

	public void fncVerify_GraphDisplay_And_RecordsCount_Validate_Bia_ToolTip(Integer View_TotalRecordsCount,
			ArrayList<String> ViewColumn_Values_Text_List, ArrayList<Integer> ViewColumn_Records_Count_List,
			boolean Take_Screenshot) throws Throwable {
		try {
			boolean TotalRecordsCount_Verification_Enabled_Flag = false;
			Integer TotalRecordsCount_Verification_Enabled = View_TotalRecordsCount;
			boolean Graph_indiviudual_Value_Verified = false;
			Integer Graph_indiviudual_Value_Count = 0;
			Integer Graph_Total_Value_Count = 0;
			View_Filter_Input_Xpath = null;
			Element = null;

			for (int i = 0; i < ViewColumn_Records_Count_List.size(); i++) {
				TotalRecordsCount_Verification_Enabled = TotalRecordsCount_Verification_Enabled
						- ViewColumn_Records_Count_List.get(i);

				if (TotalRecordsCount_Verification_Enabled == 0) {
					TotalRecordsCount_Verification_Enabled_Flag = true;
				}
			}

			List<WebElement> Graphs_Text_allObjs = fnsGet_OR_New_NSFOnline_ObjectX("Snapshot_View_PieChart_Graph")
					.findElements(By.tagName("text"));

			for (int i = 0; i < ViewColumn_Values_Text_List.size(); i++) {
				Graph_indiviudual_Value_Verified = false;
				for (WebElement Graphs_Text_Element : Graphs_Text_allObjs) {
					Text_on_Graphs = Graphs_Text_Element.getText().toLowerCase().trim();
					Actions act = new Actions(driver);
					act.moveToElement(Graphs_Text_Element).build().perform();
					Thread.sleep(500);
					ToolTip_Text = "";

					for (int GV = 1; GV <= 10; GV++) {
						try {
							Element = driver.findElement(
									By.xpath(OR_New_NSFOnline.getProperty("Snapshot_View_PieChart_Graph_ToolTip")));
							ToolTip_Text = Element.getText().toLowerCase().trim();
						} catch (Throwable t) {
							// Nothing to do
						}
						if ((ToolTip_Text.length() > 0)) {
							break;
						} else {
							Thread.sleep(250);
						}
					}

					if ((ToolTip_Text.contains(ViewColumn_Values_Text_List.get(i).toLowerCase().trim()))) {
						ToolTip_Text = ToolTip_Text.split("-")[1].trim();
						for (int j = 0; j <= 5; j++) {
							if (ToolTip_Text.contains(",")) {
								ToolTip_Text = ToolTip_Text.replace(",", "");
							} else {
								break;
							}
						}
						Graph_indiviudual_Value_Count = Integer.parseInt(ToolTip_Text);
						assert Graph_indiviudual_Value_Count.equals(ViewColumn_Records_Count_List.get(i))
								: "FAILED == '" + ViewColumn_Values_Text_List.get(i).toUpperCase()
										+ "' Total no of Records < " + ViewColumn_Records_Count_List.get(i)
										+ " > from view is not matched with the Graph Total no of records < "
										+ Graph_indiviudual_Value_Count
										+ " >, please refer screen shot >> Graph_Records_Verification_Fail"
										+ fnsScreenShot_Date_format();
						fnsApps_Report_Logs("PASSED == '" + ViewColumn_Values_Text_List.get(i).toUpperCase()
								+ "' Total no of Records < " + ViewColumn_Records_Count_List.get(i)
								+ " > from view is matched with the Graph Total no of records < "
								+ Graph_indiviudual_Value_Count + " > automation");
						Graph_Total_Value_Count = Graph_Total_Value_Count + Graph_indiviudual_Value_Count;
						Graph_indiviudual_Value_Verified = true;
						break;

					}

				}

				if (Graph_indiviudual_Value_Verified == false) {
					if (ViewColumn_Records_Count_List.get(i) == 0) {
						fnsApps_Report_Logs("PASSED == '" + ViewColumn_Values_Text_List.get(i).toUpperCase()
								+ "' records count is not coming into the Graph as count is zero.");
					} else {
						throw new Exception("FAILED == '" + ViewColumn_Values_Text_List.get(i).toUpperCase()
								+ "' records count is not coming into the Graph, please refer screen shot >> Graph_Records_Verification_Fail"
								+ fnsScreenShot_Date_format());
					}
				}

			}

			if (TotalRecordsCount_Verification_Enabled_Flag) {
				try {
					assert Graph_Total_Value_Count.equals(View_TotalRecordsCount)
							: "FAILED == View's Total no of Records < " + View_TotalRecordsCount
									+ " > is not matched with the Graph Total no of records< " + Graph_Total_Value_Count
									+ " > , please refer screen shot >> Graph_Records_Verification_Fail"
									+ fnsScreenShot_Date_format();
					fnsApps_Report_Logs("PASSED == View's Total no of Records < " + View_TotalRecordsCount
							+ " > is matched with the Graph Total no of records < " + Graph_Total_Value_Count
							+ " >. automation");
				} catch (Throwable t) {
					throw new Exception(Throwables.getStackTraceAsString(t));
				}
			}

			if (driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("Snapshot_View_PieChart_Graph_Print_Icon")))
					.size() > 0) {
				fnsApps_Report_Logs("PASSED == Successfully verified that export 'PRINT' Icon is displayed.");
			} else {
				throw new Exception(
						"FAILED == Export 'PRINT' Icon is not displayed, please refer screen shot >> Graph_Records_Verification_Fail"
								+ fnsScreenShot_Date_format());
			}

			if (driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("Snapshot_View_PieChart_Graph_Pdf_Icon")))
					.size() > 0) {
				fnsApps_Report_Logs("PASSED == Successfully verified that export 'PDF' Icon is displayed.");
			} else {
				throw new Exception(
						"FAILED == Export 'PDF' Icon is not displayed, please refer screen shot >> Graph_Records_Verification_Fail"
								+ fnsScreenShot_Date_format());
			}

		} catch (NoSuchWindowException W) {
			throw new Exception(W.getMessage());
		} catch (NullPointerException n) {
			throw new Exception("Pie Chart graph is not coming... " + Throwables.getStackTraceAsString(n));
		} catch (Throwable t) {
			if (Take_Screenshot) {
				fnsTake_Screen_Shot("Graph_Records_Verification_Fail");
			}
			throw new Exception("Pie Chart graph : " + Throwables.getStackTraceAsString(t));
		}

	}

	public void fncVerify_GraphDisplay_And_RecordsCount_Validate(Integer View_TotalRecordsCount,
			ArrayList<String> ViewColumn_Values_Text_List, ArrayList<Integer> ViewColumn_Records_Count_List,
			boolean Take_Screenshot) throws Throwable {
		try {
			boolean TotalRecordsCount_Verification_Enabled_Flag = false;
			Integer TotalRecordsCount_Verification_Enabled = View_TotalRecordsCount;
			boolean Graph_indiviudual_Value_Verified = false;
			Integer Graph_Total_Value_Count = 0;
			View_Filter_Input_Xpath = null;
			Element = null;

			for (int i = 0; i < ViewColumn_Records_Count_List.size(); i++) {
				TotalRecordsCount_Verification_Enabled = TotalRecordsCount_Verification_Enabled
						- ViewColumn_Records_Count_List.get(i);

				if (TotalRecordsCount_Verification_Enabled == 0) {
					TotalRecordsCount_Verification_Enabled_Flag = true;
				}
			}

			List<WebElement> Graphs_Text_allObjs = fnsGet_OR_New_NSFOnline_ObjectX("Snapshot_View_PieChart_Graph")
					.findElements(By.tagName("text"));

			for (int i = 0; i < ViewColumn_Values_Text_List.size(); i++) {
				Graph_indiviudual_Value_Verified = false;
				boolean Indiviudual_Count_Verified = false;
				boolean Indiviudual_Text_Verified = false;
				String ViewColumn_Records_Count_from_View = ViewColumn_Records_Count_List.get(i).toString();
				for (WebElement Graphs_Text_Element : Graphs_Text_allObjs) {
					Text_on_Graphs = Graphs_Text_Element.getText().toLowerCase().trim();
					if ((Text_on_Graphs.contains(","))) {
						for (int j = 0; j <= 5; j++) {
							if (Text_on_Graphs.contains(",")) {
								Text_on_Graphs = Text_on_Graphs.replace(",", "");
							} else {
								break;
							}
						}
						if (Text_on_Graphs.equalsIgnoreCase(ViewColumn_Records_Count_from_View)) {
							Indiviudual_Count_Verified = true;
							Graph_Total_Value_Count = Integer.parseInt(Text_on_Graphs);
							TotalRecordsCount_Verification_Enabled = TotalRecordsCount_Verification_Enabled
									+ Graph_Total_Value_Count;
						}
					} else if ((Text_on_Graphs.contains(ViewColumn_Values_Text_List.get(i).toLowerCase()))) {
						Indiviudual_Text_Verified = true;
					} else if (Text_on_Graphs.equalsIgnoreCase(ViewColumn_Records_Count_from_View)) {
						try {
							Graph_Total_Value_Count = Integer.parseInt(Text_on_Graphs);
							TotalRecordsCount_Verification_Enabled = TotalRecordsCount_Verification_Enabled
									+ Graph_Total_Value_Count;
						} catch (Throwable t) {
							// nothing to do
						}
						Indiviudual_Count_Verified = true;
					}
					if (Indiviudual_Count_Verified && Indiviudual_Text_Verified) {
						Graph_indiviudual_Value_Verified = true;
						break;
					}
				}

				if (Graph_indiviudual_Value_Verified) {
					fnsApps_Report_Logs("PASSED == '" + ViewColumn_Values_Text_List.get(i).toUpperCase()
							+ "' Total no of Records < " + ViewColumn_Records_Count_List.get(i)
							+ " > from view is matched with the Graph Total no of records < " + Text_on_Graphs
							+ " > automation");
				} else {
					if (ViewColumn_Records_Count_List.get(i) == 0) {
						fnsApps_Report_Logs("PASSED == '" + ViewColumn_Values_Text_List.get(i).toUpperCase()
								+ "' records count is not coming into the Graph as count is zero.");
					} else if (Indiviudual_Text_Verified == false) {
						throw new Exception("FAILED == '" + ViewColumn_Values_Text_List.get(i).toUpperCase()
								+ "' records count is not coming into the Graph, please refer screen shot >> Graph_Records_Verification_Fail"
								+ fnsScreenShot_Date_format());
					} else {
						throw new Exception("FAILED == '" + ViewColumn_Values_Text_List.get(i).toUpperCase()
								+ "' Total no of Records < " + ViewColumn_Records_Count_List.get(i)
								+ " > from view is not matched with the Graph Total no of records < " + Text_on_Graphs
								+ " >, please refer screen shot >> Graph_Records_Verification_Fail"
								+ fnsScreenShot_Date_format());
					}
				}

			}

			if (TotalRecordsCount_Verification_Enabled_Flag) {
				try {
					assert TotalRecordsCount_Verification_Enabled.equals(View_TotalRecordsCount)
							: "FAILED == View's Total no of Records < " + View_TotalRecordsCount
									+ " > is not matched with the Graph Total no of records< "
									+ TotalRecordsCount_Verification_Enabled
									+ " > , please refer screen shot >> Graph_Records_Verification_Fail"
									+ fnsScreenShot_Date_format();
					fnsApps_Report_Logs("PASSED == View's Total no of Records < " + View_TotalRecordsCount
							+ " > is matched with the Graph Total no of records < "
							+ TotalRecordsCount_Verification_Enabled + " >. automation");
				} catch (Throwable t) {
					throw new Exception(Throwables.getStackTraceAsString(t));
				}
			}

			if (driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("Snapshot_View_PieChart_Graph_Print_Icon")))
					.size() > 0) {
				fnsApps_Report_Logs("PASSED == Successfully verified that export 'PRINT' Icon is displayed.");
			} else {
				throw new Exception(
						"FAILED == Export 'PRINT' Icon is not displayed, please refer screen shot >> Graph_Records_Verification_Fail"
								+ fnsScreenShot_Date_format());
			}

			if (driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("Snapshot_View_PieChart_Graph_Pdf_Icon")))
					.size() > 0) {
				fnsApps_Report_Logs("PASSED == Successfully verified that export 'PDF' Icon is displayed.");
			} else {
				throw new Exception(
						"FAILED == Export 'PDF' Icon is not displayed, please refer screen shot >> Graph_Records_Verification_Fail"
								+ fnsScreenShot_Date_format());
			}

		} catch (NoSuchWindowException W) {
			throw new Exception(W.getMessage());
		} catch (NullPointerException n) {
			throw new Exception("Pie Chart graph is not coming... " + Throwables.getStackTraceAsString(n));
		} catch (Throwable t) {
			if (Take_Screenshot) {
				fnsTake_Screen_Shot("Graph_Records_Verification_Fail");
			}
			throw new Exception("Pie Chart graph : " + Throwables.getStackTraceAsString(t));
		}

	}

	public void fncClickonFirstFacilityfromViewResultTable_n_ReturnBackonViewScreen(String View_Home_ScreenName,
			String Link_Column_Name_From_DataTitle) throws Throwable {
		try {

			String Link_Text = null;
			Integer Link_Column_Number = fnsReturn_ColumnNumber_from_TableHeaderXpath_WithoutOR(
					OR_New_NSFOnline.getProperty("View_Result_TableHeader"), Link_Column_Name_From_DataTitle);
			String First_Row_MatchingColumn_Xpath = OR_New_NSFOnline.getProperty("View_Result_Table") + "/tr[1]/td["
					+ Link_Column_Number + "]";
			for (int i = 0; i <= NewNsfOnline_Element_Max_Wait_Time; i++) {
				if (driver.findElements(By.xpath(First_Row_MatchingColumn_Xpath)).size() > 0) {
					break;
				} else {
					Thread.sleep(100);
				}
				if (i == NewNsfOnline_Element_Max_Wait_Time) {
					throw new Exception("FAILED == Records are not coming into the view on '" + View_Home_ScreenName
							+ "' screen, please refer screen shot >> Opening_First_Facility_and_ReturnBack_to_View_FAIL"
							+ fnsScreenShot_Date_format() + "--Xpath >> " + First_Row_MatchingColumn_Xpath);
				}
			}
			List<WebElement> First_Row_td_Objects = TestSuiteBase_MonitorPlan
					.WithOut_OR_fnGet_ObjectX(First_Row_MatchingColumn_Xpath).findElements(By.tagName("a"));
			if (First_Row_td_Objects.size() == 1) {
				for (WebElement First_Row_td_Elements : First_Row_td_Objects) {
					Link_Text = First_Row_td_Elements.getText().trim();
					First_Row_td_Elements.click();
					fnsApps_Report_Logs("PASSED == Click done on the '" + Link_Text + "' link");
					fnsLoading_Progressing_wait(4);
					break;
				}
			} else {
				throw new Exception("FAILED == More than one link is present into the cell row 1 of column '"
						+ Link_Column_Name_From_DataTitle
						+ "', please refer screen shot >> Opening_First_Facility_and_ReturnBack_to_View_FAIL"
						+ fnsScreenShot_Date_format());
			}

			try {
				for (int i = 0; i <= NewNsfOnline_Element_Max_Wait_Time; i++) {
					if (driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("ActiveView_DD"))).size() > 0) {
						Thread.sleep(50);
					} else {
						break;
					}
					if (i == NewNsfOnline_Element_Max_Wait_Time) {
						String Facility_Screen_Table_First_Record = TestSuiteBase_MonitorPlan
								.WithOut_OR_fnGet_ObjectX(First_Row_MatchingColumn_Xpath).getText().trim();
						if (!(Link_Text.equals(Facility_Screen_Table_First_Record))) {
							break;
						} else {
							throw new Exception("FAILED == After clicking on '" + Link_Text
									+ "' link, Application is not getting navigate to Facility info screen, please refer screen shot >> Opening_First_Facility_and_ReturnBack_to_View_FAIL"
									+ fnsScreenShot_Date_format());
						}
					}
				}
			} catch (Throwable t) {
				throw new Exception(Throwables.getStackTraceAsString(t));
			}
			fnsLoading_Progressing_wait(2);

			if (View_Home_ScreenName.equalsIgnoreCase("Site Documents")) {

				fnsWait_and_Click("Site_Document_Back_Button");
				fnsLoading_Progressing_wait(3);

			} else {

				fnsWait_and_Click("NewNsfOnline_Back_Bttn");
				fnsLoading_Progressing_wait(3);
			}

			try {
				fnsGet_Element_Enabled("View_Result_Table");
			} catch (Throwable t) {
				throw new Exception(
						"FAILED == After clicking on 'BACK' button, Application is not getting navigate to '"
								+ View_Home_ScreenName
								+ "' screen, please refer screen shot >> Opening_First_Facility_and_ReturnBack_to_View_FAIL"
								+ fnsScreenShot_Date_format());
			}

			fnsApps_Report_Logs("PASSED == Clicking on '" + Link_Text + "' Link and return back to '"
					+ View_Home_ScreenName + "' view screen is successfully done.");
		} catch (Throwable t) {
			// isTestCasePass = false;
			fnsTake_Screen_Shot("Opening_First_Facility_and_ReturnBack_to_View_FAIL");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}

	}

	// To Download file
	public void fnsDownload_File(String File_Download_bttn_Xpath) throws Throwable {
		String File_Type = File_Download_bttn_Xpath.split("_")[1].trim();
		long AllFileSize_AfterFileSave = 0;
		boolean File_Download_Success = false;
		String FileLocation = System.getProperty("user.home") + "\\Downloads";
		try {
			long AllFileSize_BeforeFileSave = FileUtils.sizeOfDirectory(new File(FileLocation));

			fnsGet_Element_Enabled(File_Download_bttn_Xpath);
			fnsWait_and_Click(File_Download_bttn_Xpath);
			// fnsLoading_Progressing_wait(3); //Not working as Ok Pupup Block UI and stuck
			// into loading >> html/body/div[1]/div[1]

			for (int waitloop = 1; waitloop <= ((NewNsfOnline_Element_Max_Wait_Time) / 5); waitloop++) {

				try {
					if ((driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("Model_Confirmation_Popup_OK_Bttn")))
							.size() > 0)) {
						if ((driver
								.findElement(By.xpath(OR_New_NSFOnline.getProperty("Model_Confirmation_Popup_OK_Bttn")))
								.isDisplayed())) {
							fnsGet_Element_Enabled("Model_Confirmation_Popup_OK_Bttn");
							fnsWait_and_Click("Model_Confirmation_Popup_OK_Bttn");
							fnsLoading_Progressing_on_Popup_wait_for_Popup(3);
							// fnsLoading_Progressing_wait(3); //Not working as Ok Pupup Block UI and stuck
							// into loading >> html/body/div[1]/div[1]
						}
					}
				} catch (Throwable t) {
					if (!(waitloop == 1)) {
						fnsLoading_Progressing_on_Popup_wait_for_Popup(1);
					}
					System.out.println("'Model_Confirmation_Popup_OK_Bttn' display fail = " + waitloop);
				}
				// driver.findElement(By.xpath("//button[text()='OK']")).isDisplayed();
				fnsLoading_Progressing_on_Popup_wait_for_Popup(1);
				Actions action = new Actions(driver);
				action.keyDown(Keys.ALT);
				action.sendKeys("s");
				action.keyUp(Keys.ALT);
				action.build().perform();

				for (int i = 1; i <= 5; i++) {

					try {
						AllFileSize_AfterFileSave = FileUtils.sizeOfDirectory(new File((FileLocation)));

						if (AllFileSize_BeforeFileSave < AllFileSize_AfterFileSave) {
							File_Download_Success = true;
							break;
						} else {
							Thread.sleep(1000);
						}

					} catch (IllegalArgumentException K) {
						// nothing to do
					}
				}
				/*
				 * if( (waitloop == ( (NewNsfOnline_Element_Max_Wait_Time)/5 )) &&
				 * File_Download_Success == false){
				 * 
				 * }
				 */
				if (File_Download_Success == true) {
					break;
				}
			}

			System.out.println(
					"File Size Before = " + AllFileSize_BeforeFileSave + " ... After = " + AllFileSize_AfterFileSave);
			assert (AllFileSize_BeforeFileSave < AllFileSize_AfterFileSave)
					: "FAILED == File is not getting download. After <" + (NewNsfOnline_Element_Max_Wait_Time) * 2
							+ "> seconds wait, Please refer screenshot >> " + File_Type + "_FileDownloadFail"
							+ fnsScreenShot_Date_format();

			Actions action = new Actions(driver);
			action.keyDown(Keys.ALT);
			action.sendKeys("q");
			action.keyUp(Keys.ALT);
			action.build().perform();
			Thread.sleep(2000);

			fnsApps_Report_Logs("PASSED == '" + File_Type + "' File successfully downloaded.");

		} catch (Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot(File_Type + "_FileDownloadFail");
			throw new Exception("FAILED == '" + File_Type + "' " + Throwables.getStackTraceAsString(t));
		}

	}

	public void fnsDownload_File_or_Verify_Validation_Message(String DownloadLinkName_TEXT,
			String Download_File_Link_Xpath_withoutOR) throws Throwable {
		try {
			long AllFileSize_AfterFileSave = 0;
			boolean File_Download_Success = false;
			String FileLocation = System.getProperty("user.home") + "\\Downloads";
			long AllFileSize_BeforeFileSave = FileUtils.sizeOfDirectory(new File(FileLocation));

			TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(Download_File_Link_Xpath_withoutOR);
			fnsLoading_Progressing_on_Popup_wait_for_Popup(3);

			for (int wait = 1; wait <= ((NewNsfOnline_Element_Max_Wait_Time)); wait++) {

				if ((driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("Model_Popup_OK_Bttn"))).size() > 0)) {
					if ((driver.findElement(By.xpath(OR_New_NSFOnline.getProperty("Model_Popup_OK_Bttn")))
							.isDisplayed())) {

						fns_Verify_Success_or_Error_Message_on_ModelPopup_Generation(true, 10, "File not available",
								15);
						fnsApps_Report_Logs("PASSED == 'File not available' validation message is coming for file < "
								+ DownloadLinkName_TEXT + " > download link.");
						fnsGet_Element_Enabled("Model_Popup_OK_Bttn");
						fnsGet_Element_Displayed("Model_Popup_OK_Bttn");
						Thread.sleep(1000);
						fnsWait_and_Click("Model_Popup_OK_Bttn");
						fnsLoading_Progressing_on_Popup_wait_for_Popup(3);
						break;
					}
				} else {

					Actions action = new Actions(driver);
					action.keyDown(Keys.ALT);
					action.sendKeys("s");
					action.keyUp(Keys.ALT);
					action.build().perform();

					try {
						AllFileSize_AfterFileSave = FileUtils.sizeOfDirectory(new File((FileLocation)));
						if (AllFileSize_BeforeFileSave < AllFileSize_AfterFileSave) {
							File_Download_Success = true;
							fnsApps_Report_Logs("PASSED ==  ********  Successfully download file for  file < "
									+ DownloadLinkName_TEXT + " > download link.");
							break;
						}
					} catch (IllegalArgumentException K) {
						// nothing to do
					}

				}
				if (File_Download_Success == true) {
					break;
				} else if (wait == NewNsfOnline_Element_Max_Wait_Time) {
					fnsTake_Screen_Shot("File_Not_Download");
					throw new Exception("FAILED == File is not getting download for  file < " + DownloadLinkName_TEXT
							+ " > download link, please refer screen shot >> File_Not_Download"
							+ fnsScreenShot_Date_format());
				} else {
					Thread.sleep(400);
				}
			}

		} catch (Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot("FileDownloadFail");
			throw new Exception(DownloadLinkName_TEXT + " fail :: " + Throwables.getStackTraceAsString(t));
		}

	}

	// Verify records by moving on all screen of last pagination Dropdown
	public void fns_Verify_Record_Present_By_Clicking_LastPagination_AllScreens(String Pagination_DD_Xpath)
			throws Throwable {
		try {
			fnsGet_Element_Enabled(Pagination_DD_Xpath);
			fnsWait_and_Click(Pagination_DD_Xpath);

			for (int i = 0; i < 10; i++) {
				fnsGet_OR_New_NSFOnline_ObjectX(Pagination_DD_Xpath).click();
				Thread.sleep(500);
				if (fnsGet_OR_New_NSFOnline_ObjectX(Pagination_DD_Xpath).isDisplayed()) {

				}
			}

			// Pending method of checklist pagination

		} catch (Throwable t) {
			isTestCasePass = false;
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}

	// Verify View is present on alert screen
	public void fns_AlertScreen_Click_on_AlertMenu_and_Verify_View_and_Label_Present(boolean Clicking_on_Alert_Ajax,
			boolean Clicking_on_Alert, String Product_Label_Name, String Alert_Name) throws Throwable {
		try {
			String View_Title = "";
			if (Clicking_on_Alert_Ajax == true) {
				fncMenu_Ajax_Link_Click_By_PassingAjaxPath("Alerts_Ajax");
			}
			boolean Product_Label_Found = false;
			boolean View_Found = false;

			TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(
					"(" + OR_New_NSFOnline.getProperty("Alert_Individiual_div_Xpath") + ")[1]");
			fnsLoading_Progressing_wait_AlertLaoder(2);

			Integer All_Products_Div_Size = driver
					.findElements(By.xpath(OR_New_NSFOnline.getProperty("Alert_Individiual_div_Xpath"))).size();

			for (int i = 1; i <= All_Products_Div_Size; i++) {

				String Products_Div_Xpath = "(" + OR_New_NSFOnline.getProperty("Alert_Individiual_div_Xpath") + ")[" + i
						+ "]";
				if (driver.findElements(By.xpath(Products_Div_Xpath)).size() > 0) {
					String Products_Div_Text = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(Products_Div_Xpath)
							.getText().trim();
					TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element(Products_Div_Xpath);
					if (Products_Div_Text.toLowerCase().contains(Product_Label_Name.toLowerCase().trim())) {
						Product_Label_Found = true;
						if (Products_Div_Text.toLowerCase().contains(Alert_Name.toLowerCase().trim())) {
							View_Found = true;
							fnsApps_Report_Logs("PASSED == Alert '" + Alert_Name + "' is present into the '"
									+ Product_Label_Name + "' section on alert screen.");

							if (Clicking_on_Alert == true) {
								String Alert_Link_Xpath = "//a[text()='" + Alert_Name + "']";
								fnsWait_Click_on_First_Visible_Element_if_More_than_One_Coming(Alert_Name,
										Alert_Link_Xpath);
								fnsLoading_Progressing_wait(2);
							}
						}
						if (View_Found == true) {
							break;
						}
					}
				}
			}

			if (Product_Label_Found == false) {
				throw new Exception("FAILED == '" + Product_Label_Name
						+ "' section is not found on alert screen, Please refer screen shot >> Alert_Record_Not_found"
						+ fnsScreenShot_Date_format());
			}

			if (View_Found == false) {
				throw new Exception("FAILED == Alert '" + Alert_Name + "' is not present into the section '"
						+ Product_Label_Name + "' on alert screen, Please refer screen shot >> Alert_Record_Not_found"
						+ fnsScreenShot_Date_format());
			}

			if (Clicking_on_Alert == true) {
				for (int wait = 0; wait <= ((NewNsfOnline_Element_Max_Wait_Time)); wait++) {
					if (driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("ActiveView_DD"))).size() > 0) {
						fnsLoading_Progressing_wait(4);
						/*
						 * if( (Product_Label_Name.toLowerCase().contains("action plan")) ||
						 * (Product_Label_Name.toLowerCase().contains("audit components")) ||
						 * (Product_Label_Name.toLowerCase().contains("finding(s)")) ||
						 * (Product_Label_Name.toLowerCase().contains("food managers")) ||
						 * (Product_Label_Name.toLowerCase().contains("applications")) ){
						 * View_Title =
						 * fnsGet_OR_New_NSFOnline_ObjectX("Action_View_Title").getText().trim();
						 * }else{
						 * View_Title = fnsGet_OR_New_NSFOnline_ObjectX("View_Title").getText().trim();
						 * }
						 */

						for (int i = 0; i <= 60; i++) {
							try {
								if (driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("View_Title")))
										.size() > 0) {
									fnsLoading_Progressing_wait(1);
									View_Title = fnsGet_OR_New_NSFOnline_ObjectX("View_Title").getText().trim();
									break;
								} else if (driver
										.findElements(By.xpath(OR_New_NSFOnline.getProperty("Action_View_Title")))
										.size() > 0) {
									fnsLoading_Progressing_wait(1);
									View_Title = fnsGet_OR_New_NSFOnline_ObjectX("Action_View_Title").getText().trim();
									break;
								} else {
									Thread.sleep(1000);
								}
							} catch (Throwable t) {
								// nothing to do
							}

							if (i == 60) {
								fnsTake_Screen_Shot("ViewName_Not_Coming");
								throw new Exception(
										"FAILED == The View name is not coming, Please refer screen shot >> ViewName_Not_Coming"
												+ fnsScreenShot_Date_format());
							}
						}

						try {
							assert View_Title.contains(Alert_Name) : "FAILED == after clicking on Alert ' " + Alert_Name
									+ " '. The View < " + View_Title + " > is opened instead of view < " + Alert_Name
									+ " >, please refer the screen shot >> AlertName" + fnsScreenShot_Date_format();
							fnsApps_Report_Logs("PASSED == Successfully opened the correct " + Product_Label_Name
									+ " view screen, after clicking on '" + Alert_Name + "' alert.");
							fnsLoading_Progressing_wait(2);
						} catch (Throwable t) {
							fnsTake_Screen_Shot("Correct_View_Not_Open");
							throw new Exception(Throwables.getStackTraceAsString(t));
						}
						break;
					} else {
						Thread.sleep(1000);
					}
					if (wait == ((NewNsfOnline_Element_Max_Wait_Time))) {
						fnsTake_Screen_Shot("Screen_Open_Fail");
						throw new Exception("FAILED == " + Product_Label_Name
								+ " view screen is not getting open, after clicking on '" + Alert_Name
								+ "' alert, After " + ((NewNsfOnline_Element_Max_Wait_Time))
								+ " seconds wait, please refer screen shot >> Screen_Open_Fail"
								+ fnsScreenShot_Date_format());
					}
				}
			}

		} catch (Throwable t) {
			isTestCasePass = false;
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}

	public String fnsw_Click_on_Table_Link_by_Passing_Cell_Xpath__and__Return_CellValue(
			String Matching_Row_and_Cell_Xpath_withoutOR) throws Throwable {
		try {
			String Matching_Row_and_Cell__Text = TestSuiteBase_MonitorPlan
					.WithOut_OR_fnGet_ObjectX(Matching_Row_and_Cell_Xpath_withoutOR).getText().trim();
			String Matching_Row_and_Cell__Link_Xpath = "//a[text()='" + Matching_Row_and_Cell__Text + "']";
			fnsWait_Click_on_First_Visible_Element_if_More_than_One_Coming(Matching_Row_and_Cell__Text,
					Matching_Row_and_Cell__Link_Xpath);
			fnsApps_Report_Logs("PASSED == Click done on the link <" + Matching_Row_and_Cell__Text + ">.");
			fnsLoading_Progressing_wait(2);
			fnsLoading_Progressing_wait(1);
			return Matching_Row_and_Cell__Text;
		} catch (Throwable t) {
			isTestCasePass = false;
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}

	public void fnsGet_Element_Display_byPassing_ElementName_and_Xpath_WithoutOR(String ScreenName, String ElementName,
			String ElementXpath) throws Throwable {
		try {
			for (int i = 0; i <= NewNsfOnline_Element_Max_Wait_Time; i++) {
				try {
					if (driver.findElement(By.xpath((ElementXpath))).isDisplayed()) {
						fnsApps_Report_Logs(
								"PASSED == ************ " + ElementName + " is present on the " + ScreenName + ".");
						break;
					} else {
						Thread.sleep(1000);
					}
				} catch (Throwable t) {
					if (i == NewNsfOnline_Element_Max_Wait_Time) {
						fnsTake_Screen_Shot(ElementName + "_Not_Present");
						throw new Exception("FAILED == " + ElementName + " is NOT present on the " + ScreenName
								+ ", please refer the screen shot >> " + ElementName + "_Not_Present"
								+ fnsScreenShot_Date_format());
					} else {
						Thread.sleep(1000);
					}
				}

			}

		} catch (Throwable t) {
			// isTestCasePass = false;
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}

	public boolean fnsGet_Element_NOT_Display_WithoutOR(String ElementName, String ElementXpath) throws Throwable {
		boolean Element_NOT_Display = false;
		try {
			for (int i = 0; i <= 10; i++) {
				try {
					if (driver.findElements(By.xpath(ElementXpath)).size() > 0) {
						if (!(driver.findElement(By.xpath((ElementXpath))).isDisplayed())) {
							fnsApps_Report_Logs(
									"PASSED == ************ " + ElementName + " is NOT present. Automation");
							Element_NOT_Display = true;
							break;
						} else {
							Thread.sleep(1000);
						}
					} else {
						fnsApps_Report_Logs("PASSED == ************ " + ElementName + " is NOT present.");
						Element_NOT_Display = true;
						break;
					}
				} catch (Throwable t) {
					// nothing to do
				}
			}
			if (Element_NOT_Display == false) {
				fnsTake_Screen_Shot(ElementName + "_Present");
				throw new Exception("FAILED == " + ElementName
						+ " is present, which is not expected, please refer the screen shot >> " + ElementName
						+ "_Present" + fnsScreenShot_Date_format());
			}
			return Element_NOT_Display;
		} catch (Throwable t) {
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}

	public void fncNavigation_Verify_Application_Navigated_To_ViewScreen(String Click_Element_Name,
			String Navigated_View_Screen_Name) throws Throwable {
		try {
			for (int wait = 0; wait <= ((NewNsfOnline_Element_Max_Wait_Time)); wait++) {
				try {
					if (driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("ActiveView_DD"))).size() > 0) {
						fnsApps_Report_Logs("PASSED == After clicking on " + Click_Element_Name
								+ ", application successfully navigated to '" + Navigated_View_Screen_Name
								+ "' view screen.");
						// fnsLoading_Progressing_wait(3);
						fnsLoading_Progressing_wait(2);
						break;
					} else {
						fnsLoading_Progressing_wait(1);
					}
				} catch (Throwable tt) {
					fnsLoading_Progressing_wait(1);
				}
				if (wait == ((NewNsfOnline_Element_Max_Wait_Time))) {
					throw new Exception("FAILED == After clicking on " + Click_Element_Name
							+ ", application  NOT  navigated to '" + Navigated_View_Screen_Name
							+ "' view  screen, [ Wait Time (" + ((NewNsfOnline_Element_Max_Wait_Time))
							+ ") seconds ], please refer screen shot >> " + Navigated_View_Screen_Name
							+ "_View_Screen_Not_Open" + fnsScreenShot_Date_format());
				}
			}
		} catch (Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot(Navigated_View_Screen_Name + "_View_Screen_Not_Open");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}

	}

	public void fnsSwitchAcoount_MultiAccess(String Class_Name_for_AccountSwitch,
			String SwitchAccount_ColumnName_FromExcel_or_For_DataProvider_SwitchAccount_DD_Value) throws Throwable {
		// if(Login_Application_Name.toLowerCase().equals("nsf_connect")){
		try {
			String Switch_Account_CustomerName = null;
			boolean Login_User_Having_Multiaccess_Rights = false;
			boolean MultiAccess_Selection_Done = false;
			boolean Switch_Button = false;
			boolean Switch_DropDown = false;
			try {
				if ((Class_Name_for_AccountSwitch == null)
						&& !(SwitchAccount_ColumnName_FromExcel_or_For_DataProvider_SwitchAccount_DD_Value.toLowerCase()
								.trim().equals("no"))) {
					Switch_Account_CustomerName = SwitchAccount_ColumnName_FromExcel_or_For_DataProvider_SwitchAccount_DD_Value;
					Login_User_Having_Multiaccess_Rights = true;
				} else {
					Switch_Account_CustomerName = fnsReturn_ExcelCellValue_ByMatchingColumnName(
							Class_Name_for_AccountSwitch,
							SwitchAccount_ColumnName_FromExcel_or_For_DataProvider_SwitchAccount_DD_Value);
				}
				if (!(Switch_Account_CustomerName.trim().equals(""))
						&& !(SwitchAccount_ColumnName_FromExcel_or_For_DataProvider_SwitchAccount_DD_Value.toLowerCase()
								.trim().equals("no"))) {
					Login_User_Having_Multiaccess_Rights = true;
				}
			} catch (Throwable t) {
				fnsApps_Report_Logs(
						"*****=======+++++######----- 'Switch Account' Column entry is not mentioned into the EXCEL for sheet <"
								+ Class_Name_for_AccountSwitch + ">.");
			}

			if ((Login_User_Having_Multiaccess_Rights)) {
				for (int i = 0; i <= 60; i++) {
					try {
						if (driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("SwitchAccount_button")))
								.size() > 0) {
							if (fnsGet_OR_New_NSFOnline_ObjectX("SwitchAccount_button").isDisplayed()) {
								Switch_Button = true;
								fnsLoading_Progressing_wait(1);
								break;
							}
							/*
							 * }else if(driver.findElements(By.xpath(OR_New_NSFOnline.getProperty(
							 * "SwitchAccount_DD"))).size()>0){
							 * if(fnsGet_OR_New_NSFOnline_ObjectX("SwitchAccount_DD").isDisplayed()){
							 * Switch_DropDown = true;
							 * fnsLoading_Progressing_wait(1);
							 * break;
							 * }
							 * }
							 */
						} else if (driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("SwitchAccount_DD_Label")))
								.size() > 0) {
							if (fnsGet_OR_New_NSFOnline_ObjectX("SwitchAccount_DD_Label").isDisplayed()) {
								Switch_DropDown = true;
								fnsLoading_Progressing_wait(1);
								break;
							}
						} else {
							Thread.sleep(1000);
						}

					} catch (Throwable t) {
						if (i == 60) {
							// nothing to do
						} else {
							throw new Exception(Throwables.getStackTraceAsString(t));
						}
					}
				}

				if (Switch_Button) {
					fnsGet_Element_Enabled("SwitchAccount_button");
					fnsWait_and_Click("SwitchAccount_button");
					fnsLoading_Progressing_on_Popup_wait_for_Popup(2);

					fnsGet_Element_Enabled("SwitchAccount_DD_Label");
					/*
					 * Select DropDown = new
					 * Select(fnsGet_OR_New_NSFOnline_ObjectX("SwitchAccount_DD"));
					 * WebElement First_Selected_Value = DropDown.getFirstSelectedOption();
					 */
					WebElement First_Selected_Value = fnsGet_OR_New_NSFOnline_ObjectX("SwitchAccount_DD_Label");
					if ((First_Selected_Value.getText().toLowerCase().trim().equals("select account"))) {
						fnsTake_Screen_Shot("NoUserAccount_is_Selected_into_SwitchAccount_Popup");
						throw new Exception(
								"FAILED == Incorrect Value (Select Account) is coming into the  'switch account' drop down, instead of displaying <"
										+ Switch_Account_CustomerName
										+ "> into the drop down, Please refer the screen shot >> NoUserAccount_is_Selected_into_SwitchAccount_Popup"
										+ fnsScreenShot_Date_format());
					} else if ((First_Selected_Value.getText().trim().equals(Switch_Account_CustomerName))) {
						fnsWait_and_Click("SwitchAccount_Cancel_Bttn");
					} else {
						/*
						 * fnsDD_value_Select_TagOPTION_DDTypeSelect("SwitchAccount_DD",
						 * Switch_Account_CustomerName, 10);
						 */
						TestSuiteBase_MonitorPlan.WithOut_OR_fnDD_SelectValue_By_MatchingText(
								OR_New_NSFOnline.getProperty("SwitchAccount_DD_Label"),
								OR_New_NSFOnline.getProperty("SwitchAccount_DD"), "li", Switch_Account_CustomerName);
						fnsWait_and_Click("SwitchAccount_GO_Bttn");
					}
					fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
					MultiAccess_Selection_Done = true;
				} else if (Switch_DropDown) {
					// Below DD method is commented on 4.11.2019 as it is not working because drop
					// down is getting refresh after selecting value
					// TestSuiteBase_MonitorPlan.WithOut_OR_fnDD_SelectValue_By_MatchingText(OR_New_NSFOnline.getProperty("SwitchAccount_DD_Label"),
					// OR_New_NSFOnline.getProperty("SwitchAccount_DD"), "li",
					// Switch_Account_CustomerName);
					/*
					 * /boolean DD_Value_Selected = false; //try 5.11.2019 but not working
					 * for(int i=1; i<=10; i++){
					 * fnsDD_value_Select_By_MatchingText("SwitchAccount_DD_Label",
					 * "SwitchAccount_DD", Switch_Account_CustomerName);
					 * Thread.sleep(2000);
					 * String DD_SelectedValue =
					 * fnsGet_OR_New_NSFOnline_ObjectX("SwitchAccount_DD_Label").getText().trim();
					 * System.out.println(DD_SelectedValue+" = "+Switch_Account_CustomerName);
					 * if( (DD_SelectedValue.equals(Switch_Account_CustomerName)) ){
					 * fnsApps_Report_Logs("PASSED == Switch account value selected on "
					 * +i+" attamts.");
					 * DD_Value_Selected = true;
					 * break;
					 * }else{
					 * Thread.sleep(2000);
					 * }
					 * 
					 * if(i==10 && DD_Value_Selected == false){
					 * fnsTake_Screen_Shot("DD_Value_Select_Fail");
					 * throw new
					 * Exception("FAILED == after multiple attampts, switch account drop down value is not getting selected, Please refer the screen shot >> DD_Value_Select_Fail"
					 * +fnsScreenShot_Date_format());
					 * }
					 * }
					 */

					fnsDD_value_Select_By_MatchingText_and_SelectVerification("SwitchAccount_DD_Label",
							"SwitchAccount_DD", Switch_Account_CustomerName);
					fnsWait_and_Click("WelcomeScreen_SwitchHeaderBlue_Section");
					Thread.sleep(1500);
					fnsWait_and_Click("WelcomeScreen_SwitchAccount_GO_Bttn");
					fnsLoading_Progressing_wait(5);

					fnsGet_Element_Enabled("SwitchAccount_button");
					fnsWait_and_Click("SwitchAccount_button");
					fnsLoading_Progressing_on_Popup_wait_for_Popup(2);

					/*
					 * Select DropDown = new
					 * Select(fnsGet_OR_New_NSFOnline_ObjectX("SwitchAccount_DD"));
					 * WebElement First_Selected_Value = DropDown.getFirstSelectedOption();
					 */
					WebElement First_Selected_Value = fnsGet_OR_New_NSFOnline_ObjectX("SwitchAccount_DD_Label");
					if ((First_Selected_Value.getText().trim().equals(Switch_Account_CustomerName))) {
						fnsWait_and_Click("SwitchAccount_GO_Bttn");
					} else {
						fnsTake_Screen_Shot("Selected_User_Not_Display_into_SwitchAccount_Popup");
						throw new Exception("FAILED == After selection <" + Switch_Account_CustomerName
								+ "> from switch account drop down on 'welcome screen', The selected value <"
								+ Switch_Account_CustomerName +
								"> is not displayed into the switch account drop down on 'Switch Acccount' Popup, Please refer the screen shot >> Selected_User_Not_Display_into_SwitchAccount_Popup"
								+ fnsScreenShot_Date_format());
					}
					fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
					MultiAccess_Selection_Done = true;
				} else {
					fnsLoading_Progressing_wait(1);
				}

				if (MultiAccess_Selection_Done == true) {
					fnsLoading_Progressing_wait(1);
					fnsApps_Report_Logs("PASSED == Multi access selection is done.");
					fnsLoading_Progressing_wait_AlertLaoder(2);
					fnsApps_Report_Logs(
							"=========================================================================================================================================");
				} else {
					fnsTake_Screen_Shot("SwitchAccount_Not_Display");
					throw new Exception(
							"FAILED == 'Switch Account' Drop down is NOT coming even though the user is having multi access rights, Please refer the screen shot >> SwitchAccount_Not_Display"
									+ fnsScreenShot_Date_format());
				}

			} else if ((driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("SwitchAccount_button"))).size() > 0)
					|| (driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("SwitchAccount_DD"))).size() > 0)) {
				fnsTake_Screen_Shot("UnNecessary_SwitchAccount_Display");
				throw new Exception(
						"FAILED == 'Switch Account' Drop down is displayed even though the user doesn't have multi access rights, Please refer the screen shot >> UnNecessary_SwitchAccount_Display"
								+ fnsScreenShot_Date_format());
			}

		} catch (NoSuchWindowException W) {
			// isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		// }
	}

	public boolean fns_Verify_and_Return_ScreenName(String ScreenName, boolean JustAfterLogin) throws Throwable {
		String Get_Redirected_ScreenName = "";
		boolean CorrectScreenRedirectDone = false;
		if (ScreenName.toLowerCase().trim().contains("alert")) {
			fnsLoading_Progressing_wait_AlertLaoder(2);
		}
		fnsLoading_Progressing_wait(2);
		try {
			for (int wait = 0; wait <= ((NewNsfOnline_Element_Max_Wait_Time)); wait++) {
				if (CorrectScreenRedirectDone || (wait == NewNsfOnline_Element_Max_Wait_Time)) {
					break;
				} else {
					if ((ScreenName.toLowerCase().trim().equals("facility_snapshot"))
							|| (ScreenName.toLowerCase().trim().equals("multifacility_acess"))) {
						Get_Redirected_ScreenName = driver.getCurrentUrl().toLowerCase().trim();
					} else {
						Get_Redirected_ScreenName = TestSuiteBase_MonitorPlan
								.WithOut_OR_fnsGet_Field_TEXT(OR_New_NSFOnline.getProperty("View_ScreenName_title"));
					}
					if (Get_Redirected_ScreenName.length() > 2) {
						if (Get_Redirected_ScreenName.toLowerCase().contains(ScreenName.toLowerCase())) {
							fnsLoading_Progressing_wait(2);
							CorrectScreenRedirectDone = true;
						} else {
							CorrectScreenRedirectDone = false;
						}
					} else {
						fnsLoading_Progressing_wait(1);
					}
				}
			}
			if (JustAfterLogin) {
				if (CorrectScreenRedirectDone == true) {
					fnsApps_Report_Logs("PASSED == After Login, the Landing (" + Get_Redirected_ScreenName
							+ ") Screen is coming  without any error and which is match with the Expected ("
							+ ScreenName + ") screen. - automation");
				} else {
					fnsTake_Screen_Shot(ScreenName + "_Screen_Not_Open");
					throw new Exception("FAILED == After Login, the Landing '" + Get_Redirected_ScreenName
							+ "' Screen is coming instead of displaying Expected (" + ScreenName
							+ ") screen, please refer screen shot >> " + ScreenName + "_Screen_Not_Open"
							+ fnsScreenShot_Date_format());
				}
			}

		} catch (Throwable t) {
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		return CorrectScreenRedirectDone;
	}

	public void fns_UploadFile(String Upload_Bttn, String BrowseBttnXpath) throws Throwable {
		try {
			fnsGet_Element_Enabled(Upload_Bttn);
			fnsWait_and_Click(Upload_Bttn);
			fnsLoading_Progressing_on_Popup_wait_for_Popup(2);

			String FileUploadPath = System.getProperty("user.dir") + "\\docs\\This is test pdf document.pdf";
			WebElement Browser = fnsGet_OR_New_NSFOnline_ObjectX(BrowseBttnXpath);
			Browser.sendKeys(FileUploadPath);

			Thread.sleep(1000);
			fnsWait_and_Click_on_RadioButton_by_LabelName(true, "Does the document contains any personal Information?",
					"1");

			fnsGet_Element_Enabled("CreateIssue_DocumentInfo_Tab_Upload_Add_and_Close_bttn");
			fnsWait_and_Click("CreateIssue_DocumentInfo_Tab_Upload_Add_and_Close_bttn");

			Thread.sleep(2000);
			fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
			fnsApps_Report_Logs("PASSED == File uploaded successfully.");
		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new SkipException(W.getMessage());
		} catch (Throwable t) {
			isTestCasePass = false;
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}

	// Function to Only verification of screen navigation without clicking.
	public void fnsVerifyScreenNavigation_afterClickingOnElement(String Current_Click_Element_Name,
			String Next_Screen_Name, String Next_Screen_Element_Xpath_WithoutOR) throws Throwable {
		try {
			for (int wait = 0; wait <= ((NewNsfOnline_Element_Max_Wait_Time)); wait++) {
				try {
					if (driver.findElements(By.xpath(Next_Screen_Element_Xpath_WithoutOR)).size() > 0) {
						if (driver.findElement(By.xpath(Next_Screen_Element_Xpath_WithoutOR)).isDisplayed()) {
							fnsApps_Report_Logs("PASSED == After clicking on " + Current_Click_Element_Name
									+ " , application successfully navigated to '" + Next_Screen_Name + "' .");
							fnsLoading_Progressing_wait(1);
							fnsLoading_Progressing_wait(1);
							break;
						} else {
							Thread.sleep(1000);
						}
					} else {
						Thread.sleep(1000);
					}
					if (wait == ((NewNsfOnline_Element_Max_Wait_Time))) {
						throw new Exception();
					}

				} catch (Throwable t) {
					if (wait == ((NewNsfOnline_Element_Max_Wait_Time))) {
						fnsTake_Screen_Shot("" + Next_Screen_Name + "_Navigation_Fail");
						throw new Exception("FAILED == After clicking on " + Current_Click_Element_Name
								+ " , application  NOT  navigated to '" + Next_Screen_Name + "' , [ Wait Time ("
								+ ((NewNsfOnline_Element_Max_Wait_Time)) + ") seconds ], please refer screen shot >> "
								+ Next_Screen_Name + "_Navigation_Fail" + fnsScreenShot_Date_format()
								+ Throwables.getStackTraceAsString(t));
					} else {
						fnsLoading_Progressing_wait(1);
						// Thread.sleep(1000);
					}
				}
			}
		} catch (Throwable t) {
			// isTestCasePass = false;
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}

	public void fnsDD_value_Select_By_MatchingText(String ddClickXpathKey, String ddListXpathKey, String Value)
			throws Throwable {
		boolean ValueNotMatched = true;
		List<WebElement> objectlist5 = null;
		for (int i = 1; i <= (5); i++) {
			try {
				for (int k = 1; k <= (20); k++) {
					fnsGet_Element_Enabled(ddClickXpathKey);
					fnsWait_and_Click(ddClickXpathKey);

					objectlist5 = fnsGet_OR_New_NSFOnline_ObjectX(ddListXpathKey).findElements(By.tagName("li"));
					if (objectlist5.size() > 1) {
						break;
					} else {
						Thread.sleep(2000);
						;
					}
					if (k == 20) {
						throw new Exception("Drop down is not getting load, after 40 seconds wait.");
					}
				}
				fnsApps_Report_Logs(
						"PASSED == Count of No. of values coming in Drop down list is = " + objectlist5.size());
				for (WebElement dd_value : objectlist5) {
					if (dd_value.getText().equals(Value)) {
						dd_value.click();
						ValueNotMatched = false;
						break;
					}
				}
				if (ValueNotMatched == true) {
					throw new Exception("Excel value is not matched with DropDown Value.");
				} else {
					fnsApps_Report_Logs("PASSED == Successfully select value [ " + Value
							+ " ] from drop down, having xpath >>  " + ddClickXpathKey);
					break;
				}
			} catch (NoSuchWindowException W) {
				isTestCasePass = false;
				throw new Exception(W.getMessage());
			} catch (Throwable t) {
				if (i == 5) {
					isTestCasePass = false;
					fnsTake_Screen_Shot("DdValueSelectFail" + ddClickXpathKey);
					fnsApps_Report_Logs("FAILED == Unable to select value from drop down [ " + ddClickXpathKey
							+ fnsScreenShot_Date_format() + " ]" + ". Getting Exception  >> "
							+ Throwables.getStackTraceAsString(t).trim());
					throw new Exception("FAILED == Unable to select value from drop down [ " + ddClickXpathKey
							+ " ],plz see screenshot [ DdValueSelectFail" + ddClickXpathKey
							+ fnsScreenShot_Date_format() + " ]" + ". Getting Exception  >> "
							+ Throwables.getStackTraceAsString(t).trim());
				} else {
					Thread.sleep(2000);
					;
				}
			}
		}
	}

	public void fnsDD_value_Select_By_MatchingText_and_SelectVerification(String ddClickXpathKey, String ddListXpathKey,
			String Value) throws Throwable {
		boolean ValueNotMatched = true;
		boolean DD_Selected_Verification = false;
		List<WebElement> objectlist5 = null;

		for (int i = 1; i <= 10; i++) {
			try {
				for (int k = 1; k <= (20); k++) {
					fnsGet_Element_Enabled(ddClickXpathKey);
					fnsWait_and_Click(ddClickXpathKey);
					Thread.sleep(1000);
					fnsGet_Element_Enabled(ddListXpathKey);
					fnsGet_Element_Displayed(ddListXpathKey);
					objectlist5 = fnsGet_OR_New_NSFOnline_ObjectX(ddListXpathKey).findElements(By.tagName("li"));
					if (objectlist5.size() > 1) {
						break;
					} else {
						Thread.sleep(2000);
						;
					}
					if (k == 20) {
						throw new Exception("Drop down is not getting load, after 40 seconds wait.");
					}
				}
				fnsApps_Report_Logs(
						"PASSED == Count of No. of values coming in Drop down list is = " + objectlist5.size());

				for (WebElement dd_value : objectlist5) {
					if (dd_value.getText().equals(Value)) {
						dd_value.click();
						Thread.sleep(500);
						ValueNotMatched = false;
						break;
					}
				}
				if (ValueNotMatched == true) {
					throw new Exception("Excel value is not matched with DropDown Value.");
				} else {
					fnsApps_Report_Logs("PASSED == Successfully select value [ " + Value
							+ " ] from drop down, having xpath >>  " + ddClickXpathKey);
				}

				fnsGet_Element_Enabled(ddClickXpathKey);
				fnsWait_and_Click(ddClickXpathKey);
				fnsGet_Element_Enabled(ddListXpathKey);
				fnsGet_Element_Displayed(ddListXpathKey);
				objectlist5 = fnsGet_OR_New_NSFOnline_ObjectX(ddListXpathKey).findElements(By.tagName("li"));

				for (WebElement dd_value : objectlist5) {
					String Selected_Attribute = dd_value.getAttribute("class").trim();

					if (Selected_Attribute.equalsIgnoreCase("k-item ng-scope k-state-selected k-state-focused")) {
						DD_Selected_Verification = true;
						break;
					}
				}
				if (DD_Selected_Verification == false) {
					throw new Exception("Selected value is not displaying into the DropDown.");
				} else {
					fnsApps_Report_Logs("PASSED == Successfully Verified that the selected value [ " + Value
							+ " ] is displayed into the drop down.");
					break;
				}

			} catch (NoSuchWindowException W) {
				isTestCasePass = false;
				throw new Exception(W.getMessage());
			} catch (Throwable t) {
				if (i == 10) {
					isTestCasePass = false;
					fnsTake_Screen_Shot("DdValueSelectFail" + ddClickXpathKey);
					fnsApps_Report_Logs("FAILED == Unable to select value from drop down [ " + ddClickXpathKey
							+ " ],plz see screenshot [ DdValueSelectFail" + ddClickXpathKey
							+ fnsScreenShot_Date_format() + " ]" + ". Getting Exception  >> "
							+ Throwables.getStackTraceAsString(t).trim());
					throw new Exception("FAILED == Unable to select value from drop down [ " + ddClickXpathKey
							+ " ],plz see screenshot [ DdValueSelectFail" + ddClickXpathKey
							+ fnsScreenShot_Date_format() + " ]" + ". Getting Exception  >> "
							+ Throwables.getStackTraceAsString(t).trim());
				} else {
					Thread.sleep(2000);
					;
				}
			}
		}
	}

	// ###################################################################
	// Configuration Function
	// ############################################################################
	// Function to Launch browser and login
	@SuppressWarnings("finally")
	public boolean fnsBrowserLaunchAndLogin(String UserName, String Password) throws Throwable {
		boolean present = false;
		try {
			if (Login_Application_Name.toLowerCase().contains("ipulse")) {
				present = fnsBrowserLaunchAndLogin_into_iPulse_then_Open_New_NsfOnline_Application(UserName);
			} else if (Login_Application_Name.toLowerCase().equals("nsf_connect")) {
				present = fnsBrowserLaunchAndLogin_into_the_New_NsfOnline_Application(UserName, Password);
			}
			fnsLoading_Progressing_wait_AlertLaoder(2);
			return present;
		} catch (Throwable t) {
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}

	// Function to Launch browser and login - New NsfOnline
	@SuppressWarnings("finally")
	public boolean fnsBrowserLaunchAndLogin_into_the_New_NsfOnline_Application(String UserName, String Password)
			throws Throwable {
		boolean present = false;
		startExecutionTime = fnpTimestamp();
		ScreenShotFlagWithOR_New_NSFOnline = true;

		try {
			present = TestSuiteBase_MonitorPlan.fns_Launch_Browser_Only();
			fnsApps_Report_Logs("Browser Launch Successfully.");
			// start = new Date();

			String siteUrl = null;

			siteUrl = CONFIG.getProperty("New_NSFOnline_SiteName").trim();

			fnsApps_Report_Logs("Application credentials are URL[ " + siteUrl + " ], UserName[ " + UserName + " ].");
			driver.get(siteUrl);
			fnsApps_Report_Logs(driver.getTitle());
			try {
				TestSuiteBase_MonitorPlan.fnsComman_BrowserLogin_ElementWait(OR_New_NSFOnline.getProperty("UserName"),
						"60");
			} catch (Throwable t) {
				throw new Exception(".... Application [ " + siteUrl
						+ " ] is not getting open, after 60 seconds wait, please refer the screen shot >> LoginFail"
						+ fnsScreenShot_Date_format());
			}

			TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(OR_New_NSFOnline.getProperty("UserName")).clear();
			TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(OR_New_NSFOnline.getProperty("UserName")).sendKeys("");
			TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(OR_New_NSFOnline.getProperty("UserName"))
					.sendKeys(UserName);
			TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(OR_New_NSFOnline.getProperty("Password")).sendKeys("");
			TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(OR_New_NSFOnline.getProperty("Password"))
					.sendKeys(Password);

			for (int i = 1; i <= 10; i++) {
				if (i == 10) {
					throw new Exception(" Due to Page Loading, Clicking on 'Login Button' is failed."
							+ " , Please refer the screen shot >> LoginFail" + fnsScreenShot_Date_format());
				} else {
					Thread.sleep(1000);
				}
				try {
					TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(OR_New_NSFOnline.getProperty("Login_Bttn"))
							.click();
					break;
				} catch (Throwable t) {
					// nothing to do.
				}

			}

			fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
			fnsLoading_PageLoad(600);
			boolean Force_Password_Screen_Coming = false;
			Thread.sleep(2000);
			// fnsLoading_wait_forAll(3);
			// fnsLoading_Progressing_wait(3);// commented because Acknowledge popup is
			// coming 30.3.2019

			try {
				for (int i = 0; i <= NewNsfOnline_Element_Max_Wait_Time; i++) {
					try {
						// Popup_Coming = false;
						if (i == NewNsfOnline_Element_Max_Wait_Time) {
							throw new Exception("After Login, Home Page is not getting load after <"
									+ NewNsfOnline_Element_Max_Wait_Time + ">seconds wait."
									+ " , Please refer the screen shot >> LoginFail" + fnsScreenShot_Date_format());
						} else {
							Thread.sleep(1000);
						}
						if ((driver
								.findElements(By.xpath(OR_New_NSFOnline.getProperty("MyProfile_ChangePassword_button")))
								.size() > 0)) {
							TestSuiteBase_MonitorPlan.fnsComman_BrowserLogin_ElementWait(
									OR_New_NSFOnline.getProperty("MyProfile_ChangePassword_button"),
									CONFIG.getProperty("Element_MAX_WaitTime"));
							Force_Password_Screen_Coming = true;
							break;
						}
						if ((driver
								.findElements(
										By.xpath(OR_New_NSFOnline.getProperty("NewNsfOnline_Login_ValidationMessage")))
								.size() > 0)) {
							String ErrorMsg = TestSuiteBase_MonitorPlan
									.WithOut_OR_fnGet_ObjectX(
											OR_New_NSFOnline.getProperty("NewNsfOnline_Login_ValidationMessage"))
									.getText();
							throw new Exception("Getting Validation Error <" + ErrorMsg + ">"
									+ " , Please refer the screen shot >> LoginFail" + fnsScreenShot_Date_format());
						}
						if ((driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("Sign_Out"))).size() > 0)
								|| driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("SwitchAccount_DD")))
										.size() > 0) {
							TestSuiteBase_MonitorPlan.fnsComman_BrowserLogin_ElementWait(
									OR_New_NSFOnline.getProperty("Sign_Out"),
									CONFIG.getProperty("Element_MAX_WaitTime"));
							break;
						}
						try {
							if ((driver
									.findElement(
											By.xpath(OR_New_NSFOnline.getProperty("NewNsfOnline_Content_Model_Popup")))
									.isDisplayed())) {
								break;
							}
						} catch (Throwable t) {
							Thread.sleep(250);
						}
					} catch (StaleElementReferenceException st) {
						Thread.sleep(500);
					}

				}
				Thread.sleep(1000);
				boolean AcknowlwdgeComing = false;
				if ((Force_Password_Screen_Coming == false)) {
					for (int i = 0; i <= 120; i++) {
						try {
							if ((driver
									.findElement(
											By.xpath(OR_New_NSFOnline.getProperty("NewNsfOnline_Content_Model_Popup")))
									.isDisplayed())) {
								fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
								String ErrorMsg = TestSuiteBase_MonitorPlan
										.WithOut_OR_fnGet_ObjectX(
												OR_New_NSFOnline.getProperty("NewNsfOnline_Content_Model_Popup"))
										.getText().trim();
								if (ErrorMsg.toLowerCase().contains(
										"This is a non-production environment - Do NOT upload sensitive, confidential, NSF or customer data"
												.toLowerCase())) {
									fnsApps_Report_Logs("PASSED == Acknowledge Popup is coming.");
									AcknowlwdgeComing = true;
									break;
								} else {
									throw new Exception("Getting Popup Error <" + ErrorMsg + ">"
											+ " , Please refer the screen shot >> LoginFail"
											+ fnsScreenShot_Date_format());
								}
							} else {
								Thread.sleep(1000);
							}
						} catch (Throwable t) {
							Thread.sleep(500);
						}
						if (i == 120) {
							fnsTake_Screen_Shot("AcknowledgeAlertPopup_NotComing");
							throw new Exception(
									"Acknowledge Alert Popup is NOT coming just after login through ipulse, Please refer the screen shot >> AcknowledgeAlertPopup_NotComing"
											+ fnsScreenShot_Date_format());
						}
					}
				}
				if (AcknowlwdgeComing) {
					fnsGet_Element_Enabled("Acknowledge_Acknowledge_bttn");
					fnsWait_and_Click("Acknowledge_Acknowledge_bttn");
					Thread.sleep(2000);
				}
			} catch (Throwable t) {
				if (driver.getTitle().toLowerCase().trim().contains("nsfonline")) {
					throw new Exception("'NsfOnline 1.0' is getting open instead of 'NsOnline 2.0'"
							+ " , Please refer the screen shot >> LoginFail" + fnsScreenShot_Date_format());
				} else {
					throw new Exception(Throwables.getStackTraceAsString(t));
				}
			}

			if (!veryFirstTimeAfterLogin) {
				veryFirstTimeAfterLogin = true;
			}
			/*
			 * if(ApplicationVersion_Flag){
			 * TestSuiteBase_NSFOnline.fnsNew_NsfOnline_Application_Version();
			 * ApplicationVersion_Flag = false;
			 * }
			 */
			if (Force_Password_Screen_Coming == false) {
				TestSuiteBase_MonitorPlan.fnsComman_BrowserLogin_ElementWait(
						OR_New_NSFOnline.getProperty("NewNsfOnline_Footer"),
						CONFIG.getProperty("Element_MAX_WaitTime"));
				fnsLoading_Progressing_on_Popup_wait_for_Popup(1);
				fnsLoading_Progressing_on_Popup_wait_for_Popup(1);
			}
			fnsApps_Report_Logs("Browser is launched and Successfully login into the NsfOnline 2.0 application");
			fnsApps_Report_Logs(
					"=========================================================================================================================================");
			return present;
		} catch (NoSuchWindowException W) {
			// isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			fnsTake_Screen_Shot("LoginFail");
			fnsApps_Report_Logs("");
			fnsApps_Report_Logs(" LOGIN FAILED == " + Throwables.getStackTraceAsString(t));
			throw new Exception(" LOGIN FAILED == " + Throwables.getStackTraceAsString(t));
		}
	}

	// Function to lounch browser and verify login Authentication for invalid
	// password
	public void fnsVerify_Login_Authentication(String UserName) throws Throwable {
		try {
			if (Verify_Login_Authentication_Done == true) {
				Verify_Login_Authentication_Done = false;
				TestSuiteBase_MonitorPlan.fns_Launch_Browser_Only();

				String siteUrl = null;

				if (excelSiteURL != null) {
					if (excelSiteURL.equalsIgnoreCase("")) {
						siteUrl = CONFIG.getProperty("New_NSFOnline_SiteName").trim();
					} else {
						siteUrl = excelSiteURL.trim();

					}
				} else {
					siteUrl = CONFIG.getProperty("New_NSFOnline_SiteName").trim();
				}

				fnsApps_Report_Logs("Application credentials are URL [ " + siteUrl + " ], UserName [ " + UserName
						+ " ] & Password [ AutomationTest ].");
				driver.get(siteUrl);

				TestSuiteBase_MonitorPlan.fnsComman_BrowserLogin_ElementWait(OR_New_NSFOnline.getProperty("UserName"),
						CONFIG.getProperty("Element_MAX_WaitTime"));

				TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(OR_New_NSFOnline.getProperty("UserName")).clear();
				TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(OR_New_NSFOnline.getProperty("UserName"))
						.sendKeys("");
				TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(OR_New_NSFOnline.getProperty("UserName"))
						.sendKeys(UserName);
				TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(OR_New_NSFOnline.getProperty("Password"))
						.sendKeys("");
				TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(OR_New_NSFOnline.getProperty("Password"))
						.sendKeys("PassIncorrect");

				for (int i = 1; i <= 10; i++) {
					if (i == 10) {
						throw new Exception(" Due to Page Loading, Clicking on 'Login Button' is failed.");
					} else {
						Thread.sleep(1000);
					}
					try {
						TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(OR_New_NSFOnline.getProperty("Login_Bttn"))
								.click();
						break;
					} catch (Throwable t) {
						// nothing to do.
					}

				}

				for (int i = 0; i <= NewNsfOnline_Element_Max_Wait_Time; i++) {
					try {
						if (i == NewNsfOnline_Element_Max_Wait_Time) {
							throw new Exception(
									"FAILED == Nothin happened, after clicking on 'Login' button, please refer screen shot >> Login_Authentication_Fail"
											+ fnsScreenShot_Date_format());
						}

						if ((driver
								.findElements(
										By.xpath(OR_New_NSFOnline.getProperty("NewNsfOnline_Login_ValidationMessage")))
								.size() > 0)) {
							fnsApps_Report_Logs("PASSED == Login Authentication is working fine. (Text Validation)");
							break;
						} else {
							try {
								fnsLoading_Progressing_wait(1);
							} catch (Throwable t) {
								// fnsApps_Report_Logs("PASSED == Login Authentication is working fine. (Popup
								// Validation)");
								fnsApps_Report_Logs(
										"PASSED == Login Authentication (Enable)  is working fine for wrong password. Please ignore the abvoe message as it is expected. "
												+
												"Application credentials are URL [ " + siteUrl + " ], UserName [ "
												+ UserName + " ] & Password [ AutomationTest ].");
								break;
							}
						}

						if ((driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("Sign_Out"))).size() > 0)
								|| driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("SwitchAccount_DD")))
										.size() > 0) {
							throw new Exception("FAILED == for incorrect 'PASSWORD', user<" + UserName
									+ "> got login, which is NOT expected, please refer screen shot >> Login_Authentication_Fail"
									+ fnsScreenShot_Date_format());
						}
					} catch (StaleElementReferenceException st) {
						Thread.sleep(500);
					}
				}

				driver.quit();
				isTestCasePass = true;
				fnsApps_Report_Logs("");
			}

		} catch (NoSuchWindowException W) {
			isTestCasePass = true;
			// isTestCasePass = false; // commented because excel should be green if fail
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			isTestCasePass = true;
			// isTestCasePass = false; // commented because excel should be green if fail
			fnsTake_Screen_Shot("Login_Authentication_Fail");
			driver.quit();
			fnsApps_Report_Logs("'Login Authentication Fail' : " + Throwables.getStackTraceAsString(t));
			fnsApps_Report_Logs("");
			throw new Exception("'Login Authentication Fail' : " + Throwables.getStackTraceAsString(t));
		}
	}

	// Function to Launch browser and login -- iPulse
	@SuppressWarnings("finally")
	public boolean fnsBrowserLaunchAndLogin_into_iPulse_then_Open_New_NsfOnline_Application(String UserName)
			throws Throwable {
		boolean present = false;
		String WayTo_NsfOnlineApplicationOpen_from_iPulse = "";
		String NsfOnlineOpen_User = "";
		ScreenShotFlagWithOR_New_NSFOnline = true;
		User_DefaultCustomerAccount_COCL = null;
		boolean NsfOnline_Open_through_linkClick = false;
		boolean Nsfonline_Account_Setup_Application = false;
		User_COCL_Account = null;

		startExecutionTime = fnpTimestamp();

		try {
			try {
				present = TestSuiteBase_MonitorPlan.fns_Launch_Browser_Only();
				// TestSuiteBase_MonitorPlan.fnsIpulse_Login_Split_Excel_Urls("iPulse_NewNsfOnline",
				// null, CONFIG.getProperty("adminUsername"),
				// CONFIG.getProperty("adminPassword"));
				TestSuiteBase_MonitorPlan.fnsIpulse_Login_SSO("iPulse_NsfConnect", "",
						CONFIG.getProperty("adminUsername"), CONFIG.getProperty("adminPassword"));
				fnsApps_Report_Logs("Browser is launched and Successfully login into the 'iPulse' Application");
			} catch (Throwable t) {
				fnsTake_Screen_Shot("iPulse_Login_Fail");
				throw new Exception(Throwables.getStackTraceAsString(t));
			}

			try {
				if ((Login_Application_Name.toLowerCase().trim().equals("ipulse_servicerequests_alert"))) {
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
					fnsGet_Element_Enabled("iPulse_Alert_SecificUser_Radio");
					fnsWait_and_Click("iPulse_Alert_SecificUser_Radio");
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
					fnsGet_Element_Enabled("iPulse_Alert_SecificUser_Lookup_bttn");
					fnsWait_and_Click("iPulse_Alert_SecificUser_Lookup_bttn");
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(3);
					fnsGet_Element_Enabled("iPulse_Alert_SecificUser_Lookup_UserName_text");
					fnsWait_and_Type("iPulse_Alert_SecificUser_Lookup_UserName_text", Login_UserName);
					TestSuiteBase_MonitorPlan.fnsLookup_RadioBttn_Select();
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
					fnsGet_Element_Enabled("iPulse_Alert_SecificUser_ShowAlert");
					fnsWait_and_Click("iPulse_Alert_SecificUser_ShowAlert");
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
					fnsGet_Element_Enabled("iPulse_Alert_SecificUser_ServiceAlert");
					fnsWait_and_Click("iPulse_Alert_SecificUser_ServiceAlert");
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
					fnsGet_Element_Enabled("iPulse_Alert_SecificUser_ServiceAlert_SubjectColumn_FirstLink");
					fnsWait_and_Click("iPulse_Alert_SecificUser_ServiceAlert_SubjectColumn_FirstLink");
				} else {
					try {
						String Query = "select cus.code from cw_user_owners cuo, customers cus where cuo.cocl_seq = cus.seq and cuo.user_seq in (select seq from cw_users where username='"
								+ UserName.toLowerCase() + "') and cuo.active_flg='Y'";
						Connection connection = null;
						try {
							Class.forName("oracle.jdbc.driver.OracleDriver");
							connection = fnpGetDBConnection();
							Statement stmt = connection.createStatement();

							ResultSet rs = stmt.executeQuery(Query);

							while (rs.next()) {
								User_DefaultCustomerAccount_COCL = rs.getString("code");
								break;
							}

							connection.commit();
							connection.close();

						} catch (SQLException e) {
							fnsApps_Report_Logs("Data base Error : " + Throwables.getStackTraceAsString(e).trim());
							throw new Exception("Data base Error : " + Throwables.getStackTraceAsString(e).trim());
						} finally {
							if (!(connection == null)) {
								connection.close();
							}
						}

						if ((User_DefaultCustomerAccount_COCL == null)
								|| (User_DefaultCustomerAccount_COCL.equals(""))) {
							throw new Exception("FAILED == 'COCl_Customer_Code' is not exists for user '" + UserName
									+ "' into the DB, result got from query (" + Query + ").");
						} else {
							User_COCL_Account = User_DefaultCustomerAccount_COCL;
							fnsApps_Report_Logs("**** Query1 (" + Query
									+ ") Executed Successfully and the COCl Customer Code is = ("
									+ User_DefaultCustomerAccount_COCL + ") for user '" + UserName + "'");
						}
					} catch (Throwable t) {
						throw new Exception(Throwables.getStackTraceAsString(t));
					}

					/* User_DefaultCustomerAccount_COCL = fnsgetCOCL(UserName); */
					String Corp_Facility_Link_Xpath = "//a[text()='" + User_DefaultCustomerAccount_COCL + "']";
					fnsMenu_Ajax_Link_Click_By_PassingAjaxPath_iPulse("iPulse_SearchClient_Ajax");
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(3);

					fnsGet_Element_Enabled("iPulse_Footer");
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
					fnsGet_Element_Enabled("iPulse_SearchClient_CorpFacility_Input");
					fnsWait_and_Clear("iPulse_SearchClient_CorpFacility_Input");
					fnsWait_and_Type("iPulse_SearchClient_CorpFacility_Input", User_DefaultCustomerAccount_COCL);
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);

					fnsGet_Element_Enabled("iPulse_SearchClient_Search_Bttn");
					fnsWait_and_Click("iPulse_SearchClient_Search_Bttn");
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(5);

					fnsWait_Click_on_First_Visible_Element_if_More_than_One_Coming(
							"'" + User_DefaultCustomerAccount_COCL + "' link", Corp_Facility_Link_Xpath);

					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
					fnsGet_Element_Enabled("iPulse_ViewClient_Edit_Bttn");
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);

					TestSuiteBase_MonitorPlan.fsnClickOn_Screen_TAB_Pagination_NEXT_Link_Till_DisAppear();

					fnsGet_Element_Enabled("iPulse_ViewClient_NSFOnline2.0Accounts_TAB");
					fnsWait_and_Click("iPulse_ViewClient_NSFOnline2.0Accounts_TAB");
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(5);

					WayTo_NsfOnlineApplicationOpen_from_iPulse = Login_Application_Name.split("_")[1].trim()
							.toLowerCase();

					switch (WayTo_NsfOnlineApplicationOpen_from_iPulse) {
						case ("mentioneduser"): {
							NsfOnlineOpen_User = UserName.toLowerCase().trim();
							NsfOnline_Open_through_linkClick = true;
							break;
						}
						case ("superadmin"): {
							NsfOnlineOpen_User = User_DefaultCustomerAccount_COCL.toLowerCase() + "superadmin";
							NsfOnline_Open_through_linkClick = true;
							break;
						}
						case ("superadminlogin"): {
							fnsGet_Element_Enabled("iPulse_ViewClient_NSFOnline2.0Accounts_TAB_SuperAdminLogin_Bttn");
							TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
							fnsWait_and_Click("iPulse_ViewClient_NSFOnline2.0Accounts_TAB_SuperAdminLogin_Bttn");
							break;
						}
						case ("nsfonlineaccountsetup"): {
							fnsGet_Element_Enabled(
									"iPulse_ViewClient_NSFOnline2.0Accounts_TAB_NSFOnlineAccountSetup_Bttn");
							TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
							fnsWait_and_Click("iPulse_ViewClient_NSFOnline2.0Accounts_TAB_NSFOnlineAccountSetup_Bttn");
							TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
							Nsfonline_Account_Setup_Application = true;
							break;
						}
					}

					if (NsfOnline_Open_through_linkClick == true) {
						boolean TypeDone_into_UserName_Filter = false;
						boolean Need_To_SearchUseFilter = true;
						Integer Column_No = 1;
						String HeaderTableXpath = "";
						String HeaderTableXpath1 = "//thead[@id='mainForm:tabView:userOwner_head']/tr[1]";
						String HeaderTableXpath2 = "//thead[@id='mainForm:tabView:cwUserSr_head']/tr[1]";
						String NsfOnlineUser_link_xpath = "//a[text()='" + NsfOnlineOpen_User + "']";

						for (int s = 0; s <= 120; s++) {
							try {
								if (driver.findElements(By.xpath(NsfOnlineUser_link_xpath)).size() > 0) {
									Need_To_SearchUseFilter = false;
									break;
								} else if (driver.findElements(By.xpath(HeaderTableXpath1)).size() > 0) {
									HeaderTableXpath = HeaderTableXpath1;
									break;
								} else if (driver.findElements(By.xpath(HeaderTableXpath2)).size() > 0) {
									HeaderTableXpath = HeaderTableXpath2;
									break;
								} else {
									Thread.sleep(250);
								}
							} catch (Throwable t) {
								Thread.sleep(250);
							}
						}

						if (Need_To_SearchUseFilter) {
							try {
								TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(HeaderTableXpath);
								WebElement HeaderTableAllObj = TestSuiteBase_MonitorPlan
										.WithOut_OR_fnGet_ObjectX(HeaderTableXpath);
								List<WebElement> NoOfHeadinglist = HeaderTableAllObj.findElements(By.tagName("th"));
								for (WebElement HeaderTableEle : NoOfHeadinglist) {
									String ColumnHeadingText = HeaderTableEle.getText().toLowerCase().trim();
									if (ColumnHeadingText.contains("user name")) {
										List<WebElement> Input_Field_obj = HeaderTableEle
												.findElements(By.tagName("input"));
										for (WebElement Input_Field_ele : Input_Field_obj) {
											Input_Field_ele.sendKeys(NsfOnlineOpen_User);
											TypeDone_into_UserName_Filter = true;
											Thread.sleep(2000);
											break;
										}
										if (TypeDone_into_UserName_Filter == true) {
											break;
										}
									}
									Column_No++;
								}
								if (TypeDone_into_UserName_Filter == false) {
									throw new Exception(
											"FAILED == 'User Name' search filter is not coming, Please refer the screen shot >> UserName_Filter_Not_Coming"
													+ fnsScreenShot_Date_format());
								}
							} catch (Throwable t) {
								fnsApps_Report_Logs("UserName_Filter_Not_Coming");
								throw new Exception(Throwables.getStackTraceAsString(t));
							}
							NsfOnlineUser_link_xpath = "//tr[1]/td/a[text()='" + NsfOnlineOpen_User + "']";
						}

						fnsGet_Element_Enabled("iPulse_ViewClient_NSFOnline2.0Accounts_TAB_SuperAdminLogin_Bttn");
						TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
						TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(NsfOnlineUser_link_xpath);
						TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element(NsfOnlineUser_link_xpath);
						fnsWait_Click_on_First_Visible_Element_if_More_than_One_Coming(
								"'" + NsfOnlineOpen_User + "' link", NsfOnlineUser_link_xpath);
					}
				}

				TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(5);

				iPulse_Original_WindowHandle = driver.getWindowHandle();
				NsfConncet_Window_Handle = fnsWindowSwitch_Switch_and_return_NsfOnline2_windowHandle_OpenThrough_iPulse(
						iPulse_Original_WindowHandle);
			} catch (Throwable t) {
				throw new Exception("iPulse : " + Throwables.getStackTraceAsString(t));
			}

			/*
			 * if(Nsfonline_Account_Setup_Application){
			 * fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
			 * }else{
			 * fnsLoading_Progressing_wait(2);
			 * }
			 */
			fnsLoading_PageLoad(600);
			fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
			boolean Acknowledge_Alert = false;
			if (Nsfonline_Account_Setup_Application == false) {
				for (int i = 0; i <= 120; i++) {
					try {
						if ((driver
								.findElement(By.xpath(OR_New_NSFOnline.getProperty("NewNsfOnline_Content_Model_Popup")))
								.isDisplayed())) {
							fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
							Acknowledge_Alert = true;
							break;
						} else {
							Thread.sleep(1000);
						}
					} catch (Throwable t) {
						Thread.sleep(500);
					}
					if (i == 120) {
						throw new Exception(
								"Acknowledge Alert Popup is NOT coming just after login through ipulse, Please refer the screen shot >> LoginFail"
										+ fnsScreenShot_Date_format());
					}
				}
				try {
					for (int i = 0; i <= NewNsfOnline_Element_Max_Wait_Time; i++) {
						try {
							if (i == NewNsfOnline_Element_Max_Wait_Time) {
								throw new TimeoutException(
										"After switching from ipulse, NsfOnline 2.0 Home Page is not getting load after <"
												+ NewNsfOnline_Element_Max_Wait_Time + ">seconds wait."
												+ " , Please refer the screen shot >> NsfOnline2_Landing_Screen_Open_Fail"
												+ fnsScreenShot_Date_format());
							} else {
								Thread.sleep(1000);
							}

							if ((Acknowledge_Alert)) {
								String ErrorMsg = TestSuiteBase_MonitorPlan
										.WithOut_OR_fnGet_ObjectX(
												OR_New_NSFOnline.getProperty("NewNsfOnline_Content_Model_Popup"))
										.getText().trim();
								if (ErrorMsg.toLowerCase().contains(
										"This is a non-production environment - Do NOT upload sensitive, confidential, NSF or customer data"
												.toLowerCase())) {
									fnsApps_Report_Logs("PASSED == Acknowledge Popup is coming.");
									break;
								} else {
									throw new TimeoutException("Getting Popup Error <" + ErrorMsg + ">"
											+ " , Please refer the screen shot >> NsfOnline2_Landing_Screen_Open_Fail"
											+ fnsScreenShot_Date_format());
								}
							}
							if ((driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("Sign_Out"))).size() > 0)
									|| driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("SwitchAccount_DD")))
											.size() > 0) {
								TestSuiteBase_MonitorPlan.fnsComman_BrowserLogin_ElementWait(
										OR_New_NSFOnline.getProperty("Sign_Out"),
										CONFIG.getProperty("Element_MAX_WaitTime"));
								break;
							}
						} catch (TimeoutException t) {
							throw new Exception(Throwables.getStackTraceAsString(t));
						} catch (Throwable t) {
							// nothing to do
						}

					}
					if (Acknowledge_Alert) {
						fnsGet_Element_Enabled("Acknowledge_Acknowledge_bttn");
						fnsWait_and_Click("Acknowledge_Acknowledge_bttn");
						Thread.sleep(2000);
					}
				} catch (Throwable t) {
					fnsTake_Screen_Shot("NsfOnline2_Landing_Screen_Open_Fail");
					if (driver.getTitle().toLowerCase().trim().contains("nsfonline")) {
						throw new Exception("'NsfOnline 1.0' is getting open instead of 'NsOnline 2.0'"
								+ " , Please refer the screen shot >> NsfOnline2_Landing_Screen_Open_Fail"
								+ fnsScreenShot_Date_format());
					} else {
						throw new Exception(Throwables.getStackTraceAsString(t));
					}
				}
				// fnsLoading_Progressing_wait(2); // TEMP - due to model content error load is
				// inside the condition, need to out from condition(nsfonlineaccountsetupbutton)
				/*
				 * if(Nsfonline_Account_Setup_Application){
				 * fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
				 * }else{
				 * fnsLoading_Progressing_wait(2);
				 * }
				 */

			}

			if (Nsfonline_Account_Setup_Application) {
				TestSuiteBase_MonitorPlan.fnsComman_BrowserLogin_ElementWait(
						OR_New_NSFOnline.getProperty("NewNsfOnline_Footer1"),
						CONFIG.getProperty("Element_MAX_WaitTime"));
				fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
			} else {
				TestSuiteBase_MonitorPlan.fnsComman_BrowserLogin_ElementWait(
						OR_New_NSFOnline.getProperty("NewNsfOnline_Footer"),
						CONFIG.getProperty("Element_MAX_WaitTime"));
				fnsLoading_Progressing_wait(2);
			}
			return present;
		} catch (NoSuchWindowException W) {
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}

	public String fnsWindowSwitch_Switch_and_return_NsfOnline2_windowHandle_OpenThrough_iPulse(
			String iPulseWindowHandle) throws Throwable {
		try {
			String NSFOnline_2_Window = null;
			for (int a = 0; a <= 60; a++) {
				ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
				Integer tabsCount = tabs.size();
				if (tabsCount == 2) {
					for (int i = 0; i < tabsCount; i++) {
						if (!(iPulseWindowHandle.equalsIgnoreCase(tabs.get(i)))) {
							driver.switchTo().window(tabs.get(i));
							NSFOnline_2_Window = driver.getWindowHandle();

							fnsApps_Report_Logs("PASSED == Successfully Switch to NSFOnline 2.0 Window.");
							break;
						}
					}
					break;
				} else {
					Thread.sleep(1000);
				}
				if (a == Long.parseLong(CONFIG.getProperty("ElementWaitTime"))) {
					throw new Exception(
							" NSF Online 2.0 window is not getting open after 60 Seconds wait, please refer the screen shot >> SwitchNSFOnlineWindowFail"
									+ fnsScreenShot_Date_format());
				}
			}
			return NSFOnline_2_Window;
		} catch (Throwable t) {
			fnsTake_Screen_Shot("SwitchNSFOnlineWindowFail");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}

	// Check for Browser Type defined in Suits Excel
	public void BrowserCheck() {
		for (int i = 2; i <= suiteXls.getRowCount("Test Suite"); i++) {

			if (suiteXls.getCellData("Test Suite", "TSID", i).equalsIgnoreCase("NSF_Connect_Suite")) {
				BrowserName = suiteXls.getCellData("Test Suite", "Browser", i);

				if (BrowserName.equals("")) {
					BrowserName = "IE";
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
				hashXlData.put(hashkey, hashValue);
			}
		}

		return hashXlData;
	}

	// Check class(Y/N) and Launch browser and Login
	public void fnsCheckClassLevelTestSkip(String className) throws Exception {
		TC_Step = 1;
		Login_Application_Name = "";

		try {
			classNameText = className;
			currentScriptCode = fnpMappingClassNameWithScenarioCode(classNameText).trim();

			if (!TestUtil.isTestCaseRunnable(New_NSFOnline_Suitexls, className)) {
				fnsApps_Report_Logs("Skipping Test Case " + className + " as runmode set to NO");
				throw new SkipException("Skipping Test Case " + className + " as runmode set to NO");
			}
			isTestCasePass = true;
			runmodes = TestUtil.getDataSetRunmodes(New_NSFOnline_Suitexls, className);

			Login_Application_Name = fns_Return_Login_Application_From_TestCaseSheet(New_NSFOnline_Suitexls, className);

			Login_UserName = fns_Return_LoginUserPassword_From_TestCaseSheet(New_NSFOnline_Suitexls, className);
			if (Login_UserName == null) {
				throw new Exception("FAILED == For Test Case <" + className
						+ ">, Unable to fetch UserName and Password from Excel.");
			} else {
				Login_Password = Login_UserName.split(":")[1].trim();
				Login_UserName = Login_UserName.split(":")[0].trim();
			}

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

	// Return Login User Name and Password from Test Case Sheet
	public String fns_Return_LoginUserPassword_From_TestCaseSheet(Xls_Reader xls, String testCaseName) {
		String LoginUserPassword = null;
		for (int i = 2; i <= xls.getRowCount("Test Cases"); i++) {
			if (xls.getCellData("Test Cases", "TCID", i).equalsIgnoreCase(testCaseName)) {
				LoginUserPassword = xls.getCellData("Test Cases", "Login_User_Password", i).trim();
			}
		}
		return LoginUserPassword;
	}

	public void fnsLoading_PageLoad(long Wait) throws Throwable {
		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			for (int k = 1; k <= Wait; k++) {
				String Text = (String) jse.executeScript("return document.readyState");// .equals("complete");
				if (Text.equals("complete")) {
					fnsApps_Report_Logs("Page is loaded in '" + k + "' seconds.");
					break;
				} else {
					Thread.sleep(1000);
				}
				if (k == Wait) {
					fnsTake_Screen_Shot("PageLoad_Fail");
					throw new Exception("FAIL == after '" + Wait + "' seconds wait page is not loading.");
				}
			}
		} catch (Throwable t) {
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}

	// Return Login Application from Test Case Sheet
	public String fns_Return_Login_Application_From_TestCaseSheet(Xls_Reader xls, String testCaseName)
			throws Throwable {
		try {
			String LoginApplication = null;
			for (int i = 2; i <= xls.getRowCount("Test Cases"); i++) {
				if (xls.getCellData("Test Cases", "TCID", i).equalsIgnoreCase(testCaseName)) {
					LoginApplication = xls.getCellData("Test Cases", "Login_Application", i).trim();
				}
			}
			if ((LoginApplication == null) || (LoginApplication.equals(""))) {
				throw new Exception("FAILED == Login Application Name is not mentioned into the excel for scenario ("
						+ testCaseName + ").");
			}
			return LoginApplication;
		} catch (Throwable t) {
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}

	public void fnaDD_value_Select_In_Suggestion_Search(String DD_Xpath_Select, String Value_from_Excel, long Wait_Time)
			throws Exception {

		try {

			fnsGet_Element_Enabled(DD_Xpath_Select);
			fnsWait_and_Type(DD_Xpath_Select, Value_from_Excel);

			for (int i = 0; i <= Wait_Time; i++) {

				boolean Value_Matched = false;

				try {
					List<WebElement> DDobjectlists = driver.findElements(
							By.xpath("//div[@id='ex1_dropdown']/div[@class='autocomplete-row ng-scope']/div[1]"));
					fnsApps_Report_Logs("Total no of search results: " + DDobjectlists.size());
					for (WebElement dd_value : DDobjectlists) {
						String dd_TEXT = dd_value.getText().toLowerCase().trim();
						if (dd_TEXT.contains(Value_from_Excel.toLowerCase().trim())) {
							dd_value.click();
							Value_Matched = true;
							break;
						}
					}
					if (Value_Matched) {
						fnsApps_Report_Logs(
								"PASSED == Value [" + Value_from_Excel + "] selection from drop down is done in <" + (i)
										+ ">seconds wait, DD having xpath >>  " + DD_Xpath_Select);
						break;
					} else {
						throw new NotContextException("FAILED == Excel value < " + Value_from_Excel
								+ " > is not exists into the drop down ' " + DD_Xpath_Select
								+ " ', please refer screenshot >> DdValueSelect_Fail" + fnsScreenShot_Date_format());
					}

				} catch (NotContextException NC) {
					if (i == Wait_Time) {
						throw new Exception(Throwables.getStackTraceAsString(NC));
					} else {
						Thread.sleep(800);
					}
				} catch (NoSuchWindowException W) {
					isTestCasePass = false;
					throw new Exception(W.getMessage());
				} catch (Throwable t) {
					if (i == Wait_Time) {
						throw new Exception(Throwables.getStackTraceAsString(t));
					} else {
						Thread.sleep(800);
					}
				}
			}
		} catch (Throwable t) {
			isTestCasePass = false;
			TestSuiteBase_MonitorPlan.fnsTake_Screen_Shot_Without_OR("DdValueSelect_Fail");
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}

	public void fna_UploadFile(String Upload_Bttn) throws Throwable {
		try {

			String FileUploadPath = System.getProperty("user.dir") + "\\docs\\This is test pdf document.pdf";
			WebElement Browser = fnsGet_OR_New_NSFOnline_ObjectX(Upload_Bttn);
			Browser.sendKeys(FileUploadPath);
			fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
			fnsApps_Report_Logs("PASSED == File uploaded successfully.");

		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new SkipException(W.getMessage());
		} catch (Throwable t) {
			isTestCasePass = false;
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}

	// check if the suite has to be skip
	@BeforeSuite
	public void checkSuiteSkip() throws Throwable {
		fns_CheckSiteSkip("NSF_Connect_Suite");
	}

	// Always run after suite
	@AfterSuite(alwaysRun = true)
	public void Finishing_New_NSFOnline_Suite_Script() throws Throwable {
		ScreenShotFlagWithOR_New_NSFOnline = false;

		/* fnsApps_Report_Logs(Loading_Xpath_Array.toString()); */

		fnsApps_Report_Logs("");
		fnsApps_Report_Logs(
				"######################################################## New_NSFOnline Suite END ######################################################## ");
		fnsApps_Report_Logs(
				"=========================================================================================================================================");
		fnsApps_Report_Logs("");
	}

	public String fnsgetCOCL(String UserName) {
		String COCL = "";
		switch (UserName.toLowerCase().trim()) {
			case ("gktestuser"): {
				COCL = "C0036244";
				break;
			}
			case ("lcarr"): {
				COCL = "C0036244";
				break;
			}
			case ("willsmith"): {
				COCL = "C0036244";
				break;
			}
			case ("soldham"): {
				COCL = "C0036244";
				break;
			}
			case ("bkadmin1"): {
				COCL = "C0212898";
				break;
			}
			case ("peter"): {
				COCL = "25130";
				break;
			}
			case ("linpo"): {
				COCL = "C0067177";
				break;
			}
			case ("billsage"): {
				COCL = "C0074017";
				break;
			}
			case ("c0301949admin"): {
				COCL = "C0301949";
				break;
			}
			case ("khighfill@lsiwc.com"): {
				COCL = "C0086006";
				break;
			}
			case ("avieducationadmin"): {
				COCL = "C0005516";
				break;
			}
			case ("kimtest530"): {
				COCL = "C0183530";
				break;
			}
			case ("c0048103admin"): {
				COCL = "C0048103";
				break;
			}
			case ("lpaden-locke@ilpeaindustries"): {
				COCL = "22490";
				break;
			}
			case ("6n670admin"): {
				COCL = "6N670";
				break;
			}
		}
		return COCL;
	}

	public void fncVerify_TAB_Exists_and_Opening_without_any_Error(String TAB_Name, String TAB_Xpath) throws Throwable {
		try {
			fnsGet_Element_Enabled_By_Passing_Wait_and_Screenshot_Condition(60, false, TAB_Xpath);
		} catch (Throwable t) {
			fnsTake_Screen_Shot(TAB_Name + "_Not_Exists");
			throw new Exception("FAILED == '" + TAB_Name
					+ "' TAB is not exists on View Issue screen, please refer the screen shot >> " + TAB_Name
					+ "_Not_Exists" + fnsScreenShot_Date_format() + Throwables.getStackTraceAsString(t));
		}
		try {
			fnsWait_and_Click(TAB_Xpath);
		} catch (Throwable t) {
			fnsTake_Screen_Shot(TAB_Name + "_Tab_Click_Fail");
			throw new Exception("FAILED == Clicking on '" + TAB_Name
					+ "' TAB is getting fail, please refer the screen shot >> " + TAB_Name + "_Tab_Click_Fail"
					+ fnsScreenShot_Date_format() + Throwables.getStackTraceAsString(t));
		}
		try {
			fnsLoading_Progressing_wait(2);
		} catch (Throwable t) {
			throw new Exception(
					"FAILED == Error in '" + TAB_Name + "' TAB : -- " + Throwables.getStackTraceAsString(t));
		}
		fnsApps_Report_Logs(
				"PASSED == #############   '" + TAB_Name + "' TAB is successfully opened without any errors. ");
	}

	public void fnsVerify_SavedValue_In_ViewMode_by_LabelName(String LabelName, String ExpectedValue) throws Throwable {
		try {
			String FieldValue_Xpath = "//label[contains(text(), '" + LabelName + "')]/following::label[1]";
			String ValueFromApp = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(FieldValue_Xpath).getText().trim();

			assert ValueFromApp.contains(ExpectedValue.trim()) : "FAILED == '" + LabelName + "' field value <"
					+ ValueFromApp + "> is NOT matched with expected value <" + ExpectedValue
					+ ">,  please refer the screen shot >> Expected_Value_Not_Match" + fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED == '" + LabelName + "' field value <" + ValueFromApp
					+ "> is matched with expected value <" + ExpectedValue + ">.  Automation");

		} catch (NoSuchWindowException W) {
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			fnsTake_Screen_Shot("Expected_Value_Not_Match");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}

	public void fnsMenu_Ajax_Link_Click_By_PassingAjaxPath_Ipulse(String AjaxLinkXpath) throws Exception {
		try {
			fnsGet_Element_Enabled("Ipulse_Main_Menu");
			WebElement Menu_Element = fnsGet_OR_New_NSFOnline_ObjectX("Ipulse_Main_Menu");

			// New line added to run script in chrome.
			WebElement VersionLogoImage = fnsGet_OR_New_NSFOnline_ObjectX("IpulseVersionLogoImage");
			VersionLogoImage.click();
			Thread.sleep(500);

			Actions action1 = new Actions(driver);
			action1.moveToElement(Menu_Element).build().perform();

			Thread.sleep(500);
			Actions action2 = new Actions(driver);
			fnsGet_Element_Enabled(AjaxLinkXpath);
			WebElement CreateContractorApplicant = fnsGet_OR_New_NSFOnline_ObjectX(AjaxLinkXpath);
			action2.moveToElement(CreateContractorApplicant).click().build().perform();
			TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(3);
		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot(AjaxLinkXpath + "_Fail");
			throw new Exception("FAILED == Clicking on <" + (AjaxLinkXpath) + "> Failed, plz see screenshot ["
					+ AjaxLinkXpath + "_Fail" + fnsScreenShot_Date_format() + "]" + ". Getting Exception  >> "
					+ Throwables.getStackTraceAsString(t).trim());
		}
	}

	public void fnsVerify_Expected_RadioButtonSelected_Ipulse(String RadioButtons_MainfieldLabelName,
			String ExpectedRadioButton_Label) throws Exception {
		try {
			boolean Expected_RadioButton_Selected = false;
			String Radio_Buttons_MainTable_Xpath = "(//label[contains(text(),'" + RadioButtons_MainfieldLabelName
					+ "')]/following::table[1])[1]";
			List<WebElement> Td_Objects = TestSuiteBase_MonitorPlan
					.WithOut_OR_fnGet_ObjectX(Radio_Buttons_MainTable_Xpath).findElements(By.tagName("td"));
			for (WebElement Td_Element : Td_Objects) {
				String Td_Text = Td_Element.getText().toLowerCase().trim();
				if (Td_Text.equalsIgnoreCase(ExpectedRadioButton_Label)) {
					fnsApps_Report_Logs("PASSED == '" + ExpectedRadioButton_Label
							+ "' Radio button is coming for the field <" + RadioButtons_MainfieldLabelName + ">.");
					List<WebElement> Radio_button_Objects = Td_Element.findElements(By.tagName("div"));
					for (WebElement Radio_button_Element : Radio_button_Objects) {
						String RadionButton_Class = Radio_button_Element.getAttribute("class").trim();
						if (RadionButton_Class.equalsIgnoreCase(
								"ui-radiobutton-box ui-widget ui-corner-all ui-state-default ui-state-active")) {
							Expected_RadioButton_Selected = true;
							break;
						}
					}
				}
				if (Expected_RadioButton_Selected) {
					break;
				}
			}
			if (Expected_RadioButton_Selected) {
				fnsApps_Report_Logs("PASSED == Expected '" + ExpectedRadioButton_Label + "' radio button is selected.");
			} else {
				throw new Exception("FAILED == Expected '" + ExpectedRadioButton_Label
						+ "' radio button is not selected, please refer the screenshot >> Expected_RadioButton_Not_Selected"
						+ fnsScreenShot_Date_format());
			}

		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot("Expected_RadioButton_Not_Selected");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}

	public void fnsVerify_Expected_DropDownValueSelected_Ipulse(String DropdownLabelName, String SelectedValueText)
			throws Exception {
		try {
			boolean ExpectedValue_Selected = false;
			String Selected_Dropdown_Value_Xpath = "//label[text()='" + DropdownLabelName
					+ "']/following::label[text()='" + SelectedValueText + "']";
			for (int i = 0; i <= 60; i++) {
				if (driver.findElements(By.xpath(Selected_Dropdown_Value_Xpath)).size() > 0) {
					fnsApps_Report_Logs("PASSED == Expected '" + SelectedValueText
							+ "' value is selected into the drop down '" + DropdownLabelName + "'.");
					ExpectedValue_Selected = true;
					break;
				} else {
					Thread.sleep(1000);
				}
			}
			if (ExpectedValue_Selected == false) {
				throw new Exception(
						"FAILED == '" + SelectedValueText + "' value is not coming as selected into the drop down '"
								+ DropdownLabelName + "', please refer the screenshot >> Expected_DDValue_Not_Selected"
								+ fnsScreenShot_Date_format());
			}
		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		} catch (Throwable t) {
			isTestCasePass = false;
			fnsTake_Screen_Shot("Expected_DDValue_Not_Selected");
			throw new Exception();
		}
	}

	public void fnsVerify_DataUpdated_Successfully_Ipulse(String UpdateMsgXpath, String SectionName) throws Throwable {
		try {
			String Fetched_Text = fnsGet_Field_TEXT(UpdateMsgXpath).toLowerCase().trim();
			TestSuiteBase_MonitorPlan
					.WithOut_OR_fnMove_To_Element_BY_OFFSET(OR_New_NSFOnline.getProperty(UpdateMsgXpath), 30, 10);
			try {
				assert (Fetched_Text.contains("success"))
						: "FAILED == " + SectionName + ": Data are not updated, Getting Error<" + Fetched_Text
								+ ">, Please refer screenshot >> UpdateFail" + fnsScreenShot_Date_format();
				fnsApps_Report_Logs("PASSED == " + SectionName + ": Data are updated successfully.");
			} catch (Throwable t) {
				fnsTake_Screen_Shot("UpdateFail");
				throw new Exception(Throwables.getStackTraceAsString(t));
			}

		} catch (NoSuchWindowException W) {
			isTestCasePass = false;
			throw new SkipException(W.getMessage());
		} catch (Throwable t) {
			isTestCasePass = false;
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}

}
