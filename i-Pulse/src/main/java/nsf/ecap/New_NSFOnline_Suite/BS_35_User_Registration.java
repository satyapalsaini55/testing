package nsf.ecap.New_NSFOnline_Suite;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;

public class BS_35_User_Registration extends TestSuiteBase_New_NSFOnline{
	
	public String siteUrl = null;
	public String View_Name = "Automation - User Registration";
	public String View_Name_Edited = "Automation - Edited User Registration";
	public String User_Name = "";	
	public String Password = "Welcome123";	
	public String New_Password_Force 	 = "Welcome12345";
	public String Confirm_Password_Force = "Welcome12345";
	
	public String first_Name = "AutomationFName";
	public String last_Name = "AutomationLName";
	public String phone_No = fns_Return_UK_London_Time("mmhhddMMyy",0,0,0,0,0);
	public String email_Address = "auto"+fns_Return_UK_London_Time("ddMMyy_mmhh",0,0,0,0,0)+"@automation.com";
	public String cus_code = "C0036244";
	

	@Parameters({ "className" })
	@BeforeTest
	public void checkTestSkip(String className) throws Exception {
		if (className.isEmpty()) {
				className = this.getClass().getSimpleName();
		}
		RunningClassName = className;
		Running_Class_BS_Description = "[BS-35] Verify User Registration"; 
		IsBrowserPresentAlready =false;
		fnsCheckClassLevelTestSkip(className);
	}
	
	
	@Test( priority = 1)
	public void Open_Registration_Page_And_Complete_Registration_Form() throws Throwable{
		fnsApps_Report_Logs("################################## "+ Running_Class_BS_Description);
		fnsApps_Report_Logs("################### Test Case ::1 Open_Registration_Page_And_Complete_Registration_Form ");
		try{
			TestSuiteBase_MonitorPlan.fns_Launch_Browser_Only();	

			if (excelSiteURL!=null) {
				if (excelSiteURL.equalsIgnoreCase("")) {
					siteUrl = CONFIG.getProperty("New_NSFOnline_SiteName").trim();
				} else {
					siteUrl=excelSiteURL.trim();
				}
			} else {
				siteUrl = CONFIG.getProperty("New_NSFOnline_SiteName").trim();
			}
			fnsApps_Report_Logs("Application URL is [ "+siteUrl+" ]");
			driver.get(siteUrl);
			
			try{
				TestSuiteBase_MonitorPlan.fnsComman_BrowserLogin_ElementWait(OR_New_NSFOnline.getProperty("UserName"), "60");
			}catch(Throwable t){
				fnsTake_Screen_Shot("Login_Screen_Not_Opened");
				throw new Exception(" Application [ "+siteUrl+" ] is not getting open, after 60 seconds wait, please refer the screen shot >> LoginFail"+fnsScreenShot_Date_format());
			}
			Thread.sleep(3000);			
			JavascriptExecutor js = ((JavascriptExecutor) driver);
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			
			fnsGet_Element_Enabled("User_Registration_button");
			fnsWait_and_Click("User_Registration_button");
							
			fnsGet_Element_Enabled("User_Registration_Company_Name_input");
			fnsWait_and_Type("User_Registration_Company_Name_input", "NSF International");
							
			fnsGet_Element_Enabled("User_Registration_First_name_input");
			fnsWait_and_Type("User_Registration_First_name_input", first_Name);
			
			fnsGet_Element_Enabled("User_Registration_Customer_Number_input");
			fnsWait_and_Type("User_Registration_Customer_Number_input", cus_code);
			
			
			fnsGet_Element_Enabled("User_Registration_Last_Name_input");
			fnsWait_and_Type("User_Registration_Last_Name_input", last_Name);
							
			fnsGet_Element_Enabled("User_Registration_Email_Address_input");
			fnsWait_and_Type("User_Registration_Email_Address_input", email_Address);
						
			fnsGet_Element_Enabled("User_Registration_Title_input");
			fnsWait_and_Type("User_Registration_Title_input", "Manager");
							
			fnsGet_Element_Enabled("User_Registration_Re_enter_E_Mail_Address_input");
			fnsWait_and_Type("User_Registration_Re_enter_E_Mail_Address_input", email_Address);
							
			fnsGet_Element_Enabled("User_Registration_Phone_input");
			fnsWait_and_Type("User_Registration_Phone_input", phone_No);
			
			fnsGet_Element_Enabled("User_Registration_Terms_Condition_checkbox");
			fnsWait_and_Click("User_Registration_Terms_Condition_checkbox");
			
			
			fnsGet_Element_Enabled("User_Registration_Register_button");
			fnsWait_and_Click("User_Registration_Register_button");
						
			fns_Verify_Success_or_Error_Message_on_ModelPopup_Generation(true, 60, "User Registration Successful", 15);
			driver.quit();
			
		}catch(Throwable t){
			isTestCasePass = false;
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));
		}		
		fnsApps_Report_Logs("=========================================================================================================================================");
	}
	
	
	
	@Test( priority = 2, dependsOnMethods={"Open_Registration_Page_And_Complete_Registration_Form"}, description="" )
	public void Launch_Browser_and_Successfully_Login_into_the_Application() throws Throwable{
		try{ 
			fnsApps_Report_Logs("################### Test Case ::2 Launch_Browser_and_Successfully_Login_into_the_Application ");
			Login_Application_Name = "iPulse_SuperAdminLogin_Button";
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
	

	
	@Test( priority = 3, dependsOnMethods={"Launch_Browser_and_Successfully_Login_into_the_Application"}, description="")
	public void Verify_Create_Edit_Delete_View_and_Setup_New_User_And_AddNewUser_GivePermissions_From_Registartion() throws Throwable{
		fnsApps_Report_Logs("################### Test Case ::1 Setup_New_User_From_Registartion ");
		try{	
			fncSub_Menu_Ajax_Link_Click_By_PassingAjaxPath("Admin_Ajax", "Admin_SubMenu_User_Registration_Ajax");
			fnsLoading_Progressing_wait(2);
			
			fncNavigation_Verify_Application_Navigated_To_ViewScreen("'User Registration' Menu Ajax", "User Registration");
			

			String View_Exists = fncVerify_View_Display_Open_and_Delete_it(2, View_Name_Edited, "View_Delete_Link", "View_Remove_Link");
			if( (View_Exists.contains("View Not Exists")) ){
				fncVerify_View_Display_Open_and_Delete_it(2, View_Name, "View_Delete_Link", "View_Remove_Link");
			}
					
			fnsGet_Element_Enabled("CreateNewView_Link");
			fnsWait_and_Click("CreateNewView_Link");
			fnsLoading_Progressing_wait(1);
					
			fnsGet_Element_Enabled("CreateView_Step1_ViewName");
			fnsLoading_Progressing_wait(2);			
			fnsWait_and_Type("CreateView_Step1_ViewName", View_Name);
						
			fnsGet_Element_Enabled("CreateView_CreateView_Bttn");
			fnsLoading_Progressing_wait(1);			
			fnsWait_and_Click("CreateView_CreateView_Bttn");
			
			fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  60, "A new view has been added to your list", 25);
						
			fnsFilter_ApplyFilter_and_Return_Total_Filtered_Record_Count("User Name: ", "USER_NAME", email_Address);
			
			User_Name = fnsw_Click_on_Table_Link_by_Passing_Cell_Xpath__and__Return_CellValue(OR_New_NSFOnline.getProperty("View_Result_Table")+"/tr[1]/td["+fnsReturn_ColumnNumber_from_TableHeaderXpath_WithoutOR(OR_New_NSFOnline.getProperty("View_Result_Table_thead"), "User Name")+"]");
			
			
			try{
				assert User_Name.equalsIgnoreCase(email_Address):"FAILED == UserName is not matched. Email address <"+email_Address+"> and UserName <"+User_Name+"> are not matched, please refer screen shot >> UserName_Mismatch"+fnsScreenShot_Date_format();
				fnsApps_Report_Logs("PASSED == User Email Address is <"+email_Address+"> and User Name is <"+ User_Name +"> are matched.");
			}catch(Throwable t){
				fnsTake_Screen_Shot("UserName_Mismatch");
				throw new Exception(Throwables.getStackTraceAsString(t));
			}
			
			fnsGet_Element_Enabled("User_Registration_Proceed_to_Create_user_button");
			fnsWait_and_Click("User_Registration_Proceed_to_Create_user_button");
			fnsLoading_Progressing_wait(1);
			
			
			fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(false, "input", "First Name", first_Name );
			fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(false, "input", "Last Name", last_Name );
			fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(false, "input", "E-Mail Address", email_Address );
			fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(false, "input", "Phone No", phone_No );	
			fnsLoading_Progressing_wait(1);
			
			
			fnsGet_Element_Enabled("User_Registration_password_input");
			fnsWait_and_Type("User_Registration_password_input", Password );
			fnsLoading_Progressing_wait(1);
			
			fnsGet_Element_Enabled("User_Registration_Next_button");
			fnsWait_and_Click("User_Registration_Next_button");
			fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  60, "User Created Successfully", 20);
			
			fnsGet_Element_Enabled("UserSetup_AccessTab_Corporate_Radio_bttn");
			fnsWait_and_Click("UserSetup_AccessTab_Corporate_Radio_bttn");
			
			fnsGet_Element_Enabled("UserSetup_AccessTab_Permissions_AdminMenu_CheckBox");
			fnsWait_and_Click("UserSetup_AccessTab_Permissions_AdminMenu_CheckBox");
			
			fnsGet_Element_Enabled("UserSetup_AccessTab_Permissions_ManageUsersMenu_CheckBox");
			fnsWait_and_Click("UserSetup_AccessTab_Permissions_ManageUsersMenu_CheckBox");
			
			fnsGet_Element_Enabled("UserSetup_AccessTab_Save_bttn");
			fnsWait_and_Click("UserSetup_AccessTab_Save_bttn");
			fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  60, "User Updated Successfully", 20);
			
			driver.quit();
			
		}catch(Throwable t){
			isTestCasePass = false;
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));
		}

	}
	
	
	@Test( priority = 4, dependsOnMethods={"Verify_Create_Edit_Delete_View_and_Setup_New_User_And_AddNewUser_GivePermissions_From_Registartion"})
	public void Login_with_NewlyCreatedUser_ForcePasswordChange_And_AgainVerifyLoginSuccessfully () throws Throwable{			
		fnsApps_Report_Logs("################### Test Case ::4 Login_with_NewlyCreatedUser_ForcePasswordChange_And_AgainVerifyLoginSuccessfully ");	
		try{
			Login_Application_Name = "nsf_connect";
			fnsBrowserLaunchAndLogin(email_Address, Password);
			
			
			fnsWait_and_Type("MyProfile_OldPassword_input", Password);
			fnsWait_and_Type("MyProfile_NewPassword_input", New_Password_Force);
			fnsWait_and_Type("MyProfile_ConfirmPassword_input", Confirm_Password_Force);  
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

