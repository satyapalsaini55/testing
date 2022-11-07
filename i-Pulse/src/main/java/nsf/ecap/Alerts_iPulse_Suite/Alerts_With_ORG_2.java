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


public class Alerts_With_ORG_2 extends TestSuiteBase_Alerts {
	
	
	
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
	public void Verify_Alerts_Load_Time_and_Count_by_ORG_Unit_for___(String Serial_Number, String SecureLoginAs_UserName, String ORG_Units) throws Throwable{
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
				fnsApps_Report_Logs("################################## Execution Logs of Scenario::"+(count + 1)+" for Login User ["+Login_As_UserName+"] and ORG Unit ["+ORG_Units+"].");
				BrowserOpen = fnsBrowserLaunchAndLogin(Login_As_UserName);
				
				
				String ORG_Radio_bttn_Xpath = "//input[@name='mainForm:alertTab:alertsspecrdo']/following::label[normalize-space(text())='Org Unit']";
				WithOut_OR_fnGet_Element_Enabled(ORG_Radio_bttn_Xpath);
				WithOut_OR_fnGet_ObjectX(ORG_Radio_bttn_Xpath).click();;
				TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
				WithOut_OR_fnGet_Element_Enabled("(//span[@class='ui-icon ui-icon-triangle-1-s ui-c'])[2]");
				WithOut_OR_fnDD_SelectValue_By_ContainsText("(//span[@class='ui-icon ui-icon-triangle-1-s ui-c'])[2]", "//div[@class='ui-selectonemenu-items-wrapper']/ul", "li", ORG_Units.trim());
				WithOut_OR_fnGet_ObjectX("//button[@id='mainForm:alertTab:showAlerts']").click();;				
				TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
				TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
				
/*				String All_Alerts_Xpath = "//legend[@class='ui-fieldset-legend ui-corner-all ui-state-default']";*/
				String All_Alerts_Xpath = "//legend[contains(@class, 'ui-fieldset-legend ui-corner-all ui-state-default')]";
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
					
					/*if(driver.findElements(By.xpath("//legend[@class='ui-fieldset-legend ui-corner-all ui-state-default ui-state-hover']")).size()>0){
						Per_Alert_Xpath = "("+All_Alerts_Xpath+")["+(i-1)+"]";
					}*/
					
					
					WebElement Per_Alert_Element = WithOut_OR_fnGet_ObjectX(Per_Alert_Xpath);
					String Alert_Name = Per_Alert_Element.getText().trim();
					String Alert_RecordCount_fromAlertName = Alert_Name.split(" ")[0].trim();
					Integer Alert_RecordCount_Integer = Integer.parseInt(Alert_RecordCount_fromAlertName);
										
					try{
						Actions act = new Actions(driver);
						act.moveToElement(Per_Alert_Element).click().build().perform();
						fnsApps_Report_Logs("PASSED == Click done on  alert  >>>>>>>  "+Alert_Name);
					}catch(Throwable t){
						fnsTake_Screen_Shot("Alerts_Click_Fail");
						throw new Exception("FAILED == Clicking on <"+Alert_Name+"> Alert is getting FAIL, please refer the attached screenshot >>"+fnsScreenShot_Date_format() +" Getting Exception >> "+Throwables.getStackTraceAsString(t));
					}
					Integer Per_Alert_Loading_Time = 0;
					/*
					try{
						Per_Alert_Loading_Time = fnsLoading_Progressingwait_Alert_iPulse(3, 10);
					}catch(Throwable t){
						throw new Exception ("[ "+Alert_Name+" ] : "+Throwables.getStackTraceAsString(t));
					}					
					fnsApps_Report_Logs("********* successfully verified that the loading time is < "+Per_Alert_Loading_Time+" > seconds. ");*/
					
					try{
						Per_Alert_Loading_Time = fnsLoading_Progressingwait_Alert_iPulse(3, 20);
						if(Per_Alert_Loading_Time<=10){
							fnsApps_Report_Logs("********* successfully verified that the loading time is < "+Per_Alert_Loading_Time+" > seconds. ");
						}else{
							fnsTake_Screen_Shot("Alert_Load_Fail");
							throw new Exception("FAILED == Alert is taking <"+Per_Alert_Loading_Time+"> seconds to open which is more than 10 seconds, please refer the screen shot >> Alert_Load_Fail"+fnsScreenShot_Date_format());							
						}
					}catch(Throwable t){
						throw new Exception ("[ "+Alert_Name+" ] : "+Throwables.getStackTraceAsString(t));
					}
					
					
					
					
					try{
						if(Alert_RecordCount_Integer>10){
							String Per_Alert_Pagination_RecordCount = fnc_return_Alerts_PaginationCount(Alert_Name, Alert_RecordCount_fromAlertName, i);;
							try{
								assert Alert_RecordCount_fromAlertName.equals(Per_Alert_Pagination_RecordCount): "FAILED == Pagination's count("+Per_Alert_Pagination_RecordCount+") and alert's count("+Alert_RecordCount_fromAlertName+") are NOT matched ------- [ "+Alert_Name+" ], please refer the screenshot >> Pagination_Count_Fail"+fnsScreenShot_Date_format();
								fnsApps_Report_Logs("********* successfully verified that the Pagination counts are MATCHED.");	
							}catch(Throwable t){
								fnsTake_Screen_Shot("Pagination_Count_Fail");
								throw new Exception(Throwables.getStackTraceAsString(t));
							}
						}else{
							String Per_alert_Records_Table_Xpath = "(//tbody[@class='ui-datatable-data ui-widget-content'])["+i+"]";
							List<WebElement> Per_Alert_DataTable_Objects = driver.findElement(By.xpath(Per_alert_Records_Table_Xpath)).findElements(By.tagName("tr"));
							Integer RowCount =  Per_Alert_DataTable_Objects.size();
							
							for(WebElement Per_Alert_DataTable_Elememnt : Per_Alert_DataTable_Objects){
								Actions act2 = new Actions(driver);
								act2.moveToElement(Per_Alert_DataTable_Elememnt).build().perform();
								String Per_Alert_DataTable_Elememnt_Text = "";
								if(RowCount<3){
									Per_Alert_DataTable_Elememnt_Text = Per_Alert_DataTable_Elememnt.getText().toLowerCase().trim();
									if(!( Per_Alert_DataTable_Elememnt_Text.contains("uploading") )){
										try{
											assert !( Per_Alert_DataTable_Elememnt_Text.contains("loading") || Per_Alert_DataTable_Elememnt_Text.contains("no result found") ):
												"FAILED == 'No Result Found' or 'Loading' is displayed instead of data ------- ["+Alert_Name+"], please refer the screenshot >> NoResultFound_Fail"+fnsScreenShot_Date_format();
											break;
										}catch(Throwable t){
											fnsTake_Screen_Shot("NoResultFound_Fail");
											throw new Exception(Throwables.getStackTraceAsString(t));
										}
									}
								}
														
							}	
								
							
							try{
								assert Alert_RecordCount_Integer.equals(RowCount): "FAILED == Records count("+RowCount+") and alert's count("+Alert_RecordCount_fromAlertName+") are NOT matched ------- [ "+Alert_Name+" ], please refer the screenshot >> Alert_Record_Count_Fail"+fnsScreenShot_Date_format();
								fnsApps_Report_Logs("********* successfully verified that the records counts are MATCHED.");
							}catch(Throwable t){
								fnsTake_Screen_Shot("AlertRecords_Count_Fail");
								throw new Exception(Throwables.getStackTraceAsString(t));
							}
							
						}
					}catch(Throwable t){
						if( (Alert_Name.toLowerCase().trim().contains("Alert for Work Orders with Migration Reviewed button not clicked yet".toLowerCase())) ){
							//as per IPM-10823 for the time being the alert is ignored. 
						}else{
							throw new Exception(Throwables.getStackTraceAsString(t));
						}
					}
					
				}
			
			}	
		}catch(SkipException sk){
			throw new SkipException("Runmode of Scenario::" + (count + 1)+" for Loigin User Name ["+Login_As_UserName+"]  is set to NO, So Skipping this Scenario.");
			
		}catch(NoSuchWindowException W){
			fail = true;
			isTestCasePass = false;
			throw new Exception(W.getMessage());
			
		}catch(Throwable t){
			fail = true;
			isTestCasePass = false;
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
}

/*public String fnc_return_Alerts_PaginationCount(String Alert_Name, String Alert_RecordCount_fromAlertName, Integer LastAlertContentTable) throws Throwable{
	String Per_Alert_Pagination_RecordCount = "";
	String Per_Alert_Pagination_RecordsCount_Xpath = "";
	
	for(int j=1; j<=30; j++){
		try{
			String Pagination_Xpath = "//span[@class='ui-paginator-current']";
			Integer All_Pagination_Elements = driver.findElements(By.xpath(Pagination_Xpath)).size();					
			Per_Alert_Pagination_RecordsCount_Xpath = "("+Pagination_Xpath+")["+All_Pagination_Elements+"]";
			
			WebElement Per_Alert_Pagination_RecordsCount_Element = driver.findElement(By.xpath(Per_Alert_Pagination_RecordsCount_Xpath));
			Actions act1 = new Actions(driver);
			act1.moveToElement(Per_Alert_Pagination_RecordsCount_Element).build().perform();
			Per_Alert_Pagination_RecordCount = Per_Alert_Pagination_RecordsCount_Element.getText().trim();
			Per_Alert_Pagination_RecordCount = Per_Alert_Pagination_RecordCount.split("of")[1].trim();
		}catch(Throwable t){
			//nothing to do
		}
		if(Alert_RecordCount_fromAlertName.equals(Per_Alert_Pagination_RecordCount)){
			break;
		}else{
			Thread.sleep(500);
		}
		try{
			if(j==30){
				String Per_alert_Records_Table_Xpath = "(//tbody[@class='ui-datatable-data ui-widget-content'])["+LastAlertContentTable+"]";
				List<WebElement> Per_Alert_DataTable_Objects = driver.findElement(By.xpath(Per_alert_Records_Table_Xpath)).findElements(By.tagName("tr"));
							
				for(WebElement Per_Alert_DataTable_Elememnt : Per_Alert_DataTable_Objects){
					Actions act2 = new Actions(driver);
					act2.moveToElement(Per_Alert_DataTable_Elememnt).build().perform();
					try{
						assert !( Per_Alert_DataTable_Elememnt.getText().toLowerCase().trim().contains("loading") || Per_Alert_DataTable_Elememnt.getText().toLowerCase().trim().contains("no result found") ):
							"FAILED == 'No Result Found' or 'Loading' is displayed instead of data ------- ["+Alert_Name+"], please refer the screenshot >> NoResultFound_Fail"+fnsScreenShot_Date_format();
						break;
					}catch(Throwable t){
						fnsTake_Screen_Shot("NoResultFound_Fail");
						throw new Exception(Throwables.getStackTraceAsString(t));
					}
				}
			}
		}catch (Throwable t){
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}
	return Per_Alert_Pagination_RecordCount;
}
	*/
	
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
