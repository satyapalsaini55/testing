package nsf.ecap.NSFOnline_Suite;



import nsf.ecap.util.TestUtil;

import org.openqa.selenium.NoSuchWindowException;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;

public class NSFOnline_Search_Suppliers_Test extends TestSuiteBase_NSFOnline{
	
	public boolean fail = false;
	public boolean skip = true;
	public boolean NSFOnlineVersionScreenshotFlag = true;
	public boolean ViewIssueFlag = false;
	public String ScenaioName = null;
	public String Document_No = null;
	public Integer count = -1;
	

	
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
	fnsApps_Report_Logs("################################## [BS-02] NSFOnline Search Suppliers Test");
	if (!IsBrowserPresentAlready) {
		IsBrowserPresentAlready = fnsLaunchBrowserAndLogin();
		fnsApps_Report_Logs("Browser is launched and Successfully login into 'Insight' Application");	
	}
}
		
	
	
	
	
	
	
	@Test(dataProvider = "getTestData", priority = 1, dependsOnMethods={"Browser_Launch_and_Login_into_Insight_Application"})
	public void Insight_SearchCustomer_ThenOpen_NsfOnline_AndVerify_Suppliers_are_Displayed_as_Expected__(String Serial_No, String Customer_Number,String Insight_Search_User_Name,String Search_SupplierIDs, String Document_Verification, String Multi_Access) throws Exception{
		
		count++;
		Customer_No = Customer_Number.split("=")[1].trim();
		Insight_SearchUserName = Insight_Search_User_Name.split("=")[1].trim();
		CaseSerialNo = Serial_No.split("=")[1];
		
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
				
				
				
				
				
				try{
					if(NSFOnlineVersionScreenshotFlag){
						fncNsfOnline_Version_Screenshot("Suppliers_Customer_"+Customer_No);
						NSFOnlineVersionScreenshotFlag = false;
					}
				}catch(Throwable t){
					driver.close();
					fnsApps_Report_Logs("PASSED == Successfully Close NSFOnline Window.");
					driver.switchTo().window(MainWindowHandle);
					fnsApps_Report_Logs("PASSED == Successfully Switch to Insight Window.");
					throw new Exception(t.getMessage());	}
			}
			
			
			//Verify for Good Bad
			try{
				String NoOfSets[] = Search_SupplierIDs.split(":");
				fnsApps_Report_Logs("Sets data are ["+Search_SupplierIDs+"]. and No. of "+" Ids are == " + NoOfSets.length);
		
				for (int j = 0; j < NoOfSets.length; j++) {
					ViewIssueFlag = false;
					
					String Search_SupplierIDsSet = NoOfSets[j];
					String CurrentSet[] = Search_SupplierIDsSet.split(",");
					String Search_SupplierID = fnsRemoveFormatting(CurrentSet[0]);
					String GoodBadCondition = fnsRemoveFormatting(CurrentSet[1]).toLowerCase().trim();
					
					fnsApps_Report_Logs("****************************************************************************************************");
					fnsApps_Report_Logs("************************ Current searching "+" Id is [" + Search_SupplierIDsSet+"]  *************************");
					
					fncAjax_Link_Click_By_Passing_TwoAjaxXPathx("NSFOnline_CustomerTopMenu", "NSFOnline_Customer_Suppliers");
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(5);
					fnsGet_Element_Enabled("NSFOnline_ExpandSearchCriteriaLink");
					fnsWait_and_Click("NSFOnline_ExpandSearchCriteriaLink");	
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(2);
											
					fnsGet_Element_Enabled("NSFOnline_MySuppliers_CustomerSearchCriteria_CustomerText");
					fnsWait_and_Type("NSFOnline_MySuppliers_CustomerSearchCriteria_CustomerText", Search_SupplierID);
											
					fnsGet_Element_Enabled("NSFOnline_MySuppliers_CustomerSearchCriteria_SearchBttn");
					fnsWait_and_Click("NSFOnline_MySuppliers_CustomerSearchCriteria_SearchBttn");
					TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(4);
					
					fnsGet_Element_Enabled("NSFOnline_IssueEscalation_SearchResultTable");
					TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element_BY_OFFSET(OR_NsfOnline.getProperty("NSFOnline_IssueEscalation_SearchResultTable"), 30, 10);
					String SearchResult_GetText=fnsGet_Field_TEXT("NSFOnline_IssueEscalation_SearchResultTable").toLowerCase().trim();
						
										
					
					try{
						if (GoodBadCondition.equalsIgnoreCase("good")) {
							Thread.sleep(500);
							assert (SearchResult_GetText.contains(Search_SupplierID.toLowerCase())):"FAILED == Supplier id <"+Search_SupplierID+"> for Data  [CustomerNo#"+Customer_No+" : UserName#"+Insight_SearchUserName+"]  is not coming 'GOOD'<i.e. SearchId is not appeared into the records Table>, ,Please refer Screenshot["+GoodBadCondition+"_Fail"+fnsScreenShot_Date_format();
							fnsApps_Report_Logs("PASSED == Supplier id <"+Search_SupplierID+"> for Data  [CustomerNo#"+Customer_No+" : UserName#"+Insight_SearchUserName+"]  is coming 'GOOD'<i.e. SearchID is appeared into the records Table>.");
							ViewIssueFlag = true;
						}else {
							Thread.sleep(2000);
							assert (SearchResult_GetText.contains("no items were found")):"FAILED == Supplier id <"+Search_SupplierID+"> for Data  [CustomerNo#"+Customer_No+" : UserName#"+Insight_SearchUserName+"]  is not coming 'BAD'<i.e. SearchID is appeared into the records Table>,,Please refer Screenshot["+GoodBadCondition+"_Fail"+fnsScreenShot_Date_format();	
							fnsApps_Report_Logs("PASSED == Supplier id <"+Search_SupplierID+"> for Data  [CustomerNo#"+Customer_No+" : UserName#"+Insight_SearchUserName+"]  is coming 'BAD'<i.e. SearchID is not appeared into the records Table>.");	
						}
					}catch(Throwable t){
						fnsTake_Screen_Shot(GoodBadCondition+"_Fail");
						throw new Exception(t.getMessage());	}
					
					
					
					if(Customer_No.equalsIgnoreCase("C0148241")){
						Document_No = Document_Verification.split(":")[j].trim();
						
						String SupplierId_Link_Xpath = "//a[contains(text(), '"+Search_SupplierID+"')]";
						TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(SupplierId_Link_Xpath);
						TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(4);
						fnsGet_Element_Enabled("Footer");
								
						fnsGet_Element_Enabled("NSFOnline_ViewSuppliers_Documents");
						fnsWait_and_Click("NSFOnline_ViewSuppliers_Documents");
						TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(2);
															
						String DocumentTable_Text = fnsGet_Field_TEXT("NSFOnline_ViewSuppliers_Documents_Table").toLowerCase().trim();
						TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element_BY_OFFSET( OR_NsfOnline.getProperty("NSFOnline_ViewSuppliers_Documents_Table"), 10, 20);
						
						if(Document_No.equalsIgnoreCase("NA")){
							try{
								Thread.sleep(2000);
								assert (DocumentTable_Text.contains("no records to display")):"FAILED == 'No Record To Display' is not coming into the Document TAB, Please refer Screenshot>>"+"NoRecordsToDisplay_FAIL_"+fnsScreenShot_Date_format();
								fnsApps_Report_Logs("PASSED == Successfully Verified that 'No Record To Display' is coming in Document TAB.");
							}catch(Throwable t){
								fnsTake_Screen_Shot("NoRecordsToDisplay_FAIL_");
								throw new Exception(t.getMessage());	}
						}else{
							try{
								Thread.sleep(2000);
								assert (DocumentTable_Text.contains(Document_No.toLowerCase())):"FAILED == Document<"+Document_No+">is not displayed into the Document TAB, Please refer Screenshot>>"+Document_No+"_DisplayFAIL_"+fnsScreenShot_Date_format();
								fnsApps_Report_Logs("PASSED == Successfully Verified that Document<"+Document_No+"> is displayed into the Document TAB.");
							}catch(Throwable t){
								fnsTake_Screen_Shot(Document_No+"_DisplayFAIL_");
								throw new Exception(t.getMessage());	}
						}
					}
				}
				
			}catch(Throwable t){
				isTestCasePass = false;
				driver.close();
				fnsApps_Report_Logs("PASSED == Successfully Close NSFOnline Window.");
				driver.switchTo().window(MainWindowHandle);
				fnsApps_Report_Logs("PASSED == Successfully Switch to Insight Window.");
				throw new Exception(Throwables.getStackTraceAsString(t)+"......................");	}

		
	
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
		fnsApps_Report_Logs("Case:"+CaseSerialNo.trim()+"......................"+Throwables.getStackTraceAsString(t)+"......................");
		throw new Exception("Case:"+CaseSerialNo.trim()+"......................"+Throwables.getStackTraceAsString(t)+"......................");}						
		
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
	
	
	
	
	
	
	
