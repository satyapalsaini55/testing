package nsf.ecap.New_NSFOnline_Suite;

import java.io.File;
import java.util.List;

import nsf.ecap.MonitorPlan_Suite.TestSuiteBase_MonitorPlan;
import nsf.ecap.util.TestUtil;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;

public class BS_18_Product_Doc_Report extends TestSuiteBase_New_NSFOnline {
	
	public int count = -1;
	public String View_Name = null;
	
	public Integer DocReportID_ColumnNo=null;
	public Integer Doc_Type_ColumnNo=null;
	
	public String DocReportID_Per_Row_Xpath = "";
	
	@Parameters({ "className" })
	@BeforeTest
	public void checkTestSkip(String className) throws Exception {
		if (className.isEmpty()) {
			className = this.getClass().getSimpleName();
		}
		IsBrowserPresentAlready =false;
		fnsCheckClassLevelTestSkip(className);
	}
	

	@Test(dataProvider = "getTestData", priority = 1)
	public void Verify_Reports_Downloaded_in_PDF_Format__for_the_following_Data__(String Report_Name, String Login_Application, String Login_User_Password) throws Throwable{
	
		fail = false;
		count++;
		Login_UserName = Login_User_Password.split(":")[0].trim();
		Login_Password = Login_User_Password.split(":")[1].trim();
		TC_Step=1;	
		Login_Application_Name = Login_Application.trim();
			
		try {			
			if ((runmodes[count].equalsIgnoreCase("N"))) {
				fnsApps_Report_Logs("****************************************************************************************************************************************");
				fnsApps_Report_Logs("################################## Runmode of Scenaio :: " + (count + 1)+" ("+Report_Name+") for Login User ["+Login_UserName+"]  is set to NO, So Skipping this scenario.");
				skip=true;
				throw new SkipException("Runmode of Scenaio :: " + (count + 1)+" ("+Report_Name+") for Login User ["+Login_UserName+"]  is set to NO, So Skipping this scenario.");
			}else{
				skip=false;
				fnsApps_Report_Logs("****************************************************************************************************************************************");
				fnsApps_Report_Logs("################################## Execution Logs of Scenaio :: "+(count + 1)+" ("+Report_Name+") for Login User ["+Login_UserName+"].");
				fnsApps_Report_Logs("");
					
				

				String directoryName = System.getProperty("user.home") + "\\Downloads";
				File download_Files_FolderPath = new File(directoryName);
			   
			    File[] After_ButtonClick_filesList = null;
			    boolean File_Download_Successfully = false;
			    boolean File_Download_in_PDF_Format = false;
			    boolean FileNotAvailable_Coming = false;
			    String Downloaded_File_Name = "";
		
				
				if (!IsBrowserPresentAlready) {
					IsBrowserPresentAlready = fnsBrowserLaunchAndLogin(Login_UserName, Login_Password);
				}
				
				if(Report_Name.contains("Doc Reports"))	{
					fncSub_Menu_Ajax_Link_Click_By_PassingAjaxPath("Product_Ajax", "Product_Submenu_DocReport_Ajax");
					Downloaded_File_Name = "Test_Results_Report.pdf";
				} else{
					if(Report_Name.contains("ARF URF")){
						fncSub_Menu_Ajax_Link_Click_By_PassingAjaxPath("Product_Ajax", "Product_Submenu_ARF_URF_Ajax");
						Downloaded_File_Name = "Test_Results_Report.pdf";
					}
				}
				
				try{
					FileUtils.forceDelete(new File((directoryName+"\\"+Downloaded_File_Name)));
					Thread.sleep(500);
				}catch(Throwable t){
					//nothing to do
				}
				
				File[] Before_ButtonClick_filesList = download_Files_FolderPath.listFiles();
				
				View_Name = "Automation - "+Report_Name.trim();
				
				fncNavigation_Verify_Application_Navigated_To_ViewScreen("'"+Report_Name.trim()+"' Menu Ajax", Report_Name.trim());
				fncVerify_View_Display_Open_and_Delete_it(2, View_Name, "View_Delete_Link", "View_Remove_Link");
				
				
				fnsGet_Element_Enabled("CreateNewView_Link");
				fnsWait_and_Click("CreateNewView_Link");
				fnsLoading_Progressing_wait(2);
				
				fnsGet_Element_Enabled("CreateView_Step1_ViewName");
				fnsLoading_Progressing_wait(1);
				fnsWait_and_Type("CreateView_Step1_ViewName", View_Name);
				Thread.sleep(1000);
				
				fnsGet_Element_Enabled("CreateView_CreateView_Bttn");
				fnsWait_and_Click("CreateView_CreateView_Bttn");
					
				fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  60, "A new view has been added to your list", 25);
			
				
				fncNavigation_Verify_Application_Navigated_To_ViewScreen("'CreateView' button", Report_Name.trim());
					
				fncMyView_SharedView_NewlyCreatedView_Display_and_Conditional_Click(2, true, View_Name, "Yes");
				
				fnsGet_Element_Enabled("View_Result_Table");
				
				List<WebElement> Result_Table_Rows_objects = fnsGet_OR_New_NSFOnline_ObjectX("View_Result_Table").findElements(By.tagName("tr"));
				Integer Result_Table_Rows_Count = Result_Table_Rows_objects.size();
				
				for(WebElement Result_Table_Rows_Element : Result_Table_Rows_objects){
					if( (Result_Table_Rows_Element.getText().toLowerCase().trim()).contains("no records found")){
						fnsTake_Screen_Shot("View_Records_Not_Coming");
						throw new Exception ("FAILED == Records are not coming into the view '"+View_Name+"', please refer the screen shot >> View_Records_Not_Coming"+fnsScreenShot_Date_format());
					}else{
						break;
					}
				}
				
					if(Report_Name.contains("Doc Reports")){
						DocReportID_ColumnNo = fnsReturn_ColumnNumber_from_TableHeaderXpath_WithoutOR(OR_New_NSFOnline.getProperty("View_Result_TableHeader"), "Doc Report ID");
					}else if(Report_Name.contains("ARF URF"))
						Doc_Type_ColumnNo = fnsReturn_ColumnNumber_from_TableHeaderXpath_WithoutOR(OR_New_NSFOnline.getProperty("View_Result_TableHeader"), "Doc Type");
								 			 				 	 
					for(int RowNo=1; RowNo<=Result_Table_Rows_Count; RowNo++){         
						if(Report_Name.contains("Doc Reports")){
							 DocReportID_Per_Row_Xpath = OR_New_NSFOnline.getProperty("View_Result_Table")+"/tr["+RowNo+"]/td["+DocReportID_ColumnNo+"]/a";
						}else if(Report_Name.contains("ARF URF")){
							 DocReportID_Per_Row_Xpath = OR_New_NSFOnline.getProperty("View_Result_Table")+"/tr["+RowNo+"]/td["+Doc_Type_ColumnNo+"]/a";
						if(driver.findElements(By.xpath(DocReportID_Per_Row_Xpath)).size()>0){
							if(driver.findElement(By.xpath(DocReportID_Per_Row_Xpath)).isDisplayed()){
								TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(DocReportID_Per_Row_Xpath);
								fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
								
								for(int wait=1; wait<=((NewNsfOnline_Element_Max_Wait_Time)); wait++){
								
									if((driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("Model_Popup_OK_Bttn"))).size()>0)){
										if( (driver.findElement(By.xpath(OR_New_NSFOnline.getProperty("Model_Popup_OK_Bttn"))).isDisplayed()) ){									
											fns_Verify_Success_or_Error_Message_on_ModelPopup_Generation(true, 10, "File not available", 15);
											fnsApps_Report_Logs("PASSED == 'File not available' validation message is coming for doc Number < "+TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(DocReportID_Per_Row_Xpath) +" >.");
											fnsGet_Element_Enabled("Model_Popup_OK_Bttn");
											fnsGet_Element_Displayed("Model_Popup_OK_Bttn");
											Thread.sleep(1000);
											fnsWait_and_Click("Model_Popup_OK_Bttn");
											fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
											break;
										}
									}else{
										After_ButtonClick_filesList = download_Files_FolderPath.listFiles();
										if(After_ButtonClick_filesList!=null){
											if(After_ButtonClick_filesList.length>Before_ButtonClick_filesList.length){
												File_Download_Successfully = true;
												 for (int i = 0; i < After_ButtonClick_filesList.length; i++) {
												    	if(After_ButtonClick_filesList[i].getName().equalsIgnoreCase(Downloaded_File_Name)) {
												    		File_Download_in_PDF_Format=true;
												    		break;
												    	}
												    }
											}else if
											(File_Download_Successfully==true){
										    	fnsApps_Report_Logs("PASSED == File '"+Downloaded_File_Name+"'  is successfully downloaded in Record No <"+RowNo+">.");
										    	break;
										    }else{
												Thread.sleep(400);
											}	
										}
									}	
									
									if(FileNotAvailable_Coming == false){
										if(File_Download_Successfully==false){
									    	throw new Exception("FAILED == File '"+Downloaded_File_Name+"' is not getting download, please refer the location >> "+directoryName);
									    }
										
										if(File_Download_in_PDF_Format==true){
									    	fnsApps_Report_Logs("PASSED == File '"+Downloaded_File_Name+"' is successfully downloaded in 'PDF' format in Record No <"+RowNo+">.");
									    	break;
									    }else{
									    	throw new Exception("FAILED == Either File format 'pdf' or Name '"+Downloaded_File_Name+"' is not match in Record No <"+RowNo+">, please refer the location >> "+directoryName);
									    }
									}
								}	
							}
						}
					}
				 }
			}
			
		}catch(SkipException se){
			throw new SkipException(Throwables.getStackTraceAsString(se));	
		}catch(NoSuchWindowException W){
			fail = true;
			isTestCasePass = false;
			throw new Exception(W.getMessage());
		}catch(Throwable t){
			fail = true;
			isTestCasePass = false;
			IsBrowserPresentAlready =false;
			driver.quit();
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));
		}		
	}
	
	
	//#######################################################  Configuration Method  ################################################################
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


	@AfterMethod
	public void QuitBrowser(){
		try{
			driver.quit();
		}catch (Throwable t){
			//nothing to do
		}
	}
	
	@DataProvider
	public Object[][] getTestData() {
		return TestUtil.getData(New_NSFOnline_Suitexls, this.getClass().getSimpleName());
	}
}	


