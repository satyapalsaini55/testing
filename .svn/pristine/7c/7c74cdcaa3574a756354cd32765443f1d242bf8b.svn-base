package nsf.ecap.Proposals_suite;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import nsf.ecap.util.ErrorUtil;
import nsf.ecap.util.TestDataProvider;
import nsf.ecap.util.TestUtil;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;

public class Proposal_RequoteWon extends TestSuiteBase_Proposal {

	// Runmode of test case in a suite
	@BeforeTest
	public void checkTestSkip() throws Throwable {
		count = -1;
		BS_P01 = new Proposal_Won();
		BS_P01.checkTestSkip(this.getClass().getSimpleName());

	}







	//@Test(dataProvider = "getTestData")
	@Test(dataProviderClass = TestDataProvider.class,dataProvider = "RequoteWonDataProvider")
	public void Proposal_flow(Hashtable <String,String>table)
 {

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

/*			fnpCommonProposalCode(
					count,
					table.get("Client"),
					table.get("Work_Order_Type"),
					table.get("Financial_Program"),
					table.get("Product_Type"),
					table.get("Billing_schedule"),
					table.get("Standard_Version"),
					table.get("Scope"),
					table.get("TAT_time_days"),
					table.get("Radio_Button_Choices"),
					table.get("Same_Answers_To_All"),
					table.get("Annual_Cost"),
					table.get("Rush_Fees"),
					table.get("Discount_Percent"),
					table.get("Discount_Reason")
					);
			
			*/
			fnpCommonProposalCodePart2TillClickEditQuestLink_updated(table);

			fnpWaitForVisible("Prop_ProposalNoFromTop");
			String proposalNo = fnpGetORObjectX("Prop_ProposalNoFromTop").getText();

			String[] changeStatusArray = table.get("Change_status").split(",");

			fnpWaitForVisible("Prop_StatusList");
			fnpPFList("Prop_StatusList", "Prop_StatusListOptions", changeStatusArray[0]);

			fnpGetORObjectX("Prop_SaveBtn").click();
			
/*			
			// Thread.sleep(1000);
			fnpLoading_wait();
			// fnpLoading_wait();
			fnpWaitForVisible("ErrorMessage");
			
	*/		
			
			fnpWaitingForExpectedErrorMsg("contains 'reason' word ");
			
			
			
			if (fnpCheckElementPresenceImmediately("ErrorMessage")) {
			//	fnpMouseHover("ErrorMessage");
				fnpMouseHover_OR_EvenMultipleObjectsHavingSameXpath("ErrorMessage");
				fnpMymsg("Expected Error is thrown by application while saving Proposal when change the status to ReQuote . ");
				String ErrorMsg = fnpGetText_OR_EvenMultipleObjectsHavingSameXpath_specialForExtractingErrorMsgTextOnlyEvenIfNotVisible("ErrorMessage");;
				fnpMymsg("Expected Error message is :--" + ErrorMsg);
				Assert.assertTrue(ErrorMsg.contains("reason"), "Expected Error is thrown by application while saving the changed status 'ReQuote'.");
			} else {
				fnpMymsg(" Expected error message contains 'reason' word is not getting flashed.");
				throw new Exception(" Expected error message contains 'reason' word is not getting flashed.");
			}

		//	fnpPFList("Prop_StatusList", "Prop_StatusListOptions", changeStatusArray[0]);
			
			
			/*********Special Case without using loading in fnpPFList***************/
			fnpGetORObjectX("Prop_StatusList").click();
			Thread.sleep(500);
			String listValue = OR.getProperty("Prop_StatusListOptions") + "/div/ul/li[contains(@data-label,'" + changeStatusArray[0] + "')]";

			fnpWaitTillVisiblityOfElementNClickable(listValue);
			driver.findElement(By.xpath(listValue)).click();
					
			/*********Special Case without using loading in fnpPFList***************/
			
			
			
			
			
			
			
			
			Thread.sleep(2000);
		//	fnpGetORObjectX("Prop_StatusChangeReason").sendKeys(table.get("Status_Change_Reason"));
			fnpType("OR","Prop_StatusChangeReason",table.get("Status_Change_Reason") );
			

/*			

			fnpGetORObjectX("Prop_SaveBtn").click();
			fnpLoading_wait();
			Thread.sleep(4000);
			fnpLoading_wait();
			fnpLoading_wait();

		*/	
			/****Because error is still present on the screen *****/
		//	fnpClick_OR("Prop_SaveBtn");
			fnpGetORObjectX("Prop_SaveBtn").click();
			fnpLoading_wait();
			
/*			if (fnpCheckElementPresenceImmediately("ErrorMessage")) {
			//	fnpMouseHover("ErrorMessage");
				fnpMouseHover_OR_EvenMultipleObjectsHavingSameXpath("ErrorMessage");
				fnpMymsg(" Error is thrown by application while saving Proposal");
				throw new Exception("Error is thrown by application while saving Proposal");
			}
			*/
			fnpCheckError("Error is thrown by application while saving Proposal");

			//	fnpWaitForVisible("TopMessage3");
			fnpWaitForVisibleHavingMultipleElements("TopMessage3");
			String SuccessfulMsg = fnpGetText_OR("TopMessage3");
			fnpMymsg("Top Message after create ----" + SuccessfulMsg);

			Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"), "Top message does not contain 'success' word, so might be Proposal has not been saved successfully");

			fnpClick_OR("Prop_PDetails_QuestionnaireEditLink");
			Thread.sleep(1000);
			String value;
			String radioSequence = table.get("Radio_Button_Rechoices");
			String[] radioArray = radioSequence.split(",");
			int radioCount = radioArray.length;

			int pos = 1;
			for (int i = 0; i < radioArray.length; i++) {
				pos = pos + i;

				value = radioArray[i];

				if (value.equalsIgnoreCase("No")) {
					fnpCallFunctionClickNoInProposal(pos);

				}

				Thread.sleep(2000);
				// Thread.sleep(1000);

				if (value.equalsIgnoreCase("Yes")) {
					fnpCallFunctionClickYesInProposal(pos);

				}

			}

			// Thread.sleep(2500);
			Thread.sleep(1000);

			String xpathAnswerTxtBox = ".//input[contains(@id,'mainForm:tabView:ans') and @type='text']";
			List<WebElement> ansTextBoxes = driver.findElements(By.xpath(xpathAnswerTxtBox));

			String[] answerArray = table.get("Re_Answers_To_All").split(",");
			int answerArrayLength = answerArray.length;
			System.out.println("size of Array answerArrayLength is :" + answerArrayLength);
			int ansArrayIndex = 0;
			for (Iterator iterator = ansTextBoxes.iterator(); iterator.hasNext();) {

				WebElement webElement = (WebElement) iterator.next();
				if (!answerArray[ansArrayIndex].trim().isEmpty()) {
					webElement.clear();
					webElement.sendKeys(answerArray[ansArrayIndex]);
					// Thread.sleep(1000);
					Thread.sleep(500);
					// fnpLoading_wait();
					// fnpLoading_wait();

				}

				if (answerArrayLength != 1) {
					ansArrayIndex = ansArrayIndex + 1;
				}

			}
			
/*			fnpClick_OR("Prop_Questionnaire_SaveNCloseBtn");
			fnpLoading_wait();
			Thread.sleep(2000);
			fnpLoading_wait();
			
*/			
			fnpClickInDialog_OR("Prop_Questionnaire_SaveNCloseBtn");
			

/*			if (fnpCheckElementPresenceImmediately("ErrorMessage")) {
				//fnpMouseHover("ErrorMessage");
				fnpMouseHover_OR_EvenMultipleObjectsHavingSameXpath("ErrorMessage");
				fnpMymsg(" Error is thrown by application while saving  Questionnaire");
				throw new Exception("Error is thrown by application while saving  Questionnaire");
			}
			*/
			fnpCheckError("Error is thrown by application while saving  Questionnaire");

			fnpClick_OR("Prop_PreviewQuoteLink");
		//	Thread.sleep(1000);
			
/*			fnpClick_OR("Prop_GenerateQuoteBtn");			
			fnpLoading_wait();
		//	Thread.sleep(5000);
			fnpLoading_wait();
*/			
			
			fnpMymsg("Pradeep---Annual Amount is ---"+table.get("Annual_Cost"));
			fnpType("OR","Prop_AnnualCost_txt",table.get("Annual_Cost") );
			fnpClickInDialog_OR("Prop_GenerateQuoteBtn");
			fnpLoadingGeneratingQuote();
			
			SuccessfulMsg = fnpGetText_OR("TopMessage3");
			int efilecounter=0;
			//while(!SuccessfulMsg.equalsIgnoreCase("Quote generated successfully and report is saved to eFileRoom")){
			while(!SuccessfulMsg.contains("eFileRoom")){
				Thread.sleep(1000);
				efilecounter=efilecounter+1;
				if (efilecounter>Integer.parseInt(CONFIG.getProperty("implicitWaitTime"))) {
					
					fnpMymsg("Expected message is not coming even after 60 seconds i.e. 'Quote generated successfully and report is saved to eFileRoom' .");
					fnpMymsg("current  message is ---- '"+SuccessfulMsg+"' ");
					throw new Exception("Expected message is not coming even after 60 seconds i.e. 'Quote generated successfully and report is saved to eFileRoom' .");
				}

	/*			if (fnpCheckElementPresenceImmediately("ErrorMessage")) {
				//	fnpMouseHover("ErrorMessage");
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
			}
			*/
			fnpCheckError("Error is thrown by application while saving  preview quote");

		//	fnpWaitForVisible("TopMessage3");
			fnpMouseHover("Prop_StatusList");

			SuccessfulMsg = fnpGetText_OR("TopMessage3");
			fnpMymsg("Top Message after saving  preview quote ----" + SuccessfulMsg);

			Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"), "Top message does not contain 'success' word, so might be preview quote has not been saved successfully");

			Assert.assertTrue(
					SuccessfulMsg.equalsIgnoreCase("Quote generated successfully and report is saved to eFileRoom"),
					"Top message does not showing 'Quote generated successfully and report is saved to eFileRoom' message, so might be preview quote has not been saved successfully");

			String status = fnpGetORObjectX("Prop_StatusList").getText();
			Assert.assertTrue(status.equalsIgnoreCase("Quoted"), "Status is not become to 'Quoted' .");

			if (fnpCheckElementPresenceImmediately("Prop_PDetails_QuestionnaireEditLink")) {
				fnpMymsg(" Link on Questionare Edit text is still present and it should not be a link now.");
				throw new Exception(" Link on Questionare Edit text is still present and it should not be a link now.");
			}
			fnpMymsg(" Link on Questionare Edit text is no longer present now.");

			if (fnpCheckElementPresenceImmediately("Prop_PreviewQuoteLink")) {
				fnpMymsg(" Link on Preview Quote text is still present and it should not be a link now.");
				throw new Exception(" Link on Preview Quote text is still present and it should not be a link now.");
			} else {
				fnpMymsg(" Link on Preview Quote text is no longer present now.");
			}

			fnpWaitForVisible("Prop_InactiveQuoteNo");
			String InactiveQuoteNo = fnpGetORObjectX("Prop_InactiveQuoteNo").getText();
			fnpMymsg("Inactive Quote No. is  ----" + InactiveQuoteNo);
			Assert.assertTrue(InactiveQuoteNo.endsWith("-1"), "Inactive Quote no. does not appended by 1 .");
			Assert.assertTrue(InactiveQuoteNo.equalsIgnoreCase(proposalNo + "-1"), "Inactive Quote no. does not appended by 1 .");
			fnpMymsg("Inactive Quote No. is appeded by '-1'");

			fnpWaitForVisible("Prop_ActiveQuoteNo");
			String ActiveQuoteNo = fnpGetORObjectX("Prop_ActiveQuoteNo").getText();
			fnpMymsg("Active Quote No. is  ----" + ActiveQuoteNo);
			Assert.assertTrue(ActiveQuoteNo.endsWith("-2"), "Active Quote no. does not appended by 2 .");
			Assert.assertTrue(ActiveQuoteNo.equalsIgnoreCase(proposalNo + "-2"), "Active Quote no. does not appended by 2 .");
			fnpMymsg("Active Quote No. is appeded by '-2'");

			fnpWaitForVisible("Prop_StatusList");
			fnpPFList("Prop_StatusList", "Prop_StatusListOptions", changeStatusArray[1]);

			fnpGetORObjectX("Prop_SaveBtn").click();
			
/*			
			// Thread.sleep(1000);
			fnpLoading_wait();
			// fnpLoading_wait();
			fnpWaitForVisible("ErrorMessage");
			
	*/		
		
			
			
/*			
			
			fnpWaitingForExpectedErrorMsg("Please select BU Manager");
			
			if (fnpCheckElementPresenceImmediately("ErrorMessage")) {
				fnpMouseHover("ErrorMessage");
				fnpMymsg("Expected Error is thrown by application while saving Proposal after changing the status to WON before filling BU Manager. ");
				String ErrorMsg = fnpGetORObjectX("ErrorMessage").getText();
				Assert.assertTrue(ErrorMsg.contains("Please select BU Manager"), "Expected error message does not contain 'Please select BU Manager' word, so might be some error.");
			} else {
				fnpMymsg(" Expected error message 'Please select BU Manager' is not flashed.");
				throw new Exception(" Expected error message 'Please select BU Manager' is not flashed.");
			}

			fnpWaitTillVisiblityOfElementNClickable_OR("Prop_ClientInfoTab");
			fnpGetORObjectX("Prop_ClientInfoTab").click();
			//Thread.sleep(5000);
			fnpWaitingForElementWithoutUsingLoading("Prop_BUManagerLookupBtn");
			
			
			fnpClick_OR("Prop_BUManagerLookupBtn");

			fnpSearchNSelectFirstRadioBtn(1, table.get("BU_Manager_Code"), 1);
			// Thread.sleep(1000);
			fnpClick_OR("Prop_SaveBtn");
		//	fnpLoading_wait();

		*/	
			
			
			fnpCheckErrorUsingPageSource();
			
		//	fnpClick_OR("Prop_DocumentsTab");
			fnpClick_OR_WithoutWait("Prop_DocumentsTab");

			fnpClick_OR("Prop_PropDoc_AddBtn");
			fnpWaitForVisible("DocTab_AddWODoc_SaveNCloseBtn");
			fnpWaitTillVisiblityOfElementNClickable_OR("DocTab_AddWODoc_SaveNCloseBtn");
			String fileName = System.getProperty("user.dir") + "\\docs\\" + table.get("DocTab_AddPropDoc_FileName");
		//	fnpGetORObjectX("Prop_PropDoc_BrowseBtn").sendKeys(fileName);
			driver.findElement(By.xpath(OR.getProperty("Prop_PropDoc_BrowseBtn"))).sendKeys(fileName);
			Thread.sleep(1000);
			while (fnpCheckElementPresenceImmediately("BrowseCancelUploadBtn")) {
				Thread.sleep(1000);

			}

			fnpWaitForVisible("DocTab_AddWODoc_TypePFList");
			fnpPFList("DocTab_AddWODoc_TypePFList", "DocTab_AddWODoc_TypePFListOptions", table.get("Type"));
			fnpPFList("DocTab_AddWODoc_AccessPFList", "DocTab_AddWODoc_AccessPFListOptions", table.get("Access"));
/*
			fnpGetORObjectX("DocTab_AddWODoc_SaveNCloseBtn").click();
			Thread.sleep(2000);
			fnpLoading_wait();
			fnpLoading_wait();

	*/		
			fnpClickInDialog_OR("DocTab_AddWODoc_SaveNCloseBtn");
			
			
	/*		if (fnpCheckElementPresenceImmediately("ErrorMessage")) {
			//	fnpMouseHover("ErrorMessage");
				fnpMouseHover_OR_EvenMultipleObjectsHavingSameXpath("ErrorMessage");
				fnpMymsg(" Error is thrown by application while adding data in Document Tab");
				throw new Exception("Error is thrown by application while adding data in Document Tab");
			}
			*/
			fnpCheckError("Error is thrown by application while adding data in Document Tab");

			fnpMymsg(" Adding data in  Documents tab successfully.");

		//	fnpClick_OR("Prop_ProposalDetailsTab");
			fnpClick_OR_WithoutWait("Prop_ProposalDetailsTab");

			fnpWaitForVisible("Prop_StatusList");
			fnpPFList("Prop_StatusList", "Prop_StatusListOptions", changeStatusArray[1]);

/*			fnpGetORObjectX("Prop_SaveBtn").click();
			fnpLoading_wait();
			Thread.sleep(2000);
			fnpLoading_wait();
*/
			fnpClick_OR("Prop_SaveBtn");
			
/*			if (fnpCheckElementPresenceImmediately("ErrorMessage")) {
				//fnpMouseHover("ErrorMessage");
				fnpMouseHover_OR_EvenMultipleObjectsHavingSameXpath("ErrorMessage");
				fnpMymsg(" Error is thrown by application after changing status 'WON' in Status list");
				throw new Exception("Error is thrown by application after changing status 'WON' in Status list");
			}
			*/
			fnpCheckError("Error is thrown by application after changing status 'WON' in Status list");

			//	fnpWaitForVisible("TopMessage3");
			fnpWaitForVisibleHavingMultipleElements("TopMessage3");
			SuccessfulMsg = fnpGetText_OR("TopMessage3");
			fnpMymsg("Top Message after changing status 'WON' in Status list  ----" + SuccessfulMsg);

			Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"), "Top message does not contain 'success' word, so might be status 'WON'   has not been changed/updated successfully");

			status = fnpGetORObjectX("Prop_StatusList").getText();
			Assert.assertTrue(status.equalsIgnoreCase("WON"), "Status is not become to 'WON' .");
			fnpMymsg("Proposal status has become to 'WON' now.");

			fnpMymsg("****************************************************************");

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






/*
	@DataProvider
	public Object[][] getTestData() {
		return TestUtil.getData(Proposals_suitexls, this.getClass().getSimpleName());
	}
	
	*/
	

}
