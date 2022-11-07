package nsf.ecap.Work_Order_suite;

//import java.awt.List;
import java.util.ArrayList;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

import nsf.ecap.util.*;
import nsf.ecap.Work_Order_suite.*;
import nsf.ecap.config.IPULSE_CONSTANTS;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.os.WindowsUtils;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;

import static nsf.ecap.config.IPULSE_CONSTANTS.*;
//BS-15
public class Modbrack_Not_Certified extends TestSuiteBase {

	@BeforeTest
	public void checkTestSkip() throws Throwable {
		BS01 = new NewNew_Draft_InReview_No_Fac();
		BS05 = new NewNew_InProc_Completed_No_Fac();
		BS12 = new WO_Failure_Resolution();

		BS01.checkTestSkip(this.getClass().getSimpleName());

	}

	@Test(priority = 1)
	public void CreateWOFlow() throws Throwable {

		BS01.CreateWOFlow();

	}

	// @Test(enabled = false)
	@Test(priority = 2, dependsOnMethods = { "CreateWOFlow" })
	public void AddingData__Facility_Tab() throws Throwable {
		BS01.AddingData__Facility_Tab();

	}

	// @Test(enabled = false)
	@Test(priority = 3, dependsOnMethods = { "AddingData__Facility_Tab" })
	public void AddingData__Tasks_Tab() throws Throwable {
		BS01.AddingData__Tasks_Tab();

	}

	// @Test(enabled = false)
	@Test(priority = 4, dependsOnMethods = { "AddingData__Tasks_Tab" })
	public void AddingData__Products_Tab() throws Throwable {

		BS01.AddingData__Products_Tab();

	}

	// @Test(enabled = false)
	@Test(priority = 5, dependsOnMethods = { "AddingData__Products_Tab" })
	public void AddingData__Financials_Tab() throws Throwable {

		BS01.AddingData__Financials_Tab();

	}

	// @Test(enabled = false)
	@Test(priority = 6, dependsOnMethods = { "AddingData__Financials_Tab" })
	public void Verifying__DRAFT_INREVIEW_ModBrack() throws Throwable {

		fnpMymsg(" **************************************************************");
		fnpMymsg(" Executing Verifying__DRAFT_INREVIEW_ModBrack");
		try {

			fnpCommonDraftToInReviewCode();

			int itemDescColIndex = fnpFindColumnIndex("ActionItemTable_HeaderRow", "Item Desc.");
			
/*			
			if (!(currentSuiteName.equalsIgnoreCase(Dietary_Supplement_suite_Name)) ){
				String ItemDesc_FirstValue = fnpFetchFromTable("ActionItemTable_EditWO", 1, itemDescColIndex);
				String ItemDesc_SecondValue = fnpFetchFromTable("ActionItemTable_EditWO", 2, itemDescColIndex);
	
				String[] expectedItemDescArray = ((String) hashXlData.get("AItem_ItemDesc_INREVIEW")).split(",");
	
				int totalRowGenerated = fnpCountRowsInTable("ActionItemTable_EditWO");
				Assert.assertTrue(totalRowGenerated == 2, "Total Action Items generated must be 2 but here they are only '" + totalRowGenerated + "'  .");
				for (int i = 0; i < expectedItemDescArray.length; i++) {
	
					if (expectedItemDescArray[i].equalsIgnoreCase(ItemDesc_FirstValue)) {
						fnpMymsg(" Action Item '" + expectedItemDescArray[i] + "' is presenst at row no '1' .");
						continue;
					}
	
					if (expectedItemDescArray[i].equalsIgnoreCase(ItemDesc_SecondValue)) {
						fnpMymsg(" Action Item '" + expectedItemDescArray[i] + "' is presenst at row no '2' .");
						continue;
					}
	
					fnpMymsg("This action item '" + expectedItemDescArray[i] + "' is not present in any row between 1-2  .");
					throw new Exception("This action item '" + expectedItemDescArray[i] + "' is not present in any row between 1-2  .");
				}
	
				int StatusColIndex = fnpFindColumnIndex("ActionItemTable_HeaderRow", "Status");
				String Status_FirstValue = fnpFetchFromTable("ActionItemTable_EditWO", 1, StatusColIndex);
				String Status_SecondValue = fnpFetchFromTable("ActionItemTable_EditWO", 2, StatusColIndex);
	
				String expectedStatus = "PENDING";
	
				Assert.assertTrue(Status_FirstValue.equalsIgnoreCase(expectedStatus), "First Status is not [" + expectedStatus + "] .");
				Assert.assertTrue(Status_SecondValue.equalsIgnoreCase(expectedStatus), "Second Status is not [" + expectedStatus + "] .");
				
			}	
			
			*/
			
			
			
		//	if ((currentSuiteName.equalsIgnoreCase(Dietary_Supplement_suite_Name)) ){
				int totalRowGenerated = fnpCountRowsInTable("ActionItemTable_EditWO");
				String[] expectedItemDescArray = ((String) hashXlData.get("AItem_ItemDesc_INREVIEW")).split(",");
				Assert.assertTrue(totalRowGenerated == expectedItemDescArray.length, "Total Action Items generated must be "+expectedItemDescArray.length+" (as mentioned in excel i.e. "+((String) hashXlData.get("AItem_ItemDesc_INREVIEW"))+" ) but here they are  '" + totalRowGenerated + "' in numbers .");
				int StatusColIndex = fnpFindColumnIndex("ActionItemTable_HeaderRow", "Status");
				String statusValue;
				String expectedStatus = "PENDING";
				boolean aiPresentFlag;
				
				for (int i = 0; i < expectedItemDescArray.length; i++) {
					
					aiPresentFlag=false;
					for (int j = 0; j < totalRowGenerated; j++) {
						String ItemDesc= fnpFetchFromTable("ActionItemTable_EditWO", (j+1), itemDescColIndex);
						if (expectedItemDescArray[i].equalsIgnoreCase(ItemDesc)) {
							fnpMymsg(" Action Item '" + expectedItemDescArray[i] + "' is presenst at row no '"+(j+1)+"' .");
							aiPresentFlag=true;
							
							statusValue = fnpFetchFromTable("ActionItemTable_EditWO", (j+1), StatusColIndex);
							///Assert.assertTrue(statusValue.equalsIgnoreCase(expectedStatus), "Status is not [" + expectedStatus + "] of this action item '"+expectedItemDescArray[i]+"' which is just created after moving wo to INREVIEW .");
/*							
							if ((currentSuiteName.equalsIgnoreCase(Non_Food_Compounds_suite_Name)) && (ItemDesc.equalsIgnoreCase("ClientQuoteReview")) ){
								String expectedStatus_completed = "COMPLETED";
								Assert.assertTrue(statusValue.equalsIgnoreCase(expectedStatus_completed), "Status is not [" + expectedStatus_completed + "] of this action item '"+expectedItemDescArray[i]+"' which is just created after moving wo to INREVIEW .");
							}else{
								Assert.assertTrue(statusValue.equalsIgnoreCase(expectedStatus), "Status is not [" + expectedStatus + "] of this action item '"+expectedItemDescArray[i]+"' which is just created after moving wo to INREVIEW .");
							}
							*/
							
							
							
							
							Assert.assertTrue(statusValue.equalsIgnoreCase(expectedStatus), "Status is not [" + expectedStatus + "] of this action item '"+expectedItemDescArray[i]+"' which is just created after moving wo to INREVIEW .");
							
							
							break;
						}
					}
					
					if (!(aiPresentFlag)) {					
						fnpMymsg("This action item '" + expectedItemDescArray[i] + "' is not present in any row in action item table  .");
						throw new Exception("This action item '" + expectedItemDescArray[i] + "' is not present in any row in action item table   .");	
					}

	

				}
		//	}
			
			
			
/*			
				if (currentSuiteName.equalsIgnoreCase(Dietary_Supplement_suite_Name)){
					fnpMymsg("Action Items has been generated with Pending status except ClientQuoteReview whose status is 'COMPLETED' ");
				}else{
					fnpMymsg("Action Items has been generated with Pending status. ");
				}
*/
			
				fnpMymsg("Action Items has been generated with Pending status. ");
			

			int taskDescColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", SnapShot_taskTable_TaskDescColName);
			int statusColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", SnapShot_taskTable_StatusColName);


			if ((currentSuiteName.equalsIgnoreCase(Dietary_Supplement_suite_Name)) || (currentSuiteName.equalsIgnoreCase(Non_Food_Compounds_suite_Name)) ){
				
/*				String expectedclientAppReviewTaskStatus = (String) hashXlData.get("ExpectedClientAppReviewTaskStatus").trim();
				int clientAppReviewTaskRowNo = fnpFindRow("Snapshot_TasksSummaryTable", SnapShot_Client_App_Review_Task, taskDescColIndex);
				String clientAppReviewTaskStatus = fnpFetchFromTable("Snapshot_TasksSummaryTable", clientAppReviewTaskRowNo, statusColIndex);
				Assert.assertTrue(clientAppReviewTaskStatus.equalsIgnoreCase(expectedclientAppReviewTaskStatus), "Client Quote Task Status is not [" + expectedclientAppReviewTaskStatus + "] .");
				fnpMymsg(" Client App Review Task's  Status in Snapshot tab now becomes in INPERFORM status ");
				fnpMymsg("  VerifyDRAFT_INREVIEW is Pass as status changed to 'INREVIEW' and Action Items has been generated with Pending status except ClientQuoteReview whose status is 'COMPLETED' and Client App Review Task's  status becomes 'INPERFORM' now. ");
				*/
				
			}else{
				String expectedclientQuoteTaskStatus = (String) hashXlData.get("ExpectedClientQuoteTaskStatus").trim();
				int clientQuoteTaskRowNo = fnpFindRow("Snapshot_TasksSummaryTable", SnapShot_Client_Quote_Task, taskDescColIndex);
				String clientQuoteTaskStatus = fnpFetchFromTable("Snapshot_TasksSummaryTable", clientQuoteTaskRowNo, statusColIndex);
				Assert.assertTrue(clientQuoteTaskStatus.equalsIgnoreCase(expectedclientQuoteTaskStatus), "Client Quote Task Status is not [" + expectedclientQuoteTaskStatus + "] .");
				fnpMymsg(" Client Quote Task's  Status in Snapshot tab now becomes in INPERFORM status ");
				fnpMymsg("  VerifyDRAFT_INREVIEW is Pass as status changed to 'INREVIEW' and Action Items has been generated with Pending status and Client Quote Task's  status becomes 'INPERFORM' now. ");
				
			}
			
			
			
			isTestPass = true;
			fnpMymsg(" **************************************************************");

			fnpCheckError("while Verifying__DRAFT_INREVIEW");

		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "   Verifying__DRAFT_INREVIEW  is failed . ", "Verifying__DRAFT_INREVIEW");

		}
	}

	// @Test(enabled = false)
	@Test(priority = 7, dependsOnMethods = { "Verifying__DRAFT_INREVIEW_ModBrack" })
	public void Client_Quote_Task() throws Throwable {

		try {
			fnpProcessAI(actionItemDesc_ClientQuoteReview, (String) hashXlData.get("Change_Status_Completed"));

			int taskDescColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", SnapShot_taskTable_TaskDescColName);
			int statusColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", SnapShot_taskTable_StatusColName);

			int clientQuoteTaskRowNo = fnpFindRow("Snapshot_TasksSummaryTable", SnapShot_Client_Quote_Task, taskDescColIndex);

			String clientQuoteTaskStatus = fnpFetchFromTable("Snapshot_TasksSummaryTable", clientQuoteTaskRowNo, statusColIndex);

			String expectedclientQuoteTaskStatus = (String) hashXlData.get("Change_Status_Completed").trim();

			Assert.assertTrue(clientQuoteTaskStatus.equalsIgnoreCase(expectedclientQuoteTaskStatus), "Client Quote Task Status is not [" + expectedclientQuoteTaskStatus + "] .");

			fnpMymsg(" Client Quote Task's  Status in Snapshot tab now becomes in '" + expectedclientQuoteTaskStatus + "' status ");

		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "   Client_Quote_Task  is failed . ", "Client_Quote_TaskFail");

		}
	}

	// @Test(enabled = false)
	@Test(priority = 8, dependsOnMethods = { "Client_Quote_Task" })
	public void Mod_Brack_Review_Task() throws Throwable {

		try {

			fnpProcessAI(actionItemDesc_ClientDocRequest, (String) hashXlData.get("Change_Status_Submitted_ModBrack"));

			int taskDescColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", SnapShot_taskTable_TaskDescColName);
			int statusColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", SnapShot_taskTable_StatusColName);

			int modBrackReviewTaskRowNo = fnpFindRow("Snapshot_TasksSummaryTable", SnapShot_ModBrack_Review_Task, taskDescColIndex);

			String modBrackReviewTaskStatus = fnpFetchFromTable("Snapshot_TasksSummaryTable", modBrackReviewTaskRowNo, statusColIndex);

			String expectedModBrackReviewTaskStatus = "Ready";

			Assert.assertTrue(modBrackReviewTaskStatus.equalsIgnoreCase(expectedModBrackReviewTaskStatus), "Mod/Brack Review Task Status is not ["
					+ expectedModBrackReviewTaskStatus + "] .");

			fnpMymsg(" Mod/Brack Review Task's  Status in Snapshot tab now becomes in '" + expectedModBrackReviewTaskStatus + "' status ");
			
			
			
			
			
			
			if ( (currentSuiteName.equalsIgnoreCase(Dietary_Supplement_suite_Name)) 
					|  (currentSuiteName.equalsIgnoreCase(Wales_Work_Order_suite_Name)) ){
				
				int assignedToColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", SnapShot_taskTable_AssignedToColName);
				String modBrackReview_AssignedTo = fnpFetchFromTable("Snapshot_TasksSummaryTable", modBrackReviewTaskRowNo, assignedToColIndex);
				
				//String techReviewer=(String) hashXlData.get("Tech_Reviewer").trim();
				String techReviewer;
				if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
					techReviewer=(String) hashXlData.get("Tech_Reviewer_Code").trim();
				}else{
					techReviewer=(String) hashXlData.get("Tech_Reviewer").trim();
				}
				
				
				
				
				
				//if (modBrackReview_AssignedTo.trim().equalsIgnoreCase(techReviewer)) {
				if (modBrackReview_AssignedTo.trim().contains(techReviewer)) {
					fnpMymsg("Mod/Brack Review task is assigned successfully to the Tech_Reviewer i.e. '"+techReviewer+"'.");
				}else{
					String msg="Mod/Brack Review task is NOT getting assigned  to the TechReviewer i.e. '"+techReviewer+"'. Current assignedTo value in table is--- '"+modBrackReview_AssignedTo+"'.";
					fnpMymsg(msg);
					throw new Exception(msg);
				}
				
				int assignedTo_ActionItemColIndex = fnpFindColumnIndex("ActionItemTable_HeaderRow", actionItemTableAssignedToColName);
				int itemDescColIndex = fnpFindColumnIndex("ActionItemTable_HeaderRow", actionItemTableItemDescColName);
				int clientDocRequestRowNo = fnpFindRow("ActionItemTable_EditWO", actionItemDesc_ClientDocRequest, itemDescColIndex);
				String clientDocRequest_AssignedTo = fnpFetchFromTable("ActionItemTable_EditWO", clientDocRequestRowNo, assignedTo_ActionItemColIndex);

				//if (clientDocRequest_AssignedTo.trim().equalsIgnoreCase(techReviewer)) {
				if (clientDocRequest_AssignedTo.trim().contains(techReviewer)) {
					fnpMymsg(actionItemDesc_ClientDocRequest+" action item is assigned successfully to the Tech_Reviewer i.e. '"+techReviewer+"'.");
				}else{
					String msg=actionItemDesc_ClientDocRequest+" action item is NOT getting assigned  to the TechReviewer i.e. '"+techReviewer+"'. Current assignedTo value in table is--- '"+clientDocRequest_AssignedTo+"'.";
					fnpMymsg(msg);
					throw new Exception(msg);
				}
				
				
				
				fnpMymsg("Now going to  reviewed  clientDocRequest action item.");
				fnpProcessAI(actionItemDesc_ClientDocRequest, (String) hashXlData.get("Change_Status_Reviewed_ModBrack"));
				
				assignedToColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", SnapShot_taskTable_AssignedToColName);
				modBrackReview_AssignedTo = fnpFetchFromTable("Snapshot_TasksSummaryTable", modBrackReviewTaskRowNo, assignedToColIndex);
				
				//String qc=(String) hashXlData.get("QC").trim();
				String qc;
				if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
					qc=(String) hashXlData.get("QC_Code").trim();
				}else{
					qc=(String) hashXlData.get("QC").trim();
				}
				
				//if (modBrackReview_AssignedTo.trim().equalsIgnoreCase(qc)) {
				if (modBrackReview_AssignedTo.trim().contains(qc)) {
					fnpMymsg("Mod/Brack Review task is assigned successfully to the QC i.e. '"+qc+"'.");
				}else{
					String msg="Mod/Brack Review task is NOT getting assigned  to the QC i.e. '"+qc+"'. Current assignedTo value in table is--- '"+modBrackReview_AssignedTo+"'.";
					fnpMymsg(msg);
					throw new Exception(msg);
				}
				
				
				 assignedTo_ActionItemColIndex = fnpFindColumnIndex("ActionItemTable_HeaderRow", actionItemTableAssignedToColName);
				 itemDescColIndex = fnpFindColumnIndex("ActionItemTable_HeaderRow", actionItemTableItemDescColName);
				 clientDocRequestRowNo = fnpFindRow("ActionItemTable_EditWO", actionItemDesc_ClientDocRequest, itemDescColIndex);
				 clientDocRequest_AssignedTo = fnpFetchFromTable("ActionItemTable_EditWO", clientDocRequestRowNo, assignedTo_ActionItemColIndex);

				//if (clientDocRequest_AssignedTo.trim().equalsIgnoreCase(qc)) {
				if (clientDocRequest_AssignedTo.trim().contains(qc)) {
					fnpMymsg(actionItemDesc_ClientDocRequest+" action item is assigned successfully to the QC i.e. '"+qc+"'.");
				}else{
					String msg=actionItemDesc_ClientDocRequest+" action item is NOT getting assigned  to the QC i.e. '"+qc+"'. Current assignedTo value in table is--- '"+clientDocRequest_AssignedTo+"'.";
					fnpMymsg(msg);
					throw new Exception(msg);
				}
				
				
				
				
				
				
			}
			
			
			
			
			if  (currentSuiteName.equalsIgnoreCase(Wales_Work_Order_suite_Name)) {
				fnpProcessAI(actionItemDesc_ClientDocRequest, (String) hashXlData.get("Change_Status_Completed_ModBrack"));
			}
			
			
			
			
			
			if (!(currentSuiteName.equalsIgnoreCase(Dietary_Supplement_suite_Name)) ){

					int taskColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", SnapShot_taskTable_TaskColName);
		
					Thread.sleep(500);
					String modBrackTaskNo = fnpFetchFromTable("Snapshot_TasksSummaryTable", modBrackReviewTaskRowNo, taskColIndex);
					
		/*			fnpWaitTillVisiblityOfElementNClickable_NOR(".//a[text()='" + modBrackTaskNo + "']");
					driver.findElement(By.linkText(modBrackTaskNo)).click();
					*/
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
		
					fnpWaitForVisible("TaskTab_ScopeValidation_TaskSummaryTable");
					int taskstatusColNo = fnpFindColumnIndex("LiteEval_TaskSummaryTable_header", InsideTask_taskTable_StatusColName);
					String modBrackStatus = fnpFetchFromTable("LiteEval_TaskSummaryTable", 1, taskstatusColNo);
		
					expectedModBrackReviewTaskStatus = "INPERFORM";
					Assert.assertTrue(modBrackStatus.equalsIgnoreCase(expectedModBrackReviewTaskStatus), "Mod/Brack Review Task Status is not [" + expectedModBrackReviewTaskStatus + "] .");
		
					fnpMymsg(" Mod/Brack Review Task's  Status in inside task detail table now becomes in '" + expectedModBrackReviewTaskStatus + "' status ");
		
					
					if  (!(currentSuiteName.equalsIgnoreCase(Wales_Work_Order_suite_Name))) {
						
						fnpClick_OR("TaskTab_ScopeValidation_BackToViewBtn");
						fnpClick_OR("TaskTab_ScopeValidation_BackBtn");
			
						fnpMymsg("Now going to first reviewed then completed clientDocRequest action item.");
						fnpProcessAI(actionItemDesc_ClientDocRequest, (String) hashXlData.get("Change_Status_Reviewed_ModBrack"));
						fnpProcessAI(actionItemDesc_ClientDocRequest, (String) hashXlData.get("Change_Status_Completed_ModBrack"));
						fnpMymsg("Now clientDocRequest reviewed then completed successfully.");
			
						fnpMymsg("Now going to click 'Request Literature from Client' after clicking task no.");
						Thread.sleep(500);
						
						
			/*			fnpWaitTillVisiblityOfElementNClickable_NOR(".//a[text()='" + modBrackTaskNo + "']");
						driver.findElement(By.linkText(modBrackTaskNo)).click();
						*/
						fnpClickALinkInATable(modBrackTaskNo);
						
						fnpLoading_wait();
						
						/**************Going to assign and perform again as per Kathryn mail on 26-02-2018*****/
			
						fnpWaitTillVisiblityOfElementNClickable_OR("TaskTab_ScopeValidation_AssignTaskIcon");
						fnpGetORObjectX("TaskTab_ScopeValidation_AssignTaskIcon").click();
						fnpLoading_wait();
						fnpClickInDialog_OR("TaskTab_ScopeValidation_AssignTask_SaveBtn");
						
						fnpClickInDialog_OR("SampleSelectionChangeAssigne_ConfirmationBox_YesBtn");
						
						fnpCheckError("while assigning ModBrack task");
						fnpMymsg("Mod/Brack task assigned successfully.");
			
			
						/***************************************************************/
						
						
						
						fnpClick_OR("RequestLiteratureFromClientBtn");
						
						
						
						//	fnpWaitForVisible("AddNoteTxtbox"); // now text box changed to Editor , so comment it to skip this suggested by Ruchi mam on 12-7-16
					//	fnpWaitForVisible("AddNoteEditor");
						//fnpWaitForVisible("CreatActionItemScreenSaveBtn");
						
						
						//fnpWaitForVisible("AddNoteEditor");
						//fnpWaitForVisible("AddNoteEditorTextArea");
						
						fnpWaitForVisible("CrAI_AssignedToClientRadioBtn");
			
						fnpGetORObjectX("CrAI_AssignedToClientRadioBtn").click();
						Thread.sleep(500);
			
						//fnpType("OR", "AddNoteTxtbox", (String) hashXlData.get("AddNotes_forRequestLiteratureInModBrackReviewTask"));
						
						
						
						
			/*			if (!(fnpCheckElementDisplayedImmediately("AddNoteTextArea"))) {
							fnpGetORObjectX("EditorSourceBtn").click();				
							Thread.sleep(2000);				
			
							
						}
						
						fnpGetORObjectX("AddNoteTextArea").sendKeys((String) hashXlData.get("AddNotes_forRequestLiteratureInModBrackReviewTask"));	
						
						*/
						
						
						//fnpGetORObjectX("AddNoteEditorTextArea").sendKeys((String) hashXlData.get("AddNotes_forRequestLiteratureInModBrackReviewTask"));
						
					
						//Thread.sleep(5000);
						driver.switchTo().frame("iframe_cke_mainForm:viewtsk:createFrm:accdJobepsf:jbepsfEd");
						
	/*					Thread.sleep(2000);
						driver.findElement(By.xpath(".//a[@title='Click Here To Set Focus']")).click();
						Thread.sleep(2000);*/
						
						//Thread.sleep(5000);
						driver.findElement(By.xpath(".//body[@class='cke_editable cke_editable_themed cke_contents_ltr cke_show_borders']")).sendKeys((String) hashXlData.get("AddNotes_forRequestLiteratureInModBrackReviewTask"));
						driver.switchTo().defaultContent();
						
						//Thread.sleep(2000);
						
						fnpPFList("ActionItemTab_ChangeStatusPFList", "ActionItemTab_ChangeStatusPFListOptions", (String) hashXlData.get("StatusForRequestLitInModBrack"));
						fnpGetORObjectX("CreatActionItemScreenSaveBtn").click();
			
			
			
					    fnpClick_OR("OKBtn_inWarningPopupWhenSavingAI");
			
						//Thread.sleep(2000);
						fnpWaitForVisible("SampleSelectionFacilityPFList");
						fnpCheckError("");
						fnpWaitTillVisiblityOfElementNClickable_OR("SampleSelectionFacilityPFList");
						//fnpPFList("SampleSelectionFacilityPFList", "SampleSelectionFacilityPFListOptions", (String) hashXlData.get("ModBrack_Facility"));
						fnpPFListByLiNo("SampleSelectionFacilityPFList", "SampleSelectionFacilityPFListOptions", 1);
			
						// fnpSampleSelectionInsightCode() ;
						/******* New iPulse--Insight ******/
						fnpSampleSelectionEPSFCode();
						/******* New iPulse--Insight ******/
			
					//	fnpCheckError("after clicking 'Create EPSF' button.");
			
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
			
			/*			*//******
						 * Just as a workaournd otherwise multiple RequestFollowUPBtn gets
						 * visible
						 ****//*
						driver.navigate().refresh();
						Thread.sleep(2000);
			
						*//******
						 * Just as a workaournd otherwise multiple RequestFollowUPBtn gets
						 * visible
						 ****//*
			*/
						
						
						
						
						/*****************New changes for sprint 9.2******************************/
						fnpAnnualUpdateRequiredChkbxesUsingInReviewTask();
						/*****************New changes for sprint 9.2******************************/
						
			
						
						
						fnpClick_OR("TaskTab_ScopeValidation_CompleteTaskIcon");
			
						fnpWaitTillVisiblityOfElementNClickable_OR("TaskTab_ScopeValidation_CompleteTaskSaveBtn");
			
						fnpGetORObjectX("TaskTab_ScopeValidation_CompleteTaskSaveBtn").click();
						
			
						fnpWaitingForExpectedErrorMsg("You cannot complete the ......");
						if (fnpCheckElementPresenceImmediately("ErrorMessage")) {
							fnpMymsg(" Error is thrown by application while Going to Complete the Mod/Brack Review task ");
						}
			
						String Msg = fnpGetText_OR_EvenMultipleObjectsHavingSameXpath_specialForExtractingErrorMsgTextOnlyEvenIfNotVisible("ErrorMessage");
						fnpMymsg("Top Message while completing the  Mod/Brack Review task  ----" + Msg);
			
						String expectedErrorMsg = "You cannot complete the MODBRKREVW task until you have indicated whether" + " Supplier Forms are needed or not (via the Products tab).";
						Assert.assertEquals(Msg, expectedErrorMsg, "You cannot complete the MODBRKREVW task until you have indicated whether Supplier Forms "
								+ "are needed or not (via the Products tab).' message, " + "so might be  Completing the MODBRKREVW  is NOT successful.");
			
						fnpMymsg(" Expected error message is coming i.e. 'You cannot complete the MODBRKREVW task until you have indicated whether"
								+ " Supplier Forms are needed or not (via the Products tab).");
			
						/****** 28-04-15 commented for time being *****/
						// fnpClick_OR_WithoutWait("CompleteTaskDialogCloseSign");
						// //28-04-15 commented for time being
						Thread.sleep(1000);
						fnpWaitTillVisiblityOfElementNClickable_OR("CompleteTaskDialogCloseSign");
						fnpGetORObjectX("CompleteTaskDialogCloseSign").click();
						Thread.sleep(3000);
						/****** 28-04-15 commented for time being *****/
			
						// fnpClick_OR_WithoutWait("CompleteTaskDialogCloseSign");
			
						/*			*//******
						 * Just as a workaournd otherwise multiple RequestFollowUPBtn
						 * gets visible
						 ****/
						/*
						 * driver.navigate().refresh();waaw Thread.sleep(2000);
						 *//******
						 * Just as a workaournd otherwise multiple RequestFollowUPBtn
						 * gets visible
						 ****/
			
			/*			*//****** 28-04-15 commented for time being *****//*
						// fnpClick_OR("RequestFollowUpBtn");
						fnpGetORObjectX("RequestFollowUpBtn").click();
						Thread.sleep(8000);
						*//****** 28-04-15 commented for time being *****//*
			*/
						
						//fnpClick_OR("RequestFollowUpBtn");
						fnpGetORObjectX("RequestFollowUpBtn").click();
						Thread.sleep(2000);
						//fnpLoading_wait();
			
						/*			*//******
						 * Just as a workaournd otherwise multiple RequestFollowUPBtn
						 * gets visible
						 ****/
						/*
						 * driver.navigate().refresh(); Thread.sleep(2000);
						 *//******
						 * Just as a workaournd otherwise multiple RequestFollowUPBtn
						 * gets visible
						 ****/
			
			/*			*//****** 28-04-15 commented for time being *****//*
						// fnpClick_OR("TaskTab_ScopeValidation_CompleteTaskIcon");
						fnpGetORObjectX("TaskTab_ScopeValidation_CompleteTaskIcon").click();
						Thread.sleep(5000);
			
						*//****** 28-04-15 commented for time being *****//*
						*/
						
						 
			/*				
							*//*****************New changes for sprint 9.2******************************//*
							fnpAnnualUpdateRequiredChkbxesUsingInReviewTask();
							*//*****************New changes for sprint 9.2******************************//*
							
			*/
						 
						 

						
					}else{
						
						if (!(classNameText.equalsIgnoreCase(ModBrack_BS6920_Certified_ClassName)) ){
							fnpClick_OR("RequestFollowUpBtn");
						}
						
						
						fnpAnnualUpdateRequiredChkbxesUsingInReviewTask();
					}
					

					
					
					
					 
					Thread.sleep(5000);
					fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
					fnpClick_OR("TaskTab_ScopeValidation_CompleteTaskIcon"); 
					
/*					fnpGetORObjectX("TaskTab_ScopeValidation_CompleteTaskIcon").click();
					Thread.sleep(5000);
					fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
					fnpCheckError(" after loading ");
		*/
					
					
					fnpWaitTillVisiblityOfElementNClickable_OR("TaskTab_ScopeValidation_CompleteTaskSaveBtn");
					fnpClickInDialog_OR("TaskTab_ScopeValidation_CompleteTaskSaveBtn");
		
					/*				
					if  (currentSuiteName.equalsIgnoreCase(Wales_Work_Order_suite_Name)) {
						//here I need to click on wo no. as per given in excel test case
						//but currently as there is no id present for wo no. in banner, I am clicking Back To view and Back button
						fnpCommonBackToViewNBackBtnClick();
					}
					
					
					
				if  (!(currentSuiteName.equalsIgnoreCase(Wales_Work_Order_suite_Name))) {
						
						fnpCommonBackToViewNBackBtnClick();
						
						int supplierFormTaskRowNo = fnpFindRow("Snapshot_TasksSummaryTable", SnapShot_taskDesc_SupplierForms, taskDescColIndex);
						
						String supplierFormTaskStatus = fnpFetchFromTable("Snapshot_TasksSummaryTable", supplierFormTaskRowNo, statusColIndex);
			
						String expectedSupplierFormTaskStatus = "Ready";
			
						Assert.assertTrue(supplierFormTaskStatus.equalsIgnoreCase(expectedSupplierFormTaskStatus), "Supplier Form Task Status is not [" + expectedSupplierFormTaskStatus
								+ "] .");
			
						fnpMymsg("Supplier Form Task  Status in Snapshot tab now becomes in '" + expectedSupplierFormTaskStatus + "' status ");
					}
					*/
					
					
					
				//	if  (!(currentSuiteName.equalsIgnoreCase(Wales_Work_Order_suite_Name))) {
						
					
					if  (currentSuiteName.equalsIgnoreCase(Wales_Work_Order_suite_Name)) {
						//here I need to click on wo no. as per given in excel test case
						//but currently as there is no id present for wo no. in banner, I am clicking Back To view and Back button
						fnpCommonBackToViewNBackBtnClick();
					}else{
						fnpCommonBackToViewNBackBtnClick();
					}
					
						
					
					
					
					if  ((currentSuiteName.equalsIgnoreCase(Wales_Work_Order_suite_Name))) {
						
						

						 taskDescColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", SnapShot_taskTable_TaskDescColName);
						 statusColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", SnapShot_taskTable_StatusColName);

						 modBrackReviewTaskRowNo = fnpFindRow("Snapshot_TasksSummaryTable", SnapShot_ModBrack_Review_Task, taskDescColIndex);
						 modBrackReviewTaskStatus = fnpFetchFromTable("Snapshot_TasksSummaryTable", modBrackReviewTaskRowNo, statusColIndex);
						 expectedModBrackReviewTaskStatus = "COMPLETED";
						 Assert.assertTrue(modBrackReviewTaskStatus.equalsIgnoreCase(expectedModBrackReviewTaskStatus), "Mod/Brack Review Task Status is not ["
								+ expectedModBrackReviewTaskStatus + "] .");
						fnpMymsg(" Mod/Brack Review Task's  Status in Snapshot tab now becomes in '" + expectedModBrackReviewTaskStatus + "' status ");
						
						
						 int revisionExecutionQuoteTaskRowNo = fnpFindRow("Snapshot_TasksSummaryTable", SnapShot_RevisionExecutionQuote, taskDescColIndex);
						 String revisiionExecutionQuoteTaskStatus = fnpFetchFromTable("Snapshot_TasksSummaryTable", revisionExecutionQuoteTaskRowNo, statusColIndex);
						 String expectedRevisiionExecutionQuoteTaskRowNoTaskStatus = "Ready";
						 Assert.assertTrue(revisiionExecutionQuoteTaskStatus.equalsIgnoreCase(expectedRevisiionExecutionQuoteTaskRowNoTaskStatus), "Mod/Brack Review Task Status is not ["
								+ expectedRevisiionExecutionQuoteTaskRowNoTaskStatus + "] .");
						fnpMymsg(" Revision Execution Quote Task's  Status in Snapshot tab now becomes in '" + expectedRevisiionExecutionQuoteTaskRowNoTaskStatus + "' status ");
					
						

						int assignedToColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", SnapShot_taskTable_AssignedToColName);
						String actualRevisionExecutionQuoteTask_AssignedTo = fnpFetchFromTable("Snapshot_TasksSummaryTable", revisionExecutionQuoteTaskRowNo, assignedToColIndex);
						

						String expectedRevisionExecutionQuoteAssigner;
						if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
							expectedRevisionExecutionQuoteAssigner=(String) hashXlData.get("AccountManager_Code").trim();
						}else{
							expectedRevisionExecutionQuoteAssigner=(String) hashXlData.get("AccountManager").trim();
						}
							

						if (actualRevisionExecutionQuoteTask_AssignedTo.trim().contains(expectedRevisionExecutionQuoteAssigner)) {
							fnpMymsg("Revision Execution Quote task is assigned successfully to the Account Manager i.e. '"+expectedRevisionExecutionQuoteAssigner+"'.");
						}else{
							String msg="Revision Execution Quote  task is NOT getting assigned  to the Account Manager  i.e. '"+expectedRevisionExecutionQuoteAssigner+"'. Current assignedTo value in table is--- '"+actualRevisionExecutionQuoteTask_AssignedTo+"'.";
							fnpMymsg(msg);
							throw new Exception(msg);
						}
					
						
						
						
						
						
						
						
						if (!(classNameText.equalsIgnoreCase(ModBrack_BS6920_Certified_ClassName)) ){
							
							int supplierFormTaskRowNo = fnpFindRow("Snapshot_TasksSummaryTable", SnapShot_taskDesc_SupplierForms, taskDescColIndex);
							
							String supplierFormTaskStatus = fnpFetchFromTable("Snapshot_TasksSummaryTable", supplierFormTaskRowNo, statusColIndex);
				
							String expectedSupplierFormTaskStatus = "Ready";
				
							Assert.assertTrue(supplierFormTaskStatus.equalsIgnoreCase(expectedSupplierFormTaskStatus), "Supplier Form Task Status is not [" + expectedSupplierFormTaskStatus
									+ "] .");
				
							fnpMymsg("Supplier Form Task  Status in Snapshot tab now becomes in '" + expectedSupplierFormTaskStatus + "' status ");
							
							
							String actualSupplierFormTask_AssignedTo = fnpFetchFromTable("Snapshot_TasksSummaryTable", supplierFormTaskRowNo, assignedToColIndex);
							

							String expectedSupplierFormAssigner;
							if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
								expectedSupplierFormAssigner=(String) hashXlData.get("AccountManager_Code").trim();
							}else{
								expectedSupplierFormAssigner=(String) hashXlData.get("AccountManager").trim();
							}
								

							if (actualSupplierFormTask_AssignedTo.trim().contains(expectedSupplierFormAssigner)) {
								fnpMymsg("Supplier Form task is assigned successfully to the Account Manager i.e. '"+expectedSupplierFormAssigner+"'.");
							}else{
								String msg="Supplier Form task is NOT getting assigned  to the Account Manager  i.e. '"+expectedSupplierFormAssigner+"'. Current assignedTo value in table is--- '"+actualSupplierFormTask_AssignedTo+"'.";
								fnpMymsg(msg);
								throw new Exception(msg);
							}
						
							
							
							
							
							
						}
					}
					
					
					
					
					
					

				//	}
					
					
					
					
		

			}

		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "   Mod_Brack_Review_Task  is failed . ", "Mod_Brack_Review_TaskFail");

		}
	}

	// @Test(enabled = false)
	@Test(priority = 9, dependsOnMethods = { "Mod_Brack_Review_Task" })
	public void Supplier_Forms_task() throws Throwable {
		BS05.Supplier_Forms_task();

	}

	// @Test(enabled = false)
	@Test(priority = 10, dependsOnMethods = { "Supplier_Forms_task" })
	public void ModBrack_Revision_Execution_Quote() throws Throwable {

		try {
			fnpCommonClickSnapShotTab();
			int taskDescColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", SnapShot_taskTable_TaskDescColName);
			int revisionExecutinQuoteTaskRowNo = fnpFindRow("Snapshot_TasksSummaryTable", SnapShot_RevisionExecutionQuote, taskDescColIndex);

			int taskColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", SnapShot_taskTable_TaskColName);
			String revisionExecutinQuoteTaskNo = fnpFetchFromTable("Snapshot_TasksSummaryTable", revisionExecutinQuoteTaskRowNo, taskColIndex);

			Thread.sleep(500);
			
/*			fnpWaitTillVisiblityOfElementNClickable_NOR(".//a[text()='" + revisionExecutinQuoteTaskNo + "']");
			driver.findElement(By.linkText(revisionExecutinQuoteTaskNo)).click();
			*/
			fnpClickALinkInATable(revisionExecutinQuoteTaskNo);
			fnpLoading_wait();
			
			fnpMymsg("Clicked on  Revision Execution Quote Task no. in Snapshot task Table i.e. '" + revisionExecutinQuoteTaskNo + "' .");

			fnpWaitTillVisiblityOfElementNClickable_OR("TaskTab_ScopeValidation_AssignTaskIcon");
			fnpGetORObjectX("TaskTab_ScopeValidation_AssignTaskIcon").click();
			fnpLoading_wait();

/*			fnpClick_OR_WithoutWait("TaskTab_ScopeValidation_AssignTask_SaveBtn");
			fnpLoading_wait();
			fnpLoading_waitInsideDialogBox();
		*/	
			fnpClickInDialog_OR("TaskTab_ScopeValidation_AssignTask_SaveBtn");
			
			

			fnpCheckErrMsg("Error is thrown by application after clicking AssignTask_SaveBtn");
			fnpMymsg(" Revision Execution Quote task has been assigned by clicking Assign task icon and filling details");

			fnpWaitTillVisiblityOfElementNClickable_OR("TaskTab_ScopeValidation_PerformTaskIcon");
			fnpWaitForVisible("TaskTab_ScopeValidation_PerformTaskIcon");

			fnpCheckErrMsg("Error is thrown by application after clicking perform task icon in Revision Execution Quote");
			fnpClick_OR("TaskTab_ScopeValidation_PerformTaskIcon");

			fnpClick_OR("TaskTab_ScopeValidation_CompleteTaskIcon");
			// fnpClick_OR_WithoutWait("TaskTab_ScopeValidation_CompleteTaskSaveBtn");

			// fnpClickInDialog_OR("TaskTab_ScopeValidation_CompleteTaskSaveBtn");
			fnpMymsg("@   ---Going to click TaskTab_ScopeValidation_CompleteTaskSaveBtn and it should stop here.");

			fnpGetORObjectX("TaskTab_ScopeValidation_CompleteTaskSaveBtn").click();
			/****************************************************/

/*			
			//new changes on 05-02-2021
			if ((currentSuiteName.equalsIgnoreCase(Dietary_Supplement_suite_Name)) ){
				fnpLoading_wait();
				fnpClick_OR("TaskTab_ScopeValidation_BackToViewBtn");
				fnpClick_OR("TaskTab_ScopeValidation_BackBtn");
				
				

				
			}else{
				
				fnpWaitingForExpectedErrorMsg(IPULSE_CONSTANTS.RevisionExecutionQuoteTask_completingTaskValidationMsg);
				
				if (fnpCheckElementPresenceImmediately("ErrorMessage")) {
					fnpMouseHover_OR_EvenMultipleObjectsHavingSameXpath("ErrorMessage");
					fnpMymsg(" Error is thrown by application while Going to Complete the Revision Execution Quote Task ");
				}

				String Msg = fnpGetText_OR_EvenMultipleObjectsHavingSameXpath("ErrorMessage");
				//String Msg = fnpGetText_OR_EvenMultipleObjectsHavingSameXpath_specialForExtractingErrorMsgTextOnlyEvenIfNotVisible("ErrorMessage");

				fnpMymsg("Top Message while completing the Revision Execution Quote Task  ----" + Msg);

				String expectedErrorMsg = IPULSE_CONSTANTS.RevisionExecutionQuoteTask_completingTaskValidationMsg;

				Assert.assertTrue(Msg.contains(expectedErrorMsg),"Top message does not contain '" + expectedErrorMsg
						+ "' message, so might be action of Completing the Revision Execution Quote Task  is NOT successful.");

				
				
				fnpMymsg(" Expected error message is coming i.e. '" + expectedErrorMsg + "'");

				fnpWaitTillVisiblityOfElementNClickable_OR("CompleteTaskDialogCloseSign");
				fnpGetORObjectX("CompleteTaskDialogCloseSign").click();
				Thread.sleep(2000);
				driver.navigate().refresh();
				

				
				
				fnpClick_OR("GenerateQuoteBtn_AtRevisionQuoteTask");
				fnpClick_OR("SaveBtnInPopupAfterClickingGenerateQuoteBtn");
				
				if (!(currentSuiteName.equalsIgnoreCase(Dietary_Supplement_suite_Name)) ){
					fnpClick_OR("OKContinueButtonForAdditionalQuoteAI");
				}
				
				

				fnpClick_OR("TaskTab_ScopeValidation_BackToViewBtn");
				fnpClick_OR("TaskTab_ScopeValidation_BackBtn");

				fnpCommonClickSnapShotTab();
				fnpProcessAI("Additional Quote", "Completed");

			}
			
*/
			
			/********************************************************************/
			
			
			//fnpWaitingForExpectedErrorMsg("The Revision execution quote task cannot be completed unless a ClientQuoteReview action item is attached to it.");
			fnpWaitingForExpectedErrorMsg(IPULSE_CONSTANTS.RevisionExecutionQuoteTask_completingTaskValidationMsg);
			
			if (fnpCheckElementPresenceImmediately("ErrorMessage")) {
				fnpMouseHover_OR_EvenMultipleObjectsHavingSameXpath("ErrorMessage");
				fnpMymsg(" Error is thrown by application while Going to Complete the Revision Execution Quote Task ");
			}

			String Msg = fnpGetText_OR_EvenMultipleObjectsHavingSameXpath("ErrorMessage");
			//String Msg = fnpGetText_OR_EvenMultipleObjectsHavingSameXpath_specialForExtractingErrorMsgTextOnlyEvenIfNotVisible("ErrorMessage");

			fnpMymsg("Top Message while completing the Revision Execution Quote Task  ----" + Msg);

			String expectedErrorMsg = IPULSE_CONSTANTS.RevisionExecutionQuoteTask_completingTaskValidationMsg;

			Assert.assertTrue(Msg.contains(expectedErrorMsg),"Top message does not contain '" + expectedErrorMsg
					+ "' message, so might be action of Completing the Revision Execution Quote Task  is NOT successful.");

			
			
			fnpMymsg(" Expected error message is coming i.e. '" + expectedErrorMsg + "'");

			fnpWaitTillVisiblityOfElementNClickable_OR("CompleteTaskDialogCloseSign");
			fnpGetORObjectX("CompleteTaskDialogCloseSign").click();
			Thread.sleep(2000);
			driver.navigate().refresh();
			

			
			
			fnpClick_OR("GenerateQuoteBtn_AtRevisionQuoteTask");
			fnpClick_OR("SaveBtnInPopupAfterClickingGenerateQuoteBtn");
			
			if (!(currentSuiteName.equalsIgnoreCase(Dietary_Supplement_suite_Name)) ){
				fnpClick_OR("OKContinueButtonForAdditionalQuoteAI");
			}
			
			
			if ((currentSuiteName.equalsIgnoreCase(Wales_Work_Order_suite_Name))) {
				fnpClick_OR("TaskDetailPage_wolink_atTop_xpath"); //pradeep for time being commenting this
				
/*				fnpClick_OR("TaskTab_ScopeValidation_BackToViewBtn");
				fnpClick_OR("TaskTab_ScopeValidation_BackBtn");
				*/
				fnpCommonClickSnapShotTab();
			}else{
				fnpClick_OR("TaskTab_ScopeValidation_BackToViewBtn");
				fnpClick_OR("TaskTab_ScopeValidation_BackBtn");
				fnpCommonClickSnapShotTab();
			}


			
			
			
			
			
			
			
			if ((currentSuiteName.equalsIgnoreCase(Wales_Work_Order_suite_Name))) {
				fnpVerifyActionItemGeneratedOrNot(actionItemDesc_ClientQuoteReview);
				fnpVerifyAI_isAssignedToThisPerson(actionItemDesc_ClientQuoteReview, ((String) hashXlData.get("Client_Name")).trim(), ((String) hashXlData.get("Client")).trim(), "Client");
				fnpProcessAI(actionItemDesc_ClientQuoteReview, "Completed");
			}else{
				fnpProcessAI("Additional Quote", "Completed");
			}
			
			
			
			/*******************************************************************/
			
			
			
			
			
			
			
			taskDescColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", SnapShot_taskTable_TaskDescColName);
			int statusColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", SnapShot_taskTable_StatusColName);

			int revExecQuoteTaskRowNo = fnpFindRow("Snapshot_TasksSummaryTable", SnapShot_RevisionExecutionQuote, taskDescColIndex);

			String revExecQuoteTaskTaskStatus = fnpFetchFromTable("Snapshot_TasksSummaryTable", revExecQuoteTaskRowNo, statusColIndex);
			String expectedrevExecQuoteTaskStatus = "Completed";
			Assert.assertTrue(revExecQuoteTaskTaskStatus.equalsIgnoreCase(expectedrevExecQuoteTaskStatus), SnapShot_RevisionExecutionQuote + " Task Status is not ["
					+ expectedrevExecQuoteTaskStatus + "] .");
			fnpMymsg(SnapShot_RevisionExecutionQuote + "   Status in Snapshot tab now becomes in '" + expectedrevExecQuoteTaskStatus + "' status ");

			/****************************************************/
			
			if ((currentSuiteName.equalsIgnoreCase(Wales_Work_Order_suite_Name))) {
				fnpVerifyTask_Result_at_SnapshotTab(SnapShot_RevisionExecutionQuote,"WIN") ;
				
				
				
				int certDecisionTaskRowNo = fnpFindRow("Snapshot_TasksSummaryTable", SnapShot_taskDesc_CertDeci, taskDescColIndex);
				String actualCertDecisionTaskTaskStatus = fnpFetchFromTable("Snapshot_TasksSummaryTable", certDecisionTaskRowNo, statusColIndex);
				
				String expectedCertDecisionTaskStatus;
				if ((classNameText.equalsIgnoreCase(ModBrack_BS6920_Certified_ClassName)) ){
					expectedCertDecisionTaskStatus = "READY";
				}else{
					expectedCertDecisionTaskStatus = "CREATED";
				}
				Assert.assertTrue(actualCertDecisionTaskTaskStatus.equalsIgnoreCase(expectedCertDecisionTaskStatus), SnapShot_taskDesc_CertDeci + " Task Status is not ["
						+ expectedCertDecisionTaskStatus + "] .");
				fnpMymsg(SnapShot_taskDesc_CertDeci + "   Status in Snapshot tab now becomes in '" + expectedCertDecisionTaskStatus + "' status ");

			
				if ((classNameText.equalsIgnoreCase(ModBrack_BS6920_Certified_ClassName)) ){
					String DocumentFinalAIAssigner;
					if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
						DocumentFinalAIAssigner=(String) hashXlData.get("AccountManager_Code").trim();
					}else{
						DocumentFinalAIAssigner=(String) hashXlData.get("AccountManager").trim();
					}						
					 fnpVerifyAICreated(actionItemDesc_DocumentFinal,"Pending",DocumentFinalAIAssigner) ;
				}
			
			
			
			
			
			
			
				fnpClick_OR_WithoutWait("NewlyCrWOTopBannerInfo");
	
				fnpWaitForVisible("TopBannerWOStatus");
				String WOStatus = fnpGetORObjectX("TopBannerWOStatus").getText();
	
				Assert.assertEquals(WOStatus, "INPROCESS", "Now WO status has NOT been changed to 'INPROCESS'.The WO should be  in INPROCESS status. ");
	
				fnpMymsg(" After completed the Scope Validation , Now Work Order status  changed  to 'INPROCESS' successfully.");
			}
			

		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "   ModBrack_Revision_Execution_Quote  is failed . ", "ModBrack_Revision_Execution_QuoteFail");

		}
	}

	@Test(priority = 11, dependsOnMethods = { "ModBrack_Revision_Execution_Quote" })
	public void RestartBrowserNLogin1() throws Throwable {
		try {
			String usingGoldenProcedureOrOasis = (String) hashXlData.get("Audit_usingGoldenProcedureOrOasis");
			if (!(usingGoldenProcedureOrOasis.equalsIgnoreCase("golden"))) {
				fnpCloseBroserAndLoginInIE();
				fnpSearchWorkOrderOnly(workOrderNo);
			}
		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "   RestartBrowserNLogin  is failed . ", "RestartBrowserNLogin_Failed");

		}

	}

	// @Test(enabled = false)
	@Test(priority = 12, dependsOnMethods = { "RestartBrowserNLogin1" })
	public void ModBrack_Lite_Eval() throws Throwable {

		try {
			// fnpCommonClickSnapShotTab();

			

			
			String usingGoldenProcedureOrOasis = (String) hashXlData.get("Audit_usingGoldenProcedureOrOasis");
			if (!(usingGoldenProcedureOrOasis.equalsIgnoreCase("golden"))) {
					if (!(fnpGetCurrRunningBrowserName().equalsIgnoreCase("IE"))) {
						fnpCloseBroserAndLoginInIE();
		
						fnpSearchWorkOrderOnly(workOrderNo);
						/**** Assuming search work order takes to us on Snapshot tab *****/
						// fnpCommonClickSnapShotTab();
		
					} else {
						// nothing to do as Assuming still present on Snapshot tab
					}
			}
			
			
			int taskDescColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", SnapShot_taskTable_TaskDescColName);
			int statusColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", SnapShot_taskTable_StatusColName);

			int liteEvalTaskRowNo = fnpFindRow("Snapshot_TasksSummaryTable", SnapShot_taskDesc_LiteEval, taskDescColIndex);

			String liteEvalTaskStatus = fnpFetchFromTable("Snapshot_TasksSummaryTable", liteEvalTaskRowNo, statusColIndex);
			String expectedliteEvalTaskStatus = "Ready";
			Assert.assertTrue(liteEvalTaskStatus.equalsIgnoreCase(expectedliteEvalTaskStatus), "Literature Eval Task Status is not [" + expectedliteEvalTaskStatus + "] .");
			fnpMymsg(" Literature Eval   Status in Snapshot tab now becomes in '" + expectedliteEvalTaskStatus + "' status ");

			int phyEvalTaskRowNo = fnpFindRow("Snapshot_TasksSummaryTable", SnapShot_taskDesc_PhysicalEval, taskDescColIndex);
			String phyEvalTaskStatus = fnpFetchFromTable("Snapshot_TasksSummaryTable", phyEvalTaskRowNo, statusColIndex);
			String expectedPhyEvalTaskStatus = "Ready";
			Assert.assertTrue(phyEvalTaskStatus.equalsIgnoreCase(expectedPhyEvalTaskStatus), "Physical Eval Task Status is not [" + expectedPhyEvalTaskStatus + "] .");
			fnpMymsg(" Physical Eval   Status in Snapshot tab now becomes in '" + expectedPhyEvalTaskStatus + "' status ");

			int taskColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", SnapShot_taskTable_TaskColName);
			String liteEvalTaskNo = fnpFetchFromTable("Snapshot_TasksSummaryTable", liteEvalTaskRowNo, taskColIndex);

			Thread.sleep(500);
			
			
/*			fnpWaitTillVisiblityOfElementNClickable_NOR(".//a[text()='" + liteEvalTaskNo + "']");
			driver.findElement(By.linkText(liteEvalTaskNo)).click();
			*/
			
			fnpClickALinkInATable(liteEvalTaskNo);
			fnpLoading_wait();
			
			fnpMymsg("Clicked on  Literature Eval Task no. in Snapshot task Table i.e. '" + liteEvalTaskNo + "' .");

			fnpWaitTillVisiblityOfElementNClickable_OR("TaskTab_ScopeValidation_AssignTaskIcon");
			fnpGetORObjectX("TaskTab_ScopeValidation_AssignTaskIcon").click();
			fnpLoading_wait();

			//fnpClick_OR_WithoutWait("TaskTab_ScopeValidation_AssignTask_SaveBtn");
			//fnpClick_OR("TaskTab_ScopeValidation_AssignTask_SaveBtn");
			fnpClickInDialog_OR("TaskTab_ScopeValidation_AssignTask_SaveBtn");
			fnpCheckError("while assigning Literature Eval task");
			fnpMymsg("Literature Eval task assigned successfully.");
			
			
			int copyFromAudit_ForLiteAudit;
			int copyToAudit_ForLiteAudit;
			if (usingGoldenProcedureOrOasis.equalsIgnoreCase("golden")) {
				
				int EvalInfoTable_EvalNoColIndex = fnpFindColumnIndex("LiteEval_EvaluationInfoTable_header", "Eval #");
				String EvalINfoTable_EvalNo = fnpFetchFromTable("LiteEval_EvaluationInfoTable", 1, EvalInfoTable_EvalNoColIndex);
				copyFromAudit_ForLiteAudit=Integer.parseInt((String) hashXlData.get("copy_From_Audit_ForLiteAuditTask").trim());
				copyToAudit_ForLiteAudit=Integer.parseInt(EvalINfoTable_EvalNo.trim());
				
				fnpMymsg("Just before running golden procedure - COPY_AUDIT_TRANSACTION_SUBMIT");
				fnpCallGoldenProcedure_COPY_AUDIT_TRANSACTION_SUBMIT(copyFromAudit_ForLiteAudit,copyToAudit_ForLiteAudit,(String) hashXlData.get("Auditor").trim());
				fnpMymsg("Golden procedure 'COPY_AUDIT_TRANSACTION_SUBMIT' ran successfully.");
				
				//added on 25-04-2018
				fnpClick_OR("TaskTab_ScopeValidation_PerformTaskIcon");
				
				
				fnpCommonCodeAfterComingBackFromOasis("ModBrack_liteEval");				
				
			} else {
				
				if (usingGoldenProcedureOrOasis.equalsIgnoreCase("oasis")) {
					fnpClick_OR("TaskTab_ScopeValidation_PerformTaskIcon");
					fnpCheckError("after clicking perform task icon");

					fnpCommonOasisCode("ModBrack_liteEval");
				} else {
					throw new Exception("Value is not assigned to usingGoldenOrOasis");
				}
			
			}
			
			


		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "   ModBrack_Lite_Eval  is failed . ", "ModBrack_Lite_EvalFail");

		}
	}

	// @Test(enabled = false)
	@Test(priority = 12, dependsOnMethods = { "ModBrack_Lite_Eval" })
	// @Test
	public void ModBrack_Phy_Eval() throws Throwable {

		try {

/*			if (fnpGetCurrRunningBrowserName() != "IE") {
				fnpCloseBroserAndLoginInIE();
				// fnpSearchWorkOrderAndClickEditAlso_AfterRestartWhenAlertsNo(workOrderNo);
				fnpSearchWorkOrderOnly(workOrderNo);
				*//**** Assuming search work order takes to us on Snapshot tab *****//*
				// fnpCommonClickSnapShotTab();

			} else {
				// nothing to do as Assuming still present on Snapshot tab
			}
			*/
			
			String usingGoldenProcedureOrOasis = (String) hashXlData.get("Audit_usingGoldenProcedureOrOasis");
			if (!(usingGoldenProcedureOrOasis.equalsIgnoreCase("golden"))) {
				if (!(fnpGetCurrRunningBrowserName().equalsIgnoreCase("IE"))) {
					fnpCloseBroserAndLoginInIE();
	
					fnpSearchWorkOrderOnly(workOrderNo);
					/**** Assuming search work order takes to us on Snapshot tab *****/
					// fnpCommonClickSnapShotTab();
	
				} else {
					// nothing to do as Assuming still present on Snapshot tab
				}
		}
			
			
			
			
			

			if (fnpCheckElementPresenceImmediately("TaskTab_ScopeValidation_BackBtn")) {
				fnpClick_OR("TaskTab_ScopeValidation_BackBtn");
			}

			if (!fnpCheckElementPresenceImmediately("Snapshot_TasksSummaryTable")) {
				fnpCommonClickSnapShotTab();
			} else {
				//
			}

			fnpWaitForVisible("Snapshot_TasksSummaryTable");
			int taskDescColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", SnapShot_taskTable_TaskDescColName);
			int statusColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", SnapShot_taskTable_StatusColName);

			int phyEvalTaskRowNo = fnpFindRow("Snapshot_TasksSummaryTable", SnapShot_taskDesc_PhysicalEval, taskDescColIndex);

			int taskColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", SnapShot_taskTable_TaskColName);
			String phyEvalTaskNo = fnpFetchFromTable("Snapshot_TasksSummaryTable", phyEvalTaskRowNo, taskColIndex);

			Thread.sleep(500);
			
/*			fnpWaitTillVisiblityOfElementNClickable_NOR(".//a[text()='" + phyEvalTaskNo + "']");
			// Thread.sleep(500);
			driver.findElement(By.linkText(phyEvalTaskNo)).click();
			*/
			
			fnpClickALinkInATable(phyEvalTaskNo);
			fnpLoading_wait();
			
			fnpMymsg("Clicked on  Phy Eval Task no. in Snapshot task Table i.e. '" + phyEvalTaskNo + "' .");

			fnpWaitTillVisiblityOfElementNClickable_OR("TaskTab_ScopeValidation_AssignTaskIcon");
			fnpGetORObjectX("TaskTab_ScopeValidation_AssignTaskIcon").click();
			fnpLoading_wait();

			//fnpClick_OR_WithoutWait("TaskTab_ScopeValidation_AssignTask_SaveBtn");
			//fnpClick_OR("TaskTab_ScopeValidation_AssignTask_SaveBtn");
			fnpClickInDialog_OR("TaskTab_ScopeValidation_AssignTask_SaveBtn");
			fnpCheckError("while assigning Phy Eval task");
			fnpMymsg("Literature Phy task assigned successfully.");
			
			
			
			int copyFromAudit_ForPhyAudit;
			int copyToAudit_ForPhyAudit;
			if (usingGoldenProcedureOrOasis.equalsIgnoreCase("golden")) {
				
				int EvalInfoTable_EvalNoColIndex = fnpFindColumnIndex("PhyEval_EvaluationInfoTable_header", "Eval #");
				String EvalINfoTable_EvalNo = fnpFetchFromTable("PhyEval_EvaluationInfoTable", 1, EvalInfoTable_EvalNoColIndex);
				copyFromAudit_ForPhyAudit=Integer.parseInt((String) hashXlData.get("copy_From_Audit_ForPhyAuditTask").trim());
				copyToAudit_ForPhyAudit=Integer.parseInt(EvalINfoTable_EvalNo.trim());
				
				fnpMymsg("Just before running golden procedure - COPY_AUDIT_TRANSACTION_SUBMIT");
				fnpCallGoldenProcedure_COPY_AUDIT_TRANSACTION_SUBMIT(copyFromAudit_ForPhyAudit,copyToAudit_ForPhyAudit,(String) hashXlData.get("Auditor").trim());
				fnpMymsg("Golden procedure 'COPY_AUDIT_TRANSACTION_SUBMIT' ran successfully.");
				
	
				fnpCommonCodeAfterComingBackFromOasis("ModBrack_phyEval");				
				//fnpClick_OR("TaskTab_ScopeValidation_BackToViewBtn");  no need as we need to process findings now first of phyeval
				
			} else {
				
				if (usingGoldenProcedureOrOasis.equalsIgnoreCase("oasis")) {
					fnpClick_OR("TaskTab_ScopeValidation_PerformTaskIcon");
					fnpCheckError("after clicking perform task icon");
					fnpCommonOasisCode("ModBrack_phyEval");

					
				} else {
					throw new Exception("Value is not assigned to usingGoldenOrOasis");
				}
			
			}
			
			
			
			
			
			
			
			
			
/*			fnpClick_OR("TaskTab_ScopeValidation_PerformTaskIcon");
			fnpCheckError("after clicking perform task icon");

			fnpCommonOasisCode("ModBrack_phyEval");
*/
		
			
			fnpCommonProcessPhyEvalFindings();
			
			fnpCommonBackToViewNBackBtnClick();

			/********* Going to Process Lite Eval Findings ***********************/

			taskColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", SnapShot_taskTable_TaskColName);
			int liteEvalTaskRowNo = fnpFindRow("Snapshot_TasksSummaryTable", SnapShot_taskDesc_LiteEval, taskDescColIndex);
			String liteEvalTaskNo = fnpFetchFromTable("Snapshot_TasksSummaryTable", liteEvalTaskRowNo, taskColIndex);

			Thread.sleep(500);
			
			
/*			fnpWaitTillVisiblityOfElementNClickable_NOR(".//a[text()='" + liteEvalTaskNo + "']");
			driver.findElement(By.linkText(liteEvalTaskNo)).click();
			*/
			fnpClickALinkInATable(liteEvalTaskNo);
			fnpLoading_wait();
			
			fnpMymsg("Clicked on  Literature Eval Task no. in Snapshot task Table i.e. '" + liteEvalTaskNo + "' .");

			fnpCommonProcessLiteEvalFindings();

			/**** Refresh button is not present in LiteEval page in Modbrack ****/
			// fnpClick_OR("PhyEvalRefreshDataBtn");
			driver.navigate().refresh();
			// fnpLoading_wait();

			int LiteEvalDetailTable_ResultColIndex = fnpFindColumnIndex("LiteEval_TaskSummaryTable_header", "Result");
			String LiteEvalDetailTable_Result = fnpFetchFromTable("LiteEval_TaskSummaryTable", 1, LiteEvalDetailTable_ResultColIndex);

			String expectedResult = (String) hashXlData.get("LiteEvalTask_Result").trim();
			Assert.assertTrue(LiteEvalDetailTable_Result.equalsIgnoreCase(expectedResult), " Result in Lite Eval detail table should become " + expectedResult
					+ "  but it is coming  '" + LiteEvalDetailTable_Result + "' ");
			fnpMymsg(" Result in Lite Eval detail table is now become ---" + LiteEvalDetailTable_Result);

			int EvalInfoTable_StausColIndex = fnpFindColumnIndex("LiteEval_EvaluationInfoTable_header", "Status");
			String EvalINfoTable_status = fnpFetchFromTable("LiteEval_EvaluationInfoTable", 1, EvalInfoTable_StausColIndex);

			fnpMymsg("Going to verify/check any one status as per karen discussion either Reviewed or completed");
			fnpMymsg(" status displayed in Evaluation Info table is ---" + EvalINfoTable_status);

			if (EvalINfoTable_status.equalsIgnoreCase("reviewed")) {
				fnpMymsg(" status in Evaluation Info table is now become REVIEWED .");

			} else {
				if (EvalINfoTable_status.equalsIgnoreCase("completed")) {
					fnpMymsg(" status in Evaluation Info table is now become COMPLETED .");

				} else {
					throw new Exception("status in Evaluation Info table must become either REVIEWED or COMPLETED but it is showing --" + EvalINfoTable_status);
				}

			}

			fnpClick_OR("TaskTab_ScopeValidation_BackBtn");

			/**********************************************************************/

		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "   ModBrack_Phy_Eval  is failed . ", "ModBrack_Phy_EvalFail");

		}
	}

	@Test(priority = 13, dependsOnMethods = { "ModBrack_Phy_Eval" })
	public void ModBrack_Certification_Decision_Task() throws Throwable {

		fnpMymsg(" **************************************************************");
		fnpMymsg(" Executing ModBrack_Certification_Decision_Task");

		try {

			// if (fnpGetCurrRunningBrowserName() != "IE") {
			if (!(fnpGetCurrRunningBrowserName().equalsIgnoreCase(browserName))) {
				BS05.RestartBrowserNLogin1();
				// fnpSearchWorkOrderAndClickEditAlso_AfterRestartWhenAlertsNo(workOrderNo);
				fnpSearchWorkOrderOnly(workOrderNo);
				// fnpCommonClickTaskTab();

			}

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
			//int failureResolutionRowNo = fnpFindRow("ActionItemTable_EditWO", actionItemDesc_FailureResolution, itemDescColIndex);
			int failureResolutionRowNo = fnpFindRow_ActionItem("ActionItemTable_EditWO", actionItemDesc_FailureResolution, itemDescColIndex);

			//Assert.assertTrue(failureResolutionRowNo > 0, "Action Item 'Failure Resolution' has not been generated.  ");
			fnpMymsg("Action Item 'Failure Resolution' has  been generated successfully.  ");

			fnpProcessAI(actionItemDesc_FailureResolution, (String) hashXlData.get("Change_StatusFailureResolution_Completed"));

			fnpProcessAI(actionItemDesc_Certdecisioneducation, (String) hashXlData.get("ChangeStatus_CertDeciEducationAI_Completed"));

			fnpCommonClickTaskTab();

			int TaskTypeColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskTypeColName);
			int TaskStatusColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_StatusColName);
			int RowNoForCertDecision = fnpFindRow("TasksTable_EditWO", taskType_CertDecision, TaskTypeColIndex);

			String certDecisionStatus = fnpFetchFromTable("TasksTable_EditWO", RowNoForCertDecision, TaskStatusColIndex);

			Assert.assertTrue(certDecisionStatus.toLowerCase().contains("completed"),
					"After completed the Cert Decision Task after assign,perform,complete , status has not been changed  to 'COMPLETED' . It is still showing '"
							+ certDecisionStatus + "'.");

			fnpMymsg(" After completed the Cert Decision Task after assign,perform,complete , status has  been changed  to 'COMPLETED' successfully.");

			fnpMymsg("  Completed the Certification_Decision_Task Task successfully ");

			fnpCheckError(" while Going to Complete Certification_Decision_Task");

		} catch (Throwable t) {
			fnpCommonFinalCatchBlock(t, "   ModBrack_Certification_Decision_Task  is failed . ", "ModBrack_Certification_Decision_TaskFail");
		}

	}
	
	
	

	
	
	
	@Test(priority = 14, dependsOnMethods = { "ModBrack_Certification_Decision_Task" })
	public void CLEANUP_WO_AUTOMATION_DATA() throws Throwable {
		BS05 = new NewNew_InProc_Completed_No_Fac();		
		BS05.CLEANUP_WO_AUTOMATION_DATA();

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