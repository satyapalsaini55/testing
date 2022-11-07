package nsf.ecap.New_NSFOnline_Suite;


import java.util.Hashtable;

import nsf.ecap.MonitorPlan_Suite.TestSuiteBase_MonitorPlan;
import nsf.ecap.util.TestUtil;

import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;

public class BS_13_IM_CreateIssue_AdvSearch extends TestSuiteBase_New_NSFOnline {
	

	public int count = -1;
	public boolean BrowserOpen = false;
	public String Issue_View_Name = "Automation - Issues";
	public String CreateIssueData_from_Excel = null;	
	
	public String Current_Date_and_Time = ""; 
	public String Current_Date = "";
	public String Current_Time = "";
	public String Issue_Create_Time = "";
	public String Current_Hour = null;
	public String Login_User_Name = null;



@BeforeTest
@Parameters({ "className" })
public void checkTestSkip(String className) throws Throwable {

	if (className.isEmpty()) {
		className = this.getClass().getSimpleName();
	}
	fnsCheckClassLevelTestSkip(className);
	
}

	
	
	
	@Test(dataProvider = "getTestData", priority = 1)
	public void Create_Edit_issue_and_Verify_AllTabs_are_coming_Without_any_Error_for___(String Serial_No, String Login_UserName_Password, String CreateIssueData, String Multi_Access) throws Exception{
		BrowserOpen = false;
		boolean Product_TAB_Enabled = true;
		fail = false;
		count++;
		Login_User_Name = Login_UserName_Password.split(":")[0].trim();
		String CaseSerialNo = Serial_No.split("=")[1].trim();
		String IssueId = null;
		Login_Password = Login_UserName_Password.split(":")[1].trim();
		TC_Step=1;		
		try {			
			if (!(runmodes[count].equalsIgnoreCase("Y"))) {
				fnsApps_Report_Logs("****************************************************************************************************************************************");
				fnsApps_Report_Logs("################################## Runmode of Case::" + (count + 1)+" for Login User ["+Login_User_Name+"]  is set to NO, So Skipping this Case.");
				skip=true;
				throw new SkipException("Runmode of Case::" + (count + 1)+" for Login User ["+Login_User_Name+"]  is set to NO, So Skipping this Case.");
			}else{
				BrowserOpen = true;
				skip=false;
				fnsApps_Report_Logs("****************************************************************************************************************************************");
				fnsApps_Report_Logs("################################## Execution Logs of Case::"+(count + 1)+" for Login User ["+Login_User_Name+"].");
				
				
				fnsBrowserLaunchAndLogin(Login_User_Name, Login_Password);
				fnsSwitchAcoount_MultiAccess(null, Multi_Access);
				
				if(driver.getTitle().toLowerCase().trim().contains("nsfonline")){
					fnsTake_Screen_Shot("NSFOnline_2.0_Open");
					throw new Exception("'NsfOnline 1.0' is getting open instead of 'NsOnline 2.0'"+" , Please refer the screen shot >> NSFOnline_2.0_Open"+fnsScreenShot_Date_format());
				}
				
				
				fncMenu_Ajax_Link_Click_By_PassingAjaxPath("Issue_Ajax");
				
				fncNavigation_Verify_Application_Navigated_To_ViewScreen("'Issues' Menu Ajax", "Issue");
					
				CreateIssueData_from_Excel = CreateIssueData;	
							
				fnsGet_Element_Enabled("Issue_CreateIssue_Bttn");
				fnsWait_and_Click("Issue_CreateIssue_Bttn");
				fnsLoading_Progressing_wait(2);
				
				fnsVerifyScreenNavigation_afterClickingOnElement("'Create Issue' button", "Issue/Create Issue' screen", OR_New_NSFOnline.getProperty("CreateIssue_IssueType_DD"));
				
				fnsSelect_Value_from_LookupPopup_By_Clicking_on_LookUpButton("Search for site", "Enter your site", "Site Code", fncReturn_KeyValue_From_HashTable("SiteCode"), "CreateIssue_TopLabelsInfo_Div");
				
				fnsDD_value_Select_TagOPTION_DDTypeSelect("CreateIssue_IssueType_DD", fncReturn_KeyValue_From_HashTable("Issue_DD"), 60);
				fnsLoading_Progressing_wait(3);
				fnsDD_value_Select_TagOPTION_DDTypeSelect("CreateIssue_Issue_SubType_DD", fncReturn_KeyValue_From_HashTable("Issue_Sub_DD"), 60);
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
				
				if( (fncReturn_KeyValue_From_HashTable("Issue_DD").equalsIgnoreCase("Legal/Technical Advice"))
							|| (fncReturn_KeyValue_From_HashTable("Issue_DD").equalsIgnoreCase("Food Allergy")) 
							|| (fncReturn_KeyValue_From_HashTable("Issue_DD").equalsIgnoreCase("Enforcement liaison"))
							|| (fncReturn_KeyValue_From_HashTable("Issue_DD").equalsIgnoreCase("Food Poisoning"))
							|| (fncReturn_KeyValue_From_HashTable("Issue_DD").equalsIgnoreCase("Incident"))
						){
					Product_TAB_Enabled = false;
				}
				
//				Current_Date_and_Time = fns_Requried_Date_format("dd-MM-yyyy _ HH:mm", 0, 0, 0, 0,0);
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
				
				String Future_Hour = fns_Return_UK_London_Time("HH", 0, 0, 0, 1, 0);	
				Integer Future_Hour_integer = Integer.parseInt(Future_Hour);
				if(Future_Hour_integer<10){
					Future_Hour = "0"+Integer.toString(Future_Hour_integer);
				}else{
					Future_Hour = Integer.toString(Future_Hour_integer);
				}
				String Future_Time = Future_Hour+":00";
				String Expected_DateTime_Validation_Message = "Incident can not be in future";
				
				
				
				
				fnsLoading_Progressing_wait(2);
				fnsLoading_Progressing_wait(2);
				fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(true, "input", "Issue Title/ Reason for Issue", fns_Return_UK_London_Time("ddMM", 0, 0, 0, 0, 0));
				
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
					//	Current_Hour_integer--;
					}
				}
				
				
				fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(true, "input","Time Incident Occurred", Future_Time);
				fnsGet_Element_Display_byPassing_ElementName_and_Xpath_WithoutOR("Summary TAB ", "After entered 'Future time from issue create time' <"+Future_Time+"> into the field 'Time Incident Occurred', the validation message", OR_New_NSFOnline.getProperty("CreateIssue_SummaryInfo_Tab_IncidentCanNotBeInFuture_Error"));
				
				fns_Assert_equalsIgnoreCase_Without_OR("text", "Date/Time validation message", OR_New_NSFOnline.getProperty("CreateIssue_SummaryInfo_Tab_IncidentCanNotBeInFuture_Error"), Expected_DateTime_Validation_Message);
				
				fnsWait_and_Click("CreateIssue_SummaryInfo_Tab_TimeIncidentOccurred_IconBttn");
				Thread.sleep(2500);
				fnsDD_value_Select_By_MatchingText_WithoutClick("CreateIssue_SummaryInfo_Tab_TimeIncidentOccurred_DD", "li", Current_Hour+":00");
				Thread.sleep(1000);
				fnsGet_Element_NOT_Display_WithoutOR("After entered 'Previous time from issue create time' <"+Current_Hour+":00"+"> into the field 'Time Incident Occurred', the validation message", OR_New_NSFOnline.getProperty("CreateIssue_SummaryInfo_Tab_IncidentCanNotBeInFuture_Error"));
				
				fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(true, "input","Your Reference", "Automation Test");
				
				fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(false, "input","Time Incident Occurred", Current_Hour+":00");
				
				fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(true, "textarea","Description of Issue", "Automation Test - BS-13 DateTime - "+Current_Date_and_Time);
				
				fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(true, "textarea","Corrective actions already taken by site (if applicable)", "N/A");
				
				fnsDD_Verify_SelectedValue_TagOPTION_DDTypeSelect("CreateIssue_SummaryInfo_Tab_IssueSubType_DD", fncReturn_KeyValue_From_HashTable("Issue_Sub_DD"));
				fncVerify_BackGroundColor_of_the_Element_Without_OR("IssueSubType", OR_New_NSFOnline.getProperty("CreateIssue_SummaryInfo_Tab_IssueSubType_DD"), "rgba(66, 169, 72, 1)");
				
				fnsGet_Element_Display_byPassing_ElementName_and_Xpath_WithoutOR("SummaryInfo Tab", "Disabled 'Save & Next' button", "("+(OR_New_NSFOnline.getProperty("CreateIssue_Save_and_Next_button").split("\\]")[0])+" and @disabled='disabled'])[2]");
				fnsGet_Element_Display_byPassing_ElementName_and_Xpath_WithoutOR("SummaryInfo Tab", "Disabled 'Save & Previous' button", "("+(OR_New_NSFOnline.getProperty("CreateIssue_Save_and_Previous_button").split("\\]")[0])+" and @disabled='disabled'])[2]");
				
				fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(true, "input","Your Name", Login_User_Name);
				
				fnsGet_Element_NOT_Display_WithoutOR("Disabled 'Save & Next' button", "("+(OR_New_NSFOnline.getProperty("CreateIssue_Save_and_Next_button").split("\\]")[0])+" and @disabled='disabled'])[2]");
				
				fnsGet_Element_NOT_Display_WithoutOR("Disabled 'Save & Previous' button", "("+(OR_New_NSFOnline.getProperty("CreateIssue_Save_and_Previous_button").split("\\]")[0])+" and @disabled='disabled'])[2]");
				
				fnsLoading_Progressing_wait(2);
				fnsWait_Click_on_First_Visible_Element_if_More_than_One_Coming("'Save & Next' button", OR_New_NSFOnline.getProperty("CreateIssue_Save_and_Next_button"));
				fnsLoading_Progressing_wait(2);
				fnsLoading_Progressing_wait(2);
				
				
				if( (fncReturn_KeyValue_From_HashTable("Issue_DD").equalsIgnoreCase("Incident")) 
						|| (fncReturn_KeyValue_From_HashTable("Issue_DD").equalsIgnoreCase("Food Allergy"))
						){
					fnsWait_and_Click_on_RadioButton_by_LabelName(true, "Have you obtained consent from the customer for processing this personal information?", "1");	
					fnsWait_and_Click_on_RadioButton_by_LabelName(true, "In case of a minor (Age less than 18 years) Have you obtained Parental consent for processing this personal information?", "1");	
				} else if ( (fncReturn_KeyValue_From_HashTable("Issue_DD").equalsIgnoreCase("Product Quality")) 
						|| (fncReturn_KeyValue_From_HashTable("Issue_DD").equalsIgnoreCase("Foreign Body")) 
						|| (fncReturn_KeyValue_From_HashTable("Issue_DD").equalsIgnoreCase("Non-Conformance")) 	
						|| (fncReturn_KeyValue_From_HashTable("Issue_DD").equalsIgnoreCase("Distribution")) 
					
						|| (fncReturn_KeyValue_From_HashTable("Issue_DD").equalsIgnoreCase("Food Poisoning"))
						){
					fnsWait_and_Click_on_RadioButton_by_LabelName(true, "Have you obtained consent from the customer for processing this personal information?", "2");	
					fnsWait_and_Click_on_RadioButton_by_LabelName(true, "In case of a minor (Age less than 18 years) Have you obtained Parental consent for processing this personal information?", "2");	
				}
				
				
				
				if(fncReturn_KeyValue_From_HashTable("Issue_DD").equalsIgnoreCase("Incident")){
					fnsGet_Element_Display_byPassing_ElementName_and_Xpath_WithoutOR("SiteInfo Tab", "'InjuredPersonStatus' DD", OR_New_NSFOnline.getProperty("CreateIssue_DetailInfo_Tab_InjuredPersonStatus_DD"));
					fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(true, "input","Injured Person Name", Login_User_Name);					
					fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(true, "input","Injured Person AGE(yrs.)- Enter 999 if not known", "999");					
					fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(true, "textarea","Address", "Automation Test - BS-13 DateTime - "+Current_Date_and_Time);
					fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(true, "input","Injured person phone number - Enter 999 if not known", "9988776655");		
					
					fnsWait_and_Click_on_RadioButton_by_LabelName(true, "Gender", "1");
					fnsWait_and_Click_on_RadioButton_by_LabelName(true, "Went Directly to Hospital", "2");
					fncVerify_BackGroundColor_of_the_Element_Without_OR("InjuredPersonStatus_DD", OR_New_NSFOnline.getProperty("CreateIssue_DetailInfo_Tab_InjuredPersonStatus_DD"), "rgba(169, 68, 66, 1)");
					fnsDD_value_Select_TagOPTION_DDTypeSelect("CreateIssue_DetailInfo_Tab_InjuredPersonStatus_DD", "Contractor", 60);
					fnsLoading_Progressing_wait(1);
					
					fnsDD_value_Select_TagOPTION_DDTypeSelect("CreateIssue_DetailInfo_Tab_TimeOffWorkInDays_DD", "To be confirmed", 60); //NOM-4090
					fnsLoading_Progressing_wait(1);
					
					/*fncVerify_BackGroundColor_of_the_Element_Without_OR("JobTitleIfEmployee_DD", OR_New_NSFOnline.getProperty("CreateIssue_DetailInfo_Tab_JobTitleIfEmployee_DD"), "rgba(169, 68, 66, 1)");*/
					fnsDD_value_Select_TagOPTION_DDTypeSelect("CreateIssue_DetailInfo_Tab_JobTitleIfEmployee_DD", "GM", 60);
					fnsLoading_Progressing_wait(1);
					
					fncVerify_BackGroundColor_of_the_Element_Without_OR("IncidentType_DD", OR_New_NSFOnline.getProperty("CreateIssue_DetailInfo_Tab_IncidentType_DD"), "rgba(169, 68, 66, 1)");
					fnsDD_value_Select_TagOPTION_DDTypeSelect("CreateIssue_DetailInfo_Tab_IncidentType_DD", "Assault on Employee - Physical", 60);
					fnsLoading_Progressing_wait(1);
					
					fncVerify_BackGroundColor_of_the_Element_Without_OR("PlaceofIncident_DD", OR_New_NSFOnline.getProperty("CreateIssue_DetailInfo_Tab_PlaceofIncident_DD"), "rgba(169, 68, 66, 1)");
					fnsDD_value_Select_TagOPTION_DDTypeSelect("CreateIssue_DetailInfo_Tab_PlaceofIncident_DD", "Screen Seating", 60);//test
				/*	fnsDD_value_Select_TagOPTION_DDTypeSelect("CreateIssue_DetailInfo_Tab_PlaceofIncident_DD", "Screen", 60);//stage
*/					fnsLoading_Progressing_wait(1);
					
					fncVerify_BackGroundColor_of_the_Element_Without_OR("InjuryDetail_DD", OR_New_NSFOnline.getProperty("CreateIssue_DetailInfo_Tab_InjuryDetail_DD"), "rgba(169, 68, 66, 1)");
					fnsDD_value_Select_TagOPTION_DDTypeSelect("CreateIssue_DetailInfo_Tab_InjuryDetail_DD", "Bruise", 60);
					fnsLoading_Progressing_wait(1);
					
					fncVerify_BackGroundColor_of_the_Element_Without_OR("BodyPartInjured_DD", OR_New_NSFOnline.getProperty("CreateIssue_DetailInfo_Tab_BodyPartInjured_DD"), "rgba(169, 68, 66, 1)");
					fnsDD_value_Select_TagOPTION_DDTypeSelect("CreateIssue_DetailInfo_Tab_BodyPartInjured_DD", "Abdomen", 60);
					fnsLoading_Progressing_wait(1);
					
					
					fncVerify_BackGroundColor_of_the_Element_Without_OR("ScreenLightingLevel_DD", OR_New_NSFOnline.getProperty("CreateIssue_DetailInfo_Tab_ScreenLightingLevel_DD"), "rgba(169, 68, 66, 1)");//test
					fnsDD_value_Select_TagOPTION_DDTypeSelect("CreateIssue_DetailInfo_Tab_ScreenLightingLevel_DD", "Not Applicable", 60);//test
					fnsLoading_Progressing_wait(1);
					
											
				/*	fnsGet_Element_Enabled("CreateIssue_DetailInfo_Tab_EmployeeInHospitalForMoreThan24Hours_Radio"); //stage
					//fncVerify_BackGroundColor_of_the_Element_Without_OR("'EmployeeInHospitalForMoreThan24Hours' radio button", OR_New_NSFOnline.getProperty("CreateIssue_DetailInfo_Tab_EmployeeInHospitalForMoreThan24Hours_Radio"), "rgba(169, 68, 66, 1)");
					fnsWait_and_Click("CreateIssue_DetailInfo_Tab_EmployeeInHospitalForMoreThan24Hours_Radio");
					fnsLoading_Progressing_wait(1);*/
					
					fncVerify_BackGroundColor_of_the_Element_Without_OR("InjuredPersonStatus_DD", OR_New_NSFOnline.getProperty("CreateIssue_DetailInfo_Tab_InjuredPersonStatus_DD"), "rgba(66, 169, 72, 1)");
					fncVerify_BackGroundColor_of_the_Element_Without_OR("IncidentType_DD", OR_New_NSFOnline.getProperty("CreateIssue_DetailInfo_Tab_IncidentType_DD"), "rgba(66, 169, 72, 1)");
					fncVerify_BackGroundColor_of_the_Element_Without_OR("PlaceofIncident_DD", OR_New_NSFOnline.getProperty("CreateIssue_DetailInfo_Tab_PlaceofIncident_DD"), "rgba(66, 169, 72, 1)");
					fncVerify_BackGroundColor_of_the_Element_Without_OR("InjuryDetail_DD", OR_New_NSFOnline.getProperty("CreateIssue_DetailInfo_Tab_InjuryDetail_DD"), "rgba(66, 169, 72, 1)");
					fncVerify_BackGroundColor_of_the_Element_Without_OR("BodyPartInjured_DD", OR_New_NSFOnline.getProperty("CreateIssue_DetailInfo_Tab_BodyPartInjured_DD"), "rgba(66, 169, 72, 1)");
					fncVerify_BackGroundColor_of_the_Element_Without_OR("ScreenLightingLevel_DD", OR_New_NSFOnline.getProperty("CreateIssue_DetailInfo_Tab_ScreenLightingLevel_DD"), "rgba(66, 169, 72, 1)"); //test
				
					
					
					fncVerify_SummaryTABFieldsDetails_NavigateFromDetailsTAB();
					
					fnsGet_Element_Display_byPassing_ElementName_and_Xpath_WithoutOR("DetailsInfo Tab", "'InjuredPersonStatus' DD", OR_New_NSFOnline.getProperty("CreateIssue_DetailInfo_Tab_InjuredPersonStatus_DD"));
					fnsDD_Verify_SelectedValue_TagOPTION_DDTypeSelect("CreateIssue_DetailInfo_Tab_InjuredPersonStatus_DD", "Contractor");
					fnsDD_Verify_SelectedValue_TagOPTION_DDTypeSelect("CreateIssue_DetailInfo_Tab_JobTitleIfEmployee_DD", "GM");
					fnsDD_Verify_SelectedValue_TagOPTION_DDTypeSelect("CreateIssue_DetailInfo_Tab_IncidentType_DD", "Assault on Employee - Physical");
					fnsDD_Verify_SelectedValue_TagOPTION_DDTypeSelect("CreateIssue_DetailInfo_Tab_PlaceofIncident_DD", "Screen Seating"); //test
					/*fnsDD_Verify_SelectedValue_TagOPTION_DDTypeSelect("CreateIssue_DetailInfo_Tab_PlaceofIncident_DD", "Screen"); *///stage
					fnsDD_Verify_SelectedValue_TagOPTION_DDTypeSelect("CreateIssue_DetailInfo_Tab_InjuryDetail_DD", "Bruise");
					fnsDD_Verify_SelectedValue_TagOPTION_DDTypeSelect("CreateIssue_DetailInfo_Tab_BodyPartInjured_DD", "Abdomen");
					fnsDD_Verify_SelectedValue_TagOPTION_DDTypeSelect("CreateIssue_DetailInfo_Tab_ScreenLightingLevel_DD", "Not Applicable");	 //test
					
					fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(false, "input","Injured Person Name", Login_User_Name);					
					fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(false, "input","Injured Person AGE(yrs.)- Enter 999 if not known", "999");					
					fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(false, "textarea","Address", "Automation Test - BS-13 DateTime - "+Current_Date_and_Time);
					fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(false, "input","Injured person phone number - Enter 999 if not known", "9988776655");
					
				}
				if( (fncReturn_KeyValue_From_HashTable("Issue_DD").equalsIgnoreCase("Enforcement liaison")) ){				
					fnsCalendar_Pick_TodayDate_by_LabelName(true, true, "Correspondence Date", 0);	
					fnsCalendar_Pick_TodayDate_by_LabelName(true, true, "Appeal Submitted Date", 0);	
					fnsCalendar_Pick_TodayDate_by_LabelName(true, true, "Date operational matters to be completed", 0);					
					fnsCalendar_Pick_TodayDate_by_LabelName(true, true, "Date Critical Matters must be Completed", 0);
					fnsCalendar_Pick_TodayDate_by_LabelName(true, true, "Date of next visit", 0);					
					fnsDD_value_Select_TagOPTION_DDTypeSelect("CreateIssue_DetailInfo_Tab_AppealOutcome_DD", "Accepted", 60);
					fnsDD_value_Select_TagOPTION_DDTypeSelect("CreateIssue_DetailInfo_Tab_OpsWorksCompleted_DD", "N/A", 60);
					fnsDD_value_Select_TagOPTION_DDTypeSelect("CreateIssue_DetailInfo_Tab_MaintenanceWorksCompleted_DD", "Yes", 60);
					fnsDD_value_Select_TagOPTION_DDTypeSelect("CreateIssue_DetailInfo_Tab_CriticalWorksCompleted_DD", "Yes", 60);
					fnsDD_value_Select_TagOPTION_DDTypeSelect("CreateIssue_DetailInfo_Tab_EnforcementNoticeType_DD", "Other", 60);
					fnsDD_value_Select_TagOPTION_DDTypeSelect("CreateIssue_DetailInfo_Tab_InspectionFrequency_DD", "6", 60);	
					/*fncVerify_BackGroundColor_of_the_Element_Without_OR("Activity_DD", "//label[text()='Threat/formal Action']/following::input[1]", "rgba(169, 68, 66, 1)");*/
					fnsWait_and_Click_on_RadioButton_by_LabelName(true, "Threat/formal Action", "1");	
					/*fncVerify_BackGroundColor_of_the_Element_Without_OR("Activity_DD", "//label[text()='Lack of Response']/following::input[1]", "rgba(169, 68, 66, 1)");*/
					fnsWait_and_Click_on_RadioButton_by_LabelName(true, "Lack of Response", "1");	
					/*fncVerify_BackGroundColor_of_the_Element_Without_OR("Activity_DD", "//label[text()='Case Escalated']/following::input[1]", "rgba(169, 68, 66, 1)");*/
					fnsWait_and_Click_on_RadioButton_by_LabelName(true, "Case Escalated", "1");	
					fncVerify_BackGroundColor_of_the_Element_Without_OR("Activity_DD", OR_New_NSFOnline.getProperty("CreateIssue_DetailInfo_Tab_Activity_DD"), "rgba(169, 68, 66, 1)");
					fnsDD_value_Select_TagOPTION_DDTypeSelect("CreateIssue_DetailInfo_Tab_Activity_DD", "Accident", 60);					
					fnsWait_and_Click_on_RadioButton_by_LabelName(true, "No Correspondence Available", "1");					
					fnsWait_and_Type("CreateIssue_DetailInfo_Tab_OfficerName_input", "Automation");
					fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(true, "input","Enforcement Authority", Login_User_Name);					
					fnsWait_and_Click_on_RadioButton_by_LabelName(true, "Food Hygiene Rating Scheme", "1");					
					fnsDD_value_Select_TagOPTION_DDTypeSelect("CreateIssue_DetailInfo_Tab_FHRS_SCORE_DD", "1", 60);					
					fnsWait_and_Click_on_RadioButton_by_LabelName(true, "Bad Score", "1");
					fnsWait_and_Click_on_RadioButton_by_LabelName(true, "Appeal Submitted", "1");					
									
					fnsWait_and_Click_on_RadioButton_by_LabelName(true, "Re-Rate", "1");					
									
					fnsDD_value_Select_TagOPTION_DDTypeSelect("CreateIssue_DetailInfo_Tab_EnforcementNoticeType_DD", "EPA S80 Notice", 60);
					fnsDD_value_Select_TagOPTION_DDTypeSelect("CreateIssue_DetailInfo_Tab_InspectionFrequency_DD", "Unknown", 60);					
								
					fnsDD_value_Select_TagOPTION_DDTypeSelect("CreateIssue_DetailInfo_Tab_New_FHRS_Score_DD", "0", 60);					
					fnsDD_value_Select_TagOPTION_DDTypeSelect("CreateIssue_DetailInfo_Tab_Activity_DD", "Other (Specify)", 60);					
					fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(true, "input","Activity Other", "Automation - Testing");
					
					fncVerify_BackGroundColor_of_the_Element_Without_OR("Activity_DD", OR_New_NSFOnline.getProperty("CreateIssue_DetailInfo_Tab_Activity_DD"), "rgba(66, 169, 72, 1)");
					fncVerify_SummaryTABFieldsDetails_NavigateFromDetailsTAB();					
					
					fnsGet_Element_Display_byPassing_ElementName_and_Xpath_WithoutOR("DetailsInfo Tab", "'Save' button", OR_New_NSFOnline.getProperty("CreateIssue_DetailInfo_Tab_Activity_DD"));
					fnsDD_Verify_SelectedValue_TagOPTION_DDTypeSelect("CreateIssue_DetailInfo_Tab_AppealOutcome_DD", "Accepted");
					fnsDD_Verify_SelectedValue_TagOPTION_DDTypeSelect("CreateIssue_DetailInfo_Tab_OpsWorksCompleted_DD", "N/A");
					fnsDD_Verify_SelectedValue_TagOPTION_DDTypeSelect("CreateIssue_DetailInfo_Tab_MaintenanceWorksCompleted_DD", "Yes");
					fnsDD_Verify_SelectedValue_TagOPTION_DDTypeSelect("CreateIssue_DetailInfo_Tab_CriticalWorksCompleted_DD", "Yes");
					fnsDD_Verify_SelectedValue_TagOPTION_DDTypeSelect("CreateIssue_DetailInfo_Tab_FHRS_SCORE_DD", "1");
					fnsDD_Verify_SelectedValue_TagOPTION_DDTypeSelect("CreateIssue_DetailInfo_Tab_EnforcementNoticeType_DD", "EPA S80 Notice");
					fnsDD_Verify_SelectedValue_TagOPTION_DDTypeSelect("CreateIssue_DetailInfo_Tab_InspectionFrequency_DD", "Unknown");
					fnsDD_Verify_SelectedValue_TagOPTION_DDTypeSelect("CreateIssue_DetailInfo_Tab_New_FHRS_Score_DD", "0");
					fnsDD_Verify_SelectedValue_TagOPTION_DDTypeSelect("CreateIssue_DetailInfo_Tab_Activity_DD", "Other (Specify)");	
					fnsVerify_FieldValue_Text_by_OR("value", "Officer's Name", OR_New_NSFOnline.getProperty("CreateIssue_DetailInfo_Tab_OfficerName_input"), "Automation");
					fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(false, "input","Enforcement Authority", Login_User_Name);
					fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(false, "input","Activity Other", "Automation - Testing");					
					fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(false, "input","Correspondence Date", Current_Date);
					fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(false, "input","Appeal Submitted Date", Current_Date);
					fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(false, "input","Date operational matters to be completed", Current_Date);
					fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(false, "input","Date Critical Matters must be Completed", Current_Date);
					fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(false, "input","Date of next visit", Current_Date);					
					fnsWait_and_Click_on_RadioButton_by_LabelName(false, "Threat/formal Action", "1");	
					fnsWait_and_Click_on_RadioButton_by_LabelName(false, "No Correspondence Available", "1");
					fnsWait_and_Click_on_RadioButton_by_LabelName(false, "Food Hygiene Rating Scheme", "1");
					fnsWait_and_Click_on_RadioButton_by_LabelName(false, "Bad Score", "1");
					fnsWait_and_Click_on_RadioButton_by_LabelName(false, "Appeal Submitted", "1");
					fnsWait_and_Click_on_RadioButton_by_LabelName(false, "Re-Rate", "1");					
				}			
				if( (fncReturn_KeyValue_From_HashTable("Issue_DD").equalsIgnoreCase("Food Allergy")) ){	
					fnsGet_Element_Display_byPassing_ElementName_and_Xpath_WithoutOR("SiteInfo Tab", "'NoOfPeopleSick'", OR_New_NSFOnline.getProperty("CreateIssue_DetailInfo_Tab_NoOfPeopleSick"));
					fncVerify_BackGroundColor_of_the_Element_Without_OR("NoOfPeopleSick", OR_New_NSFOnline.getProperty("CreateIssue_DetailInfo_Tab_NoOfPeopleSick"), "rgba(169, 68, 66, 1)");
					fncVerify_BackGroundColor_of_the_Element_Without_OR("FoodSuspected", OR_New_NSFOnline.getProperty("CreateIssue_DetailInfo_Tab_FoodSuspected"), "rgba(169, 68, 66, 1)");
					fnsWait_and_Type("CreateIssue_DetailInfo_Tab_FoodSuspected", "Meat");
					fnsWait_and_Click_on_RadioButton_by_LabelName(true, "Food samples available", "1");	
					fnsWait_and_Click_on_RadioButton_by_LabelName(true, "Attended GP or Hospital", "1");	
					fnsWait_and_Click_on_RadioButton_by_LabelName(true, "EHO Contacted / Involved", "1");	
					fnsWait_and_Click_on_RadioButton_by_LabelName(true, "Press / Social Media Involvement", "1");	
					fnsWait_and_Type("CreateIssue_DetailInfo_Tab_CustomerName_input", Login_User_Name);
					fnsWait_and_Type("CreateIssue_DetailInfo_Tab_CustomerEmailAddress_input", "Automation@test.com");
					fnsWait_and_Type("CreateIssue_DetailInfo_Tab_CustomerAddress_input", "Test Automation Address");
					fnsDD_value_Select_TagOPTION_DDTypeSelect("CreateIssue_DetailInfo_Tab_BroadFoodType_DD", "Meat products", 60);
					fnsCalendar_Pick_TodayDate_by_LabelName(true, true, "Date you were notified of the allegation", 0);	
					fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(true, "input","How and when were staff advised of the allergy requirement?", "Automation Test");
										
					fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(true, "input","No. of people sick", "-1");
					fnsGet_Element_Display_byPassing_ElementName_and_Xpath_WithoutOR("Details Tab", "Disabled 'Save & Next' button", "("+(OR_New_NSFOnline.getProperty("CreateIssue_Save_and_Next_button").split("\\]")[0])+" and @disabled='disabled'])[2]");
					fnsGet_Element_Display_byPassing_ElementName_and_Xpath_WithoutOR("Details Tab", "Disabled 'Save & Previous' button", "("+(OR_New_NSFOnline.getProperty("CreateIssue_Save_and_Previous_button").split("\\]")[0])+" and @disabled='disabled'])[2]");
					
					fnsWait_and_Clear("CreateIssue_DetailInfo_Tab_NoOfPeopleSick");
					fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(true, "input","No. of people sick", "1");
					fnsGet_Element_NOT_Display_WithoutOR("Disabled 'Save & Next' button", "("+(OR_New_NSFOnline.getProperty("CreateIssue_Save_and_Next_button").split("\\]")[0])+" and @disabled='disabled'])[2]");
					fnsGet_Element_NOT_Display_WithoutOR("Disabled 'Save & Previous' button", "("+(OR_New_NSFOnline.getProperty("CreateIssue_Save_and_Previous_button").split("\\]")[0])+" and @disabled='disabled'])[2]");
					fnsGet_Element_NOT_Display_WithoutOR("Disabled 'Confirm Issue' button", "("+(OR_New_NSFOnline.getProperty("CreateIssue_DocumentInfo_Tab_ConfirmIssue_Bttn").split("\\]")[0])+" and @disabled='disabled'])[2]");
					
					fncVerify_BackGroundColor_of_the_Element_Without_OR("NoOfPeopleSick", OR_New_NSFOnline.getProperty("CreateIssue_DetailInfo_Tab_NoOfPeopleSick"), "rgba(66, 169, 72, 1)");
					fncVerify_BackGroundColor_of_the_Element_Without_OR("FoodSuspected", OR_New_NSFOnline.getProperty("CreateIssue_DetailInfo_Tab_FoodSuspected"), "rgba(66, 169, 72, 1)");
					
					fncVerify_SummaryTABFieldsDetails_NavigateFromDetailsTAB();
					
					
					fnsGet_Element_Display_byPassing_ElementName_and_Xpath_WithoutOR("SiteInfo Tab", "'NoOfPeopleSick'", OR_New_NSFOnline.getProperty("CreateIssue_DetailInfo_Tab_NoOfPeopleSick"));
					fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(false, "input","No. of people sick", "1");
					fnsVerify_FieldValue_Text_by_OR("value", "FoodSuspected", OR_New_NSFOnline.getProperty("CreateIssue_DetailInfo_Tab_FoodSuspected"), "Meat");
					fnsWait_and_Click_on_RadioButton_by_LabelName(false, "Food samples available", "1");	
					fnsWait_and_Click_on_RadioButton_by_LabelName(false, "Attended GP or Hospital", "1");	
					fnsWait_and_Click_on_RadioButton_by_LabelName(false, "EHO Contacted / Involved", "1");	
					fnsWait_and_Click_on_RadioButton_by_LabelName(false, "Press / Social Media Involvement", "1");	
					fnsVerify_FieldValue_Text_by_OR("value", "Customer's Name", OR_New_NSFOnline.getProperty("CreateIssue_DetailInfo_Tab_CustomerName_input"), Login_User_Name);
					fnsVerify_FieldValue_Text_by_OR("value", "Customer's Email Address", OR_New_NSFOnline.getProperty("CreateIssue_DetailInfo_Tab_CustomerEmailAddress_input"), "Automation@test.com");
					fnsVerify_FieldValue_Text_by_OR("value", "Customer's Address", OR_New_NSFOnline.getProperty("CreateIssue_DetailInfo_Tab_CustomerAddress_input"), "Test Automation Address");
					
					fnsDD_Verify_SelectedValue_TagOPTION_DDTypeSelect("CreateIssue_DetailInfo_Tab_BroadFoodType_DD", "Meat products");
					fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(false, "input","Date you were notified of the allegation", Current_Date);		
					fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(false, "input","How and when were staff advised of the allergy requirement?", "Automation Test");
				}
				if( (fncReturn_KeyValue_From_HashTable("Issue_DD").equalsIgnoreCase("Food Poisoning")) ){	
					fnsGet_Element_Display_byPassing_ElementName_and_Xpath_WithoutOR("SiteInfo Tab", "'NoOfPeopleSick'", OR_New_NSFOnline.getProperty("CreateIssue_DetailInfo_Tab_NoOfPeopleSick"));
					fncVerify_BackGroundColor_of_the_Element_Without_OR("NoOfPeopleSick", OR_New_NSFOnline.getProperty("CreateIssue_DetailInfo_Tab_NoOfPeopleSick"), "rgba(169, 68, 66, 1)");
					fncVerify_BackGroundColor_of_the_Element_Without_OR("FoodSuspected", OR_New_NSFOnline.getProperty("CreateIssue_DetailInfo_Tab_FoodSuspected"), "rgba(169, 68, 66, 1)");
					fnsCalendar_Pick_TodayDate_by_LabelName(true, true, "Date you were notified of the allegation", 0);							
					fnsWait_and_Type("CreateIssue_DetailInfo_Tab_FoodSuspected", "Meat");
					fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(true, "input","Total similar meals served", "3");		
					
					fnsWait_and_Click_on_RadioButton_by_LabelName(true, "Food samples available", "1");	
					fnsWait_and_Click_on_RadioButton_by_LabelName(true, "Attended GP or Hospital", "1");	
					fnsWait_and_Click_on_RadioButton_by_LabelName(true, "Stool Test Done", "1");	
					fnsWait_and_Click_on_RadioButton_by_LabelName(true, "EHO Contacted / Involved", "1");
					fnsWait_and_Click_on_RadioButton_by_LabelName(true, "Alleged Undercooked Food", "1");
					fnsWait_and_Click_on_RadioButton_by_LabelName(true, "Press / Social Media Involvement", "1");
					fnsWait_and_Click_on_RadioButton_by_LabelName(true, "At Risk Group (Under 5 / Elderly / Pregnant / Weakened Immune System)", "1");
					
					fnsDD_value_Select_TagOPTION_DDTypeSelect("CreateIssue_DetailInfo_Tab_BroadFoodType_DD", "Meat products", 60);
					fnsDD_value_Select_TagOPTION_DDTypeSelect("CreateIssue_DetailInfo_Tab_ApproxHowLongAfterEatingUntilAppearanceFirstSymptoms_DD", "0-2 hrs", 60);
							
					
					fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(true, "input","No. of people sick", "-1");
					fnsGet_Element_Display_byPassing_ElementName_and_Xpath_WithoutOR("Details Tab", "Disabled 'Save & Next' button", "("+(OR_New_NSFOnline.getProperty("CreateIssue_Save_and_Next_button").split("\\]")[0])+" and @disabled='disabled'])[2]");
					fnsGet_Element_Display_byPassing_ElementName_and_Xpath_WithoutOR("Details Tab", "Disabled 'Save & Previous' button", "("+(OR_New_NSFOnline.getProperty("CreateIssue_Save_and_Previous_button").split("\\]")[0])+" and @disabled='disabled'])[2]");
					
					
					fnsWait_and_Clear("CreateIssue_DetailInfo_Tab_NoOfPeopleSick");
					fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(true, "input","No. of people sick", "1");
					fnsGet_Element_NOT_Display_WithoutOR("Disabled 'Save & Next' button", "("+(OR_New_NSFOnline.getProperty("CreateIssue_Save_and_Next_button").split("\\]")[0])+" and @disabled='disabled'])[2]");
					fnsGet_Element_NOT_Display_WithoutOR("Disabled 'Save & Previous' button", "("+(OR_New_NSFOnline.getProperty("CreateIssue_Save_and_Previous_button").split("\\]")[0])+" and @disabled='disabled'])[2]");
					fnsGet_Element_NOT_Display_WithoutOR("Disabled 'Confirm Issue' button", "("+(OR_New_NSFOnline.getProperty("CreateIssue_DocumentInfo_Tab_ConfirmIssue_Bttn").split("\\]")[0])+" and @disabled='disabled'])[2]");
					
					fncVerify_BackGroundColor_of_the_Element_Without_OR("NoOfPeopleSick", OR_New_NSFOnline.getProperty("CreateIssue_DetailInfo_Tab_NoOfPeopleSick"), "rgba(66, 169, 72, 1)");
					fncVerify_BackGroundColor_of_the_Element_Without_OR("FoodSuspected", OR_New_NSFOnline.getProperty("CreateIssue_DetailInfo_Tab_FoodSuspected"), "rgba(66, 169, 72, 1)");
					
					fncVerify_SummaryTABFieldsDetails_NavigateFromDetailsTAB();
					
					
					fnsGet_Element_Display_byPassing_ElementName_and_Xpath_WithoutOR("SiteInfo Tab", "'NoOfPeopleSick'", OR_New_NSFOnline.getProperty("CreateIssue_DetailInfo_Tab_NoOfPeopleSick"));
					fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(false, "input","No. of people sick", "1");
					fnsVerify_FieldValue_Text_by_OR("value", "FoodSuspected", OR_New_NSFOnline.getProperty("CreateIssue_DetailInfo_Tab_FoodSuspected"), "Meat");
					fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(false, "input","Total similar meals served", "3");	
					
					fnsWait_and_Click_on_RadioButton_by_LabelName(false, "Food samples available", "1");	
					fnsWait_and_Click_on_RadioButton_by_LabelName(false, "Attended GP or Hospital", "1");	
					fnsWait_and_Click_on_RadioButton_by_LabelName(false, "Stool Test Done", "1");	
					fnsWait_and_Click_on_RadioButton_by_LabelName(false, "EHO Contacted / Involved", "1");
					fnsWait_and_Click_on_RadioButton_by_LabelName(false, "Alleged Undercooked Food", "1");
					fnsWait_and_Click_on_RadioButton_by_LabelName(false, "Press / Social Media Involvement", "1");
					fnsWait_and_Click_on_RadioButton_by_LabelName(false, "At Risk Group (Under 5 / Elderly / Pregnant / Weakened Immune System)", "1");
					
					fnsDD_Verify_SelectedValue_TagOPTION_DDTypeSelect("CreateIssue_DetailInfo_Tab_BroadFoodType_DD", "Meat products");
					fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(false, "input","Date you were notified of the allegation", Current_Date);					
				}
				
				
				
				fnsWait_Click_on_First_Visible_Element_if_More_than_One_Coming("'Save & Next' button", OR_New_NSFOnline.getProperty("CreateIssue_Save_and_Next_button"));
				fnsLoading_Progressing_wait(2);
				fnsLoading_Progressing_wait(2);
				
				if(Product_TAB_Enabled){
					if( (env.equalsIgnoreCase("test")) && ( fncReturn_KeyValue_From_HashTable("Issue_DD").equalsIgnoreCase("Non-Conformance")) || (fncReturn_KeyValue_From_HashTable("Issue_DD").equalsIgnoreCase("Distribution"))  ){
						fnsSelect_Value_from_LookupPopup_By_Clicking_on_LookUpButton("Select Product Code", "Select Product Code", "",fncReturn_KeyValue_From_HashTable("ProductCode"), "CreateIssue_SummaryInfo_Tab_SelectProductCode_Label_Top_Div");
						if ( (fncReturn_KeyValue_From_HashTable("Issue_DD").equalsIgnoreCase("Distribution")) ){
							fnsSelect_Value_from_LookupPopup_By_Clicking_on_LookUpButton("NSF Online - Select Distributor", "Distributor", "",fncReturn_KeyValue_From_HashTable("DistributorCode"), "CreateIssue_Product_Tab_DistributorCode_Label_Top_Div");
							fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(true, "input","Quantity", "3");	
							fnsDD_value_Select_TagOPTION_DDTypeSelect("CreateIssue_Product_Tab_BroadFoodType_DD", "Unit", 60);
						}
					
					
					}else{
						fnsGet_Element_Enabled("CreateIssue_Product_Tab_MealGroup_YES_bttn");
						fnsWait_and_Click("CreateIssue_Product_Tab_MealGroup_YES_bttn");
						
						fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(true, "textarea","Please list all the items in the meal", "Automation - No supplier investigation is possible in these cases");
					}
					fnsLoading_Progressing_wait(3);
					fnsWait_Click_on_First_Visible_Element_if_More_than_One_Coming("'Save & Next' button", OR_New_NSFOnline.getProperty("CreateIssue_Save_and_Next_button"));
					fnsLoading_Progressing_wait(2);
					fnsLoading_Progressing_wait(2);
				}
				
				if( (fncReturn_KeyValue_From_HashTable("Issue_DD").equalsIgnoreCase("Incident")) 
						|| (fncReturn_KeyValue_From_HashTable("Issue_DD").equalsIgnoreCase("Enforcement liaison"))
						|| (fncReturn_KeyValue_From_HashTable("Issue_DD").equalsIgnoreCase("Food Allergy"))
						|| (fncReturn_KeyValue_From_HashTable("Issue_DD").equalsIgnoreCase("Food Poisoning"))
					){
					fnsLoading_Progressing_wait(2);
					fns_UploadFile("CreateIssue_DocumentInfo_Tab_Upload_bttn", "CreateIssue_DocumentInfo_Tab_Browse");			
				}
				fnsLoading_Progressing_wait(2);
				fnsWait_Click_on_First_Visible_Element_if_More_than_One_Coming("'Confirm Issue' button", OR_New_NSFOnline.getProperty("CreateIssue_DocumentInfo_Tab_ConfirmIssue_Bttn"));
				fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
				
				
				String Issue_ID_Text = fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  NewNsfOnline_Element_Max_Wait_Time, "successfully created", 25).toLowerCase();
						
				Issue_ID_Text = Issue_ID_Text.split("issue")[1].toLowerCase();
				IssueId = Issue_ID_Text.split("successfully")[0].trim().toUpperCase();
				
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
					assert TextFetch.contains(IssueId.toLowerCase().trim()):"FAILED == Issues Id <"+IssueId+"> for User ("+Login_User_Name+")  is NOT displayed into the Advance Search results Table>, Please refer Screen shot >>"+IssueId+"_Not_Coming"+fnsScreenShot_Date_format();
					fnsApps_Report_Logs("PASSED ==  Successfully Verify that Issues Id <"+IssueId+"> is displayed into the search result.");
				}catch(Throwable t){
					fnsTake_Screen_Shot(IssueId+"_Not_Coming");
					throw new Exception(Throwables.getStackTraceAsString(t));
				}
									
				fnsWait_Click_on_First_Visible_Element_if_More_than_One_Coming("'"+IssueId+"' link", "//a[text()='"+IssueId+"']");
				fnsLoading_Progressing_wait(3);
														
				fnsVerifyScreenNavigation_afterClickingOnElement("'"+IssueId+"' link", "By Default 'Site' TAB in ReadOnlyMode", OR_New_NSFOnline.getProperty("EditIssue_Site_Tab_ByDefault_Open"));
				
				fncVerify_TAB_Exists_and_Opening_without_any_Error("Summary", "ViewIssue_Summary_Tab");
				
				fnsGet_Element_Enabled("EditIssue_EDIT_bttn");
				fnsLoading_Progressing_on_Popup_wait_for_Popup(1);
				fnsWait_and_Click("EditIssue_EDIT_bttn");
				fnsLoading_Progressing_wait(3);
				
				
				fnsGet_Element_NOT_Display_WithoutOR("The validation message <IncidentCanNotBeInFuture> ", OR_New_NSFOnline.getProperty("CreateIssue_SummaryInfo_Tab_IncidentCanNotBeInFuture_Error"));
				fnsGet_Element_NOT_Display_WithoutOR("The validation message <OccurredDateCanNotBeGreaterThanReportedDate> ", OR_New_NSFOnline.getProperty("CreateIssue_SummaryInfo_Tab_OccurredDateCanNotBeGreaterThanReportedDate_Error"));
				
							
				fnsGet_Element_Enabled("CreateIssue_SummaryInfo_Tab_DateIncidentOccured");
				fnsCalendar_Pick_TodayDate_by_LabelName(false, true, "Date Incident Occurred", -1);
				fnsLoading_Progressing_wait(2);
				
				fnsGet_Element_Enabled("CreateIssue_SummaryInfo_Tab_TimeIncidentOccurred");
				fnsWait_and_Click("CreateIssue_SummaryInfo_Tab_TimeIncidentOccurred");
				fnsWait_and_Clear("CreateIssue_SummaryInfo_Tab_TimeIncidentOccurred");
				fnsWait_and_Type("CreateIssue_SummaryInfo_Tab_TimeIncidentOccurred", fns_Return_UK_London_Time("HH:mm", 0, 0, 0, -1, 0));
			
				fnsLoading_Progressing_wait(2);
				fnsWait_Click_on_First_Visible_Element_if_More_than_One_Coming("'Confirm Issue' button", OR_New_NSFOnline.getProperty("EditIssue_Summary_Tab_SaveAndReturnToView_bttn"));
				fnsLoading_Progressing_wait(3);
				fnsLoading_Progressing_wait(2);
									
				fnsVerifyScreenNavigation_afterClickingOnElement("'Save & Return To View' button", "By Default 'Summary' TAB in ReadOnlyMode", OR_New_NSFOnline.getProperty("EditIssue_Summary_Tab_ByDefault_Open"));
				
					
				
				if( (fncReturn_KeyValue_From_HashTable("Issue_DD").equalsIgnoreCase("Product Quality")) 
						|| (fncReturn_KeyValue_From_HashTable("Issue_DD").equalsIgnoreCase("Foreign Body")) 
						|| (fncReturn_KeyValue_From_HashTable("Issue_DD").equalsIgnoreCase("Non-Conformance"))
						|| (fncReturn_KeyValue_From_HashTable("Issue_DD").equalsIgnoreCase("Legal/Technical Advice"))
						){
					
					fncMenu_Ajax_Link_Click_By_PassingAjaxPath("Issue_Ajax");
					
					fncNavigation_Verify_Application_Navigated_To_ViewScreen("'Issues' Menu Ajax", "Issue");
					
					fncVerify_View_Display_Open_and_Delete_it(2, Issue_View_Name, "View_Delete_Link", "View_Remove_Link");
					
					fnsGet_Element_Enabled("CreateNewView_Link");
					fnsWait_and_Click("CreateNewView_Link");
					fnsLoading_Progressing_wait(2);
					
					fnsGet_Element_Enabled("CreateView_Step1_ViewName");
					fnsLoading_Progressing_wait(1);
					fnsWait_and_Type("CreateView_Step1_ViewName", Issue_View_Name);
					fnsLoading_Progressing_wait(2);
					
					fnsWait_and_Clear("CreateView_Step2_IssueId");
					fnsWait_and_Type("CreateView_Step2_IssueId", IssueId);
					
					fnsGet_Element_Enabled("CreateView_CreateView_Bttn");
					fnsWait_and_Click("CreateView_CreateView_Bttn");
								
					fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  60, "A new view has been added to your list", 25);
				
					
					fncNavigation_Verify_Application_Navigated_To_ViewScreen("'CreateView' button", "Issue");
					
					fnsWait_Click_on_First_Visible_Element_if_More_than_One_Coming("'"+IssueId+"' link", "//a[text()='"+IssueId+"']");
					fnsLoading_Progressing_wait(4);
										
					
					fnsVerifyScreenNavigation_afterClickingOnElement("'"+IssueId+"' link", "By Default 'Site' TAB in ReadOnlyMode", OR_New_NSFOnline.getProperty("ViewIssue_Site_Tab_ByDefault_Open"));
				}
			
			
				fncVerify_TAB_Exists_and_Opening_without_any_Error("Documents", "ViewIssue_Documents_Tab");
				fncVerify_TAB_Exists_and_Opening_without_any_Error("Summary", "ViewIssue_Summary_Tab");
				fncVerify_TAB_Exists_and_Opening_without_any_Error("Details", "ViewIssue_Details_Tab");
				if(Product_TAB_Enabled){
					fncVerify_TAB_Exists_and_Opening_without_any_Error("Product", "ViewIssue_Product_Tab");
				}				
				fncVerify_TAB_Exists_and_Opening_without_any_Error("Response History", "ViewIssue_ResponseHistory_Tab");
				fncVerify_TAB_Exists_and_Opening_without_any_Error("Site", "ViewIssue_Site_Tab");
				
			}
		}catch(SkipException sk){
			throw new SkipException("Runmode of Case::" + (count + 1)+" for Customer  ["+Login_User_Name+"]  is set to NO, So Skipping this Case.");
			
		}catch(NoSuchWindowException W){
			fail = true;
			isTestCasePass = false;
			throw new Exception(W.getMessage());
			
		}catch(Throwable t){
			fail = true;
			isTestCasePass = false;
			fnsApps_Report_Logs("Case:"+CaseSerialNo.trim()+Throwables.getStackTraceAsString(t));
			throw new Exception("Case:"+CaseSerialNo.trim()+Throwables.getStackTraceAsString(t));
			
		}
}

	


public String fncReturn_KeyValue_From_HashTable(String Key) throws Throwable{
	try{
		Hashtable<String, String> DataProvider_HT=new Hashtable<String, String>();  
		String Excel_value_Split_DoubleCollon [] =  fnsRemoveFormatting(CreateIssueData_from_Excel).split(":");
		for(int i=0; i<Excel_value_Split_DoubleCollon.length; i++ ){
			String HashTable_Data = Excel_value_Split_DoubleCollon[i].trim();
			DataProvider_HT.put(HashTable_Data.split("=")[0].trim().toUpperCase(), HashTable_Data.split("=")[1].trim());	
		}
		return DataProvider_HT.get(Key.toUpperCase()).trim();
	}catch(Throwable t){
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
}
	
public void fncVerify_BackGroundColor_of_the_Element_Without_OR(String ElementName, String Xpath, String Color_Code) throws Throwable{
	String Color_Name = "";
	try{
		if(Color_Code.equalsIgnoreCase("rgba(169, 68, 66, 1)")){
			Color_Name = "RED";
		}else if(Color_Code.equalsIgnoreCase("rgba(66, 169, 72, 1)")){
			Color_Name = "GREEN";
		}
		WebElement Element = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(Xpath);
		String BG_Color = Element.getCssValue("border-bottom-color").trim();
		assert BG_Color.equalsIgnoreCase(Color_Code.trim()) : "FAILED == '"+Color_Name+"' Color is NOT matched for element '"+ElementName+"', please refer the screen shot >> "+Color_Name+"_Color_Not_Match"+fnsScreenShot_Date_format();
		fnsApps_Report_Logs("PASSED == '"+Color_Name+"' color is matched for '"+ElementName+"'. (Automation)");
	}catch(Throwable t){
		fnsTake_Screen_Shot(Color_Name+"_Color_Not_Match");
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
	
}

public void fncVerify_SummaryTABFieldsDetails_NavigateFromDetailsTAB() throws Throwable{
	try{
		fnsLoading_Progressing_wait(2);
		fnsWait_Click_on_First_Visible_Element_if_More_than_One_Coming("'Save & Previous' button", OR_New_NSFOnline.getProperty("CreateIssue_Save_and_Previous_button"));
		fnsLoading_Progressing_wait(2);
		fnsLoading_Progressing_wait(2);
		
		fnsGet_Element_Display_byPassing_ElementName_and_Xpath_WithoutOR("Summary Info Tab", "'DateIncidentOccured'", OR_New_NSFOnline.getProperty("CreateIssue_SummaryInfo_Tab_DateIncidentOccured"));
		
		fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(false, "input","Date Incident Occurred", Current_Date);
		fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(false, "input","Time Incident Occurred", Current_Hour+":00");
		fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(false, "input","Your Reference", "Automation Test");
		fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(false, "textarea","Description of Issue", "Automation Test - BS-13 DateTime - "+Current_Date_and_Time);
		fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(false, "textarea","Corrective actions already taken by site (if applicable)", "N/A");
		fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(false, "input","Your Name", Login_User_Name);
		
		fncVerify_BackGroundColor_of_the_Element_Without_OR("IssueSubType_DD", OR_New_NSFOnline.getProperty("CreateIssue_SummaryInfo_Tab_IssueSubType_DD"), "rgba(66, 169, 72, 1)");
		fnsDD_Verify_SelectedValue_TagOPTION_DDTypeSelect("CreateIssue_SummaryInfo_Tab_IssueSubType_DD", fncReturn_KeyValue_From_HashTable("Issue_Sub_DD"));
		
		fnsLoading_Progressing_wait(2);
		fnsWait_Click_on_First_Visible_Element_if_More_than_One_Coming("'Save & Previous' button", OR_New_NSFOnline.getProperty("CreateIssue_Save_and_Previous_button"));
		fnsLoading_Progressing_wait(2);
		fnsLoading_Progressing_wait(2);
		
		fnsGet_Element_Display_byPassing_ElementName_and_Xpath_WithoutOR("SiteInfo Tab", "'Save' button", OR_New_NSFOnline.getProperty("CreateIssue_Draft_Message_Container"));
								
		fnsLoading_Progressing_wait(2);
		fnsWait_Click_on_First_Visible_Element_if_More_than_One_Coming("'Next' button", OR_New_NSFOnline.getProperty("CreateIssue_Next_button"));
		fnsLoading_Progressing_wait(2);
		fnsLoading_Progressing_wait(2);
		
		fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(false, "input","Date Incident Occurred", Current_Date);
		fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(false, "input","Time Incident Occurred", Current_Hour+":00");
		fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(false, "input","Your Reference", "Automation Test");
		fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(false, "textarea","Description of Issue", "Automation Test - BS-13 DateTime - "+Current_Date_and_Time);
		fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(false, "textarea","Corrective actions already taken by site (if applicable)", "N/A");
		fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(false, "input","Your Name", Login_User_Name);
		
		fnsLoading_Progressing_wait(2);
		fnsWait_Click_on_First_Visible_Element_if_More_than_One_Coming("'Save & Next' button", OR_New_NSFOnline.getProperty("CreateIssue_Save_and_Next_button"));
		fnsLoading_Progressing_wait(2);
		fnsLoading_Progressing_wait(2);
	}catch(Throwable t){
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
}
//####################################################################### Config. Methods ###############################################################
	@AfterMethod
	public void reportDataSetResult() {
		if(count>-1){
			if (skip)
				TestUtil.reportDataSetResult(New_NSFOnline_Suitexls, this.getClass().getSimpleName(), count + 2 , "SKIP");
			else if (fail) {
				isTestCasePass = false;
				TestUtil.reportDataSetResult(New_NSFOnline_Suitexls, this.getClass().getSimpleName(), count + 2, "FAIL");
			} else
				TestUtil.reportDataSetResult(New_NSFOnline_Suitexls, this.getClass().getSimpleName(), count + 2, "PASS");
	
			skip = false;
			fail = false;
		}
}

	
	
	@AfterTest
	public void reportTestResult() throws Throwable {
		TestSuiteBase_MonitorPlan.fns_ReportTestResult_atClassLevel(New_NSFOnline_Suitexls,  (this.getClass().getSimpleName()), isTestCasePass );
	}
	

	@AfterMethod
	public void QuitBrowser(){
		try{
			driver.quit();
		}catch (Throwable t){
			//nothing to do
		}
	}
	
	
	
	@DataProvider
	public Object[][] getTestData() {
		return TestUtil.getData(New_NSFOnline_Suitexls, this.getClass().getSimpleName());
	}



}
