
package nsf.ecap.IssueMgt_Suite;

import java.util.HashMap;
import java.util.Properties;


//
import nsf.ecap.util.*;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.util.HSSFColor.GREEN;
import org.openqa.selenium.By;
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

public class Escalation_FourIssues extends TestSuiteBase_IM{
	
	public static String issue_id_green;//="I0039874";
	public static String issue_id_amber_one;//="I0039875";
	public static String issue_id_amber_two;//="I0039875";
	public static String issue_id_red;//="I0039876";
	public static String Escalation_Id;//="E0003283";
	public static String validation_error_msg_text;
	public static String TitleShortDescriptionText="EscPRQ_4Issues[BS-04], D/T=";
	
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
	
	
	
	
	
	
	
	@BeforeClass (description="OracleQueryLaunch_ToClosed_OpenIssues_and_InActiveAlerts_Whose --- productCode(501214), Issue_Type(PRQ) and Client(NSF-CMiRetailTestingClient")
	public void OracleQueryLaunch_ToClosed_OpenIssues_and_InActiveAlerts() throws Throwable {

		//Function Called to close all issues in database
		fnUpdateDB_to_Close_issues();
		
	}
	
	
	
	

@Test( priority = 0)
public void Launch_Browser_and_Successfully_Login_into_the_Application() throws Throwable{
	fnApps_Report_Logs("################################## [BS-04]  Create Escalation PRQ Four Issues");
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
		fnWait_and_Click("CreateIssue_confirmation_submit_bttn");
		
		
	
	
		
		//Verify issue ID Generated and Color is as per Expected and Stored into String
		issue_id_green=fnVerified_IssueID_Generated_and_Color_Verification(1, "GREEN");
		
}
	
	
	
	
	
	
	
	
	
	
	
	@Test(dependsOnMethods={"Escalation_PRQ_FirstIssue_Green"}, priority = 2, description="  [Create Issue ID with Amber color]")
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
		fnWait_and_Click("CreateIssue_confirmation_submit_bttn");
		
	
	
		
		//Verify issue ID Generated and Color is as per Expected and Stored into String
		issue_id_amber_one=fnVerified_IssueID_Generated_and_Color_Verification(2, "AMBER");
		
}
	
	
	
	
	
	
	
	
	

	
	
	
	
	@Test(dependsOnMethods={"Escalation_PRQ_SecondIssue_Amber"}, priority = 3, description="  [Create Issue ID with Amber color]")
	public void Escalation_PRQ_ThirdIssue_Amber() throws Throwable{
		
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::3 Escalation_PRQ_ThirdIssue_Amber ");
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
		fnWait_and_Click("CreateIssue_confirmation_submit_bttn");
		
	
	
		
		//Verify issue ID Generated and Color is as per Expected and Stored into String
		issue_id_amber_two=fnVerified_IssueID_Generated_and_Color_Verification(3, "AMBER");
		
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Test(dependsOnMethods={"Escalation_PRQ_ThirdIssue_Amber"}, priority = 4, description="  [Create Issue ID with RED color]")
	public void Escalation_PRQ_FourthIssue_Red() throws Throwable{
		
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::4 Escalation_PRQ_FourthIssue_Red ");
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
		fnWait_and_Click("CreateIssue_confirmation_submit_bttn");
		

	
	
		
		//Verify issue ID Generated and Color is as per Expected and Stored into String
		issue_id_red=fnVerified_IssueID_Generated_and_Color_Verification(4, "RED");
		
}
	
	
	
	
	
	
	
	
	
	
	
	
	
			
	@Test(dependsOnMethods={"Escalation_PRQ_FourthIssue_Red"}, priority = 5, description="  [Verify Escalations ID Generated with Four issues.]")
	public void Escalation_PRQ_Generated_With_4_Issues() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::5 Escalation_PRQ_Generated_With_4_Issues ");
		WebDriverWait waitvar = new WebDriverWait(driver, Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
//		IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//src//nsf//ecap//xls//IssueMgt_Suite.xlsx");
					
		try{				
			//Verify Escalation Id generated and stored into String.
			fnGet_Element_Enabled("Escalation_id_link");
			Thread.sleep(1000);
			Escalation_Id=fnGet_Field_TEXT("Escalation_id_link").trim();
			try{
				assert (Escalation_Id.contains("E")):"FAILED == Escalations ID is not Generated >> "+"Please refer ScreenShot [ EscalationIdNotGenrated"+fnScreenShot_Date_format() +" ]";	
			   	fnApps_Report_Logs("PASSED == Escalations ID has been generated === "+Escalation_Id);
			}catch(Throwable t){
				fnTake_Screen_Shot("EscalationIdNotGenrated");
				throw new Exception(Throwables.getStackTraceAsString(t));
			}
			
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
				assert ((related_issue_table.contains(issue_id_green)) && related_issue_table.contains(issue_id_amber_one)  && related_issue_table.contains(issue_id_amber_two) && related_issue_table.contains(issue_id_red) ):
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
	
	
	
	

	
	
	@Test(dependsOnMethods={"Escalation_PRQ_Generated_With_4_Issues"}, priority = 6, description="  [Change the status from >Initial Record >SATISFACTORY CLOSURE. Mention new status response>Click save. Check -Click on Assign to me Button.]")
	public void Escalation_EditEscalation_Change_status_InitialRecord_to_SatisfactoryClosure() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("####### Test Case ::::6 Escalation_EditEscalation_Change_status_InitialRecord_to_SatisfactoryClosure");
		WebDriverWait waitvar = new WebDriverWait(driver, Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
		

		//Search Escalation and also navigate to ViewEscalation EscalationDetails page
		fncEscalation_SearchEscalation_and_Navigate_till_ViewEscalationDetailsPageComes(Escalation_Id);
		

		// Select Technologist Type by clicking on radio button
		fnWait_and_Click("Escalation_Summary_assign_to_me_button");
		fnsLoading_Progressingwait(3);
			
		fncEscalation_VerifyChange_Status_on_SummaryDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_PRQ", 2, 26));
		
		fnGet_Element_Enabled("validation_error_msg");
		fncVerify_DataUpdated_Successfully("validation_error_msg", "Summary TAB");	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	@Test(dependsOnMethods={"Escalation_EditEscalation_Change_status_InitialRecord_to_SatisfactoryClosure"}, priority = 7, description="  [Verify that all issues are closed which are related to Escalation as the escalation is closed.]")
	public void Escalation_Verify_AllIssuesClosed_As_EscalationClosed() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::7 Escalation_Verify_AllIssuesClosed_As_EscalationClosed ");
		

		fnSearchIssue_Ajax_Link_Click();
		fnGet_Element_Enabled("footer");
		fnsLoading_Progressingwait(1);
	
		fncEscalation_Verify_First_IssueClosed_after_EscalationClosed("SearchIssue_Parent_Status",issue_id_green, "CLOSED" , false);
		fncEscalation_Verify_First_IssueClosed_after_EscalationClosed("SearchIssue_Parent_Status",issue_id_amber_one, "CLOSED" , false);
		fncEscalation_Verify_First_IssueClosed_after_EscalationClosed("SearchIssue_Parent_Status",issue_id_amber_two, "CLOSED" , false);
		fncEscalation_Verify_First_IssueClosed_after_EscalationClosed("SearchIssue_Parent_Status",issue_id_red, "CLOSED" , false);
		
		fnApps_Report_Logs("########################################### [BS - 04]  CreateEscalation_PRQ_FourIssues Script END ##################################### ");
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
	
	
	
	
	
	
	
