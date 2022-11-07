package nsf.ecap.New_NSFOnline_Suite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;

public class BS_36_Service_Request extends TestSuiteBase_New_NSFOnline {
	
	
	public String Servive_Request_View_Name_from_AdvanceSearch = "Automation - Service Request";
	public String Subject = "Automation Service Request";
	public String Details = "This is automation Service Request";
	public String Service_Requets_Status_DD ="Waiting for Support";
	public String Service_Request_Add_Comments_Text ="This is automation comments.";
	
	public String Request_NO = "";

	@Parameters({ "className" })
	@BeforeTest
	public void checkTestSkip(String className) throws Exception {
		if (className.isEmpty()) {
				className = this.getClass().getSimpleName();
		}
		RunningClassName = className;
		Running_Class_BS_Description = "[BS-36] Verify Service Request";
		IsBrowserPresentAlready =false;
		fnsCheckClassLevelTestSkip(className);
	}

	@Test( priority = 0)
	public void Launch_Browser_and_Login_WithUser_IntoThe_Application() throws Throwable{
		try{ 
			fnsApps_Report_Logs("################################## "+ Running_Class_BS_Description);
			if (!IsBrowserPresentAlready) {
				Login_Application_Name = "nsf_connect";
				IsBrowserPresentAlready = fnsBrowserLaunchAndLogin(Login_UserName, Login_Password);
				fnsSwitchAcoount_MultiAccess(RunningClassName, "SwitchAccount_DD");	
				
			}
		}catch(Throwable t){
			isTestCasePass = false;
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		fnsLoading_Progressing_wait(1);
		fnsApps_Report_Logs("=========================================================================================================================================");
	}
	
	
	@Test(priority = 1, dependsOnMethods={"Launch_Browser_and_Login_WithUser_IntoThe_Application"}, description="")
	public void Create_New_Service_Request_And_DeleteView_IfAnyExist () throws Exception{		
		fnsApps_Report_Logs("################### Test Case 1:: Create_New_Service_Request_And_DeleteView_IfAnyExist");
		
		try{			
			fncMenu_Ajax_Link_Click_By_PassingAjaxPath("Service_Request_Ajax");
			fnsLoading_Progressing_wait(1);
			fnsLoading_Progressing_wait(1);
			
			fncNavigation_Verify_Application_Navigated_To_ViewScreen("'Service Request' Menu Ajax", "Service Request");
			
			fncVerify_View_Display_Open_and_Delete_it(2, Servive_Request_View_Name_from_AdvanceSearch, "View_Delete_Link", "View_Remove_Link");
			fnsLoading_Progressing_wait(1);
			fnsLoading_Progressing_wait(1);
			
			fnsGet_Element_Enabled("Service_Request_New_Request_button");
			fnsWait_and_Click("Service_Request_New_Request_button");
			fnsLoading_Progressing_wait(1);
			
			fnsGet_Element_Enabled("Service_Request_Subject_Text_Box");
			fnsWait_and_Type("Service_Request_Subject_Text_Box", Subject);
			fnsLoading_Progressing_wait(1);
			
			if(driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("Service_Request_Category_DD"))).size()>0){
				fnsDD_value_Select_TagOPTION_DDTypeSelect("Service_Request_Category_DD", "Feedback", 60);
				fnsLoading_Progressing_wait(1);
			}
						
			fnsGet_Element_Enabled("Service_Request_Details_Text_Box");
			fnsWait_and_Type("Service_Request_Details_Text_Box", Details);
			fnsLoading_Progressing_wait(1);
			
			fnsGet_Element_Enabled("Service_Request_Send_Button_Request");
			fnsWait_and_Click("Service_Request_Send_Button_Request");

			fns_Verify_Success_message_coming_OR_Error_message_Coming(true, 60,  "Your request has been successfully submitted.", 25);
		}catch (Throwable t){
			isTestCasePass = false;
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));
		}		
	}
	
	
	@Test( priority = 2, dependsOnMethods={"Create_New_Service_Request_And_DeleteView_IfAnyExist"}, description="")
	public void Verify_RequestNO_ComingIntoThe_AdvanceSearch_CreateNewView_And_Reply_ServiceRequest () throws Throwable{
		fnsApps_Report_Logs("################### Test Case ::2 Verify_RequestNO_ComingIntoThe_AdvanceSearch_CreateNewView_And_Reply_ServiceRequest");
		
		try{						
			fnsGet_Element_Enabled("View_AdvanceSearch_Bttn");
			fnsWait_and_Click("View_AdvanceSearch_Bttn");
			fnsLoading_Progressing_wait(2);
			fnsLoading_Progressing_wait(2);
			fnsCalendar_Pick_TodayDate_by_LabelName_1(true, true, "Created On", 0);
			fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
			fnsDD_Value_select_by_DDLabelName_MultiselectDD("Status", Service_Requets_Status_DD);
			fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
			fnsGet_Element_Enabled("Audit_CA_AdvanceSearch_Search_Button");
			fnsWait_and_Click("Audit_CA_AdvanceSearch_Search_Button");
			fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
			fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
			
			Integer Service_Request_AdvanceSearch_Total_no_of_Records_Count = fnsReturn_View_Results_Reords_Total_Count("Advance Search" , "View_Result_Table", 10);
			
			fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
			fnsGet_Element_Enabled("AdvanceSearch_SaveAsView");
			fnsWait_and_Click("AdvanceSearch_SaveAsView");
			fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
			
			fnsGet_Element_Enabled("AdvanceSearch_SaveAsView_PopUpBox");
			fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
			fnsWait_and_Click("AdvanceSearch_SaveAsView_PopUpBox");
			fnsLoading_Progressing_on_Popup_wait_for_Popup(2);		
			fnsGet_Element_Enabled("CreateView_Step1_ViewName");
			fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
			fnsWait_and_Clear("CreateView_Step1_ViewName");
			fnsWait_and_Type("CreateView_Step1_ViewName", Servive_Request_View_Name_from_AdvanceSearch);
			fnsLoading_Progressing_wait(1);
			
			//Below lines are  not into the TC but added because the date selected on advance search are coming to the create new view screen. And also unable to replicate manually so added these lines.
			fnsCalendar_Pick_TodayDate_by_LabelName_1(true, true, "Created On", 0);
			fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
			
			fnsGet_Element_Enabled("CreateView_CreateView_Bttn");
			fnsWait_and_Click("CreateView_CreateView_Bttn");
			
			fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  60, "A new view has been added to your list", 25);
			
			Integer Service_Request_Total_no_of_Records_Count = fnsReturn_View_Results_Reords_Total_Count("Product Review" , "View_Result_Table", 20);
			
			try{
				assert Service_Request_Total_no_of_Records_Count.equals(Service_Request_AdvanceSearch_Total_no_of_Records_Count):"FAILED == Advance Search results count <"+Service_Request_AdvanceSearch_Total_no_of_Records_Count+"> and VIEW Created-From-Advance-Search count <"+
						Service_Request_Total_no_of_Records_Count+"> are not matched, please refer screen shot >> Count_Mismatch"+fnsScreenShot_Date_format();
				fnsApps_Report_Logs("PASSED == Advance Search results count <"+Service_Request_AdvanceSearch_Total_no_of_Records_Count+"> and VIEW Created-From-Advance-Search count <"+ Service_Request_Total_no_of_Records_Count +"> are matched.");
			}catch(Throwable t){
				fnsTake_Screen_Shot("Count_Mismatch");
				throw new Exception(Throwables.getStackTraceAsString(t));
			}
		
			
			Request_NO = fnsw_Click_on_Table_Link_by_Passing_Cell_Xpath__and__Return_CellValue(OR_New_NSFOnline.getProperty("View_Result_Table")+"/tr[1]/td["+fnsReturn_ColumnNumber_from_TableHeaderXpath_WithoutOR(OR_New_NSFOnline.getProperty("View_Result_Table_thead"), "Request No")+"]");
			
			fnsLoading_Progressing_wait(2);
			
						
			if(CARespond_KendoEditorDisplayed){
				String iframe_Xpath = OR_New_NSFOnline.getProperty("Service_Request_Add_Comments_Text_Box")+"/preceding::iframe[1]";
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
				WebElement iframe_ele = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(iframe_Xpath);
				driver.switchTo().frame(iframe_ele);
				String iframeBody_Xpath = "//body[@contenteditable='true']";
				TestSuiteBase_MonitorPlan.WithOut_OR_fnType(iframeBody_Xpath, Service_Request_Add_Comments_Text);
				driver.switchTo().defaultContent();
				Thread.sleep(250);	
				fnsLoading_Progressing_wait(2);
			}else {
				fnsGet_Element_Enabled("Service_Request_Add_Comments_Text_Box");
				fnsWait_and_Clear("Service_Request_Add_Comments_Text_Box");
				fnsWait_and_Type("Service_Request_Add_Comments_Text_Box", Service_Request_Add_Comments_Text);
				fnsLoading_Progressing_wait(2);
			}
			
			
			
			
			fnsGet_Element_Enabled("Service_Request_Reply_button");
			fnsWait_and_Click("Service_Request_Reply_button");
			
			fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  60, "Your comment has been successfully posted.", 25);
			
			driver.close();
			
		}catch(Throwable t){
			isTestCasePass = false;
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}

	@Test( priority = 3, dependsOnMethods={"Verify_RequestNO_ComingIntoThe_AdvanceSearch_CreateNewView_And_Reply_ServiceRequest"}, description="")
	public void Verify_LoginWith_SuperAdmin_ReplyServiceRequest_WithoutStatusChange_and_CloseIt () throws Exception{
		fnsApps_Report_Logs("################### Test Case ::3 Verify_LoginWith_SuperAdmin_ReplyServiceRequest_WithoutStatusChange_and_CloseIt");
		try{
			Login_Application_Name = "iPulse_SuperAdminLogin_Button";
			fnsBrowserLaunchAndLogin(Login_UserName, Login_Password);
	
			fnsLoading_Progressing_wait(2);
			fncMenu_Ajax_Link_Click_By_PassingAjaxPath("Service_Request_Ajax");
			fnsLoading_Progressing_wait(1);
		
			fnsGet_Element_Enabled("View_AdvanceSearch_Bttn");
			fnsWait_and_Click("View_AdvanceSearch_Bttn");
			fnsLoading_Progressing_wait(1);
			
			fnsCalendar_Pick_TodayDate_by_LabelName_1(true, true, "Created On", 0);
			
			fnsDD_Value_select_by_DDLabelName_MultiselectDD("Status", Service_Requets_Status_DD);
			
			fnsGet_Element_Enabled("Audit_CA_AdvanceSearch_Search_Button");
			fnsWait_and_Click("Audit_CA_AdvanceSearch_Search_Button");
			fnsLoading_Progressing_wait(1);
			
			fnsFilter_ApplyFilter_and_Return_Total_Filtered_Record_Count("Request No : ", "REQUEST_ID", Request_NO);
			fnsLoading_Progressing_wait(2);	
			
			fnsw_Click_on_Table_Link_by_Passing_Cell_Xpath__and__Return_CellValue(OR_New_NSFOnline.getProperty("View_Result_Table")+"/tr[1]/td["+fnsReturn_ColumnNumber_from_TableHeaderXpath_WithoutOR(OR_New_NSFOnline.getProperty("View_Result_Table_thead"), "Request No")+"]");
			fnsLoading_Progressing_wait(1);
			
			if(CARespond_KendoEditorDisplayed){
				String iframe_Xpath = OR_New_NSFOnline.getProperty("Service_Request_Add_Comments_Text_Box")+"/preceding::iframe[1]";
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
				WebElement iframe_ele = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(iframe_Xpath);
				driver.switchTo().frame(iframe_ele);
				String iframeBody_Xpath = "//body[@contenteditable='true']";
				TestSuiteBase_MonitorPlan.WithOut_OR_fnType(iframeBody_Xpath, Service_Request_Add_Comments_Text);
				driver.switchTo().defaultContent();
				Thread.sleep(250);	
			}else{
				fnsGet_Element_Enabled("Service_Request_Add_Comments_Text_Box");
				fnsWait_and_Clear("Service_Request_Add_Comments_Text_Box");
				fnsWait_and_Type("Service_Request_Add_Comments_Text_Box", Service_Request_Add_Comments_Text);
			}
			
			
			fnsGet_Element_Enabled("Service_Request_Reply_Without_Status_Change_button");
			fnsWait_and_Click("Service_Request_Reply_Without_Status_Change_button");
	
			fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  60, "Your comment has been successfully posted.", 25);
			
			if(CARespond_KendoEditorDisplayed){
				String iframe_Xpath = OR_New_NSFOnline.getProperty("Service_Request_Add_Comments_Text_Box")+"/preceding::iframe[1]";
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
				WebElement iframe_ele = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(iframe_Xpath);
				driver.switchTo().frame(iframe_ele);
				String iframeBody_Xpath = "//body[@contenteditable='true']";
				TestSuiteBase_MonitorPlan.WithOut_OR_fnType(iframeBody_Xpath, Service_Request_Add_Comments_Text);
				driver.switchTo().defaultContent();
				Thread.sleep(250);	
			}else{
				fnsGet_Element_Enabled("Service_Request_Add_Comments_Text_Box");
				fnsWait_and_Clear("Service_Request_Add_Comments_Text_Box");
				fnsWait_and_Type("Service_Request_Add_Comments_Text_Box", Service_Request_Add_Comments_Text);	
			}
			
			fnsGet_Element_Enabled("Service_Request_Close_Request_button");
			fnsWait_and_Click("Service_Request_Close_Request_button");
			
			fnsGet_Element_Enabled("Service_Request_Close_Request_Pop_Up_button");
			fnsWait_and_Click("Service_Request_Close_Request_Pop_Up_button");
			
			fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  60, "Your comment has been successfully posted.", 25);
			
			driver.close();
			try{
				driver.switchTo().window(iPulse_Original_WindowHandle);
				driver.close();
			}catch(Throwable t){
				//nothing to do
			}
		
		}catch(Throwable t){
			isTestCasePass = false;
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));
		}	
	}
	
	
	
	@Test( priority = 4, dependsOnMethods={"Verify_LoginWith_SuperAdmin_ReplyServiceRequest_WithoutStatusChange_and_CloseIt"}, description="")
	public void LoginWithUser_And_Verify_ServiceRequestStatus_And_DeleteView () throws Exception{
		fnsApps_Report_Logs("################### Test Case ::4 LoginWithUser_And_Verify_ServiceRequestStatus_And_DeleteView");
		
		try{	
			Service_Requets_Status_DD ="Closed";
			Login_Application_Name = "nsf_connect";			
			fnsBrowserLaunchAndLogin(Login_UserName, Login_Password);
	
			fncMenu_Ajax_Link_Click_By_PassingAjaxPath("Service_Request_Ajax");
			fnsLoading_Progressing_wait(1);
			
			fnsGet_Element_Enabled("View_AdvanceSearch_Bttn");
			fnsWait_and_Click("View_AdvanceSearch_Bttn");
			fnsLoading_Progressing_wait(1);
			
			fnsCalendar_Pick_TodayDate_by_LabelName_1(true, true, "Created On", 0);
			
			fnsDD_Value_select_by_DDLabelName_MultiselectDD("Status", Service_Requets_Status_DD);
			
			fnsGet_Element_Enabled("Audit_CA_AdvanceSearch_Search_Button");
			fnsWait_and_Click("Audit_CA_AdvanceSearch_Search_Button");
			fnsLoading_Progressing_wait(1);
					
			fnsFilter_ApplyFilter_and_Return_Total_Filtered_Record_Count("Request No : ", "REQUEST_ID", Request_NO);
			fnsLoading_Progressing_wait(2);
			
			fnsw_Click_on_Table_Link_by_Passing_Cell_Xpath__and__Return_CellValue(OR_New_NSFOnline.getProperty("View_Result_Table")+"/tr[1]/td["+fnsReturn_ColumnNumber_from_TableHeaderXpath_WithoutOR(OR_New_NSFOnline.getProperty("View_Result_Table_thead"), "Request No")+"]");
			fnsLoading_Progressing_wait(1);
			
			String TextFetch=fnsGet_Field_TEXT("Service_Request_Status_text").toLowerCase().trim();
			
			try{
				assert TextFetch.equalsIgnoreCase("closed"):"FAILED ==  <"+TextFetch+"> status is NOT displayed into the status field.>, Please refer Screen shot >> Status_Not_Closed"+fnsScreenShot_Date_format();
				fnsApps_Report_Logs("PASSED ==  Successfully Verify that  <"+TextFetch+"> status is displayed into the status field.");
			}catch(Throwable t){
				fnsTake_Screen_Shot("Status_Not_Closed");
				throw new Exception(Throwables.getStackTraceAsString(t));
			}
			
			fncMenu_Ajax_Link_Click_By_PassingAjaxPath("Service_Request_Ajax");
			fnsLoading_Progressing_wait(1);
			
			fncVerify_View_Display_Open_and_Delete_it(2, Servive_Request_View_Name_from_AdvanceSearch, "View_Delete_Link", "View_Remove_Link");
			
		}catch(Throwable t){
			isTestCasePass = false;
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));
		}	
		fnsApps_Report_Logs("=========================================================================================================================================");
	}
	
	
	
//#######################################################  Configuration Method  ################################################################
		
	@AfterTest
		public void reportTestResult() throws Throwable {
			TestSuiteBase_MonitorPlan.fns_ReportTestResult_atClassLevel(New_NSFOnline_Suitexls,  (this.getClass().getSimpleName()) , isTestCasePass);
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






