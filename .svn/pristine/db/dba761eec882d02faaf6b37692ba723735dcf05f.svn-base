package nsf.ecap.Audit_SAM_SPA_suite;



import static nsf.ecap.config.IPULSE_CONSTANTS.*;








import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;
import java.util.NoSuchElementException;

import nsf.ecap.Health_Science_suite.TestSuiteBase_HealthScience;




import nsf.ecap.Work_Order_suite.TestSuiteBase;


import nsf.ecap.util.TestUtil;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.StaleElementReferenceException;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.google.common.base.Throwables;




public class TestSuiteBase_Audit_SAM_SPA_suite extends TestSuiteBase {

	public static String runmodes[] = null;
	public static int count = -1;
	


	// check if the suite has to be skipped or not
	@BeforeSuite
	public void checkSuiteSkip() throws Throwable {

		fnInitialize();

		//currentSuiteName = "Audit_SAM_SPA_suite";
		setCurrentSuiteName("Audit_SAM_SPA_suite");
		fnpMymsg("             ");
		fnpMymsg("             ");
		fnpMymsg("=========================================================================================================================================");
		fnpMymsg("####################" + currentSuiteName + " Start ############################################################");
		fnpMymsg("Checking Runmode of " + currentSuiteName);
		if (!TestUtil.isSuiteRunnable(suiteXls, currentSuiteName)) {
			fnpMymsg("Skipped " + currentSuiteName + "  as the runmode was set to NO");
			//fnpMymsg("####################  " + currentSuiteName + "  End ############################################################");
			//fnpMymsg("=========================================================================================================================================");
			throw new SkipException("Runmode of " + currentSuiteName + " set to no. So Skipping all tests in " + currentSuiteName);
		}

		if (TestUtil.isSuiteRunnable(suiteXls, currentSuiteName)) {
			browserName = TestUtil.getBrowserName(suiteXls, currentSuiteName);
			excelSiteURL = TestUtil.getExcelSiteURL(suiteXls, currentSuiteName);
			fnpMymsg("Browser Name is :" + browserName);
			fnpMymsg("======================");
			currentSuiteCode = "SAM";

			int rowNum = suiteXls.getCellRowNum("Test Suite", "TSID", currentSuiteName);
			suiteXls.setCellData("Test Suite", "Last_Execution", rowNum, fnReturnDateWithTimeForExcel());

			//currentSuiteXls = Audit_SAM_SPA_suitexls;
			setCurrentSuiteExcel(Audit_SAM_SPA_suitexls);
		}

	}

	// To remove IEDriver.exe from Windows Task Manager
	 @AfterSuite(alwaysRun=true)
	//@AfterSuite
	public void cleanUp() {
		try {
			referenceSuite = currentSuiteName;
			fnpMymsg("#################### "+currentSuiteName+" Suite End ############################################################");
			if (driver!=null) {
				driver.quit();
			}
			IsBrowserPresentAlready = false;
			killprocess();
		} catch (Throwable t) {
			// Nothing to do
		}

	}
	
	


	
	
	public static String fnpClickFirstVisitWhichDoesNotHaveAuditor() throws Throwable{
		
		
		int totalVisisbleRows=0;
		String currentAuditorValueXpath="";
		String currentAuditorValue;
		int generalCounter=0;
		
		String visitNoWhichHaveBlankCurrentAuditor="";
		
		
		String paginationValue=fnpGetText_OR("FooterPagination_xpath");
		String totalRecord=paginationValue.split("of")[1].trim();
		String totalRecordCount=totalRecord.split("items")[0].trim();
		int lastRecordRowNo=Integer.parseInt(totalRecordCount);
		
		boolean blankCurrentAuditorFound=false;
		
		
		int currentAuditorColNo=fnaFindColumnIndex_NOR_TraversingFromVariousNodes_SAM(OR.getProperty("SAMSearchTable_header"), "Current Auditor",2);
		
		
		while (true) {	

			
			
			totalVisisbleRows=fnpCountRowsInTable("SAMSearchTable");
			
			String textMessage;
			for (int i = 1; i < totalVisisbleRows+1; i++) {
				generalCounter++;
				
				//currentAuditorValueXpath=".//*[@id='visitGrid']/table/tbody/tr["+i+"]/td["+currentAuditorColNo+"]/div/div";
				currentAuditorValueXpath=".//*[@role='presentation']/table/tbody/tr["+i+"]/td["+currentAuditorColNo+"]";
				currentAuditorValue=fnpGetText_NOR(currentAuditorValueXpath);
				
				
				//if (!(currentAuditorValue.contains(""))) {
				if (!(currentAuditorValue.equalsIgnoreCase(""))) {
					
					
					textMessage = "Current Auditor value is NOT blank in row no. --'"+i+"' and col no. -'"+currentAuditorColNo+"' i.e. --'"+currentAuditorValue+"', so check in next row.";
					fnpMymsg(textMessage);
					fnpDisplayingMessageInFrame(textMessage, 5);
				
					
					
					
				} else {
					textMessage = "Current Auditor value is blank in row no. --'"+i+"' and col no. -'"+currentAuditorColNo+"' i.e. --'"+currentAuditorValue+"', so going to click this.";
					fnpMymsg(textMessage);
					fnpDisplayingMessageInFrame(textMessage, 5);
					
					blankCurrentAuditorFound=true;
					
					int visitNoColNo=fnaFindColumnIndex_NOR_TraversingFromVariousNodes_SAM(OR.getProperty("SAMSearchTable_header"), "Visit No.",2);
					//String visitXpath=".//*[@id='visitGrid']/table/tbody/tr["+i+"]/td["+visitNoColNo+"]/a";	
					String visitXpath=".//*[@role='presentation']/table/tbody/tr["+i+"]/td["+visitNoColNo+"]";
					String visitNo=fnpGetText_NOR(visitXpath);
					textMessage = "Going to click this visit no. which does not have any Current Auditor i.e. --"+visitNo;
					fnpMymsg(textMessage);
					fnpDisplayingMessageInFrame(textMessage, 5);
					
					visitNoWhichHaveBlankCurrentAuditor=visitNo;
					
					fnpClick_NOR_withoutWait(visitXpath);
					//fnsLoading_wait_In_SAM(1);
					break;

				}
				
				
				
			}
			
			
			//coming out of while loop if found
			if ((blankCurrentAuditorFound)) {
				break;
			}
			
			
			
			
			if (fnpCheckElementDisplayedImmediately("NextBtnSAM")) {
				if (fnpCheckElementDisplayedImmediately("NextBtnSAM_disable")) {
					break;
				}
				fnpClick_OR_WithoutWait("NextBtnSAM");
				fnsLoading_wait_In_SAM(2);
			} else {
				
				//if ((generalCounter+1)!=lastRecordRowNo) {
				if ((generalCounter)!=lastRecordRowNo) {

					
					textMessage = " This row no. -'"+(generalCounter+1)+"' is not present as Next button is " +
							"disable but in pagination it shows total records are --"+lastRecordRowNo;
					fnpMymsg(textMessage);
					fnpDisplayingMessageInFrame(textMessage, 5);
					throw new Exception(textMessage);
					//break;
					
				} else {
					break;
				}
				
				
				//break;
				
				
			}

			
			
		}
		
		
		
		
		if (!(blankCurrentAuditorFound)) {
			
			String textMessage = " Could not find any row which have 'Current Auditor' value is blank.";
			fnpMymsg(textMessage);
			fnpDisplayingMessageInFrame(textMessage, 5);
			throw new Exception(textMessage);
		}
		
		
		
		return visitNoWhichHaveBlankCurrentAuditor;
		
		
	}
	
	
	
	public static void fnpGoingBackToFirstRecordInSAMWithoutClickingPreviousBtn() throws Throwable{
		
		String textMessage;

		fnpClick_OR_WithoutWait("Advanced_Filter_Options_ExpandBtn");
		
		//Thread.sleep(1000);
		fnpClick_InSAM_OR("ResetBtnSAM");
		//Thread.sleep(1000);
		
		
		//Thread.sleep(1000);
		fnpSelectValue_By_SelectClass(OR.getProperty("ProgramCode_dropDown"), "ISR");
		//Thread.sleep(1000);
		
		
		String expectedAuditType=(String) hashXlData.get("Audit_Type");
		
		fnpClick_OR_WithoutWait("AuditorTypeTxtBxSAM");
		fnpType("OR", "AuditorTypeTxtBxSAM", expectedAuditType);

		//fnsLoading_wait_In_SAM(2);
		//Thread.sleep(5000);
		
		//String xpathForTypeList=".//div[@id='auditTypes-list']//ul/li[text()='"+expectedAuditType+"']";
		//".//li[text()='"+ expectedAuditType +"']/label";
		String xpathForTypeList1=".//*[text()='"+ expectedAuditType +"']";
		String xpathForTypeList2=".//li[text()='"+ expectedAuditType +"']/label/input";

		if (fnpCheckElementDisplayedImmediately_NOR(xpathForTypeList2)) {
			fnpClick_NOR_withoutWait(xpathForTypeList2);
			
		}else{
			Thread.sleep(1000);
			
		//one more time to check again the list is rendered or not after giving too much wait as well as loading too.
			
			if (fnpCheckElementDisplayedImmediately_NOR(xpathForTypeList1)) {
				fnpClick_NOR_withoutWait(xpathForTypeList1);
			}else{
				textMessage = " This Audit Type '"+expectedAuditType+"' is not present in drop down, hence failed.";
				fnpMymsg(textMessage);
				fnpDisplayingMessageInFrame(textMessage, 5);
				throw new Exception(textMessage);
				}
		}
		
		
		
/*		if (fnpCheckElementDisplayedImmediately_NOR(xpathForTypeList)) {
			fnpClick_NOR_withoutWait(xpathForTypeList);
			
		}else{
			
			String textMessage = " This Audit Type '"+expectedAuditType+"' is not present in drop down, hence failed.";
			fnpMymsg(textMessage);
			fnpDisplayingMessageInFrame(textMessage, 10);
			throw new Exception(textMessage);
		}
		
		*/
		
		
/*		
		try{
			fnsLoading_wait_In_SAM(2);
			fnpWaitForVisible_NotInOR(xpathForTypeList, 30);
			fnpClick_NOR_withoutWait(xpathForTypeList);
			fnsLoading_wait_In_SAM(2);
		}catch(Throwable t){
			String textMessage = t.getMessage();//" This Audit Type '"+expectedAuditType+"' is not present in drop down, hence failed.";
			fnpMymsg(textMessage);
			fnpDisplayingMessageInFrame(textMessage, 10);
			throw new Exception(textMessage);
		}
		
		
*/		
		
		
		
		
		
		
		
		if (!(fnpGetCurrRunningBrowserName().equalsIgnoreCase("chrome"))) {
			Thread.sleep(1000);
		}
		
		fnpClick_OR_WithoutWait("AuditorTypeLblSAM");
		
		
		//String selectedDropDownValue=".//*[@id='auditTypes_taglist']/li/span";
		String selectedDropDownValue=".//*[@class='k-reset']/li/span[1]";
		String selectedValue=fnpGetText_NOR(selectedDropDownValue);
		if (!(selectedValue.equalsIgnoreCase(expectedAuditType))) {
			fnpMymsg("Audit Type down value has not been selected properly. Selected value is --"+selectedValue);
			throw new Exception("Audit Type down value has not been selected properly. " +
					"Selected value is --'"+selectedValue+"'. See screenshot.");
			
		} else {
			fnpMymsg("Audit Type down value has  been selected properly. Selected value is --"+selectedValue);
		}
		
		
		
		fnaSelectingCriteriaInAdvancedFilterInSAM();
		fnpClick_InSAM_OR("FilterBtnSAM");
		
		
		
	}
	
	
	
	public static void fnpSelectingCriteriaInAdvancedFilterInSAM() throws Throwable{
		
		fnpClick_OR_WithoutWait("SchedulingBounds_startDate");
		Thread.sleep(2000);
		Date todayDate=new Date();
		SimpleDateFormat sdfmt1 = new SimpleDateFormat("dd-MMM-yyyy");
		String todaydateInStringformat = sdfmt1.format(todayDate);
		fnpCalendarDatePicker_BySelectValues_From_NextAndBack_SAM_SchedulingBound(todaydateInStringformat,"CurrentDateXpath_forStartDate","PrevIconXpath_forStartDate","NextIconXpath_forStartDate") ;
		Thread.sleep(2000);	
		
		todayDate=new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(todayDate);
		//c.add(Calendar.DAY_OF_MONTH, 3);
		//c.add(Calendar.DAY_OF_MONTH, 30);
		
		String nDays=(String) hashXlData.get("Next_n_days").trim();
		int next_n_days=Integer.parseInt(nDays);
		c.add(Calendar.DAY_OF_MONTH, next_n_days);
		
		Date afterThreeDayDate = c.getTime();
		sdfmt1 = new SimpleDateFormat("dd-MMM-yyyy");
		String afterThreeDayDateInStringformat = sdfmt1.format(afterThreeDayDate);
		
		fnpClick_OR_WithoutWait("SchedulingBounds_endDate");
		Thread.sleep(2000);			
		fnpCalendarDatePicker_BySelectValues_From_NextAndBack_SAM_SchedulingBound(afterThreeDayDateInStringformat,"CurrentDateXpath_forEndDate","PrevIconXpath_forEndDate","NextIconXpath_forEndDate") ;
		
		
	}
	
	public static void fnaSelectingCriteriaInAdvancedFilterInSAM() throws Throwable{
		
		fnpClick_OR_WithoutWait("SchedulingBounds_startDate");
		
		fnpClick_OR_WithoutWait("SchedulingBounds_startTodayDate");
		
		Date todayDate=new Date();
		SimpleDateFormat sdfmt1 = new SimpleDateFormat("dd-MM-yyyy");
		//String todaydateInStringformat = sdfmt1.format(todayDate);
		Thread.sleep(2000);	
		
		todayDate=new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(todayDate);
		//c.add(Calendar.DAY_OF_MONTH, 3);
		//c.add(Calendar.DAY_OF_MONTH, 30);
		
		String nDays=(String) hashXlData.get("Next_n_days").trim();
		int next_n_days=Integer.parseInt(nDays);
		c.add(Calendar.DAY_OF_MONTH, next_n_days);
		
		Date afterThreeDayDate = c.getTime();
		sdfmt1 = new SimpleDateFormat("yyyy-MM-dd");
		String afterThreeDayDateInStringformat = sdfmt1.format(afterThreeDayDate);
		
		fnpMymsg("Expected date is: " + afterThreeDayDateInStringformat);
		
		fnaCalendarDatePicker_BySelectValues_From_SAM_SchedulingBound(afterThreeDayDateInStringformat);

		Thread.sleep(1000);
		
		
	}
	
	
public static void fnaSelectingSchedulingBoundsDate() throws Throwable{
		
		fnpClick_OR_WithoutWait("SchedulingBounds_startDate");
		Thread.sleep(1000);
		
		fnpClick_OR_WithoutWait("SchedulingBounds_Today_Date_xpath");
		Thread.sleep(1000);

		
		Date todayDate=new Date();
		SimpleDateFormat sdfmt1 = new SimpleDateFormat("dd-MM-yyyy");
		String todaydateInStringformat = sdfmt1.format(todayDate);
		Thread.sleep(2000);	
		
		todayDate=new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(todayDate);
		//c.add(Calendar.DAY_OF_MONTH, 3);
		//c.add(Calendar.DAY_OF_MONTH, 30);
		
		String nDays=(String) hashXlData.get("Next_n_days").trim();
		int next_n_days=Integer.parseInt(nDays);
		c.add(Calendar.DAY_OF_MONTH, next_n_days);
		
		Date afterThreeDayDate = c.getTime();
		sdfmt1 = new SimpleDateFormat("yyyy-MM-dd");
		String afterThreeDayDateInStringformat = sdfmt1.format(afterThreeDayDate);
		fnpMymsg("Expected date is: " + afterThreeDayDateInStringformat);
		fnaCalendarDatePicker_BySelectValues_From_SAM_SchedulingBound(afterThreeDayDateInStringformat);
		
		
	}


//Function of loading image appear on the screen (Block UI). 
public static void fnsLoading_wait_In_SAM(int waitcount) throws Throwable{
	try{
		String Loading_Image_Xpath = "";
		Integer Element_Max_Wait_Time = 120;
		String Loading_Classes_From_OR = OR.getProperty("SAM_Loading_Progressing").trim();
		Integer PageLoadWait = TestSuiteBase_MonitorPlan.fnsLoading_PageLoad();		
		for(int wait=PageLoadWait; wait<=((Element_Max_Wait_Time)); wait++){
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
			
			
			try{
				if(driver.findElement(By.xpath(Loading_Image_Xpath)).isDisplayed()){
					Thread.sleep(1000);
					LoadingImageFlag = true;
				}
			}catch( Throwable n){  Thread.sleep(250); }
			
			
			
			try{	
				if(  !(driver.findElement(By.xpath(Loading_Image_Xpath)).isDisplayed()) && wait > waitcount &&  LoadingImageFlag == true ){
					fnpMymsg("Progressing Loading image is displayed for < "+(Actual_Loading_Time)+" > seconds.");
					break;	
				}else if(  !(driver.findElement(By.xpath(Loading_Image_Xpath)).isDisplayed()) && wait > waitcount &&  LoadingImageFlag == false ){
					fnpMymsg("*********  Pause/Wait is for < "+Actual_Loading_Time+" > seconds.  *****  "); //To increase performance, Pause/Wait time can set to 1 sec
					break;
				}else if(  wait < waitcount &&  LoadingImageFlag == false ){
					Thread.sleep(500);
				}
			}catch(Throwable n){
				if(  wait > waitcount ){
					if( LoadingImageFlag == true ){
						//Loading_Xpath_Array.add(Loading_Image_Xpath+"&&");
						fnpMymsg("Progressing Loading image is displayed for < "+(Actual_Loading_Time)+" > seconds.");
						break;	
					}else if( LoadingImageFlag == false ){
						fnpMymsg("*********  Pause/Wait is for < "+Actual_Loading_Time+" > seconds.  *****  "); //To increase performance, Pause/Wait time can set to 1 sec
						break;
					}
				}else if(  wait < waitcount &&  LoadingImageFlag == false ){
					Thread.sleep(500);
				}
			}
						
			if(wait==(Element_Max_Wait_Time)){
				throw new InterruptedException("Loading Issue : After < "+(Element_Max_Wait_Time)+" > Seconds wait page is not getting Load, please refer screenshot >> PageLoadFail" + "*****  < "+Loading_Image_Xpath+" >");
			}
			Actual_Loading_Time++;
		}
				
			
	}catch(IllegalArgumentException i){
		//isTestCasePass = false;
		TestSuiteBase_MonitorPlan.fnsTake_Screen_Shot_Without_OR("UnExpectedError");
		fnpMymsg("Getting Un-Expected Application Error <"+ErrorMsgText+">, please refer screenshot >>UnExpectedError >>"+"  Exception ("+Throwables.getStackTraceAsString(i));
		throw new Exception(Throwables.getStackTraceAsString(i));	
	}catch(NoSuchElementException n){
		fnpMymsg("***** Progressing Loading image displayed for < "+Actual_Loading_Time+" > seconds. **************** NS");
		try{
			fnpMymsg("***** Progressing Loading image displayed for < "+Actual_Loading_Time+" > seconds. **************** NS");
			fnsLoading_wait_In_SAM(waitcount);			
		}catch(Throwable tt){
			isTestCasePass = false;
			TestSuiteBase_MonitorPlan.fnsTake_Screen_Shot_Without_OR("PageLoadFail_NS");
			fnpMymsg(Throwables.getStackTraceAsString(tt));
			throw new Exception(Throwables.getStackTraceAsString(tt));		
		}
	}catch(StaleElementReferenceException n){
		fnpMymsg("***** Progressing Loading image displayed for < "+Actual_Loading_Time+" > seconds. **************** Stale");
	} catch(NoSuchWindowException W){
		throw new Exception(W.getMessage());
	}catch(InterruptedException ie){
		System.out.println("Interrupted-----Exception");
		TestSuiteBase_MonitorPlan.fnsTake_Screen_Shot_Without_OR("PageLoadFail");
		fnpMymsg(Throwables.getStackTraceAsString(ie));
		throw new Exception(Throwables.getStackTraceAsString(ie));		
	}catch(Throwable tt){
		//isTestCasePass = false;
		TestSuiteBase_MonitorPlan.fnsTake_Screen_Shot_Without_OR("PageLoadFail");
		fnpMymsg(Throwables.getStackTraceAsString(tt));
		throw new Exception(Throwables.getStackTraceAsString(tt));		
	}
}

public static void fnaCalendarDatePicker_BySelectValues_From_SAM_SchedulingBound(String ExpectedDate) throws Throwable {
	
	try {

	LocalDate currentDate = LocalDate.parse(ExpectedDate);
	 
    Month month = currentDate.getMonth();
    
    int year = currentDate.getYear();
    
    int date = currentDate.getDayOfMonth();
    
    String monthString = month.toString().toLowerCase();
    
    String monthName = monthString.substring(0, 1).toUpperCase() + monthString.substring(1,3);
	
	
    
  /*  
    .//ul[@class='k-reset']/li/span[text()='2022']   year xpath

    .//td[@class='k-calendar-td']/span[text()='Jan']  

    .//*[contains(@class,'k-calendar-td') and contains(@title,'2022')]/span[text()='Jan']   month xpath


    .//*[contains(@class,'k-calendar-td') and contains(@title,'December')]/span[text()='31']  date xpath
    
 */   
    
    String calenderHeaderxpath ="//*[@class='k-calendar-header k-hstack']";
    
    String calenderTitle="//span[@class='k-button k-flat k-title k-calendar-title']";
    
	//String yearXpath ="//span[contains(@class,'k-calendar-navigation-marker') and contains(text(), '"+ year +"')]";
    //String monthXpath ="("+ yearXpath + "/following::span[text()='"+ monthName +"'])[1]";
    //String dateXpath = "(//td[@aria-disabled='false']/span)["+ date +"]";
    
	String yearXpath =".//ul[@class='k-reset']/li/span[text()='"+ year +"']";
	String monthXpath =".//*[contains(@class,'k-calendar-td') and contains(@title,'"+ year +"')]/span[text()='"+ monthName +"']";
	String dateXpath = ".//*[contains(@class,'k-calendar-td') and contains(@title,'"+ monthName +"')]/span[text()='"+ date +"']";
	
	//String dateXpath = "(//td[@class='k-calendar-td']/following::td/span)["+ date +"-1]"; //(//td[@aria-disabled='false'])[5]
	
	
			
	String yearListxpath = "//span[contains(@class,'k-calendar-navigation-marker')]";
	
	fnpClick_OR_WithoutWait("SchedulingBounds_endDate");
	
	if(driver.findElements(By.xpath(calenderHeaderxpath)).size()>0){

		//driver.findElement(By.xpath(yearXpath)).click();
		fnpClick_NOR_withoutWait(calenderTitle);
		Thread.sleep(1000);
		
		//driver.findElement(By.xpath(yearXpath)).click();
		fnpClick_NOR_withoutWait(yearXpath);
		Thread.sleep(1000);
		
		//driver.findElement(By.xpath(dateXpath)).click();
		fnpClick_NOR_withoutWait(monthXpath);
		Thread.sleep(1000);
		
		fnpClick_NOR_withoutWait(dateXpath);
		Thread.sleep(1000);
		
		String selectDateXpath = "(.//input[@class='k-input' and @placeholder='null'])[2]";
		
		String text = fnpGetText_NOR(selectDateXpath);
		fnpMymsg("PASSED == Successfully "+ text +"date is selected in calender");
		
		
	}
			
	}catch (Throwable e) {
		fnpMymsg(Throwables.getStackTraceAsString(e));
		throw new Exception(Throwables.getStackTraceAsString(e));
	}		
	
	
}


public static void fna_SelectDateFromCalendar_InSAM(String Cal_LabelName, String DisplayDate_Seq) throws Throwable{
	try{
		
		String SchedulingBounds_startDate=".//*[@ng-reflect-name='"+Cal_LabelName+"']/span/span/span";
		//SchedulingBounds_endDate=.//*[@ng-reflect-name='schedulingBoundToDate']/span/span/span
		
		//String DateCalButton_Xpath ="(//label[contains(text(), '"+Cal_LabelName+"')]/following::span[@class='k-icon k-i-calendar'])[1]";
		String DateXpath = "(//td[@aria-disabled='false']/span)["+DisplayDate_Seq+"]";
		String NOW_button_Xpath = "//button[@title='Select now' and text()='NOW']";
		String Set_Button_Xpath = "//button[@title='Set' and contains(text(), 'Set')]";
		
		fnpCheckElementDisplayed_NOR(SchedulingBounds_startDate);
		fnpClick_NOR(SchedulingBounds_startDate);
		fnsLoading_wait_In_SAM(1);
		fnsLoading_wait_In_SAM(1);
		
		fnpCheckElementDisplayed_NOR(DateXpath);
		fnpClick_NOR(DateXpath);
		fnsLoading_wait_In_SAM(1);
		fnsLoading_wait_In_SAM(1);
		
		fnpCheckElementDisplayed_NOR(NOW_button_Xpath);
		fnpClick_NOR(NOW_button_Xpath);
		fnsLoading_wait_In_SAM(1);
		fnsLoading_wait_In_SAM(1);
		
		
		fnpCheckElementDisplayed_NOR(Set_Button_Xpath);
		fnpClick_NOR(Set_Button_Xpath);
		fnsLoading_wait_In_SAM(2);
		fnsLoading_wait_In_SAM(1);
	}catch(Throwable t){
		fnpMymsg(Throwables.getStackTraceAsString(t));
		throw new Exception(Throwables.getStackTraceAsString(t));	
	}
}


public static int fnaFindColumnIndex_NOR_TraversingFromVariousNodes_SAM(String TableHeaderXpath, String columnName,int startingFromColNo) throws Throwable {
	int retries = 0;

	while (true) {

		retries++;

		try {
			int colIndex = 0;
			String xpathHeader = TableHeaderXpath + "/tr/th";
			int HeaderCount = driver.findElements(By.xpath(xpathHeader)).size();

			for (int i = startingFromColNo; i < HeaderCount + 1; i++) {
				String xpathExpression = TableHeaderXpath + "/tr/th[" + i + "]";
				String headerValue = driver.findElement(By.xpath(xpathExpression)).getText().trim();
				String headerValueWithoutTrim = driver.findElement(By.xpath(xpathExpression)).getText();
				if (columnName.equalsIgnoreCase(headerValue) || columnName.equalsIgnoreCase(headerValueWithoutTrim) || columnName.trim().equalsIgnoreCase(headerValue.trim())) {
					colIndex = i;
					fnpMymsg("@@@@@@@   -----col no is ---for '" + headerValue + "' is -----" + colIndex);
					break;

				}

			}

			if (colIndex > 0) {
				return colIndex;
			} else {

				if (retries > 4) {
					throw new Exception("Col no. is not calculated for -- '" + columnName + "' for table having xpath ---'" + TableHeaderXpath + "'.");
				} else {
					// going again in loop to calculate again
					// fnpLoading_wait();
				}
			}

		} catch (org.openqa.selenium.StaleElementReferenceException s) {
			if (retries > 4) {
				throw new Exception("StaleElementReferenceException is thrown ---Col no. is not calculated for -- '" + columnName + "' for table having xpath ---'"
						+ TableHeaderXpath + "'.");
			} else {
				// going again in loop to calculate again
				// fnpLoading_wait();
			}
		}

		catch (Throwable t) {
			if (retries < 2) {
				fnpMymsg("In catch block for try chance -" + retries);
				Thread.sleep(1000);
				// fnpLoading_wait();
			} else {
				String msg = t.getMessage();
				throw new Exception(msg);
			}

		}

	}
}


	
	
}

