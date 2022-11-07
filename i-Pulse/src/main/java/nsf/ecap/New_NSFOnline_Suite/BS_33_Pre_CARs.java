package nsf.ecap.New_NSFOnline_Suite;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;


public class BS_33_Pre_CARs extends TestSuiteBase_New_NSFOnline {



@Parameters({ "className" })
@BeforeTest
public void checkTestSkip(String className) throws Exception {
	if (className.isEmpty()) {
		className = this.getClass().getSimpleName();
	}
	IsBrowserPresentAlready =false;
	fnsCheckClassLevelTestSkip(className);
}


public String Audit_number = "2163198";
//UPDATE OA_CORR_ACTIONS SET CA_STATUS = 'PENDING' WHERE AUDIT_NO = '2163198';


//##########################################   EXCEL Variable ###############################################################################################

public String CorrectiveActions_View_Name = " - "+fnsReturn_CurrentTime();
public String CorrectiveActions_View_Name_EDIT = " - "+fnsReturn_CurrentTime();
public String CorrectiveActions_CreateView_Step2_Status_DD = null;
public BS_03_CheckList BS_03_CheckList = new BS_03_CheckList();








@Test( priority = 0)
public void Launch_Browser_and_Successfully_Login_into_the_Application() throws Throwable{
	try{
		CorrectiveActions_View_Name = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "CorrectiveActions_View_Name");
		CorrectiveActions_View_Name_EDIT = CorrectiveActions_View_Name+"-Edited";
		CorrectiveActions_CreateView_Step2_Status_DD = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "CorrectiveActions_CreateView_Step2_Status_DD");
		fnsDB_Update_CAR_Status_to_Pending(Audit_number);
			
		fnsApps_Report_Logs("################################## [BS-33] Verify View, AdvanceSearch, Alerts and CAR Approved");
		if (!IsBrowserPresentAlready) {
			IsBrowserPresentAlready = fnsBrowserLaunchAndLogin(Login_UserName, Login_Password);
			fnsSwitchAcoount_MultiAccess(this.getClass().getSimpleName(), "SwitchAccount_DD");
		}
	}catch(Throwable t){
		isTestCasePass = false;
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
	fnsApps_Report_Logs("=========================================================================================================================================");
}





@Test( priority = 1, dependsOnMethods={"Launch_Browser_and_Successfully_Login_into_the_Application"}, description="")
public void Verify_ViewCreateEdit_AdvanceSearch_Alerts_and_CARApproved() throws Throwable{
	fnsApps_Report_Logs("################### Test Case ::1 Create_Edit_and_Delete_Views_in_CorrectiveActions_and_in_SNAPSHOT_and_CrossMatch_of_Records_Count_Verify_AdvanceSearch_and_Alerts ");
	try{	
		fnsLoading_Progressing_wait(5);
		fncMenu_Ajax_Link_Click_By_PassingAjaxPath("CorrectiveActions_Ajax");
				
		fncNavigation_Verify_Application_Navigated_To_ViewScreen("'Corrective Actions' Menu Ajax", "CorrectiveActions");
				
		String View_Exists = fncVerify_View_Display_Open_and_Delete_it(2, CorrectiveActions_View_Name_EDIT, "View_Delete_Link", "View_Remove_Link");
		
		if( (View_Exists.contains("View Not Exists")) ){
			fncVerify_View_Display_Open_and_Delete_it(2, CorrectiveActions_View_Name, "View_Delete_Link", "View_Remove_Link");
		}
		
		fnsGet_Element_Enabled("CreateNewView_Link");
		fnsWait_and_Click("CreateNewView_Link");
		fnsLoading_Progressing_wait(2);
		
		fnsGet_Element_Enabled("CreateView_Step1_ViewName");
		fnsLoading_Progressing_wait(1);
		fnsWait_and_Type("CreateView_Step1_ViewName", CorrectiveActions_View_Name);
		Thread.sleep(3000);
				
		fnsDD_Value_select_by_DDLabelName_MultiselectDD("Status", CorrectiveActions_CreateView_Step2_Status_DD);
		
		fnsCreateView_Step3_Verify_ColumnExists_into_DD_ifNot_Then_Select("Status");
			
		fnsGet_Element_Enabled("CreateView_Step4_VisibleToAllUsers_Radio_bttn");
		fnsWait_and_Click("CreateView_Step4_VisibleToAllUsers_Radio_bttn");
		
		fnsGet_Element_Enabled("CreateView_CreateView_Bttn");
		fnsWait_and_Click("CreateView_CreateView_Bttn");
		
		fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  60, "A new view has been added to your list", 25);
		
		fncNavigation_Verify_Application_Navigated_To_ViewScreen("'CreateView' button", "Corrective Actions");
				
		fncClickonFirstFacilityfromViewResultTable_n_ReturnBackonViewScreen("Corrective Actions", "CAR-NO");
		
		
		
		fnsGet_Element_Enabled("Edit_View_Link");
		fnsWait_and_Click("Edit_View_Link");
		fnsLoading_Progressing_wait(2);
		
		fnsGet_Element_Enabled("CreateView_Step1_ViewName");
		fnsLoading_Progressing_wait(3);
		fnsWait_and_Clear("CreateView_Step1_ViewName");
		Thread.sleep(500);
		
		fnsWait_and_Type("CreateView_Step1_ViewName", CorrectiveActions_View_Name_EDIT);
		Thread.sleep(3000);
		
		fnsGet_Element_Enabled("CreateView_Step1_Alert_CheckBox");
		fnsWait_and_Click("CreateView_Step1_Alert_CheckBox");
		fnsLoading_Progressing_wait(1);
		
		fnsGet_Element_Enabled("CreateView_Step1_Alert_CheckBox_AssignColor_RED_Radio_bttn");
		fnsWait_and_Click("CreateView_Step1_Alert_CheckBox_AssignColor_RED_Radio_bttn");
		fnsLoading_Progressing_wait(1);
		
		
		fnsGet_Element_Enabled("CreateView_CreateView_Bttn");
		fnsWait_and_Click("CreateView_CreateView_Bttn");
	
		fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  60, "View has been updated successfully", 25);
	
		fncNavigation_Verify_Application_Navigated_To_ViewScreen("'UpdateView' button", "Corrective Actions");
		
		fns_AlertScreen_Click_on_AlertMenu_and_Verify_View_and_Label_Present(true, true, "Corrective Actions", CorrectiveActions_View_Name_EDIT);
		
		
		fnsGet_Element_Enabled("View_AdvanceSearch_Bttn");
		fnsLoading_Progressing_wait(1);
		fnsWait_and_Click("View_AdvanceSearch_Bttn");
		fnsLoading_Progressing_wait(5);
					
		fnsGet_Element_Enabled("AuditsAdvanceSearch_Search_Bttn");
		fnsLoading_Progressing_wait(3);
		fnsWait_and_Clear("CorrectiveActions_AdvanceSearch_AuditNo");
		fnsWait_and_Type("CorrectiveActions_AdvanceSearch_AuditNo", Audit_number);
		
		
		
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
		
		
		
		String ViewFirstRecord_AuditCode_Column_Xpath= OR_New_NSFOnline.getProperty("View_Result_Table")+"/tr[1]/td["+fnsReturn_ColumnNumber_from_TableHeaderXpath_WithoutOR(OR_New_NSFOnline.getProperty("View_Result_TableHeader"), "Audit No.")+"]";
		fnsw_Click_on_Table_Link_by_Passing_Cell_Xpath__and__Return_CellValue(ViewFirstRecord_AuditCode_Column_Xpath);
		
		fnsVerifyScreenNavigation_afterClickingOnElement("'"+Audit_number+"' link", "Default 'Audit_Info' Tab", OR_New_NSFOnline.getProperty("Audits_Audit_Info_Tab_ByDefault_Opened"));
		
		fnsGet_Element_Enabled("Audits_CorrectiveAction_Tab");
		fnsWait_and_Click("Audits_CorrectiveAction_Tab");
		fnsLoading_Progressing_wait(2);
		
		
		fnsGet_Element_Enabled("Audits_CorrectiveAction_Tab_First_Respond_Button");
		fnsWait_and_Click("Audits_CorrectiveAction_Tab_First_Respond_Button");
		fnsLoading_Progressing_wait(5);
		
		
		fnsVerifyScreenNavigation_afterClickingOnElement("'Respond' button", "Respond screen", OR_New_NSFOnline.getProperty("CA_RespondBttn_ROOTCAUSEANALYSIS_Text"));
				
		try{			
			assert ( driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("Audits_CorrectiveAction_Tab_SubmitAll_button"))).size()==0 ):"FAILED == 'ShowAll' button is coming on CA respond screen, As Expected it should not display, please refer the screen shot >> ShowAll_button_display"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED == 'ShowAll' button is NOT coming on CA respond screen.");
		}catch(Throwable t){
			fnsTake_Screen_Shot("ShowAll_button_display");
			throw new Exception (Throwables.getStackTraceAsString(t));
		}
		
		String Pending_Car_Count_String = fnsGet_Field_TEXT("CA_RespondBttn_Pagination_DD").trim();
		Pending_Car_Count_String = Pending_Car_Count_String.split("of")[1].trim();
		Integer Pending_Car_Count = Integer.parseInt(Pending_Car_Count_String);
		
		
		for(int CarPage=1; CarPage<=Pending_Car_Count; CarPage++){
			try{
				BS_03_CheckList.fncCAR_Respond_Details_Screen_FieldsList_and_Type(Login_UserName);
			
				if ( (CarPage<Pending_Car_Count) && (fnsGet_OR_New_NSFOnline_ObjectX("CA_RespondBttn_Save_nd_Next_button").isDisplayed()) ){
					fnsGet_Element_Enabled("CA_RespondBttn_Save_nd_Next_button");
					fnsWait_and_Click("CA_RespondBttn_Save_nd_Next_button");
					try{
						fns_Verify_Success_message_coming_OR_Error_message_Coming(false, 10,  "save successful", 10);
					}catch(Throwable t){
						fnsLoading_Progressing_wait(1);
					}
					fnsLoading_Progressing_wait(2);
					
				} else if (CarPage==Pending_Car_Count && (fnsGet_OR_New_NSFOnline_ObjectX("CA_RespondBttn_Save_nd_Next_button_Disabled").isDisplayed()) ){
					//upload file
					String FileUploadPath = System.getProperty("user.dir") + "\\docs\\This is test pdf document.pdf";
					WebElement Browser = fnsGet_OR_New_NSFOnline_ObjectX("CA_RespondBttn_UploadFiles_browse_input");
					Browser.sendKeys(FileUploadPath);
					
					for(int wait=0; wait<=(NewNsfOnline_Element_Max_Wait_Time); wait++){
						if(driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("CA_RespondBttn_Upload_button2"))).size()>0){
							fnsGet_Element_Enabled("CA_RespondBttn_Upload_button2");
							fnsGet_OR_New_NSFOnline_ObjectX("CA_RespondBttn_Upload_button2").click();
							fnsLoading_Progressing_wait(3);
							fnsApps_Report_Logs("PASSED == File is Successfully Upload.");
							break;
						}else{
							Thread.sleep(1000);
						}
						if(wait==(NewNsfOnline_Element_Max_Wait_Time)){
							fnsTake_Screen_Shot("FileUploadFail");
							throw new Exception("FAILED == File upload is getting fail,after wait Time<"+(NewNsfOnline_Element_Max_Wait_Time)+">Seconds, Please refer screenshot >> FileUploadFail"+fnsScreenShot_Date_format());
						}
					}					
					
					fnsLoading_Progressing_wait(2);
				} else if (CarPage==Pending_Car_Count){
					fnsTake_Screen_Shot("SaveNext_Display_on_LastScreen");
					throw new Exception("FAILED == Save & Next button is displayed on <"+CarPage+"> last screen. As excepted it should not be display, please refer the screen shot >>SaveNext_Display_on_LastScreen"+fnsScreenShot_Date_format());
				}
			}catch(Throwable t){
				throw new Exception("Respond screen >>>> CorrectiveActions save is getting fail on screen <"+CarPage+"> : "+Throwables.getStackTraceAsString(t));
			}
		}
		
		
		fnsGet_Element_Enabled("CA_RespondBttn_Submit_button");
		fnsWait_and_Click("CA_RespondBttn_Submit_button");
		fns_Verify_Success_message_coming_OR_Error_message_Coming(true, 120, "Corrective Action Submitted Successfully", 15);
		
		
		
		
		
		
		

		try{
			driver.quit();
			Thread.sleep(3000);
		}catch(Throwable t){
			//nothing to do
		}
		
		
		/*Login_UserName = fnsReturn_ExcelCellValue_ByMatchingColumnName("BS_03_CheckList", "Login_UserName_Password_to_Approve_CA").split(":")[0].trim();
		Login_Password = fnsReturn_ExcelCellValue_ByMatchingColumnName("BS_03_CheckList", "Login_UserName_Password_to_Approve_CA").split(":")[1].trim();
		Login_Application_Name = fns_Return_Login_Application_From_TestCaseSheet(New_NSFOnline_Suitexls, "BS_03_CheckList");
		fnsBrowserLaunchAndLogin(Login_UserName, Login_Password);*/
		fnsBrowserLaunchAndLogin("6n670admin", "Welcome123");
		fnsSwitchAcoount_MultiAccess(this.getClass().getSimpleName(), "SwitchAccount_DD");	
		
		
		fncMenu_Ajax_Link_Click_By_PassingAjaxPath("CorrectiveActions_Ajax");
		
		fncNavigation_Verify_Application_Navigated_To_ViewScreen("'Corrective Actions' Menu Ajax", "CorrectiveActions");
		
		fnsGet_Element_Enabled("View_AdvanceSearch_Bttn");
		fnsLoading_Progressing_wait(1);
		fnsWait_and_Click("View_AdvanceSearch_Bttn");
		fnsLoading_Progressing_wait(5);
					
		fnsGet_Element_Enabled("AuditsAdvanceSearch_Search_Bttn");
		fnsLoading_Progressing_wait(3);
		fnsWait_and_Clear("CorrectiveActions_AdvanceSearch_AuditNo");
		fnsWait_and_Type("CorrectiveActions_AdvanceSearch_AuditNo", Audit_number);
		
		
		
		fnsWait_and_Click("AuditsAdvanceSearch_Search_Bttn");
		fnsLoading_Progressing_wait(5);
		fnsGet_Element_Enabled("AuditsAdvanceSearch_Result_Table");
		TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element(OR_New_NSFOnline.getProperty("AuditsAdvanceSearch_Result_Table"));
		
		fnsLoading_Progressing_wait(1);
		Result_Table_Text = 		TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(OR_New_NSFOnline.getProperty("AuditsAdvanceSearch_Result_Table")).getText().toLowerCase().trim();
		if( !(Result_Table_Text.contains(Audit_number.toLowerCase().trim())) ){
			fnsTake_Screen_Shot("AuditNumber_Not_Coming");
			throw new Exception ("FAILED == Audit Number <"+Audit_number+"> is not coming into the advance search result, please refer the screen shot >> AuditNumber_Not_Coming"+fnsScreenShot_Date_format());
		}
		
		
		
		ViewFirstRecord_AuditCode_Column_Xpath= OR_New_NSFOnline.getProperty("View_Result_Table")+"/tr[1]/td["+fnsReturn_ColumnNumber_from_TableHeaderXpath_WithoutOR(OR_New_NSFOnline.getProperty("View_Result_TableHeader"), "Audit No.")+"]";
		fnsw_Click_on_Table_Link_by_Passing_Cell_Xpath__and__Return_CellValue(ViewFirstRecord_AuditCode_Column_Xpath);
		
		fnsVerifyScreenNavigation_afterClickingOnElement("'"+Audit_number+"' link", "Default 'Audit_Info' Tab", OR_New_NSFOnline.getProperty("Audits_Audit_Info_Tab_ByDefault_Opened"));
		
		fnsGet_Element_Enabled("Audits_CorrectiveAction_Tab");
		fnsWait_and_Click("Audits_CorrectiveAction_Tab");
		fnsLoading_Progressing_wait(2);
		
		
		
		Thread.sleep(1500);
		fnsDD_value_Select_TagOPTION_DDTypeSelect("Audits_CorrectiveAction_Tab_Filter_DD", "Pending Approval" , (NewNsfOnline_Element_Max_Wait_Time/2));
		fnsLoading_Progressing_wait(2);
		
		
		
		
		fnsGet_Element_Enabled("CheckList_CorrectiveActions_Submited_CA_CheckBox");
		fnsWait_and_Click("CheckList_CorrectiveActions_Submited_CA_CheckBox");
		fnsLoading_Progressing_wait(1);
		
		
		fnsGet_Element_Enabled("CheckList_CorrectiveActions_Submited_CA_Approve_ApproverComments");
		fnsWait_and_Type("CheckList_CorrectiveActions_Submited_CA_Approve_ApproverComments", "Automation -- Car Approved");
		
		fnsWait_Click_on_First_Visible_Element_if_More_than_One_Coming("Approve Button", OR_New_NSFOnline.getProperty("CheckList_CorrectiveActions_Submited_CA_Approve_Bttn"));
		
		fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  120, "Corrective Action(s) Approved Successfully", 25);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	}catch(Throwable t){
		isTestCasePass = false;
		fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
	
	
}











//Function to run DB Select/Update queries for fetching default screen name.
	public void fnsDB_Update_CAR_Status_to_Pending(String AuditNo) throws Throwable {
		Connection connection = null;
		try{
			fnsApps_Report_Logs("=========================================================================================================================================");
			
			Class.forName("oracle.jdbc.driver.OracleDriver");			    
			
			connection = fnpGetDBConnection(); 
			 
			Statement stmt= connection.createStatement();
			String Update_Query ="UPDATE OA_CORR_ACTIONS SET CA_STATUS = 'PENDING' WHERE AUDIT_NO = '"+AuditNo+"'";
			stmt.executeUpdate(Update_Query);
			connection.commit();
			connection.close();
			fnsApps_Report_Logs("**** Update Query Executed Successfully. >> "+Update_Query); 
		}catch (SQLException e) {
			fnsApps_Report_Logs("Data base Error : "+ Throwables.getStackTraceAsString(e).trim());
			throw new Exception ("Data base Error : "+ Throwables.getStackTraceAsString(e).trim());
		}finally {
			if( !(connection==null) ){
				connection.close();
			}
		}
	}	









//#######################################################  Configuration Method  ################################################################
@AfterTest
public void reportTestResult() throws Throwable {
	TestSuiteBase_MonitorPlan.fns_ReportTestResult_atClassLevel(New_NSFOnline_Suitexls,  (this.getClass().getSimpleName()), isTestCasePass );
}


@AfterTest
public void QuitBrowser(){
	TestSuiteBase_MonitorPlan.MoveMouseCursorToTaskBar();
	driver.quit();
}

}