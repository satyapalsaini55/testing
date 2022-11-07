package nsf.ecap.Quartz_Jobs_suite;

import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import nsf.ecap.Work_Order_suite.TestSuiteBase;
import nsf.ecap.util.ErrorUtil;
import nsf.ecap.util.TestDataProvider;
import nsf.ecap.util.TestUtil;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.os.WindowsUtils;
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

public class Quartz_Jobs extends TestSuiteBase_Quartz_Jobs {

	// Runmode of test case in a suite
	@BeforeTest
	@Parameters({ "className" })
	public void checkTestSkip(String className) throws Exception {

		//classNameText = className;
		setClassNameText( className);
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

			runmodes = TestUtil.getDataSetRunmodes(Quartz_Jobs_suitexls, className);
			fail = false;
			isTestPass = true;

			start = new Date();
/*
			if (!IsBrowserPresentAlready) {
				IsBrowserPresentAlready = fnpLaunchBrowserAndLogin();
				fnpMymsg(" Browser is launched");
			}
			
			*/
			

		} catch (SkipException sk) {
			isTestPass = false;
			String stackTrace = Throwables.getStackTraceAsString(sk);
			String errorMsg = sk.getMessage();
			Exception c = new Exception(errorMsg);
			ErrorUtil.addVerificationFailure(c);
			throw new SkipException(errorMsg + " \n\nSo,Skipping Test Case" + className);// reports
		} catch (Throwable t) {
			isTestPass = false;
			//fnpDesireScreenshotProposal(className);
			fnpDesireScreenshot(className);
			String stackTrace = Throwables.getStackTraceAsString(t);
			String errorMsg = t.getMessage();
			errorMsg = errorMsg + "\n\n\n\nSee screenshot '" + className + "'\n\n" + stackTrace;
			Exception c = new Exception(errorMsg);
			ErrorUtil.addVerificationFailure(c);
			throw new Exception(errorMsg);// reports

		}
	}

	// @Test(dataProvider = "getTestData")
	@Test(dataProviderClass = TestDataProvider.class, dataProvider = "QuartzJobDataProvider")
	public void Quartz_Job_flow(Hashtable<String, String> table) throws Throwable {

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
			
/*			
			if (count==0) {
				if (!IsBrowserPresentAlready) {
					IsBrowserPresentAlready = fnpLaunchBrowserAndLogin();
					fnpMymsg(" Browser is launched");
				}

				fnpCommonClickTopMainMenu("Menu", "FirstRefrenceLinkJustBelowToMenu", "QuartzJobsTopMenuLink", "JobNameFilterBox");
				
			} else {
				fnpClick_OR("MainClearLink");
			}
*/

			if (!IsBrowserPresentAlready) {
				IsBrowserPresentAlready = fnpLaunchBrowserAndLogin();
				fnpMymsg(" Browser is launched");
			}
			
			if (! (fnpCheckElementDisplayedImmediately("JobNameFilterBox"))) {
				
				if (!IsBrowserPresentAlready) {
					IsBrowserPresentAlready = fnpLaunchBrowserAndLogin();
					fnpMymsg(" Browser is launched");
				}
				
				fnpCommonClickTopMainMenu("Menu", "FirstRefrenceLinkJustBelowToMenu", "QuartzJobsTopMenuLink", "JobNameFilterBox");
			} else {
				fnpClick_OR("MainClearLink");
			}
			
			
			
			

			//fnpGetORObjectX("JobNameFilterBox").sendKeys(table.get("Job_Name"));
			String jobName=table.get("Job_Name").trim();
		//	driver.findElement(By.xpath(OR.getProperty("JobNameFilterBox"))).sendKeys(jobName);
			fnpType("OR", "JobNameFilterBox", jobName);
			Thread.sleep(1000);
			fnpLoading_wait();

			// int a =fnpCountRowsInTable("JobTable");
			String dataInFirstRowFirstColumn = fnpFetchFromTable("JobTable", 1, 1);

			if (!(table.get("Is_Record_present_in_search").equalsIgnoreCase("Yes"))) {

				dataInFirstRowFirstColumn = fnpFetchFromTable("JobTable", 1, 1);
				int j = 0;
				while ((!(dataInFirstRowFirstColumn.contains("No Jobs found.")) | (!dataInFirstRowFirstColumn.toLowerCase().contains("found"))) & j < 15) {
					j++;
					Thread.sleep(1000);
					dataInFirstRowFirstColumn = fnpFetchFromTable("JobTable", 1, 1);
				}

				if ((!(dataInFirstRowFirstColumn.toLowerCase().contains("found"))) | (!(dataInFirstRowFirstColumn.contains("No Jobs found")))) {
					fnpMymsg("Qurartz job -'" + table.get("Job_Name") + "' is  present in Jobs table but it should not be present in this table after search. ");
					throw new Exception("Qurartz job -'" + table.get("Job_Name") + "' is present in Jobs table");
				} else {
					fnpMymsg("Qurartz job -'" + table.get("Job_Name") + "' is Not present in Jobs table as expected (should not present). ");
				}

			} else {
				int rowCountAfterSearch = fnpCountRowsInTable("JobTable");

				if (rowCountAfterSearch != 1) {
					fnpMymsg("Qurartz job -'" + table.get("Job_Name") + "' is NOT  searched successfully as after search , total row should be only 1 .");
					throw new Exception("Qurartz job -'" + table.get("Job_Name") + "' is NOT  searched successfully as after search , total row should be only 1 .");
				} else {
					fnpMymsg("Qurartz job -'" + table.get("Job_Name") + "' is  searched successfully as after search , total row should be only 1 .");
				}

				dataInFirstRowFirstColumn = fnpFetchFromTable("JobTable", 1, 2);
				//System.out.println("Actual is--"+dataInFirstRowFirstColumn);
				//System.out.println("Expected is--"+table.get("Job_Name").trim());
				if (    (!(dataInFirstRowFirstColumn.equalsIgnoreCase(table.get("Job_Name").trim())))          ) {
				//if (    (!(dataInFirstRowFirstColumn.contains(table.get("Job_Name").trim()))) ) {
					fnpMymsg("Qurartz job -'" + table.get("Job_Name") + "' is NOT  present in Jobs table but it should  be present in this table after search. ");
					throw new Exception("Qurartz job -'" + table.get("Job_Name") + "' is NOT present in Jobs table as it should  be present in this table after search");
				} else {
					fnpMymsg("Qurartz job -'" + table.get("Job_Name") + "' is  present in Jobs table as expected (should  present). ");
				}

			}

		} catch (Throwable t) {
/*			fail = true;
			isTestPass = false;
			fnpMymsg("  Quartz_Jobs   is failed . " + " . Error is ---" + t.getMessage());
			//fnpDesireScreenshotProposal("Quartz_Jobs_Failed" + (SShots + 1));
			fnpDesireScreenshot("Quartz_Jobs_Failed" + (SShots + 1));
						
			String stackTrace = Throwables.getStackTraceAsString(t);
			String errorMsg = t.getMessage();
			errorMsg = errorMsg + "\n\n\n\n Quartz_Jobs  method is failed   .See screenshot 'Quartz_Jobs_Failed" + (SShots + 1) + "'\n\n" + stackTrace;

			
			
			
			
			IsBrowserPresentAlready = false; // This is for special reason if
												// one case is fail due to
												// application error or any
												// other reason then it close
												// the
			// browser and login again. 
			driver.quit();
			
			
			

			Exception c = new Exception(errorMsg);
			ErrorUtil.addVerificationFailure(c);
			*/
			IsBrowserPresentAlready = false;
			fnpCommonFinalCatchBlock(t, ". \n\n    Hence Quartz_Jobs flow  is fail . ", "Quartz_JobsFail");
		//	driver.quit(); // for time being

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

	@AfterTest(alwaysRun = true)
	public void reportTestResult() throws Throwable {
		int rowNum = currentSuiteXls.getCellRowNum("Test Cases", "TCID", this.getClass().getSimpleName());
		currentSuiteXls.setCellData("Test Cases", "Last_Execution", rowNum, fnReturnDateWithTimeForExcel());

		if (isTestPass) {
			TestUtil.reportDataSetResult(currentSuiteXls, "Test Cases", TestUtil.getRowNum(currentSuiteXls, this.getClass().getSimpleName()), "PASS");
			currentSuiteXls.setCellData("Test Cases", "Last_Successful_Execution", rowNum, fnReturnDateWithTimeForExcel());
			fnpElapsedTime(currentSuiteName, this.getClass().getSimpleName(), currentScriptCode, "PASS");
		} else {
			TestUtil.reportDataSetResult(currentSuiteXls, "Test Cases", TestUtil.getRowNum(currentSuiteXls, this.getClass().getSimpleName()), "FAIL");
			fnpElapsedTime(currentSuiteName, this.getClass().getSimpleName(), currentScriptCode, "FAIL");
		}

	}

	// @AfterSuite
	@AfterTest
	public void closebrowser() throws InterruptedException {
		driver.quit();
		IsBrowserPresentAlready = false;
		killprocess();

	}

	/*
	 * @DataProvider public Object[][] getTestData() { return
	 * TestUtil.getData(Proposals_suitexls, this.getClass().getSimpleName()); }
	 */
}
