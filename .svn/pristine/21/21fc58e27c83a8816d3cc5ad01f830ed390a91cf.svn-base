package nsf.ecap.Wales_Work_Order_suite;

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

public class TestSuiteBase_Wales_Work_Order_suite extends TestSuiteBase {


	public  ModBrack_WRAS_Approved BS_01;
	public static String runmodes[] = null;
	//public  int count = -1;








	// check if the suite has to be skipped or not
	@BeforeSuite
	public void checkSuiteSkip() throws Throwable {

		fnInitialize();

		//currentSuiteName = "Proposals_suite";
		setCurrentSuiteName(Wales_Work_Order_suite_Name);
		setCurrentSuiteExcel(Wales_Work_Order_suitexls);
		fnpMymsg("             ");
		fnpMymsg("             ");
		fnpMymsg("=========================================================================================================================================");
		fnpMymsg("####################" + currentSuiteName + " Start ############################################################");
		fnpMymsg("Checking Runmode of " + currentSuiteName);
		if (!TestUtil.isSuiteRunnable(suiteXls, currentSuiteName)) {
			fnpMymsg("Skipped " + currentSuiteName + "  as the runmode was set to NO");

			throw new SkipException("Runmode of " + currentSuiteName + " set to no. So Skipping all tests in " + currentSuiteName);
		}

		if (TestUtil.isSuiteRunnable(suiteXls, currentSuiteName)) {
			browserName = TestUtil.getBrowserName(suiteXls, currentSuiteName);
			excelSiteURL= TestUtil.getExcelSiteURL(suiteXls, currentSuiteName);
			fnpMymsg("Browser Name is :" + browserName);
			fnpMymsg("======================");
			currentSuiteCode = "Wales_WO";

			int rowNum = suiteXls.getCellRowNum("Test Suite", "TSID", currentSuiteName);
			suiteXls.setCellData("Test Suite", "Last_Execution", rowNum, fnReturnDateWithTimeForExcel());

		}
		
		
		// fnpDeleteSMTPMessages();

	}



	
	public void fnpVerifyClientAppReviewerTask() throws Throwable {

		try {
			fnpMymsg("Going to verify Client App Reviewer task is assigned to AM or not.");
			fnpCommonClickSnapShotTab();

			int assignedToColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", SnapShot_taskTable_AssignedToColName);

			int TaskDescColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", SnapShot_taskTable_TaskDescColName);
			int clientAppReviewRowNo = fnpFindRow("Snapshot_TasksSummaryTable", SnapShot_Client_App_Review_Task, TaskDescColIndex);

			String clientAppReviewAssigner = fnpFetchFromTable("Snapshot_TasksSummaryTable", clientAppReviewRowNo, assignedToColIndex);
			

			
			if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
				Assert.assertTrue(clientAppReviewAssigner.trim().contains((String) hashXlData.get("AccountManager_Code").trim()), "Client App Reviewer is not assigned to Account Manager because expected is this '"+(String) hashXlData.get("AccountManager_Code")+"' but actual is this '"+clientAppReviewAssigner+"'.");
			}else{
				Assert.assertEquals(clientAppReviewAssigner.trim(), (String) hashXlData.get("AccountManager").trim(), "Client App Reviewer is not assigned to Account Manager because expected is this '"+(String) hashXlData.get("AccountManager")+"' but actual is this '"+clientAppReviewAssigner+"'.");
			}
			
			
			
		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "   VerifyClientAppReviewerTask  is failed . ", "VerifyClientAppReviewerTask_Failed");

		}
	}

	
	




	// To remove IEDriver.exe from Windows Task Manager
	 @AfterSuite(alwaysRun=true)
	//@AfterSuite
	public void cleanUp() {
		 
		
		
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


	
	 public void fnpMatchDiscountRushFees_inDiscountRushFeesTable_with_DiscountRushAmount_In_PendingInvoiceRequestsTable() throws Throwable{
		 
			int feeAmountColIndex = fnpFindColumnIndex("FinancialTab_DiscountRush_table_header", "Fee Amount");
			int revCategoryColIndex = fnpFindColumnIndex("FinancialTab_DiscountRush_table_header", "Rev Category");

			int discountRowNo = fnpFindRow("FinancialTab_DiscountRush_table", "Discount", revCategoryColIndex);
			String discountAmount = fnpFetchFromTable("FinancialTab_DiscountRush_table", discountRowNo, feeAmountColIndex);
			discountAmount=fnpRemoveOtherCharactersFromAmount(discountAmount);
			double discountAmount_inDouble=Double.parseDouble(discountAmount);
			
			int rushRowNo = fnpFindRow("FinancialTab_DiscountRush_table", "Rush", revCategoryColIndex);
			String rushAmount = fnpFetchFromTable("FinancialTab_DiscountRush_table", rushRowNo, feeAmountColIndex);
			rushAmount=fnpRemoveOtherCharactersFromAmount(rushAmount);
			double rushAmount_inDouble=Double.parseDouble(rushAmount);
			
			
			
			
			
			int invoiceCategoryColIndex_inPendingInvoiceRequestsTable = fnpFindColumnIndex("FinancialTab_PendingInvoiceRequests_table_header", "Invoice Category");
			int amountColIndex_inPendingInvoiceRequestsTable = fnpFindColumnIndex("FinancialTab_PendingInvoiceRequests_table_header", "Amount");

			int discountRowNo_inPendingInvoiceRequestsTable = fnpFindRow("FinancialTab_PendingInvoiceRequests_table", "Discount", invoiceCategoryColIndex_inPendingInvoiceRequestsTable);
			String discountAmount_inPendingInvoiceRequestsTable = fnpFetchFromTable("FinancialTab_PendingInvoiceRequests_table", discountRowNo_inPendingInvoiceRequestsTable, amountColIndex_inPendingInvoiceRequestsTable);
			discountAmount_inPendingInvoiceRequestsTable=fnpRemoveOtherCharactersFromAmount(discountAmount_inPendingInvoiceRequestsTable);
			double discountAmount_inPendingInvoiceRequestsTable_inDouble=Double.parseDouble(discountAmount_inPendingInvoiceRequestsTable);
			
			
			int rushRowNo_inPendingInvoiceRequestsTable = fnpFindRow("FinancialTab_PendingInvoiceRequests_table", "Rush Cost", invoiceCategoryColIndex_inPendingInvoiceRequestsTable);
			String rushAmount_inPendingInvoiceRequestsTable = fnpFetchFromTable("FinancialTab_PendingInvoiceRequests_table", rushRowNo_inPendingInvoiceRequestsTable, amountColIndex_inPendingInvoiceRequestsTable);
			rushAmount_inPendingInvoiceRequestsTable=fnpRemoveOtherCharactersFromAmount(rushAmount_inPendingInvoiceRequestsTable);
			double rushAmount_inPendingInvoiceRequestsTable_inDouble=Double.parseDouble(rushAmount_inPendingInvoiceRequestsTable);
			
			
			if (discountAmount_inDouble!=discountAmount_inPendingInvoiceRequestsTable_inDouble) {
				msg="Discount 'Fee Amount' in Discount/Rush Table is not equal to the Discount 'Amount' in Pending Invoice Requests table at Financials tab, hence failed.";
				fnpMymsg(msg);
				throw new Exception(msg);
				
			} else {
				fnpMymsg("Discount 'Fee Amount' in Discount/Rush Table is equal to the Discount 'Amount' in Pending Invoice Requests table at Financials tab.");
			}
			
			
			if (rushAmount_inDouble!=rushAmount_inPendingInvoiceRequestsTable_inDouble) {
				msg="Rush 'Fee Amount' in Discount/Rush Table is not equal to the Rush Cost 'Amount' in Pending Invoice Requests table at Financials tab, hence failed.";
				fnpMymsg(msg);
				throw new Exception(msg);
				
			} else {
				fnpMymsg("Rush 'Fee Amount' in Discount/Rush Table is equal to the Rush Cost  'Amount' in Pending Invoice Requests table at Financials tab.");
			}
			
			
		 
		 
	 }


	 
	 
	 
	 
	 public void fnpMatchUniqueQuotedAmount_inFinancialInfoTable_with_CertificationServicesAmount_In_PendingInvoiceRequestsTable() throws Throwable{
		 
			int invoiceCategoryColIndex = fnpFindColumnIndex("FinancialTab_FinancialInfo_table_header", "Invoice Category");
			int unitQuotedAmtColIndex = fnpFindColumnIndex("FinancialTab_FinancialInfo_table_header", "Unit Quoted Amt");

			int invoiceCategoryRowNo = fnpFindRow("FinancialTab_FinancialInfo_table", "Certification Services", invoiceCategoryColIndex);
			String certificationServices_UniqueQuotedAmount = fnpFetchFromTable("FinancialTab_FinancialInfo_table", invoiceCategoryRowNo, unitQuotedAmtColIndex);
			certificationServices_UniqueQuotedAmount=fnpRemoveOtherCharactersFromAmount(certificationServices_UniqueQuotedAmount);
			double certificationServices_UniqueQuotedAmount_inDouble=Double.parseDouble(certificationServices_UniqueQuotedAmount);
			

			
			
			
			
			
			int invoiceCategoryColIndex_inPendingInvoiceRequestsTable = fnpFindColumnIndex("FinancialTab_PendingInvoiceRequests_table_header", "Invoice Category");
			int amountColIndex_inPendingInvoiceRequestsTable = fnpFindColumnIndex("FinancialTab_PendingInvoiceRequests_table_header", "Amount");

			int certificationServicesRowNo_inPendingInvoiceRequestsTable = fnpFindRow("FinancialTab_PendingInvoiceRequests_table", "Certification Services", invoiceCategoryColIndex_inPendingInvoiceRequestsTable);
			String certificationServicesAmount_inPendingInvoiceRequestsTable = fnpFetchFromTable("FinancialTab_PendingInvoiceRequests_table", certificationServicesRowNo_inPendingInvoiceRequestsTable, amountColIndex_inPendingInvoiceRequestsTable);
			certificationServicesAmount_inPendingInvoiceRequestsTable=fnpRemoveOtherCharactersFromAmount(certificationServicesAmount_inPendingInvoiceRequestsTable);
			double certificationServicesAmount_inPendingInvoiceRequestsTable_inDouble=Double.parseDouble(certificationServicesAmount_inPendingInvoiceRequestsTable);
			

			
			if (certificationServices_UniqueQuotedAmount_inDouble!=certificationServicesAmount_inPendingInvoiceRequestsTable_inDouble) {
				msg="Certification Services 'Unique Quoted Amount' in 'Financial Info' table is not equal to the Certification Services 'Amount' in Pending Invoice Requests table at Financials tab, hence failed.";
				fnpMymsg(msg);
				throw new Exception(msg);
				
			} else {
				fnpMymsg("Certification Services 'Unique Quoted Amount' in 'Financial Info' table is  equal to the Certification Services 'Amount' in Pending Invoice Requests table at Financials tab.");
			}
			
	
		 
		 
	 }
	 
	 
	 
	 
	 
	 
	 

	 
	 
	 
	 
	 
	 
	 

}
