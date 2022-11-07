package nsf.ecap.New_NSFOnline_Suite;

import java.io.File;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;

public class BS_31_Product_Review extends TestSuiteBase_New_NSFOnline {
	
	public String  Product_Review_View_Name = "Automation - ProductView";
	public String  Product_Review_View_Name_Edit = "Automation - ProductView-Edited";
	public String Product_Review_View_Name_from_AdvanceSearch = "Automation - ProductView-AdvanceSearch";
	public String Product_Review_Status_DD = "Product Review";	
	public Integer Report_ColumnNo =null;	
	public String Report_Per_Row_Xpath=null;

	
	@Parameters({ "className" })
	@BeforeTest
	public void checkTestSkip(String className) throws Exception {
		if (className.isEmpty()) {
				className = this.getClass().getSimpleName();
		}
		RunningClassName = className;
		Running_Class_BS_Description = "[BS-31] Verify Product Review";
		IsBrowserPresentAlready =false;
		fnsCheckClassLevelTestSkip(className);
	}

	@Test( priority = 0)
	public void Launch_Browser_and_Successfully_Login_into_the_Product_Review() throws Throwable{
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
	
	@Test( priority = 1, dependsOnMethods={"Launch_Browser_and_Successfully_Login_into_the_Product_Review"}, description="")
	public void Create_Edit_Delete_View_and_verify_Alerts_then_Verify_AdvanceSearch_Count_with_View_Count_in_Product_Review() throws Throwable{
		fnsApps_Report_Logs("################### Test Case ::1 Create_Edit_Delete_View_and_verify_Alerts_then_Verify_AdvanceSearch_Count_with_View_Count_in_Product_Review");		
		try{
			String directoryName = System.getProperty("user.home") + "\\Downloads";
			File download_Files_FolderPath = new File(directoryName);
		   
		    File[] After_ButtonClick_filesList = null;
		    boolean File_Download_Successfully = false;
		    boolean FileNotAvailable_Coming = false;
		    File[] Before_ButtonClick_filesList = download_Files_FolderPath.listFiles();
		    
		    BS_26_Standard_Members BS_26_Standard_Members = new BS_26_Standard_Members();
			BS_26_Standard_Members.Standard_Members_Create_and_Edit_View_Flow_Only("Product Review", "Product_Review_Ajax", Product_Review_View_Name, Product_Review_View_Name_Edit);
			
			fns_AlertScreen_Click_on_AlertMenu_and_Verify_View_and_Label_Present(true, true, "Product Review", Product_Review_View_Name_Edit);			
			fnsLoading_Progressing_wait(2);
			
			fnsGet_Element_Enabled("View_Result_Table");			
			List<WebElement> Result_Table_Rows_objects = fnsGet_OR_New_NSFOnline_ObjectX("View_Result_Table").findElements(By.tagName("tr"));			
			Integer Result_Table_Rows_Count = Result_Table_Rows_objects.size();			
			for(WebElement Result_Table_Rows_Element : Result_Table_Rows_objects){
				if( (Result_Table_Rows_Element.getText().toLowerCase().trim()).contains("no records found")){
					fnsTake_Screen_Shot("View_Records_Not_Coming");
					throw new Exception ("FAILED == Records are not coming into the view '"+Product_Review_View_Name_Edit+"', please refer the screen shot >> View_Records_Not_Coming"+fnsScreenShot_Date_format());
				}else{
					break;
				}
			}
			
			Report_ColumnNo = fnsReturn_ColumnNumber_from_TableHeaderXpath_WithoutOR(OR_New_NSFOnline.getProperty("View_Result_TableHeader"), "Report");
			
			for(int RowNo=1; RowNo<=Result_Table_Rows_Count; RowNo++){         
				
			Report_Per_Row_Xpath = OR_New_NSFOnline.getProperty("View_Result_Table")+"/tr["+RowNo+"]/td["+Report_ColumnNo+"]";
				
			if(driver.findElements(By.xpath(Report_Per_Row_Xpath)).size()>0){
				if(driver.findElement(By.xpath(Report_Per_Row_Xpath)).isDisplayed()){
					TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(Report_Per_Row_Xpath);
					fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
					
					for(int wait=1; wait<=((NewNsfOnline_Element_Max_Wait_Time)); wait++){
						FileNotAvailable_Coming = false;
						if((driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("Model_Popup_OK_Bttn"))).size()>0)){
							if( (driver.findElement(By.xpath(OR_New_NSFOnline.getProperty("Model_Popup_OK_Bttn"))).isDisplayed()) ){									
								fns_Verify_Success_or_Error_Message_on_ModelPopup_Generation(true, 10, "File not available", 15);
								fnsApps_Report_Logs("PASSED == 'File not available' validation message is coming for doc Number < "+TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(Report_Per_Row_Xpath) +" >.");
								fnsGet_Element_Enabled("Model_Popup_OK_Bttn");
								fnsWait_and_Click("Model_Popup_OK_Bttn");
								fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
								FileNotAvailable_Coming = true;
								break;
							}
						}else{
							After_ButtonClick_filesList = download_Files_FolderPath.listFiles();
							if(After_ButtonClick_filesList!=null){
								if(After_ButtonClick_filesList.length>Before_ButtonClick_filesList.length){
									File_Download_Successfully = true;
									
									}
									    
									}else 
										if(File_Download_Successfully==true){
											fnsApps_Report_Logs("PASSED == File is successfully downloaded in Record No <"+RowNo+">. automation");
												break;
						}else{
						Thread.sleep(400);
								}	
							}
						}	
					if(FileNotAvailable_Coming == false){	
						if(File_Download_Successfully==false){
							 throw new Exception("FAILED == File  is not getting download, please refer the location >> "+directoryName);
							    								
					     }else{
					    	 break;
					    	 
							    }
					}
				     }	
				}
			}
			
			fnsGet_Element_Enabled("View_AdvanceSearch_Bttn");
			fnsWait_and_Click("View_AdvanceSearch_Bttn");
			fnsLoading_Progressing_wait(2);
			
			fnsDD_Value_select_by_DDLabelName_MultiselectDD("Job Type", Product_Review_Status_DD);
			fnsLoading_Progressing_wait(2);
			
			
			fnsGet_Element_Enabled("Product_Review_AdvanceSearch_bttn");
			fnsWait_and_Click("Product_Review_AdvanceSearch_bttn");
			fnsLoading_Progressing_wait(2);
			
			fnsGet_Element_Enabled("View_Result_Table");
			Result_Table_Rows_objects = fnsGet_OR_New_NSFOnline_ObjectX("View_Result_Table").findElements(By.tagName("tr"));	
			for(WebElement Result_Table_Rows_Element : Result_Table_Rows_objects){
				if( (Result_Table_Rows_Element.getText().toLowerCase().trim()).contains("no records found")){
					fnsTake_Screen_Shot("AdvanceSearch_Records_Not_Coming");
					throw new Exception ("FAILED == Records are not coming into the Advance Search '"+Product_Review_View_Name_Edit+"', please refer the screen shot >> AdvanceSearch_Records_Not_Coming"+fnsScreenShot_Date_format());
				}else{
					break;
				}
			}
					
			
			Integer Product_Review_AdvanceSearch_Total_no_of_Records_Count = fnsReturn_View_Results_Reords_Total_Count("Advance Search" , "View_Result_Table", 10);
			
			
			fnsGet_Element_Enabled("AdvanceSearch_SaveAsView");
			fnsWait_and_Click("AdvanceSearch_SaveAsView");
			fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
			
			fnsGet_Element_Enabled("AdvanceSearch_SaveAsView_PopUpBox");
			fnsWait_and_Click("AdvanceSearch_SaveAsView_PopUpBox");
			fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
			
			fnsGet_Element_Enabled("CreateView_Step1_ViewName");
			fnsWait_and_Clear("CreateView_Step1_ViewName");
			fnsWait_and_Type("CreateView_Step1_ViewName", Product_Review_View_Name_from_AdvanceSearch);
			fnsLoading_Progressing_wait(2);
			
			fnsGet_Element_Enabled("CreateView_CreateView_Bttn");
			fnsWait_and_Click("CreateView_CreateView_Bttn");
			
			fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  60, "A new view has been added to your list", 25);
			
			Integer Product_Review_Total_no_of_Records_Count = fnsReturn_View_Results_Reords_Total_Count("Product Review" , "View_Result_Table", 20);
			
			try{
				assert Product_Review_Total_no_of_Records_Count.equals(Product_Review_AdvanceSearch_Total_no_of_Records_Count):"FAILED == Advance Search results count <"+Product_Review_AdvanceSearch_Total_no_of_Records_Count+"> and VIEW Created-From-Advance-Search count <"+
						Product_Review_Total_no_of_Records_Count+"> are not matched, please refer screen shot >> Count_Mismatch"+fnsScreenShot_Date_format();
				fnsApps_Report_Logs("PASSED == Advance Search results count <"+Product_Review_AdvanceSearch_Total_no_of_Records_Count+"> and VIEW Created-From-Advance-Search count <"+ Product_Review_Total_no_of_Records_Count +"> are matched.");
			}catch(Throwable t){
				fnsTake_Screen_Shot("Count_Mismatch");
				throw new Exception(Throwables.getStackTraceAsString(t));
			}
		
			fncVerify_View_Display_Open_and_Delete_it(2, Product_Review_View_Name_from_AdvanceSearch, "View_Delete_Link", "View_Remove_Link");

		}catch(Throwable t){
			isTestCasePass = false;
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));
		}

}
	//#######################################################  Configuration Method  #############################################################################
	@AfterTest
	public void reportTestResult() throws Throwable {
		TestSuiteBase_MonitorPlan.fns_ReportTestResult_atClassLevel(New_NSFOnline_Suitexls,  (this.getClass().getSimpleName()), isTestCasePass );
	}
	
	@AfterTest
	public void QuitBrowser(){
		TestSuiteBase_MonitorPlan.MoveMouseCursorToTaskBar();
		driver.quit();
	}
	
}
	
	