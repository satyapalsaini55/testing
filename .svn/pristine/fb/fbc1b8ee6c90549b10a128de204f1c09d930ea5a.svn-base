package nsf.ecap.Work_Order_suite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nsf.ecap.util.*;
import nsf.ecap.Work_Order_suite.*;
import nsf.ecap.config.IPULSE_CONSTANTS;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.os.WindowsUtils;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;
import static nsf.ecap.config.IPULSE_CONSTANTS.*;
//BS-12
public class WO_Failure_Resolution extends TestSuiteBase {

	@BeforeTest
	public void checkTestSkip() throws Throwable {
		BS01 = new NewNew_Draft_InReview_No_Fac();
		BS05 = new NewNew_InProc_Completed_No_Fac();
		BS10 = new WO_Annual();
		BS11 = new WO_Custom();
		BS01.checkTestSkip(this.getClass().getSimpleName());
	}

	@Test(priority = 1)
	public void FailureResolution_CreateWOFlow() throws Throwable {
		// BS01.CreateWOFlow();

		fnpMymsg(" **************************************************************");
		fnpMymsg(" Executing FailureResolution_CreateWOFlow");

		// -------First Create Work Order Page ----------------------------

		try {

			fnpCommonLoginPart(classNameText);

			fnpWaitTillClickable("ClientLKPBtn");
			fnpGetORObjectX("ClientLKPBtn").click();
			fnpSearchNSelectFirstRadioBtn(1, (String) hashXlData.get("Client"), 1);

			fnpWaitForVisible("ClientTxtBox");
			String EnteredClient = fnpGetORObjectX("ClientTxtBox").getAttribute("value");
			Assert.assertTrue(EnteredClient.contains((String) hashXlData.get("Client")), "Client Value is not selected properly from lookup");
			fnpMymsg(" Client value is properly selected from client lookup");

			Thread.sleep(1000);
			fnpPFList("WOTypeList", "WOTypeListOptions", (String) hashXlData.get("WOType"));

			Thread.sleep(1000);

			fnpClick_OR("NextBtn");

			// -------Second Create Work Order Page ----------------------------

			/*
			 * fnpGetORObjectX("AccountManagerLkpBtn").click();
			 * fnpSearchNSelectFirstRadioBtn(2, (String)
			 * hashXlData.get("AccountManager"), 1); String
			 * EnteredAccountManager =
			 * fnpGetORObjectX("AccountManagerTxtBox").getAttribute("value");
			 * Assert.assertTrue(EnteredAccountManager.contains((String)
			 * hashXlData.get("AccountManager")),
			 * "Account Manager is not selected properly from lookup");
			 * fnpMymsg(
			 * " Account Manager is properly selected from client lookup");
			 */

			/*
			 * // This is special case in which we are not allowed to put
			 * Account Manager value here, leave it as it is because if we put
			 * account manager here then Account manager are not allowed to
			 * complete clientdocrequest action item.
			 * fnpWaitForVisible("AccountManagerTxtBox"); String
			 * AlreadyAccountManager =
			 * fnpGetORObjectX("AccountManagerTxtBox").getAttribute("value");
			 * 
			 * if (AlreadyAccountManager.contains((String)
			 * hashXlData.get("AccountManager").trim())) { // nothing to do now
			 * 
			 * } else { fnpClick_OR("AccountManagerLkpBtn");
			 * 
			 * fnpMymsg("Just after  click AccountManagerLkpBtn");
			 * fnpMymsg("Just before going to insert value of Account Manger");
			 * fnpSearchNSelectFirstRadioBtn(2, (String)
			 * hashXlData.get("AccountManager"), 1);
			 * fnpMymsg("Just after  insert value of Account Manger"); String
			 * EnteredAccountManager =
			 * fnpGetORObjectX("AccountManagerTxtBox").getAttribute("value");
			 * EnteredAccountManager =
			 * fnpWaitTillTextBoxDontHaveValue("AccountManagerTxtBox", "value");
			 * Assert.assertTrue(EnteredAccountManager.contains((String)
			 * hashXlData.get("AccountManager")),
			 * "Account Manager is not selected properly from lookup");
			 * fnpMymsg(
			 * " Account Manager is properly selected from client lookup"); }
			 */

			// fnpGetORObjectX("BDMLkpBtn").click();
			fnpClick_OR("BDMLkpBtn");

			//fnpSearchNSelectFirstRadioBtn(2, (String) hashXlData.get("BDM"), 1);
			if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
				fnpSearchNSelectFirstRadioBtn(1, (String) hashXlData.get("BDM_Code"), 1);
			}else{
				fnpSearchNSelectFirstRadioBtn(2, (String) hashXlData.get("BDM"), 1);
			}
			String EnteredBDM = fnpGetORObjectX("BDMTxtBox").getAttribute("value");
			//Assert.assertTrue(EnteredBDM.contains((String) hashXlData.get("BDM")), "BDM is not selected properly from lookup");
			if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
				Assert.assertTrue(EnteredBDM.contains((String) hashXlData.get("BDM_Code")), "BDM is not selected properly from lookup");
			}else{
				Assert.assertTrue(EnteredBDM.contains((String) hashXlData.get("BDM")), "BDM is not selected properly from lookup");
			}
			fnpMymsg(" BDM is properly selected from client lookup");

			//fnpPFList("WoPrimContactList", "WoPrimContactListOptions", (String) hashXlData.get("WOPrimaryContact"));
			fnpPFListByLiNo("WoPrimContactList", "WoPrimContactListOptions", 1);

			fnpType("OR", "CreateWOProductType", (String) hashXlData.get("ProductType"));
			fnpWaitForVisible("CreateWO_ProductTypeOptions");
			String xpathProductTypeValue = OR.getProperty("CreateWO_ProductTypeOptionsList") + "[@data-item-label='" + (String) hashXlData.get("ProductType").trim() + "']";
			fnpWaitForVisible_NotInOR(xpathProductTypeValue);
			fnpMouseHover_NotInOR(xpathProductTypeValue);
			driver.findElement(By.xpath(xpathProductTypeValue)).click();
			Thread.sleep(1000);
			fnpMymsg("Product type has been inserted is " + fnpGetAttribute_OR("CreateWOProductType", "value"));
			fnpLoading_wait();
			
			
			
			
			/**********Commented on Karen Request to pick first one only, subject of mail is--requirements too specific ***/
/*			
			fnpPFList("InvoiceBillToPFList", "InvoiceBillToPFListOptions", (String) hashXlData.get("FinancialTab_BillToInvoice"));
			String insertedBillToInvice = fnpGetText_OR("InvoiceBillToPFList");
			Assert.assertEquals(insertedBillToInvice, (String) hashXlData.get("FinancialTab_BillToInvoice"), "Bill To Invice value has not been inserted properly ");

		*/	
			
			//commenting below on 09-09-2019 as some validation error related to currency is started coming while adding facility
			//fnpPFListByLiNo("InvoiceBillToPFList", "InvoiceBillToPFListOptions", 2);			
			fnpPFListContains_ifMatchingMultipleThenSelectFirstOne("InvoiceBillToPFList",  "NSFUSA") ;
			
			
			
			
			
			
			fnpType("OR", "WOSummaryTxtBox", (String) hashXlData.get("WOSummary") + startExecutionTime);

			fnpType("OR", "WOScopeTxtBox", (String) hashXlData.get("WOScope"));

			// Thread.sleep(1000);
			String ListingFRSUpdateRadioBtnValue = (String) hashXlData.get("Listing_FRS_Update");

			/*
			 * String ListingFRSUpdateRadioBtnXpath =
			 * ".//*[@id='mainForm:listingfrs']/tbody/tr/td/label[normalize-space(text())='"
			 * + ListingFRSUpdateRadioBtnValue + "']/preceding-sibling::input";
			 */
			String ListingFRSUpdateRadioBtnXpath = ".//*[contains(@id,':listingfrs')]/tbody/tr/td/label[normalize-space(text())='" + ListingFRSUpdateRadioBtnValue
					+ "']/preceding-sibling::input";
			driver.findElement(By.xpath(ListingFRSUpdateRadioBtnXpath)).click();
			// Thread.sleep(1000);

			fnpWaitForVisible("RevenueProgramList");
			fnpWaitTillClickable("RevenueProgramList");
			fnpPFList("RevenueProgramList", "RevenueProgramListOptions", (String) hashXlData.get("RevenueProgram"));

			
			
			
/*			//Commented due to IPM-10470
			fnpGetORObjectX("AddLinkWOLinkText").click();
			fnpWaitForVisible("AddLinkWOSaveBtn");
			
			
			*/
			

			
			
/*			fnpGetORObjectX("AddLinkWOFristCheckBox").click();
			Thread.sleep(1000);

			fnpWaitTillVisiblityOfElementNClickable_OR("AddLinkWOSaveBtn");
			fnpGetORObjectX("AddLinkWOSaveBtn").click();
			Thread.sleep(1000);
			// fnpLoading_wait();
*/
			
			
/*			//Commented due to IPM-10470
			*//******If no WOs in the "Add Linked WOs" link, then enter 9999999 in the "Oracle Project Requiring Resolution" field.  *****//*
			//suggested by kathryn
			//if (fnpCheckElementPresenceImmediately("AddLinkWOFristCheckBox")) {
			if (fnpCheckElementDisplayed( "AddLinkWOFristCheckBox", 30)) {
				fnpGetORObjectX("AddLinkWOFristCheckBox").click();
				Thread.sleep(1000);
				
				fnpWaitTillVisiblityOfElementNClickable_OR("AddLinkWOSaveBtn");		
				fnpClick_OR("AddLinkWOSaveBtn");
			} else{
				
				fnpGetORObjectX("AddExtWOToLinkDialogCloseSign").click();
				Thread.sleep(1000);
				fnpMymsg("@Pradeep---using this --If no WOs in the \"Add Linked WOs\" link, then enter 9999999 in the \"Oracle Project Requiring Resolution\" field.  ");
				
				fnpType("OR", "Oracle_Project_requiring_resolutionTextBox", "9999999");
				
				
				
			}

			*//******If no WOs in the "Add Linked WOs" link, then enter 9999999 in the "Oracle Project Requiring Resolution" field.  *****//*
			
			
			
			*/
			
			
			
			// New changes IPM-10470
			fnpClick_OR("Add_Edit_WOs_TasksBtn");
			fnpType("OR", "OracleProjectTxtBx", "9999999");
			fnpClick_OR("SaveAndCloseBtn_InDailog_Link_Work_Order_Monitor_Testing_Tasks_Oracle_Project");
			
		
			
			
			fnpClick_OR("CreateWOBtn");

			fnpCheckError("   while creating new WO");

			// fnpWaitForVisible("TopMessage3");
			fnpWaitForVisibleHavingMultipleElements("TopMessage3");
			fnpMymsg("Top Message after create ----" + fnpGetText_OR("TopMessage3"));

			String SuccessfulMsg = fnpGetText_OR("TopMessage3");

			Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"), "Top message does not contain 'success' word, so might be work order has not been created successfully");

			String workOrderNo_text = fnpGetORObjectX("NewlyCreatedWorkOrderNo").getText();
			workOrderNo = ((workOrderNo_text.split(" "))[0]).trim();
			fnpMymsg("Newly created WO no. is:" + workOrderNo);

			fnpWaitTillClickable("NewlyCrWOTopBannerInfo");
			fnpGetORObjectX("NewlyCrWOTopBannerInfo").click();
			fnpWaitForVisible("TopBannerWOStatus");
			String NewlyWOStatus = fnpGetORObjectX("TopBannerWOStatus").getText();
			Assert.assertEquals(NewlyWOStatus, "DRAFT", "Newly created WO status is not 'DRAFT'.The WO should get created in DRAFT status. ");
			fnpMymsg("Newly created WO status is 'DRAFT'");

			isTestPass = true;
			fnpMymsg(" **************************************************************");

		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "  FailureResolution_CreateWOFlow is failed . ", "FailureResolution_CreateWOFlowFail");

		}

		// -------Second Create Work Order Page ----------------------------

	}

	@Test(priority = 2, dependsOnMethods = { "FailureResolution_CreateWOFlow" })
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
	public void AddingData__Documents_Tab() throws Throwable {

		BS01.AddingData__Documents_Tab();

	}

	@Test(priority = 6, dependsOnMethods = { "AddingData__Documents_Tab" })
	public void AddingData__Financials_Tab() throws Throwable {

		BS01.AddingData__Financials_Tab();

	}

	@Test(priority = 7, dependsOnMethods = { "AddingData__Financials_Tab" })
	public void Verifying__ActionItems_Tab() throws Throwable {
		BS01.Verifying__ActionItems_Tab();

	}

	@Test(priority = 8, dependsOnMethods = { "Verifying__ActionItems_Tab" })
	public void FailureResolutionWO_Verifying__DRAFT_INREVIEW() throws Throwable {

		fnpMymsg(" **************************************************************");
		fnpMymsg(" Executing FailureResolutionWO_Verifying__DRAFT_INREVIEW");
		try {

			fnpWaitTillClickable("InfoTab_EditWO");
			fnpCommonClickInfoTab();

			fnpWaitForVisible("InfoTab_WOStatusPFList");

			
			
		 if (!( ((String) hashXlData.get("WOType")).equalsIgnoreCase("New Client-New Product"))){
				
				/********IPM-9433**** 10.1  sprint CAR Resolution new mandatory field  12-09-2018 , except in New -New***********************************************/
				//NO value is mentioned in the xpath already, if want to change it to Yes then change in xpath
				fnpGetORObjectX("CAR_Resolution_RadioBtn_No").click();

				/**********************************************************/

		 }
		
			
			
/*
			*//************9.2 sprint Cert decider new mandatory field***********************************************//*
			
			fnpPFList("InfoTab_CertDeciderPFList", "InfoTab_CertDeciderPFListOptions", (String) hashXlData.get("Cert_Decider"));
			
			
			
			*//**********************************************************//*
			*/
			
			
			if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
				fnpPFList("InfoTab_CertDeciderPFList", "InfoTab_CertDeciderPFListOptions", (String) hashXlData.get("Cert_Decider_Code"));
				
			}else{
				fnpPFList("InfoTab_CertDeciderPFList", "InfoTab_CertDeciderPFListOptions", (String) hashXlData.get("Cert_Decider"));
			}

			
			
			
			
			String status = "InReview";

			fnpPFList("InfoTab_WOStatusPFList", "InfoTab_WOStatusPFListOptions", status);
			fnpInsertStandardVersionAtInfoTab((String) hashXlData.get("Standard_Version"));

		//	fnpPFList("InfoTab_WOStatusPFList", "InfoTab_WOStatusPFListOptions", status);
		//	fnpClick_OR("ProAddDocSaveBtn");

			fnpCheckError("   while changing status from DRAFT to InReview in Info tab");

			// fnpWaitForVisible("TopMessage3");
			fnpWaitForVisibleHavingMultipleElements("TopMessage3");
			String SuccessfulMsg = fnpGetText_OR("TopMessage3");
			fnpMymsg("Top Message after changing status from DRAFT to InReview in Info tab ----" + SuccessfulMsg);

			Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"),
					"Top message does not contain 'success' word, so might be changing status from DRAFT to InReview in Info tab is NOT successful.");

			fnpWaitTillClickable("NewlyCrWOTopBannerInfo");
			fnpGetORObjectX("NewlyCrWOTopBannerInfo").click();
			fnpWaitForVisible("TopBannerWOStatus");
			String changedWOStatus = fnpGetORObjectX("TopBannerWOStatus").getText();
			Assert.assertEquals(changedWOStatus, "INREVIEW", " WO status is not changed from 'DRAFT' to 'INREVIEW'.");
			fnpMymsg("Now  WO status has been changed from 'DRAFT' to 'INREVIEW' status.");

			fnpCommonClickSnapShotTab();

			fnpWaitForVisible("ActionItemTable_EditWO"); // Not worked
			fnpMymsg("Action Item table is visible");
			String FirstValueInTable = fnpFetchFromTable("ActionItemTable_EditWO", 1, 1);

			Assert.assertFalse(FirstValueInTable.contains("No Data Found"), "Action items should be generated under the Action items tab but here not generated. ");

			int itemDescColIndex = fnpFindColumnIndex("ActionItemTable_HeaderRow", "Item Desc.");
			String ItemDesc_FirstValue = fnpFetchFromTable("ActionItemTable_EditWO", 1, itemDescColIndex);

			String expectedItem = ((String) hashXlData.get("AItem_ItemDesc_INREVIEW"));

			int totalRowGenerated = fnpCountRowsInTable("ActionItemTable_EditWO");
			Assert.assertTrue(totalRowGenerated == 1, "Total Action Items generated must be 1 but here they are only '" + totalRowGenerated + "'  .");

			Assert.assertEquals(ItemDesc_FirstValue, expectedItem, "ClientDocRequest action item has not been generated. ");
			fnpMymsg("'ClientDocRequest' action item has  been generated.");

			int StatusColIndex = fnpFindColumnIndex("ActionItemTable_HeaderRow", "Status");
			String Status_FirstValue = fnpFetchFromTable("ActionItemTable_EditWO", 1, StatusColIndex);

			String expectedStatus = "PENDING";

			Assert.assertTrue(Status_FirstValue.equalsIgnoreCase(expectedStatus), "First Status is not [" + expectedStatus + "] .");

			fnpMymsg("Action Items has been generated with Pending status. ");

			fnpWaitTillClickable("EditTaskTabLink");
			fnpGetORObjectX("EditTaskTabLink").click();
			fnpLoading_wait();
			// fnpWaitForVisible("TasksTable_EditWO");
			fnpWaitForVisible("Task_ShowAllLink");
			fnpWaitTillVisiblityOfElementNClickable_OR("Task_ShowAllLink");
			String expectedClientAppReviewStatus = "COMPLETED";

			int taskStatusColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_StatusColName);
			int taskTypeColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskTypeColName);
			int ClientAppReviewRowNo = fnpFindRow("TasksTable_EditWO", taskType_ClientAPPReview, taskTypeColIndex);
			String actualClientAppReviewStatus = fnpFetchFromTable("TasksTable_EditWO", ClientAppReviewRowNo, taskStatusColIndex);

			Assert.assertTrue(actualClientAppReviewStatus.equalsIgnoreCase(expectedClientAppReviewStatus), "Client App Review Status is not [" + expectedClientAppReviewStatus
					+ "] .");

			fnpMymsg(" Client App Review 's Task Status in Task tab now becomes in COMPLETED status ");

			fnpMymsg(" Going to Submit the pending Action item for ClientDocRequest ");

			/*************** Submitted the pending Actionitem(ClientDocRequest) ***********/

			fnpProcessAI(actionItemDesc_ClientDocRequest, (String) hashXlData.get("Change_Status_Submitted"));
			fnpMymsg("  Submitted the pending Action item for ClientDocRequest ");

			// ***************Completed the pending
			// Actionitem(ClientDocRequest)***********

			fnpCommonClickTaskTab();

			fnpWaitForVisible("Task_ShowAllLink");
			fnpWaitTillVisiblityOfElementNClickable_OR("Task_ShowAllLink");

			taskStatusColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_StatusColName);
			taskTypeColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskTypeColName);
			int taskTechnicalReviewRowNo = fnpFindRow("TasksTable_EditWO", taskType_TechnicalReview, taskTypeColIndex);

			String taskStatus = fnpFetchFromTable("TasksTable_EditWO", taskTechnicalReviewRowNo, taskStatusColIndex);
			Assert.assertTrue(taskStatus.equalsIgnoreCase("READY"), "Task 'Technical Review' status  is  not become 'READY' status.");

			fnpMymsg("Task 'Technical Review' status  is  now  become in  'READY' status.");

			fnpMymsg("  FailureResolutionWO_Verifying__DRAFT_INREVIEW is Pass as status changed to 'INREVIEW' and Action Items has been generated with Pending status and Client App Review's taks status becomes 'COMPLETED' now. ");
			isTestPass = true;
			fnpMymsg(" **************************************************************");

			fnpCheckError(" while  FailureResolutionWO_Verifying__DRAFT_INREVIEW ");

		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "  FailureResolutionWO_Verifying__DRAFT_INREVIEW is failed . ", "FailureResolutionWO_Verifying__DRAFT_INREVIEWFail");

		}

	}

	@Test(priority = 9, dependsOnMethods = { "FailureResolutionWO_Verifying__DRAFT_INREVIEW" })
	public void FR_Technical_Review_Task() throws Throwable {

		// BS11.Technical_Review_Task();

		// FR -Stands for Failure Resolution
		fnpMymsg(" **************************************************************");
		fnpMymsg(" Executing FR_Technical_Review_Task");
		try {

			// ***************Technical_Review_Task***********

			fnpMymsg(" Going to click Technical_Review_Task  no. ");
			fnpWaitForVisible("Task_ShowAllLink");
			fnpWaitTillVisiblityOfElementNClickable_OR("Task_ShowAllLink");
			int TaskNoColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskNoColName);
			int taskTypeColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskTypeColName);
			int technicalReviewRowNo = fnpFindRow("TasksTable_EditWO", taskType_TechnicalReview, taskTypeColIndex);

			String technicalReviewTaskNo = fnpFetchFromTable("TasksTable_EditWO", technicalReviewRowNo, TaskNoColIndex);
			Thread.sleep(500);
			
			
/*			fnpWaitTillVisiblityOfElementNClickable_NOR(".//a[text()='" + technicalReviewTaskNo + "']");
			driver.findElement(By.linkText(technicalReviewTaskNo)).click();
			*/
			fnpClickALinkInATable(technicalReviewTaskNo);
			

			fnpMymsg("Clicked on Technical_Review_Task no. in Task Table i.e. '" + technicalReviewTaskNo + "' .");
			fnpWaitTillVisiblityOfElementNClickable_OR("TaskTab_ScopeValidation_AssignTaskIcon");

			fnpClick_OR("TaskTab_ScopeValidation_AssignTaskIcon");

			fnpClickInDialog_OR("TaskTab_ScopeValidation_AssignTask_SaveBtn");

			fnpCheckError(" while Assigning the task in Technical_Review_Task  method ");

			fnpCommonBackToViewNBackBtnClick();

			/*************** Reviewed the pending Actionitem (ClientDocRequest) ***********/
			fnpMymsg(" Going to reviewed the pending Action item for ClientDocRequest ");
			fnpProcessAI(actionItemDesc_ClientDocRequest, "Reviewed");
			fnpProcessAI(actionItemDesc_ClientDocRequest, (String) hashXlData.get("Change_Status_Completed"));
			fnpMymsg("  Reviewed  and then completed the pending Action item for ClientDocRequest ");

			fnpCommonClickTaskTab();
			Thread.sleep(500);
			
/*			fnpWaitTillVisiblityOfElementNClickable_NOR(".//a[text()='" + technicalReviewTaskNo + "']");
			driver.findElement(By.linkText(technicalReviewTaskNo)).click();
			*/
			
			fnpClickALinkInATable(technicalReviewTaskNo);

			
			
			/**************Going to assign again as per Kathryn mail on 26-02-2018*****/
			fnpClick_OR("TaskTab_ScopeValidation_AssignTaskIcon");
			fnpClickInDialog_OR("TaskTab_ScopeValidation_AssignTask_SaveBtn");
			fnpCheckError(" while Assigning the task again in Technical_Review_Task  method ");			
			/***************************************************************/
			
			
			fnpClick_OR("TaskTab_ScopeValidation_PerformTaskIcon");

			
			/*****************New changes for sprint 9.2******************************/
			fnpAnnualUpdateRequiredChkbxesUsingInReviewTask();
			/*****************New changes for sprint 9.2******************************/
			
			
			
			
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
					"After completed the Scope Validation after assign,perform,complete , status has not been changed  to 'COMPLETED' . It is still showing '"
							+ scopeValidationStatus + "'.");

			fnpMymsg(" After completed the Scope Validation after assign,perform,complete , status has  been changed  to 'COMPLETED' successfully.");

			fnpCommonBackToViewNBackBtnClick();

			fnpWaitForVisible("Task_ShowAllLink");
			fnpWaitTillVisiblityOfElementNClickable_OR("Task_ShowAllLink");
			fnpWaitForElementVisibility("TasksTable_EditWO", CONFIG.getProperty("genMax_waitTime"));
			int StatusColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_StatusColName);
			taskTypeColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskTypeColName);
			technicalReviewRowNo = fnpFindRow("TasksTable_EditWO", taskType_TechnicalReview, taskTypeColIndex);
			String technicalReviewStatus_TaskTable = fnpFetchFromTable("TasksTable_EditWO", technicalReviewRowNo, StatusColIndex);

			Assert.assertTrue(technicalReviewStatus_TaskTable.toLowerCase().contains("completed"),
					"In Task Table , status has not been changed  to 'COMPLETED' . It is still showing '" + technicalReviewStatus_TaskTable + "'.");

			fnpMymsg(" After completed the Technical_Review_Task , status in Task Table has  been changed  to 'COMPLETED' successfully.");

			taskTypeColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskTypeColName);
			int taskRevisionExecutionQuoteRowNo = fnpFindRow("TasksTable_EditWO", taskType_RevisionExecutionQuote, taskTypeColIndex);

			String taskStatus = fnpFetchFromTable("TasksTable_EditWO", taskRevisionExecutionQuoteRowNo, StatusColIndex);
			Assert.assertTrue(taskStatus.equalsIgnoreCase("READY"), "Task 'Revision Execution Quote' status  is  not become 'READY' status.");

			fnpMymsg("Task 'Revision Execution Quote' status  is  now  become in  'READY' status.");

			fnpCheckError("  in  FR_Technical_Review_Task method ");

		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "  FR_Technical_Review_Task is failed . ", "Technical_Review_TaskFailed");

		}

	}

	@Test(priority = 10, dependsOnMethods = { "FR_Technical_Review_Task" })
	public void FR_Revision_Execution_Quote() throws Throwable {

		fnpMymsg(" **************************************************************");
		fnpMymsg(" Executing FR_Revision_Execution_Quote");
		try {

			
			
			// ***************Technical_Review_Task***********

			fnpMymsg(" Going to click FR_Revision_Execution_Quote  no. ");

			fnpWaitForVisible("Task_ShowAllLink");
			fnpWaitTillVisiblityOfElementNClickable_OR("Task_ShowAllLink");

			int TaskNoColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskNoColName);
			int taskTypeColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskTypeColName);
			int RevisionExecutionQuoteRowNo = fnpFindRow("TasksTable_EditWO", taskType_RevisionExecutionQuote, taskTypeColIndex);

			String RevisionExecutionQuoteTaskNo = fnpFetchFromTable("TasksTable_EditWO", RevisionExecutionQuoteRowNo, TaskNoColIndex);
			Thread.sleep(500);
			
/*			fnpWaitTillVisiblityOfElementNClickable_NOR(".//a[text()='" + RevisionExecutionQuoteTaskNo + "']");
			driver.findElement(By.linkText(RevisionExecutionQuoteTaskNo)).click();
			*/
			
			fnpClickALinkInATable(RevisionExecutionQuoteTaskNo);

			// fnpLoading_wait();

			fnpMymsg("Clicked on FR_Revision_Execution_Quote no. in Task Table i.e. '" + RevisionExecutionQuoteTaskNo + "' .");

			fnpWaitTillVisiblityOfElementNClickable_OR("TaskTab_ScopeValidation_AssignTaskIcon");
			fnpGetORObjectX("TaskTab_ScopeValidation_AssignTaskIcon").click();

			fnpClick_OR("TaskTab_ScopeValidation_AssignTask_SaveBtn");

			fnpCheckError(" while Assigning the task in FR_Revision_Execution_Quote  method");

			fnpClick_OR("TaskTab_ScopeValidation_PerformTaskIcon");
			
			fnpCheckErrMsg("Error is thrown by application after clicking PerformTaskIcon");
			
			
			
			
			if (currentSuiteName.equalsIgnoreCase(Wales_Work_Order_suite_Name)) {

				fnpMymsg("@   ---Going to click TaskTab_ScopeValidation_CompleteTaskIcon");
				fnpClick_OR("TaskTab_ScopeValidation_CompleteTaskIcon");
				// fnpClick_OR_WithoutWait("TaskTab_ScopeValidation_CompleteTaskSaveBtn");

				// fnpClickInDialog_OR("TaskTab_ScopeValidation_CompleteTaskSaveBtn");
				fnpMymsg("@   ---Going to click TaskTab_ScopeValidation_CompleteTaskSaveBtn and it should stop here.");

				fnpGetORObjectX("TaskTab_ScopeValidation_CompleteTaskSaveBtn").click();
				
				
				
				fnpWaitingForExpectedErrorMsg(IPULSE_CONSTANTS.RevisionExecutionQuoteTask_completingTaskValidationMsgForResolution);
				
				if (fnpCheckElementPresenceImmediately("ErrorMessage")) {
					fnpMouseHover_OR_EvenMultipleObjectsHavingSameXpath("ErrorMessage");
					fnpMymsg(" Error is thrown by application while Going to Complete the Revision Execution Quote Task ");
				}

				String Msg = fnpGetText_OR_EvenMultipleObjectsHavingSameXpath("ErrorMessage");
				//String Msg = fnpGetText_OR_EvenMultipleObjectsHavingSameXpath_specialForExtractingErrorMsgTextOnlyEvenIfNotVisible("ErrorMessage");

				fnpMymsg("Top Message while completing the Revision Execution Quote Task  ----" + Msg);

				String expectedErrorMsg = IPULSE_CONSTANTS.RevisionExecutionQuoteTask_completingTaskValidationMsgForResolution;

				Assert.assertTrue(Msg.contains(expectedErrorMsg),"Top message does not contain '" + expectedErrorMsg
						+ "' message, so might be action of Completing the Revision Execution Quote Task  is NOT successful.");

				
				
				fnpMymsg(" Expected error message is coming i.e. '" + expectedErrorMsg + "'");

				fnpWaitTillVisiblityOfElementNClickable_OR("CompleteTaskDialogCloseSign");
				fnpGetORObjectX("CompleteTaskDialogCloseSign").click();
				Thread.sleep(2000);
				driver.navigate().refresh();
				
			}else {

			// fnpLoading_wait();
			
			fnpWaitForVisible("TaskTab_ScopeValidation_TaskSummaryTable");
			String scopeValidationStatus = fnpFetchFromTable("TaskTab_ScopeValidation_TaskSummaryTable", 1, 5);

			int start_statusComplete = 0;
			while (!(scopeValidationStatus.toLowerCase().contains("inperform"))) {
				Thread.sleep(1000);
				scopeValidationStatus = fnpFetchFromTable("TaskTab_ScopeValidation_TaskSummaryTable", 1, 5);
				start_statusComplete++;

				if (start_statusComplete > Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					break;
				}

			}

			Assert.assertTrue(scopeValidationStatus.toLowerCase().contains("inperform"),
					"After completed the FR_Revision_Execution_Quote after assign,perform,  status has not been changed  to 'INPERFORM' . It is still showing '"
							+ scopeValidationStatus + "'.");

			fnpMymsg(" After completed the FR_Revision_Execution_Quote after assign,perform, status has  been changed  to 'INPERFORM' successfully.");

			fnpCommonBackToViewNBackBtnClick();

			fnpWaitForVisible("Task_ShowAllLink");
			fnpWaitTillVisiblityOfElementNClickable_OR("Task_ShowAllLink");
			fnpWaitForElementVisibility("TasksTable_EditWO", CONFIG.getProperty("genMax_waitTime"));

			int StatusColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_StatusColName);
			String RevisionExecutionQuoteStatus_TaskTable = fnpFetchFromTable("TasksTable_EditWO", RevisionExecutionQuoteRowNo, StatusColIndex);

			Assert.assertTrue(RevisionExecutionQuoteStatus_TaskTable.toLowerCase().contains("inperform"),
					"In Task Table , status has not been changed  to 'inperform' . It is still showing '" + RevisionExecutionQuoteStatus_TaskTable + "'.");

			fnpMymsg(" After completed the FR_Revision_Execution_Quote , status in Task Table has  been changed  to 'inperform' successfully.");

			
			fnpCommonClickFinancialTab();

			if (fnpCheckElementPresenceImmediately("EditWOBtn")) {
				fnpGetORObjectX("EditWOBtn").click();
				fnpLoading_wait();
				// fnpLoading_wait();
			}

			fnpWaitForVisible("FinancialInfo_GenerateQuoteBtn");
			fnpWaitTillVisiblityOfElementNClickable_OR("FinancialInfo_GenerateQuoteBtn");
			fnpGetORObjectX("FinancialInfo_GenerateQuoteBtn").click();
			fnpLoading_wait();

			
/*			
			*//**************IPM-7890 *****************//*
			fnpWaitForVisible("Financial_AI_ItemNameTxtBx");

			String RevisionQuoteActionItem = (String) hashXlData.get("Financial_AIItemName");
			// fnpGetORObjectX("Financial_AI_ItemNameTxtBx").sendKeys(RevisionQuoteActionItem);
			fnpType("OR", "Financial_AI_ItemNameTxtBx", RevisionQuoteActionItem);
			
			*//**********************************************//*
			
			*/
			
			String RevisionQuoteActionItem =IPULSE_CONSTANTS.RevisionQuoteActionItem;
			
			fnpWaitForVisible("Financial_AI_TaskPFList");

			fnpPFList("Financial_AI_TaskPFList", "Financial_AI_TaskPFListOptions", (String) hashXlData.get("Financial_AITask"));

			Thread.sleep(3000);
			fnpPFList("ActionItemTab_ChangeStatusPFList", "ActionItemTab_ChangeStatusPFListOptions", (String) hashXlData.get("RevisionEQ_ChangeStatus"));

			fnpGetORObjectX("Financial_AI_SaveBtn").click();
			
			
			/**************IPM-7890 *****************/
			fnpClick_OR("OKContinueButton");
			/**************IPM-7890 *****************/
			
			
			
			//fnpWaitForVisibleHavingMultipleElements("TopMessage3");

			fnpCheckError(" in FR_Revision_Execution_Quote method");

			/*************** Verify and Complete the pending Action item *****/
			fnpProcessAI(RevisionQuoteActionItem, (String) hashXlData.get("Change_Status_Completed"));

			fnpMymsg("  Completed the pending Action item for RevisionQuoteActionItem ");
			// ***************Complete the pending
			// Actionitem(ClientQuoteReview)***********

			int statusColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", SnapShot_taskTable_StatusColName);

			int TaskDescColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", SnapShot_taskTable_TaskDescColName);
			int revisionExecutionQuoterowNo = fnpFindRow("Snapshot_TasksSummaryTable", SnapShot_RevisionExecutionQuote, TaskDescColIndex);

			String revisionExecutionStatus = fnpFetchFromTable("Snapshot_TasksSummaryTable", revisionExecutionQuoterowNo, statusColIndex);
			Assert.assertEquals(revisionExecutionStatus.trim(), "COMPLETED", "Revision Execution Quote task has not been completed automatically just after "
					+ "completing the Revision Execution Quote action item");

			fnpMymsg("Revision Execution Quote task has  been completed automatically just after completing the Revision Execution Quote action item");
			
			}

		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "  FR_Revision_Execution_Quote is failed . ", "FR_Revision_Execution_QuoteFailed");

		}

	}

	@Test(priority = 11, dependsOnMethods = { "FR_Revision_Execution_Quote" })
	public void FR_Certification_Decision_Task() throws Throwable {

		try {
			BS05.Certification_Decision_Task();

			fnpCommonClickTaskTab();
			fnpWaitForVisible("Task_ShowAllLink");
			fnpWaitTillVisiblityOfElementNClickable_OR("Task_ShowAllLink");

			int TaskTypeColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskTypeColName);
			int TaskStatusColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_StatusColName);
			int RowNoForCertDecison = fnpFindRow("TasksTable_EditWO", taskType_CertDecision, TaskTypeColIndex);

			String certDecisionStatus = fnpFetchFromTable("TasksTable_EditWO", RowNoForCertDecison, TaskStatusColIndex);
			Assert.assertTrue(certDecisionStatus.toLowerCase().contains("completed"),
					"In Task Table , status for Cert Decision has not been changed  to 'COMPLETED' . It is still showing '" + certDecisionStatus + "'.");

			fnpMymsg(" After completed the Cert Decision , status in Task Table has  been changed  to 'COMPLETED' successfully.");

		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "  FR_Certification_Decision_Task is failed . ", "FR_Certification_Decision_TaskFailed");

		}

	}
	
	
	
	/******** Now no longer required to click on Invoice completed task button since 7.2 sprint  **************/

/*	@Test(priority = 12, dependsOnMethods = { "FR_Certification_Decision_Task" })
	public void InVoice_Completed() throws Throwable {

		fnpCommonClickTaskTab(); // Just for workaround ...not required here....
		// fnpLoading_wait();
		BS10.InVoice_Completed();

	}

	
	 * @Test(priority = 12, dependsOnMethods = { "InVoice_Completed" }) public
	 * void LinkEducationAI_Completed() throws Throwable {
	 * 
	 * try { fnpProcessAI(actionItemDesc_LinkEducation, "Completed");
	 * 
	 * } catch (Throwable t) { fail = true; isTestPass = false;
	 * fnpMymsg("  LinkEducationAI_Completed  method is failed . " +
	 * " . Error is ---" + t.getMessage());
	 * fnpDesireScreenshot("LinkEducationAI_CompletedFailed");
	 * 
	 * String stackTrace = Throwables.getStackTraceAsString(t); String errorMsg
	 * = t.getMessage(); errorMsg = errorMsg +
	 * "\n\n\n\n LinkEducationAI_Completed  method is failed  .See screenshot 'LinkEducationAI_CompletedFailed'\n\n"
	 * + stackTrace; Exception c = new Exception(errorMsg);
	 * ErrorUtil.addVerificationFailure(c);
	 * 
	 * }
	 * 
	 * }
	 
*/
	/******** Now no longer required to click on Invoice completed task button since 7.2 sprint  **************/
	
	
	
	
	
//	@Test(priority = 13, dependsOnMethods = { "InVoice_Completed" })
	@Test(priority = 13, dependsOnMethods = { "FR_Certification_Decision_Task" })
	public void WOAnnual_INPROCESS_COMPLETE() throws Throwable {
		BS10.WOAnnual_INPROCESS_COMPLETE();

	}

	@Test(priority = 14, dependsOnMethods = { "WOAnnual_INPROCESS_COMPLETE" })
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
