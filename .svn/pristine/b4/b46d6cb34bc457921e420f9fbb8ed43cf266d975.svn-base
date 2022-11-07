package nsf.ecap.IssueMgt_Suite;

import java.util.HashMap;
import java.util.Properties;


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

public class CloseEscalation_Messages extends TestSuiteBase_IM{
	
	public static String issue_id_green="I0060389";
	public static String issue_id_amber="I0060390";
	public static String issue_id_red="I0060391";
	public static String Escalation_Id="E0005763";
	public static String validation_error_msg_text;
	public static String TitleShortDescriptionText="CloseEsc_Msg[BS-16], D/T=";
	public static String NEW_Escalation_Id="E0005761";
		
	public static boolean IsBrowserPresentAlready = false;
		
	

	
	@Parameters({ "className" })
	@BeforeTest
		public void checkTestSkip(String className) throws Exception {
		isTestCasePass=true;
		
		
		
		try {
		
			if (className.isEmpty()) {
				className = this.getClass().getSimpleName();			}
			
			classNameText=className;
			currentScriptCode=fnpMappingClassNameWithScenarioCode(classNameText).trim();

			if (!TestUtil.isTestCaseRunnable(IssueMgt_Suitexls, className)) {
				fnApps_Report_Logs("Skipping Test Case " + className + " as runmode set to NO");
				throw new SkipException("Skipping Test Case " + className + " as runmode set to NO");}
		
			fnApps_Report_Logs("=========================================================================================================================================");
		
		}catch(SkipException sk){
			String stackTrace = Throwables.getStackTraceAsString(sk);
			String errorMsg = sk.getMessage();
			Exception c = new Exception(errorMsg);
			ErrorUtil.addVerificationFailure(c);
			throw new SkipException(errorMsg + " \n\nSo,Skipping Test Case" + className);}
		
		catch (Throwable t) {
				String stackTrace = Throwables.getStackTraceAsString(t);
				String errorMsg = Throwables.getStackTraceAsString(t);
				errorMsg = errorMsg + "\n\n\n\nSee screenshot '" + className + "'\n\n" + stackTrace;
				Exception c = new Exception(errorMsg);
				ErrorUtil.addVerificationFailure(c);
				throw new Exception(errorMsg );}
		
}
	
	
	
	
	
	
	
	@BeforeClass (description="OracleQueryLaunch_ToClosed_OpenIssues_and_InActiveAlerts_Whose --- productCode(501214), Issue_Type(PRQ) and Client(NSF-CMiRetailTestingClient")
	public void OracleQueryLaunch_ToClosed_OpenIssues_and_InActiveAlerts() throws Throwable {

		//Function Called to close all issues in database
		fnUpdateDB_to_Close_issues();
		
	}
	
	

@Test( priority = 0)
public void Launch_Browser_and_Successfully_Login_into_the_Application() throws Throwable{
	fnApps_Report_Logs("################################## [BS-16]  Close Escalation Messages");
	if (!IsBrowserPresentAlready) {
		IsBrowserPresentAlready = fnLaunchBrowserAndLogin();
		fnApps_Report_Logs("Browser is launched and ******** Successfully login into 'I-Pulse' Application.");
	}
}
		
	

	
	@Test(dependsOnMethods={"Launch_Browser_and_Successfully_Login_into_the_Application"}, priority = 1, description="  [Create Issue ID with Green Color]")
	public void CloseEscalationMsg_FirstIssue_Green() throws Throwable{
		
			
		fnApps_Report_Logs("################### Test Case ::::::1 CloseEscalationMsg_FirstIssue_Green ");
		
		
		//  
	//	IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//src//nsf//ecap//xls//IssueMgt_Suite.xlsx");
			
		//Clicking on CreateIssue Ajax Link
		fnCreateIssue_Ajax_Link_Click();
		
		//Site Details page data entered and clicking on next button
		fnVerify_SiteDetailsPage_Data_Entered_and_NavigateTo_SummaryDetailsPage(IssueMgt_Suitexls.getCellData("CloseEscalation_Msg", 2, 2), IssueMgt_Suitexls.getCellData("CloseEscalation_Msg", 2, 3));
	
		//Summary Details page data entered and clicking on next button
		fnVerify_SummaryDetailsPage_Data_Entered_and_NavigateTo_CustomerDetailsPage(IssueMgt_Suitexls.getCellData("CloseEscalation_Msg", 2, 6), IssueMgt_Suitexls.getCellData("CloseEscalation_Msg", 2, 7), IssueMgt_Suitexls.getCellData("CloseEscalation_Msg", 2, 8), fnGet_2Days_Past_date(), TitleShortDescriptionText);
	
		//Clicking on Customer  NEXT Button
		fnClick_UnTill_Navigate_to_NextPage("CreateIssue_Customer_next_bttn","CreateIssue_Customer_Name_TextField","CreateIssue_product_code_bttn");	
		
		//Product Details page data entered and clicking on next button
		fnVerify_ProductDetailsPage_Data_Entered_and_NavigateTo_DocumentsDetailsPage(IssueMgt_Suitexls.getCellData("CloseEscalation_Msg", 2, 9));
		
	
		// Clicking on Document next button
		fnClick_UnTill_Navigate_to_NextPage("CreateIssue_document_next_bttn","CreateIssue_document_Add_Attachement","CreateIssue_confirmation_submit_bttn");		
		
		// Clicking on Confirmation Submit button
		fnWait_and_Click("CreateIssue_confirmation_submit_bttn");
		
		//Verify issue ID Generated and Color is as per Expected and Stored into String
		issue_id_green=fnVerified_IssueID_Generated_and_Color_Verification(1, "GREEN");
}
	
	
	
	
	
	
	
	
	
	@Test(dependsOnMethods={"CloseEscalationMsg_FirstIssue_Green"}, priority = 2, description="  [Create Issue ID with Amber color]")
	public void CloseEscalationMsg_SecondIssue_Amber() throws Throwable{
		
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::2 CloseEscalationMsg_SecondIssue_Amber ");
	//	IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//src//nsf//ecap//xls//IssueMgt_Suite.xlsx");
		
		
		//Clicking on CreateIssue Ajax Link
		fnCreateIssue_Ajax_Link_Click();
		
		//Site Details page data entered and clicking on next button
		fnVerify_SiteDetailsPage_Data_Entered_and_NavigateTo_SummaryDetailsPage(IssueMgt_Suitexls.getCellData("CloseEscalation_Msg", 2, 2), IssueMgt_Suitexls.getCellData("CloseEscalation_Msg", 2, 4));
	
		//Summary Details page data entered and clicking on next button
		fnVerify_SummaryDetailsPage_Data_Entered_and_NavigateTo_CustomerDetailsPage(IssueMgt_Suitexls.getCellData("CloseEscalation_Msg", 2, 6), IssueMgt_Suitexls.getCellData("CloseEscalation_Msg", 2, 7), IssueMgt_Suitexls.getCellData("CloseEscalation_Msg", 2, 8), fnGet_2Days_Past_date(), TitleShortDescriptionText);
	


		//Clicking on Customer  NEXT Button
		fnClick_UnTill_Navigate_to_NextPage("CreateIssue_Customer_next_bttn","CreateIssue_Customer_Name_TextField","CreateIssue_product_code_bttn");	
		
		//Product Details page data entered and clicking on next button
		fnVerify_ProductDetailsPage_Data_Entered_and_NavigateTo_DocumentsDetailsPage(IssueMgt_Suitexls.getCellData("CloseEscalation_Msg", 2, 9));
		
	
		// Clicking on Document next button
		fnClick_UnTill_Navigate_to_NextPage("CreateIssue_document_next_bttn","CreateIssue_document_Add_Attachement","CreateIssue_confirmation_submit_bttn");		
		
		
		// Clicking on Confirmation Submit button
		fnWait_and_Click("CreateIssue_confirmation_submit_bttn");
		
		
		//Verify issue ID Generated and Color is as per Expected and Stored into String
		issue_id_amber=fnVerified_IssueID_Generated_and_Color_Verification(2, "AMBER");
		
}
	
	
	
	
	
	
	
	
	
	@Test(dependsOnMethods={"CloseEscalationMsg_SecondIssue_Amber"}, priority = 3, description="  [Create Issue ID with RED color]")
	public void CloseEscalationMsg_ThirdIssue_Red() throws Throwable{
		
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::3 CloseEscalationMsg_ThirdIssue_Red ");
//		IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//src//nsf//ecap//xls//IssueMgt_Suite.xlsx");
		
		
		//Clicking on CreateIssue Ajax Link
		fnCreateIssue_Ajax_Link_Click();
		
		//Site Details page data entered and clicking on next button
		fnVerify_SiteDetailsPage_Data_Entered_and_NavigateTo_SummaryDetailsPage(IssueMgt_Suitexls.getCellData("CloseEscalation_Msg", 2, 2), IssueMgt_Suitexls.getCellData("CloseEscalation_Msg", 2, 5));
	
		//Summary Details page data entered and clicking on next button
		fnVerify_SummaryDetailsPage_Data_Entered_and_NavigateTo_CustomerDetailsPage(IssueMgt_Suitexls.getCellData("CloseEscalation_Msg", 2, 6), IssueMgt_Suitexls.getCellData("CloseEscalation_Msg", 2, 7), IssueMgt_Suitexls.getCellData("CloseEscalation_Msg", 2, 8), fnGet_2Days_Past_date(), TitleShortDescriptionText);
	


		//Clicking on Customer  NEXT Button
		fnClick_UnTill_Navigate_to_NextPage("CreateIssue_Customer_next_bttn","CreateIssue_Customer_Name_TextField","CreateIssue_product_code_bttn");	
		
		//Product Details page data entered and clicking on next button
		fnVerify_ProductDetailsPage_Data_Entered_and_NavigateTo_DocumentsDetailsPage(IssueMgt_Suitexls.getCellData("CloseEscalation_Msg", 2, 9));
		
	
		// Clicking on Document next button
		fnClick_UnTill_Navigate_to_NextPage("CreateIssue_document_next_bttn","CreateIssue_document_Add_Attachement","CreateIssue_confirmation_submit_bttn");		
		
		// Clicking on Confirmation Submit button
		fnWait_and_Click("CreateIssue_confirmation_submit_bttn");
		
	
		
		//Verify issue ID Generated and Color is as per Expected and Stored into String
		issue_id_red=fnVerified_IssueID_Generated_and_Color_Verification(3, "RED");
		
}
	
	
	
	
	
	
	
	
	
	
	
	
	
			
	@Test(dependsOnMethods={"CloseEscalationMsg_ThirdIssue_Red"}, priority = 4, description="  [Verify Escalations ID Generated with three issues.]")
	public void CloseEscalationMsg_Generated_With_3_Issues() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::4 CloseEscalationMsg_Generated_With_3_Issues ");
	
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
				assert ((related_issue_table.contains(issue_id_green)) && related_issue_table.contains(issue_id_amber) && related_issue_table.contains(issue_id_red) ):
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
	
	
	
	
		
	
	
	
	
	
	
	
	

	@Test(dependsOnMethods={"CloseEscalationMsg_Generated_With_3_Issues"}, priority = 5, description="  [Close the first issue and  then edit this issue to change the sub-type. Verify that the escalation is dismantled.]")
	public void CloseEscalationMsg_CloseFirstIssue_andthen_ChangeIssueSubType_Verify_Escalation_Dismantled_and_IssueColors() throws Throwable{
		
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("############ Test Case :::::5 CloseEscalationMsg_CloseFirstIssue_andthen_ChangeIssueSubType_Verify_Escalation_Dismantled_and_IssueColors.");
		
	//	IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//src//nsf//ecap//xls//IssueMgt_Suite.xlsx");
		
		
		

					//Change First issue Status To Close.

		//Search IssueID and also navigate to ViewIssue Summary Details page 
		fncIssueID_SearchIssueID_and_Navigate_to_ViewIssueSummaryDetailsPageComes(issue_id_green, true);
		fncIssue_DetailsTAB_RadioButtonsSelect_and_Verify_SaveSuccessfully();
		//Clicking on Summary TAB on View issue page
		fnGet_Element_Enabled("ViewIssueSummaryDetails_summary_detail_tab");
		fnWait_and_Click("ViewIssueSummaryDetails_summary_detail_tab");
		
		
		fnGet_Element_Enabled("ViewIssueSummaryDetails_assign_to_me");
		fnWait_and_Click("ViewIssueSummaryDetails_assign_to_me");
		fnsLoading_Progressingwait(3);
		
		
		//Select Status From Drop Down
		fnGet_Element_Enabled("ViewIssueSummaryDetails_new_status_dd_click");
		fnDD_value_Select("ViewIssueSummaryDetails_new_status_dd_click", "ViewIssueSummaryDetails_new_status_dd_value", "li", IssueMgt_Suitexls.getCellData("CloseEscalation_Msg", 2, 12));
		
		//Waiting for Astric(*) Image to display
		fnGet_Element_Enabled("ViewIssueSummaryDetails_new_status_response_Astric_Image");	
		
		// Enter free text value into New Status Response field		
		fnGet_Element_Enabled("ViewIssueSummaryDetails_new_status_response_textfield");
		fnWait_and_Type("ViewIssueSummaryDetails_new_status_response_textfield", "Edit Issue, Changing Status from (Initial Record) to (Satisfactory Closure)." );
		
		// Clicking on save button
		fnGet_Element_Enabled("ViewIssueSummaryDetails_summary_details_save_button");
		fnWait_and_Click("ViewIssueSummaryDetails_summary_details_save_button");
		fnsLoading_Progressingwait(3);
		
			
		fnGet_Element_Enabled("validation_error_msg");
		fncVerify_DataUpdated_Successfully("validation_error_msg", "Summary TAB : AssignToMe");	

		
		
					//Change Issue Subtype and verify issue color and escalation dismantled
	
		//Select Status From Drop Down
		fnGet_Element_Enabled("ViewIssueSummaryDetails_IssueSubType_dd_click");
		fnDD_value_Select("ViewIssueSummaryDetails_IssueSubType_dd_click", "ViewIssueSummaryDetails_IssueSubType_dd_value", "li", IssueMgt_Suitexls.getCellData("CloseEscalation_Msg", 2, 14));
			
		fnGet_Element_Enabled("ViewIssueSummaryDetails_summary_details_save_button");
		fnWait_and_Click("ViewIssueSummaryDetails_summary_details_save_button");
		fnsLoading_Progressingwait(3);
		
			
		fnGet_Element_Enabled("validation_error_msg");
		fncVerify_DataUpdated_Successfully("validation_error_msg", "Summary TAB");	

				
				
		//Verify issue ID Generated and Color is as per Expected
		fnVerified_IssueID_Generated_and_Color_Verification(1, "GREEN");
				
	
		
		//Search Escalation and also navigate to ViewEscalation EscalationDetails page
		fncEscalation_SearchEscalation_and_Navigate_till_ViewEscalationDetailsPageComes(Escalation_Id);
				
		//Waiting for Footer till page load
		fnGet_Element_Enabled("footer");
		
		
		try{	
				
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
					
			

			String NoRecordFound_column_text=fnGet_Field_TEXT("ViewEscalation_RelatedIssues_table_NoRecordsfound").trim();
			
			assert NoRecordFound_column_text.contains("records"):"FAILED == Escalation has not been dismantled and still there issue ids are displayed into Related Issues table on View Escalation Page , please refer screenshot >> ErrorMsgNotCominginRelatedIssuesTable"+fnScreenShot_Date_format();
			fnApps_Report_Logs("PASSED == Escalation has been dismantled and there are no issue ids displayed into Related Issues table on View Escalation Page.");
								
	}catch(Throwable t) {
		isTestCasePass = false;
		fnTake_Screen_Shot("ErrorMsgNotCominginRelatedIssuesTable");
		fnApps_Report_Logs(Throwables.getStackTraceAsString(t).trim());
		throw new Exception(Throwables.getStackTraceAsString(t).trim());		
		}
	

	fnSearchIssue_Ajax_Link_Click();
	fnGet_Element_Enabled("footer");
	fnsLoading_Progressingwait(1);

	fncEscalation_Verify_First_IssueClosed_after_EscalationClosed("SearchIssue_Escalation_Status",issue_id_amber, "AMBER" , false);
	fncEscalation_Verify_First_IssueClosed_after_EscalationClosed("SearchIssue_Escalation_Status",issue_id_red, "AMBER" , false);
		

	
	
}	
		
	
	
	
	
	
	
	
	
	
	


	@Test(dependsOnMethods={"CloseEscalationMsg_CloseFirstIssue_andthen_ChangeIssueSubType_Verify_Escalation_Dismantled_and_IssueColors"}, priority = 6, description="  [Edit the same first issue again to re-open it and put the sub-type back to its original value to re-generate the escalation..]")
	public void CloseEscalationMsg_EditFirstIssueAgain_ChangeIssueSubType_BacktoOriginal_Verify_NewEscalationId() throws Throwable{
		
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::6 CloseEscalationMsg_EditFirstIssueAgain_ChangeIssueSubType_BacktoOriginal_Verify_NewEscalationId.");
	//	IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//src//nsf//ecap//xls//IssueMgt_Suite.xlsx");
		WebDriverWait waitvar = new WebDriverWait(driver, Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
		
			
					//Change Issue Subtype and verify issue color and escalation dismantled
	try{
		//Search IssueID and also navigate to ViewIssue Summary Details page 
		fncIssueID_SearchIssueID_and_Navigate_to_ViewIssueSummaryDetailsPageComes(issue_id_green, true);
		
		//Clicking on Summary TAB on View issue page
		fnGet_Element_Enabled("ViewIssueSummaryDetails_summary_detail_tab");
		fnWait_and_Click("ViewIssueSummaryDetails_summary_detail_tab");
			
		//Select Status From Drop Down
		fnGet_Element_Enabled("ViewIssueSummaryDetails_IssueSubType_dd_click");
		fnDD_value_Select("ViewIssueSummaryDetails_IssueSubType_dd_click", "ViewIssueSummaryDetails_IssueSubType_dd_value", "li", IssueMgt_Suitexls.getCellData("CloseEscalation_Msg", 2, 8));
			
		fnGet_Element_Enabled("ViewIssueSummaryDetails_summary_details_save_button");
		fnWait_and_Click("ViewIssueSummaryDetails_summary_details_save_button");
		fnsLoading_Progressingwait(3);
		
			
		fnGet_Element_Enabled("validation_error_msg");
		fncVerify_DataUpdated_Successfully("validation_error_msg", "Summary TAB");	
				
		//Verify issue ID Generated and Color is as per Expected
		fnVerified_IssueID_Generated_and_Color_Verification(1, "RED");
				
	
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
			assert ((related_issue_table.contains(issue_id_green)) && related_issue_table.contains(issue_id_amber) && related_issue_table.contains(issue_id_red)   ):
				"FAILED == Issues IDs are not Exists into the Related Issues Table."+"Please refer ScreenShot [ NEW_Escalation_Id_NotGenrated"+fnScreenShot_Date_format()+" ]";	
			fnApps_Report_Logs("PASSED == All issues ids exists into table");
		}catch(Throwable t){
			fnTake_Screen_Shot("NEW_Escalation_Id_NotGenrated");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	
	
	}catch(Throwable se) {
		isTestCasePass = false;
		fnTake_Screen_Shot("BackToOriginal");
		fnApps_Report_Logs("FAILED == Please refer Screenshot >> "+"BackToOriginal"+fnScreenShot_Date_format()+" Getting Exception >> "+se.getMessage().trim());
		throw new Exception("FAILED == Please refer Screenshot >> "+"BackToOriginal"+fnScreenShot_Date_format()+" Getting Exception >> "+se.getMessage().trim());}		
		
		
		
}		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Test(dependsOnMethods={"CloseEscalationMsg_EditFirstIssueAgain_ChangeIssueSubType_BacktoOriginal_Verify_NewEscalationId"}, priority = 7, description="  [Close the escalation. Confirm that this has closed all the related issues.]")
	public void CloseEscalationMsg_CloseEscalation_and_Verify_AllIssues_Closed() throws Throwable{
		fnApps_Report_Logs("========================================================================================================================================");
		fnApps_Report_Logs("####### Test Case ::::7 CloseEscalationMsg_CloseEscalation_and_Verify_AllIssues_Closed");
		
	

		fnWait_and_Click("Escalation_Summary_assign_to_me_button");
		fnsLoading_Progressingwait(3);
			
		fncEscalation_VerifyChange_Status_on_SummaryDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_PRQ", 2, 26));
		
		fnGet_Element_Enabled("validation_error_msg");
		fncVerify_DataUpdated_Successfully("validation_error_msg", "Summary TAB");	
		

		fnSearchIssue_Ajax_Link_Click();
		fnGet_Element_Enabled("footer");
		fnsLoading_Progressingwait(1);
	
		fncEscalation_Verify_First_IssueClosed_after_EscalationClosed("SearchIssue_Parent_Status",issue_id_green, "CLOSED" , false);
		fncEscalation_Verify_First_IssueClosed_after_EscalationClosed("SearchIssue_Parent_Status",issue_id_amber, "CLOSED" , false);
		fncEscalation_Verify_First_IssueClosed_after_EscalationClosed("SearchIssue_Parent_Status",issue_id_red, "CLOSED" , false);
	

}

	
	
	
	
	
	
	
	
	
	
	
	
	
	

	@Test(dependsOnMethods={"CloseEscalationMsg_CloseEscalation_and_Verify_AllIssues_Closed"}, priority = 8, description="  [Edit one of the issues to change the sub-type, but do not re-open the issue. Verify the message being generated here.]")
	public void CloseEscalationMsg_EditSecondIssue_ChangeIssueSubType_WithoutOpenIssue_and_Verify_UpdateMsg() throws Throwable{
		
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::8 CloseEscalationMsg_EditSecondIssue_ChangeIssueSubType_WithoutOpenIssue_and_Verify_UpdateMsg.");
		
	//	IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//src//nsf//ecap//xls//IssueMgt_Suite.xlsx");
			
		//Search IssueID and also navigate to ViewIssue Summary Details page 
		fncIssueID_SearchIssueID_and_Navigate_to_ViewIssueSummaryDetailsPageComes(issue_id_amber, true);
		
		//Clicking on Summary TAB on View issue page
		fnGet_Element_Enabled("ViewIssueSummaryDetails_summary_detail_tab");
		fnWait_and_Click("ViewIssueSummaryDetails_summary_detail_tab");
		
		//Select Status From Drop Down
		fnGet_Element_Enabled("ViewIssueSummaryDetails_IssueSubType_dd_click");
		fnDD_value_Select("ViewIssueSummaryDetails_IssueSubType_dd_click", "ViewIssueSummaryDetails_IssueSubType_dd_value", "li", IssueMgt_Suitexls.getCellData("CloseEscalation_Msg", 2, 14));
			
		// Clicking on save button
		fnGet_Element_Enabled("ViewIssueSummaryDetails_summary_details_save_button");
		fnWait_and_Click("ViewIssueSummaryDetails_summary_details_save_button");
		fnsLoading_Progressingwait(3);
		
		fnGet_Element_Enabled("validation_error_msg");
		Thread.sleep(1000);
		fnGet_Element_Enabled("validation_error_msg");
		fncVerify_DataUpdated_Successfully("validation_error_msg", "View Summary");
	
}	
	
	
	
	
	
	

	
	
	
	
	
	
	

	@Test(dependsOnMethods={"CloseEscalationMsg_EditSecondIssue_ChangeIssueSubType_WithoutOpenIssue_and_Verify_UpdateMsg"}, priority = 9, description="  [Re-open one of the issues from the escalation without changing any other details of the issue. Verify the 2nd message being generated here.]")
	public void CloseEscalationMsg_ReOpenThirdIssue_ByChangingStatus_and_Verify_UpdateMsg() throws Throwable{
		
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::9 CloseEscalationMsg_ReOpenThirdIssue_ByChangingStatus_and_Verify_UpdateMsg.");
		
	//	IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//src//nsf//ecap//xls//IssueMgt_Suite.xlsx");
		
	
		//Search IssueID and also navigate to ViewIssue Summary Details page 
		fncIssueID_SearchIssueID_and_Navigate_to_ViewIssueSummaryDetailsPageComes(issue_id_red, true);
		
		//Clicking on Summary TAB on View issue page
		fnGet_Element_Enabled("ViewIssueSummaryDetails_summary_detail_tab");
		fnWait_and_Click("ViewIssueSummaryDetails_summary_detail_tab");
		

		fnGet_Element_Enabled("ViewIssueSummaryDetails_assign_to_me");
		fnWait_and_Click("ViewIssueSummaryDetails_assign_to_me");
		fnsLoading_Progressingwait(3);
		
		Thread.sleep(2000);
		fnGet_Element_Enabled("footer");
		//Select Status From Drop Down
		fnGet_Element_Enabled("ViewIssueSummaryDetails_new_status_dd_click");
		fnDD_value_Select("ViewIssueSummaryDetails_new_status_dd_click", "ViewIssueSummaryDetails_new_status_dd_value", "li", IssueMgt_Suitexls.getCellData("CloseEscalation_Msg", 2, 13));
		
		
		//Waiting for Astric(*) Image to display
		fnGet_Element_Enabled("ViewIssueSummaryDetails_new_status_response_Astric_Image");	
		
		// Enter free text value into New Status Response field		
		fnGet_Element_Enabled("ViewIssueSummaryDetails_new_status_response_textfield");
		fnWait_and_Type("ViewIssueSummaryDetails_new_status_response_textfield", "Edit Issue, Changing Status from (Satisfactory Closure) to (Initial Record)." );
			
		// Clicking on save button
		fnGet_Element_Enabled("ViewIssueSummaryDetails_summary_details_save_button");
		fnWait_and_Click("ViewIssueSummaryDetails_summary_details_save_button");
		fnsLoading_Progressingwait(3);
		
		fnGet_Element_Enabled("validation_error_msg");
		Thread.sleep(1000);
		fnGet_Element_Enabled("validation_error_msg");
		fncVerify_DataUpdated_Successfully("validation_error_msg", "View Escalation : Status change to 'TechnologistApprovedToClose'");
	
}	
		

	
	
	
	
	
	
	
	
	
	

	@Test(dependsOnMethods={"CloseEscalationMsg_ReOpenThirdIssue_ByChangingStatus_and_Verify_UpdateMsg"}, priority = 10, description="  [Verify that the escalation status of the edited issue changes back to Green and that the original escalation still exists containing just the other two issues.]")
	public void CloseEscalationMsg_Verify_ColorsOfAllIssues_and_EscalationExistsWithTwoIssues() throws Throwable{
		
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::10 CloseEscalationMsg_Verify_ColorsOfAllIssues_and_EscalationExistsWithTwoIssues.");
		
//		IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//src//nsf//ecap//xls//IssueMgt_Suite.xlsx");

		fnSearchIssue_Ajax_Link_Click();
		fnGet_Element_Enabled("footer");
		fnsLoading_Progressingwait(1);
	
		fncEscalation_Verify_First_IssueClosed_after_EscalationClosed("SearchIssue_Escalation_Status",issue_id_green, "RED" , false);
		fncEscalation_Verify_First_IssueClosed_after_EscalationClosed("SearchIssue_Escalation_Status",issue_id_amber, "RED" , false);
		fncEscalation_Verify_First_IssueClosed_after_EscalationClosed("SearchIssue_Escalation_Status",issue_id_red, "GREEN" , false);
	
		
		//Search Escalation and also navigate to ViewEscalation EscalationDetails page
		fncEscalation_SearchEscalation_and_Navigate_till_ViewEscalationDetailsPageComes(NEW_Escalation_Id);
		
		
		//Waiting for Footer till page load
		fnGet_Element_Enabled("footer");
							
		try{
			
			//Verify 8 Newly generated issues exists into the table on Escalation details screen.
			String related_issue_table=fnGet_Field_TEXT("ViewEscalation_RelatedIssues_table").trim();
			assert ((related_issue_table.contains(issue_id_green)) && related_issue_table.contains(issue_id_amber)  ):
				"FAILED == Issues IDs are not Exists into the Related Issues Table."+"Please refer ScreenShot [ IssueIds_Not_Exists"+fnScreenShot_Date_format()+" ]";	
			fnApps_Report_Logs("PASSED == All issues ids exists into table");
		}catch(Throwable t){
			fnTake_Screen_Shot("IssueIds_Not_Exists");
			throw new Exception(Throwables.getStackTraceAsString(t));}
			
}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


	@Test(dependsOnMethods={"CloseEscalationMsg_Verify_ColorsOfAllIssues_and_EscalationExistsWithTwoIssues"}, priority = 11, description="  [Confirm that the escalation's audit trail shows the removal of the re-opened issue.]")
	public void CloseEscalationMsg_Verify_EscalationAuditTrail_Shows_Removal_of_ReOpenedIssue() throws Throwable{
		
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::11 CloseEscalationMsg_Verify_EscalationAuditTrail_Shows_Removal_of_ReOpenedIssue.");
		
	//	IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//src//nsf//ecap//xls//IssueMgt_Suite.xlsx");
			
			
		//Search Escalation and also navigate to ViewEscalation EscalationDetails page
		fncEscalation_SearchEscalation_and_Navigate_till_ViewEscalationDetailsPageComes(NEW_Escalation_Id);
		
		
		//Waiting for Footer till page load
		fnGet_Element_Enabled("footer");
		Thread.sleep(2000);
		
		fnGet_Element_Enabled("ViewEscalation_AuditTrail_TAB");
		fnWait_and_Click("ViewEscalation_AuditTrail_TAB");
		
			
		//Verify Escalation's audit trail display the removal of the re-opened issue in Audit Trail Details Table
		fnGet_Element_Enabled("footer");
		fnGet_Element_Enabled("ViewEscalation_AuditTrailDetails_Table_AfterColumnXpath");
		String related_issue_table=fnGet_Field_TEXT("ViewEscalation_AuditTrailDetails_Table_AfterColumnXpath");
		try{
			assert (!(related_issue_table.contains(issue_id_red)) && related_issue_table.contains(issue_id_amber) && related_issue_table.contains(issue_id_green)):"FAILED == Escalation's audit trail does not Display the removal of the re-opened issue in Audit Trail Details Table, Please refer ScreenShot [ AuditTrailRemovedIssueDisplayFail"+fnScreenShot_Date_format()+" ]";	
			fnApps_Report_Logs("PASSED == Escalation's audit trail successfully display the removal of the re-opened issue in Audit Trail Details Table");
		}catch(Throwable t){
			fnTake_Screen_Shot("AuditTrailRemovedIssueDisplayFail");
			fnApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));}
		
		
		fnApps_Report_Logs("############################################# [BS - 16]  CloseEscalation_Messages Script END ########################################## ");
		fnApps_Report_Logs("=========================================================================================================================================");
}	
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@AfterTest
	public void reportTestResult() throws Throwable {
		fns_ReportTestResult_atClassLevel(IssueMgt_Suitexls,  (this.getClass().getSimpleName()) );
	}
	
	

	@AfterTest
	public void Test_success() throws Throwable{
		driver.quit();
		
	}
	
	
}	
