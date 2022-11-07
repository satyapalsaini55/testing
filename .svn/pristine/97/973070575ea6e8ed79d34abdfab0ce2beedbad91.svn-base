package nsf.ecap.New_NSFOnline_Suite;

import java.util.List;

import nsf.ecap.MonitorPlan_Suite.TestSuiteBase_MonitorPlan;
import nsf.ecap.util.TestUtil;

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

public class BS_07_Audit_Standard_AdvSearch extends TestSuiteBase_New_NSFOnline {
	

	public int count = -1;
	public boolean BrowserOpen = false;
	public String SiteStandards_View_Name = "Automation - Site Standards";
	
	
	 


@Parameters({ "className" })
@BeforeTest
public void checkTestSkip(String className) throws Exception {
	if (className.isEmpty()) {
		className = this.getClass().getSimpleName();
	}
	fnsCheckClassLevelTestSkip(className);
	
}

	

	
	
	
	
	
	@Test(dataProvider = "getTestData", priority = 1)
	public void Verify_Audits_are_Displayed_as_Expected__(String Serial_No, String LoginUserName, String User_Type, String Search_AuditsNos_or_MatchingText, String Multi_Access) throws Exception{
		BrowserOpen = false;
		fail = false;
		count++;
		String Login_User_Name = LoginUserName.split("=")[1].trim();
		String CaseSerialNo = Serial_No.split("=")[1];
		Login_Password = "welcome1010";
		TC_Step=1;
		boolean All_Records_Verify = true;
		String Column_Name = "";	
		Integer Text_Verify_Column_Number = null;
		
				
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
				
								
				if(User_Type.trim().equals("CO_Audits")){
					fncSub_Menu_Ajax_Link_Click_By_PassingAjaxPath("Sites_Ajax", "Sites_SubMenu_Audits_Ajax");
				}else if(User_Type.trim().equals("CL_Audits")){
					fncSub_Menu_Ajax_Link_Click_By_PassingAjaxPath("Suppliers_Ajax", "Suppliers_SubMenu_Audits_Ajax");
				}else if(User_Type.trim().equals("CO_Standards")){
					fncSub_Menu_Ajax_Link_Click_By_PassingAjaxPath("Sites_Ajax", "Sites_SubMenu_Standards_Ajax");
				}
				
				fncNavigation_Verify_Application_Navigated_To_ViewScreen("'Audits' Menu Ajax", "Audits");
				/*
				fnsLoading_Progressing_wait(5);
				for(int wait=0; wait<=((NewNsfOnline_Element_Max_Wait_Time)); wait++){
					if(driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("CreateNewView_Link"))).size()>0){
						fnsApps_Report_Logs("PASSED == Successfully opened 'Audits' screen.");
						fnsLoading_Progressing_wait(2);
						break;
					}else{
						Thread.sleep(1000);
					}
					if(wait==((NewNsfOnline_Element_Max_Wait_Time))){
						fnsTake_Screen_Shot("AuditsScreen_Open_Fail");
						throw new Exception("FAILED == 'Audits' screen is not getting open, After "+((NewNsfOnline_Element_Max_Wait_Time))+" seconds wait, please refer screen shot >> AuditsScreen_Open_Fail"+fnsScreenShot_Date_format());
					}
				}
				*/
				
				
				
				if(User_Type.trim().equals("CO_Standards")){
					Column_Name = "Standard Code";	
					fncVerify_View_Display_Open_and_Delete_it(2, SiteStandards_View_Name, "View_Delete_Link", "View_Remove_Link");
					
					fnsGet_Element_Enabled("CreateNewView_Link");
					fnsWait_and_Click("CreateNewView_Link");
					fnsLoading_Progressing_wait(2);
					
					fnsGet_Element_Enabled("CreateView_Step1_ViewName");
					fnsLoading_Progressing_wait(1);
					fnsWait_and_Type("CreateView_Step1_ViewName", SiteStandards_View_Name);
					Thread.sleep(3000);
										
					fnsCreateView_Step3_Verify_ColumnExists_into_DD_ifNot_Then_Select(Column_Name);
										
					fnsGet_Element_Enabled("CreateView_CreateView_Bttn");
					fnsWait_and_Click("CreateView_CreateView_Bttn");
						
					fns_Verify_Success_message_coming_OR_Error_message_Coming(true,  60, "A new view has been added to your list", 25);
					
					fncNavigation_Verify_Application_Navigated_To_ViewScreen("'CreateView' button", "Site Standards");
					
					/*
					fnsLoading_Progressing_wait(2);
					for(int wait=0; wait<=((NewNsfOnline_Element_Max_Wait_Time)); wait++){
						if(driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("CreateNewView_Link"))).size()>0){
							fnsApps_Report_Logs("PASSED == After clicking on 'Create View' button, application successfully navigated to 'Site Standards' screen.");
							fnsLoading_Progressing_wait(3);
							break;
						}else{
							Thread.sleep(1000);
						}
						if(wait==((NewNsfOnline_Element_Max_Wait_Time))){
							fnsTake_Screen_Shot("SiteStandardsScreen_Navigation_Fail");
							throw new Exception("FAILED == After clicking on 'Create View' button, application  NOT  navigated to 'Site Standards' screen, [ Wait Time ("+((NewNsfOnline_Element_Max_Wait_Time))+") seconds ], please refer screen shot >> SiteStandardsScreen_Navigation_Fail"+fnsScreenShot_Date_format());
						}
					}
						*/
						
					fncMyView_SharedView_NewlyCreatedView_Display_and_Conditional_Click(2, true, SiteStandards_View_Name, "Yes");	
				}
				
				
				
				if( (User_Type.trim().equals("CO_Audits")) || (User_Type.trim().equals("CL_Audits")) ){
					Column_Name = "Audit Type/ Std";	
					if(Login_User_Name.toLowerCase().trim().equals("c0177204admin")){
						Column_Name = "Site Code";	
					}
					fnsGet_Element_Enabled("View_AdvanceSearch_Bttn");
					fnsLoading_Progressing_wait(1);
					fnsWait_and_Click("View_AdvanceSearch_Bttn");
					fnsLoading_Progressing_wait(5);
								
					fnsGet_Element_Enabled("AuditsAdvanceSearch_Search_Bttn");
					fnsLoading_Progressing_wait(1);
		
					
					//############################      Verify Column values 'start with' amd 'matching text'	#############################################
					if( (Search_AuditsNos_or_MatchingText.toLowerCase().trim().contains("startwith")) || Search_AuditsNos_or_MatchingText.toLowerCase().trim().contains("matchingtext") ){
						fnsDD_Value_select_by_DDLabelName_MultiselectDD("Audit Status", "COMPLETED");
						
						fnsGet_Element_Enabled("AuditsAdvanceSearch_Search_Bttn");
						fnsWait_and_Click("AuditsAdvanceSearch_Search_Bttn");
						fnsLoading_Progressing_wait(5);
											

						fnsGet_Element_Enabled("AuditsAdvanceSearch_Result_Table");
						TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element(OR_New_NSFOnline.getProperty("AuditsAdvanceSearch_Result_Table"));
						String TextFetch_for_NoRecords_Verification=fnsGet_Field_TEXT("AuditsAdvanceSearch_Result_Table").toLowerCase().trim();
						
						try{
							assert !(TextFetch_for_NoRecords_Verification.contains("no records found")):"FAILED == Records are NOT coming for 'COMPLETED' audits, please refer the screen shot >> No_Records_Found"+fnsScreenShot_Date_format();
						}catch(Throwable t){
							fnsTake_Screen_Shot("No_Records_Found");
							throw new Exception(Throwables.getStackTraceAsString(t));	
						}
						
						
						
					}
				}
						
				
				if( (Search_AuditsNos_or_MatchingText.toLowerCase().trim().contains("startwith")) || Search_AuditsNos_or_MatchingText.toLowerCase().trim().contains("matchingtext") ){
									
					for(int j=1; j<=2; j++){
						try{
							Text_Verify_Column_Number = fnsReturn_ColumnNumber_from_TableHeaderXpath_WithoutOR(OR_New_NSFOnline.getProperty("View_Result_TableHeader"), Column_Name);
							All_Records_Verify = false;
							break;
						}catch(Throwable k){
							if(j==2){
								throw new Exception(Throwables.getStackTraceAsString(k));
							}else{
								Thread.sleep(5000);
							}
						}
					}
					
					
					Integer Total_Records_Verify = 0;
										
					while( !All_Records_Verify){
						List<WebElement> ResultsTable_all_tr = fnsGet_OR_New_NSFOnline_ObjectX("View_Result_Table").findElements(By.tagName("tr"));
						Total_Records_Verify = Total_Records_Verify + ResultsTable_all_tr.size();
						for(int RowNo=1; RowNo<=ResultsTable_all_tr.size(); RowNo++){
							String ResultTable_Row_Xpath = OR_New_NSFOnline.getProperty("View_Result_Table")+"/tr["+RowNo+"]";
							if(driver.findElements(By.xpath(ResultTable_Row_Xpath)).size()>0){
								String ResultTable_Row_Matching_Column_Xpath = ResultTable_Row_Xpath+"/td["+Text_Verify_Column_Number+"]";
								TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element(ResultTable_Row_Matching_Column_Xpath);
								String ResultTable_Row_Matching_Column_Text = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(ResultTable_Row_Matching_Column_Xpath).getText().toLowerCase().trim();
														
								if(Search_AuditsNos_or_MatchingText.toLowerCase().trim().contains("startwith")){
									String ResultTable_First4Letter_of_Matching_Column_Text = ResultTable_Row_Matching_Column_Text.substring(0, 4);
										
									String Split_Matching_ExcelText = Search_AuditsNos_or_MatchingText.split("=")[1].trim();
									try{
										assert ( ResultTable_First4Letter_of_Matching_Column_Text.equalsIgnoreCase( Split_Matching_ExcelText.toLowerCase()) ):"In RowNo "+RowNo+" , the '"+Column_Name+"' Column contains the Text<"+ResultTable_Row_Matching_Column_Text.toUpperCase()+">, which is not start with the Expected Text ("+Split_Matching_ExcelText+"). Please refer screenshot >> Case"+CaseSerialNo.trim()+"_TextStartWith_Fail"+fnsScreenShot_Date_format();
									}catch(Throwable t){
										fnsTake_Screen_Shot("Case"+CaseSerialNo.trim()+"_TextStartWith_Fail");
										throw new Exception(t.getMessage());
									}
										
										
								}else if(Search_AuditsNos_or_MatchingText.toLowerCase().trim().contains("matchingtext")){
									
									try{
										String Split_Matching_ExcelText = Search_AuditsNos_or_MatchingText.split("=")[1].trim();
										String NoOf_Matching_ExcelText_Sets[] = Split_Matching_ExcelText.split("/");
										boolean Matching_Text_Found = false;
										for (int k = 0; k < NoOf_Matching_ExcelText_Sets.length; k++) {
											String Matching_Text_from_Excel = NoOf_Matching_ExcelText_Sets[k].trim();
											if(Matching_Text_from_Excel.toLowerCase().equals(ResultTable_Row_Matching_Column_Text)){
												try{
													assert ( (Matching_Text_from_Excel.toLowerCase()).equalsIgnoreCase(ResultTable_Row_Matching_Column_Text) ):
														"In RowNo "+RowNo+" , the '"+Column_Name+"' Column contains the Text <"+ResultTable_Row_Matching_Column_Text.toUpperCase()+
														">, which is not matched with the Expected ("+Split_Matching_ExcelText+"), Please refer screenshot >> "+"Case"+CaseSerialNo.trim()+"_ColumnValueMatchFail"+fnsScreenShot_Date_format();
													fnsApps_Report_Logs("PASSED == Successfully matched column text <"+ResultTable_Row_Matching_Column_Text+"> with excel value ("+Split_Matching_ExcelText+") in row no "+RowNo+".");
													Matching_Text_Found = true;
													break;
												}catch(Throwable t){
													throw new Exception(t.getMessage());
												}
											}
										}
										if(Matching_Text_Found==false){
											throw new Exception ("In RowNo "+RowNo+" , the '"+Column_Name+"' Column contains the Text<"+ResultTable_Row_Matching_Column_Text.toUpperCase()+
													">, which is not matched with the Expected ("+Split_Matching_ExcelText+"), Please refer screenshot >> "+"Case"+CaseSerialNo.trim()+"_ColumnValueMatchFail"+fnsScreenShot_Date_format());
										}
									}catch(Throwable t){
										fnsTake_Screen_Shot("Case"+CaseSerialNo.trim()+"_ColumnValueMatchFail");
										throw new Exception(Throwables.getStackTraceAsString(t));
									}	
								}
												
							}
						}
					
						try{
							if(driver.findElements(By.xpath(OR_New_NSFOnline.getProperty("Pagination_DropDown_NEXT_Bttn"))).size()>0){
								driver.findElement(By.xpath(OR_New_NSFOnline.getProperty("Pagination_DropDown_NEXT_Bttn"))).click();
								fnsLoading_Progressing_wait(2);
							}else{
								break;
							}
						}catch(Throwable k){
							System.out.println("Breaking from NEXT button catch");
							break;
						}
						
					}
				//	fnsTake_Screen_Shot("Total_"+Total_Records_Verify+"_records");
					fnsApps_Report_Logs("Total_"+Total_Records_Verify+"_records");
							
							
							
							
				//############################      Verify Audit Good BAD	#############################################		
				}else{
					
					String NoOfSets[] = Search_AuditsNos_or_MatchingText.split(":");
					fnsApps_Report_Logs("Sets data are ["+Search_AuditsNos_or_MatchingText+"]. and No. of AuditSets are == " + NoOfSets.length);
			
					for (int j = 0; j < NoOfSets.length; j++) {
				
						String AuditSet = NoOfSets[j];
						fnsApps_Report_Logs("***************** Current searching AuditSet  is [" + AuditSet+"]  ******************");
				
						String CurrentSet[] = AuditSet.split(",");
		
						String AuditNo = fnsRemoveFormatting(CurrentSet[0]);
						String SearchId_Condition_GoodBad = fnsRemoveFormatting(CurrentSet[1]).toLowerCase().trim();
							
						
						if(User_Type.trim().equals("CO_Audits")){
							fnsWait_and_Clear("Site_AuditsAdvanceSearch_AuditNo");
							fnsWait_and_Type("Site_AuditsAdvanceSearch_AuditNo", AuditNo);
						}else if(User_Type.trim().equals("CL_Audits")){
							fnsWait_and_Clear("Supplier_AuditsAdvanceSearch_AuditNo");
							fnsWait_and_Type("Supplier_AuditsAdvanceSearch_AuditNo", AuditNo);
						}
						
						
						fnsWait_and_Click("AuditsAdvanceSearch_Search_Bttn");
						fnsLoading_Progressing_wait(5);
						fnsGet_Element_Enabled("AuditsAdvanceSearch_Result_Table");
						TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element(OR_New_NSFOnline.getProperty("AuditsAdvanceSearch_Result_Table"));
						String TextFetch=fnsGet_Field_TEXT("AuditsAdvanceSearch_Result_Table").toLowerCase().trim();
										
						if (SearchId_Condition_GoodBad.equalsIgnoreCase("good")) {
							try{
								assert TextFetch.contains(AuditNo.toLowerCase().trim()):"FAILED == Audits No <"+AuditNo+"> for User ("+Login_User_Name+") is not coming 'GOOD'<i.e. Not appear into the Advance Search results Table>, Please refer Screen shot >>"+AuditNo+"_GoodFail"+fnsScreenShot_Date_format();
								fnsApps_Report_Logs("PASSED ==  Successfully Verify that Audits No <"+AuditNo+"> for User ("+Login_User_Name+") is coming 'GOOD'.");
							}catch(Throwable t){
								fnsTake_Screen_Shot(AuditNo+"_GoodFail");
								throw new Exception(Throwables.getStackTraceAsString(t));
							}
						}else if (SearchId_Condition_GoodBad.equalsIgnoreCase("bad")) {
							try{
								assert TextFetch.contains("no records found"):"FAILED == Audits No <"+AuditNo+"> for User ("+Login_User_Name+") is not coming 'BAD'<i.e. Appear into the Advance Search results Table>, Please refer Screen shot >>"+AuditNo+"_BadFail"+fnsScreenShot_Date_format();
								fnsApps_Report_Logs("PASSED ==  Successfully Verify that Audits No <"+AuditNo+"> for User ("+Login_User_Name+") is coming 'BAD'.");
							}catch(Throwable t){
								fnsTake_Screen_Shot(AuditNo+"_BadFail");
								throw new Exception(Throwables.getStackTraceAsString(t));	
							}
						}else{
							throw new Exception("FAILED == '"+SearchId_Condition_GoodBad.toUpperCase()+"' is mentioned for Audits No <"+AuditNo+"> instead of mentioning Good/Bad into the Excel data sheet, Please correct it." );
						}
					}		
				}
			}			
			

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
