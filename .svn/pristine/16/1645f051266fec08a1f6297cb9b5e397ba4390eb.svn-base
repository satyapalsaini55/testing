package nsf.ecap.New_NSFOnline_Suite;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import nsf.ecap.MonitorPlan_Suite.TestSuiteBase_MonitorPlan;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;


public class BS_11_Appeal_Create_To_Review extends TestSuiteBase_New_NSFOnline {



@Parameters({ "className" })
@BeforeTest
public void checkTestSkip(String className) throws Exception {
	if (className.isEmpty()) {
		className = this.getClass().getSimpleName();
	}
	IsBrowserPresentAlready =false;
	fnsCheckClassLevelTestSkip(className);
	fnsApps_Report_Logs("################################## [BS-11]  Create Edit Submit and Review Appeal");
}


//##########################################   EXCEL Variable ###############################################################################################

public String Appeal_Audit_View_Name = "Appeal - "+fnsReturn_CurrentTime();
public boolean Browser_open = false;
public String Audit_number = "";
public String Approver_LoginUser = null;
public String Approver_LoginPassword = null;
public String Submitter_LoginUser = null;
public String Submitter_LoginPassword = null;
public String FileUploadPath = null;
public String Attachement_Question_Text = null;






@Test( priority = 0)
public void DB_Query__To_CleanUpData_of_AuditNumber_for_Creating_New_Appeal() throws Throwable{
	try{
		
		fnsApps_Report_Logs("################################## DB_Query__To_CleanUpData_of_AuditNumber_for_Creating_New_Appeal");
		
		Appeal_Audit_View_Name = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Appeal_Audit_View_Name");
		Audit_number = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Audit_Number_to_CreateAppeal").trim();
		
		Approver_LoginUser = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Approver_Login_User_Password").split(":")[0].trim();
		Approver_LoginPassword = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Approver_Login_User_Password").split(":")[1].trim();
		
		Submitter_LoginUser = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Submitter_Login_User_Password").split(":")[0].trim();
		Submitter_LoginPassword = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Submitter_Login_User_Password").split(":")[1].trim();
		
		
		/*String DB_Query = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "DB_Query_To_Fetch_AuditNo").trim();
		 Audit_number = fnDB_Return_AuditNo_onWhich_Appeal_will_Create(DB_Query);
		if(Audit_number==null){
			throw new Exception("FAILED == No more Audits are exists into the DataBase to Create APPEAL.");
		}*/
		//if(Audit_number!="" && Audit_number.length()>5){
		if(!(Audit_number.equals("")) && Audit_number.length()>5){
			fnsDB_Delete_all_Data_of_the_AuditNo_onWhich_Appeal_will_Create(Audit_number);
		}else{
			throw new Exception("FAILED Audit Number <"+Audit_number+"> is not mentioned intot the excel.");
		}
		
	}catch(Throwable t){
		isTestCasePass = false;
		fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
	fnsApps_Report_Logs("=========================================================================================================================================");
}


	

		
@Test( priority = 2, dependsOnMethods={"DB_Query__To_CleanUpData_of_AuditNumber_for_Creating_New_Appeal"})
public void Submitter__Create_and_Submit__Appeal() throws Throwable{
	fnsApps_Report_Logs("=========================================================================================================================================");
	fnsApps_Report_Logs("#### Submitter__Create_and_Submit__Appeal ");
	
	try{
		
		
		
	
		fnsApps_Report_Logs("################################## ");
		fnsBrowserLaunchAndLogin(Submitter_LoginUser, Submitter_LoginPassword);
		fnsSwitchAcoount_MultiAccess(this.getClass().getSimpleName(), "SwitchAccount_DD");
			
		fnsApps_Report_Logs("=========================================================================================================================================");
	
		fncSub_Menu_Ajax_Link_Click_By_PassingAjaxPath("Sites_Ajax", "Sites_SubMenu_Audits_Ajax");
		
		fncNavigation_Verify_Application_Navigated_To_ViewScreen("'Audits' Menu Ajax", "Audits");
		
		fnsGet_Element_Enabled("View_AdvanceSearch_Bttn");
		fnsLoading_Progressing_wait(1);
		fnsWait_and_Click("View_AdvanceSearch_Bttn");
		fnsLoading_Progressing_wait(5);
					
		fnsGet_Element_Enabled("AuditsAdvanceSearch_Search_Bttn");
		fnsLoading_Progressing_wait(3);
		fnsWait_and_Clear("Site_AuditsAdvanceSearch_AuditNo");
		fnsWait_and_Type("Site_AuditsAdvanceSearch_AuditNo", Audit_number);
		
		fnsWait_and_Click("AuditsAdvanceSearch_Search_Bttn");
		fnsLoading_Progressing_wait(5);
		fnsGet_Element_Enabled("AuditsAdvanceSearch_Result_Table");
		TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element(OR_New_NSFOnline.getProperty("AuditsAdvanceSearch_Result_Table"));
		
		fnsLoading_Progressing_wait(1);
		String Result_Table_Text = 		TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(OR_New_NSFOnline.getProperty("AuditsAdvanceSearch_Result_Table")).getText().toLowerCase().trim();
		if( !(Result_Table_Text.contains(Audit_number.toLowerCase().trim())) ){
			fnsTake_Screen_Shot("AuditNumber_Not_Coming");
			throw new Exception ("FAILED == Audit Number <"+Audit_number+"> is not coming into the advance search result, please refer the screen shot >> AuditNumber_Not_Coming"+fnsScreenShot_Date_format());
		}
		
		
		
		String ViewFirstRecord_AuditCode_Column_Xpath= OR_New_NSFOnline.getProperty("View_Result_Table")+"/tr[1]/td["+fnsReturn_ColumnNumber_from_TableHeaderXpath_WithoutOR(OR_New_NSFOnline.getProperty("View_Result_TableHeader"), "Audit Code")+"]";
		String Submitter_AuditView_AuditNumber = fnsw_Click_on_Table_Link_by_Passing_Cell_Xpath__and__Return_CellValue(ViewFirstRecord_AuditCode_Column_Xpath);
		
		fnsVerifyScreenNavigation_afterClickingOnElement("'"+Submitter_AuditView_AuditNumber+"' link", "Default 'Audit_Info' Tab", OR_New_NSFOnline.getProperty("Audits_Audit_Info_Tab_ByDefault_Opened"));
		
		
		fnsGet_Element_Enabled("Audits_Appeal_Tab");
		fnsWait_and_Click("Audits_Appeal_Tab");
		fnsLoading_Progressing_wait(2);
		
		String CreateAppeal_Xpath  = TestSuiteBase_MonitorPlan.WithOut_OR_Return_XPATH_if_More_than_One_Coming(OR_New_NSFOnline.getProperty("Audits_AppealTab_CreateAppeal_button"));
		fnsGet_Element_Display_byPassing_ElementName_and_Xpath_WithoutOR("Appeal Tab", "'Create Appeal' button" ,CreateAppeal_Xpath);
		TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(CreateAppeal_Xpath);
		fnsLoading_Progressing_wait(2);
		
		fnsGet_Element_Display_byPassing_ElementName_and_Xpath_WithoutOR("Create Appeal", "'First Question Radio' button" ,OR_New_NSFOnline.getProperty("Audits_Appeal_AddQuestionScreen_First_Radio_button"));
		fnsWait_and_Click("Audits_Appeal_AddQuestionScreen_First_Radio_button");
		fnsLoading_Progressing_wait(2);
		
		CreateAppeal_Xpath  = TestSuiteBase_MonitorPlan.WithOut_OR_Return_XPATH_if_More_than_One_Coming(OR_New_NSFOnline.getProperty("Audits_AppealTab_CreateAppeal_button"));
		fnsGet_Element_Display_byPassing_ElementName_and_Xpath_WithoutOR("Create Appeal Screen", "'Create Appeal' button" ,CreateAppeal_Xpath);
		fnsLoading_Progressing_wait(1);
		TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(CreateAppeal_Xpath);
	//	fnsLoading_Progressing_wait(2);
		
		
	//	fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  NewNsfOnline_Element_Max_Wait_Time, "Created Successfully", 15);
		fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  60, "Appeal Created Successfully", 20);
		
		fnsVerifyScreenNavigation_afterClickingOnElement("'Create Appeal' button", "'Edit Appeal' screen", OR_New_NSFOnline.getProperty("Audits_Appeal_EditAppeal_ReasonNotes_Box"));
		
		
		fnsGet_Element_Display_byPassing_ElementName_and_Xpath_WithoutOR("Edit Appeal > 'Appeal Description' section", "'First Question' Name" ,OR_New_NSFOnline.getProperty("Audits_Appeal_EditAppeal_AppealReason_Label"));
		fnsGet_Element_Display_byPassing_ElementName_and_Xpath_WithoutOR("Edit Appeal > 'Appeal Description' section", "'Second Question' Name" ,OR_New_NSFOnline.getProperty("Audits_Appeal_EditAppeal_AppealPersonAvailable_Label"));
		
		String AppealReason = fnsGet_Field_TEXT("Audits_Appeal_EditAppeal_AppealReason_Label");
		String APPEAL_PERSON_AVAILABLE = fnsGet_Field_TEXT("Audits_Appeal_EditAppeal_AppealPersonAvailable_Label");
		
		try{
			assert ( (AppealReason.toLowerCase()).contains("reason for appeal") ):"FAILED == Appeal Description : First Question set naming (Reason for Appeal*) has been changed to ("+AppealReason+"*), plese refer the screen shot >> Question_Naming_Change"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED == Appeal Description : First Question set naming (Reason for Appeal*) is Matched");
			
			assert ( (APPEAL_PERSON_AVAILABLE.toLowerCase()).contains("were you present for the evaluation or debriefing") ):"FAILED == Appeal Description : Second Question set naming (Were you present for the evaluation or debriefing?*) has been changed to ("+APPEAL_PERSON_AVAILABLE+"*), plese refer the screen shot >> Question_Naming_Change"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED == Appeal Description : Second Question set naming (Were you present for the evaluation or debriefing?*) is Matched");
		}catch(Throwable t){
			fnsTake_Screen_Shot("Question_Naming_Change");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		
		
		fnsGet_Element_Enabled("Audits_Appeal_EditAppeal_AppealReason_2nd_Radio_button");
		fnsWait_and_Click("Audits_Appeal_EditAppeal_AppealReason_2nd_Radio_button");
		fnsLoading_Progressing_wait(1);
		
		fnsGet_Element_Enabled("Audits_Appeal_EditAppeal_ReasonNotes_Box");
		fnsWait_and_Type("Audits_Appeal_EditAppeal_ReasonNotes_Box", "Automation  "+AppealReason);
		fnsLoading_Progressing_wait(1);
		
		
		fnsGet_Element_Enabled("Audits_Appeal_EditAppeal_AppealPersonAvailable_1st_Radio_button");
		fnsWait_and_Click("Audits_Appeal_EditAppeal_AppealPersonAvailable_1st_Radio_button");
		fnsLoading_Progressing_wait(1);
		
		fnsGet_Element_Enabled("Audits_Appeal_EditAppeal_Person_Avail_Notes_Box");
		fnsWait_and_Type("Audits_Appeal_EditAppeal_Person_Avail_Notes_Box", "Automation  were you present for the evaluation or debriefing attachement");
		fnsLoading_Progressing_wait(1);
		
		
		String Attachement_Question_Div_Xpath = "(//div[@class='auditcorrectiveaction'])[1]";
		Attachement_Question_Text = driver.findElement(By.xpath(Attachement_Question_Div_Xpath)).getText().toLowerCase().trim();
		//Attachement_Question_Text = Attachement_Question_Text.split("question:")[1].trim();
		Attachement_Question_Text = Attachement_Question_Text.substring(15);
		System.out.println("String Index = "+Attachement_Question_Text);
		
		//Upload file
		FileUploadPath = System.getProperty("user.dir") + "\\docs\\This is test pdf document.pdf";
		WebElement Browser = fnsGet_OR_New_NSFOnline_ObjectX("Audits_Appeal_EditAppeal_UploadFiles_browse_input");
		Browser.sendKeys(FileUploadPath);
		
		for(int wait=0; wait<=(NewNsfOnline_Element_Max_Wait_Time); wait++){
			if(driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("Audits_Appeal_EditAppeal_Upload_button"))).size()>0){
				fnsWait_and_Click("Audits_Appeal_EditAppeal_Upload_button");
				//fnsLoading_Progressing_wait(3);
				fns_Verify_Success_message_coming_OR_Error_message_Coming(false,  60, "Attachment Uploaded Successfully", 15);
				fnsLoading_Progressing_wait(2);
			//	fnsLoading_Progressing_wait(2);
			//	fnsLoading_Progressing_wait(2);
				break;
			}else{
				Thread.sleep(1000);
			}
			if(wait==(NewNsfOnline_Element_Max_Wait_Time)){
				fnsTake_Screen_Shot("FileUploadFail");
				throw new Exception("FAILED == File upload is getting fail,after wait Time<"+(NewNsfOnline_Element_Max_Wait_Time)+">Seconds, Please refer screenshot >> FileUploadFail"+fnsScreenShot_Date_format());
			}
		}

		for(int i=0; i<=5; i++){
			try{
				fnsGet_Element_Enabled("Audits_Appeal_EditAppeal_Save_button");
				fnsWait_and_Click("Audits_Appeal_EditAppeal_Save_button"); // Double click save button navigate to advance audit search
				//fns_Verify_Success_message_coming_OR_Error_message_Coming(false,  30, "Saved Successful", 15);
				fns_Verify_Success_message_coming_OR_Error_message_Coming(false,  60, "Appeal Saved Successfully", 15);
				fnsLoading_Progressing_wait(2);
				break;				
			}catch(Throwable t){
				if(i==5){
					fnsTake_Screen_Shot("Success_Message_Fail");
					throw new Exception(Throwables.getStackTraceAsString(t));
				}else{
					fnsLoading_Progressing_wait(1);
				}
			}
		}
		
		fnsLoading_Progressing_wait(1);
		fnsVerifyScreenNavigation_afterClickingOnElement("Edit Appeal > 'Save'  button", "'Appeal' TAB", OR_New_NSFOnline.getProperty("Audits_Appeal_AddQuestion_button"));
		
		fnsGet_Element_Enabled("Audits_Appeal_AddQuestion_button");
		fnsWait_and_Click("Audits_Appeal_AddQuestion_button");
		fnsLoading_Progressing_wait(3);
		
		fnsVerifyScreenNavigation_afterClickingOnElement("'Add Question' button", "'Add Question' screen", OR_New_NSFOnline.getProperty("Audits_Appeal_AddQuestionScreen_First_Radio_button"));
		
		
		fnsGet_Element_Enabled("Audits_Appeal_AddQuestionScreen_First_Radio_button");
		fnsWait_and_Click("Audits_Appeal_AddQuestionScreen_First_Radio_button");
		fnsLoading_Progressing_wait(2);
				
		
		
		String Appeal_AddQuestionScreen_AddQuestions_button_Xpath  = TestSuiteBase_MonitorPlan.WithOut_OR_Return_XPATH_if_More_than_One_Coming(OR_New_NSFOnline.getProperty("Audits_Appeal_AddQuestionScreen_AddQuestions_button"));
		fnsGet_Element_Display_byPassing_ElementName_and_Xpath_WithoutOR("Appeal Tab", "'Create Appeal' button" ,Appeal_AddQuestionScreen_AddQuestions_button_Xpath);
		TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(Appeal_AddQuestionScreen_AddQuestions_button_Xpath);
			
		
		fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  60, "Appeal Created Successfully", 20);
		//fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  60, "Created Successfully", 15);
		fnsLoading_Progressing_wait(2);
		
		fnsVerifyScreenNavigation_afterClickingOnElement("'Add Question' button", "'Edit Appeal' screen", OR_New_NSFOnline.getProperty("Audits_Appeal_EditAppeal_ReasonNotes_Box"));
		
		
		fnsGet_Element_Display_byPassing_ElementName_and_Xpath_WithoutOR("Edit Appeal > 'Appeal Description' section", "'First Question' Name" ,OR_New_NSFOnline.getProperty("Audits_Appeal_EditAppeal_AppealReason_Label"));
		fnsGet_Element_Display_byPassing_ElementName_and_Xpath_WithoutOR("Edit Appeal > 'Appeal Description' section", "'Second Question' Name" ,OR_New_NSFOnline.getProperty("Audits_Appeal_EditAppeal_AppealPersonAvailable_Label"));
		
		AppealReason = fnsGet_Field_TEXT("Audits_Appeal_EditAppeal_AppealReason_Label");
		APPEAL_PERSON_AVAILABLE = fnsGet_Field_TEXT("Audits_Appeal_EditAppeal_AppealPersonAvailable_Label");
		
		try{
			assert ( (AppealReason.toLowerCase()).contains("reason for appeal") ):"FAILED == Appeal Description : First Question set naming (Reason for Appeal*) has been changed to ("+AppealReason+"*), plese refer the screen shot >> Question_Naming_Change"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED == Appeal Description : First Question set naming (Reason for Appeal*) is Matched");
			
			assert ( (APPEAL_PERSON_AVAILABLE.toLowerCase()).contains("were you present for the evaluation or debriefing") ):"FAILED == Appeal Description : Second Question set naming (Were you present for the evaluation or debriefing?*) has been changed to ("+APPEAL_PERSON_AVAILABLE+"*), plese refer the screen shot >> Question_Naming_Change"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED == Appeal Description : Second Question set naming (Were you present for the evaluation or debriefing?*) is Matched");
		}catch(Throwable t){
			fnsTake_Screen_Shot("Question_Naming_Change");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		
		
		fnsGet_Element_Enabled("Audits_Appeal_EditAppeal_AppealReason_2nd_Radio_button");
		fnsWait_and_Click("Audits_Appeal_EditAppeal_AppealReason_2nd_Radio_button");
		fnsLoading_Progressing_wait(1);
		
		fnsGet_Element_Enabled("Audits_Appeal_EditAppeal_ReasonNotes_Box");
		fnsWait_and_Type("Audits_Appeal_EditAppeal_ReasonNotes_Box", "Automation  "+AppealReason);
		fnsLoading_Progressing_wait(1);
		
		
		fnsGet_Element_Enabled("Audits_Appeal_EditAppeal_AppealPersonAvailable_1st_Radio_button");
		fnsWait_and_Click("Audits_Appeal_EditAppeal_AppealPersonAvailable_1st_Radio_button");
		fnsLoading_Progressing_wait(1);
		
		fnsGet_Element_Enabled("Audits_Appeal_EditAppeal_Person_Avail_Notes_Box");
		fnsWait_and_Type("Audits_Appeal_EditAppeal_Person_Avail_Notes_Box", "Automation  were you present for the evaluation or debriefing");
		fnsLoading_Progressing_wait(1);
		
			
		fnsGet_Element_Enabled("Audits_Appeal_EditAppeal_Save_button");
		fnsGet_OR_New_NSFOnline_ObjectX("Audits_Appeal_EditAppeal_Save_button").click();
		fnsLoading_Progressing_wait(2);
		
		fnsGet_Element_Displayed("Audits_Appeal_Alert_Message");
		String QuestionSave_Alert_Message_TEXT = fnsGet_Field_TEXT("Audits_Appeal_Alert_Message");
	
		
		try{
			assert ( (QuestionSave_Alert_Message_TEXT.toLowerCase()).contains("No Attachments added. Are you sure you want to continue".toLowerCase()) ):
				"FAILED == Wrong messae ("+QuestionSave_Alert_Message_TEXT+") is coming after clicking on Submit All button, plese refer the screen shot >> QuestionSave_Incorrect_Message"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED == Correct messae is coming after clicking on Question Save button.");
		}catch(Throwable t){
			fnsTake_Screen_Shot("QuestionSave_Incorrect_Message");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		
		
		fnsGet_Element_Displayed("Audits_Appeal_Alert_OK_Button");
		fnsWait_and_Click("Audits_Appeal_Alert_OK_Button");
		
		fns_Verify_Success_message_coming_OR_Error_message_Coming(false,  5, "Appeal Saved Successfully", 20);
		fnsLoading_Progressing_wait(2);
		
		fnsLoading_Progressing_wait(1);
		fnsVerifyScreenNavigation_afterClickingOnElement("Edit Appeal > 'Save'  button", "'Appeal' TAB", OR_New_NSFOnline.getProperty("Audits_Appeal_AddQuestion_button"));
		
	
		fnsGet_Element_Displayed("Audits_Appeal_SubmitAll_button");
		fnsWait_and_Click("Audits_Appeal_SubmitAll_button");
		fnsLoading_Progressing_wait(2);
		
		
		String SubmitAll_Alert_Message_TEXT = fnsGet_Field_TEXT("Audits_Appeal_Alert_Message");
	
		
		try{
			assert ( (SubmitAll_Alert_Message_TEXT.toLowerCase()).contains("You can submit Appeal only once for current Audit and the details cannot be modified once submitted. Are you sure you want to submit all".toLowerCase()) ):
				"FAILED == Wrong messae ("+SubmitAll_Alert_Message_TEXT+") is coming after clicking on Submit All button, plese refer the screen shot >> SubmitAll_Incorrect_Message"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED == Correct messae is coming after clicking on Submit All button.");
		}catch(Throwable t){
			fnsTake_Screen_Shot("SubmitAll_Incorrect_Message");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		
		
		fnsGet_Element_Displayed("Audits_Appeal_SubmitAll_Alert_OK_Button");
		fnsWait_and_Click("Audits_Appeal_SubmitAll_Alert_OK_Button");
		
		fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  60, "Appeal Submitted Successfully", 20);
		fnsLoading_Progressing_wait(3);
		
		
		
			
		try{
			assert ( (driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("Audits_Appeal_SubmitAll_button"))).size()==0) ):"FAILED == 'Submit All' button is still displayed, after appeal submittion, plese refer the screen shot >> SubmitAll_Button_Display"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED == 'Submit All' button is not displayed.");
		}catch(Throwable t){
			fnsTake_Screen_Shot("SubmitAll_Button_Display");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		
		
		try{
			assert ( (driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("Audits_Appeal_AddQuestion_button"))).size() == 0) ):"FAILED == 'Add Question' button is still displayed, after appeal submittion, plese refer the screen shot >> SubmitAll_Button_Display"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED == 'Add Question' button is not displayed.");
		}catch(Throwable t){
			fnsTake_Screen_Shot("AddQuestion_Button_Display");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		
		
	}catch(Throwable t){
		isTestCasePass = false;
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
	
	
}	
		
		
		
		
@Test( priority = 3, dependsOnMethods={"Submitter__Create_and_Submit__Appeal"})
public void Approver__OpenSameAudit_SubmitedBySubmitter_Now_Approve_and_Reject_AppealsQuestions() throws Throwable{
	fnsApps_Report_Logs("=========================================================================================================================================");
	fnsApps_Report_Logs("#### Approver__OpenSameAudit_SubmitedBySubmitter_Now_Approve_and_Reject_AppealsQuestions ");
	
	try{

		fnsApps_Report_Logs("################################## ");
		fnsBrowserLaunchAndLogin(Approver_LoginUser, Approver_LoginPassword);
		fnsSwitchAcoount_MultiAccess(this.getClass().getSimpleName(), "NO");
			
		fnsApps_Report_Logs("=========================================================================================================================================");
	
		fncSub_Menu_Ajax_Link_Click_By_PassingAjaxPath("Sites_Ajax", "Sites_SubMenu_Audits_Ajax");
		
		fncNavigation_Verify_Application_Navigated_To_ViewScreen("'Audits' Menu Ajax", "Audits");
		
		fnsGet_Element_Enabled("View_AdvanceSearch_Bttn");
		fnsLoading_Progressing_wait(1);
		fnsWait_and_Click("View_AdvanceSearch_Bttn");
		fnsLoading_Progressing_wait(5);
					
		fnsGet_Element_Enabled("AuditsAdvanceSearch_Search_Bttn");
		fnsLoading_Progressing_wait(3);
		fnsWait_and_Clear("Site_AuditsAdvanceSearch_AuditNo");
		fnsWait_and_Type("Site_AuditsAdvanceSearch_AuditNo", Audit_number);
		
		fnsWait_and_Click("AuditsAdvanceSearch_Search_Bttn");
		fnsLoading_Progressing_wait(5);
		fnsGet_Element_Enabled("AuditsAdvanceSearch_Result_Table");
		TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element(OR_New_NSFOnline.getProperty("AuditsAdvanceSearch_Result_Table"));
		
		
		
		String ViewFirstRecord_AuditCode_Column_Xpath= OR_New_NSFOnline.getProperty("View_Result_Table")+"/tr[1]/td["+fnsReturn_ColumnNumber_from_TableHeaderXpath_WithoutOR(OR_New_NSFOnline.getProperty("View_Result_TableHeader"), "Audit Code")+"]";
		String Submitter_AuditView_AuditNumber = fnsw_Click_on_Table_Link_by_Passing_Cell_Xpath__and__Return_CellValue(ViewFirstRecord_AuditCode_Column_Xpath);
		
		fnsVerifyScreenNavigation_afterClickingOnElement("'"+Submitter_AuditView_AuditNumber+"' link", "Default 'Audit_Info' Tab", OR_New_NSFOnline.getProperty("Audits_Audit_Info_Tab_ByDefault_Opened"));
		
		
		fnsGet_Element_Enabled("Audits_Appeal_Tab");
		fnsWait_and_Click("Audits_Appeal_Tab");
		fnsLoading_Progressing_wait(2);
		
		String Question_Des_Xpath = "//div[@id='ccaDetaildsc']"; 
		Integer Review_Button_Having_Attachedment = 0;
		boolean Attachement_Found = false;
		Integer Question_Des_Size = 0;
		for(int i=0; i<=20; i++){
			try{
				Question_Des_Size = driver.findElements(By.xpath(Question_Des_Xpath)).size();
				if(Question_Des_Size==2){
					break;
				}else{
					Thread.sleep(1000);
				}
			}catch(Throwable t){
				if(i==20){
					fnsTake_Screen_Shot("Both_Question_Not_Coming");
					throw new Exception("FAILED == Both Questions are not coming, please refer the screen shot >> Both_Question_Not_Coming"+fnsScreenShot_Date_format());
				}
			}			
		}
				
				
		for(int Q_Contain_Pdf=1; Q_Contain_Pdf<=(Question_Des_Size); Q_Contain_Pdf++){
			String Inddivisual_Question_Desc_Xpath = "("+Question_Des_Xpath+")["+Q_Contain_Pdf+"]";
			String Inddivisual_Question_Desc_Text = driver.findElement(By.xpath(Inddivisual_Question_Desc_Xpath)).getText().toLowerCase().trim();
			if(Inddivisual_Question_Desc_Text.contains(Attachement_Question_Text)){
				Attachement_Found = true;
				Review_Button_Having_Attachedment = Q_Contain_Pdf;
				break;
			}			
		}
		
		if(Attachement_Found == false){
			fnsTake_Screen_Shot("Attachedment_not_Found");
			throw new Exception("FAILED == Attachedment uploaded by submitter in previous step is not showing to reviewer, please refer the screen shot >> Attachedment_not_Found"+fnsScreenShot_Date_format());
		}
		
		
		String Review_Button_Having_Attachement_Xpath = "("+OR_New_NSFOnline.getProperty("Audits_ReviewAppeal_LabelLink")+")["+Review_Button_Having_Attachedment+"]";
		TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(Review_Button_Having_Attachement_Xpath);
		TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(Review_Button_Having_Attachement_Xpath);
		fnsLoading_Progressing_wait(3);
		
		
		fnsGet_Element_Enabled("Audits_ReviewAppeal_AddComments_Box");
		fnsWait_and_Clear("Audits_ReviewAppeal_AddComments_Box");
		fnsWait_and_Type("Audits_ReviewAppeal_AddComments_Box", "Autoimation  Approved");
		
		
				
		
		
		
		/*/for(int c=1; c<=2; c++){
			try{
				if(fnsGet_OR_New_NSFOnline_ObjectX("Audits_ReviewAppeal_Approve_Bttn").isDisplayed()){
					fnsWait_and_Click("Audits_ReviewAppeal_Approve_Bttn");								
				}
				fns_Verify_Success_message_coming_OR_Error_message_Coming(false, 60,  "Approved Successfully", 15);
				break;
			}catch(Throwable t){
				if(c==2){
					fns_Verify_Success_message_coming_OR_Error_message_Coming(true, 3,  "Approved Successfully", 15);
				}else{
					Thread.sleep(1000);
				}
			}
		}*/
		
		
		
		//Not Working on 6.11.2019 
		fnsGet_Element_Enabled("Audits_ReviewAppeal_Approve_Bttn");
		fnsGet_Element_Displayed("Audits_ReviewAppeal_Approve_Bttn");
		fnsWait_and_Click("Audits_ReviewAppeal_Approve_Bttn");
		fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  60, "Approved Successfully", 15);
		
		
		
		
		fnsLoading_Progressing_wait(2);
		
		//Commented on 21.6.2018 as not navigated to expected screen (new Cycle changes)
		/*
		fnsVerifyScreenNavigation_afterClickingOnElement("Edit Appeal > 'Approve'  button", "'Appeal' TAB", OR_New_NSFOnline.getProperty("Audits_AppealTab_Filter_Status_DD"));
		
		fnsDD_value_Select_TagOPTION_DDTypeSelect("Audits_AppealTab_Filter_Status_DD", "SUBMITTED", 20);
		fnsLoading_Progressing_wait(2);
		
		fnsGet_Element_Enabled("Audits_ReviewAppeal_LabelLink");
		fnsWait_and_Click("Audits_ReviewAppeal_LabelLink");
		fnsLoading_Progressing_wait(3);*/
		
		
		fnsVerifyScreenNavigation_afterClickingOnElement("Edit Appeal > 'Approve'  button", "'Review Appeal > Next Question'", OR_New_NSFOnline.getProperty("Audits_ReviewAppeal_AddComments_Box"));
		
		fnsGet_Element_Enabled("Audits_ReviewAppeal_AddComments_Box");
		fnsWait_and_Clear("Audits_ReviewAppeal_AddComments_Box");
		fnsWait_and_Type("Audits_ReviewAppeal_AddComments_Box", "Autoimation  Rejected");
		
				
		//Upload file
		WebElement Browser = fnsGet_OR_New_NSFOnline_ObjectX("Audits_ReviewAppeal_UploadFiles_browse_input");
		Browser.sendKeys(FileUploadPath);
		
		for(int wait=0; wait<=(NewNsfOnline_Element_Max_Wait_Time); wait++){
			if(driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("Audits_ReviewAppeal_UploadFiles_Upload_button"))).size()>0){
				fnsWait_and_Click("Audits_ReviewAppeal_UploadFiles_Upload_button");
			//	fnsLoading_Progressing_wait(3);
				fns_Verify_Success_message_coming_OR_Error_message_Coming(false,  60, "Attachment Uploaded Successfully", 15);
				fnsLoading_Progressing_wait(2);
				//fnsLoading_Progressing_wait(2);
			//	fnsLoading_Progressing_wait(2);
				break;
			}else{
				Thread.sleep(1000);
			}
			if(wait==(NewNsfOnline_Element_Max_Wait_Time)){
				fnsTake_Screen_Shot("FileUploadFail");
				throw new Exception("FAILED == File upload is getting fail,after wait Time<"+(NewNsfOnline_Element_Max_Wait_Time)+">Seconds, Please refer screenshot >> FileUploadFail"+fnsScreenShot_Date_format());
			}
		}
		
		
				
		fnsGet_Element_Enabled("Audits_ReviewAppeal_Reject_Bttn");
		fnsGet_Element_Displayed("Audits_ReviewAppeal_Reject_Bttn");
		fnsWait_and_Click("Audits_ReviewAppeal_Reject_Bttn");
		
		
			
		
		fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  60, "Rejected Successfully", 15);
		fnsLoading_Progressing_wait(2);
		
		/*fnsVerifyScreenNavigation_afterClickingOnElement("Edit Appeal > 'Reject'  button", "'Appeal' TAB", OR_New_NSFOnline.getProperty("Audits_AppealTab_Filter_Status_DD"));
		*/
		
	}catch(Throwable t){
		isTestCasePass = false;
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
	
	
}		
		
		

		
		
@Test( priority = 4, dependsOnMethods={"Approver__OpenSameAudit_SubmitedBySubmitter_Now_Approve_and_Reject_AppealsQuestions"})
public void Submitter__OpenSameAudit_RejectedByApprover_Now_Escalate_the_RejcetedQuestion() throws Throwable{
	fnsApps_Report_Logs("=========================================================================================================================================");
	fnsApps_Report_Logs("#### Submitter__OpenSameAudit_RejectedByApprover_Now_Escalate_the_RejcetedQuestion ");
	
	try{

		fnsApps_Report_Logs("################################## ");
		fnsBrowserLaunchAndLogin(Submitter_LoginUser, Submitter_LoginPassword);
		fnsSwitchAcoount_MultiAccess(this.getClass().getSimpleName(), "SwitchAccount_DD");
			
		fnsApps_Report_Logs("=========================================================================================================================================");
	
		fncSub_Menu_Ajax_Link_Click_By_PassingAjaxPath("Sites_Ajax", "Sites_SubMenu_Audits_Ajax");
		
		fncNavigation_Verify_Application_Navigated_To_ViewScreen("'Audits' Menu Ajax", "Audits");
		
		fnsGet_Element_Enabled("View_AdvanceSearch_Bttn");
		fnsLoading_Progressing_wait(1);
		fnsWait_and_Click("View_AdvanceSearch_Bttn");
		fnsLoading_Progressing_wait(5);
					
		fnsGet_Element_Enabled("AuditsAdvanceSearch_Search_Bttn");
		fnsLoading_Progressing_wait(3);
		fnsWait_and_Clear("Site_AuditsAdvanceSearch_AuditNo");
		fnsWait_and_Type("Site_AuditsAdvanceSearch_AuditNo", Audit_number);
		
		fnsWait_and_Click("AuditsAdvanceSearch_Search_Bttn");
		fnsLoading_Progressing_wait(5);
		fnsGet_Element_Enabled("AuditsAdvanceSearch_Result_Table");
		TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element(OR_New_NSFOnline.getProperty("AuditsAdvanceSearch_Result_Table"));
		
		
		
		String ViewFirstRecord_AuditCode_Column_Xpath= OR_New_NSFOnline.getProperty("View_Result_Table")+"/tr[1]/td["+fnsReturn_ColumnNumber_from_TableHeaderXpath_WithoutOR(OR_New_NSFOnline.getProperty("View_Result_TableHeader"), "Audit Code")+"]";
		String Submitter_AuditView_AuditNumber = fnsw_Click_on_Table_Link_by_Passing_Cell_Xpath__and__Return_CellValue(ViewFirstRecord_AuditCode_Column_Xpath);
		
		fnsVerifyScreenNavigation_afterClickingOnElement("'"+Submitter_AuditView_AuditNumber+"' link", "Default 'Audit_Info' Tab", OR_New_NSFOnline.getProperty("Audits_Audit_Info_Tab_ByDefault_Opened"));
		
		
		fnsGet_Element_Enabled("Audits_Appeal_Tab");
		fnsWait_and_Click("Audits_Appeal_Tab");
		fnsLoading_Progressing_wait(2);
				
				
		
		fnsDD_value_Select_TagOPTION_DDTypeSelect("Audits_AppealTab_Filter_Status_DD", "REJECTED", 20);
		fnsLoading_Progressing_wait(2);
		
		
		fnsGet_Element_Enabled("Audits_EscalateAppeal_LabelLink");
		fnsWait_and_Click("Audits_EscalateAppeal_LabelLink");
		fnsLoading_Progressing_wait(2);
		
		

		fnsGet_Element_Enabled("Audits_ReviewAppeal_AddComments_Box");
		fnsWait_and_Clear("Audits_ReviewAppeal_AddComments_Box");
		fnsWait_and_Type("Audits_ReviewAppeal_AddComments_Box", "Autoimation  Escalated");
		
				
		fnsGet_Element_Enabled("Audits_ReviewAppeal_Escalate_Bttn");
		fnsWait_and_Click("Audits_ReviewAppeal_Escalate_Bttn");
		
		fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  60, "Escalated successfully", 20);
		fnsLoading_Progressing_wait(2);
		
		fnsVerifyScreenNavigation_afterClickingOnElement("Edit Appeal > 'Escalate'  button", "'Appeal' TAB", OR_New_NSFOnline.getProperty("Audits_AppealTab_Filter_Status_DD"));
	}catch(Throwable t){
		isTestCasePass = false;
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
	
	
}		
		

		
@Test( priority = 5, dependsOnMethods={"Submitter__OpenSameAudit_RejectedByApprover_Now_Escalate_the_RejcetedQuestion"})
public void Approver__OpenSameAudit_EscalatedBySubmitter_Now_Reject_the_EscalatedQuestion_ViewQuestion() throws Throwable{
	fnsApps_Report_Logs("=========================================================================================================================================");
	fnsApps_Report_Logs("#### Approver__OpenSameAudit_EscalatedBySubmitter_Now_Reject_the_EscalatedQuestion_ViewQuestion ");
	
	try{

		fnsApps_Report_Logs("################################## ");
		fnsBrowserLaunchAndLogin(Approver_LoginUser, Approver_LoginPassword);
		fnsSwitchAcoount_MultiAccess(this.getClass().getSimpleName(), "NO");
			
		fnsApps_Report_Logs("=========================================================================================================================================");
	
		fncSub_Menu_Ajax_Link_Click_By_PassingAjaxPath("Sites_Ajax", "Sites_SubMenu_Audits_Ajax");
		
		fncNavigation_Verify_Application_Navigated_To_ViewScreen("'Audits' Menu Ajax", "Audits");
		
		fnsGet_Element_Enabled("View_AdvanceSearch_Bttn");
		fnsLoading_Progressing_wait(1);
		fnsWait_and_Click("View_AdvanceSearch_Bttn");
		fnsLoading_Progressing_wait(5);
					
		fnsGet_Element_Enabled("AuditsAdvanceSearch_Search_Bttn");
		fnsLoading_Progressing_wait(3);
		fnsWait_and_Clear("Site_AuditsAdvanceSearch_AuditNo");
		fnsWait_and_Type("Site_AuditsAdvanceSearch_AuditNo", Audit_number);
		
		fnsWait_and_Click("AuditsAdvanceSearch_Search_Bttn");
		fnsLoading_Progressing_wait(5);
		fnsGet_Element_Enabled("AuditsAdvanceSearch_Result_Table");
		TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element(OR_New_NSFOnline.getProperty("AuditsAdvanceSearch_Result_Table"));
		
		
		
		String ViewFirstRecord_AuditCode_Column_Xpath= OR_New_NSFOnline.getProperty("View_Result_Table")+"/tr[1]/td["+fnsReturn_ColumnNumber_from_TableHeaderXpath_WithoutOR(OR_New_NSFOnline.getProperty("View_Result_TableHeader"), "Audit Code")+"]";
		String Submitter_AuditView_AuditNumber = fnsw_Click_on_Table_Link_by_Passing_Cell_Xpath__and__Return_CellValue(ViewFirstRecord_AuditCode_Column_Xpath);
		
		fnsVerifyScreenNavigation_afterClickingOnElement("'"+Submitter_AuditView_AuditNumber+"' link", "Default 'Audit_Info' Tab", OR_New_NSFOnline.getProperty("Audits_Audit_Info_Tab_ByDefault_Opened"));
		
		
		fnsGet_Element_Enabled("Audits_Appeal_Tab");
		fnsWait_and_Click("Audits_Appeal_Tab");
		fnsLoading_Progressing_wait(2);
			
		
		
		fnsDD_value_Select_TagOPTION_DDTypeSelect("Audits_AppealTab_Filter_Status_DD", "ESCALATED", 20);
		fnsLoading_Progressing_wait(2);
		
		fnsGet_Element_Enabled("Audits_ReviewAppeal_LabelLink");
		fnsWait_and_Click("Audits_ReviewAppeal_LabelLink");
		fnsLoading_Progressing_wait(3);
		

		fnsGet_Element_Enabled("Audits_ReviewAppeal_AddComments_Box");
		fnsWait_and_Clear("Audits_ReviewAppeal_AddComments_Box");
		fnsWait_and_Type("Audits_ReviewAppeal_AddComments_Box", "Automation Escalate to Rejected");
		
		
		fnsGet_Element_Enabled("Audits_ReviewAppeal_Reject_Bttn");
		fnsWait_and_Click("Audits_ReviewAppeal_Reject_Bttn");
		
		fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  60, "Rejected Successfully", 15);
		fnsLoading_Progressing_wait(2);
		
		fnsVerifyScreenNavigation_afterClickingOnElement("Edit Appeal > 'Reject'  button", "'Appeal' TAB", OR_New_NSFOnline.getProperty("Audits_AppealTab_Filter_Status_DD"));
		
		fnsDD_value_Select_TagOPTION_DDTypeSelect("Audits_AppealTab_Filter_Status_DD", "REJECTED", 20);
		fnsLoading_Progressing_wait(2);
		
		
		
		try{
			assert ( (driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("Audits_ReviewAppeal_LabelLink"))).size()==0) ):"FAILED == 'Review' button is still displayed for the Question, after Twice rejection of the Question, plese refer the screen shot >> Review_Button_Display"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED == 'Review Appeal' button is not displayed.");
		}catch(Throwable t){
			fnsTake_Screen_Shot("Review_Button_Display");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		
		

		try{
			assert ( (driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("Audits_EscalateAppeal_LabelLink"))).size() == 0) ):"FAILED == 'Escalate' button is still displayed for the Question, after Twice rejection of the Question, plese refer the screen shot >> Escalate_Button_Display"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED == 'Escalate' button is not displayed.");
		}catch(Throwable t){
			fnsTake_Screen_Shot("Escalate_Button_Display");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		
		
		fnsGet_Element_Enabled("Audits_ViewAppeal_LabelLink");
		fnsWait_and_Click("Audits_ViewAppeal_LabelLink");
		fnsLoading_Progressing_wait(3);
		
		
		fnsVerifyScreenNavigation_afterClickingOnElement("Appeal TAB > 'View'  button", "'View' Appeal Screen", OR_New_NSFOnline.getProperty("Audits_ViewAppeal_GoBack_button"));
		
		
	}catch(Throwable t){
		isTestCasePass = false;
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
	
	
}



































/*
@Test( priority = 1, dependsOnMethods={"DB_Query__To_Fetch_AuditNumber_for_Creating_New_Appeal"})
public void () throws Throwable{
	try{
		Appeal_Audit_View_Name = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Appeal_Audit_View_Name");
		WelComeScreen_Account_Switch_DD = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "WelComeScreen_SelectAccount_DD");
		
		fnsApps_Report_Logs("################################## ");
		if (!IsBrowserPresentAlready) {
			IsBrowserPresentAlready = fnsBrowserLaunchAndLogin(Login_UserName, Login_Password, WelComeScreen_Account_Switch_DD);
			fnsApps_Report_Logs("Browser is launched and Successfully login into Application");	
		}
		
	}catch(Throwable t){
		isTestCasePass = false;
		fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
	fnsApps_Report_Logs("=========================================================================================================================================");
}

	
		

		
@Test( priority = 2, dependsOnMethods={""})
public void Create_Edit_Submit_Approve_Reject_Escalate_and_View__Appeal() throws Throwable{
	fnsApps_Report_Logs("=========================================================================================================================================");
	fnsApps_Report_Logs("#### Create_Edit_Submit_Approve_Reject_Escalate_and_View__Appeal ");
	
	try{	
		
		fncSub_Menu_Ajax_Link_Click_By_PassingAjaxPath("Sites_Ajax", "Sites_SubMenu_Audits_Ajax");
		
		fncNavigation_Verify_Application_Navigated_To_ViewScreen("'Audits' Menu Ajax", "Audits");
		
		fnsGet_Element_Enabled("View_AdvanceSearch_Bttn");
		fnsLoading_Progressing_wait(1);
		fnsWait_and_Click("View_AdvanceSearch_Bttn");
		fnsLoading_Progressing_wait(5);
					
		fnsGet_Element_Enabled("AuditsAdvanceSearch_Search_Bttn");
		fnsLoading_Progressing_wait(1);
		fnsWait_and_Clear("Site_AuditsAdvanceSearch_AuditNo");
		fnsWait_and_Type("Site_AuditsAdvanceSearch_AuditNo", Audit_number);
		
		fnsWait_and_Click("AuditsAdvanceSearch_Search_Bttn");
		fnsLoading_Progressing_wait(5);
		fnsGet_Element_Enabled("AuditsAdvanceSearch_Result_Table");
		TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element(OR_New_NSFOnline.getProperty("AuditsAdvanceSearch_Result_Table"));
		
		
		
		String ViewFirstRecord_AuditCode_Column_Xpath= OR_New_NSFOnline.getProperty("View_Result_Table")+"/tr[1]/td["+fnsReturn_ColumnNumber_from_TableHeaderXpath("View_Result_TableHeader", "Audit Code")+"]";
		String Submitter_AuditView_AuditNumber = fnsw_Click_on_Table_Link_by_Passing_Cell_Xpath__and__Return_CellValue(ViewFirstRecord_AuditCode_Column_Xpath);
		
		fnsVerifyScreenNavigation_afterClickingOnElement("'"+Submitter_AuditView_AuditNumber+"' link", "Default 'Audit_Info' Tab", OR_New_NSFOnline.getProperty("Audits_Audit_Info_Tab_ByDefault_Opened"));
		
		
		fnsGet_Element_Enabled("Audits_Appeal_Tab");
		fnsWait_and_Click("Audits_Appeal_Tab");
		fnsLoading_Progressing_wait(2);
		
		String CreateAppeal_Xpath  = TestSuiteBase_MonitorPlan.WithOut_OR_Return_XPATH_if_More_than_One_Coming(OR_New_NSFOnline.getProperty("Audits_AppealTab_CreateAppeal_button"));
		fnsGet_Element_Display_byPassing_ElementName_and_Xpath_WithoutOR("Appeal Tab", "'Create Appeal' button" ,CreateAppeal_Xpath);
		TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(CreateAppeal_Xpath);
		fnsLoading_Progressing_wait(2);
		
		fnsGet_Element_Display_byPassing_ElementName_and_Xpath_WithoutOR("Create Appeal", "'First Question Radio' button" ,OR_New_NSFOnline.getProperty("Audits_Appeal_AddQuestionScreen_First_Radio_button"));
		fnsWait_and_Click("Audits_Appeal_AddQuestionScreen_First_Radio_button");
		fnsLoading_Progressing_wait(2);
		
		CreateAppeal_Xpath  = TestSuiteBase_MonitorPlan.WithOut_OR_Return_XPATH_if_More_than_One_Coming(OR_New_NSFOnline.getProperty("Audits_AppealTab_CreateAppeal_button"));
		fnsGet_Element_Display_byPassing_ElementName_and_Xpath_WithoutOR("Create Appeal Screen", "'Create Appeal' button" ,CreateAppeal_Xpath);
		fnsLoading_Progressing_wait(1);
		TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(CreateAppeal_Xpath);
		fnsLoading_Progressing_wait(2);
		
		
		fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  NewNsfOnline_Element_Max_Wait_Time, "Created Successfully", 15);
		
		fnsVerifyScreenNavigation_afterClickingOnElement("'Create Appeal' button", "'Edit Appeal' screen", OR_New_NSFOnline.getProperty("Audits_Appeal_EditAppeal_ReasonNotes_Box"));
		
		
		fnsGet_Element_Display_byPassing_ElementName_and_Xpath_WithoutOR("Edit Appeal > 'Appeal Description' section", "'First Question' Name" ,OR_New_NSFOnline.getProperty("Audits_Appeal_EditAppeal_AppealReason_Label"));
		fnsGet_Element_Display_byPassing_ElementName_and_Xpath_WithoutOR("Edit Appeal > 'Appeal Description' section", "'Second Question' Name" ,OR_New_NSFOnline.getProperty("Audits_Appeal_EditAppeal_AppealPersonAvailable_Label"));
		
		String AppealReason = fnsGet_Field_TEXT("Audits_Appeal_EditAppeal_AppealReason_Label");
		String APPEAL_PERSON_AVAILABLE = fnsGet_Field_TEXT("Audits_Appeal_EditAppeal_AppealPersonAvailable_Label");
		
		try{
			assert ( (AppealReason.toLowerCase()).contains("reason for appeal") ):"FAILED == Appeal Description : First Question set naming (Reason for Appeal*) has been changed to ("+AppealReason+"*), plese refer the screen shot >> Question_Naming_Change"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED == Appeal Description : First Question set naming (Reason for Appeal*) is Matched");
			
			assert ( (APPEAL_PERSON_AVAILABLE.toLowerCase()).contains("were you present for the evaluation or debriefing") ):"FAILED == Appeal Description : Second Question set naming (Were you present for the evaluation or debriefing?*) has been changed to ("+APPEAL_PERSON_AVAILABLE+"*), plese refer the screen shot >> Question_Naming_Change"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED == Appeal Description : Second Question set naming (Were you present for the evaluation or debriefing?*) is Matched");
		}catch(Throwable t){
			fnsTake_Screen_Shot("Question_Naming_Change");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		
		
		fnsGet_Element_Enabled("Audits_Appeal_EditAppeal_AppealReason_2nd_Radio_button");
		fnsWait_and_Click("Audits_Appeal_EditAppeal_AppealReason_2nd_Radio_button");
		fnsLoading_Progressing_wait(1);
		
		fnsGet_Element_Enabled("Audits_Appeal_EditAppeal_ReasonNotes_Box");
		fnsWait_and_Type("Audits_Appeal_EditAppeal_ReasonNotes_Box", "Automation  "+AppealReason);
		fnsLoading_Progressing_wait(1);
		
		
		fnsGet_Element_Enabled("Audits_Appeal_EditAppeal_AppealPersonAvailable_1st_Radio_button");
		fnsWait_and_Click("Audits_Appeal_EditAppeal_AppealPersonAvailable_1st_Radio_button");
		fnsLoading_Progressing_wait(1);
		
		fnsGet_Element_Enabled("Audits_Appeal_EditAppeal_Person_Avail_Notes_Box");
		fnsWait_and_Type("Audits_Appeal_EditAppeal_Person_Avail_Notes_Box", "Automation  were you present for the evaluation or debriefing");
		fnsLoading_Progressing_wait(1);
		
		
		//Upload file
		String FileUploadPath = System.getProperty("user.dir") + "\\docs\\This is test pdf document.pdf";
		WebElement Browser = fnsGet_OR_New_NSFOnline_ObjectX("Audits_Appeal_EditAppeal_UploadFiles_browse_input");
		Browser.sendKeys(FileUploadPath);
		
		for(int wait=0; wait<=(NewNsfOnline_Element_Max_Wait_Time); wait++){
			if(driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("Audits_Appeal_EditAppeal_Upload_button"))).size()>0){
				fnsGet_OR_New_NSFOnline_ObjectX("Audits_Appeal_EditAppeal_Upload_button").click();
				fnsLoading_Progressing_wait(3);
				fnsApps_Report_Logs("PASSED == File is Successfully Upload.");
				fnsLoading_Progressing_wait(2);
				break;
			}else{
				Thread.sleep(1000);
			}
			if(wait==(NewNsfOnline_Element_Max_Wait_Time)){
				fnsTake_Screen_Shot("FileUploadFail");
				throw new Exception("FAILED == File upload is getting fail,after wait Time<"+(NewNsfOnline_Element_Max_Wait_Time)+">Seconds, Please refer screenshot >> FileUploadFail"+fnsScreenShot_Date_format());
			}
		}

		for(int i=0; i<=5; i++){
			try{
				fnsGet_Element_Enabled("Audits_Appeal_EditAppeal_Save_button");
				fnsGet_OR_New_NSFOnline_ObjectX("Audits_Appeal_EditAppeal_Save_button").click();
				fns_Verify_Success_message_coming_OR_Error_message_Coming(false,  5, "Saved Successful", 15);
				fnsLoading_Progressing_wait(2);
				break;				
			}catch(Throwable t){
				if(i==5){
					fnsTake_Screen_Shot("Success_Message_Fail");
					throw new Exception(Throwables.getStackTraceAsString(t));
				}else{
					fnsLoading_Progressing_wait(1);
				}
			}
		}
		
		fnsLoading_Progressing_wait(1);
		fnsVerifyScreenNavigation_afterClickingOnElement("Edit Appeal > 'Save'  button", "'Appeal' TAB", OR_New_NSFOnline.getProperty("Audits_Appeal_AddQuestion_button"));
		
		fnsGet_Element_Enabled("Audits_Appeal_AddQuestion_button");
		fnsWait_and_Click("Audits_Appeal_AddQuestion_button");
		fnsLoading_Progressing_wait(3);
		
		fnsVerifyScreenNavigation_afterClickingOnElement("'Add Question' button", "'Add Question' screen", OR_New_NSFOnline.getProperty("Audits_Appeal_AddQuestionScreen_First_Radio_button"));
		
		
		fnsGet_Element_Enabled("Audits_Appeal_AddQuestionScreen_First_Radio_button");
		fnsWait_and_Click("Audits_Appeal_AddQuestionScreen_First_Radio_button");
		fnsLoading_Progressing_wait(2);
				
		
		
		String Appeal_AddQuestionScreen_AddQuestions_button_Xpath  = TestSuiteBase_MonitorPlan.WithOut_OR_Return_XPATH_if_More_than_One_Coming(OR_New_NSFOnline.getProperty("Audits_Appeal_AddQuestionScreen_AddQuestions_button"));
		fnsGet_Element_Display_byPassing_ElementName_and_Xpath_WithoutOR("Appeal Tab", "'Create Appeal' button" ,Appeal_AddQuestionScreen_AddQuestions_button_Xpath);
		TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(Appeal_AddQuestionScreen_AddQuestions_button_Xpath);
			
		
		fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  NewNsfOnline_Element_Max_Wait_Time, "Created Successfully", 15);
		fnsLoading_Progressing_wait(2);
		
		fnsVerifyScreenNavigation_afterClickingOnElement("'Add Question' button", "'Edit Appeal' screen", OR_New_NSFOnline.getProperty("Audits_Appeal_EditAppeal_ReasonNotes_Box"));
		
		
		fnsGet_Element_Display_byPassing_ElementName_and_Xpath_WithoutOR("Edit Appeal > 'Appeal Description' section", "'First Question' Name" ,OR_New_NSFOnline.getProperty("Audits_Appeal_EditAppeal_AppealReason_Label"));
		fnsGet_Element_Display_byPassing_ElementName_and_Xpath_WithoutOR("Edit Appeal > 'Appeal Description' section", "'Second Question' Name" ,OR_New_NSFOnline.getProperty("Audits_Appeal_EditAppeal_AppealPersonAvailable_Label"));
		
		AppealReason = fnsGet_Field_TEXT("Audits_Appeal_EditAppeal_AppealReason_Label");
		APPEAL_PERSON_AVAILABLE = fnsGet_Field_TEXT("Audits_Appeal_EditAppeal_AppealPersonAvailable_Label");
		
		try{
			assert ( (AppealReason.toLowerCase()).contains("reason for appeal") ):"FAILED == Appeal Description : First Question set naming (Reason for Appeal*) has been changed to ("+AppealReason+"*), plese refer the screen shot >> Question_Naming_Change"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED == Appeal Description : First Question set naming (Reason for Appeal*) is Matched");
			
			assert ( (APPEAL_PERSON_AVAILABLE.toLowerCase()).contains("were you present for the evaluation or debriefing") ):"FAILED == Appeal Description : Second Question set naming (Were you present for the evaluation or debriefing?*) has been changed to ("+APPEAL_PERSON_AVAILABLE+"*), plese refer the screen shot >> Question_Naming_Change"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED == Appeal Description : Second Question set naming (Were you present for the evaluation or debriefing?*) is Matched");
		}catch(Throwable t){
			fnsTake_Screen_Shot("Question_Naming_Change");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		
		
		fnsGet_Element_Enabled("Audits_Appeal_EditAppeal_AppealReason_2nd_Radio_button");
		fnsWait_and_Click("Audits_Appeal_EditAppeal_AppealReason_2nd_Radio_button");
		fnsLoading_Progressing_wait(1);
		
		fnsGet_Element_Enabled("Audits_Appeal_EditAppeal_ReasonNotes_Box");
		fnsWait_and_Type("Audits_Appeal_EditAppeal_ReasonNotes_Box", "Automation  "+AppealReason);
		fnsLoading_Progressing_wait(1);
		
		
		fnsGet_Element_Enabled("Audits_Appeal_EditAppeal_AppealPersonAvailable_1st_Radio_button");
		fnsWait_and_Click("Audits_Appeal_EditAppeal_AppealPersonAvailable_1st_Radio_button");
		fnsLoading_Progressing_wait(1);
		
		fnsGet_Element_Enabled("Audits_Appeal_EditAppeal_Person_Avail_Notes_Box");
		fnsWait_and_Type("Audits_Appeal_EditAppeal_Person_Avail_Notes_Box", "Automation  were you present for the evaluation or debriefing");
		fnsLoading_Progressing_wait(1);
		
			
		fnsGet_Element_Enabled("Audits_Appeal_EditAppeal_Save_button");
		fnsGet_OR_New_NSFOnline_ObjectX("Audits_Appeal_EditAppeal_Save_button").click();
		fnsLoading_Progressing_wait(2);
		
		fnsGet_Element_Displayed("Audits_Appeal_Alert_Message");
		String QuestionSave_Alert_Message_TEXT = fnsGet_Field_TEXT("Audits_Appeal_Alert_Message");
	
		
		try{
			assert ( (QuestionSave_Alert_Message_TEXT.toLowerCase()).contains("No Attachments added. Are you sure you want to continue".toLowerCase()) ):
				"FAILED == Wrong messae ("+QuestionSave_Alert_Message_TEXT+") is coming after clicking on Submit All button, plese refer the screen shot >> QuestionSave_Incorrect_Message"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED == Correct messae is coming after clicking on Question Save button.");
		}catch(Throwable t){
			fnsTake_Screen_Shot("QuestionSave_Incorrect_Message");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		
		
		fnsGet_Element_Displayed("Audits_Appeal_Alert_OK_Button");
		fnsWait_and_Click("Audits_Appeal_Alert_OK_Button");
		
		fns_Verify_Success_message_coming_OR_Error_message_Coming(false,  5, "Saved Successful", 15);
		fnsLoading_Progressing_wait(2);
		
		
		
		
		
		
		
		fnsLoading_Progressing_wait(1);
		fnsVerifyScreenNavigation_afterClickingOnElement("Edit Appeal > 'Save'  button", "'Appeal' TAB", OR_New_NSFOnline.getProperty("Audits_Appeal_AddQuestion_button"));
		
	
		fnsGet_Element_Displayed("Audits_Appeal_SubmitAll_button");
		fnsWait_and_Click("Audits_Appeal_SubmitAll_button");
		fnsLoading_Progressing_wait(2);
		
		
		String SubmitAll_Alert_Message_TEXT = fnsGet_Field_TEXT("Audits_Appeal_Alert_Message");
	
		
		try{
			assert ( (SubmitAll_Alert_Message_TEXT.toLowerCase()).contains("You can submit Appeal only once for current Audit and the details cannot be modified once submitted. Are you sure you want to submit all".toLowerCase()) ):
				"FAILED == Wrong messae ("+SubmitAll_Alert_Message_TEXT+") is coming after clicking on Submit All button, plese refer the screen shot >> SubmitAll_Incorrect_Message"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED == Correct messae is coming after clicking on Submit All button.");
		}catch(Throwable t){
			fnsTake_Screen_Shot("SubmitAll_Incorrect_Message");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		
		
		fnsGet_Element_Displayed("Audits_Appeal_Alert_OK_Button");
		fnsWait_and_Click("Audits_Appeal_Alert_OK_Button");
		
		fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  NewNsfOnline_Element_Max_Wait_Time, "Appeal Submitted Successfully", 20);
		fnsLoading_Progressing_wait(3);
		
		
		
			
		try{
			assert ( (driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("Audits_Appeal_SubmitAll_button"))).size()==0) ):"FAILED == 'Submit All' button is still displayed, after appeal submittion, plese refer the screen shot >> SubmitAll_Button_Display"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED == 'Submit All' button is not displayed.");
		}catch(Throwable t){
			fnsTake_Screen_Shot("SubmitAll_Button_Display");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		
		
		try{
			assert ( (driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("Audits_Appeal_AddQuestion_button"))).size() == 0) ):"FAILED == 'Add Question' button is still displayed, after appeal submittion, plese refer the screen shot >> SubmitAll_Button_Display"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED == 'Add Question' button is not displayed.");
		}catch(Throwable t){
			fnsTake_Screen_Shot("AddQuestion_Button_Display");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		
		
		fnsGet_Element_Enabled("Audits_ReviewAppeal_LabelLink");
		fnsWait_and_Click("Audits_ReviewAppeal_LabelLink");
		fnsLoading_Progressing_wait(3);
		
		
		fnsGet_Element_Enabled("Audits_ReviewAppeal_AddComments_Box");
		fnsWait_and_Clear("Audits_ReviewAppeal_AddComments_Box");
		fnsWait_and_Type("Audits_ReviewAppeal_AddComments_Box", "Autoimation  Approved");
		
		
		fnsGet_Element_Enabled("Audits_ReviewAppeal_ApproverName_Box");
		fnsWait_and_Clear("Audits_ReviewAppeal_ApproverName_Box");
		fnsWait_and_Type("Audits_ReviewAppeal_ApproverName_Box", "testscriptuser");
		
		
		
		fnsGet_Element_Enabled("Audits_ReviewAppeal_Approve_Bttn");
		fnsWait_and_Click("Audits_ReviewAppeal_Approve_Bttn");
		
		fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  NewNsfOnline_Element_Max_Wait_Time, "Approved Successfully", 15);
		fnsLoading_Progressing_wait(2);
		
		fnsVerifyScreenNavigation_afterClickingOnElement("Edit Appeal > 'Approve'  button", "'Appeal' TAB", OR_New_NSFOnline.getProperty("Audits_AppealTab_Filter_Status_DD"));
		
		fnsDD_value_Select_TagOPTION_DDTypeSelect("Audits_AppealTab_Filter_Status_DD", "SUBMITTED");
		fnsLoading_Progressing_wait(2);
		
		fnsGet_Element_Enabled("Audits_ReviewAppeal_LabelLink");
		fnsWait_and_Click("Audits_ReviewAppeal_LabelLink");
		fnsLoading_Progressing_wait(3);
		
		
		fnsGet_Element_Enabled("Audits_ReviewAppeal_AddComments_Box");
		fnsWait_and_Clear("Audits_ReviewAppeal_AddComments_Box");
		fnsWait_and_Type("Audits_ReviewAppeal_AddComments_Box", "Autoimation  Rejected");
		
		
		fnsGet_Element_Enabled("Audits_ReviewAppeal_ApproverName_Box");
		fnsWait_and_Clear("Audits_ReviewAppeal_ApproverName_Box");
		fnsWait_and_Type("Audits_ReviewAppeal_ApproverName_Box", "testscriptuser");
		
		
		//Upload file
		Browser = fnsGet_OR_New_NSFOnline_ObjectX("Audits_ReviewAppeal_UploadFiles_browse_input");
		Browser.sendKeys(FileUploadPath);
		
		for(int wait=0; wait<=(NewNsfOnline_Element_Max_Wait_Time); wait++){
			if(driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("Audits_ReviewAppeal_UploadFiles_Upload_button"))).size()>0){
				fnsGet_OR_New_NSFOnline_ObjectX("Audits_ReviewAppeal_UploadFiles_Upload_button").click();
				fnsLoading_Progressing_wait(3);
				fnsApps_Report_Logs("PASSED == File is Successfully Upload.");
				fnsLoading_Progressing_wait(3);
				break;
			}else{
				Thread.sleep(1000);
			}
			if(wait==(NewNsfOnline_Element_Max_Wait_Time)){
				fnsTake_Screen_Shot("FileUploadFail");
				throw new Exception("FAILED == File upload is getting fail,after wait Time<"+(NewNsfOnline_Element_Max_Wait_Time)+">Seconds, Please refer screenshot >> FileUploadFail"+fnsScreenShot_Date_format());
			}
		}
		
		
				
		fnsGet_Element_Enabled("Audits_ReviewAppeal_Reject_Bttn");
		fnsWait_and_Click("Audits_ReviewAppeal_Reject_Bttn");
		
		
			
		
		fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  NewNsfOnline_Element_Max_Wait_Time, "Rejected Successfully", 15);
		fnsLoading_Progressing_wait(2);
		
		fnsVerifyScreenNavigation_afterClickingOnElement("Edit Appeal > 'Reject'  button", "'Appeal' TAB", OR_New_NSFOnline.getProperty("Audits_AppealTab_Filter_Status_DD"));
		
		fnsDD_value_Select_TagOPTION_DDTypeSelect("Audits_AppealTab_Filter_Status_DD", "REJECTED");
		fnsLoading_Progressing_wait(2);
		
		
		fnsGet_Element_Enabled("Audits_EscalateAppeal_LabelLink");
		fnsWait_and_Click("Audits_EscalateAppeal_LabelLink");
		fnsLoading_Progressing_wait(2);
		
		

		fnsGet_Element_Enabled("Audits_ReviewAppeal_AddComments_Box");
		fnsWait_and_Clear("Audits_ReviewAppeal_AddComments_Box");
		fnsWait_and_Type("Audits_ReviewAppeal_AddComments_Box", "Autoimation  Escalated");
		
				
		fnsGet_Element_Enabled("Audits_ReviewAppeal_Escalate_Bttn");
		fnsWait_and_Click("Audits_ReviewAppeal_Escalate_Bttn");
		
		fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  NewNsfOnline_Element_Max_Wait_Time, "Appeal has been Escalated successfully", 25);
		fnsLoading_Progressing_wait(2);
		
		fnsVerifyScreenNavigation_afterClickingOnElement("Edit Appeal > 'Escalate'  button", "'Appeal' TAB", OR_New_NSFOnline.getProperty("Audits_AppealTab_Filter_Status_DD"));
		
		
		fnsDD_value_Select_TagOPTION_DDTypeSelect("Audits_AppealTab_Filter_Status_DD", "ESCALATED");
		fnsLoading_Progressing_wait(2);
		
		fnsGet_Element_Enabled("Audits_ReviewAppeal_LabelLink");
		fnsWait_and_Click("Audits_ReviewAppeal_LabelLink");
		fnsLoading_Progressing_wait(3);
		

		fnsGet_Element_Enabled("Audits_ReviewAppeal_AddComments_Box");
		fnsWait_and_Clear("Audits_ReviewAppeal_AddComments_Box");
		fnsWait_and_Type("Audits_ReviewAppeal_AddComments_Box", "Automation Escalate to Rejected");
		
		
		fnsGet_Element_Enabled("Audits_ReviewAppeal_Reject_Bttn");
		fnsWait_and_Click("Audits_ReviewAppeal_Reject_Bttn");
		
		fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  NewNsfOnline_Element_Max_Wait_Time, "Rejected Successfully", 15);
		fnsLoading_Progressing_wait(2);
		
		fnsVerifyScreenNavigation_afterClickingOnElement("Edit Appeal > 'Reject'  button", "'Appeal' TAB", OR_New_NSFOnline.getProperty("Audits_AppealTab_Filter_Status_DD"));
		
		fnsDD_value_Select_TagOPTION_DDTypeSelect("Audits_AppealTab_Filter_Status_DD", "REJECTED");
		fnsLoading_Progressing_wait(2);
		
		
		
		try{
			assert ( (driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("Audits_ReviewAppeal_LabelLink"))).size()==0) ):"FAILED == 'Review' button is still displayed for the Question, after Twice rejection of the Question, plese refer the screen shot >> Review_Button_Display"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED == 'Submit All' button is not displayed.");
		}catch(Throwable t){
			fnsTake_Screen_Shot("Review_Button_Display");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		
		

		try{
			assert ( (driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("Audits_EscalateAppeal_LabelLink"))).size() == 0) ):"FAILED == 'Escalate' button is still displayed for the Question, after Twice rejection of the Question, plese refer the screen shot >> Escalate_Button_Display"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED == 'Escalate' button is not displayed.");
		}catch(Throwable t){
			fnsTake_Screen_Shot("Escalate_Button_Display");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		
		
		fnsGet_Element_Enabled("Audits_ViewAppeal_LabelLink");
		fnsWait_and_Click("Audits_ViewAppeal_LabelLink");
		fnsLoading_Progressing_wait(3);
		
		
		fnsVerifyScreenNavigation_afterClickingOnElement("Appeal TAB > 'View'  button", "'View' Appeal Screen", OR_New_NSFOnline.getProperty("Audits_ViewAppeal_GoBack_button"));
		
		
	}catch(Throwable t){
		isTestCasePass = false;
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
	
	
}
*/


	// 
	public String fnDB_Return_AuditNo_onWhich_Appeal_will_Create(String Query) throws Throwable {

		fnsApps_Report_Logs("=========================================================================================================================================");
		
		
		/*String Query = "SELECT distinct oaa1.AUDIT_REPORT_SENT_DATE,  CASE    WHEN OA.ANNOUNCED_FLG = 'Y'    OR OAP.AUDIT_STATUS  <> 'CREATED'    THEN TO_CHAR(OAP.SCHDL_START_DATETIME, 'HH:MI AM') || ' - '      || TO_CHAR(OAP.SCHDL_END_DATETIME, 'HH:MI AM')    WHEN OA.ANNOUNCED_FLG = 'N'    THEN '-'    WHEN OA.ANNOUNCED_FLG = 'N'    AND EXISTS      (SELECT 1  FROM TABLE (FN_GET_USER_ROLES(949)) FN ,        CW_ACTIONS CA ,        CW_ACTION_MAPPINGS CAM ,        CW_ROLE_PERMISSIONS CRP      WHERE FN.ROLE_NAME  = CRP.ROLE_NAME AND CRP.CAM_SEQ     = CAM.SEQ      AND CAM.ACTION_NAME = CA.ACTION_NAME      AND CA.ACTION_NAME  = 'NSFII.VIEW.UNANNOUNCED.AUDITS'      )    THEN TO_CHAR(OAP.SCHDL_START_DATETIME, 'HH:MI AM') || ' - '      || TO_CHAR(OAP.SCHDL_END_DATETIME, 'HH:MI AM')  END       AS AUDIT_TIME,  CW_FN_REPLACE(CUS.TIME_ZONE) AS TIME_ZONE,  OA.AUDIT_NO                  AS AUDIT_NO,  NVL(CUS.STORE_NO,CUS.CODE)   AS SITE_NO,  OA.CAR_STATUS      AS CAR_STATUS,  CASE    WHEN OA.ANNOUNCED_FLG = 'Y'    OR OAP.AUDIT_STATUS  <> 'CREATED'    THEN CW_FN_DATE_TO_STRING(OAP.SCHDL_START_DATETIME)   WHEN OA.ANNOUNCED_FLG = 'N'    AND EXISTS      (SELECT 1      FROM TABLE (FN_GET_USER_ROLES((select seq from cw_user_owners where user_seq in (select seq from cw_users where username='"+
						Submitter_LoginUser+
						"')))) FN , CW_ACTIONS CA,        CW_ACTION_MAPPINGS CAM,        CW_ROLE_PERMISSIONS CRP      WHERE FN.ROLE_NAME  = CRP.ROLE_NAME      AND CRP.CAM_SEQ     = CAM.SEQ      AND CAM.ACTION_NAME = CA.ACTION_NAME AND CA.ACTION_NAME  = 'NSFII.VIEW.UNANNOUNCED.AUDITS'      )    THEN CW_FN_DATE_TO_STRING(OAP.SCHDL_START_DATETIME)    WHEN OA.ANNOUNCED_FLG = 'N'    THEN '01-Jan-1900'  END          AS AUDIT_DATE,  DECODE('RFS','ISR',STD.NAME,OA.AUDIT_TYPE) AS AUDIT_TYPE,  CUS.NAME           AS SITE_NAME,  CUS.Address_line1  || ' '  || cus.city  || ' '  || DECODE(sta.cou_code,'USA',sta.abbrev,'CAN',sta.abbrev,STA.NAME)  || ' '  || CUS.POSTAL_CODE  ||' '  || DECODE(COU.code,'USA','USA','CAN','CAN',COU.NAME) AS SITE_FULL_ADDRESS,  (SELECT LISTAGG(AA.APPEAL_STATUS, ',') WITHIN GROUP (  ORDER BY AA.APPEAL_STATUS)  FROM CW_AUDIT_APPEALS AA,    OA_AUDIT_SEC_QUESTIONS OASQ  WHERE AA.AUSQ_SEQ = OASQ.SEQ  AND OASQ.AUDIT_NO = OA.AUDIT_NO  )   AS APPEAL_STATUS,  CUST.NAME     AS CUS_NAME,  CW_FN_DATE_TO_STRING(OAP.SCHDL_START_DATETIME) AS AUDIT_DATE_ADDL_FILTER,  CUS.SEQ       AS SEQ FROM TABLE(FN_GET_AUDITS((select seq from cw_user_owners where user_seq in (select seq from cw_users where username='"+
						Submitter_LoginUser+
						"')),'CO')) OA INNER JOIN CUSTOMERS CUS ON CUS.CODE=OA.FACILITY_CODE INNER JOIN OA_AUDIT_PERFORM OAP ON OAP.AUDIT_NO        =OA.AUDIT_NO AND OA.AUDIT_TYPE NOT IN  (SELECT sca_scheme FROM iq_sca_settings WHERE sca_scheme IS NOT NULL  ) INNER JOIN OA_VISITS OV ON OV.VISIT_NO = OA.VISIT_NO LEFT OUTER JOIN OA_AUDIT_TYPE_MASTER OAM ON OAM.AUDIT_TYPE = OA.AUDIT_TYPE LEFT OUTER JOIN STANDARDS STD ON STD.CODE = OAM.AUDIT_TYPE INNER JOIN CUSTOMERS CUST ON OA.COCL_CODE = CUST.CODE LEFT JOIN COUNTRIES COU ON COU.CODE=CUS.COU_CODE LEFT JOIN STATES STA ON STA.CODE=CUS.STATE_CODE INNER JOIN CW_USER_OWNERS CUO ON CUO.SEQ    =OA.CUO_SEQ join OA_AUDIT_SEC_QUESTIONS OASQ on OA.AUDIT_NO = OASQ.AUDIT_NO join oa_audits oaa1 on oaa1.audit_no =OASQ.AUDIT_NO WHERE UPPER(OAP.ATTEMPTED_FLG)='N' AND OAP.AUDIT_STATUS          ='COMPLETED' and OASQ.SEQ not in (select AA.AUSQ_SEQ  from CW_AUDIT_APPEALS AA where AA.AUSQ_SEQ =OASQ.SEQ) and oaa1.AUDIT_REPORT_SENT_DATE > sysdate - 8"; 

				*/
		Connection connection = null;
		String AuditNumber = null;
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = fnpGetDBConnection();
			Statement stmt = connection.createStatement();

			ResultSet rs = stmt.executeQuery(Query);
			
			while(rs.next()){
				AuditNumber = rs.getString("AUDIT_no");
				break;
			}
			
				
			connection.commit();
			connection.close();

		} catch (SQLException e) {
			fnsApps_Report_Logs("Data base Error : "+ Throwables.getStackTraceAsString(e).trim());
			throw new Exception("Data base Error : "+ Throwables.getStackTraceAsString(e).trim());
		} finally {
			if (!(connection == null)) {
				connection.close();
			}
		}
		fnsApps_Report_Logs("**** Query1 Executed Successfully and the Audit Number is = "+AuditNumber);
		fnsApps_Report_Logs("=========================================================================================================================================");
		return AuditNumber;
	}


	
	
//Function to run DB Update/Delete queries
public void fnsDB_Delete_all_Data_of_the_AuditNo_onWhich_Appeal_will_Create(String Audit_Number) throws Throwable {
	Connection connection = null;
	try{
		fnsApps_Report_Logs("=========================================================================================================================================");
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		    
		
		connection = fnpGetDBConnection(); 
		 
		Statement stmt= connection.createStatement();
			   
		//Query1.
		String query1="delete CW_APPEAL_ATTACHMENTS where APPEAL_SEQ in(select caa.SEQ from CW_AUDIT_APPEALS caa where caa.ausq_seq like '"+Audit_Number+"%')";
		
		//Query2
		String query2 ="delete CW_AUDIT_APPEALS where ausq_seq like '"+Audit_Number+"%'";
		   
		//Query3
		String query3="update oa_audits set AUDIT_REPORT_SENT_DATE=sysdate -1 where audit_no="+Audit_Number;
			 
		stmt.executeUpdate(query1);
		connection.commit();
		fnsApps_Report_Logs("**** Query1 Executed Successfully. >> "+query1); 
		 
		 
		stmt.executeUpdate(query2);
		connection.commit();
		fnsApps_Report_Logs("**** Query2 Executed Successfully. >> "+query2); 
		 
		stmt.executeUpdate(query3);
		connection.commit();
		fnsApps_Report_Logs("**** Query3 Executed Successfully. >> "+query3); 
			 
			
		connection.close();
		   
	   
	}catch (SQLException e) {
		fnsApps_Report_Logs("Data base Error : "+ Throwables.getStackTraceAsString(e).trim());
		throw new Exception ("Data base Error : "+ Throwables.getStackTraceAsString(e).trim());
	}finally {
		if( !(connection==null) ){
			connection.close();
		}
	}
		fnsApps_Report_Logs("=========================================================================================================================================");
}	
	
	
//#######################################################  Configuration Method  ################################################################
@AfterTest
public void reportTestResult() throws Throwable {
	TestSuiteBase_MonitorPlan.fns_ReportTestResult_atClassLevel(New_NSFOnline_Suitexls,  (this.getClass().getSimpleName()), isTestCasePass );
}


@AfterMethod
public void QuitBrowser(){
	try{
		driver.quit();
	}catch(Throwable t){
		//nothing to do
	}
}

}