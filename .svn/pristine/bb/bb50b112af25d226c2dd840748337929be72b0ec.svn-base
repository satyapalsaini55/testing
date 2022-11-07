package nsf.ecap.Invoice_Function_suite;

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
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import nsf.ecap.Proposals_suite.TestSuiteBase_Proposal;
import nsf.ecap.Test_Plan_suite.TestSuiteBase_Test_Plan_suite;
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

public class TestSuiteBase_Invoice_Function_suite extends TestSuiteBase {

	
	public static String runmodes[] = null;
	public static int count = -1;
	


	// check if the suite has to be skipped or not
	@BeforeSuite
	public void checkSuiteSkip() throws Throwable {

		fnInitialize();


		setCurrentSuiteName(Invoice_suite_Name);
		currentSuiteCode = "Invoice_Function";
		//currentSuiteXls = Dietary_Supplement_suitexls;
		setCurrentSuiteExcel(Invoice_Function_suitexls);
		
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
		
		
		 //fnpDeleteSMTPMessages();

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
	 
	 
	 
	 
	 
	 public void allocateExpense() throws Throwable{
			
			int rowInBothTaskSectionTable_beforeClickingOnAutoAllocateByDaysBtn=fnpCountRowsInTable("TaskSectionTable");
			int rowInConcurExpenseTable=fnpCountRowsInTable("ConcurExpensesTable");
			
			//int totalTables = fnpFindNoOfElementsWithThisXpath("TaskSectionTable");
			List<WebElement> totalTasksTables = driver.findElements(By.xpath(OR.getProperty("TaskSectionTable")));
			
			/***********Checks if already allocated *****************************/
			
			
			int concurId_Col_Index_InConcurExpenseTable =fnpFindColumnIndex2("ConcurExpensesTableHeader", "Concur id");
			int idColIndex_InTaskSectionTable= fnpFindColumnIndex_NOR_TraversingFromVariousNodes(OR.getProperty("TaskSectionTableHeader"),  "ID");

			String concurId_InConcurExpenseTable;
			String id_InTaskSectionTable;
			boolean alreadyAllocated = false;
			WebElement currentTaskTable;
			int rowInCurrentTaskTable;
			//int rowInConcurExpenseTable;
			for(int j=1; j <=rowInConcurExpenseTable ;j++ ){	
				alreadyAllocated=false;
				concurId_InConcurExpenseTable = fnpFetchFromTable("ConcurExpensesTable", j, concurId_Col_Index_InConcurExpenseTable);

				
				for(int t=0;t<totalTasksTables.size();t++){
					
					currentTaskTable=totalTasksTables.get(t);
					rowInCurrentTaskTable=currentTaskTable.findElements(By.xpath("./tr")).size();
					for(int m=1; m <= rowInCurrentTaskTable ;m++ ){							
						//id_InTaskSectionTable = fnpFetchFromTable("TaskSectionTable", (m), idColIndex_InTaskSectionTable);
						//id_InTaskSectionTable=fnpFetchFromTable_NOR_TraversingFromVariousNodes(OR.getProperty("TaskSectionTable"), (m), idColIndex_InTaskSectionTable);
													
						id_InTaskSectionTable=currentTaskTable.findElement(By.xpath("./tr[" + m + "]/td[" + idColIndex_InTaskSectionTable + "]")).getText().trim();
						
						if(concurId_InConcurExpenseTable.equalsIgnoreCase(id_InTaskSectionTable)){
							alreadyAllocated=true;
							fnpMymsg("This Concur Expenses's Concur Id '"+concurId_InConcurExpenseTable+"' is present in Task Section Table no. '"+t+"' in  row no. --'"+m+"'.");
							break;
						}
					}
					
					
					if ((alreadyAllocated)) {
						break;
					}
				}
				

					
/*					if ((alreadyAllocated)) {
					msg="This Concur Expenses's Concur Id '"+concurId_InConcurExpenseTable+"' is present in Task Section Table, so we are not going to click on AutoAllocateByDays button again";
					fnpMymsg(msg);
					throw new Exception(msg);
					
				} 
				*/
				
				
				if ((alreadyAllocated)) {
					break;
				}
				

			}
			
			
			
/**************************************************************************/
			
			
			if (!alreadyAllocated) {
				fnpMymsg("Before clicking on 'Auto Allocation by Days' button, no. of rows in Task sections are --"+rowInBothTaskSectionTable_beforeClickingOnAutoAllocateByDaysBtn);
				
				int remainingAmcount_Col_Index =fnpFindColumnIndex2("ConcurExpensesTableHeader", "Remaining Amount");
				double remainingAmount;
				String reminingAmountString;
				for (int i = 1; i <= rowInConcurExpenseTable; i++) {
					 reminingAmountString = fnpFetchFromTable("ConcurExpensesTable", i, remainingAmcount_Col_Index);
					 if (reminingAmountString.trim().equalsIgnoreCase("")) {
						 msg="Remaining Amount is blank (not present) in the Concur Expenses table, hence failed.";
						 fnpMymsg(msg);
						 throw new Exception(msg);
						
					} else {
						reminingAmountString=reminingAmountString.replaceAll(",", "");
						remainingAmount=Double.parseDouble(reminingAmountString);
						if (remainingAmount>0) {
							fnpMymsg("Remaining Amount in this '"+i+"' row is --"+remainingAmount);
							
						} else {
							msg="Remaining Amount is zero (0) in  this '"+i+"' row, hence failed.";
							fnpMymsg(msg);
							throw new Exception(msg);
						}
						
					}
					
				}
				
									
				
				/*************Click on Auto Allocate by Days.Verify that remaining amount is zero and all the expenses should get listed under the Tasks section to the left.*************/
				

				fnpClick_OR("AutoAllocateByDays_btn");
				fnpLoading_wait();
				
				int rowInTaskSectionTable_AfterClickingOnAutoAllocateByDaysBtn=fnpCountRowsInTable("TaskSectionTable");
				
				int expectedRowsInTaskSection=rowInBothTaskSectionTable_beforeClickingOnAutoAllocateByDaysBtn+rowInConcurExpenseTable;
				if (expectedRowsInTaskSection==rowInTaskSectionTable_AfterClickingOnAutoAllocateByDaysBtn) {
					fnpMymsg("Concur Expenses rows have been added to Task Section table successfully.");
					
				}else{
					msg="Concur Expenses rows have NOT been added to Task Section table successfully becasue before clicking on 'Auto Allocate By Days' button, no. of rows in Task section were '"+rowInBothTaskSectionTable_beforeClickingOnAutoAllocateByDaysBtn
							+"' and no. of rows in Concur Expenses were '"+rowInConcurExpenseTable+"', hence total rows should be total of both i.e. '"+expectedRowsInTaskSection+"' but actually it shows '"+rowInTaskSectionTable_AfterClickingOnAutoAllocateByDaysBtn+"'.";
					fnpMymsg(msg);
					throw new Exception(msg);
				}
				
				
				
				
				rowInConcurExpenseTable=fnpCountRowsInTable("ConcurExpensesTable");
				if (rowInConcurExpenseTable>0) {
					fnpMymsg("Concur Expenses have '"+rowInConcurExpenseTable+"' rows, afer clicking on  'Auto Allocation by Days' button.");
					
				} else {
					msg="Concur Expenses have 0 (zero) rows, afer clicking on  'Auto Allocation by Days' button, hence failed.";
					fnpMymsg(msg);
					throw new Exception(msg);
				}
				for (int i = 1; i <= rowInConcurExpenseTable; i++) {
					 reminingAmountString = fnpFetchFromTable("ConcurExpensesTable", i, remainingAmcount_Col_Index);
					 
					 if (reminingAmountString.trim().equalsIgnoreCase("")) {
						 msg="Remaining Amount is blank (not present) in the Concur Expenses table, afer clicking on  'Auto Allocation by Days' button, hence failed.";
						 fnpMymsg(msg);
						 throw new Exception(msg);
						
					} else {
						reminingAmountString=reminingAmountString.replaceAll(",", "");
						remainingAmount=Double.parseDouble(reminingAmountString);
						if (!(remainingAmount>0) ){
							fnpMymsg("Remaining Amount in this '"+i+"' row is --'"+remainingAmount +"' , afer clicking on  'Auto Allocation by Days' button");
							
						} else {
							msg="Remaining Amount should be zero (0) in  this '"+i+"' row , afer clicking on  'Auto Allocation by Days' button but it is '"+remainingAmount+"', hence failed.";
							fnpMymsg(msg);
							throw new Exception(msg);
						}
						
					}
					
				}
				
				
				/****************************************************************************************************************/
				
			}else{
				fnpMymsg("As concur id of Concur Expenses table is already present in Task Section table, so not going to click 'Auto Allocate By Days' button, and going to click 'Allocation Completed' button i.e. next step.");
			}
		/**************************************************************************/

	 }
	
	 public void allocation_complete() throws Throwable{
			fnpClick_OR("AllocationCompleted_btn");
			
			fnpClick_OR("AllocationCompleted_Confirmation_Yes_btn");
			
			try{
				
				fnpWaitForVisibleHavingMultipleElements("TopMessage3");
			}catch(Throwable t){
				msg="Success message after clicking on 'Allocation Completed' button for 'Generate Invoice' is not coming.";
				fnpMymsg(msg);
				throw new Exception(msg);
			}


			fnpMymsg("Top Message after clicking on 'Allocation Completed' button ----" + fnpGetText_OR("TopMessage3"));

			String SuccessfulMsg = fnpGetText_OR("TopMessage3");

			//Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"), "Top message does not contain 'success' word, so might be Allocation is not Completed successfully");

			
			if (SuccessfulMsg.equalsIgnoreCase(successMessageOnAllocationCompleted)) {
				fnpMymsg("Confirmation message is correct after clicking 'Allocation Completed'.");
				
			} else {
				msg="Confirmation message is NOT correct after clicking 'Allocation Completed' because actual is this '"+SuccessfulMsg+
						"' and expected is this '"+successMessageOnAllocationCompleted+"'.";
				fnpMymsg(msg);
				throw new Exception(msg);				
			}
		
			
			
			
			
			
			fnpMymsg("Allocation is completed successfully as success message is getting flashed.");
			
			
			TestSuiteBase_Test_Plan_suite.fnpCheckField_IsDisable("OR","AllocationCompleted_btn","AllocationCompleted_btn",true);
			TestSuiteBase_Test_Plan_suite.fnpCheckField_IsDisable("OR","AutoAllocateByDays_btn","AutoAllocateByDays_btn",true);
			TestSuiteBase_Test_Plan_suite.fnpCheckField_IsDisable("OR","AllocateSelected_btn","AllocateSelected_btn",true);

	 }
	 
	 
	 public boolean fnaFindDuplicateValueInArrayList(ArrayList<String> list) throws Exception {
		 
		 boolean duplicateFound = false;
		 
		 try {	
			 
			 String[] str = list.toArray(new String[list.size()]);
			
			 for(int i=0; i< str.length-1; i++) {
					for(int j=i+1; j<str.length; j++) {
						if(str[i].equals(str[j])) {
							duplicateFound = true;
							fnpMymsg("Duplicate billCode is :" + str[i]);
						}
					}
				}
				
				if (!duplicateFound) {
					fnpMymsg("No duplicate elements found.");
		        }
			
			return duplicateFound;
		 
	 }catch(Throwable t){
		 throw new Exception(t);
	 }
		
}
	 
}
























