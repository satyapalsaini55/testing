package nsf.ecap.Proposals_suite;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import nsf.ecap.Work_Order_suite.TestSuiteBase;
import nsf.ecap.util.TestUtil;
import static nsf.ecap.config.IPULSE_CONSTANTS.*;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.os.WindowsUtils;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestSuiteBase_Proposal extends TestSuiteBase {

	private static final String EnteredSalesForceId = null;
	public  Proposal_Won BS_P01;
	public static String runmodes[] = null;
	//public  int count = -1;








	// check if the suite has to be skipped or not
	@BeforeSuite
	public void checkSuiteSkip() throws Throwable {

		fnInitialize();

		//currentSuiteName = "Proposals_suite";
		setCurrentSuiteName("Proposals_suite");
		fnpMymsg("             ");
		fnpMymsg("             ");
		fnpMymsg("=========================================================================================================================================");
		fnpMymsg("####################Proposal Suite Start ############################################################");
		fnpMymsg("Checking Runmode of Proposal_suite");
		if (!TestUtil.isSuiteRunnable(suiteXls, "Proposals_suite")) {
			fnpMymsg("Skipped Proposals_suite as the runmode was set to NO");
			//fnpMymsg("####################Proposal Suite End ############################################################");
			//fnpMymsg("=========================================================================================================================================");
			throw new SkipException("Runmode of Proposals_suite set to no. So Skipping all tests in Proposals_suite");
		}

		if (TestUtil.isSuiteRunnable(suiteXls, "Proposals_suite")) {
			browserName = TestUtil.getBrowserName(suiteXls, "Proposals_suite");
			excelSiteURL= TestUtil.getExcelSiteURL(suiteXls, currentSuiteName);
			fnpMymsg("Browser Name is :" + browserName);
			fnpMymsg("======================");
			currentSuiteCode = "PO";

			int rowNum = suiteXls.getCellRowNum("Test Suite", "TSID", currentSuiteName);
			suiteXls.setCellData("Test Suite", "Last_Execution", rowNum, fnReturnDateWithTimeForExcel());

		}
		
		
		// fnpDeleteSMTPMessages();

	}







	// To remove IEDriver.exe from Windows Task Manager
	 @AfterSuite(alwaysRun=true)
	//@AfterSuite
	public void cleanUp() {
		 
/*		loginAs="";
		loginAsFullName="";
		*/
		setLoginAsAndLoginAsFullName_blank();
		
		
		try {
			referenceSuite=currentSuiteName;
			fnpMymsg("#################### "+currentSuiteName+" Suite End ############################################################");
			if (driver!=null) {
				driver.quit();
			}
			IsBrowserPresentAlready = false;
			killprocess();
		}
		catch (Throwable t) {
			// Nothing to do
		}

	}


	






	public static void fnpCallFunctionClickNoInProposal(
														int pos) throws Throwable {

		String xpathRadioBoxNo = ".//label[text()='No']";
		List<WebElement> ansRadioBoxesNo = driver.findElements(By.xpath(xpathRadioBoxNo));

		int noCount = ansRadioBoxesNo.size();
		int timer = 0;
		while (noCount < pos) {
			timer = timer + 1;
			Thread.sleep(1000);
			if (timer > Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
				break;
			}

		}

		int clickPos = pos;
		int check = 1;
		for (Iterator iterator = ansRadioBoxesNo.iterator(); iterator.hasNext();) {

			WebElement webElement = (WebElement) iterator.next();
			if (check == clickPos) {
				webElement.click();

				fnpLoading_wait();
			}

			check = check + 1;

		}

	}







	public static void fnpCallFunctionClickYesInProposal(
															int pos) throws Throwable {

		String xpathRadioBoxYes = ".//label[text()='Yes']";
		List<WebElement> ansRadioBoxesYes = driver.findElements(By.xpath(xpathRadioBoxYes));

		int YesCount = ansRadioBoxesYes.size();
		int timer = 0;
		while (YesCount < pos) {
			timer = timer + 1;
			Thread.sleep(1000);
			if (timer > Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
				break;
			}

		}

		int clickPos = pos;
		int check = 1;
		for (Iterator iterator = ansRadioBoxesYes.iterator(); iterator.hasNext();) {
			WebElement webElement = (WebElement) iterator.next();
			if (check == clickPos) {
				webElement.click();
				// Thread.sleep(1000);
				fnpLoading_wait();
			//	fnpLoading_wait();
			}

			check = check + 1;

		}

	}






/*
	public static void fnpCommonProposalCode(
												int count,
												String Client,
												String Work_Order_Type,
												String Financial_Program,
												String Product_Type,
												String Billing_schedule,
												String standardVersion,
												String Scope,
												String TAT_time_days,
												String Radio_Button_Choices,
												String Same_Answers_To_All,
												String AnnualAmount,
												String Rush_Fees,
												String Discount_Percent,
												String Discount_Reason) throws Throwable {

		fnpCommonProposalCodePart2TillClickEditQuestLink(count, Client, Work_Order_Type, Financial_Program, Product_Type, Billing_schedule,standardVersion, Scope, TAT_time_days);
		
		
		
		
		String value;
		String radioSequence = Radio_Button_Choices;
		String[] radioArray = radioSequence.split(",");
		int radioCount = radioArray.length;

		String xpathRadioBoxNo = ".//label[text()='No']";
		String xpathRadioBoxYes = ".//label[text()='Yes']";
		int TotalYesNo = 0;
		int retries = 0;
		int noCountNo = 0;
		int noCountYes = 0;

		int pos = 1;
		for (int i = 0; i < radioArray.length; i++) {
			pos = pos + i;

			value = radioArray[i];

			if (value.equalsIgnoreCase("No")) {
				fnpCallFunctionClickNoInProposal(pos);

			}

			// Thread.sleep(2000);

			if (value.equalsIgnoreCase("Yes")) {
				fnpCallFunctionClickYesInProposal(pos);

			}

			while (TotalYesNo < (radioCount * 2)) {

				java.util.List<WebElement> ansRadioBoxesNo = driver.findElements(By.xpath(xpathRadioBoxNo));
				noCountNo = ansRadioBoxesNo.size();

				List<WebElement> ansRadioBoxesYes = driver.findElements(By.xpath(xpathRadioBoxYes));
				noCountYes = ansRadioBoxesYes.size();

				TotalYesNo = noCountNo + noCountYes;

				if (retries > Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					break;

				}

				retries = retries + 1;
				Thread.sleep(1000);

			}

		}

		Thread.sleep(1500);
		String xpathAnswerTxtBox = ".//input[contains(@id,'mainForm:tabView:ans') and @type='text']";
		java.util.List<WebElement> ansTextBoxes = driver.findElements(By.xpath(xpathAnswerTxtBox));

		fnpMymsg("No. of answer text boxes are :" + ansTextBoxes.size());

		String[] answerArray = Same_Answers_To_All.split(",");
		int answerArrayLength = answerArray.length;
		int ansArrayIndex = 0;
		for (Iterator iterator = ansTextBoxes.iterator(); iterator.hasNext();) {
			WebElement webElement = (WebElement) iterator.next();
			webElement.sendKeys(answerArray[ansArrayIndex]);

			Thread.sleep(500);


			if (answerArrayLength != 1) {
				ansArrayIndex = ansArrayIndex + 1;
			}

		}

		fnpClick_OR("Prop_Questionnaire_SaveNCloseBtn");

		fnpLoading_wait();
		Thread.sleep(2000);
		fnpLoading_wait();

		
		
		fnpCommonCodeProposalFromCheckingUnansweredToPreviewQuoteLinkDisabled( AnnualAmount, Rush_Fees, Discount_Percent, Discount_Reason);

	}

*/



/*	public static void fnpCommonProposalCode_new(
												int count,
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
												String Rush_Fees,
												String Discount_Percent,
												String Discount_Reason) throws Throwable {

		fnpCommonProposalCodePart2TillClickEditQuestLink(count, Client, Work_Order_Type, Financial_Program, Product_Type, Billing_schedule, standardVersion,Scope, TAT_time_days);
		
		
		fnpCommonProcessQuestionnairesSet( QuestionNAnswerSets);

		
		
		fnpCommonCodeProposalFromCheckingUnansweredToPreviewQuoteLinkDisabled( AnnualAmount, Rush_Fees, Discount_Percent, Discount_Reason);

	}

*/

	
/*	
	public  void fnpCommonProposalCode_updated(Hashtable <String,String>table) throws Throwable {

		//fnpCommonProposalCodePart2TillClickEditQuestLink(count, Client, Work_Order_Type, Financial_Program, Product_Type, Billing_schedule, standardVersion,Scope, TAT_time_days);
		fnpCommonProposalCodePart2TillClickEditQuestLink_updated(table);
		
		fnpCommonProcessQuestionnairesSet(table.get("QuestionNAnswerSets"));
		
		
		
		fnpCommonCodeProposalFromCheckingUnansweredToPreviewQuoteLinkDisabled( table);

}
*/	


/*
	public static void fnpCommonProposalCodePart2TillClickEditQuestLink(
																		int count,
																		String Client,
																		String Work_Order_Type,
																		String Financial_Program,
																		String Product_Type,
																		String Billing_schedule,
																		String standardVersion,
																		String Scope,
																		String TAT_time_days) throws Throwable {

		fnpMymsg("             ");
		fnpMymsg("             ");
		fnpMymsg("             ");
		fnpMymsg(" Executing Proposal data set  no  --" + (count + 1));

		fnpCommonClickTopMainMenu("Menu", "FirstRefrenceLinkJustBelowToMenu", "CreateProposalLink", "Proposal_ClientFromIPulseLabel");
		
		
		fnpWaitForVisible("Proposal_ClientFromIPulseLabel");
		fnpGetORObjectX("Proposal_ClientFromIPulseLabel").click();

		fnpMymsg("Clicked on Client From i Pulse label/radio button. ");

		fnpClick_OR("Prop_ClFromIPulseLkpBtn");

		fnpSearchNSelectFirstRadioBtn(1, Client, 1);


		fnpMymsg(" Clicked lookup button and selected client i.e. --" + Client);

		fnpMymsg(" Going to select value from Work Order Type List is --" + Work_Order_Type);

		fnpWaitTillVisiblityOfElementNClickable_OR("Prop_WorkOrderTypeList");
		fnpPFList("Prop_WorkOrderTypeList", "Prop_WorkOrderTypeListOptions", Work_Order_Type);

		fnpWaitTillVisiblityOfElementNClickable_OR("Prop_WorkOrderTypeList");
		String selectedWorkOrderType = fnpGetText_OR("Prop_WorkOrderTypeList");
		Assert.assertTrue(selectedWorkOrderType.equalsIgnoreCase(Work_Order_Type), "Work Order Type value is not selected properly");


		
		
		
		fnpMymsg(" Going to select value from Financial Program List is --" + Financial_Program);
		Thread.sleep(500);
		fnpWaitTillVisiblityOfElementNClickable_OR("Prop_FinancialProgramList");
		fnpPFList("Prop_FinancialProgramList", "Prop_FinancialProgramListOptions", Financial_Program);
		Thread.sleep(500);

		String selectedProgram = fnpGetText_OR("Prop_FinancialProgramList");
		Assert.assertTrue(selectedProgram.equalsIgnoreCase(Financial_Program), "Financial Program value is not selected properly");

		Thread.sleep(1000);
		Product_Type = Product_Type.trim();

		fnpWaitTillVisiblityOfElementNClickable_OR("Prop_ProductTypeSuggestionBox");

		fnpType("OR","Prop_ProductTypeSuggestionBox",Product_Type );
		Thread.sleep(1000);
		fnpWaitForVisible("Prop_ProductTypeSuggestionBoxeOptions");
		fnpWaitTillVisiblityOfElementNClickable_OR("Prop_ProductTypeSuggestionBoxeOptions");
		String xpathProductTypeValue = OR.getProperty("Prop_ProductTypeSuggestionBoxeOptionsList") + "[@data-item-label='" + Product_Type + "']";
		fnpMouseHover_NotInOR(xpathProductTypeValue);
		driver.findElement(By.xpath(xpathProductTypeValue)).click();
		Thread.sleep(2000);
		fnpClick_OR("Prop_NextBtn");

		fnpCheckErrMsg("Error thrown by applciation After Click Prop_Next Button  ");

		fnpWaitForVisible("Prop_ProposalNo");
		String proposalNo = fnpGetText_OR("Prop_ProposalNo");
		fnpMymsg("Newly created Proposal No. is --'" + proposalNo + "' .");

		fnpPFList("Prop_BillingSchedule_PFList", "Prop_BillingSchedule_PFListOptions", Billing_schedule);
		
		
		fnpPFListByLiNo("Prop_WOPrimaryContact_PFList", "Prop_WOPrimaryContact_PFListOptions", 2);
		
		
		fnpCommonSuggestionBox("Prop_StandardVersionTxt",standardVersion, "Prop_StandardVersionOptionsXpath","Prop_StandardVersionOptions");
		

		fnpWaitTillVisiblityOfElementNClickable_OR("Prop_SaveBtn");

		fnpClick_OR("Prop_SaveBtn");

		
		fnpCheckError("Error is thrown by application while creating Proposal");
		

		fnpWaitForVisibleHavingMultipleElements("TopMessage3");
		String SuccessfulMsg = fnpGetText_OR("TopMessage3");
		fnpMymsg("Top Message after create ----" + SuccessfulMsg);

		Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"), "Top message does not contain 'success' word, so might be Proposal billing schedule has not been saved successfully");

		fnpWaitForVisible("Prop_ProposalTextTab");
		fnpWaitTillVisiblityOfElementNClickable_OR("Prop_ProposalTextTab");
		fnpGetORObjectX("Prop_ProposalTextTab").click();

		fnpWaitForVisible("Prop_ScopeTxtBx");

		fnpType("OR","Prop_ScopeTxtBx",Scope );

		fnpType("OR","Prop_TATTimeTxtBx",TAT_time_days );
		fnpClick_OR("Prop_SaveBtn");

		
		fnpCheckError("Error is thrown by application while creating Proposal");

		fnpWaitForVisibleHavingMultipleElements("TopMessage3");
		SuccessfulMsg = fnpGetText_OR("TopMessage3");
		fnpMymsg("Top Message after create ----" + SuccessfulMsg);

		Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"), "Top message does not contain 'success' word, so might be Proposal has not been saved successfully");

		fnpClick_OR_WithoutWait("Prop_ProposalDetailsTab");

		fnpClick_OR_WithoutWait("Prop_PDetails_QuestionnaireEditLink");

	}

*/
	


	public  void fnpCommonProposalCodePart2TillClickEditQuestLink_updated(Hashtable<String, String> table) throws Throwable {


		fnpMymsg("             ");
		fnpMymsg("             ");
		fnpMymsg("             ");
		fnpMymsg(" Executing Proposal data set  no  --" + (count + 1));

		fnpCommonClickTopMainMenu("Menu", "FirstRefrenceLinkJustBelowToMenu", "CreateProposalLink", "Proposal_ClientFromIPulseLabel");
		
		
		
		
		if ((this.getClass().getSimpleName().equalsIgnoreCase(ClassName_Proposal_Lost))  || (this.getClass().getSimpleName().equalsIgnoreCase(ClassName_Create_WO_from_Proposal))   ) {
			
			fnpWaitForVisible("Proposal_ClientFromIPulseLabel");
			fnpGetORObjectX("Proposal_ClientFromIPulseLabel").click();

			fnpMymsg("Clicked on Client From i Pulse label/radio button. ");

			fnpClick_OR("Prop_ClFromIPulseLkpBtn");

			fnpSearchNSelectFirstRadioBtn(1, table.get("Client"), 1);


			fnpMymsg(" Clicked lookup button and selected client i.e. --" + table.get("Client"));
		} else {
			if (this.getClass().getSimpleName().equalsIgnoreCase(ClassName_Proposal_Won)) {

				
				fnpGetORObjectX("ProspectFromSalesForceRadioBtn").click();
				Thread.sleep(1000);
				

				
				fnpClick_OR("ProspectFromSalesForceLkpBtn");
				
			
				
				String[][] lookup;
				lookup = lookup_criteria_processing(table.get("Prospect_from_Sales_Force"));
				fnpSearchNSelectFirstRadioBtn(lookup, 1);

						
				String salesForceIdExpected="";
				for (int i = 0; i < lookup.length; i++) {
					//if(lookup[i][0].equalsIgnoreCase("Account ID") || lookup[i][0].equalsIgnoreCase("Account ID")){
					if(lookup[i][0].equalsIgnoreCase("Account ID") ){
						salesForceIdExpected=lookup[i][2];
						break;
					}
				}
				

				String enteredSalesForceId = fnpWaitTillTextBoxDontHaveValue("ProspectFromSalesForceTxtBx", "value");
				

				
				Assert.assertTrue(enteredSalesForceId.contains(salesForceIdExpected), "Prospect from Sales Force is not selected properly from lookup");
				fnpMymsg(" Prospect from Sales Force value is properly selected from Prospect from Sales Force lookup");
				
				
				
				
				Thread.sleep(1000);

			}else{
				throw new Exception("Test case is neither WON or Lost or Create_WO_from_Proposal_flow");
			}

		}
		


		fnpMymsg(" Going to select value from Work Order Type List is --" + table.get("Work_Order_Type"));

		fnpWaitTillVisiblityOfElementNClickable_OR("Prop_WorkOrderTypeList");
		fnpPFList("Prop_WorkOrderTypeList", "Prop_WorkOrderTypeListOptions", table.get("Work_Order_Type"));
		fnpLoading_wait();
		
		
		
/*		Thread.sleep(2000);//for time being as Testscriptuser is too slow and these are dependent drop downs
		fnpWorkAroundToClickbottomFooter();//for time being as Testscriptuser is too slow and these are dependent drop downs
		Thread.sleep(2000);//for time being as Testscriptuser is too slow and these are dependent drop downs
*/
		
		
		//IPM-10565
		//fnpPFList("Prop_CurrencyList", "Prop_CurrencyListOptions", "Need value");
		
		
		
		fnpWaitTillVisiblityOfElementNClickable_OR("Prop_WorkOrderTypeList");
		String selectedWorkOrderType = fnpGetText_OR("Prop_WorkOrderTypeList");
		Assert.assertTrue(selectedWorkOrderType.equalsIgnoreCase(table.get("Work_Order_Type")), "Work Order Type value is not selected properly");


		if (this.getClass().getSimpleName().equalsIgnoreCase(ClassName_Proposal_Won)) {
			
/*			Thread.sleep(2000);//for time being as Testscriptuser is too slow and these are dependent drop downs
			fnpWorkAroundToClickbottomFooter();//for time being as Testscriptuser is too slow and these are dependent drop downs
			Thread.sleep(2000);//for time being as Testscriptuser is too slow and these are dependent drop downs
			*/
			
			fnpPFList("Prop_OrgUnitList", "Prop_OrgUnitOptions", table.get("OrgUnit"));
			fnpLoading_wait();
			
/*			Thread.sleep(2000);//for time being as Testscriptuser is too slow and these are dependent drop downs
			fnpWorkAroundToClickbottomFooter();//for time being as Testscriptuser is too slow and these are dependent drop downs
			Thread.sleep(2000);//for time being as Testscriptuser is too slow and these are dependent drop downs
			
			*/
			
		}
		
		
		
		fnpMymsg(" Going to select value from Financial Program List is --" + table.get("Financial_Program"));
		
		//Thread.sleep(500);
		fnpWaitTillVisiblityOfElementNClickable_OR("Prop_FinancialProgramList");
		
		fnpPFList("Prop_FinancialProgramList", "Prop_FinancialProgramListOptions", table.get("Financial_Program"));
/*		
		Thread.sleep(500);
		Thread.sleep(2000);//for time being as Testscriptuser is too slow and these are dependent drop downs
		fnpWorkAroundToClickbottomFooter();//for time being as Testscriptuser is too slow and these are dependent drop downs
		Thread.sleep(2000);//for time being as Testscriptuser is too slow and these are dependent drop downs
		
		*/
		fnpLoading_wait();
		
		
		String selectedProgram = fnpGetText_OR("Prop_FinancialProgramList");
		Assert.assertTrue(selectedProgram.equalsIgnoreCase(table.get("Financial_Program")), "Financial Program value is not selected properly");

		Thread.sleep(1000);
		String Product_Type = table.get("Product_Type").trim();

		fnpWaitTillVisiblityOfElementNClickable_OR("Prop_ProductTypeSuggestionBox");
		
		fnpType("OR","Prop_ProductTypeSuggestionBox",Product_Type );
		Thread.sleep(1000);
		fnpWaitForVisible("Prop_ProductTypeSuggestionBoxeOptions");
		fnpWaitTillVisiblityOfElementNClickable_OR("Prop_ProductTypeSuggestionBoxeOptions");
		String xpathProductTypeValue = OR.getProperty("Prop_ProductTypeSuggestionBoxeOptionsList") + "[@data-item-label='" + Product_Type + "']";
		fnpMouseHover_NotInOR(xpathProductTypeValue);
		driver.findElement(By.xpath(xpathProductTypeValue)).click();
		
		Thread.sleep(2000);
		//fnpClick_OR_WithoutWait("Prop_ProductTypeDescription");
		//driver.findElement(By.xpath(OR.getProperty("Prop_ProductTypeDescription"))).click();
		fnpWorkAroundToClickbottomFooter();
		
		//fnpWaitForElementVisibility("Prop_ProductTypeDescription", "10");
		String descText=fnpGetText_OR("Prop_ProductTypeDescription");
		fnpMymsg("Product Description ---"+descText);
		
			

			
		
		int iWhileCounter=0;
		while(true){
		iWhileCounter++;
		descText=fnpGetText_OR("Prop_ProductTypeDescription");
		if (descText.trim().equalsIgnoreCase("")) {
			fnpMymsg("Product Description ---"+descText);
			Thread.sleep(1000);
			
		} else {
			break;
		}
		
		
		if (iWhileCounter > (Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))*2)) {
		//if (iWhileCounter > 10) {	
			fnpMymsg("Product Description ---"+descText);
			throw new Exception("Product Description is not populating after "+iWhileCounter+" seconds.");
		}
			
	}
				
			
		
		
		
		
		
		
		
		
		fnpClick_OR("Prop_NextBtn");

		fnpCheckErrMsg("Error thrown by applciation After Click Prop_Next Button  ");

		fnpWaitForVisible("Prop_ProposalNo");
		String proposalNo = fnpGetText_OR("Prop_ProposalNo");
		fnpMymsg("Newly created Proposal No. is --'" + proposalNo + "' .");
		
		
		//new change on 17-11-2020. I got this on Teams from Ruchi  IPM-14181
		if (this.getClass().getSimpleName().equalsIgnoreCase(ClassName_Proposal_Won)) {
			//salesforce entry will be here
			fnpType("OR","Prop_SalesForceId","Auto sales id");
		}
		
		
		

		//IPM-10129
	//	fnpPFList("Prop_BillingSchedule_PFList", "Prop_BillingSchedule_PFListOptions",  table.get("Billing_schedule"));
		
		if ((this.getClass().getSimpleName().equalsIgnoreCase(ClassName_Proposal_Lost))   || this.getClass().getSimpleName().equalsIgnoreCase(ClassName_Create_WO_from_Proposal)   ) {
		fnpPFListByLiNo("Prop_WOPrimaryContact_PFList", "Prop_WOPrimaryContact_PFListOptions", 2);
		}
		
		fnpCommonSuggestionBox("Prop_StandardVersionTxt",table.get("Standard_Version"), "Prop_StandardVersionOptionsXpath","Prop_StandardVersionOptions");
		
		
		
		if (this.getClass().getSimpleName().equalsIgnoreCase(ClassName_Create_WO_from_Proposal))    {
		//fnpPFListByLiNo("Prop_InvoiceBillTo_PFList", "Prop_InvoiceBillTo_PFListOptions", 2);		
		fnpPFListContains_ifMatchingMultipleThenSelectFirstOne("Prop_InvoiceBillTo_PFList", "NSFUSA");
		
		
		
		String ListingFRSUpdateRadioBtnValue ="Yes";// (String) hashXlData.get("Listing_FRS_Update");
		String ListingFRSUpdateRadioBtnXpath = OR.getProperty("Prop_ListingFRSUpdateValuePart1") + "'" + ListingFRSUpdateRadioBtnValue + "']/preceding-sibling::input";
		driver.findElement(By.xpath(ListingFRSUpdateRadioBtnXpath)).click();
		Thread.sleep(500);
		
		fnpPFListByLiNo("Prop_CertDecider_PFList", "Prop_CertDecider_PFListOptions", 2);
		}
		


		fnpWaitTillVisiblityOfElementNClickable_OR("Prop_SaveBtn");

		fnpClick_OR("Prop_SaveBtn");

		
		fnpCheckError("Error is thrown by application while creating Proposal");
		

		fnpWaitForVisibleHavingMultipleElements("TopMessage3");
		String SuccessfulMsg = fnpGetText_OR("TopMessage3");
		fnpMymsg("Top Message after create ----" + SuccessfulMsg);

		Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"), "Top message does not contain 'success' word, so might be Proposal billing schedule has not been saved successfully");

		fnpWaitForVisible("Prop_ProposalTextTab");
		fnpWaitTillVisiblityOfElementNClickable_OR("Prop_ProposalTextTab");
		fnpGetORObjectX("Prop_ProposalTextTab").click();

		fnpWaitForVisible("Prop_ScopeTxtBx");

		fnpType("OR","Prop_ScopeTxtBx",table.get("Scope") );

		fnpType("OR","Prop_TATTimeTxtBx",table.get("TAT_time_days") );
		fnpClick_OR("Prop_SaveBtn");

		
		fnpCheckError("Error is thrown by application while creating Proposal");

		fnpWaitForVisibleHavingMultipleElements("TopMessage3");
		SuccessfulMsg = fnpGetText_OR("TopMessage3");
		fnpMymsg("Top Message after create ----" + SuccessfulMsg);

		Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"), "Top message does not contain 'success' word, so might be Proposal has not been saved successfully");

		fnpClick_OR_WithoutWait("Prop_ProposalDetailsTab");
		

		if ((this.getClass().getSimpleName().equalsIgnoreCase(ClassName_Proposal_Won))   || this.getClass().getSimpleName().equalsIgnoreCase(ClassName_Create_WO_from_Proposal)   ) {
			//fnpType("OR", "Prop_Details_ProposalQuoteValueTxtBx", table.get("ProposalQuoteValue"));
			fnpGetORObjectX("Prop_Details_ProposalQuoteValueTxtBx").click();
			fnpGetORObjectX("Prop_Details_ProposalQuoteValueTxtBx").sendKeys(table.get("ProposalQuoteValue"));
			
/*			WebElement wb=fnpGetORObjectX("Prop_Details_ProposalQuoteValueTxtBx");
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;  
			jsExecutor.executeScript("arguments[0].value='"+table.get("ProposalQuoteValue")+"'", wb); 
			*/
			
			fnpClick_OR("Prop_SaveBtn");
		}
		

		fnpClick_OR_WithoutWait("Prop_PDetails_QuestionnaireEditLink");

	}


	
	
/*

	public static void fnpCommonProposalCodePart2TillClickEditQuestLink_new(
																		int count,
																		String Prospect_from_Sales_Force,
																		String Work_Order_Type,
																		String orgUnit,
																		String Financial_Program,
																		String Product_Type,
																		String Billing_schedule,
																		String Standard_Version,
																		String Scope,
																		String TAT_time_days) throws Throwable {

		fnpMymsg("             ");
		fnpMymsg("             ");
		fnpMymsg("             ");
		fnpMymsg(" Executing Proposal data set  no  --" + (count + 1));


		fnpCommonClickTopMainMenu("Menu", "FirstRefrenceLinkJustBelowToMenu", "CreateProposalLink", "Proposal_ClientFromIPulseLabel");

		
		fnpGetORObjectX("ProspectFromSalesForceRadioBtn").click();
		Thread.sleep(1000);
		

		
		fnpClick_OR("ProspectFromSalesForceLkpBtn");
		
	
		
		String[][] lookup;
		lookup = lookup_criteria_processing(Prospect_from_Sales_Force);
		fnpSearchNSelectFirstRadioBtn(lookup, 1);

				
		String salesForceIdExpected="";
		for (int i = 0; i < lookup.length; i++) {
			if(lookup[i][0].equalsIgnoreCase("Account ID") | lookup[i][0].equalsIgnoreCase("Account ID")){
				salesForceIdExpected=lookup[i][2];
				break;
			}
		}
		

		String enteredSalesForceId = fnpWaitTillTextBoxDontHaveValue("ProspectFromSalesForceTxtBx", "value");
		

		
		Assert.assertTrue(enteredSalesForceId.contains(salesForceIdExpected), "Prospect from Sales Force is not selected properly from lookup");
		fnpMymsg(" Prospect from Sales Force value is properly selected from Prospect from Sales Force lookup");
		
		
		
		
		Thread.sleep(1000);


		fnpMymsg(" Going to select value from Work Order Type List is --" + Work_Order_Type);

		fnpWaitTillVisiblityOfElementNClickable_OR("Prop_WorkOrderTypeList");
		fnpPFList("Prop_WorkOrderTypeList", "Prop_WorkOrderTypeListOptions", Work_Order_Type);
		
		//Thread.sleep(5000);// because other drop down after this work order type are dependent on work order type drown down
		fnpLoading_wait();
		fnpWaitTillVisiblityOfElementNClickable_OR("Prop_WorkOrderTypeList");
		String selectedWorkOrderType = fnpGetText_OR("Prop_WorkOrderTypeList");
		Assert.assertTrue(selectedWorkOrderType.equalsIgnoreCase(Work_Order_Type), "Work Order Type value is not selected properly");


		fnpPFList("Prop_OrgUnitList", "Prop_OrgUnitOptions", orgUnit);
		
		fnpMymsg(" Going to select value from Financial Program List is --" + Financial_Program);
		Thread.sleep(500);
		fnpWaitTillVisiblityOfElementNClickable_OR("Prop_FinancialProgramList");
		fnpPFList("Prop_FinancialProgramList", "Prop_FinancialProgramListOptions", Financial_Program);
		Thread.sleep(500);

		String selectedProgram = fnpGetText_OR("Prop_FinancialProgramList");
		Assert.assertTrue(selectedProgram.equalsIgnoreCase(Financial_Program), "Financial Program value is not selected properly");

		Thread.sleep(1000);
		Product_Type = Product_Type.trim();

		fnpWaitTillVisiblityOfElementNClickable_OR("Prop_ProductTypeSuggestionBox");
	//	fnpGetORObjectX("Prop_ProductTypeSuggestionBox").sendKeys(Product_Type);
		fnpType("OR","Prop_ProductTypeSuggestionBox",Product_Type );
		Thread.sleep(1000);
		fnpWaitForVisible("Prop_ProductTypeSuggestionBoxeOptions");
		fnpWaitTillVisiblityOfElementNClickable_OR("Prop_ProductTypeSuggestionBoxeOptions");
		String xpathProductTypeValue = OR.getProperty("Prop_ProductTypeSuggestionBoxeOptionsList") + "[@data-item-label='" + Product_Type + "']";
		fnpMouseHover_NotInOR(xpathProductTypeValue);
		driver.findElement(By.xpath(xpathProductTypeValue)).click();
		Thread.sleep(2000);
		fnpClick_OR("Prop_NextBtn");



		fnpCheckErrMsg("Error thrown by applciation After Click Prop_Next Button  ");

		fnpWaitForVisible("Prop_ProposalNo");
		String proposalNo = fnpGetText_OR("Prop_ProposalNo");
		fnpMymsg("Newly created Proposal No. is --'" + proposalNo + "' .");

		fnpPFList("Prop_BillingSchedule_PFList", "Prop_BillingSchedule_PFListOptions", Billing_schedule);

		 fnpCommonSuggestionBox("Prop_StandardVersionTxt",Standard_Version, "Prop_StandardVersionOptionsXpath","Prop_StandardVersionOptions");
		
		
		
		fnpWaitTillVisiblityOfElementNClickable_OR("Prop_SaveBtn");

		fnpClick_OR("Prop_SaveBtn");

		
		fnpCheckError("Error is thrown by application while creating Proposal");

		fnpWaitForVisibleHavingMultipleElements("TopMessage3");
		String SuccessfulMsg = fnpGetText_OR("TopMessage3");
		fnpMymsg("Top Message after create ----" + SuccessfulMsg);

		Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"), "Top message does not contain 'success' word, so might be Proposal billing schedule has not been saved successfully");

		fnpWaitForVisible("Prop_ProposalTextTab");
		fnpWaitTillVisiblityOfElementNClickable_OR("Prop_ProposalTextTab");
		fnpGetORObjectX("Prop_ProposalTextTab").click();

		fnpWaitForVisible("Prop_ScopeTxtBx");

		fnpType("OR","Prop_ScopeTxtBx",Scope );

		fnpType("OR","Prop_TATTimeTxtBx",TAT_time_days );
		fnpClick_OR("Prop_SaveBtn");


		
		fnpCheckError("Error is thrown by application while creating Proposal");

		fnpWaitForVisibleHavingMultipleElements("TopMessage3");
		SuccessfulMsg = fnpGetText_OR("TopMessage3");
		fnpMymsg("Top Message after create ----" + SuccessfulMsg);

		Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"), "Top message does not contain 'success' word, so might be Proposal has not been saved successfully");

		fnpClick_OR_WithoutWait("Prop_ProposalDetailsTab");

		fnpClick_OR_WithoutWait("Prop_PDetails_QuestionnaireEditLink");

	}
*/




	// Question No,Answer,AnswerFieldType,AnswerFieldIndexAtThispointOfTime
	public static void fnpSelectListInPropQuestionaire(
														String questNo,
														String answervalue,
														int answerFieldTypeSequenceAtThisPointOfTime) throws Throwable {

		fnpMymsg("Going to select the value '" + answervalue + "' for Question --" + questNo);
		String xpathSelectLists = ".//div[contains(@id,':ans_')]/label[contains(@class,'ui-selectonemenu-label')]";

		List<WebElement> SelectBoxes = driver.findElements(By.xpath(xpathSelectLists));
		int sequence = 0;

		if (SelectBoxes.size() > 0) {
			for (Iterator iterator = SelectBoxes.iterator(); iterator.hasNext();) {
				sequence++;
				WebElement webElement = (WebElement) iterator.next();

				if (sequence == answerFieldTypeSequenceAtThisPointOfTime) {
					String p = webElement.getAttribute("id");
					Thread.sleep(500);
					webElement.click();
					Thread.sleep(1500);
					String panelId = p.replace("label", "panel");
					String listValue = ".//*[@id='" + panelId + "']" + "/div/ul/li[@data-label='" + answervalue + "']";

					int iCounter = 1;
					while (true) {
						try {
							driver.findElement(By.xpath(listValue)).click();
							Thread.sleep(2000);
							break;
						} catch (Throwable t) {
							WebElement saveNnextBtn = fnpGetORObjectX("Prop_Questionnaire_SaveNNextBtn");

							Actions action = new Actions(driver);
							action.moveToElement(saveNnextBtn).build().perform();

						//	saveNnextBtn.sendKeys(Keys.ARROW_DOWN);
							if (!browserName.equalsIgnoreCase("chrome")) 
								saveNnextBtn.sendKeys(Keys.ARROW_DOWN);
							Thread.sleep(2000);
							driver.findElement(By.id(p)).click();

							Thread.sleep(2000);

							if (iCounter < 3) {
								iCounter++;
							} else {
								break;

							}
						}
					}

					// Thread.sleep(1000);
					String selectedlistValue = "";
					try {
						List<WebElement> SelectBoxes1_1 = driver.findElements(By.xpath(xpathSelectLists));
						selectedlistValue = SelectBoxes1_1.get(sequence - 1).getText();
					} catch (Exception e) {
						Thread.sleep(3000);
						List<WebElement> SelectBoxes1_1 = driver.findElements(By.xpath(xpathSelectLists));
						selectedlistValue = SelectBoxes1_1.get(sequence - 1).getText();
					}
					fnpMymsg("Value has been selected ie. '" + selectedlistValue + "'  for Question --" + questNo);

					Assert.assertEquals(selectedlistValue, answervalue, "Fail due to Actual '" + selectedlistValue + "' and expected '" + answervalue + "' are not matched.");
				}

			}
		} else {
			throw new Exception("No of select list in Questionnare are either 0 or not present.");
		}

	}







	// Question No,Answer,AnswerFieldType,AnswerFieldIndexAtThispointOfTime
	public static void fnpFillAnswerToPropQuestionnaireDynamically_old(
																	String questNo,
																	String answervalue) throws Throwable {

		fnpMymsg("Going to select the value '" + answervalue + "' for Question --" + questNo);

		String questXpath = ".//label[contains(text(),'" + questNo + " :')]";
		WebElement quest = driver.findElement(By.xpath(questXpath));

		Actions action0 = new Actions(driver);
		// action0.moveToElement(quest).doubleClick().build().perform();
		action0.moveToElement(fnpGetORObjectX___NOR(questXpath)).doubleClick().build().perform();
		Thread.sleep(1000);

		// quest = driver.findElement(By.xpath(questXpath));
		// if (quest.isDisplayed()) {
		if (fnpGetORObjectX___NOR(questXpath).isDisplayed()) {
			// fnpMymsg("@@@  ---displayed@@@@  -----displayed@@@@  @@@@  @@@@  @@@@");
			// fnpMymsg("@@@@    question no.--"+questNo +"' is not displayed...so ")
			Actions action = new Actions(driver);

			/*
			 * action.moveToElement(quest).build().perform(); quest.sendKeys(Keys.ARROW_DOWN);
			 */
			action.moveToElement(fnpGetORObjectX___NOR(questXpath)).build().perform();
		//	fnpGetORObjectX___NOR(questXpath).sendKeys(Keys.ARROW_DOWN);
			if (!browserName.equalsIgnoreCase("chrome")) 
				fnpGetORObjectX___NOR(questXpath).sendKeys(Keys.ARROW_DOWN);

			// Thread.sleep(500);
		} else {

			WebElement saveNnextBtn = fnpGetORObjectX("Prop_Questionnaire_SaveNNextBtn");
			// fnpMymsg("@@@  @@@@  @@@@  @@@@  @@@@  @@@@");
			fnpMymsg("As question is not visible properly, so going to move down in If block");
			Actions action = new Actions(driver);
			action.moveToElement(saveNnextBtn).build().perform();
		//	saveNnextBtn.sendKeys(Keys.ARROW_DOWN);
			if (!browserName.equalsIgnoreCase("chrome")) 
				saveNnextBtn.sendKeys(Keys.ARROW_DOWN);
			// Thread.sleep(500);
		}

		/*
		 * quest = driver.findElement(By.xpath(questXpath)); fnpMymsg(" Complete Question is this--" + quest.getText());
		 */
		fnpMymsg(" Complete Question is this--" + fnpGetText_NOR(questXpath));
		quest = driver.findElement(By.xpath(questXpath));
		String questid = quest.getAttribute("id");

		// String answerListXpath = ".//tr/td/label[contains(text(),'" + questNo +
		// " :')]/../../following-sibling::tr/td/div[contains(@class,'ui-selectonemenu ui-widget')]";
		String answerListXpath = ".//tr/td/label[contains(text(),'" + questNo + " :')]/../../following-sibling::tr/td/div[contains(@class,'ui-selectonemenu ui-widget')]/label";
		String answerRadioXpath = ".//tr/td/label[contains(text(),'" + questNo + " :')]/../../following-sibling::tr/td/table[contains(@class,'ui-selectoneradio ui-widget')]";
		String answerTextBoxXpath = ".//tr/td/label[contains(text(),'" + questNo + " :')]/../../following-sibling::tr/td/input[contains(@class,'ui-inputfield ui-inputtext')]";

		if (fnpCheckElementPresenceImmediately_NotInOR(answerListXpath)) {
			fnpWaitForVisible_NotInOR(answerListXpath);

			fnpMymsg("List is present for Question--" + questNo);
			String p;
			WebElement oList;
			try {
				// oList = driver.findElement(By.xpath(answerListXpath));
				oList = fnpGetORObjectX___NOR(answerListXpath);
				p = oList.getAttribute("id");
			} catch (StaleElementReferenceException s) {
				WebElement saveNnextBtn = fnpGetORObjectX("Prop_Questionnaire_SaveNNextBtn");
				fnpMymsg("As question is not visible properly, so going to move down in If block");
				Actions action = new Actions(driver);
				action.moveToElement(saveNnextBtn).build().perform();
			//	saveNnextBtn.sendKeys(Keys.ARROW_DOWN);
				if (!browserName.equalsIgnoreCase("chrome")) 
					saveNnextBtn.sendKeys(Keys.ARROW_DOWN);
				// Thread.sleep(2000);
				// Thread.sleep(500);
				// oList = driver.findElement(By.xpath(answerListXpath));
				oList = fnpGetORObjectX___NOR(answerListXpath);
				p = oList.getAttribute("id");

			}

			fnpMymsg("  ---id for list is-" + p);

			// String labelid = p + "_label";

			String pp = p.replace("_label", "");
			pp = pp.trim();
			String labelid = pp.trim() + "_label";
			// oList.click();
			int timer = 0;
			while (true) {
				try {
					// fnpWaitTillVisiblityOfElementNClickable(answerListXpath);
					// oList = driver.findElement(By.xpath(answerListXpath));
					oList = fnpGetORObjectX___NOR(answerListXpath);
					oList.click();
					break;
				} catch (StaleElementReferenceException s) {
					Thread.sleep(1000);
					timer = timer + 1;
					if (timer > Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
						break;
					}
				}
			}
			Thread.sleep(1500);

			// String panelId = p + "_panel";
			String panelId = pp.trim() + "_panel";
			String listValue = ".//*[@id='" + panelId + "']" + "/div/ul/li[@data-label='" + answervalue + "']";
			int iCounter = 1;
			while (true) {
				try {
					// fnpWaitTillVisiblityOfElementNClickable(listValue);---old
					// fnpMouseHover_NotInOR(listValue);--were fine
					// Thread.sleep(2000);--were fine
					// driver.findElement(By.xpath(listValue)).click();--old

					// List<WebElement> objectlistValues=driver.findElement(By.xpath(listValue)).findElements(By.tagName("li"));
					List<WebElement> objectlistValues = driver.findElement(By.xpath(".//*[@id='" + panelId + "']")).findElements(By.tagName("li"));
					boolean ValueMatched = false;
					;
					for (WebElement dd_value : objectlistValues) {
						Actions act = new Actions(driver);
					//	act.moveToElement(dd_value).sendKeys(Keys.ARROW_DOWN).build().perform();
						if (!browserName.equalsIgnoreCase("chrome")) 
							act.moveToElement(dd_value).sendKeys(Keys.ARROW_DOWN).build().perform();
					//	//System.out.println("  ------" + dd_value.getText());
						if (dd_value.getText().equals(answervalue)) {
							Thread.sleep(1000);
							// fnpWaitForVisible_NotInOR(listValue);
							fnpWaitTillVisiblityOfElementNClickable(listValue);
							dd_value.click();
							ValueMatched = true;
							break;
						}

					}
					if (ValueMatched == false) {
						throw new Exception("Excel value --'" + answervalue + "'  is not matched with DropDown Value.");
					}

					// Thread.sleep(2000);
					// Thread.sleep(500);
					// Thread.sleep(1000);
					break;
				} catch (Throwable t) {
					WebElement saveNnextBtn = fnpGetORObjectX("Prop_Questionnaire_SaveNNextBtn");
					Actions action = new Actions(driver);
					action.moveToElement(saveNnextBtn).build().perform();
					//saveNnextBtn.sendKeys(Keys.ARROW_DOWN);
					if (!browserName.equalsIgnoreCase("chrome")) 
						saveNnextBtn.sendKeys(Keys.ARROW_DOWN);
					Thread.sleep(1000);
					driver.findElement(By.id(p)).click();
					Thread.sleep(2000);
					if (iCounter < 3) {
						iCounter++;
					} else {
						break;

					}
				}
			}

			// Thread.sleep(1000);
			String selectedlistValue = "";
			try {
				selectedlistValue = driver.findElement(By.xpath(answerListXpath)).getText();
			} catch (Throwable t) {
				// Thread.sleep(5000);
				Thread.sleep(2000);
				selectedlistValue = driver.findElement(By.xpath(answerListXpath)).getText();
			}
			fnpMymsg("Value has been selected ie. '" + selectedlistValue + "'  for Question --" + questNo);

			Assert.assertEquals(selectedlistValue, answervalue, "Fail due to Actual '" + selectedlistValue + "' and expected '" + answervalue + "' are not matched.");

			fnpMymsg("");
			fnpMymsg("");

		}

		if (fnpCheckElementPresenceImmediately_NotInOR(answerRadioXpath)) {
			fnpMymsg("Radio is present for Question--" + questNo);

			String answerRadioXpathLabel = answerRadioXpath + "/tbody/tr/td/label[contains(text(),'" + answervalue + "')]";
			int whileloopCount = 0;
			while (true) {

						// Thread.sleep(2000); commented on 19th feb
						String checkedRadioLabelForAttribute;
						String checkedLabel = null;
		
						try {
							//WebElement fac = driver.findElement(By.xpath(answerRadioXpathLabel));
							WebElement fac = fnpGetORObjectX___NOR(answerRadioXpathLabel);
							Actions action = new Actions(driver);
							action.moveToElement(fac).doubleClick().build().perform();
							 Thread.sleep(2000); //commented on 19th feb
							//Thread.sleep(1000);
						//	checkedRadioLabelForAttribute = driver.findElement(By.xpath(answerRadioXpathLabel)).getAttribute("for");
						//	checkedRadioLabelForAttribute = fnpGetORObjectX___NOR(answerRadioXpathLabel).getAttribute("for");
									while (true) {
										Thread.sleep(500);
									//	checkedRadioLabelForAttribute = driver.findElement(By.xpath(answerRadioXpathLabel)).getAttribute("for");
										checkedRadioLabelForAttribute = fnpGetORObjectX___NOR(answerRadioXpathLabel).getAttribute("for");						
										String checkedRadioXpath2 = ".//td/div/div/input[@id='" + checkedRadioLabelForAttribute + "']";
									//	WebElement checkedRadio = driver.findElement(By.xpath(checkedRadioXpath2));
										WebElement checkedRadio = fnpGetORObjectX___NOR(checkedRadioXpath2);
										checkedLabel = checkedRadio.getAttribute("checked");
										fnpMymsg("Checked attribute value is--" + checkedLabel);
				
											if (checkedLabel != null) {
												break;
					
											} else {
					
												whileloopCount++;
												if (whileloopCount < 3) {
													fnpMymsg("@@@    ---As checked attribute value is null so going to double click again--");
													action.moveToElement(fac).doubleClick().build().perform();
													Thread.sleep(2000);
												} else {
													break;
												}
											}
									}

									if (checkedLabel != null) {
										if (checkedLabel.equalsIgnoreCase("true")) {
											fnpMymsg("Radio button for question '" + questNo + "' has  been selected properly.");
											break;
										} else {
											// continue;
											fnpMymsg("  checked label is not true in question no.--" + questNo + " and checkedlabel value  is --" + checkedLabel);
											throw new Exception("  checked label is not true in question no.--" + questNo + " and checkedlabel value  is --" + checkedLabel);
										}
				
									} else {
										// continue;
										fnpMymsg("  checked label is null in question no.--" + questNo + " and checkedlabel value  is --" + checkedLabel);
										throw new Exception("  checked label is null in question no.--" + questNo + " and checkedlabel value  is --" + checkedLabel);
									}

				} catch (StaleElementReferenceException s) {
					whileloopCount++;
					if (whileloopCount < 3) {
						fnpMymsg("@@@    ---in StaleElementReferenceException for retry chance ---" +whileloopCount);
						Thread.sleep(2000);
					} else {
						throw new Exception(s.getMessage());
					}
				}

				catch (Throwable t) {
					fnpMymsg("********   Watch here in catch block is ---" + t.getMessage());
					// WebElement saveNnextBtn = fnpGetORObjectX("Prop_Questionnaire_SaveNNextBtn");
					WebElement saveNnextBtn = fnpGetORObjectX("Prop_Questionnaire_SaveNCloseBtn");// for last page if error thrown as saveNNext button not
																									// enabled there
					Actions action = new Actions(driver);
					action.moveToElement(saveNnextBtn).build().perform();
					//saveNnextBtn.sendKeys(Keys.ARROW_DOWN);
					if (!browserName.equalsIgnoreCase("chrome")) 
						saveNnextBtn.sendKeys(Keys.ARROW_DOWN);
					Thread.sleep(1000);
					// saveNnextBtn.sendKeys(Keys.ARROW_DOWN);
					// Thread.sleep(1000);

					whileloopCount++;
					if (whileloopCount < 3) {
						// Thread.sleep(2000);
						// continue;
						Thread.sleep(2000);
					} else {
						throw new Exception(t.getMessage() + "\n\n Error thrown in question no.--" + questNo + " and error is --" + t.getMessage());
					}
				}
			}
		}

		if (fnpCheckElementPresenceImmediately_NotInOR(answerTextBoxXpath)) {

			String insertedValue;
			fnpMymsg("Text box is present for Question--" + questNo);
			fnpWaitForVisible_NotInOR(answerTextBoxXpath);
		//	driver.findElement(By.xpath(answerTextBoxXpath)).sendKeys(answervalue);
			fnpType(null,answerTextBoxXpath,answervalue );
			Thread.sleep(1000);

			// commented on 19th feb
			/*
			 * if (fnpGetCurrRunningBrowserName().equalsIgnoreCase("IE")) { driver.findElement(By.xpath(answerTextBoxXpath)).click(); Thread.sleep(1000); }
			 */
			fnpMymsg("Value has been inserted and now going to verify whether right value is inserted or not.");

			insertedValue = driver.findElement(By.xpath(answerTextBoxXpath)).getAttribute("value");

			/*
			 * try { insertedValue = driver.findElement(By.xpath(answerTextBoxXpath)).getAttribute("value");
			 * 
			 * } catch (Throwable t) { Thread.sleep(4000); insertedValue = driver.findElement(By.xpath(answerTextBoxXpath)).getAttribute("value"); }
			 * 
			 * if (insertedValue.equalsIgnoreCase(answervalue)) { //nothing to do } else { Thread.sleep(4000); insertedValue =
			 * driver.findElement(By.xpath(answerTextBoxXpath)).getAttribute("value");
			 * 
			 * }
			 */

			int whileloopCountTxtbox = 0;
			while (!insertedValue.equalsIgnoreCase(answervalue)) {
				whileloopCountTxtbox++;
				if (whileloopCountTxtbox < 3) {
					Thread.sleep(2000);
					insertedValue = driver.findElement(By.xpath(answerTextBoxXpath)).getAttribute("value");
				} else {
					break;
				}

			}
			fnpMymsg("Inserted value in text box is--" + insertedValue);
			Assert.assertEquals(insertedValue, answervalue, "Value in text box for this question '" + questNo + "' has NOT been inserted  properly.");
			fnpMymsg("Value in text box for this question '" + questNo + "' has  been inserted  properly.");

		}

	}



	// Question No,Answer,AnswerFieldType,AnswerFieldIndexAtThispointOfTime
	public static void fnpFillAnswerToPropQuestionnaireDynamically(
																	String questNo,
																	String answervalue) throws Throwable {

		fnpMymsg("Going to select the value '" + answervalue + "' for Question --" + questNo);

		String questXpath = ".//label[contains(text(),'" + questNo + " :')]";
		WebElement quest = driver.findElement(By.xpath(questXpath));

		Actions action0 = new Actions(driver);
		// action0.moveToElement(quest).doubleClick().build().perform();
		action0.moveToElement(fnpGetORObjectX___NOR(questXpath)).doubleClick().build().perform();
		Thread.sleep(1000);

		 quest = fnpGetORObjectX___NOR(questXpath);
		if (quest.isDisplayed()) {
		//if (fnpGetORObjectX___NOR(questXpath).isDisplayed()) {
			// fnpMymsg("@@@  ---displayed@@@@  -----displayed@@@@  @@@@  @@@@  @@@@");
			// fnpMymsg("@@@@    question no.--"+questNo +"' is not displayed...so ")
			Actions action = new Actions(driver);

			/*
			 * action.moveToElement(quest).build().perform(); quest.sendKeys(Keys.ARROW_DOWN);
			 */
			action.moveToElement(quest).build().perform();
		//	quest.sendKeys(Keys.ARROW_DOWN);
			
			if (!browserName.equalsIgnoreCase("chrome")) 
				quest.sendKeys(Keys.ARROW_DOWN);

			// Thread.sleep(500);
		} else {

			WebElement saveNnextBtn = fnpGetORObjectX("Prop_Questionnaire_SaveNNextBtn");
			// fnpMymsg("@@@  @@@@  @@@@  @@@@  @@@@  @@@@");
			fnpMymsg("As question is not visible properly, so going to move down in If block");
			Actions action = new Actions(driver);
			action.moveToElement(saveNnextBtn).build().perform();
		//	saveNnextBtn.sendKeys(Keys.ARROW_DOWN);
			// Thread.sleep(500);
			if (!browserName.equalsIgnoreCase("chrome")) 
				quest.sendKeys(Keys.ARROW_DOWN);
		}

		/*
		 * quest = driver.findElement(By.xpath(questXpath)); fnpMymsg(" Complete Question is this--" + quest.getText());
		 */
		
		
		fnpMymsg(" Complete Question is this--" + fnpGetText_NOR(questXpath));
		quest = fnpGetORObjectX___NOR(questXpath);
		String questid = quest.getAttribute("id");

		// String answerListXpath = ".//tr/td/label[contains(text(),'" + questNo +
		// " :')]/../../following-sibling::tr/td/div[contains(@class,'ui-selectonemenu ui-widget')]";
		String answerListXpath = ".//tr/td/label[contains(text(),'" + questNo + " :')]/../../following-sibling::tr/td/div[contains(@class,'ui-selectonemenu ui-widget')]/label";
		String answerRadioXpath = ".//tr/td/label[contains(text(),'" + questNo + " :')]/../../following-sibling::tr/td/table[contains(@class,'ui-selectoneradio ui-widget')]";
		String answerTextBoxXpath = ".//tr/td/label[contains(text(),'" + questNo + " :')]/../../following-sibling::tr/td/input[contains(@class,'ui-inputfield ui-inputtext')]";

		if (fnpCheckElementPresenceImmediately_NotInOR(answerListXpath)) {
			fnpWaitForVisible_NotInOR(answerListXpath);

			fnpMymsg("List is present for Question--" + questNo);
			String p;
			WebElement oList;
			try {
				// oList = driver.findElement(By.xpath(answerListXpath));
				oList = fnpGetORObjectX___NOR(answerListXpath);
				p = oList.getAttribute("id");
			} catch (StaleElementReferenceException s) {
				WebElement saveNnextBtn = fnpGetORObjectX("Prop_Questionnaire_SaveNNextBtn");
				fnpMymsg("As question is not visible properly, so going to move down in If block");
				Actions action = new Actions(driver);
				action.moveToElement(saveNnextBtn).build().perform();
			//	saveNnextBtn.sendKeys(Keys.ARROW_DOWN);
				if (!browserName.equalsIgnoreCase("chrome")) 
					quest.sendKeys(Keys.ARROW_DOWN);

				oList = fnpGetORObjectX___NOR(answerListXpath);
				p = oList.getAttribute("id");

			}


			String pp = p.replace("_label", "");
			pp = pp.trim();
			String labelid = pp.trim() + "_label";
			// oList.click();
			int timer = 0;
			while (true) {
				try {

					oList = fnpGetORObjectX___NOR(answerListXpath);
					oList.click();
				//	fnpLoading_wait();
					break;
				} catch (StaleElementReferenceException s) {
					Thread.sleep(1000);
					timer = timer + 1;
					if (timer > Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
						break;
					}
				}
			}
			Thread.sleep(1500);

			// String panelId = p + "_panel";
			String panelId = pp.trim() + "_panel";
			String listValue = ".//*[@id='" + panelId + "']" + "/div/ul/li[@data-label='" + answervalue + "']";
			int iCounter = 1;
			while (true) {
				try {

					List<WebElement> objectlistValues = driver.findElement(By.xpath(".//*[@id='" + panelId + "']")).findElements(By.tagName("li"));
					boolean ValueMatched = false;
					;
					for (WebElement dd_value : objectlistValues) {
						Actions act = new Actions(driver);
						act.moveToElement(dd_value).sendKeys(Keys.ARROW_DOWN).build().perform();
					//	//System.out.println("  ------" + dd_value.getText());
						if (dd_value.getText().equals(answervalue)) {
							Thread.sleep(500);
							// fnpWaitForVisible_NotInOR(listValue);
							
							if (browserName.equalsIgnoreCase("IE")) {
								act.moveToElement(dd_value).sendKeys(Keys.ARROW_DOWN).build().perform();
								Thread.sleep(200);
								act.moveToElement(dd_value).sendKeys(Keys.ARROW_DOWN).build().perform();

							}


							fnpWaitTillVisiblityOfElementNClickable(listValue);
							dd_value.click();
							fnpLoading_wait();
							ValueMatched = true;
							break;
						}

					}
					if (ValueMatched == false) {
						throw new Exception("Excel value --'" + answervalue + "'  is not matched with DropDown Value.");
					}

					break;
				} catch (Throwable t) {
					WebElement saveNnextBtn = fnpGetORObjectX("Prop_Questionnaire_SaveNNextBtn");
					Actions action = new Actions(driver);
					action.moveToElement(saveNnextBtn).build().perform();
				//	saveNnextBtn.sendKeys(Keys.ARROW_DOWN);
					if (!browserName.equalsIgnoreCase("chrome")) 
						quest.sendKeys(Keys.ARROW_DOWN);
					Thread.sleep(1000);
					driver.findElement(By.id(p)).click();
					fnpLoading_wait();

					if (iCounter < 3) {
						iCounter++;
					} else {
						break;

					}
				}
			}

			// Thread.sleep(1000);
			String selectedlistValue = "";
			try {
				selectedlistValue = driver.findElement(By.xpath(answerListXpath)).getText();
			} catch (Throwable t) {
				// Thread.sleep(5000);
				Thread.sleep(2000);
				selectedlistValue = driver.findElement(By.xpath(answerListXpath)).getText();
			}
			fnpMymsg("Value has been selected ie. '" + selectedlistValue + "'  for Question --" + questNo);

			Assert.assertEquals(selectedlistValue, answervalue, "Fail due to Actual '" + selectedlistValue + "' and expected '" + answervalue + "' are not matched.");

			fnpMymsg("");
			fnpMymsg("");

		}

		if (fnpCheckElementPresenceImmediately_NotInOR(answerRadioXpath)) {
			fnpMymsg("Radio is present for Question--" + questNo);

			String answerRadioXpathLabel = answerRadioXpath + "/tbody/tr/td/label[contains(text(),'" + answervalue + "')]";
			int whileloopCount = 0;
			while (true) {

				// Thread.sleep(2000); commented on 19th feb
				String checkedRadioLabelForAttribute;
				String checkedLabel = null;

						try {
							//WebElement fac = driver.findElement(By.xpath(answerRadioXpathLabel));
							WebElement fac = fnpGetORObjectX___NOR(answerRadioXpathLabel);
							Actions action = new Actions(driver);
							action.moveToElement(fac).doubleClick().build().perform();
						//	 Thread.sleep(1000);
							fnpLoading_wait();
						//	 Thread.sleep(1000); //commented on 19th feb
							//Thread.sleep(1000);
						//	checkedRadioLabelForAttribute = driver.findElement(By.xpath(answerRadioXpathLabel)).getAttribute("for");
						//	checkedRadioLabelForAttribute = fnpGetORObjectX___NOR(answerRadioXpathLabel).getAttribute("for");
									while (true) {
										Thread.sleep(500);
									//	checkedRadioLabelForAttribute = driver.findElement(By.xpath(answerRadioXpathLabel)).getAttribute("for");
										checkedRadioLabelForAttribute = fnpGetORObjectX___NOR(answerRadioXpathLabel).getAttribute("for");						
										String checkedRadioXpath2 = ".//td/div/div/input[@id='" + checkedRadioLabelForAttribute + "']";
									//	WebElement checkedRadio = driver.findElement(By.xpath(checkedRadioXpath2));
										WebElement checkedRadio = fnpGetORObjectX___NOR(checkedRadioXpath2);
										checkedLabel = checkedRadio.getAttribute("checked");
										fnpMymsg("Checked attribute value is--" + checkedLabel);

										if (checkedLabel != null) {
											break;
				
										} else {
				
											whileloopCount++;
											if (whileloopCount < 3) {
												fnpMymsg("@@@    ---As checked attribute value is null so going to double click again--");
												action.moveToElement(fac).doubleClick().build().perform();
												//Thread.sleep(2000);
												fnpLoading_wait();
											} else {
												break;
											}
										}
										}

										if (checkedLabel != null) {
											if (checkedLabel.equalsIgnoreCase("true")) {
												fnpMymsg("Radio button for question '" + questNo + "' has  been selected properly.");
												break;
											} else {
												// continue;
												fnpMymsg("  checked label is not true in question no.--" + questNo + " and checkedlabel value  is --" + checkedLabel);
												throw new Exception("  checked label is not true in question no.--" + questNo + " and checkedlabel value  is --" + checkedLabel);
											}
					
										} else {
											// continue;
											fnpMymsg("  checked label is null in question no.--" + questNo + " and checkedlabel value  is --" + checkedLabel);
											throw new Exception("  checked label is null in question no.--" + questNo + " and checkedlabel value  is --" + checkedLabel);
										}

									} catch (StaleElementReferenceException s) {
										whileloopCount++;
										if (whileloopCount < 3) {
											Thread.sleep(2000);
										} else {
											throw new Exception(s.getMessage());
										}
									}

				catch (Throwable t) {
					fnpMymsg("********   Watch here in catch block is ---" + t.getMessage());
					// WebElement saveNnextBtn = fnpGetORObjectX("Prop_Questionnaire_SaveNNextBtn");
					WebElement saveNnextBtn = fnpGetORObjectX("Prop_Questionnaire_SaveNCloseBtn");// for last page if error thrown as saveNNext button not
																									// enabled there
					Actions action = new Actions(driver);
					action.moveToElement(saveNnextBtn).build().perform();
				//	saveNnextBtn.sendKeys(Keys.ARROW_DOWN);
					if (!browserName.equalsIgnoreCase("chrome")) 
						quest.sendKeys(Keys.ARROW_DOWN);
					Thread.sleep(1000);
					// saveNnextBtn.sendKeys(Keys.ARROW_DOWN);
					// Thread.sleep(1000);

					whileloopCount++;
					if (whileloopCount < 3) {
						// Thread.sleep(2000);
						// continue;
						Thread.sleep(2000);
					} else {
						throw new Exception(t.getMessage() + "\n\n Error thrown in question no.--" + questNo + " and error is --" + t.getMessage());
					}
				}
			}
		}

		if (fnpCheckElementPresenceImmediately_NotInOR(answerTextBoxXpath)) {

			String insertedValue;
			fnpMymsg("Text box is present for Question--" + questNo);
			fnpWaitForVisible_NotInOR(answerTextBoxXpath);
		//	driver.findElement(By.xpath(answerTextBoxXpath)).sendKeys(answervalue);
			fnpType(null,answerTextBoxXpath,answervalue );
			Thread.sleep(1000);

			// commented on 19th feb
			/*
			 * if (fnpGetCurrRunningBrowserName().equalsIgnoreCase("IE")) { driver.findElement(By.xpath(answerTextBoxXpath)).click(); Thread.sleep(1000); }
			 */
			fnpMymsg("Value has been inserted and now going to verify whether right value is inserted or not.");

			insertedValue = driver.findElement(By.xpath(answerTextBoxXpath)).getAttribute("value");

			/*
			 * try { insertedValue = driver.findElement(By.xpath(answerTextBoxXpath)).getAttribute("value");
			 * 
			 * } catch (Throwable t) { Thread.sleep(4000); insertedValue = driver.findElement(By.xpath(answerTextBoxXpath)).getAttribute("value"); }
			 * 
			 * if (insertedValue.equalsIgnoreCase(answervalue)) { //nothing to do } else { Thread.sleep(4000); insertedValue =
			 * driver.findElement(By.xpath(answerTextBoxXpath)).getAttribute("value");
			 * 
			 * }
			 */

			int whileloopCountTxtbox = 0;
			while (!insertedValue.equalsIgnoreCase(answervalue)) {
				whileloopCountTxtbox++;
				if (whileloopCountTxtbox < 3) {
					Thread.sleep(2000);
					insertedValue = driver.findElement(By.xpath(answerTextBoxXpath)).getAttribute("value");
				} else {
					break;
				}

			}
			fnpMymsg("Inserted value in text box is--" + insertedValue);
			Assert.assertEquals(insertedValue, answervalue, "Value in text box for this question '" + questNo + "' has NOT been inserted  properly.");
			fnpMymsg("Value in text box for this question '" + questNo + "' has  been inserted  properly.");

		}

	}




	// Question No,Answer,AnswerFieldType,AnswerFieldIndexAtThispointOfTime
	public static void fnpFillAnswerToPropQuestionnaireDynamically_processHiddenAlso(
																	String questNo,
																	String answervalue) throws Throwable {

		fnpMymsg("Going to select the value '" + answervalue + "' for Question --" + questNo);
/*		if(questNo.equals("Q7A")){
			System.out.println("Test");
		}*/

		String questXpath = ".//label[contains(text(),'" + questNo + " :')]";
	//	String questXpath = ".//label[contains(normalize-space(text()),'" + questNo + " :')]";
	
		WebElement quest=null;
		try{
		  quest = driver.findElement(By.xpath(questXpath));
		}		
		catch(org.openqa.selenium.NoSuchElementException  nse){
			fnpMymsg("Might be element with this xpath '"+".//label[contains(text(),'" + questNo + " :')]' is not present or visible.");
			throw new Exception("Might be element with this xpath '"+".//label[contains(text(),'" + questNo + " :')]' is not present or visible.");
		}
		


		Actions action0 = new Actions(driver);
		 action0.moveToElement(quest).doubleClick().build().perform();
		//action0.moveToElement(fnpGetORObjectX___NOR(questXpath)).doubleClick().build().perform();
		Thread.sleep(1000);

		 quest = driver.findElement(By.xpath(questXpath));
	
			Actions action = new Actions(driver);

			/*
			 * action.moveToElement(quest).build().perform(); quest.sendKeys(Keys.ARROW_DOWN);
			 */
			action.moveToElement(quest).build().perform();
		//	quest.sendKeys(Keys.ARROW_DOWN);
			
			if (!browserName.equalsIgnoreCase("chrome")) 
				quest.sendKeys(Keys.ARROW_DOWN);


		
		//fnpMymsg(" Complete Question is this--" + fnpGetText_NOR(questXpath));
		//quest = fnpGetORObjectX___NOR(questXpath);
			 quest = driver.findElement(By.xpath(questXpath));
		String questid = quest.getAttribute("id");

		// String answerListXpath = ".//tr/td/label[contains(text(),'" + questNo +
		// " :')]/../../following-sibling::tr/td/div[contains(@class,'ui-selectonemenu ui-widget')]";
		
		
/*		
		
		String answerListXpath = ".//tr/td/label[contains(text(),'" + questNo + " :')]/../../following-sibling::tr/td/div[contains(@class,'ui-selectonemenu ui-widget')]/label";
		String answerRadioXpath = ".//tr/td/label[contains(text(),'" + questNo + " :')]/../../following-sibling::tr/td/table[contains(@class,'ui-selectoneradio ui-widget')]";
		String answerTextBoxXpath = ".//tr/td/label[contains(text(),'" + questNo + " :')]/../../following-sibling::tr/td/input[contains(@class,'ui-inputfield ui-inputtext')]";

*/		
		
		
		String answerListXpath = ".//tr/td/label[starts-with(text(),'" + questNo+ " :')]/../../following-sibling::tr/td/div[contains(@class,'ui-selectonemenu ui-widget')]/label";
		
		
		String answerRadioXpath = ".//tr/td/label[starts-with(text(),'" + questNo
				+ " :')]/../../following-sibling::tr/td/table[contains(@class,'ui-selectoneradio ui-widget')]";
		/*String answerTextBoxXpath = ".//tr/td/label[starts-with(text(),'" + questNo
				+ " :')]/../../following-sibling::tr/td/input[contains(@class,'ui-inputfield ui-inputtext')]";
	*/String answerTextBoxXpath = ".//tr/td/label[starts-with(text(),'" + questNo
			+ " :')]/../../following-sibling::tr/td/*[contains(@class,'ui-inputfield ui-inputtext')]";
	
		
		
		
		if (fnpCheckElementPresenceImmediately_NotInOR(answerListXpath)) {
			//fnpWaitForVisible_NotInOR(answerListXpath);

			fnpMymsg("List is present for Question--" + questNo);
			String p;
			WebElement oList;
			try {
				 oList = driver.findElement(By.xpath(answerListXpath));
				//oList = fnpGetORObjectX___NOR(answerListXpath);
				p = oList.getAttribute("id");
			} catch (StaleElementReferenceException s) {
				//WebElement saveNnextBtn = fnpGetORObjectX("Prop_Questionnaire_SaveNNextBtn");
				WebElement saveNnextBtn = driver.findElement(By.xpath((OR.getProperty("Prop_Questionnaire_SaveNNextBtn"))));

				fnpMymsg("As question is not visible properly, so going to move down in If block");
				 action = new Actions(driver);
				action.moveToElement(saveNnextBtn).build().perform();
			//	saveNnextBtn.sendKeys(Keys.ARROW_DOWN);
				if (!browserName.equalsIgnoreCase("chrome")) 
					quest.sendKeys(Keys.ARROW_DOWN);
				// Thread.sleep(2000);
				// Thread.sleep(500);
				// oList = driver.findElement(By.xpath(answerListXpath));
				oList = fnpGetORObjectX___NOR(answerListXpath);
				p = oList.getAttribute("id");

			}

		//	fnpMymsg("  ---id for list is-" + p);

			// String labelid = p + "_label";

			String pp = p.replace("_label", "");
			pp = pp.trim();
			String labelid = pp.trim() + "_label";
			// oList.click();
			int timer = 0;
			while (true) {
				try {
					// fnpWaitTillVisiblityOfElementNClickable(answerListXpath);
					 oList = driver.findElement(By.xpath(answerListXpath));
					//oList = fnpGetORObjectX___NOR(answerListXpath);
					oList.click();
				//	fnpLoading_wait();
					break;
				} catch (StaleElementReferenceException s) {
					Thread.sleep(1000);
					timer = timer + 1;
					if (timer > Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
						break;
					}
				}
			}
			Thread.sleep(1500);

			// String panelId = p + "_panel";
			String panelId = pp.trim() + "_panel";
			String listValue = ".//*[@id='" + panelId + "']" + "/div/ul/li[@data-label='" + answervalue + "']";
			int iCounter = 1;
			while (true) {
				try {
					// fnpWaitTillVisiblityOfElementNClickable(listValue);---old
					// fnpMouseHover_NotInOR(listValue);--were fine
					// Thread.sleep(2000);--were fine
					// driver.findElement(By.xpath(listValue)).click();--old

					// List<WebElement> objectlistValues=driver.findElement(By.xpath(listValue)).findElements(By.tagName("li"));
					List<WebElement> objectlistValues = driver.findElement(By.xpath(".//*[@id='" + panelId + "']")).findElements(By.tagName("li"));
					boolean ValueMatched = false;
				
					for (WebElement dd_value : objectlistValues) {
						Actions act = new Actions(driver);
						act.moveToElement(dd_value).sendKeys(Keys.ARROW_DOWN).build().perform();
					//	//System.out.println("  ------" + dd_value.getText());
						if (dd_value.getText().equals(answervalue)) {
							//Thread.sleep(500);
							Thread.sleep(1000);
							// fnpWaitForVisible_NotInOR(listValue);
							
							if (browserName.equalsIgnoreCase("IE")) {
								act.moveToElement(dd_value).sendKeys(Keys.ARROW_DOWN).build().perform();
								Thread.sleep(200);
								act.moveToElement(dd_value).sendKeys(Keys.ARROW_DOWN).build().perform();

							}



							//fnpWaitTillVisiblityOfElementNClickable(listValue);
							dd_value.click();
							fnpLoading_wait();
							ValueMatched = true;
							break;
						}

					}
					if (ValueMatched == false) {
						Thread.sleep(5000);
						throw new Exception("Excel value --'" + answervalue + "'  is not matched with DropDown Value.");
					}

					// Thread.sleep(2000);
					// Thread.sleep(500);
					// Thread.sleep(1000);
					break;
				} catch (Throwable t) {
					
					
/*					
					//WebElement saveNnextBtn = fnpGetORObjectX("Prop_Questionnaire_SaveNNextBtn");
					WebElement saveNnextBtn = driver.findElement(By.xpath((OR.getProperty("Prop_Questionnaire_SaveNNextBtn"))));

					Actions action11 = new Actions(driver);
					action11.moveToElement(saveNnextBtn).build().perform();
				//	saveNnextBtn.sendKeys(Keys.ARROW_DOWN);
					if (!browserName.equalsIgnoreCase("chrome")) 
						quest.sendKeys(Keys.ARROW_DOWN);
					Thread.sleep(1000);
					driver.findElement(By.id(p)).click();
					fnpLoading_wait();
				//	Thread.sleep(1000);
				//	fnpLoading_wait();
					if (iCounter < 3) {
						iCounter++;
					} else {
						break;

					}
					
				*/	
					
					
					//fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
					if (t.getMessage().contains(CustomizeErrorMsgWhenLoadingNotOver.substring(0, 28))){
						throw t;
					}
					
					
					if (t.getMessage().contains("Excel value --'" + answervalue + "'  is not matched with DropDown Value.")) {
						if (iCounter < 3) {
							iCounter++;
							String newXpath=".//[@id='"+p+"']";
							oList = driver.findElement(By.xpath(answerListXpath));
							
							fnpMymsg("Here assumption is that list is already expanded so first close and then open it again.");
							//fnpClick_NOR_withoutWait(newXpath);
							oList.click();
							//Thread.sleep(1000);
							fnpLoading_wait();
							//fnpClick_NOR_withoutWait(newXpath);
							oList.click();
							fnpLoading_wait();
							
						} else {
							throw t;

						}
					} else {
						WebElement saveNnextBtn = driver.findElement(By.xpath((OR.getProperty("Prop_Questionnaire_SaveNNextBtn"))));

						Actions action11 = new Actions(driver);
						action11.moveToElement(saveNnextBtn).build().perform();
					//	saveNnextBtn.sendKeys(Keys.ARROW_DOWN);
						if (!browserName.equalsIgnoreCase("chrome")) 
							quest.sendKeys(Keys.ARROW_DOWN);
						Thread.sleep(1000);
						//driver.findElement(By.id(p)).click();
						
						
						String newXpath=".//[@id='"+p+"']";
						if (!(fnpCheckElementDisplayedImmediately(newXpath))) {
							fnpMymsg("Here assumption is that list is not expanded.");
							//fnpClick_NOR_withoutWait(newXpath);
							oList = driver.findElement(By.xpath(answerListXpath));
							oList.click();
							fnpLoading_wait();
						} else {
							// This will work when try again in 2nd chance, first close
							// already opened one and then click again to expand it.
							fnpMymsg("Here assumption is that list is already expanded so first close and then open it again.");
							//fnpClick_NOR_withoutWait(newXpath);
							oList = driver.findElement(By.xpath(answerListXpath));
							oList.click();
							fnpLoading_wait();
							//Thread.sleep(1000);
							//fnpClick_NOR_withoutWait(newXpath);
							oList.click();
							fnpLoading_wait();
						}

						Thread.sleep(500);
						
						
						
						//fnpLoading_wait();
					//	Thread.sleep(1000);
					//	fnpLoading_wait();
						if (iCounter < 3) {
							iCounter++;
						} else {
							break;

						}
					}
					
					
					
					
				}
			}

			// Thread.sleep(1000);
			String selectedlistValue = "";
			try {
				selectedlistValue = driver.findElement(By.xpath(answerListXpath)).getText();
			} catch (Throwable t) {
				// Thread.sleep(5000);
				Thread.sleep(2000);
				selectedlistValue = driver.findElement(By.xpath(answerListXpath)).getText();
			}
			fnpMymsg("Value has been selected ie. '" + selectedlistValue + "'  for Question --" + questNo);

			Assert.assertEquals(selectedlistValue, answervalue, "Fail due to Actual '" + selectedlistValue + "' and expected '" + answervalue + "' are not matched.");

			fnpMymsg("");
			fnpMymsg("");

		}

		if (fnpCheckElementPresenceImmediately_NotInOR(answerRadioXpath)) {
			fnpMymsg("Radio is present for Question--" + questNo);

			String answerRadioXpathLabel = answerRadioXpath + "/tbody/tr/td/label[contains(text(),'" + answervalue + "')]";
			int whileloopCount = 0;
			while (true) {

				// Thread.sleep(2000); commented on 19th feb
				String checkedRadioLabelForAttribute;
				String checkedLabel = null;

						try {
							WebElement fac = driver.findElement(By.xpath(answerRadioXpathLabel));
							//WebElement fac = fnpGetORObjectX___NOR(answerRadioXpathLabel);
							 action = new Actions(driver);
							action.moveToElement(fac).doubleClick().build().perform();
						//	 Thread.sleep(1000);
							fnpLoading_wait();
						//	 Thread.sleep(1000); //commented on 19th feb
							//Thread.sleep(1000);
						//	checkedRadioLabelForAttribute = driver.findElement(By.xpath(answerRadioXpathLabel)).getAttribute("for");
						//	checkedRadioLabelForAttribute = fnpGetORObjectX___NOR(answerRadioXpathLabel).getAttribute("for");
									while (true) {
										Thread.sleep(500);
										checkedRadioLabelForAttribute = driver.findElement(By.xpath(answerRadioXpathLabel)).getAttribute("for");
										//checkedRadioLabelForAttribute = fnpGetORObjectX___NOR(answerRadioXpathLabel).getAttribute("for");						
										String checkedRadioXpath2 = ".//td/div/div/input[@id='" + checkedRadioLabelForAttribute + "']";
										WebElement checkedRadio = driver.findElement(By.xpath(checkedRadioXpath2));
									//	WebElement checkedRadio = fnpGetORObjectX___NOR(checkedRadioXpath2);
										checkedLabel = checkedRadio.getAttribute("checked");
										fnpMymsg("Checked attribute value is--" + checkedLabel);

										if (checkedLabel != null) {
											break;
				
										} else {
				
											whileloopCount++;
											if (whileloopCount < 3) {
												fnpMymsg("@@@    ---As checked attribute value is null so going to double click again--");
												action.moveToElement(fac).doubleClick().build().perform();
												//Thread.sleep(2000);
												fnpLoading_wait();
											} else {
												break;
											}
										}
										}

										if (checkedLabel != null) {
											if (checkedLabel.equalsIgnoreCase("true")) {
												fnpMymsg("Radio button for question '" + questNo + "' has  been selected properly.");
												break;
											} else {
												// continue;
												fnpMymsg("  checked label is not true in question no.--" + questNo + " and checkedlabel value  is --" + checkedLabel);
												throw new Exception("  checked label is not true in question no.--" + questNo + " and checkedlabel value  is --" + checkedLabel);
											}
					
										} else {
											// continue;
											fnpMymsg("  checked label is null in question no.--" + questNo + " and checkedlabel value  is --" + checkedLabel);
											throw new Exception("  checked label is null in question no.--" + questNo + " and checkedlabel value  is --" + checkedLabel);
										}

									} catch (StaleElementReferenceException s) {
										whileloopCount++;
										if (whileloopCount < 3) {
											Thread.sleep(2000);
										} else {
											throw new Exception(s.getMessage());
										}
									}

				catch (Throwable t) {
					fnpMymsg("********   Watch here in catch block is ---" + t.getMessage());
					// WebElement saveNnextBtn = fnpGetORObjectX("Prop_Questionnaire_SaveNNextBtn");
					
				//	WebElement saveNnextBtn = fnpGetORObjectX("Prop_Questionnaire_SaveNCloseBtn");// for last page if error thrown as saveNNext button not
																									// enabled there
					
					
					
					
					WebElement saveNnextBtn = null;
					
					if (fnpCheckElementDisplayedImmediately( "Prop_Questionnaire_SaveNCloseBtn")) {
						saveNnextBtn = fnpGetORObjectX( "Prop_Questionnaire_SaveNCloseBtn");// for
						action = new Actions(driver);
						action.moveToElement(saveNnextBtn).build().perform();
					} else {
						if (fnpCheckElementDisplayedImmediately( "Prop_QuestionnaireCloseBtnInISR")) {
							saveNnextBtn = fnpGetORObjectX("Prop_QuestionnaireCloseBtnInISR");// for
							action = new Actions(driver);
							action.moveToElement(saveNnextBtn).build().perform();
						}
						
					}
					
					
					
/*					
					action = new Actions(driver);
					action.moveToElement(saveNnextBtn).build().perform();
					*/
					
					
				//	saveNnextBtn.sendKeys(Keys.ARROW_DOWN);
					if (!browserName.equalsIgnoreCase("chrome")) 
						quest.sendKeys(Keys.ARROW_DOWN);
					Thread.sleep(1000);
					// saveNnextBtn.sendKeys(Keys.ARROW_DOWN);
					// Thread.sleep(1000);

					whileloopCount++;
					if (whileloopCount < 3) {
						// Thread.sleep(2000);
						// continue;
						Thread.sleep(2000);
					} else {
						throw new Exception(t.getMessage() + "\n\n Error thrown in question no.--" + questNo + " and error is --" + t.getMessage());
					}
				}
			}
		}

		if (fnpCheckElementPresenceImmediately_NotInOR(answerTextBoxXpath)) {

			String insertedValue;
			fnpMymsg("Text box is present for Question--" + questNo);
		//	fnpWaitForVisible_NotInOR(answerTextBoxXpath);
			driver.findElement(By.xpath(answerTextBoxXpath)).sendKeys(answervalue);
		//	fnpType(null,answerTextBoxXpath,answervalue );
			Thread.sleep(1000);

			// commented on 19th feb
			/*
			 * if (fnpGetCurrRunningBrowserName().equalsIgnoreCase("IE")) { driver.findElement(By.xpath(answerTextBoxXpath)).click(); Thread.sleep(1000); }
			 */
			fnpMymsg("Value has been inserted and now going to verify whether right value is inserted or not.");

			insertedValue = driver.findElement(By.xpath(answerTextBoxXpath)).getAttribute("value");



			int whileloopCountTxtbox = 0;
			while (!insertedValue.equalsIgnoreCase(answervalue)) {
				whileloopCountTxtbox++;
				if (whileloopCountTxtbox < 3) {
					Thread.sleep(2000);
					insertedValue = driver.findElement(By.xpath(answerTextBoxXpath)).getAttribute("value");
				} else {
					break;
				}

			}
			fnpMymsg("Inserted value in text box is--" + insertedValue);
			Assert.assertEquals(insertedValue, answervalue, "Value in text box for this question '" + questNo + "' has NOT been inserted  properly.");
			fnpMymsg("Value in text box for this question '" + questNo + "' has  been inserted  properly.");

		}

	}





	// Question No,Answer,AnswerFieldType,AnswerFieldIndexAtThispointOfTime
	public static void fnpFillAnswerToPropQuestionnaireDynamically_processHiddenAlso_new(
																	String questNo) throws Throwable {

		fnpMymsg("Going to select the value  for Question --" + questNo);


		String questXpath = ".//label[contains(text(),'" + questNo + " :')]";
	//	String questXpath = ".//label[contains(normalize-space(text()),'" + questNo + " :')]";
	
		WebElement quest=null;
		try{
		  quest = driver.findElement(By.xpath(questXpath));
		}		
		catch(org.openqa.selenium.NoSuchElementException  nse){
			fnpMymsg("Might be element with this xpath '"+".//label[contains(text(),'" + questNo + " :')]' is not present or visible.");
			throw new Exception("Might be element with this xpath '"+".//label[contains(text(),'" + questNo + " :')]' is not present or visible.");
		}
		


		Actions action0 = new Actions(driver);
		 action0.moveToElement(quest).doubleClick().build().perform();
		//action0.moveToElement(fnpGetORObjectX___NOR(questXpath)).doubleClick().build().perform();
		Thread.sleep(1000);

		 quest = driver.findElement(By.xpath(questXpath));
	
			Actions action = new Actions(driver);

			/*
			 * action.moveToElement(quest).build().perform(); quest.sendKeys(Keys.ARROW_DOWN);
			 */
			action.moveToElement(quest).build().perform();
		//	quest.sendKeys(Keys.ARROW_DOWN);
			
			if (!browserName.equalsIgnoreCase("chrome")) 
				quest.sendKeys(Keys.ARROW_DOWN);


		
		//fnpMymsg(" Complete Question is this--" + fnpGetText_NOR(questXpath));
		//quest = fnpGetORObjectX___NOR(questXpath);
		quest = driver.findElement(By.xpath(questXpath));
		String questid = quest.getAttribute("id");
	
		
		String answerListXpath = ".//tr/td/label[starts-with(text(),'" + questNo+ " :')]/../../following-sibling::tr/td/div[contains(@class,'ui-selectonemenu ui-widget')]/label";
		
		
		String answerRadioXpath = ".//tr/td/label[starts-with(text(),'" + questNo
				+ " :')]/../../following-sibling::tr/td/table[contains(@class,'ui-selectoneradio ui-widget')]";
		/*String answerTextBoxXpath = ".//tr/td/label[starts-with(text(),'" + questNo
				+ " :')]/../../following-sibling::tr/td/input[contains(@class,'ui-inputfield ui-inputtext')]";
	*/String answerTextBoxXpath = ".//tr/td/label[starts-with(text(),'" + questNo
			+ " :')]/../../following-sibling::tr/td/*[contains(@class,'ui-inputfield ui-inputtext')]";
	
		
		
		
		String answervalue;
		boolean selectedProperly = false;
		String finalValueToBeSelect = "";
		if (fnpCheckElementPresenceImmediately_NotInOR(answerListXpath)) {
			//fnpWaitForVisible_NotInOR(answerListXpath);
			int liNo=1;
			fnpMymsg("List is present for Question--" + questNo);
			String p;
			WebElement oList;
			try {
				 oList = driver.findElement(By.xpath(answerListXpath));
				//oList = fnpGetORObjectX___NOR(answerListXpath);
				p = oList.getAttribute("id");
			} catch (StaleElementReferenceException s) {
				//WebElement saveNnextBtn = fnpGetORObjectX("Prop_Questionnaire_SaveNNextBtn");
				WebElement saveNnextBtn = driver.findElement(By.xpath((OR.getProperty("Prop_Questionnaire_SaveNNextBtn"))));

				fnpMymsg("As question is not visible properly, so going to move down in If block");
				 action = new Actions(driver);
				action.moveToElement(saveNnextBtn).build().perform();
			//	saveNnextBtn.sendKeys(Keys.ARROW_DOWN);
				if (!browserName.equalsIgnoreCase("chrome")) 
					quest.sendKeys(Keys.ARROW_DOWN);
				// Thread.sleep(2000);
				// Thread.sleep(500);
				// oList = driver.findElement(By.xpath(answerListXpath));
				oList = fnpGetORObjectX___NOR(answerListXpath);
				p = oList.getAttribute("id");

			}

		//	fnpMymsg("  ---id for list is-" + p);

			// String labelid = p + "_label";

			String pp = p.replace("_label", "");
			pp = pp.trim();
			String labelid = pp.trim() + "_label";
			// oList.click();
			int timer = 0;
			while (true) {
				try {
					// fnpWaitTillVisiblityOfElementNClickable(answerListXpath);
					 oList = driver.findElement(By.xpath(answerListXpath));
					//oList = fnpGetORObjectX___NOR(answerListXpath);
					oList.click();
				//	fnpLoading_wait();
					break;
				} catch (StaleElementReferenceException s) {
					Thread.sleep(1000);
					timer = timer + 1;
					if (timer > Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
						break;
					}
				}
			}
			Thread.sleep(1500);

			// String panelId = p + "_panel";
			String panelId = pp.trim() + "_panel";
			//String listValue = ".//*[@id='" + panelId + "']" + "/div/ul/li[@data-label='" + answervalue + "']";
			String listValue = ".//*[@id='" + panelId + "']" + "/div/ul/li[" + liNo + "]";
			int iCounter = 1;
			while (true) {
				try {
					
					int totalValues = driver.findElements(By.xpath(".//*[@id='" + panelId + "']"+ "/div/ul/li")).size();
					if (!(totalValues > 0)) {
						throw new Exception("Drop down is blank. " );

					}

					if ((totalValues == 1)) {
						String onlyGivenValue = driver.findElement(By.xpath(".//*[@id='" + panelId + "']"+ "/div/ul/li[1]")).getText();
						if ((onlyGivenValue.toLowerCase().contains("select")) || (onlyGivenValue.trim().equalsIgnoreCase(""))) {
							throw new Exception("Drop down is blank --" );
						}

					}

					listValue = ".//*[@id='" + panelId + "']" + "/div/ul/li[" + liNo + "]";

					fnpWaitTillVisiblityOfElementNClickable_NOR_Immediately(listValue);


					String valueToBeSelect = fnpGetText_NOR(listValue);
					if ((valueToBeSelect.toLowerCase().contains("select")) || (valueToBeSelect.trim().equalsIgnoreCase(""))) {
						listValue = ".//*[@id='" + panelId + "']" + "/div/ul/li[" + (liNo + 1) + "]";
					} else {
						listValue = ".//*[@id='" + panelId + "']" + "/div/ul/li[" + liNo + "]";
					}

					finalValueToBeSelect = driver.findElement(By.xpath(listValue)).getText();
					driver.findElement(By.xpath(listValue)).click();

					Thread.sleep(1000);

					fnpMymsg("@ -  Now going to fetch the selected value.");
					//String FinalSelectedValue = fnpGetText_OR(XpathKey).trim();
					String FinalSelectedValue =fnpGetText_NOR(answerListXpath);
					fnpMymsg("@ -  Fetched/Selected value is ---" + FinalSelectedValue);
					String value2 = StringUtils.normalizeSpace(FinalSelectedValue);


					if (!(((finalValueToBeSelect.contains(FinalSelectedValue))) || ((finalValueToBeSelect.contains(value2))))) {
						msg="@ - '" + finalValueToBeSelect + "'  is NOT selected properly in  DropDown as expected is" + "--'" + finalValueToBeSelect + "' and actual is --'"
								+ FinalSelectedValue + "' .";
						fnpMymsg(msg);
						throw new Exception(msg);
					} else {
						fnpMymsg("@ - '" + finalValueToBeSelect + "'  is  selected properly in  DropDown as expected is" + "--'" + finalValueToBeSelect + "' and actual is --'"
								+ FinalSelectedValue + "' .");
						selectedProperly = true;
						break;
					}
					
					
					
					
				} catch (Throwable t) {
					

					
					
					//fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
					if (t.getMessage().contains(CustomizeErrorMsgWhenLoadingNotOver.substring(0, 28))){
						throw t;
					}
					
					
					if (t.getMessage().contains("Value   is not matched with DropDown Value.")) {
						if (iCounter < 3) {
							iCounter++;
							String newXpath=".//[@id='"+p+"']";
							oList = driver.findElement(By.xpath(answerListXpath));
							
							fnpMymsg("Here assumption is that list is already expanded so first close and then open it again.");
							//fnpClick_NOR_withoutWait(newXpath);
							oList.click();
							//Thread.sleep(1000);
							fnpLoading_wait();
							//fnpClick_NOR_withoutWait(newXpath);
							oList.click();
							fnpLoading_wait();
							
						} else {
							throw t;

						}
					} else {
						WebElement saveNnextBtn = driver.findElement(By.xpath((OR.getProperty("Prop_Questionnaire_SaveNNextBtn"))));

						Actions action11 = new Actions(driver);
						action11.moveToElement(saveNnextBtn).build().perform();
					//	saveNnextBtn.sendKeys(Keys.ARROW_DOWN);
						if (!browserName.equalsIgnoreCase("chrome")) 
							quest.sendKeys(Keys.ARROW_DOWN);
						Thread.sleep(1000);
						//driver.findElement(By.id(p)).click();
						
						
						String newXpath=".//[@id='"+p+"']";
						if (!(fnpCheckElementDisplayedImmediately(newXpath))) {
							fnpMymsg("Here assumption is that list is not expanded.");
							//fnpClick_NOR_withoutWait(newXpath);
							oList = driver.findElement(By.xpath(answerListXpath));
							oList.click();
							fnpLoading_wait();
						} else {
							// This will work when try again in 2nd chance, first close
							// already opened one and then click again to expand it.
							fnpMymsg("Here assumption is that list is already expanded so first close and then open it again.");
							//fnpClick_NOR_withoutWait(newXpath);
							oList = driver.findElement(By.xpath(answerListXpath));
							oList.click();
							fnpLoading_wait();
							//Thread.sleep(1000);
							//fnpClick_NOR_withoutWait(newXpath);
							oList.click();
							fnpLoading_wait();
						}

						Thread.sleep(500);
						
						
						
						//fnpLoading_wait();
					//	Thread.sleep(1000);
					//	fnpLoading_wait();
						if (iCounter < 3) {
							iCounter++;
						} else {
							break;

						}
					}
					
					
					
					
				}
			}

			// Thread.sleep(1000);
			String selectedlistValue = "";
			try {
				selectedlistValue = driver.findElement(By.xpath(answerListXpath)).getText();
			} catch (Throwable t) {
				// Thread.sleep(5000);
				Thread.sleep(2000);
				selectedlistValue = driver.findElement(By.xpath(answerListXpath)).getText();
			}
			fnpMymsg("Value has been selected ie. '" + selectedlistValue + "'  for Question --" + questNo);

			Assert.assertEquals(selectedlistValue, finalValueToBeSelect, "Fail due to Actual '" + selectedlistValue + "' and expected '" + finalValueToBeSelect + "' are not matched.");

			fnpMymsg("");
			fnpMymsg("");

		}

		if (fnpCheckElementPresenceImmediately_NotInOR(answerRadioXpath)) {
			fnpMymsg("Radio is present for Question--" + questNo);
			
/*			answervalue="Yes";
			String answerRadioXpathLabel = answerRadioXpath + "/tbody/tr/td/label[contains(text(),'" + answervalue + "')]";
			 */
			
			//String answerRadioXpathLabel = answerRadioXpath + "/tbody/tr/td[2]/label";  // here 2 in td because first td for radio and second td for its label
			String answerRadioXpathLabel = answerRadioXpath + "/tbody/tr/td[1]/label";  // both radio and its label is in same td in new primefaces libraray in June/July 2020
			int whileloopCount = 0;
			while (true) {

				// Thread.sleep(2000); commented on 19th feb
				String checkedRadioLabelForAttribute;
				String checkedLabel = null;

						try {
							WebElement fac = driver.findElement(By.xpath(answerRadioXpathLabel));
							//WebElement fac = fnpGetORObjectX___NOR(answerRadioXpathLabel);
							 action = new Actions(driver);
							action.moveToElement(fac).doubleClick().build().perform();							
							//action.moveToElement(fac).build().perform();
							
/*							action.moveToElement(fac).click().build().perform();
							action.moveToElement(fac).click().perform();				
							action.moveToElement(fac).click().doubleClick().build().perform();
							
							*/
							
							
							
						//	Thread.sleep(1000);		
							fnpLoading_wait();
							
							// commented below on 02-11-2020
/*							fac.click();  // because This is working on my machine but above is working on all machine
							fnpLoading_wait();
							*/
							
							
							
							
						//	 Thread.sleep(1000);
					
						//	 Thread.sleep(1000); //commented on 19th feb
							//Thread.sleep(1000);
						//	checkedRadioLabelForAttribute = driver.findElement(By.xpath(answerRadioXpathLabel)).getAttribute("for");
						//	checkedRadioLabelForAttribute = fnpGetORObjectX___NOR(answerRadioXpathLabel).getAttribute("for");
									while (true) {
										Thread.sleep(500);
										checkedRadioLabelForAttribute = driver.findElement(By.xpath(answerRadioXpathLabel)).getAttribute("for");
										//checkedRadioLabelForAttribute = fnpGetORObjectX___NOR(answerRadioXpathLabel).getAttribute("for");						
										String checkedRadioXpath2 = ".//td/div/div/input[@id='" + checkedRadioLabelForAttribute + "']";
										WebElement checkedRadio = driver.findElement(By.xpath(checkedRadioXpath2));
									//	WebElement checkedRadio = fnpGetORObjectX___NOR(checkedRadioXpath2);
										checkedLabel = checkedRadio.getAttribute("checked");
										fnpMymsg("Checked attribute value is--" + checkedLabel);

										if (checkedLabel != null) {
											break;
				
										} else {
				
											whileloopCount++;
											if (whileloopCount < 3) {
												fnpMymsg("@@@    ---As checked attribute value is null so going to double click again--");
												action.moveToElement(fac).doubleClick().build().perform();
												//Thread.sleep(2000);
												fnpLoading_wait();
											} else {
												break;
											}
										}
										}

										if (checkedLabel != null) {
											if (checkedLabel.equalsIgnoreCase("true")) {
												fnpMymsg("Radio button for question '" + questNo + "' has  been selected properly.");
												break;
											} else {
												// continue;
												fnpMymsg("  checked label is not true in question no.--" + questNo + " and checkedlabel value  is --" + checkedLabel);
												throw new Exception("  checked label is not true in question no.--" + questNo + " and checkedlabel value  is --" + checkedLabel);
											}
					
										} else {
											// continue;
											fnpMymsg("  checked label is null in question no.--" + questNo + " and checkedlabel value  is --" + checkedLabel);
											throw new Exception("  checked label is null in question no.--" + questNo + " and checkedlabel value  is --" + checkedLabel);
										}

									} catch (StaleElementReferenceException s) {
										whileloopCount++;
										if (whileloopCount < 3) {
											Thread.sleep(2000);
										} else {
											throw new Exception(s.getMessage());
										}
									}

				catch (Throwable t) {
					fnpMymsg("********   Watch here in catch block is ---" + t.getMessage());
					// WebElement saveNnextBtn = fnpGetORObjectX("Prop_Questionnaire_SaveNNextBtn");
					
				//	WebElement saveNnextBtn = fnpGetORObjectX("Prop_Questionnaire_SaveNCloseBtn");// for last page if error thrown as saveNNext button not
																									// enabled there
					
					
					
					
					WebElement saveNnextBtn = null;
					
					if (fnpCheckElementDisplayedImmediately( "Prop_Questionnaire_SaveNCloseBtn")) {
						saveNnextBtn = fnpGetORObjectX( "Prop_Questionnaire_SaveNCloseBtn");// for
						action = new Actions(driver);
						action.moveToElement(saveNnextBtn).build().perform();
					} else {
						if (fnpCheckElementDisplayedImmediately( "Prop_QuestionnaireCloseBtnInISR")) {
							saveNnextBtn = fnpGetORObjectX("Prop_QuestionnaireCloseBtnInISR");// for
							action = new Actions(driver);
							action.moveToElement(saveNnextBtn).build().perform();
						}
						
					}
					
					
					
/*					
					action = new Actions(driver);
					action.moveToElement(saveNnextBtn).build().perform();
					*/
					
					
				//	saveNnextBtn.sendKeys(Keys.ARROW_DOWN);
					if (!browserName.equalsIgnoreCase("chrome")) 
						quest.sendKeys(Keys.ARROW_DOWN);
					Thread.sleep(1000);
					// saveNnextBtn.sendKeys(Keys.ARROW_DOWN);
					// Thread.sleep(1000);

					whileloopCount++;
					if (whileloopCount < 3) {
						// Thread.sleep(2000);
						// continue;
						Thread.sleep(2000);
					} else {
						throw new Exception(t.getMessage() + "\n\n Error thrown in question no.--" + questNo + " and error is --" + t.getMessage());
					}
				}
			}
		}

		if (fnpCheckElementPresenceImmediately_NotInOR(answerTextBoxXpath)) {
			answervalue="test";
			String insertedValue;
			fnpMymsg("Text box is present for Question--" + questNo);
		//	fnpWaitForVisible_NotInOR(answerTextBoxXpath);
			
			
		//	driver.findElement(By.xpath(answerTextBoxXpath)).sendKeys(answervalue);
			
			WebElement inputField = driver.findElement(By.xpath(answerTextBoxXpath));
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("arguments[0].setAttribute('value', '" + answervalue +"')", inputField);
			
			
			Thread.sleep(1000);
			try{
			fnpType(null,answerTextBoxXpath,answervalue );
			}catch(Throwable t){
				//we are not interested in catching the issue here, either javascript will work or this type method will work
			}
			Thread.sleep(1000);

			// commented on 19th feb
			/*
			 * if (fnpGetCurrRunningBrowserName().equalsIgnoreCase("IE")) { driver.findElement(By.xpath(answerTextBoxXpath)).click(); Thread.sleep(1000); }
			 */
			fnpMymsg("Value has been inserted and now going to verify whether right value is inserted or not.");

			insertedValue = driver.findElement(By.xpath(answerTextBoxXpath)).getAttribute("value");



			int whileloopCountTxtbox = 0;
			while (!insertedValue.equalsIgnoreCase(answervalue)) {
				whileloopCountTxtbox++;
				if (whileloopCountTxtbox < 3) {
					Thread.sleep(2000);
					insertedValue = driver.findElement(By.xpath(answerTextBoxXpath)).getAttribute("value");
				} else {
					break;
				}

			}
			fnpMymsg("Inserted value in text box is--" + insertedValue);
			Assert.assertEquals(insertedValue, answervalue, "Value in text box for this question '" + questNo + "' has NOT been inserted  properly.");
			fnpMymsg("Value in text box for this question '" + questNo + "' has  been inserted  properly.");

		}

	}





	// Question No,Answer,AnswerFieldType,AnswerFieldIndexAtThispointOfTime
	public static void fnpRadioBtnInPropQuestionaire(
														String questNo,
														String answervalue,
														int answerFieldTypeSequenceAtThisPointOfTime) throws Throwable {

		fnpMymsg("Going to select the Radio value '" + answervalue + "' for Question --" + questNo);
		String xpathRadioBtn = ".//table[contains(@id,':ans_') and @class='ui-selectoneradio ui-widget']/tbody/tr/td/label[text()='" + answervalue + "']";

		List<WebElement> radioBtns = driver.findElements(By.xpath(xpathRadioBtn));

		int sequence = 0;

		if (radioBtns.size() > 0) {
			for (Iterator iterator = radioBtns.iterator(); iterator.hasNext();) {
				sequence++;
				WebElement webElement = (WebElement) iterator.next();

				if (sequence == answerFieldTypeSequenceAtThisPointOfTime) {

					webElement.click();
					Thread.sleep(1500);

				}

			}
		} else {
			throw new Exception("No. of radio btns in Questionnare are either 0 or not present.");
		}

	}







	// Question No,Answer,AnswerFieldType,AnswerFieldIndexAtThispointOfTime
	public static void fnpTextBoxInPropQuestionaire(
													String questNo,
													String answervalue,
													int answerFieldTypeSequenceAtThisPointOfTime) throws Throwable {

		fnpMymsg("Going to insert the value '" + answervalue + "' for Question --" + questNo);

		String xpathAnswerTxtBox = ".//input[contains(@id,'mainForm:tabView:ans_') and @aria-disabled='false']";

		List<WebElement> TextBoxes = driver.findElements(By.xpath(xpathAnswerTxtBox));

		int sequence = 0;

		if (TextBoxes.size() > 0) {
			for (Iterator iterator = TextBoxes.iterator(); iterator.hasNext();) {
				sequence++;
				WebElement webElement = (WebElement) iterator.next();

				if (sequence == answerFieldTypeSequenceAtThisPointOfTime) {

					webElement.sendKeys(answervalue);
					Thread.sleep(1500);
					String insertedTextValue = "";
					try {
						List<WebElement> TextBoxes_1 = driver.findElements(By.xpath(xpathAnswerTxtBox));
						insertedTextValue = TextBoxes_1.get(sequence - 1).getAttribute("value");
					} catch (Exception e) {
						Thread.sleep(3000);
						List<WebElement> TextBoxes_1 = driver.findElements(By.xpath(xpathAnswerTxtBox));
						insertedTextValue = TextBoxes_1.get(sequence - 1).getAttribute("value");
					}
					fnpMymsg("Value has been inserted ie. '" + insertedTextValue + "'  for Question --" + questNo);

					Assert.assertEquals(insertedTextValue, answervalue, "Fail due to Actual '" + insertedTextValue + "' and expected '" + answervalue + "' are not matched.");

					fnpMymsg(" ");

				}

			}
		} else {
			throw new Exception("No of Text boxes in Questionnare are either 0 or not present.");
		}

	}







	public static void fnpVerifyingOutputProp2(
												String taskType,
												String expectedbillcode,
												String expectedbillqty) throws Throwable {

		WebElement fac = driver.findElement(By.xpath(".//td/label[contains(@id,':wotype') and normalize-space(text())='" + taskType + "']"));

		String id = fac.getAttribute("id");

		String idArray[] = id.split(":");

		String jidt = "";
		int arrayIndex = 0;
		for (int i = 0; i < idArray.length; i++) {
			if (idArray[i].contains("j_idt")) {
				jidt = idArray[i];
				arrayIndex = i;
				break;
			}

		}

		String mainJ_idt = jidt + ":" + idArray[arrayIndex + 1];
		String mainTableXpath = ".//tbody[contains(@id,':" + mainJ_idt + ":')]";
		String trianglexpath = ".//td/label[contains(@id,':wotype') and normalize-space(text())='" + taskType + "']/../preceding-sibling::td/div";
		String firstbillcodeXpath = ".//label[contains(@id,':" + mainJ_idt + ":') and contains(@id,':bilcode') and normalize-space(text())='" + expectedbillcode + "']";

		if (!fnpCheckElementPresenceImmediately_NotInOR(firstbillcodeXpath)) {
			driver.findElement(By.xpath(trianglexpath)).click();
			Thread.sleep(5000);
		}

		// ===================
		WebElement billcode = driver.findElement(By.xpath(firstbillcodeXpath));
		String billcodeid = billcode.getAttribute("id");
		String billCodeidArray[] = billcodeid.split(":");

		String calculatedBilCodeRowNo = "";
		for (int i = 0; i < billCodeidArray.length; i++) {
			if (billCodeidArray[i].contains("bilcode")) {
				calculatedBilCodeRowNo = billCodeidArray[i - 1];
				break;
			}

		}

		// ===================

		int row = Integer.parseInt(calculatedBilCodeRowNo) + 1;

		WebElement billcodeElement = driver.findElement(By.xpath(firstbillcodeXpath));
		String actualBillCode = billcodeElement.getText();
		Assert.assertEquals(actualBillCode, expectedbillcode, "Bill code '" + expectedbillcode + "' either is not present/matched for " + taskType);
		String billqtyXpath = ".//label[contains(@id,':" + mainJ_idt + ":') and contains(@id,':" + (row - 1) + ":billqnt')]";
		WebElement billqtyElement = driver.findElement(By.xpath(billqtyXpath));
		String actualbillqty = billqtyElement.getText();
		Assert.assertEquals(actualbillqty, expectedbillqty, "Bill quantity '" + expectedbillqty + "' either is not present/matched for " + actualBillCode);

	}







	public static void fnpVerifyingOutputProp(
												String taskDescription,
												String expectedbillcode,
												String expectedbillqty) throws Throwable {

		String taskDescrXpath = ".//td/label[contains(@id,':wotask') and normalize-space(text())='" + taskDescription + "']";
		WebElement fac = driver.findElement(By.xpath(taskDescrXpath));
		String id = fac.getAttribute("id");
		String updatednewBillingCodeId = id.replace("wotask", "qtbill:");
		String trianglexpath = ".//td/label[contains(@id,':wotask') and normalize-space(text())='" + taskDescription + "']/../../td/div";

		String firstbillcodeXpath = ".//label[contains(@id,'" + updatednewBillingCodeId + "') and normalize-space(text())='" + expectedbillcode + "']";
		if (!fnpCheckElementPresenceImmediately_NotInOR(firstbillcodeXpath)) {
			// Thread.sleep(2000);
			fnpMouseHover_NotInOR(trianglexpath);
			driver.findElement(By.xpath(trianglexpath)).click();
			// Thread.sleep(5000);
			Thread.sleep(2000);

			if (!fnpCheckElementPresenceImmediately_NotInOR(firstbillcodeXpath)) {
				Thread.sleep(2000);
				fnpMouseHover_NotInOR(trianglexpath);
				driver.findElement(By.xpath(trianglexpath)).click();
			}

			fnpMymsg("To expand the task, triangle icon has been clicked.");
		}

		WebElement billcodeElement = driver.findElement(By.xpath(firstbillcodeXpath));
		String actualBillCode = billcodeElement.getText();
		fnpMymsg("Actual bill code is---" + actualBillCode);
		Assert.assertEquals(actualBillCode, expectedbillcode, "Bill code '" + expectedbillcode + "' either is not present/matched for " + taskDescription);
		fnpMymsg("Bill code '" + expectedbillcode + "'  is  present/matched successfully for " + taskDescription);

		String billingcodeid = billcodeElement.getAttribute("id");
		String updatednewBillingQTYId = billingcodeid.replace("bilcode", "billqnt");

		String billqtyXpath = ".//label[@id='" + updatednewBillingQTYId + "']";
		WebElement billqtyElement = driver.findElement(By.xpath(billqtyXpath));
		String actualbillqty = billqtyElement.getText();
		fnpMymsg("Actual bill quantity is---" + actualbillqty);
		double doubleActualBillQTY = Double.parseDouble(actualbillqty);
		double doubleExpectedBillQTY = Double.parseDouble(expectedbillqty);

		Assert.assertEquals(doubleActualBillQTY, doubleExpectedBillQTY, 1e-15, "Bill quantity '" + doubleExpectedBillQTY + "' either is not present/matched for " + actualBillCode);

		fnpMymsg("Bill quantity '" + doubleExpectedBillQTY + "'  is  present/matched successfully for " + expectedbillcode);

	}







	public static double fnpVerifyingOutputProp_withRate_N_ReturnTotalBillCodeCost(
																					String taskDescription,
																					String expectedbillcode,
																					String expectedbillqty,
																					String year,
																					String progCode) throws Throwable {

		String rate = "";
		String taskDescrXpath = ".//td/label[contains(@id,':wotask') and normalize-space(text())='" + taskDescription + "']";
		WebElement fac = driver.findElement(By.xpath(taskDescrXpath));
		String id = fac.getAttribute("id");
		String updatednewBillingCodeId = id.replace("wotask", "qtbill:");
		String trianglexpath = ".//td/label[contains(@id,':wotask') and normalize-space(text())='" + taskDescription + "']/../../td/div";
		String firstbillcodeXpath = ".//label[contains(@id,'" + updatednewBillingCodeId + "') and normalize-space(text())='" + expectedbillcode + "']";

		WebElement triangleElement = driver.findElement(By.xpath(trianglexpath));
		try {
			WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(CONFIG.getProperty("implicitWaitTime")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(trianglexpath)));
		} catch (Throwable t) {

			WebElement refElement = fnpGetORObjectX("Prop_GenerateQuoteBtn");
			fnpMymsg("As taskDescrXpath --" + taskDescrXpath + "  is not visible properly, so going to move down in If block");
			Actions action = new Actions(driver);
			action.moveToElement(refElement).build().perform();
		//	refElement.sendKeys(Keys.ARROW_DOWN);
			if (!browserName.equalsIgnoreCase("chrome")) 
				refElement.sendKeys(Keys.ARROW_DOWN);
			Thread.sleep(2000);
		}

		if (!fnpCheckElementPresenceImmediately_NotInOR(firstbillcodeXpath)) {

			fnpMouseHover_NotInOR(trianglexpath);

			driver.findElement(By.xpath(trianglexpath)).click();

			fnpWaitForVisible_NotInOR(firstbillcodeXpath);



			fnpMymsg("To expand the task, triangle icon has been clicked.");
		}

		// fnpHighlightByDoubleClick(firstbillcodeXpath);

		WebElement billcodeElement = driver.findElement(By.xpath(firstbillcodeXpath));
		try {

			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(firstbillcodeXpath)));

			Actions action = new Actions(driver);
			action.moveToElement(billcodeElement).doubleClick().build().perform();


		} catch (Throwable t) {

			WebElement Element1 = fnpGetORObjectX("Prop_GenerateQuoteBtn");
			fnpMymsg("As taskDescrXpath --" + taskDescrXpath + "  is not visible properly, so going to move down in If block");
			Actions action = new Actions(driver);
			action.moveToElement(Element1).build().perform();
		//	Element1.sendKeys(Keys.ARROW_DOWN);
			if (!browserName.equalsIgnoreCase("chrome")) 
				Element1.sendKeys(Keys.ARROW_DOWN);
			Thread.sleep(2000);
			fnpHighlightByDoubleClick(firstbillcodeXpath);

		}

		String actualBillCode = null;
		try {
		
			billcodeElement = driver.findElement(By.xpath(firstbillcodeXpath));
			actualBillCode = billcodeElement.getText();


		} catch (Throwable t) {
		
			WebElement Element1 = fnpGetORObjectX("Prop_GenerateQuoteBtn");
			fnpMymsg("As taskDescrXpath --" + taskDescrXpath + "  is not visible properly, so going to move down in If block");
			Actions action = new Actions(driver);
			action.moveToElement(Element1).build().perform();
		//	Element1.sendKeys(Keys.ARROW_DOWN);
			if (!browserName.equalsIgnoreCase("chrome")) 
				Element1.sendKeys(Keys.ARROW_DOWN);
			Thread.sleep(2000);
			fnpMouseHover_NotInOR(trianglexpath);
			driver.findElement(By.xpath(trianglexpath)).click();
			Thread.sleep(1000);
			fnpHighlightByDoubleClick(firstbillcodeXpath);
		
		}


		billcodeElement = driver.findElement(By.xpath(firstbillcodeXpath));
		actualBillCode = billcodeElement.getText();

		fnpMymsg("Actual bill code is---" + actualBillCode);
		Assert.assertEquals(actualBillCode, expectedbillcode, "Bill code '" + expectedbillcode + "' either is not present/matched for " + taskDescription);
		fnpMymsg("Bill code '" + expectedbillcode + "'  is  present/matched successfully for " + taskDescription);

		String billingcodeid = billcodeElement.getAttribute("id");
		String updatednewBillingQTYId = billingcodeid.replace("bilcode", "billqnt");
		String billqtyXpath = ".//label[@id='" + updatednewBillingQTYId + "']";
		fnpHighlightByDoubleClick(billqtyXpath);
		WebElement billqtyElement = driver.findElement(By.xpath(billqtyXpath));
		String actualbillqty = billqtyElement.getText();
		fnpMymsg("Actual bill quantity is---" + actualbillqty);

		double doubleActualBillQTY = Double.parseDouble(actualbillqty);
		double doubleExpectedBillQTY = Double.parseDouble(expectedbillqty);

		Assert.assertEquals(doubleActualBillQTY, doubleExpectedBillQTY, 1e-15, "Bill quantity '" + doubleExpectedBillQTY + "' either is not present/matched for " + actualBillCode);

		fnpMymsg("Bill quantity '" + doubleExpectedBillQTY + "'  is  present/matched successfully for " + expectedbillcode);

		rate = fnpFetchBillRateFromDB(actualBillCode, year, progCode);

		fnpMymsg("Expected Bill Code(" + actualBillCode + ")  Cost (fetch from database) is---" + rate);
		if (rate == null) {
			fnpMymsg("  Rate is returned by the Data base is: " + rate);
			throw new Exception("Null rate returned by the data base for this bill code '" + actualBillCode + "'");

		}

		String updatednewBillingRateId = billingcodeid.replace("bilcode", "billcost");

		String billCostXpath = ".//*[@id='" + updatednewBillingRateId + "']";
		fnpHighlightByDoubleClick(billCostXpath);
		WebElement billCostElement = driver.findElement(By.xpath(billCostXpath));
		String actualbillCost = billCostElement.getText();
		// fnpMymsg("Actual bill ("+actualBillCode+")  Cost is---"+actualbillCost);

		actualbillCost = actualbillCost.replace("$", "").trim();
		actualbillCost = actualbillCost.replace(",", "").trim();
		fnpMymsg("Actual Bill Code (" + actualBillCode + ") Cost (ie. displayed in Application) -- Afer processing (remove dollar and , comma)  is---" + actualbillCost);

		double doubleActualBillCost = Double.parseDouble(actualbillCost);
		double doubleExpectedBillCost = Double.parseDouble(rate);

		Assert.assertEquals(doubleActualBillCost, doubleExpectedBillCost, 1e-15, "Bill Cost '" + doubleExpectedBillCost + "' either is not present/matched for " + doubleActualBillCost);

		fnpMymsg("Hence Bill Code Cost '" + doubleExpectedBillCost + "'  is  equal/same as in Data base  for " + actualBillCode);

		fnpMymsg("");
		fnpMymsg("");

		String updatednewBillingRateTotalId = billingcodeid.replace("bilcode", "eatotalamt");
		String totalXpath = ".//*[@id='" + updatednewBillingRateTotalId + "']";
		fnpHighlightByDoubleClick(totalXpath);
		WebElement totalCostElement = driver.findElement(By.xpath(totalXpath));
		String actualTotalCost = totalCostElement.getText();
		// fnpMymsg("Actual Total Cost is---"+actualTotalCost);

		actualTotalCost = actualTotalCost.replace("$", "").trim();
		actualTotalCost = actualTotalCost.replace(",", "").trim();
		fnpMymsg("Actual Total Bill Code Cost (ie. displayed in application) -- Afer processing (remove dollar and , comma)  is---" + actualTotalCost);

		double doubleActualTotalCost = Double.parseDouble(actualTotalCost);
		double doubleExpectedTotalCost = Double.parseDouble(rate) * doubleActualBillQTY;
		fnpMymsg("Expected Total Bill Code Cost (i.e. calculated by multiply quantity[as displayed] by each cost[fetch from Database]  ) is---" + doubleExpectedTotalCost);

		Assert.assertEquals(doubleActualTotalCost, doubleExpectedTotalCost, 1e-15, "Total Cost '" + doubleExpectedTotalCost + "' either is not equal/matched for " + doubleActualTotalCost);

		fnpMymsg("Hence Total Bill Code Cost '" + doubleExpectedTotalCost + "'  is  equal/same as calculated  for " + actualBillCode);

		fnpMymsg("");
		fnpMymsg("");

		return doubleActualTotalCost;

	}







	public static void fnpCommonProposalDetailsTabWon(
														String Change_status,
														String BU_Manager_Code,
														String DocTab_AddPropDoc_FileName,
														String Type,
														String Access) throws Throwable {

		fnpClick_OR("Prop_DocumentsTab");

		
		
		
		String fileNames=DocTab_AddPropDoc_FileName;
		String[] fileCount = fileNames.split(",");
		int fileCountSize = fileCount.length;
		
		if (!(fileCountSize>0)) {
			throw new Exception("Upload file names should be given in excel.");
		} 
		
		
		String fileTypes=Type;
		String[] fileTypeCount = fileTypes.split(",");
		int fileTypesCountSize = fileCount.length;
		
		if (!(fileTypesCountSize==fileCountSize)) {
			throw new Exception("Upload file type should be equal to the no. of files in excel.");
		} 
		
		String fileAccessNames=Access;
		String[] fileAccessCount = fileAccessNames.split(",");
		int fileAccessCountSize = fileAccessCount.length;
		
		if (!(fileAccessCountSize==fileCountSize)) {
			throw new Exception("Upload file access should be equal to the no. of files  in excel.");
		} 
		
		
		
		
		String fileName="";

		
		String fname;
	
		
		if ((fnpGetCurrRunningBrowserName().equalsIgnoreCase("firefox"))) {
			
			for(int f=0;f<fileCountSize;f++){
				
				Thread.sleep(2000);		
				
				  fileName = System.getProperty("user.dir") + "\\docs\\" + fileCount[f];
				  

				
				fnpClick_OR("Prop_PropDoc_AddBtn");
				fnpWaitForVisible("Prop_DocTab_AddWODoc_SaveNCloseBtn");
				driver.findElement(By.xpath(OR.getProperty("Prop_PropDoc_SelectFilesBtn"))).sendKeys(fileName);
				Thread.sleep(1000);
				
				
				
				while (fnpCheckElementPresenceImmediately("BrowseCancelUploadBtn")) {
					Thread.sleep(1000);
				}
				fnpWaitForVisible("Prop_DocTab_AddWODoc_TypePFList");
				fnpPFList("Prop_DocTab_AddWODoc_TypePFList", "Prop_DocTab_AddWODoc_TypePFListOptions", fileTypeCount[f]);
				fnpPFList("Prop_DocTab_AddWODoc_AccessPFList", "Prop_DocTab_AddWODoc_AccessPFListOptions", fileAccessCount[f]);
				fnpClickInDialog_OR("Prop_DocTab_AddWODoc_SaveNCloseBtn");			
				fnpCheckError("Error is thrown by application while adding data in Document Tab");	
				
				Thread.sleep(2000);	
			}
			
			
			
		} else {
			
			
			for(int f=0;f<fileCountSize;f++){			
				Thread.sleep(2000);					
				fname = System.getProperty("user.dir") + "\\docs\\" + fileCount[f];
				  if (fnpGetCurrRunningBrowserName().equalsIgnoreCase("chrome")) {								  
					  if (f!=fileCountSize-1) {
						  fileName=fileName+fname+"\n";
					} else {
						fileName=fileName+fname;
					}
					  
				} else {
					 	if (fnpGetCurrRunningBrowserName().equalsIgnoreCase("IE")) {
					 		 fileName=fileName+"\""+fname+"\"";
						}				 	
				}						
			}
			

			
			fnpClick_OR("Prop_PropDoc_AddBtn");
			fnpWaitForVisible("Prop_DocTab_AddWODoc_SaveNCloseBtn");
			fnpMymsg("Going to upload files.");
		//	driver.findElement(By.xpath(OR.getProperty("Prop_PropDoc_SelectFilesBtn"))).sendKeys(fileName);
			
			
			
			String fname1 ;
			for(int f=0;f<fileCountSize;f++){			
				Thread.sleep(2000);					
				fname1 = System.getProperty("user.dir") + "\\docs\\" + fileCount[f];
				driver.findElement(By.xpath(OR.getProperty("Prop_PropDoc_SelectFilesBtn"))).sendKeys(fname1);
				Thread.sleep(1000);	
				fnpLoading_wait();
				Thread.sleep(3000);	
				fnpLoading_wait();
				
				int whileloopcounter=0;
				while (fnpCheckElementPresenceImmediately("BrowseCancelUploadBtn") ) {
					whileloopcounter++;
					if ((whileloopcounter > Integer.parseInt(CONFIG.getProperty("genMax_waitTime")))) {
						fnpMymsg("@  ---still BrowseCancelUploadBtn is present after too much time ---"+whileloopcounter+" seconds");
						break;
					}else{
						fnpMymsg("@  --- BrowseCancelUploadBtn is present after  time ---"+whileloopcounter+" seconds");	
					}
					Thread.sleep(1000);
				}
				
				
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			//Pradeep--You can comment below lines later for wait
		/***********************************************************/	
			fnpMymsg("Hit the file Names.");
			Thread.sleep(1000);
			fnpLoading_wait();
			fnpLoading_waitInsideDialogBox();
			Thread.sleep(1000);
		/***********************************************************/	

			int whileloopcounter=0;
			while (fnpCheckElementPresenceImmediately("BrowseCancelUploadBtn") ) {
				whileloopcounter++;
				if ((whileloopcounter > Integer.parseInt(CONFIG.getProperty("genMax_waitTime")))) {
					fnpMymsg("@  ---still BrowseCancelUploadBtn is present after too much time ---"+whileloopcounter+" seconds");
					break;
				}else{
					fnpMymsg("@  --- BrowseCancelUploadBtn is present after  time ---"+whileloopcounter+" seconds");	
				}
				Thread.sleep(1000);
			}
			
			
			
			
			
			String typelist;
			String accessList;
			for(int f=0;f<fileCountSize;f++){	
				typelist=OR.getProperty("Prop_DocTab_AddWODoc_TypePFList_part1")+f+OR.getProperty("Prop_DocTab_AddWODoc_TypePFList_part2");
				accessList=OR.getProperty("Prop_DocTab_AddWODoc_AccessPFList_part1")+f+OR.getProperty("Prop_DocTab_AddWODoc_AccessPFList_part2");
				fnpPFListModify_NOR(typelist, fileTypeCount[f]);
				Thread.sleep(2000);	
				fnpPFListModify_NOR(accessList, fileAccessCount[f]);
			}
			
			fnpClickInDialog_OR("Prop_DocTab_AddWODoc_SaveNCloseBtn");			
			fnpCheckError("Error is thrown by application while adding data in Document Tab");	
		}
		
		
		
		
		

		
		


		
		Thread.sleep(2000);	
		
		

		
		fnpCheckError("Error is thrown by application while adding data in Document Tab");

		
		
		fnpWaitForVisible("Prop_ProposalDocumentTable_Heading");
		 int DocumentrowCount = fnpCountRowsInTable("Prop_ProposalDocumentTable");


		if (DocumentrowCount == fileCountSize) {
			fnpMymsg(" All Uploaded document are showing/displaying in table.");

		} else {
			fnpMymsg(" All Uploaded document are NOT showing/displaying in table as uploaded '"+fileCountSize+"' but displaying in table only '"+DocumentrowCount+"'.");
			throw new Exception(" All Uploaded document are NOT showing/displaying in table as uploaded '"+fileCountSize+"' but displaying in table only '"+DocumentrowCount+"'.");

		}

		
		
		
		
		
		
		fnpMymsg(" Adding data in  Documents tab successfully.");

		fnpClick_OR_WithoutWait("Prop_ProposalDetailsTab");

		fnpWaitForVisible("Prop_StatusList");
		fnpPFList("Prop_StatusList", "Prop_StatusListOptions", Change_status);

		fnpGetORObjectX("Prop_SaveBtn").click();
		fnpLoading_wait();

		
		fnpCheckError("Error is thrown by application after changing status 'WON' in Status list");

		//	fnpWaitForVisible("TopMessage3");
		fnpWaitForVisibleHavingMultipleElements("TopMessage3");
		String SuccessfulMsg = fnpGetText_OR("TopMessage3");
		fnpMymsg("Top Message after changing status 'WON' in Status list  ----" + SuccessfulMsg);

		Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"), "Top message does not contain 'success' word, so might be status 'WON'   has not been changed/updated successfully");

		String status = fnpGetText_OR("Prop_StatusList");
		Assert.assertTrue(status.equalsIgnoreCase("WON"), "Status is not become to 'WON' .");
		fnpMymsg("Proposal status has become to 'WON' now.");

		fnpMymsg("****************************************************************");

	}







	public static String fnpremoveFormatting(
												String s) {

		s = s.replace("[", " ");
		s = s.replace("]", " ");
		s = s.replace("(", " ");
		s = s.replace(")", " ");
		s = s.trim();

		return s;

	}







	// Function to fetch bill rate from DB
	public static String fnpFetchBillRateFromDB(
												String billcode,
												String in_year,
												String prog_code) throws Throwable {
		String rate = null;

		fnpMymsg("******************");
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			fnpMymsg("******* Oracle JDBC Driver Registered *******");
		} catch (ClassNotFoundException e) {
			fnpMymsg("****************Oracle JDBC Driver is NOT found *************");
			fnpMymsg("########################################################################");
			e.printStackTrace();
			throw new Exception(e.getMessage());
			// return;
		}

		Connection connection = null;
		try {
			//connection = DriverManager.getConnection("jdbc:oracle:thin:@dbserv13vm:1521:certtest", "testscriptuser", "test4nsf");
			 connection=fnpGetDBConnection();
			Statement stmt = connection.createStatement();

			/*
			 * String query1 = "SELECT rate " + " FROM nsf_ipulse_bill_codes_rate_mvw v" + " WHERE TRIM (UPPER (v.bill_code)) = TRIM (UPPER ('" + billcode +
			 * "'))" + " and rev_prog_code ='" + prog_code + "'" + " AND DECODE ('" + in_year + "'," + "  TO_CHAR (SYSDATE, 'RRRR'), SYSDATE," +
			 * " TO_DATE ('1/1/' || '" + in_year + "', 'mm/dd/yyyy')" + " ) BETWEEN v.start_date_active" + " AND DECODE (v.end_date_active," +
			 * " NULL, TO_DATE ('1/1/2200', 'mm/dd/yyyy')," + "  v.end_date_active" + " )";
			 */
/*			
			@SuppressWarnings("SQL_PREPARED_STATEMENT_GENERATED_FROM_NONCONSTANT_STRING")
			String query1 = "SELECT rate " + " FROM nsf_ipulse_bill_codes_rate_mvw v" + " WHERE TRIM (UPPER (v.bill_code)) = TRIM (UPPER ('" + billcode + "'))" + " and rev_prog_code ='" + prog_code
					+ "'" + " AND DECODE ('" + in_year + "'," + "  TO_CHAR (SYSDATE, 'RRRR'), SYSDATE," + "TO_CHAR (SYSDATE, 'RRRR') -1,  TO_DATE ('12/31/' || '" + in_year + "', 'mm/dd/yyyy'),"
					+ " TO_DATE ('1/1/' || '" + in_year + "', 'mm/dd/yyyy')" + " ) BETWEEN v.start_date_active" + " AND DECODE (v.end_date_active," + " NULL, TO_DATE ('1/1/2200', 'mm/dd/yyyy'),"
					+ "  v.end_date_active" + " )";
			
			*/
			String query1 = String.format("SELECT rate  FROM nsf_ipulse_bill_codes_rate_mvw v WHERE TRIM (UPPER (v.bill_code)) = TRIM (UPPER ('%s')) and rev_prog_code ='%s' AND DECODE ('%s',  TO_CHAR (SYSDATE, 'RRRR'), SYSDATE,TO_CHAR (SYSDATE, 'RRRR') -1,  TO_DATE ('12/31/' || '%s', 'mm/dd/yyyy'), TO_DATE ('1/1/' || '%s', 'mm/dd/yyyy') ) BETWEEN v.start_date_active AND DECODE (v.end_date_active, NULL, TO_DATE ('1/1/2200', 'mm/dd/yyyy'),  v.end_date_active )",billcode,prog_code,in_year,in_year,in_year);
			

			ResultSet rst = stmt.executeQuery(query1);

			while (rst.next()) {
				rate = (rst.getString("rate"));
			}
			rst.close();

			connection.close();

		} catch (SQLException e) {
			fnpMymsg("*********************************************** Connection Failed! with Database,   Getting Error >>  " + e.getMessage().trim());
			fnpMymsg("=========================================================================================================================================");
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		fnpMymsg("******************");

		return rate;
	}







	//
	public static void fnpHighlightByDoubleClick(
													String XpathKey) throws Exception {

		try {
			Actions action = new Actions(driver);
			WebElement Element1 = driver.findElement(By.xpath(XpathKey));
			action.moveToElement(Element1).doubleClick().build().perform();
		} catch (Throwable e) {

		}

	}

	
/*	
	
	public static void fnpCommonCodeProposalFromCheckingUnansweredToPreviewQuoteLinkDisabled(String AnnualAmount,String Rush_Fees,String Discount_Percent,String Discount_Reason) throws Throwable{
		

		
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
		Thread.sleep(1000);

		// =====================================================================================
		fnpWaitForVisible("Prop_RushFees_txt");

		fnpMymsg("  ---Annual Amount is ---"+AnnualAmount);
		fnpType("OR","Prop_AnnualCost_txt",AnnualAmount );
		
		
		fnpGetORObjectX("Prop_RushFees_txt").clear();
		fnpType("OR","Prop_RushFees_txt",Rush_Fees );
		//fnpGetORObjectX("Prop_Discount_PercentTxt").sendKeys(Discount_Percent);
		fnpType("OR","Prop_Discount_PercentTxt",Discount_Percent );
		Thread.sleep(4000);
		Thread.sleep(4000);
		
	

		fnpMove_To_Element_and_DoubleClick_NOR(OR.getProperty("Prop_Discount_ReasonTxt"));


		
		fnpType("OR","Prop_Discount_ReasonTxt",Discount_Reason );
		//fnpTypeInField(OR.getProperty("Prop_Discount_ReasonTxt"), Discount_Reason);
		fnpGetORObjectX("Prop_Discount_ReasonTxt").click();
		//Thread.sleep(1000);	
		
		
		
		
		*//**************This is work around to refresh as soon as possible discount amount ****************//*
		Thread.sleep(1000);
		fnpMove_To_Element_and_DoubleClick_NOR(OR.getProperty("Prop_Discount_ReasonTxt"));
	
			
		Thread.sleep(1000);
		fnpMove_To_Element_and_DoubleClick_NOR(OR.getProperty("Prop_TotalTaskCost"));
		
		
		Thread.sleep(1000);
		fnpMove_To_Element_and_DoubleClick_NOR(OR.getProperty("Prop_Discount_AmountTxt"));
				
		Thread.sleep(1000);
		fnpMove_To_Element_and_DoubleClick_NOR(OR.getProperty("Prop_TotalQuoteAmountCalculated"));
		
		*//********************************************************************************************//*
		
		
		
		
		
		
		//Thread.sleep(4000);

		String TotalTaskCost = fnpGetText_OR("Prop_TotalTaskCost").trim();
		TotalTaskCost = TotalTaskCost.replace("$", "");
		TotalTaskCost = TotalTaskCost.replace(",", "");
		double TotalTaskCostdbl = Double.parseDouble(TotalTaskCost);
		fnpMymsg("Total Task Cost is ---" + TotalTaskCostdbl);

		//Thread.sleep(7000);
		double discountRate = Double.parseDouble(Discount_Percent);

		double rushFeesAmount = Double.parseDouble(Rush_Fees.replaceAll(",", ""));
		double ExpectedDiscountAmount = ((discountRate * TotalTaskCostdbl) / 100);
		String ActualDiscountAmount = fnpGetORObjectX("Prop_Discount_AmountTxt").getAttribute("value");

		 fnpMymsg("Actual Discount Amount  is:" + ActualDiscountAmount);
		ActualDiscountAmount = ActualDiscountAmount.replace("(", "");
		ActualDiscountAmount = ActualDiscountAmount.replace(")", "");
		ActualDiscountAmount = ActualDiscountAmount.replace("$", "");
		ActualDiscountAmount = ActualDiscountAmount.replace(",", "");
		double ActualDiscountAmountdble = Double.parseDouble(ActualDiscountAmount);
		fnpMymsg("Actual Discount Amount (after formatting) is:" + ActualDiscountAmount);
		int timer = 0;
		while ((ActualDiscountAmountdble == 0)| (ActualDiscountAmountdble == 0.0)   | (ActualDiscountAmountdble == 0.00)) {
			timer = timer + 1;
			Thread.sleep(1000);
			fnpMymsg("In while loop of work around to refresh as soon as possible discount amount for chance ---"+ timer);
			
			fnpMymsg("Actual Discount Amount (after formatting) is:" + ActualDiscountAmount);
			
			
			
			
			*//**************This is work around to refresh as soon as possible discount amount ****************//*
			
			
			fnpGetORObjectX("Prop_Discount_PercentTxt").clear();
			Thread.sleep(1000);
			fnpType("OR","Prop_Discount_PercentTxt",Discount_Percent );
			//fnpGetORObjectX("Prop_Discount_PercentTxt").sendKeys(Discount_Percent);
			Thread.sleep(4000);
			Thread.sleep(4000);
			
			
			


			fnpGetORObjectX("Prop_Discount_ReasonTxt").click();
			//fnpGetORObjectX("Prop_Discount_PercentTxt").click();
			

			
			Thread.sleep(1000);
			fnpMove_To_Element_and_DoubleClick_NOR(OR.getProperty("Prop_Discount_ReasonTxt"));
		
	

			Thread.sleep(1000);
			fnpMove_To_Element_and_DoubleClick_NOR(OR.getProperty("Prop_TotalTaskCost"));
			
			Thread.sleep(1000);
			fnpMove_To_Element_and_DoubleClick_NOR(OR.getProperty("Prop_Discount_AmountTxt"));
			
			
			Thread.sleep(1000);
			fnpMove_To_Element_and_DoubleClick_NOR(OR.getProperty("Prop_TotalQuoteAmountCalculated"));
			
			*//********************************************************************************************//*
			
			
			
			
			
			
			
			
			
			
			
			
			

			ActualDiscountAmount = fnpGetORObjectX("Prop_Discount_AmountTxt").getAttribute("value");

			ActualDiscountAmount = ActualDiscountAmount.replace("(", "");
			ActualDiscountAmount = ActualDiscountAmount.replace(")", "");
			ActualDiscountAmount = ActualDiscountAmount.replace("$", "");
			ActualDiscountAmount = ActualDiscountAmount.replace(",", "");
			ActualDiscountAmountdble = Double.parseDouble(ActualDiscountAmount);
			fnpMymsg("  ----Discount Amount  is:" + ActualDiscountAmountdble);
			if (timer > Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
				fnpMymsg("Discount Amount is not coming after inserting values '"+timer+"' times in Discount % and Discount reason");
				throw new Exception("Discount Amount is not coming after inserting values '"+timer+"' times in Discount % and Discount reason");
				//break;
			}

			
			
			
			
			
			

			
			
			
			
		}

		fnpMymsg("Actual Discount Amount  is:" + ActualDiscountAmountdble);
		fnpMymsg("Expected Discount Amount is:" + ExpectedDiscountAmount);
		Assert.assertEquals(ActualDiscountAmountdble, ExpectedDiscountAmount, 1e-15, "Discounted Amount is not matched.");
		fnpMymsg("Actual and Expected discount is matched.");
		String ActualTotalQuoteAmount = fnpGetText_OR("Prop_TotalQuoteAmountCalculated");
		ActualTotalQuoteAmount = ActualTotalQuoteAmount.replace("$", "");
		ActualTotalQuoteAmount = ActualTotalQuoteAmount.replace(",", "");
		double ActualTotalQuoteAmountdble = Double.parseDouble(ActualTotalQuoteAmount);

		double ExpectedTotalQuoteAmountdble = TotalTaskCostdbl + rushFeesAmount - ActualDiscountAmountdble;

		Assert.assertEquals(ActualTotalQuoteAmountdble, ExpectedTotalQuoteAmountdble, 1e-15, "Total Quote Amount is not correct");
		fnpMymsg("Total Amount is correct----Actual is:" + ActualTotalQuoteAmountdble);
		fnpMymsg(" ------------------------Expected is:" + ExpectedTotalQuoteAmountdble);

		fnpClick_OR("Prop_GenerateQuoteBtn");

		fnpWaitForVisible("Prop_QuoteGenerationConfirYesBtn");
		
		
		//   uncomment below lines as they are part of flow
		
		
		fnpClick_OR("Prop_QuoteGenerationConfirYesBtn");
		fnpLoadingGeneratingQuote();
		
		// '============================================================================='

		fnpLoading_wait();
		// Thread.sleep(1000);
		fnpLoading_wait();

		
		
		fnpCheckError(" Error is thrown by application while saving  preview quote ");
		

		//	fnpWaitForVisible("TopMessage3");
		fnpWaitForVisibleHavingMultipleElements("TopMessage3");
		fnpMouseHover("Prop_StatusList");

		String SuccessfulMsg = fnpGetText_OR("TopMessage3");
		fnpMymsg("Top Message after saving  preview quote ----" + SuccessfulMsg);

		Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"), "Top message does not contain 'success' word, so might be preview quote has not been saved successfully");

		Assert.assertTrue(
				SuccessfulMsg.equalsIgnoreCase("Quote generated successfully and report is saved to eFileRoom"),
				"Top message does not showing 'Quote generated successfully and report is saved to eFileRoom' message, so might be preview quote has not been saved successfully");

		String status = fnpGetText_OR("Prop_StatusList");
		Assert.assertTrue(status.equalsIgnoreCase("Quoted"), "Status is not become to 'Quoted' .");

		if (fnpCheckElementPresenceImmediately("Prop_PDetails_QuestionnaireEditLink")) {
			fnpMymsg(" Link on Questionnare Edit text is still present and it should not be a link now.");
			throw new Exception(" Link on Questionnare Edit text is still present and it should not be a link now.");
		}
		fnpMymsg(" Link on Questionnare Edit text is no longer present now.");

		if (fnpCheckElementPresenceImmediately("Prop_PreviewQuoteLink")) {
			fnpMymsg(" Link on Preview Quote text is still present and it should not be a link now.");
			throw new Exception(" Link on Preview Quote text is still present and it should not be a link now.");
		} else {
			fnpMymsg(" Link on Preview Quote text is no longer present now.");
		}
		
		
		
		
		
		

	}
	
	*/
	
	
	
	
	
	
	
	public static void fnpCommonCodeProposalFromCheckingUnansweredToPreviewQuoteLinkDisabled(Hashtable <String,String>table) throws Throwable{
		

		
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
		Thread.sleep(1000);

		
		
		
		if (!(classNameText.equalsIgnoreCase(ClassName_Create_WO_from_Proposal)))    {
				// =====================================================================================
				fnpWaitForVisible("Prop_RushFees_txt");
		
				fnpMymsg("  ---Annual Amount is ---"+table.get("Annual_Cost"));
				fnpType("OR","Prop_AnnualCost_txt",table.get("Annual_Cost") );
				
				
				fnpGetORObjectX("Prop_RushFees_txt").clear();
				Thread.sleep(4000);
				
			//	fnpType("OR","Prop_RushFees_txt",table.get("Rush_Fees") );
				fnpGetORObjectX("Prop_RushFees_txt").sendKeys(table.get("Rush_Fees"));
				fnpLoading_wait();
				Thread.sleep(4000);
				//fnpDesireScreenshot_old("Just after entering RushFees1_");
				fnpGetORObjectX("Prop_Discount_PercentTxt").click();
				//fnpDesireScreenshot_old("Just after entering RushFees2_");
				
				fnpGetORObjectX("Prop_Discount_PercentTxt").clear();
				Thread.sleep(4000);
				fnpGetORObjectX("Prop_Discount_PercentTxt").sendKeys(table.get("Discount_Percent"));
				//fnpType("OR","Prop_Discount_PercentTxt",table.get("Discount_Percent") );
				Thread.sleep(4000);
				Thread.sleep(4000);
				
			
		
				fnpMove_To_Element_and_DoubleClick_NOR(OR.getProperty("Prop_Discount_ReasonTxt"));
		
		
				
				fnpType("OR","Prop_Discount_ReasonTxt",table.get("Discount_Reason") );
				//fnpTypeInField(OR.getProperty("Prop_Discount_ReasonTxt"), Discount_Reason);
				fnpGetORObjectX("Prop_Discount_ReasonTxt").click();
				//Thread.sleep(1000);	
				
				
				
				
				/**************This is work around to refresh as soon as possible discount amount ****************/
				Thread.sleep(1000);
				fnpMove_To_Element_and_DoubleClick_NOR(OR.getProperty("Prop_Discount_ReasonTxt"));
			
					
				Thread.sleep(1000);
				fnpMove_To_Element_and_DoubleClick_NOR(OR.getProperty("Prop_TotalTaskCost"));
				
				
				Thread.sleep(1000);
				fnpMove_To_Element_and_DoubleClick_NOR(OR.getProperty("Prop_Discount_AmountTxt"));
						
				Thread.sleep(1000);
				fnpMove_To_Element_and_DoubleClick_NOR(OR.getProperty("Prop_TotalQuoteAmountCalculated"));
				
				/********************************************************************************************/
				
				
				
				
				
				
				//Thread.sleep(4000);
		
				String TotalTaskCost = fnpGetText_OR("Prop_TotalTaskCost").trim();
				TotalTaskCost = TotalTaskCost.replace("$", "");
				TotalTaskCost = TotalTaskCost.replace(",", "");
				double TotalTaskCostdbl = Double.parseDouble(TotalTaskCost);
				fnpMymsg("Total Task Cost is ---" + TotalTaskCostdbl);
		
				//Thread.sleep(7000);
				double discountRate = Double.parseDouble(table.get("Discount_Percent"));
		
				double rushFeesAmount = Double.parseDouble(table.get("Rush_Fees").replaceAll(",", ""));
				double ExpectedDiscountAmount = ((discountRate * TotalTaskCostdbl) / 100);
				String ActualDiscountAmount = fnpGetORObjectX("Prop_Discount_AmountTxt").getAttribute("value");
		
				 fnpMymsg("Actual Discount Amount  is:" + ActualDiscountAmount);
				ActualDiscountAmount = ActualDiscountAmount.replace("(", "");
				ActualDiscountAmount = ActualDiscountAmount.replace(")", "");
				ActualDiscountAmount = ActualDiscountAmount.replace("$", "");
				ActualDiscountAmount = ActualDiscountAmount.replace(",", "");
				
				//ActualDiscountAmount = ActualDiscountAmount.replace("-", "");
				
				double ActualDiscountAmountdble = Double.parseDouble(ActualDiscountAmount);
				
/*				double ActualDiscountAmountdble;
				if (ActualDiscountAmount.trim().equalsIgnoreCase("")) {
					//nothing to do
					ActualDiscountAmountdble=0;
				}else{
					ActualDiscountAmountdble = Double.parseDouble(ActualDiscountAmount);
				}
				
				*/
				
				
				fnpMymsg("Actual Discount Amount (after formatting) is:" + ActualDiscountAmount);
				int timer = 0;
				while ((ActualDiscountAmountdble == 0)| (ActualDiscountAmountdble == 0.0)   | (ActualDiscountAmountdble == 0.00)  ) {
				//while ((ActualDiscountAmountdble == 0)| (ActualDiscountAmountdble == 0.0)   | (ActualDiscountAmountdble == 0.00)  | ActualDiscountAmount.trim().equalsIgnoreCase("")) {
					timer = timer + 1;
					Thread.sleep(1000);
					fnpMymsg("In while loop of work around to refresh as soon as possible discount amount for chance ---"+ timer);
					
					fnpMymsg("Actual Discount Amount (after formatting) is:" + ActualDiscountAmount);
					
					
					
					
					/**************This is work around to refresh as soon as possible discount amount ****************/
					
					
					fnpGetORObjectX("Prop_Discount_PercentTxt").clear();
					Thread.sleep(1000);
					//fnpType("OR","Prop_Discount_PercentTxt",table.get("Discount_Percent") );
					fnpGetORObjectX("Prop_Discount_PercentTxt").sendKeys(table.get("Discount_Percent"));
					Thread.sleep(4000);
					Thread.sleep(4000);
					
					
					
		
		
					fnpGetORObjectX("Prop_Discount_ReasonTxt").click();
					//fnpGetORObjectX("Prop_Discount_PercentTxt").click();
					
		
					
					Thread.sleep(1000);
					fnpMove_To_Element_and_DoubleClick_NOR(OR.getProperty("Prop_Discount_ReasonTxt"));
				
			
		
					Thread.sleep(1000);
					fnpMove_To_Element_and_DoubleClick_NOR(OR.getProperty("Prop_TotalTaskCost"));
					
					Thread.sleep(1000);
					fnpMove_To_Element_and_DoubleClick_NOR(OR.getProperty("Prop_Discount_AmountTxt"));
					
					
					Thread.sleep(1000);
					fnpMove_To_Element_and_DoubleClick_NOR(OR.getProperty("Prop_TotalQuoteAmountCalculated"));
					
					/********************************************************************************************/
					
					
		
					
					
		
					ActualDiscountAmount = fnpGetORObjectX("Prop_Discount_AmountTxt").getAttribute("value");
		
					ActualDiscountAmount = ActualDiscountAmount.replace("(", "");
					ActualDiscountAmount = ActualDiscountAmount.replace(")", "");
					ActualDiscountAmount = ActualDiscountAmount.replace("$", "");
					ActualDiscountAmount = ActualDiscountAmount.replace(",", "");
					
				//	ActualDiscountAmount = ActualDiscountAmount.replace("-", "");
					
					ActualDiscountAmountdble = Double.parseDouble(ActualDiscountAmount);
					
/*					//double ActualDiscountAmountdble;
					if (ActualDiscountAmount.trim().equalsIgnoreCase("")) {
						//nothing to do
						ActualDiscountAmountdble=0;
					}else{
						ActualDiscountAmountdble = Double.parseDouble(ActualDiscountAmount);
					}
					
					
					*/
					
					fnpMymsg("  ----Discount Amount  is:" + ActualDiscountAmountdble);
					if (timer > Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
						fnpMymsg("Discount Amount is not coming after inserting values '"+timer+"' times in Discount % and Discount reason");
						throw new Exception("Discount Amount is not coming after inserting values '"+timer+"' times in Discount % and Discount reason");
						//break;
					}
		
					
					
					
					
					
					
		
					
					
					
					
				}
		
				fnpMymsg("Actual Discount Amount  is:" + ActualDiscountAmountdble);
				fnpMymsg("Expected Discount Amount is:" + ExpectedDiscountAmount);
				Assert.assertEquals(ActualDiscountAmountdble, ExpectedDiscountAmount, 1e-15, "Discounted Amount is not matched.");
				fnpMymsg("Actual and Expected discount is matched.");
				String ActualTotalQuoteAmount = fnpGetText_OR("Prop_TotalQuoteAmountCalculated");
				ActualTotalQuoteAmount = ActualTotalQuoteAmount.replace("$", "");
				ActualTotalQuoteAmount = ActualTotalQuoteAmount.replace(",", "");
				double ActualTotalQuoteAmountdble = Double.parseDouble(ActualTotalQuoteAmount);
		
				double ExpectedTotalQuoteAmountdble = TotalTaskCostdbl + rushFeesAmount - ActualDiscountAmountdble;
		
				Assert.assertEquals(ActualTotalQuoteAmountdble, ExpectedTotalQuoteAmountdble, 1e-15, "Total Quote Amount is not correct");
				fnpMymsg("Total Amount is correct----Actual is:" + ActualTotalQuoteAmountdble);
				fnpMymsg(" ------------------------Expected is:" + ExpectedTotalQuoteAmountdble);
				
				
				
				
				
		}		
		
		
		
		

		fnpClick_OR("Prop_GenerateQuoteBtn");
		
		if ((classNameText.equalsIgnoreCase(ClassName_Create_WO_from_Proposal)))    {
			//This confirmation button is not coming in create_wo_from_Proposal as we are not inserting any value on this screen
			//nothing to do
		}else{
			//fnpWaitForVisible("Prop_QuoteGenerationConfirYesBtn");
			fnpClick_OR("Prop_QuoteGenerationConfirYesBtn");
		}


		
		
		fnpLoadingGeneratingQuote();
		
		// '============================================================================='

		//commenting on 21-05-2018 to avoid too much wait at this point, if you find any issue later then uncomment below
	//	fnpLoading_wait();
		// Thread.sleep(1000);
		//fnpLoading_wait();

		
		
		fnpCheckError(" Error is thrown by application while saving  preview quote ");
		

		//	fnpWaitForVisible("TopMessage3");
		fnpWaitForVisibleHavingMultipleElements("TopMessage3");
		fnpMouseHover("Prop_StatusList");

		String SuccessfulMsg = fnpGetText_OR("TopMessage3");
		fnpMymsg("Top Message after saving  preview quote ----" + SuccessfulMsg);

		Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"), "Top message does not contain 'success' word, so might be preview quote has not been saved successfully");

		Assert.assertTrue(
				SuccessfulMsg.equalsIgnoreCase("Quote generated successfully and report is saved to eFileRoom"),
				"Top message does not showing 'Quote generated successfully and report is saved to eFileRoom' message, so might be preview quote has not been saved successfully");

		String status = fnpGetText_OR("Prop_StatusList");
		Assert.assertTrue(status.equalsIgnoreCase("Quoted"), "Status is not become to 'Quoted' .");

		if (fnpCheckElementPresenceImmediately("Prop_PDetails_QuestionnaireEditLink")) {
			fnpMymsg(" Link on Questionnare Edit text is still present and it should not be a link now.");
			throw new Exception(" Link on Questionnare Edit text is still present and it should not be a link now.");
		}
		fnpMymsg(" Link on Questionnare Edit text is no longer present now.");

		if (fnpCheckElementPresenceImmediately("Prop_PreviewQuoteLink")) {
			fnpMymsg(" Link on Preview Quote text is still present and it should not be a link now.");
			throw new Exception(" Link on Preview Quote text is still present and it should not be a link now.");
		} else {
			fnpMymsg(" Link on Preview Quote text is no longer present now.");
		}
		
		
		
		
		
		

	}
	
	
	
	
	
	
	
	
	
	
	
	//this become old on 07-09-2021, You can delete it later
	public static void fnpCommonProcessQuestionnairesSet_old(String questionnaireSet) throws Throwable{
		
		
		fnpMymsg("");
		fnpMymsg(" Now going to insert the values in Questionnaire .");
		//String NoOfSets[] = table.get("QuestionNAnswerSets").split("::");
		String NoOfSets[] = questionnaireSet.split("::");
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


				
				
				try{
					
					fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
					fnpFillAnswerToPropQuestionnaireDynamically_processHiddenAlso(QuestionNo, AnswerValue);
					fnpLoading_wait();
					fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
				}catch(Throwable t){
					if (   (t.getMessage().contains("Fail due to Actual"))     &&  (t.getMessage().contains("are not matched."))  )  {
						fnpMymsg("As value has not selected properly, so we are going to select it properly once again.");
						fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
						fnpFillAnswerToPropQuestionnaireDynamically_processHiddenAlso(QuestionNo, AnswerValue);
						fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
					}else{
						
						if (   (t.getMessage().contains("stale element reference")) ){
							// if (retries < 3) {
							Thread.sleep(1000);
							fnpMymsg("In staleElementException catch block of fnpFillAnswerToPropQuestionnaireDynamically_processHiddenAlso function. " );
							fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
							fnpFillAnswerToPropQuestionnaireDynamically_processHiddenAlso(QuestionNo, AnswerValue);
							fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
						} else {
							throw t;
						}
						
						
					}
				}
				
				
				
				
				

				//Thread.sleep(2000);//commented on 19th feb	
				Thread.sleep(500);

			}

			if (NoOfSets.length > 1 && j < (NoOfSets.length - 1)) {
				//Thread.sleep(1000);

				//WebElement saveNnextBtn = fnpGetORObjectX("Prop_Questionnaire_SaveNNextBtn");
				WebElement saveNnextBtn = driver.findElement(By.xpath(OR.getProperty("Prop_Questionnaire_SaveNNextBtn")));
				Actions action = new Actions(driver);
				action.moveToElement(saveNnextBtn).build().perform();
				if (!browserName.equalsIgnoreCase("chrome")) {
				saveNnextBtn.sendKeys(Keys.ARROW_UP);
				saveNnextBtn.sendKeys(Keys.ARROW_UP);
				saveNnextBtn.sendKeys(Keys.ARROW_UP);
				}
			//	fnpClick_OR("Prop_Questionnaire_SaveNNextBtn");
				//fnpClickInDialog_OR("Prop_Questionnaire_SaveNNextBtn");
				driver.findElement(By.xpath(OR.getProperty("Prop_Questionnaire_SaveNNextBtn"))).click();
				 Thread.sleep(8000);
			//	Thread.sleep(2000);
			}

		}
		
		

		if  (    (currentSuiteName.equalsIgnoreCase("ISR_suite"))   || (currentSuiteName.equalsIgnoreCase("SCFS_suite")) ) {             
			fnpClickInDialog_OR("Prop_QuestionnaireCloseBtnInISR");
		} else {
			fnpClickInDialog_OR("Prop_QuestionnaireCloseBtn");
		}
		
		//}
}
	
	
	
	
	
	
	public static void fnpCommonProcessQuestionnairesSet(String questionnaireSet) throws Throwable{
		
		
		
		fnpMymsg("");
		fnpMymsg(" Now going to insert the values in Questionnaire .");
		//String NoOfSets[] = table.get("QuestionNAnswerSets").split("::");
		String NoOfSets[] = questionnaireSet.split("::");
		fnpMymsg("No. of question sets are ---" + NoOfSets.length);
		fnpMymsg("");
		fnpMymsg("");
		
		

		
		
		
		
		

		for (int j = 0; j < NoOfSets.length; j++) {
			if (NoOfSets.length > 1 && j < (NoOfSets.length - 1)) {
				// Thread.sleep(1000);
				fnpWaitForVisible("Prop_Questionnaire_SaveNNextBtn");
			}
			
			if (fnpCheckElementDisplayedImmediately("Prop_Questionnaire_UnmarkSectionNA_Btn_inTabView")) {
				fnpClick_OR("Prop_Questionnaire_UnmarkSectionNA_Btn_inTabView");
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


				
				
				try{
					
					fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
					fnpFillAnswerToPropQuestionnaireDynamically_processHiddenAlso(QuestionNo, AnswerValue);
					fnpLoading_wait();
					fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
				}catch(Throwable t){
					if (   (t.getMessage().contains("Fail due to Actual"))     &&  (t.getMessage().contains("are not matched."))  )  {
						fnpMymsg("As value has not selected properly, so we are going to select it properly once again.");
						fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
						fnpFillAnswerToPropQuestionnaireDynamically_processHiddenAlso(QuestionNo, AnswerValue);
						fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
					}else{
						
						if (   (t.getMessage().contains("stale element reference")) ){
							// if (retries < 3) {
							Thread.sleep(1000);
							fnpMymsg("In staleElementException catch block of fnpFillAnswerToPropQuestionnaireDynamically_processHiddenAlso function. " );
							fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
							fnpFillAnswerToPropQuestionnaireDynamically_processHiddenAlso(QuestionNo, AnswerValue);
							fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
						} else {
							throw t;
						}
						
						
					}
				}
				
				
				
				
				

				//Thread.sleep(2000);//commented on 19th feb	
				Thread.sleep(500);

			}

			if (NoOfSets.length > 1 && j < (NoOfSets.length - 1)) {
				//Thread.sleep(1000);

				//WebElement saveNnextBtn = fnpGetORObjectX("Prop_Questionnaire_SaveNNextBtn");
				WebElement saveNnextBtn = driver.findElement(By.xpath(OR.getProperty("Prop_Questionnaire_SaveNNextBtn")));
				Actions action = new Actions(driver);
				action.moveToElement(saveNnextBtn).build().perform();
				if (!browserName.equalsIgnoreCase("chrome")) {
				saveNnextBtn.sendKeys(Keys.ARROW_UP);
				saveNnextBtn.sendKeys(Keys.ARROW_UP);
				saveNnextBtn.sendKeys(Keys.ARROW_UP);
				}
			//	fnpClick_OR("Prop_Questionnaire_SaveNNextBtn");
				//fnpClickInDialog_OR("Prop_Questionnaire_SaveNNextBtn");
				driver.findElement(By.xpath(OR.getProperty("Prop_Questionnaire_SaveNNextBtn"))).click();
				 Thread.sleep(8000);
			//	Thread.sleep(2000);
			}

		}
		
		

		if  (    (currentSuiteName.equalsIgnoreCase("ISR_suite"))   || (currentSuiteName.equalsIgnoreCase("SCFS_suite")) ) {             
			fnpClickInDialog_OR("Prop_QuestionnaireCloseBtnInISR");
		} else {
			fnpClickInDialog_OR("Prop_QuestionnaireCloseBtn");
		}
		
		//}
}
	
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//Not having MarkNUnmarkFunctioanlity
public static void fnpCommonProcessQuestionnairesSet_ISR_old() throws Throwable{
		
		
		fnpMymsg("");
		fnpMymsg(" Now going to insert the values in Questionnaire .");

		
		
		
		fnpMymsg("");
		fnpMymsg("");
		String classValue;
		do{

			//below is work both for tabview in ISR and without tabview in proposal
			String questionnaire_xpath=".//div[contains(@id,'mainForm:')][contains(@id,':IQMAINQUES_')][contains(@id,'_content')]/table/tbody/tr[@class='ui-widget-content']/td/table/tbody/tr/td/label";
			
			List<WebElement> questionnaireList = driver.findElements(By.xpath(questionnaire_xpath));
			int totalQuestionnaire=questionnaireList.size();
			String question;
			String questionNo;
			for (int i = 0; i < questionnaireList.size(); i++) {
				question=questionnaireList.get(i).getText();
				fnpMymsg("Question "+(i+1)+" is --"+question);
				questionNo=question.split(":")[0].trim();
				fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
				//fnpFillAnswerToPropQuestionnaireDynamically_processHiddenAlso(questionNo, "Yes"); //working fine for only radio button
				fnpFillAnswerToPropQuestionnaireDynamically_processHiddenAlso_new(questionNo); //I think it will work for all radio, first value in drop down and text box
						
				//fnpLoading_wait();
				fnpLoading_wait_CheckLoadingIconOnlyIniPulse();	
				
				questionnaireList = driver.findElements(By.xpath(questionnaire_xpath));
			}

				
				
			WebElement wb = fnpGetORObjectX_withoutCheckingElementClickable("Prop_Questionnaire_SaveNNextBtn");
			classValue = wb.getAttribute("class").trim();
			if (!(classValue.contains("ui-state-disabled"))) {
				
/*				
				
				WebElement saveNnextBtn = driver.findElement(By.xpath(OR.getProperty("Prop_Questionnaire_SaveNNextBtn")));
				Actions action = new Actions(driver);
				action.moveToElement(saveNnextBtn).build().perform();
				if (!browserName.equalsIgnoreCase("chrome")) {
					saveNnextBtn.sendKeys(Keys.ARROW_UP);
					saveNnextBtn.sendKeys(Keys.ARROW_UP);
					saveNnextBtn.sendKeys(Keys.ARROW_UP);
				}
				
				
				fnpMymsg("Going to click 'SaveNNext' button in questionnaires.");
				fnpLoading_wait_specialCase(10);
				fnpClick_OR("ISR_Prop_Questionnaire_Save_btn");
				fnpLoading_wait_specialCase(10);
				//saveNnextBtn.click();
				fnpClick_OR("Prop_Questionnaire_SaveNNextBtn");
				fnpMymsg("Clicked SaveNNext button.");
				fnpLoading_wait();
				fnpMymsg("Debug:After loadingwait function.");
				
				*/
				
				
/*				JavascriptExecutor executor = (JavascriptExecutor)driver;
				executor.executeScript("arguments[0].click();", fnpGetORObjectX("Prop_Questionnaire_SaveNNextBtn"));
				Thread.sleep(1000*60);
				fnpLoading_wait();
				*/
				
				
				
				
				WebElement saveNnextBtn = driver.findElement(By.xpath(OR.getProperty("Prop_Questionnaire_SaveNNextBtn")));
				Actions action = new Actions(driver);
				action.moveToElement(saveNnextBtn).build().perform();
				
				fnpClick_OR("Prop_Questionnaire_SaveNNextBtn");
				
			}	
			
		//} while(fnpCheckElementDisplayedImmediately("Prop_Questionnaire_SaveNNextBtn"));
		} while(!(classValue.contains("ui-state-disabled")));
			
		


		
		

		if  (    (currentSuiteName.equalsIgnoreCase("ISR_suite"))   || (currentSuiteName.equalsIgnoreCase("SCFS_suite"))
				||  (currentSuiteName.equalsIgnoreCase("SUS_suite"))) {   
/*			
			fnpLoading_wait_specialCase(10);
			fnpClick_OR("ISR_Prop_Questionnaire_Save_btn");
			fnpLoading_wait_specialCase(10);
			fnpClickInDialog_OR("Prop_QuestionnaireCloseBtnInISR");
			*/
			
			
/*			
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", fnpGetORObjectX("Prop_QuestionnaireCloseBtnInISR"));
			Thread.sleep(1000*60);
			fnpLoading_wait();
			
			*/
			
			fnpClickInDialog_OR("Prop_QuestionnaireCloseBtnInISR");
			
			fnpWaitTillThisElementIsNoLongerDisplayedTillMaxWait_OR("Prop_QuestionnaireCloseBtnInISR", "After clicking Save&Close button in questionnaire, it is visible after 300 seconds", 300);
		} else {
			fnpClickInDialog_OR("Prop_QuestionnaireCloseBtn");
		}
		
		//}

	
	
}








public static void fnpCommonProcessQuestionnairesSet_ISR() throws Throwable{
	
	
	fnpMymsg("");
	fnpMymsg(" Now going to insert the values in Questionnaire .");

	
	boolean unMarkSectionButtonDisplay_alreadyClicked = false;
	boolean unMarkSectionButtonFlag = false;
	
	boolean MarkSectionButtonDisplay_alreadyClicked = false;
	boolean MarkSectionButtonFlag= false;
	int counter=0;
	
	
	fnpMymsg("");
	fnpMymsg("");
	String classValue;
	do{
		counter++;
		fnpMymsg("******************* Questionnaire Section -"+counter+" ***********************************************************************************");
		
/*		if (fnpCheckElementDisplayedImmediately("Prop_Questionnaire_UnmarkSectionNA_Btn")) {
			fnpMymsg("Unmark Section N/A button is visible so going to click it first.");
			WebElement unmarkSectioinNABtn = driver.findElement(By.xpath(OR.getProperty("Prop_Questionnaire_UnmarkSectionNA_Btn")));
			Actions action = new Actions(driver);
			action.moveToElement(unmarkSectioinNABtn).build().perform();				
			fnpClick_OR("Prop_Questionnaire_UnmarkSectionNA_Btn");
		}
	*/
/*		
		if ( (fnpCheckElementDisplayedImmediately("Prop_Questionnaire_UnmarkSectionNA_Btn"))
				&& (unMarkSectionButtonDisplay=false)){
			unMarkSectionButtonDisplay=true;
			counter=counter+1;
		}else{
			if ( (fnpCheckElementDisplayedImmediately("Prop_Questionnaire_markSectionNA_Btn")) 
					&& (MarkSectionButtonDisplay=false)){{
				MarkSectionButtonDisplay=true;
				counter=counter+1;
			}
			
		}
		*/

		//it covers first UnMarkSectionNA button, it click it and answer the questions 
		fnpMymsg("Is Unmark Section N/A button  visible?---"+fnpCheckElementDisplayedImmediately("Prop_Questionnaire_UnmarkSectionNA_Btn"));
		fnpMymsg("Is Unmark Section N/A button  clicked already?---"+unMarkSectionButtonDisplay_alreadyClicked);
		fnpMymsg("-------------------------------------------------------------------------------");
		fnpMymsg("Is Mark Section N/A button  visible?---"+fnpCheckElementDisplayedImmediately("Prop_Questionnaire_markSectionNA_Btn"));
		fnpMymsg("Is Mark Section N/A button  clicked already?---"+MarkSectionButtonDisplay_alreadyClicked);
		
/*		
		if ( (unMarkSectionButtonDisplay_alreadyClicked==false) && (fnpCheckElementDisplayedImmediately("Prop_Questionnaire_UnmarkSectionNA_Btn"))) {
			fnpMymsg("Unmark Section N/A button is visible, so going to click it.");
			unMarkSectionButtonDisplay_alreadyClicked=true;
			fnpClick_OR("Prop_Questionnaire_UnmarkSectionNA_Btn");
			fnpMymsg("Going to process the questionnaires.");
			reusableCode_forAnsweringQuestionsInISR();
			
		}else{
			//it covers first MarkSectionsNA button, it click it and no need to answer the questions 
			if ((MarkSectionButtonDisplay_alreadyClicked==false) && (fnpCheckElementDisplayedImmediately("Prop_Questionnaire_markSectionNA_Btn"))) {
				fnpMymsg("Mark Section N/A button is visible, so going to click it.");
				MarkSectionButtonDisplay_alreadyClicked=true;
				fnpClick_OR("Prop_Questionnaire_markSectionNA_Btn");
				fnpMymsg("Skipping the processing of the questionnaires.");
				//reusableCode_forAnsweringQuestionsInISR(); not filling questions
				}else{
					//it covers 1> if UnmarkSectionNA button is visible again (2nd time) then click it and answers the questions.
					//			2> If MarkSectionNA button is visible again (2nd time) then nothing to do, just answers the questions.
					if (fnpCheckElementDisplayedImmediately("Prop_Questionnaire_UnmarkSectionNA_Btn")) {
						fnpClick_OR("Prop_Questionnaire_UnmarkSectionNA_Btn");
					}
					fnpMymsg("Going to process the questionnaires.");
					reusableCode_forAnsweringQuestionsInISR();
				}

		}
		
		*/
		
		
		
		//it covers using N/A sections (means without cliking it) , so not going to click it first tiem and no need to answer the questions , if 2nd time it is visibel then click it.
		if (  (fnpCheckElementDisplayedImmediately("Prop_Questionnaire_UnmarkSectionNA_Btn"))) {
			if ((unMarkSectionButtonFlag==false) ){
				fnpMymsg("Unmark Section N/A button is visible, so not using this first time, so not going to click it.");
				fnpMymsg("So, not going to process (means skipping) the questionnaires.");
				//reusableCode_forAnsweringQuestionsInISR();
				
				unMarkSectionButtonDisplay_alreadyClicked=true;
				unMarkSectionButtonFlag=true;
			}else{
				fnpMymsg("Unmark Section N/A button is visible, so using as it is  first time, so  going to click it.");
				fnpClick_OR("Prop_Questionnaire_UnmarkSectionNA_Btn");
				fnpMymsg("Going to process the questionnaires.");
				reusableCode_forAnsweringQuestionsInISR();
				
				unMarkSectionButtonDisplay_alreadyClicked=true;
				//unMarkSectionButtonFlag=false;
			}
					

			
		}else{
			//it covers first MarkSectionsNA button, it click it and no need to answer the questions 
			if ((MarkSectionButtonDisplay_alreadyClicked==false) && (fnpCheckElementDisplayedImmediately("Prop_Questionnaire_markSectionNA_Btn"))) {
				fnpMymsg("Mark Section N/A button is visible, so going to click it.");
				MarkSectionButtonDisplay_alreadyClicked=true;
				fnpClick_OR("Prop_Questionnaire_markSectionNA_Btn");
				fnpMymsg("Skipping the processing of the questionnaires.");
				//reusableCode_forAnsweringQuestionsInISR(); not filling questions
				}else{
					//it covers 1> if UnmarkSectionNA button is visible again (2nd time) then click it and answers the questions.
					//			2> If MarkSectionNA button is visible again (2nd time) then nothing to do, just answers the questions.
					if (fnpCheckElementDisplayedImmediately("Prop_Questionnaire_UnmarkSectionNA_Btn")) {
						fnpClick_OR("Prop_Questionnaire_UnmarkSectionNA_Btn");
					}
					fnpMymsg("Going to process the questionnaires.");
					reusableCode_forAnsweringQuestionsInISR();
				}

		}
		
		
		
		
		
		
		
/*		
		1. it covers first UnMarkSectionNA button, it click it and answer the questions 
		2. it covers first MarkSectionsNA button, it click it and no need to answer the questions 
		3. it covers a> if UnmarkSectionNA button is visible again (2nd time) then click it and answers the questions.
				b> If MarkSectionNA button is visible again (2nd time) then nothing to do, just answers the questions.
		*/
		
		
			
			
		WebElement wb = fnpGetORObjectX_withoutCheckingElementClickable("Prop_Questionnaire_SaveNNextBtn");
		classValue = wb.getAttribute("class").trim();
		if (!(classValue.contains("ui-state-disabled"))) {
			
/*				
			
			WebElement saveNnextBtn = driver.findElement(By.xpath(OR.getProperty("Prop_Questionnaire_SaveNNextBtn")));
			Actions action = new Actions(driver);
			action.moveToElement(saveNnextBtn).build().perform();
			if (!browserName.equalsIgnoreCase("chrome")) {
				saveNnextBtn.sendKeys(Keys.ARROW_UP);
				saveNnextBtn.sendKeys(Keys.ARROW_UP);
				saveNnextBtn.sendKeys(Keys.ARROW_UP);
			}
			
			
			fnpMymsg("Going to click 'SaveNNext' button in questionnaires.");
			fnpLoading_wait_specialCase(10);
			fnpClick_OR("ISR_Prop_Questionnaire_Save_btn");
			fnpLoading_wait_specialCase(10);
			//saveNnextBtn.click();
			fnpClick_OR("Prop_Questionnaire_SaveNNextBtn");
			fnpMymsg("Clicked SaveNNext button.");
			fnpLoading_wait();
			fnpMymsg("Debug:After loadingwait function.");
			
			*/
			
			
/*				JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", fnpGetORObjectX("Prop_Questionnaire_SaveNNextBtn"));
			Thread.sleep(1000*60);
			fnpLoading_wait();
			*/
			
			
			
			
			WebElement saveNnextBtn = driver.findElement(By.xpath(OR.getProperty("Prop_Questionnaire_SaveNNextBtn")));
			Actions action = new Actions(driver);
			action.moveToElement(saveNnextBtn).build().perform();
			
			fnpClick_OR("Prop_Questionnaire_SaveNNextBtn");
			
		}	
		
	//} while(fnpCheckElementDisplayedImmediately("Prop_Questionnaire_SaveNNextBtn"));
	} while(!(classValue.contains("ui-state-disabled")));
		
	


	
	

	if  (    (currentSuiteName.equalsIgnoreCase("ISR_suite"))   || currentSuiteName.equalsIgnoreCase("SCFS_suite") || currentSuiteName.equalsIgnoreCase("FPC_Work_Order_suite")
			||  (currentSuiteName.equalsIgnoreCase("SUS_suite"))) {   
/*			
		fnpLoading_wait_specialCase(10);
		fnpClick_OR("ISR_Prop_Questionnaire_Save_btn");
		fnpLoading_wait_specialCase(10);
		fnpClickInDialog_OR("Prop_QuestionnaireCloseBtnInISR");
		*/
		
		
/*			
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", fnpGetORObjectX("Prop_QuestionnaireCloseBtnInISR"));
		Thread.sleep(1000*60);
		fnpLoading_wait();
		
		*/
		
		fnpClickInDialog_OR("Prop_QuestionnaireCloseBtnInISR");
		
		fnpWaitTillThisElementIsNoLongerDisplayedTillMaxWait_OR("Prop_QuestionnaireCloseBtnInISR", "After clicking Save&Close button in questionnaire, it is visible after 300 seconds", 300);
	} else {
		fnpClickInDialog_OR("Prop_QuestionnaireCloseBtn");
	}
	
	//}



}


public static void reusableCode_forAnsweringQuestionsInISR() throws Throwable{
	//below is work both for tabview in ISR and without tabview in proposal
	String questionnaire_xpath=".//div[contains(@id,'mainForm:')][contains(@id,':IQMAINQUES_')][contains(@id,'_content')]/table/tbody/tr[@class='ui-widget-content']/td/table/tbody/tr/td/label";
	
	List<WebElement> questionnaireList = driver.findElements(By.xpath(questionnaire_xpath));
	int totalQuestionnaire=questionnaireList.size();
	String question;
	String questionNo;
	for (int i = 0; i < questionnaireList.size(); i++) {
		question=questionnaireList.get(i).getText();
		fnpMymsg("Question "+(i+1)+" is --"+question);
		questionNo=question.split(":")[0].trim();
		fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
		//fnpFillAnswerToPropQuestionnaireDynamically_processHiddenAlso(questionNo, "Yes"); //working fine for only radio button
		fnpFillAnswerToPropQuestionnaireDynamically_processHiddenAlso_new(questionNo); //I think it will work for all radio, first value in drop down and text box
				
		//fnpLoading_wait();
		fnpLoading_wait_CheckLoadingIconOnlyIniPulse();	
		
		questionnaireList = driver.findElements(By.xpath(questionnaire_xpath));
	}

}

}
