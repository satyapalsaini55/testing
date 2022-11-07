package nsf.ecap.Alerts_iPulse_Suite;

import java.util.List;

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


public class Alerts_Navigation_3 extends TestSuiteBase_Alerts {
	
	
	
@Parameters({ "className" })
@BeforeTest
public void checkTestSkip(String className) throws Exception {
	if (className.isEmpty()) {
		className = this.getClass().getSimpleName();
	}
	fnsCheckClassLevelTestSkip(className);
	
}

	

	
	
	
	
	//IPM-10676
	@Test(dataProvider = "getTestData", priority = 1)
	public void Verify_Alerts_FirstRecord_Alllinks_Navigation_for__LoginUser___(String Serial_Number, String SecureLoginAs_UserName) throws Throwable{
		BrowserOpen = false;
		fail = false;
		count++;
		Login_As_UserName = SecureLoginAs_UserName.trim();
		TC_Step=1;	
		
		
		
		
		
		
		try {
			
			if (!(runmodes[count].equalsIgnoreCase("Y"))) {
				fnsApps_Report_Logs("****************************************************************************************************************************************");
				fnsApps_Report_Logs("################################## Runmode of Scenario::" + (count + 1)+" for Login User ["+Login_As_UserName+"]  is set to NO, So Skipping this Scenario.");
				skip=true;
				throw new SkipException("Runmode of Scenario::" + (count + 1)+" for Login User ["+Login_As_UserName+"]  is set to NO, So Skipping this Scenario.");
			}else{
				skip=false;
				fnsApps_Report_Logs("****************************************************************************************************************************************");
				fnsApps_Report_Logs("################################## Execution Logs of Scenario::"+(count + 1)+" for Login User ["+Login_As_UserName+"].");
				BrowserOpen = fnsBrowserLaunchAndLogin(Login_As_UserName);
				
				
				
				
				String All_Alerts_Xpath = "//legend[@class='ui-fieldset-legend ui-corner-all ui-state-default']";
				Integer Total_Alerts_Count = driver.findElements(By.xpath(All_Alerts_Xpath)).size();
				
				try{
					assert Total_Alerts_Count!=0 : "FAILED == Alerts are not coming, please refer the screenshot >> Alerts_not_Coming"+fnsScreenShot_Date_format();
				}catch(Throwable t){
					fnsTake_Screen_Shot("Alerts_not_Coming");
					throw new Exception(Throwables.getStackTraceAsString(t));
				}
				
				
				for(int i=1; i<=Total_Alerts_Count; i++){
					Total_Alerts_Run_Count ++;
					String Per_Alert_Xpath = "("+All_Alerts_Xpath+")["+i+"]";
					
					if(driver.findElements(By.xpath("//legend[@class='ui-fieldset-legend ui-corner-all ui-state-default ui-state-hover']")).size()>0){
						Per_Alert_Xpath = "("+All_Alerts_Xpath+")["+(i-1)+"]";
					}
					
					
					WebElement Per_Alert_Element = WithOut_OR_fnGet_ObjectX(Per_Alert_Xpath);
					String Alert_Name = Per_Alert_Element.getText().trim();
																				
					try{
						Actions act = new Actions(driver);
						act.moveToElement(Per_Alert_Element).click().build().perform();
						fnsApps_Report_Logs("PASSED == Click done on  alert  >>>>>>>  "+Alert_Name);
					}catch(Throwable t){
						fnsTake_Screen_Shot("Alerts_Click_Fail");
						throw new Exception("FAILED == Clicking on <"+Alert_Name+"> Alert is getting FAIL, please refer the attached screenshot >>"+fnsScreenShot_Date_format() +" Getting Exception >> "+Throwables.getStackTraceAsString(t));
					}
					
					fnsLoading_Progressingwait_Alert_iPulse(3, 120);
					
					String Per_alert_FirstRow_Xpath = "(//tbody[@class='ui-datatable-data ui-widget-content']/tr[1])["+i+"]";
					String Per_alert_FirstRow_Text = WithOut_OR_fnGet_ObjectX(Per_alert_FirstRow_Xpath).getText().toLowerCase().trim();
					List<WebElement> Per_alert_FirstRow_links_Objects = driver.findElement(By.xpath(Per_alert_FirstRow_Xpath)).findElements(By.tagName("a"));
					Integer Links_Count_PerRecord = Per_alert_FirstRow_links_Objects.size();
					
					try{
						assert !( Per_alert_FirstRow_Text.contains("loading") || Per_alert_FirstRow_Text.contains("no result found") ):
							"FAILED == 'No Result Found' or 'Loading' is displayed instead of data ------- ["+Alert_Name+"], please refer the screenshot >> NoResultFound_Fail"+fnsScreenShot_Date_format();
					}catch(Throwable t){
						fnsTake_Screen_Shot("NoResultFound_Fail");
						throw new Exception(Throwables.getStackTraceAsString(t));
					}
					
					
					
					
					
					
					
										
//					for(int link=Links_Count_PerRecord; link>=1; link--){
					for(int link=1; link<=Links_Count_PerRecord; link++){
						boolean Navigated_to_NextScreen = false;
						boolean Navigated_to_Popup = false;
						String Links_Text_Per_Record = "";
//						String Links_Xpath_Per_Record = "(//tbody[@class='ui-datatable-data ui-widget-content']/tr[2])["+i+"]"+"/preceding::a["+link+"]";
						String Links_Xpath_Per_Record = "(//a[text()='Export To Excel'])["+i+"]/following::a["+link+"]";
						WebElement Per_alert_FirstRow_links = WithOut_OR_fnGet_ObjectX(Links_Xpath_Per_Record);
						Links_Text_Per_Record = Per_alert_FirstRow_links.getText().trim();
						System.out.println("Links_Text_Per_Record = "+Links_Text_Per_Record);
						
						
						if( !(Links_Text_Per_Record.equalsIgnoreCase("Edit Comment")) && (Links_Text_Per_Record.length()>2) ){
							for(int j=1; j<10; j++){
								try{
									Actions act2 = new Actions(driver);
									act2.moveToElement(Per_alert_FirstRow_links).click().build().perform();	
									break;
								}catch(Throwable t){
									Thread.sleep(500);
								}
							}
							
							for(int l=1; l<=50; l++){
								try{
									if(driver.findElements(By.xpath("//div[@id='mainForm:cmntDlg' and @aria-hidden='false']")).size()>0){
										WithOut_OR_fnGet_ObjectX("//a[@class='ui-commandlink ui-widget' and text()='Cancel']").click();
										System.out.println("PASSED == Back button cancel done.");
										Navigated_to_Popup = true;
										break;
									}else if(driver.findElements(By.xpath("//span[@class='ui-button-text ui-c' and text()='Back']")).size()>0){
										Navigated_to_NextScreen = true;
										break;
									}else if(driver.findElements(By.xpath("//span[@class='ui-button-text ui-c' and text()='Back To View']")).size()>0){
										Navigated_to_NextScreen = true;
										break;
									}else{
										Thread.sleep(100);
									}
								}catch(Throwable t){
									//nothing to do
								}
							}
							
							fnsLoading_Progressingwait_Alert_iPulse(1, 120);
							
							
							
							
							
							
							
							if(Navigated_to_NextScreen){
								/*String Navigated_Screen_Text = driver.getPageSource().trim();
								fnsApps_Report_Logs("Navigated_Screen_Text length = "+Navigated_Screen_Text.length());
								if( (PageSourceLength > Navigated_Screen_Text.length()) || (PageSourceLength == 0) ){
									PageSourceLength = Navigated_Screen_Text.length();
								}
								*/
								if(driver.findElements(By.xpath("//span[@class='ui-button-text ui-c' and text()='Back']")).size()>0){
									WithOut_OR_fnGet_ObjectX("//span[@class='ui-button-text ui-c' and text()='Back']").click();
									System.out.println("PASSED == Back button click done.");
								}else if(driver.findElements(By.xpath("//span[@class='ui-button-text ui-c' and text()='Back To View']")).size()>0){
									WithOut_OR_fnGet_ObjectX("//span[@class='ui-button-text ui-c' and text()='Back To View']").click();
									System.out.println("PASSED == Back to view button click done.");
								}else{
									WithOut_OR_fnGet_ObjectX("//a[@id='headerForm:home']").click();
									System.out.println("PASSED == Home link click done.");
								}
								fnsLoading_Progressingwait_Alert_iPulse(1, 120);
								
								
								if(driver.findElements(By.xpath("//span[@class='ui-button-text ui-c' and text()='Back']")).size()>0){
									WithOut_OR_fnGet_ObjectX("//span[@class='ui-button-text ui-c' and text()='Back']").click();
									System.out.println("PASSED == Back button click done.");
									fnsLoading_Progressingwait_Alert_iPulse(1, 120);
								}else if(driver.findElements(By.xpath("//span[@class='ui-button-text ui-c' and text()='Back To View']")).size()>0){
									WithOut_OR_fnGet_ObjectX("//span[@class='ui-button-text ui-c' and text()='Back To View']").click();
									System.out.println("PASSED == Back to view button click done.");
									fnsLoading_Progressingwait_Alert_iPulse(1, 120);
								}
								
							}else if(Navigated_to_Popup){
								for(int l=1; l<=50; l++){
									try{
										if(driver.findElements(By.xpath("//div[@id='mainForm:cmntDlg' and @aria-hidden='true']")).size()>0){
											break;
										}else{
											Thread.sleep(100);
										}
									}catch(Throwable t){
										//nothing to do
									}
								}
							}else{
								fnsTake_Screen_Shot("Blank_Screen");
								throw new Exception("FAILED == Blank Screen is coming after clicking on the link <"+Links_Text_Per_Record+"> of the alert ["+Alert_Name+"], please refer the screenshot >> Blank_Screen"+fnsScreenShot_Date_format());
							}
							
							
							
							
							
							
							
							fnsApps_Report_Logs("********* Successfully verified that the Navigation is working fine for link = "+Links_Text_Per_Record);
						//	break;			
						}	
						
					}
				}
			
			}
			/*fnsApps_Report_Logs("satya Lowest Page source length = "+PageSourceLength);	*/
		}catch(SkipException sk){
			throw new SkipException("Runmode of Scenario::" + (count + 1)+" for Loigin User Name ["+Login_As_UserName+"]  is set to NO, So Skipping this Scenario.");
			
		}catch(NoSuchWindowException W){
			fail = true;
			isTestCasePass = false;
			throw new Exception(W.getMessage());
			
		}catch(Throwable t){
			fail = true;
			isTestCasePass = false;
			/*fnsApps_Report_Logs("satya Lowest Page source length = "+PageSourceLength);*/	
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
}

	
//################################################################## Config Method ############################################################################

	@AfterMethod
	public void reportDataSetResult() {
		if(count>-1){
			if (skip)
				TestUtil.reportDataSetResult(Alerts_iPulse_suitexls, this.getClass().getSimpleName(), count + 2 , "SKIP");
			else if (fail) {
				isTestCasePass = false;
				TestUtil.reportDataSetResult(Alerts_iPulse_suitexls, this.getClass().getSimpleName(), count + 2, "FAIL");
			} else
				TestUtil.reportDataSetResult(Alerts_iPulse_suitexls, this.getClass().getSimpleName(), count + 2, "PASS");
	
			skip = false;
			fail = false;
		}
	}
	
	
	
	@AfterTest
	public void reportTestResult() throws Throwable {
		TestSuiteBase_MonitorPlan.fns_ReportTestResult_atClassLevel(Alerts_iPulse_suitexls,  (this.getClass().getSimpleName()) , isTestCasePass);
	}
	

	@AfterMethod
	public void QuitBrowser() throws Throwable{
		try{
			driver.quit();
		}catch(Throwable t){
			//nothing to do			
		}		
	}
	
	
	@DataProvider
	public Object[][] getTestData() {
		return TestUtil.getData(Alerts_iPulse_suitexls, this.getClass().getSimpleName());
	}


}
