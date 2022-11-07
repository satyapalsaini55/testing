package nsf.ecap.NSFOnline_Suite;



import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import nsf.ecap.util.TestUtil;
import nsf.ecap.util.Xls_Reader;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;

public class NSFOnline_IssueCount_Comparison extends TestSuiteBase_NSFOnline{
	
	public boolean fail = false;
	public boolean skip = true;
	public boolean NSFOnlineVersionScreenshotFlag = true;
	public boolean True_False_Flag = false;
	public boolean Insight_WindowOpen_Flag = false;
	
	public Integer count = -1;
	public Integer TotalCount = null;
	public Integer rows = null;
	public Integer cols = null;
	public Integer NSFOnline_ProductIssuesShort_ExcelRow_Count = null;
	public Integer FoodPartnersSouthall_Count_from_SearchIssueResult_Xls = null;
	public Integer FoodPartnersSouthall_Count_from_DB = null;
	public Integer NsfOnline_DIS_IssueCount = null;
	public Integer NsfOnline_PRQ_IssueCount = null;
	public Integer NsfOnline_FBY_IssueCount = null;
	public Integer NsfOnline_NC_IssueCount = null;
	public Integer NSFOnline_SearchIssueResult_ExcelRow_Count = null;
	public Integer iPulse_PRQ_SearchIssueResult_ExcelRow_Count = null;
	public Integer iPulse_IssueCount = null;
	public Integer NSFOnline_PRQ_Count_from_ProductIssuesShort_Excel = null;
	public Integer Excel_Filtered_RowCount = null;
	public Integer PRQ_PieChart_x=null;
	public Integer PRQ_PieChart_y=null;
	public Integer IM_Info_PieChart__PRQ_Count=0;
	public Integer NSFOnline_ProductQuality_Graph_ExcelRow_Count = null;
	
	public String ScenaioName = null;
	public String Text_Fetched = null;
	public String Search_SupplierID = null;
	public String SCA_Client_User_Supplier_Details_Issue_GoodOne = null;
	public String SCA_Client_User_Supplier_Details_Issue_BadOne = null;
	public String SCA_Client_User_Supplier_Details_Issue_BadTwo = null;
	public String NSFOnline_Reports_ManagementReports_ProductIssuesShort_DateOccurred_From = null;
	public String NSFOnline_Reports_ManagementReports_ProductIssuesShort_DateOccurred_To = null;
	public String IPulse_SearchScreen_Client_LK_Value_Case2 = null;
	public String IPulse_SearchScreen_Client_LK_Value_Case3_7 = null;
	public String IPulse_SearchScreen_Client_LK_Value_Case6 = null;
	public String IPulse_SearchScreen_Client_LK_Value_Case5 = null;
	public String IM_Info_PieChart__PRQ_Text = null;
		
	public String Autoit_scriptPath_Case3_NsfOnline =System.getProperty("user.dir")+"\\src\\nsf\\ecap\\Autoit_Scripts\\NsfOnline_Bs06_Case3_NsfOnline.exe";
	public String Download_XlsFile_Location_case3 =System.getProperty("user.home")+"\\Downloads\\Product Issues (short).xls"; 
	public String AutoIt_XlsFile_Location_Case3 =System.getProperty("user.home")+"\\Downloads\\ProductIssueShort_AutoIt.xlsx";
	public String AutoIt_LogFile_Location =System.getProperty("user.dir")+"\\src\\nsf\\ecap\\Autoit_Scripts\\AutoIt_Log.log";
	public String AutoIt_XlsFile_Location_Case4 =System.getProperty("user.home")+"\\Downloads\\SearchIssueResult_AutoIt.xlsx"; 
	public String Autoit_scriptPath_Case4_NsfOnline =System.getProperty("user.dir")+"\\src\\nsf\\ecap\\Autoit_Scripts\\NsfOnline_Bs06_Case4_NsfOnline.exe"; 
	public String AutoIt_XlsFile_Location_Case6 =System.getProperty("user.home")+"\\Downloads\\iPulse_PRQ_SearchIssuesResult_AutoIt.xlsx";
	public String Autoit_scriptPath_Case6_iPulse =System.getProperty("user.dir")+"\\src\\nsf\\ecap\\Autoit_Scripts\\NsfOnline_Bs06_Case6_iPulse.exe";
	public String Download_XlsFile_Location_case6 =System.getProperty("user.home")+"\\Downloads\\stdSearch.xls"; 
	public String Autoit_scriptPath_Case7_NsfOnline =System.getProperty("user.dir")+"\\src\\nsf\\ecap\\Autoit_Scripts\\NsfOnline_Bs06_Case7_NsfOnline.exe";
	public String Autoit_scriptPath_Case7_iPulse =System.getProperty("user.dir")+"\\src\\nsf\\ecap\\Autoit_Scripts\\NsfOnline_Bs06_Case7_iPulse.exe";
	public String Download_XlsFile_Location_case5 =System.getProperty("user.home")+"\\Downloads\\report.xls"; 
	public String AutoIt_XlsFile_Location_Case5 =System.getProperty("user.home")+"\\Downloads\\ProductQuality_Graph_Report_AutoIt.xlsx";
	public String Autoit_scriptPath_Case5_NsfOnline =System.getProperty("user.dir")+"\\src\\nsf\\ecap\\Autoit_Scripts\\NsfOnline_Bs06_Case5_NsfOnline.exe";
		
	public Xls_Reader AutoIt_NSFOnline_ProductIssuesShort_Xls = null;
	public Xls_Reader AutoIt_NSFOnline_SearchIssueResult_Xls = null;
	public Xls_Reader AutoIt_iPulse_PRQ_Issues_SearchResult_Xls = null;
	public Xls_Reader AutoIt_NSFOnline_ProductQuality_Graph_Xls = null;
	
		
	

	
	
	@Parameters({ "className" })
	@BeforeTest
		public void checkTestSkip(String className) throws Exception {
		
		isTestCasePass=true;
		count = -1;
		
		if (className.isEmpty()) {
			className = this.getClass().getSimpleName();
		}
		IsBrowserPresentAlready =false;
		fnsCheckClassLevelTestSkip(className);
	}
	
	
	

	
	
	

@Test( priority = 0)
public void Browser_Launch_and_Login_into_Insight_Application() throws Throwable{
	fnsApps_Report_Logs("################################## [BS-06] NSFOnline Issue Count Comparisons");
	if (!IsBrowserPresentAlready) {
		IsBrowserPresentAlready = fnsLaunchBrowserAndLogin();
		fnsApps_Report_Logs("Browser is launched and Successfully login into 'Insight' Application");
		Insight_WindowOpen_Flag = true;
		fncDelete_File(AutoIt_LogFile_Location);
	}
}
		
	
	
	
	
	
	
	@Test(dataProvider = "getTestData", priority = 1, dependsOnMethods={"Browser_Launch_and_Login_into_Insight_Application"})
	public void NsfOnline_IssuesCount_Comparsions_with_iPulse_IssuesCount_(String Case_No, String Scenario_Name, String Customer_Number, String Insight_Search_User_Name) throws Throwable{
		
		count++;
		Customer_No = Customer_Number.split("=")[1].trim();
		Insight_SearchUserName = Insight_Search_User_Name.split("=")[1].trim();
		CaseSerialNo = Case_No.split("=")[1];
		ScenaioName=Scenario_Name.trim();
		
		//Case 1
		Search_SupplierID = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Supplier_IDs").trim();
		SCA_Client_User_Supplier_Details_Issue_GoodOne = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Good_IDs").trim();
		SCA_Client_User_Supplier_Details_Issue_BadOne = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Bad_IDs").split(",")[0].trim();
		SCA_Client_User_Supplier_Details_Issue_BadTwo = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Bad_IDs").split(",")[1].trim();
		
		IPulse_SearchScreen_Client_LK_Value_Case2 =  fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "iPulse_Client_LK_Case2").trim();
		IPulse_SearchScreen_Client_LK_Value_Case3_7 =  fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "iPulse_Client_LK_Case3").trim();
		IPulse_SearchScreen_Client_LK_Value_Case6 =  fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "iPulse_Client_LK_Case6").trim();
		IPulse_SearchScreen_Client_LK_Value_Case5 =  fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "iPulse_Client_LK_Case5").trim();
		
		NSFOnline_Reports_ManagementReports_ProductIssuesShort_DateOccurred_From = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "DateOccurred_From").trim();
		NSFOnline_Reports_ManagementReports_ProductIssuesShort_DateOccurred_To = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "DateOccurred_To").trim();
		
		
		try {
			
			if (!(runmodes[count].equalsIgnoreCase("Y"))) {
				fnsApps_Report_Logs("****************************************************************************************************************************************");
				fnsApps_Report_Logs("######################### Case "+CaseSerialNo+" : Runmode of ("+Scenario_Name+") for Customer["+Customer_No+"]  is set to NO, So Skipping this Case.");
				skip=true;
				throw new SkipException( "Case "+CaseSerialNo+" :  Runmode of ("+Scenario_Name+") for Customer["+Customer_No+"]  is set to NO, So Skipping this Case.");
			}else{
				skip=false;
				fnsApps_Report_Logs("****************************************************************************************************************************************");
				fnsApps_Report_Logs("###################### Case "+CaseSerialNo+" : Execution Logs of Scenario[ "+Scenario_Name+" ] for Customer_No[ "+Customer_No+" ].");
		
							
				try{
					 if(!Insight_WindowOpen_Flag){
						driver.quit();
						fnsApps_Report_Logs("PASSED == Successfully Close ipulse Window.");
						fnsLaunchBrowserAndLogin();
					 	fnsApps_Report_Logs("Browser is launched and Successfully login into 'Insight' Application");
						Insight_WindowOpen_Flag = true;
					 }
					 CustomerName = fnsInsight_SerchCustomer_UserLinkClick(Customer_No, Insight_SearchUserName);
				}catch(Throwable t){
					driver.quit();
					IsBrowserPresentAlready = fnsLaunchBrowserAndLogin();
					throw new Exception("Insight Error : "+t.getMessage());
				}
				
				
				fncSwitchingTo_NsfOnline_from_Insight_AfterClickingOnUserName();
				
				try{
					try{
						if( (ScenaioName.contains("SCA_Client_User_Supplier_Details")) || (ScenaioName.contains("IM_Client_User_Product_Issues_Short_Report")) || (ScenaioName.contains("SCA_Client_User_Issue_Search_Results")) || (ScenaioName.contains("SCA_Client_User_Product_Issues_Short_Report")) || (ScenaioName.contains("SCA_Client_User_Snapshot_Pie_Charts"))){
							Actions act=new Actions(driver);
							act.moveByOffset(0, 0).click().sendKeys(Keys.TAB).sendKeys(Keys.TAB).sendKeys(Keys.ENTER).build().perform();
							Thread.sleep(2000);
							TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(3);
							Thread.sleep(4000);
							fnsApps_Report_Logs("PASSED == Successfully Click on the 'Open' link")	;
						}
						
						if(ScenaioName.contains("IM_Client_User_Issue_Search_Results")){
							Actions act=new Actions(driver);
							act.moveByOffset(0, 0).click().sendKeys(Keys.TAB).sendKeys(Keys.ENTER).build().perform();
							Thread.sleep(2000);
							TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(3);
							Thread.sleep(4000);
							fnsApps_Report_Logs("PASSED == Successfully Click on the 'Open' link")	;
						}
					}catch(Throwable t){
						fnsTake_Screen_Shot("OpenLinkClickFail");
						throw new Exception("FAILED == Unable to click on open link, plz refer screenshot [OpenLinkClickFail"+ fnsScreenShot_Date_format() + "].  Getting Exception >> "+t.getMessage());	}
				
				
				
					if(NSFOnlineVersionScreenshotFlag){
						fncNsfOnline_Version_Screenshot("IssueCountComparison_Customer_"+Customer_No);
						NSFOnlineVersionScreenshotFlag = false;
					}					
								
					
					
				
					// Case 1 (SCA Client user; Supplier details)	--- Confirm accuracy of Issues listing
					if(ScenaioName.contains("SCA_Client_User_Supplier_Details")){	
						True_False_Flag = false;
						
						fnsApps_Report_Logs("******************************************************************************************************************** ");
						fnsApps_Report_Logs("**************** Set's data are Supplier<"+Search_SupplierID+">, Good_IDs<"+SCA_Client_User_Supplier_Details_Issue_GoodOne+"> and Bad_IDs<"+fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Bad_IDs").trim()+">  *************");
											
						fncAjax_Link_Click_By_Passing_TwoAjaxXPathx("NSFOnline_CustomerTopMenu", "NSFOnline_Customer_Suppliers");
						TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(5);
						fnsGet_Element_Enabled("NSFOnline_ExpandSearchCriteriaLink");
						fnsWait_and_Click("NSFOnline_ExpandSearchCriteriaLink");			
												
						fnsGet_Element_Enabled("NSFOnline_MySuppliers_CustomerSearchCriteria_CustomerText");
						fnsWait_and_Type("NSFOnline_MySuppliers_CustomerSearchCriteria_CustomerText", Search_SupplierID);
												
						fnsGet_Element_Enabled("NSFOnline_MySuppliers_CustomerSearchCriteria_SearchBttn");
						fnsWait_and_Click("NSFOnline_MySuppliers_CustomerSearchCriteria_SearchBttn");
						TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(4);
						
						fnsGet_Element_Enabled("NSFOnline_IssueEscalation_SearchResultTable");
						TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element_BY_OFFSET(OR_NsfOnline.getProperty("NSFOnline_IssueEscalation_SearchResultTable"), 30, 10);
						String SearchResult_GetText=fnsGet_Field_TEXT("NSFOnline_IssueEscalation_SearchResultTable").toLowerCase().trim();
									
						try{
							Thread.sleep(500);
							assert (SearchResult_GetText.contains(Search_SupplierID.toLowerCase())):"FAILED == Supplier id <"+Search_SupplierID+"> for Data  [CustomerNo#"+Customer_No+" : UserName#"+Insight_SearchUserName+"]  is not found into the records Table, Please refer Screenshot >> SupplierNotFound"+fnsScreenShot_Date_format();
							fnsApps_Report_Logs("PASSED == Supplier id <"+Search_SupplierID+"> for Data  [CustomerNo#"+Customer_No+" : UserName#"+Insight_SearchUserName+"]  is found into the records Table.");
						}catch(Throwable t){
							fnsTake_Screen_Shot("SupplierNotFound");
							throw new Exception(t.getMessage());	}
						
						String SupplierId_Link_Xpath = "//a[contains(text(), '"+Search_SupplierID+"')]";
						TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(SupplierId_Link_Xpath);
						TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(4);
						fnsGet_Element_Enabled("Footer");
						
						fnsGet_Element_Enabled("NSFOnline_ViewSupplier_Issue_Header");
						fnsMove_To_MouseOver("NSFOnline_ViewSupplier_Issue_Header");
						
					
						
						WebElement Issue_Table_Element = fnsGet_OR_NsfOnline_ObjectX("NSFOnline_ViewSupplier_Issue_TableHeader");
						List<WebElement> Issue_Table_Obj = Issue_Table_Element.findElements(By.tagName("tr"));
						
						RowCount = 1;
						for(WebElement Issue_Table_Elements:Issue_Table_Obj){
							Text_Fetched = Issue_Table_Elements.getText().trim();
							if(Text_Fetched.contains(SCA_Client_User_Supplier_Details_Issue_GoodOne)){
								try{
									Actions act = new Actions(driver);
									act.moveToElement(Issue_Table_Elements).doubleClick().build().perform();
									Thread.sleep(1500);
									assert Text_Fetched.contains(SCA_Client_User_Supplier_Details_Issue_GoodOne):"FAILED == <Good> Issue Id <"+SCA_Client_User_Supplier_Details_Issue_GoodOne+"> is not found into the Table, please refer screen shot >>IssueIdNotFound"+fnsScreenShot_Date_format();
									fnsApps_Report_Logs("PASSED == *********** Successfully verified that issue id <"+SCA_Client_User_Supplier_Details_Issue_GoodOne+"> exists into the table.");
								}catch(Throwable t){
									fnsTake_Screen_Shot("IssueIdNotFound");
									throw new Exception(t.getMessage());
								}
							}
							
							if( (Text_Fetched.contains(SCA_Client_User_Supplier_Details_Issue_BadOne)) || (Text_Fetched.contains(SCA_Client_User_Supplier_Details_Issue_BadTwo))){
								Actions act = new Actions(driver);
								act.moveToElement(Issue_Table_Elements).doubleClick().build().perform();
								Thread.sleep(1500);
								True_False_Flag = true;
							}					
						}
						
						if(True_False_Flag){
							fnsTake_Screen_Shot("IssueId_Found");
							throw new Exception("FAILED == <BAD> Issue Id <"+SCA_Client_User_Supplier_Details_Issue_BadOne+"/"+SCA_Client_User_Supplier_Details_Issue_BadTwo+"> is found into the Table, please refer screen shot >>IssueId_Found"+fnsScreenShot_Date_format());
						}else{
							fnsApps_Report_Logs("PASSED == *********** Successfully verified that both issue ids <"+SCA_Client_User_Supplier_Details_Issue_BadOne+","+SCA_Client_User_Supplier_Details_Issue_BadTwo+">are not exsist into the table.");
						}
							
					}
				
					
					
					
					//Case 2  -- (IM Client user; Issue search results) -- Confirm accuracy of results list and extracts
					if(ScenaioName.contains("IM_Client_User_Issue_Search_Results")){	
						TotalCount = fncReturn_Issues_TotalCount_andClick_on_ExpandSearchCriteria();
						TestSuiteBase_MonitorPlan.WithOut_OR_fnDD_SelectValue_By_SelectClass(OR_NsfOnline.getProperty("NSFOnline_Issues_IssueSearchResult_ParentStatus_DD"), "Draft");
						Thread.sleep(2500);
												
						fnsGet_Element_Enabled("NSFOnline_MySuppliers_CustomerSearchCriteria_SearchBttn");
						fnsWait_and_Click("NSFOnline_MySuppliers_CustomerSearchCriteria_SearchBttn");
						TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(4);
						
						TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element(OR_NsfOnline.getProperty("Footer"));
						Integer DraftStatus_IssueTotalCount = TestSuiteBase_MonitorPlan.WithOut_OR_Retrun_TotalRowCount_OfTable(OR_NsfOnline.getProperty("NSFOnline_Issues_IssueSearchResult_TableHeader"));
						
						Text_Fetched = fnsGet_Field_TEXT("NSFOnline_Issues_IssueSearchResult_TableHeader").toLowerCase().trim();
						if(Text_Fetched.contains("no items were found")){
							DraftStatus_IssueTotalCount = DraftStatus_IssueTotalCount-1;
						}
						
						TotalCount = TotalCount-DraftStatus_IssueTotalCount;
						fnsApps_Report_Logs("PASSED == Successfully fetched Total issues count with Filter <"+TotalCount+"> and with Draft status Filter<"+DraftStatus_IssueTotalCount+">.");
									
					}
					
						
					
		
					//Case 3  IM Client user; Product Issues (Short) report -- Confirm accuracy of report results
					if( (ScenaioName.contains("IM_Client_User_Product_Issues_Short_Report"))  ){
						fncNsfOnline_Case3_and_Case7(Autoit_scriptPath_Case3_NsfOnline);	
					}
					
						
					//Case 4 [Supplier user (IM Supplier User, CO access); Issue search results] -- Confirm accuracy of results list and extracts
					if(ScenaioName.contains("IM_Supplier_User_and_CO_Access_Issue_Search_Results")){	
						fncAjax_Link_Click_By_Passing_TwoAjaxXPathx("NSFOnline_Issues_TopMenu_Ajax_Link", "NSFOnline_Issues_Ajax_Link");
						TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(5);
					
						fncDelete_File(AutoIt_XlsFile_Location_Case4);
															
					
						fncSave_and_Open_File_from_IE_Browser("NSFOnline_Issue_IssueSearchResult_Export_Bttn", "Yes");
						fncCreateNewExcel_and_Run_Autoit_Scripts(AutoIt_XlsFile_Location_Case4, Autoit_scriptPath_Case4_NsfOnline);
						
						try{
							AutoIt_NSFOnline_SearchIssueResult_Xls = new Xls_Reader(AutoIt_XlsFile_Location_Case4);
							NSFOnline_SearchIssueResult_ExcelRow_Count = AutoIt_NSFOnline_SearchIssueResult_Xls.getRowCount("FirstSheet");
							NSFOnline_SearchIssueResult_ExcelRow_Count = NSFOnline_SearchIssueResult_ExcelRow_Count-1;
							fnsApps_Report_Logs("PASSED == Download Excel Row Count is <"+NSFOnline_SearchIssueResult_ExcelRow_Count+">");
							if(NSFOnline_SearchIssueResult_ExcelRow_Count<1){
								throw new Exception ("Downloaded Excel <"+AutoIt_XlsFile_Location_Case4+"> rows count is coming Zero, please refer screen shot >> ExcelRowCountZero"+fnsScreenShot_Date_format());
							}
						}catch(Throwable t){
							fnsTake_Screen_Shot("ExcelRowCountZero");
							Thread.sleep(5000);
							throw new Exception(t.getMessage());
						}
					
									// Extracting 'Food Partners - Southall' data from excel
						Object[][] Excel_Data1 = getExcelData_for_DataProvider(AutoIt_NSFOnline_SearchIssueResult_Xls, "FirstSheet");
						FoodPartnersSouthall_Count_from_SearchIssueResult_Xls = 0;	
						for(int i=0; i<rows; i++){
							for(int k=0; k<cols; k++){
								String abc = (String) Excel_Data1[i][k];
								if(abc.trim().contains("Food Partners - Southall")){
									FoodPartnersSouthall_Count_from_SearchIssueResult_Xls++;
								}
							}
						}
						
						FoodPartnersSouthall_Count_from_DB = fncQuery_to_Retun_Total_Number_of_Result_Row_Count("SELECT DISTINCT issue_id FROM im_issue iss INNER JOIN im_issue_sub_type iist ON iss.issue_sub_type_seq = iist.seq INNER JOIN im_issue_type_customer iitc ON iist.issue_type_cus_seq = iitc.seq LEFT JOIN ec_audit_trail eat ON eat.entity_id = TO_CHAR(iss.seq) AND eat.entity_master_seq = 3 AND ec_entity_status = 106 WHERE iss.suppliers_seq = 306881 AND iitc.im_issue_type_seq <> 6 AND (iss.parent_status = 'CLOSED' OR eat.seq IS NOT NULL)");
						try{
							assert(FoodPartnersSouthall_Count_from_SearchIssueResult_Xls.equals(FoodPartnersSouthall_Count_from_DB)):
								"FAILED == Supplier 'Food Partners - Southall' count from Excel<"+FoodPartnersSouthall_Count_from_SearchIssueResult_Xls+"> is not matched with DB count<"+FoodPartnersSouthall_Count_from_DB+">, please refer screen shot >> FoodPartners_Southall_Count_MatchFail"+fnsScreenShot_Date_format();
							fnsApps_Report_Logs("PASSED == Supplier 'Food Partners - Southall' count from Excel<"+FoodPartnersSouthall_Count_from_SearchIssueResult_Xls+"> is successfully matched with DB count<"+FoodPartnersSouthall_Count_from_DB+">.");
						}catch(Throwable t){
							fnsTake_Screen_Shot("FoodPartners_Southall_Count_MatchFail");
							throw new Exception(t.getMessage());
						}
					
					}
					
					
					// Case 5 SCA Client user; snapshot pie charts	--- Confirm accuracy of figures and drill down extracts
					if(ScenaioName.contains("SCA_Client_User_Snapshot_Pie_Charts")){
						
						fncDelete_File(Download_XlsFile_Location_case5);
						fncDelete_File(AutoIt_XlsFile_Location_Case5);
						
						fnsGet_Element_Enabled("NSFOnline_MySnapshot_IssuesTAB");
						fnsWait_and_Click("NSFOnline_MySnapshot_IssuesTAB");
						TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(3);
						
						TestSuiteBase_MonitorPlan.WithOut_OR_fnDD_SelectValue_By_SelectClass(OR_NsfOnline.getProperty("NSFOnline_MySnapshot_IssuesTAB_WhereDateIssueOccurredIs_DD"), "Last Year");
						Thread.sleep(1000);
						
						fnsGet_Element_Enabled("NSFOnline_MySnapshot_IssuesTAB_GO_Bttn");
						fnsWait_and_Click("NSFOnline_MySnapshot_IssuesTAB_GO_Bttn");
						TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(3);
						
						fnsGet_Element_Enabled("NsfOnline_CARSummary_FirstGraph_DivXpath");
						Thread.sleep(1000);
						WebElement IM_Info_Div=fnsGet_OR_NsfOnline_ObjectX("NsfOnline_CARSummary_FirstGraph_DivXpath");
						List<WebElement> IM_Info_Div_Elements=IM_Info_Div.findElements(By.tagName("area"));
					
						
											
						int i=0;
						for(WebElement Elements:IM_Info_Div_Elements){
							String IM_Info_PieChart_MouseOverAttributeTextArray=(IM_Info_Div_Elements.get(i).getAttribute("onmouseover"));
							if(IM_Info_PieChart_MouseOverAttributeTextArray.contains("Product Quality")){
						
								String[] IM_Info_PieChart__PRQ_TextArray=(IM_Info_Div_Elements.get(i).getAttribute("onmouseover")).split("\\,");
								IM_Info_PieChart__PRQ_Text=IM_Info_PieChart__PRQ_TextArray[2].toString();
								IM_Info_PieChart__PRQ_Text=IM_Info_PieChart__PRQ_Text.split("\\'")[1];
								IM_Info_PieChart__PRQ_Text=IM_Info_PieChart__PRQ_Text.split("Product Quality")[0].trim();
								IM_Info_PieChart__PRQ_Count = Integer.parseInt(IM_Info_PieChart__PRQ_Text);
																			
								Point Div_Graph_xy=Elements.getLocation();
								PRQ_PieChart_x=(Div_Graph_xy.getX())+100;
								PRQ_PieChart_y=Div_Graph_xy.getY();
								break;	
							}
									
							i++;
						}
						try{
							assert IM_Info_PieChart__PRQ_Count>0:"FAILED == 'Product Quality' issue Count on graph is coming Zero, please refer screenshot >>PRQ_Graph_Count_Is_Zero"+fnsScreenShot_Date_format();
							fnsApps_Report_Logs("PASSED == Successfully get the 'Product Quality' issues count("+IM_Info_PieChart__PRQ_Count+") from graph.");
						}catch(Throwable t){
							fnsTake_Screen_Shot("PRQ_Graph_Count_Is_Zero");
							throw new Exception(t.getMessage());
						}
										
						//Clicking on PRQ Pie Chart section
						Actions act5=new Actions(driver);
						act5.moveToElement(IM_Info_Div, 0, 0).moveToElement(IM_Info_Div, PRQ_PieChart_x, PRQ_PieChart_y).click().build().perform();
						fnsApps_Report_Logs("PASSED == Successfully click on 'Product Quality' of the Graph at Locaation ("+PRQ_PieChart_x+", "+PRQ_PieChart_y+").");
						Thread.sleep(10000);
						
						//open browser dialog box
						for(int a=0; a<=Long.parseLong(CONFIG.getProperty("ElementWaitTime")); a++){ 
							ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
							tabsCount = tabs.size();
							if(tabsCount==2){
								fnsApps_Report_Logs("PASSED == Successfully open Browser dialog box (To save file).");
								break;
							}else{
								Thread.sleep(2000);
							}
							if(a==Long.parseLong(CONFIG.getProperty("ElementWaitTime"))){
								fnsTake_Screen_Shot("BrowserDialogBox_Not_Open");
								throw new Exception(" Browser dialog box (To save file) is not getting open after "+(10+(Long.parseLong(CONFIG.getProperty("ElementWaitTime"))*2))+"Seconds wait, please refer screenshot >> BrowserDialogBox_Not_Open"+fnsScreenShot_Date_format());
							}
						}
														
						fncSave_and_Open_File_from_IE_Browser("", "Yes");
						fnsApps_Report_Logs("PASSED == ****** Successfully 'Save' and open file, after clicking on 'Product Quality' graph Link.");
						fncCreateNewExcel_and_Run_Autoit_Scripts(AutoIt_XlsFile_Location_Case5, Autoit_scriptPath_Case5_NsfOnline);
						
						try{
							AutoIt_NSFOnline_ProductQuality_Graph_Xls = new Xls_Reader(AutoIt_XlsFile_Location_Case5);
							NSFOnline_ProductQuality_Graph_ExcelRow_Count = AutoIt_NSFOnline_ProductQuality_Graph_Xls.getRowCount("FirstSheet");
							NSFOnline_ProductQuality_Graph_ExcelRow_Count = NSFOnline_ProductQuality_Graph_ExcelRow_Count-1;
							fnsApps_Report_Logs("PASSED == Successfully get 'Product Quality' issues count from Excel is <"+NSFOnline_ProductQuality_Graph_ExcelRow_Count+">.");
							if(NSFOnline_ProductQuality_Graph_ExcelRow_Count<1){
								throw new Exception ("Downloaded Excel <"+Download_XlsFile_Location_case5+"> rows count is coming Zero, please refer screen shot >> ExcelRowCountZero"+fnsScreenShot_Date_format());
							}
						}catch(Throwable t){
							fnsTake_Screen_Shot("ExcelRowCountZero");
							Thread.sleep(5000);
							throw new Exception(t.getMessage());
						}
						
						try{
							assert NSFOnline_ProductQuality_Graph_ExcelRow_Count.equals(IM_Info_PieChart__PRQ_Count):"FAILED == 'Product Quality' issue Count from Excel<"+NSFOnline_ProductQuality_Graph_ExcelRow_Count+"> is not matched with Graph count<"+IM_Info_PieChart__PRQ_Count+">, please refer screenshot >>PRQ_ExcelCount_NotMatch_with_GraphCount"+fnsScreenShot_Date_format();
							fnsApps_Report_Logs("PASSED == ****** Successfully verified that 'Product Quality' issue Count from Excel<"+NSFOnline_ProductQuality_Graph_ExcelRow_Count+"> is matched with Graph count<"+IM_Info_PieChart__PRQ_Count+">.");
						}catch(Throwable t){
							fnsTake_Screen_Shot("PRQ_ExcelCount_NotMatch_with_GraphCount");
							throw new Exception(t.getMessage());
						}
							
					}
									
					
					//Case 6  -- (SCA Client user; Issue search results) -- Confirm accuracy of results list and extracts
					if(ScenaioName.contains("SCA_Client_User_Issue_Search_Results")){
						
						TotalCount = fncReturn_Issues_TotalCount_andClick_on_ExpandSearchCriteria();
						NsfOnline_DIS_IssueCount = fncReturn_Issues_Count_By_Selecting_IssueTypeDDValue(fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "IssueType_DD").split(",")[0]);
						NsfOnline_FBY_IssueCount = fncReturn_Issues_Count_By_Selecting_IssueTypeDDValue(fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "IssueType_DD").split(",")[1]);
						NsfOnline_NC_IssueCount = fncReturn_Issues_Count_By_Selecting_IssueTypeDDValue(fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "IssueType_DD").split(",")[2]);
						NsfOnline_PRQ_IssueCount = fncReturn_Issues_Count_By_Selecting_IssueTypeDDValue(fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "IssueType_DD").split(",")[3]);
						
						Integer TotalIssue_Count_from_individual_IssueType = NsfOnline_DIS_IssueCount + NsfOnline_FBY_IssueCount + NsfOnline_NC_IssueCount + NsfOnline_PRQ_IssueCount ;
						
						try{
							assert TotalIssue_Count_from_individual_IssueType.equals(TotalCount):
								"FAILED == Individual IssueType[DIS<"+NsfOnline_DIS_IssueCount+">, FBY<"+NsfOnline_FBY_IssueCount+">, NC<"+NsfOnline_NC_IssueCount+">, PRQ<"+NsfOnline_PRQ_IssueCount+">] search result Total Count"+
									"<"+TotalIssue_Count_from_individual_IssueType+"> is not matched with default search count<"+TotalCount+">,Please refer screenshot >> IssueCountTotalNotMatch"+fnsScreenShot_Date_format();
							fnsApps_Report_Logs("FAILED == ****** Successfully verified that Individual IssueType[DIS<"+NsfOnline_DIS_IssueCount+">, FBY<"+NsfOnline_FBY_IssueCount+">, NC<"+NsfOnline_NC_IssueCount+">, PRQ<"+NsfOnline_PRQ_IssueCount+">]"+
									" search result Total Count<"+TotalIssue_Count_from_individual_IssueType+"> is matched with default search count<"+TotalCount+">");
						}catch(Throwable t){
							fnsTake_Screen_Shot("IssueCountTotalNotMatch");
							throw new Exception(t.getMessage());
						}	
					}
					
					
				//Case 7  SCA Client user; Product Issues (Short) report -- Confirm accuracy of report results
				if( (ScenaioName.contains("SCA_Client_User_Product_Issues_Short_Report")) ){
					fncNsfOnline_Case3_and_Case7(Autoit_scriptPath_Case7_NsfOnline);	
				}
					
			}catch(Throwable t){
				driver.close();
				fnsApps_Report_Logs("PASSED == Successfully Close NSFOnline Window.");
				driver.switchTo().window(MainWindowHandle);
				fnsApps_Report_Logs("PASSED == Successfully Switch to Insight Window.");
				throw new Exception(Throwables.getStackTraceAsString(t)+"......................");	
			}
			
			driver.close();
			fnsApps_Report_Logs("PASSED == Successfully Close NSFOnline Window.");
			driver.switchTo().window(MainWindowHandle);
			fnsApps_Report_Logs("PASSED == Successfully Switch to Insight Window.");
			
			
			
			
			
			
//		##################### I-pulse scripts ######################################################################################################################################
			try{
				//Case 2, 3, 5, 6 and 7 continue
				if( (ScenaioName.contains("IM_Client_User_Issue_Search_Results")) || (ScenaioName.contains("IM_Client_User_Product_Issues_Short_Report")) || (ScenaioName.contains("SCA_Client_User_Snapshot_Pie_Charts")) || (ScenaioName.contains("SCA_Client_User_Issue_Search_Results")) || (ScenaioName.contains("SCA_Client_User_Product_Issues_Short_Report")) ){	
					driver.quit();
					Thread.sleep(1500);
					
					//Switching into I-pulse application
					/*fnLaunch_Ipulse_BrowserAndLogin();
					fnsApps_Report_Logs("PASSED == Browser is launched and Successfully login into 'I-Pulse' Application.");*/
					
					TestSuiteBase_MonitorPlan.fnsLaunchBrowserAndLogin();
					
					fncAjax_Link_Click_By_Passing_TwoAjaxXPathx("IPulse_Menu_Ajax", "IPulse_Search_Issues");
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(5);		
					Insight_WindowOpen_Flag = false;
				}
				
				
				//Case 2 continue
				if( (ScenaioName.contains("IM_Client_User_Issue_Search_Results")) ){	
								
					fnsGet_Element_Enabled("IPulse_SearchScreen_Client_LK");
					fnsWait_and_Click("IPulse_SearchScreen_Client_LK");
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
					fnsWait_and_Type("IPulse_SearchScreen_Client_LK_Value", IPulse_SearchScreen_Client_LK_Value_Case2);
					TestSuiteBase_MonitorPlan.fnsLookup_RadioBttn_Select();
					
					fnsGet_Element_Enabled("IPulse_SearchScreen_Search_Bttn");
					fnsWait_and_Click("IPulse_SearchScreen_Search_Bttn");
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(5);
					
					fnsGet_Element_Enabled("IPulse_SearchScreen_Pagination_Div");
					fnsMove_To_MouseOver("IPulse_SearchScreen_Pagination_Div");
					String Pagination_Text = fnsGet_Field_TEXT("IPulse_SearchScreen_Pagination_Div").split("of")[1].trim();
					iPulse_IssueCount = Integer.parseInt(Pagination_Text);
				
					try{
						Thread.sleep(500);
						assert (iPulse_IssueCount.equals(TotalCount)):"FAILED == iPulse issues count<"+iPulse_IssueCount+"> is not matched with NsfOnline issue count<"+TotalCount+"> for Customer <"+IPulse_SearchScreen_Client_LK_Value_Case2+">, Please refer Screenshot >> IssueCountNotMatch"+fnsScreenShot_Date_format();
						fnsApps_Report_Logs("PASSED == *************  Successfully verified that iPulse issues count<"+iPulse_IssueCount+"> is matched with NsfOnline issue count<"+TotalCount+"> for Customer <"+IPulse_SearchScreen_Client_LK_Value_Case2+">.");
					}catch(Throwable t){
						fnsTake_Screen_Shot("IssueCountNotMatch");
						throw new Exception(t.getMessage());	}
				}
				
				//Case 6  and 7  continue
				if( (ScenaioName.contains("SCA_Client_User_Issue_Search_Results")) || (ScenaioName.contains("SCA_Client_User_Product_Issues_Short_Report")) ){
					fncDelete_File(AutoIt_XlsFile_Location_Case6);
					fncDelete_File(Download_XlsFile_Location_case6);
										
					fnsGet_Element_Enabled("IPulse_SearchScreen_Client_LK");
					fnsWait_and_Click("IPulse_SearchScreen_Client_LK");
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
					fnsWait_and_Type("IPulse_SearchScreen_Client_LK_Value", IPulse_SearchScreen_Client_LK_Value_Case6);
					TestSuiteBase_MonitorPlan.fnsLookup_RadioBttn_Select();
					
					TestSuiteBase_MonitorPlan.WithOut_OR_fnDD_SelectValue_By_MatchingText(OR_NsfOnline.getProperty("IPulse_SearchScreen_IssueType_DD_Click"), OR_NsfOnline.getProperty("IPulse_SearchScreen_IssueType_DD_Value"), "li", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "IssueType_DD").split(",")[3]);
										
					fnsGet_Element_Enabled("IPulse_SearchScreen_Search_Bttn");
					fnsWait_and_Click("IPulse_SearchScreen_Search_Bttn");
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(5);
					
					fnsGet_Element_Enabled("IPulse_SearchScreen_Pagination_Div");
				}
				//Case 6 continue
				if( (ScenaioName.contains("SCA_Client_User_Issue_Search_Results")) ){
					fnsMove_To_MouseOver("IPulse_SearchScreen_Pagination_Div");
					String Pagination_Text = fnsGet_Field_TEXT("IPulse_SearchScreen_Pagination_Div").split("of")[1].trim();
					iPulse_IssueCount = Integer.parseInt(Pagination_Text);
					try{
						Thread.sleep(500);
						assert (iPulse_IssueCount.equals(NsfOnline_PRQ_IssueCount)):"FAILED == iPulse PRQ issues count<"+iPulse_IssueCount+"> is not matched with NsfOnline PRQ issue count<"+NsfOnline_PRQ_IssueCount+"> for Customer <"+IPulse_SearchScreen_Client_LK_Value_Case6+">, Please refer Screenshot >> PRQ_IssueCountNotMatch"+fnsScreenShot_Date_format();
						fnsApps_Report_Logs("PASSED == *************  Successfully verified that iPulse PRQ issues count<"+iPulse_IssueCount+"> is matched with NsfOnline PRQ issue count<"+NsfOnline_PRQ_IssueCount+"> for Customer <"+IPulse_SearchScreen_Client_LK_Value_Case6+">.");
					}catch(Throwable t){
						fnsTake_Screen_Shot("PRQ_IssueCountNotMatch");
						throw new Exception(t.getMessage());	}
					
					fncSave_and_Open_File_from_IE_Browser("IPulse_SearchScreen_Export_Bttn", "Yes");
					fncCreateNewExcel_and_Run_Autoit_Scripts(AutoIt_XlsFile_Location_Case6, Autoit_scriptPath_Case6_iPulse);
					
					try{
						AutoIt_iPulse_PRQ_Issues_SearchResult_Xls = new Xls_Reader(AutoIt_XlsFile_Location_Case6);
						iPulse_PRQ_SearchIssueResult_ExcelRow_Count = AutoIt_iPulse_PRQ_Issues_SearchResult_Xls.getRowCount("FirstSheet");
						iPulse_PRQ_SearchIssueResult_ExcelRow_Count = iPulse_PRQ_SearchIssueResult_ExcelRow_Count-1;
						fnsApps_Report_Logs("PASSED == Download Excel Row Count is <"+iPulse_PRQ_SearchIssueResult_ExcelRow_Count+">");
						if(iPulse_PRQ_SearchIssueResult_ExcelRow_Count<1){
							throw new Exception ("Downloaded Excel <"+Download_XlsFile_Location_case6+"> rows count is coming Zero, please refer screen shot >> ExcelRowCountZero"+fnsScreenShot_Date_format());
						}
					}catch(Throwable t){
						fnsTake_Screen_Shot("ExcelRowCountZero");
						Thread.sleep(5000);
						throw new Exception(t.getMessage());
					}
				
					try{
						Thread.sleep(500);
						assert (iPulse_IssueCount.equals(iPulse_PRQ_SearchIssueResult_ExcelRow_Count)):"FAILED == iPulse PRQ issues count<"+iPulse_IssueCount+"> is not matched with Export Excel PRQ issue count<"+NsfOnline_PRQ_IssueCount+"> for Customer <"+IPulse_SearchScreen_Client_LK_Value_Case6+">, Please refer Screenshot >> PRQ_IssueCountNotMatch"+fnsScreenShot_Date_format();
						fnsApps_Report_Logs("PASSED == *************  Successfully verified that iPulse PRQ issues count<"+iPulse_IssueCount+"> is matched with Export Excel PRQ issue count<"+NsfOnline_PRQ_IssueCount+"> for Customer <"+IPulse_SearchScreen_Client_LK_Value_Case6+">.");
					}catch(Throwable t){
						fnsTake_Screen_Shot("PRQ_Excel_IssueCountNotMatch");
						throw new Exception(t.getMessage());	}	
				}
				
				//Case 7 continue	
				if( (ScenaioName.contains("SCA_Client_User_Product_Issues_Short_Report")) ){
					fncSave_and_Open_File_from_IE_Browser("IPulse_SearchScreen_Export_Bttn", "Yes");
					fncCreateNewExcel_and_Run_Autoit_Scripts(AutoIt_XlsFile_Location_Case6, Autoit_scriptPath_Case7_iPulse);
					
					try{
						AutoIt_iPulse_PRQ_Issues_SearchResult_Xls = new Xls_Reader(AutoIt_XlsFile_Location_Case6);
						iPulse_PRQ_SearchIssueResult_ExcelRow_Count = AutoIt_iPulse_PRQ_Issues_SearchResult_Xls.getRowCount("FirstSheet");
						iPulse_PRQ_SearchIssueResult_ExcelRow_Count = iPulse_PRQ_SearchIssueResult_ExcelRow_Count-1;
						fnsApps_Report_Logs("PASSED == Download Excel Row Count is <"+iPulse_PRQ_SearchIssueResult_ExcelRow_Count+">");
						if(iPulse_PRQ_SearchIssueResult_ExcelRow_Count<1){
							throw new Exception ("Downloaded Excel <"+Download_XlsFile_Location_case6+"> rows count is coming Zero, please refer screen shot >> ExcelRowCountZero"+fnsScreenShot_Date_format());
						}
					}catch(Throwable t){
						fnsTake_Screen_Shot("ExcelRowCountZero");
						Thread.sleep(5000);
						throw new Exception(t.getMessage());
					}
					Excel_Filtered_RowCount = 0;
					rows=AutoIt_NSFOnline_ProductIssuesShort_Xls.getRowCount("FirstSheet");
					fncReturn_Excel_ColumnNumber_by_Matching_ColumnName(AutoIt_NSFOnline_ProductIssuesShort_Xls,"FirstSheet","Complaint Type");
					for(int rowNum=0;rowNum<=rows-1;rowNum++){
							String Excel_Cell_Value =  AutoIt_NSFOnline_ProductIssuesShort_Xls.getCellData("FirstSheet", cols, rowNum+1);
							if ( Excel_Cell_Value.contains("Product Quality") ){
								Excel_Filtered_RowCount++;
								NSFOnline_PRQ_Count_from_ProductIssuesShort_Excel = Excel_Filtered_RowCount;
							}
					}					
										
					Excel_Filtered_RowCount =0;
					rows=AutoIt_iPulse_PRQ_Issues_SearchResult_Xls.getRowCount("FirstSheet");
					fncReturn_Excel_ColumnNumber_by_Matching_ColumnName(AutoIt_iPulse_PRQ_Issues_SearchResult_Xls,"FirstSheet","Date Of Issue");
					for(int rowNum=0;rowNum<=rows-1;rowNum++){
							String Excel_Cell_Value =  AutoIt_iPulse_PRQ_Issues_SearchResult_Xls.getCellData("FirstSheet", cols, rowNum+1);
							if ( Excel_Cell_Value.startsWith("8/") && Excel_Cell_Value.endsWith("/14") ){
								Excel_Filtered_RowCount++;
								iPulse_PRQ_SearchIssueResult_ExcelRow_Count = Excel_Filtered_RowCount;
							}
					}
					try{
						Thread.sleep(500);
						assert (iPulse_PRQ_SearchIssueResult_ExcelRow_Count.equals(NSFOnline_PRQ_Count_from_ProductIssuesShort_Excel)):"FAILED == NsfOnline 'Product Issues Short' count<"+NSFOnline_PRQ_Count_from_ProductIssuesShort_Excel+">  for date range[August,2014] is not matched with iPulse Export Excel PRQ issue count<"+iPulse_PRQ_SearchIssueResult_ExcelRow_Count+"> after date range[August,2014] filter for Customer <"+IPulse_SearchScreen_Client_LK_Value_Case6+">, Please refer Screenshot >> PRQ_IssueCountNotMatch"+fnsScreenShot_Date_format();
						fnsApps_Report_Logs("PASSED == *************  Successfully verified that NsfOnline 'Product Issues Short' count<"+NSFOnline_PRQ_Count_from_ProductIssuesShort_Excel+">  for date range[August,2014] is matched with iPulse Export Excel PRQ issue count<"+iPulse_PRQ_SearchIssueResult_ExcelRow_Count+"> after date range[August,2014] filter for Customer <"+IPulse_SearchScreen_Client_LK_Value_Case6+">.");
					}catch(Throwable t){
						fnsTake_Screen_Shot("PRQ_Excel_IssueCountNotMatch");
						throw new Exception(t.getMessage());	}		
				}
				
				//Case 5 continue
				if( (ScenaioName.contains("SCA_Client_User_Snapshot_Pie_Charts")) ){
					fnsGet_Element_Enabled("IPulse_SearchScreen_Client_LK");
					fnsWait_and_Click("IPulse_SearchScreen_Client_LK");
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
					fnsWait_and_Type("IPulse_SearchScreen_Client_LK_Value", IPulse_SearchScreen_Client_LK_Value_Case5);
					TestSuiteBase_MonitorPlan.fnsLookup_RadioBttn_Select();
					
					TestSuiteBase_MonitorPlan.WithOut_OR_fnDD_SelectValue_By_MatchingText(OR_NsfOnline.getProperty("IPulse_SearchScreen_IssueType_DD_Click"), OR_NsfOnline.getProperty("IPulse_SearchScreen_IssueType_DD_Value"), "li", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "IssueType_DD").split(",")[3]);
					
					fnsGet_Element_Enabled("IPulse_SearchScreen_DateOfIssueFrom");
					fnsWait_and_Type("IPulse_SearchScreen_DateOfIssueFrom", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "iPulse_DateOfIssueFrom").trim());
					
					fnsGet_Element_Enabled("IPulse_SearchScreen_DateOfIssueTo");
					fnsWait_and_Type("IPulse_SearchScreen_DateOfIssueTo", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "iPulse_DateOfIssueTo").trim());
					
					
					fnsGet_Element_Enabled("IPulse_SearchScreen_Search_Bttn");
					fnsWait_and_Click("IPulse_SearchScreen_Search_Bttn");
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(5);
					
					fnsGet_Element_Enabled("IPulse_SearchScreen_Pagination_Div");
					
					fnsMove_To_MouseOver("IPulse_SearchScreen_Pagination_Div");
					String Pagination_Text = fnsGet_Field_TEXT("IPulse_SearchScreen_Pagination_Div").split("of")[1].trim();
					iPulse_IssueCount = Integer.parseInt(Pagination_Text);
					
					Excel_Filtered_RowCount = 0;
					rows=AutoIt_NSFOnline_ProductQuality_Graph_Xls.getRowCount("FirstSheet");
					fncReturn_Excel_ColumnNumber_by_Matching_ColumnName(AutoIt_NSFOnline_ProductQuality_Graph_Xls,"FirstSheet","Status");
					for(int rowNum=0;rowNum<=rows-1;rowNum++){
							String Excel_Cell_Value =  AutoIt_NSFOnline_ProductQuality_Graph_Xls.getCellData("FirstSheet", cols, rowNum+1);
							if ( Excel_Cell_Value.contains("Draft") ){
								Excel_Filtered_RowCount++;
							}
					}
					NSFOnline_ProductQuality_Graph_ExcelRow_Count = NSFOnline_ProductQuality_Graph_ExcelRow_Count-Excel_Filtered_RowCount;
					
					try{
						Thread.sleep(500);
						assert (iPulse_IssueCount.equals(NSFOnline_ProductQuality_Graph_ExcelRow_Count)):"FAILED == iPulse 'Product Quality' issues count<"+iPulse_IssueCount+"> is not matched with NsfOnline 'Product Quality' issue count<"+NSFOnline_ProductQuality_Graph_ExcelRow_Count+"> from download Excel by clicking on 'Product Quality' Graph section for Customer <"+IPulse_SearchScreen_Client_LK_Value_Case5+">, Please refer Screenshot >> PRQ_IssueCountNotMatch"+fnsScreenShot_Date_format();
						fnsApps_Report_Logs("PASSED == *************  Successfully verified that iPulse 'Product Quality' issues count<"+iPulse_IssueCount+"> is matched with NsfOnline 'Product Quality' issue count<"+NSFOnline_ProductQuality_Graph_ExcelRow_Count+"> from download Excel by clicking on 'Product Quality' Graph section for Customer <"+IPulse_SearchScreen_Client_LK_Value_Case5+">.");
					}catch(Throwable t){
						fnsTake_Screen_Shot("PRQ_IssueCountNotMatch");
						throw new Exception(t.getMessage());	}	
				}
				
				
				
				//Case 3 continue
				
			}catch(Throwable t){
				Insight_WindowOpen_Flag = false;
				throw new Exception("......................"+Throwables.getStackTraceAsString(t)+"......................");	}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		}		
		
	}catch(SkipException sk){
			throw new SkipException(sk.getMessage());
			
	}catch(NoSuchWindowException W){
		fail = true;
		isTestCasePass = false;
		throw new Exception(W.getMessage());
		
	}catch(Throwable t){
		fail = true;
		isTestCasePass = false;
		fnsApps_Report_Logs("Case:"+CaseSerialNo.trim()+"......................"+" "+Throwables.getStackTraceAsString(t)+"......................");
		throw new Exception("Case:"+CaseSerialNo.trim()+"......................"+" "+Throwables.getStackTraceAsString(t)+"......................");}						
		
}


	
	
	
	
//################################################################  Class Function Start ################################################################################################
//Return Excel all data
	public Object[][] getExcelData_for_DataProvider(Xls_Reader xls , String testCaseName){
		
		if(! xls.isSheetExist(testCaseName)){
			xls=null;
			return new Object[1][0];
		}
		
		rows=xls.getRowCount(testCaseName);
		cols=xls.getColumnCount(testCaseName);
		System.out.println("Row = "+rows+"\t Col = "+cols);
		Object[][] data =new Object[rows][cols];
		for(int rowNum=0;rowNum<=rows-1;rowNum++){
			for(int colNum=0;colNum<cols;colNum++){
				data[rowNum][colNum] = xls.getCellData(testCaseName, colNum, rowNum+1);
				System.out.println(data[rowNum][colNum]);
			}
		}
		return data;
	}

//Fetch Excel column number by column name.
	public Integer fncReturn_Excel_ColumnNumber_by_Matching_ColumnName(Xls_Reader xls, String SheetName, String Matching_ColumnName){
		cols=xls.getRowCount(SheetName);
		for(int Col=0;Col<=cols;Col++){
			String CellValue =  xls.getCellData(SheetName, Col, 1).toLowerCase().trim();
			if ( CellValue.equalsIgnoreCase(Matching_ColumnName.toLowerCase().trim()) ){
				cols = Col;
				break;
			}
		}
		return cols;
	}
	
//To delete file
	public void fncDelete_File(String filePath){
		boolean FileDeleteFlag =  FileUtils.deleteQuietly(new File((filePath)));
		if(FileDeleteFlag == true){
			fnsApps_Report_Logs("PASSED == Successfully Deleted the file, having path[ "+filePath+" ]");
		}
		if(FileDeleteFlag == false){
			fnsApps_Report_Logs("PASSED == file, having path[ "+filePath+" ] is not exists for Deletion.");
		}
	
	}

//Create new excel file and execute autoit scripts
	public void fncCreateNewExcel_and_Run_Autoit_Scripts(String Excel_File_Path, String Autoit_Script_Path) throws Throwable{
		try{
			FileOutputStream fileOut = new FileOutputStream(Excel_File_Path);
			XSSFWorkbook workbook = new XSSFWorkbook();
	        workbook.createSheet("FirstSheet"); 
	        workbook.write(fileOut);
	        fileOut.close();
	        fnsApps_Report_Logs("PASSED == New excel file created, having path [ "+Excel_File_Path+" ]");
			//Process p = Runtime.getRuntime().exec(Autoit_Script_Path);
			//p.waitFor();
			//Thread.sleep(2000);
	        Runtime.getRuntime().exec(Autoit_Script_Path);
	        Thread.sleep(20000);
			fnsApps_Report_Logs("PASSED == ********* Successfulluy Executed Autoit script having exe file path [ "+Autoit_Script_Path+" ]");
		}catch(Throwable t){
			throw new Exception(Throwables.getStackTraceAsString(t)+"......................");	}
	}

//Save and Open file from IE browser dialog popup
	public void fncSave_and_Open_File_from_IE_Browser(String ButtonXpath, String YesNo_ToOpenFile) throws Throwable{
		try{
			
			String FileLocation = System.getProperty("user.home")+"\\Downloads";
			long AllFileSize_BeforeFileSave = FileUtils.sizeOfDirectory(new File(FileLocation));
			
			if(ButtonXpath.length()>1){
				fnsGet_Element_Enabled(ButtonXpath);
				TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element_and_Click(OR_NsfOnline.getProperty(ButtonXpath));
			}
						
			for(int waitloop=0; waitloop<7; waitloop++){
				try{	
					Actions action = new Actions(driver);
					action.keyDown(Keys.ALT);
					action.sendKeys("s");
					action.keyUp(Keys.ALT);
					action.build().perform();
					fnsApps_Report_Logs("PASSED == Successfully click on 'save' button of Browser dialog box.");
				    Thread.sleep(10000);
				}catch(Throwable t){
					fnsTake_Screen_Shot("BrowserFileSaveFail");
		    		throw new Exception(t.getMessage()+", Please refer screenshot >> BrowserFileSaveFail"+fnsScreenShot_Date_format());		}
			
			
				try{
					long AllFileSize_AfterFileSave = FileUtils.sizeOfDirectory(new File((FileLocation)));
					
					if(AllFileSize_BeforeFileSave<AllFileSize_AfterFileSave){
						Thread.sleep(1500);
						break;
					}
				}catch(IllegalArgumentException K){
					if(waitloop==6){
			    		fnsTake_Screen_Shot("FileDownloadFail");
			    		throw new Exception("File is not getting download, after <"+waitloop+">attempts, Please refer screenshot >> FileDownloadFail"+fnsScreenShot_Date_format());
			    	}else{
			    		Thread.sleep(5000);
			    	}
				}
			}
			fnsApps_Report_Logs("PASSED == Successfully 'Save' the file, after clicking on button >> "+ButtonXpath);
			
			if( (YesNo_ToOpenFile.toLowerCase().trim().equalsIgnoreCase("yes")) ){
				try{	
					Actions action = new Actions(driver);
					action.keyDown(Keys.ALT);
					action.sendKeys("o");
					action.keyUp(Keys.ALT);
					action.build().perform();
					fnsApps_Report_Logs("PASSED == Successfully click on 'save' button of Browser dialog box.");
				    Thread.sleep(2000);
				}catch(Throwable t){
					fnsTake_Screen_Shot("FileOpenFail");
		    		throw new Exception(t.getMessage()+", Please refer screenshot >> FileOpenFail"+fnsScreenShot_Date_format());		}
				fnsApps_Report_Logs("PASSED == Successfully 'Open' the file, after clicking on button >> "+ButtonXpath);
			}
		}catch(Throwable t){
			throw new Exception(Throwables.getStackTraceAsString(t)+"......................");
		}
	}

//Return NsfOnline issues total count	
	public Integer fncReturn_Issues_TotalCount_andClick_on_ExpandSearchCriteria() throws Throwable{
		Integer Issue_TotalCount = null;
		try{
			fncAjax_Link_Click_By_Passing_TwoAjaxXPathx("NSFOnline_Issues_TopMenu_Ajax_Link", "NSFOnline_Issues_Ajax_Link");
			TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(5);
			
			
			fnsGet_Element_Enabled("NSFOnline_Issues_IssueSearchResult_Pagination");
			WebElement PaginationDD=fnsGet_OR_NsfOnline_ObjectX("NSFOnline_Issues_IssueSearchResult_Pagination");
			TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element(OR_NsfOnline.getProperty("NSFOnline_Issues_IssueSearchResult_Pagination"));
			List<WebElement> PaginationDD_ElementList=PaginationDD.findElements(By.tagName("option"));
			for(WebElement PaginationDD_EleList:PaginationDD_ElementList){
				if((PaginationDD_EleList.getText()).contains("of")){
					String PaginationCount =(PaginationDD_EleList.getText().split("of")[1]).trim();
					Issue_TotalCount = Integer.parseInt(PaginationCount);
					break;
				}
			}
			
			Thread.sleep(1500);
			fnsGet_Element_Enabled("NSFOnline_ExpandSearchCriteriaLink");
			fnsWait_and_Click("NSFOnline_ExpandSearchCriteriaLink");
		}catch(Throwable t){
			throw new Exception(Throwables.getStackTraceAsString(t)+"......................");
		}
		return Issue_TotalCount;
	}
	
//Return NsfOnline issues count by selecting value from Issue Type DropDown	
	public Integer fncReturn_Issues_Count_By_Selecting_IssueTypeDDValue(String IssueTypeDDValue) throws Throwable{
		Integer Issue_Count = null;
		try{
			TestSuiteBase_MonitorPlan.WithOut_OR_fnDD_SelectValue_By_SelectClass(OR_NsfOnline.getProperty("NSFOnline_Issues_IssueSearchResult_IssueType_DD"), IssueTypeDDValue.trim());
			Thread.sleep(2500);
									
			fnsGet_Element_Enabled("NSFOnline_MySuppliers_CustomerSearchCriteria_SearchBttn");
			fnsWait_and_Click("NSFOnline_MySuppliers_CustomerSearchCriteria_SearchBttn");
			TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(4);
			
			
			fnsGet_Element_Enabled("NSFOnline_Issues_IssueSearchResult_Pagination");
			WebElement PaginationDD=fnsGet_OR_NsfOnline_ObjectX("NSFOnline_Issues_IssueSearchResult_Pagination");
			TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element(OR_NsfOnline.getProperty("NSFOnline_Issues_IssueSearchResult_Pagination"));
			List<WebElement> PaginationDD_ElementList=PaginationDD.findElements(By.tagName("option"));
			for(WebElement PaginationDD_EleList:PaginationDD_ElementList){
				if((PaginationDD_EleList.getText()).contains("of")){
					String PaginationCount =(PaginationDD_EleList.getText().split("of")[1]).trim();
					Issue_Count = Integer.parseInt(PaginationCount);
					break;
				}
			}
		}catch(Throwable t){
			throw new Exception(Throwables.getStackTraceAsString(t)+"......................");
		}
		return Issue_Count;
	}
		
//DB Function to Fetch total number of Result count
	public Integer fncQuery_to_Retun_Total_Number_of_Result_Row_Count( String Query) throws Exception {
		  int count = 0;
		  try {
			   Class.forName("oracle.jdbc.driver.OracleDriver");
		  }catch (ClassNotFoundException e) {
			  fnsApps_Report_Logs("*********************************************** Oracle JDBC Driver is not found ******************************************");
			  fnsApps_Report_Logs("=========================================================================================================================================");
			  throw new Exception (e.getMessage());
		 }
		  fnsApps_Report_Logs("PASSED == ********** Oracle JDBC Driver Registered "); 
		
		 try {
			 Connection connection = null;
			   connection = DriverManager.getConnection("jdbc:oracle:thin:@dbserv13vm:1521:certtest", "testscriptuser", "test4nsf");
			   
			   Statement stmt= connection.createStatement();
			  			   
			   stmt.executeUpdate(Query);
			   ResultSet rs = stmt.executeQuery(Query);
			 
			   while(rs.next()){
				  count++;
			   }
			   connection.commit();
			   fnsApps_Report_Logs("PASSED == **********  Query Executed Successfully."); 
			 
			   connection.close();
		
		  }catch (SQLException e) {
			  fnsApps_Report_Logs("*********************************************** Connection Failed! with Database,   Getting Error >>  "+e.getMessage().trim());
			  fnsApps_Report_Logs("=========================================================================================================================================");	 
			  e.printStackTrace();
			  throw new Exception (Throwables.getStackTraceAsString(e)+"......................");
		  }
		 return count;  
	}
	
	public void fncNsfOnline_Case3_and_Case7(String Autoit_scriptPath) throws Throwable{
		fncAjax_Link_Click_By_Passing_TwoAjaxXPathx("NSFOnline_Reports_Ajax_Link", "NSFOnline_Reports_ManagementReports_Ajax_Link");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(5);
		
		fnsGet_Element_Enabled("NSFOnline_Reports_ManagementReports_ProductIssuesShort_Link");
		fnsWait_and_Click("NSFOnline_Reports_ManagementReports_ProductIssuesShort_Link");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(3);
		
		
		fnsGet_Element_Enabled("NSFOnline_Reports_ManagementReports_ProductIssuesShort_GenerateReport_Bttn");
		
		fnsGet_Element_Enabled("NSFOnline_Reports_ManagementReports_ProductIssuesShort_DateOccurred_From");
		TestSuiteBase_MonitorPlan.WithOut_OR_fnClear(OR_NsfOnline.getProperty("NSFOnline_Reports_ManagementReports_ProductIssuesShort_DateOccurred_From"));
		fnsWait_and_Type("NSFOnline_Reports_ManagementReports_ProductIssuesShort_DateOccurred_From", NSFOnline_Reports_ManagementReports_ProductIssuesShort_DateOccurred_From);
		
		
		fnsGet_Element_Enabled("NSFOnline_Reports_ManagementReports_ProductIssuesShort_DateOccurred_To");
		TestSuiteBase_MonitorPlan.WithOut_OR_fnClear(OR_NsfOnline.getProperty("NSFOnline_Reports_ManagementReports_ProductIssuesShort_DateOccurred_To"));
		fnsWait_and_Type("NSFOnline_Reports_ManagementReports_ProductIssuesShort_DateOccurred_To", NSFOnline_Reports_ManagementReports_ProductIssuesShort_DateOccurred_To);
		
		
		fncDelete_File(Download_XlsFile_Location_case3);
		fncDelete_File(AutoIt_XlsFile_Location_Case3);
					
		fncSave_and_Open_File_from_IE_Browser("NSFOnline_Reports_ManagementReports_ProductIssuesShort_GenerateReport_Bttn", "Yes");
		fncCreateNewExcel_and_Run_Autoit_Scripts(AutoIt_XlsFile_Location_Case3, Autoit_scriptPath);
		
		try{
			AutoIt_NSFOnline_ProductIssuesShort_Xls = new Xls_Reader(AutoIt_XlsFile_Location_Case3);
			NSFOnline_ProductIssuesShort_ExcelRow_Count = AutoIt_NSFOnline_ProductIssuesShort_Xls.getRowCount("FirstSheet");
			NSFOnline_ProductIssuesShort_ExcelRow_Count = NSFOnline_ProductIssuesShort_ExcelRow_Count-1;
			fnsApps_Report_Logs("PASSED == Download Excel Row Count is <"+NSFOnline_ProductIssuesShort_ExcelRow_Count+">");
			if(NSFOnline_ProductIssuesShort_ExcelRow_Count<1){
				throw new Exception ("Downloaded Excel <"+Download_XlsFile_Location_case3+"> rows count is coming Zero, please refer screen shot >> ExcelRowCountZero"+fnsScreenShot_Date_format());
			}
		}catch(Throwable t){
			fnsTake_Screen_Shot("ExcelRowCountZero");
			Thread.sleep(5000);
			throw new Exception(Throwables.getStackTraceAsString(t)+"......................");
		}
	}
//################################################################  Class Function End ################################################################################################	
	
	
	
	
	
	
	
	
	
//################################################################  Config Functions ################################################################################################
	@AfterMethod
	public void reportDataSetResult() {
		if(count>-1){
			if (skip)
				TestUtil.reportDataSetResult(NSFOnline_suitexls, this.getClass().getSimpleName(), count + 2 , "SKIP");
			else if (fail) {
				isTestCasePass = false;
				TestUtil.reportDataSetResult(NSFOnline_suitexls, this.getClass().getSimpleName(), count + 2, "FAIL");
			} else
				TestUtil.reportDataSetResult(NSFOnline_suitexls, this.getClass().getSimpleName(), count + 2, "PASS");
	
			skip = false;
			fail = false;
		}
}

	
	
	@AfterTest
	public void reportTestResult() throws Throwable {
		
		TestSuiteBase_MonitorPlan.fns_ReportTestResult_atClassLevel(NSFOnline_suitexls,  (this.getClass().getSimpleName()) , isTestCasePass);
	}
	

	@AfterTest
	public void QuitBrowser(){
		try{
			MoveMouseCursorToTaskBar();
			driver.quit();
		}catch(Throwable t){
			//nothing to do
		}
	}
	
	
	
	
	@DataProvider
	public Object[][] getTestData() {
		return TestSuiteBase_MonitorPlan.getExcelData_for_DataProvider(NSFOnline_suitexls, this.getClass().getSimpleName());  
	}
	
}	