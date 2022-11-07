package nsf.ecap.Proposals_suite;

import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import nsf.ecap.Health_Science_suite.TestSuiteBase_HealthScience;
import nsf.ecap.Work_Order_suite.TestSuiteBase;
import nsf.ecap.util.ErrorUtil;
import nsf.ecap.util.TestDataProvider;
import nsf.ecap.util.TestUtil;

//import org.ini4j.Config;
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

public class Proposal_Won extends TestSuiteBase_Proposal {

	// Runmode of test case in a suite
	@BeforeTest
	@Parameters({ "className" })
	public void checkTestSkip(
								String className) throws Throwable {

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

			if (!TestUtil.isTestCaseRunnable(Proposals_suitexls, className)) {
				fnpMymsg("Skipping Test Case" + className + " as runmode set to NO");// logs
				throw new SkipException("Skipping Test Case '" + className + "' as runmode set to NO");// reports
			}

	
			
			fnpMymsg("Going to Run test cases of '" + classNameText + "'");
			fnpMymsg("             ");
			fnpMymsg("             ");

			runmodes = TestUtil.getDataSetRunmodes(Proposals_suitexls, className);
			fail = false;
			isTestPass = true;

			
			start = new Date();
			
			
/*			if (!IsBrowserPresentAlready) {
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
			fnpCommonFinalCatchBlock(t, "  checkTestSkip method  is fail . ", "checkTestSkipFail");

		}
		
		
		
	}







	//@Test(dataProvider = "getTestData")
	@Test(dataProviderClass = TestDataProvider.class,dataProvider = "WonDataProvider")
	public void Proposal_flow(
								Hashtable<String, String> table) throws Throwable {

		fnpMymsg("****************************************************************");
		count++;
		if (!runmodes[count].equalsIgnoreCase("Y")) {
			skip = true;
			fnpMymsg("Runmode for test set data set to no " + (count + 1));
			fnpMymsg("*****************");
			fnpMymsg("");
			throw new SkipException("Runmode for test set data set to no " + (count + 1));
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
			//fnpCommonProcessQuestionnairesSet_ISR( );
			

			
			
			
			fnpCommonCodeProposalFromCheckingUnansweredToPreviewQuoteLinkDisabled( table);
			
			
			/****** New code ******************/
			fnpClick_OR_WithoutWait("Prop_CorrespondenceTab");
			
			fnpWaitForVisible("Prop_ProposalCorrespondence_Heading");
			int rowCount = fnpCountRowsInTable("Prop_Corresondence_table");
			fnpMymsg("  ----Email has been  listed under the Proposal Correspondence table after Quote" +
					" Generation Confirmation are----"+rowCount);
			if (rowCount == 1) {
				fnpMymsg("  An Email has been  listed under the Proposal Correspondence table after Quote Generation Confirmation.");

			} else {
				if(rowCount == 0){
				fnpMymsg(" An Email has NOT been  listed under the Proposal Correspondence table after Quote Generation Confirmation.");
				throw new Exception("  An Email has NOT been  listed under the Proposal Correspondence table after Quote Generation Confirmation.");
				}else if(rowCount >1){
					fnpMymsg(" Multiple Emails has  been  listed under the Proposal Correspondence table after Quote Generation Confirmation.");
					throw new Exception(" Multiple Emails has  been  listed under the Proposal Correspondence table after Quote Generation Confirmation.");
					}
			}	
			
			fnpClick_OR_WithoutWait("Prop_ProposalDetailsTab");
		//	fnpWaitForVisible("Prop_StatusList");
			/****** New code ******************/
			
			
			
			fnpClick_OR("Prop_ActiveQuoteDetails_EmailLink");

			fnpPFList("Prop_EmailTemplate_PFList", "Prop_EmailTemplate_PFListOptions", table.get("Email_Template"));

			fnpClick_OR("Prop_EmailTemplateContinueBtn");


			fnpWaitForVisible("Prop_EmailToTxt");

			fnpGetORObjectX("Prop_EmailFromTxt").clear();

			fnpType("OR","Prop_EmailFromTxt",table.get("From_emailCorrespondence") );

			fnpGetORObjectX("Prop_EmailToTxt").clear();

			fnpType("OR","Prop_EmailToTxt",table.get("To_emailCorrespondence") );
			
			fnpClickInDialog_OR("Prop_CorresEmailSendBtn");
			


			fnpClick_OR_WithoutWait("Prop_CorrespondenceTab");
			
			fnpWaitForVisible("Prop_ProposalCorrespondence_Heading");
			 rowCount = fnpCountRowsInTable("Prop_Corresondence_table");

			//Earlier only 1 rows was coming but now 2 data rows are coming
			// (rowCount==1)
			if (rowCount == 2) {
				fnpMymsg(" Correspondence' 2 email has been generated.");

			} else {
				fnpMymsg(" Correspondence'2 email has NOT been generated.");
				throw new Exception(" Correspondence'2 email has NOT been generated.");

			}

			if (fnpCheckElementPresenceImmediately("Prop_Corres_MessageIcon")) {
				fnpMymsg(" Option Column has message Icon present");

			} else {
				fnpMymsg(" Option Column has NOT message Icon present");
				throw new Exception(" Option Column has NOT message Icon present.");
			}

			
			
			
			
			
			fnpCommonProposalDetailsTabWon(table.get("Change_status"), table.get("BU_Manager_Code"), table.get("DocTab_AddPropDoc_FileName"), table.get("Type"), table.get("Access"));


		
	} catch (Throwable t) {
		
		fnpCommonFinalCatchBlock(t, "  Proposal_flow  is fail . ", "Proposal_flowFail");
		IsBrowserPresentAlready = false; // This is for special reason if one case is fail due to application error or any other reason then it close the
		// browser and login again.
		//driver.quit();
	
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
		//driver.quit();
	//	IsBrowserPresentAlready = false;
		setIsBrowserPresentAlready_false();
		killprocess();

	}





/*	@DataProvider
	public Object[][] getTestData() {
		return TestUtil.getData(Proposals_suitexls, this.getClass().getSimpleName());
	}*/
}
