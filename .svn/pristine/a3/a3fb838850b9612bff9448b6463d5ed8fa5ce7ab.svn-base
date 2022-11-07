package nsf.ecap.New_NSFOnline_Suite;

import java.util.List;

import nsf.ecap.MonitorPlan_Suite.TestSuiteBase_MonitorPlan;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;


public class BS_12_BkAdmin_Mgt_Reports extends TestSuiteBase_New_NSFOnline {

public boolean Browser_open = false;

@Parameters({ "className" })
@BeforeTest
public void checkTestSkip(String className) throws Exception {
	if (className.isEmpty()) {
		className = this.getClass().getSimpleName();
	}
	IsBrowserPresentAlready =false;
	fnsCheckClassLevelTestSkip(className);
}




@Test( priority = 1)
public void BK_Verify_Management_Reports__BurgerKingAuditResultsReport() throws Throwable{
	fnsApps_Report_Logs("################################## [BS-12] BK Admin - Management Reports");
	fnsApps_Report_Logs("=========================================================================================================================================");
	fnsApps_Report_Logs("################### Test Case ::1 BK_Verify_Management_Reports__BurgerKingAuditResultsReport ");
	try{
		fnsBrowserLaunchAndLogin(Login_UserName, Login_Password);
		fnsSwitchAcoount_MultiAccess(this.getClass().getSimpleName(), "SwitchAccount_DD");	
		fnsLoading_Progressing_wait(5);
		fncSub_Menu_Ajax_Link_Click_By_PassingAjaxPath("Reports_Ajax", "Reports_SubMenu_MgmtReports_Ajax");
		
				
		for(int wait=0; wait<=((NewNsfOnline_Element_Max_Wait_Time)); wait++){
			if(driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("Reports_MgmtReports_Screen"))).size()>0){
				fnsApps_Report_Logs("PASSED == Successfully opened 'Management Reports' screen.");
				fnsLoading_Progressing_wait(2);
				break;
			}else{
				Thread.sleep(1000);
			}
			if(wait==((NewNsfOnline_Element_Max_Wait_Time))){
				fnsTake_Screen_Shot("MgmtReportScreen_Open_Fail");
				throw new Exception("FAILED == 'Management Reports' screen is not getting open, After "+((NewNsfOnline_Element_Max_Wait_Time))+" seconds wait, please refer screen shot >> MgmtReportScreen_Open_Fail"+fnsScreenShot_Date_format());
			}
		}
		
		
		fnsVerifyScreenNavigation_afterClickingOnElement("'Mgmt Reports' Ajax link", "Default 'All Reports' Tab", OR_New_NSFOnline.getProperty("Reports_MgmtReports_All_Reports_Tab_ByDefault_Opened"));
		
		fnsGet_Element_Enabled("View_Result_Table");
		String All_Reports_Table_Text = fnsGet_OR_New_NSFOnline_ObjectX("View_Result_Table").getText().toLowerCase().trim();
		try{
			assert  !(All_Reports_Table_Text.contains("no records found")):"FAILED == Data are not coming under 'All Reports' Tab, please refer the screen shot >> Data_Not_Coming"+fnsScreenShot_Date_format();
		}catch(Throwable t){
			fnsTake_Screen_Shot("Data_Not_Coming");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		
		
		fnsWait_Click_on_First_Visible_Element_if_More_than_One_Coming("'"+fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Report_1_Name")+"' report link", "//a[text()='"+fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Report_1_Name")+"']");
		fnsLoading_Progressing_wait(5);
				
		fnsWait_Click_or_Type_By_LabelName("Search_Fields_Table_TopDiv", "From Date", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Report_1_FromDate"));
		
		fnsWait_Click_or_Type_By_LabelName("Search_Fields_Table_TopDiv", "To Date", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Report_1_ToDate"));
				
		fnsDD_value_Select_TagOPTION_DDTypeSelect("Reports_MgmtReports_Country_DD", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Report_1_Country"), (10));
		fnsLoading_Progressing_wait(1);
				
		fnsDownload_File("Reports__Generate_Report_Bttn");	
		
		Browser_open = true;	
	}catch(Throwable t){
		isTestCasePass = false;
		Browser_open = false;
		driver.quit();
		fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
		
}



@Test( priority = 2)
public void BK_Verify_Management_Reports__Top10NonComplianceReport() throws Throwable{
	fnsApps_Report_Logs("=========================================================================================================================================");
	fnsApps_Report_Logs("################### Test Case ::2 BK_Verify_Management_Reports__Top10NonComplianceReport ");
	try{	
		if(Browser_open == false){
			fnsBrowserLaunchAndLogin(Login_UserName, Login_Password);
			fnsSwitchAcoount_MultiAccess(this.getClass().getSimpleName(), "SwitchAccount_DD");
		}
		fncSub_Menu_Ajax_Link_Click_By_PassingAjaxPath("Reports_Ajax", "Reports_SubMenu_MgmtReports_Ajax");
		
		
		for(int wait=0; wait<=((NewNsfOnline_Element_Max_Wait_Time)); wait++){
			if(driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("Reports_MgmtReports_Screen"))).size()>0){
				fnsApps_Report_Logs("PASSED == Successfully opened 'Management Reports' screen.");
				fnsLoading_Progressing_wait(2);
				break;
			}else{
				Thread.sleep(1000);
			}
			if(wait==((NewNsfOnline_Element_Max_Wait_Time))){
				fnsTake_Screen_Shot("MgmtReportScreen_Open_Fail");
				throw new Exception("FAILED == 'Management Reports' screen is not getting open, After "+((NewNsfOnline_Element_Max_Wait_Time))+" seconds wait, please refer screen shot >> MgmtReportScreen_Open_Fail"+fnsScreenShot_Date_format());
			}
		}
		
		
		fnsVerifyScreenNavigation_afterClickingOnElement("'Mgmt Reports' Ajax link", "Default 'All Reports' Tab", OR_New_NSFOnline.getProperty("Reports_MgmtReports_All_Reports_Tab_ByDefault_Opened"));
		
		fnsGet_Element_Enabled("View_Result_Table");
		String All_Reports_Table_Text = fnsGet_OR_New_NSFOnline_ObjectX("View_Result_Table").getText().toLowerCase().trim();
		try{
			assert  !(All_Reports_Table_Text.contains("no records found")):"FAILED == Data are not coming under 'All Reports' Tab, please refer the screen shot >> Data_Not_Coming"+fnsScreenShot_Date_format();
		}catch(Throwable t){
			fnsTake_Screen_Shot("Data_Not_Coming");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		
		
		fnsWait_Click_on_First_Visible_Element_if_More_than_One_Coming("'"+fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Report_2_Name")+"' report link", "//a[text()='"+fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Report_2_Name")+"']");
		fnsLoading_Progressing_wait(5);
				
		fnsWait_Click_or_Type_By_LabelName("Search_Fields_Table_TopDiv", "From Date", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Report_2_FromDate"));
		
		fnsWait_Click_or_Type_By_LabelName("Search_Fields_Table_TopDiv", "To Date", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Report_2_ToDate"));
				
		fnsDD_value_Select_TagOPTION_DDTypeSelect("Reports_MgmtReports_AUDIT_TYPE_DD", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Report_2_AuditType"), (10));
		fnsLoading_Progressing_wait(2);
		
		fnsGet_Element_Enabled("Reports_MgmtReports_Search_bttn");
		fnsWait_and_Click("Reports_MgmtReports_Search_bttn");
		fnsLoading_Progressing_wait(5);
		
		
		try{
			fnsGet_Element_Enabled_By_Passing_Wait_and_Screenshot_Condition(60, false, "View_Result_Table");
		}catch(Throwable t){
			fnsTake_Screen_Shot("Records_Not_Coming");
			throw new Exception("FAILED == Records are NOT coming, after clicking on Search button, please refer the screen shot >> Records_Not_Coming"+fnsScreenShot_Date_format());
		}
		
		TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element(OR_New_NSFOnline.getProperty("View_Result_Table"));
		List<WebElement> Table_Rows = fnsGet_OR_New_NSFOnline_ObjectX("View_Result_Table").findElements(By.tagName("tr"));
		try{
			assert ( Table_Rows.size()>=1):"FAILED == Records are NOT coming, after clicking on Search button, please refer the screen shot >> Records_Not_Coming"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED == Records are displayed for report '"+fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Report_2_Name")+"'.");
		}catch(Throwable t){
			fnsTake_Screen_Shot("Records_Not_Coming");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		
		
		
		String TextFetch_for_NoRecords_Verification=fnsGet_Field_TEXT("Reports_MgmtReports_Result_TopDiv").toLowerCase().trim();
		
		try{
			assert !(TextFetch_for_NoRecords_Verification.contains("no records found")):"FAILED == Records are NOT coming for report '"+fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Report_2_Name")+"', please refer the screen shot >> No_Records_Found"+fnsScreenShot_Date_format();
		}catch(Throwable t){
			fnsTake_Screen_Shot("No_Records_Found");
			throw new Exception(Throwables.getStackTraceAsString(t));	
		}
					
	}catch(Throwable t){
		isTestCasePass = false;
		fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
		throw new Exception(Throwables.getStackTraceAsString(t));
	}	
	
}





//#######################################################  Configuration Method  ################################################################
@AfterTest
public void reportTestResult() throws Throwable {
	TestSuiteBase_MonitorPlan.fns_ReportTestResult_atClassLevel(New_NSFOnline_Suitexls,  (this.getClass().getSimpleName()), isTestCasePass );
}


@AfterTest
public void QuitBrowser(){
	driver.quit();
}

}