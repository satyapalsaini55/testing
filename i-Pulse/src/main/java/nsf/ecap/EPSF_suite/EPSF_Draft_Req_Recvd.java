/*package nsf.ecap.EPSF_suite;



import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import nsf.ecap.Health_Science_suite.TestSuiteBase_HealthScience;
import nsf.ecap.Proposals_suite.TestSuiteBase_Proposal;
import nsf.ecap.Work_Order_suite.TestSuiteBase;
import nsf.ecap.util.ErrorUtil;
import nsf.ecap.util.TestDataProvider;
import nsf.ecap.util.TestUtil;
import nsf.ecap.util.Xls_Reader;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.os.WindowsUtils;
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

public class EPSF_Draft_Req_Recvd extends TestSuiteBase_EPSF {
	 //BS-01
	
	
	// Runmode of test case in a suite
	@BeforeTest
	@Parameters({ "className" })
	public void checkTestSkip(
								String className) throws Exception {

		//classNameText = className;
		setClassNameText(className);
		
		count = -1;

		try {
			if (className.isEmpty()) {
				className = this.getClass().getSimpleName();

			}

			classNameText = className;
			currentScriptCode = fnpMappingClassNameWithScenarioCode(classNameText).trim();
			fnpMymsg("             ");
			fnpMymsg("             ");
			fnpMymsg("             ");
			fnpMymsg("             ");
			fnpMymsg("             ");
			fnpMymsg("=========================================================================================================================================");

			if (!TestUtil.isTestCaseRunnable(currentSuiteXls, className)) {
				fnpMymsg("Skipping Test Case" + className + " as runmode set to NO");// logs
				throw new SkipException("Skipping Test Case '" + className + "' as runmode set to NO");// reports
			}

	
			
			fnpMymsg("Going to Run test cases of '" + classNameText + "'");
			fnpMymsg("             ");
			fnpMymsg("             ");

			runmodes = TestUtil.getDataSetRunmodes(currentSuiteXls, className);
			fail = false;
			isTestPass = true;

			if (!IsBrowserPresentAlready) {
				IsBrowserPresentAlready = fnpLaunchBrowserAndLogin();
				fnpMymsg(" Browser is launched");
			}

		} catch (SkipException sk) {
			isTestPass = false;
			String stackTrace = Throwables.getStackTraceAsString(sk);
			String errorMsg = sk.getMessage();
			Exception c = new Exception(errorMsg);
			ErrorUtil.addVerificationFailure(c);
			throw new SkipException(errorMsg + " \n\nSo,Skipping Test Case" + className);// reports
		} catch (Throwable t) {
			isTestPass = false;
			fnpDesireScreenshotProposal(className);
			String stackTrace = Throwables.getStackTraceAsString(t);
			String errorMsg = t.getMessage();
			errorMsg = errorMsg + "\n\n\n\nSee screenshot '" + className + "'\n\n" + stackTrace;
			Exception c = new Exception(errorMsg);
			ErrorUtil.addVerificationFailure(c);
			throw new Exception(errorMsg);// reports

		}
	}







	//@Test(dataProvider = "getTestData")
	@Test(dataProviderClass = TestDataProvider.class,dataProvider = "EPSF_Draft_Req_Recvd")
	public void EPSF_Draft_Req_Recvd_flow(
								Hashtable table) throws Throwable {

		fnpMymsg("****************************************************************");
		count++;
		if (!runmodes[count].equalsIgnoreCase("Y")) {
			skip = true;
			fnpMymsg("Runmode for test set data set to no " + (count + 1));
			fnpMymsg("*****************");
			fnpMymsg("");
			throw new SkipException("Runmode for test set data set to no " + (count + 1));
		}

		try {
			
			if (!IsBrowserPresentAlready) {
				IsBrowserPresentAlready = fnpLaunchBrowserAndLogin();
				fnpMymsg(" Browser is launched");
			}


			hashXlData=new HashMap(table);

			fnpCommonCodeEPSFCreation(table);
			
			String EPSF_No=fnpGetText_OR("EPSF_No");
			if (classNameText.equalsIgnoreCase("EPSF_Draft_Req_Recvd")) {
								
				fnpClickTopHomeMenu();				
				fnpMymsg("@@@Pradeep Alert Name is---"+(String) hashXlData.get("Alert_EPSF_DraftStatus"));				
				
				
				// -------Alert EPSF created in DRAFT status -------------------
				fnpCommonAlertGeneratedVerification(
						"Alert_EPSF_DraftStatus",
						"EPSFDraftStatusAlertTable_header",
						" EPSF ID",
						"EPSFDraftStatusAlertTable",
						"EPSFDraftStausAlert_EPSFID_filterTxtBox",
						EPSF_No);
				// ---------------------------------------------
				

				
				
				
				
				
				// -------Action_item_assigned_to_client -------------------
				//" EPSF ID" has a one space character in starting
				TestSuiteBase_HealthScience.fnpCommonAlertGeneratedVerificationHS(
						table,
						"Alert_EPSF_DraftStatus",
						"EPSFDraftStatusAlertTable_header",
						" EPSF ID",
						"EPSFDraftStatusAlertTable",
						"EPSFDraftStausAlert_EPSFID_filterTxtBox",
						EPSF_No);
				// ---------------------------------------------

				fnpMymsg("Now going to click this search EPSF no.");
				driver.findElement(By.linkText(EPSF_No)).click();
				fnpLoading_wait();
			}
			
			
			if (fnpCheckElementPresenceImmediately("EPSFEditBtn")) {
				fnpClick_OR("EPSFEditBtn");
			}
			
			
			fnpPFList("EPSFStatusPFList", "EPSFStatusPFListOptions", (String) table.get("First_ChangeStatus"));			
			fnpClick_OR("EPSF_SaveBtn");			
			String current_epsfStatus=fnpGetText_OR("EPSF_Status");
			Assert.assertTrue(current_epsfStatus.equalsIgnoreCase((String)table.get("First_ChangeStatus")), "EPSF status not changed to '"+
					table.get("First_ChangeStatus")+"'.");
			fnpMymsg("EPSF status  changed to '"+table.get("First_ChangeStatus")+"'.");
			
			
			fnpPFList("EPSFStatusPFList", "EPSFStatusPFListOptions", (String) table.get("Second_ChangeStatus"));			
			fnpClick_OR("EPSF_SaveBtn");
			current_epsfStatus=fnpGetText_OR("EPSF_Status");
			Assert.assertTrue(current_epsfStatus.equalsIgnoreCase((String)table.get("Second_ChangeStatus")), "EPSF status not changed to '"+
					table.get("Second_ChangeStatus")+"'.");	
			fnpMymsg("EPSF status  changed to '"+(String)table.get("Second_ChangeStatus")+"'.");
			
			
		} catch (Throwable t) {
			
			fail = true;
			isTestPass = false;
			fnpMymsg("  EPSFDraftReqRecvd_flow  method is failed . " + " . Error is ---" + t.getMessage());
			fnpDesireScreenshotProposal("EPSFDraftReqRecvd_flow_Failed" + (SShots + 1));
			String stackTrace = Throwables.getStackTraceAsString(t);
			String errorMsg = t.getMessage();
			errorMsg = errorMsg + "\n\n\n\n EPSFDraftReqRecvd_flow  method is failed   .See screenshot 'EPSFDraftReqRecvd_flow_Failed" + (SShots + 1) + "'\n\n" + stackTrace;

			
			IsBrowserPresentAlready = false; // This is for special reason if one case is fail due to application error or any other reason then it close the
			// browser and login again.
			driver.quit();
			
			Exception c = new Exception(errorMsg);
			ErrorUtil.addVerificationFailure(c);
			
			
			
			fnpCommonFinalCatchBlock( t,"  EPSFDraftReqRecvd_flow  method is failed . ","EPSFDraftReqRecvd_flow") ;
			
			IsBrowserPresentAlready = false; // This is for special reason if one case is fail due to application error or any other reason then it close the
			// browser and login again.
			driver.quit();
			
			

		}

	}







	@AfterMethod
	public void reportDataSetResult() {
		if (skip)
			TestUtil.reportDataSetResult(currentSuiteXls, this.getClass().getSimpleName(), count + 2, "SKIP");
		else if (fail) {
			isTestPass = false;
			TestUtil.reportDataSetResult(currentSuiteXls, this.getClass().getSimpleName(), count + 2, "FAIL");
		} else
			TestUtil.reportDataSetResult(currentSuiteXls, this.getClass().getSimpleName(), count + 2, "PASS");

		skip = false;
		fail = false;

	}







	@AfterTest
	public void reportTestResult() {
		int rowNum=currentSuiteXls.getCellRowNum("Test Cases", "TCID", this.getClass().getSimpleName());
		currentSuiteXls.setCellData("Test Cases", "Last_Execution", rowNum, fnReturnDateWithTimeForExcel() );
		
		if (isTestPass){
			TestUtil.reportDataSetResult(currentSuiteXls, "Test Cases", TestUtil.getRowNum(currentSuiteXls, this.getClass().getSimpleName()), "PASS");
			currentSuiteXls.setCellData("Test Cases", "Last_Successful_Execution", rowNum, fnReturnDateWithTimeForExcel() );
		}else{
			TestUtil.reportDataSetResult(currentSuiteXls, "Test Cases", TestUtil.getRowNum(currentSuiteXls, this.getClass().getSimpleName()), "FAIL");
		}
		
		

	}







	// @AfterSuite
	@AfterTest
	public void closebrowser() throws InterruptedException {
		driver.quit();
		IsBrowserPresentAlready = false;
		killprocess();

	}




	
}

*/