package nsf.ecap.Generic_suite;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import nsf.ecap.Health_Science_suite.TestSuiteBase_HealthScience;
import nsf.ecap.Work_Order_suite.NewNew_Draft_InReview_No_Fac;
//import nsf.ecap.config.IPULSE_CONSTANTS;
import static nsf.ecap.config.IPULSE_CONSTANTS.*;

import nsf.ecap.util.ErrorUtil;
import nsf.ecap.util.TestUtil;
import nsf.ecap.util.Xls_Reader;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;

public class Basic_Search_Functionality extends TestSuiteBase_Generic_suite {

	public boolean ealierTestCaseFailed=false;//so that kill browser and launch again
	public String subTestCaseName=null;
	public String searchName=null;
	
	@BeforeTest
	public void checkTestSkip() throws Throwable {
		BS01 = new NewNew_Draft_InReview_No_Fac();
		BS01.checkTestSkip(this.getClass().getSimpleName());
		
		start = new Date();
		fnpLaunchBrowserAndLogin();//launch for first time for first test case only whichever runs first
	}
	
	
	
	

	
	
	
	//@Test(enabled = false)
	@Test(priority = 1)
	public void Search_WorkOrder() throws Throwable {

		subTestCaseName = new Throwable() .getStackTrace()[0]  .getMethodName(); 
		searchName=generic_suite_basic_search_func_searchWo_water_test_name;
		try{			
			fnpIsSubTestCaseRunnable(this.getClass().getSimpleName(),subTestCaseName);		
			if (ealierTestCaseFailed) {
				fnpLaunchBrowserAndLogin();
				//fnpLaunchBrowserAndLogin(userName, password);
				ealierTestCaseFailed=false;
			} 

			fnpCommonSearch(searchName,"SearchWorkOrderLink","","",
					"WorkOrderNoField",searchResult_WO_colName,"DetailWorkOrderPageheadingContainsId",detailWorkOrderPageheadingContains);		
			
			String value="ISR-NSF International Strategic Registrations, Ltd.";
			fnpClick_OR("WO_BackBtn");
			fnpClick_OR("MainClearLink");
			String temp=fnpGetAttribute_OR("WorkOrderNoField", "value");
			if (temp.equalsIgnoreCase("")) {
				fnpMymsg("Work Order No. field becomes empty after clicking 'Clear' link.");
			} else {
				msg="Work Order No. field is not becoming empty after clicking 'Clear' link because actal is this '"+temp+"' and expected is blank.";
				fnpMymsg(msg);
				throw new Exception(msg);
			}			
			searchName=generic_suite_basic_search_func_searchWo_isr_test_name;
			fnpCommonSearch_withoutClickingTopMenu( searchName,"SearchWorkOrderLink","",value,
					"WorkOrderNoField",searchResult_WO_colName,"DetailWorkOrderPageheadingContainsId",detailWorkOrderPageheadingContains);
			
			
			
			
			}catch (SkipException sk) {
		//	skip = true;
			String stackTrace = Throwables.getStackTraceAsString(sk);
			String errorMsg = sk.getMessage();
			Exception c = new Exception(stackTrace);
			ErrorUtil.addVerificationFailure(c);
			throw new SkipException(errorMsg + " \n\nSo,Skipping Test Case" + subTestCaseName);
		} catch (Throwable t) {
			ealierTestCaseFailed=true;
			fnpCommonFinalCatchBlock(t, "  "+subTestCaseName+"Flow flow  is fail . ", subTestCaseName+"FlowFail");

		}
		
		

	}
	
	
/*	
	@Test(priority = 2)
	public void Search_WorkOrder_ISR() throws Throwable {
		
		String subTestCaseName="Search_WorkOrder_ISR";
		String searchName="Search Work Order ISR";
		try{			
			fnpIsSubTestCaseRunnable(this.getClass().getSimpleName(),subTestCaseName);
			if (ealierTestCaseFailed) {
				fnpLaunchBrowserAndLogin();
				ealierTestCaseFailed=false;
			} 

			String value="ISR-NSF International Strategic Registrations, Ltd.";
			fnpCommonSearch(searchName,"SearchWorkOrderLink","",value,
					"WorkOrderNoField",searchResult_WO_colName,"DetailWorkOrderPageheadingContainsId",detailWorkOrderPageheadingContains);
							
		}catch (SkipException sk) {
	//	skip = true;
		String stackTrace = Throwables.getStackTraceAsString(sk);
		String errorMsg = sk.getMessage();
		Exception c = new Exception(stackTrace);
		ErrorUtil.addVerificationFailure(c);
		throw new SkipException(errorMsg + " \n\nSo,Skipping Test Case" + subTestCaseName);
	} catch (Throwable t) {
		ealierTestCaseFailed=true;
		fnpCommonFinalCatchBlock(t, "  "+subTestCaseName+"Flow flow  is fail . ", subTestCaseName+"FlowFail");

	}
	

	}
	
	*/
	
	
	
	
	@Test(priority = 3)
	public void Search_Task() throws Throwable {

		subTestCaseName = new Throwable() .getStackTrace()[0]  .getMethodName(); 
		searchName=generic_suite_basic_search_func_searchTask_test_name;
		try{			
			fnpIsSubTestCaseRunnable(this.getClass().getSimpleName(),subTestCaseName);
			if (ealierTestCaseFailed) {
				fnpLaunchBrowserAndLogin();
				ealierTestCaseFailed=false;
			} 

			fnpCommonSearch(searchName,"SearchTaskLink","","",
					"TaskNoField",searchResult_TaskNo_colName,"ViewTaskPageheading",viewTaskPageheadingContains);
							
		}catch (SkipException sk) {
	//	skip = true;
		String stackTrace = Throwables.getStackTraceAsString(sk);
		String errorMsg = sk.getMessage();
		Exception c = new Exception(stackTrace);
		ErrorUtil.addVerificationFailure(c);
		throw new SkipException(errorMsg + " \n\nSo,Skipping Test Case" + subTestCaseName);
	} catch (Throwable t) {
		ealierTestCaseFailed=true;
		fnpCommonFinalCatchBlock(t, "  "+subTestCaseName+"Flow flow  is fail . ", subTestCaseName+"FlowFail");

	}
	

	}
	
	
	
	
	
	@Test(priority = 4)
	public void Search_Client() throws Throwable {
		
		subTestCaseName = new Throwable() .getStackTrace()[0]  .getMethodName(); 
		searchName=generic_suite_basic_search_func_searchClient_test_name;
		try{			
			fnpIsSubTestCaseRunnable(this.getClass().getSimpleName(),subTestCaseName);
			if (ealierTestCaseFailed) {
				fnpLaunchBrowserAndLogin();
				ealierTestCaseFailed=false;
			} 

			fnpCommonSearch(searchName,"SearchClientLink","","",
					"CorpFacilityNoField",searchResult_ClientCorpFac_colName,"ViewClientPageheading",viewClientPageheadingContains);
							
		}catch (SkipException sk) {
		//skip = true;
		String stackTrace = Throwables.getStackTraceAsString(sk);
		String errorMsg = sk.getMessage();
		Exception c = new Exception(stackTrace);
		ErrorUtil.addVerificationFailure(c);
		throw new SkipException(errorMsg + " \n\nSo,Skipping Test Case" + subTestCaseName);
	} catch (Throwable t) {
		ealierTestCaseFailed=true;
		fnpCommonFinalCatchBlock(t, "  "+subTestCaseName+"Flow flow  is fail . ", subTestCaseName+"FlowFail");

	}
	

	}
	
	
	
	
	
	@Test(priority = 5)
	public void Search_Resource() throws Throwable {
		
		subTestCaseName = new Throwable() .getStackTrace()[0]  .getMethodName(); 
		searchName=generic_suite_basic_search_func_searchResource_test_name;
		try{			
			fnpIsSubTestCaseRunnable(this.getClass().getSimpleName(),subTestCaseName);
			if (ealierTestCaseFailed) {
				fnpLaunchBrowserAndLogin();
				ealierTestCaseFailed=false;
			} 

			fnpCommonSearch(searchName,"Search_Resource","","",
					"ResourceIdField",searchResult_ResourceID_colName,"ViewResourceheading",viewSearchResourceHeadingContains);
							
		}catch (SkipException sk) {
	//	skip = true;
		String stackTrace = Throwables.getStackTraceAsString(sk);
		String errorMsg = sk.getMessage();
		Exception c = new Exception(stackTrace);
		ErrorUtil.addVerificationFailure(c);
		throw new SkipException(errorMsg + " \n\nSo,Skipping Test Case" + subTestCaseName);
	} catch (Throwable t) {
		ealierTestCaseFailed=true;
		fnpCommonFinalCatchBlock(t, "  "+subTestCaseName+"Flow flow  is fail . ", subTestCaseName+"FlowFail");

	}
	

	}
	
	
	
	
	
	@Test(priority = 6)
	public void Search_EPSF() throws Throwable {
		
		subTestCaseName = new Throwable() .getStackTrace()[0]  .getMethodName(); 
		searchName=generic_suite_basic_search_func_searchEPSF_test_name;
		try{			
			fnpIsSubTestCaseRunnable(this.getClass().getSimpleName(),subTestCaseName);
			if (ealierTestCaseFailed) {
				fnpLaunchBrowserAndLogin();
				ealierTestCaseFailed=false;
			} 

			fnpCommonSearch(searchName,"SearchEPSFLink","","",
					"EPSFSearchField",searchResult_EPSF_colName,"ViewEPSFheading",viewEPSFHeadingContains);
							
		}catch (SkipException sk) {
		//skip = true;
		String stackTrace = Throwables.getStackTraceAsString(sk);
		String errorMsg = sk.getMessage();
		Exception c = new Exception(stackTrace);
		ErrorUtil.addVerificationFailure(c);
		throw new SkipException(errorMsg + " \n\nSo,Skipping Test Case" + subTestCaseName);
	} catch (Throwable t) {
		ealierTestCaseFailed=true;
		fnpCommonFinalCatchBlock(t, "  "+subTestCaseName+"Flow flow  is fail . ", subTestCaseName+"FlowFail");

	}
	

	}
	
	
	
	
	
	
	
	@Test(priority = 7)
	public void Search_Issue() throws Throwable {
		
		subTestCaseName = new Throwable() .getStackTrace()[0]  .getMethodName(); 
		searchName=generic_suite_basic_search_func_searchIssue_test_name;
		try{			
			fnpIsSubTestCaseRunnable(this.getClass().getSimpleName(),subTestCaseName);
			if (ealierTestCaseFailed) {
				fnpLaunchBrowserAndLogin();
				ealierTestCaseFailed=false;
			} 

			fnpCommonSearch(searchName,"SearchIssuesLink","","",
					"IssueIdField",searchResult_IssueId_colName,"ViewIssueheading",viewIssueHeadingContains);
							
		}catch (SkipException sk) {
		//skip = true;
		String stackTrace = Throwables.getStackTraceAsString(sk);
		String errorMsg = sk.getMessage();
		Exception c = new Exception(stackTrace);
		ErrorUtil.addVerificationFailure(c);
		throw new SkipException(errorMsg + " \n\nSo,Skipping Test Case" + subTestCaseName);
	} catch (Throwable t) {
		ealierTestCaseFailed=true;
		fnpCommonFinalCatchBlock(t, "  "+subTestCaseName+"Flow flow  is fail . ", subTestCaseName+"FlowFail");

	}
	

	}
	
	
	
	
	
	
	
	
	
	@Test(priority = 8)
	public void Search_Escalation() throws Throwable {
		
		subTestCaseName = new Throwable() .getStackTrace()[0]  .getMethodName(); 
		searchName=generic_suite_basic_search_func_searchEscalation_test_name;
		try{			
			fnpIsSubTestCaseRunnable(this.getClass().getSimpleName(),subTestCaseName);
			if (ealierTestCaseFailed) {
				fnpLaunchBrowserAndLogin();
				ealierTestCaseFailed=false;
			} 

			fnpCommonSearch(searchName,"SearchEscalationLink","","",
					"EscalationIdField",searchResult_Escalation_colName,"ViewEscalationheading",viewEscalationHeadingContains);
							
		}catch (SkipException sk) {
		//skip = true;
		String stackTrace = Throwables.getStackTraceAsString(sk);
		String errorMsg = sk.getMessage();
		Exception c = new Exception(stackTrace);
		ErrorUtil.addVerificationFailure(c);
		throw new SkipException(errorMsg + " \n\nSo,Skipping Test Case" + subTestCaseName);
	} catch (Throwable t) {
		ealierTestCaseFailed=true;
		fnpCommonFinalCatchBlock(t, "  "+subTestCaseName+"Flow flow  is fail . ", subTestCaseName+"FlowFail");

	}
	

	}
	
	
	
	
	
	
	
	
	@Test(priority = 9)
	public void Search_Monitor_Plan() throws Throwable {
		
		subTestCaseName = new Throwable() .getStackTrace()[0]  .getMethodName(); 
		searchName=generic_suite_basic_search_func_searchMonitorPlan_test_name;
		try{			
			fnpIsSubTestCaseRunnable(this.getClass().getSimpleName(),subTestCaseName);
			if (ealierTestCaseFailed) {
				fnpLaunchBrowserAndLogin();
				ealierTestCaseFailed=false;
			} 

			fnpCommonSearch(searchName,"SearchAnnualMonitorPlanLink","","",
					"PlanIdField",searchResult_MonitorPlanID_colName,"ViewAnnualMonitorPlanheading",viewMonitorPlanHeadingContains);
							
		}catch (SkipException sk) {
		//skip = true;
		String stackTrace = Throwables.getStackTraceAsString(sk);
		String errorMsg = sk.getMessage();
		Exception c = new Exception(stackTrace);
		ErrorUtil.addVerificationFailure(c);
		throw new SkipException(errorMsg + " \n\nSo,Skipping Test Case" + subTestCaseName);
	} catch (Throwable t) {
		ealierTestCaseFailed=true;
		fnpCommonFinalCatchBlock(t, "  "+subTestCaseName+"Flow flow  is fail . ", subTestCaseName+"FlowFail");

	}
	

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	@AfterMethod
	public void afterMethod() throws Throwable {
		if (fail) {
			isTestPass = false;
		}
		
		
		if (ealierTestCaseFailed) {
			fnpCommonCloseBrowsersAndKillProcess();
		} 
		
		
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