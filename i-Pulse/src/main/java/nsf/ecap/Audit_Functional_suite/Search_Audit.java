
package nsf.ecap.Audit_Functional_suite;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.TimeUnit;


import nsf.ecap.Work_Order_suite.TestSuiteBase;
import nsf.ecap.config.Constants;
import nsf.ecap.config.Constants;
import nsf.ecap.util.ErrorUtil;
import nsf.ecap.util.TestDataProvider;
import nsf.ecap.util.TestUtil;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;

public class Search_Audit extends
		TestSuiteBase_Audit_Functional_suite {

	// Runmode of test case in a suite
	@BeforeTest
	@Parameters({ "className" })
	public void checkTestSkip(String className) throws Throwable {

		// initLogs(this.getClass());
		try {
			// this.getClass().getSimpleName()
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
			fnpMymsg("Checking Runmode of testcase '" + className + "' .");


			if (!TestUtil.isTestCaseRunnable(currentSuiteXls, className)) {


				fnpMymsg("Skipping Test Case" + className + " as runmode set to NO");// logs
				fnpMymsg("=========================================================================================================================================");
				throw new SkipException("Skipping Test Case" + className + " as runmode set to NO");// reports
			}

			fnpMymsg("Going to execute the script for  '" + className + "'  as runmode set to Yes");// logs


			fail = false;
			isTestPass = true;

			if (!IsBrowserPresentAlready) {
				IsBrowserPresentAlready = fnpLaunchBrowserAndLogin();
				fnpMymsg(" Browser is launched");
			} else {
				fnpMymsg("@Pradeep----Browser is not already present, something error happened.");
			}


			

		}

		catch (SkipException sk) {
			skip = true;
			String stackTrace = Throwables.getStackTraceAsString(sk);
			String errorMsg = sk.getMessage();
			Exception c = new Exception(stackTrace);
			ErrorUtil.addVerificationFailure(c);
			throw new SkipException(errorMsg + " \n\nSo,Skipping Test Case" + className);// reports
		} catch (Throwable t) {



			fnpCommonFinalCatchBlock(t, "  checkTestSkip method  is fail . ", "checkTestSkipFail");

		}

	}

	@Test(priority = 1)
	public void Search_Audit_Functionality() throws Throwable {

		fnpMymsg(" **************************************************************");
		fnpMymsg(" Executing Search_Audit_Functionality");

		try {
			//fnpLoadHashData(hashXlData, currentSuiteXls, className, 2);
			
			fnpClickAtTopWorkAroundForClickingMenu();

			//fnpCommonClickTopMainMenu("Menu","FirstRefrenceLinkJustBelowToMenu","Search_Audit_LinkName");
		//	fnpAjax_Link_Click_By_Passing_TwoAjaxXPathx("Menu","Create_Client");

			fnpCommonClickTopMainMenu("Menu","FirstRefrenceLinkJustBelowToMenu","Search_Audit_LinkName","MainSearchButton");
		

			fnpClick_OR("MainSearchButton");
			
			
/*			fnpCommoniPulseSearchFunctionality2( classNameText,"Search_Audit_LinkName",
					 "AuditNo_Txtbx", "Audit #", "DetailViewAditPageheadingContainsId", Constants.ViewAuditDetailPageHeading);
	*/		
			fnpCommoniPulseSearchFunctionality( classNameText,"Search_Audit_LinkName",
					 "AuditNo_Txtbx", "Audit #");
			
					

			isTestPass = true;
			fnpMymsg(" **************************************************************");

		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "  Search_Audit_Functionality  is fail . ", "Search_Audit_Functionality");

		}

	}


	@AfterMethod
	public void reportDataSetResult() {
		if (fail) {
			isTestPass = false;
		}
	}

	@AfterTest
	public void reportTestResult() throws Throwable {
		int rowNum = currentSuiteXls.getCellRowNum("Test Cases", "TCID", this.getClass().getSimpleName());
		currentSuiteXls.setCellData("Test Cases", "Last_Execution", rowNum, fnReturnDateWithTimeForExcel());

		// Add Results column to test case sheet
		if (isTestPass) {
			TestUtil.reportDataSetResult(currentSuiteXls, "Test Cases", TestUtil.getRowNum(currentSuiteXls, this.getClass().getSimpleName()), "PASS");
			currentSuiteXls.setCellData("Test Cases", "Last_Successful_Execution", rowNum, fnReturnDateWithTimeForExcel());

		} else {

			TestUtil.reportDataSetResult(currentSuiteXls, "Test Cases", TestUtil.getRowNum(currentSuiteXls, this.getClass().getSimpleName()), "FAIL");
		}
		fnpMymsg("=========================================================================================================================================");

		hashXlData.clear();

	//	fnpCommonCloseBrosersAndKillProcess();

	}


}