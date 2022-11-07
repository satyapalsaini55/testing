package nsf.ecap.IssueMgt_Suite;

import java.util.HashMap;
import java.util.Properties;


//
import nsf.ecap.util.*;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.util.HSSFColor.GREEN;
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

public class CreateEscalation_DIS extends TestSuiteBase_IM{
	
	public static String issue_id_green="I0060517";
	public static String FirstIssueId_Green="I0060470";
	public static String SecondIssueId_Amber="I0060472";
	public static String ThirdIssueId_Amber="I0060473";
	public static String ForthIssueId_Amber="I0060475";
	public static String FifthIssueId_Red="I0060477";
	
	public static String Escalation_Id="E0005780";
	public static String NEW_Escalation_Id="E0005780";
	public static String NEW_Escalation_Id_SubType;
	
	public static String validation_error_msg_text;
	public static String TitleShortDescriptionText="CreateEsc_DIS[BS-15], D/T=";	
	
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
			throw new SkipException(errorMsg + " \n\nSo,Skipping Test Case" + className);// reports
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
	
	
	
	
	
	
	
	@BeforeClass (description="OracleQueryLaunch_ToClosed_OpenIssues_and_InActiveAlerts_Whose --- productCode(501214), Issue_Type(PRQ) and Client(NSF-CMiRetailTestingClient")
	public void OracleQueryLaunch_ToClosed_OpenIssues_and_InActiveAlerts() throws Throwable {

		//Function Called to close all issues in database
		fnUpdateDB_to_Close_issues();
		
	}
	
	
	
	

@Test( priority = 0)
public void Launch_Browser_and_Successfully_Login_into_the_Application() throws Throwable{
	fnApps_Report_Logs("################################## [BS-15]  Create Escalation DIS");
	if (!IsBrowserPresentAlready) {
		IsBrowserPresentAlready = fnLaunchBrowserAndLogin();
		fnApps_Report_Logs("Browser is launched and ******** Successfully login into 'I-Pulse' Application.");
	}
}	
	
	
	
	
	@Test(dependsOnMethods={"Launch_Browser_and_Successfully_Login_into_the_Application"}, priority = 1, description="  [Create Issue ID with Green Color]")
	public void Escalation_DIS_CreateIssue_Green() throws Throwable{
		
			
		fnApps_Report_Logs("################### Test Case ::::::1 Escalation_DIS_CreateIssue_Green ");
		
		
		  
	//	IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//src//nsf//ecap//xls//IssueMgt_Suite.xlsx");
			
		Create_DIS_Issue_Without_IssueID_Verification(IssueMgt_Suitexls.getCellData("Escalation_DIS", 2, 3), IssueMgt_Suitexls.getCellData("Escalation_DIS", 2, 4), TitleShortDescriptionText );
		
		//Verify issue ID Generated and Color is as per Expected and Stored into String
		issue_id_green=fnVerified_IssueID_Generated_and_Color_Verification(1, "GREEN");
}
	
	
	
	
	

	
	@Test(dependsOnMethods={"Escalation_DIS_CreateIssue_Green"}, priority = 2, description="  [Go to Summary tab > Click 'Assign to me'. Click Save.]")
	public void Escalation_EditIssue_IssueSummaryDetailsUpdate_after_AssignToMeButtonClick_and_Alerts_VERIFY() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::2 Escalation_EditIssue_IssueSummaryDetailsUpdate_after_AssignToMeButtonClick_and_Alerts_VERIFY ");
		
		//Search IssueID and also navigate to ViewIssue Summary Details page 
		fncIssueID_SearchIssueID_and_Navigate_to_ViewIssueSummaryDetailsPageComes(issue_id_green, true);
		fncIssue_DetailsTAB_RadioButtonsSelect_and_Verify_SaveSuccessfully();
		//Clicking on Summary TAB on View issue page
		fnGet_Element_Enabled("ViewIssueSummaryDetails_summary_detail_tab");
		fnWait_and_Click("ViewIssueSummaryDetails_summary_detail_tab");
		
		// Clicking on Assign to me button
		fnGet_Element_Enabled("ViewIssueSummaryDetails_assign_to_me");
		fnWait_and_Click("ViewIssueSummaryDetails_assign_to_me");
		fnsLoading_Progressingwait(3);
		
		// Clicking on save button
		fnGet_Element_Enabled("ViewIssueSummaryDetails_summary_details_save_button");
		fnWait_and_Click("ViewIssueSummaryDetails_summary_details_save_button");
		fnsLoading_Progressingwait(3);
		
			
		fnGet_Element_Enabled("validation_error_msg");
		fncVerify_DataUpdated_Successfully("validation_error_msg", "Summary TAB : AssignToMe");	
		
		//Verify Alert (Issues Assigned To Me As Admin)
		fnVerify_ADMIN_Alert_redirect_from_summarydetailsPage("IssuesAssignedToMeAsAdmin_text_link","IssuesAssignedToMeAsAdmin_issue_id_filter", issue_id_green);
		fnVerify_ID_Exist_inAlert_Table("IssuesAssignedToMeAsAdmin_table_1st_cell","IssuesAssignedToMeAsAdmin_text_link", issue_id_green);
		
		//Verify Alert (Issues Assigned to Me. Awaiting Admin action)
		fnVerify_ADMIN_Alert_redirect_from_summarydetailsPage("IssuesAssignedtoMeAwaitingAdminaction_text_link","IssuesAssignedtoMeAwaitingAdminaction_issue_id_filter", issue_id_green);
		fnVerify_ID_Exist_inAlert_Table("IssuesAssignedtoMeAwaitingAdminaction_table_1st_cell","IssuesAssignedtoMeAwaitingAdminaction_text_link", issue_id_green);	
	}
		
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Test(dependsOnMethods={"Escalation_EditIssue_IssueSummaryDetailsUpdate_after_AssignToMeButtonClick_and_Alerts_VERIFY"}, priority= 3, description="  [Change the status from INITIAL RECORD > Additional info Required  >mention new status response>Click save.]")
	public void Escalation_EditIssue_Change_status_InitialRecord_to_AdditionalinfoRequired_andThen_Alerts_Verify() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::3 Escalation_EditIssue_Change_status_InitialRecord_to_AdditionalinfoRequired_andThen_Alerts_Verify ");
		
		
		fnIssue_VerifyChange_Status_on_SummaryDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_DIS", 2, 28), issue_id_green);
		
		//Verify Alert --- --Issues Assigned to Me as Admin		
		fnVerify_ADMIN_Alert_redirect_from_summarydetailsPage("IssuesAssignedToMeAsAdmin_text_link","IssuesAssignedToMeAsAdmin_issue_id_filter", issue_id_green);
		fnVerify_ID_Exist_inAlert_Table("IssuesAssignedToMeAsAdmin_table_1st_cell","IssuesAssignedToMeAsAdmin_text_link", issue_id_green);
		
		//Verify Alert --- Issues Assigned To Me Awaiting Client Action
		fnVerify_ADMIN_Alert_redirect_from_summarydetailsPage("IssuesAssignedtoMeAwaitingClientaction_text_link","IssuesAssignedtoMeAwaitingClientaction_issue_id_filter", issue_id_green);
		fnVerify_ID_Exist_inAlert_Table("IssuesAssignedtoMeAwaitingClientaction_table_1st_cell","IssuesAssignedtoMeAwaitingClientaction_text_link", issue_id_green);
		
		
		//Alerts Removed Verification (Removed Alert --- Issues assigned to me, as awaiting admin action)
		fnVerify_ADMIN_Alert_redirect_from_summarydetailsPage("IssuesAssignedtoMeAwaitingAdminaction_text_link","IssuesAssignedtoMeAwaitingAdminaction_issue_id_filter", issue_id_green);
		fnVerify_NoRecordsFound_Msg_for_ID_When_AlertRemoved("IssuesAssignedtoMeAwaitingAdminaction_table_1st_cell","IssuesAssignedtoMeAwaitingAdminaction_text_link", issue_id_green);
				
}			
			
	
	
	
	
	
	
	
	
	
	
	
	@Test(dependsOnMethods={"Escalation_EditIssue_Change_status_InitialRecord_to_AdditionalinfoRequired_andThen_Alerts_Verify"}, priority= 4, description="  [Link the technologist name here -select radio button as NSF  and mention the Technologist name. Click save.]")
	public void Escalation_EditIssue_SelectRadioButtonAsNSF_MentionTechnologistName_andThen_Alerts_Verify() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::4 Escalation_EditIssue_SelectRadioButtonAsNSF_MentionTechnologistName_andThen_Alerts_Verify ");
		
		//Search IssueID and also navigate to ViewIssue Summary Details page 
		fncIssueID_SearchIssueID_and_Navigate_to_ViewIssueSummaryDetailsPageComes(issue_id_green, true);
		
		//Clicking on Summary TAB on View issue page
		fnGet_Element_Enabled("ViewIssueSummaryDetails_summary_detail_tab");
		fnWait_and_Click("ViewIssueSummaryDetails_summary_detail_tab");
	
		/*// Select Technologist Type by clicking on radio button
		/fnGet_Element_Enabled("ViewIssueSummaryDetails_technologist_type_radio_bttn");
		Thread.sleep(1500);
		fnWait_and_Click("ViewIssueSummaryDetails_technologist_type_radio_bttn");
		Thread.sleep(4000);
		Thread.sleep(4000); // Due to DD Loading an Additional time has been added 10.1.2016a
		
		//Select value from Technologist Name drop down
		/fnDD_value_Select("ViewIssueSummaryDetails_technologist_name_dd_click", "ViewIssueSummaryDetails_technologist_name_dd_value", "li", IssueMgt_Suitexls.getCellData("Escalation_DIS", 2, 27));
		*/
		
		//Added on 29.3.2017 as Kelvin Owen is not comin into the DD and above code commented.
		fnc_ClickOn_TechnologistTypeRadioBttn_AndThen_Select_TechnologistNameFromDD("ViewIssueSummaryDetails_technologist_type_radio_bttn", "ViewIssueSummaryDetails_technologist_name_dd_click", "ViewIssueSummaryDetails_technologist_name_dd_value");
				
		
		
		// Clicking on save button
		fnGet_Element_Enabled("ViewIssueSummaryDetails_summary_details_save_button");
		fnWait_and_Click("ViewIssueSummaryDetails_summary_details_save_button");
		fnsLoading_Progressingwait(3);
		
		fnGet_Element_Enabled("validation_error_msg");
		Thread.sleep(1000);
		fnGet_Element_Enabled("validation_error_msg");
		fncVerify_DataUpdated_Successfully("validation_error_msg", "Summary TAB : Status change to 'Select RadioButtonAsNSF and TechnologistName'");
		
		//Verify Alert --- --Issues Assigned to Me as Admin		
		fnVerify_ADMIN_Alert_redirect_from_summarydetailsPage("IssuesAssignedToMeAsAdmin_text_link","IssuesAssignedToMeAsAdmin_issue_id_filter", issue_id_green);
		fnVerify_ID_Exist_inAlert_Table("IssuesAssignedToMeAsAdmin_table_1st_cell","IssuesAssignedToMeAsAdmin_text_link", issue_id_green);
		
		//Verify Alert --- Issues Assigned To Me Awaiting Client Action
		fnVerify_ADMIN_Alert_redirect_from_summarydetailsPage("IssuesAssignedtoMeAwaitingClientaction_text_link","IssuesAssignedtoMeAwaitingClientaction_issue_id_filter", issue_id_green);
		fnVerify_ID_Exist_inAlert_Table("IssuesAssignedtoMeAwaitingClientaction_table_1st_cell","IssuesAssignedtoMeAwaitingClientaction_text_link", issue_id_green);
		
		//Verify Alert --- Issues Assigned to Me as Technologist
		fnVerify_TECH_Alert_redirect_from_AlertPage_by_SpecificUserRadioBttn("IssuesAssignedtoMeasTechnologist_text_link","IssuesAssignedtoMeasTechnologist_issue_id_filter", issue_id_green);
		fnVerify_ID_Exist_inAlert_Table("IssuesAssignedtoMeasTechnologist_table_1st_cell","IssuesAssignedtoMeasTechnologist_text_link", issue_id_green);
	}


	
	
	
	
	
	
	
	
	
	@Test(dependsOnMethods={"Escalation_EditIssue_SelectRadioButtonAsNSF_MentionTechnologistName_andThen_Alerts_Verify"}, priority= 5, description="  [Change the status from AdditionalinfoRequired  >  INITIAL RECORD . mention new status response>Click save.]")
	public void Escalation_EditIssue_Change_status_AdditionalinfoRequired_to_InitialRecord_andThen_Alerts_Verify() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::5 Escalation_EditIssue_Change_status_AdditionalinfoRequired_to_InitialRecord_andThen_Alerts_Verify ");
		
		fnIssue_VerifyChange_Status_on_SummaryDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_DIS", 2, 29), issue_id_green);
		
		//Verify Alert --- Issues Assigned to Me as Admin		
		fnVerify_ADMIN_Alert_redirect_from_summarydetailsPage("IssuesAssignedToMeAsAdmin_text_link","IssuesAssignedToMeAsAdmin_issue_id_filter", issue_id_green);
		fnVerify_ID_Exist_inAlert_Table("IssuesAssignedToMeAsAdmin_table_1st_cell","IssuesAssignedToMeAsAdmin_text_link", issue_id_green);
		
		//Verify Alert (Issues Assigned to Me. Awaiting Admin action)
		fnVerify_ADMIN_Alert_redirect_from_summarydetailsPage("IssuesAssignedtoMeAwaitingAdminaction_text_link","IssuesAssignedtoMeAwaitingAdminaction_issue_id_filter", issue_id_green);
		fnVerify_ID_Exist_inAlert_Table("IssuesAssignedtoMeAwaitingAdminaction_table_1st_cell","IssuesAssignedtoMeAwaitingAdminaction_text_link", issue_id_green);	
				
		//Verify Alert --- Issues Assigned to Me as Technologist
		fnVerify_TECH_Alert_redirect_from_AlertPage_by_SpecificUserRadioBttn("IssuesAssignedtoMeasTechnologist_text_link","IssuesAssignedtoMeasTechnologist_issue_id_filter", issue_id_green);
		fnVerify_ID_Exist_inAlert_Table("IssuesAssignedtoMeasTechnologist_table_1st_cell","IssuesAssignedtoMeasTechnologist_text_link", issue_id_green);
		
		//Alerts Removed Verification (Removed Alert --- Issues assigned to me, as awaiting client action.)
		fnVerify_TECH_Alert_by_clicking_ShowAlertBttn_from_TECHAlertPage("IssuesAssignedtoMeAwaitingClientaction_text_link","IssuesAssignedtoMeAwaitingClientaction_issue_id_filter", issue_id_green);
		fnVerify_NoRecordsFound_Msg_for_ID_When_AlertRemoved("IssuesAssignedtoMeAwaitingClientaction_table_1st_cell","IssuesAssignedtoMeAwaitingClientaction_text_link", issue_id_green);
}	
			
	
	
	
	
	
	
	
	
	
	
	@Test(dependsOnMethods={"Escalation_EditIssue_Change_status_AdditionalinfoRequired_to_InitialRecord_andThen_Alerts_Verify"}, priority= 6, description="  [Change the status from INITIAL RECORD > Details Sent to Distributor .mention new status response>Click save. Check - assign to Technologist]")
	public void Escalation_EditIssue_Change_status_InitialRecord_to_DetailsSentToDistributor_SelectAssigntoTechnologistCheckBox_and_Alerts_Verify() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("##### Test Case :::6 Escalation_EditIssue_Change_status_InitialRecord_to_DetailSentToDistributor_SelectAssigntoTechnologist_AlertVerify ");
		
		//Search IssueID and also navigate to ViewIssue Summary Details page 
		fncIssueID_SearchIssueID_and_Navigate_to_ViewIssueSummaryDetailsPageComes(issue_id_green, true);
		
		//Clicking on Summary TAB on View issue page
		fnGet_Element_Enabled("ViewIssueSummaryDetails_summary_detail_tab");
		fnWait_and_Click("ViewIssueSummaryDetails_summary_detail_tab");
		
		Thread.sleep(1000);
		
		
		// Select Technologist Type by clicking on radio button
		fnGet_Element_Enabled("ViewIssueSummaryDetails_assign_to_technologist");
		fnWait_and_Click("ViewIssueSummaryDetails_assign_to_technologist");
		Thread.sleep(2000);
					
		//Change value from NEW STATUS drop down
		fnDD_value_Select("ViewIssueSummaryDetails_new_status_dd_click", "ViewIssueSummaryDetails_new_status_dd_value", "li", IssueMgt_Suitexls.getCellData("Escalation_DIS", 2, 30));
		
		//Waiting for Astric(*) Image to display
		fnGet_Element_Enabled("ViewIssueSummaryDetails_new_status_response_Astric_Image");	
		
		// Enter free text value into New Status Response field		
		fnGet_Element_Enabled("ViewIssueSummaryDetails_new_status_response_textfield");	
		fnWait_and_Type("ViewIssueSummaryDetails_new_status_response_textfield", "Edit Issue, Changing Status from (Initial Record) to (Details Sent To Supplier).");
		
		
		
		// Clicking on save button
		fnGet_Element_Enabled("ViewIssueSummaryDetails_summary_details_save_button");
		fnWait_and_Click("ViewIssueSummaryDetails_summary_details_save_button");
		fnsLoading_Progressingwait(3);
		
		fnGet_Element_Enabled("validation_error_msg");
		Thread.sleep(1000);
		fnGet_Element_Enabled("validation_error_msg");
		fncVerify_DataUpdated_Successfully("validation_error_msg", "Summary TAB ");
		
		
			
		//Verify Alert --- Issues Assigned to Me as Admin		
		fnVerify_ADMIN_Alert_redirect_from_summarydetailsPage("IssuesAssignedToMeAsAdmin_text_link","IssuesAssignedToMeAsAdmin_issue_id_filter", issue_id_green);
		fnVerify_ID_Exist_inAlert_Table("IssuesAssignedToMeAsAdmin_table_1st_cell","IssuesAssignedToMeAsAdmin_text_link", issue_id_green);
		
		//Verify Alert --- Issues Assigned to Me as Technologist
		fnVerify_TECH_Alert_redirect_from_AlertPage_by_SpecificUserRadioBttn("IssuesAssignedtoMeasTechnologist_text_link","IssuesAssignedtoMeasTechnologist_issue_id_filter", issue_id_green);
		fnVerify_ID_Exist_inAlert_Table("IssuesAssignedtoMeasTechnologist_table_1st_cell","IssuesAssignedtoMeasTechnologist_text_link", issue_id_green);
	
		//Verify DetailsSentToSupplier Alert ---  Issues Assigned to Me. Awaiting Supplier/Distributor action
		fnVerify_TECH_Alert_by_clicking_ShowAlertBttn_from_TECHAlertPage("IssuesAssignedToMeAwaitingSupplierDistributorAction_text_link", "IssuesAssignedToMeAwaitingSupplierDistributorAction_issue_id_filter", issue_id_green);
		fnVerify_ID_Exist_inAlert_Table("IssuesAssignedToMeAwaitingSupplierDistributorAction_table_1st_cell","IssuesAssignedToMeAwaitingSupplierDistributorAction_text_link", issue_id_green);
		
		//Alerts Removed Verification (Removed Alert --- Issues assigned to me, awaiting technologist action)
		fnVerify_TECH_Alert_by_clicking_ShowAlertBttn_from_TECHAlertPage("IssuesAssignedtoMeAwaitingTechnologistaction_text_link","IssuesAssignedtoMeAwaitingTechnologistaction_issue_id_filter", issue_id_green);
		fnVerify_NoRecordsFound_Msg_for_ID_When_AlertRemoved("IssuesAssignedtoMeAwaitingTechnologistaction_table_1st_cell","IssuesAssignedtoMeAwaitingTechnologistaction_text_link", issue_id_green);
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	@Test(dependsOnMethods={"Escalation_EditIssue_Change_status_InitialRecord_to_DetailsSentToDistributor_SelectAssigntoTechnologistCheckBox_and_Alerts_Verify"}, priority= 7, description="  [Change the status from  DETAILS SENT TO SUPPLIER >ISSUE RESPONSE .mention new status response>Click save.]")
	public void Escalation_EditIssue_Change_status_DetailsSentToDistributor_to_IssueResponse_andThen_Alerts_Verify() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::7 Escalation_EditIssue_Change_status_DetailsSentToDistributor_to_IssueResponse_andThen_Alerts_Verify ");
		
		
			fnIssue_VerifyChange_Status_on_SummaryDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_DIS", 2, 31), issue_id_green);
			
			//Verify Alert --- Issues Assigned to Me as Admin		
			fnVerify_ADMIN_Alert_redirect_from_summarydetailsPage("IssuesAssignedToMeAsAdmin_text_link","IssuesAssignedToMeAsAdmin_issue_id_filter", issue_id_green);
			fnVerify_ID_Exist_inAlert_Table("IssuesAssignedToMeAsAdmin_table_1st_cell","IssuesAssignedToMeAsAdmin_text_link", issue_id_green);
			
			//Verify Alert --- Issues Assigned to Me as Technologist
			fnVerify_TECH_Alert_redirect_from_AlertPage_by_SpecificUserRadioBttn("IssuesAssignedtoMeasTechnologist_text_link","IssuesAssignedtoMeasTechnologist_issue_id_filter", issue_id_green);
			fnVerify_ID_Exist_inAlert_Table("IssuesAssignedtoMeasTechnologist_table_1st_cell","IssuesAssignedtoMeasTechnologist_text_link", issue_id_green);
		
			//Verify Alert --- Issues Assigned to Me. Awaiting Technologist action
			fnVerify_TECH_Alert_by_clicking_ShowAlertBttn_from_TECHAlertPage("IssuesAssignedtoMeAwaitingTechnologistaction_text_link","IssuesAssignedtoMeAwaitingTechnologistaction_issue_id_filter", issue_id_green);
			fnVerify_ID_Exist_inAlert_Table("IssuesAssignedtoMeAwaitingTechnologistaction_table_1st_cell","IssuesAssignedtoMeAwaitingTechnologistaction_text_link", issue_id_green);
			
			//Alerts Removed Verification (Removed Alert --- Issues assigned to me, awaiting supplier / distributor action.)
			fnVerify_TECH_Alert_by_clicking_ShowAlertBttn_from_TECHAlertPage("IssuesAssignedToMeAwaitingSupplierDistributorAction_text_link","IssuesAssignedToMeAwaitingSupplierDistributorAction_issue_id_filter", issue_id_green);
			fnVerify_NoRecordsFound_Msg_for_ID_When_AlertRemoved("IssuesAssignedToMeAwaitingSupplierDistributorAction_table_1st_cell","IssuesAssignedToMeAwaitingSupplierDistributorAction_text_link", issue_id_green);
}
	

	
	
	
	
	
	
	
	
	
	@Test(dependsOnMethods={"Escalation_EditIssue_Change_status_DetailsSentToDistributor_to_IssueResponse_andThen_Alerts_Verify"}, priority= 8, description="  [Change the status from  ISSUE RESPONSE >TECHNOLOGIST approved to close. Mention new status response>Click save.]")
	public void Escalation_EditIssue_Change_status_IssueResponse_to_TechnologistApprovedToClose_andThen_Alerts_Verify() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::8 Escalation_EditIssue_Change_status_IssueResponse_to_TechnologistApprovedToClose_andThen_Alerts_Verify ");
		
		
			fnIssue_VerifyChange_Status_on_SummaryDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_DIS", 2, 32), issue_id_green);
			
			//Verify Assign to Technologist checkbox is unchecked.
			fnGet_Element_Enabled("ViewIssueSummaryDetails_assign_to_technologist");
			
			try{
				assert !(fnGet_OR_IM_ObjectX("ViewIssueSummaryDetails_assign_to_technologist").isSelected()):"AssignToTechnologist checkbox is selected after changing status to Technologist Approved to Close, please refer screenshot >> CheckBoxIsSelected";
				fnApps_Report_Logs("PASSED == 'Assign To Technologist' checkBox is getting unchecked after changing status to TechnologistApprovedToClose.");		
			}catch(Throwable t){
				isTestCasePass = false;
				fnTake_Screen_Shot("CheckBoxIsSelected");
				fnApps_Report_Logs(Throwables.getStackTraceAsString(t));
				throw new Exception(Throwables.getStackTraceAsString(t));
			}
			
			//Verify Alert --- Issues Assigned to Me as Admin		
			fnVerify_ADMIN_Alert_redirect_from_summarydetailsPage("IssuesAssignedToMeAsAdmin_text_link","IssuesAssignedToMeAsAdmin_issue_id_filter", issue_id_green);
			fnVerify_ID_Exist_inAlert_Table("IssuesAssignedToMeAsAdmin_table_1st_cell","IssuesAssignedToMeAsAdmin_text_link", issue_id_green);
			
			//Verify Alert (Issues Assigned to Me. Awaiting Admin action)
			fnVerify_ADMIN_Alert_redirect_from_summarydetailsPage("IssuesAssignedtoMeAwaitingAdminaction_text_link","IssuesAssignedtoMeAwaitingAdminaction_issue_id_filter", issue_id_green);
			fnVerify_ID_Exist_inAlert_Table("IssuesAssignedtoMeAwaitingAdminaction_table_1st_cell","IssuesAssignedtoMeAwaitingAdminaction_text_link", issue_id_green);	
			
			//Verify Alert --- Issues Assigned to Me as Technologist
			fnVerify_TECH_Alert_redirect_from_AlertPage_by_SpecificUserRadioBttn("IssuesAssignedtoMeasTechnologist_text_link","IssuesAssignedtoMeasTechnologist_issue_id_filter", issue_id_green);
			fnVerify_ID_Exist_inAlert_Table("IssuesAssignedtoMeasTechnologist_table_1st_cell","IssuesAssignedtoMeasTechnologist_text_link", issue_id_green);
			
			//Alerts Removed Verification (Removed Alert --- Issues assigned to me, awaiting technologist action)
			fnVerify_TECH_Alert_by_clicking_ShowAlertBttn_from_TECHAlertPage("IssuesAssignedtoMeAwaitingTechnologistaction_text_link","IssuesAssignedtoMeAwaitingTechnologistaction_issue_id_filter", issue_id_green);
			fnVerify_NoRecordsFound_Msg_for_ID_When_AlertRemoved("IssuesAssignedtoMeAwaitingTechnologistaction_table_1st_cell","IssuesAssignedtoMeAwaitingTechnologistaction_text_link", issue_id_green);	
}
	
	
	
	
	
	
	
	
	
	
	@Test(dependsOnMethods={"Escalation_EditIssue_Change_status_IssueResponse_to_TechnologistApprovedToClose_andThen_Alerts_Verify"}, priority= 9, description="  [Change the status from >TECHNOLOGIST approved to close- > SATISFACTORY CLOSURE. Mention new status response>Click save.]")
	public void Escalation_EditIssue_Change_status_TechnologistApprovedToClose_to_SatisfactoryClosure_andThen_Alerts_Verify() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("########### Test Case :::9 Escalation_EditIssue_Change_status_TechnologistApprovedToClose_to_SatisfactoryClosure_andThen_Alerts_Verify ");
		
		
			fnIssue_VerifyChange_Status_on_SummaryDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_DIS", 2, 33), issue_id_green);
			
			//Verify Alert (Issues Assigned to Me as Admin) after Issue ID Closed
			fnVerify_ADMIN_Alert_redirect_from_summarydetailsPage("IssuesAssignedToMeAsAdmin_text_link","IssuesAssignedToMeAsAdmin_issue_id_filter", issue_id_green);
			fnVerify_NoRecordsFound_Msg_for_ID_When_AlertRemoved("IssuesAssignedToMeAsAdmin_table_1st_cell","IssuesAssignedToMeAsAdmin_text_link", issue_id_green);
						
			//Verify Alert (Issues Assigned to Me. Awaiting Admin action) after Issue ID Closed
			fnVerify_ADMIN_Alert_redirect_from_summarydetailsPage("IssuesAssignedtoMeAwaitingAdminaction_text_link","IssuesAssignedtoMeAwaitingAdminaction_issue_id_filter", issue_id_green);
			fnVerify_NoRecordsFound_Msg_for_ID_When_AlertRemoved("IssuesAssignedtoMeAwaitingAdminaction_table_1st_cell","IssuesAssignedtoMeAwaitingAdminaction_text_link", issue_id_green);
			
			//Verify Alert (Issues Assigned to Me as Technologist) after Issue ID Closed
			fnVerify_TECH_Alert_redirect_from_AlertPage_by_SpecificUserRadioBttn("IssuesAssignedtoMeasTechnologist_text_link","IssuesAssignedtoMeasTechnologist_issue_id_filter", issue_id_green);
			fnVerify_NoRecordsFound_Msg_for_ID_When_AlertRemoved("IssuesAssignedtoMeasTechnologist_table_1st_cell","IssuesAssignedtoMeasTechnologist_text_link", issue_id_green);

			//Verify Alert (Issues Assigned to Me. Awaiting Technologist action) after Issue ID Closed
			fnVerify_TECH_Alert_by_clicking_ShowAlertBttn_from_TECHAlertPage("IssuesAssignedtoMeAwaitingTechnologistaction_text_link","IssuesAssignedtoMeAwaitingTechnologistaction_issue_id_filter", issue_id_green);
			fnVerify_NoRecordsFound_Msg_for_ID_When_AlertRemoved("IssuesAssignedtoMeAwaitingTechnologistaction_table_1st_cell","IssuesAssignedtoMeAwaitingTechnologistaction_text_link", issue_id_green);
			
			//Verify Alert (Issues Assigned to Me. Awaiting Supplier/Distributor action ) after Issue ID Closed
			fnVerify_TECH_Alert_by_clicking_ShowAlertBttn_from_TECHAlertPage("IssuesAssignedToMeAwaitingSupplierDistributorAction_text_link","IssuesAssignedToMeAwaitingSupplierDistributorAction_issue_id_filter", issue_id_green);
			fnVerify_NoRecordsFound_Msg_for_ID_When_AlertRemoved("IssuesAssignedToMeAwaitingSupplierDistributorAction_table_1st_cell","IssuesAssignedToMeAwaitingSupplierDistributorAction_text_link", issue_id_green);
			
			//Verify Alert (Issues Assigned to Me. Awaiting Client action) after Issue ID Closed
			fnVerify_TECH_Alert_by_clicking_ShowAlertBttn_from_TECHAlertPage("IssuesAssignedtoMeAwaitingClientaction_text_link","IssuesAssignedtoMeAwaitingClientaction_issue_id_filter", issue_id_green);
			fnVerify_NoRecordsFound_Msg_for_ID_When_AlertRemoved("IssuesAssignedtoMeAwaitingClientaction_table_1st_cell","IssuesAssignedtoMeAwaitingClientaction_text_link", issue_id_green);
		}
	
	
	
	
	
	@Test(dependsOnMethods={"Escalation_EditIssue_Change_status_TechnologistApprovedToClose_to_SatisfactoryClosure_andThen_Alerts_Verify"}, priority= 10, description="  [Change the status from >TECHNOLOGIST approved to close- > SATISFACTORY CLOSURE. Mention new status response>Click save.]")
	public void Escalation_RelaunchIEDriver_and_DB_QueryExecute_to_continue_TestCase() throws Throwable{
	
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("########### Test Case :::10 Escalation_RelaunchIEDriver_and_DB_QueryExecute_to_continue_TestCase ");
			
		fnIEBrowser_Relaunched();
		Thread.sleep(5000);
		fnApps_Report_Logs("PASSED  ==  Successfully Relaunched IEDriver to Complete rest of the TestCase ");
		
		fnUpdateDB_to_Close_issues();
}
	
	

	

	
	
	@Test(dependsOnMethods={"Escalation_RelaunchIEDriver_and_DB_QueryExecute_to_continue_TestCase"}, priority = 11, description="  [Create Issue ID with Green Color]")
	public void Escalation_DIS_FirstIssue_Green() throws Throwable{
		
		fnApps_Report_Logs("=========================================================================================================================================");	
		fnApps_Report_Logs("################### Test Case ::::::11 Escalation_DIS_FirstIssue_Green ");
		
		
		  
	//	IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//src//nsf//ecap//xls//IssueMgt_Suite.xlsx");
			
		Create_DIS_Issue_Without_IssueID_Verification(IssueMgt_Suitexls.getCellData("Escalation_DIS", 2, 3), IssueMgt_Suitexls.getCellData("Escalation_DIS", 2, 4), TitleShortDescriptionText );
		
		//Verify issue ID Generated and Color is as per Expected and Stored into String
		FirstIssueId_Green=fnVerified_IssueID_Generated_and_Color_Verification(1, "GREEN");
}
	
	
	
	

	

	@Test(dependsOnMethods={"Escalation_DIS_FirstIssue_Green"}, priority = 12, description="  [Create Issue ID with Amber Color]")
	public void Escalation_DIS_SecondIssue_Amber() throws Throwable{
		
		fnApps_Report_Logs("=========================================================================================================================================");	
		fnApps_Report_Logs("################### Test Case ::::::12 Escalation_DIS_SecondIssue_Amber ");
		
	//	IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//src//nsf//ecap//xls//IssueMgt_Suite.xlsx");
			
		Create_DIS_Issue_Without_IssueID_Verification(IssueMgt_Suitexls.getCellData("Escalation_DIS", 2, 6), IssueMgt_Suitexls.getCellData("Escalation_DIS", 2, 7), TitleShortDescriptionText );
		
		//Verify issue ID Generated and Color is as per Expected and Stored into String
		SecondIssueId_Amber=fnVerified_IssueID_Generated_and_Color_Verification(2, "AMBER");
}
	
	

	
	
	@Test(dependsOnMethods={"Escalation_DIS_SecondIssue_Amber"}, priority = 13, description="  [Create Issue ID with Amber Color]")
	public void Escalation_DIS_ThirdIssue_Amber() throws Throwable{
		
		fnApps_Report_Logs("=========================================================================================================================================");	
		fnApps_Report_Logs("################### Test Case ::::::13 Escalation_DIS_ThirdIssue_Amber ");
		
	//	IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//src//nsf//ecap//xls//IssueMgt_Suite.xlsx");
			
		Create_DIS_Issue_Without_IssueID_Verification(IssueMgt_Suitexls.getCellData("Escalation_DIS", 2, 9), IssueMgt_Suitexls.getCellData("Escalation_DIS", 2, 10), TitleShortDescriptionText );
		
		//Verify issue ID Generated and Color is as per Expected and Stored into String
		ThirdIssueId_Amber=fnVerified_IssueID_Generated_and_Color_Verification(3, "AMBER");
}
	
	
	

	
	@Test(dependsOnMethods={"Escalation_DIS_ThirdIssue_Amber"}, priority = 14, description="  [Create Issue ID with Amber Color]")
	public void Escalation_DIS_ForthIssue_Amber() throws Throwable{
		
		fnApps_Report_Logs("=========================================================================================================================================");	
		fnApps_Report_Logs("################### Test Case ::::::14 Escalation_DIS_ForthIssue_Amber ");
		
//		IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//src//nsf//ecap//xls//IssueMgt_Suite.xlsx");
			
		Create_DIS_Issue_Without_IssueID_Verification(IssueMgt_Suitexls.getCellData("Escalation_DIS", 2, 12), IssueMgt_Suitexls.getCellData("Escalation_DIS", 2, 13), TitleShortDescriptionText );
		
		//Verify issue ID Generated and Color is as per Expected and Stored into String
		ForthIssueId_Amber=fnVerified_IssueID_Generated_and_Color_Verification(4, "AMBER");
}
	
	

	
	
	
	@Test(dependsOnMethods={"Escalation_DIS_ForthIssue_Amber"}, priority = 15, description="  [Create Issue ID with RED Color]")
	public void Escalation_DIS_FifthIssue_Red() throws Throwable{
		
		fnApps_Report_Logs("=========================================================================================================================================");	
		fnApps_Report_Logs("################### Test Case ::::::15 Escalation_DIS_FifthIssue_Red ");
		
	//	IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//src//nsf//ecap//xls//IssueMgt_Suite.xlsx");
			
		Create_DIS_Issue_Without_IssueID_Verification(IssueMgt_Suitexls.getCellData("Escalation_DIS", 2, 15), IssueMgt_Suitexls.getCellData("Escalation_DIS", 2, 16), TitleShortDescriptionText );
		
		//Verify issue ID Generated and Color is as per Expected and Stored into String
		FifthIssueId_Red=fnVerified_IssueID_Generated_and_Color_Verification(5, "RED");
}
	
	
	
	
	
	
	
@Test(dependsOnMethods={"Escalation_DIS_FifthIssue_Red"}, priority = 16, description="  [Verify Escalations ID Generated with Five issues.]")
public void Escalation_DIS_Generated_With_5_Issues() throws Throwable{
	fnApps_Report_Logs("=========================================================================================================================================");
	fnApps_Report_Logs("################### Test Case ::::::16 Escalation_DIS_Generated_With_5_Issues ");
	

	
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
			assert ((related_issue_table.contains(FirstIssueId_Green)) && related_issue_table.contains(SecondIssueId_Amber) && related_issue_table.contains(ThirdIssueId_Amber) && related_issue_table.contains(ForthIssueId_Amber) && related_issue_table.contains(FifthIssueId_Red)):
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
	fnVerify_ADMIN_Alert_redirect_from_summarydetailsPage("EscalationsUnassigned_text_link","EscalationsUnassigned_issue_id_filter", Escalation_Id);
	
	//verify Escalation ID exists in Alert Table
	fnVerify_ID_Exist_inAlert_Table("EscalationsUnassigned_table_1st_cell","EscalationsUnassigned_text_link", Escalation_Id);	
}


	






	
	
@Test(dependsOnMethods={"Escalation_DIS_Generated_With_5_Issues"}, priority= 17, description="  [Edit the first issue created on 1st june'14 and change its date to fall not within the 28 days.]")
public void Escalation_ChangeFirstIssueDate_to_Make_Escalation_Dismantled_and_Verify_IssuesColor() throws Throwable{

	fnApps_Report_Logs("=========================================================================================================================================");
	fnApps_Report_Logs("############# Test Case ::::17 Escalation_ChangeFirstIssueDate_to_Make_Escalation_Dismantled_and_Verify_IssuesColor ");
	
//	IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//src//nsf//ecap//xls//IssueMgt_Suite.xlsx");
			
	//Search IssueID and also navigate to ViewIssue Summary Details page 
	fncIssueID_SearchIssueID_and_Navigate_to_ViewIssueSummaryDetailsPageComes(FirstIssueId_Green, true);
	
	//Clicking on Summary TAB on View issue page
	fnGet_Element_Enabled("ViewIssueSummaryDetails_summary_detail_tab");
	fnWait_and_Click("ViewIssueSummaryDetails_summary_detail_tab");
	
	//Edit Issue Date to 1 month back
	fnGet_Element_Enabled("ViewIssueSummaryDetails_DateTime_Issue");
	WithOut_OR_fnClear(OR_IM.getProperty("ViewIssueSummaryDetails_DateTime_Issue"));
	fnWait_and_Type("ViewIssueSummaryDetails_DateTime_Issue", IssueMgt_Suitexls.getCellData("Escalation_DIS", 2, 5));
	
	// Clicking on save button
	fnGet_Element_Enabled("ViewIssueSummaryDetails_summary_details_save_button");
	fnWait_and_Click("ViewIssueSummaryDetails_summary_details_save_button");
	fnsLoading_Progressingwait(3);
	
	fnGet_Element_Enabled("validation_error_msg");
	Thread.sleep(1000);
	fnGet_Element_Enabled("validation_error_msg");
	fncVerify_DataUpdated_Successfully("validation_error_msg", "Summary TAB");
	
	

	fnSearchIssue_Ajax_Link_Click();
	fnGet_Element_Enabled("footer");
	fnsLoading_Progressingwait(1);

	fncEscalation_Verify_First_IssueClosed_after_EscalationClosed("SearchIssue_Escalation_Status",FirstIssueId_Green, "GREEN" , false);
	fncEscalation_Verify_First_IssueClosed_after_EscalationClosed("SearchIssue_Escalation_Status",SecondIssueId_Amber, "AMBER" , false);
	fncEscalation_Verify_First_IssueClosed_after_EscalationClosed("SearchIssue_Escalation_Status",ThirdIssueId_Amber, "AMBER" , false);
	fncEscalation_Verify_First_IssueClosed_after_EscalationClosed("SearchIssue_Escalation_Status",ForthIssueId_Amber, "AMBER" , false);
	fncEscalation_Verify_First_IssueClosed_after_EscalationClosed("SearchIssue_Escalation_Status",FifthIssueId_Red, "AMBER" , false);

}			

	
	
	







@Test(dependsOnMethods={"Escalation_ChangeFirstIssueDate_to_Make_Escalation_Dismantled_and_Verify_IssuesColor"}, priority= 18, description="  [Edit the first issue created on 1st june'14 and change its date to the original date ie 1st June'14.]")
public void Escalation_ChangeFirstIssueDate_BackToOriginal_Verify_NEW_Escalation_ID_Generated_and_Verify_IssuesColor() throws Throwable{

	fnApps_Report_Logs("=========================================================================================================================================");
	fnApps_Report_Logs("############# Test Case ::::18 Escalation_ChangeFirstIssueDate_BackToOriginal_Verify_NEW_Escalation_ID_Generated_and_Verify_IssuesColor ");
	
	//Search IssueID and also navigate to ViewIssue Summary Details page 
	fncIssueID_SearchIssueID_and_Navigate_to_ViewIssueSummaryDetailsPageComes(FirstIssueId_Green, true);
	
	//Clicking on Summary TAB on View issue page
	fnGet_Element_Enabled("ViewIssueSummaryDetails_summary_detail_tab");
	fnWait_and_Click("ViewIssueSummaryDetails_summary_detail_tab");
	
	//Edit Issue Date to 1 month back
	fnGet_Element_Enabled("ViewIssueSummaryDetails_DateTime_Issue");
	WithOut_OR_fnClear(OR_IM.getProperty("ViewIssueSummaryDetails_DateTime_Issue"));
	fnWait_and_Type("ViewIssueSummaryDetails_DateTime_Issue", IssueMgt_Suitexls.getCellData("Escalation_DIS", 2, 4));
	
	// Clicking on save button
	fnGet_Element_Enabled("ViewIssueSummaryDetails_summary_details_save_button");
	fnWait_and_Click("ViewIssueSummaryDetails_summary_details_save_button");
	fnsLoading_Progressingwait(3);
	
	fnGet_Element_Enabled("validation_error_msg");
	Thread.sleep(1000);
	fnGet_Element_Enabled("validation_error_msg");
	fncVerify_DataUpdated_Successfully("validation_error_msg", "Summary TAB ");
	
	try{
		
		//Verify NEW Escalation Generated
		fnGet_Element_Enabled("Escalation_id_link");
		Thread.sleep(500);
		NEW_Escalation_Id=fnGet_Field_TEXT("Escalation_id_link").trim();
		try{
			assert (!NEW_Escalation_Id.contains(Escalation_Id)):"FAILED == NEW Escalations ID is not Generated >> "+NEW_Escalation_Id+", Please refer ScreenShot [ NEW_Escalation_Id_Not_Genrated"+fnScreenShot_Date_format() +" ]";	
		   	fnApps_Report_Logs("PASSED == NEW Escalations ID has been generated === "+NEW_Escalation_Id);
		}catch(Throwable t){
			fnTake_Screen_Shot("NEW_Escalation_Id_Not_Genrated");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	
		//Clicking on Escalation ID link
		fnWait_and_Click("Escalation_id_link");
		
		fnGet_Element_Enabled("footer");
		
		
		try{
			
			//Verify 8 Newly generated issues exists into the table on Escalation details screen.
			fnGet_Element_Enabled("ViewEscalation_RelatedIssues_table");
			Thread.sleep(500);
			String related_issue_table=fnGet_Field_TEXT("ViewEscalation_RelatedIssues_table").trim();
			assert ((related_issue_table.contains(FirstIssueId_Green)) && related_issue_table.contains(SecondIssueId_Amber) && related_issue_table.contains(ThirdIssueId_Amber)  && related_issue_table.contains(ForthIssueId_Amber) && related_issue_table.contains(FifthIssueId_Red) ):
				"FAILED == Issues IDs are not Exists into the Related Issues Table."+"Please refer ScreenShot [ NEW_Escalation_Id_NotGenrated"+fnScreenShot_Date_format()+" ]";	
			fnApps_Report_Logs("PASSED == All issues ids exists into table");
		}catch(Throwable t){
			fnTake_Screen_Shot("NEW_Escalation_Id_NotGenrated");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}

	
	}catch(Throwable t){
		isTestCasePass = false;
		fnApps_Report_Logs(Throwables.getStackTraceAsString(t));
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
	
	

	fnSearchIssue_Ajax_Link_Click();
	fnGet_Element_Enabled("footer");
	fnsLoading_Progressingwait(1);

	fncEscalation_Verify_First_IssueClosed_after_EscalationClosed("SearchIssue_Escalation_Status",FirstIssueId_Green, "RED" , false);
	fncEscalation_Verify_First_IssueClosed_after_EscalationClosed("SearchIssue_Escalation_Status",SecondIssueId_Amber, "RED" , false);
	fncEscalation_Verify_First_IssueClosed_after_EscalationClosed("SearchIssue_Escalation_Status",ThirdIssueId_Amber, "RED" , false);
	fncEscalation_Verify_First_IssueClosed_after_EscalationClosed("SearchIssue_Escalation_Status",ForthIssueId_Amber, "RED" , false);
	fncEscalation_Verify_First_IssueClosed_after_EscalationClosed("SearchIssue_Escalation_Status",FifthIssueId_Red, "RED" , false);
}	





@Test(dependsOnMethods={"Escalation_ChangeFirstIssueDate_BackToOriginal_Verify_NEW_Escalation_ID_Generated_and_Verify_IssuesColor"}, priority = 19, description="  [Edit the Escalation and click Save. Click -> Assign to Me button.]")
public void Escalation_EditEscalation_AssignToMeButtonClick_and_ALERTs_VERIFY() throws Throwable{

	fnApps_Report_Logs("=========================================================================================================================================");
	fnApps_Report_Logs("################### Test Case ::::::19 Escalation_EditEscalation_AssignToMeButtonClick_and_ALERTs_VERIFY ");
	
	
	//Search Escalation and also navigate to ViewEscalation EscalationDetails page
	fncEscalation_SearchEscalation_and_Navigate_till_ViewEscalationDetailsPageComes(NEW_Escalation_Id);
	
	//Clicking on assign_to_me button
	fnGet_Element_Enabled("Escalation_Summary_assign_to_me_button");
	fnWait_and_Click("Escalation_Summary_assign_to_me_button");
	fnsLoading_Progressingwait(3);
	
	// Clicking on save button
	fnGet_Element_Enabled("Escalation_summary_details_save_button");
	fnWait_and_Click("Escalation_summary_details_save_button");
	fnsLoading_Progressingwait(3);
	
		
	fnGet_Element_Enabled("validation_error_msg");
	fncVerify_DataUpdated_Successfully("validation_error_msg", "Summary TAB : AssignToMe");	
	
	//Verify Alert (Escalations Assigned to Me as Admin)
	fnVerify_ADMIN_Alert_redirect_from_summarydetailsPage("EscalationsAssignedToMeAsAdmin_text_link","EscalationsAssignedToMeAsAdmin_Escalation_id_filter", NEW_Escalation_Id);
	fnVerify_ID_Exist_inAlert_Table("EscalationsAssignedToMeAsAdmin_table_1st_cell","EscalationsAssignedToMeAsAdmin_text_link", NEW_Escalation_Id);
			
	//Verify Alert (Escalations Assigned to Me. Awaiting Admin action )
	fnVerify_ADMIN_Alert_redirect_from_summarydetailsPage("EscalationsAssignedToMeAwaitingAdminAction_text_link","EscalationsAssignedToMeAwaitingAdminAction_Escalation_id_filter", NEW_Escalation_Id);
	fnVerify_ID_Exist_inAlert_Table("EscalationsAssignedToMeAwaitingAdminAction_table_1st_cell","EscalationsAssignedToMeAwaitingAdminAction_text_link", NEW_Escalation_Id);	
}



















@Test(dependsOnMethods={"Escalation_EditEscalation_AssignToMeButtonClick_and_ALERTs_VERIFY"}, priority = 20, description="  [Link the technologist name .select radio button as NSF  and mention the Technologist name. Click save.]")
public void Escalation_EditEscalation_SelectRadioButtonAsNSF_MentionTechnologistName_andThen_Alerts_Verify() throws Throwable{

	WebDriverWait waitvar = new WebDriverWait(driver, Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
	fnApps_Report_Logs("=========================================================================================================================================");	
	fnApps_Report_Logs("################### Test Case ::::::20 Escalation_EditEscalation_SelectRadioButtonAsNSF_MentionTechnologistName_andThen_Alerts_Verify ");
	
	//Search Escalation and also navigate to ViewEscalation EscalationDetails page
	fncEscalation_SearchEscalation_and_Navigate_till_ViewEscalationDetailsPageComes(NEW_Escalation_Id);
	
		
	// Select Technologist Type by clicking on radio button
	/*/fnGet_Element_Enabled("Escalation_Summary_TechnologistType_RadioButton");
	Thread.sleep(1500);
	fnWait_and_Click("Escalation_Summary_TechnologistType_RadioButton");
		
	Thread.sleep(3000);			
	Thread.sleep(2000); // Due to DD Loading an Additional time has been added 10.1.2016
	//Select value from Technologist Name drop down
	/fnDD_value_Select("Escalation_Summary_TechnologistName_DD_Click", "Escalation_Summary_TechnologistName_DD_Value", "li", IssueMgt_Suitexls.getCellData("Escalation_DIS", 2, 27));
	*/
	
	//Added on 29.3.2017 as Kelvin Owen is not comin into the DD and above code commented.
	fnc_ClickOn_TechnologistTypeRadioBttn_AndThen_Select_TechnologistNameFromDD("Escalation_Summary_TechnologistType_RadioButton", "Escalation_Summary_TechnologistName_DD_Click", "Escalation_Summary_TechnologistName_DD_Value");
			
	
	fnGet_Element_Enabled("Escalation_summary_details_save_button");
	fnWait_and_Click("Escalation_summary_details_save_button");
	fnsLoading_Progressingwait(3);

	fnGet_Element_Enabled("validation_error_msg");
	Thread.sleep(1000);
	fnGet_Element_Enabled("validation_error_msg");
	fncVerify_DataUpdated_Successfully("validation_error_msg", "View Escalation");	
	
	
	//Verify Alert (Escalations Assigned to Me as Admin)
	fnVerify_ADMIN_Alert_redirect_from_summarydetailsPage("EscalationsAssignedToMeAsAdmin_text_link","EscalationsAssignedToMeAsAdmin_Escalation_id_filter", NEW_Escalation_Id);
	fnVerify_ID_Exist_inAlert_Table("EscalationsAssignedToMeAsAdmin_table_1st_cell","EscalationsAssignedToMeAsAdmin_text_link", NEW_Escalation_Id);
	
	//Verify Alert (Escalations Assigned to Me. Awaiting Admin action )
	fnVerify_ADMIN_Alert_redirect_from_summarydetailsPage("EscalationsAssignedToMeAwaitingAdminAction_text_link","EscalationsAssignedToMeAwaitingAdminAction_Escalation_id_filter", NEW_Escalation_Id);
	fnVerify_ID_Exist_inAlert_Table("EscalationsAssignedToMeAwaitingAdminAction_table_1st_cell","EscalationsAssignedToMeAwaitingAdminAction_text_link", NEW_Escalation_Id);	
	
	//Verify Alert  (Escalations Assigned to Me as Technologist )
	fnVerify_TECH_Alert_redirect_from_AlertPage_by_SpecificUserRadioBttn("EscalationsAssignedToMeAsTechnologist_text_link","EscalationsAssignedToMeAsTechnologist_Escalation_id_filter", NEW_Escalation_Id);
	fnVerify_ID_Exist_inAlert_Table("EscalationsAssignedToMeAsTechnologist_table_1st_cell","EscalationsAssignedToMeAsTechnologist_text_link", NEW_Escalation_Id);
}






@Test(dependsOnMethods={"Escalation_EditEscalation_SelectRadioButtonAsNSF_MentionTechnologistName_andThen_Alerts_Verify"}, priority= 21, description="  [Change the status from >TECHNOLOGIST approved to close- > SATISFACTORY CLOSURE. Mention new status response>Click save.]")
public void Escalation_RelaunchIEDriver_to_continue_TestCase() throws Throwable{

	fnApps_Report_Logs("=========================================================================================================================================");
	fnApps_Report_Logs("########### Test Case :::21 Escalation_RelaunchIEDriver_to_continue_TestCase ");
		
	fnIEBrowser_Relaunched();
	Thread.sleep(5000);
	fnApps_Report_Logs("PASSED  ==  Successfully Relaunched IEDriver to Complete rest of the TestCase ");
}










@Test(dependsOnMethods={"Escalation_RelaunchIEDriver_to_continue_TestCase"}, priority = 22, description="  [Change the status from INITIAL RECORD > DETAILS SENT TO Distributor .mention new status response>Click save.]")
public void Escalation_EditEscalation_Change_status_InitialRecord_to_DetailsSentToDistributor_andThen_Alerts_Verify() throws Throwable{
	fnApps_Report_Logs("=========================================================================================================================================");
	fnApps_Report_Logs("################### Test Case ::::::22 Escalation_EditEscalation_Change_status_InitialRecord_to_DetailsSentToDistributor_andThen_Alerts_Verify ");

	
	//Search Escalation and also navigate to ViewEscalation EscalationDetails page
	fncEscalation_SearchEscalation_and_Navigate_till_ViewEscalationDetailsPageComes(NEW_Escalation_Id);
	
	
	fncEscalation_VerifyChange_Status_on_SummaryDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_DIS", 2, 36));
	
	fnGet_Element_Enabled("validation_error_msg");
	Thread.sleep(1000);
	fnGet_Element_Enabled("validation_error_msg");
	fncVerify_DataUpdated_Successfully("validation_error_msg", "View Escalation : Status change to 'DetailsSentToSupplier'");	
	
			
	//Verify Alert (Escalations Assigned to Me as Admin)
	fnVerify_ADMIN_Alert_redirect_from_summarydetailsPage("EscalationsAssignedToMeAsAdmin_text_link","EscalationsAssignedToMeAsAdmin_Escalation_id_filter", NEW_Escalation_Id);
	fnVerify_ID_Exist_inAlert_Table("EscalationsAssignedToMeAsAdmin_table_1st_cell","EscalationsAssignedToMeAsAdmin_text_link", NEW_Escalation_Id);
	
	//Verify Alert  (Escalations Assigned to Me as Technologist )
	fnVerify_TECH_Alert_redirect_from_AlertPage_by_SpecificUserRadioBttn("EscalationsAssignedToMeAsTechnologist_text_link","EscalationsAssignedToMeAsTechnologist_Escalation_id_filter", NEW_Escalation_Id);
	fnVerify_ID_Exist_inAlert_Table("EscalationsAssignedToMeAsTechnologist_table_1st_cell","EscalationsAssignedToMeAsTechnologist_text_link", NEW_Escalation_Id);

	//Verify Alert (Escalations Assigned to Me. Awaiting Supplier/Distributor action) 
	fnVerify_TECH_Alert_by_clicking_ShowAlertBttn_from_TECHAlertPage("EscalationsAssignedToMeAwaitingSupplierDistributorAction_text_link", "EscalationsAssignedToMeAwaitingSupplierDistributorAction_Escalation_id_filter", NEW_Escalation_Id);
	fnVerify_ID_Exist_inAlert_Table("EscalationsAssignedToMeAwaitingSupplierDistributorAction_table_1st_cell","EscalationsAssignedToMeAwaitingSupplierDistributorAction_text_link", NEW_Escalation_Id);
	
	//Verify Alert (Alert Removed --- Escalation assigned to me, awaiting Admin action)
	fnVerify_ADMIN_Alert_redirect_from_summarydetailsPage("EscalationsAssignedToMeAwaitingAdminAction_text_link","EscalationsAssignedToMeAwaitingAdminAction_Escalation_id_filter", NEW_Escalation_Id);
	fnVerify_NoRecordsFound_Msg_for_ID_When_AlertRemoved("EscalationsAssignedToMeAwaitingAdminAction_table_1st_cell","EscalationsAssignedToMeAwaitingAdminAction_text_link", NEW_Escalation_Id);
	
}

















@Test(dependsOnMethods={"Escalation_EditEscalation_Change_status_InitialRecord_to_DetailsSentToDistributor_andThen_Alerts_Verify"}, priority = 23, description="  [Change the status from DETAILS SENT TO Distributor to ESCALATION Response .mention new status response>Click save.]")
public void Escalation_EditEscalation_Change_status_DetailsSentToDistributor_to_EscalationResponse_andThen_Alerts_Verify() throws Throwable{

	fnApps_Report_Logs("=========================================================================================================================================");
	fnApps_Report_Logs("########## Test Case ::::23 Escalation_EditEscalation_Change_status_DetailsSentToDistributor_to_EscalationResponse_andThen_Alerts_Verify ");
	
	

	//Search Escalation and also navigate to ViewEscalation EscalationDetails page
	fncEscalation_SearchEscalation_and_Navigate_till_ViewEscalationDetailsPageComes(NEW_Escalation_Id);
	
	fncEscalation_VerifyChange_Status_on_SummaryDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_DIS", 2, 37));
	
	fnGet_Element_Enabled("validation_error_msg");
	Thread.sleep(1000);
	fnGet_Element_Enabled("validation_error_msg");
	fncVerify_DataUpdated_Successfully("validation_error_msg", "View Escalation : Status change to 'EscalationResponse'");	
			
	

	//Verify Alert (Escalations Assigned to Me as Admin)
	fnVerify_ADMIN_Alert_redirect_from_summarydetailsPage("EscalationsAssignedToMeAsAdmin_text_link","EscalationsAssignedToMeAsAdmin_Escalation_id_filter", NEW_Escalation_Id);
	fnVerify_ID_Exist_inAlert_Table("EscalationsAssignedToMeAsAdmin_table_1st_cell","EscalationsAssignedToMeAsAdmin_text_link", NEW_Escalation_Id);
	
	//Verify Alert (Escalations Assigned to Me. Awaiting Technologist action )
	fnVerify_TECH_Alert_redirect_from_AlertPage_by_SpecificUserRadioBttn("EscalationsAssignedToMeAwaitingTechnologistAction_text_link","EscalationsAssignedToMeAwaitingTechnologistAction_Escalation_id_filter", NEW_Escalation_Id);
	fnVerify_ID_Exist_inAlert_Table("EscalationsAssignedToMeAwaitingTechnologistAction_table_1st_cell","EscalationsAssignedToMeAwaitingTechnologistAction_text_link", NEW_Escalation_Id);	
	
	//Verify Alert  (Escalations Assigned to Me as Technologist )
	fnVerify_TECH_Alert_by_clicking_ShowAlertBttn_from_TECHAlertPage("EscalationsAssignedToMeAsTechnologist_text_link","EscalationsAssignedToMeAsTechnologist_Escalation_id_filter", NEW_Escalation_Id);
	fnVerify_ID_Exist_inAlert_Table("EscalationsAssignedToMeAsTechnologist_table_1st_cell","EscalationsAssignedToMeAsTechnologist_text_link", NEW_Escalation_Id);
	
	//Verify Alert (Alert Removed --- Escalation assigned to me, awaiting supplier/distributor action)
	fnVerify_TECH_Alert_by_clicking_ShowAlertBttn_from_TECHAlertPage("EscalationsAssignedToMeAwaitingSupplierDistributorAction_text_link","EscalationsAssignedToMeAwaitingSupplierDistributorAction_Escalation_id_filter", NEW_Escalation_Id);
	fnVerify_NoRecordsFound_Msg_for_ID_When_AlertRemoved("EscalationsAssignedToMeAwaitingSupplierDistributorAction_table_1st_cell","EscalationsAssignedToMeAwaitingSupplierDistributorAction_text_link", NEW_Escalation_Id);
}













@Test(dependsOnMethods={"Escalation_EditEscalation_Change_status_DetailsSentToDistributor_to_EscalationResponse_andThen_Alerts_Verify"}, priority = 24, description="  [Edit the 3rd issue created on 8th june and change its issue subtype to Damaged.]")
public void Escalation_EditEscalation_Change_3rd_IssueSubTypeValue_and_Verify_Escalation_Dismantled() throws Throwable{

	
	fnApps_Report_Logs("=========================================================================================================================================");
	fnApps_Report_Logs("################### Test Case ::::::24 Escalation_EditEscalation_Change_3rd_IssueSubTypeValue_and_Verify_Escalation_Dismantled.");
	
	//IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//src//nsf//ecap//xls//IssueMgt_Suite.xlsx");
	
	
	//Search IssueID and also navigate to ViewIssue Summary Details page 
	fncIssueID_SearchIssueID_and_Navigate_to_ViewIssueSummaryDetailsPageComes(ThirdIssueId_Amber, true);
	
	//Clicking on Summary TAB on View issue page
	fnGet_Element_Enabled("ViewIssueSummaryDetails_summary_detail_tab");
	fnWait_and_Click("ViewIssueSummaryDetails_summary_detail_tab");
	
	//Wait till IssueSubType DD enabled
	fnGet_Element_Enabled("ViewIssueSummaryDetails_IssueSubType_dd_click");
	
	//Select IssueSubType From Drop Down
	fnDD_value_Select("ViewIssueSummaryDetails_IssueSubType_dd_click", "ViewIssueSummaryDetails_IssueSubType_dd_value", "li", IssueMgt_Suitexls.getCellData("Escalation_DIS", 2, 21));
	
	
	// Clicking on save button
	fnGet_Element_Enabled("ViewIssueSummaryDetails_summary_details_save_button");
	Thread.sleep(2000);
	fnWait_and_Click("ViewIssueSummaryDetails_summary_details_save_button");
	fnsLoading_Progressingwait(3);
	
	fnGet_Element_Enabled("validation_error_msg");
	Thread.sleep(1000);
	fnGet_Element_Enabled("validation_error_msg");
	fncVerify_DataUpdated_Successfully("validation_error_msg", "Summary TAB ");
	

	fnSearchIssue_Ajax_Link_Click();
	fnGet_Element_Enabled("footer");
	fnsLoading_Progressingwait(1);

	fncEscalation_Verify_First_IssueClosed_after_EscalationClosed("SearchIssue_Escalation_Status",FirstIssueId_Green, "AMBER" , false);
	fncEscalation_Verify_First_IssueClosed_after_EscalationClosed("SearchIssue_Escalation_Status",SecondIssueId_Amber, "AMBER" , false);
	fncEscalation_Verify_First_IssueClosed_after_EscalationClosed("SearchIssue_Escalation_Status",ThirdIssueId_Amber, "GREEN" , false);
	fncEscalation_Verify_First_IssueClosed_after_EscalationClosed("SearchIssue_Escalation_Status",ForthIssueId_Amber, "AMBER" , false);
	fncEscalation_Verify_First_IssueClosed_after_EscalationClosed("SearchIssue_Escalation_Status",FifthIssueId_Red, "AMBER" , false);
	

}	

	




@Test(dependsOnMethods={"Escalation_EditEscalation_Change_3rd_IssueSubTypeValue_and_Verify_Escalation_Dismantled"}, priority = 25, description="  [Edit the 3rd issue created on 8th june and change its issue subtype back to Delivered.]")
public void Escalation_EditEscalation_Change_3rd_IssueSubTypeValue_BackToOriginal_and_Verify_NEW_Escalation_ID_Generated() throws Throwable{

	
	fnApps_Report_Logs("=========================================================================================================================================");
	fnApps_Report_Logs("########### Test Case ::::25 Escalation_EditEscalation_Change_3rd_IssueSubTypeValue_BackToOriginal_and_Verify_NEW_Escalation_ID_Generated ");

//	IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//src//nsf//ecap//xls//IssueMgt_Suite.xlsx");
	
	
	//Search IssueID and also navigate to ViewIssue Summary Details page 
	fncIssueID_SearchIssueID_and_Navigate_to_ViewIssueSummaryDetailsPageComes(ThirdIssueId_Amber, true);
	
	//Clicking on Summary TAB on View issue page
	fnGet_Element_Enabled("ViewIssueSummaryDetails_summary_detail_tab");
	fnWait_and_Click("ViewIssueSummaryDetails_summary_detail_tab");
	
	
	//Wait till IssueSubType DD enabled
	fnGet_Element_Enabled("ViewIssueSummaryDetails_IssueSubType_dd_click");
	
	//Select IssueSubType From Drop Down
	fnDD_value_Select("ViewIssueSummaryDetails_IssueSubType_dd_click", "ViewIssueSummaryDetails_IssueSubType_dd_value", "li",  IssueMgt_Suitexls.getCellData("Escalation_DIS", 2, 20));
	
	
	
	
	// Clicking on save button
		fnGet_Element_Enabled("ViewIssueSummaryDetails_summary_details_save_button");
		fnWait_and_Click("ViewIssueSummaryDetails_summary_details_save_button");
		fnsLoading_Progressingwait(3);
		
		fnGet_Element_Enabled("validation_error_msg");
		Thread.sleep(1000);
		fnGet_Element_Enabled("validation_error_msg");
		fncVerify_DataUpdated_Successfully("validation_error_msg", "Summary TAB ");
		
		try{
			
			//Verify NEW Escalation Generated
			fnGet_Element_Enabled("Escalation_id_link");
			Thread.sleep(500);
			NEW_Escalation_Id_SubType=fnGet_Field_TEXT("Escalation_id_link").trim();
			try{
				assert (!NEW_Escalation_Id_SubType.contains(NEW_Escalation_Id)):"FAILED == NEW Escalations ID is not Generated >> "+NEW_Escalation_Id_SubType+", Please refer ScreenShot [ NEW_Escalation_Id_Not_Genrated"+fnScreenShot_Date_format() +" ]";	
			   	fnApps_Report_Logs("PASSED == NEW Escalations ID has been generated === "+NEW_Escalation_Id_SubType);
			}catch(Throwable t){
				fnTake_Screen_Shot("NEW_Escalation_Id_Not_Genrated");
				throw new Exception(Throwables.getStackTraceAsString(t));
			}
		
			//Clicking on Escalation ID link
			fnWait_and_Click("Escalation_id_link");
			
			fnGet_Element_Enabled("footer");
			
			
			try{
				
				//Verify 8 Newly generated issues exists into the table on Escalation details screen.
				fnGet_Element_Enabled("ViewEscalation_RelatedIssues_table");
				Thread.sleep(500);
				String related_issue_table=fnGet_Field_TEXT("ViewEscalation_RelatedIssues_table").trim();
				assert ((related_issue_table.contains(FirstIssueId_Green)) && related_issue_table.contains(SecondIssueId_Amber) && related_issue_table.contains(ThirdIssueId_Amber)  && related_issue_table.contains(ForthIssueId_Amber) && related_issue_table.contains(FifthIssueId_Red) ):
					"FAILED == Issues IDs are not Exists into the Related Issues Table."+"Please refer ScreenShot [ NEW_Escalation_Id_NotGenrated"+fnScreenShot_Date_format()+" ]";	
				fnApps_Report_Logs("PASSED == All issues ids exists into table");
			}catch(Throwable t){
				fnTake_Screen_Shot("NEW_Escalation_Id_NotGenrated");
				throw new Exception(Throwables.getStackTraceAsString(t));
			}

		
		}catch(Throwable t){
			isTestCasePass = false;
			fnApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		
		

		fnSearchIssue_Ajax_Link_Click();
		fnGet_Element_Enabled("footer");
		fnsLoading_Progressingwait(1);

		fncEscalation_Verify_First_IssueClosed_after_EscalationClosed("SearchIssue_Escalation_Status",FirstIssueId_Green, "RED" , false);
		fncEscalation_Verify_First_IssueClosed_after_EscalationClosed("SearchIssue_Escalation_Status",SecondIssueId_Amber, "RED" , false);
		fncEscalation_Verify_First_IssueClosed_after_EscalationClosed("SearchIssue_Escalation_Status",ThirdIssueId_Amber, "RED" , false);
		fncEscalation_Verify_First_IssueClosed_after_EscalationClosed("SearchIssue_Escalation_Status",ForthIssueId_Amber, "RED" , false);
		fncEscalation_Verify_First_IssueClosed_after_EscalationClosed("SearchIssue_Escalation_Status",FifthIssueId_Red, "RED" , false);
		
		
		fnApps_Report_Logs("######################################### [BS - 15]  CreateEscalation_DIS Script END ################################################## ");
		fnApps_Report_Logs("=========================================================================================================================================");

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
	
	
	
	
	
	
	
