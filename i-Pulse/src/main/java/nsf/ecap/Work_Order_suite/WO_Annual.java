package nsf.ecap.Work_Order_suite;

import nsf.ecap.util.ErrorUtil;
import nsf.ecap.util.TestUtil;

import org.openqa.selenium.By;
//import org.openqa.selenium.os.WindowsUtils;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;
import static nsf.ecap.config.IPULSE_CONSTANTS.*;
// BS-10
public class WO_Annual extends TestSuiteBase {

	@BeforeTest
	public void checkTestSkip() throws Throwable {
		BS01 = new NewNew_Draft_InReview_No_Fac();
		BS11 = new WO_Custom();
		BS01.checkTestSkip(this.getClass().getSimpleName());
	}

	@Test(priority = 1)
	public void CreateWOFlow() throws Throwable {
		BS01.CreateWOFlow();

	}

	@Test(priority = 2, dependsOnMethods = { "CreateWOFlow" })
	public void WOAnnual_AddingData__Facility_Tab() throws Throwable {
		fnpMymsg(" **************************************************************");
		fnpMymsg(" Executing WOAnnual_AddingData__Facility_Tab");
		try {

			// fnpLoading_wait();
			fnpWaitTillClickable("EditFacilityTabLink");
			fnpGetORObjectX("EditFacilityTabLink").click();
			fnpMymsg("Facility tab has been clicked");

			int nRowsBeforeAdding = fnpCountRowsInTable("Facility_DataTable");
			fnpWaitForVisible("Facility_IncludeExistingFacilityStandardBTn");
			fnpWaitTillVisiblityOfElementNClickable_OR("Facility_IncludeExistingFacilityStandardBTn");

			fnpClick_OR("Facility_IncludeExistingFacilityStandardBTn");
			fnpMymsg("Include Existing Facility/Standard button has been clicked");

			fnpSearchNSelectFirstCheckBox(1, (String) hashXlData.get("FacCode"), 2);
			Thread.sleep(2000);

			fnpClickInDialog_OR("SelectAndReturnBtn");

			Thread.sleep(2000);
			// fnpLoading_wait();
			Thread.sleep(2000);

			// Just for make sure that pop has been closed as sometimes it takes
			// more time
			fnpWaitForVisible("Facility_IncludeExistingFacilityStandardBTn");
			fnpWaitTillVisiblityOfElementNClickable_OR("Facility_IncludeExistingFacilityStandardBTn");

			int nRowsAfterAdding = fnpCountRowsInTable("Facility_DataTable");
			int start_statusComplete = 0;
			while (true) {
				start_statusComplete++;
				nRowsAfterAdding = fnpCountRowsInTable("Facility_DataTable");
				try {
					if (nRowsAfterAdding == nRowsBeforeAdding + 1) {
						break;
					} else {
						if ((start_statusComplete > Integer.parseInt(CONFIG.getProperty("genMax_waitTime")))) {
							break;

						} else {
							Thread.sleep(1000);
							continue;
						}

					}

				} catch (Throwable t) {
					continue;
				}

			}

			Assert.assertEquals(nRowsAfterAdding, nRowsBeforeAdding + 1, "Facility has been added successfully as no. of rows increase by 1");

			fnpCheckError(" while adding data in Facility Tab ");

			fnpMymsg(" Adding data in New Wo Facility tab is Pass");
			isTestPass = true;
			fnpMymsg(" **************************************************************");

		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "  Adding data in WOAnnual_AddingData__Facility_ tab is failed . ", "WOAnnual_AddingData__Facility_TabFail");

		}
	}

	@Test(priority = 4, dependsOnMethods = { "WOAnnual_AddingData__Facility_Tab" })
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

	// @Test(enabled = false)
	@Test(priority = 8, dependsOnMethods = { "AddingData__Financials_Tab" })
	public void Verifying__ActionItems_Tab() throws Throwable {
		BS01.Verifying__ActionItems_Tab();

	}

	@Test(priority = 9, dependsOnMethods = { "Verifying__ActionItems_Tab" })
	public void WOAnnual__DRAFT_INPROCESS() throws Throwable {

		fnpMymsg(" **************************************************************");
		fnpMymsg(" Executing WOAnnual__DRAFT_INPROCESS");
		try {

			fnpWaitTillClickable("InfoTab_EditWO");
			fnpGetORObjectX("InfoTab_EditWO").click();
			// Thread.sleep(5000);
			fnpLoading_wait();
			fnpWaitForVisible("InfoTab_WOStatusPFList");

			String status = "InProcess";

			fnpInsertStandardVersionAtInfoTab((String) hashXlData.get("Standard_Version"));

			fnpPFList("InfoTab_WOStatusPFList", "InfoTab_WOStatusPFListOptions", status);

			fnpClick_OR("ProAddDocSaveBtn");

			fnpCheckError(" while changing status from DRAFT to InProcess in Info tab ");

			// fnpWaitForVisible("TopMessage3");
			fnpWaitForVisibleHavingMultipleElements("TopMessage3");
			String SuccessfulMsg = fnpGetText_OR("TopMessage3");
			fnpMymsg("Top Message after changing status from DRAFT to InProcess in Info tab ----" + SuccessfulMsg);

			Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"),
					"Top message does not contain 'success' word, so might be changing status from DRAFT to InProcess in Info tab is NOT successful.");

			fnpWaitTillClickable("NewlyCrWOTopBannerInfo");
			fnpGetORObjectX("NewlyCrWOTopBannerInfo").click();

			fnpWaitForVisible("TopBannerWOStatus");
			String changedWOStatus = fnpGetORObjectX("TopBannerWOStatus").getText();
			Assert.assertEquals(changedWOStatus, "INPROCESS", " WO status is not changed from 'DRAFT' to 'InProcess'.");
			fnpMymsg("Now  WO status has been changed from 'DRAFT' to 'InProcess' status.");

		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "  WOAnnual__DRAFT_INPROCESS is failed . ", "WOAnnual__DRAFT_INPROCESS_Fail");
		}
	}

	@Test(priority = 10, dependsOnMethods = { "WOAnnual__DRAFT_INPROCESS" })
	public void Verify_AnnualListing_ProfessionalServices_Task_Completed() throws Throwable {

		fnpMymsg(" **************************************************************");
		fnpMymsg(" Executing Verify_AnnualListing_ProfessionalServices_Task_Completed");
		try {

			fnpCommonClickTaskTab();

			int StatusColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_StatusColName);
			int taskTypeColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskTypeColName);
			int AnnualListingRowNo = fnpFindRow("TasksTable_EditWO", taskType_AnnualListing, taskTypeColIndex);
			String AnnualListingStatus_TaskTable = fnpFetchFromTable("TasksTable_EditWO", AnnualListingRowNo, StatusColIndex);

			Assert.assertTrue(AnnualListingStatus_TaskTable.toLowerCase().contains("completed"),
					"In Task Table , status for Annual Listing has not been changed  to 'COMPLETED' . It is still showing '" + AnnualListingStatus_TaskTable + "'.");
			fnpMymsg("In Task table , status for Annual Listing is automatically completed.");

			int ProfessionalServRowNo = fnpFindRow("TasksTable_EditWO", taskType_ProfessionalServices, taskTypeColIndex);
			String ProfessionalServStatus_TaskTable = fnpFetchFromTable("TasksTable_EditWO", ProfessionalServRowNo, StatusColIndex);

			Assert.assertTrue(ProfessionalServStatus_TaskTable.toLowerCase().contains("completed"),
					"In Task Table , status for Professional Services has not been changed  to 'COMPLETED' . It is still showing '" + ProfessionalServStatus_TaskTable + "'.");

			fnpMymsg("In Task table , status for Professional Services is automatically completed.");

			fnpCheckError("  in Verify_AnnualListing_ProfessionalServices_Task_Completed method ");

		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "  Verify_AnnualListing_ProfessionalServices_Task_Completed is failed . ", "Verify_AnnualListing_ProfessionalServices_Task_CompletedFailed");

		}
	}

	@Test(priority = 11, dependsOnMethods = { "Verify_AnnualListing_ProfessionalServices_Task_Completed" })
	public void Verify_Alerts_Generated() throws Throwable {

		fnpMymsg(" **************************************************************");
		fnpMymsg(" Executing Verify_Alerts_Generated");
		try {

			fnpCommonGoToHomeNClick();
			// -------Alert Work Order in InProcess status -------------------
			fnpCommonAlertGeneratedVerification("Custom_Resolution_Work_Orders_with_completed_tasks_not_yet_invoiced",
					"Custom_Resolution_Work_Orders_with_completed_tasks_not_yet_invoiced_AlertTable_header", "WO #",
					"Custom_Resolution_Work_Orders_with_completed_tasks_not_yet_invoiced_AlertTable",
					"Custom_Resolution_Work_Orders_with_completed_tasks_not_yet_invoiced_Alert_WO_filterTxtBox", workOrderNo);
			// ---------------------------------------------

		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "  Verify_Alerts_Generated is failed . ", "Verify_Alerts_GeneratedFailed");

		}
	}

	// @Test(enabled = false)
	@Test(priority = 11, dependsOnMethods = { "Verify_Alerts_Generated" })
	public void InVoice_Completed() throws Throwable {

		fnpMymsg(" **************************************************************");
		fnpMymsg(" Executing InVoice_Completed");
		try {

			fnpSearchWorkOrderAndClickEditAlso(workOrderNo);

			fnpCommonClickFinancialTab();

			// fnpWaitForVisible("FinancialTab_EditWO_InvoiceBillToPFList");
			fnpWaitForVisible("FinancialTab_InvoiceBillToLabel");
			if (fnpCheckElementPresenceImmediately("EditWOBtn")) {

				fnpClick_OR("EditWOBtn");
			}

			fnpWaitForVisible("Facility_InvoiceCompleteTaskBtn");

			fnpClick_OR("Facility_InvoiceCompleteTaskBtn");

			fnpWaitForVisible("Facility_InvoiceCompleteTask_YesBtn");
			fnpClickInDialog_OR("Facility_InvoiceCompleteTask_YesBtn");

			fnpCheckError(" after clicking Invoice compelete task button. ");

		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "  InVoice_Completed_ is failed . ", "InVoice_Completed_Failed");

		}
	}

	@Test(priority = 12, dependsOnMethods = { "InVoice_Completed" })
	public void WOAnnual_INPROCESS_COMPLETE() throws Throwable {

		fnpMymsg(" **************************************************************");
		fnpMymsg(" Executing WOAnnual_INPROCESS_COMPLETE");
		try {

			// Thread.sleep(5000);
			// fnpLoading_wait();
			fnpWaitTillClickable("InfoTab_EditWO");
			fnpWaitForVisible("InfoTab_EditWO");
			fnpGetORObjectX("InfoTab_EditWO").click();
			fnpLoading_wait();
			fnpWaitForVisible("InfoTab_WOStatusPFList");

			fnpWaitForVisible("InfoTab_WOStatusPFList");

			String status = "Complete";

			fnpPFList("InfoTab_WOStatusPFList", "InfoTab_WOStatusPFListOptions", status);

			fnpClick_OR("ProAddDocSaveBtn");

			fnpCheckError(" while changing status from InProcess to Complete in Info tab ");

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
			Assert.assertEquals(changedWOStatus, "COMPLETE", " WO status is not changed from InProcess to Complete'.");
			fnpMymsg("Now  WO status has been changed from InProcess to Complete status.");

		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "  WOAnnual_INPROCESS_COMPLETE is failed . ", "WOAnnual_INPROCESS_COMPLETE_Fail");

		}
	}

	@Test(priority = 13, dependsOnMethods = { "WOAnnual_INPROCESS_COMPLETE" })
	public void App_Memory_StackTrace() throws Throwable {
		BS11.App_Memory_StackTrace();

	}
	
	
	@Test(priority = 14, dependsOnMethods = { "App_Memory_StackTrace" })
	public void CLEANUP_WO_AUTOMATION_DATA() throws Throwable {
		BS05 = new NewNew_InProc_Completed_No_Fac();		
		BS05.CLEANUP_WO_AUTOMATION_DATA();

	}
	
	

	@AfterTest
	public void reportTestResult() throws Throwable {
		int rowNum = Work_Order_suitexls.getCellRowNum("Test Cases", "TCID", this.getClass().getSimpleName());
		Work_Order_suitexls.setCellData("Test Cases", "Last_Execution", rowNum, fnReturnDateWithTimeForExcel());

		// Add Results column to test case sheet
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
