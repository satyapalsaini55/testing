package nsf.ecap.Proposals_suite;

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

public class Proposal_VerifyBillRate extends TestSuiteBase_Proposal {

	// Runmode of test case in a suite
	@BeforeTest
	public void checkTestSkip() throws Throwable {
		count = -1;
		BS_P01 = new Proposal_Won();
		BS_P01.checkTestSkip(this.getClass().getSimpleName());

	}







	//@Test(dataProvider = "getTestData")
	@Test(dataProviderClass = TestDataProvider.class,dataProvider = "VerifyBillRateDataProvider")
	public void Proposal_flow(Hashtable<String, String> table) {

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

			

/*			fnpCommonProposalCodePart2TillClickEditQuestLink(count, table.get("Client"), table.get("Work_Order_Type"), table.get("Financial_Program"), 
					table.get("Product_Type"), table.get("Billing_schedule"),table.get("Standard_Version"), table.get("Scope"), table.get("TAT_time_days"));
			*/
			
			
			
			
			
			fnpCommonProcessQuestionnairesSet( table.get("QuestionNAnswerSets"));
			
			fnpCheckError(" Error is thrown by application while saving  Questionnaire ");


			fnpWaitForVisible("QuestionnaireDetailsTable");
			int colIndex = fnpFindColumnIndex("QuestionnaireDetailsTable_header", "Un Answered Questions");
			String unAnsweredQuestNo = fnpFetchFromTable("QuestionnaireDetailsTable", 1, colIndex);

			int counter = 0;
			while (!unAnsweredQuestNo.equalsIgnoreCase("0")) {
				Thread.sleep(1000);
				counter = counter + 1;
				unAnsweredQuestNo = fnpFetchFromTable("QuestionnaireDetailsTable", 1, colIndex);
				if (counter > Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					break;
				}
			}

			Assert.assertEquals(unAnsweredQuestNo, "0", "Unanswered Questions are not equal to '0'. ");

			fnpClick_OR("Prop_PreviewQuoteLink");
		//	Thread.sleep(1000);

		//	Thread.sleep(5000);
			fnpWaitForVisible("Prop_GenerateQuoteBtn");
			fnpType("OR","Prop_AnnualCost_txt",table.get("Annual_Cost") );

			fnpMymsg("");
			fnpMymsg("");
			fnpMymsg(" Now going to Verifying the outputs .");
			fnpMymsg("");
			fnpMymsg("Output Value set is--" + table.get("Outputs"));
			String NoOfOutputValues[] = table.get("Outputs").trim().split(":");

			String oldTaskDescription = "";
			String newTaskDescription = "";

			double doubleCalculatedTaskCost = 0.0;
			double doubleActualTaskCost = 0.0;

			fnpMymsg("No. of output value sets are ---" + NoOfOutputValues.length);
			fnpMymsg("");
			fnpMymsg("");
			String lastid="";
			fnpMymsg("=========================================================================================================================================");
			for (int i = 0; i < NoOfOutputValues.length; i++) {

				String eachOutput[] = NoOfOutputValues[i].split(",");
				String taskDescription = fnpremoveFormatting(eachOutput[0]);
				newTaskDescription = taskDescription;
				String billCode = fnpremoveFormatting(eachOutput[1]);
				String quantity = fnpremoveFormatting(eachOutput[2]);

				if (i > 0) {
					if (!newTaskDescription.equalsIgnoreCase(oldTaskDescription)) {

						fnpMymsg("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
						fnpMymsg("So, Actual Total Task (" + oldTaskDescription + ") Cost[as displayed in application] is:" + doubleActualTaskCost);
						fnpMymsg("So, Expected (calculated) Total Task (" + oldTaskDescription + ")  Cost[as calculated by adding each bill code total cost] is:" + doubleCalculatedTaskCost);
						Assert.assertEquals(doubleActualTaskCost, doubleCalculatedTaskCost, 1e-15, "Both  Actual_TaskTotalCost '" + doubleActualTaskCost + "' for task '" + oldTaskDescription
								+ "' is NOT  equal to Expected_TaskTotalCost '" + doubleCalculatedTaskCost + "'.");
						fnpMymsg(" Hence Both  Actual_TaskTotalCost '" + doubleActualTaskCost + "' for task '" + oldTaskDescription + "' is  equal to Expected_TaskTotalCost '"
								+ doubleCalculatedTaskCost + "'.");
						doubleCalculatedTaskCost = 0.0;
						fnpMymsg("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
						fnpMymsg("=========================================================================================================================================");
						fnpMymsg("");
						fnpMymsg("");
					} else {
						// nothing
					}
				} else {
					//
				}

				fnpMymsg("Next output set  values are--" + NoOfOutputValues[i]);
				fnpMymsg("Task Description is--" + taskDescription);
				fnpMymsg("Expected Bill code is--" + billCode);
				fnpMymsg("Expected Quantity is--" + quantity);

				
				if (!newTaskDescription.equalsIgnoreCase(oldTaskDescription)) {

					String taskDescrXpath = ".//td/label[contains(@id,':wotask') and normalize-space(text())='" + taskDescription + "']";
					WebElement fac = driver.findElement(By.xpath(taskDescrXpath));
					
					
					try{
						Actions action=new Actions(driver);	
						action.moveToElement(fac).doubleClick().build().perform();						
						//fac.sendKeys(Keys.ARROW_DOWN);
						if (!browserName.equalsIgnoreCase("chrome")) 
							fac.sendKeys(Keys.ARROW_DOWN);
						
						Thread.sleep(500);
					}catch(Throwable t){
						WebElement saveNnextBtn = fnpGetORObjectX("Prop_GenerateQuoteBtn");
						fnpMymsg("As taskDescrXpath --"+taskDescrXpath +"  is not visible properly, so going to move down in If block");
						Actions action = new Actions(driver);
						action.moveToElement(saveNnextBtn).build().perform();
						//saveNnextBtn.sendKeys(Keys.ARROW_DOWN);
						if (!browserName.equalsIgnoreCase("chrome")) 
							saveNnextBtn.sendKeys(Keys.ARROW_DOWN);
						Thread.sleep(2000);
					}
					
					String id = fac.getAttribute("id");
					String taskTotalCostId = id.replace("wotask", "wototamt");
					String totalTaskCostXpath = ".//*[@id='" + taskTotalCostId + "']";
					fnpHighlightByDoubleClick(totalTaskCostXpath);
					lastid=totalTaskCostXpath;
					WebElement totalTaskCostElement = driver.findElement(By.xpath(totalTaskCostXpath));
					String actualTotalTaskCost = totalTaskCostElement.getText();
					//fnpMymsg("Actual Task (" + newTaskDescription + ")  Total Cost  is---" + actualTotalTaskCost);
					actualTotalTaskCost = actualTotalTaskCost.replace("$", "").trim();
					actualTotalTaskCost = actualTotalTaskCost.replace(",", "").trim();
					//fnpMymsg("Actual Task  (" + newTaskDescription + ") Total Cost[as displayed in application] -- Afer processing (remove dollar and , comma)  is---" + actualTotalTaskCost);
					fnpMymsg("");
					doubleActualTaskCost = Double.parseDouble(actualTotalTaskCost);
				}


			//	double totalBillCodeCost = fnpVerifyingOutputProp_withRate_N_ReturnTotalBillCodeCost(taskDescription, billCode, quantity, Billing_schedule, "0045");
				double totalBillCodeCost = fnpVerifyingOutputProp_withRate_N_ReturnTotalBillCodeCost(taskDescription, billCode, quantity, table.get("Billing_schedule"), table.get("ProgCode"));
				
				
				fnpMymsg("");
				fnpMymsg("");

				doubleCalculatedTaskCost = doubleCalculatedTaskCost + totalBillCodeCost;

				oldTaskDescription = newTaskDescription;
			}

			
			WebElement refElement = driver.findElement(By.xpath(lastid));
			Actions action = new Actions(driver);
		//	action.moveToElement(refElement).build().perform();
			action.moveToElement(refElement).doubleClick().build().perform();
			if (!browserName.equalsIgnoreCase("chrome")) {
			refElement.sendKeys(Keys.ARROW_UP);
			Thread.sleep(500);
			refElement.sendKeys(Keys.ARROW_UP);
		//	Thread.sleep(2000);
			}
			
		//	fnpClick_OR("Prop_GenerateQuoteBtn");
			fnpClickInDialog_OR("Prop_GenerateQuoteBtn");
			fnpLoadingGeneratingQuote();
		//	fnpLoading_wait();
			//Thread.sleep(5000);
		//	fnpLoading_wait();
			
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


				fnpCheckError("Error is thrown by application while saving  preview quote");
				
				SuccessfulMsg = fnpGetText_OR("TopMessage3");
			}
			
			

			
			fnpCheckError("Error is thrown by application while saving  preview quote");

		//	fnpWaitForVisible("TopMessage3");
			fnpMouseHover("Prop_StatusList");

			 SuccessfulMsg = fnpGetText_OR("TopMessage3");
			fnpMymsg("Top Message after saving  preview quote ----" + SuccessfulMsg);

			Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"), "Top message does not contain 'success' word, so might be preview quote has not been saved successfully");

			Assert.assertTrue(
					SuccessfulMsg.equalsIgnoreCase("Quote generated successfully and report is saved to eFileRoom"),
					"Top message does not showing 'Quote generated successfully and report is saved to eFileRoom' message, so might be preview quote has not been saved successfully");

			
			fnpCommonProposalDetailsTabWon(table.get("Change_status"), table.get("BU_Manager_Code"), table.get("DocTab_AddPropDoc_FileName"), table.get("Type"), table.get("Access"));

		} catch (Throwable t) {

			fail = true;
			isTestPass = false;
			fnpMymsg("  Proposal_flow  method is failed . " + " . Error is ---" + t.getMessage());
			fnpDesireScreenshotProposal("Proposal_flow_Failed" + (SShots + 1));
			String stackTrace = Throwables.getStackTraceAsString(t);
			String errorMsg = t.getMessage();
			errorMsg = errorMsg + "\n\n\n\n Proposal_flow  method is failed   .See screenshot 'Proposal_flow_Failed" + (SShots + 1) + "'\n\n" + stackTrace;

			//IsBrowserPresentAlready = false; // This is for special reason if one case is fail due to application error or any other reason then it close the
												// browser and login again.
			
			setIsBrowserPresentAlready_false();
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







	//@AfterTest(alwaysRun=true)
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








	@DataProvider
	public Object[][] getTestData() {
		return TestUtil.getData(Proposals_suitexls, this.getClass().getSimpleName());
	}

}
