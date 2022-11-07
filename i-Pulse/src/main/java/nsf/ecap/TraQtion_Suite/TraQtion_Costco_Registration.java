package nsf.ecap.TraQtion_Suite;

import java.util.List;

import nsf.ecap.MonitorPlan_Suite.TestSuiteBase_MonitorPlan;
import nsf.ecap.util.ErrorUtil;
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


public class TraQtion_Costco_Registration extends TestSuiteBase_TraQtion{
	
	public Integer tabsCount;
	public Integer count = -1;
	
	
		
//######### Scripts variables Starts ##################################
	public String ScenaioName = null;
		
	public String TraQtionRegistration_Registration_Step1_CorporateInfo_AddressLine1 = null;
	public String TraQtionRegistration_Registration_Step1_CorporateInfo_City = null;
	public String TraQtionRegistration_Registration_Step1_CorporateInfo_Country_DD = null;
	public String TraQtionRegistration_Registration_Step1_CorporateInfo_StateProvince_DD = null;
	public String TraQtionRegistration_Registration_Step1_CorporateInfo_ZipPostalCode = null;
	
	public String TraQtionRegistration_Registration_Step1_PrimaryContact_FirstName = null;
	public String TraQtionRegistration_Registration_Step1_PrimaryContact_LastName = null;
	public String TraQtionRegistration_Registration_Step1_PrimaryContact_Email = null;
	public String TraQtionRegistration_Registration_Step1_PrimaryContact_Phone = null;
	
	
	public String TraQtionRegistration_Registration_Step2_AddEditProduct_Popup_SelectCountry_DD = null;
		
		
	public String TraQtionRegistration_Registration_Step3_CardHolderName = null;
	public String TraQtionRegistration_Registration_Step3_ZipCode = null;
	public String TraQtionRegistration_Registration_Step3_CreditCardType_DD = null;
	public String TraQtionRegistration_Registration_Step3_CreditCardNumber = null;
	public String TraQtionRegistration_Registration_Step3_SecurityCodeCVV = null;	
	public String TraQtionRegistration_Registration_Step3_ExpirationMonth = null;
	public String TraQtionRegistration_Registration_Step3_ExpirationYear = null;	
	
	@Parameters({ "className" })
	@BeforeTest
	public void checkTestSkip(String className) throws Exception {
		if (className.isEmpty()) {
			className = this.getClass().getSimpleName();
		}
		IsBrowserPresentAlready =false;
		fnsCheckClassLevelTestSkip(className);
	}
	
	
	

	
		
	
	
	
	
	
	@Test(dataProvider = "getTestData", priority = 1 )
	public void TraQtionCostcoRegistration_Verify_AllSteps_are_Working_Fine_to_Complete_Registration_for_(String Case_No, String Scenario_Name) throws Throwable{
		
		fnsApps_Report_Logs("=========================================================================================================================================");
				
		count++;
				
		ScenaioName = Scenario_Name.trim();
		
		TraQtionRegistration_Registration_Step1_CorporateInfo_CompanyName =  fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "CompanyName").trim();
		TraQtionRegistration_Registration_Step1_CorporateInfo_AddressLine1 =  fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "AddressLine1");
		TraQtionRegistration_Registration_Step1_CorporateInfo_City =  fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "City");
		TraQtionRegistration_Registration_Step1_CorporateInfo_Country_DD =  fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Country_DD");
		TraQtionRegistration_Registration_Step1_CorporateInfo_StateProvince_DD =  fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "StateProvince_DD");
		TraQtionRegistration_Registration_Step1_CorporateInfo_ZipPostalCode =  fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "ZipPostalCode");

		TraQtionRegistration_Registration_Step1_PrimaryContact_FirstName =  fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "FirstName");
		TraQtionRegistration_Registration_Step1_PrimaryContact_LastName =  fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "LastName");
		TraQtionRegistration_Registration_Step1_PrimaryContact_Email =   fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Email");
		TraQtionRegistration_Registration_Step1_PrimaryContact_Phone =  fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Phone");
		
		TraQtionRegistration_Registration_Step2_AddEditProduct_Popup_SelectCountry_DD =  fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "SelectCountry_DD");
		
		
		TraQtionRegistration_Registration_Step3_CardHolderName =  fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "CardHolderName");
		TraQtionRegistration_Registration_Step3_ZipCode =  fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "ZipCode");
		TraQtionRegistration_Registration_Step3_CreditCardType_DD =  fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "CreditCardType_DD");
		TraQtionRegistration_Registration_Step3_CreditCardNumber =  fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "CreditCardNumber");
		TraQtionRegistration_Registration_Step3_SecurityCodeCVV =  fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "SecurityCodeCVV");
		TraQtionRegistration_Registration_Step3_ExpirationMonth =  fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "ExpirationMonth");
		TraQtionRegistration_Registration_Step3_ExpirationYear =  fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "ExpirationYear");
		
		
		
		try {
			
			if (!(runmodes[count].equalsIgnoreCase("Y"))) {
				fnsApps_Report_Logs("****************************************************************************************************************************************");
				fnsApps_Report_Logs("################################## Runmode of Case::" + (count + 1)+" for Scenaio["+ScenaioName+"]  is set to NO, So Skipping this Case.");
				skip=true;
				throw new SkipException("Runmode of Case::" + (count + 1)+" for Scenaio["+ScenaioName+"]  is set to NO, So Skipping this Case.");
			}else{
				skip=false;
				
				fnsApps_Report_Logs("****************************************************************************************************************************************");
				fnsTraQtionPortal_LaunchBrowserAndLogin(ScenaioName, fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Full_Name"), fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Email_Address"));
				fnsApps_Report_Logs("Browser is launched and ******** Successfully login into 'TraQtion Costco Registration' Application.");
				fnsApps_Report_Logs("****************************************************************************************************************************************");
								
				fnsApps_Report_Logs("################################## Execution Logs of Case::"+(count + 1)+" for Scenaio["+ScenaioName+"].");
				
			
				
// ########################################### Step 1  ###############################################################################################################################

				fncClick("TraQtionRegistration_Registration_Agree_Bttn");
				fnsLoading_Progressingwait_TraQtionOnline(2);
				
				
				if(ScenaioName.equalsIgnoreCase("Register_New_Supplier")){
					fncType("TraQtionRegistration_Registration_Step1_CorporateInfo_CompanyName", TraQtionRegistration_Registration_Step1_CorporateInfo_CompanyName);
					fncType("TraQtionRegistration_Registration_Step1_CorporateInfo_AddressLine1", TraQtionRegistration_Registration_Step1_CorporateInfo_AddressLine1);
					fncType("TraQtionRegistration_Registration_Step1_CorporateInfo_City", TraQtionRegistration_Registration_Step1_CorporateInfo_City);
					
					
					TestSuiteBase_MonitorPlan.WithOut_OR_fnDD_SelectValue_By_SelectClass(OR_TraQtion.getProperty("TraQtionRegistration_Registration_Step1_CorporateInfo_Country_DD"), TraQtionRegistration_Registration_Step1_CorporateInfo_Country_DD);
					fnsLoading_Progressingwait_TraQtionOnline(2);
					
					TestSuiteBase_MonitorPlan.WithOut_OR_fnDD_SelectValue_By_SelectClass(OR_TraQtion.getProperty("TraQtionRegistration_Registration_Step1_CorporateInfo_StateProvince_DD"), TraQtionRegistration_Registration_Step1_CorporateInfo_StateProvince_DD);
					fnsLoading_Progressingwait_TraQtionOnline(2);
					
					fncType("TraQtionRegistration_Registration_Step1_CorporateInfo_ZipPostalCode", TraQtionRegistration_Registration_Step1_CorporateInfo_ZipPostalCode);
										
					
					fncType("TraQtionRegistration_Registration_Step1_PrimaryContact_FirstName", TraQtionRegistration_Registration_Step1_PrimaryContact_FirstName);
					fncType("TraQtionRegistration_Registration_Step1_PrimaryContact_LastName", TraQtionRegistration_Registration_Step1_PrimaryContact_LastName);
					fncType("TraQtionRegistration_Registration_Step1_PrimaryContact_Email", TraQtionRegistration_Registration_Step1_PrimaryContact_Email);
					fncType("TraQtionRegistration_Registration_Step1_PrimaryContact_Phone", TraQtionRegistration_Registration_Step1_PrimaryContact_Phone);
									
					fncClick("TraQtionRegistration_Registration_Step1_FacilityInfo_CopyCorporateInfo_CheckBox");
					fnsLoading_Progressingwait_TraQtionOnline(2);
					Thread.sleep(2000);
					
					
					fnsGet_Element_Enabled("TraQtionRegistration_Registration_Step1_FacilityInfo_FacilityName");
					fncValue_Fetch_and_Assert("TraQtionRegistration_Registration_Step1_FacilityInfo_FacilityName", TraQtionRegistration_Registration_Step1_CorporateInfo_CompanyName);
					
					fnsGet_Element_Enabled("TraQtionRegistration_Registration_Step1_FacilityInfo_AddressLine1");
					fncValue_Fetch_and_Assert("TraQtionRegistration_Registration_Step1_FacilityInfo_AddressLine1", TraQtionRegistration_Registration_Step1_CorporateInfo_AddressLine1);
					
					fnsGet_Element_Enabled("TraQtionRegistration_Registration_Step1_FacilityInfo_City");
					fncValue_Fetch_and_Assert("TraQtionRegistration_Registration_Step1_FacilityInfo_City", TraQtionRegistration_Registration_Step1_CorporateInfo_City);
						
					fnsGet_Element_Enabled("TraQtionRegistration_Registration_Step1_FacilityInfo_ZipPostalCode");
					fncValue_Fetch_and_Assert("TraQtionRegistration_Registration_Step1_FacilityInfo_ZipPostalCode", TraQtionRegistration_Registration_Step1_CorporateInfo_ZipPostalCode);
					
					
					fncClick("TraQtionRegistration_Registration_Step1_PrimaryFacility_CopyPrimaryInfo_CheckBox");
					fnsLoading_Progressingwait_TraQtionOnline(2);
					Thread.sleep(2000);
					
					
					fnsGet_Element_Enabled("TraQtionRegistration_Registration_Step1_PrimaryFacilityContact_FirstName");
					fncValue_Fetch_and_Assert("TraQtionRegistration_Registration_Step1_PrimaryFacilityContact_FirstName", TraQtionRegistration_Registration_Step1_PrimaryContact_FirstName);
					
					fnsGet_Element_Enabled("TraQtionRegistration_Registration_Step1_PrimaryFacilityContact_LastName");
					fncValue_Fetch_and_Assert("TraQtionRegistration_Registration_Step1_PrimaryFacilityContact_LastName", TraQtionRegistration_Registration_Step1_PrimaryContact_LastName);
					
					fnsGet_Element_Enabled("TraQtionRegistration_Registration_Step1_PrimaryFacilityContact_Email");
					fncValue_Fetch_and_Assert("TraQtionRegistration_Registration_Step1_PrimaryFacilityContact_Email", TraQtionRegistration_Registration_Step1_PrimaryContact_Email);
						
					fnsGet_Element_Enabled("TraQtionRegistration_Registration_Step1_PrimaryFacilityContact_Phone");
					fncValue_Fetch_and_Assert("TraQtionRegistration_Registration_Step1_PrimaryFacilityContact_Phone", TraQtionRegistration_Registration_Step1_PrimaryContact_Phone);
				}
				
				if(ScenaioName.equalsIgnoreCase("Register_New_Facility_for_Existing_Supplier")){
					fncType("TraQtionRegistration_Registration_Step1_FacilityInfo_FacilityName", TraQtionRegistration_Registration_Step1_CorporateInfo_CompanyName);
					fncType("TraQtionRegistration_Registration_Step1_FacilityInfo_AddressLine1", TraQtionRegistration_Registration_Step1_CorporateInfo_AddressLine1);
					fncType("TraQtionRegistration_Registration_Step1_FacilityInfo_City", TraQtionRegistration_Registration_Step1_CorporateInfo_City);
					
					
					TestSuiteBase_MonitorPlan.WithOut_OR_fnDD_SelectValue_By_SelectClass(OR_TraQtion.getProperty("TraQtionRegistration_Registration_Step1_FacilityInfo_Country_DD_Click"), TraQtionRegistration_Registration_Step1_CorporateInfo_Country_DD);
					fnsLoading_Progressingwait_TraQtionOnline(2);
					
					TestSuiteBase_MonitorPlan.WithOut_OR_fnDD_SelectValue_By_SelectClass(OR_TraQtion.getProperty("TraQtionRegistration_Registration_Step1_FacilityInfo_StateProvince_DD_Click"), TraQtionRegistration_Registration_Step1_CorporateInfo_StateProvince_DD);
					fnsLoading_Progressingwait_TraQtionOnline(2);
					
					fncType("TraQtionRegistration_Registration_Step1_FacilityInfo_ZipPostalCode", TraQtionRegistration_Registration_Step1_CorporateInfo_ZipPostalCode);
										
					
					fncType("TraQtionRegistration_Registration_Step1_PrimaryFacilityContact_FirstName", TraQtionRegistration_Registration_Step1_PrimaryContact_FirstName);
					fncType("TraQtionRegistration_Registration_Step1_PrimaryFacilityContact_LastName", TraQtionRegistration_Registration_Step1_PrimaryContact_LastName);
					fncType("TraQtionRegistration_Registration_Step1_PrimaryFacilityContact_Email", TraQtionRegistration_Registration_Step1_PrimaryContact_Email);
					fncType("TraQtionRegistration_Registration_Step1_PrimaryFacilityContact_Phone", TraQtionRegistration_Registration_Step1_PrimaryContact_Phone);	
				}
				
								
				fncClick("TraQtionRegistration_Registration_Step1_SaveAndNext_Bttn");
				fnsLoading_Progressingwait_TraQtionOnline(2);
				
				
// ########################################### Step 2  ###############################################################################################################################

				//Does your company/facility manufacture under the Private Label? -click Yes
				fncClick("TraQtionRegistration_Registration_Step2_ProductInformation_YES_Radio_Bttn");
				
				fncClick("TraQtionRegistration_Registration_Step2_ClickHereToAddProduct_Link");
				fnsLoading_Progressingwait_TraQtionOnline(2);
				Thread.sleep(1500);
				
				driver.switchTo().frame(0);
				
				TestSuiteBase_MonitorPlan.WithOut_OR_fnDD_SelectValue_By_SelectClass(OR_TraQtion.getProperty("TraQtionRegistration_Registration_Step2_AddEditProduct_Popup_SelectCountry_DD"), TraQtionRegistration_Registration_Step2_AddEditProduct_Popup_SelectCountry_DD);
				Thread.sleep(2000);
				
				fncClick("TraQtionRegistration_Registration_Step2_AddEditProduct_Popup_Department_CheckBox");
				Thread.sleep(1000);
				
				fncType("TraQtionRegistration_Registration_Step2_AddEditProduct_Popup_DescribeProduct", "Automation Test");
				
				fncClick("TraQtionRegistration_Registration_Step2_AddEditProduct_Popup_Save_Bttn");
				Thread.sleep(2000);
				
				driver.switchTo().defaultContent();
				
				for(int i=0; i<10; i++){
					if(driver.findElements(By.xpath(OR_TraQtion.getProperty("TraQtionRegistration_Registration_Step2_ClickHereToAddProduct_Table_1Row_RecordEdit"))).size()>0){
						Thread.sleep(1000);
						break;
					}else{
						Thread.sleep(1000);
					}
				}
					
				fnsGet_Element_Enabled("TraQtionRegistration_Registration_Step2_ClickHereToAddProduct_Table_1Row");
				TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element(OR_TraQtion.getProperty("TraQtionRegistration_Registration_Step2_ClickHereToAddProduct_Table_1Row"));
				String GetText=fnsGet_Field_TEXT("TraQtionRegistration_Registration_Step2_ClickHereToAddProduct_Table_1Row").trim();	
				try{
					assert !(GetText.toLowerCase()).contains("No Region Selected".toLowerCase()):"FAILED == Records saved from 'Add/Edit Product' Popup is not updated into the table, Please refer Screen shot >> RecordNotUpdated"+fnsScreenShot_Date_format();
					fnsApps_Report_Logs("PASSED ==  Records saved from 'Add/Edit Product' Popup is updated into the table.");
					
				}catch(Throwable t){
					isTestCasePass = false;
					fnsTake_Screen_Shot("RecordNotUpdated");
					fnsApps_Report_Logs(t.getMessage());
					throw new Exception(t.getMessage());
				}
				
						
				fncClick("TraQtionRegistration_Registration_Step2_IamAuthorizedToRegister_CheckBox");
				
				fncType("TraQtionRegistration_Registration_Step2_Name", TraQtionRegistration_Registration_Step1_CorporateInfo_CompanyName);
				
				
				
				//New Mandatory fields are added on 26.5.2017
				fncClick("TraQtionRegistration_Registration_Step2_ProductInformation_HasCompletedAny3PartyAudit_Radio_Bttn");
				fncClick("TraQtionRegistration_Registration_Step2_ProductInformation_DoesMoreThan25Employees_Radio_Bttn");
				
				
				
				
				fncClick("TraQtionRegistration_Registration_Step2_SaveAndNext_Bttn");
				fnsLoading_Progressingwait_TraQtionOnline(2);
				
				
// ########################################### Step 3  ###############################################################################################################################
				/*fnsGet_Element_Enabled("TraQtionRegistration_Registration_Step3_CardHolderName");
				Thread.sleep(500);*/
				fncType("TraQtionRegistration_Registration_Step3_CardHolderName", TraQtionRegistration_Registration_Step3_CardHolderName);
				
				fncType("TraQtionRegistration_Registration_Step3_ZipCode", TraQtionRegistration_Registration_Step3_ZipCode);
				
				TestSuiteBase_MonitorPlan.WithOut_OR_fnDD_SelectValue_By_SelectClass(OR_TraQtion.getProperty("TraQtionRegistration_Registration_Step3_CreditCardType_DD"), TraQtionRegistration_Registration_Step3_CreditCardType_DD);
				Thread.sleep(2000);
				
				
				fncType("TraQtionRegistration_Registration_Step3_CreditCardNumber", TraQtionRegistration_Registration_Step3_CreditCardNumber);
				
				fncType("TraQtionRegistration_Registration_Step3_SecurityCodeCVV", TraQtionRegistration_Registration_Step3_SecurityCodeCVV);
				
				
				TestSuiteBase_MonitorPlan.WithOut_OR_fnDD_SelectValue_By_SelectClass(OR_TraQtion.getProperty("TraQtionRegistration_Registration_Step3_ExpirationMonth"), TraQtionRegistration_Registration_Step3_ExpirationMonth);
				Thread.sleep(2000);
				
				TestSuiteBase_MonitorPlan.WithOut_OR_fnDD_SelectValue_By_SelectClass(OR_TraQtion.getProperty("TraQtionRegistration_Registration_Step3_ExpirationYear"), TraQtionRegistration_Registration_Step3_ExpirationYear);
				Thread.sleep(2000);
				
				
				fncClick("TraQtionRegistration_Registration_Step3_PayNow_Bttn");
				fnsLoading_Progressingwait_TraQtionOnline(2);
				
				fncClick("TraQtionRegistration_Registration_Step3_ProceedWithRegistration_Bttn");
				fnsLoading_Progressingwait_TraQtionOnline(2);
				
				
				
// ########################################### Step 4  ###############################################################################################################################

				fnsGet_Element_Enabled("Step_CorporateInfo_Section");
				fncFind_and_AssertText_into_the_Table("Step_CorporateInfo_Section", TraQtionRegistration_Registration_Step1_CorporateInfo_CompanyName);
				
				fncFind_and_AssertText_into_the_Table("Step_CorporateInfo_Section", TraQtionRegistration_Registration_Step1_CorporateInfo_ZipPostalCode);
				
				
				fncFind_and_AssertText_into_the_Table("Step_PrimaryFacilityContact_Section", TraQtionRegistration_Registration_Step1_PrimaryContact_Email);
				
				fncFind_and_AssertText_into_the_Table("Step_PrimaryFacilityContact_Section", TraQtionRegistration_Registration_Step1_PrimaryContact_Phone);
				
				fncFind_and_AssertText_into_the_Table("Step_PaymentDetails_Section", TraQtionRegistration_Registration_Step1_CorporateInfo_CompanyName);
								
				if(ScenaioName.equalsIgnoreCase("Register_New_Supplier")){
					fncClick("TraQtionRegistration_Registration_Step_Submit_Bttn");
				}else{
					fncClick("TraQtionRegistration_Registration_Step_SubmitForApproval_Bttn");
				}
				fnsLoading_Progressingwait_TraQtionOnline(3);
				
				
				fnsGet_Element_Enabled("TraQtionRegistration_Registration_Step_NextPage_Message_Div");
				TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element_BY_OFFSET(OR_TraQtion.getProperty("TraQtionRegistration_Registration_Step_NextPage_Message_Div"), 30, 30);
				//String Div_Text=fnsGet_Field_TEXT("TraQtionRegistration_Registration_Step_NextPage_Message_Div").toLowerCase().trim(); // Getting report generate fail
				String Div_Text=driver.getPageSource().toLowerCase().trim();
							
				try{
					if(ScenaioName.equalsIgnoreCase("Register_New_Supplier")){
						assert ( (Div_Text.toLowerCase()).contains("Registration Submitted Successfully".toLowerCase().trim())  && (Div_Text.trim()).contains("Thank you for registering. You will receive a confirmation email shortly.".toLowerCase().trim())  ):
							"FAILED == After clicking on 'Submit' button, success message is not coming, Please refer Screen shot >> UpdatedMsgNotComing"+fnsScreenShot_Date_format();
						fnsApps_Report_Logs("PASSED ==  After clicking on 'Submit' button, success message is coming.");
					}else{
						assert ( (Div_Text.toLowerCase()).contains("Registration initiated successfully".toLowerCase().trim())  && (Div_Text.trim()).contains("Thank you for initiating the registration. An email has been sent to the Supplier’s corporate contact person. The email contains a link for them to access and complete the registration".toLowerCase().trim())  ):
							"FAILED == After clicking on 'Submit For Approval' button, update message is not coming, Please refer Screen shot >> UpdatedMsgNotComing"+fnsScreenShot_Date_format();
						fnsApps_Report_Logs("PASSED ==  After clicking on 'Submit For Approval' button, update message is coming.");
					}					
				}catch(Throwable t){
					isTestCasePass = false;
					fnsTake_Screen_Shot("UpdatedMsgNotComing");
					fnsApps_Report_Logs(t.getMessage());
					throw new Exception(t.getMessage());
				}
				
			}
		}catch(SkipException sk){
				throw new SkipException(sk.getMessage());
				
			
		}catch(Throwable t){
			fail = true;
			isTestCasePass = false;
			fnsApps_Report_Logs(" "+Throwables.getStackTraceAsString(t));
			throw new Exception(" "+Throwables.getStackTraceAsString(t));
		}						
		
}

	

	
	
	
	
	
	
	
	
	
	
	
	
	
	//#############################################################  Class Script Method Start #########################################################################################	
	
		//Function to type
		public void fncType(String XpathKey, String value) throws Exception {
			try{
				fnsGet_Element_Enabled(XpathKey);
				fnsWait_and_Type(XpathKey, value);
			}catch(Throwable t){
				isTestCasePass = false;
				fnsApps_Report_Logs(t.getMessage());
				throw new Exception(t.getMessage());	}
		}	
		
		//Function to Click
		public void fncClick(String XpathKey) throws Exception {
			try{
				fnsGet_Element_Enabled(XpathKey);
				fnsWait_and_Click(XpathKey);
			}catch(Throwable t){
				isTestCasePass = false;
				fnsApps_Report_Logs(t.getMessage());
				throw new Exception(t.getMessage());	}
		}	
		

		//Verify Text fetched and matched with expected text.
		public void fncValue_Fetch_and_Assert(String XpathKey, String MatchingText) throws Exception {
			fnsGet_Element_Enabled(XpathKey);
			TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element(OR_TraQtion.getProperty(XpathKey));
			String GetText=fnsGet_OR_TraQtion_ObjectX(XpathKey).getAttribute("value");	
			try{
				assert (GetText.toLowerCase()).contains(MatchingText.toLowerCase()):"FAILED == Actual value ("+GetText+") is not matched with the Expected value ("+MatchingText+"), Please refer Screen shot >> TextNotMatch"+fnsScreenShot_Date_format();
				fnsApps_Report_Logs("PASSED ==  Actual value ("+GetText+") is matched with the Expected value ("+MatchingText+").");
				
			}catch(NoSuchWindowException W){
				isTestCasePass = false;
				throw new Exception( W.getMessage());		}
			
			catch(Throwable t){
				isTestCasePass = false;
				fnsTake_Screen_Shot("TextNotMatch");
				fnsApps_Report_Logs(t.getMessage());
				throw new Exception(t.getMessage());		}
		}	
		
		
		//function to select drop down value
		public void fncFind_and_AssertText_into_the_Table(String TableXpath, String MatchingValue) throws Exception {
			
			boolean ValueNotMatched=true;
			try{
				fnsGet_Element_Enabled(TableXpath);
				List<WebElement> Tableobjectlists=fnsGet_OR_TraQtion_ObjectX(TableXpath).findElements(By.tagName("td"));
					for(WebElement Table_value:Tableobjectlists){
						String Table_Text = Table_value.getText().toLowerCase().trim();
						//System.out.println("********** "+ TableXpath+" satya"+Table_Text);
						if(Table_Text.equals(MatchingValue.toLowerCase().trim())){
							Actions act = new Actions(driver);
							act.moveToElement(Table_value, 20, 10).build().perform();
							ValueNotMatched=false;
							break;
						}
					}
				if(ValueNotMatched==true){
					throw new Exception("FAILED == Value<"+MatchingValue+"> is not matched into <"+TableXpath+"> section, Please refer screenshot [ ValueNotMatch" +  fnsScreenShot_Date_format());
				}	
				fnsApps_Report_Logs("PASSED == Value<"+MatchingValue+"> is matched into <"+TableXpath+"> section");
			}catch(NoSuchWindowException W){
				isTestCasePass = false;
				throw new Exception(W.getMessage());			}
			catch(Throwable t) {
				isTestCasePass = false;
				fnsTake_Screen_Shot("ValueNotMatch");
				fnsApps_Report_Logs(t.getMessage().trim());
				throw new Exception(t.getMessage().trim());}
		}
		
		
		public void fncFind_and_AssertText_into_the_Div(String MainDivXpath, String MatchingValue) throws Exception {
			
			boolean ValueNotMatched=true;
			try{
				fnsGet_Element_Enabled(MainDivXpath);
				List<WebElement> Tableobjectlists=fnsGet_OR_TraQtion_ObjectX(MainDivXpath).findElements(By.tagName("span"));
					for(WebElement Table_value:Tableobjectlists){
						String Table_Text = Table_value.getText().toLowerCase().trim();
						//System.out.println("********** "+ TableXpath+" satya"+Table_Text);
						if(Table_Text.equals(MatchingValue.toLowerCase().trim())){
							Actions act = new Actions(driver);
							act.moveToElement(Table_value, 20, 10).build().perform();
							ValueNotMatched=false;
							break;
						}
					}
				if(ValueNotMatched==true){
					throw new Exception("FAILED == Value<"+MatchingValue+"> is not matched into <"+MainDivXpath+"> section, Please refer screenshot [ ValueNotMatch" +  fnsScreenShot_Date_format());
				}	
				fnsApps_Report_Logs("PASSED == Value<"+MatchingValue+"> is matched into <"+MainDivXpath+"> section");
			}catch(NoSuchWindowException W){
				isTestCasePass = false;
				throw new Exception(W.getMessage());			}
			catch(Throwable t) {
				isTestCasePass = false;
				fnsTake_Screen_Shot("ValueNotMatch");
				fnsApps_Report_Logs(t.getMessage().trim());
				throw new Exception(t.getMessage().trim());}
		}
	//#############################################################  Class Script Method END #########################################################################################		
		
		
		
		
	
	

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
		

	@AfterMethod
	public void QuitBrowser(){
		if(!skip){
			MoveMouseCursorToTaskBar();
			driver.quit();
		}
	}
	
	
	
	@DataProvider
	public Object[][] getTestData() {
		return getExcelData_for_DataProvider(TraQtion_suitexls, this.getClass().getSimpleName());
	}

	
	
}	
	
	
	
	
	
	
	

