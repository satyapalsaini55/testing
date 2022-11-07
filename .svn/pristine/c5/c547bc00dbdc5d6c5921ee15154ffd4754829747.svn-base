package nsf.ecap.Invoice_Function_suite;



import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import nsf.ecap.Health_Science_suite.TestSuiteBase_HealthScience;
import nsf.ecap.Test_Plan_suite.TestSuiteBase_Test_Plan_suite;
import nsf.ecap.Work_Order_suite.NewNew_Draft_InReview_No_Fac;
import nsf.ecap.util.TestDataProvider;
import nsf.ecap.util.TestUtil;

import static nsf.ecap.config.IPULSE_CONSTANTS.*;

public class Exp_Allocation  extends TestSuiteBase_Invoice_Function_suite{
	
	
	
	
	
	@BeforeTest
	public void checkTestSkip() throws Throwable {
		 count = -1;
		BS01 = new NewNew_Draft_InReview_No_Fac();
		BS01.checkTestSkip(this.getClass().getSimpleName());
		
		runmodes = TestUtil.getDataSetRunmodes(currentSuiteXls, this.getClass().getSimpleName());
		fail = false;
		isTestPass = true;
		
	
		start = new Date();
	}
	
	
	
	
	//@Test(enabled = false)
	//@Test(priority = 1)
	//public void Create_InvoiceFlow() throws Throwable {
	@Test(dataProviderClass = TestDataProvider.class,dataProvider = "Exp_AllocationDataProvider")
	public void Exp_AllocationFlow(
								Hashtable<String, String> table) throws Throwable {
		
		
		
		fnpMymsg("****************************************************************");
		count++;
		if (!runmodes[count].equalsIgnoreCase("Y")) {
			skip = true;
			fnpMymsg("Runmode for test set data set to no " + (count + 1));
			fnpMymsg("*****************");
			fnpMymsg("");
			throw new SkipException("Runmode for test set data set to no " + (count + 1));
		}else{
			skip = false;
		}
		
		
		
		int iwhileCount=1;
		int maxTry=10;//3;
		while(iwhileCount<=maxTry){
			
		
				try{
					
					if (iwhileCount==1) {
						fnpLaunchBrowserAndLogin();
					}else{
						fnpClickTopHomeMenu();
					}
						
						fnpMymsg("*********************trying "+iwhileCount+" time**********");
						
						fnpClick_OR("Alert_For_OrgUnit_label");
		
						fnpPFList("Alert_For_SelectOrgUnit_List", "Alert_For_SelectOrgUnit_ListOptions", (String) table.get("Org_Name"));
						fnpClick_OR("ShowAlertsBtn");
						
						
						//Hashtable<String, String> ht1 = new Hashtable<String,String>();
						//ht1.putAll(hashXlData);
						//TestSuiteBase_HealthScience.fnpCommonAlertClickedFirstRecord_in_specifiedColumnName_withoutSearching_BasedOnAlertNameContains(table, (String) table.get("Alert_Name"),  "Visit No");
						TestSuiteBase_HealthScience.fnpCommonAlertClickedRecord_in_specifiedRow_specifiedColumnName_withoutSearching_BasedOnAlertNameContains(table, (String) table.get("Alert_Name"), iwhileCount, "Visit No");
						
						
						
						
						//fnpLoading_wait();
						
						
		/*				
						int totalRowCount=fnpCountRowsInTable("AuditsTable");
						if (totalRowCount==0) {
							msg="Audits table is coming blank";
							fnpMymsg(msg);
							throw new Exception(msg);
						} 
						
						String linkToBeClicked = null;
						//int columnIndex;
						int audit_columnIndex = fnpFindColumnIndex("AuditsTableHeader", "Audit");
						
						int columnIndex_AuditType;
						if (totalRowCount==1) {
							linkToBeClicked=fnpFetchFromTable("AuditsTable", 1, audit_columnIndex);
							fnpClickALinkInATable(linkToBeClicked);
						} else {
							
							String auditType;
							String multipleAuditsValue=(String)(String) table.get("Alert_Name");
							String[] multipleAuditsValue_Array = multipleAuditsValue.split("::");
							boolean foundMatchedAudit=false;
							for (int i = 1; i < totalRowCount; i++) {
								columnIndex_AuditType=fnpFindColumnIndex("AuditsTableHeader", "Audit Type");
								auditType=fnpFetchFromTable("AuditsTable", 1, columnIndex_AuditType);
								if (linkToBeClicked.equalsIgnoreCase("173GMP") | linkToBeClicked.equalsIgnoreCase("229GMP")) {
									
								}
								
								
								for (int j = 0; j < multipleAuditsValue_Array.length; j++) {
									if (auditType.equalsIgnoreCase(multipleAuditsValue_Array[j])) {
										foundMatchedAudit=true;
										linkToBeClicked=fnpFetchFromTable("AuditsTable", i, audit_columnIndex);
										break;
									}
									
								}
								
							}
							
							if (foundMatchedAudit) {
								fnpMymsg("Going to click this Audit --"+linkToBeClicked);
								fnpClickALinkInATable(linkToBeClicked);
							} else {
								msg="Audits table has multiple audits but these are not matched to our audits mentioned in excel i.e. --"+multipleAuditsValue.replaceAll("::", ",");
								fnpMymsg(msg);
								throw new Exception(msg);
							}
							
		
						}
						
						
						//
						
						
						String taskNo=fnpGetText_OR("TaskNo_ViewAuditScreen");
						fnpMymsg("Going to click this task no.--"+taskNo);
						fnpClick_OR("TaskNo_ViewAuditScreen");
						
						fnpClick_OR("TaskFinancialTabLink");
						fnpClick_OR("EditBtn");
						
						
						fnpClick_OR("ExpenseAllocation_btn");
						
						*/
						
						
						fnpClick_OR("ExpenseAllocation_btn_ViewVisit_screen");
						
						
						fnpWaitForVisible("AutoAllocateByDays_btn");
						//String remoteAuditFlag=fnpGetText_OR("ExpenseAllocationByVisitScreen_RemoteAuditFlag").trim();
						String onSiteFlag=fnpGetText_OR("ExpenseAllocationByVisitScreen_OnSite_Flag").trim();
						String remoteAuditFlag;
						if (onSiteFlag.equalsIgnoreCase("Y")) {
							remoteAuditFlag="N";
						} else {
							if (onSiteFlag.equalsIgnoreCase("N")) {
								remoteAuditFlag="Y";
							}else{
								msg="Value is not present for OnSite flag.";
								fnpMymsg(msg);
								throw new Exception(msg);
							}
						}
						
						int rowInConcurExpenseTable=fnpCountRowsInTable("ConcurExpensesTable");
						
						if (remoteAuditFlag.equalsIgnoreCase("N")) {
						//if (onSiteFlag.equalsIgnoreCase("N")) {
							fnpMymsg("Remote Audit Flag is N.");
							if (rowInConcurExpenseTable>0) {
								fnpMymsg("Concur Expenses have '"+rowInConcurExpenseTable+"' rows.");								
							} else {
								msg="Concur Expenses have 0 (zero) rows, hence failed.";
								fnpMymsg(msg);
								throw new Exception(msg);
							}
							
							allocateExpense();
							allocation_complete();
							break;

						} else {
							if (remoteAuditFlag.equalsIgnoreCase("Y")) {
								fnpMymsg("Remote Audit Flag is Y. ");
								if (rowInConcurExpenseTable>0) {
									fnpMymsg("Concur Expenses have '"+rowInConcurExpenseTable+"' rows.");
									allocateExpense();
									allocation_complete();
									break;									
								} else {
									fnpMymsg("Concur Expenses have '"+rowInConcurExpenseTable+"' rows, so not going to allocate but going to click AllocationCompleted_btn");
									allocation_complete();
									break;
								}
								
							} else {
								msg="Onsite Flag is not set.";
								fnpMymsg(msg);
								iwhileCount=maxTry+1;
								throw new Exception(msg);
							}
						}
						
					}catch (Throwable t) {
						
						if (iwhileCount<(maxTry)) {
							iwhileCount++;
							//fnpCommonFinalCatchBlock(t, "  Exp_AllocationFlow flow  is fail . ", "Exp_AllocationFlowFail");
							fnpDesireScreenshot("Exp_AllocationFlowFail");
							fnpDesireScreenshot_old("Exp_AllocationFlowFail"+"usingOldMethod");
							//keep in loop , do all steps again
						}else{
							fnpCommonFinalCatchBlock(t, "  Exp_AllocationFlow flow  is fail . ", "Exp_AllocationFlowFail");
							//IsBrowserPresentAlready = false; // This is for special reason if one case is fail due to application error or any other reason then it close the browser and login again.
							break;
						}
		
		
				}
		}

	}
	
	

	@AfterMethod
	public void reportDataSetResult() throws Throwable {
		
		
		fnpCommonCloseBrowsersAndKillProcess();
		
		if (skip)
			TestUtil.reportDataSetResult(currentSuiteXls, this.getClass().getSimpleName(), count + 2, "SKIP");
		else if (fail) {
			isTestPass = false;
			TestUtil.reportDataSetResult(currentSuiteXls, this.getClass().getSimpleName(), count + 2, "FAIL");
		} else
			TestUtil.reportDataSetResult(currentSuiteXls, this.getClass().getSimpleName(), count + 2, "PASS");

		skip = false;
		fail = false;

	}


	
	

	@AfterTest
	public void reportTestResult() throws Throwable {
		int rowNum = currentSuiteXls.getCellRowNum("Test Cases", "TCID", this.getClass().getSimpleName());
		currentSuiteXls.setCellData("Test Cases", "Last_Execution", rowNum, fnReturnDateWithTimeForExcel());

		// Add Results column to test case sheet
		if (isTestPass) {
			TestUtil.reportDataSetResult(currentSuiteXls, "Test Cases", TestUtil.getRowNum(currentSuiteXls, this.getClass().getSimpleName()), "PASS");
			currentSuiteXls.setCellData("Test Cases", "Last_Successful_Execution", rowNum, fnReturnDateWithTimeForExcel());
			fnpElapsedTime(currentSuiteName, this.getClass().getSimpleName(), currentScriptCode, "PASS");
		} else {
			TestUtil.reportDataSetResult(currentSuiteXls, "Test Cases", TestUtil.getRowNum(currentSuiteXls, this.getClass().getSimpleName()), "FAIL");
			fnpElapsedTime(currentSuiteName, this.getClass().getSimpleName(), currentScriptCode, "FAIL");
		}
		fnpMymsg("=========================================================================================================================================");

		hashXlData.clear();

		fnpCommonCloseBrowsersAndKillProcess();

	}

	
	
	
	

}
