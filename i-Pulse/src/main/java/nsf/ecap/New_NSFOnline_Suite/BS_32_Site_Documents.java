package nsf.ecap.New_NSFOnline_Suite;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import nsf.ecap.util.TestUtil;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;

public class BS_32_Site_Documents extends TestSuiteBase_New_NSFOnline {
	
	public int count = -1;
	public String Site_Document_View_Name_from_AdvanceSearch="Automation - Site Document";
	public String Site_document_Edit_ViewName="Automation - Site Document-Edited";
	public String Document_Title="Automation Document" + fnsReturn_CurrentTime();
	public String Document_Type_DD_Value="Ceiling Certificates";
	public String Document_Type_Customer_DD="C0036244";
	public String Doc_No=null;
	public String Doc_Expiry_Date=fnaReturn_CurrentTime();
	
	@Parameters({ "className" })
	@BeforeTest
	public void checkTestSkip(String className) throws Exception {
		if (className.isEmpty()) {
				className = this.getClass().getSimpleName();
		}
		RunningClassName = className;
		Running_Class_BS_Description = "[BS-32] Verify Site Documents";
		IsBrowserPresentAlready =false;
		fnsCheckClassLevelTestSkip(className);
	}

	@Test( priority = 0)
	public void Launch_Browser_and_Successfully_Login_into_the_Site_Documents() throws Throwable{
		try{ 
			fnsApps_Report_Logs("################################## "+ Running_Class_BS_Description);
			if (!IsBrowserPresentAlready) {
				IsBrowserPresentAlready = fnsBrowserLaunchAndLogin(Login_UserName, Login_Password);
				fnsSwitchAcoount_MultiAccess(RunningClassName, "SwitchAccount_DD");	
			}
			}catch(Throwable t){
				isTestCasePass = false;
				throw new Exception(Throwables.getStackTraceAsString(t));
			}
		fnsLoading_Progressing_wait(1);
			fnsApps_Report_Logs("=========================================================================================================================================");
		}
	
	
	@Test( priority = 1, dependsOnMethods={"Launch_Browser_and_Successfully_Login_into_the_Site_Documents"}, description="")
	public void Verify_Create_Submit_Inreview_Accepted_And_Add_Versions_In_Site_Document () throws Exception{
		fnsApps_Report_Logs("################### Test Case :: Verify_Create_Submit_Inreview_Accepted_And_Add_Versions_In_Site_Document");
		try{
			
			fncMenu_Ajax_Link_Click_By_PassingAjaxPath("Site_Document_Ajax");
			fnsLoading_Progressing_wait(1);
			
			fncNavigation_Verify_Application_Navigated_To_ViewScreen("'Site Documents' Menu Ajax", "Site Documents");
			
			fnsGet_Element_Enabled("Site_Document_Create_Document_button");
			fnsWait_and_Click("Site_Document_Create_Document_button");
			fnsLoading_Progressing_wait(1);
			
			fnsWait_and_Type("Site_Document_Document_Title_input", Document_Title);
			fnaDD_value_Select_TagOPTION_DDTypeSelect("Site_Document_Document_Type_DD", Document_Type_DD_Value, 60);
			fnaDD_value_Select_In_Suggestion_Search("Site_Document_Choose_Customer_DD", Document_Type_Customer_DD , 60 );
			
			fnsGet_Element_Enabled("Site_Document_Create_Document_Button");
			fnsWait_and_Click("Site_Document_Create_Document_Button");
		//	fnsLoading_Progressing_on_Popup_wait_for_Popup(1);
			
			fns_Verify_Success_message_coming_OR_Error_message_Coming(true, 60, "Document Created Successfully", 20);
	
			List<WebElement> ele = driver.findElements(By.xpath("//div[@class='col-xs-12 col-sm-12 col-md-12 col-lg-12 padding0']/div[2]"));
				for (WebElement Label_Elements : ele) {
					String label_text = Label_Elements.getText().trim();
					if(label_text.contains("D000")){
						fnsApps_Report_Logs("Document No. "+label_text+" Created Successfully.");
						Doc_No = label_text.substring(0, label_text.length() - 3);
						fnsApps_Report_Logs(Doc_No);
						break;
					}else{
						// nothing to do
					}
				}
				
			fnaDD_value_Select_TagOPTION_DDTypeSelect("Site_Document_Status_DD", "SUBMITTED", 2);
			
			fna_UploadFile("Site_Document_Upload_Files_Button");
			fnsLoading_Progressing_wait(1);
							
			fnsGet_Element_Enabled("Site_Document_Save_Button");
			fnsWait_and_Click("Site_Document_Save_Button");
			//fnsLoading_Progressing_on_Popup_wait_for_Popup(1);
			
			fns_Verify_Success_message_coming_OR_Error_message_Coming(true, 60, "Document Uploaded Successfully !", 20);
			
			fnsGet_Element_Enabled("Site_Document_Edit_Button");
			fnsWait_and_Click("Site_Document_Edit_Button");
			fnsLoading_Progressing_wait(1);
			
			fnaDD_value_Select_TagOPTION_DDTypeSelect("Site_Document_Status_DD", "INREVIEW", 2);
			fnsLoading_Progressing_wait(1);
			
			fnsGet_Element_Enabled("Site_Document_Doc_Expiry_Date");
			fnsWait_and_Clear("Site_Document_Doc_Expiry_Date");
			fnsWait_and_Type("Site_Document_Doc_Expiry_Date", Doc_Expiry_Date);
			
			fnsGet_Element_Enabled("Site_Document_Save_Button");
			fnsWait_and_Click("Site_Document_Save_Button");
		//	fnsLoading_Progressing_on_Popup_wait_for_Popup(1);
			
			fns_Verify_Success_message_coming_OR_Error_message_Coming(true, 60, "Document Updated Successfully", 20);
			fnsLoading_Progressing_wait(1);
			
			fnsGet_Element_Enabled("Site_Document_Edit_Button");
			fnsWait_and_Click("Site_Document_Edit_Button");
			fnsLoading_Progressing_wait(1);
						
			fnaDD_value_Select_TagOPTION_DDTypeSelect("Site_Document_Status_DD", "ACCEPTED", 2);
		
			fnsGet_Element_Enabled("Site_Document_Doc_Expiry_Date");
			fnsWait_and_Clear("Site_Document_Doc_Expiry_Date");
			fnsWait_and_Type("Site_Document_Doc_Expiry_Date", Doc_Expiry_Date);
			
			fnsGet_Element_Enabled("Site_Document_Save_Button");
			fnsWait_and_Click("Site_Document_Save_Button");
			fnsLoading_Progressing_on_Popup_wait_for_Popup(1);
						
			fnsGet_Element_Enabled("Site_Document_Add_Version_Button");
			fnsWait_and_Click("Site_Document_Add_Version_Button");
			fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
			
			fnsGet_Element_Enabled("Site_Document_Add_Version_PoPup_Yes_Button");
			fnsWait_and_Click("Site_Document_Add_Version_PoPup_Yes_Button");
			fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
			
			fnsGet_Element_Enabled("Site_Document_Doc_Expiry_Date");
			fnsWait_and_Clear("Site_Document_Doc_Expiry_Date");
			fnsWait_and_Type("Site_Document_Doc_Expiry_Date", Doc_Expiry_Date);
			
			fnsGet_Element_Enabled("Site_Document_Save_Button");
			fnsWait_and_Click("Site_Document_Save_Button");
		//	fnsLoading_Progressing_on_Popup_wait_for_Popup(4);
			
			fns_Verify_Success_message_coming_OR_Error_message_Coming(true, 60, "Version Created Successfully", 20);
			fnsLoading_Progressing_wait(1);
			
			fnsGet_Element_Enabled("Site_Document_version_tab");
			fnsWait_and_Click("Site_Document_version_tab");
			fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
			
			
			fncClickonDocVersionfromViewResultTable_n_ReturnBackonViewScreen("Site Documents", "Doc.Version", 2);
			
			WebElement element = driver.findElement(By.xpath("//div[@class='sample_doc doc-sam']/span[text()='This is test pdf document.pdf']"));
			
			String text = element.getText();
			
			assert text.equalsIgnoreCase("This is test pdf document.pdf"): "Failed: Attachment name is diffrent or attachment is not available.";

			fnsGet_Element_Enabled("Site_Document_Add_Version_Button");
			fnsWait_and_Click("Site_Document_Add_Version_Button");
			fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
			
			fnsGet_Element_Enabled("Site_Document_Add_Version_PoPup_No_Button");
			fnsWait_and_Click("Site_Document_Add_Version_PoPup_No_Button");
			fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
			
			fna_UploadFile("Site_Document_Upload_Files_Button");
			fnsLoading_Progressing_wait(1);
			
			fnsGet_Element_Enabled("Site_Document_Doc_Expiry_Date");
			fnsWait_and_Clear("Site_Document_Doc_Expiry_Date");
			fnsWait_and_Type("Site_Document_Doc_Expiry_Date", Doc_Expiry_Date);
			
			fnsGet_Element_Enabled("Site_Document_Save_Button");
			fnsWait_and_Click("Site_Document_Save_Button");
			//fnsLoading_Progressing_on_Popup_wait_for_Popup(4);
			
			fns_Verify_Success_message_coming_OR_Error_message_Coming(true, 60, "Document Uploaded Successfully !", 20);
			fnsLoading_Progressing_wait(1);
			
			fnsGet_Element_Enabled("Site_Document_version_tab");
			fnsWait_and_Click("Site_Document_version_tab");
			fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
			
			
			fncClickonDocVersionfromViewResultTable_n_ReturnBackonViewScreen("Site Documents", "Doc.Version", 3);
			
			
			fncMenu_Ajax_Link_Click_By_PassingAjaxPath("Site_Document_Ajax");
			fnsLoading_Progressing_wait(1);
			
			fncVerify_View_Display_Open_and_Delete_it(2, Site_Document_View_Name_from_AdvanceSearch, "View_Delete_Link", "View_Remove_Link");
			
			fnsGet_Element_Enabled("View_AdvanceSearch_Bttn");
			fnsWait_and_Click("View_AdvanceSearch_Bttn");
			fnsLoading_Progressing_wait(2);
			
			
			fnsGet_Element_Enabled("Site_Document_Document_Number_input");
			fnsWait_and_Type("Site_Document_Document_Number_input", Doc_No);
			fnsLoading_Progressing_wait(2);
			
			fnsGet_Element_Enabled("Site_Document_AdvanceSearch_Button");
			fnsWait_and_Click("Site_Document_AdvanceSearch_Button");
			fnsLoading_Progressing_wait(2);
			
			Integer Site_Document_AdvanceSearch_Total_no_of_Records_Count = fnsReturn_View_Results_Reords_Total_Count("Advance Search" , "View_Result_Table", 10);
			
			fnsGet_Element_Enabled("AdvanceSearch_SaveAsView");
			fnsWait_and_Click("AdvanceSearch_SaveAsView");
			fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
			
			fnsGet_Element_Enabled("AdvanceSearch_SaveAsView_PopUpBox");
			fnsWait_and_Click("AdvanceSearch_SaveAsView_PopUpBox");
			fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
			
			fnsGet_Element_Enabled("CreateView_Step1_ViewName");
			fnsWait_and_Clear("CreateView_Step1_ViewName");
			fnsWait_and_Type("CreateView_Step1_ViewName", Site_Document_View_Name_from_AdvanceSearch);
			fnsLoading_Progressing_wait(2);
			
			fnsGet_Element_Enabled("CreateView_CreateView_Bttn");
			fnsWait_and_Click("CreateView_CreateView_Bttn");
			fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  60, "A new view has been added to your list", 25);
			
			
			Integer Site_Document_Total_no_of_Records_Count = fnsReturn_View_Results_Reords_Total_Count("Documents" , "View_Result_Table", 20);
			
			try{
				assert Site_Document_Total_no_of_Records_Count.equals(Site_Document_AdvanceSearch_Total_no_of_Records_Count):"FAILED == Advance Search results count <"+Site_Document_AdvanceSearch_Total_no_of_Records_Count+"> and VIEW Created-From-Advance-Search count <"+
						Site_Document_Total_no_of_Records_Count+"> are not matched, please refer screen shot >> Count_Mismatch"+fnsScreenShot_Date_format();
				fnsApps_Report_Logs("PASSED == Advance Search results count <"+Site_Document_AdvanceSearch_Total_no_of_Records_Count+"> and VIEW Created-From-Advance-Search count <"+ Site_Document_Total_no_of_Records_Count +"> are matched.");
			}catch(Throwable t){
				fnsTake_Screen_Shot("Count_Mismatch");
				throw new Exception(Throwables.getStackTraceAsString(t));
			}
			
			fncClickonFirstFacilityfromViewResultTable_n_ReturnBackonViewScreen("Site Documents", "Document No");
			
			
			fnsGet_Element_Enabled("Edit_View_Link_2");
			fnsWait_and_Click("Edit_View_Link_2");
			fnsLoading_Progressing_wait(1);
			
			fnsGet_Element_Enabled("CreateView_Step1_ViewName");
			fnsLoading_Progressing_wait(1);
			fnsWait_and_Clear("CreateView_Step1_ViewName");
			fnsWait_and_Type("CreateView_Step1_ViewName", Site_document_Edit_ViewName);				
			
			fnsGet_Element_Enabled("CreateView_Step1_Alert_CheckBox");
			fnsWait_and_Click("CreateView_Step1_Alert_CheckBox");
			fnsLoading_Progressing_wait(1);			
			
			fnsGet_Element_Enabled("CreateView_Step1_Alert_CheckBox_AssignColor_RED_Radio_bttn");
			fnsWait_and_Click("CreateView_Step1_Alert_CheckBox_AssignColor_RED_Radio_bttn");
			fnsLoading_Progressing_wait(1);
			
			fnsGet_Element_Enabled("CreateView_CreateView_Bttn");
			fnsWait_and_Click("CreateView_CreateView_Bttn");
			
			fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  60, "View has been updated successfully", 25);

			fns_AlertScreen_Click_on_AlertMenu_and_Verify_View_and_Label_Present(true, true, "Site Documents", Site_document_Edit_ViewName);
			
			fncVerify_View_Display_Open_and_Delete_it(2, Site_document_Edit_ViewName, "View_Delete_Link", "View_Remove_Link");

		}catch(Throwable t){
			isTestCasePass = false;
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}
		
	//***************************************************************************************************************************************
	
	@AfterMethod
	public void reportDataSetResult() {
		if(count>-1){
			if (skip)
				TestUtil.reportDataSetResult(New_NSFOnline_Suitexls, this.getClass().getSimpleName(), count + 2 , "SKIP");
			else if (fail) {
				isTestCasePass = false;
				TestUtil.reportDataSetResult(New_NSFOnline_Suitexls, this.getClass().getSimpleName(), count + 2, "FAIL");
			} else
				TestUtil.reportDataSetResult(New_NSFOnline_Suitexls, this.getClass().getSimpleName(), count + 2, "PASS");
	
			skip = false;
			fail = false;
		}
	}
	
	
	 
	@AfterTest
	public void reportTestResult() throws Throwable {
		TestSuiteBase_MonitorPlan.fns_ReportTestResult_atClassLevel(New_NSFOnline_Suitexls,  (this.getClass().getSimpleName()) , isTestCasePass);
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
		return TestUtil.getData(New_NSFOnline_Suitexls, this.getClass().getSimpleName());
	}

	
//########################################################Class Method###############################################################################

public void fncClickonDocVersionfromViewResultTable_n_ReturnBackonViewScreen(String View_Home_ScreenName, String Link_Column_Name_From_DataTitle, int Doc_version_count) throws Throwable{
	try{
		
		String Link_Text = null;
		Integer Version_no = Doc_version_count;
		Integer Link_Column_Number = fnsReturn_ColumnNumber_from_TableHeaderXpath_WithoutOR(OR_New_NSFOnline.getProperty("View_Result_TableHeader"), Link_Column_Name_From_DataTitle);
		String First_Row_MatchingColumn_Xpath = OR_New_NSFOnline.getProperty("View_Result_Table")+"/tr["+Version_no+"]/td["+Link_Column_Number+"]";
		for(int i=0; i<=NewNsfOnline_Element_Max_Wait_Time; i++){
			if(driver.findElements(By.xpath(First_Row_MatchingColumn_Xpath)).size()>0){
				break;
			}else{
				Thread.sleep(100);
			}
			if(i==NewNsfOnline_Element_Max_Wait_Time){
				throw new Exception ("FAILED == Records are not coming into the view on '"+View_Home_ScreenName+"' screen, please refer screen shot >> Opening_First_Facility_and_ReturnBack_to_View_FAIL"+fnsScreenShot_Date_format()+"--Xpath >> "+First_Row_MatchingColumn_Xpath);
			}
		}
		List<WebElement> First_Row_td_Objects = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(First_Row_MatchingColumn_Xpath).findElements(By.tagName("a"));
		if(First_Row_td_Objects.size()==1){
			for(WebElement First_Row_td_Elements : First_Row_td_Objects){
				Link_Text = First_Row_td_Elements.getText().trim();
				First_Row_td_Elements.click();
				fnsApps_Report_Logs("PASSED == Click done on the '"+Link_Text+"' link");
				fnsLoading_Progressing_on_Popup_wait_for_Popup(4);
				break;
			}
		}else{
			throw new Exception("FAILED == More than one link is present into the cell row '"+ Version_no +"' of column '"+Link_Column_Name_From_DataTitle+"', please refer screen shot >> "+fnsScreenShot_Date_format());
		}
		
		fnsApps_Report_Logs("PASSED == Clicking on Doc version '"+ Version_no +"' of column '"+ Version_no +"' ");
fnsApps_Report_Logs("");
	}catch (Throwable t){
		//isTestCasePass = false;
		fnsTake_Screen_Shot("Clicking On Doc_Version");
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
	
}

//Function for Screen date format2 
public static String fnaReturn_CurrentTime() {
	Date date = new Date();
	SimpleDateFormat DateFor = new SimpleDateFormat("MM/dd/yyyy");
	String stringDate = DateFor.format(date);
	
	DateFor = new SimpleDateFormat("dd-MMM-yyyy");
	stringDate = DateFor.format(date);
	
	return stringDate;
}
	
}
