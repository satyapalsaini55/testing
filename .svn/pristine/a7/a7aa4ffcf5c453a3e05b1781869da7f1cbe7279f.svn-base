package nsf.ecap.New_NSFOnline_Suite;


import nsf.ecap.MonitorPlan_Suite.TestSuiteBase_MonitorPlan;
import nsf.ecap.util.TestUtil;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchWindowException;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;

public class BS_05_Document_AdvSrch_SrchDoc extends TestSuiteBase_New_NSFOnline {
	

	public int count = -1;
	public boolean BrowserOpen = false;
	public String Documents_View_Name = "Automation - Documents";
	
	
	 


@Parameters({ "className" })
@BeforeTest
public void checkTestSkip(String className) throws Throwable {

	if (className.isEmpty()) {
		className = this.getClass().getSimpleName();
	}
	fnsCheckClassLevelTestSkip(className);
	
}

	

	
	
	
	
	
	@Test(dataProvider = "getTestData", priority = 1)
	public void Verify_Documents_are_Displayed_as_Expected__(String Serial_No, String LoginUserName,String User_Type,String Search_DocumentsNos, /*String Sort_CustomerName, String CustomerName_Mandatory_if_Sorting*/ String Multi_Access) throws Exception{
		BrowserOpen = false;
		fail = false;
		count++;
		String Login_User_Name = LoginUserName.split("=")[1].trim();
		String CaseSerialNo = Serial_No.split("=")[1];
		//String CustomerName_Sorting = Sort_CustomerName.split("=")[1].toLowerCase().trim();
		Login_Password = "welcome1010";
		TC_Step=1;	
		try {
			
			if (!(runmodes[count].equalsIgnoreCase("Y"))) {
				fnsApps_Report_Logs("****************************************************************************************************************************************");
				fnsApps_Report_Logs("################################## Runmode of Case::" + (count + 1)+" for Login User ["+Login_User_Name+"]  is set to NO, So Skipping this Case.");
				skip=true;
				throw new SkipException("Runmode of Case::" + (count + 1)+" for Login User ["+Login_User_Name+"]  is set to NO, So Skipping this Case.");
			}else{
				BrowserOpen = true;
				skip=false;
				fnsApps_Report_Logs("****************************************************************************************************************************************");
				fnsApps_Report_Logs("################################## Execution Logs of Case::"+(count + 1)+" for Login User ["+Login_User_Name+"].");
				
				fnsBrowserLaunchAndLogin(Login_User_Name, Login_Password);
				fnsSwitchAcoount_MultiAccess(null, Multi_Access);
				
				if(driver.getTitle().toLowerCase().trim().contains("nsfonline")){
					fnsTake_Screen_Shot("NSFOnline_2.0_Open");
					throw new Exception("'NsfOnline 1.0' is getting open instead of 'NsOnline 2.0'"+" , Please refer the screen shot >> NSFOnline_2.0_Open"+fnsScreenShot_Date_format());
				}
				
				if(User_Type.trim().equals("CO")){
					fncSub_Menu_Ajax_Link_Click_By_PassingAjaxPath("Sites_Ajax", "Sites_SubMenu_Documents_Ajax");
				}else if(User_Type.trim().equals("CL")){
					fncSub_Menu_Ajax_Link_Click_By_PassingAjaxPath("Suppliers_Ajax", "Suppliers_SubMenu_Documents_Ajax");
				}
				
				fncNavigation_Verify_Application_Navigated_To_ViewScreen("'Documents' SubMenu Ajax", "Documents");
				
				/*
				fnsLoading_Progressing_wait(5);
				for(int wait=0; wait<=((NewNsfOnline_Element_Max_Wait_Time)); wait++){
					if(driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("CreateNewView_Link"))).size()>0){
						fnsApps_Report_Logs("PASSED == Successfully opened 'Documents' screen.");
						fnsLoading_Progressing_wait(2);
						break;
					}else{
						Thread.sleep(1000);
					}
					if(wait==((NewNsfOnline_Element_Max_Wait_Time))){
						fnsTake_Screen_Shot("DocumentsScreen_Open_Fail");
						throw new Exception("FAILED == 'Documents' screen is not getting open, After "+((NewNsfOnline_Element_Max_Wait_Time))+" seconds wait, please refer screen shot >> DocumentsScreen_Open_Fail"+fnsScreenShot_Date_format());
					}
				}*/
				
				fnsGet_Element_Enabled("View_AdvanceSearch_Bttn");
				fnsWait_and_Click("View_AdvanceSearch_Bttn");
				fnsLoading_Progressing_wait(5);
								
				fnsGet_Element_Enabled("DocumentsAdvanceSearch_DocumentNumber");
				
	
				
				String NoOfSets[] = Search_DocumentsNos.split(":");
				fnsApps_Report_Logs("Sets data are ["+Search_DocumentsNos+"]. and No. of DocumentSets are == " + NoOfSets.length);
		
				for (int j = 0; j < NoOfSets.length; j++) {
			
					String DocumentSet = NoOfSets[j];
					fnsApps_Report_Logs("***************** Current searching DocumentSet  is [" + DocumentSet+"]  ******************");
			
					String CurrentSet[] = DocumentSet.split(",");
	
					String DocumentNo = fnsRemoveFormatting(CurrentSet[0]);
					String SearchId_Condition_GoodBad = fnsRemoveFormatting(CurrentSet[1]).toLowerCase().trim();
						
					fnsWait_and_Clear("DocumentsAdvanceSearch_DocumentNumber");
					fnsWait_and_Type("DocumentsAdvanceSearch_DocumentNumber", DocumentNo);
					
					System.out.println("Search button count ======= "+driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("DocumentsAdvanceSearch_Search_Bttn"))).size());
					fnsWait_and_Click("DocumentsAdvanceSearch_Search_Bttn");
					fnsLoading_Progressing_wait(5);
					fnsGet_Element_Enabled("DocumentsAdvanceSearch_Result_Table");
					TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element(OR_New_NSFOnline.getProperty("DocumentsAdvanceSearch_Result_Table"));
					String TextFetch=fnsGet_Field_TEXT("DocumentsAdvanceSearch_Result_Table").toLowerCase().trim();
									
					if (SearchId_Condition_GoodBad.equalsIgnoreCase("good")) {
						try{
							assert TextFetch.contains(DocumentNo.toLowerCase().trim()):"FAILED == Documents No <"+DocumentNo+"> for User ("+Login_User_Name+") is not coming 'GOOD'<i.e. Not appear into the Advance Search results Table>, Please refer Screen shot >>"+DocumentNo+"_GoodFail"+fnsScreenShot_Date_format();
							fnsApps_Report_Logs("PASSED ==  Successfully Verify that Documents No <"+DocumentNo+"> for User ("+Login_User_Name+") is coming 'GOOD'.");
						}catch(Throwable t){
							fnsTake_Screen_Shot(DocumentNo+"_GoodFail");
							throw new Exception(Throwables.getStackTraceAsString(t));
						}
					}else if (SearchId_Condition_GoodBad.equalsIgnoreCase("bad")) {
						try{
							assert TextFetch.contains("no records found"):"FAILED == Documents No <"+DocumentNo+"> for User ("+Login_User_Name+") is not coming 'BAD'<i.e. Appear into the Advance Search results Table>, Please refer Screen shot >>"+DocumentNo+"_BadFail"+fnsScreenShot_Date_format();
							fnsApps_Report_Logs("PASSED ==  Successfully Verify that Documents No <"+DocumentNo+"> for User ("+Login_User_Name+") is coming 'BAD'.");
						}catch(Throwable t){
							fnsTake_Screen_Shot(DocumentNo+"_BadFail");
							throw new Exception(Throwables.getStackTraceAsString(t));	
						}
					}else{
						throw new Exception("FAILED == '"+SearchId_Condition_GoodBad.toUpperCase()+"' is mentioned for Documents No <"+DocumentNo+"> instead of mentioning Good/Bad into the Excel data sheet, Please correct it." );
					}
				}		
		
				
				
				

				//Sorting functionality is commented as it is not in the new nsfonline.
				/*//Sorting customer Name
				/try{
					if(CustomerName_Sorting.equals("yes")){
						fnsGet_Element_Enabled("DocumentsAdvanceSearch_Back_Bttn");
						fnsWait_and_Click("DocumentsAdvanceSearch_Back_Bttn");
						fnsLoading_Progressing_wait(3);
	
						fncVerify_View_Display_Open_and_Delete_it(Documents_View_Name, "View_Delete_Link", "View_Remove_Link");
						
						fnsGet_Element_Enabled("CreateNewView_Link");
						fnsWait_and_Click("CreateNewView_Link");
						fnsLoading_Progressing_wait(2);
											
						fnsGet_Element_Enabled("CreateView_Step1_ViewName");
						fnsLoading_Progressing_wait(1);
						fnsWait_and_Type("CreateView_Step1_ViewName", Documents_View_Name);
						
											
						fnsCreateView_Step3_Verify_ColumnExists_into_DD_ifNot_Then_Select("Customer Name");
						
						fnsGet_Element_Enabled("CreateView_CreateView_Bttn");
						fnsWait_and_Click("CreateView_CreateView_Bttn");
												
						fns_Verify_Success_message_coming_OR_Error_message_Coming("A new view has been added to your list", 25);
						fnsLoading_Progressing_wait(2);
					
						
						for(int wait=0; wait<=((NewNsfOnline_Element_Max_Wait_Time)); wait++){
							if(driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("CreateNewView_Link"))).size()>0){
								fnsApps_Report_Logs("PASSED == After clicking on 'Create View' button, application successfully navigated to 'Documents' screen.");
								fnsLoading_Progressing_wait(3);
								break;
							}else{
								Thread.sleep(1000);
							}
							if(wait==((NewNsfOnline_Element_Max_Wait_Time))){
								fnsTake_Screen_Shot("SiteScreen_Navigation_Fail");
								throw new Exception("FAILED == After clicking on 'Create View' button, application  NOT  navigated to 'Documents' screen, [ Wait Time ("+((NewNsfOnline_Element_Max_Wait_Time))+") seconds ], please refer screen shot >> SiteScreen_Navigation_Fail"+fnsScreenShot_Date_format());
							}
						}
						
						Integer CustomerName_Column_Number = fnsReturn_ColumnNumber_from_TableHeaderXpath("View_Result_TableHeader", "Customer Name") ;
						for(int RowNo=1; RowNo<=25; RowNo++){
							String Documents_View_CustomerName_Column_Text = "";
							String Result_PerRow_CustomerName_Column_Xpath = OR_New_NSFOnline.getProperty("View_Result_Table")+"/tr["+RowNo+"]/td["+CustomerName_Column_Number+"]";
							if(driver.findElements(By.xpath(Result_PerRow_CustomerName_Column_Xpath)).size()>0){
								Documents_View_CustomerName_Column_Text = driver.findElement(By.xpath(Result_PerRow_CustomerName_Column_Xpath)).getText().toLowerCase().trim();
								if(Documents_View_CustomerName_Column_Text.length()>0){
									try{
										assert Documents_View_CustomerName_Column_Text.equals(CustomerName_Mandatory_if_Sorting.toLowerCase().trim()):"Expected CustomerName("+CustomerName_Mandatory_if_Sorting.trim()+") is not Matched with ActualCustomerName("+Documents_View_CustomerName_Column_Text+") on Documents view screen, Please refer screen shot >> Sorting_Fail_"+  fnsScreenShot_Date_format() ;
										//break;
									}catch(Throwable t){
										fnsTake_Screen_Shot("Sorting_Fail_");
										throw new Exception (Throwables.getStackTraceAsString(t));
									}
								}
							}else if(driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("View_Pagination_NEXT_Link"))).size()>0){
								fnsWait_and_Click("View_Pagination_NEXT_Link");
								fnsLoading_Progressing_wait(2);
							}else{
								fnsApps_Report_Logs("PASSED == Sorting done for all records as Expected CustomerName("+CustomerName_Mandatory_if_Sorting.trim()+") is Matched with ActualCustomerName("+Documents_View_CustomerName_Column_Text+") on Documents view screen.");
								break;
							}	
						}
					}	
				}catch(Throwable t){
					throw new Exception ("Sorting Fail : "+Throwables.getStackTraceAsString(t));
				}*/
				
				
				
				
				
				
				
				
			}
			//isTestCasePass = true;
		}catch(SkipException sk){
			throw new SkipException("Runmode of Case::" + (count + 1)+" for Customer["+Login_User_Name+"]  is set to NO, So Skipping this Case.");
			
		}catch(NoSuchWindowException W){
			fail = true;
			isTestCasePass = false;
			throw new Exception(W.getMessage());
			
		}catch(Throwable t){
			fail = true;
			isTestCasePass = false;
			fnsApps_Report_Logs("Case:"+CaseSerialNo.trim()+Throwables.getStackTraceAsString(t));
			throw new Exception("Case:"+CaseSerialNo.trim()+Throwables.getStackTraceAsString(t));
			
		}
}

	

	
	

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
	

	@AfterMethod
	public void QuitBrowser(){
		if(BrowserOpen){
			driver.quit();
		}
	}
	
	
	
	@DataProvider
	public Object[][] getTestData() {
		return TestUtil.getData(New_NSFOnline_Suitexls, this.getClass().getSimpleName());
	}



}
