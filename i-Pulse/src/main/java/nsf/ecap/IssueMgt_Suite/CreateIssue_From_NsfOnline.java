package nsf.ecap.IssueMgt_Suite;

import java.util.ArrayList;

import nsf.ecap.util.*;
import nsf.ecap.Grip_Suite.TestSuiteBase_Grip;

import nsf.ecap.NSFOnline_Suite.TestSuiteBase_NSFOnline;
//

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;

public class CreateIssue_From_NsfOnline extends TestSuiteBase_IM{/*
	
	public static boolean IsBrowserPresentAlready = false;
	public static boolean fail = false;
	public static boolean skip = true;
//	public static boolean isTestPass = true;
	public static boolean InsightWindowhandle = true;
	public static boolean NSFOnlineVersionScreenshot = true;
	public static String InsightoriginalHandle = null;
	public static String MainWindowHandle = null;
	public static String runmodes[] = null;
	public static String NSFOnlineoriginalHandle;
	public static Integer ColumnNumber = null;
	public static Integer GetTextLength;
	public static Integer tabsCount;
	public static Integer count = -1;
	

	public static String TitleShortDescriptionText="Create Issue From NSF-Online[BS-18], D/T=";
	public static String Insight_Customer_Name = null;
	public static String Insight_User_Name = null;
	public static String IssueType_DD_Value = null;
	public static String IssueSubType_DD_Value = null;
	public static String Issue_ID = "I0059310";
	public static String Confirmyoursite_LK_SiteNo = null;
		
	
	@Parameters({ "className" })
	@BeforeTest
		public void checkTestSkip(String className) throws Exception {
		
		isTestCasePass=true;
		count = -1;
		
		
		try {
			if (className.isEmpty()) {
				className = this.getClass().getSimpleName();
			}
			
			
			
			classNameText=className;
			currentScriptCode=fnpMappingClassNameWithScenarioCode(classNameText).trim();

			if (!TestUtil.isTestCaseRunnable(IssueMgt_Suitexls, className)) {
				fnApps_Report_Logs("Skipping Test Case " + className + " as runmode set to NO");
				throw new SkipException("Skipping Test Case " + className + " as runmode set to NO");
			}

			ClassName_IM_Bs18 = true;
			runmodes = TestUtil.getDataSetRunmodes(IssueMgt_Suitexls, className);
			
			fnApps_Report_Logs("=========================================================================================================================================");
		
		}catch(SkipException sk){
			String errorMsg = sk.getMessage();
			Exception c = new Exception(errorMsg);
			ErrorUtil.addVerificationFailure(c);
			throw new SkipException(errorMsg + " \n\nSo,Skipping Test Case" + className);
		}catch (Throwable t) {								
				fnTake_Screen_Shot(className);				
				String stackTrace = Throwables.getStackTraceAsString(t);
				String errorMsg = Throwables.getStackTraceAsString(t);
				errorMsg = errorMsg + "\n\n\n\nSee screenshot '" + className + "'\n\n" + stackTrace;
				Exception c = new Exception(errorMsg);
				ErrorUtil.addVerificationFailure(c);
				throw new Exception(errorMsg );
		}
}
	
	
	

	
	
	
	

@Test( priority = 0)
public void Browser_Launch_and_Login_into_Insight_Application() throws Throwable{
	fnApps_Report_Logs("################################## [BS-18] Create Issue From NSF-Online");
	if (!IsBrowserPresentAlready) {
		IsBrowserPresentAlready = TestSuiteBase_NSFOnline.fnsLaunchBrowserAndLogin();
		ClassName_IM_Bs18 = false;
		fnApps_Report_Logs("Browser is launched and Successfully login into 'Insight' Application");	
	}
	fnApps_Report_Logs("=========================================================================================================================================");
}
		
	
	
	
	
	
	
	@Test(priority = 1, dependsOnMethods={"Browser_Launch_and_Login_into_Insight_Application"})
	public void SwitchToNsfOnline_FromInsight_and_Verify_Issue_Create_and_Edit_Successfully_in_NsfOnline_Application() throws Exception{
		
		try{
			
			Insight_Customer_Name = fnReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Insight_CustomerName");
			Insight_User_Name = fnReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Insight_UserName");
			Confirmyoursite_LK_SiteNo = fnReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Confirmyoursite_LK_SiteNo");
			IssueType_DD_Value = fnReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "IssueType_DD_Value");
			IssueSubType_DD_Value = fnReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "IssueSubType_DD_Value");
					
			
			fnApps_Report_Logs("PASSED == Excel Values >>  Insight_Customer_Name==="+Insight_Customer_Name+"     Insight_User_Name===="+Insight_User_Name+"     Confirmyoursite_LK_SiteNo===="+ Confirmyoursite_LK_SiteNo+"     IssueType_DD_Value===="+IssueType_DD_Value+"    IssueSubType_DD_Value===="+IssueSubType_DD_Value);
			
			MainWindowHandle = driver.getWindowHandle();
			

			fnGet_Element_Enabled("Insight_CustomersTopMenuLink");
			WithOut_OR_fnMove_To_Element(OR_IM.getProperty("Insight_CustomersTopMenuLink"));
			Thread.sleep(500);
			WithOut_OR_fnMove_To_Element_and_Click(OR_IM.getProperty("Insight_Customer_SearchCustomer_MenuAjaxLink"));		
		
				
			fnGet_Element_Enabled("Insight_CustomerTxtBox");
			fnWait_and_Type("Insight_CustomerTxtBox", Insight_Customer_Name);
	
			fnWait_and_Click("Insight_Customer_SearchBtn");
	
			fnGet_Element_Enabled("Insight_FirstUserSetUPLink");
			fnWait_and_Click("Insight_FirstUserSetUPLink");
	
			fnGet_Element_Enabled("Insight_UserNameTxtBox");
			Thread.sleep(1000);
			fnWait_and_Type("Insight_UserNameTxtBox", Insight_User_Name);
			
			fnWait_and_Click("Insight_SearchUserBtn");
	
			InsightoriginalHandle = driver.getWindowHandle();
			
			Thread.sleep(1000);
			fnsTable_ClickOn_LINK_ByMatchingText(Insight_User_Name);
			Thread.sleep(8000);
			
			
			
			
			
			try{
				for(int a=0; a<=Long.parseLong(CONFIG.getProperty("ElementWaitTime")); a++){ 
				
					ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
					System.out.println("satyaWindow List of all win >> "+tabs);
				
					tabsCount = tabs.size();
					System.out.println("satyaWindow tabs size >> "+tabsCount);
				
					if(tabsCount==2){
						for (int i = 0; i < tabsCount; i++) {
							if (InsightoriginalHandle.equalsIgnoreCase(tabs.get(i))) {
								System.out.println("satyaWindow Inner If loop tabs.get(i)>> "+tabs.get(i));// nothing to do
							} else {
								System.out.println("satyaWindow switch window ="+tabs.get(i));
								driver.switchTo().window(tabs.get(i));
								
								NSFOnlineoriginalHandle = driver.getWindowHandle();
								System.out.println("satyaWindow NSFOnlineoriginalHandle >>"+NSFOnlineoriginalHandle);
							
								fnApps_Report_Logs("PASSED == Successfully Switch to NSFOnline Window.");
								break;}
							}
						break;
					}else{
						Thread.sleep(1000);
					}
					if(a==Long.parseLong(CONFIG.getProperty("ElementWaitTime"))){
						throw new Exception(" NSF Online window is not getting open after 60Minute wait");
					}
				}
			
				  
				
				Thread.sleep(1500);
				Actions act=new Actions(driver);
				act.moveByOffset(0, 0).click().sendKeys(Keys.TAB).sendKeys(Keys.ENTER).build().perform();
				Thread.sleep(10000);
				
				fnApps_Report_Logs("PASSED == Successfully Click on the 'Open' link")	;
			}catch(Throwable t){
				fnTake_Screen_Shot("SwitchNSFOnlineWindowFail");
				fnApps_Report_Logs("FAILED == NSF Online Window is not getting Open, plz refer screenshot [SwitchNSFOnlineWindowFail"+ fnScreenShot_Date_format() + "].  Getting Exception >> "+Throwables.getStackTraceAsString(t));
				throw new Exception("FAILED == NSF Online Window is not getting Open, plz refer screenshot [SwitchNSFOnlineWindowFail"+ fnScreenShot_Date_format() + "].  Getting Exception >> "+Throwables.getStackTraceAsString(t));	}
		
		
			
			
			
			//Code to capture Screen shot of NSFOnline Application version
			try{
				if(NSFOnlineVersionScreenshot){
				
					fnGet_Element_Enabled("NsfOnline_Version");
					WebElement NSFOnlineVersionWE=fnGet_OR_IM_ObjectX("NsfOnline_Version");
					Actions action = new Actions(driver);
					action.moveToElement(NSFOnlineVersionWE).keyUp(Keys.CONTROL).click().keyDown(Keys.CONTROL).build().perform();
					Thread.sleep(5000);
					
				
					for(int v=0; v<=Long.parseLong(CONFIG.getProperty("ElementWaitTime")); v++){
					
						ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
						System.out.println("satyaWindow List of all win >> "+tabs);
				
						tabsCount = tabs.size();
						System.out.println("satyaWindow tabs size >> "+tabsCount);
				
						if(tabsCount==3){
							for (int i = 0; i < tabsCount; i++) {
								if (NSFOnlineoriginalHandle.equalsIgnoreCase(tabs.get(i))) {
									System.out.println("satyaWindow Inner If loop tabs.get(i)>> "+tabs.get(i));// nothing to do
								} else if(InsightoriginalHandle.equalsIgnoreCase(tabs.get(i))) {
									System.out.println("satyaWindow Inner If loop tabs.get(i)>> "+tabs.get(i));// nothing to do
								}else{
									System.out.println("satyaWindow switch NSFOnline Version window ="+tabs.get(i));
									driver.switchTo().window(tabs.get(i));
									fnGet_Element_Enabled("NsfOnline_Version_BuildInfo_Element");
									fnApps_Report_Logs("PASSED == Successfully switch to NSFOnline Version Window.");
									break;
								}	
							} // for loop end
							break;
						}else{
							Thread.sleep(1000);			}
					
						if(v==Long.parseLong(CONFIG.getProperty("ElementWaitTime"))){
							throw new Exception(" NSF Online Version window is not getting open after 60 minute wait");
						}
					}
		      
				//	Thread.sleep(2000);
					TestSuiteBase_NSFOnline.fnsTake_Screen_Shot_NSFOnlineVersion("IM_Bs18_CreateIssue_From_NSFOnline");
					Thread.sleep(500);
		    	
					driver.close();
					fnApps_Report_Logs("PASSED == Successfully Closed NSFOnlineVersion Window.");
			
					driver.switchTo().window(NSFOnlineoriginalHandle);
					fnApps_Report_Logs("PASSED == Successfully switch to NSF Online Window");
					NSFOnlineVersionScreenshot = false;	
				}
			
			}catch(Throwable t){
				fnTake_Screen_Shot("NSFOnlineVersionScreenShotFail");
				driver.close();
				fnApps_Report_Logs("PASSED == Successfully Close NSFOnline Window.");
				driver.switchTo().window(MainWindowHandle);
				fnApps_Report_Logs("PASSED == Successfully Switch to Insight Window.");
				fnApps_Report_Logs("FAILED ==  NSFOnline Version Window is not getting display, plz refer screenshot [NSFOnlineVersionScreenShotFail"+ fnScreenShot_Date_format() + "].  Getting Exception >> "+Throwables.getStackTraceAsString(t));
				throw new Exception("FAILED ==  NSFOnline Version Window is not getting display, plz refer screenshot [NSFOnlineVersionScreenShotFail"+ fnScreenShot_Date_format() + "].  Getting Exception >> "+Throwables.getStackTraceAsString(t));	}
		
		
		
			
			
			fnGet_Element_Enabled("NSFOnline_Issues_TopMenu_Ajax_Link");
			WithOut_OR_fnMove_To_Element(OR_IM.getProperty("NSFOnline_Issues_TopMenu_Ajax_Link"));
			Thread.sleep(500);
			WithOut_OR_fnMove_To_Element_and_Click(OR_IM.getProperty("NSFOnline_Issues_Ajax_Link"));
			fnsLoading_Progressingwait_NsfOnline(5);
			
			fnGet_Element_Enabled("NSFOnline_ExpandSearchCriteriaLink");
			
			fnGet_Element_Enabled("NSFOnline_CreatIssue_Bttn");
			fnWait_and_Click("NSFOnline_CreatIssue_Bttn");
			fnsLoading_Progressingwait_NsfOnline(2);
			
					
			fnGet_Element_Enabled("NSFOnline_CreatIssue_SiteInfo_Confirmyoursite_LK");
			fnWait_and_Click("NSFOnline_CreatIssue_SiteInfo_Confirmyoursite_LK");
			Thread.sleep(5000);
			
			driver.switchTo().frame(0);
			fnApps_Report_Logs("PASSED == Successfully switch to Site LookUp frame.");
			
			fnGet_Element_Enabled("NSFOnline_CreatIssue_SiteInfo_Confirmyoursite_LK_SiteNo_Text");
			fnWait_and_Type("NSFOnline_CreatIssue_SiteInfo_Confirmyoursite_LK_SiteNo_Text", Confirmyoursite_LK_SiteNo);
			
			fnGet_Element_Enabled("NSFOnline_CreatIssue_SiteInfo_Confirmyoursite_LK_Search_Bttn");
			fnWait_and_Click("NSFOnline_CreatIssue_SiteInfo_Confirmyoursite_LK_Search_Bttn");
			
			fnGet_Element_Enabled("NSFOnline_CreatIssue_SiteInfo_Confirmyoursite_LK_SelectRadio_Bttn");
			fnWait_and_Click("NSFOnline_CreatIssue_SiteInfo_Confirmyoursite_LK_SelectRadio_Bttn");
			
			fnGet_Element_Enabled("NSFOnline_CreatIssue_SiteInfo_Confirmyoursite_LK_SelectReturn_Bttn");
			fnWait_and_Click("NSFOnline_CreatIssue_SiteInfo_Confirmyoursite_LK_SelectReturn_Bttn");
			fnsLoading_Progressingwait_NsfOnline(3);
			
			driver.switchTo().defaultContent();
					
			WithOut_OR_fnDD_SelectValue_By_SelectClass(OR_IM.getProperty("NSFOnline_CreatIssue_SiteInfo_IssueType_DD_Click"), IssueType_DD_Value);
			fnsLoading_Progressingwait_NsfOnline(2);
			
			WithOut_OR_fnDD_SelectValue_By_SelectClass(OR_IM.getProperty("NSFOnline_CreatIssue_SiteInfo_IssueSubType_DD_Click"), IssueSubType_DD_Value);
									
			fnGet_Element_Enabled("NSFOnline_CreatIssue_SiteInfo_CreateIssue_Bttn");
			fnWait_and_Click("NSFOnline_CreatIssue_SiteInfo_CreateIssue_Bttn");
			Thread.sleep(3000);
			
			
			
			fnGet_Element_Enabled("NSFOnline_Validation_Message");
			Issue_ID = fnGet_Field_TEXT("NSFOnline_Validation_Message");
			try{
				assert ( (Issue_ID.toLowerCase()).contains("success")):"FAILED == Issue ID is not created, Getting Error<"+Issue_ID+">, Please refer screenshot >> IssueCreateFail"+fnScreenShot_Date_format();
			}catch(Throwable t){
				fnTake_Screen_Shot("IssueCreateFail");
				throw new Exception(Throwables.getStackTraceAsString(t));	
			}
			Issue_ID = Issue_ID.split("Issue")[1].trim();
			Issue_ID = Issue_ID.split("is")[0].trim();
			fnApps_Report_Logs("PASSED == **************** Successfully created Issue and Issue ID is "+Issue_ID);
			
			
			
			fnGet_Element_Enabled("NSFOnline_CreatIssue_SiteInfo_Next_Bttn");
			fnWait_and_Click("NSFOnline_CreatIssue_SiteInfo_Next_Bttn");
			Thread.sleep(2000);
			fnsLoading_Progressingwait_NsfOnline(1);
			
			
			
			fnGet_Element_Enabled("NSFOnline_CreatIssue_SummaryInfo_SaveNext_Bttn");
			
			fnGet_Element_Enabled("NSFOnline_CreatIssue_SummaryInfo_IssueTitleReasonforIssue_Text");
			Thread.sleep(1000);
			fnWait_and_Click("NSFOnline_CreatIssue_SummaryInfo_IssueTitleReasonforIssue_Text");
			fnWait_and_Type("NSFOnline_CreatIssue_SummaryInfo_IssueTitleReasonforIssue_Text", TitleShortDescriptionText+fnIssueCreationText_Date_format());
			
			
			
			fnGet_Element_Enabled("NSFOnline_CreatIssue_SummaryInfo_DateIncidentOccurred_calander_Bttn");
			fnWait_and_Click("NSFOnline_CreatIssue_SummaryInfo_DateIncidentOccurred_calander_Bttn");
			Thread.sleep(3000);
			driver.switchTo().frame(0);
			fncCalendarDatePicker_BySelectValues_From_DropDown_NSFOnline(TestSuiteBase_Grip.fnsReturn_Requried_Day(-2), TestSuiteBase_Grip.fnsReturn_Requried_Month("MMMM", -1), null);
			driver.switchTo().defaultContent();
			
		
			fnGet_Element_Enabled("NSFOnline_CreatIssue_SummaryInfo_TimeIncidentOccurred_Text");
			fnWait_and_Type("NSFOnline_CreatIssue_SummaryInfo_TimeIncidentOccurred_Text", fnReturn_Requried_Time_HHmm(0));
			
			fnGet_Element_Enabled("NSFOnline_CreatIssue_SummaryInfo_YourName_Text");
			fnWait_and_Type("NSFOnline_CreatIssue_SummaryInfo_YourName_Text", "Testscriptuser");
			
			fnGet_Element_Enabled("NSFOnline_CreatIssue_SummaryInfo_SaveNext_Bttn");
			fnWait_and_Click("NSFOnline_CreatIssue_SummaryInfo_SaveNext_Bttn");
			fnsLoading_Progressingwait_NsfOnline(5);
			
			
			
			fnGet_Element_Enabled("NSFOnline_CreatIssue_DetailsInfo_SaveNext_Bttn");
			fnWait_and_Click("NSFOnline_CreatIssue_DetailsInfo_SaveNext_Bttn");
			fnsLoading_Progressingwait_NsfOnline(3);
			
			
			
			fnGet_Element_Enabled("NSFOnline_CreatIssue_ProductInfo_YES_Radio_Bttn");
			fnWait_and_Click("NSFOnline_CreatIssue_ProductInfo_YES_Radio_Bttn");
			fnsLoading_Progressingwait_NsfOnline(3);
			
			fnGet_Element_Enabled("NSFOnline_CreatIssue_ProductInfo_FreeTextBox");
			fnWait_and_Type("NSFOnline_CreatIssue_ProductInfo_FreeTextBox", TitleShortDescriptionText+fnIssueCreationText_Date_format());
			
			fnGet_Element_Enabled("NSFOnline_CreatIssue_ProductInfo_SaveNext_Bttn");
			fnWait_and_Click("NSFOnline_CreatIssue_ProductInfo_SaveNext_Bttn");
			fnsLoading_Progressingwait_NsfOnline(3);
			
			
			fnGet_Element_Enabled("NSFOnline_CreatIssue_DocumentInfo_ConfirmIssue_Bttn");
			fnWait_and_Click("NSFOnline_CreatIssue_DocumentInfo_ConfirmIssue_Bttn");
			Thread.sleep(5000);
			
			
			fnGet_Element_Enabled("NSFOnline_Validation_Message");
			String Issue_ID_Success_Text = fnGet_Field_TEXT("NSFOnline_Validation_Message").trim();
			
			try{
				assert (  (Issue_ID_Success_Text.toLowerCase()).contains(Issue_ID.toLowerCase())  && (Issue_ID_Success_Text.toLowerCase()).contains("success")  ):"FAILED == Issue Id is not Genrated, getting error < "+Issue_ID_Success_Text+" >";
				fnApps_Report_Logs("PASSED == **************** Successfully verified that Issue Id has been genrated. ( "+Issue_ID+" )");
			}catch (Throwable t){
				fnTake_Screen_Shot("IssueID_Not_Created");
				throw new Exception(Throwables.getStackTraceAsString(t));		
			}
			
			
			
			
			
			
			fnGet_Element_Enabled("NSFOnline_ExpandSearchCriteriaLink");
			fnWait_and_Click("NSFOnline_ExpandSearchCriteriaLink");
			
			
			fnGet_Element_Enabled("NSFOnline_IssueEscalation_SearchID_Input");
			fnWait_and_Type("NSFOnline_IssueEscalation_SearchID_Input", Issue_ID);
			
			
			fnGet_Element_Enabled("NSFOnline_IssueEscalation_Search_Bttn");
			fnWait_and_Click("NSFOnline_IssueEscalation_Search_Bttn");
			fnsLoading_Progressingwait_NsfOnline(3);
			
			fnGet_Element_Enabled("NSFOnline_IssueEscalation_SearchResultTable");
			WithOut_OR_fnMove_To_Element_BY_OFFSET(OR_NsfOnline.getProperty("NSFOnline_IssueEscalation_SearchResultTable"), 30, 10);
						
			String IssueId_Link_Xpath = "//a[contains(text(), '"+Issue_ID+"')]";
			WithOut_OR_fnClick(IssueId_Link_Xpath);
			fnsLoading_Progressingwait_NsfOnline(3);
			
			fncClicking_On_NsfOnline_Issue_TABs("NSFOnline_Issue_Summary_TAB", "NSFOnline_EditIssue_SummaryTAB_SummaryInfo_Header" ,"Summary");
						
			fnGet_Element_Enabled("NSFOnline_Issue_Edit_Button");
			fnWait_and_Click("NSFOnline_Issue_Edit_Button");
			fnsLoading_Progressingwait_NsfOnline(3);
			
			

			fnGet_Element_Enabled("NSFOnline_CreatIssue_SummaryInfo_DateIncidentOccurred_calander_Bttn");
			fnWait_and_Click("NSFOnline_CreatIssue_SummaryInfo_DateIncidentOccurred_calander_Bttn");
			Thread.sleep(3000);
			driver.switchTo().frame(0);
			fncCalendarDatePicker_BySelectValues_From_DropDown_NSFOnline(TestSuiteBase_Grip.fnsReturn_Requried_Day(-3), TestSuiteBase_Grip.fnsReturn_Requried_Month("MMMM", -2), null);
			driver.switchTo().defaultContent();
			
		
			fnGet_Element_Enabled("NSFOnline_CreatIssue_SummaryInfo_TimeIncidentOccurred_Text");
			WithOut_OR_fnClear(OR_IM.getProperty("NSFOnline_CreatIssue_SummaryInfo_TimeIncidentOccurred_Text"));
			fnWait_and_Type("NSFOnline_CreatIssue_SummaryInfo_TimeIncidentOccurred_Text", fnReturn_Requried_Time_HHmm(-20));
			
			String SummaryInfo_EditedTime = fnReturn_Requried_Time_HHmm(-20);
			String SummaryInfo_EditedDate = fnGet_OR_IM_ObjectX("NSFOnline_EditIssue_SummaryTAB_DateIncidentOccurred_Text").getAttribute("value").trim();
			System.out.println("SummaryInfo_EditedDate   ===== "+SummaryInfo_EditedDate);
			
			fnGet_Element_Enabled("NSFOnline_EditIssue_SummaryTAB_SaveReturnToView");
			fnWait_and_Click("NSFOnline_EditIssue_SummaryTAB_SaveReturnToView");
			fnsLoading_Progressingwait_NsfOnline(3);
			
			
			
			fncClicking_On_NsfOnline_Issue_TABs("NSFOnline_Issue_Summary_TAB", "NSFOnline_EditIssue_SummaryTAB_SummaryInfo_Header" ,"Summary");
			
			String Summary_Tab_Data = fnGet_Field_TEXT("NSFOnline_Issue_TAB_DataTable").trim();
			
			
			
			try{
				assert ( Summary_Tab_Data.contains(SummaryInfo_EditedDate) ):"FAILED == 'Date' is not updated in 'Summary' TAB. Script Edited date<"+SummaryInfo_EditedDate+">, Please refer Screenshot[SummaryTabUpdateFail"+fnScreenShot_Date_format();
				String SummaryInfo_EditedDate_Xpath = "//*[text()='"+SummaryInfo_EditedDate+"']";
				WithOut_OR_fnMove_To_Element_and_DoubleClick(SummaryInfo_EditedDate_Xpath);
				fnApps_Report_Logs("PASSED == **************** Successfully updated 'Time' in summary TAB.");
				Thread.sleep(1000);
			}catch(Throwable t){
				fnTake_Screen_Shot("SummaryTabUpdateFail");
				throw new Exception(Throwables.getStackTraceAsString(t));	}
			
			
			
			try{
				assert ( Summary_Tab_Data.contains(SummaryInfo_EditedTime) ):"FAILED == 'Time' is not updated in 'Summary' TAB. Script Edited Time<"+SummaryInfo_EditedTime+">, Please refer Screenshot[SummaryTabUpdateFail"+fnScreenShot_Date_format();
				String SummaryInfo_EditedTime_Xpath = "//*[text()='"+SummaryInfo_EditedTime+"']";
				WithOut_OR_fnMove_To_Element_and_DoubleClick(SummaryInfo_EditedTime_Xpath);
				fnApps_Report_Logs("PASSED == **************** Successfully updated 'Date' in summary TAB.");
				Thread.sleep(1000);
			}catch(Throwable t){
				fnTake_Screen_Shot("SummaryTabUpdateFail");
				throw new Exception(Throwables.getStackTraceAsString(t));	}
			
			
						
			fncClicking_On_NsfOnline_Issue_TABs("NSFOnline_Issue_Detail_TAB", "NSFOnline_EditIssue_DetailTAB_DetailInfo_Header" ,"Detail");
			
			fncClicking_On_NsfOnline_Issue_TABs("NSFOnline_Issue_Product_TAB", "NSFOnline_EditIssue_ProductTAB_ProductInfo_Header" ,"Product");
			
			fncClicking_On_NsfOnline_Issue_TABs("NSFOnline_Issue_Document_TAB", "NSFOnline_EditIssue_DocumentTAB_DocumentInfo_Header" ,"Document");
			
			fncClicking_On_NsfOnline_Issue_TABs("NSFOnline_Issue_Response_TAB", "NSFOnline_EditIssue_ResponseTAB_ResponseInfo_Header" ,"Response");
			
			
			
			
		fnApps_Report_Logs("####################################### [BS-18] Create Issue From NSF-Online Script END ################################################ ");
		fnApps_Report_Logs("=========================================================================================================================================");		
		}catch(SkipException sk){
				throw new SkipException(sk.getMessage());
				
		}catch(NoSuchWindowException W){
			fail = true;
			isTestCasePass = false;
			throw new Exception(W.getMessage());
			
		}catch(Throwable t){
			fail = true;
			isTestCasePass = false;
			fnApps_Report_Logs(" "+Throwables.getStackTraceAsString(t));
			throw new Exception(" "+Throwables.getStackTraceAsString(t));}						
		
}

	

	
	
	
	

	
	
//######################################################### Class Function ######################################################################################	
	//Clicking on Issue  Tabs
	public static void fncClicking_On_NsfOnline_Issue_TABs(String TabXpath, String TabElementXpath, String TabName) throws Throwable{
			try{
			
			fnGet_Element_Enabled(TabXpath);
			for(int clicktry=0; clicktry<4; clicktry++){
				fnWait_and_Click(TabXpath);
				fnsLoading_Progressingwait_NsfOnline(3);
				
				if(driver.findElements(By.xpath(OR_IM.getProperty(TabElementXpath))).size()>0){
					break;
				}
				if(clicktry==3){
					throw new Exception("After 4 time Click on TAB, Tab is not getting opened.");
				}
			}
			fnApps_Report_Logs("PASSED == **************** Successfully opened '"+TabName+"' TAB with NO errors.");
			}catch(Throwable t){
				fnTake_Screen_Shot("TabOpeningFail");
				throw new Exception("FAILED == < "+TabName+" > TAB is not getting opened, Please refer screen shot >> TabOpenFail"+fnScreenShot_Date_format()+" Getting Exception >> "+Throwables.getStackTraceAsString(t));
			}
			
	}
	
	
	
	
	
	
	
	
	
//################################################# Config Functions ################################################	

	@AfterTest
	public void reportTestResult() throws Throwable {
		fns_ReportTestResult_atClassLevel(IssueMgt_Suitexls,  (this.getClass().getSimpleName()) );
	}
		

	@AfterTest
	public void QuitBrowser(){
		
		TestSuiteBase_NSFOnline.MoveMouseCursorToTaskBar();
		driver.quit();
	}
	
	
	
*/}	
	
	
	
	
	
	
	
