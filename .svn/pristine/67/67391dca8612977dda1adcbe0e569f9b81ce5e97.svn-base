package nsf.ecap.NSFOnline_Suite;


import java.io.File;

import nsf.ecap.util.TestUtil;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.interactions.Actions;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;

public class NSFOnline_Search_Audits_Test extends TestSuiteBase_NSFOnline{
	
	public boolean fail = false;
	public boolean skip = true;
	public boolean NSFOnlineVersionScreenshotFlag = true;
	
	public boolean customer_C0180508 = false;
	
	public String AuditType;
	public String Split_Text = null;
	public String No_of_MatchingText_Set []= null;
	public String Matching_Text1 =null;
	public String Matching_Text2 =null;
	public String Matching_Text3 =null;
	public String Matching_Text4 =null;
	public String Matching_Text5 =null;
	public String Matching_Text6 =null;
	public String Matching_Text7 =null;
	public String Matching_Text8 =null;
	public String Matching_Text9 =null;
	public String Matching_Text10 =null;
	public long AllFileSize_AfterFileSave ;
	public long AllFileSize_BeforeFileSave ;

	
	public Integer ColumnNumber = null;
	public Integer GetTextLength;
	public Integer count = -1;
	
	public String Matching_or_NotMatchingText ;
	public boolean Matching_Text_Found;
	public String Matching_Text_from_Excel;

	
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
	fnsApps_Report_Logs("################################## [BS-03] NSFOnline Search Audits and Standards Test");
	if (!IsBrowserPresentAlready) {
		IsBrowserPresentAlready = fnsLaunchBrowserAndLogin();
		fnsApps_Report_Logs("Browser is launched and Successfully login into 'Insight' Application");	
	}
	fnsApps_Report_Logs("=========================================================================================================================================");
}
		
	
	
	
	
	
	
	@Test(dataProvider = "getTestData", priority = 1, dependsOnMethods={"Browser_Launch_and_Login_into_Insight_Application"})
	public void Insight_SearchCustomer_ThenOpen_NsfOnline_AndVerify_Audits_and_Standards__(String Serial_No, String Types_Of_Audit, String Customer_Number,String Insight_Search_User_Name,String Search_AuditsNos_and_Matching_or_NotMatchingText, String Multi_Access) throws Exception{
		System.out.println("Enter satya");
		Matching_or_NotMatchingText = Search_AuditsNos_and_Matching_or_NotMatchingText.trim();
		count++;
		Customer_No = Customer_Number.split("=")[1].trim();
		Insight_SearchUserName = Insight_Search_User_Name.split("=")[1].trim();
		AuditType=Types_Of_Audit.trim();
		CaseSerialNo = Serial_No.split("=")[1];
		
		if(Customer_Number.contains("C0180508")){
			customer_C0180508 = true;
		}
		
		
		
		
		try {
			
			if (!(runmodes[count].equalsIgnoreCase("Y"))) {
				fnsApps_Report_Logs("****************************************************************************************************************************************");
				fnsApps_Report_Logs("################################## Runmode of Case::" + (count + 1)+" ("+Types_Of_Audit+") for Customer["+Customer_No+"]  is set to NO, So Skipping this Case.");
				skip=true;
				throw new SkipException("Runmode of Case::" + (count + 1)+" ("+Types_Of_Audit+") for Customer["+Customer_No+"]  is set to NO, So Skipping this Case.");
			}else{
				skip=false;
				fnsApps_Report_Logs("****************************************************************************************************************************************");
				fnsApps_Report_Logs("################################## Execution Logs of Case::"+(count + 1)+" ("+Types_Of_Audit+") for Customer_No["+Customer_No+"].");
		
				
				try{
					CustomerName = fnsInsight_SerchCustomer_UserLinkClick(Customer_No, Insight_SearchUserName);
				}catch(Throwable t){
					driver.quit();
					IsBrowserPresentAlready = fnsLaunchBrowserAndLogin();
					throw new Exception("Insight Error : "+t.getMessage());
				}

				fncSwitchingTo_NsfOnline_from_Insight_AfterClickingOnUserName();
				
				// New Code 9.2.2016			due to jira - NOM-1503, New Code has been added.	
				fnsSwitchViewAccount_Functionality(Customer_No, CustomerName, Multi_Access.trim());	
			
				
				//Code to capture Screen shot of NSFOnline Application version
				try{
					if(NSFOnlineVersionScreenshotFlag){
						fncNsfOnline_Version_Screenshot("Audits_Customer_"+Customer_No);
						NSFOnlineVersionScreenshotFlag = false;
					}
				}catch(Throwable t){
					driver.close();
					fnsApps_Report_Logs("PASSED == Successfully Close NSFOnline Window.");
					driver.switchTo().window(MainWindowHandle);
					fnsApps_Report_Logs("PASSED == Successfully Switch to Insight Window.");
					throw new Exception(t.getMessage());
				}
			} 
						
			
			try{
				if( (AuditType.contains("Completed_Audits")) || (AuditType.contains("SCA_Client_Audit_Validation")) || (AuditType.contains("SCA_Supplier_Audit_Validation")) ){			
						fncAjax_Link_Click_By_Passing_TwoAjaxXPathx("NSFOnline_AuditsTopMenu", "NSFOnline_CompletedAuditsSubMenu");
						TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(2);
					}
					if(AuditType.contains("Scheduled_and_Upcoming_Audits")){	
						fncAjax_Link_Click_By_Passing_TwoAjaxXPathx("NSFOnline_AuditsTopMenu", "NSFOnline_ScheduledUpcomingSubMenu");
						TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(2);
					}
					if(AuditType.contains("SCA_Client_Standards")){	
						fncAjax_Link_Click_By_Passing_TwoAjaxXPathx("NSFOnline_Customer_Ajax_Link", "NSFOnline_Standards_Ajax_Link");
						Thread.sleep(3000);
						TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(2);
					}
					if(AuditType.contains("CorrectiveActions_CA_Report_Download")){	
						fncAjax_Link_Click_By_Passing_TwoAjaxXPathx("NSFOnline_CorrectiveActions_TopMenu", "NSFOnline_CorrectiveActions_SubMenu");
						Thread.sleep(3000);
						TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(2);
					}
					
					
					
					
					
					
					
				}catch(Throwable I){
					driver.close();
					fnsApps_Report_Logs("PASSED == Successfully Close NSFOnline Window.");
					driver.switchTo().window(MainWindowHandle);
					fnsApps_Report_Logs("PASSED == Successfully Switch to Insight Window.");
					throw new Exception(Throwables.getStackTraceAsString(I));	}
			//TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(5);
			fnsGet_Element_Enabled("Footer");
					
				
			//Verification of Completed_Audits and Scheduled_and_Upcoming_Audits 
			if( (AuditType.contains("Completed_Audits")) || (AuditType.contains("Scheduled_and_Upcoming_Audits")) ){	
				fnsGet_Element_Enabled("NSFOnline_ExpandSearchCriteriaLink");
				fnsWait_and_Click("NSFOnline_ExpandSearchCriteriaLink");
				TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(2);
				try{
					String NoOfSets[] = Search_AuditsNos_and_Matching_or_NotMatchingText.split(":");
					fnsApps_Report_Logs("Sets data are ["+Search_AuditsNos_and_Matching_or_NotMatchingText+"] for ("+AuditType+") Type. And No. of Sets are == " + NoOfSets.length);
			
					for (int j = 0; j < NoOfSets.length; j++) {
					
						String AuditSet = NoOfSets[j];
						fnsApps_Report_Logs("***************** Current searching ("+AuditType+") Set  is [" + AuditSet+"]  ******************");
				
						String CurrentSet[] = AuditSet.split(",");

						String AuditNo = fnsRemoveFormatting(CurrentSet[0]);
						String healthCondition = fnsRemoveFormatting(CurrentSet[1]).toLowerCase().trim();
						
						fnsGet_Element_Enabled("NSFOnline_AuditNoTxtBox");
						fnsWait_and_Type("NSFOnline_AuditNoTxtBox", AuditNo);
						
						
						
						fnsGet_Element_Enabled("NSFOnline_AuditSearchBttn_DivXpath");
						String SearchBttnText=fnsGet_Field_TEXT("NSFOnline_AuditSearchBttn_DivXpath").toLowerCase().trim();
						
						if (SearchBttnText.contains("audits")){
							fnsWait_and_Click("NSFOnline_CompletedAuditSearch_Bttn");
						}
						if (!(SearchBttnText.contains("audits"))){
							fnsWait_and_Click("NSFOnline_ScheduledAuditSearch_Bttn");
						}
												
						TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(3);
					
						if (SearchBttnText.contains("audits")){
							fnsMove_To_MouseOver("NSFOnline_CompletedAuditSearchResult_Table_2ndRow");
							String GetText=fnsGet_Field_TEXT("NSFOnline_CompletedAuditSearchResult_Table_2ndRow").trim();
							GetTextLength=GetText.length();
							fnsMove_To_MouseOver("NSFOnline_CompletedAuditSearchResult_Table_moseover");
							System.out.println("GetTextLength="+GetTextLength);
						}else{
							fnsMove_To_MouseOver("NSFOnline_ScheduledAuditsSearchResult_Table");
							String GetText=fnsGet_Field_TEXT("NSFOnline_ScheduledAuditsSearchResult_Table").trim();
							GetTextLength=GetText.length();
							fnsMove_To_MouseOver("NSFOnline_ScheduledAuditsSearchResult_Table_mouseover");
							System.out.println("ScheduledAuditsSearchResult GetTextLength="+GetTextLength);// for NoRecord length 32 and for other 218
						}
						
						
					
						if (healthCondition.equalsIgnoreCase("good")) {
							Thread.sleep(2000);
							assert (GetTextLength>50):"Audit No <"+AuditNo+"> for Data  [CustomerNo#"+Customer_No+" : UserName#"+Insight_SearchUserName+"]  is not coming 'GOOD'<i.e. Not appear into the records Table>.";
							fnsApps_Report_Logs("PASSED == Audit No <"+AuditNo+"> for Data  [CustomerNo#"+Customer_No+" : UserName#"+Insight_SearchUserName+"]  is coming 'GOOD'<i.e. Appear into the records Table>.");	}
						else {
							Thread.sleep(2000);
							assert (GetTextLength<50):"Audit No <"+AuditNo+"> for Data  [CustomerNo#"+Customer_No+" : UserName#"+Insight_SearchUserName+"]  is not coming 'BAD'<i.e. Appear into the records Table>.";	
							fnsApps_Report_Logs("PASSED == Audit No <"+AuditNo+"> for Data  [CustomerNo#"+Customer_No+" : UserName#"+Insight_SearchUserName+"]  is coming 'BAD'<i.e. Not appear into the records Table>.");	}
			
						
						
						
						if(j < ((NoOfSets.length)-1)){
							fnsWait_and_Click("ResestBtn");
							TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(2);
								if(driver.findElements(By.xpath(OR_NsfOnline.getProperty("NSFOnline_ExpandSearchCriteriaLink"))).size()>0){
									TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(2);
									fnsGet_Element_Enabled("NSFOnline_ExpandSearchCriteriaLink");
									fnsWait_and_Click("NSFOnline_ExpandSearchCriteriaLink");
								}
						}
						
					}	
				}catch(Throwable t){
					isTestCasePass = false;
					fnsTake_Screen_Shot("SearchAuditFail");
					driver.close();
					fnsApps_Report_Logs("PASSED == Successfully Close NSFOnline Window.");
					driver.switchTo().window(MainWindowHandle);
					fnsApps_Report_Logs("PASSED == Successfully Switch to Insight Window.");
					throw new Exception("FAILED == '"+Types_Of_Audit+"' SearchResult is not comming as ExpectedResult, Please refer Screenshot[SearchAuditFail"+fnsScreenShot_Date_format()+"]. Getting Exception >> "+Throwables.getStackTraceAsString(t)+".....");	}
	
			}
			
			
			//Verification of SCA_Client_Audit_Validation 
			if( (AuditType.contains("SCA_Client_Audit_Validation")) ){
				FncNSfOnline_Verify_SearchResult_ColumnValues("Audit Type", Search_AuditsNos_and_Matching_or_NotMatchingText);
			}
				
				
			//Verification of SCA_Supplier_Audit_Validation 
			if( (AuditType.contains("SCA_Supplier_Audit_Validation")) ){
				if( (Customer_No.toLowerCase()).equalsIgnoreCase("C0177201") ){
					FncNSfOnline_Verify_SearchResult_ColumnValues("Cus#", Search_AuditsNos_and_Matching_or_NotMatchingText);
				}else{
					FncNSfOnline_Verify_SearchResult_ColumnValues("Type/Std", Search_AuditsNos_and_Matching_or_NotMatchingText);
				}
			}
				
				
			//Verification of SCA_Client_Standards 
			if( (AuditType.contains("SCA_Client_Standards")) ){
				FncNSfOnline_Verify_SearchResult_ColumnValues("Standard Code", Search_AuditsNos_and_Matching_or_NotMatchingText);
			}
				
				
			//Verification Audit coming and CA report download. 
			if( (AuditType.contains("CorrectiveActions_CA_Report_Download")) ){	
				
				fnsGet_Element_Enabled("NSFOnline_ExpandSearchCriteriaLink");
				fnsWait_and_Click("NSFOnline_ExpandSearchCriteriaLink");
				TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(2);
				
				try{
					String AuditNo = Search_AuditsNos_and_Matching_or_NotMatchingText.split("=")[1].trim();
					
					fnsGet_Element_Enabled("NSFOnline_AuditNoTxtBox");
					fnsWait_and_Type("NSFOnline_AuditNoTxtBox", AuditNo);
					
					fnsGet_Element_Enabled("NSFOnline_CorrectiveActionSearch_Bttn");
					fnsWait_and_Click("NSFOnline_CorrectiveActionSearch_Bttn");
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(3);
					
					

					fnsMove_To_MouseOver("NSFOnline_CorrectiveActions_SearchResult_Table_1stRow");
					String TextFetch=fnsGet_Field_TEXT("NSFOnline_CorrectiveActions_SearchResult_Table_1stRow").trim().toLowerCase();
					
					try{
						assert TextFetch.contains(AuditNo.toLowerCase().trim()):"FAILED == Audit No <"+AuditNo+"> for Data  [CustomerNo#"+Customer_No+" : UserName#"+Insight_SearchUserName+"] is not coming into the search result, Please refer Screen shot >>"+AuditNo+fnsScreenShot_Date_format();
						fnsApps_Report_Logs("PASSED ==  Successfully Verify that Audit No <"+AuditNo+"> for Data  [CustomerNo#"+Customer_No+" : UserName#"+Insight_SearchUserName+"] is coming into the search result.");
					}catch(Throwable t){
						fnsTake_Screen_Shot(AuditNo);
						throw new Exception(t.getMessage());	
					}
					
					fncVerify_File_Download_Successfully("NSFOnline_CorrectiveActions_Audit_CA_Report_Link");
					
					/*fnsGet_Element_Displayed("NSFOnline_CorrectiveActions_Audit_CA_Report_Link");
					fnsWait_and_Click("NSFOnline_CorrectiveActions_Audit_CA_Report_Link");
					
					*/
					
					
					
					
				}catch(Throwable t){
					isTestCasePass = false;
					fnsTake_Screen_Shot("SearchAuditFail");
					driver.close();
					fnsApps_Report_Logs("PASSED == Successfully Close NSFOnline Window.");
					driver.switchTo().window(MainWindowHandle);
					fnsApps_Report_Logs("PASSED == Successfully Switch to Insight Window.");
					throw new Exception("FAILED == '"+Types_Of_Audit+"............"+Throwables.getStackTraceAsString(t)+".....");
				}
	
			}
					
					
	
			driver.close();
			fnsApps_Report_Logs("PASSED == Successfully Close NSFOnline Window.");
			driver.switchTo().window(MainWindowHandle);
			fnsApps_Report_Logs("PASSED == Successfully Switch to Insight Window.");
		
	}catch(SkipException sk){
			throw new SkipException(sk.getMessage());
			
	}catch(NoSuchWindowException W){
		fail = true;
		isTestCasePass = false;
		throw new Exception(W.getMessage());
		
	}catch(Throwable t){
		fail = true;
		isTestCasePass = false;
		fnsApps_Report_Logs("......................"+Throwables.getStackTraceAsString(t)+"......................");
		throw new Exception("......................"+Throwables.getStackTraceAsString(t)+"......................");}						
		
}

	

	
	
	
	

	
	
//######################################################### Class Function ######################################################################################	
	
	//Method has been changed on 6.1.2016 as it stuck in loop while verify text on page.	
	public void FncNSfOnline_Verify_SearchResult_ColumnValues( String ColumnName_TextNeedToVerify, String DP_Excel_Column_MatchingText)  throws Throwable{
		try{
			fnsGet_Element_Enabled("NSFOnline_Audit_SearchResult_TableHeader");
			
			String Text_On_Previous_Page_Row = null;
			String Text_On_Next_Page_Row = null;
			boolean first_Row_Text_Fetched_Flag = true;
				
			String Table_ColumnHeader_Row_Xpath = OR_NsfOnline.getProperty("NSFOnline_Audit_SearchResult_TableHeader")+"/tbody/tr[1]";
			ColumnNumber = TestSuiteBase_MonitorPlan.WithOut_OR_Retrun_TableColumnNo_of_MatchingColumnName(Table_ColumnHeader_Row_Xpath, ColumnName_TextNeedToVerify);
				
			for(int CoulmnNo_Start = 2; CoulmnNo_Start<=26; CoulmnNo_Start++){
			
				String Text_On_Page_Row_Xpath = OR_NsfOnline.getProperty("NSFOnline_Audit_SearchResult_TableHeader")+"/tbody/tr[2]";
				String AuditType_ColumnRow_Xpath = OR_NsfOnline.getProperty("NSFOnline_Audit_SearchResult_TableHeader")+"/tbody/tr["+CoulmnNo_Start+"]/td["+ColumnNumber+"]";
				
				if(driver.findElements(By.xpath(AuditType_ColumnRow_Xpath)).size()>0){
					if(first_Row_Text_Fetched_Flag){
						Text_On_Next_Page_Row = TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(Text_On_Page_Row_Xpath);
						first_Row_Text_Fetched_Flag = false;
					}
					
					if(Text_On_Next_Page_Row.equals(Text_On_Previous_Page_Row)){
						break;
					}else{
						String 	AuditTypeColumn_Text = TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(AuditType_ColumnRow_Xpath).toLowerCase().trim();
						String First4Letter_of_AuditTypeColumn = AuditTypeColumn_Text.substring(0, 4).toLowerCase();
					//	TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element_and_DoubleClick(AuditType_ColumnRow_Xpath);
						TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element(AuditType_ColumnRow_Xpath);
											
							
						String  Excel_Column_MatchingText_Split= DP_Excel_Column_MatchingText.split("=")[0].toLowerCase().trim();
						if(Excel_Column_MatchingText_Split.contains("start")){
								
							Split_Text = DP_Excel_Column_MatchingText.split("=")[1].trim();
							No_of_MatchingText_Set =  Split_Text.split("/");
								if(No_of_MatchingText_Set.length==1){
									Matching_Text1 = No_of_MatchingText_Set[0];
									try{
										assert ( First4Letter_of_AuditTypeColumn.equalsIgnoreCase( Matching_Text1.toLowerCase().trim()) ):"In RowNo<"+CoulmnNo_Start+">, the '"+ColumnName_TextNeedToVerify+"' Column contains the Text<"+AuditTypeColumn_Text.toUpperCase()+">, which is not start with '"+Matching_Text1+"'. Please refer screenshot >> Case"+CaseSerialNo.trim()+"_TextStartWith_Fail"+fnsScreenShot_Date_format();
									}catch(Throwable t){
										fnsTake_Screen_Shot("Case"+CaseSerialNo.trim()+"_TextStartWith_Fail");
										throw new Exception(t.getMessage());
									}
										
								}
								if(No_of_MatchingText_Set.length==2){
									Matching_Text1 = No_of_MatchingText_Set[0];
									Matching_Text2 = No_of_MatchingText_Set[1];
									try{
										assert ( First4Letter_of_AuditTypeColumn.equalsIgnoreCase( Matching_Text1.toLowerCase().trim()) || First4Letter_of_AuditTypeColumn.equalsIgnoreCase( Matching_Text2.toLowerCase().trim()) ):"In RowNo<"+CoulmnNo_Start+">, the '"+ColumnName_TextNeedToVerify+"' Column contains the Text<"+AuditTypeColumn_Text.toUpperCase()+">, which is not start with '"+Matching_Text1+"/"+Matching_Text2+"'. Please refer screenshot >> Case"+CaseSerialNo.trim()+"_TextStartWith_Fail"+fnsScreenShot_Date_format();
									}catch(Throwable t){
										fnsTake_Screen_Shot("Case"+CaseSerialNo.trim()+"_TextStartWith_Fail");
										throw new Exception(t.getMessage());
									}
								}
								
						}else{
							
							try{
								Split_Text = Matching_or_NotMatchingText.split("=")[1].trim();
								String NoOf_Matching_Text_Sets[] = Split_Text.split("/");
								Matching_Text_Found = false;
								for (int k = 0; k < NoOf_Matching_Text_Sets.length; k++) {
									Matching_Text_from_Excel = NoOf_Matching_Text_Sets[k].trim();
								
									if(Matching_Text_from_Excel.toLowerCase().equals(AuditTypeColumn_Text.toLowerCase().trim())){
										
										try{
											assert ( (Matching_Text_from_Excel.toLowerCase()).equalsIgnoreCase(AuditTypeColumn_Text.toLowerCase().trim()) ):
												"In RowNo<"+CoulmnNo_Start+">, the '"+ColumnName_TextNeedToVerify+"' Column contains the Text<"+AuditTypeColumn_Text.toUpperCase()+
												">, which is not matched with '"+Split_Text+"'. Please refer screenshot >> "+"Case"+CaseSerialNo.trim()+"_ColumnValueMatchFail"+fnsScreenShot_Date_format();
											fnsApps_Report_Logs("PASSED == Successfully matched column text <"+AuditTypeColumn_Text+"> with excel value <"+Split_Text+"> in row no "+CoulmnNo_Start+".");
											Matching_Text_Found = true;
											break;
										}catch(Throwable t){
											throw new Exception(t.getMessage());
										}
									}
								}
								if(Matching_Text_Found==false){
									throw new Exception ("In RowNo<"+CoulmnNo_Start+">, the '"+ColumnName_TextNeedToVerify+"' Column contains the Text<"+AuditTypeColumn_Text.toUpperCase()+
											">, which is not matched with '"+Split_Text+"'. Please refer screenshot >> "+"Case"+CaseSerialNo.trim()+"_ColumnValueMatchFail"+fnsScreenShot_Date_format());
								}
								}catch(Throwable t){
									fnsTake_Screen_Shot("Case"+CaseSerialNo.trim()+"_ColumnValueMatchFail");
									throw new Exception(Throwables.getStackTraceAsString(t));
								}
							
							
							
							
							
							
							
							
							
							
							
							
							
							/*// Not working
							Split_Text = DP_Excel_Column_MatchingText.split("=")[1].trim();
										
							try{
								assert ( (Split_Text.toLowerCase()).equalsIgnoreCase(AuditTypeColumn_Text.toLowerCase().trim()) ):
									"In RowNo<"+CoulmnNo_Start+">, the '"+ColumnName_TextNeedToVerify+"' Column contains the Text<"+AuditTypeColumn_Text.toUpperCase()+
									">, which is not matched with '"+Split_Text+"'. Please refer screenshot >> "+"Case"+CaseSerialNo.trim()+"_ColumnValueMatchFail"+fnsScreenShot_Date_format();
							}catch(Throwable t){
								fnsTake_Screen_Shot("Case"+CaseSerialNo.trim()+"_ColumnValueMatchFail");
								throw new Exception(t.getMessage());
							}*/
						}
					}		
				}else{
					break;
				}
				
				
				if(CoulmnNo_Start==26){
					AuditType_ColumnRow_Xpath = OR_NsfOnline.getProperty("NSFOnline_Audit_SearchResult_TableHeader")+"/tbody/tr["+(CoulmnNo_Start+1)+"]/td["+ColumnNumber+"]";
					if(driver.findElements(By.xpath(AuditType_ColumnRow_Xpath)).size()>0){
						throw new Exception("Pagination Failed == More than 25 records are displayed in search results on a single page");
					}else if(driver.findElements(By.xpath(OR_NsfOnline.getProperty("NSFOnline_Standards_SearchResult_NEXT_Link"))).size()>0){
						Text_On_Previous_Page_Row = Text_On_Next_Page_Row;
						fnsWait_and_Click("NSFOnline_Standards_SearchResult_NEXT_Link");
						TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(3);
						first_Row_Text_Fetched_Flag = true;
						CoulmnNo_Start =1;
					}
				}
			
			}
			Thread.sleep(2000);
			fnsApps_Report_Logs("PASSED == Case:"+CaseSerialNo.trim()+" >> Successfully verified all text values in column '"+ColumnName_TextNeedToVerify+"'.");
			
		}catch(Throwable t){
			driver.close();
			fnsApps_Report_Logs("PASSED == Successfully Close NSFOnline Window.");
			driver.switchTo().window(MainWindowHandle);
			fnsApps_Report_Logs("PASSED == Successfully Switch to Insight Window.");
			throw new Exception("FAILED == Case:"+CaseSerialNo.trim()+" >>> "+Throwables.getStackTraceAsString(t));
		}
	}
		
		
public void fncVerify_File_Download_Successfully(String FileDownload_ElementXpath) throws Throwable{
	try{
		String FileLocation = System.getProperty("user.home")+"\\Downloads";
		AllFileSize_BeforeFileSave = FileUtils.sizeOfDirectory(new File(FileLocation));
		
	
		fnsGet_Element_Displayed(FileDownload_ElementXpath);
		fnsWait_and_Click(FileDownload_ElementXpath);
		
	
		for(int waitloop=0; waitloop<12; waitloop++){
			try{	
				Actions action = new Actions(driver);
				action.keyDown(Keys.ALT);
				action.sendKeys("s");
				action.keyUp(Keys.ALT);
				action.build().perform();
				fnsApps_Report_Logs("PASSED == Successfully click on 'save' button of Browser dialog box.");
			    Thread.sleep(5000);
			}catch(Throwable t){
				fnsTake_Screen_Shot("BrowserFileSaveFail");
	    		throw new Exception("FAILED == "+t.getMessage()+", Please refer screenshot >> BrowserFileSaveFail"+fnsScreenShot_Date_format());		}
		
		
			try{
				AllFileSize_AfterFileSave = FileUtils.sizeOfDirectory(new File((FileLocation)));
				
				if(AllFileSize_BeforeFileSave<AllFileSize_AfterFileSave){
					Thread.sleep(1500);
					break;
				}
			}catch(IllegalArgumentException K){
				if(waitloop==6){
		    		fnsTake_Screen_Shot("FileDownloadFail");
		    		throw new Exception("FAILED == File is not getting download, after <"+waitloop+">attempts, Please refer screenshot >> FileDownloadFail"+fnsScreenShot_Date_format());
		    	}else{
		    		Thread.sleep(5000);
		    	}
			}
		}
		
		try{
			assert (AllFileSize_BeforeFileSave<AllFileSize_AfterFileSave):"FAILED == File is not getting download, after clicking on '"+FileDownload_ElementXpath+"', Please refer screenshot >> FileDownloadFail"+fnsScreenShot_Date_format();
			fnsApps_Report_Logs("PASSED == Successfully download file through Browser Dialog Popup, after clicking on '"+FileDownload_ElementXpath+"'.");	  
		}catch(Throwable t){
			fnsTake_Screen_Shot("FileDownloadFail");
			throw new Exception(t.getMessage());
		}
		      
	    try{	
			Actions action = new Actions(driver);
			action.keyDown(Keys.ALT);
			action.sendKeys("q");
			action.keyUp(Keys.ALT);
			action.build().perform();
	        Thread.sleep(1000);
	        fnsApps_Report_Logs("PASSED == Successfully close Browser dialog box.");
		}catch(Throwable t){
			fnsTake_Screen_Shot("BrowserPdfDialogCloseFail");
			throw new Exception("FAILED == "+t.getMessage()+", Please refer screenshot >> BrowserPdfDialogCloseFail"+fnsScreenShot_Date_format());
		}
	    
	} catch(Throwable t){
    	throw new Exception (Throwables.getStackTraceAsString(t));
    }
}
	
	
	
	
	
	
	
	
//################################################# Config Functions ################################################	
	@AfterMethod
	public void reportDataSetResult() {
		System.out.println("Print Count "+count);
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
		return TestUtil.getData(NSFOnline_suitexls, this.getClass().getSimpleName());
	}

	
	
}	
	
	
	
	
	
	
	
