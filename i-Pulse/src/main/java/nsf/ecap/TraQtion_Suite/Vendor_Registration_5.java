package nsf.ecap.TraQtion_Suite;

import nsf.ecap.util.TestUtil;

import org.openqa.selenium.NoSuchWindowException;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;

public class Vendor_Registration_5 extends TestSuiteBase_TraQtion {
	
	public int count = -1;
	public TraQtion_Costco_Registration TraQtion_Costco_Registration = new TraQtion_Costco_Registration();
	
	@Parameters({ "className" })
	@BeforeTest
	public void checkTestSkip(String className) throws Exception {
		if (className.isEmpty()) {
			className = this.getClass().getSimpleName();
		}
		fnsCheckClassLevelTestSkip(className);
	}
	
	
	

	
	@Test(dataProvider = "getTestData", priority = 1)
	public void Verify_Vendor_Registration_is_Successfully_done_for_Client__(String Client_Key) throws Throwable{
		
		IsBrowserPresentAlready = false;
		fail = false;
		count++;
		TC_Step=1;	
		try {			
			if ((runmodes[count].equalsIgnoreCase("N"))) {
				fnsApps_Report_Logs("****************************************************************************************************************************************");
				fnsApps_Report_Logs("################################## Runmode of Scenaio :: " + (count + 1)+" of Client ["+Client_Key+"]  is set to NO, So Skipping this scenario.");
				skip=true;
				throw new SkipException("Runmode of Scenaio :: " + (count + 1)+" of Client ["+Client_Key+"] is set to NO, So Skipping this scenario.");
			}else{
				skip=false;
				fnsApps_Report_Logs("****************************************************************************************************************************************");
				fnsApps_Report_Logs("################################## Execution Logs of Scenaio :: "+(count + 1)+" of Client ["+Client_Key+"]." );
				fnsApps_Report_Logs("");
				
				Login_As = Client_Key.trim();
				
				

				if (!IsBrowserPresentAlready) {
					IsBrowserPresentAlready = fnsTraQtionPortal_LaunchBrowserAndLogin("vendor registration", Login_UserName, Login_Password);
				}

				fnsGet_Element_Enabled("Vendor_Registration_Agree_bttn");
				fnsWait_and_Click("Vendor_Registration_Agree_bttn");	
				fnsLoading_Progressing_TraQtion_All(2);
				
				
				
				TraQtion_Costco_Registration.fncType("TraQtionRegistration_Registration_Step1_CorporateInfo_CompanyName", Login_As+"Automation");
				TraQtion_Costco_Registration.fncType("TraQtionRegistration_Registration_Step1_CorporateInfo_AddressLine1", "789 N. Dixboro Road");
				TraQtion_Costco_Registration.fncType("TraQtionRegistration_Registration_Step1_CorporateInfo_City", "Ann Arbor");
				
				
				TestSuiteBase_MonitorPlan.WithOut_OR_fnDD_SelectValue_By_SelectClass_WithoutEnable(OR_TraQtion.getProperty("TraQtionRegistration_Registration_Step1_CorporateInfo_Country_DD"), "United States");
				fnsLoading_Progressing_TraQtion_All(2);
				
				TestSuiteBase_MonitorPlan.WithOut_OR_fnDD_SelectValue_By_SelectClass_WithoutEnable(OR_TraQtion.getProperty("TraQtionRegistration_Registration_Step1_CorporateInfo_StateProvince_DD"), "Michigan");
				fnsLoading_Progressing_TraQtion_All(2);
				
				TraQtion_Costco_Registration.fncType("TraQtionRegistration_Registration_Step1_CorporateInfo_ZipPostalCode", "48105");
									
				
				TraQtion_Costco_Registration.fncType("TraQtionRegistration_Registration_Step1_PrimaryContact_FirstName", "TestFName");
				TraQtion_Costco_Registration.fncType("TraQtionRegistration_Registration_Step1_PrimaryContact_LastName", "TestLName");
				TraQtion_Costco_Registration.fncType("TraQtionRegistration_Registration_Step1_PrimaryContact_Email", "test@automation.com");
				TraQtion_Costco_Registration.fncType("TraQtionRegistration_Registration_Step1_PrimaryContact_Phone", "8484848484");
								
				TraQtion_Costco_Registration.fncClick("TraQtionRegistration_Registration_Step1_FacilityInfo_CopyCorporateInfo_CheckBox");
				fnsLoading_Progressing_TraQtion_All(5);
								
				
				fnsGet_Element_Enabled("TraQtionRegistration_Registration_Step1_FacilityInfo_FacilityName");
				TraQtion_Costco_Registration.fncValue_Fetch_and_Assert("TraQtionRegistration_Registration_Step1_FacilityInfo_FacilityName", Login_As+"Automation");
				
				fnsGet_Element_Enabled("TraQtionRegistration_Registration_Step1_FacilityInfo_AddressLine1");
				TraQtion_Costco_Registration.fncValue_Fetch_and_Assert("TraQtionRegistration_Registration_Step1_FacilityInfo_AddressLine1", "789 N. Dixboro Road");
				
				fnsGet_Element_Enabled("TraQtionRegistration_Registration_Step1_FacilityInfo_City");
				TraQtion_Costco_Registration.fncValue_Fetch_and_Assert("TraQtionRegistration_Registration_Step1_FacilityInfo_City", "Ann Arbor");
					
				fnsGet_Element_Enabled("TraQtionRegistration_Registration_Step1_FacilityInfo_ZipPostalCode");
				TraQtion_Costco_Registration.fncValue_Fetch_and_Assert("TraQtionRegistration_Registration_Step1_FacilityInfo_ZipPostalCode", "48105");
				
				
				TraQtion_Costco_Registration.fncClick("TraQtionRegistration_Registration_Step1_PrimaryFacility_CopyPrimaryInfo_CheckBox");
				fnsLoading_Progressing_TraQtion_All(5);
							
				
				fnsGet_Element_Enabled("TraQtionRegistration_Registration_Step1_PrimaryFacilityContact_FirstName");
				TraQtion_Costco_Registration.fncValue_Fetch_and_Assert("TraQtionRegistration_Registration_Step1_PrimaryFacilityContact_FirstName", "TestFName");
				
				fnsGet_Element_Enabled("TraQtionRegistration_Registration_Step1_PrimaryFacilityContact_LastName");
				TraQtion_Costco_Registration.fncValue_Fetch_and_Assert("TraQtionRegistration_Registration_Step1_PrimaryFacilityContact_LastName", "TestLName");
				
				fnsGet_Element_Enabled("TraQtionRegistration_Registration_Step1_PrimaryFacilityContact_Email");
				TraQtion_Costco_Registration.fncValue_Fetch_and_Assert("TraQtionRegistration_Registration_Step1_PrimaryFacilityContact_Email", "test@automation.com");
					
				fnsGet_Element_Enabled("TraQtionRegistration_Registration_Step1_PrimaryFacilityContact_Phone");
				TraQtion_Costco_Registration.fncValue_Fetch_and_Assert("TraQtionRegistration_Registration_Step1_PrimaryFacilityContact_Phone", "8484848484");
			
				TraQtion_Costco_Registration.fncClick("TraQtionRegistration_Registration_Step1_PrimaryFacilityContact_Authorization_checkBox");
				
				
				TraQtion_Costco_Registration.fncType("TraQtionRegistration_Registration_Step1_PrimaryFacilityContact_Authorization_Name", Login_As);
				
				
				TraQtion_Costco_Registration.fncClick("TraQtionRegistration_Registration_Step1_SaveAndNext_Bttn");
				fnsLoading_Progressing_TraQtion_All(2);
				
				fnsGet_Element_Enabled("Step_CorporateInfo_Section");
				fnsLoading_Progressing_TraQtion_All(2);
				TraQtion_Costco_Registration.fncFind_and_AssertText_into_the_Div("Step_CorporateInfo_Section", Login_As+"Automation");
				
				TraQtion_Costco_Registration.fncFind_and_AssertText_into_the_Div("Step_CorporateInfo_Section", "48105");
				
				
				TraQtion_Costco_Registration.fncFind_and_AssertText_into_the_Div("Step_PrimaryFacilityContact_Section", "8484848484");
				
				TraQtion_Costco_Registration.fncFind_and_AssertText_into_the_Div("Step_PrimaryFacilityContact_Section", "test@automation.com");
				
				
				TraQtion_Costco_Registration.fncClick("TraQtionRegistration_Registration_Step_Submit_Bttn");
				fnsLoading_Progressing_TraQtion_All(2);
				
				fnsGet_Element_Enabled("TraQtionRegistration_Registration_Step_NextPage_Message_Div");
				fnsLoading_Progressing_TraQtion_All(2);
				TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element_BY_OFFSET(OR_TraQtion.getProperty("TraQtionRegistration_Registration_Step_NextPage_Message_Div"), 30, 30);
				String Div_Text=driver.getPageSource().toLowerCase().trim();
							
				try{
					assert ( (Div_Text.toLowerCase()).contains("Registration Submitted Successfully".toLowerCase().trim())   &&  (Div_Text.toLowerCase()).contains("Thank you for registering. You will receive the confirmation and user account emails shortly.".toLowerCase().trim())   ):
						"FAILED == After clicking on 'Submit' button, success message is not coming, Please refer Screen shot >> UpdatedMsgNotComing"+fnsScreenShot_Date_format();
					fnsApps_Report_Logs("PASSED ==  After clicking on 'Submit' button, success message is coming.");
								
				}catch(Throwable t){
					isTestCasePass = false;
					fnsTake_Screen_Shot("UpdatedMsgNotComing");
					fnsApps_Report_Logs(t.getMessage());
					throw new Exception(t.getMessage());
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
			IsBrowserPresentAlready = false;
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
		TestSuiteBase_MonitorPlan.fns_ReportTestResult_atClassLevel(TraQtion_suitexls,  (this.getClass().getSimpleName()), isTestCasePass );
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


