package nsf.ecap.New_NSFOnline_Suite;

import nsf.ecap.MonitorPlan_Suite.TestSuiteBase_MonitorPlan;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;



public class BS_21_Submission_Create_Submit extends TestSuiteBase_New_NSFOnline {



@Parameters({ "className" })
@BeforeTest
public void checkTestSkip(String className) throws Exception {
	if (className.isEmpty()) {
		className = this.getClass().getSimpleName();
	}
	RunningClassName = className;
	Running_Class_BS_Description = "[BS-21] Create and Submit Submission";
	IsBrowserPresentAlready =false;
	fnsCheckClassLevelTestSkip(className);
}





@Test( priority = 0)
public void Launch_Browser_and_Successfully_Login_into_the_Application() throws Throwable{
	try{ 
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
public void Create_and_Submit_Submission() throws Throwable{
	fnsApps_Report_Logs("################### Test Case ::1 Create_and_Submit_Submission ");
	try{	
		Running_Class_BS_Description = Running_Class_BS_Description+" - "+fns_Return_UK_London_Time("dd-MM-yyyy _ HH:mm", 0, 0, 0, 5, 30);
		String MFG_Popup_MFGPartNumber = "MFG001";
		String OEM_Popup_OEMPartNumber = "OEM001";		
		String Document_Des = "Document - "+fns_Return_UK_London_Time("dd-MM-yyyy _ HH:mm", 0, 0, 0, 5, 30);
		String DocumentDetails_Grid_Text = "";
		String MakeModelDetails_Grid_Text = "";
		String Submission_ID = "";
		
		String AddFacilityPopup_CustomerCode = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "AddFacilityPopup_CustomerCode");
		String CreateSubmission_Protocol_DD = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "CreateSubmission_Protocol_DD");
		String PartDetails_Description_DD = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "PartDetails_Description_DD");
		String PartDetails_Region_DD = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "PartDetails_Region_DD");
		String MakeModelDetails_Make_DD = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "MakeModelDetails_Make_DD");
		String MakeModelDetails_Model_DD = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "MakeModelDetails_Model_DD");
		String MakeModelDetails_StartYear_DD = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "MakeModelDetails_StartYear_DD");
		String MakeModelDetails_EndYear_DD = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "MakeModelDetails_EndYear_DD");
		String AddDocument_Popup_FileType_DD = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "AddDocument_Popup_FileType_DD");
		
		
		
		fncMenu_Ajax_Link_Click_By_PassingAjaxPath("Submission_Ajax");
		fnsLoading_Progressing_wait(2);
		
		fncNavigation_Verify_Application_Navigated_To_ViewScreen("'Submission' Menu Ajax", "Submission");
		
	//	fnsGet_Element_Displayed("Submission_ViewScreen_CreateSubmission_Bttn");
		fnsWait_Click_on_First_Visible_Element_if_More_than_One_Coming("Create Submission button ", OR_New_NSFOnline.getProperty("Submission_ViewScreen_CreateSubmission_Bttn"));
		fnsLoading_Progressing_wait(2);
		
		//need to rename below method
		fnsVerifyScreenNavigation_afterClickingOnElement("'Create Submission' button", "Create Submission' screen", OR_New_NSFOnline.getProperty("Submission_CreateSubmission_FacilityCode_Bttn"));
		
		fnsGet_Element_Enabled("Submission_CreateSubmission_FacilityCode_Bttn");
		fnsWait_and_Click("Submission_CreateSubmission_FacilityCode_Bttn");
		fnsLoading_Progressing_on_Popup_wait_for_Popup(3);
		
		fnsGet_Element_Enabled("Submission_CreateSubmission_AddFacility_SelectFacility_Bttn");
		Integer AddFacilityPopup_Faility_Count = fnsFilter_ApplyFilter_and_Return_Total_Filtered_Record_Count("Add Facility Popup : ", "CODE", AddFacilityPopup_CustomerCode);
//		fnsFilter_ApplyFilter_and_Return_Total_Filtered_Record_Count("Add Facility Popup : ", "CUS_TYPE", "PL"); //Not available on stage 
		
		try{
			assert (AddFacilityPopup_Faility_Count!=0):"FAILED == No record is found for customer <"+AddFacilityPopup_CustomerCode+">, please refer the screen shot >>Facility_Not_Found"+fnsScreenShot_Date_format();
		}catch(Throwable t){
			fnsTake_Screen_Shot("Facility_Not_Found");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		
		fnsGet_Element_Displayed("Submission_CreateSubmission_AddFacility_First_CheckBox");
		fnsGet_Element_Enabled("Submission_CreateSubmission_AddFacility_First_CheckBox");
		fnsWait_and_Click("Submission_CreateSubmission_AddFacility_First_CheckBox");
		Thread.sleep(500);
		fnsWait_and_Click("Submission_CreateSubmission_AddFacility_SelectFacility_Bttn");
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
		
		fnsGet_Element_Enabled("Submission_CreateSubmission_AddFacility_Save_Bttn");
		fnsWait_and_Click("Submission_CreateSubmission_AddFacility_Save_Bttn");
		Thread.sleep(2000);
		
		
		fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(false, "input","Facility Code", AddFacilityPopup_CustomerCode);
		
			
		fnsDD_value_Select_TagOPTION_DDTypeSelect("Submission_CreateSubmission_Protocol_DD", CreateSubmission_Protocol_DD, 60);
		fnsLoading_Progressing_wait(1);
		
		fnsGet_Element_Displayed("Submission_CreateSubmission_Create_Bttn");
		fnsWait_and_Click("Submission_CreateSubmission_Create_Bttn");
		fnsLoading_Progressing_wait(2);
		fnsLoading_Progressing_wait(2);
		
		fnsVerifyScreenNavigation_afterClickingOnElement("'Create' button", "Edit Submission' screen", OR_New_NSFOnline.getProperty("Submission_EditSubmission_SubmissionID"));
		
		Submission_ID = fnsGet_Field_TEXT("Submission_EditSubmission_SubmissionID");
		Submission_ID = Submission_ID.split("-")[1].trim();
		
		try{
			assert (Submission_ID.length()>5) : "FAILED == Submission ID is NOT generated, please refer the screenshots >> Submission_ID_NOT_Generated"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED == Submission ID < "+Submission_ID+" > is generated successfully.");
		}catch(Throwable t){
			fnsTake_Screen_Shot("Submission_ID_NOT_Generated");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		
		
		fnsGet_Element_Enabled("Submission_EditSubmission_AddPart_Bttn");
		fnsWait_and_Click("Submission_EditSubmission_AddPart_Bttn");
				
		fnsVerifyScreenNavigation_afterClickingOnElement("'Add Part' button", "Part Information' section", OR_New_NSFOnline.getProperty("Submission_EditSubmission_PartInformation_MFG_Add_Bttn"));
		
		
		fnsDD_value_Select_TagOPTION_DDTypeSelect("Submission_EditSubmission_PartDetails_Description_DD", PartDetails_Description_DD, 60);
		fnsDD_value_Select_TagOPTION_DDTypeSelect("Submission_EditSubmission_PartDetails_Region_DD", PartDetails_Region_DD, 60);
		
		fnsWait_and_Type("Submission_EditSubmission_PartInformation_PartsLink_input", "Link001");
		
		fnsGet_Element_Enabled("Submission_EditSubmission_PartInformation_MFG_Add_Bttn");
		fnsWait_and_Click("Submission_EditSubmission_PartInformation_MFG_Add_Bttn");
		fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
		
		fncVerifyPopupOpen_afterClickingOnElement("'MFG Add' button under Part Information section", "MFG Add Part' Popup", OR_New_NSFOnline.getProperty("Submission_EditSubmission_Popup_Save_Bttn"));
		
		fnsGet_Element_Enabled("Submission_EditSubmission_MFG_Popup_MFGPartNumber_input");
		fnsWait_and_Type("Submission_EditSubmission_MFG_Popup_MFGPartNumber_input", MFG_Popup_MFGPartNumber);
				
		fnsGet_Element_Enabled("Submission_EditSubmission_Popup_Save_Bttn");
		fnsWait_and_Click("Submission_EditSubmission_Popup_Save_Bttn");
		fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
		
		try{
			assert (fnsGet_Field_TEXT("Submission_EditSubmission_MFG_PartGrid").toLowerCase().contains(MFG_Popup_MFGPartNumber.toLowerCase())) : "FAILED == Record is not getting displaying under MFG Parts Number grid, please refer the screenshots >> MFG_Record_Not_Display"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED == successfully verified that record is displayed under MFG Parts Number grid. ");
		}catch(Throwable t){
			fnsTake_Screen_Shot("MFG_Record_Not_Display");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		
		
		
		fnsGet_Element_Enabled("Submission_EditSubmission_PartInformation_OEM_Add_Bttn");
		fnsWait_and_Click("Submission_EditSubmission_PartInformation_OEM_Add_Bttn");
		fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
		
		fncVerifyPopupOpen_afterClickingOnElement("'OEM Add' button under Part Information section", "OEM Add Part' Popup", OR_New_NSFOnline.getProperty("Submission_EditSubmission_Popup_Save_Bttn"));
		
		fnsGet_Element_Enabled("Submission_EditSubmission_OEM_Popup_OEMPartNumber_input");
		fnsWait_and_Type("Submission_EditSubmission_OEM_Popup_OEMPartNumber_input", OEM_Popup_OEMPartNumber);
				
		fnsGet_Element_Enabled("Submission_EditSubmission_Popup_Save_Bttn");
		fnsWait_and_Click("Submission_EditSubmission_Popup_Save_Bttn");
		fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
		
		try{
			assert (fnsGet_Field_TEXT("Submission_EditSubmission_OEM_PartGrid").toLowerCase().contains(OEM_Popup_OEMPartNumber.toLowerCase())) : "FAILED == Record is not getting displaying under OEM Parts Number grid, please refer the screenshots >> OEM_Record_Not_Display"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED == successfully verified that record is displayed under OEM Parts Number grid. ");
		}catch(Throwable t){
			fnsTake_Screen_Shot("OEM_Record_Not_Display");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		
		
		
		
		
		fnsGet_Element_Enabled("Submission_EditSubmission_MakeModelDetails_Add_Bttn");
		fnsWait_and_Click("Submission_EditSubmission_MakeModelDetails_Add_Bttn");
		fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
		
		fncVerifyPopupOpen_afterClickingOnElement("'MakeModelDetails Add' button under Part Information section", "MakeModelDetails Add Part' Popup", OR_New_NSFOnline.getProperty("Submission_EditSubmission_Popup_Save_Bttn"));
		
		
		fnsDD_value_Select_TagOPTION_DDTypeSelect("Submission_EditSubmission_MakeModelDetails_Make_DD", MakeModelDetails_Make_DD, 60);
		fnsLoading_Progressing_on_Popup_wait_for_Popup(1);
		fnsDD_value_Select_TagOPTION_DDTypeSelect("Submission_EditSubmission_MakeModelDetails_Model_DD", MakeModelDetails_Model_DD, 60);		
		fnsDD_value_Select_TagOPTION_DDTypeSelect("Submission_EditSubmission_MakeModelDetails_StartYear_DD", MakeModelDetails_StartYear_DD, 60);		
		fnsDD_value_Select_TagOPTION_DDTypeSelect("Submission_EditSubmission_MakeModelDetails_EndYear_DD", MakeModelDetails_EndYear_DD, 60);
		fnsLoading_Progressing_on_Popup_wait_for_Popup(1);
		fnsWait_and_Type("Submission_EditSubmission_MakeModelDetails_Description", Running_Class_BS_Description);
		
		
		fnsGet_Element_Enabled("Submission_EditSubmission_Popup_Save_Bttn");
		fnsWait_and_Click("Submission_EditSubmission_Popup_Save_Bttn");
		fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
		
		try{
			assert (fnsGet_Field_TEXT("Submission_EditSubmission_MakeModelDetails_Grid").toLowerCase().contains(Running_Class_BS_Description.toLowerCase())) : "FAILED == Record is not getting displaying under MakeModelDetails grid, please refer the screenshots >> MakeModelDetails_Record_Not_Display"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED == successfully verified that record is displayed under MakeModelDetails grid. ");
		}catch(Throwable t){
			fnsTake_Screen_Shot("MakeModelDetails_Record_Not_Display");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		
		
		
		
		
		
		
		fnsGet_Element_Enabled("Submission_EditSubmission_DocumentDetails_AddDocument_Bttn");
		fnsWait_and_Click("Submission_EditSubmission_DocumentDetails_AddDocument_Bttn");
		fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
		
		fncVerifyPopupOpen_afterClickingOnElement("'MakeModelDetails Add' button under Part Information section", "MakeModelDetails Add Part' Popup", OR_New_NSFOnline.getProperty("Submission_EditSubmission_AddDocument_Popup_SaveClose_Bttn"));
		
		try{
			String FileUploadPath = System.getProperty("user.dir") + "\\docs\\This is test pdf document.pdf";
			WebElement Browser = fnsGet_OR_New_NSFOnline_ObjectX("Submission_EditSubmission_AddDocument_Popup_Browse_input");
			Browser.sendKeys(FileUploadPath);
			Thread.sleep(2000);
			fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
			fnsApps_Report_Logs("PASSED == Document uploaded successfully.");
		}catch(Throwable t){
			fnsTake_Screen_Shot("Upload_Document_Fail");
			throw new Exception("FAILED == Document is not getting uploaad, please refer the screenshots >> Upload_Document_Fail"+fnsScreenShot_Date_format()+" Exception >> "+Throwables.getStackTraceAsString(t));
		}
		
		fnsDD_value_Select_TagOPTION_DDTypeSelect("Submission_EditSubmission_AddDocument_Popup_FileType_DD", AddDocument_Popup_FileType_DD, 60);
		fnsWait_and_Type("Submission_EditSubmission_AddDocument_Popup_Description", Document_Des);
		
		
		fnsGet_Element_Enabled("Submission_EditSubmission_AddDocument_Popup_SaveClose_Bttn");
		fnsWait_and_Click("Submission_EditSubmission_AddDocument_Popup_SaveClose_Bttn");
		fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
		
		fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  60, "Document uploaded successfully", 25);
		fnsLoading_Progressing_wait(2);
		DocumentDetails_Grid_Text =  fnsGet_Field_TEXT("Submission_EditSubmission_DocumentDetails_Grid").toLowerCase();
		
		try{
			assert ( (DocumentDetails_Grid_Text.contains(Document_Des.toLowerCase())) 
					&& (DocumentDetails_Grid_Text.contains(AddDocument_Popup_FileType_DD.toLowerCase())) 
					&& (DocumentDetails_Grid_Text.contains("This is test pdf document.pdf".toLowerCase())) ) : "FAILED == Record is not getting displaying under DocumentDetails grid, please refer the screenshots >> DocumentDetails_Record_Not_Display"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED == successfully verified that record is displayed under MakeModelDetails grid. ");
		}catch(Throwable t){
			fnsTake_Screen_Shot("DocumentDetails_Record_Not_Display");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		
		
		fnsGet_Element_Enabled("Submission_EditSubmission_Save_Bttn");
		fnsWait_and_Click("Submission_EditSubmission_Save_Bttn");
		fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  60, "Submission details updated successfully", 30);
		fnsLoading_Progressing_wait(1);
		
		
		fnsGet_Element_Enabled("Submission_EditSubmission_Submit_Bttn");
		fnsWait_and_Click("Submission_EditSubmission_Submit_Bttn");
		fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  60, "Submission details updated successfully", 25);
		fnsLoading_Progressing_wait(1);
		
		fnsVerifyScreenNavigation_afterClickingOnElement("'Submit' button", "Submission Details' Tab Opened by default", OR_New_NSFOnline.getProperty("Submission_SubmissionInfo_SubmissionDetails_Tab_OpenedByDefault"));
		
		
		
		
		
		try{
			assert (fnsGet_Field_TEXT("Submission_SubmissionInfo_MFG_PartGrid").toLowerCase().contains(MFG_Popup_MFGPartNumber.toLowerCase())) : "FAILED == After clicking on submit button, all the data are NOT coming under MFG Parts Number grid, please refer the screenshots >> MFG_Record_Not_Display"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED == Succesffully verified that after clicking on submit button, all the data are displayed under MFG Parts Number grid. ");
		}catch(Throwable t){
			fnsTake_Screen_Shot("MFG_Record_Not_Display");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		
		
		try{
			assert (fnsGet_Field_TEXT("Submission_SubmissionInfo_OEM_PartGrid").toLowerCase().contains(OEM_Popup_OEMPartNumber.toLowerCase())) : "FAILED == After clicking on submit button, all the data are NOT coming under OEM Parts Number grid, please refer the screenshots >> OEM_Record_Not_Display"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED == Succesffully verified that after clicking on submit button, all the data are displayed under OEM Parts Number grid. ");
		}catch(Throwable t){
			fnsTake_Screen_Shot("OEM_Record_Not_Display");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		
		MakeModelDetails_Grid_Text =  fnsGet_Field_TEXT("Submission_SubmissionInfo_MakeModelDetails_Grid").toLowerCase();
		try{
			assert ( (MakeModelDetails_Grid_Text.contains(Running_Class_BS_Description.toLowerCase())) 
					&& (MakeModelDetails_Grid_Text.contains(MakeModelDetails_Make_DD.toLowerCase())) 
					&& (MakeModelDetails_Grid_Text.contains(MakeModelDetails_Model_DD.toLowerCase())) 
					&& (MakeModelDetails_Grid_Text.contains(MakeModelDetails_StartYear_DD.toLowerCase())) 
					&& (MakeModelDetails_Grid_Text.contains(MakeModelDetails_EndYear_DD.toLowerCase())) ): "FAILED == After clicking on submit button, all the data are NOT coming under MakeModelDetails grid, please refer the screenshots >> MakeModelDetails_Record_Not_Display"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED == Succesffully verified that after clicking on submit button, all the data are displayed under MakeModelDetails grid. ");
		}catch(Throwable t){
			fnsTake_Screen_Shot("MakeModelDetails_Record_Not_Display");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		
		
		DocumentDetails_Grid_Text =  fnsGet_Field_TEXT("Submission_SubmissionInfo_DocumentDetails_Grid").toLowerCase();
		try{
			assert ( (DocumentDetails_Grid_Text.contains(Document_Des.toLowerCase())) 
					&& (DocumentDetails_Grid_Text.contains(AddDocument_Popup_FileType_DD.toLowerCase())) 
					&& (DocumentDetails_Grid_Text.contains("This is test pdf document.pdf".toLowerCase())) ): "FAILED == After clicking on submit button, all the data are NOT coming under DocumentDetails grid, please refer the screenshots >> DocumentDetails_Record_Not_Display"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED == Succesffully verified that after clicking on submit button, all the data are displayed under DocumentDetails grid. ");
		}catch(Throwable t){
			fnsTake_Screen_Shot("DocumentDetails_Record_Not_Display");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		
		
		
		
		
		fncMenu_Ajax_Link_Click_By_PassingAjaxPath("Submission_Ajax");
		fnsLoading_Progressing_wait(2);
		
		fncNavigation_Verify_Application_Navigated_To_ViewScreen("'Submission' Menu Ajax", "Submission");
		
		fnsGet_Element_Enabled("View_AdvanceSearch_Bttn");
		fnsLoading_Progressing_wait(1);
		fnsWait_and_Click("View_AdvanceSearch_Bttn");
		fnsLoading_Progressing_wait(3);
						
		fnsGet_Element_Enabled("Submission_AdvanceSearch_SubmissionID_Input");
		fnsWait_and_Clear("Submission_AdvanceSearch_SubmissionID_Input");
		fnsWait_and_Type("Submission_AdvanceSearch_SubmissionID_Input", Submission_ID);
		
		fnsWait_and_Click("Submission_AdvanceSearch_Search_Bttn");
		fnsLoading_Progressing_wait(5);
		fnsGet_Element_Enabled("Submission_AdvanceSearch_Result_Table");
		TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element(OR_New_NSFOnline.getProperty("Submission_AdvanceSearch_Result_Table"));
		String TextFetch=fnsGet_Field_TEXT("Submission_AdvanceSearch_Result_Table").toLowerCase().trim();
						
		try{
			assert TextFetch.contains(Submission_ID.toLowerCase().trim()):"FAILED == Submission_ID <"+Submission_ID+"> is NOT displayed into the Advance Search results Table>, Please refer Screen shot >> Submission_ID_Not_Coming"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED ==  Successfully Verify that Submission_ID <"+Submission_ID+"> is displayed into the Advance search result.");
		}catch(Throwable t){
			fnsTake_Screen_Shot("Submission_ID_Not_Coming");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
							
		fnsWait_Click_on_First_Visible_Element_if_More_than_One_Coming("'"+Submission_ID+"' link", "//a[text()='"+Submission_ID+"']");
		fnsLoading_Progressing_wait(3);
												
		fnsVerifyScreenNavigation_afterClickingOnElement("'"+Submission_ID+"' link", "By Default 'Submission' TAB in ReadOnlyMode", OR_New_NSFOnline.getProperty("Submission_SubmissionInfo_SubmissionDetails_Tab_OpenedByDefault"));
			
		try{
			assert (fnsGet_Field_TEXT("Submission_SubmissionInfo_MFG_PartGrid").toLowerCase().contains(MFG_Popup_MFGPartNumber.toLowerCase())) : "FAILED == After retrieving Created submission,all the data are NOT coming under MFG Parts Number grid, please refer the screenshots >> MFG_Record_Not_Display"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED == Succesffully verified that After retrieving Created submission, all the data are displayed under MFG Parts Number grid. ");
		}catch(Throwable t){
			fnsTake_Screen_Shot("MFG_Record_Not_Display");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		
		
		try{
			assert (fnsGet_Field_TEXT("Submission_SubmissionInfo_OEM_PartGrid").toLowerCase().contains(OEM_Popup_OEMPartNumber.toLowerCase())) : "FAILED == After retrieving Created submission,all the data are NOT coming under OEM Parts Number grid, please refer the screenshots >> OEM_Record_Not_Display"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED == Succesffully verified that After retrieving Created submission, all the data are displayed under OEM Parts Number grid. ");
		}catch(Throwable t){
			fnsTake_Screen_Shot("OEM_Record_Not_Display");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		
		MakeModelDetails_Grid_Text =  fnsGet_Field_TEXT("Submission_SubmissionInfo_MakeModelDetails_Grid").toLowerCase();
		try{
			assert ( (MakeModelDetails_Grid_Text.contains(Running_Class_BS_Description.toLowerCase())) 
					&& (MakeModelDetails_Grid_Text.contains(MakeModelDetails_Make_DD.toLowerCase())) 
					&& (MakeModelDetails_Grid_Text.contains(MakeModelDetails_Model_DD.toLowerCase())) 
					&& (MakeModelDetails_Grid_Text.contains(MakeModelDetails_StartYear_DD.toLowerCase())) 
					&& (MakeModelDetails_Grid_Text.contains(MakeModelDetails_EndYear_DD.toLowerCase())) ): "FAILED ==  After retrieving Created submission,all the data are NOT coming under MakeModelDetails grid, please refer the screenshots >> MakeModelDetails_Record_Not_Display"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED == Succesffully verified that After retrieving Created submission, all the data are displayed under MakeModelDetails grid. ");
		}catch(Throwable t){
			fnsTake_Screen_Shot("MakeModelDetails_Record_Not_Display");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		
		
		DocumentDetails_Grid_Text =  fnsGet_Field_TEXT("Submission_SubmissionInfo_DocumentDetails_Grid").toLowerCase();
		try{
			assert ( (DocumentDetails_Grid_Text.contains(Document_Des.toLowerCase())) 
					&& (DocumentDetails_Grid_Text.contains(AddDocument_Popup_FileType_DD.toLowerCase())) 
					&& (DocumentDetails_Grid_Text.contains("This is test pdf document.pdf".toLowerCase())) ): "FAILED == After retrieving Created submission, all the data are NOT coming under DocumentDetails grid, please refer the screenshots >> DocumentDetails_Record_Not_Display"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED == Succesffully verified that After retrieving Created submission, all the data are displayed under DocumentDetails grid. ");
		}catch(Throwable t){
			fnsTake_Screen_Shot("DocumentDetails_Record_Not_Display");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		
		
		
		
		
		
		
	}catch(Throwable t){
		isTestCasePass = false;
		fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
	
	
}










//############################################################################################################################################################
//###################################################### CLASS Function ######################################################################################

//Function to Only verification of screen navigation without clicking.
public void fncVerifyPopupOpen_afterClickingOnElement(String Current_Click_Element_Name, String Next_Screen_Name, String Next_Screen_Element_Xpath_WithoutOR) throws Throwable{
	try{
		for(int wait=0; wait<=((NewNsfOnline_Element_Max_Wait_Time)); wait++){
			try{
				if(driver.findElements(By.xpath(Next_Screen_Element_Xpath_WithoutOR)).size()>0){
					if(driver.findElement(By.xpath(Next_Screen_Element_Xpath_WithoutOR)).isDisplayed()){
						fnsApps_Report_Logs("PASSED == After clicking on "+Current_Click_Element_Name+" , application successfully navigated to '"+Next_Screen_Name+"' .");
						break;
					}else{
						Thread.sleep(1000);
					}
				}else{
					Thread.sleep(1000);
				}
				if(wait==((NewNsfOnline_Element_Max_Wait_Time))){
					throw new Exception();
				}
				
			}catch(Throwable t){
				if(wait==((NewNsfOnline_Element_Max_Wait_Time))){
					fnsTake_Screen_Shot(""+Next_Screen_Name+"_Navigation_Fail");
					throw new Exception("FAILED == After clicking on "+Current_Click_Element_Name+" , application  NOT  navigated to '"+Next_Screen_Name+"' , [ Wait Time ("+((NewNsfOnline_Element_Max_Wait_Time))+") seconds ], please refer screen shot >> "+Next_Screen_Name+"_Navigation_Fail"+fnsScreenShot_Date_format()+Throwables.getStackTraceAsString(t));
				}else{
					Thread.sleep(1000);
				}
			}
		}
	}catch(Throwable t){
	//	isTestCasePass = false;
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