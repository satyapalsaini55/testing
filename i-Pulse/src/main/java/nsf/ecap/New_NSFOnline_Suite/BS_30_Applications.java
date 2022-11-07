package nsf.ecap.New_NSFOnline_Suite;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;

public class BS_30_Applications extends TestSuiteBase_New_NSFOnline {
	
	
	public String  Applications_View_Name = "Automation - Applications";
	public String  Applications_View_Name_Edit = "Automation - Applications - Edited";
	public String Applications_View_Name_from_AdvanceSearch = "Automation - Applications PENDING";
	public String  Applications_Status_DD = "PENDING";
	
	
	
	@Parameters({ "className" })
	@BeforeTest
	public void checkTestSkip(String className) throws Exception {
			if (className.isEmpty()) {
				className = this.getClass().getSimpleName();
			}
			RunningClassName = className;
			Running_Class_BS_Description = "[BS-30] Verify Applications";
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
			fnsLoading_Progressing_wait(1);
			fnsApps_Report_Logs("=========================================================================================================================================");
		}
	
	
	@Test( priority = 1, dependsOnMethods={"Launch_Browser_and_Successfully_Login_into_the_Application"}, description="")
	public void Create_Edit_Delete_View_and_verify_Alerts_then_Verify_AdvanceSearch_Count_with_View_Count_in_Applications() throws Throwable{
		fnsApps_Report_Logs("################### Test Case ::1 Create_Edit_Delete_View_and_verify_Alerts_then_Verify_AdvanceSearch_Count_with_View_Count_in_Applications");		
		try{
			BS_26_Standard_Members BS_26_Standard_Members = new BS_26_Standard_Members();
			BS_26_Standard_Members.Standard_Members_Create_and_Edit_View_Flow_Only("Applications", "Applications_Ajax", Applications_View_Name, Applications_View_Name_Edit);
			
			fns_AlertScreen_Click_on_AlertMenu_and_Verify_View_and_Label_Present(true, true, "Applications", Applications_View_Name_Edit);			
			fnsLoading_Progressing_wait(1);
			
			fncVerify_View_Display_Open_and_Delete_it(2, Applications_View_Name_from_AdvanceSearch, "View_Delete_Link", "View_Remove_Link");
			
			fnsGet_Element_Enabled("View_AdvanceSearch_Bttn");
			fnsWait_and_Click("View_AdvanceSearch_Bttn");
			fnsLoading_Progressing_wait(2);
			
			fnsDD_Value_select_by_DDLabelName_MultiselectDD("App Status", Applications_Status_DD);
			fnsLoading_Progressing_wait(2);
			
			
			fnsGet_Element_Enabled("Application_AdvanceSearch_bttn");
			fnsWait_and_Click("Application_AdvanceSearch_bttn");
			fnsLoading_Progressing_wait(2);
			
			
			Integer Application_AdvanceSearch_Total_no_of_Records_Count = fnsReturn_View_Results_Reords_Total_Count("Advance Search" , "View_Result_Table", 10);
			
			fnsGet_Element_Enabled("AdvanceSearch_SaveAsView");
			fnsWait_and_Click("AdvanceSearch_SaveAsView");
			fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
			
			fnsGet_Element_Enabled("AdvanceSearch_SaveAsView_PopUpBox");
			fnsWait_and_Click("AdvanceSearch_SaveAsView_PopUpBox");
			fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
			
			fnsGet_Element_Enabled("CreateView_Step1_ViewName");
			fnsWait_and_Clear("CreateView_Step1_ViewName");
			fnsWait_and_Type("CreateView_Step1_ViewName", Applications_View_Name_from_AdvanceSearch);
			fnsLoading_Progressing_wait(2);
			
			fnsGet_Element_Enabled("CreateView_CreateView_Bttn");
			fnsWait_and_Click("CreateView_CreateView_Bttn");
		
			fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  60, "A new view has been added to your list", 25);
			
			Integer Application_Total_no_of_Records_Count = fnsReturn_View_Results_Reords_Total_Count("Applications" , "View_Result_Table", 20);
			
			try{
				assert Application_Total_no_of_Records_Count.equals(Application_AdvanceSearch_Total_no_of_Records_Count):"FAILED == Advance Search results count <"+Application_AdvanceSearch_Total_no_of_Records_Count+"> and VIEW Created-From-Advance-Search count <"+
						Application_Total_no_of_Records_Count+"> are not matched, please refer screen shot >> Count_Mismatch"+fnsScreenShot_Date_format();
				fnsApps_Report_Logs("PASSED == Advance Search results count <"+Application_AdvanceSearch_Total_no_of_Records_Count+"> and VIEW Created-From-Advance-Search count <"+ Application_Total_no_of_Records_Count +"> are matched.");
			}catch(Throwable t){
				fnsTake_Screen_Shot("Count_Mismatch");
				throw new Exception(Throwables.getStackTraceAsString(t));
			}
		
		//	fncNavigation_Verify_Application_Navigated_To_ViewScreen("'Applications' Menu Ajax", "Applications");
			fncVerify_View_Display_Open_and_Delete_it(2, Applications_View_Name_from_AdvanceSearch, "View_Delete_Link", "View_Remove_Link");

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
	
