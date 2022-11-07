package nsf.ecap.New_NSFOnline_Suite;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;

public class BS_24_Users extends TestSuiteBase_New_NSFOnline {
	

	
	public String View_Name = "Automation - ManageUsers";
	public String View_Name_Edited = "Automation - ManageUsers - Edited";
	public String EMail_Address = "automation"+fns_Return_UK_London_Time("ddMM_mmhh",0,0,0,0,0)+"@test.com";
	public String Manage_User_View_Name = "Automation View - " + fnsReturn_CurrentTime();
	public String First_Name = "AutomationFName";
	public String Last_Name = "AutomationLName";
	public String Password = "Welcome123";
	public String Phone_No = fns_Return_UK_London_Time("ddMMyyyy",0,0,0,0,0);
	public String Title = "Automation > BS-24 - "+fns_Return_UK_London_Time("dd MMM yyyy :: hh:mm",0,0,0,0,0);
	
	public String New_Password_Force = "Welcome12345";
	public String Confirm_Password_Force = "Welcome12345";
	public String New_Password_Pop_Up = "Welcome123";
	public String Confirm_Password_Pop_Up = "Welcome123";
	
	

	@Parameters({ "className" })
	@BeforeTest
	public void checkTestSkip(String className) throws Exception {
		if (className.isEmpty()) {
			className = this.getClass().getSimpleName();
		}
		RunningClassName = className;
		Running_Class_BS_Description = "[BS-24] Create, Edit and Delete User";
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
	public void Verify_DataPrePopulated_and_Update_WorkingFine_under_MyProfile_Screen() throws Throwable{
		fnsApps_Report_Logs("################### Test Case ::1 Verify_DataPrePopulated_and_Update_WorkingFine_under_MyProfile_Screen ");
		try{	
			
			fncMenu_Ajax_Link_Click_By_PassingAjaxPath("MyProfile_Ajax");
			fnsLoading_Progressing_wait(1);
			
			fnsVerifyScreenNavigation_afterClickingOnElement("'My Profile' menu", "'My Profile' screen", OR_New_NSFOnline.getProperty("MyProfile_FirstName_input"));
			
			for(int i=0; i<=120; i++){
				try{
					String Input_Entered_Value = driver.findElement(By.xpath(OR_New_NSFOnline.getProperty("MyProfile_FirstName_input"))).getAttribute("value").trim();
					if(Input_Entered_Value.length()>0){
						break;
					}else{
						Thread.sleep(500);
					}
				}catch(NoSuchElementException e){
					isTestCasePass = false;
					fnsTake_Screen_Shot("NoSuchElementException");
					throw new Exception("FAILED == First Name field is not found [User - "+Login_UserName+"], please refer the screenshot >> NoSuchElementException"  + fnsScreenShot_Date_format() );
				}catch(Throwable t){
					if(i==120){
						fnsTake_Screen_Shot("Field_Empty");
						throw new Exception("FAILED == Data is not coming into the 'first Name' field [User - "+Login_UserName+"], after 60 seconds wait, please refer the screenshot >> Field_Empty"+fnsScreenShot_Date_format());
					}else{
						Thread.sleep(500);
					}
				}
			}
			
			fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(false, "input","First Name", "");
			fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(false, "input","Last Name", "");
			fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(false, "input","E-Mail Address", "");
			fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(false, "input","Phone No", "");
			
			
			
			fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(true, "input","E-Mail Address", EMail_Address);
			
			fnsGet_Element_Enabled("MyProfile_Update_bttn");
			fnsWait_and_Click("MyProfile_Update_bttn");
	
			fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  60, "User Updated Successfully", 20);
			
		}catch(Throwable t){
			isTestCasePass = false;
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));
		}	
		fnsApps_Report_Logs("=========================================================================================================================================");
	}


		
	@Test( priority = 2, dependsOnMethods={"Verify_DataPrePopulated_and_Update_WorkingFine_under_MyProfile_Screen"})
	public void Verify_Create_Edit_Delete_View_WorkingFine_under_ManageUsers_screen () throws Throwable{
		fnsApps_Report_Logs("################### Test Case ::2 Verify_Create_Edit_Delete_View_WorkingFine_under_ManageUsers_screen ");
		try{
			
			fncSub_Menu_Ajax_Link_Click_By_PassingAjaxPath("Admin_Ajax", "Admin_SubMenu_ManageUser_Ajax");
			fnsLoading_Progressing_wait(2);
						
			fncNavigation_Verify_Application_Navigated_To_ViewScreen("'Manage User(s)' Menu Ajax", "Manage User(s)");
						
			String View_Exists = fncVerify_View_Display_Open_and_Delete_it(2, View_Name_Edited, "View_Delete_Link", "View_Remove_Link");
			if( (View_Exists.contains("View Not Exists")) ){
				fncVerify_View_Display_Open_and_Delete_it(2, View_Name, "View_Delete_Link", "View_Remove_Link");
			}
					
			fnsGet_Element_Enabled("ManageUser_CreateNewView_Link");
			fnsWait_and_Click("ManageUser_CreateNewView_Link");
			fnsLoading_Progressing_wait(1);
					
			fnsGet_Element_Enabled("CreateView_Step1_ViewName");
			fnsLoading_Progressing_wait(1);
			
			fnsWait_and_Type("CreateView_Step1_ViewName", View_Name);
						
			fnsGet_Element_Enabled("CreateView_CreateView_Bttn");
			fnsWait_and_Click("CreateView_CreateView_Bttn");
					
			fns_Verify_Success_message_coming_OR_Error_message_Coming(true, 60,  "A new view has been added to your list", 25);
			fncNavigation_Verify_Application_Navigated_To_ViewScreen("'CreateView' button", "Manage User(s)");
			
			
			
			fnsGet_Element_Enabled("ManageUser_EditView_Link");
			fnsWait_and_Click("ManageUser_EditView_Link");
			fnsLoading_Progressing_wait(1);
			
			fnsGet_Element_Enabled("CreateView_Step1_ViewName");
			fnsLoading_Progressing_wait(1);
			fnsWait_and_Clear("CreateView_Step1_ViewName");
			Thread.sleep(500);
			
			fnsWait_and_Type("CreateView_Step1_ViewName", View_Name_Edited);
							
			fnsGet_Element_Enabled("CreateView_CreateView_Bttn");
			fnsWait_and_Click("CreateView_CreateView_Bttn");
		
			fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  60, "View has been updated successfully", 25);
			
			fncNavigation_Verify_Application_Navigated_To_ViewScreen("'UpdateView' button", "Manage User(s)");
						
			fncVerify_View_Display_Open_and_Delete_it(2, View_Name_Edited, "ManageUser_DeleteView_Link", "View_Remove_Link");		
		
		
		}catch(Throwable t){
			isTestCasePass = false;
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));
		}	
		fnsApps_Report_Logs("=========================================================================================================================================");
	}
		
	
	@Test( priority = 3, dependsOnMethods={"Verify_Create_Edit_Delete_View_WorkingFine_under_ManageUsers_screen"})
	public void AddNewUser_GivePermissions_and_Verify_RetrievedIntoThe_AdvanceSearch () throws Throwable{
		fnsApps_Report_Logs("################### Test Case ::3 AddNewUser_GivePermissions_and_Verify_RetrievedIntoThe_AdvanceSearch ");	
		try{
				
			fnsGet_Element_Enabled("ManageUser_AddUser_bttn");
			fnsWait_and_Click("ManageUser_AddUser_bttn");
			fnsLoading_Progressing_wait(1);
			
			fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(true, "input", "First Name", First_Name );
			fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(true, "input", "Last Name", Last_Name );
			fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(true, "input", "E-Mail Address", EMail_Address );
			fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(true, "input", "Title", Title );
			fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(true, "input", "Phone No", Phone_No );
			//fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(true, "input", "Password", Password );//not working html value (Text) is changed into ******** - 11.12.2019 NOM-4567
			fnsWait_and_Type("UserSetup_ProfileTab_Password", Password);
			
			//fnsWait_and_Clear("UserSetup_ProfileTab_Password");
			//fnsWait_and_Type("UserSetup_ProfileTab_Password", Password);
			
			
			//Not working on 6.11.2019
			//fnsGet_Element_Enabled("UserSetup_ProfileTab_Next_bttn");
		//	fnsWait_and_Click("UserSetup_ProfileTab_Next_bttn");									
			//fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  60, "User Created Successfully", 20);
			
			boolean Click_and_Message_Verify = false;
			for(int c=1; c<=5; c++){
				try{
					if(fnsGet_OR_New_NSFOnline_ObjectX("UserSetup_ProfileTab_Next_bttn").isDisplayed()){
						fnsWait_and_Click("UserSetup_ProfileTab_Next_bttn");								
					}
					fns_Verify_Success_message_coming_OR_Error_message_Coming(false,  30, "User Created Successfully", 20);
					Click_and_Message_Verify = true;
					break;
				}catch(Throwable t){
					if(c==5){
						fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  2, "User Created Successfully", 20);
					}else{
						Thread.sleep(1000);
					}
				}
			}
			
			if(Click_and_Message_Verify==false){
				fnsTake_Screen_Shot("Functionality_Not_Working");
				throw new Exception("FAILED == Clicking on Next button and message verification is getting fail after 5 attampts, please refer the screen shot >> Functionality_Not_Working"+fnsScreenShot_Date_format());
			}
			
			
			fnsVerifyScreenNavigation_afterClickingOnElement("'Next' button", "'Access' Tab", OR_New_NSFOnline.getProperty("UserSetup_AccessTab_ByDefault_Opened"));
			
			fnsGet_Element_Enabled("UserSetup_AccessTab_Previous_bttn");
			fnsWait_and_Click("UserSetup_AccessTab_Previous_bttn");
			fnsLoading_Progressing_wait(2);
			
			
			fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(false, "input", "First Name", First_Name );
			fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(false, "input", "Last Name", Last_Name );
			fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(false, "input", "E-Mail Address", EMail_Address );
			fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(false, "input", "Title", Title );
			fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(false, "input", "Phone No", Phone_No );
			
			
			fnsGet_Element_Enabled("UserSetup_Access_Tab");
			fnsWait_and_Click("UserSetup_Access_Tab");
			fnsLoading_Progressing_wait(1);
			
			fnsGet_Element_Enabled("UserSetup_AccessTab_Corporate_Radio_bttn");
			fnsWait_and_Click("UserSetup_AccessTab_Corporate_Radio_bttn");
			
			fnsGet_Element_Enabled("UserSetup_AccessTab_Permissions_ManageUsersMenu_CheckBox");
			fnsWait_and_Click("UserSetup_AccessTab_Permissions_ManageUsersMenu_CheckBox");
			
			fnsGet_Element_Enabled("UserSetup_AccessTab_Save_bttn");
			fnsWait_and_Click("UserSetup_AccessTab_Save_bttn");
			
			fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  60, "User Updated Successfully", 20);
			
			boolean User_Groups_Tab_Coming = false;			//Added on 21.7.2020 NOM-5139
			if(driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("UserSetup_UserGroupsTab_UnOpened"))).size()>0){
				User_Groups_Tab_Coming = true;
			}			
			
			fnsGet_Element_Enabled("UserSetup_AccessTab_Next_bttn");
			fnsWait_and_Click("UserSetup_AccessTab_Next_bttn");
			fnsLoading_Progressing_wait(1);	
			
			
			if(User_Groups_Tab_Coming){
				fnsVerifyScreenNavigation_afterClickingOnElement("'Next' button", "'User Group(s)' Tab", OR_New_NSFOnline.getProperty("UserSetup_UserGroupsTab_ByDefault_Opened"));
				fnsGet_Element_Enabled("UserSetup_UserGroupsTab_Next_bttn");
				fnsWait_and_Click("UserSetup_UserGroupsTab_Next_bttn");
				fnsLoading_Progressing_wait(1);				
			}
			
			// added on 31-03-22 NOM-5939
			fnsVerifyScreenNavigation_afterClickingOnElement("'Next' button", "'Dashboard Settings' Tab", OR_New_NSFOnline.getProperty("UserSetup_DashboardTab_ByDefault_Opened"));
			
			fnsGet_Element_Enabled("UserSetup_DashboardTab_Next_bttn");
			fnsWait_and_Click("UserSetup_DashboardTab_Next_bttn");
			fnsLoading_Progressing_wait(1);	
			
					
			fnsVerifyScreenNavigation_afterClickingOnElement("'Next' button", "'Report(s)' Tab", OR_New_NSFOnline.getProperty("UserSetup_ReportsTab_ByDefault_Opened"));
			
			fnsGet_Element_Enabled("UserSetup_ReportsTab_Finish_bttn");
			fnsWait_and_Click("UserSetup_ReportsTab_Finish_bttn");
						
			fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  60, "User Updated Successfully", 20);
			
			fncNavigation_Verify_Application_Navigated_To_ViewScreen("'finish' button", "Manage User(s)");
			
			
			fnsGet_Element_Enabled("ManageUser_AdvanceSearch_bttn");
			fnsWait_and_Click("ManageUser_AdvanceSearch_bttn");
			fnsLoading_Progressing_wait(2);
							
			fnsWait_Click_or_Type_By_LabelName("AdvanceSearch_Searchfields_Top_Div", "User Name", EMail_Address);				
						
			fnsWait_and_Click("ManageUser_AdvancedSearch_Search_Bttn");
			fnsLoading_Progressing_wait(2);
			fnsGet_Element_Enabled("ManageUser_AdvancedSearch_Result_Table");
			TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element(OR_New_NSFOnline.getProperty("ManageUser_AdvancedSearch_Result_Table"));						
			
			String TextFetch=fnsGet_Field_TEXT("ManageUser_AdvancedSearch_Result_Table").toLowerCase().trim();
							
			try{
				assert TextFetch.contains(EMail_Address.toLowerCase().trim()):"FAILED ==  <"+EMail_Address+"> user is NOT displayed into the Advance Search results Table>, Please refer Screen shot >> User_Not_Coming"+fnsScreenShot_Date_format();
				fnsApps_Report_Logs("PASSED ==  Successfully Verify that  <"+EMail_Address+"> user is displayed into the Advance search result.");
			}catch(Throwable t){
				fnsTake_Screen_Shot("User_Not_Coming");
				throw new Exception(Throwables.getStackTraceAsString(t));
			}
			driver.quit();	
			
		}catch(Throwable t){
			isTestCasePass = false;
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));
		}				
		fnsApps_Report_Logs("=========================================================================================================================================");			
	}
	
	
	@Test( priority = 4, dependsOnMethods={"AddNewUser_GivePermissions_and_Verify_RetrievedIntoThe_AdvanceSearch"})
	public void Login_with_NewllyCreatedUser_ForcePasswordChange_And_Verify_CorrectDataRetrieved_Under_MyProfile_Screen () throws Throwable{			
		fnsApps_Report_Logs("################### Test Case ::4 Login_with_NewllyCreatedUser_ForcePasswordChange_And_Verify_CorrectDataRetrieved_Under_MyProfile_Screen ");	
		try{
			fnsBrowserLaunchAndLogin(EMail_Address, Password);
			
			
			fnsWait_and_Type("MyProfile_OldPassword_input", Password);
			fnsWait_and_Type("MyProfile_NewPassword_input", New_Password_Force);
			fnsWait_and_Type("MyProfile_ConfirmPassword_input", Confirm_Password_Force);  //Welcome12345
			fnsLoading_Progressing_wait(1);
			
			fnsGet_Element_Enabled("MyProfile_ChangePassword_button");
			fnsWait_and_Click("MyProfile_ChangePassword_button");
						
			fns_Verify_Success_or_Error_Message_on_ModelPopup_Generation(true, 60, "Password Updated!", 20);
			
			Thread.sleep(1000);
			fnsWait_and_Click("MyProfile_ChangePassword_Confirm_button");
			
			
			
			boolean AcknowlwdgeComing = false;
			for(int i=0; i<=120; i++){
				try{
					if( (driver.findElement(By.xpath(OR_New_NSFOnline.getProperty("NewNsfOnline_Content_Model_Popup"))).isDisplayed()) ){
						fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
						String ErrorMsg = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(OR_New_NSFOnline.getProperty("NewNsfOnline_Content_Model_Popup")).getText().trim();
						if(ErrorMsg.toLowerCase().contains("This is a non-production environment - Do NOT upload sensitive, confidential, NSF or customer data".toLowerCase())){
							fnsApps_Report_Logs("PASSED == Acknowledge Popup is coming.");									
							AcknowlwdgeComing = true;
							break;
						}else{
							throw new Exception("Getting Popup Error <"+ErrorMsg+">"+" , Please refer the screen shot >> LoginFail"+fnsScreenShot_Date_format());
						}
					}else{
						Thread.sleep(1000);
					}
				}catch(Throwable t){
					Thread.sleep(500);
				}								
				if(i==120){
					throw new Exception("Acknowledge Alert Popup is NOT coming just after login through ipulse, Please refer the screen shot >> LoginFail"+fnsScreenShot_Date_format());
				}
			}
			if(AcknowlwdgeComing){
				fnsGet_Element_Enabled("Acknowledge_Acknowledge_bttn");
				fnsWait_and_Click("Acknowledge_Acknowledge_bttn");
				Thread.sleep(2000);
			}
			
			
			
			
			
			
			fns_Verify_and_Return_ScreenName("Alerts", true);
			
			
			
			fncMenu_Ajax_Link_Click_By_PassingAjaxPath("MyProfile_Ajax");
			fnsLoading_Progressing_wait(1);
			fnsVerifyScreenNavigation_afterClickingOnElement("'My Profile' menu", "'My Profile' screen", OR_New_NSFOnline.getProperty("MyProfile_FirstName_input"));
			
			for(int i=0; i<=120; i++){
				try{
					String Input_Entered_Value = driver.findElement(By.xpath(OR_New_NSFOnline.getProperty("MyProfile_FirstName_input"))).getAttribute("value").trim();
					if(Input_Entered_Value.length()>0){
						break;
					}else{
						Thread.sleep(500);
					}
				}catch(NoSuchElementException e){
					isTestCasePass = false;
					fnsTake_Screen_Shot("NoSuchElementException");
					throw new Exception("FAILED == FirstName field is not found [User - "+EMail_Address+"], please refer the screenshot >> NoSuchElementException"  + fnsScreenShot_Date_format() );
				}catch(Throwable t){
					if(i==120){
						fnsTake_Screen_Shot("Field_Empty");
						throw new Exception("FAILED == Data is not coming into the 'first Name' field [User - "+EMail_Address+"], after 60 seconds wait, please refer the screenshot >> Field_Empty"+fnsScreenShot_Date_format());
					}else{
						Thread.sleep(500);
					}
				}
			}
			
			fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(false, "input", "First Name", First_Name );
			fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(false, "input", "Last Name", Last_Name );
			fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(false, "input", "E-Mail Address", EMail_Address );
			fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(false, "input", "Title", Title );
			fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(false, "input", "Phone No", Phone_No );	
			fnsLoading_Progressing_wait(1);
		
		}catch(Throwable t){
			isTestCasePass = false;
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));
		}					
		fnsApps_Report_Logs("=========================================================================================================================================");	
	}
	
	@Test( priority = 5, dependsOnMethods={"Login_with_NewllyCreatedUser_ForcePasswordChange_And_Verify_CorrectDataRetrieved_Under_MyProfile_Screen"})
	public void Change_Password_and_Verify_Login_Successfull_with_New_Password () throws Throwable{			
		fnsApps_Report_Logs("################### Test Case ::5 Change_Password_and_Verify_Login_Successfull_with_New_Password ");	
		try{			
			fnsGet_Element_Enabled("MyProfile_ChangePassword_bttn");
			Thread.sleep(2000);
			fnsWait_and_Click("MyProfile_ChangePassword_bttn");
			fnsLoading_Progressing_wait(3);
			
			fnsWait_and_Type("MyProfile_OldPassword_input", New_Password_Force);
			fnsWait_and_Type("MyProfile_NewPassword_input", New_Password_Pop_Up );
			fnsWait_and_Type("MyProfile_ConfirmPassword_input", Confirm_Password_Pop_Up);
			fnsLoading_Progressing_wait(3);
			
			fnsGet_Element_Enabled("MyProfile_ChangePassword_button");
			fnsWait_and_Click("MyProfile_ChangePassword_button");
			Thread.sleep(3000);			
			fnsWait_and_Click("MyProfile_ChangePassword_Confirm_button");
						
			fns_Verify_Success_or_Error_Message_on_ModelPopup_Generation(true, 60, "Password Updated", 15);
			driver.quit();
						
			fnsBrowserLaunchAndLogin(EMail_Address, New_Password_Pop_Up);
			
			fns_Verify_and_Return_ScreenName("Alerts", true);
			
		
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
		driver.quit();
	}	

}
