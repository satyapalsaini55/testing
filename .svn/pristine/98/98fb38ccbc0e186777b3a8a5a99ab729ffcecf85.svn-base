package nsf.ecap.Work_Order_suite;

import java.util.ArrayList;
import java.util.Iterator;

import nsf.ecap.util.*;
import nsf.ecap.Work_Order_suite.*;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.os.WindowsUtils;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;

//BS-04
public class NewNew_Draft_InProcess_With_Fac extends TestSuiteBase {

	@BeforeTest
	public void checkTestSkip() throws Throwable {
		BS01 = new NewNew_Draft_InReview_No_Fac();
		BS05 = new NewNew_InProc_Completed_No_Fac();
		BS01.checkTestSkip(this.getClass().getSimpleName());
	}

	// @Test(enabled = false)
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

		BS05.Client_App_Review_Task();

	}

	@Test(priority = 13, dependsOnMethods = { "Client_App_Review_Task" })
	public void Check_RemoveAlert_Client_App_Review_Task() throws Throwable {

		BS05.Check_RemoveAlert_Client_App_Review_Task();

	}

	@Test(priority = 14, dependsOnMethods = { "Check_RemoveAlert_Client_App_Review_Task" })
	public void Scope_Validation_Task() throws Throwable {

		BS05.Scope_Validation_Task();

	}

	@Test(priority = 14, dependsOnMethods = { "Scope_Validation_Task" })
	public void VerifySampleSelectionReviewer() throws Throwable {
		BS05.VerifySampleSelectionReviewer();

	}

	@Test(priority = 15, dependsOnMethods = { "Scope_Validation_Task" })
	public void VerifyStatus_InReview_To_InProcess_N_checkAlerts() throws Throwable {

		BS05.VerifyStatus_InReview_To_InProcess_N_checkAlerts();

	}
	
	
/*	@Test(priority = 16, dependsOnMethods = { "VerifyStatus_InReview_To_InProcess_N_checkAlerts" })
	public void CLEANUP_WO_AUTOMATION_DATA() throws Throwable {
		BS01.CLEANUP_WO_AUTOMATION_DATA();

	}*/
	

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
