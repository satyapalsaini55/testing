package nsf.ecap.IssueMgt_Suite;

//import nsf.ecap.MonitorPlan_Suite.TestSuiteBase_MonitorPlan;
//
import nsf.ecap.util.*;
import org.openqa.selenium.By;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;

public class Escalation_PRQ_WithOutAlerts extends TestSuiteBase_IM{
	
	public static String issue_id_green="I0162109";
	public static String issue_id_amber="I0135388";
	public static String issue_id_red="I0135389";
	public static String Escalation_Id="E0022523";
	public static String validation_error_msg_text;
	public static String TitleShortDescriptionText="Esc_PRQ_WithoutAlerts[BS-19], D/T=";	
	
	public static boolean IsBrowserPresentAlready = false;
	public static boolean Technologist_CheckBox_Flag = false;
	
	public static String Fetched_Text = null;
	

	
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
	
	
	
	
	
	
	
	@Test( priority = 0, description="OracleQueryLaunch_ToClosed_OpenIssues_and_InActiveAlerts_Whose --- productCode(501214) and Client(Demo Customer)")
	public void OracleQueryLaunch_ToClosed_OpenIssues_and_InActiveAlerts() throws Throwable {
		fnApps_Report_Logs("##################################    [BS-19] Create Escalation PRQ Without Alerts Verify    ############################################");
		//Function Called to close all issues in database
		fnUpdateDB_to_Close_issues();
		
	}
	
	
	
	

@Test( priority = 1, dependsOnMethods={"OracleQueryLaunch_ToClosed_OpenIssues_and_InActiveAlerts"})
public void Launch_Browser_and_Successfully_Login_into_the_Application() throws Throwable{
	
	if (!IsBrowserPresentAlready) {
		IsBrowserPresentAlready = fnLaunchBrowserAndLogin();
		fnApps_Report_Logs("Browser is launched and ******** Successfully login into 'I-Pulse' Application.");
	}
}
	
	
	
	
	@Test(dependsOnMethods={"Launch_Browser_and_Successfully_Login_into_the_Application"}, priority = 2, description="  [Create Issue ID with Green Color]")
	public void Escalation_PRQ_FirstIssue_Green() throws Throwable{
		
			
		fnApps_Report_Logs("################### Test Case ::::::1 Escalation_PRQ_FirstIssue_Green ");
		
		
	//	  
//		IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//src//nsf//ecap//xls//IssueMgt_Suite.xlsx");
			
		//Clicking on CreateIssue Ajax Link
		fnCreateIssue_Ajax_Link_Click();
		
		//Site Details page data entered and clicking on next button
		fnVerify_SiteDetailsPage_Data_Entered_and_NavigateTo_SummaryDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_PRQ", 2, 2), IssueMgt_Suitexls.getCellData("Escalation_PRQ", 2, 3));
	
		//Summary Details page data entered and clicking on next button
		fnVerify_SummaryDetailsPage_Data_Entered_and_NavigateTo_CustomerDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_PRQ", 2, 6), IssueMgt_Suitexls.getCellData("Escalation_PRQ", 2, 7), IssueMgt_Suitexls.getCellData("Escalation_PRQ", 2, 8), fnGet_2Days_Past_date(), TitleShortDescriptionText);
	
		//Clicking on Customer  NEXT Button
		fnClick_UnTill_Navigate_to_NextPage("CreateIssue_Customer_next_bttn","CreateIssue_Customer_Name_TextField","CreateIssue_product_code_bttn");	
		
		//Product Details page data entered and clicking on next button
		fnVerify_ProductDetailsPage_Data_Entered_and_NavigateTo_DocumentsDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_PRQ", 2, 9));
		
	
		// Clicking on Document next button
		fnClick_UnTill_Navigate_to_NextPage("CreateIssue_document_next_bttn","CreateIssue_document_Add_Attachement","CreateIssue_confirmation_submit_bttn");		
		
		// Clicking on Confirmation Submit button
		fnGet_Element_Enabled("CreateIssue_confirmation_submit_bttn");
		fnWait_and_Click("CreateIssue_confirmation_submit_bttn");
		//Verify issue ID Generated and Color is as per Expected and Stored into String
		issue_id_green=fnVerified_IssueID_Generated_and_Color_Verification(1, "GREEN");
}
	
	

	
	
	
	@Test(dependsOnMethods={"Escalation_PRQ_FirstIssue_Green"}, priority = 3, description="  [Create Issue ID with Amber color]")
	public void Escalation_PRQ_SecondIssue_Amber() throws Throwable{
		
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::2 Escalation_PRQ_SecondIssue_Amber ");
	//	IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//src//nsf//ecap//xls//IssueMgt_Suite.xlsx");
		
		
		//Clicking on CreateIssue Ajax Link
		fnCreateIssue_Ajax_Link_Click();
		
		//Site Details page data entered and clicking on next button
		fnVerify_SiteDetailsPage_Data_Entered_and_NavigateTo_SummaryDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_PRQ", 2, 2), IssueMgt_Suitexls.getCellData("Escalation_PRQ", 2, 4));
	
		//Summary Details page data entered and clicking on next button
		fnVerify_SummaryDetailsPage_Data_Entered_and_NavigateTo_CustomerDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_PRQ", 2, 6), IssueMgt_Suitexls.getCellData("Escalation_PRQ", 2, 7), IssueMgt_Suitexls.getCellData("Escalation_PRQ", 2, 8), fnGet_2Days_Past_date(), TitleShortDescriptionText);
	


		//Clicking on Customer  NEXT Button
		fnClick_UnTill_Navigate_to_NextPage("CreateIssue_Customer_next_bttn","CreateIssue_Customer_Name_TextField","CreateIssue_product_code_bttn");	
		
		//Product Details page data entered and clicking on next button
		fnVerify_ProductDetailsPage_Data_Entered_and_NavigateTo_DocumentsDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_PRQ", 2, 9));
		
	
		// Clicking on Document next button
		fnClick_UnTill_Navigate_to_NextPage("CreateIssue_document_next_bttn","CreateIssue_document_Add_Attachement","CreateIssue_confirmation_submit_bttn");		
		
		
		// Clicking on Confirmation Submit button
		fnGet_Element_Enabled("CreateIssue_confirmation_submit_bttn");
		fnWait_and_Click("CreateIssue_confirmation_submit_bttn");
		
		
		//Verify issue ID Generated and Color is as per Expected and Stored into String
		issue_id_amber=fnVerified_IssueID_Generated_and_Color_Verification(2, "AMBER");
		
}
	

	
	
	
	
	@Test(dependsOnMethods={"Escalation_PRQ_SecondIssue_Amber"}, priority = 4, description="  [Create Issue ID with RED color]")
	public void Escalation_PRQ_ThirdIssue_Red() throws Throwable{
		
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::3 Escalation_PRQ_ThirdIssue_Red ");
	//	IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//src//nsf//ecap//xls//IssueMgt_Suite.xlsx");
		
		
		//Clicking on CreateIssue Ajax Link
		fnCreateIssue_Ajax_Link_Click();
		
		//Site Details page data entered and clicking on next button
		fnVerify_SiteDetailsPage_Data_Entered_and_NavigateTo_SummaryDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_PRQ", 2, 2), IssueMgt_Suitexls.getCellData("Escalation_PRQ", 2, 5));
	
		//Summary Details page data entered and clicking on next button
		fnVerify_SummaryDetailsPage_Data_Entered_and_NavigateTo_CustomerDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_PRQ", 2, 6), IssueMgt_Suitexls.getCellData("Escalation_PRQ", 2, 7), IssueMgt_Suitexls.getCellData("Escalation_PRQ", 2, 8), fnGet_2Days_Past_date(), TitleShortDescriptionText);
	


		//Clicking on Customer  NEXT Button
		fnClick_UnTill_Navigate_to_NextPage("CreateIssue_Customer_next_bttn","CreateIssue_Customer_Name_TextField","CreateIssue_product_code_bttn");	
		
		//Product Details page data entered and clicking on next button
		fnVerify_ProductDetailsPage_Data_Entered_and_NavigateTo_DocumentsDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_PRQ", 2, 9));
		
	
		// Clicking on Document next button
		fnClick_UnTill_Navigate_to_NextPage("CreateIssue_document_next_bttn","CreateIssue_document_Add_Attachement","CreateIssue_confirmation_submit_bttn");		
		
		// Clicking on Confirmation Submit button
		fnGet_Element_Enabled("CreateIssue_confirmation_submit_bttn");
		fnWait_and_Click("CreateIssue_confirmation_submit_bttn");
		
	
		
		//Verify issue ID Generated and Color is as per Expected and Stored into String
		issue_id_red=fnVerified_IssueID_Generated_and_Color_Verification(3, "RED");
		
}
	
	

	
	
	
			
	@Test(dependsOnMethods={"Escalation_PRQ_ThirdIssue_Red"}, priority = 5, description="  [Verify Escalations ID Generated with three issues.]")
	public void Escalation_PRQ_Generated_With_3_Issues() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::4 Escalation_PRQ_Generated_With_3_Issues ");
		
	//	IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//src//nsf//ecap//xls//IssueMgt_Suite.xlsx");
					
		//Verify Escalation Id generated and stored into String.
		try{
			fnGet_Element_Enabled("Escalation_id_link");
			Escalation_Id = fnGet_Field_TEXT("Escalation_id_link").trim();
			assert (Escalation_Id.contains("E")):"Escalations ID is not Generated >> "+Escalation_Id+"Please refer ScreenShot >> EscalationIdNotGenerated"+fnScreenShot_Date_format();	
		  	fnApps_Report_Logs("PASSED == Escalations ID has been generated === "+Escalation_Id);
		}catch(Throwable t){
			isTestCasePass = false;
			fnTake_Screen_Shot("EscalationIdNotGenerated");
			fnApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
			
		fnWait_and_Click("Escalation_id_link");
		
				

		//Waiting for Footer till page load
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
		
				
		
		//Verify 3 Newly generated issues exists into the table on Escalation details screen.
		fnGet_Element_Enabled("ViewEscalation_RelatedIssues_table");
		String related_issue_table=fnGet_Field_TEXT("ViewEscalation_RelatedIssues_table");
		try{
			assert ((related_issue_table.contains(issue_id_green)) && related_issue_table.contains(issue_id_amber) && related_issue_table.contains(issue_id_red)):"FAILED == Issues IDs [ "+"issue_id_green >> "+issue_id_green+"issue_id_amber >> "+issue_id_amber+"issue_id_red >> "+issue_id_red+ " ] are not Exists into the Related Issues Table."+"Please refer ScreenShot [ IssuesNotDisplay"+fnScreenShot_Date_format()+" ]";	
			fnApps_Report_Logs("PASSED == All issues ids exists into table"+"issue_id_green >> "+issue_id_green+"issue_id_amber >> "+issue_id_amber+"issue_id_red >> "+issue_id_red);
		}catch(Throwable t){
			isTestCasePass = false;
			fnTake_Screen_Shot("IssuesNotDisplay");
			fnApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));
		}

	}
	

	
	
	
	@Test(dependsOnMethods={"Escalation_PRQ_Generated_With_3_Issues"}, priority = 6, description="  [Go to Summary tab > Click 'Assign to me'. Click Save.]")
	public void Escalation_Verify_IssueSummaryDetailsUpdate_after_AssignToMeButtonClick_andAlso_RadioButton_Select_in_DetailsTab() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("###### Test Case ::::::5 Escalation_Verify_IssueSummaryDetailsUpdate_after_AssignToMeButtonClick_andAlso_RadioButton_Select_in_DetailsTab ");
		
	//	IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//src//nsf//ecap//xls//IssueMgt_Suite.xlsx");
		
		//Search IssueID and also navigate to ViewIssue Summary Details page 
		fncIssueID_SearchIssueID_and_Navigate_to_ViewIssueSummaryDetailsPageComes(issue_id_green, true);
		
		fncIssue_DetailsTAB_RadioButtonsSelect_and_Verify_SaveSuccessfully();
		
		//Clicking on Summary TAB on View issue page
		fnGet_Element_Enabled("ViewIssueSummaryDetails_summary_detail_tab");
		fnWait_and_Click("ViewIssueSummaryDetails_summary_detail_tab");
		
		// Clicking on Assign to me button
		fnGet_Element_Enabled("ViewIssueSummaryDetails_assign_to_me");
		fnWait_and_Click("ViewIssueSummaryDetails_assign_to_me");
		/**/fnsLoading_Progressingwait(3);
				
		// Clicking on save button
		fnGet_Element_Enabled("ViewIssueSummaryDetails_summary_details_save_button");
		fnWait_and_Click("ViewIssueSummaryDetails_summary_details_save_button");
		
			
		fnGet_Element_Enabled("validation_error_msg");
		fncVerify_DataUpdated_Successfully("validation_error_msg", "Summary TAB : AssignToMe");		
	}
		
	
	
	@Test(dependsOnMethods={"Escalation_Verify_IssueSummaryDetailsUpdate_after_AssignToMeButtonClick_andAlso_RadioButton_Select_in_DetailsTab"}, priority= 7, description="  [Change the status from INITIAL RECORD > ADDITIONAL INFO REQUIRED  >mention new status response>Click save.]")
	public void Escalation_Verify_FirstIssue_Change_status_InitialRecord_to_AdditionalInfoRequired() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::6 Escalation_Verify_FirstIssue_Change_status_InitialRecord_to_AdditionalInfoRequired ");
				
		fncIssue_VerifyChange_Status_on_SummaryDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_PRQ", 2, 13), issue_id_green);
		
		fnGet_Element_Enabled("validation_error_msg");
		Thread.sleep(1000);
		fnGet_Element_Enabled("validation_error_msg");
		fncVerify_DataUpdated_Successfully("validation_error_msg", "Summary TAB : Status change to 'AdditionalInfoRequired'");	
					
	}			
		
	
	
	
	@Test(dependsOnMethods={"Escalation_Verify_FirstIssue_Change_status_InitialRecord_to_AdditionalInfoRequired"}, priority= 8, description="  [Link the technologist name here -select radio button as NSF  and mention the Technologist name. Click save.]")
	public void Escalation_VerifyUpdateSuccessfully_FirstIssue_SelectRadioButtonAsNSF_MentionTechnologistName() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::7 Escalation_VerifyUpdateSuccessfully_FirstIssue_SelectRadioButtonAsNSF_MentionTechnologistName ");
		
		/*// Commented on 29.3.2017 as Kelvin Owen is not comin into the DD
		/fnGet_Element_Enabled("ViewIssueSummaryDetails_technologist_type_radio_bttn");
		Thread.sleep(1500);
		fnWait_and_Click("ViewIssueSummaryDetails_technologist_type_radio_bttn");
		Thread.sleep(3000);
		Thread.sleep(2000); // Due to DD Loading an Additional time has been added 10.1.2016
		
		//Select value from Technologist Name drop down
		fnDD_value_Select("ViewIssueSummaryDetails_technologist_name_dd_click", "ViewIssueSummaryDetails_technologist_name_dd_value", "li", IssueMgt_Suitexls.getCellData("Escalation_PRQ", 2, 12));
		/
		*/
		//Added on 29.3.2017 as Kelvin Owen is not comin into the DD and above code commented.
		fnc_ClickOn_TechnologistTypeRadioBttn_AndThen_Select_TechnologistNameFromDD("ViewIssueSummaryDetails_technologist_type_radio_bttn", "ViewIssueSummaryDetails_technologist_name_dd_click", "ViewIssueSummaryDetails_technologist_name_dd_value");
		
		// Clicking on save button
		fnGet_Element_Enabled("ViewIssueSummaryDetails_summary_details_save_button");
		fnWait_and_Click("ViewIssueSummaryDetails_summary_details_save_button");
		/**/fnsLoading_Progressingwait(3);
		
		fnGet_Element_Enabled("validation_error_msg");
		Thread.sleep(1000);
		fnGet_Element_Enabled("validation_error_msg");
		fncVerify_DataUpdated_Successfully("validation_error_msg", "Summary TAB : Status change to 'Select RadioButtonAsNSF and TechnologistName'");
			
	}
	
	
	
	
	@Test(dependsOnMethods={"Escalation_VerifyUpdateSuccessfully_FirstIssue_SelectRadioButtonAsNSF_MentionTechnologistName"}, priority= 9, description="  [Change the status from ADDITIONAL INFO REQUIRED  >  INITIAL RECORD . mention new status response>Click save.]")
	public void Escalation_Verify_FirstIssue_Change_status_AdditionalInfoRequired_to_InitialRecord_Successfully() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::8 Escalation_Verify_FirstIssue_Change_status_AdditionalInfoRequired_to_InitialRecord_Successfully ");
		
		fncIssue_VerifyChange_Status_on_SummaryDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_PRQ", 2, 14), issue_id_green);
		
		fnGet_Element_Enabled("validation_error_msg");
		Thread.sleep(1000);
		fnGet_Element_Enabled("validation_error_msg");
		fncVerify_DataUpdated_Successfully("validation_error_msg", "Summary TAB : Status change to 'InitialRecord'");
		
	}	
	

	
	// Pending SelectAssigntoTechnologistCheckBox
	@Test(dependsOnMethods={"Escalation_Verify_FirstIssue_Change_status_AdditionalInfoRequired_to_InitialRecord_Successfully"}, priority= 10, description="  [Change the status from INITIAL RECORD > DETAILS SENT TO SUPPLIER .mention new status response>Click save. Check - assign to Technologist]")
	public void Escalation_Verify_FirstIssue_Change_status_InitialRecord_to_DetailsSentToSupplier_SelectAssigntoTechnologistCheckBox_Successfully() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("######## Test Case :::9 Escalation_Verify_FirstIssue_Change_status_InitialRecord_to_DetailsSentToSupplier_SelectAssigntoTechnologistCheckBox_Successfully ");
		
		Thread.sleep(1000);
				
		// Select Technologist Type by clicking on radio button
		fnGet_Element_Enabled("ViewIssueSummaryDetails_assign_to_technologist");
		fnWait_and_Click("ViewIssueSummaryDetails_assign_to_technologist");
		Thread.sleep(2000);
		
		//Change value from NEW STATUS drop down
		fnDD_value_Select("ViewIssueSummaryDetails_new_status_dd_click", "ViewIssueSummaryDetails_new_status_dd_value", "li", IssueMgt_Suitexls.getCellData("Escalation_PRQ", 2, 16));
		
		//Waiting for Astric(*) Image to display
		fnGet_Element_Enabled("ViewIssueSummaryDetails_new_status_response_Astric_Image");	
		
		// Enter free text value into New Status Response field		
		fnGet_Element_Enabled("ViewIssueSummaryDetails_new_status_response_textfield");	
		fnWait_and_Type("ViewIssueSummaryDetails_new_status_response_textfield", "Edit Issue, Changing Status from (Initial Record) to (Details Sent To Supplier).");
		
		
		
		// Clicking on save button
		fnGet_Element_Enabled("ViewIssueSummaryDetails_summary_details_save_button");
		fnWait_and_Click("ViewIssueSummaryDetails_summary_details_save_button");
		/**/fnsLoading_Progressingwait(3);
		
		fnGet_Element_Enabled("validation_error_msg");
		Thread.sleep(1000);
		fnGet_Element_Enabled("validation_error_msg");
		fncVerify_DataUpdated_Successfully("validation_error_msg", "Summary TAB : Status change to 'DetailsSentToSupplier'");
		
	}
	
	
	
	

	@Test(dependsOnMethods={"Escalation_Verify_FirstIssue_Change_status_InitialRecord_to_DetailsSentToSupplier_SelectAssigntoTechnologistCheckBox_Successfully"}, priority= 11, description="  [Change the status from  DETAILS SENT TO SUPPLIER >ISSUE RESPONSE .mention new status response>Click save.]")
	public void Escalation_Verify_FirstIssue_Change_status_DetailsSentToSupplier_to_IssueResponse_Successfully() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::10 Escalation_Verify_FirstIssue_Change_status_DetailsSentToSupplier_to_IssueResponse_Successfully ");
		
		fncIssue_VerifyChange_Status_on_SummaryDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_PRQ", 2, 17), issue_id_green);
		
		fnGet_Element_Enabled("validation_error_msg");
		Thread.sleep(1000);
		fnGet_Element_Enabled("validation_error_msg");
		fncVerify_DataUpdated_Successfully("validation_error_msg", "Summary TAB : Status change to 'IssueResponse'");
	}
	

		
	
	
	
	@Test(dependsOnMethods={"Escalation_Verify_FirstIssue_Change_status_DetailsSentToSupplier_to_IssueResponse_Successfully"}, priority= 12, description="  [Change the status from  ISSUE RESPONSE >TECHNOLOGIST approved to close. Mention new status response>Click save.]")
	public void Escalation_Verify_FirstIssue_Change_status_IssueResponse_to_TechnologistApprovedToClose_Successfully() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::11 Escalation_Verify_FirstIssue_Change_status_IssueResponse_to_TechnologistApprovedToClose_Successfully ");
		
		fncIssue_VerifyChange_Status_on_SummaryDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_PRQ", 2, 18), issue_id_green);
		
		fnGet_Element_Enabled("validation_error_msg");
		Thread.sleep(1000);
		fnGet_Element_Enabled("validation_error_msg");
		fncVerify_DataUpdated_Successfully("validation_error_msg", "Summary TAB : Status change to 'TechnologistApprovedToClose'");		
		Thread.sleep(2000);
		
		try{
			assert !(fnGet_OR_IM_ObjectX("ViewIssueSummaryDetails_assign_to_technologist").isSelected()):"AssignToTechnologist checkbox is selected after changing status to Technologist Approved to Close, please refer screenshot >> CheckBoxIsSelected";
			fnApps_Report_Logs("PASSED == 'Assign To Technologist' checkBox is getting unchecked after changing status to TechnologistApprovedToClose.");		
		}catch(Throwable t){
			isTestCasePass = false;
			fnTake_Screen_Shot("CheckBoxIsSelected");
			fnApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		
		
	}
	

	
	
	
	
	
	
	
	
	
	
	
	@Test(dependsOnMethods={"Escalation_Verify_FirstIssue_Change_status_IssueResponse_to_TechnologistApprovedToClose_Successfully"}, priority= 13, description="  [Change the status from >TECHNOLOGIST approved to close- > SATISFACTORY CLOSURE. Mention new status response>Click save.]")
	public void Escalation_FirstIssue_Change_status_TechnologistApprovedToClose_to_SatisfactoryClosure_andThen_Alerts_Verify() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("########### Test Case :::12 Escalation_FirstIssue_Change_status_TechnologistApprovedToClose_to_SatisfactoryClosure_andThen_Alerts_Verify ");
		
		fncIssue_VerifyChange_Status_on_SummaryDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_PRQ", 2, 19), issue_id_green);
		
		fnGet_Element_Enabled("validation_error_msg");
		Thread.sleep(1000);
		fnGet_Element_Enabled("validation_error_msg");
		fncVerify_DataUpdated_Successfully("validation_error_msg", "Summary TAB : Status change to 'TechnologistApprovedToClose'");		
	}

	
		
	
	
	
	
	
	@Test(dependsOnMethods={"Escalation_FirstIssue_Change_status_TechnologistApprovedToClose_to_SatisfactoryClosure_andThen_Alerts_Verify"}, priority = 14, description="  [Edit the Escalation and click Save. Click -> Assign to Me button.]")
	public void EditEscalation_Verify_AssignToMeButtonClick_and_UpdateSuccessfully() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::14 EditEscalation_Verify_AssignToMeButtonClick_and_UpdateSuccessfully ");
		
		//Search Escalation and also navigate to ViewEscalation EscalationDetails page
		fncEscalation_SearchEscalation_and_Navigate_till_ViewEscalationDetailsPageComes(Escalation_Id);
		
		//Clicking on assign_to_me button
		fnGet_Element_Enabled("Escalation_Summary_assign_to_me_button");
		fnWait_and_Click("Escalation_Summary_assign_to_me_button");
		/**/fnsLoading_Progressingwait(3);
		
		fnGet_Element_Enabled("Escalation_summary_details_save_button");
		fnWait_and_Click("Escalation_summary_details_save_button");
		/**/fnsLoading_Progressingwait(3);

		fnGet_Element_Enabled("validation_error_msg");
		Thread.sleep(1000);
		fnGet_Element_Enabled("validation_error_msg");
		fncVerify_DataUpdated_Successfully("validation_error_msg", "View Escalation");	
		
	}
	
	
	
	
	
	@Test(dependsOnMethods={"EditEscalation_Verify_AssignToMeButtonClick_and_UpdateSuccessfully"}, priority = 15, description="  [Link the technologist name .select radio button as NSF  and mention the Technologist name. Click save.]")
	public void EditEscalation_Verify_SelectRadioButtonAsNSF_MentionTechnologistName_and_UpdateSuccessfully() throws Throwable{
	
		fnApps_Report_Logs("=========================================================================================================================================");	
		fnApps_Report_Logs("################### Test Case ::::::15 EditEscalation_Verify_SelectRadioButtonAsNSF_MentionTechnologistName_and_UpdateSuccessfully ");
				
		/*// Select Technologist Type by clicking on radio button
		/fnGet_Element_Enabled("Escalation_Summary_TechnologistType_RadioButton");
		Thread.sleep(1500);
		fnWait_and_Click("Escalation_Summary_TechnologistType_RadioButton");
		
		Thread.sleep(1500);		
		
		Thread.sleep(3000); // Due to DD Loading an Additional time has been added 10.1.2016
		//Select value from Technologist Name drop down
		fnDD_value_Select("Escalation_Summary_TechnologistName_DD_Click", "Escalation_Summary_TechnologistName_DD_Value", "li", IssueMgt_Suitexls.getCellData("Escalation_PRQ", 2, 20));
		/*/
		
		//Added on 29.3.2017 as Kelvin Owen is not comin into the DD and above code commented.
		fnc_ClickOn_TechnologistTypeRadioBttn_AndThen_Select_TechnologistNameFromDD("Escalation_Summary_TechnologistType_RadioButton", "Escalation_Summary_TechnologistName_DD_Click", "Escalation_Summary_TechnologistName_DD_Value");
				
		
		
		fnGet_Element_Enabled("Escalation_summary_details_save_button");
		fnWait_and_Click("Escalation_summary_details_save_button");
		/**/fnsLoading_Progressingwait(3);

		fnGet_Element_Enabled("validation_error_msg");
		Thread.sleep(1000);
		fnGet_Element_Enabled("validation_error_msg");
		fncVerify_DataUpdated_Successfully("validation_error_msg", "View Escalation : 'SelectRadioButtonAsNSF' and 'MentionTechnologistName'");	
		
	}
	

	
	
	@Test(dependsOnMethods={"EditEscalation_Verify_SelectRadioButtonAsNSF_MentionTechnologistName_and_UpdateSuccessfully"}, priority = 16, description="  [Change the status from INITIAL RECORD > DETAILS SENT TO SUPPLIER .mention new status response>Click save.]")
	public void EditEscalation_Verify_Change_status_InitialRecord_to_DetailsSentToSupplier_and_UpdateSuccessfully() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::16 EditEscalation_Verify_Change_status_InitialRecord_to_DetailsSentToSupplier_and_UpdateSuccessfully ");
			
		
		Technologist_CheckBox_Flag = true;
		
		fncEscalation_VerifyChange_Status_on_SummaryDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_PRQ", 2, 23));
		
		fnGet_Element_Enabled("validation_error_msg");
		Thread.sleep(1000);
		fnGet_Element_Enabled("validation_error_msg");
		fncVerify_DataUpdated_Successfully("validation_error_msg", "View Escalation : Status change to 'DetailsSentToSupplier'");	
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Test(dependsOnMethods={"EditEscalation_Verify_Change_status_InitialRecord_to_DetailsSentToSupplier_and_UpdateSuccessfully"}, priority = 17, description="  [Change the status from DETAILS SENT TO SUPPLIER to ESCALATION Response .mention new status response>Click save.]")
	public void EditEscalation_Verify_Change_status_DetailsSentToSupplier_EscalationResponse_and_UpdateSuccessfully() throws Throwable{
	
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::17 EditEscalation_Verify_Change_status_DetailsSentToSupplier_EscalationResponse_and_UpdateSuccessfully ");
		
		fncEscalation_VerifyChange_Status_on_SummaryDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_PRQ", 2, 24));
		
		fnGet_Element_Enabled("validation_error_msg");
		Thread.sleep(1000);
		fnGet_Element_Enabled("validation_error_msg");
		fncVerify_DataUpdated_Successfully("validation_error_msg", "View Escalation : Status change to 'EscalationResponse'");	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Test(dependsOnMethods={"EditEscalation_Verify_Change_status_DetailsSentToSupplier_EscalationResponse_and_UpdateSuccessfully"}, priority = 18, description="  [Change the status from ESCALATION Response ->Technology approved. Mention new status response>Click save.]")
	public void EditEscalation_Verify_Change_status_EscalationResponse_TechnologistApprovedToClose_and_UpdateSuccessfully() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("########### Test Case ::::18 EditEscalation_Verify_Change_status_EscalationResponse_TechnologistApprovedToClose_and_UpdateSuccessfully");
				
		fncEscalation_VerifyChange_Status_on_SummaryDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_PRQ", 2, 25));
		
		fnGet_Element_Enabled("validation_error_msg");
		Thread.sleep(1000);
		fnGet_Element_Enabled("validation_error_msg");
		fncVerify_DataUpdated_Successfully("validation_error_msg", "View Escalation : Status change to 'TechnologistApprovedToClose'");	
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Test(dependsOnMethods={"EditEscalation_Verify_Change_status_EscalationResponse_TechnologistApprovedToClose_and_UpdateSuccessfully"}, priority = 19, description="  [Change the status from >Technology approved >SATISFACTORY CLOSURE. Mention new status response>Click save. Check -Assign to technologist]")
	public void EditEscalation_Verify_Change_status_TechnologistApprovedToClose_to_SatisfactoryClosure_and_UpdateSuccessfully() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("####### Test Case ::::19 EditEscalation_Verify_Change_status_TechnologistApprovedToClose_to_SatisfactoryClosure_and_UpdateSuccessfully");

		Technologist_CheckBox_Flag = true;
		
		fncEscalation_VerifyChange_Status_on_SummaryDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_PRQ", 2, 26));
		
		fnGet_Element_Enabled("validation_error_msg");
		Thread.sleep(1000);
		fnGet_Element_Enabled("validation_error_msg");
		fncVerify_DataUpdated_Successfully("validation_error_msg", "View Escalation : Status change to 'SatisfactoryClosure'");		
	}
	

	
	
	
	
	
	
	
	
	
	

	@Test(dependsOnMethods={"EditEscalation_Verify_Change_status_TechnologistApprovedToClose_to_SatisfactoryClosure_and_UpdateSuccessfully"}, priority = 20, description="  [Verify that all issues are closed which are related to Escalation as the escalation is closed.]")
	public void EditEscalation_Verify_AllIssuesClosed_As_EscalationClosed() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::20 EditEscalation_Verify_AllIssuesClosed_As_EscalationClosed ");
		
		fnSearchIssue_Ajax_Link_Click();
		fnGet_Element_Enabled("footer");
		/**/fnsLoading_Progressingwait(1);
		
		//Verify First Issue ID Closed
		fncEscalation_Verify_First_IssueClosed_after_EscalationClosed("SearchIssue_Parent_Status",issue_id_green, "CLOSED" , false);
				
		//Verify Second Issue ID Closed
		fncEscalation_Verify_First_IssueClosed_after_EscalationClosed("SearchIssue_Parent_Status",issue_id_amber, "CLOSED", false);
				
		//Verify Third Issue ID Closed	
		fncEscalation_Verify_First_IssueClosed_after_EscalationClosed("SearchIssue_Parent_Status",issue_id_red, "CLOSED", false);
		
		
		fnApps_Report_Logs("################################ [BS-19] Create Escalation PRQ Without Alerts Verify Script END ######################################## ");
		fnApps_Report_Logs("=========================================================================================================================================");
		
	}
	
	
	
	
//##########################################################################################################################################################################	
//################################################################## Class Functions ########################################################################################	


//##########################################################################################################################################################################	
//################################################################## Configuration Functions ########################################################################################		
	@AfterTest
	public void reportTestResult() throws Throwable {
		/**/fns_ReportTestResult_atClassLevel(IssueMgt_Suitexls,  (this.getClass().getSimpleName()) );
	}
	

	@AfterTest
	public void Test_success() throws Throwable{
		driver.quit();
	}
	
	
}	
	
	
	
	
	
	
	
