package nsf.ecap.New_NSFOnline_Suite;


import nsf.ecap.MonitorPlan_Suite.TestSuiteBase_MonitorPlan;

import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;


public class BS_08_CheckList_SingleUsrLogin extends TestSuiteBase_New_NSFOnline {


	public BS_03_CheckList BS_03_CheckList = new BS_03_CheckList();


@Parameters({ "className" })
@BeforeTest
public void checkTestSkip(String className) throws Exception {
	if (className.isEmpty()) {
		className = this.getClass().getSimpleName();
	}
	IsBrowserPresentAlready =false;
	fnsCheckClassLevelTestSkip(className);
}





//##########################################   EXCEL Variable ###############################################################################################




@Test( priority = 0)
public void Launch_Browser_and_Successfully_Login_into_the_Application() throws Throwable{
	try{
		RunningClassName = "BS_03_CheckList";
		Running_Class_BS_Description = "[BS-08]  Create Edit and Update Check List by single user login";
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
		BS_03_CheckList.fncLogin_into_Application_and_Create_CheckList();
		
		fnsGet_Element_Enabled("View_AdvanceSearch_Bttn");
		fnsWait_and_Click("View_AdvanceSearch_Bttn");
		fnsLoading_Progressing_wait(5);
		
		fnsGet_Element_Enabled("CheckList_AdvanceSearch_FacilityCode_Text");
		fnsWait_and_Type("CheckList_AdvanceSearch_FacilityCode_Text", BS_03_CheckList.AddFacilityPopup_CustomerNumber);
		
		fnsGet_Element_Enabled("CheckList_AdvanceSearch_DoNotStartBefore_Text");
		fnsWait_and_Type("CheckList_AdvanceSearch_DoNotStartBefore_Text", BS_03_CheckList.CheckList_StartsOn_Date);
		
				
		fnsDD_Value_select_by_DDLabelName_MultiselectDD("Status", "CREATED");
		
		fnsDD_Value_select_by_DDLabelName_MultiselectDD("Audit Type", fnsReturn_ExcelCellValue_ByMatchingColumnName(RunningClassName, "CheckList_AuditType_DD"));
		
		fnsGet_Element_Enabled("CheckList_AdvanceSearch_Search_Bttn");
		fnsWait_and_Click("CheckList_AdvanceSearch_Search_Bttn");
		fnsLoading_Progressing_wait(5);
		
		
		Integer Checklist_AdvanceSearch_Result_Records_Count = fnsReturn_View_Results_Reords_Total_Count("Advance Search " , "View_Result_Table", 10);
		
		try{
			assert Checklist_AdvanceSearch_Result_Records_Count.equals(1):"FAILED == After Checklist creation, it is NOT displayed into the Advance Search result, please refer screen shot >> Checklist_NOT_display_in_AdvanceSearch"+fnsScreenShot_Date_format();
		}catch(Throwable t){
			fnsTake_Screen_Shot("Checklist_NOT_display_in_AdvanceSearch");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		
				
		
		String CheckListNumber_Cell_Xpath= OR_New_NSFOnline.getProperty("View_Result_Table")+"/tr[1]/td["+fnsReturn_ColumnNumber_from_TableHeaderXpath_WithoutOR(OR_New_NSFOnline.getProperty("View_Result_TableHeader"), "Checklist Number")+"]";
		BS_03_CheckList.CheckListNumber = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(CheckListNumber_Cell_Xpath).getText().trim();
		fnsApps_Report_Logs("PASSED == New CheckList Number <"+BS_03_CheckList.CheckListNumber+"> successfully created for Date <"+BS_03_CheckList.CheckList_StartsOn_Date+">.");
		
		
		String CheckListNumber_link_Xpath = "//a[text()='"+BS_03_CheckList.CheckListNumber+"']";
		fnsWait_Click_on_First_Visible_Element_if_More_than_One_Coming(BS_03_CheckList.CheckListNumber +" - link", CheckListNumber_link_Xpath);
		fnsLoading_Progressing_wait(2);
		fnsLoading_Progressing_wait(1);
		
		

		fnsGet_Element_Enabled("CheckList_CheckListDetailsTab_PerformeChecklist_Bttn");
		fnsWait_and_Click("CheckList_CheckListDetailsTab_PerformeChecklist_Bttn");
		fnsLoading_Progressing_wait(2);
		BS_03_CheckList.fncMake_100Percentage_Questions_Answered_by_Selecting_RadioButton();
		
		
		for(int i=0; i<=10; i++){
			try{
				fnsGet_Element_Enabled("CheckList_CheckListDetailsTab_Save_Bttn");
				fnsGet_OR_New_NSFOnline_ObjectX("CheckList_CheckListDetailsTab_Save_Bttn").click();
				fnsApps_Report_Logs("PASSED == Click done on 'Save' button.");
				fns_Verify_Success_message_coming_OR_Error_message_Coming(false,  25, "Save Success", 10);
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
		
		
		
		fnsGet_Element_Enabled("CheckList_CheckListDetailsTab_Submit_Bttn");
		fnsWait_and_Click("CheckList_CheckListDetailsTab_Submit_Bttn");
		//fnsLoading_Progressing_wait(2);
		//For the time being Commented as not working 2.6.2017
		fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  NewNsfOnline_Element_Max_Wait_Time, "Checklist Submitted Successfully", 25); 
		//fnsLoading_Progressing_wait(2);
		
		for(int wait=0; wait<=((NewNsfOnline_Element_Max_Wait_Time)); wait++){
			if(driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("CheckList_AdvanceSearch_FacilityCode_Text"))).size()>0){
				fnsApps_Report_Logs("PASSED == After clicking on 'Submit' button, application successfully navigated to 'CheckList Advance Search' screen.");
				fnsLoading_Progressing_wait(3);
				break;
			}else{
				Thread.sleep(1000);
			}
			if(wait==((NewNsfOnline_Element_Max_Wait_Time))){
				fnsTake_Screen_Shot("CheckList_Navigation_Fail");
				throw new Exception("FAILED == After clicking on 'Submit' button, application  NOT  navigated to 'CheckList Advance Search' screen, [ Wait Time ("+((NewNsfOnline_Element_Max_Wait_Time))+") seconds ], please refer screen shot >> CheckList_Navigation_Fail"+fnsScreenShot_Date_format());
			}
		}
		
		
		

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
		
		
		
		
		
		
		
		
		
		fncVerify_View_Display_Open_and_Delete_it(2, BS_03_CheckList.CheckList_View_Name, "View_Delete_Link", "View_Remove_Link");
		
		fnsGet_Element_Enabled("CreateNewView_Link");
		fnsWait_and_Click("CreateNewView_Link");
		fnsLoading_Progressing_wait(2);
			
		fnsGet_Element_Enabled("CreateView_Step1_ViewName");
		fnsLoading_Progressing_wait(1);
		fnsWait_and_Type("CreateView_Step1_ViewName", BS_03_CheckList.CheckList_View_Name);
		
		fnsDD_Value_select_by_DDLabelName_MultiselectDD("Status", "COMPLETED");
		fnsDD_Value_select_by_DDLabelName_MultiselectDD("Status", "SUBMITTED");
		
		fnsGet_Element_Enabled("CreateView_CreateView_Bttn");
		fnsWait_and_Click("CreateView_CreateView_Bttn");
	//	fnsLoading_Progressing_wait(1);
			
		fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  60, "A new view has been added to your list", 25);
		//fnsLoading_Progressing_wait(2);
	
		fncNavigation_Verify_Application_Navigated_To_ViewScreen("'CreateView' button", "CheckList");
		
		
		/*
		for(int wait=0; wait<=((NewNsfOnline_Element_Max_Wait_Time)); wait++){
			if(driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("ActiveView_DD"))).size()>0){
				fnsApps_Report_Logs("PASSED == After clicking on 'Create View' button, application successfully navigated to 'Check List' screen.");
				fnsLoading_Progressing_wait(3);
				break;
			}else{
				Thread.sleep(1000);
			}
			if(wait==((NewNsfOnline_Element_Max_Wait_Time))){
				fnsTake_Screen_Shot("CheckList_Navigation_Fail");
				throw new Exception("FAILED == After clicking on 'Create View' button, application  NOT  navigated to 'Check List' screen, [ Wait Time ("+((NewNsfOnline_Element_Max_Wait_Time))+") seconds ], please refer screen shot >> CheckList_Navigation_Fail"+fnsScreenShot_Date_format());
			}
		}*/
		
		
		
		
		
		
		
		
		
		
		
		BS_03_CheckList.fncSelect_AutomationView_FilterOut_CheckListNumber_and_OpenIt();
		
		fnsGet_Element_Enabled("CheckList_CorrectiveActions_TAB_UnOpened");
		fnsWait_and_Click("CheckList_CorrectiveActions_TAB_UnOpened");
		fnsLoading_Progressing_wait(2);
		
		
		fnsGet_Element_Enabled("CheckList_CorrectiveActions_PerCreatedCAR_QA_Section");
		Integer No_of_CAR_Created = driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("CheckList_CorrectiveActions_PerCreatedCAR_QA_Section"))).size();
		
		try{
			assert No_of_CAR_Created!=0 : "FAILED == <"+No_of_CAR_Created+"> CAR is NOT coming into 'Corrctive Actions' TAB, please refer screen shot >> Created_CAR_Not_Coming"+fnsScreenShot_Date_format();
		}catch (Throwable t){
			fnsTake_Screen_Shot("Created_CAR_Not_Coming");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		
		
		BS_03_CheckList.CarNo_1 = BS_03_CheckList.fnsReturn_SecondLabelValue_by_Passing_FirstLabelName("("+OR_New_NSFOnline.getProperty("CheckList_CorrectiveActions_PerCreatedCAR_LabelHeader_Section")+")[1]", "CAR");
		
		
		String First_CA_VIEW_Button_Xpath = "("+OR_New_NSFOnline.getProperty("CheckList_CorrectiveActions_View_Bttn")+")[1]";
		
		TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(First_CA_VIEW_Button_Xpath);
		TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(First_CA_VIEW_Button_Xpath);
		fnsLoading_Progressing_wait(5);
		
				
		for(int wait=0; wait<=((NewNsfOnline_Element_Max_Wait_Time)); wait++){
			if(driver.findElements(By.xpath("//cadescr[@id='caDescription']")).size()>0){
				String CA_Description_Header_Xpath = driver.findElement(By.xpath("//cadescr[@id='caDescription']")).getText().toLowerCase().trim();
				if(CA_Description_Header_Xpath.contains(BS_03_CheckList.CarNo_1.toLowerCase())){
					fnsApps_Report_Logs("PASSED == After Clicking on 'VIEW' button, CAR's <"+BS_03_CheckList.CarNo_1+"> Data are coming on Corrective Action Response screen");
					break;
				}
			}else{
				fnsLoading_Progressing_wait(1);
			}
			if(wait==((NewNsfOnline_Element_Max_Wait_Time))){
				fnsTake_Screen_Shot("CAR_Not_Coming");
				throw new Exception("FAILED == After Clicking on 'VIEW' button,  CAR's <"+BS_03_CheckList.CarNo_1+"> Data are not coming on Corrective Action Response screen, please refer screen shot >> CAR_Not_Coming"+fnsScreenShot_Date_format());
			}
		}
		
	
		
		
		BS_03_CheckList.fncAfter_RespondbuttonClik_FillAllTheManadtoryField_and_UploadFile("", 100, Login_UserName);
		
		
		
			
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
		
		
		for(int i=0; i<=10; i++){
			try{
				fnsGet_Element_Enabled("CA_RespondBttn_Submit_button");
				fnsGet_OR_New_NSFOnline_ObjectX("CA_RespondBttn_Submit_button").click();
				fnsApps_Report_Logs("PASSED == Click done on 'Submit' button.");
				fns_Verify_Success_message_coming_OR_Error_message_Coming(false,  120, "Corrective Action Submitted Successfully", 30);
			//	fnsLoading_Progressing_wait(3);
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
		
		
		
		/*
		for(int wait=0; wait<=((NewNsfOnline_Element_Max_Wait_Time)); wait++){
			if(driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("CheckList_CorrectiveActions_TAB_UnOpened"))).size()>0){
				fnsApps_Report_Logs("PASSED == After clicking on 'Submit' button, application successfully navigated to 'Checklist info TAB' under checklist screen.");
				fnsLoading_Progressing_wait(3);
				break;
			}else{
				Thread.sleep(1000);
			}
			if(wait==((NewNsfOnline_Element_Max_Wait_Time))){
				fnsTake_Screen_Shot("CA_Navigation_Fail");
				throw new Exception("FAILED == After clicking on 'Submit' button, application  NOT  navigated to  'Checklist info TAB' under checklist screen, [ Wait Time ("+((NewNsfOnline_Element_Max_Wait_Time))+") seconds ], please refer screen shot >> CA_Navigation_Fail"+fnsScreenShot_Date_format());
			}
		}
		*/
		
		 
		
		
		
		
		
		
		
		


		fncMenu_Ajax_Link_Click_By_PassingAjaxPath("CheckList_Ajax");
		fnsLoading_Progressing_wait(5);
			
		
		BS_03_CheckList.fncSelect_AutomationView_FilterOut_CheckListNumber_and_OpenIt();
		
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
		fnsWait_and_Type("CheckList_CorrectiveActions_Submited_CA_Approve_ApproverComments", "Automation -- Car<"+BS_03_CheckList.CarNo_1+"> Approved");
		
		fnsWait_Click_on_First_Visible_Element_if_More_than_One_Coming("Approve Button", OR_New_NSFOnline.getProperty("CheckList_CorrectiveActions_Submited_CA_Approve_Bttn"));
		
		//fncVerify_Success_or_Error_Message_on_ModelPopup_Generation(Integer.parseInt(CONFIG.getProperty("Element_MAX_WaitTime")),"Action Performed Successfully", 25);
		//Message has been changed on 19.9.2017
		/*fncVerify_Success_or_Error_Message_on_ModelPopup_Generation(true, NewNsfOnline_Element_Max_Wait_Time,"Corrective Action(s) Approved Successfully", 25);

		fnsGet_Element_Enabled("Model_Popup_OK_Bttn");
		fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
		fnsWait_and_Click("Model_Popup_OK_Bttn");
		Thread.sleep(5000);
		fnsLoading_Progressing_wait(1);	*/
		
		
		fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  120, "Corrective Action(s) Approved Successfully", 25);
				
		//isTestCasePass = true;
	}catch(Throwable t){
		RunningClassName = 	this.getClass().getSimpleName();
		isTestCasePass = false;
		fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
	
	
}












//############################################################################################################################################################
//###################################################### CLASS Function ######################################################################################

//############################################################################################################################################################
//#######################################################  Configuration Method  #############################################################################
@AfterTest
public void reportTestResult() throws Throwable {
	RunningClassName = 	this.getClass().getSimpleName();
	TestSuiteBase_MonitorPlan.fns_ReportTestResult_atClassLevel(New_NSFOnline_Suitexls,  (RunningClassName) , isTestCasePass);
}


@AfterTest
public void QuitBrowser(){
	TestSuiteBase_MonitorPlan.MoveMouseCursorToTaskBar();
	driver.quit();
}

}