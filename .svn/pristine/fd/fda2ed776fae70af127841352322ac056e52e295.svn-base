package nsf.ecap.New_NSFOnline_Suite;

import java.util.List;

import nsf.ecap.MonitorPlan_Suite.TestSuiteBase_MonitorPlan;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;



public class BS_23_Questionnaire extends TestSuiteBase_New_NSFOnline {

public String View_Name = "Automation - Questionnaire";
public String View_Name_Edited = "Automation - Questionnaire - Edited";
public String AddSupplierPopup_SupplierCode = "";
public String Questionnaire_Number = "";

@Parameters({ "className" })
@BeforeTest
public void checkTestSkip(String className) throws Exception {
	if (className.isEmpty()) {
		className = this.getClass().getSimpleName();
	}
	RunningClassName = className;
	Running_Class_BS_Description = "[BS-23] Create and Submit Questionnaire";
	IsBrowserPresentAlready =false;
	fnsCheckClassLevelTestSkip(className);
}





@Test( priority = 0)
public void Launch_Browser_and_Successfully_Login_into_the_Application() throws Throwable{
	try{ 
		AddSupplierPopup_SupplierCode = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "AddSupplierPopup_SupplierCode");
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
public void Create_Edit_Delete_View____Verify_AdvanceSearch____Create_Submit_Questionnaire() throws Throwable{
	fnsApps_Report_Logs("################### Test Case ::1 Create_Edit_and_Delete_View___Verify_AdvanceSearch__Create_and_Submit_Questionnaire ");
	try{	
		
		fncMenu_Ajax_Link_Click_By_PassingAjaxPath("Supplier_Questionnaire_Ajax");
		fnsLoading_Progressing_wait(2);
		
		fncNavigation_Verify_Application_Navigated_To_ViewScreen("'Supplier Questionnaires' Menu Ajax", "Supplier Questionnaires");
		
		String View_Exists = fncVerify_View_Display_Open_and_Delete_it(2, View_Name_Edited, "View_Delete_Link", "View_Remove_Link");
		
		if( (View_Exists.contains("View Not Exists")) ){
			fncVerify_View_Display_Open_and_Delete_it(2, View_Name, "View_Delete_Link", "View_Remove_Link");
		}
				
		fnsGet_Element_Enabled("CreateNewView_Link");
		fnsWait_and_Click("CreateNewView_Link");
		fnsLoading_Progressing_wait(1);
				
		fnsGet_Element_Enabled("CreateView_Step1_ViewName");
		fnsLoading_Progressing_wait(1);
		
		fnsWait_and_Type("CreateView_Step1_ViewName", View_Name);
					
		fnsGet_Element_Enabled("CreateView_CreateView_Bttn");
		fnsWait_and_Click("CreateView_CreateView_Bttn");
				
		fns_Verify_Success_message_coming_OR_Error_message_Coming(true, 60,  "A new view has been added to your list", 25);
		fncNavigation_Verify_Application_Navigated_To_ViewScreen("'CreateView' button", "Supplier Questionnaires");
		
		
		
		fnsGet_Element_Enabled("Edit_View_Link_2");
		fnsWait_and_Click("Edit_View_Link_2");
		fnsLoading_Progressing_wait(1);
		
		fnsGet_Element_Enabled("CreateView_Step1_ViewName");
		fnsLoading_Progressing_wait(1);
		fnsWait_and_Clear("CreateView_Step1_ViewName");
		Thread.sleep(500);
		
		fnsWait_and_Type("CreateView_Step1_ViewName", View_Name_Edited);
						
		fnsGet_Element_Enabled("CreateView_CreateView_Bttn");
		fnsWait_and_Click("CreateView_CreateView_Bttn");
	
		fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  60, "View has been updated successfully", 25);
		
		fncNavigation_Verify_Application_Navigated_To_ViewScreen("'UpdateView' button", "Supplier Questionnaires");
		
	
		
		fncVerify_View_Display_Open_and_Delete_it(2, View_Name_Edited, "View_Delete_Link", "View_Remove_Link");
		
		
		
		fnsGet_Element_Enabled("Questionnaire_AddQuestionnaire_Bttn");
		fnsLoading_Progressing_wait(1);
		fnsWait_and_Click("Questionnaire_AddQuestionnaire_Bttn");
				
		fnsVerifyScreenNavigation_afterClickingOnElement("'Add Questionnaire' button", "'Questionnaire/ Create New' screen", OR_New_NSFOnline.getProperty("Questionnaire_QuestionnaireCreateNew_ChooseSuppliers_Bttn"));
		
		fnsCalendar_Pick_TodayDate_by_LabelName_1(false, true, "Deadline Date", 0);
		
		fnsGet_Element_Enabled("Questionnaire_QuestionnaireCreateNew_ChooseSuppliers_Bttn");
		fnsWait_and_Click("Questionnaire_QuestionnaireCreateNew_ChooseSuppliers_Bttn");
		fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
		
		fnsGet_Element_Enabled("Questionnaire_QuestionnaireCreateNew_AddSupplier_Bttn");		
		Integer AddFacilityPopup_Faility_Count = fnsFilter_ApplyFilter_and_Return_Total_Filtered_Record_Count("Add Supplier(s) popup", "CODE", AddSupplierPopup_SupplierCode);

		
		try{
			assert (AddFacilityPopup_Faility_Count!=0):"FAILED == No record is found for Supplier <"+AddSupplierPopup_SupplierCode+">, please refer the screen shot >>Supplier_Not_Found"+fnsScreenShot_Date_format();
		}catch(Throwable t){
			fnsTake_Screen_Shot("Supplier_Not_Found");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		
		fnsGet_Element_Displayed("Questionnaire_QuestionnaireCreateNew_AddSupplier_First_CheckBox");
		fnsGet_Element_Enabled("Questionnaire_QuestionnaireCreateNew_AddSupplier_First_CheckBox");
		fnsWait_and_Click("Questionnaire_QuestionnaireCreateNew_AddSupplier_First_CheckBox");
		Thread.sleep(500);
		fnsWait_and_Click("Questionnaire_QuestionnaireCreateNew_AddSupplier_Bttn");
		fnsLoading_Progressing_on_Popup_wait_for_Popup(1);
		
		
		//fnsLoading_Progressing_wait(2); //Due to AddFacility pop up it is Not Working 
		for(int i=0; i<=NewNsfOnline_Element_Max_Wait_Time; i++){
			Integer AddedFacility_Table_Record_Count = fnsReturn_View_Results_Reords_Total_Count("Added Supplier : 2nd Result Table", "Popup_View_Result_Table", 20 );	
			if(AddedFacility_Table_Record_Count>0){
				break;
			}else{
				Thread.sleep(1000);
			}
			if( (AddedFacility_Table_Record_Count==0) && (i==NewNsfOnline_Element_Max_Wait_Time) ){
				fnsTake_Screen_Shot("Supplier_Not_Found");
				throw new Exception("FAILED == After clicking on 'AddSupplier' button, Supplier is not getting added into Add Supplier(s) section. Waiting for <"+NewNsfOnline_Element_Max_Wait_Time+"> seconds, please refer screen shot >> Supplier_Not_Found"+fnsScreenShot_Date_format());
			}	
		}
		
		fnsGet_Element_Enabled("Questionnaire_QuestionnaireCreateNew_AddSupplier_Save_Bttn");
		fnsWait_and_Click("Questionnaire_QuestionnaireCreateNew_AddSupplier_Save_Bttn");
		Thread.sleep(2000);
		
		
		fnsGet_Element_Enabled("Questionnaire_QuestionnaireCreateNew_Grid");
		String TextFetch=fnsGet_Field_TEXT("Questionnaire_QuestionnaireCreateNew_Grid").toLowerCase().trim();
						
		try{
			assert TextFetch.contains(AddSupplierPopup_SupplierCode.toLowerCase().trim()):"FAILED ==  Added supplier <"+AddSupplierPopup_SupplierCode+"> is NOT displayed into the Grid under 'Questionnaire/ Create New' screen, Please refer Screen shot >> Supplier_Not_Coming"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED ==  Successfully Verify that  Added supplier <"+AddSupplierPopup_SupplierCode+"> is displayed into the Grid under 'Questionnaire/ Create New' screen.");
		}catch(Throwable t){
			fnsTake_Screen_Shot("Supplier_Not_Coming");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		Thread.sleep(5000);
		
		
		
		
		
		
		
		fnsGet_Element_Enabled("Questionnaire_QuestionnaireCreateNew_ChooseQuestionnaireTemplate_DD");
		List<WebElement> DDobjectlists=fnsGet_OR_New_NSFOnline_ObjectX("Questionnaire_QuestionnaireCreateNew_ChooseQuestionnaireTemplate_DD").findElements(By.tagName("option"));
		Integer LoopCounter =  DDobjectlists.size();
		boolean QuestionnaireCreated_Done = false;
		String Selected_DD_Value = "";
		for(int i=1; i<LoopCounter; i++){
			try{
				Select ChooseQuestionnaireTemplate = new Select(fnsGet_OR_New_NSFOnline_ObjectX("Questionnaire_QuestionnaireCreateNew_ChooseQuestionnaireTemplate_DD"));
				ChooseQuestionnaireTemplate.selectByIndex(i);
				WebElement Selected_DD_Value_Ele = DDobjectlists.get(i);
				Selected_DD_Value = Selected_DD_Value_Ele.getText().trim();
				System.out.println("Selected value = "+Selected_DD_Value);
			}catch(Throwable t) {
				isTestCasePass = false;
				fnsTake_Screen_Shot("DdValueSelect_Fail");
				throw new Exception("FAILED == ChooseQuestionnaireTemplate drop down's value no <"+i+"> is not getting selected, please refer the screen shot >> DdValueSelect_Fail"+fnsScreenShot_Date_format());
			}
			
			fnsGet_Element_Displayed("Questionnaire_QuestionnaireCreateNew_CreateQuestionnaire_Bttn");
			fnsWait_and_Click("Questionnaire_QuestionnaireCreateNew_CreateQuestionnaire_Bttn");
							
			String CreateQuestionnaire_Validation_Message = fns_Verify_Success_or_Error_Message_on_ModelPopup_Generation(true, 60, "Questionnaire", 10);
			
			if(CreateQuestionnaire_Validation_Message.toLowerCase().contains("duplicate questionnaire")){
				//fns_Verify_Success_or_Error_Message_on_ModelPopup_Generation(true, 60, "Questionnaire", 15);
				Thread.sleep(2000);	
				fnsWait_and_Click("Questionnaire_QuestionnaireCreateNew_ValidationPopup_OK_Bttn");
				Thread.sleep(1000);			
			}else if (CreateQuestionnaire_Validation_Message.toLowerCase().contains("questionnaire created")){
				fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  60, "Questionnaire created", 15);
				QuestionnaireCreated_Done = true;
				break;
			}else{
				fnsTake_Screen_Shot("Unexpected_Message_");
				throw new Exception ("FAILED == Some other unexpected validation message is coming ["+CreateQuestionnaire_Validation_Message+"], please refer the screenshot >> Unexpected_Message_"+fnsScreenShot_Date_format());
			}
			
			if((QuestionnaireCreated_Done==false) && (i==LoopCounter)){
				fnsTake_Screen_Shot("Questionnaire_Create_Fail_");
				throw new Exception ("FAILED == Unable to create Questionnaire as getting 'Duplicate Questionnaire' message for all data [TodayDate + All Dropdown Values + customer-"+AddSupplierPopup_SupplierCode+"] , please refer the screenshot >> Questionnaire_Create_Fail_"+fnsScreenShot_Date_format());
			}
		}
		
		
		
		
		fnsGet_Element_Enabled("View_AdvanceSearch_Bttn");
		fnsWait_and_Click("View_AdvanceSearch_Bttn");
		fnsLoading_Progressing_wait(2);
						
		fnsWait_Click_or_Type_By_LabelName("AdvanceSearch_Searchfields_Top_Div", "Supplier Code", AddSupplierPopup_SupplierCode);
		
		fnsDD_Value_select_by_DDLabelName_MultiselectDD("Status", "CREATED");
		
		fnsCalendar_Pick_TodayDate_by_LabelName_1(false, true, "From DeadLine Date", 0);
		fnsCalendar_Pick_TodayDate_by_LabelName_1(false, true, "To DeadLine Date", 0);
		
		fnsWait_and_Click("Questionnaire_AdvanceSearch_Search_Bttn");
		fnsLoading_Progressing_wait(2);
		fnsGet_Element_Enabled("Questionnaire_AdvanceSearch_Result_Table");
		TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element(OR_New_NSFOnline.getProperty("Questionnaire_AdvanceSearch_Result_Table"));
		
		fnsFilter_ApplyFilter_and_Return_Total_Filtered_Record_Count("AUDIT TYPE : ", "AUDIT_TYPE", Selected_DD_Value);
		
		
		TextFetch=fnsGet_Field_TEXT("Questionnaire_AdvanceSearch_Result_Table").toLowerCase().trim();
						
		try{
			assert TextFetch.contains(AddSupplierPopup_SupplierCode.toLowerCase().trim()):"FAILED ==  <"+AddSupplierPopup_SupplierCode+"> NOT displayed into the Advance Search results Table>, Please refer Screen shot >> Supplier_Not_Coming"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED ==  Successfully Verify that  <"+AddSupplierPopup_SupplierCode+"> displayed into the Advance search result.");
		}catch(Throwable t){
			fnsTake_Screen_Shot("Supplier_Not_Coming");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		
		
		Questionnaire_Number = fnsw_Click_on_Table_Link_by_Passing_Cell_Xpath__and__Return_CellValue(OR_New_NSFOnline.getProperty("Questionnaire_AdvanceSearch_FirstRow_firstCell"));
		
		fnsVerifyScreenNavigation_afterClickingOnElement("'"+Questionnaire_Number+"' link from advance search", "'Questionnaire Details' Tab Opened By Default", OR_New_NSFOnline.getProperty("Questionnaire_QuestionnaireDetails_Tab_ByDefault_Opened"));
		
		
		try{
			int Text_Per_Question_Count = 1;
			
			fnsGet_Element_Enabled("Questionnaire_QuestionnaireDetailsTab_Header_PageCount");
			fnsLoading_Progressing_wait(1);
			String Section_Info_PageCount = fnsGet_Field_TEXT("Questionnaire_QuestionnaireDetailsTab_Header_PageCount").toLowerCase().trim();
			Section_Info_PageCount = Section_Info_PageCount.split("/")[1];
			Integer No_of_Pages = Integer.parseInt(Section_Info_PageCount);
			
			for(int i=1; i<=No_of_Pages; i++){
				List<WebElement> textarea_Sets_Per_Question_All_Objects = fnsGet_OR_New_NSFOnline_ObjectX("Questionnaire_QuestionnaireDetailsTab_QuestionMain_Div").findElements(By.tagName("textarea"));
				for(WebElement textarea_Sets_Per_Question_All_Elements : textarea_Sets_Per_Question_All_Objects){
					try{
						if(textarea_Sets_Per_Question_All_Elements.isDisplayed()){
							textarea_Sets_Per_Question_All_Elements.sendKeys("Automation > Text");;
							fnsApps_Report_Logs("Question <"+Text_Per_Question_Count+"> type done.");
							Text_Per_Question_Count++;
						}
					}catch(Throwable t){
						fnsApps_Report_Logs("Throw Question <"+Text_Per_Question_Count+"> type.");
						//nothing to do
					}				
				}
			
				List<WebElement> input_Sets_Per_Question_All_Objects = fnsGet_OR_New_NSFOnline_ObjectX("Questionnaire_QuestionnaireDetailsTab_QuestionMain_Div").findElements(By.tagName("input"));
				if(input_Sets_Per_Question_All_Objects.size()>0){
					for(WebElement input_Sets_Per_Question_All_Elements : input_Sets_Per_Question_All_Objects){
						try{
							if(input_Sets_Per_Question_All_Elements.isDisplayed()){
								input_Sets_Per_Question_All_Elements.sendKeys("123456");;
								fnsApps_Report_Logs("Question <"+Text_Per_Question_Count+"> type done.");
								Text_Per_Question_Count++;
							}
						}catch(Throwable t){
							fnsApps_Report_Logs("Throw Question <"+Text_Per_Question_Count+"> type.");
							//nothing to do
						}				
					}	
				}
				if(i<No_of_Pages){
					if(driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("Questionnaire_QuestionnaireDetailsTab_Submit_bttn"))).size()>0){
						if(fnsGet_OR_New_NSFOnline_ObjectX("Questionnaire_QuestionnaireDetailsTab_Submit_bttn").isDisplayed()){
							throw new Exception("FAILED == 'Submit' button is appeared before answered all questions, please refer the screenshot >> Question_Answered_Fail"+fnsScreenShot_Date_format());
						}
					}
					fnsWait_and_Click("Questionnaire_QuestionnaireDetailsTab_SaveNdNext_bttn");
					fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  20, "Save Success", 10);
				}else if(i==No_of_Pages){
					if(driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("Questionnaire_QuestionnaireDetailsTab_Submit_bttn"))).size()>0){
						if(fnsGet_OR_New_NSFOnline_ObjectX("Questionnaire_QuestionnaireDetailsTab_Submit_bttn").isDisplayed()){
							fnsApps_Report_Logs("PASSED == All Questions are answered as submit button appeared on Last screen");
						}
					}else{
						throw new Exception("FAILED == After all Question's Answered, SUBMIT button is not coming, please refer screen shot >> Question_Answered_Fail"+fnsScreenShot_Date_format());
					}
				}
			}
		}catch(NoSuchWindowException W){
			
			throw new Exception(W.getMessage());
		}catch(Throwable t){
			fnsTake_Screen_Shot("Question_Answered_Fail");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		
		
		fnsGet_Element_Enabled("Questionnaire_QuestionnaireDetailsTab_Submit_bttn");
		fnsWait_and_Click("Questionnaire_QuestionnaireDetailsTab_Submit_bttn");
		fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  60, "Questionnaire Submitted Successfully", 35);
		
		
		
		fncMenu_Ajax_Link_Click_By_PassingAjaxPath("Supplier_Questionnaire_Ajax");
		fnsLoading_Progressing_wait(2);		
		
		fnsGet_Element_Enabled("View_AdvanceSearch_Bttn");
		fnsWait_and_Click("View_AdvanceSearch_Bttn");
		fnsLoading_Progressing_wait(2);
						
		fnsWait_Click_or_Type_By_LabelName("AdvanceSearch_Searchfields_Top_Div", "Supplier Code", AddSupplierPopup_SupplierCode);
		
		fnsDD_Value_select_by_DDLabelName_MultiselectDD("Status", "SUBMITTED");
		
		fnsCalendar_Pick_TodayDate_by_LabelName_1(false, true, "From DeadLine Date", 0);
		fnsCalendar_Pick_TodayDate_by_LabelName_1(false, true, "To DeadLine Date", 0);
		
		fnsWait_and_Click("Questionnaire_AdvanceSearch_Search_Bttn");
		fnsLoading_Progressing_wait(2);
		fnsGet_Element_Enabled("Questionnaire_AdvanceSearch_Result_Table");
		TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element(OR_New_NSFOnline.getProperty("Questionnaire_AdvanceSearch_Result_Table"));
		
		fnsFilter_ApplyFilter_and_Return_Total_Filtered_Record_Count("AUDIT TYPE : ", "AUDIT_TYPE", Selected_DD_Value);
		
		TextFetch=fnsGet_Field_TEXT("Questionnaire_AdvanceSearch_Result_Table").toLowerCase().trim();
						
		try{
			assert TextFetch.contains(AddSupplierPopup_SupplierCode.toLowerCase().trim()):"FAILED ==  <"+AddSupplierPopup_SupplierCode+"> NOT displayed into the Advance Search results Table, Please refer Screen shot >> Supplier_Not_Coming"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED ==  Successfully Verify that  <"+AddSupplierPopup_SupplierCode+"> displayed into the Advance search result.");
		}catch(Throwable t){
			fnsTake_Screen_Shot("Supplier_Not_Coming");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		
		
		String Submitted_Questionnaire_Number = fnsGet_Field_TEXT("Questionnaire_AdvanceSearch_FirstRow_firstCell");
		
		try{
			assert Questionnaire_Number.equals(Submitted_Questionnaire_Number):"FAILED ==  Questionnair Number <"+Submitted_Questionnaire_Number+"> after submit and Questionnair Number <"+Questionnaire_Number+"> before submit are not matched, Please refer Screen shot >> Supplier_Not_Coming"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED ==  Successfully Verify that  Questionnair Number <"+Submitted_Questionnaire_Number+"> after submit and Questionnair Number <"+Questionnaire_Number+"> before submit are matched.");
		}catch(Throwable t){
			fnsTake_Screen_Shot("Questionnaire_Number_Not_Match");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		
	
		
	}catch(Throwable t){
		isTestCasePass = false;
		fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
	
	
}










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
	try{
		driver.quit();
		Thread.sleep(3000);
	}catch(Throwable t){
		//nothing to do
	}
}

}