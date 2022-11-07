package nsf.ecap.Listings_Suite;

import java.sql.Connection;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;


public class BS_03_VerifyListings extends TestSuiteBase_Listings {

public String ClientID = ""; 
public String Standard = ""; 
public String AddTestGroup_Popup_Code_1 = "";
public String AddTestGroup_Popup_Code_2 = "";

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
		String user ="TESTSCRIPTUSER";
		DBconnection = fnpGetDBConnection(); 
		String Query_1 = "delete from nucleus.TEMPLATE_FIELD_DETAILS where create_user='"+user+"'";
		String Query_2 = "delete from nucleus.COMP_DETAILS where create_user='"+user+"'";
		String Query_3 = "delete from nucleus.compounds where create_user='"+user+"'";
		String Query_4 = "delete from nucleus.model_details where create_user='"+user+"'";
		String Query_5 = "delete from nucleus.models where create_user='"+user+"'";
		String Query_6 = "delete from nucleus.product_details where create_user='"+user+"'";
		String Query_7 = "delete from nucleus.products where create_user='"+user+"'";
		String Query_8 = "delete from nucleus.category_details where create_user='"+user+"'";
		String Query_9 = "delete  from nucleus.categories where create_user='"+user+"'";
		String Query_10 = "delete from nucleus.notes where create_user='"+user+"'";	
		
		fnsDB_Fetch_or_Update_LoginTC_Query(Query_1, DBconnection);
		fnsDB_Fetch_or_Update_LoginTC_Query(Query_2, DBconnection);
		fnsDB_Fetch_or_Update_LoginTC_Query(Query_3, DBconnection);
		fnsDB_Fetch_or_Update_LoginTC_Query(Query_4, DBconnection);
		fnsDB_Fetch_or_Update_LoginTC_Query(Query_5, DBconnection);
		fnsDB_Fetch_or_Update_LoginTC_Query(Query_6, DBconnection);
		fnsDB_Fetch_or_Update_LoginTC_Query(Query_7, DBconnection);
		fnsDB_Fetch_or_Update_LoginTC_Query(Query_8, DBconnection);
		fnsDB_Fetch_or_Update_LoginTC_Query(Query_9, DBconnection);
		fnsDB_Fetch_or_Update_LoginTC_Query(Query_10, DBconnection);
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
	ClientID = "0$A01";
	Standard = "0MP";
	AddTestGroup_Popup_Code_1 = fnsTime_Return_DemandedFormatCalendar("MMddHHmm", 0, 0, 0, 0, 0);
	AddTestGroup_Popup_Code_2 = fnsTime_Return_DemandedFormatCalendar("MMddHHmm", 0, 0, 0, -1, 0);
	try{
		fnsApps_Report_Logs("################################## [BS-03]  Verify Listings");
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
public void Verify_AddDelete_WorkingFine_Under_TestGroup_and_EvaluationPerformanceInfo_Sections() throws Throwable{
	fnsApps_Report_Logs("################### Test Case ::2 Verify_AddDelete_WorkingFine_Under_TestGroup_and_EvaluationPerformanceInfo_Sections ");
	try{	
		fnsGet_Element_Enabled("SideBarLink_SearchListings_Link");
		fnsWait_and_Click("SideBarLink_SearchListings_Link");
		fnsLoading_Progressing_wait(1);	
		fnsLoading_Progressing_wait(2);
		fnsNavigation_Verify_Application_Navigation_fromHeaderText("'Search Listing' link", "Search Listing");
		
		fnsGet_Element_Enabled("SearchListings_Client_Filter");
		fnsWait_and_Type("SearchListings_Client_Filter", ClientID);
		fnsLoading_Progressing_wait(2);
		
		fnsGet_Element_Enabled("SearchListings_Standard_Filter");
		fnsWait_and_Type("SearchListings_Standard_Filter", Standard);
		fnsLoading_Progressing_wait(2);
		
		fnsWait_Click_on_First_Visible_Element_if_More_than_One_Coming("'"+ClientID+"' link", "//a[text()='"+ClientID+"']");
		fnsLoading_Progressing_wait(2);		
		
		fnsNavigation_Verify_Application_Navigation_fromHeaderText("'"+ClientID+"' link", "View Listing");
		
		fnsGet_Element_Enabled("ViewListings_Edit_Button");
		fnsWait_and_Click("ViewListings_Edit_Button");
		fnsLoading_Progressing_wait(2);		
		fnsLoading_Progressing_wait(1);
		fnsLoading_Progressing_wait(1);
		
		
		if(driver.findElements(By.xpath(OR_Listings.getProperty("EditListings_TestGroups_EvaluationPerformanceInfos_Delete_Icon"))).size()>0){
			Integer Delete_Button_Size = driver.findElements(By.xpath(OR_Listings.getProperty("EditListings_TestGroups_EvaluationPerformanceInfos_Delete_Icon"))).size();
			for(int i=Delete_Button_Size; i>=1; i--){
				fnsLoading_Progressing_wait(2);	
				Actions act1 = new Actions(driver);				
				act1.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).build().perform();
				String Deletebutton_Xpath  ="("+OR_Listings.getProperty("EditListings_TestGroups_EvaluationPerformanceInfos_Delete_Icon")+")["+i+"]";
				TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(Deletebutton_Xpath);
				TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(Deletebutton_Xpath);
				fnsLoading_PageLoad(2);
				
				if(driver.findElements(By.xpath(OR_Listings.getProperty("EditListings_TestGroups_EvaluationPerformanceInfos_Confirmation_Delete_Button"))).size()==0){
					TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(Deletebutton_Xpath);
					TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(Deletebutton_Xpath);
					fnsLoading_PageLoad(2);
				}
				
				fnsGet_Element_Enabled("EditListings_TestGroups_EvaluationPerformanceInfos_Confirmation_Delete_Button");
				fnsWait_and_Click("EditListings_TestGroups_EvaluationPerformanceInfos_Confirmation_Delete_Button");
				fnsLoading_Progressing_wait(2);					
			}
		}
		
		
		fnsGet_Element_Enabled("EditListings_AddTestGroup_Link");
		fnsWait_and_Click("EditListings_AddTestGroup_Link");
		fnsLoading_Progressing_wait(2);		
		
		fnsVerify_PopupOpened_byTitle("'Add Test Group' Link");
		
		fnsGet_Element_Enabled("EditListings_AddTestGroup_Popup_Code_Input");
		fnsWait_and_Type("EditListings_AddTestGroup_Popup_Code_Input", AddTestGroup_Popup_Code_1);
		fnsLoading_Progressing_wait(2);
		
		
		fnsDD_Value_select_by_PassingDDLabelName("Type", "FAMILY");	
		
		fnsGet_Element_Enabled("EditListings_AddTestGroup_Popup_SaveAddAnotherTestGroup_button");
		fnsWait_and_Click("EditListings_AddTestGroup_Popup_SaveAddAnotherTestGroup_button");
		fnsLoading_Progressing_wait(2);	
		
		
		fnsGet_Element_Enabled("EditListings_AddTestGroup_Popup_Code_Input");
		fnsWait_and_Type("EditListings_AddTestGroup_Popup_Code_Input", AddTestGroup_Popup_Code_2);
		fnsLoading_Progressing_wait(2);
		
		
		fnsDD_Value_select_by_PassingDDLabelName("Type", "MONITOR");	
		
		fnsGet_Element_Enabled("EditListings_AddTestGroup_Popup_Add_button");
		fnsWait_and_Click("EditListings_AddTestGroup_Popup_Add_button");
		fnsLoading_Progressing_wait(2);	
		
		
		fnsGet_Element_Enabled("EditListings_TestGroups_Table");		
		List<WebElement> Row_Object = fnsGet_OR_Listings_ObjectX("EditListings_TestGroups_Table").findElements(By.tagName("tr"));		
		try{
			assert (Row_Object.size()>=2) : "FAILED == Records are not added under 'Test Groups' section, please refer the screenshot >> Records_Not_Added"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED == Records are added under 'Test Group'.");
		}catch(Throwable t){
			fnsTake_Screen_Shot("Records_Not_Added");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		
		
		
		
		fnsGet_Element_Enabled("EditListings_AddEvaluationPerformanceInfo_Link");
		fnsWait_and_Click("EditListings_AddEvaluationPerformanceInfo_Link");
		fnsLoading_Progressing_wait(2);		
		
		fnsVerify_PopupOpened_byTitle("'Add Evaluation Performance Info' Link");
		
		fnsDD_Value_select_by_PassingDDLabelName("Type", "EVALUATION");	
		
		fnsGet_Element_Enabled("EditListings_AddEvaluationPerformanceInfo_Popup_Date_Calendar_Icon");
		fnsWait_and_Click("EditListings_AddEvaluationPerformanceInfo_Popup_Date_Calendar_Icon");
		fnsLoading_Progressing_wait(2);	
		
		fnsGet_Element_Enabled("EditListings_AddEvaluationPerformanceInfo_Popup_Calendar_TodayDate");
		fnsWait_and_Click("EditListings_AddEvaluationPerformanceInfo_Popup_Calendar_TodayDate");
		fnsLoading_Progressing_wait(2);	
		
		fnsGet_Element_Enabled("EditListings_AddEvaluationPerformanceInfo_Popup_SaveAddAnotherEvaluationPerformanceInfo_button");
		fnsWait_and_Click("EditListings_AddEvaluationPerformanceInfo_Popup_SaveAddAnotherEvaluationPerformanceInfo_button");
		fnsLoading_Progressing_wait(2);	
		
		
		fnsDD_Value_select_by_PassingDDLabelName("Type", "PERFORMANCE");	
		
		
		fnsGet_Element_Enabled("EditListings_AddEvaluationPerformanceInfo_Popup_Date_Calendar_Icon");
		fnsWait_and_Click("EditListings_AddEvaluationPerformanceInfo_Popup_Date_Calendar_Icon");
		fnsLoading_Progressing_wait(2);	
		
		fnsGet_Element_Enabled("EditListings_AddEvaluationPerformanceInfo_Popup_Calendar_TodayDate");
		fnsWait_and_Click("EditListings_AddEvaluationPerformanceInfo_Popup_Calendar_TodayDate");
		fnsLoading_Progressing_wait(2);	
		
		
		fnsGet_Element_Enabled("EditListings_AddEvaluationPerformanceInfo_Popup_Add_button");
		fnsWait_and_Click("EditListings_AddEvaluationPerformanceInfo_Popup_Add_button");
		fnsLoading_Progressing_wait(2);	
		
		
		fnsGet_Element_Enabled("EditListings_EvaluationPerformanceInfos_Table");		
		Row_Object = fnsGet_OR_Listings_ObjectX("EditListings_EvaluationPerformanceInfos_Table").findElements(By.tagName("tr"));		
		try{
			assert (Row_Object.size()>=2) : "FAILED == Records are not added under 'Add Evaluation Performance Info' section, please refer the screenshot >> Records_Not_Added"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED == Records are added under 'Add Evaluation Performance Info'.");
		}catch(Throwable t){
			fnsTake_Screen_Shot("Records_Not_Added");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}catch(Throwable t){
		isTestCasePass = false;
		fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
	
	
}




@Test( priority = 3, dependsOnMethods={"Verify_AddDelete_WorkingFine_Under_TestGroup_and_EvaluationPerformanceInfo_Sections"}, description="")
public void Verify_Add_Edit_WorkingFine_Under_Categories_Tab() throws Throwable{
	fnsApps_Report_Logs("################### Test Case ::3 Verify_Add_Edit_WorkingFine_Under_Categories_Tab ");
	try{	
		
		fnsGet_Element_Enabled("EditListings_Categories_Tab");
		fnsWait_and_Click("EditListings_Categories_Tab");
		fnsLoading_Progressing_wait(2);	
		
		fnsGet_Element_Enabled("EditListings_Categories_AddCategories_Link");
		fnsWait_and_Click("EditListings_Categories_AddCategories_Link");
		fnsLoading_Progressing_wait(2);	
		
				
		fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(true, "input", "DSP Seq", "DSPSeq01");
		fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(true, "input", "Category Description", "Automation Test 01");
		
		fnsGet_Element_Enabled("EditListings_Categories_AddCategories_Popup_TG1_LookUp_button");
		fnsWait_and_Click("EditListings_Categories_AddCategories_Popup_TG1_LookUp_button");
		fnsLoading_Progressing_wait(2);	
		
		fnsGet_Element_Enabled("EditListings_Categories_AddCategories_Popup_TG1_LookUp_Radio_button");
		fnsWait_and_Click("EditListings_Categories_AddCategories_Popup_TG1_LookUp_Radio_button");
		fnsLoading_Progressing_wait(2);	
		
		/*fnsGet_Element_Enabled("EditListings_Categories_AddCategories_Popup_Add_button");
		fnsWait_and_Click("EditListings_Categories_AddCategories_Popup_Add_button");
		fnsLoading_Progressing_wait(2);
		fnsLoading_Progressing_wait(2);*/
		
		fnsGet_Element_Enabled("EditListings_EditCategories_AddCategoriesDetails_ProductType_input");
		fnsWait_and_Type("EditListings_EditCategories_AddCategoriesDetails_ProductType_input", "Automation - ProductType");

		fnsGet_Element_Enabled("EditListings_EditCategories_AddCategoriesDetails_MaterialType_input");
		fnsWait_and_Type("EditListings_EditCategories_AddCategoriesDetails_MaterialType_input", "Automation - MaterialType");
	
		fnsGet_Element_Enabled("EditListings_EditCategories_AddCategoriesDetails_TradeName_input");
		fnsWait_and_Type("EditListings_EditCategories_AddCategoriesDetails_TradeName_input", "Automation - TradeName");

		fnsGet_Element_Enabled("EditListings_EditCategories_AddCategoriesDetails_ProductStandard_input");
		fnsWait_and_Type("EditListings_EditCategories_AddCategoriesDetails_ProductStandard_input", "Automation - ProductStandard");
		
		
		fnsGet_Element_Enabled("EditListings_Categories_AddCategories_Popup_Add_button");
		fnsWait_and_Click("EditListings_Categories_AddCategories_Popup_Add_button");
		fnsLoading_Progressing_wait(2);	
		
		
		fnsGet_Element_Enabled("EditListings_Categories_Table");		
		List<WebElement> Row_Object = fnsGet_OR_Listings_ObjectX("EditListings_Categories_Table").findElements(By.tagName("tr"));		
		try{
			assert (Row_Object.size()==1) : "FAILED == Records are not added under 'Categories' Tab, please refer the screenshot >> Records_Not_Added"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED == Records are added under 'Categories' Tab.");
		}catch(Throwable t){
			fnsTake_Screen_Shot("Records_Not_Added");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		
		
		fnsGet_Element_Enabled("EditListings_Categories_Record_Edit_Icon");
		fnsWait_and_Click("EditListings_Categories_Record_Edit_Icon");
		fnsLoading_Progressing_wait(2);	
		
		fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(true, "input", "Category Description", "Edited Automation Test 01");
		
		fnsGet_Element_Enabled("EditListings_EditCategories_AddCategoriesDetails_Save_Button");
		fnsWait_and_Click("EditListings_EditCategories_AddCategoriesDetails_Save_Button");
		fnsLoading_Progressing_wait(2);	
		
		
		fnsGet_Element_Enabled("EditListings_Categories_Record_Expand_Icon");
		fnsWait_and_Click("EditListings_Categories_Record_Expand_Icon");
		fnsLoading_Progressing_wait(2);	
	
		
		fnsGet_Element_Enabled("EditListings_Categories_Record_Expand_AddProduct_Link");
		fnsWait_and_Click("EditListings_Categories_Record_Expand_AddProduct_Link");
		fnsLoading_Progressing_wait(2);	
		
		
		fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(true, "input", "DSP Seq", "123");
		fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(true, "input", "Product Description", "Automation Test 01");
		
		fnsGet_Element_Enabled("EditListings_Categories_Record_Expand_AddProductPopup_TG1_LookUp_button");
		fnsWait_and_Click("EditListings_Categories_Record_Expand_AddProductPopup_TG1_LookUp_button");
		fnsLoading_Progressing_wait(2);	
		
		fnsGet_Element_Enabled("EditListings_Categories_Record_Expand_AddProductPopup_TG1_LookUp_Radio_button");
		fnsWait_and_Click("EditListings_Categories_Record_Expand_AddProductPopup_TG1_LookUp_Radio_button");
		fnsLoading_Progressing_wait(2);	
		
		fnsGet_Element_Enabled("EditListings_Categories_Record_Expand_AddProductPopup_TG2_LookUp_button");
		fnsWait_and_Click("EditListings_Categories_Record_Expand_AddProductPopup_TG2_LookUp_button");
		fnsLoading_Progressing_wait(2);	
		
		fnsGet_Element_Enabled("EditListings_Categories_Record_Expand_AddProductPopup_TG2_LookUp_Radio_button");
		fnsWait_and_Click("EditListings_Categories_Record_Expand_AddProductPopup_TG2_LookUp_Radio_button");
		fnsLoading_Progressing_wait(2);	
		
	/*	fnsGet_Element_Enabled("EditListings_Categories_Record_Expand_AddProductPopup_Add_button");
		fnsWait_and_Click("EditListings_Categories_Record_Expand_AddProductPopup_Add_button");
		fnsLoading_Progressing_wait(2);
		fnsLoading_Progressing_wait(2);*/
		
		fnsGet_Element_Enabled("EditListings_Categories_Record_Expand_AddProductPopup_ProductDescription");
		fnsWait_and_Type("EditListings_Categories_Record_Expand_AddProductPopup_ProductDescription", "Automation - Product Description");
		
		fnsGet_Element_Enabled("EditListings_Categories_Record_Expand_AddProductPopup_Schedule");
		fnsWait_and_Click("EditListings_Categories_Record_Expand_AddProductPopup_Schedule");
		fnsLoading_Progressing_wait(2);
		fnsLoading_Progressing_wait(2);
		
		fnsGet_Element_Enabled("EditListings_Categories_Record_Expand_AddProductPopup_Schedule_AddMultipleValues_Link");
		fnsWait_and_Click("EditListings_Categories_Record_Expand_AddProductPopup_Schedule_AddMultipleValues_Link");
		fnsLoading_Progressing_wait(2);
		fnsLoading_Progressing_wait(2);
		
		fnsGet_Element_Enabled("EditListings_Categories_Record_Expand_AddProductPopup_Schedule_SeqNo");
		fnsWait_and_Type("EditListings_Categories_Record_Expand_AddProductPopup_Schedule_SeqNo", "1");
		
		fnsGet_Element_Enabled("EditListings_Categories_Record_Expand_AddProductPopup_Schedule_Value");
		fnsWait_and_Type("EditListings_Categories_Record_Expand_AddProductPopup_Schedule_Value", "Automation - Test");
		
		/*fnsGet_Element_Enabled("EditListings_Categories_Record_Expand_AddProductPopup_Save_Button");
		fnsWait_and_Click("EditListings_Categories_Record_Expand_AddProductPopup_Save_Button");
		fnsLoading_Progressing_wait(2);	*/
		
		fnsGet_Element_Enabled("EditListings_Categories_Record_Expand_AddProductPopup_Add_button");
		fnsWait_and_Click("EditListings_Categories_Record_Expand_AddProductPopup_Add_button");
		fnsLoading_Progressing_wait(2);
		fnsLoading_Progressing_wait(2);
		
		fnsGet_Element_Enabled("EditListings_Categories_Record_Expand_AddProduct_Table");		
		Row_Object = fnsGet_OR_Listings_ObjectX("EditListings_Categories_Record_Expand_AddProduct_Table").findElements(By.tagName("tr"));		
		try{
			assert (Row_Object.size()==2) : "FAILED == Records are not added under 'Add Product' Tab, please refer the screenshot >> Records_Not_Added"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED == Records are added under 'Add Product' Tab.");
		}catch(Throwable t){
			fnsTake_Screen_Shot("Records_Not_Added");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		
		
		fnsGet_Element_Enabled("EditListings_Categories_Product_Record_Edit_Icon");
		fnsWait_and_Click("EditListings_Categories_Product_Record_Edit_Icon");
		fnsLoading_Progressing_wait(2);	
	
		
		fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(true, "input", "Product Description", "Edited - Automation Test 01");
		
		
		fnsGet_Element_Enabled("EditListings_Categories_Record_Expand_AddProductPopup_Save_Button");
		fnsWait_and_Click("EditListings_Categories_Record_Expand_AddProductPopup_Save_Button");
		fnsLoading_Progressing_wait(2);	
		
		
		fnsGet_Element_Enabled("EditListings_Categories_Product_Record_Plus_Icon");
		fnsWait_and_Click("EditListings_Categories_Product_Record_Plus_Icon");
		fnsLoading_Progressing_wait(2);	
		
		fnsGet_Element_Enabled("EditListings_Categories_Product_Record_AddModels_Link");
		fnsWait_and_Click("EditListings_Categories_Product_Record_AddModels_Link");
		fnsLoading_Progressing_wait(2);	
		
		fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(true, "input", "DSP Seq", "4");
		fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(true, "input", "Model Description", "Automation Test 01");
		
		fnsGet_Element_Enabled("EditListings_Categories_Product_Record_AddModelsPopup_TG1_LookUp_button");
		fnsWait_and_Click("EditListings_Categories_Product_Record_AddModelsPopup_TG1_LookUp_button");
		fnsLoading_Progressing_wait(2);	
		
		fnsGet_Element_Enabled("EditListings_Categories_Product_Record_AddModelsPopup_TG1_LookUp_Radio_button");
		fnsWait_and_Click("EditListings_Categories_Product_Record_AddModelsPopup_TG1_LookUp_Radio_button");
		fnsLoading_Progressing_wait(2);	
		
		fnsGet_Element_Enabled("EditListings_Categories_Product_Record_AddModelsPopup_TG2_LookUp_button");
		fnsWait_and_Click("EditListings_Categories_Product_Record_AddModelsPopup_TG2_LookUp_button");
		fnsLoading_Progressing_wait(2);	
		
		fnsGet_Element_Enabled("EditListings_Categories_Product_Record_AddModelsPopup_TG2_LookUp_Radio_button");
		fnsWait_and_Click("EditListings_Categories_Product_Record_AddModelsPopup_TG2_LookUp_Radio_button");
		fnsLoading_Progressing_wait(2);	
		
		fnsGet_Element_Enabled("EditListings_Categories_Product_Record_AddModelsPopup_Add_button");
		fnsWait_and_Click("EditListings_Categories_Product_Record_AddModelsPopup_Add_button");
		fnsLoading_Progressing_wait(2);
		fnsLoading_Progressing_wait(2);
		
		fnsGet_Element_Enabled("EditListings_Categories_Product_Record_AddModels_Table");		
		Row_Object = fnsGet_OR_Listings_ObjectX("EditListings_Categories_Product_Record_AddModels_Table").findElements(By.tagName("tr"));		
		try{
			assert (Row_Object.size()==2) : "FAILED == Records are not added under 'Add Model' Section, please refer the screenshot >> Records_Not_Added"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED == Records are added under 'Add Model' Section.");
		}catch(Throwable t){
			fnsTake_Screen_Shot("Records_Not_Added");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		
		Actions act2 = new Actions(driver);				
		act2.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).build().perform();
		Thread.sleep(1500);
		
		
		fnsGet_Element_Enabled("EditListings_Categories_Product_Record_AddModels_Edit_Icon");
		fnsWait_and_Click("EditListings_Categories_Product_Record_AddModels_Edit_Icon");
		fnsLoading_Progressing_wait(2);	
	
		
		fnsWait_and_Type_or_OnlyVerify_Value_by_LabelName(true, "input", "Model Description", "Edited - Automation Test 01");
		
		
		fnsGet_Element_Enabled("EditListings_Categories_Product_Record_AddModelsPopup_Save_Button");
		fnsWait_and_Click("EditListings_Categories_Product_Record_AddModelsPopup_Save_Button");
		fnsLoading_Progressing_wait(2);	
	
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