package nsf.ecap.Work_Order_suite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import nsf.ecap.util.*;
import nsf.ecap.Health_Science_suite.TestSuiteBase_HealthScience;
import nsf.ecap.Work_Order_suite.*;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.os.WindowsUtils;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;
import static nsf.ecap.config.IPULSE_CONSTANTS.*;
//BS-11
public class WO_Custom extends TestSuiteBase {

	@BeforeTest
	public void checkTestSkip() throws Throwable {
		BS01 = new NewNew_Draft_InReview_No_Fac();
		BS05 = new NewNew_InProc_Completed_No_Fac();
		BS01.checkTestSkip(this.getClass().getSimpleName());
	}

	@Test(priority = 1)
	public void CreateWOFlow() throws Throwable {

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
	public void Custom_AddingData__Tasks_Tab() throws Throwable {

		fnpMymsg(" **************************************************************");
		fnpMymsg(" Executing Custom_AddingData__Tasks_Tab");
		try {

			// fnpLoading_wait();
			fnpWaitTillClickable("EditTaskTabLink");
			fnpGetORObjectX("EditTaskTabLink").click();
			// Thread.sleep(5000);

			fnpLoading_wait();

			fnpClick_OR("ApplyTaskTemplateBtn");

/*			String[] TaskTypeArray = ((String) hashXlData.get("TaskTypeName")).split(",");

			for (int i = 0; i < TaskTypeArray.length; i++) {
				String val = TaskTypeArray[i];
				
				
				//String TaskTypeCheckboxXpath = "//td[contains(text(),'" + val + "')]/preceding-sibling::td/div/div[contains(@class,'ui-chkbox-box')]";
				String TaskTypeCheckboxXpath = "//td[contains(text(),'" + val + "')]/preceding-sibling::td/div/div/span[contains(@class,'ui-chkbox-icon ui-icon')]";

				try {
					driver.findElement(By.xpath(TaskTypeCheckboxXpath)).click();
				} catch (Throwable t) {
					throw new Exception("Unable to click on Task Type '" + val + "', or may be it is not present in available tasks . ");
				}
				
				String TaskTypeCheckboxXpath = "//td[contains(text(),'" + val + "')]/preceding-sibling::td/div/div/span[contains(@class,'ui-chkbox-icon ui-icon')]";
				if (fnpCheckElementPresenceImmediately_NotInOR(TaskTypeCheckboxXpath)) {
					String isitBlank=fnpGetORObjectX___NOR(TaskTypeCheckboxXpath).getAttribute("class");
					if (isitBlank.contains("blank")) {
						try {
							driver.findElement(By.xpath(TaskTypeCheckboxXpath)).click();
							Thread.sleep(1000);
						} catch (Throwable t) {
							throw new Exception("Unable to click on Task Type '" + val + "' in available tasks . ");
						}
					}else{
						fnpMymsg("This task Task Type '" + val + "' is already checked, so not going to check it again");
					}
				} else {
					throw new Exception("May be this  Task Type '" + val + "' is not present in available tasks . ");
				}
				
				

			}
			
			*/
			
			fnpCommonSelectingTasks_InWO((String) hashXlData.get("TaskTypeName"));

		//	fnpClickInDialog_OR("ContinueBtnInTaskTab");

			fnpGetORObjectX("Task_ShowAllLink").click();
			// Thread.sleep(15000);

			String[] BillingCodeArray = ((String) hashXlData.get("BillingCode")).split(",");
			String[] TaskTypeNameArray = ((String) hashXlData.get("TaskTypeName")).split(",");
			int jBillingCounter = 0;

			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			List<WebElement> billingSaveBtns = driver.findElements(By.xpath(OR.getProperty("Task_BillingCode_SaveAllBtn")));
			while (billingSaveBtns.size() != TaskTypeNameArray.length) {
				Thread.sleep(1000);
				jBillingCounter++;
				billingSaveBtns = driver.findElements(By.xpath(OR.getProperty("Task_BillingCode_SaveAllBtn")));

			//	if (jBillingCounter > 120) { // if wait is more than 120 seconds
												// or 2 min. then throw
												// exception
				if (jBillingCounter > 60) {
					throw new Exception("Billing codes table not expended properly even after waiting for 2 min. Billing Task type count are [" + TaskTypeNameArray.length
							+ "] and  billing save all buttons  count are [" + billingSaveBtns.size() + "] .");
					

				}

				WebElement wbElement = driver.findElement(By.xpath(OR.getProperty("FooterElement")));
				new Actions(driver).moveToElement(wbElement).perform();

			}

			driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);
			
			fnpMymsg("classNameText here is---" + classNameText);
			if (((String) hashXlData.get("TaskTypeName")).contains("TECHRVW") && (classNameText.contains("Custom"))) {
				Thread.sleep(2000);
				fnpGetORObjectX("TaskTab_TechnicalReview_AddSingleBtn").click();

				fnpWaitForVisible("TaskTab_TechnicalReview_QTY2ndRow5thCol");
				fnpGetORObjectX("TaskTab_TechnicalReview_QTY2ndRow5thCol").clear();

				fnpType("OR", "TaskTab_TechnicalReview_QTY2ndRow5thCol", "0.5");

			}

			List<WebElement> billingTxt = driver.findElements(By.xpath(OR.getProperty("Task_BillingCode_txtBoxes")));

			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			int catchCounter = 0;
			while (billingTxt.size() != BillingCodeArray.length) {
				Thread.sleep(1000);
				jBillingCounter++;

				try {
					billingTxt = driver.findElements(By.xpath(OR.getProperty("Task_BillingCode_txtBoxes")));
				} catch (Throwable t) {
					if (catchCounter > 5) {
						throw new Exception(t.getMessage());
					} else {
						catchCounter++;
					}
				}

				/*
				 * if (jBillingCounter > 120) { // if wait is more than 120
				 * seconds // or 2 min. then throw // exception
				 */
				if (jBillingCounter > 30) { // if wait is more than 120 seconds
												// // or 2 min. then throw//
												// exception
					throw new Exception("Either Billing codes table not expended properly or  Billing code text boxes are not present even after waiting for 2 min. Billing codes count are [" + BillingCodeArray.length
							+ "] and  billingCode opened text boxes till now count are [" + billingTxt.size() + "] .");

				}

				WebElement wbElement = driver.findElement(By.xpath(OR.getProperty("FooterElement")));
				new Actions(driver).moveToElement(wbElement).build().perform();

			}
			
			driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);
			
			

			int j1 = 0;
			for (Iterator iterator = billingTxt.iterator(); iterator.hasNext();) {

				WebElement webElement = (WebElement) iterator.next();
				webElement.sendKeys(BillingCodeArray[j1]);
				j1++;

			}

			if (((String) hashXlData.get("TaskTypeName")).contains("FACAUD")) {

				fnpType("OR", "DeadLineDate", (String) hashXlData.get("DeadLineDate"));
				Thread.sleep(2000);
				String StandardCheckboxXpath = "//td[contains(text(),'" + (String) hashXlData.get("StandardCode") + "')]/preceding-sibling::td/input[contains(@type,'checkbox')]";
				driver.findElement(By.xpath(StandardCheckboxXpath)).click();
				Thread.sleep(1000);

			}

			WebDriverWait wait3 = new WebDriverWait(driver, Integer.parseInt(CONFIG.getProperty("genMax_waitTime")));
			int countBillngSaveBtn = billingSaveBtns.size();
			for (int i = 0; i < countBillngSaveBtn; i++) {
				// fnpLoading_wait();
				Thread.sleep(5000);
				// fnpLoading_wait();

				String oId = "mainForm:tabView:dataTable:" + i + ":savealltaskwo";

				String idxpath = "//*[@id='" + oId + "']";

				WebElement wbElement = driver.findElement(By.xpath(idxpath));
				new Actions(driver).moveToElement(wbElement).perform();
				Thread.sleep(1000);

				WebElement element3 = wait3.until(ExpectedConditions.elementToBeClickable(By.xpath(idxpath)));
				driver.findElement(By.xpath(idxpath)).click();
				fnpLoading_wait();

				fnpCheckError(" while adding Billing Code in Task Tab ");

				// fnpWaitForVisible("TopMessage3");
				fnpWaitForVisibleHavingMultipleElements("TopMessage3");
				String SuccessfulMsg = fnpGetText_OR("TopMessage3");

				Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"),
						"Top message does not contain 'success' word, so might be adding each billing code in Task Tab is not successful.");

			}

			if (((String) hashXlData.get("TaskTypeName")).contains("TECHRVW") && (classNameText.contains("Custom"))) {

				fnpGetORObjectX("TaskTab_TechnicalReview_FirstRowEditIcon").click();
				// fnpLoading_wait();

				fnpWaitForVisible("TaskTab_TechnicalReview_UAmount1stRow7thCol");
				fnpGetORObjectX("TaskTab_TechnicalReview_UAmount1stRow7thCol").clear();
				// fnpGetORObjectX("TaskTab_TechnicalReview_UAmount1stRow7thCol").sendKeys("1");
				fnpType("OR", "TaskTab_TechnicalReview_UAmount1stRow7thCol", "1");

			}

			// Click 4th Save All button only,not all
			// for (int i = 3; i < 4; i++) {
			// Click 2th Save All button only,not all
			for (int i = 1; i < 2; i++) {
				// fnpLoading_wait();
				Thread.sleep(5000);
				// fnpLoading_wait();

				String oId = "mainForm:tabView:dataTable:" + i + ":savealltaskwo";

				String idxpath = "//*[@id='" + oId + "']";

				WebElement wbElement = driver.findElement(By.xpath(idxpath));
				new Actions(driver).moveToElement(wbElement).perform();
				Thread.sleep(1000);

				WebElement element3 = wait3.until(ExpectedConditions.elementToBeClickable(By.xpath(idxpath)));
				driver.findElement(By.xpath(idxpath)).click();
				fnpLoading_wait();
				// Thread.sleep(5000);
				// fnpLoading_wait();

				if (fnpCheckElementPresenceImmediately("Task_BillCodePriceChangeConfirmationDialogTxtBx")) {
					fnpType("OR", "Task_BillCodePriceChangeConfirmationDialogTxtBx", "Testing change");
					// fnpClick_OR("Task_BillCodePriceChangeConfirmationDialogSaveBtn");
					fnpClickInDialog_OR("Task_BillCodePriceChangeConfirmationDialogSaveBtn");
				}

				fnpCheckError(" while adding Billing Code in Task Tab ");

				// fnpWaitForVisible("TopMessage3");
				fnpWaitForVisibleHavingMultipleElements("TopMessage3");
				String SuccessfulMsg = fnpGetText_OR("TopMessage3");
				Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"),
						"Top message does not contain 'success' word, so might be adding each billing code in Task Tab is not successful.");

			}

			fnpCheckError(" while adding  data in Task Tab ");

			// fnpWaitForVisible("TopMessage3");
			fnpWaitForVisibleHavingMultipleElements("TopMessage3");
			fnpMymsg("Top Message after adding billing code in Task Tab----" + fnpGetText_OR("TopMessage3"));

			String SuccessfulMsg = fnpGetText_OR("TopMessage3");

			Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"),
					"Top message does not contain 'success' word, so might be adding billing code in Task Tab is not successful.");

			fnpMymsg(" Adding data in New Wo Task tab is Pass");
			isTestPass = true;
			fnpMymsg(" **************************************************************");

			fnpCommonClickTaskTab();
			// Thread.sleep(2000);

		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "  Adding data in New Wo Task tab is failed . ", "Custom_AddingData__Tasks_Tab_Fail");

		}

	}

	@Test(priority = 5, dependsOnMethods = { "Custom_AddingData__Tasks_Tab" })
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

	@Test(priority = 9, dependsOnMethods = { "Verifying__ActionItems_Tab" })
	public void Custom_Verifying__DRAFT_INREVIEW() throws Throwable {

		fnpMymsg(" **************************************************************");
		fnpMymsg(" Executing Custom_Verifying__DRAFT_INREVIEW");
		try {
			// fnpLoading_wait();

			// fnpLoading_wait();
			fnpWaitTillClickable("InfoTab_EditWO");
			fnpCommonClickInfoTab();
			
			
			
			
		 if (!( ((String) hashXlData.get("WOType")).equalsIgnoreCase("New Client-New Product"))){
				
				/********IPM-9433**** 10.1  sprint CAR Resolution new mandatory field  12-09-2018 , except in New -New***********************************************/
				//NO value is mentioned in the xpath already, if want to change it to Yes then change in xpath
				fnpGetORObjectX("CAR_Resolution_RadioBtn_No").click();

				/**********************************************************/

		 }
		
			
			
			/************9.2 sprint Cert decider new mandatory field***********************************************/
			
			//fnpPFList("InfoTab_CertDeciderPFList", "InfoTab_CertDeciderPFListOptions", (String) hashXlData.get("Cert_Decider"));
			
			
			
			/**********************************************************/
			
			if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
				fnpPFList("InfoTab_CertDeciderPFList", "InfoTab_CertDeciderPFListOptions", (String) hashXlData.get("Cert_Decider_Code"));
				
			}else{
				fnpPFList("InfoTab_CertDeciderPFList", "InfoTab_CertDeciderPFListOptions", (String) hashXlData.get("Cert_Decider"));
			}
			
			
			

			
			/**************
			 * In new coming sprint R4.1, today 16-3-16 before releasing , on
			 * trunk this action item is not being generated and if comment this
			 * then wo getting completed successfully also
			 *****************/
			
			
			/************** New change for custom according to IPM-2081  sprint 4.1*****************/
			// Thread.sleep(1000);
			String ListingFRSUpdateRadioBtnValue = (String) hashXlData.get("Listing_FRS_Update");
			String ListingFRSUpdateRadioBtnXpath = ".//table[contains(@id,':listingfrs')]/tbody/tr/td/label[normalize-space(text())='" + ListingFRSUpdateRadioBtnValue
					+ "']/preceding-sibling::input";
			driver.findElement(By.xpath(ListingFRSUpdateRadioBtnXpath)).click();
			// Thread.sleep(1000);

			/************** New change for custom according to IPM-2081 *****************/
			
			
			/**************
			 * In new coming sprint R4.1, today 16-3-16 before releasing , on
			 * trunk this action item is not being generated and if comment this
			 * then wo getting completed successfully also
			 *****************/
	
			
			
			
			

			fnpWaitForVisible("InfoTab_WOStatusPFList");

			String status = "InReview";
			//String status=(String) hashXlData.get("Draft_to_InReview_status_value");
			
			fnpPFList("InfoTab_WOStatusPFList", "InfoTab_WOStatusPFListOptions", status);

			/******** Standard Version is no longer mandatory for time being, may be *******/
			//IPM 3668
			fnpInsertStandardVersionAtInfoTab((String) hashXlData.get("Standard_Version"));
			
			fnpCheckFacilityDropStatusAndThenRunSQLQueries();

			//fnpPFList("InfoTab_WOStatusPFList", "InfoTab_WOStatusPFListOptions", status);

			//fnpClick_OR("ProAddDocSaveBtn");

			fnpCheckError(" while changing status from DRAFT to InReview in Info tab");

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

			Assert.assertEquals(ItemDesc_FirstValue, expectedItem, "ClientQuoteReview action item has not been generated. ");
			fnpMymsg("'ClientQuoteReview' action item has  been generated.");

			int StatusColIndex = fnpFindColumnIndex("ActionItemTable_HeaderRow", "Status");
			String Status_FirstValue = fnpFetchFromTable("ActionItemTable_EditWO", 1, StatusColIndex);

			String expectedStatus = "PENDING";

			Assert.assertTrue(Status_FirstValue.equalsIgnoreCase(expectedStatus), "First Status is not [" + expectedStatus + "] .");

			fnpMymsg("Action Items has been generated with Pending status. ");

			fnpWaitTillClickable("EditTaskTabLink");
			fnpGetORObjectX("EditTaskTabLink").click();
			fnpLoading_wait();
			fnpWaitForVisible("Task_ShowAllLink");

			String expectedClientAppReviewStatus = "INPERFORM";

			int taskStatusColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_StatusColName);
			int taskTypeColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskTypeColName);
			int ClientAppReviewRowNo = fnpFindRow("TasksTable_EditWO", taskType_ClientAPPReview, taskTypeColIndex);
			String actualClientAppReviewStatus = fnpFetchFromTable("TasksTable_EditWO", ClientAppReviewRowNo, taskStatusColIndex);

			Assert.assertTrue(actualClientAppReviewStatus.equalsIgnoreCase(expectedClientAppReviewStatus), "Client App Review Status is not [" + expectedClientAppReviewStatus
					+ "] .");

			fnpMymsg(" Client App Review 's Task Status in Task tab now becomes in INPERFORM status ");

			fnpMymsg("  VerifyDRAFT_INREVIEW is Pass as status changed to 'INREVIEW' and Action Items has been generated with Pending status and Client App Review's taks status becomes 'INPERFORM' now. ");
			isTestPass = true;
			fnpMymsg(" **************************************************************");

			fnpCheckError(" while VerifyDRAFT_INREVIEW");

		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "  VerifyDRAFT_INREVIEW is failed . ", "VerifyingDraftToInReviewFail");

		}

	}

	@Test(priority = 10, dependsOnMethods = { "Custom_Verifying__DRAFT_INREVIEW" })
	public void Custom_Client_App_Review_Task() {

		fnpMymsg(" **************************************************************");
		fnpMymsg(" Executing Custom_Client_App_Review_Task");
		try {

			/*************** Complete the pending Action item ClientQuoteReview ****/
			fnpProcessAI(actionItemDesc_ClientQuoteReview, (String) hashXlData.get("Change_Status_Completed"));

			fnpMymsg("  Completed the pending Action item for ClientQuoteReview ");
			// ***************Complete the pending
			// Actionitem(ClientQuoteReview)***********

			fnpCommonClickTaskTab();
			
			int taskTechnicalReviewRowNo=fnpFindTechnicalReviewOfNonLinkedWOInCustomWO();
			
			int taskStatusColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_StatusColName);

			String taskStatus = fnpFetchFromTable("TasksTable_EditWO", taskTechnicalReviewRowNo, taskStatusColIndex);
			Assert.assertTrue(taskStatus.equalsIgnoreCase("READY"), "Task 'Technical Review' status  is  not become 'READY' status.");

			fnpCheckError(" while Going to Complete the pending Action item for ClientQuoteReview");

		} catch (Throwable t) {
			fail = true;
			isTestPass = false;
			fnpMymsg("  Client_App_Review_Task  method is failed . " + " . Error is ---" + t.getMessage());
			fnpDesireScreenshot("ClientAppReviewTaskFailed");

			String stackTrace = Throwables.getStackTraceAsString(t);
			String errorMsg = t.getMessage();
			errorMsg = errorMsg + "\n\n\n\n Client_App_Review_Task  method is failed   .See screenshot 'ClientAppReviewTaskFailed'\n\n" + stackTrace;
			Exception c = new Exception(errorMsg);
			ErrorUtil.addVerificationFailure(c);

		}
	}


	@Test(priority = 11, dependsOnMethods = { "Custom_Client_App_Review_Task" })
	public void Manually_SupplierFormsReady() throws Throwable {

		fnpMymsg(" **************************************************************");
		fnpMymsg(" Executing Manually_SupplierFormsReady");
		try {

			fnpWaitForVisible("EditProductTabLink");
			fnpWaitTillClickable("EditProductTabLink");
			fnpWaitTillVisiblityOfElementNClickable_OR("EditProductTabLink");
			fnpGetORObjectX("EditProductTabLink").click();
			fnpLoading_wait();

			fnpWaitForVisible("ProductTab_ProductInformationHeading");

			if (fnpCheckElementPresenceImmediately("EditWOBtn")) {
				fnpGetORObjectX("EditWOBtn").click();
				fnpLoading_wait();
			}

			fnpClick_OR("ProductTab_RequestFollowUpBtn");

			fnpCheckError(" in Manually_SupplierFormsReady  method");

			fnpMymsg("Request the Follow up Form by clicking button on Product tab");

			fnpCommonClickTaskTab();

			int taskStatusColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_StatusColName);
			int taskTypeColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskTypeColName);
			int taskSupplierFormsRowNo = fnpFindRow("TasksTable_EditWO", taskType_SupplierForms, taskTypeColIndex);

			String taskStatus = fnpFetchFromTable("TasksTable_EditWO", taskSupplierFormsRowNo, taskStatusColIndex);
			Assert.assertTrue(taskStatus.equalsIgnoreCase("READY"), "Task 'Supplier Forms' status  is  not become 'READY' status.");
			fnpMymsg("Task 'Supplier Forms' status  is  not become 'READY' status.");

			fnpMymsg(" After completed the Manual task of Supplier Forms become Ready");

			fnpCheckError(" in Manually_SupplierFormsReady  method");

		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "  Manually_SupplierFormsReady  method is failed . ", "Manually_SupplierFormsReadyFailed");

		}
	}

	@Test(priority = 12, dependsOnMethods = { "Manually_SupplierFormsReady" })
	public void Custom_WO_Manually_Status_Change_To_InProcess() throws Throwable {

		fnpMymsg(" **************************************************************");
		fnpMymsg(" Executing Custom_WO_Manually_Status_Change_To_InProcess");

		try {

			fnpCommonClickInfoTab();

			if (fnpCheckElementPresenceImmediately("EditWOBtn")) {
				fnpGetORObjectX("EditWOBtn").click();
				fnpLoading_wait();
			}

			fnpWaitForVisible("InfoTab_WOStatusPFList");

			String status = "InProcess";

			fnpPFList("InfoTab_WOStatusPFList", "InfoTab_WOStatusPFListOptions", status);

			fnpClick_OR("ProAddDocSaveBtn");

			fnpCheckError(" while changing status from  InReview to InProcess in Info tab");

			// fnpWaitForVisible("TopMessage3");
			fnpWaitForVisibleHavingMultipleElements("TopMessage3");
			String SuccessfulMsg = fnpGetText_OR("TopMessage3");
			fnpMymsg("Top Message after changing status from InReview to InProcess in Info tab ----" + SuccessfulMsg);

			Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"),
					"Top message does not contain 'success' word, so might be changing status from InReview to InProcess in Info tab is NOT successful.");

			fnpWaitTillClickable("NewlyCrWOTopBannerInfo");
			fnpGetORObjectX("NewlyCrWOTopBannerInfo").click();

			fnpWaitForVisible("TopBannerWOStatus");
			String changedWOStatus = fnpGetORObjectX("TopBannerWOStatus").getText();
			Assert.assertEquals(changedWOStatus, "INPROCESS", " WO status is not changed from InReview to InProcess.");
			fnpMymsg("Now  WO status has been changed from InReview to InProcess status.");

		}

		catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "  Custom_WO_Manually_Status_Change_To_InProcess  method is failed . ", "Custom_WO_Manually_Status_Change_To_InProcessFailed");

		}

	}

	@Test(priority = 13, dependsOnMethods = { "Custom_WO_Manually_Status_Change_To_InProcess" })
	public void Custom_WO_Alerts_Verify() throws Throwable {

		fnpMymsg(" **************************************************************");
		fnpMymsg(" Executing Custom_WO_Alerts_Verify");

		try {

			fnpCommonGoToHomeNClick();

			// -------Alert Work Order in InProcess status -------------------
			fnpCommonAlertGeneratedVerification("Work_order_in_INPROCESS_status", "WO_InProcess_Status_AlertTable_header", "WO #", "WO_InProcess_Status_AlertTable",
					"WO_InProcess_Status_Alert_WO_filterTxtBox", workOrderNo);
			// ---------------------------------------------

			// -------Alert Task(s) with no open Action Items
			// -------------------
			fnpCommonAlertGeneratedVerification("Task_with_no_open_Action_Items", "Task_with_no_open_Action_Items_AlertTable_header", "WO #",
					"Task_with_no_open_Action_Items_AlertTable", "Task_with_no_open_Action_Items_Alert_WO_filterTxtBox", workOrderNo);
			// ---------------------------------------------

			// -------Alert Quote(s) accepted by clients in the past 5 days
			// -------------------
			fnpCommonAlertGeneratedVerification("Quote_accepted_by_clients_in_the_past_5_days", "Quote_accepted_by_clients_in_the_past_5_days_AlertTable_header", "WO #",
					"Quote_accepted_by_clients_in_the_past_5_days_AlertTable", "Quote_accepted_by_clients_in_the_past_5_days_Alert_WO_filterTxtBox", workOrderNo);
			// ---------------------------------------------

			// -------Alert Custom/Resolution Work Orders with completed tasks
			// not yet invoiced-------------------
			fnpCommonAlertGeneratedVerification("Custom_Resolution_Work_Orders_with_completed_tasks_not_yet_invoiced",
					"Custom_Resolution_Work_Orders_with_completed_tasks_not_yet_invoiced_AlertTable_header", "WO #",
					"Custom_Resolution_Work_Orders_with_completed_tasks_not_yet_invoiced_AlertTable",
					"Custom_Resolution_Work_Orders_with_completed_tasks_not_yet_invoiced_Alert_WO_filterTxtBox", workOrderNo);
			// ---------------------------------------------

		}

		catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "  Custom_WO_Alerts_Verify  method is failed . ", "Custom_WO_Alerts_VerifyFailed");
		}

	}

	@Test(priority = 14, dependsOnMethods = { "Custom_WO_Alerts_Verify" })
	public void Supplier_Forms_task() throws Throwable {

		BS05.Supplier_Forms_task();

	}

	
/*	@Test(enabled = false)
	public void Certification_Services_task() throws Throwable {

		fnpMymsg(" **************************************************************");
		fnpMymsg(" Executing Certification_Services_task");
		try {

			// ***************Certification_Services_task***********

			fnpMymsg(" Going to click Certification_Services_task  no. ");

			// fnpCommonClickTaskTab();

			int TaskNoColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskNoColName);
			int taskTypeColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskTypeColName);
			int CertificationServicesRowNo = fnpFindRow("TasksTable_EditWO", taskType_CertificationServices, taskTypeColIndex);

			String CertificationServicesTaskNo = fnpFetchFromTable("TasksTable_EditWO", CertificationServicesRowNo, TaskNoColIndex);
			Thread.sleep(500);
			fnpWaitTillVisiblityOfElementNClickable(".//a[text()='" + CertificationServicesTaskNo + "']");
			driver.findElement(By.linkText(CertificationServicesTaskNo)).click();

			fnpMymsg("Clicked on Certification_Services_task no. in Task Table i.e. '" + CertificationServicesTaskNo + "' .");
			fnpClick_OR("TaskTab_ScopeValidation_AssignTaskIcon");

			//fnpClickInDialog_OR("TaskTab_ScopeValidation_AssignTaskToUserLKPBtn");

			//fnpWaitTillVisiblityOfElementNClickable_OR("LookkupSecondTextBox");
			//fnpSearchNSelectFirstRadioBtn(2, (String) hashXlData.get("CertificationServices_AssignTaskToUser"), 2);

			fnpClickInDialog_OR("TaskTab_ScopeValidation_AssignTask_SaveBtn");

			fnpCheckError(" while Assigning the task in Certification_Services_task  method ");

			fnpClick_OR("TaskTab_ScopeValidation_PerformTaskIcon");
			fnpCheckErrMsg("Error is thrown by application after clicking PerfromTaskIcon");

			fnpClick_OR("TaskTab_ScopeValidation_CompleteTaskIcon");

			fnpCheckErrMsg("Error is thrown by application after clicking CompleteTaskIcon");

			fnpClickInDialog_OR("TaskTab_ScopeValidation_CompleteTaskSaveBtn");

			fnpCheckError("in Certification_Services_task  method while completing the task ");

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
			Thread.sleep(1000);
			fnpClick_OR("TaskTab_ScopeValidation_BackBtn");

			fnpWaitForElementVisibility("TasksTable_EditWO", CONFIG.getProperty("genMax_waitTime"));
			int StatusColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_StatusColName);
			taskTypeColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskTypeColName);
			CertificationServicesRowNo = fnpFindRow("TasksTable_EditWO", taskType_CertificationServices, taskTypeColIndex);
			String CertificationServicesStatus_TaskTable = fnpFetchFromTable("TasksTable_EditWO", CertificationServicesRowNo, StatusColIndex);

			Assert.assertTrue(CertificationServicesStatus_TaskTable.toLowerCase().contains("completed"),
					"In Task Table , status has not been changed  to 'COMPLETED' . It is still showing '" + CertificationServicesStatus_TaskTable + "'.");

			fnpMymsg(" After completed the Certification_Services_task , status in Task Table has  been changed  to 'COMPLETED' successfully.");

			fnpCheckError("in Certification_Services_task  method ");

		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "  Certification_Services_task  method is failed . ", "Certification_Services_taskFailed");

		}
	}

	
	*/
	
	
	
	
	
	

	@Test(priority = 15, dependsOnMethods = { "Supplier_Forms_task" })
	public void Technical_Review_Task() {

		fnpMymsg(" **************************************************************");
		fnpMymsg(" Executing Technical_Review_Task");
		try {

			// ***************Technical_Review_Task***********

			fnpMymsg(" Going to click Technical_Review_Task  no. ");

			int TaskNoColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskNoColName);
			int taskTypeColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskTypeColName);
			//int technicalReviewRowNo = fnpFindRow("TasksTable_EditWO", taskType_TechnicalReview, taskTypeColIndex);
			
			int technicalReviewRowNo=fnpFindTechnicalReviewOfNonLinkedWOInCustomWO();

			String technicalReviewTaskNo = fnpFetchFromTable("TasksTable_EditWO", technicalReviewRowNo, TaskNoColIndex);
			Thread.sleep(500);
			
			
/*			fnpWaitTillVisiblityOfElementNClickable_NOR(".//a[text()='" + technicalReviewTaskNo + "']");
			driver.findElement(By.linkText(technicalReviewTaskNo)).click();
			*/
			fnpClickALinkInATable(technicalReviewTaskNo);
			fnpLoading_wait();

			// fnpLoading_wait();
			// Thread.sleep(4000);//commented on 1 may
			fnpMymsg("Clicked on Technical_Review_Task no. in Task Table i.e. '" + technicalReviewTaskNo + "' .");
			fnpClick_OR("TaskTab_ScopeValidation_AssignTaskIcon");

		//	fnpClickInDialog_OR("TaskTab_ScopeValidation_AssignTaskToUserLKPBtn");

		//	fnpWaitTillVisiblityOfElementNClickable_OR("LookkupSecondTextBox");
		//	fnpSearchNSelectFirstRadioBtn(2, (String) hashXlData.get("TechnicalReview_AssignTaskToUser"), 2);

			fnpClickInDialog_OR("TaskTab_ScopeValidation_AssignTask_SaveBtn");

			// fnpLoading_wait();
			Thread.sleep(4000);

			fnpCheckError("in Technical_Review_Task  method while Assigning the task");

			fnpClick_OR("TaskTab_ScopeValidation_PerformTaskIcon");

			
			
			
			/*****************New changes for sprint 9.2******************************/
			
/*			
			String annualUpdateRequireOptions = (String) hashXlData.get("Annual_Update_Required").trim();
			String annualUpdateRequireOptionsArray[] = annualUpdateRequireOptions.split(":");
			int annualUpdateRequireOptionsArraySize = annualUpdateRequireOptionsArray.length;
			String chkBxLabelXpath;
			String chkBxXpath;
			String checkedStatusClass;
			for (int i = 0; i < annualUpdateRequireOptionsArraySize; i++) {
				chkBxLabelXpath = OR.getProperty("AnnualUpdateRequiredChkBxLabel_part1") + annualUpdateRequireOptionsArray[i]
						+ OR.getProperty("AnnualUpdateRequiredChkBxLabel_part2");
								
				chkBxXpath = chkBxLabelXpath + "/../../td[1]/div/div[2]";
				checkedStatusClass = driver.findElement(By.xpath(chkBxXpath)).getAttribute("class");
				if (!(checkedStatusClass.contains("ui-state-active"))) {
					driver.findElement(By.xpath(chkBxLabelXpath)).click();
					Thread.sleep(2000);
				}
		

			}
			*/
			
			
			fnpAnnualUpdateRequiredChkbxesUsingInReviewTask();
			/*****************New changes for sprint 9.2******************************/
			
			
			fnpClick_OR("TaskTab_ScopeValidation_CompleteTaskIcon");

			fnpClickInDialog_OR("TaskTab_ScopeValidation_CompleteTaskSaveBtn");

			fnpCheckError(" in Technical_Review_Task  method while completing the task");

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

			fnpWaitForElementVisibility("TasksTable_EditWO", CONFIG.getProperty("genMax_waitTime"));
			int StatusColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_StatusColName);
			taskTypeColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskTypeColName);
			//technicalReviewRowNo = fnpFindRow("TasksTable_EditWO", taskType_TechnicalReview, taskTypeColIndex);
			technicalReviewRowNo=fnpFindTechnicalReviewOfNonLinkedWOInCustomWO();
			String technicalReviewStatus_TaskTable = fnpFetchFromTable("TasksTable_EditWO", technicalReviewRowNo, StatusColIndex);

			Assert.assertTrue(technicalReviewStatus_TaskTable.toLowerCase().contains("completed"),
					"In Task Table , status has not been changed  to 'COMPLETED' . It is still showing '" + technicalReviewStatus_TaskTable + "'.");

			fnpMymsg(" After completed the Technical_Review_Task , status in Task Table has  been changed  to 'COMPLETED' successfully.");

			fnpCheckError(" in Technical_Review_Task  method");

		} catch (Throwable t) {
			fail = true;
			isTestPass = false;
			fnpMymsg("  Technical_Review_Task  method is failed . " + " . Error is ---" + t.getMessage());
			fnpDesireScreenshot("Technical_Review_TaskFailed");
			String stackTrace = Throwables.getStackTraceAsString(t);
			String errorMsg = t.getMessage();
			errorMsg = errorMsg + "\n\n\n\n Technical_Review_Task  method is failed  .See screenshot 'Technical_Review_TaskFailed'\n\n" + stackTrace;
			Exception c = new Exception(errorMsg);
			ErrorUtil.addVerificationFailure(c);

		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Test(priority = 16, dependsOnMethods = { "Technical_Review_Task" })
	public void Custom_WO_Certification_Decision_Task() throws Throwable {
		fnpMymsg(" **************************************************************");
		fnpMymsg(" Executing Custom_WO_Certification_Decision_Task");

		try {

			fnpWaitForVisible("Task_ShowAllLink");

			int TaskTypeColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskTypeColName);
			int TaskStatusColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_StatusColName);
			int RowNoForCertDecision = fnpFindRow("TasksTable_EditWO", taskType_CertDecision, TaskTypeColIndex);

			TaskTypeColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskTypeColName);

			String certDecisionStatus = fnpFetchFromTable("TasksTable_EditWO", RowNoForCertDecision, TaskStatusColIndex);
			fnpMymsg("certDecisionStatus ----" + certDecisionStatus);

/*			Assert.assertTrue(certDecisionStatus.equalsIgnoreCase("ready"), "Cert decision status should be in 'Ready' state but "
					+ "it is not in ready state now (here we are waiting for scheduler )");
*/
			Assert.assertTrue(certDecisionStatus.equalsIgnoreCase("ready"), "Cert decision status should be in 'Ready' state but "
					+ "it is not in ready state now.");
			
			
			
			
			
			
		/********************New changes for sprint 9.2***********************************/
			

			fnpCommonClickSnapShotTab();

			fnpWaitForVisible("ActionItemsTopLabel");
			fnpMymsg(" Going to verify DocumentFinal action item should be generated in Pending status. ");
			// fnpWaitForVisible("ActionItemTable_EditWO");
			int ActionItemNoColIndex = fnpFindColumnIndex("ActionItemTable_HeaderRow", actionItemTableStatusColName);
			int AItem_ItemDesc_ColIndex = fnpFindColumnIndex("ActionItemTable_HeaderRow", actionItemTableItemDescColName);
			int AI_DocumentFinalRowNo = fnpFindRow("ActionItemTable_EditWO", actionItemDesc_DocumentFinal, AItem_ItemDesc_ColIndex);
			
			
			Assert.assertTrue(AI_DocumentFinalRowNo > 0, "Action Item 'DocumentFinal' has not been generated.  ");
			fnpMymsg("Action Item 'DocumentFinal' has  been generated successfully.  ");
			
			String documentFinalStatusinActionItemTable = fnpFetchFromTable("ActionItemTable_EditWO", AI_DocumentFinalRowNo, ActionItemNoColIndex);

			Assert.assertTrue(documentFinalStatusinActionItemTable.toLowerCase().contains("pending"),
					"After Cert Decision task become in Ready status, DocumentFinal action item should be generated in Pending status. But it is not as It is still showing '"
							+ documentFinalStatusinActionItemTable + "'.");

			fnpMymsg( "After Cert Decision task become in Ready status, DocumentFinal action item should be generated in Pending status successfully. ");
			String documentFinalStatusChanged="COMPLETED";
			fnpProcessAI(actionItemDesc_DocumentFinal, documentFinalStatusChanged);


			
			int certDecUpdateRowNo = fnpFindRow("ActionItemTable_EditWO", actionItemDesc_CertDecUpdate, AItem_ItemDesc_ColIndex);			
			Assert.assertTrue(certDecUpdateRowNo > 0, "Action Item 'CertDecUpdate' has not been generated.  ");
			fnpMymsg("Action Item 'CertDecUpdate' has  been generated successfully.  ");

			
			String certDecuUpdateStatusChanged="SUBMITTED";
			fnpProcessAI(actionItemDesc_CertDecUpdate, certDecuUpdateStatusChanged);
						
			certDecuUpdateStatusChanged="COMPLETED";
			fnpProcessAI(actionItemDesc_CertDecUpdate, certDecuUpdateStatusChanged);
			
			/*****************************************************/
			
			
			
			
			
			
			
			
			
			
			/******************New changes due to new field cert decider ***************************/
						
/*			driver.close();
		//	fnpLaunchBrowserAndSecureLogin("srisley");	
			fnpLaunchBrowserAndSecureLogin( (String) hashXlData.get("SecureLoginUserNameForCertTask"));
		*/
			
			
			fnpCommonClickTopMainMenu("Menu", "FirstRefrenceLinkJustBelowToMenu", "SwitchUser_Link", "SwitchUser_Go_btn");		 
			fnpType("OR","SwitchUser_txtBx",(String) hashXlData.get("SecureLoginUserNameForCertTask"));
			fnpLoading_wait();
			fnpIpulseDuringLoading();
			 
			String Ajax_UserName_Xpath = "//li[@data-item-label='"+(String) hashXlData.get("SecureLoginUserNameForCertTask").toUpperCase().trim()+"']";
			fnpCheckElementEnabledImmediately_NOR(Ajax_UserName_Xpath);
			Thread.sleep(1000);
			fnpClick_NOR(Ajax_UserName_Xpath);
			fnpLoading_wait();
			fnpIpulseDuringLoading();
			fnpClick_OR("SwitchUser_Go_btn");
			
			fnpSearchWorkOrderOnly(workOrderNo);			
			fnpWaitTillClickable("EditTaskTabLink");
			fnpGetORObjectX("EditTaskTabLink").click();
			fnpLoading_wait();
			fnpWaitForVisible("Task_ShowAllLink");
			
			
			/****************************************************************** ***************************/
			
			

			
			
			
			
			
			fnpMymsg(" Going to click Cert Decision task no. ");
			int ColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskNoColName);
			String CertDecisionTaskNo = fnpFetchFromTable("TasksTable_EditWO", RowNoForCertDecision, ColIndex);
			Thread.sleep(500);
			
			
/*			fnpWaitTillVisiblityOfElementNClickable_NOR(".//a[text()='" + CertDecisionTaskNo + "']");
			driver.findElement(By.linkText(CertDecisionTaskNo)).click();
			*/
			
			fnpClickALinkInATable(CertDecisionTaskNo);
			fnpLoading_wait();
			fnpMymsg("Clicked on Cert Decision task no. in Task Table i.e. '" + CertDecisionTaskNo + "' .");

			fnpClick_OR("TaskTab_CertDeci_AssignTaskIcon");

		//	fnpClickInDialog_OR("TaskTab_CertDeci_AssignTaskToUserLKPBtn");

		//	fnpWaitTillVisiblityOfElementNClickable_OR("LookkupSecondTextBox");
			
		//	fnpSearchNSelectFirstRadioBtn(2, (String) hashXlData.get("CertDeci_AssignTaskToUser"), 2);
		//	fnpSearchNSelectFirstRadioBtn(2, (String) hashXlData.get("Cert_Decider"), 2);

			fnpClickInDialog_OR("TaskTab_ScopeValidation_AssignTask_SaveBtn");

			fnpCheckError(" while Assigning the task in Custom_WO_Certification_Decision_Task  method  ");
			fnpClick_OR("TaskTab_CertDeci_PerformTaskIcon");

			fnpCheckErrMsg("Error is thrown by application after click on perform task icon. ");

			fnpWaitForVisible("CertDeci_DecisionPFList");
			fnpPFList("CertDeci_DecisionPFList", "CertDeci_DecisionPFListOptions", (String) hashXlData.get("Decision_Option"));

			Thread.sleep(2000);

			if (fnpCheckElementPresenceImmediately("EditWOBtnTaskInside")) {
				fnpClick_OR("EditWOBtnTaskInside");

			}
			fnpClick_OR("CertDeci_TaskDetails_SaveBtn");
			fnpCheckErrMsg("Error is thrown by application after click on Save button in Cert Decision Task details page. ");
			
			
			
			
			// fnpLoading_wait();
			
			/************Commented below lines due to new changes according to new filed cert decider ********************/
			
/*			fnpClick_OR("TaskTab_ScopeValidation_BackToViewBtn");
			Thread.sleep(1000);
			fnpClick_OR("TaskTab_ScopeValidation_BackBtn");
			*/
			/****************** ***************************/
			
			
			
			
			
			

			
			
			fnpClick_OR("TaskTab_ScopeValidation_BackToViewBtn");
			Thread.sleep(1000);
			fnpClick_OR("TaskTab_ScopeValidation_BackBtn");
			
			

			if (!fnpCheckElementPresenceImmediately("TasksTable_EditWO")) {
				fnpCommonClickTaskTab();

			}

			fnpWaitForElementVisibility("TasksTable_EditWO", CONFIG.getProperty("genMax_waitTime"));
			int StatusColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_StatusColName);
			int taskTypeColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskTypeColName);
			RowNoForCertDecision = fnpFindRow("TasksTable_EditWO", taskType_CertDecision, taskTypeColIndex);
			String certDeciStatus_TaskTable = fnpFetchFromTable("TasksTable_EditWO", RowNoForCertDecision, StatusColIndex);

			Assert.assertTrue(certDeciStatus_TaskTable.toLowerCase().contains("inperform"), "In Task Table , status has not been changed  to 'INPERFORM' . It is still showing '"
					+ certDeciStatus_TaskTable + "'.");

			fnpMymsg(" After assigning and perform , status in Task Table has  been changed  to 'INPERFORM' successfully.");

			fnpCheckError("  in Custom_WO_Certification_Decision_Task method  ");

			
			
			

			fnpCommonClickSnapShotTab();

			fnpWaitForVisible("ActionItemTable_EditWO");

			int ActionItemColIndex = fnpFindColumnIndex("ActionItemTable_HeaderRow", actionItemTableActionItemColName);
			int itemDescColIndex = fnpFindColumnIndex("ActionItemTable_HeaderRow", actionItemTableItemDescColName);
			int ListingUpdateRowNo = fnpFindRow("ActionItemTable_EditWO", actionItemDesc_ListingUpdate, itemDescColIndex);

			Assert.assertTrue(ListingUpdateRowNo > 0, "Action Item 'ListingUpdate' has not been generated.  ");
			fnpMymsg("Action Item 'ListingUpdate' has  been generated successfully.  ");
			
			
			//if ((currentSuiteName.equalsIgnoreCase(Diatory_Supplement_suite_Name)) ){	

				int AItem_ItemAssignedTo_ColIndex = fnpFindColumnIndex("ActionItemTable_HeaderRow", actionItemTableAssignedToColName);
				String listingUpdateAIAssigner = fnpFetchFromTable("ActionItemTable_EditWO", ListingUpdateRowNo, AItem_ItemAssignedTo_ColIndex);
				if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
					Assert.assertTrue(listingUpdateAIAssigner.contains((String) hashXlData.get("Assign_ListingUpdate_Code")), actionItemDesc_ListingUpdate+" AI is not assigned to Assign_ListingUpdate_Code (Marie Zueski) just after generated --i.e. '" + (String) hashXlData.get("Assign_ListingUpdate_Code").trim() + "'");
				}else{
					Assert.assertEquals(listingUpdateAIAssigner.trim(), (String) hashXlData.get("Assign_ListingUpdate").trim(),  actionItemDesc_ListingUpdate+" AI  is not assigned to Assign_ListingUpdate  (Marie Zueski)  just after generated -- i.e. '" + (String) hashXlData.get("Assign_ListingUpdate").trim() + "'");
				}
				fnpMymsg(actionItemDesc_ListingUpdate+" AI  is  assigned to Assign_ListingUpdate  (Marie S.Zueski)  just after generated -- i.e. '" + (String) hashXlData.get("Assign_ListingUpdate").trim() + "' successfully.");

			
			
			
			/******************New changes due to new field cert decider ***************************/
			
/*			driver.close();
			fnpLaunchBrowserAndLogin();	
*/			
				
				
/*			
			fnpCommonClickTopMainMenu("Menu", "FirstRefrenceLinkJustBelowToMenu", "SwitchUser_Link", "SwitchUser_Go_btn");		 
			fnpType("OR","SwitchUser_txtBx",(String) hashXlData.get("SecureLoginUserNameForCertTask"));
			fnpClick_OR("SwitchUser_Go_btn");
			*/
			fnpLaunchBrowserAndLogin();
			
			
			fnpSearchWorkOrderOnly(workOrderNo);
			
			/****************** ***************************/
			
			
			
			fnpProcessAI(actionItemDesc_ListingUpdate, (String) hashXlData.get("Change_StatusListingUpdate_Completed"));

			 ActionItemColIndex = fnpFindColumnIndex("ActionItemTable_HeaderRow", actionItemTableActionItemColName);
			 itemDescColIndex = fnpFindColumnIndex("ActionItemTable_HeaderRow", actionItemTableItemDescColName);
			int certDeciEduRowNo = fnpFindRow("ActionItemTable_EditWO", actionItemDesc_Certdecisioneducation, itemDescColIndex);

			Assert.assertTrue(certDeciEduRowNo > 0, "Action Item 'Cert decision education' has not been generated.  ");
			fnpMymsg("Action Item 'Cert decision education' has  been generated successfully.  ");

			
			
/*			
			*//**************
			 * In new coming sprint R4.1, today 16-3-16 before releasing , on
			 * trunk this action item is not being generated and if comment this
			 * then wo getting completed successfully also
			 *****************//*

			// int annualSetupRowNo =
			// fnpFindRow("ActionItemTable_EditWO",actionItemDesc_AnnualSetup,
			// itemDescColIndex);
			// Assert.assertTrue(annualSetupRowNo > 0,
			// "Action Item 'AnnualSetup' has not been generated.  ");
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

			TaskTypeColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskTypeColName);
			TaskStatusColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_StatusColName);
			RowNoForCertDecision = fnpFindRow("TasksTable_EditWO", taskType_CertDecision, TaskTypeColIndex);

			certDecisionStatus = fnpFetchFromTable("TasksTable_EditWO", RowNoForCertDecision, TaskStatusColIndex);

			Assert.assertTrue(certDecisionStatus.toLowerCase().contains("completed"),
					"After completed the Cert Decision Task after assign,perform,complete , status has not been changed  to 'COMPLETED' . It is still showing '"
							+ certDecisionStatus + "'.");

			fnpMymsg(" After completed the Cert Decision Task after assign,perform,complete , status has  been changed  to 'COMPLETED' successfully.");

			fnpProcessAI(actionItemDesc_Certdecisioneducation, (String) hashXlData.get("Change_Status_Completed"));

			fnpMymsg(" Completed successfully  pending Action item for Cert Decision Education ");

			
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
			
			
			
			fnpCommonClickFinancialTab();

			if (fnpCheckElementPresenceImmediately("EditWOBtn")) {
				fnpGetORObjectX("EditWOBtn").click();
				fnpLoading_wait();
			}

			fnpWaitForVisible("FinancialTab_EditWO_InvoiceBillToPFList");

			fnpClick_OR("Facility_InvoiceCompleteTaskBtn");

			fnpClickInDialog_OR("Facility_InvoiceCompleteTask_YesBtn");

			fnpMymsg("  Completed the Certification_Decision_Task Task successfully ");

			fnpCheckError("   while Going to Complete Custom_WO_Certification_Decision_Task  ");

		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "  Custom_WO_Certification_Decision_Task  method is failed . ", "Custom_WO_Certification_Decision_Task");

		}

	}

	@Test(priority = 17, dependsOnMethods = { "Custom_WO_Certification_Decision_Task" })
	public void Custom_WO_INPROCESS_to_COMPLETE() throws Throwable {
		fnpMymsg(" **************************************************************");
		fnpMymsg(" Executing Custom_WO_INPROCESS_to_COMPLETE");
		try {
			if (((String) hashXlData.get("Check_Batch_Job")).equalsIgnoreCase("Yes")) {

				int waitCount = 0;
				String changedWOStatus;
				while (true) {
					waitCount++;
					fnpWaitForVisible("NewlyCrWOTopBannerInfo");
					fnpGetORObjectX("NewlyCrWOTopBannerInfo").click();
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

						fnpMymsg("Script waited for approx. 100 min to become 'COMPLETE' state of WO status but it is still showing '" + changedWOStatus + "' .");
						throw new Exception("Script waited for approx. 100 min to become 'COMPLETE' state of WO status but it is still showing '" + changedWOStatus + "' .");

					}

					fnpCheckError("   while Going to Complete Custom_WO_INPROCESS_to_COMPLETE and refreshing again n again. ");

				}

				fnpMymsg("  Completed the Custom_WO_INPROCESS_to_COMPLETE Task successfully ");

				// ------------------------------------------------------------------------------------------------------

			}

			else {

				// fnpLoading_wait();
				fnpWaitTillClickable("InfoTab_EditWO");
				fnpGetORObjectX("InfoTab_EditWO").click();
				fnpLoading_wait();
				fnpWaitForVisible("InfoTab_WOStatusPFList");

				String status = "Complete";

				fnpPFList("InfoTab_WOStatusPFList", "InfoTab_WOStatusPFListOptions", status);

				fnpClick_OR("ProAddDocSaveBtn");

				fnpCheckError("   while  changing status from InProcess to Complete in Info tab. ");

				// fnpWaitForVisible("TopMessage3");
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

				fnpCheckError("   while  Going to Complete Custom_WO_INPROCESS_to_COMPLETE");

			}
			
			
			
		}

		catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "  Custom_WO_INPROCESS_to_COMPLETE  method is failed . ", "Custom_WO_INPROCESS_to_COMPLETE");

		}

	}

	
	//@Test(priority = 18, dependsOnMethods = { "Custom_WO_INPROCESS_to_COMPLETE" })
	@Test(enabled = false)
	public void App_Memory_StackTrace() throws Throwable {
		fnpMymsg(" **************************************************************");
		fnpMymsg(" Executing App_Memory_StackTrace");
		try {

			fnpCommonStackTracePageOpen();
			String screenshotImageName = fnpTimestamp() + "   " + fnpMappingClassNameWithScenarioCode(this.getClass().getSimpleName());
			screenshotImageName = screenshotImageName.replaceAll(":", "_");
			String folderPath = screenshots_path + "//screenshots_WO_ApplicationMemoryStackTrace//";
			fnpDesireScreenshot_Advance(screenshotImageName, folderPath);

		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "  App_Memory_StackTrace  method is failed . ", "Failed_App_Memory_StackTrace_Failed");
		}

	}
	
	
	//@Test(priority = 19, dependsOnMethods = { "App_Memory_StackTrace" })
	@Test(priority = 19, dependsOnMethods = { "Custom_WO_INPROCESS_to_COMPLETE" })
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
