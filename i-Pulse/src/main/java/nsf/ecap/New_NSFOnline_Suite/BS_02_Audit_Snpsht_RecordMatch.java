package nsf.ecap.New_NSFOnline_Suite;


import java.util.ArrayList;

import nsf.ecap.MonitorPlan_Suite.TestSuiteBase_MonitorPlan;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;


public class BS_02_Audit_Snpsht_RecordMatch extends TestSuiteBase_New_NSFOnline {



@Parameters({ "className" })
@BeforeTest
public void checkTestSkip(String className) throws Exception {
	
	if (className.isEmpty()) {
		className = this.getClass().getSimpleName();
	}
	IsBrowserPresentAlready =false;
	fnsCheckClassLevelTestSkip(className);
}


public Integer SiteAudits_View_Total_no_of_Records_Count = 270;



//##########################################   EXCEL Variable ###############################################################################################

public String SiteAudits_View_Name = "Audit - "+fnsReturn_CurrentTime();
public String Snapshot_View_Name = "Snapshot - "+fnsReturn_CurrentTime();
public String Snapshot_PieChart_Name = "Automation - Pie Chart";
public String SiteAudits_CreateView_Step2_Country_DD = null;
public String Snapshot_View_PieChartConfiguration_For_DD = null;
public String Snapshot_View_PieChartConfiguration_GroupBy_DD = null;
public String Snapshot_View_PieChartConfiguration_DateAndValue_DD = null;
public String Snapshot_View_PieChartConfiguration_Format_DD = null;
public String Snapshot_View_PieChartConfiguration_AggregateFunction_DD = null;
public String Snapshot_View_PieChartConfiguration_Theme_DD = null;








@Test( priority = 0)
public void Launch_Browser_and_Successfully_Login_into_the_Application() throws Throwable{
	try{
		SiteAudits_View_Name = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Audit_View_Name");
		Snapshot_View_Name = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Snapshot_View_Name");
		Snapshot_PieChart_Name = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Snapshot_PieChart_Name");
		SiteAudits_CreateView_Step2_Country_DD = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "SitesAudits_CreateView_Step2_Country_DD");
		Snapshot_View_PieChartConfiguration_For_DD = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Snapshot_View_PieChartConfiguration_For_DD");
		Snapshot_View_PieChartConfiguration_GroupBy_DD = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Snapshot_View_PieChartConfiguration_GroupBy_DD");
		Snapshot_View_PieChartConfiguration_DateAndValue_DD = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Snapshot_View_PieChartConfiguration_DateAndValue_DD");
		Snapshot_View_PieChartConfiguration_Format_DD = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Snapshot_View_PieChartConfiguration_Format_DD");
		Snapshot_View_PieChartConfiguration_AggregateFunction_DD = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Snapshot_View_PieChartConfiguration_AggregateFunction_DD");
		Snapshot_View_PieChartConfiguration_Theme_DD = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Snapshot_View_PieChartConfiguration_Theme_DD");
		
		
		fnsApps_Report_Logs("################################## [BS-02]  Create View in Sites > Audits and Snapshot and Verify Records Count");
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
public void Create_Edit_Update_and_Delete_Views_in_Sites_AUDITS_and_in_SNAPSHOT_and_CrossMatch_of_Records_Count() throws Throwable{
	fnsApps_Report_Logs("################### Test Case ::1 Create_Edit_Update_and_Delete_Views_in_Sites_AUDITS_and_in_SNAPSHOT_and_CrossMatch_of_Records_Count ");
	try{	
		ArrayList<Integer> Audits_AuditStatus_Records_Count_List = new ArrayList<Integer>();
		ArrayList<String> Audits_AuditStatus_Column_Values_Text_List = new ArrayList<String>();
		if( fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Audits_AuditStatus_Column_Values").contains(",") ){
			for(int i=0; i<fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Audits_AuditStatus_Column_Values").split(",").length; i++){
				Audits_AuditStatus_Column_Values_Text_List.add(fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Audits_AuditStatus_Column_Values").split(",")[i].trim());
			}
		}else{
			Audits_AuditStatus_Column_Values_Text_List.add(fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Audits_AuditStatus_Column_Values").trim() );
		}
		
		fnsLoading_Progressing_wait(5);
		fncSub_Menu_Ajax_Link_Click_By_PassingAjaxPath("Sites_Ajax", "Sites_SubMenu_Audits_Ajax");
		
		fncNavigation_Verify_Application_Navigated_To_ViewScreen("'Audits' Menu Ajax", "Audits");
		
		/*fnsLoading_Progressing_wait(5);
		for(int wait=0; wait<=((NewNsfOnline_Element_Max_Wait_Time)); wait++){
			if(driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("CreateNewView_Link"))).size()>0){
				fnsApps_Report_Logs("PASSED == Successfully opened 'Site > Audits' screen.");
				fnsLoading_Progressing_wait(2);
				break;
			}else{
				Thread.sleep(1000);
			}
			if(wait==((NewNsfOnline_Element_Max_Wait_Time))){
				fnsTake_Screen_Shot("SiteScreen_Open_Fail");
				throw new Exception("FAILED == 'Sites > Audits' screen is not getting open, After "+((NewNsfOnline_Element_Max_Wait_Time))+" seconds wait, please refer screen shot >> SiteScreen_Open_Fail"+fnsScreenShot_Date_format());
			}
		}*/
		
		fncVerify_View_Display_Open_and_Delete_it(2, SiteAudits_View_Name, "View_Delete_Link", "View_Remove_Link");
		
		fnsGet_Element_Enabled("CreateNewView_Link");
		fnsWait_and_Click("CreateNewView_Link");
		fnsLoading_Progressing_wait(2);
		
		fnsGet_Element_Enabled("CreateView_Step1_ViewName");
		fnsLoading_Progressing_wait(1);
		fnsWait_and_Type("CreateView_Step1_ViewName", SiteAudits_View_Name);
		Thread.sleep(3000);
		
		//fnsDD_value_Select_TagOPTION_DDTypeSelect("CreateView_Step2_Country_DD", SiteAudits_CreateView_Step2_Country_DD); //not working as DD type changed 20.9.2017
		//fnsLoading_Progressing_wait(1);
		fnsDD_Value_select_by_DDLabelName_MultiselectDD("Country", SiteAudits_CreateView_Step2_Country_DD);
		
		fnsCreateView_Step3_Verify_ColumnExists_into_DD_ifNot_Then_Select("Audit Status");
			
		//Commented discussed with sarwagya
		//fnsGet_Element_Enabled("CreateView_Step4_VisibleToAllUsers_Radio_bttn");
		//fnsWait_and_Click("CreateView_Step4_VisibleToAllUsers_Radio_bttn");
		
		
		
		fnsGet_Element_Enabled("CreateView_CreateView_Bttn");
		fnsWait_and_Click("CreateView_CreateView_Bttn");
	//	fnsLoading_Progressing_wait(1);
			
		fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  60, "A new view has been added to your list", 25);
	
		
		fncNavigation_Verify_Application_Navigated_To_ViewScreen("'CreateView' button", "Sites");
		
		
		/*
		fnsLoading_Progressing_wait(2);
		for(int wait=0; wait<=((NewNsfOnline_Element_Max_Wait_Time)); wait++){
			if(driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("CreateNewView_Link"))).size()>0){
				fnsApps_Report_Logs("PASSED == After clicking on 'Create View' button, application successfully navigated to 'Sites' screen.");
				fnsLoading_Progressing_wait(3);
				break;
			}else{
				Thread.sleep(1000);
			}
			if(wait==((NewNsfOnline_Element_Max_Wait_Time))){
				fnsTake_Screen_Shot("SiteScreen_Navigation_Fail");
				throw new Exception("FAILED == After clicking on 'Create View' button, application  NOT  navigated to 'Sites' screen, [ Wait Time ("+((NewNsfOnline_Element_Max_Wait_Time))+") seconds ], please refer screen shot >> SiteScreen_Navigation_Fail"+fnsScreenShot_Date_format());
			}
		}
			*/
			
		fncMyView_SharedView_NewlyCreatedView_Display_and_Conditional_Click(2, true, SiteAudits_View_Name, "Yes");
		
		fncClickonFirstFacilityfromViewResultTable_n_ReturnBackonViewScreen(Snapshot_View_PieChartConfiguration_For_DD, "Audit Code");
			
		SiteAudits_View_Total_no_of_Records_Count = fnsReturn_View_Results_Reords_Total_Count("Site View ( "+SiteAudits_View_Name+" )" , "View_Result_Table", 20);
		for(int i=0; i<Audits_AuditStatus_Column_Values_Text_List.size(); i++){
			Audits_AuditStatus_Records_Count_List.add( fnsFilter_ApplyFilter_and_Return_Total_Filtered_Record_Count("Audit Status : "+Audits_AuditStatus_Column_Values_Text_List.get(i), "AUDIT_STATUS", Audits_AuditStatus_Column_Values_Text_List.get(i)) );
		}
				
		
		//########################################################     Snapshot screen start   #######################################################################################
		fncMenu_Ajax_Link_Click_By_PassingAjaxPath("Snapshot_Ajax");
		
	/*	
		//temp below 2 lines
		Thread.sleep(4000);
		driver.navigate().refresh();
		*/
		
		fnsLoading_Progressing_wait(2);
		fncVerify_View_Display_Open_and_Delete_it(2, Snapshot_View_Name, "Snapshot_Delete_Link", "Snapshot_Remove_Link");
		
		fnsGet_Element_Enabled("Snapshot_CreateNewSnapshot_bttn");
		fnsWait_and_Click("Snapshot_CreateNewSnapshot_bttn");
		
		fnsGet_Element_Enabled("Snapshot_CreateNewSnapshot_SnapshotName_input");
		fnsWait_and_Type("Snapshot_CreateNewSnapshot_SnapshotName_input", Snapshot_View_Name);
		Thread.sleep(1000); // As validation message is coming on first text enter
		fnsGet_Element_Enabled("Snapshot_CreateNewSnapshot_CreateSnapshot_bttn");
		fnsWait_and_Click("Snapshot_CreateNewSnapshot_CreateSnapshot_bttn");
		
		fns_Verify_Success_message_coming_OR_Error_message_Coming(true, 60,  "success", 5);
		
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
		fnsLoading_Progressing_wait(1);
		fnsWait_and_Type("Snapshot_View_PieChartConfiguration_Title_input", Snapshot_PieChart_Name);
		
		
		fnsDD_value_Select_TagOPTION_DDTypeSelect("Snapshot_View_PieChartConfiguration_For_DD", Snapshot_View_PieChartConfiguration_For_DD, (NewNsfOnline_Element_Max_Wait_Time/2));
		fnsLoading_Progressing_wait(2);
		
		Thread.sleep(3000);
		fncMyView_SharedView_NewlyCreatedView_Click_from_PopUp(SiteAudits_View_Name);
		
		Thread.sleep(1000);
		fnsDD_value_Select_TagOPTION_DDTypeSelect("Snapshot_View_PieChartConfiguration_GroupBy_DD", Snapshot_View_PieChartConfiguration_GroupBy_DD, (NewNsfOnline_Element_Max_Wait_Time/2));

		fnsLoading_Progressing_wait(1);
		
		fnsDD_value_Select_TagOPTION_DDTypeSelect("Snapshot_View_PieChartConfiguration_DateAndValue_DD", Snapshot_View_PieChartConfiguration_DateAndValue_DD, (NewNsfOnline_Element_Max_Wait_Time/2));
		fnsLoading_Progressing_wait(1);
		
		
		fnsDD_value_Select_TagOPTION_DDTypeSelect("Snapshot_View_PieChartConfiguration_Format_DD", Snapshot_View_PieChartConfiguration_Format_DD, (NewNsfOnline_Element_Max_Wait_Time/2));
		fnsLoading_Progressing_wait(1);
		
		
		fnsDD_value_Select_TagOPTION_DDTypeSelect("Snapshot_View_PieChartConfiguration_AggregateFunction_DD", Snapshot_View_PieChartConfiguration_AggregateFunction_DD, (NewNsfOnline_Element_Max_Wait_Time/2));
		fnsLoading_Progressing_wait(1);
		
		
		fnsDD_value_Select_TagOPTION_DDTypeSelect("Snapshot_View_PieChartConfiguration_Theme_DD", Snapshot_View_PieChartConfiguration_Theme_DD, (NewNsfOnline_Element_Max_Wait_Time/2));
		fnsLoading_Progressing_wait(1);
		
		fnsGet_Element_Enabled("Snapshot_View_PieChartConfiguration_DisplayLabel_CheckBox");
		fnsWait_and_Click("Snapshot_View_PieChartConfiguration_DisplayLabel_CheckBox");
		
		fnsGet_Element_Enabled("Snapshot_View_PieChartConfiguration_DisplayExportOption_CheckBox");
		fnsWait_and_Click("Snapshot_View_PieChartConfiguration_DisplayExportOption_CheckBox");
		
		fnsGet_Element_Enabled("Snapshot_View_PieChartConfiguration_Update_Bttn");
		fnsWait_and_Click("Snapshot_View_PieChartConfiguration_Update_Bttn");
		
		
	/*	fncVerify_Success_or_Error_Message_on_ModelPopup_Generation(true, NewNsfOnline_Element_Max_Wait_Time, "Piechart created successfully", 25);
		fnsGet_Element_Enabled("Model_Popup_OK_Bttn");
		fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
		fnsWait_and_Click("Model_Popup_OK_Bttn");
		*/
		
		fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  NewNsfOnline_Element_Max_Wait_Time, "Piechart created successfully", 20);
		try{
			fncVerify_GraphDisplay_And_RecordsCount_Validate(SiteAudits_View_Total_no_of_Records_Count, Audits_AuditStatus_Column_Values_Text_List,  Audits_AuditStatus_Records_Count_List, false);
		}catch(Throwable t){
			fncVerify_GraphDisplay_And_RecordsCount_Validate(SiteAudits_View_Total_no_of_Records_Count, Audits_AuditStatus_Column_Values_Text_List,  Audits_AuditStatus_Records_Count_List, true);
		}
		
		//isTestCasePass = true;
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