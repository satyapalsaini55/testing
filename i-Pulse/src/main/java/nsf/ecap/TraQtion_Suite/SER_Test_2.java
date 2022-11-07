package nsf.ecap.TraQtion_Suite;

import java.util.ArrayList;

import nsf.ecap.MonitorPlan_Suite.TestSuiteBase_MonitorPlan;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;


public class SER_Test_2 extends TestSuiteBase_TraQtion {

	public String Work_Order_NO = null;

@Parameters({ "className" })
@BeforeTest
public void checkTestSkip(String className) throws Exception {
	if (className.isEmpty()) {
		className = this.getClass().getSimpleName();
	}
	IsBrowserPresentAlready =false;
	fnsCheckClassLevelTestSkip(className);
}



//##########################################   EXCEL Variable ###############################################################################################




@Test( priority = 1)
public void Launch_Browser_and_Successfully_Login_into_the_Application() throws Throwable{
	try{		
		fnsApps_Report_Logs("################################## [BS-02]  SER Test");
		if (!IsBrowserPresentAlready) {
			IsBrowserPresentAlready = fnsTraQtionPortal_LaunchBrowserAndLogin("TraQtion Portal", Login_UserName, Login_Password);			
		}
	}catch(Throwable t){
		isTestCasePass = false;
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
	fnsApps_Report_Logs("=========================================================================================================================================");
}





@Test( priority = 2, dependsOnMethods={"Launch_Browser_and_Successfully_Login_into_the_Application"}, description="")
public void Create_SER_WorkOrder() throws Throwable{
	fnsApps_Report_Logs("################### Test Case ::1 Create_SER_WorkOrder ");
	try{
		
		fnsGet_Element_Enabled("TraQtionPortal_TraQtion_Button");
		fnsWait_and_Click("TraQtionPortal_TraQtion_Button");					
		
		for(int a=0; a<=120; a++){ 
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			Integer tabsCount = tabs.size();
			if(tabsCount==2){
				for (int i = 0; i < tabsCount; i++) {
					if( !( (TraQtionPortal_Window.equalsIgnoreCase(tabs.get(i))) ) ){
						driver.switchTo().window(tabs.get(i));
						fnsApps_Report_Logs("PASSED == Successfully Switch to 'TraQtion' Window, jump from 'TraQtion Portal' by clicking on TraQtion button.");
						for(int l=1; l<=120; l++){
							if(driver.findElements(By.xpath(OR_TraQtion.getProperty("TraQtionPortal_Acknowledge_bttn"))).size()>0){
								fnsGet_Element_Enabled("TraQtionPortal_Acknowledge_bttn");
								Thread.sleep(2000);
								fnsWait_and_Click("TraQtionPortal_Acknowledge_bttn");
								Thread.sleep(1000);
								break;
							}
							if(l==120){
								fnsTake_Screen_Shot("Acknowledge_Alert_Not_Coming");
								throw new Exception("FAILED == Acknowledge alert is not coming, after 120 Seconds wait, please refer the screen shot >> Acknowledge_Alert_Not_Coming"+fnsScreenShot_Date_format());
							}
						}
						break;
					}
				}
				break;
			}else{
				Thread.sleep(1000);
			}
			if(a==Long.parseLong(CONFIG.getProperty("ElementWaitTime"))){
				fnsTake_Screen_Shot("Switch_TraQtion_WindowFail");
				throw new Exception("FAILED == 'TraQtion' window is not getting open  by clicking on TraQtion button, after 120 Seconds wait, please refer the screen shot >> Switch_TraQtion_WindowFail"+fnsScreenShot_Date_format());
			}
		}
		

		fnsMenu_Ajax_Link_Click_By_PassingAjaxPath_TraQtion_WithoutOR("Search SER", OR_TraQtion.getProperty("TraQtion_SearchSER_Ajax") );
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
		
		fnsVerify_Application_Navigated_To_Next_Designated_Screen_WithoutOR("'Search SER' Menu link", "Search SER", "//div[contains(text(), 'Search SER')]");
		
		fnsGet_Element_Enabled("TraQtion_SearchSER_CreateSER_bttn");
		fnsWait_and_Click("TraQtion_SearchSER_CreateSER_bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		fnsVerify_Application_Navigated_To_Next_Designated_Screen_WithoutOR("'Create SER' button", "Create SER", "//div[contains(text(), 'Create SER')]");
		
				
		fnsGet_Element_Enabled("TraQtion_CreateSER_NewIngredient_CheckBox");
		fnsWait_and_Click("TraQtion_CreateSER_NewIngredient_CheckBox");
		
		fnsWait_and_Type("TraQtion_CreateSER_SER_Name", "Automation SER-"+TestSuiteBase_MonitorPlan.fnsReturn_Requried_cal("dd-MMM-yy"));
		
		fnsWait_and_Type("TraQtion_CreateSER_Franchisee", "PapaJhons");
		
		fnsWait_and_Type("TraQtion_CreateSER_Requested_By", "testscriptuser");
		
		fnsWait_and_Type("TraQtion_CreateSER_Requester_Email", "Automationtest@email.com");
		
		fnsWait_and_Type("TraQtion_CreateSER_ReasonforRequestChangeAddition", "Automation > BS-01 (SER TEST) - "+TestSuiteBase_MonitorPlan.fnsReturn_Requried_cal("dd-MMM-yy"));
		
		fnsDD_value_Select_By_MatchingText_DownKeyPress("TraQtion_CreateSER_Region_DD_Click", "TraQtion_CreateSER_Region_DD_List", "li", "ASIA");
		
		fnsGet_Element_Enabled("TraQtion_CreateSER_Market_LK_Bttn");
		fnsWait_and_Click("TraQtion_CreateSER_Market_LK_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		fnsGet_Element_Enabled("TraQtion_CreateSER_Market_LK_Text");
		fnsWait_and_Type("TraQtion_CreateSER_Market_LK_Text", "ASA-CHCO");
		TestSuiteBase_MonitorPlan.fnsLookup_RadioBttn_Select();
		
		
		fnsDD_value_Select_By_MatchingText_DownKeyPress("TraQtion_CreateSER_AssociatedCategory_DD_Click", "TraQtion_CreateSER_AssociatedCategory_DD_List", "li", "Beverages");
		
		fnsWait_and_Click("TraQtion_CreateSER_Continue_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		fnsGet_Element_Enabled("TraQtion_Message_Div");
		fnsVerify_Validation_Message_TraQtion();
		
		Work_Order_NO = fnsGet_Field_TEXT("TraQtion_Message_Div").split("created")[0];
		Work_Order_NO = Work_Order_NO.split("Order")[1].trim();
		
		fnsApps_Report_Logs("PASSED == Work Order '"+Work_Order_NO+"' is created successfully from Create SER screen.");
		
		
	}catch(Throwable t){
		isTestCasePass = false;
		fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
}




@Test( priority = 3, dependsOnMethods={"Create_SER_WorkOrder"}, description="")
public void Search_and_Open_NewllyCreatedSER_from_SerachSER_Screen() throws Throwable{
	fnsApps_Report_Logs("################### Test Case ::2 Search_and_Open_NewllyCreatedSER_from_SerachSER_Screen ");
	try{
		
		fnsMenu_Ajax_Link_Click_By_PassingAjaxPath_TraQtion_WithoutOR("Search SER", OR_TraQtion.getProperty("TraQtion_SearchSER_Ajax") );
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
		
		fnsVerify_Application_Navigated_To_Next_Designated_Screen_WithoutOR("'Search SER' Menu link", "Search SER", "//div[contains(text(), 'Search SER')]");
		
		fnsGet_Element_Enabled("TraQtion_SearchSER_SER_No");
		fnsWait_and_Type("TraQtion_SearchSER_SER_No", Work_Order_NO);
		
		fnsGet_Element_Enabled("TraQtion_Search_Bttn");
		fnsWait_and_Click("TraQtion_Search_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);		
		
		fnsGet_Element_Enabled("TraQtion_Search_FirstRow_FirstColumn_Link");
		fnsGet_Field_TEXT("TraQtion_Search_FirstRow_FirstColumn_Link");
		String Search_Result_Value = fnsGet_Field_TEXT("TraQtion_Search_FirstRow_FirstColumn_Link");
		try{
			assert (Search_Result_Value.toLowerCase().trim().equals(Work_Order_NO.toLowerCase()));
		}catch(Throwable t){
			fnsTake_Screen_Shot("Search_SER_Not_Working");
			throw new Exception("FAILED == Incorrect '"+Search_Result_Value+"' search result is displayed on Search SER screen [Correct value was = "+Work_Order_NO+" ], please refer the screen shot >> Search_SER_Not_Working");
		}
		
		fnsWait_and_Click("TraQtion_Search_FirstRow_FirstColumn_Link");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		fnsVerify_Application_Navigated_To_Next_Designated_Screen_WithoutOR("'"+Work_Order_NO+"' link", "View SER", "//div[contains(text(), 'View SER')]");
		
		fnsGet_Element_Enabled("TraQtion_Edit_Bttn");
		fnsWait_and_Click("TraQtion_Edit_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
			
	}catch(Throwable t){
		isTestCasePass = false;
		fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
}




@Test( priority = 4, dependsOnMethods={"Search_and_Open_NewllyCreatedSER_from_SerachSER_Screen"}, description="")
public void EditSER_Verify_Data_AddEditUpdate_Successfully_into_the_Info_TAB() throws Throwable{
	fnsApps_Report_Logs("################### Test Case ::3 EditSER_Verify_Data_AddEditUpdate_Successfully_into_the_Info_TAB ");
	try{
		fnsGet_Element_Enabled("TraQtion_EditSER_InfoTab_SER_Name");
		fnsGet_OR_TraQtion_ObjectX("TraQtion_EditSER_InfoTab_SER_Name").clear();
		fnsWait_and_Type("TraQtion_EditSER_InfoTab_SER_Name", "Automation Edited SER-"+TestSuiteBase_MonitorPlan.fnsReturn_Requried_cal("dd-MMM-yy"));
		
		fnsGet_Element_Enabled("TraQtion_Save_Bttn");
		fnsWait_and_Click("TraQtion_Save_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		fnsGet_Element_Enabled("TraQtion_Message_Div");
		fnsVerify_Validation_Message_TraQtion();	
		
	}catch(Throwable t){
		isTestCasePass = false;
		fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
}



@Test( priority = 5, dependsOnMethods={"EditSER_Verify_Data_AddEditUpdate_Successfully_into_the_Info_TAB"}, description="")
public void EditSER_Verify_Data_AddEditUpdate_Successfully_into_the_Details_TAB() throws Throwable{
	fnsApps_Report_Logs("################### Test Case ::4 EditSER_Verify_Data_AddEditUpdate_Successfully_into_the_Details_TAB ");
	try{
		fnsGet_Element_Enabled("TraQtion_EditSER_Details_TAB");
		fnsWait_and_Click("TraQtion_EditSER_Details_TAB");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		fnsGet_Element_Enabled("TraQtion_EditSER_DetailsTAB_Ingredient_Add_Bttn");
		fnsWait_and_Click("TraQtion_EditSER_DetailsTAB_Ingredient_Add_Bttn");
				
		fnsGet_Element_Enabled("TraQtion_EditSER_DetailsTAB_Ingredient_IngredientName");
		fnsWait_and_Type("TraQtion_EditSER_DetailsTAB_Ingredient_IngredientName", "NEW Ingredient");
		
		fnsDD_value_Select_By_MatchingText_DownKeyPress("TraQtion_EditSER_DetailsTAB_Ingredient_TypeofRequest_DD_Click", "TraQtion_EditSER_DetailsTAB_Ingredient_TypeofRequest_DD_List", "li", "NEW");
		
		fnsGet_Element_Enabled("TraQtion_EditSER_DetailsTAB_Ingredient_CurrentProduct_NO_RadioBttn");
		fnsWait_and_Click("TraQtion_EditSER_DetailsTAB_Ingredient_CurrentProduct_NO_RadioBttn");
		
		fnsGet_Element_Enabled("TraQtion_EditSER_DetailsTAB_Ingredient_CurrentSupplierforPJ_NO_RadioBttn");
		fnsWait_and_Click("TraQtion_EditSER_DetailsTAB_Ingredient_CurrentSupplierforPJ_NO_RadioBttn");
		
		fnsDD_value_Select_By_MatchingText_DownKeyPress("TraQtion_EditSER_DetailsTAB_Ingredient_CoreNonCore_DD_Click", "TraQtion_EditSER_DetailsTAB_Ingredient_CoreNonCore_DD_List", "li", "CORE");
		
		fnsGet_Element_Enabled("TraQtion_Save_Bttn");
		fnsWait_and_Click("TraQtion_Save_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		fnsGet_Element_Enabled("TraQtion_Message_Div");
		fnsVerify_Validation_Message_TraQtion();	
		
		
		
		fnsGet_Element_Enabled("TraQtion_EditSER_DetailsTAB_NewMenuItem_CheckBox");
		fnsWait_and_Click("TraQtion_EditSER_DetailsTAB_NewMenuItem_CheckBox");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		fnsGet_Element_Enabled("TraQtion_EditSER_DetailsTAB_NewMenuItem_Add_Bttn");
		TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element(OR_TraQtion.getProperty("TraQtion_EditSER_DetailsTAB_NewMenuItem_Add_Bttn"));
		fnsWait_and_Click("TraQtion_EditSER_DetailsTAB_NewMenuItem_Add_Bttn");
				
		fnsGet_Element_Enabled("TraQtion_EditSER_DetailsTAB_NewMenuItem_MenuItemName");
		fnsWait_and_Type("TraQtion_EditSER_DetailsTAB_NewMenuItem_MenuItemName", "NEW Menu Item");
		
		fnsDD_value_Select_By_MatchingText_DownKeyPress("TraQtion_EditSER_DetailsTAB_NewMenuItem_TypeofRequest_DD_Click", "TraQtion_EditSER_DetailsTAB_NewMenuItem_TypeofRequest_DD_List", "li", "NEW");
		
		fnsDD_value_Select_By_MatchingText_DownKeyPress("TraQtion_EditSER_DetailsTAB_NewMenuItem_CoreNonCore_DD_Click", "TraQtion_EditSER_DetailsTAB_NewMenuItem_CoreNonCore_DD_List", "li", "CORE");		
		
		fnsDD_value_Select_By_MatchingText_DownKeyPress("TraQtion_EditSER_DetailsTAB_NewMenuItem_LengthOfRequest_DD_Click", "TraQtion_EditSER_DetailsTAB_NewMenuItem_LengthOfRequest_DD_List", "li", "PERMANENT");
		
		fnsGet_Element_Enabled("TraQtion_Save_Bttn");
		fnsWait_and_Click("TraQtion_Save_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		fnsGet_Element_Enabled("TraQtion_Message_Div");
		fnsVerify_Validation_Message_TraQtion();	
		
		
		
		
		
		fnsGet_Element_Enabled("TraQtion_EditSER_DetailsTAB_NewSupplier_CheckBox");
		fnsWait_and_Click("TraQtion_EditSER_DetailsTAB_NewSupplier_CheckBox");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		
		fnsVerify_Application_Navigated_To_Next_Designated_Screen_WithoutOR("'New Supplier' CheckBox", "New Supplier section", OR_TraQtion.getProperty("TraQtion_EditSER_DetailsTAB_NewSupplier_Div"));
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);

		try{
			Thread.sleep(4000);
			Actions act1 = new Actions(driver);
			WebElement ele = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(OR_TraQtion.getProperty("TraQtion_EditSER_DetailsTAB_NewSupplier_Div"));
			act1.moveToElement(ele).click().build().perform();
			act1.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).build().perform();
			
		}catch(Throwable t){
			//nothing to do
		}
		TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element(OR_TraQtion.getProperty("TraQtion_EditSER_DetailsTAB_NewSupplier_Div"));
		fnsGet_Element_Enabled("TraQtion_EditSER_DetailsTAB_NewSupplier_Add_Bttn");		
		Actions act = new  Actions(driver);
		for(int i=0; i <=120; i++){
			try{
				act.moveToElement(fnsGet_OR_TraQtion_ObjectX("TraQtion_EditSER_DetailsTAB_NewSupplier_Add_Bttn")).doubleClick().build().perform();
				fnsApps_Report_Logs("PASSED == Click done on 'Add' button, after <"+i+"> attampts.");
				break;
			}catch (Throwable t){
				if(i==120){
					fnsTake_Screen_Shot("Click_Fail");
					throw new Exception("FAILED == Unable to click after <"+i+"> attampts, please refer the screen shot >> Click_Fail"+fnsScreenShot_Date_format());
				}else{
					Thread.sleep(1000);
				}
			}
		}
		
		try{
			Thread.sleep(2000);
			Actions act1 = new Actions(driver);
			WebElement ele = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(OR_TraQtion.getProperty("TraQtion_EditSER_DetailsTAB_NewSupplier_Div"));
			act1.moveToElement(ele).click().build().perform();
			act1.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).build().perform();
			
		}catch(Throwable t){
			//nothing to do
		}
		
		
		
		fnsGet_Element_Enabled("TraQtion_EditSER_DetailsTAB_NewSupplier_NewSupplierName");
		fnsWait_and_Type("TraQtion_EditSER_DetailsTAB_NewSupplier_NewSupplierName", "NEW Supplier");
				
		fnsGet_Element_Enabled("TraQtion_EditSER_DetailsTAB_NewSupplier_PreviousSupplierforPJ_NO_RadioBttn");
		TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element_and_Click(OR_TraQtion.getProperty("TraQtion_EditSER_DetailsTAB_NewSupplier_PreviousSupplierforPJ_NO_RadioBttn"));
		
		
		fnsGet_Element_Enabled("TraQtion_EditSER_DetailsTAB_NewSupplier_SupplierCity");
		fnsWait_and_Type("TraQtion_EditSER_DetailsTAB_NewSupplier_SupplierCity", "NEW York");		
		
		fnsDD_value_Select_By_MatchingText_DownKeyPress("TraQtion_EditSER_DetailsTAB_NewSupplier_SupplierCountry_DD_Click", "TraQtion_EditSER_DetailsTAB_NewSupplier_SupplierCountry_DD_List", "li", "United States");
		
		fnsGet_Element_Enabled("TraQtion_EditSER_DetailsTAB_NewSupplier_ReplacingACurrentSupplier_NO_RadioBttn");
		TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element_and_Click(OR_TraQtion.getProperty("TraQtion_EditSER_DetailsTAB_NewSupplier_ReplacingACurrentSupplier_NO_RadioBttn"));
		
		
		fnsGet_Element_Enabled("TraQtion_EditSER_DetailsTAB_NewSupplier_CurrentProduct_NO_RadioBttn");
		TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element_and_Click(OR_TraQtion.getProperty("TraQtion_EditSER_DetailsTAB_NewSupplier_CurrentProduct_NO_RadioBttn"));
		
		fnsGet_Element_Enabled("TraQtion_EditSER_DetailsTAB_NewSupplier_SupplierContactName");
		fnsWait_and_Type("TraQtion_EditSER_DetailsTAB_NewSupplier_SupplierContactName", "testscriptuser");	
		
		
		try{
			Actions act2 = new Actions(driver);
			WebElement ele = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(OR_TraQtion.getProperty("TraQtion_EditSER_DetailsTAB_NewSupplier_Div"));
			act2.moveToElement(ele).click().build().perform();
			act2.sendKeys(Keys.ARROW_UP).sendKeys(Keys.ARROW_UP).build().perform();
			
		}catch(Throwable t){
			//nothing to do
		}
		
		
		
		fnsGet_Element_Enabled("TraQtion_Save_Bttn");
		fnsWait_and_Click("TraQtion_Save_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		fnsGet_Element_Enabled("TraQtion_Message_Div");
		fnsVerify_Validation_Message_TraQtion();	
		
		
		
	}catch(Throwable t){
		isTestCasePass = false;
		fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
}


@Test( priority = 6, dependsOnMethods={"EditSER_Verify_Data_AddEditUpdate_Successfully_into_the_Details_TAB"}, description="")
public void EditSER_Verify_Data_AddEditUpdate_Successfully_into_the_RegionMarket_TAB() throws Throwable{
	fnsApps_Report_Logs("################### Test Case ::5 EditSER_Verify_Data_AddEditUpdate_Successfully_into_the_RegionMarket_TAB ");
	try{
		fnsGet_Element_Enabled("TraQtion_EditSER_RegionMarket_TAB");
		fnsWait_and_Click("TraQtion_EditSER_RegionMarket_TAB");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		fnsGet_Element_Enabled("TraQtion_EditSER_RegionMarketTAB_Add_Region_Bttn");
		fnsWait_and_Click("TraQtion_EditSER_RegionMarketTAB_Add_Region_Bttn");

		fnsVerify_Application_Navigated_To_Next_Designated_Screen_WithoutOR("'Add' button", "Add Region Popup", OR_TraQtion.getProperty("TraQtion_EditSER_RegionMarketTAB_AddRegion_Popup_Title"));
				
		fnsDD_value_Select_By_MatchingText_DownKeyPress("TraQtion_EditSER_RegionMarketTAB_AddRegion_Region_DD_Click", "TraQtion_EditSER_RegionMarketTAB_AddRegion_Region_DD_List", "li", "ASIA");
		
		fnsGet_Element_Enabled("TraQtion_EditSER_RegionMarketTAB_AddRegion_Market_LK_Bttn");
		fnsWait_and_Click("TraQtion_EditSER_RegionMarketTAB_AddRegion_Market_LK_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		fnsGet_Element_Enabled("TraQtion_EditSER_RegionMarketTAB_AddRegion_Market_LK_Text");
		fnsWait_and_Type("TraQtion_EditSER_RegionMarketTAB_AddRegion_Market_LK_Text", "ASA-CHNF");
		TestSuiteBase_MonitorPlan.fnsLookup_RadioBttn_Select();
		
		fnsGet_Element_Enabled("TraQtion_EditSER_RegionMarketTAB_AddRegion_Add_bttn");
		fnsWait_and_Click("TraQtion_EditSER_RegionMarketTAB_AddRegion_Add_bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
			
		fnsGet_Element_Enabled("TraQtion_Save_Bttn");
		fnsWait_and_Click("TraQtion_Save_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		fnsGet_Element_Enabled("TraQtion_Message_Div");
		fnsVerify_Validation_Message_TraQtion();	
		
		
	}catch(Throwable t){
		isTestCasePass = false;
		fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
}




@Test( priority = 7, dependsOnMethods={"EditSER_Verify_Data_AddEditUpdate_Successfully_into_the_RegionMarket_TAB"}, description="")
public void EditSER_Verify_RestOfThe_TABs_Opened_WithOutError() throws Throwable{
	fnsApps_Report_Logs("################### Test Case ::6 EditSER_Verify_RestOfThe_TABs_Opened_WithOutError ");
	try{
		
		fnsGet_Element_Enabled("TraQtion_EditSER_Task_TAB");
		fnsWait_and_Click("TraQtion_EditSER_Task_TAB");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		fnsGet_Element_Enabled("TraQtion_EditSER_Documents_TAB");
		fnsWait_and_Click("TraQtion_EditSER_Documents_TAB");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		fnsGet_Element_Enabled("TraQtion_EditSER_Correspondence_TAB");
		fnsWait_and_Click("TraQtion_EditSER_Correspondence_TAB");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
		fnsGet_Element_Enabled("TraQtion_EditSER_History_TAB");
		fnsWait_and_Click("TraQtion_EditSER_History_TAB");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		
	}catch(Throwable t){
		isTestCasePass = false;
		fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
}




//#######################################################  Configuration Method  ################################################################
@AfterTest
public void reportTestResult() throws Throwable {
	TestSuiteBase_MonitorPlan.fns_ReportTestResult_atClassLevel(TraQtion_suitexls,  (this.getClass().getSimpleName()) , isTestCasePass);
}


@AfterTest
public void QuitBrowser(){
	TestSuiteBase_MonitorPlan.MoveMouseCursorToTaskBar();
	driver.quit();
}

}