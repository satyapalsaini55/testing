package nsf.ecap.TraQtion_Suite;

import java.util.ArrayList;

import nsf.ecap.MonitorPlan_Suite.TestSuiteBase_MonitorPlan;
import nsf.ecap.util.TestUtil;

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

public class Search_Screens_1 extends TestSuiteBase_TraQtion {
	
	public int count = -1;
		
	
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
	public void Verify_HOA_Search_Edit_and_Update_are_Working_Fine_for__(String Search_Screen_Names) throws Throwable{
	
		fail = false;
		count++;
		TC_Step=1;	
		try {			
			if ((runmodes[count].equalsIgnoreCase("N"))) {
				fnsApps_Report_Logs("****************************************************************************************************************************************");
				fnsApps_Report_Logs("################################## Runmode of Scenaio :: " + (count + 1)+" of Module ["+Search_Screen_Names+"]  is set to NO, So Skipping this scenario.");
				skip=true;
				throw new SkipException("Runmode of Scenaio :: " + (count + 1)+" of Module ["+Search_Screen_Names+"] is set to NO, So Skipping this scenario.");
			}else{
				skip=false;
				fnsApps_Report_Logs("****************************************************************************************************************************************");
				fnsApps_Report_Logs("################################## Execution Logs of Scenaio :: "+(count + 1)+" of Module ["+Search_Screen_Names+"]." );
				fnsApps_Report_Logs("");
				
				
				if (!IsBrowserPresentAlready) {
					IsBrowserPresentAlready = fnsTraQtionPortal_LaunchBrowserAndLogin("TraQtion Portal", Login_UserName, Login_Password);
					
					fnsGet_Element_Enabled("TraQtionPortal_TraQtion_Button");
					fnsWait_and_Click("TraQtionPortal_TraQtion_Button");					
					
					for(int a=0; a<=120; a++){ 
						ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
						Integer tabsCount = tabs.size();
						if(tabsCount==2){
							for (int i = 0; i < tabsCount; i++) {
								if( !( (TraQtionPortal_Window.equalsIgnoreCase(tabs.get(i))) ) ){
									driver.switchTo().window(tabs.get(i));
									fnsApps_Report_Logs("PASSED == Successfully Switch to 'TraQtion' Window, jump from 'TraQtion Portal' by clicking on TraQtion button.");
									for(int l=1; l<=120; l++){
										if(driver.findElements(By.xpath(OR_TraQtion.getProperty("TraQtionPortal_Acknowledge_bttn"))).size()>0){
											fnsGet_Element_Enabled("TraQtionPortal_Acknowledge_bttn");
											Thread.sleep(2000);
											fnsWait_and_Click("TraQtionPortal_Acknowledge_bttn");
											Thread.sleep(1000);
											break;
										}
										if(l==120){
											fnsTake_Screen_Shot("Acknowledge_Alert_Not_Coming");
											throw new Exception("FAILED == Acknowledge alert is not coming, after 120 Seconds wait, please refer the screen shot >> Acknowledge_Alert_Not_Coming"+fnsScreenShot_Date_format());
										}
									}
									break;
								}
							}
							break;
						}else{
							Thread.sleep(1000);
						}
						if(a==Long.parseLong(CONFIG.getProperty("ElementWaitTime"))){
							fnsTake_Screen_Shot("Switch_TraQtion_WindowFail");
							throw new Exception("FAILED == 'TraQtion' window is not getting open  by clicking on TraQtion button, after 120 Seconds wait, please refer the screen shot >> Switch_TraQtion_WindowFail"+fnsScreenShot_Date_format());
						}
					}
					
				}
				
				boolean Edit_Screen = true;
				String Edit_Screen_Input_Xpath_Name_from_OR = null;
				String MenuAjax_Link_Xpath = "//a[text()='"+Search_Screen_Names+"s']";				
				fnsMenu_Ajax_Link_Click_By_PassingAjaxPath_TraQtion_WithoutOR(Search_Screen_Names, MenuAjax_Link_Xpath);
				TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
			
				fnsVerify_Application_Navigated_To_Next_Designated_Screen_WithoutOR("'"+Search_Screen_Names+"' Ajax link", Search_Screen_Names, "//div[contains(text(), '"+Search_Screen_Names+"')]");
			
				if( (Search_Screen_Names.toLowerCase().trim()).contains("product")){
					fnsGet_Element_Enabled("TraQtion_Search_ProductTemplateStatus_DRAFT_Status");
					fnsWait_and_Click("TraQtion_Search_ProductTemplateStatus_DRAFT_Status");
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
				}
				
				if( (Search_Screen_Names.toLowerCase().trim()).contains("corrective action")){
					if( (env.equalsIgnoreCase("Test")) /*|| (env.equalsIgnoreCase("staging"))*/){
						fnsGet_Element_Enabled("TraQtion_Search_CorrectiveActions_CarStatus_Pending");
						fnsWait_and_Click("TraQtion_Search_CorrectiveActions_CarStatus_Pending");
					}else{
						/*fnsGet_Element_Enabled("TraQtion_Search_CorrectiveActions_CarStatus_Pending");
						fnsWait_and_Click("TraQtion_Search_CorrectiveActions_CarStatus_Pending");
						Thread.sleep(500);
						fnsGet_Element_Enabled("TraQtion_Search_CorrectiveActions_CarStatus_SUBMITTED");
						fnsWait_and_Click("TraQtion_Search_CorrectiveActions_CarStatus_SUBMITTED");
						Thread.sleep(500);*/
						fnsGet_Element_Enabled("TraQtion_Search_CorrectiveActions_CarStatus_REJECTED");
						fnsWait_and_Click("TraQtion_Search_CorrectiveActions_CarStatus_REJECTED");
					}
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
				}
				
				
				fnsGet_Element_Enabled("TraQtion_Search_Bttn");
				fnsWait_and_Click("TraQtion_Search_Bttn");
				TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
				
				fnsGet_Element_Enabled("TraQtion_Search_FirstRow_FirstColumn_Link");
				String Link_Value = fnsGet_Field_TEXT("TraQtion_Search_FirstRow_FirstColumn_Link");
				fnsWait_and_Click("TraQtion_Search_FirstRow_FirstColumn_Link");
				TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
				
				switch (Search_Screen_Names.toLowerCase().trim()) {
					case("search product") : {
						Edit_Screen_Input_Xpath_Name_from_OR = "TraQtion_EditProduct_ProductName";
						break;
					}
					case("search site") : {
						Edit_Screen_Input_Xpath_Name_from_OR = "TraQtion_EditSite_SiteName";
						break;
					}
					case("search supplier") : {
						fnsGet_Element_Enabled("TraQtion_EditSupplier_InformationTAB");
						fnsWait_and_Click("TraQtion_EditSupplier_InformationTAB");
						TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
						Edit_Screen_Input_Xpath_Name_from_OR = "TraQtion_EditSupplier_AddressLine2";
						break;
					}
					case("search product template") : {
						Edit_Screen_Input_Xpath_Name_from_OR = "TraQtion_EditProductTemplate_ProductTemplateName";
						break;
					}
					case("search corrective action") : {
						Edit_Screen_Input_Xpath_Name_from_OR = "TraQtion_EditCorrectiveAction_ContainmentPlan";
						break;
					}
					case("search document") : {
						if(Search_Screen_Names.toLowerCase().trim().equals("search document")) {
							for(int i=0; i<=NewNsfOnline_Element_Max_Wait_Time; i++){
								if(driver.findElements(By.xpath(OR_TraQtion.getProperty("TraQtion_ViewDocument_Popup"))).size()>0){
									fnsApps_Report_Logs("PASSED == After clicking on '"+Link_Value+"' link, view document popup is getting open. Automation");
									fnsGet_Element_Enabled("TraQtion_ViewDocument_Popup_Close_link");
									fnsWait_and_Click("TraQtion_ViewDocument_Popup_Close_link");
									TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
									break;
								} else{
									Thread.sleep(1000);
								}
								if(i==NewNsfOnline_Element_Max_Wait_Time){
									fnsTake_Screen_Shot("ViewDocument_Popup_Not_Open");
									throw new Exception("PASSED == After clicking on '"+Link_Value+"' link, view document popup is NOT getting open [wait= "+NewNsfOnline_Element_Max_Wait_Time+" seconds], please refer the screen shot >> ViewDocument_Popup_Not_Open"+fnsScreenShot_Date_format());
								}
							}
						}
						Edit_Screen = false;
						break;
					}
					default : {
						fnsApps_Report_Logs("FAILED == Screen Name '"+Search_Screen_Names+"' is not matched with the code.");
						break;
					}
				}
				
				
				if(Edit_Screen){
					fnsVerify_Application_Navigated_To_Next_Designated_Screen_WithoutOR("'"+Link_Value+"' link", Search_Screen_Names.replace("Search", "View"), OR_TraQtion.getProperty("TraQtion_Edit_Bttn"));
				
					fnsGet_Element_Enabled("TraQtion_Edit_Bttn");
					fnsWait_and_Click("TraQtion_Edit_Bttn");
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
					
					/*/if(Search_Screen_Names.toLowerCase().trim().equals("search product template")) {
						for(int i=0; i<=120; i++){
							if(driver.findElements(By.xpath(OR_TraQtion.getProperty("TraQtion_ViewProductTemplate_EditConfirmationBox_YES_bttn"))).size()>0){
								try{
									if(fnsGet_OR_TraQtion_ObjectX("TraQtion_ViewProductTemplate_EditConfirmationBox_YES_bttn").isDisplayed()){
										fnsGet_Element_Enabled("TraQtion_ViewProductTemplate_EditConfirmationBox_YES_bttn");
										fnsWait_and_Click("TraQtion_ViewProductTemplate_EditConfirmationBox_YES_bttn");
										TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
									}
								}catch(Throwable t){
									//nothing to do
								}								
								break;
							}else if(driver.findElements(By.xpath(OR_TraQtion.getProperty("TraQtion_EditProductTemplate_ProductTemplateName"))).size()>0){
								break;
							}else{
								Thread.sleep(500);
							}
						}
					}*/
					
					fnsGet_Element_Enabled(Edit_Screen_Input_Xpath_Name_from_OR);
					fnsGet_OR_TraQtion_ObjectX(Edit_Screen_Input_Xpath_Name_from_OR).clear();
					fnsWait_and_Type(Edit_Screen_Input_Xpath_Name_from_OR, "EDITED - Automation");
					
					fnsGet_Element_Enabled("TraQtion_Save_Bttn");
					fnsWait_and_Click("TraQtion_Save_Bttn");
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
					
					fnsVerify_Validation_Message_TraQtion();					
					
					fnsGet_Element_Enabled("TraQtion_BackToView_Bttn");
					fnsWait_and_Click("TraQtion_BackToView_Bttn");
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);					
					
					fnsGet_Element_Enabled("TraQtion_Back_Bttn");
					fnsWait_and_Click("TraQtion_Back_Bttn");
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
										
					fnsVerify_Application_Navigated_To_Next_Designated_Screen_WithoutOR("'Back' button", Search_Screen_Names, "//div[contains(text(), '"+Search_Screen_Names+"')]");
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);					
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
			throw new Exception(Throwables.getStackTraceAsString(t));
		}		
	}
	
	
	//#######################################################  Configuration Method  ################################################################
	@AfterMethod
	public void reportDataSetResult() {
		if(count>-1){
			if (skip)
				TestUtil.reportDataSetResult(TraQtion_suitexls, this.getClass().getSimpleName(), count + 2 , "SKIP");
			else if (fail) {
				isTestCasePass = false;
				TestUtil.reportDataSetResult(TraQtion_suitexls, this.getClass().getSimpleName(), count + 2, "FAIL");
			} else
				TestUtil.reportDataSetResult(TraQtion_suitexls, this.getClass().getSimpleName(), count + 2, "PASS");
	
			skip = false;
			fail = false;
		}
	}
	
	
	@AfterTest
	public void reportTestResult() throws Throwable {
		TestSuiteBase_MonitorPlan.fns_ReportTestResult_atClassLevel(TraQtion_suitexls,  (this.getClass().getSimpleName()) , isTestCasePass);
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
		return TestUtil.getData(TraQtion_suitexls, this.getClass().getSimpleName());
	}
	
	}	


