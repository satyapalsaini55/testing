package nsf.ecap.Wales_Work_Order_suite;


import static nsf.ecap.config.IPULSE_CONSTANTS.*;

/*import static nsf.ecap.config.IPULSE_CONSTANTS.Dietary_Supplement_suite_Name;
import static nsf.ecap.config.IPULSE_CONSTANTS.Non_Food_Compounds_suite_Name;
import static nsf.ecap.config.IPULSE_CONSTANTS.Wales_Work_Order_suite_Name;
import static nsf.ecap.config.IPULSE_CONSTANTS.actionItemDesc_DocumentFinal;
import static nsf.ecap.config.IPULSE_CONSTANTS.actionItemTableAssignedToColName;
import static nsf.ecap.config.IPULSE_CONSTANTS.actionItemTableItemDescColName;*/

import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import nsf.ecap.Health_Science_suite.TestSuiteBase_HealthScience;
import nsf.ecap.ISR_suite.TestSuiteBase_ISR_suite;
import nsf.ecap.Non_Food_Compounds_suite.NFC_ExistingNew_Draft_Complete;
import nsf.ecap.Work_Order_suite.Modbrack_Not_Certified;
import nsf.ecap.Work_Order_suite.NewNew_Draft_InReview_No_Fac;
import nsf.ecap.Work_Order_suite.NewNew_InProc_Completed_No_Fac;
import nsf.ecap.Work_Order_suite.TestSuiteBase;
import nsf.ecap.Work_Order_suite.WO_Failure_Resolution;
import nsf.ecap.config.IPULSE_CONSTANTS;
import nsf.ecap.util.ErrorUtil;
import nsf.ecap.util.TestDataProvider;
import nsf.ecap.util.TestUtil;

//import org.ini4j.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.os.WindowsUtils;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;

public class ModBrack_WRAS_Approved extends TestSuiteBase_Wales_Work_Order_suite {

	
	@BeforeTest
	@Parameters({ "className" })
	public void checkTestSkip(String className) throws Throwable {
		
		if (className.isEmpty()) {
			className = this.getClass().getSimpleName();

		}
		BS01 = new NewNew_Draft_InReview_No_Fac();
		BS05 = new NewNew_InProc_Completed_No_Fac();
		BS16= new Modbrack_Not_Certified();
		//BS12=new WO_Failure_Resolution() ;
		nfcWo=new NFC_ExistingNew_Draft_Complete();
		
		BS01.checkTestSkip(className);
		
		runmodes = TestUtil.getDataSetRunmodes(currentSuiteXls, classNameText);

	}
	
	





	//@Test(dataProvider = "getTestData")
	@Test(dataProviderClass = TestDataProvider.class,dataProvider = "ModBrack_WRAS_Approved")
	public void Wales_Approved_flow(
								Hashtable<String, String> table) throws Throwable {

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



			/***********************************************************************/
			

			
			/*********************************************************************/
			
			
			
			
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
			
			
		//	BS01.AddingData__Documents_Tab() ;
			
			
			
			fnpCommonClickInfoTab();
			
			String certDecider;
			String techReviewer;
			String qc;
			String externalReviewer;
			
			if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
				certDecider= (String) hashXlData.get("Cert_Decider_Code") ;
				techReviewer= (String) hashXlData.get("Tech_Reviewer_Code") ;
				qc= (String) hashXlData.get("QC_Code")  ;
				externalReviewer  =  (String) hashXlData.get("ExternalReviewer_Code");
				
			}else{
				certDecider= (String) hashXlData.get("Cert_Decider") ;
				techReviewer=  (String) hashXlData.get("Tech_Reviewer");
				qc=  (String) hashXlData.get("QC") ;
				externalReviewer  =  (String) hashXlData.get("ExternalReviewer_fullName");
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
			
		//	fnpType("OR", "Wales_InfoTab_SalesForceOpportunityID_TxtBox_id", (String) hashXlData.get("Salesforce_Opportunity_ID"));
			

			
			switch ((String) hashXlData.get("WO_Sub_Type").trim()) {
			
			case "WRAS": 
				//fnpPFListByLiNo("WO_InfoTab_ExternalReviewer_List", "WO_InfoTab_ExternalReviewer_Options", 1);
			//	fnpCommonSuggestionBox("StandardVersionTxt", (String) hashXlData.get("Standard_Version"), "StandardVersionOptionsXpath", "StandardVersionOptions");
				
				fnpPFList("WO_InfoTab_ExternalReviewer_List", "WO_InfoTab_ExternalReviewer_Options", externalReviewer);
				break;
			case "BS6920":
			//	fnpCommonSuggestionBox("StandardVersionTxt", (String) hashXlData.get("Standard_Version"), "StandardVersionOptionsXpath", "StandardVersionOptions");
				break;
			case "TMV":
				//fnpPFListByLiNo("WO_InfoTab_ExternalReviewer_List", "WO_InfoTab_ExternalReviewer_Options", 1);
				fnpPFList("WO_InfoTab_ExternalReviewer_List", "WO_InfoTab_ExternalReviewer_Options", externalReviewer);
				//fnpCommonSuggestionBox("StandardVersionTxt", (String) hashXlData.get("Standard_Version"), "StandardVersionOptionsXpath", "StandardVersionOptions");
				break;
				
			case "REG4":
				//fnpCommonSuggestionBox("StandardVersionTxt", (String) hashXlData.get("Standard_Version"), "StandardVersionOptionsXpath", "StandardVersionOptions");
				break;

				

			default:
				//fnpPFListContains_ifMatchingMultipleThenSelectFirstOne("InvoiceBillToPFList",  "NSFUSA") ;

			}
			
			
			//fnpCommonSuggestionBox("StandardVersionTxt", (String) hashXlData.get("Standard_Version"), "StandardVersionOptionsXpath", "StandardVersionOptions"); ////jira FPC-86
			fnsDD_Value_select_by_DDLabelName_and_Filter("Standard Version", (String) hashXlData.get("Standard_Version"));
			
			fnpClick_OR("ProAddDocSaveBtn");
			
			String status = "InReview";

			fnpPFList("InfoTab_WOStatusPFList", "InfoTab_WOStatusPFListOptions", status);
			
			fnpClick_OR("ProAddDocSaveBtn");
		
			fnpCommonClickSnapShotTab();
			
			int AItem_ItemAssignedTo_ColIndex = fnpFindColumnIndex("ActionItemTable_HeaderRow", actionItemTableAssignedToColName);
			int AItem_ItemDesc_ColIndex = fnpFindColumnIndex("ActionItemTable_HeaderRow", actionItemTableItemDescColName);
			
			
			fnpVerifyAIs() ;
			
			
/*			
			fnpVerifyAI_and_ClientAppReviewTask_afterDraftToInreview();
			fnpVerifyClientAppReviewerTask();
			

			int verifyCIFAiRowNo = fnpFindRow("ActionItemTable_EditWO", actionItemDesc_VerifyCIF, AItem_ItemDesc_ColIndex);
			String verifyCIFAssigner = fnpFetchFromTable("ActionItemTable_EditWO", verifyCIFAiRowNo, AItem_ItemAssignedTo_ColIndex);
			
			if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
				Assert.assertTrue(verifyCIFAssigner.trim().contains((String) hashXlData.get("AccountManager_Code").trim()), "VerifyCIF is not assigned to Account Manager because expected is this '"+(String) hashXlData.get("AccountManager_Code")+"' but actual is this '"+verifyCIFAssigner+"'.");
			}else{
				Assert.assertEquals(verifyCIFAssigner.trim(), (String) hashXlData.get("AccountManager").trim(), "VerifyCIF is not assigned to Account Manager because expected is this '"+(String) hashXlData.get("AccountManager")+"' but actual is this '"+verifyCIFAssigner+"'.");
			}	
			fnpMymsg(actionItemDesc_VerifyCIF+"  AI is  assigned to  Account Manager  successfully i.e. '" +verifyCIFAssigner + "'. ");
			
			*/
			
			
			int clientDocRequestAiRowNo = fnpFindRow("ActionItemTable_EditWO", actionItemDesc_ClientDocRequest, AItem_ItemDesc_ColIndex);
			String clientDocRequestAssigner = fnpFetchFromTable("ActionItemTable_EditWO", clientDocRequestAiRowNo, AItem_ItemAssignedTo_ColIndex);
			
			if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
				Assert.assertTrue(clientDocRequestAssigner.trim().contains((String) hashXlData.get("AccountManager_Code").trim()), actionItemDesc_ClientDocRequest +" is not assigned to Account Manager because expected is this '"+(String) hashXlData.get("AccountManager_Code")+"' but actual is this '"+clientDocRequestAssigner+"'.");
			}else{
				Assert.assertEquals(clientDocRequestAssigner.trim(), (String) hashXlData.get("AccountManager").trim(), actionItemDesc_ClientDocRequest +"  is not assigned to Account Manager because expected is this '"+(String) hashXlData.get("AccountManager")+"' but actual is this '"+clientDocRequestAssigner+"'.");
			}	
			fnpMymsg(actionItemDesc_ClientDocRequest +"  AI is  assigned to  Account Manager  successfully i.e. '" +clientDocRequestAssigner + "'. ");
			
			
			
/*			
			int initialClientEducationAiRowNo = fnpFindRow("ActionItemTable_EditWO", actionItemDesc_InitialClientEducation, AItem_ItemDesc_ColIndex);
			String initialClientEducationAssigner = fnpFetchFromTable("ActionItemTable_EditWO", initialClientEducationAiRowNo, AItem_ItemAssignedTo_ColIndex);
			
			if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
				Assert.assertTrue(initialClientEducationAssigner.trim().contains((String) hashXlData.get("AccountManager_Code").trim()), actionItemDesc_InitialClientEducation +" is not assigned to Account Manager because expected is this '"+(String) hashXlData.get("AccountManager_Code")+"' but actual is this '"+initialClientEducationAssigner+"'.");
			}else{
				Assert.assertEquals(initialClientEducationAssigner.trim(), (String) hashXlData.get("AccountManager").trim(), actionItemDesc_InitialClientEducation +"  is not assigned to Account Manager because expected is this '"+(String) hashXlData.get("AccountManager")+"' but actual is this '"+initialClientEducationAssigner+"'.");
			}	
			fnpMymsg(actionItemDesc_InitialClientEducation +"  AI is  assigned to  Account Manager  successfully i.e. '" +initialClientEducationAssigner + "'. ");
			
				
			*/

			fnpVerifyAllTasksInCreatedStatus_TasksTab();
			
			
			
			
/*			fnpCommonClickSnapShotTab();
			fnpProcessAI(actionItemDesc_ClientDocRequest, "Submitted");
			
			int taskDescColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", SnapShot_taskTable_TaskDescColName);
			int statusColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", SnapShot_taskTable_StatusColName);

			int modBrackReviewTaskRowNo = fnpFindRow("Snapshot_TasksSummaryTable", SnapShot_ModBrack_Review_Task, taskDescColIndex);

			String modBrackReviewTaskStatus = fnpFetchFromTable("Snapshot_TasksSummaryTable", modBrackReviewTaskRowNo, statusColIndex);

			//String expectedmodBrackReviewTaskStatus = (String) hashXlData.get("Change_Status_Completed").trim();
			String expectedmodBrackReviewTaskStatus = "Ready";

			Assert.assertTrue(modBrackReviewTaskStatus.equalsIgnoreCase(expectedmodBrackReviewTaskStatus), "ModBrack Review Task Status is not [" + expectedmodBrackReviewTaskStatus + "] .");

			fnpMymsg(" ModBrack Review Task's  Status in Snapshot tab now becomes in '" + expectedmodBrackReviewTaskStatus + "' status ");
			
			*/
			
			
			
			BS16.Mod_Brack_Review_Task() ;
			
			if (   (classNameText.equalsIgnoreCase(ModBrack_WRAS_Approved_ClassName))
					||
					(classNameText.equalsIgnoreCase(ModBrack_BS6920_Certified_ClassName))
					||
					(classNameText.equalsIgnoreCase(ModBrack_Reg4_Certified_ClassName))
					){
				fnpCommonClickTaskTab();
				nfcWo.fnpRevenuePostedAmount_equalsTo_AdjustedAmount_forSpecificTask(((String) hashXlData.get("TaskNames_Check_AdjustedAmt_equalsTo_RevenuePostedAmt")).trim());
			}
			

			
			
/*			
			fnpClick_OR("FinancialInfo_GenerateQuoteBtn");

			fnpClick_OR("SaveBtnInPopupAfterClickingGenerateQuoteBtn");
			
			if (!(currentSuiteName.equalsIgnoreCase(Dietary_Supplement_suite_Name)) ){
				fnpClick_OR("OKContinueButtonForAdditionalQuoteAI");
			}

			fnpCommonClickSnapShotTab();
			
			
			
			//fnpCommonClickSnapShotTab();
			//fnpCommonClickTaskTab();
			BS16.Client_Quote_Task();
			
		*/	
			
			
			BS16.ModBrack_Revision_Execution_Quote();
			
			if (  (classNameText.equalsIgnoreCase(ModBrack_WRAS_Approved_ClassName))
					||
					 (classNameText.equalsIgnoreCase(ModBrack_BS6920_Certified_ClassName))
						||
						 (classNameText.equalsIgnoreCase(ModBrack_Reg4_Certified_ClassName))
					){
				fnpCommonClickTaskTab();
				nfcWo.fnpRevenuePostedAmount_equalsTo_AdjustedAmount_forSpecificTask(((String) hashXlData.get("TaskNames_Check_AdjustedAmt_equalsTo_RevenuePostedAmt_after_InProcess_wo")).trim());
				nfcWo.fnpInvoiceAmount_equalsTo_AdjustedAmount_forSpecificTask(((String) hashXlData.get("TaskNames_Check_AdjustedAmt_equalsTo_InvoiceAmt")).trim());
				
				if ((classNameText.equalsIgnoreCase(ModBrack_WRAS_Approved_ClassName)) ){
					fnpMatchDiscountRushFees_inDiscountRushFeesTable_with_DiscountRushAmount_In_PendingInvoiceRequestsTable();
					fnpMatchUniqueQuotedAmount_inFinancialInfoTable_with_CertificationServicesAmount_In_PendingInvoiceRequestsTable() ;		
				}
		
			}
			
/*			if ((classNameText.equalsIgnoreCase(ModBrack_BS6920_Certified_ClassName))) {
				fnpProcessAI(actionItemDesc_DocumentFinal, "Completed");
			}
		*/
			if (!(classNameText.equalsIgnoreCase(ModBrack_BS6920_Certified_ClassName))) {
				BS05.Supplier_Forms_task();
			}
			
		
			fnpCommonCodeCertDecisionWaitTillNotReady_till_inperform();
			
			driver.close();
			fnpLaunchBrowserAndLogin();					
			fnpSearchWorkOrderOnly(workOrderNo);

			if ((
					classNameText.equalsIgnoreCase(ModBrack_WRAS_Approved_ClassName)
					||classNameText.equalsIgnoreCase(ModBrack_Reg4_Certified_ClassName)
					
					) ){
				fnpCommonClickTaskTab();
				nfcWo.fnpRevenuePostedAmount_equalsTo_AdjustedAmount_forSpecificTask(((String) hashXlData.get("TaskNames_Check_AdjustedAmt_equalsTo_RevenuePostedAmt_after_CertDecisionTask_completed")).trim());
			}
			
			
			// not for ModBrack_WRAS_Cancelled_By_Client_ClassName
			if ((classNameText.equalsIgnoreCase(ModBrack_WRAS_Approved_ClassName)) 
					||
					(classNameText.equalsIgnoreCase(ModBrack_WRAS_Rejected_ClassName)) 
					||
					(classNameText.equalsIgnoreCase(ModBrack_TMV_Certified_ClassName)) 
					
					){
				
				fnpCommonClickSnapShotTab();
				fnpProcessAI(actionItemDesc_AssessPnlReview, ((String) hashXlData.get("AssessPnlReview_changeStatus_1")).trim());
				fnpProcessAI(actionItemDesc_AssessPnlReview, ((String) hashXlData.get("AssessPnlReview_changeStatus_2")).trim());
				

				fnpMymsg(" Going to verify '"+actionItemDesc_ProdAssessReview+"' action item should be generated in Pending status and it is assigned to Account Manager");	
				fnpVerifyActionItemGeneratedOrNot (actionItemDesc_ProdAssessReview);			
				fnpVerifyAI_isAssignedToThisPerson(actionItemDesc_ProdAssessReview,(String) hashXlData.get("AccountManager"), (String) hashXlData.get("AccountManager_Code"), "Account Manager");

				fnpProcessAI(actionItemDesc_ProdAssessReview, ((String) hashXlData.get("ProdAssessReview_AI_changeStatus_1")).trim());
				fnpProcessAI(actionItemDesc_ProdAssessReview, ((String) hashXlData.get("ProdAssessReview_AI_changeStatus_2")).trim());
					
				
			}
			
			if ((classNameText.equalsIgnoreCase(ModBrack_TMV_Certified_ClassName)) ) {
				//need to code here
				//W0677608 -		ppppp
				
				fnpMymsg(" Going to verify '"+actionItemDesc_CertDecUpdateExt+"' action item should be generated in Pending status and it is assigned to Account Manager");	
				//Thread.sleep(5000);
				fnpVerifyActionItemGeneratedOrNot (actionItemDesc_CertDecUpdateExt);			
				fnpVerifyAI_isAssignedToThisPerson(actionItemDesc_CertDecUpdateExt,(String) hashXlData.get("AccountManager"), (String) hashXlData.get("AccountManager_Code"), "Account Manager");

				fnpProcessAI(actionItemDesc_CertDecUpdateExt, ((String) hashXlData.get("CertDecUpdateExt_AI_Status_1")).trim());
				
				fnpVerifyAI_isAssignedToThisPerson(actionItemDesc_CertDecUpdateExt,(String) hashXlData.get("ExternalReviewer_fullName"), (String) hashXlData.get("ExternalReviewer_Code"), "External Reviewer");				
				//fnpDesireScreenshot("External_Review_Task_is_not_assigned_to_the_External_Reviewer");
				fnpVerifyTask_isAssignedToThisPerson_atSnapshotTab(SnapShot_taskDesc_ExternalReview_Task,(String) hashXlData.get("ExternalReviewer_fullName"),(String) hashXlData.get("ExternalReviewer_Code"), "External Reviewer");
				
				
				
				
				
				fnpSwitchUser(hashXlData.get("ExternalReviewer_UserName"));
				fnpSearchWorkOrderOnly(workOrderNo);				
				fnpProcessAI(actionItemDesc_CertDecUpdateExt, ((String) hashXlData.get("CertDecUpdateExt_AI_Status_2")).trim());
			//	driver.close();
			//	fnpLaunchBrowserAndLogin();	
			//	fnpSearchWorkOrderOnly(workOrderNo);
			}
			
			
			if (!(
					classNameText.equalsIgnoreCase(ModBrack_BS6920_Certified_ClassName)  
					|| classNameText.equalsIgnoreCase(ModBrack_Reg4_Certified_ClassName)   
					)
					) {
					
					fnpCommonClickTaskTab();
	
					// ***************Complete External Review Task ***********
					fnpMymsg(" Going to click External Review Task  no. ");
					 int ColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskNoColName);
					 int taskTypeColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskTypeColName);
					 int taskRowNo = fnpFindRow("TasksTable_EditWO", taskType_ExternalReview_Task, taskTypeColIndex);
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

					switch (classNameText) {
					
					case ModBrack_WRAS_Approved_ClassName:
						fnpPFList("ExtReviewTask_DecisionPFList", "ExtReviewTask_DecisionPFListOptions", (String) hashXlData.get("ExternalReview_Decision"));
						fnpClick_OR("ExtReviewTask_TaskDetails_SaveBtn"); // taking some time
						break;
						
					case ModBrack_WRAS_Rejected_ClassName:
						fnpPFList("ExtReviewTask_DecisionPFList", "ExtReviewTask_DecisionPFListOptions", (String) hashXlData.get("ExternalReview_Decision_1"));
						fnpGetORObjectX("ExtReviewTask_TaskDetails_SaveBtn").click();
						
		/*				fnpWaitingForExpectedErrorMsg(IPULSE_CONSTANTS.ProdAssessReviewAIValidationMsgOnCompletingWithoutApprovalNo);
						String Msg = fnpGetText_OR("ErrorMessage");
						String expectedErrorMsg = IPULSE_CONSTANTS.ProdAssessReviewAIValidationMsgOnCompletingWithoutApprovalNo;
						Assert.assertTrue(Msg.toLowerCase().contains(expectedErrorMsg.toLowerCase()), "Expected top error message does not contain '" + expectedErrorMsg + "  ' message.");
						fnpMymsg("Expected top error Message is coming while completing ProdAssessReview action item without giving approval no. is ----" + Msg);
						
						*/
						
						fnpWaitingForExpectedErrorMsg("");
						
						//fnpPFList("ExtReviewTask_DecisionPFList", "ExtReviewTask_DecisionPFListOptions", (String) hashXlData.get("ExternalReview_Decision_2"));
						fnpPFListModify_usingWhenIgnoringErrorOnSelectingValues_2("ExtReviewTask_DecisionPFList", (String) hashXlData.get("ExternalReview_Decision_2"));
						fnpGetORObjectX("ExtReviewTask_TaskDetails_SaveBtn").click();
						fnpLoading_wait_withoutErrorChecking();
						fnpLoading_wait_withoutErrorChecking();
						break;
						
					case ModBrack_WRAS_Cancelled_By_Client_ClassName:
						fnpPFList("ExtReviewTask_DecisionPFList", "ExtReviewTask_DecisionPFListOptions", (String) hashXlData.get("ExternalReview_Decision"));
						fnpClick_OR("ExtReviewTask_TaskDetails_SaveBtn"); // taking some time
						break;
	
					case ModBrack_TMV_Certified_ClassName:
						fnpPFList("ExtReviewTask_DecisionPFList", "ExtReviewTask_DecisionPFListOptions", (String) hashXlData.get("ExternalReview_Decision"));
						fnpClick_OR("ExtReviewTask_TaskDetails_SaveBtn"); // taking some time
						break;
	
					default:
	
	
					}
					
				
					
				//	fnpClick_OR("ExtReviewTask_TaskDetails_SaveBtn"); // taking some time
					fnpCheckErrMsg("Error is thrown by application in External Review Task while completing the task");
					//fnpCommonBackToViewNBackBtnClick();
					
					if (fnpCheckElementPresenceImmediately("TaskTab_ScopeValidation_BackToViewBtn")) {
						fnpClick_OR("TaskTab_ScopeValidation_BackToViewBtn");
						fnpMymsg("BackToViewBtn is present, so clicked it.");
					}
					
					if (fnpCheckElementPresenceImmediately("TaskTab_ScopeValidation_BackBtn")) {
						fnpClick_OR("TaskTab_ScopeValidation_BackBtn");
						fnpMymsg("BackBtn is present, so clicked it.");
					}
					
					
					fnpCommonClickSnapShotTab();
					
					if ((classNameText.equalsIgnoreCase(ModBrack_TMV_Certified_ClassName)) ) {
							driver.close();
							fnpLaunchBrowserAndLogin();	
							fnpSearchWorkOrderOnly(workOrderNo);
					}
					
					if ((classNameText.equalsIgnoreCase(ModBrack_TMV_Certified_ClassName)) ) {

						fnpMymsg(" Going to verify '"+actionItemDesc_ListingUpdate+"' action item should be generated in Pending status and it is assigned to '	Marie S. Zueski'");	
						fnpVerifyActionItemGeneratedOrNot (actionItemDesc_ListingUpdate);			
						fnpVerifyAI_isAssignedToThisPerson(actionItemDesc_ListingUpdate,(String) hashXlData.get("ListingUpdateAIAssignee_Name"), (String) hashXlData.get("ListingUpdateAIAssignee_Code"), "Marie S. Zueski");
						
						fnpProcessAI(actionItemDesc_ListingUpdate, ((String) hashXlData.get("ListingUpdateAIStatus")).trim());
						fnpVerifyActionItemGeneratedOrNot (actionItemDesc_Certdecisioneducation);	
						fnpVerifyAI_isAssignedToThisPerson(actionItemDesc_Certdecisioneducation,(String) hashXlData.get("AccountManager"), (String) hashXlData.get("AccountManager_Code"), "Account Manager");
						

					}
					
					
				
					fnpVerifyTask_ColValue_at_SnapshotTab( SnapShot_taskTable_StatusColName,SnapShot_taskTable_TaskDescColName,SnapShot_taskDesc_ExternalReview_Task,"COMPLETED");
					fnpVerifyTask_ColValue_at_SnapshotTab( SnapShot_taskTable_ResultColName,SnapShot_taskTable_TaskDescColName,SnapShot_taskDesc_ExternalReview_Task,(String) hashXlData.get("ExpectedEternalReviewTask_Result"));

					
					

			}
			
		
			

			
			
			
			
			
			if (!(classNameText.equalsIgnoreCase(ModBrack_Reg4_Certified_ClassName)) ){				
				fnpCommonClickTaskTab();
				nfcWo.fnpRevenuePostedAmount_equalsTo_AdjustedAmount_forSpecificTask(((String) hashXlData.get("TaskNames_Check_AdjustedAmt_equalsTo_RevenuePostedAmt_after_ExternalReview_completed")).trim());					
			}

			fnpCommonClickSnapShotTab();
			fnpProcessAI(actionItemDesc_Certdecisioneducation,"Completed");
			

			
			/******************************************/
			
			 TestSuiteBase_ISR_suite tISR = new TestSuiteBase_ISR_suite();
			tISR.fnpRunJob("UpdateApplicationCompletedQJobScheduler");
			
			//fnpWaitTillWOStatusGetCompleteAutomatically();
			
			/******************************************/
			
			fnpVerifyWoStatus("COMPLETE");
			
			
			
			
		
			
		
	} catch (Throwable t) {
		
		fnpCommonFinalCatchBlock(t, "  Wales_wo_flow"+"--"+((String) hashXlData.get("WO_Sub_Type")).trim()+"  is fail . ", ((String) hashXlData.get("WO_Sub_Type")).trim()+"--"+"Wales_wo_flowFail");
		IsBrowserPresentAlready = false; // This is for special reason if one case is fail due to application error or any other reason then it close the
		// browser and login again.
		//driver.quit();
		
		//pradeep for time being commenting this
		//above text for wo link
	
	}
		

	}




	
	@AfterMethod
	public void reportDataSetResult() throws Throwable {
		
		fnpCommonCloseBrowsersAndKillProcess();
		
		
		if (skip)
				fnpMymsg("");
			//comment below line to not write skip in result column  OR not overwrite previous result if current test case for current data set is skipped
			//TestUtil.reportDataSetResult(currentSuiteXlsReference, this.getClass().getSimpleName(), count + 2, "SKIP");
		else if (fail) {
			isTestPass = false;
			TestUtil.reportDataSetResult(currentSuiteXls, this.getClass().getSimpleName(), count + 2, "FAIL");
		//	fnpElapsedTime(currentSuiteName, this.getClass().getSimpleName(), currentScriptCode +" ====>     User --> "+(String) hashXlData.get("User")+"   And CO --> "+ (String) hashXlData.get("CO"), "FAIL");
			fnpElapsedTime(currentSuiteName, this.getClass().getSimpleName(), currentScriptCode +" ====>     User --> "+(String) hashXlData.get("User")+"   And WO_SubType --> "+ ((String) hashXlData.get("WO_Sub_Type")).trim(), "FAIL");
		} else
			{
			TestUtil.reportDataSetResult(currentSuiteXls, this.getClass().getSimpleName(), count + 2, "PASS");			
			//currentSuiteXlsReference.setCellData("Test Cases", "Last_Successful_Execution", rowNum, fnReturnDateWithTimeForExcel());
			currentSuiteXls.setCellData(this.getClass().getSimpleName(), "Last_Successful_Execution", count + 2, fnReturnDateWithTimeForExcel());
			
			//fnpElapsedTime(currentSuiteName, this.getClass().getSimpleName(), currentScriptCode +" ====>     User --> "+(String) hashXlData.get("User")+"   And CO --> "+ (String) hashXlData.get("CO"), "PASS");
			fnpElapsedTime(currentSuiteName, this.getClass().getSimpleName(), currentScriptCode +" ====>     User --> "+(String) hashXlData.get("User")+"   And WO_SubType --> "+ ((String) hashXlData.get("WO_Sub_Type")).trim(), "PASS");
			
			}
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





/*	@DataProvider
	public Object[][] getTestData() {
		return TestUtil.getData(Proposals_suitexls, this.getClass().getSimpleName());
	}*/
}
