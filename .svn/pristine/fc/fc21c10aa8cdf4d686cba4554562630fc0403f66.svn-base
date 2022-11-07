package nsf.ecap.NSFOnline_Suite;


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

public class NSFOnline_Search_Documents_Test extends TestSuiteBase_NSFOnline{
	
	public boolean NSFOnlineVersionScreenshotFlag = true;
	public boolean fail = false;
	public boolean skip = true;
	public boolean customer_C0196359 = false;
	public boolean customer_C0180508 = false;
	public int count = -1;
	

	
	/*public String Errormsg;
	public String CustomerName;
	public String TextFetch = null;
	public String CaseSerialNo = null;
	public Integer RowCount;
	public String CustomerName_Sorting = null;
	public String Customer_No = null;
	public String Insight_SearchUserName = null;
	public String NsfOnline_Documents_SubMenu = null;*/
	
		
	
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
	fnsApps_Report_Logs("################################## [BS-01] NSFOnline Search Documents Test");
	if (!IsBrowserPresentAlready) {
		IsBrowserPresentAlready = fnsLaunchBrowserAndLogin();
		fnsApps_Report_Logs("Browser is launched and Successfully login into 'Insight' Application");	
	}
}
		
	
	
	
	
	
	
	@Test(dataProvider = "getTestData", priority = 1, dependsOnMethods={"Browser_Launch_and_Login_into_Insight_Application"})
	public void Insight_SearchCustomer_ThenOpen_NsfOnline_AndVerify_Documents_are_Displayed_as_Expected__(String Serial_No, String Customer_Number,String Insight_Search_User_Name,String NsfOnline_CustomerMenu_SubMenu_Name,String Search_DocumentsNos, String Sort_CustomerName, String Multi_Access) throws Exception{
		
		count++;
		Customer_No = Customer_Number.split("=")[1].trim();
		Insight_SearchUserName = Insight_Search_User_Name.split("=")[1].trim();
		CustomerName_Sorting=Sort_CustomerName.split("=")[1].trim();
		CaseSerialNo = Serial_No.split("=")[1];
		NsfOnline_Documents_SubMenu = NsfOnline_CustomerMenu_SubMenu_Name.trim();
		
		try {
			
			if (!(runmodes[count].equalsIgnoreCase("Y"))) {
				fnsApps_Report_Logs("****************************************************************************************************************************************");
				fnsApps_Report_Logs("################################## Runmode of Case::" + (count + 1)+" for Customer["+Customer_No+"]  is set to NO, So Skipping this Case.");
				skip=true;
				throw new SkipException("Runmode of Case::" + (count + 1)+" for Customer["+Customer_No+"]  is set to NO, So Skipping this Case.");
			}else{
				skip=false;
				fnsApps_Report_Logs("****************************************************************************************************************************************");
				fnsApps_Report_Logs("################################## Execution Logs of Case::"+(count + 1)+" for Customer_No["+Customer_No+"].");
				
				
				
				
				
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
						fncNsfOnline_Version_Screenshot("Documents_Customer_"+Customer_No);
						NSFOnlineVersionScreenshotFlag = false;
					}
				}catch(Throwable t){
					driver.close();
					fnsApps_Report_Logs("PASSED == Successfully Close NSFOnline Window.");
					driver.switchTo().window(MainWindowHandle);
					fnsApps_Report_Logs("PASSED == Successfully Switch to Insight Window.");
					throw new Exception(t.getMessage());	}
			}
						
			
			try{
				
				if(NsfOnline_Documents_SubMenu.toLowerCase().equalsIgnoreCase("documents")){
					fnsGet_Element_Enabled("NSFOnline_CustomerTopMenu");
					fnsMove_To_MouseOver("NSFOnline_CustomerTopMenu");
				}
				Thread.sleep(250);
				
				if(NsfOnline_Documents_SubMenu.toLowerCase().equalsIgnoreCase("documents")){
					
					fnsMove_To_MouseOver("NSFOnline_DocumentsSubMenu");
					Thread.sleep(250);
					fnsWait_and_Click("NSFOnline_DocumentsSubMenu");				
				}
				TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(2);
				
				
				/*fnsGet_Element_Enabled("NSFOnline_ExpandSearchCriteriaLink");
				fnsWait_and_Click("NSFOnline_ExpandSearchCriteriaLink");
				TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(2);*/
				boolean ClickDone_ExpandSearchLink = false;
				for (int i=1; i<=10; i++){
					try{
						if(driver.findElements(By.xpath(OR_NsfOnline.getProperty("NSFOnline_ExpandSearchCriteriaLink"))).size()>0){
							/*driver.findElement(By.xpath(OR_NsfOnline.getProperty("NSFOnline_ExpandSearchCriteriaLink"))).click();*/
							/*TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element_and_Click(OR_NsfOnline.getProperty("NSFOnline_ExpandSearchCriteriaLink"));*/
							TestSuiteBase_New_NSFOnline.fnsWait_and_Click_Through_JS(OR_NsfOnline.getProperty("NSFOnline_ExpandSearchCriteriaLink"));
							fnsApps_Report_Logs("PASSED == Click done on 'Expand Search Criteria' link.");
							TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(2);
							ClickDone_ExpandSearchLink = true;
						}
						if( (driver.findElements(By.xpath(OR_NsfOnline.getProperty("NSFOnline_ExpandSearchCriteriaLink"))).size()==0) && ClickDone_ExpandSearchLink==true){
							break;
						}else{
							Thread.sleep(3000);
						}
					}catch(Throwable t){
						if(i==10){
							throw new Exception("FAILED == clicking on 'Expand Search Criteria' link is getting fail after 10 attampts, please refer the screenshot");
						}else{
							Thread.sleep(3000);
						}
					}
					
				}
		
			
			}catch(Throwable I){
				driver.close();
				fnsApps_Report_Logs("PASSED == Successfully Close NSFOnline Window.");
				driver.switchTo().window(MainWindowHandle);
				fnsApps_Report_Logs("PASSED == Successfully Switch to Insight Window.");
				throw new Exception(I.getMessage());	}
			
		
	
	
			try{
				String NoOfSets[] = Search_DocumentsNos.split(":");
				fnsApps_Report_Logs("Sets data are ["+Search_DocumentsNos+"]. and No. of DocumentSets are == " + NoOfSets.length);
		
				for (int j = 0; j < NoOfSets.length; j++) {
			
					String DocumentSet = NoOfSets[j];
					fnsApps_Report_Logs("***************** Current searching DocumentSet  is [" + DocumentSet+"]  ******************");
			
					String CurrentSet[] = DocumentSet.split(",");

					String DocumentNo = fnsRemoveFormatting(CurrentSet[0]);
					String healthCondition = fnsRemoveFormatting(CurrentSet[1]).toLowerCase().trim();
								
					fnsWait_and_Type("NSFOnline_DocumentNoTxtBox", DocumentNo);
					
					fnsWait_and_Click("SearchBtn");
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(2);
					Thread.sleep(4000);
					
					fnsMove_To_MouseOver("NSFOnline_ViewDocuments_DocumentsSearchResult_Table");
					TextFetch=fnsGet_Field_TEXT("NSFOnline_ViewDocuments_DocumentsSearchResult_Table").trim().toLowerCase();
					
				
					if (healthCondition.equalsIgnoreCase("good")) {
						try{
							assert TextFetch.contains(DocumentNo.toLowerCase().trim()):"FAILED == Documents No <"+DocumentNo+"> for Data  [CustomerNo#"+Customer_No+" : UserName#"+Insight_SearchUserName+"]  is not coming 'GOOD'<i.e. Not appear into the records Table>, Please refer Screen shot >>"+DocumentNo+"_GoodFail"+fnsScreenShot_Date_format();
							fnsApps_Report_Logs("PASSED ==  Successfully Verify Documents No <"+DocumentNo+"> for Data  [CustomerNo#"+Customer_No+" : UserName#"+Insight_SearchUserName+"]  is coming 'GOOD'.");
						}catch(Throwable t){
							fnsTake_Screen_Shot(DocumentNo+"_GoodFail");
							throw new Exception(t.getMessage());	}
						Thread.sleep(2000);
					}else {
						try{
							assert TextFetch.contains("no records to display"):"FAILED == Documents No <"+DocumentNo+"> for Data  [CustomerNo#"+Customer_No+" : UserName#"+Insight_SearchUserName+"]  is not coming 'BAD'<i.e. Appear into the records Table>, Please refer Screen shot >>"+DocumentNo+"_BadFail"+fnsScreenShot_Date_format();
							fnsApps_Report_Logs("PASSED ==  Successfully Verify Documents No <"+DocumentNo+"> for Data  [CustomerNo#"+Customer_No+" : UserName#"+Insight_SearchUserName+"]  is coming 'BAD'.");
						}catch(Throwable t){
							fnsTake_Screen_Shot(DocumentNo+"_BadFail");
							throw new Exception(t.getMessage());	}
						Thread.sleep(2000);		
					}
					
		
					//if(j < ((NoOfSets.length)-1)){
						fnsWait_and_Click("ResestBtn");
						TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(2);
						Thread.sleep(4000);	
					//}
				
				}		
			}catch(Throwable t){
			//	fnsTake_Screen_Shot("SearchDocumentFail");
				driver.close();
				fnsApps_Report_Logs("PASSED == Successfully Close NSFOnline Window.");
				driver.switchTo().window(MainWindowHandle);
				fnsApps_Report_Logs("PASSED == Successfully Switch to Insight Window.");
				throw new Exception(Throwables.getStackTraceAsString(t)+"......................");	}
			
		
		
			/*try{
				//Client name verification in search result table.
				if(CustomerName_Sorting.equalsIgnoreCase("Yes")){

					
					//Method has been changed on 6.1.2016 as it stuck in loop while verify text on page.	
										fnsGet_Element_Enabled("NSFOnline_Documents_SearchResult_TableHeader");
										
										String Text_On_Previous_Page_Row = null;
										String Text_On_Next_Page_Row = null;
										boolean first_Row_Text_Fetched_Flag = true;
											
										String Table_ColumnHeader_Row_Xpath = OR_NsfOnline.getProperty("NSFOnline_Documents_SearchResult_TableHeader")+"/tbody/tr[1]";
										Integer ColumnNumber = TestSuiteBase_MonitorPlan.WithOut_OR_Retrun_TableColumnNo_of_MatchingColumnName(Table_ColumnHeader_Row_Xpath, "Client Name");
											
										for(int CoulmnNo_Start = 2; CoulmnNo_Start<=26; CoulmnNo_Start++){
										
											String Text_On_Page_Row_Xpath = OR_NsfOnline.getProperty("NSFOnline_Documents_SearchResult_TableHeader")+"/tbody/tr[2]";
											String DocumentType_ColumnRow_Xpath = OR_NsfOnline.getProperty("NSFOnline_Documents_SearchResult_TableHeader")+"/tbody/tr["+CoulmnNo_Start+"]/td["+ColumnNumber+"]";
											
											if(driver.findElements(By.xpath(DocumentType_ColumnRow_Xpath)).size()>0){
												
												if(first_Row_Text_Fetched_Flag){
													Text_On_Next_Page_Row = TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(Text_On_Page_Row_Xpath);
													first_Row_Text_Fetched_Flag = false;
												}
												
												if(Text_On_Next_Page_Row.equals(Text_On_Previous_Page_Row)){
													break;
												}else{
													String 	DocumentTypeColumn_Text = TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(DocumentType_ColumnRow_Xpath).toLowerCase().trim();
													String First4Letter_of_DocumentTypeColumn = DocumentTypeColumn_Text.substring(0, 4).toLowerCase();
													TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element_and_DoubleClick(DocumentType_ColumnRow_Xpath);
																		
													try{
														TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element_and_DoubleClick(DocumentType_ColumnRow_Xpath);
														assert (DocumentTypeColumn_Text.contains(CustomerName.toLowerCase())):"FAILED == Insight Expected CustomerName("+CustomerName+") is not Matched with NsfOnline ActualClientName("+DocumentTypeColumn_Text+"), Please refer screen shot ["+ CustomerName + fnsScreenShot_Date_format() + "]";
														fnsApps_Report_Logs("PASSED == Insight Expected CustomerName("+CustomerName+") is Matched with NsfOnline ActualClientName("+DocumentTypeColumn_Text+")");
													}catch(Throwable t) {
														fnsTake_Screen_Shot(CustomerName);
														fnsApps_Report_Logs(t.getMessage());
														throw new Exception(t.getMessage());			
													}
												}		
											}else{
												break;
											}
											
											
											if(CoulmnNo_Start==26){
												DocumentType_ColumnRow_Xpath = OR_NsfOnline.getProperty("NSFOnline_Documents_SearchResult_TableHeader")+"/tbody/tr["+(CoulmnNo_Start+1)+"]/td["+ColumnNumber+"]";
												if(driver.findElements(By.xpath(DocumentType_ColumnRow_Xpath)).size()>0){
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
										fnsApps_Report_Logs("PASSED == Case:"+CaseSerialNo.trim()+" >> Successfully verified all text values in column 'Client Name'.");
										
										
										
										
										Integer SearchTableTotalRowCount=fnsReturn_TotalRowCount("NSFOnline_Documents_SearchResult_TableHeader");
										Integer ColumnNameNo=fnsReturn_ColumnNo_By_MatchingColumnNameText("NSFOnline_Documents_SearchResult_TableHeader_1stRow", "Client Name", "th");
												
										while(true){
											fnsReturn_TotalRowCount("NSFOnline_Documents_SearchResult_TableHeader");
											int ColumnVerificationLoopStart=1;
											fnsGet_Element_Enabled("NSFOnline_Documents_SearchResult_TableHeader");
											WebElement stdtable=fnsGet_OR_NsfOnline_ObjectX("NSFOnline_Documents_SearchResult_TableHeader");
											List<WebElement> AllRowsObj = stdtable.findElements(By.tagName("tr"));
											if(AllRowsObj.size()<26){
												ColumnVerificationLoopStart=0;
											}
											//int satyatest=0;
											for(WebElement countrows:AllRowsObj){
												
												
											//	fnsApps_Report_Logs("Satya ColumnVerificationLoopStart++="+ColumnVerificationLoopStart);
											//	fnsApps_Report_Logs("satyatest="+satyatest);
												if(ColumnVerificationLoopStart>1){
													
													String ColumnTextXpath=".//*[@class='x7a']/tbody/tr["+ColumnVerificationLoopStart+"]/td["+ColumnNameNo+"]";
													//System.out.println("ColumnTextXpath="+ColumnTextXpath);
													String ColumnText=TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(ColumnTextXpath).toLowerCase().trim();
													//System.out.println("ColumnText="+ColumnText);
													
													try{
														TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element_and_DoubleClick(ColumnTextXpath);
														assert (ColumnText.contains(CustomerName)):"FAILED == Insight Expected CustomerName("+CustomerName+") is not Matched with NsfOnline ActualClientName("+ColumnText+"), Please refer screen shot ["+ CustomerName + fnsScreenShot_Date_format() + "]";
														fnsApps_Report_Logs("PASSED == Insight Expected CustomerName("+CustomerName+") is Matched with NsfOnline ActualClientName("+ColumnText+")");
													}catch(Throwable t) {
														fnsTake_Screen_Shot(CustomerName);
														fnsApps_Report_Logs(t.getMessage());
														throw new Exception(t.getMessage());			}
												}
											//	satyatest++;
												
												ColumnVerificationLoopStart++;	
												
											}
											
											if(ColumnVerificationLoopStart==27){
												if(driver.findElements(By.xpath(OR_NsfOnline.getProperty("NSFOnline_Documents_SearchResult_Pagination_Next"))).size()>0){
													fnsGet_Element_Enabled("NSFOnline_Documents_SearchResult_Pagination_Next");
													fnsWait_and_Click("NSFOnline_Documents_SearchResult_Pagination_Next");	
													Thread.sleep(7000);		} 	
												else{
													Thread.sleep(1500);
													break;}
												
											}else{
												Thread.sleep(1500);
												break;}
											
										} //while loop end
										
										
										
										
										
										
										
										
									
				} 
			}catch(Throwable t) {
				driver.close();
				fnsApps_Report_Logs("PASSED == Successfully Close NSFOnline Window.");
				driver.switchTo().window(MainWindowHandle);
				fnsApps_Report_Logs("PASSED == Successfully Switch to Insight Window.");
				fnsApps_Report_Logs("Sorting: "+Throwables.getStackTraceAsString(t)+"......................");
				throw new Exception("Sorting: "+Throwables.getStackTraceAsString(t)+"......................");			}*/
			
			fnc_Verify_NsfOnline_CustomerName_ComingSameAsIn_Insight();
				
					
			driver.close();
			fnsApps_Report_Logs("PASSED == Successfully Close NSFOnline Window.");
			driver.switchTo().window(MainWindowHandle);
			fnsApps_Report_Logs("PASSED == Successfully Switch to Insight Window.");


		}catch(SkipException sk){
			throw new SkipException("Runmode of Case::" + (count + 1)+" for Customer["+Customer_No+"]  is set to NO, So Skipping this Case.");
			
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
		return TestUtil.getData(NSFOnline_suitexls, this.getClass().getSimpleName());
	}

	
	
}	
	
	
	
	
	
	
	
