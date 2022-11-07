/*package nsf.ecap.EPSF_suite;



import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import nsf.ecap.Health_Science_suite.TestSuiteBase_HealthScience;
import nsf.ecap.Proposals_suite.Proposal_Won;
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

public class EPSF_Draft_Req_Void extends TestSuiteBase_EPSF {
	 //BS-04



	@BeforeTest
	public void checkTestSkip() throws Exception {
		count = -1;
		EPSF_BS_01 = new EPSF_Draft_Req_Recvd();
		EPSF_BS_01.checkTestSkip(this.getClass().getSimpleName());

	}
	



	//@Test(dataProvider = "getTestData")
	@Test(dataProviderClass = TestDataProvider.class,dataProvider = "EPSF_Draft_Req_Void")
	public void EPSF_Draft_Req_Void_flow(
								Hashtable table) throws Throwable {

	try{
		EPSF_BS_01.EPSF_Draft_Req_Recvd_flow(table);
			
		} catch (Throwable t) {

			
			fnpCommonFinalCatchBlock( t,"  EPSF_Draft_Req_Recvd_flow  method is failed . ","EPSF_Draft_Req_Recvd_flow") ;
			
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