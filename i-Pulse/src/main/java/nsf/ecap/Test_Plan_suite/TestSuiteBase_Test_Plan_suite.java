package nsf.ecap.Test_Plan_suite;

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

public class TestSuiteBase_Test_Plan_suite extends TestSuiteBase {

	
	public static String runmodes[] = null;
	public static int count = -1;
	


	// check if the suite has to be skipped or not
	@BeforeSuite
	public void checkSuiteSkip() throws Throwable {

		fnInitialize();


		setCurrentSuiteName(Test_Plan_suite_Name);
		currentSuiteCode = "TP";
		//currentSuiteXls = Dietary_Supplement_suitexls;
		setCurrentSuiteExcel(Test_Plan_suitexls);
		
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
	
	
	 
		
	 public void fnpCommonCodeForSelectingTestingTaskMultipleTimes_old() throws Throwable{
			
			
			List <String> alreadyCreatedTestingTaskList=new ArrayList<String>();
			int totalRows=fnpCountRowsInTable("TasksTable_EditWO");
			int taskNoColNo=fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskNoColName);
			int taskTypeColNo=fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskTypeColName);
			int taskStatusColNo=fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_StatusColName);
			int epsfColNo=fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_EPSFColName);
			
			String taskType;
			String taskNo;
			
			
			if (fnpCheckElementDisplayedImmediately("DataTable_paginator")) {
				List<WebElement> pagesCount = fnpGetORObject_list("DataTable_paginator", Integer.parseInt(CONFIG.getProperty("implicitWaitTime")));
				int pageCountsize = pagesCount.size();
				fnpMymsg("Total pagination on task table are --"+pageCountsize);
				int j1 = 0;
				int iCounter = 1;
				for (int p = 0; p < pagesCount.size(); p++) {
					pagesCount = fnpGetORObject_list("DataTable_paginator", Integer.parseInt(CONFIG.getProperty("implicitWaitTime")));
					for (int i = 1; i <= totalRows; i++) {
						taskType=fnpFetchFromTable("TasksTable_EditWO", i, taskTypeColNo);
						if (taskType.equalsIgnoreCase("TESTING")) {
							taskNo=fnpFetchFromTable("TasksTable_EditWO", i, taskNoColNo);
							alreadyCreatedTestingTaskList.add(taskNo);
						}						
					}								
					if ((pageCountsize!=1)  & (  (p+1)<pageCountsize) ) {
						pagesCount.get(p + 1).click();
						fnpMymsg("Clicked '"+(p+2)+"' page");
						Thread.sleep(4000);
						fnpLoading_wait();
						fnpLoading_wait();
						
						totalRows=fnpCountRowsInTable("TasksTable_EditWO");
					}
				}
				
			} else {
				for (int i = 1; i <= totalRows; i++) {
					taskType=fnpFetchFromTable("TasksTable_EditWO", i, taskTypeColNo);
					if (taskType.equalsIgnoreCase("TESTING")) {
						taskNo=fnpFetchFromTable("TasksTable_EditWO", i, taskNoColNo);
						alreadyCreatedTestingTaskList.add(taskNo);
					}						
				}	
			}
			

			
			
		
			fnpMymsg("So already created TESTING tasks are --"+alreadyCreatedTestingTaskList);
			fnpClick_OR("EditWorkOrderScreen_TasksTab_AddTasksBtn");
			fnpCommonSelectingTasks_InWO((String) hashXlData.get("TaskTypeName"));			
			String newlyCreatedTestingTask = null;
			totalRows=fnpCountRowsInTable("TasksTable_EditWO");
			String taskStatus;
			String epsfAlready;
			
			
			//Comeback to the first page of the table			
		//	if (fnpCheckElementClickableOrNot_NOR(OR.getProperty("DataTable_paginator_FirstIcon_ForTakingYouToFirstPage"), 2000)) {
			if (fnpCheckElementDisplayedImmediately("DataTable_paginator_FirstIcon_ForTakingYouToFirstPage")) {
				fnpClick_OR("DataTable_paginator_FirstIcon_ForTakingYouToFirstPage");				
			}
			

			
			totalRows=fnpCountRowsInTable("TasksTable_EditWO");
			
			if (fnpCheckElementDisplayedImmediately("DataTable_paginator")) {
				List<WebElement> pagesCount = fnpGetORObject_list("DataTable_paginator", Integer.parseInt(CONFIG.getProperty("implicitWaitTime")));
				int pageCountsize = pagesCount.size();
				
				int j1 = 0;
				int iCounter = 1;
				for (int p = 0; p < pageCountsize; p++) {
					pagesCount = fnpGetORObject_list("DataTable_paginator", Integer.parseInt(CONFIG.getProperty("implicitWaitTime")));		
				
					for (int i = 1; i <= totalRows; i++) {
						taskType=fnpFetchFromTable("TasksTable_EditWO", i, taskTypeColNo);
						if (taskType.equalsIgnoreCase("TESTING")) {
							taskNo=fnpFetchFromTable("TasksTable_EditWO", i, taskNoColNo);
							if (!(alreadyCreatedTestingTaskList.contains(taskNo))) {
								newlyCreatedTestingTask=taskNo;
								fnpMymsg("Newly created TESTING task no. is --"+newlyCreatedTestingTask);
								taskStatus=fnpFetchFromTable("TasksTable_EditWO", i, taskStatusColNo);
								epsfAlready=fnpFetchFromTable("TasksTable_EditWO", i, epsfColNo);
								Assert.assertEquals( taskStatus, "CREATED","Newly created TESTING task '"+newlyCreatedTestingTask+"' 's status should be CREATED.");
								fnpMymsg("Newly created TESTING task '"+newlyCreatedTestingTask+"' 's status is CREATED.");
								Assert.assertEquals(epsfAlready.trim(),"",  "Newly created TESTING task '"+newlyCreatedTestingTask+"' 's epsf should be blank.");
								fnpMymsg("Newly created TESTING task '"+newlyCreatedTestingTask+"' 's epsf is blank.");
								break;
							}
						}						
					}
					
					if (newlyCreatedTestingTask!=null) {
						break;
					} else {
						//if (pageCountsize!=1) {
						if ((pageCountsize!=1)  & (  (p+1)<pageCountsize) ) {
							fnpMymsg("Newly created TESTING task is not found, so going to check on '"+(p+2)+"' page");
							pagesCount.get(p + 1).click();
							fnpMymsg("Clicked '"+(p+2)+"' page");
							fnpLoading_wait();
							Thread.sleep(4000);
							totalRows=fnpCountRowsInTable("TasksTable_EditWO");
						}
					}
					
				}
			}else{
				
				
				for (int i = 1; i <= totalRows; i++) {
					taskType=fnpFetchFromTable("TasksTable_EditWO", i, taskTypeColNo);
					if (taskType.equalsIgnoreCase("TESTING")) {
						taskNo=fnpFetchFromTable("TasksTable_EditWO", i, taskNoColNo);
						if (!(alreadyCreatedTestingTaskList.contains(taskNo))) {
							newlyCreatedTestingTask=taskNo;
							fnpMymsg("Newly created TESTING task no. is --"+newlyCreatedTestingTask);
							taskStatus=fnpFetchFromTable("TasksTable_EditWO", i, taskStatusColNo);
							epsfAlready=fnpFetchFromTable("TasksTable_EditWO", i, epsfColNo);
							Assert.assertEquals( taskStatus,"CREATED", "Newly created TESTING task '"+newlyCreatedTestingTask+"' 's status should be CREATED.");
							fnpMymsg("Newly created TESTING task '"+newlyCreatedTestingTask+"' 's status is CREATED.");
							Assert.assertEquals(epsfAlready.trim(),"",  "Newly created TESTING task '"+newlyCreatedTestingTask+"' 's epsf should be blank.");
							fnpMymsg("Newly created TESTING task '"+newlyCreatedTestingTask+"' 's epsf is blank.");
							break;
						}
					}						
				}
			}

			
			if (newlyCreatedTestingTask!=null) {
				fnpClickALinkInATable(newlyCreatedTestingTask);
			} else {
				msg="Testing task is not getting generated.";
				fnpMymsg(msg);
				throw new Exception(msg);
			}
			
			
			fnpPFListByLiNo("FacilityLocationList", "FacilityLocationOptions", 1);
			
			fnpClick_OR("CreateNew_btn");
	 }
	 
	 
	
	 public void fnpCommonCodeForSelectingTestingTaskMultipleTimes() throws Throwable{
			
			
			List <String> alreadyCreatedTestingTaskList=new ArrayList<String>() ;
			int totalRows=fnpCountRowsInTable("TasksTable_EditWO");
			int taskNoColNo=fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskNoColName);
			int taskTypeColNo=fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_TaskTypeColName);
			int taskStatusColNo=fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_StatusColName);
			int epsfColNo=fnpFindColumnIndex("TaskTable_HeaderRow", taskTable_EPSFColName);
			
			String taskType;
			String taskNo;
			int a;
			
			String pageLinkXpath_part1=OR.getProperty("DataTable_paginator")+"[normalize-space(text())='";
			String pageLinkXpath_part2="']";
			String pageLinkXpath;
			String classValue = null;
			int maxWaitSecondsAfterClickingPagination=60;
			int iWhileCounter;
			
			if (fnpCheckElementDisplayedImmediately("DataTable_paginator")) {
				fnpMymsg("Pagination is present.");
				
/*				List<WebElement> pagesCount = fnpGetORObject_list("DataTable_paginator", Integer.parseInt(CONFIG.getProperty("implicitWaitTime")));
				int pageCountsize = pagesCount.size();
				*/
				
				//if (fnpCheckElementClickableOrNot_notInOR("DataTable_paginator_LastIcon_ForTakingYouToLastPage")) {
				if (fnpCheckElementIsEnabled_usingForPaginationIconOnly("DataTable_paginator_LastIcon_ForTakingYouToLastPage")) {
					fnpClick_OR("DataTable_paginator_LastIcon_ForTakingYouToLastPage");	
					fnpMymsg("Clicked pagination last icon which takes to the last page.");
					fnpWaitTillThisPaginationIconGetsDisabled("DataTable_paginator_LastIcon_ForTakingYouToLastPage");
				}

				List<WebElement> visiblePagesList = fnpGetORObject_list("DataTable_paginator", Integer.parseInt(CONFIG.getProperty("implicitWaitTime")));
				int visiblePagesListsize = visiblePagesList.size();
				String lastPageNo=visiblePagesList.get(visiblePagesListsize-1).getText();
				int totalPages=Integer.parseInt(lastPageNo);
				
				fnpMymsg("Total pagination on task table are --"+totalPages);
				fnpClick_OR("DataTable_paginator_FirstIcon_ForTakingYouToFirstPage");
				fnpMymsg("Clicked pagination first icon which takes to the first page.");
				fnpWaitTillThisPaginationIconGetsDisabled("DataTable_paginator_FirstIcon_ForTakingYouToFirstPage");
				pageLinkXpath=pageLinkXpath_part1+(1)+pageLinkXpath_part2;
				iWhileCounter=0;
				while(true){
					iWhileCounter++;
					//try{
							//classValue=fnpGetORObjectX___NOR(pageLinkXpath).getAttribute("class");
							if (fnpCheckElementDisplayedImmediately_NOR(pageLinkXpath)) {
								classValue=fnpGetORObjectX___NOR(pageLinkXpath).getAttribute("class");
							}
							if (classValue.contains("ui-state-active")) {
								break;
							}
					//}catch(Throwable t){
						if (iWhileCounter>maxWaitSecondsAfterClickingPagination) {
							msg="After clicking DataTable_paginator_FirstIcon_ForTakingYouToFirstPage , first page is not getting opened even after more than "+maxWaitSecondsAfterClickingPagination+" seconds approx.";
							fnpMymsg(msg );
							throw new Exception(msg );									
						} else {
							Thread.sleep(1000);
						}
					//}
							
							
					}
				
				
				
				
				totalRows=fnpCountRowsInTable("TasksTable_EditWO");
				

				
				int j1 = 0;
				int iCounter = 1;
				//iWhileCounter=0;
				
				for (int p = 1; p < totalPages+1; p++) {
					//pagesCount = fnpGetORObject_list("DataTable_paginator", Integer.parseInt(CONFIG.getProperty("implicitWaitTime")));
					
					if (p!=1) {
						pageLinkXpath=pageLinkXpath_part1+(p)+pageLinkXpath_part2;
						//	fnpWaitTillVisiblityOfElementNClickable_NOR(pageLinkXpath);
							fnpClick_NOR(pageLinkXpath);
							//fnpClick_NOR_withoutWait(pageLinkXpath);
							iWhileCounter=0;					
							while(true){
								iWhileCounter++;
								//try{
									//classValue=fnpGetORObjectX___NOR(pageLinkXpath).getAttribute("class");
									if (fnpCheckElementDisplayedImmediately_NOR(pageLinkXpath)) {
										classValue=fnpGetORObjectX___NOR(pageLinkXpath).getAttribute("class");
									}
										if (classValue.contains("ui-state-active")) {
											break;
										}
								//}catch(Throwable t){
									if (iWhileCounter>maxWaitSecondsAfterClickingPagination) {
										msg="After clicking this no. '"+(p)+"' , this page is not getting opened even after more than "+maxWaitSecondsAfterClickingPagination+" seconds approx.";
										//fnpMymsg(msg +"/n/n "+t.getMessage());
										fnpMymsg(msg );
										throw new Exception(msg);								
									} else {
										Thread.sleep(1000);
									}
								//}
										
										
								}
					}

					totalRows=fnpCountRowsInTable("TasksTable_EditWO");
					for (int i = 1; i <= totalRows; i++) {
						taskType=fnpFetchFromTable("TasksTable_EditWO", i, taskTypeColNo);
						if (taskType.equalsIgnoreCase("TESTING")) {
							taskNo=fnpFetchFromTable("TasksTable_EditWO", i, taskNoColNo);
							alreadyCreatedTestingTaskList.add(taskNo);
						}						
					}

					
					
				}
				
			} else {
				for (int i = 1; i <= totalRows; i++) {
					taskType=fnpFetchFromTable("TasksTable_EditWO", i, taskTypeColNo);
					if (taskType.equalsIgnoreCase("TESTING")) {
						taskNo=fnpFetchFromTable("TasksTable_EditWO", i, taskNoColNo);
						alreadyCreatedTestingTaskList.add(taskNo);
					}						
				}	
			}
			

			
			
		
			fnpMymsg("So already created TESTING tasks are --"+alreadyCreatedTestingTaskList);
			fnpClick_OR("EditWorkOrderScreen_TasksTab_AddTasksBtn");
			fnpCommonSelectingTasks_InWO((String) hashXlData.get("TaskTypeName"));			
			String newlyCreatedTestingTask = null;
			totalRows=fnpCountRowsInTable("TasksTable_EditWO");
			String taskStatus;
			String epsfAlready;
			
			
			//if (fnpCheckElementClickableOrNot_notInOR("DataTable_paginator_LastIcon_ForTakingYouToLastPage")) {
			//if (fnpCheckElementEnabledImmediately("DataTable_paginator_LastIcon_ForTakingYouToLastPage")) {
			//if (fnpCheckElementClickableOrNot_notInOR("DataTable_paginator_LastIcon_ForTakingYouToLastPage")) {
			
			if (fnpCheckElementDisplayedImmediately("DataTable_paginator")) {
				if (fnpCheckElementIsEnabled_usingForPaginationIconOnly("DataTable_paginator_LastIcon_ForTakingYouToLastPage")) {
					fnpMymsg("We are not on last page, so clicking this last icon to take us to the last page.");
					fnpClick_OR("DataTable_paginator_LastIcon_ForTakingYouToLastPage");	
					fnpWaitTillThisPaginationIconGetsDisabled("DataTable_paginator_LastIcon_ForTakingYouToLastPage");
				}else{
					fnpMymsg("We are already on the last page");
				}
			}

			
			//fnpClick_OR("DataTable_paginator_FirstIcon_ForTakingYouToLastPage");	
			
			int totalPages;
			if (fnpCheckElementDisplayedImmediately("DataTable_paginator")) {
				List<WebElement> visiblePagesList = fnpGetORObject_list("DataTable_paginator", Integer.parseInt(CONFIG.getProperty("implicitWaitTime")));
				int visiblePagesListsize = visiblePagesList.size();
				String lastPageNo=visiblePagesList.get(visiblePagesListsize-1).getText();
				totalPages=Integer.parseInt(lastPageNo);			
			}else{
				totalPages=1;
			}
			
			
			//Comeback to the first page of the table			
		//	if (fnpCheckElementClickableOrNot_NOR(OR.getProperty("DataTable_paginator_FirstIcon_ForTakingYouToFirstPage"), 2000)) {
			//if (fnpCheckElementDisplayedImmediately("DataTable_paginator_FirstIcon_ForTakingYouToFirstPage")) {
			//if (fnpCheckElementClickableOrNot_notInOR("DataTable_paginator_FirstIcon_ForTakingYouToFirstPage")) {
			
			if (fnpCheckElementDisplayedImmediately("DataTable_paginator")) {
				if (fnpCheckElementIsEnabled_usingForPaginationIconOnly("DataTable_paginator_FirstIcon_ForTakingYouToFirstPage")) {
					fnpMymsg("We are not on fist page, so clicking this fist icon to take us to the first page.");
					fnpClick_OR("DataTable_paginator_FirstIcon_ForTakingYouToFirstPage");
					fnpWaitTillThisPaginationIconGetsDisabled("DataTable_paginator_FirstIcon_ForTakingYouToFirstPage");
				}else{
					fnpMymsg("We are already on the first page");
				}
			}
			
			

			

			
			totalRows=fnpCountRowsInTable("TasksTable_EditWO");
			
			if (fnpCheckElementDisplayedImmediately("DataTable_paginator")) {
				List<WebElement> pagesCount = fnpGetORObject_list("DataTable_paginator", Integer.parseInt(CONFIG.getProperty("implicitWaitTime")));
				int pageCountsize = pagesCount.size();
				
				int j1 = 0;
				int iCounter = 1;
				
				
				for (int p = 1; p < totalPages+1; p++) {
					//pagesCount = fnpGetORObject_list("DataTable_paginator", Integer.parseInt(CONFIG.getProperty("implicitWaitTime")));
					
					if (p!=1) {

						pageLinkXpath=pageLinkXpath_part1+(p)+pageLinkXpath_part2;
						//fnpWaitTillVisiblityOfElementNClickable_NOR(pageLinkXpath);
						fnpClick_NOR(pageLinkXpath);
						//fnpClick_NOR_withoutWait(pageLinkXpath);
						iWhileCounter=0;
						while(true){
							iWhileCounter++;
							//try{
									//classValue=fnpGetORObjectX___NOR(pageLinkXpath).getAttribute("class");
									if (fnpCheckElementDisplayedImmediately_NOR(pageLinkXpath)) {
										classValue=fnpGetORObjectX___NOR(pageLinkXpath).getAttribute("class");
									}
									if (classValue.contains("ui-state-active")) {
										break;
									}
							//}catch(Throwable t){
								if (iWhileCounter>maxWaitSecondsAfterClickingPagination) {
									msg="After clicking this no. '"+(p+1)+"' , this page is not getting opened even after more than "+maxWaitSecondsAfterClickingPagination+" seconds approx.";
									fnpMymsg(msg );
									throw new Exception(msg);								
								} else {
									Thread.sleep(1000);
								}
							//}
									
									
							}
				
					}
					
					
					totalRows=fnpCountRowsInTable("TasksTable_EditWO");
					fnpMymsg("Page no. is --"+p);
					for (int i = 1; i <= totalRows; i++) {
						fnpMymsg("Current row no. is --"+i);
						taskType=fnpFetchFromTable("TasksTable_EditWO", i, taskTypeColNo);
						if (taskType.equalsIgnoreCase("TESTING")) {
							taskNo=fnpFetchFromTable("TasksTable_EditWO", i, taskNoColNo);
							if (!(alreadyCreatedTestingTaskList.contains(taskNo))) {
								newlyCreatedTestingTask=taskNo;
								fnpMymsg("Newly created TESTING task no. is --"+newlyCreatedTestingTask);
								taskStatus=fnpFetchFromTable("TasksTable_EditWO", i, taskStatusColNo);
								epsfAlready=fnpFetchFromTable("TasksTable_EditWO", i, epsfColNo);
								Assert.assertEquals( taskStatus, "CREATED","Newly created TESTING task '"+newlyCreatedTestingTask+"' 's status should be CREATED.");
								fnpMymsg("Newly created TESTING task '"+newlyCreatedTestingTask+"' 's status is CREATED.");
								Assert.assertEquals(epsfAlready.trim(),"",  "Newly created TESTING task '"+newlyCreatedTestingTask+"' 's epsf should be blank.");
								fnpMymsg("Newly created TESTING task '"+newlyCreatedTestingTask+"' 's epsf is blank.");
								break;
							}
						}						
					}
					
					if (newlyCreatedTestingTask!=null) {
						break;
					} 
				
				
				}
				
				
			}else{
				
				
				for (int i = 1; i <= totalRows; i++) {
					taskType=fnpFetchFromTable("TasksTable_EditWO", i, taskTypeColNo);
					if (taskType.equalsIgnoreCase("TESTING")) {
						taskNo=fnpFetchFromTable("TasksTable_EditWO", i, taskNoColNo);
						if (!(alreadyCreatedTestingTaskList.contains(taskNo))) {
							newlyCreatedTestingTask=taskNo;
							fnpMymsg("Newly created TESTING task no. is --"+newlyCreatedTestingTask);
							taskStatus=fnpFetchFromTable("TasksTable_EditWO", i, taskStatusColNo);
							epsfAlready=fnpFetchFromTable("TasksTable_EditWO", i, epsfColNo);
							Assert.assertEquals( taskStatus,"CREATED", "Newly created TESTING task '"+newlyCreatedTestingTask+"' 's status should be CREATED.");
							fnpMymsg("Newly created TESTING task '"+newlyCreatedTestingTask+"' 's status is CREATED.");
							Assert.assertEquals(epsfAlready.trim(),"",  "Newly created TESTING task '"+newlyCreatedTestingTask+"' 's epsf should be blank.");
							fnpMymsg("Newly created TESTING task '"+newlyCreatedTestingTask+"' 's epsf is blank.");
							break;
						}
					}						
				}
			}

			
			if (newlyCreatedTestingTask!=null) {
				fnpClickALinkInATable(newlyCreatedTestingTask);
			} else {
				msg="Testing task is not getting generated.";
				fnpMymsg(msg);
				throw new Exception(msg);
			}
			
			
			//fnpPFListByLiNo("FacilityLocationList", "FacilityLocationOptions", 1);
			//fnpPFList("FacilityLocationList", "FacilityLocationOptions", "07481");
			
			
			//first get all options
			ArrayList<String> stringListValues=fnpPFList_FetchAllValuesPresent("FacilityLocationList");
			
			//fire sql queries one by one and exit if dcc present
			fnpMymsg("Going to check for this facility whether dcc is present or not.");
			String temp;
			String temp2;
			int dccPresent=0;
			boolean dccFound=false;
			for (int i = 0; i <= (stringListValues.size())-1; i++) {
				temp=stringListValues.get(i).trim();
				fnpMymsg(" Full value present in the drop down at position (ignoring select,choose etc.) '"+(i+1)+"' is --'"+temp+"'.");
				temp2=temp.split("-")[0].trim();//Assuming facility code is separated by '-'
				fnpMymsg("Facility code is --'"+temp2+"'");
			
				dccPresent= fnpCheckThisValueHasDCCPresentOrNot(temp2);//fire sql queriess   
				
				if (dccPresent>0) {
					fnpPFList("FacilityLocationList", "FacilityLocationOptions", temp);
					dccFound=true;
					break;
				} else{
					//in loop again
					fnpMymsg("DCCs are not present for this facility --'"+temp+"'.");
				}
			}
			
			if (!dccFound) {
				msg="DCCs are not present for any facility present in this faclity drop down.";
				fnpMymsg(msg);
				throw new Exception(msg);
				
			}
			

			
			
			fnpClick_OR("CreateNew_btn");
	 }

	 

	 
	 
	 
	 public void fnpCommonCodeFor_EPSFHeaderTab(String collectionType,String testCategory,String standard,String testlocation,String shipToLocation) throws Throwable{
			String expCollType = collectionType;
			// value in excel and drop down is not matched , 'Qualification( QQ )'
			// and 'Qualification (QQ)'
			fnpPFList("EPSF_CollectionTypeList", "EPSF_CollectionTypeListOptions", expCollType);
			//Thread.sleep(3000);
			fnpLoading_wait();
			
			fnpPFList("EPSF_TestCategoryList", "EPSF_TestCategoryListOptions", testCategory);
			//Thread.sleep(3000);
			fnpLoading_wait();
			
			fnpPFList("EPSF_StandardList", "EPSF_StandardListOptions", standard);
			//Thread.sleep(3000);
			fnpLoading_wait();
			fnpPFList("EPSF_TestLocationList", "EPSF_TestLocationListOptions", testlocation);
			fnpPFList("EPSF_ShipToLocationList", "EPSF_ShipToLocationListOptions", shipToLocation);

			fnpClick_OR("EPSF_CreateNextBtn");
	 }
	 
	 
	 
	 
	 public void fnpCommonCodeFor_EPSF_NextNext() throws Throwable{
			/***** New changes as 4 tabs added in epsf ******/
			fnpClick_OR("EPSF_CreateNextBtn");// Click on Next for EPSF fields tab.
			fnpLoading_wait();
			fnpWaitForVisible("EPSF_INFO_highlightedTab");
			fnpLoading_wait();
			fnpClick_OR("EPSF_CreateNextBtn");// Click on Next for EPSF Info tab.
			fnpLoading_wait();

			fnpClick_OR("CreateEPSFFirstBtn");
			/***** New changes as 4 tabs added in epsf ******/

			// fnpClick_OR("EPSF_CreateEPSFBtn"); /// this is old one when 2 tab
			// present only.


			
			
	 }
	 
	 
	 
	 
	 

		public static String fnpReturnHexaCode(String rgbaColor){
			String[] hexValue = rgbaColor.replace("rgba(", "").replace(")", "").split(",");                           

			hexValue[0] = hexValue[0].trim();

			int hexValue1 = Integer.parseInt(hexValue[0]);                   

			hexValue[1] = hexValue[1].trim();

			int hexValue2 = Integer.parseInt(hexValue[1]);                   

			hexValue[2] = hexValue[2].trim();

			int hexValue3 = Integer.parseInt(hexValue[2]);                   

			String actualColor = String.format("#%02x%02x%02x", hexValue1, hexValue2, hexValue3);
			return actualColor;
		}
		
		
		public static void fnpCheckFieldIsAcceptingNoOfCharsAndSaveEvenAfterTabChange(int maxCharsLimit,String xpathExpression,
				String nameOfField,String saveBtn_xpathExpression,String currentTab_xpathExpressioin,String tab2ForChange_xpathExpression) throws Throwable{
			//noOfCharacterWeEntered=4000;
			//int noOfCharacterNeedToEnter = noOfCharacterNeedToEnter;
			int loopCount=maxCharsLimit/4;
			WebElement wb=driver.findElement(By.xpath(xpathExpression));
			for(int i=1;i<=loopCount;i++){//Enter text (up to 4,000 characters) in the wb (include both alphanumeric & symbols) and select 'SAVE'
				wb.sendKeys("A1!@");
			}

			int acceptingText_len = driver.findElement(By.xpath(xpathExpression)).getAttribute("value").length();
			if (acceptingText_len!=maxCharsLimit) {

				msg=nameOfField+" text area does not accepting "+maxCharsLimit+" characters as we are trying to insert "+maxCharsLimit+ " characters but "
						+ "it is accepting only "+acceptingText_len+" characters.";
				fnpMymsg(msg);
				throw new Exception(msg);											
			}
			
			//fnpClick_OR("TestPlan_SaveBtn");
			driver.findElement(By.xpath(saveBtn_xpathExpression)).click();
			fnpLoading_wait();			
			
			 int afterSavingText_len = driver.findElement(By.xpath(xpathExpression)).getAttribute("value").length();
			if (afterSavingText_len!=maxCharsLimit) {
				msg="After click Save button "+nameOfField+" text area does not have "+maxCharsLimit+" characters as we have entered "+maxCharsLimit+ " characters but "
						+ "it has only "+afterSavingText_len+" characters after saving.";
				fnpMymsg(msg);
				throw new Exception(msg);											
			}
			
			
			//fnpClick_OR("EditEpst_TasksTabLink");
			driver.findElement(By.xpath(tab2ForChange_xpathExpression)).click();
			fnpLoading_wait();	
			//fnpClick_OR("EditEpst_TestPlan_TabLink");
			driver.findElement(By.xpath(currentTab_xpathExpressioin)).click();
			fnpLoading_wait();	
			
			
			int tabChangeText_len = driver.findElement(By.xpath(xpathExpression)).getAttribute("value").length();
			if (tabChangeText_len!=maxCharsLimit) {
				//fnpMymsg("Actual characters (After switching tab change) are --'"+fnpGetORObjectX("TestPlan_AddToxicologyNote_TxtArea").getText()+"'.");
				msg="After switching tab change, "+nameOfField+"  text area does not have "+maxCharsLimit+" characters as we have entered "+maxCharsLimit+ " characters but "
						+ "it has only "+tabChangeText_len+" characters after saving.";
				fnpMymsg(msg);
				throw new Exception(msg);											
			}
			
			
			/*********************************************************************/
		}
		
	 
	 
	 
	 
	 
	 

		public static void fnpClickCheckboxInATable_advance(String partialTableDataId, int rowNo ,int colNo) throws Throwable {
			String chkbx_xpath = "//tbody[contains(@id,'" + partialTableDataId + "')]/tr[" + rowNo + "]/td[" + colNo + "]/div/div/span";
			driver.findElement(By.xpath(chkbx_xpath)).click();
			
			}
		
		public static void fnpCheckAllRowsHaveCheckboxColumns(String tableName,String tabledataXpath, int colNo) throws Throwable{
			String xpathRow = tabledataXpath + "/tr";
			int totalRowCount = driver.findElements(By.xpath(xpathRow)).size();
			
	
			String xpathExpression = null;
			for (int i = 1; i < totalRowCount + 1; i++) {
				xpathExpression = xpathRow + "[" + i + "]/td[" + colNo + "]/div//span[contains(@class,'ui-chkbox-icon')]";

				if (fnpCheckElementDisplayedImmediately_NOR(xpathExpression)) {
					//nothing as checkbox is present at this row and col no.
					fnpMymsg("Checkbox is present at this row '"+i+"' and col '"+colNo+"'.");
				} else {
					msg="Checkbox is NOT present in  this table '"+tableName+"' at this row '"+i+"' and col '"+colNo+"'.";
					fnpMymsg(msg);
					throw new Exception(msg);
				}
				
				

			}
		}
		
		
		
		
		public static void fnpCheckCheckbox_basedOnDayData( int dayData, int checkboxColNo) throws Throwable{
			String xpathRow = OR.getProperty("TestPlan_EditAnalysisDayTable_data") + "/tr";
			int totalRowCount = driver.findElements(By.xpath(xpathRow)).size();
			int dayColIndex = fnpFindColumnIndex("TestPlan_EditAnalysisDayTable_HeaderRow", "Day");
		
			String xpathExpression = null;
			String xpathExpression_forCheckbox = null;
			String value = null;
			String classValue = null;
			boolean found=false;
			String dayDataString=String.valueOf(dayData);
			for (int i = 1; i < totalRowCount + 1; i++) {
				xpathExpression = xpathRow + "[" + i + "]/td[" + dayColIndex + "]/input";
				value= driver.findElement(By.xpath(xpathExpression)).getAttribute("value");
				if (value.equalsIgnoreCase(dayDataString)) {
					found=true;
					xpathExpression_forCheckbox = xpathRow + "[" + i + "]/td[" + checkboxColNo + "]/div//span[contains(@class,'ui-chkbox-icon')]";
					classValue= driver.findElement(By.xpath(xpathExpression_forCheckbox)).getAttribute("class");
					if ((classValue.contains("ui-icon-blank"))) {
						fnpMymsg("This checkbox for day '"+dayData+"' is unchecked, so going to click/check it.");
						driver.findElement(By.xpath(xpathExpression_forCheckbox)).click();
						fnpMymsg("This checkbox for day '"+dayData+"' is clicked to check it.");
						Thread.sleep(1000);
						xpathExpression_forCheckbox = xpathRow + "[" + i + "]/td[" + checkboxColNo + "]/div//span[contains(@class,'ui-chkbox-icon')]";
						classValue= driver.findElement(By.xpath(xpathExpression_forCheckbox)).getAttribute("class");
						if ((classValue.contains("ui-icon-blank"))) {
							msg="This checkbox for day '"+dayData+"' is still unchecked, after clicking/checking it. It means there is some problem in checking it as its class is still 'ui-icon-blank' after clicking it.";
							fnpMymsg(msg);
							throw new Exception(msg);
						}
					}else{
						fnpMymsg("This checkbox for day '"+dayData+"' is already checked, so not going to click it again.");
					}
					break;
				} 
				
				

			}
			
			if (!found) {
				msg="This day '"+dayData+"' is NOT present in  this table 'TestPlan_EditAnalysisDayTable'.";
				fnpMymsg(msg);
				throw new Exception(msg);
			}
			
		}
		
		
		
		public static void fnpCheckCheckbox_basedOn_ColData( String OR_dataTable,String OR_HeaderTable,String ColName,String valueAgainst, int checkboxColNo) throws Throwable{
			String xpathRow = OR.getProperty(OR_dataTable) + "/tr";
			int totalRowCount = driver.findElements(By.xpath(xpathRow)).size();
			int colIndex = fnpFindColumnIndex(OR_HeaderTable, ColName);
		
			String xpathExpression = null;
			String xpathExpression_forCheckbox = null;
			String value = null;
			String classValue = null;
			boolean found=false;
			//String dayDataString=String.valueOf(dayData);
			for (int i = 1; i < totalRowCount + 1; i++) {
				
/*				xpathExpression = xpathRow + "[" + i + "]/td[" + colIndex + "]/input";
				value= driver.findElement(By.xpath(xpathExpression)).getAttribute("value");
				*/
				xpathExpression = xpathRow + "[" + i + "]/td[" + colIndex + "]/span";
				value= driver.findElement(By.xpath(xpathExpression)).getText();
				if (value.equalsIgnoreCase(valueAgainst)) {
					found=true;
					xpathExpression_forCheckbox = xpathRow + "[" + i + "]/td[" + checkboxColNo + "]/div//span[contains(@class,'ui-chkbox-icon')]";
					classValue= driver.findElement(By.xpath(xpathExpression_forCheckbox)).getAttribute("class");
					if ((classValue.contains("ui-icon-blank"))) {
						fnpMymsg("This checkbox for value '"+valueAgainst+"' is unchecked, so going to click/check it.");
						driver.findElement(By.xpath(xpathExpression_forCheckbox)).click();
						fnpMymsg("This checkbox for value '"+valueAgainst+"' is clicked to check it.");
						Thread.sleep(1000);
						xpathExpression_forCheckbox = xpathRow + "[" + i + "]/td[" + checkboxColNo + "]/div//span[contains(@class,'ui-chkbox-icon')]";
						classValue= driver.findElement(By.xpath(xpathExpression_forCheckbox)).getAttribute("class");
						if ((classValue.contains("ui-icon-blank"))) {
							msg="This checkbox for value '"+valueAgainst+"' is still unchecked, after clicking/checking it. It means there is some problem in checking it as its class is still 'ui-icon-blank' after clicking it.";
							fnpMymsg(msg);
							throw new Exception(msg);
						}
					}else{
						fnpMymsg("This checkbox for value '"+valueAgainst+"' is already checked, so not going to click it again.");
					}
					break;
				} 
				
				

			}
			
			if (!found) {
				msg="This value '"+valueAgainst+"' is NOT present in  this table '"+OR_dataTable+"'.";
				fnpMymsg(msg);
				throw new Exception(msg);
			}
			
		}
		
		public static void fnpCheckIsDaysCriticalIn_ascendingOrder_and_its_color(  ArrayList<Integer> daysList,ArrayList<Integer> criticalDaysList ) throws Throwable{
			Thread.sleep(1);
			List<WebElement> daysListWebElements = fnpGetORObject_list("TestPlan_Progeny_1_Days_isCriticalSection", 2);
			int daysListWebElementsSize=daysListWebElements.size();
			if (daysListWebElementsSize==0) {
				msg="Days are not visible.";
				fnpMymsg(msg);
				throw new Exception(msg);
			} 
			String actValue_string;
			int actValue_int;
			String expValue_string;
			int expValue_int;
			for (int i = 0; i < daysListWebElementsSize; i++) {
				actValue_string=daysListWebElements.get(i).getText();
				actValue_int=Integer.parseInt(actValue_string);
				
				expValue_string=String.valueOf(daysList.get(i));
				expValue_int=daysList.get(i);
				if (actValue_int!=expValue_int) {
					msg=" The days should be displayed in ascending numerical order but here they are not in ascending order because at this position ' "+i+"', expected value is '"+expValue_int+"' but actual value is '"+actValue_int+"'.";
					fnpMymsg(msg);
					throw new Exception(msg);
					
				}
				
			}
			
			msg="The days are displayed in ascending numerical order .";
			fnpMymsg(msg);
			fnpDisplayingMessageInFrame(msg, 10);
			Thread.sleep(2000);
			
			// now going to check their colors
			String classValue;
			//boolean found=false;
			for (int i = 0; i < criticalDaysList.size(); i++) {
				//found=false;
				for (int j = 0; j < daysListWebElementsSize; j++) {
					
					if (isAttribtuePresent(daysListWebElements.get(j), "class")) {
						classValue=daysListWebElements.get(j).getAttribute("class");
						actValue_string=daysListWebElements.get(j).getText();
						actValue_int=Integer.parseInt(actValue_string);
						
						if (criticalDaysList.get(i)==actValue_int) {
							if (classValue.contains("bold-label red-color")) {
								//fnpMymsg("This value '"+actValue_int+"' is bold and its color is red.");									
								msg="This value '"+actValue_int+"' is bold and its color is red.";
								fnpMymsg(msg);
								fnpDisplayingMessageInFrame(msg, 10);
								
								
								break;
							}else{
								msg="This critical days value '"+actValue_int+"' is either not bold or its color is not red.";
								fnpMymsg(msg);
								throw new Exception(msg);
							}
							
						}
						
					}
			}
			}

				
			
			
		}
		
		public static boolean isAttribtuePresent(WebElement element, String attribute) {
		    Boolean result = false;
		    try {
		        String value = element.getAttribute(attribute);
		        if (value != null){
		            result = true;
		        }
		    } catch (Exception e) {}

		    return result;
		}
		
		
		
		
		public static void fnpCheckField_IsDisable(String ORName,String key,String fieldName,boolean result) throws Throwable{
			fnpMymsg("                               ");
			fnpMymsg("                               ");
			fnpMymsg("                               ");
			
			fnpMymsg("*************************************************************");
			boolean isDisable=false;
			WebElement wb = null;
			if ( ORName == null || (ORName.equalsIgnoreCase(""))  ) {
				wb = fnpGetORObjectX___NOR_withoutCheckingElementClickable(key);
			}
			if (ORName.equalsIgnoreCase("OR")) {
				wb = fnpGetORObjectX_withoutCheckingElementClickable(key);
			}
			
			String classValue=wb.getAttribute("class").trim();
			
			
			
			
			try{
				
				//ui-inputfield ui-inputtextarea ui-widget ui-state-default ui-corner-all ui-state-disabled font-normal width-450 languageheight120 ui-inputtextarea-resizable
				if (classValue.contains("ui-state-disabled")) {
					fnpMymsg("This field '"+fieldName+"' is disable because its class attribute value is '"+classValue+"'.");					
					isDisable=true;
					//return isDisable;				
				}else{
					fnpMymsg("This field '"+fieldName+"' is not disable because its class attribute value is '"+classValue+"'.");					
				}
			}catch(Throwable t){
				fnpMymsg("This field '"+fieldName+"' is does not have class attribute.");
			}
			

			try{
				String disableValue=wb.getAttribute("disabled").trim();
				if ((disableValue.equalsIgnoreCase("disabled"))  || (disableValue.equalsIgnoreCase("true")) ) {
					fnpMymsg("This field '"+fieldName+"' is disable because its disabled attribute value is '"+disableValue+"'.");
					isDisable=true;
					//return isDisable;				
				}else{
					fnpMymsg("This field '"+fieldName+"' is not disable because its disabled attribute value is '"+disableValue+"'.");
				}
			}catch(Throwable t){
				fnpMymsg("This field '"+fieldName+"'  does not have disabled attribute.");
			}
			
			
			
			try{
				String areaDisableValue=wb.getAttribute("aria-disabled").trim();
				if (areaDisableValue.equalsIgnoreCase("true")) {
					fnpMymsg("This field '"+fieldName+"' is disable  because its 'aria-disabled' attribute value is '"+areaDisableValue+"'.");
					isDisable=true;
					//return isDisable;
				}else{
					fnpMymsg("This field '"+fieldName+"' is not disable because its 'aria-disabled' attribute value is '"+areaDisableValue+"'.");
				}
			}catch(Throwable t){
				fnpMymsg("This field '"+fieldName+"'  does not have 'aria-disabled' attribute.");
			}
			

			
			
			
			try{
				String readOnlyAttributeValue=wb.getAttribute("readonly").trim();
				boolean readOnlyAttributValue_boolean= Boolean.parseBoolean(readOnlyAttributeValue);
				//if (readOnlyAttributeValue.contains("readonly")) {
				if (readOnlyAttributValue_boolean) {
					fnpMymsg("This field '"+fieldName+"' is disable because  its readOnly attribute value is 'true'");
					isDisable=true;
					//return isDisable;
				}else{
					fnpMymsg("This field '"+fieldName+"' is NOT disable because  its readOnly attribute value is not 'true'");
				}
			}catch(Throwable t){
				fnpMymsg("This field '"+fieldName+"'  does not have 'readonly' attribute.");
			}
			
			
			
			
			
			
			
			
			

			//if (result==true) {
			if (result) {
				if (isDisable==result) {
					msg="Hence,this field '"+fieldName+"' is  disable as expected. ";
					fnpMymsg(msg);
				} else{
					msg="Hence,this field '"+fieldName+"' is NOT disable. ";
					fnpMymsg(msg);
					throw new Exception(msg);
				}
					
			} else {
				if (isDisable==result) {
					msg="Hence,this field '"+fieldName+"' is  enable as expected. ";
					fnpMymsg(msg);
				} else{
					msg="Hence,this field '"+fieldName+"' is NOT enabled. ";
					fnpMymsg(msg);
					throw new Exception(msg);
				}
			}

			fnpMymsg("*************************************************************");


			
			//return isDisable;
			
			
		}
	 
		
		
		
		

		// Function to check dcc is present or not for this given value
		public static int fnpCheckThisValueHasDCCPresentOrNot(String facilityName) throws Throwable {
			//String rate = null;

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
			
				connection=fnpGetDBConnection();//donep
				
				Statement stmt = connection.createStatement();


				
				//String updateQuery1 = String.format("UPDATE IQ_APPLICATIONS  SET APP_STATUS = 'DROP'  WHERE APP_NO IN   (SELECT B.APP_NO FROM IQ_APP_CSX_XREF a,IQ_APPLICATIONS B WHERE CUSSX_SEQ IN (SELECT seq from CUS_STD_XREF WHERE STD_CODE = '%s' 	AND CUS_SEQ = (SELECT SEQ FROM CUSTOMERS WHERE CODE = '%s' ))	AND A.APP_NO = B.APP_NO AND B.APP_STATUS <> 'DROP')",facilityName);
				String query1 = String.format("SELECT count (DISTINCT PTI.DCC) total_dcc FROM ITEM_PLANT_STDS IPS,NUCLEUS.CUSTOMERS CUS,ITEMS PTI,ITEM_DOCS ITD where CUS.SEQ = PTI.CUS_SEQ AND IPS.ITM_SEQ = PTI.SEQ AND ITD.IPS_SEQ = IPS.SEQ and ips.cus_seq= (select seq from customers where code='%s')",facilityName);


				//int valuePresent = stmt.executeQuery(query1)
				ResultSet rs = stmt.executeQuery(query1);
				String stringValuePresent="";
				 while (rs.next()){
					  stringValuePresent =rs.getString("total_dcc"); 
				 }
				
				int valuePresent=Integer.parseInt(stringValuePresent);
				fnpMymsg("@  ---DCCs count present for this value is '"+facilityName+"' --" + valuePresent);
				//connection.commit();


				

				connection.close();
				fnpMymsg("******************");
				
				
				return valuePresent;

			} catch (SQLException e) {
				fnpMymsg("*********************************************** Either connection Failed or some different error with Database,   Getting Error >>  " + e.getMessage().trim());
				fnpMymsg("=========================================================================================================================================");
				e.printStackTrace();
				throw new Exception(e.getMessage());
			}
		

			// return rate;
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		public static void fnpWaitTillThisPaginationIconGetsDisabled(String OrName) throws Throwable{
			String xpath_parentAnchorTab=OR.getProperty(OrName)+"/..";
			String classOfThisxpath=fnpGetORObjectX___NOR(xpath_parentAnchorTab).getAttribute("class");
			int iWhleCounter=0;
			while(true){
				iWhleCounter++;
				if (classOfThisxpath.contains("ui-state-disabled")) {
					break;
					
				}else{
					Thread.sleep(1000);
					if (iWhleCounter>Integer.parseInt(CONFIG.getProperty("implicitWaitTime"))) {
						msg="This icon '"+OrName+"' is not getting disabled after clicking it and waited for approx "+iWhleCounter+" seconds";
						fnpMymsg(msg);
						throw new Exception(msg);
					}
				}
			}
		}
		
		
		public boolean  fnpCheckElementIsEnabled_usingForPaginationIconOnly(String OrName) throws Throwable{
			
			boolean enable=false;
			String xpath_parentAnchorTab=OR.getProperty(OrName)+"/..";
			String classOfThisxpath=fnpGetORObjectX___NOR(xpath_parentAnchorTab).getAttribute("class");
			
			if (classOfThisxpath.contains("ui-state-disabled")) {
				enable=false;						
			}else{
				enable=true;
			}
			return enable;
		}
		
		
		
		
}

























