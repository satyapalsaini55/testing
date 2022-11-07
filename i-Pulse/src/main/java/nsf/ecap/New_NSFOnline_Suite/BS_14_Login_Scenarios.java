package nsf.ecap.New_NSFOnline_Suite;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import nsf.ecap.MonitorPlan_Suite.TestSuiteBase_MonitorPlan;
import nsf.ecap.util.TestUtil;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;

public class BS_14_Login_Scenarios extends TestSuiteBase_New_NSFOnline {
	

	public int count = -1;
	public boolean BrowserOpen = false;
	public boolean Update_DefaultHomePage_Column_alert_Set_Query_Run_AtTheEndOfTC = false;	
	public boolean Update_FacilityHomePage_Column_Value_facilitysnapshot_Set_Query_Run_AtTheEndOfTC = false;	
	 


@Parameters({ "className" })
@BeforeTest
public void checkTestSkip(String className) throws Exception {
	if (className.isEmpty()) {
		className = this.getClass().getSimpleName();
	}
	fnsCheckClassLevelTestSkip(className);
	
}

	

	
	
	
	
	
	@Test(dataProvider = "getTestData", priority = 1)
	public void Verify_LoginSuccess_and_LandingScreen_for_following_Data__(String Login_Senario, String Login_Application, String Login_User_Password,String Multi_Access) throws Throwable{
		BrowserOpen = false;
		fail = false;
		Update_DefaultHomePage_Column_alert_Set_Query_Run_AtTheEndOfTC = false;
		Update_FacilityHomePage_Column_Value_facilitysnapshot_Set_Query_Run_AtTheEndOfTC = false;
		count++;
		Login_UserName = Login_User_Password.split(":")[0].trim();
		String Login_Password = Login_User_Password.split(":")[1].trim();
		String LoginScenarios = Login_Senario.split("=")[1].toLowerCase().trim();
		TC_Step=1;	
		Login_Application_Name = Login_Application.trim();
		try {
			
			if (!(runmodes[count].equalsIgnoreCase("Y"))) {
				fnsApps_Report_Logs("****************************************************************************************************************************************");
				fnsApps_Report_Logs("################################## Runmode of Scenario::" + (count + 1)+" for Login User ["+Login_UserName+"]  is set to NO, So Skipping this Scenario.");
				skip=true;
				throw new SkipException("Runmode of Scenario::" + (count + 1)+" for Login User ["+Login_UserName+"]  is set to NO, So Skipping this Scenario.");
			}else{
				skip=false;
				fnsApps_Report_Logs("****************************************************************************************************************************************");
				fnsApps_Report_Logs("################################## Execution Logs of Scenario::"+(count + 1)+" for Login User ["+Login_UserName+"].");
				
				switch (LoginScenarios) {
				
					case("corporateuser_singleaccount") : { //Scenario 1
						BrowserOpen = fnsBrowserLaunchAndLogin(Login_UserName, Login_Password);
						fnsSwitchAcoount_MultiAccess(null, Multi_Access);	
						fncVerify_Menu_is_Coming_andAfterClick_redirectedToCorrectScreenWithoutAnyError_Except_Snapshot_and_LandingScreenMenu("Alerts");					
						break;
					}
					
					case("corporateuser_multiaccount") : { //Scenario 2
						BrowserOpen = fnsBrowserLaunchAndLogin(Login_UserName, Login_Password);
						fnsSwitchAcoount_MultiAccess(null, Multi_Access);	
						fncVerify_Menu_is_Coming_andAfterClick_redirectedToCorrectScreenWithoutAnyError_Except_Snapshot_and_LandingScreenMenu("Alerts");
						break;	
									
					}
					case("facilityuser_singlefacility") : { //Scenario 3
						Update_FacilityHomePage_Column_Value_facilitysnapshot_Set_Query_Run_AtTheEndOfTC = true;
						Update_DefaultHomePage_Column_alert_Set_Query_Run_AtTheEndOfTC = false;
						fnsDB_Fetch_or_Update_LoginTC_Query("select_facilityhomepage", Login_UserName, "facilitysnapshot");
						BrowserOpen = fnsBrowserLaunchAndLogin(Login_UserName, Login_Password);
						fns_Verify_and_Return_ScreenName("facility_snapshot", true);
						fncVerify_Menu_is_Coming_andAfterClick_redirectedToCorrectScreenWithoutAnyError_Except_Snapshot_and_LandingScreenMenu("Alerts");
						driver.quit();						
						fnsDB_Fetch_or_Update_LoginTC_Query("update_facilityhomepage", Login_UserName, "alert");
						BrowserOpen = fnsBrowserLaunchAndLogin(Login_UserName, Login_Password);
						fns_Verify_and_Return_ScreenName("Alerts", true);
						fncVerify_Menu_is_Coming_andAfterClick_redirectedToCorrectScreenWithoutAnyError_Except_Snapshot_and_LandingScreenMenu("Alerts");
						break;	
					}
					case("facilityuser_morethan5") : { //Scenario 4
						Update_DefaultHomePage_Column_alert_Set_Query_Run_AtTheEndOfTC = true;
						Update_FacilityHomePage_Column_Value_facilitysnapshot_Set_Query_Run_AtTheEndOfTC = false;
						fnsDB_Fetch_or_Update_LoginTC_Query("select_defaulthomepage", Login_UserName, "alert");
						BrowserOpen = fnsBrowserLaunchAndLogin(Login_UserName, Login_Password);
						fnsSwitchAcoount_MultiAccess(null, Multi_Access);	
						fns_Verify_and_Return_ScreenName("Alerts", true);
						fncVerify_Menu_is_Coming_andAfterClick_redirectedToCorrectScreenWithoutAnyError_Except_Snapshot_and_LandingScreenMenu("Alerts");
						driver.quit();
						fnsDB_Fetch_or_Update_LoginTC_Query("update_defaulthomepage", Login_UserName, "snapshot"); 
						BrowserOpen = fnsBrowserLaunchAndLogin(Login_UserName, Login_Password);
						fnsSwitchAcoount_MultiAccess(null, Multi_Access);	
						fns_Verify_and_Return_ScreenName("snapshot", true);
						fncVerify_Menu_is_Coming_andAfterClick_redirectedToCorrectScreenWithoutAnyError_Except_Snapshot_and_LandingScreenMenu("Alerts");
						break;
					}
					case("facilityuser_inbetween_1_5") : { //Scenario 5
						Update_FacilityHomePage_Column_Value_facilitysnapshot_Set_Query_Run_AtTheEndOfTC = true;
						Update_DefaultHomePage_Column_alert_Set_Query_Run_AtTheEndOfTC = false;
						fnsDB_Fetch_or_Update_LoginTC_Query("select_facilityhomepage", Login_UserName, "facilitysnapshot");
						BrowserOpen = fnsBrowserLaunchAndLogin(Login_UserName, Login_Password);
						fns_Verify_and_Return_ScreenName("multifacility_acess", true);
						Integer Cust_Store_ID_Column_Number = fnsReturn_ColumnNumber_from_TableHeaderXpath_WithoutOR(OR_New_NSFOnline.getProperty("View_Result_Table_thead"), "Cust. Store ID");
						fnsw_Click_on_Table_Link_by_Passing_Cell_Xpath__and__Return_CellValue(OR_New_NSFOnline.getProperty("View_Result_Table")+"/tr[1]/td["+Cust_Store_ID_Column_Number+"]");
						
						String All_Card_Xpath = "//div[@class='dash-width20 green bor_rad10 margin-left0 box-status-inner ng-scope']/h1";
						Integer All_Card_Size = driver.findElements(By.xpath(All_Card_Xpath)).size();
						boolean Card_found = false;
						boolean Card_Value_Count_Coming = false;
						boolean Card_Click_Done = false;
						Integer Card_Value_Count = 0;
						for(int i=1; i <=All_Card_Size; i++){
							String Per_Card_Xpath = "("+All_Card_Xpath+")["+i+"]";
							try{
								if(driver.findElement(By.xpath(Per_Card_Xpath)).isDisplayed()){
									Card_found = true;
								}
							}catch(Throwable t){
								System.out.println("Per card error");
							}
							if(Card_found){
								String Card_Value = driver.findElement(By.xpath(Per_Card_Xpath)).getText().trim();
								
								if(Card_Value.length() > 0){
									Card_Value_Count = Integer.parseInt(Card_Value);
									Card_Value_Count_Coming = true;
									driver.findElement(By.xpath(Per_Card_Xpath)).click();
									Card_Click_Done = true;
									break;
								}else{
									Card_Value_Count_Coming = false;
								}
							}
						}
						
						try{
							assert Card_found==true :"FAILED == Any of the 'Card' is NOT coming, plese refer the screen shot >> CARD_NOT_Coming"+fnsScreenShot_Date_format();
							fnsApps_Report_Logs("PASSED == successfully verified that Card is coming.");
						}catch(Throwable t){
							fnsTake_Screen_Shot("CARD_NOT_Coming");
							throw new Exception(Throwables.getStackTraceAsString(t));
						}
						
						try{
							assert Card_Value_Count_Coming==true :"FAILED == Card is available but the count is not coming, plese refer the screen shot >> CARD_COUNT_NOT_Coming"+fnsScreenShot_Date_format();
							fnsApps_Report_Logs("PASSED == successfully verified that Card COUNT is coming.");
						}catch(Throwable t){
							fnsTake_Screen_Shot("CARD_COUNT_NOT_Coming");
							throw new Exception(Throwables.getStackTraceAsString(t));
						}
						
						try{
							assert Card_Click_Done==true :"FAILED == Clicking on CARD ("+Card_Value_Count+")  is getting FAIL, plese refer the screen shot >> CARD_COUNT_NOT_Coming"+fnsScreenShot_Date_format();
							fnsApps_Report_Logs("PASSED == Click done on the CARD having value="+Card_Value_Count);
						}catch(Throwable t){
							fnsTake_Screen_Shot("CARD_Click_Fail");
							throw new Exception(Throwables.getStackTraceAsString(t));
						}
						
						
						fnsLoading_Progressing_wait(5);
						fncVerify_Menu_is_Coming_andAfterClick_redirectedToCorrectScreenWithoutAnyError_Except_Snapshot_and_LandingScreenMenu("Alerts");
						driver.quit();
						fnsDB_Fetch_or_Update_LoginTC_Query("update_facilityhomepage", Login_UserName, "alert"); 
						BrowserOpen = fnsBrowserLaunchAndLogin(Login_UserName, Login_Password);
						fns_Verify_and_Return_ScreenName("alert", true);
						fncVerify_Menu_is_Coming_andAfterClick_redirectedToCorrectScreenWithoutAnyError_Except_Snapshot_and_LandingScreenMenu("alert");
						break;
					}
					case("jumpfrom_superadmin_link_case_a") : { //Scenario 6
						BrowserOpen = fnsBrowserLaunchAndLogin(Login_UserName, Login_Password);
						fnsSwitchAcoount_MultiAccess(null, Multi_Access);	
						fncVerify_Menu_is_Coming_andAfterClick_redirectedToCorrectScreenWithoutAnyError_Except_Snapshot_and_LandingScreenMenu("Alerts");
						break;
					}
					case("jumpfrom_mentioneduser_link_case_b") : { //Scenario 7
						BrowserOpen = fnsBrowserLaunchAndLogin(Login_UserName, Login_Password);
						fnsSwitchAcoount_MultiAccess(null, Multi_Access);	
						fncVerify_Menu_is_Coming_andAfterClick_redirectedToCorrectScreenWithoutAnyError_Except_Snapshot_and_LandingScreenMenu("Alerts");
						break;
					}
					case("jumpfrom_servicerequests_alert_case_c") : { //Scenario 8
						fnsDB_Fetch_or_Update_LoginTC_Query("update_servicerequests_alert", Login_UserName, "alert"); 
						BrowserOpen = fnsBrowserLaunchAndLogin(Login_UserName, Login_Password);
						//fnsLoading_Progressing_wait(1);
						fncVerify_Menu_is_Coming_andAfterClick_redirectedToCorrectScreenWithoutAnyError_Except_Snapshot_and_LandingScreenMenu("Alerts");
						break;
					}
					case("jumpfrom_superadminlogin_button_case_d") : { //Scenario 9
						BrowserOpen = fnsBrowserLaunchAndLogin(Login_UserName, Login_Password);
						fnsSwitchAcoount_MultiAccess(null, Multi_Access);	
						fncVerify_Menu_is_Coming_andAfterClick_redirectedToCorrectScreenWithoutAnyError_Except_Snapshot_and_LandingScreenMenu("Alerts");
						break;
					}
					case("jumpfrom_nsfonlineaccountsetup_button") : { //Scenario 10
						BrowserOpen = fnsBrowserLaunchAndLogin(Login_UserName, Login_Password);
						fnsSwitchAcoount_MultiAccess(null, Multi_Access);	
						fnsLoading_UnwantedPopupError();
						fnsApps_Report_Logs("PASSED == After clicking on 'Nsfonline Account Setup' button, the Landing Screen is coming  without any error.");
						break;
					}
					case("special_signin") : { //Scenario 11
						//String Audit_No = fnsDB_Fetch_or_Update_LoginTC_Query("auditno", Login_UserName, null);
						String Audit_No = "";
						String siteUrl = "";
						if(env.toLowerCase().trim().equals("test")){
							//Audit_No  = "1544073"; //given by itesh
							Audit_No  = "1336033"; 
						}else{
							Audit_No  = "1336033"; //given by deepak in email on 15.5.2018
						}
						siteUrl = fnsDB_Fetch_or_Update_LoginTC_Query("url", Login_UserName, Audit_No); 
						if(env.toLowerCase().trim().equals("test")){
							siteUrl = siteUrl.replace("https://my.", "https://mytest.");
						}else{
							siteUrl = siteUrl.replace("https://my.", "https://mystg.");
						}
						String Password = fnsDB_Fetch_or_Update_LoginTC_Query("password", Login_UserName, Audit_No); 
						
						BrowserOpen =  TestSuiteBase_MonitorPlan.fns_Launch_Browser_Only();	
												
						fnsApps_Report_Logs("Application credentials are URL[ "+siteUrl+" ] & Password[ "+Password+" ]");
						driver.get(siteUrl);
						  
						fnsLoading_Progressing_wait(5);
						try{
							TestSuiteBase_MonitorPlan.fnsComman_BrowserLogin_ElementWait(OR_New_NSFOnline.getProperty("SpecialSignin_FirstName"), "60");
						}catch(Throwable t){
							throw new Exception(".... Application [ "+siteUrl+" ] is not getting open, after 60 seconds wait, please refer the screen shot >> LoginFail"+fnsScreenShot_Date_format());
						}
												
						TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(OR_New_NSFOnline.getProperty("SpecialSignin_FirstName")).sendKeys("");
						TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(OR_New_NSFOnline.getProperty("SpecialSignin_FirstName")).sendKeys("Test");
						TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(OR_New_NSFOnline.getProperty("SpecialSignin_LastName")).sendKeys("");
						TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(OR_New_NSFOnline.getProperty("SpecialSignin_LastName")).sendKeys("Test");
						
						TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(OR_New_NSFOnline.getProperty("SpecialSignin_Title")).sendKeys("Automation");
						TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(OR_New_NSFOnline.getProperty("SpecialSignin_Password")).sendKeys("");
						TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(OR_New_NSFOnline.getProperty("SpecialSignin_Password")).sendKeys(Password);
						
						for(int i=1; i<=10; i++){
							if(i==10){
								throw new Exception(" Due to Page Loading, Clicking on 'Login Button' is failed."+" , Please refer the screen shot >> LoginFail"+fnsScreenShot_Date_format());
							}else{
								Thread.sleep(1000);
							}
							try{
								TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(OR_New_NSFOnline.getProperty("SpecialSignin_Login_Bttn")).click();
								break;
							}catch(Throwable t){
								//nothing to do.
							}							
						}
						/*fnsLoading_Progressing_wait(3);
						fnsLoading_Progressing_wait(2);
						fnsLoading_Progressing_wait(1);*/
						fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
						Thread.sleep(2000);
						for(int i=0; i<=120; i++){
							try{
								if( (driver.findElement(By.xpath(OR_New_NSFOnline.getProperty("NewNsfOnline_Content_Model_Popup"))).isDisplayed()) ){
									fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
									String ErrorMsg = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(OR_New_NSFOnline.getProperty("NewNsfOnline_Content_Model_Popup")).getText().trim();
									if(ErrorMsg.toLowerCase().contains("This is a non-production environment - Do NOT upload sensitive, confidential, NSF or customer data".toLowerCase())){
										fnsApps_Report_Logs("PASSED == Acknowledge Popup is coming.");
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
						fnsGet_Element_Enabled("Acknowledge_Acknowledge_bttn");
						fnsWait_and_Click("Acknowledge_Acknowledge_bttn");
						Thread.sleep(2000);
						fns_Verify_and_Return_ScreenName("Audit : "+Audit_No, true);
						break;
					}	
					
				}
			}	
		//	Post_TestCase_DB_Query_Execute();
		}catch(SkipException sk){
			throw new SkipException("Runmode of Scenario::" + (count + 1)+" for Customer["+Login_UserName+"]  is set to NO, So Skipping this Scenario.");
			
		}catch(NoSuchWindowException W){
			fail = true;
			isTestCasePass = false;
			throw new Exception(W.getMessage());
			
		}catch(Throwable t){
			fail = true;
			isTestCasePass = false;
			fnsApps_Report_Logs(Login_Senario.trim()+" : "+Throwables.getStackTraceAsString(t));
			throw new Exception(Login_Senario.trim()+" : "+Throwables.getStackTraceAsString(t));
		}finally {
			try{
				Post_TestCase_DB_Query_Execute();
			}catch(Throwable tt){
				fail = true;
				isTestCasePass = false;
				throw new Exception(Throwables.getStackTraceAsString(tt));
			}
		}
}

	
//################################################################## Class Method ############################################################################

public void fncVerify_Menu_is_Coming_andAfterClick_redirectedToCorrectScreenWithoutAnyError_Except_Snapshot_and_LandingScreenMenu(String LandingScreenMenuName) throws Throwable{
	try{
		boolean Only_Ajax_Snapshot_and_Home_Menu_display = false;
		boolean Menus_are_Coming = false;
		String ClickDone_on_MenuName = "";
		String Get_Redirected_ScreenName = "";
		boolean Screen_Navigation_done = false;
		boolean CorrectScreenRedirectDone = false;
		
		//fnsLoading_Progressing_wait(2);
		fnsGet_Element_Enabled("Main_Menu_Ajax_Link");
		fnsLoading_Progressing_wait(2);
		WebElement MainMenu = fnsGet_OR_New_NSFOnline_ObjectX("Main_Menu_Ajax_Link");
		Actions action = new Actions(driver);
		action.moveToElement(MainMenu).click().build().perform();
		Thread.sleep(1500);
		fnsGet_Element_Enabled("Menu_Bar_Ajax_List");
		List<WebElement> MenuBarAjax_Menu_Links_Objects = fnsGet_OR_New_NSFOnline_ObjectX("Menu_Bar_Ajax_List").findElements(By.tagName("a"));
		for(WebElement MenuBarAjax_Menu_Links_Elements : MenuBarAjax_Menu_Links_Objects){
			String Menu_Link_Text = MenuBarAjax_Menu_Links_Elements.getText().trim();
			if(Menu_Link_Text.length()>3){
				Menus_are_Coming = true;
				if( (Menu_Link_Text.toLowerCase().equals("snapshot")) || (Menu_Link_Text.toLowerCase().equals("home")) /*|| (Menu_Link_Text.toLowerCase().equals("common.home"))*/){
					Only_Ajax_Snapshot_and_Home_Menu_display = true;
				}else if(Menu_Link_Text.toLowerCase().contains(LandingScreenMenuName.toLowerCase().trim())){
					Only_Ajax_Snapshot_and_Home_Menu_display = true;
				}else{
					MenuBarAjax_Menu_Links_Elements.click();
					fnsApps_Report_Logs("PASSED == Click done on '"+Menu_Link_Text+"' menu.");
					Only_Ajax_Snapshot_and_Home_Menu_display = false;		
					ClickDone_on_MenuName = Menu_Link_Text;
					break;
				}							
			}					
		}
		

		if(Menus_are_Coming == false){
			fnsTake_Screen_Shot("Menu_Not_Coming");
			throw new Exception("FAILED == Not a single 'Menu' is available to click (Menu list is blank), please refer the screen shot >> Menu_Not_Coming"+fnsScreenShot_Date_format());
		}
		
		if(Only_Ajax_Snapshot_and_Home_Menu_display == true){
			fnsTake_Screen_Shot("Menu_Not_Coming_Except_Snapshot_or_"+LandingScreenMenuName);
			throw new Exception("FAILED == 'Menu' is not available to click except 'Snapshot' menu, please refer the screen shot >> Menu_Not_Coming_Except_Snapshot_or_"+LandingScreenMenuName+fnsScreenShot_Date_format());
		}		
		
		
		CorrectScreenRedirectDone = fns_Verify_and_Return_ScreenName(ClickDone_on_MenuName, false);
		
		if(CorrectScreenRedirectDone){
			fnsApps_Report_Logs("PASSED == After clicking on ("+ClickDone_on_MenuName+") menu, application is successfully navigated to '"+ClickDone_on_MenuName+"' screen without any error");
		}else{
			fnsTake_Screen_Shot(ClickDone_on_MenuName+"_View_Screen_Not_Open");
			throw new Exception("FAILED == After clicking on ("+ClickDone_on_MenuName+") menu, application is NOT navigated to '"+ClickDone_on_MenuName+"' screen, please refer screen shot >> "+ClickDone_on_MenuName+"_View_Screen_Not_Open"+fnsScreenShot_Date_format());
		
		}
	}catch(Throwable t){
		isTestCasePass = false;
		throw new Exception(Throwables.getStackTraceAsString(t));
	}	
}
	


	
	//Function to run DB Select/Update queries for fetching default screen name.
	public String fnsDB_Fetch_or_Update_LoginTC_Query(String ScriptRunForWhich__SelectUpdate_FacilityHomePage_or_DefaultHomePage__Url_Password_AuditNo, String UserName, String Default_LandingScreenName_AuditNo) throws Throwable {
		Connection connection = null;
		String Default_LandingScreenName_or_AuditNo_or_URL_or_Password_from_DB = "";
		try{
			fnsApps_Report_Logs("=========================================================================================================================================");
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			    
			
			connection = fnpGetDBConnection(); 
			 
			Statement stmt= connection.createStatement();
			
			switch(ScriptRunForWhich__SelectUpdate_FacilityHomePage_or_DefaultHomePage__Url_Password_AuditNo) {
			
				case ("select_facilityhomepage") : {
					String Select_query="select facility_home_page from CW_CUS_SETTINGS where CUS_SEQ =(select CUO.COCL_SEQ from cw_users cw,CW_USER_OWNERS cuo where cuo.user_seq=cw.seq and cw.username='"+UserName+"')";
					ResultSet rs = stmt.executeQuery(Select_query);
					
					while(rs.next()){
						Default_LandingScreenName_or_AuditNo_or_URL_or_Password_from_DB = rs.getString("facility_home_page");
						break;
					}
					fnsApps_Report_Logs("**** Select Query Executed Successfully and the default landing screen name is ("+Default_LandingScreenName_or_AuditNo_or_URL_or_Password_from_DB+"). query >> "+Select_query); 	
					
					assert ( (Default_LandingScreenName_or_AuditNo_or_URL_or_Password_from_DB.toLowerCase().trim()).equals(Default_LandingScreenName_AuditNo.toLowerCase().trim()) ): 
						"FAILED == Before Login : Value '"+Default_LandingScreenName_or_AuditNo_or_URL_or_Password_from_DB+"' fetched from Data Base is NOT matched with the expected value ("+Default_LandingScreenName_AuditNo+"). Query is >> "+Select_query;
					break;							
				}
				
				case ("update_facilityhomepage") : {
					String Update_Query ="update CW_CUS_SETTINGS set facility_home_page= '"+Default_LandingScreenName_AuditNo+"' where CUS_SEQ =(select CUO.COCL_SEQ from cw_users cw,CW_USER_OWNERS cuo where cuo.user_seq=cw.seq and cw.username='"+UserName+"')";
					stmt.executeUpdate(Update_Query);
					connection.commit();
					fnsApps_Report_Logs("**** Update Query Executed Successfully. >> "+Update_Query); 				
					break;
				}
				
				case ("select_defaulthomepage") : {
					String Select_query="select default_home_page from CW_CUS_SETTINGS where CUS_SEQ =(select CUO.COCL_SEQ from cw_users cw,CW_USER_OWNERS cuo where cuo.user_seq=cw.seq and cw.username='"+UserName+"')";
					ResultSet rs = stmt.executeQuery(Select_query);
					
					while(rs.next()){
						Default_LandingScreenName_or_AuditNo_or_URL_or_Password_from_DB = rs.getString("default_home_page");
						break;
					}
					fnsApps_Report_Logs("**** Select Query Executed Successfully and the default landing screen name is ("+Default_LandingScreenName_or_AuditNo_or_URL_or_Password_from_DB+"). query >> "+Select_query); 	
					
					assert ( (Default_LandingScreenName_or_AuditNo_or_URL_or_Password_from_DB.toLowerCase().trim()).equals(Default_LandingScreenName_AuditNo.toLowerCase().trim()) ): 
						"FAILED == Before Login : Value '"+Default_LandingScreenName_or_AuditNo_or_URL_or_Password_from_DB+"' fetched from Data Base is NOT matched with the expected value ("+Default_LandingScreenName_AuditNo+"). Query is >> "+Select_query;
					break;							
				}
				
				case ("update_defaulthomepage") : {
					String Update_Query ="update CW_CUS_SETTINGS set default_home_page= '"+Default_LandingScreenName_AuditNo+"' where CUS_SEQ =(select CUO.COCL_SEQ from cw_users cw,CW_USER_OWNERS cuo where cuo.user_seq=cw.seq and cw.username='"+UserName+"')";
					stmt.executeUpdate(Update_Query);
					connection.commit();
					fnsApps_Report_Logs("**** Update Query Executed Successfully. >> "+Update_Query); 				
					break;
				}
				
				case ("auditno") : {
					String Select_query="select car.* from OA_CORR_ACTIONS car, oa_audits oa,customers c where car.audit_no=oa.audit_no and OA.COCL_CODE='C0212898' and c.code=OA.FACILITY_CODE and car.ca_status='PENDING'";
					ResultSet rs = stmt.executeQuery(Select_query);
					
					while(rs.next()){
						Default_LandingScreenName_or_AuditNo_or_URL_or_Password_from_DB = rs.getString("audit_no");
						break;
					}
					fnsApps_Report_Logs("**** Select Query Executed Successfully and the Audit No is ("+Default_LandingScreenName_or_AuditNo_or_URL_or_Password_from_DB+"). query >> "+Select_query); 	
					
					assert ( Default_LandingScreenName_or_AuditNo_or_URL_or_Password_from_DB.length()>0 ): 
						"FAILED == Audit no is not available into the DB. Query is >> "+Select_query;
					break;
				}	
				
				case ("url") : {
					//String Select_query="SELECT   (SELECT 'https://mytest.nsf.org/nsfonline/#/specialsignin?param='     || UTL_URL.escape (OASIS.OAII_PKG_UTIL.ENCRYPT_PARAMETER ( 'username='     ||     (SELECT LOWER ('bk'       || c2.store_no)     FROM oa_audits oa2,       customers c2     WHERE OA2.FACILITY_CODe = C2.CODE     AND OA2.AUDIT_NO        = OA.AUDIT_NO     )     || '##auditno=' || oa.audit_no     || '##cuo_seq=' ||     (SELECT cuo.SEQ     FROM oa_audits oa2,       customers c2,       cw_user_owners cuo, cw_users cw     WHERE OA2.FACILITY_CODe = C2.CODE     AND OA2.AUDIT_NO        = oa.audit_no     AND cw.USERNAME      = LOWER ('bk'       || C2.STORE_NO)       and cw.seq=cuo.user_seq     AND cuo.COCL_SEQ     = C2.PARENT_CUS_SEQ     AND cuo.ACTIVE_FLG  ='Y'     and cw.ACTIVE_FLG = 'Y'     )     || '##page=alert##carstatus=awaiting'))   FROM DUAL   ) AWAITING_CARS_LINK FROM oa_audits oa,   customers fac WHERE oa.facility_code = fac.code AND oa.audit_no        = '"+Default_LandingScreenName_AuditNo+"'";
					String Select_query="";
					if(env.equalsIgnoreCase("test")){
						Select_query="SELECT   (SELECT 'https://mytest.nsf.org/nsfonline/#/specialsignin?param='     || UTL_URL.escape (OASIS.OAII_PKG_UTIL.ENCRYPT_PARAMETER ( 'username='     ||     (SELECT LOWER ('bk'       || c2.store_no)     FROM oa_audits oa2,       customers c2     WHERE OA2.FACILITY_CODe = C2.CODE     AND OA2.AUDIT_NO        = OA.AUDIT_NO     )     || '##auditno=' || oa.audit_no     || '##cuo_seq=' ||     (SELECT cuo.SEQ     FROM oa_audits oa2,       customers c2,       cw_user_owners cuo, cw_users cw     WHERE OA2.FACILITY_CODe = C2.CODE     AND OA2.AUDIT_NO        = oa.audit_no     AND cw.USERNAME      = LOWER ('bk'       || C2.STORE_NO)       and cw.seq=cuo.user_seq     AND cuo.COCL_SEQ     = C2.PARENT_CUS_SEQ     AND cuo.ACTIVE_FLG  ='Y'     and cw.ACTIVE_FLG = 'Y'     )     || '##page=alert##carstatus=awaiting'))   FROM DUAL   ) AWAITING_CARS_LINK FROM oa_audits oa,   customers fac WHERE oa.facility_code = fac.code AND oa.audit_no        = '"+Default_LandingScreenName_AuditNo+"'";
					}else{
						Select_query="SELECT   (SELECT 'https://mystg.nsf.org/nsfonline/#/specialsignin?param='     || UTL_URL.escape (OASIS.OAII_PKG_UTIL.ENCRYPT_PARAMETER ( 'username='     ||     (SELECT LOWER ('bk'       || c2.store_no)     FROM oa_audits oa2,       customers c2     WHERE OA2.FACILITY_CODe = C2.CODE     AND OA2.AUDIT_NO        = OA.AUDIT_NO     )     || '##auditno=' || oa.audit_no     || '##cuo_seq=' ||     (SELECT cuo.SEQ     FROM oa_audits oa2,       customers c2,       cw_user_owners cuo, cw_users cw     WHERE OA2.FACILITY_CODe = C2.CODE     AND OA2.AUDIT_NO        = oa.audit_no     AND cw.USERNAME      = LOWER ('bk'       || C2.STORE_NO)       and cw.seq=cuo.user_seq     AND cuo.COCL_SEQ     = C2.PARENT_CUS_SEQ     AND cuo.ACTIVE_FLG  ='Y'     and cw.ACTIVE_FLG = 'Y'     )     || '##page=alert##carstatus=awaiting'))   FROM DUAL   ) AWAITING_CARS_LINK FROM oa_audits oa,   customers fac WHERE oa.facility_code = fac.code AND oa.audit_no        = '"+Default_LandingScreenName_AuditNo+"'";
					}
					
					ResultSet rs = stmt.executeQuery(Select_query);
					
					while(rs.next()){
						Default_LandingScreenName_or_AuditNo_or_URL_or_Password_from_DB = rs.getString("AWAITING_CARS_LINK");
						break;
					}
					fnsApps_Report_Logs("**** Select Query Executed Successfully and the URL is ("+Default_LandingScreenName_or_AuditNo_or_URL_or_Password_from_DB+"). query >> "+Select_query); 	
					
					assert ( Default_LandingScreenName_or_AuditNo_or_URL_or_Password_from_DB.length()>0 ): 
						"FAILED == URL is not available into the DB. Query is >> "+Select_query;
					break;
				}
				
				case ("password") : {
					//String Select_query="select SUBSTR(cusweb.cw_pkg_util.generate_password((select 'bk' || upper(c.store_no) from oa_audits oa, customers c where OA.FACILITY_CODe=C.CODE and OA.AUDIT_NO='"+Default_LandingScreenName_AuditNo+"')),1,8)||'2' from dual";
					String Select_query= "select SUBSTR(cusweb.cw_pkg_util.generate_password((select LOWER(c.CODE) from oa_audits oa, customers c WHERE OA.FACILITY_CODE=C.CODE and OA.AUDIT_NO='"+Default_LandingScreenName_AuditNo+"')),1,8)||'2' from dual"; // Given by sahil - 25.3.21
					ResultSet rs = stmt.executeQuery(Select_query);
					
					while(rs.next()){
						Default_LandingScreenName_or_AuditNo_or_URL_or_Password_from_DB = rs.getString(1);
						break;
					}
					fnsApps_Report_Logs("**** Select Query Executed Successfully and the Password is ("+Default_LandingScreenName_or_AuditNo_or_URL_or_Password_from_DB+"). query >> "+Select_query); 	
					
					assert ( Default_LandingScreenName_or_AuditNo_or_URL_or_Password_from_DB.length()>0 ): 
						"FAILED == Password is not available into the DB. Query is >> "+Select_query;
					break;
				}
				case ("update_servicerequests_alert") : {
					String Update_Query ="update ECAP.EC_ALERT_TRANSACTION set ASSIGNED_EMP_NO=(select emp_no from employees where Upper (username) = Upper ('"+UserName+"')) where ALEM_SEQ =(select seq from ECAP.EC_ALERT_MASTER where  ALERT_code = 'SERVICE_REQUEST_ASSIGNED')";
					stmt.executeUpdate(Update_Query);
					connection.commit();
					fnsApps_Report_Logs("**** Update Query Executed Successfully. >> "+Update_Query); 				
					break;
				}
			}
			connection.close();
			   
		   
		}catch (SQLException e) {
			fnsApps_Report_Logs("Data base Error : "+ Throwables.getStackTraceAsString(e).trim());
			throw new Exception ("Data base Error : "+ Throwables.getStackTraceAsString(e).trim());
		}finally {
			if( !(connection==null) ){
				connection.close();
			}
		}
			fnsApps_Report_Logs("=========================================================================================================================================");
			System.out.println("DB Value = "+Default_LandingScreenName_or_AuditNo_or_URL_or_Password_from_DB);
		return Default_LandingScreenName_or_AuditNo_or_URL_or_Password_from_DB.trim();
	}	

	
	
	
	
	
	
	
//################################################################## Config Method ############################################################################

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
		/*//try{
			fnsDB_Fetch_or_Update_LoginTC_Query("update_facilityhomepage", Login_UserName, "facilitysnapshot");
		}catch(Throwable t){
			throw new Exception (Throwables.getStackTraceAsString(t));			
		}*/	
		TestSuiteBase_MonitorPlan.fns_ReportTestResult_atClassLevel(New_NSFOnline_Suitexls,  (this.getClass().getSimpleName()) , isTestCasePass);
	}
	

	@AfterMethod
	public void QuitBrowser() throws Throwable{
		try{
			driver.quit();
		}catch(Throwable t){
			//nothing to do			
		}		
	}
	
	//@AfterMethod
	public void Post_TestCase_DB_Query_Execute() throws Throwable{
		try{
			if(Update_DefaultHomePage_Column_alert_Set_Query_Run_AtTheEndOfTC ){
				fnsDB_Fetch_or_Update_LoginTC_Query("update_defaulthomepage", Login_UserName, "alert");
			}
			if(Update_FacilityHomePage_Column_Value_facilitysnapshot_Set_Query_Run_AtTheEndOfTC){
				fnsDB_Fetch_or_Update_LoginTC_Query("update_facilityhomepage", Login_UserName, "facilitysnapshot");
			}
		}catch(Throwable t){
			throw new Exception (Throwables.getStackTraceAsString(t));			
		}		
	}
	
	@DataProvider
	public Object[][] getTestData() {
		return TestUtil.getData(New_NSFOnline_Suitexls, this.getClass().getSimpleName());
	}


}
