package nsf.ecap.New_NSFOnline_Suite;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import nsf.ecap.MonitorPlan_Suite.TestSuiteBase_MonitorPlan;
import nsf.ecap.util.TestUtil;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;

public class BS_19_Client_Resource_Reports extends TestSuiteBase_New_NSFOnline {
	
	public int count = -1;
	public String View_Name = null;
	public String directoryName = System.getProperty("user.home") + "\\Downloads";
	public File download_Files_FolderPath = new File(directoryName);   
	public boolean Will_Download_File = false;
	public boolean FileNotAvailable_Coming = false;
	public boolean File_Download_Successfully = false;
	public String Downloaded_File_Name = "";
	
	
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
	public void Verify_Reports_Downloaded_in_designated_Format__for_the_following_Data__(String Report_Name, String Login_Application, String Login_User_Password) throws Throwable{
	
		fail = false;
		count++;
		Login_UserName = Login_User_Password.split(":")[0].trim();
		Login_Password = Login_User_Password.split(":")[1].trim();
		TC_Step=1;	
		Login_Application_Name = Login_Application.trim();
			
		try {			
			if ((runmodes[count].equalsIgnoreCase("N"))) {
				fnsApps_Report_Logs("****************************************************************************************************************************************");
				fnsApps_Report_Logs("################################## Runmode of Scenaio :: " + (count + 1)+" ("+Report_Name+") for superadmin of customer ["+User_DefaultCustomerAccount_COCL+"]  is set to NO, So Skipping this scenario.");
				skip=true;
				throw new SkipException("Runmode of Scenaio :: " + (count + 1)+" ("+Report_Name+") for superadmin of customer ["+User_DefaultCustomerAccount_COCL+"]  is set to NO, So Skipping this scenario.");
			}else{
				skip=false;
				fnsApps_Report_Logs("****************************************************************************************************************************************");
				fnsApps_Report_Logs("################################## Execution Logs of Scenaio :: "+(count + 1)+" ("+Report_Name+") for superadmin of customer ["+User_DefaultCustomerAccount_COCL+"].");
				fnsApps_Report_Logs("");
					
				File[] Before_FileDownload_filesList ;
				File[] After_FileDownload_filesList ;
			    File_Download_Successfully = false;
			    FileNotAvailable_Coming = false;
			    Downloaded_File_Name = "";
				
				
				if (!IsBrowserPresentAlready) {
					IsBrowserPresentAlready = fnsBrowserLaunchAndLogin(Login_UserName, Login_Password);
				}
				
				switch (Report_Name) {
					case ("Forms") : {
						fncSub_Menu_Ajax_Link_Click_By_PassingAjaxPath("ClientResources_Ajax", "ClientResources_Submenu_Forms_Ajax");
						Will_Download_File = true;
						break;
					}
					case ("Policies & Instructions") :{
						fncSub_Menu_Ajax_Link_Click_By_PassingAjaxPath("ClientResources_Ajax", "ClientResources_Submenu_PoliciesInstructions_Ajax");
						Will_Download_File = true;
						break;
					}
					case ("Standards & Protocols") : {
						fncSub_Menu_Ajax_Link_Click_By_PassingAjaxPath("ClientResources_Ajax", "ClientResources_Submenu_StandardsProtocols_Ajax");
						Will_Download_File = true;
						break;
					}
					case ("Video Tutorials") : {
						fncSub_Menu_Ajax_Link_Click_By_PassingAjaxPath("ClientResources_Ajax", "ClientResources_Submenu_VideoTutorials_Ajax");
						Will_Download_File = false;
						break;
					}
					default : {
						throw new Exception ("FAILED == Excel Report Name '"+Report_Name+"' is not handled into the automation script.");
					}
				}
				
				
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
					
				fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
				fncMyView_SharedView_NewlyCreatedView_Display_and_Conditional_Click(2, true, View_Name, "No");
							
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
				
				if(Will_Download_File){
					Integer FileNumber_ColumnNo = fnsReturn_ColumnNumber_from_TableHeaderXpath_WithoutOR(OR_New_NSFOnline.getProperty("View_Result_TableHeader"), "File Number");
					
					for(int RowNo=1; RowNo<=Result_Table_Rows_Count; RowNo++){
						String FileNumber_Per_Row_Xpath = OR_New_NSFOnline.getProperty("View_Result_Table")+"/tr["+RowNo+"]/td["+FileNumber_ColumnNo+"]/a";
						FileNotAvailable_Coming = false;
						if(driver.findElements(By.xpath(FileNumber_Per_Row_Xpath)).size()>0){
							if(driver.findElement(By.xpath(FileNumber_Per_Row_Xpath)).isDisplayed()){
								Downloaded_File_Name = TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(FileNumber_Per_Row_Xpath).trim();
								
								String File_Format = ".pdf,.doc,.xlsx,.docx";
								for(int i=0; i <=File_Format.split(",").length; i++){
									try{
										FileUtils.forceDelete(new File((directoryName+"\\"+Downloaded_File_Name+File_Format.split(",")[i])));
										Thread.sleep(500);
										break;
									}catch(Throwable t){
										//nothing to do
									}
								}
								
								Before_FileDownload_filesList = download_Files_FolderPath.listFiles();
								
								TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(FileNumber_Per_Row_Xpath);
								fnsLoading_Progressing_on_Popup_wait_for_Popup(4);
								
								for(int wait=1; wait<=((NewNsfOnline_Element_Max_Wait_Time)); wait++){
								
									if(  (driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("Model_Popup_OK_Bttn"))).size()>0) ){
										if( (driver.findElement(By.xpath(OR_New_NSFOnline.getProperty("Model_Popup_OK_Bttn"))).isDisplayed()) ){									
											fns_Verify_Success_or_Error_Message_on_ModelPopup_Generation(true, 10, "File not available", 15);
											fnsApps_Report_Logs("PASSED == 'File not available' validation message is coming for File Number < "+TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(FileNumber_Per_Row_Xpath) +" >. automation");
											fnsGet_Element_Enabled("Model_Popup_OK_Bttn");
											fnsGet_Element_Displayed("Model_Popup_OK_Bttn");
											Thread.sleep(1000);
											fnsWait_and_Click("Model_Popup_OK_Bttn");
											fnsLoading_Progressing_on_Popup_wait_for_Popup(2);
											FileNotAvailable_Coming = true;
											break;
										}
									}else{
										After_FileDownload_filesList = download_Files_FolderPath.listFiles();
										if(After_FileDownload_filesList!=null){
											if(After_FileDownload_filesList.length>Before_FileDownload_filesList.length){
												File_Download_Successfully = true;
												break;
											}else{
												Thread.sleep(400);
											}	
										}
									}	
									if(wait==((NewNsfOnline_Element_Max_Wait_Time))){
										fnsTake_Screen_Shot("File_Downliad_Fail");
										throw new Exception ("FAILED == 'File download' is getting fail as after clicking on '"+Downloaded_File_Name+"' link, nothing is happened, please refer the screen shot >> File_Downliad_Fail"+fnsScreenShot_Date_format());
									}
								}	
								if(FileNotAvailable_Coming == false){
									if(File_Download_Successfully==true){
								    	fnsApps_Report_Logs("PASSED == File '"+Downloaded_File_Name+"'  is successfully downloaded at Record No <"+RowNo+">.");
								    	break;
								    }
								}
							}
						}
					}
				}else{
					
					String VideoTutorialNameColumn_First_Row_Link_Xpath = OR_New_NSFOnline.getProperty("View_Result_Table")+"/tr/td/a";
					TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(VideoTutorialNameColumn_First_Row_Link_Xpath);
					fnsLoading_Progressing_wait(2);
					
					
					for(int a=0; a<=120; a++){ 
						ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
						Integer tabsCount = tabs.size();
						if(tabsCount==3){
							for (int i = 0; i < tabsCount; i++) {
								if( !( (NsfConncet_Window_Handle.equalsIgnoreCase(tabs.get(i))) || (iPulse_Original_WindowHandle.equalsIgnoreCase(tabs.get(i))) ) ){
									driver.switchTo().window(tabs.get(i));
									fnsApps_Report_Logs("PASSED == Successfully Switch to 'You Tube' Window.");
									break;
								}
							}
							break;
						}else{
							Thread.sleep(1000);
						}
						if(a==Long.parseLong(CONFIG.getProperty("ElementWaitTime"))){
							fnsTake_Screen_Shot("SwitchYouTubeWindowFail");
							throw new Exception("FAILED == 'You Tube' window is not getting open after 120 Seconds wait, please refer the screen shot >> SwitchYouTubeWindowFail"+fnsScreenShot_Date_format());
						}
					}
					
				try{
					assert (driver.getCurrentUrl().toLowerCase().trim()).contains("youtube") : "FAILED == other application got open instead of 'you tube' application, please refer the screen shot >> YouTube_Window_Open_Fail"+fnsScreenShot_Date_format();
					fnsApps_Report_Logs("PASSED == successfully verified that 'you tube' application is opened.");
				}catch(Throwable t){
					fnsTake_Screen_Shot("YouTube_Window_Open_Fail");
					throw new Exception (Throwables.getStackTraceAsString(t));
				}
				
				//String Uploader_Info_des_Xpath_from_Youtube = "//div[@id='description']/yt-formatted-string"; //Working till 29.8.2019
				String Uploader_Info_des_Xpath_from_Youtube = "//h1[@class='title style-scope ytd-video-primary-info-renderer']";
				String Uploader_Info_des_Xpath = "";
				for(int i=0; i<= NewNsfOnline_Element_Max_Wait_Time; i++){
					try{
						if(( driver.findElements(By.xpath(Uploader_Info_des_Xpath_from_Youtube))).size()>0){
							Uploader_Info_des_Xpath = Uploader_Info_des_Xpath_from_Youtube;
							break;
						}
					}catch(Throwable t){
						Thread.sleep(250);
					}
				}
				
				if(Uploader_Info_des_Xpath.length()<2){
					for(int i=0; i<= NewNsfOnline_Element_Max_Wait_Time; i++){
						try{
							if(( driver.findElements(By.xpath(Uploader_Info_des_Xpath_from_Youtube))).size()>0){
								Uploader_Info_des_Xpath = Uploader_Info_des_Xpath_from_Youtube;
								break;
							}
						}catch(Throwable t){
							Thread.sleep(250);
						}
					}
				}
				
				String Uploader_Info_des_Text = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(Uploader_Info_des_Xpath).getText().trim();
				
				try{
					assert ( (Uploader_Info_des_Text.toLowerCase().trim()).contains("nsf") || (Uploader_Info_des_Text.toLowerCase().trim()).contains("international") || (Uploader_Info_des_Text.toLowerCase().trim()).contains("certification")): "FAILED == 'NSF' video is not getting open on you tube, please refer the screen shot >> NsfConnect_Screen_Not_Open_OnYouTube"+fnsScreenShot_Date_format();
					fnsApps_Report_Logs("PASSED == successfully verified that NSF video is opened on 'you tube' application >> "+Uploader_Info_des_Text);
				}catch(Throwable t){
					fnsTake_Screen_Shot("Nsf_Video_Not_Open_OnYouTube");
					throw new Exception ("FAILED == 'NSF' video is not getting open on 'you tube', please refer the screen shot >> Nsf_Video_Not_Open_OnYouTube"+fnsScreenShot_Date_format() + "  >>  "+Uploader_Info_des_Text);
				
				}
				
				
				/*
				for(int i=0; i<= NewNsfOnline_Element_Max_Wait_Time; i++){
					if( (driver.getPageSource().toLowerCase().trim()).contains("nsf connect")){
						fnsApps_Report_Logs("PASSED == successfully verified that nsf connect video is opened on 'you tube' application.");
						break;
					}else{
						Thread.sleep(1000);
					}
					if(i==NewNsfOnline_Element_Max_Wait_Time){
						fnsTake_Screen_Shot("NsfConnect_Screen_Not_Open_OnYouTube");
						throw new Exception ("FAILED == 'nsf connect' video is not getting open on you tube, please refer the screen shot >> NsfConnect_Screen_Not_Open_OnYouTube"+fnsScreenShot_Date_format());
					}
				}*/
				
					
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
		TestSuiteBase_MonitorPlan.fns_ReportTestResult_atClassLevel(New_NSFOnline_Suitexls,  (this.getClass().getSimpleName()), isTestCasePass );
	}


	@AfterTest
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


