package nsf.ecap.Work_Order_suite;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

import nsf.ecap.util.*;
import nsf.ecap.Work_Order_suite.*;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.os.WindowsUtils;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;

//BS-07
public class NN_Complete_With_Fac_N_BatchJob extends TestSuiteBase {

	@BeforeTest
	public void checkTestSkip() throws Throwable {
		BS01 = new NewNew_Draft_InReview_No_Fac();
		BS01.checkTestSkip(this.getClass().getSimpleName());
		// BS05 = new NewNew_InProc_Completed_No_Fac();
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

		BS05 = new NewNew_InProc_Completed_No_Fac();
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

	@Test(priority = 15, dependsOnMethods = { "VerifySampleSelectionReviewer" })
	public void VerifyStatus_InReview_To_InProcess_N_checkAlerts() throws Throwable {
		BS05.VerifyStatus_InReview_To_InProcess_N_checkAlerts();
	}

	@Test(priority = 16, dependsOnMethods = { "VerifyStatus_InReview_To_InProcess_N_checkAlerts" })
	public void Supplier_Forms_task() throws Throwable {
		BS05.Supplier_Forms_task();

	}

	@Test(priority = 17, dependsOnMethods = { "Supplier_Forms_task" })
	public void Verify_Supplier_Forms_Task_Alerts() throws Throwable {
		BS05.Verify_Supplier_Forms_Task_Alerts();

	}

	@Test(priority = 17, dependsOnMethods = { "Verify_Supplier_Forms_Task_Alerts" })
	public void RestartBrowserNLogin1() throws Throwable {
		BS05.RestartBrowserNLogin1();

	}

	@Test(priority = 18, dependsOnMethods = { "Verify_Supplier_Forms_Task_Alerts" })
	public void Sample_Selection_Process_N_CheckAlerts() throws Throwable {
		BS05.Sample_Selection_Process_N_CheckAlerts();

	}

	@Test(priority = 19, dependsOnMethods = { "Sample_Selection_Process_N_CheckAlerts" })
	public void RestartBrowserNLogin2() throws Throwable {
		BS05.RestartBrowserNLogin1();

	}

	@Test(priority = 19, dependsOnMethods = { "RestartBrowserNLogin2" })
	public void VerifyPhyEvalReviewer() throws Throwable {
		BS05.VerifyPhyEvalReviewer();

	}

	@Test(priority = 20, dependsOnMethods = { "VerifyPhyEvalReviewer" })
	public void Literature_Eval() throws Throwable {
		BS05.Literature_Eval();
	}

	@Test(priority = 20, dependsOnMethods = { "Literature_Eval" })
	public void RestartBrowserNLogin3() throws Throwable {
		BS05.RestartBrowserNLogin3();

	}

	@Test(priority = 21, dependsOnMethods = { "RestartBrowserNLogin3" })
	public void Physical_Eval_With_Alerts() throws Throwable {
		BS05.Physical_Eval_With_Alerts();
	}

	@Test(priority = 21, dependsOnMethods = { "Physical_Eval_With_Alerts" })
	public void RestartBrowserNLogin4() throws Throwable {
		// BS05.RestartBrowserNLogin1();
		BS05.RestartBrowserNLogin3(); // in this IE browser is launched

	}

	@Test(priority = 22, dependsOnMethods = { "RestartBrowserNLogin4" })
	public void Facility_audit_Task() throws Throwable {

		BS06 = new NewNew_InProc_Complete_With_Fac();
		BS06.Facility_audit_Task();
	}

	@Test(priority = 23, dependsOnMethods = { "Facility_audit_Task" })
	public void RestartBrowserNLogin5() throws Throwable {

		BS05.RestartBrowserNLogin1();
	}

	@Test(priority = 24, dependsOnMethods = { "RestartBrowserNLogin5" })
	public void Certification_Decision_Task() throws Throwable {
		BS05.Certification_Decision_Task();

	}

	@Test(priority = 25, dependsOnMethods = { "Certification_Decision_Task" })
	public void Verify_INPROCESS_to_COMPLETE_Automatically() throws Throwable {
		BS05.Verify_INPROCESS_to_COMPLETE_Automatically();

	}
	
	
	@Test(priority = 26, dependsOnMethods = { "Verify_INPROCESS_to_COMPLETE_Automatically" })
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
