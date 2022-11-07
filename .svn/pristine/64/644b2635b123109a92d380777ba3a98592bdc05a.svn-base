//TrunktestSiteName=http://oraapp10.nsf.org:8070/trunkecap/index.jsp
package nsf.ecap.Grip_Suite;

import java.io.File;
import java.util.List;

import nsf.ecap.Client_Suite.TestSuiteBase_CLNT;
import nsf.ecap.IssueMgt_Suite.TestSuiteBase_IM;
import nsf.ecap.MonitorPlan_Suite.TestSuiteBase_MonitorPlan;
import nsf.ecap.util.Xls_Reader_Till_2007;

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

public class SearchResource extends TestSuiteBase_Grip {


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
	public String Comments_Free_Text = "AutomationTest >> Grip_SearchResource[BS-02], Date/Time=" + fnIssueCreationText_Date_format();
	public String PdfFile_Location =System.getProperty("user.home")+"\\Downloads"; 
	public static String SearchResult_Export_XlsFile_Location =System.getProperty("user.home")+"\\Downloads\\stdSearch.xls"; 
	public Xls_Reader_Till_2007 Resource_SearchResult_Xls = null;
	public String ResourceID = "0711";
	public String Email_Success_Time = "30-Jul-2015 03:01:22 AM";

	public boolean Resource_TonyCampos =true;
	public long AllFileSize_AfterFileSave ;
	


@Parameters({ "className" })
@BeforeTest
public void checkTestSkip(String className) throws Exception {
	
	if (className.isEmpty()) {
		className = this.getClass().getSimpleName();		}
	IsBrowserPresentAlready = false;
	fnsCheckClassLevelTestSkip(className);
}





@Test( priority = 0)
public void Launch_Browser_and_Successfully_Login_into_the_Application() throws Throwable{
	fnsApps_Report_Logs("################################## [BS - 02]  Search Resource");
	if (!IsBrowserPresentAlready) {
		IsBrowserPresentAlready = fnsLaunchBrowserAndLogin();
		fnsApps_Report_Logs("Browser is launched and Successfully login into Application");	
	}
	fnsApps_Report_Logs("=========================================================================================================================================");
}







@Test( priority = 1, dependsOnMethods={"Launch_Browser_and_Successfully_Login_into_the_Application"}, description="[Step 1. >> Menu > Search Resource > Click on Search. Expected == <It should retrieve the list of resources>]. [Step 2. >> Enter the search criteria for Type as :Contract] [Step 3. >>Click on Export link and select the option as 'XLS'.]")
public void Verify_SearchResultDisplay_on_SearchResourceScreen_and_in_ExportExcel_are_Same() throws Throwable{
	fnsApps_Report_Logs("################### Test Case ::::::1 Verify_SearchResultDisplay_on_SearchResourceScreen_and_in_ExportExcel_are_Same ");
	
	try{
	
		TestSuiteBase_MonitorPlan.fnsDelete_File(SearchResult_Export_XlsFile_Location);
		
		ResourceName = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Name");
		
				
		
		fncMenu_Ajax_Link_Click_By_PassingAjaxPath("SearchResource_Ajax_Link");
		fnsGet_Element_Enabled("SearchResource_Search_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		fnsGet_Element_Enabled("SearchResource_Profile_Type_DD_Contract");
		fnsWait_and_Click("SearchResource_Profile_Type_DD_Contract");
		
		fnsGet_Element_Enabled("SearchResource_Search_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		fnsWait_and_Click("SearchResource_Search_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(3);	
		
		//Thread.sleep(10000);//added as app is very slow
		
		String FileLocation = System.getProperty("user.home")+"\\Downloads";
		long AllFileSize_BeforeFileSave = FileUtils.sizeOfDirectory(new File(FileLocation));
		
		
		
		fnsGet_Element_Enabled("SearchResource_SearchResult_TableHeader");
		fnsGet_Element_Enabled("SearchResource_SearchResult_Pagination_ResultCount");
		
		String Apps_SearchResult_Count_String = fnsGet_Field_TEXT("SearchResource_SearchResult_Pagination_ResultCount").split("of")[1].trim();
		Apps_SearchResult_Count = Integer.parseInt(Apps_SearchResult_Count_String);
		
	//	Thread.sleep(10000);//added due to the slowness
		fnsGet_Element_Enabled("SearchResource_SearchResult_Export_Bttn");
		fnsWait_and_Click("SearchResource_SearchResult_Export_Bttn");
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
	    		throw new Exception("FAILED == "+t.getMessage()+", Please refer screenshot >> BrowserFileSaveFail"+fnsScreenShot_Date_format());		}
		
		
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
			fnsApps_Report_Logs("PASSED == Successfully download file through Browser Dialog Popup, after clicking on 'Export' Button of Search Result.");	 	  
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
		
		Resource_SearchResult_Xls = new Xls_Reader_Till_2007(SearchResult_Export_XlsFile_Location);
		
		Excel_SearchResult_Count = Resource_SearchResult_Xls.getRowCount("stdSearchExportC");
		Excel_SearchResult_Count = Excel_SearchResult_Count-1;
		
		try{
			assert Apps_SearchResult_Count.equals(Excel_SearchResult_Count):"FAILED == Search results count<"+Apps_SearchResult_Count+"> is not matched with Export Excel count<"+Excel_SearchResult_Count+">, Please refer screenshot >> Export_SearchResult_NotMatch"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED == Successfully Verify that Search results count<"+Apps_SearchResult_Count+"> and Export Excel count<"+Excel_SearchResult_Count+"> are same.");
		}catch(Throwable t){
			isTestCasePass = false;
			fnsTake_Screen_Shot("Export_SearchResult_NotMatch");
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));		}
		
	}catch(Throwable t){
		isTestCasePass = false;
		fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
		throw new Exception(Throwables.getStackTraceAsString(t));		}	
		
}





@Test( priority = 2, dependsOnMethods={"Verify_SearchResultDisplay_on_SearchResourceScreen_and_in_ExportExcel_are_Same"}, description="[Step 1. >> Mention the name of the employee (Tony Campos) in the Profile section. Click on the Save criteria link and enter the name for the Criteria as 'Test automation'. Click on the Search button.>]. [Step 2. >> Delete the Save criteria in the dropdown using the red cross button. Click on the OK button for delete dialog.]")
public void Verify_SearchCriteria_Successfully_Save_and_Display_CorrectSearchResults_and_DeletedSuccessfully() throws Throwable{
	fnsApps_Report_Logs("=========================================================================================================================================");
	fnsApps_Report_Logs("################### Test Case ::::::2 Verify_SearchCriteria_Successfully_Save_and_Display_CorrectSearchResults_and_DeletedSuccessfully ");
	try{
		fnsGet_Element_Enabled("SearchResource_SearchResult_Clear_Link");
		fnsWait_and_Click("SearchResource_SearchResult_Clear_Link");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(5);
		for(int i=0; i<=Long.parseLong(CONFIG.getProperty("ElementWaitTime")); i++){
			try{
				String Apps_SearchResult_Count_String = fnsGet_Field_TEXT("SearchResource_SearchResult_Pagination_ResultCount").split("of")[1].trim();
				Apps_SearchResult_Count = Integer.parseInt(Apps_SearchResult_Count_String);
				if(Apps_SearchResult_Count<3){
					isTestCasePass = true;
					break;
				}else{
					Thread.sleep(1000);
				}
			}catch(Throwable t){
				if(i==(Long.parseLong(CONFIG.getProperty("ElementWaitTime"))) ){
					fnsTake_Screen_Shot("SearchFilters_NOT_Clear");
					throw new Exception ("FAILED == After clicking on Clear link, the search filter is not getting clear after seconds wait, please refer the screenshot >> SearchFilters_NOT_Clear"+fnsScreenShot_Date_format()+" -- Getting exception >> "+Throwables.getStackTraceAsString(t));
				}else{
					Thread.sleep(1000);
				}
			}
		
		}
		
		fnsGet_Element_Enabled("SearchResource_Name_InputField");
		fnsWait_and_Type("SearchResource_Name_InputField", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Name"));
		
		
		Thread.sleep(2000);
		fnsGet_Element_Enabled("SearchResource_SaveCriteria_Link");
		fnsWait_and_Click("SearchResource_SaveCriteria_Link");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
	
		
		fncAssert_Contains(OR_Grip.getProperty("SearchResource_SaveCriteria_Popup_Title"), "Save Criteria", "'Save Criteria' Popup is not getting opened");
		
		String SaveCritera_Name = "Test Automation";
		
		fnsGet_Element_Enabled("SearchResource_SaveCriteria_Popup_Text");
		fnsWait_and_Type("SearchResource_SaveCriteria_Popup_Text", SaveCritera_Name);
		
		
		fnsGet_Element_Enabled("SearchResource_SaveCriteria_Popup_Save_Bttn");
		fnsWait_and_Click("SearchResource_SaveCriteria_Popup_Save_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		fncVerify_DataUpdated_Successfully("IPulse_ValidationMessage_Div", "Search Resource : Save Criteria");
		
		
		
		fnsGet_Element_Enabled("SearchResource_SearchResult_Clear_Link");
		fnsWait_and_Click("SearchResource_SearchResult_Clear_Link");
		Thread.sleep(8000);
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);	
		
		fnsGet_Element_Enabled("SearchResource_SelectCriteriaList_DD_Click");
		fnsWait_and_Click("SearchResource_SelectCriteriaList_DD_Click");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		String SaveCiteria_Name_Xpath = "//td[text()='"+SaveCritera_Name+"']";
		
		TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(SaveCiteria_Name_Xpath);
		TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(SaveCiteria_Name_Xpath);
		//Thread.sleep(2000);
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);	
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
		
		fnsGet_Element_Enabled("SearchResource_Search_Bttn");
		fnsWait_and_Click("SearchResource_Search_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(3);	
		
		fnsGet_Element_Enabled("SearchResource_SearchResult_TableHeader");
		String SearchResource_SearchResult_TableHeader_FirstRowXpath = OR_Grip.getProperty("SearchResource_SearchResult_TableHeader")+"/tr[1]";
		
		String First_Row_Text = TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(SearchResource_SearchResult_TableHeader_FirstRowXpath).toLowerCase().trim();
		Integer ResourceID_ColumnNumber = fncFind_HeaderColumnNo_ByColumnName("SearchResource_SearchResult_HeadingFirstRowXpath", "Resource ID");
		String SearchResult_ResourceID_FirstCellXpath = SearchResource_SearchResult_TableHeader_FirstRowXpath+"/td["+ResourceID_ColumnNumber+"]";
		ResourceID = TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(SearchResult_ResourceID_FirstCellXpath).trim();
		
		try{
			assert First_Row_Text.contains(fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Name").toLowerCase().trim()):"FAILED == Incorrect search results are coming through 'SAVE CRITERIA' functionality, Please refer screenshot >> SaveCriteriaSearchFail"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED == Correct search results are coming through 'SAVE CRITERIA' functionality.");
		}catch(Throwable t){
			isTestCasePass = false;
			fnsTake_Screen_Shot("SaveCriteriaSearchFail");
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));		}
		
		
		String SearchResource_SelectCriteriaList_DD_Click = "//label[contains(text(), '"+SaveCritera_Name+"')]";
		TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(SearchResource_SelectCriteriaList_DD_Click);
		TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(SearchResource_SelectCriteriaList_DD_Click);
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
	
		
		try{
			int RowCount = 1;
			WebElement stdtable=fnsGet_OR_Grip_ObjectX("SearchResource_SaveCriteria_DropDown_Div");
			List<WebElement> no_of_rows_in_list = stdtable.findElements(By.tagName("tr"));
			Count = 0;
			for(WebElement countrows:no_of_rows_in_list){
				String RowText=countrows.getText().toLowerCase().trim();
				if(RowText.contains(SaveCritera_Name.toLowerCase().trim())){
					Count = RowCount;
					Count = Count-1;
					break;
				}
				if(RowCount==no_of_rows_in_list.size() && !(RowText.contains(SaveCritera_Name.toLowerCase().trim())) ){
					fnsTake_Screen_Shot("SaveCriteria_NotInList");
					throw new Exception("FAILED == 'Save Criteria' is not found in the drop down list, please refer screen shot >> SaveCriteria_NotInList"+fnsScreenShot_Date_format());
				}
				RowCount++;
			}
			fnsApps_Report_Logs("PASSED == Successfully verified that 'Save Criteria' is found in the drop down list.");
			System.out.println("RowCount = " + RowCount  +"Count = " +Count);
		}catch(Throwable t){
			isTestCasePass = false;
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));		}
		
		
		
		
		
		String SaveCiteria_Delete_Bttn_Xpath="(//*[@class='ui-commandlink ui-widget deleteic'])["+Count+"]";
		
		TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(SaveCiteria_Delete_Bttn_Xpath);
		TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(SaveCiteria_Delete_Bttn_Xpath);
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);	
			
		fnsGet_Element_Enabled("SearchResource_SelectCriteria_Delete_ConfirmeDelete_OK_Bttn");
		fnsWait_and_Click("SearchResource_SelectCriteria_Delete_ConfirmeDelete_OK_Bttn");	
		
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(5);
		for(int i=0; i<Long.parseLong(CONFIG.getProperty("ElementWaitTime")); i++){
			if(TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(OR_Grip.getProperty("SearchResource_SelectCriteria_Delete_ConfirmeDelete_OK_Bttn")).isDisplayed()){
				Thread.sleep(1000);
			}else{
				Thread.sleep(1000);
				break;
			}
		}
		
		
		try{
			if(driver.findElements(By.xpath(OR_Grip.getProperty("SearchResource_SaveCriteria_DropDown_Div"))).size()>0){
				WebElement stdtable=fnsGet_OR_Grip_ObjectX("SearchResource_SaveCriteria_DropDown_Div");
				List<WebElement> no_of_rows_in_list_AfterDelete = stdtable.findElements(By.tagName("tr"));
				for(WebElement countrows:no_of_rows_in_list_AfterDelete){
					String RowText=countrows.getText().toLowerCase().trim();
					if(RowText.contains(SaveCritera_Name.toLowerCase().trim())){
						fnsTake_Screen_Shot("DeleteSaveCriteria_Found_InDDList");
						throw new Exception("FAILED == After deleting 'Save Criteria'. It is still exists into the drop down list, please refer screen shot >> DeleteSaveCriteria_Found_InDDList"+fnsScreenShot_Date_format());
					}
				}
				fnsApps_Report_Logs("PASSED == Successfully deleted 'Save Criteria' from drop down list.");
			}else{
				fnsApps_Report_Logs("PASSED == Successfully deleted 'Save Criteria' from drop down list.");
			}
		}catch(Throwable t){
			isTestCasePass = false;
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));		}
	}catch(Throwable t){
		isTestCasePass = false;
		throw new Exception(Throwables.getStackTraceAsString(t));	
	}
}





@Test( priority = 3, dependsOnMethods={"Verify_SearchCriteria_Successfully_Save_and_Display_CorrectSearchResults_and_DeletedSuccessfully"}, description="[Step 1. >> Select the resource by checking it.Click on Email link.Select the type and send email. Go to the correspondence tab and verify that email is generated on the particular date]")
public void Verify_Email_Functionality_on_SearchResourceScreen_WorkingFine_and_EmailSent_CrossCheck_in_CorrespondenceTAB () throws Throwable{
	fnsApps_Report_Logs("=========================================================================================================================================");
	fnsApps_Report_Logs("################### Test Case ::::::3 Verify_Email_Functionality_on_SearchResourceScreen_WorkingFine_and_EmailSent_CrossCheck_in_CorrespondenceTAB ");
	try{
	
	/*TestSuiteBase_MonitorPlan.fnsTable_ClickOn_LINK_ByMatchingText(ResourceID);
	TestSuiteBase_Aspirago.fnsLoading_Progressingwait(2);
	fnsGet_Element_Enabled("IPulse_Footer");
	TestSuiteBase_Aspirago.fnsLoading_Progressingwait(1);
	
	fnsGet_Element_Enabled("EditResource_Correspondence_TAB");
	fnsWait_and_Click("EditResource_Correspondence_TAB");
	
	Before_DataAdded = TestSuiteBase_MonitorPlan.WithOut_OR_Retrun_TotalRowCount_OfTable(OR_Grip.getProperty("EditResource_CorrespondenceTab_TableHeader_Data"));

	fnsGet_Element_Enabled("ViewResource_Back_Bttn");
	fnsWait_and_Click("ViewResource_Back_Bttn");
	TestSuiteBase_Aspirago.fnsLoading_Progressingwait(3);
	
	

	fnsGet_Element_Enabled("SearchResource_Name_InputField");
	fnsWait_and_Clear("SearchResource_Name_InputField");
	fnsWait_and_Type("SearchResource_Name_InputField", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Name"));
	
	

	fnsGet_Element_Enabled("SearchResource_Search_Bttn");
	fnsWait_and_Click("SearchResource_Search_Bttn");
	TestSuiteBase_Aspirago.fnsLoading_Progressingwait(3);	
	*/
	
	
	fnsGet_Element_Enabled("SearchResource_SearchResult_CheckBox");
	fnsWait_and_Click("SearchResource_SearchResult_CheckBox");
	
	fnsGet_Element_Enabled("SearchResource_SearchResult_Email_button");
	fnsWait_and_Click("SearchResource_SearchResult_Email_button");
	
	
	fncAssert_Contains(OR_Grip.getProperty("SearchResource_SearchResult_EmailPopup_Title"), "Email Template", "'Email Template' Popup is not getting opened");
	
	fnsDD_value_Select_By_MatchingText_DownKeyPress("SearchResource_SearchResult_EmailPopup_TepmlateType_DD_Click", "SearchResource_SearchResult_EmailPopup_TepmlateType_DD_Value", "li", "Welcome Letter");
	
	fnsGet_Element_Enabled("SearchResource_SearchResult_EmailPopup_TepmlateType_Continue_Bttn");
	fnsWait_and_Click("SearchResource_SearchResult_EmailPopup_TepmlateType_Continue_Bttn");
	
	fnsGet_Element_Enabled("SearchResource_SearchResult_EmailPopup_TepmlateType_SendEmail_Bttn");
	fnsWait_and_Click("SearchResource_SearchResult_EmailPopup_TepmlateType_SendEmail_Bttn");
	TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(5);
	
	TestSuiteBase_MonitorPlan.fnsTable_ClickOn_LINK_ByMatchingText(ResourceID);
	TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
	fnsGet_Element_Enabled("IPulse_Footer");
	TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
	
	fnsGet_Element_Enabled("EditResource_Edit_Bttn");
	fnsWait_and_Click("EditResource_Edit_Bttn");
	TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(3);
	
	
	
/*	//Commented 12.5.2016 as Correspondence TAB is disabled
	fnsGet_Element_Enabled("EditResource_Correspondence_TAB");
	fnsWait_and_Click("EditResource_Correspondence_TAB");
	
	
	fnsGet_Element_Enabled("EditResource_CorrespondenceTab_TableHeader_Data");
	String Record_Updated_Text_Fetched = fnsGet_Field_TEXT("EditResource_CorrespondenceTab_TableHeader_Data").toLowerCase().trim();
	String Current_DateMatched_to_Verify_RecordUpdated = TestSuiteBase_MonitorPlan.fnsReturn_Requried_cal("dd-MMM-yyyy").toLowerCase().trim();
	System.out.println("Current_DateMatched_to_Verify_RecordUpdated = "+Current_DateMatched_to_Verify_RecordUpdated);
	
	try{
		assert (Record_Updated_Text_Fetched.contains(Current_DateMatched_to_Verify_RecordUpdated)):"FAILED == 'Email send' record is not updated in Correspondence Tab, please refer screen shot >> EmailSendRecordNotFound"+fnsScreenShot_Date_format();
		fnsApps_Report_Logs("PASSED == Successfully verified that 'Emailsent' record is found in Correspondence TAB.");
	}catch(Throwable t){
		fnsTake_Screen_Shot("EmailSendRecordNotFound");
		isTestCasePass = false;
		fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
		throw new Exception(Throwables.getStackTraceAsString(t));		}
	*/
	
	
	
	
	
	
	
	
	/*
	
	After_DataAdded = TestSuiteBase_MonitorPlan.WithOut_OR_Retrun_TotalRowCount_OfTable(OR_Grip.getProperty("EditResource_CorrespondenceTab_TableHeader_Data"));
	
	try{
		assert !(Before_DataAdded.equals(After_DataAdded)):"FAILED == 'Email send' record is not updated in Correspondence Tab [Before<"+(Before_DataAdded+1)+"> after<"+(After_DataAdded+1)+">], please refer screen shot >> EmailSendRecordNotFound"+fnsScreenShot_Date_format();
		fnsApps_Report_Logs("PASSED == Successfully verified that 'Emailsent' record is found in Correspondence TAB.");
	}catch(Throwable t){
		fnsTake_Screen_Shot("EmailSendRecordNotFound");
		isTestCasePass = false;
		fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
		throw new Exception(Throwables.getStackTraceAsString(t));		}*/
	}catch(Throwable t){
		isTestCasePass = false;
		throw new Exception(Throwables.getStackTraceAsString(t));	
	}
}





@Test(dependsOnMethods={"Verify_Email_Functionality_on_SearchResourceScreen_WorkingFine_and_EmailSent_CrossCheck_in_CorrespondenceTAB"}, priority = 4, description="[Click resource id > go to Profile tab > Make sure we cannot change employee profile data but we can add additional info section.   Expected<The employee profile data should be in read only mode and Additonal info should be editable.>]")
public void EditResource_ProfileTab_Verify_ProfileInfoSection_ComingIn_ReadOnlyMode_and_OtherSections_in_EditMode() throws Throwable{
	fnsApps_Report_Logs("=========================================================================================================================================");
	fnsApps_Report_Logs("############### Test Case :::::4 EditResource_ProfileTab_Verify_ProfileInfoSection_ComingIn_ReadOnlyMode_and_OtherSections_in_EditMode");
	try{

		fnsGet_Element_Enabled("EditResource_Profile_TAB");
		fnsWait_and_Click("EditResource_Profile_TAB");
		
		
		fnsGet_Element_Enabled("EditResource_ProfileTab_ProfileInfoSection_TableHeader");
		WebElement ProfileInfoSection_TableElement = fnsGet_OR_Grip_ObjectX("EditResource_ProfileTab_ProfileInfoSection_TableHeader");
		List<WebElement> ProfileInfoSection_AllElements = ProfileInfoSection_TableElement.findElements(By.tagName("input"));
		Integer ProfileInfoSection_InputCount = ProfileInfoSection_AllElements.size();
		
		try{
			assert (ProfileInfoSection_InputCount==1):"FAILED == 'Profile Info' Section is not coming in ReadOnlyMode, Please refer screenshot >> ReadOnlyModeFail_"+ProfileInfoSection_InputCount+fnsScreenShot_Date_format();
			//Commented on 8.1202016 as new field Remote Resource added and coming in edit mode.     assert (ProfileInfoSection_InputCount==0):"FAILED == 'Profile Info' Section is not coming in ReadOnlyMode, Please refer screenshot >> ReadOnlyModeFail_"+ProfileInfoSection_InputCount+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED == Successfully Verify that 'Profile Info' Section is coming in ReadOnlyMode. "+ProfileInfoSection_InputCount);
		}catch(Throwable t){
			isTestCasePass = false;
			fnsTake_Screen_Shot("ReadOnlyModeFail_"+ProfileInfoSection_InputCount);
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));		}
		
		WebElement PrimaryAddressSection_TableElement = fnsGet_OR_Grip_ObjectX("EditResource_ProfileTab_PrimaryAddressSection_TableHeader");
		List<WebElement> PrimaryAddressSection_AllElements = PrimaryAddressSection_TableElement.findElements(By.tagName("input"));
		Integer PrimaryAddressSection_InputCount = PrimaryAddressSection_AllElements.size();
		
		try{
			assert (PrimaryAddressSection_InputCount==0):"FAILED == 'Primary Address' Section is not coming in ReadOnlyMode, Please refer screenshot >> EditModeFail_"+PrimaryAddressSection_InputCount+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED == Successfully Verify that 'Primary Address' Section is coming in ReadOnlyMode. "+PrimaryAddressSection_InputCount);
		}catch(Throwable t){
			isTestCasePass = false;
			fnsTake_Screen_Shot("ReadOnlyModeFail_"+PrimaryAddressSection_InputCount);
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));		}
		
		
		
		WebElement AddtionalInfoSection_TableElement = fnsGet_OR_Grip_ObjectX("EditResource_ProfileTab_AdditionalInfoSection_TableHeader");
		List<WebElement> AddtionalInfoSection_AllElements = AddtionalInfoSection_TableElement.findElements(By.tagName("input"));
		Integer AddtionalInfoSection_InputCount = AddtionalInfoSection_AllElements.size();
		
		try{
			assert (AddtionalInfoSection_InputCount>0):"FAILED == 'Additional Info' Sections are not coming in EditMode, Please refer screenshot >> EditModeFail_"+AddtionalInfoSection_InputCount+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED == Successfully Verify that 'Additional Info' Sections are coming in EditMode. "+AddtionalInfoSection_InputCount);
		}catch(Throwable t){
			isTestCasePass = false;
			fnsTake_Screen_Shot("EditModeFail_"+AddtionalInfoSection_InputCount);
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));		}
		
	}catch(Throwable t){
		isTestCasePass = false;
		throw new Exception(Throwables.getStackTraceAsString(t));	
	}	
}				


@Test(dependsOnMethods={"EditResource_ProfileTab_Verify_ProfileInfoSection_ComingIn_ReadOnlyMode_and_OtherSections_in_EditMode"}, priority = 5, description="[Enter the data under the Additonal info text field area. Click on Save button.   Expected<The information should get saved successfully.>]")
public void EditResource_ProfileTab_EnterData_in_AdditionalInfoSection_and_Verify_UpdatedSuccessfully() throws Throwable{
	fnsApps_Report_Logs("=========================================================================================================================================");
	fnsApps_Report_Logs("############### Test Case :::::3 EditResource_ProfileTab_EnterData_in_AdditionalInfoSection_and_Verify_UpdatedSuccessfully");
	try{
		fnsGet_Element_Enabled("EditResource_ProfileTab_SecondaryAddress_TableHeader");
		SecondayAddress_RowCount = TestSuiteBase_MonitorPlan.WithOut_OR_Retrun_TotalRowCount_OfTable(OR_Grip.getProperty("EditResource_ProfileTab_SecondaryAddress_TableHeader"));
		SecondayAddress_RowCount = SecondayAddress_RowCount+1;	
		for(int i=0; i<=SecondayAddress_RowCount; i++){
			String SecondayAddress_FirstRowText = fnsGet_Field_TEXT("EditResource_ProfileTab_SecondaryAddress_TableHeader_FirstRow").toLowerCase().trim();
			if(SecondayAddress_FirstRowText.contains("no records found")){
				fnsGet_Element_Enabled("EditResource_ProfileTab_SecondaryAddress_ADD_Bttn");
				fnsWait_and_Click("EditResource_ProfileTab_SecondaryAddress_ADD_Bttn");
				
				fncAssert_Contains(OR_Grip.getProperty("EditResource_ProfileTab_SecondaryAddress_Popup_Title"), "Add new additional address", "'Add new additional address' Popup is not getting opened");
				TestSuiteBase_MonitorPlan.WithOut_OR_fnDD_SelectValue_By_MatchingText(OR_Grip.getProperty("EditResource_ProfileTab_SecondaryAddress_Popup_AddressType_DD_Click"), OR_Grip.getProperty("EditResource_ProfileTab_SecondaryAddress_Popup_AddressType_DD_Value"), "li", "Secondary");
				
				Thread.sleep(2000);
				fnsGet_Element_Enabled("EditResource_ProfileTab_SecondaryAddress_Popup_BusinessName");
				fnsWait_and_Type("EditResource_ProfileTab_SecondaryAddress_Popup_BusinessName", "Automation Test");
				
				fnsGet_Element_Enabled("EditResource_ProfileTab_SecondaryAddress_Popup_AddressLine1");
				fnsWait_and_Type("EditResource_ProfileTab_SecondaryAddress_Popup_AddressLine1", "789 N Dixboro Rd");
				
				fnsGet_Element_Enabled("EditResource_ProfileTab_SecondaryAddress_Popup_AddressLine2");
				fnsWait_and_Type("EditResource_ProfileTab_SecondaryAddress_Popup_AddressLine2", "Ann Arbor");
				
				fnsGet_Element_Enabled("EditResource_ProfileTab_SecondaryAddress_Popup_AddressLine3");
				fnsWait_and_Type("EditResource_ProfileTab_SecondaryAddress_Popup_AddressLine3", "MI 48105");
				
				TestSuiteBase_MonitorPlan.WithOut_OR_fnDD_SelectValue_By_MatchingText(OR_Grip.getProperty("EditResource_ProfileTab_SecondaryAddress_Popup_Country_DD_Click"), OR_Grip.getProperty("EditResource_ProfileTab_SecondaryAddress_Popup_Country_DD_Value"), "li", "United States");
				//TestSuiteBase_MonitorPlan.WithOut_OR_fnDD_SelectValue_Through_Filter(OR_Grip.getProperty("EditResource_ProfileTab_SecondaryAddress_Popup_Country_DD_Click"), OR_Grip.getProperty("EditResource_ProfileTab_SecondaryAddress_Popup_Country_DD_Filter"), "United States");
				TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(3);
				TestSuiteBase_MonitorPlan.WithOut_OR_fnDD_SelectValue_Through_Filter(OR_Grip.getProperty("EditResource_ProfileTab_SecondaryAddress_Popup_State_DD_Click"), OR_Grip.getProperty("EditResource_ProfileTab_SecondaryAddress_Popup_State_DD_Filter"), "Michigan");
				Thread.sleep(200);
				
				fnsGet_Element_Enabled("EditResource_ProfileTab_SecondaryAddress_Popup_SaveClose_Bttn");
				fnsWait_and_Click("EditResource_ProfileTab_SecondaryAddress_Popup_SaveClose_Bttn");
				TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
				fncVerify_DataUpdated_Successfully("IPulse_ValidationMessage_Div", "Profile : SecondayAddress");
				break;			
				
			}else{
				String Delete_YES_Bttn_Xpath = OR_Grip.getProperty("EditResource_ProfileTab_SecondaryAddress_Delete_YES_Bttn1")+(SecondayAddress_RowCount-(i+1))+OR_Grip.getProperty("EditResource_ProfileTab_SecondaryAddress_Delete_YES_Bttn2");
				fnsGet_Element_Enabled("EditResource_ProfileTab_SecondaryAddress_Delete_Bttn");
				fnsWait_and_Click("EditResource_ProfileTab_SecondaryAddress_Delete_Bttn");
				TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(3);
				TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(Delete_YES_Bttn_Xpath);
				TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(Delete_YES_Bttn_Xpath);
				TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(3);
			}
			
		}
		
	
/*		fnsGet_Element_Enabled("EditResource_ProfileTab_AdditionalInfoSection_AddEmail");
		fnsWait_and_Clear("EditResource_ProfileTab_AdditionalInfoSection_AddEmail");
		fnsWait_and_Type("EditResource_ProfileTab_AdditionalInfoSection_AddEmail", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "AddEmail"));
		*/
		fnsGet_Element_Enabled("EditResource_ProfileTab_AdditionalInfoSection_LocationPreference");
		fnsWait_and_Clear("EditResource_ProfileTab_AdditionalInfoSection_LocationPreference");
		fnsWait_and_Type("EditResource_ProfileTab_AdditionalInfoSection_LocationPreference", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "LocationPreference"));
		
		
		//Added on 12.12.2016 as mandatory
		fnsGet_Element_Enabled("EditResource_ProfileTab_AdditionalInfoSection_ReomteResource_DD");
		fnsDD_value_Select_By_MatchingText_DownKeyPress("EditResource_ProfileTab_AdditionalInfoSection_ReomteResource_DD", "EditResource_ProfileTab_AdditionalInfoSection_ReomteResource_value", "li", "Yes");
			
		
		
		
		fnsWait_and_Click("EditResource_Save_Bttn");
		
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		fncVerify_DataUpdated_Successfully("IPulse_ValidationMessage_Div", "AdditionalInfo");
	}catch(Throwable t){
		isTestCasePass = false;
		throw new Exception(Throwables.getStackTraceAsString(t));	
	}
}


@Test(dependsOnMethods={"EditResource_ProfileTab_EnterData_in_AdditionalInfoSection_and_Verify_UpdatedSuccessfully"}, priority = 8, description="Step1. [Go to Qualifications ->Click on Add for Standards,Edit for Standards and make changes and click save icon.   Expected<Data Updated Successfully message should come.>]    Step2. [Standards> Delete the newly created record.   Expected<Data deleted successfully! message should come and row should be deleted.>]" )
public void EditResource_QualificationsTab_StandardsSection_Add_Edit_and_Delete_Records_and_Verify_UpdateDeleteWorkingFine() throws Throwable{
	fnsApps_Report_Logs("=========================================================================================================================================");
	fnsApps_Report_Logs("######### Test Case ::::8 EditResource_QualificationsTab_StandardsSection_Add_Edit_and_Delete_Records_and_Verify_UpdateDeleteWorkingFine");
	try{
			fncClicking_on_TAB("Qualifications", "EditResource_Qualifications_TAB", "EditResource_QualificationsTab_Standards_Add_Bttn");
		
		String Standards_Popup_LookUp_Value = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Popup_LK_TextField").trim();
		Before_DataAdded = TestSuiteBase_MonitorPlan.WithOut_OR_Retrun_TotalRowCount_OfTable(OR_Grip.getProperty("EditResource_QualificationsTab_Standards_TableHeader"));
		String TableText = TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(OR_Grip.getProperty("EditResource_QualificationsTab_Standards_TableHeader")).toLowerCase();
		if (TableText.contains("no standards have been added")){
			Before_DataAdded = 0;
		}
		
		
	
		fnsGet_Element_Enabled("EditResource_QualificationsTab_Standards_Edit_ShowAllFilter_DD_Click");
		fnsWait_and_Click("EditResource_QualificationsTab_Standards_Edit_ShowAllFilter_DD_Click");
		
		fnsGet_Element_Enabled("EditResource_QualificationsTab_Standards_Edit_ShowAllFilter_DD_Filter");
		fnsGet_OR_Grip_ObjectX("EditResource_QualificationsTab_Standards_Edit_ShowAllFilter_DD_Filter").sendKeys(Standards_Popup_LookUp_Value);
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
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
		//	Thread.sleep(2000);
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
		//TestSuiteBase_MonitorPlan.fnsLookup_RadioBttn_Select();  // New code developed --- lookup changed from radio button to check box.
		TestSuiteBase_MonitorPlan.fnsSelect_lookup_FirstCheckBox_and_Return();
		
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		fnsGet_Element_Enabled("EditResource_QualificationsTab_Standards_Popup_Save_Bttn");
		//Thread.sleep(1000);
		fnsWait_and_Click("EditResource_QualificationsTab_Standards_Popup_Save_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		//Thread.sleep(5000);
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
	
		
		if(driver.findElements(By.xpath(OR_Grip.getProperty("IPulse_ValidationMessage_Div"))).size()>0){
			TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		}
		
		String DeleteButtonXpath = "";
		
		if(Resource_TonyCampos){
			fncVerify_DataUpdated_Successfully("IPulse_ValidationMessage_Div", "QualificationsTab: 'Standards' Section");
			
			TestSuiteBase_MonitorPlan.WithOut_OR_fnDD_SelectValue_Through_Filter(OR_Grip.getProperty("EditResource_QualificationsTab_Standards_Edit_ShowAllFilter_DD_Click"), OR_Grip.getProperty("EditResource_QualificationsTab_Standards_Edit_ShowAllFilter_DD_Filter"), Standards_Popup_LookUp_Value);
			TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
			Thread.sleep(2000);
		}
		
		
			
		
		
		
		
		NewlyAddedRowNo = TestSuiteBase_MonitorPlan.WithOut_OR_fnsReturn_RowNumber_By_TableMatching_TEXT(OR_Grip.getProperty("EditResource_QualificationsTab_Standards_TableHeader"), OR_Grip.getProperty("EditResource_QualificationsTab_Standards_TableHeader_Data"), Standards_Popup_LookUp_Value);
		String EditBttnXpath = OR_Grip.getProperty("EditResource_QualificationsTab_Standards_Edit_Bttn1")+(NewlyAddedRowNo-1)+ OR_Grip.getProperty("EditResource_QualificationsTab_Standards_Edit_Bttn2");
		String EditRowXpath = OR_Grip.getProperty("EditResource_QualificationsTab_Standards_TableHeader_Data")+"/tr["+NewlyAddedRowNo+"]";
		DeleteButtonXpath = OR_Grip.getProperty("EditResource_QualificationsTab_Standards_Delete_Bttn1")+(NewlyAddedRowNo-1)+OR_Grip.getProperty("EditResource_QualificationsTab_Standards_Delete_Bttn2");
			
		
		
		//While adding new resource on hold should be N by default. 11.10.2017
		fnsGet_Element_Enabled("EditResource_QualificationsTab_Standards_TableHeader_Column");
		Integer OnHold_ColumnNo = TestSuiteBase_MonitorPlan.WithOut_OR_Retrun_TableColumnNo_of_MatchingColumnName_BY_EqualsIgnoreCase(OR_Grip.getProperty("EditResource_QualificationsTab_Standards_TableHeader_Column"), "On Hold");
		fnsApps_Report_Logs("OnHold_ColumnNo = " +OnHold_ColumnNo);
		String OnHold_Column_Xpath = ".//*[@id='mainForm:resourceTabView:rsQualTabView:rsEditStandardDT_data']/tr[1]/td["+OnHold_ColumnNo+"]";
		String OnHold_Text_ByDefault = TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(OnHold_Column_Xpath).toLowerCase().trim();
		try{
			assert OnHold_Text_ByDefault.equalsIgnoreCase("n"):"FAILED == 'Standrads', By default on Hold is not coming 'N', please refer screenshot >> ByDefault_OnHold_N_Fail"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED == Successfully verified that by default on Hold is coming 'N'.");
		}catch( Throwable t ){
			isTestCasePass = false;
			fnsTake_Screen_Shot("ByDefault_OnHold_N_Fail");
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		
		
		
		
		
		
		TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(EditBttnXpath);
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
			
		fncAssert_Contains(OR_Grip.getProperty("EditResource_QualificationsTab_Standards_Edit_Popup_Title"), "Edit Standard Qualification", "'Edit Standard Qualification' Popup is not getting opened");
	
		fnsGet_Element_Enabled("EditResource_QualificationsTab_Standards_Popup_ExpirationDate");
		fncCalendarDatePicker_BySelectValues_From_DropDown(OR_Grip.getProperty("EditResource_QualificationsTab_Standards_Popup_ExpirationDate_Calander_Bttn"), null, fnsReturn_Requried_Year(3));
		
		TestSuiteBase_MonitorPlan.WithOut_OR_fnDD_SelectValue_By_MatchingText(OR_Grip.getProperty("EditResource_QualificationsTab_Standards_Popup_OnHold_DD_Click"), OR_Grip.getProperty("EditResource_QualificationsTab_Standards_Popup_OnHold_DD_Value"), "li", "Yes");
		Thread.sleep(2000);
		fnsGet_Element_Enabled("EditResource_QualificationsTab_Standards_Edit_Popup_Save_Bttn");
		fnsWait_and_Click("EditResource_QualificationsTab_Standards_Edit_Popup_Save_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(3);
		//Thread.sleep(2000);
		fncVerify_DataUpdated_Successfully("IPulse_ValidationMessage_Div", "QualificationsTab: 'Standards' Section");
	
		
		fnsGet_Element_Enabled("EditResource_QualificationsTab_Standards_TableHeader_Column");
		String OnHold_Text = TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(OnHold_Column_Xpath).toLowerCase().trim();
		try{
			assert OnHold_Text.equalsIgnoreCase("y"):"FAILED == 'Standrads' type is not put on Hold, please refer screenshot >> Standrads_OnHoldFail"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED == Successfully verified that 'Standrads' type is put on Hold.");
		}catch( Throwable t ){
			isTestCasePass = false;
			fnsTake_Screen_Shot("Standrads_OnHoldFail");
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		
		String Expiration_DateMatch_String = null;
		if(fnsReturn_Requried_YearDate(3).contains("31")){
			Expiration_DateMatch_String = "30-"+fnsReturn_Requried_YearDate(3).split("-")[1]+"-"+fnsReturn_Requried_YearDate(3).split("-")[2];
		}else{
			Expiration_DateMatch_String = fnsReturn_Requried_YearDate(3).toString();
		}
		
		
		fncAssert_Contains(EditRowXpath,  Expiration_DateMatch_String,  "Edit 'Expiration Date' Field is not updated into the Table for 'newlly added records'");
			
		TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(DeleteButtonXpath);
		TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(DeleteButtonXpath);
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		//Added on 15.6.2015a
		fnsGet_Element_Enabled("EditResource_QualificationsTab_Standards_Test_Ac_Record_Delete_Comments");
		fnsWait_and_Type("EditResource_QualificationsTab_Standards_Test_Ac_Record_Delete_Comments", "Automation Test");
		
		
		fnsGet_Element_Enabled("EditResource_QualificationsTab_Standards_Delete_YES_Bttn");
		fnsWait_and_Click("EditResource_QualificationsTab_Standards_Delete_YES_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(3);
	//	Thread.sleep(2000);
		fncVerify_DataDeleted_Successfully("IPulse_ValidationMessage_Div", "QualificationsTab: 'Standards' Section");
	}catch(Throwable t){
		isTestCasePass = false;
		throw new Exception(Throwables.getStackTraceAsString(t));	
	}
}


@Test(dependsOnMethods={"EditResource_QualificationsTab_StandardsSection_Add_Edit_and_Delete_Records_and_Verify_UpdateDeleteWorkingFine"}, priority = 9, description="Step1. [Go to Qualifications ->Click on Add for Language,Edit for Language and make changes and click save icon.   Expected<Data Updated Successfully message should come.>]    Step2. [Language> Delete the newly created record.   Expected<Data deleted successfully! message should come and row should be deleted.>]" )
public void EditResource_QualificationsTab_LanguageSection_Add_Edit_and_Delete_Records_and_Verify_UpdateDeleteWorkingFine() throws Throwable{
	fnsApps_Report_Logs("=========================================================================================================================================");
	fnsApps_Report_Logs("########### Test Case ::::9 EditResource_QualificationsTab_LanguageSection_Add_Edit_and_Delete_Records_and_Verify_UpdateDeleteWorkingFine");
	try{
		fncClicking_on_TAB("Qualifications: 'Language' Section", "EditResource_QualificationsTab_LanguageSection_TAB", "EditResource_QualificationsTab_Language_Add_Bttn");
		
		Before_DataAdded = TestSuiteBase_MonitorPlan.WithOut_OR_Retrun_TotalRowCount_OfTable(OR_Grip.getProperty("EditResource_QualificationsTab_Language_TableHeader"));
		String TableText = TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(OR_Grip.getProperty("EditResource_QualificationsTab_Language_TableHeader")).toLowerCase();
		if (TableText.contains("no languages have been added")){
			Before_DataAdded = 0;
		}
		
		fnsGet_Element_Enabled("EditResource_QualificationsTab_Language_Add_Bttn");
		fnsWait_and_Click("EditResource_QualificationsTab_Language_Add_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		for(int wait=0; wait<=(Long.parseLong(CONFIG.getProperty("ElementWaitTime"))/4); wait++){
			After_DataAdded = TestSuiteBase_MonitorPlan.WithOut_OR_Retrun_TotalRowCount_OfTable(OR_Grip.getProperty("EditResource_QualificationsTab_Language_TableHeader"));
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
		
		fnsGet_Element_Enabled("EditResource_QualificationsTab_Language_LanguageDD_Click");
		fnsDD_value_Select_By_MatchingText_DownKeyPress("EditResource_QualificationsTab_Language_LanguageDD_Click", "EditResource_QualificationsTab_Language_LanguageDD_Value", "li",  fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "LanguageDD"));
		
		fnsGet_Element_Enabled("EditResource_QualificationsTab_Language_Read_CheckBox");
		fnsWait_and_Click("EditResource_QualificationsTab_Language_Read_CheckBox");
		
		
		
		fncEdit_and_Save_ButtonClick_WithOut_OR(("EditResource_QualificationsTab_Language_TableHeader_Data"), 1, "save");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(3);
		
		fncVerify_DataUpdated_Successfully("IPulse_ValidationMessage_Div", "QualificationsTab: 'Language' Section");
		
		
		fncEdit_and_Save_ButtonClick_WithOut_OR(("EditResource_QualificationsTab_Language_TableHeader_Data"), 1, "edit");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(3);
		
		fnsGet_Element_Enabled("EditResource_QualificationsTab_Language_Speak_CheckBox");
		fnsWait_and_Click("EditResource_QualificationsTab_Language_Speak_CheckBox");
		
		fnsGet_Element_Enabled("EditResource_QualificationsTab_Language_Write_CheckBox");
		fnsWait_and_Click("EditResource_QualificationsTab_Language_Write_CheckBox");
		
		fncEdit_and_Save_ButtonClick_WithOut_OR(("EditResource_QualificationsTab_Language_TableHeader_Data"), 1, "Save");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(3);
		
		fncVerify_DataUpdated_Successfully("IPulse_ValidationMessage_Div", "QualificationsTab: 'Language' Section");
		
		fnsGet_Element_Enabled("EditResource_QualificationsTab_Language_Delete_Bttn");
		fnsWait_and_Click("EditResource_QualificationsTab_Language_Delete_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		fnsGet_Element_Enabled("EditResource_QualificationsTab_Language_Delete_YES_Bttn");
		fnsWait_and_Click("EditResource_QualificationsTab_Language_Delete_YES_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		//Thread.sleep(1000);
		fncVerify_DataDeleted_Successfully("IPulse_ValidationMessage_Div", "QualificationsTab: 'Language' Section");
	}catch(Throwable t){
		isTestCasePass = false;
		throw new Exception(Throwables.getStackTraceAsString(t));	
	}
}





@Test(dependsOnMethods={"EditResource_QualificationsTab_LanguageSection_Add_Edit_and_Delete_Records_and_Verify_UpdateDeleteWorkingFine"}, priority = 10, description="Step1. [Go to Qualifications ->Click on Add for Disqualifications,Edit for Disqualifications and make changes and click save icon.   Expected<Data Updated Successfully message should come.>]    Step2. [Disqualifications> Delete the newly created record.   Expected<Data deleted successfully! message should come and row should be deleted.>]" )
public void EditResource_QualificationsTab_DisqualificationsSection_Add_Edit_and_Delete_Records_and_Verify_UpdateDeleteWorkingFine() throws Throwable{
	fnsApps_Report_Logs("=========================================================================================================================================");
	fnsApps_Report_Logs("##### Test Case :::10 EditResource_QualificationsTab_DisqualificationsSection_Add_Edit_and_Delete_Records_and_Verify_UpdateDeleteWorkingFine");
	try{
		fncClicking_on_TAB("Qualifications: 'Disqualifications' Section", "EditResource_QualificationsTab_DisqualificationSection_TAB", "EditResource_QualificationsTab_Disqualification_FacilityDisqualification_Add_Bttn");
		
		//Validate if any record found for given data the first delete it.
		Before_DataAdded = TestSuiteBase_MonitorPlan.WithOut_OR_Retrun_TotalRowCount_OfTable(OR_Grip.getProperty("EditResource_QualificationsTab_Disqualification_FacilityDisqualification_TableHeader"));
		String FacilityDisqualification_TableText = fnsGet_Field_TEXT("EditResource_QualificationsTab_Disqualification_FacilityDisqualification_TableHeader").trim();
		if(FacilityDisqualification_TableText.contains(fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "FacilityCode_LK_Text"))){
			for(int LoopStart=1; LoopStart<=Before_DataAdded; LoopStart++){
				String RowXpath = OR_Grip.getProperty("EditResource_QualificationsTab_Disqualification_FacilityDisqualification_TableHeader_Data")+"/tr["+LoopStart+"]";
				String RowText = TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(RowXpath).trim();
				if(RowText.contains(fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "FacilityCode_LK_Text"))){
					LoopStart = LoopStart-1;
					Before_DataAdded = Before_DataAdded-1;
					MatchingText_RowXpath_Count = Before_DataAdded ;
					String FacilityDisqualification_DeleteBttnXpath = "//*[@id='mainForm:resourceTabView:rsQualTabView:resDisQualDT:"+LoopStart+":delBilInfo']";
					TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(FacilityDisqualification_DeleteBttnXpath);
					TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(FacilityDisqualification_DeleteBttnXpath);
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
					
					String FacilityDisqualification_Delete_YES_BttnXpath = ".//*[@id='mainForm:resourceTabView:rsQualTabView:resDisQualDT:"+MatchingText_RowXpath_Count+":yshidebtn']";
				//	TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(FacilityDisqualification_Delete_YES_BttnXpath); //Not Working changed on 9.10.2017
				//	TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(FacilityDisqualification_Delete_YES_BttnXpath);
					fnsGet_Element_Enabled("EditResource_QualificationsTab_Disqualification_FacilityDisqualification_Delete_YES_Bttn");
					fnsWait_and_Click("EditResource_QualificationsTab_Disqualification_FacilityDisqualification_Delete_YES_Bttn");
					
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(3);
				}
			}
			Thread.sleep(2000);
		}
		
		Before_DataAdded = TestSuiteBase_MonitorPlan.WithOut_OR_Retrun_TotalRowCount_OfTable(OR_Grip.getProperty("EditResource_QualificationsTab_Disqualification_FacilityDisqualification_TableHeader"));
		String TableText = TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(OR_Grip.getProperty("EditResource_QualificationsTab_Disqualification_FacilityDisqualification_TableHeader")).toLowerCase();
		if (TableText.contains("no disqualification have been added")){
			Before_DataAdded = 0;
		}
		//Commented on 21.3.207 as new change came IPM-5364
		/*/fnsGet_Element_Enabled("EditResource_QualificationsTab_Disqualification_FacilityDisqualification_Add_Bttn");
		fnsWait_and_Click("EditResource_QualificationsTab_Disqualification_FacilityDisqualification_Add_Bttn");
		TestSuiteBase_Aspirago.fnsLoading_Progressingwait(2);
		
		fnsGet_Element_Enabled("EditResource_QualificationsTab_Disqualification_FacilityDisqualification_FacilityCode_LK_Bttn");
		fnsWait_and_Click("EditResource_QualificationsTab_Disqualification_FacilityDisqualification_FacilityCode_LK_Bttn");
		TestSuiteBase_Aspirago.fnsLoading_Progressingwait(2);
		fnsGet_Element_Enabled("EditResource_QualificationsTab_Disqualification_FacilityDisqualification_FacilityCode_LK_Text");
		fnsWait_and_Clear("EditResource_QualificationsTab_Disqualification_FacilityDisqualification_FacilityCode_LK_Text");
		
		if(Resource_MatthewBoll){
			fnsWait_and_Type("EditResource_QualificationsTab_Disqualification_FacilityDisqualification_FacilityCode_LK_Text", "C0022389");
		}
		if(Resource_TonyCampos){
			fnsWait_and_Type("EditResource_QualificationsTab_Disqualification_FacilityDisqualification_FacilityCode_LK_Text", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "FacilityCode_LK_Text"));
		}
		TestSuiteBase_MonitorPlan.fnsLookup_RadioBttn_Select();/*/
		
		
		
		fnsGet_Element_Enabled("EditResource_QualificationsTab_Disqualification_FacilityDisqualification_Add_Bttn");
		fnsWait_and_Click("EditResource_QualificationsTab_Disqualification_FacilityDisqualification_Add_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		fnsGet_Element_Enabled("EditResource_QualificationsTab_Disqualification_FacilityDisqualification_FacilityCode_LK_Text");
		fnsWait_and_Clear("EditResource_QualificationsTab_Disqualification_FacilityDisqualification_FacilityCode_LK_Text");
		
		
		if(Resource_TonyCampos){
			fnsWait_and_Type("EditResource_QualificationsTab_Disqualification_FacilityDisqualification_FacilityCode_LK_Text", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "FacilityCode_LK_Text"));
		}
		TestSuiteBase_MonitorPlan.fnsSelect_lookup_FirstCheckBox_and_Return();
		
		
		
		
		
		fnsGet_Element_Enabled("EditResource_QualificationsTab_Disqualification_FacilityDisqualification_DisqualificationType_DD_Click");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		fnsDD_value_Select_By_MatchingText_DownKeyPress("EditResource_QualificationsTab_Disqualification_FacilityDisqualification_DisqualificationType_DD_Click", "EditResource_QualificationsTab_Disqualification_FacilityDisqualification_DisqualificationType_DD_Value", "li", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "DisqualificationType_DD"));
		
		fncEdit_and_Save_ButtonClick_WithOut_OR(("EditResource_QualificationsTab_Disqualification_FacilityDisqualification_TableHeader_Data"), 1, "Save");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(3);
		
		fncVerify_DataUpdated_Successfully("IPulse_ValidationMessage_Div", "QualificationsTab: 'Disqualifications' Section");
		
		//Editing and Delete Newly Added Records
		fncEdit_and_Save_ButtonClick_WithOut_OR(("EditResource_QualificationsTab_Disqualification_FacilityDisqualification_TableHeader_Data"), 1, "edit");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(3);
		
		fnsGet_Element_Enabled("EditResource_QualificationsTab_Disqualification_FacilityDisqualification_DisqualificationType_DD_Click");
		fnsDD_value_Select_By_MatchingText_DownKeyPress("EditResource_QualificationsTab_Disqualification_FacilityDisqualification_DisqualificationType_DD_Click", "EditResource_QualificationsTab_Disqualification_FacilityDisqualification_DisqualificationType_DD_Value", "li", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "DisqualificationType_DD_Edit"));
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
	
		fncEdit_and_Save_ButtonClick_WithOut_OR(("EditResource_QualificationsTab_Disqualification_FacilityDisqualification_TableHeader_Data"), 1, "Save");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(3);
		
		fncVerify_DataUpdated_Successfully("IPulse_ValidationMessage_Div", "QualificationsTab: 'Disqualifications' Section");
		
		
		fnsGet_Element_Enabled("EditResource_QualificationsTab_Disqualification_FacilityDisqualification_Delete_Bttn");
		fnsWait_and_Click("EditResource_QualificationsTab_Disqualification_FacilityDisqualification_Delete_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		fnsGet_Element_Enabled("EditResource_QualificationsTab_Disqualification_FacilityDisqualification_Delete_YES_Bttn");
		fnsWait_and_Click("EditResource_QualificationsTab_Disqualification_FacilityDisqualification_Delete_YES_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(5);
		Thread.sleep(1500);
		fncVerify_DataDeleted_Successfully("IPulse_ValidationMessage_Div", "QualificationsTab: 'Disqualifications' Section");          //Tem. Commented
		
		
		//Facility/Standard Rotation DIV Start
		//Validate if any record found for given data the first delete it.
		Before_DataAdded = TestSuiteBase_MonitorPlan.WithOut_OR_Retrun_TotalRowCount_OfTable(OR_Grip.getProperty("EditResource_QualificationsTab_Disqualification_FacilityStandards_TableHeader"));
		String FacilityStandards_TableText = fnsGet_Field_TEXT("EditResource_QualificationsTab_Disqualification_FacilityStandards_TableHeader").trim();
		if(FacilityStandards_TableText.contains(fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "FacilityCode_LK"))){
			for(int LoopStart=1; LoopStart<=Before_DataAdded; LoopStart++){
				String RowXpath = OR_Grip.getProperty("EditResource_QualificationsTab_Disqualification_FacilityStandards_TableHeader_Data")+"/tr["+LoopStart+"]";
				String RowText = TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(RowXpath).trim();
				if(RowText.contains(fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "FacilityCode_LK"))){
					LoopStart = LoopStart-1;
					Before_DataAdded = Before_DataAdded-1;
					MatchingText_RowXpath_Count = Before_DataAdded ;
					String FacilityDisqualification_DeleteBttnXpath = ".//*[@id='mainForm:resourceTabView:rsQualTabView:resDisQualFSRDT:"+LoopStart+":delfstrtt']";
					TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(FacilityDisqualification_DeleteBttnXpath);
					TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(FacilityDisqualification_DeleteBttnXpath);
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
					
				//	String FacilityDisqualification_Delete_YES_BttnXpath = ".//*[@id='mainForm:resourceTabView:rsQualTabView:resDisQualFSRDT:"+MatchingText_RowXpath_Count+":yshidebtnfsr']";
					//TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(FacilityDisqualification_Delete_YES_BttnXpath);
					//TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(FacilityDisqualification_Delete_YES_BttnXpath);
					fnsGet_Element_Enabled("EditResource_QualificationsTab_Disqualification_FacilityStandards_Delete_YES_Bttn");
					fnsWait_and_Click("EditResource_QualificationsTab_Disqualification_FacilityStandards_Delete_YES_Bttn");
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(3);
				}
			}
			Thread.sleep(2000);
		}
		
		Before_DataAdded = TestSuiteBase_MonitorPlan.WithOut_OR_Retrun_TotalRowCount_OfTable(OR_Grip.getProperty("EditResource_QualificationsTab_Disqualification_FacilityStandards_TableHeader"));
		String FacilityStandardRotation_TableText = TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(OR_Grip.getProperty("EditResource_QualificationsTab_Disqualification_FacilityStandards_TableHeader")).toLowerCase();
		if (FacilityStandardRotation_TableText.contains("no facility standard rotation have been added")){
			Before_DataAdded = 0;
		}
		
		fnsGet_Element_Enabled("EditResource_QualificationsTab_Disqualification_FacilityStandards_Add_Bttn");
		fnsWait_and_Click("EditResource_QualificationsTab_Disqualification_FacilityStandards_Add_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		fncAssert_Contains(OR_Grip.getProperty("EditResource_QualificationsTab_Disqualification_FacilityStandards_Popup_title"), "Facility/Standard Rotation", "DisQualification: 'Facility/Standard Rotation' Popup is not getting opened");
		
		
		
		
		
		fnsGet_Element_Enabled("EditResource_QualificationsTab_Disqualification_FacilityStandards_FacilityCode_LK_Bttn");
		fnsWait_and_Click("EditResource_QualificationsTab_Disqualification_FacilityStandards_FacilityCode_LK_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		fnsGet_Element_Enabled("EditResource_QualificationsTab_Disqualification_FacilityStandards_FacilityCode_LK_Text");
		fnsWait_and_Clear("EditResource_QualificationsTab_Disqualification_FacilityStandards_FacilityCode_LK_Text");
		
		
		if(Resource_TonyCampos){
			fnsWait_and_Type("EditResource_QualificationsTab_Disqualification_FacilityStandards_FacilityCode_LK_Text", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "FacilityCode_LK"));
		}
		
		TestSuiteBase_MonitorPlan.fnsLookup_RadioBttn_Select();
		
		fnsGet_Element_Enabled("EditResource_QualificationsTab_Disqualification_FacilityStandards_Standard_LK_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		fnsWait_and_Click("EditResource_QualificationsTab_Disqualification_FacilityStandards_Standard_LK_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		fnsGet_Element_Enabled("EditResource_QualificationsTab_Disqualification_FacilityStandards_Standard_LK_Text");
		fnsWait_and_Clear("EditResource_QualificationsTab_Disqualification_FacilityStandards_Standard_LK_Text");
		
		if(Resource_TonyCampos){
			fnsWait_and_Type("EditResource_QualificationsTab_Disqualification_FacilityStandards_Standard_LK_Text", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Standard_LK_Text"));
		}
		
		TestSuiteBase_MonitorPlan.fnsSelect_lookup_FirstCheckBox_and_Return();
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(3);
		
		fnsWait_and_Click("EditResource_QualificationsTab_Disqualification_FacilityStandards_Popup_Save_bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		
	//	fncEdit_and_Save_ButtonClick_WithOut_OR(("EditResource_QualificationsTab_Disqualification_FacilityStandards_TableHeader_Data"), 1, "Save");
		//fncEdit_and_Save_ButtonClick_WithOut_OR(("EditResource_QualificationsTab_Disqualification_FacilityStandards_TableHeader_Data"), 1, "Save");
	//	TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
	
		
		fncVerify_DataUpdated_Successfully("IPulse_ValidationMessage_Div", "QualificationsTab: 'Disqualifications' Section");   //Temp Commented
		
		//Editing and Delete Newly Added Records
		fncEdit_and_Save_ButtonClick_WithOut_OR(("EditResource_QualificationsTab_Disqualification_FacilityStandards_TableHeader_Data"), 1, "edit");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(3);
		
		fnsGet_Element_Enabled("EditResource_QualificationsTab_Disqualification_FacilityStandards_BlockedFrom");
		fnsWait_and_Type("EditResource_QualificationsTab_Disqualification_FacilityStandards_BlockedFrom", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "BlockedFrom"));
		
		fnsGet_Element_Enabled("EditResource_QualificationsTab_Disqualification_FacilityStandards_BlockedTo");
		fnsWait_and_Type("EditResource_QualificationsTab_Disqualification_FacilityStandards_BlockedTo", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "BlockedTo"));
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		fncEdit_and_Save_ButtonClick_WithOut_OR(("EditResource_QualificationsTab_Disqualification_FacilityStandards_TableHeader_Data"), 1, "Save");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(3);
		
		fncVerify_DataUpdated_Successfully("IPulse_ValidationMessage_Div", "QualificationsTab: 'Disqualifications' Section");
		
		fnsGet_Element_Enabled("EditResource_QualificationsTab_Disqualification_FacilityStandards_Delete_Bttn");
		fnsWait_and_Click("EditResource_QualificationsTab_Disqualification_FacilityStandards_Delete_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		fnsGet_Element_Enabled("EditResource_QualificationsTab_Disqualification_FacilityStandards_Delete_YES_Bttn");
		fnsWait_and_Click("EditResource_QualificationsTab_Disqualification_FacilityStandards_Delete_YES_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(3);
		//Thread.sleep(1000);
		fncVerify_DataDeleted_Successfully("IPulse_ValidationMessage_Div", "QualificationsTab: 'Disqualifications' Section");
	}catch(Throwable t){
		isTestCasePass = false;
		throw new Exception(Throwables.getStackTraceAsString(t));	
	}
}
	




@Test(dependsOnMethods={"EditResource_QualificationsTab_DisqualificationsSection_Add_Edit_and_Delete_Records_and_Verify_UpdateDeleteWorkingFine"}, priority = 11, description="Step1. [Go to Certifications tab> Add>Add mandatory fields and click on Save and Edit the information and click Save again.   Expected<Data saved successfully message should come.>]    Step2. [Click on delete button for the certification.   Expected<Data deleted successfully! message should come and row should be deleted.>]" )
public void EditResource_CertificationsTab_Add_Edit_and_Delete_Records_and_Verify_Records_Save_and_Delete_Successfully() throws Throwable{
	fnsApps_Report_Logs("=========================================================================================================================================");
	fnsApps_Report_Logs("############# Test Case :::::11 EditResource_CertificationsTab_Add_Edit_and_Delete_Records_and_Verify_Records_Save_and_Delete_Successfully");
	try{
		fncClicking_on_TAB("Certifications", "EditResource_Certifications_TAB", "EditResource_CertificationsTab_Add_Bttn");
		
		Before_DataAdded = TestSuiteBase_MonitorPlan.WithOut_OR_Retrun_TotalRowCount_OfTable(OR_Grip.getProperty("EditResource_CertificationsTab_TableHeader"));
		String TableText = TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(OR_Grip.getProperty("EditResource_CertificationsTab_TableHeader")).toLowerCase();
		if (TableText.contains("no certifications have been added")){
			Before_DataAdded = 0;
		}
		fnsGet_Element_Enabled("EditResource_CertificationsTab_Add_Bttn");
		fnsWait_and_Click("EditResource_CertificationsTab_Add_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		fncAssert_Contains(OR_Grip.getProperty("EditResource_CertificationsTab_AddPopup_Title"), "Add New Certificate", "Certifications: 'Add New Certificate' Popup is not getting opened");
		
		
		fnsGet_Element_Enabled("EditApplicant_CertificationsTab_AddPopup_Division_DD_Click");
		fnsDD_value_Select_By_MatchingText_DownKeyPress("EditApplicant_CertificationsTab_AddPopup_Division_DD_Click", "EditApplicant_CertificationsTab_AddPopup_Division_DD_Value", "li", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Certificate_Training_Division_DD"));
		
		
		
		fnsGet_Element_Enabled("EditResource_CertificationsTab_AddPopup_CertificationType_DD_Click");
		Thread.sleep(2000);
		fnsDD_value_Select_By_MatchingText_DownKeyPress("EditResource_CertificationsTab_AddPopup_CertificationType_DD_Click", "EditResource_CertificationsTab_AddPopup_CertificationType_DD_Value", "li", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "CertificationType_DD"));
		
		
		
		
		
		
		
		
		
		
		fncUploadFile("EditResource_CertificationsTab_AddPopup_BrowseFile", "'Add New Certificate' PopUp");
		
		
		
		
		
		
		
		
		
		
		
		
		
		fnsGet_Element_Enabled("EditResource_CertificationsTab_AddPopup_CertifiedBy");
		fnsWait_and_Type("EditResource_CertificationsTab_AddPopup_CertifiedBy", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "CertifiedBy"));
		
		fncCalendarDatePicker_BySelectValues_From_DropDown(OR_Grip.getProperty("EditResource_CertificationsTab_AddPopup_CertificateDate_Bttn"), fnsReturn_Requried_Month("MMM", 1), fnsReturn_Requried_Year(-1));
		
		
		fnsGet_Element_Enabled("EditResource_CertificationsTab_AddPopup_Save_Bttn");
		fnsWait_and_Click("EditResource_CertificationsTab_AddPopup_Save_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		fncVerify_DataUpdated_Successfully("IPulse_ValidationMessage_Div", "Certifications");
		
		fncVerify_NewRow_Added_into_Table("Certifications Table", OR_Grip.getProperty("EditResource_CertificationsTab_TableHeader"), Before_DataAdded);
		
		//Fetching newly added row no.
		for(int NewRowAdd=1; NewRowAdd<=After_DataAdded; NewRowAdd++){
			String RowXpath = OR_Grip.getProperty("EditResource_CertificationsTab_TableHeader_Data")+"/tr["+NewRowAdd+"]";
			String RowText = TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(RowXpath).toLowerCase().trim();
			if(RowText.contains(fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "CertificationType_DD").toLowerCase().trim())){
				NewlyAddedRowNo = NewRowAdd;
				break;
			}
		}
		
		String CertificationsTab_ExpirationDate_BttnXpath = OR_Grip.getProperty("EditResource_CertificationsTab_ExpirationDate_Bttn")+(NewlyAddedRowNo-1)+":expiryDate']/button";
		String CertificationsTab_Delete_BttnXpath = OR_Grip.getProperty("EditResource_CertificationsTab_Delete_Bttn1")+(NewlyAddedRowNo-1)+OR_Grip.getProperty("EditResource_CertificationsTab_Delete_Bttn2");
		
		fncEdit_and_Save_ButtonClick_WithOut_OR(("EditResource_CertificationsTab_TableHeader_Data"), NewlyAddedRowNo, "edit");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(3);
		
		fncCalendarDatePicker_BySelectValues_From_DropDown(CertificationsTab_ExpirationDate_BttnXpath, fnsReturn_Requried_Month("MMM", 3), fnsReturn_Requried_Year(2));
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		fncEdit_and_Save_ButtonClick_WithOut_OR(("EditResource_CertificationsTab_TableHeader_Data"), NewlyAddedRowNo, "save");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(3);
		
		fncVerify_DataUpdated_Successfully("IPulse_ValidationMessage_Div", "Certifications");
		
		
		TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(CertificationsTab_Delete_BttnXpath);
		TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(CertificationsTab_Delete_BttnXpath);
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		fnsGet_Element_Enabled("EditResource_CertificationsTab_Delete_YES_Bttn");
		fnsWait_and_Click("EditResource_CertificationsTab_Delete_YES_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(3);
		//Thread.sleep(1000);
		fncVerify_DataDeleted_Successfully("IPulse_ValidationMessage_Div", "Certifications");
	}catch(Throwable t){
		isTestCasePass = false;
		throw new Exception(Throwables.getStackTraceAsString(t));	
	}
}



/*//Comment because of IPM-14069
@Test(dependsOnMethods={"EditResource_CertificationsTab_Add_Edit_and_Delete_Records_and_Verify_Records_Save_and_Delete_Successfully"}, priority = 12, description="Step1. [Go to Training tab> Add>Add mandatory fields and click on Save and Edit the information and click Save again.   Expected<Data saved successfully message should come.>]    Step2. [Click on delete button for the certification.   Expected<Data deleted successfully! message should come and row should be deleted.>]" )
public void EditResource_TrainingTab_Add_Edit_and_Delete_Records_and_Verify_Records_Save_and_Delete_Successfully() throws Throwable{
	fnsApps_Report_Logs("=========================================================================================================================================");
	fnsApps_Report_Logs("################ Test Case :::::12 EditResource_TrainingTab_Add_Edit_and_Delete_Records_and_Verify_Records_Save_and_Delete_Successfully");
	try{
		fncClicking_on_TAB("Training", "EditResource_Training_TAB", "EditResource_TrainingTab_Add_Bttn");
		
		Before_DataAdded = TestSuiteBase_MonitorPlan.WithOut_OR_Retrun_TotalRowCount_OfTable(OR_Grip.getProperty("EditResource_TrainingTab_TableHeader"));
		String TableText = TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(OR_Grip.getProperty("EditResource_TrainingTab_TableHeader")).toLowerCase();
		if (TableText.contains("no training have been added")){
			Before_DataAdded = 0;
		}
		fnsGet_Element_Enabled("EditResource_TrainingTab_Add_Bttn");
		fnsWait_and_Click("EditResource_TrainingTab_Add_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		fncAssert_Contains(OR_Grip.getProperty("EditResource_TrainingTab_AddPopup_Title"), "Add a new Training", "Training: 'Add a new Training' Popup is not getting opened");
		
		fnsGet_Element_Enabled("EditApplicant_TrainingTab_AddPopup_Division_DD_Click");
		fnsDD_value_Select_By_MatchingText_DownKeyPress("EditApplicant_TrainingTab_AddPopup_Division_DD_Click", "EditApplicant_TrainingTab_AddPopup_Division_DD_Value", "li", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Certificate_Training_Division_DD"));
		
		
		fnsGet_Element_Enabled("EditResource_TrainingTab_AddPopup_TrainingCourse_DD_Click");
		fnsDD_value_Select_By_MatchingText_DownKeyPress("EditResource_TrainingTab_AddPopup_TrainingCourse_DD_Click", "EditResource_TrainingTab_AddPopup_TrainingCourse_DD_Value", "li", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "TrainingCourse_DD"));
		
		
		fnsGet_Element_Enabled("EditResource_TrainingTab_AddPopup_Description");
		fnsWait_and_Type("EditResource_TrainingTab_AddPopup_Description", Comments_Free_Text );
		
		fnsGet_Element_Enabled("EditResource_TrainingTab_AddPopup_Trainer");
		fnsWait_and_Type("EditResource_TrainingTab_AddPopup_Trainer", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Training_Trainer"));
		
		fncCalendarDatePicker_BySelectValues_From_DropDown(OR_Grip.getProperty("EditResource_TrainingTab_AddPopup_TrainingDate_Bttn"), null, null);
		
		
		fnsGet_Element_Enabled("EditResource_TrainingTab_AddPopup_Save_Bttn");
		fnsWait_and_Click("EditResource_TrainingTab_AddPopup_Save_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		fncVerify_DataUpdated_Successfully("IPulse_ValidationMessage_Div", "Training");
		
		fncVerify_NewRow_Added_into_Table("Training Table", OR_Grip.getProperty("EditResource_TrainingTab_TableHeader"), Before_DataAdded);
		
		//Fetching newly added row no.
		for(int NewRowAdd=1; NewRowAdd<=After_DataAdded; NewRowAdd++){
			String RowXpath = OR_Grip.getProperty("EditResource_TrainingTab_TableHeader_Data")+"/tr["+NewRowAdd+"]";
			String RowText = TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(RowXpath).toLowerCase().trim();
			if(RowText.contains(fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "TrainingCourse_DD").toLowerCase().trim())){
				NewlyAddedRowNo = NewRowAdd;
				break;
			}
		}
		
		String TrainingTab_ExpirationDate_BttnXpath = OR_Grip.getProperty("EditResource_TrainingTab_ExpirationDate_Bttn")+(NewlyAddedRowNo-1)+":trexpirydt']/button";
		String TrainingTab_Delete_BttnXpath = OR_Grip.getProperty("EditResource_TrainingTab_Delete_Bttn1")+(NewlyAddedRowNo-1)+OR_Grip.getProperty("EditResource_TrainingTab_Delete_Bttn2");
		
		fncEdit_and_Save_ButtonClick_WithOut_OR(("EditResource_TrainingTab_TableHeader_Data"), NewlyAddedRowNo, "edit");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(3);
		
		//Added on 19.4.2016 due to excess columns, bottom scroll bar coming
		Thread.sleep(1500);
		Actions act = new Actions(driver);
		act.sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ARROW_RIGHT).build().perform();
		
		Thread.sleep(3000);
		fncCalendarDatePicker_BySelectValues_From_DropDown(TrainingTab_ExpirationDate_BttnXpath, null, null);
		Thread.sleep(1000);
		
		fncEdit_and_Save_ButtonClick_WithOut_OR(("EditResource_TrainingTab_TableHeader_Data"), NewlyAddedRowNo, "save");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(3);
		
		fncVerify_DataUpdated_Successfully("IPulse_ValidationMessage_Div", "Training");
		
		
		TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(TrainingTab_Delete_BttnXpath);
		TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(TrainingTab_Delete_BttnXpath);
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		fnsGet_Element_Enabled("EditResource_TrainingTab_Delete_YES_Bttn");
		fnsWait_and_Click("EditResource_TrainingTab_Delete_YES_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(3);
		//Thread.sleep(1000);
		fncVerify_DataDeleted_Successfully("IPulse_ValidationMessage_Div", "Training");
	}catch(Throwable t){
		isTestCasePass = false;
		throw new Exception(Throwables.getStackTraceAsString(t));	
	}
}
*/


@Test(dependsOnMethods={"EditResource_CertificationsTab_Add_Edit_and_Delete_Records_and_Verify_Records_Save_and_Delete_Successfully"}, priority = 13, description="Step1. [Documents/Notes tab>Add Notes/Remarks -> Edit them and check if the data has been updated.   Expected<Data Updated successfully message should come.>]    Step2. [Documents/Notes tab>Add Notes/Remarks -> delete the newly added notes.   Expected<Data deleted successfully! message should come and row should be deleted.>]" )
public void EditResource_DocumentsNotesTab_NotesRemarksSection_Add_Edit_and_Delete_Records_and_Verify_Records_Save_and_Delete_Successfully() throws Throwable{
	fnsApps_Report_Logs("=========================================================================================================================================");
	fnsApps_Report_Logs("######### Test Case ::13 EditResource_DocumentsNotesTab_NotesRemarks_Add_Edit_and_Delete_Records_and_Verify_Records_Save_and_Delete_Success");
	try{
		driver.navigate().refresh();
		Thread.sleep(3000);
			
		fnsGet_Element_Enabled("EditResource_Edit_Bttn");
		fnsWait_and_Click("EditResource_Edit_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(3);
		
			
		fncClicking_on_TAB("DocumentsNotes: 'NotesRemarks'", "EditResource_DocumentsNotes_TAB", "EditResource_DocumentsNotesTab_NotesRemarks_Add_Bttn");
		
		fnsGet_Element_Enabled("EditResource_DocumentsNotesTab_NotesRemarks_Add_Bttn");
		fnsWait_and_Click("EditResource_DocumentsNotesTab_NotesRemarks_Add_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(3);
		
		fnsGet_Element_Enabled("EditResource_DocumentsNotesTab_NotesRemarks_Type_DD_Click");
		fnsDD_value_Select_By_MatchingText_DownKeyPress("EditResource_DocumentsNotesTab_NotesRemarks_Type_DD_Click", "EditResource_DocumentsNotesTab_NotesRemarks_Type_DD_Value", "li", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "NotesRemarks_Type_DD"));
		
		fnsGet_Element_Enabled("EditResource_DocumentsNotesTab_NotesRemarks_Notes_TextBox");
		fnsWait_and_Type("EditResource_DocumentsNotesTab_NotesRemarks_Notes_TextBox", "Test");
			
		fncEdit_and_Save_ButtonClick_WithOut_OR(("EditResource_DocumentsNotesTab_NotesRemarks_TableHeader_Data"), 1, "save");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(3);
		//Thread.sleep(2000); 
		//Thread.sleep(40000);  //Temp.
		
		fncVerify_DataUpdated_Successfully("IPulse_ValidationMessage_Div", "DocumentsNotes: 'NotesRemarks'");
		
		fncEdit_and_Save_ButtonClick_WithOut_OR(("EditResource_DocumentsNotesTab_NotesRemarks_TableHeader_Data"), 1, "edit");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		fnsGet_Element_Enabled("EditResource_DocumentsNotesTab_NotesRemarks_Notes_TextBox");
		fnsWait_and_Type("EditResource_DocumentsNotesTab_NotesRemarks_Notes_TextBox", " Automation Test");
		
		fncEdit_and_Save_ButtonClick_WithOut_OR(("EditResource_DocumentsNotesTab_NotesRemarks_TableHeader_Data"), 1, "save");
		//Thread.sleep(2000);
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(30);
		//Thread.sleep(40000);
		//Thread.sleep(60000);  //Temp.
		
		fncVerify_DataUpdated_Successfully("IPulse_ValidationMessage_Div", "DocumentsNotes: 'NotesRemarks'");
		
		fnsGet_Element_Enabled("EditResource_DocumentsNotesTab_NotesRemarks_Delete_Bttn");
		fnsWait_and_Click("EditResource_DocumentsNotesTab_NotesRemarks_Delete_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(3);
			
		fnsGet_Element_Enabled("EditResource_DocumentsNotesTab_NotesRemarks_Delete_YES_Bttn");
		fnsWait_and_Click("EditResource_DocumentsNotesTab_NotesRemarks_Delete_YES_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(5);
		Thread.sleep(1000);
		fncVerify_DataDeleted_Successfully("IPulse_ValidationMessage_Div", "DocumentsNotes: 'NotesRemarks'");
	}catch(Throwable t){
		isTestCasePass = false;
		throw new Exception(Throwables.getStackTraceAsString(t));	
	}
}



@Test(dependsOnMethods={"EditResource_DocumentsNotesTab_NotesRemarksSection_Add_Edit_and_Delete_Records_and_Verify_Records_Save_and_Delete_Successfully"}, priority = 14, description="Step1. [Documents/Notes tab>>General Documents > Add the document and then delete it.   Expected<Record Deleted Successfully should come.>]" )
public void EditResource_DocumentsNotesTab_GeneralDocumentsSection_AddDelete_Records_and_Verify_Records_Save_and_Delete_Successfully() throws Throwable{
	fnsApps_Report_Logs("=========================================================================================================================================");
	fnsApps_Report_Logs("######### Test Case ::14 EditResource_DocumentsNotesTab_GeneralDocuments_AddDelete_Records_and_Verify_Records_Save_and_Delete_Successfully");
	try{	
		fnsGet_Element_Enabled("EditResource_DocumentsNotesTab_GeneralDocuments_Add_Bttn");
		fnsWait_and_Click("EditResource_DocumentsNotesTab_GeneralDocuments_Add_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
	
		fncAssert_Contains(OR_Grip.getProperty("EditResource_DocumentsNotesTab_GeneralDocuments_AddPopup_Title"), "Add Resource Document", "General Documents: 'Add Resource Document' Popup is not getting opened");
	
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
	//	fnsGet_Element_Enabled("EditResource_DocumentsNotesTab_GeneralDocuments_AddPopup_BrowseFile"); // Not working in Chrome
		fncUploadFile("EditResource_DocumentsNotesTab_GeneralDocuments_AddPopup_BrowseFile", "'Add Resource Document' PopUp");
	
		fnsGet_Element_Enabled("EditResource_DocumentsNotesTab_GeneralDocuments_AddPopup_Type_DD_Click");
		fnsDD_value_Select_By_MatchingText_DownKeyPress("EditResource_DocumentsNotesTab_GeneralDocuments_AddPopup_Type_DD_Click", "EditResource_DocumentsNotesTab_GeneralDocuments_AddPopup_Type_DD_Value", "li", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "GeneralDocuments_AddPopup_Type_DD"));
		
		fnsGet_Element_Enabled("EditResource_DocumentsNotesTab_GeneralDocuments_AddPopup_SaveClose_Bttn");
		fnsWait_and_Click("EditResource_DocumentsNotesTab_GeneralDocuments_AddPopup_SaveClose_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(3);
	
		fncVerify_DataUpdated_Successfully("IPulse_ValidationMessage_Div", "DocumentsNotes: 'General Documents'");
		
		fnsGet_Element_Enabled("EditResource_DocumentsNotesTab_GeneralDocuments_Delete_Bttn");
		fnsWait_and_Click("EditResource_DocumentsNotesTab_GeneralDocuments_Delete_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(3);
			
		fnsGet_Element_Enabled("EditResource_DocumentsNotesTab_GeneralDocuments_Delete_YES_Bttn");
		fnsWait_and_Click("EditResource_DocumentsNotesTab_GeneralDocuments_Delete_YES_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(3);
		//Thread.sleep(1000);
		fncVerify_DataDeleted_Successfully("IPulse_ValidationMessage_Div", "DocumentsNotes: 'General Documents'");
	}catch(Throwable t){
		isTestCasePass = false;
		throw new Exception(Throwables.getStackTraceAsString(t));	
	}
}




@Test(dependsOnMethods={"EditResource_DocumentsNotesTab_GeneralDocumentsSection_AddDelete_Records_and_Verify_Records_Save_and_Delete_Successfully"}, priority = 15, description="Step1. [Documents/Notes tab>>General Documents > Add the document and then delete it.   Expected<Record Deleted Successfully should come.>]" )
public void EditResource_CVTab_Verify_DataSaveSuccessfully_and_PDF_Open_AfterClickingOn_GenerateResumeButton() throws Throwable{
	fnsApps_Report_Logs("=========================================================================================================================================");
	fnsApps_Report_Logs("##################### Test Case :::::15 EditResource_CVTab_Verify_PDF_Open_AfterClickingOn_GenerateResumeButton");
	
	try{
		String FileLocation = System.getProperty("user.home")+"\\Downloads";
		long AllFileSize_BeforeFileSave = FileUtils.sizeOfDirectory(new File(FileLocation));

		fncClicking_on_TAB("CV", "EditResource_CV_TAB", "EditResource_CvTab_GenerateResume_Bttn");
	
		fnsGet_Element_Enabled("EditResource_CvTab_Template_DD_Click");
		fnsDD_value_Select_By_MatchingText_DownKeyPress("EditResource_CvTab_Template_DD_Click", "EditResource_CvTab_Template_DD_Value", "li", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Template_DD"));
	
		fnsGet_Element_Enabled("EditResource_CvTab_BiographicalSketch_Text");
		fnsWait_and_Clear("EditResource_CvTab_BiographicalSketch_Text");
		fnsWait_and_Type("EditResource_CvTab_BiographicalSketch_Text", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "BiographicalSketch")+" - "+fnsEditClient_Date_format());
	
		fnsGet_Element_Enabled("EditResource_CvTab_Education_Text");
		fnsWait_and_Clear("EditResource_CvTab_Education_Text");
		fnsWait_and_Type("EditResource_CvTab_Education_Text", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Education")+" - "+fnsEditClient_Date_format());
	
	
		fnsGet_Element_Enabled("EditResource_CvTab_Experience_Text");
		fnsWait_and_Clear("EditResource_CvTab_Experience_Text");
		fnsWait_and_Type("EditResource_CvTab_Experience_Text", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Experience")+" - "+fnsEditClient_Date_format());
	
		fnsGet_Element_Enabled("EditResource_CvTab_Skills_Text");
		fnsWait_and_Clear("EditResource_CvTab_Skills_Text");
		fnsWait_and_Type("EditResource_CvTab_Skills_Text", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Skills")+" - "+fnsEditClient_Date_format());
	
		fnsGet_Element_Enabled("EditResource_CvTab_AdditionalTrainingAndskills_Text");
		fnsWait_and_Clear("EditResource_CvTab_AdditionalTrainingAndskills_Text");
		fnsWait_and_Type("EditResource_CvTab_AdditionalTrainingAndskills_Text", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "AdditionalTrainingAndSkills")+" - "+fnsEditClient_Date_format());
	
		fnsGet_Element_Enabled("EditResource_CvTab_Publications_Text");
		fnsWait_and_Clear("EditResource_CvTab_Publications_Text");
		fnsWait_and_Type("EditResource_CvTab_Publications_Text", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Publications")+" - "+fnsEditClient_Date_format());
	
		
		fnsGet_Element_Enabled("EditResource_Save_Bttn");
		fnsWait_and_Click("EditResource_Save_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(3);
		
		fncVerify_DataUpdated_Successfully("IPulse_ValidationMessage_Div", "CV");
		
		fnsGet_Element_Enabled("EditResource_CvTab_GenerateResume_Bttn");
		fnsWait_and_Click("EditResource_CvTab_GenerateResume_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(5);
		//Thread.sleep(8000);
		
		
		
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