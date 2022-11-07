package nsf.ecap.IssueMgt_Suite;

import java.util.HashMap;
import java.util.Properties;


import nsf.ecap.util.*;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.internal.selenesedriver.FindElement;
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

public class CreateEscalation_AFP_Diff_Issues extends TestSuiteBase_IM{
	
	public static String issue_id_black="I0060392";
	public static String issue_id_amber="I0060393";
	public static String issue_id_red="I0060394";
	public static String Escalation_Id="E0005762";
	public static String NEW_Escalation_Id="";
	public static String validation_error_msg_text;
	public static String TitleShortDescriptionText="Esc_AFP_DiffIssue[BS-14], D/T=";	
	
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
	fnApps_Report_Logs("################################## [BS-14]  Create Escalation AFP Diff Issues");
	if (!IsBrowserPresentAlready) {
		IsBrowserPresentAlready = fnLaunchBrowserAndLogin();
		fnApps_Report_Logs("Browser is launched and ******** Successfully login into 'I-Pulse' Application.");
	}
}
	
	
	
	@Test(dependsOnMethods={"Launch_Browser_and_Successfully_Login_into_the_Application"}, priority = 1, description="  [Create Issue ID with Black Color]")
	public void Escalation_AFP_FirstIssue_Black() throws Throwable{
		
			
		fnApps_Report_Logs("################### Test Case ::::::1 Escalation_AFP_FirstIssue_Black ");
	//	IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//src//nsf//ecap//xls//IssueMgt_Suite.xlsx");
		
		  
				
		
		Create_AFP_Issue_Without_IssueID_Verification("2", IssueMgt_Suitexls.getCellData("Escalation_AFP_Diff_Issue", 2, 2), IssueMgt_Suitexls.getCellData("Escalation_AFP_Diff_Issue", 2, 3), TitleShortDescriptionText);
		
		
		String FetchIssueIdText=fnGet_Field_TEXT("AFP_IssueID_GetText");
		issue_id_black=(FetchIssueIdText.split("\\[")[0]).trim();
		
		
		
		try{
			assert FetchIssueIdText.contains(issue_id_black):"FAILED == First issue id  not Generated >> "+FetchIssueIdText+"  ,Please refer ScreenShot [ "+issue_id_black+fnScreenShot_Date_format()+" ].";
			fnApps_Report_Logs("PASSED == First Issue ID has been generated  === "+issue_id_black);
			
		}
		catch(Throwable t){
			isTestCasePass = false;
			fnTake_Screen_Shot(issue_id_black);
			fnApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));}
		
		
		
		
		try{
			assert (!(FetchIssueIdText.contains("black")) && !(FetchIssueIdText.contains("AMBER")) && !(FetchIssueIdText.contains("RED"))  ):"FAILED == First issue id Generated, but color is not BLACK"+" >> "+FetchIssueIdText+"  ,Please refer ScreenShot [ "+issue_id_black+" ].";
			fnApps_Report_Logs("PASSED == First Issue ID has been generated and Color is  BLACK"+" === "+FetchIssueIdText);
			}
		catch(Throwable t){
			isTestCasePass = false;
			fnTake_Screen_Shot(issue_id_black);
			fnApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));}
		
	
}
	
	
	
	
	
	
	
	
	

	@Test(dependsOnMethods={"Escalation_AFP_FirstIssue_Black"}, priority = 2, description="  [Create Second Issue ID with Amber Color]")
	public void Escalation_AFP_SecondIssue_Amber() throws Throwable{
		
		fnApps_Report_Logs("=========================================================================================================================================");	
		fnApps_Report_Logs("################### Test Case ::::::2 Escalation_AFP_SecondIssue_Amber ");
	//	IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//src//nsf//ecap//xls//IssueMgt_Suite.xlsx");
		
				
		Create_AFP_Issue_Without_IssueID_Verification("3", IssueMgt_Suitexls.getCellData("Escalation_AFP_Diff_Issue", 2, 6), IssueMgt_Suitexls.getCellData("Escalation_AFP_Diff_Issue", 2, 7), TitleShortDescriptionText);
				
		issue_id_amber=fnVerified_IssueID_Generated_and_Color_Verification(2, "AMBER");

}
		
	
	
	
	
	
	
	
	@Test(dependsOnMethods={"Escalation_AFP_SecondIssue_Amber"}, priority= 3, description="  [Edit the first issue and change its Date of Issue to 1 month back (2 issues should not fall between 28 days). Click Save.]")
	public void Escalation_ChangeFirstIssueDate_to_PastDate_of_MoreThan_28Days_from_SecondIssueDate_and_IssueColor_Verify() throws Throwable{
	
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("############ Test Case :::::3 Escalation_ChangeFirstIssueDate_to_PastDate_of_MoreThan_28Days_from_SecondIssueDate_and_IssueColor_Verify ");
		
			
		//	IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//src//nsf//ecap//xls//IssueMgt_Suite.xlsx");
				
			//Search IssueID and also navigate to ViewIssue Summary Details page 
			fncIssueID_SearchIssueID_and_Navigate_to_ViewIssueSummaryDetailsPageComes(issue_id_black, true);
			fncIssue_DetailsTAB_RadioButtonsSelect_and_Verify_SaveSuccessfully();
			//Clicking on Summary TAB on View issue page
			fnGet_Element_Enabled("ViewIssueSummaryDetails_summary_detail_tab");
			fnWait_and_Click("ViewIssueSummaryDetails_summary_detail_tab");
			
			//Edit Issue Date to 1 month back
			fnGet_Element_Enabled("ViewIssueSummaryDetails_DateTime_Issue");
			WithOut_OR_fnClear(OR_IM.getProperty("ViewIssueSummaryDetails_DateTime_Issue"));
			fnWait_and_Type("ViewIssueSummaryDetails_DateTime_Issue", IssueMgt_Suitexls.getCellData("Escalation_AFP_Diff_Issue", 2, 4));
			
			fnGet_Element_Enabled("ViewIssueSummaryDetails_summary_details_save_button");
			fnWait_and_Click("ViewIssueSummaryDetails_summary_details_save_button");
			fnsLoading_Progressingwait(3);
			

			fnGet_Element_Enabled("validation_error_msg");
			fncVerify_DataUpdated_Successfully("validation_error_msg", "Summary TAB ");	
			
			

			fnSearchIssue_Ajax_Link_Click();
			fnGet_Element_Enabled("footer");
			fnsLoading_Progressingwait(1);
		
			fncEscalation_Verify_First_IssueClosed_after_EscalationClosed("SearchIssue_Escalation_Status",issue_id_black, "" , false);
			
			fncEscalation_Verify_First_IssueClosed_after_EscalationClosed("SearchIssue_Escalation_Status",issue_id_amber, "" , false);
						
			//Verify First Issue color black
			//fnEscalation_Verify_First_IssueClosed_after_EscalationClosed("SearchIssue_Escalation_Status",issue_id_black, "");
			
			//Verify Second Issue color black
			//fnEscalation_Verify_IssueClosed_AfterFirstIssueVerification_after_EscalationClosed("SearchIssue_Escalation_Status",issue_id_amber, "");
			
	}			
	
	
	
	
	
	
	
	
	
	
	

	@Test(dependsOnMethods={"Escalation_ChangeFirstIssueDate_to_PastDate_of_MoreThan_28Days_from_SecondIssueDate_and_IssueColor_Verify"}, priority= 4, description="  [Edit the first issue again and change its Date of Issue back to the original date(ie within 28 days of both the issues).Click Save.]")
	public void Escalation_ChangeFirstIssueDate_BackTo_OriginalDate_and_IssueColor_Verify() throws Throwable{
	
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::4 Escalation_ChangeFirstIssueDate_BackTo_OriginalDate_and_IssueColor_Verify ");
	
		
	//	IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//src//nsf//ecap//xls//IssueMgt_Suite.xlsx");
			
		//Search IssueID and also navigate to ViewIssue Summary Details page 
		fncIssueID_SearchIssueID_and_Navigate_to_ViewIssueSummaryDetailsPageComes(issue_id_black, true);
		
		//Clicking on Summary TAB on View issue page
		fnGet_Element_Enabled("ViewIssueSummaryDetails_summary_detail_tab");
		fnWait_and_Click("ViewIssueSummaryDetails_summary_detail_tab");
		
		//Edit Issue Date to 1 month back
		fnGet_Element_Enabled("ViewIssueSummaryDetails_DateTime_Issue");
		WithOut_OR_fnClear(OR_IM.getProperty("ViewIssueSummaryDetails_DateTime_Issue"));
		fnWait_and_Type("ViewIssueSummaryDetails_DateTime_Issue", IssueMgt_Suitexls.getCellData("Escalation_AFP_Diff_Issue", 2, 2));
		
		fnGet_Element_Enabled("ViewIssueSummaryDetails_summary_details_save_button");
		fnWait_and_Click("ViewIssueSummaryDetails_summary_details_save_button");
		fnsLoading_Progressingwait(3);
		

		fnGet_Element_Enabled("validation_error_msg");
		fncVerify_DataUpdated_Successfully("validation_error_msg", "Summary TAB ");	
		

		fnSearchIssue_Ajax_Link_Click();
		fnGet_Element_Enabled("footer");
		fnsLoading_Progressingwait(1);
	
		fncEscalation_Verify_First_IssueClosed_after_EscalationClosed("SearchIssue_Escalation_Status",issue_id_black, "AMBER" , false);
		fncEscalation_Verify_First_IssueClosed_after_EscalationClosed("SearchIssue_Escalation_Status",issue_id_amber, "AMBER" , false);
		
			
	}			
	
	
	
	
	
	
	

	
	@Test(dependsOnMethods={"Escalation_ChangeFirstIssueDate_BackTo_OriginalDate_and_IssueColor_Verify"}, priority= 5, description="  [Edit the second issue and change its Date of Issue to 1 month back (2 issues should not fall between 28 days). Click Save.]")
	public void Escalation_ChangeSecondIssueDate_to_PastDate_of_MoreThan_28Days_from_FirstIssueDate_and_IssueColor_Verify() throws Throwable{
	
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("############# Test Case :::::3 Escalation_ChangeSecondIssueDate_to_PastDate_of_MoreThan_28Days_from_FirstIssueDate_and_IssueColor_Verify ");
		
		
//		IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//src//nsf//ecap//xls//IssueMgt_Suite.xlsx");
			
		//Search IssueID and also navigate to ViewIssue Summary Details page 
		fncIssueID_SearchIssueID_and_Navigate_to_ViewIssueSummaryDetailsPageComes(issue_id_amber, true);
		
		//Clicking on Summary TAB on View issue page
		fnGet_Element_Enabled("ViewIssueSummaryDetails_summary_detail_tab");
		fnWait_and_Click("ViewIssueSummaryDetails_summary_detail_tab");
		
		//Edit Issue Date to 1 month back
		fnGet_Element_Enabled("ViewIssueSummaryDetails_DateTime_Issue");
		WithOut_OR_fnClear(OR_IM.getProperty("ViewIssueSummaryDetails_DateTime_Issue"));
		fnWait_and_Type("ViewIssueSummaryDetails_DateTime_Issue", IssueMgt_Suitexls.getCellData("Escalation_AFP_Diff_Issue", 2, 8));
		
		fnGet_Element_Enabled("ViewIssueSummaryDetails_summary_details_save_button");
		fnWait_and_Click("ViewIssueSummaryDetails_summary_details_save_button");
		fnsLoading_Progressingwait(3);
		

		fnGet_Element_Enabled("validation_error_msg");
		fncVerify_DataUpdated_Successfully("validation_error_msg", "Summary TAB ");	
		

		fnSearchIssue_Ajax_Link_Click();
		fnGet_Element_Enabled("footer");
		fnsLoading_Progressingwait(1);
	
		fncEscalation_Verify_First_IssueClosed_after_EscalationClosed("SearchIssue_Escalation_Status",issue_id_amber, "" , false);
		fncEscalation_Verify_First_IssueClosed_after_EscalationClosed("SearchIssue_Escalation_Status",issue_id_black, "" , false);	
		
	}			
	
	
	
	
	
	
	
	
	
	
	

	@Test(dependsOnMethods={"Escalation_ChangeSecondIssueDate_to_PastDate_of_MoreThan_28Days_from_FirstIssueDate_and_IssueColor_Verify"}, priority= 6, description="  [Edit the Second issue again and change its Date of Issue back to the original date(ie within 28 days of both the issues).Click Save.]")
	public void Escalation_ChangeSecondIssueDate_BackTo_OriginalDate_and_IssueColor_Verify() throws Throwable{
	
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::6 Escalation_ChangeSecondIssueDate_BackTo_OriginalDate_and_IssueColor_Verify ");
		
		
	//	IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//src//nsf//ecap//xls//IssueMgt_Suite.xlsx");
			
		//Search IssueID and also navigate to ViewIssue Summary Details page 
		fncIssueID_SearchIssueID_and_Navigate_to_ViewIssueSummaryDetailsPageComes(issue_id_amber, true);
				
		//Clicking on Summary TAB on View issue page
		fnGet_Element_Enabled("ViewIssueSummaryDetails_summary_detail_tab");
		fnWait_and_Click("ViewIssueSummaryDetails_summary_detail_tab");
		
		//Edit Issue Date to 1 month back
		fnGet_Element_Enabled("ViewIssueSummaryDetails_DateTime_Issue");
		WithOut_OR_fnClear(OR_IM.getProperty("ViewIssueSummaryDetails_DateTime_Issue"));
		fnWait_and_Type("ViewIssueSummaryDetails_DateTime_Issue", IssueMgt_Suitexls.getCellData("Escalation_AFP_Diff_Issue", 2, 6));
		
		fnGet_Element_Enabled("ViewIssueSummaryDetails_summary_details_save_button");
		fnWait_and_Click("ViewIssueSummaryDetails_summary_details_save_button");
		fnsLoading_Progressingwait(3);
		

		fnSearchIssue_Ajax_Link_Click();
		fnGet_Element_Enabled("footer");
		fnsLoading_Progressingwait(1);
	
		fncEscalation_Verify_First_IssueClosed_after_EscalationClosed("SearchIssue_Escalation_Status",issue_id_amber, "AMBER" , false);
		fncEscalation_Verify_First_IssueClosed_after_EscalationClosed("SearchIssue_Escalation_Status",issue_id_black, "AMBER" , false);
		
		
}
	
	
	
	
	@Test(dependsOnMethods={"Escalation_ChangeSecondIssueDate_BackTo_OriginalDate_and_IssueColor_Verify"}, priority = 7, description="  [Create Third Issue ID with Red Color]")
	public void Escalation_AFP_ThirdIssue_Red() throws Throwable{
		
		fnApps_Report_Logs("=========================================================================================================================================");	
		fnApps_Report_Logs("################### Test Case ::::::7 Escalation_AFP_ThirdIssue_Red ");
	//	IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//src//nsf//ecap//xls//IssueMgt_Suite.xlsx");
		
				
		Create_AFP_Issue_Without_IssueID_Verification("2", IssueMgt_Suitexls.getCellData("Escalation_AFP_Diff_Issue", 2, 10), IssueMgt_Suitexls.getCellData("Escalation_AFP_Diff_Issue", 2, 11), TitleShortDescriptionText);
				
		issue_id_red=fnVerified_IssueID_Generated_and_Color_Verification(3, "RED");
		
		
		

}
	
	
	
	
	@Test(dependsOnMethods={"Escalation_AFP_ThirdIssue_Red"}, priority = 8, description="  [The Color of the issue should be red and an escalation should be generated. All the 3 issues should be changed to red color.]")
	public void Escalation_AFP_Verify_Escalation_Generated_and_VERIFY_All_Issues_Color() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::8 Escalation_AFP_Verify_Escalation_Generated_and_VERIFY_All_Issues_Color ");
		
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
				assert ( (related_issue_table.contains(issue_id_black)) && related_issue_table.contains(issue_id_amber) && related_issue_table.contains(issue_id_red) ):
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
				
		

		fnSearchIssue_Ajax_Link_Click();
		fnGet_Element_Enabled("footer");
		fnsLoading_Progressingwait(1);
	
		fncEscalation_Verify_First_IssueClosed_after_EscalationClosed("SearchIssue_Escalation_Status",issue_id_black, "RED" , false);
		fncEscalation_Verify_First_IssueClosed_after_EscalationClosed("SearchIssue_Escalation_Status",issue_id_amber, "RED" , false);
		fncEscalation_Verify_First_IssueClosed_after_EscalationClosed("SearchIssue_Escalation_Status",issue_id_red, "RED" , false);
		
		
}
	

	

	@Test(dependsOnMethods={"Escalation_AFP_Verify_Escalation_Generated_and_VERIFY_All_Issues_Color"}, priority= 9, description="  [Edit the first issue and change its Date of Issue to 2 months back (all 3 issues should not fall between 28 days). Click Save.]")
	public void Escalation_ChangeFirstIssueDate_to_PastDate_of_MoreThan_28Days_from_SecondIssueDate_and_Verify_IssuesColor() throws Throwable{
	
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("############# Test Case ::::9 Escalation_ChangeFirstIssueDate_to_PastDate_of_MoreThan_28Days_from_SecondIssueDate_and_Verify_IssuesColor ");
		
		
//		IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//src//nsf//ecap//xls//IssueMgt_Suite.xlsx");
		
		
		//Search IssueID and also navigate to ViewIssue Summary Details page 
		fncIssueID_SearchIssueID_and_Navigate_to_ViewIssueSummaryDetailsPageComes(issue_id_black, true);
				
		//Clicking on Summary TAB on View issue page
		fnGet_Element_Enabled("ViewIssueSummaryDetails_summary_detail_tab");
		fnWait_and_Click("ViewIssueSummaryDetails_summary_detail_tab");
		
		//Edit Issue Date to 1 month back
		fnGet_Element_Enabled("ViewIssueSummaryDetails_DateTime_Issue");
		WithOut_OR_fnClear(OR_IM.getProperty("ViewIssueSummaryDetails_DateTime_Issue"));
		fnWait_and_Type("ViewIssueSummaryDetails_DateTime_Issue", IssueMgt_Suitexls.getCellData("Escalation_AFP_Diff_Issue", 2, 12));
		
		fnGet_Element_Enabled("ViewIssueSummaryDetails_summary_details_save_button");
		fnWait_and_Click("ViewIssueSummaryDetails_summary_details_save_button");
		fnsLoading_Progressingwait(3);
		

		fnSearchIssue_Ajax_Link_Click();
		fnGet_Element_Enabled("footer");
		fnsLoading_Progressingwait(1);
	
		fncEscalation_Verify_First_IssueClosed_after_EscalationClosed("SearchIssue_Escalation_Status",issue_id_black, "" , false);
		fncEscalation_Verify_First_IssueClosed_after_EscalationClosed("SearchIssue_Escalation_Status",issue_id_amber, "AMBER" , false);
		fncEscalation_Verify_First_IssueClosed_after_EscalationClosed("SearchIssue_Escalation_Status",issue_id_red, "AMBER" , false);
			
	}			
	
	
	
	
	
	
	
	
	
	
	

	@Test(dependsOnMethods={"Escalation_ChangeFirstIssueDate_to_PastDate_of_MoreThan_28Days_from_SecondIssueDate_and_Verify_IssuesColor"}, priority= 10, description="  [Edit the first issue again and change its Date of Issue back to the original date(ie within 28 days of all the issues).Click Save.]")
	public void Escalation_ChangeFirstIssueDate_BackTo_OriginalDate_and_Verify_IssuesColor() throws Throwable{
	
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::10 Escalation_ChangeFirstIssueDate_BackTo_OriginalDate_and_Verify_IssuesColor ");
		
		
//		IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//src//nsf//ecap//xls//IssueMgt_Suite.xlsx");
			
		try{
			//Search IssueID and also navigate to ViewIssue Summary Details page 
			fncIssueID_SearchIssueID_and_Navigate_to_ViewIssueSummaryDetailsPageComes(issue_id_black, true);
					
			//Clicking on Summary TAB on View issue page
			fnGet_Element_Enabled("ViewIssueSummaryDetails_summary_detail_tab");
			fnWait_and_Click("ViewIssueSummaryDetails_summary_detail_tab");
			
			//Edit Issue Date to 1 month back
			fnGet_Element_Enabled("ViewIssueSummaryDetails_DateTime_Issue");
			WithOut_OR_fnClear(OR_IM.getProperty("ViewIssueSummaryDetails_DateTime_Issue"));
			fnWait_and_Type("ViewIssueSummaryDetails_DateTime_Issue", IssueMgt_Suitexls.getCellData("Escalation_AFP_Diff_Issue", 2, 2));
			
			fnGet_Element_Enabled("ViewIssueSummaryDetails_summary_details_save_button");
			fnWait_and_Click("ViewIssueSummaryDetails_summary_details_save_button");
			fnsLoading_Progressingwait(3);
			
						
	
			//Verify NEW Escalation Generated
			fnGet_Element_Enabled("Escalation_id_link");
	
			Thread.sleep(500);
			NEW_Escalation_Id=fnGet_Field_TEXT("Escalation_id_link").trim();
			try{
				assert (!NEW_Escalation_Id.contains(Escalation_Id)):"FAILED == NEW Escalations ID is not Generated >> "+NEW_Escalation_Id+",  Please refer ScreenShot [ "+NEW_Escalation_Id+fnScreenShot_Date_format() +" ]";	
			   	fnApps_Report_Logs("PASSED == NEW Escalations ID has been generated === "+NEW_Escalation_Id);
			}catch(Throwable t){
				isTestCasePass = false;
				fnTake_Screen_Shot(NEW_Escalation_Id);
				fnApps_Report_Logs(Throwables.getStackTraceAsString(t));
				throw new Exception(Throwables.getStackTraceAsString(t));}
		
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
				assert ((related_issue_table.contains(issue_id_black)) && related_issue_table.contains(issue_id_amber) && related_issue_table.contains(issue_id_red) ):
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
		

		fnSearchIssue_Ajax_Link_Click();
		fnGet_Element_Enabled("footer");
		fnsLoading_Progressingwait(1);
	
		fncEscalation_Verify_First_IssueClosed_after_EscalationClosed("SearchIssue_Escalation_Status",issue_id_black, "RED" , false);
		fncEscalation_Verify_First_IssueClosed_after_EscalationClosed("SearchIssue_Escalation_Status",issue_id_amber, "RED" , false);
		fncEscalation_Verify_First_IssueClosed_after_EscalationClosed("SearchIssue_Escalation_Status",issue_id_red, "RED" , false);
		
}			
	
	
		
	
		
	
	
	
	
	
	
	
	

	@Test(dependsOnMethods={"Escalation_ChangeFirstIssueDate_BackTo_OriginalDate_and_Verify_IssuesColor"}, priority = 11, description="  [Change the status from >Initial Record >SATISFACTORY CLOSURE. Mention new status response>Click save. Check -Click on Assign to me Button.]")
	public void Escalation_EditEscalation_Change_status_InitialRecord_to_SatisfactoryClosure() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("####### Test Case ::::11 Escalation_EditEscalation_Change_status_InitialRecord_to_SatisfactoryClosure");
		WebDriverWait waitvar = new WebDriverWait(driver, Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
		
		//Search Escalation and also navigate to ViewEscalation EscalationDetails page
		fncEscalation_SearchEscalation_and_Navigate_till_ViewEscalationDetailsPageComes(NEW_Escalation_Id);
		
		// Select Technologist Type by clicking on radio button
		fnGet_Element_Enabled("Escalation_Summary_assign_to_me_button");
		fnGet_Element_Enabled("Escalation_Summary_assign_to_me_button");
		fnWait_and_Click("Escalation_Summary_assign_to_me_button");
		fnsLoading_Progressingwait(3);
			
		fncEscalation_VerifyChange_Status_on_SummaryDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_AFP_Diff_Issue", 2, 13));
		
		fnGet_Element_Enabled("validation_error_msg");
		fncVerify_DataUpdated_Successfully("validation_error_msg", "Summary TAB");	
		
	}
	
	
	
	
	
	
	
	
	
	


	@Test(dependsOnMethods={"Escalation_EditEscalation_Change_status_InitialRecord_to_SatisfactoryClosure"}, priority = 12, description="  [Verify that all issues are closed which are related to Escalation as the escalation is closed.]")
	public void Escalation_Verify_AllIssuesClosed_As_EscalationClosed() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::12 Escalation_Verify_AllIssuesClosed_As_EscalationClosed ");
		
		fnSearchIssue_Ajax_Link_Click();
		fnGet_Element_Enabled("footer");
		fnsLoading_Progressingwait(1);
	
		fncEscalation_Verify_First_IssueClosed_after_EscalationClosed("SearchIssue_Parent_Status",issue_id_black, "CLOSED" , false);
		fncEscalation_Verify_First_IssueClosed_after_EscalationClosed("SearchIssue_Parent_Status",issue_id_amber, "CLOSED" , false);
		fncEscalation_Verify_First_IssueClosed_after_EscalationClosed("SearchIssue_Parent_Status",issue_id_red, "CLOSED" , false);
		
		fnApps_Report_Logs("########################################## [BS - 14]  CreateEscalation_AFP_Diff_Issues Script END #################################### ");
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
	
	
	
	
	
	
	
