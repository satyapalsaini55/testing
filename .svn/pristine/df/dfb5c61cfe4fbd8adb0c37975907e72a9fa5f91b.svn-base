//TrunktestSiteName=http://oraapp10.nsf.org:8070/trunkecap/index.jsp
package nsf.ecap.Grip_Suite;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;


public class Resource_Master_Data_Setup extends TestSuiteBase_Grip {


//################################################ Class Variables Starts #####################################################################
	public Integer Count = -1;
	public Integer NewllyAdded_RowNo = null;
	public Integer Incremental_Count = null;
	public String Text_Fetched = null;
	public String NewllyAdded_Row_Xpath = null;
	public String NewllyAddedRow_Edit_Bttn_Xpath = null;
	public String NewllyAddedRow_Save_Bttn_Xpath = null;
	public String Department_Name = "AAutomationTest "+TestSuiteBase_MonitorPlan.fnsReturn_Requried_cal("dMHms");
	public String Comments_Free_Text = "AAutomationTest >> ResourceMasterDataSetup[BS-04], Date/Time=" + fnIssueCreationText_Date_format();
	
	public boolean Newlly_Added_Department_Found = false;
	public static boolean Temp_Wait = true;
	public static Integer ResourceManagerColumnSeq = 9;	



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
	fnsApps_Report_Logs("################################## [BS-04]  Resource Master Data Setup");
	if (!IsBrowserPresentAlready) {
		IsBrowserPresentAlready = fnsLaunchBrowserAndLogin();
		fnsApps_Report_Logs("Browser is launched and Successfully login into Application");	
	}
	fnsApps_Report_Logs("=========================================================================================================================================");
}







@Test( priority = 1, dependsOnMethods={"Launch_Browser_and_Successfully_Login_into_the_Application"})
public void Verify_Add_NewDepartment_Then_EditDepartment_by_AddDelete_ResourceManager_and_AddDelete_OrgUnit() throws Throwable{
	fnsApps_Report_Logs("################### Test Case ::::::1 Verify_Add_NewDepartment_Then_EditDepartment_by_AddDelete_ResourceManager_and_AddDelete_OrgUnit ");

	String Edit_ResourceMasterData_AddNewDepartment_Popup_AddNewResourceManager_LK_Text = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "AddNewResourceManager_LookUp");
	String Edit_ResourceMasterData_AddNewDepartment_Popup_AddNewOrgUnit_LK_Text = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "AddNewOrgUnit_LookUp");
	try{

	
		fncMenu_Ajax_Link_Click_By_PassingAjaxPath("ResourceMasterDataSetup_Ajax_Link");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(4);
		if(Temp_Wait){
			Thread.sleep(5000);
		}
		
		
		
		fnsGet_Element_Enabled("ResourceMasterData_Division_DD_Click");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		if(Temp_Wait){
			Thread.sleep(5000);
		}
		
		fnsGet_Element_Enabled("ResourceMasterData_Division_DD_Click");
		TestSuiteBase_MonitorPlan.WithOut_OR_fnDD_SelectValue_Through_Filter(OR_Grip.getProperty("ResourceMasterData_Division_DD_Click"), OR_Grip.getProperty("ResourceMasterData_Division_DD_Filter"), fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Division"));
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(3);
		if(Temp_Wait){
			Thread.sleep(5000);
		}
		
		fnsGet_Element_Enabled("ResourceMasterData_DepartmentTAB");	
		fnsGet_Element_Enabled("ResourceMasterData_Edit_Bttn");
		fnsWait_and_Click("ResourceMasterData_Edit_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(5);	
		if(Temp_Wait){
			Thread.sleep(5000);
		}
		
		fnsGet_Element_Enabled("Edit_ResourceMasterData_AddNewDepartment_Bttn");
		fnsWait_and_Click("Edit_ResourceMasterData_AddNewDepartment_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(4);
		if(Temp_Wait){
			Thread.sleep(5000);
		}
		
		fncAssert_Contains(OR_Grip.getProperty("Edit_ResourceMasterData_AddNewDepartment_Popup_Title"), "Add New Department", "'Add New Department' Popup is not getting opened");
		
		fnsGet_Element_Enabled("Edit_ResourceMasterData_AddNewDepartment_Popup_DepartmentName_Text");
		fnsWait_and_Type_WithoutClick("Edit_ResourceMasterData_AddNewDepartment_Popup_DepartmentName_Text", Department_Name);
		
		fnsGet_Element_Enabled("Edit_ResourceMasterData_AddNewDepartment_Popup_DepartmentDescription_Text");
		fnsWait_and_Type_WithoutClick("Edit_ResourceMasterData_AddNewDepartment_Popup_DepartmentDescription_Text", Comments_Free_Text);
		
		
		TestSuiteBase_MonitorPlan.WithOut_OR_fnDD_SelectValue_By_ContainsText(OR_Grip.getProperty("Edit_ResourceMasterData_AddNewDepartment_Popup_ParentDepartment_DD_Click"), OR_Grip.getProperty("Edit_ResourceMasterData_AddNewDepartment_Popup_ParentDepartment_DD_Value"), "li", "Info Security Program Office");
		
		
		fnc_MoveDownToEnd_on_Popup("//span[text()='Resource Manager Name']");
		fnsGet_Element_Enabled("Edit_ResourceMasterData_AddNewDepartment_Popup_SaveClose_Bttn");
		fnsWait_and_Click("Edit_ResourceMasterData_AddNewDepartment_Popup_SaveClose_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(5);
		if(Temp_Wait){
			Thread.sleep(5000);
		}
			
		fncVerify_DataUpdated_Successfully("IPulse_ValidationMessage_Div", "Add New Department");
		
		
		try{		
			Integer Page_Count = driver.findElements(By.xpath(OR_Grip.getProperty("Edit_ResourceMasterData_Pagination_Page_Count"))).size();
			Page_Count = Page_Count+1;
			for(int i=1; i<=Page_Count; i++){
				NewllyAdded_RowNo = 0;
				fnsGet_Element_Enabled("Edit_ResourceMasterData_TableHeader_Data");
				WebElement Department_TableData_Element = fnsGet_OR_Grip_ObjectX("Edit_ResourceMasterData_TableHeader_Data");
				List<WebElement> Department_TableData_Obj = Department_TableData_Element.findElements(By.tagName("tr"));
				for(WebElement  Department_TableData:Department_TableData_Obj){
					NewllyAdded_RowNo++;
					Count++;
					Text_Fetched = Department_TableData.getText().trim();
					if(Text_Fetched.contains(Department_Name)){
						
						Newlly_Added_Department_Found = true;
						break;
					}
				}
				if(Newlly_Added_Department_Found){
					break;
				}
				if(i!=Page_Count){
					fnsGet_Element_Enabled("Edit_ResourceMasterData_Pagination_Next_Bttn");
					fnsWait_and_Click("Edit_ResourceMasterData_Pagination_Next_Bttn");
					Thread.sleep(3000);
				}
			}
			
			if(!Newlly_Added_Department_Found){
				fnsTake_Screen_Shot("RecordNotFound");
				throw new Exception("FAILED == Newlly added Department is not displayed into the records, please refer screenshot >> RecordNotFound"+fnsScreenShot_Date_format());
			}
			
		}catch(NoSuchWindowException W){
			isTestCasePass = false;
			throw new SkipException(W.getMessage());
		}catch(Throwable t){
			isTestCasePass = false;
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));		}	
		
		
		NewllyAddedRow_Edit_Bttn_Xpath = OR_Grip.getProperty("Edit_ResourceMasterData_AddNewDepartment_Edit_Bttn1")+Count+OR_Grip.getProperty("Edit_ResourceMasterData_AddNewDepartment_Edit_Bttn2");
		NewllyAdded_Row_Xpath =  OR_Grip.getProperty("Edit_ResourceMasterData_TableHeader_Data")+"/tr["+NewllyAdded_RowNo+"]";
		
		TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(NewllyAddedRow_Edit_Bttn_Xpath);
		TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(NewllyAddedRow_Edit_Bttn_Xpath);
		
	
		fncAssert_Contains(OR_Grip.getProperty("Edit_ResourceMasterData_AddNewDepartment_Popup_Title"), "Edit Department", "'Edit Department' Popup is not getting opened");
		
		fnsGet_Element_Enabled("Edit_ResourceMasterData_AddNewDepartment_Popup_DepartmentDescription_Text");
		fnsWait_and_Clear("Edit_ResourceMasterData_AddNewDepartment_Popup_DepartmentDescription_Text");
		fnsWait_and_Type_WithoutClick("Edit_ResourceMasterData_AddNewDepartment_Popup_DepartmentDescription_Text", "Edit "+Comments_Free_Text);
		
		fnsDD_value_Select_By_MatchingText_DownKeyPress("Edit_ResourceMasterData_AddNewDepartment_Popup_ActiveStatus_DD_Click", "Edit_ResourceMasterData_AddNewDepartment_Popup_ActiveStatus_DD_Value", "li", "Yes");
		
		fnc_MoveDownToEnd_on_Popup("//span[text()='Resource Manager Name']");
		fnsGet_Element_Enabled("Edit_ResourceMasterData_AddNewDepartment_Popup_SaveClose_Bttn");
		fnsWait_and_Click("Edit_ResourceMasterData_AddNewDepartment_Popup_SaveClose_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(5);
		if(Temp_Wait){
			Thread.sleep(5000);
		}
			
		fncVerify_DataUpdated_Successfully("IPulse_ValidationMessage_Div", "Add New Department");
		
		
		TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(NewllyAddedRow_Edit_Bttn_Xpath);
		TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(NewllyAddedRow_Edit_Bttn_Xpath);
		
	
		fncAssert_Contains(OR_Grip.getProperty("Edit_ResourceMasterData_AddNewDepartment_Popup_Title"), "Edit Department", "'Edit Department' Popup is not getting opened");
		
		
		fnsGet_Element_Enabled("Edit_ResourceMasterData_AddNewDepartment_Popup_AddNewResourceManager_LK");
		fnsWait_and_Click("Edit_ResourceMasterData_AddNewDepartment_Popup_AddNewResourceManager_LK");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);	
		fnsGet_Element_Enabled("Edit_ResourceMasterData_AddNewDepartment_Popup_AddNewResourceManager_LK_Text");
		fnsWait_and_Clear("Edit_ResourceMasterData_AddNewDepartment_Popup_AddNewResourceManager_LK_Text");
		fnsWait_and_Type_WithoutClick("Edit_ResourceMasterData_AddNewDepartment_Popup_AddNewResourceManager_LK_Text", Edit_ResourceMasterData_AddNewDepartment_Popup_AddNewResourceManager_LK_Text);
		TestSuiteBase_MonitorPlan.fnsSelect_lookup_FirstCheckBox_and_Return();
		
		
		fnc_MoveDownToEnd_on_Popup("//span[text()='Resource Manager Name']");
		fnsGet_Element_Enabled("Edit_ResourceMasterData_AddNewDepartment_Popup_SaveClose_Bttn");
		fnsWait_and_Click("Edit_ResourceMasterData_AddNewDepartment_Popup_SaveClose_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(5);
		if(Temp_Wait){
			Thread.sleep(5000);
		}
			
		fncVerify_DataUpdated_Successfully("IPulse_ValidationMessage_Div", "Edit Department");
		
		/*Text_Fetched = TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(NewllyAdded_Row_Xpath).trim();/
		try{
			
			assert (Text_Fetched.contains(Edit_ResourceMasterData_AddNewDepartment_Popup_AddNewResourceManager_LK_Text)):"FAILED == 'Add New Resource Manager' is not updated into the records, please refer screen shot >>AddNewResourceManager_UpdateFail"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED == ************ Successfully updated 'Add New Resource Manager' into the records");		
		}catch (Throwable t){
			isTestCasePass = false;
			fnsTake_Screen_Shot("AddNewResourceManager_UpdateFail");
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));
		}*/
		
		String ResourceManagerColumn_ViewLink_Xpath = NewllyAdded_Row_Xpath+"/td["+ResourceManagerColumnSeq+"]/a";
		TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(ResourceManagerColumn_ViewLink_Xpath);
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(3);
		
		Text_Fetched = fnsGet_Field_TEXT("Edit_ResourceMasterData_AddNewDepartment_ResourceManager_ViewPopup_Text").trim();
		try{
			
			assert (Text_Fetched.toLowerCase().contains(Edit_ResourceMasterData_AddNewDepartment_Popup_AddNewResourceManager_LK_Text.toLowerCase())):"FAILED == 'Add New Resource Manager' is not updated into the records, please refer screen shot >>AddNewResourceManager_UpdateFail"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED == ************ Successfully updated 'Add New Resource Manager' into the records");		
		}catch (Throwable t){
			isTestCasePass = false;
			fnsTake_Screen_Shot("AddNewResourceManager_UpdateFail");
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		fnsWait_and_Click("Edit_ResourceMasterData_AddNewDepartment_ResourceManager_ViewPopup_CloseButton");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(3);
		
		TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(NewllyAddedRow_Edit_Bttn_Xpath);
		TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(NewllyAddedRow_Edit_Bttn_Xpath);
		
	
		fncAssert_Contains(OR_Grip.getProperty("Edit_ResourceMasterData_AddNewDepartment_Popup_Title"), "Edit Department", "'Edit Department' Popup is not getting opened");
		
		
		fnsGet_Element_Enabled("Edit_ResourceMasterData_AddNewDepartment_Popup_AddNewOrgUnit_LK");
		fnsWait_and_Click("Edit_ResourceMasterData_AddNewDepartment_Popup_AddNewOrgUnit_LK");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);	
		fnsGet_Element_Enabled("Edit_ResourceMasterData_AddNewDepartment_Popup_AddNewOrgUnit_LK_Text");
		fnsWait_and_Clear("Edit_ResourceMasterData_AddNewDepartment_Popup_AddNewOrgUnit_LK_Text");
		fnsWait_and_Type_WithoutClick("Edit_ResourceMasterData_AddNewDepartment_Popup_AddNewOrgUnit_LK_Text", Edit_ResourceMasterData_AddNewDepartment_Popup_AddNewOrgUnit_LK_Text);
		TestSuiteBase_MonitorPlan.fnsSelect_lookup_FirstCheckBox_and_Return();
		
		fnc_MoveDownToEnd_on_Popup("//span[text()='Resource Manager Name']");
		fnsGet_Element_Enabled("Edit_ResourceMasterData_AddNewDepartment_Popup_SaveClose_Bttn");
		fnsWait_and_Click("Edit_ResourceMasterData_AddNewDepartment_Popup_SaveClose_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(5);
		if(Temp_Wait){
			Thread.sleep(5000);
		}
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(5);	
		fncVerify_DataUpdated_Successfully("IPulse_ValidationMessage_Div", "Edit Department");
		
		
		Text_Fetched = TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(NewllyAdded_Row_Xpath).trim();
		try{
			
			assert (Text_Fetched.contains(Edit_ResourceMasterData_AddNewDepartment_Popup_AddNewOrgUnit_LK_Text)):"FAILED == 'Add New Org Unit' is not updated into the records, please refer screen shot >>AddNewOrgUnit_UpdateFail"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED ==  ************ Successfully updated 'Add New Resource Manager' into the records");	
		}catch (Throwable t){
			isTestCasePass = false;
			fnsTake_Screen_Shot("AddNewOrgUnit_UpdateFail");
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		
		
		
		
		
		
		
		
		TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(NewllyAddedRow_Edit_Bttn_Xpath);
		TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(NewllyAddedRow_Edit_Bttn_Xpath);
		
	
		fncAssert_Contains(OR_Grip.getProperty("Edit_ResourceMasterData_AddNewDepartment_Popup_Title"), "Edit Department", "'Edit Department' Popup is not getting opened");
		
		fnsGet_Element_Enabled("Edit_ResourceMasterData_AddNewDepartment_Popup_AddNewResourceManager_Delete_Bttn");
		fnsWait_and_Click("Edit_ResourceMasterData_AddNewDepartment_Popup_AddNewResourceManager_Delete_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(4);	
		if(Temp_Wait){
			Thread.sleep(5000);
		}
		
	
		fnsGet_Element_Enabled("Edit_ResourceMasterData_AddNewDepartment_Popup_AddNewResourceManager_Delete_YES_Bttn");
		fnsWait_and_Click("Edit_ResourceMasterData_AddNewDepartment_Popup_AddNewResourceManager_Delete_YES_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(5);
		if(Temp_Wait){
			Thread.sleep(5000);
		}
		
		fncVerify_DataDeleted_Successfully("Edit_ResourceMasterData_AddNewDepartment_Popup_Validation_Msg", "Edit Department: 'Add New Resource Manager' Section");
		
		
		fnc_MoveDownToEnd_on_Popup("//span[text()='Resource Manager Name']");
		fnsGet_Element_Enabled("Edit_ResourceMasterData_AddNewDepartment_Popup_SaveClose_Bttn");
		fnsWait_and_Click("Edit_ResourceMasterData_AddNewDepartment_Popup_SaveClose_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(5);
		if(Temp_Wait){
			Thread.sleep(5000);
		}
			
		fncVerify_DataUpdated_Successfully("IPulse_ValidationMessage_Div", "Edit Department");
		
		Text_Fetched = TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(NewllyAdded_Row_Xpath).trim();
		try{
			
			assert !(Text_Fetched.contains(Edit_ResourceMasterData_AddNewDepartment_Popup_AddNewResourceManager_LK_Text)):"FAILED == 'Add New Resource Manager' is not deleted into the records, please refer screen shot >>AddNewResourceManager_deleteFail"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED == ************ Successfully deleted 'Add New Resource Manager' from records");	
		}catch (Throwable t){
			isTestCasePass = false;
			fnsTake_Screen_Shot("AddNewResourceManager_deleteFail");
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	
		
		
		TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(NewllyAddedRow_Edit_Bttn_Xpath);
		TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(NewllyAddedRow_Edit_Bttn_Xpath);
		
	
		fncAssert_Contains(OR_Grip.getProperty("Edit_ResourceMasterData_AddNewDepartment_Popup_Title"), "Edit Department", "'Edit Department' Popup is not getting opened");
		
		fnsGet_Element_Enabled("Edit_ResourceMasterData_AddNewDepartment_Popup_AddNewOrgUnit_Delete_Bttn");
		fnsWait_and_Click("Edit_ResourceMasterData_AddNewDepartment_Popup_AddNewOrgUnit_Delete_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(4);	
		if(Temp_Wait){
			Thread.sleep(5000);
		}
		
	
		fnsGet_Element_Enabled("Edit_ResourceMasterData_AddNewDepartment_Popup_AddNewOrgUnit_Delete_YES_Bttn");
		fnsWait_and_Click("Edit_ResourceMasterData_AddNewDepartment_Popup_AddNewOrgUnit_Delete_YES_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(5);
		if(Temp_Wait){
			Thread.sleep(5000);
		}
		
		fncVerify_DataDeleted_Successfully("Edit_ResourceMasterData_AddNewDepartment_Popup_Validation_Msg", "Edit Department: 'Add New OrgUnit' Section");
		
		fnc_MoveDownToEnd_on_Popup("//span[text()='Resource Manager Name']");
		fnsGet_Element_Enabled("Edit_ResourceMasterData_AddNewDepartment_Popup_SaveClose_Bttn");
		fnsWait_and_Click("Edit_ResourceMasterData_AddNewDepartment_Popup_SaveClose_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(5);
		if(Temp_Wait){
			Thread.sleep(5000);
		}
			
		fncVerify_DataUpdated_Successfully("IPulse_ValidationMessage_Div", "Edit Department");
		if(Temp_Wait){
			Thread.sleep(5000);
		}
		
	
		Text_Fetched = TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(NewllyAdded_Row_Xpath).trim();
		try{
			
			assert !(Text_Fetched.contains(Edit_ResourceMasterData_AddNewDepartment_Popup_AddNewOrgUnit_LK_Text)):"FAILED == 'Add New Org Unit' is not deleted into the records, please refer screen shot >>AddNewOrgUnit_DeleteFail"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED ==  ************ Successfully deleted 'Add New Org Unit' from records");
		}catch (Throwable t){
			isTestCasePass = false;
			fnsTake_Screen_Shot("AddNewOrgUnit_DeleteFail");
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}catch(Throwable t){
		isTestCasePass = false;
		throw new Exception(Throwables.getStackTraceAsString(t));	
	}		
}





@Test( priority = 2, dependsOnMethods={"Verify_Add_NewDepartment_Then_EditDepartment_by_AddDelete_ResourceManager_and_AddDelete_OrgUnit"} )
public void Verify_Add_NewCertificate_Then_EditCertificate_and_AllWorkingFine() throws Throwable{
	fnsApps_Report_Logs("=========================================================================================================================================");
	fnsApps_Report_Logs("################### Test Case ::::::2 Verify_Add_NewCertificate_Then_EditCertificate_and_AllWorkingFine");
	try{
		String Edit_ResourceMasterData_Certificate_AddNewCertificateType_CertificateCode_Text = TestSuiteBase_MonitorPlan.fnsReturn_Requried_cal("dMHms");
		String Edit_ResourceMasterData_Certificate_AddNewCertificateType_CertificateName_Text =  fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "CertificateName");
		
		NewllyAdded_Row_Xpath =  OR_Grip.getProperty("Edit_ResourceMasterData_Certificate_TableHeader_Data")+"/tr[1]";
		
		fnsGet_Element_Enabled("Edit_ResourceMasterData_CertificateTAB");
		fnsWait_and_Click("Edit_ResourceMasterData_CertificateTAB");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(4);
		if(Temp_Wait){
			Thread.sleep(5000);
		}
		
		fnsGet_Element_Enabled("Edit_ResourceMasterData_Certificate_AddNewCertificateType_Bttn");
		fnsWait_and_Click("Edit_ResourceMasterData_Certificate_AddNewCertificateType_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(4);
		if(Temp_Wait){
			Thread.sleep(5000);
		}
		
		fnsGet_Element_Enabled("Edit_ResourceMasterData_Certificate_AddNewCertificateType_CertificateCode_Text");
		fnsWait_and_Type_WithoutClick("Edit_ResourceMasterData_Certificate_AddNewCertificateType_CertificateCode_Text", Edit_ResourceMasterData_Certificate_AddNewCertificateType_CertificateCode_Text);
		
		fnsGet_Element_Enabled("Edit_ResourceMasterData_Certificate_AddNewCertificateType_CertificateName_Text");
		fnsWait_and_Type_WithoutClick("Edit_ResourceMasterData_Certificate_AddNewCertificateType_CertificateName_Text", Edit_ResourceMasterData_Certificate_AddNewCertificateType_CertificateName_Text);
		
		fnsGet_Element_Enabled("Edit_ResourceMasterData_Certificate_AddNewCertificateType_Active_DD_Click");
		fnsDD_value_Select_By_MatchingText_DownKeyPress("Edit_ResourceMasterData_Certificate_AddNewCertificateType_Active_DD_Click", "Edit_ResourceMasterData_Certificate_AddNewCertificateType_Active_DD_Value", "li", "Yes");
		
		fnsGet_Element_Enabled("Edit_ResourceMasterData_Certificate_AddNewCertificateType_Save_Bttn");
		fnsWait_and_Click("Edit_ResourceMasterData_Certificate_AddNewCertificateType_Save_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(5);
		if(Temp_Wait){
			Thread.sleep(5000);
		}
		
		fncVerify_DataUpdated_Successfully("IPulse_ValidationMessage_Div", "Add New Certificate Type");
		
		Text_Fetched = TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(NewllyAdded_Row_Xpath).trim();
		try{
			
			assert ( Text_Fetched.contains(Edit_ResourceMasterData_Certificate_AddNewCertificateType_CertificateCode_Text) && Text_Fetched.contains(Edit_ResourceMasterData_Certificate_AddNewCertificateType_CertificateName_Text)):
				"FAILED == 'Add New Certificate Type' is not Added into the records, please refer screen shot >>AddNewCertificateType_AddFail"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED ==  ************ Successfully added 'Add New Certificate Type' into the records");
		}catch (Throwable t){
			isTestCasePass = false;
			fnsTake_Screen_Shot("AddNewCertificateType_AddFail");
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		
		
		Edit_ResourceMasterData_Certificate_AddNewCertificateType_CertificateName_Text = TestSuiteBase_MonitorPlan.fnsReturn_Requried_cal("dMHms");
		
		
		fnsGet_Element_Enabled("Edit_ResourceMasterData_Certificate_AddNewCertificateType_Edit_Bttn");
		fnsWait_and_Click("Edit_ResourceMasterData_Certificate_AddNewCertificateType_Edit_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(4);
		if(Temp_Wait){
			Thread.sleep(5000);
		}
		
		
		fnsGet_Element_Enabled("Edit_ResourceMasterData_Certificate_AddNewCertificateType_CertificateName_Text");
		//fnsWait_and_Clear("Edit_ResourceMasterData_Certificate_AddNewCertificateType_CertificateName_Text");
		fnsWait_and_Type_WithoutClick("Edit_ResourceMasterData_Certificate_AddNewCertificateType_CertificateName_Text", Edit_ResourceMasterData_Certificate_AddNewCertificateType_CertificateName_Text);
		
		fnsGet_Element_Enabled("Edit_ResourceMasterData_Certificate_AddNewCertificateType_Description_Text");
		//fnsWait_and_Clear("Edit_ResourceMasterData_Certificate_AddNewCertificateType_Description_Text");
		fnsWait_and_Type_WithoutClick("Edit_ResourceMasterData_Certificate_AddNewCertificateType_Description_Text", Comments_Free_Text);
		
		
		fnsGet_Element_Enabled("Edit_ResourceMasterData_Certificate_AddNewCertificateType_Save_Bttn");
		fnsWait_and_Click("Edit_ResourceMasterData_Certificate_AddNewCertificateType_Save_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(5);
		if(Temp_Wait){
			Thread.sleep(5000);
		}
		
		fncVerify_DataUpdated_Successfully("IPulse_ValidationMessage_Div", "Edit Certificate");
		Edit_ResourceMasterData_Certificate_AddNewCertificateType_CertificateName_Text = "Automation Test Certificate"+Edit_ResourceMasterData_Certificate_AddNewCertificateType_CertificateName_Text;
		
		Text_Fetched = TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(NewllyAdded_Row_Xpath).trim();
		try{
			
			assert ( Text_Fetched.contains(Comments_Free_Text) && Text_Fetched.contains(Edit_ResourceMasterData_Certificate_AddNewCertificateType_CertificateName_Text) ):
				"FAILED == 'Edit Certificate' is not updated into the records, please refer screen shot >>EditCertificate_UpdateFail"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED ==  ************ Successfully updated 'Edit Certificate' into the records");
		}catch (Throwable t){
			isTestCasePass = false;
			fnsTake_Screen_Shot("EditCertificate_UpdateFail");
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}catch(Throwable t){
		isTestCasePass = false;
		throw new Exception(Throwables.getStackTraceAsString(t));	
	}	
}



@Test( priority = 3, dependsOnMethods={"Verify_Add_NewCertificate_Then_EditCertificate_and_AllWorkingFine"} )
public void Verify_Add_NewTraining_Then_EditTraining_and_AllWorkingFine() throws Throwable{
	fnsApps_Report_Logs("=========================================================================================================================================");
	fnsApps_Report_Logs("################### Test Case ::::::3 Verify_Add_NewTraining_Then_EditTraining_and_AllWorkingFine");
	try{
		String Edit_ResourceMasterData_Training_AddNewTrainingMaster_Popup_TrainingCode = "AAT"+TestSuiteBase_MonitorPlan.fnsReturn_Requried_cal("dMHms");
		String Edit_ResourceMasterData_Training_AddNewTrainingMaster_Popup_TrainingName = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "TrainingName");
		
		fnsGet_Element_Enabled("Edit_ResourceMasterData_TrainingTAB");
		fnsWait_and_Click("Edit_ResourceMasterData_TrainingTAB");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(4);
		if(Temp_Wait){
			Thread.sleep(5000);
		}
		
		fnsGet_Element_Enabled("Edit_ResourceMasterData_Training_AddNewTraining_Bttn");
		fnsWait_and_Click("Edit_ResourceMasterData_Training_AddNewTraining_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(4);
		if(Temp_Wait){
			Thread.sleep(5000);
		}
		
		fncAssert_Contains(OR_Grip.getProperty("Edit_ResourceMasterData_Training_AddNewTrainingMaster_Popup_Title"), "Add a new Training Master", "'Add a new Training Master' Popup is not getting opened");
		
		fnsGet_Element_Enabled("Edit_ResourceMasterData_Training_AddNewTrainingMaster_Popup_TrainingCode");
		fnsWait_and_Type_WithoutClick("Edit_ResourceMasterData_Training_AddNewTrainingMaster_Popup_TrainingCode", Edit_ResourceMasterData_Training_AddNewTrainingMaster_Popup_TrainingCode);
		
		fnsGet_Element_Enabled("Edit_ResourceMasterData_Training_AddNewTrainingMaster_Popup_TrainingName");
		fnsWait_and_Type_WithoutClick("Edit_ResourceMasterData_Training_AddNewTrainingMaster_Popup_TrainingName", Edit_ResourceMasterData_Training_AddNewTrainingMaster_Popup_TrainingName);
		
			
		fnsGet_Element_Enabled("Edit_ResourceMasterData_Training_AddNewTrainingMaster_Popup_TrainingCategory_DD_Click");
		fnsDD_value_Select_By_MatchingText_DownKeyPress("Edit_ResourceMasterData_Training_AddNewTrainingMaster_Popup_TrainingCategory_DD_Click", "Edit_ResourceMasterData_Training_AddNewTrainingMaster_Popup_TrainingCategory_DD_Value", "li",  fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "TrainingCategory_DD"));
		
		fnsGet_Element_Enabled("Edit_ResourceMasterData_Training_AddNewTrainingMaster_Popup_TrainingType_DD_Click");
		TestSuiteBase_MonitorPlan.WithOut_OR_fnDD_SelectValue_Through_Filter(OR_Grip.getProperty("Edit_ResourceMasterData_Training_AddNewTrainingMaster_Popup_TrainingType_DD_Click"), OR_Grip.getProperty("Edit_ResourceMasterData_Training_AddNewTrainingMaster_Popup_TrainingType_DD_Filter"),  fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "TrainingType_DD"));
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		if(Temp_Wait){
			Thread.sleep(5000);
		}
			
		fnsGet_Element_Enabled("Edit_ResourceMasterData_Training_AddNewTrainingMaster_Popup_Save_Bttn");
		fnsWait_and_Click("Edit_ResourceMasterData_Training_AddNewTrainingMaster_Popup_Save_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(5);
		if(Temp_Wait){
			Thread.sleep(5000);
		}
		
		fncVerify_DataUpdated_Successfully("IPulse_ValidationMessage_Div", "Add a new Training Master");
		
		
		try{
			Incremental_Count = -1;
			for(int PageCount = 1; PageCount <= 20; PageCount++){
				Count = 0;
				Newlly_Added_Department_Found = false;
				fnsGet_Element_Enabled("Edit_ResourceMasterData_Training_TableHeader_Data");
				for(int i=1; i<21; i++){
					Incremental_Count++;
					String Row_Xpath = OR_Grip.getProperty("Edit_ResourceMasterData_Training_TableHeader_Data")+"/tr["+i+"]";
					Count = i;
					if(driver.findElements(By.xpath(Row_Xpath)).size()>0){
						TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element(Row_Xpath);
						Text_Fetched = TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(Row_Xpath).trim();
						if(Text_Fetched.contains(Edit_ResourceMasterData_Training_AddNewTrainingMaster_Popup_TrainingCode)){
							NewllyAdded_RowNo = i;
							Newlly_Added_Department_Found = true;
							break;
						}	
					}else{
						break;
					}
					
					
				}
			//	System.out.println("Incremental_Count = "+Incremental_Count);
				if(Newlly_Added_Department_Found){
					break;
				}else if(Count==20){
						fnsGet_Element_Enabled("Edit_ResourceMasterData_Training_Pagination_Next_Bttn");
						fnsWait_and_Click("Edit_ResourceMasterData_Training_Pagination_Next_Bttn");
						Thread.sleep(4000);
				} else{
						break;
					}
			}
				
			if(!Newlly_Added_Department_Found){
				fnsTake_Screen_Shot("RecordNotFound");
				throw new Exception("FAILED == Newlly added Training is not displayed into the records, please refer screenshot >> RecordNotFound"+fnsScreenShot_Date_format());
			}
			fnsApps_Report_Logs("PASSED ==  ************ Successfully added 'Add a new Training Master' into the records");	
		}catch(NoSuchWindowException W){
			isTestCasePass = false;
			throw new SkipException(W.getMessage());
		}catch(Throwable t){
			isTestCasePass = false;
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));		}	
		
		
			
		NewllyAddedRow_Edit_Bttn_Xpath =  OR_Grip.getProperty("Edit_ResourceMasterData_Training_TableHeader_Data")+"/tr["+NewllyAdded_RowNo+"]/td/div/a[1]/span";
		NewllyAddedRow_Save_Bttn_Xpath =  OR_Grip.getProperty("Edit_ResourceMasterData_Training_TableHeader_Data")+"/tr["+NewllyAdded_RowNo+"]/td/div/a[2]/span";
		NewllyAdded_Row_Xpath =  OR_Grip.getProperty("Edit_ResourceMasterData_Training_TableHeader_Data")+"/tr["+NewllyAdded_RowNo+"]";
		
		TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(NewllyAddedRow_Edit_Bttn_Xpath);
		TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(NewllyAddedRow_Edit_Bttn_Xpath);
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		if(Temp_Wait){
			Thread.sleep(5000);
		}
		
		String Description_Field_Xpath = "//*[@id='mainForm:resourceTabView:rsmdtrdt:"+Incremental_Count+":trdescription']";
		TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(Description_Field_Xpath);
		TestSuiteBase_MonitorPlan.WithOut_OR_fnType(Description_Field_Xpath, Comments_Free_Text);
		
		TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(NewllyAddedRow_Save_Bttn_Xpath);
		TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(NewllyAddedRow_Save_Bttn_Xpath);
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(5);
		if(Temp_Wait){
			Thread.sleep(5000);
		}
	
		fncVerify_DataUpdated_Successfully("IPulse_ValidationMessage_Div", "Add a new Training Master");
		
		Text_Fetched = TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(NewllyAdded_Row_Xpath).trim();
		try{
			assert ( Text_Fetched.contains(Comments_Free_Text) ):
				"FAILED == 'Edit Training' is not updated into the records, please refer screen shot >>EditTraining_UpdateFail"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED ==  ************ Successfully updated 'Edit Training' into the records");
		}catch (Throwable t){
			isTestCasePass = false;
			fnsTake_Screen_Shot("EditTraining_UpdateFail");
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}catch(Throwable t){
		isTestCasePass = false;
		throw new Exception(Throwables.getStackTraceAsString(t));	
	}
}





@Test( priority = 4, dependsOnMethods={"Verify_Add_NewTraining_Then_EditTraining_and_AllWorkingFine"} )
public void Verify_Add_NewPaymentSchedule_EditPaymentSchedule_and_DeleteIt_AllWorkingFine() throws Throwable{
	fnsApps_Report_Logs("=========================================================================================================================================");
	fnsApps_Report_Logs("################### Test Case ::::::4 Verify_Add_NewPaymentSchedule_EditPaymentSchedule_and_DeleteIt_AllWorkingFine");
	try{
		String Edit_ResourceMasterData_PaymentSchedule_AddPaymentSchedule_Popup_ScheduleName = "SN"+TestSuiteBase_MonitorPlan.fnsReturn_Requried_cal("dMHms");
	
		fnsGet_Element_Enabled("Edit_ResourceMasterData_PaymentScheduleTAB");
		fnsWait_and_Click("Edit_ResourceMasterData_PaymentScheduleTAB");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(4);
		if(Temp_Wait){
			Thread.sleep(5000);
		}
		
		fnsGet_Element_Enabled("Edit_ResourceMasterData_PaymentSchedule_AddNewPaymentSchedule_Bttn");
		fnsWait_and_Click("Edit_ResourceMasterData_PaymentSchedule_AddNewPaymentSchedule_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(4);
		if(Temp_Wait){
			Thread.sleep(5000);
		}
		
		fncAssert_Contains(OR_Grip.getProperty("Edit_ResourceMasterData_PaymentSchedule_AddPaymentSchedule_Popup_Title"), "Add Payment Schedule", "'Add Payment Schedule' Popup is not getting opened");
		
		fnsGet_Element_Enabled("Edit_ResourceMasterData_PaymentSchedule_AddPaymentSchedule_Popup_ScheduleName");
		fnsWait_and_Type_WithoutClick("Edit_ResourceMasterData_PaymentSchedule_AddPaymentSchedule_Popup_ScheduleName", Edit_ResourceMasterData_PaymentSchedule_AddPaymentSchedule_Popup_ScheduleName);
		
		fnsGet_Element_Enabled("Edit_ResourceMasterData_PaymentSchedule_AddPaymentSchedule_Popup_Unit_DD_Click");
		fnsDD_value_Select_By_MatchingText_DownKeyPress("Edit_ResourceMasterData_PaymentSchedule_AddPaymentSchedule_Popup_Unit_DD_Click", "Edit_ResourceMasterData_PaymentSchedule_AddPaymentSchedule_Popup_Unit_DD_Value", "li",   fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Popup_Unit_DD"));
		
		fnsGet_Element_Enabled("Edit_ResourceMasterData_PaymentSchedule_AddPaymentSchedule_Popup_Currency_DD_Click");
		TestSuiteBase_MonitorPlan.WithOut_OR_fnDD_SelectValue_Through_Filter(OR_Grip.getProperty("Edit_ResourceMasterData_PaymentSchedule_AddPaymentSchedule_Popup_Currency_DD_Click"), OR_Grip.getProperty("Edit_ResourceMasterData_PaymentSchedule_AddPaymentSchedule_Popup_Currency_DD_Filter"),   fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Popup_Currency_DD"));
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		if(Temp_Wait){
			Thread.sleep(5000);
		}
			
		fnsGet_Element_Enabled("Edit_ResourceMasterData_PaymentSchedule_AddPaymentSchedule_Popup_ScheduleComments");
		fnsWait_and_Type_WithoutClick("Edit_ResourceMasterData_PaymentSchedule_AddPaymentSchedule_Popup_ScheduleComments", Comments_Free_Text);
		
		fnsGet_Element_Enabled("Edit_ResourceMasterData_PaymentSchedule_AddPaymentSchedule_Popup_Save_Bttn");
		fnsWait_and_Click("Edit_ResourceMasterData_PaymentSchedule_AddPaymentSchedule_Popup_Save_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(5);
		if(Temp_Wait){
			Thread.sleep(5000);
		}
		
		fncVerify_DataUpdated_Successfully("IPulse_ValidationMessage_Div", "Add Payment Schedule");
		
		
		try{
			NewllyAdded_RowNo = TestSuiteBase_MonitorPlan.WithOut_OR_fnsReturn_RowNumber_By_TableMatching_TEXT(OR_Grip.getProperty("Edit_ResourceMasterData_PaymentSchedule_TableHeader"), OR_Grip.getProperty("Edit_ResourceMasterData_PaymentSchedule_TableHeader_Data"), Edit_ResourceMasterData_PaymentSchedule_AddPaymentSchedule_Popup_ScheduleName);
			fnsApps_Report_Logs("PASSED ==  ************ Successfully added 'Add a new Training Master' into the records");
		}catch (Throwable t){
			isTestCasePass = false;
			fnsTake_Screen_Shot("AddPaymentSchedule_AddFail");
			fnsApps_Report_Logs("FAILED == 'Add a new Training Master' is not Added into the records, please refer screen shot >>AddPaymentSchedule_AddFail"+fnsScreenShot_Date_format()+" Getting Exception >> "+Throwables.getStackTraceAsString(t));
			throw new Exception("FAILED == 'Add a new Training Master' is not Added into the records, please refer screen shot >>AddPaymentSchedule_AddFail"+fnsScreenShot_Date_format()+" Getting Exception >> "+Throwables.getStackTraceAsString(t));
		}
		
		
		NewllyAddedRow_Edit_Bttn_Xpath =  OR_Grip.getProperty("Edit_ResourceMasterData_PaymentSchedule_TableHeader_Data")+"/tr["+NewllyAdded_RowNo+"]/td/button";
		NewllyAdded_Row_Xpath =  OR_Grip.getProperty("Edit_ResourceMasterData_PaymentSchedule_TableHeader_Data")+"/tr["+NewllyAdded_RowNo+"]";
		
		TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(NewllyAddedRow_Edit_Bttn_Xpath);
		TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(NewllyAddedRow_Edit_Bttn_Xpath);
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(4);
		if(Temp_Wait){
			Thread.sleep(5000);
		}
		
		fncAssert_Contains(OR_Grip.getProperty("Edit_ResourceMasterData_PaymentSchedule_AddPaymentSchedule_Popup_Title"), "Edit Payment Schedule", "'Edit Payment Schedule' Popup is not getting opened");
		
		fnsGet_Element_Enabled("Edit_ResourceMasterData_PaymentSchedule_AddPaymentSchedule_Popup_Add_Bttn");
		fnsWait_and_Click("Edit_ResourceMasterData_PaymentSchedule_AddPaymentSchedule_Popup_Add_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(4);
		if(Temp_Wait){
			Thread.sleep(5000);
		}
		
		
		fnsGet_Element_Enabled("Edit_ResourceMasterData_PaymentSchedule_AddPaymentSchedule_Popup_Add_NoOfHours");
		fnsWait_and_Type_WithoutClick("Edit_ResourceMasterData_PaymentSchedule_AddPaymentSchedule_Popup_Add_NoOfHours",  fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Popup_Add_NoOfHours"));
		
		fnsGet_Element_Enabled("Edit_ResourceMasterData_PaymentSchedule_AddPaymentSchedule_Popup_Add_Country_DD_Click");
		TestSuiteBase_MonitorPlan.WithOut_OR_fnDD_SelectValue_Through_Filter(OR_Grip.getProperty("Edit_ResourceMasterData_PaymentSchedule_AddPaymentSchedule_Popup_Add_Country_DD_Click"), OR_Grip.getProperty("Edit_ResourceMasterData_PaymentSchedule_AddPaymentSchedule_Popup_Add_Country_DD_Filter"),  fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Popup_Add_Country_DD"));
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
		if(Temp_Wait){
			Thread.sleep(5000);
		}
		
		fnsGet_Element_Enabled("Edit_ResourceMasterData_PaymentSchedule_AddPaymentSchedule_Popup_Add_Role_DD_Click");
		fnsDD_value_Select_By_MatchingText_DownKeyPress("Edit_ResourceMasterData_PaymentSchedule_AddPaymentSchedule_Popup_Add_Role_DD_Click", "Edit_ResourceMasterData_PaymentSchedule_AddPaymentSchedule_Popup_Add_Role_DD_Value", "li",  fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Popup_Add_Role_DD"));
		
		
		fnsGet_Element_Enabled("Edit_ResourceMasterData_PaymentSchedule_AddPaymentSchedule_Popup_Add_StandardRate");
		fnsWait_and_Type_WithoutClick("Edit_ResourceMasterData_PaymentSchedule_AddPaymentSchedule_Popup_Add_StandardRate", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Popup_Add_StandardRate"));
		
		
		fnsGet_Element_Enabled("Edit_ResourceMasterData_PaymentSchedule_AddPaymentSchedule_Popup_Add_MinRate");
		fnsWait_and_Type_WithoutClick("Edit_ResourceMasterData_PaymentSchedule_AddPaymentSchedule_Popup_Add_MinRate",  fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Popup_Add_MinRate"));
		
		
		fnsGet_Element_Enabled("Edit_ResourceMasterData_PaymentSchedule_AddPaymentSchedule_Popup_Add_MaxRate");
		fnsWait_and_Type_WithoutClick("Edit_ResourceMasterData_PaymentSchedule_AddPaymentSchedule_Popup_Add_MaxRate", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Popup_Add_MaxRate"));
		
		
		fnsGet_Element_Enabled("Edit_ResourceMasterData_PaymentSchedule_AddPaymentSchedule_Popup_Add_BillingAmount");
		fnsWait_and_Type_WithoutClick("Edit_ResourceMasterData_PaymentSchedule_AddPaymentSchedule_Popup_Add_BillingAmount", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Popup_Add_BillingAmount"));
		
	
		fnsGet_Element_Enabled("Edit_ResourceMasterData_PaymentSchedule_AddPaymentSchedule_Popup_Save_Bttn");
		fnsWait_and_Click("Edit_ResourceMasterData_PaymentSchedule_AddPaymentSchedule_Popup_Save_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(5);
		if(Temp_Wait){
			Thread.sleep(5000);
		}
		
		
		fncVerify_DataUpdated_Successfully("IPulse_ValidationMessage_Div", "Edit Payment Schedule");
		
		
		String NewllyAdded_Row_Delete_Bttn_Xpath = "//a[@id='mainForm:resourceTabView:rspymntschdt:"+(NewllyAdded_RowNo-1)+":delpymntschbtn']";
		
		TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(NewllyAdded_Row_Delete_Bttn_Xpath);
		TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(NewllyAdded_Row_Delete_Bttn_Xpath);
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(4);
		if(Temp_Wait){
			Thread.sleep(5000);
		}
		
		fnsGet_Element_Enabled("Edit_ResourceMasterData_PaymentSchedule_AddPaymentSchedule_Delete_YES_Bttn");
		fnsWait_and_Click("Edit_ResourceMasterData_PaymentSchedule_AddPaymentSchedule_Delete_YES_Bttn");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(4);
		if(Temp_Wait){
			Thread.sleep(5000);
		}
		
		fncVerify_DataDeleted_Successfully("IPulse_ValidationMessage_Div", "Payment Schedule: 'Add New Payment Schedule' Section");
	}catch(Throwable t){
		isTestCasePass = false;
		throw new Exception(Throwables.getStackTraceAsString(t));	
	}
}







public void fnc_MoveDownToEnd_on_Popup(String XpathKey) throws Throwable{
	try{
		Actions act = new Actions(driver);
		WebElement ele = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(XpathKey);
		act.moveToElement(ele).click().build().perform();
		act.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).build().perform();
		
	}catch(Throwable t){
		throw new Exception ("PopupMoveDown_Fail : "+Throwables.getStackTraceAsString(t));
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

