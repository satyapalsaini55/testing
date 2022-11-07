package nsf.ecap.New_NSFOnline_Suite;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import nsf.ecap.MonitorPlan_Suite.TestSuiteBase_MonitorPlan;

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


public class BS_09_Invoice_n_Certificate extends TestSuiteBase_New_NSFOnline {



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

public String Invoice_View_Name = "Invoice - "+fnsReturn_CurrentTime();
public String Certificate_View_Name = "Certificate - "+fnsReturn_CurrentTime();




@Test( priority = 1)
public void Launch_Browser_and_Successfully_Login_into_the_Application() throws Throwable{
	try{
	
		Invoice_View_Name = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Invoice_View_Name");
		Certificate_View_Name = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Certificate_View_Name");
		
		
		fnsApps_Report_Logs("################################## [BS-09] Invoice and Certificate Sanity Testing");
		if (!IsBrowserPresentAlready) {
			IsBrowserPresentAlready = fnsBrowserLaunchAndLogin(Login_UserName, Login_Password);
			fnsSwitchAcoount_MultiAccess(this.getClass().getSimpleName(), "SwitchAccount_DD");
		}
	}catch(Throwable t){
		isTestCasePass = false;
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
	fnsApps_Report_Logs("=========================================================================================================================================");
}




@Test( priority = 2, dependsOnMethods={"Launch_Browser_and_Successfully_Login_into_the_Application"}, description="<Step1: Verify that logo is present for this user> <Step2: All the top right buttons are displayed properly.(Annoucements,NSF -Support - United Sattes -English,Signout,Help)>")
public void Verify_LOGO_Present_and_All_the_Buttons_are_Displayed_at_the_TopRight_of_the_LandingScreen() throws Throwable{
	fnsApps_Report_Logs("=========================================================================================================================================");
	fnsApps_Report_Logs("##### Test Case ::1 Verify_LOGO_Present_and_All_the_Buttons_are_Displayed_at_the_TopRight_of_the_LandingScreen ");
	
	
	try{		
		for(int wait=0; wait<=((NewNsfOnline_Element_Max_Wait_Time)); wait++){
			if(driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("NewNsfOnline_Logo_Img"))).size()>0){
				Thread.sleep(3000);
				assert fnsGet_OR_New_NSFOnline_ObjectX("NewNsfOnline_Logo_Img").isDisplayed():"FAILED == 'Logo' is not coming, please refer screen shot >> Logo_Not_Coming"+fnsScreenShot_Date_format();
				fnsApps_Report_Logs("PASSED == Verified that the 'LOGO' is coming.");
				break;
			}else{
				Thread.sleep(1000);
			}
			if(wait==((NewNsfOnline_Element_Max_Wait_Time))){
				throw new Exception("FAILED == 'Logo' is not coming, After "+((NewNsfOnline_Element_Max_Wait_Time))+" seconds wait, please refer screen shot >> Logo_Not_Coming"+fnsScreenShot_Date_format());
			}
		}
		
	}catch(Throwable t){
		isTestCasePass = false;
		fnsTake_Screen_Shot("Logo_Not_Coming");
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
	
	
	fnsGet_Element_Displayed("NewNsfOnline_TopRightMenu_Div");
	try{
		ArrayList<String> TopRightMenu_Label_Name_List_from_Excel = new ArrayList<String>();
		if( fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "TopRightMenu_Label_Name_List").contains(",") ){
			for(int i=0; i<fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "TopRightMenu_Label_Name_List").split(",").length; i++){
				TopRightMenu_Label_Name_List_from_Excel.add(fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "TopRightMenu_Label_Name_List").split(",")[i].trim());
			}
		}else{
			TopRightMenu_Label_Name_List_from_Excel.add(fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "TopRightMenu_Label_Name_List").trim() );
		}
		
		List<WebElement> TopRightMenu_Label_All_Objects = fnsGet_OR_New_NSFOnline_ObjectX("NewNsfOnline_TopRightMenu_Div").findElements(By.tagName("label"));
		
		for(int i=0; i<TopRightMenu_Label_Name_List_from_Excel.size(); i++){
			String TopRightMenu_Label_Name_from_List = TopRightMenu_Label_Name_List_from_Excel.get(i).trim();
			boolean label_found = false;
			for(WebElement TopRightMenu_Label_Elements : TopRightMenu_Label_All_Objects){
				if( (TopRightMenu_Label_Elements.getText().toLowerCase().trim()).contains(TopRightMenu_Label_Name_from_List.toLowerCase()) ){
					label_found = true;
					break;
				}
			}
			if(label_found==true){
				fnsApps_Report_Logs("PASSED == button '"+TopRightMenu_Label_Name_from_List+"' is coming into the 'Top Right Menu' section.");
			}else{
				fnsTake_Screen_Shot("button_Not_Found");
				throw new Exception("FAILED == button '"+TopRightMenu_Label_Name_from_List+"' is not coming into the 'Top Right Menu' section, please refer the screen shot >> button_Not_Found"+fnsScreenShot_Date_format());
			}
		}
	}catch(Throwable t){
		isTestCasePass = false;
		fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
		throw new Exception (Throwables.getStackTraceAsString(t));
	}
	
}



@Test( priority = 3, dependsOnMethods={"Verify_LOGO_Present_and_All_the_Buttons_are_Displayed_at_the_TopRight_of_the_LandingScreen"}, description="")
public void Invoice_CreateView_then_Click_on_InvoiceNumber_and_Verify_Invoice_File_Download() throws Throwable{
	fnsApps_Report_Logs("=========================================================================================================================================");
	fnsApps_Report_Logs("##### Test Case ::2 Invoice_CreateView_then_Click_on_InvoiceNumber_and_Verify_Invoice_File_Download ");
	try{	
		fncSub_Menu_Ajax_Link_Click_By_PassingAjaxPath("Financials_Ajax", "Financials_SubMenu_Invoice_Ajax");
		
		fncNavigation_Verify_Application_Navigated_To_ViewScreen("'Invoice' SubMenu Ajax", "Invoice");
		/*
		fnsLoading_Progressing_wait(5);
		for(int wait=0; wait<=((NewNsfOnline_Element_Max_Wait_Time)); wait++){
			if(driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("CreateNewView_Link"))).size()>0){
				fnsApps_Report_Logs("PASSED == Successfully opened 'Invoice' screen.");
				fnsLoading_Progressing_wait(2);
				break;
			}else{
				Thread.sleep(1000);
			}
			if(wait==((NewNsfOnline_Element_Max_Wait_Time))){
				fnsTake_Screen_Shot("InvoiceScreen_Open_Fail");
				throw new Exception("FAILED == 'Invoice' screen is not getting open, After "+((NewNsfOnline_Element_Max_Wait_Time))+" seconds wait, please refer screen shot >> InvoiceScreen_Open_Fail"+fnsScreenShot_Date_format());
			}
		}*/
		
		fncVerify_View_Display_Open_and_Delete_it(2, Invoice_View_Name, "View_Delete_Link", "View_Remove_Link");
		
		fnsGet_Element_Enabled("CreateNewView_Link");
		fnsWait_and_Click("CreateNewView_Link");
		fnsLoading_Progressing_wait(2);
		
		
		fnsGet_Element_Enabled("CreateView_Step1_ViewName");
		fnsLoading_Progressing_wait(1);
		
		fnsWait_and_Type("CreateView_Step1_ViewName", Invoice_View_Name);
		fnsLoading_Progressing_wait(2);
		
		
		fnsDD_Value_select_by_DDLabelName_MultiselectDD("Currency", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Invoice_CreateView_Currency_DD"));
				
		
		fnsGet_Element_Enabled("CreateView_Step1_Alert_CheckBox");
		fnsWait_and_Click("CreateView_Step1_Alert_CheckBox");
		fnsLoading_Progressing_wait(1);
		
		fnsGet_Element_Enabled("CreateView_Step1_Alert_CheckBox_AssignColor_RED_Radio_bttn");
		fnsWait_and_Click("CreateView_Step1_Alert_CheckBox_AssignColor_RED_Radio_bttn");
		fnsLoading_Progressing_wait(1);
		
		
		fnsCreateView_Step3_Verify_ColumnExists_into_DD_ifNot_Then_Select("Invoice Number");
			
		
		fnsGet_Element_Enabled("CreateView_CreateView_Bttn");
		fnsWait_and_Click("CreateView_CreateView_Bttn");
			
		fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  60, "A new view has been added to your list", 25);
		fnsLoading_Progressing_wait(2);
	
		fncNavigation_Verify_Application_Navigated_To_ViewScreen("'CreateView' button", "Invoice");
		
		
		/*for(int wait=0; wait<=((NewNsfOnline_Element_Max_Wait_Time)); wait++){
			if(driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("CreateNewView_Link"))).size()>0){
				fnsApps_Report_Logs("PASSED == After clicking on 'Create View' button, application successfully navigated to 'Invoice' screen.");
				fnsLoading_Progressing_wait(3);
				break;
			}else{
				Thread.sleep(1000);
			}
			if(wait==((NewNsfOnline_Element_Max_Wait_Time))){
				fnsTake_Screen_Shot("InvoiceScreen_Navigation_Fail");
				throw new Exception("FAILED == After clicking on 'Create View' button, application  NOT  navigated to 'Invoice' screen, [ Wait Time ("+((NewNsfOnline_Element_Max_Wait_Time))+") seconds ], please refer screen shot >> InvoiceScreen_Navigation_Fail"+fnsScreenShot_Date_format());
			}
		}*/
			
		
		
		fncMyView_SharedView_NewlyCreatedView_Display_and_Conditional_Click(2, true, Invoice_View_Name, "Yes");
		
		
		fnsGet_Element_Enabled("View_Result_Table");
		List<WebElement> Result_Table_Rows_objects = fnsGet_OR_New_NSFOnline_ObjectX("View_Result_Table").findElements(By.tagName("tr"));
		Integer Result_Table_Rows_Count = Result_Table_Rows_objects.size();
		
		for(WebElement Result_Table_Rows_Element : Result_Table_Rows_objects){
			if( (Result_Table_Rows_Element.getText().toLowerCase().trim()).contains("no records found")){
				fnsTake_Screen_Shot("View_Records_Not_Coming");
				throw new Exception ("FAILED == Records are not coming into the view '"+Invoice_View_Name+"', please refer the screen shot >> View_Records_Not_Coming"+fnsScreenShot_Date_format());
			}else{
				break;
			}
		}
		
		Integer InvoiceNumber_ColumnNo = fnsReturn_ColumnNumber_from_TableHeaderXpath_WithoutOR(OR_New_NSFOnline.getProperty("View_Result_TableHeader"), "Invoice Number");
		
		
		long AllFileSize_AfterFileSave = 0;
		boolean File_Download_Success = false;
		String FileLocation = System.getProperty("user.home")+"\\Downloads";
		long AllFileSize_BeforeFileSave = FileUtils.sizeOfDirectory(new File(FileLocation));
		for(int RowNo=1; RowNo<=Result_Table_Rows_Count; RowNo++){
			String InvoiceNumber_Per_Row_Xpath = OR_New_NSFOnline.getProperty("View_Result_Table")+"/tr["+RowNo+"]/td["+InvoiceNumber_ColumnNo+"]/div/a";
			
			if(driver.findElement(By.xpath(InvoiceNumber_Per_Row_Xpath)).isDisplayed()){
				TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(InvoiceNumber_Per_Row_Xpath);
				fnsLoading_Progressing_on_Popup_wait_for_Popup(3);
				
				for(int wait=1; wait<=((NewNsfOnline_Element_Max_Wait_Time)); wait++){
				
					if(  (driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("Model_Popup_OK_Bttn"))).size()>0) ){
						if( (driver.findElement(By.xpath(OR_New_NSFOnline.getProperty("Model_Popup_OK_Bttn"))).isDisplayed()) ){
							
							fns_Verify_Success_or_Error_Message_on_ModelPopup_Generation(true, 10, "File not available", 15);
							fnsApps_Report_Logs("PASSED == 'File not available' validation message is coming for Invoice Number < "+TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(InvoiceNumber_Per_Row_Xpath) +" >.");
							fnsGet_Element_Enabled("Model_Popup_OK_Bttn");
							fnsGet_Element_Displayed("Model_Popup_OK_Bttn");
							Thread.sleep(1000);
							fnsWait_and_Click("Model_Popup_OK_Bttn");
							fnsLoading_Progressing_on_Popup_wait_for_Popup(3);
							break;
						}
					}else{				

						Actions action = new Actions(driver);
						action.keyDown(Keys.ALT);
						action.sendKeys("s");
						action.keyUp(Keys.ALT);
						action.build().perform();
						
						try{
							AllFileSize_AfterFileSave = FileUtils.sizeOfDirectory(new File((FileLocation)));
							if(AllFileSize_BeforeFileSave<AllFileSize_AfterFileSave){
								File_Download_Success =true;
								fnsApps_Report_Logs("PASSED == Successfully download file for Invoice Number < "+TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(InvoiceNumber_Per_Row_Xpath) +" >.");
								break;
							}
						}catch(IllegalArgumentException K){ 	
							//nothing to do
						}	
					
						
					}	
					if(File_Download_Success==true){
						break;
					}else if(wait==NewNsfOnline_Element_Max_Wait_Time){
						fnsTake_Screen_Shot("File_Not_Download");
						throw new Exception ("FAILED == File is not getting download for Invoice Number < "+TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(InvoiceNumber_Per_Row_Xpath) +" >, after <"+NewNsfOnline_Element_Max_Wait_Time+"> seconds wait, please refer the screen shot >> File_Not_Download"+fnsScreenShot_Date_format());
					}else {
						Thread.sleep(400);
					}
				}
				if(File_Download_Success==true){
					break;
				}else if(RowNo==Result_Table_Rows_Count){
					fnsApps_Report_Logs ("<><><><><><><><><><  File is not downloaded for all Invoice Number as 'File not available' message is coming for all invoice.   ><><><><><><>");
				}
			}	
		}
	}catch(Throwable t){
		isTestCasePass = false;
		fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
}






@Test( priority = 4, dependsOnMethods={"Invoice_CreateView_then_Click_on_InvoiceNumber_and_Verify_Invoice_File_Download"}, description="")
public void Certificate_CreateView_then_Click_on_CertificateNumber_and_Verify_Certificate_File_Download() throws Throwable{
	fnsApps_Report_Logs("=========================================================================================================================================");
	fnsApps_Report_Logs("##### Test Case ::3 Certificate_CreateView_then_Click_on_CertificateNumber_and_Verify_Certificate_File_Download ");
	try{
		
		fncMenu_Ajax_Link_Click_By_PassingAjaxPath("Certificate_Menu");
		fnsLoading_Progressing_wait(5);
		for(int wait=0; wait<=((NewNsfOnline_Element_Max_Wait_Time)); wait++){
			if(driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("ActiveView_DD"))).size()>0){
				fnsApps_Report_Logs("PASSED == Successfully opened 'Certificate' screen.");
				fnsLoading_Progressing_wait(2);
				break;
			}else{
				Thread.sleep(1000);
			}
			if(wait==((NewNsfOnline_Element_Max_Wait_Time))){
				fnsTake_Screen_Shot("CertificateScreen_Open_Fail");
				throw new Exception("FAILED == 'Certificate' screen is not getting open, After "+((NewNsfOnline_Element_Max_Wait_Time))+" seconds wait, please refer screen shot >> CertificateScreen_Open_Fail"+fnsScreenShot_Date_format());
			}
		}
		
		fncVerify_View_Display_Open_and_Delete_it(2, Certificate_View_Name, "View_Delete_Link", "View_Remove_Link");
		
		fnsGet_Element_Enabled("CreateNewView_Link");
		fnsWait_and_Click("CreateNewView_Link");
		fnsLoading_Progressing_wait(2);
		
		
		fnsGet_Element_Enabled("CreateView_Step1_ViewName");
		fnsLoading_Progressing_wait(1);
		
		fnsWait_and_Type("CreateView_Step1_ViewName", Certificate_View_Name);
		
		
		
		
		fnsLoading_Progressing_wait(2);
		
		
	//	fnsDD_value_Select_TagOPTION_DDTypeSelect("CreateView_Step2_StandardName_DD", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Certificate_CreateView_StandardName_DD"), (NewNsfOnline_Element_Max_Wait_Time/2));
		fnsDD_Value_select_by_DDLabelName_MultiselectDD("Standard", fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "Certificate_CreateView_StandardName_DD"));
		
		fnsGet_Element_Enabled("CreateView_Step1_Alert_CheckBox");
		fnsWait_and_Click("CreateView_Step1_Alert_CheckBox");
		fnsLoading_Progressing_wait(1);
		
		fnsGet_Element_Enabled("CreateView_Step1_Alert_CheckBox_AssignColor_RED_Radio_bttn");
		fnsWait_and_Click("CreateView_Step1_Alert_CheckBox_AssignColor_RED_Radio_bttn");
		fnsLoading_Progressing_wait(1);
		
		
		//Certificate No column is not coming into the list - bug		
		//fnsCreateView_Step3_Verify_ColumnExists_into_DD_ifNot_Then_Select("Certificate No");
			
		
		fnsGet_Element_Enabled("CreateView_CreateView_Bttn");
		fnsWait_and_Click("CreateView_CreateView_Bttn");
			
		fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  60, "A new view has been added to your list", 25);
		//fnsLoading_Progressing_wait(2);
	
		fncNavigation_Verify_Application_Navigated_To_ViewScreen("'CreateView' button", "Certificate");
		
		/*for(int wait=0; wait<=((NewNsfOnline_Element_Max_Wait_Time)); wait++){
			if(driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("CreateNewView_Link"))).size()>0){
				fnsApps_Report_Logs("PASSED == After clicking on 'Create View' button, application successfully navigated to 'Certificate' screen.");
				fnsLoading_Progressing_wait(3);
				break;
			}else{
				Thread.sleep(1000);
			}
			if(wait==((NewNsfOnline_Element_Max_Wait_Time))){
				fnsTake_Screen_Shot("CertificateScreen_Navigation_Fail");
				throw new Exception("FAILED == After clicking on 'Create View' button, application  NOT  navigated to 'Certificate' screen, [ Wait Time ("+((NewNsfOnline_Element_Max_Wait_Time))+") seconds ], please refer screen shot >> CertificateScreen_Navigation_Fail"+fnsScreenShot_Date_format());
			}
		}*/
			
		
		
		fncMyView_SharedView_NewlyCreatedView_Display_and_Conditional_Click(2, true, Certificate_View_Name, "Yes");
		
		
		fnsGet_Element_Enabled("View_Result_Table");
		List<WebElement> Result_Table_Rows_objects = fnsGet_OR_New_NSFOnline_ObjectX("View_Result_Table").findElements(By.tagName("tr"));
		Integer Result_Table_Rows_Count = Result_Table_Rows_objects.size();
		
		for(WebElement Result_Table_Rows_Element : Result_Table_Rows_objects){
			if( (Result_Table_Rows_Element.getText().toLowerCase().trim()).contains("no records found")){
				fnsTake_Screen_Shot("View_Records_Not_Coming");
				throw new Exception ("FAILED == Records are not coming into the view '"+Certificate_View_Name+"', please refer the screen shot >> View_Records_Not_Coming"+fnsScreenShot_Date_format());
			}else{
				break;
			}
		}		
		
		Integer CertificateNumber_ColumnNo = fnsReturn_ColumnNumber_from_TableHeaderXpath_WithoutOR(OR_New_NSFOnline.getProperty("View_Result_TableHeader"), "Certificate No");
		
		
		long AllFileSize_AfterFileSave = 0;
		boolean File_Download_Success = false;
		String FileLocation = System.getProperty("user.home")+"\\Downloads";
		long AllFileSize_BeforeFileSave = FileUtils.sizeOfDirectory(new File(FileLocation));
		for(int RowNo=1; RowNo<=Result_Table_Rows_Count; RowNo++){
			String CertificateNumber_Per_Row_Xpath = OR_New_NSFOnline.getProperty("View_Result_Table")+"/tr["+RowNo+"]/td["+CertificateNumber_ColumnNo+"]/div/a";
			
			if(driver.findElement(By.xpath(CertificateNumber_Per_Row_Xpath)).isDisplayed()){
				fnsLoading_Progressing_wait(1);
				
				TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element(CertificateNumber_Per_Row_Xpath);
				Thread.sleep(1500);
				TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(CertificateNumber_Per_Row_Xpath);
				fnsLoading_Progressing_on_Popup_wait_for_Popup(3);
				
				for(int wait=1; wait<=((NewNsfOnline_Element_Max_Wait_Time)); wait++){
				
					if(  (driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("Model_Popup_OK_Bttn"))).size()>0) ){
						if( (driver.findElement(By.xpath(OR_New_NSFOnline.getProperty("Model_Popup_OK_Bttn"))).isDisplayed()) ){
							
							fns_Verify_Success_or_Error_Message_on_ModelPopup_Generation(true, 10, "File not available", 15);
							fnsApps_Report_Logs("PASSED == 'File not available' validation message is coming for Certificate Number < "+TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(CertificateNumber_Per_Row_Xpath) +" >.");
							fnsGet_Element_Enabled("Model_Popup_OK_Bttn");
							fnsWait_and_Click("Model_Popup_OK_Bttn");
							Thread.sleep(2000);
							fnsLoading_Progressing_on_Popup_wait_for_Popup(3);
							break;
						}
					}else{				

						Actions action = new Actions(driver);
						action.keyDown(Keys.ALT);
						action.sendKeys("s");
						action.keyUp(Keys.ALT);
						action.build().perform();
						
						try{
							AllFileSize_AfterFileSave = FileUtils.sizeOfDirectory(new File((FileLocation)));
							if(AllFileSize_BeforeFileSave<AllFileSize_AfterFileSave){
								File_Download_Success =true;
								fnsApps_Report_Logs("PASSED == Successfully download file for Certificate Number < "+TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(CertificateNumber_Per_Row_Xpath) +" >.");
								break;
							}
						}catch(IllegalArgumentException K){ 	
							//nothing to do
						}	
					
						
					}	
					if(File_Download_Success==true){
						break;
					}else if(wait==NewNsfOnline_Element_Max_Wait_Time){
						fnsTake_Screen_Shot("File_Not_Download");
						throw new Exception ("FAILED == File is not getting download for Certificate Number < "+TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(CertificateNumber_Per_Row_Xpath) +" >, after <"+NewNsfOnline_Element_Max_Wait_Time+"> seconds wait, please refer the screen shot >> File_Not_Download"+fnsScreenShot_Date_format());
					}else {
						Thread.sleep(400);
					}
				}
				if(File_Download_Success==true){
					break;
				}else if(RowNo==Result_Table_Rows_Count){
					fnsApps_Report_Logs ("<><><><><><><><><><  File is not downloaded for all Certificate Number as 'File not available' message is coming for all Certificate.   ><><><><><><>");
				}
			}	
		}
	
	}catch(Throwable t){
		isTestCasePass = false;
		fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
}





@Test( priority = 5, dependsOnMethods={"Certificate_CreateView_then_Click_on_CertificateNumber_and_Verify_Certificate_File_Download"}, description="")
public void AlertScreen_Click_on_AlertMenu_and_Verify_AlertGenarated_for_Invoice_and_Certificate_View() throws Throwable{
	fnsApps_Report_Logs("=========================================================================================================================================");
	fnsApps_Report_Logs("##### Test Case ::4 AlertScreen_Click_on_AlertMenu_and_Verify_AlertGenarated_for_Invoice_and_Certificate_View ");
	try{
		
		fns_AlertScreen_Click_on_AlertMenu_and_Verify_View_and_Label_Present(true, false, "Invoice", Invoice_View_Name);
		
		fns_AlertScreen_Click_on_AlertMenu_and_Verify_View_and_Label_Present(false, false, "Certificates", Certificate_View_Name);
	
		
	}catch(Throwable t){
		isTestCasePass = false;
		fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
		throw new Exception(Throwables.getStackTraceAsString(t));
	}
}











//#######################################################  Configuration Method  ################################################################
@AfterTest
public void reportTestResult() throws Throwable {
	TestSuiteBase_MonitorPlan.fns_ReportTestResult_atClassLevel(New_NSFOnline_Suitexls,  (this.getClass().getSimpleName()) , isTestCasePass);
}


@AfterTest
public void QuitBrowser(){
	TestSuiteBase_MonitorPlan.MoveMouseCursorToTaskBar();
	driver.quit();
}

}