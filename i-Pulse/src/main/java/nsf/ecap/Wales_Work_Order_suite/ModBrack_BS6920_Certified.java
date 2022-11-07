package nsf.ecap.Wales_Work_Order_suite;

import java.util.Hashtable;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import nsf.ecap.Non_Food_Compounds_suite.NFC_ExistingNew_Draft_Complete;
import nsf.ecap.Work_Order_suite.Modbrack_Not_Certified;
import nsf.ecap.Work_Order_suite.NewNew_Draft_InReview_No_Fac;
import nsf.ecap.Work_Order_suite.NewNew_InProc_Completed_No_Fac;
import nsf.ecap.util.TestDataProvider;
import nsf.ecap.util.TestUtil;



public class ModBrack_BS6920_Certified extends TestSuiteBase_Wales_Work_Order_suite{
	
	ModBrack_WRAS_Approved BSW_01;
	

	@BeforeTest
	@Parameters({ "className" })
	public void checkTestSkip(String className) throws Throwable {
		
		
		BSW_01 = new ModBrack_WRAS_Approved();
		BSW_01.checkTestSkip(className);

	}


	@Test(priority = 1,dataProviderClass = TestDataProvider.class,dataProvider = "ModBrack_BS6920_Certified")
	public void CreateWOFlow(Hashtable<String, String> table) throws Throwable {
		//count++;
		BSW_01.Wales_Approved_flow(table);

	}


	
	@AfterMethod
	public void reportDataSetResult() throws Throwable {
		
		fnpCommonCloseBrowsersAndKillProcess();
		
		
		if (skip)
				fnpMymsg("");
			//comment below line to not write skip in result column  OR not overwrite previous result if current test case for current data set is skipped
			//TestUtil.reportDataSetResult(currentSuiteXlsReference, this.getClass().getSimpleName(), count + 2, "SKIP");
		else if (fail) {
			isTestPass = false;
			TestUtil.reportDataSetResult(currentSuiteXls, this.getClass().getSimpleName(), count + 2, "FAIL");
		//	fnpElapsedTime(currentSuiteName, this.getClass().getSimpleName(), currentScriptCode +" ====>     User --> "+(String) hashXlData.get("User")+"   And CO --> "+ (String) hashXlData.get("CO"), "FAIL");
			fnpElapsedTime(currentSuiteName, this.getClass().getSimpleName(), currentScriptCode +" ====>     User --> "+(String) hashXlData.get("User")+"   And WO_SubType --> "+ ((String) hashXlData.get("WO_Sub_Type")).trim(), "FAIL");
		} else
			{
			TestUtil.reportDataSetResult(currentSuiteXls, this.getClass().getSimpleName(), count + 2, "PASS");			
			//currentSuiteXlsReference.setCellData("Test Cases", "Last_Successful_Execution", rowNum, fnReturnDateWithTimeForExcel());
			currentSuiteXls.setCellData(this.getClass().getSimpleName(), "Last_Successful_Execution", count + 2, fnReturnDateWithTimeForExcel());
			
			//fnpElapsedTime(currentSuiteName, this.getClass().getSimpleName(), currentScriptCode +" ====>     User --> "+(String) hashXlData.get("User")+"   And CO --> "+ (String) hashXlData.get("CO"), "PASS");
			fnpElapsedTime(currentSuiteName, this.getClass().getSimpleName(), currentScriptCode +" ====>     User --> "+(String) hashXlData.get("User")+"   And WO_SubType --> "+ ((String) hashXlData.get("WO_Sub_Type")).trim(), "PASS");
			
			}
		skip = false;
		fail = false;

	}
	
	
	
	
	

	@AfterTest
	public void reportTestResult() throws Throwable {
		int rowNum = currentSuiteXls.getCellRowNum("Test Cases", "TCID", this.getClass().getSimpleName());
		currentSuiteXls.setCellData("Test Cases", "Last_Execution", rowNum, fnReturnDateWithTimeForExcel());

		// Add Results column to test case sheet
		if (isTestPass) {
			TestUtil.reportDataSetResult(currentSuiteXls, "Test Cases", TestUtil.getRowNum(currentSuiteXls, this.getClass().getSimpleName()), "PASS");
			currentSuiteXls.setCellData("Test Cases", "Last_Successful_Execution", rowNum, fnReturnDateWithTimeForExcel());
			//fnpElapsedTime(currentSuiteName, this.getClass().getSimpleName(), currentScriptCode, "PASS");
		} else {
			TestUtil.reportDataSetResult(currentSuiteXls, "Test Cases", TestUtil.getRowNum(currentSuiteXls, this.getClass().getSimpleName()), "FAIL");
			//fnpElapsedTime(currentSuiteName, this.getClass().getSimpleName(), currentScriptCode, "FAIL");
		}
		fnpMymsg("=========================================================================================================================================");

		hashXlData.clear();

		fnpCommonCloseBrowsersAndKillProcess();

	}
	




	// @AfterSuite
	@AfterTest
	public void closebrowser() throws InterruptedException {
		//driver.quit();
	//	IsBrowserPresentAlready = false;
		setIsBrowserPresentAlready_false();
		killprocess();

	}

}
