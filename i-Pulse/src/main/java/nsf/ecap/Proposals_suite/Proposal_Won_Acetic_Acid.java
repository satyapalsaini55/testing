package nsf.ecap.Proposals_suite;

import java.util.Iterator;
import java.util.List;

import nsf.ecap.Work_Order_suite.TestSuiteBase;
import nsf.ecap.util.ErrorUtil;
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

public class Proposal_Won_Acetic_Acid extends TestSuiteBase_Proposal {

	// Runmode of test case in a suite
	@BeforeTest
	public void checkTestSkip() throws Throwable {
		count = -1;

		BS_P01 = new Proposal_Won();
		BS_P01.checkTestSkip(this.getClass().getSimpleName());

	}







	@Test(dataProvider = "getTestData")
	public void Proposal_flow(
								String S_No,
								String Client,
								String Work_Order_Type,
								String Financial_Program,
								String Product_Type,
								String Billing_schedule,
								String standardVersion,
								String Scope,
								String TAT_time_days,
								
								String QuestionNAnswerSets,	
								String AnnualAmount,
								String Outputs,
								String Change_status,
								String BU_Manager_Code,
								String DocTab_AddPropDoc_FileName,
								String Type,
								String Access) {

		fnpMymsg("****************************************************************");
		count++;
		if (!runmodes[count].equalsIgnoreCase("Y")) {
			skip = true;
			fnpMymsg("Runmode for test  data set  no. " + (count + 1)+"is -- N");
			fnpMymsg("*****************");
			fnpMymsg("");
			throw new SkipException("Runmode for test  data set  no. " + (count + 1)+"is -- N");
		}

		try {

			if (!IsBrowserPresentAlready) {
				IsBrowserPresentAlready = fnpLaunchBrowserAndLogin();
				fnpMymsg(" Browser is launched");
			}

//			fnpCommonProposalCodePart2TillClickEditQuestLink(count, Client, Work_Order_Type, Financial_Program, Product_Type, Billing_schedule,standardVersion, Scope, TAT_time_days);


			fnpMymsg("");
			fnpMymsg(" Now going to insert the values in Questionnaire .");
			String NoOfSets[] = QuestionNAnswerSets.split("::");
			fnpMymsg("No. of question sets are ---" + NoOfSets.length);
			fnpMymsg("");
			fnpMymsg("");

			for (int j = 0; j < NoOfSets.length; j++) {
				if (NoOfSets.length > 1 && j < (NoOfSets.length - 1)) {
					// Thread.sleep(1000);
					fnpWaitForVisible("Prop_Questionnaire_SaveNNextBtn");
				}
				fnpMymsg("***********************************************");
				String QuestionsSet = NoOfSets[j];
				fnpMymsg("Question set is--" + QuestionsSet);
				String NoOfQuestionsInCurrentSet[] = QuestionsSet.split(":");

				fnpMymsg("No. of questions are ---" + NoOfQuestionsInCurrentSet.length);
				fnpMymsg("");
				fnpMymsg("");
				for (int i = 0; i < NoOfQuestionsInCurrentSet.length; i++) {
					fnpMymsg("");
					fnpMymsg("");
					fnpMymsg("Each question and its value are--" + NoOfQuestionsInCurrentSet[i]);
					String eachQuestion[] = NoOfQuestionsInCurrentSet[i].split(",");
					String QuestionNo = fnpremoveFormatting(eachQuestion[0]);
					fnpMymsg("Question no. is--" + QuestionNo);

					String AnswerValue = fnpremoveFormatting(eachQuestion[1]);
					fnpMymsg("Its answer is--" + AnswerValue);

					fnpFillAnswerToPropQuestionnaireDynamically(QuestionNo, AnswerValue);

					Thread.sleep(2000);

				}

				if (NoOfSets.length > 1 && j < (NoOfSets.length - 1)) {
					Thread.sleep(1000);

					WebElement saveNnextBtn = fnpGetORObjectX("Prop_Questionnaire_SaveNNextBtn");
					Actions action = new Actions(driver);
					action.moveToElement(saveNnextBtn).build().perform();
					saveNnextBtn.sendKeys(Keys.ARROW_UP);
					saveNnextBtn.sendKeys(Keys.ARROW_UP);
					saveNnextBtn.sendKeys(Keys.ARROW_UP);
				//	fnpClick_OR("Prop_Questionnaire_SaveNNextBtn");
					fnpClickInDialog_OR("Prop_Questionnaire_SaveNNextBtn");
					// Thread.sleep(8000);
					//Thread.sleep(2000);
				}

			}

			// Thread.sleep(5000);
/*
			fnpClick_OR("Prop_Questionnaire_SaveNCloseBtn");
			fnpLoading_wait();
			// Thread.sleep(4000);
			fnpLoading_wait();
	*/		
			
			fnpClickInDialog_OR("Prop_Questionnaire_SaveNCloseBtn");
			
/*
			if (fnpCheckElementPresenceImmediately("ErrorMessage")) {
			//	fnpMouseHover("ErrorMessage");
				fnpMouseHover_OR_EvenMultipleObjectsHavingSameXpath("ErrorMessage");
				fnpMymsg(" Error is thrown by application while saving  Questionnaire");
				throw new Exception("Error is thrown by application while saving  Questionnaire");
			}*/

			fnpCheckError("Error is thrown by application while saving  Questionnaire");
			
/*			
			//########## For time  being
			driver.navigate().refresh();
			fnpLoading_wait();
			
			//########## For time  being
			
	*/		
			
			fnpWaitForVisible("QuestionnaireDetailsTable");
			int colIndex = fnpFindColumnIndex("QuestionnaireDetailsTable_header", "Un Answered Questions");
			String unAnsweredQuestNo = fnpFetchFromTable("QuestionnaireDetailsTable", 1, colIndex);

			if (Integer.parseInt(unAnsweredQuestNo) > 0) {
				Thread.sleep(5000);
				unAnsweredQuestNo = fnpFetchFromTable("QuestionnaireDetailsTable", 1, colIndex);
			}
			Assert.assertEquals(unAnsweredQuestNo, "0", "Unanswered Questions are not equal to '0'. ");

			fnpClick_OR("Prop_PreviewQuoteLink");
		//	Thread.sleep(1000);

		//	Thread.sleep(5000);
			fnpWaitForVisible("Prop_GenerateQuoteBtn");

		//	fnpMymsg("Pradeep ---annual amount--"+AnnualAmount);
			fnpType("OR","Prop_AnnualCost_txt",AnnualAmount );
			
			fnpMymsg("");
			fnpMymsg("");
			fnpMymsg(" Now going to Verifying the outputs .");
			fnpMymsg("");
			fnpMymsg("Output Value set is--" + Outputs);
			String NoOfOutputValues[] = Outputs.split(":");

			fnpMymsg("No. of output value sets are ---" + NoOfOutputValues.length);
			fnpMymsg("");
			fnpMymsg("");
			for (int i = 0; i < NoOfOutputValues.length; i++) {
				fnpMymsg("Each output set  value are--" + NoOfOutputValues[i]);
				String eachOutput[] = NoOfOutputValues[i].split(",");
				String taskDescription = fnpremoveFormatting(eachOutput[0]);
				fnpMymsg("Task Description is--" + taskDescription);

				String billCode = fnpremoveFormatting(eachOutput[1]);
				fnpMymsg("Expected Bill code is--" + billCode);

				String quantity = fnpremoveFormatting(eachOutput[2]);
				fnpMymsg("Expected Quantity is--" + quantity);
				fnpVerifyingOutputProp(taskDescription, billCode, quantity);
				fnpMymsg("");
				fnpMymsg("");

			}

		//	fnpClick_OR("Prop_GenerateQuoteBtn");
			fnpClickInDialog_OR("Prop_GenerateQuoteBtn");
			fnpLoadingGeneratingQuote();
/*
			fnpLoading_wait();
		//	Thread.sleep(5000);
			fnpLoading_wait();
*/
			
			String SuccessfulMsg = fnpGetText_OR("TopMessage3");
			int efilecounter=0;
			//while(!SuccessfulMsg.equalsIgnoreCase("Quote generated successfully and report is saved to eFileRoom")){
			while(!SuccessfulMsg.contains("eFileRoom")){
				Thread.sleep(1000);
				efilecounter=efilecounter+1;
				if (efilecounter>Integer.parseInt(CONFIG.getProperty("implicitWaitTime"))) {
					
					fnpMymsg("Expected message is not coming even after 60 seconds i.e. 'Quote generated successfully and report is saved to eFileRoom' .");
					throw new Exception("Expected message is not coming even after 60 seconds i.e. 'Quote generated successfully and report is saved to eFileRoom' .");
				}

/*				if (fnpCheckElementPresenceImmediately("ErrorMessage")) {
					//fnpMouseHover("ErrorMessage");
					fnpMouseHover_OR_EvenMultipleObjectsHavingSameXpath("ErrorMessage");
					fnpMymsg(" Error is thrown by application while saving  preview quote");
					throw new Exception("Error is thrown by application while saving  preview quote");
				}
				*/
				fnpCheckError("Error is thrown by application while saving  preview quote");
				
				
				SuccessfulMsg = fnpGetText_OR("TopMessage3");
			}
			
			
			
			
/*			if (fnpCheckElementPresenceImmediately("ErrorMessage")) {
				//fnpMouseHover("ErrorMessage");
				fnpMouseHover_OR_EvenMultipleObjectsHavingSameXpath("ErrorMessage");
				fnpMymsg(" Error is thrown by application while saving  preview quote");
				throw new Exception("Error is thrown by application while saving  preview quote");
			}*/
			
			fnpCheckError("Error is thrown by application while saving  preview quote");

		//	fnpWaitForVisible("TopMessage3");
			fnpMouseHover("Prop_StatusList");

			 SuccessfulMsg = fnpGetText_OR("TopMessage3");
			fnpMymsg("Top Message after saving  preview quote ----" + SuccessfulMsg);

			Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"), "Top message does not contain 'success' word, so might be preview quote has not been saved successfully");

			Assert.assertTrue(
					SuccessfulMsg.equalsIgnoreCase("Quote generated successfully and report is saved to eFileRoom"),
					"Top message does not showing 'Quote generated successfully and report is saved to eFileRoom' message, so might be preview quote has not been saved successfully");

			fnpCommonProposalDetailsTabWon(Change_status, BU_Manager_Code, DocTab_AddPropDoc_FileName, Type, Access);

		} catch (Throwable t) {

			fail = true;
			isTestPass = false;
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

	}







	@AfterMethod
	public void reportDataSetResult() {

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
		

	}







	// @AfterSuite
	@AfterTest
	public void closebrowser() throws InterruptedException {
		driver.quit();
		//IsBrowserPresentAlready = false;
		setIsBrowserPresentAlready_false();
		killprocess();

	}







	@DataProvider
	public Object[][] getTestData() {
		return TestUtil.getData(Proposals_suitexls, this.getClass().getSimpleName());
	}

}
