package nsf.ecap.Non_Food_Compounds_suite;

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

public class NFC_ExistingNew_Draft_Complete extends TestSuiteBase_Non_Food_Compounds_suite {


	
	
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
	
	
	
	

	
	
	
	//@Test(enabled = false)
	@Test(priority = 1)
	public void CreateWOFlow() throws Throwable {

		BS01.CreateWOFlow();


	}

	//@Test(enabled = false)
	@Test(priority = 2, dependsOnMethods = { "CreateWOFlow" })
	public void AddingData__Facility_Tab() throws Throwable {
		BS01.AddingData__Facility_Tab();

	}

	
	
	//@Test(enabled = false)
	@Test(priority = 3, dependsOnMethods = { "AddingData__Facility_Tab" })
	public void AddingData__Tasks_Tab() throws Throwable {
		BS01.AddingData__Tasks_Tab();

	}
	
	
	
	
	//@Test(enabled = false)
	@Test(priority = 4, dependsOnMethods = { "AddingData__Tasks_Tab" })
	public void AddingData__Products_Tab() throws Throwable {

		BS01.AddingData__Products_Tab();

	}
	
	
	
	//@Test(enabled = false)
	@Test(priority = 5, dependsOnMethods = { "AddingData__Products_Tab" })
	public void Verifying__DRAFT_INREVIEW() throws Throwable {
		try {
			BS16.Verifying__DRAFT_INREVIEW_ModBrack();
			
/*			
			int taskDescColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", SnapShot_taskTable_TaskDescColName);
			int statusColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", SnapShot_taskTable_StatusColName);
			
			String expectedclientAppReviewTaskStatus = (String) hashXlData.get("ExpectedClientAppReviewTaskStatus").trim();
			int clientAppReviewTaskRowNo = fnpFindRow("Snapshot_TasksSummaryTable", SnapShot_Client_App_Review_Task, taskDescColIndex);
			String clientAppReviewTaskStatus = fnpFetchFromTable("Snapshot_TasksSummaryTable", clientAppReviewTaskRowNo, statusColIndex);
			Assert.assertTrue(clientAppReviewTaskStatus.equalsIgnoreCase(expectedclientAppReviewTaskStatus), "Client Quote Task Status is not [" + expectedclientAppReviewTaskStatus + "] .");
			fnpMymsg(" Client App Review Task's  Status in Snapshot tab now becomes in INPERFORM status ");
			fnpMymsg("  VerifyDRAFT_INREVIEW is Pass as status changed to 'INREVIEW' and Action Items has been generated with Pending status except ClientQuoteReview whose status is 'COMPLETED' and Client App Review Task's  status becomes 'INPERFORM' now. ");
			*/
			
			int taskDescColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", SnapShot_taskTable_TaskDescColName);
			int statusColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", SnapShot_taskTable_StatusColName);
			
			int totalTasksRowGenerated = fnpCountRowsInTable("Snapshot_TasksSummaryTable");
			String[] expectedTaskDescArray = ((String) hashXlData.get("Task_generated_after_INREVIEW")).split(",");
			Assert.assertTrue(totalTasksRowGenerated == expectedTaskDescArray.length, "Total Tasks generated must be "+expectedTaskDescArray.length+" (as mentioned in excel) but here they are only '" + totalTasksRowGenerated + "'  .");
			int StatusColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", "Status");
			String statusValue;
			String expectedStatus = "CREATED";
			boolean taskPresentFlag;
			
			for (int i = 0; i < expectedTaskDescArray.length; i++) {
				
				taskPresentFlag=false;
				for (int j = 0; j < totalTasksRowGenerated; j++) {
					String taskDesc= fnpFetchFromTable("Snapshot_TasksSummaryTable", (j+1), taskDescColIndex);
					if (expectedTaskDescArray[i].equalsIgnoreCase(taskDesc)) {
						fnpMymsg(" Task '" + expectedTaskDescArray[i] + "' is presenst at row no '"+(j+1)+"' .");
						taskPresentFlag=true;
						
						statusValue = fnpFetchFromTable("Snapshot_TasksSummaryTable", (j+1), StatusColIndex);

						
						if (taskDesc.equalsIgnoreCase("Client App Review")) {
							String expectedStatus_completed = "INPERFORM";
							Assert.assertTrue(statusValue.equalsIgnoreCase(expectedStatus_completed), "Status is not [" + expectedStatus_completed + "] of this task '"+expectedTaskDescArray[i]+"' which is just created after moving wo to INREVIEW .");
						}else{
							if (taskDesc.contains("Test Description")) {
								String expectedStatus_completed = "DROPPED";
								Assert.assertTrue(statusValue.equalsIgnoreCase(expectedStatus_completed), "Status is not [" + expectedStatus_completed + "] of this task '"+expectedTaskDescArray[i]+"' .");
							}else{
								Assert.assertTrue(statusValue.equalsIgnoreCase(expectedStatus), "Status is not [" + expectedStatus + "] of this action item '"+expectedTaskDescArray[i]+"' which is just created after moving wo to INREVIEW .");
							}
						}
						

						
						
						break;
					}
				}
				
				if (!(taskPresentFlag)) {					
					fnpMymsg("This task '" + expectedTaskDescArray[i] + "' is not present in any row in task table  .");
					throw new Exception("This task'" + expectedTaskDescArray[i] + "' is not present in any row in task table   .");	
				}



			}
			
		
			
			isTestPass = true;
			fnpMymsg(" **************************************************************");
	
			fnpCheckError("while Verifying__DRAFT_INREVIEW");

		} catch (Throwable t) {
	
			fnpCommonFinalCatchBlock(t, "   Verifying__DRAFT_INREVIEW  is failed . ", "Verifying__DRAFT_INREVIEW");
	
		}
	}
	


	
	//@Test(enabled = false)
	@Test(priority = 6, dependsOnMethods = { "Verifying__DRAFT_INREVIEW" })
	public void Completing_ClientDocRequestAI() throws Throwable {
		try{
				fnpProcessAI(actionItemDesc_ClientDocRequest, "Submitted");
				fnpProcessAI(actionItemDesc_ClientDocRequest, "Reviewed");
				String qc;
				if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
					qc=(String) hashXlData.get("QC_Code").trim();
				
				}else{
					qc=(String) hashXlData.get("QC").trim();				
				}
			
				int ActionItemColIndex = fnpFindColumnIndex("ActionItemTable_HeaderRow", actionItemTableActionItemColName);
				int itemDescColIndex = fnpFindColumnIndex("ActionItemTable_HeaderRow", actionItemTableItemDescColName);
				int clientDocRequestRowNo = fnpFindRow("ActionItemTable_EditWO", actionItemDesc_ClientDocRequest, itemDescColIndex);
				
				
				int AItem_ItemAssignedTo_ColIndex = fnpFindColumnIndex("ActionItemTable_HeaderRow", actionItemTableAssignedToColName);
				String clientDocRequestAIAssigner = fnpFetchFromTable("ActionItemTable_EditWO", clientDocRequestRowNo, AItem_ItemAssignedTo_ColIndex);
				if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
					Assert.assertTrue(clientDocRequestAIAssigner.contains((String) hashXlData.get("QC_Code")), actionItemDesc_ClientDocRequest+" AI is not assigned to QC_Code '"+(String) hashXlData.get("QC_Code")+"'  just after Reviewed.");
				}else{
					Assert.assertEquals(clientDocRequestAIAssigner.trim(), (String) hashXlData.get("QC").trim(),  actionItemDesc_ClientDocRequest+" AI  is not assigned to QC '"+(String) hashXlData.get("QC")+"' just after Reviewed.");
				}
				fnpMymsg(actionItemDesc_ClientDocRequest+" AI  is  assigned to QC  '"+(String) hashXlData.get("QC")+"'  just after Reviewed.");

				
				
				
				fnpProcessAI(actionItemDesc_ClientDocRequest, (String) hashXlData.get("Change_Status_Completed"));

				
				int taskDescColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", SnapShot_taskTable_TaskDescColName);
				int statusColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", SnapShot_taskTable_StatusColName);
				int clientAppReviewTaskRowNo = fnpFindRow("Snapshot_TasksSummaryTable", SnapShot_Client_App_Review_Task, taskDescColIndex);
				
				String clientAppReviewTaskStatus = fnpFetchFromTable("Snapshot_TasksSummaryTable", clientAppReviewTaskRowNo, statusColIndex);
		
				String expectedclientAppReviewStatus = "COMPLETED";
		
				Assert.assertTrue(clientAppReviewTaskStatus.equalsIgnoreCase(expectedclientAppReviewStatus), "Client App Review Task Status is not getting changed to -- " + expectedclientAppReviewStatus
						+ ".");
		
				fnpMymsg("Client App Review Task Status in Snapshot tab now becomes to '" + expectedclientAppReviewStatus + "' status ");
				
				
				
				
			//	fnpProcessAI(actionItemDesc_ClientLitRequest, "Submitted");
				

		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "   Completing_ClientDocRequestAI  is failed . ", "Completing_ClientDocRequestAIFail");

		}	
		
	}
	
	
	

	
	//@Test(enabled = false)
	@Test(priority = 7, dependsOnMethods = { "Completing_ClientDocRequestAI" })
	public void Lit_Eval() throws Throwable {

		try{
			//fnpProcessAI("ClientLitRequest", "Submitted");
			//BS16.ModBrack_Lite_Eval();
			BS05.Literature_Eval();
			
		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "   Lit_Eval  is failed . ", "Lit_EvalFail");

		}	

	}
	
	

	//@Test(enabled = false)
	@Test(priority = 8, dependsOnMethods = { "Lit_Eval" })
	public void Risk_Assessment_AI_N_Task() throws Throwable {

		try{
			fnpClick_OR("TaskDetail_BackBtn");
			fnpCommonClickSnapShotTab();

			fnpWaitForVisible("ActionItemTable_EditWO");
			fnpProcessAI(actionItemDesc_RiskAssessmentUpdate, "Submitted");

			int TaskStatusColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", SnapShot_taskTable_StatusColName);
			int TaskDescColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", SnapShot_taskTable_TaskDescColName);
			int RowNoForRiskAssessment = fnpFindRow("Snapshot_TasksSummaryTable", SnapShot_taskDesc_RiskAssessment_task, TaskDescColIndex);
			String riskAssessmentTaskStatus = fnpFetchFromTable("Snapshot_TasksSummaryTable", RowNoForRiskAssessment, TaskStatusColIndex); 

			Assert.assertTrue(riskAssessmentTaskStatus.toLowerCase().contains("ready"),
					"In Task Table , Risk Assessment Task status has not become  to 'READY' . It is  showing '" + riskAssessmentTaskStatus + "'.");
			fnpMymsg(" Risk Assessment Task status has now  become  to 'READY' state  successfully.");


			int assignedToColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", SnapShot_taskTable_AssignedToColName);
			//int TaskDescColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", SnapShot_taskTable_TaskDescColName);
			//int liteEvalrowNo = fnpFindRow("Snapshot_TasksSummaryTable", SnapShot_taskDesc_LiteEval, TaskDescColIndex);
			String riskAssessmentAssigner = fnpFetchFromTable("Snapshot_TasksSummaryTable", RowNoForRiskAssessment, assignedToColIndex);
			

					
			if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){						
				Assert.assertTrue(riskAssessmentAssigner.trim().contains((String) hashXlData.get("AccountManager_Code").trim()), SnapShot_taskDesc_RiskAssessment_task+" is not assigned to Account Manager because expected is this '"+(String) hashXlData.get("AccountManager_Code").trim()+"' but actual is this '"+riskAssessmentAssigner+"'.");
			}else{
				Assert.assertEquals(riskAssessmentAssigner.trim(), (String) hashXlData.get("AccountManager").trim(), SnapShot_taskDesc_RiskAssessment_task+" is not assigned to Account Manager because expected is this '"+(String) hashXlData.get("AccountManager").trim()+"' but actual is this '"+riskAssessmentAssigner+"'.");
			}
			fnpMymsg(SnapShot_taskDesc_RiskAssessment_task+" is assigned to '" + (String) hashXlData.get("AccountManager").trim() + "'  successfully.");
			
				
			
			fnpCommonClickTaskTab();

			// ***************Risk Assessment Task***********
			fnpMymsg(" Going to click "+SnapShot_taskDesc_RiskAssessment_task+" task no. ");
			int ColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskNoColName);
			int TaskStatusColIndex_atTaskTab = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_StatusColName);
			int TaskTypeColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskTypeColName);
			int RowNoForRiskAssessment_atTaskTab = fnpFindRow("TasksTable_EditWO", taskType_RiskAssessment_Task, TaskTypeColIndex);
			String riskAssessmentTaskNo = fnpFetchFromTable("TasksTable_EditWO", RowNoForRiskAssessment_atTaskTab, ColIndex);
			Thread.sleep(500);
			
			
/*			fnpWaitTillVisiblityOfElementNClickable_NOR(".//a[text()='" + literatureEvalTaskNo + "']");
			driver.findElement(By.linkText(literatureEvalTaskNo)).click();
			*/
			
			fnpClickALinkInATable(riskAssessmentTaskNo);
			fnpCheckError("");

			fnpMymsg("Clicked on Risk Assessment task no. in Task Table i.e. '" + riskAssessmentTaskNo + "' .");
			fnpClick_OR("TaskTab_ScopeValidation_AssignTaskIcon");

			// fnpLoading_wait();

			Thread.sleep(1000);
			fnpClickInDialog_OR("TaskTab_ScopeValidation_AssignTask_SaveBtn");

			fnpCheckErrMsg("Error is thrown by application in Risk Assessment  task while Assigning the task");

			Thread.sleep(1000);
			fnpClick_OR("TaskTab_ScopeValidation_PerformTaskIcon");

			
			int copyToAudit_ForRiskAssessmentAudit;
			int copyFromAudit_ForRiskAssessmentAudit;
			String usingGoldenProcedureOrOasis = (String) hashXlData.get("Audit_usingGoldenProcedureOrOasis");
			if (usingGoldenProcedureOrOasis.equalsIgnoreCase("golden")) {
				
				int EvalInfoTable_EvalNoColIndex = fnpFindColumnIndex("LiteEval_EvaluationInfoTable_header", "Eval #");
				String EvalINfoTable_EvalNo = fnpFetchFromTable("LiteEval_EvaluationInfoTable", 1, EvalInfoTable_EvalNoColIndex);
				copyFromAudit_ForRiskAssessmentAudit=Integer.parseInt((String) hashXlData.get("copy_From_Audit_For_RiskAssessment_Task").trim());
				copyToAudit_ForRiskAssessmentAudit=Integer.parseInt(EvalINfoTable_EvalNo.trim());
				
				fnpMymsg("Just before running golden procedure - COPY_AUDIT_TRANSACTION_SUBMIT");
				//fnpCallGoldenProcedure_COPY_AUDIT_TRANSACTION_SUBMIT(copyFromAudit_ForLiteAudit,copyToAudit_ForLiteAudit,(String) hashXlData.get("Auditor").trim());
				String Auditor;
				if (  (loginAsFullName==null) ||loginAsFullName.equalsIgnoreCase("") ) {
					Auditor=(String) hashXlData.get("Auditor").trim();
				} else {
					Auditor=loginAsFullName;
				}
				fnpCallGoldenProcedure_COPY_AUDIT_TRANSACTION_SUBMIT(copyFromAudit_ForRiskAssessmentAudit,copyToAudit_ForRiskAssessmentAudit,Auditor);
				
				
				fnpMymsg("Golden procedure 'COPY_AUDIT_TRANSACTION_SUBMIT' ran successfully.");
			}else{
				//currently we are not writting the steps for Oasis.
				throw new Exception("Performing Risk Assessment task by going into Oasis is not implemented yet");
			}
				
			

			fnpClick_OR("LitEvalRefreshDataBtn");
			fnpMymsg(" clicked 'Refresh Data' button.");
			
			
			//through golden procedure  sometime refresh button does not work...
			//it is for timebeing...
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
			fnpCheckErrMsg("Error is thrown by application in Risk Assessment  task while completing the task ");


			
			
			
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
				Assert.assertTrue(countAssignFindingIcons ==expectedFindingCount, expectedFindingCount+" Findings are getting generated as visible AssignFinding icons count are '" + countAssignFindingIcons + "' but according to the test case findings should not be generated in this Dietary Mod/Brack wo");
						
					}
			
			

			int TaskSummaryTable_StausColIndex = fnpFindColumnIndex("LiteEval_TaskSummaryTable_header", "Status");
			String LitEvalTask_status = fnpFetchFromTable("LiteEval_TaskSummaryTable", 1, TaskSummaryTable_StausColIndex);

			Assert.assertTrue(LitEvalTask_status.equalsIgnoreCase("completed"), " status for the Literature Eval task should be completed but it is coming  '" + LitEvalTask_status
					+ "' ");
			fnpMymsg(" Status for the Risk Assessment task has become  to 'COMPLETED'  successfully.");
			
		

		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "   Risk_Assessment_AI_N_Task  is failed . ", "Risk_Assessment_AI_N_TaskFail");

		}	

	}
	
	//@Test(enabled = false)
	@Test(priority = 9, dependsOnMethods = { "Risk_Assessment_AI_N_Task" })
	public void Scope_Validation_Task() throws Throwable {
		try{
			fnpCommonBackToViewNBackBtnClick() ;
			fnpCommonClickTaskTab();
			fnpWaitForVisible("Task_ShowAllLink");
			fnpCheckError("");
			fnpWaitTillVisiblityOfElementNClickable_OR("Task_ShowAllLink");
			int taskStatusColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_StatusColName);
			int taskTypeColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskTypeColName);
			int scopeValidationRowNo = fnpFindRow("TasksTable_EditWO", taskType_ScopeValidation, taskTypeColIndex);
			String scopeValidationTaksStatus = fnpFetchFromTable("TasksTable_EditWO", scopeValidationRowNo, taskStatusColIndex);
			Assert.assertTrue(scopeValidationTaksStatus.toLowerCase().contains("ready"), "The Scope Validation  task should become Ready , but it has not  become Ready ");
			fnpMymsg("The Scope Validation  task has become Ready");
			
			fnpCommonClickSnapShotTab();
			int assignedToColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", SnapShot_taskTable_AssignedToColName);
			int TaskDescColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", SnapShot_taskTable_TaskDescColName);
			int scopeValidationrowNo = fnpFindRow("Snapshot_TasksSummaryTable", SnapShot_taskDesc_ScopeValidation, TaskDescColIndex);

			String scopeValidationAssigner = fnpFetchFromTable("Snapshot_TasksSummaryTable", scopeValidationrowNo, assignedToColIndex);

			if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){						
				Assert.assertTrue(scopeValidationAssigner.trim().contains((String) hashXlData.get("AccountManager_Code").trim()), SnapShot_taskDesc_ScopeValidation+" is not assigned to Account Manager because expected is this '"+(String) hashXlData.get("AccountManager_Code").trim()+"' but actual is this '"+scopeValidationAssigner+"'.");
			}else{
				Assert.assertEquals(scopeValidationAssigner.trim(), (String) hashXlData.get("AccountManager").trim(), SnapShot_taskDesc_ScopeValidation+" is not assigned to Account Manager because expected is this '"+(String) hashXlData.get("AccountManager").trim()+"' but actual is this '"+scopeValidationAssigner+"'.");
			}
			fnpMymsg(SnapShot_taskDesc_ScopeValidation+" is assigned to '" + (String) hashXlData.get("AccountManager").trim() + "'  successfully.");
			
			
			BS05.Scope_Validation_Task();
			fnpMymsg("Just After completing Scope Validation task");
			fnpRevenuePostedAmount() ;

		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "   Scope_Validation_Task  is failed . ", "Scope_Validation_TaskFail");

		}	
	}

	//@Test(enabled = false)
	@Test(priority = 10, dependsOnMethods = { "Scope_Validation_Task" })
	public void SampleSelectionTask() throws Throwable {
		
		try{
			
			BS05.VerifySampleSelectionReviewer();
			fnpProcessAI(actionItemDesc_SampleSelection, "Reviewed");
			fnpProcessAI(actionItemDesc_SampleSelection, "Completed");
			
			

			fnpCommonClickTaskTab();

			// ***************Sample Selection**********
			fnpMymsg(" Going to click Sampeltask no. ");
			int TaskNoColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskNoColName);
			int taskTypeColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskTypeColName);
			int sampleSelectionRowNo = fnpFindRow("TasksTable_EditWO", taskType_SampleSelection, taskTypeColIndex);

			String sampleSelectionTaskNo = fnpFetchFromTable("TasksTable_EditWO", sampleSelectionRowNo, TaskNoColIndex);
			Thread.sleep(500);
			fnpClickALinkInATable(sampleSelectionTaskNo);
			fnpMymsg("Clicked on Sample Selection task no. in Task Table i.e. '" + sampleSelectionTaskNo + "' .");
			fnpWaitTillVisiblityOfElementNClickable_OR("TaskTab_ScopeValidation_AssignTaskIcon");
			fnpCheckError("");
			fnpGetORObjectX("TaskTab_ScopeValidation_AssignTaskIcon").click();

			fnpClickInDialog_OR("TaskTab_ScopeValidation_AssignTask_SaveBtn");
			fnpCheckErrMsg("Error is thrown by application in Sample Selection Task  method while Assigning the task  ");						
			fnpClick_OR("TaskTab_ScopeValidation_PerformTaskIcon");
			fnpClick_OR("TaskTab_ScopeValidation_CompleteTaskIcon");

			fnpClickInDialog_OR("TaskTab_ScopeValidation_CompleteTaskSaveBtn");
			
			fnpCheckErrMsg("Error is thrown by application in Sample Selection Task   while completing the task  ");
			
			
			
			// fnpLoading_wait();
			fnpWaitForVisible("TaskTab_ScopeValidation_TaskSummaryTable");
			String sampleSelectionStatus = fnpFetchFromTable("TaskTab_ScopeValidation_TaskSummaryTable", 1, 5);

			int start_statusComplete = 0;
			while (!(sampleSelectionStatus.toLowerCase().contains("completed"))) {
				Thread.sleep(1000);
				sampleSelectionStatus = fnpFetchFromTable("TaskTab_ScopeValidation_TaskSummaryTable", 1, 5);
				start_statusComplete++;

				if (start_statusComplete > Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					break;
				}

			}

			Assert.assertTrue(sampleSelectionStatus.toLowerCase().contains("completed"),
					"After completed the Sample Selection Task after assign,perform,complete , status has not been changed  to 'COMPLETED' . It is still showing '"
							+ sampleSelectionStatus + "'.");

			fnpMymsg(" After completed the Sample Selection Taskafter assign,perform,complete , status has  been changed  to 'COMPLETED' successfully.");

			fnpClick_OR("TaskTab_ScopeValidation_BackToViewBtn");

			fnpClick_OR("TaskTab_ScopeValidation_BackBtn");

			fnpWaitForVisible("Task_ShowAllLink");
			fnpWaitTillVisiblityOfElementNClickable_OR("Task_ShowAllLink");

			fnpWaitForElementVisibility("TasksTable_EditWO", CONFIG.getProperty("genMax_waitTime"));
			int StatusColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_StatusColName);
			taskTypeColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskTypeColName);
			sampleSelectionRowNo = fnpFindRow("TasksTable_EditWO", taskType_ScopeValidation, taskTypeColIndex);
			String sampleSelectionStatus_TaskTable = fnpFetchFromTable("TasksTable_EditWO", sampleSelectionRowNo, StatusColIndex);

			Assert.assertTrue(sampleSelectionStatus_TaskTable.toLowerCase().contains("completed"),
					"In Task Table , status has not been changed  to 'COMPLETED' . It is still showing '" + sampleSelectionStatus_TaskTable + "'.");

			fnpMymsg(" After completed the Sample Selection Task , status in Task Table has  been changed  to 'COMPLETED' successfully.");
			
			fnpCheckErrMsg("Error is thrown by application in Sample Selection Task  method  ");
			
			fnpMymsg("Just After completing Sample Selection task");
			fnpRevenuePostedAmount() ;
			
		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "   SampleSelectionTask  is failed . ", "SampleSelectionTaskFail");

		}	

	}
	
	
	
	//@Test(priority = 1)
	@Test(priority = 11, dependsOnMethods = { "SampleSelectionTask" })
	public void Certification_Decision_Task() throws Throwable {
		try{		
/*			
			workOrderNo="W0554546";
			fnpCommonLoginPart(classNameText);
			fnpSearchWorkOrderOnly(workOrderNo);
			fnpCommonClickTaskTab();
			Thread.sleep(2000);*/
			
			BS05.Certification_Decision_Task();
		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "   Certification_Decision_Task  is failed . ", "Certification_Decision_TaskFail");

		}

	}
	
	
	
	
	@Test(priority = 12, dependsOnMethods = { "Certification_Decision_Task" })
	public void Verify_INPROCESS_to_COMPLETE_Automatically() throws Throwable {	
		try{
			BS05.Verify_INPROCESS_to_COMPLETE_Automatically();
		} catch (Throwable t) {
			fnpCommonFinalCatchBlock(t, "   Verify_INPROCESS_to_COMPLETE_Automatically  is failed . ", "Verify_INPROCESS_to_COMPLETE_AutomaticallyFail");

		}

	}
	
	
	
	@Test(priority = 13, dependsOnMethods = { "Verify_INPROCESS_to_COMPLETE_Automatically" })
	public void Verify_InvoiceInFinancialTab() throws Throwable {

		fnpMymsg("Just After wo status changed to COMPLETED.");
		fnpCommonClickTaskTab();
		fnpRevenuePostedAmount() ;
		
		fnpMymsg(" **************************************************************");
		fnpMymsg(" Executing Verify_InvoiceInFinancialTab");

		try {

			fnpWaitTillClickable("FinancialTab_EditWO");
			fnpMymsg("Now we are going into Financials Tab for verifying/comparing respective amounts");

			fnpCommonClickFinancialTab();

			fnpWaitForVisible("FinancialTab_InvoiceCategoryLink");
			
			String tagName=fnpGetORObjectX("FinancialTab_InvoiceCategoryLink").getTagName();
			
			if (tagName.equalsIgnoreCase("a")) {
				msg="Invoice category has a hyper link as its html tagname is '"+tagName+"' and it means Invoice Category section is not opened bydefault as after opening the financial tab, its (Invoice Category) html tag is changed from 'a' to 'div'.";
				fnpMymsg(msg);
				throw new Exception(msg);
				
			} else {
				fnpMymsg("Invoice Category section is opened bydefault.");

			}
			
			int CountTotalRows = fnpCountRowsInTable("PendingInvoiceRequest_AtFinancialTabTable");
			if (CountTotalRows==1) {
				fnpMymsg("Total rows in Pending Invoice Request table are --"+CountTotalRows);
			} else {
				if (CountTotalRows>1) {
					String msg = "Total rows in Pending Invoice Request table are --'"+CountTotalRows+"' but it should be only 1.";
					fnpMymsg(msg);
					throw new Exception(msg);
				} else {
					String msg = "Pending invoice Request should be present but here in table they are not present because currently total rows in Pending Invoice Request table are --"+CountTotalRows ;
					fnpMymsg(msg);
					throw new Exception(msg);
				}

			}
			
			int pendingInvoiceRequestAmountColIndex = fnpFindColumnIndex("PendingInvoiceRequest_AtFinancialTabTable_header", "Amount");
			String pendingInvoiceRequestAmount = fnpFetchFromTable("PendingInvoiceRequest_AtFinancialTabTable", 1, pendingInvoiceRequestAmountColIndex);
			String InvoicedAmt = fnpGetORObjectX("FinancialInfo_InvoiceCategoryTable_InvoicedAmount").getText();

			fnpMymsg("Pending Invoice Request Amount in Invoice Category is :  " + pendingInvoiceRequestAmount);
			fnpMymsg("Financial Info's Invoiced Amount in Invoice Category is :  " + InvoicedAmt);

			/**********Here we are matching exact Strings (amount), not converting first into double and then match because we want actual format also same otherwise matching using double , exact format can not be catched. ****/
			Assert.assertEquals(pendingInvoiceRequestAmount, InvoicedAmt, "Pending Invoice Request Amount and Financial Info's Invoiced Amount should have been equal in Invoice Category table but here they are not equal because "
					+ " pending invoice request amount is '"+pendingInvoiceRequestAmount+"' and Financial Info's invoice amount is '"+InvoicedAmt+"'.");
			fnpMymsg("Hence ,Pending Invoice Request Amount and Financial Info's Invoiced Amount are  equal in Invoice Category table.");



	

		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "   Verify_InvoiceInFinancialTab  is failed . ", "Verify_InvoiceInFinancialTab_Failed");

		}

	}
	
	@Test(priority = 14, dependsOnMethods = { "Verify_InvoiceInFinancialTab" })
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