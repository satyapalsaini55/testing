package nsf.ecap.IssueMgt_Suite;


//
import nsf.ecap.util.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;

public class CreateIssue_PRQ extends TestSuiteBase_IM{
	
	public static String issue_id="I0162109";
	public static String validation_error_msg_text;
	public static String TitleShortDescriptionText="CreateIssue_PRQ[BS-01], D/T=";
	
	public static boolean IsBrowserPresentAlready = false;
	
		
	
	
	@Parameters({ "className" })
	@BeforeTest
	public void checkTestSkip(String className) throws Exception {
		isTestCasePass=true;
		try {
			// this.getClass().getSimpleName()
			if (className.isEmpty()) {
				className = this.getClass().getSimpleName();

			}
			
			classNameText=className;
			currentScriptCode=fnpMappingClassNameWithScenarioCode(classNameText).trim();

			if (!TestUtil.isTestCaseRunnable(IssueMgt_Suitexls, className)) {
				fnApps_Report_Logs("Skipping Test Case " + className + " as runmode set to NO");// logs
				throw new SkipException("Skipping Test Case " + className + " as runmode set to NO");// reports
			}

		
			fnApps_Report_Logs("=========================================================================================================================================");
			//fnLoadHashData(hashXlData, IssueMgt_Suitexls, className, 2);
		
		}
		catch(SkipException sk){
			String stackTrace = Throwables.getStackTraceAsString(sk);
			String errorMsg = sk.getMessage();
			Exception c = new Exception(errorMsg);
			ErrorUtil.addVerificationFailure(c);
			throw new SkipException(errorMsg + " \n\nSo,Skipping Test Case" + className);// reports
		}
		catch (Throwable t) {
								
				//fnDesireScreenshot(className);				
				String stackTrace = Throwables.getStackTraceAsString(t);
				String errorMsg = Throwables.getStackTraceAsString(t);
				errorMsg = errorMsg + "\n\n\n\nSee screenshot '" + className + "'\n\n" + stackTrace;
				Exception c = new Exception(errorMsg);
				ErrorUtil.addVerificationFailure(c);
				throw new Exception(errorMsg );// reports
		}
	}
	

	

@BeforeClass (description="OracleQueryLaunch_ToClosed_OpenIssues_and_InActiveAlerts_Whose --- productCode(501214) and Client(Demo Customer)")
public void OracleQueryLaunch_ToClosed_OpenIssues_and_InActiveAlerts() throws Throwable {

	//Function Called to close all issues in database
	fnUpdateDB_to_Close_issues();
	
}

	
	
	
@Test( priority = 0)
public void Launch_Browser_and_Successfully_Login_into_the_Application() throws Throwable{
	fnApps_Report_Logs("################################## [BS-01]  Create Issue PRQ");
	if (!IsBrowserPresentAlready) {
		IsBrowserPresentAlready = fnLaunchBrowserAndLogin();
		fnApps_Report_Logs("Browser is launched and ******** Successfully login into 'I-Pulse' Application.");
	}
}	
	
	
	@Test(dependsOnMethods={"Launch_Browser_and_Successfully_Login_into_the_Application"}, priority = 1, description="Verify that issue id is generated with message.")
	public void PRQ_CreateIssue() throws Exception{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::1 PRQ_CreateIssue ");
		
		  
	//	IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//src//nsf//ecap//xls//IssueMgt_Suite.xlsx");
	
		//Clicking on CreateIssue Ajax Link
		fnCreateIssue_Ajax_Link_Click();
				
		//Site Details page data entered and clicking on next button
		fnVerify_SiteDetailsPage_Data_Entered_and_NavigateTo_SummaryDetailsPage(IssueMgt_Suitexls.getCellData("CreateIssue_PRQ", 2, 2), IssueMgt_Suitexls.getCellData("CreateIssue_PRQ", 2, 3));
	
		//Summary Details page data entered and clicking on next button
		fnVerify_SummaryDetailsPage_Data_Entered_and_NavigateTo_CustomerDetailsPage(IssueMgt_Suitexls.getCellData("CreateIssue_PRQ", 2, 8), IssueMgt_Suitexls.getCellData("CreateIssue_PRQ", 2, 9), IssueMgt_Suitexls.getCellData("CreateIssue_PRQ", 2, 10), fnGet_2Days_Past_date(),TitleShortDescriptionText);



		//Clicking on Customer  NEXT Button
		fnClick_UnTill_Navigate_to_NextPage("CreateIssue_Customer_next_bttn","CreateIssue_Customer_Name_TextField","CreateIssue_product_code_bttn");	
		
		//Product Details page data entered and clicking on next button
		fnVerify_ProductDetailsPage_Data_Entered_and_NavigateTo_DocumentsDetailsPage(IssueMgt_Suitexls.getCellData("CreateIssue_PRQ", 2, 12));
		
	
		// Clicking on Document next button
		fnClick_UnTill_Navigate_to_NextPage("CreateIssue_document_next_bttn","CreateIssue_document_Add_Attachement","CreateIssue_confirmation_submit_bttn");	
			
		
		
		// Clicking on Confirmation Submit button
		fnGet_OR_IM_ObjectX("CreateIssue_confirmation_submit_bttn");
		fnWait_and_Click("CreateIssue_confirmation_submit_bttn");
		
		
		//Verify issue ID Generated and Color is as per Expected and Stored into String
		issue_id=fnVerified_IssueID_Generated_and_Color_Verification(1, "GREEN");
					
	}
	
	
	@Test(dependsOnMethods={"PRQ_CreateIssue"}, priority = 2, description="Verify Alert --- Food Issues Unassigned.")
	public void Check_Alert_FoodIssuesUnassigned() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::2 Check_Alert_FoodIssuesUnassigned ");
		
		fnVerify_ADMIN_Alert_redirect_from_summarydetailsPage("Issues_FoodIssuesUnassigned_text_link","Issues_FoodIssuesUnassigned_issue_id_filter", issue_id);
		
		fnVerify_ID_Exist_inAlert_Table("Issues_FoodIssuesUnassigned_table_1st_cell","Issues_FoodIssuesUnassigned_text_link", issue_id);

	}

	
	
	
	
	
	@Test(dependsOnMethods={"Check_Alert_FoodIssuesUnassigned"},priority = 3,description="Go to the Summary tab.Click on - Assign to me button.   >>> Select the technologist type as : NSF and select the Technologist name. Check Assign to Technologist. Click Save. Verify the Alerts.")
	public void Verify_DataUpdated_in_SummaryDetailsTab_and_DetailsTab() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::3 Verify_DataUpdated_in_SummaryDetailsTab_and_DetailsTab ");
	
	//	WebDriverWait waitvar = new WebDriverWait(driver, 20);
	//	IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//src//nsf//ecap//xls//IssueMgt_Suite.xlsx");
				
		fncIssueID_SearchIssueID_and_Navigate_to_ViewIssueSummaryDetailsPageComes(issue_id, true);	
		
		fncIssue_DetailsTAB_RadioButtonsSelect_and_Verify_SaveSuccessfully();
		
		//Clicking on Summary TAB on View issue page
		fnGet_Element_Enabled("ViewIssueSummaryDetails_summary_detail_tab");
		fnWait_and_Click("ViewIssueSummaryDetails_summary_detail_tab");
		
		// Clicking on Assign to me button
		fnGet_Element_Enabled("ViewIssueSummaryDetails_assign_to_me");
		fnWait_and_Click("ViewIssueSummaryDetails_assign_to_me");
		fnsLoading_Progressingwait(3);
		
		/*// Select Technologist Type by clicking on radio button
		/fnGet_Element_Enabled("ViewIssueSummaryDetails_technologist_type_radio_bttn");
		Thread.sleep(1500);
		fnWait_and_Click("ViewIssueSummaryDetails_technologist_type_radio_bttn");
		Thread.sleep(3000);
		Thread.sleep(2000); // Due to DD Loading an Additional time has been added 10.1.2016
		
		//Select value from Technologist Name drop down
		fnDD_value_Select("ViewIssueSummaryDetails_technologist_name_dd_click", "ViewIssueSummaryDetails_technologist_name_dd_value", "li", IssueMgt_Suitexls.getCellData("CreateIssue_PRQ", 2, 15));
		/*/
		
		//Added on 24.3.2017 as Kelvin Owen is not comin into the DD
		fnc_ClickOn_TechnologistTypeRadioBttn_AndThen_Select_TechnologistNameFromDD("ViewIssueSummaryDetails_technologist_type_radio_bttn", "ViewIssueSummaryDetails_technologist_name_dd_click", "ViewIssueSummaryDetails_technologist_name_dd_value");
		
		Thread.sleep(500);
		//Select Assign To Technologist Check box 
		fnGet_Element_Enabled("ViewIssueSummaryDetails_assign_to_technologist");
		fnWait_and_Click("ViewIssueSummaryDetails_assign_to_technologist");
			
		// Clicking on save button
		fnGet_Element_Enabled("ViewIssueSummaryDetails_summary_details_save_button");
		fnWait_and_Click("ViewIssueSummaryDetails_summary_details_save_button");
		fnsLoading_Progressingwait(3);
		
		fnGet_Element_Enabled("validation_error_msg");
		fncVerify_DataUpdated_Successfully("validation_error_msg", "Summary TAB : AssignToMe");	
}
	
	
	
	@Test(dependsOnMethods={"Verify_DataUpdated_in_SummaryDetailsTab_and_DetailsTab"},priority = 4, description="Verify Alert --- Issues Assigned to Me as Admin")
	public void Check_Alert_IssuesAssignedToMeAsAdmin() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::4 Check_Alert_IssuesAssignedToMeAsAdmin ");
		
		fnVerify_ADMIN_Alert_redirect_from_summarydetailsPage("IssuesAssignedToMeAsAdmin_text_link","IssuesAssignedToMeAsAdmin_issue_id_filter", issue_id);
		
		fnVerify_ID_Exist_inAlert_Table("IssuesAssignedToMeAsAdmin_table_1st_cell","IssuesAssignedToMeAsAdmin_text_link", issue_id);

	}
	
	
	
	@Test(dependsOnMethods={"Check_Alert_IssuesAssignedToMeAsAdmin"},priority = 5, description="Verify Alert --- Issues Assigned to Me as Technologist")
	public void Check_Alert_IssuesAssignedToMeAsTechnologist() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::5 Check_Alert_IssuesAssignedToMeAsTechnologist ");
		
		fnVerify_TECH_Alert_redirect_from_AlertPage_by_SpecificUserRadioBttn("IssuesAssignedtoMeasTechnologist_text_link","IssuesAssignedtoMeasTechnologist_issue_id_filter", issue_id);
		fnVerify_ID_Exist_inAlert_Table("IssuesAssignedtoMeasTechnologist_table_1st_cell","IssuesAssignedtoMeasTechnologist_text_link", issue_id);
	
	}
		
	
	

	@Test(dependsOnMethods={"Check_Alert_IssuesAssignedToMeAsTechnologist"},priority = 6, description="Verify Alert --- Issues Assigned to Me. Awaiting Technologist action")
	public void Check_alert_IssuesAssignedToMeAwaitingTechnologistAction() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::6 Check_alert_IssuesAssignedToMeAwaitingTechnologistAction ");
		
		fnVerify_TECH_Alert_by_clicking_ShowAlertBttn_from_TECHAlertPage("IssuesAssignedtoMeAwaitingTechnologistaction_text_link","IssuesAssignedtoMeAwaitingTechnologistaction_issue_id_filter", issue_id);
		fnVerify_ID_Exist_inAlert_Table("IssuesAssignedtoMeAwaitingTechnologistaction_table_1st_cell","IssuesAssignedtoMeAwaitingTechnologistaction_text_link", issue_id);
		
	}
	
	
	@Test(dependsOnMethods={"Check_alert_IssuesAssignedToMeAwaitingTechnologistAction"},priority= 7, description="Change the status : Initial Record to -> Additional Info and mention the new status response. Click on save")
	public void Change_status_InitialRecord_to_AdditionalInfoRequired() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::7 Change_status_InitialRecord_to_AdditionalInfoRequired ");
		
		fnIssue_VerifyChange_Status_on_SummaryDetailsPage(IssueMgt_Suitexls.getCellData("CreateIssue_PRQ", 2, 16), issue_id);

	}
	
	
	
	
	@Test(dependsOnMethods={"Change_status_InitialRecord_to_AdditionalInfoRequired"},priority = 8, description="Verify  AdditionalInfoRequired Alert --- Issues Assigned to Me as Admin")
	public void AdditionalInfoRequired_Check_Alert_IssuesAssignedToMeAsAdmin() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::8 AdditionalInfoRequired_Check_Alert_IssuesAssignedToMeAsAdmin ");
		
		fnVerify_ADMIN_Alert_redirect_from_summarydetailsPage("IssuesAssignedToMeAsAdmin_text_link","IssuesAssignedToMeAsAdmin_issue_id_filter", issue_id);
		
		fnVerify_ID_Exist_inAlert_Table("IssuesAssignedToMeAsAdmin_table_1st_cell","IssuesAssignedToMeAsAdmin_text_link", issue_id);
					
	}
	
	
	
	@Test(dependsOnMethods={"AdditionalInfoRequired_Check_Alert_IssuesAssignedToMeAsAdmin"},priority = 9, description="Verify AdditionalInfoRequired  Alert --- Issues Assigned to Me as Technologist")
	public void AdditionalInfoRequired_Check_Alert_IssuesAssignedtoMeAsTechnologist() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::9 AdditionalInfoRequired_Check_Alert_IssuesAssignedtoMeAsTechnologist ");
		
		fnVerify_TECH_Alert_redirect_from_AlertPage_by_SpecificUserRadioBttn("IssuesAssignedtoMeasTechnologist_text_link","IssuesAssignedtoMeasTechnologist_issue_id_filter", issue_id);
		
		fnVerify_ID_Exist_inAlert_Table("IssuesAssignedtoMeasTechnologist_table_1st_cell","IssuesAssignedtoMeasTechnologist_text_link", issue_id);
	
		
	}
		
	
	

	@Test(dependsOnMethods={"AdditionalInfoRequired_Check_Alert_IssuesAssignedtoMeAsTechnologist"},priority = 10, description="Verify AdditionalInfoRequired  Alert --- Issues Assigned To Me Awaiting Client Action")
	public void AdditionalInfoRequired_Check_Alert_IssuesAssignedToMeAwaitingClientAction() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::10 AdditionalInfoRequired_Check_Alert_IssuesAssignedToMeAwaitingClientAction ");
		
		
		fnVerify_TECH_Alert_by_clicking_ShowAlertBttn_from_TECHAlertPage("IssuesAssignedtoMeAwaitingClientaction_text_link","IssuesAssignedtoMeAwaitingClientaction_issue_id_filter", issue_id);
		fnVerify_ID_Exist_inAlert_Table("IssuesAssignedtoMeAwaitingClientaction_table_1st_cell","IssuesAssignedtoMeAwaitingClientaction_text_link", issue_id);
		
	}
	
	
	

	
	@Test(dependsOnMethods={"AdditionalInfoRequired_Check_Alert_IssuesAssignedToMeAwaitingClientAction"},priority=11, description="Change the Additional Info to > Initial Record and mention the new status response.Click on save.")
	public void Change_Status_AdditionalInfoRequired_TO_InitialRecord() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::11 Change_Status_AdditionalInfoRequired_TO_InitialRecord ");
		
		fnIssue_VerifyChange_Status_on_SummaryDetailsPage(IssueMgt_Suitexls.getCellData("CreateIssue_PRQ", 2, 18), issue_id);
		
	}
	
	
	
	@Test(dependsOnMethods={"Change_Status_AdditionalInfoRequired_TO_InitialRecord"},priority = 12, description="Verify InitialRecord Alert --- Issues Assigned to Me as Admin")
	public void InitialRecord_Check_Alert_IssuesAssignedToMeAsAdmin() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::12 InitialRecord_Check_Alert_IssuesAssignedToMeAsAdmin ");
		
		fnVerify_ADMIN_Alert_redirect_from_summarydetailsPage("IssuesAssignedToMeAsAdmin_text_link","IssuesAssignedToMeAsAdmin_issue_id_filter", issue_id);
		fnVerify_ID_Exist_inAlert_Table("IssuesAssignedToMeAsAdmin_table_1st_cell","IssuesAssignedToMeAsAdmin_text_link", issue_id);
					
	}
	
	
	
	@Test(dependsOnMethods={"InitialRecord_Check_Alert_IssuesAssignedToMeAsAdmin"},priority = 13, description="Verify InitialRecord Alert --- Issues Assigned to Me as Technologist")
	public void InitialRecord_Check_Alert_IssuesAssignedToMeAsTechnologist() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::13 InitialRecord_Check_Alert_IssuesAssignedToMeAsTechnologist ");
		
		fnVerify_TECH_Alert_redirect_from_AlertPage_by_SpecificUserRadioBttn("IssuesAssignedtoMeasTechnologist_text_link","IssuesAssignedtoMeasTechnologist_issue_id_filter", issue_id);
		fnVerify_ID_Exist_inAlert_Table("IssuesAssignedtoMeasTechnologist_table_1st_cell","IssuesAssignedtoMeasTechnologist_text_link", issue_id);
	
	}
		
	
	

	@Test(dependsOnMethods={"InitialRecord_Check_Alert_IssuesAssignedToMeAsTechnologist"},priority = 14, description="Verify InitialRecord Alert --- Issues Assigned to Me. Awaiting Technologist action")
	public void InitialRecord_Check_Alert_IssuesAssignedToMeAwaitingTechnologistAction() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::14 InitialRecord_Check_Alert_IssuesAssignedToMeAwaitingTechnologistAction ");
		
		fnVerify_TECH_Alert_by_clicking_ShowAlertBttn_from_TECHAlertPage("IssuesAssignedtoMeAwaitingTechnologistaction_text_link","IssuesAssignedtoMeAwaitingTechnologistaction_issue_id_filter", issue_id);
		fnVerify_ID_Exist_inAlert_Table("IssuesAssignedtoMeAwaitingTechnologistaction_table_1st_cell","IssuesAssignedtoMeAwaitingTechnologistaction_text_link", issue_id);
	
	}
	


	
	
	@Test(dependsOnMethods={"InitialRecord_Check_Alert_IssuesAssignedToMeAwaitingTechnologistAction"},priority=15, description="Set the Initial Record to Details sent to supplier >  Mention the new status response.Click on save.")
	public void Change_status_InitialRecord_to_DetailsSentToSupplier() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::15 Change_status_InitialRecord_to_DetailsSentToSupplier ");
		
		fnIssue_VerifyChange_Status_on_SummaryDetailsPage(IssueMgt_Suitexls.getCellData("CreateIssue_PRQ", 2, 19), issue_id);

	}
	
	
	
	
	@Test(dependsOnMethods={"Change_status_InitialRecord_to_DetailsSentToSupplier"},priority = 16, description="Verify DetailsSentToSupplier Alert --- --Issues Assigned to Me as Admin")
	public void DetailsSentToSupplier_Check_Alert_IssuesAssignedToMeAsAdmin() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::16 DetailsSentToSupplier_Check_Alert_IssuesAssignedToMeAsAdmin ");
		
		fnVerify_ADMIN_Alert_redirect_from_summarydetailsPage("IssuesAssignedToMeAsAdmin_text_link","IssuesAssignedToMeAsAdmin_issue_id_filter", issue_id);
		fnVerify_ID_Exist_inAlert_Table("IssuesAssignedToMeAsAdmin_table_1st_cell","IssuesAssignedToMeAsAdmin_text_link", issue_id);
					
	}
	
	
	
	@Test(dependsOnMethods={"DetailsSentToSupplier_Check_Alert_IssuesAssignedToMeAsAdmin"},priority = 17, description="Verify DetailsSentToSupplier Alert --- Issues Assigned to Me as Technologist")
	public void DetailsSentToSupplier_Check_Alert_IssuesAssignedtoMeAsTechnologist() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::17  DetailsSentToSupplier_Check_Alert_IssuesAssignedtoMeAsTechnologist ");
		
		fnVerify_TECH_Alert_redirect_from_AlertPage_by_SpecificUserRadioBttn("IssuesAssignedtoMeasTechnologist_text_link","IssuesAssignedtoMeasTechnologist_issue_id_filter", issue_id);
		fnVerify_ID_Exist_inAlert_Table("IssuesAssignedtoMeasTechnologist_table_1st_cell","IssuesAssignedtoMeasTechnologist_text_link", issue_id);
	
	}
		


	@Test(dependsOnMethods={"DetailsSentToSupplier_Check_Alert_IssuesAssignedtoMeAsTechnologist"},priority = 18, description="Verify DetailsSentToSupplier Alert ---  Issues Assigned to Me. Awaiting Supplier/Distributor action ")
	public void DetailsSentToSupplier_Check_Alert_IssuesAssignedToMeAwaitingSupplierDistributorAction() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::18 DetailsSentToSupplier_Check_Alert_IssuesAssignedToMeAwaitingSupplierDistributorAction ");
		
		fnVerify_TECH_Alert_by_clicking_ShowAlertBttn_from_TECHAlertPage("IssuesAssignedToMeAwaitingSupplierDistributorAction_text_link", "IssuesAssignedToMeAwaitingSupplierDistributorAction_issue_id_filter", issue_id);
		fnVerify_ID_Exist_inAlert_Table("IssuesAssignedToMeAwaitingSupplierDistributorAction_table_1st_cell","IssuesAssignedToMeAwaitingSupplierDistributorAction_text_link", issue_id);
		
	}
	

	
	
	@Test(dependsOnMethods={"DetailsSentToSupplier_Check_Alert_IssuesAssignedToMeAwaitingSupplierDistributorAction"},priority=19, description="Set the Details sent to supplier >Issue Response  and mention the new status response.Click on save")
	public void Change_status_DetailsSentToSupplier_to_IssueResponse() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::19 Change_status_DetailsSentToSupplier_to_IssueResponse ");
		
		fnIssue_VerifyChange_Status_on_SummaryDetailsPage(IssueMgt_Suitexls.getCellData("CreateIssue_PRQ", 2, 20), issue_id);
		
	}
	
	
	
	
	@Test(dependsOnMethods={"Change_status_DetailsSentToSupplier_to_IssueResponse"},priority = 20, description="Verify IssueResponse Alert --- --Issues Assigned to Me as Admin")
	public void IssueResponse_Check_Alert_IssuesAssignedToMeAsAdmin() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::20 IssueResponse_Check_Alert_IssuesAssignedToMeAsAdmin ");
		
		fnVerify_ADMIN_Alert_redirect_from_summarydetailsPage("IssuesAssignedToMeAsAdmin_text_link","IssuesAssignedToMeAsAdmin_issue_id_filter", issue_id);
		fnVerify_ID_Exist_inAlert_Table("IssuesAssignedToMeAsAdmin_table_1st_cell","IssuesAssignedToMeAsAdmin_text_link", issue_id);
	}
	

	
	@Test(dependsOnMethods={"IssueResponse_Check_Alert_IssuesAssignedToMeAsAdmin"},priority = 21, description="Verify IssueResponse Alert --- Issues Assigned to Me as Technologist")
	public void IssueResponse_Check_Alert_IssuesAssignedtoMeAsTechnologist() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::21 IssueResponse_Check_Alert_IssuesAssignedtoMeAsTechnologist ");
		
		fnVerify_TECH_Alert_redirect_from_AlertPage_by_SpecificUserRadioBttn("IssuesAssignedtoMeasTechnologist_text_link","IssuesAssignedtoMeasTechnologist_issue_id_filter", issue_id);
		fnVerify_ID_Exist_inAlert_Table("IssuesAssignedtoMeasTechnologist_table_1st_cell","IssuesAssignedtoMeasTechnologist_text_link", issue_id);
	
	}
		


	@Test(dependsOnMethods={"IssueResponse_Check_Alert_IssuesAssignedtoMeAsTechnologist"},priority = 22, description="Verify IssueResponse Alert ---   Issues Assigned to Me. Awaiting Technologist action")
	public void IssueResponse_Check_Alert_IssuesAssignedToMeAwaitingTechnologistAction() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::22 IssueResponse_Check_Alert_IssuesAssignedToMeAwaitingTechnologistAction ");
		
		fnVerify_TECH_Alert_by_clicking_ShowAlertBttn_from_TECHAlertPage("IssuesAssignedtoMeAwaitingTechnologistaction_text_link", "IssuesAssignedtoMeAwaitingTechnologistaction_issue_id_filter", issue_id);
		fnVerify_ID_Exist_inAlert_Table("IssuesAssignedtoMeAwaitingTechnologistaction_table_1st_cell","IssuesAssignedtoMeAwaitingTechnologistaction_text_link", issue_id);
			
	}
	
	


	
	
	@Test(dependsOnMethods={"IssueResponse_Check_Alert_IssuesAssignedToMeAwaitingTechnologistAction"},priority=23, description="Set the  Issue Response  >Technology Approved to Close and mention the new status response.Click on save.")
	public void Change_status_IssueResponse_to_TechnologistApprovedToClose() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::23 Change_status_IssueResponse_to_TechnologistApprovedToClose ");
		
		fnIssue_VerifyChange_Status_on_SummaryDetailsPage(IssueMgt_Suitexls.getCellData("CreateIssue_PRQ", 2, 21), issue_id);
				
		Thread.sleep(2000);
		WebElement AssignToTechnologist=driver.findElement(By.xpath(OR_IM.getProperty("ViewIssueSummaryDetails_assign_to_technologist")));
		assert !(AssignToTechnologist.isSelected()):"AssignToTechnologist checkbox is selected after changing status to Technologist Approved to Close";
	}
	

	
	@Test(dependsOnMethods={"Change_status_IssueResponse_to_TechnologistApprovedToClose"},priority = 24, description="Verify Technology Approved to Close Alert --- --Issues Assigned to Me as Admin")
	public void TechnologistApprovedToClose_Check_Alert_IssuesAssignedToMeAsAdmin() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::24 TechnologistApprovedToClose_Check_Alert_IssuesAssignedToMeAsAdmin ");
		
		fnVerify_ADMIN_Alert_redirect_from_summarydetailsPage("IssuesAssignedToMeAsAdmin_text_link","IssuesAssignedToMeAsAdmin_issue_id_filter", issue_id);
		fnVerify_ID_Exist_inAlert_Table("IssuesAssignedToMeAsAdmin_table_1st_cell","IssuesAssignedToMeAsAdmin_text_link", issue_id);
		
	}
	

	
	
	
	@Test(dependsOnMethods={"TechnologistApprovedToClose_Check_Alert_IssuesAssignedToMeAsAdmin"},priority = 25, description="Verify Technology Approved to Close Alert --- Issues Assigned to Me as Technologist")
	public void TechnologistApprovedToClose_Check_Alert_IssuesAssignedtoMeAsTechnologist() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::25 TechnologistApprovedToClose_Check_Alert_IssuesAssignedtoMeAsTechnologist ");
		
		fnVerify_TECH_Alert_redirect_from_AlertPage_by_SpecificUserRadioBttn("IssuesAssignedtoMeasTechnologist_text_link","IssuesAssignedtoMeasTechnologist_issue_id_filter", issue_id);
		fnVerify_ID_Exist_inAlert_Table("IssuesAssignedtoMeasTechnologist_table_1st_cell","IssuesAssignedtoMeasTechnologist_text_link", issue_id);
	
	}
		

	
	

	@Test(dependsOnMethods={"TechnologistApprovedToClose_Check_Alert_IssuesAssignedtoMeAsTechnologist"},priority = 26, description="Verify Technology Approved to Close Alert ---   Issues Assigned to Me. Awaiting Admin action")
	public void TechnologistApprovedToClose_Check_Alert_IssuesAssignedToMeAwaitingAdminAction() throws Throwable{
		
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::26 TechnologistApprovedToClose_Check_Alert_IssuesAssignedToMeAwaitingAdminAction ");
		
		fnVerify_ADMIN_Alert_redirect_from_summarydetailsPage("IssuesAssignedtoMeAwaitingAdminaction_text_link","IssuesAssignedtoMeAwaitingAdminaction_issue_id_filter", issue_id);
		fnVerify_ID_Exist_inAlert_Table("IssuesAssignedtoMeAwaitingAdminaction_table_1st_cell","IssuesAssignedtoMeAwaitingAdminaction_text_link", issue_id);	
	}
	
	
	
	
	@Test(dependsOnMethods={"TechnologistApprovedToClose_Check_Alert_IssuesAssignedToMeAwaitingAdminAction"},priority=27, description="Set the Technology Approved to Close>Satisfactory Closure and mention the new status response.Click on save.")
	public void Change_status_TechnologistApprovedToClose_to_SatisfactoryClosure() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::27 Change_status_TechnologistApprovedToClose_to_SatisfactoryClosure ");
		
		fnIssue_VerifyChange_Status_on_SummaryDetailsPage(IssueMgt_Suitexls.getCellData("CreateIssue_PRQ", 2, 22), issue_id);	
	}
	
	
	

	@Test(dependsOnMethods={"Change_status_TechnologistApprovedToClose_to_SatisfactoryClosure"},priority = 28, description="Verify IssueID is Not Present in Alerts After Issue Closed")
	public void SatisfactoryClosure_Check_IssueID_Not_Present_In_Alerts_After_Issue_Closed() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::28 SatisfactoryClosure_Check_IssueID_Not_Present_In_Alerts_After_Issue_Closed ");
		
		//Verify Alert (Issues Assigned to Me as Admin) after Issue ID Closed
		fnVerify_ADMIN_Alert_redirect_from_summarydetailsPage("IssuesAssignedToMeAsAdmin_text_link","IssuesAssignedToMeAsAdmin_issue_id_filter", issue_id);
		fnVerify_NoRecordsFound_Msg_for_ID_When_AlertRemoved("IssuesAssignedToMeAsAdmin_table_1st_cell","IssuesAssignedToMeAsAdmin_text_link", issue_id);
					
		//Verify Alert (Issues Assigned to Me. Awaiting Admin action) after Issue ID Closed
		fnVerify_ADMIN_Alert_redirect_from_summarydetailsPage("IssuesAssignedtoMeAwaitingAdminaction_text_link","IssuesAssignedtoMeAwaitingAdminaction_issue_id_filter", issue_id);
		fnVerify_NoRecordsFound_Msg_for_ID_When_AlertRemoved("IssuesAssignedtoMeAwaitingAdminaction_table_1st_cell","IssuesAssignedtoMeAwaitingAdminaction_text_link", issue_id);
		
		//Verify Alert (Issues Assigned to Me as Technologist) after Issue ID Closed
		fnVerify_TECH_Alert_redirect_from_AlertPage_by_SpecificUserRadioBttn("IssuesAssignedtoMeasTechnologist_text_link","IssuesAssignedtoMeasTechnologist_issue_id_filter", issue_id);
		fnVerify_NoRecordsFound_Msg_for_ID_When_AlertRemoved("IssuesAssignedtoMeasTechnologist_table_1st_cell","IssuesAssignedtoMeasTechnologist_text_link", issue_id);

		//Verify Alert (Issues Assigned to Me. Awaiting Technologist action) after Issue ID Closed
		fnVerify_TECH_Alert_by_clicking_ShowAlertBttn_from_TECHAlertPage("IssuesAssignedtoMeAwaitingTechnologistaction_text_link","IssuesAssignedtoMeAwaitingTechnologistaction_issue_id_filter", issue_id);
		fnVerify_NoRecordsFound_Msg_for_ID_When_AlertRemoved("IssuesAssignedtoMeAwaitingTechnologistaction_table_1st_cell","IssuesAssignedtoMeAwaitingTechnologistaction_text_link", issue_id);
		
		//Verify Alert (Issues Assigned to Me. Awaiting Supplier/Distributor action ) after Issue ID Closed
		fnVerify_TECH_Alert_by_clicking_ShowAlertBttn_from_TECHAlertPage("IssuesAssignedToMeAwaitingSupplierDistributorAction_text_link","IssuesAssignedToMeAwaitingSupplierDistributorAction_issue_id_filter", issue_id);
		fnVerify_NoRecordsFound_Msg_for_ID_When_AlertRemoved("IssuesAssignedToMeAwaitingSupplierDistributorAction_table_1st_cell","IssuesAssignedToMeAwaitingSupplierDistributorAction_text_link", issue_id);
		
		//Verify Alert (Issues Assigned to Me. Awaiting Client action) after Issue ID Closed
		fnVerify_TECH_Alert_by_clicking_ShowAlertBttn_from_TECHAlertPage("IssuesAssignedtoMeAwaitingClientaction_text_link","IssuesAssignedtoMeAwaitingClientaction_issue_id_filter", issue_id);
		fnVerify_NoRecordsFound_Msg_for_ID_When_AlertRemoved("IssuesAssignedtoMeAwaitingClientaction_table_1st_cell","IssuesAssignedtoMeAwaitingClientaction_text_link", issue_id);
	
	
		fnApps_Report_Logs("######################################### [BS - 01]  CreateIssue_PRQ Script END ####################################################### ");
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
	
	
