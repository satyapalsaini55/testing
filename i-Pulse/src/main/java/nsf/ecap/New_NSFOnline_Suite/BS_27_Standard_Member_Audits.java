package nsf.ecap.New_NSFOnline_Suite;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;

public class BS_27_Standard_Member_Audits extends TestSuiteBase_New_NSFOnline {
	
	public String StandardMemberAudits_View_Name = "Automation - Std. Member Audit";
	public String StandardMemberAudit_View_Name_EDIT = "Automation - Std. Member Audit-Edited";
	public String StandardMemberAudit_View_Name_from_AdvanceSearch = "Automation - Std. Member Audit-AS"  ;

	
	@Parameters({ "className" })
	@BeforeTest
	public void checkTestSkip(String className) throws Exception {
		if (className.isEmpty()) {
			className = this.getClass().getSimpleName();
		}
		RunningClassName = className;
		Running_Class_BS_Description = "[BS-27] Verify Standard Member Audits";
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
	public void Create_Edit_Delete_View_and_verify_Alerts_then_Verify_AdvanceSearch_Count_with_View_Count_in_Standard_Member_Audits() throws Throwable{
		fnsApps_Report_Logs("################### Test Case ::1 Create_Edit_Delete_View_and_verify_Alerts_then_Verify_AdvanceSearch_Count_with_View_Count_in_Standard_Member_Audits");		
		try{
			BS_26_Standard_Members BS_26_Standard_Members = new BS_26_Standard_Members();
			BS_26_Standard_Members.Standard_Members_Create_and_Edit_View_Flow_Only("Standard Member Audit", "StandardMembersAudit_Ajax", "Automation - Std. Member Audit", "Automation - Std. Member Audit-Edited");
						
			fns_AlertScreen_Click_on_AlertMenu_and_Verify_View_and_Label_Present(true, true, "Standard Member Audits", StandardMemberAudit_View_Name_EDIT);
			fncVerify_View_Display_Open_and_Delete_it(2, StandardMemberAudit_View_Name_EDIT, "View_Delete_Link", "View_Remove_Link");			
			
			fncVerify_View_Display_Open_and_Delete_it(2, StandardMemberAudit_View_Name_from_AdvanceSearch, "View_Delete_Link", "View_Remove_Link");	
			fnsLoading_Progressing_wait(1);
			fnsGet_Element_Enabled("View_AdvanceSearch_Bttn");
			fnsWait_and_Click("View_AdvanceSearch_Bttn");
			fnsLoading_Progressing_wait(2);			
			
			fnsDD_Value_select_by_DDLabelName_MultiselectDD("Status", "COMPLETED");
			
			fnsGet_Element_Enabled("AuditCorrectiveActions_AdvanceSearch_Search_Bttn");
			fnsWait_and_Click("AuditCorrectiveActions_AdvanceSearch_Search_Bttn");
			fnsLoading_Progressing_wait(2);				
			
			Integer StandardMemberAudit_AdvanceSearch_Total_no_of_Records_Count = fnsReturn_View_Results_Reords_Total_Count("Advance Search " , "View_Result_Table", 10);
			
			fnsGet_Element_Enabled("AdvanceSearch_SaveAsView");
			fnsWait_and_Click("AdvanceSearch_SaveAsView");
			fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
			
			fnsGet_Element_Enabled("AdvanceSearch_SaveAsView_PopUpBox");
			fnsWait_and_Click("AdvanceSearch_SaveAsView_PopUpBox");
			fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
			
			fnsGet_Element_Enabled("CreateView_Step1_ViewName");
			fnsWait_and_Clear("CreateView_Step1_ViewName");
			fnsWait_and_Type("CreateView_Step1_ViewName", StandardMemberAudit_View_Name_from_AdvanceSearch);
			fnsLoading_Progressing_wait(2);
			
			fnsGet_Element_Enabled("CreateView_CreateView_Bttn");
			fnsWait_and_Click("CreateView_CreateView_Bttn");
		
			fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  60, "A new view has been added to your list", 25);
			
			Integer StandardMemberAudit_Gridscreen_Total_no_of_Records_Count = fnsReturn_View_Results_Reords_Total_Count("Standard Member Audits" , "View_Result_Table", 20);		
				
			try{
				assert StandardMemberAudit_Gridscreen_Total_no_of_Records_Count.equals(StandardMemberAudit_AdvanceSearch_Total_no_of_Records_Count):"FAILED == Advance Search results count <"+StandardMemberAudit_AdvanceSearch_Total_no_of_Records_Count+"> and VIEW Created-From-Advance-Search count <"+
						StandardMemberAudit_Gridscreen_Total_no_of_Records_Count+"> are not matched, please refer screen shot >> Count_Mismatch"+fnsScreenShot_Date_format();
				fnsApps_Report_Logs("PASSED == Advance Search results count <"+StandardMemberAudit_AdvanceSearch_Total_no_of_Records_Count+"> and VIEW Created-From-Advance-Search count <"+
						StandardMemberAudit_Gridscreen_Total_no_of_Records_Count+"> are matched.");
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
		try{
			driver.quit();
			Thread.sleep(3000);
		}catch(Throwable t){
			//nothing to do
		}
	}
}