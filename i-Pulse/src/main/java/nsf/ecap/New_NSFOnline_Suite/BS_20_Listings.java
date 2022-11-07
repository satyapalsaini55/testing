package nsf.ecap.New_NSFOnline_Suite;
import java.util.ArrayList;
import java.util.List;

import nsf.ecap.MonitorPlan_Suite.TestSuiteBase_MonitorPlan;
import nsf.ecap.util.TestUtil;

import org.openqa.selenium.By;
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
public class BS_20_Listings extends TestSuiteBase_New_NSFOnline{
	public int count = -1;
	public String View_Name = null;

	@Parameters({ "className" })
	@BeforeTest
	public void checkTestSkip(String className) throws Exception {
		if (className.isEmpty()) {
			className = this.getClass().getSimpleName();
		}
		IsBrowserPresentAlready =false;
		fnsCheckClassLevelTestSkip(className);
	}

	@Test(dataProvider = "getTestData", priority = 1)
	public void Login_i_pulse_to_perform_Burger_operation_Listing(String Report_Name,String Login_Application, String Login_User_Password) throws Throwable{
		fail = false;
		count++;
		Login_UserName = Login_User_Password.split(":")[0].trim();
		Login_Password = Login_User_Password.split(":")[1].trim();
		TC_Step=1;
		Login_Application_Name = Login_Application.trim();
			
		try {			
			if ((runmodes[count].equalsIgnoreCase("N"))) {
				fnsApps_Report_Logs("****************************************************************************************************************************************");
				fnsApps_Report_Logs("################################## Runmode of Scenaio :: " + (count + 1)+" ("+Report_Name+") for Login User ["+Login_UserName+"]  is set to NO, So Skipping this scenario.");
				skip=true;
				throw new SkipException("Runmode of Scenaio :: " + (count + 1)+" ("+Report_Name+") for Login User ["+Login_UserName+"]  is set to NO, So Skipping this scenario.");
			}else{
				skip=false;
				fnsApps_Report_Logs("****************************************************************************************************************************************");
				fnsApps_Report_Logs("################################## Execution Logs of Scenaio :: "+(count + 1)+" ("+Report_Name+") for Login User ["+Login_UserName+"].");
				fnsApps_Report_Logs("");
							    
				if (!IsBrowserPresentAlready) {
					IsBrowserPresentAlready = fnsBrowserLaunchAndLogin(Login_UserName, Login_Password);
				}
				if(Report_Name.equals("Listings"))	{
					fncMenu_Ajax_Link_Click_By_PassingAjaxPath("Listings_Ajax");
				}
				View_Name = "Automation - "+Report_Name.trim();
				
				fncNavigation_Verify_Application_Navigated_To_ViewScreen("'"+Report_Name.trim()+"' Menu Ajax", Report_Name.trim());
				fncVerify_View_Display_Open_and_Delete_it(2, View_Name, "View_Delete_Link", "View_Remove_Link");
				
				
				fnsGet_Element_Enabled("CreateNewView_Link");
				fnsWait_and_Click("CreateNewView_Link");
				fnsLoading_Progressing_wait(2);
				
				fnsGet_Element_Enabled("CreateView_Step1_ViewName");
				fnsLoading_Progressing_wait(1);
				fnsWait_and_Type("CreateView_Step1_ViewName", View_Name);
				Thread.sleep(1000);
				
				fnsGet_Element_Enabled("CreateView_CreateView_Bttn");
				fnsWait_and_Click("CreateView_CreateView_Bttn");
					
				fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  60, "A new view has been added to your list", 25);
			
				fncNavigation_Verify_Application_Navigated_To_ViewScreen("'CreateView' button", Report_Name.trim());
				
				fnsGet_Element_Enabled("View_Result_Table");
				List<WebElement> Result_Table_Rows_objects = fnsGet_OR_New_NSFOnline_ObjectX("View_Result_Table").findElements(By.tagName("tr"));
								
				for(WebElement Result_Table_Rows_Element : Result_Table_Rows_objects){
					if( (Result_Table_Rows_Element.getText().toLowerCase().trim()).contains("no records found")){
						fnsTake_Screen_Shot("View_Records_Not_Coming");
						throw new Exception ("FAILED == Records are not coming into the view '"+View_Name+"', please refer the screen shot >> View_Records_Not_Coming"+fnsScreenShot_Date_format());
					}else{
						break;
					}
				}
				
				fnsWait_Click_on_First_Visible_Element_if_More_than_One_Coming("'22590' link", "//a[text()='22590']");
				fnsLoading_Progressing_wait(2);
				

				for(int a=0; a<=120; a++){ 
					ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
					Integer tabsCount = tabs.size();
					if(tabsCount==3){
						for (int i = 0; i < tabsCount; i++) {
							if( !( (NsfConncet_Window_Handle.equalsIgnoreCase(tabs.get(i))) || (iPulse_Original_WindowHandle.equalsIgnoreCase(tabs.get(i))) ) ){
								driver.switchTo().window(tabs.get(i));
								fnsApps_Report_Logs("PASSED == Successfully Switch to 'Listing Category' Window.");
								break;
							}
						}
						break;
					}else{
						Thread.sleep(1000);
					}
					if(a==Long.parseLong(CONFIG.getProperty("ElementWaitTime"))){
						fnsTake_Screen_Shot("SwitchWindowFail");
						throw new Exception("FAILED == 'Listing Category' window is not getting open after 120 Seconds wait, please refer the screen shot >> SwitchWindowFail"+fnsScreenShot_Date_format());
					}
				}
				
				try{
					assert (driver.getTitle().toLowerCase().trim()).contains(("Listing Category Search Page | NSF International".toLowerCase())) : "FAILED == other screen got open instead of 'Listing Category' screen, please refer the screen shot >> YouTube_Window_Open_Fail"+fnsScreenShot_Date_format();
					fnsApps_Report_Logs("PASSED == successfully verified that 'Listing Category' screen is opened.");
				}catch(Throwable t){
					fnsTake_Screen_Shot("Listing_Window_Open_Fail");
					throw new Exception (Throwables.getStackTraceAsString(t));
				}
					
				try{
					assert ( (driver.getPageSource().toLowerCase().trim()).contains("hobart corporation") && (driver.getPageSource().toLowerCase().trim()).contains("nsf product and service listings") ): "FAILED == Incorrect Data are displayed for 22590 record, please refer the screen shot >> IncorrectData_Fail"+fnsScreenShot_Date_format();
					fnsApps_Report_Logs("PASSED == successfully verified that Data are displayed for 22590 record on 'Listing Category' screen.");
				}catch(Throwable t){
					fnsTake_Screen_Shot("IncorrectData_Fail");
					throw new Exception (Throwables.getStackTraceAsString(t));
				}	
			}
		}catch(SkipException se){
			throw new SkipException(Throwables.getStackTraceAsString(se));
		}catch(NoSuchWindowException W){
			fail = true;
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		}catch(Throwable t){
			fail = true;
			isTestCasePass = false;
			IsBrowserPresentAlready =false;
			driver.quit();
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));
		}	
	}

	
	//#######################################################  Configuration Method  ################################################################
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
