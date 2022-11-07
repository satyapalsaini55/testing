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

public class BulkAssign_ChangeAssignTo extends TestSuiteBase {

	@BeforeTest
	public void checkTestSkip() throws Throwable {
		BS01 = new NewNew_Draft_InReview_No_Fac();
		BS01.checkTestSkip(this.getClass().getSimpleName());
		BS13 = new BulkAssign_Unassign_To_Assign();
	}

	@Test
	public void BulkAssignment_Flow() {
		BS13.BulkAssignment_Flow();
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

	//	WindowsUtils.killByName("IEDriverServer.exe");

		try {
			killprocess();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
