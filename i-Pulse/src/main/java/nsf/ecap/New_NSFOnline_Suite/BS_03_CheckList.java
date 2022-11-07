package nsf.ecap.New_NSFOnline_Suite;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;
/*import com.opera.core.systems.scope.protos.CoreProtos.ClearPrivateDataArg;*/


public class BS_03_CheckList extends TestSuiteBase_New_NSFOnline {



@Parameters({ "className" })
@BeforeTest
public void checkTestSkip(String className) throws Exception {
	if (className.isEmpty()) {
		className = this.getClass().getSimpleName();
	}
	RunningClassName = className;
	Running_Class_BS_Description = "[BS-03]  Create Edit and Update Check List";
	IsBrowserPresentAlready =false;
	fnsCheckClassLevelTestSkip(className);
}





//##########################################   EXCEL Variable ###############################################################################################
public String CheckList_View_Name = "CheckList - "+fnsReturn_CurrentTime();
public String AddFacilityPopup_CustomerNumber = "5237";
public String CheckList_StartsOn_Date ="25-Sep-2021"; //TestSuiteBase_New_NSFOnline.fns_CheckList_Requried_Date_format("dd-MMM-YYYY", 0) ; // getting testng error class=null
public String CheckList_End_Date = "25-Sep-2021";
public String CheckListNumber = "96875";
public Integer CheckList_Created_for_above_Criteria;
public String CarNo_1 = "C96875-1";
public String CarNo_2 = "C96875-10";
public Integer CA_count_After_ShowAll = null;
public Integer CorrectiveAction_count_from_Card = null;
public Integer CheckList_Count_From_Card = null;






@Test( priority = 0)
public void Launch_Browser_and_Successfully_Login_into_the_Application() throws Throwable{
	try{
		BS_03_Runmode=true;
		fnsApps_Report_Logs("################################## "+Running_Class_BS_Description);
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
public void Create_View_and_CreateEditApprove_CorrectiveActions_in_CheckList() throws Throwable{
	fnsApps_Report_Logs("################### Test Case ::1 Create_View_and_CreateEditApprove_CorrectiveActions_in_CheckList ");
	try{	
		fncLogin_into_Application_and_Create_CheckList();
				
		CheckList_Created_for_above_Criteria = 0;
		for(int i=1; i<=5; i++){
			if(CheckList_Created_for_above_Criteria == 0){
				fnsFilter_ApplyFilter_and_Return_Total_Filtered_Record_Count("DEADLINE DATE : ", "DEADLINE_DATE", CheckList_End_Date);
				CheckList_Created_for_above_Criteria = fnsFilter_ApplyFilter_and_Return_Total_Filtered_Record_Count("Facility Code : ", "FACILITY_CODE", AddFacilityPopup_CustomerNumber);
			}
			if(CheckList_Created_for_above_Criteria==1){
				break;
			}
			
			if( !(CheckList_Created_for_above_Criteria==1) && i==5){
				fnsTake_Screen_Shot("CheckList_Display_in_View_Fail");
				throw new Exception ("FAILED ==  After 5 times filters used, CheckList created for Date<"+CheckList_StartsOn_Date+"> is not coming into the View, please refer the screen shot >>CheckList_Display_in_View_Fail"+fnsScreenShot_Date_format());
			}
		}
		
		String CheckListNumber_Cell_Xpath= OR_New_NSFOnline.getProperty("View_Result_Table")+"/tr[1]/td["+fnsReturn_ColumnNumber_from_TableHeaderXpath_WithoutOR(OR_New_NSFOnline.getProperty("View_Result_TableHeader"), "Checklist Number")+"]";
		CheckListNumber = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(CheckListNumber_Cell_Xpath).getText().trim();
		fnsApps_Report_Logs("PASSED == New CheckList Number <"+CheckListNumber+"> successfully created for Date <"+CheckList_StartsOn_Date+">.");
		
		try{
			driver.quit();
			Thread.sleep(3000);
		}catch(Throwable t){
			//nothing to do
		}
		
		//DBA-25264 ---DBA-25734
		fncDB_Query_to_set_Checklist_DeadLineDate_in_BackDate(CheckListNumber);
		
			
		Login_UserName = fnsReturn_ExcelCellValue_ByMatchingColumnName("BS_03_CheckList", "Login_UserName_Password_to_SaveSubmit_CA").split(":")[0].trim();
		Login_Password = fnsReturn_ExcelCellValue_ByMatchingColumnName("BS_03_CheckList", "Login_UserName_Password_to_SaveSubmit_CA").split(":")[1].trim();
		Login_Application_Name = "nsf_connect";
		fnsBrowserLaunchAndLogin(Login_UserName, Login_Password);
		fnsSwitchAcoount_MultiAccess(this.getClass().getSimpleName(), "SwitchAccount_DD_Greeneking5237");
		fnsApps_Report_Logs("Browser is launched and Successfully login into Application for User <"+Login_UserName+">.");	
		
		
		fnsGet_Element_Enabled("CheckList_Card_Link");
		String CheckList_Card_Link_Text = fnsGet_Field_TEXT("CheckList_Card_Link").trim();
		try{
			CheckList_Count_From_Card = Integer.parseInt(CheckList_Card_Link_Text);
		}catch(NumberFormatException nc){
			fnsTake_Screen_Shot("Checklist_Card_Empty");
			throw new Exception("FAILED == Checklist count is not coming into the checklist card, please refer the screenshot >> Checklist_Card_Empty"+fnsScreenShot_Date_format());
		}
		
		fnsWait_and_Click("CheckList_Card_Link");
		fnsLoading_Progressing_wait(2);
		
		
		for(int wait=0; wait<=((NewNsfOnline_Element_Max_Wait_Time)); wait++){
			if(driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("Pagination_DropDown"))).size()>0){
				fnsApps_Report_Logs("PASSED == After clicking on 'CheckList Card' Link, application successfully navigated to 'CheckList' screen.");
				fnsLoading_Progressing_wait(3);
				break;
			}else{
				Thread.sleep(1000);
			}
			if(wait==((NewNsfOnline_Element_Max_Wait_Time))){
				fnsTake_Screen_Shot("CheckList_Navigation_Fail");
				throw new Exception("FAILED == After clicking on 'CheckList Card' button, application  NOT  navigated to 'CheckList' screen, [ Wait Time ("+((NewNsfOnline_Element_Max_Wait_Time))+") seconds ], please refer screen shot >> CA_Navigation_Fail"+fnsScreenShot_Date_format());
			}
		}
		
//Added - NOM-3349
		Integer CheclistListViewScreen_records_Count = fnsReturn_Pagination_Reords_Total_Count ("Checklis_List_View screen");;
		
		try{
			assert ( (CheckList_Count_From_Card.equals(CheclistListViewScreen_records_Count)) );
			fnsApps_Report_Logs("PASSED == Checklist count are matched. [ChecklistCard ("+CheckList_Count_From_Card+") vs ChecklistListViewScreen ("+CheclistListViewScreen_records_Count+")");
		}catch(Throwable t){
			fnsTake_Screen_Shot("Checklist_Count_Not_Match");
			throw new Exception("FAILED == Checklist count are NOT matched. [ChecklistCard ("+CheckList_Count_From_Card+") vs ChecklistListViewScreen ("+CheclistListViewScreen_records_Count+"), please refer the screen shot >> Checklist_Count_Not_Match"+fnsScreenShot_Date_format());
		}
		
		/*fnsGet_Element_Enabled("ChecklistListView_First_Respond_bttn");
		fnsWait_and_Click("ChecklistListView_First_Respond_bttn");
		fnsLoading_Progressing_wait(3);*/
		
/*Commented - NOM-3349 */ fncSearch_CA_Checklist_Number_Click_on_Respond_Button(CheckListNumber, CheckList_Count_From_Card);
		try{
			for(int i=0; i<=10; i++){
				if(driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("CheckList_CheckListDetailsTab_PerformeChecklist_Bttn"))).size()>0){
					fnsGet_Element_Enabled("CheckList_CheckListDetailsTab_PerformeChecklist_Bttn");
					fnsWait_and_Click("CheckList_CheckListDetailsTab_PerformeChecklist_Bttn");
					fnsLoading_Progressing_wait(2);
					break;
				}else{
					Thread.sleep(500);
				}			
			}
		}catch(Throwable t){
			Thread.sleep(500);
		}
		
		
		
		fncMake_100Percentage_Questions_Answered_by_Selecting_RadioButton();
		
		//Now the selection of radio button test case has been changed. (require to select all radio button as template has been changed.) 18.8.2017
		//fns_Select_Radio_Option_of_Question("CheckList_CheckListDetailsTab_Per_QA_Section", fnsReturn_ExcelCellValue_ByMatchingColumnName(RunningClassName, "CheckList_Question_/_Option"));
		
		
		for(int i=0; i<=10; i++){
			try{				
				fnsGet_Element_Enabled("CheckList_CheckListDetailsTab_Save_Bttn");
				fnsGet_OR_New_NSFOnline_ObjectX("CheckList_CheckListDetailsTab_Save_Bttn").click();
				fnsApps_Report_Logs("PASSED == Click done on 'Save' button.");
				fns_Verify_Success_message_coming_OR_Error_message_Coming(false,  20, "Save Success", 10);
			//	fnsLoading_Progressing_wait(2);
				break;				
			}catch(Throwable t){
				if(i==10){
					fnsTake_Screen_Shot("Success_Message_Fail");
					throw new Exception(Throwables.getStackTraceAsString(t));
				}else{
					fnsLoading_Progressing_wait(1);
				}
			}
		}
		
		
		for(int i=0; i<=10; i++){
			try{				
				fnsGet_Element_Enabled("CheckList_CheckListDetailsTab_Submit_Bttn");
				fnsGet_OR_New_NSFOnline_ObjectX("CheckList_CheckListDetailsTab_Submit_Bttn").click();
				fnsApps_Report_Logs("PASSED == Click done on 'Submit' button.");
				fns_Verify_Success_message_coming_OR_Error_message_Coming(false,  25, "Checklist Submitted Successfully", 25); 
			//	fnsLoading_Progressing_wait(2);
				break;				
			}catch(Throwable t){
				if(i==10){
					fnsTake_Screen_Shot("Success_Message_Fail");
					throw new Exception(Throwables.getStackTraceAsString(t));
				}else{
					fnsLoading_Progressing_wait(1);
				}
			}
		}
		
		
		for(int wait=0; wait<=((NewNsfOnline_Element_Max_Wait_Time)); wait++){
			if(driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("Pagination_DropDown"))).size()>0){
				fnsApps_Report_Logs("PASSED == After clicking on 'Submit' button, application successfully navigated to 'Check List' screen.");
				fnsLoading_Progressing_wait(3);
				break;
			}else{
				Thread.sleep(1000);
			}
			if(wait==((NewNsfOnline_Element_Max_Wait_Time))){
				fnsTake_Screen_Shot("CheckList_Navigation_Fail");
				throw new Exception("FAILED == After clicking on 'Submit' button, application  NOT  navigated to 'Check List' screen, [ Wait Time ("+((NewNsfOnline_Element_Max_Wait_Time))+") seconds ], please refer screen shot >> CheckList_Navigation_Fail"+fnsScreenShot_Date_format());
			}
		}
		
	/*	
//Added - NOM-3349 (Line 226-330)
		fncMenu_Ajax_Link_Click_By_PassingAjaxPath("CheckList_Ajax");
		fnsLoading_Progressing_wait(5);			
		
		for(int wait=0; wait<=((NewNsfOnline_Element_Max_Wait_Time)); wait++){
			if(driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("ActiveView_DD"))).size()>0){
				fnsApps_Report_Logs("PASSED == Successfully opened 'Check List' screen.");
				fnsLoading_Progressing_wait(2);
				break;
			}else{
				Thread.sleep(1000);
			}
			if(wait==((NewNsfOnline_Element_Max_Wait_Time))){
				fnsTake_Screen_Shot("CheckList_Screen_Open_Fail");
				throw new Exception("FAILED == 'Check List' screen is not getting open, After "+((NewNsfOnline_Element_Max_Wait_Time))+" seconds wait, please refer screen shot >> CheckList_Screen_Open_Fail"+fnsScreenShot_Date_format());
			}
		}		
		
		fnsGet_Element_Enabled("View_AdvanceSearch_Bttn");
		fnsLoading_Progressing_wait(1);
		fnsWait_and_Click("View_AdvanceSearch_Bttn");
		fnsLoading_Progressing_wait(2);
		
		fnsGet_Element_Enabled("Checklist_AdvanceSearch_ChecklistNumber");
		fnsLoading_Progressing_wait(1);
		fnsGet_Element_Enabled("Checklist_AdvanceSearch_ChecklistNumber");
		fnsWait_and_Type("Checklist_AdvanceSearch_ChecklistNumber", CheckListNumber);
		fnsLoading_Progressing_wait(2);
		
		fnsGet_Element_Enabled("Checklist_AdvanceSearch_Search_Bttn");
		fnsWait_and_Click("Checklist_AdvanceSearch_Search_Bttn");
		fnsLoading_Progressing_wait(2);
		
		fnsWait_Click_on_First_Visible_Element_if_More_than_One_Coming(CheckListNumber+" link", "//a[text()='"+CheckListNumber+"']");
		fnsLoading_Progressing_wait(2);
		
		fnsGet_Element_Enabled("CheckList_CheckListDetailsTab_PerformeChecklist_Bttn");
		fnsWait_and_Click("CheckList_CheckListDetailsTab_PerformeChecklist_Bttn");
		fnsLoading_Progressing_wait(2);
		fncMake_100Percentage_Questions_Answered_by_Selecting_RadioButton();
		
		for(int i=0; i<=5; i++){
			try{				
				fnsGet_Element_Enabled("CheckList_CheckListDetailsTab_Save_Bttn");
				fnsGet_OR_New_NSFOnline_ObjectX("CheckList_CheckListDetailsTab_Save_Bttn").click();
				fnsApps_Report_Logs("PASSED == Click done on 'Save' button.");
				fns_Verify_Success_message_coming_OR_Error_message_Coming(false,  20, "Save Success", 10);
				break;				
			}catch(Throwable t){
				if(i==10){
					fnsTake_Screen_Shot("Success_Message_Fail");
					throw new Exception(Throwables.getStackTraceAsString(t));
				}else{
					fnsLoading_Progressing_wait(1);
				}
			}
		}
		
		
		for(int i=0; i<=10; i++){
			try{				
				fnsGet_Element_Enabled("CheckList_CheckListDetailsTab_Submit_Bttn");
				fnsGet_OR_New_NSFOnline_ObjectX("CheckList_CheckListDetailsTab_Submit_Bttn").click();
				fnsApps_Report_Logs("PASSED == Click done on 'Submit' button.");
				fns_Verify_Success_message_coming_OR_Error_message_Coming(false,  25, "Checklist Submitted Successfully", 25); 
			//	fnsLoading_Progressing_wait(2);
				break;				
			}catch(Throwable t){
				if(i==10){
					fnsTake_Screen_Shot("Success_Message_Fail");
					throw new Exception(Throwables.getStackTraceAsString(t));
				}else{
					fnsLoading_Progressing_wait(1);
				}
			}
		}
		
		fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
		for(int wait=0; wait<=((NewNsfOnline_Element_Max_Wait_Time)); wait++){
			if(driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("Pagination_DropDown"))).size()>0){
				fnsApps_Report_Logs("PASSED == After clicking on 'Submit' button, application successfully navigated to 'Check List' screen.");
				fnsLoading_Progressing_wait(3);
				break;
			}else{
				Thread.sleep(1000);
			}
			if(wait==((NewNsfOnline_Element_Max_Wait_Time))){
				fnsTake_Screen_Shot("CheckList_Navigation_Fail");
				throw new Exception("FAILED == After clicking on 'Submit' button, application  NOT  navigated to 'Check List' screen, [ Wait Time ("+((NewNsfOnline_Element_Max_Wait_Time))+") seconds ], please refer screen shot >> CheckList_Navigation_Fail"+fnsScreenShot_Date_format());
			}
		}
		
		
	*/	
		
		
		
		
		
		
		
		
		
		
		
		
		fncMenu_Ajax_Link_Click_By_PassingAjaxPath("CheckList_Ajax");
		fnsLoading_Progressing_wait(5);
			
		
		for(int wait=0; wait<=((NewNsfOnline_Element_Max_Wait_Time)); wait++){
			if(driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("ActiveView_DD"))).size()>0){
				fnsApps_Report_Logs("PASSED == Successfully opened 'Check List' screen.");
				fnsLoading_Progressing_wait(2);
				break;
			}else{
				Thread.sleep(1000);
			}
			if(wait==((NewNsfOnline_Element_Max_Wait_Time))){
				fnsTake_Screen_Shot("CheckList_Screen_Open_Fail");
				throw new Exception("FAILED == 'Check List' screen is not getting open, After "+((NewNsfOnline_Element_Max_Wait_Time))+" seconds wait, please refer screen shot >> CheckList_Screen_Open_Fail"+fnsScreenShot_Date_format());
			}
		}
		
		fncVerify_View_Display_Open_and_Delete_it(2, CheckList_View_Name, "View_Delete_Link", "View_Remove_Link");
		
		fnsGet_Element_Enabled("CreateNewView_Link");
		fnsWait_and_Click("CreateNewView_Link");
		fnsLoading_Progressing_wait(2);
			
		fnsGet_Element_Enabled("CreateView_Step1_ViewName");
		fnsLoading_Progressing_wait(1);
		fnsWait_and_Type("CreateView_Step1_ViewName", CheckList_View_Name);
		
		fnsLoading_Progressing_wait(3); // added as somtimes no records found is coming into the DD
		fnsDD_Value_select_by_DDLabelName_MultiselectDD("Audit Type", fnsReturn_ExcelCellValue_ByMatchingColumnName("BS_03_CheckList", "CheckList_AuditType_DD"));
		
		fnsGet_Element_Enabled("CreateView_CreateView_Bttn");
		fnsWait_and_Click("CreateView_CreateView_Bttn");
				
		fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  60, "A new view has been added to your list", 25);
	
		fncNavigation_Verify_Application_Navigated_To_ViewScreen("'CreateView' button", "CheckList");
		
		fncSelect_AutomationView_FilterOut_CheckListNumber_and_OpenIt();
		
		fnsGet_Element_Enabled("CheckList_CorrectiveActions_TAB_UnOpened");
		fnsWait_and_Click("CheckList_CorrectiveActions_TAB_UnOpened");
		fnsLoading_Progressing_wait(2);
		
		
		fnsGet_Element_Enabled("CheckList_CorrectiveActions_PerCreatedCAR_QA_Section");
		Integer No_of_CAR_Created = driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("CheckList_CorrectiveActions_PerCreatedCAR_QA_Section"))).size();
		
		try{
			assert No_of_CAR_Created!=0 : "FAILED == <"+No_of_CAR_Created+"> CAR is coming which is not excepted. As Excepted, only two CAR should be displayed into 'Corrctive Actions' TAB, please refer screen shot >> Created_CAR_Count_Match_Fail"+fnsScreenShot_Date_format();
		}catch (Throwable t){
			fnsTake_Screen_Shot("Created_CAR_Count_Match_Fail");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		
		
		CarNo_1 = fnsReturn_SecondLabelValue_by_Passing_FirstLabelName("("+OR_New_NSFOnline.getProperty("CheckList_CorrectiveActions_PerCreatedCAR_LabelHeader_Section")+")[1]", "CAR");
		CarNo_2 = fnsReturn_SecondLabelValue_by_Passing_FirstLabelName("("+OR_New_NSFOnline.getProperty("CheckList_CorrectiveActions_PerCreatedCAR_LabelHeader_Section")+")[2]", "CAR");
		
		try{
			driver.quit();
			Thread.sleep(3000);
		}catch(Throwable t){
			//nothing to do
		}
		
		fnsBrowserLaunchAndLogin(Login_UserName, Login_Password);
		fnsSwitchAcoount_MultiAccess(this.getClass().getSimpleName(), "SwitchAccount_DD_Greeneking5237");
		fnsApps_Report_Logs("Browser is launched and Successfully login into Application for User <"+Login_UserName+">.");	
		
		
		fnsGet_Element_Enabled("CorrectiveAction_Card_Link");
		String CARcard_Count_Link_Text = fnsGet_Field_TEXT("CorrectiveAction_Card_Link").trim();
		CorrectiveAction_count_from_Card = Integer.parseInt(CARcard_Count_Link_Text);
		
		fnsWait_and_Click("CorrectiveAction_Card_Link");
		fnsLoading_Progressing_wait(2);
		
		
		for(int wait=0; wait<=((NewNsfOnline_Element_Max_Wait_Time)); wait++){
			if(driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("Pagination_DropDown"))).size()>0){
				fnsApps_Report_Logs("PASSED == After clicking on 'CAR Card' Link, application successfully navigated to 'Corrective Action(s)' screen.");
				fnsLoading_Progressing_wait(3);
				break;
			}else{
				Thread.sleep(1000);
			}
			if(wait==((NewNsfOnline_Element_Max_Wait_Time))){
				fnsTake_Screen_Shot("CA_Navigation_Fail");
				throw new Exception("FAILED == After clicking on 'CAR Card' button, application  NOT  navigated to 'Corrective Action(s)' screen, [ Wait Time ("+((NewNsfOnline_Element_Max_Wait_Time))+") seconds ], please refer screen shot >> CA_Navigation_Fail"+fnsScreenShot_Date_format());
			}
		}
		
		
		fncAfter_RespondbuttonClik_FillAllTheManadtoryField_and_UploadFile(CarNo_1, CorrectiveAction_count_from_Card, Login_UserName);
		
		
		for(int i=0; i<=10; i++){
			try{
				fnsGet_Element_Enabled("CA_RespondBttn_Save_button");
				fnsGet_OR_New_NSFOnline_ObjectX("CA_RespondBttn_Save_button").click();
				fnsApps_Report_Logs("PASSED == Click done on 'Save' button.");
				break;				
			}catch(Throwable t){
				if(i==10){
					throw new Exception(Throwables.getStackTraceAsString(t));
				}else{
					fnsLoading_Progressing_wait(1);
				}
			}
		}
		
		
		fnsGet_Element_Enabled("Model_Popup_OK_Bttn");
		fnsGet_Element_Displayed("Model_Popup_OK_Bttn");
		Thread.sleep(1000);
		fnsWait_and_Click("Model_Popup_OK_Bttn");
		fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  25, "Save Successful", 15);
		
		
		fnsGet_Element_Enabled("CA_RespondBttn_Submit_button");
		fnsWait_and_Click("CA_RespondBttn_Submit_button");
		fns_Verify_Success_message_coming_OR_Error_message_Coming(true, 120, "Corrective Action Submitted Successfully", 15);
		
		
		for(int wait=0; wait<=((NewNsfOnline_Element_Max_Wait_Time)); wait++){
			if(driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("Pagination_DropDown"))).size()>0){
				fnsApps_Report_Logs("PASSED == After clicking on 'Submit' button, application successfully navigated to 'Corrective Action(s)' screen.");
				fnsLoading_Progressing_wait(3);
				break;
			}else{
				Thread.sleep(1000);
			}
			if(wait==((NewNsfOnline_Element_Max_Wait_Time))){
				fnsTake_Screen_Shot("CA_Navigation_Fail");
				throw new Exception("FAILED == After clicking on 'Submit' button, application  NOT  navigated to 'Corrective Action(s)' screen, [ Wait Time ("+((NewNsfOnline_Element_Max_Wait_Time))+") seconds ], please refer screen shot >> CA_Navigation_Fail"+fnsScreenShot_Date_format());
			}
		}
		
		try{
			driver.quit();
			Thread.sleep(3000);
		}catch(Throwable t){
			//nothing to do
		}
		
		
		Login_UserName = fnsReturn_ExcelCellValue_ByMatchingColumnName("BS_03_CheckList", "Login_UserName_Password_to_Approve_CA").split(":")[0].trim();
		Login_Password = fnsReturn_ExcelCellValue_ByMatchingColumnName("BS_03_CheckList", "Login_UserName_Password_to_Approve_CA").split(":")[1].trim();
		Login_Application_Name = fns_Return_Login_Application_From_TestCaseSheet(New_NSFOnline_Suitexls, "BS_03_CheckList");
		fnsBrowserLaunchAndLogin(Login_UserName, Login_Password);
		fnsSwitchAcoount_MultiAccess(this.getClass().getSimpleName(), "SwitchAccount_DD");	
		
		fncSwitchUser_from_Home_and_Create_or_Open_Existing_View_into_CheckList(false);
		
		fncSelect_AutomationView_FilterOut_CheckListNumber_and_OpenIt();
		
		fnsGet_Element_Enabled("CheckList_CorrectiveActions_TAB_UnOpened");
		fnsWait_and_Click("CheckList_CorrectiveActions_TAB_UnOpened");
		fnsLoading_Progressing_wait(2);
		
		Thread.sleep(1500);
		fnsDD_value_Select_TagOPTION_DDTypeSelect("CheckList_CorrectiveActions_Filter_DD", "Pending Approval" , (NewNsfOnline_Element_Max_Wait_Time/2));
		fnsLoading_Progressing_wait(2);
		
		

		fnsGet_Element_Enabled("CheckList_CorrectiveActions_Submited_CA_CheckBox");
		fnsWait_and_Click("CheckList_CorrectiveActions_Submited_CA_CheckBox");
		fnsLoading_Progressing_wait(1);
		
		
		fnsGet_Element_Enabled("CheckList_CorrectiveActions_Submited_CA_Approve_ApproverComments");
		fnsWait_and_Type("CheckList_CorrectiveActions_Submited_CA_Approve_ApproverComments", "Automation -- Car<"+CarNo_1+"> Approved");
		
		fnsWait_Click_on_First_Visible_Element_if_More_than_One_Coming("Approve Button", OR_New_NSFOnline.getProperty("CheckList_CorrectiveActions_Submited_CA_Approve_Bttn"));
		
		fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  120, "Corrective Action(s) Approved Successfully", 25);
	}catch(Throwable t){
		isTestCasePass = false;
		fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
	
	
}












//############################################################################################################################################################
//###################################################### CLASS Function ######################################################################################

public void fncSelect_AutomationView_FilterOut_CheckListNumber_and_OpenIt() throws Throwable{
	try{

		fncMyView_SharedView_NewlyCreatedView_Display_and_Conditional_Click(2, true, CheckList_View_Name, "Yes");
		
		
		fnsGet_Element_Enabled("CreateNewView_Link");
		CheckList_Created_for_above_Criteria = 0;
		for(int i=1; i<=5; i++){
			if(CheckList_Created_for_above_Criteria == 0){
				//Commented as DEADLINE_DATE filter is not working in automation1.9.2017 using checklist number filter
				/*/fnsFilter_ApplyFilter_and_Return_Total_Filtered_Record_Count("DEADLINE DATE : ", "DEADLINE_DATE", CheckList_End_Date);
				CheckList_Created_for_above_Criteria = fnsFilter_ApplyFilter_and_Return_Total_Filtered_Record_Count("Facility Code : ", "FACILITY_CODE", AddFacilityPopup_CustomerNumber);*/
				CheckList_Created_for_above_Criteria = fnsFilter_ApplyFilter_and_Return_Total_Filtered_Record_Count("CheckList Number : ", "CHECKLIST_NO", CheckListNumber);
			}
			if(CheckList_Created_for_above_Criteria==1){
				break;
			}
			
			if( !(CheckList_Created_for_above_Criteria==1) && i==5){
				fnsTake_Screen_Shot("CheckList_Display_in_View_Fail");
				throw new Exception ("FAILED == After 5 try, CheckList created for Date<"+CheckList_StartsOn_Date+"> is not display into the View, please refer the screen shot >>CheckList_Display_in_View_Fail"+fnsScreenShot_Date_format());
			}
			Thread.sleep(2000);
		}
		
				
		String FacilityLogin_CheckListNumber_Cell_Xpath= OR_New_NSFOnline.getProperty("View_Result_Table")+"/tr[1]/td["+fnsReturn_ColumnNumber_from_TableHeaderXpath_WithoutOR(OR_New_NSFOnline.getProperty("View_Result_TableHeader"), "Checklist Number")+"]";
		String FacilityLogin_CheckListNumber = TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(FacilityLogin_CheckListNumber_Cell_Xpath).trim();
		
		try{
			assert (CheckListNumber.equalsIgnoreCase(FacilityLogin_CheckListNumber) ):"FAILED == CheckList number not matched, please refer the screen shot >>CheckList_Display_in_View_Fail"+fnsScreenShot_Date_format();
		}catch(Throwable t){
			fnsTake_Screen_Shot("CheckList_Number_Match_Fail");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		
		
		String CheckListNumber_link_Xpath = "//a[text()='"+CheckListNumber+"']";
		fnsWait_Click_on_First_Visible_Element_if_More_than_One_Coming(CheckListNumber+" - link", CheckListNumber_link_Xpath);
		//TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(CheckListNumber_link_Xpath);
		//TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(CheckListNumber_link_Xpath);
		fnsLoading_Progressing_wait(2);
		fnsLoading_Progressing_wait(1);
		
				
	}catch(NoSuchWindowException W){
		
		throw new Exception(W.getMessage());			}
	catch(Throwable e){
		
		throw new Exception(Throwables.getStackTraceAsString(e));
	}
}




public void fncSearch_CA_Checklist_Number_Click_on_Respond_Button(String Car_Or_CheckList_Number, Integer Card_Link_Count) throws Throwable{
	boolean Car_Found = false;
	boolean Resond_Button_Click_done = false;
	boolean Last_screen_appear = false;
	String CheckList_or_Car = null;
	if(Car_Or_CheckList_Number.contains("-")){
		CheckList_or_Car = "Car Number";
	}else{
		CheckList_or_Car = "Checklist Number";
	}
	try{
		fnsGet_Element_Displayed("CarCard_CA_listItemHeaderSection");
		Thread.sleep(2000);
		Integer Per_Page_Number_of_records_Display = driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("CarCard_CA_listItemHeaderSection"))).size();
		System.out.println("TEST");
		for(int PaginationPages=1; PaginationPages<=((Card_Link_Count/40)+1); PaginationPages++){
			String Pager_DD_Xpath = OR_New_NSFOnline.getProperty("Pagination_DropDown")+"/ul";
			for(int i=1; i<=5; i++){
				try{
					TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(Pager_DD_Xpath).click();
					break;
				}catch(Throwable t){
					if(i==5){
						if(driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("Pagination_DropDown_NEXT_Bttn"))).size()>0){
							driver.findElement(By.xpath(OR_New_NSFOnline.getProperty("Pagination_DropDown_NEXT_Bttn"))).click();
							fnsApps_Report_Logs("PASSED == NEXT button click done.");
							fnsLoading_Progressing_wait(2);
							Thread.sleep(1000);
							//Last_screen_appear = true;
						}
					}
					fnsLoading_Progressing_wait(1);
					System.out.println("Pager DD click fail.");
				}
			}
			Thread.sleep(500);	
			
			List<WebElement> Pager_DDobjectlists = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(Pager_DD_Xpath).findElements(By.tagName("li"));
			Integer PagerDD_List_Size = Pager_DDobjectlists.size();
			//System.out.println("PaginationPages = "+PaginationPages+"-----PagerDD_List_Size = "+PagerDD_List_Size);
			String Pager_DD_Last_Li_Xpath = Pager_DD_Xpath+"/li["+PagerDD_List_Size+"]";
			
			for(WebElement Pager_DD_Ele:Pager_DDobjectlists){
				String Pager_Label_Text = Pager_DD_Ele.getText();
				String Pager_Total_Records = Pager_Label_Text.split("of")[1].trim();
				if( (Pager_Label_Text.split("of")[0]).contains(Pager_Total_Records)){
					System.out.println("Total no of records are = "+Pager_Total_Records);
					Last_screen_appear = true;
				}
				break;
			}
			
			if(Last_screen_appear){
				break;
			}
			
			if(TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(Pager_DD_Last_Li_Xpath).getText().trim().equals("...")){
				TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(Pager_DD_Last_Li_Xpath).click();
				fnsLoading_Progressing_wait(2);
				Thread.sleep(1000);
			}else{
				try{
					TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(Pager_DD_Last_Li_Xpath).click();
					fnsLoading_Progressing_wait(3);
					Thread.sleep(1000);
				}catch(Throwable t){
					try{
						System.out.println("Last page click fail");
						for(int i=1; i<=10; i++){
							if(driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("Pagination_DropDown_NEXT_Bttn"))).size()>0){
								driver.findElement(By.xpath(OR_New_NSFOnline.getProperty("Pagination_DropDown_NEXT_Bttn"))).click();
								fnsApps_Report_Logs("PASSED == NEXT button click done.");
								fnsLoading_Progressing_wait(2);
								Thread.sleep(1000);
							}else{
								System.out.println("NEXT button is disabled.");
								//Last_screen_appear=true;
								break;
							}
						}
					}catch(Throwable k){
						break;
					}
				}
			}
		}
		
		for(int PaginationPages=1; PaginationPages<=(Card_Link_Count/10)+1; PaginationPages++){
			fnsLoading_Progressing_wait(1);
			for(int i=1; i<=Per_Page_Number_of_records_Display; i++){
				String Per_CA_listItemHeaderSection = "("+OR_New_NSFOnline.getProperty("CarCard_CA_listItemHeaderSection")+")["+i+"]";
				if(driver.findElements(By.xpath(Per_CA_listItemHeaderSection)).size()>0){
					WebElement Per_CA_listItemHeaderSection_Element = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(Per_CA_listItemHeaderSection);
					String Per_CA_listItemHeaderSection_Text = Per_CA_listItemHeaderSection_Element.getText().trim();
					System.out.println(Per_CA_listItemHeaderSection_Text);
					if(Per_CA_listItemHeaderSection_Text.toLowerCase().contains(Car_Or_CheckList_Number.toLowerCase())){
						List<WebElement> CA_listItemHeaderSection_All_Button_Attribute_Objs = Per_CA_listItemHeaderSection_Element.findElements(By.tagName("button"));
						for(WebElement CA_listItemHeaderSection_All_Button_Attribute_Element:CA_listItemHeaderSection_All_Button_Attribute_Objs){
							String Button_Name = CA_listItemHeaderSection_All_Button_Attribute_Element.getText().toLowerCase().trim();
							if(Button_Name.contains("respond")){
								Thread.sleep(1000);
								for(int l=1; l<=5; l++){
									try{
										CA_listItemHeaderSection_All_Button_Attribute_Element.click();
										System.out.println("PASSED == Respond button click done "+l+" time.");
										fnsLoading_Progressing_wait(4);
										Resond_Button_Click_done = true;
										break;
									}catch(Throwable t){
										if(l==5){
											throw new Exception("FAILED == Clicking on CAR 'RESPOND' button is getting fail, navigated from Audit Corrective action card click, please refer the screen shot >> Respond_Button_Click_Fail"+fnsScreenShot_Date_format());
										}else{
											fnsLoading_Progressing_wait(1);
										}
									}
								}
								if(Resond_Button_Click_done==true){
									break;
								}
							}
						}
						Car_Found = true;
						break;
					}
				}
			}
			
			if(Car_Found==true){
				break;
			}else{
				for(int k=1; k<=5; k++){
					try{
						if(driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("Pagination_DropDown_PREVIOUS_Bttn"))).size()>0){
							driver.findElement(By.xpath(OR_New_NSFOnline.getProperty("Pagination_DropDown_PREVIOUS_Bttn"))).click();
							fnsApps_Report_Logs("PASSED == Previous button click done.");
							fnsLoading_Progressing_wait(2);
							Thread.sleep(1000);
							break;
						}else{
							System.out.println("Previous button is disabled.");
							break;
						}
					}catch(Throwable t){
						fnsLoading_Progressing_wait(2);
						//nothing to do
					}
				}
			}
			
	}
		
	try{
			assert (Car_Found == true):"FAILED == "+CheckList_or_Car+" '"+Car_Or_CheckList_Number+"' is not found into the list, please refer the screen shot >> Respond_Button_Click_Fail"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED == "+CheckList_or_Car+" '"+Car_Or_CheckList_Number+"' is found into the list.");
		}catch(Throwable t){
		//	fnsTake_Screen_Shot(CheckList_or_Car+"_Not_Found");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
			
		try{
			assert (Resond_Button_Click_done == true ):"FAILED == 'Respond button Click fail for "+CheckList_or_Car+" <"+Car_Or_CheckList_Number+">, please refer the screen shot >> Respond_Button_Click_Fail"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED == 'Respond button Click done for "+CheckList_or_Car+" <"+Car_Or_CheckList_Number+">.");
		}catch(Throwable t){
		//	fnsTake_Screen_Shot("Respond_Button_Click_Fail");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
			
	}catch(NoSuchWindowException W){		
		throw new Exception(W.getMessage());			}
	catch(Throwable e){
		fnsTake_Screen_Shot("Respond_Button_Click_Fail");
		throw new Exception(Throwables.getStackTraceAsString(e));
	}
}




public void fncAfter_RespondbuttonClik_FillAllTheManadtoryField_and_UploadFile(String Car_Number, Integer Card_Link_Count, String Login_UserName) throws Throwable{
	try{
		if(BS_03_Runmode==true){	
			fncSearch_CA_Checklist_Number_Click_on_Respond_Button(Car_Number, Card_Link_Count);
		}
		
		fncCAR_Respond_Details_Screen_FieldsList_and_Type(Login_UserName);
				
		
		//Upload file
		String FileUploadPath = System.getProperty("user.dir") + "\\docs\\This is test pdf document.pdf";
		WebElement Browser = fnsGet_OR_New_NSFOnline_ObjectX("CA_RespondBttn_UploadFiles_browse_input");
		Browser.sendKeys(FileUploadPath);
		
		for(int wait=0; wait<=(NewNsfOnline_Element_Max_Wait_Time); wait++){
			if(driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("CA_RespondBttn_Upload_button"))).size()>0){
				fnsGet_Element_Enabled("CA_RespondBttn_Upload_button");
				fnsGet_OR_New_NSFOnline_ObjectX("CA_RespondBttn_Upload_button").click();
				fnsLoading_Progressing_wait(3);
				fnsApps_Report_Logs("PASSED == File is Successfully Upload.");
				break;
			}else if(driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("CA_RespondBttn_Upload_button2"))).size()>0){
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
		
		Thread.sleep(2000);
			
	}catch(NoSuchWindowException W){
		
		throw new Exception(W.getMessage());			}
	catch(Throwable e){
		
		throw new Exception(Throwables.getStackTraceAsString(e));
	}
}







public void fncSubmited_CA_and_ApproveIt_from_CorrectiveActionsScreen(String CAR_Labels_HeaderXpath, String CAR_Details_HeaderXpath, String Car_Number) throws Throwable{
	try{
		boolean Car_Number_Found =false;
		boolean Car_cursorPointer_Image_Click_Done =false;
		
		fnsGet_Element_Enabled(CAR_Labels_HeaderXpath);
		fnsLoading_Progressing_wait(2);
		Integer CAR_Number_Count = driver.findElements(By.xpath(OR_New_NSFOnline.getProperty(CAR_Labels_HeaderXpath))).size();
		
		for(int i=1; i<=CAR_Number_Count; i++){
			String Per_CAR_Labels_Xpath =  "("+OR_New_NSFOnline.getProperty(CAR_Labels_HeaderXpath)+")["+i+"]";
			String Per_CAR_Labels_Text =  TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(Per_CAR_Labels_Xpath).getText().toLowerCase().trim();
			if(Per_CAR_Labels_Text.contains(Car_Number.toLowerCase()) ){
				String Per_CAR_Details_Xpath = "("+OR_New_NSFOnline.getProperty(CAR_Details_HeaderXpath)+")["+i+"]";
				List<WebElement> Per_CAR_Details_images_Objs = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(Per_CAR_Details_Xpath).findElements(By.tagName("img"));
				for(WebElement Per_CAR_Details_images_Element:Per_CAR_Details_images_Objs){
					String Img_Class_Name = Per_CAR_Details_images_Element.getAttribute("class").toLowerCase().trim();
					if(Img_Class_Name.contains("cursorpointer")){
						Per_CAR_Details_images_Element.click();
						fnsLoading_Progressing_wait(2);
						Car_cursorPointer_Image_Click_Done=true;
						break;
					}
				}			
				Car_Number_Found = true;
				break;
			}	
		}
		
		assert Car_Number_Found == true : "FAILED == Car# number is not found into the Corective Actions TAB, please refer the screen shot >> Car_Not_Found"+fnsScreenShot_Date_format();
		fnsApps_Report_Logs("PASSED == Car# number <"+Car_Number+"> is found into the Corective Actions TAB.");
		
		assert Car_cursorPointer_Image_Click_Done == true : "FAILED == Unable to click on 'CursorPointerImage' Blue Icon into the Corective Actions TAB, please refer the screen shot >> Car_Not_Found"+fnsScreenShot_Date_format();
		fnsApps_Report_Logs("PASSED == Click done on 'CursorPointerImage' Blue Icon into the Corective Actions TAB.");
		
			
	}catch(NoSuchWindowException W){
		
		throw new Exception(W.getMessage());			}
	catch(Throwable e){
		
		fnsTake_Screen_Shot("Car_Not_Found");
		throw new Exception(Throwables.getStackTraceAsString(e));
	}
	
	
}





public void fncSwitchUser_from_Home_and_Create_or_Open_Existing_View_into_CheckList(boolean will_delete_View) throws Throwable{
	try{
		fncMenu_Ajax_Link_Click_By_PassingAjaxPath("CheckList_Ajax");
		fnsLoading_Progressing_wait(5);
		
		CheckList_View_Name = fnsReturn_ExcelCellValue_ByMatchingColumnName("BS_03_CheckList", "CheckList_View_Name");
		
		for(int wait=0; wait<=((NewNsfOnline_Element_Max_Wait_Time)); wait++){
			if(driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("ActiveView_DD"))).size()>0){
				fnsApps_Report_Logs("PASSED == Successfully opened 'Check List' screen.");
				fnsLoading_Progressing_wait(2);
				break;
			}else{
				Thread.sleep(1000);
			}
			if(wait==((NewNsfOnline_Element_Max_Wait_Time))){
				fnsTake_Screen_Shot("SiteScreen_Open_Fail");
				throw new Exception("FAILED == 'Check List' screen is not getting open, After "+((NewNsfOnline_Element_Max_Wait_Time))+" seconds wait, please refer screen shot >> SiteScreen_Open_Fail"+fnsScreenShot_Date_format());
			}
		}
		
		if(BS_03_Runmode==true){		
			
			if(will_delete_View){
				fncVerify_View_Display_Open_and_Delete_it(2, CheckList_View_Name, "View_Delete_Link", "View_Remove_Link");
			

			
				fnsGet_Element_Enabled("CreateNewView_Link");
				fnsWait_and_Click("CreateNewView_Link");
				fnsLoading_Progressing_wait(2);
					
				fnsGet_Element_Enabled("CreateView_Step1_ViewName");
				fnsLoading_Progressing_wait(1);
				fnsWait_and_Type("CreateView_Step1_ViewName", CheckList_View_Name);
				
				fnsLoading_Progressing_wait(3); // added as somtimes no records found is coming into the DD
				fnsDD_Value_select_by_DDLabelName_MultiselectDD("Audit Type", fnsReturn_ExcelCellValue_ByMatchingColumnName("BS_03_CheckList", "CheckList_AuditType_DD"));
				
				fnsGet_Element_Enabled("CreateView_CreateView_Bttn");
				fnsWait_and_Click("CreateView_CreateView_Bttn");
		//		fnsLoading_Progressing_wait(1);
					
				fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  NewNsfOnline_Element_Max_Wait_Time, "A new view has been added to your list", 25);
				
			
				fncNavigation_Verify_Application_Navigated_To_ViewScreen("'CreateView' button", "CheckList");
				
				/*fnsLoading_Progressing_wait(2);
				 for(int wait=0; wait<=((NewNsfOnline_Element_Max_Wait_Time)); wait++){
					if(driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("CreateNewView_Link"))).size()>0){
						fnsApps_Report_Logs("PASSED == After clicking on 'Create View' button, application successfully navigated to 'Check List' screen.");
						fnsLoading_Progressing_wait(3);
						break;
					}else{
						Thread.sleep(1000);
					}
					if(wait==((NewNsfOnline_Element_Max_Wait_Time))){
						fnsTake_Screen_Shot("SiteScreen_Navigation_Fail");
						throw new Exception("FAILED == After clicking on 'Create View' button, application  NOT  navigated to 'Check List' screen, [ Wait Time ("+((NewNsfOnline_Element_Max_Wait_Time))+") seconds ], please refer screen shot >> SiteScreen_Navigation_Fail"+fnsScreenShot_Date_format());
					}
				}*/
			}
			fncMyView_SharedView_NewlyCreatedView_Display_and_Conditional_Click(2, false, CheckList_View_Name, "Yes");
			
		}
			
	}catch(NoSuchWindowException W){
		
		throw new Exception(W.getMessage());			}
	catch(Throwable e){
		
		fnsTake_Screen_Shot("Car_Not_Found");
		throw new Exception(Throwables.getStackTraceAsString(e));
	}
	
	
}


//Select option's radio button of the given Question from Excel
public void fncMake_100Percentage_Questions_Answered_by_Selecting_RadioButton() throws Throwable{
	try{
		int Radio_Bttn_Count = 1;
		int Text_Per_Question_Count = 1;
		/*//Not Working if stale error coming
		fnsGet_Element_Enabled("CheckList_CheckListDetailsTab_PerformeChecklist_Bttn");
		fnsWait_and_Click("CheckList_CheckListDetailsTab_PerformeChecklist_Bttn");
		fnsLoading_Progressing_wait(2);
		*/
		fnsGet_Element_Enabled("CheckList_CheckListDetailsTab_All_QA_Div");
		fnsLoading_Progressing_wait(1);
		Integer No_of_Pages = 1;
		for(int l=0; l<=10; l++){
			try{		
				if(fnsGet_OR_New_NSFOnline_ObjectX("CheckList_CheckListDetailsTab_Pagination_DropDown").isDisplayed()){
					fnsGet_Element_Enabled("CheckList_CheckListDetailsTab_Pagination_DropDown");
					List<WebElement> Pages_Count_Object = fnsGet_OR_New_NSFOnline_ObjectX("CheckList_CheckListDetailsTab_Pagination_DropDown").findElements(By.tagName("option"));
					No_of_Pages = Pages_Count_Object.size();
					break;
				}
			}catch(Throwable t){
				Thread.sleep(1000);
			}
		}
		
		for(int i=1; i<=No_of_Pages; i++){
		
			String Section_Info_Header_Text = fnsGet_Field_TEXT("CheckList_CheckListDetailsTab_SectionInfoHeader").toLowerCase().trim();
			if(Section_Info_Header_Text.contains("100%")){
				fnsApps_Report_Logs("PASSED == All Questions are answered as percentage reaches to 100%.");
				break;
			}else{
				List<WebElement> Question_Sets_All_Objects = null;
				for(int s=0; s<=20; s++){
					Question_Sets_All_Objects = fnsGet_OR_New_NSFOnline_ObjectX("CheckList_CheckListDetailsTab_All_QA_Div").findElements(By.tagName("qurenderer"));
					if(Question_Sets_All_Objects.size()>1){
						fnsLoading_Progressing_wait(1);
						break;
					}else{
						fnsLoading_Progressing_wait(1);	
					}
				}
				
				fnsLoading_Progressing_wait(2);
				for(WebElement Question_Sets_All_Elements : Question_Sets_All_Objects){
					List<WebElement> Input_Sets_Per_Question_All_Objects = Question_Sets_All_Elements.findElements(By.tagName("input"));
					Integer Input_Button_in_Sequence = 1;
					for(WebElement Input_Sets_Per_Question_All_Elements : Input_Sets_Per_Question_All_Objects){
						if(Input_Button_in_Sequence==2){
							try{
								if(Input_Sets_Per_Question_All_Elements.isDisplayed()){
									Input_Sets_Per_Question_All_Elements.click();
									fnsApps_Report_Logs("Question <"+Radio_Bttn_Count+"> Radio button select done.");
									Radio_Bttn_Count++;
									break;
								}
							}catch(Throwable t){
								fnsApps_Report_Logs("Throw Question <"+Radio_Bttn_Count+"> Radio button select.");
								//nothing to do
							}
						}
						Input_Button_in_Sequence++;
					}
				}
				fnsLoading_Progressing_wait(1);
				for(WebElement Question_Sets_All_Elements : Question_Sets_All_Objects){					
					List<WebElement> textarea_Sets_Per_Question_All_Objects = Question_Sets_All_Elements.findElements(By.tagName("textarea"));
					for(WebElement textarea_Sets_Per_Question_All_Elements : textarea_Sets_Per_Question_All_Objects){
						try{
							if(textarea_Sets_Per_Question_All_Elements.isDisplayed()){
								textarea_Sets_Per_Question_All_Elements.sendKeys("Automation > Text");;
								fnsApps_Report_Logs("Question <"+Text_Per_Question_Count+"> type done.");
								Text_Per_Question_Count++;
								break;
							}
						}catch(Throwable t){
							fnsApps_Report_Logs("Throw Question <"+Text_Per_Question_Count+"> type.");
							//nothing to do
						}
					
					}
				}
				if (driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("CheckList_CheckListDetailsTab_Narration"))).size()>0 ){
					fnsLoading_Progressing_wait(2);
					int Narration_Size = driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("CheckList_CheckListDetailsTab_Narration"))).size();
					for(int size=1; size<=Narration_Size; size++){
						String Narration_Xpath = "("+OR_New_NSFOnline.getProperty("CheckList_CheckListDetailsTab_Narration")+")["+size+"]";
						driver.findElement(By.xpath(Narration_Xpath)).sendKeys("Automation Test");						
					}
				}
				
				if(i<No_of_Pages){
					try{
						if(driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("CheckList_CheckListDetailsTab_Submit_Bttn"))).size()>0){
							if(fnsGet_OR_New_NSFOnline_ObjectX("CheckList_CheckListDetailsTab_Submit_Bttn").isDisplayed()){
								fnsApps_Report_Logs("PASSED == All Questions are answered as submit button appeared on page "+i);
								break;
							}
						}
					}catch(Throwable t){
						//nothing to do
					}
					fnsWait_and_Click("CheckList_CheckListDetailsTab_Pagination_NEXT_Bttn");
					fnsLoading_Progressing_wait(2);
				}else if(i==No_of_Pages){
					if(driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("CheckList_CheckListDetailsTab_Submit_Bttn"))).size()>0){
						if(fnsGet_OR_New_NSFOnline_ObjectX("CheckList_CheckListDetailsTab_Submit_Bttn").isDisplayed()){
							fnsApps_Report_Logs("PASSED == All Questions are answered as submit button appeared on Last page "+i);
						}
					}else{
						throw new Exception("FAILED == After all Question's Answered, SUBMIT button is not coming, please refer screen shot >> Question_Option_Fail"+fnsScreenShot_Date_format());
					}
				}
				
				
				
				
			}
		}
	}catch(NoSuchWindowException W){
		
		throw new Exception(W.getMessage());
	}catch(StaleElementReferenceException e){
		fncMake_100Percentage_Questions_Answered_by_Selecting_RadioButton();
	}catch(Throwable t){
		fnsTake_Screen_Shot("Question_Option_Fail");
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
}



public void fncLogin_into_Application_and_Create_CheckList() throws Throwable{
	try{
		
		fncSwitchUser_from_Home_and_Create_or_Open_Existing_View_into_CheckList(true);
				
		fnsGet_Element_Enabled("ActiveView_DD");
		fnsWait_Click_on_First_Visible_Element_if_More_than_One_Coming("Add New CheckList", OR_New_NSFOnline.getProperty("CheckList_AddNewCheckList_Bttn"));
		
		fnsGet_Element_Enabled("CheckList_CreateNew_ChooseFacility_Bttn");
		fnsWait_and_Click("CheckList_CreateNew_ChooseFacility_Bttn");
		fnsLoading_Progressing_on_Popup_wait_for_Popup(3);
		//fnsLoading_Progressing_wait(3); //No Working as pop up open in same error class
		
		
		fnsGet_Element_Enabled("CheckList_CreateNew_AddFacility_SelectFacility_Bttn");
		Integer AddFacilityPopup_Faility_Count = fnsFilter_ApplyFilter_and_Return_Total_Filtered_Record_Count("Add Facility Popup : ", "STORE_NO", AddFacilityPopup_CustomerNumber);
		
		
		try{
			assert (AddFacilityPopup_Faility_Count!=0):"FAILED == No record is found for customer <"+AddFacilityPopup_CustomerNumber+">, please refer the screen shot >>Facility_Not_Found"+fnsScreenShot_Date_format();
		}catch(Throwable t){
			fnsTake_Screen_Shot("Facility_Not_Found");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		
		fnsGet_Element_Displayed("CheckList_CreateNew_AddFacility_First_CheckBox");
		fnsGet_Element_Enabled("CheckList_CreateNew_AddFacility_First_CheckBox");
		fnsWait_and_Click("CheckList_CreateNew_AddFacility_First_CheckBox");
		Thread.sleep(500);
		fnsWait_and_Click("CheckList_CreateNew_AddFacility_SelectFacility_Bttn");
		fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
		//fnsLoading_Progressing_wait(2); //Due to AddFacility pop up it is Not Working 
		for(int i=0; i<=NewNsfOnline_Element_Max_Wait_Time; i++){
			Integer AddedFacility_Table_Record_Count = fnsReturn_View_Results_Reords_Total_Count("Added Facility : 2nd Result Table", "Popup_View_Result_Table", 20 );	
			if(AddedFacility_Table_Record_Count>0){
				break;
			}else{
				Thread.sleep(1000);
			}
			if( (AddedFacility_Table_Record_Count==0) && (i==NewNsfOnline_Element_Max_Wait_Time) ){
				fnsTake_Screen_Shot("Facility_Not_Added");
				throw new Exception("FAILED == After clicking on 'Select Facility(s)' button Facility is not getting added into Add Facility(s) section. Waiting for <"+NewNsfOnline_Element_Max_Wait_Time+"> seconds, please refer screen shot >> Facility_Not_Added"+fnsScreenShot_Date_format());
			}	
		}
		
		fnsGet_Element_Enabled("CheckList_CreateNew_AddFacility_Save_Bttn");
		fnsWait_and_Click("CheckList_CreateNew_AddFacility_Save_Bttn");
		Thread.sleep(2000);
		for(int i=0; i<=NewNsfOnline_Element_Max_Wait_Time; i++){
			if(driver.findElement(By.xpath(OR_New_NSFOnline.getProperty("Sorry_NoInformationAvailable_Image"))).isDisplayed()){
				Thread.sleep(1000);
			}else{
				break;
			}
			if( !(driver.findElement(By.xpath(OR_New_NSFOnline.getProperty("Sorry_NoInformationAvailable_Image"))).isDisplayed()) && (i==NewNsfOnline_Element_Max_Wait_Time) ){
				fnsTake_Screen_Shot("AddFacility_Popup_Not_Close");
				throw new Exception("FAILED == After clicking on 'Save' from Add Facility(s) Popup. ADDED Facility is not getting display on 'Checklist/CreateNew' screen. Waiting for <"+NewNsfOnline_Element_Max_Wait_Time+"> seconds, please refer screen shot >> AddFacility_Popup_Not_Close"+fnsScreenShot_Date_format());
			}	
		}
		
		
		fnsDD_value_Select_TagOPTION_DDTypeSelect("CheckList_CreateNew_ChooseTamplate_DD", fnsReturn_ExcelCellValue_ByMatchingColumnName("BS_03_CheckList", "CheckList_CreateNew_ChooseTamplate_DD") , (NewNsfOnline_Element_Max_Wait_Time/2) );
		
		fnsDD_value_Select_TagOPTION_DDTypeSelect("CheckList_CreateNew_Repeats_DD", fnsReturn_ExcelCellValue_ByMatchingColumnName("BS_03_CheckList", "CheckList_CreateNew_Repeats_DD"), (NewNsfOnline_Element_Max_Wait_Time/2) );
		Thread.sleep(500);
		
		Integer LoopCount = 0; 
		boolean ValueEntered_into_CompletedWithin_Days = false;
		ArrayList<String> Dates_List = new ArrayList<String>();
		String CreateNew_StartsOn_End_Date_YEAR = fnsReturn_ExcelCellValue_ByMatchingColumnName("BS_03_CheckList", "CreateNew_StartsOn_End_Date_YEAR");
		while(true){
			if(LoopCount==30){
				fnsTake_Screen_Shot("CheckList_Not_Created");
				throw new Exception ("FAILED == After "+(LoopCount)+" attampts for different dates "+Dates_List+" , checklist is not created, please refer screen shot >>"+fnsScreenShot_Date_format());
			}
			
		//	Dates_List.add(fns_CheckList_Requried_Date_format("dd-MMM-yyyy", LoopCount) ); //Now the date year is picking from Excel, so it's not working.
			Dates_List.add(fns_CheckList_Requried_Date_format("dd-MMM-", LoopCount, 0) + CreateNew_StartsOn_End_Date_YEAR);
			
			CheckList_StartsOn_Date = Dates_List.get(LoopCount).trim();
			CheckList_End_Date = CheckList_StartsOn_Date;
			
						
			fnsGet_Element_Enabled("CheckList_CreateNew_StartsOn_Date");
			fnsWait_and_Clear("CheckList_CreateNew_StartsOn_Date");
			fnsWait_and_Type("CheckList_CreateNew_StartsOn_Date", CheckList_StartsOn_Date);
						
			fnsGet_Element_Enabled("CheckList_CreateNew_End_Date");
			fnsWait_and_Clear("CheckList_CreateNew_End_Date");
			fnsWait_and_Type("CheckList_CreateNew_End_Date", CheckList_End_Date);
			
			
			if(ValueEntered_into_CompletedWithin_Days==false){
				fnsGet_Element_Enabled("CheckList_CreateNew_CompletedWithinDays_DecreaseArrow");
				fnsWait_and_Click("CheckList_CreateNew_CompletedWithinDays_DecreaseArrow");
				fnsLoading_Progressing_on_Popup_wait_for_Popup(1);
				ValueEntered_into_CompletedWithin_Days = true;
			}
			
			
			
			/*
			if(driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("CheckList_CreateNew_CompletedWithin_Days"))).size()>0){
				fnsWait_and_Clear("CheckList_CreateNew_CompletedWithin_Days");
				fnsWait_and_Type("CheckList_CreateNew_CompletedWithin_Days", "0");
			}else{			
				for(int i=0; i<=5; i++){
					try{
						fnsWait_and_Clear("CheckList_CreateNew_CompletedWithin_Days_Input1");
						break;
					}catch(Throwable t){
						if(i==5){
							throw new Exception(Throwables.getStackTraceAsString(t));
						}else{
							Thread.sleep(250);
						}
					}
				}
				for(int i=0; i<=5; i++){
					try{
						fnsWait_and_Clear("CheckList_CreateNew_CompletedWithin_Days_Input2");
						break;
					}catch(Throwable t){
						if(i==5){
							throw new Exception(Throwables.getStackTraceAsString(t));
						}else{
							Thread.sleep(250);
						}
					}
				}
				fnsWait_and_Type("CheckList_CreateNew_CompletedWithin_Days_Input2", "0");
			}*/		
					
			
			
			Thread.sleep(500);
			for(int wait=0; wait<=(NewNsfOnline_Element_Max_Wait_Time); wait++){
				if(driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("CheckList_CreateNew_Create_n_AddToCalendar_Bttn_disabled"))).size()>0){
					Thread.sleep(1000);
				}else{
					break;
				}
				if(wait==(NewNsfOnline_Element_Max_Wait_Time)){
					fnsTake_Screen_Shot("CreateAdd_to_Calendar_button");
					throw new Exception("FAILED == 'Create & Add to Calendar' button is not getting display, After "+((NewNsfOnline_Element_Max_Wait_Time))+" seconds wait, please refer screen shot >> CreateAdd_to_Calendar_button"+fnsScreenShot_Date_format());
				}
			}
			
			Thread.sleep(2000);
			fnsWait_and_Click("CheckList_CreateNew_Create_n_AddToCalendar_Bttn");
			
			
			try{ 
				fnsGet_Element_Enabled_By_Passing_Wait_and_Screenshot_Condition(10, false, "Model_Alert_Popup_OK_Bttn");
				fns_Verify_Success_or_Error_Message_on_ModelPopup_Generation(false, 3, "Following Checklist(s) were not created as already exists", 35);
				Thread.sleep(2000);
				fnsWait_and_Click("Model_Alert_Popup_OK_Bttn");
				Thread.sleep(1000);
				isTestCasePass=true;
			}catch(Throwable t){
				try{
					// TEST
					try{	
						fnsLoading_Progressing_wait(1);
						fnsGet_Element_Enabled("ActiveView_DD");
						break;
					}catch(Throwable tt){
						throw new Exception("FAILED == Checklist is not Created as after clicking on 'Create_n_AddToCalendar' button, Neither the application displayed the validation message"+
					"<Following Checklist(s) were not created as already exists> nor the successful message <Checklist created>."+Throwables.getStackTraceAsString(tt));
					}
					
					//Staging
					/*fncVerify_Success_or_Error_Message_on_ModelPopup_Generation(true,  60, "Checklist created", 15);
					fnsGet_Element_Enabled("Model_Popup_OK_Bttn");
					fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
					fnsWait_and_Click("Model_Popup_OK_Bttn");
					Thread.sleep(2000);
					fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
					fnsGet_Element_Enabled("ActiveView_DD");
					break;*/
				}catch(Throwable tt){
					throw new Exception(Throwables.getStackTraceAsString(tt));
				}
				
			}
			LoopCount++;
		}
				
		Thread.sleep(2000);
	}catch(Throwable t){
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
	
}


public void fncCAR_Respond_Details_Screen_FieldsList_and_Type(String Login_UserName) throws Throwable{
	try{
		String iframe_Xpath = "";
		WebElement iframe_ele ;
		String iframeBody_Xpath = "";
		WebElement iframeBody_ele ;
		//List of Fields >>>  CHKLISTCAR.PA_PLAN, CHKLISTCAR.RC_ANALYSIS, CHKLISTCAR.CA_PLAN,  CaResponsiblePerson, caCompletionDate 
		//CORRACTION.INVESTIGATION, CORRACTION.IMMD_CORR_TAKEN, CORRACTION.ROOT_CAUSE_STMT, CORRACTION.VERIFICATION, CORRACTION.CORR_ACTION, CORRACTION.CA_PLAN, CORRACTION.RC_ANALYSIS, CORRACTION.CA_TAKEN, CORRACTION.PA_PLAN
		String CAR_Respond_Details_Screen_FieldsList = null;
		String COMPLETIONDATE = fns_CheckList_Requried_Date_format("dd-MMM-YYYY", 0, 0);
		// for greeneking changes has been done on 26.2.2018 email (ITESTH) sub - All checklist type of corrective actions we show different fields in car response
		switch(Login_UserName.toLowerCase().trim()){
			case ("greeneking5237") : { //BS-03
				CAR_Respond_Details_Screen_FieldsList = "CHKLISTCAR.RC_ANALYSIS, CHKLISTCAR.PA_PLAN, CaResponsiblePerson, caCompletionDate";
				break;
			}
			case ("gktestuser") : { //BS-08
				CAR_Respond_Details_Screen_FieldsList = "CHKLISTCAR.RC_ANALYSIS, CHKLISTCAR.PA_PLAN, CaResponsiblePerson, caCompletionDate";//TEST
				break;
			}
			case ("gktestuser_audit") : { //BS-34
				CAR_Respond_Details_Screen_FieldsList = "CORRACTION.RC_ANALYSIS, CORRACTION.PA_PLAN, CaResponsiblePerson, caCompletionDate";
				break;
			}
			case ("c0036244superadmin") : { //BS-08 through i-pulse
					CAR_Respond_Details_Screen_FieldsList = "CHKLISTCAR.RC_ANALYSIS, CHKLISTCAR.PA_PLAN, CaResponsiblePerson, caCompletionDate";//TEST
					break;
				}
			case ("submitter") : { //BS-10 //as per sarwagya on 27.2.2018 chat -- CA change to PA 
				CAR_Respond_Details_Screen_FieldsList = "CORRACTION.PA_PLAN, CORRACTION.RC_ANALYSIS, caCompletionDate";
				break;
			}
			case ("6n670admin") : { 
				CAR_Respond_Details_Screen_FieldsList = "CORRACTION.INVESTIGATION, CORRACTION.IMMD_CORR_TAKEN, CORRACTION.ROOT_CAUSE_STMT, CORRACTION.VERIFICATION, CORRACTION.CORR_ACTION, CORRACTION.RC_ANALYSIS, CaResponsiblePerson";
				break;
			}
			case ("submitter_checklist") : { //BS-03
				//CAR_Respond_Details_Screen_FieldsList = "CHKLISTCAR.RC_ANALYSIS, CHKLISTCAR.PA_PLAN, CaResponsiblePerson, caCompletionDate";
				CAR_Respond_Details_Screen_FieldsList = "CHKLISTCAR.RC_ANALYSIS, CHKLISTCAR.CA_PLAN, CaResponsiblePerson, caCompletionDate"; // as email by itesh on 21.121 PA change to CA
				Login_UserName = "submitter";
				break;
			}
			case ("subway") : { //BS-39
				CAR_Respond_Details_Screen_FieldsList = "CORRACTION.RC_ANALYSIS, CORRACTION.CA_PLAN, CaResponsiblePerson, caCompletionDate";
				break;
			}
			default : {
				throw new Exception("FAILED == Login user < "+Login_UserName+" > is not mentioned into the 'Switch Case' for CAR_Respond_Details_Screen_FieldsList");
			}
		}		
		
		
		for(int FieldNo=0; FieldNo<CAR_Respond_Details_Screen_FieldsList.split(",").length; FieldNo++){
			switch(CAR_Respond_Details_Screen_FieldsList.split(",")[FieldNo].trim()){
				case "CORRACTION.PA_PLAN" : {
					if(CARespond_KendoEditorDisplayed){
						iframe_Xpath = OR_New_NSFOnline.getProperty("CA_RespondBttn_PREVENTIVEACTIONPLAN_Text")+"/preceding::iframe[1]";
						for(int i=1; i<=60; i++){
							try{
								if ( driver.findElements(By.xpath(iframe_Xpath)).size()>0 ) {
									break;
								}else{
									Thread.sleep(1000);
								}
							}catch(Throwable t){
								Thread.sleep(1000);
							}
						}
						iframe_ele = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(iframe_Xpath);
						driver.switchTo().frame(iframe_ele);
						iframeBody_Xpath = "//body[@contenteditable='true']";
						TestSuiteBase_MonitorPlan.WithOut_OR_fnType(iframeBody_Xpath, "Automation - PREVENTIVE ACTION PLAN");
						driver.switchTo().defaultContent();
					}else{
						fnsGet_Element_Enabled_By_Passing_Wait_and_Screenshot_Condition(60, true, "CA_RespondBttn_PREVENTIVEACTIONPLAN_Text");
						fnsWait_and_Type("CA_RespondBttn_PREVENTIVEACTIONPLAN_Text", " : Automation - PREVENTIVE ACTION PLAN");
					}
					Thread.sleep(250);	
					break;
				}
				case "CHKLISTCAR.PA_PLAN" : {
					if(CARespond_KendoEditorDisplayed){
						iframe_Xpath = OR_New_NSFOnline.getProperty("Checklist_CA_RespondBttn_PREVENTIVEACTIONPLAN_Text")+"/preceding::iframe[1]";
						for(int i=1; i<=60; i++){
							try{
								if ( driver.findElements(By.xpath(iframe_Xpath)).size()>0 ) {
									break;
								}else{
									Thread.sleep(1000);
								}
							}catch(Throwable t){
								Thread.sleep(1000);
							}
						}
						iframe_ele = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(iframe_Xpath);
						driver.switchTo().frame(iframe_ele);
						iframeBody_Xpath = "//body[@contenteditable='true']";
						TestSuiteBase_MonitorPlan.WithOut_OR_fnType(iframeBody_Xpath, "Automation - PREVENTIVE ACTION PLAN");
						driver.switchTo().defaultContent();
					}else{
						fnsGet_Element_Enabled_By_Passing_Wait_and_Screenshot_Condition(60, true, "Checklist_CA_RespondBttn_PREVENTIVEACTIONPLAN_Text");
						fnsWait_and_Type("Checklist_CA_RespondBttn_PREVENTIVEACTIONPLAN_Text", " : Automation - PREVENTIVE ACTION PLAN");
					}
					Thread.sleep(250);	
					break;
				}
				case "CHKLISTCAR.RC_ANALYSIS" : {
					if(CARespond_KendoEditorDisplayed){
						iframe_Xpath = OR_New_NSFOnline.getProperty("Checklist_CA_RespondBttn_ROOTCAUSEANALYSIS_Text")+"/preceding::iframe[1]";
						for(int i=1; i<=60; i++){
							try{
								if ( driver.findElements(By.xpath(iframe_Xpath)).size()>0 ) {
									break;
								}else{
									Thread.sleep(1000);
								}
							}catch(Throwable t){
								Thread.sleep(1000);
							}
						}
						iframe_ele = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(iframe_Xpath);
						driver.switchTo().frame(iframe_ele);
						iframeBody_Xpath = "//body[@contenteditable='true']";
						TestSuiteBase_MonitorPlan.WithOut_OR_fnType(iframeBody_Xpath, "Automation - Root cause and Analysis");
						driver.switchTo().defaultContent();
					}else{
						fnsGet_Element_Enabled_By_Passing_Wait_and_Screenshot_Condition(60, true, "Checklist_CA_RespondBttn_ROOTCAUSEANALYSIS_Text");
						fnsWait_and_Clear("Checklist_CA_RespondBttn_ROOTCAUSEANALYSIS_Text");
						fnsWait_and_Type("Checklist_CA_RespondBttn_ROOTCAUSEANALYSIS_Text", " : Automation - Root cause and Analysis");
					}
					Thread.sleep(250);	
					break;
				}
				case "CHKLISTCAR.CA_PLAN" : {
					if(CARespond_KendoEditorDisplayed){
						iframe_Xpath = OR_New_NSFOnline.getProperty("Checklist_CA_RespondBttn_CORRECTIVEACTIONPLAN_Text")+"/preceding::iframe[1]";
						for(int i=1; i<=60; i++){
							try{
								if ( driver.findElements(By.xpath(iframe_Xpath)).size()>0 ) {
									break;
								}else{
									Thread.sleep(1000);
								}
							}catch(Throwable t){
								Thread.sleep(1000);
							}
						}
						iframe_ele = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(iframe_Xpath);
						driver.switchTo().frame(iframe_ele);
						iframeBody_Xpath = "//body[@contenteditable='true']";
						TestSuiteBase_MonitorPlan.WithOut_OR_fnType(iframeBody_Xpath, "Automation - CORRECTIVE ACTION PLAN");
						driver.switchTo().defaultContent();
					}else{
						fnsGet_Element_Enabled_By_Passing_Wait_and_Screenshot_Condition(60, true, "Checklist_CA_RespondBttn_CORRECTIVEACTIONPLAN_Text");
						fnsWait_and_Type("Checklist_CA_RespondBttn_CORRECTIVEACTIONPLAN_Text", " : Automation - CORRECTIVE ACTION PLAN");
					}
					Thread.sleep(250);	
					break;
				}
				case "CORRACTION.CA_PLAN" : {
					if(CARespond_KendoEditorDisplayed){
						iframe_Xpath = OR_New_NSFOnline.getProperty("CA_RespondBttn_CORRECTIVEACTIONPLAN_Text")+"/preceding::iframe[1]";
						for(int i=1; i<=60; i++){
							try{
								if ( driver.findElements(By.xpath(iframe_Xpath)).size()>0 ) {
									break;
								}else{
									Thread.sleep(1000);
								}
							}catch(Throwable t){
								Thread.sleep(1000);
							}
						}
						iframe_ele = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(iframe_Xpath);
						driver.switchTo().frame(iframe_ele);
						iframeBody_Xpath = "//body[@contenteditable='true']";
						TestSuiteBase_MonitorPlan.WithOut_OR_fnType(iframeBody_Xpath, "Automation - CORRECTIVE ACTION PLAN");
						driver.switchTo().defaultContent();
					}else{
						fnsGet_Element_Enabled_By_Passing_Wait_and_Screenshot_Condition(60, true, "CA_RespondBttn_CORRECTIVEACTIONPLAN_Text");
						fnsWait_and_Type("CA_RespondBttn_CORRECTIVEACTIONPLAN_Text", " : Automation - CORRECTIVE ACTION PLAN");
					}
					Thread.sleep(250);	
					break;					
				}
				case "CORRACTION.RC_ANALYSIS" : {
					if(CARespond_KendoEditorDisplayed){
						iframe_Xpath = OR_New_NSFOnline.getProperty("CA_RespondBttn_ROOTCAUSEANALYSIS_Text")+"/preceding::iframe[1]";
						for(int i=1; i<=60; i++){
							try{
								if ( driver.findElements(By.xpath(iframe_Xpath)).size()>0 ) {
									break;
								}else{
									Thread.sleep(1000);
								}
							}catch(Throwable t){
								Thread.sleep(1000);
							}
						}
						iframe_ele = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(iframe_Xpath);
						driver.switchTo().frame(iframe_ele);
						iframeBody_Xpath = "//body[@contenteditable='true']";
						TestSuiteBase_MonitorPlan.WithOut_OR_fnType(iframeBody_Xpath, "Automation - Root cause and Analysis");
						driver.switchTo().defaultContent();
					}else{
						fnsGet_Element_Enabled_By_Passing_Wait_and_Screenshot_Condition(60, true, "CA_RespondBttn_ROOTCAUSEANALYSIS_Text");
						fnsWait_and_Clear("CA_RespondBttn_ROOTCAUSEANALYSIS_Text");
						fnsWait_and_Type("CA_RespondBttn_ROOTCAUSEANALYSIS_Text", " : Automation - Root cause and Analysis");
					}
					Thread.sleep(250);					
					break;
				}				
				case "CORRACTION.CA_TAKEN" : {
					fnsGet_Element_Enabled_By_Passing_Wait_and_Screenshot_Condition(60, true, "CA_RespondBttn_CORRECTIVEACTIONTaken_Text");
					fnsWait_and_Type("CA_RespondBttn_CORRECTIVEACTIONTaken_Text", " : Automation - Prevent it from happening again");
					break;
				}
				case "CaResponsiblePerson" : {
					fnsGet_Element_Enabled_By_Passing_Wait_and_Screenshot_Condition(60, true, "CA_RespondBttn_ResponsiblePerson_Text");
					fnsWait_and_Clear("CA_RespondBttn_ResponsiblePerson_Text");
					fnsWait_and_Type("CA_RespondBttn_ResponsiblePerson_Text", Login_UserName);
					break;
				}
				case "caCompletionDate" : {
					fnsGet_Element_Enabled_By_Passing_Wait_and_Screenshot_Condition(60, true, "CA_RespondBttn_COMPLETIONDATE_Text");
					fnsWait_and_Type("CA_RespondBttn_COMPLETIONDATE_Text", COMPLETIONDATE);
					break;
				}
				case "CORRACTION.INVESTIGATION" : {
					fnsGet_Element_Enabled_By_Passing_Wait_and_Screenshot_Condition(60, true, "CA_RespondBttn_Investigation_Text");
					fnsWait_and_Clear("CA_RespondBttn_Investigation_Text");
					fnsWait_and_Type("CA_RespondBttn_Investigation_Text", " : Automation - Investigation");
					break;
				}
				case "CORRACTION.IMMD_CORR_TAKEN" : {
					fnsGet_Element_Enabled_By_Passing_Wait_and_Screenshot_Condition(60, true, "CA_RespondBttn_ImmediateCorrectionTaken_Text");
					fnsWait_and_Clear("CA_RespondBttn_ImmediateCorrectionTaken_Text");
					fnsWait_and_Type("CA_RespondBttn_ImmediateCorrectionTaken_Text", " : Automation - ImmediateCorrectionTaken");
					break;
				}
				case "CORRACTION.ROOT_CAUSE_STMT" : {
					fnsGet_Element_Enabled_By_Passing_Wait_and_Screenshot_Condition(60, true, "CA_RespondBttn_RootCauseStatement_Text");
					fnsWait_and_Clear("CA_RespondBttn_RootCauseStatement_Text");
					fnsWait_and_Type("CA_RespondBttn_RootCauseStatement_Text", " : Automation - RootCauseStatement");
					break;
				}
				case "CORRACTION.VERIFICATION" : {
					fnsGet_Element_Enabled_By_Passing_Wait_and_Screenshot_Condition(60, true, "CA_RespondBttn_Verification_Text");
					fnsWait_and_Clear("CA_RespondBttn_Verification_Text");
					fnsWait_and_Type("CA_RespondBttn_Verification_Text", " : Automation - Verification");
					break;
				}
				case "CORRACTION.CORR_ACTION" : {
					fnsGet_Element_Enabled_By_Passing_Wait_and_Screenshot_Condition(60, true, "CA_RespondBttn_CorrectiveAction_Text");
					fnsWait_and_Clear("CA_RespondBttn_CorrectiveAction_Text");
					fnsWait_and_Type("CA_RespondBttn_CorrectiveAction_Text", " : Automation - CorrectiveAction");
					break;
				}				
				default : {
					throw new Exception("FAILED == Field < "+CAR_Respond_Details_Screen_FieldsList.split(",")[FieldNo].trim()+" > is not mentioned into the 'Switch Case'");
				}			
			}		
		}			
	}catch(Throwable t){
		throw new Exception("Car Respond Details Screen : [ User > "+Login_UserName+" ].."+Throwables.getStackTraceAsString(t));
	}
}



public void fncDB_Query_to_set_Checklist_DeadLineDate_in_BackDate(String ChecklistNumber) throws Throwable{
	Connection connection = null;
	try{			
		Class.forName("oracle.jdbc.driver.OracleDriver");
		connection = fnpGetDBConnection();
		Statement stmt = connection.createStatement();

		String Update_Query ="UPDATE CW_CHECKLIST SET DEADLINE_DATE = SYSDATE-2, DO_NOT_START_BEFORE=SYSDATE-3, CREATE_DATE = SYSDATE-3, MODIFY_DATE = SYSDATE-3 WHERE SEQ = "+ChecklistNumber;
		stmt.executeUpdate(Update_Query);
		connection.commit();
		connection.close();
		fnsApps_Report_Logs("**** Update Query Executed Successfully. >> "+Update_Query); 	

	}catch(SQLException e){
		fnsApps_Report_Logs("Data base Error : "+ Throwables.getStackTraceAsString(e).trim());
		throw new Exception("Data base Error : "+ Throwables.getStackTraceAsString(e).trim());
	}finally {
		if(!(connection == null)){
			connection.close();
		}
	}
		
}

//############################################################################################################################################################
//#######################################################  Configuration Method  #############################################################################
@AfterTest
public void reportTestResult() throws Throwable {
	RunningClassName = 	this.getClass().getSimpleName();
	TestSuiteBase_MonitorPlan.fns_ReportTestResult_atClassLevel(New_NSFOnline_Suitexls,  (RunningClassName), isTestCasePass );
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