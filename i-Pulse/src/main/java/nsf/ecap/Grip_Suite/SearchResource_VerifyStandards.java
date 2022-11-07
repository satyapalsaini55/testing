package nsf.ecap.Grip_Suite;

import nsf.ecap.IssueMgt_Suite.TestSuiteBase_IM;
import nsf.ecap.MonitorPlan_Suite.TestSuiteBase_MonitorPlan;
import nsf.ecap.util.Xls_Reader_Till_2007;

import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;

public class SearchResource_VerifyStandards extends TestSuiteBase_Grip {


	//################################################ Class Variables Starts #####################################################################
	public Integer Count = null;
	public Integer Before_DataAdded = null; ;
	public Integer NewlyAddedRowNo = null;
	public Integer MatchingText_RowXpath_Count = null;
	public Integer SecondayAddress_RowCount = null;
	public Integer Apps_SearchResult_Count = null;
	public Integer Excel_SearchResult_Count = null;
	
	public String ResourceName = null;
	public String Row_Xpath = null;
	public String Comments_Free_Text = "AutomationTest >> SearchResource-VerifyStandards [BS-05], Date/Time=" + fnIssueCreationText_Date_format();
	public String PdfFile_Location =System.getProperty("user.home")+"\\Downloads"; 
	public String SearchResult_Export_XlsFile_Location =System.getProperty("user.home")+"\\Downloads\\stdSearch.xls"; 
	public Xls_Reader_Till_2007 Resource_SearchResult_Xls = null;
	public String ResourceID = "0711";
	public String Email_Success_Time = "30-Jul-2015 03:01:22 AM";
	public String Standards_Popup_LookUp_Value = null;

	public boolean Resource_WillSmith =true;
	
	


@Parameters({ "className" })
@BeforeTest
public void checkTestSkip(String className) throws Exception {
	
	if (className.isEmpty()) {
		className = this.getClass().getSimpleName();
	}
	IsBrowserPresentAlready = false;
	fnsCheckClassLevelTestSkip(className);
}





@Test( priority = 0)
public void Launch_Browser_and_Successfully_Login_into_the_Application() throws Throwable{
	fnsApps_Report_Logs("################################## [BS - 05] Search Resource - Verify Standards");
	if (!IsBrowserPresentAlready) {
		IsBrowserPresentAlready = fnsLaunchBrowserAndLogin();
		fnsApps_Report_Logs("Browser is launched and Successfully login into Application");	
	}
	fnsApps_Report_Logs("=========================================================================================================================================");
}







@Test( priority = 1, dependsOnMethods={"Launch_Browser_and_Successfully_Login_into_the_Application"}, description="[Step 1. >> Menu > Search Resource > Enter Resource ID > Click on Search. Expected == <It should retrieve the resources>]. [Step 2. >> Click on Resource ID.   Expected == <It should open View resource screen.>] [Step 3. >>Click on Edit button.]")
public void Verify_SearchResource_Display_on_SearchResultScreen_and_Click_On_ResourceID() throws Throwable{
	fnsApps_Report_Logs("################### Test Case ::::::1 Verify_SearchResource_Display_on_SearchResultScreen_and_Click_On_ResourceID ");
	
	try{
	
		ResourceName = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "ResourceName");
		
		
		fncMenu_Ajax_Link_Click_By_PassingAjaxPath("SearchResource_Ajax_Link");
		fnsGet_Element_Enabled("SearchResource_Search_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
				
		fnsGet_Element_Enabled("SearchResource_Name_InputField");
		fnsWait_and_Type("SearchResource_Name_InputField", ResourceName);
				
		fnsGet_Element_Enabled("SearchResource_Search_Bttn");
		fnsWait_and_Click("SearchResource_Search_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(3);	
			
		fnsGet_Element_Enabled("SearchResource_SearchResult_TableHeader");
		String SearchResource_SearchResult_TableHeader_FirstRowXpath = OR_Grip.getProperty("SearchResource_SearchResult_TableHeader")+"/tr[1]";
		
		String First_Row_Text = TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(SearchResource_SearchResult_TableHeader_FirstRowXpath).toLowerCase().trim();
		Integer ResourceID_ColumnNumber = fncFind_HeaderColumnNo_ByColumnName("SearchResource_SearchResult_HeadingFirstRowXpath", "Resource ID");
		String SearchResult_ResourceID_FirstCellXpath = SearchResource_SearchResult_TableHeader_FirstRowXpath+"/td["+ResourceID_ColumnNumber+"]";
		ResourceID = TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(SearchResult_ResourceID_FirstCellXpath).trim();
		
		TestSuiteBase_MonitorPlan.fnsTable_ClickOn_LINK_ByMatchingText(ResourceID);
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		fnsGet_Element_Enabled("IPulse_Footer");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
		
		fnsGet_Element_Enabled("EditResource_Edit_Bttn");
		fnsWait_and_Click("EditResource_Edit_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(3);
		
	}catch(Throwable t){
		isTestCasePass = false;
		fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
		throw new Exception(Throwables.getStackTraceAsString(t));		}	
		
}





@Test(dependsOnMethods={"Verify_SearchResource_Display_on_SearchResultScreen_and_Click_On_ResourceID"}, priority = 2, description="Step1. [Go to Qualifications ->Click on Add for Standards and click save icon.   Expected<Data Updated Successfully message should come.>]    Step2. [Standards> Delete the newly created record.   Expected<Data deleted successfully! message should come and row should be deleted.>]" )
public void EditResource_QualificationsTab_StandardsSection_Add_and_Delete_Records_and_Verify_AddDeleteWorkingFine() throws Throwable{
	fnsApps_Report_Logs("=========================================================================================================================================");
	fnsApps_Report_Logs("######### Test Case ::::2 EditResource_QualificationsTab_StandardsSection_Add_and_Delete_Records_and_Verify_AddDeleteWorkingFine");
	try{
		fncClicking_on_TAB("Qualifications", "EditResource_Qualifications_TAB", "EditResource_QualificationsTab_Standards_Add_Bttn");
		
		Standards_Popup_LookUp_Value = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Popup_LK_TextField").trim();
		Before_DataAdded = TestSuiteBase_MonitorPlan.WithOut_OR_Retrun_TotalRowCount_OfTable(OR_Grip.getProperty("EditResource_QualificationsTab_Standards_TableHeader"));
		String TableText = TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(OR_Grip.getProperty("EditResource_QualificationsTab_Standards_TableHeader")).toLowerCase();
		if (TableText.contains("no standards have been added")){
			Before_DataAdded = 0;
		}
		
		
	
		fnsGet_Element_Enabled("EditResource_QualificationsTab_Standards_Edit_ShowAllFilter_DD_Click");
		fnsWait_and_Click("EditResource_QualificationsTab_Standards_Edit_ShowAllFilter_DD_Click");
		
		fnsGet_Element_Enabled("EditResource_QualificationsTab_Standards_Edit_ShowAllFilter_DD_Filter");
		fnsGet_OR_Grip_ObjectX("EditResource_QualificationsTab_Standards_Edit_ShowAllFilter_DD_Filter").sendKeys(Standards_Popup_LookUp_Value);
		Thread.sleep(2000);
		
		String MatchingValueXpath = "//li[text()='"+Standards_Popup_LookUp_Value+"']";
		
		if(driver.findElements(By.xpath(MatchingValueXpath)).size()>0){
			TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(MatchingValueXpath);
			TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(MatchingValueXpath).click();
			TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
			Thread.sleep(500);
			
			fnsGet_Element_Enabled("EditResource_QualificationsTab_Standards_Test_Ac_Record_Delete_Bttn");
			fnsWait_and_Click("EditResource_QualificationsTab_Standards_Test_Ac_Record_Delete_Bttn");
			TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
			
			//Added on 15.6.2015
			fnsGet_Element_Enabled("EditResource_QualificationsTab_Standards_Test_Ac_Record_Delete_Comments");
			fnsWait_and_Type("EditResource_QualificationsTab_Standards_Test_Ac_Record_Delete_Comments", "Automation Test");
			
			
			
			fnsGet_Element_Enabled("EditResource_QualificationsTab_Standards_Delete_YES_Bttn");
			fnsWait_and_Click("EditResource_QualificationsTab_Standards_Delete_YES_Bttn");
			TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(3);
			//Thread.sleep(2000);
			fncVerify_DataDeleted_Successfully("IPulse_ValidationMessage_Div", "QualificationsTab: 'Standards' Section");
			
		}
		
		
		fnsGet_Element_Enabled("EditResource_QualificationsTab_Standards_Add_Bttn");
		fnsWait_and_Click("EditResource_QualificationsTab_Standards_Add_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		fncAssert_Contains(OR_Grip.getProperty("EditResource_QualificationsTab_Standards_Popup_Title"), "Add New Standard Qualification", "'Add New Standard Qualification' Popup is not getting opened");
		
		fnsGet_Element_Enabled("EditResource_QualificationsTab_Standards_Popup_LK_Bttn");
		fnsWait_and_Click("EditResource_QualificationsTab_Standards_Popup_LK_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		fnsGet_Element_Enabled("EditResource_QualificationsTab_Standards_Popup_LK_TextField");
		fnsWait_and_Type("EditResource_QualificationsTab_Standards_Popup_LK_TextField", Standards_Popup_LookUp_Value);
		TestSuiteBase_MonitorPlan.fnsSelect_lookup_FirstCheckBox_and_Return();
		
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(3);
		fnsGet_Element_Enabled("EditResource_QualificationsTab_Standards_Popup_Save_Bttn");
		fnsWait_and_Click("EditResource_QualificationsTab_Standards_Popup_Save_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(3);
		//TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(3);
	
		
		if(driver.findElements(By.xpath(OR_Grip.getProperty("IPulse_ValidationMessage_Div"))).size()>0){
			Thread.sleep(2000);
		}
		
		String DeleteButtonXpath = "";
		
		if(Resource_WillSmith){
			fncVerify_DataUpdated_Successfully("IPulse_ValidationMessage_Div", "QualificationsTab: 'Standards' Section");
			
			TestSuiteBase_MonitorPlan.WithOut_OR_fnDD_SelectValue_Through_Filter(OR_Grip.getProperty("EditResource_QualificationsTab_Standards_Edit_ShowAllFilter_DD_Click"), OR_Grip.getProperty("EditResource_QualificationsTab_Standards_Edit_ShowAllFilter_DD_Filter"), Standards_Popup_LookUp_Value);
			TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
			Thread.sleep(2000);
		}
		
		
			
		
		NewlyAddedRowNo = TestSuiteBase_MonitorPlan.WithOut_OR_fnsReturn_RowNumber_By_TableMatching_TEXT(OR_Grip.getProperty("EditResource_QualificationsTab_Standards_TableHeader"), OR_Grip.getProperty("EditResource_QualificationsTab_Standards_TableHeader_Data"), Standards_Popup_LookUp_Value);
		DeleteButtonXpath = OR_Grip.getProperty("EditResource_QualificationsTab_Standards_Delete_Bttn1")+(NewlyAddedRowNo-1)+OR_Grip.getProperty("EditResource_QualificationsTab_Standards_Delete_Bttn2");
			
		fnsGet_Element_Enabled("EditResource_QualificationsTab_Standards_TableHeader_Column");
		Thread.sleep(2000);
	
		TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(DeleteButtonXpath);
		TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(DeleteButtonXpath);
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		//Added on 15.6.2015
		fnsGet_Element_Enabled("EditResource_QualificationsTab_Standards_Test_Ac_Record_Delete_Comments");
		fnsWait_and_Type("EditResource_QualificationsTab_Standards_Test_Ac_Record_Delete_Comments", "Automation Test");
		
		fnsGet_Element_Enabled("EditResource_QualificationsTab_Standards_Delete_YES_Bttn");
		fnsWait_and_Click("EditResource_QualificationsTab_Standards_Delete_YES_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(3);
		//Thread.sleep(2000);
		fncVerify_DataDeleted_Successfully("IPulse_ValidationMessage_Div", "QualificationsTab: 'Standards' Section");
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