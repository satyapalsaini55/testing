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

public class NSFOnline_Search_GlobalDocument extends TestSuiteBase_NSFOnline{
	
	public boolean NSFOnlineVersionScreenshotFlag = true;
	public boolean fail = false;
	public boolean skip = true;
	public boolean customer_C0196359 = false;
	public boolean customer_C0180508 = false;
	public int count = -1;
	
	
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
	fnsApps_Report_Logs("################################## [BS-06] NSFOnline Search Documents Test");
	if (!IsBrowserPresentAlready) {
		IsBrowserPresentAlready = fnsLaunchBrowserAndLogin();
		fnsApps_Report_Logs("Browser is launched and Successfully login into 'Insight' Application");	
	}
}
		
	
	
	
	
	
	
	@Test(dataProvider = "getTestData", priority = 1, dependsOnMethods={"Browser_Launch_and_Login_into_Insight_Application"})
	public void Insight_SearchCustomer_ThenOpen_NsfOnline_AndVerify_Documents_are_Displayed_as_Expected__(String Serial_No, String Customer_Number,String Insight_Search_User_Name,String NsfOnline_DocumentsMenu_SubMenu_Name,String Search_DocumentsNos, String Sort_CustomerName, String Multi_Access) throws Exception{
		
		count++;
		Customer_No = Customer_Number.split("=")[1].trim();
		Insight_SearchUserName = Insight_Search_User_Name.split("=")[1].trim();
		CustomerName_Sorting=Sort_CustomerName.split("=")[1].trim();
		CaseSerialNo = Serial_No.split("=")[1];
		NsfOnline_Documents_SubMenu = NsfOnline_DocumentsMenu_SubMenu_Name.trim();
		
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
						fncNsfOnline_Version_Screenshot("Global_Documents_Customer_"+Customer_No);
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
				fnsGet_Element_Enabled("NSFOnline_DocumentsTopMenu");
				fnsMove_To_MouseOver("NSFOnline_DocumentsTopMenu");
				Thread.sleep(250);
				
				
				if(NsfOnline_Documents_SubMenu.toLowerCase().equalsIgnoreCase("supplier_documents")){
					fnsMove_To_MouseOver("NSFOnline_Documents_SupplierDocumentsSubMenu");
					Thread.sleep(250);
					fnsWait_and_Click("NSFOnline_Documents_SupplierDocumentsSubMenu");
				}
				
				if(NsfOnline_Documents_SubMenu.toLowerCase().equalsIgnoreCase("facilities_documents")){
					fnsMove_To_MouseOver("NSFOnline_Documents_FacilitiesDocumentsSubMenu");
					Thread.sleep(250);
					fnsWait_and_Click("NSFOnline_Documents_FacilitiesDocumentsSubMenu");
				}
				TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(2);
				
				
				fnsGet_Element_Enabled("NSFOnline_ExpandSearchCriteriaLink");
				fnsWait_and_Click("NSFOnline_ExpandSearchCriteriaLink");
				TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(2);
		
			
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
	
	
	
	
	
	
	
