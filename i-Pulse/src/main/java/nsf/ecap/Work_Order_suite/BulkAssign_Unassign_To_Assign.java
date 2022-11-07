package nsf.ecap.Work_Order_suite;

import java.util.List;

import nsf.ecap.util.ErrorUtil;
import nsf.ecap.util.TestUtil;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.os.WindowsUtils;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;

public class BulkAssign_Unassign_To_Assign extends TestSuiteBase {

	@BeforeTest
	public void checkTestSkip() throws Throwable {
		BS01 = new NewNew_Draft_InReview_No_Fac();
		BS01.checkTestSkip(this.getClass().getSimpleName());
	}

	@Test
	public void BulkAssignment_Flow() {
		try {

			fnpCommonLoginPart(classNameText);

			String vWLPlanner = (String) hashXlData.get("Display_items_for_WorkLoad_Planner");
			String vShowRadioButton = (String) hashXlData.get("Show_radiobutton");
			String vTaskResults = (String) hashXlData.get("Task_Result_table");
			String vActionItemResults = (String) hashXlData.get("Action_Item_Result");
			String vFindingResults = (String) hashXlData.get("Finding_Results");
			String vAlreadyAssignedEmpCode;

			fnpWaitForVisible("WorkLoadPlannerList");

			String defaultWorkLoadPlannerValue = fnpGetORObjectX("WorkLoadPlannerList").getText();

			if (!defaultWorkLoadPlannerValue.equalsIgnoreCase(vWLPlanner)) {

				fnpPFList("WorkLoadPlannerList", "WorkLoadPlannerListOptions", vWLPlanner);

				Thread.sleep(2000);
			}

			String xpathShowRadioBtn = ".//*[@id='mainForm:optionButton']/tbody/tr/td/label[text()='" + vShowRadioButton + "']";
			driver.findElement(By.xpath(xpathShowRadioBtn)).click();

			if (!vShowRadioButton.isEmpty()) {

				if (vShowRadioButton.equalsIgnoreCase("Unassigned Items")) {
					fnpMymsg(" In this case first  we are going to search for  Unassigned items means blank search .     ");
					fnpGetORObjectX("BulkAssing_SearchBtn").click();
					// Thread.sleep(2000);
					fnpLoading_wait();
					// fnpLoading_wait();

				} else {
					vAlreadyAssignedEmpCode = (String) hashXlData.get("Already_assigned_EmpCode");

					fnpMymsg(" In this case first  we are going to search for Already assigned items for this employee '" + vAlreadyAssignedEmpCode + "'   .  ");

					fnpClick_OR("ItemAssignedTo_lookup");

					fnpSearchNSelectFirstRadioBtn(1, vAlreadyAssignedEmpCode, 2);

					fnpClick_OR("BulkAssing_SearchBtn");

				}

			} else {
				throw new Exception(" 'Show_radiobutton' column does 'NOT have any value . Please provide value as it is must .");
			}

			fnpMymsg("");
			fnpMymsg("");

			// ****************Task Results Table code
			String taskNo = null;
			int TaskColNo = 0;
			int taskTableFlag = 0; // 0 for empty and have data then 1
			if (vTaskResults.equalsIgnoreCase("Yes")) {
				fnpMymsg("Value for  selecting task from Task Results table in Excel sheet is ---Yes");

				int taskTableRows = fnpCountRowsInTable("BulkAssignment_TaskTable");
				if (taskTableRows > 0) {
					taskTableFlag = 1;
					fnpMymsg("Now we are going  to select record from Task Results table as table is not empty and visible data rows are --" + taskTableRows);
					String firstvalue = fnpFetchFromTable("BulkAssignment_TaskTable", 1, 1);
					if (firstvalue.contains("No") && firstvalue.contains("ound")) {

						fnpMymsg("    Task Results table is blank ( do 'NOT have any record) .      ");
						throw new Exception("    Task Results table is blank ( do 'NOT have any record) .      ");

					}

					int rownoToSelect = 1;
					String xpathOfCheckbox = OR.getProperty("BulkAssignment_TaskTable") + "/tr[" + rownoToSelect + "]/td[1]/div/div[2]";
					driver.findElement(By.xpath(xpathOfCheckbox)).click();
					Thread.sleep(1000);

					/*********
					 * hours text box is no longer come after sprint R2.1
					 * (IPM-2240)
					 ****/
					/*
					 * int inputBoxrowcount = rownoToSelect - 1; String
					 * inputXpath = "//input[contains(@id,'mainForm:taskTable:"
					 * + inputBoxrowcount + ":')]";
					 * 
					 * fnpWaitForVisible_NotInOR(inputXpath);
					 * driver.findElement(By.xpath(inputXpath)).clear();
					 * Thread.sleep(1000); fnpType(null, inputXpath, (String)
					 * hashXlData.get("Task_Est_hr")); Thread.sleep(1000);
					 */

					TaskColNo = fnpFindColumnIndex("BulkAssignment_TaskTableHeader", "Task");
					taskNo = fnpFetchFromTable("BulkAssignment_TaskTable", rownoToSelect, TaskColNo);
					fnpMymsg("We have picked row no. '" + rownoToSelect + "' and its Task no. is '" + taskNo + "' .");
					fnpMymsg("");
					fnpMymsg("");
					fnpMymsg("");

				} else {
					fnpMymsg("Data rows are not present in Task Results  table , so unable to pick the task ");
					fnpMymsg("");
					fnpMymsg("");
					fnpMymsg("");
				}

			}

			// ****************Action Items Results Table code
			String itemNo = null;
			int ItemColNo = 0;
			int actionItemTableFlag = 0; // 0 for empty and have data then 1
			if (vActionItemResults.equalsIgnoreCase("Yes")) {
				fnpMymsg("Value for  selecting action item from Action item  table in Excel sheet is ---Yes");

				int actionItemTableRows = fnpCountRowsInTable("BulkAssignment_ActionItemResultsTable");

				if (actionItemTableRows > 0) {
					actionItemTableFlag = 1;
					fnpMymsg("Now we are going  to select record from Action item table as table is not empty and visible data rows are --" + actionItemTableFlag);

					String firstvalue = fnpFetchFromTable("BulkAssignment_ActionItemResultsTable", 1, 1);
					if (firstvalue.contains("No") && firstvalue.contains("ound")) {

						fnpMymsg("    Action Item Results table is blank ( do 'NOT have any record) .      ");
						throw new Exception("    Action Item Results table is blank ( do 'NOT have any record) .      ");

					}

					int rownoToSelect = 1;
					String xpathOfCheckbox = OR.getProperty("BulkAssignment_ActionItemResultsTable") + "/tr[" + rownoToSelect + "]/td[1]/div/div[2]";
					driver.findElement(By.xpath(xpathOfCheckbox)).click();
					Thread.sleep(1000);

					/*********
					 * hours text box is no longer come after sprint R2.1
					 * (IPM-2240)
					 ****/

					/*
					 * int inputBoxrowcount = rownoToSelect - 1; String
					 * inputXpath =
					 * "//input[contains(@id,'mainForm:actionItemTable:" +
					 * inputBoxrowcount + ":')]";
					 * 
					 * fnpWaitForVisible_NotInOR(inputXpath);
					 * driver.findElement(By.xpath(inputXpath)).clear();
					 * Thread.sleep(1000); fnpType(null, inputXpath, (String)
					 * hashXlData.get("ActionItem_Est_hr")); Thread.sleep(1000);
					 */

					ItemColNo = fnpFindColumnIndex("BulkAssignment_ActionItemResultsTableHeader", "Item#");
					itemNo = fnpFetchFromTable("BulkAssignment_ActionItemResultsTable", rownoToSelect, ItemColNo);
					fnpMymsg("We have picked row no. '" + rownoToSelect + "' and its Item no. is '" + itemNo + "' .");
					fnpMymsg("");
					fnpMymsg("");
					fnpMymsg("");

				} else {
					fnpMymsg("Data rows are not present in Actin item table , so unable to pick the action item ");
					fnpMymsg("");
					fnpMymsg("");
					fnpMymsg("");

				}

			}

			// ***************Finding Table code
			String findingNo = null;
			int FindingColNo = 0;
			int findingResultsTableFlag = 0; // 0 for empty and have data then 1
			if (vFindingResults.equalsIgnoreCase("Yes")) {
				fnpMymsg("Value for  selecting Finding Result from Finding Results table in Excel sheet is ---Yes");

				int findingResultsTableRows = fnpCountRowsInTable("BulkAssignment_FindingResultsTable");

				if (findingResultsTableRows > 0) {
					findingResultsTableFlag = 1;
					fnpMymsg("Now we are going  to select record from Finding Results table as table is not empty and visible data rows are --" + findingResultsTableRows);

					String firstvalue = fnpFetchFromTable("BulkAssignment_FindingResultsTable", 1, 1);
					if (firstvalue.contains("No") && firstvalue.contains("ound")) {

						fnpMymsg("    Findings  Results table is blank ( do 'NOT have any record) .      ");
						throw new Exception("    FindingsResults table is blank ( do 'NOT have any record) .      ");

					}

					int rownoToSelect = 1;
					String xpathOfCheckbox = OR.getProperty("BulkAssignment_FindingResultsTable") + "/tr[" + rownoToSelect + "]/td[1]/div/div[2]";
					driver.findElement(By.xpath(xpathOfCheckbox)).click();
					Thread.sleep(1000);

					/*********
					 * hours text box is no longer come after sprint R2.1
					 * (IPM-2240)
					 ****/

					/*
					 * int inputBoxrowcount = rownoToSelect - 1; String
					 * inputXpath =
					 * "//input[contains(@id,'mainForm:findingTable:" +
					 * inputBoxrowcount + ":')]";
					 * 
					 * fnpWaitForVisible_NotInOR(inputXpath);
					 * driver.findElement(By.xpath(inputXpath)).clear();
					 * Thread.sleep(1000); fnpType(null, inputXpath, (String)
					 * hashXlData.get("Finding_Est_hr")); Thread.sleep(1000);
					 */

					FindingColNo = fnpFindColumnIndex("BulkAssignment_FindingResultsTableHeader", "Finding #");
					findingNo = fnpFetchFromTable("BulkAssignment_FindingResultsTable", rownoToSelect, FindingColNo);
					fnpMymsg("We have picked row no. '" + rownoToSelect + "' and its Finding no. is '" + findingNo + "' .");
					fnpMymsg("");
					fnpMymsg("");
					fnpMymsg("");

				} else {
					fnpMymsg("Data rows are not present in Finding Results table , so unable to pick the Finding Result");
					fnpMymsg("");
					fnpMymsg("");
					fnpMymsg("");

				}

			}
			// ****************Finding Table code

			fnpGetORObjectX("BulkAssing_Assign_All_checked_items_to").click();
			String newAssigneeEmpCode = (String) hashXlData.get("Assign_All_checked_items_to_EmpCode");

			fnpSearchNSelectFirstRadioBtn(1, newAssigneeEmpCode, 2);

			fnpClick_OR("BulkAssing_SaveAssignmentBtn");

			fnpLoading_wait();

	/*		if (fnpCheckElementPresenceImmediately("ErrorMessage")) {
				fnpMymsg(" Error is thrown by application while bulk assignment");

				fnpMouseHover_OR_EvenMultipleObjectsHavingSameXpath("ErrorMessage");
				throw new Exception("Error is thrown by application while bulk assignment");
			}
			*/
			fnpCheckErrMsg("Error is thrown by application while bulk assignment");

			fnpWaitForVisibleHavingMultipleElements("TopMessage3");
			fnpMymsg("Top Message after assigning ----" + fnpGetText_OR("TopMessage3"));

			String SuccessfulMsg = fnpGetText_OR("TopMessage3");

			Assert.assertTrue(SuccessfulMsg.contains("uccess"), "Top message does not contain 'success' word, so might be bulk assignment has not been done successfully");

			fnpMymsg(" We have assigned item to '" + newAssigneeEmpCode + "'   successfully. So now going to check/ verify whether these items are present under this employee '"
					+ newAssigneeEmpCode + "'  .   ");

			xpathShowRadioBtn = ".//*[@id='mainForm:optionButton']/tbody/tr/td/label[text()='Item Assigned to']";

			driver.findElement(By.xpath(xpathShowRadioBtn)).click();
			Thread.sleep(1000);

			fnpWaitForVisible("ItemAssignedTo_lookup");
			fnpWaitTillVisiblityOfElementNClickable_OR("ItemAssignedTo_lookup");
			fnpGetORObjectX("ItemAssignedTo_lookup").click();
			fnpLoading_wait();

			fnpSearchNSelectFirstRadioBtn(1, newAssigneeEmpCode, 2);
			fnpGetORObjectX("BulkAssing_SearchBtn").click();

			fnpLoading_wait();

			int vResultRow = 0;

			// if (vTaskResults.equalsIgnoreCase("Yes")) {
			if (taskTableFlag == 1) {
				fnpMymsg("Now we are going  to search  record under Assignee  in Task Results table");
				vResultRow = fnpFindDataInTableWithPaging_passingPaginatorName("BulkAssignment_TaskTable", taskNo, TaskColNo, ":taskTable_paginator_bottom");
				if (vResultRow > 0) {
					fnpMymsg("  Record having Task '" + taskNo + "'  is present in 'Task Results ' table at row no. '" + vResultRow + "'   . ");

				} else {
					fnpMymsg("  Record having Task '" + taskNo + "'  is  'NOT present in 'Task Results ' table for Assignee.     ");
					throw new Exception("  Record having Task '" + taskNo + "'  is  'NOT present in 'Task Results ' table  for Assignee.     ");
				}

			}

			vResultRow = 0;
			// if (vActionItemResults.equalsIgnoreCase("Yes")) {
			if (actionItemTableFlag == 1) {
				fnpMymsg("Now we are going  to search  record under Assignee  in Action Item Results table");
				vResultRow = fnpFindDataInTableWithPaging_passingPaginatorName("BulkAssignment_ActionItemResultsTable", itemNo, ItemColNo,
						"mainForm:actionItemTable_paginator_bottom");
				if (vResultRow > 0) {
					fnpMymsg("  Record having Action Item '" + itemNo + "'  is present in 'Action Item Results ' table at row no. '" + vResultRow + "'   . ");

				} else {
					fnpMymsg("  Record having Action Item  '" + itemNo + "'  is  'NOT present in 'Action Item Results ' table for Assignee.     ");
					throw new Exception("  Record having Action Item  '" + itemNo + "'  is  'NOT present in 'Action Item Results ' table  for Assignee.     ");
				}

			}

			vResultRow = 0;
			// if (vFindingResults.equalsIgnoreCase("Yes")) {
			if (findingResultsTableFlag == 1) {
				fnpMymsg("Now we are going  to search  record under Assignee  in Finding Results table");
				vResultRow = fnpFindDataInTableWithPaging_passingPaginatorName("BulkAssignment_FindingResultsTable", findingNo, FindingColNo,
						"mainForm:findingTable_paginator_bottom");
				if (vResultRow > 0) {
					fnpMymsg("  Record having Finding Results '" + findingNo + "'  is present in 'Finding Results ' table at row no. '" + vResultRow + "'   . ");

				} else {
					fnpMymsg("  Record having Finding Results '" + findingNo + "'  is  'NOT present in 'Finding Results ' table for Assignee.     ");
					throw new Exception("  Record having Finding Results '" + findingNo + "'  is  'NOT present in 'Finding Results ' table  for Assignee.     ");
				}

			}

		} catch (Throwable t) {

			fail = true;
			isTestPass = false;
			fnpMymsg(" BulkAssignment_Flow  is fail" + " . Error is ---" + t.getMessage());
			fnpDesireScreenshot("BulkAssignment_Flow");
			String stackTrace = Throwables.getStackTraceAsString(t);
			String errorMsg = t.getMessage();
			errorMsg = errorMsg + "\n\n\n\nSee screenshot 'BulkAssignment_Flow'\n\n" + stackTrace;
			Exception c = new Exception(errorMsg);
			ErrorUtil.addVerificationFailure(c);

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

	}

	// @AfterSuite
	@AfterTest
	public void closebrowser() throws InterruptedException {
		driver.quit();
		IsBrowserPresentAlready = false;
		fnppradeepKillIEProcess();
		killprocess();

	}

	// @AfterSuite
	public void cleanUp() {
		driver.quit();
		IsBrowserPresentAlready = false;
		try {
			killprocess();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
