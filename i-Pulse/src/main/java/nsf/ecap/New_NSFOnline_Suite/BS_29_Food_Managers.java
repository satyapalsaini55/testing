package nsf.ecap.New_NSFOnline_Suite;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;

public class BS_29_Food_Managers extends TestSuiteBase_New_NSFOnline{
	
	public String  Food_Managers_View_Name = "Automation - Food_Managers_View";
	public String  Food_Managers_View_Name_Edit = "Automation - Food_Managers_View_Edited";
	public String Food_Managers_View_Name_from_AdvanceSearch = "Automation - Food Managers Hierarchy";
	
	public String  Hierarchy_DD = "AVI 2016 Hierarchy";
	public String  Vice_President_DD = "";
	
	
	
	@Parameters({ "className" })
	@BeforeTest
	public void checkTestSkip(String className) throws Exception {
		if (className.isEmpty()) {
			className = this.getClass().getSimpleName();
		}
		RunningClassName = className;
		Running_Class_BS_Description = "[BS-29] Verify Food Managers";
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
	
	
	@Test( priority = 1, dependsOnMethods="Launch_Browser_and_Successfully_Login_into_the_Application")
	public void Create_Edit_Views_in_Food_Manager_and_Verify_View_data_in_AdvanceSearch_And_Alerts_Screen() throws Throwable{
		fnsApps_Report_Logs("################### Test Case ::1 Create_Edit_Views_in_Food_Managers_and_Verify_View_data_in_AdvanceSearch_And_Alerts_Screen");
			if(env.equalsIgnoreCase("test")){
				Vice_President_DD = "AVI 2016 Hierarchy Division 18440483";
				Vice_President_DD = "Vince Lombardi";
			}else{
				Vice_President_DD = "Vince Lombardi";
			}
		try{
			BS_26_Standard_Members BS_26_Standard_Members = new BS_26_Standard_Members();
			BS_26_Standard_Members.Standard_Members_Create_and_Edit_View_Flow_Only("Food Managers", "Food_Manager_Ajax", Food_Managers_View_Name, Food_Managers_View_Name_Edit);
			
			fncVerify_View_Display_Open_and_Delete_it(2, Food_Managers_View_Name_from_AdvanceSearch, "View_Delete_Link", "View_Remove_Link");	

			fnsGet_Element_Enabled("View_AdvanceSearch_Bttn");
			fnsWait_and_Click("View_AdvanceSearch_Bttn");
			fnsLoading_Progressing_wait(2);
			fnsLoading_Progressing_wait(2);
			fnsDD_value_Select_TagOPTION_DDTypeSelect("Hierarchy_DD", Hierarchy_DD, 2);
			fnsLoading_Progressing_wait(3);
			fnsLoading_Progressing_wait(3);
	
			fnsDD_Value_select_by_DDLabelName_MultiselectDD("Vice President_Dependent", Vice_President_DD);
			fnsLoading_Progressing_wait(2);
			
			fnsGet_Element_Enabled("Food_Manager_AdvanceSearch_bttn");
			fnsWait_and_Click("Food_Manager_AdvanceSearch_bttn");
			fnsLoading_Progressing_wait(2);
			
			
			Integer Food_Managers_AdvanceSearch_Total_no_of_Records_Count = fnsReturn_View_Results_Reords_Total_Count("Advance Search" , "View_Result_Table", 10);
			
			fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
			fnsGet_Element_Enabled("AdvanceSearch_SaveAsView");
			fnsWait_and_Click("AdvanceSearch_SaveAsView");
			fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
			fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
			fnsGet_Element_Enabled("AdvanceSearch_SaveAsView_PopUpBox");
			fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
			fnsWait_and_Click("AdvanceSearch_SaveAsView_PopUpBox");
			
			fnsGet_Element_Enabled("CreateView_Step1_ViewName");
			fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
			
			
			fnsWait_and_Clear("CreateView_Step1_ViewName");
			fnsWait_and_Type("CreateView_Step1_ViewName", Food_Managers_View_Name_from_AdvanceSearch);
			fnsLoading_Progressing_wait(2);
			
			fnsGet_Element_Enabled("CreateView_CreateView_Bttn");
			fnsWait_and_Click("CreateView_CreateView_Bttn");
		
			fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  60, "A new view has been added to your list", 25);
			
			Integer Food_Managers_Total_no_of_Records_Count = fnsReturn_View_Results_Reords_Total_Count("Food Managers" , "View_Result_Table", 20);
			
			
			try{
				assert Food_Managers_Total_no_of_Records_Count.equals(Food_Managers_AdvanceSearch_Total_no_of_Records_Count):"FAILED == Advance Search results count <"+Food_Managers_AdvanceSearch_Total_no_of_Records_Count+"> and VIEW Created-From-Advance-Search count <"+
						Food_Managers_Total_no_of_Records_Count+"> are not matched, please refer screen shot >> Count_Mismatch"+fnsScreenShot_Date_format();
				fnsApps_Report_Logs("PASSED == Advance Search results count <"+Food_Managers_AdvanceSearch_Total_no_of_Records_Count+"> and VIEW Created-From-Advance-Search count <"+ Food_Managers_Total_no_of_Records_Count +"> are matched.");
			}catch(Throwable t){
				fnsTake_Screen_Shot("Count_Mismatch");
				throw new Exception(Throwables.getStackTraceAsString(t));
			}
			
			
							
			fncNavigation_Verify_Application_Navigated_To_ViewScreen("'Food Managers' Menu Ajax", "Food Managers");
			fncVerify_View_Display_Open_and_Delete_it(2, Food_Managers_View_Name_from_AdvanceSearch, "View_Delete_Link", "View_Remove_Link");
			

		}catch(Throwable t){
			isTestCasePass = false;
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		fnsApps_Report_Logs("=========================================================================================================================================");
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
