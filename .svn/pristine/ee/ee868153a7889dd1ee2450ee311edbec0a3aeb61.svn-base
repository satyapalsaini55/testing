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
import static nsf.ecap.config.IPULSE_CONSTANTS.*;
//BS-06
public class NewNew_InProc_Complete_With_Fac extends TestSuiteBase {

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
		try {
			BS05.RestartBrowserNLogin1();
		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "   RestartBrowserNLogin  is failed . ", "RestartBrowserNLogin_Failed");

		}

	}

	@Test(priority = 18, dependsOnMethods = { "Verify_Supplier_Forms_Task_Alerts" })
	public void Sample_Selection_Process_N_CheckAlerts() throws Throwable {
		BS05.Sample_Selection_Process_N_CheckAlerts();

	}

	@Test(priority = 19, dependsOnMethods = { "Sample_Selection_Process_N_CheckAlerts" })
	public void RestartBrowserNLogin2() throws Throwable {
		try {
			BS05.RestartBrowserNLogin1();
		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "   RestartBrowserNLogin  is failed . ", "RestartBrowserNLogin_Failed");

		}

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
		try {
			BS05.RestartBrowserNLogin3();
		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "   RestartBrowserNLogin  is failed . ", "RestartBrowserNLogin_Failed");

		}

	}

	@Test(priority = 22, dependsOnMethods = { "RestartBrowserNLogin4" })
	public void Facility_audit_Task() throws Throwable {

		fnpMymsg(" **************************************************************");
		try {

			//fnpSearchWorkOrderAndClickEditAlso(workOrderNo);
			

/*
			String usingGoldenProcedureOrOasis = (String) hashXlData.get("Audit_usingGoldenProcedureOrOasis");
			if ((usingGoldenProcedureOrOasis.equalsIgnoreCase("golden"))) {
				
				if (fnpCheckElementPresenceImmediately("TaskTab_ScopeValidation_BackToViewBtn")) {
					fnpClick_OR("TaskTab_ScopeValidation_BackToViewBtn");
				}
				
				if (fnpCheckElementPresenceImmediately("TaskTab_ScopeValidation_BackBtn")) {
					fnpClick_OR("TaskTab_ScopeValidation_BackBtn");
				}
				
				fnpClick_OR("TaskTab_ScopeValidation_BackBtn");
			}else{
				fnpCloseBroserAndLoginInIE();
				fnpSearchWorkOrderAndClickEditAlso_AfterRestartWhenAlertsNo(workOrderNo);
			}
			
			*/
			

			fnpCloseBroserAndLoginInIE();
			fnpSearchWorkOrderAndClickEditAlso_AfterRestartWhenAlertsNo(workOrderNo);
			
			
			
			
			fnpCommonClickTaskTab();

			fnpMymsg("we are in Facility Audit");
			fnpMymsg(" Going to click Facility Audit task no. ");
			int TaskNoColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskNoColName);
			int taskTypeColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskTypeColName);
			int facilityAuditRowNo = fnpFindRow("TasksTable_EditWO", taskType_FacilityAudit, taskTypeColIndex);

			String facilityAuditTaskNo = fnpFetchFromTable("TasksTable_EditWO", facilityAuditRowNo, TaskNoColIndex);
			// Thread.sleep(500);
			
/*			
			fnpWaitTillVisiblityOfElementNClickable_NOR(".//a[text()='" + facilityAuditTaskNo + "']");
			driver.findElement(By.linkText(facilityAuditTaskNo)).click();
			*/
			fnpClickALinkInATable(facilityAuditTaskNo);
			
			
			Thread.sleep(1000);

			fnpMymsg("Clicked on Facility Audit task no. in Task Table i.e. '" + facilityAuditTaskNo + "' .");

			fnpCheckError(" while  Facility_audit_Task");

			String originalHandle = driver.getWindowHandle();
			fnpWaitForVisible("FacilityAudit_AuditInfoTable_header");
			String AuditNoColName = "Audit No.";
			int AuditNoColIndex = fnpFindColumnIndex("FacilityAudit_AuditInfoTable_header", AuditNoColName);
			String AuditNo = fnpFetchFromTable("FacilityAudit_AuditInfoTable", 1, AuditNoColIndex);

			String visitNoColName = "Visit #";
			int visitNoColIndex = fnpFindColumnIndex("FacilityAudit_AuditInfoTable_header", visitNoColName);
			String visitNo = fnpFetchFromTable("FacilityAudit_AuditInfoTable", 1, visitNoColIndex);
			fnpMymsg("@@@@@    ---Oasis visit no. is -------" + visitNo);

			fnpMymsg("Now we are going into Oasis for filling necessary information for Facility Audit");
			
			
			int copyFromAudit_ForFacilityAudit;
			int copyToAudit_ForFacilityAudit;
			 String usingGoldenProcedureOrOasis = "Oasis";//(String) hashXlData.get("Audit_usingGoldenProcedureOrOasis");
			if (usingGoldenProcedureOrOasis.equalsIgnoreCase("golden")) {
				
				int facilityAuditInfoTable_AuditNoColIndex = fnpFindColumnIndex("FacilityAudit_AuditInfoTable_header", "Audit No.");
				String facilityAuditINfoTable_EvalNo = fnpFetchFromTable("FacilityAudit_AuditInfoTable", 1, facilityAuditInfoTable_AuditNoColIndex);
				copyFromAudit_ForFacilityAudit=Integer.parseInt((String) hashXlData.get("copy_From_Audit_ForFacilityAuditTask").trim());
				copyToAudit_ForFacilityAudit=Integer.parseInt(facilityAuditINfoTable_EvalNo.trim());
				
				fnpMymsg("Just before running golden procedure - COPY_AUDIT_TRANSACTION_SUBMIT");
				fnpCallGoldenProcedure_COPY_AUDIT_TRANSACTION_SUBMIT(copyFromAudit_ForFacilityAudit,copyToAudit_ForFacilityAudit,(String) hashXlData.get("Auditor").trim());
				fnpMymsg("Golden procedure 'COPY_AUDIT_TRANSACTION_SUBMIT' ran successfully.");
				
				
			} else {
			

				ArrayList<String> listoldtabs = new ArrayList<String>(driver.getWindowHandles());
				// Thread.sleep(500);
				
/*				fnpWaitTillVisiblityOfElementNClickable_NOR(".//a[text()='" + AuditNo + "']");
				driver.findElement(By.linkText(AuditNo)).click();
				*/
				
				fnpClickALinkInATable(AuditNo);

				// Thread.sleep(15000);
				// Thread.sleep(10000);
				Thread.sleep(15000);

				ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());

				int oldTotalcount = listoldtabs.size();
				int newTotal1 = tabs.size();
				int ii = 0;
				while (newTotal1 != (oldTotalcount + 1)) {
					Thread.sleep(1000);
					tabs = new ArrayList<String>(driver.getWindowHandles());
					newTotal1 = tabs.size();
					ii = ii + 1;
					if (ii > Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
						break;
					}
				}

				// Thread.sleep(5000);
				Thread.sleep(2000);
				int tabsCount = newTotal1;

				for (int i = 0; i < tabsCount; i++) {
					if (originalHandle.equalsIgnoreCase(tabs.get(i))) { // nothing
																		// to do

					} else {
						driver.switchTo().window(tabs.get(i));
						Thread.sleep(1000);
						break;
					}

				}
				// fnpMymsg("  --before waiting for Audits menu visible");
				fnpWaitForVisible("Oasis_Audits_menu");
				fnpCheckErrorUsingPageSource_Oasis();
				// fnpMymsg("  --after waiting for Audits menu visible");
				fnpMymsg("We are in Oasis Application and going to click 'Search' under Audits tab.");

				fnpMouseHover("Oasis_Audits_menu"); // Thread.sleep(1000);

				String SecondHandle = driver.getWindowHandle();
				fnpGetORObjectX("Oasis_Audits_SearchSubmenu").click();
				// fnpLoading_wait();
				Thread.sleep(2000);
				fnpCheckErrorUsingPageSource_Oasis();

				// fnpGetORObjectX("Oasis_Audits_AuditNoTxtBox").sendKeys(AuditNo);
				fnpType("OR", "Oasis_Audits_AuditNoTxtBox", AuditNo);

				fnpGetORObjectX("Oasis_Audits_SearchBtn").click();
				Thread.sleep(2000);
				fnpWaitForVisible("Oasis_Audits_EditVisit");// It throws timeout
															// exception b/c button
															// not
				// visible as in oasis it is on same page and little fraction of
				// time page load something like that

				fnpCheckErrorUsingPageSource_Oasis();
				fnpWaitTillVisiblityOfElementNClickable_OR("Oasis_Audits_EditVisit");
				fnpGetORObjectX("Oasis_Audits_EditVisit").click();
				Thread.sleep(2000);
				fnpCheckErrorUsingPageSource_Oasis();
				fnpWaitForVisible("Oasis_Audits_ModifyBtn");// It throws timeout
															// exception b/c button
															// not
				// visible as in oasis it is on same page and little fraction of
				// time page load something like that
				fnpWaitTillVisiblityOfElementNClickable_OR("Oasis_Audits_ModifyBtn");
				fnpCheckErrorUsingPageSource_Oasis();
				fnpGetORObjectX("Oasis_Audits_ModifyBtn").click();
				Thread.sleep(2000);
				fnpCheckErrorUsingPageSource_Oasis();

				fnpGetORObjectX("Oasis_Audits_AuditorCodeTxtBox").clear();
				// fnpGetORObjectX("Oasis_Audits_AuditorCodeTxtBox").sendKeys((String)
				// hashXlData.get("Auditor_Code"));
				fnpType("OR", "Oasis_Audits_AuditorCodeTxtBox", (String) hashXlData.get("Auditor_Code"));

				fnpGetORObjectX("Oasis_Audits_scheduleDateFromImg").click();
				Thread.sleep(1000);
				fnpCheckErrorUsingPageSource_Oasis();

				driver.findElement(By.xpath(".//td[@class='wkday curdate']")).click();
				fnpCheckErrorUsingPageSource_Oasis();
				fnpGetORObjectX("Oasis_Audits_SaveBtn").click();

				Thread.sleep(4000); // mandatory as page refreshing/changing
									// otherwise throwing error
				// fnpWaitForVisible("Oasis_Audits_VisitSavedSuccessfullyMsg");

				fnpCheckErrorUsingPageSource_Oasis();

				String successMsg = fnpGetORObjectX("Oasis_Audits_VisitSavedSuccessfullyMsg").getText();
				int iwhilecounter = 0;
				while (!successMsg.contains("Visit saved successfully.")) {
					Thread.sleep(1000);
					iwhilecounter++;
					fnpCheckErrorUsingPageSource_Oasis();
					successMsg = fnpGetORObjectX("Oasis_Audits_VisitSavedSuccessfullyMsg").getText();
					if (iwhilecounter > 30) {
						break;

					}

				}

				fnpCheckErrorUsingPageSource_Oasis();
				Assert.assertTrue(successMsg.contains("Visit saved successfully."), "Visit saved successfully message is not visible");
				fnpMymsg("Visit saved successfully message is visible");

				Thread.sleep(1000);
				fnpMouseHover("Oasis_Home_menu");
				Thread.sleep(2000);
				// Thread.sleep(1000);
				// fnpGetORObjectX("Oasis_Home_MyCalendarSubmenu").click();
				fnpClick_OR_WithoutWait("Oasis_Home_MyCalendarSubmenu");
				Thread.sleep(10000);
				fnpCheckErrorUsingPageSource_Oasis();

				/*******
				 * For time being Just to find total how much perform audit buttons
				 * and what property they have
				 *****/
				List<WebElement> PerformAuditBtns = driver.findElements(By.xpath(OR.getProperty("Oasis_Calendar_PerformAuditBtn")));
				fnpMymsg("Total Perform Audit buttons =>" + PerformAuditBtns.size());
				for (int pai = 0; pai < PerformAuditBtns.size(); pai++) {

					fnpMymsg("@@@   ---this is button no ---" + pai + "----and its onclick " + "property is ---" + PerformAuditBtns.get(pai).getAttribute("onclick"));

				}

				/*******
				 * For time being Just to find total how much perform audit buttons
				 * and what property they have
				 *****/

				// String
				// perfomrAuditXpath=OR.getProperty("Oasis_Calendar_PerformAuditBtn2")+" and contains(@onclick,'"+AuditNo+"')]";
				String perfomrAuditXpath = OR.getProperty("Oasis_Calendar_PerformAuditBtn2") + " and contains(@onclick,'" + visitNo + "')]";
				fnpMymsg("@@@@   visit no is ----" + visitNo);
				List<WebElement> totalPerformAuditBtns = driver.findElements(By.xpath(perfomrAuditXpath));
				int totalCountPerformBtns;

				int iPerformAudit = 0;
				while (true) {
					totalPerformAuditBtns = driver.findElements(By.xpath(perfomrAuditXpath));
					iPerformAudit++;

					fnpMymsg("Total Perform Audit buttons =>" + totalPerformAuditBtns.size());

					totalCountPerformBtns = totalPerformAuditBtns.size();
					if (totalCountPerformBtns > 0) {
						fnpMymsg("Going to click first Perform Audit button");
						totalPerformAuditBtns.get(0).click();
						break;

					} else {

						if (iPerformAudit > 2) {
							throw new Exception("As there is no Perform Audit button on this page corresponding to " + "this visit no '" + visitNo
									+ "', so unable to proceed further. ");

						} else {
							Thread.sleep(500);
						}

					}

				}

				fnpMymsg("'" + totalCountPerformBtns + "' first Perform Audit button has been clicked.");

				fnpCheckErrorUsingPageSource_Oasis();
				fnpGetORObjectX("Oasis_CompleteAuditFormBtn").click();
				Thread.sleep(2000);
				fnpCheckErrorUsingPageSource_Oasis();

				fnpWaitForVisible("Oasis_AuditNo_leftside");// It throws timeout
															// exception b/c button
															// not
				// visible as in oasis it is on same page and little fraction of
				// time page load something like that
				fnpWaitTillVisiblityOfElementNClickable_OR("Oasis_AuditNo_leftside");
				fnpCheckErrorUsingPageSource_Oasis();
				fnpGetORObjectX("Oasis_AuditNo_leftside").click();
				Thread.sleep(2000);
				fnpCheckErrorUsingPageSource_Oasis();

				fnpSimpleSelectList("Oasis_GoToSectionList", (String) hashXlData.get("SectionList"));

				Thread.sleep(5000);

				fnpCheckErrorUsingPageSource_Oasis();
				fnpWaitForVisible("Oasis_Audits_NonacceptRadioBtn");// It throws
																	// timeout
																	// exception b/c
																	// button not
				// visible as in oasis it is on same page and little fraction of
				// time page load something like that
				fnpWaitTillVisiblityOfElementNClickable_OR("Oasis_Audits_NonacceptRadioBtn");
				fnpGetORObjectX("Oasis_Audits_NonacceptRadioBtn").click();

				// fnpGetORObjectX("Oasis_Audits_NotesTxtBox").sendKeys((String)
				// hashXlData.get("FA_Notes"));
				fnpType("OR", "Oasis_Audits_NotesTxtBox", (String) hashXlData.get("FA_Notes"));
				// fnpGetORObjectX("Oasis_Audits_SectionNotesTxtBox").sendKeys((String)
				// hashXlData.get("FA_SectionNotes"));
				fnpType("OR", "Oasis_Audits_SectionNotesTxtBox", (String) hashXlData.get("FA_SectionNotes"));

				java.util.List<WebElement> oasisSaveBtn = driver.findElements(By.xpath(OR.getProperty("Oasis_SaveBtn")));
				oasisSaveBtn.get(0).click();
				Thread.sleep(2000);

				fnpCheckErrorUsingPageSource_Oasis();
				fnpWaitForVisible("Oasis_AuditSubmitBtn");// It throws timeout
															// exception b/c button
															// not
				// visible as in oasis it is on same page and little fraction of
				// time page load something like that
				fnpWaitTillVisiblityOfElementNClickable_OR("Oasis_AuditSubmitBtn");
				// fnpWaitForVisible("Oasis_AuditSubmitBtn");
				listoldtabs = new ArrayList<String>(driver.getWindowHandles());
				fnpCheckErrorUsingPageSource_Oasis();
				fnpGetORObjectX("Oasis_AuditSubmitBtn").click();
				// Thread.sleep(3000);

				tabs = new ArrayList<String>(driver.getWindowHandles());

				oldTotalcount = listoldtabs.size();
				newTotal1 = tabs.size();
				ii = 0;
				while (newTotal1 != (oldTotalcount + 1)) {
					Thread.sleep(1000);
					tabs = new ArrayList<String>(driver.getWindowHandles());
					newTotal1 = tabs.size();
					ii = ii + 1;
					if (ii > Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
						break;
					}
				}

				Thread.sleep(5000);
				// int tabsCount =newTotal1;

				fnpCheckErrorUsingPageSource_Oasis();

				for (int i = 0; i < tabs.size(); i++) {

					System.out.println("This is Window Id - " + i + "--->" + tabs.get(i));
					if ((tabs.get(i).equalsIgnoreCase(originalHandle)) || (tabs.get(i).equalsIgnoreCase(SecondHandle))) {

					} else {
						driver.switchTo().window(tabs.get(i));
						break;

					}

				}
				Thread.sleep(1000);
				fnpWaitForVisible("Oasis_VisitStartTimeHour");// It throws timeout
																// exception b/c
																// button not
				// visible as in oasis it is on same page and little fraction of
				// time page load something like that

				fnpTypeTimeInOasis("OR", "Oasis_VisitStartTimeHour", (String) hashXlData.get("Oasis_Facud_VisitStartTimeHour"));
				fnpTypeTimeInOasis("OR", "Oasis_VisitStartMinute", (String) hashXlData.get("Oasis_Facud_VisitStartMinute"));
				fnpTypeTimeInOasis("OR", "Oasis_VisitEndTimeHour", (String) hashXlData.get("Oasis_Facud_VisitEndTimeHour"));
				fnpTypeTimeInOasis("OR", "Oasis_VisitEndMinute", (String) hashXlData.get("Oasis_Facud_VisitEndMinute"));

				Thread.sleep(2000);
				fnpGetORObjectX("Oasis_UpdateVisitTime_SaveBtn").click();

				// Thread.sleep(2000);
				Thread.sleep(5000);

				driver.switchTo().window(SecondHandle);

				//fnpWaitForVisible("Oasis_VisitSuccessfullySubmittedMsg");
				fnpWaitForVisible("Oasis_VisitSuccessfullySubmittedMsg", 360);
				Thread.sleep(2000);
				fnpGetORObjectX("Oasis_ExitBtn").click();
				// Thread.sleep(2000);
				Thread.sleep(5000);

				driver.close();
				// driver.quit();

				driver.switchTo().window(originalHandle);
			
				fnpMymsg("We have inserted all the necessary information in Oasis for Facility Audit and now come back to iPulse successfully.");
			}
			
			


			
			driver.navigate().refresh();
			Thread.sleep(2000);

			fnpWaitForVisible("FacilityAudit_AuditInfoTable");
			String StatusColName = "Status";
			int StatusColIndex = fnpFindColumnIndex("FacilityAudit_AuditInfoTable_header", StatusColName);
			String AuditInfoStatus = fnpFetchFromTable("FacilityAudit_AuditInfoTable", 1, StatusColIndex);
			/*
			 * Assert.assertTrue(AuditInfoStatus.equalsIgnoreCase("SUBMITTED"),
			 * "Status in Audit Inof table should have 'SUBMITTED' status but now it is --"
			 * + AuditInfoStatus); fnpMymsg(
			 * "After refreshing the page , Status in Audit Inof table become 'SUBMITTED' status"
			 * );
			 */

			int waitCount = 0;
			while (true) {
				waitCount++;
				// driver.navigate().refresh();

				AuditInfoStatus = fnpFetchFromTable("FacilityAudit_AuditInfoTable", 1, StatusColIndex);

				if (AuditInfoStatus.equalsIgnoreCase("COMPLETED")) {
					fnpMymsg("After refreshing the page , Status in Audit Inof table become 'COMPLETED' status ");
					break;
				} else {
					Thread.sleep(1000 * 20 * 1);
					driver.navigate().refresh();
					Thread.sleep(8000);

					fnpMymsg("We are in  waiting loop for Status in Audit Inof table to become 'COMPLETED' status ----" + (waitCount * 0.5) + "  minutes. ");
				}

				if (waitCount > 60) {

					fnpMymsg("Script waited for approx. 30 min to become 'COMPLETED' status in Audit Info table but it is still showing '" + AuditInfoStatus + "' .");
					throw new Exception("Script waited for approx. 30 min to become 'COMPLETED' status in Audit Info table but it is  still showing '" + AuditInfoStatus + "' .");

				}

			}

			fnpMymsg("Now going to check FACUD task status at detail page under Info tab ");

			// ******************************************************************************************************
			int TaskSummaryTable_StausColIndex = fnpFindColumnIndex("LiteEval_TaskSummaryTable_header", "Status");
			String FACAUDTask_status = fnpFetchFromTable("LiteEval_TaskSummaryTable", 1, TaskSummaryTable_StausColIndex);

			waitCount = 0;
			while (true) {
				waitCount++;
				FACAUDTask_status = fnpFetchFromTable("LiteEval_TaskSummaryTable", 1, TaskSummaryTable_StausColIndex);

				if (FACAUDTask_status.equalsIgnoreCase("COMPLETED")) {
					fnpMymsg("Task FACUD status has become to 'COMPLETED' status. ");
					break;
				} else {
					Thread.sleep(1000 * 10);
					driver.navigate().refresh();
					Thread.sleep(5000);
					fnpMymsg("Waiting for FACUD taks status 'COMPLETED' ----" + ((waitCount * 30) / 60) + " minutes");
					fnpMymsg("It is still   ----" + FACAUDTask_status);

					fnpMymsg("We are in  waiting loop for FACUD Task to  become 'COMPLETED' status ----" + (waitCount * 0.5) + "  minutes. ");
					fnpMymsg("It is still   ----" + FACAUDTask_status);
				}

				if (waitCount > 180) {

					fnpMymsg("Script waited for approx. more than 90 min to become 'COMPLETED' status of FACUD  task but it is still showing '" + FACAUDTask_status + "' .");
					throw new Exception("Script waited for approx. more than 90 min to become 'COMPLETED' status of FACUD  task but it is still showing '" + FACAUDTask_status
							+ "' .");

				}

				fnpCheckError(" while  waiting  to become 'COMPLETED' status of FACUD  task and refreshing again n again.");

			}

			// ******************************************************************************************************

			int CountCARRows = fnpCountRowsInTable("FacilityAudit_CARTable");
			// if (CountCARRows > 1) {
			if (CountCARRows > 0) {
				fnpMymsg("Corrective Actions has been generated and they are --" + CountCARRows);
			} else {
				fnpMymsg("Corrective Actions has been generated and they are --" + CountCARRows);
				String firstCellValue = fnpFetchFromTable("FacilityAudit_CARTable", 1, 1);
				Assert.assertFalse(firstCellValue.equalsIgnoreCase("No records found."),
						"Corrective Actions have not been generated as first row contains 'No records found.' message");

			}

			int CARStatusColIndex = fnpFindColumnIndex("FacilityAudit_CARTable_header", "Status");
			String CARStatus;
			for (int i = 1; i < CountCARRows + 1; i++) {

				CARStatus = fnpFetchFromTable("FacilityAudit_CARTable", i, CARStatusColIndex);
				Assert.assertEquals(CARStatus, "PENDING", "Corrective Actions are not in 'PENDING' status in row --" + i);

			}

			// ************Verify Alerts
			// ************************************************

			fnpCommonGoToHomeNClick();

			// -------Alert Work Order in InProcess status -------------------
			fnpCommonAlertGeneratedVerification("Work_order_in_INPROCESS_status", "WO_InProcess_Status_AlertTable_header", "WO #", "WO_InProcess_Status_AlertTable",
					"WO_InProcess_Status_Alert_WO_filterTxtBox", workOrderNo);
			// ---------------------------------------------

			/*
			 * // -------Alert Work order(s) with no tasks ready to perform
			 * ------------------- fnpCommonAlertGeneratedVerification(
			 * "Work_order_with_no_tasks_ready_to_perform",
			 * "Work_order_with_no_tasks_ready_to_perform_AlertTable_header",
			 * "WO #", "Work_order_with_no_tasks_ready_to_perform_AlertTable",
			 * "Work_order_with_no_tasks_ready_to_perform_Alert_WO_filterTxtBox"
			 * , workOrderNo); // //
			 * ---------------------------------------------
			 */

			// ************Verify
			// Alerts************************************************

			fnpSearchWorkOrderAndClickEditAlso(workOrderNo);

			try {
				if (((String) hashXlData.get("Alerts_Verify_Flag")).equalsIgnoreCase("No")) {
					fnpCommonBackToViewNBackBtnClick();
					fnpWaitForVisible("Task_ShowAllLink");

				} else {

					fnpCommonClickTaskTab();

				}
			} catch (NullPointerException e) {
				// nothing to do just ignore it. Null pointer exception will be
				// thrown when variable is not present.
			}

			// fnpCommonClickTaskTab();
			// Thread.sleep(500);
			
			
/*			fnpWaitTillVisiblityOfElementNClickable_NOR(".//a[text()='" + facilityAuditTaskNo + "']");
			driver.findElement(By.linkText(facilityAuditTaskNo)).click();
			*/
			
			fnpClickALinkInATable(facilityAuditTaskNo);
			

			// fnpLoading_wait();
			// Thread.sleep(4000);//commented on 1 may
			fnpMymsg("Clicked on Facility Audit task no. in Task Table i.e. '" + facilityAuditTaskNo + "' .");

			fnpCheckError(" while  Facility_audit_Task.");

			originalHandle = driver.getWindowHandle();

			fnpMymsg("Now we are going into Oasis again for Corrective Actions 's approval");

			ArrayList<String> listoldtabs = new ArrayList<String>(driver.getWindowHandles());

/*			// Thread.sleep(500);
			fnpWaitTillVisiblityOfElementNClickable_NOR(".//a[text()='" + AuditNo + "']");
			driver.findElement(By.linkText(AuditNo)).click();
			*/
			fnpClickALinkInATable(AuditNo);
			

			// Thread.sleep(15000);
			Thread.sleep(10000);

			// tabs = new ArrayList<String>(driver.getWindowHandles());

			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());

			int oldTotalcount = listoldtabs.size();
			int newTotal1 = tabs.size();
			int ii = 0;
			while (newTotal1 != (oldTotalcount + 1)) {
				Thread.sleep(1000);
				tabs = new ArrayList<String>(driver.getWindowHandles());
				newTotal1 = tabs.size();
				ii = ii + 1;
				if (ii > Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					break;
				}
			}

			int tabsCount = tabs.size();
			Thread.sleep(2000);

			for (int i = 0; i < tabsCount; i++) {

				if (originalHandle.equalsIgnoreCase(tabs.get(i))) { // nothing
																	// to do

				} else {
					driver.switchTo().window(tabs.get(i));
					Thread.sleep(1000);
					break;
				}

			}
			Thread.sleep(1000);
			fnpWaitForVisible("Oasis_Search_Topmenu");// It throws timeout
														// exception b/c button
														// not
			// visible as in oasis it is on same page and little fraction of
			// time page load something like that

			fnpMymsg("We are in Oasis Application for Corrective Actions approval.");

			fnpMouseHover("Oasis_Search_Topmenu");

			fnpMouseHover("Oasis_Search_CorrectiveActionsSubmenu");
			fnpGetORObjectX("Oasis_Search_CorrectiveActionsSubmenu").click();

			// fnpGetORObjectX("Oasis_CA_AuditNoTxtBox").sendKeys(AuditNo);
			fnpType("OR", "Oasis_CA_AuditNoTxtBox", AuditNo);

			fnpGetORObjectX("Oasis_CA_SearchBtn").click();
			Thread.sleep(2000);
			fnpWaitForVisible("Oasis_CA_ViewCARImage");// It throws timeout
														// exception b/c button
														// not
			// visible as in oasis it is on same page and little fraction of
			// time page load something like that
			fnpWaitTillVisiblityOfElementNClickable_OR("Oasis_CA_ViewCARImage");
			fnpGetORObjectX("Oasis_CA_ViewCARImage").click();
			Thread.sleep(2000);
			fnpWaitForVisible("Oasis_CA_EditCARReport");// It throws timeout
														// exception b/c button
														// not
			// visible as in oasis it is on same page and little fraction of
			// time page load something like that
			fnpWaitTillVisiblityOfElementNClickable_OR("Oasis_CA_EditCARReport");
			fnpGetORObjectX("Oasis_CA_EditCARReport").click();

			// fnpGetORObjectX("Oasis_ChangeCARStatusToList").sendKeys("APPROVED");
			fnpType("OR", "Oasis_ChangeCARStatusToList", "APPROVED");
			fnpGetORObjectX("Oasis_CARReportEdit_SaveBtn").click();
			fnpMymsg("Now we are coming back into iPulse from Oasis after performing steps for  Corrective Actions 's approval");
			Thread.sleep(10000);
			driver.close();
			Thread.sleep(2000);
			driver.switchTo().window(originalHandle);
			driver.navigate().refresh();

			CARStatusColIndex = fnpFindColumnIndex("FacilityAudit_CARTable_header", "Status");
			for (int i = 1; i < CountCARRows + 1; i++) {

				CARStatus = fnpFetchFromTable("FacilityAudit_CARTable", i, CARStatusColIndex);
				Assert.assertEquals(CARStatus, "APPROVED", "Corrective Actions are not in 'APPROVED' status in row --" + i);
				fnpMymsg("All Corrective Actions are in 'APPROVED' status .  ");

			}

			// ************Verify Alerts
			// ************************************************

			fnpCommonGoToHomeNClick();

			// -------Alert Work Order in InProcess status -------------------
			fnpCommonAlertGeneratedVerification("Work_order_in_INPROCESS_status", "WO_InProcess_Status_AlertTable_header", "WO #", "WO_InProcess_Status_AlertTable",
					"WO_InProcess_Status_Alert_WO_filterTxtBox", workOrderNo);
			// ---------------------------------------------

			/*
			 * 
			 * // -------Alert Work order(s) with no tasks ready to perform
			 * ------------------- fnpCommonAlertGeneratedVerification(
			 * "Work_order_with_no_tasks_ready_to_perform",
			 * "Work_order_with_no_tasks_ready_to_perform_AlertTable_header",
			 * "WO #", "Work_order_with_no_tasks_ready_to_perform_AlertTable",
			 * "Work_order_with_no_tasks_ready_to_perform_Alert_WO_filterTxtBox"
			 * , workOrderNo); // //
			 * ---------------------------------------------
			 */

			// ************Verify
			// Alerts************************************************

			// ------------------------------------------------------------------------------------------------------

			fnpMymsg(" **************************************************************");

		} catch (Throwable t) {
			
/*			
			fail = true;
			isTestPass = false;
			fnpDesireScreenshot("Facility_audit_Task");
			fnpMymsg(" Facility_audit_Task is fail,See screenshot 'Facility_audit_Task'" + " . Error is ---" + t.getMessage());

			String stackTrace = Throwables.getStackTraceAsString(t);
			String errorMsg = t.getMessage();
			errorMsg = errorMsg + "\n\n\n\nSee screenshot 'Facility_audit_Task'\n\n" + stackTrace;
			Exception c = new Exception(errorMsg);
			ErrorUtil.addVerificationFailure(c);
			*/
			fnpCommonFinalCatchBlock(t, "   Facility_audit_Task is fail. ", "Facility_audit_Task");
			
			
		}

	}

	@Test(priority = 23, dependsOnMethods = { "Facility_audit_Task" })
	public void RestartBrowserNLogin5() throws Throwable {
		try {
			BS05.RestartBrowserNLogin1();
		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "   RestartBrowserNLogin  is failed . ", "RestartBrowserNLogin_Failed");

		}

	}

	@Test(priority = 24, dependsOnMethods = { "Facility_audit_Task" })
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
