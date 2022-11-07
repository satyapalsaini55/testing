package nsf.ecap.TraQtion_Suite;

//satya
//satya
import java.util.ArrayList;
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

public class All_Module_Screen_for_NoError_6 extends TestSuiteBase_TraQtion {
	
	public int count = -1;
		
	
	@Parameters({ "className" })
	@BeforeTest
	public void checkTestSkip(String className) throws Exception {
		if (className.isEmpty()) {
			className = this.getClass().getSimpleName();
		}
		fnsCheckClassLevelTestSkip(className);
	}
	
	
	
	
	@Test(dataProvider = "getTestData", priority = 1)
	public void Verify_AllScreens_of_AllModules_are_Opened_WithOutAnyError_for_Client__(String LoginAs_Name) throws Throwable{
	
		fail = false;
		count++;
		TC_Step=1;	
		try {			
			if ((runmodes[count].equalsIgnoreCase("N"))) {
				fnsApps_Report_Logs("****************************************************************************************************************************************");
				fnsApps_Report_Logs("################################## Runmode of Scenaio :: " + (count + 1)+" of Client ["+LoginAs_Name+"]  is set to NO, So Skipping this scenario.");
				skip=true;
				throw new SkipException("Runmode of Scenaio :: " + (count + 1)+" of Client ["+LoginAs_Name+"] is set to NO, So Skipping this scenario.");
			}else{
				skip=false;
				fnsApps_Report_Logs("****************************************************************************************************************************************");
				fnsApps_Report_Logs("################################## Execution Logs of Scenaio :: "+(count + 1)+" of Client ["+LoginAs_Name+"]." );
				fnsApps_Report_Logs("");
				
				Login_As = LoginAs_Name.trim();

				fnsTraQtionPortal_LaunchBrowserAndLogin("TraQtion Portal", Login_UserName, Login_Password);
				
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
				
			
				
				
				ArrayList<String> Menu_Names_List =  new ArrayList<String>();
				ArrayList<String> Tabs_List = new ArrayList<String>();
				fnsGet_Element_Enabled("TraQtion_Main_Menu_Ajax_Link");
				WebElement Menu_Element = fnsGet_OR_TraQtion_ObjectX("TraQtion_Main_Menu_Ajax_Link");
				
				WebElement VersionLogoImage = fnsGet_OR_TraQtion_ObjectX("TraQtion_VersionLogoImage");			
				VersionLogoImage.click();
				Thread.sleep(500);
				
				Actions action = new Actions(driver);
				action.moveToElement(Menu_Element).build().perform();						
				Thread.sleep(1000);
				
				fnsGet_Element_Enabled("TraQtion_Main_Menu_Div");
				List<WebElement> Menus_All_Ajax_Objects = fnsGet_OR_TraQtion_ObjectX("TraQtion_Main_Menu_Div").findElements(By.tagName("a"));
				for(WebElement Menus_All_Ajax_Elements : Menus_All_Ajax_Objects){
					String Menu_Text = Menus_All_Ajax_Elements.getText().trim();
					Menu_Names_List.add(Menu_Text);
				}
				
				for(int MenuCount=0; MenuCount<Menu_Names_List.size(); MenuCount++){
					String MenuName= Menu_Names_List.get(MenuCount);
					String Menu_Xpath = "//a[text()='"+MenuName+"']";
					boolean Menu_Display = false;
					
					WebElement VersionLogoImage1 = fnsGet_OR_TraQtion_ObjectX("TraQtion_VersionLogoImage");
					VersionLogoImage1.click();
					Thread.sleep(500);	
					WebElement Menu_Element1 = fnsGet_OR_TraQtion_ObjectX("TraQtion_Main_Menu_Ajax_Link");
					Actions action2 = new Actions(driver);
					action2.moveToElement(Menu_Element1).build().perform();	
					for(int k=0; k<10; k++){
						try{
							if(TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(Menu_Xpath).isDisplayed()){
								Menu_Display = true;
								break;
							}
						}catch(Throwable t){
							WebElement Menu_Element21 = fnsGet_OR_TraQtion_ObjectX("TraQtion_Main_Menu_Ajax_Link");
							Actions action32 = new Actions(driver);
							action32.moveToElement(Menu_Element21).build().perform();	
							Thread.sleep(500);
						}
					}
										
					if(Menu_Display){						
						WebElement Menu_Eelement = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(Menu_Xpath);
						Actions Menu = new Actions(driver);
						Menu.moveToElement(Menu_Eelement).click().build().perform();
						fnsApps_Report_Logs("PASSED == Successfully click on the Menu ( "+MenuName+" ) link."); 
						if(MenuName.toLowerCase().trim().contains("create bulk questionnaires")){
							TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(8);
						}else{
							TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
						}
						
						if( (MenuName.toLowerCase().trim().contains("create")) 
								|| (MenuName.toLowerCase().trim().contains("report")) 
								|| (MenuName.toLowerCase().trim().contains("batch"))
								|| (MenuName.toLowerCase().trim().contains("questionnaires"))
							){
							TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
						}else{
							fnsGet_Element_Enabled("TraQtion_Search_Bttn");
							fnsWait_and_Click("TraQtion_Search_Bttn");
							TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
							
							fnsGet_Element_Enabled("TraQtion_Search_Result_Table");
							String ResultTable_Data = fnsGet_OR_TraQtion_ObjectX("TraQtion_Search_Result_Table").getText().trim();
							
							if( (MenuName.toLowerCase().trim().contains("email")) || (MenuName.toLowerCase().trim().contains("job scheduler controller")) ){
								fnsApps_Report_Logs("PASSED == No links are coming for menu ( "+MenuName+" ) on search screen. automation");
							}else if( (ResultTable_Data.toLowerCase().trim().contains("no records found")) ){
								fnsApps_Report_Logs("PASSED == 'No records found' are found for for the menu ( "+MenuName+" ) on search screen. automation");
							}else{
								fnsGet_Element_Enabled("TraQtion_Search_FirstRow_FirstColumn_Link");
								String Search_Screen_Link_Value = fnsGet_Field_TEXT("TraQtion_Search_FirstRow_FirstColumn_Link");
								if( (Search_Screen_Link_Value.toLowerCase().contains("report")) || MenuName.toLowerCase().contains("product document")){
									Search_Screen_Link_Value = fnsGet_Field_TEXT("TraQtion_Search_FirstRow_SecondColumn_Link");
									fnsGet_OR_TraQtion_ObjectX("TraQtion_Search_FirstRow_SecondColumn_Link").click();
								}else{								
									fnsGet_OR_TraQtion_ObjectX("TraQtion_Search_FirstRow_FirstColumn_Link").click();
								}
								fnsApps_Report_Logs("PASSED == successfully click on the link ( "+Search_Screen_Link_Value+" ). ");							
								TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
								
								
								if( (MenuName.toLowerCase().trim().contains("documents")) && !(MenuName.toLowerCase().trim().contains("product")) ){
									fnsGet_Element_Enabled("TraQtion_ViewDocument_Popup_Close_link");
									fnsWait_and_Click("TraQtion_ViewDocument_Popup_Close_link");
									TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
									fnsApps_Report_Logs("PASSED == Successfully verified that all the popup is opened with out any error for record no ("+Search_Screen_Link_Value+").automation");
								}else{
									List<WebElement> Tabs_Objects = null;
									for(int i=0; i<=120; i++){
										try{
											if(driver.findElements(By.xpath(OR_TraQtion.getProperty("TraQtion_TAB_View_div"))).size()>0){
												fnsGet_Element_Enabled("TraQtion_TAB_View_div");
												Tabs_Objects = fnsGet_OR_TraQtion_ObjectX("TraQtion_TAB_View_div").findElements(By.tagName("a"));
												break;
											}else if(driver.findElements(By.xpath(OR_TraQtion.getProperty("TraQtion_TAB_View_div2"))).size()>0){
												fnsGet_Element_Enabled("TraQtion_TAB_View_div2");
												Tabs_Objects = fnsGet_OR_TraQtion_ObjectX("TraQtion_TAB_View_div2").findElements(By.tagName("a"));
												break;
											}else{
												Thread.sleep(1000);
											}
										}catch(Throwable t){
											if(i==120){
												fnsTake_Screen_Shot(Search_Screen_Link_Value+"Navigation_Fail");
												throw new Exception ("FAILED == After clicking on the link ( "+Search_Screen_Link_Value+" ), application is not navigated to next screen, please refer the screen shot >> "+Search_Screen_Link_Value+"Navigation_Fail"+fnsScreenShot_Date_format());
											}else{
												Thread.sleep(1000);
											}
										}
									}
									
									if(Tabs_Objects!=null){
										for(WebElement Tabs_Elements : Tabs_Objects){
											if(Tabs_Elements.isDisplayed()){
												String Tab_Name = Tabs_Elements.getText().trim();
												Tabs_List.add(Tab_Name);
												Actions Tab = new Actions(driver);
												Tab.moveToElement(Tabs_Elements).click().build().perform();
												TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
											}					
										}
									}
									fnsApps_Report_Logs("PASSED == Successfully verified that all the TABs "+Tabs_List+" are displayed with out any error for record no ("+Search_Screen_Link_Value+"). automation");
								}
							}
						}	
					}else{
						fnsApps_Report_Logs("PASSED == Menu ( "+MenuName+" ) exists into the list but it is not visible into the DOM.");
					}
				}
				driver.quit();
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


