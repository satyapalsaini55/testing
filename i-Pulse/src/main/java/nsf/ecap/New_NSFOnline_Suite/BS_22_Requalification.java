package nsf.ecap.New_NSFOnline_Suite;

import nsf.ecap.MonitorPlan_Suite.TestSuiteBase_MonitorPlan;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;



public class BS_22_Requalification extends TestSuiteBase_New_NSFOnline {

public String View_Name = "Automation - Requalification";
public String View_Name_Edited = "Automation - Requalification - Edited";
public String Standard = "";
public String Certificate_Number = "";

@Parameters({ "className" })
@BeforeTest
public void checkTestSkip(String className) throws Exception {
	if (className.isEmpty()) {
		className = this.getClass().getSimpleName();
	}
	RunningClassName = className;
	Running_Class_BS_Description = "[BS-22] Create and Submit Requalification";
	IsBrowserPresentAlready =false;
	fnsCheckClassLevelTestSkip(className);
}





@Test( priority = 0)
public void Launch_Browser_and_Successfully_Login_into_the_Application() throws Throwable{
	try{ 
		Standard = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Standard");
		Certificate_Number = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Certificate_Number");
		fnsApps_Report_Logs("################################## "+Running_Class_BS_Description);
		if (!IsBrowserPresentAlready) {
			IsBrowserPresentAlready = fnsBrowserLaunchAndLogin(Login_UserName, Login_Password);
			fnsSwitchAcoount_MultiAccess(RunningClassName, "SwitchAccount_DD");	
		}
	}catch(Throwable t){
		isTestCasePass = false;
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
	fnsApps_Report_Logs("=========================================================================================================================================");
}





@Test( priority = 1, dependsOnMethods={"Launch_Browser_and_Successfully_Login_into_the_Application"}, description="")
public void Create_Edit_and_Delete_View_and_OpenCertificationNumber_under_Requalification() throws Throwable{
	fnsApps_Report_Logs("################### Test Case ::1 Create_Edit_and_Delete_View_and_OpenCertificationNumber_under_Requalification ");
	try{	
	
		fncMenu_Ajax_Link_Click_By_PassingAjaxPath("Requalification_Ajax");
		fnsLoading_Progressing_wait(2);
		
		fncNavigation_Verify_Application_Navigated_To_ViewScreen("'Requalification' Menu Ajax", "Requalification");
		
		String View_Exists = fncVerify_View_Display_Open_and_Delete_it(2, View_Name_Edited, "View_Delete_Link", "View_Remove_Link");
		
		if( (View_Exists.contains("View Not Exists")) ){
			fncVerify_View_Display_Open_and_Delete_it(2, View_Name, "View_Delete_Link", "View_Remove_Link");
		}
				
		fnsGet_Element_Enabled("CreateNewView_Link");
		fnsWait_and_Click("CreateNewView_Link");
		fnsLoading_Progressing_wait(2);
				
		fnsGet_Element_Enabled("CreateView_Step1_ViewName");
		fnsLoading_Progressing_wait(1);
		
		fnsWait_and_Type("CreateView_Step1_ViewName", View_Name);
					
		fnsGet_Element_Enabled("CreateView_CreateView_Bttn");
		fnsWait_and_Click("CreateView_CreateView_Bttn");
				
		fns_Verify_Success_message_coming_OR_Error_message_Coming(true, 60,  "A new view has been added to your list", 25);
		fncNavigation_Verify_Application_Navigated_To_ViewScreen("'CreateView' button", "Requalification");
		
		
		
		fnsWait_Click_on_First_Visible_Element_if_More_than_One_Coming("'"+Certificate_Number+"' link", "//a[text()='"+Certificate_Number+"']");
		fnsLoading_Progressing_wait(3);
		fnsVerifyScreenNavigation_afterClickingOnElement("'"+Certificate_Number+"' link", "'Certification Status' screen", OR_New_NSFOnline.getProperty("Back_Bttn"));
		fnsGet_Element_Enabled("Back_Bttn");
		fnsWait_and_Click("Back_Bttn");
		fnsLoading_Progressing_wait(2);
		
		fncNavigation_Verify_Application_Navigated_To_ViewScreen("'Back' button", "Requalification");
		
		
		
		fnsGet_Element_Enabled("Edit_View_Link_2");
		fnsWait_and_Click("Edit_View_Link_2");
		fnsLoading_Progressing_wait(2);
		
		fnsGet_Element_Enabled("CreateView_Step1_ViewName");
		fnsLoading_Progressing_wait(3);
		fnsWait_and_Clear("CreateView_Step1_ViewName");
		Thread.sleep(500);
		
		fnsWait_and_Type("CreateView_Step1_ViewName", View_Name_Edited);
		Thread.sleep(3000);
				
		fnsGet_Element_Enabled("CreateView_CreateView_Bttn");
		fnsWait_and_Click("CreateView_CreateView_Bttn");
	
		fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  60, "View has been updated successfully", 25);
		
		fncNavigation_Verify_Application_Navigated_To_ViewScreen("'UpdateView' button", "Requalification");
		
	
		
		fncVerify_View_Display_Open_and_Delete_it(2, View_Name_Edited, "View_Delete_Link", "View_Remove_Link");
		
		
		
		fnsGet_Element_Enabled("View_AdvanceSearch_Bttn");
		fnsLoading_Progressing_wait(1);
		fnsWait_and_Click("View_AdvanceSearch_Bttn");
		fnsLoading_Progressing_wait(2);
						
		fnsWait_Click_or_Type_By_LabelName("AdvanceSearch_Searchfields_Top_Div", "Standard", Standard);
		fnsWait_Click_or_Type_By_LabelName("AdvanceSearch_Searchfields_Top_Div", "Certificate Number", Certificate_Number);		
		
		fnsWait_and_Click("Requalification_AdvanceSearch_Search_Bttn");
		fnsLoading_Progressing_wait(2);
		fnsGet_Element_Enabled("Requalification_AdvanceSearch_Result_Table");
		TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element(OR_New_NSFOnline.getProperty("Requalification_AdvanceSearch_Result_Table"));
		String TextFetch=fnsGet_Field_TEXT("Requalification_AdvanceSearch_Result_Table").toLowerCase().trim();
						
		try{
			assert TextFetch.contains(Standard.toLowerCase().trim()):"FAILED ==  <"+Standard+"> NOT displayed into the Advance Search results Table>, Please refer Screen shot >> Standard_Not_Coming"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED ==  Successfully Verify that  <"+Standard+"> displayed into the Advance search result.");
		}catch(Throwable t){
			fnsTake_Screen_Shot("Standard_Not_Coming");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		
		try{
			assert TextFetch.contains(Certificate_Number.toLowerCase().trim()):"FAILED == <"+Certificate_Number+"> Certificate Number is NOT displayed into the Advance Search results Table>, Please refer Screen shot >> CertificateNumber_Not_Coming"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED ==  Successfully Verify that  <"+Certificate_Number+"> Certificate Number is displayed into the Advance search result.");
		}catch(Throwable t){
			fnsTake_Screen_Shot("CertificateNumber_Not_Coming");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
							
		
		
	
		
	}catch(Throwable t){
		isTestCasePass = false;
		fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
	
	
}










//############################################################################################################################################################
//#######################################################  Configuration Method  #############################################################################
@AfterTest
public void reportTestResult() throws Throwable {
	RunningClassName = 	this.getClass().getSimpleName();
	TestSuiteBase_MonitorPlan.fns_ReportTestResult_atClassLevel(New_NSFOnline_Suitexls,  (RunningClassName) , isTestCasePass);
}


@AfterTest
public void QuitBrowser(){
	TestSuiteBase_MonitorPlan.MoveMouseCursorToTaskBar();
	try{
		driver.quit();
		Thread.sleep(3000);
	}catch(Throwable t){
		//nothing to do
	}
}

}