package nsf.ecap.New_NSFOnline_Suite;


import java.util.List;

import javax.naming.NotContextException;

import nsf.ecap.MonitorPlan_Suite.TestSuiteBase_MonitorPlan;

/*import org.jaxen.function.NormalizeSpaceFunction;*/
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;


public class BS_15_NSFOnlineAccountSetup extends TestSuiteBase_New_NSFOnline {
	
	public String CustomerSettingsTAB_SelectProfile_DD = null;
	public String CustomerSettingsTAB_DefaultHomePage_DD = null;
	public String CustomerSettingsTAB_FacilityUserHomePage_DD = null;
	public String CustomLabelsTAB_SelectEntity_DD = null;
	public String CustomLabelsTAB_ChooseDefaultValue_input = null;
	public String CustomLabelsTAB_ChooseDefaultValue_AjaxList = null;
	public String CustomLabelsTAB_TypeCustomeValueHere_input = null;
	public String AuditSettingsTAB_Audit_Type = null;
	
	
	


@Parameters({ "className" })
@BeforeTest
public void checkTestSkip(String className) throws Exception {
	
	if (className.isEmpty()) {
		className = this.getClass().getSimpleName();
	}
	IsBrowserPresentAlready =false;
	fnsCheckClassLevelTestSkip(className);
	fnsApps_Report_Logs("################################## [BS-15]  NSFOnline Account Setup Test Cases");
}


//##########################################   EXCEL Variable ###############################################################################################






@Test( priority = 1)
public void Login_into_iPulse_and_SwitchTo_NSfOnline_2_by_Clicking_on_NSFOnlineAccountSetup_Button() throws Throwable{
	try{
		CustomerSettingsTAB_SelectProfile_DD = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "SelectProfile_DD");
		CustomerSettingsTAB_DefaultHomePage_DD = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "DefaultHomePage_DD");
		CustomerSettingsTAB_FacilityUserHomePage_DD = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "FacilityUserHomePage_DD");
		CustomLabelsTAB_SelectEntity_DD = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "SelectEntity_DD");
		CustomLabelsTAB_ChooseDefaultValue_input = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "ChooseDefaultValue_input");
		CustomLabelsTAB_ChooseDefaultValue_AjaxList = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "ChooseDefaultValue_AjaxList");
		CustomLabelsTAB_TypeCustomeValueHere_input = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "TypeCustomeValueHere_input");
		AuditSettingsTAB_Audit_Type = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Audit_Type");
		fnsApps_Report_Logs("################### Test Case ::1 Login_into_iPulse_and_SwitchTo_NSfOnline_2_by_Clicking_on_NSFOnlineAccountSetup_Button ");
		if (!IsBrowserPresentAlready) {
			IsBrowserPresentAlready = fnsBrowserLaunchAndLogin(Login_UserName, Login_Password);
			fnsLoading_UnwantedPopupError();
		}
	}catch(Throwable t){
		isTestCasePass = false;
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
	fnsApps_Report_Logs("=========================================================================================================================================");
}





@Test( priority = 2, dependsOnMethods={"Login_into_iPulse_and_SwitchTo_NSfOnline_2_by_Clicking_on_NSFOnlineAccountSetup_Button"}, description="")
public void Verify_Data_Updated_Successfully_under_CustomerSetting_TAB() throws Throwable{
	fnsApps_Report_Logs("################### Test Case ::2 Verify_Data_Updated_Successfully_under_CustomerSetting_TAB ");
	try{
		fnsGet_Element_Enabled("NSFOnlineAccountSetup_ScreenHeaderInfoBar_Div");
		String HeaderInfo_Text = fnsGet_Field_TEXT("NSFOnlineAccountSetup_ScreenHeaderInfoBar_Div").trim();
		
		try{
			assert ( (HeaderInfo_Text.toLowerCase()).contains(User_COCL_Account.toLowerCase()) ):
			"FAILED == After clicking on 'NSF Online Account Setup' button from ipulse, the 'Customer Setting' screen is displayed for some other user ["+HeaderInfo_Text+"], please refer the screen shot >> "+User_COCL_Account+"_NSFOnlineAccountSetup_Screen_Display_Fail"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED == Successfully verify that the 'Customer Setting' screen is opened for '"+User_COCL_Account+"' customer code.");
		}catch(Throwable t){
			fnsTake_Screen_Shot(User_COCL_Account+"_CustomerSetting_Screen_Display_Fail");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		
		try{
			String CustomerSettings_Default_Tab_Xpath = TestSuiteBase_MonitorPlan.WithOut_OR_Return_XPATH_if_More_than_One_Coming(OR_New_NSFOnline.getProperty("NSFOnlineAccountSetup_CustomerSettings_Tab_DefaultOpen"));
			assert (driver.findElement(By.xpath(CustomerSettings_Default_Tab_Xpath)).isDisplayed()):
				"FAILED == 'Customer Settings' default TAB is not displayed as default tab, please refer the screen shot >> CustomerSettingsTab_Not_Coming_as_DefaultOpen"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED == Successfully verify that the 'Customer Settings' default TAB is opened.");
		}catch(Throwable t){
			fnsTake_Screen_Shot("CustomerSettingsTab_Not_Coming_as_DefaultOpen");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		
		//fnsDD_value_Select_TagOPTION_DDTypeSelect("NSFOnlineAccountSetup_CustomeSettingTab_SelectProfile_DD", CustomerSettingsTAB_SelectProfile_DD, 60);
		
		fnsDD_value_Select_TagOPTION_DDTypeSelect("NSFOnlineAccountSetup_CustomeSettingTab_DefaultHomePage_DD", CustomerSettingsTAB_DefaultHomePage_DD, 60);
		
		fnsDD_value_Select_TagOPTION_DDTypeSelect("NSFOnlineAccountSetup_CustomeSettingTab_FacilityUserHomePage_DD", CustomerSettingsTAB_FacilityUserHomePage_DD, 60);
		
		fnsGet_Element_Enabled("NSFOnlineAccountSetup_Save_bttn");
		fnsWait_and_Click("NSFOnlineAccountSetup_Save_bttn");
		fnsLoading_Progressing_on_Popup_wait_for_Popup(3);
				
		fns_Verify_Success_or_Error_Message_on_ModelPopup_Generation(true, NewNsfOnline_Element_Max_Wait_Time, "Customer Settings updated successfully", 30);
		
		fnsGet_Element_Enabled("Confirmation_Popup_OK_Bttn");
		fnsWait_and_Click("Confirmation_Popup_OK_Bttn");
		fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
				
	}catch(Throwable t){
		isTestCasePass = false;
		fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
		throw new Exception(Throwables.getStackTraceAsString(t));
	}	
}




@Test( priority = 3, dependsOnMethods={"Verify_Data_Updated_Successfully_under_CustomerSetting_TAB"}, description="")
public void Verify_Data_Updated_Successfully_under_AccessRestriction_TAB() throws Throwable{
	fnsApps_Report_Logs("################### Test Case ::3 Verify_Data_Updated_Successfully_under_AccessRestriction_TAB ");
	try{
		String AccessRestriction_TAB_Xpath = TestSuiteBase_MonitorPlan.WithOut_OR_Return_XPATH_if_More_than_One_Coming(OR_New_NSFOnline.getProperty("NSFOnlineAccountSetup_AccessRestriction_Tab"));
		TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(AccessRestriction_TAB_Xpath);
		TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(AccessRestriction_TAB_Xpath);
		fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
		fnsLoading_UnwantedPopupError();
		
		
		fnsGet_Element_Enabled("NSFOnlineAccountSetup_AccessRestrictionTab_AdminMenu_CheckBox");
		if(driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("NSFOnlineAccountSetup_AccessRestrictionTab_AdminMenu_CheckBox_Checked"))).size()>0){
			fnsApps_Report_Logs("PASSED == 'Admin Menu' check box is already check.");
		}else{
			fnsWait_and_Click("NSFOnlineAccountSetup_AccessRestrictionTab_AdminMenu_CheckBox");
		}
				
	
		fnsGet_Element_Enabled("NSFOnlineAccountSetup_AccessRestrictionTab_ManageUsersMenu_CheckBox");
		if(driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("NSFOnlineAccountSetup_AccessRestrictionTab_ManageUsersMenu_CheckBox_Checked"))).size()>0){
			fnsApps_Report_Logs("PASSED == 'Manage Users Menu' check box is already check.");
		}else{
			fnsWait_and_Click("NSFOnlineAccountSetup_AccessRestrictionTab_ManageUsersMenu_CheckBox");
		}
		
		fnsGet_Element_Enabled("NSFOnlineAccountSetup_Save_bttn");
		fnsWait_and_Click("NSFOnlineAccountSetup_Save_bttn");
		fnsLoading_Progressing_on_Popup_wait_for_Popup(3);
		
		fns_Verify_Success_or_Error_Message_on_ModelPopup_Generation(true, NewNsfOnline_Element_Max_Wait_Time, "Access Restriction updated successfully", 30);
		
		fnsGet_Element_Enabled("Confirmation_Popup_OK_Bttn");
		fnsWait_and_Click("Confirmation_Popup_OK_Bttn");
		fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
		
	}catch(Throwable t){
		isTestCasePass = false;
		fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
}


@Test( priority = 4, dependsOnMethods={"Verify_Data_Updated_Successfully_under_AccessRestriction_TAB"}, description="")
public void Verify_Data_Updated_Successfully_under_CustomLabels_TAB() throws Throwable{
	fnsApps_Report_Logs("################### Test Case ::4 Verify_Data_Updated_Successfully_under_CustomLabels_TAB ");
	try{
		String CustomerSettingsTAB_Table_MatchingRow_Data = CustomLabelsTAB_SelectEntity_DD+", "+CustomLabelsTAB_ChooseDefaultValue_AjaxList+", "+CustomLabelsTAB_TypeCustomeValueHere_input;
		String CustomLabels_TAB_Xpath = TestSuiteBase_MonitorPlan.WithOut_OR_Return_XPATH_if_More_than_One_Coming(OR_New_NSFOnline.getProperty("NSFOnlineAccountSetup_CustomLabels_Tab"));
		TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(CustomLabels_TAB_Xpath);
		TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(CustomLabels_TAB_Xpath);
		fnsLoading_Progressing_on_Popup_wait_for_Popup(3);
		fnsLoading_UnwantedPopupError();
		
		fnsDD_value_Select_TagOPTION_DDTypeSelect("NSFOnlineAccountSetup_CustomLabelsTab_SelectEntity_DD", CustomLabelsTAB_SelectEntity_DD, 60);
		fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
		
		fnsGet_Element_Enabled("NSFOnlineAccountSetup_CustomLabelsTab_ChooseDefaultValue_input");
		fnsWait_and_Type("NSFOnlineAccountSetup_CustomLabelsTab_ChooseDefaultValue_input", CustomLabelsTAB_ChooseDefaultValue_input);
		

		fnsGet_Element_Enabled("NSFOnlineAccountSetup_CustomLabelsTab_ChooseDefaultValue_DD");
		
		try{			
			for(int i=0; i<=60; i++){
				boolean Value_Matched = false;
				try{
					List<WebElement> DDobjectlists=fnsGet_OR_New_NSFOnline_ObjectX("NSFOnlineAccountSetup_CustomLabelsTab_ChooseDefaultValue_DD").findElements(By.tagName("li"));
					for(WebElement dd_value:DDobjectlists){
						String dd_TEXT = dd_value.getText().toLowerCase().trim();
						if(dd_TEXT.equals(CustomLabelsTAB_ChooseDefaultValue_AjaxList.toLowerCase().trim())){
							dd_value.click();
							Value_Matched = true;
							break;
						}
					}
					if(Value_Matched){
						fnsApps_Report_Logs("PASSED == Value ["+CustomLabelsTAB_ChooseDefaultValue_AjaxList+"] selection is done from Ajax List <Choose Default Value>" );
						break;
					}else{
						throw new NotContextException("FAILED == Excel value < "+CustomLabelsTAB_ChooseDefaultValue_AjaxList+" > is not exists into the Ajax List <Choose Default Value>, please refer screenshot >> ValueSelect_Fail" +  fnsScreenShot_Date_format() );
					}	
					
				}catch(NotContextException NC){
					if(i==60){
						throw new Exception(Throwables.getStackTraceAsString(NC));
					}else{
						Thread.sleep(800);
					}
				}catch(NoSuchWindowException W){
					isTestCasePass = false;
					throw new Exception(W.getMessage());
				}catch(Throwable t) {
					throw new Exception("FAILED == value <"+CustomLabelsTAB_ChooseDefaultValue_AjaxList+"> selection is failed from the Ajax List <Choose Default Value>, please refer screenshot >> ValueSelect_Fail" +  fnsScreenShot_Date_format() +". Getting Exception  >> "+Throwables.getStackTraceAsString(t).trim() );
				}
			}
		}catch(Throwable t){
			isTestCasePass = false;
			fnsTake_Screen_Shot("ValueSelect_Fail");
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception (Throwables.getStackTraceAsString(t));
		}
		
		
		fnsGet_Element_Enabled("NSFOnlineAccountSetup_CustomLabelsTab_TypeCustomeValueHere_input");
		fnsWait_and_Type("NSFOnlineAccountSetup_CustomLabelsTab_TypeCustomeValueHere_input", CustomLabelsTAB_TypeCustomeValueHere_input);
		
		fnsGet_Element_Enabled("NSFOnlineAccountSetup_CustomLabelsTab_EnterIcon");
		fnsWait_and_Click("NSFOnlineAccountSetup_CustomLabelsTab_EnterIcon");
		
		fnsGet_Element_Enabled("NSFOnlineAccountSetup_Save_bttn");
		fnsWait_and_Click("NSFOnlineAccountSetup_Save_bttn");
		fnsLoading_Progressing_on_Popup_wait_for_Popup(3);		
		
		fns_Verify_Success_or_Error_Message_on_ModelPopup_Generation(true, NewNsfOnline_Element_Max_Wait_Time, "Custom Label(s) updated successfully", 25);
		
		fnsGet_Element_Enabled("Confirmation_Popup_OK_Bttn");
		fnsWait_and_Click("Confirmation_Popup_OK_Bttn");
		fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
		
		Integer Newlly_Added_Record_RowNumber = fncVerify_MatchingRowExists_DeleteOptional_and_Return_MatchingRowNumber(CustomerSettingsTAB_Table_MatchingRow_Data, true, false);
		
		String Record_pencil_Icon_Xpath = "(//span[@id='customNavigationId'])["+Newlly_Added_Record_RowNumber+"]";
		TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(Record_pencil_Icon_Xpath);
		TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(Record_pencil_Icon_Xpath);
		fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
		
		fnsGet_Element_Enabled("NSFOnlineAccountSetup_CustomLabelsTab_Edit_Icon_Header");
		String Edit_Icon_Header = fnsGet_Field_TEXT("NSFOnlineAccountSetup_CustomLabelsTab_Edit_Icon_Header").toLowerCase().trim();
		Edit_Icon_Header = Edit_Icon_Header.replace("\n", " "); 
			
		try{ 
		   assert Edit_Icon_Header.equals("translate video not available back") :
			 "FAILED == 'Translate Video' screen is not getting open , please refer the screen shot >> TranslateVideo_Screen_Not_Open" +fnsScreenShot_Date_format();
		    
		   }catch(Throwable t){
			  fnsTake_Screen_Shot("TranslateVideo_Screen_Not_Open"); 
			  throw new Exception(Throwables.getStackTraceAsString(t)); }
			 
		
		String Edit_Icon_DefaultLabel = fnsGet_Field_TEXT("NSFOnlineAccountSetup_CustomLabelsTab_Edit_Icon_DefaultLabel").toLowerCase().trim();
		try{
			assert Edit_Icon_DefaultLabel.equals(CustomLabelsTAB_ChooseDefaultValue_AjaxList.toLowerCase()) : 
			"FAILED == 'Video not available' is not coming under the <Default Label:> label , please refer the screen shot >> Video_Value_Not_Coming"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED == successfully verified that the 'Video not available' value is coming under the <Default Label:> label.");
		}catch(Throwable t){
			fnsTake_Screen_Shot("Video_Value_Not_Coming");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		
		
		String Edit_Icon_TranslatedCustomLabel = fnsGet_OR_New_NSFOnline_ObjectX("NSFOnlineAccountSetup_CustomLabelsTab_Edit_Icon_TranslatedCustomLabel").getAttribute("value").toLowerCase().trim();
		try{
			assert Edit_Icon_TranslatedCustomLabel.equals(CustomLabelsTAB_TypeCustomeValueHere_input.toLowerCase()) : 
			"FAILED == 'Video not present' is not coming under the <Custom Label:> label, please refer the screen shot >> Video not present_Value_Not_Coming"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED == successfully verified that the 'Video not present' value is coming under the <Custom Label:> label.");
		}catch(Throwable t){
			fnsTake_Screen_Shot("Video not present_Value_Not_Coming");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		
		
		fnsWait_and_Click("NSFOnlineAccountSetup_CustomLabelsTab_Edit_Icon_Back_bttn");
		//fnsLoading_Progressing_on_Popup_wait_for_Popup(4);
		fnsLoading_Progressing_on_Popup_wait_for_Popup(1);
		
		fncVerify_MatchingRowExists_DeleteOptional_and_Return_MatchingRowNumber(CustomerSettingsTAB_Table_MatchingRow_Data, true, true);
		
	}catch(Throwable t){
		isTestCasePass = false;
		fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
}



@Test( priority = 5, dependsOnMethods={"Verify_Data_Updated_Successfully_under_CustomLabels_TAB"}, description="")
public void Verify_Data_Updated_Successfully_under_AuditSettings_TAB() throws Throwable{
	fnsApps_Report_Logs("################### Test Case ::5 Verify_Data_Updated_Successfully_under_AuditSettings_TAB ");
	try{
		String AuditSettings_TAB_Xpath = "";
		
		if(env.equalsIgnoreCase("test")){
			AuditSettings_TAB_Xpath = TestSuiteBase_MonitorPlan.WithOut_OR_Return_XPATH_if_More_than_One_Coming(OR_New_NSFOnline.getProperty("NSFOnlineAccountSetup_AssessmentSettings_Tab"));
		}else{
			//AuditSettings_TAB_Xpath = TestSuiteBase_MonitorPlan.WithOut_OR_Return_XPATH_if_More_than_One_Coming(OR_New_NSFOnline.getProperty("NSFOnlineAccountSetup_AuditSettings_Tab"));
			AuditSettings_TAB_Xpath = TestSuiteBase_MonitorPlan.WithOut_OR_Return_XPATH_if_More_than_One_Coming(OR_New_NSFOnline.getProperty("NSFOnlineAccountSetup_AssessmentSettings_Tab"));
		}
		TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(AuditSettings_TAB_Xpath);
		TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(AuditSettings_TAB_Xpath);
		fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
		fnsLoading_UnwantedPopupError();
		
		Integer Record_Found_RowNumber = fncVerify_MatchingRowExists_DeleteOptional_and_Return_MatchingRowNumber(AuditSettingsTAB_Audit_Type, true, false);  //BDMAUDIT
		
		//String Audit_Edit_bttn_Xpath = "(//img[@class='editImg'])["+Record_Found_RowNumber+"]";
		String Audit_Edit_bttn_Xpath = "(.//img[@id='assementEditClickHandlerId'])["+Record_Found_RowNumber+"]";
		
		
		TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(Audit_Edit_bttn_Xpath);
		TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(Audit_Edit_bttn_Xpath);
		fnsLoading_Progressing_on_Popup_wait_for_Popup(1);
		
		
		fnsGet_Element_Enabled("NSFOnlineAccountSetup_AuditSettings_EditAudit_DataTable");
		List<WebElement> Table_Row_Objects = fnsGet_OR_New_NSFOnline_ObjectX("NSFOnlineAccountSetup_AuditSettings_EditAudit_DataTable").findElements(By.tagName("tr"));
		
		try{
			assert ( (Table_Row_Objects.size())==1) : 
			"FAILED == "+( (Table_Row_Objects.size())-1)+" more records are displayed on the <Edit Audit> popup. [Expectd record is only '"+AuditSettingsTAB_Audit_Type+"'], please refer the screen shot >> EditAudit_Popup_Record_Count_Fail"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED == successfully verified that only one record is coming on <Edit Audit> popup.");
		}catch(Throwable t){
			fnsTake_Screen_Shot("EditAudit_Popup_Record_Count_Fail");
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		for(WebElement Table_Row_Elements : Table_Row_Objects){
			String Row_Text = Table_Row_Elements.getText().toLowerCase().trim();
			if(Row_Text.contains(AuditSettingsTAB_Audit_Type.toLowerCase().trim())){
				fnsApps_Report_Logs("PASSED == successfully verified that the audit '"+AuditSettingsTAB_Audit_Type+"' is coming on <Edit Audit> popup.");
			}else{
				fnsTake_Screen_Shot("Audit_Not_Coming");
				throw new Exception("FAILED == audit '"+AuditSettingsTAB_Audit_Type+"' is not coming on <Edit Audit> popup,  please refer the screen shot >> Audit_Not_Coming"+fnsScreenShot_Date_format());
			}
		}
		
		fnsGet_Element_Enabled("NSFOnlineAccountSetup_AuditSettings_EditAudit_SaveClose_Bttn");
		fnsWait_and_Click("NSFOnlineAccountSetup_AuditSettings_EditAudit_SaveClose_Bttn");
		fnsLoading_Progressing_on_Popup_wait_for_Popup(1);
		
		if(env.equalsIgnoreCase("test")){
			fns_Verify_Success_or_Error_Message_on_ModelPopup_Generation(true, NewNsfOnline_Element_Max_Wait_Time, "Assessment Settings updated successfully", 25);
			//fns_Verify_Success_or_Error_Message_on_ModelPopup_Generation(true, NewNsfOnline_Element_Max_Wait_Time, "Audit Settings updated successfully", 25);//Commented as the message is changed 25.4.2020
		}{
			fns_Verify_Success_or_Error_Message_on_ModelPopup_Generation(true, NewNsfOnline_Element_Max_Wait_Time, "Assessment Settings updated successfully", 25);
		}
		
		fnsGet_Element_Enabled("Confirmation_Popup_OK_Bttn");
		fnsWait_and_Click("Confirmation_Popup_OK_Bttn");
		fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
		
	}catch(Throwable t){
		isTestCasePass = false;
		fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
}






//#######################################################  Class Method  ################################################################
public Integer fncVerify_MatchingRowExists_DeleteOptional_and_Return_MatchingRowNumber(String Matching_Row_Data, boolean Record_Existance_Mandatory, boolean Record_will_Delete) throws Throwable{
	int Matching_Row_Number = 0;
	try{
		String Matching_Text = Matching_Row_Data.toLowerCase().trim();
		fnsGet_Element_Enabled("View_Result_Table_ForAccountSetupScreen");
		boolean Matching_Row_Found = false;
		
		List<WebElement> Table_Row_Objects = fnsGet_OR_New_NSFOnline_ObjectX("View_Result_Table_ForAccountSetupScreen").findElements(By.tagName("tr"));
		for(WebElement Table_Row_Elements : Table_Row_Objects){
			Matching_Row_Number++;
			String Table_Row_Text = Table_Row_Elements.getText().toLowerCase().trim();
			if(Matching_Text.contains(",")){
				String Split_Text[] = Matching_Text.split(",");
				for(int i=0; i<Split_Text.length; i++){
					Matching_Row_Found = false;
					Record_Existance_Mandatory = false;
					if( (Table_Row_Text.contains(Matching_Text.split(",")[i])) ){
						Matching_Row_Found = true;
						Record_Existance_Mandatory = true;
					}
				}
			}else{
				if( (Table_Row_Text.contains(Matching_Text)) ){
					Matching_Row_Found = true;
					Record_Existance_Mandatory = true;
					break;
				}
			}
			if(Matching_Row_Found && Record_Existance_Mandatory){
				break;
			}			
		}
		
		if(Record_Existance_Mandatory){
			if(Matching_Row_Found){
				fnsApps_Report_Logs("PASSED == Record found into the table for data ["+Matching_Text+"].");
			}else{
				fnsTake_Screen_Shot("Record_Not_Found");
				throw new Exception("FAILED == ["+Matching_Row_Data+"] record is not found into the table, please refer the screen shot >> Record_Not_Found"+fnsScreenShot_Date_format());
			}
		}
		
		if(Record_will_Delete){
			if(Matching_Row_Found){
				String Delete_button_Xpath = "(//span[@class='k-icon k-i-delete k-i-trash deleteIcon'])["+Matching_Row_Number+"]";
				TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(Delete_button_Xpath);
				TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(Delete_button_Xpath);
				fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
				fnsGet_Element_Enabled("NSFOnlineAccountSetup_CustomLabelsTab_DeletePopup_OK_Bttn");
				fnsWait_and_Click("NSFOnlineAccountSetup_CustomLabelsTab_DeletePopup_OK_Bttn");
				fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
				
				if(driver.findElements(By.xpath("NewNsfOnline_Content_Model_Popup")).size()<0) {
					fns_Verify_Success_or_Error_Message_on_ModelPopup_Generation(true, NewNsfOnline_Element_Max_Wait_Time, "Custom Label(s) deleted successfully", 25);
					
					fnsGet_Element_Enabled("Confirmation_Popup_OK_Bttn");
					fnsWait_and_Click("Confirmation_Popup_OK_Bttn");
				}
				fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
				
			}
		}
	}catch(Throwable t){
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
	return Matching_Row_Number;	
}

//#######################################################  Configuration Method  ################################################################
@AfterTest
public void reportTestResult() throws Throwable {
	TestSuiteBase_MonitorPlan.fns_ReportTestResult_atClassLevel(New_NSFOnline_Suitexls,  (this.getClass().getSimpleName()), isTestCasePass );
}


@AfterTest
public void QuitBrowser() throws Throwable{
	try{
		driver.quit();
	}catch(Throwable t){
		//nothing to do			
	}		
}

}