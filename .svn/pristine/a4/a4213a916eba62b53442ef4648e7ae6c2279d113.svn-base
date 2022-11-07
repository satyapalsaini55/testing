package nsf.ecap.New_NSFOnline_Suite;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchWindowException;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;

import nsf.ecap.util.TestUtil;

public class BS_38_AdvSrch_ExportFunctional extends TestSuiteBase_New_NSFOnline {

	public int count = -1;

	public String Sites_Status_DD = "INACTIVE";
	public String checklist_Status_DD = "SUBMITTED";
	public String audits_Status_DD = "COMPLETED";
	public String audit_CA_Status_DD = "Pending";

	@Parameters({ "className" })
	@BeforeTest
	public void checkTestSkip(String className) throws Exception {
		if (className.isEmpty()) {
			className = this.getClass().getSimpleName();
		}
		RunningClassName = className;
		Running_Class_BS_Description = "[BS-38] Verify Advanced Search Export Functionality";
		IsBrowserPresentAlready = false;
		fnsCheckClassLevelTestSkip(className);
	}

	@Test(dataProvider = "getTestData", priority = 1)
	public void Verify_AdvanceSearch_Export_Functionality_for_the_following_Data_(String Menu_Ajax_Name, String Login_Application,String Login_User_Password) throws Throwable {

		count++;
		Login_UserName = Login_User_Password.split(":")[0].trim();
		Login_Password = Login_User_Password.split(":")[1].trim();
		TC_Step = 1;
		Login_Application_Name = Login_Application.trim();

		try {
			if ((runmodes[count].equalsIgnoreCase("N"))) {
				fnsApps_Report_Logs(
						"****************************************************************************************************************************************");
				fnsApps_Report_Logs("################################## Runmode of Scenaio :: " + (count + 1) + " ("+ Menu_Ajax_Name + ") for Login User [" + Login_UserName + "]  is set to NO, So Skipping this scenario.");
				skip = true;
				throw new SkipException("Runmode of Scenaio :: " + (count + 1) + " (" + Menu_Ajax_Name + ") for Login User [" + Login_UserName + "]  is set to NO, So Skipping this scenario.");
			} else {
				skip = false;
				fnsApps_Report_Logs(
						"****************************************************************************************************************************************");
				fnsApps_Report_Logs("################################## Execution Logs of Scenaio :: " + (count + 1) + " (" + Menu_Ajax_Name + ") for Login User [" + Login_UserName + "].");
				fnsApps_Report_Logs("");

				if (!IsBrowserPresentAlready) {
					IsBrowserPresentAlready = fnsBrowserLaunchAndLogin(Login_UserName, Login_Password);
				}

				if (Menu_Ajax_Name.equalsIgnoreCase("Sites")) {
					fncMenu_Ajax_Link_Click_By_PassingAjaxPath("Sites_SubMenu_SearchSites_Ajax");

				} else if (Menu_Ajax_Name.equalsIgnoreCase("Checklist")) {
					fncMenu_Ajax_Link_Click_By_PassingAjaxPath("CheckList_Ajax");

				} else if (Menu_Ajax_Name.equalsIgnoreCase("Audits")) {
					fncMenu_Ajax_Link_Click_By_PassingAjaxPath("Sites_SubMenu_Audits_Ajax");

				} else if (Menu_Ajax_Name.equalsIgnoreCase("Audit Corr. Action(s)")) {
					
				driver.quit();
				
				fnsBrowserLaunchAndLogin(Login_UserName, Login_Password);
					
				fncMenu_Ajax_Link_Click_By_PassingAjaxPath("AuditCorrectiveActions_Ajax");

				}

				fnsLoading_Progressing_wait(3);

				fncNavigation_Verify_Application_Navigated_To_ViewScreen("'" + Menu_Ajax_Name + "' Menu Ajax", Menu_Ajax_Name);

				fnsGet_Element_Enabled("View_AdvanceSearch_Bttn");
				fnsWait_and_Click("View_AdvanceSearch_Bttn");
				fnsLoading_Progressing_wait(2);

				if (Menu_Ajax_Name.trim().equalsIgnoreCase("Sites")) {
					fnsDD_Value_select_by_DDLabelName_MultiselectDD("List Status", Sites_Status_DD);

				} else if (Menu_Ajax_Name.trim().equalsIgnoreCase("Checklist")) {
					fnsDD_Value_select_by_DDLabelName_MultiselectDD("Status", checklist_Status_DD);

				} else if (Menu_Ajax_Name.trim().equalsIgnoreCase("Audits")) {
					fnsDD_Value_select_by_DDLabelName_MultiselectDD("Audit Status", audits_Status_DD);
				
				
				} else if (Menu_Ajax_Name.trim().equalsIgnoreCase("Audit Corr. Action(s)")) {
					fnsDD_Value_select_by_DDLabelName_MultiselectDD("Status", audit_CA_Status_DD);
				}

				fnsGet_Element_Enabled("AuditCorrectiveActions_AdvanceSearch_Search_Bttn");
				fnsWait_and_Click("AuditCorrectiveActions_AdvanceSearch_Search_Bttn");
				fnsLoading_Progressing_wait(2);

				fnsDownload_File("View_PDF");

				fnsDownload_File("View_Excel");
				

			/*	//#################   Print functionality verification  ###########################################
				boolean Print_Functionality_Verification_Enabled = true;
				
				if(Print_Functionality_Verification_Enabled == true){
					try{
						String NsfOnline_originalHandle = driver.getWindowHandle();
						Integer tabsCount;
						boolean Print_Working = false;
						
						fnsGet_Element_Enabled("View_Print");
						fnsWait_and_Click("View_Print");
						boolean Switch_Success = false;			
						if( (browserName.equalsIgnoreCase("edge")) ){
							for(int a=0; a<=( (NewNsfOnline_Element_Max_Wait_Time)/5); a++){ 						
								ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
								tabsCount = tabs.size();
								if(tabsCount>=3){	
									if(tabsCount==3){	
										driver.switchTo().window(tabs.get(2));
									}else if(tabsCount==4){	
										driver.switchTo().window(tabs.get(3));
									}
									Switch_Success = true;
								}else{
									Thread.sleep(1000);
								}
								if( (a==( (NewNsfOnline_Element_Max_Wait_Time)/5 ) ) && Print_Working == false ){
									throw new Exception("FAILED 'PRINT' popup Window is not getting open, ater <"+NewNsfOnline_Element_Max_Wait_Time+"> seconds wait, please refer screen shot >> Print_Not_Working"+fnsScreenShot_Date_format());
								}
								
								if(Switch_Success){
									Thread.sleep(1000);
									fnsApps_Report_Logs("PASSED == Successfully verify that 'PRINT' functionality is working.");	
									try{
										driver.close();
									}catch(Throwable t){
										try{
											fnsApps_Report_Logs("Print window close fail.");
											driver.switchTo().window(tabs.get(1));
											driver.close();
										}catch(NoSuchWindowException n){
											throw new Exception ("FAILED 'PRINT' popup Window is not getting open, please refer screen shot >> Print_Not_Working"+fnsScreenShot_Date_format()+Throwables.getStackTraceAsString(n));
										}							
									}
									Thread.sleep(500);
									driver.switchTo().window(NsfOnline_originalHandle);
									Print_Working = true;	
									break;
								}						
							}					
						}else {
							for(int a=0; a<=( (NewNsfOnline_Element_Max_Wait_Time)/5); a++){ 
									try{
										if(  (driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("Model_Confirmation_Popup_OK_Bttn"))).size()>0) ){
											if( (driver.findElement(By.xpath(OR_New_NSFOnline.getProperty("Model_Confirmation_Popup_OK_Bttn"))).isDisplayed()) ){
												fnsGet_Element_Enabled("Model_Confirmation_Popup_OK_Bttn");
												fnsWait_and_Click("Model_Confirmation_Popup_OK_Bttn");
												fnsLoading_Progressing_on_Popup_wait_for_Popup(3);
											}
										}
									}catch(Throwable t){
										if( !(a==0) ){
											fnsLoading_Progressing_on_Popup_wait_for_Popup(1);
										}
										System.out.println("NoSuchElementException : nothing to do >> 'Model_Confirmation_Popup_OK_Bttn' display fail = "+a);
									}
									fnsLoading_Progressing_on_Popup_wait_for_Popup(1);
								ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
								tabsCount = tabs.size();
								if(tabsCount>=3){	
									if(tabsCount==3){	
										driver.switchTo().window(tabs.get(2));
									}else if(tabsCount==4){	
										driver.switchTo().window(tabs.get(3));
									}
									Thread.sleep(1000);
									String Popup_Window_Title = driver.getTitle().toLowerCase().trim();
									if(Popup_Window_Title.contains("print")){
										fnsApps_Report_Logs("PASSED == Successfully verify that 'PRINT' functionality is working.");	
										try{
											driver.close();
										}catch(Throwable t){
											try{
												fnsApps_Report_Logs("Print window close fail.");
												driver.switchTo().window(tabs.get(1));
												driver.close();
											}catch(NoSuchWindowException n){
												throw new Exception ("FAILED 'PRINT' popup Window is not getting open, please refer screen shot >> Print_Not_Working"+fnsScreenShot_Date_format()+Throwables.getStackTraceAsString(n));
											}							
										}
										Thread.sleep(500);
										driver.switchTo().window(NsfOnline_originalHandle);
										Print_Working = true;
										break;
									}
								}else{
									Thread.sleep(1000);
								}
								if( (a==( (NewNsfOnline_Element_Max_Wait_Time)/5 ) ) && Print_Working == false ){
									throw new Exception("FAILED 'PRINT' popup Window is not getting open, ater <"+NewNsfOnline_Element_Max_Wait_Time+"> seconds wait, please refer screen shot >> Print_Not_Working"+fnsScreenShot_Date_format());
								}
							}
						}
						if(Print_Working == false){
							throw new Exception ("FAILED 'PRINT' popup Window is not getting open, please refer screen shot >> Print_Not_Working"+fnsScreenShot_Date_format());
						}
						Thread.sleep(1500);
					}catch(Throwable t){
						fnsTake_Screen_Shot("Print_Not_Working");
						throw new Exception(Throwables.getStackTraceAsString(t));
					}
					
				}*/

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
		TestSuiteBase_MonitorPlan.fns_ReportTestResult_atClassLevel(New_NSFOnline_Suitexls,  (this.getClass().getSimpleName()) , isTestCasePass);
	}


	@AfterTest
	public void QuitBrowser() throws Throwable{
		try{
			driver.quit();
		}catch(Throwable t){
			//nothing to do			
		}
}
	

	@DataProvider
	public Object[][] getTestData() {
		return TestUtil.getData(New_NSFOnline_Suitexls, this.getClass().getSimpleName());

	}

}
