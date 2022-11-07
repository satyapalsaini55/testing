package nsf.ecap.New_NSFOnline_Suite;

import java.util.ArrayList;
import java.util.List;

import nsf.ecap.MonitorPlan_Suite.TestSuiteBase_MonitorPlan;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;


public class BS_10_BkAdmin extends TestSuiteBase_New_NSFOnline {



@Parameters({ "className" })
@BeforeTest
public void checkTestSkip(String className) throws Exception {
	if (className.isEmpty()) {
		className = this.getClass().getSimpleName();
	}
	IsBrowserPresentAlready =false;
	fnsCheckClassLevelTestSkip(className);
	fnsApps_Report_Logs("################################## [BS-10] BK Admin Test Case");
}


//##########################################   EXCEL Variable ###############################################################################################

public String Site_View_Name = "Site - "+fnsReturn_CurrentTime();
public String Audit_View_Name = "Site - "+fnsReturn_CurrentTime();
public boolean Browser_open = false;
public String Site_number = null;
public String CreateView_AuditNo_having_CA = null;
public BS_03_CheckList BS_03_CheckList = new BS_03_CheckList();







@Test( priority = 1)
public void Case_1_Approver__Verify_ListedMenu_and_TopRightButtons_Coming_on_LandingScreen() throws Throwable{
	fnsApps_Report_Logs("=========================================================================================================================================");
	fnsApps_Report_Logs("#### Case_1_Approver__Verify_ListedMenu_and_TopRightButtons_Coming_on_LandingScreen ");
	
	try{	
		Site_number = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Approver_Active_SiteNo_having_CA");
		Site_View_Name = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Site_View_Name");
		Audit_View_Name = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Audit_View_Name");
		CreateView_AuditNo_having_CA = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "CreateView_AuditNo_having_CA");
		
		Login_UserName = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Approver_Login_User_Password").split(":")[0].trim();
		Login_Password = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Approver_Login_User_Password").split(":")[1].trim();
		
		fnsBrowserLaunchAndLogin(Login_UserName, Login_Password);
		fnsSwitchAcoount_MultiAccess(this.getClass().getSimpleName(), "SwitchAccount_DD");
		fnsLoading_Progressing_wait_AlertLaoder(2);
			
		fncVerify_Menu_SubMenu_Listed_into_Excel_are_displayed_into_the_application("Menu_Ajax_List_of_Approver");
		Browser_open = true;	
	}catch(Throwable t){
		isTestCasePass = false;
		Browser_open = false;
		driver.quit();
		fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
}
		
		

@Test( priority = 2)
public void Case_1_Approver__ClickOn_SiteAjax_OpenSite_then_OpenAudit_Download_Audit_and_CAR_Reports() throws Throwable{
	fnsApps_Report_Logs("=========================================================================================================================================");
	fnsApps_Report_Logs("#### Case_1_Approver__ClickOn_SiteAjax_OpenSite_then_OpenAudit_Download_Audit_and_CAR_Reports ");
	
	try{
		if(Browser_open == false){
			fnsBrowserLaunchAndLogin(Login_UserName, Login_Password);
			fnsSwitchAcoount_MultiAccess(this.getClass().getSimpleName(), "SwitchAccount_DD");
			fnsLoading_Progressing_wait_AlertLaoder(2);
		}
		
		fncMenuClick_CreateView_for_Site_or_SelectView_fromShared_for_Audit_ByPassing_ScreenName_and_ViewName("Site", Site_View_Name);	
		
			
		String ViewFirstRecord_SiteCode_Column_Xpath= OR_New_NSFOnline.getProperty("View_Result_Table")+"/tr[1]/td["+fnsReturn_ColumnNumber_from_TableHeaderXpath_WithoutOR(OR_New_NSFOnline.getProperty("View_Result_TableHeader"), "Site code")+"]";
		String Approver_SiteCode = fnsw_Click_on_Table_Link_by_Passing_Cell_Xpath__and__Return_CellValue(ViewFirstRecord_SiteCode_Column_Xpath);
		
		fnsVerifyScreenNavigation_afterClickingOnElement("'"+Approver_SiteCode+"' link", "Default 'Facility Info' Tab", OR_New_NSFOnline.getProperty("Site_Facility_Info_Tab_ByDefault_Opened"));

			
		fnsGet_Element_Enabled("Site_Audits_Tab");
		fnsWait_and_Click("Site_Audits_Tab");
		fnsLoading_Progressing_wait(2);
		fnsLoading_Progressing_wait(2);
		
		//fnsGet_Element_Enabled("View_Result_Table"); Not Working
		Integer Visible_TableNo = TestSuiteBase_MonitorPlan.WithOut_OR_Return_DisplayElementNumber_if_More_than_One_Coming(OR_New_NSFOnline.getProperty("View_Result_Table")); //View_Result_TableHeader
		String Visible_Table_Header_Xpath = TestSuiteBase_MonitorPlan.WithOut_OR_Return_XPATH_if_More_than_One_Coming(OR_New_NSFOnline.getProperty("View_Result_TableHeader")); //View_Result_TableHeader
				
		String View_Audit_FirstRow_Column_ReportsButton_Xpath= "("+OR_New_NSFOnline.getProperty("View_Result_Table")+")["+Visible_TableNo+"]"+"/tr[1]/td["+fnsReturn_ColumnNumber_from_TableHeaderXpath_WithoutOR(Visible_Table_Header_Xpath, "Reports")+"]/button";
		String View_Audit_FirstRow_AuditNumber_Column_Xpath = "("+OR_New_NSFOnline.getProperty("View_Result_Table")+")["+Visible_TableNo+"]"+"/tr[1]/td["+fnsReturn_ColumnNumber_from_TableHeaderXpath_WithoutOR(Visible_Table_Header_Xpath, "Audit Code")+"]";
		String Approver_SiteView_AuditNumber = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(View_Audit_FirstRow_AuditNumber_Column_Xpath).getText().trim();
		
		
		fnsDownload_File_or_Verify_Validation_Message("<"+Approver_SiteView_AuditNumber+"> AuditNumber's Report", View_Audit_FirstRow_Column_ReportsButton_Xpath);
		
		fnsw_Click_on_Table_Link_by_Passing_Cell_Xpath__and__Return_CellValue(View_Audit_FirstRow_AuditNumber_Column_Xpath);
		
		fnsVerifyScreenNavigation_afterClickingOnElement("'"+Approver_SiteView_AuditNumber+"' link", "Default 'Audit_Info' Tab", OR_New_NSFOnline.getProperty("Audits_Audit_Info_Tab_ByDefault_Opened"));
		
		fnsGet_Element_Display_byPassing_ElementName_and_Xpath_WithoutOR("Audit Info Tab", "'Audit Report' link" ,OR_New_NSFOnline.getProperty("Audits_Audit_Info_Tab_DownloadAuditReport_button"));
		fnsGet_Element_Display_byPassing_ElementName_and_Xpath_WithoutOR("Audit Info Tab", "'CAR Report' link" ,OR_New_NSFOnline.getProperty("Audits_Audit_Info_Tab_DownloadCorrectiveActionReport_button"));
		
		

		fncSub_Menu_Ajax_Link_Click_By_PassingAjaxPath("Sites_Ajax", "Sites_SubMenu_Audits_Ajax");
		
		fncNavigation_Verify_Application_Navigated_To_ViewScreen("'Audits' Menu Ajax", "Audits");
		
		fnsGet_Element_Enabled("View_AdvanceSearch_Bttn");
		fnsLoading_Progressing_wait(1);
		fnsWait_and_Click("View_AdvanceSearch_Bttn");
		fnsLoading_Progressing_wait(5);
					
		fnsGet_Element_Enabled("AuditsAdvanceSearch_Search_Bttn");
		fnsLoading_Progressing_wait(3);
		fnsWait_and_Clear("Site_AuditsAdvanceSearch_AuditNo");
		fnsWait_and_Type("Site_AuditsAdvanceSearch_AuditNo", Approver_SiteView_AuditNumber);
		
		fnsWait_and_Click("AuditsAdvanceSearch_Search_Bttn");
		fnsLoading_Progressing_wait(5);
		fnsGet_Element_Enabled("AuditsAdvanceSearch_Result_Table");
		TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element(OR_New_NSFOnline.getProperty("AuditsAdvanceSearch_Result_Table"));
		
						
		String ViewFirstRecord_AuditCode_Column_Xpath= OR_New_NSFOnline.getProperty("View_Result_Table")+"/tr[1]/td["+fnsReturn_ColumnNumber_from_TableHeaderXpath_WithoutOR(OR_New_NSFOnline.getProperty("View_Result_TableHeader"), "Audit Code")+"]";
		String Approver_Audit_Number = fnsw_Click_on_Table_Link_by_Passing_Cell_Xpath__and__Return_CellValue(ViewFirstRecord_AuditCode_Column_Xpath);

		fnsVerifyScreenNavigation_afterClickingOnElement("'"+Approver_Audit_Number+"' link", "Default 'Audit_Info' Tab", OR_New_NSFOnline.getProperty("Audits_Audit_Info_Tab_ByDefault_Opened"));
		
		fnsGet_Element_Display_byPassing_ElementName_and_Xpath_WithoutOR("Audit Info Tab", "'Audit Report' link" ,OR_New_NSFOnline.getProperty("Audits_Audit_Info_Tab_Audit_Report_button"));
		fnsGet_Element_Display_byPassing_ElementName_and_Xpath_WithoutOR("Audit Info Tab", "'CAR Report' link" ,OR_New_NSFOnline.getProperty("Audits_Audit_Info_Tab_CAR_Report_button"));
		
				
		fnsDownload_File_or_Verify_Validation_Message("Audit Report", OR_New_NSFOnline.getProperty("Audits_Audit_Info_Tab_Audit_Report_button"));
		
		fnsDownload_File_or_Verify_Validation_Message("CAR Report", OR_New_NSFOnline.getProperty("Audits_Audit_Info_Tab_CAR_Report_button"));
		
		
		fnsGet_Element_Enabled("Audits_CorrectiveAction_Tab");
		fnsWait_and_Click("Audits_CorrectiveAction_Tab");
		fnsLoading_Progressing_wait(2);
		
				
		for(int i=0; i<=20; i++){
			if(driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("Audits_CorrectiveAction_Tab_ApproverComments"))).size()>0){
				fnsGet_Element_Display_byPassing_ElementName_and_Xpath_WithoutOR("Corrective Action Tab", "'Filter'" ,OR_New_NSFOnline.getProperty("Audits_CorrectiveAction_Tab_ApproverComments"));
				break;
			}else if(driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("NewNsfOnline_Sorry_no_information_available_Message"))).size()>0){
				fnsApps_Report_Logs("PASSED == 'no records found' message is coming into the Corrective ActionsTAB.");
				break;
			}else{
				Thread.sleep(1000);
			}
		}
		
		
		
		Browser_open = true;	
	}catch(Throwable t){
		isTestCasePass = false;
		Browser_open = false;
		driver.quit();
		fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
	
	
}






@Test( priority = 3)
public void Case_1_Approver__Reviewer_Click_on_Alert_ActionPlansPendingApproval_then_Perform_CA_Approve_Reject_AdditionalInfoRequested() throws Throwable{
	fnsApps_Report_Logs("=========================================================================================================================================");
	fnsApps_Report_Logs("#### Case_1_Approver__Reviewer_Click_on_Alert_ActionPlansPendingApproval_then_Perform_CA_Approve_Reject_AdditionalInfoRequested ");
	
	try{
		if(Browser_open == false){
			fnsBrowserLaunchAndLogin(Login_UserName, Login_Password);
			fnsSwitchAcoount_MultiAccess(this.getClass().getSimpleName(), "SwitchAccount_DD");
			fnsLoading_Progressing_wait_AlertLaoder(2);
		}
		
		String AlertName = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Alert_Name_Approver_Reviwer");
		fns_AlertScreen_Click_on_AlertMenu_and_Verify_View_and_Label_Present(true, true, "Site Action Plan", AlertName);
		
		fncClick_on_Approve_or_Respond_button_on_ActionPlanScreen("submitted");
					
		fnsLoading_Progressing_wait(2);
		String CA_screen_URL = driver.getCurrentUrl().toLowerCase().trim();
		
		
		if(CA_screen_URL.contains("checklist")){		
			fnsVerifyScreenNavigation_afterClickingOnElement("'Approve' button", "Default 'Corrective Actions' Tab", OR_New_NSFOnline.getProperty("Checklist_CorrectiveActions_Tab_ByDefault_Opened"));
			fnsDD_value_Select_TagOPTION_DDTypeSelect("Checklist_CorrectiveAction_Tab_Filter_DD", "PENDING APPROVAL", (NewNsfOnline_Element_Max_Wait_Time/2) );
		}else{
			fnsVerifyScreenNavigation_afterClickingOnElement("'Approve' button", "Default 'Corrective Actions' Tab", OR_New_NSFOnline.getProperty("Audits_CorrectiveActions_Tab_ByDefault_Opened"));
			fnsDD_value_Select_TagOPTION_DDTypeSelect("Audits_CorrectiveAction_Tab_Filter_DD", "PENDING APPROVAL", (NewNsfOnline_Element_Max_Wait_Time/2) );
		}

		
		
		fnsLoading_Progressing_wait(2);
		fnsLoading_Progressing_wait(2);
		fncCorrectiveAction_Approve_Rejcet_AddInfo_and_Validate("Approve");
		fncCorrectiveAction_Approve_Rejcet_AddInfo_and_Validate("Reject");
		fncCorrectiveAction_Approve_Rejcet_AddInfo_and_Validate("AddInfo");
		
		
		Browser_open = false;
		driver.quit();
	}catch(Throwable t){
		isTestCasePass = false;
		driver.quit();
		fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
	
	
}




@Test( priority = 4)
public void Case_2_Submitter__Verify_ListedMenu_Coming_on_LandingScreen() throws Throwable{
	fnsApps_Report_Logs("=========================================================================================================================================");
	fnsApps_Report_Logs("#### Case_2_Submitter__Verify_ListedMenu_Coming_on_LandingScreen ");
	//WelComeScreen_Account_Switch_DD = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "WelComeScreen_SelectAccount_DD");
	try{
		Login_UserName = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Submitter_Login_User_Password").split(":")[0].trim();
		Login_Password = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Submitter_Login_User_Password").split(":")[1].trim();
		fnsBrowserLaunchAndLogin(Login_UserName, Login_Password);
		fnsSwitchAcoount_MultiAccess(this.getClass().getSimpleName(), "SwitchAccount_DD");
		fnsLoading_Progressing_wait_AlertLaoder(2);

		fncVerify_Menu_SubMenu_Listed_into_Excel_are_displayed_into_the_application("Menu_Ajax_List_of_Submitter");
		
		Browser_open = true;
	}catch(Throwable t){
		isTestCasePass = false;
		Browser_open = false;
		driver.quit();
		fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
	
	
}
		
		
	
		
@Test( priority = 5)
public void Case_2_Submitter__ClickOn_AuditAjax_OpenAudit_Download_Audit_and_CAR_Reports() throws Throwable{
	fnsApps_Report_Logs("=========================================================================================================================================");
	fnsApps_Report_Logs("#### Case_2_Submitter__ClickOn_AuditAjax_OpenAudit_Download_Audit_and_CAR_Reports ");
	
	try{	
		if(Browser_open == false){
			fnsBrowserLaunchAndLogin(Login_UserName, Login_Password);
			fnsSwitchAcoount_MultiAccess(this.getClass().getSimpleName(), "SwitchAccount_DD");
			fnsLoading_Progressing_wait_AlertLaoder(2);
		}
		
		fncMenuClick_CreateView_for_Site_or_SelectView_fromShared_for_Audit_ByPassing_ScreenName_and_ViewName("Audit", Audit_View_Name);	
		
		
		String ViewFirstRecord_AuditCode_Column_Xpath= OR_New_NSFOnline.getProperty("View_Result_Table")+"/tr[1]/td["+fnsReturn_ColumnNumber_from_TableHeaderXpath_WithoutOR(OR_New_NSFOnline.getProperty("View_Result_TableHeader"), "Audit Code")+"]";
		String Submitter_AuditView_AuditNumber = fnsw_Click_on_Table_Link_by_Passing_Cell_Xpath__and__Return_CellValue(ViewFirstRecord_AuditCode_Column_Xpath);
		
		fnsVerifyScreenNavigation_afterClickingOnElement("'"+Submitter_AuditView_AuditNumber+"' link", "Default 'Audit_Info' Tab", OR_New_NSFOnline.getProperty("Audits_Audit_Info_Tab_ByDefault_Opened"));
		
		

		fnsDownload_File_or_Verify_Validation_Message("Audit Report", OR_New_NSFOnline.getProperty("Audits_Audit_Info_Tab_Audit_Report_button"));
		
		fnsDownload_File_or_Verify_Validation_Message("CAR Report", OR_New_NSFOnline.getProperty("Audits_Audit_Info_Tab_CAR_Report_button"));
		

		fnsGet_Element_Enabled("Audits_CorrectiveAction_Tab");
		fnsWait_and_Click("Audits_CorrectiveAction_Tab");
		fnsLoading_Progressing_wait(2);
		
		for(int i=0; i<=20; i++){
			if(driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("Audits_CorrectiveAction_Tab_Filter_DD"))).size()>0){
				fnsGet_Element_Display_byPassing_ElementName_and_Xpath_WithoutOR("Corrective Action Tab", "'Filter'" ,OR_New_NSFOnline.getProperty("Audits_CorrectiveAction_Tab_Filter_DD"));
				break;
			}else if(driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("NewNsfOnline_Sorry_no_information_available_Message"))).size()>0){
				fnsApps_Report_Logs("PASSED == 'no records found' message is coming into the Corrective ActionsTAB.");
				break;
			}else{
				Thread.sleep(1000);
			}
		}
		
		Browser_open = true;
	}catch(Throwable t){
		isTestCasePass = false;
		Browser_open = false;
		driver.quit();
		fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
	
	
}




@Test( priority = 6)
public void Case_2_Submitter__Click_on_Alert_OpenAudit_then_Click_Respond_for_Pending_CA_and_SubmitAll() throws Throwable{
	fnsApps_Report_Logs("=========================================================================================================================================");
	fnsApps_Report_Logs("#### Case_2_Submitter__Click_on_Alert_OpenAudit_then_Click_Respond_for_Pending_CA_and_SubmitAll");
	try{
		if(Browser_open == false){
			fnsBrowserLaunchAndLogin(Login_UserName, Login_Password);
			fnsSwitchAcoount_MultiAccess(this.getClass().getSimpleName(), "SwitchAccount_DD");
			fnsLoading_Progressing_wait_AlertLaoder(2);
		}
		
		String AlertName = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Alert_Name_Submitter");
		fns_AlertScreen_Click_on_AlertMenu_and_Verify_View_and_Label_Present(true, true, "Site Action Plan", AlertName);
		
				
		Integer Pending_Car_Count = fncClick_on_Approve_or_Respond_button_on_ActionPlanScreen("pending");
		
		fnsLoading_Progressing_wait(3);
		String CA_screen_URL = driver.getCurrentUrl().toLowerCase().trim();
		
		
		if(CA_screen_URL.contains("checklist")){
			fnsVerifyScreenNavigation_afterClickingOnElement("'Respond' button", "Default 'Corrective Actions' Tab", OR_New_NSFOnline.getProperty("Checklist_CorrectiveActions_Tab_ByDefault_Opened"));
			
			fnsDD_value_Select_TagOPTION_DDTypeSelect("Checklist_CorrectiveAction_Tab_Filter_DD", "PENDING" , (NewNsfOnline_Element_Max_Wait_Time/2));
			fnsLoading_Progressing_wait(2);
			
			fnsGet_Element_Enabled("Audits_CorrectiveAction_Tab_First_Respond_Button");
			fnsWait_and_Click("Audits_CorrectiveAction_Tab_First_Respond_Button");
			fnsLoading_Progressing_wait(2);
			
			fnsVerifyScreenNavigation_afterClickingOnElement("'Respond' button", "Respond screen", OR_New_NSFOnline.getProperty("CA_RespondBttn_Save_button"));
			
			
			Login_UserName = "submitter_checklist"; // Added only to reuse existing function
			//BS_03_CheckList.fncCAR_Respond_Details_Screen_FieldsList_and_Type();
			BS_03_CheckList.fncAfter_RespondbuttonClik_FillAllTheManadtoryField_and_UploadFile("", 0, Login_UserName);
			
			
			/*fnsGet_Element_Enabled("CA_RespondBttn_Save_button");
			fnsWait_and_Click("CA_RespondBttn_Save_button");
			try{
				fns_Verify_Success_message_coming_OR_Error_message_Coming(false, 10,  "save successful", 10);
			}catch(Throwable t){
				fnsLoading_Progressing_wait(1);
			}
			fnsLoading_Progressing_wait(2);*/
			
			
			fnsGet_Element_Enabled("CA_RespondBttn_Submit_button");
			fnsWait_and_Click("CA_RespondBttn_Submit_button");
			fns_Verify_Success_message_coming_OR_Error_message_Coming(true, 60,  "Corrective Action Submitted Successfully", 25);
			
			
			
		}else{
			fnsVerifyScreenNavigation_afterClickingOnElement("'Respond' button", "Default 'Corrective Actions' Tab", OR_New_NSFOnline.getProperty("Audits_CorrectiveActions_Tab_ByDefault_Opened"));
			
			
			fnsGet_Element_Display_byPassing_ElementName_and_Xpath_WithoutOR("Corrective Action Tab", "'Submit All' button" ,OR_New_NSFOnline.getProperty("Audits_CorrectiveAction_Tab_SubmitAll_button"));
					
	
			fnsDD_value_Select_TagOPTION_DDTypeSelect("Audits_CorrectiveAction_Tab_Filter_DD", "PENDING" , (NewNsfOnline_Element_Max_Wait_Time/2));
			fnsLoading_Progressing_wait(2);
			
			fnsGet_Element_Enabled("Audits_CorrectiveAction_Tab_First_Respond_Button");
			fnsWait_and_Click("Audits_CorrectiveAction_Tab_First_Respond_Button");
			fnsLoading_Progressing_wait(5);
			
			
			fnsVerifyScreenNavigation_afterClickingOnElement("'Respond' button", "Respond screen", OR_New_NSFOnline.getProperty("CA_RespondBttn_COMPLETIONDATE_Text"));
					
			try{			
				assert ( driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("Audits_CorrectiveAction_Tab_SubmitAll_button"))).size()==0 ):"FAILED == 'ShowAll' button is coming on CA respond screen, As Expected it should not display, please refer the screen shot >> ShowAll_button_display"+fnsScreenShot_Date_format();
				fnsApps_Report_Logs("PASSED == 'ShowAll' button is NOT coming on CA respond screen.");
			}catch(Throwable t){
				fnsTake_Screen_Shot("ShowAll_button_display");
				throw new Exception (Throwables.getStackTraceAsString(t));
			}
			
			
			for(int CarPage=1; CarPage<=Pending_Car_Count; CarPage++){
				try{
					BS_03_CheckList.fncCAR_Respond_Details_Screen_FieldsList_and_Type(Login_UserName);
					
					//if ( (CarPage<Pending_Car_Count) ){//Commented as it is not working if other screen will open instead of screen one.
					if ( (CarPage<Pending_Car_Count) && (fnsGet_OR_New_NSFOnline_ObjectX("CA_RespondBttn_Save_nd_Next_button").isDisplayed()) ){
						fnsGet_Element_Enabled("CA_RespondBttn_Save_nd_Next_button");
						fnsWait_and_Click("CA_RespondBttn_Save_nd_Next_button");
						try{
							fns_Verify_Success_message_coming_OR_Error_message_Coming(false, 10,  "save successful", 10);
						}catch(Throwable t){
							fnsLoading_Progressing_wait(1);
						}
						fnsLoading_Progressing_wait(2);
						
					} else if (CarPage==Pending_Car_Count && (fnsGet_OR_New_NSFOnline_ObjectX("CA_RespondBttn_Save_nd_Next_button_Disabled").isDisplayed()) ){
						fnsGet_Element_Enabled("CA_RespondBttn_Save_button");
						fnsWait_and_Click("CA_RespondBttn_Save_button");
						try{
							fns_Verify_Success_message_coming_OR_Error_message_Coming(false, 10,  "save successful", 10);
						}catch(Throwable t){
							fnsLoading_Progressing_wait(1);
						}
						fnsLoading_Progressing_wait(2);
					} else if (CarPage==Pending_Car_Count){
						fnsTake_Screen_Shot("SaveNext_Display_on_LastScreen");
						throw new Exception("FAILED == Save & Next button is displayed on <"+CarPage+"> last screen. As excepted it should not be display, please refer the screen shot >>SaveNext_Display_on_LastScreen"+fnsScreenShot_Date_format());
					}
				}catch(Throwable t){
					throw new Exception("Respond screen >>>> CorrectiveActions save is getting fail on screen <"+CarPage+"> : "+Throwables.getStackTraceAsString(t));
				}
			}
			
			
			fnsGet_Element_Enabled("NewNsfOnline_Back_Bttn");
			fnsWait_and_Click("NewNsfOnline_Back_Bttn");
			fnsLoading_Progressing_wait(5);
				
			fnsVerifyScreenNavigation_afterClickingOnElement("'Back' button", "CA Details Save screen", OR_New_NSFOnline.getProperty("Audits_CorrectiveAction_Tab_SubmitAll_button"));
			fnsWait_and_Click("Audits_CorrectiveAction_Tab_SubmitAll_button");
					
			
			fns_Verify_Success_message_coming_OR_Error_message_Coming(true, 60,  "Corrective Action(s) submitted for Approval", 25);
			/*fncVerify_Success_or_Error_Message_on_ModelPopup_Generation(true, NewNsfOnline_Element_Max_Wait_Time,"Corrective Action(s) submitted for Approval", 25);
			
			fnsGet_Element_Enabled("Model_Popup_OK_Bttn");
			fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
			fnsWait_and_Click("Model_Popup_OK_Bttn");
			fnsLoading_Progressing_wait(2);*/
			
			fnsApps_Report_Logs("PASSED == '"+Pending_Car_Count+"' CARs are Responded.");
			
		}
	}catch(Throwable t){
		isTestCasePass = false;
		fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
	
	
}




//############################################################################## Class Functions ##############################################################

public void fncVerify_Menu_SubMenu_Listed_into_Excel_are_displayed_into_the_application(String Menu_List_Column_Name_from_Excel) throws Throwable{
	try{
		ArrayList<String> Runtime_MenuAjax_List_from_site = new ArrayList<String>();
		ArrayList<String> MenuAjax_List_from_Excel = new ArrayList<String>();
		ArrayList<String> SubMenu_Ajax_List_from_SiteMenu = new ArrayList<String>();
		if( fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), Menu_List_Column_Name_from_Excel).contains(",") ){
			for(int i=0; i<fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), Menu_List_Column_Name_from_Excel).split(",").length; i++){
				MenuAjax_List_from_Excel.add(fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), Menu_List_Column_Name_from_Excel).split(",")[i].trim());
			}
		}else{
			MenuAjax_List_from_Excel.add(fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), Menu_List_Column_Name_from_Excel).trim() );
		}
		
		boolean Menu_from_SiteList_Found_into_the_EXcel = false;
		boolean SubMenu_from_SiteList_Found_into_the_EXcel = false;
		fnsLoading_Progressing_wait(2);
		fnsGet_Element_Enabled("Main_Menu_Ajax_Link");
		WebElement MainMenu = fnsGet_OR_New_NSFOnline_ObjectX("Main_Menu_Ajax_Link");
		Actions action = new Actions(driver);
		action.moveToElement(MainMenu).click().build().perform();
		Thread.sleep(500);
		fnsGet_Element_Enabled("Menu_Bar_Ajax_List");
		List<WebElement> MenuBarAjax_Menu_Links_Objects = fnsGet_OR_New_NSFOnline_ObjectX("Menu_Bar_Ajax_List").findElements(By.tagName("a"));
		for(WebElement MenuBarAjax_Menu_Links_Elements : MenuBarAjax_Menu_Links_Objects){
			Menu_from_SiteList_Found_into_the_EXcel = false;
			String Menu_Link_Text = MenuBarAjax_Menu_Links_Elements.getText().trim();
			if(Menu_Link_Text.length()>1){
				for(int i=0; i<MenuAjax_List_from_Excel.size(); i++){
					if( (MenuAjax_List_from_Excel.get(i).toLowerCase().trim()).contains(Menu_Link_Text.toLowerCase()) ){
						String Link_Href_Text = MenuBarAjax_Menu_Links_Elements.getAttribute("href").toLowerCase().trim();
						
						if(Link_Href_Text.contains("javascript:void(0)")){
							SubMenu_Ajax_List_from_SiteMenu.add(Menu_Link_Text);
							MenuBarAjax_Menu_Links_Elements.click();
							Thread.sleep(500);
							String Menu_Having_SubMenu_Xpath = "//a[@href='"+Link_Href_Text+"']/following-sibling::ul";
							List<WebElement> SubMenuBarAjax_Menu_Links_Objects = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(Menu_Having_SubMenu_Xpath).findElements(By.tagName("a"));
							for(WebElement SubMenuBarAjax_Menu_Links_Elements : SubMenuBarAjax_Menu_Links_Objects){
								SubMenu_from_SiteList_Found_into_the_EXcel = false;
								String SubMenu_Link_Text = SubMenuBarAjax_Menu_Links_Elements.getText().trim();
								if(SubMenu_Link_Text.length()>1){
									for(int j=0; j<MenuAjax_List_from_Excel.size(); j++){
										if( (MenuAjax_List_from_Excel.get(j).toLowerCase().trim()).contains(SubMenu_Link_Text.toLowerCase()) ){
											Runtime_MenuAjax_List_from_site.add(SubMenu_Link_Text);
											SubMenu_from_SiteList_Found_into_the_EXcel = true;
											break;
										}
									}	
								}
								if(SubMenu_Link_Text.equals("")){
									//nothing to do
								} else if(SubMenu_from_SiteList_Found_into_the_EXcel == false){
									fnsTake_Screen_Shot("Out_Listed_Sub_Menu_Display");
									throw new Exception("FAILED == < "+SubMenu_Link_Text+" > Sub Menu under ' "+Menu_Link_Text+" ' Menu is not mentioned into the Excel, please refer the screen shot >> Out_Listed_Sub_Menu_Display"+fnsScreenShot_Date_format());
								}	
							}		
							MenuBarAjax_Menu_Links_Elements.click();
							Thread.sleep(500);
						}
						Runtime_MenuAjax_List_from_site.add(Menu_Link_Text);
						Menu_from_SiteList_Found_into_the_EXcel = true;
						break;
					}
				}
			}
			if(Menu_Link_Text.equals("")){
				//nothing to do
			} else if(Menu_from_SiteList_Found_into_the_EXcel == false){
				fnsTake_Screen_Shot("Out_Listed_Menu_Display");
				throw new Exception("FAILED == ' "+Menu_Link_Text+" ' Menu coming into the application is not mentioned into the Excel, please refer the screen shot >> Out_Listed_Menu_Display"+fnsScreenShot_Date_format());
			}
		}
		
		if ( (Runtime_MenuAjax_List_from_site.size())== (MenuAjax_List_from_Excel.size()) ) {
			Thread.sleep(2000);
			fnsWait_Click_on_First_Visible_Element_if_More_than_One_Coming("Menu logo image", "//ul[@id='menubarheight']/li/span/img[contains(@src, 'logo.png')]");
			//action.moveToElement(MainMenu).click().build().perform();//Not Working as MainMenu Element cover under menu popup 23.2.2018
			Thread.sleep(2000);
			fnsLoading_Progressing_wait(2);
			fnsLoading_Progressing_wait_AlertLaoder(10); // 10 seconds is temp.
			fnsApps_Report_Logs("PASSED == *************************  Successfully verified that all "+MenuAjax_List_from_Excel+" menu and sub menu are displayed.");
		}else{
			boolean MenuAjax_List_from_Excel_found_into_the_SiteMenu_List = false;
			for(int exl=0; exl<MenuAjax_List_from_Excel.size(); exl++){
				MenuAjax_List_from_Excel_found_into_the_SiteMenu_List = false;
				if(Runtime_MenuAjax_List_from_site.contains(MenuAjax_List_from_Excel.get(exl))){
					MenuAjax_List_from_Excel_found_into_the_SiteMenu_List = true;
				}
				if(MenuAjax_List_from_Excel_found_into_the_SiteMenu_List == false){
					fnsTake_Screen_Shot("Menu_Not_Display_into_the_Application");
					throw new Exception("FAILED == ' "+MenuAjax_List_from_Excel.get(exl)+" ' listed Menu (mentioned into the Excel) is not displayed into the application, please refer the screen shot >> Menu_Not_Display_into_the_Application"+fnsScreenShot_Date_format());
				}
			}
			if(MenuAjax_List_from_Excel_found_into_the_SiteMenu_List==true){
				throw new Exception ("FAILED == One of the menu is mentioned 2 times into the excel, please correct it.");
			}
		}		
		
/*		fnsWait_Click_on_First_Visible_Element_if_More_than_One_Coming("logo image on Menu Bar", "//ul[@id='menubarheight']/li/span/img[contains(@src, 'logo.png')]");		
		fnsLoading_Progressing_wait(2);
		fnsLoading_Progressing_wait_AlertLaoder(2);*/
	}catch(Throwable t){
		isTestCasePass = false;
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
	
}



public void fncMenuClick_CreateView_for_Site_or_SelectView_fromShared_for_Audit_ByPassing_ScreenName_and_ViewName(String ScreenName, String ViewName ) throws Throwable{
	try{
		fnsLoading_Progressing_wait_AlertLaoder(2);
		try{
			if(ScreenName.toLowerCase().trim().equals("site")){
				fncSub_Menu_Ajax_Link_Click_By_PassingAjaxPath("Sites_Ajax", "Sites_SubMenu_SearchSites_Ajax");
			}else if( (ScreenName.toLowerCase().trim().equals("audit")) ){
				fncSub_Menu_Ajax_Link_Click_By_PassingAjaxPath("Sites_Ajax", "Sites_SubMenu_Audits_Ajax");
			}
		}catch(Throwable t){
			fnsLoading_Progressing_wait(2);
			fnsLoading_Progressing_wait_AlertLaoder(2);
			fnsGet_Element_Enabled("Main_Menu_Ajax_Link");
			WebElement MainMenu = fnsGet_OR_New_NSFOnline_ObjectX("Main_Menu_Ajax_Link");
			Actions action = new Actions(driver);
			action.moveToElement(MainMenu).click().build().perform();
			fnsLoading_Progressing_wait_AlertLaoder(2);
			if(ScreenName.toLowerCase().trim().equals("site")){
				fncSub_Menu_Ajax_Link_Click_By_PassingAjaxPath("Sites_Ajax", "Sites_SubMenu_SearchSites_Ajax");
			}else if( (ScreenName.toLowerCase().trim().equals("audit"))  ){
				fncSub_Menu_Ajax_Link_Click_By_PassingAjaxPath("Sites_Ajax", "Sites_SubMenu_Audits_Ajax");
			}			
		}
		
		if(ScreenName.toLowerCase().trim().equals("site")){
			fncNavigation_Verify_Application_Navigated_To_ViewScreen("'Sites' Menu Ajax", "Sites");
		}else if( (ScreenName.toLowerCase().trim().equals("audit")) ){
			fncNavigation_Verify_Application_Navigated_To_ViewScreen("'Audit' Menu Ajax", "Audits");
		}
		
		fncVerify_View_Display_Open_and_Delete_it(2, ViewName, "View_Delete_Link", "View_Remove_Link");
		
		fnsGet_Element_Enabled("CreateNewView_Link");
		fnsWait_and_Click("CreateNewView_Link");
		fnsLoading_Progressing_wait(2);
		
		fnsGet_Element_Enabled("CreateView_Step1_ViewName");
		fnsLoading_Progressing_wait(2);
		fnsWait_and_Type("CreateView_Step1_ViewName", ViewName);
		fnsLoading_Progressing_wait(2);		
		
		if(ScreenName.toLowerCase().trim().equals("site")){
			fnsWait_and_Type("CreateView_Step2_Site_Code", Site_number);
		}else if( (ScreenName.toLowerCase().trim().equals("audit")) ){
			fnsWait_and_Type("CreateView_Step2_AuditNumber", CreateView_AuditNo_having_CA);
		}
		
		fnsGet_Element_Enabled("CreateView_CreateView_Bttn");
		fnsWait_and_Click("CreateView_CreateView_Bttn");
			
		fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  60, "A new view has been added to your list", 25);
		fnsLoading_Progressing_wait(2);
	
		
		fnsVerifyScreenNavigation_afterClickingOnElement("'Create View' button", ScreenName, OR_New_NSFOnline.getProperty("CreateNewView_Link"));
								
		fncMyView_SharedView_NewlyCreatedView_Display_and_Conditional_Click(2, true, ViewName, "Yes");
		
	
		
	}catch(Throwable t){
		isTestCasePass = false;
		throw new Exception(Throwables.getStackTraceAsString(t));
	}

}


public void fncCorrectiveAction_Approve_Rejcet_AddInfo_and_Validate(String ButtonNameText) throws Throwable{
	try{
		
		try{
			fnsGet_Element_Enabled_By_Passing_Wait_and_Screenshot_Condition(10, true, "Audits_CorrectiveAction_Tab_First_CheckBox");
		}catch(Throwable t){
			throw new Exception("FAILED == 'No Record are availble' into the corrective action tab, after selecting <PENDING APPROVAL> from filter, please refer screen shot >> Audits_CorrectiveAction_Tab_First_CheckBox"+Throwables.getStackTraceAsString(t));
		}
				
		fnsWait_and_Click("Audits_CorrectiveAction_Tab_First_CheckBox");
		fnsLoading_Progressing_wait(1);
			
		fnsGet_Element_Enabled("Audits_CorrectiveAction_Tab_ApproverComments");
		
		if(ButtonNameText.toLowerCase().trim().contains("approve")){		
			fnsWait_and_Type("Audits_CorrectiveAction_Tab_ApproverComments", "Automation > Car - Approved");
			fnsWait_Click_on_First_Visible_Element_if_More_than_One_Coming("Approve Button", OR_New_NSFOnline.getProperty("Audits_CorrectiveAction_Tab_Approve_button"));
			fns_Verify_Success_message_coming_OR_Error_message_Coming(true, 60,  "Corrective Action(s) Approved Successfully", 25);
			//fncVerify_Success_or_Error_Message_on_ModelPopup_Generation(true, NewNsfOnline_Element_Max_Wait_Time,"Corrective Action(s) Approved Successfully", 25);
		}else if(ButtonNameText.toLowerCase().trim().contains("reject")){
			fnsWait_and_Type("Audits_CorrectiveAction_Tab_ApproverComments", "Automation > Car - Rejected");
			fnsWait_Click_on_First_Visible_Element_if_More_than_One_Coming("Reject Button", OR_New_NSFOnline.getProperty("Audits_CorrectiveAction_Tab_Reject_button"));
			fns_Verify_Success_message_coming_OR_Error_message_Coming(true, 60,  "Corrective Action(s) Rejected Successfully", 25);
			//fncVerify_Success_or_Error_Message_on_ModelPopup_Generation(true, NewNsfOnline_Element_Max_Wait_Time,"Corrective Action(s) Rejected Successfully", 25);
		}else if(ButtonNameText.toLowerCase().trim().contains("addinfo")){
			fnsWait_and_Type("Audits_CorrectiveAction_Tab_ApproverComments", "Automation > Car - Additional Info Requested");
			fnsWait_Click_on_First_Visible_Element_if_More_than_One_Coming("ADDL-INFO-RQSTD Button", OR_New_NSFOnline.getProperty("Audits_CorrectiveAction_Tab_ADDL_INFO_RQSTD_button"));
			fns_Verify_Success_message_coming_OR_Error_message_Coming(true, 60,  "Additional Info Requested Successfully", 25);
			//fncVerify_Success_or_Error_Message_on_ModelPopup_Generation(true, NewNsfOnline_Element_Max_Wait_Time,"Additional Info Requested Successfully", 25);
		}
		/*fnsLoading_Progressing_on_Popup_wait_for_Popup(1);		
		fnsGet_Element_Enabled("Model_Popup_OK_Bttn");
		fnsLoading_Progressing_on_Popup_wait_for_Popup(1);
		fnsWait_and_Click("Model_Popup_OK_Bttn");
		fnsLoading_Progressing_on_Popup_wait_for_Popup(2);*/
		
	}catch(Throwable t){
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
}





public Integer fncClick_on_Approve_or_Respond_button_on_ActionPlanScreen(String CarStatus_Filter_Search_Value) throws Throwable{
	try{
		fnsFilter_ApplyFilter_and_Return_Total_Filtered_Record_Count("CAR Status : ", "CAR_STATUS", CarStatus_Filter_Search_Value.toUpperCase());
		fnsLoading_Progressing_wait(2);			
		
		Integer CarStatus_Column_Number= fnsReturn_ColumnNumber_from_TableHeaderXpath_WithoutOR(OR_New_NSFOnline.getProperty("View_Result_TableHeader"), "CAR Status");
		Integer Car_Count = 0;
		boolean Car_Found = false;
		for(int i=1; i<=20; i++){
			String CarStatus_Column_Xpath= OR_New_NSFOnline.getProperty("View_Result_Table")+"/tr["+i+"]/td["+CarStatus_Column_Number+"]";
			String CarStatus_Column_Text = (TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(CarStatus_Column_Xpath).getText().toLowerCase()).trim();
			if(CarStatus_Column_Text.contains(",")){
				for(int s=0; s<CarStatus_Column_Text.split(",").length; s++){
					if(CarStatus_Column_Text.split(",")[s].contains(CarStatus_Filter_Search_Value.toLowerCase())){
						String Pending_Car_Count_Text = CarStatus_Column_Text.split(",")[s].split(CarStatus_Filter_Search_Value.toLowerCase())[0].trim();
						Car_Count = Integer.parseInt(Pending_Car_Count_Text);						
						break;
					}
				}				
			}else if(CarStatus_Column_Text.contains(CarStatus_Filter_Search_Value.toLowerCase())){
				String Pending_Car_Count_Text = CarStatus_Column_Text.split(CarStatus_Filter_Search_Value.toLowerCase())[0].trim();
				Car_Count = Integer.parseInt(Pending_Car_Count_Text);						
			}
			
			if(Car_Count>2 && CarStatus_Filter_Search_Value.equalsIgnoreCase("submitted")){ 
				Car_Found = true;
			}
			if(Car_Count>0 && CarStatus_Filter_Search_Value.equalsIgnoreCase("pending")){ 
				Car_Found = true;
			}
			if(Car_Found == true){
				String Button_Xpath = null;
				if(CarStatus_Filter_Search_Value.toLowerCase().contains("submitted")){
					Button_Xpath = "("+OR_New_NSFOnline.getProperty("ActionPlan_View_First_Approve_bttn")+")["+i+"]";
				}else if(CarStatus_Filter_Search_Value.toLowerCase().contains("pending")){
					Button_Xpath = "("+OR_New_NSFOnline.getProperty("ActionPlan_View_First_Respond_bttn")+")["+i+"]";
				}				
				
				TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(Button_Xpath);
				TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(Button_Xpath);
				fnsLoading_Progressing_wait(2);
				fnsLoading_Progressing_wait(2);
				break;
			}
			
			if(i==20 && Car_Found == false){
				fnsTake_Screen_Shot(CarStatus_Filter_Search_Value.toUpperCase()+"_Car_Not_Found");
				throw new Exception("FAILED == No Action Plan is found with '"+CarStatus_Filter_Search_Value.toUpperCase()+"' car, please refer the screen shot >> "+CarStatus_Filter_Search_Value.toUpperCase()+"_Car_Not_Found"+fnsScreenShot_Date_format());
			}
		}
		return Car_Count;
	}catch(Throwable t){
		throw new Exception(Throwables.getStackTraceAsString(t));
	}	
}


//#######################################################  Configuration Method  ################################################################
@AfterTest
public void reportTestResult() throws Throwable {
	TestSuiteBase_MonitorPlan.fns_ReportTestResult_atClassLevel(New_NSFOnline_Suitexls,  (this.getClass().getSimpleName()) , isTestCasePass);
}


@AfterTest
public void QuitBrowser(){
	driver.quit();
}

}