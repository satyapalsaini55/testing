package nsf.ecap.Invoice_Function_suite;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import nsf.ecap.Health_Science_suite.TestSuiteBase_HealthScience;
import nsf.ecap.Work_Order_suite.NewNew_Draft_InReview_No_Fac;
import nsf.ecap.util.TestDataProvider;
import nsf.ecap.util.TestUtil;

public class Create_Invoice  extends TestSuiteBase_Invoice_Function_suite{
	
	ArrayList<String> BillCodeList;
	ArrayList<String> taskList;
	HashSet<String> taskSet;
	
	String LineNoValue="";
	String Billcodevalue="";
	String taskNoValue="";
	String taskNo="";
	
	
	@BeforeTest
	public void checkTestSkip() throws Throwable {
		count = -1;
		BS01 = new NewNew_Draft_InReview_No_Fac();
		BS01.checkTestSkip(this.getClass().getSimpleName());
		
		runmodes = TestUtil.getDataSetRunmodes(currentSuiteXls, this.getClass().getSimpleName());
		fail = false;
		isTestPass = true;
		
		start = new Date();

	}
	
	
	
	
	//@Test(enabled = false)
	//@Test(priority = 1)
	//public void Create_InvoiceFlow() throws Throwable {
	@Test(dataProviderClass = TestDataProvider.class,dataProvider = "CreateInvoiceDataProvider")
	public void Create_InvoiceFlow(
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
		
		
		
		
		
		int iwhileCount=1;
		int maxTry=10;
		boolean tryagain=false;
		while(iwhileCount<=maxTry){
			
		
				try{
					
					if (iwhileCount==1) {
						fnpLaunchBrowserAndLogin();
					}else{
						
						fnpClickTopHomeMenu();
					}
				
					
					fnpMymsg("*************  "+ iwhileCount +" try ************************");
						fnpClick_OR("Alert_For_OrgUnit_label");
					//	fnpPFList("Alert_For_SelectOrgUnit_List", "Alert_For_SelectOrgUnit_ListOptions", (String) hashXlData.get("Org_name"));
						fnpPFList("Alert_For_SelectOrgUnit_List", "Alert_For_SelectOrgUnit_ListOptions", (String) table.get("Org_Name"));
						fnpClick_OR("ShowAlertsBtn");
						
						
						
						//TestSuiteBase_HealthScience.fnpCommonAlertClickedFirstRecord_in_specifiedColumnName_withoutSearching_But_AfterClickingAscendingIcon_BasedOnAlertNameContains(table, (String) table.get("Alert_Name"),  "GO TO INVOICE","Scheduled Audit End Date");
						maxTry=TestSuiteBase_HealthScience.fnpCommonAlertClickedRecord_in_specifiedRow_specifiedColumnName_withoutSearching_BasedOnAlertNameContains(table, (String) table.get("Alert_Name"), iwhileCount, "GO TO INVOICE");
						
						fnpWaitForVisible("InvoiceTemplatesTable");
						fnpWaitForVisible("invoice_Tasks_TableTable");
						
						int invoice_Template_Table_rows_count = fnpCountRowsInTable("InvoiceTemplatesTable");						
						int invoice_Tasks_Table_rows_count = fnpCountRowsInTable("invoice_Tasks_TableTable");
						fnpMymsg("Invoice template row count is --'"+invoice_Template_Table_rows_count+" and invoice Tasks table row count is --"+invoice_Tasks_Table_rows_count+". ");
					//	if ((invoice_Template_Table_rows_count==0)  & (invoice_Tasks_Table_rows_count==0) ){
						if ((invoice_Template_Table_rows_count==0)  || (invoice_Tasks_Table_rows_count==0) ){
							String expectedmessagecontains="Both Invoice Templates and Tasks are required to proceed. If you don't find any of these please contact IT Support for more help.";
							//String expectedmessagecontains="Both Invoice Templates and Tasks are required to proceed.";
							String message1_xpath=".//td/span[contains(text(),\""+expectedmessagecontains+"\")]";

							if (fnpCheckElementDisplayedImmediately_NOR(message1_xpath)) {
								// go to next record
								
								if (iwhileCount>(maxTry-1)) {
									msg="Script has tried "+maxTry+" times but failed all time may be due to same or different reasons. I was told to run max.  "+maxTry+" times this script.";
									fnpMymsg(msg);
									throw new Exception(msg);
									
								}else{
									msg="Either Invoice Tasks Table or Invoice Templates table  is coming blank or may be both ---chance --"+maxTry;
									fnpMymsg(msg);
									tryagain=true;
									
								}
								
								
								
							} else {
								msg="This expected message '"+expectedmessagecontains+"' is not present even when there is no records in invoice template table and in invoice task table.";
								fnpMymsg(msg);
								throw new Exception(msg);
							}
							
						} else {
							fnpClick_OR("SelectAll_InvoiceTask_chkbx");
						//	fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
							//List<WebElement> radioList = driver.findElements(By.xpath(OR.getProperty("SelectRadiobtns_InvoiceTemplates")));
							if (fnpCheckElementDisplayedImmediately_NOR(OR.getProperty("SelectRadiobtns_InvoiceTemplates"))) {
								List<WebElement> radioList = driver.findElements(By.xpath(OR.getProperty("SelectRadiobtns_InvoiceTemplates")));
								if (radioList.size() >0) {
									fnpMymsg("Going to click first radio button of Invoice Template.");
									radioList.get(0).click();// only clicking first radio button
									Thread.sleep(1000);
								}
							}
							
							//clicking all radio buttons
			/*				for (WebElement webElement : radioList) {
								webElement.click();
								Thread.sleep(1000);
								fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
								
							}
							*/
							


						//	fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
							
							
							fnpClick_OR("Next_btn_On_CreateInvoice_screen");
							
					
					int LineNoColIndex = fnpFindColumnIndex("InvoicingTaskTableHeader", "Line #");
					int BillCodeColIndex = fnpFindColumnIndex("InvoicingTaskTableHeader", "Bill Code");
					
							
					if(fnpCheckElementDisplayedImmediately("InvoicingTaskTable")) {
								
							int invoicing_tasks_count = fnpCountRowsInTable("InvoicingTaskTable");
							if (invoicing_tasks_count>0) {
								tryagain=false;
								
								for(int i=1; i<=2; i++) {
									
								String LineNoBillcodeCompleteString=(String) table.get("LineNo_BillCode").trim();
									
								String LineNoBillcodeSet = LineNoBillcodeCompleteString.split("::")[i-1];
									
								String Line_No = LineNoBillcodeSet.split(":")[0];
								String Bill_Code = LineNoBillcodeSet.split(":")[1];
									
								 LineNoValue = fnpFetchFromTable("InvoicingTaskTable", i, LineNoColIndex);
								 Billcodevalue = fnpFetchFromTable("InvoicingTaskTable", i, BillCodeColIndex);	
								 
								try{
									assert (LineNoValue.equals(Line_No)): "Line Value is not equal to given value.";
									fnpMymsg("PASSED == LineNo '"+ LineNoValue +"' is matched with given value.'"+ Line_No +"' for row '"+ i +"'" );
									
									assert (Billcodevalue.equals(Bill_Code)): "Bill Code Value is not equal to given value.";
									fnpMymsg("PASSED == Bill code '"+ Billcodevalue +"' is matched with given value.'"+ Bill_Code +"' for row '"+ i +"' " );
									
								}catch(Throwable t){
									fnpDesireScreenshot("Values_Not_Match");
									throw new Exception("FAILED == Line No and Bill code values are NOT matched, please refer the screen shot >> Values_Not_Match");
								}
								
							}
								//fnpMymsg(msg);
								//break;
							}else{
								fnpMove_To_Element_and_DoubleClick_NOR(OR.getProperty("InvoicingTaskTable"));
								fnpDesireScreenshot("InvoicingTaskTable-"+iwhileCount+"_");
								if (iwhileCount>(maxTry-1)) {
									msg="Script has tried "+maxTry+" times but Invoicing Task Table is coming blank this time ---"+iwhileCount;
									fnpMymsg(msg);
									throw new Exception(msg);
									
								}else{
									msg="Invoicing Task Table is coming blank ---chance --"+maxTry;
									fnpMymsg(msg);
									tryagain=true;
									
								}
							}
						}else {
							fnpDesireScreenshot("InvoicingTaskTableNotPresent");
							throw new Exception("InvoicingTaskTable- is not showing on the screen.");
						}
					
					int taskNoColIndex = fnpFindColumnIndex("InvoicingTaskTableHeader", "Task #");
					int invoicing_tasks_count = fnpCountRowsInTable("InvoicingTaskTable");
					
					fnpMymsg("   Task #  ||  Line #  || Bill Code  ");
					fnpMymsg("========================================================");
					
					taskList = new ArrayList<String>();
					BillCodeList = new ArrayList<String>();
					taskSet = new HashSet<String>();

					for(int i=3; i<=invoicing_tasks_count; i++) {
	
					 taskNoValue = fnpFetchFromTable("InvoicingTaskTable", i, taskNoColIndex);
					 LineNoValue = fnpFetchFromTable("InvoicingTaskTable", i, LineNoColIndex);
					 Billcodevalue = fnpFetchFromTable("InvoicingTaskTable", i, BillCodeColIndex);
					 
					 if(StringUtils.isNotBlank(taskNoValue)){
					  taskList.add(taskNoValue);
					  taskSet.add(taskNoValue);
					 }
					 
					 
					 if(!(Billcodevalue.equalsIgnoreCase("0000-Zero Dollar"))) {
						 BillCodeList.add(Billcodevalue);
						 
						 fnpMymsg("  "+	taskNoValue	+"  ||  "+LineNoValue	+"  ||  "+	Billcodevalue	);
					 }
					
				}
					
					
					boolean duplicateValues = fnaFindDuplicateValueInArrayList(BillCodeList);
					
					if(duplicateValues) {
						fnpMymsg("Duplicates BillCodes are displaying in the task tab.");
					}
					
					int taskSize = taskList.size();
					int BillCodeSize = BillCodeList.size();
					int allTaskNo = taskSet.size();
							
							
					if(taskSize==BillCodeSize) {
						fnpMymsg("Task size  " + taskSize + "  and Billcode size  " + BillCodeSize + "   both are same.");
					}else {
						throw new Exception("Either Billcode/Task is not displaying in the Invoicing Tasks row.");
					}
					
					
					
					
				 }						

						//fnpClickTopHomeMenu();
						//fnpMymsg("************************************************");
						
			//	}
				
				
						if (tryagain) {
							throw new Exception(msg);
							
						} else {
							List<WebElement> radiobtnList = driver.findElements(By.xpath(OR.getProperty("BillTo_AllRadioBtn_At_GenerateInvoice_screen")));
							
							if(!(radiobtnList.get(0).getAttribute("class").contains("ui-state-disabled"))) {
								fnpClick_OR("BillTo_firstRadioBtn_At_GenerateInvoice_screen");
							}else {
								fnpMymsg("Going to click second radio button of Generate Invoice screen");
								radiobtnList.get(1).click();
								Thread.sleep(1000);
							}
							
						//	if (fnpCheckElementDisplayedImmediately("GenerateInvoiceScreen_PaymentMethod_PFList")) {
							if (fnpCheckElementDisplayedImmediately("GenerateInvoiceScreen_InvoiceLocalFields_SaveBtn")) {
								
/*								fnpPFList("GenerateInvoiceScreen_PaymentMethod_PFList", "GenerateInvoiceScreen_PaymentMethod_PFListOptions", (String) table.get("Payment_Method"));
								fnpPFList("GenerateInvoiceScreen_USO_GFDI_PFList", "GenerateInvoiceScreen_USO_GFDI_PFListOptions", (String) table.get("USO_GFDI"));
								fnpPFList("GenerateInvoiceScreen_Payment_Type_PFList", "GenerateInvoiceScreen_Payment_Type_PFListOptions", (String) table.get("Payment_Type"));
								fnpPFList("GenerateInvoiceScreen_Sat_Service_Code_PFList", "GenerateInvoiceScreen_Sat_Service_Code_PFListOptions", (String) table.get("Sat_Service_Code"));
								*/
								
/*								fnpPFListUsingFieldName("Payment Method",(String) table.get("Payment_Method")) ;
								fnpPFListUsingFieldName("Uso GFDI",(String) table.get("USO_GFDI")) ;
								fnpPFListUsingFieldName("Payment Type",(String) table.get("Payment_Type")) ;
								fnpPFListUsingFieldName("SAT Service Code",(String) table.get("Sat_Service_Code")) ;
								
								*/
								
								fnpPFListUsingFieldName("Payment Method",1) ;
								fnpPFListUsingFieldName("Uso GFDI",1) ;
								fnpPFListUsingFieldName("Payment Type",1) ;
								fnpPFListUsingFieldName("SAT Service Code",1) ;
								
								
								
								//fnpPFListByLiNo(XpathKey, XpathOptionsKey, liNo);
								
								fnpClick_OR("GenerateInvoiceScreen_InvoiceLocalFields_SaveBtn");
								
									
							}
							
							fnpClick_OR("GenerateInvoice_btn_GenerateInvoice_screen");
							
							try{
							
								fnpWaitForVisibleHavingMultipleElements("TopMessage3");
							}catch(Throwable t){
								msg="Success message after clicking on 'Generate Invoice' button for Creating Invoice is not coming.";
								fnpMymsg(msg);
								throw new Exception(msg);
							}


							fnpMymsg("Top Message after clicking on Generate Invoice button ----" + fnpGetText_OR("TopMessage3"));

							String SuccessfulMsg = fnpGetText_OR("TopMessage3");

							Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"), "Top message does not contain 'success' word, so might be Invoice is not getting generated successfully");

							fnpMymsg("Invoice is generated successfully as success message is getting flashed.");
							
							Thread.sleep(1000);
							String taskno = "";
							for(String taskSet_String : taskSet) {
							taskno = taskSet_String;
							
							fnpCommonClickTopMainMenu("Menu", "FirstRefrenceLinkJustBelowToMenu", "SearchTaskLink", "TaskTypeMultipleSelectDropDown");
							
							
							fnpType("OR", "TaskNoField", taskno); 
							
							fnpClick_OR("MainSearchButton");
							
							String s = fnpFetchFromStSearchTable(1, 1);
							int j = 0;
							
							while (s.contains("No records found") && j < 15) {
								j++;
								Thread.sleep(1000);
								s = fnpFetchFromStSearchTable(1, 1);
							}
							
							if (s.contains("No records found")) {
								msg="After selecting search criteria and clicking search button, table is coming blank.";
								fnpMymsg(msg);
								throw new Exception (msg);			
							} else {
								int taskNoCol = fnpFindColumnIndex("StandardSearchTableHeader", "Task No");
								s = fnpFetchFromStSearchTable(1, taskNoCol);
							}
					
							fnpMymsg("First hyperlink Task no. is --"+s);
							fnpClickALinkInATable(s);
					
							fnpLoading_wait();
							fnpIpulseDuringLoading();
							
							fnpClick_OR("TaskFinancialTabLink");
							
							int BillingCodeColIndex_Exp = fnpFindColumnIndex("TaskFinancialTabExpensestableHeader", "Billing Code");
							int AlreadyInvoicedFlagColIndex_Exp = fnpFindColumnIndex("TaskFinancialTabExpensestableHeader", "Already Invoiced?");
							
							int BillingCodeColIndex_Fees = fnpFindColumnIndex("TaskFinancialTabFeestableHeader", "Billing Code");
							int AlreadyInvoicedFlagColIndex_Fees = fnpFindColumnIndex("TaskFinancialTabFeestableHeader", "Already Invoiced?");
							
							
							String BillingCodeValue_Exp = "";
							String AlreadyInvoicedFlagValue_Exp = "";
							
							String BillingCodeValue_Fees = "";
							String AlreadyInvoicedFlagValue_Fees = "";

							
						for(int i=1; i<=BillCodeList.size(); i++) {

									
								List<WebElement> expTablelist = fnpGetORObject("TaskFinancialTabExpensestable").findElements(By.tagName("tr"));
									
								for(int a=1; a<=expTablelist.size(); a++) {
									
									BillingCodeValue_Exp = fnpFetchFromTable("TaskFinancialTabExpensestable", a, BillingCodeColIndex_Exp);
									
										if(BillingCodeValue_Exp.contains("No records found")) {
											System.out.println("Bill Codes are not available under Expense table.");
											break;
										}else { 
											AlreadyInvoicedFlagValue_Exp = fnpFetchFromTable("TaskFinancialTabExpensestable", a, AlreadyInvoicedFlagColIndex_Exp);
											}
										
										
										if(BillCodeList.contains(BillingCodeValue_Exp)){
											
											//fnpMymsg(+ a + "  Bill Code present '"+ BillingCodeValue_Exp +"' in Expenses table and  Already Invoiced Flag:  '" + AlreadyInvoicedFlagValue_Exp + "'");
												
												assert AlreadyInvoicedFlagValue_Exp.equalsIgnoreCase("Y"): "Failed == 'Already Invoiced flag' is not Y for BillCode '"+ BillingCodeValue_Exp +"'";
												//fnpMymsg("Already Invoiced Flag:  '" + AlreadyInvoicedFlagValue_Exp + "' in Expenses table. ");
												fnpMymsg( a + "  Bill Code present '"+ BillingCodeValue_Exp +"' in Expenses table and  Already Invoiced Flag:  '" + AlreadyInvoicedFlagValue_Exp + "'");
											
								}
							
							}
								
								List<WebElement> feeTablelist = fnpGetORObject("TaskFinancialTabFeestable").findElements(By.tagName("tr"));
								
								for(int b=1; b<=feeTablelist.size(); b++) {
									
									BillingCodeValue_Fees = fnpFetchFromTable("TaskFinancialTabFeestable", b, BillingCodeColIndex_Fees);
									
									if(BillingCodeValue_Fees.contains("No records found")) {
										System.out.println("Bill Codes are not available under Expense table.");
										break;
									  }else { 
										AlreadyInvoicedFlagValue_Fees = fnpFetchFromTable("TaskFinancialTabFeestable", b, AlreadyInvoicedFlagColIndex_Fees);
									   }
									
										if(BillCodeList.contains(BillingCodeValue_Fees)) {
										
										//fnpMymsg(+b + "  Bill Code present '"+ BillingCodeValue_Fees +"' in Fees table");
	
										assert AlreadyInvoicedFlagValue_Fees.equalsIgnoreCase("Y"): "Failed == 'Already Invoiced flag' is not Y for BillCode '"+ BillingCodeValue_Fees +"'";
										//fnpMymsg("Already Invoiced Flag:  " + AlreadyInvoicedFlagValue_Fees + " in Fees table. ");
										
										fnpMymsg( b + "  Bill Code present '"+ BillingCodeValue_Fees +"' in Fees table and  Already Invoiced Flag:  '" + AlreadyInvoicedFlagValue_Fees + "'");
									}
							
								}
	

									
							/*		
							 * String BillingCodeValue_Fees_Xpath = OR.getProperty("TaskFinancialTabFeestable")+"/tr["+i+"]/td["+BillingCodeColIndex_Fees+"]";
									if(driver.findElements(By.xpath(BillingCodeValue_Fees_Xpath)).size()>0) {
										BillingCodeValue_Fees = driver.findElement(By.xpath(BillingCodeValue_Fees_Xpath)).getText().trim();
										if(BillingCodeValue_Fees.contains("No records found")) {
											System.out.println("Bill Codes are not available under Expense table.");
										}else { 
											AlreadyInvoicedFlagValue_Fees = fnpFetchFromTable("TaskFinancialTabFeestable", i, AlreadyInvoicedFlagColIndex_Fees);
										}
									}
								*/	
	
									
								}
						
						fnpMymsg("Task No. '"+ taskno +"'   Billcodes verification completed.");
							
							}
						
							break;
						}

				
				
				}catch (Throwable t) {
					if (iwhileCount<(maxTry)) {
						iwhileCount++;
						//fnpCommonFinalCatchBlock(t, "  Exp_AllocationFlow flow  is fail . ", "Exp_AllocationFlowFail");
						fnpDesireScreenshot(" Create_InvoiceFlowFail");
						fnpDesireScreenshot_old(" Create_InvoiceFlowFail"+"usingOldMethod");
						//keep in loop , do all steps again
					}else{
						fnpCommonFinalCatchBlock(t, "  Create_InvoiceFlow flow  is fail . ", "Create_InvoiceFlowFail");
						//IsBrowserPresentAlready = false; // This is for special reason if one case is fail due to application error or any other reason then it close the browser and login again.
						break;
					}

			}
	}

}
				

	@AfterMethod
	public void reportDataSetResult() throws Throwable {
		
		
		fnpCommonCloseBrowsersAndKillProcess();
		
		if (skip)
			TestUtil.reportDataSetResult(currentSuiteXls, this.getClass().getSimpleName(), count + 2, "SKIP");
		else if (fail) {
			isTestPass = false;
			TestUtil.reportDataSetResult(currentSuiteXls, this.getClass().getSimpleName(), count + 2, "FAIL");
		} else
			TestUtil.reportDataSetResult(currentSuiteXls, this.getClass().getSimpleName(), count + 2, "PASS");

		skip = false;
		fail = false;

	}


	
	

	@AfterTest
	public void reportTestResult() throws Throwable {
		int rowNum = currentSuiteXls.getCellRowNum("Test Cases", "TCID", this.getClass().getSimpleName());
		currentSuiteXls.setCellData("Test Cases", "Last_Execution", rowNum, fnReturnDateWithTimeForExcel());

		// Add Results column to test case sheet
		if (isTestPass) {
			TestUtil.reportDataSetResult(currentSuiteXls, "Test Cases", TestUtil.getRowNum(currentSuiteXls, this.getClass().getSimpleName()), "PASS");
			currentSuiteXls.setCellData("Test Cases", "Last_Successful_Execution", rowNum, fnReturnDateWithTimeForExcel());
			fnpElapsedTime(currentSuiteName, this.getClass().getSimpleName(), currentScriptCode, "PASS");
		} else {
			TestUtil.reportDataSetResult(currentSuiteXls, "Test Cases", TestUtil.getRowNum(currentSuiteXls, this.getClass().getSimpleName()), "FAIL");
			fnpElapsedTime(currentSuiteName, this.getClass().getSimpleName(), currentScriptCode, "FAIL");
		}
		fnpMymsg("=========================================================================================================================================");

		hashXlData.clear();

		fnpCommonCloseBrowsersAndKillProcess();

	}

	
	
	
	

}
