package nsf.ecap.New_NSFOnline_Suite;

import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;

public class BS_37_IM_Integration_Test extends TestSuiteBase_New_NSFOnline {
	

	public int count = -1;
	public boolean BrowserOpen = false;
	public String Issue_View_Name = "Automation - Issues";
		
	public String Current_Date_and_Time = ""; 
	public String Current_Date = "";
	public String Current_Time = "";
	public String Issue_Create_Time = "";
	public String Current_Hour = null;
	public String IssueId = "";
	
	
	
	
	
	public String SiteCode = "";
	public String IssueType_DD = "";
	public String Issue_SubType_DD = "";
	public String TimeIncidentOccurred = "";
	public String DateIncidentOccurred = "";
	public String DateIncidentOccurred_ViewMode = "";
	public String DateTimeIssueReported = "";
	public String YourReference = "Automation Reference";
	public String DescriptionOfIssue = "";
	public String CorrectiveActionsAlreadyTakenBySiteIfApplicable = "N/A";
	public String YourName = "";
	public String IssueTitleReasonforIssue = "";
	public String HowIssueReported = "Web Interface";
	
	public String  HaveYouObtainedConsent = "1";
	public String  IncaseOfAMinor = "1";
	public String  InjuredPersonAGE = "999";
	public String  Address = "";
	public String  InjuredPersonPhoneNumber = "9988776655";
	public String  Gender = "1";
	public String  WentDirectlyToHospital = "2";
	public String  InjuredPersonStatus= "Contractor";
	public String  IncapacitationOfAWorker = "To be confirmed";
	public String  JobTitleIfEmployee = "GM";
	public String  IncidentType = "Assault on Employee - Physical";
	public String  PlaceofIncident = "Screen Seating";
	public String  InjuryDetail = "Bruise";
	public String  BodyPartInjured = "Abdomen";
	public String  ScreenLightingLevel = "Not Applicable";	
	public String DescriptionOfIssue_Edited = ""; 
	public String CorrectiveActionsAlreadyTakenBySiteIfApplicable_Edited = ""; 
	public String YourName_Edit = "";
	public String CrimeReferencePoliceNumber = "N/A";
	
	
	


	@Parameters({ "className" })
	@BeforeTest
	public void checkTestSkip(String className) throws Exception {
		if (className.isEmpty()) {
				className = this.getClass().getSimpleName();
		}
		RunningClassName = className;
		Running_Class_BS_Description = "[BS-37] Verify NSF Connect IM Integration Test";
		IsBrowserPresentAlready =false;
		fnsCheckClassLevelTestSkip(className);
	}

	@Test( priority = 0)
	public void NsfConnect_Launch_Browser_and_Login_WithUser_IntoThe_Application() throws Throwable{
		fnsApps_Report_Logs("################################## "+ Running_Class_BS_Description);
		try{ 
			if (!IsBrowserPresentAlready) {
				Login_Application_Name = "nsf_connect";
				IsBrowserPresentAlready = fnsBrowserLaunchAndLogin(Login_UserName, Login_Password);
				fnsSwitchAcoount_MultiAccess(RunningClassName, "SwitchAccount_DD");					
			}
			
			
			
			
			SiteCode = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "SiteCode");
			IssueType_DD = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "IssueType_DD");
			Issue_SubType_DD = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Issue_SubType_DD");
			IssueTitleReasonforIssue = fns_Return_UK_London_Time("ddMM", 0, 0, 0, 0, 0);
			
			
			
			
			
			
		}catch(Throwable t){
			isTestCasePass = false;
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		fnsLoading_Progressing_wait(1);
		fnsApps_Report_Logs("=========================================================================================================================================");
	}
	
	
	@Test(priority = 1, dependsOnMethods={"NsfConnect_Launch_Browser_and_Login_WithUser_IntoThe_Application"}, description="")
	public void NsfConnect_Create_Issue () throws Exception{		
		fnsApps_Report_Logs("################### Test Case 1:: NsfConnect_Create_Issue");
		
		try{
			fncMenu_Ajax_Link_Click_By_PassingAjaxPath("Issue_Ajax");
			
			fncNavigation_Verify_Application_Navigated_To_ViewScreen("'Issues' Menu Ajax", "Issue");
				
									
			fnsGet_Element_Enabled("Issue_CreateIssue_Bttn");
			fnsWait_and_Click("Issue_CreateIssue_Bttn");
			fnsLoading_Progressing_wait(2);
			
			fnsVerifyScreenNavigation_afterClickingOnElement("'Create Issue' button", "Issue/Create Issue' screen", OR_New_NSFOnline.getProperty("CreateIssue_IssueType_DD"));
			
			fnsSelect_Value_from_LookupPopup_By_Clicking_on_LookUpButton("Search for site", "Enter your site", "Site Code", SiteCode, "CreateIssue_TopLabelsInfo_Div");
			
			fnsDD_value_Select_TagOPTION_DDTypeSelect("CreateIssue_IssueType_DD", IssueType_DD, 60);
			fnsLoading_Progressing_wait(3);
			fnsDD_value_Select_TagOPTION_DDTypeSelect("CreateIssue_Issue_SubType_DD", Issue_SubType_DD, 60);
			fnsLoading_Progressing_wait(1);
			
			fnsGet_Element_Enabled("CreateIssue_CreateIssue_Bttn");
			fnsWait_and_Click("CreateIssue_CreateIssue_Bttn");
			fnsLoading_Progressing_wait(3);
			fnsLoading_Progressing_wait(3);
			
			fnsGet_Element_Enabled("CreateIssue_Draft_Message_Container");
			fnsLoading_Progressing_wait(1);
			String IssueCreated_Draft_Msg = fnsGet_OR_New_NSFOnline_ObjectX("CreateIssue_Draft_Message_Container").getText().trim();
			String Draft_Message_from_Excel = "Issue is created in Draft status. You must confirm this issue before NSF acts on it.".trim();
			try{
				assert ( (IssueCreated_Draft_Msg.toLowerCase()).contains(Draft_Message_from_Excel.toLowerCase()) );
				fnsApps_Report_Logs("PASSED == Message ("+IssueCreated_Draft_Msg+") verification is done when Issue created in draft status.");
			}catch(Throwable t){
				fnsTake_Screen_Shot("IssueCreatede_in_Draft_Message_Not_Match");
				throw new Exception("FAILED == Issue created in draft status Expected MESSAGE ("+Draft_Message_from_Excel+") is not Matched with Actual message ("+IssueCreated_Draft_Msg+"), please refer the screen shot >> IssueCreatede_in_Draft_Message_Not_Match"+fnsScreenShot_Date_format());
			}
			
			String Issue_id_Value = fnsReturn_SecondLabelValue_by_Passing_FirstLabelName(OR_New_NSFOnline.getProperty("CreateIssue_SiteInfo_Tab_InfoBar_Label_Top_Div"), "Issue #");
			
			try{
				assert ( (Issue_id_Value.toLowerCase()).contains("draft") );
				fnsApps_Report_Logs("PASSED == Successfully verified that DRAFT is coming under Issue ID.");
			}catch(Throwable t){
				fnsTake_Screen_Shot("Draft_Not_coming_under_IssueId");
				if(Issue_id_Value.contains("I")){
					throw new Exception("FAILED == Issue Id '"+Issue_id_Value+"' is created instead of 'DRAFT' word, please refer the screen shot >> Draft_Not_coming_under_IssueId"+fnsScreenShot_Date_format());
				}else{
					throw new Exception("FAILED == DRAFT is NOT coming under Issue ID field (next to label 'Issue #'), please refer the screen shot >> Draft_Not_coming_under_IssueId"+fnsScreenShot_Date_format());
				}
			}
			
			String Issue_ParentStatus = fnsReturn_SecondLabelValue_by_Passing_FirstLabelName(OR_New_NSFOnline.getProperty("CreateIssue_SiteInfo_Tab_InfoBar_Label_Top_Div"), "Issue Status (Parent Status)");
			try{
				assert ( (Issue_ParentStatus.toLowerCase()).contains("draft") );
				fnsApps_Report_Logs("PASSED == Successfully verified that issue parent status is 'draft'.");
			}catch(Throwable t){
				fnsTake_Screen_Shot("Issue_Parent_Status_Match_Fail");
				throw new Exception("FAILED == Issue parent status '"+Issue_ParentStatus+"' is not coming as 'DRAFT', please refer the screen shot >> Issue_Parent_Status_Match_Fail"+fnsScreenShot_Date_format());
			}
					
			fnsLoading_Progressing_wait(3);
			fnsWait_Click_on_First_Visible_Element_if_More_than_One_Coming("Next button", OR_New_NSFOnline.getProperty("CreateIssue_Next_button"));
			
			
			Current_Date_and_Time = fns_Return_UK_London_Time("dd-MM-yyyy _ HH:mm", 0, 0, 0, 0, 0);
			Current_Date = Current_Date_and_Time.split("\\_")[0].trim();
			Current_Time = Current_Date_and_Time.split("\\_")[1].trim();
			Integer Current_Hour_integer = Integer.parseInt((Current_Time).split("\\:")[0]);	
			if(Current_Hour_integer<10){
				Current_Hour = "0"+Integer.toString(Current_Hour_integer);
			}else{
				Current_Hour = Integer.toString(Current_Hour_integer);
			}				
			int Current_Minute = Integer.parseInt((Current_Time).split("\\:")[1]);	
		
			
			fnsLoading_Progressing_wait(2);
			fnsLoading_Progressing_wait(2);
			fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(true, "input", "Issue Title/ Reason for Issue", IssueTitleReasonforIssue);
			
			fnsCalendar_Pick_TodayDate_by_LabelName(false, true, "Date Incident Occurred", 0);
			
			boolean Issue_Create_Time_Match = false;
			boolean Hour_Increaed_once = true;
			String Input_Entered_Value = "";
			String minutes_String = "";
			for(int i=-2; i<=12; i++){
				try{
					Integer minutes = Current_Minute+i;	
					if(minutes<10){
						minutes_String = "0"+Integer.toString(minutes);
					}else if(minutes>59){
						int minute_cal = minutes-60;
						minutes_String = "0"+Integer.toString(minute_cal);	
						Current_Time = Current_Date_and_Time.split("\\_")[1].trim();
						if(Hour_Increaed_once){
							Current_Hour_integer++;
							Current_Hour = Integer.toString(Current_Hour_integer);
							
							if(Current_Hour_integer<10){
								Current_Hour = "0"+Integer.toString(Current_Hour_integer);
							}else{
								Current_Hour = Integer.toString(Current_Hour_integer);
							}
							Hour_Increaed_once = false;
						}
					}else{
						minutes_String = Integer.toString(minutes);
					}
					Issue_Create_Time = Current_Hour+":"+minutes_String;
					Input_Entered_Value = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX("//label[text()='Time Incident Occurred']/following::input[1]").getAttribute("value").trim();
					assert Input_Entered_Value.equalsIgnoreCase(Issue_Create_Time): "FAILED == 'Time Incident Occurred' field value <"+Input_Entered_Value+"> is NOT matched with expected value <"+Issue_Create_Time+">,  please refer the screen shot >> Expected_Value_Not_Match"+fnsScreenShot_Date_format();
					fnsApps_Report_Logs("PASSED == 'Time Incident Occurred' field value <"+Input_Entered_Value+"> is matched with expected value <"+Issue_Create_Time+">.");
					Issue_Create_Time_Match = true;
					break;
				}catch(Throwable t){
					if( i==12 && Issue_Create_Time_Match==false){
						fnsTake_Screen_Shot("DefaultTime_NOT_Matched");
						throw new Exception ("FAILED == Issue create default time <"+Input_Entered_Value+"> is NOT matched any time within the range [ "+Current_Time+" to "+Issue_Create_Time+" ], please refer the screen shot >> DefaultTime_NOT_Matched"+fnsScreenShot_Date_format());
					}
				}
			}
			TimeIncidentOccurred = Current_Hour+":00";
			DateIncidentOccurred = Current_Date;
			DateIncidentOccurred_ViewMode = fns_Return_UK_London_Time("dd-MMM-yyyy", 0, 0, 0, 0, 0);
			DescriptionOfIssue = "Automation Test - BS-13 DateTime - "+Current_Date_and_Time;
			YourName = Login_UserName;
			
	
			fnsWait_and_Click("CreateIssue_SummaryInfo_Tab_TimeIncidentOccurred_IconBttn");
			Thread.sleep(2500);
			fnsDD_value_Select_By_MatchingText_WithoutClick("CreateIssue_SummaryInfo_Tab_TimeIncidentOccurred_DD", "li", TimeIncidentOccurred);
			Thread.sleep(1000);
			fnsGet_Element_NOT_Display_WithoutOR("After entered 'Previous time from issue create time' <"+TimeIncidentOccurred+"> into the field 'Time Incident Occurred', the validation message", OR_New_NSFOnline.getProperty("CreateIssue_SummaryInfo_Tab_IncidentCanNotBeInFuture_Error"));
			
			fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(true, "input","Your Reference", YourReference);
			
			fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(false, "input","Time Incident Occurred", TimeIncidentOccurred);
			
			fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(true, "textarea","Description of Issue", DescriptionOfIssue);
			
			fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(true, "textarea","Corrective actions already taken by site (if applicable)", CorrectiveActionsAlreadyTakenBySiteIfApplicable);
			
			fnsDD_Verify_SelectedValue_TagOPTION_DDTypeSelect("CreateIssue_SummaryInfo_Tab_IssueSubType_DD", Issue_SubType_DD);
						
			fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(true, "input","Your Name", YourName);
			
			fnsLoading_Progressing_wait(2);
			fnsWait_Click_on_First_Visible_Element_if_More_than_One_Coming("'Save & Next' button", OR_New_NSFOnline.getProperty("CreateIssue_Save_and_Next_button"));
			fnsLoading_Progressing_wait(2);
			fnsLoading_Progressing_wait(2);
			
			
			
			
			//Details TAB - Create Issue
			Address = "Automation Test - BS-13 DateTime - "+Current_Date_and_Time;
			
			fnsWait_and_Click_on_RadioButton_by_LabelName(true, "Have you obtained consent from the customer for processing this personal information?", HaveYouObtainedConsent);	
			fnsWait_and_Click_on_RadioButton_by_LabelName(true, "In case of a minor (Age less than 18 years) Have you obtained Parental consent for processing this personal information?", IncaseOfAMinor);
			
			fnsGet_Element_Display_byPassing_ElementName_and_Xpath_WithoutOR("SiteInfo Tab", "'InjuredPersonStatus' DD", OR_New_NSFOnline.getProperty("CreateIssue_DetailInfo_Tab_InjuredPersonStatus_DD"));
			fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(true, "input","Injured Person Name", Login_UserName);					
			fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(true, "input","Injured Person AGE(yrs.)- Enter 999 if not known", InjuredPersonAGE);					
			fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(true, "textarea","Address", Address);
			fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(true, "input","Injured person phone number - Enter 999 if not known", InjuredPersonPhoneNumber);		
			
			fnsWait_and_Click_on_RadioButton_by_LabelName(true, "Gender", Gender);
			fnsWait_and_Click_on_RadioButton_by_LabelName(true, "Went Directly to Hospital", WentDirectlyToHospital);
			
			fnsDD_value_Select_TagOPTION_DDTypeSelect("CreateIssue_DetailInfo_Tab_InjuredPersonStatus_DD", InjuredPersonStatus, 60);
			fnsLoading_Progressing_wait(1);
			
			fnsDD_value_Select_TagOPTION_DDTypeSelect("CreateIssue_DetailInfo_Tab_IncapacitationOfAWorker_DD", IncapacitationOfAWorker, 60); 
			fnsLoading_Progressing_wait(1);
			
			fnsDD_value_Select_TagOPTION_DDTypeSelect("CreateIssue_DetailInfo_Tab_JobTitleIfEmployee_DD", JobTitleIfEmployee, 60);
			fnsLoading_Progressing_wait(1);
						
			fnsDD_value_Select_TagOPTION_DDTypeSelect("CreateIssue_DetailInfo_Tab_IncidentType_DD", IncidentType, 60);
			fnsLoading_Progressing_wait(1);
			
			fnsDD_value_Select_TagOPTION_DDTypeSelect("CreateIssue_DetailInfo_Tab_PlaceofIncident_DD", PlaceofIncident, 60);
			fnsLoading_Progressing_wait(1);
			
			fnsDD_value_Select_TagOPTION_DDTypeSelect("CreateIssue_DetailInfo_Tab_InjuryDetail_DD", InjuryDetail, 60);
			fnsLoading_Progressing_wait(1);
			
			fnsDD_value_Select_TagOPTION_DDTypeSelect("CreateIssue_DetailInfo_Tab_BodyPartInjured_DD", BodyPartInjured, 60);
			fnsLoading_Progressing_wait(1);
			
			fnsDD_value_Select_TagOPTION_DDTypeSelect("CreateIssue_DetailInfo_Tab_ScreenLightingLevel_DD", ScreenLightingLevel, 60);
			fnsLoading_Progressing_wait(1);
			
			fnsWait_Click_on_First_Visible_Element_if_More_than_One_Coming("'Save & Next' button", OR_New_NSFOnline.getProperty("CreateIssue_Save_and_Next_button"));
			fnsLoading_Progressing_wait(2);
			fnsLoading_Progressing_wait(2);
			
			
			fnsLoading_Progressing_wait(2);
			fns_UploadFile("CreateIssue_DocumentInfo_Tab_Upload_bttn", "CreateIssue_DocumentInfo_Tab_Browse");	
						
			fnsLoading_Progressing_wait(2);
			fns_UploadFile("CreateIssue_DocumentInfo_Tab_Upload_bttn", "CreateIssue_DocumentInfo_Tab_Browse");	
			fnsLoading_Progressing_wait(2);
			
			fnsGet_Element_Enabled("CreateIssue_DocumentInfo_Tab_FileDelete_bttn");
			fnsWait_and_Click("CreateIssue_DocumentInfo_Tab_FileDelete_bttn");
			fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
			
			
			fnsGet_Element_Enabled("CreateIssue_DocumentInfo_Tab_Delete_Yes_bttn");
			fnsWait_and_Click("CreateIssue_DocumentInfo_Tab_Delete_Yes_bttn");
			fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
			
			
			
			
			
			
			
			fnsLoading_Progressing_wait(2);
			fnsWait_Click_on_First_Visible_Element_if_More_than_One_Coming("'Confirm Issue' button", OR_New_NSFOnline.getProperty("CreateIssue_DocumentInfo_Tab_ConfirmIssue_Bttn"));
			fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
			DateTimeIssueReported = DateIncidentOccurred_ViewMode +" "+fns_Return_UK_London_Time("HH:mm", 0, 0, 0, 0, 0);;
			System.out.println("Current_Date_and_Time = "+DateTimeIssueReported);
			
			String Issue_ID_Text = fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  NewNsfOnline_Element_Max_Wait_Time, "successfully created", 25).toLowerCase();
				
			Issue_ID_Text = Issue_ID_Text.split("issue")[1].toLowerCase();
			IssueId = Issue_ID_Text.split("successfully")[0].trim().toUpperCase();	
			
		}catch (Throwable t){
			isTestCasePass = false;
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));
		}		
	}
	
	
	
	
	@Test(priority = 2, dependsOnMethods={"NsfConnect_Create_Issue"}, description="")
	public void NsfConnect_OpenNewlyCreatedIssue_AndVerify_AllTheDataDisplayed_IntoThe_EditAndViewMode () throws Exception{		
		fnsApps_Report_Logs("################### Test Case 2:: NsfConnect_OpenNewlyCreatedIssue_AndVerify_AllTheDataDisplayed_IntoThe_EditAndViewMode");
		try{
			
			fnsGet_Element_Enabled("View_AdvanceSearch_Bttn");
			fnsLoading_Progressing_wait(1);
			fnsWait_and_Click("View_AdvanceSearch_Bttn");
			fnsLoading_Progressing_wait(3);
							
			fnsGet_Element_Enabled("IssueAdvanceSearch_IssueID_Input");
			
			fnsWait_and_Clear("IssueAdvanceSearch_IssueID_Input");
			fnsWait_and_Type("IssueAdvanceSearch_IssueID_Input", IssueId);
			
			fnsWait_and_Click("IssueAdvanceSearch_Search_Bttn");
			fnsLoading_Progressing_wait(5);
			fnsGet_Element_Enabled("IssueAdvanceSearch_Result_Table");
			TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element(OR_New_NSFOnline.getProperty("IssueAdvanceSearch_Result_Table"));
			String TextFetch=fnsGet_Field_TEXT("IssueAdvanceSearch_Result_Table").toLowerCase().trim();
							
			try{
				assert TextFetch.contains(IssueId.toLowerCase().trim()):"FAILED == Issues Id <"+IssueId+"> for User ("+Login_UserName+")  is NOT displayed into the Advance Search results Table>, Please refer Screen shot >>"+IssueId+"_Not_Coming"+fnsScreenShot_Date_format();
				fnsApps_Report_Logs("PASSED ==  Successfully Verify that Issues Id <"+IssueId+"> is displayed into the search result.");
			}catch(Throwable t){
				fnsTake_Screen_Shot(IssueId+"_Not_Coming");
				throw new Exception(Throwables.getStackTraceAsString(t));
			}
								
			fnsWait_Click_on_First_Visible_Element_if_More_than_One_Coming("'"+IssueId+"' link", "//a[text()='"+IssueId+"']");
			fnsLoading_Progressing_wait(3);
													
			fnsVerifyScreenNavigation_afterClickingOnElement("'"+IssueId+"' link", "By Default 'Site' TAB in ReadOnlyMode", OR_New_NSFOnline.getProperty("EditIssue_Site_Tab_ByDefault_Open"));
	
			
			
			//Summary TAB: - Verify in Read Only mode
			fncVerify_TAB_Exists_and_Opening_without_any_Error("Summary", "ViewIssue_Summary_Tab");
			fnsLoading_Progressing_wait(3);
			fnsVerify_SavedValue_In_ViewMode_by_LabelName("Issue Type", IssueType_DD);
			fnsVerify_SavedValue_In_ViewMode_by_LabelName("Sub Type", Issue_SubType_DD);
			fnsVerify_SavedValue_In_ViewMode_by_LabelName("Issue Title/ Reason for Issue", IssueTitleReasonforIssue);
			fnsVerify_SavedValue_In_ViewMode_by_LabelName("Date Incident Occurred", DateIncidentOccurred_ViewMode);
			fnsVerify_SavedValue_In_ViewMode_by_LabelName("Time Incident Occurred", TimeIncidentOccurred);
			fnsVerify_SavedValue_In_ViewMode_by_LabelName("Date/ Time Issue Reported", DateTimeIssueReported);
			fnsVerify_SavedValue_In_ViewMode_by_LabelName("How Issue Reported", HowIssueReported);
			fnsVerify_SavedValue_In_ViewMode_by_LabelName("Your Reference", YourReference);
			fnsVerify_SavedValue_In_ViewMode_by_LabelName("Your Name", YourName);
			fnsVerify_SavedValue_In_ViewMode_by_LabelName("Description of Issue", DescriptionOfIssue);
			fnsVerify_SavedValue_In_ViewMode_by_LabelName("Corrective actions already taken by site (if applicable)", CorrectiveActionsAlreadyTakenBySiteIfApplicable);
			
			
			
			//Details TAB: - Verify in Read Only mode
			fncVerify_TAB_Exists_and_Opening_without_any_Error("Details", "ViewIssue_Details_Tab");
			fnsLoading_Progressing_wait(3);
			fnsVerify_SavedValue_In_ViewMode_by_LabelName("Have you obtained consent from the customer for processing this personal information?", "Yes");
			fnsVerify_SavedValue_In_ViewMode_by_LabelName("In case of a minor (Age less than 18 years) Have you obtained Parental consent for processing this personal information?", "Yes");
			fnsVerify_SavedValue_In_ViewMode_by_LabelName("Injured Person Name", Login_UserName);
			fnsVerify_SavedValue_In_ViewMode_by_LabelName("Injured Person AGE(yrs.)- Enter 999 if not known", InjuredPersonAGE);
			fnsVerify_SavedValue_In_ViewMode_by_LabelName("Address", Address);
			fnsVerify_SavedValue_In_ViewMode_by_LabelName("Injured person phone number - Enter 999 if not known", InjuredPersonPhoneNumber);
			fnsVerify_SavedValue_In_ViewMode_by_LabelName("Gender", "F");
			fnsVerify_SavedValue_In_ViewMode_by_LabelName("Went Directly to Hospital", "No");
			fnsVerify_SavedValue_In_ViewMode_by_LabelName("Injured Person Status", InjuredPersonStatus);
			fnsVerify_SavedValue_In_ViewMode_by_LabelName("Incapacitation of a Worker", IncapacitationOfAWorker);
			fnsVerify_SavedValue_In_ViewMode_by_LabelName("Job Title (If Employee)", JobTitleIfEmployee);
			fnsVerify_SavedValue_In_ViewMode_by_LabelName("Incident Type", IncidentType);
			fnsVerify_SavedValue_In_ViewMode_by_LabelName("Place of Incident", PlaceofIncident);
			fnsVerify_SavedValue_In_ViewMode_by_LabelName("Injury Detail", InjuryDetail);
			fnsVerify_SavedValue_In_ViewMode_by_LabelName("Body Part Injured", BodyPartInjured);
			fnsVerify_SavedValue_In_ViewMode_by_LabelName("Screen Lighting Level", ScreenLightingLevel);
			
			
			//Documents TAB: - Verify in Read Only mode
			fncVerify_TAB_Exists_and_Opening_without_any_Error("Documents", "ViewIssue_Documents_Tab");
			fnsLoading_Progressing_wait(3);
			String Upload_File_Xpath = "//td[text()='This is test pdf document.pdf']";
			try{
				if((driver.findElements(By.xpath(Upload_File_Xpath)).size()==1)){
					fnsApps_Report_Logs("PASSED == Uploaded docuement is displayed into the document TAB.");
				}else if((driver.findElements(By.xpath(Upload_File_Xpath)).size()>1)){
					throw new Exception("FAILED == More than one document is coming into the document TAB, please refer the screenshot >> Document_Display_FAIL"+fnsScreenShot_Date_format());
				}else{
					throw new Exception("FAILED == Uploaded document is NOT coming into the document TAB, please refer the screenshot >> Document_Display_FAIL"+fnsScreenShot_Date_format());
				}				
			}catch(Throwable t){
				fnsTake_Screen_Shot("Document_Display_FAIL");
				throw new Exception(Throwables.getStackTraceAsString(t));
			}
			
			
			fnsGet_Element_Enabled("EditIssue_EDIT_bttn");
			fnsLoading_Progressing_on_Popup_wait_for_Popup(1);
			fnsWait_and_Click("EditIssue_EDIT_bttn");
			fnsLoading_Progressing_wait(3);
			
			//Documents TAB: - Verify in Read Only mode
			try{
				if((driver.findElements(By.xpath(Upload_File_Xpath)).size()==1)){
					fnsApps_Report_Logs("PASSED == Uploaded docuement is displayed into the document TAB.");
				}else if((driver.findElements(By.xpath(Upload_File_Xpath)).size()>1)){
					throw new Exception("FAILED == More than one document is coming into the document TAB, please refer the screenshot >> Document_Display_FAIL"+fnsScreenShot_Date_format());
				}else{
					throw new Exception("FAILED == Uploaded document is NOT coming into the document TAB, please refer the screenshot >> Document_Display_FAIL"+fnsScreenShot_Date_format());
				}				
			}catch(Throwable t){
				fnsTake_Screen_Shot("Document_Display_FAIL");
				throw new Exception(Throwables.getStackTraceAsString(t));
			}
			
			//Summary TAB: - Verify in Edit mode
			fncVerify_TAB_Exists_and_Opening_without_any_Error("Summary", "EditIssue_SummaryInfo_Tab");
			fnsLoading_Progressing_wait(3);
			fnsVerify_SavedValue_In_ViewMode_by_LabelName("Issue Type", IssueType_DD);
			fnsDD_Verify_SelectedValue_TagOPTION_DDTypeSelect("CreateIssue_SummaryInfo_Tab_IssueSubType_DD", Issue_SubType_DD);
			fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(false, "input", "Issue Title/ Reason for Issue", IssueTitleReasonforIssue);
			fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(false, "input", "Date Incident Occurred", DateIncidentOccurred);
			fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(false, "input", "Time Incident Occurred", TimeIncidentOccurred);
			fnsVerify_SavedValue_In_ViewMode_by_LabelName("Date/ Time Issue Reported", DateTimeIssueReported);
			//fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(false, "input", "How Issue Reported", HowIssueReported);
			fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(false, "input", "Your Reference", YourReference);
			fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(false, "input", "Your Name", YourName);
			fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(false, "textarea", "Description of Issue", DescriptionOfIssue);
			fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(false, "textarea", "Corrective actions already taken by site (if applicable)", CorrectiveActionsAlreadyTakenBySiteIfApplicable);
			
			
			//Details TAB: - Verify in Edit mode
			fncVerify_TAB_Exists_and_Opening_without_any_Error("Details", "EditIssue_DetailInfo_Tab");
			fnsLoading_Progressing_wait(3);
			fnsWait_and_Click_on_RadioButton_by_LabelName(false, "Have you obtained consent from the customer for processing this personal information?", HaveYouObtainedConsent);	
			fnsWait_and_Click_on_RadioButton_by_LabelName(false, "In case of a minor (Age less than 18 years) Have you obtained Parental consent for processing this personal information?", IncaseOfAMinor);
			fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(false, "input","Injured Person Name", Login_UserName);					
			fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(false, "input","Injured Person AGE(yrs.)- Enter 999 if not known", InjuredPersonAGE);					
			fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(false, "textarea","Address", Address);
			fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(false, "input","Injured person phone number - Enter 999 if not known", InjuredPersonPhoneNumber);		
			
			fnsWait_and_Click_on_RadioButton_by_LabelName(false, "Gender", Gender);
			fnsWait_and_Click_on_RadioButton_by_LabelName(false, "Went Directly to Hospital", WentDirectlyToHospital);			
			
			fnsDD_Verify_SelectedValue_TagOPTION_DDTypeSelect("CreateIssue_DetailInfo_Tab_InjuredPersonStatus_DD", InjuredPersonStatus);
			fnsDD_Verify_SelectedValue_TagOPTION_DDTypeSelect("CreateIssue_DetailInfo_Tab_IncapacitationOfAWorker_DD", IncapacitationOfAWorker); 
			fnsDD_Verify_SelectedValue_TagOPTION_DDTypeSelect("CreateIssue_DetailInfo_Tab_JobTitleIfEmployee_DD", JobTitleIfEmployee);
			fnsDD_Verify_SelectedValue_TagOPTION_DDTypeSelect("CreateIssue_DetailInfo_Tab_IncidentType_DD", IncidentType);
			fnsDD_Verify_SelectedValue_TagOPTION_DDTypeSelect("CreateIssue_DetailInfo_Tab_PlaceofIncident_DD", PlaceofIncident);
			fnsDD_Verify_SelectedValue_TagOPTION_DDTypeSelect("CreateIssue_DetailInfo_Tab_InjuryDetail_DD", InjuryDetail);
			fnsDD_Verify_SelectedValue_TagOPTION_DDTypeSelect("CreateIssue_DetailInfo_Tab_BodyPartInjured_DD", BodyPartInjured);
			fnsDD_Verify_SelectedValue_TagOPTION_DDTypeSelect("CreateIssue_DetailInfo_Tab_ScreenLightingLevel_DD", ScreenLightingLevel);
			driver.quit();
		
		}catch (Throwable t){
			isTestCasePass = false;
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));
		}		
}

	
	@Test(priority = 3, dependsOnMethods={"NsfConnect_OpenNewlyCreatedIssue_AndVerify_AllTheDataDisplayed_IntoThe_EditAndViewMode"}, description="")
	public void IPulse_LoginIntoTheIpulse_VerifyAllSavedDataRetrieved_UpdateSuccessfully () throws Exception{		
		fnsApps_Report_Logs("################### Test Case 3:: IPulse_LoginIntoTheIpulse_VerifyAllSavedDataRetrieved_UpdateSuccessfully");
		try{
	
			TestSuiteBase_MonitorPlan.fns_Launch_Browser_Only();			
			TestSuiteBase_MonitorPlan.fnsIpulse_Login_SSO("iPulse_NsfConnect", "", CONFIG.getProperty("adminUsername"), CONFIG.getProperty("adminPassword"));
			fnsApps_Report_Logs("Browser is launched and Successfully login into the 'iPulse' Application");
			
			fnsMenu_Ajax_Link_Click_By_PassingAjaxPath_Ipulse("Ipulse_SearchIssues_Menu");
			fnsGet_Element_Enabled("iPulse_Footer");
			TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
			
			fnsGet_Element_Enabled("Ipulse_SearchIssue_issueId_input");
			TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
			fnsWait_and_Clear("Ipulse_SearchIssue_issueId_input");
			TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
			fnsGet_OR_New_NSFOnline_ObjectX("Ipulse_SearchIssue_issueId_input").sendKeys(IssueId);
			
		
			fnsGet_Element_Enabled("Ipulse_SearchIssue_search_bttn");
			fnsWait_and_Click("Ipulse_SearchIssue_search_bttn");
			TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(3);
			
			String IssueId_Xpath = "//a[text()='"+IssueId+"']";
			fnsWait_Click_on_First_Visible_Element_if_More_than_One_Coming("'"+IssueId+"' link", IssueId_Xpath);
			TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(3);
			
			fnsGet_Element_Enabled("Ipulse_EditIssue_Details_Tab");
			fnsWait_and_Click("Ipulse_EditIssue_Details_Tab");
			TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
			
			HaveYouObtainedConsent = "Yes";
			IncaseOfAMinor = "Yes";

			fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(false, "input","Injured Person Name", Login_UserName);					
			fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(false, "input","Injured Person Age (yrs.) - enter 999 if not known", InjuredPersonAGE);					
			fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(false, "input","Injured person phone number", InjuredPersonPhoneNumber);	
			String Address_Text = fnsGet_OR_New_NSFOnline_ObjectX("Ipulse_EditIssue_DetailsTab_Address_textarea").getAttribute("value").trim();
			try{
				assert Address_Text.equalsIgnoreCase(Address): "FAILED == 'Address' field value <"+Address_Text+"> is NOT matched with expected value <"+Address+">,  please refer the screen shot >> Expected_Address_Value_Not_Match"+fnsScreenShot_Date_format();
				fnsApps_Report_Logs("PASSED == 'Address' field value <"+Address_Text+"> is matched with expected value <"+Address+">.  Automation");
			}catch(Throwable t){
				fnsTake_Screen_Shot("Expected_Address_Value_Not_Match");
				throw new Exception(Throwables.getStackTraceAsString(t));	
			}
			
			fnsVerify_Expected_RadioButtonSelected_Ipulse("Gender", "Female");
			fnsVerify_Expected_RadioButtonSelected_Ipulse("Went Directly To Hospital", "No");			
			fnsVerify_Expected_RadioButtonSelected_Ipulse("Have you obtained consent from the customer for processing this personal information?", HaveYouObtainedConsent);	
			fnsVerify_Expected_RadioButtonSelected_Ipulse("In case of a minor (Age less than 18 years) Have you obtained Parental consent for processing this personal information?", IncaseOfAMinor);
			
			fnsVerify_Expected_DropDownValueSelected_Ipulse("Injured Person Status", InjuredPersonStatus);
			fnsVerify_Expected_DropDownValueSelected_Ipulse("Incapacitation of a Worker", IncapacitationOfAWorker);
			fnsVerify_Expected_DropDownValueSelected_Ipulse("Job Title (If Employee)", JobTitleIfEmployee);
			fnsVerify_Expected_DropDownValueSelected_Ipulse("Incident Type", IncidentType);
			fnsVerify_Expected_DropDownValueSelected_Ipulse("Place of Incident", PlaceofIncident);
			fnsVerify_Expected_DropDownValueSelected_Ipulse("Injury Detail", InjuryDetail);
			fnsVerify_Expected_DropDownValueSelected_Ipulse("Body Part Injured", BodyPartInjured);
			fnsVerify_Expected_DropDownValueSelected_Ipulse("Screen Lighting Level", ScreenLightingLevel);
			
			
			
			
			fnsGet_Element_Enabled("Ipulse_EditIssue_Documents_Tab");
			fnsWait_and_Click("Ipulse_EditIssue_Documents_Tab");
			TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
			
			String NSFConnect_UploadedFile_Xpath = "//a[text()='This is test pdf document.pdf']/following::span[text()='PUBLIC']";
			try{
				if((driver.findElements(By.xpath(NSFConnect_UploadedFile_Xpath)).size()==1)){
					fnsApps_Report_Logs("PASSED == Uploaded docuement is displayed under the document TAB into the ipulse.");
				}else{
					throw new Exception("FAILED == Uploaded document is NOT coming under the document TAB into the ipulse, please refer the screenshot >> Document_Display_FAIL"+fnsScreenShot_Date_format());
				}				
			}catch(Throwable t){
				fnsTake_Screen_Shot("Document_Display_FAIL");
				throw new Exception(Throwables.getStackTraceAsString(t));
			}
			
			String NSFConnect_DeletedFile_Xpath = "//a[text()='This is test pdf document.pdf']/following::span[text()='DELETED']/following::span[text()='INTERNAL']";
			try{
				if((driver.findElements(By.xpath(NSFConnect_DeletedFile_Xpath)).size()==1)){
					fnsApps_Report_Logs("PASSED == Deleted docuement is displayed (with description Deleted and Access Type Internal) under the document TAB into the ipulse.");
				}else{
					throw new Exception("FAILED == Deleted docuement is NOT displayed (with description Deleted and Access Type Internal)  under the document TAB into the ipuls, please refer the screenshot >> Document_Display_FAIL"+fnsScreenShot_Date_format());
				}				
			}catch(Throwable t){
				fnsTake_Screen_Shot("Document_Display_FAIL");
				throw new Exception(Throwables.getStackTraceAsString(t));
			}
			
			
			
			
			
			fnsGet_Element_Enabled("Ipulse_EditIssue_Summary_Tab");
			fnsWait_and_Click("Ipulse_EditIssue_Summary_Tab");
			TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
			
			
			fnsVerify_Expected_DropDownValueSelected_Ipulse("Issue Type", IssueType_DD);
			fnsVerify_Expected_DropDownValueSelected_Ipulse("Issue Subtype", Issue_SubType_DD);
			fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(false, "input", "Title (Short Description)", IssueTitleReasonforIssue);
			fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(false, "input", "Date/Time Issue", DateIncidentOccurred_ViewMode);
		//	fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(false, "input", "Time Incident Occurred", TimeIncidentOccurred);
			System.out.println(TimeIncidentOccurred);
		//	fnsVerify_SavedValue_In_ViewMode_by_LabelName("Date/ Time Issue Reported", DateIncidentOccurred_ViewMode);
			//fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(false, "input", "How Issue Reported", HowIssueReported);
			fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(false, "input", "Client Ref No", YourReference);
			fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(false, "input", "Site Contact Name", YourName);
			fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(false, "textarea", "Issue Notes ", DescriptionOfIssue);
			fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(false, "textarea", "Action Taken", CorrectiveActionsAlreadyTakenBySiteIfApplicable);
			
			DescriptionOfIssue_Edited = "Edited "+DescriptionOfIssue; 
			CorrectiveActionsAlreadyTakenBySiteIfApplicable_Edited = "Edited "+CorrectiveActionsAlreadyTakenBySiteIfApplicable; 
			Issue_SubType_DD = "Facility"; 
			YourName_Edit = "EditedYourName";
			
			fnsWait_and_Type("Ipulse_EditIssue_SummaryTab_IssueNote", DescriptionOfIssue_Edited);
			fnsWait_and_Type("Ipulse_EditIssue_SummaryTab_ActionTaken", CorrectiveActionsAlreadyTakenBySiteIfApplicable_Edited);
			fnsWait_and_Type("Ipulse_EditIssue_SummaryTab_SiteContactName", YourName_Edit);
			TestSuiteBase_MonitorPlan.WithOut_OR_fnDD_SelectValue_By_MatchingText(OR_New_NSFOnline.getProperty("Ipulse_EditIssue_SummaryTab_IssueSubType_DD_Click"), OR_New_NSFOnline.getProperty("Ipulse_EditIssue_SummaryTab_IssueSubType_DD_List"), "li", Issue_SubType_DD);
			
			
			fnsGet_Element_Enabled("Ipulse_EditIssue_save_button");
			fnsWait_and_Click("Ipulse_EditIssue_save_button");
			TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
			
			fnsGet_Element_Enabled("Ipulse_msg_Div");
			fnsVerify_DataUpdated_Successfully_Ipulse("Ipulse_msg_Div", "Summary Tab :  ");
			
			
			
			
			fnsGet_Element_Enabled("Ipulse_EditIssue_Details_Tab");
			fnsWait_and_Click("Ipulse_EditIssue_Details_Tab");
			TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
			
			IncapacitationOfAWorker = "2 days unable to work as normal in total";
			InjuredPersonStatus = "Employee";
			
			
			TestSuiteBase_MonitorPlan.WithOut_OR_fnDD_SelectValue_By_MatchingText(OR_New_NSFOnline.getProperty("Ipulse_EditIssue_DetailsTab_IncapacitationOfAWorker_DD_Click"), OR_New_NSFOnline.getProperty("Ipulse_EditIssue_DetailsTab_IncapacitationOfAWorker_DD_List"), "li", IncapacitationOfAWorker);
			TestSuiteBase_MonitorPlan.WithOut_OR_fnDD_SelectValue_By_MatchingText(OR_New_NSFOnline.getProperty("Ipulse_EditIssue_DetailsTab_InjuredPersonStatus_DD_Click"), OR_New_NSFOnline.getProperty("Ipulse_EditIssue_DetailsTab_InjuredPersonStatus_DD_List"), "li", InjuredPersonStatus);
			fnsWait_and_Type("Ipulse_EditIssue_DetailsTab_CrimeReferencePoliceNumber", CrimeReferencePoliceNumber);
			
			
			fnsGet_Element_Enabled("Ipulse_EditIssue_DetailsTab_NonHospitalTreatmentRequired_FirstAidKit");
			fnsWait_and_Click("Ipulse_EditIssue_DetailsTab_NonHospitalTreatmentRequired_FirstAidKit");
			TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
			
			fnsGet_Element_Enabled("Ipulse_EditIssue_DetailsTab_InjuryidentifiedABreakFracture");
			fnsWait_and_Click("Ipulse_EditIssue_DetailsTab_InjuryidentifiedABreakFracture");
			TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
			
			
			fnsGet_Element_Enabled("Ipulse_EditIssue_DetailsTab_DocumentUploadChecklist_Photographs");
			fnsWait_and_Click("Ipulse_EditIssue_DetailsTab_DocumentUploadChecklist_Photographs");
			TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
		
			fnsGet_Element_Enabled("Ipulse_EditIssue_save_button");
			fnsWait_and_Click("Ipulse_EditIssue_save_button");
			TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
			
			fnsGet_Element_Enabled("Ipulse_msg_Div");
			fnsVerify_DataUpdated_Successfully_Ipulse("Ipulse_msg_Div", "Details Tab :  ");
			
			driver.quit();
		}catch (Throwable t){
			isTestCasePass = false;
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));
		}		
}
	
	
	
	
	
	@Test(priority = 4, dependsOnMethods={"IPulse_LoginIntoTheIpulse_VerifyAllSavedDataRetrieved_UpdateSuccessfully"}, description="")
	public void NsfConnect_LoginIntoNsfConnect_VerifyAllSavedDataRetrieved_Successfully () throws Exception{		
		fnsApps_Report_Logs("################### Test Case 4:: NsfConnect_LoginIntoNsfConnect_VerifyAllSavedDataRetrieved_Successfully");
		try{	
			Login_Application_Name = "nsf_connect";
			IsBrowserPresentAlready = fnsBrowserLaunchAndLogin(Login_UserName, Login_Password);
			fnsSwitchAcoount_MultiAccess(RunningClassName, "SwitchAccount_DD");	
	
			fncMenu_Ajax_Link_Click_By_PassingAjaxPath("Issue_Ajax");
			
			
			fncNavigation_Verify_Application_Navigated_To_ViewScreen("'Issues' Menu Ajax", "Issue");
			
			
			
			fnsGet_Element_Enabled("View_AdvanceSearch_Bttn");
			fnsLoading_Progressing_wait(1);
			fnsWait_and_Click("View_AdvanceSearch_Bttn");
			fnsLoading_Progressing_wait(3);
							
			fnsGet_Element_Enabled("IssueAdvanceSearch_IssueID_Input");
			
			fnsWait_and_Clear("IssueAdvanceSearch_IssueID_Input");
			fnsWait_and_Type("IssueAdvanceSearch_IssueID_Input", IssueId);
			
			fnsWait_and_Click("IssueAdvanceSearch_Search_Bttn");
			fnsLoading_Progressing_wait(5);
			fnsGet_Element_Enabled("IssueAdvanceSearch_Result_Table");
			TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element(OR_New_NSFOnline.getProperty("IssueAdvanceSearch_Result_Table"));
			String TextFetch=fnsGet_Field_TEXT("IssueAdvanceSearch_Result_Table").toLowerCase().trim();
							
			try{
				assert TextFetch.contains(IssueId.toLowerCase().trim()):"FAILED == Issues Id <"+IssueId+"> for User ("+Login_UserName+")  is NOT displayed into the Advance Search results Table>, Please refer Screen shot >>"+IssueId+"_Not_Coming"+fnsScreenShot_Date_format();
				fnsApps_Report_Logs("PASSED ==  Successfully Verify that Issues Id <"+IssueId+"> is displayed into the search result.");
			}catch(Throwable t){
				fnsTake_Screen_Shot(IssueId+"_Not_Coming");
				throw new Exception(Throwables.getStackTraceAsString(t));
			}
								
			fnsWait_Click_on_First_Visible_Element_if_More_than_One_Coming("'"+IssueId+"' link", "//a[text()='"+IssueId+"']");
			fnsLoading_Progressing_wait(3);
													
			fnsVerifyScreenNavigation_afterClickingOnElement("'"+IssueId+"' link", "By Default 'Site' TAB in ReadOnlyMode", OR_New_NSFOnline.getProperty("EditIssue_Site_Tab_ByDefault_Open"));
	
			
			
			//Summary TAB: - Verify in Read Only mode
			fncVerify_TAB_Exists_and_Opening_without_any_Error("Summary", "ViewIssue_Summary_Tab");
			fnsLoading_Progressing_wait(3);
			fnsVerify_SavedValue_In_ViewMode_by_LabelName("Issue Type", IssueType_DD);
			fnsVerify_SavedValue_In_ViewMode_by_LabelName("Sub Type", Issue_SubType_DD );
			fnsVerify_SavedValue_In_ViewMode_by_LabelName("Issue Title/ Reason for Issue", IssueTitleReasonforIssue);
			fnsVerify_SavedValue_In_ViewMode_by_LabelName("Date Incident Occurred", DateIncidentOccurred_ViewMode);
			fnsVerify_SavedValue_In_ViewMode_by_LabelName("Time Incident Occurred", TimeIncidentOccurred);
			fnsVerify_SavedValue_In_ViewMode_by_LabelName("Date/ Time Issue Reported", DateTimeIssueReported);
			fnsVerify_SavedValue_In_ViewMode_by_LabelName("How Issue Reported", HowIssueReported);
			fnsVerify_SavedValue_In_ViewMode_by_LabelName("Your Reference", YourReference);
			fnsVerify_SavedValue_In_ViewMode_by_LabelName("Your Name", YourName_Edit );
			fnsVerify_SavedValue_In_ViewMode_by_LabelName("Description of Issue", DescriptionOfIssue_Edited );
			fnsVerify_SavedValue_In_ViewMode_by_LabelName("Corrective actions already taken by site (if applicable)", CorrectiveActionsAlreadyTakenBySiteIfApplicable_Edited );
			
			
			
			//Details TAB: - Verify in Read Only mode
			fncVerify_TAB_Exists_and_Opening_without_any_Error("Details", "ViewIssue_Details_Tab");
			fnsLoading_Progressing_wait(3);
			fnsVerify_SavedValue_In_ViewMode_by_LabelName("Have you obtained consent from the customer for processing this personal information?", "Yes");
			fnsVerify_SavedValue_In_ViewMode_by_LabelName("In case of a minor (Age less than 18 years) Have you obtained Parental consent for processing this personal information?", "Yes");
			fnsVerify_SavedValue_In_ViewMode_by_LabelName("Injured Person Name", Login_UserName);
			fnsVerify_SavedValue_In_ViewMode_by_LabelName("Injured Person AGE(yrs.)- Enter 999 if not known", InjuredPersonAGE);
			fnsVerify_SavedValue_In_ViewMode_by_LabelName("Address", Address);
			fnsVerify_SavedValue_In_ViewMode_by_LabelName("Injured person phone number - Enter 999 if not known", InjuredPersonPhoneNumber);
			fnsVerify_SavedValue_In_ViewMode_by_LabelName("Gender", "F");
			fnsVerify_SavedValue_In_ViewMode_by_LabelName("Went Directly to Hospital", "No");
			fnsVerify_SavedValue_In_ViewMode_by_LabelName("Injured Person Status", InjuredPersonStatus);
			fnsVerify_SavedValue_In_ViewMode_by_LabelName("Incapacitation of a Worker", IncapacitationOfAWorker );
			fnsVerify_SavedValue_In_ViewMode_by_LabelName("Job Title (If Employee)", JobTitleIfEmployee);
			fnsVerify_SavedValue_In_ViewMode_by_LabelName("Incident Type", IncidentType);
			fnsVerify_SavedValue_In_ViewMode_by_LabelName("Place of Incident", PlaceofIncident);
			fnsVerify_SavedValue_In_ViewMode_by_LabelName("Injury Detail", InjuryDetail);
			fnsVerify_SavedValue_In_ViewMode_by_LabelName("Body Part Injured", BodyPartInjured);
			fnsVerify_SavedValue_In_ViewMode_by_LabelName("Screen Lighting Level", ScreenLightingLevel);
			fnsVerify_SavedValue_In_ViewMode_by_LabelName("Injury identified as break/fracture", "Yes");
			fnsVerify_SavedValue_In_ViewMode_by_LabelName("Non Hospital Treatment Required", "First Aid Kit Stock Used");
			fnsVerify_SavedValue_In_ViewMode_by_LabelName("Document Upload Checklist (as applicable)", "Photographs");
	
}catch (Throwable t){
	isTestCasePass = false;
	fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
	throw new Exception(Throwables.getStackTraceAsString(t));
}		
}
	
	
	
//####################################################################### Config. Methods ###############################################################
	
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
