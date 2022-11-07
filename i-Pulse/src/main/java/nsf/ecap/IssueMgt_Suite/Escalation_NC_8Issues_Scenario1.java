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

public class Escalation_NC_8Issues_Scenario1 extends TestSuiteBase_IM{
	
	public static String issue_id_green_one;//="I0040317";
	public static String issue_id_amber_two;//="I0040318";
	public static String issue_id_amber_three;//="I0040319";
	public static String issue_id_amber_four;//="I0040320";
	public static String issue_id_amber_five;//="I0040321";
	public static String issue_id_amber_six;//="I0040322";
	public static String issue_id_amber_seven;//="I0040323";
	public static String issue_id_red_eight;//="I0040324";
	
	public static String Escalation_Id;//="E0003370";
	public static String NEW_Escalation_Id;//="E0003370";
	public static String validation_error_msg_text;
	public static String TitleShortDescriptionText="EscNC_8Issues_S1[BS-09], D/T=";
	
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
	
	
	
	
	
	
	
	@BeforeClass (description="OracleQueryLaunch_ToClosed_OpenIssues_and_InActiveAlerts_Whose --- productCode(501214), Issue_Type(NC) and Client(NSF-CMiRetailTestingClient")
	public void OracleQueryLaunch_ToClosed_OpenIssues_and_InActiveAlerts() throws Throwable {

		//Function Called to close all issues in database
		fnUpdateDB_to_Close_issues();
		
	}
	
	
	
	

@Test( priority = 0)
public void Launch_Browser_and_Successfully_Login_into_the_Application() throws Throwable{
	fnApps_Report_Logs("################################## [BS-09]  Create Escalation NC Eight Issues Scenario");
	if (!IsBrowserPresentAlready) {
		IsBrowserPresentAlready = fnLaunchBrowserAndLogin();
		fnApps_Report_Logs("Browser is launched and ******** Successfully login into 'I-Pulse' Application.");
	}
}
	
	
	
	
	@Test(dependsOnMethods={"Launch_Browser_and_Successfully_Login_into_the_Application"}, priority = 1, description="  [Create Issue ID with Green Color]")
	public void Escalation_NC_FirstIssue_Green() throws Throwable{
		
			
		fnApps_Report_Logs("################### Test Case ::::::1 Escalation_NC_FirstIssue_Green ");
	
	
		  
///		IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//src//nsf//ecap//xls//IssueMgt_Suite.xlsx");
			
		//Clicking on CreateIssue Ajax Link
		fnCreateIssue_Ajax_Link_Click();
		
		//Site Details page data entered and clicking on next button
		fnVerify_SiteDetailsPage_Data_Entered_and_NavigateTo_SummaryDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_NC", 2, 2), IssueMgt_Suitexls.getCellData("Escalation_EightIssues_NC", 2, 2));
	
		//Summary Details page data entered and clicking on next button
		fnVerify_SummaryDetailsPage_Data_Entered_and_NavigateTo_CustomerDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_NC", 2, 6), IssueMgt_Suitexls.getCellData("Escalation_NC", 2, 7), IssueMgt_Suitexls.getCellData("Escalation_NC", 2, 8), IssueMgt_Suitexls.getCellData("Escalation_EightIssues_NC", 2, 3), TitleShortDescriptionText);
	
		//Clicking on Customer  NEXT Button
		fnClick_UnTill_Navigate_to_NextPage("CreateIssue_Customer_next_bttn","CreateIssue_Customer_Name_TextField","CreateIssue_product_code_bttn");	
		
		//Product Details page data entered and clicking on next button
		fnVerify_ProductDetailsPage_Data_Entered_and_NavigateTo_DocumentsDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_NC", 2, 9));
		
	
		// Clicking on Document next button
		fnClick_UnTill_Navigate_to_NextPage("CreateIssue_document_next_bttn","CreateIssue_document_Add_Attachement","CreateIssue_confirmation_submit_bttn");	
		
		
		// Clicking on Confirmation Submit button
		fnWait_and_Click("CreateIssue_confirmation_submit_bttn");
	
		
		//Verify issue ID Generated and Color is as per Expected and Stored into String
		issue_id_green_one=fnVerified_IssueID_Generated_and_Color_Verification(1, "GREEN");
		
}
	
	
	
	
	
	
	
	
	
	@Test(dependsOnMethods={"Escalation_NC_FirstIssue_Green"}, priority = 2, description="  [Create Issue ID with Amber color]")
	public void Escalation_NC_SecondIssue_Amber() throws Throwable{
		
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::2 Escalation_NC_SecondIssue_Amber ");
	//	IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//src//nsf//ecap//xls//IssueMgt_Suite.xlsx");
		

	
		//Clicking on CreateIssue Ajax Link
		fnCreateIssue_Ajax_Link_Click();
		
		//Site Details page data entered and clicking on next button
		fnVerify_SiteDetailsPage_Data_Entered_and_NavigateTo_SummaryDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_NC", 2, 2),IssueMgt_Suitexls.getCellData("Escalation_EightIssues_NC", 2, 4));
	
		//Summary Details page data entered and clicking on next button
		fnVerify_SummaryDetailsPage_Data_Entered_and_NavigateTo_CustomerDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_NC", 2, 6), IssueMgt_Suitexls.getCellData("Escalation_NC", 2, 7), IssueMgt_Suitexls.getCellData("Escalation_NC", 2, 8), IssueMgt_Suitexls.getCellData("Escalation_EightIssues_NC", 2, 5), TitleShortDescriptionText);


		//Clicking on Customer  NEXT Button
		fnClick_UnTill_Navigate_to_NextPage("CreateIssue_Customer_next_bttn","CreateIssue_Customer_Name_TextField","CreateIssue_product_code_bttn");	
		
		//Product Details page data entered and clicking on next button
		fnVerify_ProductDetailsPage_Data_Entered_and_NavigateTo_DocumentsDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_NC", 2, 9));
		
	
		// Clicking on Document next button
		fnClick_UnTill_Navigate_to_NextPage("CreateIssue_document_next_bttn","CreateIssue_document_Add_Attachement","CreateIssue_confirmation_submit_bttn");	
		
		// Clicking on Confirmation Submit button
		fnWait_and_Click("CreateIssue_confirmation_submit_bttn");
		
	
		
		//Verify issue ID Generated and Color is as per Expected and Stored into String
		issue_id_amber_two=fnVerified_IssueID_Generated_and_Color_Verification(2, "AMBER");
		
}

	
	
	
	
	
	
	

	
	
	
	
	@Test(dependsOnMethods={"Escalation_NC_SecondIssue_Amber"}, priority = 3, description="  [Create Issue ID with Amber color]")
	public void Escalation_NC_ThirdIssue_Amber() throws Throwable{
		
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::3 Escalation_NC_ThirdIssue_Amber ");
	//	IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//src//nsf//ecap//xls//IssueMgt_Suite.xlsx");
		

	
		//Clicking on CreateIssue Ajax Link
		fnCreateIssue_Ajax_Link_Click();
		
		//Site Details page data entered and clicking on next button
		fnVerify_SiteDetailsPage_Data_Entered_and_NavigateTo_SummaryDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_NC", 2, 2), IssueMgt_Suitexls.getCellData("Escalation_EightIssues_NC", 2, 6));
	
		//Summary Details page data entered and clicking on next button
		fnVerify_SummaryDetailsPage_Data_Entered_and_NavigateTo_CustomerDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_NC", 2, 6), IssueMgt_Suitexls.getCellData("Escalation_NC", 2, 7), IssueMgt_Suitexls.getCellData("Escalation_NC", 2, 8), IssueMgt_Suitexls.getCellData("Escalation_EightIssues_NC", 2, 7), TitleShortDescriptionText);
	

		//Clicking on Customer  NEXT Button
		fnClick_UnTill_Navigate_to_NextPage("CreateIssue_Customer_next_bttn","CreateIssue_Customer_Name_TextField","CreateIssue_product_code_bttn");	
		
		//Product Details page data entered and clicking on next button
		fnVerify_ProductDetailsPage_Data_Entered_and_NavigateTo_DocumentsDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_NC", 2, 9));
		
	
		// Clicking on Document next button
		fnClick_UnTill_Navigate_to_NextPage("CreateIssue_document_next_bttn","CreateIssue_document_Add_Attachement","CreateIssue_confirmation_submit_bttn");	
		
		
		// Clicking on Confirmation Submit button
		fnWait_and_Click("CreateIssue_confirmation_submit_bttn");
		
	
		
		//Verify issue ID Generated and Color is as per Expected and Stored into String
		issue_id_amber_three=fnVerified_IssueID_Generated_and_Color_Verification(3, "AMBER");
		
}
	
	
	
	
	
	
	
	

	@Test(dependsOnMethods={"Escalation_NC_ThirdIssue_Amber"}, priority = 4, description="  [Create Issue ID with Amber color]")
	public void Escalation_NC_FourthIssue_Amber() throws Throwable{
		
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::4 Escalation_NC_FourthIssue_Amber ");
//		IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//src//nsf//ecap//xls//IssueMgt_Suite.xlsx");
		

	
		//Clicking on CreateIssue Ajax Link
		fnCreateIssue_Ajax_Link_Click();
		
		//Site Details page data entered and clicking on next button
		fnVerify_SiteDetailsPage_Data_Entered_and_NavigateTo_SummaryDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_NC", 2, 2),IssueMgt_Suitexls.getCellData("Escalation_EightIssues_NC", 2, 8));
	
		//Summary Details page data entered and clicking on next button
		fnVerify_SummaryDetailsPage_Data_Entered_and_NavigateTo_CustomerDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_NC", 2, 6), IssueMgt_Suitexls.getCellData("Escalation_NC", 2, 7), IssueMgt_Suitexls.getCellData("Escalation_NC", 2, 8), IssueMgt_Suitexls.getCellData("Escalation_EightIssues_NC", 2, 9), TitleShortDescriptionText);


		//Clicking on Customer  NEXT Button
		fnClick_UnTill_Navigate_to_NextPage("CreateIssue_Customer_next_bttn","CreateIssue_Customer_Name_TextField","CreateIssue_product_code_bttn");	
		
		//Product Details page data entered and clicking on next button
		fnVerify_ProductDetailsPage_Data_Entered_and_NavigateTo_DocumentsDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_NC", 2, 9));
		
	
		// Clicking on Document next button
		fnClick_UnTill_Navigate_to_NextPage("CreateIssue_document_next_bttn","CreateIssue_document_Add_Attachement","CreateIssue_confirmation_submit_bttn");		
		
		
		// Clicking on Confirmation Submit button
		fnWait_and_Click("CreateIssue_confirmation_submit_bttn");
		
	
		
		//Verify issue ID Generated and Color is as per Expected and Stored into String
		issue_id_amber_four=fnVerified_IssueID_Generated_and_Color_Verification(4, "AMBER");
		
}


	
	
	
	
	
	
	
	
	

	@Test(dependsOnMethods={"Escalation_NC_FourthIssue_Amber"}, priority = 5, description="  [Create Issue ID with Amber color]")
	public void Escalation_NC_FifthIssue_Amber() throws Throwable{
		
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::5 Escalation_NC_FifthIssue_Amber ");
	//	IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//src//nsf//ecap//xls//IssueMgt_Suite.xlsx");
		

	
		//Clicking on CreateIssue Ajax Link
		fnCreateIssue_Ajax_Link_Click();
		
		//Site Details page data entered and clicking on next button
		fnVerify_SiteDetailsPage_Data_Entered_and_NavigateTo_SummaryDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_NC", 2, 2), IssueMgt_Suitexls.getCellData("Escalation_EightIssues_NC", 2, 10));
	
		//Summary Details page data entered and clicking on next button
		fnVerify_SummaryDetailsPage_Data_Entered_and_NavigateTo_CustomerDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_NC", 2, 6), IssueMgt_Suitexls.getCellData("Escalation_NC", 2, 7), IssueMgt_Suitexls.getCellData("Escalation_NC", 2, 8), IssueMgt_Suitexls.getCellData("Escalation_EightIssues_NC", 2, 11), TitleShortDescriptionText);


		//Clicking on Customer  NEXT Button
		fnClick_UnTill_Navigate_to_NextPage("CreateIssue_Customer_next_bttn","CreateIssue_Customer_Name_TextField","CreateIssue_product_code_bttn");	
		
		//Product Details page data entered and clicking on next button
		fnVerify_ProductDetailsPage_Data_Entered_and_NavigateTo_DocumentsDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_NC", 2, 9));
		
	
		// Clicking on Document next button
		fnClick_UnTill_Navigate_to_NextPage("CreateIssue_document_next_bttn","CreateIssue_document_Add_Attachement","CreateIssue_confirmation_submit_bttn");	
		
		
		// Clicking on Confirmation Submit button
		fnWait_and_Click("CreateIssue_confirmation_submit_bttn");
		
	
		
		//Verify issue ID Generated and Color is as per Expected and Stored into String
		issue_id_amber_five=fnVerified_IssueID_Generated_and_Color_Verification(5, "AMBER");
		
}

	

	
	

	@Test(dependsOnMethods={"Escalation_NC_FifthIssue_Amber"}, priority = 6, description="  [Create Issue ID with Amber color]")
	public void Escalation_NC_SixthIssue_Amber() throws Throwable{
		
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::6 Escalation_NC_SixthIssue_Amber ");
	//	IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//src//nsf//ecap//xls//IssueMgt_Suite.xlsx");
		

	
		//Clicking on CreateIssue Ajax Link
		fnCreateIssue_Ajax_Link_Click();
		
		//Site Details page data entered and clicking on next button
		fnVerify_SiteDetailsPage_Data_Entered_and_NavigateTo_SummaryDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_NC", 2, 2), IssueMgt_Suitexls.getCellData("Escalation_EightIssues_NC", 2, 12));
	
		//Summary Details page data entered and clicking on next button
		fnVerify_SummaryDetailsPage_Data_Entered_and_NavigateTo_CustomerDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_NC", 2, 6), IssueMgt_Suitexls.getCellData("Escalation_NC", 2, 7), IssueMgt_Suitexls.getCellData("Escalation_NC", 2, 8), IssueMgt_Suitexls.getCellData("Escalation_EightIssues_NC", 2, 13), TitleShortDescriptionText);
	


		//Clicking on Customer  NEXT Button
		fnClick_UnTill_Navigate_to_NextPage("CreateIssue_Customer_next_bttn","CreateIssue_Customer_Name_TextField","CreateIssue_product_code_bttn");	
		
		//Product Details page data entered and clicking on next button
		fnVerify_ProductDetailsPage_Data_Entered_and_NavigateTo_DocumentsDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_NC", 2, 9));
		
	
		// Clicking on Document next button
		fnClick_UnTill_Navigate_to_NextPage("CreateIssue_document_next_bttn","CreateIssue_document_Add_Attachement","CreateIssue_confirmation_submit_bttn");	
		
		
		// Clicking on Confirmation Submit button
		fnWait_and_Click("CreateIssue_confirmation_submit_bttn");
		
	
		
		//Verify issue ID Generated and Color is as per Expected and Stored into String
		issue_id_amber_six=fnVerified_IssueID_Generated_and_Color_Verification(6, "AMBER");
		
}

	

	
	
	
	
	
	
	
	
	
	
	

	@Test(dependsOnMethods={"Escalation_NC_SixthIssue_Amber"}, priority = 7, description="  [Create Issue ID with Amber color]")
	public void Escalation_NC_SeventhIssue_Amber() throws Throwable{
		
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::7 Escalation_NC_SeventhIssue_Amber ");
	//	IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//src//nsf//ecap//xls//IssueMgt_Suite.xlsx");
		
		
	
		//Clicking on CreateIssue Ajax Link
		fnCreateIssue_Ajax_Link_Click();
		
		//Site Details page data entered and clicking on next button
		fnVerify_SiteDetailsPage_Data_Entered_and_NavigateTo_SummaryDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_NC", 2, 2), IssueMgt_Suitexls.getCellData("Escalation_EightIssues_NC", 2, 14));
	
		//Summary Details page data entered and clicking on next button
		fnVerify_SummaryDetailsPage_Data_Entered_and_NavigateTo_CustomerDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_NC", 2, 6), IssueMgt_Suitexls.getCellData("Escalation_NC", 2, 7), IssueMgt_Suitexls.getCellData("Escalation_NC", 2, 8), IssueMgt_Suitexls.getCellData("Escalation_EightIssues_NC", 2, 15), TitleShortDescriptionText);


		//Clicking on Customer  NEXT Button
		fnClick_UnTill_Navigate_to_NextPage("CreateIssue_Customer_next_bttn","CreateIssue_Customer_Name_TextField","CreateIssue_product_code_bttn");	
		
		//Product Details page data entered and clicking on next button
		fnVerify_ProductDetailsPage_Data_Entered_and_NavigateTo_DocumentsDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_NC", 2, 9));
		
	
		// Clicking on Document next button
		fnClick_UnTill_Navigate_to_NextPage("CreateIssue_document_next_bttn","CreateIssue_document_Add_Attachement","CreateIssue_confirmation_submit_bttn");	
		
		
		// Clicking on Confirmation Submit button
		fnWait_and_Click("CreateIssue_confirmation_submit_bttn");
		
	
		
		//Verify issue ID Generated and Color is as per Expected and Stored into String
		issue_id_amber_seven=fnVerified_IssueID_Generated_and_Color_Verification(7, "AMBER");
		
}

	
	

	
	
	
	
	
	
	
	
	
	
	
	

	
	@Test(dependsOnMethods={"Escalation_NC_SeventhIssue_Amber"}, priority = 8, description="  [Create Issue ID with RED color]")
	public void Escalation_NC_EighthIssue_Red() throws Throwable{
		
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::8 Escalation_NC_EighthIssue_Red ");
//		IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//src//nsf//ecap//xls//IssueMgt_Suite.xlsx");
		

		
		//Clicking on CreateIssue Ajax Link
		fnCreateIssue_Ajax_Link_Click();
		
		//Site Details page data entered and clicking on next button
		fnVerify_SiteDetailsPage_Data_Entered_and_NavigateTo_SummaryDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_NC", 2, 2), IssueMgt_Suitexls.getCellData("Escalation_EightIssues_NC", 2, 16));
	
		//Summary Details page data entered and clicking on next button
		fnVerify_SummaryDetailsPage_Data_Entered_and_NavigateTo_CustomerDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_NC", 2, 6), IssueMgt_Suitexls.getCellData("Escalation_NC", 2, 7), IssueMgt_Suitexls.getCellData("Escalation_NC", 2, 8), IssueMgt_Suitexls.getCellData("Escalation_EightIssues_NC", 2, 17), TitleShortDescriptionText);


		//Clicking on Customer  NEXT Button
		fnClick_UnTill_Navigate_to_NextPage("CreateIssue_Customer_next_bttn","CreateIssue_Customer_Name_TextField","CreateIssue_product_code_bttn");	
		
		//Product Details page data entered and clicking on next button
		fnVerify_ProductDetailsPage_Data_Entered_and_NavigateTo_DocumentsDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_NC", 2, 9));
		
	
		// Clicking on Document next button
		fnClick_UnTill_Navigate_to_NextPage("CreateIssue_document_next_bttn","CreateIssue_document_Add_Attachement","CreateIssue_confirmation_submit_bttn");	
		
		
		// Clicking on Confirmation Submit button
		fnWait_and_Click("CreateIssue_confirmation_submit_bttn");
		

	
		
		//Verify issue ID Generated and Color is as per Expected and Stored into String
		issue_id_red_eight=fnVerified_IssueID_Generated_and_Color_Verification(8, "RED");
		
}

	

	
	

	
	@Test(dependsOnMethods={"Escalation_NC_EighthIssue_Red"}, priority = 9, description="  [Verify Escalations ID Generated with 8 issues.]")
	public void Escalation_NC_Generated_With_8_Issues() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::9 Escalation_NC_Generated_With_8_Issues ");
		
		//IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//src//nsf//ecap//xls//IssueMgt_Suite.xlsx");

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
				assert ((related_issue_table.contains(issue_id_green_one)) && related_issue_table.contains(issue_id_amber_two) && related_issue_table.contains(issue_id_amber_three)  && related_issue_table.contains(issue_id_amber_four) && related_issue_table.contains(issue_id_amber_five) && related_issue_table.contains(issue_id_amber_six) && related_issue_table.contains(issue_id_amber_seven) && related_issue_table.contains(issue_id_red_eight) ):
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
	
	
	
	
	
	
	
	

	@Test(dependsOnMethods={"Escalation_NC_Generated_With_8_Issues"}, priority = 10, description="  [Edit Issue8 SubType and Verify Escalation Dismantled and there are no issue exists into Escalation.]")
	public void Escalation_NC_Edit_05NOV_DateIssueSubType_Verify_Escalation_Dismantled() throws Throwable{

		try{	
			fnApps_Report_Logs("=========================================================================================================================================");
			fnApps_Report_Logs("################### Test Case ::::::10 Escalation_NC_Edit_05NOV_DateIssueSubType_Verify_Escalation_Dismantled.");
		//	IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//src//nsf//ecap//xls//IssueMgt_Suite.xlsx");
			//Search IssueID and also navigate to ViewIssue Summary Details page 
			fncIssueID_SearchIssueID_and_Navigate_to_ViewIssueSummaryDetailsPageComes(issue_id_red_eight, true);
			fncIssue_DetailsTAB_RadioButtonsSelect_and_Verify_SaveSuccessfully();
			//Clicking on Summary TAB on View issue page
			fnGet_Element_Enabled("ViewIssueSummaryDetails_summary_detail_tab");
			fnWait_and_Click("ViewIssueSummaryDetails_summary_detail_tab");
			
			//Wait till IssueSubType DD enabled
			fnGet_Element_Enabled("ViewIssueSummaryDetails_IssueSubType_dd_click");
			
			//Select IssueSubType From Drop Down
			fnDD_value_Select("ViewIssueSummaryDetails_IssueSubType_dd_click", "ViewIssueSummaryDetails_IssueSubType_dd_value", "li", IssueMgt_Suitexls.getCellData("Escalation_EightIssues_NC", 2, 18));
			
			
			
	
			//Click on Save Button from ViewIssueDetailsPage and verify updated message
			fnGet_Element_Enabled("ViewIssueSummaryDetails_summary_details_save_button");
			fnWait_and_Click("ViewIssueSummaryDetails_summary_details_save_button");
			fnsLoading_Progressingwait(3);
			
			fnGet_Element_Enabled("validation_error_msg");
			fncVerify_DataUpdated_Successfully("validation_error_msg", "Summary TAB");	
			
			fnGet_Element_Enabled("footer");
			
					
			String get_issue_id=fnGet_Field_TEXT("issue_id").trim();
			
			try{
				assert get_issue_id.contains("GREEN"):"FAILED == Color is not GREEN >> "+get_issue_id+"  ,Please refer ScreenShot [ "+issue_id_red_eight+fnScreenShot_Date_format()+" ].";
				fnApps_Report_Logs("PASSED == Issue ID Color is GREEN === "+get_issue_id);			
			}
			catch(Throwable t){
				fnTake_Screen_Shot(issue_id_red_eight);
				throw new Exception(Throwables.getStackTraceAsString(t));}
			
			
			//Verify Escalation Dismantled
			//Search Escalation and also navigate to ViewEscalation EscalationDetails page
			fncEscalation_SearchEscalation_and_Navigate_till_ViewEscalationDetailsPageComes(Escalation_Id);
			
			fnGet_Element_Enabled("footer");
			
	
			try{
				fnGet_Element_Enabled("ViewEscalation_RelatedIssues_table_NoRecordsfound");
				Thread.sleep(500);
				String NoRecordFound_column_text=fnGet_Field_TEXT("ViewEscalation_RelatedIssues_table_NoRecordsfound").trim();
				
				assert NoRecordFound_column_text.contains("records"):"FAILED == Escalation has not been dismantled and still there issue ids are displayed into Related Issues table on View Escalation Page."+
					", Please refer screenshot >> ErrorMsgNotCominginRelatedIssuesTable"+fnScreenShot_Date_format();
				fnApps_Report_Logs("PASSED == Escalation has been dismantled and there are no issue ids displayed into Related Issues table on View Escalation Page.");
									
			}catch(Throwable t) {
				fnTake_Screen_Shot("ErrorMsgNotCominginRelatedIssuesTable");
				throw new Exception(Throwables.getStackTraceAsString(t));		
				}
	
		}catch(Throwable t) {
			isTestCasePass = false;
			fnApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));		
		}
}	
	
	
	
	
	
	
	
	
	
	
	
	
	

	@Test(dependsOnMethods={"Escalation_NC_Edit_05NOV_DateIssueSubType_Verify_Escalation_Dismantled"}, priority = 11, description="  [Edit Issue8 SubType back to original and Verify new Escalation is generated with new escalation Id and all 8 issues exists int Escalation.]")
	public void Escalation_NC_Edit_05NOV_DateIssueSubType_BackToOriginal_and_Verify_NEW_Escalation_ID_Generated() throws Throwable{

		try{		
			fnApps_Report_Logs("=========================================================================================================================================");
			fnApps_Report_Logs("################### Test Case ::::::11 Escalation_NC_Edit_05NOV_DateIssueSubType_BackToOriginal_and_Verify_NEW_Escalation_ID_Generated ");
		//	IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//src//nsf//ecap//xls//IssueMgt_Suite.xlsx");
			
		//	IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//src//nsf//ecap//xls//IssueMgt_Suite.xlsx");
			
			//Search IssueID and also navigate to ViewIssue Summary Details page 
			fncIssueID_SearchIssueID_and_Navigate_to_ViewIssueSummaryDetailsPageComes(issue_id_red_eight, true);
			
			//Clicking on Summary TAB on View issue page
			fnGet_Element_Enabled("ViewIssueSummaryDetails_summary_detail_tab");
			fnWait_and_Click("ViewIssueSummaryDetails_summary_detail_tab");
			
			
			//Wait till IssueSubType DD enabled
			fnGet_Element_Enabled("ViewIssueSummaryDetails_IssueSubType_dd_click");
			
			//Select IssueSubType From Drop Down
			fnDD_value_Select("ViewIssueSummaryDetails_IssueSubType_dd_click", "ViewIssueSummaryDetails_IssueSubType_dd_value", "li",  IssueMgt_Suitexls.getCellData("Escalation_NC", 2, 8));
			
			
			
	
			//Click on Save Button from ViewIssueDetailsPage and verify updated message
			fnGet_Element_Enabled("ViewIssueSummaryDetails_summary_details_save_button");
			fnWait_and_Click("ViewIssueSummaryDetails_summary_details_save_button");
			fnsLoading_Progressingwait(3);
			
			fnGet_Element_Enabled("validation_error_msg");
			fncVerify_DataUpdated_Successfully("validation_error_msg", "Summary TAB");	
			
			fnGet_Element_Enabled("footer");
			
					
			String get_issue_id=fnGet_Field_TEXT("issue_id").trim();
	
		
			try{
				assert get_issue_id.contains("RED"):"FAILED == Color is not RED >> "+get_issue_id+"  ,Please refer ScreenShot [ issue_id_Eight_Not_red"+fnScreenShot_Date_format()+" ].";
				fnApps_Report_Logs("PASSED == Issue ID Color is RED === "+get_issue_id);			
			}
			catch(Throwable t){
					fnTake_Screen_Shot("issue_id_Eight_Not_red");
					throw new Exception(Throwables.getStackTraceAsString(t));
			}
			
			
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
				assert ((related_issue_table.contains(issue_id_green_one)) && related_issue_table.contains(issue_id_amber_two) && related_issue_table.contains(issue_id_amber_three)  && related_issue_table.contains(issue_id_amber_four) && related_issue_table.contains(issue_id_amber_five) && related_issue_table.contains(issue_id_amber_six) && related_issue_table.contains(issue_id_amber_seven) && related_issue_table.contains(issue_id_red_eight) ):
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
	
}
	
	

	
	
	

	@Test(dependsOnMethods={"Escalation_NC_Edit_05NOV_DateIssueSubType_BackToOriginal_and_Verify_NEW_Escalation_ID_Generated"}, priority = 12, description="  [Change the status from >Initial Record >SATISFACTORY CLOSURE. Mention new status response>Click save. Check -Click on Assign to me Button.]")
	public void Escalation_EditEscalation_Change_status_InitialRecord_to_SatisfactoryClosure() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("####### Test Case ::::12 Escalation_EditEscalation_Change_status_InitialRecord_to_SatisfactoryClosure");

		// Select Technologist Type by clicking on radio button
		fnWait_and_Click("Escalation_Summary_assign_to_me_button");
		fnsLoading_Progressingwait(3);
			
		fncEscalation_VerifyChange_Status_on_SummaryDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_PRQ", 2, 26));
		
		fnGet_Element_Enabled("validation_error_msg");
		fncVerify_DataUpdated_Successfully("validation_error_msg", "Summary TAB");	
	}
	
	
	
	
	
	
	
	
	


	@Test(dependsOnMethods={"Escalation_EditEscalation_Change_status_InitialRecord_to_SatisfactoryClosure"}, priority = 13, description="  [Verify that all issues are closed which are related to Escalation as the escalation is closed.]")
	public void Escalation_Verify_AllIssuesClosed_As_EscalationClosed() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::13 Escalation_Verify_AllIssuesClosed_As_EscalationClosed ");


		fnSearchIssue_Ajax_Link_Click();
		fnGet_Element_Enabled("footer");
		fnsLoading_Progressingwait(1);
	
		fncEscalation_Verify_First_IssueClosed_after_EscalationClosed("SearchIssue_Parent_Status",issue_id_green_one, "CLOSED" , false);
		fncEscalation_Verify_First_IssueClosed_after_EscalationClosed("SearchIssue_Parent_Status",issue_id_amber_two, "CLOSED" , false);
		fncEscalation_Verify_First_IssueClosed_after_EscalationClosed("SearchIssue_Parent_Status",issue_id_amber_three, "CLOSED" , false);
		fncEscalation_Verify_First_IssueClosed_after_EscalationClosed("SearchIssue_Parent_Status",issue_id_amber_four, "CLOSED" , false);
		fncEscalation_Verify_First_IssueClosed_after_EscalationClosed("SearchIssue_Parent_Status",issue_id_amber_five, "CLOSED" , false);
		fncEscalation_Verify_First_IssueClosed_after_EscalationClosed("SearchIssue_Parent_Status",issue_id_amber_six, "CLOSED" , false);
		fncEscalation_Verify_First_IssueClosed_after_EscalationClosed("SearchIssue_Parent_Status",issue_id_amber_seven, "CLOSED" , false);
		fncEscalation_Verify_First_IssueClosed_after_EscalationClosed("SearchIssue_Parent_Status",issue_id_red_eight, "CLOSED" , false);

		fnApps_Report_Logs("##################################### [BS - 09]  CreateEscalation_NC_8Issues_Scenario1 Script END ##################################### ");
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
