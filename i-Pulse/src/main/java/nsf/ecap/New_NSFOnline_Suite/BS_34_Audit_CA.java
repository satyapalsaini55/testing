package nsf.ecap.New_NSFOnline_Suite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;

public class BS_34_Audit_CA extends TestSuiteBase_New_NSFOnline {
	
	public BS_03_CheckList BS_03_CheckList = new BS_03_CheckList();
	public String CreateView_Step2_Status_DD = null;
	public String Audit_CA_View_Name_from_AdvanceSearch = "Automation - Audit CA";
	public String Audit_CA_Status_DD = "Pending Overdue";
	public String CAR_NO = "";
	
	
	
	@Parameters({ "className" })
	@BeforeTest
	public void checkTestSkip(String className) throws Exception {
		if (className.isEmpty()) {
				className = this.getClass().getSimpleName();
		}
		RunningClassName = className;
		Running_Class_BS_Description = "[BS-34] Verify Audit CA"; // renamed to bs-34
		IsBrowserPresentAlready =false;
		fnsCheckClassLevelTestSkip(className);
	}

	@Test( priority = 0)
	public void Launch_Browser_and_Successfully_Login_into_the_Application() throws Throwable{
		try{ 
			fnsApps_Report_Logs("################################## "+ Running_Class_BS_Description);
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
	public void Verify_Submit_Approve_Reject_of_Audit_Corrective_Action() throws Throwable{
		fnsApps_Report_Logs("################### Test Case ::1 Save_And_Submit_Audit_CA ");
		try{	
			fnsLoading_Progressing_wait(5);
			fncMenu_Ajax_Link_Click_By_PassingAjaxPath("AuditCorrectiveActions_Ajax");
			//fnsLoading_Progressing_on_Popup_wait_for_Popup(1);//Commented because it will not capture unknown error. satya
			fnsLoading_Progressing_wait(2);
			
			fncNavigation_Verify_Application_Navigated_To_ViewScreen("'Audit Corr. Action(s)' Menu Ajax", "Audit Corr. Action(s)");
			

			fnsGet_Element_Enabled("View_AdvanceSearch_Bttn");
			fnsWait_and_Click("View_AdvanceSearch_Bttn");
			fnsLoading_Progressing_wait(2);
			fnsGet_Element_Enabled("Audit_CA_AdvanceSearch_Search_Button");
			Thread.sleep(1000);
			
			fnsDD_Value_select_by_DDLabelName_MultiselectDD("Status", Audit_CA_Status_DD);   
			
			fnsGet_Element_Enabled("Audit_CA_AdvanceSearch_Search_Button");
			fnsWait_and_Click("Audit_CA_AdvanceSearch_Search_Button");
			fnsLoading_Progressing_wait(2);
			
			//Integer Audit_CA_AdvanceSearch_Total_no_of_Records_Count = fnsReturn_View_Results_Reords_Total_Count("Advance Search " , "View_Result_Table", 10);			
			//fnsApps_Report_Logs("PASSED == Advance Search results count <"+Audit_CA_AdvanceSearch_Total_no_of_Records_Count+">.");		
			//fncClickonFirstFacilityfromViewResultTable("Audit Corr. Action(s)/Advance Search", "CAR-NO"); //Unwanted method is used here.satya
			
			
			
			fnsReturn_View_Results_Reords_Total_Count("Advance Search " , "View_Result_Table", 10);
			CAR_NO = fnsw_Click_on_Table_Link_by_Passing_Cell_Xpath__and__Return_CellValue(OR_New_NSFOnline.getProperty("View_Result_Table")+"/tr[1]/td["+fnsReturn_ColumnNumber_from_TableHeaderXpath_WithoutOR(OR_New_NSFOnline.getProperty("View_Result_Table_thead"), "CAR-NO")+"]");
			BS_03_CheckList.fncAfter_RespondbuttonClik_FillAllTheManadtoryField_and_UploadFile(CAR_NO, 0, Login_UserName+"_Audit");
			
		
			
			
			//Duplicate code -satya
			/*fnsGet_Element_Enabled("CA_RespondBttn_PREVENTIVEACTIONPLAN_Text");
			fnsWait_and_Type("CA_RespondBttn_PREVENTIVEACTIONPLAN_Text", "Automation - How will you prevent it from happening again?");
			fnsLoading_Progressing_wait(1);
			
			fnsGet_Element_Enabled("CA_RespondBttn_ROOTCAUSEANALYSIS_Text");
			fnsWait_and_Type("CA_RespondBttn_ROOTCAUSEANALYSIS_Text", "Automation - What have you done to resolve the issue?");
			fnsLoading_Progressing_wait(1);
						
			fnsGet_Element_Enabled("CA_RespondBttn_ResponsiblePerson_Text");
			fnsWait_and_Type("CA_RespondBttn_ResponsiblePerson_Text", "Greene King");
			fnsLoading_Progressing_wait(1);
			
			String FileUploadPath = System.getProperty("user.dir") + "\\docs\\This is test pdf document.pdf";
			WebElement Browser = fnsGet_OR_New_NSFOnline_ObjectX("CA_RespondBttn_UploadFiles_browse_input");
			Browser.sendKeys(FileUploadPath);
			
			for(int wait=0; wait<=(NewNsfOnline_Element_Max_Wait_Time); wait++){
				if(driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("Audit_CA_RespondBttn_Upload_button"))).size()>0){
					fnsGet_Element_Enabled("Audit_CA_RespondBttn_Upload_button");
					fnsGet_OR_New_NSFOnline_ObjectX("Audit_CA_RespondBttn_Upload_button").click();
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
			}*/
						
			fnsGet_Element_Enabled("CA_RespondBttn_Submit_button");
			fnsWait_and_Click("CA_RespondBttn_Submit_button");
			fns_Verify_Success_message_coming_OR_Error_message_Coming(true, 120, "Corrective Action Submitted Successfully", 25);
			fnsLoading_Progressing_wait(2);			
			
			fncMenu_Ajax_Link_Click_By_PassingAjaxPath("AuditCorrectiveActions_Ajax");
			//fnsLoading_Progressing_on_Popup_wait_for_Popup(1);//Commented because it will not capture unknown error. satya
			fnsLoading_Progressing_wait(2);
			
			fncNavigation_Verify_Application_Navigated_To_ViewScreen("'Audit Corr. Action(s)' Menu Ajax", "Audit Corr. Action(s)");
			
			fncVerify_View_Display_Open_and_Delete_it(2, Audit_CA_View_Name_from_AdvanceSearch, "View_Delete_Link", "View_Remove_Link");
			
		
			fnsGet_Element_Enabled("View_AdvanceSearch_Bttn");
			fnsWait_and_Click("View_AdvanceSearch_Bttn");
			fnsLoading_Progressing_wait(2);
			fnsGet_Element_Enabled("Audit_CA_AdvanceSearch_Search_Button");
			Thread.sleep(1000);
			
			fnsGet_Element_Enabled("Audit_CA_AdvanceSearch_CAR_No_input");
			fnsWait_and_Type("Audit_CA_AdvanceSearch_CAR_No_input", CAR_NO );
			fnsLoading_Progressing_wait(1);
			
			Audit_CA_Status_DD = "Submitted";
			fnsDD_Value_select_by_DDLabelName_MultiselectDD("Status", Audit_CA_Status_DD); 
				
			fnsGet_Element_Enabled("Audit_CA_AdvanceSearch_Search_Button");
			fnsWait_and_Click("Audit_CA_AdvanceSearch_Search_Button");
			fnsLoading_Progressing_wait(3);
			
			Integer Audit_CA_AdvanceSearch_Total_no_of_Records_Count_for_view = fnsReturn_View_Results_Reords_Total_Count("Advance Search" , "View_Result_Table", 10);			
			
			fnsGet_Element_Enabled("AdvanceSearch_SaveAsView");
			fnsWait_and_Click("AdvanceSearch_SaveAsView");
			fnsLoading_Progressing_on_Popup_wait_for_Popup(3);		
			fnsGet_Element_Enabled("AdvanceSearch_SaveAsView_PopUpBox");
			fnsWait_and_Click("AdvanceSearch_SaveAsView_PopUpBox");
			//fnsLoading_Progressing_wait(1);//commented by satya
			fnsLoading_Progressing_on_Popup_wait_for_Popup(3);		
			fnsGet_Element_Enabled("CreateView_Step1_ViewName");
			Thread.sleep(2000);
			fnsWait_and_Clear("CreateView_Step1_ViewName");
			fnsWait_and_Type("CreateView_Step1_ViewName", Audit_CA_View_Name_from_AdvanceSearch);
			fnsLoading_Progressing_wait(2);
			
			fnsGet_Element_Enabled("CreateView_CreateView_Bttn");
			fnsWait_and_Click("CreateView_CreateView_Bttn");
		
			fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  120, "A new view has been added to your list", 25);
			Integer Audit_CA_Total_no_of_Records_Count = fnsReturn_View_Results_Reords_Total_Count("Audit Corr. Action(s)" , "View_Result_Table", 20);
			
			try{
				assert Audit_CA_Total_no_of_Records_Count.equals(Audit_CA_AdvanceSearch_Total_no_of_Records_Count_for_view):"FAILED == Advance Search results count <"+Audit_CA_AdvanceSearch_Total_no_of_Records_Count_for_view+"> and VIEW Created-From-Advance-Search count <"+
						Audit_CA_Total_no_of_Records_Count+"> are not matched, please refer screen shot >> Count_Mismatch"+fnsScreenShot_Date_format();
				fnsApps_Report_Logs("PASSED == Advance Search results count <"+Audit_CA_AdvanceSearch_Total_no_of_Records_Count_for_view+"> and VIEW Created-From-Advance-Search count <"+ Audit_CA_Total_no_of_Records_Count +"> are matched.");
			}catch(Throwable t){
				fnsTake_Screen_Shot("Count_Mismatch");
				throw new Exception(Throwables.getStackTraceAsString(t));
			}
			
			fnsWait_Click_on_First_Visible_Element_if_More_than_One_Coming("'"+CAR_NO+"' link", "//a[contains(text(), '"+CAR_NO+"')]");
			//driver.findElement(By.linkText(Link_Text)).click();	//commented by satya exception handling missing		
			fnsLoading_Progressing_wait(2);
			
			fnsVerifyScreenNavigation_afterClickingOnElement("'"+CAR_NO+"' link", " 'Car Details Respond' screen", OR_New_NSFOnline.getProperty("Audits_CA_CareDtailsRespond_CarStatus"));
			fns_Assert_equalsIgnoreCase_Without_OR("Text", "Status", OR_New_NSFOnline.getProperty("Audits_CA_CareDtailsRespond_CarStatus"), "Submitted");
			
			/*String Status_button_text = fnsGet_Field_TEXT("Status_button_text"); //commented by satya - duplicate code
					
			try{
				assert Status_button_text.equals("Submitted"):"FAILED == CAR No. "+ Link_Text + " status is not submitted."+fnsScreenShot_Date_format();
				fnsApps_Report_Logs("PASSED == CAR No. "+ Link_Text + " Status is <"+Status_button_text+">.");
			}catch(Throwable t){
				fnsTake_Screen_Shot("Status_Mismatch");
				throw new Exception(Throwables.getStackTraceAsString(t));
			}*/
						
			if(CARespond_KendoEditorDisplayed){
				String iframe_Xpath = OR_New_NSFOnline.getProperty("Audit_CA_Approve_Add_Comment_inputBox")+"/preceding::iframe[1]";
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
				TestSuiteBase_MonitorPlan.WithOut_OR_fnType(iframeBody_Xpath, "Automation Rejected");
				driver.switchTo().defaultContent();
				fnsLoading_Progressing_wait(1);
			}else{
				fnsGet_Element_Enabled("Audit_CA_Approve_Add_Comment_inputBox");
				fnsWait_and_Clear("Audit_CA_Approve_Add_Comment_inputBox");
				fnsWait_and_Type("Audit_CA_Approve_Add_Comment_inputBox", "Automation Rejected");   
				fnsLoading_Progressing_wait(1);
			}
			
			
			
			
			/*fnsGet_Element_Enabled("Audits_CorrectiveAction_Reject_button");
			fnsWait_and_Click("Audits_CorrectiveAction_Reject_button");*/ //
			fnsGet_Element_Enabled("Audits_CA_CareDtailsRespond_Reject_button");
			fnsWait_and_Click("Audits_CA_CareDtailsRespond_Reject_button");
				
			fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  120, "Corrective Action Rejected Successfully", 25);

			fnsGet_Element_Enabled("CA_RespondBttn_Submit_button");
			fnsWait_and_Click("CA_RespondBttn_Submit_button");
						
			fns_Verify_Success_message_coming_OR_Error_message_Coming(true, 120, "Corrective Action Submitted Successfully", 25);
			fnsLoading_Progressing_wait(2);
			
			
			
			
			
			//fncMenu_Ajax_Link_Click_By_PassingAjaxPath("AuditCorrectiveActions_Ajax"); //no need to call the screen through menu if screen already redirected on view screen
			//fnsLoading_Progressing_on_Popup_wait_for_Popup(1);//Commented because it will not capture unknown error. satya
			fnsLoading_Progressing_wait(2);
			
			fncNavigation_Verify_Application_Navigated_To_ViewScreen("'Audit Corr. Action(s)' Menu Ajax", "Audit Corr. Action(s)");
			
			//fncMyView_SharedView_NewlyCreatedView_Display_and_Conditional_Click(2, true, Audit_CA_View_Name_from_AdvanceSearch, "Yes"); //no need to call this function if view is already open. here need to put checkpoint for automation view opened.
			fnsLoading_Progressing_wait(2);
			String SelectedViewName = fnsGet_Field_TEXT("Action_View_Title");
			try{
				assert SelectedViewName.equalsIgnoreCase(Audit_CA_View_Name_from_AdvanceSearch):"FAILED == After submitted the CAR, application is navigated to view screen. But the expected view ("+Audit_CA_View_Name_from_AdvanceSearch+") is not opened by default, please refer screen shot >> Navigation_Fail"+fnsScreenShot_Date_format();
				fnsApps_Report_Logs("PASSED == After submitted the CAR, application is navigated to view screen with expected view opened.");
			}catch(Throwable t){
				fnsTake_Screen_Shot("Navigation_Fail");
				throw new Exception(Throwables.getStackTraceAsString(t));
			}
			
			//fncClickonFirstFacilityfromViewResultTable("Audit Corr. Action(s)", "CAR-NO");
			
			
			fnsWait_Click_on_First_Visible_Element_if_More_than_One_Coming("'"+CAR_NO+"' link", "//a[contains(text(), '"+CAR_NO+"')]");
			fnsLoading_Progressing_wait(2);
				
			fnsVerifyScreenNavigation_afterClickingOnElement("'"+CAR_NO+"' link", " 'Car Details Respond' screen", OR_New_NSFOnline.getProperty("Audits_CA_CareDtailsRespond_CarStatus"));
			fns_Assert_equalsIgnoreCase_Without_OR("Text", "Status", OR_New_NSFOnline.getProperty("Audits_CA_CareDtailsRespond_CarStatus"), "Submitted");
			
			
			if(CARespond_KendoEditorDisplayed){
				String iframe_Xpath = OR_New_NSFOnline.getProperty("Audit_CA_Approve_Add_Comment_inputBox")+"/preceding::iframe[1]";
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
				TestSuiteBase_MonitorPlan.WithOut_OR_fnType(iframeBody_Xpath, "Automation Approved");
				driver.switchTo().defaultContent();
				fnsLoading_Progressing_wait(1);
			}else{
				fnsGet_Element_Enabled("Audit_CA_Approve_Add_Comment_inputBox");
				fnsWait_and_Clear("Audit_CA_Approve_Add_Comment_inputBox");
				fnsWait_and_Type("Audit_CA_Approve_Add_Comment_inputBox", "Automation Approved");  
				fnsLoading_Progressing_wait(1);
			}
			
			
			
			
			fnsGet_Element_Enabled("Audit_CA_CareDtailsRespond_Approve_Button");  
			fnsWait_and_Click("Audit_CA_CareDtailsRespond_Approve_Button");
				
			fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  120, "Corrective Action Approved Successfully", 25);
			fnsLoading_Progressing_wait(2);
			fnsGet_Element_Enabled("Audit_CA_Back_Button");
			fnsWait_and_Click("Audit_CA_Back_Button");				
			fnsLoading_Progressing_wait(2);
			
			fncNavigation_Verify_Application_Navigated_To_ViewScreen("'Audit Corr. Action(s)' Menu Ajax", "Audit Corr. Action(s)");
			
			fncVerify_View_Display_Open_and_Delete_it(2, Audit_CA_View_Name_from_AdvanceSearch, "View_Delete_Link", "View_Remove_Link");
			fnsLoading_Progressing_wait(1);
			
		}catch(Throwable t){
			isTestCasePass = false;
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));
		}

	}

	//duplicate code
/*	public void fncClickonFirstFacilityfromViewResultTable(String View_Home_ScreenName, String Link_Column_Name_From_DataTitle) throws Throwable{
		try{

			Integer Link_Column_Number = fnsReturn_ColumnNumber_from_TableHeaderXpath_WithoutOR(OR_New_NSFOnline.getProperty("View_Result_TableHeader"), Link_Column_Name_From_DataTitle);
			String First_Row_MatchingColumn_Xpath = OR_New_NSFOnline.getProperty("View_Result_Table")+"/tr[1]/td["+Link_Column_Number+"]";
			for(int i=0; i<=NewNsfOnline_Element_Max_Wait_Time; i++){
				if(driver.findElements(By.xpath(First_Row_MatchingColumn_Xpath)).size()>0){
					break;
				}else{
					Thread.sleep(100);
				}
				if(i==NewNsfOnline_Element_Max_Wait_Time){
					throw new Exception ("FAILED == Records are not coming into the view on '"+View_Home_ScreenName+"' screen, please refer screen shot >> Opening_First_Facility_and_ReturnBack_to_View_FAIL"+fnsScreenShot_Date_format()+"--Xpath >> "+First_Row_MatchingColumn_Xpath);
				}
			}
			List<WebElement> First_Row_td_Objects = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(First_Row_MatchingColumn_Xpath).findElements(By.tagName("a"));
			if(First_Row_td_Objects.size()==1){
				for(WebElement First_Row_td_Elements : First_Row_td_Objects){
					Link_Text = First_Row_td_Elements.getText().trim();
					First_Row_td_Elements.click();
					fnsApps_Report_Logs("PASSED == Click done on the '"+Link_Text+"' link");
					fnsLoading_Progressing_wait(2);
					break;
				}
			}else{
				throw new Exception("FAILED == More than one link is present into the cell row 1 of column '"+Link_Column_Name_From_DataTitle+"', please refer screen shot >> Opening_First_Facility_and_ReturnBack_to_View_FAIL"+fnsScreenShot_Date_format());
			}
			
			try{
				for(int i=0; i<=NewNsfOnline_Element_Max_Wait_Time; i++){
					if(driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("ActiveView_DD"))).size()>0){
						Thread.sleep(50);
					}else{
						break;
					}
					if(i==NewNsfOnline_Element_Max_Wait_Time){
						String Facility_Screen_Table_First_Record = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(First_Row_MatchingColumn_Xpath).getText().trim();
						if( !(Link_Text.equals(Facility_Screen_Table_First_Record)) ){
							break;
						}else{
							throw new Exception ("FAILED == After clicking on '"+Link_Text+"' link, Application is not getting navigate to Facility info screen, please refer screen shot >> Opening_First_Facility_to_View_FAIL"+fnsScreenShot_Date_format());
						}
					}
				}
			}catch(Throwable t){
				throw new Exception(Throwables.getStackTraceAsString(t));
			}
		
		}catch (Throwable t){
			//isTestCasePass = false;
			fnsTake_Screen_Shot("Opening_First_Facility_to_View_FAIL");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		
	}	
	*/
	//#######################################################  Configuration Method  #############################################################################
	@AfterTest
	public void reportTestResult() throws Throwable {
		TestSuiteBase_MonitorPlan.fns_ReportTestResult_atClassLevel(New_NSFOnline_Suitexls,  (this.getClass().getSimpleName()), isTestCasePass );
	}
	
	@AfterTest
	public void QuitBrowser() throws Throwable{
		try{
			driver.quit();
		}catch(Throwable t){
			//nothing to do			
		}
}	

}
