package nsf.ecap.Automotive_Suite;

import java.util.List;

import nsf.ecap.IssueMgt_Suite.TestSuiteBase_IM;
import nsf.ecap.MonitorPlan_Suite.TestSuiteBase_MonitorPlan;








import nsf.ecap.TraQtion_Suite.TestSuiteBase_TraQtion;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;

public class Create_Submission extends TestSuiteBase_Automotive {



@Parameters({ "className" })
@BeforeTest
public void checkTestSkip(String className) throws Exception {
	
	if (className.isEmpty()) {
		className = this.getClass().getSimpleName();
	}
	IsBrowserPresentAlready =false;
	fnsCheckClassLevelTestSkip(className);
}

//################################################ Class Variables Starts #####################################################################
public String Row_Xpath = null;
public String Comments_Free_Text = "AutomationTest >> Automotive_CreateSubmission[BS-01], Date/Time=" ;
public String TextFetched = null;
public String Submission_ID = null;
public Integer Items_Recevied_Row_No = 0;
public String DetailsTab_Status_DD = null;
public String DetailsTab_CertificationStatus_DD = null;




@Test( priority = 0)
public void Launch_Browser_and_Successfully_Login_into_the_Application() throws Throwable{
	fnsApps_Report_Logs("################################## [BS - 01]  Create Submission");
	DetailsTab_Status_DD = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Status_DD");
	DetailsTab_CertificationStatus_DD = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "CertificationStatus_DD");
	if (!IsBrowserPresentAlready) {
		IsBrowserPresentAlready = fnsLaunchBrowserAndLogin();
		fnsApps_Report_Logs("Browser is launched and Successfully login into Application");	
	}
	fnsApps_Report_Logs("=========================================================================================================================================");
}





@Test( priority = 1, dependsOnMethods={"Launch_Browser_and_Successfully_Login_into_the_Application"}, description="")
public void Create_new_Submission() throws Throwable{
	fnsApps_Report_Logs("################### Test Case ::::::1 Create_new_Submission ");
		
	fncMenu_Ajax_Link_Click_By_PassingAjaxPath("CreateSubmission_Ajax_Link");
	fnsGet_Element_Enabled("IPulse_Footer");
	TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
	
	
	fnsGet_Element_Enabled("CreateSubmission_FacilityName_LK_Bttn");
	fnsWait_and_Click("CreateSubmission_FacilityName_LK_Bttn");
	TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
	fnsGet_Element_Enabled("LookUp_First_Input_Field");
	fnsWait_and_Type("LookUp_First_Input_Field", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Facility_Name"));
	TestSuiteBase_MonitorPlan.fnsLookup_RadioBttn_Select();
	Thread.sleep(1500);
		
	
	fnsGet_Element_Enabled("CreateSubmission_SubmissionType_DD_Click");
	fnsDD_value_Select_By_MatchingText_DownKeyPress("CreateSubmission_SubmissionType_DD_Click", "CreateSubmission_SubmissionType_DD_Value", "li", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "SubmissionType_DD"));
	Thread.sleep(2000);

	fnsGet_Element_Enabled("CreateSubmission_Protocol_DD_Click");
	fnsDD_value_Select_By_MatchingText_DownKeyPress("CreateSubmission_Protocol_DD_Click", "CreateSubmission_Protocol_DD_Value", "li", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Protocol_DD"));
	
	fnsGet_Element_Enabled("CreateSubmission_Notes_TextArea");
	fnsWait_and_Type("CreateSubmission_Notes_TextArea", Comments_Free_Text);
	
	
	fnsGet_Element_Enabled("CreateSubmission_Create_Bttn");
	fnsWait_and_Click("CreateSubmission_Create_Bttn");
	TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(3);
	fnsGet_Element_Enabled("IPulse_Footer");
	TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
	
	
	for(int i=0; i<=Long.parseLong(CONFIG.getProperty("ElementWaitTime"));i++){
		if(driver.findElements(By.xpath(OR_Automotive.getProperty("EditSubmission_DetailsTab_SubmissionID"))).size()>0){
			Submission_ID = fnsGet_Field_TEXT("EditSubmission_DetailsTab_SubmissionID");
			fnsApps_Report_Logs("PASSED == Submission ID created successfully, Submission_ID  =  "+Submission_ID);
			break;
		}else{
			Thread.sleep(1000);
		}
		if(i==Long.parseLong(CONFIG.getProperty("ElementWaitTime"))){
			isTestCasePass = false;
			fnsTake_Screen_Shot("RedirectedFail");
			throw new Exception("FAILED == Application is not redirected to 'Edit Submission' screen from 'Create Submission' screen, after clicking on CREATE Button. TimeOut after <"+Long.parseLong(CONFIG.getProperty("ElementWaitTime"))+">Seconds, Please refer ScreenShot >> RedirectedFail"+fnsScreenShot_Date_format());
		}
	}
	
	
	
	
	
}




@Test( priority = 2, dependsOnMethods={"Create_new_Submission"}, description="")
public void EditSubmission_Add_Edit_and_Update_Data_in_Details_TAB() throws Throwable{
	fnsApps_Report_Logs("=========================================================================================================================================");
	fnsApps_Report_Logs("################### Test Case ::::::2 EditSubmission_Add_Edit_and_Update_Data_in_Details_TAB ");
	
	fnsGet_Element_Enabled("EditSubmission_DetailsTab_TechReviewer_DD_Click");
	fnsDD_value_Select_By_MatchingText_DownKeyPress("EditSubmission_DetailsTab_TechReviewer_DD_Click", "EditSubmission_DetailsTab_TechReviewer_DD_Value", "li", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "TechReviewer_DD"));
	Thread.sleep(2000);

	fnsGet_Element_Enabled("EditSubmission_DetailsTab_BillToContact_DD_Click");
	fnsDD_value_Select_By_MatchingText_DownKeyPress("EditSubmission_DetailsTab_BillToContact_DD_Click", "EditSubmission_DetailsTab_BillToContact_DD_Value", "li", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "BillToContact_DD"));
	
	
	fnsGet_Element_Enabled("EditSubmission_DetailsTab_CertDecisionMaker_LK_Bttn");
	fnsWait_and_Click("EditSubmission_DetailsTab_CertDecisionMaker_LK_Bttn");
	TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
	fnsGet_Element_Enabled("LookUp_First_Input_Field");
	fnsWait_and_Type("LookUp_First_Input_Field", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "CertDecisionMaker_LK"));
	TestSuiteBase_MonitorPlan.fnsLookup_RadioBttn_Select();
	Thread.sleep(1000);
	TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);	
	
	fnsGet_Element_Enabled("EditSubmission_DetailsTab_DeviationNeeded_Yes_Bttn");
	fnsWait_and_Click("EditSubmission_DetailsTab_DeviationNeeded_Yes_Bttn");
	
	
	//Wait for Deviation Comment field
	for(int clicktry=0; clicktry<20; clicktry++){
		if(driver.findElements(By.xpath(OR_Automotive.getProperty("EditSubmission_DetailsTab_Deviation_Comments_Box"))).size()>0){
			fnsGet_Element_Enabled("EditSubmission_DetailsTab_Deviation_Comments_Box");
			fnsWait_and_Type("EditSubmission_DetailsTab_Deviation_Comments_Box", "Deviation Needed == Yes");
			break;
		}else{
			Thread.sleep(1000);
		}
		if(clicktry==19){
			isTestCasePass = false;
			fnsTake_Screen_Shot("DeviationCommentBox_Not_Load");
			throw new Exception("FAILED == Deviation Comment box is not getting load, Please refer screen shot >> DeviationCommentBox_Not_Load"+fnsScreenShot_Date_format());
		}
	}
	
		
	fnsGet_Element_Enabled("EditSubmission_Save_Bttn");
	fnsWait_and_Click("EditSubmission_Save_Bttn");
	TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
	
	fncVerify_DataUpdated_Successfully("IPulse_ValidationMessage_Div", "Details TAB");
	
}


@Test( priority = 3, dependsOnMethods={"EditSubmission_Add_Edit_and_Update_Data_in_Details_TAB"}, description="")
public void EditSubmission_Add_Edit_and_Update_Data_in_Part_TAB() throws Throwable{
	fnsApps_Report_Logs("=========================================================================================================================================");
	fnsApps_Report_Logs("################### Test Case ::::::3 EditSubmission_Add_Edit_and_Update_Data_in_Part_TAB ");
	
	
	fnsGet_Element_Enabled("EditSubmission_PartTab");
	fnsWait_and_Click("EditSubmission_PartTab");
	TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
	
	
	fnsGet_Element_Enabled("EditSubmission_PartTab_AttachExistingPart_Bttn");
	fnsWait_and_Click("EditSubmission_PartTab_AttachExistingPart_Bttn");
	TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
	fnsGet_Element_Enabled("LookUp_First_Input_Field");
	fnsWait_and_Type("LookUp_First_Input_Field", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "AttachExistingPart_LK"));
	TestSuiteBase_MonitorPlan.fnsLookup_RadioBttn_Select();
	Thread.sleep(1000);
	TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);	
	
	
	
	fnsGet_Element_Enabled("EditSubmission_PartTab_Make_Model_Details_Add_Link");
	
	if(driver.findElements(By.xpath(OR_Automotive.getProperty("EditSubmission_PartTab_Make_Model_Details_RecordDelete_Link"))).size()>0){
		fnsGet_Element_Enabled("EditSubmission_PartTab_Make_Model_Details_RecordDelete_Link");
		fnsWait_and_Click("EditSubmission_PartTab_Make_Model_Details_RecordDelete_Link");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
	
		fnsGet_Element_Enabled("EditSubmission_PartTab_Make_Model_Details_RecordDelete_YES_Bttn");
		fnsWait_and_Click("EditSubmission_PartTab_Make_Model_Details_RecordDelete_YES_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
	}
	
	
	
	
	

	fnsGet_Element_Enabled("EditSubmission_PartTab_Make_Model_Details_Add_Link");
	fnsWait_and_Click("EditSubmission_PartTab_Make_Model_Details_Add_Link");
	TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
	
	
	fnsGet_Element_Enabled("EditSubmission_PartTab_Make_Model_Details_Make_DD_Click");
	fnsDD_value_Select_By_MatchingText_DownKeyPress("EditSubmission_PartTab_Make_Model_Details_Make_DD_Click", "EditSubmission_PartTab_Make_Model_Details_Make_DD_Value", "tr", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Make_Model_Details").split(",")[0]);
	Thread.sleep(2000);
	
	fnsGet_Element_Enabled("EditSubmission_PartTab_Make_Model_Details_Model_DD_Click");
	fnsDD_value_Select_By_MatchingText_DownKeyPress("EditSubmission_PartTab_Make_Model_Details_Model_DD_Click", "EditSubmission_PartTab_Make_Model_Details_Model_DD_Value", "li", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Make_Model_Details").split(",")[1]);
	
	
	fnsGet_Element_Enabled("EditSubmission_PartTab_Make_Model_Details_YearStart_DD_Click");
	fnsDD_value_Select_By_MatchingText_DownKeyPress("EditSubmission_PartTab_Make_Model_Details_YearStart_DD_Click", "EditSubmission_PartTab_Make_Model_Details_YearStart_DD_Value", "li", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Make_Model_Details").split(",")[2]);
	
	
	
	fnsGet_Element_Enabled("EditSubmission_PartTab_Make_Model_Details_YearEnd_DD_Click");
	fnsDD_value_Select_By_MatchingText_DownKeyPress("EditSubmission_PartTab_Make_Model_Details_YearEnd_DD_Click", "EditSubmission_PartTab_Make_Model_Details_YearEnd_DD_Value", "li", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Make_Model_Details").split(",")[3]);
	
	
	fnsGet_Element_Enabled("EditSubmission_Save_Bttn");
	fnsWait_and_Click("EditSubmission_Save_Bttn");
	TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
	
	fncVerify_DataUpdated_Successfully("IPulse_ValidationMessage_Div", "Part TAB");
	
}


@Test( priority = 4, dependsOnMethods={"EditSubmission_Add_Edit_and_Update_Data_in_Part_TAB"}, description="")
public void EditSubmission_Add_Edit_and_Update_Data_in_Documents_TAB() throws Throwable{
	fnsApps_Report_Logs("=========================================================================================================================================");
	fnsApps_Report_Logs("################### Test Case ::::::4 EditSubmission_Add_Edit_and_Update_Data_in_Documents_TAB ");
	
	
	fnsGet_Element_Enabled("EditSubmission_DocumentsTab");
	fnsWait_and_Click("EditSubmission_DocumentsTab");
	TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
	

	fnsGet_Element_Enabled("EditSubmission_DocumentsTab_SubmissionSection_Add_Bttn");
	fnsWait_and_Click("EditSubmission_DocumentsTab_SubmissionSection_Add_Bttn");
	TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
	
	fncAssert_Contains(OR_Automotive.getProperty("EditSubmission_DocumentsTab_AddSubmissionDocument_Title"), "Add Submission Document", "'Add Submission Document' Popup is not getting opened");
	
	fncUploadFile("EditSubmission_DocumentsTab_AddSubmissionDocument_BrowseFile_Bttn", "Add Submission Document");
	
	fnsGet_Element_Enabled("EditSubmission_DocumentsTab_AddSubmissionDocument_Description");
	fnsWait_and_Type("EditSubmission_DocumentsTab_AddSubmissionDocument_Description", Comments_Free_Text);
	
	fnsGet_Element_Enabled("EditSubmission_DocumentsTab_AddSubmissionDocument_Type_DD_Click");
	fnsDD_value_Select_By_MatchingText_DownKeyPress("EditSubmission_DocumentsTab_AddSubmissionDocument_Type_DD_Click", "EditSubmission_DocumentsTab_AddSubmissionDocument_Type_DD_value", "li", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "AddSubmissionDocument_PopUp_Type"));
	Thread.sleep(1500);
	
	fnsGet_Element_Enabled("EditSubmission_DocumentsTab_AddSubmissionDocument_Access_DD_Click");
	fnsDD_value_Select_By_MatchingText_DownKeyPress("EditSubmission_DocumentsTab_AddSubmissionDocument_Access_DD_Click", "EditSubmission_DocumentsTab_AddSubmissionDocument_Access_DD_value", "li", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "AddSubmissionDocument_PopUp_Access"));
	
	
	fnsGet_Element_Enabled("EditSubmission_DocumentsTab_AddSubmissionDocument_SaveClose_Bttn");
	fnsWait_and_Click("EditSubmission_DocumentsTab_AddSubmissionDocument_SaveClose_Bttn");
	TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
	
		
	
	
	TextFetched = fnsGet_Field_TEXT("EditSubmission_DocumentsTab_AddSubmissionDocument_RecordTable");
	try{
		assert ( !(TextFetched.toLowerCase().contains("no records found")) ):"FAILED == New Record data are not getting added into 'Add Submission Document' section of Dcouments Tab, Please refer screenshot >> RecordAddFail"+fnsScreenShot_Date_format();
		fnsApps_Report_Logs("PASSED == Successfully added New Record into 'Add Submission Document' section of Dcouments Tab.");
	}catch(Throwable t){
		isTestCasePass = false;
		fnsTake_Screen_Shot("RecordAddFail");
		fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
		throw new Exception(Throwables.getStackTraceAsString(t));		}
		
}





@Test( priority = 5, dependsOnMethods={"EditSubmission_Add_Edit_and_Update_Data_in_Documents_TAB"}, description="")
public void EditSubmission_Select_ItemRecevied_Radio_buttons_and_Save_it_in_Review_TAB() throws Throwable{
	fnsApps_Report_Logs("=========================================================================================================================================");
	fnsApps_Report_Logs("################### Test Case ::::::5 EditSubmission_Select_ItemRecevied_Radio_buttons_and_Save_it_in_Review_TAB ");
	
	
	fnsGet_Element_Enabled("EditSubmission_ReviewTab");
	fnsWait_and_Click("EditSubmission_ReviewTab");
	TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
	
	try{
		WebElement Items_Recevied_Element = fnsGet_OR_Automotive_ObjectX("EditSubmission_ReviewTab_RecordTable_tbody");
		List<WebElement> Items_Recevied_All_Records_Object = Items_Recevied_Element.findElements(By.tagName("tr"));
		boolean Radio_Bttn_Select_Done = false;
		
	
		String Items_Recevied_Yes_or_No = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Items_Recevied_Yes_or_No");
		String List_of_Items_Recevied_Excel [] = Items_Recevied_Yes_or_No.split(":");
		for(int i=0; i<List_of_Items_Recevied_Excel.length; i++){
			Items_Recevied_Row_No = 0;
			String Sets_of_Items_Recevied_Excel = List_of_Items_Recevied_Excel[i];
			String Item_Name_Excel = Sets_of_Items_Recevied_Excel.split(",")[0].trim();
			String Recevied_Status_Excel = Sets_of_Items_Recevied_Excel.split(",")[1].trim();
			
			for(WebElement  Items_Recevied_All_Records_Element : Items_Recevied_All_Records_Object){
				Items_Recevied_Row_No++;
				Radio_Bttn_Select_Done = false;
				String Items_Recevied_Row_Text = Items_Recevied_All_Records_Element.getText().toLowerCase().trim();
				if(Items_Recevied_Row_Text.contains(Item_Name_Excel.toLowerCase())){
					Items_Recevied_Row_No = 0;
					List<WebElement> Row_Radio_Bttn_Object = Items_Recevied_All_Records_Element.findElements(By.tagName("div"));
					for(WebElement Row_Radio_Bttn_Elements : Row_Radio_Bttn_Object){
						if( Row_Radio_Bttn_Elements.getAttribute("class").equals("ui-radiobutton ui-widget") ){
							Items_Recevied_Row_No++;
							if( (Recevied_Status_Excel.toLowerCase().equals("yes")) && Items_Recevied_Row_No==1 ){
								Row_Radio_Bttn_Elements.click();
								Radio_Bttn_Select_Done = true;
								break;
							}
							if( (Recevied_Status_Excel.toLowerCase().equals("no")) && Items_Recevied_Row_No==2 ){
								Row_Radio_Bttn_Elements.click();
								Radio_Bttn_Select_Done = true;
								break;
							}
						}
					}
				}
				if(Radio_Bttn_Select_Done == true){
					fnsApps_Report_Logs("PASSED == '"+Recevied_Status_Excel+"' radio button selection done for item '"+Item_Name_Excel+"'.");
					break;
				}
			}	
		}
	}catch(Throwable t){
		isTestCasePass = false;
		fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
		throw new Exception(Throwables.getStackTraceAsString(t));	
	}
	
	
	
	fnsGet_Element_Enabled("EditSubmission_Save_Bttn");
	fnsWait_and_Click("EditSubmission_Save_Bttn");
	TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
	
	fncVerify_DataUpdated_Successfully("IPulse_ValidationMessage_Div", "Review TAB");
		
}







@Test( priority = 6, dependsOnMethods={"EditSubmission_Select_ItemRecevied_Radio_buttons_and_Save_it_in_Review_TAB"}, description="")
public void EditSubmission_Finally_Status_Change_to_Completed_from_POReview_in_DetailsTAB() throws Throwable{
	fnsApps_Report_Logs("=========================================================================================================================================");
	fnsApps_Report_Logs("################### Test Case ::::::6 EditSubmission_Finally_Status_Change_to_Completed_from_POReview_in_DetailsTAB ");
	
	
	fnsGet_Element_Enabled("EditSubmission_DetailsTab");
	fnsWait_and_Click("EditSubmission_DetailsTab");
	TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
	
	//>Change the Status from PO Review to Tech Review.Click Save
	fnsGet_Element_Enabled("EditSubmission_DetailsTab_Status_DD_Click");
	fnsDD_value_Select_By_MatchingText_DownKeyPress("EditSubmission_DetailsTab_Status_DD_Click", "EditSubmission_DetailsTab_Status_DD_Value", "li", DetailsTab_Status_DD.split(",")[0]);
	fnsGet_Element_Enabled("EditSubmission_Save_Bttn");
	fnsWait_and_Click("EditSubmission_Save_Bttn");
	TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
	fncVerify_DataUpdated_Successfully("IPulse_ValidationMessage_Div", "Details TAB");
	
	

	//Go to the Part tab>Select the Description as Bracket and click Save.
	fnsGet_Element_Enabled("EditSubmission_PartTab");
	fnsWait_and_Click("EditSubmission_PartTab");
	TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
	fnsGet_Element_Enabled("EditSubmission_PartTab_Description_DD_Click");
	fnsDD_value_Select_By_MatchingText_DownKeyPress("EditSubmission_PartTab_Description_DD_Click", "EditSubmission_PartTab_Description_DD_Value", "li", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Description_DD"));
	fnsGet_Element_Enabled("EditSubmission_Save_Bttn");
	fnsWait_and_Click("EditSubmission_Save_Bttn");
	TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
	fncVerify_DataUpdated_Successfully("IPulse_ValidationMessage_Div", "Part TAB");
	
	
	
	
	

	
	//Back to Details tab 
	fnsGet_Element_Enabled("EditSubmission_DetailsTab");
	fnsWait_and_Click("EditSubmission_DetailsTab");
	TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		
		
		
		
	//change the status to >Need Mfg CA Response.Click Save.
	fnsGet_Element_Enabled("EditSubmission_DetailsTab_Status_DD_Click");
	fnsDD_value_Select_By_MatchingText_DownKeyPress("EditSubmission_DetailsTab_Status_DD_Click", "EditSubmission_DetailsTab_Status_DD_Value", "li", DetailsTab_Status_DD.split(",")[1]);
	fnsGet_Element_Enabled("EditSubmission_Save_Bttn");
	fnsWait_and_Click("EditSubmission_Save_Bttn");
	TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
	fncVerify_DataUpdated_Successfully("IPulse_ValidationMessage_Div", "Details TAB");
	
	
	//Change the status to >Tech Review .Click Save.
	fnsGet_Element_Enabled("EditSubmission_DetailsTab_Status_DD_Click");
	fnsDD_value_Select_By_MatchingText_DownKeyPress("EditSubmission_DetailsTab_Status_DD_Click", "EditSubmission_DetailsTab_Status_DD_Value", "li", DetailsTab_Status_DD.split(",")[2]);
	fnsGet_Element_Enabled("EditSubmission_Save_Bttn");
	fnsWait_and_Click("EditSubmission_Save_Bttn");
	TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
	fncVerify_DataUpdated_Successfully("IPulse_ValidationMessage_Div", "Details TAB");
	
	

	fnsGet_Element_Enabled("EditSubmission_DetailsTab_ApproveDeviation_Bttn");
	fnsWait_and_Click("EditSubmission_DetailsTab_ApproveDeviation_Bttn");
	TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
	/*fnsGet_Element_Enabled("EditSubmission_Save_Bttn");
	fnsWait_and_Click("EditSubmission_Save_Bttn");
	TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
	fncVerify_DataUpdated_Successfully("IPulse_ValidationMessage_Div", "Details TAB");*/

	
	
	
	//>Change the status to :Cert Decision Needed
	fnsGet_Element_Enabled("EditSubmission_DetailsTab_CertificationStatus_DD_Click");
	fnsDD_value_Select_By_MatchingText_DownKeyPress("EditSubmission_DetailsTab_CertificationStatus_DD_Click", "EditSubmission_DetailsTab_CertificationStatus_DD_Value", "li", DetailsTab_CertificationStatus_DD.split(",")[0]);
	fnsGet_Element_Enabled("EditSubmission_Save_Bttn");
	fnsWait_and_Click("EditSubmission_Save_Bttn");
	TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
	fncVerify_DataUpdated_Successfully("IPulse_ValidationMessage_Div", "Details TAB");
	
	
	
	//>Change the status to :Cert Decision Needed
	fnsGet_Element_Enabled("EditSubmission_DetailsTab_CertificationStatus_DD_Click");
	fnsDD_value_Select_By_MatchingText_DownKeyPress("EditSubmission_DetailsTab_CertificationStatus_DD_Click", "EditSubmission_DetailsTab_CertificationStatus_DD_Value", "li", DetailsTab_CertificationStatus_DD.split(",")[1]);
	fnsGet_Element_Enabled("EditSubmission_Save_Bttn");
	fnsWait_and_Click("EditSubmission_Save_Bttn");
	TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
	fncVerify_DataUpdated_Successfully("IPulse_ValidationMessage_Div", "Details TAB");
	
	
	//Change the status to >Tech Review .Click Save.
	fnsGet_Element_Enabled("EditSubmission_DetailsTab_Status_DD_Click");
	fnsDD_value_Select_By_MatchingText_DownKeyPress("EditSubmission_DetailsTab_Status_DD_Click", "EditSubmission_DetailsTab_Status_DD_Value", "li", DetailsTab_Status_DD.split(",")[3]);
	fnsGet_Element_Enabled("EditSubmission_Save_Bttn");
	fnsWait_and_Click("EditSubmission_Save_Bttn");
	TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
	fncVerify_DataUpdated_Successfully("IPulse_ValidationMessage_Div", "Details TAB");
		
}



























//#######################################################  Configuration Method  ################################################################
@AfterTest
public void reportTestResult() throws Throwable {
	TestSuiteBase_MonitorPlan.fns_ReportTestResult_atClassLevel(Automotive_Suitexls,  (this.getClass().getSimpleName()) );
}


@AfterTest
public void QuitBrowser(){
	TestSuiteBase_MonitorPlan.MoveMouseCursorToTaskBar();
	driver.quit();
}

}