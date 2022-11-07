package nsf.ecap.Wales_Work_Order_suite;

import static nsf.ecap.config.IPULSE_CONSTANTS.SnapShot_RevisionExecutionQuote;
import static nsf.ecap.config.IPULSE_CONSTANTS.SnapShot_taskDesc_CertDeci;
import static nsf.ecap.config.IPULSE_CONSTANTS.SnapShot_taskDesc_ExternalReview_Task;
import static nsf.ecap.config.IPULSE_CONSTANTS.SnapShot_taskDesc_TechnicalReview_Task;
import static nsf.ecap.config.IPULSE_CONSTANTS.SnapShot_taskTable_AssignedToColName;
import static nsf.ecap.config.IPULSE_CONSTANTS.SnapShot_taskTable_ResultColName;
import static nsf.ecap.config.IPULSE_CONSTANTS.SnapShot_taskTable_StatusColName;
import static nsf.ecap.config.IPULSE_CONSTANTS.SnapShot_taskTable_TaskColName;
import static nsf.ecap.config.IPULSE_CONSTANTS.SnapShot_taskTable_TaskDescColName;
import static nsf.ecap.config.IPULSE_CONSTANTS.actionItemDesc_Additional_Quote;
import static nsf.ecap.config.IPULSE_CONSTANTS.actionItemDesc_AssessPnlReview;
import static nsf.ecap.config.IPULSE_CONSTANTS.actionItemDesc_Certdecisioneducation;
import static nsf.ecap.config.IPULSE_CONSTANTS.actionItemDesc_ClientDocRequest;
import static nsf.ecap.config.IPULSE_CONSTANTS.actionItemDesc_ProdAssessReview;
import static nsf.ecap.config.IPULSE_CONSTANTS.actionItemTableAssignedToColName;
import static nsf.ecap.config.IPULSE_CONSTANTS.actionItemTableItemDescColName;
import static nsf.ecap.config.IPULSE_CONSTANTS.taskTable_TaskNoColName;
import static nsf.ecap.config.IPULSE_CONSTANTS.taskTable_TaskTypeColName;
import static nsf.ecap.config.IPULSE_CONSTANTS.taskType_ExternalReview_Task;

import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import nsf.ecap.ISR_suite.TestSuiteBase_ISR_suite;
import nsf.ecap.Non_Food_Compounds_suite.NFC_ExistingNew_Draft_Complete;
import nsf.ecap.Work_Order_suite.Modbrack_Not_Certified;
import nsf.ecap.Work_Order_suite.NewNew_Draft_InReview_No_Fac;
import nsf.ecap.Work_Order_suite.NewNew_InProc_Completed_No_Fac;
import nsf.ecap.Work_Order_suite.WO_Failure_Resolution;
import nsf.ecap.util.TestDataProvider;
import nsf.ecap.util.TestUtil;


public class Resolution_WRAS_Approved extends TestSuiteBase_Wales_Work_Order_suite {
	
	
	
	@BeforeTest
	@Parameters({ "className" })
	public void checkTestSkip(String className) throws Throwable {
		
		if (className.isEmpty()) {
			className = this.getClass().getSimpleName();

		}
		BS01 = new NewNew_Draft_InReview_No_Fac();
		BS05 = new NewNew_InProc_Completed_No_Fac();
		BS16= new Modbrack_Not_Certified();
		BS12=new WO_Failure_Resolution() ;
		nfcWo=new NFC_ExistingNew_Draft_Complete();
		
		BS01.checkTestSkip(className);
		
		runmodes = TestUtil.getDataSetRunmodes(currentSuiteXls, classNameText);

	}
	
	
	@Test(dataProviderClass = TestDataProvider.class,dataProvider = "Resolution_WRAS_Approved")
	public void Wales_Approved_flow(Hashtable<String, String> table) throws Throwable {

		fnpMymsg("****************************************************************");
		count++;
		if (!runmodes[count].equalsIgnoreCase("Y")) {
			skip = true;
			fnpMymsg("Runmode for test set data set to 'N' " + (count + 1));
			fnpMymsg("*****************");
			fnpMymsg("");
			throw new SkipException("Runmode for test set data set to 'N' " + (count + 1));
		}else{
			skip = false;
		}

		try {
			
			start = new Date();
			hashXlData=new HashMap(table); 
			
			if (!IsBrowserPresentAlready) {
				IsBrowserPresentAlready = fnpLaunchBrowserAndLogin();
				fnpMymsg(" Browser is launched");
			}

			
			if (!(loginAs.equalsIgnoreCase(table.get("User")))) {
				fnpSwitchUser(table.get("User"));
			}
					
			fnpCommonClickTopMainMenu("Menu", "FirstRefrenceLinkJustBelowToMenu", "CreateWorkOrderLink", "ClientLKPBtn");
			
			// -------First Create Work Order Page ----------------------------
			fnpCreateWO() ;			
			BS01.AddingData__Facility_Tab();
			/***************Adding data in Tasks tab****************/
			BS01.AddingData__Tasks_Tab();
			
			/***************Add discount and RUSH fees****************/
			fnpCommonClickFinancialTab();

			String feeType;
			String amount;
			String comments;
			String eachDiscount[];
			
			String discountCompleteString=(String) hashXlData.get("Fee_Discount").trim();
			String discountSets[] = discountCompleteString.split("::");
			for (int i = 0; i < discountSets.length; i++) {
				
				fnpClick_OR("WO_FinancialsTab_AddDiscountRush_Btn");

				fnpMymsg("Each Discount Set and its value are--" + discountSets[i]);
				eachDiscount = discountSets[i].split(",");
				feeType = fnpremoveFormatting(eachDiscount[0]);
				amount = fnpremoveFormatting(eachDiscount[1]);
				comments = fnpremoveFormatting(eachDiscount[2]);
				
				fnpPFList("AddDiscountRushFee_Popup_Fee_Type_List", "AddDiscountRushFee_Popup_Fee_TypeOptions", feeType);
				fnpLoading_wait_specialCase(7);//either feetype or amount is dependent drop down and because of this comments gets cleared sometime
				fnpType("OR", "AddDiscountRushFee_Popup_Amount_txtBx", amount);
				fnpLoading_wait_specialCase(7);
				fnpType("OR", "AddDiscountRushFee_Popup_Comments_txtBx", comments);
				fnpClick_OR("AddDiscountRushFee_Popup_Save_btn");

			}
			
			fnpCommonClickInfoTab();
			String certDecider;
			String techReviewer;
			String qc;
			String externalReviewer;
			String accountManager;
			
			if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
				certDecider= (String) hashXlData.get("Cert_Decider_Code") ;
				techReviewer= (String) hashXlData.get("Tech_Reviewer_Code") ;
				qc= (String) hashXlData.get("QC_Code")  ;
				externalReviewer  =  (String) hashXlData.get("ExternalReviewer_Code");
				accountManager  =  (String) hashXlData.get("AccountManager_Code");
				
			}else{
				certDecider= (String) hashXlData.get("Cert_Decider") ;
				techReviewer=  (String) hashXlData.get("Tech_Reviewer");
				qc=  (String) hashXlData.get("QC") ;
				externalReviewer  =  (String) hashXlData.get("ExternalReviewer_fullName");
				accountManager  =  (String) hashXlData.get("AccountManager");
			}
				
			
			fnpPFList("InfoTab_CertDeciderPFList", "InfoTab_CertDeciderPFListOptions", certDecider);
			
			fnpClick_OR("InfoTab_TechReviLKPBtn");
			if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){				
				fnpSearchNSelectFirstRadioBtn(1, techReviewer, 1);
			}else{
				fnpSearchNSelectFirstRadioBtn(2, techReviewer, 1);
			}

			fnpClick_OR("InfoTab_CTDeciMakerLKPBtn");
			//fnpSearchNSelectFirstRadioBtn(2, qc, 1);
			if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){				
				fnpSearchNSelectFirstRadioBtn(1, qc, 1);
			}else{
				fnpSearchNSelectFirstRadioBtn(2, qc, 1);
			}
			
			fnpPFList("WO_InfoTab_ExternalReviewer_List", "WO_InfoTab_ExternalReviewer_Options", externalReviewer);
            Thread.sleep(2000);
            
			fnsDD_Value_select_by_DDLabelName_and_Filter("Standard Version", (String) hashXlData.get("Standard_Version"));
			
			String status = "InReview";
			fnpPFList("InfoTab_WOStatusPFList", "InfoTab_WOStatusPFListOptions", status);
			fnpClick_OR("ProAddDocSaveBtn");
		
			fnpCommonClickSnapShotTab();

			fnpVerifyAIs() ;

			// Verify ClientDocRequest AI assigned to the technical reviewer
			//fnpVerifyTask_isAssignedToThisPerson_atSnapshotTab(actionItemDesc_ClientDocRequest,(String) hashXlData.get("AccountManager"),(String) hashXlData.get("AccountManager_Code"), "Account Manager");	

			fnpVerifyClientAppReviewTaskInCompletedAndOtherInCreatedStatus_TasksTab();
			
			// On snapshot tab submit ClientDocRequest Action item
			fnpProcessAI(actionItemDesc_ClientDocRequest, "Submitted");
			
			int AItem_ItemAssignedTo_ColIndex = fnpFindColumnIndex("ActionItemTable_HeaderRow", actionItemTableAssignedToColName);
			int AItem_ItemDesc_ColIndex = fnpFindColumnIndex("ActionItemTable_HeaderRow", actionItemTableItemDescColName);
			
			int clientDocRequestAiRowNo = fnpFindRow("ActionItemTable_EditWO", actionItemDesc_ClientDocRequest, AItem_ItemDesc_ColIndex);
			String clientDocRequestAssigner = fnpFetchFromTable("ActionItemTable_EditWO", clientDocRequestAiRowNo, AItem_ItemAssignedTo_ColIndex);
			
			if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
				Assert.assertTrue(clientDocRequestAssigner.trim().contains((String) hashXlData.get("Tech_Reviewer_Code").trim()), actionItemDesc_ClientDocRequest +" is not assigned to Tech Reviewer because expected is this '"+(String) hashXlData.get("Tech_Reviewer_Code")+"' but actual is this '"+clientDocRequestAssigner+"'.");
			}else{
				Assert.assertEquals(clientDocRequestAssigner.trim(), (String) hashXlData.get("Tech_Reviewer").trim(), actionItemDesc_ClientDocRequest +"  is not assigned to Tech Reviewer because expected is this '"+(String) hashXlData.get("Tech_Reviewer")+"' but actual is this '"+clientDocRequestAssigner+"'.");
			}	
			fnpMymsg(actionItemDesc_ClientDocRequest +"  AI is  assigned to  Technical Reviewer  successfully i.e. '" +techReviewer + "'. ");

			
			String expectedTechnicalReviewStatus = "READY";
			
			int taskStatusColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", SnapShot_taskTable_StatusColName);
			int taskTypeTechnicalReviewColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", SnapShot_taskTable_TaskDescColName);
			int TechnicalReviewRowNo = fnpFindRow("Snapshot_TasksSummaryTable", SnapShot_taskDesc_TechnicalReview_Task, taskTypeTechnicalReviewColIndex);
			
			String actualTechnicalReviewStatus = fnpFetchFromTable("Snapshot_TasksSummaryTable", TechnicalReviewRowNo, taskStatusColIndex);
			Assert.assertTrue(actualTechnicalReviewStatus.equalsIgnoreCase(expectedTechnicalReviewStatus), "Technical Review Status is not [" + expectedTechnicalReviewStatus+ "] .");
			fnpMymsg(" Technical Review Status 's Task Status in Task tab now becomes in [" + expectedTechnicalReviewStatus+ "] status ");
			
			fnpVerifyTask_isAssignedToThisPerson_atSnapshotTab(SnapShot_taskDesc_TechnicalReview_Task,(String) hashXlData.get("Tech_Reviewer"),(String) hashXlData.get("Tech_Reviewer_Code"), "Technical Reviewer");	
	
			// action item change status as Reviewed BC_09
			
			fnpProcessAI(actionItemDesc_ClientDocRequest, "Reviewed");
			String clientDocRequestAssignerAfterReviewed = fnpFetchFromTable("ActionItemTable_EditWO", clientDocRequestAiRowNo, AItem_ItemAssignedTo_ColIndex);
			
			if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
				Assert.assertTrue(clientDocRequestAssignerAfterReviewed.trim().contains((String) hashXlData.get("QC_Code").trim()), actionItemDesc_ClientDocRequest +" is not assigned to QC because expected is this '"+(String) hashXlData.get("QC_Code")+"' but actual is this '"+clientDocRequestAssigner+"'.");
			}else{
				Assert.assertEquals(clientDocRequestAssignerAfterReviewed.trim(), (String) hashXlData.get("QC").trim(), actionItemDesc_ClientDocRequest +"  is not assigned to QC because expected is this '"+(String) hashXlData.get("QC")+"' but actual is this '"+clientDocRequestAssigner+"'.");
			}	
			fnpMymsg(actionItemDesc_ClientDocRequest +"  AI is  assigned to  QC  successfully i.e. '" +techReviewer + "'. ");
	
			fnpVerifyTask_isAssignedToThisPerson_atSnapshotTab(SnapShot_taskDesc_TechnicalReview_Task,(String) hashXlData.get("QC"),(String) hashXlData.get("QC_Code"), "QC");
			fnpProcessAI(actionItemDesc_ClientDocRequest, "Completed");
			
			
			// ***************Technical_Review_Task***********

						fnpMymsg(" Going to click Technical_Review_Task  no. ");
						//fnpWaitForVisible("Task_ShowAllLink");
						//fnpWaitTillVisiblityOfElementNClickable_OR("Task_ShowAllLink");
						int TaskNoColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", SnapShot_taskTable_TaskColName);  //1
						int taskTypeColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", SnapShot_taskTable_TaskDescColName); //7
						
						fnpFindRow("Snapshot_TasksSummaryTable", SnapShot_taskDesc_TechnicalReview_Task, taskTypeColIndex);
						
						int technicalReviewRowNo = fnpFindRow("Snapshot_TasksSummaryTable", SnapShot_taskDesc_TechnicalReview_Task, taskTypeColIndex);

						String technicalReviewTaskNo = fnpFetchFromTable("Snapshot_TasksSummaryTable", technicalReviewRowNo, TaskNoColIndex);
						Thread.sleep(500);
						
						fnpClickALinkInATable(technicalReviewTaskNo);
						
						fnpMymsg("Clicked on Technical_Review_Task no. in Task Table i.e. '" + technicalReviewTaskNo + "' .");
						fnpWaitTillVisiblityOfElementNClickable_OR("TaskTab_ScopeValidation_AssignTaskIcon");

						fnpClick_OR("TaskTab_ScopeValidation_AssignTaskIcon");
						fnpClickInDialog_OR("TaskTab_ScopeValidation_AssignTask_SaveBtn");
						fnpCheckError(" while Assigning the task in Technical_Review_Task  method ");
						fnpClick_OR("TaskTab_ScopeValidation_PerformTaskIcon");
						
						fnpAnnualUpdateRequiredChkbxesUsingInReviewTask();
						fnpClick_OR("TaskTab_ScopeValidation_CompleteTaskIcon");
						fnpClickInDialog_OR("TaskTab_ScopeValidation_CompleteTaskSaveBtn");
						fnpCheckError("  in Technical_Review_Task  method while completing the task ");

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
								"After completed the Technical Review after assign,perform,complete , status has not been changed  to 'COMPLETED' . It is still showing '"
										+ scopeValidationStatus + "'.");
						fnpMymsg(" After completed the Technical Review after assign,perform,complete , status has  been changed  to 'COMPLETED' successfully.");
						fnpCommonBackToViewNBackBtnClick();
						fnpWaitForElementVisibility("Snapshot_TasksSummary_HeaderRow", CONFIG.getProperty("genMax_waitTime"));
						
						int StatusColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", SnapShot_taskTable_StatusColName);
						taskTypeColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", SnapShot_taskTable_TaskDescColName);
						technicalReviewRowNo = fnpFindRow("Snapshot_TasksSummaryTable", SnapShot_taskDesc_TechnicalReview_Task, taskTypeColIndex);
						
						String technicalReviewStatus_TaskTable = fnpFetchFromTable("Snapshot_TasksSummaryTable", technicalReviewRowNo, StatusColIndex);

						Assert.assertTrue(technicalReviewStatus_TaskTable.toLowerCase().contains("completed"),
								"In Task Table , status has not been changed  to 'COMPLETED' . It is still showing '" + technicalReviewStatus_TaskTable + "'.");
						fnpMymsg(" After completed the Technical_Review_Task , status in Task Table has  been changed  to 'COMPLETED' successfully.");

						taskTypeColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", SnapShot_taskTable_TaskDescColName);
						
						
						int taskRevisionExecutionQuoteRowNo = fnpFindRow("Snapshot_TasksSummaryTable", SnapShot_RevisionExecutionQuote, taskTypeColIndex);

						String taskStatus = fnpFetchFromTable("Snapshot_TasksSummaryTable", taskRevisionExecutionQuoteRowNo, StatusColIndex);
						Assert.assertTrue(taskStatus.equalsIgnoreCase("READY"), "Task 'Revision Execution Quote' status  is  not become 'READY' status.");

						fnpMymsg("Task 'Revision Execution Quote' status  is  now  become in  'READY' status.");

						fnpCheckError("  in  Technical_Review_Task method ");
						
						
						
						int taskAssignedTo_ColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", SnapShot_taskTable_AssignedToColName);
						int taskDesc_ColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", SnapShot_taskTable_TaskDescColName);
						
						
						int RevisionExecutionQuoteAiRowNo = fnpFindRow("Snapshot_TasksSummaryTable", SnapShot_RevisionExecutionQuote, taskDesc_ColIndex);
						String RevisionExecutionQuoteAssigner = fnpFetchFromTable("Snapshot_TasksSummaryTable", RevisionExecutionQuoteAiRowNo, taskAssignedTo_ColIndex);
						
						if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
							Assert.assertTrue(RevisionExecutionQuoteAssigner.trim().contains((String) hashXlData.get("AccountManager_Code").trim()), SnapShot_RevisionExecutionQuote +" is not assigned to Account Manager because expected is this '"+(String) hashXlData.get("AccountManager_Code")+"' but actual is this '"+clientDocRequestAssigner+"'.");
						}else{
							Assert.assertEquals(RevisionExecutionQuoteAssigner.trim(), (String) hashXlData.get("AccountManager").trim(), SnapShot_RevisionExecutionQuote +"  is not assigned to Account Manager because expected is this '"+(String) hashXlData.get("AccountManager")+"' but actual is this '"+clientDocRequestAssigner+"'.");
						}	
						fnpMymsg(SnapShot_RevisionExecutionQuote +"  AI is  assigned to  Account Manager  successfully i.e. '" + accountManager + "'. ");
						
						
			
						// ***************Revision Quote_Task***********
						
						fnpCommonClickTaskTab();
						
						BS12.FR_Revision_Execution_Quote();
						
						fnpClick_OR("GenerateQuoteBtn_AtRevisionQuoteTask");
						fnpClick_OR("SaveBtnInPopupAfterClickingGenerateQuoteBtn");
						
						fnpClick_OR("OKContinueButtonForAdditionalQuoteAI");

						fnpCommonBackToViewNBackBtnClick();
						
						fnpCommonClickSnapShotTab();
						
						fnpVerifyActionItemGeneratedOrNot(actionItemDesc_Additional_Quote);
						fnpVerifyAI_isAssignedToThisPerson(actionItemDesc_Additional_Quote, ((String) hashXlData.get("Client_Name")).trim(), ((String) hashXlData.get("Client")).trim(), "Client");

						
						fnpProcessAI(actionItemDesc_Additional_Quote, "Completed");

						int taskDescColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", SnapShot_taskTable_TaskDescColName);  //3
						int statusColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", SnapShot_taskTable_StatusColName);   //5

						int revExecQuoteTaskRowNo = fnpFindRow("Snapshot_TasksSummaryTable", SnapShot_RevisionExecutionQuote, taskDescColIndex);

						String revExecQuoteTaskTaskStatus = fnpFetchFromTable("Snapshot_TasksSummaryTable", revExecQuoteTaskRowNo, statusColIndex);
						String expectedrevExecQuoteTaskStatus = "Completed";
						
						Assert.assertTrue(revExecQuoteTaskTaskStatus.equalsIgnoreCase(expectedrevExecQuoteTaskStatus), SnapShot_RevisionExecutionQuote + " Task Status is not ["+ expectedrevExecQuoteTaskStatus + "] .");
						
						fnpMymsg(SnapShot_RevisionExecutionQuote + "   Status in Snapshot tab now becomes in '" + expectedrevExecQuoteTaskStatus + "' status ");
						
						fnpVerifyTask_Result_at_SnapshotTab(SnapShot_RevisionExecutionQuote,"WIN");
						
						
						int certDecisionTaskRowNo = fnpFindRow("Snapshot_TasksSummaryTable", SnapShot_taskDesc_CertDeci, taskDescColIndex);
						String actualCertDecisionTaskTaskStatus = fnpFetchFromTable("Snapshot_TasksSummaryTable", certDecisionTaskRowNo, statusColIndex);

						String expectedCertDecisionTaskStatus = "READY";
						
						Assert.assertTrue(actualCertDecisionTaskTaskStatus.equalsIgnoreCase(expectedCertDecisionTaskStatus), SnapShot_taskDesc_CertDeci + " Task Status is not [" + expectedCertDecisionTaskStatus + "] .");
						fnpMymsg(SnapShot_taskDesc_CertDeci + "   Status in Snapshot tab now becomes in '" + expectedCertDecisionTaskStatus + "' status ");
						
						fnpClick_OR_WithoutWait("NewlyCrWOTopBannerInfo");
						
						fnpWaitForVisible("TopBannerWOStatus");
						String WOStatus = fnpGetORObjectX("TopBannerWOStatus").getText();
			
						Assert.assertEquals(WOStatus, "INPROCESS", "Now WO status has NOT been changed to 'INPROCESS'.The WO should be  in INPROCESS status. ");
						fnpMymsg(" After completed the ClientQuoteReview AI , Now Work Order status  changed  to 'INPROCESS' successfully.");
								
						fnpCommonCodeCertDecisionWaitTillNotReady_till_inperform();
						
						driver.close();
						fnpLaunchBrowserAndLogin();					
						fnpSearchWorkOrderOnly(workOrderNo);
						
						fnpCommonClickTaskTab();
						nfcWo.fnpRevenuePostedAmount_equalsTo_AdjustedAmount_forSpecificTask(((String) hashXlData.get("TaskNames_Check_AdjustedAmt_equalsTo_RevenuePostedAmt_after_CertDecisionTask_completed")).trim());

						fnpCommonClickSnapShotTab();
						fnpProcessAI(actionItemDesc_AssessPnlReview, ((String) hashXlData.get("AssessPnlReview_changeStatus_1")).trim());
						fnpProcessAI(actionItemDesc_AssessPnlReview, ((String) hashXlData.get("AssessPnlReview_changeStatus_2")).trim());
						
						fnpMymsg(" Going to verify '"+actionItemDesc_ProdAssessReview+"' action item should be generated in Pending status and it is assigned to Account Manager");	
						fnpVerifyActionItemGeneratedOrNot (actionItemDesc_ProdAssessReview);			
						fnpVerifyAI_isAssignedToThisPerson(actionItemDesc_ProdAssessReview,(String) hashXlData.get("AccountManager"), (String) hashXlData.get("AccountManager_Code"), "Account Manager");
						
						fnpProcessAI(actionItemDesc_ProdAssessReview, ((String) hashXlData.get("ProdAssessReview_AI_changeStatus_1")).trim());
						fnpProcessAI(actionItemDesc_ProdAssessReview, ((String) hashXlData.get("ProdAssessReview_AI_changeStatus_2")).trim());
						
						fnpCommonClickTaskTab();
						
						// ***************Complete External Review Task ***********
						fnpMymsg(" Going to click External Review Task  no. ");
						 int ColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskNoColName);
						 int ExtaskTypeColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskTypeColName);
						 int taskRowNo = fnpFindRow("TasksTable_EditWO", taskType_ExternalReview_Task, ExtaskTypeColIndex);
						 String taskNo = fnpFetchFromTable("TasksTable_EditWO", taskRowNo, ColIndex);
						Thread.sleep(500);
		
						fnpClickALinkInATable(taskNo);
						fnpCheckError("");
						if (fnpCheckElementPresenceImmediately("EditWOBtnTaskInside")) {
							fnpClick_OR("EditWOBtnTaskInside");
		
						}
						fnpClick_OR("TaskTab_CertDeci_AssignTaskIcon");
						fnpClickInDialog_OR("TaskTab_ScopeValidation_AssignTask_SaveBtn");
						fnpCheckErrMsg("Error is thrown by application while Assigning the '"+taskType_ExternalReview_Task+"' task. ");
		
						fnpClick_OR("TaskTab_CertDeci_PerformTaskIcon");
						fnpCheckErrMsg("Error is thrown by application after clicking PerformTaskIcon");
						
						fnpPFList("ExtReviewTask_DecisionPFList", "ExtReviewTask_DecisionPFListOptions", (String) hashXlData.get("ExternalReview_Decision"));
						fnpClick_OR("ExtReviewTask_TaskDetails_SaveBtn");
						
						
						fnpCheckErrMsg("Error is thrown by application in External Review Task while completing the task");
						
						if (fnpCheckElementPresenceImmediately("TaskTab_ScopeValidation_BackToViewBtn")) {
							fnpClick_OR("TaskTab_ScopeValidation_BackToViewBtn");
							fnpMymsg("BackToViewBtn is present, so clicked it.");
						}
						
						if (fnpCheckElementPresenceImmediately("TaskTab_ScopeValidation_BackBtn")) {
							fnpClick_OR("TaskTab_ScopeValidation_BackBtn");
							fnpMymsg("BackBtn is present, so clicked it.");
						}
						
						System.out.println("Test");
						fnpCommonClickSnapShotTab();
						fnpMymsg(" Going to verify on Snapshot tab'"+ SnapShot_taskDesc_ExternalReview_Task +"' The external review task should be set to 'COMPLETE' and the result is 'APPROVED'");	
						
						fnpVerifyTask_ColValue_at_SnapshotTab( SnapShot_taskTable_StatusColName,SnapShot_taskTable_TaskDescColName,SnapShot_taskDesc_ExternalReview_Task,"COMPLETED");
						fnpVerifyTask_ColValue_at_SnapshotTab( SnapShot_taskTable_ResultColName,SnapShot_taskTable_TaskDescColName,SnapShot_taskDesc_ExternalReview_Task,(String) hashXlData.get("ExpectedEternalReviewTask_Result"));
						
						
						fnpCommonClickTaskTab();
						nfcWo.fnpRevenuePostedAmount_equalsTo_AdjustedAmount_forSpecificTask(((String) hashXlData.get("TaskNames_Check_AdjustedAmt_equalsTo_RevenuePostedAmt_after_ExternalReview_completed")).trim());					
					

					    fnpCommonClickSnapShotTab();
					    fnpProcessAI(actionItemDesc_Certdecisioneducation,"Completed");
						
						
					    TestSuiteBase_ISR_suite tISR = new TestSuiteBase_ISR_suite();
						tISR.fnpRunJob("UpdateApplicationCompletedQJobScheduler");
					
						fnpMymsg(" Going to verify '"+ workOrderNo +"' Work order in Completed status.");
						
						fnpVerifyWoStatus("COMPLETE");
						
	
			
		} catch (Throwable t) {
			
			fnpCommonFinalCatchBlock(t, " Resolution_Wales_wo_flow"+"--"+((String) hashXlData.get("WO_Sub_Type")).trim()+"  is fail . ", ((String) hashXlData.get("WO_Sub_Type")).trim()+"--"+"Wales_wo_flowFail");
			IsBrowserPresentAlready = false; // This is for special reason if one case is fail due to application error or any other reason then it close the
			// browser and login again.
			//driver.quit();

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
			//fnpElapsedTime(currentSuiteName, this.getClass().getSimpleName(), currentScriptCode, "PASS");
		} else {
			TestUtil.reportDataSetResult(currentSuiteXls, "Test Cases", TestUtil.getRowNum(currentSuiteXls, this.getClass().getSimpleName()), "FAIL");
			//fnpElapsedTime(currentSuiteName, this.getClass().getSimpleName(), currentScriptCode, "FAIL");
		}
		fnpMymsg("=========================================================================================================================================");

		hashXlData.clear();

		fnpCommonCloseBrowsersAndKillProcess();

	}
	




	// @AfterSuite
	@AfterTest
	public void closebrowser() throws InterruptedException {
		//driver.quit();
	//	IsBrowserPresentAlready = false;
		setIsBrowserPresentAlready_false();
		killprocess();

	}

}
