package nsf.ecap.Proposals_suite;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import nsf.ecap.Work_Order_suite.TestSuiteBase;
import nsf.ecap.util.ErrorUtil;
import nsf.ecap.util.TestDataProvider;
import nsf.ecap.util.TestUtil;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

public class Proposal_Lost extends TestSuiteBase_Proposal {

	// Runmode of test case in a suite
	@BeforeTest
	public void checkTestSkip() throws Throwable {
		count = -1;
		BS_P01 = new Proposal_Won();
		BS_P01.checkTestSkip(this.getClass().getSimpleName());

	}







//	@Test(dataProvider = "getTestData")
	@Test(dataProviderClass = TestDataProvider.class,dataProvider = "LostDataProvider")
	public void Proposal_flow(Hashtable <String,String>table) throws Throwable {
		// test the runmode of current dataset

		fnpMymsg("****************************************************************");
		count++;
		if (!runmodes[count].equalsIgnoreCase("Y")) {
			skip = true;
			fnpMymsg("Runmode for test  data set  no. " + (count + 1)+"is -- N");
			fnpMymsg("*****************");
			fnpMymsg("");
			throw new SkipException("Runmode for test  data set  no. " + (count + 1)+"is -- N");
		}else{
			skip = false;
		}

		try {
			
			if (!IsBrowserPresentAlready) {
				IsBrowserPresentAlready = fnpLaunchBrowserAndLogin();
				fnpMymsg(" Browser is launched");
			}

			
			fnpCommonProposalCodePart2TillClickEditQuestLink_updated(table);
			
			fnpCommonProcessQuestionnairesSet(table.get("QuestionNAnswerSets"));
			
			
			
			fnpCommonCodeProposalFromCheckingUnansweredToPreviewQuoteLinkDisabled( table);
			
			
			
			fnpClick_OR("Prop_ProposalDetailsTab");

			fnpWaitForVisible("Prop_StatusList");
			fnpPFList("Prop_StatusList", "Prop_StatusListOptions", table.get("Change_status"));
	
			fnpClick_OR("Prop_SaveBtn");
			

			fnpCheckErrMsg("Error is thrown by application after changing status to 'LOST' in Status list  ");

			//	fnpWaitForVisible("TopMessage3");
			fnpWaitForVisibleHavingMultipleElements("TopMessage3");
			String SuccessfulMsg = fnpGetText_OR("TopMessage3");
			fnpMymsg("Top Message after changing status to 'LOST' in Status list  ----" + SuccessfulMsg);

			Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"), "Top message does not contain 'success' word, so might be status has not been changed/updated to 'LOST'successfully.");

			String status = fnpGetORObjectX("Prop_StatusList").getText();
			Assert.assertTrue(status.equalsIgnoreCase(table.get("Change_status")), "Proposal Status is not become to 'LOST' .");
			fnpMymsg("Proposal status has become to 'LOST' now.");

			fnpMymsg("****************************************************************");
/*
		} catch (Throwable t) {
			fail = true;
			fnpMymsg("  Proposal_flow  method is failed . " + " . Error is ---" + t.getMessage());
			fnpDesireScreenshotProposal("Proposal_flow_Failed" + (SShots + 1));
			String stackTrace = Throwables.getStackTraceAsString(t);
			String errorMsg = t.getMessage();
			errorMsg = errorMsg + "\n\n\n\n Proposal_flow  method is failed   .See screenshot 'Proposal_flow_Failed" + (SShots + 1) + "'\n\n" + stackTrace;
		
			

			IsBrowserPresentAlready = false; // This is for special reason if one case is fail due to application error or any other reason then it close the
												// browser and login again.
			driver.quit();

	
			
			
			Exception c = new Exception(errorMsg);
			ErrorUtil.addVerificationFailure(c);

		}
		
		
		*/
		} catch (Throwable t) {
				
				fnpCommonFinalCatchBlock(t, "  Proposal_flow  is fail . ", "Proposal_flowFail");
				IsBrowserPresentAlready = false; // This is for special reason if one case is fail due to application error or any other reason then it close the
				// browser and login again.
			//	driver.quit();
			
			}

	}







	@AfterMethod
	public void reportDataSetResult() throws Throwable {
		
		fnpCommonCloseBrowsersAndKillProcess();
		
		
		if (skip)
			TestUtil.reportDataSetResult(Proposals_suitexls, this.getClass().getSimpleName(), count + 2, "SKIP");
		else if (fail) {
			isTestPass = false;
			TestUtil.reportDataSetResult(Proposals_suitexls, this.getClass().getSimpleName(), count + 2, "FAIL");
		} else
			TestUtil.reportDataSetResult(Proposals_suitexls, this.getClass().getSimpleName(), count + 2, "PASS");

		skip = false;
		fail = false;

	}







	@AfterTest
	public void reportTestResult() throws Throwable {
		int rowNum=Proposals_suitexls.getCellRowNum("Test Cases", "TCID", this.getClass().getSimpleName());
		Proposals_suitexls.setCellData("Test Cases", "Last_Execution", rowNum, fnReturnDateWithTimeForExcel() );
		
		if (isTestPass){
			TestUtil.reportDataSetResult(Proposals_suitexls, "Test Cases", TestUtil.getRowNum(Proposals_suitexls, this.getClass().getSimpleName()), "PASS");
			Proposals_suitexls.setCellData("Test Cases", "Last_Successful_Execution", rowNum, fnReturnDateWithTimeForExcel() );
			fnpElapsedTime(currentSuiteName, this.getClass().getSimpleName(), currentScriptCode, "PASS");
		}else{
			TestUtil.reportDataSetResult(Proposals_suitexls, "Test Cases", TestUtil.getRowNum(Proposals_suitexls, this.getClass().getSimpleName()), "FAIL");
			fnpElapsedTime(currentSuiteName, this.getClass().getSimpleName(), currentScriptCode, "FAIL");
		}
		

		
		fnpCommonCloseBrowsersAndKillProcess();
		
	}







	// @AfterSuite
	@AfterTest
	public void closebrowser() throws InterruptedException {
	//	driver.quit();
		//IsBrowserPresentAlready = false;
		setIsBrowserPresentAlready_false();

		killprocess();

	}






/*
	@DataProvider
	public Object[][] getTestData() {
		return TestUtil.getData(Proposals_suitexls, this.getClass().getSimpleName());
	}
*/	
	
/*	@DataProvider
	public Object[][] getTestData() {
		//return TestUtil.getData(Proposals_suitexls, this.getClass().getSimpleName());
		return TestUtil.getDataInHash(Proposals_suitexls, this.getClass().getSimpleName());
	}*/

}
