package nsf.ecap.Listings_Suite;

import java.sql.Connection;

import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;


public class BS_01_VerifyStandards extends TestSuiteBase_Listings {



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
		String Query_1 = "DELETE FROM NUCLEUS.TEMPLATE_FIELD_LOVS where create_user='TESTSCRIPTUSER'";
		String Query_2 = "DELETE FROM NUCLEUS.STD_LIST_HEAD where START_TEM_SEQ in ( select seq from TEMPLATES where create_user='TESTSCRIPTUSER' )";
		String Query_3 = "DELETE FROM NUCLEUS.TEMPLATES where create_user='TESTSCRIPTUSER'";
		String Query_4 = "DELETE FROM nucleus.cussx_status_history where cussx_seq in ( select seq from cus_std_xref where std_code in (select code from  NUCLEUS.standards where create_user='TESTSCRIPTUSER'))";
		/*String Query_5 = "DELETE FROM nucleus.cus_std_xref where std_code in (select code from  NUCLEUS.standards where create_user='TESTSCRIPTUSER')";*/
		String Query_6 = "DELETE FROM IQ_STD_DETAILS where create_user='TESTSCRIPTUSER'";
		String Query_7 = "DELETE FROM NUCLEUS.standards where create_user='TESTSCRIPTUSER'";		
		
		fnsDB_Fetch_or_Update_LoginTC_Query(Query_1, DBconnection);
		fnsDB_Fetch_or_Update_LoginTC_Query(Query_2, DBconnection);
		fnsDB_Fetch_or_Update_LoginTC_Query(Query_3, DBconnection);
		fnsDB_Fetch_or_Update_LoginTC_Query(Query_4, DBconnection);
		/*fnsDB_Fetch_or_Update_LoginTC_Query(Query_5, DBconnection);*/
		fnsDB_Fetch_or_Update_LoginTC_Query(Query_6, DBconnection);
		try{
			fnsDB_Fetch_or_Update_LoginTC_Query(Query_7, DBconnection);
		}catch(Throwable t){
			//nothing to do
		}
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
		fnsApps_Report_Logs("################################## [BS-01]  Verify Standards");
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
public void Create_Standard_then_AddListingTemplates_and_AddListingHeadings() throws Throwable{
	fnsApps_Report_Logs("################### Test Case ::1 Create_Standard_then_AddListingTemplates_and_AddListingHeadings ");
	try{	
		fnsGet_Element_Enabled("SideBarLink_SearchStandard_Link");
		fnsWait_and_Click("SideBarLink_SearchStandard_Link");
		fnsLoading_Progressing_wait(1);		
		fnsNavigation_Verify_Application_Navigation_fromHeaderText("'Search Standards' link", "Search Standards");
		
		
		fnsGet_Element_Enabled("SearchStandard_CreateStandard_Button");
		fnsWait_and_Click("SearchStandard_CreateStandard_Button");
		fnsLoading_Progressing_wait(1);		
		fnsNavigation_Verify_Application_Navigation_fromHeaderText("'Create Standard' button", "Create Standard");
		
		fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(true, "input", "Code", fnsTime_Return_DemandedFormatCalendar("ddHHmmss", 0, 0, 0, 0, 0));
		
		fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(true, "input", "Name", "Automation - Std");
		
		fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(true, "input", "Prog Code", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "ProgCode"));
		
		fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(true, "input", "Format Type", "1");
		
		fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(true, "input", "Heading", "Automation Test > BS-13 DateTime - "+fnsTime_Return_DemandedFormatCalendar("dd-MM-yyyy _ HH:mm", 0, 0, 0, 0, 0));
		
		fnsDD_Value_select_by_PassingDDLabelName("Std Type", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "StdType"));
		
		fnsDD_Value_select_by_PassingDDLabelName("Std Status", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "StdStatus"));
		
		fnsGet_Element_Enabled("CreateStandard_SAVE_Button");
		fnsWait_and_Click("CreateStandard_SAVE_Button");
		fnsLoading_Progressing_wait(2);	
	
		
		for(int i=0; i<=120; i++){
			if(driver.findElements(By.xpath(OR_Listings.getProperty("ViewStandard_Edit_Button"))).size()>0){
				fnsApps_Report_Logs("PASSED == Standard is created successfully.");
				break;
			}else if(driver.findElements(By.xpath(OR_Listings.getProperty("CreateStandard_Error_Div"))).size()>0){
				String Error = fnsGet_Field_TEXT("CreateStandard_Error_Div");
				fnsTake_Screen_Shot("StandardCreate_Fail");
				throw new Exception("FAILED == Error < "+Error+" > is coming after clicking on save button, Please refer the screenshot >> StandardCreate_Fail"+fnsScreenShot_Date_format());
			}else{
				Thread.sleep(1000);
			}
		}
	
		fnsGet_Element_Enabled("ViewStandard_Edit_Button");
		fnsWait_and_Click("ViewStandard_Edit_Button");
		fnsLoading_Progressing_wait(2);	
		
		String FieldType_fromExcel = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "AddListingTemplates_FieldType").trim();
		String FieldTypeSet[] = FieldType_fromExcel.split(",");
		
		String Start_End_Field = "";
		
		for(int i=0; i<FieldTypeSet.length; i++){
			String DspSeq = "";
			DspSeq = Integer.toString((i+1));
			Start_End_Field = fncAddListingTemplates(DspSeq, FieldTypeSet[i]);			
		}
		
		fnsGet_Element_Enabled("EditStandard_AddListingHeading_Tab_link");
		fnsWait_and_Click("EditStandard_AddListingHeading_Tab_link");
		fnsLoading_Progressing_wait(2);	
		
		fncAddListingHeading(Start_End_Field);
		
	}catch(Throwable t){
		isTestCasePass = false;
		fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
	
	
}




public String fncAddListingTemplates(String DspSeq, String FieldType) throws Throwable{
	String Heading = "Automation-"+fnsTime_Return_DemandedFormatCalendar("MMddyyyy", 0, 0, 0, 0, 0);
	try{
		fnsGet_Element_Enabled("EditStandard_AddListingField_Link");
		fnsWait_and_Click("EditStandard_AddListingField_Link");
		fnsLoading_Progressing_wait(3);	
		
		fnsVerify_PopupOpened_byTitle("'Add Listing Field' Link");
		
		
		String FieldName = "Automation-"+FieldType;
		fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(true, "input", "Dsp Seq", DspSeq);
		
		fnsDD_Value_select_by_PassingDDLabelName("Template Type", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "AddListingTemplates_TemplateType"));
				
		fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(true, "input", "Field Name", FieldName);
		
		fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(true, "input", "Field Heading", Heading);
		
		fnsDD_Value_select_by_PassingDDLabelName("Field Type", FieldType);		
		
		
		if(FieldType.equalsIgnoreCase("CHARARRAY")){
			fnsWait_and_Click_Through_JS(OR_Listings.getProperty("EditStandard_AddListingField_Popup_LOV_Checkbox"));
			
			fnsGet_Element_Enabled("EditStandard_AddListingField_Popup_LOV_Code_Input");
			fnsWait_and_Type("EditStandard_AddListingField_Popup_LOV_Code_Input", "A123");
			
			fnsGet_Element_Enabled("EditStandard_AddListingField_Popup_LOV_Description_Input");
			fnsWait_and_Type("EditStandard_AddListingField_Popup_LOV_Description_Input", "Automation");	
		}
		
		fnsGet_Element_Enabled("EditStandard_AddListingField_Popup_Save_Button");
		fnsWait_and_Click("EditStandard_AddListingField_Popup_Save_Button");
		fnsLoading_Progressing_wait(3);	
		
		
		fnsGet_Element_Enabled("EditStandard_ListingTemplates_RecordsTable");
		Thread.sleep(1000);
		String RecordsTable_Text = fnsGet_Field_TEXT("EditStandard_ListingTemplates_RecordsTable").toLowerCase().trim();
		
		try{
			assert RecordsTable_Text.contains(FieldName.toLowerCase()): "FAILED == After clicking on Save button, the template '"+FieldType+"' is not displayed under listing templates,  please refer the screen shot >> ListingTemplate_Fail"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED == Template '"+FieldType+"' is successfully added under the listing template.");
		}catch(Throwable t){
			fnsTake_Screen_Shot("ListingTemplate_Fail");
			throw new Exception(Throwables.getStackTraceAsString(t));	
		}		
		
	}catch(Throwable t){
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
	return Heading;
}








public void fncAddListingHeading(String Start_End_Field) throws Throwable{
	try{
		fnsGet_Element_Enabled("EditStandard_AddListingHeading_Link");
		fnsWait_and_Click("EditStandard_AddListingHeading_Link");
		fnsLoading_Progressing_wait(2);	
		
		fnsVerify_PopupOpened_byTitle("'Add Listing Heading' Link");
		
			
		fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(true, "input", "Line Number", "1");
		
		fnsDD_Value_select_by_PassingDDLabelName("Heading Type", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "AddListingHeading_HeadingType"));
			
		fnsDD_Value_select_by_PassingDDLabelName("Start Field", Start_End_Field);
		
		fnsDD_Value_select_by_PassingDDLabelName("End Field", Start_End_Field);			
		
		
		fnsGet_Element_Enabled("EditStandard_AddListingHeading_Popup_Save_Button");
		fnsWait_and_Click("EditStandard_AddListingHeading_Popup_Save_Button");
		fnsLoading_Progressing_wait(2);	
		
		
		fnsGet_Element_Enabled("EditStandard_ListingHeading_RecordsTable");
		Thread.sleep(1000);
		String RecordsTable_Text = fnsGet_Field_TEXT("EditStandard_ListingHeading_RecordsTable").toLowerCase().trim();
		
		try{
			assert RecordsTable_Text.contains(Start_End_Field.toLowerCase()): "FAILED == After clicking on Save button, the template is not displayed under listing Heading,  please refer the screen shot >> ListingHeading_Fail"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED == Template is successfully added under the listing Heading.");
		}catch(Throwable t){
			fnsTake_Screen_Shot("ListingHeading_Fail");
			throw new Exception(Throwables.getStackTraceAsString(t));	
		}		
		
	}catch(Throwable t){
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