package nsf.ecap.Work_Order_suite;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import nsf.ecap.util.ErrorUtil;
import nsf.ecap.util.TestUtil;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;
import static nsf.ecap.config.IPULSE_CONSTANTS.*;

public class Linking_Custom_Custom_WO extends TestSuiteBase {

	public String FirstWoNo = null;
	public String SecondWoNo = null;

	public static Date Originalstart;

	@BeforeTest
	public void checkTestSkip() throws Throwable {
		BS01 = new NewNew_Draft_InReview_No_Fac();
		BS11 = new WO_Custom();
		BS01.checkTestSkip(this.getClass().getSimpleName());

	}

	// @Test(enabled = false)
	@Test(priority = 1)
	public void First_CreateWOFlow() throws Throwable {

		hashXlData.put("Alerts_Verify_Flag", "No");
		hashXlData.put("Check_Batch_Job", "No");

		BS01.CreateWOFlow();
		Originalstart = start;
		String workOrderNo_text = fnpGetORObjectX("NewlyCreatedWorkOrderNo").getText();
		FirstWoNo = ((workOrderNo_text.split(" "))[0]).trim();
		fnpMymsg("First  WO no. is:" + FirstWoNo);

	}

	@Test(priority = 2, dependsOnMethods = { "First_CreateWOFlow" })
	public void First_AddingData__Facility_Tab() throws Throwable {
		BS01.AddingData__Facility_Tab();

	}

	@Test(priority = 3, dependsOnMethods = { "First_AddingData__Facility_Tab" })
	public void First_AddingData__Tasks_Tab() throws Throwable {
		BS01.AddingData__Tasks_Tab();

	}

	@Test(priority = 4, dependsOnMethods = { "First_AddingData__Tasks_Tab" })
	public void First_AddingData__Products_Tab() throws Throwable {

		BS01.AddingData__Products_Tab();

	}

	@Test(priority = 5, dependsOnMethods = { "First_AddingData__Products_Tab" })
	public void First_AddingData__Documents_Tab() throws Throwable {

		BS01.AddingData__Documents_Tab();

	}

	@Test(priority = 6, dependsOnMethods = { "First_AddingData__Documents_Tab" })
	public void First_AddingData__Financials_Tab() throws Throwable {

		BS01.AddingData__Financials_Tab();

	}

	@Test(priority = 7, dependsOnMethods = { "First_AddingData__Financials_Tab" })
	public void First_Verifying__ActionItems_Tab() throws Throwable {
		BS01.Verifying__ActionItems_Tab();

	}

	@Test(priority = 8, dependsOnMethods = { "First_Verifying__ActionItems_Tab" })
	public void Second_CreateWOFlow() throws Exception {

		try {
			hashXlData.clear();
			fnpLoadHashData(hashXlData, Work_Order_suitexls, classNameText, 2, 4);
			hashXlData.put("Alerts_Verify_Flag", "No");
			hashXlData.put("Check_Batch_Job", "No");

			fnpClickAtTopWorkAroundForClickingMenu();
			fnpWaitForVisible_usingLinkNameInOR("Menu");
			fnpMouseHover_LinkNameInOR("Menu");
			fnpMouseHover_LinkNameInOR("CreateIssue");
			fnpGetORObjectX_usingLinkText("CreateWorkOrderLink").click();
			fnpLoading_wait();

			BS01.CreateWOFlow();
			start = Originalstart;

			String workOrderNo_text = fnpGetORObjectX("NewlyCreatedWorkOrderNo").getText();
			SecondWoNo = ((workOrderNo_text.split(" "))[0]).trim();
			fnpMymsg(" Second Work Order No is:" + SecondWoNo);

		} catch (Throwable t) {
			fail = true;
			isTestPass = false;
			fnpMymsg("  Second_CreateWOFlow  method is failed . " + " . Error is ---" + t.getMessage());
			fnpDesireScreenshot("Second_CreateWOFlow");
			String stackTrace = Throwables.getStackTraceAsString(t);
			String errorMsg = t.getMessage();
			errorMsg = errorMsg + "\n\n\n\n Second_CreateWOFlow  method is failed   .See screenshot 'Second_CreateWOFlow'\n\n" + stackTrace;
			Exception c = new Exception(errorMsg);
			ErrorUtil.addVerificationFailure(c);

		}

	}

	@Test(priority = 9, dependsOnMethods = { "Second_CreateWOFlow" })
	public void Second_AddingData__Facility_Tab() throws Throwable {
		BS01.AddingData__Facility_Tab();

	}

	@Test(priority = 10, dependsOnMethods = { "Second_AddingData__Facility_Tab" })
	public void Second_AddingData__Tasks_Tab() throws Throwable {
		BS01.AddingData__Tasks_Tab();

	}

	@Test(priority = 11, dependsOnMethods = { "Second_AddingData__Tasks_Tab" })
	public void Second_AddingData__Products_Tab() throws Throwable {

		BS01.AddingData__Products_Tab();

	}

	@Test(priority = 12, dependsOnMethods = { "Second_AddingData__Products_Tab" })
	public void Second_AddingData__Documents_Tab() throws Throwable {

		BS01.AddingData__Documents_Tab();

	}

	@Test(priority = 13, dependsOnMethods = { "Second_AddingData__Documents_Tab" })
	public void Second_AddingData__Financials_Tab() throws Throwable {

		BS01.AddingData__Financials_Tab();

	}

	@Test(priority = 14, dependsOnMethods = { "Second_AddingData__Financials_Tab" })
	public void Second_Verifying__ActionItems_Tab() throws Throwable {
		BS01.Verifying__ActionItems_Tab();

	}

	@Test(priority = 15, dependsOnMethods = { "Second_Verifying__ActionItems_Tab" })
	public void Link_First_To_Second_WO() {
		try {

			fnpCommonClickTaskTab();
			fnpClick_OR("LinkTasksBtn");

			fnpType("OR", "LinkTextBox", FirstWoNo);

			fnpClickInDialog_OR("LinkGoBtn");
			fnpWaitForVisible("LinkTaskSaveBtn");

			fnpWaitForVisible("TechnicalTaskLinkCheckbox");
			fnpGetORObjectX("TechnicalTaskLinkCheckbox").click();

			fnpClickInDialog_OR("LinkTaskSaveBtn");

			fnpCheckError(" while Link_First_To_Second_WO");

			fnpWaitForVisible("Task_ShowAllLink");
			fnpWaitTillVisiblityOfElementNClickable_OR("Task_ShowAllLink");

			int LinkedWOColumnNo = fnpFindColumnIndex("TaskTable_HeaderRow", "Linked WO");
			//int LinkedWOColumnNo = fnpFindColumnIndex("TaskTable_HeaderRow", "Linked WOs");
			int OptionsColumnNo = fnpFindColumnIndex("TaskTable_HeaderRow", "Options");
			int LinkRowNo = 0;
			LinkRowNo = fnpFindRow("TasksTable_EditWO", "Unlink", OptionsColumnNo);

			if ((LinkRowNo < 1)) {
				throw new Exception(" 'UnLink' link is not present in Task Table.");
			}

			String linkWO = fnpFetchFromTable("TasksTable_EditWO", LinkRowNo, LinkedWOColumnNo);

			Assert.assertEquals(FirstWoNo, linkWO, "First Work Order is not linked properly.");

			fnpMymsg("First Work Order is linked successfully to Second Work Order");

		} catch (Throwable t) {
			fail = true;
			isTestPass = false;
			fnpMymsg(" Link_First_To_Second_WO is fail,See screenshot 'Link_First_To_Second_WOFail'" + " . Error is ---" + t.getMessage());
			fnpDesireScreenshot("Link_First_To_Second_WOFail");

			String stackTrace = Throwables.getStackTraceAsString(t);
			String errorMsg = t.getMessage();
			errorMsg = errorMsg + "\n\n\n\n Link_First_To_Second_WO is failed.See screenshot 'Link_First_To_Second_WOFail'\n\n" + stackTrace;
			Exception c = new Exception(errorMsg);
			ErrorUtil.addVerificationFailure(c);
		}

	}

	@Test(priority = 16, dependsOnMethods = { "Link_First_To_Second_WO" })
	public void Second_Verifying__DRAFT_INREVIEW() throws Throwable {

		BS11.Custom_Verifying__DRAFT_INREVIEW();

	}

	@Test(priority = 17, dependsOnMethods = { "Second_Verifying__DRAFT_INREVIEW" })
	public void Second_Complete_Client_App_Review_Task() {
		BS11.Custom_Client_App_Review_Task();

	}

	
	@Test(priority = 18, dependsOnMethods = { "Second_Complete_Client_App_Review_Task" })
	public void Second_Technical_Review_Task() {

		BS11.Technical_Review_Task();

	}

	@Test(priority = 19, dependsOnMethods = { "Second_Technical_Review_Task" })
	public void First_Verifying__DRAFT_INREVIEW() throws Exception {

		try {
			hashXlData.clear();

			fnpCommonClickSnapShotTab();

			Thread.sleep(500);
			
/*			fnpWaitTillVisiblityOfElementNClickable_NOR(".//a[text()='" + FirstWoNo + "']");
			driver.findElement(By.linkText(FirstWoNo)).click();
			*/
			
			fnpClickALinkInATable(FirstWoNo);
			

			Thread.sleep(2000);
			fnpLoading_wait();

			hashXlData.clear();
			fnpLoadHashData(hashXlData, Work_Order_suitexls, classNameText, 2, 3);
			hashXlData.put("Alerts_Verify_Flag", "No");
			hashXlData.put("Check_Batch_Job", "No");

			if (fnpCheckElementPresenceImmediately("EditWOBtn")) {

				fnpClick_OR("EditWOBtn");
			}

			BS11.Custom_Verifying__DRAFT_INREVIEW();

		} catch (Throwable t) {
			fail = true;
			isTestPass = false;
			fnpMymsg("  First_Verifying__DRAFT_INREVIEW  method is failed . " + " . Error is ---" + t.getMessage());
			fnpDesireScreenshot("First_Verifying__DRAFT_INREVIEW");
			String stackTrace = Throwables.getStackTraceAsString(t);
			String errorMsg = t.getMessage();
			errorMsg = errorMsg + "\n\n\n\n First_Verifying__DRAFT_INREVIEW  method is failed   .See screenshot 'First_Verifying__DRAFT_INREVIEW'\n\n" + stackTrace;
			Exception c = new Exception(errorMsg);
			ErrorUtil.addVerificationFailure(c);

		}

	}

	@Test(priority = 20, dependsOnMethods = { "First_Verifying__DRAFT_INREVIEW" })
	public void First_Complete_Client_App_Review_Task() {
		BS11.Custom_Client_App_Review_Task();

	}

	@Test(priority = 21, dependsOnMethods = { "First_Complete_Client_App_Review_Task" })
	public void Verify_Second_CertDeci_Not_Ready() {

		try {
			fnpWaitForVisible("SnapshotTabLink");
			fnpGetORObjectX("SnapshotTabLink").click();
			fnpLoading_wait();

			Thread.sleep(500);
			
/*			fnpWaitTillVisiblityOfElementNClickable_NOR(".//a[text()='" + SecondWoNo + "']");
			driver.findElement(By.linkText(SecondWoNo)).click();
			*/
			
			fnpClickALinkInATable(SecondWoNo);

			Thread.sleep(2000);
			fnpLoading_wait();

			fnpCommonClickTaskTab();
			fnpWaitForVisible("Task_ShowAllLink");

			int TaskTypeColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskTypeColName);
			int TaskStatusColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_StatusColName);
			int RowNoForCertDecison = fnpFindRow("TasksTable_EditWO", taskType_CertDecision, TaskTypeColIndex);

			String certDecisionStatus = fnpFetchFromTable("TasksTable_EditWO", RowNoForCertDecison, TaskStatusColIndex);
			certDecisionStatus = certDecisionStatus.trim();

			Assert.assertEquals(certDecisionStatus, "CREATED",
					" Expected is that Work Order B should not become ready and should be in 'CREATED' state but it is not in 'CREATED' status.");
			fnpMymsg("Cert decision of Work Order B is not become ready state and its current status is --" + certDecisionStatus);

			fnpWaitForVisible("SnapshotTabLink");
			fnpGetORObjectX("SnapshotTabLink").click();

			fnpLoading_wait();

			Thread.sleep(500);
			
			
/*			fnpWaitTillVisiblityOfElementNClickable_NOR(".//a[text()='" + FirstWoNo + "']");
			driver.findElement(By.linkText(FirstWoNo)).click();
			*/
			fnpClickALinkInATable(FirstWoNo);
			
			fnpLoading_wait();

			fnpCommonClickTaskTab();
			fnpWaitForVisible("Task_ShowAllLink");

		} catch (Throwable t) {
			fail = true;
			isTestPass = false;
			fnpMymsg("  Verify_Second_CertDeci_Not_Ready  method is failed . " + " . Error is ---" + t.getMessage());

			fnpDesireScreenshot("Verify_Second_CertDeci_Not_Ready");
			String stackTrace = Throwables.getStackTraceAsString(t);
			String errorMsg = t.getMessage();
			errorMsg = errorMsg + "\n\n\n\n Verify_Second_CertDeci_Not_Ready  method is failed   .See screenshot 'Verify_Second_CertDeci_Not_Ready'\n\n" + stackTrace;
			Exception c = new Exception(errorMsg);
			ErrorUtil.addVerificationFailure(c);
		}
	}

	@Test(priority = 22, dependsOnMethods = { "Verify_Second_CertDeci_Not_Ready" })
	public void First_Technical_Review_Task() throws Throwable {

		BS11.Technical_Review_Task();

	}

	@Test(priority = 23, dependsOnMethods = { "First_Technical_Review_Task" })
	public void Second_Certification_Decision_Task() throws Throwable {

		try {

			hashXlData.clear();
			fnpLoadHashData(hashXlData, Work_Order_suitexls, classNameText, 2, 4);
			hashXlData.put("Alerts_Verify_Flag", "No");
			hashXlData.put("Check_Batch_Job", "No");

			fnpWaitForVisible("SnapshotTabLink");
			fnpGetORObjectX("SnapshotTabLink").click();

			fnpLoading_wait();

			Thread.sleep(500);
			
/*			fnpWaitTillVisiblityOfElementNClickable_NOR(".//a[text()='" + SecondWoNo + "']");
			driver.findElement(By.linkText(SecondWoNo)).click();
			*/
			
			fnpClickALinkInATable(SecondWoNo);
			
			fnpLoading_wait();

			fnpCommonClickTaskTab();

			int TaskTypeColIndex;
			int TaskStatusColIndex;
			int RowNoForCertDecison;

			int waitCount = 0;
			String certDecisionStatus;
			while (true) {
				waitCount++;

				fnpWaitForVisible("Task_ShowAllLink");

				fnpWaitForVisible("Task_ShowAllLink"); // sometime fail on
														// Jeff's machine to
														// calculate column and
														// row

				TaskTypeColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskTypeColName);
				TaskStatusColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_StatusColName);
				RowNoForCertDecison = fnpFindRow("TasksTable_EditWO", taskType_CertDecision, TaskTypeColIndex);

				certDecisionStatus = fnpFetchFromTable("TasksTable_EditWO", RowNoForCertDecison, TaskStatusColIndex);
				fnpMymsg("certDecisionStatus ----" + certDecisionStatus);

				if (certDecisionStatus.equalsIgnoreCase("Ready")) {
					fnpMymsg("Cert Decision status has become to 'Ready' state. ");
					break;
				} else {
					Thread.sleep(1000 * 20 * 1);
					driver.navigate().refresh();

					Thread.sleep(8000);

					if (!fnpCheckElementPresenceImmediately("Task_ShowAllLink")) {
						fnpCommonClickTaskTab();
					}

					fnpMymsg("we are in  waiting loop for cert become in Ready ----" + waitCount);
				}

				if (waitCount > 60) {

					fnpMymsg("Script waited for approx. 30 min to become 'READY' state of cert decision but it is still showing '" + certDecisionStatus + "' .");
					throw new Exception("Script waited for approx. 30 min to become 'READY' state of cert decision but it is still showing '" + certDecisionStatus + "' .");

				}

			}

			BS11.Custom_WO_Certification_Decision_Task();

		} catch (Throwable t) {
			fail = true;
			isTestPass = false;
			fnpMymsg("  Second_Certification_Decision_Task  method is failed . " + " . Error is ---" + t.getMessage());
			fnpDesireScreenshot("Second_Certification_Decision_Task");
			String stackTrace = Throwables.getStackTraceAsString(t);
			String errorMsg = t.getMessage();
			errorMsg = errorMsg + "\n\n\n\n Second_Certification_Decision_Task  method is failed   .See screenshot 'Second_Certification_Decision_Task'\n\n" + stackTrace;
			Exception c = new Exception(errorMsg);
			ErrorUtil.addVerificationFailure(c);

		}

	}
	
	
	

	
	@Test(priority = 24, dependsOnMethods = { "Second_Certification_Decision_Task" })
	public void CLEANUP_WO_AUTOMATION_DATA() throws Throwable {
		
/*		BS05 = new NewNew_InProc_Completed_No_Fac();		
		BS05.CLEANUP_WO_AUTOMATION_DATA();
		*/

		
		fnpMymsg(" **************************************************************");
		fnpMymsg("ECAP.CLEANUP_WO_AUTOMATION_DATA");
		try {
			if (env.equalsIgnoreCase("Test") ) {
				fnpCallProcedure_DELETE_WO_AUTOMATION_DATA_WaterProfile(FirstWoNo);
				fnpCallProcedure_DELETE_WO_AUTOMATION_DATA_WaterProfile(SecondWoNo);
			}else{
				//throw new SkipException( " \n\n Skipping this as this procedure is only available in TEST environment" );
			}

		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "  CLEANUP_WO_AUTOMATION_DATA  method is failed . ", "CLEANUP_WO_AUTOMATION_DATA_Failed");
		}


	}
	
	
	
	

	@AfterTest
	public void reportTestResult() throws Throwable {

		int rowNum = Work_Order_suitexls.getCellRowNum("Test Cases", "TCID", this.getClass().getSimpleName());
		Work_Order_suitexls.setCellData("Test Cases", "Last_Execution", rowNum, fnReturnDateWithTimeForExcel());

		if (isTestPass) {
			TestUtil.reportDataSetResult(Work_Order_suitexls, "Test Cases", TestUtil.getRowNum(Work_Order_suitexls, this.getClass().getSimpleName()), "PASS");
			Work_Order_suitexls.setCellData("Test Cases", "Last_Successful_Execution", rowNum, fnReturnDateWithTimeForExcel());
			fnpElapsedTime(currentSuiteName, this.getClass().getSimpleName(), currentScriptCode, "PASS");
		} else {
			TestUtil.reportDataSetResult(Work_Order_suitexls, "Test Cases", TestUtil.getRowNum(Work_Order_suitexls, this.getClass().getSimpleName()), "FAIL");
			fnpElapsedTime(currentSuiteName, this.getClass().getSimpleName(), currentScriptCode, "FAIL");
		}
		fnpMymsg("=========================================================================================================================================");

		hashXlData.clear();

		fnpCommonCloseBrowsersAndKillProcess();

	}

}
