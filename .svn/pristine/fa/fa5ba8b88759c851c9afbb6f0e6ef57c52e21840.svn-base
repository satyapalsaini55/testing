package nsf.ecap.Listings_Suite;

import java.sql.Connection;

import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;


public class BS_02_CreateProgram extends TestSuiteBase_Listings {



@Parameters({ "className" })
@BeforeTest
public void checkTestSkip(String className) throws Exception {
	if (className.isEmpty()) {
		className = this.getClass().getSimpleName();
	}
	IsBrowserPresentAlready =false;
	fnsCheckClassLevelTestSkip(className);
}


@Test( priority = 0 )
public void Execute_Data_Delete_Queries() throws Throwable{
	fnsApps_Report_Logs("################### Test Case ::0 Execute_Data_Delete_Queries ");
	Connection DBconnection = null;
	try{		
		DBconnection = fnpGetDBConnection(); 
		String Query_1 = "DELETE FROM nucleus.programs where create_user='TESTSCRIPTUSER'";
		
		fnsDB_Fetch_or_Update_LoginTC_Query(Query_1, DBconnection);
		DBconnection.close();
	}catch(Throwable t){
		isTestCasePass = false;
		throw new Exception(Throwables.getStackTraceAsString(t));
	}finally {
		fnsApps_Report_Logs("=========================================================================================================================================");
		if( !(DBconnection==null) ){
			DBconnection.close();
		}
	}
}


@Test( priority = 1, dependsOnMethods={"Execute_Data_Delete_Queries"})
public void VerifyLoginIntoIpulse_and_SuccessfullySwitchToListingsApplication() throws Throwable{
	try{
		fnsApps_Report_Logs("################################## [BS-02]  Verify Programs");
		if (!IsBrowserPresentAlready) {
			IsBrowserPresentAlready = fnsBrowserLaunch_LoginIntoiPulse_thenClickOnListingApplicationMenu_AndSwitchTo_ListingsApplication();
		}
	}catch(Throwable t){
		isTestCasePass = false;
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
	fnsApps_Report_Logs("=========================================================================================================================================");
}





@Test( priority = 2, dependsOnMethods={"VerifyLoginIntoIpulse_and_SuccessfullySwitchToListingsApplication"}, description="")
public void Create_and_Delete_Program() throws Throwable{
	fnsApps_Report_Logs("################### Test Case ::1 Create_and_Delete_Program ");
	try{	
		fnsGet_Element_Enabled("SideBarLink_SearchProgram_Link");
		fnsWait_and_Click("SideBarLink_SearchProgram_Link");
		fnsLoading_Progressing_wait(1);		
		fnsNavigation_Verify_Application_Navigation_fromHeaderText("'Search Programs' link", "Search Programs");
		
		
		fnsGet_Element_Enabled("SearchProgram_CreateProgram_Button");
		fnsWait_and_Click("SearchProgram_CreateProgram_Button");
		fnsLoading_Progressing_wait(1);		
		fnsNavigation_Verify_Application_Navigation_fromHeaderText("'Create Program' button", "Create Program");
		
		String ProgCode = fnsTime_Return_DemandedFormatCalendar("ddHHmmss", 0, 0, 0, 0, 0);
		fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(true, "input", "Prog Code", ProgCode);
		
		fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(true, "input", "Name", "Automation-Program");
		
		fnsDD_Value_select_by_PassingDDLabelName("Prog Type", "NSF");
		
		fnsGet_Element_Enabled("CreateProgram_SAVE_Button");
		fnsWait_and_Click("CreateProgram_SAVE_Button");
		fnsLoading_Progressing_wait(2);	
		
		for(int i=0; i<=120; i++){
			if(driver.findElements(By.xpath(OR_Listings.getProperty("ViewProgram_Delete_Button"))).size()>0){
				fnsApps_Report_Logs("PASSED == Program is successfully created.");
				break;
			}else{
				Thread.sleep(1000);
			}
			if(i==120){
				fnsTake_Screen_Shot("ProgramCreate_Fail");
				throw new Exception("FAILED == Program is not getting created, after clicking on Delete button is not coming, Please refer the screenshot >> ProgramCreate_Fail"+fnsScreenShot_Date_format());
			}
		}
		
		
		fnsGet_Element_Enabled("ViewProgram_Delete_Button");
		fnsWait_and_Click("ViewProgram_Delete_Button");
		fnsLoading_Progressing_wait(2);	
		
		fnsVerify_PopupOpened_byTitle("'Delete' button");
		
		fnsGet_Element_Enabled("ViewProgram_DeletePopup_Delete_Button");
		fnsWait_and_Click("ViewProgram_DeletePopup_Delete_Button");
		fnsLoading_Progressing_wait(2);	
		
		for(int i=0; i<=120; i++){
			if(driver.findElements(By.xpath(OR_Listings.getProperty("SearchProgram_ProgCode_Filter"))).size()>0){
				fnsApps_Report_Logs("PASSED == after clicking on Delete button from delete popup, the application is successfully navigated to search program screen..");
				break;
			}else{
				Thread.sleep(1000);
			}
			if(i==120){
				fnsTake_Screen_Shot("DeleteNavigation_Fail");
				throw new Exception("FAILED == after clicking on Delete button from delete popup, the application is not getting navigated to search program screen, Please refer the screenshot >> DeleteNavigation_Fail"+fnsScreenShot_Date_format());
			}
		}
		
		fnsWait_and_Type("SearchProgram_ProgCode_Filter", ProgCode);
		Thread.sleep(2000);
		String ResultGrid_Text = fnsGet_Field_TEXT("Listings_ResultGrid_DataTable").toLowerCase();
		
		try{
			assert ! (ResultGrid_Text.contains(ProgCode.toLowerCase()) ): "FAILED == after clicking on Delete button from delete popup,  The Deleted Program '"+ProgCode+"' is still displayed on the search program screen, please refer the screen shot >> ProgramNotDeleted"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED == Program '"+ProgCode+"' is successfully deleted.");
		}catch(Throwable t){
			fnsTake_Screen_Shot("ProgramNotDeleted");
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
	TestSuiteBase_MonitorPlan.fns_ReportTestResult_atClassLevel(Listings_Suitexls,  (this.getClass().getSimpleName()), isTestCasePass );
}


@AfterTest
public void QuitBrowser(){
	try{
		TestSuiteBase_MonitorPlan.MoveMouseCursorToTaskBar();
		driver.quit();
	}catch(Throwable t){
		//nothing to do
	}
}

}