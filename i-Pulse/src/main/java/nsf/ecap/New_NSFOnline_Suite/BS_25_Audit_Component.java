package nsf.ecap.New_NSFOnline_Suite;

import java.util.ArrayList;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;

public class BS_25_Audit_Component extends TestSuiteBase_New_NSFOnline{
	
	
	
	public String AuditComponent_View_Name_EDIT = "Automation - Audit Component - Edited";
	public Integer AuditComponent_View_Total_no_of_Records_Count = 25928;
	ArrayList<Integer> AuditComponent_View_Records_Count_List = new ArrayList<Integer>();
	ArrayList<String> AuditComponent_View_StatusFilterColumn_Text_List = new ArrayList<String>();	
	
	
	@Parameters({ "className" })
	@BeforeTest
	public void checkTestSkip(String className) throws Exception {
		if (className.isEmpty()) {
			className = this.getClass().getSimpleName();
		}
		RunningClassName = className;
		Running_Class_BS_Description = "[BS-25]  Create View and Piechart for Audit Component then cross records count verification";
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
	public void CreateNewView_MakeAlert_by_EditView_then_Verify_ViewRecordsCount_MatchWith_AdvanceSearchResultCount() throws Throwable{
	fnsApps_Report_Logs("################### Test Case ::1 CreateNewView_MakeAlert_by_EditView_then_Verify_ViewRecordsCount_MatchWith_AdvanceSearchResultCount");	
		try{		
			String AuditComponent_Status_DD = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "AuditComponent_Status_DD");
			String AuditComponent_View_Name = "Automation - Audit Component";
			
			if( fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "AuditComponent_Status_DD").contains(",") ){
				for(int i=0; i<fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "AuditComponent_Status_DD").split(",").length; i++){
					AuditComponent_View_StatusFilterColumn_Text_List.add(fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "AuditComponent_Status_DD").split(",")[i].trim());
				}
			}else{
				AuditComponent_View_StatusFilterColumn_Text_List.add(fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "AuditComponent_Status_DD").trim() );
			}
			
			fncMenu_Ajax_Link_Click_By_PassingAjaxPath("AuditComponent_Ajax");
			fnsLoading_Progressing_wait(1);
			
			fncNavigation_Verify_Application_Navigated_To_ViewScreen("'Audit Component' Menu Ajax", "Audit Component");
			
			String View_Exists = fncVerify_View_Display_Open_and_Delete_it(2, AuditComponent_View_Name_EDIT, "View_Delete_Link", "View_Remove_Link");
			
			if( (View_Exists.contains("View Not Exists")) ){
				fncVerify_View_Display_Open_and_Delete_it(2, AuditComponent_View_Name, "View_Delete_Link", "View_Remove_Link");			
			}			
						
			fnsGet_Element_Enabled("CreateNewView_Link");
			fnsWait_and_Click("CreateNewView_Link");
			fnsLoading_Progressing_wait(2);
			
			fnsGet_Element_Enabled("CreateView_Step1_ViewName");
			fnsLoading_Progressing_wait(1);
			fnsWait_and_Type("CreateView_Step1_ViewName", AuditComponent_View_Name);
			
			fnsGet_Element_Enabled("CreateView_CreateView_Bttn");
			fnsWait_and_Click("CreateView_CreateView_Bttn");
			fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  60, "A new view has been added to your list", 25);
							
			fncNavigation_Verify_Application_Navigated_To_ViewScreen("'CreateView' button", "Audit Component");
			
			fncClickonFirstFacilityfromViewResultTable_n_ReturnBackonViewScreen("Audit Component", "Audit No");			
			
			fnsGet_Element_Enabled("Edit_View_Link_2");
			fnsWait_and_Click("Edit_View_Link_2");
			fnsLoading_Progressing_wait(1);
			
			fnsGet_Element_Enabled("CreateView_Step1_ViewName");
			fnsLoading_Progressing_wait(1);
			fnsWait_and_Clear("CreateView_Step1_ViewName");
			fnsWait_and_Type("CreateView_Step1_ViewName", AuditComponent_View_Name_EDIT);
			
			
			fnsGet_Element_Enabled("CreateView_Step1_Alert_CheckBox");
			fnsWait_and_Click("CreateView_Step1_Alert_CheckBox");
			fnsLoading_Progressing_wait(1);
		
			
			fnsGet_Element_Enabled("CreateView_Step1_Alert_CheckBox_AssignColor_RED_Radio_bttn");
			fnsWait_and_Click("CreateView_Step1_Alert_CheckBox_AssignColor_RED_Radio_bttn");
			fnsLoading_Progressing_wait(1);
			
			fnsGet_Element_Enabled("CreateView_CreateView_Bttn");
			fnsWait_and_Click("CreateView_CreateView_Bttn");
			fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  60, "View has been updated successfully", 25);
			
			fncNavigation_Verify_Application_Navigated_To_ViewScreen("'CreateView' button", "Audit Component");
			
			
			for(int i=0; i<AuditComponent_View_StatusFilterColumn_Text_List.size(); i++){
				AuditComponent_View_Records_Count_List.add( fnsFilter_ApplyFilter_and_Return_Total_Filtered_Record_Count("Audit Status : "+AuditComponent_View_StatusFilterColumn_Text_List.get(i), "AUDIT_STATUS", AuditComponent_View_StatusFilterColumn_Text_List.get(i)) );
				AuditComponent_View_Total_no_of_Records_Count = AuditComponent_View_Records_Count_List.get(i);
			}
			
					
			fnsGet_Element_Enabled("View_AdvanceSearch_Bttn");
			fnsWait_and_Click("View_AdvanceSearch_Bttn");
			fnsLoading_Progressing_wait(2);			
			
			fnsDD_Value_select_by_DDLabelName_MultiselectDD("Status", AuditComponent_Status_DD);
								
			fnsGet_Element_Enabled("AuditCorrectiveActions_AdvanceSearch_Search_Bttn");
			fnsWait_and_Click("AuditCorrectiveActions_AdvanceSearch_Search_Bttn");
			fnsLoading_Progressing_wait(2);
				
			Integer AuditComponent_AdvanceSearch_Total_no_of_Records_Count = fnsReturn_View_Results_Reords_Total_Count("Advance Search" , "View_Result_Table", 10);
			
			try{
				assert AuditComponent_View_Total_no_of_Records_Count.equals(AuditComponent_AdvanceSearch_Total_no_of_Records_Count) : "FAILED == Advance Search display incorrect results because the total no of records from VIEW '"+AuditComponent_View_Total_no_of_Records_Count+
						"' are NOT matched with the total no of Records from ADVANCE SEARCH '"+AuditComponent_AdvanceSearch_Total_no_of_Records_Count+"', please refer screen shot >> Records_Count_Match_Fail"+fnsScreenShot_Date_format();
				fnsApps_Report_Logs("PASSED == Advance Search display correct results as the total no of records from VIEW '"+AuditComponent_View_Total_no_of_Records_Count+
						"' are matched with total no of Records from ADVANCE SEARCH '"+AuditComponent_AdvanceSearch_Total_no_of_Records_Count+"'.");
			}catch(Throwable t){
				fnsTake_Screen_Shot("Records_Count_Match_Fail");
				throw new Exception(Throwables.getStackTraceAsString(t));
			}
	
	
			fnsWait_and_Click("AuditComponent_AdvanceSearch_Back_Bttn");
			fnsLoading_Progressing_wait(1);
			fncNavigation_Verify_Application_Navigated_To_ViewScreen("'CreateView' button", "Audit Component");
			
		}catch(Throwable t){
			isTestCasePass = false;
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	
	}
		
	@Test( priority = 2, dependsOnMethods={"CreateNewView_MakeAlert_by_EditView_then_Verify_ViewRecordsCount_MatchWith_AdvanceSearchResultCount"}, description="")
	public void CreateNewSnapshot_and_PieChart_for_AuditCompenent_then_Verify_Records_Counts_in_PieChart() throws Throwable{
		fnsApps_Report_Logs("################### Test Case ::2 CreateNewSnapshot_and_PieChart_for_AuditCompenent_then_Verify_Records_Counts_in_PieChart");
						
		try{
			String  Snapshot_View_Name = "Automation - Snapshot Audit Component";
			String  Snapshot_PieChart_Name = "Automation - Pie Chart";
			String  Snapshot_View_PieChartConfiguration_For_DD = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Snapshot_View_PieChartConfiguration_For_DD");
			String  Snapshot_View_PieChartConfiguration_GroupBy_DD = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Snapshot_View_PieChartConfiguration_GroupBy_DD");
			String  Snapshot_View_PieChartConfiguration_DateAndValue_DD = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Snapshot_View_PieChartConfiguration_DateAndValue_DD");
			String  Snapshot_View_PieChartConfiguration_Format_DD = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Snapshot_View_PieChartConfiguration_Format_DD");
			String  Snapshot_View_PieChartConfiguration_AggregateFunction_DD = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Snapshot_View_PieChartConfiguration_AggregateFunction_DD");
			
			
			fncMenu_Ajax_Link_Click_By_PassingAjaxPath("Snapshot_Ajax");			
			fnsLoading_Progressing_wait(2);
			
			fncVerify_View_Display_Open_and_Delete_it(2, Snapshot_View_Name, "Snapshot_Delete_Link", "Snapshot_Remove_Link");
			
			fnsGet_Element_Enabled("Snapshot_CreateNewSnapshot_bttn");
			fnsWait_and_Click("Snapshot_CreateNewSnapshot_bttn");
			
			fnsGet_Element_Enabled("Snapshot_CreateNewSnapshot_SnapshotName_input");
			fnsWait_and_Type("Snapshot_CreateNewSnapshot_SnapshotName_input", Snapshot_View_Name);
			Thread.sleep(1000); 
			fnsGet_Element_Enabled("Snapshot_CreateNewSnapshot_CreateSnapshot_bttn");
			fnsWait_and_Click("Snapshot_CreateNewSnapshot_CreateSnapshot_bttn");
			
			fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  60, "Snapshot created successfully", 20);
			
			fnsGet_Element_Enabled("View_Title");
			fncMyView_SharedView_NewlyCreatedView_Display_and_Conditional_Click(2, true, Snapshot_View_Name, "Yes");
			
			for(int i=0; i<=20; i++){
				try{
					String View_Title = fnsGet_OR_New_NSFOnline_ObjectX("View_Title").getText().trim();
					if(View_Title.contains(Snapshot_View_Name)){
						break;
					}else{
						fnsLoading_Progressing_wait(1);
					}
				
				}catch(Throwable t){
					if(i==20){
						throw new Exception(Throwables.getStackTraceAsString(t));
					}else{
						Thread.sleep(1000);
						}
					}
			}
		

			fnsGet_Element_Enabled("Snapshot_View_AddChart_Bttn");
			fnsWait_and_Click("Snapshot_View_AddChart_Bttn");
			
			fnsGet_Element_Enabled("Snapshot_View_AddChart_SelectChartType_Pie");
			fnsWait_and_Click("Snapshot_View_AddChart_SelectChartType_Pie");
			
			fnsGet_Element_Enabled("Snapshot_View_AddChart_SelectChartType_AddChart_Bttn");
			fnsWait_and_Click("Snapshot_View_AddChart_SelectChartType_AddChart_Bttn");
			
			fnsGet_Element_Enabled("Snapshot_View_PieChartConfiguration_Title_input");
			fnsWait_and_Type("Snapshot_View_PieChartConfiguration_Title_input", Snapshot_PieChart_Name);				
			
			fnsDD_value_Select_TagOPTION_DDTypeSelect("Snapshot_View_PieChartConfiguration_For_DD", Snapshot_View_PieChartConfiguration_For_DD, (NewNsfOnline_Element_Max_Wait_Time/2));
			fnsLoading_Progressing_wait(2);				
			
			fncMyView_SharedView_NewlyCreatedView_Click_from_PopUp(AuditComponent_View_Name_EDIT);
			
			Thread.sleep(1000);
			fnsDD_value_Select_TagOPTION_DDTypeSelect("Snapshot_View_PieChartConfiguration_GroupBy_DD", Snapshot_View_PieChartConfiguration_GroupBy_DD, (NewNsfOnline_Element_Max_Wait_Time/2));		
			fnsLoading_Progressing_wait(1);
			
			fnsDD_value_Select_TagOPTION_DDTypeSelect("Snapshot_View_PieChartConfiguration_DateAndValue_DD", Snapshot_View_PieChartConfiguration_DateAndValue_DD, (NewNsfOnline_Element_Max_Wait_Time/2));
			fnsLoading_Progressing_wait(1);				
			
			fnsDD_value_Select_TagOPTION_DDTypeSelect("Snapshot_View_PieChartConfiguration_Format_DD", Snapshot_View_PieChartConfiguration_Format_DD, (NewNsfOnline_Element_Max_Wait_Time/2));
			fnsLoading_Progressing_wait(1);				
			
			fnsDD_value_Select_TagOPTION_DDTypeSelect("Snapshot_View_PieChartConfiguration_AggregateFunction_DD", Snapshot_View_PieChartConfiguration_AggregateFunction_DD, (NewNsfOnline_Element_Max_Wait_Time/2));
			fnsLoading_Progressing_wait(1);
			
			fnsGet_Element_Enabled("Snapshot_View_PieChartConfiguration_DisplayLabel_CheckBox");
			fnsWait_and_Click("Snapshot_View_PieChartConfiguration_DisplayLabel_CheckBox");
			
			fnsGet_Element_Enabled("Snapshot_View_PieChartConfiguration_DisplayExportOption_CheckBox");
			fnsWait_and_Click("Snapshot_View_PieChartConfiguration_DisplayExportOption_CheckBox");
			
			fnsGet_Element_Enabled("Snapshot_View_PieChartConfiguration_Update_Bttn");
			fnsWait_and_Click("Snapshot_View_PieChartConfiguration_Update_Bttn");
							
			fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  NewNsfOnline_Element_Max_Wait_Time, "Piechart created successfully", 20);


			
			try{
				fncVerify_GraphDisplay_And_RecordsCount_Validate(AuditComponent_View_Total_no_of_Records_Count, AuditComponent_View_StatusFilterColumn_Text_List,  AuditComponent_View_Records_Count_List, false);
			}catch(Throwable t){
				fncVerify_GraphDisplay_And_RecordsCount_Validate(AuditComponent_View_Total_no_of_Records_Count, AuditComponent_View_StatusFilterColumn_Text_List,  AuditComponent_View_Records_Count_List, true);
		
			}
			
			
			fns_AlertScreen_Click_on_AlertMenu_and_Verify_View_and_Label_Present(true, true, Snapshot_View_PieChartConfiguration_For_DD, AuditComponent_View_Name_EDIT);				
			fncNavigation_Verify_Application_Navigated_To_ViewScreen("'Audit Component' Menu Ajax", "Audit Component");
			fncVerify_View_Display_Open_and_Delete_it(2, AuditComponent_View_Name_EDIT, "View_Delete_Link", "View_Remove_Link");
							
		}catch(Throwable t){
			isTestCasePass = false;
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));
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