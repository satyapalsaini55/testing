package nsf.ecap.Generic_suite;

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

public class TestSuiteBase_Generic_suite extends TestSuiteBase {

	
	public static String runmodes[] = null;
	public static int count = -1;
	


	// check if the suite has to be skipped or not
	@BeforeSuite
	public void checkSuiteSkip() throws Throwable {

		fnInitialize();


		setCurrentSuiteName(Generic_suite_Name);
		currentSuiteCode = "Generic";
		//currentSuiteXls = Dietary_Supplement_suitexls;
		setCurrentSuiteExcel(Generic_suitexls);
		
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
	

		
	 public void fnpCommonCode_ClickWOFromItemNotesAndDocumentsTable_GoToDocumentTab_AddDoc_Upload_download_GoToTaskTab_clickTaskNo_GoToDocumentTab_AddDoc_Upload_download(String aiType,String copyFileName_withCompletePath,String copyFileNameOnly) throws Throwable{
			
			
			
			if (aiType==null) {
				aiType="";
			}
			
			String xpathForFirstRowFirstWOlink_xpath="";//OR.getProperty("ItemNotesAndDocuments_dataTable")+"/tr[1]/td["+woColNo+"]/a[1]";
			if (aiType.equalsIgnoreCase(cerDecisionAIName)) {
				xpathForFirstRowFirstWOlink_xpath=OR.getProperty("WorkOrderNoInViewActionItem");
			}else{
				if (aiType.equalsIgnoreCase(transferReviewAIName)) {
					int woColNo = fnpFindColumnIndex2("TransferReviewAI_ItemNotesAndDocuments_headerTable", "Work Order");
					xpathForFirstRowFirstWOlink_xpath=OR.getProperty("TransferReviewAI_ItemNotesAndDocuments_dataTable")+"/tr[1]/td["+woColNo+"]/a[1]";
				}else{
					int woColNo = fnpFindColumnIndex2("ItemNotesAndDocuments_headerTable", "Work Order");
					xpathForFirstRowFirstWOlink_xpath=OR.getProperty("ItemNotesAndDocuments_dataTable")+"/tr[1]/td["+woColNo+"]/a[1]";
				}

			}
			WebElement wb=fnpGetORObjectX___NOR(xpathForFirstRowFirstWOlink_xpath);
			String woNo=wb.getText().trim();
		
			
/*			
			if (aiType .equalsIgnoreCase(technicalReviewAIName)) {
				driver.quit();
				fnpLaunchBrowserAndLogin();
				fnpSearchWorkOrderOnly(woNo);
			}else{
				fnpMymsg("Going to Click on the Work Order link '"+woNo+"' in the Item Notes and Documents section.");
				fnpClick_NOR(xpathForFirstRowFirstWOlink_xpath);
			}
			
			*/
			
			
			
			fnpMymsg("Going to Click on the Work Order link '"+woNo+"' in the Item Notes and Documents section.");
			fnpClick_NOR(xpathForFirstRowFirstWOlink_xpath);
			
			
			
			
			String woNOAtOpenedscreen=fnpGetText_OR("TopBanner_WoNo_xpath");
			Assert.assertEquals(woNo, woNOAtOpenedscreen, "Correct WO screen should be displayed on clicking wo link but here script clicked "
					+ "this wo '"+woNo+"' but this wo '"+woNOAtOpenedscreen+"' is getting opened.");
			
			
			fnpClick_OR("ISR_Documents_Tab");
			
			
			//fnpClick_OR("EditWOBtn");
/*			if (fnpCheckElementPresenceImmediately("EditWOBtn")) {
				fnpClick_OR("EditWOBtn");
			}
			
			*/
			
			
			if (aiType .equalsIgnoreCase(technicalReviewAIName)) {
				
				if (fnpCheckElementPresenceImmediately("EditWOBtn")) {
					fnpClick_OR("EditWOBtn");
				}else{
					driver.quit();
					fnpLaunchBrowserAndLogin();
					fnpSearchWorkOrderOnly(woNo);
					fnpClick_OR("ISR_Documents_Tab");
					if (fnpCheckElementPresenceImmediately("EditWOBtn")) {
						fnpClick_OR("EditWOBtn");
					}
				}
				
				
			}else{
				if (fnpCheckElementPresenceImmediately("EditWOBtn")) {
					fnpClick_OR("EditWOBtn");
				}
			}
			
			
			

			
			
			
			
			fnpClick_OR("WOAddDocAddBtn");
			fnpLoading_wait();
			
			fnpWaitTillVisiblityOfElementNClickable_OR("ISR_DocTab_AddWODoc_SaveNCloseBtn");
			fnpWaitForVisible("ISR_DocTab_AddWODoc_SaveNCloseBtn");
		//	Thread.sleep(2000);


			fnpGetORObjectX("ISR_DocTab_AddWODoc_DescTxtBx").click();
					
			//driver.findElement(By.xpath(OR.getProperty("ISR_DocTab_AddWODoc_BrowseBtn"))).sendKeys(copyFileName_withCompletePath);
			
			//Thread.sleep(2000);
			//fnpBrowseLoading();
			fnpPFListByLiNo("DocTab_AddWODoc_TypePFList", "DocTab_AddWODoc_TypePFListOptions", 1);
			fnpPFListByLiNo("DocTab_AddWODoc_AccessPFList", "DocTab_AddWODoc_AccessPFListOptions", 1);
			
			driver.findElement(By.xpath(OR.getProperty("ISR_DocTab_AddWODoc_BrowseBtn"))).sendKeys(copyFileName_withCompletePath);
			fnpBrowseLoading();
			fnpBrowseLoading();
			
			fnpClickInDialog_OR("ISR_DocTab_AddWODoc_SaveNCloseBtn");
			fnpWaitTillThisElementIsNoLongerDisplayedTillMaxWait_OR("ISR_DocTab_AddWODoc_SaveNCloseBtn", "ISR_DocTab_AddWODoc_SaveNCloseBtn is not getting over after clicking it.", 60*5);
			
			
			int documentsColNo = fnpFindColumnIndex2("DocumentTab_WorkOrderDocument_headerTable", "Document");
			
			String xpathForFirstRowDocName_xpath=OR.getProperty("DocumentTab_WorkOrderDocument_dataTable")+"/tr[1]/td["+documentsColNo+"]/a[contains(@id,':woDnldCmdLink')]";
			wb=fnpGetORObjectX___NOR(xpathForFirstRowDocName_xpath);
			String docName=wb.getText().trim();
			Assert.assertEquals(docName, copyFileNameOnly, "Document should be successfully uploaded and should be displayed on the top but here on tob "
					+ "this file name  '"+docName+"' is present but we were expecting this '"+copyFileNameOnly+"' file name.");
			
			
			fnpCheckFileIsDownloadedOrNotIniPulse(xpathForFirstRowDocName_xpath) ;
			fnpCheckError("");
			fnpClick_OR("FirstDocDeleteIcon_workorderdocumentTable_at_ISRDocumentsTab");
			fnpClick_OR("FirstDocDelete_workorderdocumentTable_Confirmation_Yes_button");
			xpathForFirstRowDocName_xpath=OR.getProperty("DocumentTab_WorkOrderDocument_dataTable")+"/tr[1]/td["+documentsColNo+"]/a[contains(@id,':woDnldCmdLink')]";
			if (fnpCheckElementDisplayedImmediately_NOR(xpathForFirstRowDocName_xpath)) {
				wb=fnpGetORObjectX___NOR(xpathForFirstRowDocName_xpath);
			    docName=wb.getText().trim();
			    
/*							Assert.assertEquals(docName, copyFileNameOnly, "Document should be successfully uploaded and should be displayed on the top but here on tob "
						+ "this file name  '"+docName+"' is present but we were expecting this '"+copyFileNameOnly+"' file name.");
				*/
				if (docName.equalsIgnoreCase( copyFileNameOnly)) {
					msg="Our uploaded file '"+copyFileNameOnly+"' is Not getting deleted successfully.";
					fnpMymsg(msg);
					throw new Exception(msg);
				} else {
					fnpMymsg("Our uploaded file '"+copyFileNameOnly+"' is deleted successfully.");
				}

			}
			
			
			if (!(aiType .equalsIgnoreCase(transferReviewAIName))) {
				
				
				fnpClick_OR("ISRTaskTab");
				
				int taskAINoColIndex = fnpFindColumnIndex("TaskTabTable_HeaderRow", WOISOTaskTab_TaskAISummaryTableColName_TaskAINo);
				//String taskNoValue = fnpFetchFromTable("TaskTabTable", 1, taskAINoColIndex);
				int totalrowsInTaskTab = fnpCountRowsInTable("TaskTabTable");
				String task_OR_AINoValue = null;
				String foundTask=null;
				String firstCharacter=null;
				for (int i = 0; i < totalrowsInTaskTab; i++) {
					task_OR_AINoValue = fnpFetchFromTable("TaskTabTable", (i+1), taskAINoColIndex);
					 firstCharacter=task_OR_AINoValue.substring(0, 1);
					 if (firstCharacter.equalsIgnoreCase("T")) {
						fnpMymsg("This is our task no. '"+task_OR_AINoValue+"' which we are going to click");
						foundTask=task_OR_AINoValue;
						break;
					}
				}
				
				
				
				if (foundTask==null) {
					msg="Not a single task is present at this task tab, so going to skip it";
					fnpMymsg(msg);
				//	throw new Exception(msg);
				}else{
					fnpMymsg("Going to click this task no. --'"+foundTask+"'.");
					fnpClickALinkInATable(foundTask.trim());
					fnpClick_OR("ISR_Task_Documents_Tab");
					fnpClick_OR("EditWOBtn");
					fnpClick_OR("ISR_Task_Documents_Tab_Add_button");
					fnpLoading_wait();
					
					fnpWaitTillVisiblityOfElementNClickable_OR("ISR_Task_Documents_Tab_AddDoc_SaveNCloseBtn");
					fnpWaitForVisible("ISR_Task_Documents_Tab_AddDoc_SaveNCloseBtn");
					//Thread.sleep(2000);
					//fileName = System.getProperty("user.dir") + "\\docs\\" + (String) hashXlData.get("Upload_fileName");
					fnpGetORObjectX("ISR_Task_Documents_Tab_AddDoc__DescTxtBx").click();
					//driver.findElement(By.xpath(OR.getProperty("ISR_Task_Documents_Tab_browse_btn"))).sendKeys(copyFileName_withCompletePath);
					//Thread.sleep(2000);
					//fnpBrowseLoading();
					fnpPFListByLiNo("ISR_Task_Documents_Tab_AddDoc_TypePFList", "ISR_Task_Documents_Tab_AddDoc_TypePFListOptions", 1);
					fnpPFListByLiNo("ISR_Task_Documents_Tab_AddDoc_AccessPFList", "ISR_Task_Documents_Tab_AddDoc_AccessPFListOptions", 1);
					
					driver.findElement(By.xpath(OR.getProperty("ISR_Task_Documents_Tab_browse_btn"))).sendKeys(copyFileName_withCompletePath);
					fnpBrowseLoading();
					
					fnpClickInDialog_OR("ISR_Task_Documents_Tab_AddDoc_SaveNCloseBtn");						
					
					
					
					
					documentsColNo = fnpFindColumnIndex2("ISR_Task_Documents_Tab_TaskDocuments_headerTable", "Document");
					
					xpathForFirstRowDocName_xpath=OR.getProperty("ISR_Task_Documents_Tab_TaskDocuments_dataTable")+"/tr[1]/td["+documentsColNo+"]/a[contains(@id,':isrTaskDocsDT:0:taskISRDwnldCmdLink')]";
					wb=fnpGetORObjectX___NOR(xpathForFirstRowDocName_xpath);
					docName=wb.getText().trim();
					Assert.assertEquals(docName, copyFileNameOnly, "Document should be successfully uploaded and should be displayed on the top but here on tob "
							+ "this file name  '"+docName+"' is present but we were expecting this '"+copyFileNameOnly+"' file name.");
					
					
					fnpCheckFileIsDownloadedOrNotIniPulse(xpathForFirstRowDocName_xpath) ;
					fnpCheckError("");
					
					
					
					fnpClick_OR("ViewTaskScreen_DocumentsTab_DeleteIcon");
					fnpClick_OR("ViewTaskScreen_DocumentsTab_DeleteIcon_Confirmation_Yes_button");
					xpathForFirstRowDocName_xpath=OR.getProperty("ISR_Task_Documents_Tab_TaskDocuments_dataTable")+"/tr[1]/td["+documentsColNo+"]/a[contains(@id,':isrTaskDocsDT:0:taskISRDwnldCmdLink')]";
					if (fnpCheckElementDisplayedImmediately_NOR(xpathForFirstRowDocName_xpath)) {
						wb=fnpGetORObjectX___NOR(xpathForFirstRowDocName_xpath);
					    docName=wb.getText().trim();
					    
		/*							Assert.assertEquals(docName, copyFileNameOnly, "Document should be successfully uploaded and should be displayed on the top but here on tob "
								+ "this file name  '"+docName+"' is present but we were expecting this '"+copyFileNameOnly+"' file name.");
						*/
						if (docName.equalsIgnoreCase( copyFileNameOnly)) {
							msg="Our uploaded file '"+copyFileNameOnly+"' is Not getting deleted successfully.";
							fnpMymsg(msg);
							throw new Exception(msg);
						} else {
							fnpMymsg("Our uploaded file '"+copyFileNameOnly+"' is deleted successfully.");
						}

					}

					
				}
				
				
				
			
			}
			
	
			

			
			
			
	 }
	 
	 
	 
	 public static void fnpCommonSearch(String programName,String searchLinkName,String filterNameIfAny,String filterValueIfAny,
			 							String searchFieldOR,String searchColName,String viewSearchTopHeadingOR,String constantViewSearchHeading) throws Throwable{

		//	fnpCommonClickTopMainMenu_advance("Menu","FirstRefrenceLinkJustBelowToMenu",programLinkName,searchFieldOR);
			fnpCommonClickTopMainMenu("Menu","FirstRefrenceLinkJustBelowToMenu",searchLinkName,searchFieldOR);
			
			fnpCommonSearch_withoutClickingTopMenu( programName, searchLinkName, filterNameIfAny, filterValueIfAny,
						 searchFieldOR, searchColName, viewSearchTopHeadingOR, constantViewSearchHeading);

			
	 }
	 
	 
	 
	 
	 
	 
	 public static void fnpCommonSearch_withoutClickingTopMenu(String programName,String searchLinkName,String filterNameIfAny,String filterValueIfAny,
			 							String searchFieldOR,String searchColName,String viewSearchTopHeadingOR,String constantViewSearchHeading) throws Throwable{


			
			fnpMouseHover("FooterElement");	
				
			fnpWaitTillVisiblityOfElementNClickable_OR("MainSearchButton");
			fnpWaitForVisible("MainSearchButton");	
				
			if ( (programName.trim().equalsIgnoreCase(generic_suite_basic_search_func_searchWo_isr_test_name)))  {	// second time for only ISR work order  type
				//String value="ISR-NSF International Strategic Registrations, Ltd.";	
				if (!(filterValueIfAny.equalsIgnoreCase(""))) {
					fnpSelectingValueInOrgUnitInIpulse( filterValueIfAny);
				}
			

			}
			
			
			if (programName.trim().equalsIgnoreCase(generic_suite_basic_search_func_searchTask_test_name)) {				
				fnpMultipleSelectDropDown2("WorkOrderTypeMultipleSelectDropDown_defaultSearch", "New Client-New Product");
			}
			
			
			//fnpCheckErrorUsingPageSource();
			//fnpCheckError("");
			
			fnpClick_OR("MainSearchButton");


	
			String noToBeSearch = fnpFetchFromStSearchTable(1, 1);
			int j = 0;
		//	fnpLoading_wait();
			while (noToBeSearch.contains("No records found") & j < 15) {
				j++;
				Thread.sleep(1000);
				noToBeSearch = fnpFetchFromStSearchTable(1, 1);
			}
			
		//	noToBeSearch= fnpFetchFromStSearchTable(1, searchColName);
			
			
			noToBeSearch = fnpFetchFromStSearchTable(1, 1);
			if (noToBeSearch.toLowerCase().contains("no records found"))  {
				fnpMymsg(" Default search does not retrieve any records as it is showing 'No records found' after clicking Search button, hence search is failed. " );
				throw new Exception("Default search does not retrieve any records as it is showing 'No records found' after clicking Search button. ");
			}
			
			
			String afterRemovingSearchFromTestCaseName=programName.replace("Search", "");
			
			fnpMymsg(" Picked first "+afterRemovingSearchFromTestCaseName+" no. from the default search table to be searched and currently it is ---"+noToBeSearch +" .  ");
			fnpType("OR", searchFieldOR, noToBeSearch);
			fnpMymsg("Just after inserting value in "+searchFieldOR);
			fnpClick_OR("MainSearchButton");
			
			
			int countRows=fnpCountRowsInTable("StandardSearchTable");
			
			j=1;
			while (countRows < 1 ) {
				if (j>15) {
					break;
				}
				j++;
				Thread.sleep(1000);
				countRows=fnpCountRowsInTable("StandardSearchTable");
			}
			
		
			
			
			
			
			String searchedResult = fnpFetchFromStSearchTable(1, 1);
			if (searchedResult.toLowerCase().contains("no records found"))  {
				fnpMymsg(" It is showing 'No records found'  after clicking Search button, hence search is failed. ") ;
				throw new Exception("It is showing 'No records found' after clicking Search button. ");
			}
			
			
			
			
			if (countRows<1)  {
				fnpMymsg(programName +" is not run successfully as retrieved "+countRows+" records .");
				throw new Exception (programName +" is not run successfully as retrieved "+countRows+" records .");
			}
			
			
			
			
			
			
			
			
			String s =null;
			String firstResult=null;
			for (int i = 1; i < countRows +1; i++) {
				
				s= fnpFetchFromStSearchTable(i, searchColName);
				fnpMymsg( i +" row  for column name '"+searchColName+"' has value --- i.e. --'"+s+"'. ");
			//	Assert.assertTrue(s.contains(noToBeSearch), i +"  row  for column name '"+searchColName+"' i.e. --'"+s+"' does not contain '"+noToBeSearch+"'. ");
				
				if(i==1){
					firstResult=s;
				}
				if (!(s.contains(noToBeSearch))) {
					
					fnpMymsg(programName +" is not run successfully as retrieved results are not proper because " + i +" row  for column name '"+searchColName+"' i.e. --'"+s+"' does not contain '"+noToBeSearch+"'. ");
					throw new Exception (programName +" is not run successfully as retrieved results are not proper because " + i +" row  for column name '"+searchColName+"' i.e. --'"+s+"' does not contain '"+noToBeSearch+"'. ");
				}
			}
			
			
			
			
			
			
			fnpMymsg(programName +" is run successfully as it retrieved the search result successfully.");
			
			fnpMymsg("Now going to click first hyperlinked searched result i.e. '"+firstResult+"'. ");

			
			//String Link_Xpath = "//td[@role='gridcell']/a[text()='"+firstResult+"']";
			String Link_Xpath = "//td/a[contains(@id,'mainForm:stdSearchC:')][text()='"+firstResult+"']";
			//fnpWaitTillVisiblityOfElementNClickable(Link_Xpath);
			fnpWaitTillVisiblityOfElementNClickable_NOR(Link_Xpath);
			driver.findElement(By.xpath(Link_Xpath)).click();
			

			
			
			
		//	fnpMymsg("just after clicking first record");
			fnpLoading_wait();
			fnpCheckError("");

			
			String viewHeading=fnpGetText_OR(viewSearchTopHeadingOR);
			//String viewHeading=fnpGetText_OR("GreenTopheadingOfDetailPage");
			
			
			
			
			
			

/*			
			if (viewHeading.contains(constantViewSearchHeading)) {
				msg="Detail page is opened successfully.";
			} else {
				msg="View "+afterRemovingSearchFromTestCaseName+" Page heading does not contain '" +
						constantViewSearchHeading +"' word, so might be View "+afterRemovingSearchFromTestCaseName+"  page is not opened correctly."
								+ " Expected is  this '"+constantViewSearchHeading+"' and actual is this '"+viewHeading+"'.";
				fnpMymsg(msg);
				throw new Exception(msg);
			}
			
			*/
			
/*			
			String editHeadingIfViewHeadingIsNotPresent=null;
			if (viewHeading.contains(constantViewSearchHeading)) {
				msg="Detail View page is opened successfully.";
			} else {
				editHeadingIfViewHeadingIsNotPresent=constantViewSearchHeading.replace("View", "Edit");
				if (viewHeading.contains(editHeadingIfViewHeadingIsNotPresent)) {
					msg="Detail Edit page is opened successfully.";
				} else {
					msg="View "+afterRemovingSearchFromTestCaseName+" Page heading does not contain '" +
							constantViewSearchHeading +"' word, so might be View "+afterRemovingSearchFromTestCaseName+"  page is not opened correctly."
									+ " Expected is  this '"+constantViewSearchHeading+"' and actual is this '"+viewHeading+"'.";
					fnpMymsg(msg);
					throw new Exception(msg);
				}
			}
			
			*/
			
			
			
			String expectingHeading=null;
			if (programName.contains(generic_suite_basic_search_func_searchWo_water_test_name)) {
				expectingHeading=constantViewSearchHeading.replace("View", "Edit");
			} else {
				expectingHeading=constantViewSearchHeading;
			}
			
			if (viewHeading.contains(expectingHeading)) {
				msg="Detail page is opened successfully.";
			} else {
				msg="View "+afterRemovingSearchFromTestCaseName+" Page heading does not contain '" +
						constantViewSearchHeading +"' word, so might be View "+afterRemovingSearchFromTestCaseName+"  page is not opened correctly."
								+ " Expected is  this '"+expectingHeading+"' and actual is this '"+viewHeading+"'.";
				fnpMymsg(msg);
				throw new Exception(msg);
			}
			
			
			fnpMymsg("After clicking hyperlink searched "+afterRemovingSearchFromTestCaseName+"  no. from searched result page , View "+afterRemovingSearchFromTestCaseName+"  page is opened correctly. ");



			
	 }
	 
	 
	 
	 
	 
	 public static void fnpFindAIUser_SwitchUser_N_SearchAI(String aiNo) throws Throwable{
		 
		 
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
			String user=""; 
			try {
			
				connection=fnpGetDBConnection();//donep
				
				Statement stmt = connection.createStatement();
				
				//Select USERNAME FROM EMPLOYEES WHERE EMP_NO= (SELECT ASSIGNED_EMP_NO FROM AI_ACTION_ITEMS WHERE AI_NO='')
				
				String extractUser_Query = String.format("Select USERNAME FROM EMPLOYEES WHERE EMP_NO= (SELECT ASSIGNED_EMP_NO FROM AI_ACTION_ITEMS WHERE AI_NO='%s')",aiNo);
				
				       
		        ResultSet rs = stmt.executeQuery(extractUser_Query);
		        while (rs.next()) {
		        	user = rs.getString(1);
		        }


				connection.close();

				} catch (SQLException e) {
					fnpMymsg("*********************************************** Either connection Failed or some different error with Database,   Getting Error >>  " + e.getMessage().trim());
					fnpMymsg("=========================================================================================================================================");
					e.printStackTrace();
					throw new Exception(e.getMessage());
				}
			
			
			fnpCommonClickTopMainMenu("Menu", "FirstRefrenceLinkJustBelowToMenu", "SwitchUser_Link", "SwitchUser_Go_btn");		 
			fnpType("OR","SwitchUser_txtBx",user);
			fnpClick_OR("SwitchUser_Go_btn");
			
			
			fnpSearchActionItemsOnly(aiNo);
			
			
			
			
			
			
			
			
		   fnpMymsg("******************");
				
		 
	 }
	 
	 
	 
	 public static String fnpGetUser_fullName(String empNo) throws Throwable{
		 
		 
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
			String user_fullName=""; 
			try {
			
				connection=fnpGetDBConnection();//donep
				
				Statement stmt = connection.createStatement();
				
				//Select USERNAME FROM EMPLOYEES WHERE EMP_NO= (SELECT ASSIGNED_EMP_NO FROM AI_ACTION_ITEMS WHERE AI_NO='')
				
				String extractUser_Query = String.format("Select NAME FROM EMPLOYEES WHERE EMP_NO='%s'",empNo);
				
				       
		        ResultSet rs = stmt.executeQuery(extractUser_Query);
		        while (rs.next()) {
		        	user_fullName = rs.getString(1);
		        }


				connection.close();

				} catch (SQLException e) {
					fnpMymsg("*********************************************** Either connection Failed or some different error with Database,   Getting Error >>  " + e.getMessage().trim());
					fnpMymsg("=========================================================================================================================================");
					e.printStackTrace();
					throw new Exception(e.getMessage());
				}
			
			
				return user_fullName;
			
				
		 
	 }
	 
	 
	 
}

























