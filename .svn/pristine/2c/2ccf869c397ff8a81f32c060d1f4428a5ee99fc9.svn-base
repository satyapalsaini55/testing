package nsf.ecap.Dietary_Supplement_suite;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import nsf.ecap.ISR_suite.ISO9001_Single;
import nsf.ecap.Work_Order_suite.Modbrack_Not_Certified;
import nsf.ecap.Work_Order_suite.NewNew_Draft_InReview_No_Fac;
import nsf.ecap.Work_Order_suite.NewNew_InProc_Completed_No_Fac;
import nsf.ecap.Work_Order_suite.TestSuiteBase;
import nsf.ecap.config.IPULSE_CONSTANTS;
//import nsf.ecap.config.IPULSE_CONSTANTS;
import static nsf.ecap.config.IPULSE_CONSTANTS.*;
import nsf.ecap.util.ErrorUtil;
import nsf.ecap.util.TestUtil;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;

public class ModBrack_Certified extends TestSuiteBase_Dietary_Supplement_suite {


	
	
/*	// Runmode of test case in a suite
	@BeforeTest
	@Parameters({ "className" })
	public void checkTestSkip(String className) throws Throwable {
		BS01 = new NewNew_Draft_InReview_No_Fac();
		BS01.checkTestSkip(this.getClass().getSimpleName());

	}
	
	*/
	
	@BeforeTest
	public void checkTestSkip() throws Throwable {
		BS01 = new NewNew_Draft_InReview_No_Fac();
		BS01.checkTestSkip(this.getClass().getSimpleName());
		
		BS16=new Modbrack_Not_Certified();
		BS05 = new NewNew_InProc_Completed_No_Fac();

	}
	
	
	
	

	@Test(priority = 1)
	public void CreateWOFlow() throws Throwable {

		//fnpCallGoldenProcedure_COPY_AUDIT_TRANSACTION_SUBMIT(2230662,2277601,"TestScriptUser TestScriptUser");
		
		BS01.CreateWOFlow();


	}

	
	@Test(priority = 2, dependsOnMethods = { "CreateWOFlow" })
	public void AddingData__Facility_Tab() throws Throwable {
		BS01.AddingData__Facility_Tab();

	}

	
	
	
	@Test(priority = 3, dependsOnMethods = { "AddingData__Facility_Tab" })
	public void AddingData__Tasks_Tab() throws Throwable {
		BS01.AddingData__Tasks_Tab();

	}
	
	
	
	

	@Test(priority = 4, dependsOnMethods = { "AddingData__Tasks_Tab" })
	public void AddingData__Products_Tab() throws Throwable {

		BS01.AddingData__Products_Tab();

	}
	
	
	
	
	@Test(priority = 5, dependsOnMethods = { "AddingData__Products_Tab" })
	public void Verifying__DRAFT_INREVIEW_ModBrack() throws Throwable {

		BS16.Verifying__DRAFT_INREVIEW_ModBrack();

	}
	
	
	
	@Test(priority = 6, dependsOnMethods = { "Verifying__DRAFT_INREVIEW_ModBrack" })
	public void Client_Quote_Task() throws Throwable {

		BS16.Client_Quote_Task();

	}
	
	
	
	@Test(priority = 7, dependsOnMethods = { "Client_Quote_Task" })
	public void Mod_Brack_Review_Task() throws Throwable {
		BS16.Mod_Brack_Review_Task();
	}

	
	
	@Test(priority = 8, dependsOnMethods = { "Mod_Brack_Review_Task" })
	public void Testing_Task() throws Throwable {
		
		try{
				String msg;
				fnpCommonClickTaskTab();
				//Pradeep
				fnpClick_OR("AddTasks_btn");
				
				String[] TaskTypeArray;
				String TaskTypeString = (String) hashXlData.get("Adding_Tasks_again");
				if (!TaskTypeString.isEmpty()) {
					TaskTypeArray = ((String) hashXlData.get("Adding_Tasks_again")).split(",");
		
					for (int i = 0; i < TaskTypeArray.length; i++) {
						String val = TaskTypeArray[i];
						String TaskTypeCheckboxXpath = "//td[contains(text(),'" + val + "')]/preceding-sibling::td/div/div[contains(@class,'ui-chkbox-box')]";
						driver.findElement(By.xpath(TaskTypeCheckboxXpath)).click(); //
		
					}
		
				}else{
					msg="Tasks to be added is not provided in excel.";
					fnpMymsg(msg);
					throw new Exception(msg);
					
				}
				
				fnpGetORObjectX("ContinueBtnInTaskTab").click();
				fnpLoading_wait();
				fnpLoading_wait();
				
				//in below lines,  we are only adding bill code to the TESTING TASK, as here we are assuming that there will be only 1 task in excel in this 'Adding_Tasks_again' column
				int taskTypeColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskTypeColName);
				int testingTaskRowNo = fnpFindRow("TasksTable_EditWO", (String) hashXlData.get("Adding_Tasks_again"), taskTypeColIndex);
				String expandingIconForTestingTask_xpath=".//*[@id='mainForm:tabView:dataTable_data']/tr["+testingTaskRowNo+"]/td[1]/div[@class='ui-row-toggler ui-icon ui-icon-circle-triangle-e']";
				fnpClick_NOR(expandingIconForTestingTask_xpath);
				String billCodeTxtBx_xpath=".//*[@id='mainForm:tabView:dataTable:"+(testingTaskRowNo-1)+":jobExpence:0:biilCode']";
				//here we are inserting the same first billcode which is present in excel in column BillingCode
				String[] BillingCodeArray = ((String) hashXlData.get("BillingCode")).split(",");
				fnpGetORObjectX___NOR(billCodeTxtBx_xpath).sendKeys(BillingCodeArray[0]);
				
				String saveAllBtn_forTestingTask_xpath=".//*[@id='mainForm:tabView:dataTable:"+(testingTaskRowNo-1)+":savealltaskwo']";
				fnpClick_NOR(saveAllBtn_forTestingTask_xpath);
				
				
		
				fnpMymsg(" Going to click Testing task no. ");
				int ColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskNoColName);
				String testingTaskNo = fnpFetchFromTable("TasksTable_EditWO", testingTaskRowNo, ColIndex);
				Thread.sleep(500);
				fnpMymsg("Going to click on Testing task no. in Task Table i.e. '" + testingTaskNo + "' .");
				fnpClickALinkInATable(testingTaskNo);
				fnpMymsg("Clicked on Testing task no. in Task Table i.e. '" + testingTaskNo + "' .");
				fnpLoading_wait();
				
				fnpPFListByLiNo_old("FacilityLocationList", "FacilityLocationOptions",  1);
				//pradeep
				fnpClick_OR("CreateNewBtn_WhichTakesYouToEPSF");
				
				
				String expCollType = (String) hashXlData.get("Collection_Type").trim();
				// value in excel and drop down is not matched , 'Qualification( QQ )'
				// and 'Qualification (QQ)'
				fnpPFList("EPSF_CollectionTypeList", "EPSF_CollectionTypeListOptions", expCollType);
				Thread.sleep(3000);
		
				fnpPFList("EPSF_TestCategoryList", "EPSF_TestCategoryListOptions", (String) hashXlData.get("Test_Category"));
		
				fnpPFList("EPSF_StandardList", "EPSF_StandardListOptions", (String) hashXlData.get("Standard"));
		
				fnpPFList("EPSF_TestLocationList", "EPSF_TestLocationListOptions", (String) hashXlData.get("Test_Location"));
		
				fnpPFList("EPSF_ShipToLocationList", "EPSF_ShipToLocationListOptions", (String) hashXlData.get("Ship_to_Location"));
		
				fnpClick_OR("EPSF_CreateNextBtn");
		
				fnpType("OR", "EPSF_TestDescriptionTxtBox", (String) hashXlData.get("Test_Description"));
				fnpType("OR", "EPSF_TradeDesignation_ProductIDTxtBox", (String) hashXlData.get("TradeDesignation"));
				fnpType("OR", "EPSF_PhysicalDescriptionofSampleTxtBox", (String) hashXlData.get("PhysicalDescription"));
				fnpType("OR", "EPSF_DCCNumberTxtBox", (String) hashXlData.get("DCCNumber"));
				fnpType("OR", "EPSF_MonitorCodeTxtBox", (String) hashXlData.get("MonitorCode"));
				fnpType("OR", "EPSF_LotTxtBox", (String) hashXlData.get("Lot"));
				
				
				
				/***** New changes as 4 tabs added in epsf ******/
				fnpClick_OR("EPSF_CreateNextBtn");// Click on Next for EPSF fields tab.
				fnpLoading_wait();
				fnpWaitForVisible("EPSF_INFO_highlightedTab");
				fnpLoading_wait();
				fnpClick_OR("EPSF_CreateNextBtn");// Click on Next for EPSF Info tab.
				fnpLoading_wait();
		
				fnpClick_OR("CreateEPSFFirstBtn");
				
				
				fnpClick_OR("EPSF_SaveBtn");
				fnpClick_OR("WOLinkATEPSF");
				
				
				
				String dropTestingTask_xpath=".//*[@id='mainForm:tabView:dataTable:"+(testingTaskRowNo-1)+":deleteTaskBtn']";
				fnpClick_NOR(dropTestingTask_xpath);
				fnpType("OR", "DropReasonTxtArea", "Automation Testing Purpose");
				
				fnpClick_OR("DropConfirmation");
				
		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "   Testing_Task  is failed . ", "Testing_TaskFail");

		}
		
	}

	
	
	@Test(priority = 9, dependsOnMethods = { "Testing_Task" })
	public void Completing_ClientDocRequestAI_And_Completing_ModBrackReview_Task() throws Throwable {
		try{
				fnpProcessAI(actionItemDesc_ClientDocRequest, (String) hashXlData.get("Change_Status_Completed"));
				
				int taskDescColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", SnapShot_taskTable_TaskDescColName);
				int taskColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", SnapShot_taskTable_TaskColName);
				int modBrackReviewTaskRowNo = fnpFindRow("Snapshot_TasksSummaryTable", SnapShot_ModBrack_Review_Task, taskDescColIndex);
				Thread.sleep(500);
				String modBrackTaskNo = fnpFetchFromTable("Snapshot_TasksSummaryTable", modBrackReviewTaskRowNo, taskColIndex);
				fnpClickALinkInATable(modBrackTaskNo);
				
				
				fnpLoading_wait();
				fnpMymsg("Clicked on Mod/Brack Review Task no. in Snapshot task Table i.e. '" + modBrackTaskNo + "' .");
		
				fnpWaitTillVisiblityOfElementNClickable_OR("TaskTab_ScopeValidation_AssignTaskIcon");
				fnpGetORObjectX("TaskTab_ScopeValidation_AssignTaskIcon").click();
				fnpLoading_wait();
		
				//fnpClick_OR_WithoutWait("TaskTab_ScopeValidation_AssignTask_SaveBtn");
				//fnpClick_OR("TaskTab_ScopeValidation_AssignTask_SaveBtn");
				fnpClickInDialog_OR("TaskTab_ScopeValidation_AssignTask_SaveBtn");
				fnpCheckError("while assigning ModBrack task");
				fnpMymsg("Mod/Brack task assigned successfully.");
				fnpClick_OR("TaskTab_ScopeValidation_PerformTaskIcon");
				fnpCheckError("after clicking perform task icon");
				fnpMymsg("Mod/Brack task performed successfully.");
				
				
				
				/*****************New changes for sprint 9.2******************************/
				fnpAnnualUpdateRequiredChkbxesUsingInReviewTask();
				/*****************New changes for sprint 9.2******************************/
				
		
				
				
				fnpClick_OR("TaskTab_ScopeValidation_CompleteTaskIcon");
		
				fnpWaitTillVisiblityOfElementNClickable_OR("TaskTab_ScopeValidation_CompleteTaskSaveBtn");
		

				fnpClick_OR("TaskTab_ScopeValidation_CompleteTaskSaveBtn");
				
				fnpCommonBackToViewNBackBtnClick();
				
				 taskDescColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", SnapShot_taskTable_TaskDescColName);
				int statusColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", SnapShot_taskTable_StatusColName);
				modBrackReviewTaskRowNo = fnpFindRow("Snapshot_TasksSummaryTable", SnapShot_ModBrack_Review_Task, taskDescColIndex);
				
				String modBrackReviewTaskStatus = fnpFetchFromTable("Snapshot_TasksSummaryTable", modBrackReviewTaskRowNo, statusColIndex);
		
				String expectedModBrackReviewStatus = "COMPLETED";
		
				Assert.assertTrue(modBrackReviewTaskStatus.equalsIgnoreCase(expectedModBrackReviewStatus), "Mod/Brack Review Task Status is not getting changed to -- " + expectedModBrackReviewStatus
						+ ".");
		
				fnpMymsg("Mod/Brack Review Task Status in Snapshot tab now becomes to '" + expectedModBrackReviewStatus + "' status ");
				
				
				
				int revisionExecutionQuoteTaskRowNo = fnpFindRow("Snapshot_TasksSummaryTable", SnapShot_RevisionExecutionQuote, taskDescColIndex);
				
				String revisionExecutionQuoteTaskStatus = fnpFetchFromTable("Snapshot_TasksSummaryTable", revisionExecutionQuoteTaskRowNo, statusColIndex);
		
				String expectedrevisionExecutionQuoteTaskStatus = "READY";
		
				Assert.assertTrue(revisionExecutionQuoteTaskStatus.equalsIgnoreCase(expectedrevisionExecutionQuoteTaskStatus), SnapShot_RevisionExecutionQuote+" Task Status is not getting changed to -- " + expectedrevisionExecutionQuoteTaskStatus
						+ ".");
		
				fnpMymsg(SnapShot_RevisionExecutionQuote+" Task Status in Snapshot tab now becomes to '" + expectedrevisionExecutionQuoteTaskStatus + "' status ");
				
				
				
				int assignedToColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", SnapShot_taskTable_AssignedToColName);
				String revisionExecutionQuoteTask_assignee = fnpFetchFromTable("Snapshot_TasksSummaryTable", revisionExecutionQuoteTaskRowNo, assignedToColIndex);
				
				String accountManager;
				
/*				
				if (loginAsFullName.equalsIgnoreCase("") | (loginAsFullName==null) ) {
					accountManager=(String) hashXlData.get("AccountManager").trim();
				} else {
					accountManager=loginAsFullName;
				}
				Assert.assertTrue(revisionExecutionQuoteTask_assignee.equalsIgnoreCase(accountManager), SnapShot_RevisionExecutionQuote+" Task is not assigned to the Account Manager i.e. -- " + accountManager+ ".");
				
				*/
				
				
				if (  (loginAsFullName==null) ||loginAsFullName.equalsIgnoreCase("") ) {
					
					if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
						accountManager=(String) hashXlData.get("AccountManager_Code").trim();
					
					}else{
						accountManager=(String) hashXlData.get("AccountManager").trim();				
					}

				} else {
				
					if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
						accountManager=loginUser_code;

					}else{
						accountManager=loginAsFullName;

					}
				}
			

				
				
				
				Assert.assertTrue(revisionExecutionQuoteTask_assignee.contains(accountManager), SnapShot_RevisionExecutionQuote+" Task is not assigned to the Account Manager i.e. -- " + accountManager+ ".");
		
				fnpMymsg(SnapShot_RevisionExecutionQuote+" Task is assigned to the Account Manager i.e. -- " + accountManager + "'. ");
				
		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "   Completing_ClientDocRequestAI_And_Completing_ModBrackReview_Task  is failed . ", "Completing_ClientDocRequestAI_And_Completing_ModBrackReview_TaskFail");

		}	
		
	}
	
	
	
	
	
	@Test(priority = 10, dependsOnMethods = { "Completing_ClientDocRequestAI_And_Completing_ModBrackReview_Task" })
	public void ModBrack_Revision_Execution_Quote() throws Throwable {
		try{
	
			BS16.ModBrack_Revision_Execution_Quote();
		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "   ModBrack_Revision_Execution_Quote  is failed . ", "ModBrack_Revision_Execution_QuoteFail");

		}	
		
	}
	
	
/*	
	@Test(priority = 11, dependsOnMethods = { "ModBrack_Revision_Execution_Quote" })
	public void Lit_Eval() throws Throwable {

		try{
			//fnpProcessAI("ClientLitRequest", "Submitted");
			//BS16.ModBrack_Lite_Eval();
			BS05.Literature_Eval();
		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "   Lit_Eval  is failed . ", "Lit_EvalFail");

		}	

	}
	
	*/

	
	
	//@Test(priority = 12, dependsOnMethods = { "Lit_Eval" })
	@Test(priority = 12, dependsOnMethods = { "ModBrack_Revision_Execution_Quote" })
	public void Certification_Decision_Task() throws Throwable {
		try{
			
			if (fnpCheckElementPresenceImmediately("TaskTab_ScopeValidation_BackBtn")) {
				//fnpClickBackToViewNBackBtnWhenNullPointerExceptionWhenAlertFlagNotPresent();
				fnpClick_OR("TaskTab_ScopeValidation_BackBtn");
			}
			
			//fnpProcessAI(actionItemDesc_DelistedGMPStandard, "Completed");
			
/*			fnpCommonClickSnapShotTab();
			fnpWaitForVisible("ActionItemTable_EditWO");
			int ActionItemColIndex = fnpFindColumnIndex("ActionItemTable_HeaderRow", actionItemTableActionItemColName);
			int itemDescColIndex = fnpFindColumnIndex("ActionItemTable_HeaderRow", actionItemTableItemDescColName);
			//int actionItemInfoRowNo = fnpFindRow("ActionItemTable_EditWO", actionItemDesc_DelistedGMPStandard, itemDescColIndex);
			int actionItemInfoRowNo = fnpFindRowContainsName_usingForPagingToNotThrowErrorIfNotFoundInCurrentPage("ActionItemTable_EditWO", actionItemDesc_DelistedGMPStandard, itemDescColIndex);
			if (actionItemInfoRowNo>0) {
				fnpMymsg(" new  action item delighed is coming.");
				fnpProcessAI(actionItemDesc_DelistedGMPStandard, "Completed");
			}else{
				fnpMymsg(" new  action item delighed is NOT  coming.");
			}
			*/
			
			fnpCommonForProcessDelistedGMPStandardAI_ifPresent() ;
			
			BS05.Certification_Decision_Task();
			fnpCommonForProcessDelistedGMPStandardAI_ifPresent() ;
		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "   Certification_Decision_Task  is failed . ", "Certification_Decision_TaskFail");

		}

	}
	
	
	
	
	@Test(priority = 13, dependsOnMethods = { "Certification_Decision_Task" })
	public void Verify_INPROCESS_to_COMPLETE_Automatically() throws Throwable {	
		try{
			BS05.Verify_INPROCESS_to_COMPLETE_Automatically();
		} catch (Throwable t) {
			fnpCommonFinalCatchBlock(t, "   Verify_INPROCESS_to_COMPLETE_Automatically  is failed . ", "Verify_INPROCESS_to_COMPLETE_AutomaticallyFail");

		}

	}
	
	

	
/*	
	@Test(priority = 14, dependsOnMethods = { "Verify_INPROCESS_to_COMPLETE_Automatically" })
	public void Verify_InvoiceInFinancialTab() throws Throwable {
		try{
			BS05.Verify_InvoiceInFinancialTab();
		} catch (Throwable t) {
			fnpCommonFinalCatchBlock(t, "   Verify_InvoiceInFinancialTab  is failed . ", "Verify_InvoiceInFinancialTabFail");

		}
		
		
	}
	*/

	
	@Test(priority = 13, dependsOnMethods = { "Verify_INPROCESS_to_COMPLETE_Automatically" })
	public void CLEANUP_WO_AUTOMATION_DATA() throws Throwable {
		BS05 = new NewNew_InProc_Completed_No_Fac();		
		BS05.CLEANUP_WO_AUTOMATION_DATA();

	}
	
	
	
	@AfterMethod
	public void reportDataSetResult() {
		if (fail) {
			isTestPass = false;
		}
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