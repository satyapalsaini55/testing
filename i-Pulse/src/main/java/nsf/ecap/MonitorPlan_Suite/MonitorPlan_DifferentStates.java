package nsf.ecap.MonitorPlan_Suite;

import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
//

public class MonitorPlan_DifferentStates extends TestSuiteBase_MonitorPlan{
	
	public boolean IsBrowserPresentAlready = false;
	public String ANNLIST_Task_BillCode_Data = null;
	public String Status_DDValue = null;
	public String TaskSummary_ChooseYear_DD = null;

	public CreateActiveMonitorPlan MP_BS_01_Object = new CreateActiveMonitorPlan();
		
		

	
	@Parameters({ "className" })
	@BeforeTest
	public void checkTestSkip(String className) throws Throwable {	
		
		if (className.isEmpty()) {
			className = this.getClass().getSimpleName();		}
		IsBrowserPresentAlready =false;
		fnsCheckClassLevelTestSkip(className);
		
		Facility_ID = fnsReturn_ExcelCellValue_ByMatchingColumnName("CreateActiveMonitorPlan", "Facility_LookUp").trim();
		
	}
	
	
	
	
	@Test( priority = 0, description="To Close Draft Status monitor Plan for specific client=36440")
	public void DB_ProcedureLaunch_ToClose_DraftMonitorPlan() throws Throwable {
		fnsApps_Report_Logs("################################## [BS - 02] Monitor Plan Different States");
		fnUpdateDB_By_Executing_Procedure_ToCloseMonitorPlan(Facility_ID);
}
	
	
	

@Test( priority = 1, dependsOnMethods={"DB_ProcedureLaunch_ToClose_DraftMonitorPlan"})
public void Launch_Browser_and_Successfully_Login_into_the_Application() throws Throwable{
	
	if (!IsBrowserPresentAlready) {		
		IsBrowserPresentAlready = fnsLaunchBrowserAndLogin();
		fnsApps_Report_Logs("Browser is launched and Successfully login into Application");	
	}
	fnsApps_Report_Logs("=========================================================================================================================================");
}	
	

	
	
	
	
	
	@Test(priority = 2, description="  [SearchWorkOrder_for_Facility_36440_in_DraftStatus_IfAnyThen_DropIt]",   dependsOnMethods={"Launch_Browser_and_Successfully_Login_into_the_Application"})
	public void SearchWorkOrder_for_Facility_36440_InDraftStatus_IfAnyThen_DropIt() throws Throwable{	
		TC_Step=1;
		
		ANNLIST_Task_Details = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "ANNLIST_Task_Details");
		ANNLIST_Task_BillCode_Data = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "ANNLIST_Task_BillCode_Data");
		Status_DDValue = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Status_DDValue");
		TaskSummary_ChooseYear_DD = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "TaskSummary_ChooseYear_DD");
	
		
		if(WO_Drop_Bs_02_Flag){
			
			MP_BS_01_Object.SearchWorkOrder_for_Facility_36440_InDraftStatus_IfAnyThen_DropIt();
		}
}	
	
	
	
	@Test(dependsOnMethods={"SearchWorkOrder_for_Facility_36440_InDraftStatus_IfAnyThen_DropIt"}, priority = 3, description="  [Create Monitor Plan -- Verify data updated successfully and New Monitor Plan Created.]")
	public void CreateMonitorPlan_Verify_NewMonitorPlanCreated_and_inDraftStatus() throws Throwable{
				
		MP_BS_01_Object.CreateMonitorPlan_Verify_NewMonitorPlanCreated_and_inDraftStatus();
}
	
	

	
	@Test(dependsOnMethods={"CreateMonitorPlan_Verify_NewMonitorPlanCreated_and_inDraftStatus"}, priority = 4, description="  [Info tab>Change the status of Monitor Plan from Draft to Active.]")
	public void EditMonitorPlan_InfoTab_Change_MonitorPlanStatus_as_Active() throws Throwable{
		
		fnsApps_Report_Logs("=========================================================================================================================================");
		fnsApps_Report_Logs("################### Test Case ::::::3 EditMonitorPlan_InfoTab_Change_MonitorPlanStatus_as_Active ");
		
		fnc_MP_InfoTab_Change_MonitorPlanStatus(Status_DDValue.split(",")[1]);
}
	
	
	
	
	@Test(dependsOnMethods={"EditMonitorPlan_InfoTab_Change_MonitorPlanStatus_as_Active"}, priority = 5, description="  [Edit Monitor Plan >> Verify TaskTab Data are Updated Sccessfully.]")
	public void EditMonitorPlan_TasksTab_Verify_TaskAndBillCode_Sccessfully_AddAndDelete_WhenMPStatusActive() throws Throwable{
		
		fnsApps_Report_Logs("=========================================================================================================================================");
		fnsApps_Report_Logs("################### Test Case ::::::4 EditMonitorPlan_TasksTab_Verify_TaskAndBillCode_Sccessfully_AddAndDelete_WhenMPStatusActive ");
		
		//Clicking Tasks TAB
		for(int clicktry=0; clicktry<6; clicktry++){
			fnsGet_Element_Enabled("EditAnnualMonitorPlan_Tasks_TAB");
			fnsWait_and_Click("EditAnnualMonitorPlan_Tasks_TAB");
			fnsLoading_Progressingwait(2);
							
			if(driver.findElements(By.xpath(OR_MtrPlan.getProperty("TasksTab_ApplyTaskTemplate_Bttn"))).size()>0){
				break;
			}
			if(clicktry==5){
				isTestCasePass = false;
				fnsTake_Screen_Shot("TabOpenFail");
				throw new Exception("FAILED == <Tasks> TAB is not opened, Please refer screen shot >> TabOpenFail"+fnsScreenShot_Date_format());
			}
		}
		
		fnsGet_Element_Enabled("Footer");
		fnsGet_Element_Enabled("TasksTab_ApplyTaskTemplate_Bttn");
		fnsWait_and_Click("TasksTab_ApplyTaskTemplate_Bttn");
		fnsLoading_Progressingwait(3);
		

		TestSuiteBase_Grip.fncAssert_Contains(OR_MtrPlan.getProperty("TasksTab_ApplyTaskTemplate_Popup_Title"), "Select Tasks", "'Select Tasks' Popup is not getting opened");
		
		fnsWait_and_Click("TasksTab_ApplyTaskTemplate_SelectTasks_Continue_Bttn");
		fnsLoading_Progressingwait(2);
		
		//Verify Records are updated into Task list table
		fnsGet_Element_Enabled("TasksTab_TaskList_TableHeader_Xpath");
		Integer TaskListTableRowCount = fnsRetrun_TotalRowCount_OfTable("TasksTab_TaskList_TableHeader_Xpath");
		try{
			assert !(TaskListTableRowCount.equals(0)):"FAILED == Tasks are not getting added into TaskList Table, plz see screenshot [TaskListTableUpdateFail" + fnsScreenShot_Date_format() + "]";
		}catch(Throwable t){
			isTestCasePass = false;
			fnsTake_Screen_Shot("TaskListTableUpdateFail");
			fnsApps_Report_Logs(t.getMessage());
			throw new Exception(t.getMessage());}
		
		//Edit Annual Listing Task, Perform add/delete bill code .
		MP_BS_01_Object.fnc_TaskTAB_EditTask_and_Enter_Data_in_StartYear_Frenquency_EndYear("TasksTab_TaskList_ANNLIST_Edit_Bttn", "Edit Monitor Plan Task -- Annual Listing", (ANNLIST_Task_Details.split(",")[0]), (ANNLIST_Task_Details.split(",")[1]), (ANNLIST_Task_Details.split(",")[2]));
		
		fnVerify_BillingCode_Updated_through_AddSingle_Bttn
		 (0, (ANNLIST_Task_BillCode_Data.split(",")[0]), 
		 (ANNLIST_Task_BillCode_Data.split(",")[1]), 
		 (ANNLIST_Task_BillCode_Data.split(",")[2]),
		 (ANNLIST_Task_BillCode_Data.split(",")[3]),
		 (ANNLIST_Task_BillCode_Data.split(",")[4]));
		
		fnsGet_Element_Enabled("TasksTab_EditMonitorPlanTask_Popup_SaveAndClose_Bttn");
		fnsWait_and_Click("TasksTab_EditMonitorPlanTask_Popup_SaveAndClose_Bttn");
		fnsLoading_Progressingwait(3);
		Thread.sleep(1000);
		fnsLoading_Progressingwait(1);
		
		fnsGet_Element_Enabled("TasksTab_TaskList_ANNLIST_Edit_Bttn");
		fnsWait_and_Click("TasksTab_TaskList_ANNLIST_Edit_Bttn");
		
		TestSuiteBase_Grip.fncAssert_Contains(OR_MtrPlan.getProperty("TasksTab_EditMonitorPlanTask_Popup_Tilte"), "Edit Monitor Plan Task", "'Edit Monitor Plan Task -- Annual Listing' Popup is not getting opened");
		
		
		//Delete BillCode
		fnsGet_Element_Enabled("TaskTab_BillCode_Delete_Bttn");
		fnsWait_and_Click("TaskTab_BillCode_Delete_Bttn");
		fnsLoading_Progressingwait(2);
		fnsGet_Element_Enabled("TaskTab_BillCodeDelete_Confirmation_YES_Bttn");
		fnsWait_and_Click("TaskTab_BillCodeDelete_Confirmation_YES_Bttn");
		fnsLoading_Progressingwait(3);
		fnsGet_Element_Enabled("TasksTab_EditMonitorPlanTask_Popup_Success_Message_Div");
		fnsText_Fetch_and_Assert("TasksTab_EditMonitorPlanTask_Popup_Success_Message_Div", "success");
		
		
		fnsGet_Element_Enabled("TasksTab_EditMonitorPlanTask_Popup_SaveAndClose_Bttn");
		fnsWait_and_Click("TasksTab_EditMonitorPlanTask_Popup_SaveAndClose_Bttn");
		fnsLoading_Progressingwait(3);
		Thread.sleep(1000);
		fnsLoading_Progressingwait(1);
		
			
		//Delete Task
		fnsGet_Element_Enabled("TaskTab_TaskDelete_Bttn");
		fnsWait_and_Click("TaskTab_TaskDelete_Bttn");
		fnsLoading_Progressingwait(2);
		fnsLoading_Progressingwait(1);
		fnsGet_Element_Enabled("TaskTab_TaskDelete_Confirmation_YES_Bttn");
		fnsWait_and_Click("TaskTab_TaskDelete_Confirmation_YES_Bttn");
		fnsLoading_Progressingwait(5);
		fnsGet_Element_Enabled("Footer");
		Thread.sleep(5000);
		fnsGet_Element_Enabled("Success_Message_Div");
		fnsText_Fetch_and_Assert("Success_Message_Div", "deleted");	
		
}
	

	

	@Test(dependsOnMethods={"EditMonitorPlan_TasksTab_Verify_TaskAndBillCode_Sccessfully_AddAndDelete_WhenMPStatusActive"}, priority = 6, description="  [Edit Monitor Plan >> Verify Testing Tasks Tab Data are Updated Sccessfully.]")
	public void EditMonitorPlan_TestingTasksTab_Verify_TaskAndBillCode_Sccessfully_AddAndDelete_WhenMPStatusActive() throws Throwable{
		
		fnsApps_Report_Logs("=========================================================================================================================================");
		fnsApps_Report_Logs("################### Test Case ::::::5 EditMonitorPlan_TestingTasksTab_Verify_TaskAndBillCode_Sccessfully_AddAndDelete_WhenMPStatusActive ");

				
		//Testing TAB --- Add and Edit Task and verify dd default values and a new bill code.	
		MP_BS_01_Object.fnc_TestingTAB_AddEditTask_Verify_DDDefaultValue_and_Bill_Code();
		
		
		fnsGet_Element_Enabled("TestingTaskTab_EditMonitorPlanTask_Popup_SaveAndClose_Bttn");
		fnsWait_and_Click("TestingTaskTab_EditMonitorPlanTask_Popup_SaveAndClose_Bttn");
		fnsLoading_Progressingwait(3);
		Thread.sleep(1000);
		fnsLoading_Progressingwait(1);	
		
		fnsGet_Element_Enabled("TestingTaskTab_TaskList_TestingTask_Edit_Bttn");
		fnsWait_and_Click("TestingTaskTab_TaskList_TestingTask_Edit_Bttn");
		
		TestSuiteBase_Grip.fncAssert_Contains(OR_MtrPlan.getProperty("TestingTaskTab_EditMonitorPlanTask_Popup_Tilte"), "Edit Monitor Plan Task", "'Edit Monitor Plan Task -- Testing' Popup is not getting opened");
		
		//Delete BillCode
		fnsGet_Element_Enabled("TestingTaskTab_BillCode_Delete_Bttn");
		fnsWait_and_Click("TestingTaskTab_BillCode_Delete_Bttn");
		fnsLoading_Progressingwait(2);
		fnsGet_Element_Enabled("TestingTaskTab_BillCodeDelete_Confirmation_YES_Bttn");
		fnsWait_and_Click("TestingTaskTab_BillCodeDelete_Confirmation_YES_Bttn");
		fnsLoading_Progressingwait(3);
		fnsGet_Element_Enabled("TestingTaskTab_EditMonitorPlanTask_Popup_Success_Message_Div");
		fnsText_Fetch_and_Assert("TestingTaskTab_EditMonitorPlanTask_Popup_Success_Message_Div", "success");
		
		
		fnsGet_Element_Enabled("TestingTaskTab_EditMonitorPlanTask_Popup_SaveAndClose_Bttn");
		fnsWait_and_Click("TestingTaskTab_EditMonitorPlanTask_Popup_SaveAndClose_Bttn");
		fnsLoading_Progressingwait(3);
		Thread.sleep(1000);
		fnsLoading_Progressingwait(1);	
		
			
		//Delete Task
		fnsGet_Element_Enabled("TestingTaskTab_TaskDelete_Bttn");
		fnsWait_and_Click("TestingTaskTab_TaskDelete_Bttn");
		fnsLoading_Progressingwait(2);
		fnsGet_Element_Enabled("TestingTaskTab_TaskDelete_Confirmation_YES_Bttn");
		fnsWait_and_Click("TestingTaskTab_TaskDelete_Confirmation_YES_Bttn");
		fnsLoading_Progressingwait(5);
		fnsGet_Element_Enabled("Footer");
		Thread.sleep(10000);
		fnsGet_Element_Enabled("Success_Message_Div");
		fnsText_Fetch_and_Assert("Success_Message_Div", "deleted");	
				
}
	
	
	
	

	@Test(dependsOnMethods={"EditMonitorPlan_TestingTasksTab_Verify_TaskAndBillCode_Sccessfully_AddAndDelete_WhenMPStatusActive"}, priority = 7, description="  [Go to the Info tab and change the status of the monitor plan as Inactive.Click on Save]")
	public void EditMonitorPlan_InfoTab_Change_MonitorPlanStatus_as_InActive() throws Throwable{
		
		fnsApps_Report_Logs("=========================================================================================================================================");
		fnsApps_Report_Logs("################### Test Case ::::::6 EditMonitorPlan_InfoTab_Change_MonitorPlanStatus_as_InActive ");

		fnc_MP_InfoTab_Change_MonitorPlanStatus(Status_DDValue.split(",")[2]);
}
	
	

	
	
	
	@Test(dependsOnMethods={"EditMonitorPlan_InfoTab_Change_MonitorPlanStatus_as_InActive"}, priority = 8, description="  [Go to the Snapshot tab >Verify that 'Create Work Order' button should be disabled.]")
	public void EditMonitorPlan_SnapshotTab_Verify_CreateWorkOrderButton_Disabled_WhenMPStatusInactive() throws Throwable{
		
		fnsApps_Report_Logs("=========================================================================================================================================");
		fnsApps_Report_Logs("################### Test Case ::::::7 EditMonitorPlan_SnapshotTab_Verify_CreateWorkOrderButton_Disabled_WhenMPStatusInactive ");
				
		fnc_MP_SnapshotTab_Verify_CreateWorkOrderButton_Disabled();
}
	
	
	
	

	@Test(dependsOnMethods={"EditMonitorPlan_SnapshotTab_Verify_CreateWorkOrderButton_Disabled_WhenMPStatusInactive"}, priority = 9, description="  [Go to the Info tab and change the status of the monitor plan as Pending. Click on Save.]")
	public void EditMonitorPlan_InfoTab_Change_MonitorPlanStatus_as_InPending() throws Throwable{
		
		fnsApps_Report_Logs("=========================================================================================================================================");
		fnsApps_Report_Logs("################### Test Case ::::::8 EditMonitorPlan_InfoTab_Change_MonitorPlanStatus_as_InPending ");

		fnc_MP_InfoTab_Change_MonitorPlanStatus(Status_DDValue.split(",")[3]);
}
	
	

	
	
	@Test(dependsOnMethods={"EditMonitorPlan_InfoTab_Change_MonitorPlanStatus_as_InPending"}, priority = 10, description="  [Go to the Snapshot tab >Verify that 'Create Work Order' button should be disabled.]")
	public void EditMonitorPlan_SnapshotTab_Verify_CreateWorkOrderButton_Disabled_WhenMPStatusPending() throws Throwable{
		
		fnsApps_Report_Logs("=========================================================================================================================================");
		fnsApps_Report_Logs("################### Test Case ::::::9 EditMonitorPlan_SnapshotTab_Verify_CreateWorkOrderButton_Disabled_WhenMPStatusPending ");
		
		fnc_MP_SnapshotTab_Verify_CreateWorkOrderButton_Disabled();
}
		
	
	
	
//########################################################################################################################################################################################
//############################################################   Class Function ##########################################################################################################	

//Info tab>Change the status of Monitor Plan
	public void fnc_MP_InfoTab_Change_MonitorPlanStatus(String Status_DDValue) throws Throwable{
		try{
			for(int clicktry=0; clicktry<6; clicktry++){
				fnsGet_Element_Enabled("EditAnnualMonitorPlan_Info_TAB");
				fnsLoading_Progressingwait(1);
				fnsWait_and_Click("EditAnnualMonitorPlan_Info_TAB");
				fnsLoading_Progressingwait(2);
						
				if(driver.findElements(By.xpath(OR_MtrPlan.getProperty("InfoTab_Status_DDClick"))).size()>0){
					break;
				}
				if(clicktry==5){
					fnsTake_Screen_Shot("TabOpenFail");
					throw new Exception("FAILED == <Info> TAB is not opened, Please refer screen shot >> TabOpenFail"+fnsScreenShot_Date_format());
				}					
			}
					
			fnsGet_Element_Enabled("Footer");
					
			fnsGet_Element_Enabled("InfoTab_Status_DDClick");
			fnsDD_value_Select_By_MatchingText("InfoTab_Status_DDClick", "InfoTab_Status_DDValue", "li", Status_DDValue);
			
			fnsWait_and_Click("InfoTab_Save_Bttn");
			fnsLoading_Progressingwait(3);
			fnsGet_Element_Enabled("Footer");
			fnsGet_Element_Enabled("Success_Message_Div");
			fnsText_Fetch_and_Assert("Success_Message_Div", "success");	
		}catch(Throwable t){
			isTestCasePass = false;
			fnsApps_Report_Logs(t.getMessage());
			throw new Exception(t.getMessage());
		}
	}
	
	
//Info tab>Change the status of Monitor Plan    
	public void fnc_MP_SnapshotTab_Verify_CreateWorkOrderButton_Disabled() throws Throwable{
		try{
			for(int clicktry=0; clicktry<6; clicktry++){
				fnsGet_Element_Enabled("EditAnnualMonitorPlan_Snapshot_TAB");
				fnsWait_and_Click("EditAnnualMonitorPlan_Snapshot_TAB");
				fnsLoading_Progressingwait(2);
										
				if(driver.findElements(By.xpath(OR_MtrPlan.getProperty("SnapshotTab_TaskSummary_ChooseYear_DDClick"))).size()>0){
					break;
				}
				if(clicktry==5){
					fnsTake_Screen_Shot("TabOpenFail");
					throw new Exception("FAILED == <Snapshot> TAB is not opened, Please refer screen shot >> TabOpenFail"+fnsScreenShot_Date_format());
				}
			}
						
			fnsGet_Element_Enabled("Footer");
			
			fnsDD_value_Select_By_MatchingText("SnapshotTab_TaskSummary_ChooseYear_DDClick", "SnapshotTab_TaskSummary_ChooseYear_DDValue", "li", TaskSummary_ChooseYear_DD);
			
			try{
				assert (driver.findElements(By.xpath("//button[@id='mainForm:tabView:mptasksnpcrwobtn' and @aria-disabled='true']")).size()>0):"FAILED == Create Work Order Button is not disabled, Please refer screenshot[CreateWOBttnDisableFail" +  fnsScreenShot_Date_format() +"].";
				fnsApps_Report_Logs("PASSED == Create Work Order Button is disabled.");
			}catch (Throwable t) {
				fnsTake_Screen_Shot("CreateWOBttnDisableFail");
				throw new Exception(t.getMessage().trim());
			}
			
			/*				
			fnsGet_Element_Enabled("SnapshotTab_TaskSummary_CreateWorkOrder_bttn");
			fnsWait_and_Click("SnapshotTab_TaskSummary_CreateWorkOrder_bttn");
			fnsLoading_Progressingwait(3);
		
			try{
				assert !(fnsGet_OR_MtrPlan_ObjectX("SnapshotTab_TaskSummary_CreateWorkOrder_ConfirmationBox_Yes_Bttn").isDisplayed()):"FAILED == Create Work Order Button is not disabled, Please refer screenshot[CreateWOBttnDisableFail" +  fnsScreenShot_Date_format() +"].";
				fnsApps_Report_Logs("PASSED == Create Work Order Button is disabled.");
			}catch (Throwable t) {
				fnsTake_Screen_Shot("CreateWOBttnDisableFail");
				throw new Exception(t.getMessage().trim());
			}*/
		}catch(Throwable t){
			isTestCasePass = false;
			fnsApps_Report_Logs(t.getMessage());
			throw new Exception(t.getMessage());
		}
}	
	
	
	
	
//########################################################################################################################################################################################
//############################################################   Config Function #########################################################################################################	

	@AfterTest
	public void reportTestResult() throws Throwable {
		
		fns_ReportTestResult_atClassLevel(MonitorPlan_suitexls,  (this.getClass().getSimpleName()) , isTestCasePass);
	}
	
	
	@AfterTest
	public void QuitBrowser(){
		driver.quit();
	}
	
}	
