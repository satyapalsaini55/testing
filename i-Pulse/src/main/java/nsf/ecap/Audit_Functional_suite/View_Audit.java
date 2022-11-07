
package nsf.ecap.Audit_Functional_suite;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.TimeUnit;


import nsf.ecap.Work_Order_suite.NewNew_Draft_InReview_No_Fac;
import nsf.ecap.Work_Order_suite.TestSuiteBase;
import nsf.ecap.config.Constants;
import nsf.ecap.config.Constants;
import nsf.ecap.util.ErrorUtil;
import nsf.ecap.util.TestDataProvider;
import nsf.ecap.util.TestUtil;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;

public class View_Audit extends
		TestSuiteBase_Audit_Functional_suite {

	// Runmode of test case in a suite
	@BeforeTest
	public void checkTestSkip() throws Throwable {
		SA_BS01 = new Search_Audit();
		SA_BS01.checkTestSkip(this.getClass().getSimpleName());
	}



	@Test(priority = 1)
	public void View_Audit_Functionality() throws Throwable {

		fnpMymsg(" **************************************************************");
		fnpMymsg(" Executing View_Audit_Functionality");

		try {
			

			
			
			if (!(fnpCheckElementDisplayedImmediately("MainSearchButton"))) {
				fnpClickAtTopWorkAroundForClickingMenu();
				//fnpCommonClickTopMainMenu("Menu","FirstRefrenceLinkJustBelowToMenu","Search_Audit_LinkName");
				fnpCommonClickTopMainMenu("Menu","FirstRefrenceLinkJustBelowToMenu","Search_Audit_LinkName","MainSearchButton");
				//fnpLoading_wait();


			}
			
			String first_value_in_first_row = fnpFetchFromStSearchTable(1, 1);
			
			if ((first_value_in_first_row.contains("No records found"))) {
				fnpClick_OR("MainSearchButton");
				String noToBeSearch = fnpFetchFromStSearchTable(1, 1);
				int j = 0;
			//	fnpLoading_wait();
				while (noToBeSearch.contains("No records found") & j < 15) {
					j++;
					Thread.sleep(1000);
					noToBeSearch = fnpFetchFromStSearchTable(1, 1);
				}
				
			}
			
			String viewAuditNo= fnpFetchFromStSearchTable(1, "Audit #");
			
			
			fnpWaitTillVisiblityOfElementNClickable(".//a[text()='"+viewAuditNo+"']");
			driver.findElement(By.linkText(viewAuditNo)).click();
		//	fnpMymsg("just after clicking first record");
			fnpLoading_wait();
			fnpCheckError("");

			
			String viewHeading=fnpGetText_OR("DetailViewAditPageheadingContainsId");
			
			try{
				Assert.assertTrue(viewHeading.contains(Constants.ViewAuditDetailPageHeading), "View Audit Page heading does not contain '" +
						Constants.ViewAuditDetailPageHeading +"' word,  but '"+viewHeading+"' is displayed .");
			}catch(Throwable t){
				throw new Exception(t);
			}
		
	
			fnpMymsg("After clicking hyperlink searched Audit no. from searched result page , View Audit  page is opened correctly. ");
		
			

					

			isTestPass = true;
			fnpMymsg(" **************************************************************");

		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "  View_Audit_Functionality  is fail . ", "View_Audit_Functionality");

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