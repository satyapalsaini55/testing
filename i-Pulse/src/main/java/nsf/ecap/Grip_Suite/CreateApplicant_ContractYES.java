package nsf.ecap.Grip_Suite;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;

public class CreateApplicant_ContractYES extends TestSuiteBase_Grip {



@Parameters({ "className" })
@BeforeTest
public void checkTestSkip(String className) throws Exception {
	IsBrowserPresentAlready =false;
	if (className.isEmpty()) {
		className = this.getClass().getSimpleName();		}
	
	fnsCheckClassLevelTestSkip(className);
}

//################################################ Class Variables Starts #####################################################################
public Integer Before_DataAdded = null; 
public Integer NewlyAddedRowNo = null;
public Integer MatchingText_RowXpath_Count = null;

public String Row_Xpath = null;
public String Comments_Free_Text = "AutomationTest >> Grip_Create Applicant-Already have Signed Contract - YES[BS-03], Date/Time=" + fnIssueCreationText_Date_format();
public String PdfFile_Location =System.getProperty("user.home")+"\\Downloads"; 
public String TextFetched = null;
public String TextFetched_After = null;




@Test( priority = 0)
public void Launch_Browser_and_Successfully_Login_into_the_Application() throws Throwable{
	fnsApps_Report_Logs("################################## [BS - 03]  Create Applicant-Already have Signed Contract - YES");
	if (!IsBrowserPresentAlready) {
		IsBrowserPresentAlready = fnsLaunchBrowserAndLogin();
		fnsApps_Report_Logs("Browser is launched and Successfully login into Application");	
	}
	fnsApps_Report_Logs("=========================================================================================================================================");
}



@Test( priority = 1, dependsOnMethods={"Launch_Browser_and_Successfully_Login_into_the_Application"}, description="[Steps<Login into application >Menu >Create Contractor Applicant -> Add First name,last name,Selection Division and Department. Select Yes radio button and Click Create.>,   Expected<Contractor created successfully.>]")
public void CreateContractorApplicant_AlreadyHaveSignedContract_YES_andVerifyApplicantCreatedSuccessfully() throws Throwable{
	fnsApps_Report_Logs("############# Test Case ::::::1 CreateContractorApplicant_AlreadyHaveSignedContract_YES_andVerifyApplicantCreatedSuccessfully ");
	try{	
		fncMenu_Ajax_Link_Click_By_PassingAjaxPath("CreateContractorApplicant_Ajax_Link");
		fnsGet_Element_Enabled("IPulse_Footer");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
	
		fnsGet_Element_Enabled("OnboardingCreateContractor_Profile_Type_DD_Click");
		fnsDD_value_Select_By_MatchingText_DownKeyPress("OnboardingCreateContractor_Profile_Type_DD_Click", "OnboardingCreateContractor_Profile_Type_DD_Value", "li", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Type_DD"));
		
		fnsGet_Element_Enabled("OnboardingCreateContractor_Profile_WorkPhone");
		fnsWait_and_Type("OnboardingCreateContractor_Profile_WorkPhone", fnsEditClient_Date_format());
		
		
		fnsGet_Element_Enabled("OnboardingCreateContractor_Profile_FirstName");
		fnsWait_and_Type("OnboardingCreateContractor_Profile_FirstName", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "FirstName"));
	
	
		fnsGet_Element_Enabled("OnboardingCreateContractor_Profile_LastName");
		fnsWait_and_Type("OnboardingCreateContractor_Profile_LastName", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "LastName"));
		
		fnsGet_Element_Enabled("OnboardingCreateContractor_Profile_Email");
		fnsWait_and_Type("OnboardingCreateContractor_Profile_Email", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Email"));
	
		
		//Added on 25.4.2015 as field made mandatory
		fnsGet_Element_Enabled("OnboardingCreateContractor_Profile_CellPhone");
		fnsWait_and_Type("OnboardingCreateContractor_Profile_CellPhone", fnsEditClient_Date_format());
		
		fnsGet_Element_Enabled("OnboardingCreateContractor_Division_Division_DD_Click");
		Thread.sleep(3000); // Wait Added as DD is not getting load
		fnsDD_value_Select_By_MatchingText_DownKeyPress("OnboardingCreateContractor_Division_Division_DD_Click", "OnboardingCreateContractor_Division_Division_DD_Value", "li", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Division_DD"));
			
		Thread.sleep(1000);
		fnsGet_Element_Enabled("OnboardingCreateContractor_Division_Department_DD_Click");
		fnsWait_and_Click("OnboardingCreateContractor_Profile_FirstName");
		Thread.sleep(1000);
		try{
			boolean ValueNotMatched=true;
			fnsWait_and_Click("OnboardingCreateContractor_Division_Department_DD_Click");
			fnsGet_Element_Enabled("OnboardingCreateContractor_Division_Department_DD_Value");
			List<WebElement> DDobjectlists=fnsGet_OR_Grip_ObjectX("OnboardingCreateContractor_Division_Department_DD_Value").findElements(By.tagName("li"));
			Actions action = new Actions(driver);
			for(WebElement dd_value:DDobjectlists){
				action.sendKeys(Keys.ARROW_DOWN).build().perform();		
				if(dd_value.getText().equals(fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Department_DD"))){
					Thread.sleep(250);
					action.moveToElement(dd_value).click(dd_value).build().perform();
					ValueNotMatched=false;
					break;
				}
			}
			if(ValueNotMatched==true){
				throw new Exception("Excel value is not matched with DropDown Value.");
			}	
			fnsApps_Report_Logs("PASSED == 'Division Department DD' value selection done in 1st attampt.");
			
		//	fnsDD_value_Select_By_MatchingText_DownKeyPress("OnboardingCreateContractor_Division_Department_DD_Click", "OnboardingCreateContractor_Division_Department_DD_Value", "li", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Department_DD"));
		}catch(Throwable t){
			Thread.sleep(500);
			fnsWait_and_Click("OnboardingCreateContractor_Division_Department_DD_Click");
			Thread.sleep(4000);
			fnsDD_value_Select_By_MatchingText_DownKeyPress("OnboardingCreateContractor_Division_Department_DD_Click", "OnboardingCreateContractor_Division_Department_DD_Value", "li", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Department_DD"));
			fnsApps_Report_Logs("PASSED == 'Division Department DD' value selection done in 2nd attampt.");
		}
		
		
		
		fnsDD_value_Select_From_Filter_CheckBox_And_Radio_Select_DD("OnboardingCreateContractor_Division_Role_DD_Click", "OnboardingCreateContractor_Division_Role_DD_Radio_Bttn", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Role_DD"));
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//fnsDD_value_Select_By_MatchingText_DownKeyPress("OnboardingCreateContractor_Division_Role_DD_Click", "OnboardingCreateContractor_Division_Role_DD_Value", "li", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Role_DD"));
		
		fnsGet_Element_Enabled("OnboardingCreateContractor_Division_OfficeLocation_DD_Click");
		fnsDD_value_Select_By_MatchingText_DownKeyPress("OnboardingCreateContractor_Division_OfficeLocation_DD_Click", "OnboardingCreateContractor_Division_OfficeLocation_DD_Value", "li", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "OfficeLocation_DD"));
		
		//Added on 25.4.2015 as field made mandatory
		fnsGet_Element_Enabled("OnboardingCreateContractor_Division_PaymentOffice_DD_Click");
		fnsDD_value_Select_By_MatchingText_DownKeyPress("OnboardingCreateContractor_Division_PaymentOffice_DD_Click", "OnboardingCreateContractor_Division_PaymentOffice_DD_Value", "li", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "PaymentOffice_DD"));
		
		fnsGet_Element_Enabled("OnboardingCreateContractor_PrimaryAddress_Address");
		fnsWait_and_Type("OnboardingCreateContractor_PrimaryAddress_Address", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "PrimaryAddress_Address"));
	
	
		fnsGet_Element_Enabled("OnboardingCreateContractor_PrimaryAddress_City");
		fnsWait_and_Type("OnboardingCreateContractor_PrimaryAddress_City", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "PrimaryAddress_City"));
		
		TestSuiteBase_MonitorPlan.WithOut_OR_fnDD_SelectValue_Through_Filter(OR_Grip.getProperty("OnboardingCreateContractor_PrimaryAddress_CountryDD_Click"), OR_Grip.getProperty("OnboardingCreateContractor_PrimaryAddress_CountryDD_Filter"), fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Country_DD"));
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		Thread.sleep(2000);
		
		fnsGet_Element_Enabled("OnboardingCreateContractor_PrimaryAddress_StateDD_Click");
		fnsDD_value_Select_By_MatchingText_DownKeyPress("OnboardingCreateContractor_PrimaryAddress_StateDD_Click", "OnboardingCreateContractor_PrimaryAddress_StateDD_Value", "li", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "State_DD"));
		
		fnsGet_Element_Enabled("OnboardingCreateContractor_PrimaryAddress_PostalCode");
		fnsWait_and_Type("OnboardingCreateContractor_PrimaryAddress_PostalCode", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "PrimaryAddress_PostalCode"));
	
		
		fnsGet_Element_Enabled("OnboardingCreateContractor_BusinessAddressSupplier_Address");
		fnsWait_and_Type("OnboardingCreateContractor_BusinessAddressSupplier_Address", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "BusinessAddressSupplier_Address"));
	
		fnsGet_Element_Enabled("OnboardingCreateContractor_BusinessAddressSupplier_City");
		fnsWait_and_Type("OnboardingCreateContractor_BusinessAddressSupplier_City", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "BusinessAddressSupplier_City"));
	
		fnsGet_Element_Enabled("OnboardingCreateContractor_BusinessAddressSupplier_BusinessName");
		fnsWait_and_Type("OnboardingCreateContractor_BusinessAddressSupplier_BusinessName", "AutomationBName");
		//YES Radio Button Click
		fnsGet_Element_Enabled("OnboardingCreateContractor_SignedContract_YES_Bttn");
		fnsWait_and_Click("OnboardingCreateContractor_SignedContract_YES_Bttn");
		Thread.sleep(5000);
		
	//	fnsGet_Element_Enabled("OnboardingCreateContractor_EffectiveDate");
	//	fnsWait_and_Type("OnboardingCreateContractor_EffectiveDate", fnsReturn_Requried_YearDate(0));
		fncCalendarDatePicker_BySelectValues_From_DropDown(OR_Grip.getProperty("OnboardingCreateContractor_EffectiveDate_Bttn"), null, null);
		
		fnsGet_Element_Enabled("OnboardingCreateContractor_Manager_LK_Bttn");
		fnsWait_and_Click("OnboardingCreateContractor_Manager_LK_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		fnsGet_Element_Enabled("OnboardingCreateContractor_Manager_LK_Text");
		fnsWait_and_Type("OnboardingCreateContractor_Manager_LK_Text", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Manager_LK_Text"));
		TestSuiteBase_MonitorPlan.fnsLookup_RadioBttn_Select();
			
		//Upload file
		try{
			String FileUploadPath = System.getProperty("user.dir") + "\\docs\\This is test pdf document.pdf";
			WebElement Browser = fnsGet_OR_Grip_ObjectX("OnboardingCreateContractor_UploadSignedContract_BrowseFile");
			Browser.sendKeys(FileUploadPath);
			
			for(int wait=0; wait<=(Long.parseLong(CONFIG.getProperty("ElementWaitTime"))/2); wait++){
				if(driver.findElements(By.xpath(OR_Grip.getProperty("EditResource_ContractTab_WIPContracts_AddPopup_BrowseFile_Progress"))).size()>0){
					Thread.sleep(1000);
				}else{
					Thread.sleep(1000);
					fnsApps_Report_Logs("PASSED == "+"Create Contractor : 'Upload Signed Contract'"+": File is Successfully Upload.");
					break;
				}
				if(wait==(Long.parseLong(CONFIG.getProperty("ElementWaitTime"))/2)){
					fnsTake_Screen_Shot("FileUploadFail");
					throw new Exception("FAILED == "+"Create Contractor : 'Upload Signed Contract'"+": File is not getting Upload, TimeOut after <"+(Long.parseLong(CONFIG.getProperty("ElementWaitTime"))/2)+">Seconds, Please refer screenshot >> FileUploadFail"+fnsScreenShot_Date_format());
				}
			}
		
		}catch(NoSuchWindowException W){
			isTestCasePass = false;
			throw new SkipException(W.getMessage());
		}catch(Throwable t){
			isTestCasePass = false;
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));	}
		
		
			
	//	fnsGet_Element_Enabled("OnboardingCreateContractor_ContractDate");
	//	fnsWait_and_Type("OnboardingCreateContractor_ContractDate", fnsReturn_Requried_YearDate(0));
		fncCalendarDatePicker_BySelectValues_From_DropDown(OR_Grip.getProperty("OnboardingCreateContractor_ContractDate_Bttn"), null, null);
		
	//	fnsGet_Element_Enabled("OnboardingCreateContractor_ExpiryDate");
	//	fnsWait_and_Type("OnboardingCreateContractor_ExpiryDate", fnsReturn_Requried_YearDate(1));
		fncCalendarDatePicker_BySelectValues_From_DropDown(OR_Grip.getProperty("OnboardingCreateContractor_ExpiryDate_Bttn"), null, fnsReturn_Requried_Year(1));
		
		fnsGet_Element_Enabled("OnboardingCreateContractor_NSFEmailAccount_YES_Bttn");
		fnsWait_and_Click("OnboardingCreateContractor_NSFEmailAccount_YES_Bttn");
		
		
		fnsGet_Element_Enabled("CreateContractor_Profile_Manager_LK_Bttn");
		fnsWait_and_Click("CreateContractor_Profile_Manager_LK_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		fnsGet_Element_Enabled("CreateContractor_Profile_Manager_LK_Text");
		fnsWait_and_Type("CreateContractor_Profile_Manager_LK_Text", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Manager_LK_Text"));
		TestSuiteBase_MonitorPlan.fnsLookup_RadioBttn_Select();
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		//Added on 12.12.2016 as mandatory
		Thread.sleep(1000);
		fnsGet_Element_Enabled("OnboardingCreateContractor_Division_RemoteResource_DD_Click");
		fnsDD_value_Select_By_MatchingText_WithOut_DownKeyPress("OnboardingCreateContractor_Division_RemoteResource_DD_Click", "OnboardingCreateContractor_Division_RemoteResource_DD_Value", "li", "No");
		
		
		
		fnsGet_Element_Enabled("OnboardingCreateContractor_Create_Bttn");
		fnsWait_and_Click("OnboardingCreateContractor_Create_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		Thread.sleep(2000);
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
	
	fncAssert_Contains(OR_Grip.getProperty("ApplicationsAccessRequest_Popup_Tilte"), "Applications Access Request", "'Applications Access Request' Popup is not getting opened");
		
		fnsGet_Element_Enabled("ApplicationsAccessRequest_Popup_Comments");
		fnsWait_and_Type("ApplicationsAccessRequest_Popup_Comments", Comments_Free_Text);
		
		//New lines added as new mandatory field added
		fnsGet_Element_Enabled("ApplicationsAccessRequest_Popup_NSF_IC_Contact_LK_Bttn");
		fnsWait_and_Click("ApplicationsAccessRequest_Popup_NSF_IC_Contact_LK_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		fnsGet_Element_Enabled("ApplicationsAccessRequest_Popup_NSF_IC_Contact_LK_Text");
		fnsWait_and_Type("ApplicationsAccessRequest_Popup_NSF_IC_Contact_LK_Text", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "NSF_IC_Contact_LK_Text"));
		TestSuiteBase_MonitorPlan.fnsLookup_RadioBttn_Select();
		Thread.sleep(1500);
		
		
		
		fnsGet_Element_Enabled("ApplicationsAccessRequest_Popup_SaveApplicationsAccessRequest_Bttn");
		fnsWait_and_Click("ApplicationsAccessRequest_Popup_SaveApplicationsAccessRequest_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(5);
		
	
		fncVerify_DataUpdated_Successfully("IPulse_ValidationMessage_Div", "Onboarding Create Contractor");
		
		for(int i=0; i<=Long.parseLong(CONFIG.getProperty("ElementWaitTime"));i++){
			if(driver.findElements(By.xpath(OR_Grip.getProperty("EditResource_Save_Bttn"))).size()>0){
				fnsApps_Report_Logs("PASSED == Successfully redirected to 'ViewApplicant:OnBoarding' screen from 'Onboarding Create Contractor' screen, after clicking on 'No Applications Access' Button.");
				break;
			}else{
				Thread.sleep(1000);
			}
			if(i==Long.parseLong(CONFIG.getProperty("ElementWaitTime"))){
				isTestCasePass = false;
				fnsTake_Screen_Shot("RedirectedFail");
				fnsApps_Report_Logs("FAILED == Application is not redirected to 'ViewApplicant:OnBoarding' screen from 'Onboarding Create Contractor' screen, after clicking on 'No Applications Access' Button. TimeOut after <"+Long.parseLong(CONFIG.getProperty("ElementWaitTime"))+">Seconds, Please refer ScreenShot >> RedirectedFail"+fnsScreenShot_Date_format());
				throw new Exception("FAILED == Application is not redirected to 'ViewApplicant:OnBoarding' screen from 'Onboarding Create Contractor' screen, after clicking on 'No Applications Access' Button. TimeOut after <"+Long.parseLong(CONFIG.getProperty("ElementWaitTime"))+">Seconds, Please refer ScreenShot >> RedirectedFail"+fnsScreenShot_Date_format());
			}
		}
	}catch(Throwable t){
		isTestCasePass = false;
		throw new Exception(Throwables.getStackTraceAsString(t));	
	}
}
















//#######################################################  Configuration Method  ################################################################
@AfterTest
public void reportTestResult() throws Throwable {
	TestSuiteBase_MonitorPlan.fns_ReportTestResult_atClassLevel(Grip_Suitexls,  (this.getClass().getSimpleName()) , isTestCasePass);
}


@AfterTest
public void QuitBrowser(){
	TestSuiteBase_MonitorPlan.MoveMouseCursorToTaskBar();
	driver.quit();
}
}