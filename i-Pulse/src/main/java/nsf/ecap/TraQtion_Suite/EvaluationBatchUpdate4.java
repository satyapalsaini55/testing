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

public class EvaluationBatchUpdate4 extends TestSuiteBase_TraQtion{
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
	public void download_Evaluation_Batch_File_And_Upload(String evaluationBatchFile) throws Throwable{
		fail = false;
		count++;
		TC_Step=1;	
		try{
			if ((runmodes[count].equalsIgnoreCase("N"))) {
				fnsApps_Report_Logs("****************************************************************************************************************************************");
				fnsApps_Report_Logs("################################## Runmode of Scenaio :: " + (count + 1)+" of Module ["+evaluationBatchFile+"]  is set to NO, So Skipping this scenario.");
				skip=true;
				throw new SkipException("Runmode of Scenaio :: " + (count + 1)+" of Module ["+evaluationBatchFile+"] is set to NO, So Skipping this scenario.");
			}else{
				skip=false;
				fnsApps_Report_Logs("****************************************************************************************************************************************");
				fnsApps_Report_Logs("################################## Execution Logs of Scenaio :: "+(count + 1)+" of Module ["+evaluationBatchFile+"]." );
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
