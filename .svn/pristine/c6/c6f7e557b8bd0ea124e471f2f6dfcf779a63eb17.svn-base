package nsf.ecap.New_NSFOnline_Suite;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;

public class BS_26_Standard_Members extends TestSuiteBase_New_NSFOnline{
	
	
	public String  StandardMembers_View_Name = "Automation - Standard Members";
	public String  StandardMembers_View_Name_EDIT = "Automation - Standard Members-Edited";
	public String  StandardMembers_Status_DD = "INACTIVE";
	
	

	@Parameters({ "className" })
	@BeforeTest
	public void checkTestSkip(String className) throws Exception {
		if (className.isEmpty()) {
			className = this.getClass().getSimpleName();
		}
		RunningClassName = className;
		Running_Class_BS_Description = "[BS-26] Verify Standard Members";
		IsBrowserPresentAlready =false;
		fnsCheckClassLevelTestSkip(className);
	}

	
	@Test( priority = 0)
	public void Launch_Browser_and_Successfully_Login_into_the_Application() throws Throwable{
		try{ 
			fnsApps_Report_Logs("################################## "+ Running_Class_BS_Description);
			if (!IsBrowserPresentAlready) {
				IsBrowserPresentAlready = fnsBrowserLaunchAndLogin(Login_UserName, Login_Password);
				//fnsSwitchAcoount_MultiAccess(RunningClassName, "SwitchAccount_DD");	
			}
		}catch(Throwable t){
			isTestCasePass = false;
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		fnsApps_Report_Logs("=========================================================================================================================================");
	}



	@Test( priority = 1, dependsOnMethods={"Launch_Browser_and_Successfully_Login_into_the_Application"}, description="")
	public void Create_Edit_Views_in_Standard_Members_and_Verify_View_data_in_AdvanceSearch_And_Alerts_Screen() throws Throwable{
	fnsApps_Report_Logs("################### Test Case ::1 Create_Edit_Views_in_Standard_Members_and_Verify_View_data_in_AdvanceSearch_And_Alerts_Screen");
		try{	
			Standard_Members_Create_and_Edit_View_Flow_Only("Standard Members", "StandardMembers_Ajax", StandardMembers_View_Name, StandardMembers_View_Name_EDIT);
						
			Integer StandardMembers_View_Total_no_of_Records_Count =  fnsFilter_ApplyFilter_and_Return_Total_Filtered_Record_Count("List Status : ", "LIST_STATUS_TYPE", StandardMembers_Status_DD) ;				
			
			
			fnsGet_Element_Enabled("View_AdvanceSearch_Bttn");
			fnsWait_and_Click("View_AdvanceSearch_Bttn");
			fnsLoading_Progressing_wait(2);				
			
			fnsDD_Value_select_by_DDLabelName_MultiselectDD("Status", StandardMembers_Status_DD);
			
			fnsGet_Element_Enabled("AuditCorrectiveActions_AdvanceSearch_Search_Bttn");
			fnsWait_and_Click("AuditCorrectiveActions_AdvanceSearch_Search_Bttn");
			fnsLoading_Progressing_wait(2);				
	
			Integer StandardMembers_AdvanceSearch_Total_no_of_Records_Count = fnsReturn_View_Results_Reords_Total_Count("Advance Search " , "View_Result_Table", 10);
					
			try{
				assert StandardMembers_View_Total_no_of_Records_Count.equals(StandardMembers_AdvanceSearch_Total_no_of_Records_Count):"FAILED == Advance Search display incorrect results because the total no of records from VIEW '"+StandardMembers_View_Total_no_of_Records_Count+
						"' are NOT matched with the total no of Records from ADVANCE SEARCH '"+StandardMembers_AdvanceSearch_Total_no_of_Records_Count+"', please refer screen shot >> Incorrect_Result_Display"+fnsScreenShot_Date_format();
				fnsApps_Report_Logs("PASSED == Advance Search display correct results as the total no of records from VIEW '"+StandardMembers_View_Total_no_of_Records_Count+
						"' are matched with total no of Records from ADVANCE SEARCH '"+StandardMembers_AdvanceSearch_Total_no_of_Records_Count+"'.");
			}catch(Throwable t){
				fnsTake_Screen_Shot("Incorrect_Result_Display");
				throw new Exception(Throwables.getStackTraceAsString(t));
			}

			
			fnsWait_and_Click("StandardMembers_AdvanceSearch_Back_Bttn");
			fnsLoading_Progressing_wait(1);
			fncNavigation_Verify_Application_Navigated_To_ViewScreen("'Back' button", "Standard Members");
			
			fns_AlertScreen_Click_on_AlertMenu_and_Verify_View_and_Label_Present(true, true, "Standard Members", StandardMembers_View_Name_EDIT);
			
			fncVerify_View_Display_Open_and_Delete_it(2, StandardMembers_View_Name_EDIT, "View_Delete_Link", "View_Remove_Link");
			
		}catch(Throwable t){
			isTestCasePass = false;
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}
		

	
	
//#######################################################  Class Method  #############################################################################		
	public void Standard_Members_Create_and_Edit_View_Flow_Only(String FlowName, String Ajax_Name_from_OR, String ViewName, String Edit_ViewName) throws Throwable{
		try{		
			fncMenu_Ajax_Link_Click_By_PassingAjaxPath(Ajax_Name_from_OR);
			fnsLoading_Progressing_wait(1);
			
			fncNavigation_Verify_Application_Navigated_To_ViewScreen("'"+FlowName+"' Menu Ajax", FlowName);
			
			String View_Exists = fncVerify_View_Display_Open_and_Delete_it(2, Edit_ViewName, "View_Delete_Link", "View_Remove_Link");				
			if( (View_Exists.contains("View Not Exists")) ){
				fncVerify_View_Display_Open_and_Delete_it(2, ViewName, "View_Delete_Link", "View_Remove_Link");
			}
			
			fnsGet_Element_Enabled("CreateNewView_Link");
			fnsWait_and_Click("CreateNewView_Link");
			fnsLoading_Progressing_wait(2);
			
			fnsGet_Element_Enabled("CreateView_Step1_ViewName");
			fnsLoading_Progressing_wait(1);
			fnsWait_and_Type("CreateView_Step1_ViewName", ViewName);
			
			if(FlowName.equalsIgnoreCase("Product Review")){
				fnsDD_Value_select_by_DDLabelName_MultiselectDD("Job Type", FlowName);
				fnsLoading_Progressing_wait(2);
			}
			
			fnsGet_Element_Enabled("CreateView_CreateView_Bttn");
			fnsWait_and_Click("CreateView_CreateView_Bttn");
			fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  60, "A new view has been added to your list", 25);
			
			fncNavigation_Verify_Application_Navigated_To_ViewScreen("'CreateView' button", FlowName);
			if(FlowName.equalsIgnoreCase("Standard Members")){
				fncClickonFirstFacilityfromViewResultTable_n_ReturnBackonViewScreen(ViewName, "Code");
			}else if(FlowName.equalsIgnoreCase("Product Review")){
				fncClickonFirstFacilityfromViewResultTable_n_ReturnBackonViewScreen("Product Review", "Facility Code");
			}
			
			fnsGet_Element_Enabled("Edit_View_Link_2");
			fnsWait_and_Click("Edit_View_Link_2");
			fnsLoading_Progressing_wait(1);				
			
			fnsGet_Element_Enabled("CreateView_Step1_ViewName");
			fnsLoading_Progressing_wait(1);
			fnsWait_and_Clear("CreateView_Step1_ViewName");
			fnsWait_and_Type("CreateView_Step1_ViewName", Edit_ViewName);				
			
			fnsGet_Element_Enabled("CreateView_Step1_Alert_CheckBox");
			fnsWait_and_Click("CreateView_Step1_Alert_CheckBox");
			fnsLoading_Progressing_wait(1);			
			
			fnsGet_Element_Enabled("CreateView_Step1_Alert_CheckBox_AssignColor_RED_Radio_bttn");
			fnsWait_and_Click("CreateView_Step1_Alert_CheckBox_AssignColor_RED_Radio_bttn");
			fnsLoading_Progressing_wait(1);
			
			fnsGet_Element_Enabled("CreateView_CreateView_Bttn");
			fnsWait_and_Click("CreateView_CreateView_Bttn");
			
			fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  60, "View has been updated successfully", 25);		

			fncNavigation_Verify_Application_Navigated_To_ViewScreen("'Update View' button", FlowName);
			
			
		}catch(Throwable t){
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
		try{
			driver.quit();
			Thread.sleep(3000);
		}catch(Throwable t){
			//nothing to do
		}
	}
}
