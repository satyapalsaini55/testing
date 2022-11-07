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

public class Draft_tossed_returned extends TestSuiteBase_EPSF {
	 //BS-02



	@BeforeTest
	public void checkTestSkip() throws Exception {
		count = -1;
		EPSF_BS_01 = new EPSF_Draft_Req_Recvd();
		EPSF_BS_01.checkTestSkip(this.getClass().getSimpleName());

	}
	



	//@Test(dataProvider = "getTestData")
	@Test(dataProviderClass = TestDataProvider.class,dataProvider = "Draft_tossed_returned")
	public void Draft_tossed_returned_flow(
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
			
			
			
			
			fnpPFList("EPSFStatusPFList", "EPSFStatusPFListOptions", (String) table.get("First_ChangeStatus"));			
			fnpClick_OR("EPSF_SaveBtn");			
			String current_epsfStatus=fnpGetText_OR("EPSF_Status");
			String current_epsfStatusListValue=fnpGetText_OR("EPSFStatusPFList");
			Assert.assertTrue(current_epsfStatus.equalsIgnoreCase((String)table.get("First_ChangeStatus")), "EPSF status not changed to '"+
					table.get("First_ChangeStatus")+"'.");
			Assert.assertTrue(current_epsfStatus.equalsIgnoreCase(current_epsfStatusListValue), " EPSF status are not same " +
					"at both places i.e. at top and in status list");
			fnpMymsg("EPSF status  changed to '"+table.get("First_ChangeStatus")+"'.");
			
			
			 
			fnpCommonEPSFStatusChangedPFList( table, (String) table.get("Requested_ChangeStatus"));
			
			fnpCommonEPSFStatusChangedPFList( table, (String) table.get("Received_ChangeStatus"));
			
			fnpCommonClickEPSFIssuesTab();
			String sampleReviewRequiredFlag=((String) table.get("SampleReviewRequired")).trim();
			fnpPFList("SampleReviewRequiredPFList", "SampleReviewRequiredPFListOptions",sampleReviewRequiredFlag);
		
			fnpClick_OR("EPSF_SaveBtn");
			
			//	fnpWaitForVisible("TopMessage3");
			fnpWaitForVisibleHavingMultipleElements("TopMessage3");
			fnpMymsg("Top Message after set flag "+(String) table.get("SampleReviewRequired")+
					"  in Issues Tab and click save button----" + fnpGetText_OR("TopMessage3"));

			String SuccessfulMsg = fnpGetText_OR("TopMessage3");

			Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"), "Top message does not contain 'success' word, so might be after set flag "+(String) table.get("SampleReviewRequired")+
					"  in Issues Tab and click save button.");

			
			fnpCommonClickEPSFInfoTab();
			
			
			fnpClick_OR("EPSF_CrJobBtn");
			//	fnpWaitForVisible("TopMessage3");
			fnpWaitForVisibleHavingMultipleElements("TopMessage3");
			fnpMymsg("Top Message after clicking Create Job button----" + fnpGetText_OR("TopMessage3"));
			 SuccessfulMsg = fnpGetText_OR("TopMessage3");
			Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"), "Top message does not contain 'success' word, after clicking Create Job button");
			
			Thread.sleep(15000);
			fnpClick_OR("EPSF_AutoLoginToBeakerBtn");
			
			fnpClickAutoLoginToBeakerBtn();
			try{
				//	fnpWaitForVisible("TopMessage3");
				fnpWaitForVisibleHavingMultipleElements("TopMessage3");
			}catch(Throwable t){				
				throw new Exception("Successful login message is not flashed after clicking 'Auto Login To Beaker' button");
			}
			fnpMymsg("Top Message after clicking Auto Login To Beaker button----" + fnpGetText_OR("TopMessage3"));
			 SuccessfulMsg = fnpGetText_OR("TopMessage3");
			Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"), "Top message does not contain 'success' word, after clicking Auto Login To Beaker");
			
			String current_epsfStatus = fnpGetText_OR("EPSF_Status");
			String current_epsfStatusListValue = fnpGetText_OR("EPSFStatusPFList");
			Assert.assertTrue(current_epsfStatus.equalsIgnoreCase((String)table.get("Third_ChangeStatus")), "EPSF status not changed to '"+
					table.get("Third_ChangeStatus")+"'.");	
			Assert.assertTrue(current_epsfStatus.equalsIgnoreCase(current_epsfStatusListValue), " EPSF status are not same " +
					"at both places i.e. at top and in status list");
			fnpMymsg("EPSF status  changed to '"+(String)table.get("Third_ChangeStatus")+"'.");
	
			
			
			
			
			fnpPFList("EPSFStatusPFList", "EPSFStatusPFListOptions", (String) table.get("Fourth_ChangeStatus"));			
			fnpClick_OR("EPSF_SaveBtn");
			current_epsfStatus=fnpGetText_OR("EPSF_Status");
			 current_epsfStatusListValue=fnpGetText_OR("EPSFStatusPFList");
			Assert.assertTrue(current_epsfStatus.equalsIgnoreCase((String)table.get("Fourth_ChangeStatus")), "EPSF status not changed to '"+
					table.get("Fourth_ChangeStatus")+"'.");	
			Assert.assertTrue(current_epsfStatus.equalsIgnoreCase(current_epsfStatusListValue), " EPSF status are not same " +
					"at both places i.e. at top and in status list");
			fnpMymsg("EPSF status  changed to '"+(String)table.get("Fourth_ChangeStatus")+"'.");
			
			
			fnpCommonEPSFStatusChangedPFList( table, (String) table.get("Fourth_ChangeStatus"));
			
			
			
			fnpPFList("EPSFStatusPFList", "EPSFStatusPFListOptions", (String) table.get("Fifth_ChangeStatus"));			
			fnpClick_OR("EPSF_SaveBtn");
			current_epsfStatus=fnpGetText_OR("EPSF_Status");
			 current_epsfStatusListValue=fnpGetText_OR("EPSFStatusPFList");
			Assert.assertTrue(current_epsfStatus.equalsIgnoreCase((String)table.get("Fifth_ChangeStatus")), "EPSF status not changed to '"+
					table.get("Fifth_ChangeStatus")+"'.");	
			Assert.assertTrue(current_epsfStatus.equalsIgnoreCase(current_epsfStatusListValue), " EPSF status are not same " +
					"at both places i.e. at top and in status list");
			fnpMymsg("EPSF status  changed to '"+(String)table.get("Fifth_ChangeStatus")+"'.");
			
			
			
			
			
		} catch (Throwable t) {

			
			fnpCommonFinalCatchBlock( t,"  Draft_tossed_returned_flow  method is failed . ","Draft_tossed_returned_flow") ;
			
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