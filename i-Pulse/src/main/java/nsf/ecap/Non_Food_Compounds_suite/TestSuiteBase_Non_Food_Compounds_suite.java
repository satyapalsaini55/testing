package nsf.ecap.Non_Food_Compounds_suite;

import static nsf.ecap.config.IPULSE_CONSTANTS.*;



import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import nsf.ecap.Proposals_suite.TestSuiteBase_Proposal;
import nsf.ecap.Work_Order_suite.Modbrack_Not_Certified;
import nsf.ecap.Work_Order_suite.NewNew_Draft_InReview_No_Fac;
import nsf.ecap.Work_Order_suite.TestSuiteBase;
import nsf.ecap.config.IPULSE_CONSTANTS;
import nsf.ecap.util.ErrorUtil;
import nsf.ecap.util.TestUtil;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.os.WindowsUtils;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.google.common.base.Throwables;
import com.google.common.base.Verify;

public class TestSuiteBase_Non_Food_Compounds_suite extends TestSuiteBase {

	
	public static String runmodes[] = null;
	public static int count = -1;
	
	//public static String usingGoldenProcedureOrOasis="Oasis";//"golden";//"Oasis" or "golden" value

	public  NFC_ExistingNew_Draft_Complete NFC_ExistingNew_Draft_Complete_BS01;
	public  Modbrack_Not_Certified BS16;

	// check if the suite has to be skipped or not
	@BeforeSuite
	public void checkSuiteSkip() throws Throwable {

		fnInitialize();


		setCurrentSuiteName(Non_Food_Compounds_suite_Name);
		currentSuiteCode = "NFC";
		//currentSuiteXls = Dietary_Supplement_suitexls;
		setCurrentSuiteExcel(Non_Food_Compounds_suitexls);
		
		fnpMymsg("             ");
		fnpMymsg("             ");
		fnpMymsg("=========================================================================================================================================");
		fnpMymsg("####################" + currentSuiteName + " Start ############################################################");
		fnpMymsg("Checking Runmode of " + currentSuiteName);
		if (!TestUtil.isSuiteRunnable(suiteXls, currentSuiteName)) {
			fnpMymsg("Skipped " + currentSuiteName + "  as the runmode was set to NO");
			//fnpMymsg("####################  " + currentSuiteName + "  End ############################################################");
			//fnpMymsg("=========================================================================================================================================");
			throw new SkipException("Runmode of " + currentSuiteName + " set to no. So Skipping all tests in " + currentSuiteName);
		}

		if (TestUtil.isSuiteRunnable(suiteXls, currentSuiteName)) {
			browserName = TestUtil.getBrowserName(suiteXls, currentSuiteName);
			excelSiteURL = TestUtil.getExcelSiteURL(suiteXls, currentSuiteName);
			fnpMymsg("Browser Name is :" + browserName);
			fnpMymsg("======================");
			

			int rowNum = suiteXls.getCellRowNum("Test Suite", "TSID", currentSuiteName);
			suiteXls.setCellData("Test Suite", "Last_Execution", rowNum, fnReturnDateWithTimeForExcel());

			
		}

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
			referenceSuite = currentSuiteName;
			fnpMymsg("#################### "+currentSuiteName+" Suite End ############################################################");
			if (driver!=null) {
				driver.quit();
			}
			IsBrowserPresentAlready = false;
			killprocess();
		} catch (Throwable t) {
			// Nothing to do
		}

	}
	
	
	

	

	
	public void fnpRevenuePostedAmount() throws Throwable{
		
		//creating a hashMap
		int totalRows_taskTab=fnpCountRowsInTable("TasksTable_EditWO");
		int taskNo_ColIndex_taskTab = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskNoColName);
		int tasktype_ColIndex_taskTab = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskTypeColName);
		int taskStatusColIndex_taskTab = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_StatusColName);
		
		HashMap<String, String> hashTaskMap = new HashMap <String, String>();
		HashMap<String, String> hashTaskMap2 = new HashMap <String, String>();
		String hashkey;
		String hashValue;

		String taskNo_taskTab;
		String taskType_taskTab;
		String taskStatus_taskTab;
		
		for (int i = 1; i < totalRows_taskTab+1; i++) {
			taskNo_taskTab=fnpFetchFromTable("TasksTable_EditWO", i, taskNo_ColIndex_taskTab);
			taskType_taskTab=fnpFetchFromTable("TasksTable_EditWO", i, tasktype_ColIndex_taskTab);
			taskStatus_taskTab=fnpFetchFromTable("TasksTable_EditWO", i, taskStatusColIndex_taskTab);
			
			hashkey = taskNo_taskTab;
			hashValue = taskType_taskTab+"::"+taskStatus_taskTab;

			if (!hashkey.isEmpty()) {
				hashTaskMap.put(hashkey, hashValue);
			}

		}
		
		
		
		
		fnpCommonClickFinancialTab();
		fnpWorkAroundToClickbottomFooter();
		fnpClick_OR("FinancialTab_FinancialInfoTable_triangleIcon_toExpand");
		
		
		int billingCodeColIndex = fnpFindColumnIndex("FinancialTab_FinancialInfo_InnerTable_ForRevenuePostedAmt_table_header", "Billing Code");
		int woTaskColIndex = fnpFindColumnIndex("FinancialTab_FinancialInfo_InnerTable_ForRevenuePostedAmt_table_header", "WO Tasks");
		int taskStatusColIndex = fnpFindColumnIndex("FinancialTab_FinancialInfo_InnerTable_ForRevenuePostedAmt_table_header", "Task Status");
		int RevenuePostedAmountColIndex = fnpFindColumnIndex("FinancialTab_FinancialInfo_InnerTable_ForRevenuePostedAmt_table_header", "Revenue Posted Amt");
		//int scopeValidationrowNo = fnpFindRow("Snapshot_TasksSummaryTable", SnapShot_taskDesc_ScopeValidation, TaskDescColIndex);
		//String scopeValidationAssigner = fnpFetchFromTable("FinancialTab_FinancialInfo_InnerTable_ForRevenuePostedAmt_table", scopeValidationrowNo, assignedToColIndex);
		
		
		int totalRows=fnpCountRowsInTable("FinancialTab_FinancialInfo_InnerTable_ForRevenuePostedAmt_table");
		String billingCode;
		String woTaskCode;
		String taskStatus;
		String revenuPostedAmount;
		fnpMymsg("********************************Revenue Posted Amount Details********************************************************************************************************");
		fnpMymsg("|| Billing Code    ||  WO Tasks || Task Status  || Revenue Posted Amount ||");
		String currentHashValue;
		String currentTaskName;
		String currentStatus;
		double revenuePostedAmount_double;
		for (int i = 1; i < totalRows+1; i++) {
			billingCode = fnpFetchFromTable("FinancialTab_FinancialInfo_InnerTable_ForRevenuePostedAmt_table", i, billingCodeColIndex);
			woTaskCode = fnpFetchFromTable("FinancialTab_FinancialInfo_InnerTable_ForRevenuePostedAmt_table", i, woTaskColIndex);
			taskStatus = fnpFetchFromTable("FinancialTab_FinancialInfo_InnerTable_ForRevenuePostedAmt_table", i, taskStatusColIndex);
			revenuPostedAmount = fnpFetchFromTable("FinancialTab_FinancialInfo_InnerTable_ForRevenuePostedAmt_table", i, RevenuePostedAmountColIndex).trim();
			if (revenuPostedAmount.equalsIgnoreCase("")) {
				revenuePostedAmount_double=0;
			}else{
				revenuePostedAmount_double=Double.parseDouble(revenuPostedAmount);
			}
			
			
			fnpMymsg("|| "+billingCode+" || "+woTaskCode+" || "+taskStatus+"  || "+revenuPostedAmount+" ||");
			
			// currentHashValue=hashTaskMap.get(woTaskCode).trim();
			 boolean isKeyPresent = hashTaskMap.containsKey(woTaskCode); 
			// if (  (currentHashValue!=null) && (!(currentHashValue.equalsIgnoreCase(""))) ) {
			 if (  isKeyPresent) {
				 currentHashValue=hashTaskMap.get(woTaskCode).trim();
				 currentTaskName=currentHashValue.split("::")[0].trim();
				 currentStatus=currentHashValue.split("::")[1].trim();
				 if (currentStatus.equalsIgnoreCase("COMPLETED")) {
					 if (revenuePostedAmount_double>0) {
						 fnpMymsg("|| "+billingCode+" || "+woTaskCode+" || "+taskStatus+"  || "+revenuPostedAmount+" ||");
					}else{
						fnpMymsg("|| "+billingCode+" || "+woTaskCode+" || "+taskStatus+"  || "+revenuPostedAmount+" ||");					
						msg="As this task '"+woTaskCode+"' is already completed, so its revenue posted amount should be greater than 0 but it is coming '"+revenuPostedAmount+"'.";
						fnpMymsg(msg);
						throw new Exception(msg);
						
					}
					
				 }else {
						 if (revenuePostedAmount_double==0) {
							 fnpMymsg("|| "+billingCode+" || "+woTaskCode+" || "+taskStatus+"  || "+revenuPostedAmount+" ||");
							 fnpMymsg("As this task '"+woTaskCode+"' is not completed as its status is '"+taskStatus+"', so its revenue posted amount is ---'"+revenuPostedAmount+"'.");
						}else{
							msg="As this task '"+woTaskCode+"' is not completed as its status is '"+taskStatus+"', so its revenue posted amount should be 0 but it is coming ---'"+revenuPostedAmount+"'.";
							fnpMymsg(msg);
							throw new Exception(msg);
						
					
						}
				 }
				
				} else {
					msg="This taskCode '"+woTaskCode+"' is present in Financial Info section at Financial tab but NOT present at task tab.";
					fnpMymsg(msg);
					throw new Exception(msg);
				}
			
			
			
			
			
		}
		
		fnpMymsg("********************************Revenue Posted Amount Details********************************************************************************************************");
		
	}
	
	





public void fnpRevenuePostedAmount_equalsTo_AdjustedAmount_forSpecificTask(String taskTypeNames) throws Throwable{
	
	HashMap<String, String> hashTaskMap=fnpFetchTaskCode_basedOnTaskTypes( taskTypeNames);
	
	
	
	fnpCommonClickFinancialTab();
	fnpWorkAroundToClickbottomFooter();
	fnpClick_OR("FinancialTab_FinancialInfoTable_triangleIcon_toExpand");
	
	
	int billingCodeColIndex = fnpFindColumnIndex("FinancialTab_FinancialInfo_InnerTable_ForRevenuePostedAmt_table_header", "Billing Code");
	int woTaskColIndex = fnpFindColumnIndex("FinancialTab_FinancialInfo_InnerTable_ForRevenuePostedAmt_table_header", "WO Tasks");
	int taskStatusColIndex = fnpFindColumnIndex("FinancialTab_FinancialInfo_InnerTable_ForRevenuePostedAmt_table_header", "Task Status");
	int RevenuePostedAmountColIndex = fnpFindColumnIndex("FinancialTab_FinancialInfo_InnerTable_ForRevenuePostedAmt_table_header", "Revenue Posted Amt");
	int adjustedAmountColIndex = fnpFindColumnIndex("FinancialTab_FinancialInfo_InnerTable_ForRevenuePostedAmt_table_header", "Adjusted Amt");
	//int scopeValidationrowNo = fnpFindRow("Snapshot_TasksSummaryTable", SnapShot_taskDesc_ScopeValidation, TaskDescColIndex);
	//String scopeValidationAssigner = fnpFetchFromTable("FinancialTab_FinancialInfo_InnerTable_ForRevenuePostedAmt_table", scopeValidationrowNo, assignedToColIndex);
	
	
	int totalRows=fnpCountRowsInTable("FinancialTab_FinancialInfo_InnerTable_ForRevenuePostedAmt_table");
	String billingCode;
	String woTaskCode;
	String taskStatus;
	String revenuPostedAmount;
	String adjustedAmount;
	fnpMymsg("********************************Revenue Posted Amount Details********************************************************************************************************");
	fnpMymsg("|| Billing Code    ||  WO Tasks || Task Status  || Adjusted Amount || Revenue Posted Amount ||");
	String currentHashValue;
	String currentTaskName;
	String currentStatus;
	double revenuePostedAmount_double;
	double adjustedAmount_double;
	
	boolean taskFoundAtFinancialTab=false;
	
	//for (int j = 0; j < taskTypeNamesArray.length; j++) {
	for (Map.Entry<String,String> entry : hashTaskMap.entrySet()){
		fnpMymsg("Key = " + entry.getKey() + ", Value = " + entry.getValue());
		
		for (int i = 1; i < totalRows+1; i++) {
			billingCode = fnpFetchFromTable("FinancialTab_FinancialInfo_InnerTable_ForRevenuePostedAmt_table", i, billingCodeColIndex);
			woTaskCode = fnpFetchFromTable("FinancialTab_FinancialInfo_InnerTable_ForRevenuePostedAmt_table", i, woTaskColIndex);
			taskStatus = fnpFetchFromTable("FinancialTab_FinancialInfo_InnerTable_ForRevenuePostedAmt_table", i, taskStatusColIndex);
			revenuPostedAmount = fnpFetchFromTable("FinancialTab_FinancialInfo_InnerTable_ForRevenuePostedAmt_table", i, RevenuePostedAmountColIndex).trim();
			adjustedAmount = fnpFetchFromTable("FinancialTab_FinancialInfo_InnerTable_ForRevenuePostedAmt_table", i, adjustedAmountColIndex).trim();
			if (revenuPostedAmount.equalsIgnoreCase("")) {
				revenuePostedAmount_double=0;
			}else{
				revenuePostedAmount_double=Double.parseDouble(revenuPostedAmount);
			}
			if (adjustedAmount.equalsIgnoreCase("")) {
				adjustedAmount_double=0;
			}else{
				adjustedAmount_double=Double.parseDouble(adjustedAmount);
			}
			
			
			fnpMymsg("|| "+billingCode+" || "+woTaskCode+" || "+taskStatus+"  || "+adjustedAmount+"   || "+revenuPostedAmount+" ||");
			
			
			
			if (entry.getKey().equalsIgnoreCase(woTaskCode)) {
				taskFoundAtFinancialTab=true;
				if (adjustedAmount_double!=revenuePostedAmount_double) {
					msg="Adjusted amount '"+adjustedAmount_double+"' is NOT equal to Revenue Posted Amount '"+revenuePostedAmount_double+"'. ";
					fnpMymsg(msg);
					throw new Exception(msg);
					
				}else{
					fnpMymsg("Adjusted amount '"+adjustedAmount_double+"' is equal to Revenue Posted Amount '"+revenuePostedAmount_double+"'. ");
				}
				
				break;
				
			}else{
				
/*				if (revenuePostedAmount_double==0) {
					//nothing to do
					
				} else {
					msg="Revenue posted amount should have zero or blank for this task code '"+woTaskCode+"', but it is '"+revenuePostedAmount_double+"' and hence failed.";
					throw new Exception(msg);
				}
				
				*/
				if (hashTaskMap.containsKey(woTaskCode)) {
					//nothing to do
					
				} else {
					if (revenuePostedAmount_double==0) {
						//nothing to do
						
					} else {
						msg="Revenue posted amount should have zero or blank for this task code '"+woTaskCode+"', but it is '"+revenuePostedAmount_double+"' and hence failed.";
						throw new Exception(msg);
					}
				}
				
			}

			
			
			
			
			
		}
		
		if (!(taskFoundAtFinancialTab)) {
			msg="This taskCode '"+entry.getKey()+"' is present at Task tab but it is not present  in Financial Info section at Financial tab.";
			fnpMymsg(msg);
			throw new Exception(msg);
		}
		
		
		
		
	}

	
	fnpMymsg("********************************Revenue Posted Amount Details********************************************************************************************************");
	
}





public void fnpInvoiceAmount_equalsTo_AdjustedAmount_forSpecificTask(String taskTypeNames) throws Throwable{
	
	
	HashMap<String, String> hashTaskMap=fnpFetchTaskCode_basedOnTaskTypes( taskTypeNames);
	
	
	
	fnpCommonClickFinancialTab();
	fnpWorkAroundToClickbottomFooter();
	fnpClick_OR("FinancialTab_FinancialInfoTable_triangleIcon_toExpand");
	
	
	int billingCodeColIndex = fnpFindColumnIndex("FinancialTab_FinancialInfo_InnerTable_ForRevenuePostedAmt_table_header", "Billing Code");
	int woTaskColIndex = fnpFindColumnIndex("FinancialTab_FinancialInfo_InnerTable_ForRevenuePostedAmt_table_header", "WO Tasks");
	int taskStatusColIndex = fnpFindColumnIndex("FinancialTab_FinancialInfo_InnerTable_ForRevenuePostedAmt_table_header", "Task Status");
	int invoiceAmountColIndex = fnpFindColumnIndex("FinancialTab_FinancialInfo_InnerTable_ForRevenuePostedAmt_table_header", "Invoiced Amount");
	int adjustedAmountColIndex = fnpFindColumnIndex("FinancialTab_FinancialInfo_InnerTable_ForRevenuePostedAmt_table_header", "Adjusted Amt");
	//int scopeValidationrowNo = fnpFindRow("Snapshot_TasksSummaryTable", SnapShot_taskDesc_ScopeValidation, TaskDescColIndex);
	//String scopeValidationAssigner = fnpFetchFromTable("FinancialTab_FinancialInfo_InnerTable_ForRevenuePostedAmt_table", scopeValidationrowNo, assignedToColIndex);
	
	
	int totalRows=fnpCountRowsInTable("FinancialTab_FinancialInfo_InnerTable_ForRevenuePostedAmt_table");
	String billingCode;
	String woTaskCode;
	String taskStatus;
	String invoiceAmount;
	String adjustedAmount;
	fnpMymsg("********************************Invoice and Adjusted Amount Details********************************************************************************************************");
	fnpMymsg("|| Billing Code    ||  WO Tasks || Task Status  || Adjusted Amount || Invoice Amount ||");
	String currentHashValue;
	String currentTaskName;
	String currentStatus;
	double invoiceAmount_double;
	double adjustedAmount_double;
	
	boolean taskFoundAtFinancialTab=false;
	
	//for (int j = 0; j < taskTypeNamesArray.length; j++) {
	for (Map.Entry<String,String> entry : hashTaskMap.entrySet()){
		fnpMymsg("Key = " + entry.getKey() + ", Value = " + entry.getValue());
		
		for (int i = 1; i < totalRows+1; i++) {
			billingCode = fnpFetchFromTable("FinancialTab_FinancialInfo_InnerTable_ForRevenuePostedAmt_table", i, billingCodeColIndex);
			woTaskCode = fnpFetchFromTable("FinancialTab_FinancialInfo_InnerTable_ForRevenuePostedAmt_table", i, woTaskColIndex);
			taskStatus = fnpFetchFromTable("FinancialTab_FinancialInfo_InnerTable_ForRevenuePostedAmt_table", i, taskStatusColIndex);
			invoiceAmount = fnpFetchFromTable("FinancialTab_FinancialInfo_InnerTable_ForRevenuePostedAmt_table", i, invoiceAmountColIndex).trim();
			adjustedAmount = fnpFetchFromTable("FinancialTab_FinancialInfo_InnerTable_ForRevenuePostedAmt_table", i, adjustedAmountColIndex).trim();
			if (invoiceAmount.equalsIgnoreCase("")) {
				invoiceAmount_double=0;
			}else{
				invoiceAmount_double=Double.parseDouble(invoiceAmount);
			}
			if (adjustedAmount.equalsIgnoreCase("")) {
				adjustedAmount_double=0;
			}else{
				adjustedAmount_double=Double.parseDouble(adjustedAmount);
			}
			
			
			fnpMymsg("|| "+billingCode+" || "+woTaskCode+" || "+taskStatus+"  || "+adjustedAmount+"   || "+invoiceAmount+" ||");
			
			
			
			if (entry.getKey().equalsIgnoreCase(woTaskCode)) {
				taskFoundAtFinancialTab=true;
				if (adjustedAmount_double!=invoiceAmount_double) {
					msg="Adjusted amount '"+adjustedAmount_double+"' is NOT equal to Invoiced Amount '"+invoiceAmount_double+"'. ";
					fnpMymsg(msg);
					throw new Exception(msg);
					
				}else{
					fnpMymsg("Adjusted amount '"+adjustedAmount_double+"' is equal to Invoiced Amount '"+invoiceAmount_double+"'. ");
				}
				
				break;
				
			}

			
			
			
			
			
		}
		
		if (!(taskFoundAtFinancialTab)) {
			msg="This taskCode '"+entry.getKey()+"' is present at Task tab but it is not present  in Financial Info section at Financial tab.";
			fnpMymsg(msg);
			throw new Exception(msg);
		}
		
		
		
		
	}

	
	fnpMymsg("********************************Adjusted and Invoiced Amount Details********************************************************************************************************");
	
}




public HashMap fnpFetchTaskCode_basedOnTaskTypes(String taskTypeNames) throws Throwable{
	
	fnpCommonClickTaskTab();
	
	String[] taskTypeNamesArray=taskTypeNames.split(",");
	
	
	
	
	//creating a hashMap
	int totalRows_taskTab=fnpCountRowsInTable("TasksTable_EditWO");
	int taskNo_ColIndex_taskTab = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskNoColName);
	int tasktype_ColIndex_taskTab = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskTypeColName);
	int taskStatusColIndex_taskTab = fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_StatusColName);
	
	HashMap<String, String> hashTaskMap = new HashMap <String, String>();
	//HashMap<String, String> hashTaskMap2 = new HashMap <String, String>();
	String hashkey;
	String hashValue;

	String taskNo_taskTab;
	String taskType_taskTab;
	String taskStatus_taskTab;
	
	for (int j = 0; j < taskTypeNamesArray.length; j++) {
		for (int i = 1; i < totalRows_taskTab+1; i++) {
			taskNo_taskTab=fnpFetchFromTable("TasksTable_EditWO", i, taskNo_ColIndex_taskTab);
			taskType_taskTab=fnpFetchFromTable("TasksTable_EditWO", i, tasktype_ColIndex_taskTab);
			taskStatus_taskTab=fnpFetchFromTable("TasksTable_EditWO", i, taskStatusColIndex_taskTab);
			
			if (taskTypeNamesArray[j].equalsIgnoreCase(taskType_taskTab)) {
				hashkey = taskNo_taskTab;
				hashValue = taskType_taskTab+"::"+taskStatus_taskTab;
				if (!hashkey.isEmpty()) {
					hashTaskMap.put(hashkey, hashValue);
				}
				break;
			}
			




		}
		
	}
	
	return hashTaskMap;
	

}


}
