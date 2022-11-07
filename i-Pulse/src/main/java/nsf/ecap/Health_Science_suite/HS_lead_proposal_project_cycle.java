package nsf.ecap.Health_Science_suite;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import nsf.ecap.MonitorPlan_Suite.TestSuiteBase_MonitorPlan;
import nsf.ecap.util.ErrorUtil;
import nsf.ecap.util.TestDataProvider;
import nsf.ecap.util.TestUtil;
import nsf.ecap.util.Xls_Reader;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
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

public class HS_lead_proposal_project_cycle extends TestSuiteBase_HealthScience {

	//public Xls_Reader currentSuiteXlsReference;

	// Runmode of test case in a suite
	@BeforeTest
	@Parameters({ "className" })
	public void checkTestSkip(String className) throws Exception {

		//currentSuiteXlsReference = Health_Science_suitexls;
		setCurrentSuiteExcel(Health_Science_suitexls);
		classNameText = className;
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

			if (!TestUtil.isTestCaseRunnable(currentSuiteXls, className)) {
				fnpMymsg("Skipping Test Case" + className + " as runmode set to NO");// logs
				throw new SkipException("Skipping Test Case '" + className + "' as runmode set to NO");// reports
			}

			fnpMymsg("Going to Run test cases of '" + classNameText + "'");
			fnpMymsg("             ");
			fnpMymsg("             ");

			runmodes = TestUtil.getDataSetRunmodes(currentSuiteXls, className);
			fail = false;
			isTestPass = true;

			start = new Date();

			/*
			 * if (!IsBrowserPresentAlready) { IsBrowserPresentAlready =
			 * fnpLaunchBrowserAndLogin(); fnpMymsg(" Browser is launched"); }
			 */
		} catch (SkipException sk) {
			isTestPass = false;
			String stackTrace = Throwables.getStackTraceAsString(sk);
			String errorMsg = sk.getMessage();
			Exception c = new Exception(errorMsg);
			ErrorUtil.addVerificationFailure(c);
			throw new SkipException(errorMsg + " \n\nSo,Skipping Test Case" + className);// reports
		} catch (Throwable t) {
			isTestPass = false;
			fail = true;
			fnpDesireScreenshot(className);
			String stackTrace = Throwables.getStackTraceAsString(t);
			String errorMsg = t.getMessage();
			errorMsg = errorMsg + "\n\n\n\nSee screenshot '" + className + "'\n\n" + stackTrace;
			Exception c = new Exception(errorMsg);
			ErrorUtil.addVerificationFailure(c);
			throw new Exception(errorMsg);// reports

		}
	}

	@Test(dataProviderClass = TestDataProvider.class, dataProvider = "HSleadProposalProjectCycleDataProvider")
	public void HS_flow(Hashtable<String, String> table) throws Throwable {

		fnpMymsg("****************************************************************");
		count++;
		if (!runmodes[count].equalsIgnoreCase("Y")) {
			skip = true;
			fnpMymsg("Runmode for test set data set to no " + (count + 1));
			fnpMymsg("*****************");
			fnpMymsg("");
			throw new SkipException("Runmode for test set data set to no " + (count + 1));
		}

		try {

			start = new Date();
			fnpDeleteTimeSheetFromDB(table.get("Prop_Resource"));

			fnpMymsg("   ---Just before launching browser");
			//fnpLaunchBrowserAndLogin(table.get("HSLoginAsName")); Not using after implementing SSO
			fnpLaunchBrowserAndLogin();
			fnpMymsg(" Browser is launched");
			fnpMymsg("   ---Just after launching browser");

			//Thread.sleep(1000);
		

			String browserName=((RemoteWebDriver)driver).getCapabilities().getBrowserName();
			//((RemoteWebDriver)driver).getCapabilities().getBrowserName();
			
			//String ClientNo_text =fnpCreateClientOnly( table);
			String ClientNo_text =table.get("Client_no");			
			fnpCommoniPulseSearchTestCase("SearchClientLink","CorpFacilityTxtBx",ClientNo_text,"Corp/Fac #","ViewClientPageheading","View Client");
			fnpClick_OR("EditClientBtn");

			
			
/*			
			fnpClick_OR_WithoutWait("ActivateBtn");
			// fnpClick_OR("ActivateConfirmationYesBtn");
			fnpClickInDialog_OR("ActivateConfirmationYesBtn");

			fnpWaitForVisibleHavingMultipleElements("TopMessage3");
			SuccessfulMsg = fnpGetText_OR("TopMessage3");

			Assert.assertTrue(SuccessfulMsg.contains("success"), "Top message does not contain 'success' word, so might be client has not been activated successfully");

			Assert.assertTrue(SuccessfulMsg.contains("ctivated"), "Top message does not contain 'activated' word, so might be client has not been activated successfully");
			fnpMymsg("Client has been activated successfully");
			
			*/
			
			
			
			
			String ClientStatus = fnpGetORObjectX("ClientTopStatusTxt").getText();
			fnpMymsg("Status of client is --" + ClientStatus);

			Assert.assertEquals(ClientStatus, "ACTIVE", "Status of client has not become 'ACTIVE' .");
			fnpMymsg("Assertion passed here as status is 'ACTIVE' ");

			fnpClick_OR("CreateLeadBtn");

			fnpWaitForVisible("ProjectDivision_PFList");
			
			fnpPFList("ProjectDivision_PFList", "ProjectDivision_PFListOptions", table.get("Project_Division"));
			
			fnpLoading_wait();
			//APP-88221 on 15 October,2019
			//fnpPFList("LeadType_PFList", "LeadType_PFListOptions", table.get("LeadType"));
			fnpPFListByLiNo("LeadType_PFList", "LeadType_PFListOptions", 1);

			fnpGetORObjectX("CreateLeadFormBtn").click();

			fnpLoading_wait();

			fnpWaitForVisibleHavingMultipleElements("TopMessage3");
			String SuccessfulMsg = fnpGetText_OR("TopMessage3");

			Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"), "Top message does not contain 'success' word, so might be LEAD has not been created successfully");

			fnpMymsg("LEAD has been created successfully as our assertion has been passed.");
		//	fnpGetORObjectX("EditLeadButton").click();

			fnpMymsg("Going to convert Lead into Proposal.");
			fnpClick_OR("ConvertToProposalBtn");

			
			/**************** Auto fill fields  ***********************/
		//	fnpPFList("ProjectDivisonOnCreateProposal_PFList", "ProjectDivisonOnCreateProposal_PFListOptions", table.get("Project_Division"));			
		//	fnpPFList("ProjectTypeOnCreateProposal_PFList", "ProjectTypeOnCreateProposal_PFListOptions", table.get("LeadType"));
			/******************************************************/
			
			
			
			fnpClick_OR("CreateBtnAtCreateProposalPage");
			
			//Karen told me to comment below code on 09-05-2018

/*

			fnpClick_OR("Prop_FacilityTabLink");
			fnpClick_OR("AssociateNewFacilityStandardBtn");

			fnpMymsg("AssociateNewFacilityStandardBtn button has been clicked");

	

			fnpClick_OR("FacilityCodeLkpBtn");

			fnpSearchNSelectFirstRadioBtn(1, table.get("FacCode"), 2);


			fnpClick_OR("SearchAndAddBtn");

			fnpSearchNSelectFirstCheckBox(1, table.get("StandardCode"), 2);

			fnpClick_OR("SelectAndReturnBtn");



			fnpClick_OR("SaveNCloseBtn");

			fnpMymsg("After clicking Save&Close button");


			fnpLoading_waitInsideDialogBox();
			fnpCheckError(" while adding data in Facility Tab");

			String FirstCelldata = "nothing";
			int wait = 0;
			while (true) {
				wait++;
				Thread.sleep(1000);
				try {
					fnpWaitForVisible("FirstRecordInFacilityTable");
					FirstCelldata = fnpGetText_OR("FirstRecordInFacilityTable");
					fnpMymsg("  ----First Cell data is-->" + FirstCelldata);
				} catch (Exception e) {
					// nothing to do
				}

				
				 * if (FirstCelldata.contains("FRS")) { break; }
				 

				if (FirstCelldata.contains(table.get("Client"))) {
					break;
				}

				if (wait > 5) {
					break;
				}
				Thread.sleep(3000);
			}

			// Assert.assertTrue(FirstCelldata.contains("FRS"),
			// "Facility has not been added as first cell does not contain 'FRS' word");
			Assert.assertTrue(FirstCelldata.contains(table.get("Client")), "Facility has not been added as first cell does not contain '" + table.get("Client") + "' word");

			fnpMymsg(" Adding data in  Facility tab is successfully.");

			
	*/		
			
			
			fnpClick_OR("Proposal_ResourcesTabLink");

			fnpClick_OR("AddResourceBtn");

			fnpClick_OR("ResourceLkpBtn");

			fnpSearchNSelectFirstRadioBtn(1, table.get("Prop_Resource"), 2);

			// fnpGetORObjectX("RatePerHourTxtBx").sendKeys(table.get("Rate_per_Hour"));
			fnpType("OR", "RatePerHourTxtBx", table.get("Rate_per_Hour"));

			// fnpGetORObjectX("CostPerHourTxtBx").sendKeys(table.get("Cost_per_Hour"));
			fnpType("OR", "CostPerHourTxtBx", table.get("Cost_per_Hour"));

			fnpGetORObjectX("SaveFloppyIcon").click();
			fnpLoading_wait();

			// fnpWaitForVisible("TopMessage3");
			fnpWaitForVisibleHavingMultipleElements("TopMessage3");

			fnpCheckError(" while adding data in Proposal Resource tab. ");

			SuccessfulMsg = fnpGetText_OR("TopMessage3");

			Assert.assertTrue(SuccessfulMsg.contains("uccess"),
					"Top message does not contain 'success' word, so might be data resouce has not been added successfully in Proposal Resource tab");

			fnpMymsg(" Adding data in  Proposal Resource tab is successfully done .");

			 fnpClick_OR("ConverToProjectBtn");


			 
			 /*****************IPM-9517****Create screen will not come now onwards from 25-10-2018******************
			fnpWaitForVisible("CreateProjectLabel");
			fnpMymsg(" Create Project screen is opened now .");
			fnpClick_OR("CreateBtnAtCreateProposalPage");

			******************************************************************/
			
			
			
			fnpCheckError(" while convert to project after clicking create button. ");

			// fnpWaitForVisible("TopMessage3");
			fnpWaitForVisibleHavingMultipleElements("TopMessage3");
			SuccessfulMsg = fnpGetText_OR("TopMessage3");

			Assert.assertTrue(SuccessfulMsg.contains("uccess"), "Top message does not contain 'success' word, so might be proposal not converted to Project successfully .");

			fnpMymsg("Project has been created successfully .");

			// fnpGetORObjectX("EditButtonAtViewProjectScreen").click();
			
			
			/************new changes 20-03-2017 ************************/	
			//fnpPFList("PrimaryProjectContact_PFList", "PrimaryProjectContact_PFListOptions", table.get("LeadType"));
			fnpPFListByLiNo("PrimaryProjectContact_PFList", "PrimaryProjectContact_PFListOptions", 2);// first value is "------Select-----"
			/*****************************************************/
			
		/************new changes ************************/	
			fnpType("OR", "ProjectNameAtEditProjectScreen", table.get("Project_Name"));
			fnpClick_OR("HSSaveBtn");
		/*****************************************************/
			
			/*************using hard coded in below code due to urgent delivering this *******************/
			fnpClick_OR("Project_ProjectResourcesTabLink");
			fnpClick_OR("ProjectResources_edit_pencilIcon");
			fnpType("OR", "ProjectResources_hoursAllowed_txtbx", "8.00");
			fnpClick_OR("ProjectResources_BillCodeLookupBtn");
			//fnpSearchNSelectFirstRadioBtn(1, "0511 - Pharma Credit Card Charge", 2);
			fnpSearchNSelectFirstRadioBtn(1, "", 2);
			fnpClick_OR("ProjectResources_edit_saveFloopyIcon");
			fnpWaitForVisibleHavingMultipleElements("TopMessage3");
			/**************************************************************/
			
			
			// String projectNo=fnpGetORObjectX("ProjectNo").getText();
			String projectNo = fnpGetText_OR("ProjectNo");
			fnpMymsg("Newly created project no. is ---" + projectNo);
			
			
			String projectStatus = fnpGetText_OR("ProjectStatus_xpath");
			fnpMymsg("Newly created project status is ---" + projectStatus);
			
			Assert.assertTrue(projectStatus.equalsIgnoreCase("ACTIVE"), "Newly created project status is NOT showing ACTIVE");
			
			
			
			
			
			
	/*		
			
			
			fnpClick_OR("EditFacilityTabLink");

			fnpWaitForVisible("Facility_DataTable");
			int row_beforeAdded = fnpCountRowsInTable("Facility_DataTable");
			fnpClick_OR("AssociateNewFacilityStandardBtnAtProjectPage");

			fnpWaitForVisible("FacilityCodeLkpBtn");

			fnpClick_OR("FacilityCodeLkpBtn");

			fnpSearchNSelectFirstRadioBtn(1, table.get("Project_FacCode"), 2);
			// fnpWithoutSearchSelectFirstRadioBtn();

			// fnpWaitForVisible("SearchAndAddBtn");
			// fnpWaitTillClickable("SearchAndAddBtn");

			fnpLoading_wait();
			fnpClick_OR("SearchAndAddBtn");

			fnpSearchNSelectFirstCheckBox(1, table.get("Project_StandardCode"), 2);

			fnpClick_OR("SelectAndReturnBtn");

			fnpLoading_wait();
			// fnpLoading_wait();

			// fnpClick_OR("SaveNCloseBtn");// As failing in this case loading
			// still present, so might be this loading is not catched
			fnpClickInDialog_OR("SaveNCloseBtn");

			// fnpLoading_wait();
			// Thread.sleep(3000);
			// fnpLoading_wait();
			// fnpLoading_wait();
			// fnpLoading_wait();
			fnpMymsg("After clicking Save&Close button");

			int row_afterAdded = fnpCountRowsInTable("Facility_DataTable");

			int iWhileCounter = 0;
			while (!(row_afterAdded > row_beforeAdded)) {
				Thread.sleep(1000);
				iWhileCounter++;
				row_afterAdded = fnpCountRowsInTable("Facility_DataTable");
				if (iWhileCounter > Integer.parseInt(CONFIG.getProperty("genMax_waitTime")) * 2) {
					break;

				}
			}

			Assert.assertTrue(row_afterAdded > row_beforeAdded, "Facility has not been added to Project successfully.");

			fnpMymsg("Facility has  been added to Project successfully.");

			fnpClick_OR("Project_TaskTabLink");

			fnpClick_OR("Project_AddTaskBtn");

			String[] TaskTypeArray;
			String TaskTypeString = table.get("TaskTypeName");
			if (!TaskTypeString.isEmpty()) {
				TaskTypeArray = (table.get("TaskTypeName")).split(",");

				for (int i = 0; i < TaskTypeArray.length; i++) {
					String val = TaskTypeArray[i];
					String TaskTypeCheckboxXpath = "//td[contains(text(),'" + val + "')]/preceding-sibling::td/div/div[contains(@class,'ui-chkbox-box')]";
					driver.findElement(By.xpath(TaskTypeCheckboxXpath)).click(); //
					// Thread.sleep(2000);
					// Thread.sleep(1000);
					fnpLoading_wait();
					// fnpLoading_wait();
				}

			}

			fnpClick_OR("SaveBtnInAddTaskScreenInEditProject");

			// fnpLoading_wait();
			fnpCheckError(" while saving tasks to Project after clicking save button. ");

			// fnpWaitForVisible("TopMessage3");
			fnpWaitForVisibleHavingMultipleElements("TopMessage3");
			SuccessfulMsg = fnpGetText_OR("TopMessage3");

			Assert.assertTrue(SuccessfulMsg.contains("uccess"), "Top message does not contain 'success' word, so might be tasks have not been added to Project successfully .");

			fnpMymsg("Tasks have been added successfully to Project .");

			fnpMymsg(" Going to add data in Tasks tab.");
			fnpClick_OR("ShowAllLinksHS");

			Thread.sleep(5000);

			fnpWaitForVisible("ScheduleStartDate");

			DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");

			// if value in excel is not present then it will pick current date
			if (table.get("Schedule_Start").trim().isEmpty()) {
				Date date1 = new Date();

				fnpMymsg("  -------start date is---" + dateFormat.format(date1));

				// fnpGetORObjectX("ScheduleStartDate").sendKeys(dateFormat.format(date1).toString());
				fnpType("OR", "ScheduleStartDate", dateFormat.format(date1).toString());
			} else {
				// fnpGetORObjectX("ScheduleStartDate").sendKeys(table.get("Schedule_Start"));
				fnpType("OR", "ScheduleStartDate", table.get("Schedule_Start"));
			}

			fnpLoading_wait();

			// if value in excel is not present then it will pick current date +
			// 5 days
			if (table.get("Schedule_End").trim().isEmpty()) {
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, 5);
				fnpMymsg("  -------End date is---" + cal.getTime());
				String EndDate = dateFormat.format(cal.getTime());
				// fnpGetORObjectX("ScheduleEndDate").sendKeys(EndDate);
				fnpType("OR", "ScheduleEndDate", EndDate);
			} else {
				// fnpGetORObjectX("ScheduleEndDate").sendKeys(table.get("Schedule_End"));
				fnpType("OR", "ScheduleEndDate", table.get("Schedule_End"));
			}

			// fnpLoading_wait();
			String StandardCheckboxXpath = "//td[contains(text(),'" + table.get("StandardCode") + "')]/preceding-sibling::td/input[contains(@type,'checkbox')]";
			driver.findElement(By.xpath(StandardCheckboxXpath)).click();
			fnpLoading_wait();
			// fnpLoading_wait();
			StandardCheckboxXpath = "//td[contains(text(),'" + table.get("Project_StandardCode") + "')]/preceding-sibling::td/input[contains(@type,'checkbox')]";
			driver.findElement(By.xpath(StandardCheckboxXpath)).click();

			// fnpPFList("AssignedResource_PFList",
			// "AssignedResource_PFListOptions", table.get("AssignedResource"));
			fnpPFListByLiNo("AssignedResource_PFList", "AssignedResource_PFListOptions", 2);
			Thread.sleep(1000);

			// fnpPFList("AssignedResource_PFListConsult",
			// "AssignedResource_PFListOptionsConsult",
			// table.get("AssignedResource"));
			fnpPFListByLiNo("AssignedResource_PFListConsult", "AssignedResource_PFListOptionsConsult", 2);
			Thread.sleep(1000);

			// fnpPFList("AssignedResource_PFListFinal",
			// "AssignedResource_PFListOptionsFinal",
			// table.get("AssignedResource"));
			fnpPFListByLiNo("AssignedResource_PFListFinal", "AssignedResource_PFListOptionsFinal", 2);
			Thread.sleep(1000);

			List<WebElement> billingSaveBtns = driver.findElements(By.xpath(OR.getProperty("SaveBtnInOpendTaskinProject")));

			WebDriverWait wait3 = new WebDriverWait(driver, Integer.parseInt(CONFIG.getProperty("genMax_waitTime")));
			int countBillngSaveBtn = billingSaveBtns.size();
			fnpMymsg("No. of save button in task detail page are ---" + countBillngSaveBtn);
			for (int i = 0; i < countBillngSaveBtn; i++) {
				// fnpLoading_wait();
				fnpMymsg(" Going to click save button no. ---" + (i + 1));
				// String oId = "mainForm:hstabView:taskDataTable:" + i +
				// ":updateTaskBtn";
				String oId = "mainForm:hstabView:taskDataTable:" + i + ":updateTask";
				// String idxpath = "//*[@id='" + oId + "']";
				String idxpath = "//*[contains(@id,'" + oId + "')]";

				WebElement wbElement = driver.findElement(By.xpath(idxpath));
				new Actions(driver).moveToElement(wbElement).perform();
				// fnpLoading_wait();

				WebElement element3 = wait3.until(ExpectedConditions.elementToBeClickable(By.xpath(idxpath)));
				fnpMouseHover_NotInOR(idxpath);
				// driver.findElement(By.xpath(idxpath)).click();
				fnpClick_NOR(idxpath);
				Thread.sleep(1000);
				fnpLoading_wait();

				fnpCheckError(" while adding data in Task Tab. ");
				fnpWaitForVisibleHavingMultipleElements("TopMessage3");
				SuccessfulMsg = fnpGetText_OR("TopMessage3");
				Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("uccess"),
						"Top message does not contain 'success' word, so might be adding data in Task Tab is not successful.");
				fnpMymsg("Saved individual message is ---" + SuccessfulMsg);

			}



			fnpMymsg("Going to Info tab to change status from Draft to InProcess. ");
			fnpClick_OR("Project_InfoTabLink");

			fnpWaitForVisible("StatusHS_PFList");
			fnpPFList("StatusHS_PFList", "StatusHS_PFListOptions", table.get("Status"));

			fnpClick_OR("HSSaveBtn");

			// commenting for timebeing
			fnpCheckError(" while changing and saving status from Draft to InProcess. ");

			fnpWaitForVisibleHavingMultipleElements("TopMessage3");
			SuccessfulMsg = fnpGetText_OR("TopMessage3");

			Assert.assertTrue(SuccessfulMsg.contains("uccess"),
					"Top message does not contain 'success' word, so might be status from Draft to InProcess has not been changed successfully .");

			fnpMymsg("Changed the status from Draft to InProcess successfully. ");

			*/
			
			
			
			
			
			
			
			fnpClickAtTopWorkAroundForClickingMenu();
			fnpWaitForVisible_usingLinkNameInOR("Menu");

			fnpMouseHover_LinkNameInOR("Menu");
			Thread.sleep(1000);


			fnpCommonClickTopMainMenu("Menu", "FirstRefrenceLinkJustBelowToMenu", "TimesheetTopLink", "LogNewTimeBtn");

			// fnpLoading_wait();
			fnpClick_OR("LogNewTimeBtn");
			// Thread.sleep(2000);

			fnpClick_OR("TimeTrackedToLKPBtn");

			fnpSearchNSelectFirstRadioBtn(2, projectNo, 2);
			
			
			
			
			
			

			SimpleDateFormat dateFormat = new SimpleDateFormat("d");
			Date date1 = new Date();
			String dayNo = dateFormat.format(date1);
			fnpMymsg("day no is---" + dayNo);

			DateFormat dateFormat2 = new SimpleDateFormat("E");
			fnpMymsg("Day Name---" + dateFormat2.format(date1));
			String firstAlpha = dateFormat2.format(date1).substring(0, 1);
			fnpMymsg("Day firstAlpha is---" + firstAlpha);

			String fullHeader = dayNo + " - " + firstAlpha;
			fnpMymsg("Full header is---" + fullHeader);

			
/*			
			int colNo;
			try{
			 colNo = fnpFindColumnIndex("TimesheetTableHeader", fullHeader);
			}catch(Throwable t){
				String expMessage="Today's date input logged hour box i.e. '"+fullHeader+"' is not present.";
				fnpMymsg(expMessage);
				throw new Exception(expMessage +"\n\n\n   and Exception due to this is --"+t.getMessage());
				
			}
			fnpMymsg("Col no is--" + colNo);

			
			String xpathForTextBoxForLoggingHours = OR.getProperty("TimesheetTable") + "/tr/td[" + colNo + "]//input[contains(@id,':mvlogedHours_input')]";
			//fnpType("", "xpathForTextBoxForLoggingHours", table.get("Logged_Hours"));
			driver.findElement(By.xpath(xpathForTextBoxForLoggingHours)).sendKeys(table.get("Logged_Hours"));
			
*/
			
			//fnpDisplayingMessageInFrame_fnpMymsg("Here we are going to insert log hours in any one day [say in 3rd input box] .",10);
			
			List<WebElement> loggingHoursInputFields = driver.findElements(By.xpath("//input[contains(@id,':mvlogedHours_input')]"));
			//loggingHoursInputFields.get(2).clear();
			//Thread.sleep(2000);
			loggingHoursInputFields.get(2).sendKeys("8");
			Thread.sleep(2000);
			
			fnpDisplayingMessageInFrame_fnpMymsg("Here we have inserted log hours in any one day [say in 3rd input box].",10);
			
			fnpClick_OR("Timesheet_floppySaveIcon");
			
			fnpWaitForVisibleHavingMultipleElements("TopMessage3");
			 SuccessfulMsg = fnpGetText_OR("TopMessage3");
			fnpMymsg("Top Message after clicking floppy save icon (for saving timesheet) ----" + SuccessfulMsg);

			Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"),
					"Top message does not contain 'success' word, so might be saving timesheet is NOT successful.");
			
			

		//	fnpMymsg("Status is --" + timesheetstatus);

			fnpClick_OR("SubmitTimesheetBtn");
			fnpClick_OR("YesBtnOnSubmitTimesheet");
			
			
			
			fnpWaitForVisibleHavingMultipleElements("TopMessage3");
			 SuccessfulMsg = fnpGetText_OR("TopMessage3");
			fnpMymsg("Top Message after clicking SubmitTimesheetBtn ----" + SuccessfulMsg);

			Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"),
					"Top message does not contain 'success' word, so might be submitting timesheet is NOT successful.");
			
			
			
			
			// fnpLoading_wait();
			// Thread.sleep(10000);
		//	timesheetstatus = fnpGetORObjectX("TimesheetStatus").getText();
		//	fnpMymsg("  --status is after clicking SubmitTimesheetBtn--" + timesheetstatus);

		//	Assert.assertEquals(timesheetstatus, "SUBMITTED", "Time sheet has not been submitted successfully.");

			// fnpClick_OR("logOutBtn");
			
			
			
			
			
			 int statuscolNo = fnpFindColumnIndex("TimesheetTableHeader", "Status");
			 String timesheetStatus = fnpFetchFromTable("TimesheetTable", 1, statuscolNo);
			
			 fnpMymsg("Status of timesheet after clicking 'Submit Timesheet' button  is ---" + timesheetStatus);
			 Assert.assertTrue(timesheetStatus.equalsIgnoreCase("SUBMITTED"), "Status of timesheet after clicking 'Submit Timesheet' button should be 'Submitted' but currently it is --"+timesheetStatus);
			
			 fnpMymsg("TimeSheet is submitted successfully now.");
			
			
			 
			 
			 

			
			 /*******if both timesheet submitter and timsheet approvar are same person , then no need to login again  *************/
			if (!(table.get("HSLoginAsName").trim().equalsIgnoreCase(table.get("AdminLogin").trim()))) {
				fnpMymsg("Timesheet submitter and approver are different, so going to login with approver.");
				driver.close();
				fnpLaunchBrowserAndLogin(table.get("AdminLogin"));
			} else {
				//no need to logout and login again as timesheet submitter and timesheet approvar both are same person
				fnpMymsg("Timesheet submitter and approver are same person, so going to home page.");
				fnpCommonGoToHomePage();
			}
			


			String consultantCode = table.get("Prop_Resource"); // it is code of HSLoginAsName or timesheet submitter
																

			// -------Action_item_assigned_to_client -------------------
			fnpCommonAlertGeneratedVerificationHS(table, "Timesheets_submitted_waiting_for_Approval", "Timesheets_submitted_waiting_for_Approval_AlertTable_header",
					"Consultant Code", "Timesheets_submitted_waiting_for_Approval_AlertTable", "Timesheets_submitted_waiting_for_Approval_Alert_ConsultantCode_filterTxtBox",
					consultantCode);
			// ---------------------------------------------

			driver.findElement(By.linkText(consultantCode)).click();
			fnpLoading_wait();
			
			fnpWaitForVisible("Timesheet_ChkBoxOnApproverLogin");
			fnpGetORObjectX("Timesheet_ChkBoxOnApproverLogin").click();
			
			
			
			

			fnpClick_OR("TimesheetApproveBtn");


			Thread.sleep(1000);
			fnpClick_OR("ApproveConfirmationYesBtn");

			int col = fnpFindColumnIndex("MgrTimesheetTableHeader", "Status");
			String status = fnpFetchFromTable("MgrTimesheetTable", 1, col);
			fnpMymsg("  --status is after click approve button--" + status);

			Assert.assertEquals(status, "APPROVED", "Time sheet has not been approved successfully.");

			
			
			
/*			driver.close();
			fnpLaunchBrowserAndLogin(table.get("HSLoginAsName"));
		*/	
			
			 /*******if both timesheet submitter and timsheet approvar are same person , then no need to login again to verify approved or not *************/
			if (!(table.get("HSLoginAsName").trim().equalsIgnoreCase(table.get("AdminLogin").trim()))) {
				fnpMymsg("Timesheet submitter and approver are different, so going to login with approver.");
				driver.close();
				fnpLaunchBrowserAndLogin(table.get("HSLoginAsName"));
				fnpCommonClickTopMainMenu("Menu", "FirstRefrenceLinkJustBelowToMenu", "TimesheetTopLink", "TimesheetTableHeader");
			} else {
				//no need to logout and login again as timesheet submitter and timesheet approvar both are same person
			
			
			}
			
			

		

			// fnpLoading_wait();

			col = fnpFindColumnIndex("TimesheetTableHeader", "Status");
			status = fnpFetchFromTable("TimesheetTable", 1, col);
			fnpMymsg("  --status is after click approve button when login as'" + table.get("HSLoginAsName") + "' is --" + status);

			Assert.assertEquals(status, "APPROVED", "Time sheet has not seen approved when Login as'" + table.get("HSLoginAsName") + "'");

			
			
			
			
			
			int projectSummarycol = fnpFindColumnIndex("TimesheetTableHeader", "Project Summary");
			String projectSummary = fnpFetchFromTable("TimesheetTable", 1, projectSummarycol);
			fnpMymsg("Project Summary is ---"+projectSummary);
			try{
			projectNo=projectSummary.split("-")[0].trim();
			fnpMymsg("Project no. is ---"+projectNo);
			}catch(Throwable t){
				fnpMymsg("Exception in extracting project no from Project Summary column  and exception thrown is ---"+t.getMessage());
				throw new Exception("Exception in extracting project no from Project Summary column  and exception thrown is ---"+t.getMessage());
			}
			
			fnpCommonClickTopMainMenu("Menu", "FirstRefrenceLinkJustBelowToMenu", "SearchProjectTopLink", "ProjectNoTxtBox_id");
			
			
			fnpGetORObjectX("ProjectNoTxtBox_id").sendKeys(projectNo);
			fnpClick_OR("MainSearchButton_id");
			
			String s = fnpFetchFromStSearchTable(1, 1);
			int j = 0;
			// fnpLoading_wait();
			while (s.contains("No records found") && j < 15) {
				j++;
				Thread.sleep(1000);
				s = fnpFetchFromStSearchTable(1, 1);
			}

/*			fnpWaitTillVisiblityOfElementNClickable_NOR(".//a[text()='" + s + "']");
			driver.findElement(By.linkText(s)).click();
			*/
			
			fnpClickALinkInATable(s);

			fnpLoading_wait();
			fnpIpulseDuringLoading();
			
			fnpClick_OR("EditButtonAtViewProjectScreen");
			fnpClick_OR("HSFinancialTabLink");
			
			
			//fnpPFList("HS_InvoiceBillTO_PFList", "HS_InvoiceBillTO_PFListOptions", "");
			fnpPFListByLiNo("HS_InvoiceBillTO_PFList", "HS_InvoiceBillTO_PFListOptions", 2);
			fnpClick_OR("HS_SaveBtn");
			
			
			
			fnpClick_OR("HSFinancialTab_AddDraftInvoiceBtn_id");
			
			
			fnpWaitForVisible("ProfessionalServiceHoursTable");
			
			int ratecol = fnpFindColumnIndex("ProfessionalServiceHoursTableHeader", "Rate");
			String rateValue = fnpFetchFromTable("ProfessionalServiceHoursTable", 1, ratecol);
			
			
			//rateValue = rateValue.split("\\$")[1].trim();
			if (rateValue.contains("$")) {		
				//rateValue = rateValue.split("\\$")[1].trim();
				
				throw new Exception("Currency symbol '$' is  present in 'Rate' column. ");
			} else {
				//throw new Exception("Currency symbol '$' is not present in 'Rate' column. ");
			}
			
			
			
			
			
			double rateValueGivenInExcel = Double.parseDouble(table.get("Rate_per_Hour").trim());
			fnpMymsg("Rate given in excel is ----"+rateValueGivenInExcel);			
			double rateValueGivenAtGenerateInvoiceScreen = Double.parseDouble(rateValue);
			fnpMymsg("Rate shown/displayed at Generate Invoice Screen is ----"+rateValueGivenAtGenerateInvoiceScreen);
			
			if (rateValueGivenInExcel==rateValueGivenAtGenerateInvoiceScreen) {
				fnpMymsg("Rate given in excel is matched to rate shown/displayed at Generate Invoice Screen");
				
			} else {
				fnpMymsg("Rate given in excel is NOT matched to rate shown/displayed at Generate Invoice Screen");
				throw new Exception("Rate given in excel is NOT matched to rate shown/displayed at Generate Invoice Screen");
			}
			
			
			
			
			
			
			int loggedHourscol = fnpFindColumnIndex("ProfessionalServiceHoursTableHeader", "Logged Hours");
			String loggedHoursValue = fnpFetchFromTable("ProfessionalServiceHoursTable", 1, loggedHourscol);
		
			double loggedHoursGivenInExcel = Double.parseDouble(table.get("Logged_Hours").trim());
			fnpMymsg("Logged Hours given in excel is ----"+loggedHoursGivenInExcel);			
			double loggedHoursGivenAtGenerateInvoiceScreen = Double.parseDouble(loggedHoursValue);
			fnpMymsg("Logged Hours shown/displayed at Generate Invoice Screen is ----"+loggedHoursGivenAtGenerateInvoiceScreen);
			
			if (loggedHoursGivenInExcel==loggedHoursGivenAtGenerateInvoiceScreen) {
				fnpMymsg("Logged Hours given in excel is matched to Logged Hours shown/displayed at Generate Invoice Screen");
				
			} else {
				fnpMymsg("Logged Hours given in excel is NOT matched to Logged Hours shown/displayed at Generate Invoice Screen");
				throw new Exception("Logged Hours given in excel is NOT matched to Logged Hours shown/displayed at Generate Invoice Screen");
			}
			
			
			
			
			
			int amountcol = fnpFindColumnIndex("ProfessionalServiceHoursTableHeader", "Amount");
			String amountValue = fnpFetchFromTable("ProfessionalServiceHoursTable", 1, amountcol);
			
			
			
			//amountValue = amountValue.split("\\$")[1].trim();
			if (amountValue.contains("$")) {
				//amountValue = amountValue.split("\\$")[1].trim();
				
				throw new Exception("Currency symbol '$' is  present in 'Amount' column. ");
			} else {
				//throw new Exception("Currency symbol '$' is not present in 'Amount' column. ");
			}
			
			
			
			
			double expectedCalculatedAmount = rateValueGivenInExcel *loggedHoursGivenInExcel;
			fnpMymsg("Expected Calculated Amount is ----"+expectedCalculatedAmount);			
			double actualAmountGivenAtGenerateInvoiceScreen = Double.parseDouble(amountValue);
			fnpMymsg("Actual amount shown/displayed at Generate Invoice Screen is ----"+actualAmountGivenAtGenerateInvoiceScreen);
			
/*			if (expectedCalculatedAmount==actualAmountGivenAtGenerateInvoiceScreen) {
				fnpMymsg("Actual and Expected amount is matched at Generate Invoice Screen");
				
			} else {
				fnpMymsg("Actual and Expected amount is NOT matched at Generate Invoice Screen");
				throw new Exception("Actual and Expected amount is NOT matched at Generate Invoice Screen");
			}
			*/
			
			final double THRESHOLD = .0001;
			if (Math.abs(expectedCalculatedAmount - actualAmountGivenAtGenerateInvoiceScreen) < THRESHOLD){
				fnpMymsg("Actual and Expected amount is matched at Generate Invoice Screen");
				}	else{
				fnpMymsg("Actual and Expected amount is NOT matched at Generate Invoice Screen");
				throw new Exception("Actual and Expected amount is NOT matched at Generate Invoice Screen");
			
				}
			
			
			fnpMymsg(" **********************End Flow****************************************");

		
			
			
			
			
			
			
			

		} catch (Throwable t) {

			IsBrowserPresentAlready = false;
			fnpCommonFinalCatchBlock(t, ". \n\n    Hence Health Science flow  is fail . ", "HealthScienceFlowFail");
			driver.quit(); // for time being

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

		if (isTestPass) {
			TestUtil.reportDataSetResult(currentSuiteXls, "Test Cases", TestUtil.getRowNum(currentSuiteXls, this.getClass().getSimpleName()), "PASS");
			currentSuiteXls.setCellData("Test Cases", "Last_Successful_Execution", rowNum, fnReturnDateWithTimeForExcel());
			fnpElapsedTime(currentSuiteName, this.getClass().getSimpleName(), currentScriptCode, "PASS");
		} else {
			TestUtil.reportDataSetResult(currentSuiteXls, "Test Cases", TestUtil.getRowNum(currentSuiteXls, this.getClass().getSimpleName()), "FAIL");
			fnpElapsedTime(currentSuiteName, this.getClass().getSimpleName(), currentScriptCode, "FAIL");
		}

	}

	
	
	
	// @AfterSuite
	@AfterTest
	public void closebrowser() throws InterruptedException {

		// for time being,later uncomment it
		//driver.quit();
		//IsBrowserPresentAlready = false;
		setIsBrowserPresentAlready_false();
		killprocess();

	}

	
	
	
	
}
