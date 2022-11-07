package nsf.ecap.New_NSFOnline_Suite;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;

public class BS_28_Findings extends TestSuiteBase_New_NSFOnline {
	
	public String  Findings_View_Name = "Automation - Findings";
	public String  Findings_View_Name_Edit = "Automation - Findings - Edited";
	public String  Findings_View_Name_from_AdvanceSearch = "Automation - Findings AS-Approved";
	
	@Parameters({ "className" })
	@BeforeTest
	public void checkTestSkip(String className) throws Exception {
		if (className.isEmpty()) {
			className = this.getClass().getSimpleName();
		}
		RunningClassName = className;
		Running_Class_BS_Description = "[BS-28] Verify Findings";
		IsBrowserPresentAlready =false;
		fnsCheckClassLevelTestSkip(className);
	}	

	
	@Test( priority = 0)
	public void Launch_Browser_and_Successfully_Login_into_the_Application() throws Throwable{
		try{ 
			fnsApps_Report_Logs("################################## "+ Running_Class_BS_Description);
			if (!IsBrowserPresentAlready) {
				IsBrowserPresentAlready = fnsBrowserLaunchAndLogin(Login_UserName, Login_Password);
				fnsSwitchAcoount_MultiAccess(RunningClassName, "SwitchAccount_DD");	
			}
		}catch(Throwable t){
			isTestCasePass = false;
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		fnsApps_Report_Logs("=========================================================================================================================================");
	}
	

	@Test( priority = 1, dependsOnMethods={"Launch_Browser_and_Successfully_Login_into_the_Application"}, description="")
	public void Create_Edit_Views_in_Findings_and_Verify_View_data_in_AdvanceSearch_And_Alerts_Screen() throws Throwable{
		fnsApps_Report_Logs("################### Test Case ::1 Create_Edit_Views_in_Findings_and_Verify_View_data_in_AdvanceSearch_And_Alerts_Screen");
	
		try{
			fncMenu_Ajax_Link_Click_By_PassingAjaxPath("Findings_Ajax");
			fnsLoading_Progressing_wait(1);
			
			fncNavigation_Verify_Application_Navigated_To_ViewScreen("'Finding(s)' Menu Ajax", "Finding(s)");
			
			String View_Exists = fncVerify_View_Display_Open_and_Delete_it(2, Findings_View_Name_Edit, "View_Delete_Link", "View_Remove_Link");				
			if( (View_Exists.contains("View Not Exists")) ){
				fncVerify_View_Display_Open_and_Delete_it(2, Findings_View_Name, "View_Delete_Link", "View_Remove_Link");
			}
			
			fnsGet_Element_Enabled("CreateNewView_Link");
			fnsWait_and_Click("CreateNewView_Link");
			fnsLoading_Progressing_wait(2);
			
			fnsGet_Element_Enabled("CreateView_Step1_ViewName");
			fnsLoading_Progressing_wait(1);
			fnsWait_and_Type("CreateView_Step1_ViewName", Findings_View_Name);
			
			
			fnsGet_Element_Enabled("CreateView_CreateView_Bttn");
			fnsWait_and_Click("CreateView_CreateView_Bttn");
			fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  60, "A new view has been added to your list", 25);
			
			fncNavigation_Verify_Application_Navigated_To_ViewScreen("'CreateView' button", "Finding(s)");
		
			fncClickonFirstFacilityfromViewResultTable_n_ReturnBackonViewScreen("Finding(s)", "Finding-NO");			
			
			Integer Visible_TableNo = TestSuiteBase_MonitorPlan.WithOut_OR_Return_DisplayElementNumber_if_More_than_One_Coming(OR_New_NSFOnline.getProperty("View_Result_Table")); //View_Result_TableHeader
			String Visible_Table_Header_Xpath = TestSuiteBase_MonitorPlan.WithOut_OR_Return_XPATH_if_More_than_One_Coming(OR_New_NSFOnline.getProperty("View_Result_TableHeader")); //View_Result_TableHeader
			String FindingView_FirstRow_AuditNumber_Column_Xpath = "("+OR_New_NSFOnline.getProperty("View_Result_Table")+")["+Visible_TableNo+"]"+"/tr[1]/td["+fnsReturn_ColumnNumber_from_TableHeaderXpath_WithoutOR(Visible_Table_Header_Xpath, "Audit No.")+"]";
			String FindingView_AuditNumber = fnsw_Click_on_Table_Link_by_Passing_Cell_Xpath__and__Return_CellValue(FindingView_FirstRow_AuditNumber_Column_Xpath);
			
			
			
			fnsVerifyScreenNavigation_afterClickingOnElement("'"+FindingView_AuditNumber+"' link", "Default 'Audit_Info' Tab", OR_New_NSFOnline.getProperty("Finding_AuditInfo_Tab_ByDefault_Opened"));
			
			fnsGet_Element_Display_byPassing_ElementName_and_Xpath_WithoutOR("Audit Info Tab", "'Download Audit Report' link" ,OR_New_NSFOnline.getProperty("Finding_AuditInfo_Tab_DownloadAuditReport_button"));
			fnsGet_Element_Display_byPassing_ElementName_and_Xpath_WithoutOR("Audit Info Tab", "'Download Corrective Action Report' link" ,OR_New_NSFOnline.getProperty("Finding_AuditInfo_Tab_DownloadCorrectiveActionReport_button"));
		
			fnsDownload_File_or_Verify_Validation_Message("Download Audit Report", OR_New_NSFOnline.getProperty("Finding_AuditInfo_Tab_DownloadAuditReport_button"));			
			fnsDownload_File_or_Verify_Validation_Message("Download Corrective Action Report", OR_New_NSFOnline.getProperty("Finding_AuditInfo_Tab_DownloadCorrectiveActionReport_button"));
			
			fnsGet_Element_Enabled("Finding_AuditNo_Finding_Tab");
			fnsWait_and_Click("Finding_AuditNo_Finding_Tab");
			fnsLoading_Progressing_wait(2);
			
			fnsWait_and_Click("NewNsfOnline_Back_Bttn");
			fnsLoading_Progressing_wait(3);
			
			try{
				fnsGet_Element_Enabled("View_Result_Table");
			}catch (Throwable t){
				fnsTake_Screen_Shot("ReturnBack_to_View_FAIL");
				throw new Exception ("FAILED == After clicking on 'BACK' button, Application is not getting navigate to 'Finding(s)' view screen, please refer screen shot >> ReturnBack_to_View_FAIL"+fnsScreenShot_Date_format());
			}
			
			fnsGet_Element_Enabled("Edit_View_Link");
			fnsWait_and_Click("Edit_View_Link");
			fnsLoading_Progressing_wait(1);				
			
			fnsGet_Element_Enabled("CreateView_Step1_ViewName");
			fnsLoading_Progressing_wait(1);
			fnsWait_and_Clear("CreateView_Step1_ViewName");
			fnsWait_and_Type("CreateView_Step1_ViewName", Findings_View_Name_Edit);				
			
			fnsGet_Element_Enabled("CreateView_Step1_Alert_CheckBox");
			fnsWait_and_Click("CreateView_Step1_Alert_CheckBox");
			fnsLoading_Progressing_wait(1);			
			
			fnsGet_Element_Enabled("CreateView_Step1_Alert_CheckBox_AssignColor_RED_Radio_bttn");
			fnsWait_and_Click("CreateView_Step1_Alert_CheckBox_AssignColor_RED_Radio_bttn");
			fnsLoading_Progressing_wait(1);
			
			fnsGet_Element_Enabled("CreateView_CreateView_Bttn");
			fnsWait_and_Click("CreateView_CreateView_Bttn");
			
			fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  60, "View has been updated successfully", 25);		

			fncNavigation_Verify_Application_Navigated_To_ViewScreen("'Update View' button", "Finding(s)");
			
			fns_AlertScreen_Click_on_AlertMenu_and_Verify_View_and_Label_Present(true, true, "Finding(s)", Findings_View_Name_Edit);
			fncVerify_View_Display_Open_and_Delete_it(2, Findings_View_Name_Edit, "View_Delete_Link", "View_Remove_Link");			
			
			fncVerify_View_Display_Open_and_Delete_it(2, Findings_View_Name_from_AdvanceSearch, "View_Delete_Link", "View_Remove_Link");	
			fnsLoading_Progressing_wait(1);
			fnsGet_Element_Enabled("View_AdvanceSearch_Bttn");
			fnsWait_and_Click("View_AdvanceSearch_Bttn");
			fnsLoading_Progressing_wait(2);			
			
			fnsDD_Value_select_by_DDLabelName_MultiselectDD("Status", "Approved");
			
			fnsGet_Element_Enabled("AuditCorrectiveActions_AdvanceSearch_Search_Bttn");
			fnsWait_and_Click("AuditCorrectiveActions_AdvanceSearch_Search_Bttn");
			fnsLoading_Progressing_wait(2);				
			
			Integer Finding_AdvanceSearch_Total_no_of_Records_Count = fnsReturn_View_Results_Reords_Total_Count("Advance Search " , "View_Result_Table", 10);
			
			fnsGet_Element_Enabled("AdvanceSearch_SaveAsView");
			fnsWait_and_Click("AdvanceSearch_SaveAsView");
			fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
			
			fnsGet_Element_Enabled("AdvanceSearch_SaveAsView_PopUpBox");
			fnsWait_and_Click("AdvanceSearch_SaveAsView_PopUpBox");
			fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
			
			fnsGet_Element_Enabled("CreateView_Step1_ViewName");
			fnsWait_and_Clear("CreateView_Step1_ViewName");
			fnsWait_and_Type("CreateView_Step1_ViewName", Findings_View_Name_from_AdvanceSearch);
			fnsLoading_Progressing_wait(2);
			
			fnsGet_Element_Enabled("CreateView_CreateView_Bttn");
			fnsWait_and_Click("CreateView_CreateView_Bttn");
		
			fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  60, "A new view has been added to your list", 25);
			
			Integer Finding_Gridscreen_Total_no_of_Records_Count = fnsReturn_View_Results_Reords_Total_Count("Standard Member Audits" , "View_Result_Table", 20);		
				
			try{
				assert Finding_Gridscreen_Total_no_of_Records_Count.equals(Finding_AdvanceSearch_Total_no_of_Records_Count):"FAILED == Advance Search results count <"+Finding_AdvanceSearch_Total_no_of_Records_Count+"> and VIEW Created-From-Advance-Search count <"+
						Finding_Gridscreen_Total_no_of_Records_Count+"> are not matched, please refer screen shot >> Count_Mismatch"+fnsScreenShot_Date_format();
				fnsApps_Report_Logs("PASSED == Advance Search results count <"+Finding_AdvanceSearch_Total_no_of_Records_Count+"> and VIEW Created-From-Advance-Search count <"+
						Finding_Gridscreen_Total_no_of_Records_Count+"> are matched.");
			}catch(Throwable t){
				fnsTake_Screen_Shot("Count_Mismatch");
				throw new Exception(Throwables.getStackTraceAsString(t));
			}
			
	
		}catch(Throwable t){
			isTestCasePass = false;
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}
	

//#######################################################  Configuration Method  #############################################################################
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