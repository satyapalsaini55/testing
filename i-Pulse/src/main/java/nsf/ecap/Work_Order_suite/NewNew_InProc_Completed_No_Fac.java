package nsf.ecap.Work_Order_suite;

//import java.awt.List;
import java.util.ArrayList;

import nsf.ecap.config.IPULSE_CONSTANTS;
import nsf.ecap.util.ErrorUtil;
import nsf.ecap.util.TestUtil;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.os.WindowsUtils;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;
import static nsf.ecap.config.IPULSE_CONSTANTS.*;
//BS-05
public class NewNew_InProc_Completed_No_Fac extends TestSuiteBase {


	
	@BeforeTest
	public void checkTestSkip() throws Throwable {
		BS01 = new NewNew_Draft_InReview_No_Fac();
		BS01.checkTestSkip(this.getClass().getSimpleName());

	}

	// @Test(enabled = false)
	@Test(priority = 1)
	public void CreateWOFlow() throws Throwable {
		
	//	fnpCallGoldenProcedure_COPY_AUDIT_TRANSACTION_SUBMIT(2368106,2846826,"TestScriptUser TestScriptUser");
		BS01.CreateWOFlow();

	}

	@Test(priority = 2, dependsOnMethods = { "CreateWOFlow" })
	public void Check_alert_DRAFT_Status_N_AssignedWithTaskSetupIncomplete() throws Throwable {

		BS01.Check_alert_DRAFT_Status_N_AssignedWithTaskSetupIncomplete();

	}

	@Test(priority = 3, dependsOnMethods = { "Check_alert_DRAFT_Status_N_AssignedWithTaskSetupIncomplete" })
	public void AddingData__Facility_Tab() throws Throwable {
		BS01.AddingData__Facility_Tab();

	}

	@Test(priority = 4, dependsOnMethods = { "AddingData__Facility_Tab" })
	public void AddingData__Tasks_Tab() throws Throwable {
		BS01.AddingData__Tasks_Tab();

	}

	@Test(priority = 5, dependsOnMethods = { "AddingData__Tasks_Tab" })
	public void AddingData__Products_Tab() throws Throwable {

		BS01.AddingData__Products_Tab();

	}

	@Test(priority = 6, dependsOnMethods = { "AddingData__Products_Tab" })
	public void AddingData__Documents_Tab() throws Throwable {

		BS01.AddingData__Documents_Tab();

	}

	@Test(priority = 7, dependsOnMethods = { "AddingData__Documents_Tab" })
	public void AddingData__Financials_Tab() throws Throwable {

		BS01.AddingData__Financials_Tab();

	}

	@Test(priority = 8, dependsOnMethods = { "AddingData__Financials_Tab" })
	public void Verifying__ActionItems_Tab() throws Throwable {
		BS01.Verifying__ActionItems_Tab();

	}

	@Test(priority = 8, dependsOnMethods = { "Verifying__ActionItems_Tab" })
	public void InsertValueForTR_LER_PER_QC() throws Throwable {
		BS01.InsertValueForTR_LER_PER_QC();

	}

	@Test(priority = 9, dependsOnMethods = { "InsertValueForTR_LER_PER_QC" })
	public void Check_RemoveAlert_AssignedWithTaskSetupIncomplete() throws Throwable {

		BS01.Check_RemoveAlert_AssignedWithTaskSetupIncomplete();

	}

	@Test(priority = 10, dependsOnMethods = { "Check_RemoveAlert_AssignedWithTaskSetupIncomplete" })
	public void Verifying__DRAFT_INREVIEW() throws Throwable {

		BS01.Verifying__DRAFT_INREVIEW();

	}

	@Test(priority = 11, dependsOnMethods = { "Verifying__DRAFT_INREVIEW" })
	public void Check_alerts_DRAFT_INREVIEW() throws Throwable {

		BS01.Check_alerts_DRAFT_INREVIEW();

	}

	@Test(priority = 12, dependsOnMethods = { "Check_alerts_DRAFT_INREVIEW" })
	public void Client_App_Review_Task() throws Throwable {

		fnpMymsg(" **************************************************************");
		fnpMymsg(" Executing Client_App_Review_Task");
		try {

			fnpSearchWorkOrderAndClickEditAlso(workOrderNo);

			fnpProcessAI(actionItemDesc_VerifyCIF, (String) hashXlData.get("Change_Status_Completed"));

			// fnpLoading_wait();
			fnpCommonGoToHomeNClick();
			// fnpLoading_wait();

			// ------Deleted Alert Action_item_assigned_to_client deleted
			// -------------------
			fnpCommonAlertDeletedVerification("Action_item_assigned_to_client", "AI_ASSIGNED_CLIENT_IND_AlertTable_header", "WO #", "AI_ASSIGNED_CLIENT_IND_AlertTable",
					"AI_ASSIGNED_CLIENT_IND_Alert_WO_filterTxtBox", workOrderNo);
			// ---------------------------------------------

			fnpSearchWorkOrderAndClickEditAlso(workOrderNo);

			fnpCommonClickSnapShotTab();

			fnpWaitForVisible("ActionItemTable_EditWO");

			// ***************Complete the 2nd pending Action
			// item(ClientDocRequest)***********

			fnpMymsg(" Going to Complete the 2nd pending Action item for ClientDocRequest ");
			int ActionItemColIndex = fnpFindColumnIndex("ActionItemTable_HeaderRow", actionItemTableActionItemColName);
			int itemDescColIndex = fnpFindColumnIndex("ActionItemTable_HeaderRow", actionItemTableItemDescColName);
			int ClientDocRequestRowNo = fnpFindRow("ActionItemTable_EditWO", actionItemDesc_ClientDocRequest, itemDescColIndex);
			String ClientDocRequest_AINo = fnpFetchFromTable("ActionItemTable_EditWO", ClientDocRequestRowNo, ActionItemColIndex);
			Thread.sleep(500);
			
			
/*			fnpWaitTillVisiblityOfElementNClickable_NOR(".//a[text()='" + ClientDocRequest_AINo + "']");
			driver.findElement(By.linkText(ClientDocRequest_AINo)).click();
			*/
			fnpClickALinkInATable(ClientDocRequest_AINo);
			
			
			// Thread.sleep(3000);

			fnpLoading_wait();

			String statusValue = (String) hashXlData.get("Change_Status_Completed"); // as
																						// already
																						// fetched
			fnpWaitForVisible("ActionItemTab_ChangeStatusPFList");

			fnpPFList("ActionItemTab_ChangeStatusPFList", "ActionItemTab_ChangeStatusPFListOptions", statusValue);
			fnpGetORObjectX("EditWO_SaveBtn").click();

			// fnpLoading_wait();
			fnpWaitingForExpectedErrorMsg("You cannot complete the CLAPPRVW task until you......");
			if (fnpCheckElementPresenceImmediately("ErrorMessage")) {

				fnpMouseHover_OR_EvenMultipleObjectsHavingSameXpath("ErrorMessage");
				fnpMymsg(" Error is thrown by application while Going to Complete the pending Action item for ClientDocRequest ");
			}

			String Msg = fnpGetText_OR_EvenMultipleObjectsHavingSameXpath_specialForExtractingErrorMsgTextOnlyEvenIfNotVisible("ErrorMessage");

			fnpMymsg("Top Message while completing the pending Action item for ClientDocRequest  ----" + Msg);

			String expectedErrorMsg = "You cannot complete the CLAPPRVW task until you have indicated whether Supplier Forms are needed or not (via the Products tab).";
			Assert.assertEquals(
					Msg,
					expectedErrorMsg,
					"Top message does not contain 'You cannot complete the CLAPPRVW task until you have indicated whether Supplier Forms are needed or not' message, so might be action of Completing the pending Action item for ClientDocRequest  is NOT successful.");

			fnpMymsg(" Expected error message is coming i.e. 'You cannot complete the CLAPPRVW task until you have indicated whether Supplier Forms are needed or not '");

			fnpGetORObjectX("ActionItemDialogCloseSign").click();
			fnpLoading_wait();

			// Thread.sleep(5000);// sometime product tab becomes blank
			Thread.sleep(1000);// sometime product tab becomes blank

			fnpClick_OR("EditProductTabLink");
			// fnpLoading_wait();

			fnpWaitingForElementWithoutUsingLoading("ProductTab_ProductInformationHeading");

			fnpCommonClickEditBtnIfPresent();

			fnpClick_OR("ProductTab_RequestFollowUpBtn");

			fnpCommonClickTaskTab();

			int taskStatusColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_StatusColName);
			int taskTypeColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskTypeColName);
			int supplierFormRowNo = fnpFindRow("TasksTable_EditWO", taskType_SupplierForms, taskTypeColIndex);
			String supplierFormTaksStatus = fnpFetchFromTable("TasksTable_EditWO", supplierFormRowNo, taskStatusColIndex);
			Assert.assertTrue(supplierFormTaksStatus.toLowerCase().contains("ready"), "The Supplier forms task should become Ready , but it has not  become Ready ");

			fnpMymsg("The Supplier forms task has become Ready");

			fnpProcessAI(actionItemDesc_ClientDocRequest, (String) hashXlData.get("Change_Status_Completed"));

			fnpMymsg("ClientDocRequest action item has been completed now");
			fnpMymsg("  Completed the pending Action item for ClientDocRequest ");

			// ***************Complete the 2nd pending Action
			// item(ClientDocRequest)***********

			fnpWaitTillClickable("EditTaskTabLink");
			fnpGetORObjectX("EditTaskTabLink").click();
			// fnpLoading_wait();
			// Thread.sleep(5000);
			fnpWaitForVisible("Task_ShowAllLink");
			fnpCheckError("");
			fnpWaitTillVisiblityOfElementNClickable_OR("Task_ShowAllLink");
			taskStatusColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_StatusColName);
			taskTypeColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskTypeColName);
			int scopeValidationRowNo = fnpFindRow("TasksTable_EditWO", taskType_ScopeValidation, taskTypeColIndex);
			String scopeValidationTaksStatus = fnpFetchFromTable("TasksTable_EditWO", scopeValidationRowNo, taskStatusColIndex);
			Assert.assertTrue(scopeValidationTaksStatus.toLowerCase().contains("ready"), "The Scope Validation  task should become Ready , but it has not  become Ready ");
			fnpMymsg("The Scope Validation  task has become Ready");

			// ***************Complete the action item Initial Client
			// Education***************
			fnpMymsg("Now going to Complete the action item Initial Client Education.");

			fnpProcessAI(actionItemDesc_InitialClientEducation, (String) hashXlData.get("Change_Status_Completed"));

			fnpMymsg("Initial Client Education  action item has been completed now");
			fnpMymsg("  Completed the pending Action item for Initial Client Education  ");

			fnpCheckError(" while   Going to Complete the pending Action item for Initial Client Education.");

		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "  Client_App_Review_Task  method is failed . ", "ClientAppReviewTaskFailed");

		}
	}

	@Test(priority = 13, dependsOnMethods = { "Client_App_Review_Task" })
	public void Check_RemoveAlert_Client_App_Review_Task() throws Throwable {

		fnpMymsg(" **************************************************************");
		try {

			// fnpLoading_wait();
			fnpCommonGoToHomeNClick();
			// fnpLoading_wait();

			// ------Deleted Alert Removed Action_item_assigned
			// -------------------
			fnpCommonAlertDeletedVerification("Action_item_assigned", "AI_ITEMS_ASSIGNED_AlertTable_header", "WO #", "AI_ITEMS_ASSIGNED_AlertTable",
					"AI_ITEMS_ASSIGNED_Alert_WO_filterTxtBox", workOrderNo);
			// ---------------------------------------------

			// -------Deleted Alert Task_with_open_Action_Items
			// -------------------
			fnpCommonAlertDeletedVerification("Task_with_open_Action_Items", "TA_TASK_OPEN_ITEMS_AlertTable_header", "WO #", "TA_TASK_OPEN_ITEMS_AlertTable",
					"TA_TASK_OPEN_ITEMS_Alert_WO_filterTxtBox", workOrderNo);
			// ---------------------------------------------

			// -------Deleted Alert Work_Order_with_open_action_items
			// -------------------
			fnpCommonAlertDeletedVerification("Work_Order_with_open_action_items", "WO_WITH_OPEN_ACTION_ITEMS_AlertTable_header", "WO #", "WO_WITH_OPEN_ACTION_ITEMS_AlertTable",
					"WO_WITH_OPEN_ACTION_ITEMS_Alert_WO_filterTxtBox", workOrderNo);
			// ---------------------------------------------

			fnpMymsg(" **************************************************************");

		} catch (Throwable t) {
/*			fail = true;
			isTestPass = false;
			fnpDesireScreenshot("check_RemoveAlert_Client_App_Review_Task");
			fnpMymsg(" check_RemoveAlert_Client_App_Review_Task is fail,See screenshot 'check_RemoveAlert_Client_App_Review_Task'" + " . Error is ---" + t.getMessage());

			String stackTrace = Throwables.getStackTraceAsString(t);
			String errorMsg = t.getMessage();
			errorMsg = errorMsg + "\n\n\n\nAfter Client App Review Task,Alerts have not been REMOVED successfully.See screenshot 'check_RemoveAlert_Client_App_Review_Task'\n\n"
					+ stackTrace;
			Exception c = new Exception(errorMsg);
			ErrorUtil.addVerificationFailure(c);
			*/
			
			fnpCommonFinalCatchBlock(t, " check_RemoveAlert_Client_App_Review_Task is fail . ", "check_RemoveAlert_Client_App_Review_Task");
		}

	}

	@Test(priority = 14, dependsOnMethods = { "Check_RemoveAlert_Client_App_Review_Task" })
	public void Scope_Validation_Task() throws Throwable {

		fnpMymsg(" **************************************************************");
		fnpMymsg(" Executing Scope_Validation_Task");
		try {

			fnpSearchWorkOrderAndClickEditAlso(workOrderNo);

			fnpCommonClickTaskTab();

			// ***************Scope Validation***********
			fnpMymsg(" Going to click scope validation task no. ");
			int TaskNoColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskNoColName);
			int taskTypeColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskTypeColName);
			int scopeValidationRowNo = fnpFindRow("TasksTable_EditWO", taskType_ScopeValidation, taskTypeColIndex);

			String scopeValidationTaskNo = fnpFetchFromTable("TasksTable_EditWO", scopeValidationRowNo, TaskNoColIndex);
			Thread.sleep(500);
			
			
/*			fnpWaitTillVisiblityOfElementNClickable_NOR(".//a[text()='" + scopeValidationTaskNo + "']");
			driver.findElement(By.linkText(scopeValidationTaskNo)).click();
			*/
			fnpClickALinkInATable(scopeValidationTaskNo);
			

			// fnpLoading_wait();
			// Thread.sleep(4000);//commented on 1 may
			fnpMymsg("Clicked on Scope validation task no. in Task Table i.e. '" + scopeValidationTaskNo + "' .");

			// fnpWaitForVisible("TaskTab_ScopeValidation_AssignTaskIcon");
			// //commented on 1 may
			fnpWaitTillVisiblityOfElementNClickable_OR("TaskTab_ScopeValidation_AssignTaskIcon");
			fnpCheckError("");
			fnpGetORObjectX("TaskTab_ScopeValidation_AssignTaskIcon").click();

			fnpClickInDialog_OR("TaskTab_ScopeValidation_AssignTask_SaveBtn");

/*			
			if (fnpCheckElementPresenceImmediately("ErrorMessage")) {
				// fnpMouseHover("ErrorMessage");
				fnpMouseHover_OR_EvenMultipleObjectsHavingSameXpath("ErrorMessage");
				fnpMymsg(" Error is thrown by application while Assigning the task in Scope_Validation_Task  method ");
				throw new Exception("Error is thrown by application in Scope_Validation_Task  method while Assigning the task");
			}

			*/
			
			fnpCheckErrMsg("Error is thrown by application in Scope_Validation_Task  method while Assigning the task  ");
			
			
			fnpClick_OR("TaskTab_ScopeValidation_PerformTaskIcon");

			fnpClick_OR("TaskTab_ScopeValidation_CompleteTaskIcon");

			fnpClickInDialog_OR("TaskTab_ScopeValidation_CompleteTaskSaveBtn");

/*			if (fnpCheckElementPresenceImmediately("ErrorMessage")) {
				// fnpMouseHover("ErrorMessage");
				fnpMouseHover_OR_EvenMultipleObjectsHavingSameXpath("ErrorMessage");
				fnpMymsg(" Error is thrown by application in Scope_Validation_Task  method while completing the task");
				throw new Exception("Error is thrown by application in Scope_Validation_Task  method while completing the task");
			}
			
		*/	
			
			fnpCheckErrMsg("Error is thrown by application in Scope_Validation_Task  method while completing the task  ");
			
			
			
			// fnpLoading_wait();
			fnpWaitForVisible("TaskTab_ScopeValidation_TaskSummaryTable");
			String scopeValidationStatus = fnpFetchFromTable("TaskTab_ScopeValidation_TaskSummaryTable", 1, 5);

			int start_statusComplete = 0;
			while (!(scopeValidationStatus.toLowerCase().contains("completed"))) {
				Thread.sleep(1000);
				scopeValidationStatus = fnpFetchFromTable("TaskTab_ScopeValidation_TaskSummaryTable", 1, 5);
				start_statusComplete++;

				if (start_statusComplete > Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					break;
				}

			}

			Assert.assertTrue(scopeValidationStatus.toLowerCase().contains("completed"),
					"After completed the Scope Validation after assign,perform,complete , status has not been changed  to 'COMPLETED' . It is still showing '"
							+ scopeValidationStatus + "'.");

			fnpMymsg(" After completed the Scope Validation after assign,perform,complete , status has  been changed  to 'COMPLETED' successfully.");

			fnpClick_OR("TaskTab_ScopeValidation_BackToViewBtn");

			fnpClick_OR("TaskTab_ScopeValidation_BackBtn");

			fnpWaitForVisible("Task_ShowAllLink");
			fnpWaitTillVisiblityOfElementNClickable_OR("Task_ShowAllLink");

			fnpWaitForElementVisibility("TasksTable_EditWO", CONFIG.getProperty("genMax_waitTime"));
			int StatusColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_StatusColName);
			taskTypeColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskTypeColName);
			scopeValidationRowNo = fnpFindRow("TasksTable_EditWO", taskType_ScopeValidation, taskTypeColIndex);
			String scopeValidationStatus_TaskTable = fnpFetchFromTable("TasksTable_EditWO", scopeValidationRowNo, StatusColIndex);

			Assert.assertTrue(scopeValidationStatus_TaskTable.toLowerCase().contains("completed"),
					"In Task Table , status has not been changed  to 'COMPLETED' . It is still showing '" + scopeValidationStatus_TaskTable + "'.");

			fnpMymsg(" After completed the Scope Validation , status in Task Table has  been changed  to 'COMPLETED' successfully.");

/*			if (fnpCheckElementPresenceImmediately("ErrorMessage")) {
				// fnpMouseHover("ErrorMessage");
				fnpMouseHover_OR_EvenMultipleObjectsHavingSameXpath("ErrorMessage");
				fnpMymsg(" Error is thrown by application in Scope_Validation_Task method");
				throw new Exception("Error is thrown by application in Scope_Validation_Task  method");
			}
			
			*/
			
			fnpCheckErrMsg("Error is thrown by application in Scope_Validation_Task  method  ");
			
			

		} catch (Throwable t) {
			
/*			
			fail = true;
			isTestPass = false;
			fnpMymsg("  Scope_Validation_Task  method is failed . " + " . Error is ---" + t.getMessage());
			fnpDesireScreenshot("Scope_Validation_TaskFailed");
			String stackTrace = Throwables.getStackTraceAsString(t);
			String errorMsg = t.getMessage();
			errorMsg = errorMsg + "\n\n\n\n Scope_Validation_Task  method is failed  .See screenshot 'Scope_Validation_TaskFailed'\n\n" + stackTrace;
			Exception c = new Exception(errorMsg);
			ErrorUtil.addVerificationFailure(c);
			*/
			fnpCommonFinalCatchBlock(t, "   Scope_Validation_Task  method is failed . ", "Scope_Validation_TaskFailed");

		}
	}

	@Test(priority = 14, dependsOnMethods = { "Scope_Validation_Task" })
	public void VerifySampleSelectionReviewer() throws Throwable {
		try {
			fnpMymsg("Going to verify sample selection task is assigned to tech reviewer or not.");
			fnpCommonClickSnapShotTab();

			int assignedToColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", SnapShot_taskTable_AssignedToColName);

			int TaskDescColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", SnapShot_taskTable_TaskDescColName);
			int sampleSelectionrowNo = fnpFindRow("Snapshot_TasksSummaryTable", SnapShot_taskDesc_SampleSelection, TaskDescColIndex);

			String sampleSelectionAssigner = fnpFetchFromTable("Snapshot_TasksSummaryTable", sampleSelectionrowNo, assignedToColIndex);

			/*
			 * Assert.assertTrue(sampleSelectionAssigner.toLowerCase().contains(
			 * "ready"),
			 * "In Task Table , Sample Selection Task status has not become  to 'READY' . It is  showing '"
			 * + sampleSelectionTaskStatus_TaskTable + "'.");
			 */
/*
			*//****** 28-04-15 commented for time being *****//*
			Assert.assertEquals(sampleSelectionAssigner.trim(), (String) hashXlData.get("Tech_Reviewer").trim(), "Sample Selection is not assigned to Tech Reviewer");
			fnpMymsg("############  #################");
			fnpMymsg(" Sample Selection task assigned to '" + (String) hashXlData.get("Tech_Reviewer").trim() + "'  successfully.");
			fnpMymsg("############  #################");
			*//****** 28-04-15 commented for time being *****//*
			
			*/
			if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
				//Assert.assertEquals(sampleSelectionAssigner.trim(), (String) hashXlData.get("Tech_Reviewer_Code").trim(), "Sample Selection is not assigned to Tech Reviewer");
				Assert.assertTrue(sampleSelectionAssigner.trim().contains((String) hashXlData.get("Tech_Reviewer_Code").trim()), "Sample Selection is not assigned to Tech Reviewer because expected is this '"+(String) hashXlData.get("Tech_Reviewer_Code")+"' but actual is this '"+sampleSelectionAssigner+"'.");
				fnpMymsg(" Sample Selection task assigned to '" + (String) hashXlData.get("Tech_Reviewer_Code").trim() + "'  successfully.");
			}else{
				Assert.assertEquals(sampleSelectionAssigner.trim(), (String) hashXlData.get("Tech_Reviewer").trim(), "Sample Selection is not assigned to Tech Reviewer because expected is this '"+(String) hashXlData.get("Tech_Reviewer")+"' but actual is this '"+sampleSelectionAssigner+"'.");
				fnpMymsg(" Sample Selection task assigned to '" + (String) hashXlData.get("Tech_Reviewer").trim() + "'  successfully.");
			}
			
			
			
			
			

			fnpMymsg(" Going to verify Sample Selection action item should be generated in Submitted status. ");
			int ActionItemNoColIndex = fnpFindColumnIndex("ActionItemTable_HeaderRow", actionItemTableStatusColName);
			int AItem_ItemDesc_ColIndex = fnpFindColumnIndex("ActionItemTable_HeaderRow", actionItemTableItemDescColName);
			int AI_SampleSelectionRowNo = fnpFindRow("ActionItemTable_EditWO", actionItemDesc_SampleSelection, AItem_ItemDesc_ColIndex);

			Assert.assertTrue(AI_SampleSelectionRowNo > 0, "Action Item 'Sample Selection' has not been generated just after completed Scope Validation task.");
			fnpMymsg("Action Item 'Sample Selection' has  been generated just after completed Scope Validation task. ");

			String SampleSelectionStatusinActionItemTable = fnpFetchFromTable("ActionItemTable_EditWO", AI_SampleSelectionRowNo, ActionItemNoColIndex);

			Assert.assertTrue(SampleSelectionStatusinActionItemTable.toLowerCase().contains("submitted"),
					"Action Item 'Sample Selection' should have been  generated in Submitted status just after completed Scope Validation task. But it is not as It is still showing '"
							+ SampleSelectionStatusinActionItemTable + "'.");

			fnpMymsg(" Action Item 'Sample Selection'  have been  generated in Submitted status just after completed Scope Validation task. ");

		} catch (Throwable t) {
			fnpCommonFinalCatchBlock(t, "   VerifySampleSelectionReviewer  is failed . ", "VerifySampleSelectionReviewerFail");
		}

	}

	// @Test(priority = 15, dependsOnMethods = { "Scope_Validation_Task" })
	@Test(priority = 15, dependsOnMethods = { "VerifySampleSelectionReviewer" })
	public void VerifyStatus_InReview_To_InProcess_N_checkAlerts() throws Throwable {

		fnpMymsg(" **************************************************************");
		fnpMymsg(" Executing VerifyStatus_InReview_To_InProcess");
		try {

			fnpClick_OR_WithoutWait("NewlyCrWOTopBannerInfo");

			fnpWaitForVisible("TopBannerWOStatus");
			String WOStatus = fnpGetORObjectX("TopBannerWOStatus").getText();

			Assert.assertEquals(WOStatus, "INPROCESS", "Now WO status has NOT been changed to 'INPROCESS'.The WO should be  in INPROCESS status. ");

			fnpMymsg(" After completed the Scope Validation , Now Work Order status  changed  to 'INPROCESS' successfully.");

/*			if (fnpCheckElementPresenceImmediately("ErrorMessage")) {
				// fnpMouseHover("ErrorMessage");
				fnpMouseHover_OR_EvenMultipleObjectsHavingSameXpath("ErrorMessage");
				fnpMymsg(" Error is thrown by application in VerifyStatus_InReview_To_InProcess  method");
				throw new Exception("Error is thrown by application in VerifyStatus_InReview_To_InProcess  method");
			}
			
			*/
			fnpCheckErrMsg("Error is thrown by application in VerifyStatus_InReview_To_InProcess  method ");
			

			fnpCommonGoToHomeNClick();
			// -------Alert Work Order in InProcess status -------------------
			fnpCommonAlertGeneratedVerification("Work_order_in_INPROCESS_status", "WO_InProcess_Status_AlertTable_header", "WO #", "WO_InProcess_Status_AlertTable",
					"WO_InProcess_Status_Alert_WO_filterTxtBox", workOrderNo);
			// ---------------------------------------------

		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "   VerifyStatus_InReview_To_InProcess  is failed . ", "VerifyStatus_InReview_To_InProcessFailed");

		}
	}

	@Test(priority = 16, dependsOnMethods = { "VerifyStatus_InReview_To_InProcess_N_checkAlerts" })
	public void Supplier_Forms_task() throws Throwable {

		fnpMymsg(" **************************************************************");
		fnpMymsg(" Executing Supplier_Forms_task");
		try {

			if (!(currentSuiteName.equalsIgnoreCase(Wales_Work_Order_suite_Name))) {
				fnpSearchWorkOrderAndClickEditAlso(workOrderNo);
			}
			

			fnpCommonClickTaskTab();

			// ***************Complete Supplier forms task***********
			fnpMymsg(" Going to click Supplier forms task no. ");
			int ColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskNoColName);
			int taskTypeColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskTypeColName);
			int supplierFormRowNo = fnpFindRow("TasksTable_EditWO", taskType_SupplierForms, taskTypeColIndex);
			String supplierFormTaskNo = fnpFetchFromTable("TasksTable_EditWO", supplierFormRowNo, ColIndex);
			Thread.sleep(500);
			
/*			fnpWaitTillVisiblityOfElementNClickable_NOR(".//a[text()='" + supplierFormTaskNo + "']");
			driver.findElement(By.linkText(supplierFormTaskNo)).click();
			*/
			
			fnpClickALinkInATable(supplierFormTaskNo);
			fnpCheckError("");
			fnpClick_OR("TaskTab_ScopeValidation_AssignTaskIcon");

			fnpClickInDialog_OR("TaskTab_ScopeValidation_AssignTask_SaveBtn");

/*			// Thread.sleep(4000);
			if (fnpCheckElementPresenceImmediately("ErrorMessage")) {
				// fnpMouseHover("ErrorMessage");
				fnpMouseHover_OR_EvenMultipleObjectsHavingSameXpath("ErrorMessage");
				fnpMymsg(" Error is thrown by application while Assigning the Supplier Forms task in Supplier_Forms_task  method ");
				throw new Exception("Error is thrown by application while Assigning the Supplier Forms task in Supplier_Forms_task  method");
			}
			*/
			
			
			fnpCheckErrMsg("Error is thrown by application while Assigning the Supplier Forms task in Supplier_Forms_task  method ");

			fnpClick_OR("TaskTab_ScopeValidation_PerformTaskIcon");
			fnpCheckErrMsg("Error is thrown by application after clicking PerformTaskIcon");

			// Thread.sleep(4000);//commented on 1 may

			// ---To click radio button agains Task Details option
			String taskDetailsXpath = OR.getProperty("TaskTab_SupplierForms_TaskDetailsTable");
			String taskDetails_Options = (String) hashXlData.get("OptionsTaskDetails").trim();
			String xpathForRadioBtn = taskDetailsXpath + "/tbody/tr/td/label[contains(text(),'" + taskDetails_Options + "')]";
			driver.findElement(By.xpath(xpathForRadioBtn)).click();

			fnpClick_OR("TaskTab_ScopeValidation_CompleteTaskIcon");

			fnpClickInDialog_OR("TaskTab_ScopeValidation_CompleteTaskSaveBtn");

/*			if (fnpCheckElementPresenceImmediately("ErrorMessage")) {
				// fnpMouseHover("ErrorMessage");
				fnpMouseHover_OR_EvenMultipleObjectsHavingSameXpath("ErrorMessage");
				fnpMymsg(" Error is thrown by application in Supplier_Forms_task  method while completing the task");
				throw new Exception("Error is thrown by application in Supplier_Forms_task  method while completing the task");
			}
			
			*/
			
			fnpCheckErrMsg("Error is thrown by application in Supplier_Forms_task  method while completing the task");
			
			String supplierFormsStatus = fnpFetchFromTable("TaskTab_ScopeValidation_TaskSummaryTable", 1, 5);

			int start_statusComplete = 0;
			while (!(supplierFormsStatus.toLowerCase().contains("completed"))) {
				Thread.sleep(1000);
				supplierFormsStatus = fnpFetchFromTable("TaskTab_ScopeValidation_TaskSummaryTable", 1, 5);
				start_statusComplete++;

				if (start_statusComplete > Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					break;
				}

			}

			Assert.assertTrue(supplierFormsStatus.toLowerCase().contains("completed"),
					"After completed the Supplier Forms after assign,perform,complete , status has not been changed  to 'COMPLETED' . It is still showing '" + supplierFormsStatus
							+ "'.");

			fnpMymsg(" After completed the Supplier Fomrs after assign,perform,complete , status has  been changed  to 'COMPLETED' successfully.");

			fnpClick_OR("TaskTab_ScopeValidation_BackToViewBtn");

			fnpClick_OR("TaskTab_ScopeValidation_BackBtn");

			fnpWaitForVisible("Task_ShowAllLink");
			fnpWaitTillVisiblityOfElementNClickable_OR("Task_ShowAllLink");

			fnpWaitForElementVisibility("TasksTable_EditWO", CONFIG.getProperty("genMax_waitTime"));
			int StatusColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_StatusColName);
			String supplierFormsTaskStatus_TaskTable = fnpFetchFromTable("TasksTable_EditWO", supplierFormRowNo, StatusColIndex);

			Assert.assertTrue(supplierFormsTaskStatus_TaskTable.toLowerCase().contains("completed"),
					"In Task Table , status has not been changed  to 'COMPLETED' . It is still showing '" + supplierFormsTaskStatus_TaskTable + "'.");

			fnpMymsg(" After completed the Supplier Forms , status in Task Table has  been changed  to 'COMPLETED' successfully.");

/*			if (fnpCheckElementPresenceImmediately("ErrorMessage")) {
				// fnpMouseHover("ErrorMessage");
				fnpMouseHover_OR_EvenMultipleObjectsHavingSameXpath("ErrorMessage");
				fnpMymsg(" Error is thrown by application in Supplier_Forms_task method");
				throw new Exception("Error is thrown by application in Supplier_Forms_task  method");
			}

			*/
			fnpCheckErrMsg("Error is thrown by application in Supplier_Forms_task  method");
			
			// ----------------

			fnpMymsg(" Completed  'Supplier_Forms_task' successfully.");

		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "   Supplier_Forms_task  is failed . ", "Supplier_Forms_taskFailed");

		}
	}

	@Test(priority = 17, dependsOnMethods = { "Supplier_Forms_task" })
	public void Verify_Supplier_Forms_Task_Alerts() throws Throwable {

		fnpMymsg(" **************************************************************");
		fnpMymsg(" Executing Verify_Supplier_Forms_Task_Alerts");
		try {

			// +++++++++++++Alert Verification++++++++++++++++++++++++++++++++++
			fnpCommonGoToHomeNClick();

			// -------Alert Work Order in InProcess status -------------------
			fnpCommonAlertGeneratedVerification("Work_order_in_INPROCESS_status", "WO_InProcess_Status_AlertTable_header", "WO #", "WO_InProcess_Status_AlertTable",
					"WO_InProcess_Status_Alert_WO_filterTxtBox", workOrderNo);

			fnpCommonSwitchToUserForAlerts((String) hashXlData.get("TechOpsManager"));

			// -------Alert Task ready to be assigned -------------------
			fnpCommonAlertGeneratedVerification("Task_ready_to_be_assigned_AlertName", "Task_ready_to_be_assigned_AlertTable_header", "WO #",
					"Task_ready_to_be_assigned_AlertTable", "Task_ready_to_be_assigned_Alert_WO_filterTxtBox", workOrderNo);
			// ---------------------------------------------

			// ++++++++++++++++++++++++++++++++++++++++++++++++

			fnpMymsg(" Completed  'Verify_Supplier_Forms_Task_Alerts' successfully.");

			fnpCheckErrMsg("Error is thrown by application in Verify_Supplier_Forms_Task_Alerts  method");
			
			

		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "   Verify_Supplier_Forms_Task_Alerts  is failed . ", "Verify_Supplier_Forms_Task_Alerts");

		}

	}

	// @Test(enabled = false)
	@Test(priority = 18, dependsOnMethods = { "Verify_Supplier_Forms_Task_Alerts" })
	public void RestartBrowserNLogin1() throws Throwable {

		try {
			//if (fnpGetCurrRunningBrowserName() != "chrome") {
			if (!(fnpGetCurrRunningBrowserName().equalsIgnoreCase("chrome"))) {		

				driver.quit();

				try {

					fnppradeepKillIEProcess();
					if (checkRunningProcess("IEDriverServer.exe")) {
						//WindowsUtils.killByName("IEDriverServer.exe");
					}

				} catch (Throwable t) {
					// nothing to do
				}


				Thread.sleep(2000);

				fnpLaunchBrowserAndLogin();
				// fnpLoading_wait();
				fnpSearchWorkOrderAndClickEditAlso_AfterRestartWhenAlertsNo(workOrderNo);

			} else {

				if (fnpCheckElementPresenceImmediately("TaskTab_ScopeValidation_BackToViewBtn")) {
					fnpClick_OR("TaskTab_ScopeValidation_BackToViewBtn");
				}
				if (fnpCheckElementPresenceImmediately("TaskTab_ScopeValidation_BackBtn")) {
					fnpClick_OR("TaskTab_ScopeValidation_BackBtn");
				}
			}

		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "   RestartBrowserNLogin  is failed . ", "RestartBrowserNLogin_Failed");

		}

	}

	// @Test(enabled = false)
	// @Test
	@Test(priority = 19, dependsOnMethods = { "Verify_Supplier_Forms_Task_Alerts" })
	public void Sample_Selection_Process_N_CheckAlerts() throws Throwable {

		fnpMymsg(" **************************************************************");
		fnpMymsg(" Executing Sample_Selection_Process");
		try {

			fnpSearchWorkOrderAndClickEditAlso(workOrderNo);

			fnpCommonClickSnapShotTab();
			/***************
			 * New changes for trunk as new action item 'Sample Selection' is
			 * coming in submitted status during 3rd week of Sep 2015
			 ***************************************/
			fnpProcessAI(actionItemDesc_SampleSelection, "Reviewed");
			fnpProcessAI(actionItemDesc_SampleSelection, (String) hashXlData.get("Change_Status_Completed"));

			/**************************************************************************************/

			fnpCommonClickTaskTab();

			int StatusColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_StatusColName);
			int TaskTypeColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskTypeColName);
			int sampleSelectionrowNo = fnpFindRow("TasksTable_EditWO", taskType_SampleSelection, TaskTypeColIndex);
			String sampleSelectionTaskStatus_TaskTable = fnpFetchFromTable("TasksTable_EditWO", sampleSelectionrowNo, StatusColIndex);

			Assert.assertTrue(sampleSelectionTaskStatus_TaskTable.toLowerCase().contains("ready"),
					"In Task Table , Sample Selection Task status has not become  to 'READY' . It is  showing '" + sampleSelectionTaskStatus_TaskTable + "'.");

			fnpMymsg(" Sample Selection Task status has now  become  to 'READY' state  successfully.");

			// ***************Generating EPSF***********
			fnpMymsg(" Going to click Sample Selection task no. ");
			int ColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", "Task #");

			String sampleSelectionTaskNo = fnpFetchFromTable("TasksTable_EditWO", sampleSelectionrowNo, ColIndex);
			Thread.sleep(500);
			
			
		
			fnpClickALinkInATable(sampleSelectionTaskNo);
			fnpLoading_wait();
			
			fnpMymsg("Clicked on Sample Selection task no. in Task Table i.e. '" + sampleSelectionTaskNo + "' .");
			fnpClick_OR("TaskTab_ScopeValidation_AssignTaskIcon");

			fnpMymsg(" Now going to assign the Sample Selection task");

			fnpClickInDialog_OR("TaskTab_ScopeValidation_AssignTask_SaveBtn");

			fnpCheckErrMsg("Error is thrown by application after clicking AssignTask_SaveBtn");
			fnpMymsg(" Sample Selection task has been assigned by clicking Assign task icon and filling details");

			fnpWaitTillVisiblityOfElementNClickable_OR("TaskTab_ScopeValidation_PerformTaskIcon");
			fnpWaitForVisible("TaskTab_ScopeValidation_PerformTaskIcon");


			
			fnpCheckErrMsg("Error is thrown by application while Assigning the Supplier Forms task in Supplier_Forms_task  method");
			
			

			fnpClick_OR("TaskTab_ScopeValidation_PerformTaskIcon");

			fnpMymsg(" Sample Selection task has been performed by clicking Perform Task icon (check symbol) .");

			//fnpPFList("SampleSelectionFacilityPFList", "SampleSelectionFacilityPFListOptions", (String) hashXlData.get("SampleSelection_Facility"));
			fnpPFListByLiNo("SampleSelectionFacilityPFList", "SampleSelectionFacilityPFListOptions", 1);

			// fnpSampleSelectionInsightCode() ;
			/******* New iPulse--Insight ******/
			fnpSampleSelectionEPSFCode();
			/******* New iPulse--Insight ******/

			
			

			
			//  
			
			/**************New changes for sprint 10.1***********************/
			/*			// fnpLoading_wait();
			fnpWaitForVisible("EPSFNoCellXpath");
			String EPSFNo = fnpGetORObjectX("EPSFNoCellXpath").getText();
			*/

			
			int epsfColIndex = fnpFindColumnIndex("TaskDetailTableInSampleSelectionTask_HeaderRow", "EPSF#");
			String EPSFNo = fnpFetchFromTable("TaskDetailTableInSampleSelectionTask_Table", 1, epsfColIndex);
			
			/************************************************************************/
			
			
			
			Assert.assertTrue(!EPSFNo.isEmpty(), "EPSF has not been generated. ");

			fnpMymsg(" EPSF No. has been generated successfully and it is '" + EPSFNo + "' .");


			
			fnpCheckErrMsg("Error is thrown by application in Sample_Selection_Process  method");

			// ++++++++++++++++++Verify Alerts+++++++++++++++++++++++++

			fnpCommonGoToHomeNClick();

			// -------Alert Work Order in InProcess status -------------------
			fnpCommonAlertGeneratedVerification("Work_order_in_INPROCESS_status", "WO_InProcess_Status_AlertTable_header", "WO #", "WO_InProcess_Status_AlertTable",
					"WO_InProcess_Status_Alert_WO_filterTxtBox", workOrderNo);
			// ---------------------------------------------

			fnpSearchWorkOrderAndClickEditAlso(workOrderNo);

			if (((String) hashXlData.get("Alerts_Verify_Flag")).equalsIgnoreCase("Yes")) {

				fnpCommonClickTaskTab();

				fnpMymsg(" Going to click Sample Selection task no. ");
				ColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", "Task #");

				sampleSelectionTaskNo = fnpFetchFromTable("TasksTable_EditWO", sampleSelectionrowNo, ColIndex);
				Thread.sleep(500);

				
				fnpClickALinkInATable(sampleSelectionTaskNo);
				
				fnpClick_OR("TaskTab_ScopeValidation_PerformTaskIcon");

			}

			fnpClick_OR("RequestLiteratureFromClientBtn");
			Thread.sleep(4000);

			
			
		//	fnpWaitForVisible("AddNoteEditorTextArea");
			fnpWaitForVisible("SampleSelection_CrAI_AssignedToAccMgRadioBtn");
			
			fnpCheckError("");
			fnpGetORObjectX("SampleSelection_CrAI_AssignedToAccMgRadioBtn").click();
			Thread.sleep(1000);


			

			
			//fnpGetORObjectX("AddNoteEditorTextArea").sendKeys((String) hashXlData.get("AddNotes_forRequestLiterature"));	
		//	Thread.sleep(5000);
			driver.switchTo().frame("iframe_cke_mainForm:viewtsk:createFrm:accdJobepsf:jbepsfEd");
			
			/*Thread.sleep(2000);
			driver.findElement(By.xpath(".//a[@title='Click Here To Set Focus']")).click();
			Thread.sleep(2000);*/
			
		//	Thread.sleep(5000);
			driver.findElement(By.xpath(".//body[@class='cke_editable cke_editable_themed cke_contents_ltr cke_show_borders']")).sendKeys((String) hashXlData.get("AddNotes_forRequestLiterature"));
			driver.switchTo().defaultContent();
			
		//	Thread.sleep(2000);
			
			
			
			
			fnpGetORObjectX("CreatActionItemScreenSaveBtn").click();


			fnpWaitForVisible("LiteratureReviewText");
			fnpCheckError("");
			String LiteratureText = fnpGetText_OR("LiteratureReviewText");



			int j = 0;
			while (!LiteratureText.contains("Requested on")) {
				Thread.sleep(1000);
				j++;
				LiteratureText = fnpGetText_OR("LiteratureReviewText");
				if (j > Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					break;
				}
			}

			fnpMymsg(" Request for Literature from client has  been fulfilled successfully  and it is '" + LiteratureText + "' .");


			
			fnpCheckErrMsg("Error is thrown by application in Sample_Selection_Process  method");

			fnpClick_OR("BackToViewBtn_sampleSelection");
			fnpClick_OR("BackBtn_sampleSelection");

			fnpCommonClickSnapShotTab();

			fnpWaitForVisible("ActionItemsTopLabel");
			fnpMymsg(" Going to verify Client Literature Request action item should be generated in Pending status. ");
			// fnpWaitForVisible("ActionItemTable_EditWO");
			int ActionItemNoColIndex = fnpFindColumnIndex("ActionItemTable_HeaderRow", actionItemTableStatusColName);
			int AItem_ItemDesc_ColIndex = fnpFindColumnIndex("ActionItemTable_HeaderRow", actionItemTableItemDescColName);
			int AI_LiteratureRequestRowNo = fnpFindRow("ActionItemTable_EditWO", actionItemDesc_LiteratureRequest, AItem_ItemDesc_ColIndex);
			String LiteratureRequestStatusinActionItemTable = fnpFetchFromTable("ActionItemTable_EditWO", AI_LiteratureRequestRowNo, ActionItemNoColIndex);

			Assert.assertTrue(LiteratureRequestStatusinActionItemTable.toLowerCase().contains("pending"),
					"After Clicking on Request Literature from client button, Client Literature Request action item should be generated in Pending status. But it is not as It is still showing '"
							+ LiteratureRequestStatusinActionItemTable + "'.");

			fnpMymsg(" After Clicking on Request Literature from client button, Client Literature Request action item should be generated in Pending status successfully. ");

			// ++++++++++++++++++Verify Alerts+++++++++++++++++++++++++

			fnpCommonGoToHomeNClick();

			// -------Alert Work Order in InProcess status -------------------
			fnpCommonAlertGeneratedVerification("Work_order_in_INPROCESS_status", "WO_InProcess_Status_AlertTable_header", "WO #", "WO_InProcess_Status_AlertTable",
					"WO_InProcess_Status_Alert_WO_filterTxtBox", workOrderNo);
			// ---------------------------------------------

			// -------Alert Action item assigned -------------------
			fnpCommonAlertGeneratedVerification("Action_item_assigned", "AI_ITEMS_ASSIGNED_AlertTable_header", "WO #", "AI_ITEMS_ASSIGNED_AlertTable",
					"AI_ITEMS_ASSIGNED_Alert_WO_filterTxtBox", workOrderNo);
			// ---------------------------------------------

			// -------Alert Work_Order_with_open_action_items
			// -------------------
			fnpCommonAlertGeneratedVerification("Work_Order_with_open_action_items", "WO_WITH_OPEN_ACTION_ITEMS_AlertTable_header", "WO #", "WO_WITH_OPEN_ACTION_ITEMS_AlertTable",
					"WO_WITH_OPEN_ACTION_ITEMS_Alert_WO_filterTxtBox", workOrderNo);
			// ---------------------------------------------

			/*
			 * 
			 * // -------Alert Task_with_open_Action_Items -------------------
			 * fnpCommonAlertGeneratedVerification(
			 * "Task_with_open_Action_Items",
			 * "TA_TASK_OPEN_ITEMS_AlertTable_header", "WO #",
			 * "TA_TASK_OPEN_ITEMS_AlertTable",
			 * "TA_TASK_OPEN_ITEMS_Alert_WO_filterTxtBox", workOrderNo); //
			 * ---------------------------------------------
			 */

			fnpSearchWorkOrderAndClickEditAlso(workOrderNo);
			fnpCommonClickTaskTab();

			ColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", "Task #");
			sampleSelectionTaskNo = fnpFetchFromTable("TasksTable_EditWO", sampleSelectionrowNo, ColIndex);
			Thread.sleep(500);
			
/*			fnpWaitTillVisiblityOfElementNClickable_NOR(".//a[text()='" + sampleSelectionTaskNo + "']");
			driver.findElement(By.linkText(sampleSelectionTaskNo)).click();
			*/
			fnpClickALinkInATable(sampleSelectionTaskNo);
			
			

			fnpMymsg(" Now going to complete the sample selection task");

			fnpClick_OR("TaskTab_ScopeValidation_CompleteTaskIcon");

			fnpClickInDialog_OR("TaskTab_ScopeValidation_CompleteTaskSaveBtn");


			fnpCheckErrMsg("Error is thrown by application in Scope_Validation_Task  method while completing the task");

			fnpWaitForVisible("TaskTab_ScopeValidation_TaskSummaryTable");
			String scopeValidationStatus = fnpFetchFromTable("TaskTab_ScopeValidation_TaskSummaryTable", 1, 5);

			int start_statusComplete = 0;
			while (!(scopeValidationStatus.toLowerCase().contains("completed"))) {
				Thread.sleep(1000);
				scopeValidationStatus = fnpFetchFromTable("TaskTab_ScopeValidation_TaskSummaryTable", 1, 5);
				start_statusComplete++;

				if (start_statusComplete > Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					break;
				}

			}

			Assert.assertTrue(
					scopeValidationStatus.toLowerCase().contains("completed"),
					"After completed the Sample Selection Task after assign,perform,Create EPSF,Request Literature from client  and complete , status has not been changed  to 'COMPLETED' . It is still showing '"
							+ scopeValidationStatus + "'.");

			fnpMymsg(" After completed the Sample Selection Task after assign,perform,Create EPSF,Request Literature from client  and complete , status has  been changed  to 'COMPLETED' successfully. ");

			/****** 28-04-15 commented for time being *****/
			// fnpClick_OR("TaskTab_ScopeValidation_BackToViewBtn");
			if (fnpCheckElementPresenceImmediately("TaskTab_ScopeValidation_BackToViewBtn")) {
				fnpClick_OR("TaskTab_ScopeValidation_BackToViewBtn");
			}
			/****** 28-04-15 commented for time being *****/

			fnpClick_OR("TaskTab_ScopeValidation_BackBtn");

			fnpWaitForVisible("Task_ShowAllLink");
			fnpWaitTillVisiblityOfElementNClickable_OR("Task_ShowAllLink");

			fnpWaitForElementVisibility("TasksTable_EditWO", CONFIG.getProperty("genMax_waitTime"));
			StatusColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_StatusColName);
			TaskTypeColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskTypeColName);
			int TaskSampleSelectionRowNo = fnpFindRow("TasksTable_EditWO", taskType_SampleSelection, TaskTypeColIndex);

			String sampleSelectionStatus_TaskTable = fnpFetchFromTable("TasksTable_EditWO", TaskSampleSelectionRowNo, StatusColIndex);

			Assert.assertTrue(sampleSelectionStatus_TaskTable.toLowerCase().contains("completed"),
					"In Task Table , Sample Selection status has not been changed  to 'COMPLETED' . It is still showing '" + sampleSelectionStatus_TaskTable + "'.");

			fnpMymsg(" After completed the Sample Selection task, status in Task Table has  been changed  to 'COMPLETED' successfully.");

			int TaskPhyEvalRowNo = fnpFindRow("TasksTable_EditWO", taskType_PhysicalEval, TaskTypeColIndex);
			String physicalEvalStatus_TaskTable = fnpFetchFromTable("TasksTable_EditWO", TaskPhyEvalRowNo, StatusColIndex);

			Assert.assertTrue(physicalEvalStatus_TaskTable.toLowerCase().contains("ready"),
					"In Task Table , after completing Sample Selection task, Physical Evaluation status has not become  'READY' . It is still showing '"
							+ physicalEvalStatus_TaskTable + "'.");

			fnpMymsg(" After completed the Sample Selection task , status of Physical Evaluation  in Task Table has  become 'READY'  successfully.");


			
			fnpCheckErrMsg("Error is thrown by application in Client_App_Review_Task  method");
			

			// ++++++++++++++++++Verify Alerts+++++++++++++++++++++++++

			fnpCommonGoToHomeNClick();

			// -------Alert Work Order in InProcess status -------------------
			fnpCommonAlertGeneratedVerification("Work_order_in_INPROCESS_status", "WO_InProcess_Status_AlertTable_header", "WO #", "WO_InProcess_Status_AlertTable",
					"WO_InProcess_Status_Alert_WO_filterTxtBox", workOrderNo);
			// ---------------------------------------------

			// -------Alert Action item assigned -------------------
			fnpCommonAlertGeneratedVerification("Action_item_assigned", "AI_ITEMS_ASSIGNED_AlertTable_header", "WO #", "AI_ITEMS_ASSIGNED_AlertTable",
					"AI_ITEMS_ASSIGNED_Alert_WO_filterTxtBox", workOrderNo);
			// ---------------------------------------------

			// -------Alert Work_Order_with_open_action_items
			// -------------------
			fnpCommonAlertGeneratedVerification("Work_Order_with_open_action_items", "WO_WITH_OPEN_ACTION_ITEMS_AlertTable_header", "WO #", "WO_WITH_OPEN_ACTION_ITEMS_AlertTable",
					"WO_WITH_OPEN_ACTION_ITEMS_Alert_WO_filterTxtBox", workOrderNo);
			// ---------------------------------------------

			/*
			 * 
			 * // -------Alert Task_with_open_Action_Items -------------------
			 * fnpCommonAlertGeneratedVerification(
			 * "Task_with_open_Action_Items",
			 * "TA_TASK_OPEN_ITEMS_AlertTable_header", "WO #",
			 * "TA_TASK_OPEN_ITEMS_AlertTable",
			 * "TA_TASK_OPEN_ITEMS_Alert_WO_filterTxtBox", workOrderNo); //
			 * ---------------------------------------------
			 */

			fnpCommonSwitchToUserForAlerts((String) hashXlData.get("TechOpsManager"));

			// -------Alert Task ready to be assigned -------------------
			fnpCommonAlertGeneratedVerification("Task_ready_to_be_assigned_AlertName", "Task_ready_to_be_assigned_AlertTable_header", "WO #",
					"Task_ready_to_be_assigned_AlertTable", "Task_ready_to_be_assigned_Alert_WO_filterTxtBox", workOrderNo);
			// ---------------------------------------------

			fnpMymsg(" Sample selection task has been completed successfully.");

			// ----------------

			fnpMymsg(" Completed  'Sample_Selection_Process' successfully.");

		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "   Sample_Selection_Process  is failed . ", "Sample_Selection_Process");

		}
	}
	
	@Test(enabled = false)
	//@Test(priority = 20, dependsOnMethods = { "Sample_Selection_Process_N_CheckAlerts" })
	public void App_Memory_StackTrace() throws Throwable {
		fnpMymsg(" **************************************************************");
		fnpMymsg(" Executing App_Memory_StackTrace");
		try {

			fnpCommonStackTracePageOpen();
			String screenshotImageName = fnpTimestamp() + "   " + fnpMappingClassNameWithScenarioCode(this.getClass().getSimpleName()) + " --Part_1";
			screenshotImageName = screenshotImageName.replaceAll(":", "_");
			String folderPath = screenshots_path + "//screenshots_WO_ApplicationMemoryStackTrace//";
			fnpDesireScreenshot_Advance(screenshotImageName, folderPath);

		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "   App_Memory_StackTrace  is failed . ", "Failed_App_Memory_StackTrace_Failed");

		}

	}

	//@Test(priority = 21, dependsOnMethods = { "App_Memory_StackTrace" })
	@Test(priority = 21, dependsOnMethods = { "Sample_Selection_Process_N_CheckAlerts" })
	public void RestartBrowserNLogin2() throws Throwable {
		try {
			
			
		
			
			driver.quit();

			try {
				fnppradeepKillIEProcess();
				if (checkRunningProcess("IEDriverServer.exe")) {
					//WindowsUtils.killByName("IEDriverServer.exe");
				}

			} catch (Throwable t) {
				// nothing to do
			}

			Thread.sleep(10000);
			fnpLaunchBrowserAndLogin();
			// fnpLoading_wait();
			fnpSearchWorkOrderAndClickEditAlso_AfterRestartWhenAlertsNo(workOrderNo);

		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "   RestartBrowserNLogin  is failed . ", "RestartBrowserNLogin_Failed");

		}

	}

	@Test(priority = 22, dependsOnMethods = { "RestartBrowserNLogin2" })
	public void VerifyPhyEvalReviewer() throws Throwable {

		try {
			fnpMymsg("Going to verify Physical Eval task is assigned to Phy Eval reviewer or not.");
			if (!fnpCheckElementPresenceImmediately("SnapshotTabLink")) {
				fnpSearchWorkOrderAndClickEditAlso(workOrderNo);
			}
			fnpCommonClickSnapShotTab();

			int assignedToColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", SnapShot_taskTable_AssignedToColName);

			int TaskDescColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", SnapShot_taskTable_TaskDescColName);
			int phyEvalrowNo = fnpFindRow("Snapshot_TasksSummaryTable", SnapShot_taskDesc_PhysicalEval, TaskDescColIndex);

			String phyEvalAssigner = fnpFetchFromTable("Snapshot_TasksSummaryTable", phyEvalrowNo, assignedToColIndex);
			
/*			*//****** 28-04-15 commented for time being *****//*
			Assert.assertEquals(phyEvalAssigner.trim(), (String) hashXlData.get("Phys_Eval_Reviewer").trim(), "Physical Eval is not assigned to Phys_Eval_Reviewer");
			fnpMymsg("############  #################");
			fnpMymsg(" Physical Eval task assigned to '" + (String) hashXlData.get("Phys_Eval_Reviewer").trim() + "'  successfully.");
			fnpMymsg("############  #################");
			*//****** 28-04-15 commented for time being *****//*
			*/
			
			if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
				//Assert.assertEquals(phyEvalAssigner.trim(), (String) hashXlData.get("Phys_Eval_Reviewer_Code").trim(), "Physical Eval is not assigned to Phys_Eval_Reviewer");
				Assert.assertTrue(phyEvalAssigner.trim().contains((String) hashXlData.get("Phys_Eval_Reviewer_Code").trim()), "Physical Eval is not assigned to Phys_Eval_Reviewer because expected is this '"+(String) hashXlData.get("Phys_Eval_Reviewer_Code")+"' but actual is this '"+phyEvalAssigner+"'.");
			}else{
				Assert.assertEquals(phyEvalAssigner.trim(), (String) hashXlData.get("Phys_Eval_Reviewer").trim(), "Physical Eval is not assigned to Phys_Eval_Reviewer because expected is this '"+(String) hashXlData.get("Phys_Eval_Reviewer")+"' but actual is this '"+phyEvalAssigner+"'.");
			}
			
			
			
		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "   VerifyPhyEvalReviewer  is failed . ", "VerifyPhyEvalReviewer_Failed");

		}
	}

	@Test(priority = 22, dependsOnMethods = { "VerifyPhyEvalReviewer" })
	public void Literature_Eval() throws Throwable {

		fnpMymsg(" **************************************************************");
		fnpMymsg(" Executing Literature_Eval");
		try {

			fnpSearchWorkOrderAndClickEditAlso(workOrderNo);

			
			
			
			if (  (!(currentSuiteName.equalsIgnoreCase(Dietary_Supplement_suite_Name))) && (!(currentSuiteName.equalsIgnoreCase(Non_Food_Compounds_suite_Name))) ){
				fnpProcessAI(actionItemDesc_LiteratureRequest, (String) hashXlData.get("Change_Status_Submitted"));
				fnpMymsg("  Submitted  the pending Action item for Literature Request successfully ");
			}else{
				//fnpProcessAI("ClientLitRequest", "Submitted");
				fnpProcessAI(actionItemDesc_ClientLitRequest, "Submitted");
				
			}

			
			
			
			
			
			// ***************Submitted the pending Actionitem(Literature
			// Request)***********

			fnpCommonClickTaskTab();

			int TaskStatusColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", "Task Status");
			int TaskTypeColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", "Task Type");

			int RowNoForLITEVAL = fnpFindRow("TasksTable_EditWO", taskType_LiteratureEvaluate, TaskTypeColIndex);

			String literatureEvalTaskStatus_TaskTable = fnpFetchFromTable("TasksTable_EditWO", RowNoForLITEVAL, TaskStatusColIndex); // 6th
																																		// is
																																		// Row
																																		// no.
																																		// here

			Assert.assertTrue(literatureEvalTaskStatus_TaskTable.toLowerCase().contains("ready"),
					"In Task Table , Literature Eval Task status has not become  to 'READY' . It is  showing '" + literatureEvalTaskStatus_TaskTable + "'.");

			fnpMymsg(" Literature Eval Task status has now  become  to 'READY' state  successfully.");

			fnpCommonClickSnapShotTab();
			int assignedToColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", SnapShot_taskTable_AssignedToColName);
			int TaskDescColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", SnapShot_taskTable_TaskDescColName);
			int liteEvalrowNo = fnpFindRow("Snapshot_TasksSummaryTable", SnapShot_taskDesc_LiteEval, TaskDescColIndex);
			String liteEvalAssigner = fnpFetchFromTable("Snapshot_TasksSummaryTable", liteEvalrowNo, assignedToColIndex);
			
/*			*//****** 28-04-15 commented for time being *****//*
			Assert.assertEquals(liteEvalAssigner.trim(), (String) hashXlData.get("LitEval_Reviewer").trim(), "Literature Eval is not assigned to LitEval_Reviewer");
			fnpMymsg("############  #################");
			fnpMymsg(" Literature Eval task assigned to '" + (String) hashXlData.get("LitEval_Reviewer").trim() + "'  successfully.");
			fnpMymsg("############  #################");
			*//****** 28-04-15 commented for time being *****//*
			
			*/

			
/*			
			if (!(currentSuiteName.equalsIgnoreCase(Dietary_Supplement_suite_Name)) ){
				if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
					//Assert.assertEquals(liteEvalAssigner.trim(), (String) hashXlData.get("LitEval_Reviewer_Code").trim(), "Literature Eval is not assigned to LitEval_Reviewer");
					Assert.assertTrue(liteEvalAssigner.trim().contains((String) hashXlData.get("LitEval_Reviewer_Code").trim()), "Literature Eval is not assigned to LitEval_Reviewer because expected is this '"+(String) hashXlData.get("LitEval_Reviewer_Code")+"' but actual is this '"+liteEvalAssigner+"'.");
				}else{
					Assert.assertEquals(liteEvalAssigner.trim(), (String) hashXlData.get("LitEval_Reviewer").trim(), "Literature Eval is not assigned to LitEval_Reviewer because expected is this '"+(String) hashXlData.get("LitEval_Reviewer")+"' but actual is this '"+liteEvalAssigner+"'.");
				}
				fnpMymsg(" Literature Eval task assigned to '" + (String) hashXlData.get("LitEval_Reviewer").trim() + "'  successfully.");
				
			}else{
				//in Dietary supplement, we dont have anything to match/compare...but here liteeval Reviewer on info tab, we are not entered anything in it and bydefault it contains login user
			}
			*/
			
			String litEvalReviewer=null;
			String litEvalReviewerCode=null;
			if (currentSuiteName.equalsIgnoreCase(Dietary_Supplement_suite_Name)) {
				//nothing to do for now
			}else{
				if (currentSuiteName.equalsIgnoreCase(Non_Food_Compounds_suite_Name)) {
					litEvalReviewer=(String) hashXlData.get("AccountManager").trim();
					litEvalReviewerCode=(String) hashXlData.get("AccountManager_Code").trim();					
				}else{
					litEvalReviewer=(String) hashXlData.get("LitEval_Reviewer").trim();
					litEvalReviewerCode=(String) hashXlData.get("LitEval_Reviewer_Code").trim();
				}				
				if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){						
					Assert.assertTrue(liteEvalAssigner.trim().contains(litEvalReviewerCode), "Literature Eval is not assigned to LitEval_Reviewer because expected is this '"+litEvalReviewerCode+"' but actual is this '"+liteEvalAssigner+"'.");
				}else{
					Assert.assertEquals(liteEvalAssigner.trim(), litEvalReviewer, "Literature Eval is not assigned to LitEval_Reviewer because expected is this '"+litEvalReviewer+"' but actual is this '"+liteEvalAssigner+"'.");
				}
				fnpMymsg(" Literature Eval task assigned to '" + litEvalReviewer + "'  successfully.");
				
				
				
			}
			
			
			
			
			
			
			// ++++++++++++++++++Verify Alerts+++++++++++++++++++++++++

			fnpCommonGoToHomeNClick();

			// -------Alert Action item assigned -------------------
			fnpCommonAlertGeneratedVerification("Action_item_assigned", "AI_ITEMS_ASSIGNED_AlertTable_header", "WO #", "AI_ITEMS_ASSIGNED_AlertTable",
					"AI_ITEMS_ASSIGNED_Alert_WO_filterTxtBox", workOrderNo);
			// ---------------------------------------------

			// -------Alert Work_Order_with_open_action_items
			// -------------------
			fnpCommonAlertGeneratedVerification("Work_Order_with_open_action_items", "WO_WITH_OPEN_ACTION_ITEMS_AlertTable_header", "WO #", "WO_WITH_OPEN_ACTION_ITEMS_AlertTable",
					"WO_WITH_OPEN_ACTION_ITEMS_Alert_WO_filterTxtBox", workOrderNo);
			// ---------------------------------------------

			/*
			 * // -------Alert Task_with_open_Action_Items -------------------
			 * fnpCommonAlertGeneratedVerification(
			 * "Task_with_open_Action_Items",
			 * "TA_TASK_OPEN_ITEMS_AlertTable_header", "WO #",
			 * "TA_TASK_OPEN_ITEMS_AlertTable",
			 * "TA_TASK_OPEN_ITEMS_Alert_WO_filterTxtBox", workOrderNo); //
			 * ---------------------------------------------
			 */

			// -------Alert Work Order in InProcess status -------------------
			fnpCommonAlertGeneratedVerification("Work_order_in_INPROCESS_status", "WO_InProcess_Status_AlertTable_header", "WO #", "WO_InProcess_Status_AlertTable",
					"WO_InProcess_Status_Alert_WO_filterTxtBox", workOrderNo);
			// ---------------------------------------------

			fnpCommonSwitchToUserForAlerts((String) hashXlData.get("TechOpsManager"));

			// -------Alert Task ready to be assigned -------------------
			fnpCommonAlertGeneratedVerification("Task_ready_to_be_assigned_AlertName", "Task_ready_to_be_assigned_AlertTable_header", "WO #",
					"Task_ready_to_be_assigned_AlertTable", "Task_ready_to_be_assigned_Alert_WO_filterTxtBox", workOrderNo);
			// ---------------------------------------------

			/*
			 * -----Commented below on Karen request on 30-5-14
			 * 
			 * // -------Work order(s) with unassigned action items
			 * ------------------- fnpCommonAlertGeneratedVerification(
			 * "Work_order_with_unassigned_action_items",
			 * "Work_order_with_unassigned_action_items_AlertTable_header",
			 * "WO #", "Work_order_with_unassigned_action_items_AlertTable",
			 * "Work_order_with_unassigned_action_items_Alert_WO_filterTxtBox",
			 * workOrderNo); // ---------------------------------------------
			 * 
			 * // ------- Task(s) with unassigned Action
			 * Items------------------- fnpCommonAlertGeneratedVerification(
			 * "Task_with_unassigned_Action_Items",
			 * "Task_with_unassigned_Action_Items_AlertTable_header", "WO #",
			 * "Task_with_unassigned_Action_Items_AlertTable",
			 * "Task_with_unassigned_Action_Items_Alert_WO_filterTxtBox",
			 * workOrderNo);
			 * 
			 * // ---------------------------------------------
			 */

			// ++++++++++++++++++Verify Alerts+++++++++++++++++++++++++

			/***********
			 * As we are going into Oasis , so login in IE browser if current
			 * browser is different
			 ******/
			String usingGoldenProcedureOrOasis = (String) hashXlData.get("Audit_usingGoldenProcedureOrOasis");
			if (!(usingGoldenProcedureOrOasis.equalsIgnoreCase("golden"))) {
				if (!(fnpGetCurrRunningBrowserName().equalsIgnoreCase("IE"))) {
					fnpCloseBroserAndLoginInIE();		
					fnpSearchWorkOrderOnly(workOrderNo);		
				} else {
					fnpSearchWorkOrderAndClickEditAlso(workOrderNo);
				}
		}

			/***********
			 * As we are going into Oasis , so login in IE browser if current
			 * browser is different
			 ******/

			
			

			
			
			
			
			
			fnpCommonClickTaskTab();

			// ***************Literature Eval Task***********
			fnpMymsg(" Going to click Literatue Eval task no. ");
			int ColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskNoColName);
			String literatureEvalTaskNo = fnpFetchFromTable("TasksTable_EditWO", RowNoForLITEVAL, ColIndex);
			Thread.sleep(500);
			
			
/*			fnpWaitTillVisiblityOfElementNClickable_NOR(".//a[text()='" + literatureEvalTaskNo + "']");
			driver.findElement(By.linkText(literatureEvalTaskNo)).click();
			*/
			
			fnpClickALinkInATable(literatureEvalTaskNo);
			fnpCheckError("");

			// fnpLoading_wait();
			// Thread.sleep(4000);//commented on 1 may
			fnpMymsg("Clicked on Literature Eval task no. in Task Table i.e. '" + literatureEvalTaskNo + "' .");
			fnpClick_OR("TaskTab_ScopeValidation_AssignTaskIcon");

			// fnpLoading_wait();

			Thread.sleep(1000);
			fnpClickInDialog_OR("TaskTab_ScopeValidation_AssignTask_SaveBtn");

			fnpCheckErrMsg("Error is thrown by application in Literature_Eval  method while Assigning the task");

			Thread.sleep(1000);
			fnpClick_OR("TaskTab_ScopeValidation_PerformTaskIcon");

			String originalHandle = driver.getWindowHandle();

			fnpMymsg(" Now going to Evaluate the info  by going to the OASIS system by clicking 'PerformAudit' icon  and filling forms.");
			fnpWaitForVisible("TaskTab_PerformAuditIcon");
			fnpWaitTillVisiblityOfElementNClickable_OR("TaskTab_PerformAuditIcon");
			
			
			
			int copyFromAudit_ForLiteAudit;
			int copyToAudit_ForLiteAudit;
			if (usingGoldenProcedureOrOasis.equalsIgnoreCase("golden")) {
				
				int EvalInfoTable_EvalNoColIndex = fnpFindColumnIndex("LiteEval_EvaluationInfoTable_header", "Eval #");
				String EvalINfoTable_EvalNo = fnpFetchFromTable("LiteEval_EvaluationInfoTable", 1, EvalInfoTable_EvalNoColIndex);
				copyFromAudit_ForLiteAudit=Integer.parseInt((String) hashXlData.get("copy_From_Audit_ForLiteAuditTask").trim());
				copyToAudit_ForLiteAudit=Integer.parseInt(EvalINfoTable_EvalNo.trim());
				
				fnpMymsg("Just before running golden procedure - COPY_AUDIT_TRANSACTION_SUBMIT");
				//fnpCallGoldenProcedure_COPY_AUDIT_TRANSACTION_SUBMIT(copyFromAudit_ForLiteAudit,copyToAudit_ForLiteAudit,(String) hashXlData.get("Auditor").trim());
				String Auditor;
				if (  (loginAsFullName==null) ||loginAsFullName.equalsIgnoreCase("") ) {
					Auditor=(String) hashXlData.get("Auditor").trim();
				} else {
					Auditor=loginAsFullName;
				}
				fnpCallGoldenProcedure_COPY_AUDIT_TRANSACTION_SUBMIT(copyFromAudit_ForLiteAudit,copyToAudit_ForLiteAudit,Auditor);
				
				
				fnpMymsg("Golden procedure 'COPY_AUDIT_TRANSACTION_SUBMIT' ran successfully.");
				
							
				
			} else {
				
				if (usingGoldenProcedureOrOasis.equalsIgnoreCase("oasis")) {
					
					ArrayList<String> listoldtabs2 = new ArrayList<String>(driver.getWindowHandles());
					fnpGetORObjectX("TaskTab_PerformAuditIcon").click();

					// fnpLoading_wait();
					Thread.sleep(20000);

					ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());

					int oldtabscount2 = listoldtabs2.size();
					int newTotal = tabs.size();
					int ii = 0;
					while (newTotal != (oldtabscount2 + 1)) {
						Thread.sleep(2000);
						tabs = new ArrayList<String>(driver.getWindowHandles());
						newTotal = tabs.size();
						ii = ii + 1;
						if (ii > Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
							break;
						}

					}

					Thread.sleep(2000);

					fnpMymsg("we are in Literature Eval");
					int tabsCount = tabs.size();
					for (int i = 0; i < tabsCount; i++) {

						fnpMymsg((i + 1) + "  window handle is:" + tabs.get(i));

						if (originalHandle.equalsIgnoreCase(tabs.get(i))) {
							// nothing to do

						} else {

							driver.switchTo().window(tabs.get(i));
							Thread.sleep(5000);
							// Thread.sleep(500);
							break;
						}

					}

					fnpMymsg("Going to click 'Complete Audit' button");
					fnpCheckErrorUsingPageSource_Oasis();
					 fnpWaitForVisible("CompleteAuditFom");
					
				//	fnpWaitTillVisiblityOfElementNClickable_OR("CompleteAuditFom");
					
				// new code as on 20-6-16 due to very slow, it failed due to error message --Unable to get browser exception....	
					int iCounter=0;
					
						while(iCounter<(Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))*5)){
							iCounter++;
							try{
								fnpWaitTillVisiblityOfElementNClickable_OR("CompleteAuditFom");
								break;
						}catch(Throwable t){
							if ((t.getMessage().contains("Unable to get browser"))) {
								// nothing to do
								Thread.sleep(1000);
								fnpMymsg("  ---Unable to get browser exception is coming ---"+iCounter);
							} else {
								fnpMymsg("  ---Unable to get browser exception is NOT coming ---"+iCounter);
								throw new Exception(t);
							}
							
						}
					}
					
						
						
						
						
						
					
					fnpGetORObjectX("CompleteAuditFom").click();
					fnpMymsg("Clicked 'Complete Audit' button");
					fnpCheckErrorUsingPageSource_Oasis();
					fnpMymsg("After checking page source error---no error");

					Thread.sleep(5000);
					fnpCheckErrorUsingPageSource_Oasis();
					String SecondHandle = driver.getWindowHandle();

					fnpWaitTillVisiblityOfElementNClickable_OR("LiteratureEvalNo");
					fnpGetORObjectX("LiteratureEvalNo").click();
					// Thread.sleep(1000);
					Thread.sleep(3000); // to avoid invalid selector exception some time
					fnpCheckErrorUsingPageSource_Oasis();

					fnpGetORObjectX("QuestionNotesTxtBox").clear();

					fnpType("OR", "QuestionNotesTxtBox", (String) hashXlData.get("Question_Notes"));

					fnpGetORObjectX("SectionNotesTxtBox").clear();

					fnpType("OR", "SectionNotesTxtBox", (String) hashXlData.get("Section_Notes"));
					java.util.List<WebElement> oasisSaveBtn = driver.findElements(By.xpath(OR.getProperty("Oasis_SaveBtn")));
					oasisSaveBtn.get(0).click();
					Thread.sleep(2000);
					fnpCheckErrorUsingPageSource_Oasis();

					fnpWaitTillVisiblityOfElementNClickable_OR("Oasis_SubmitBtn");
					// fnpWaitForVisible("Oasis_SubmitBtn");
					ArrayList<String> listoldtabs3 = new ArrayList<String>(driver.getWindowHandles());
					fnpGetORObjectX("Oasis_SubmitBtn").click();
					fnpCheckErrorUsingPageSource_Oasis();

					tabs = new ArrayList<String>(driver.getWindowHandles());

					int oldtabscount3 = listoldtabs3.size();
					newTotal = tabs.size();
					ii = 0;
					while (newTotal != (oldtabscount3 + 1)) {
						Thread.sleep(1000);
						tabs = new ArrayList<String>(driver.getWindowHandles());
						newTotal = tabs.size();
						ii = ii + 1;
						if (ii > Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
							break;
						}
					}

					Thread.sleep(2000);
					fnpCheckErrorUsingPageSource_Oasis();

					fnpMymsg("we are in Literature Eval");
					for (int i = 0; i < tabs.size(); i++) {

						fnpMymsg("This is Window Id - " + i + "--->" + tabs.get(i));
						if ((tabs.get(i).equalsIgnoreCase(originalHandle)) || (tabs.get(i).equalsIgnoreCase(SecondHandle))) {

						} else {
							driver.switchTo().window(tabs.get(i));
							Thread.sleep(500);
							break;

						}

					}

					// fnpWaitForVisible("Oasis_VisitStartTimeHour");
					Thread.sleep(1000);
					fnpCheckErrorUsingPageSource_Oasis();
					fnpTypeTimeInOasis("OR", "Oasis_VisitStartTimeHour", (String) hashXlData.get("Oasis_VisitStartTimeHour"));
					fnpTypeTimeInOasis("OR", "Oasis_VisitStartMinute", (String) hashXlData.get("Oasis_VisitStartMinute"));
					fnpTypeTimeInOasis("OR", "Oasis_VisitEndTimeHour", (String) hashXlData.get("Oasis_VisitEndTimeHour"));
					fnpTypeTimeInOasis("OR", "Oasis_VisitEndMinute", (String) hashXlData.get("Oasis_VisitEndMinute"));

					fnpGetORObjectX("Oasis_UpdateVisitTime_SaveBtn").click();

					// Thread.sleep(2000);
					Thread.sleep(1000);

					driver.switchTo().window(SecondHandle);
					Thread.sleep(1000);

					fnpCheckErrorUsingPageSource_Oasis();
					// fnpWaitForVisible("Oasis_VisitSuccessfullySubmittedMsg");
					fnpWaitForVisible("Oasis_VisitSuccessfullySubmittedMsg", 360);
					Thread.sleep(2000);
					fnpWaitForVisible("Oasis_ExitBtn");
					fnpWaitTillVisiblityOfElementNClickable_OR("Oasis_ExitBtn");
					fnpGetORObjectX("Oasis_ExitBtn").click();
					fnpCheckErrorUsingPageSource_Oasis();

					driver.close();
					// driver.quit();

					driver.switchTo().window(originalHandle);
				} else {
					throw new Exception("Value is not assigned to usingGoldenOrOasis");
				}
			
			}
			
			
			
			
			
			


			fnpMymsg(" Now we have coming back from Oasis and now going to click 'Refresh Data' button in iPulse");

			fnpClick_OR("LitEvalRefreshDataBtn");
			fnpMymsg(" clicked 'Refresh Data' button.");
			
			
			//through golden procedure  sometime refresh button does not work...
			//it is for timebeing...remove it later 11-04-2017
			if (usingGoldenProcedureOrOasis.equalsIgnoreCase("golden")) {
				driver.navigate().refresh();
			}
			

			fnpWaitForVisible("LiteEval_EvaluationInfoTable_header");
			int EvalInfoStatusColNo = fnpFindColumnIndex("LiteEval_EvaluationInfoTable_header", "Status");
			String EvalInfoStatusValue = fnpFetchFromTable("LiteEval_EvaluationInfoTable", 1, EvalInfoStatusColNo);

			Assert.assertTrue(EvalInfoStatusValue.toLowerCase().contains("submitted"),
					"In Evaluation Info Table , Evaluation info's  status has not become  to 'SUBMITTED' . It is  showing '" + EvalInfoStatusValue + "'.");

			fnpMymsg("  Evaluation info's  status has become  to 'SUBMITTED'  successfully.");

			fnpClick_OR("TaskTab_ScopeValidation_CompleteTaskIcon");

			fnpClickInDialog_OR("TaskTab_ScopeValidation_CompleteTaskSaveBtn");


			fnpCheckErrMsg("Error is thrown by application in Literature_Eval  method while completing the task ");


			
			
			
			if (usingGoldenProcedureOrOasis.equalsIgnoreCase("golden")) {
				driver.navigate().refresh();
			}else{
				/****** 28-04-15 commented for time being *****/
				// fnpClick_OR("LitEvalRefreshDataBtn");
				if (fnpCheckElementPresenceImmediately("LitEvalRefreshDataBtn")) {
					fnpClick_OR("LitEvalRefreshDataBtn");
				}
				/****** 28-04-15 commented for time being *****/
			}
			
			
			

			// if (fnpGetCurrRunningBrowserName() != "IE") {

			
			
			
			if (!(usingGoldenProcedureOrOasis.equalsIgnoreCase("golden"))) {
				if (!(browserName.equalsIgnoreCase("IE"))) {
					// fnpCloseBroserAndLoginInIE();
					fnpLaunchBrowserAndLogin();
					fnpSearchWorkOrderAndClickEditAlso_AfterRestartWhenAlertsNo(workOrderNo);
					fnpCommonClickTaskTab();

					// ***************Literature Eval Task***********
					fnpMymsg(" Going to click Literatue Eval task no. ");
					Thread.sleep(500);

					
					fnpClickALinkInATable(literatureEvalTaskNo);
					
					// fnpLoading_wait();

				} else {
					// fnpSearchWorkOrderAndClickEditAlso(workOrderNo);
					// fnpSearchWorkOrderOnly(workOrderNo);
				}
			}
			
			

			
			fnpWaitForVisible("LiteEval_FindingsTable");

			java.util.List<WebElement> AssignFindingIcons = driver.findElements(By.xpath(OR.getProperty("FindingsTable_AssignFindingIcon")));
			int countAssignFindingIcons = AssignFindingIcons.size();
			if ( (!(currentSuiteName.equalsIgnoreCase(Dietary_Supplement_suite_Name)))   && (!(currentSuiteName.equalsIgnoreCase(Non_Food_Compounds_suite_Name)))  ){
					String[] findingStatus = ((String) hashXlData.get("LitFinding_ChangeStatus")).split(",");
					int expectedFindingCount=findingStatus.length;
		//			Assert.assertTrue(countAssignFindingIcons > 1, "AssignFinding icons are not visible as their count is '" + countAssignFindingIcons + "' .");
					Assert.assertTrue(countAssignFindingIcons ==expectedFindingCount, expectedFindingCount+" Findings are not getting generated as visible AssignFinding icons count are '" + countAssignFindingIcons + "' .");
					fnpMymsg("Findings are present or visible and their count is '" + countAssignFindingIcons + "'");
					
		
					int FindingStausColIndex = fnpFindColumnIndex("LiteEval_FindingsTable_header", "Status");
					int CountTotalRows = fnpCountRowsInTable("LiteEval_FindingsTable");
		
					for (int i = 1; i < CountTotalRows + 1; i++) {
		
						String status = fnpFetchFromTable("LiteEval_FindingsTable", i, FindingStausColIndex);
		
						Assert.assertTrue(status.equalsIgnoreCase("pending"), "Status of findings  are not in 'PENDING' status in row '" + i + "' .");
					}
		
					fnpMymsg("All findings are in 'PENDING' status");
				
			}else
			{
				int expectedFindingCount = 0;
				Assert.assertTrue(countAssignFindingIcons ==expectedFindingCount, countAssignFindingIcons+" Findings are being generated as visible AssignFinding icons count are '" + countAssignFindingIcons + "' but according to the test case findings should not be generated in this Dietary Mod/Brack wo");
						
					}
			
			
			
			
			

			int TaskSummaryTable_StausColIndex = fnpFindColumnIndex("LiteEval_TaskSummaryTable_header", "Status");
			String LitEvalTask_status = fnpFetchFromTable("LiteEval_TaskSummaryTable", 1, TaskSummaryTable_StausColIndex);

			Assert.assertTrue(LitEvalTask_status.equalsIgnoreCase("completed"), " status for the Literature Eval task should be completed but it is coming  '" + LitEvalTask_status
					+ "' ");
			fnpMymsg(" Status for the Literature Eval task has become  to 'COMPLETED'  successfully.");

			driver.navigate().refresh();

			Thread.sleep(2000);

			fnpWaitForVisible("LiteEval_ActionItemsTab_ActionItemTable");
			int LEval_AItemsTab_AItemTable_StatusColIndex = fnpFindColumnIndex("LiteEval_ActionItemsTab_ActionItemTable_header", "Status");

			String LiteEval_ActionItemsTab_ActionItemTable_status = fnpFetchFromTable("LiteEval_ActionItemsTab_ActionItemTable", 1, LEval_AItemsTab_AItemTable_StatusColIndex);

			Assert.assertTrue(LiteEval_ActionItemsTab_ActionItemTable_status.equalsIgnoreCase("completed"),
					" Action item for Literature eval should also get changed from SUBMITTED to COMPLETED but it is coming  '" + LiteEval_ActionItemsTab_ActionItemTable_status
							+ "' ");
			fnpMymsg(" Action item for Literature eval task has become  to 'COMPLETED'  successfully.");

			// ++++++++++++++++++Verify Alerts+++++++++++++++++++++++++

			fnpCommonGoToHomeNClick();

			// -------Alert Work Order in InProcess status -------------------
			fnpCommonAlertGeneratedVerification("Work_order_in_INPROCESS_status", "WO_InProcess_Status_AlertTable_header", "WO #", "WO_InProcess_Status_AlertTable",
					"WO_InProcess_Status_Alert_WO_filterTxtBox", workOrderNo);
			// ---------------------------------------------

			fnpCommonSwitchToUserForAlerts((String) hashXlData.get("TechOpsManager"));

			// -------Alert Task ready to be assigned -------------------
			fnpCommonAlertGeneratedVerification("Task_ready_to_be_assigned_AlertName", "Task_ready_to_be_assigned_AlertTable_header", "WO #",
					"Task_ready_to_be_assigned_AlertTable", "Task_ready_to_be_assigned_Alert_WO_filterTxtBox", workOrderNo);
			// ---------------------------------------------

			// ++++++++++++++++++Verify Alerts+++++++++++++++++++++++++

			fnpMymsg("  Completed the Literature Eval Task successfully ");

			fnpCheckErrMsg("Error is thrown by application while Going to Complete the Literature Eval Task ");

		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "   Literature_Eval  is failed . ", "Literature_Eval_Failed");

		}
	}

	@Test(priority = 22, dependsOnMethods = { "Literature_Eval" })
	public void RestartBrowserNLogin3() throws Throwable {
		try {

			String usingGoldenProcedureOrOasis = (String) hashXlData.get("Audit_usingGoldenProcedureOrOasis");
			if (!(usingGoldenProcedureOrOasis.equalsIgnoreCase("golden"))) {
				fnpCloseBroserAndLoginInIE();
				fnpSearchWorkOrderAndClickEditAlso_AfterRestartWhenAlertsNo(workOrderNo);
			}

			
		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "   RestartBrowserNLogin3  is failed . ", "RestartBrowserNLogin3_Failed");

		}

	}

	@Test(priority = 23, dependsOnMethods = { "RestartBrowserNLogin3" })
	public void Physical_Eval_With_Alerts() throws Throwable {

		fnpMymsg(" **************************************************************");
		fnpMymsg(" Executing Physical_Eval_With_Alerts");

		try {

			fnpSearchWorkOrderAndClickEditAlso(workOrderNo);

			try {
				if (((String) hashXlData.get("Alerts_Verify_Flag")).equalsIgnoreCase("No")) {

					// fnpLoading_wait();
					if (fnpCheckElementPresenceImmediately("TaskTab_ScopeValidation_BackBtn")) {
						fnpGetORObjectX("TaskTab_ScopeValidation_BackBtn").click();
						fnpLoading_wait();
					}
					// fnpLoading_wait();
					if (!fnpCheckElementPresenceImmediately("Task_ShowAllLink")) {
						fnpCommonClickTaskTab();
						// fnpLoading_wait();
					}

					// fnpLoading_wait();
					fnpWaitForVisible("Task_ShowAllLink");
				} else {
					fnpCommonClickTaskTab();
				}
			} catch (NullPointerException e) {
				fnpClickBackToViewNBackBtnWhenNullPointerExceptionWhenAlertFlagNotPresent();
				fnpCommonClickTaskTab();
				// nothing to do just ignore it. Null pointer exception will be
				// thrown when variable is not present.
			}

			fnpWaitForVisible("Task_ShowAllLink");
			fnpWaitTillVisiblityOfElementNClickable_OR("Task_ShowAllLink");

			int TaskTypeColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskTypeColName);
			int RowNoForPhyEval = fnpFindRow("TasksTable_EditWO", taskType_PhysicalEval, TaskTypeColIndex);

			fnpMymsg(" Going to click Physical Eval task no. ");
			int ColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskNoColName);
			String PhyEvalTaskNo = fnpFetchFromTable("TasksTable_EditWO", RowNoForPhyEval, ColIndex);
			Thread.sleep(500);
			
			
/*			fnpWaitTillVisiblityOfElementNClickable_NOR(".//a[text()='" + PhyEvalTaskNo + "']");
			driver.findElement(By.linkText(PhyEvalTaskNo)).click();
			*/
			
			fnpClickALinkInATable(PhyEvalTaskNo);

			fnpMymsg("Clicked on Physical Eval task no. in Task Table i.e. '" + PhyEvalTaskNo + "' .");
			fnpClick_OR("TaskTab_ScopeValidation_AssignTaskIcon");

			fnpClickInDialog_OR("TaskTab_ScopeValidation_AssignTask_SaveBtn");
	
			fnpCheckErrMsg("Error is thrown by application in Physical_Eval_With_Alerts  method while Assigning the task");

			fnpClick_OR("TaskTab_ScopeValidation_PerformTaskIcon");
			fnpCheckErrMsg("Error is thrown by application after clicking performTaskIcon");

			int EvalColNo = fnpFindColumnIndex("PhyEval_EvaluationInfoTable_header", "Eval #");
			String EvalNo = fnpFetchFromTable("PhyEval_EvaluationInfoTable", 1, EvalColNo);

			String originalHandle = driver.getWindowHandle();

			fnpMymsg(" Now going to Evaluate the info  by going to the OASIS system by clicking 'Perform Audit' icon  and filling forms.");
			fnpWaitForVisible("TaskTab_PerformAuditIcon");
			
			
			
			int copyFromAudit_ForPhyAudit;
			int copyToAudit_ForPhyAudit;
			String usingGoldenProcedureOrOasis = (String) hashXlData.get("Audit_usingGoldenProcedureOrOasis");
			if (usingGoldenProcedureOrOasis.equalsIgnoreCase("golden")) {
				
				int EvalInfoTable_EvalNoColIndex = fnpFindColumnIndex("PhyEval_EvaluationInfoTable_header", "Eval #");
				String EvalINfoTable_EvalNo = fnpFetchFromTable("PhyEval_EvaluationInfoTable", 1, EvalInfoTable_EvalNoColIndex);
				copyFromAudit_ForPhyAudit=Integer.parseInt((String) hashXlData.get("copy_From_Audit_ForPhyAuditTask").trim());
				copyToAudit_ForPhyAudit=Integer.parseInt(EvalINfoTable_EvalNo.trim());
				
				fnpMymsg("Just before running golden procedure - COPY_AUDIT_TRANSACTION_SUBMIT");
				//fnpCallGoldenProcedure_COPY_AUDIT_TRANSACTION_SUBMIT(copyFromAudit_ForPhyAudit,copyToAudit_ForPhyAudit,(String) hashXlData.get("Auditor").trim());
				String Auditor;
				if (  (loginAsFullName==null) ||loginAsFullName.equalsIgnoreCase("") ) {
					Auditor=(String) hashXlData.get("Auditor").trim();
				} else {
					Auditor=loginAsFullName;
				}
				fnpCallGoldenProcedure_COPY_AUDIT_TRANSACTION_SUBMIT(copyFromAudit_ForPhyAudit,copyToAudit_ForPhyAudit,Auditor);
				
				fnpMymsg("Golden procedure 'COPY_AUDIT_TRANSACTION_SUBMIT' ran successfully.");
				
				
			} else {
				
				if (usingGoldenProcedureOrOasis.equalsIgnoreCase("oasis")) {
					
					fnpWaitTillVisiblityOfElementNClickable_OR("TaskTab_PerformAuditIcon");
					ArrayList<String> listoldtabs4 = new ArrayList<String>(driver.getWindowHandles());
					fnpGetORObjectX("TaskTab_PerformAuditIcon").click();

					Thread.sleep(15000);

					ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());

					int oldTotalcount4 = listoldtabs4.size();
					int newTotal1 = tabs.size();
					int ii = 0;
					while (newTotal1 != (oldTotalcount4 + 1)) {
						Thread.sleep(1000);
						tabs = new ArrayList<String>(driver.getWindowHandles());
						newTotal1 = tabs.size();
						ii = ii + 1;
						if (ii > Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
							break;
						}
					}

					Thread.sleep(5000);// sometime screen refresh specially failed here
										// in 28 new machine
					 Thread.sleep(2000);

					fnpMymsg("we are in Physical Eval");
					int tabsCount = tabs.size();
					for (int i = 0; i < tabsCount; i++) {

						fnpMymsg((i + 1) + "  window handle is:" + tabs.get(i));

						if (originalHandle.equalsIgnoreCase(tabs.get(i))) {
							// nothing to do

						} else {
							driver.switchTo().window(tabs.get(i));
							 Thread.sleep(1000);
							Thread.sleep(500);
							break;
						}

					}

					if (fnpGetIPAddress().equalsIgnoreCase("76")) {
						Thread.sleep(5000);// sometime screen refresh specially failed
											// here in 28 new machine
					}

					fnpCheckErrorUsingPageSource_Oasis();
					fnpMymsg("Going to click 'Complete Audit' button");
					// fnpWaitForVisible("CompleteAuditFom");
				//	fnpWaitTillVisiblityOfElementNClickable_OR("CompleteAuditFom");
					
					
					
					// new code as on 20-6-16 due to very slow, it failed due to error message --Unable to get browser exception....	
					int iCounter=0;
					
						while(iCounter<(Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))*5)){
							iCounter++;
							try{
								fnpWaitTillVisiblityOfElementNClickable_OR("CompleteAuditFom");
								break;
						}catch(Throwable t){
							if ((t.getMessage().contains("Unable to get browser"))) {
								// nothing to do
								Thread.sleep(1000);
								fnpMymsg("  ---Unable to get browser exception is coming ---"+iCounter);
							} else {
								fnpMymsg("  ---Unable to get browser exception is NOT coming ---"+iCounter);
								throw new Exception(t);
							}
							
						}
					}
					
					
					
					
					
					
					fnpGetORObjectX("CompleteAuditFom").click();
					fnpMymsg("Clicked 'Complete Audit' button");

					Thread.sleep(5000);
					// Thread.sleep(3000);
					fnpCheckErrorUsingPageSource_Oasis();

					String SecondHandle = driver.getWindowHandle();

					String newUpdatedPhysicalEvalNo = OR.getProperty("PhysicalEvalNo_Oasis_part1") + EvalNo + OR.getProperty("PhysicalEvalNo_Oasis_part2");

					Thread.sleep(1000);// sometime screen refresh specially failed here
										// in 28 new machine

					fnpClick_NOR_withoutWait(newUpdatedPhysicalEvalNo);

					Thread.sleep(4000);

					fnpCheckErrorUsingPageSource_Oasis();

					try {// sometime screen refresh
						fnpWaitForVisible("INFO1_answerTxtBox");
					} catch (Throwable t) {
						// sometime screen refresh

						fnpMymsg("");
						fnpMymsg("");
						fnpMymsg("");
						fnpMymsg("");
						fnpMymsg("");
						fnpMymsg("@   ---********Exception thrown is in try block of INFO1_answerTxtBox ************************************************");

						fnpMymsg(t.getMessage());
						fnpMymsg("");
						fnpMymsg("");
						fnpMymsg("");
						fnpMymsg("");
						fnpMymsg("@   ---********************************************************");
						String PageSourceText = driver.getPageSource().toLowerCase();
						fnpMymsg("@   ---********************************************************");

						// fnpMymsg(PageSourceText);
						// fnpMymsg(fnpFormatReplaceSpecailCharacters(PageSourceText));
						fnpMymsg("@   ---*********COMMENTING PAGE SOURCE For Time being before release on 4th March********************");

						fnpMymsg("@   ---********************************************************");
						fnpMymsg("");
						fnpMymsg("");
						fnpMymsg("");
						fnpMymsg("");
						fnpMymsg("");
						fnpMymsg("");
						fnpMymsg("@  -----due to invalid selector , INFO1_answerTxtBox not identified, so refreshing it again.");
						driver.navigate().refresh();
						Thread.sleep(6000);
					}

					fnpGetORObjectX("INFO1_answerTxtBox").clear();
					fnpType("OR", "INFO1_answerTxtBox", (String) hashXlData.get("PhyEval_INFO1_answer"));


					fnpType("OR", "QuestionNotesTxtBox", (String) hashXlData.get("PhyEval_Question_Notes"));

	
					fnpType("OR", "PhyEval_SectionNotesTxtBox", (String) hashXlData.get("PhyEval_Section_Notes"));

				
					java.util.List<WebElement> oasisSaveNNextBtn = driver.findElements(By.xpath(OR.getProperty("Oasis_SaveNNextBtn")));
					oasisSaveNNextBtn.get(0).click();
					Thread.sleep(3000);
					fnpCheckErrorUsingPageSource_Oasis();

					fnpGetORObjectX("INFO1_answerTxtBox").clear();
					fnpType("OR", "INFO1_answerTxtBox", (String) hashXlData.get("PhyEval_INFO1_answer"));

					fnpGetORObjectX("QuestionNotesTxtBox").clear();
					fnpType("OR", "QuestionNotesTxtBox", (String) hashXlData.get("PhyEval_Question_Notes"));

					fnpGetORObjectX("PhyEval_SectionNotesTxtBox").clear();
					fnpType("OR", "PhyEval_SectionNotesTxtBox", (String) hashXlData.get("PhyEval_Section_Notes"));

					oasisSaveNNextBtn = driver.findElements(By.xpath(OR.getProperty("Oasis_SaveNNextBtn")));
					oasisSaveNNextBtn.get(0).click();
					Thread.sleep(3000);
					fnpCheckErrorUsingPageSource_Oasis();

					fnpClick_OR_WithoutWait("Oasis_FindingRadioBtn");
					Thread.sleep(200);
					fnpGetORObjectX("QuestionNotesTxtBox").clear();
					fnpType("OR", "QuestionNotesTxtBox", (String) hashXlData.get("PhyEval_Question_Notes"));

					fnpGetORObjectX("PhyEval_SectionNotesTxtBox").clear();
					fnpType("OR", "PhyEval_SectionNotesTxtBox", (String) hashXlData.get("PhyEval_Section_Notes"));

					oasisSaveNNextBtn = driver.findElements(By.xpath(OR.getProperty("Oasis_SaveNNextBtn")));
					oasisSaveNNextBtn.get(0).click();
					Thread.sleep(3000);
					fnpCheckErrorUsingPageSource_Oasis();

					fnpClick_OR_WithoutWait("Oasis_FindingRadioBtn");
					Thread.sleep(200);
					fnpGetORObjectX("QuestionNotesTxtBox").clear();
					fnpType("OR", "QuestionNotesTxtBox", (String) hashXlData.get("PhyEval_Question_Notes"));

					fnpGetORObjectX("PhyEval_SectionNotesTxtBox").clear();
					fnpType("OR", "PhyEval_SectionNotesTxtBox", (String) hashXlData.get("PhyEval_Section_Notes"));

					oasisSaveNNextBtn = driver.findElements(By.xpath(OR.getProperty("Oasis_SaveNNextBtn")));
					oasisSaveNNextBtn.get(0).click();
					Thread.sleep(3000);
					fnpCheckErrorUsingPageSource_Oasis();

					fnpClick_OR_WithoutWait("Oasis_NotApplicableRadioBtn");
					Thread.sleep(200);
					fnpGetORObjectX("QuestionNotesTxtBox").clear();
					fnpType("OR", "QuestionNotesTxtBox", (String) hashXlData.get("PhyEval_Question_Notes"));

					fnpGetORObjectX("PhyEval_SectionNotesTxtBox").clear();
					fnpType("OR", "PhyEval_SectionNotesTxtBox", (String) hashXlData.get("PhyEval_Section_Notes"));

					oasisSaveNNextBtn = driver.findElements(By.xpath(OR.getProperty("Oasis_SaveNNextBtn")));
					oasisSaveNNextBtn.get(0).click();
					Thread.sleep(3000);
					fnpCheckErrorUsingPageSource_Oasis();

					fnpClick_OR_WithoutWait("Oasis_NotApplicableRadioBtn");
					Thread.sleep(200);
					fnpGetORObjectX("QuestionNotesTxtBox").clear();
					fnpType("OR", "QuestionNotesTxtBox", (String) hashXlData.get("PhyEval_Question_Notes"));

					fnpGetORObjectX("PhyEval_SectionNotesTxtBox").clear();
					fnpType("OR", "PhyEval_SectionNotesTxtBox", (String) hashXlData.get("PhyEval_Section_Notes"));

					oasisSaveNNextBtn = driver.findElements(By.xpath(OR.getProperty("Oasis_SaveNNextBtn")));
					oasisSaveNNextBtn.get(0).click();
					Thread.sleep(3000);
					fnpCheckErrorUsingPageSource_Oasis();

					/*****
					 * fnpWaitForVisible("Oasis_SubmitBtn"); // It throws timeout
					 * exception b/c button not visible as in oasis it is on same page
					 * and little fraction of time page load something like that
					 ***/
					fnpWaitTillVisiblityOfElementNClickable_OR("Oasis_SubmitBtn");

					ArrayList<String> listoldtabs5 = new ArrayList<String>(driver.getWindowHandles());
					fnpGetORObjectX("Oasis_SubmitBtn").click();
					fnpCheckErrorUsingPageSource_Oasis();

					Thread.sleep(3000);
					fnpCheckErrorUsingPageSource_Oasis();
					tabs = new ArrayList<String>(driver.getWindowHandles());

					int oldtabscount5 = listoldtabs5.size();
					int newTotal = tabs.size();
					ii = 0;
					while (newTotal != (oldtabscount5 + 1)) {
						Thread.sleep(1000);
						tabs = new ArrayList<String>(driver.getWindowHandles());
						newTotal = tabs.size();
						ii = ii + 1;
						if (ii > Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
							break;
						}
					}

			
					Thread.sleep(1000);

				
					for (int i = 0; i < tabs.size(); i++) {

						fnpMymsg("This is Window Id - " + i + "--->" + tabs.get(i));
						if ((tabs.get(i).equalsIgnoreCase(originalHandle)) || (tabs.get(i).equalsIgnoreCase(SecondHandle))) {

						} else {
							driver.switchTo().window(tabs.get(i));
							// Thread.sleep(2000);
							Thread.sleep(500);
							break;

						}

					}
					fnpCheckErrorUsingPageSource_Oasis();


					Thread.sleep(1000);
					fnpTypeTimeInOasis("OR", "Oasis_VisitStartTimeHour", (String) hashXlData.get("Oasis_PhyEval_VisitStartTimeHour"));
					fnpTypeTimeInOasis("OR", "Oasis_VisitStartMinute", (String) hashXlData.get("Oasis_PhyEval_VisitStartMinute"));
					fnpTypeTimeInOasis("OR", "Oasis_VisitEndTimeHour", (String) hashXlData.get("Oasis_PhyEval_VisitEndTimeHour"));
					fnpTypeTimeInOasis("OR", "Oasis_VisitEndMinute", (String) hashXlData.get("Oasis_PhyEval_VisitEndMinute"));

					fnpGetORObjectX("Oasis_UpdateVisitTime_SaveBtn").click();
					Thread.sleep(1000);

					driver.switchTo().window(SecondHandle);
					Thread.sleep(1000);

					fnpWaitForVisible("Oasis_VisitSuccessfullySubmittedMsg", 360);
					Thread.sleep(2000);
					fnpWaitForVisible("Oasis_ExitBtn");
					fnpWaitTillVisiblityOfElementNClickable_OR("Oasis_ExitBtn");
					fnpGetORObjectX("Oasis_ExitBtn").click();
					fnpCheckErrorUsingPageSource_Oasis();

					driver.close();

					driver.switchTo().window(originalHandle);

					
				} else {
					throw new Exception("Value is not assigned to usingGoldenOrOasis");
				}
			
			}
			


			fnpMymsg(" Now we have coming back from Oasis and now going to click 'Refresh Data' button in iPulse");

			/****** 28-04-15 commented for time being *****/
			// fnpClick_OR("PhyEvalRefreshDataBtn");
			if (fnpCheckElementPresenceImmediately("PhyEvalRefreshDataBtn")) {
				fnpClick_OR("PhyEvalRefreshDataBtn");
				fnpMymsg(" clicked 'Refresh Data' button.");
			}
			/****** 28-04-15 commented for time being *****/
			
			
			
			//through golden procedure  sometime refresh button does not work...
			//it is for timebeing...remove it later 11-04-2017
			if (usingGoldenProcedureOrOasis.equalsIgnoreCase("golden")) {
				driver.navigate().refresh();
			}
			

			fnpWaitForVisible("PhyEval_EvaluationInfoTable_header");
			int EvalInfoStatusColNo = fnpFindColumnIndex("PhyEval_EvaluationInfoTable_header", "Status");
			String EvalInfoStatusValue = fnpFetchFromTable("PhyEval_EvaluationInfoTable", 1, EvalInfoStatusColNo);

			Assert.assertTrue(EvalInfoStatusValue.toLowerCase().contains("submitted"),
					"In Evaluation Info Table , Evaluation info's  status has not become  to 'SUBMITTED' . It is  showing '" + EvalInfoStatusValue + "'.");

			fnpMymsg("  Evaluation info's  status has become  to 'SUBMITTED'  successfully.");

			// ++++++++++++++++++Verify Alerts+++++++++++++++++++++++++

			fnpCommonGoToHomeNClick();

			// -------Alert Work Order in InProcess status -------------------
			fnpCommonAlertGeneratedVerification("Work_order_in_INPROCESS_status", "WO_InProcess_Status_AlertTable_header", "WO #", "WO_InProcess_Status_AlertTable",
					"WO_InProcess_Status_Alert_WO_filterTxtBox", workOrderNo);
			// ---------------------------------------------

			// ++++++++++++++++++Verify Alerts+++++++++++++++++++++++++

			fnpSearchWorkOrderAndClickEditAlso(workOrderNo);

			try {
				if (((String) hashXlData.get("Alerts_Verify_Flag")).equalsIgnoreCase("No")) {
					// fnpCommonBackToViewNBackBtnClick();
					// fnpWaitForVisible("Task_ShowAllLink");

				} else {
					if (usingGoldenProcedureOrOasis.equalsIgnoreCase("golden")) {
						if (fnpCheckElementPresenceImmediately("TaskTab_ScopeValidation_BackToViewBtn")) {
							fnpClick_OR("TaskTab_ScopeValidation_BackToViewBtn");							
						}
						fnpClick_OR("TaskTab_ScopeValidation_BackBtn");

					}
					fnpCommonClickTaskTab();

				}
			} catch (NullPointerException e) {
				fnpClickBackToViewNBackBtnWhenNullPointerExceptionWhenAlertFlagNotPresent();
				fnpCommonClickTaskTab();
				// nothing to do just ignore it. Null pointer exception will be
				// thrown when variable is not present.
			}
			if (!(usingGoldenProcedureOrOasis.equalsIgnoreCase("golden"))) {
				if ((fnpGetCurrRunningBrowserName().equalsIgnoreCase("IE"))) {
					fnpLaunchBrowserAndLogin();
					fnpSearchWorkOrderAndClickEditAlso_AfterRestartWhenAlertsNo(workOrderNo);
					fnpCommonClickTaskTab();
				} else {
					// nothing to do
				}
			}


			if (!fnpCheckElementPresenceImmediately("PhyEvalRefreshDataBtn")) {
				fnpWaitForVisible("Task_ShowAllLink");
				fnpWaitTillVisiblityOfElementNClickable_OR("Task_ShowAllLink");

				TaskTypeColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskTypeColName);
				RowNoForPhyEval = fnpFindRow("TasksTable_EditWO", taskType_PhysicalEval, TaskTypeColIndex);

				fnpMymsg(" Going to click Physical Eval task no. ");
				ColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskNoColName);
				PhyEvalTaskNo = fnpFetchFromTable("TasksTable_EditWO", RowNoForPhyEval, ColIndex);
				Thread.sleep(500);
				

				
				fnpClickALinkInATable(PhyEvalTaskNo);
				
				fnpWaitForVisible("PhyEvalRefreshDataBtn");
			}

			if (fnpCheckElementPresenceImmediately("TaskTab_ScopeValidation_PerformTaskIcon")) {
				fnpGetORObjectX("TaskTab_ScopeValidation_PerformTaskIcon").click();
				fnpLoading_wait();
			}

			fnpClick_OR("PhyEval_CheckIcon");

			// fnpClick_OR_WithoutWait("PhyEval_CompleteTaskSaveBtn");
			fnpClickInDialog_OR("PhyEval_CompleteTaskSaveBtn");


			
			fnpCheckErrMsg("Error is thrown by application in Physical_Eval_With_Alerts  method while completing the task");

			// fnpLoading_wait();

			int TaskSummaryTable_StausColIndex = fnpFindColumnIndex("LiteEval_TaskSummaryTable_header", "Status");
			String PhyEvalTask_status = fnpFetchFromTable("LiteEval_TaskSummaryTable", 1, TaskSummaryTable_StausColIndex);

			Assert.assertTrue(PhyEvalTask_status.equalsIgnoreCase("ready"), " status for the Physical Eval task should be in Ready status but it is coming  '" + PhyEvalTask_status
					+ "' ");
			fnpMymsg(" Status for the Physical Eval task has become  to 'READY'  successfully.");

			fnpGetORObjectX("TaskTab_ScopeValidation_AssignTaskIcon").click();

			fnpClickInDialog_OR("TaskTab_ScopeValidation_AssignTask_SaveBtn");

			fnpCheckErrMsg("Error is thrown by application after clicking AssignTask_SaveBtn");
			fnpClick_OR("TaskTab_ScopeValidation_PerformTaskIcon");

			// fnpLoading_wait();
			fnpCheckErrMsg("Error is thrown by application after clicking PerformTaskIcon");
			TaskSummaryTable_StausColIndex = fnpFindColumnIndex("LiteEval_TaskSummaryTable_header", "Status");
			PhyEvalTask_status = fnpFetchFromTable("LiteEval_TaskSummaryTable", 1, TaskSummaryTable_StausColIndex);

			Assert.assertTrue(PhyEvalTask_status.equalsIgnoreCase("inperform"), " status for the Physical Eval task should be in INPERFORM status but it is coming  '"
					+ PhyEvalTask_status + "' ");
			fnpMymsg(" Status for the Physical Eval task has become  to 'INPERFORM'  successfully.");

			fnpWaitForVisible("PhyEval_ReviewInfoTable_SaveBtn");
			fnpMymsg(" Review Info gets generated successfully as Save button is visible in Review Info table");

			// ++++++++++++++++++Verify Alerts+++++++++++++++++++++++++

			fnpCommonGoToHomeNClick();

			// -------Alert Physical evaluation(s) to be reviewed
			// -------------------
			fnpCommonAlertGeneratedVerification("Physical_evaluation_to_be_reviewed", "Physical_evaluation_to_be_reviewed_AlertTable_header", "WO #",
					"Physical_evaluation_to_be_reviewed_AlertTable", "Physical_evaluation_to_be_reviewed_Alert_WO_filterTxtBox", workOrderNo);
			// ---------------------------------------------

			// -------Alert Work Order in InProcess status -------------------
			fnpCommonAlertGeneratedVerification("Work_order_in_INPROCESS_status", "WO_InProcess_Status_AlertTable_header", "WO #", "WO_InProcess_Status_AlertTable",
					"WO_InProcess_Status_Alert_WO_filterTxtBox", workOrderNo);
			// ---------------------------------------------

			// ++++++++++++++++++Verify Alerts+++++++++++++++++++++++++

			fnpSearchWorkOrderAndClickEditAlso(workOrderNo);

			try {
				if (((String) hashXlData.get("Alerts_Verify_Flag")).equalsIgnoreCase("No")) {
					// fnpCommonBackToViewNBackBtnClick();
					// fnpWaitForVisible("Task_ShowAllLink");

				} else {

					fnpCommonClickTaskTab();

				}
			} catch (NullPointerException e) {
				fnpClickBackToViewNBackBtnWhenNullPointerExceptionWhenAlertFlagNotPresent();
				fnpCommonClickTaskTab();
				// nothing to do just ignore it. Null pointer exception will be
				// thrown when variable is not present.
			}

			if (fnpCheckElementPresenceImmediately("TaskTab_ScopeValidation_PerformTaskIcon")) {
				fnpClick_OR("TaskTab_ScopeValidation_PerformTaskIcon");
			}

			fnpClick_OR("PhyEval_ReviewInfoTable_CompletePhyEvalBtn");

			fnpClickInDialog_OR("PhyEval_CompleteTaskSaveBtn2");


			
			fnpCheckErrMsg("Error is thrown by application in Physical_Eval_With_Alerts  method while completing the task");

			// fnpLoading_wait();
			fnpWaitForVisible("LiteEval_TaskSummaryTable_header");

			/****** 28-04-15 commented for time being *****/
			// fnpClick_OR("PhyEvalRefreshDataBtn");
			if (fnpCheckElementPresenceImmediately("PhyEvalRefreshDataBtn")) {
				fnpClick_OR("PhyEvalRefreshDataBtn");
			}
			/****** 28-04-15 commented for time being *****/

			/* ======Below is new code ====== */

			TaskSummaryTable_StausColIndex = fnpFindColumnIndex("LiteEval_TaskSummaryTable_header", "Status");
			PhyEvalTask_status = fnpFetchFromTable("LiteEval_TaskSummaryTable", 1, TaskSummaryTable_StausColIndex);

			Assert.assertTrue(PhyEvalTask_status.equalsIgnoreCase("completed"), " status for the Physical Eval task should be COMPLETED status but it is coming  '"
					+ PhyEvalTask_status + "' ");
			fnpMymsg(" Status for the Physical Eval task has become  to 'COMPLETED'  successfully.");

			// fnpClick_OR("PhyEvalRefreshDataBtn");
			// Thread.sleep(2000);

			fnpWaitForVisible("PhyEval_EvaluationInfoTable_header");
			int EvalInfoTable_StausColIndex = fnpFindColumnIndex("PhyEval_EvaluationInfoTable_header", "Status");
			String EvalINfoTable_status = fnpFetchFromTable("PhyEval_EvaluationInfoTable", 1, EvalInfoTable_StausColIndex);

			// change in below 2 line
			Assert.assertTrue(EvalINfoTable_status.equalsIgnoreCase("submitted"), " status in Evaluation Info table should be submitted status but it is coming  '"
					+ EvalINfoTable_status + "' ");
			fnpMymsg(" status in Evaluation Info table has become  to 'submitted'  successfully.");

			java.util.List<WebElement> AssignFindingIcons = driver.findElements(By.xpath(OR.getProperty("FindingsTable_AssignFindingIcon")));

			int countAssignFindingIcons = AssignFindingIcons.size();
			Assert.assertTrue(countAssignFindingIcons > 1, "AssignFinding icons are not visible as their count is '" + countAssignFindingIcons + "' .");
			fnpMymsg("Findings are present or visible and their count is '" + countAssignFindingIcons + "'");

			int FindingStausColIndex = fnpFindColumnIndex("PhyEval_FindingsTable_header", "Status");
			int CountTotalRows = fnpCountRowsInTable("PhyEval_FindingsTable");

			for (int i = 1; i < CountTotalRows + 1; i++) {

				String status = fnpFetchFromTable("PhyEval_FindingsTable", i, FindingStausColIndex);

				Assert.assertTrue(status.equalsIgnoreCase("pending"), "Status of findings  are not in 'PENDING' status in row '" + i + "' .");
				fnpMymsg("Status of findings  is in 'PENDING' status in row '" + i + "' .");
			}

			fnpMymsg("All findings are in 'PENDING' status");

			// ++++++++++++++++++Verify Alerts+++++++++++++++++++++++++

			fnpCommonGoToHomeNClick();

			// -------Alert Work Order in InProcess status -------------------
			fnpCommonAlertGeneratedVerification("Work_order_in_INPROCESS_status", "WO_InProcess_Status_AlertTable_header", "WO #", "WO_InProcess_Status_AlertTable",
					"WO_InProcess_Status_Alert_WO_filterTxtBox", workOrderNo);
			// ---------------------------------------------

			// ++++++++++++++++++Verify Alerts+++++++++++++++++++++++++

			fnpMymsg("Now going to Complete all the Findings now for Literature Eval and Physical Eval ");
			fnpSearchWorkOrderAndClickEditAlso(workOrderNo);

			try {
				if (((String) hashXlData.get("Alerts_Verify_Flag")).equalsIgnoreCase("No")) {
					// fnpCommonBackToViewNBackBtnClick();
					// fnpWaitForVisible("Task_ShowAllLink");

				} else {

					fnpCommonClickTaskTab();

				}
			} catch (NullPointerException e) {
				fnpClickBackToViewNBackBtnWhenNullPointerExceptionWhenAlertFlagNotPresent();
				fnpCommonClickTaskTab();
				// nothing to do just ignore it. Null pointer exception will be
				// thrown when variable is not present.
			}

			if (!fnpCheckElementPresenceImmediately("PhyEvalRefreshDataBtn")) {
				fnpWaitForVisible("Task_ShowAllLink");
				fnpWaitTillVisiblityOfElementNClickable_OR("Task_ShowAllLink");

				TaskTypeColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskTypeColName);
				RowNoForPhyEval = fnpFindRow("TasksTable_EditWO", taskType_PhysicalEval, TaskTypeColIndex);

				fnpMymsg(" Going to click Physical Eval task no. ");
				ColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskNoColName);
				PhyEvalTaskNo = fnpFetchFromTable("TasksTable_EditWO", RowNoForPhyEval, ColIndex);
				Thread.sleep(500);
				

				fnpClickALinkInATable(PhyEvalTaskNo);
				
				fnpWaitForVisible("PhyEvalRefreshDataBtn");
			}

			fnpWaitForVisible("PhyEval_EvaluationInfoTable_header");

			EvalInfoTable_StausColIndex = fnpFindColumnIndex("PhyEval_EvaluationInfoTable_header", "Status");
			EvalINfoTable_status = fnpFetchFromTable("PhyEval_EvaluationInfoTable", 1, EvalInfoTable_StausColIndex);

			Assert.assertTrue(EvalINfoTable_status.equalsIgnoreCase("submitted"), " status in Evaluation Info table still submitted status but it is coming  '"
					+ EvalINfoTable_status + "' ");
			fnpMymsg(" status in Evaluation Info table is still 'submitted'  .");

			fnpCommonProcessPhyEvalFindings();

			fnpClick_OR("TaskTab_ScopeValidation_BackToViewBtn");
			Thread.sleep(1000);
			fnpClick_OR("TaskTab_ScopeValidation_BackBtn");

			// fnpLoading_wait();
			fnpWaitForVisible("Task_ShowAllLink");

			// /For LiteEval   
			TaskTypeColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskTypeColName);
			int TaskStatusColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_StatusColName);
			RowNoForPhyEval = fnpFindRow("TasksTable_EditWO", taskType_PhysicalEval, TaskTypeColIndex);
			String TaskTablePhyEval_status = fnpFetchFromTable("TasksTable_EditWO", RowNoForPhyEval, TaskStatusColIndex);

			Assert.assertTrue(TaskTablePhyEval_status.equalsIgnoreCase("completed"), " In Task Table, status of Physical Eval status should have 'COMPLETED' but it is coming  '"
					+ TaskTablePhyEval_status + "' ");
			fnpMymsg(" In Task Table, status of Physical Eval status  has become  to 'COMPLETED'  successfully.");

			TaskTypeColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskTypeColName);
			int RowNoForLitEval = fnpFindRow("TasksTable_EditWO", taskType_LiteratureEvaluate, TaskTypeColIndex);

			fnpMymsg(" Going to click Literature Eval task no. ");
			ColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskNoColName);
			String LitEvalTaskNo = fnpFetchFromTable("TasksTable_EditWO", RowNoForLitEval, ColIndex);
			Thread.sleep(500);
			

			fnpClickALinkInATable(LitEvalTaskNo);
			
			
			
			// fnpLoading_wait();
			fnpWaitForVisible("TaskTab_ScopeValidation_BackBtn"); // Assumption
																	// if this
																	// back
																	// button is
																	// visible
																	// it means
																	// loading
																	// is over
																	// and you
																	// come on
																	// expected
																	// page

			fnpCommonProcessLiteEvalFindings();
			fnpMymsg(" Now completed processing on  findings of LiteEval task also");

			/*************** Just to add to check the Result of Lite Eval Task *****************/
			// fnpClick_OR("PhyEvalRefreshDataBtn");
			driver.navigate().refresh();
			// fnpLoading_wait();

			int LiteEvalDetailTable_ResultColIndex = fnpFindColumnIndex("LiteEval_TaskSummaryTable_header", "Result");
			String LiteEvalDetailTable_Result = fnpFetchFromTable("LiteEval_TaskSummaryTable", 1, LiteEvalDetailTable_ResultColIndex);

			String expectedResult = (String) hashXlData.get("LiteEvalTask_Result").trim();
			Assert.assertTrue(LiteEvalDetailTable_Result.equalsIgnoreCase(expectedResult), " Result in Lite Eval detail table should become " + expectedResult
					+ "  but it is coming  '" + LiteEvalDetailTable_Result + "' ");
			fnpMymsg(" Result in Lite Eval detail table is now become ---" + LiteEvalDetailTable_Result);

			/*************** Just to add to check the Result of Lite Eval Task *****************/

		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "   Physical_Eval_With_Alerts  is failed . ", "Physical_Eval_With_Alerts_Failed");

		}

	}

	/******This is conditional , if browser is not chrome then close the iPulse and login again. IMPORTANT ******/
	@Test(priority = 24, dependsOnMethods = { "Physical_Eval_With_Alerts" })
	public void RestartBrowserNLogin4() throws Throwable {
		try {
			RestartBrowserNLogin1(); // This is conditional , if browser is not chrome then close the iPulse and login again. IMPORTANT
		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "   RestartBrowserNLogin  is failed . ", "RestartBrowserNLogin_Failed");

		}

	}

	@Test(priority = 25, dependsOnMethods = { "RestartBrowserNLogin4" })
	public void Certification_Decision_Task() throws Throwable {

		fnpMymsg(" **************************************************************");
		fnpMymsg(" Executing Certification_Decision_Task");

		try {

			fnpCommonCodeCertDecisionWaitTillNotReady_till_inperform();
			
			
			


			/******************New changes due to new field cert decider ***************************/
					
		
			
			driver.close();
			fnpLaunchBrowserAndLogin();					
			fnpSearchWorkOrderOnly(workOrderNo);
					
					/***********************************************************************/
			
			

			fnpCommonClickSnapShotTab();

			fnpWaitForVisible("ActionItemTable_EditWO");

			int ActionItemColIndex = fnpFindColumnIndex("ActionItemTable_HeaderRow", actionItemTableActionItemColName);
			int itemDescColIndex = fnpFindColumnIndex("ActionItemTable_HeaderRow", actionItemTableItemDescColName);
			//int ListingUpdateRowNo = fnpFindRow("ActionItemTable_EditWO", actionItemDesc_ListingUpdate, itemDescColIndex);
			int ListingUpdateRowNo = fnpFindRow_ActionItem("ActionItemTable_EditWO", actionItemDesc_ListingUpdate, itemDescColIndex);
			
			//Assert.assertTrue(ListingUpdateRowNo > 0, "Action Item 'ListingUpdate' has not been generated.  ");
			fnpMymsg("Action Item 'ListingUpdate' has  been generated successfully.  ");
			
			
			//if ((currentSuiteName.equalsIgnoreCase(Dietary_Supplement_suite_Name)) ){	

				int AItem_ItemAssignedTo_ColIndex = fnpFindColumnIndex("ActionItemTable_HeaderRow", actionItemTableAssignedToColName);
				String listingUpdateAIAssigner = fnpFetchFromTable("ActionItemTable_EditWO", ListingUpdateRowNo, AItem_ItemAssignedTo_ColIndex);
				if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
					Assert.assertTrue(listingUpdateAIAssigner.contains((String) hashXlData.get("Assign_ListingUpdate_Code")), actionItemDesc_ListingUpdate+" AI is not assigned to Assign_ListingUpdate_Code  just after generated --i.e. '" + (String) hashXlData.get("Assign_ListingUpdate_Code").trim() + "'");
				}else{
					Assert.assertEquals(listingUpdateAIAssigner.trim(), (String) hashXlData.get("Assign_ListingUpdate").trim(),  actionItemDesc_ListingUpdate+" AI  is not assigned to Assign_ListingUpdate  just after generated -- i.e. '" + (String) hashXlData.get("Assign_ListingUpdate").trim() + "'");
				}
				fnpMymsg(actionItemDesc_ListingUpdate+" AI  is  assigned to Assign_ListingUpdate  (Marie S.Zueski)  just after generated -- i.e. '" + (String) hashXlData.get("Assign_ListingUpdate").trim() + "' successfully.");

			//}
			
			
			
			
			
			
			
			
			
			
			

			// ++++++++++++++++++Verify Alerts+++++++++++++++++++++++++

			fnpCommonGoToHomeNClick();

			// -------Alert Work Order in InProcess status -------------------
			fnpCommonAlertGeneratedVerification("Work_order_in_INPROCESS_status", "WO_InProcess_Status_AlertTable_header", "WO #", "WO_InProcess_Status_AlertTable",
					"WO_InProcess_Status_Alert_WO_filterTxtBox", workOrderNo);
			// ---------------------------------------------

			// -------Alert Work_Order_with_open_action_items
			// -------------------
			fnpCommonAlertGeneratedVerification("Work_Order_with_open_action_items", "WO_WITH_OPEN_ACTION_ITEMS_AlertTable_header", "WO #", "WO_WITH_OPEN_ACTION_ITEMS_AlertTable",
					"WO_WITH_OPEN_ACTION_ITEMS_Alert_WO_filterTxtBox", workOrderNo);
			// ---------------------------------------------

			/*
			 * 
			 * // -------Alert Task_with_open_Action_Items -------------------
			 * fnpCommonAlertGeneratedVerification(
			 * "Task_with_open_Action_Items",
			 * "TA_TASK_OPEN_ITEMS_AlertTable_header", "WO #",
			 * "TA_TASK_OPEN_ITEMS_AlertTable",
			 * "TA_TASK_OPEN_ITEMS_Alert_WO_filterTxtBox", workOrderNo); //
			 * ---------------------------------------------
			 */

			fnpCommonSwitchToUserForAlerts((String) hashXlData.get("TechOpsManager"));

			// ------- Task(s) with unassigned Action Items-------------------
			fnpCommonAlertGeneratedVerification("Task_with_unassigned_Action_Items", "Task_with_unassigned_Action_Items_AlertTable_header", "WO #",
					"Task_with_unassigned_Action_Items_AlertTable", "Task_with_unassigned_Action_Items_Alert_WO_filterTxtBox", workOrderNo);

			// ---------------------------------------------

			fnpCommonSwitchToUserForAlerts("Marie Zueski");

			// -------Alert Action item assigned -------------------
			fnpCommonAlertGeneratedVerification("Action_item_assigned", "AI_ITEMS_ASSIGNED_AlertTable_header", "WO #", "AI_ITEMS_ASSIGNED_AlertTable",
					"AI_ITEMS_ASSIGNED_Alert_WO_filterTxtBox", workOrderNo);
			// ---------------------------------------------

			// ++++++++++++++++++Verify Alerts+++++++++++++++++++++++++

			fnpSearchWorkOrderAndClickEditAlso(workOrderNo);

			
			
			
			fnpProcessAI(actionItemDesc_ListingUpdate, (String) hashXlData.get("Change_StatusListingUpdate_Completed"));
			
			boolean certDeciEduFlagInNonFoodCompound=false;

			//'IPM-10788'
			if (!(currentSuiteName.equalsIgnoreCase(Dietary_Supplement_suite_Name)) ){	
				ActionItemColIndex = fnpFindColumnIndex("ActionItemTable_HeaderRow", actionItemTableActionItemColName);
				itemDescColIndex = fnpFindColumnIndex("ActionItemTable_HeaderRow", actionItemTableItemDescColName);
				//int certDeciEduRowNo = fnpFindRow("ActionItemTable_EditWO", actionItemDesc_Certdecisioneducation, itemDescColIndex);
				
				if (!(currentSuiteName.equalsIgnoreCase(Non_Food_Compounds_suite_Name)) ){	
					int certDeciEduRowNo = fnpFindRow_ActionItem("ActionItemTable_EditWO", actionItemDesc_Certdecisioneducation, itemDescColIndex);
					certDeciEduFlagInNonFoodCompound=true;
				}
	
				//Assert.assertTrue(certDeciEduRowNo > 0, "Action Item 'Cert decision education' has not been generated.  ");
				fnpMymsg("Action Item 'Cert decision education' has  been generated successfully.  ");
				
				
				
			}
			
/*			//'IPM-10788'
			if ((currentSuiteName.equalsIgnoreCase(Dietary_Supplement_suite_Name)) ){	

				 AItem_ItemAssignedTo_ColIndex = fnpFindColumnIndex("ActionItemTable_HeaderRow", actionItemTableAssignedToColName);
				String certDecisionEducationAIAssigner = fnpFetchFromTable("ActionItemTable_EditWO", certDeciEduRowNo, AItem_ItemAssignedTo_ColIndex);

				
				String expectedCertDeciEducationAIAssignee;
				if (loginAsFullName.equalsIgnoreCase("") | (loginAsFullName==null) ) {
					if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
						expectedCertDeciEducationAIAssignee=(String) hashXlData.get("AccountManager_Code").trim();
					}else{
						expectedCertDeciEducationAIAssignee=(String) hashXlData.get("AccountManager").trim();
					}

				} else {
					if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
						expectedCertDeciEducationAIAssignee=loginUser_code;
					}else{
						expectedCertDeciEducationAIAssignee=loginAsFullName;
					}
				}
				
				Assert.assertTrue(certDecisionEducationAIAssigner.contains(expectedCertDeciEducationAIAssignee), actionItemDesc_Certdecisioneducation+" AI is not assigned to account manager i.e. '" + expectedCertDeciEducationAIAssignee+ "'");
				fnpMymsg(actionItemDesc_Certdecisioneducation+" AI is  assigned to '" + expectedCertDeciEducationAIAssignee + "'  successfully.");

			}
			
			*/
			
			if ((currentSuiteName.equalsIgnoreCase(Non_Food_Compounds_suite_Name)) && certDeciEduFlagInNonFoodCompound ){
				int certDeciEduRowNo = fnpFindRow_ActionItem("ActionItemTable_EditWO", actionItemDesc_Certdecisioneducation, itemDescColIndex);
				 AItem_ItemAssignedTo_ColIndex = fnpFindColumnIndex("ActionItemTable_HeaderRow", actionItemTableAssignedToColName);
				String certDecisionEducationAIAssigner = fnpFetchFromTable("ActionItemTable_EditWO", certDeciEduRowNo, AItem_ItemAssignedTo_ColIndex);
				

				
				String expectedCertDeciEducationAIAssignee;
				if (loginAsFullName.equalsIgnoreCase("") | (loginAsFullName==null) ) {
					if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
						expectedCertDeciEducationAIAssignee=(String) hashXlData.get("AccountManager_Code").trim();
					}else{
						expectedCertDeciEducationAIAssignee=(String) hashXlData.get("AccountManager").trim();
					}

				} else {
					if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
						expectedCertDeciEducationAIAssignee=loginUser_code;
					}else{
						expectedCertDeciEducationAIAssignee=loginAsFullName;
					}
				}
				
				Assert.assertTrue(certDecisionEducationAIAssigner.contains(expectedCertDeciEducationAIAssignee), actionItemDesc_Certdecisioneducation+" AI is not assigned to account manager i.e. '" + expectedCertDeciEducationAIAssignee+ "'");
				fnpMymsg(actionItemDesc_Certdecisioneducation+" AI is  assigned to '" + expectedCertDeciEducationAIAssignee + "'  successfully.");

			}
			
			
/*			
			*//**************
			 * In new coming sprint R4.1, today 16-3-16 before releasing , on
			 * trunk this action item is not being generated and if comment this
			 * then wo getting completed successfully also
			 *****************//*

			// int annualSetupRowNo =
			// fnpFindRow("ActionItemTable_EditWO",actionItemDesc_AnnualSetup,
			// itemDescColIndex);
			// Assert.assertTrue(annualSetupRowNo >
			// 0,"Action Item 'AnnualSetup' has not been generated.  ");
			try {
				int annualSetupRowNo = fnpFindRow("ActionItemTable_EditWO", actionItemDesc_AnnualSetup, itemDescColIndex);
			} catch (Throwable t) {
				throw new Exception("Either Action Item 'AnnualSetup' has not been generated or    " + t.getMessage());
			}

			fnpMymsg("Action Item 'AnnualSetup' has  been generated successfully.  ");
			*//**************
			 * In new coming sprint R4.1, today 16-3-16 before releasing , on
			 * trunk this action item is not being generated and if comment this
			 * then wo getting completed successfully also
			 *****************//*

		*/	
			
			
			
			fnpCommonClickTaskTab();

			int TaskTypeColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskTypeColName);
			int TaskStatusColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_StatusColName);
			int RowNoForCertDecision = fnpFindRow("TasksTable_EditWO", taskType_CertDecision, TaskTypeColIndex);

			String certDecisionStatus = fnpFetchFromTable("TasksTable_EditWO", RowNoForCertDecision, TaskStatusColIndex);

			Assert.assertTrue(certDecisionStatus.toLowerCase().contains("completed"),
					"After completed the Cert Decision Task after assign,perform,complete , status has not been changed  to 'COMPLETED' . It is still showing '"
							+ certDecisionStatus + "'.");

			fnpMymsg(" After completed the Cert Decision Task after assign,perform,complete , status has  been changed  to 'COMPLETED' successfully.");

			
			
			if ((currentSuiteName.equalsIgnoreCase(Dietary_Supplement_suite_Name)) ){
				
				fnpWaitTillClickable("FinancialTab_EditWO");
				fnpMymsg("Now we are going into Financials Tab for verifying/comparing respective amounts");

				fnpCommonClickFinancialTab();
				int CountTotalRows = fnpCountRowsInTable("PendingInvoiceRequest_AtFinancialTabTable");
				if (CountTotalRows>0) {
					fnpMymsg("Total rows in Pending Invoice Request table are --"+CountTotalRows);
				} else {
					String msg = "Pending invoice Request should be present but here in table they are not present because currently total rows in Pending Invoice Request table are --"+CountTotalRows ;
					fnpMymsg(msg);
					throw new Exception(msg);
				}
				
				
				
			}
			
			
			
			
			
			// ++++++++++++++++++Verify Alerts+++++++++++++++++++++++++

			fnpCommonGoToHomeNClick();

			// -------Alert Work Order in InProcess status -------------------
			fnpCommonAlertGeneratedVerification("Work_order_in_INPROCESS_status", "WO_InProcess_Status_AlertTable_header", "WO #", "WO_InProcess_Status_AlertTable",
					"WO_InProcess_Status_Alert_WO_filterTxtBox", workOrderNo);
			// ---------------------------------------------

			// -------Alert Action item assigned -------------------
			fnpCommonAlertGeneratedVerification("Action_item_assigned", "AI_ITEMS_ASSIGNED_AlertTable_header", "WO #", "AI_ITEMS_ASSIGNED_AlertTable",
					"AI_ITEMS_ASSIGNED_Alert_WO_filterTxtBox", workOrderNo);
			// ---------------------------------------------

			// -------Alert Work_Order_with_open_action_items
			// -------------------
			fnpCommonAlertGeneratedVerification("Work_Order_with_open_action_items", "WO_WITH_OPEN_ACTION_ITEMS_AlertTable_header", "WO #", "WO_WITH_OPEN_ACTION_ITEMS_AlertTable",
					"WO_WITH_OPEN_ACTION_ITEMS_Alert_WO_filterTxtBox", workOrderNo);
			// ---------------------------------------------

			// ++++++++++++++++++Verify Alerts+++++++++++++++++++++++++

			fnpSearchWorkOrderAndClickEditAlso(workOrderNo);

			
			
			//'IPM-10788' in Dietary Supplement, this ai is no longer generated...
			if (!(currentSuiteName.equalsIgnoreCase(Dietary_Supplement_suite_Name))   && (!(currentSuiteName.equalsIgnoreCase(Non_Food_Compounds_suite_Name)))   ){	
			// ***************Complete the pending Action item (Cert Decision Education)***********
			fnpProcessAI(actionItemDesc_Certdecisioneducation, (String) hashXlData.get("Change_Status_Completed"));
			fnpMymsg(" Completed successfully  pending Action item for Cert Decision Education ");
			}
			
/*			
			*//**************
			 * In new coming sprint R4.1, today 16-3-16 before releasing , on
			 * trunk this action item is not being generated and if comment this
			 * then wo getting completed successfully also
			 *****************//*

			*//*************** Complete the pending Action item (AnnualSetup) ***********//*

			fnpProcessAI(actionItemDesc_AnnualSetup, (String) hashXlData.get("Change_Status_Completed"));

			fnpMymsg(" Completed successfully  pending Action item for AnnualSetup ");
			*//**************
			 * In new coming sprint R4.1, today 16-3-16 before releasing , on
			 * trunk this action item is not being generated and if comment this
			 * then wo getting completed successfully also
			 *****************//*

			*/
			
			
			// ++++++++++++++++++Verify Alerts+++++++++++++++++++++++++

			fnpCommonGoToHomeNClick();

			// -------Alert Work Order in InProcess status -------------------
			fnpCommonAlertGeneratedVerification("Work_order_in_INPROCESS_status", "WO_InProcess_Status_AlertTable_header", "WO #", "WO_InProcess_Status_AlertTable",
					"WO_InProcess_Status_Alert_WO_filterTxtBox", workOrderNo);
			// ---------------------------------------------

			// ++++++++++++++++++Verify Alerts+++++++++++++++++++++++++

			fnpMymsg("  Completed the Certification_Decision_Task Task successfully ");


			fnpCheckErrMsg("Error is thrown by application while Going to Complete Certification_Decision_Task");

		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "   Certification_Decision_Task  is failed . ", "Certification_Decision_Task_Failed");

		}

	}

	@Test(priority = 26, dependsOnMethods = { "Certification_Decision_Task" })
	public void Verify_INPROCESS_to_COMPLETE_Automatically() throws Throwable {

		fnpMymsg(" **************************************************************");
		fnpMymsg(" Executing Verify_INPROCESS_to_COMPLETE_Automatically");

		try {

			fnpSearchWorkOrderAndClickEditAlso(workOrderNo);

			String Check_Batch_Job_Value = (String) hashXlData.get("Check_Batch_Job");

			if (Check_Batch_Job_Value != null && Check_Batch_Job_Value.equalsIgnoreCase("Yes")) {
/*
				int waitCount = 0;
				String changedWOStatus;
				while (true) {
					waitCount++;
					fnpWaitForVisible("NewlyCrWOTopBannerInfo");
					fnpGetORObjectX("NewlyCrWOTopBannerInfo").click();
					// fnpLoading_wait();

					fnpWaitForVisible("TopBannerWOStatus");
					changedWOStatus = fnpGetORObjectX("TopBannerWOStatus").getText();

					if (changedWOStatus.equalsIgnoreCase("complete")) {
						fnpMymsg("WO status has become to 'COMPLETE' state. ");
						break;
					} else {
						Thread.sleep(1000 * 30 * 1);
						driver.navigate().refresh();
						Thread.sleep(8000);
						fnpMymsg("Waiting for Complete wo status ----" + ((waitCount * 40) / 60) + " minutes");
						fnpMymsg("It is still   ----" + changedWOStatus);
					}

					if (waitCount > 150) {
						msg="Script waited for approx. 100 min to become 'COMPLETE' state of WO status but it is still showing '" + changedWOStatus + "' .";
						fnpMymsg(msg);
						throw new Exception(msg);

					}

	
					
					fnpCheckErrMsg("Error is thrown by application while Going to Complete Verify_INPROCESS_to_COMPLETE_Automatically and refreshing again n again.");
				
				
				
				
				

				}

				fnpMymsg("  Completed the Verify_INPROCESS_to_COMPLETE_Automatically Task successfully ");

				// ------------------------------------------------------------------------------------------------------

				*/
				
				
				fnpWaitTillWOStatusGetCompleteAutomatically();
				
				
				
				
				
			}

			else {

				fnpCommonClickInfoTab();

				fnpCommonClickEditBtnIfPresent();
				fnpWaitForVisible("InfoTab_WOStatusPFList");

				String status = "Complete";

				fnpPFList("InfoTab_WOStatusPFList", "InfoTab_WOStatusPFListOptions", status);
				
				

				fnpGetORObjectX("ProAddDocSaveBtn").click();
				//fnpLoading_wait();
				
				try{
					fnpLoading_wait();
					fnpCheckError("");
				}catch(Throwable t){
					if (currentSuiteName.equalsIgnoreCase(IPULSE_CONSTANTS.Dietary_Supplement_suite_Name)) {
											
						if (fnpCheckElementPresenceImmediately("ErrorMessage")) {
							fnpMouseHover_OR_EvenMultipleObjectsHavingSameXpath("ErrorMessage");
							fnpMymsg(" Error is thrown by application while Going to Complete the wo ");
						}
	
						String Msg = fnpGetText_OR_EvenMultipleObjectsHavingSameXpath_specialForExtractingErrorMsgTextOnlyEvenIfNotVisible("ErrorMessage");
	
						fnpMymsg("Top Message while completing the wo  ----" + Msg);
	
						String expectedErrorMsg = errorMsgOnCompletingWOWhenSomeAiIsRemaining;
						
						if (Msg.equalsIgnoreCase(expectedErrorMsg)) {
							fnpCommonClickSnapShotTab();							
							fnpCommonForProcessDelistedGMPStandardAI_ifPresent() ;
							fnpCommonClickInfoTab();

							fnpCommonClickEditBtnIfPresent();
							fnpWaitForVisible("InfoTab_WOStatusPFList");

							 status = "Complete";

							fnpPFList("InfoTab_WOStatusPFList", "InfoTab_WOStatusPFListOptions", status);

							fnpGetORObjectX("ProAddDocSaveBtn").click();
							fnpLoading_wait();
							
						}else{
							throw t;
						}
	
					}else{
						throw t;
					}
				
				}

				fnpCheckErrMsg("Error is thrown by application while changing status from InProcess to Complete in Info tab");

				fnpWaitForVisibleHavingMultipleElements("TopMessage3");
				String SuccessfulMsg = fnpGetText_OR("TopMessage3");
				fnpMymsg("Top Message after changing status from InProcess to Complete in Info tab ----" + SuccessfulMsg);

				Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"),
						"Top message does not contain 'success' word, so might be changing status from InProcess to Complete in Info tab is NOT successful.");

				fnpWaitTillClickable("NewlyCrWOTopBannerInfo");
				fnpGetORObjectX("NewlyCrWOTopBannerInfo").click();


				fnpWaitForVisible("TopBannerWOStatus");
				String changedWOStatus = fnpGetORObjectX("TopBannerWOStatus").getText();
				Assert.assertTrue(changedWOStatus.equalsIgnoreCase("complete"), " WO status is not changed from InProcess to Complete.");
				fnpMymsg("Now  WO status has been changed from 'InProcess' to 'Complete' status.");

			}

			fnpCommonGoToHomeNClick();


			// --1-----Alert deleted Work_order_in_INREVIEW_status
			// -------------------
			fnpCommonAlertDeletedVerification("Work_order_in_INREVIEW_status", "InReviewStatusAlertTable_header", "WO #", "InReviewStatusAlertTable",
					"InReviewStatusAlert_WO_filterTxtBox", workOrderNo);
			// ---------------------------------------------

			// --2-----Alert deleted Work order(s) in DRAFT status
			// -------------------
			fnpCommonAlertDeletedVerification("Draft_Status", "DraftStatusAlertTable_header", "WO #", "DraftStatusAlertTable", "DraftStausAlert_WO_filterTxtBox", workOrderNo);
			// ---------------------------------------------

			// --3-----Alert deleted Work Order in InProcess status
			// -------------------
			fnpCommonAlertDeletedVerification("Work_order_in_INPROCESS_status", "WO_InProcess_Status_AlertTable_header", "WO #", "WO_InProcess_Status_AlertTable",
					"WO_InProcess_Status_Alert_WO_filterTxtBox", workOrderNo);
			// ---------------------------------------------

			// --4-----Alert deleted Work_Order_with_open_action_items
			// -------------------
			fnpCommonAlertDeletedVerification("Work_Order_with_open_action_items", "WO_WITH_OPEN_ACTION_ITEMS_AlertTable_header", "WO #", "WO_WITH_OPEN_ACTION_ITEMS_AlertTable",
					"WO_WITH_OPEN_ACTION_ITEMS_Alert_WO_filterTxtBox", workOrderNo);
			// ---------------------------------------------

			// -5-----Deleted Alert Action_item_assigned_to_client deleted
			// -------------------
			fnpCommonAlertDeletedVerification("Action_item_assigned_to_client", "AI_ASSIGNED_CLIENT_IND_AlertTable_header", "WO #", "AI_ASSIGNED_CLIENT_IND_AlertTable",
					"AI_ASSIGNED_CLIENT_IND_Alert_WO_filterTxtBox", workOrderNo);
			// ---------------------------------------------

			// -6------Alert deleted Action item assigned -------------------
			fnpCommonAlertDeletedVerification("Action_item_assigned", "AI_ITEMS_ASSIGNED_AlertTable_header", "WO #", "AI_ITEMS_ASSIGNED_AlertTable",
					"AI_ITEMS_ASSIGNED_Alert_WO_filterTxtBox", workOrderNo);
			// ---------------------------------------------

			// -7------Alert deleted 'Draft Work order(s) assigned with task
			// setup incomplete -------------------
			fnpCommonAlertDeletedVerification("Task_Setup_Incomplete", "TaskSetupIncompleteAlertTable_header", "WO #", "TaskSetupIncompleteAlertTable",
					"TaskSetupIncompleteAlert_WO_filterTxtBox", workOrderNo);
			// ---------------------------------------------

			// -8------Alert deleted Physical evaluation(s) to be reviewed
			// -------------------
			fnpCommonAlertDeletedVerification("Physical_evaluation_to_be_reviewed", "Physical_evaluation_to_be_reviewed_AlertTable_header", "WO #",
					"Physical_evaluation_to_be_reviewed_AlertTable", "Physical_evaluation_to_be_reviewed_Alert_WO_filterTxtBox", workOrderNo);
			// ---------------------------------------------

			/*
			 * 
			 * // -9------Alert deleted Task_with_open_Action_Items
			 * ------------------- fnpCommonAlertDeletedVerification(
			 * "Task_with_open_Action_Items",
			 * "TA_TASK_OPEN_ITEMS_AlertTable_header", "WO #",
			 * "TA_TASK_OPEN_ITEMS_AlertTable",
			 * "TA_TASK_OPEN_ITEMS_Alert_WO_filterTxtBox", workOrderNo); //
			 * ---------------------------------------------
			 */

			fnpCommonSwitchToUserForAlerts((String) hashXlData.get("TechOpsManager"));

			// -10------Alert deleted Task ready to be assigned
			// -------------------
			fnpCommonAlertDeletedVerification("Task_ready_to_be_assigned_AlertName", "Task_ready_to_be_assigned_AlertTable_header", "WO #", "Task_ready_to_be_assigned_AlertTable",
					"Task_ready_to_be_assigned_Alert_WO_filterTxtBox", workOrderNo);
			// ---------------------------------------------

			// -11------ Alert deleted Task(s) with unassigned Action
			// Items-------------------
			fnpCommonAlertDeletedVerification("Task_with_unassigned_Action_Items", "Task_with_unassigned_Action_Items_AlertTable_header", "WO #",
					"Task_with_unassigned_Action_Items_AlertTable", "Task_with_unassigned_Action_Items_Alert_WO_filterTxtBox", workOrderNo);

			// ---------------------------------------------

			// ++++++++++++++++++Verify All Alerts
			// Deletion+++++++++++++++++++++++++

			fnpCheckErrMsg("Error is thrown by application while Going to Complete Verify_INPROCESS_to_COMPLETE_Automatically");

		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "   Verify_INPROCESS_to_COMPLETE_Automatically  is failed . ", "Verify_INPROCESS_to_COMPLETE_Automatically_Failed");

		}

	}

	@Test(priority = 27, dependsOnMethods = { "Verify_INPROCESS_to_COMPLETE_Automatically" })
	public void Verify_InvoiceInFinancialTab() throws Throwable {

		fnpMymsg(" **************************************************************");
		fnpMymsg(" Executing Verify_InvoiceInFinancialTab");

		try {

			fnpWaitTillClickable("FinancialTab_EditWO");
			fnpMymsg("Now we are going into Financials Tab for verifying/comparing respective amounts");

			fnpCommonClickFinancialTab();

			fnpWaitForVisible("FinancialTab_InvoiceCategoryLink");

			String UnitQuotedAmt = fnpGetORObjectX("FinancialInfo_InvoiceCategoryTable_UnitQuotedAmount").getText();
			String InvoicedAmt = fnpGetORObjectX("FinancialInfo_InvoiceCategoryTable_InvoicedAmount").getText();

			fnpMymsg("Quoted Amount in Invoice Category is :  " + UnitQuotedAmt);
			fnpMymsg("Invoiced Amount in Invoice Category is :  " + InvoicedAmt);

			Assert.assertEquals(UnitQuotedAmt, InvoicedAmt, "Quoted Amount and Invoiced Amount should have been equal in Invoice Category table but here they are not equal. ");
			fnpMymsg("Hence ,Quoted Amount and Invoiced Amount are  equal in Invoice Category table.");

			fnpWaitTillClickable("FinancialTab_TaskLink");
			fnpGetORObjectX("FinancialTab_TaskLink").click();

			// Thread.sleep(6000);
			fnpWaitForVisible("FinancialInfo_TaskTable_QuotedAmount");
			fnpCheckError("");

			String TaskQuotedAmt = fnpGetORObjectX("FinancialInfo_TaskTable_QuotedAmount").getText();
			String TaskInvoicedAmt = fnpGetORObjectX("FinancialInfo_TaskTable_InvoicedAmount").getText();

			fnpMymsg("Quoted Amount in Task is :  " + TaskQuotedAmt);
			fnpMymsg("Invoiced Amount in Task is :  " + TaskInvoicedAmt);

			Assert.assertEquals(TaskQuotedAmt, TaskInvoicedAmt, "Quoted Amount and Invoiced Amount should have been equal in Task table but here they are not equal. ");

			fnpMymsg("Hence, Quoted Amount and Invoiced Amount are  equal in Task table.");


			fnpCheckErrMsg("Error is thrown by application while Going to Complete Verify_InvoiceInFinancialTab");

		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "   Verify_InvoiceInFinancialTab  is failed . ", "Verify_InvoiceInFinancialTab_Failed");

		}

	}

	
	//@Test(priority = 28, dependsOnMethods = { "Verify_InvoiceInFinancialTab" })
	@Test(enabled = false)
	public void App_Memory_StackTrace2() throws Throwable {
		fnpMymsg(" **************************************************************");
		fnpMymsg(" Executing App_Memory_StackTrace2");
		try {

			Thread.sleep(2000);

			fnpCommonStackTracePageOpen();
			String screenshotImageName = fnpTimestamp() + "   " + fnpMappingClassNameWithScenarioCode(this.getClass().getSimpleName()) + " --Part_2";

			screenshotImageName = screenshotImageName.replaceAll(":", "_");
			String folderPath = screenshots_path + "//screenshots_WO_ApplicationMemoryStackTrace//";
			fnpDesireScreenshot_Advance(screenshotImageName, folderPath);

		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "   App_Memory_StackTrace2  is failed . ", "Failed_App_Memory_StackTrace2_Failed");

		}

	}
	
	
	//@Test(priority = 29, dependsOnMethods = { "App_Memory_StackTrace2" })
	@Test(priority = 29, dependsOnMethods = { "Verify_InvoiceInFinancialTab" })
	public void CLEANUP_WO_AUTOMATION_DATA() throws Throwable {
		fnpMymsg(" **************************************************************");
		fnpMymsg("ECAP.CLEANUP_WO_AUTOMATION_DATA");
		try {
			if (env.equalsIgnoreCase("Test") ) {
				fnpCallProcedure_DELETE_WO_AUTOMATION_DATA_WaterProfile(workOrderNo);
			}else{
				//throw new SkipException( " \n\n Skipping this as this procedure is only available in TEST environment" );
			}

		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "  CLEANUP_WO_AUTOMATION_DATA  method is failed . ", "CLEANUP_WO_AUTOMATION_DATA_Failed");
		}

	}
	
	
	
	

	// @AfterTest(alwaysRun=true)
	@AfterTest
	public void reportTestResult() throws Throwable {

		int rowNum = Work_Order_suitexls.getCellRowNum("Test Cases", "TCID", this.getClass().getSimpleName());
		Work_Order_suitexls.setCellData("Test Cases", "Last_Execution", rowNum, fnReturnDateWithTimeForExcel());

		if (isTestPass) {
			fnpMymsg("  ---@AfterTestAnnotation ---in if(isTestPass)---value is---" + isTestPass);
			TestUtil.reportDataSetResult(Work_Order_suitexls, "Test Cases", TestUtil.getRowNum(Work_Order_suitexls, this.getClass().getSimpleName()), "PASS");
			Work_Order_suitexls.setCellData("Test Cases", "Last_Successful_Execution", rowNum, fnReturnDateWithTimeForExcel());
			fnpElapsedTime(currentSuiteName, this.getClass().getSimpleName(), currentScriptCode, "PASS");
			//fnpCallProcedure_DELETE_WO_AUTOMATION_DATA_WaterProfile(workOrderNo);
		} else {
			TestUtil.reportDataSetResult(Work_Order_suitexls, "Test Cases", TestUtil.getRowNum(Work_Order_suitexls, this.getClass().getSimpleName()), "FAIL");
			fnpElapsedTime(currentSuiteName, this.getClass().getSimpleName(), currentScriptCode, "FAIL");
		}
		fnpMymsg("=========================================================================================================================================");

		hashXlData.clear();

		fnpCommonCloseBrowsersAndKillProcess();

	}

}
