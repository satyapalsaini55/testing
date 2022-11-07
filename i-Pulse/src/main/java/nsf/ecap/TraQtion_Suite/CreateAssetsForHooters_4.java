package nsf.ecap.TraQtion_Suite;
import java.util.ArrayList;
import java.util.List;

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

public class CreateAssetsForHooters_4  extends TestSuiteBase_TraQtion {
	
	public int count = -1;
	public Integer tabsCount;
	public String createdProductNumber=null;
	public boolean flag = false;
	public String successMessage=null;
	public String createdSupplier=null;
	public String createdSiteCO=null;
	public String createdSitePL=null;
	public String createdProduct=null;
	public String headerMessage=null;
	public String siteModule=null;
	//######### Scripts variables Starts ##################################
	public String ScenaioName = null;
	public String TraQtion_Product_Category = null;
	public String TraQtion_Facility_Type = null;
	public String TraQtion_Evaluation_Cat = null;
	public String TraQtion_Evaluation_Type = null;
	public String TraQtion_Supplier_SiteName = null;
	public String TraQtion_Supplier_Phone = null;
	public String TraQtion_Supplier_Email = null;
	public String TraQtion_Supplier_Country = null;
	public String TraQtion_Supplier_City = null;
	public String TraQtion_Supplier_fName = null;
	public String TraQtion_Supplier_lName = null;
	public String TraQtion_Supplier_Addres = null;
	public String TraQtion_Supplier_Name = null;
	public String TraQtion_Lock_Prod_Change_Reason = null;
	public String TraQtion_Status_Reason = null;	
	public String TraQtion_Max_Value = null;
	public String TraQtion_Min_Value = null;	
	public String TraQtion_ProductTemplateName=null;
	
	@Parameters({ "className" })
	@BeforeTest
	public void checkTestSkip(String className) throws Exception {
		if (className.isEmpty()) {
			className = this.getClass().getSimpleName();
		}
		IsBrowserPresentAlready =false;
		fnsCheckClassLevelTestSkip(className);
	}
	/**
	 * Launch_Browser_and_Successfully_Login_into_the_Application test case is
	 * launching Browser for the application.
	 * @throws Throwable
	 */
	@Test( priority = 1)
	public void Launch_Browser_and_Successfully_Login_into_the_Application() throws Throwable{
		try{	
			fnsApps_Report_Logs("################################## [BS-04]  CreateAssetsForHooters Test");
			if (!IsBrowserPresentAlready) {
				IsBrowserPresentAlready = fnsTraQtionPortal_LaunchBrowserAndLogin("TraQtion Portal", Login_UserName, Login_Password);			
			}
		}catch(Throwable t){
			isTestCasePass = false;
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		fnsApps_Report_Logs("=========================================================================================================================================");
	}
	/**
	 * Create_Product_Template_In_Trq test case is creating Product Template.
	 * 
	 * @throws Throwable
	 */
	@Test( priority = 2, dependsOnMethods={"Launch_Browser_and_Successfully_Login_into_the_Application"}, description="")
	public void Create_Product_Template() throws Throwable{
		fnsApps_Report_Logs("################### Test Case ::1 Create_Product_Template");
		try{
			//##########################################   EXCEL Variable ###############################################################################################
			fnsApps_Report_Logs("################### Fetching Data from Excel  ###################");
			TraQtion_ProductTemplateName =  fngReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Product_Template_Name");
			TraQtion_Min_Value=  fngReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Min_Value");
			TraQtion_Max_Value =  fngReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Max_Value");
			TraQtion_Status_Reason =  fngReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Status_Reason");
			TraQtion_Lock_Prod_Change_Reason =  fngReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Lock_Prod_Change_Desc");
			TraQtion_Supplier_Name =  fngReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Supplier_Name");
			TraQtion_Supplier_Addres =  fngReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Address");
			TraQtion_Supplier_lName =  fngReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "LastName");
			TraQtion_Supplier_fName =  fngReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "FirstName");
			TraQtion_Supplier_City =  fngReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "CITY");
			TraQtion_Supplier_Email =   fngReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Email");
			TraQtion_Supplier_Country =  fngReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Country");
			TraQtion_Supplier_Phone =   fngReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Phone");
			TraQtion_Supplier_SiteName =   fngReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Site_Name");
			TraQtion_Evaluation_Type =   fngReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Evaluation_Type");
			TraQtion_Evaluation_Cat =   fngReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Evaluation_Category");
			TraQtion_Facility_Type =   fngReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Facility_Type");
			TraQtion_Product_Category =   fngReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Product_Category");

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
		//fngGotoSearchTemplateProductPage("TraQtion_Menu","TraQtion_SearchProductTemplates_Ajax");
		fnsMenu_Ajax_Link_Click_By_PassingAjaxPath_TraQtion_WithoutOR("SearchTemplateProduct", OR_TraQtion.getProperty("TraQtion_SearchProductTemplates_Ajax"));
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled("//button[@type='submit']/span[text()='Create']");
		TestSuiteBase_MonitorPlan.WithOut_OR_fnClick("//button[@type='submit']/span[text()='Create']");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		fnsGet_Element_Enabled("TraQtion_Product_Template_Name_Txt");
		headerMessage=fnsGet_Field_TEXT("TraQtion_Page_Header");
		if(!headerMessage.contains("Create Product Template")){
			fnsTake_Screen_Shot("Navigation_Fail");
			throw new Exception("FAILED == 'Create Product Template' screen is not getting open, please refer the screen shot >> Navigation_Fail"+fnsScreenShot_Date_format());
		}else{
			fnc_Type("TraQtion_Product_Template_Name_Txt", TraQtion_ProductTemplateName);
			fnc_Click("TraQtion_Product_Category_Lookup");
			//fnsGet_OR_TraQtion_ObjectX("TraQtion_Product_Category_Lookup").click();
			fnc_Click("TraQtion_Food_Bread");
			TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
			//fnsGet_OR_TraQtion_ObjectX("TraQtion_Food_Bread").click();
		//	TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
			fnc_Click("TraQtion_Select_Return_Button");
			//fnsGet_OR_TraQtion_ObjectX("TraQtion_Select_Return_Button").click();
			TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
			fnc_Click("TraQtion_CreateButton_CPT");
			//fnsGet_OR_TraQtion_ObjectX("TraQtion_CreateButton_CPT").click();
			TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
			/*successMessage=fnsGet_Field_TEXT("TraQtion_Message_Div");
			if(successMessage.contains("Product Template created successfully")){
				createdProductNumber = fngFetchDigitValueFromString(successMessage);
			}else{
				fnsApps_Report_Logs("Product Template is not Created!!!");
			}*/
			createdProductNumber = fnsVerify_Validation_Message_TraQtion();
			createdProductNumber = createdProductNumber.split("Product")[0].trim();
		}
		/*flag = fnsIs_Element_Displayed("TraQtion_Attributs_TAB");
		if(!flag){
			fnsApps_Report_Logs("Attribute Tab is not displayed!!!");
		}else{
			fngGotoTab("TraQtion_Attributs_TAB");*/
			fnc_Click("TraQtion_Attributs_TAB");
			TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
			fnc_Click("TraQtion_Edit_Bttn");
		//	fnsGet_OR_TraQtion_ObjectX("TraQtion_Edit_Bttn").click();
			TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
			fngEnterMinValueForInspectableAttribute(1);
			TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
			fngEnterMaxValueForInspectableAttribute(11);
			fnc_Click("TraQtion_Save_Bttn");
		//	fnsGet_OR_TraQtion_ObjectX("TraQtion_Save_Bttn").click();
			TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		//	fnsGet_Element_Enabled("TraQtion_Message_Div");
		//	String successMessage=fnsGet_Field_TEXT("TraQtion_Message_Div");
			fnsVerify_Validation_Message_TraQtion();
		/*}*/
		/*flag = fnsIs_Element_Displayed("TraQtion_Information_TAB");
		if(!flag){
			fnsApps_Report_Logs("Information Tab is not displayed!!!");
		}else{
			fngGotoTab("TraQtion_Information_TAB");*/
			
			
			
			//IPM-10355
			fnc_Click("TraQtion_EditProduct_Nutritional_Tab");
			TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
			fnsWait_and_Click("TraQtion_EditProduct_Nutritional_Expand_Icon");
			TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
			
			fnsGet_Element_Enabled("TraQtion_EditProduct_Nutritional_Data_Table");
			Integer Table_Row_Count = 1;
			for(int i=1; i<=Long.parseLong(CONFIG.getProperty("ElementWaitTime")); i++){
				try{
					Table_Row_Count = driver.findElements(By.xpath(OR_TraQtion.getProperty("TraQtion_EditProduct_Nutritional_Data_Table")+"/tr")).size();
					if(Table_Row_Count>2){
						break;
					}else{
						Thread.sleep(1000);;
					}
				}catch(Throwable t){
					if(i==Long.parseLong(CONFIG.getProperty("ElementWaitTime"))){
						fnsTake_Screen_Shot("Nutritional_Tab_RecordTable_Not_Coming");
						throw new Exception("FAILED == Record Table is not coming under NutritionalTab, please refer the screenshot >> Nutritional_Tab_RecordTable_Not_Coming"+fnsScreenShot_Date_format());
					}else{
						Thread.sleep(1000);
					}
				}
			}
			
			for(int j=2; j<=Table_Row_Count+1; j++){
				String Table_Edit_Pencil_Xpath = "(//span[@class='ui-icon ui-icon-pencil'])["+j+"]";
				for(int k=0; k<=10; k++){
					try{
						Actions act1 = new Actions(driver);
						WebElement ele = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(Table_Edit_Pencil_Xpath);
						act1.moveToElement(ele).click().build().perform();
						act1.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).build().perform();
						
						
						
					//	TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element_and_Click(Table_Edit_Pencil_Xpath);
					//	driver.findElement(By.xpath(Table_Edit_Pencil_Xpath)).click();
						break;
					}catch(Throwable t){
						if(k==10){
							fnsTake_Screen_Shot("Pencil_Icon_"+j+"_Click_Fail");
							throw new Exception("FAILED == Clicking on pencil icon is getting fail at row no = "+j+", please refer the screenshot >> Pencil_Icon_"+j+"_Click_Fail"+fnsScreenShot_Date_format());
						}else{
							Thread.sleep(100);
						}
					}
				}	
			}
			Thread.sleep(2000);
			List<WebElement> Table_All_Input_Objects = fnsGet_OR_TraQtion_ObjectX("TraQtion_EditProduct_Nutritional_Data_Table").findElements(By.tagName("input"));
			Integer Input_RawData = 1;
			try{
				for(WebElement Table_All_Input_Element : Table_All_Input_Objects){
					String Input_Element_Type_Attribute = Table_All_Input_Element.getAttribute("type").toLowerCase().trim();
					if(Input_Element_Type_Attribute.equals("text")){
						Table_All_Input_Element.sendKeys("A_"+Input_RawData);
						Input_RawData++;
					}
				}
			}catch(Throwable t){
				fnsTake_Screen_Shot("Type_Fail");
				throw new Exception(Throwables.getStackTraceAsString(t));
			}
					
			fnc_Click("TraQtion_EditProduct_Nutritional_Edit_PencilButton");
			TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
			fnc_Type("TraQtion_EditProduct_Nutritional_ServingSize", "Automation > Serving Size");
			fnc_Type("TraQtion_EditProduct_Nutritional_ServingPerContainer", "Automation > Serving Per Container");
			fnc_Type("TraQtion_EditProduct_Nutritional_NutritionNotes", "Automation > Nutrition Notes");
			fnc_Type("TraQtion_EditProduct_Nutritional_Client", Login_UserName);
			
			fnc_Click("TraQtion_Save_Bttn");
			TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
			fnsVerify_Validation_Message_TraQtion();
			
			
			
			
			fnc_Click("TraQtion_Information_TAB");
			TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
			fnsDD_value_Select_By_MatchingText_DownKeyPress("TraQtion_Label_PT_Status", "TraQtion_ddXpathKey_PT_Status","li", "SUBMITTED");
			TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
			fnc_Type("TraQtion_PT_Status_Reason", TraQtion_Status_Reason);
		//	TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
			fnc_Click("TraQtion_Save_Bttn");
		//	fnsGet_OR_TraQtion_ObjectX("TraQtion_Save_Bttn").click();
			TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
			fnsVerify_Validation_Message_TraQtion();
			fnsDD_value_Select_By_MatchingText_DownKeyPress("TraQtion_Label_PT_Status", "TraQtion_ddXpathKey_PT_Status","li", "ACTIVE");
			TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
			fnc_Type("TraQtion_PT_Status_Reason", TraQtion_Status_Reason);
			fnc_Click("TraQtion_Save_Bttn");
		//	fnsGet_OR_TraQtion_ObjectX("TraQtion_Save_Bttn").click();
			TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
			fnsVerify_Validation_Message_TraQtion();
		/*}*/
		/*flag = fnsIs_Element_Displayed("TraQtion_Lock_Button");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		if(flag){*/
			fnc_Click("TraQtion_Lock_Button");
		//	fnsGet_OR_TraQtion_ObjectX("TraQtion_Lock_Button").click();
			TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
			fnc_Type("TraQtion_Lock_Product_Change_Desc_Area", TraQtion_Lock_Prod_Change_Reason);
			//TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
			fnc_Click("TraQtion_Lock_Popup_Button");
		//	fnsGet_OR_TraQtion_ObjectX("TraQtion_Lock_Popup_Button").click();
			TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
			String lockedText=fnsGet_Field_TEXT("TraQtion_Locked_Template_Text");
			fnsApps_Report_Logs("PASSED == Product is '"+lockedText+".");
	/*	}*/
		}catch(Throwable t){
			isTestCasePass = false;
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}
	/**
	 * Create_And_Search_Supplier_In_Trq test case is creating 
	 * and Searching Supplier.
	 * @throws Exception
	 */
	@Test( priority = 3, dependsOnMethods={"Create_Product_Template"}, description="")
	public void Create_Supplier_CO_And_PL_Facility() throws Exception{
		fnsApps_Report_Logs("################### Test Case ::2 Create_Supplier_CO_And_PL_Facility");
		try{
			//fngGotoSearchSupplierLinkPage("TraQtion_Menu","TraQtion_SearchSuppliers_Ajax");
			fnsMenu_Ajax_Link_Click_By_PassingAjaxPath_TraQtion_WithoutOR("SearchSupplier", OR_TraQtion.getProperty("TraQtion_SearchSuppliers_Ajax"));
			TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled("//button[@type='submit']/span[text()='Create']");
			TestSuiteBase_MonitorPlan.WithOut_OR_fnClick("//button[@type='submit']/span[text()='Create']");
			TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
			fnsGet_Element_Enabled("TraQtion_Supplier_NameTxt");
			headerMessage=fnsGet_Field_TEXT("TraQtion_Page_Header");
			if(!headerMessage.contains("Create Supplier")){
				fnsTake_Screen_Shot("Navigation_Fail");
				throw new Exception("FAILED == 'Create Supplier' screen is not getting open, please refer the screen shot >> Navigation_Fail"+fnsScreenShot_Date_format());
			}else{
			//	TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
				fnc_Type("TraQtion_Supplier_NameTxt",TraQtion_Supplier_Name);
				//TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
				for(int i=0; i<=20; i++){
					TestSuiteBase_MonitorPlan.WithOut_OR_fnDD_SelectValue_By_MatchingText(OR_TraQtion.getProperty("TraQtion_Supplier_Type_Label"), OR_TraQtion.getProperty("TraQtion_Supplier_Type"), "li", "CO");
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
					String DD_Selected_Text = fnsGet_Field_TEXT("TraQtion_Supplier_Type_Label");
					if(DD_Selected_Text.equals("PL")){						
						Thread.sleep(500);
					}else{
						break;
					}
				}
				
				
				
				
				
				
			//	TestSuiteBase_MonitorPlan.WithOut_OR_fnDD_SelectValue_By_MatchingText(OR_TraQtion.getProperty("TraQtion_Supplier_Type_Label"), OR_TraQtion.getProperty("TraQtion_Supplier_Type"), "li", "CO");
			//	fnsDD_value_Select_By_MatchingText_DownKeyPress("TraQtion_Supplier_Type_Label", "TraQtion_Supplier_Type","li", "CO");
			//	TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
				fnc_Type("TraQtion_Supplier_Address1", TraQtion_Supplier_Addres);
			//	TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
				fnc_Type("TraQtion_Supplier_CITY", TraQtion_Supplier_City);
			//	TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
				fnsDD_value_Select_By_MatchingText_DownKeyPress("TraQtion_Supplier_Country_Label", "TraQtion_Supplier_Country","li", TraQtion_Supplier_Country);
				TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
				fnc_Type("TraQtion_Supplier_FNAME", TraQtion_Supplier_fName);
			//	TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
				fnc_Type("TraQtion_Supplier_LNAME", TraQtion_Supplier_lName);
			//	TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
				fnc_Type("TraQtion_Supplier_EMAIL", TraQtion_Supplier_Email);
			//	TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
				fnc_Type("TraQtion_Supplier_PHONE", TraQtion_Supplier_Phone);
			//	TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
				fnc_Click("TraQtion_Supplier_Create_Button");
				//fnsGet_OR_TraQtion_ObjectX("TraQtion_Supplier_Create_Button").click();
				TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(5);
				/*successMessage=fnsGet_Field_TEXT("TraQtion_Message_Div");
				if(!successMessage.contains("created successfully")){
					fnsApps_Report_Logs("Product Template is not Created!!!");
				}else{
					createdSupplier= fngFetchDigitValueFromString(successMessage);
					fnsApps_Report_Logs("PASSED == Supplier is "+createdSupplier+" Created.");
				}*/
				createdSupplier = fnsVerify_Validation_Message_TraQtion();
				createdSupplier = createdSupplier.split("Supplier")[0].trim();
			}
		}catch(Throwable t){
			isTestCasePass = false;
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}
	/**
	 * Create_And_Search_Site_CO_In_Trq test case is creating & Searching
	 * Site of CO type.
	 * @throws Exception
	 */
	@Test( priority = 4, dependsOnMethods={"Create_Supplier_CO_And_PL_Facility"}, description="")
	public void Creating_CO_Site_For_Organization() throws Exception{
		fnsApps_Report_Logs("################### Test Case ::4 Creating_CO_Site_For_Organization");
		try{
			//fngGotoSearchSitesLinkPage("TraQtion_Menu","TraQtion_SearchSites_Ajax");			
			fnsMenu_Ajax_Link_Click_By_PassingAjaxPath_TraQtion_WithoutOR("SearchSites",  OR_TraQtion.getProperty("TraQtion_SearchSites_Ajax"));
			TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled("//button[@type='submit']/span[text()='Create']");
			TestSuiteBase_MonitorPlan.WithOut_OR_fnClick("//button[@type='submit']/span[text()='Create']");
			TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
			fnsGet_Element_Enabled("TraQtion_Supplier_NameTxt");
			headerMessage=fnsGet_Field_TEXT("TraQtion_Page_Header");
			if(!headerMessage.contains("Create Site")){
				fnsTake_Screen_Shot("Navigation_Fail");
				throw new Exception("FAILED == 'Create Site' screen is not getting open, please refer the screen shot >> Navigation_Fail"+fnsScreenShot_Date_format());
			}else{
			//	TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
				fnc_Type("TraQtion_Supplier_NameTxt", TraQtion_Supplier_SiteName);
			//	TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
				try{
					for(int i=0; i<=20; i++){
							TestSuiteBase_MonitorPlan.WithOut_OR_fnDD_SelectValue_By_MatchingText(OR_TraQtion.getProperty("TraQtion_Sites_Type_Label"), OR_TraQtion.getProperty("TraQtion_Sites_Type"), "li", "CO");
							TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
							String DD_Selected_Text = fnsGet_Field_TEXT("TraQtion_Sites_Type_Label");
							if(DD_Selected_Text.equals("PL")){						
								Thread.sleep(500);
							}else{
								break;
							}
						}
				}catch(Throwable t){
					//nothing to do
				}
								
				fnc_Type("TraQtion_Supplier_Address1", TraQtion_Supplier_Addres);
			//	TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
				fnc_Type("TraQtion_Site_City", TraQtion_Supplier_City);
			//	TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
				fnsDD_value_Select_By_MatchingText_DownKeyPress("TraQtion_Supplier_Country_Label", "TraQtion_Supplier_Country","li", TraQtion_Supplier_Country);
				TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
				fnc_Type("TraQtion_Supplier_FNAME", TraQtion_Supplier_fName);
			//	TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
				fnc_Type("TraQtion_Supplier_LNAME",TraQtion_Supplier_lName);
			//	TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
				fnc_Type("TraQtion_Supplier_EMAIL", TraQtion_Supplier_Email);
			//	TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
				fnc_Type("TraQtion_Site_PHONE", TraQtion_Supplier_Phone);
			//	TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
				fnc_Click("TraQtion_Site_Create");
				//fnsGet_OR_TraQtion_ObjectX("TraQtion_Site_Create").click();
				TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(5);
				/*successMessage=fnsGet_Field_TEXT("TraQtion_Message_Div");
				if(!successMessage.contains("created successfully")){
					fnsApps_Report_Logs("Product Template is not Created!!!");
				}else{
					createdSiteCO= fngFetchDigitValueFromString(successMessage);
					fnsApps_Report_Logs("PASSED == Site for CO is "+createdSiteCO+" Created.");
				}*/
				createdSiteCO = fnsVerify_Validation_Message_TraQtion();
				createdSiteCO = createdSiteCO.split("Site")[0].trim();
			}
		}catch(Throwable t){
			isTestCasePass = false;
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}
	/**
	 * Create_And_Search_Site_PL_In_Trq Test case is creating & searching
	 * Site for PL Type.
	 * @throws Exception
	 */
	@Test( priority = 5, dependsOnMethods={"Creating_CO_Site_For_Organization"}, description="")
	public void Create_a_PL_Supplier_Site_By_using_parent_ID_as_the_CO_Site_ID_Created() throws Exception{
		fnsApps_Report_Logs("################### Test Case ::5 Create_a_PL_Supplier_Site_By_using_parent_ID_as_the_CO_Site_ID_Created");
		try{
		//	fngGotoSearchSitesLinkPage("TraQtion_Menu","TraQtion_SearchSites_Ajax");
			fnsMenu_Ajax_Link_Click_By_PassingAjaxPath_TraQtion_WithoutOR("SearchSites", OR_TraQtion.getProperty("TraQtion_SearchSites_Ajax"));
			TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled("//button[@type='submit']/span[text()='Create']");
			TestSuiteBase_MonitorPlan.WithOut_OR_fnClick("//button[@type='submit']/span[text()='Create']");
			TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
			fnsGet_Element_Enabled("TraQtion_Supplier_NameTxt");
			headerMessage=fnsGet_Field_TEXT("TraQtion_Page_Header");
			if(!headerMessage.contains("Create Site")){
				fnsTake_Screen_Shot("Navigation_Fail");
				throw new Exception("FAILED == 'Create Site' screen is not getting open, please refer the screen shot >> Navigation_Fail"+fnsScreenShot_Date_format());
			}else{
			//	TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
				fnc_Type("TraQtion_Supplier_NameTxt", TraQtion_Supplier_SiteName);
			//	TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
				fnsDD_value_Select_By_MatchingText_DownKeyPress("TraQtion_Sites_Type_Label", "TraQtion_Sites_Type","li", "PL");
				TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
				fnc_Click("TraQtion_Sites_Parent_Lookup_Button");			
			//	fnsGet_OR_TraQtion_ObjectX("TraQtion_Sites_Parent_Lookup_Button").click();
			//	TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
			//	fnsGet_OR_TraQtion_ObjectX("TraQtion_Sites_Search_Lookup_Button").click();
			//	TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
			//	fnsGet_OR_TraQtion_ObjectX("TraQtion_Sites_Searched_1ROW").click();
				fnc_Type("LookUp_Text", createdSiteCO);
				TestSuiteBase_MonitorPlan.fnsLookup_RadioBttn_Select();
				TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
				fnc_Type("TraQtion_Supplier_Address1", TraQtion_Supplier_Addres);
			//	TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
				fnc_Type("TraQtion_Site_City", TraQtion_Supplier_City);
			//	TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
				fnsDD_value_Select_By_MatchingText_DownKeyPress("TraQtion_Supplier_Country_Label", "TraQtion_Supplier_Country","li", TraQtion_Supplier_Country);
				TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
				fnc_Type("TraQtion_Supplier_FNAME", TraQtion_Supplier_fName);
			//	TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
				fnc_Type("TraQtion_Supplier_LNAME", TraQtion_Supplier_lName);
			//	TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
				fnc_Type("TraQtion_Supplier_EMAIL",TraQtion_Supplier_Email);
			//	TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
				fnc_Type("TraQtion_Site_PHONE", TraQtion_Supplier_Phone);
			//	TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
				fnc_Click("TraQtion_Site_Create");
			//	fnsGet_OR_TraQtion_ObjectX("TraQtion_Site_Create").click();
				TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(5);
				/*successMessage=fnsGet_Field_TEXT("TraQtion_Message_Div");
				if(!successMessage.contains("created successfully")){
					fnsApps_Report_Logs("Product Template is not Created!!!");
				}else{
					createdSitePL= fngFetchDigitValueFromStringForSites(successMessage);
					fnsApps_Report_Logs("PASSED == Site for PL is "+createdSitePL+" Created.");
				}*/
				createdSitePL = fnsVerify_Validation_Message_TraQtion();
				createdSitePL = createdSitePL.split("Site")[0].trim();
			}
		}catch(Throwable t){
			isTestCasePass = false;
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}
	/**
	 * Create_Product_Using_Created_Product_Template_In_Trq test case is creating
	 * Product by using product Template created in previous test case.
	 * @throws Exception
	 */
	@Test( priority = 6, dependsOnMethods={"Create_a_PL_Supplier_Site_By_using_parent_ID_as_the_CO_Site_ID_Created"}, description="")
	public void Create_Product_by_Using_Same_Product_Template_as_Created_Earlier() throws Exception{
		fnsApps_Report_Logs("################### Test Case ::5 Create_Product_by_Using_Same_Product_Template_as_Created_Earlier");
		try{
			TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
			//fngGotoSearchProductLinkPage("TraQtion_Menu","TraQtion_SearchProducts_Ajax");
			fnsMenu_Ajax_Link_Click_By_PassingAjaxPath_TraQtion_WithoutOR("SearchProduct", OR_TraQtion.getProperty("TraQtion_SearchProducts_Ajax"));
			TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled("//button[@type='submit']/span[text()='Create']");
			TestSuiteBase_MonitorPlan.WithOut_OR_fnClick("//button[@type='submit']/span[text()='Create']");
			TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
			fnsGet_Element_Enabled("TraQtion_Product_Template_Name_Txt");
			headerMessage=fnsGet_Field_TEXT("TraQtion_Page_Header");
			if(!headerMessage.contains("Create Product")){
				fnsTake_Screen_Shot("Navigation_Fail");
				throw new Exception("FAILED == 'Create Product' screen is not getting open, please refer the screen shot >> Navigation_Fail"+fnsScreenShot_Date_format());
			}else{
				fnc_Type("TraQtion_Product_Template_Name_Txt", TraQtion_ProductTemplateName);
			//	TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
				fnc_Type("TraQtion_Product_Trade_NameTxt", "Product_TradeName");
			//	TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
				fnc_Click("TraQtion_Product_Cat_lookup_Button");
			//	fnsGet_OR_TraQtion_ObjectX("TraQtion_Product_Cat_lookup_Button").click();
				TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
				fnc_Type("TraQtion_Product_Product_Cat_Value", "Bread");
			//	TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
				fnc_Click("TraQtion_Sites_Search_Lookup_Button");
			//	fnsGet_OR_TraQtion_ObjectX("TraQtion_Sites_Search_Lookup_Button").click();
				TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
				fnc_Click("TraQtion_Product_Select_checkBox");
			//	fnsGet_OR_TraQtion_ObjectX("TraQtion_Product_Select_checkBox").click();
				TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
				fnsDD_value_Select_By_ContainsText_DownKeyPress("TraQtion_Product_Template_Label", "TraQtion_Product_Temp_Select","li", createdProductNumber);
				TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
				fnc_Click("TraQtion_Product_CreateButton");
			//	fnsGet_OR_TraQtion_ObjectX("TraQtion_Product_CreateButton").click();
				TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
				fnc_Click("TraQtion_Product_Popup_YES");
			//	fnsGet_OR_TraQtion_ObjectX("TraQtion_Product_Popup_YES").click();
				TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
				/*successMessage=fnsGet_Field_TEXT("TraQtion_Message_Div");
				if(successMessage.contains("created successfully")){
					createdProduct= fngFetchDigitValueFromStringForSites(successMessage);
				}else{
					fnsApps_Report_Logs("Product Template is not Created!!!");
				}*/
				createdProduct = fnsVerify_Validation_Message_TraQtion();
				createdProduct = createdProduct.split("Product")[0].trim();
				/*flag = fnsIs_Element_Displayed("TraQtion_Attributs_TAB");
				if(!flag){
					fnsApps_Report_Logs("Attribute Tab is not displayed!!!");
				}else{
					fngGotoTab("TraQtion_Attributs_TAB");*/
					fnc_Click("TraQtion_Attributs_TAB");
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
					fnc_Click("TraQtion_Edit_Bttn");
				//	fnsGet_OR_TraQtion_ObjectX("TraQtion_Edit_Bttn").click();
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
					fnc_Click("TraQtion_Attribute_Add_Button");
				//	fnsGet_OR_TraQtion_ObjectX("TraQtion_Attribute_Add_Button").click();
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
					fngSelectAttributesFromAttributeTab();//"5"
				//	TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
					fnc_Click("TraQtion_Attribute_Select_Save_Button");
				//	fnsGet_OR_TraQtion_ObjectX("TraQtion_Attribute_Select_Save_Button").click();
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
					fngEnterMinValueForInspectableAttribute(5);
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
					fngEnterMaxValueForInspectableAttribute(15);
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
					fnc_Click("TraQtion_Save_Bttn");
				//	fnsGet_OR_TraQtion_ObjectX("TraQtion_Save_Bttn").click();
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
					
					
					fnc_Click("TraQtion_Supplier_TAB");
				//	fngGotoTab("TraQtion_Supplier_TAB");
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
					fnc_Click("TraQtion_Supplier_Add_Button");
				//	fnsGet_OR_TraQtion_ObjectX("TraQtion_Supplier_Add_Button").click();
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
					fnsDD_value_Select_By_ContainsText_DownKeyPress("TraQtion_Direct_Supplier_Label", "TraQtion_Direct_Supplier_YES","li", "Yes");
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
					fnc_Click("TraQtion_Supplier_Code_Lookup");
				//	fnsGet_OR_TraQtion_ObjectX("TraQtion_Supplier_Code_Lookup").click();
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
					fnc_Type("TraQtion_Supplier_Code_Txt", createdSupplier);
					TestSuiteBase_MonitorPlan.fnsLookup_RadioBttn_Select();
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
				//  fnc_Click("TraQtion_Sites_Search_Lookup_Button");
				//	fnsGet_OR_TraQtion_ObjectX("TraQtion_Sites_Search_Lookup_Button").click();
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
				//	fnc_Click("TraQtion_Product_Select_checkBox");
				//	fnsGet_OR_TraQtion_ObjectX("TraQtion_Product_Select_checkBox").click();
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
					fnsDD_value_Select_By_ContainsText_DownKeyPress("TraQtion_Supplier_FacilityType_Label", "TraQtion_Supplier_FacilityType_value","li", TraQtion_Facility_Type);
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
					fnc_Click("TraQtion_Supplier_IsPrimary");
				//	fnsGet_OR_TraQtion_ObjectX("TraQtion_Supplier_IsPrimary").click();
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
					fnc_Click("TraQtion_Supplier_SaveNcloseButton");
				//	fnsGet_OR_TraQtion_ObjectX("TraQtion_Supplier_SaveNcloseButton").click();
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
					fnc_Click("TraQtion_Supplier_Confirm_Popup_Yes");
				//	fnsGet_OR_TraQtion_ObjectX("TraQtion_Supplier_Confirm_Popup_Yes").click();
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
				//	fnsGet_Element_Enabled("TraQtion_Message_Div");
				//	successMessage=fnsGet_Field_TEXT("TraQtion_Message_Div");
				//	fnsVerify_Validation_Message_TraQtion();
					successMessage=fnsVerify_Validation_Message_TraQtion();
			/*	}
				flag = fnsIs_Element_Displayed("TraQtion_DC_TAB");
				if(!flag){
					fnsApps_Report_Logs("DC Tab is not displayed!!!");
				}else{
					fngGotoTab("TraQtion_DC_TAB");*/
					fnc_Click("TraQtion_DC_TAB");
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
					fnc_Click("TraQtion_DC_ADD_Button");
				//	fnsGet_OR_TraQtion_ObjectX("TraQtion_DC_ADD_Button").click();
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
					fnc_Type("TraQtion_Product_Product_Cat_Value", createdSitePL);	
					TestSuiteBase_MonitorPlan.fnsSelect_lookup_FirstCheckBox_and_Return();
				//	TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
				//	fnc_Click("TraQtion_Sites_Search_Lookup_Button");
				//	fnsGet_OR_TraQtion_ObjectX("TraQtion_Sites_Search_Lookup_Button").click();
				//	TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(3);
				//	fnc_Click("TraQtion_DC_First_Row_Check");
				//	fnsGet_OR_TraQtion_ObjectX("TraQtion_DC_First_Row_Check").click();
				//	TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
				//	fnc_Click("TraQtion_Attribute_Select_Save_Button");
				//	fnsGet_OR_TraQtion_ObjectX("TraQtion_Attribute_Select_Save_Button").click();
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
					fnc_Click("TraQtion_Save_Bttn");
				//	fnsGet_OR_TraQtion_ObjectX("TraQtion_Save_Bttn").click();
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(3);
					successMessage=fnsGet_Field_TEXT("TraQtion_Message_Div");
					fnsApps_Report_Logs("PASSED == Site for PL is "+successMessage+" Created.");
				/*}*/
			}
		}catch(Throwable t){
			isTestCasePass = false;
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}
	/**
	 * Create_Client_Evaluation_Using_Product_Evaluation_Screen_In_Trq test case is
	 * creating Client Evaluation by using product Evaluation Screen. 
	 * @throws Exception
	 */
	@Test( priority = 7, dependsOnMethods={"Create_Product_by_Using_Same_Product_Template_as_Created_Earlier"}, description="")
	public void Create_Client_Evaluation_By_Using_Product_Evaluation_Screen() throws Exception{
		fnsApps_Report_Logs("################### Test Case ::6 Create_Client_Evaluation_By_Using_Product_Evaluation_Screen");
		try{
			//fngGotoSearchProductLinkPage1("TraQtion_Menu","TraQtion_SearchProducts_Ajax");
			fnsMenu_Ajax_Link_Click_By_PassingAjaxPath_TraQtion_WithoutOR("SearchProduct", OR_TraQtion.getProperty("TraQtion_SearchProducts_Ajax"));
			TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
			fnsGet_Element_Enabled("TraQtion_Created_Product_Code");
			headerMessage=fnsGet_Field_TEXT("TraQtion_Page_Header");
			if(!headerMessage.contains("Search Product")){
				fnsTake_Screen_Shot("Navigation_Fail");
				throw new Exception("FAILED == 'Search Product' screen is not getting open, please refer the screen shot >> Navigation_Fail"+fnsScreenShot_Date_format());
			}else{
				fnc_Type("TraQtion_Created_Product_Code", createdProduct);
			//	TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
				fnc_Click("TraQtion_Product_SearchButton");
			//	fnsGet_OR_TraQtion_ObjectX("TraQtion_Product_SearchButton").click();
				TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
				fnc_Click("TraQtion_Product_Searched_Row");
			//	fnsGet_OR_TraQtion_ObjectX("TraQtion_Product_Searched_Row").click();
				TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
				fnc_Click("TraQtion_Evaluation_TAB");
			//	fnsGet_OR_TraQtion_ObjectX("TraQtion_Evaluation_TAB").click();
				TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
				fnc_Click("TraQtion_Edit_Bttn");
			//	fnsGet_OR_TraQtion_ObjectX("TraQtion_Edit_Bttn").click();
				TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
				fnc_Click("TraQtion_Evaluation_Add_Button");
			//	fnsGet_OR_TraQtion_ObjectX("TraQtion_Evaluation_Add_Button").click();
				TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
				fnsDD_value_Select_By_MatchingText_DownKeyPress("TraQtion_Evaluation_Category_Label", "TraQtion_Evaluation_Category_Val","li", TraQtion_Evaluation_Cat);
			//	fnsDD_value_Select_By_ContainsText_DownKeyPress("TraQtion_Evaluation_Category_Label", "TraQtion_Evaluation_Category_Val","li", TraQtion_Evaluation_Cat);
				TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
				fnc_Click("TraQtion_Evaluation_Site_LookupButton");
			//	fnsGet_OR_TraQtion_ObjectX("TraQtion_Evaluation_Site_LookupButton").click();
				TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
				fnc_Click("TraQtion_Evaluation_Site_CheckBox");
			//	fnsGet_OR_TraQtion_ObjectX("TraQtion_Evaluation_Site_CheckBox").click();
				TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
				fnc_Click("TraQtion_Evaluation_Sup_CheckBox");
			//	fnsGet_OR_TraQtion_ObjectX("TraQtion_Evaluation_Sup_CheckBox").click();
				TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
				fnc_Click("TraQtion_Evaluation_Site_Continue_Button");
			//	fnsGet_OR_TraQtion_ObjectX("TraQtion_Evaluation_Site_Continue_Button").click();
				TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
				fnsDD_value_Select_By_ContainsText_DownKeyPress("TraQtion_Evaluation_Type_Label", "TraQtion_Evaluation_Type_Val","li", TraQtion_Evaluation_Type);
				TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
				fnc_Click("TraQtion_Evaluation_SaveNclose_Button");
			//	fnsGet_OR_TraQtion_ObjectX("TraQtion_Evaluation_SaveNclose_Button").click();
				TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(3);
				fnc_Click("TraQtion_Evaluation_Option_EditButton");
			//	fnsGet_OR_TraQtion_ObjectX("TraQtion_Evaluation_Option_EditButton").click();
				TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(5);
				fnc_Click("TraQtion_Evaluation_Close_PopupButton");
			//	fnsGet_OR_TraQtion_ObjectX("TraQtion_Evaluation_Close_PopupButton").click();
				TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(3);
				fnc_Click("TraQtion_Evaluation_ID_Link");
			//	fnsGet_OR_TraQtion_ObjectX("TraQtion_Evaluation_ID_Link").click();
				TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
				fnsGet_Element_Enabled("TraQtion_Edit_Bttn");
				headerMessage=fnsGet_Field_TEXT("TraQtion_Page_Header");
				if(!headerMessage.contains("Evaluation Entry")){
					fnsTake_Screen_Shot("Navigation_Fail");
					throw new Exception("FAILED == 'Evaluation Entry' screen is not getting open, please refer the screen shot >> Navigation_Fail"+fnsScreenShot_Date_format());
				}else{
					fnsApps_Report_Logs("PASSED == "+headerMessage+" page is opened.");
				}
			}
			isTestCasePass = true;
		}catch(Throwable t){
			isTestCasePass = false;
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}
//#######################################################  Class Method  ################################################################
	
/*
		public void fngClickOnApplications(String application) throws Exception{
			fnsApps_Report_Logs("Going to click on the Application.");
			fnsGet_OR_TraQtion_ObjectX(application).click();
			Thread.sleep(15000);
			goTraQtionPortal();
			
		}
		*/
	/*	public void goTraQtionPortal() throws InterruptedException{
			ArrayList<String> listoldtabs = new ArrayList<String>(driver.getWindowHandles());
			String originalHandle = driver.getWindowHandle();
			
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());

			int oldTotalcount = listoldtabs.size();
			int newTotaltabsCount = tabs.size();
			int ii = 0;
			while (newTotaltabsCount != (oldTotalcount + 1)) {
				Thread.sleep(1000);
				tabs = new ArrayList<String>(driver.getWindowHandles());
				newTotaltabsCount = tabs.size();
				ii = ii + 1;
				System.out.println("  ----waiting for tabs --"+ii);
				if (ii > Integer.parseInt(CONFIG.getProperty("TraQtion_genMax_waitTime"))) {
					
					break;
				}
			}
			Thread.sleep(2000);
			
			
			for (int i = 0; i < newTotaltabsCount; i++) {
				if (originalHandle.equalsIgnoreCase(tabs.get(i))) { 
					// nothing to do
				} else {
					driver.switchTo().window(tabs.get(i));
					Thread.sleep(1000);
					break;
				}

			}
		}*/
		
		/*public void fngGotoSearchTemplateProductPage(String firstXpath, String secondXpath) throws Throwable{
			fnsApps_Report_Logs("Going to click on the Menu tab and Search Template Product Link.");
			fngMoveMouseAtCenterBottomOfScreen();
			fnc_Click(firstXpath);
		//	fnsGet_OR_TraQtion_ObjectX(firstXpath).click();
			fnc_Click(secondXpath);
		//	fnsGet_OR_TraQtion_ObjectX(secondXpath).click();
			//TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
			fnsApps_Report_Logs("Going to click on the CreatButton on Search Template Product Page.");
			fnc_Click("TraQtion_CreateButton");
		//	fnsGet_OR_TraQtion_ObjectX("TraQtion_CreateButton").click();
			TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		}*/
		
		/*public void fngGotoSearchSupplierLinkPage(String firstXpath, String secondXpath) throws Throwable{
			fnsApps_Report_Logs("Going to click on the Menu tab and Search Supplier Link.");
			fngMoveMouseAtCenterBottomOfScreen();
			fnc_Click(firstXpath);
		//	fnsGet_OR_TraQtion_ObjectX(firstXpath).click();
			fnc_Click(secondXpath);
		//	fnsGet_Element_Displayed(secondXpath);
		//	fnsGet_OR_TraQtion_ObjectX(secondXpath).click();
			//TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
			fnsApps_Report_Logs("Going to click on the CreatButton on Search Supplier Page.");
			fnc_Click("TraQtion_CreateButton_Supplier");
		//	fnsGet_OR_TraQtion_ObjectX("TraQtion_CreateButton_Supplier").click();
			TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		}
		public void fngGotoSearchSitesLinkPage(String firstXpath, String secondXpath) throws Throwable{
			fnsApps_Report_Logs("Going to click on the Menu tab and Search Sites Link.");
			fngMoveMouseAtCenterBottomOfScreen();
			fnc_Click(firstXpath);
		//	fnsGet_OR_TraQtion_ObjectX(firstXpath).click();
			fnc_Click(secondXpath);
		//	fnsGet_Element_Displayed(secondXpath);
		//	fnsGet_OR_TraQtion_ObjectX(secondXpath).click();
			//TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
			fnsApps_Report_Logs("Going to click on the CreatButton on Search Sites Page.");
			fnc_Click("TraQtion_CreateButton_Sites");
		//	fnsGet_OR_TraQtion_ObjectX("TraQtion_CreateButton_Sites").click();
			TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		}*/
		
		/*public void fngGotoSearchProductLinkPage(String firstXpath, String secondXpath) throws Throwable{
			fnsApps_Report_Logs("Going to click on the Menu tab and Search Sites Link.");
			fngMoveMouseAtCenterBottomOfScreen();
			fnc_Click(firstXpath);
		//	fnsGet_OR_TraQtion_ObjectX(firstXpath).click();
			fnc_Click(secondXpath);
		//	fnsGet_Element_Displayed(secondXpath);
		//	fnsGet_OR_TraQtion_ObjectX(secondXpath).click();
			fnsApps_Report_Logs("Going to click on the CreatButton on Search Sites Page.");
			fnc_Click("TraQtion_CreateButton");
		//	fnsGet_OR_TraQtion_ObjectX("TraQtion_CreateButton").click();
			TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		}*/
		/*public void fngGotoSearchProductLinkPage1(String firstXpath, String secondXpath) throws Throwable{
			fnsApps_Report_Logs("Going to click on the Menu tab and Search Sites Link.");
			fngMoveMouseAtCenterBottomOfScreen();
			fnc_Click(firstXpath);
		//	fnsGet_OR_TraQtion_ObjectX(firstXpath).click();
			fnc_Click(secondXpath);
		//	fnsGet_Element_Displayed(secondXpath);
		//	fnsGet_OR_TraQtion_ObjectX(secondXpath).click();
			TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		}*/
		
		/*public void fngMoveMouseAtCenterBottomOfScreen() throws Throwable {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			double width = screenSize.getWidth();
			double height = screenSize.getHeight();
			fnsApps_Report_Logs("width is ---" + width);
			fnsApps_Report_Logs("hight is ---" + height);

			Robot robot = new Robot();
			robot.mouseMove((int) width / 2, ((int) height + 200));
			Thread.sleep(500);
		}*/
		/*public void fngEnterProductName(String elementXPath,String value) throws Exception{
			fnsApps_Report_Logs("Going to enter the Product template Name.");
			fnsGet_Element_Displayed(elementXPath);
			fnsGet_OR_TraQtion_ObjectX(elementXPath).clear();
			Thread.sleep(250);
			fnsGet_OR_TraQtion_ObjectX(elementXPath).sendKeys(value);
		}*/
	
		/*public String fngFetchDigitValueFromString(String msg){
			fnsApps_Report_Logs("Going to get the Created Product template.");
			fnsApps_Report_Logs("Satya Split = "+msg);
			return msg.substring(0,8);
		}
		public String fngFetchDigitValueFromStringForSites(String msg){
			fnsApps_Report_Logs("Going to get the Created Product template.");
			return msg.substring(0,8);
		}*/
		/*public void fngGotoTab(String elementXpath) throws Throwable{
			fnsApps_Report_Logs("Going to click on the Tab.");
			fnc_Click(elementXpath);
		//	fnsGet_OR_TraQtion_ObjectX(elementXpath).click();
			TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		}*/
	/*	//Function to wait for element
		public boolean fnsIs_Element_Displayed(String XpathKey) throws Exception {
			boolean flag=false;
			try{
				for(int wait=0; wait<=( (NewNsfOnline_Element_Max_Wait_Time)*2 ); wait++){
					try{
						if(fnsGet_OR_TraQtion_ObjectX(XpathKey).isDisplayed()){
							flag=true;
							break;
						}else{
							Thread.sleep(500);
						}
					}catch(Throwable t){
						Thread.sleep(500);
						//nothing to do
					}
					if(wait==NewNsfOnline_Element_Max_Wait_Time){
						throw new Exception("FAILED == Element is not displayed, after <"+( (NewNsfOnline_Element_Max_Wait_Time) )+"> seconds wait, please refer screenshot >> "+fnsScreenShot_Date_format());
					}
				} 
				fnsApps_Report_Logs("PASSED == Element is displayed having Xpath  >> "+XpathKey);	
			}catch(NoSuchWindowException W){
				//isTestCasePass = false;
				throw new Exception(W.getMessage());
			}catch(Throwable t){
				//isTestCasePass = false;
				fnsTake_Screen_Shot(XpathKey);
				fnsApps_Report_Logs("FAILED == Element is not displayed having Xpath  >> "+XpathKey+", plz see screenshot [ " + ""+XpathKey + fnsScreenShot_Date_format() + " ]. Getting Exception >> "+Throwables.getStackTraceAsString(t));
				throw new Exception("FAILED == Element is not displayed having Xpath  [ " + XpathKey + " ] , plz see screenshot [ " + ""+XpathKey + fnsScreenShot_Date_format() + " ]. Getting Exception >> "+Throwables.getStackTraceAsString(t));
			
			}
			return flag;
		}*/
		public void fngEnterMinValueForInspectableAttribute(Integer min_value) throws Exception{
			/*fnsApps_Report_Logs("Going to enter minimum value into Min column of the InspectableAttribute.");
			try {
				for (int i=0; i<=4; i++) {
					WebDriverWait elementwaitvar = new WebDriverWait(driver,Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
					driver.findElement(By.id("mainForm:tabView:prodQltyDT2:"+i+":mintxt")).isEnabled();
					Thread.sleep(1000);
					driver.findElement(By.id("mainForm:tabView:prodQltyDT2:"+i+":mintxt")).clear();
					Thread.sleep(1000);
					driver.findElement(By.id("mainForm:tabView:prodQltyDT2:"+i+":mintxt")).sendKeys(min_value);
					Thread.sleep(1000);
				} 
				Thread.sleep(2000);
			}catch(Throwable t){
				throw new Exception(t.getMessage());
			}*/
			
			try {
				for (int i=0; i<=4; i++) {
					String xpath = "//input[@id='mainForm:tabView:prodQltyDT2:"+i+":mintxt']";
					min_value = min_value+i;
					TestSuiteBase_MonitorPlan.WithOut_OR_fnClear_then_Type_for_InputNotWorkingInIE(xpath, Integer.toString(min_value));
				} 
			}catch(Throwable t){
				throw new Exception(Throwables.getStackTraceAsString(t));
			}
		}
		public void fngEnterMaxValueForInspectableAttribute(Integer max_value) throws Exception{
			/*fnsApps_Report_Logs("Going to enter minimum value into Min column of the InspectableAttribute.");
			try {
				for (int i=0; i<=4; i++) {
					WebDriverWait elementwaitvar = new WebDriverWait(driver,Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
					driver.findElement(By.id("mainForm:tabView:prodQltyDT2:"+i+":maxtxt")).isEnabled();
					Thread.sleep(1000);
					driver.findElement(By.id("mainForm:tabView:prodQltyDT2:"+i+":maxtxt")).clear();
					Thread.sleep(1000);
					driver.findElement(By.id("mainForm:tabView:prodQltyDT2:"+i+":maxtxt")).sendKeys(max_value);;
					Thread.sleep(1000);
				} 
				Thread.sleep(2000);
			}catch(Throwable t){
				throw new Exception(t.getMessage());
			}*/
				try {
					for (int i=0; i<=4; i++) {
						String xpath = "//input[@id='mainForm:tabView:prodQltyDT2:"+i+":maxtxt']";
						max_value = max_value+i;
						TestSuiteBase_MonitorPlan.WithOut_OR_fnClear_then_Type_for_InputNotWorkingInIE(xpath, Integer.toString(max_value));
					} 
				}catch(Throwable t){
					throw new Exception(Throwables.getStackTraceAsString(t));
				}
		}
		public void fngSelectAttributesFromAttributeTab() throws Throwable{
			fnsApps_Report_Logs("Going to enter minimum value into Min column of the InspectableAttribute.");
			try{
				for(int i=1;i<=5;i++){
					/*driver.findElement(By.xpath("//tbody[@id='mainForm:lookuptableid_data']/tr["+i+"]/td/div[@class='ui-chkbox ui-widget']")).isEnabled();
					Thread.sleep(500);
					driver.findElement(By.xpath("//tbody[@id='mainForm:lookuptableid_data']/tr["+i+"]/td/div[@class='ui-chkbox ui-widget']")).click();*/
					String xpath = "//tbody[@id='mainForm:lookuptableid_data']/tr["+i+"]/td/div[@class='ui-chkbox ui-widget']";
					TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(xpath);
					TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(xpath);
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(1);
				}
				//Thread.sleep(2000);
			}catch(Throwable t){
				throw new Exception(t.getMessage());
			}
		}	
	
	public void fnc_Click(String Xpath) throws Throwable{
		try{
			fnsGet_Element_Enabled(Xpath);
			fnsWait_and_Click(Xpath);
		}catch(Throwable t){
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}
	public void fnc_Type(String elementXPath, String value) throws Throwable{
		try{
			fnsGet_Element_Enabled(elementXPath);
			TestSuiteBase_MonitorPlan.WithOut_OR_fnClear(OR_TraQtion.getProperty(elementXPath));
			fnsWait_and_Type(elementXPath, value);
		}catch(Throwable t){
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
