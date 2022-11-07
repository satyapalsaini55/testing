package nsf.ecap.IssueMgt_Suite;

import java.util.HashMap;
import java.util.Properties;


//
import nsf.ecap.util.*;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.os.WindowsUtils;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;

public class CreateEscalation_AFP extends TestSuiteBase_IM{
	
	public static String IssueId;//="I0039875";
	public static String issue_id_amber;//="I0039875";
	public static String issue_id_red;//="I0039876";
	public static String Escalation_Id;//="E0003339";
	public static String validation_error_msg_text;
	public static String TitleShortDescriptionText="CreateEsc_AFP[BS-13], D/T=";	
	
	public static boolean IsBrowserPresentAlready = false;
		
	

	
	@Parameters({ "className" })
	@BeforeTest
		public void checkTestSkip(String className) throws Exception {
		
		isTestCasePass=true;
		
		
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

		
			fnApps_Report_Logs("=========================================================================================================================================");
		
		}
		catch(SkipException sk){
			String stackTrace = Throwables.getStackTraceAsString(sk);
			String errorMsg = sk.getMessage();
			Exception c = new Exception(errorMsg);
			ErrorUtil.addVerificationFailure(c);
			throw new SkipException(errorMsg + " \n\nSo,Skipping Test Case" + className);
		}
		catch (Throwable t) {
								
				String stackTrace = Throwables.getStackTraceAsString(t);
				String errorMsg = Throwables.getStackTraceAsString(t);
				errorMsg = errorMsg + "\n\n\n\nSee screenshot '" + className + "'\n\n" + stackTrace;
				Exception c = new Exception(errorMsg);
				ErrorUtil.addVerificationFailure(c);
				throw new Exception(errorMsg );
		}
}
	
	
	
	
	
	
	
	@BeforeClass (description="OracleQueryLaunch_ToClosed_OpenIssues_and_InActiveAlerts_for ---  Client(Demo Customer)")
	public void OracleQueryLaunch_ToClosed_OpenIssues_and_InActiveAlerts() throws Throwable {

		//Function Called to close all issues in database
		fnUpdateDB_to_Close_AFP_issues();
		
	}
	
	
	
	
	

@Test( priority = 0)
public void Launch_Browser_and_Successfully_Login_into_the_Application() throws Throwable{
	fnApps_Report_Logs("################################## [BS-13]  Create Escalation AFP");
	if (!IsBrowserPresentAlready) {
		IsBrowserPresentAlready = fnLaunchBrowserAndLogin();
		fnApps_Report_Logs("Browser is launched and ******** Successfully login into 'I-Pulse' Application.");
	}
}
	
	
	
	@Test(dependsOnMethods={"Launch_Browser_and_Successfully_Login_into_the_Application"}, priority = 1, description="  [Create Issue ID with Black Color]")
	public void Escalation_AFP_FirstIssue_Black() throws Throwable{
		
			
		fnApps_Report_Logs("################### Test Case ::::::1 Escalation_AFP_FirstIssue_Black ");
	//	IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//src//nsf//ecap//xls//IssueMgt_Suite.xlsx");
		
		  
				
		
		Create_AFP_Issue_Without_IssueID_Verification("1", IssueMgt_Suitexls.getCellData("Escalation_AFP", 2, 7), IssueMgt_Suitexls.getCellData("Escalation_AFP", 2, 8), TitleShortDescriptionText);
		
		fnsLoading_Progressingwait(2);
		String FetchIssueIdText=fnGet_Field_TEXT("AFP_IssueID_GetText");
		IssueId=(FetchIssueIdText.split("\\[")[0]).trim();
		
		
		
		try{
			assert FetchIssueIdText.contains(IssueId):"FAILED == First issue id  not Generated >> "+FetchIssueIdText+"  ,Please refer ScreenShot [ "+IssueId+fnScreenShot_Date_format()+" ].";
			fnApps_Report_Logs("PASSED == First Issue ID has been generated  === "+IssueId);
			
		}
		catch(Throwable t){
			isTestCasePass = false;
			fnTake_Screen_Shot(IssueId);
			fnApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));}
		
		
		
		
		try{
			assert (!(FetchIssueIdText.contains("GREEN")) && !(FetchIssueIdText.contains("AMBER")) && !(FetchIssueIdText.contains("RED"))  ):"FAILED == First issue id Generated, but color is not BLACK"+" >> "+FetchIssueIdText+"  ,Please refer ScreenShot [ "+IssueId+" ].";
			fnApps_Report_Logs("PASSED == First Issue ID has been generated and Color is  BLACK"+" === "+FetchIssueIdText);
			}
		catch(Throwable t){
			isTestCasePass = false;
			fnTake_Screen_Shot(IssueId);
			fnApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));}
		
	
}
	
	
	
	
	
	
	@Test(dependsOnMethods={"Escalation_AFP_FirstIssue_Black"}, priority = 2, description="  [Go to Details tab and mention the 'No of people sick' as (2).Click on Save.]")
	public void Escalation_AFP_Verify_ColorBecome_AMBER_When_NoOfPeopleSick_2() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::2 Escalation_AFP_Verify_ColorBecome_AMBER_When_NoOfPeopleSick_2 ");
		WebDriverWait waitvar = new WebDriverWait(driver, Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
		
		fncIssue_DetailsTAB_RadioButtonsSelect_and_Verify_SaveSuccessfully();
		
		fnGet_Element_Enabled("ViewIssue_details_No_Of_People_Sick_TextField");
		WithOut_OR_fnClear(OR_IM.getProperty("ViewIssue_details_No_Of_People_Sick_TextField"));
		fnWait_and_Type("ViewIssue_details_No_Of_People_Sick_TextField", "5");
		
		
		fnGet_Element_Enabled("ViewIssueSummaryDetails_summary_details_save_button");
		fnWait_and_Click("ViewIssueSummaryDetails_summary_details_save_button");
		fnsLoading_Progressingwait(3);
		

		fnGet_Element_Enabled("validation_error_msg");
		fncVerify_DataUpdated_Successfully("validation_error_msg", "Summary TAB ");	
		
		issue_id_amber=fnVerified_IssueID_Generated_and_Color_Verification(2, "AMBER");
	
	}
	
	
	
	
	
	
	
	
	@Test(dependsOnMethods={"Escalation_AFP_Verify_ColorBecome_AMBER_When_NoOfPeopleSick_2"}, priority = 3, description="  [Go to Details tab and mention the 'No of people sick' as (3).Click on Save.]")
	public void Escalation_AFP_Verify_ColorBecome_RED_When_NoOfPeopleSick_3() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::3 Escalation_AFP_Verify_ColorBecome_RED_When_NoOfPeopleSick_3 ");
		WebDriverWait waitvar = new WebDriverWait(driver, Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
		
		
		//fnWait_and_Click("ViewIssue_details_tab");
		
		fnGet_Element_Enabled("ViewIssue_details_No_Of_People_Sick_TextField");
		fnGet_OR_IM_ObjectX("ViewIssue_details_No_Of_People_Sick_TextField").clear();
		fnWait_and_Type("ViewIssue_details_No_Of_People_Sick_TextField", "7");
		

		fnGet_Element_Enabled("ViewIssueSummaryDetails_summary_details_save_button");
		fnWait_and_Click("ViewIssueSummaryDetails_summary_details_save_button");
		fnsLoading_Progressingwait(3);
		

		fnGet_Element_Enabled("validation_error_msg");
		fncVerify_DataUpdated_Successfully("validation_error_msg", "Summary TAB ");	
		
		issue_id_red=fnVerified_IssueID_Generated_and_Color_Verification(3, "RED");
		
		
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
			
	@Test(dependsOnMethods={"Escalation_AFP_Verify_ColorBecome_RED_When_NoOfPeopleSick_3"}, priority = 4, description="  [The color of the issue should become red and an escalation should get generated with issue.]")
	public void Escalation_AFP_Verify_Escalation_Generated_and_Alerts_VERIFY() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::4 Escalation_AFP_Verify_Escalation_Generated_and_Alerts_VERIFY ");
		
	//	IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//src//nsf//ecap//xls//IssueMgt_Suite.xlsx");
			

		try{
			fnGet_Element_Enabled("Escalation_id_link");
			Thread.sleep(500);
			Escalation_Id=fnGet_Field_TEXT("Escalation_id_link").trim();
					try{
						assert (Escalation_Id.contains("E")):"Escalations ID is not Generated >> "+Escalation_Id+"Please refer ScreenShot [ "+Escalation_Id+fnScreenShot_Date_format() +" ]";	
					  
						fnApps_Report_Logs("Escalations ID has been generated === "+Escalation_Id);
					}catch(Throwable t){
						fnTake_Screen_Shot(Escalation_Id);
						throw new Exception(Throwables.getStackTraceAsString(t));
					}
				
					
			//Clicking on Escalation ID link
			fnWait_and_Click("Escalation_id_link");
			
			
	
			fnGet_Element_Enabled("footer");
			
			for(int i=0; i<5; i++){
				Thread.sleep(1500);
				if(driver.findElements(By.xpath(OR_IM.getProperty("ViewEscalation_RelatedIssues_table"))).size()>0){
					break;
				}
				else{
				driver.navigate().refresh();
				Thread.sleep(5000);
				}
			}
					
	
			try{
				
				//Verify 8 Newly generated issues exists into the table on Escalation details screen.
				String related_issue_table=fnGet_Field_TEXT("ViewEscalation_RelatedIssues_table").trim();
				assert ((related_issue_table.contains(IssueId)) ):
					"FAILED == Issues IDs are not Exists into the Related Issues Table."+"Please refer ScreenShot [ IssueIds_Not_Exists"+fnScreenShot_Date_format()+" ]";	
				fnApps_Report_Logs("PASSED == All issues ids exists into table");
			}catch(Throwable t){
				fnTake_Screen_Shot("IssueIds_Not_Exists");
				throw new Exception(Throwables.getStackTraceAsString(t));}
			
		}catch(Throwable t){
			isTestCasePass = false;
			fnApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		//Verify Escalation Alert and entered Escalation Id into alert search filter
		fnVerify_ADMIN_Alert_redirect_from_summarydetailsPage("AFP_EscalationsUnassigned_text_link","AFP_EscalationsUnassigned_issue_id_filter", Escalation_Id);
		
		//verify Escalation ID exists in Alert Table
		fnVerify_ID_Exist_inAlert_Table("AFP_EscalationsUnassigned_table_1st_cell","AFP_EscalationsUnassigned_text_link", Escalation_Id);
	}
	
	
	
	
	
	
	
	
	
	

	
	@Test(dependsOnMethods={"Escalation_AFP_Verify_Escalation_Generated_and_Alerts_VERIFY"}, priority = 5, description="  [Edit the Escalation and click Save. Click -> Assign to Me button.]")
	public void Escalation_EditEscalation_ClickOn_AssignToMe_Button_andThen_Alerts_Verify() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::5 Escalation_EditEscalation_ClickOn_AssignToMe_Button_andThen_Alerts_Verify ");
		//Search Escalation and also navigate to ViewEscalation EscalationDetails page
		fncEscalation_SearchEscalation_and_Navigate_till_ViewEscalationDetailsPageComes(Escalation_Id);
		
		//Clicking on assign_to_me button
		fnGet_Element_Enabled("Escalation_Summary_assign_to_me_button");
		fnWait_and_Click("Escalation_Summary_assign_to_me_button");
		fnsLoading_Progressingwait(3);
		
		fnGet_Element_Enabled("Escalation_summary_details_save_button");
		fnWait_and_Click("Escalation_summary_details_save_button");
		fnsLoading_Progressingwait(3);
		

		fnGet_Element_Enabled("validation_error_msg");
		fncVerify_DataUpdated_Successfully("validation_error_msg", "Summary TAB ");	
		
		//Verify Alert (Escalations Assigned to Me as Admin)
		fnVerify_ADMIN_Alert_redirect_from_summarydetailsPage("EscalationsAssignedToMeAsAdmin_text_link","EscalationsAssignedToMeAsAdmin_Escalation_id_filter", Escalation_Id);
		fnVerify_ID_Exist_inAlert_Table("EscalationsAssignedToMeAsAdmin_table_1st_cell","EscalationsAssignedToMeAsAdmin_text_link", Escalation_Id);
		
		//Verify Alert (Escalations Assigned to Me. Awaiting Admin action )
		fnVerify_ADMIN_Alert_redirect_from_summarydetailsPage("EscalationsAssignedToMeAwaitingAdminAction_text_link","EscalationsAssignedToMeAwaitingAdminAction_Escalation_id_filter", Escalation_Id);
		fnVerify_ID_Exist_inAlert_Table("EscalationsAssignedToMeAwaitingAdminAction_table_1st_cell","EscalationsAssignedToMeAwaitingAdminAction_text_link", Escalation_Id);	
		
		
		//Verify Alert Removed (AFP Escalations Unassigned)
		fnVerify_ADMIN_Alert_redirect_from_summarydetailsPage("AFP_EscalationsUnassigned_text_link","AFP_EscalationsUnassigned_issue_id_filter", Escalation_Id);
		fnVerify_NoRecordsFound_Msg_for_ID_When_AlertRemoved("AFP_EscalationsUnassigned_table_1st_cell","AFP_EscalationsUnassigned_text_link", Escalation_Id);	
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Test(dependsOnMethods={"Escalation_EditEscalation_ClickOn_AssignToMe_Button_andThen_Alerts_Verify"}, priority = 6, description="  [Change the status from INITIAL RECORD > AdditionalInfoRequired mention new status response>Click save.]")
	public void Escalation_EditEscalation_Change_status_InitialRecord_to_AdditionalInfoRequired_andThen_Alerts_Verify() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::6 Escalation_EditEscalation_Change_status_InitialRecord_to_AdditionalInfoRequired_andThen_Alerts_Verify ");
	
		
		//Search Escalation and also navigate to ViewEscalation EscalationDetails page
		fncEscalation_SearchEscalation_and_Navigate_till_ViewEscalationDetailsPageComes(Escalation_Id);
		
		fncEscalation_VerifyChange_Status_on_SummaryDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_AFP", 2, 11));
		

		fnGet_Element_Enabled("validation_error_msg");
		fncVerify_DataUpdated_Successfully("validation_error_msg", "Summary TAB ");	
		
		//Verify Alert (Escalations Assigned to Me as Admin)
		fnVerify_ADMIN_Alert_redirect_from_summarydetailsPage("EscalationsAssignedToMeAsAdmin_text_link","EscalationsAssignedToMeAsAdmin_Escalation_id_filter", Escalation_Id);
		fnVerify_ID_Exist_inAlert_Table("EscalationsAssignedToMeAsAdmin_table_1st_cell","EscalationsAssignedToMeAsAdmin_text_link", Escalation_Id);
		
		//Verify Alert (Escalations Assigned to Me. Awaiting Client action)
		fnVerify_ADMIN_Alert_redirect_from_summarydetailsPage("EscalationsAssignedtoMeAwaitingClientAction_text_link","EscalationsAssignedtoMeAwaitingClientAction_Escalation_id_filter", Escalation_Id);
		fnVerify_ID_Exist_inAlert_Table("EscalationsAssignedtoMeAwaitingClientAction_table_1st_cell","EscalationsAssignedtoMeAwaitingClientAction_text_link", Escalation_Id);
		
	}


	
	
	
	
	
	
	
	

	@Test(dependsOnMethods={"Escalation_EditEscalation_Change_status_InitialRecord_to_AdditionalInfoRequired_andThen_Alerts_Verify"}, priority = 7, description="  [Change the status from AdditionalInfoRequired_to_InitialRecord .mention new status response>Click save.]")
	public void Escalation_EditEscalation_Change_status_AdditionalInfoRequired_to_InitialRecord_andThen_Alerts_Verify() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::7 Escalation_EditEscalation_Change_status_AdditionalInfoRequired_to_InitialRecord_andThen_Alerts_Verify ");
	
		//Search Escalation and also navigate to ViewEscalation EscalationDetails page
		fncEscalation_SearchEscalation_and_Navigate_till_ViewEscalationDetailsPageComes(Escalation_Id);
		
		fncEscalation_VerifyChange_Status_on_SummaryDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_AFP", 2, 10));
		

		fnGet_Element_Enabled("validation_error_msg");
		fncVerify_DataUpdated_Successfully("validation_error_msg", "Summary TAB ");	
		
		
		//Verify Alert (Escalations Assigned to Me as Admin)
		fnVerify_ADMIN_Alert_redirect_from_summarydetailsPage("EscalationsAssignedToMeAsAdmin_text_link","EscalationsAssignedToMeAsAdmin_Escalation_id_filter", Escalation_Id);
		fnVerify_ID_Exist_inAlert_Table("EscalationsAssignedToMeAsAdmin_table_1st_cell","EscalationsAssignedToMeAsAdmin_text_link", Escalation_Id);
		
		//Verify Alert Removed (Escalations Assigned to Me. Awaiting Client action)
		fnVerify_ADMIN_Alert_redirect_from_summarydetailsPage("EscalationsAssignedtoMeAwaitingClientAction_text_link","EscalationsAssignedtoMeAwaitingClientAction_Escalation_id_filter", Escalation_Id);
		fnVerify_NoRecordsFound_Msg_for_ID_When_AlertRemoved("EscalationsAssignedtoMeAwaitingClientAction_table_1st_cell","EscalationsAssignedtoMeAwaitingClientAction_text_link", Escalation_Id);
				
		
		//Verify Alert  (Escalation assigned to me, awaiting Admin action)
		fnVerify_ADMIN_Alert_redirect_from_summarydetailsPage("EscalationsAssignedToMeAwaitingAdminAction_text_link","EscalationsAssignedToMeAwaitingAdminAction_Escalation_id_filter", Escalation_Id);
		fnVerify_ID_Exist_inAlert_Table("EscalationsAssignedToMeAwaitingAdminAction_table_1st_cell","EscalationsAssignedToMeAwaitingAdminAction_text_link", Escalation_Id);
		
		
	}


	
	
	
	
	@Test(dependsOnMethods={"Escalation_EditEscalation_Change_status_AdditionalInfoRequired_to_InitialRecord_andThen_Alerts_Verify"}, priority = 8, description="  [Change the status from InitialRecord_to_SatisfactoryClosure. Mention new status response>Click save. Check -Assign to technologist]")
	public void Escalation_EditEscalation_Change_status_InitialRecord_to_SatisfactoryClosure_andThen_Alerts_Verify() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("####### Test Case ::::8 Escalation_EditEscalation_Change_status_InitialRecord_to_SatisfactoryClosure_andThen_Alerts_Verify");

		//Search Escalation and also navigate to ViewEscalation EscalationDetails page
		fncEscalation_SearchEscalation_and_Navigate_till_ViewEscalationDetailsPageComes(Escalation_Id);
		
		fncEscalation_VerifyChange_Status_on_SummaryDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_AFP", 2, 12));
		

		fnGet_Element_Enabled("validation_error_msg");
		fncVerify_DataUpdated_Successfully("validation_error_msg", "Summary TAB ");	
		
		
		//Verify Alert Removed (Escalations Assigned to Me as Admin) after Escalation ID closed
		fnVerify_ADMIN_Alert_redirect_from_summarydetailsPage("EscalationsAssignedToMeAsAdmin_text_link","EscalationsAssignedToMeAsAdmin_Escalation_id_filter", Escalation_Id);
		fnVerify_NoRecordsFound_Msg_for_ID_When_AlertRemoved("EscalationsAssignedToMeAsAdmin_table_1st_cell","EscalationsAssignedToMeAsAdmin_text_link", Escalation_Id);
		
		//Verify Alert Removed (Escalations Assigned to Me. Awaiting Admin action ) after Escalation ID closed
		fnVerify_ADMIN_Alert_redirect_from_summarydetailsPage("EscalationsAssignedToMeAwaitingAdminAction_text_link","EscalationsAssignedToMeAwaitingAdminAction_Escalation_id_filter", Escalation_Id);
		fnVerify_NoRecordsFound_Msg_for_ID_When_AlertRemoved("EscalationsAssignedToMeAwaitingAdminAction_table_1st_cell","EscalationsAssignedToMeAwaitingAdminAction_text_link", Escalation_Id);
		

		//Verify Alert Removed (Escalations Assigned to Me. Awaiting Client action)  after Escalation ID closed
		fnVerify_ADMIN_Alert_redirect_from_summarydetailsPage("EscalationsAssignedtoMeAwaitingClientAction_text_link","EscalationsAssignedtoMeAwaitingClientAction_Escalation_id_filter", Escalation_Id);
		fnVerify_NoRecordsFound_Msg_for_ID_When_AlertRemoved("EscalationsAssignedtoMeAwaitingClientAction_Escalation_id_filter","EscalationsAssignedtoMeAwaitingClientAction_text_link", Escalation_Id);
		
		//Verify Alert Removed (AFP Escalations Unassigned)  after Escalation ID closed
		fnVerify_ADMIN_Alert_redirect_from_summarydetailsPage("AFP_EscalationsUnassigned_text_link","AFP_EscalationsUnassigned_issue_id_filter", Escalation_Id);
		fnVerify_NoRecordsFound_Msg_for_ID_When_AlertRemoved("AFP_EscalationsUnassigned_table_1st_cell","AFP_EscalationsUnassigned_text_link", Escalation_Id);	
		
	}
	

	
	
	
	
	
	
	
	
	
	

	@Test(dependsOnMethods={"Escalation_EditEscalation_Change_status_InitialRecord_to_SatisfactoryClosure_andThen_Alerts_Verify"}, priority = 9, description="  [Verify that all issues are closed which are related to Escalation as the escalation is closed.]")
	public void Escalation_Verify_IssueClosed_As_EscalationClosed() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::9 Escalation_Verify_IssueClosed_As_EscalationClosed ");
		
		

		fnSearchIssue_Ajax_Link_Click();
		fnGet_Element_Enabled("footer");
		fnsLoading_Progressingwait(1);
	
		fncEscalation_Verify_First_IssueClosed_after_EscalationClosed("SearchIssue_Parent_Status",IssueId, "CLOSED" , false);
		
		
	
		fnApps_Report_Logs("######################################### [BS - 13]  CreateEscalation_AFP Script END ################################################## ");
		
		
	}
	
	
	
	
	
	
	
	
	

	@AfterTest
	public void reportTestResult() throws Throwable {
		fns_ReportTestResult_atClassLevel(IssueMgt_Suitexls,  (this.getClass().getSimpleName()) );
	}
	
	
	

	@AfterTest
	public void Test_success() throws Throwable{
		
		driver.quit();
		//WindowsUtils.tryToKillByName("IEDriverServer.exe");
		
		//Thread.sleep(5000);
		
	}
	
	
}	
	
	
	
	
	
	
	
