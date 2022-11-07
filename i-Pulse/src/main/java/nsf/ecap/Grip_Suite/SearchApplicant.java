package nsf.ecap.Grip_Suite;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;

public class SearchApplicant extends TestSuiteBase_Grip {



@Parameters({ "className" })
@BeforeTest
public void checkTestSkip(String className) throws Exception {
	
	if (className.isEmpty()) {
		className = this.getClass().getSimpleName();		}
	IsBrowserPresentAlready =false;
	fnsCheckClassLevelTestSkip(className);
}

//################################################ Class Variables Starts #####################################################################
public Integer Before_DataAdded = null; 
public Integer NewlyAddedRowNo = null;
public Integer MatchingText_RowXpath_Count = null;
public String Row_Xpath = null;
public String Comments_Free_Text = "AutomationTest >> Grip_SearchApplicant[BS-01], Date/Time=" + fnIssueCreationText_Date_format();
public String TextFetched = null;
public String TextFetched_After = null;

public long AllFileSize_AfterFileSave ;






@Test( priority = 0)
public void Launch_Browser_and_Successfully_Login_into_the_Application() throws Throwable{
	fnsApps_Report_Logs("################################## [BS - 01]  Search Applicant");
	if (!IsBrowserPresentAlready) {
		IsBrowserPresentAlready = fnsLaunchBrowserAndLogin();
		fnsApps_Report_Logs("Browser is launched and Successfully login into Application");	
	}
	fnsApps_Report_Logs("=========================================================================================================================================");
}





@Test( priority = 1, dependsOnMethods={"Launch_Browser_and_Successfully_Login_into_the_Application"}, description="[Steps<Login into application >Menu >Create Contractor Applicant -> Add First name,last name,Selection Division and Department. Click Create.>,   Expected<Contractor created successfully.>]")
public void Create_NewContractorApplicant_AndThenVerify_ItShouldRedirectTo_ViewApplicant_OnBoardingScreen() throws Throwable{
	fnsApps_Report_Logs("################### Test Case ::::::1 Create_NewContractorApplicant_AndThenVerify_ItShouldRedirectTo_ViewApplicant_OnBoardingScreen ");
	try{	
		fncMenu_Ajax_Link_Click_By_PassingAjaxPath("CreateContractorApplicant_Ajax_Link");
				
		fnsGet_Element_Enabled("OnboardingCreateContractor_Profile_Type_DD_Click");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(3);
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(3);
		fnsDD_value_Select_By_MatchingText_DownKeyPress("OnboardingCreateContractor_Profile_Type_DD_Click", "OnboardingCreateContractor_Profile_Type_DD_Value", "li", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Type_DD"));
		
		//Added on 25.4.2015 as field made mandatory
		fnsGet_Element_Enabled("OnboardingCreateContractor_Profile_CellPhone");
		fnsWait_and_Type("OnboardingCreateContractor_Profile_CellPhone", fnsEditClient_Date_format());
		
		
		fnsGet_Element_Enabled("OnboardingCreateContractor_Profile_FirstName");
		fnsWait_and_Type("OnboardingCreateContractor_Profile_FirstName", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "FirstName"));
	
	
		fnsGet_Element_Enabled("OnboardingCreateContractor_Profile_LastName");
		fnsWait_and_Type("OnboardingCreateContractor_Profile_LastName", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "LastName"));
		
		fnsGet_Element_Enabled("OnboardingCreateContractor_Profile_Email");
		fnsWait_and_Type("OnboardingCreateContractor_Profile_Email", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Email"));
	
		fnsGet_Element_Enabled("OnboardingCreateContractor_Profile_WorkPhone");
		fnsWait_and_Type("OnboardingCreateContractor_Profile_WorkPhone", fnsEditClient_Date_format());
		
		/*//Added on 25.4.2015 as field made mandatory
		fnsGet_Element_Enabled("OnboardingCreateContractor_Profile_CellPhone");
		fnsWait_and_Type("OnboardingCreateContractor_Profile_CellPhone", fnsEditClient_Date_format());*/
		Thread.sleep(3000); 
		fnsGet_Element_Enabled("OnboardingCreateContractor_Division_Division_DD_Click");
		Thread.sleep(3000); // Wait Added as DD is not getting load
		fnsDD_value_Select_By_MatchingText_DownKeyPress("OnboardingCreateContractor_Division_Division_DD_Click", "OnboardingCreateContractor_Division_Division_DD_Value", "li", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Division_DD"));
		
		Thread.sleep(1000);
		fnsGet_Element_Enabled("OnboardingCreateContractor_Division_Department_DD_Click");
		fnsWait_and_Click("OnboardingCreateContractor_Profile_FirstName");
		Thread.sleep(4000);
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
		
		fnsGet_Element_Enabled("OnboardingCreateContractor_Division_OfficeLocation_DD_Click");
		fnsDD_value_Select_By_MatchingText_DownKeyPress("OnboardingCreateContractor_Division_OfficeLocation_DD_Click", "OnboardingCreateContractor_Division_OfficeLocation_DD_Value", "li", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "OfficeLocation_DD"));
		
		//Added on 25.4.2015 as field made mandatory
		fnsGet_Element_Enabled("OnboardingCreateContractor_Division_PaymentOffice_DD_Click");
		fnsDD_value_Select_By_MatchingText_DownKeyPress("OnboardingCreateContractor_Division_PaymentOffice_DD_Click", "OnboardingCreateContractor_Division_PaymentOffice_DD_Value", "li", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "PaymentOffice_DD"));
			
		fnsGet_Element_Enabled("OnboardingCreateContractor_PrimaryAddress_Address");
		fnsWait_and_Type("OnboardingCreateContractor_PrimaryAddress_Address", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "PrimaryAddress_Address"));
	
	
		fnsGet_Element_Enabled("OnboardingCreateContractor_PrimaryAddress_City");
		fnsWait_and_Type("OnboardingCreateContractor_PrimaryAddress_City", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "PrimaryAddress_City"));
		
	//	TestSuiteBase_MonitorPlan.WithOut_OR_fnDD_SelectValue_By_MatchingText(OR_Grip.getProperty("OnboardingCreateContractor_PrimaryAddress_CountryDD_Click"), OR_Grip.getProperty("OnboardingCreateContractor_PrimaryAddress_CountryDD_Value"), "li", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Country_DD"));
		TestSuiteBase_MonitorPlan.WithOut_OR_fnDD_SelectValue_Through_Filter(OR_Grip.getProperty("OnboardingCreateContractor_PrimaryAddress_CountryDD_Click"), OR_Grip.getProperty("OnboardingCreateContractor_PrimaryAddress_CountryDD_Filter"), fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Country_DD"));
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		Thread.sleep(2000);
		
		fnsGet_Element_Enabled("OnboardingCreateContractor_PrimaryAddress_StateDD_Click");
		fnsDD_value_Select_By_MatchingText_DownKeyPress("OnboardingCreateContractor_PrimaryAddress_StateDD_Click", "OnboardingCreateContractor_PrimaryAddress_StateDD_Value", "li", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "State_DD"));
		
	
		fnsGet_Element_Enabled("OnboardingCreateContractor_BusinessAddressSupplier_Address");
		fnsWait_and_Type("OnboardingCreateContractor_BusinessAddressSupplier_Address", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "BusinessAddressSupplier_Address"));
	
		fnsGet_Element_Enabled("OnboardingCreateContractor_BusinessAddressSupplier_City");
		fnsWait_and_Type("OnboardingCreateContractor_BusinessAddressSupplier_City", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "BusinessAddressSupplier_City"));
		
		fnsGet_Element_Enabled("OnboardingCreateContractor_BusinessAddressSupplier_BusinessName");
		fnsWait_and_Type("OnboardingCreateContractor_BusinessAddressSupplier_BusinessName", "AutomationBName");
	
		
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
		fnsDD_value_Select_By_MatchingText_WithOut_DownKeyPress("OnboardingCreateContractor_Division_RemoteResource_DD_Click", "OnboardingCreateContractor_Division_RemoteResource_DD_Value", "li", "Yes");
		
		
		
		
		fnsGet_Element_Enabled("OnboardingCreateContractor_Create_Bttn");
		fnsWait_and_Click("OnboardingCreateContractor_Create_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(5);
	
		fncVerify_DataUpdated_Successfully("IPulse_ValidationMessage_Div", "Onboarding Create Contractor");
		
		for(int i=0; i<=Long.parseLong(CONFIG.getProperty("ElementWaitTime"));i++){
			if(driver.findElements(By.xpath(OR_Grip.getProperty("EditResource_Save_Bttn"))).size()>0){
				fnsApps_Report_Logs("PASSED == Successfully redirected to 'ViewApplicant:OnBoarding' screen from 'Onboarding Create Contractor' screen, after clicking on Create Button.");
				break;
			}else{
				Thread.sleep(1000);
			}
			if(i==Long.parseLong(CONFIG.getProperty("ElementWaitTime"))){
				isTestCasePass = false;
				fnsTake_Screen_Shot("RedirectedFail");
				fnsApps_Report_Logs("FAILED == Application is not redirected to 'ViewApplicant:OnBoarding' screen from 'Onboarding Create Contractor' screen, after clicking on Create Button. TimeOut after <"+Long.parseLong(CONFIG.getProperty("ElementWaitTime"))+">Seconds, Please refer ScreenShot >> RedirectedFail"+fnsScreenShot_Date_format());
				throw new Exception("FAILED == Application is not redirected to 'ViewApplicant:OnBoarding' screen from 'Onboarding Create Contractor' screen, after clicking on Create Button. TimeOut after <"+Long.parseLong(CONFIG.getProperty("ElementWaitTime"))+">Seconds, Please refer ScreenShot >> RedirectedFail"+fnsScreenShot_Date_format());
			}
		}
	}catch(Throwable t){
		isTestCasePass = false;
		throw new Exception(Throwables.getStackTraceAsString(t));	
	}
}




@Test(dependsOnMethods={"Create_NewContractorApplicant_AndThenVerify_ItShouldRedirectTo_ViewApplicant_OnBoardingScreen"}, priority = 2, description="[Steps<On -Boarding tab > Click Add Division (NSF-ISR & Sustainability). Click on 'Hold Applicant' to hold it and enter reasons.Click yes button>,   Expected<The message displayed should be: Applicant's on-boarding status modified from APPLIED to ONHOLD for NSF-ISR & Sustainability division.>]"+
"[Steps<Click on Release Hold and enter the reason for unhold and click Yes button.>,   Expected<The message displayed should be: Applicant's on-boarding status modified from ONHOLD to APPLIED for NSF-ISR & Sustainability division.>]")
public void EditApplicant_OnBoardingTab_AddAnotherDivision_PutOnHold_ReleaseOnHold_and_VerifyAllWorkingFine() throws Throwable{
	fnsApps_Report_Logs("=========================================================================================================================================");
	fnsApps_Report_Logs("############### Test Case :::::2 EditApplicant_OnBoardingTab_AddAnotherDivision_PutOnHold_ReleaseOnHold_and_VerifyAllWorkingFine");
	try{
		fnsGet_Element_Enabled("EditApplicant_OnBoardingTab_DivisionSection_AddDivision_Bttn");
		fnsWait_and_Click("EditApplicant_OnBoardingTab_DivisionSection_AddDivision_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		
		fnsGet_Element_Enabled("EditApplicant_OnBoardingTab_DivisionSection_DivisionDD_Click");
		fnsDD_value_Select_By_MatchingText_DownKeyPress("EditApplicant_OnBoardingTab_DivisionSection_DivisionDD_Click", "EditApplicant_OnBoardingTab_DivisionSection_DivisionDD_Value", "li", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "DivisionDD"));
		
		fnsDD_value_Select_From_Filter_CheckBox_And_Radio_Select_DD("EditApplicant_OnBoardingTab_DivisionSection_RoleDD_Click", "EditApplicant_OnBoardingTab_DivisionSection_RoleDD_Radio_Bttn",  fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Role_DD"));
		
		fnsGet_Element_Enabled("EditApplicant_OnBoardingTab_DivisionSection_Save_Bttn");
		fnsWait_and_Click("EditApplicant_OnBoardingTab_DivisionSection_Save_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		fncVerify_DataUpdated_Successfully("IPulse_ValidationMessage_Div", "On-Boarding TAB");
		
		fnsGet_Element_Enabled("EditApplicant_OnBoardingTab_DivisionSection_Edit_Bttn");
		fnsWait_and_Click("EditApplicant_OnBoardingTab_DivisionSection_Edit_Bttn");
	
		fnsGet_Element_Enabled("EditApplicant_OnBoardingTab_DivisionSection_Note");
		fnsWait_and_Type("EditApplicant_OnBoardingTab_DivisionSection_Note", Comments_Free_Text);
			
		fnsGet_Element_Enabled("EditApplicant_OnBoardingTab_DivisionSection_Save_Bttn");
		fnsWait_and_Click("EditApplicant_OnBoardingTab_DivisionSection_Save_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		fncVerify_DataUpdated_Successfully("IPulse_ValidationMessage_Div", "On-Boarding TAB");
	
		
		fnsGet_Element_Enabled("EditApplicant_OnBoardingTab_DivisionSection_HoldApplicant_Bttn");
		fnsWait_and_Click("EditApplicant_OnBoardingTab_DivisionSection_HoldApplicant_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		fnsGet_Element_Enabled("EditApplicant_OnBoardingTab_Hold_UpdateRecordConfirmation_TextBox");
		fnsWait_and_Type("EditApplicant_OnBoardingTab_Hold_UpdateRecordConfirmation_TextBox", "Putting division<"+fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "DivisionDD")+"> OnHold.");
		fnsGet_Element_Enabled("EditApplicant_OnBoardingTab_Hold_UpdateRecordConfirmation_Yes_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		fnsWait_and_Click("EditApplicant_OnBoardingTab_Hold_UpdateRecordConfirmation_Yes_Bttn");
		for(int i=0; i<=Long.parseLong(CONFIG.getProperty("ElementWaitTime")); i++ ){
			try{
				if(fnsGet_OR_Grip_ObjectX("EditApplicant_OnBoardingTab_Hold_UpdateRecordConfirmation_Yes_Bttn").isDisplayed()){
					Thread.sleep(1000);
				}else{
					break;
				}
			}catch(Throwable t){
				if(i==Long.parseLong(CONFIG.getProperty("ElementWaitTime"))){
					
				}else{
					Thread.sleep(1000);
				}
			}
		}
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		Thread.sleep(3000);  // Time wait is putting to run scripts on Chrome
		TextFetched = fnsGet_Field_TEXT("IPulse_ValidationMessage_Div");
		try{
			TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element_BY_OFFSET(OR_Grip.getProperty("IPulse_ValidationMessage_Div"), 100, 10);
			assert (TextFetched.contains(("Applicant's on-boarding status modified from APPLIED to ONHOLD for "+fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "DivisionDD")+" division.")))
			:"FAILED == Putting division<"+fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "DivisionDD")+"> on Hold is getting fail, , Getting Error<"+TextFetched+">. Please refer screenshot >> PutHoldFail"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED == Successfully put division<"+fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "DivisionDD")+"> OnHold.");
		}catch(Throwable t){
			isTestCasePass = false;
			fnsTake_Screen_Shot("PutHoldFail");
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));		}
		
	
		
		fnsGet_Element_Enabled("EditApplicant_OnBoardingTab_DivisionSection_ReleaseHold_Bttn");
		Thread.sleep(3000);
		fnsWait_and_Click("EditApplicant_OnBoardingTab_DivisionSection_ReleaseHold_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		fnsGet_Element_Enabled("EditApplicant_OnBoardingTab_ReleaseHold_UpdateRecordConfirmation_TextBox");
		fnsWait_and_Type("EditApplicant_OnBoardingTab_ReleaseHold_UpdateRecordConfirmation_TextBox", "Release division<"+fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "DivisionDD")+"> from OnHold.");
		fnsGet_Element_Enabled("EditApplicant_OnBoardingTab_ReleaseHold_UpdateRecordConfirmation_Yes_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		fnsWait_and_Click("EditApplicant_OnBoardingTab_ReleaseHold_UpdateRecordConfirmation_Yes_Bttn");
		for(int i=0; i<=Long.parseLong(CONFIG.getProperty("ElementWaitTime")); i++ ){
			try{
				if(fnsGet_OR_Grip_ObjectX("EditApplicant_OnBoardingTab_ReleaseHold_UpdateRecordConfirmation_Yes_Bttn").isDisplayed()){
					Thread.sleep(1000);
				}else{
					break;
				}
			}catch(Throwable t){
				if(i==Long.parseLong(CONFIG.getProperty("ElementWaitTime"))){
					
				}else{
					Thread.sleep(1000);
				}
			}
			
			
		}
		
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		Thread.sleep(3000);  // Time wait is putting to run scripts on Chrome
		TextFetched = fnsGet_Field_TEXT("IPulse_ValidationMessage_Div");
		try{
			TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element_BY_OFFSET(OR_Grip.getProperty("IPulse_ValidationMessage_Div"), 100, 10);
			assert (TextFetched.contains(("Applicant's on-boarding status modified from ONHOLD to APPLIED for "+fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "DivisionDD")+" division.")))
			:"FAILED == Releasing division<"+fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "DivisionDD")+"> from OnHold is getting fail, , Getting Error<"+TextFetched+">. Please refer screenshot >> ReleaseHoldFail"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED == Successfully Release division<"+fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "DivisionDD")+"> from OnHold.");
		}catch(Throwable t){
			isTestCasePass = false;
			fnsTake_Screen_Shot("ReleaseHoldFail");
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));		}
	}catch(Throwable t){
		isTestCasePass = false;
		throw new Exception(Throwables.getStackTraceAsString(t));	
	}
}





@Test(dependsOnMethods={"EditApplicant_OnBoardingTab_AddAnotherDivision_PutOnHold_ReleaseOnHold_and_VerifyAllWorkingFine"}, priority = 3, description="[Steps<Department tab >Click on Add department to add 2 departments.>,   Expected<Data Updated successfully message should come.>]")
public void EditApplicant_OnBoardingTab_DepartmentSection_AddTwoDepartments_and_VerifyAddedSuccessfully() throws Throwable{
	fnsApps_Report_Logs("=========================================================================================================================================");
	fnsApps_Report_Logs("############### Test Case :::::3 EditApplicant_OnBoardingTab_DepartmentSection_AddTwoDepartments_and_VerifyAddedSuccessfully");
	try{
		Before_DataAdded = TestSuiteBase_MonitorPlan.WithOut_OR_Retrun_TotalRowCount_OfTable(OR_Grip.getProperty("EditApplicant_OnBoardingTab_AddDepartmentSection_TableHeader"));
		
		fnsGet_Element_Enabled("EditApplicant_OnBoardingTab_AddDepartmentSection_AddDepartment_Bttn");
		fnsWait_and_Click("EditApplicant_OnBoardingTab_AddDepartmentSection_AddDepartment_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(3);
	
		fnsGet_Element_Enabled("LookUp_SelectAndReturn_Bttn");
	
		fnsGet_Element_Enabled("LookUp_First_CheckBox");
		fnsWait_and_Click("LookUp_First_CheckBox");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
	
		fnsGet_Element_Enabled("LookUp_Second_CheckBox");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		fnsWait_and_Click("LookUp_Second_CheckBox");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
	
		fnsGet_Element_Enabled("LookUp_SelectAndReturn_Bttn");
		fnsWait_and_Click("LookUp_SelectAndReturn_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
	
		fncVerify_NewRow_Added_into_Table("On-Boarding TAB : Department Section", OR_Grip.getProperty("EditApplicant_OnBoardingTab_AddDepartmentSection_TableHeader"), Before_DataAdded);
		
		fnsGet_Element_Enabled("EditApplicant_OnBoardingTab_AddDepartmentSection_Save_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		fnsWait_and_Click("EditApplicant_OnBoardingTab_AddDepartmentSection_Save_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		fncVerify_DataUpdated_Successfully("IPulse_ValidationMessage_Div", "On-Boarding TAB : Department Section");
	}catch(Throwable t){
		isTestCasePass = false;
		throw new Exception(Throwables.getStackTraceAsString(t));	
	}
}




@Test(dependsOnMethods={"EditApplicant_OnBoardingTab_DepartmentSection_AddTwoDepartments_and_VerifyAddedSuccessfully"}, priority = 4, description="[Steps<Click on Resume tab>Add Resumes.Browse and click save and close.>,   Expected<Data Updated Successfully should come.>]")
public void EditApplicant_OnBoardingTab_ResumeSection_AddNewRecord_and_VerifyAddedSuccessfully() throws Throwable{
	fnsApps_Report_Logs("=========================================================================================================================================");
	fnsApps_Report_Logs("############### Test Case :::::4 EditApplicant_OnBoardingTab_ResumeSection_AddNewRecord_and_VerifyAddedSuccessfully");
	try{	
		fncClicking_on_TAB("On-BoardingTAB : Resume", "EditApplicant_OnBoardingTab_Resume_TAB", "EditApplicant_OnBoardingTab_ResumeSection_Add_Bttn");
		
		fnsGet_Element_Enabled("EditApplicant_OnBoardingTab_ResumeSection_Add_Bttn");
		fnsWait_and_Click("EditApplicant_OnBoardingTab_ResumeSection_Add_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		fncAssert_Contains(OR_Grip.getProperty("EditApplicant_OnBoardingTab_ResumeSection_AddPopup_Title"), "Add Resource Resume", "'Add Resource Resume' Popup is not getting opened");
		
		fncUploadFile("EditApplicant_OnBoardingTab_ResumeSection_AddPopup_BrowseFile", "Add Resource Resume");
		
		fnsGet_Element_Enabled("EditApplicant_OnBoardingTab_ResumeSection_AddPopup_Description");
		fnsWait_and_Type("EditApplicant_OnBoardingTab_ResumeSection_AddPopup_Description", Comments_Free_Text);
	
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(12);
		fnsGet_Element_Enabled("EditApplicant_OnBoardingTab_ResumeSection_AddPopup_SaveClose_Bttn");
		fnsWait_and_Click("EditApplicant_OnBoardingTab_ResumeSection_AddPopup_SaveClose_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		fncVerify_DataUpdated_Successfully("IPulse_ValidationMessage_Div", "On-Boarding TAB : Resume");
	
		TextFetched = fnsGet_Field_TEXT("EditApplicant_OnBoardingTab_ResumeSection_TableHeader_FirstRow");
		try{
			TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element_BY_OFFSET(OR_Grip.getProperty("EditApplicant_OnBoardingTab_ResumeSection_TableHeader_FirstRow"), 100, 10);
			assert (TextFetched.contains(Comments_Free_Text)):"FAILED == Resume data are not getting added, Please refer screenshot >> ResumeAddFail"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED == Successfully added data into Resume Table.");
		}catch(Throwable t){
			isTestCasePass = false;
			fnsTake_Screen_Shot("ResumeAddFail");
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));		}
		
	}catch(Throwable t){
		isTestCasePass = false;
		throw new Exception(Throwables.getStackTraceAsString(t));	
	}
}




@Test(dependsOnMethods={"EditApplicant_OnBoardingTab_ResumeSection_AddNewRecord_and_VerifyAddedSuccessfully"}, priority = 5, description="[Steps<Review Tab >Review Result:>Select the option as Meets Requirements.Enter the text in textarea.Click Save>,   Expected<It should display 'Data Updated successfully' message>]")
public void EditApplicant_OnBoardingTab_ReviewSection_AddReviewResults_and_VerifyReviewDataSaveSuccessfully() throws Throwable{
	fnsApps_Report_Logs("=========================================================================================================================================");
	fnsApps_Report_Logs("############### Test Case :::::5 EditApplicant_OnBoardingTab_ReviewSection_AddReviewResults_and_VerifyReviewDataSaveSuccessfully");
	try{
		fncClicking_on_TAB("On-BoardingTAB : Review", "EditApplicant_OnBoardingTab_Review_TAB", "EditApplicant_OnBoardingTab_ReviewSection_Save_Bttn");
			
		fnsGet_Element_Enabled("EditApplicant_OnBoardingTab_ReviewSection_MeetsRequirements_RadioBttn");
		fnsWait_and_Click("EditApplicant_OnBoardingTab_ReviewSection_MeetsRequirements_RadioBttn");
		
		fnsGet_Element_Enabled("EditApplicant_OnBoardingTab_ReviewSection_TextBox");
		fnsWait_and_Clear("EditApplicant_OnBoardingTab_ReviewSection_TextBox");
		fnsWait_and_Type("EditApplicant_OnBoardingTab_ReviewSection_TextBox", Comments_Free_Text);
			
		fnsGet_Element_Enabled("EditApplicant_OnBoardingTab_ReviewSection_Save_Bttn");
		fnsWait_and_Click("EditApplicant_OnBoardingTab_ReviewSection_Save_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		fncVerify_DataUpdated_Successfully("IPulse_ValidationMessage_Div", "On-BoardingTAB : Review");
	}catch(Throwable t){
		isTestCasePass = false;
		throw new Exception(Throwables.getStackTraceAsString(t));	
	}
}



@Test(dependsOnMethods={"EditApplicant_OnBoardingTab_ReviewSection_AddReviewResults_and_VerifyReviewDataSaveSuccessfully"}, priority = 6, description="[Steps<Interview > Mention the radio button for 'Meets requirements'.Select References valid.Enter the text.Click save. Add interview > Enter the date,time, interview type as FACE to FACE and comments. Click on Save icon.> Step2<Add another record for Add interview > Enter the date,time, interview type as TELEPHONIC and comments. Click on Save icon.>,   Expected<It should display 'Data Updated successfully' message>]")
public void EditApplicant_OnBoardingTab_InterviewSection_AddInterviewResultsDetails_and_VerifySavedSuccessfully() throws Throwable{
	fnsApps_Report_Logs("=========================================================================================================================================");
	fnsApps_Report_Logs("############### Test Case :::::6 EditApplicant_OnBoardingTab_InterviewSection_AddInterviewResultsDetails_and_VerifySavedSuccessfully");
	try{
		fncClicking_on_TAB("On-BoardingTAB : Interview", "EditApplicant_OnBoardingTab_Interview_TAB", "EditApplicant_OnBoardingTab_InterviewSection_Save_Bttn");
			
		fnsGet_Element_Enabled("EditApplicant_OnBoardingTab_InterviewSection_MeetsRequirements_RadioBttn");
		fnsWait_and_Click("EditApplicant_OnBoardingTab_InterviewSection_MeetsRequirements_RadioBttn");
			
		fnsGet_Element_Enabled("EditApplicant_OnBoardingTab_InterviewSection_ReferencesVerified_CheckBox");
		fnsWait_and_Click("EditApplicant_OnBoardingTab_InterviewSection_ReferencesVerified_CheckBox");
		
		fnsGet_Element_Enabled("EditApplicant_OnBoardingTab_InterviewSection_TextBox");
		fnsWait_and_Clear("EditApplicant_OnBoardingTab_InterviewSection_TextBox");
		fnsWait_and_Type("EditApplicant_OnBoardingTab_InterviewSection_TextBox", Comments_Free_Text);
		
		fnsGet_Element_Enabled("EditApplicant_OnBoardingTab_InterviewSection_Save_Bttn");
		fnsWait_and_Click("EditApplicant_OnBoardingTab_InterviewSection_Save_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		fncVerify_DataUpdated_Successfully("IPulse_ValidationMessage_Div", "On-BoardingTAB : Interview Results");
		
		
		fnsGet_Element_Enabled("EditApplicant_OnBoardingTab_InterviewSec_AddInterview_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		fnsWait_and_Click("EditApplicant_OnBoardingTab_InterviewSec_AddInterview_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		
		
		//fncCalendarDatePicker_NullMeansCurrent(OR_Grip.getProperty("EditApplicant_OnBoardingTab_InterviewSec_AddIntrvw_Date_Calender"), null, null, null);
		fncCalendarDatePicker_BySelectValues_From_DropDown(OR_Grip.getProperty("EditApplicant_OnBoardingTab_InterviewSec_AddIntrvw_Date_Calender"), null, null);
		
		/*/Thread.sleep(1000);	
		fnsGet_Element_Enabled("EditApplicant_OnBoardingTab_InterviewSec_AddIntrvw_TimeFrom");
		//fnsWait_and_Type("EditApplicant_OnBoardingTab_InterviewSec_AddIntrvw_TimeFrom", fnsReturn_Requried_Time_HHmm(-110));
		fnsWait_and_Type("EditApplicant_OnBoardingTab_InterviewSec_AddIntrvw_TimeFrom", fnsReturn_Requried_Time_HHmm(-20));
		
		fnsGet_Element_Enabled("EditApplicant_OnBoardingTab_InterviewSec_AddIntrvw_TimeTo");
		//fnsWait_and_Type("EditApplicant_OnBoardingTab_InterviewSec_AddIntrvw_TimeTo", fnsReturn_Requried_Time_HHmm(-70));
		fnsWait_and_Type("EditApplicant_OnBoardingTab_InterviewSec_AddIntrvw_TimeTo", fnsReturn_Requried_Time_HHmm(0));/*/
		
		
		
		Thread.sleep(1000);	
		fnsGet_Element_Enabled("EditApplicant_OnBoardingTab_InterviewSec_AddIntrvw_TimeFrom");
		fnsWait_and_Click("EditApplicant_OnBoardingTab_InterviewSec_AddIntrvw_TimeFrom");
		Thread.sleep(500);
		TestSuiteBase_MonitorPlan.WithOut_OR_fnClick("(//a[text()='02'])[1]");
			
		Thread.sleep(1000);	
		fnsGet_Element_Enabled("EditApplicant_OnBoardingTab_InterviewSec_AddIntrvw_TimeTo");
		fnsWait_and_Click("EditApplicant_OnBoardingTab_InterviewSec_AddIntrvw_TimeTo");
		Thread.sleep(500);
		TestSuiteBase_MonitorPlan.WithOut_OR_fnClick("(//a[text()='03'])[1]");
		
		
		
		
		
		
		
		fnsGet_Element_Enabled("EditApplicant_OnBoardingTab_InterviewSec_AddIntrvw_IntrvwTypeDD_Click");
		fnsDD_value_Select_By_MatchingText_DownKeyPress("EditApplicant_OnBoardingTab_InterviewSec_AddIntrvw_IntrvwTypeDD_Click", "EditApplicant_OnBoardingTab_InterviewSec_AddIntrvw_IntrvwTypeDD_Value", "li", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "InterviewType_DD_FirstAdd"));
		
		
		fnsGet_Element_Enabled("EditApplicant_OnBoardingTab_InterviewSec_AddIntrvw_Interview_Text");
		fnsWait_and_Type("EditApplicant_OnBoardingTab_InterviewSec_AddIntrvw_Interview_Text", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Interviewer_Text"));
		
		
		fnsGet_Element_Enabled("EditApplicant_OnBoardingTab_InterviewSec_AddIntrvw_CommentsText");
		fnsWait_and_Type("EditApplicant_OnBoardingTab_InterviewSec_AddIntrvw_CommentsText", Comments_Free_Text);
	
		fncEdit_and_Save_ButtonClick_WithOut_OR("EditApplicant_OnBoardingTab_InterviewSec_AddIntrvw_TableHeader_Data", 1, "save");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
	
		fncVerify_DataUpdated_Successfully("IPulse_ValidationMessage_Div", "On-BoardingTAB : Add Interview");
		
		//Second Row Adding	
		fnsGet_Element_Enabled("EditApplicant_OnBoardingTab_InterviewSec_AddInterview_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		fnsWait_and_Click("EditApplicant_OnBoardingTab_InterviewSec_AddInterview_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		//fncCalendarDatePicker_NullMeansCurrent(OR_Grip.getProperty("EditApplicant_OnBoardingTab_InterviewSec_AddIntrvw_Date_Calender"), null, null, null);
		fncCalendarDatePicker_BySelectValues_From_DropDown(OR_Grip.getProperty("EditApplicant_OnBoardingTab_InterviewSec_AddIntrvw_Date_Calender"), null, null);
		
		//Thread.sleep(1000);
	//	fnsGet_Element_Enabled("EditApplicant_OnBoardingTab_InterviewSec_AddIntrvw_TimeFrom");
	//	fnsWait_and_Click("EditApplicant_OnBoardingTab_InterviewSec_AddIntrvw_TimeFrom");
	//	fnsWait_and_Type("EditApplicant_OnBoardingTab_InterviewSec_AddIntrvw_TimeFrom", fnsReturn_Requried_Time_HHmm(-150));
	//	fnsWait_and_Type("EditApplicant_OnBoardingTab_InterviewSec_AddIntrvw_TimeFrom", fnsReturn_Requried_Time_HHmm(-40));
		
		
	//	fnsGet_Element_Enabled("EditApplicant_OnBoardingTab_InterviewSec_AddIntrvw_TimeTo");
	//	fnsWait_and_Type("EditApplicant_OnBoardingTab_InterviewSec_AddIntrvw_TimeTo", fnsReturn_Requried_Time_HHmm(-130));
	//	fnsWait_and_Type("EditApplicant_OnBoardingTab_InterviewSec_AddIntrvw_TimeTo", fnsReturn_Requried_Time_HHmm(-30));
		
		
		
		
		
	
		Thread.sleep(1000);	
		fnsGet_Element_Enabled("EditApplicant_OnBoardingTab_InterviewSec_AddIntrvw_TimeFrom");
		fnsWait_and_Click("EditApplicant_OnBoardingTab_InterviewSec_AddIntrvw_TimeFrom");
		Thread.sleep(500);
		TestSuiteBase_MonitorPlan.WithOut_OR_fnClick("(//a[text()='15'])[2]");
			
		fnsWait_and_Click("EditApplicant_OnBoardingTab_InterviewSec_AddIntrvw_CommentsText");
		Thread.sleep(1000);	
		fnsGet_Element_Enabled("EditApplicant_OnBoardingTab_InterviewSec_AddIntrvw_TimeTo");
		fnsWait_and_Click("EditApplicant_OnBoardingTab_InterviewSec_AddIntrvw_TimeTo");
		Thread.sleep(500);
		TestSuiteBase_MonitorPlan.WithOut_OR_fnClick("(//a[text()='55'])[1]");
		
		
		
		
		
		
		fnsGet_Element_Enabled("EditApplicant_OnBoardingTab_InterviewSec_AddIntrvw_IntrvwTypeDD_Click");
		fnsDD_value_Select_By_MatchingText_DownKeyPress("EditApplicant_OnBoardingTab_InterviewSec_AddIntrvw_IntrvwTypeDD_Click", "EditApplicant_OnBoardingTab_InterviewSec_AddIntrvw_IntrvwTypeDD_Value", "li", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "InterviewType_DD_SecondAdd"));
			
		fnsGet_Element_Enabled("EditApplicant_OnBoardingTab_InterviewSec_AddIntrvw_Interview_Text");
		fnsWait_and_Type("EditApplicant_OnBoardingTab_InterviewSec_AddIntrvw_Interview_Text", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Interviewer_Text"));
			
		fnsGet_Element_Enabled("EditApplicant_OnBoardingTab_InterviewSec_AddIntrvw_CommentsText");
		fnsWait_and_Type("EditApplicant_OnBoardingTab_InterviewSec_AddIntrvw_CommentsText", Comments_Free_Text);
		
		fncEdit_and_Save_ButtonClick_WithOut_OR("EditApplicant_OnBoardingTab_InterviewSec_AddIntrvw_TableHeader_Data", 1, "save");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(10);
		
		fncVerify_DataUpdated_Successfully("IPulse_ValidationMessage_Div", "On-BoardingTAB : Add Interview");
	}catch(Throwable t){
		isTestCasePass = false;
		throw new Exception(Throwables.getStackTraceAsString(t));	
	}
}




@Test(dependsOnMethods={"EditApplicant_OnBoardingTab_InterviewSection_AddInterviewResultsDetails_and_VerifySavedSuccessfully"}, priority = 7, description="[Steps<Go to the profile tab>Enter the Manager using the look up. Mention the text for the Special Instructions and Availability.Click Save.>,   Expected<Data Updated Successfully should come.>]")
public void EditApplicant_ProfileTab_Verify_AllSections_ComingIn_EditableMode_and_DataUpdatedSuccessfully() throws Throwable{
	fnsApps_Report_Logs("=========================================================================================================================================");
	fnsApps_Report_Logs("############### Test Case :::::7 EditApplicant_ProfileTab_Verify_AllSections_ComingIn_EditableMode_and_DataUpdatedSuccessfully");
	try{
		fncClicking_on_TAB("Profile", "EditApplicant_Profile_TAB", "EditApplicant_ProfileTab_AdditionalInfoSection_Availability");
			
		fnsGet_Element_Enabled("EditApplicant_ProfileTab_MainDiv");
		WebElement ProfileAllSection_TableElement = fnsGet_OR_Grip_ObjectX("EditApplicant_ProfileTab_MainDiv");
		List<WebElement> ProfileAllSection_AllElements = ProfileAllSection_TableElement.findElements(By.tagName("input"));
		Integer ProfileAllSection_InputCount = ProfileAllSection_AllElements.size();
		
		try{
			assert (ProfileAllSection_InputCount>=46):"FAILED == Profile's All Sections are not coming in EditMode, Please refer screenshot >> EditModeFail_"+ProfileAllSection_InputCount+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED == Successfully Verify that Profile's All Sections are coming in EditMode. "+ProfileAllSection_InputCount);
		}catch(Throwable t){
			isTestCasePass = false;
			fnsTake_Screen_Shot("EditModeFail_"+ProfileAllSection_InputCount);
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));		}
		
		
		fnsGet_Element_Enabled("EditApplicant_ProfileTab_ProfileInfoSection_Manager_LK_Bttn");
		fnsWait_and_Click("EditApplicant_ProfileTab_ProfileInfoSection_Manager_LK_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		fnsGet_Element_Enabled("EditApplicant_ProfileTab_ProfileInfoSection_Manager_LK_Text");
		fnsWait_and_Type("EditApplicant_ProfileTab_ProfileInfoSection_Manager_LK_Text", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Manager_LK_Text"));
		TestSuiteBase_MonitorPlan.fnsLookup_RadioBttn_Select();
		
		fnsGet_Element_Enabled("EditApplicant_ProfileTab_PrimaryAddressSection_PostalCode");
		fnsWait_and_Type("EditApplicant_ProfileTab_PrimaryAddressSection_PostalCode", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "PostalCode"));
			
	/*	fnsGet_Element_Enabled("EditApplicant_ProfileTab_AdditionalInfoSection_AddEmail");
		fnsWait_and_Type("EditApplicant_ProfileTab_AdditionalInfoSection_AddEmail", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "AddEmail"));
		*/
		fnsGet_Element_Enabled("EditApplicant_ProfileTab_AdditionalInfoSection_LocationPreference");
		fnsWait_and_Type("EditApplicant_ProfileTab_AdditionalInfoSection_LocationPreference", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "LocationPreference"));
		
		fnsGet_Element_Enabled("EditApplicant_ProfileTab_AdditionalInfoSection_SpecialInstructions");
		fnsWait_and_Type("EditApplicant_ProfileTab_AdditionalInfoSection_SpecialInstructions", Comments_Free_Text);
		
		fnsGet_Element_Enabled("EditApplicant_ProfileTab_AdditionalInfoSection_Availability");
		fnsWait_and_Type("EditApplicant_ProfileTab_AdditionalInfoSection_Availability", Comments_Free_Text);
		
		
		fnsWait_and_Click("EditApplicant_Save_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		fncVerify_DataUpdated_Successfully("IPulse_ValidationMessage_Div", "Profile TAB");
	}catch(Throwable t){
		isTestCasePass = false;
		throw new Exception(Throwables.getStackTraceAsString(t));	
	}
}	




@Test(dependsOnMethods={"EditApplicant_ProfileTab_Verify_AllSections_ComingIn_EditableMode_and_DataUpdatedSuccessfully"}, priority = 8, description="[Steps<Contract>Click on Add for WIP contracts>Select signed contract radio button.Enter mandatory information. Upload the contract and Click save.>,   Expected<Contract has been successfully saved message should come and check that contract gets added in the table.>]"+
		"[Steps<Go to Onboarding >Department>contracts. Verify that contract is being displayed for both the divisions.>,   Expected<The contract should be displayed for both the divisions.>]")
public void EditApplicant_ContractTab_AddNewContract_ThenGoTo_OnBoardingTab_ContractSection_and_Verify_ContractDisplay() throws Throwable{
	fnsApps_Report_Logs("=========================================================================================================================================");
	fnsApps_Report_Logs("############## Test Case :::::8 EditApplicant_ContractTab_AddNewContract_ThenGoTo_OnBoardingTab_ContractSection_and_Verify_ContractDisplay");
	try{
		fncClicking_on_TAB("Contract", "EditApplicant_Contract_TAB", "EditApplicant_ContractTab_WIPContracts_Add_Bttn");
		
		Before_DataAdded = TestSuiteBase_MonitorPlan.WithOut_OR_Retrun_TotalRowCount_OfTable(OR_Grip.getProperty("EditApplicant_ContractTab_WIPContracts_TableHeader"));
		String TableText = TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(OR_Grip.getProperty("EditApplicant_ContractTab_WIPContracts_TableHeader")).toLowerCase();
		if (TableText.contains("no contracts have been added")){
			Before_DataAdded = 0;
		}
		
		fnsGet_Element_Enabled("EditApplicant_ContractTab_WIPContracts_Add_Bttn");
		fnsWait_and_Click("EditApplicant_ContractTab_WIPContracts_Add_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		fncAssert_Contains(OR_Grip.getProperty("EditApplicant_ContractTab_WIPContracts_AddPopup_Title"), "Add New Contract", "'Add New Contract' Popup is not getting opened");
		
		fnsGet_Element_Enabled("EditApplicant_ContractTab_WIPContracts_AddPopup_SignedContract");
		fnsWait_and_Click("EditApplicant_ContractTab_WIPContracts_AddPopup_SignedContract");
	
		Thread.sleep(3000);
		fncUploadFile("EditApplicant_ContractTab_WIPContracts_AddPopup_BrowseFile", "'Add New Contract' PopUp");
		
		fnsGet_Element_Enabled("EditApplicant_ContractTab_WIPContracts_AddPopup_Comments");
		fnsWait_and_Type("EditApplicant_ContractTab_WIPContracts_AddPopup_Comments", Comments_Free_Text);
		
		//fncCalendarDatePicker_NullMeansCurrent(OR_Grip.getProperty("EditResource_ContractTab_WIPContracts_AddPopup_ContractDate_CalenderBttn"), null, null, null);
		fncCalendarDatePicker_BySelectValues_From_DropDown(OR_Grip.getProperty("EditResource_ContractTab_WIPContracts_AddPopup_ContractDate_CalenderBttn"), null, null);
		
		//IPM-10329
		fncCalendarDatePicker_BySelectValues_From_DropDown(OR_Grip.getProperty("EditResource_ContractTab_WIPContracts_AddPopup_ExpiryDate_CalenderBttn"), null, fnsReturn_Requried_Year(1));
		
		fnsGet_Element_Enabled("EditApplicant_ContractTab_WIPContracts_AddPopup_SaveBttn");
		fnsWait_and_Click("EditApplicant_ContractTab_WIPContracts_AddPopup_SaveBttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
			
		fncVerify_NewRow_Added_into_Table("WIP Contracts", OR_Grip.getProperty("EditApplicant_ContractTab_WIPContracts_TableHeader"), Before_DataAdded);
		TableText = TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(OR_Grip.getProperty("EditApplicant_ContractTab_WIPContracts_TableHeader")).toLowerCase();
		if (TableText.contains("no contracts have been added")){
			fnsTake_Screen_Shot("Contract_Added_Fail");
			throw new Exception("FAILED == New contract is not added into the table, Please refer screenshot >> NewDataAddedFail"+fnsScreenShot_Date_format());
		}
		fncVerify_DataUpdated_Successfully("IPulse_ValidationMessage_Div", "WIP Contracts");
		
		
	//On-BoardingTAB : Contract Section // commented on 26.2.2021 - IPM-14632
	/*	fncClicking_on_TAB("On-BoardingTAB", "EditResource_OnBoarding_TAB", "EditApplicant_OnBoardingTab_Contract_TAB");
		fncClicking_on_TAB("On-BoardingTAB : Contract", "EditApplicant_OnBoardingTab_Contract_TAB", "EditApplicant_OnBoardingTab_ContractSection_WIP_TableHeader");
		
		for(int LoopCount=1; LoopCount<3; LoopCount++){
		
			Row_Xpath = OR_Grip.getProperty("EditApplicant_OnBoardingTab_DivisionSection_TableHeader_Data")+"/tr["+LoopCount+"]/td[1]/table/tbody/tr[1]/td[1]/div/div[2]";
			TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(Row_Xpath);
			TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(Row_Xpath);
			TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
					
			TextFetched = fnsGet_Field_TEXT("EditApplicant_OnBoardingTab_ContractSection_WIP_TableHeader").toLowerCase().trim();
			
			TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element_BY_OFFSET(OR_Grip.getProperty("EditApplicant_OnBoardingTab_ContractSection_WIP_TableHeader"), 100, 25);
			
			if(TextFetched.contains((Comments_Free_Text.toLowerCase().trim()))){
				fnsApps_Report_Logs("PASSED == Successfully Verify that Newly Created 'Signed' Contract is display into 'On-Boarding : Contract' section.");
			}else{
				isTestCasePass = false;
				fnsTake_Screen_Shot("ContractNotDisplay");
				fnsApps_Report_Logs("FAILED == Newly Created 'Signed' Contract is not display into 'On-Boarding : Contract' section, Please refer screenshot >>ContractNotDisplay"+fnsScreenShot_Date_format());
				throw new Exception("FAILED == Newly Created 'Signed' Contract is not display into 'On-Boarding : Contract' section, Please refer screenshot >>ContractNotDisplay"+fnsScreenShot_Date_format())	;
			}
		
		}	*/
	}catch(Throwable t){
		isTestCasePass = false;
		throw new Exception(Throwables.getStackTraceAsString(t));	
	}
}




@Test(dependsOnMethods={"EditApplicant_ContractTab_AddNewContract_ThenGoTo_OnBoardingTab_ContractSection_and_Verify_ContractDisplay"}, priority = 9, description="[Steps<Go back to contract tab again> Edit the same contract> Enter the expiry date and click save icon.> <Click on Add New Schedule > Enter all the information as :Schedule name,unit,currency,No of hours, country,role,Std rate,min rate,max rate,bill amount and click save.>,   Expected<The 'Data Updated successfully' message should come.>]")
public void EditApplicant_ContractTab_EditContractVerifyDataSaved_Then_AddNewPaymentSchedule_and_Verify_ADDEDSuccessfully() throws Throwable{
	fnsApps_Report_Logs("=========================================================================================================================================");
	fnsApps_Report_Logs("########### Test Case :::::9 EditApplicant_ContractTab_EditContractVerifyDataSaved_Then_AddNewPaymentSchedule_and_Verify_ADDEDSuccessfully");
	try{	
	
		fncClicking_on_TAB("Contract", "EditApplicant_Contract_TAB", "EditApplicant_ContractTab_WIPContracts_Add_Bttn");
		
		for(int NewRowAdd=1; NewRowAdd<=After_DataAdded; NewRowAdd++){
			String RowXpath = OR_Grip.getProperty("EditApplicant_ContractTab_WIPContracts_TableHeader_Data")+"/tr["+NewRowAdd+"]";
			String RowText = TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(RowXpath).toLowerCase().trim();
			if(RowText.contains(Comments_Free_Text.toLowerCase().trim())){
				NewlyAddedRowNo = NewRowAdd;
				break;
			}
		}
	
		String ContractTab_ExpiryDate_BttnXpath = OR_Grip.getProperty("EditApplicant_ContractTab_WIPContracts_ExpiryDate_Bttn")+(NewlyAddedRowNo-1)+":exprDate']/button";
					
		fncEdit_and_Save_ButtonClick_WithOut_OR("EditApplicant_ContractTab_WIPContracts_TableHeader_Data", NewlyAddedRowNo, "edit");
		Thread.sleep(4000);
			
	//	fncCalendarDatePicker_NullMeansCurrent(ContractTab_ExpiryDate_BttnXpath, null, fnsReturn_Requried_Month("MMMM", 3), null);
		fncCalendarDatePicker_BySelectValues_From_DropDown(ContractTab_ExpiryDate_BttnXpath, fnsReturn_Requried_Month("MMM", 2), null);
		Thread.sleep(1000);
					
		fncEdit_and_Save_ButtonClick_WithOut_OR("EditApplicant_ContractTab_WIPContracts_TableHeader_Data", NewlyAddedRowNo, "save");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
			
		fncVerify_DataUpdated_Successfully("IPulse_ValidationMessage_Div", "WIP Contracts");
		
		
		
		//Payment Schedules 
		Before_DataAdded = TestSuiteBase_MonitorPlan.WithOut_OR_Retrun_TotalRowCount_OfTable(OR_Grip.getProperty("EditApplicant_ContractTab_PaymentSchedules_TableHeader"));
		String TableText = TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(OR_Grip.getProperty("EditApplicant_ContractTab_PaymentSchedules_TableHeader")).toLowerCase();
		if (TableText.contains("no payment schedules have been added")){
			Before_DataAdded = 0;
		}
		
		
		fnsGet_Element_Enabled("EditApplicant_ContractTab_PaymentSchedules_AddNewSchedule_Bttn");
		fnsWait_and_Click("EditApplicant_ContractTab_PaymentSchedules_AddNewSchedule_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		fncAssert_Contains(OR_Grip.getProperty("EditApplicant_ContractTab_PaymentSchedules_Popup_Title"), "Add Payment Schedule", "'Add Payment Schedule' Popup is not getting opened");
		
		fnsGet_Element_Enabled("EditApplicant_ContractTab_PaymentSchedules_Popup_ScheduleName");
		fnsWait_and_Type("EditApplicant_ContractTab_PaymentSchedules_Popup_ScheduleName", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Popup_ScheduleName"));
	
		fnsDD_value_Select_By_MatchingText_DownKeyPress("EditApplicant_ContractTab_PaymentSchedules_Popup_Unit_DD", "EditApplicant_ContractTab_PaymentSchedules_Popup_Unit_Value", "li", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Popup_Unit"));
		
		TestSuiteBase_MonitorPlan.WithOut_OR_fnDD_SelectValue_By_MatchingText(OR_Grip.getProperty("EditApplicant_ContractTab_PaymentSchedules_Popup_Currency_DD"), OR_Grip.getProperty("EditApplicant_ContractTab_PaymentSchedules_Popup_Currency_Value"), "li", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Popup_Currency"));
	//	fnsDD_value_Select_By_MatchingText_DownKeyPress("EditApplicant_ContractTab_PaymentSchedules_Popup_Currency_DD", "EditApplicant_ContractTab_PaymentSchedules_Popup_Currency_Value", "li",  fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Popup_Currency"));
		
		fnsGet_Element_Enabled("EditApplicant_ContractTab_PaymentSchedules_Popup_ScheduleComments");
		fnsWait_and_Type("EditApplicant_ContractTab_PaymentSchedules_Popup_ScheduleComments", Comments_Free_Text);
		
		fnsGet_Element_Enabled("EditApplicant_ContractTab_PaymentSchedules_Popup_Primary");
		fnsWait_and_Click("EditApplicant_ContractTab_PaymentSchedules_Popup_Primary");
			
		
		fnsGet_Element_Enabled("EditApplicant_ContractTab_PaymentSchedules_Popup_ADD_Bttn");
		fnsWait_and_Click("EditApplicant_ContractTab_PaymentSchedules_Popup_ADD_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		fnsGet_Element_Enabled("EditApplicant_ContractTab_PaymentSchedules_Popup_ADD_NoOfHours");
		fnsWait_and_Type("EditApplicant_ContractTab_PaymentSchedules_Popup_ADD_NoOfHours", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Popup_ADD_NoOfHours").split(":")[0]);
		
		TestSuiteBase_MonitorPlan.WithOut_OR_fnDD_SelectValue_By_MatchingText(OR_Grip.getProperty("EditApplicant_ContractTab_PaymentSchedules_Popup_ADD_Country_DD"), OR_Grip.getProperty("EditApplicant_ContractTab_PaymentSchedules_Popup_ADD_Country_Value"), "li", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Popup_ADD_Country"));
		
		//fnsDD_value_Select_By_MatchingText_DownKeyPress("EditApplicant_ContractTab_PaymentSchedules_Popup_ADD_Country_DD", "EditApplicant_ContractTab_PaymentSchedules_Popup_ADD_Country_Value", "li", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Popup_ADD_Country"));
		
		fnsDD_value_Select_By_MatchingText_DownKeyPress("EditApplicant_ContractTab_PaymentSchedules_Popup_ADD_Role_DD", "EditApplicant_ContractTab_PaymentSchedules_Popup_ADD_Role_Value", "li", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Popup_ADD_Role"));
		
		fnsDD_value_Select_By_MatchingText_DownKeyPress("EditApplicant_ContractTab_PaymentSchedules_Popup_ADD_IsBundle_DD", "EditApplicant_ContractTab_PaymentSchedules_Popup_ADD_IsBundle_Value", "li", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Popup_ADD_IsBundle"));
		
		fnsGet_Element_Enabled("EditApplicant_ContractTab_PaymentSchedules_Popup_ADD_StandardRate");
		fnsWait_and_Type("EditApplicant_ContractTab_PaymentSchedules_Popup_ADD_StandardRate", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Popup_ADD_StandardRate"));
		
		fnsGet_Element_Enabled("EditApplicant_ContractTab_PaymentSchedules_Popup_ADD_MinRate");
		fnsWait_and_Type("EditApplicant_ContractTab_PaymentSchedules_Popup_ADD_MinRate", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Popup_ADD_MinRate"));
		
		fnsGet_Element_Enabled("EditApplicant_ContractTab_PaymentSchedules_Popup_ADD_MaxRate");
		fnsWait_and_Type("EditApplicant_ContractTab_PaymentSchedules_Popup_ADD_MaxRate", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Popup_ADD_MaxRate"));
			
		fnsGet_Element_Enabled("EditApplicant_ContractTab_PaymentSchedules_Popup_ADD_BillingAmount");
		fnsWait_and_Type("EditApplicant_ContractTab_PaymentSchedules_Popup_ADD_BillingAmount", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Popup_ADD_BillingAmount"));
		
		
		fnsGet_Element_Enabled("EditApplicant_ContractTab_PaymentSchedules_Popup_Save_Bttn");
		fnsWait_and_Click("EditApplicant_ContractTab_PaymentSchedules_Popup_Save_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		fncVerify_NewRow_Added_into_Table("Payment Schedules", OR_Grip.getProperty("EditApplicant_ContractTab_PaymentSchedules_TableHeader"), Before_DataAdded);
		fncVerify_DataUpdated_Successfully("IPulse_ValidationMessage_Div", "Payment Schedules");
	}catch(Throwable t){
		isTestCasePass = false;
		throw new Exception(Throwables.getStackTraceAsString(t));	
	}
}





@Test(dependsOnMethods={"EditApplicant_ContractTab_EditContractVerifyDataSaved_Then_AddNewPaymentSchedule_and_Verify_ADDEDSuccessfully"}, priority = 10, description="[Steps<Go to Qualifications> Standards >Click on Add.Enter mandatory information and click Save.>,   Expected<'Data Updated Successfully' message should come.>]" )
public void EditApplicant_QualificationsTab_StandardsSection_AddNew_Record_and_Verify_AddedSuccessfully() throws Throwable{
	fnsApps_Report_Logs("=========================================================================================================================================");
	fnsApps_Report_Logs("#################### Test Case ::::10 EditApplicant_QualificationsTab_StandardsSection_AddNew_Record_and_Verify_AddedSuccessfully");
	try{	
		fncClicking_on_TAB("Qualifications", "EditApplicant_Qualifications_TAB", "EditApplicant_QualificationsTab_Standards_Add_Bttn");
		
		fnsGet_Element_Enabled("EditApplicant_QualificationsTab_Standards_Add_Bttn");
		fnsWait_and_Click("EditApplicant_QualificationsTab_Standards_Add_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		fncAssert_Contains(OR_Grip.getProperty("EditApplicant_QualificationsTab_Standards_Popup_Title"), "Add New Standard Qualification", "'Add New Standard Qualification' Popup is not getting opened");
		
		fnsGet_Element_Enabled("EditApplicant_QualificationsTab_Standards_Popup_LK_Bttn");
		fnsWait_and_Click("EditApplicant_QualificationsTab_Standards_Popup_LK_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		fnsGet_Element_Enabled("EditApplicant_QualificationsTab_Standards_Popup_LK_TextField");
		fnsWait_and_Type("EditApplicant_QualificationsTab_Standards_Popup_LK_TextField", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Popup_LK_TextField"));
		TestSuiteBase_MonitorPlan.fnsSelect_lookup_FirstCheckBox_and_Return();
	//	TestSuiteBase_MonitorPlan.fnsLookup_RadioBttn_Select();
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		fnsGet_Element_Enabled("EditApplicant_QualificationsTab_Standards_Popup_Save_Bttn");
		fnsWait_and_Click("EditApplicant_QualificationsTab_Standards_Popup_Save_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		fncVerify_DataUpdated_Successfully("IPulse_ValidationMessage_Div", "QualificationsTab: 'Standards' Section");
	}catch(Throwable t){
		isTestCasePass = false;
		throw new Exception(Throwables.getStackTraceAsString(t));	
	}
}



@Test(dependsOnMethods={"EditApplicant_QualificationsTab_StandardsSection_AddNew_Record_and_Verify_AddedSuccessfully"}, priority = 11, description="[Steps<Go to Qualifications> Language >Click on Add.Enter mandatory information and select the checkboxes and click Save.>,   Expected<'Data Updated Successfully' message should come.>]" )
public void EditApplicant_QualificationsTab_LanguageSection_AddNew_Record_and_Verify_AddedSuccessfully() throws Throwable{
	fnsApps_Report_Logs("=========================================================================================================================================");
	fnsApps_Report_Logs("######################## Test Case ::::11 EditApplicant_QualificationsTab_LanguageSection_AddNew_Record_and_Verify_AddedSuccessfully");
	try{
		fncClicking_on_TAB("Qualifications: 'Language' Section", "EditApplicant_QualificationsTab_LanguageSection_TAB", "EditApplicant_QualificationsTab_Language_Add_Bttn");
		
		Before_DataAdded = TestSuiteBase_MonitorPlan.WithOut_OR_Retrun_TotalRowCount_OfTable(OR_Grip.getProperty("EditApplicant_QualificationsTab_Language_TableHeader"));
		String TableText = TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(OR_Grip.getProperty("EditApplicant_QualificationsTab_Language_TableHeader")).toLowerCase();
		if (TableText.contains("no languages have been added")){
			Before_DataAdded = 0;
		}
		
		fnsGet_Element_Enabled("EditApplicant_QualificationsTab_Language_Add_Bttn");
		fnsWait_and_Click("EditApplicant_QualificationsTab_Language_Add_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		for(int wait=0; wait<=(Long.parseLong(CONFIG.getProperty("ElementWaitTime"))/4); wait++){
			After_DataAdded = TestSuiteBase_MonitorPlan.WithOut_OR_Retrun_TotalRowCount_OfTable(OR_Grip.getProperty("EditApplicant_QualificationsTab_Language_TableHeader"));
			if(Before_DataAdded<After_DataAdded){
				break;
			}else{
				Thread.sleep(4000);
			}
			if(wait==(Long.parseLong(CONFIG.getProperty("ElementWaitTime"))/4)){
				isTestCasePass = false;
				fnsTake_Screen_Shot("TimeOut");
				fnsApps_Report_Logs("FAILED == After clicking on ADD button, new Row is not coming, Getting TimeOut after <"+(wait*4)+">Seconds Wait. Please refer screen shot >> TimeOut"+fnsScreenShot_Date_format());
				throw new Exception("FAILED == After clicking on ADD button, new Row is not coming, Getting TimeOut after <"+(wait*4)+">Seconds Wait. Please refer screen shot >> TimeOut"+fnsScreenShot_Date_format());
			}
		}
		
		fnsGet_Element_Enabled("EditApplicant_QualificationsTab_Language_LanguageDD_Click");
		fnsDD_value_Select_By_MatchingText_DownKeyPress("EditApplicant_QualificationsTab_Language_LanguageDD_Click", "EditApplicant_QualificationsTab_Language_LanguageDD_Value", "li",  fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "LanguageDD"));
		
		fnsGet_Element_Enabled("EditApplicant_QualificationsTab_Language_Read_CheckBox");
		fnsWait_and_Click("EditApplicant_QualificationsTab_Language_Read_CheckBox");
		
		fnsGet_Element_Enabled("EditApplicant_QualificationsTab_Language_Speak_CheckBox");
		fnsWait_and_Click("EditApplicant_QualificationsTab_Language_Speak_CheckBox");
		
		fnsGet_Element_Enabled("EditApplicant_QualificationsTab_Language_Write_CheckBox");
		fnsWait_and_Click("EditApplicant_QualificationsTab_Language_Write_CheckBox");
			
		fncEdit_and_Save_ButtonClick_WithOut_OR(("EditApplicant_QualificationsTab_Language_TableHeader_Data"), 1, "save");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		fncVerify_DataUpdated_Successfully("IPulse_ValidationMessage_Div", "QualificationsTab: 'Language' Section");
	}catch(Throwable t){
		isTestCasePass = false;
		throw new Exception(Throwables.getStackTraceAsString(t));	
	}
}



@Test(dependsOnMethods={"EditApplicant_QualificationsTab_LanguageSection_AddNew_Record_and_Verify_AddedSuccessfully"}, priority = 12, description="[Steps<Go to Qualifications> Disqualifications>Click on Add.Enter mandatory information and click Save.> <Click on Add for the Facility Disqualification and enter all the information and click Save.> <Click on Add for the Facility/Standard Rotation and enter all the information and click Save.>,   Expected<Data Updated successfully message should come.>]" )
public void EditApplicant_QualificationsTab_DisqualificationsSection_AddEdit_Records_and_Verify_Added_and_UpdateSuccessfully() throws Throwable{
	fnsApps_Report_Logs("=========================================================================================================================================");
	fnsApps_Report_Logs("######### Test Case :::12 EditApplicant_QualificationsTab_DisqualificationsSection_AddEdit_Records_and_Verify_Added_and_UpdateSuccessfully");
	try{
		fncClicking_on_TAB("Qualifications: 'Disqualifications' Section", "EditApplicant_QualificationsTab_DisqualificationSection_TAB", "EditApplicant_QualificationsTab_Disqualification_FacilityDisqualification_Add_Bttn");
		
		//Validate if any record found for given data the first delete it.
		Before_DataAdded = TestSuiteBase_MonitorPlan.WithOut_OR_Retrun_TotalRowCount_OfTable(OR_Grip.getProperty("EditApplicant_QualificationsTab_Disqualification_FacilityDisqualification_TableHeader"));
		String FacilityDisqualification_TableText = fnsGet_Field_TEXT("EditApplicant_QualificationsTab_Disqualification_FacilityDisqualification_TableHeader").trim();
		if(FacilityDisqualification_TableText.contains(fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "FacilityCode_LK_Text"))){
			for(int LoopStart=1; LoopStart<=Before_DataAdded; LoopStart++){
				String RowXpath = OR_Grip.getProperty("EditApplicant_QualificationsTab_Disqualification_FacilityDisqualification_TableHeader_Data")+"/tr["+LoopStart+"]";
				String RowText = TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(RowXpath).trim();
				if(RowText.contains(fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "FacilityCode_LK_Text"))){
					LoopStart = LoopStart-1;
					Before_DataAdded = Before_DataAdded-1;
					MatchingText_RowXpath_Count = Before_DataAdded ;
					String FacilityDisqualification_DeleteBttnXpath = "//*[@id='mainForm:ApplicantTabView:rsQualTabView:resDisQualDT:"+LoopStart+":delBilInfo']";
					TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(FacilityDisqualification_DeleteBttnXpath);
					TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(FacilityDisqualification_DeleteBttnXpath);
					Thread.sleep(2000);
					
					String FacilityDisqualification_Delete_YES_BttnXpath = ".//*[@id='mainForm:ApplicantTabView:rsQualTabView:resDisQualDT:"+MatchingText_RowXpath_Count+":yshidebtn']";
					TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(FacilityDisqualification_Delete_YES_BttnXpath);
					TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(FacilityDisqualification_Delete_YES_BttnXpath);
					Thread.sleep(2000);
				}
			}
			Thread.sleep(2000);
		}
		
		Before_DataAdded = TestSuiteBase_MonitorPlan.WithOut_OR_Retrun_TotalRowCount_OfTable(OR_Grip.getProperty("EditApplicant_QualificationsTab_Disqualification_FacilityDisqualification_TableHeader"));
		String TableText = TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(OR_Grip.getProperty("EditApplicant_QualificationsTab_Disqualification_FacilityDisqualification_TableHeader")).toLowerCase();
		if (TableText.contains("no disqualification have been added")){
			Before_DataAdded = 0;
		}
		//Commented on 21.3.207 as new change came IPM-5364
		/*/fnsGet_Element_Enabled("EditApplicant_QualificationsTab_Disqualification_FacilityDisqualification_Add_Bttn");
		fnsWait_and_Click("EditApplicant_QualificationsTab_Disqualification_FacilityDisqualification_Add_Bttn");
		TestSuiteBase_Aspirago.fnsLoading_Progressingwait(5);
		
		fnsGet_Element_Enabled("EditApplicant_QualificationsTab_Disqualification_FacilityDisqualification_FacilityCode_LK_Bttn");
		fnsWait_and_Click("EditApplicant_QualificationsTab_Disqualification_FacilityDisqualification_FacilityCode_LK_Bttn");
		TestSuiteBase_Aspirago.fnsLoading_Progressingwait(4);
		fnsGet_Element_Enabled("EditApplicant_QualificationsTab_Disqualification_FacilityDisqualification_FacilityCode_LK_Text");
		fnsWait_and_Clear("EditApplicant_QualificationsTab_Disqualification_FacilityDisqualification_FacilityCode_LK_Text");
		fnsWait_and_Type("EditApplicant_QualificationsTab_Disqualification_FacilityDisqualification_FacilityCode_LK_Text", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "FacilityCode_LK_Text"));
		TestSuiteBase_MonitorPlan.fnsLookup_RadioBttn_Select();/
		*/
		
		fnsGet_Element_Enabled("EditApplicant_QualificationsTab_Disqualification_FacilityDisqualification_Add_Bttn");
		fnsWait_and_Click("EditApplicant_QualificationsTab_Disqualification_FacilityDisqualification_Add_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		fnsGet_Element_Enabled("EditApplicant_QualificationsTab_Disqualification_FacilityDisqualification_FacilityCode_LK_Text");
		fnsWait_and_Clear("EditApplicant_QualificationsTab_Disqualification_FacilityDisqualification_FacilityCode_LK_Text");
		fnsWait_and_Type("EditApplicant_QualificationsTab_Disqualification_FacilityDisqualification_FacilityCode_LK_Text", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "FacilityCode_LK_Text"));
		TestSuiteBase_MonitorPlan.fnsSelect_lookup_FirstCheckBox_and_Return();
		
		
		
		
		
		Thread.sleep(1500);
		fnsGet_Element_Enabled("EditApplicant_QualificationsTab_Disqualification_FacilityDisqualification_DisqualificationType_DD_Click");
		fnsDD_value_Select_By_MatchingText_DownKeyPress("EditApplicant_QualificationsTab_Disqualification_FacilityDisqualification_DisqualificationType_DD_Click", "EditApplicant_QualificationsTab_Disqualification_FacilityDisqualification_DisqualificationType_DD_Value", "li", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "DisqualificationType_DD"));
		
		fncEdit_and_Save_ButtonClick_WithOut_OR(("EditApplicant_QualificationsTab_Disqualification_FacilityDisqualification_TableHeader_Data"), 1, "Save");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		fncVerify_DataUpdated_Successfully("IPulse_ValidationMessage_Div", "QualificationsTab: 'Disqualifications' Section");
		
		//Editing and Delete Newly Added Records
		fncEdit_and_Save_ButtonClick_WithOut_OR(("EditApplicant_QualificationsTab_Disqualification_FacilityDisqualification_TableHeader_Data"), 1, "edit");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		fnsGet_Element_Enabled("EditApplicant_QualificationsTab_Disqualification_FacilityDisqualification_DisqualificationType_DD_Click");
		fnsDD_value_Select_By_MatchingText_DownKeyPress("EditApplicant_QualificationsTab_Disqualification_FacilityDisqualification_DisqualificationType_DD_Click", "EditApplicant_QualificationsTab_Disqualification_FacilityDisqualification_DisqualificationType_DD_Value", "li", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "DisqualificationType_DD_Edit"));
		Thread.sleep(2000);
	
		fncEdit_and_Save_ButtonClick_WithOut_OR(("EditApplicant_QualificationsTab_Disqualification_FacilityDisqualification_TableHeader_Data"), 1, "Save");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		fncVerify_DataUpdated_Successfully("IPulse_ValidationMessage_Div", "QualificationsTab: 'Disqualifications' Section");
		
	}catch(Throwable t){
		isTestCasePass = false;
		throw new Exception(Throwables.getStackTraceAsString(t));	
	}
	
	
}




@Test(dependsOnMethods={"EditApplicant_QualificationsTab_DisqualificationsSection_AddEdit_Records_and_Verify_Added_and_UpdateSuccessfully"}, priority = 13, description="[Steps<Click on Add for the Certification.Enter all mandatory information and click Save.>,   Expected<'Data Updated Successfully' message should come.>]" )
public void EditApplicant_CertificationsTab__AddEdit_Records_and_Verify_Added_and_UpdateSuccessfully() throws Throwable{
	fnsApps_Report_Logs("=========================================================================================================================================");
	fnsApps_Report_Logs("############# Test Case :::::13 EditApplicant_CertificationsTab__AddEdit_Records_and_Verify_Added_and_UpdateSuccessfully");
	try{
		fncClicking_on_TAB("Certifications", "EditApplicant_Certifications_TAB", "EditApplicant_CertificationsTab_Add_Bttn");
		
		Before_DataAdded = TestSuiteBase_MonitorPlan.WithOut_OR_Retrun_TotalRowCount_OfTable(OR_Grip.getProperty("EditApplicant_CertificationsTab_TableHeader"));
		String TableText = TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(OR_Grip.getProperty("EditApplicant_CertificationsTab_TableHeader")).toLowerCase();
		if (TableText.contains("no certifications have been added")){
			Before_DataAdded = 0;
		}
		fnsGet_Element_Enabled("EditApplicant_CertificationsTab_Add_Bttn");
		fnsWait_and_Click("EditApplicant_CertificationsTab_Add_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		fncAssert_Contains(OR_Grip.getProperty("EditApplicant_CertificationsTab_AddPopup_Title"), "Add New Certificate", "Certifications: 'Add New Certificate' Popup is not getting opened");
		
		fnsGet_Element_Enabled("EditApplicant_CertificationsTab_AddPopup_Division_DD_Click");
		Thread.sleep(3000);
		fnsDD_value_Select_By_MatchingText_DownKeyPress("EditApplicant_CertificationsTab_AddPopup_Division_DD_Click", "EditApplicant_CertificationsTab_AddPopup_Division_DD_Value", "li", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "DivisionDD"));
		
		
		fnsGet_Element_Enabled("EditApplicant_CertificationsTab_AddPopup_CertificationType_DD_Click");
		Thread.sleep(3000);
		fnsDD_value_Select_By_MatchingText_DownKeyPress("EditApplicant_CertificationsTab_AddPopup_CertificationType_DD_Click", "EditApplicant_CertificationsTab_AddPopup_CertificationType_DD_Value", "li", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "CertificationType_DD"));
		
		fncUploadFile("EditApplicant_CertificationsTab_AddPopup_BrowseFile", "'Add New Certificate' PopUp"); // New Changes added.
		
		
		fnsGet_Element_Enabled("EditApplicant_CertificationsTab_AddPopup_CertifiedBy");
		fnsWait_and_Type("EditApplicant_CertificationsTab_AddPopup_CertifiedBy", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "CertifiedBy"));
		
		fncCalendarDatePicker_BySelectValues_From_DropDown(OR_Grip.getProperty("EditApplicant_CertificationsTab_AddPopup_CertificateDate_Bttn"), fnsReturn_Requried_Month("MMM", 1), fnsReturn_Requried_Year(-1));
		
		fnsGet_Element_Enabled("EditApplicant_CertificationsTab_AddPopup_Save_Bttn");
		fnsWait_and_Click("EditApplicant_CertificationsTab_AddPopup_Save_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		fncVerify_DataUpdated_Successfully("IPulse_ValidationMessage_Div", "Certifications");
		
		fncVerify_NewRow_Added_into_Table("Certifications Table", OR_Grip.getProperty("EditApplicant_CertificationsTab_TableHeader"), Before_DataAdded);
		
		//Fetching newly added row no.
		for(int NewRowAdd=1; NewRowAdd<=After_DataAdded; NewRowAdd++){
			String RowXpath = OR_Grip.getProperty("EditApplicant_CertificationsTab_TableHeader_Data")+"/tr["+NewRowAdd+"]";
			String RowText = TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(RowXpath).toLowerCase().trim();
			if(RowText.contains(fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "CertificationType_DD").toLowerCase().trim())){
				NewlyAddedRowNo = NewRowAdd;
				break;
			}
		}
		
		String CertificationsTab_ExpirationDate_BttnXpath = OR_Grip.getProperty("EditApplicant_CertificationsTab_ExpirationDate_Bttn")+(NewlyAddedRowNo-1)+":expiryDate']/button";
			
		fncEdit_and_Save_ButtonClick_WithOut_OR(("EditApplicant_CertificationsTab_TableHeader_Data"), NewlyAddedRowNo, "edit");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		fncCalendarDatePicker_BySelectValues_From_DropDown(CertificationsTab_ExpirationDate_BttnXpath, fnsReturn_Requried_Month("MMM", 4), fnsReturn_Requried_Year(2));
		Thread.sleep(1000);
		
		fncEdit_and_Save_ButtonClick_WithOut_OR(("EditApplicant_CertificationsTab_TableHeader_Data"), NewlyAddedRowNo, "save");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		fncVerify_DataUpdated_Successfully("IPulse_ValidationMessage_Div", "Certifications");
	}catch(Throwable t){
		isTestCasePass = false;
		throw new Exception(Throwables.getStackTraceAsString(t));	
	}
}



/*//Comment because of IPM-14069
@Test(dependsOnMethods={"EditApplicant_CertificationsTab__AddEdit_Records_and_Verify_Added_and_UpdateSuccessfully"}, priority = 14, description="[Steps<Click on Add for the Certification.Enter all mandatory information and click Save.>,   Expected<'Data Updated Successfully' message should come.>]" )
public void EditApplicant_TrainingTab_AddNew_Record_and_Verify_AddedSuccessfully() throws Throwable{
	fnsApps_Report_Logs("=========================================================================================================================================");
	fnsApps_Report_Logs("########################## Test Case :::::14 EditApplicant_TrainingTab_AddNew_Record_and_Verify_AddedSuccessfully");
	try{
		fncClicking_on_TAB("Training", "EditApplicant_Training_TAB", "EditApplicant_TrainingTab_Add_Bttn");
		
		Before_DataAdded = TestSuiteBase_MonitorPlan.WithOut_OR_Retrun_TotalRowCount_OfTable(OR_Grip.getProperty("EditApplicant_TrainingTab_TableHeader"));
		String TableText = TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(OR_Grip.getProperty("EditApplicant_TrainingTab_TableHeader")).toLowerCase();
		if (TableText.contains("no training have been added")){
			Before_DataAdded = 0;
		}
		fnsGet_Element_Enabled("EditApplicant_TrainingTab_Add_Bttn");
		fnsWait_and_Click("EditApplicant_TrainingTab_Add_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		fncAssert_Contains(OR_Grip.getProperty("EditApplicant_TrainingTab_AddPopup_Title"), "Add a new Training", "Training: 'Add a new Training' Popup is not getting opened");
		
		fnsGet_Element_Enabled("EditApplicant_TrainingTab_AddPopup_Division_DD_Click");
		fnsDD_value_Select_By_MatchingText_DownKeyPress("EditApplicant_TrainingTab_AddPopup_Division_DD_Click", "EditApplicant_TrainingTab_AddPopup_Division_DD_Value", "li", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "DivisionDD"));
		
		
		fnsGet_Element_Enabled("EditApplicant_TrainingTab_AddPopup_TrainingCourse_DD_Click");
		fnsDD_value_Select_By_MatchingText_DownKeyPress("EditApplicant_TrainingTab_AddPopup_TrainingCourse_DD_Click", "EditApplicant_TrainingTab_AddPopup_TrainingCourse_DD_Value", "li", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "TrainingCourse_DD"));
		
	
		fnsGet_Element_Enabled("EditApplicant_TrainingTab_AddPopup_Description");
		fnsWait_and_Type("EditApplicant_TrainingTab_AddPopup_Description", Comments_Free_Text );
		
		fnsGet_Element_Enabled("EditApplicant_TrainingTab_AddPopup_Trainer");
		fnsWait_and_Type("EditApplicant_TrainingTab_AddPopup_Trainer", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Training_Trainer"));
		
		fncCalendarDatePicker_BySelectValues_From_DropDown(OR_Grip.getProperty("EditApplicant_TrainingTab_AddPopup_TrainingDate_Bttn"), null, null);
		
		Thread.sleep(4000);
		fncCalendarDatePicker_BySelectValues_From_DropDown(OR_Grip.getProperty("EditApplicant_TrainingTab_AddPopup_ExpirationDate_Bttn"), null, null);
		
		fnsGet_Element_Enabled("EditApplicant_TrainingTab_AddPopup_Save_Bttn");
		fnsWait_and_Click("EditApplicant_TrainingTab_AddPopup_Save_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		fncVerify_DataUpdated_Successfully("IPulse_ValidationMessage_Div", "Training");
		
		fncVerify_NewRow_Added_into_Table("Training Table", OR_Grip.getProperty("EditApplicant_TrainingTab_TableHeader"), Before_DataAdded);
	}catch(Throwable t){
		isTestCasePass = false;
		throw new Exception(Throwables.getStackTraceAsString(t));	
	}
}
*/


@Test(dependsOnMethods={"EditApplicant_CertificationsTab__AddEdit_Records_and_Verify_Added_and_UpdateSuccessfully"}, priority = 15, description="[Steps<Click on Add for Notes/Remarks>,   Expected<'Data Updated Successfully' message should come.>]" )
public void EditApplicant_DocumentsNotesTab_NotesRemarksSection_AddEdit_Records_and_Verify_Added_and_UpdateSuccessfully() throws Throwable{
	fnsApps_Report_Logs("=========================================================================================================================================");
	fnsApps_Report_Logs("######### Test Case ::15 EditApplicant_DocumentsNotesTab_NotesRemarksSection_AddEdit_Records_and_Verify_Added_and_UpdateSuccessfully");
	try{
		fncClicking_on_TAB("DocumentsNotes: 'NotesRemarks'", "EditApplicant_DocumentsNotes_TAB", "EditApplicant_DocumentsNotesTab_NotesRemarks_Add_Bttn");
		
		fnsGet_Element_Enabled("EditApplicant_DocumentsNotesTab_NotesRemarks_Add_Bttn");
		fnsWait_and_Click("EditApplicant_DocumentsNotesTab_NotesRemarks_Add_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		fnsGet_Element_Enabled("EditApplicant_DocumentsNotesTab_NotesRemarks_Type_DD_Click");
		fnsDD_value_Select_By_MatchingText_DownKeyPress("EditApplicant_DocumentsNotesTab_NotesRemarks_Type_DD_Click", "EditApplicant_DocumentsNotesTab_NotesRemarks_Type_DD_Value", "li", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "NotesRemarks_Type_DD"));
		
		fnsGet_Element_Enabled("EditApplicant_DocumentsNotesTab_NotesRemarks_Notes_TextBox");
		fnsWait_and_Type("EditApplicant_DocumentsNotesTab_NotesRemarks_Notes_TextBox", "Automation Test");
			
		fncEdit_and_Save_ButtonClick_WithOut_OR(("EditApplicant_DocumentsNotesTab_NotesRemarks_TableHeader_Data"), 1, "save");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
			
		fncVerify_DataUpdated_Successfully("IPulse_ValidationMessage_Div", "DocumentsNotes: 'NotesRemarks'");
		
		fncEdit_and_Save_ButtonClick_WithOut_OR(("EditApplicant_DocumentsNotesTab_NotesRemarks_TableHeader_Data"), 1, "edit");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(3);
		
		fnsGet_Element_Enabled("EditApplicant_DocumentsNotesTab_NotesRemarks_Notes_TextBox");
		fnsWait_and_Type("EditApplicant_DocumentsNotesTab_NotesRemarks_Notes_TextBox", "               Edit Automation Test");
		
		fncEdit_and_Save_ButtonClick_WithOut_OR(("EditApplicant_DocumentsNotesTab_NotesRemarks_TableHeader_Data"), 1, "save");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(30);
			
		fncVerify_DataUpdated_Successfully("IPulse_ValidationMessage_Div", "DocumentsNotes: 'NotesRemarks'");
	}catch(Throwable t){
		isTestCasePass = false;
		throw new Exception(Throwables.getStackTraceAsString(t));	
	}
}



@Test(dependsOnMethods={"EditApplicant_DocumentsNotesTab_NotesRemarksSection_AddEdit_Records_and_Verify_Added_and_UpdateSuccessfully"}, priority = 16, description="[Steps<Click on Add for Documents>,   Expected<'Data Updated Successfully' message should come.>]" )
public void EditApplicant_DocumentsNotesTab_GeneralDocumentsSection_AddEdit_Records_and_Verify_Added_and_UpdateSuccessfully() throws Throwable{
	fnsApps_Report_Logs("=========================================================================================================================================");
	fnsApps_Report_Logs("######### Test Case ::16 EditApplicant_DocumentsNotesTab_GeneralDocumentsSection_AddEdit_Records_and_Verify_Added_and_UpdateSuccessfully");
	try{	
		fnsGet_Element_Enabled("EditApplicant_DocumentsNotesTab_GeneralDocuments_Add_Bttn");
		fnsWait_and_Click("EditApplicant_DocumentsNotesTab_GeneralDocuments_Add_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
	
		fncAssert_Contains(OR_Grip.getProperty("EditApplicant_DocumentsNotesTab_GeneralDocuments_AddPopup_Title"), "Add Resource Document", "General Documents: 'Add Applicant Document' Popup is not getting opened");
	
		Thread.sleep(2000);
		//fnsGet_Element_Enabled("EditApplicant_DocumentsNotesTab_GeneralDocuments_AddPopup_BrowseFile"); // Line is commented to run script on Chrome
		fncUploadFile("EditApplicant_DocumentsNotesTab_GeneralDocuments_AddPopup_BrowseFile", "'Add Applicant Document' PopUp");
	
		fnsGet_Element_Enabled("EditApplicant_DocumentsNotesTab_GeneralDocuments_AddPopup_Type_DD_Click");
		fnsDD_value_Select_By_MatchingText_DownKeyPress("EditApplicant_DocumentsNotesTab_GeneralDocuments_AddPopup_Type_DD_Click", "EditApplicant_DocumentsNotesTab_GeneralDocuments_AddPopup_Type_DD_Value", "li", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "GeneralDocuments_AddPopup_Type_DD"));
		
		fnsGet_Element_Enabled("EditApplicant_DocumentsNotesTab_GeneralDocuments_AddPopup_SaveClose_Bttn");
		fnsWait_and_Click("EditApplicant_DocumentsNotesTab_GeneralDocuments_AddPopup_SaveClose_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
	
		fncVerify_DataUpdated_Successfully("IPulse_ValidationMessage_Div", "DocumentsNotes: 'General Documents'");
	}catch(Throwable t){
		isTestCasePass = false;
		throw new Exception(Throwables.getStackTraceAsString(t));	
	}
}


/*// Commented 12.5.2016 as Correspondence TAB has been removed.

/@Test(dependsOnMethods={"EditApplicant_DocumentsNotesTab_GeneralDocumentsSection_AddEdit_Records_and_Verify_Added_and_UpdateSuccessfully"}, priority = 17, description="[Steps<Correspondence> Click on Add for Resource correspondence.>,   Expected<The row for correspondence gets added successfully.>]" )
public void EditApplicant_CorrespondenceTab_AddNew_Record_and_Verify_AddedSuccessfully() throws Throwable{
	fnsApps_Report_Logs("=========================================================================================================================================");
	fnsApps_Report_Logs("########################## Test Case :::::17 EditApplicant_CorrespondenceTab_AddNew_Record_and_Verify_AddedSuccessfully");
	
	fncClicking_on_TAB("Correspondence", "EditResource_Correspondence_TAB", "EditApplicant_CorrespondenceTab_Add_Bttn");
	
	fnsGet_Element_Enabled("EditApplicant_CorrespondenceTab_Add_Bttn");
	fnsWait_and_Click("EditApplicant_CorrespondenceTab_Add_Bttn");
	TestSuiteBase_Aspirago.fnsLoading_Progressingwait(2);
	
	fncAssert_Contains(OR_Grip.getProperty("EditApplicant_CorrespondenceTab_AddPopup_Title"), "Select Correspondence Template", "Correspondence: 'Select Correspondence Template' Popup is not getting opened");
	
	fnsGet_Element_Enabled("EditApplicant_CorrespondenceTab_AddPopup_Email_DD_Click");
	fnsDD_value_Select_By_MatchingText_DownKeyPress("EditApplicant_CorrespondenceTab_AddPopup_Email_DD_Click", "EditApplicant_CorrespondenceTab_AddPopup_Email_DD_Value", "li", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Email_Template"));
	
	fnsGet_Element_Enabled("EditApplicant_CorrespondenceTab_AddPopup_Continue_Bttn");
	fnsWait_and_Click("EditApplicant_CorrespondenceTab_AddPopup_Continue_Bttn");
	TestSuiteBase_Aspirago.fnsLoading_Progressingwait(2);
	
	fncAssert_Contains(OR_Grip.getProperty("EditApplicant_CorrespondenceTab_NewEmailPopup_Title"), "New Email Correspondence", "Correspondence: 'New Email Correspondence' Popup is not getting opened");
	
	fnsGet_Element_Enabled("EditApplicant_CorrespondenceTab_NewEmailPopup_SendEmail_Bttn");
	
	fnsGet_Element_Enabled("EditApplicant_CorrespondenceTab_NewEmailPopup_From");
	fnsWait_and_Clear("EditApplicant_CorrespondenceTab_NewEmailPopup_From");
	fnsWait_and_Type("EditApplicant_CorrespondenceTab_NewEmailPopup_From", "Testscriptuser_From@automation.com");
	
	fnsGet_Element_Enabled("EditApplicant_CorrespondenceTab_NewEmailPopup_To");
	fnsWait_and_Clear("EditApplicant_CorrespondenceTab_NewEmailPopup_To");
	fnsWait_and_Type("EditApplicant_CorrespondenceTab_NewEmailPopup_To", "Testscriptuser_To@automation.com");
	
	fnsGet_Element_Enabled("EditApplicant_CorrespondenceTab_NewEmailPopup_Subject");
	fnsWait_and_Clear("EditApplicant_CorrespondenceTab_NewEmailPopup_Subject");
	fnsWait_and_Type("EditApplicant_CorrespondenceTab_NewEmailPopup_Subject", Comments_Free_Text);
	
	fnsGet_Element_Enabled("EditApplicant_CorrespondenceTab_NewEmailPopup_SendEmail_Bttn");
	fnsWait_and_Click("EditApplicant_CorrespondenceTab_NewEmailPopup_SendEmail_Bttn");
	TestSuiteBase_Aspirago.fnsLoading_Progressingwait(5);
	Thread.sleep(3000); // wait is putting to run script on chrome.
	
	
	TextFetched = fnsGet_Field_TEXT("EditApplicant_CorrespondenceTab_TableHeader").toLowerCase().trim();
	TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element_BY_OFFSET(OR_Grip.getProperty("EditApplicant_CorrespondenceTab_TableHeader"), 100, 25);
	
	try{
		assert(TextFetched.contains((Comments_Free_Text.toLowerCase().trim()))):"FAILED == 'New Email Template' data is not getting saved, Please refer screenshot >>DataNotSave"+fnsScreenShot_Date_format();
		fnsApps_Report_Logs("PASSED == Successfully saved 'New Email Template' data.");
	}catch(Throwable t){
		isTestCasePass = false;
		fnsTake_Screen_Shot("DataNotSave");
		fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
		throw new Exception(Throwables.getStackTraceAsString(t))	;
	}	
}/

*/


@Test(dependsOnMethods={"EditApplicant_DocumentsNotesTab_GeneralDocumentsSection_AddEdit_Records_and_Verify_Added_and_UpdateSuccessfully"}, priority = 18, description="[Steps<Go to CV tab>Mention the 'GRIP Resource Resume template'. Mention the text in the textarea.Click 'Generate Resume'.>,   Expected<'Data Updated Successfully' message should come.>]" )
public void EditApplicant_CVTab_Verify_DataSaveSuccessfully_and_PDF_Open_AfterClickingOn_GenerateResumeButton() throws Throwable{
	fnsApps_Report_Logs("=========================================================================================================================================");
	fnsApps_Report_Logs("##################### Test Case :::::18 EditApplicant_CVTab_Verify_PDF_Open_AfterClickingOn_GenerateResumeButton");
	
	try{
		String FileLocation = System.getProperty("user.home")+"\\Downloads";
		long AllFileSize_BeforeFileSave = FileUtils.sizeOfDirectory(new File(FileLocation));
	
		fncClicking_on_TAB("CV", "EditApplicant_CV_TAB", "EditApplicant_CvTab_GenerateResume_Bttn");
	
		fnsGet_Element_Enabled("EditApplicant_CvTab_Template_DD_Click");
		fnsDD_value_Select_By_MatchingText_DownKeyPress("EditApplicant_CvTab_Template_DD_Click", "EditApplicant_CvTab_Template_DD_Value", "li", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Template_DD"));
	
		fnsGet_Element_Enabled("EditApplicant_CvTab_BiographicalSketch_Text");
		fnsWait_and_Clear("EditApplicant_CvTab_BiographicalSketch_Text");
		fnsWait_and_Type("EditApplicant_CvTab_BiographicalSketch_Text", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "BiographicalSketch"));
	
		fnsGet_Element_Enabled("EditApplicant_CvTab_Education_Text");
		fnsWait_and_Clear("EditApplicant_CvTab_Education_Text");
		fnsWait_and_Type("EditApplicant_CvTab_Education_Text", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Education"));
	
	
		fnsGet_Element_Enabled("EditApplicant_CvTab_Experience_Text");
		fnsWait_and_Clear("EditApplicant_CvTab_Experience_Text");
		fnsWait_and_Type("EditApplicant_CvTab_Experience_Text", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Experience"));
	
		fnsGet_Element_Enabled("EditApplicant_CvTab_Skills_Text");
		fnsWait_and_Clear("EditApplicant_CvTab_Skills_Text");
		fnsWait_and_Type("EditApplicant_CvTab_Skills_Text", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Skills"));
	
		fnsGet_Element_Enabled("EditApplicant_CvTab_AdditionalTrainingAndskills_Text");
		fnsWait_and_Clear("EditApplicant_CvTab_AdditionalTrainingAndskills_Text");
		fnsWait_and_Type("EditApplicant_CvTab_AdditionalTrainingAndskills_Text", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "AdditionalTrainingAndSkills"));
	
		fnsGet_Element_Enabled("EditApplicant_CvTab_Publications_Text");
		fnsWait_and_Clear("EditApplicant_CvTab_Publications_Text");
		fnsWait_and_Type("EditApplicant_CvTab_Publications_Text", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Publications"));
	
		
		fnsGet_Element_Enabled("EditApplicant_Save_Bttn");
		fnsWait_and_Click("EditApplicant_Save_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		fncVerify_DataUpdated_Successfully("IPulse_ValidationMessage_Div", "CV");
		
		fnsGet_Element_Enabled("EditApplicant_CvTab_GenerateResume_Bttn");
		fnsWait_and_Click("EditApplicant_CvTab_GenerateResume_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(5);
		
	
		

		for(int waitloop=0; waitloop<12; waitloop++){
			try{	
				Actions action = new Actions(driver);
				action.keyDown(Keys.ALT);
				action.sendKeys("s");
				action.keyUp(Keys.ALT);
				action.build().perform();
				fnsApps_Report_Logs("PASSED == Successfully click on 'save' button of Browser dialog box.");
			    Thread.sleep(5000);
			}catch(Throwable t){
				fnsTake_Screen_Shot("BrowserFileSaveFail");
	    		throw new Exception("FAILED == "+Throwables.getStackTraceAsString(t)+", Please refer screenshot >> BrowserFileSaveFail"+fnsScreenShot_Date_format());		}
		
		
			try{
				AllFileSize_AfterFileSave = FileUtils.sizeOfDirectory(new File((FileLocation)));
				
				if(AllFileSize_BeforeFileSave<AllFileSize_AfterFileSave){
					Thread.sleep(1500);
					break;
				}
			}catch(IllegalArgumentException K){
				if(waitloop==6){
		    		fnsTake_Screen_Shot("FileDownloadFail");
		    		throw new Exception("FAILED == File is not getting download, after <"+waitloop+">attempts, Please refer screenshot >> FileDownloadFail"+fnsScreenShot_Date_format());
		    	}else{
		    		Thread.sleep(5000);
		    	}
			}
		}
		
		try{
			assert (AllFileSize_BeforeFileSave<AllFileSize_AfterFileSave):"FAILED == File is not getting download, Please refer screenshot >> FileDownloadFail"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED == Successfully download PDF file through Browser Dialog Popup, after clicking on 'Generate Resume' Button.");	  
		}catch(Throwable t){
			fnsTake_Screen_Shot("FileDownloadFail");
    		throw new Exception(Throwables.getStackTraceAsString(t));
    	}
		      
	    try{	
			Actions action = new Actions(driver);
			action.keyDown(Keys.ALT);
			action.sendKeys("q");
			action.keyUp(Keys.ALT);
			action.build().perform();
	        Thread.sleep(1000);
	        fnsApps_Report_Logs("PASSED == Successfully close Browser dialog box.");
		}catch(Throwable t){
			fnsTake_Screen_Shot("BrowserPdfDialogCloseFail");
			throw new Exception("FAILED == "+Throwables.getStackTraceAsString(t)+", Please refer screenshot >> BrowserPdfDialogCloseFail"+fnsScreenShot_Date_format());
		}
		
	}catch(Throwable t){
		isTestCasePass = false;
		fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
		throw new Exception(Throwables.getStackTraceAsString(t));		}	
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