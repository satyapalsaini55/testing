package nsf.ecap.IssueMgt_Suite;


//
import nsf.ecap.util.*;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.os.WindowsUtils;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;

public class Escalation_PRQ_DeleteIssue extends TestSuiteBase_IM{
	
	public static String issue_id_firstIssue_witoutEsc="I0056670"; 
	public static String issue_id_green="I0056675";
	public static String issue_id_amber="I0056676";
	public static String issue_id_red="I0056677";
	public static String Escalation_Id="E0004766";
	public static String validation_error_msg_text;
	public static String TitleShortDescriptionText="EscPRQ_DeleteIssue[BS-17], D/T=";

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
	fnApps_Report_Logs("################################## [BS-17] Escalation PRQ Delete Issue");
	if (!IsBrowserPresentAlready) {
		IsBrowserPresentAlready = fnLaunchBrowserAndLogin();
		fnApps_Report_Logs("Browser is launched and ******** Successfully login into 'I-Pulse' Application.");
	}
}	
	
	
	
	
	
	
	

	@Test(dependsOnMethods={"Launch_Browser_and_Successfully_Login_into_the_Application"}, priority = 1, description="  [Create Issue ID with Green Color]")
	public void Escalation_PRQ_FirstIssue_Green() throws Throwable{
		
			
		fnApps_Report_Logs("################### Test Case ::::::1 Escalation_PRQ_FirstIssue_Green ");
		
		
		  
	//	IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//src//nsf//ecap//xls//IssueMgt_Suite.xlsx");
			
		//Clicking on CreateIssue Ajax Link
		fnCreateIssue_Ajax_Link_Click();
		
		//Site Details page data entered and clicking on next button
		fnVerify_SiteDetailsPage_Data_Entered_and_NavigateTo_SummaryDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_PRQ_DeleteIssue", 2, 2), IssueMgt_Suitexls.getCellData("Escalation_PRQ_DeleteIssue", 2, 14));
	
		//Summary Details page data entered and clicking on next button
		fnVerify_SummaryDetailsPage_Data_Entered_and_NavigateTo_CustomerDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_PRQ_DeleteIssue", 2, 6), IssueMgt_Suitexls.getCellData("Escalation_PRQ_DeleteIssue", 2, 7), IssueMgt_Suitexls.getCellData("Escalation_PRQ_DeleteIssue", 2, 8), fnGet_2Days_Past_date(), TitleShortDescriptionText);
	
		//Clicking on Customer  NEXT Button
		fnClick_UnTill_Navigate_to_NextPage("CreateIssue_Customer_next_bttn","CreateIssue_Customer_Name_TextField","CreateIssue_product_code_bttn");	
		
		//Product Details page data entered and clicking on next button
		fnVerify_ProductDetailsPage_Data_Entered_and_NavigateTo_DocumentsDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_PRQ_DeleteIssue", 2, 9));
		
	
		// Clicking on Document next button
		fnClick_UnTill_Navigate_to_NextPage("CreateIssue_document_next_bttn","CreateIssue_document_Add_Attachement","CreateIssue_confirmation_submit_bttn");		
		
		// Clicking on Confirmation Submit button
		fnWait_and_Click("CreateIssue_confirmation_submit_bttn");
		
		//Verify issue ID Generated and Color is as per Expected and Stored into String
		issue_id_firstIssue_witoutEsc=fnVerified_IssueID_Generated_and_Color_Verification(1, "GREEN");
}
	
	
	
	
	
	
	
	
	
	@Test(dependsOnMethods={"Escalation_PRQ_FirstIssue_Green"}, priority = 2, description="Verify Alert --- Food Issues Unassigned.")
	public void Check_Alert_FoodIssuesUnassigned() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::2 Check_Alert_FoodIssuesUnassigned ");
		
		fnVerify_ADMIN_Alert_redirect_from_summarydetailsPage("Issues_FoodIssuesUnassigned_text_link","Issues_FoodIssuesUnassigned_issue_id_filter", issue_id_firstIssue_witoutEsc);
		fnVerify_ID_Exist_inAlert_Table("Issues_FoodIssuesUnassigned_table_1st_cell","Issues_FoodIssuesUnassigned_text_link", issue_id_firstIssue_witoutEsc);

}
	
	
	
	
	
	
	
	
	
	@Test(dependsOnMethods={"Check_Alert_FoodIssuesUnassigned"}, priority = 3, description="  [Go to Summary tab > Click 'Assign to me'. Click Save.]")
	public void DeleteIssue_ClickOn_DeleteThisIssueBttn_and_VerifyDialogBoxOpen() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::3 DeleteIssue_ClickOn_DeleteThisIssueBttn_and_VerifyDialogBoxOpen ");
		
	
	//	IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//src//nsf//ecap//xls//IssueMgt_Suite.xlsx");
	
		//Search IssueID and also navigate to ViewIssue Summary Details page 
		fncIssueID_SearchIssueID_and_Navigate_to_ViewIssueSummaryDetailsPageComes(issue_id_firstIssue_witoutEsc, true);
	
		//Clicking on Summary TAB on View issue page
		fnGet_Element_Enabled("ViewIssueSummaryDetails_summary_detail_tab");
		fnWait_and_Click("ViewIssueSummaryDetails_summary_detail_tab");
	
		fnGet_Element_Enabled("footer");
	
		fnGet_Element_Enabled("ViewIssueSummaryDetails_DeleteThisIssue_Bttn");
		fnWait_and_Click("ViewIssueSummaryDetails_DeleteThisIssue_Bttn");
	
		String DialogBoxMsg=fnGet_Field_TEXT("ViewIssueSummaryDetails_DeleteThisIssue_ConfirmDelete_DialogBox_TopMsgDiv").trim();
		try{
			assert (DialogBoxMsg.contains("Confirm Deletion")):"ConfirmDelete Dialog Box is not getting opened, Please refer Screen Shot[DailogBoxNotOpen"+ fnScreenShot_Date_format();	
			fnApps_Report_Logs("PASSED == ConfirmDelete Dialog Box Successfully opened.");
		}catch(Throwable t) {
			isTestCasePass = false;
			fnTake_Screen_Shot("DailogBoxNotOpen");
			fnApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
}	

	
	

	
	
	
	
	
	
	
	

	@Test(dependsOnMethods={"DeleteIssue_ClickOn_DeleteThisIssueBttn_and_VerifyDialogBoxOpen"}, priority = 4, description="  [Go to Summary tab > Click 'Assign to me'. Click Save.]")
	public void DeleteIssue_YesDeleteBttn_of_DialogBox_WithOutReasonText_andVerify_ValidationMsg() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::4 DeleteIssue_YesDeleteBttn_of_DialogBox_WithOutReasonText_andVerify_ValidationMsg ");
		
	//	IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//src//nsf//ecap//xls//IssueMgt_Suite.xlsx");
		
		fnGet_Element_Enabled("ViewIssueSummaryDetails_DeleteThisIssue_YESDelete_Bttn");
		fnWait_and_Click("ViewIssueSummaryDetails_DeleteThisIssue_YESDelete_Bttn");
		
		String ValidationMsg=fnGet_Field_TEXT("ViewIssueSummaryDetails_DeleteThisIssue_Reason_ValidationMsgXpath");
		try{
			assert (ValidationMsg.contains("Please provide the reason for deleting the issue")):"ConfirmDelete Dialog Box is not getting opened, Please refer Screen Shot[ExpectedTextNotMatched"+ fnScreenShot_Date_format();	
			fnApps_Report_Logs("PASSED == Successfully matched ValidationTextMessage("+ValidationMsg+") of ReasonTextField with Expected TEXT.");
		}catch(Throwable t) {
			isTestCasePass = false;
			fnTake_Screen_Shot("ExpectedTextNotMatched");
			fnApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));}
}	
	
	
	
	
	
	
	
	
	
	
	
	

	@Test(dependsOnMethods={"DeleteIssue_YesDeleteBttn_of_DialogBox_WithOutReasonText_andVerify_ValidationMsg"}, priority = 5, description="  [Go to Summary tab > Click 'Assign to me'. Click Save.]")
	public void DeleteIssue_ClickOnCancelBttn_Verify_DialogBoxClose() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::5 DeleteIssue_ClickOnCancelBttn_Verify_DialogBoxClose ");

	//	IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//src//nsf//ecap//xls//IssueMgt_Suite.xlsx");
		
		fnGet_Element_Enabled("ViewIssueSummaryDetails_DeleteThisIssue_Cancel_Bttn");
		fnWait_and_Click("ViewIssueSummaryDetails_DeleteThisIssue_Cancel_Bttn");
			
		Thread.sleep(3000);
			
			try{
				Actions act2 = new Actions(driver);
				WebElement EleParentStatusSecondIssue=fnGet_OR_IM_ObjectX("ViewIssueSummaryDetails_Comments_TextField");
				act2.moveToElement(EleParentStatusSecondIssue).doubleClick().build().perform();
				fnApps_Report_Logs("PASSED == Successfully Close Dialog Box and return to ViewIssueSummaryDetailsPage after Clicking on Cancel Button");
			}catch(Throwable ta){
				isTestCasePass = false;
				fnTake_Screen_Shot("DialogBoxNotClose");
				fnApps_Report_Logs("FAILED == Dialog box is not getting closed, Please refer Screen Shot[DialogBoxNotClose"+ fnScreenShot_Date_format()+"], Getting Exception >> "+ta.getMessage());
				throw new Exception("FAILED == Dialog box is not getting closed, Please refer Screen Shot[DialogBoxNotClose"+ fnScreenShot_Date_format()+"], Getting Exception >> "+ta.getMessage());}
}	
	

	
	
	
	
	
	
	
	
	
	
	

	@Test(dependsOnMethods={"DeleteIssue_ClickOnCancelBttn_Verify_DialogBoxClose"}, priority = 6, description="  [Go to Summary tab > Click 'Assign to me'. Click Save.]")
	public void DeleteIssue_OpenDialogBox_EnterTextIntoReasonTextField_ClickOnYesDeleteBttn_and_VerifyIssueDeleted() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::6 DeleteIssue_OpenDialogBox_EnterTextIntoReasonTextField_ClickOnYesDeleteBttn_and_VerifyIssueDeleted ");

	
		
	//	IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//src//nsf//ecap//xls//IssueMgt_Suite.xlsx");
		
		fnGet_Element_Enabled("ViewIssueSummaryDetails_DeleteThisIssue_Bttn");
		fnWait_and_Click("ViewIssueSummaryDetails_DeleteThisIssue_Bttn");
	
		String DialogBoxMsg=fnGet_Field_TEXT("ViewIssueSummaryDetails_DeleteThisIssue_ConfirmDelete_DialogBox_TopMsgDiv");
		try{
			assert (DialogBoxMsg.contains("Confirm Deletion")):"ConfirmDelete Dialog Box is not getting opened, Please refer Screen Shot[DailogBoxNotOpen"+ fnScreenShot_Date_format();	
			fnApps_Report_Logs("PASSED == ConfirmDelete Dialog Box Successfully opened.");
		}catch(Throwable t) {
			isTestCasePass = false;
			fnTake_Screen_Shot("DailogBoxNotOpen");
			fnApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));}

	
		fnGet_Element_Enabled("ViewIssueSummaryDetails_DeleteThisIssue_ReasonText_Box");
		fnWait_and_Type("ViewIssueSummaryDetails_DeleteThisIssue_ReasonText_Box", IssueMgt_Suitexls.getCellData("Escalation_PRQ_DeleteIssue", 2, 12));
		
	
	
		fnGet_Element_Enabled("ViewIssueSummaryDetails_DeleteThisIssue_YESDelete_Bttn");
		fnWait_and_Click("ViewIssueSummaryDetails_DeleteThisIssue_YESDelete_Bttn");
		fnsLoading_Progressingwait(2);
	
	
		try{
			fnGet_Element_Enabled("footer");			
			fnGet_Element_Enabled("SearchIssue_issue_id_text_field");
			fnWait_and_Click("SearchIssue_issue_id_text_field");
		
			fnApps_Report_Logs("PASSED == After clicking on YESDelete Bttn of DialogBox, Page Successfully Navigate to SearchIssue Page");
		}catch(Throwable t) {
			isTestCasePass = false;
			fnTake_Screen_Shot("NavigateToSearchIssuePageFail");
			fnApps_Report_Logs("FAILED == After clicking on YESDelete Bttn of DialogBox, Page is not Navigated to SearchIssue Page, Please refer Screen Shot[NavigateToSearchIssuePageFail"+ fnScreenShot_Date_format()+"], Getting Exception >> "+Throwables.getStackTraceAsString(t));
			throw new Exception("FAILED == After clicking on YESDelete Bttn of DialogBox, Page is not Navigated to SearchIssue Page, Please refer Screen Shot[NavigateToSearchIssuePageFail"+ fnScreenShot_Date_format()+"], Getting Exception >> "+Throwables.getStackTraceAsString(t));}
	
	
		
		
		WithOut_OR_fnClear(OR_IM.getProperty("SearchIssue_issue_id_text_field"));
		fnWait_and_Type("SearchIssue_issue_id_text_field", issue_id_firstIssue_witoutEsc);
		fnWait_and_Click("SearchIssue_search_bttn");
		fnsLoading_Progressingwait(3);

		Thread.sleep(2000);
		fnGet_Element_Enabled("footer");	
		
		fnGet_Element_Enabled("SearchResult_IssueID_column");
		WithOut_OR_fnMove_To_Element_and_DoubleClick(OR_IM.getProperty("SearchResult_IssueID_column"));
		Thread.sleep(500);
		String Issue_NoRecordFound_text=fnGet_Field_TEXT("SearchResult_IssueID_column").trim();
		try{
			assert Issue_NoRecordFound_text.contains("No records found."):"IssueID ("+issue_id_firstIssue_witoutEsc+") is not Deleted, Please refer screen shot"+"IssueIdSNotDeleted" + fnScreenShot_Date_format();
			fnApps_Report_Logs("PASSED == IssueID ("+issue_id_firstIssue_witoutEsc+") is successfully Verified and It is Deleted");
	
		}catch(Throwable t) {
			isTestCasePass = false;
			fnTake_Screen_Shot("IssueIdSNotDeleted");
			fnApps_Report_Logs(Throwables.getStackTraceAsString(t).trim());
			throw new Exception(Throwables.getStackTraceAsString(t).trim());	}
		
	}	
		
	
	
	
	
	
	
	
	
	
	
//////////////////// Create Escalation Start from BC-14
	
	
	
	@Test(dependsOnMethods={"DeleteIssue_OpenDialogBox_EnterTextIntoReasonTextField_ClickOnYesDeleteBttn_and_VerifyIssueDeleted"}, priority = 7, description="  [Create Issue ID with Green Color]")
	public void Escalation_PRQ_DeleteIssue_FirstIssue_Green() throws Throwable{
		
		fnApps_Report_Logs("=========================================================================================================================================");	
		fnApps_Report_Logs("################### Test Case ::::::7 Escalation_PRQ_DeleteIssue_FirstIssue_Green ");
		
		
		  
	//	IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//src//nsf//ecap//xls//IssueMgt_Suite.xlsx");
			
		//Clicking on CreateIssue Ajax Link
		fnCreateIssue_Ajax_Link_Click();
		
		//Site Details page data entered and clicking on next button
		fnVerify_SiteDetailsPage_Data_Entered_and_NavigateTo_SummaryDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_PRQ_DeleteIssue", 2, 2), IssueMgt_Suitexls.getCellData("Escalation_PRQ_DeleteIssue", 2, 3));
	
		//Summary Details page data entered and clicking on next button
		fnVerify_SummaryDetailsPage_Data_Entered_and_NavigateTo_CustomerDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_PRQ_DeleteIssue", 2, 6), IssueMgt_Suitexls.getCellData("Escalation_PRQ_DeleteIssue", 2, 7), IssueMgt_Suitexls.getCellData("Escalation_PRQ_DeleteIssue", 2, 8), fnGet_2Days_Past_date(), TitleShortDescriptionText);
	
		//Clicking on Customer  NEXT Button
		fnClick_UnTill_Navigate_to_NextPage("CreateIssue_Customer_next_bttn","CreateIssue_Customer_Name_TextField","CreateIssue_product_code_bttn");	
		
		//Product Details page data entered and clicking on next button
		fnVerify_ProductDetailsPage_Data_Entered_and_NavigateTo_DocumentsDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_PRQ_DeleteIssue", 2, 9));
		
	
		// Clicking on Document next button
		fnClick_UnTill_Navigate_to_NextPage("CreateIssue_document_next_bttn","CreateIssue_document_Add_Attachement","CreateIssue_confirmation_submit_bttn");		
		
		// Clicking on Confirmation Submit button
		fnWait_and_Click("CreateIssue_confirmation_submit_bttn");
		
		//Verify issue ID Generated and Color is as per Expected and Stored into String
		issue_id_green=fnVerified_IssueID_Generated_and_Color_Verification(1, "GREEN");
}
	
	
	
	
	
	
	
	
	
	@Test(dependsOnMethods={"Escalation_PRQ_DeleteIssue_FirstIssue_Green"}, priority = 8, description="  [Create Issue ID with Amber color]")
	public void Escalation_PRQ_DeleteIssue_SecondIssue_Amber() throws Throwable{
		
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::8 Escalation_PRQ_DeleteIssue_SecondIssue_Amber ");
	//	IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//src//nsf//ecap//xls//IssueMgt_Suite.xlsx");
		
		
		//Clicking on CreateIssue Ajax Link
		fnCreateIssue_Ajax_Link_Click();
		
		//Site Details page data entered and clicking on next button
		fnVerify_SiteDetailsPage_Data_Entered_and_NavigateTo_SummaryDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_PRQ_DeleteIssue", 2, 2), IssueMgt_Suitexls.getCellData("Escalation_PRQ_DeleteIssue", 2, 4));
	
		//Summary Details page data entered and clicking on next button
		fnVerify_SummaryDetailsPage_Data_Entered_and_NavigateTo_CustomerDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_PRQ_DeleteIssue", 2, 6), IssueMgt_Suitexls.getCellData("Escalation_PRQ_DeleteIssue", 2, 7), IssueMgt_Suitexls.getCellData("Escalation_PRQ_DeleteIssue", 2, 8), fnGet_2Days_Past_date(), TitleShortDescriptionText);
	


		//Clicking on Customer  NEXT Button
		fnClick_UnTill_Navigate_to_NextPage("CreateIssue_Customer_next_bttn","CreateIssue_Customer_Name_TextField","CreateIssue_product_code_bttn");	
		
		//Product Details page data entered and clicking on next button
		fnVerify_ProductDetailsPage_Data_Entered_and_NavigateTo_DocumentsDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_PRQ_DeleteIssue", 2, 9));
		
	
		// Clicking on Document next button
		fnClick_UnTill_Navigate_to_NextPage("CreateIssue_document_next_bttn","CreateIssue_document_Add_Attachement","CreateIssue_confirmation_submit_bttn");		
		
		
		// Clicking on Confirmation Submit button
		fnWait_and_Click("CreateIssue_confirmation_submit_bttn");
		
		
		//Verify issue ID Generated and Color is as per Expected and Stored into String
		issue_id_amber=fnVerified_IssueID_Generated_and_Color_Verification(2, "AMBER");
		
}
	
	
	
	
	
	
	
	
	
	@Test(dependsOnMethods={"Escalation_PRQ_DeleteIssue_SecondIssue_Amber"}, priority = 9, description="  [Create Issue ID with RED color]")
	public void Escalation_PRQ_DeleteIssue_ThirdIssue_Red() throws Throwable{
		
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::9 Escalation_PRQ_DeleteIssue_ThirdIssue_Red ");
//		IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//src//nsf//ecap//xls//IssueMgt_Suite.xlsx");
		
		
		//Clicking on CreateIssue Ajax Link
		fnCreateIssue_Ajax_Link_Click();
		
		//Site Details page data entered and clicking on next button
		fnVerify_SiteDetailsPage_Data_Entered_and_NavigateTo_SummaryDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_PRQ_DeleteIssue", 2, 2), IssueMgt_Suitexls.getCellData("Escalation_PRQ_DeleteIssue", 2, 5));
	
		//Summary Details page data entered and clicking on next button
		fnVerify_SummaryDetailsPage_Data_Entered_and_NavigateTo_CustomerDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_PRQ_DeleteIssue", 2, 6), IssueMgt_Suitexls.getCellData("Escalation_PRQ_DeleteIssue", 2, 7), IssueMgt_Suitexls.getCellData("Escalation_PRQ_DeleteIssue", 2, 8), fnGet_2Days_Past_date(), TitleShortDescriptionText);
	


		//Clicking on Customer  NEXT Button
		fnClick_UnTill_Navigate_to_NextPage("CreateIssue_Customer_next_bttn","CreateIssue_Customer_Name_TextField","CreateIssue_product_code_bttn");	
		
		//Product Details page data entered and clicking on next button
		fnVerify_ProductDetailsPage_Data_Entered_and_NavigateTo_DocumentsDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_PRQ_DeleteIssue", 2, 9));
		
	
		// Clicking on Document next button
		fnClick_UnTill_Navigate_to_NextPage("CreateIssue_document_next_bttn","CreateIssue_document_Add_Attachement","CreateIssue_confirmation_submit_bttn");		
		
		// Clicking on Confirmation Submit button
		fnWait_and_Click("CreateIssue_confirmation_submit_bttn");
		
	
		
		//Verify issue ID Generated and Color is as per Expected and Stored into String
		issue_id_red=fnVerified_IssueID_Generated_and_Color_Verification(3, "RED");
		
}
	
	
	
	
	
	
	
	
	
	
	
	
	
			
	@Test(dependsOnMethods={"Escalation_PRQ_DeleteIssue_ThirdIssue_Red"}, priority = 10, description="  [Verify Escalations ID Generated with three issues.]")
	public void Escalation_PRQ_DeleteIssue_Generated_With_3_Issues() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::10 Escalation_PRQ_DeleteIssue_Generated_With_3_Issues ");
		

//		IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//src//nsf//ecap//xls//IssueMgt_Suite.xlsx");
		
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
		
	}
	

	
	
	
	
	
	
	
	
	
	
	
	@Test(dependsOnMethods={"Escalation_PRQ_DeleteIssue_Generated_With_3_Issues"}, priority = 11, description="  [Go to Summary tab > Click 'Assign to me'. Click Save.]")
	public void EditEsc_OpenAndDeleteEscFirstIssue_and_VerifyAfterIssueDelete_PageRedirectToViewEscPage() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::11 EditEsc_OpenAndDeleteEscFirstIssue_and_VerifyAfterIssueDelete_PageRedirectToViewEscPage ");
		
		
	//	IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//src//nsf//ecap//xls//IssueMgt_Suite.xlsx");
	
		
		//fnEscalation_SearchEscalation_and_Navigate_till_ViewEscalationDetailsPageComes(Escalation_Id); //for Testing purpose only
		//fnGet_Element_Enabled("footer");//for Testing purpose only
		//fnGet_Element_Enabled("ViewEscalation_RelatedIssues_table");
		
		
		
		
		
		fnsTable_ClickOn_LINK_ByMatchingText(issue_id_green);
	
		//Clicking on Summary TAB on View issue page
		fnGet_Element_Enabled("ViewIssueSummaryDetails_summary_detail_tab");
		fnWait_and_Click("ViewIssueSummaryDetails_summary_detail_tab");
	
		fnGet_Element_Enabled("footer");
	
		
		fnGet_Element_Enabled("ViewIssueSummaryDetails_DeleteThisIssue_Bttn");
		fnWait_and_Click("ViewIssueSummaryDetails_DeleteThisIssue_Bttn");
	
		String DialogBoxMsg=fnGet_Field_TEXT("ViewIssueSummaryDetails_DeleteThisIssue_ConfirmDelete_DialogBox_TopMsgDiv");
		try{
			assert (DialogBoxMsg.contains("Confirm Deletion")):"ConfirmDelete Dialog Box is not getting opened, Please refer Screen Shot[DailogBoxNotOpen"+ fnScreenShot_Date_format();	
			fnApps_Report_Logs("PASSED == ConfirmDelete Dialog Box Successfully opened.");
		}catch(Throwable t) {
			isTestCasePass = false;
			fnTake_Screen_Shot("DailogBoxNotOpen");
			fnApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));}

	
		fnGet_Element_Enabled("ViewIssueSummaryDetails_DeleteThisIssue_ReasonText_Box");
		fnWait_and_Type("ViewIssueSummaryDetails_DeleteThisIssue_ReasonText_Box", IssueMgt_Suitexls.getCellData("Escalation_PRQ_DeleteIssue", 2, 15));
	
	
	
		fnGet_Element_Enabled("ViewIssueSummaryDetails_DeleteThisIssue_YESDelete_Bttn");
		fnWait_and_Click("ViewIssueSummaryDetails_DeleteThisIssue_YESDelete_Bttn");
	
	
		try{
			fnGet_Element_Enabled("footer");			
			fnGet_Element_Enabled("ViewEscalation_RelatedIssues_table");
				
			fnApps_Report_Logs("PASSED == After clicking on YESDelete Bttn of DialogBox, Page Successfully Navigate to ViewEscalation Page");
		}catch(Throwable t) {
			isTestCasePass = false;
			fnTake_Screen_Shot("NavigateToViewEscalationPageFail");
			fnApps_Report_Logs("FAILED == After clicking on YESDelete Bttn of DialogBox, Page is not Navigated to ViewEscalation Page, Please refer Screen Shot[NavigateToViewEscalationPageFail"+ fnScreenShot_Date_format()+"], Getting Exception >> "+Throwables.getStackTraceAsString(t));
			throw new Exception("FAILED == After clicking on YESDelete Bttn of DialogBox, Page is not Navigated to ViewEscalation Page, Please refer Screen Shot[NavigateToViewEscalationPageFail"+ fnScreenShot_Date_format()+"], Getting Exception >> "+Throwables.getStackTraceAsString(t));}
	
}	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Test(dependsOnMethods={"EditEsc_OpenAndDeleteEscFirstIssue_and_VerifyAfterIssueDelete_PageRedirectToViewEscPage"}, priority = 12, description="  [Go to Summary tab > Click 'Assign to me'. Click Save.]")
	public void EditEsc_Verify_EsclationDismentled_and_VerifyCorrectAuditTrailEntry_thenVerify_OtherTwoIssuesColor() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::12 EditEsc_Verify_EsclationDismentled_and_VerifyCorrectAuditTrailEntry_thenVerify_OtherTwoIssuesColor ");
			WebDriverWait waitvar = new WebDriverWait(driver, Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
		//	IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//src//nsf//ecap//xls//IssueMgt_Suite.xlsx");
		
		
		
			
			fnGet_Element_Enabled("ViewEscalation_RelatedIssues_table");
			Thread.sleep(500);
			String related_issue_table=fnGet_Field_TEXT("ViewEscalation_RelatedIssues_table").trim();
			try{
				assert (!(related_issue_table.contains(issue_id_green)) && !(related_issue_table.contains(issue_id_amber)) && !(related_issue_table.contains(issue_id_red))):"FAILED == Escalation has not been dismantled but still issue ids are displayed, "+"Please refer ScreenShot [ "+Escalation_Id+fnScreenShot_Date_format();	
				fnApps_Report_Logs("PASSED == Escalation has been dismantled successfully ");
			}catch(Throwable t){
				fnTake_Screen_Shot(Escalation_Id);
				fnApps_Report_Logs(Throwables.getStackTraceAsString(t));
				throw new Exception(Throwables.getStackTraceAsString(t));}


	
	
	
			fnGet_Element_Enabled("ViewEscalation_AuditTrail_TAB");
			fnWait_and_Click("ViewEscalation_AuditTrail_TAB");
			
						
		
			
			//Verify Escalation's audit trail display the removal of the re-opened issue in Audit Trail Details Table
			fnGet_Element_Enabled("footer");
			fnGet_Element_Enabled("ViewEscalation_AuditTrailDetails_Table_AfterColumnXpath");
			String audit_trail_table=fnGet_Field_TEXT("ViewEscalation_AuditTrailDetails_Table_AfterColumnXpath");
			try{
		
				assert (!(audit_trail_table.contains(issue_id_red)) && !(audit_trail_table.contains(issue_id_amber)) && !(audit_trail_table.contains(issue_id_green))):"FAILED == Escalation's audit trail Display the issues id into AfterColumn fo Audit Trail Table even though the Escalation is dismentled, Please refer ScreenShot [ AuditTrailRemovedIssueDisplayFail"+fnScreenShot_Date_format()+" ]";	
				fnApps_Report_Logs("PASSED == Audit Trail successfully Verified and it is working as excepted.");
			}catch(Throwable t){
				fnTake_Screen_Shot("AuditTrailRemovedIssueDisplayFail");
				fnApps_Report_Logs(Throwables.getStackTraceAsString(t));
				throw new Exception(Throwables.getStackTraceAsString(t));}
			
			fnSearchIssue_Ajax_Link_Click();
			fnGet_Element_Enabled("footer");
			fnsLoading_Progressingwait(1);
		
			fncEscalation_Verify_First_IssueClosed_after_EscalationClosed("SearchIssue_Escalation_Status",issue_id_amber, "AMBER" , false);
			fncEscalation_Verify_First_IssueClosed_after_EscalationClosed("SearchIssue_Escalation_Status",issue_id_red, "AMBER" , false);
}	
	
	
	
	
	
	
	
	
	
	

	@Test(dependsOnMethods={"EditEsc_Verify_EsclationDismentled_and_VerifyCorrectAuditTrailEntry_thenVerify_OtherTwoIssuesColor"}, priority = 13, description="  [Create Issue ID with Green Color]")
	public void Escalation_PRQ_CreateNewIssue_WithSameFirstIssueGreenDetails_and_Verify_EscReGenerates_IncludingOldTwoIssues() throws Throwable{
		
		fnApps_Report_Logs("=========================================================================================================================================");	
		fnApps_Report_Logs("########## Test Case ::::13 Escalation_PRQ_CreateNewIssue_WithSameFirstIssueGreenDetails_and_Verify_EscReGenerates_IncludingOldTwoIssues ");
		
		
		  
//		IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//src//nsf//ecap//xls//IssueMgt_Suite.xlsx");
			
		//Clicking on CreateIssue Ajax Link
		fnCreateIssue_Ajax_Link_Click();
		
		//Site Details page data entered and clicking on next button
		fnVerify_SiteDetailsPage_Data_Entered_and_NavigateTo_SummaryDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_PRQ_DeleteIssue", 2, 2), IssueMgt_Suitexls.getCellData("Escalation_PRQ_DeleteIssue", 2, 3));
	
		//Summary Details page data entered and clicking on next button
		fnVerify_SummaryDetailsPage_Data_Entered_and_NavigateTo_CustomerDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_PRQ_DeleteIssue", 2, 6), IssueMgt_Suitexls.getCellData("Escalation_PRQ_DeleteIssue", 2, 7), IssueMgt_Suitexls.getCellData("Escalation_PRQ_DeleteIssue", 2, 8), fnGet_2Days_Past_date(), TitleShortDescriptionText);
	
		//Clicking on Customer  NEXT Button
		fnClick_UnTill_Navigate_to_NextPage("CreateIssue_Customer_next_bttn","CreateIssue_Customer_Name_TextField","CreateIssue_product_code_bttn");	
		
		//Product Details page data entered and clicking on next button
		fnVerify_ProductDetailsPage_Data_Entered_and_NavigateTo_DocumentsDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_PRQ_DeleteIssue", 2, 9));
		
	
		// Clicking on Document next button
		fnClick_UnTill_Navigate_to_NextPage("CreateIssue_document_next_bttn","CreateIssue_document_Add_Attachement","CreateIssue_confirmation_submit_bttn");		
		
		// Clicking on Confirmation Submit button
		fnWait_and_Click("CreateIssue_confirmation_submit_bttn");
		
		//Verify issue ID Generated and Color is as per Expected and Stored into String
		issue_id_green=fnVerified_IssueID_Generated_and_Color_Verification(1, "RED");
		
		
		
		
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
			
}

	
	
	
	
	
	
	
	
	
	
	

	@Test(dependsOnMethods={"Escalation_PRQ_CreateNewIssue_WithSameFirstIssueGreenDetails_and_Verify_EscReGenerates_IncludingOldTwoIssues"}, priority = 14, description="  [Change the status from >Initial Record >SATISFACTORY CLOSURE. Mention new status response>Click save. Check -Click on Assign to me Button.]")
	public void EditEsc_CloseEscalation_and_Verify_AllIssueIdsClosed() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("####### Test Case ::::14 Escalation_EditEscalation_Change_status_InitialRecord_to_SatisfactoryClosure");
		WebDriverWait waitvar = new WebDriverWait(driver, Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
		
	
		
		// Select Technologist Type by clicking on radio button
		fnGet_Element_Enabled("Escalation_Summary_assign_to_me_button");
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