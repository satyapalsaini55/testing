package nsf.ecap.New_NSFOnline_Suite;

import java.util.ArrayList;

import nsf.ecap.MonitorPlan_Suite.TestSuiteBase_MonitorPlan;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;


public class BS_04_CA_Alert_AdvSrch_Snpshot extends TestSuiteBase_New_NSFOnline {



@Parameters({ "className" })
@BeforeTest
public void checkTestSkip(String className) throws Exception {
	if (className.isEmpty()) {
		className = this.getClass().getSimpleName();
	}
	IsBrowserPresentAlready =false;
	fnsCheckClassLevelTestSkip(className);
}


public Integer CorrectiveActions_View_Total_no_of_Records_Count = 270;
public Integer CorrectiveActions_AdvanceSearch_Total_no_of_Records_Count = 270;


//##########################################   EXCEL Variable ###############################################################################################

public String CorrectiveActions_View_Name = " - "+fnsReturn_CurrentTime();
public String CorrectiveActions_View_Name_EDIT = " - "+fnsReturn_CurrentTime();
public String Snapshot_View_Name = "Snapshot - "+fnsReturn_CurrentTime();
public String Snapshot_PieChart_Name = "Automation - Pie Chart";
public String CorrectiveActions_CreateView_Step2_Status_DD = null;
public String Snapshot_View_PieChartConfiguration_For_DD = null;
public String Snapshot_View_PieChartConfiguration_GroupBy_DD = null;
public String Snapshot_View_PieChartConfiguration_DateAndValue_DD = null;
public String Snapshot_View_PieChartConfiguration_Format_DD = null;
public String Snapshot_View_PieChartConfiguration_AggregateFunction_DD = null;








@Test( priority = 0)
public void Launch_Browser_and_Successfully_Login_into_the_Application() throws Throwable{
	try{
		CorrectiveActions_View_Name = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "CorrectiveActions_View_Name");
		CorrectiveActions_View_Name_EDIT = CorrectiveActions_View_Name+"-Edited";
		Snapshot_View_Name = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Snapshot_View_Name");
		Snapshot_PieChart_Name = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Snapshot_PieChart_Name");
		CorrectiveActions_CreateView_Step2_Status_DD = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "CorrectiveActions_CreateView_Step2_Status_DD");
		Snapshot_View_PieChartConfiguration_For_DD = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Snapshot_View_PieChartConfiguration_For_DD");
		Snapshot_View_PieChartConfiguration_GroupBy_DD = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Snapshot_View_PieChartConfiguration_GroupBy_DD");
		Snapshot_View_PieChartConfiguration_DateAndValue_DD = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Snapshot_View_PieChartConfiguration_DateAndValue_DD");
		Snapshot_View_PieChartConfiguration_Format_DD = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Snapshot_View_PieChartConfiguration_Format_DD");
		Snapshot_View_PieChartConfiguration_AggregateFunction_DD = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Snapshot_View_PieChartConfiguration_AggregateFunction_DD");
		
			
		fnsApps_Report_Logs("################################## [BS-04] Create Edit Delete CA View, AdvanceSearch, Alerts and PieChart Records Count");
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
public void Create_Edit_and_Delete_Views_in_CorrectiveActions_and_in_SNAPSHOT_and_CrossMatch_of_Records_Count_Verify_AdvanceSearch_and_Alerts() throws Throwable{
	fnsApps_Report_Logs("################### Test Case ::1 Create_Edit_and_Delete_Views_in_CorrectiveActions_and_in_SNAPSHOT_and_CrossMatch_of_Records_Count_Verify_AdvanceSearch_and_Alerts ");
	try{	
		ArrayList<Integer> SitesCA_Status_Records_Count_List = new ArrayList<Integer>();
		ArrayList<String> SitesCA_Status_Column_Values_Text_List = new ArrayList<String>();
		if( fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "SitesCA_Status_Column_Values").contains(",") ){
			for(int i=0; i<fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "SitesCA_Status_Column_Values").split(",").length; i++){
				SitesCA_Status_Column_Values_Text_List.add(fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "SitesCA_Status_Column_Values").split(",")[i].trim());
			}
		}else{
			SitesCA_Status_Column_Values_Text_List.add(fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "SitesCA_Status_Column_Values").trim());
		}
		
		fnsLoading_Progressing_wait(5);
		fncSub_Menu_Ajax_Link_Click_By_PassingAjaxPath("Sites_Ajax", "AuditCorrectiveActions_Ajax");
		
		fncNavigation_Verify_Application_Navigated_To_ViewScreen("'Corrective Actions' Menu Ajax", "CorrectiveActions");
		/*
		fnsLoading_Progressing_wait(5);
		for(int wait=0; wait<=((NewNsfOnline_Element_Max_Wait_Time)); wait++){
			if(driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("CreateNewView_Link"))).size()>0){
				fnsApps_Report_Logs("PASSED == Successfully opened 'CorrectiveActions' screen.");
				fnsLoading_Progressing_wait(2);
				break;
			}else{
				Thread.sleep(1000);
			}
			if(wait==((NewNsfOnline_Element_Max_Wait_Time))){
				fnsTake_Screen_Shot("CorrectiveActionsScreen_Open_Fail");
				throw new Exception("FAILED == 'CorrectiveActions' screen is not getting open, After "+((NewNsfOnline_Element_Max_Wait_Time))+" seconds wait, please refer screen shot >> CorrectiveActionsScreen_Open_Fail"+fnsScreenShot_Date_format());
			}
		}*/
		
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
		//fnsDD_value_Select_TagOPTION_DDTypeSelect("CreateView_Step2_Status_DD", CorrectiveActions_CreateView_Step2_Status_DD);
		//fnsLoading_Progressing_wait(1);
		
		fnsDD_Value_select_by_DDLabelName_MultiselectDD("Status", CorrectiveActions_CreateView_Step2_Status_DD);
		
		fnsCreateView_Step3_Verify_ColumnExists_into_DD_ifNot_Then_Select("Status");
			
		//Commented discussed with sarwagya
		//fnsGet_Element_Enabled("CreateView_Step4_VisibleToAllUsers_Radio_bttn");
		//fnsWait_and_Click("CreateView_Step4_VisibleToAllUsers_Radio_bttn");
		
		
		
		fnsGet_Element_Enabled("CreateView_CreateView_Bttn");
		fnsWait_and_Click("CreateView_CreateView_Bttn");
	//	fnsLoading_Progressing_wait(1);
			
		fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  60, "A new view has been added to your list", 25);
		
		fncNavigation_Verify_Application_Navigated_To_ViewScreen("'CreateView' button", "Corrective Actions");
		
		/*fnsLoading_Progressing_wait(2);
		for(int wait=0; wait<=((NewNsfOnline_Element_Max_Wait_Time)); wait++){
			if(driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("CreateNewView_Link"))).size()>0){
				fnsApps_Report_Logs("PASSED == After clicking on 'Create View' button, application successfully navigated to 'CorrectiveActions' screen.");
				fnsLoading_Progressing_wait(3);
				break;
			}else{
				Thread.sleep(1000);
			}
			if(wait==((NewNsfOnline_Element_Max_Wait_Time))){
				fnsTake_Screen_Shot("CorrectiveActionsScreen_Navigation_Fail");
				throw new Exception("FAILED == After clicking on 'Create View' button, application  NOT  navigated to 'CorrectiveActions' screen, [ Wait Time ("+((NewNsfOnline_Element_Max_Wait_Time))+") seconds ], please refer screen shot >> CorrectiveActionsScreen_Navigation_Fail"+fnsScreenShot_Date_format());
			}
		}*/
			
			
		fncMyView_SharedView_NewlyCreatedView_Display_and_Conditional_Click(2, true, CorrectiveActions_View_Name, "Yes");
		
		
		fncClickonFirstFacilityfromViewResultTable_n_ReturnBackonViewScreen(Snapshot_View_PieChartConfiguration_For_DD, "Audit No.");
		
		
		
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
		
		/*
		for(int wait=0; wait<=((NewNsfOnline_Element_Max_Wait_Time)); wait++){
			if(driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("CreateNewView_Link"))).size()>0){
				fnsApps_Report_Logs("PASSED == After clicking on 'Update View' button, application successfully navigated to 'CorrectiveActions' screen.");
				fnsLoading_Progressing_wait(3);
				break;
			}else{
				Thread.sleep(1000);
			}
			if(wait==((NewNsfOnline_Element_Max_Wait_Time))){
				fnsTake_Screen_Shot("CorrectiveActionsScreen_Navigation_Fail");
				throw new Exception("FAILED == After clicking on 'Update View' button, application  NOT  navigated to 'CorrectiveActions' screen, [ Wait Time ("+((NewNsfOnline_Element_Max_Wait_Time))+") seconds ], please refer screen shot >> CorrectiveActionsScreen_Navigation_Fail"+fnsScreenShot_Date_format());
			}
		}
			*/
		
		fncNavigation_Verify_Application_Navigated_To_ViewScreen("'UpdateView' button", "Corrective Actions");
		
		fncMyView_SharedView_NewlyCreatedView_Display_and_Conditional_Click(2, true, CorrectiveActions_View_Name_EDIT, "Yes");
		
		
		CorrectiveActions_View_Total_no_of_Records_Count = fnsReturn_View_Results_Reords_Total_Count("Site View ( "+CorrectiveActions_View_Name_EDIT+" )" , "View_Result_Table", 20);
		for(int i=0; i<SitesCA_Status_Column_Values_Text_List.size(); i++){
			try{
				SitesCA_Status_Records_Count_List.add( fnsFilter_ApplyFilter_and_Return_Total_Filtered_Record_Count("Status : "+SitesCA_Status_Column_Values_Text_List.get(i), "CA_STATUS", SitesCA_Status_Column_Values_Text_List.get(i)) );
			}catch(Throwable t){
				SitesCA_Status_Records_Count_List.add( fnsFilter_ApplyFilter_and_Return_Total_Filtered_Record_Count("Status : "+SitesCA_Status_Column_Values_Text_List.get(i), "STATUS", SitesCA_Status_Column_Values_Text_List.get(i)) );
			}//temp added try catch as status filter xpaths has been changed
		}
		
		fnsLoading_Progressing_wait(1);
		fnsGet_Element_Enabled("View_AdvanceSearch_Bttn");
		fnsWait_and_Click("View_AdvanceSearch_Bttn");
		fnsLoading_Progressing_wait(2);
		
		
		fnsGet_Element_Enabled("AuditCorrectiveActions_AdvanceSearch_Search_Bttn");
		Thread.sleep(1000);
		//fnsDD_value_Select_TagOPTION_DDTypeSelect("CreateView_Step2_Status_DD", CorrectiveActions_CreateView_Step2_Status_DD);
	//	fnsLoading_Progressing_wait(1);
		
		fnsDD_Value_select_by_DDLabelName_MultiselectDD("Status", CorrectiveActions_CreateView_Step2_Status_DD);
				
		
		fnsGet_Element_Enabled("AuditCorrectiveActions_AdvanceSearch_Search_Bttn");
		fnsWait_and_Click("AuditCorrectiveActions_AdvanceSearch_Search_Bttn");
		fnsLoading_Progressing_wait(2);
		
		CorrectiveActions_AdvanceSearch_Total_no_of_Records_Count = fnsReturn_View_Results_Reords_Total_Count("Advance Search " , "View_Result_Table", 10);
		
		try{
			assert CorrectiveActions_View_Total_no_of_Records_Count.equals(CorrectiveActions_AdvanceSearch_Total_no_of_Records_Count):"FAILED == Advance Search display incorrect results because the total no of records from VIEW '"+CorrectiveActions_View_Total_no_of_Records_Count+
					"' are NOT matched with the total no of Records from ADVANCE SEARCH '"+CorrectiveActions_AdvanceSearch_Total_no_of_Records_Count+"', please refer screen shot >> Incorrect_Result_Display"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED == Advance Search display correct results as the total no of records from VIEW '"+CorrectiveActions_View_Total_no_of_Records_Count+
					"' are matched with total no of Records from ADVANCE SEARCH '"+CorrectiveActions_AdvanceSearch_Total_no_of_Records_Count+"'.");
		}catch(Throwable t){
			fnsTake_Screen_Shot("Incorrect_Result_Display");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		
		
		
		
		fncMenu_Ajax_Link_Click_By_PassingAjaxPath("Snapshot_Ajax");
		
		fnsLoading_Progressing_wait(2);
		fncVerify_View_Display_Open_and_Delete_it(2, Snapshot_View_Name, "Snapshot_Delete_Link", "Snapshot_Remove_Link");
		
		fnsGet_Element_Enabled("Snapshot_CreateNewSnapshot_bttn");
		fnsWait_and_Click("Snapshot_CreateNewSnapshot_bttn");
		
		fnsGet_Element_Enabled("Snapshot_CreateNewSnapshot_SnapshotName_input");
		fnsWait_and_Type("Snapshot_CreateNewSnapshot_SnapshotName_input", Snapshot_View_Name);
		Thread.sleep(1000); // As validation message is coming on first text enter
		fnsGet_Element_Enabled("Snapshot_CreateNewSnapshot_CreateSnapshot_bttn");
		fnsWait_and_Click("Snapshot_CreateNewSnapshot_CreateSnapshot_bttn");
		
		fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  60, "success", 5);
		
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
		fnsLoading_Progressing_wait(2);
		fnsWait_and_Type("Snapshot_View_PieChartConfiguration_Title_input", Snapshot_PieChart_Name);
		
		
		fnsDD_value_Select_TagOPTION_DDTypeSelect("Snapshot_View_PieChartConfiguration_For_DD", Snapshot_View_PieChartConfiguration_For_DD, (NewNsfOnline_Element_Max_Wait_Time/2));
		fnsLoading_Progressing_wait(2);
		
		
		fncMyView_SharedView_NewlyCreatedView_Click_from_PopUp(CorrectiveActions_View_Name_EDIT);
		
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
		
		
		/*fncVerify_Success_or_Error_Message_on_ModelPopup_Generation(true, NewNsfOnline_Element_Max_Wait_Time, "Piechart created successfully", 25);
		fnsGet_Element_Enabled("Model_Popup_OK_Bttn");
		fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
		fnsWait_and_Click("Model_Popup_OK_Bttn");*/
		fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  NewNsfOnline_Element_Max_Wait_Time, "Piechart created successfully", 20);
		try{
			fncVerify_GraphDisplay_And_RecordsCount_Validate(CorrectiveActions_View_Total_no_of_Records_Count, SitesCA_Status_Column_Values_Text_List,  SitesCA_Status_Records_Count_List, false);
		}catch(Throwable t){
			fncVerify_GraphDisplay_And_RecordsCount_Validate(CorrectiveActions_View_Total_no_of_Records_Count, SitesCA_Status_Column_Values_Text_List,  SitesCA_Status_Records_Count_List, true);
		}
		
		
		fns_AlertScreen_Click_on_AlertMenu_and_Verify_View_and_Label_Present(true, false, Snapshot_View_PieChartConfiguration_For_DD, CorrectiveActions_View_Name_EDIT);
	//	isTestCasePass = true;
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