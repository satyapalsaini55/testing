package nsf.ecap.NSFOnline_Suite;



import nsf.ecap.util.ErrorUtil;
import nsf.ecap.util.TestUtil;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchWindowException;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;

public class NSFOnline_CSA_IM extends TestSuiteBase_NSFOnline{
	
	public boolean fail = false;
	public boolean skip = true;
	public boolean NSFOnlineVersionScreenshotFlag = true;
	public boolean ViewIssueFlag = false;
	public String ScenaioName = null;
	public Integer count = -1;
	public String TitleShortDescriptionText="[BS-05] NSFOnline SCA IM Validations-Case8, D/T=";
	public String IssueType_DD_Value = null;
	public String IssueSubType_DD_Value = null;
	public String Issue_ID = "I0059310";
	public String Confirmyoursite_LK_SiteNo = null;
	public String Issue_ID_Success_Text = null;
	
	
	

	
	@Parameters({ "className" })
	@BeforeTest
		public void checkTestSkip(String className) throws Exception {
		
		isTestCasePass=true;
		count = -1;
		
		
		try {
			if (className.isEmpty()) {
				className = this.getClass().getSimpleName();}
			
			classNameText=className;
			currentScriptCode=fnpMappingClassNameWithScenarioCode(classNameText).trim();

			if (!TestUtil.isTestCaseRunnable(NSFOnline_suitexls, className)) {
				fnsApps_Report_Logs("Skipping Test Case " + className + " as runmode set to NO");
				throw new SkipException("Skipping Test Case " + className + " as runmode set to NO");
			}

			runmodes = TestUtil.getDataSetRunmodes(NSFOnline_suitexls, className);
		
			fnsApps_Report_Logs("=========================================================================================================================================");
		
		}catch(SkipException sk){
			String errorMsg = sk.getMessage();
			Exception c = new Exception(errorMsg);
			ErrorUtil.addVerificationFailure(c);
			throw new SkipException(errorMsg + " \n\nSo,Skipping Test Case" + className);
		}catch (Throwable t) {		
			isTestCasePass=false;
			fnsTake_Screen_Shot(className);				
			String stackTrace = Throwables.getStackTraceAsString(t);
			String errorMsg = t.getMessage();
			errorMsg = errorMsg + "\n\n\n\nSee screenshot '" + className + "'\n\n" + stackTrace;
			Exception c = new Exception(errorMsg);
			ErrorUtil.addVerificationFailure(c);
			throw new Exception(errorMsg );
		}
}
	
	
	

	
	
	

@Test( priority = 0)
public void Browser_Launch_and_Login_into_Insight_Application() throws Throwable{
	fnsApps_Report_Logs("################################## [BS-05] NSFOnline SCA IM Validations");
	if (!IsBrowserPresentAlready) {
		IsBrowserPresentAlready = fnsLaunchBrowserAndLogin();
		fnsApps_Report_Logs("Browser is launched and Successfully login into 'Insight' Application");	
	}
}
		
	
	
	
	
	
	
	@Test(dataProvider = "getTestData", priority = 1, dependsOnMethods={"Browser_Launch_and_Login_into_Insight_Application"})
	public void NsfOnline_Issue_Management_Validations_(String Serial_No, String Scenario_Name, String Customer_Number,String Insight_Search_User_Name,String Search_IDs, String Multi_Access) throws Exception{
		
		count++;
		Customer_No = Customer_Number.split("=")[1].trim();
		Insight_SearchUserName = Insight_Search_User_Name.split("=")[1].trim();
		CaseSerialNo = Serial_No.split("=")[1];
		ScenaioName=Scenario_Name.trim();
		
		try {
			
			if (!(runmodes[count].equalsIgnoreCase("Y"))) {
				fnsApps_Report_Logs("****************************************************************************************************************************************");
				fnsApps_Report_Logs("################################## Runmode of ("+Scenario_Name+") for Customer["+Customer_No+"]  is set to NO, So Skipping this Case.");
				skip=true;
				throw new SkipException("Runmode of ("+Scenario_Name+") for Customer["+Customer_No+"]  is set to NO, So Skipping this Case.");
			}else{
				skip=false;
				fnsApps_Report_Logs("****************************************************************************************************************************************");
				fnsApps_Report_Logs("################################## Execution Logs of ("+Scenario_Name+") for Customer_No["+Customer_No+"].");
		
							
				try{
					CustomerName =	fnsInsight_SerchCustomer_UserLinkClick(Customer_No, Insight_SearchUserName);
				}catch(Throwable t){
					driver.quit();
					IsBrowserPresentAlready = fnsLaunchBrowserAndLogin();
					throw new Exception("Insight Error : "+t.getMessage());
				}
				
				fncSwitchingTo_NsfOnline_from_Insight_AfterClickingOnUserName();
				
				// New Code 9.2.2016			due to jira - NOM-1503, New Code has been added.	
				fnsSwitchViewAccount_Functionality(Customer_No, CustomerName, Multi_Access.trim());	
			

				
				try{
					if(NSFOnlineVersionScreenshotFlag){
					/*	fncNsfOnline_Version_Screenshot("CSA_IM_Customer_"+Customer_No);*/
						NSFOnlineVersionScreenshotFlag = false;
					}
				}catch(Throwable t){
					driver.close();
					fnsApps_Report_Logs("PASSED == Successfully Close NSFOnline Window.");
					driver.switchTo().window(MainWindowHandle);
					fnsApps_Report_Logs("PASSED == Successfully Switch to Insight Window.");
					throw new Exception(t.getMessage());
				}
			} 
					
			
			
			
			
					
			if(ScenaioName.contains("IssueManagement_Create_Issue")){
				try{
					
					Confirmyoursite_LK_SiteNo = Search_IDs.split(":")[0].trim();
					Confirmyoursite_LK_SiteNo = Confirmyoursite_LK_SiteNo.split("=")[1].trim();
					Confirmyoursite_LK_SiteNo = Confirmyoursite_LK_SiteNo.split("\\)")[0].trim();
					
					IssueType_DD_Value = Search_IDs.split(":")[1].trim();
					IssueType_DD_Value = IssueType_DD_Value.split("=")[1].trim();
					IssueType_DD_Value = IssueType_DD_Value.split("\\)")[0].trim();
					
					IssueSubType_DD_Value = Search_IDs.split(":")[2].trim();
					IssueSubType_DD_Value = IssueSubType_DD_Value.split("=")[1].trim();
					IssueSubType_DD_Value = IssueSubType_DD_Value.split("\\)")[0].trim();
					
					
					fnsGet_Element_Enabled("NSFOnline_Issues_TopMenu_Ajax_Link");
					TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element(OR_IM.getProperty("NSFOnline_Issues_TopMenu_Ajax_Link"));
					Thread.sleep(500);
					TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element_and_Click(OR_IM.getProperty("NSFOnline_Issues_Ajax_Link"));
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(5);
					
					fnsGet_Element_Enabled("NSFOnline_ExpandSearchCriteriaLink");
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(2);
					
					fnsGet_Element_Enabled("NSFOnline_CreatIssue_Bttn");
					fnsWait_and_Click("NSFOnline_CreatIssue_Bttn");
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(2);
					
							
					fnsGet_Element_Enabled("NSFOnline_CreatIssue_SiteInfo_Confirmyoursite_LK");
					fnsWait_and_Click("NSFOnline_CreatIssue_SiteInfo_Confirmyoursite_LK");
					Thread.sleep(5000);
					
					driver.switchTo().frame(0);
					fnsApps_Report_Logs("PASSED == Successfully switch to Site LookUp frame.");
					
					fnsGet_Element_Enabled("NSFOnline_CreatIssue_SiteInfo_Confirmyoursite_LK_SiteNo_Text");
					fnsWait_and_Type("NSFOnline_CreatIssue_SiteInfo_Confirmyoursite_LK_SiteNo_Text", Confirmyoursite_LK_SiteNo);
					
					fnsGet_Element_Enabled("NSFOnline_CreatIssue_SiteInfo_Confirmyoursite_LK_Search_Bttn");
					fnsWait_and_Click("NSFOnline_CreatIssue_SiteInfo_Confirmyoursite_LK_Search_Bttn");
					
					fnsGet_Element_Enabled("NSFOnline_CreatIssue_SiteInfo_Confirmyoursite_LK_SelectRadio_Bttn");
					fnsWait_and_Click("NSFOnline_CreatIssue_SiteInfo_Confirmyoursite_LK_SelectRadio_Bttn");
					
					fnsGet_Element_Enabled("NSFOnline_CreatIssue_SiteInfo_Confirmyoursite_LK_SelectReturn_Bttn");
					fnsWait_and_Click("NSFOnline_CreatIssue_SiteInfo_Confirmyoursite_LK_SelectReturn_Bttn");
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(3);
					
					driver.switchTo().defaultContent();
					if(NsfOnline_New_Version_RUN){
						fnsSwitching_into_First_Frame();
					}
					
												
					TestSuiteBase_MonitorPlan.WithOut_OR_fnDD_SelectValue_By_SelectClass(OR_IM.getProperty("NSFOnline_CreatIssue_SiteInfo_IssueType_DD_Click"), IssueType_DD_Value);
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(2);
					
					TestSuiteBase_MonitorPlan.WithOut_OR_fnDD_SelectValue_By_SelectClass(OR_IM.getProperty("NSFOnline_CreatIssue_SiteInfo_IssueSubType_DD_Click"), IssueSubType_DD_Value);
											
					fnsGet_Element_Enabled("NSFOnline_CreatIssue_SiteInfo_CreateIssue_Bttn");
					fnsWait_and_Click("NSFOnline_CreatIssue_SiteInfo_CreateIssue_Bttn");
					Thread.sleep(3000);
					
					
					
					fnsGet_Element_Enabled("NSFOnline_Validation_Message");
					Issue_ID_Success_Text = fnsGet_Field_TEXT("NSFOnline_Validation_Message");
					try{
						assert ( (Issue_ID_Success_Text.toLowerCase()).contains("Info: Issue is created in Draft status. You must Confirm this Issue before NSF will act on it".toLowerCase())):"FAILED == Issue is not created, Getting Error<"+Issue_ID_Success_Text+">, Please refer screenshot >> IssueCreateFail"+fnsScreenShot_Date_format();
						fnsApps_Report_Logs("PASSED == Issue created.");
					}catch(Throwable t){
						fnsTake_Screen_Shot("IssueCreateFail");
						throw new Exception(t.getMessage());	
					}
					
					fnsGet_Element_Enabled("NSFOnline_Issue_Close_Bttn");
					fnsWait_and_Click("NSFOnline_Issue_Close_Bttn");
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(2);
					
					
					fnsGet_Element_Enabled("NSFOnline_Issues_TopMenu_Ajax_Link");
					TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element(OR_IM.getProperty("NSFOnline_Issues_TopMenu_Ajax_Link"));
					Thread.sleep(500);
					TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element_and_Click(OR_IM.getProperty("NSFOnline_Issues_Ajax_Link"));
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(5);
					
					fnsGet_Element_Enabled("NSFOnline_ExpandSearchCriteriaLink");
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(2);
					
					try{
						assert ( driver.findElements(By.xpath(OR_NsfOnline.getProperty("NSFOnline_Issue_IssueDraft_Link"))).size()>0  ):"FAILED == Issue is not created in DRAFT status, Please refer screenshot >> IssueCreate_in_Draft_Fail"+fnsScreenShot_Date_format();
						fnsApps_Report_Logs("PASSED == Issue is created in Draft Status.");
					}catch(Throwable t){
						fnsTake_Screen_Shot("IssueCreate_in_Draft_Fail");
						throw new Exception(t.getMessage());	
					}
					
					fnsWait_and_Click("NSFOnline_Issue_IssueDraft_Link");
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(2);
					
					
					
					
					
					
					
					
					
					
				
					
					
					
					fnsGet_Element_Enabled("NSFOnline_CreatIssue_SiteInfo_Next_Bttn");
					fnsWait_and_Click("NSFOnline_CreatIssue_SiteInfo_Next_Bttn");
					Thread.sleep(2000);
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(1);
					
					
					
					fnsGet_Element_Enabled("NSFOnline_CreatIssue_SummaryInfo_SaveNext_Bttn");
					
					fnsGet_Element_Enabled("NSFOnline_CreatIssue_SummaryInfo_IssueTitleReasonforIssue_Text");
					Thread.sleep(1000);
					fnsWait_and_Click("NSFOnline_CreatIssue_SummaryInfo_IssueTitleReasonforIssue_Text");
					fnsWait_and_Type("NSFOnline_CreatIssue_SummaryInfo_IssueTitleReasonforIssue_Text", TitleShortDescriptionText+fnIssueCreationText_Date_format());
					
					
					
					fnsGet_Element_Enabled("NSFOnline_CreatIssue_SummaryInfo_DateIncidentOccurred_calander_Bttn");
					fnsWait_and_Click("NSFOnline_CreatIssue_SummaryInfo_DateIncidentOccurred_calander_Bttn");
					Thread.sleep(3000);
					driver.switchTo().frame(0);
				//	TestSuiteBase_IM.fncCalendarDatePicker_BySelectValues_From_DropDown_NSFOnline(TestSuiteBase_Grip.fnsReturn_Requried_Day(-2), TestSuiteBase_Grip.fnsReturn_Requried_Month("MMMM", 0), null);
					fncCalendarDatePicker_BySelectValues_From_DropDown_NSFOnline(TestSuiteBase_Grip.fnsReturn_Requried_Day(0), TestSuiteBase_Grip.fnsReturn_Requried_Month("MMMM", 0),TestSuiteBase_Grip.fnsReturn_Requried_Year(-1));
					
					driver.switchTo().defaultContent();
					if(NsfOnline_New_Version_RUN){
						fnsSwitching_into_First_Frame();
					}
					
					
				
					fnsGet_Element_Enabled("NSFOnline_CreatIssue_SummaryInfo_TimeIncidentOccurred_Text");
					fnsWait_and_Type("NSFOnline_CreatIssue_SummaryInfo_TimeIncidentOccurred_Text", fnReturn_Requried_Time_HHmm(0));
					
					fnsGet_Element_Enabled("NSFOnline_CreatIssue_SummaryInfo_YourName_Text");
					fnsWait_and_Type("NSFOnline_CreatIssue_SummaryInfo_YourName_Text", "Testscriptuser");
					
					fnsGet_Element_Enabled("NSFOnline_CreatIssue_SummaryInfo_SaveNext_Bttn");
					fnsWait_and_Click("NSFOnline_CreatIssue_SummaryInfo_SaveNext_Bttn");
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(5);
					
					
					
					fnsGet_Element_Enabled("NSFOnline_CreatIssue_DetailsInfo_SaveNext_Bttn");
					fnsWait_and_Click("NSFOnline_CreatIssue_DetailsInfo_SaveNext_Bttn");
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(3);
					
					
					
					fnsGet_Element_Enabled("NSFOnline_CreatIssue_ProductInfo_YES_Radio_Bttn");
					fnsWait_and_Click("NSFOnline_CreatIssue_ProductInfo_YES_Radio_Bttn");
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(3);
					
					fnsGet_Element_Enabled("NSFOnline_CreatIssue_ProductInfo_FreeTextBox");
					fnsWait_and_Type("NSFOnline_CreatIssue_ProductInfo_FreeTextBox", TitleShortDescriptionText+fnIssueCreationText_Date_format());
					
					fnsGet_Element_Enabled("NSFOnline_CreatIssue_ProductInfo_SaveNext_Bttn");
					fnsWait_and_Click("NSFOnline_CreatIssue_ProductInfo_SaveNext_Bttn");
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(3);
					
					
					fnsGet_Element_Enabled("NSFOnline_CreatIssue_DocumentInfo_ConfirmIssue_Bttn");
					fnsWait_and_Click("NSFOnline_CreatIssue_DocumentInfo_ConfirmIssue_Bttn");
					Thread.sleep(5000);
					
					
					fnsGet_Element_Enabled("NSFOnline_Validation_Message");
					Issue_ID_Success_Text = fnsGet_Field_TEXT("NSFOnline_Validation_Message").trim();
					

					
					
					try{
						assert (  (Issue_ID_Success_Text.toLowerCase()).contains("success")  ):"FAILED == Issue Id is not Genrated, getting error < "+Issue_ID_Success_Text+" >";
						
					}catch (Throwable t){
						fnsTake_Screen_Shot("IssueID_Not_Created");
						throw new Exception(t.getMessage());		
					}
								
					
					Issue_ID = Issue_ID_Success_Text.split("Issue")[1].trim();
					Issue_ID = Issue_ID.split("successfully")[0].trim();
					fnsApps_Report_Logs("PASSED == **************** Successfully verified that Issue Id has been genrated. ( "+Issue_ID+" )");
					
					
					
					fnsGet_Element_Enabled("NSFOnline_ExpandSearchCriteriaLink");
					fnsWait_and_Click("NSFOnline_ExpandSearchCriteriaLink");
					
					
					fnsGet_Element_Enabled("NSFOnline_IssueEscalation_SearchID_Input");
					fnsWait_and_Type("NSFOnline_IssueEscalation_SearchID_Input", Issue_ID);
					
					
					fnsGet_Element_Enabled("NSFOnline_IssueEscalation_Search_Bttn");
					fnsWait_and_Click("NSFOnline_IssueEscalation_Search_Bttn");
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(3);
					
					fnsGet_Element_Enabled("NSFOnline_IssueEscalation_SearchResultTable");
					TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element_BY_OFFSET(OR_NsfOnline.getProperty("NSFOnline_IssueEscalation_SearchResultTable"), 30, 10);
								
					String IssueId_Link_Xpath = "//a[contains(text(), '"+Issue_ID+"')]";
					TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(IssueId_Link_Xpath);
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(3);
					
					fncClicking_On_NsfOnline_Issue_TABs("NSFOnline_Issue_Summary_TAB", "NSFOnline_EditIssue_SummaryTAB_SummaryInfo_Header" ,"Summary");
								
					fnsGet_Element_Enabled("NSFOnline_Issue_Edit_Button");
					fnsWait_and_Click("NSFOnline_Issue_Edit_Button");
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(3);
					
	
					fnsGet_Element_Enabled("NSFOnline_CreatIssue_SummaryInfo_DateIncidentOccurred_calander_Bttn");
					fnsWait_and_Click("NSFOnline_CreatIssue_SummaryInfo_DateIncidentOccurred_calander_Bttn");
					Thread.sleep(3000);
					driver.switchTo().frame(0);
				//	TestSuiteBase_IM.fncCalendarDatePicker_BySelectValues_From_DropDown_NSFOnline(TestSuiteBase_Grip.fnsReturn_Requried_Day(-3), TestSuiteBase_Grip.fnsReturn_Requried_Month("MMMM", 0), null);
					fncCalendarDatePicker_BySelectValues_From_DropDown_NSFOnline(TestSuiteBase_Grip.fnsReturn_Requried_Day(-1), TestSuiteBase_Grip.fnsReturn_Requried_Month("MMMM", -1),TestSuiteBase_Grip.fnsReturn_Requried_Year(-1));
					
					driver.switchTo().defaultContent();
					if(NsfOnline_New_Version_RUN){
						fnsSwitching_into_First_Frame();
					}
					
				
					fnsGet_Element_Enabled("NSFOnline_CreatIssue_SummaryInfo_TimeIncidentOccurred_Text");
					TestSuiteBase_MonitorPlan.WithOut_OR_fnClear(OR_IM.getProperty("NSFOnline_CreatIssue_SummaryInfo_TimeIncidentOccurred_Text"));
					fnsWait_and_Type("NSFOnline_CreatIssue_SummaryInfo_TimeIncidentOccurred_Text", fnReturn_Requried_Time_HHmm(-20));
					
					String SummaryInfo_EditedTime = fnReturn_Requried_Time_HHmm(-20);
					String SummaryInfo_EditedDate = fnsGet_OR_NsfOnline_ObjectX("NSFOnline_EditIssue_SummaryTAB_DateIncidentOccurred_Text").getAttribute("value").trim();
					System.out.println("SummaryInfo_EditedDate   ===== "+SummaryInfo_EditedDate);
					
					fnsGet_Element_Enabled("NSFOnline_EditIssue_SummaryTAB_SaveReturnToView");
					fnsWait_and_Click("NSFOnline_EditIssue_SummaryTAB_SaveReturnToView");
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(3);
					
							
					fncClicking_On_NsfOnline_Issue_TABs("NSFOnline_Issue_Summary_TAB", "NSFOnline_EditIssue_SummaryTAB_SummaryInfo_Header" ,"Summary");
					
					String Summary_Tab_Data = fnsGet_Field_TEXT("NSFOnline_Issue_TAB_DataTable").trim();
					
					String SummaryInfo_EditedDate_Xpath = "//*[text()='"+SummaryInfo_EditedDate+"']";
					TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element_and_DoubleClick(SummaryInfo_EditedDate_Xpath);
					try{
						assert ( Summary_Tab_Data.contains(SummaryInfo_EditedDate) ):"FAILED == 'Date' is not updated in 'Summary' TAB. Script Edited date<"+SummaryInfo_EditedDate+">, Please refer Screenshot[SummaryTabUpdateFail"+fnsScreenShot_Date_format();
						fnsApps_Report_Logs("PASSED == **************** Successfully updated 'Time' in summary TAB.");
						Thread.sleep(1000);
					}catch(Throwable t){
						fnsTake_Screen_Shot("SummaryTabUpdateFail");
						throw new Exception(t.getMessage());	}
					
					
					String SummaryInfo_EditedTime_Xpath = "//*[text()='"+SummaryInfo_EditedTime+"']";
					TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element_and_DoubleClick(SummaryInfo_EditedTime_Xpath);
					try{
						assert ( Summary_Tab_Data.contains(SummaryInfo_EditedTime) ):"FAILED == 'Time' is not updated in 'Summary' TAB. Script Edited Time<"+SummaryInfo_EditedTime+">, Please refer Screenshot[SummaryTabUpdateFail"+fnsScreenShot_Date_format();
						fnsApps_Report_Logs("PASSED == **************** Successfully updated 'Date' in summary TAB.");
						Thread.sleep(1000);
					}catch(Throwable t){
						fnsTake_Screen_Shot("SummaryTabUpdateFail");
						throw new Exception(t.getMessage());	}
								
					fncClicking_On_NsfOnline_Issue_TABs("NSFOnline_Issue_Detail_TAB", "NSFOnline_EditIssue_DetailTAB_DetailInfo_Header" ,"Detail");
					
					fncClicking_On_NsfOnline_Issue_TABs("NSFOnline_Issue_Product_TAB", "NSFOnline_EditIssue_ProductTAB_ProductInfo_Header" ,"Product");
					
					fncClicking_On_NsfOnline_Issue_TABs("NSFOnline_Issue_Document_TAB", "NSFOnline_EditIssue_DocumentTAB_DocumentInfo_Header" ,"Document");
					
					fncClicking_On_NsfOnline_Issue_TABs("NSFOnline_Issue_Response_TAB", "NSFOnline_EditIssue_ResponseTAB_ResponseInfo_Header" ,"Response");
				}catch(Throwable t){
					driver.close();
					fnsApps_Report_Logs("PASSED == Successfully Close NSFOnline Window.");
					driver.switchTo().window(MainWindowHandle);
					fnsApps_Report_Logs("PASSED == Successfully Switch to Insight Window.");
					throw new Exception("...."+Throwables.getStackTraceAsString(t));	}
				
			}else{	
			
				//Verify for Good Bad
				try{
					String NoOfSets[] = Search_IDs.split(":");
					fnsApps_Report_Logs("Sets data are ["+Search_IDs+"]. and No. of "+" Ids are == " + NoOfSets.length);
			
					for (int j = 0; j < NoOfSets.length; j++) {
						ViewIssueFlag = false;
						
						String Search_IDsSet = NoOfSets[j];
						fnsApps_Report_Logs("****************************************************************************************************");
						fnsApps_Report_Logs("************************ Current searching "+" Id is [" + Search_IDsSet+"]  *************************");
									
						//ScenaioName=Scenario_Name.toLowerCase().trim();
						//System.out.println("ScenaioName =======  "+ScenaioName);
							
						if(ScenaioName.contains(("IssueManagement_Client_IssueValidation").trim()) || ScenaioName.contains(("IssueManagement_Supplier_IssueValidation").trim())){			
							fncAjax_Link_Click_By_Passing_TwoAjaxXPathx("NSFOnline_Issues_TopMenu_Ajax_Link", "NSFOnline_Issues_Ajax_Link");
						}
									
						TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(5);
						fnsGet_Element_Enabled("NSFOnline_ExpandSearchCriteriaLink");
						fnsWait_and_Click("NSFOnline_ExpandSearchCriteriaLink");
						TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(2);
						
										
						String CurrentSet[] = Search_IDsSet.split(",");

						String Search_IDNo = fnsRemoveFormatting(CurrentSet[0]);
						String GoodBadCondition = fnsRemoveFormatting(CurrentSet[1]).toLowerCase().trim();
						
						fnsGet_Element_Enabled("NSFOnline_IssueEscalation_SearchID_Input");
						fnsWait_and_Type("NSFOnline_IssueEscalation_SearchID_Input", Search_IDNo);
						
						
						fnsGet_Element_Enabled("NSFOnline_IssueEscalation_Search_Bttn");
						fnsWait_and_Click("NSFOnline_IssueEscalation_Search_Bttn");
						TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(4);
						
						fnsGet_Element_Enabled("NSFOnline_IssueEscalation_SearchResultTable");
						TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element_BY_OFFSET(OR_NsfOnline.getProperty("NSFOnline_IssueEscalation_SearchResultTable"), 30, 10);
						String SearchResult_GetText=fnsGet_Field_TEXT("NSFOnline_IssueEscalation_SearchResultTable").toLowerCase().trim();
							
											
						
						try{
							if (GoodBadCondition.equalsIgnoreCase("good")) {
								Thread.sleep(500);
								assert (SearchResult_GetText.contains(Search_IDNo.toLowerCase())):"FAILED == Issue id <"+Search_IDNo+"> for Data  [CustomerNo#"+Customer_No+" : UserName#"+Insight_SearchUserName+"]  is not coming 'GOOD'<i.e. SearchId is not appeared into the records Table>, ,Please refer Screenshot["+GoodBadCondition+"_Fail"+fnsScreenShot_Date_format();
								fnsApps_Report_Logs("PASSED == Issue id <"+Search_IDNo+"> for Data  [CustomerNo#"+Customer_No+" : UserName#"+Insight_SearchUserName+"]  is coming 'GOOD'<i.e. SearchID is appeared into the records Table>.");
								ViewIssueFlag = true;
							}else {
								Thread.sleep(2000);
								assert (SearchResult_GetText.contains("no items were found")):"FAILED == Issue id <"+Search_IDNo+"> for Data  [CustomerNo#"+Customer_No+" : UserName#"+Insight_SearchUserName+"]  is not coming 'BAD'<i.e. SearchID is appeared into the records Table>,,Please refer Screenshot["+GoodBadCondition+"_Fail"+fnsScreenShot_Date_format();	
								fnsApps_Report_Logs("PASSED == Issue id <"+Search_IDNo+"> for Data  [CustomerNo#"+Customer_No+" : UserName#"+Insight_SearchUserName+"]  is coming 'BAD'<i.e. SearchID is not appeared into the records Table>.");	
							}
						}catch(Throwable t){
							fnsTake_Screen_Shot(GoodBadCondition+"_Fail");
							throw new Exception(t.getMessage());	}
						
						
						
						if(ViewIssueFlag){
							String IssueId_Link_Xpath = "//a[contains(text(), '"+Search_IDNo+"')]";
							//String IssueId_Link_Xpath = "//a[text()='"+Search_IDNo+"']";
							TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(IssueId_Link_Xpath);
							TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(4);
							fnsGet_Element_Enabled("Footer");
														
							String Header_Info_Text = fnsGet_Field_TEXT("NsfOnline_ViewIssue_HeaderInfo").toLowerCase().trim();
							String Header_Info_IssueId_Xpath = OR_NsfOnline.getProperty("NsfOnline_ViewIssue_HeaderInfo")+"/tbody/tr[1]/td[1]/table/tbody/tr[1]/td[2]/span";
							TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element_and_DoubleClick(Header_Info_IssueId_Xpath);
						
							try{
								Thread.sleep(2000);
								assert (Header_Info_Text.contains(Search_IDNo.toLowerCase())):"FAILED == 'View Issue' screen is not getting opened, Please refer Screenshot[ViewIssesScreenOpenFail"+fnsScreenShot_Date_format();
								fnsApps_Report_Logs("PASSED == Successfully opened 'View Issue' screen and Issue id <"+Search_IDNo+"> is displayed on the screen.");
							}catch(Throwable t){
								fnsTake_Screen_Shot("ViewIssesScreenOpenFail");
								throw new Exception(t.getMessage());	}
						}
						
					}	
				}catch(Throwable t){
					isTestCasePass = false;
					driver.close();
					fnsApps_Report_Logs("PASSED == Successfully Close NSFOnline Window.");
					driver.switchTo().window(MainWindowHandle);
					fnsApps_Report_Logs("PASSED == Successfully Switch to Insight Window.");
					throw new Exception("...."+Throwables.getStackTraceAsString(t));	}
	
			}
		
				driver.close();
				fnsApps_Report_Logs("PASSED == Successfully Close NSFOnline Window.");
				driver.switchTo().window(MainWindowHandle);
				fnsApps_Report_Logs("PASSED == Successfully Switch to Insight Window.");
		
	}catch(SkipException sk){
			throw new SkipException(sk.getMessage());
			
	}catch(NoSuchWindowException W){
		fail = true;
		isTestCasePass = false;
		throw new Exception(W.getMessage());
		
	}catch(Throwable t){
		fail = true;
		isTestCasePass = false;
		fnsApps_Report_Logs("Case:"+CaseSerialNo.trim()+"......................"+Throwables.getStackTraceAsString(t)+"......................");
		throw new Exception("Case:"+CaseSerialNo.trim()+"......................"+Throwables.getStackTraceAsString(t)+"......................");}						
		
}

	

	
	
	
	
	
	
	

	
	
	
	
	
	
//########################################################  Class Function ########################################################
	//Clicking on Issue  Tabs
	public void fncClicking_On_NsfOnline_Issue_TABs(String TabXpath, String TabElementXpath, String TabName) throws Throwable{
			try{
			
			fnsGet_Element_Enabled(TabXpath);
			for(int clicktry=0; clicktry<4; clicktry++){
				fnsWait_and_Click(TabXpath);
				TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(3);
				
				if(driver.findElements(By.xpath(OR_IM.getProperty(TabElementXpath))).size()>0){
					break;
				}
				if(clicktry==3){
					throw new Exception("After 4 time Click on TAB, Tab is not getting opened.");
				}
			}
			fnsApps_Report_Logs("PASSED == **************** Successfully opened '"+TabName+"' TAB with NO errors.");
			}catch(Throwable t){
				fnsTake_Screen_Shot("TabOpeningFail");
				throw new Exception("FAILED == < "+TabName+" > TAB is not getting opened, Please refer screen shot >> TabOpenFail"+fnsScreenShot_Date_format()+" Getting Exception >> "+Throwables.getStackTraceAsString(t)+"......................");
			}
			
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	@AfterMethod
	public void reportDataSetResult() {
		if(count>-1){
			if (skip)
				TestUtil.reportDataSetResult(NSFOnline_suitexls, this.getClass().getSimpleName(), count + 2 , "SKIP");
			else if (fail) {
				isTestCasePass = false;
				TestUtil.reportDataSetResult(NSFOnline_suitexls, this.getClass().getSimpleName(), count + 2, "FAIL");
			} else
				TestUtil.reportDataSetResult(NSFOnline_suitexls, this.getClass().getSimpleName(), count + 2, "PASS");
	
			skip = false;
			fail = false;
		}
}

	
	@AfterTest
	public void reportTestResult() throws Throwable {
		
		TestSuiteBase_MonitorPlan.fns_ReportTestResult_atClassLevel(NSFOnline_suitexls,  (this.getClass().getSimpleName()) , isTestCasePass);
	}
	

	@AfterTest
	public void QuitBrowser(){
		try{
			MoveMouseCursorToTaskBar();
			driver.quit();
		}catch(Throwable t){
			//nothing to do
		}
	}
	
	
	
	@DataProvider
	public Object[][] getTestData() {
		return TestUtil.getData(NSFOnline_suitexls, this.getClass().getSimpleName());
	}

	
	
}	
	
	
	
	
	
	
	
