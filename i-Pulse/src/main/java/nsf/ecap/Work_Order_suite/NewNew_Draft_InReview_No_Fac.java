package nsf.ecap.Work_Order_suite;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import nsf.ecap.util.ErrorUtil;
import nsf.ecap.util.TestUtil;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;
import static nsf.ecap.config.IPULSE_CONSTANTS.*;
//BS-01
public class NewNew_Draft_InReview_No_Fac extends TestSuiteBase {

	// Runmode of test case in a suite
	@BeforeTest
	@Parameters({ "className" })
	public void checkTestSkip(String className) throws Throwable {

		count = -1;
		try {

			if (className.isEmpty()) {
				className = this.getClass().getSimpleName();

			}
			//classNameText = className;
			setClassNameText( className);
			currentScriptCode = fnpMappingClassNameWithScenarioCode(classNameText).trim();

			fnpMymsg("             ");
			fnpMymsg("             ");
			fnpMymsg("             ");
			fnpMymsg("             ");
			fnpMymsg("             ");
			fnpMymsg("=========================================================================================================================================");
			fnpMymsg("Checking Runmode of testcase '" + className + "' .");

			if (!TestUtil.isTestCaseRunnable(currentSuiteXls, className)) {

				fnpMymsg("Skipping Test Case" + className + " as runmode set to NO");// logs
				fnpMymsg("=========================================================================================================================================");
				throw new SkipException("Skipping Test Case" + className + " as runmode set to NO");// reports
			}

			fnpMymsg("Going to execute the script for  '" + className + "'  as runmode set to Yes");// logs

			
			fnpLoadHashData(hashXlData, currentSuiteXls, className, 2);
			
			TestSuiteBase.fail = false;
			TestSuiteBase.isTestPass = true;

		}

		catch (SkipException sk) {
			skip = true;
			String stackTrace = Throwables.getStackTraceAsString(sk);
			String errorMsg = sk.getMessage();
			Exception c = new Exception(stackTrace);
			ErrorUtil.addVerificationFailure(c);
			throw new SkipException(errorMsg + " \n\nSo,Skipping Test Case" + className);// reports
		} catch (Throwable t) {
			fnpCommonFinalCatchBlock(t, "  checkTestSkip method  is fail . ", "checkTestSkipFail");

		}

	}

	@Test(priority = 1)
	public void CreateWOFlow() throws Throwable {

		fnpMymsg(" **************************************************************");
		fnpMymsg(" Executing CreateWOFlow");

		try {
			// start = new Date();

/*			
			if (   (!(currentSuiteName.equalsIgnoreCase(Dietary_Supplement_suite_Name))) & (!(currentSuiteName.equalsIgnoreCase(Non_Food_Compounds_suite_Name))) ){
				fnpChangeStatusOfDropFacilityToApply((String) hashXlData.get("FacCode"),(String) hashXlData.get("StandardCode"));
			}
			*/
			
/*			if (  (!(currentSuiteName.equalsIgnoreCase(Non_Food_Compounds_suite_Name)))
					| (!(currentSuiteName.equalsIgnoreCase(Wales_Work_Order_suite_Name)))){
				fnpChangeStatusOfDropFacilityToApply((String) hashXlData.get("FacCode"),(String) hashXlData.get("StandardCode"));
			}
			
			*/
			
			fnpCommonLoginPart(classNameText);
			

			// -------First Create Work Order Page ----------------------------


			fnpClick_OR("ClientLKPBtn_id");
			fnpSearchNSelectFirstRadioBtn(1, (String) hashXlData.get("Client"), 1);


			String EnteredClient = fnpWaitTillTextBoxDontHaveValue("ClientTxtBox_id", "value");
			Assert.assertTrue(EnteredClient.contains((String) hashXlData.get("Client")), "Client Value is not selected properly from lookup");
			fnpMymsg(" Client value is properly selected from client lookup");

			Thread.sleep(100);
			
			if ((currentSuiteName.equalsIgnoreCase(Dietary_Supplement_suite_Name)) ){
				fnpPFList("SolutionTypeList", "SolutionTypeListOptions", (String) hashXlData.get("SolutionType"));
			}
			
			fnpPFList("WOTypeList", "WOTypeListOptions", (String) hashXlData.get("WOType"));
			
			fnpClick_OR("NextBtn_id");

			fnpCheckErrMsg("Error thrown by applciation After Click Next Button  ");
			// -------Second Create Work Order Page ----------------------------

			fnpMymsg("Just before going to click AccountManagerLkpBtn");

			fnpWaitForVisible("AccountManagerTxtBox_id");
			String AlreadyAccountManager = fnpGetORObjectX("AccountManagerTxtBox_id").getAttribute("value");

			//if (AlreadyAccountManager.contains((String) hashXlData.get("AccountManager").trim())) {
			String accountManager=null;
			String accountManager_code=null;
			
			if (!(currentSuiteName.equalsIgnoreCase(Non_Food_Compounds_suite_Name)) ){
			
					if (  (loginAsFullName==null) ||loginAsFullName.equalsIgnoreCase("") ) {
						accountManager=(String) hashXlData.get("AccountManager").trim();
						accountManager_code=(String) hashXlData.get("AccountManager_Code").trim();
						
					} else {
						accountManager=loginAsFullName;
						accountManager_code=loginUser_code;
					}
					
					
					
					//if (AlreadyAccountManager.contains(accountManager)) {
					if (    (AlreadyAccountManager.contains(accountManager)) ||(AlreadyAccountManager.contains(accountManager_code)) ){
						// nothing to do now
		
					} else {
						fnpClick_OR("AccountManagerLkpBtn_id");
		
						fnpMymsg("Just after  click AccountManagerLkpBtn");
						fnpMymsg("Just before going to insert value of Account Manger");
						//fnpSearchNSelectFirstRadioBtn(2, accountManager, 1);
						//fnpSearchNSelectFirstRadioBtn(2, "Contains",accountManager, 1);
						
						
						if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
							fnpSearchNSelectFirstRadioBtn(1, accountManager_code, 1);
						}else{
							fnpSearchNSelectFirstRadioBtn(2, accountManager, 1);
						}
						
						fnpMymsg("Just after  insert value of Account Manger");
						String EnteredAccountManager = fnpGetORObjectX("AccountManagerTxtBox_id").getAttribute("value");
						EnteredAccountManager = fnpWaitTillTextBoxDontHaveValue("AccountManagerTxtBox_id", "value");
						//Assert.assertTrue(EnteredAccountManager.contains(accountManager), "Account Manager is not selected properly from lookup");
						//Assert.assertTrue(EnteredAccountManager.contains(accountManager_code), "Account Manager is not selected properly from lookup");
						if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
							//Assert.assertTrue(EnteredAccountManager.contains(accountManager_code), "Account Manager is not selected properly from lookup");
							Assert.assertTrue(EnteredAccountManager.contains(accountManager_code), "Account Manager is not selected properly from lookup because actual value is ---'"+EnteredAccountManager+"' and expected is --'"+accountManager_code+"'.");
						}else{
							Assert.assertTrue(EnteredAccountManager.contains(accountManager), "Account Manager is not selected properly from lookup because actual value is ---'"+EnteredAccountManager+"' and expected is --'"+accountManager+"'.");
						}
						
						
						
						fnpMymsg(" Account Manager is properly selected from client lookup");
					}
			
			}else{
				String expectedDefaultAccountManager=(String) hashXlData.get("AccountManager").trim();
				String expectedDefaultAccountManager_code=(String) hashXlData.get("AccountManager_Code").trim();
				if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
					Assert.assertTrue(AlreadyAccountManager.contains(expectedDefaultAccountManager_code), "Expected default account manager is not present. Actual is this '"+AlreadyAccountManager+"' but expected is this  '"+expectedDefaultAccountManager_code+"'.");
				}else{
					Assert.assertTrue(AlreadyAccountManager.contains(expectedDefaultAccountManager), "Expected default account manager is not present. Actual is this '"+AlreadyAccountManager+"' but expected is this  '"+expectedDefaultAccountManager+"'.");
				}
			}
			
			
			
			

			//if (!(currentSuiteName.equalsIgnoreCase(Dietary_Supplement_suite_Name)) ){
			if (   (!(currentSuiteName.equalsIgnoreCase(Dietary_Supplement_suite_Name))) & (!(currentSuiteName.equalsIgnoreCase(Non_Food_Compounds_suite_Name))) & (!(currentSuiteName.equalsIgnoreCase(FPC_Work_Order_suite_Name))) ){
				fnpClick_OR("BDMLkpBtn_id");
				//fnpSearchNSelectFirstRadioBtn(2, (String) hashXlData.get("BDM"), 1);
				//fnpSearchNSelectFirstRadioBtn(1, (String) hashXlData.get("BDM_Code"), 1);
				
				if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
					fnpSearchNSelectFirstRadioBtn(1, (String) hashXlData.get("BDM_Code"), 1);
				}else{
					fnpSearchNSelectFirstRadioBtn(2, (String) hashXlData.get("BDM"), 1);
				}
				
				String EnteredBDM = fnpGetORObjectX("BDMTxtBox_id").getAttribute("value");
				EnteredBDM = fnpWaitTillTextBoxDontHaveValue("BDMTxtBox_id", "value");
				//Assert.assertTrue(EnteredBDM.contains((String) hashXlData.get("BDM")), "BDM is not selected properly from lookup");
				//Assert.assertTrue(EnteredBDM.contains((String) hashXlData.get("BDM_Code")), "BDM is not selected properly from lookup");
				if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
					Assert.assertTrue(EnteredBDM.contains((String) hashXlData.get("BDM_Code")), "BDM is not selected properly from lookup  because actual value is ---'"+EnteredBDM+"' and expected is --'"+(String) hashXlData.get("BDM_Code")+"'.");
				}else{
					Assert.assertTrue(EnteredBDM.contains((String) hashXlData.get("BDM")), "BDM is not selected properly from lookup  because actual value is ---'"+EnteredBDM+"' and expected is --'"+(String) hashXlData.get("BDM")+"'.");
				}
				fnpMymsg(" BDM is properly selected from client lookup");
			}
			
/*			if (!(currentSuiteName.equalsIgnoreCase(Dietary_Supplement_suite_Name)) ){
				fnpPFList("WoPrimContactList", "WoPrimContactListOptions", (String) hashXlData.get("WOPrimaryContact"));
			}else{
				fnpPFListByLiNo("WoPrimContactList", "WoPrimContactListOptions", 1);
			}
*/
			fnpPFListByLiNo("WoPrimContactList", "WoPrimContactListOptions", 1);
			

			fnpGetORObjectX("WOSummaryTxtBox_id").sendKeys((String) hashXlData.get("WOSummary") + fnTimestampDateWithTime());


			fnpType("OR", "WOScopeTxtBox_id", (String) hashXlData.get("WOScope"));
			
			if ((currentSuiteName.equalsIgnoreCase(Non_Food_Compounds_suite_Name)) ){
				fnpPFList("WO_SubTypePFList", "WO_SubTypePFListOptions", (String) hashXlData.get("WO_Sub_Type"));
			}


			if (!(classNameText.equalsIgnoreCase("WO_Annual"))) {
				if (!(currentSuiteName.equalsIgnoreCase(Non_Food_Compounds_suite_Name)) ){
					fnpType("OR", "CreateWOProductType", (String) hashXlData.get("ProductType"));
					fnpWaitForVisible("CreateWO_ProductTypeOptions");
					String xpathProductTypeValue = OR.getProperty("CreateWO_ProductTypeOptionsList") + "[@data-item-label='" + (String) hashXlData.get("ProductType").trim() + "']";
					fnpWaitForVisible_NotInOR(xpathProductTypeValue);
					fnpMouseHover_NotInOR(xpathProductTypeValue);
					driver.findElement(By.xpath(xpathProductTypeValue)).click();
					fnpMymsg("Product type has been inserted is " + fnpGetAttribute_OR("CreateWOProductType", "value"));
				}else{
					fnpPFList("NFC_ProductTypePFList", "NFC_ProductTypePFListOptions", (String) hashXlData.get("ProductType"));
					fnpPFList("NFC_ProductTypePFList", "NFC_ProductTypePFListOptions", (String) hashXlData.get("Second_ProductType"));
				}

			}
			
			
			fnpLoading_wait();

			
			/**********Commented on Karen Request to pick first one only, subject of mail is--requirements too specific ***/
/*			
			fnpPFList("InvoiceBillToPFList", "InvoiceBillToPFListOptions", (String) hashXlData.get("FinancialTab_BillToInvoice"));
			String insertedBillToInvice = fnpGetText_OR("InvoiceBillToPFList");
			Assert.assertEquals(insertedBillToInvice, (String) hashXlData.get("FinancialTab_BillToInvoice"), "Bill To Invice value has not been inserted properly ");

		*/	
			

			//fnpPFListByLiNo("InvoiceBillToPFList", "InvoiceBillToPFListOptions", 1);
			
			
			if ((currentSuiteName.equalsIgnoreCase(Non_Food_Compounds_suite_Name)) ){
			fnpPFListContains_ifMatchingMultipleThenSelectFirstOne("InvoiceBillToPFList",  (String) hashXlData.get("Invoice_Bill_To_contains")) ;
			}else{
				//fnpPFListByLiNo("InvoiceBillToPFList", "InvoiceBillToPFListOptions", 1);

				//commenting below on 09-09-2019 as some validation error related to currency is started coming while adding facility
				//fnpPFListByLiNo("InvoiceBillToPFList", "InvoiceBillToPFListOptions", 2);			
				fnpPFListContains_ifMatchingMultipleThenSelectFirstOne("InvoiceBillToPFList",  "NSFUSA") ;
			}
			
			
			//for custom here , added for new sprint of 2017 i.e. 2.1
			//if  (    (classNameText.equalsIgnoreCase("Modbrack_Not_Certified")) | (classNameText.equalsIgnoreCase("WO_Custom"))     )   {
			if  (    (classNameText.equalsIgnoreCase("Modbrack_Not_Certified"))
					|| (classNameText.contains("Custom")) 
					|| (classNameText.contains("ModBrack_Certified")) )   {
				String ListingFRSUpdateRadioBtnValue = (String) hashXlData.get("Listing_FRS_Update");

				String ListingFRSUpdateRadioBtnXpath = OR.getProperty("ListingFRSUpdateValuePart1") + "'" + ListingFRSUpdateRadioBtnValue + "']/preceding-sibling::input";
				fnpGetORObjectX___NOR(ListingFRSUpdateRadioBtnXpath).click();
				//driver.findElement(By.xpath(ListingFRSUpdateRadioBtnXpath)).click();
				// Thread.sleep(500);

			}
			
			
			if ((currentSuiteName.equalsIgnoreCase(Non_Food_Compounds_suite_Name)) || (currentSuiteName.equalsIgnoreCase(FPC_Work_Order_suite_Name)) ){
					fnpGetORObjectX("NFC_CAR_Resolution_RadioBtn_No").click();
			 }
			
			if (!(currentSuiteName.equalsIgnoreCase(Non_Food_Compounds_suite_Name)) ){
				fnpPFList("RevenueProgramList", "RevenueProgramListOptions", (String) hashXlData.get("RevenueProgram"));
		 }

			

			fnpClick_OR("CreateWOBtn_id");

			fnpCheckError(" while creating new WO");

			fnpWaitForVisibleHavingMultipleElements("TopMessage3_classname");


			fnpMymsg("Top Message after create ----" + fnpGetText_OR("TopMessage3_classname"));

			String SuccessfulMsg = fnpGetText_OR("TopMessage3_classname");

			Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"), "Top message does not contain 'success' word, so might be work order has not been created successfully");

			fnpMymsg("Work Order created successfully as success message is getting flashed.");
			String workOrderNo_text = fnpGetText_OR("NewlyCreatedWorkOrderNo");
			
			
			//workOrderNo = ((workOrderNo_text.split(" "))[0]).trim();
			
			setWONo(((workOrderNo_text.split(" "))[0]).trim());
			
			fnpMymsg("Newly created WO no. is:" + workOrderNo);

			fnpWaitTillClickable("NewlyCrWOTopBannerInfo");
			fnpGetORObjectX("NewlyCrWOTopBannerInfo").click();
			fnpWaitForVisible("TopBannerWOStatus");
			String NewlyWOStatus = fnpGetText_OR("TopBannerWOStatus");
			Assert.assertEquals(NewlyWOStatus, "DRAFT", "Newly created WO status is not 'DRAFT'.The WO should get created in DRAFT status. ");
			fnpMymsg("Newly created WO status is 'DRAFT'");

			isTestPass = true;
			fnpMymsg(" **************************************************************");

		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "  Create Work Order flow  is fail . ", "CreateWorkOrderFail");

		}

	}

	@Test(priority = 2, dependsOnMethods = { "CreateWOFlow" })
	public void Check_alert_DRAFT_Status_N_AssignedWithTaskSetupIncomplete() throws Throwable {
		fnpMymsg(" **************************************************************");
		try {

			fnpCommonGoToHomeNClick();

			// -------Alert Task ready to be assigned -------------------

			fnpCommonAlertGeneratedVerification("Draft_Status", "DraftStatusAlertTable_header", "WO #", "DraftStatusAlertTable", "DraftStausAlert_WO_filterTxtBox", workOrderNo);
			// ---------------------------------------------

			// -------Alert 'Draft Work order(s) assigned with task setup
			// incomplete -------------------
			fnpCommonAlertGeneratedVerification("Task_Setup_Incomplete", "TaskSetupIncompleteAlertTable_header", "WO #", "TaskSetupIncompleteAlertTable",
					"TaskSetupIncompleteAlert_WO_filterTxtBox", workOrderNo);
			// ---------------------------------------------

			fnpMymsg(" **************************************************************");

		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "  check_alert_DRAFT_Status_N_AssignedWithTaskSetupIncomplete is fail . ", "check_alert_DRAFT_Status_N_AssignedWithTaskSetupIncomplete");

		}

	}

	@Test(priority = 3, dependsOnMethods = { "Check_alert_DRAFT_Status_N_AssignedWithTaskSetupIncomplete" })
	public void AddingData__Facility_Tab() throws Throwable {
		fnpMymsg(" **************************************************************");
		fnpMymsg(" Executing AddingData__Facility_Tab");
		try {

			fnpSearchWorkOrderAndClickEditAlso(workOrderNo);

/*			fnpWaitTillClickable("EditFacilityTabLink");
			fnpGetORObjectX("EditFacilityTabLink").click();
			fnpLoading_wait();
			*/
			fnpClick_OR("EditFacilityTabLink");
			
			fnpMymsg("Facility tab has been clicked");
			fnpWaitForVisible("AssociateNewFacilityStandardBtn");
			fnpWaitTillVisiblityOfElementNClickable_OR("AssociateNewFacilityStandardBtn");

/*			
			if ( (!(currentSuiteName.equalsIgnoreCase(Non_Food_Compounds_suite_Name)))					
					){
				fnpClick_OR("AssociateNewFacilityStandardBtn");
				fnpMymsg("AssociateNewFacilityStandardBtn button has been clicked");
				fnpWaitForVisible("FacilityCodeLkpBtn");
				fnpGetORObjectX("FacilityCodeLkpBtn").click();
				fnpSearchNSelectFirstRadioBtn(1, (String) hashXlData.get("FacCode"), 2);
				fnpWaitForVisible("SearchAndAddBtn");
				fnpWaitTillClickable("SearchAndAddBtn");
				fnpClickInDialog_OR("SearchAndAddBtn");
				fnpSearchNSelectFirstCheckBox(1, (String) hashXlData.get("StandardCode"), 2);
				Thread.sleep(2000);
				fnpClickInDialog_OR("SelectAndReturnBtn");
				fnpWaitForVisible("SaveNCloseBtn");
				fnpWaitTillVisiblityOfElementNClickable_OR("SaveNCloseBtn");
				fnpClickInDialog_OR("SaveNCloseBtn");
				fnpLoading_wait();

				driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);
				 //if(fnpCheckElementDisplayed("FacilityTab_ConfirmationDialogBox",20)) {
				 if(fnpCheckElementDisplayed2("FacilityTab_ConfirmationDialogBox",30)) {	  
					 fnpGetORObjectX("FacilityTab_ConfirmationBox_YesBtn").click(); //
					 fnpLoading_wait();

					 // fnpLoading_wait(); 
					  
					  fnpLoading_waitInsideDialogBox();
					}
				 
			}else{
				fnpClick_OR("IncludeExistingFacilityStandardBtn");
				fnpMymsg("IncludeExistingFacilityStandardBtn button has been clicked");
				fnpSearchNSelectAllCheckBox(1, (String) hashXlData.get("FacCode"), 2);
				Thread.sleep(2000);
				fnpClickInDialog_OR("SelectAndReturnBtn");

			}

			*/
			
			
			
			switch (currentSuiteName) {
			case Non_Food_Compounds_suite_Name:
				fnpClick_OR("IncludeExistingFacilityStandardBtn");
				fnpMymsg("IncludeExistingFacilityStandardBtn button has been clicked");
				fnpSearchNSelectAllCheckBox(1, (String) hashXlData.get("FacCode"), 2);
				//Thread.sleep(2000);
				fnpClickInDialog_OR("SelectAndReturnBtn");
				break;	
				
			case FPC_Work_Order_suite_Name:
				fnpClick_OR("IncludeExistingFacilityStandardBtn");
				fnpMymsg("IncludeExistingFacilityStandardBtn button has been clicked");				
				
				fnpSearchNSelectAllCheckBox(3, (String) hashXlData.get("StandardCode"), 2);
				fnpClickInDialog_OR("SelectAndReturnBtn");
				break;	

			case Wales_Work_Order_suite_Name:
				fnpClick_OR("IncludeExistingFacilityStandardBtn");
				fnpMymsg("IncludeExistingFacilityStandardBtn button has been clicked");
				
				String completeSearchCriteria=(String) hashXlData.get("FacCode_completeString");
				fnpSearchNSelectFirstCheckBox( completeSearchCriteria);
				fnpClickInDialog_OR("SelectAndReturnBtn");
				break;
				
			default:
				fnpClick_OR("AssociateNewFacilityStandardBtn");
				fnpMymsg("AssociateNewFacilityStandardBtn button has been clicked");
				fnpWaitForVisible("FacilityCodeLkpBtn");
				fnpGetORObjectX("FacilityCodeLkpBtn").click();
				fnpSearchNSelectFirstRadioBtn(1, (String) hashXlData.get("FacCode"), 2);
				fnpWaitForVisible("SearchAndAddBtn");
				fnpWaitTillClickable("SearchAndAddBtn");
				fnpClickInDialog_OR("SearchAndAddBtn");
				fnpSearchNSelectFirstCheckBox(1, (String) hashXlData.get("StandardCode"), 2);
				//Thread.sleep(2000);
				fnpClickInDialog_OR("SelectAndReturnBtn");
				fnpWaitForVisible("SaveNCloseBtn");
				fnpWaitTillVisiblityOfElementNClickable_OR("SaveNCloseBtn");
				fnpClickInDialog_OR("SaveNCloseBtn");
				fnpLoading_wait();

				driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);
				 //if(fnpCheckElementDisplayed("FacilityTab_ConfirmationDialogBox",20)) {
				 if(fnpCheckElementDisplayed2("FacilityTab_ConfirmationDialogBox",30)) {	  
					 fnpGetORObjectX("FacilityTab_ConfirmationBox_YesBtn").click(); //
					 fnpLoading_wait();

					 // fnpLoading_wait(); 
					  
					  fnpLoading_waitInsideDialogBox();
					}
			}
		
			
			
/*			
			
			//commenting above lines as when loading icon is coming very late till then it bypass all loading 
			//statemetns and reached to this statment "Assert.assertTrue(FirstCelldata.contains("FRS") and result in fail the script
			//thats why I try to modify these lines as below
			fnpClick_OR_WithoutWait("SaveNCloseBtn");
			//fnpWaitForVisible("LoadingImg",60); //this statement is not appropriate here as before checking waitforvisiblity, it goes in ipulse loading loop first
			
			driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
			if(fnpCheckElementDisplayed("LoadingImg",60)) {
				fnpMymsg("LoadingImg is present.");
				 
			 }else{
				 fnpMymsg("LoadingImg is NOT present even after 60 seconds.");
			 }
			
			 fnpLoading_wait();
			 
			 */
			 
			 
			 
			 




			fnpMymsg("Going to check any error not thrown");

			fnpCheckError(" while adding data in Facility Tab");

			String FirstCelldata = "nothing";
			int wait = 0;
			while (true) {
				wait++;
				Thread.sleep(1000);
				try {
					fnpWaitForVisible("FirstRecordInFacilityTable");
					FirstCelldata = fnpGetText_OR("FirstRecordInFacilityTable");
				} catch (Exception e) {
					// nothing to do
				}
				if (FirstCelldata.contains("FRS")) {
					break;
				}

				if (wait > 5) {
					break;
				}
				Thread.sleep(3000);
			}
			Assert.assertTrue(FirstCelldata.contains("FRS"), "Facility has not been added as first cell does not contain 'FRS' word");
			
			
			fnpCheckFacilityDropStatusAndThenRunSQLQueries_atFacilitiesTab();
			
			
			fnpMymsg(" Adding data in New Wo Facility tab is Pass");
			isTestPass = true;
			fnpMymsg(" **************************************************************");

		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "  Adding data in New Wo Facility tab is fail . ", "AddingDataFaciltyTabFail");

		}
	}

	@Test(priority = 4, dependsOnMethods = { "AddingData__Facility_Tab" })
	public void AddingData__Tasks_Tab() throws Throwable {

		fnpMymsg(" **************************************************************");
		fnpMymsg(" Executing AddingData__Tasks_Tab");
		try {

			//fnpWaitForVisible("EditTaskTabLink");
			//fnpWaitTillClickable("EditTaskTabLink");
			fnpGetORObjectX("EditTaskTabLink").click();
			fnpLoading_wait();

			//fnpWaitForVisible("ApplyTaskTemplateBtn");
			fnpClick_OR("ApplyTaskTemplateBtn");

/*			String[] TaskTypeArray;
			String TaskTypeString = (String) hashXlData.get("TaskTypeName");
			if (!TaskTypeString.isEmpty()) {
				TaskTypeArray = ((String) hashXlData.get("TaskTypeName")).split(",");

				for (int i = 0; i < TaskTypeArray.length; i++) {
					String val = TaskTypeArray[i];
					String TaskTypeCheckboxXpath = "//td[contains(text(),'" + val + "')]/preceding-sibling::td/div/div[contains(@class,'ui-chkbox-box')]";
					driver.findElement(By.xpath(TaskTypeCheckboxXpath)).click(); //
					Thread.sleep(1000);

				}

			}
			*/
			
			
			
			if ( (currentSuiteName.equalsIgnoreCase(Wales_Work_Order_suite_Name)) || (currentSuiteName.equalsIgnoreCase(FPC_Work_Order_suite_Name)) ){
				try{
				fnpWaitForVisible("ContinueBtnInTaskTab");
				}catch(Throwable t){
					msg="Either template dialog box is not opened after clicking 'Apply Task Template' button or its xpath has been changed.";
					fnpMymsg(msg);
					throw new Exception(msg);
				}
			
				String expectedTemplateName=(String) hashXlData.get("Template_Name").trim();
				String actualTemplateName=fnpGetText_OR("Tasks_Tab_SelectTaskforWorkOrderTemplate_templateName").trim();
				if ((expectedTemplateName.equalsIgnoreCase(actualTemplateName))) {
					fnpMymsg("Actual template is matched successfully with expected Template i.e. --'"+actualTemplateName+"'.");
				}else{
					msg="Actual template '"+actualTemplateName+"' is NOT matched  with expected Template '"+expectedTemplateName+"' and hence failed.";
					fnpMymsg(msg);
					throw new Exception(msg);
				}
				
				String taskNeedToBeSelect=(String) hashXlData.get("TaskTypeName").trim();				
				fnpVerifyTasksAreAlreadySelected_InWO((String) hashXlData.get("TaskTypeName_shouldHaveSelected"),taskNeedToBeSelect);	
				
				if (!(taskNeedToBeSelect.equalsIgnoreCase(""))) {
					fnpCommonSelectingTasks_InWO((String) hashXlData.get("TaskTypeName"));
				}
				
				
			} else {
				fnpCommonSelectingTasks_InWO((String) hashXlData.get("TaskTypeName"));
			}
			
			
/*			String[] TaskTypeUnCheckArray;
			String TaskTypeUnCheckString = (String) hashXlData.get("UnCheckTaskTypeName");
			if (!TaskTypeUnCheckString.isEmpty()) {
				TaskTypeUnCheckArray = ((String) hashXlData.get("UnCheckTaskTypeName")).split(",");

				for (int i = 0; i < TaskTypeUnCheckArray.length; i++) {
					Thread.sleep(1000);
					String val = TaskTypeUnCheckArray[i];
					String TaskTypeCheckboxXpath = "//td[contains(text(),'" + val + "')]/preceding-sibling::td/div/div[contains(@class,'ui-chkbox-box')]";
					WebElement wb=driver.findElement(By.xpath(TaskTypeCheckboxXpath));
					String spanClass=wb.findElement(By.xpath(".//span")).getAttribute("class");
					if (spanClass.contains("check ui-c")) {
						fnpMymsg("This checkbox against '"+val+"' is checked, so we are going to uncheck it.");
						//driver.findElement(By.xpath(TaskTypeCheckboxXpath)).click(); //	
						//wb.click();
						wb.findElement(By.xpath(".//span")).click();
						Thread.sleep(1000);
					}else{
						if (spanClass.contains("blank ui-c")) {
							msg="This checkbox is already unchecked (blank) and according to excel it should be checked.";
							fnpMymsg(msg);
							throw new Exception(msg);
							
						} else {
							msg="Something went wrong, need to handle this else condition again.";
							fnpMymsg(msg);
							throw new Exception(msg);
						}
					}
					
					
				

				}
				

			}
			*/
			

			//fnpWaitForVisible("ContinueBtnInTaskTab");
			//fnpGetORObjectX("ContinueBtnInTaskTab").click();

			fnpWaitTillVisiblityOfElementNClickable_OR("Task_ShowAllLink");
			fnpGetORObjectX("Task_ShowAllLink").click();

			String[] BillingCodeArray = ((String) hashXlData.get("BillingCode")).split(",");
			List<WebElement> billingTxt = driver.findElements(By.xpath(OR.getProperty("Task_BillingCode_txtBoxes")));
			int jBillingCounter = 0;

			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			int catchCounter = 0;
			while (billingTxt.size() != BillingCodeArray.length) {
				Thread.sleep(1000);
				jBillingCounter++;

				try {
					billingTxt = driver.findElements(By.xpath(OR.getProperty("Task_BillingCode_txtBoxes")));
				} catch (Throwable t) {
					if (catchCounter > 5) {
						throw new Exception(t.getMessage());
					} else {
						catchCounter++;
					}
				}

				/*
				 * if (jBillingCounter > 120) { // if wait is more than 120
				 * seconds // or 2 min. then throw // exception
				 */
				if (jBillingCounter > 30) { // if wait is more than 120 seconds
												// // or 2 min. then throw//
												// exception
					throw new Exception("Either Billing codes table not expended properly or  Billing code text boxes are not present even after waiting for 2 min. Billing codes count are [" + BillingCodeArray.length
							+ "] and  billingCode opened text boxes till now count are [" + billingTxt.size() + "] .");

				}

				WebElement wbElement = driver.findElement(By.xpath(OR.getProperty("FooterElement")));
				new Actions(driver).moveToElement(wbElement).build().perform();

			}
			
			driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);

			int j1 = 0;
			for (Iterator iterator = billingTxt.iterator(); iterator.hasNext();) {

				WebElement webElement = (WebElement) iterator.next();
				webElement.sendKeys("");
				webElement.sendKeys(BillingCodeArray[j1]);
				j1++;

			}

			if (((String) hashXlData.get("TaskTypeName")).contains("FACAUD")) {
				if ( (currentSuiteName.equalsIgnoreCase(FPC_Work_Order_suite_Name)) ){
					fnpType("OR", "DeadLineDate", fns_Return_RequriedDateTime("dd-MMM-yyyy", 0,0,1,0,0));
				}else{
					fnpType("OR", "DeadLineDate", (String) hashXlData.get("DeadLineDate"));
				}

				String StandardCheckboxXpath = "//td[contains(text(),'" + (String) hashXlData.get("StandardCode") + "')]/preceding-sibling::td/input[contains(@type,'checkbox')]";
				driver.findElement(By.xpath(StandardCheckboxXpath)).click();

			}

			List<WebElement> billingSaveBtns = driver.findElements(By.xpath(OR.getProperty("Task_BillingCode_SaveAllBtn")));

			WebDriverWait wait3 = new WebDriverWait(driver, Integer.parseInt(CONFIG.getProperty("genMax_waitTime")));
			int countBillngSaveBtn = billingSaveBtns.size();
			for (int i = 0; i < countBillngSaveBtn; i++) {

				String oId = "mainForm:tabView:dataTable:" + i + ":savealltaskwo";
				String idxpath = "//*[@id='" + oId + "']";

				WebElement wbElement = driver.findElement(By.xpath(idxpath));
				new Actions(driver).moveToElement(wbElement).build().perform();

				WebElement element3 = wait3.until(ExpectedConditions.elementToBeClickable(By.xpath(idxpath)));

				Thread.sleep(1000);
				driver.findElement(By.xpath(idxpath)).click();

				fnpLoading_wait();

				new Actions(driver).moveToElement(driver.findElement(By.xpath(idxpath))).sendKeys(Keys.ARROW_DOWN).build().perform();
				Thread.sleep(100);

				fnpCheckError(" while adding Billing Code in Task Tab");

				String SuccessfulMsg = fnpGetText_OR("TopMessage3");
				Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"),
						"Top message does not contain 'success' word, so might be adding each billing code in Task Tab is not successful.");

			}

			fnpCheckError(" while adding data in Task Tab");

			fnpMymsg("Top Message after adding billing code in Task Tab----" + fnpGetText_OR("TopMessage3"));

			String SuccessfulMsg = fnpGetText_OR("TopMessage3");

			Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"),
					"Top message does not contain 'success' word, so might be adding billing code in Task Tab is not successful.");

			fnpMymsg(" Adding data in New Wo Task tab is Pass");
			
			
			if ((currentSuiteName.equalsIgnoreCase(Non_Food_Compounds_suite_Name)) ){
				fnpClick_OR("Task_ShowAllLink");
				
				//This while logic for wait till expanded task table gets closed
				int whileloopcounter=0;
				while (fnpCheckElementPresenceImmediately("Task_BillingCode_SaveAllBtn") ) {
					whileloopcounter++;
					if ((whileloopcounter > (Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))*2))) {
						fnpMymsg("@  ---still Task table rows are not hide after clicking 'Hide All' link even after too much time ---"+whileloopcounter+" seconds");
						break;
					}else{
						fnpMymsg("@  --- still Task table rows are not hide  after clicking 'Hide All' link after  time ---"+whileloopcounter+" seconds");	
					}
					Thread.sleep(1000);
				}
				
				
				int taskTypeColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskTypeColName);
				int testingTaskRowNo = fnpFindRow("TasksTable_EditWO", "TESTING", taskTypeColIndex);
				
				String dropTestingTask_xpath=".//*[@id='mainForm:tabView:dataTable:"+(testingTaskRowNo-1)+":deleteTaskBtn']";
				fnpClick_NOR(dropTestingTask_xpath);
				
				fnpClick_OR("DropConfirmation_Yes_btn");				
				fnpType("OR", "DropReasonTxtArea", "Automation Testing Purpose");				
				fnpClick_OR("DropConfirmation");
				
				int taskStatusColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_StatusColName);
				String Status_FirstValue = fnpFetchFromTable("TasksTable_EditWO", testingTaskRowNo, taskStatusColIndex);
				Assert.assertTrue(Status_FirstValue.equalsIgnoreCase("DROPPED"), "Testing task status is not becoming DROPPED after dropping it.");
			
			}
			
			if ( (currentSuiteName.equalsIgnoreCase(FPC_Work_Order_suite_Name)) ){
				boolean UnitActAmt_inputFound = false;
				for(int i=0; i<=5; i++) {
					try {
						driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
						if(driver.findElement(By.xpath(OR.getProperty("WO_TaskTab_BillCode_FACAUDSP_UnitActAmt_input"))).isDisplayed()) {
							UnitActAmt_inputFound= true;
							driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);
							break;
						}else { 
							Thread.sleep(2000);
						}
					}catch(Throwable e) {
						//nothing to do
					}
				}
				if(UnitActAmt_inputFound) {
					
					fnpType("OR", "WO_TaskTab_BillCode_FACAUDSP_UnitActAmt_input", "100");
				
					fnpClick_OR("WO_TaskTab_BillCode_FACAUDSP_SaveIcon");	
					
					fnpClick_OR("WO_TaskTab_BillCode_FACAUDSP_UnitActAmt_ConfirmationSaveButton");	
					
					fnpCheckError(" while adding data in Task Tab");
	
					fnpMymsg("Top Message after adding billing code in Task Tab----" + fnpGetText_OR("TopMessage3"));
	
					SuccessfulMsg = fnpGetText_OR("TopMessage3");
	
					Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"),
							"Top message does not contain 'success' word, so might be adding billing code in Task Tab is not successful.");
	
					fnpMymsg(" Adding data in New Wo Task tab is Pass");
				}
				
			}
			
				driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);
			
			isTestPass = true;
			fnpMymsg(" **************************************************************");

			//fnpCommonClickTaskTab();

		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "  Adding data in New Wo Facility tab is fail . ", "AddingDataFaciltyTabFail");

		}
	}

	// @Test(enabled = false)
	@Test(priority = 5, dependsOnMethods = { "AddingData__Tasks_Tab" })
	public void AddingData__Products_Tab() throws Throwable {

		fnpMymsg(" **************************************************************");
		fnpMymsg(" Executing AddingData__Products_Tab");
		try {
			// Thread.sleep(35000); // This is intentionally as Mukesh told that
			// it (Task tab) is still in saving mode and does not load product
			// tab
			// successfully.
			Thread.sleep(5000);

			fnpWaitForVisible("EditProductTabLink");
			fnpWaitTillVisiblityOfElementNClickable_OR("EditProductTabLink");
		//	fnpGetORObjectX("EditProductTabLink").click();
			fnpClick_OR("EditProductTabLink");
			fnpLoading_wait();
			
			//Thread.sleep(15000);
			
			if (!(currentSuiteName.equalsIgnoreCase(Non_Food_Compounds_suite_Name)) ){
				fnpWaitForVisible("ProductType");
			}else{
				fnpWaitForVisible("NFS_ProductTab_ProductTypeOptions");
			}

			if ((classNameText.equalsIgnoreCase("WO_Annual"))) {

				fnpWaitForVisible("ProductType");
				// fnpMouseHover("FooterElement");
				fnpMouseHover("logOutBtn2");
				fnpWaitTillVisiblityOfElementNClickable_OR("ProductType");
				fnpType("OR", "ProductType", (String) hashXlData.get("ProductType"));
				fnpWaitForVisible("ProductTab_ProductTypeOptions");
				String xpathProductTypeValue = OR.getProperty("ProductTab_ProductTypeOptionsList") + "[@data-item-label='" + (String) hashXlData.get("ProductType").trim() + "']";
				fnpWaitForVisible_NotInOR(xpathProductTypeValue);
				fnpMouseHover_NotInOR(xpathProductTypeValue);
				driver.findElement(By.xpath(xpathProductTypeValue)).click();

				String selectedProductType = fnpGetAttribute_OR("ProductType", "value");
				fnpMymsg("Product type has been inserted is " + selectedProductType);
				Assert.assertTrue(selectedProductType.contains((String) hashXlData.get("ProductType")), "Product type is not selected properly at Product tab in Annual WO ");

			}


/*
			
			
			String fileNames=(String) hashXlData.get("ProAddDocToWOFileName");
			String[] fileCount = fileNames.split(",");
			int fileCountSize = fileCount.length;
			
			if (!(fileCountSize>0)) {
				throw new Exception("Upload file names should be given in excel.");
			} 
			
			
			String fileTypes=(String) hashXlData.get("WOProductDocType");
			String[] fileTypeCount = fileTypes.split(",");
			int fileTypesCountSize = fileCount.length;
			
			if (!(fileTypesCountSize==fileCountSize)) {
				throw new Exception("Upload file type should be equal to the no. of files in excel.");
			} 
			
			String fileAccessNames=(String) hashXlData.get("Access");
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
					  

					  
					fnpClick_OR("ProAddDocumentBtn");
					//fnpWaitForVisible("ProAddDocSaveNCloseBtn");
					driver.findElement(By.xpath(OR.getProperty("ProAddDocumentSelectFilesBtn"))).sendKeys(fileName);
					Thread.sleep(1000);
					
					
					int iWhileCounter=0;
					while (fnpCheckElementPresenceImmediately("BrowseCancelUploadBtn")) {
						Thread.sleep(1000);
						iWhileCounter++;
						if (iWhileCounter>60) {
							break;
							
						}
					}
					
					fnpWaitForVisible("ProAddDocTypePFList");	

					//fnpPFList("ProAddDocTypePFList", "ProAddDocTypePFListOptions", fileTypeCount[f]);
					fnpPFListExactly("ProAddDocTypePFList", "ProAddDocTypePFListOptions", fileTypeCount[f]);
					
					fnpPFList("ProAddDocAccessPFList", "ProAddDocAccessPFListOptions", fileAccessCount[f]);
					
					
					fnpClick_OR("ProAddDocSaveNCloseBtn");
					
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
				

				
				fnpClick_OR("ProAddDocumentBtn");
				fnpWaitForVisible("ProAddDocSaveNCloseBtn");
				fnpMymsg("Going to upload files.");
			//	driver.findElement(By.xpath(OR.getProperty("ProAddDocumentSelectFilesBtn"))).sendKeys(fileName);
				
				String fname1 ;
				for(int f=0;f<fileCountSize;f++){			
					Thread.sleep(2000);					
					fname1 = System.getProperty("user.dir") + "\\docs\\" + fileCount[f];
					driver.findElement(By.xpath(OR.getProperty("ProAddDocumentSelectFilesBtn"))).sendKeys(fname1);
					Thread.sleep(1000);	
					fnpLoading_wait();
					Thread.sleep(3000);	
					fnpLoading_wait();
					
					int whileloopcounter=0;
					while (fnpCheckElementPresenceImmediately("BrowseCancelUploadBtn") ) {
						whileloopcounter++;
						if ((whileloopcounter > (Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))*2))) {
							fnpMymsg("@  ---still BrowseCancelUploadBtn is present after too much time ---"+whileloopcounter+" seconds");
							break;
						}else{
							fnpMymsg("@  --- BrowseCancelUploadBtn is present after  time ---"+whileloopcounter+" seconds");	
						}
						Thread.sleep(1000);
					}
					
					
				}
				
				
				
				//Pradeep--You can comment below lines later for wait
			*//***********************************************************//*	
				fnpMymsg("Hit the file Names.");
				Thread.sleep(1000);
				fnpLoading_wait();
				fnpLoading_waitInsideDialogBox();
				Thread.sleep(1000);
			*//***********************************************************//*	
				
				

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
				
				
				Thread.sleep(2000);
				
				String typelist;
				String accessList;
				int uploadedFilesCount=0;
				for(int f=0;f<fileCountSize;f++){	
					typelist=OR.getProperty("ProAddDocTypePFList_part1")+f+OR.getProperty("ProAddDocTypePFList_part2");
					accessList=OR.getProperty("ProAddDoc_AccessPFList_part1")+f+OR.getProperty("ProAddDoc_AccessPFList_part2");
					
					

					
					if (fnpCheckElementDisplayedImmediately_NOR(typelist)) {
						fnpPFListModify_NOR(typelist, fileTypeCount[f]);
						Thread.sleep(2000);	
						fnpPFListModify_NOR(accessList, fileAccessCount[f]);
						Thread.sleep(2000);	
						uploadedFilesCount++;
					}else
					{
						fnpMymsg("@@@@   ---will fail at product multiple doc functionality otherwise if not going to wait for all type list in script --"+classNameText);
					}
					
					

				}
				
				
				if (!(uploadedFilesCount >0)) {
					
					if (fileCountSize>1) {
						throw new Exception("Not a single document is uploaded successfully out of '"+fileCountSize+"' documents.");
					} else {
						throw new Exception("Document is not uploaded successfully.");
					}
					
					
				}
				
				
				fnpClickInDialog_OR("ProAddDocSaveNCloseBtn");	
				
				fnpCheckError("Error is thrown by application while adding data in Document Tab");	
				
				
				
				
			}
			
			
			
			fnpWaitTillThisElementIsNoLongerDisplayedTillMaxWait_OR("ProAddDocSaveNCloseBtn", "ProAddDocSaveNCloseBtn button is still visible after clicking it and waited 120 seconds.", 120);

*/			
			
			fnpUploadMultipleDocs((String) hashXlData.get("ProAddDocToWOFileName"),
					(String) hashXlData.get("WOProductDocType"),
						(String) hashXlData.get("Access"),
					"ProAddDocumentBtn", "ProAddDocSaveNCloseBtn", "ProAddDocumentSelectFilesBtn",
					"ProAddDocTypePFList_part1", "ProAddDocTypePFList_part2",
					"ProAddDoc_AccessPFList_part1", "ProAddDoc_AccessPFList_part2") ;

			fnpCheckError(" while adding data in Product Tab");

			fnpMymsg(" Adding data in New Wo Product tab is Pass");
			isTestPass = true;

			fnpMymsg(" **************************************************************");

		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "  Adding data in New Wo Product tab is fail . ", "AddingDataProductTabFail");

		}
	}

	@Test(priority = 6, dependsOnMethods = { "AddingData__Products_Tab" })
	public void AddingData__Documents_Tab() throws Throwable {

		fnpMymsg(" **************************************************************");
		fnpMymsg(" Executing AddingData__Documents_Tab");
		try {


			fnpClick_OR("EditDocumentTabLink");
	
			
			
			fnpWaitTillVisiblityOfElementNClickable_OR("WOAddDocAddBtn");
			
			
			
			
			fnpClick_OR("WOAddDocAddBtn");
			fnpWaitTillVisiblityOfElementNClickable_OR("DocTab_AddWODoc_SaveNCloseBtn");
			fnpWaitForVisible("DocTab_AddWODoc_SaveNCloseBtn");
			String fileName2 = System.getProperty("user.dir") + "\\docs\\" + (String) hashXlData.get("DocTab_AddWODoc_FileName");
			driver.findElement(By.xpath(OR.getProperty("DocTab_AddWODoc_BrowseBtn"))).sendKeys(fileName2);
			Thread.sleep(2000);
			

			
			int iloop=0;
			int maxloopWait = 10;
			while (true) {
				if (fnpCheckElementDisplayedImmediately("BrowseCancelUploadBtn")) {
					fnpMymsg("@@@   BrowseCancelUploadBtn is visible--" + iloop);
					break;
				} else {

					if (iloop > maxloopWait) {
						fnpMymsg("@@@    BrowseCancelUploadBtn is not visible after " + (maxloopWait + 1) + " seconds");
						break;
					} else {
						fnpMymsg("@@@    BrowseCancelUploadBtn is  visible --" + iloop);
						Thread.sleep(1000);
					}

					
					iloop++;
				}
			}
			
			
			
			

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
			
			
			Thread.sleep(2000);

			fnpPFListExactly("DocTab_AddWODoc_TypePFList", "DocTab_AddWODoc_TypePFListOptions", (String) hashXlData.get("WODocTab_AddWODocType"));
			
			fnpPFList("DocTab_AddWODoc_AccessPFList", "DocTab_AddWODoc_AccessPFListOptions", (String) hashXlData.get("WODocTab_AddWODocAccess"));
			fnpClickInDialog_OR("DocTab_AddWODoc_SaveNCloseBtn");

			
			
			

/*			
			
			fnpUploadMultipleDocs((String) hashXlData.get("DocTab_AddWODoc_FileName"),
					(String) hashXlData.get("WODocTab_AddWODocType"),
						(String) hashXlData.get("WODocTab_AddWODocAccess"),
					"WOAddDocAddBtn", "DocTab_AddWODoc_SaveNCloseBtn_MultiUpload_Dialog", "DocTab_AddDocumentSelectFilesBtn",
					"DocTab_AddDocTypePFList_part1", "DocTab_AddDocTypePFList_part2",
					"DocTab_AddDoc_AccessPFList_part1", "DocTab_AddDoc_AccessPFList_part2") ;
			
			
			
			*/
			
			
			
			fnpCheckError(" while adding data in Document Tab");

			fnpMymsg(" Adding data in New Wo Document tab is Pass");
			isTestPass = true;
			fnpMymsg(" **************************************************************");

		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "  Adding data in New Wo Document tab is fail . ", "AddingDataDocumentTabFail");

		}
	}

	@Test(priority = 7, dependsOnMethods = { "AddingData__Documents_Tab" })
	public void AddingData__Financials_Tab() throws Throwable {

	}

	@Test(priority = 8, dependsOnMethods = { "AddingData__Financials_Tab" })
	public void Verifying__ActionItems_Tab() throws Throwable {

		fnpMymsg(" **************************************************************");
		fnpMymsg(" Executing Verifying__ActionItems_Tab");
		try {

			fnpCommonClickSnapShotTab();

			fnpWaitForVisible("ActionItemTable_EditWO"); // Added on 24 June for
															// increasing
															// performance
			String FirstValueInTable = fnpFetchFromTable("ActionItemTable_EditWO", 1, 1);
			Assert.assertTrue(FirstValueInTable.contains("No Data Found"), "No Action items should be generated under the Action items tab.");
			fnpMymsg(" Verifying New Wo  Action Item tab is Pass as No Action Item has been generated till now");
			isTestPass = true;
			fnpMymsg(" **************************************************************");
			fnpCheckError(" while verifying Action Tab");

		} catch (Throwable t) {
			fnpCommonFinalCatchBlock(t, "  Verifying New Wo Action Item tab is failed . ", "VerifyingActionItemTabFail");

		}
	}

	@Test(priority = 8, dependsOnMethods = { "Verifying__ActionItems_Tab" })
	public void InsertValueForTR_LER_PER_QC() throws Throwable {

		fnpMymsg(" **************************************************************");
		fnpMymsg(" Executing InsertValueForTR_LER_PER_QC");
		try {
			//

			/**
			 * 12-01-16*********As IE browser is not responding after long run
			 * time , so going to close it and relogin
			 ******/
			if (fnpGetCurrRunningBrowserName().equalsIgnoreCase("IE")) {
				fnpCloseBroserAndLoginInIE();
				// fnpSearchWorkOrderAndClickEditAlso_AfterRestartWhenAlertsNo(workOrderNo);
				fnpSearchWorkOrderOnly(workOrderNo);

			}
			/***********
			 * As IE browser is not responding after long run time , so going to
			 * close it and relogin
			 ******/

			fnpCommonClickInfoTab();

			fnpClick_OR("InfoTab_TechReviLKPBtn");
			//fnpSearchNSelectFirstRadioBtn(2, (String) hashXlData.get("Tech_Reviewer"), 1);
			if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
				fnpSearchNSelectFirstRadioBtn(1, (String) hashXlData.get("Tech_Reviewer_Code"), 1);
			}else{
				fnpSearchNSelectFirstRadioBtn(2, (String) hashXlData.get("Tech_Reviewer"), 1);
			}			
			String techReviewer = fnpWaitTillTextBoxDontHaveValue("InfoTab_TechReviTxtBox", "value");
			//Assert.assertTrue(techReviewer.contains((String) hashXlData.get("Tech_Reviewer")), "Tech Reviewer Value is not selected properly from lookup");
			if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
				Assert.assertTrue(techReviewer.contains((String) hashXlData.get("Tech_Reviewer_Code")), "Tech Reviewer Value is not selected properly from lookup because expected is this '"+(String) hashXlData.get("Tech_Reviewer_Code")+"' but actual is this '"+techReviewer+"'.");
			}else{
				Assert.assertTrue(techReviewer.contains((String) hashXlData.get("Tech_Reviewer")), "Tech Reviewer Value is not selected properly from lookup because expected is this '"+(String) hashXlData.get("Tech_Reviewer")+"' but actual is this '"+techReviewer+"'.");
			}
			fnpMymsg(" Tech Reviewer value is properly selected from  lookup");

			
			
			
			
			
			
			
			
			
			fnpClick_OR("InfoTab_LiteEvalLKPBtn");
			//fnpSearchNSelectFirstRadioBtn(2, (String) hashXlData.get("LitEval_Reviewer"), 1);
			if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
				fnpSearchNSelectFirstRadioBtn(1, (String) hashXlData.get("LitEval_Reviewer_Code"), 1);
			}else{
				fnpSearchNSelectFirstRadioBtn(2, (String) hashXlData.get("LitEval_Reviewer"), 1);
			}
			String liteEvalReviewer = fnpWaitTillTextBoxDontHaveValue("InfoTab_LiteEvalTxtBox", "value");
			//Assert.assertTrue(liteEvalReviewer.contains((String) hashXlData.get("LitEval_Reviewer")), "Lite Eval Reviewer Value is not selected properly from lookup");
			if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
				Assert.assertTrue(liteEvalReviewer.contains((String) hashXlData.get("LitEval_Reviewer_Code")), "Lite Eval Reviewer Value is not selected properly from lookup because expected is this '"+(String) hashXlData.get("LitEval_Reviewer_Code")+"' but actual is this '"+liteEvalReviewer+"'.");
			}else{
				Assert.assertTrue(liteEvalReviewer.contains((String) hashXlData.get("LitEval_Reviewer")), "Lite Eval Reviewer Value is not selected properly from lookup because expected is this '"+(String) hashXlData.get("LitEval_Reviewer")+"' but actual is this '"+liteEvalReviewer+"'.");
			}
			fnpMymsg(" Lite Eval  Reviewer value is properly selected from  lookup");

			
			
			
			
			
			
			fnpClick_OR("InfoTab_PhyEvalLKPBtn");
			//fnpSearchNSelectFirstRadioBtn(2, (String) hashXlData.get("Phys_Eval_Reviewer"), 1);
			if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
				fnpSearchNSelectFirstRadioBtn(1, (String) hashXlData.get("Phys_Eval_Reviewer_Code"), 1);
			}else{
				fnpSearchNSelectFirstRadioBtn(2, (String) hashXlData.get("Phys_Eval_Reviewer"), 1);
			}
			String phyEvaluator = fnpWaitTillTextBoxDontHaveValue("InfoTab_PhyEvalTxtBox", "value");
			//Assert.assertTrue(phyEvaluator.contains((String) hashXlData.get("Phys_Eval_Reviewer")), "Phy Eval Reviewer Value is not selected properly from lookup");
			if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
				Assert.assertTrue(phyEvaluator.contains((String) hashXlData.get("Phys_Eval_Reviewer_Code")), "Phy Eval Reviewer Value is not selected properly from lookup because expected is this '"+(String) hashXlData.get("Phys_Eval_Reviewer_Code")+"' but actual is this '"+phyEvaluator+"'.");
			}else{
				Assert.assertTrue(phyEvaluator.contains((String) hashXlData.get("Phys_Eval_Reviewer")), "Phy Eval Reviewer Value is not selected properly from lookup because expected is this '"+(String) hashXlData.get("Phys_Eval_Reviewer")+"' but actual is this '"+phyEvaluator+"'.");
			}
			fnpMymsg(" Phy Eval Reviewer value is properly selected from  lookup");

			
			
			
			fnpClick_OR("InfoTab_CTDeciMakerLKPBtn");
			//fnpSearchNSelectFirstRadioBtn(2, (String) hashXlData.get("QCCertDecision_Maker"), 1);
			if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
				fnpSearchNSelectFirstRadioBtn(1, (String) hashXlData.get("QCCertDecision_Maker_Code"), 1);
			}else{
				fnpSearchNSelectFirstRadioBtn(2, (String) hashXlData.get("QCCertDecision_Maker"), 1);
			}
			String certDeciMaker = fnpWaitTillTextBoxDontHaveValue("InfoTab_CTDeciMakerTxtBox", "value");
			//Assert.assertTrue(certDeciMaker.contains((String) hashXlData.get("QCCertDecision_Maker")), "Cert Decision Maker Value is not selected properly from lookup");
			if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
				Assert.assertTrue(certDeciMaker.contains((String) hashXlData.get("QCCertDecision_Maker_Code")), "Cert Decision Maker Value is not selected properly from lookup because expected is this '"+(String) hashXlData.get("QCCertDecision_Maker_Code")+"' but actual is this '"+certDeciMaker+"'.");
			}else{
				Assert.assertTrue(certDeciMaker.contains((String) hashXlData.get("QCCertDecision_Maker")), "Cert Decision Maker Value is not selected properly from lookup because expected is this '"+(String) hashXlData.get("QCCertDecision_Maker")+"' but actual is this '"+certDeciMaker+"'.");
			}
			fnpMymsg(" Cert Decision Maker value is properly selected from  lookup");

			
			
			/************9.2 sprint Cert decider new mandatory field***********************************************/
			
		//	fnpPFList("InfoTab_CertDeciderPFList", "InfoTab_CertDeciderPFListOptions", (String) hashXlData.get("Cert_Decider"));
			
			
			
			/**********************************************************/
			
			if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
				fnpPFList("InfoTab_CertDeciderPFList", "InfoTab_CertDeciderPFListOptions", (String) hashXlData.get("Cert_Decider_Code"));
				
			}else{
				fnpPFList("InfoTab_CertDeciderPFList", "InfoTab_CertDeciderPFListOptions", (String) hashXlData.get("Cert_Decider"));
			}
			

			
			
		//	fnpType("OR", "InfoTab_SalesForceOpportunityId_txt", "auto test sales id");
			fnpType("OR", "InfoTab_SalesForceOpportunityId_txt", (String) hashXlData.get("SalesForceId"));
			
			
			
			
			fnpClick_OR("ProAddDocSaveBtn");
			//fnpCheckError("while Inserting Value For TR,LER,PER and QC and then added Standard Version.");
			fnpCheckError("while Inserting Value For TR,LER,PER , QC and Cert decider and then added Standard Version.");

		} catch (Throwable t) {
			fnpCommonFinalCatchBlock(t, "  InsertValueForTR_LER_PER_QC is failed . ", "InsertValueForTR_LER_PER_QCFail");

		}
	}

	@Test(priority = 9, dependsOnMethods = { "InsertValueForTR_LER_PER_QC" })
	public void Check_RemoveAlert_AssignedWithTaskSetupIncomplete() throws Throwable {

		fnpMymsg(" **************************************************************");
		try {

			/***
			 * 12-01-16********As IE browser is not responding after long run
			 * time , so going to close it and relogin
			 ******/
			if (fnpGetCurrRunningBrowserName().equalsIgnoreCase("IE")) {
				fnpCloseBroserAndLoginInIE();
				// fnpSearchWorkOrderAndClickEditAlso_AfterRestartWhenAlertsNo(workOrderNo);
				fnpSearchWorkOrderOnly(workOrderNo);

			}
			/***********
			 * As IE browser is not responding after long run time , so going to
			 * close it and relogin
			 ******/

			fnpCommonGoToHomeNClick();

			// -------Deleted Alert 'Draft Work order(s) assigned with task
			// setup incomplete -------------------
			fnpCommonAlertDeletedVerification("Task_Setup_Incomplete", "TaskSetupIncompleteAlertTable_header", "WO #", "TaskSetupIncompleteAlertTable",
					"TaskSetupIncompleteAlert_WO_filterTxtBox", workOrderNo);
			// ---------------------------------------------

			fnpMymsg(" **************************************************************");

		} catch (Throwable t) {
			fnpCommonFinalCatchBlock(t, "  check_RemoveAlert_AssignedWithTaskSetupIncomplete is fail. ", "check_RemoveAlert_AssignedWithTaskSetupIncomplete");
		}

	}

	@Test(priority = 10, dependsOnMethods = { "Check_RemoveAlert_AssignedWithTaskSetupIncomplete" })
	public void Verifying__DRAFT_INREVIEW() throws Throwable {

		fnpMymsg(" **************************************************************");
		fnpMymsg(" Executing Verifying__DRAFT_INREVIEW");
		try {

			fnpCommonDraftToInReviewCode();
			
			int totalRowGenerated = fnpCountRowsInTable("ActionItemTable_EditWO");
			Assert.assertTrue(totalRowGenerated == 3, "Total Action Items generated must be 3 but here they are only '" + totalRowGenerated + "'  .");

			int itemDescColIndex = fnpFindColumnIndex("ActionItemTable_HeaderRow", "Item Desc.");
			String ItemDesc_FirstValue = fnpFetchFromTable("ActionItemTable_EditWO", 1, itemDescColIndex);
			String ItemDesc_SecondValue = fnpFetchFromTable("ActionItemTable_EditWO", 2, itemDescColIndex);
			String ItemDesc_ThirdValue = fnpFetchFromTable("ActionItemTable_EditWO", 3, itemDescColIndex);

			String[] expectedItemDescArray = ((String) hashXlData.get("AItem_ItemDesc_INREVIEW")).split(",");


			for (int i = 0; i < expectedItemDescArray.length; i++) {

				if (expectedItemDescArray[i].equalsIgnoreCase(ItemDesc_FirstValue)) {
					fnpMymsg(" Action Item '" + expectedItemDescArray[i] + "' is presenst at row no '1' .");
					continue;
				}

				if (expectedItemDescArray[i].equalsIgnoreCase(ItemDesc_SecondValue)) {
					fnpMymsg(" Action Item '" + expectedItemDescArray[i] + "' is presenst at row no '2' .");
					continue;
				}

				if (expectedItemDescArray[i].equalsIgnoreCase(ItemDesc_ThirdValue)) {
					fnpMymsg(" Action Item '" + expectedItemDescArray[i] + "' is presenst at row no '3' .");
					continue;
				}
				msg="This action item '" + expectedItemDescArray[i] + "' is not present in any row 1-3  .";
				fnpMymsg(msg);
				throw new Exception(msg);
			}

			int StatusColIndex = fnpFindColumnIndex("ActionItemTable_HeaderRow", "Status");
			String Status_FirstValue = fnpFetchFromTable("ActionItemTable_EditWO", 1, StatusColIndex);
			String Status_SecondValue = fnpFetchFromTable("ActionItemTable_EditWO", 2, StatusColIndex);
			String Status_ThirdValue = fnpFetchFromTable("ActionItemTable_EditWO", 3, StatusColIndex);

			String expectedStatus = "PENDING";

			Assert.assertTrue(Status_FirstValue.equalsIgnoreCase(expectedStatus), "First Status is not [" + expectedStatus + "] .");
			Assert.assertTrue(Status_SecondValue.equalsIgnoreCase(expectedStatus), "Second Status is not [" + expectedStatus + "] .");
			Assert.assertTrue(Status_ThirdValue.equalsIgnoreCase(expectedStatus), "Third Status is not [" + expectedStatus + "] .");

			fnpMymsg("Action Items has been generated with Pending status. ");

			fnpWaitTillClickable("EditTaskTabLink");
			fnpGetORObjectX("EditTaskTabLink").click();
			fnpLoading_wait();

			fnpWaitForVisible("Task_ShowAllLink");

			String expectedClientAppReviewStatus = "INPERFORM";
			int taskStatusColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_StatusColName);
			int taskTypeColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskTypeColName);
			int ClientAppReviewRowNo = fnpFindRow("TasksTable_EditWO", taskType_ClientAPPReview, taskTypeColIndex);
			String actualClientAppReviewStatus = fnpFetchFromTable("TasksTable_EditWO", ClientAppReviewRowNo, taskStatusColIndex);

			Assert.assertTrue(actualClientAppReviewStatus.equalsIgnoreCase(expectedClientAppReviewStatus), "Client App Review Status is not [" + expectedClientAppReviewStatus
					+ "] .");

			fnpMymsg(" Client App Review 's Task Status in Task tab now becomes in INPERFORM status ");

			fnpMymsg("Just before counting the rows in task table");
			int totalRowTaskTable = fnpCountRowsInTable("TasksTable_EditWO");
			taskStatusColIndex = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_StatusColName);

			fnpMymsg("Task Table rows are :" + totalRowTaskTable);
			for (int i = 2; i < totalRowTaskTable; i++) {
				String taskStatus = fnpFetchFromTable("TasksTable_EditWO", i, taskStatusColIndex);
				fnpMymsg("@  ----task status for row --" + i + "  is---" + taskStatus);
				Assert.assertTrue(taskStatus.equalsIgnoreCase("CREATED"), "All other tasks statuses except 'Client App Review' are not having 'CREATED' status.");

			}
			fnpMymsg("All other tasks statuses except 'Client App Review' have 'CREATED' status.");

			fnpMymsg("  VerifyDRAFT_INREVIEW is Pass as status changed to 'INREVIEW' and Action Items has been generated with Pending status and Client App Review's tasks status becomes 'INPERFORM' now. ");
			isTestPass = true;
			fnpMymsg(" **************************************************************");

			fnpCheckError(" while  VerifyDRAFT_INREVIEW");

		} catch (Throwable t) {
			fnpCommonFinalCatchBlock(t, "   VerifyDRAFT_INREVIEW  is failed . ", "VerifyingDraftToInReviewFail");

		}
	}

	@Test(priority = 11, dependsOnMethods = { "Verifying__DRAFT_INREVIEW" })
	public void Check_alerts_DRAFT_INREVIEW() throws Throwable {
		fnpMymsg(" *********************check_alerts_DRAFT_INREVIEW*****************************************");
		try {

			fnpCommonGoToHomeNClick();

			// -------Alert Action item assigned -------------------
			fnpCommonAlertGeneratedVerification_SpecialCaseActionItemAssignedVerify2Rows("Action_item_assigned", "AI_ITEMS_ASSIGNED_AlertTable_header", "WO #",
					"AI_ITEMS_ASSIGNED_AlertTable", "AI_ITEMS_ASSIGNED_Alert_WO_filterTxtBox", workOrderNo); //
			// ---------------------------------------------

			// -------Action_item_assigned_to_client -------------------
			fnpCommonAlertGeneratedVerification("Action_item_assigned_to_client", "AI_ASSIGNED_CLIENT_IND_AlertTable_header", "WO #", "AI_ASSIGNED_CLIENT_IND_AlertTable",
					"AI_ASSIGNED_CLIENT_IND_Alert_WO_filterTxtBox", workOrderNo);
			// ---------------------------------------------

			// -------Alert Task_with_open_Action_Items -------------------
			fnpCommonAlertGeneratedVerification("Task_with_open_Action_Items", "TA_TASK_OPEN_ITEMS_AlertTable_header", "WO #", "TA_TASK_OPEN_ITEMS_AlertTable",
					"TA_TASK_OPEN_ITEMS_Alert_WO_filterTxtBox", workOrderNo);
			// ---------------------------------------------

			// -------Alert Work_Order_with_open_action_items
			// -------------------
			fnpCommonAlertGeneratedVerification("Work_Order_with_open_action_items", "WO_WITH_OPEN_ACTION_ITEMS_AlertTable_header", "WO #", "WO_WITH_OPEN_ACTION_ITEMS_AlertTable",
					"WO_WITH_OPEN_ACTION_ITEMS_Alert_WO_filterTxtBox", workOrderNo);
			// ---------------------------------------------

			// -------Alert Work_order_in_INREVIEW_status -------------------
			fnpCommonAlertGeneratedVerification("Work_order_in_INREVIEW_status", "InReviewStatusAlertTable_header", "WO #", "InReviewStatusAlertTable",
					"InReviewStatusAlert_WO_filterTxtBox", workOrderNo);
			// ---------------------------------------------

			// ------Deleted Alert work order: - work order in draft status
			// -------------------
			fnpCommonAlertDeletedVerification("Draft_Status", "DraftStatusAlertTable_header", "WO #", "DraftStatusAlertTable", "DraftStausAlert_WO_filterTxtBox", workOrderNo);
			// ---------------------------------------------

			fnpMymsg(" **************************************************************");

		} catch (Throwable t) {
			fnpCommonFinalCatchBlock(t, "   check_alerts_DRAFT_INREVIEW is fail. ", "check_alerts_DRAFT_INREVIEW");
		}

	}
	
	
	
/*	@Test(priority = 12, dependsOnMethods = { "Check_alerts_DRAFT_INREVIEW" })
	public void CLEANUP_WO_AUTOMATION_DATA() throws Throwable {
		BS01.CLEANUP_WO_AUTOMATION_DATA();
	}
	*/
	

	@AfterMethod
	public void reportDataSetResult() {
		if (fail) {
			isTestPass = false;
		}
	}

	@AfterTest
	public void reportTestResult() throws Throwable {
		int rowNum = Work_Order_suitexls.getCellRowNum("Test Cases", "TCID", this.getClass().getSimpleName());
		Work_Order_suitexls.setCellData("Test Cases", "Last_Execution", rowNum, fnReturnDateWithTimeForExcel());

		// Add Results column to test case sheet
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

		fnpCommonCloseBrowsersAndKillProcess();

	}

}