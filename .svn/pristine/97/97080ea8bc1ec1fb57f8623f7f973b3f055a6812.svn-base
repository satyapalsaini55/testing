package nsf.ecap.SCFS_suite;

import static nsf.ecap.config.IPULSE_CONSTANTS.WOISOFinancialTab_BillCodeTableColName_BillCodes;
import static nsf.ecap.config.IPULSE_CONSTANTS.WOISOFinancialTab_BillCodeTableColName_Quantity;
import static nsf.ecap.config.IPULSE_CONSTANTS.WOISOFinancialTab_BillCodeTableColName_TaskTypeFeeType;
import static nsf.ecap.config.IPULSE_CONSTANTS.WOISOTaskName_CertificationAudit;
import static nsf.ecap.config.IPULSE_CONSTANTS.WOISOTaskName_DeskAudit;
import static nsf.ecap.config.IPULSE_CONSTANTS.WOISOTaskTab_Certification_MainTaskAIName;
import static nsf.ecap.config.IPULSE_CONSTANTS.WOISOTaskTab_TaskAISummaryTableColName_TaskAIName;
import static nsf.ecap.config.IPULSE_CONSTANTS.WOISOTaskType_CertificationAudit;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.TimeUnit;

import net.sf.saxon.functions.Parse;
import nsf.ecap.ISR_suite.ISO9001_Single;
import nsf.ecap.MonitorPlan_Suite.TestSuiteBase_MonitorPlan;
import nsf.ecap.config.IPULSE_CONSTANTS;
import nsf.ecap.util.ErrorUtil;
import nsf.ecap.util.TestDataProvider;
import nsf.ecap.util.TestUtil;
import nsf.ecap.util.Xls_Reader;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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


public class Non_Cert_Standards  extends TestSuiteBase_SCFS_suite{

	public Xls_Reader currentSuiteXlsReference;
	
	public static String expectedbillCode;
	public static String expectedbillCode_Des;
	public static Integer innerFirstTaskRow;
	
	

	
	
	
	
	
	
	
	

	// Runmode of test case in a suite
	@BeforeTest
	@Parameters({ "className" })
	public void checkTestSkip(String className) throws Throwable {

		currentSuiteXlsReference = currentSuiteXls;
		
		ISR_BS01 = new ISO9001_Single();
		ISR_BS01.checkTestSkip(this.getClass().getSimpleName());
		
		className = this.getClass().getSimpleName();
		runmodes = TestUtil.getDataSetRunmodes(currentSuiteXlsReference, className);
		fail = false;
		isTestPass = true;

		start = new Date();
	}

	@Test(dataProviderClass = TestDataProvider.class, dataProvider = "Non_Cert_Standards_DataProvider")
	public void SQF_Non_Cert_flow(Hashtable<String, String> table) throws Throwable {
	//public void SQF_Non_Cert_flow(HashMap<String, String> table) throws Throwable {

		skip=false;
		
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
			
			hashXlData=new HashMap(table); 

			//start = new Date();
			
			
			String regularAuditTaskTab_expectedBillCode = "";
			double regularAuditTaskTab_expectedQuantity = 0;
			double regularAuditTaskTab_expectedAuditDays = 0;
			
			
			
			//fnpCallGoldenProcedure_COPY_AUDIT_TRANSACTION_SUBMIT(1234876,1939447,"TestScriptUser TestScriptUser");
			
			
			fnpMymsg("@  ---Before query starts");
			fnpDropWOISRFromDB((String) table.get("Corp/Facility#"), (String) table.get("Standard"));

			fnpMymsg("@  ---After query finishes");
			
	
			
			
			
			fnpCreateISRWO_excludingDBDropQuery();
			
			
			
			
			
			
			
			fnpMymsg(" *****Standard Facility tab*********************************************************");
			
			
			
			fnpType("OR", "ScopeofthefacilityTxtBx", (String) table.get("Standard") + "  "+(String) table.get("Scope_of_the_facility"));
			fnpType("OR", "Exclusion_of_the_facilityTxtBx", (String) table.get("Standard") + "  "+(String) table.get("Exclusion_of_the_facility"));
			
			
			
			fnpClick_OR("ISRSFTabSaveBtn");

			fnpWaitForVisibleHavingMultipleElements("TopMessage3");
			fnpMymsg("Top Message after create ----" + fnpGetText_OR("TopMessage3"));

			String SuccessfulMsg = fnpGetText_OR("TopMessage3");

			Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"),
					"Top message does not contain 'success' word, so might be data on Standard Facility tab has not been saved successfully");

			fnpMymsg("Data on Standard Facility tab has  been saved successfully");

	
			fnpMymsg("==============Standard Facility tab=================================================================================================");
			
			
			

			
			
			
			fnpMymsg("==============Financial_Tab tab=================================================================================================");
			
			
/*			
			
			fnpClick_OR("ISRFinancialTabLink");

			
			
			fnpPFList("InvoiceCurrencyPFList", "InvoiceCurrencyPFListOptions", (String) table.get("Invoice_Currency"));
			
			
			fnpPFList("ISRBillToOfficePFList", "ISRBillToOfficePFListOptions", (String) table.get("BillToOffice"));
			String insertedBillToOffice = fnpGetText_OR("ISRBillToOfficePFList");
			Assert.assertEquals(insertedBillToOffice, (String) table.get("BillToOffice"), "Bill To Office value has not been inserted properly ");
			
			
			
			fnpPFList("UpchargePFList", "UpchargePFListOptions", (String) table.get("Upcharge"));
			
			
			
			
			
			
			
			String fileNames = (String) table.get("FinancialTab_docUpload_FileName");
			String[] fileCount = fileNames.split(",");
			int fileCountSize = fileCount.length;

			if (!(fileCountSize > 0)) {
				throw new Exception("Upload file names should be given in excel.");
			}

			String fileTypes = (String) table.get("FinancialTab_docUpload_FileType");
			String[] fileTypeCount = fileTypes.split(",");
			int fileTypesCountSize = fileCount.length;

			if (!(fileTypesCountSize == fileCountSize)) {
				throw new Exception("Upload file type should be equal to the no. of files in excel.");
			}

			String fileAccessNames = (String) table.get("FinancialTab_docUpload_FileAccess");
			String[] fileAccessCount = fileAccessNames.split(",");
			int fileAccessCountSize = fileAccessCount.length;

			if (!(fileAccessCountSize == fileCountSize)) {
				throw new Exception("Upload file access should be equal to the no. of files  in excel.");
			}

			String fileName = "";

			String fname;

			for (int f = 0; f < fileCountSize; f++) {

			
				
				//Thread.sleep(2000);

				fnpClick_OR("ISRFinaceTabAddBtn");
				fnpWaitTillVisiblityOfElementNClickable_OR("ISRFinaceTabUploadSave&CloseBtn");
				fnpWaitForVisible("ISRFinaceTabUploadSave&CloseBtn");

				Thread.sleep(3000);
				fnsUploadFile("ISRFinaceTabUploadBrowseBtn", "FinancialTab : ADD popup :");  // added by satya
				//Commented by satya as file is not getting upload.
				
				fileName = System.getProperty("user.dir") + "\\docs\\" + fileCount[f];
				driver.findElement(By.xpath(OR.getProperty("ISRFinaceTabUploadBrowseBtn"))).sendKeys(fileName);
				Thread.sleep(1000);
				while (fnpCheckElementPresenceImmediately("BrowseCancelUploadBtn")) {
					Thread.sleep(1000);
				}

							
				fnpWaitForVisible("ISRFinaceTabUploadDoc_TypePFList");
				fnpPFList("ISRFinaceTabUploadDoc_TypePFList", "ISRFinaceTabUploadDoc_TypePFListOptions", fileTypeCount[f]);
				fnpPFList("ISRFinaceTabUploadDoc_AccessPFList", "ISRFinaceTabUploadDoc_AccessPFListOptions", fileAccessCount[f]);
				fnpClickInDialog_OR("ISRFinaceTabUploadSave&CloseBtn");

				fnpCheckError("Error is thrown by application while adding documents in Financial Tab");

				//Thread.sleep(2000);
			}

			
			
			
			
			fnpFileUploadInISR();
			
			
			
			String textMessage = "";

			fnpClick_OR("ISRMoveToInReviewBtn");

			fnpWaitTillClickable("NewlyCrWOTopBannerInfo");
			fnpGetORObjectX("NewlyCrWOTopBannerInfo").click();
			// fnpLoading_wait();

			fnpWaitForVisible("TopBannerWOStatus");
			String changedWOStatus = fnpGetORObjectX("TopBannerWOStatus").getText();
			Assert.assertTrue(changedWOStatus.equalsIgnoreCase("INREVIEW"), " WO_ISR status is not changed from DRAFT to INREVIEW.");
			fnpMymsg("Now  WO_ISR status has been changed from 'DRAFT' to  INREVIEW.");
			
			//Thread.sleep(3000); // added by satya
			
			*/
			
			
			fnpCommonCodeOfFinancialTabTillInReviewForISRSCFS();
			
			
			
			
			
			String firstTaskName_forTaskTab="";
			String firstTaskBillCode="";
			double firstTaskQuantity=0;
			double firstTaskDuration=0;
			
			
			
		
			
			int taskIndex = fnpFindColumnIndex("AuditDurationTable_HeaderRow", "Task");
			int calculatedDurationIndex = fnpFindColumnIndex("AuditDurationTable_HeaderRow", "Calculated Duration");

			fnpMymsg(" Now going to compare the Audit Duration values .");

			fnpDisplayingMessageInFrame("Now going to compare the Audit Duration values .", 5);

			String durationSets = (String) table.get("Audit_Duration");
			String NoOfSets[] = durationSets.split("::");
			fnpMymsg("No. of duration sets are ---" + NoOfSets.length);
			fnpMymsg("");
			fnpMymsg("");

			int totalrowsInAuditDurationTable = fnpCountRowsInTable("AuditDurationTable");

			boolean foundThistask = false;
			String textMessage;
			for (int i = 0; i < NoOfSets.length; i++) {
				foundThistask = false;
				fnpMymsg("");
				fnpMymsg("");
				fnpMymsg("Each duration set value are--" + NoOfSets[i]);
				String auditSet[] = NoOfSets[i].split(",");
				String auditTaskName = fnpremoveFormatting_forISR(auditSet[0]);
				fnpMymsg("Audit Task is--" + auditTaskName);

				String expectedAuditTaskDuration = fnpremoveFormatting_forISR(auditSet[1]);
				fnpMymsg("Audit Task Duration is--" + expectedAuditTaskDuration);

				for (int j = 1; j <= totalrowsInAuditDurationTable; j++) {

					String taskName = fnpFetchFromTable("AuditDurationTable", j, taskIndex);
					if (taskName.equalsIgnoreCase(auditTaskName)) {
						foundThistask = true;
						String taskduration = fnpFetchFromTable("AuditDurationTable", j, calculatedDurationIndex);
						double actualTaskDuration = Double.parseDouble(taskduration);
						double expectedTaskDuration = Double.parseDouble(expectedAuditTaskDuration);
						
						firstTaskDuration=expectedTaskDuration;
						
						
						if (actualTaskDuration != expectedTaskDuration) {
							textMessage = "Audit task duraton is not matched for task '" + taskName + "' as expected is --'" + expectedTaskDuration + "' but actual is --'"
									+ actualTaskDuration + "'.";
							fnpMymsg(textMessage);
							fnpDisplayingMessageInFrame(textMessage, 10);
							throw new Exception(textMessage);

							// //System.out.println("Audit task duraton is not matched for task '"+taskName+"' as expected is --'"+expectedTaskDuration+"' but actual is --'"+actualTaskDuration+"'.");

						} else {
							textMessage = "Audit task duraton is  matched successfully for task '" + taskName + "' as expected is --'" + expectedTaskDuration
									+ "' and actual is --'" + actualTaskDuration + "'.";
							fnpMymsg(textMessage);
							fnpDisplayingMessageInFrame(textMessage, 5);
							break;
						}

					}
				}

				if (!foundThistask) {
					textMessage = "This task '" + auditTaskName
							+ "' code is not present in Audit duration table while checking that all the Calculated Durations are correct or not.";
					fnpMymsg(textMessage);
					fnpDisplayingMessageInFrame(textMessage, 10);
					throw new Exception(textMessage);

				}

			}

			fnpMymsg("");
			fnpMymsg("");

			fnpMymsg("===============================================================================================================");

			/*				
				
		*//************** Matching bill code's quantity with having Audit work in bill code description ******************/

			
			
			

			
			
			
			
			
			
			
			
			
			
			
			/************** Matching bill code's quantity for given bill code ******************/

			int taskTypeIndex = fnpFindColumnIndex("BillCodesTable_HeaderRow", WOISOFinancialTab_BillCodeTableColName_TaskTypeFeeType);
			int billCodesIndex = fnpFindColumnIndex("BillCodesTable_HeaderRow", WOISOFinancialTab_BillCodeTableColName_BillCodes);
			int quantityIndex = fnpFindColumnIndex("BillCodesTable_HeaderRow", WOISOFinancialTab_BillCodeTableColName_Quantity);

			textMessage = " Now going to compare the bill code quantity values .";
			fnpMymsg(textMessage);
			fnpDisplayingMessageInFrame(textMessage, 5);
			String billCodeQuantityString = (String) table.get("BillCodeTableMatch_Quantity_forGivenBillCode");

			String billCodeQuantitySets[] = billCodeQuantityString.split("::");
			fnpMymsg("No. of bill code quantity sets are ---" + billCodeQuantitySets.length);
			fnpMymsg("");
			fnpMymsg("");

			int totalrowsInBillCodesTable = fnpCountRowsInTable("BillCodesTable");

			String expectedTaskTypeName;
			boolean foundThistaskWithGivenBillCode = false;
			boolean foundThisTask = false;
			boolean foundThisBillCode = false;
			

			
			
			
			
			for (int i = 0; i < billCodeQuantitySets.length; i++) {
				foundThistaskWithGivenBillCode = false;
				foundThisTask = false;
				foundThisBillCode = false;
				fnpMymsg("");
				fnpMymsg("");
				fnpMymsg("Each bill code quantity set value are--" + billCodeQuantitySets[i]);
				String eachQuantitySet[]= billCodeQuantitySets[i].split(",");
				expectedTaskTypeName = fnpremoveFormatting_forISR(eachQuantitySet[0]);
				fnpMymsg("Expected Task type name is--" + expectedTaskTypeName);

				
				
				String expectedTaskQuantity = fnpremoveFormatting_forISR(eachQuantitySet[1]);
				fnpMymsg("Expected Task Quantity is--" + expectedTaskQuantity);

				expectedbillCode = fnpremoveFormatting_forISR(eachQuantitySet[2]);
				fnpMymsg("Expected bill code  is--" + expectedbillCode);

				/*expectedbillCode_Des = fnpremoveFormatting_forISR(eachQuantitySet[3]);
				fnpMymsg("Expected bill code Description is--" + expectedbillCode_Des);*/
				
				
/*				
				if (expectedTaskTypeName.equalsIgnoreCase(WOISOTaskName_RegularAudit)) {
					regularAuditTaskTab_expectedBillCode =expectedbillCode;
				}
			*/

				
				firstTaskName_forTaskTab=fnpremoveFormatting_forISR(eachQuantitySet[0]);
				firstTaskQuantity=Double.parseDouble(fnpremoveFormatting_forISR(eachQuantitySet[1]));
				firstTaskBillCode=fnpremoveFormatting_forISR(eachQuantitySet[2]);
				
				
				
				/*for (int j = 1; j <= totalrowsInBillCodesTable; j++) {

					String taskName = fnpFetchFromTable("BillCodesTable", j, taskTypeIndex);
					String billcodeInTable = fnpFetchFromTable("BillCodesTable", j, billCodesIndex);

					if ((taskName.equalsIgnoreCase(expectedTaskTypeName)) & (billcodeInTable.equalsIgnoreCase(expectedbillCode))) {
						foundThistaskWithGivenBillCode = true;
						String actualTaskQuantity = fnpFetchFromTable("BillCodesTable", j, quantityIndex);

						double actualTaskQuantityInDouble = Double.parseDouble(actualTaskQuantity);
						double expectedTaskQuantityInDouble = Double.parseDouble(expectedTaskQuantity);
						
						
						if (actualTaskQuantityInDouble != expectedTaskQuantityInDouble) {
							textMessage = "FAILED ==>Actual Task Quantity is not matched for task type'" + taskName + "'  and for bill code '" + expectedbillCode
									+ "'    as expected is --'" + expectedTaskQuantityInDouble + "' but actual is --'" + actualTaskQuantityInDouble + "' at row no --" + j;
							fnpMymsg(textMessage);
							fnpDisplayingMessageInFrame(textMessage, 10);
							throw new Exception(textMessage);
						} else {
							textMessage = "PASSED ==> Actual Task Quantity is  matched successfully for task type'" + taskName + "'  and for bill code '" + expectedbillCode
									+ "'    as expected is --'" + expectedTaskQuantityInDouble + "' and actual is --'" + actualTaskQuantityInDouble + "' at row no --" + j;
							fnpDisplayingMessageInFrame(textMessage, 5);
							fnpMymsg(textMessage);
							break;
						}

					}
				}

				if (!foundThistaskWithGivenBillCode) {
					textMessage = "Either task '" + expectedTaskTypeName + "' or Bill Code '" + expectedbillCode
							+ "' is not present in the table when we are  going to compare the bill code quantity values .";
					fnpDisplayingMessageInFrame(textMessage, 10);
					fnpMymsg(textMessage);
					throw new Exception(textMessage);

				}*/
				
				
				
				
				for (int j = 1; j <= totalrowsInBillCodesTable; j++) {

					String taskName = fnpFetchFromTable("BillCodesTable", j, taskTypeIndex);
					String billcodeInTable = fnpFetchFromTable("BillCodesTable", j, billCodesIndex);

					if ((taskName.equalsIgnoreCase(expectedTaskTypeName)) ) {
						foundThisTask = true;
							if ( (billcodeInTable.equalsIgnoreCase(expectedbillCode)) ) {
								foundThisBillCode = true;
								String actualTaskQuantity = fnpFetchFromTable("BillCodesTable", j, quantityIndex);
		
								double actualTaskQuantityInDouble = Double.parseDouble(actualTaskQuantity);
								double expectedTaskQuantityInDouble = Double.parseDouble(expectedTaskQuantity);
								
								
								if (actualTaskQuantityInDouble != expectedTaskQuantityInDouble) {
									textMessage = "FAILED ==>Actual Task Quantity is not matched for task type'" + taskName + "'  and for bill code '" + expectedbillCode
											+ "'    as expected is --'" + expectedTaskQuantityInDouble + "' but actual is --'" + actualTaskQuantityInDouble + "' at row no --" + j;
									fnpMymsg(textMessage);
									fnpDisplayingMessageInFrame(textMessage, 10);
									throw new Exception(textMessage);
								} else {
									textMessage = "PASSED ==> Actual Task Quantity is  matched successfully for task type'" + taskName + "'  and for bill code '" + expectedbillCode
											+ "'    as expected is --'" + expectedTaskQuantityInDouble + "' and actual is --'" + actualTaskQuantityInDouble + "' at row no --" + j;
									fnpDisplayingMessageInFrame(textMessage, 5);
									fnpMymsg(textMessage);
									break;
								}
							}
					}
				}

				if (!foundThisTask) {
					textMessage = "Expected Task '" + expectedTaskTypeName + "' is not present in the table when we are  going to compare the bill code quantity values .";
					fnpDisplayingMessageInFrame(textMessage, 10);
					fnpMymsg(textMessage);
					throw new Exception(textMessage);

				}
				
				if (!foundThisBillCode) {
					textMessage = "Expected Bill Code '" + expectedbillCode
							+ "' is not present in the table when we are  going to compare the bill code quantity values .";
					fnpDisplayingMessageInFrame(textMessage, 10);
					fnpMymsg(textMessage);
					throw new Exception(textMessage);

				}
				
				

			}

			/*******************************************************************************************************************/

			
			
			
			
			
			
			
			
			fnpClick_OR("MoveToInProcessBtn");
			
			fnpWaitTillClickable("NewlyCrWOTopBannerInfo");
			fnpGetORObjectX("NewlyCrWOTopBannerInfo").click();
			// fnpLoading_wait();

			fnpWaitForVisible("TopBannerWOStatus");
			String changedWOStatus = fnpGetORObjectX("TopBannerWOStatus").getText();
			Assert.assertTrue(changedWOStatus.equalsIgnoreCase("INPROCESS"), " WO_ISR status is not changed from INREVIEW to INPROCESS.");
			fnpMymsg("Now  WO_ISR status has been changed from 'INREVIEW' to  INPROCESS.");

			
			
			
						
					
			fnpClick_OR("ISRTaskTab");
			
			int whileCounter = 0;
			while (true) {
				whileCounter++;
				if (!(fnpCheckElementDisplayedImmediately("TaskTabTable"))) {
						
						fnpMymsg("@  --Going to refresh the browser as task tab is blank.... Remove it later----" + whileCounter);
						driver.navigate().refresh();
						Thread.sleep(5000);
					
						if (!(fnpCheckElementDisplayedImmediately("TaskTabTable"))) {
								fnpClick_OR("ISRTaskTab");
							} else {
							break;
							}
			
				}
			
				if (whileCounter > 10) {
				break;
				}
			
			}
			
			//int taskNameColIndex = fnpFindColumnIndex("TaskTabTable_HeaderRow", WOISOTaskTab_TaskAISummaryTableColName_TaskAIName);
			int taskTypeColIndex = fnpFindColumnIndex("TaskTabTable_HeaderRow", WOISOTaskTab_TaskAISummaryTableColName_TaskAIType);
			
			
			
			String[] expectedTaskNameArray = ((String) table.get("Task/AI_Type_created_just_after_InProcess")).split(",");
			
			int totalRowGeneratedInTaskTable = fnpCountRowsInTable("TaskTabTable");
			
			int taskRowNo=0;
			boolean foundTaskflag = false;
			//String firstCreatedTaskName=null;
			for (int i = 0; i < expectedTaskNameArray.length; i++) {
			
				for (int j = 0; j < totalRowGeneratedInTaskTable; j++) {
				String taskTypeValue = fnpFetchFromTable("TaskTabTable", (j + 1), taskTypeColIndex);
				if (taskTypeValue.equalsIgnoreCase(expectedTaskNameArray[i])) {
				fnpMymsg(" Task/AI Type '" + expectedTaskNameArray[i] + "' is present at row no '" + (j + 1) + "' .");
				foundTaskflag = true;
				taskRowNo=j + 1;
				break;
				}
			}
			
			if (!foundTaskflag) {
			
			throw new Exception("  Task/AI Type'" + expectedTaskNameArray[i] + "' is NOT present in any row in Task table .");
			
			}
			
			}
						
			
			
			
			//ISR_BS01.Financial_Tab_And_InReviewToInProcess();
			
			
			
			
			// To find Task/AI no as we need it to distinguish  regular audit task if created again with same name later
			int taskAINoColIndex = fnpFindColumnIndex("TaskTabTable_HeaderRow", WOISOTaskTab_TaskAISummaryTableColName_TaskAINo);
			String firstCreatedTaskNo = fnpFetchFromTable("TaskTabTable", taskRowNo, taskAINoColIndex);
			
			
			
			
			//String firstTaskName_forTaskTab;
			//String firstTaskBillCode;
			//double firstTaskQuantity;
			//double firstTaskDuration;
			
			

			/******** Open the First task  in Task tab and compare some values *************************/

			 textMessage = "Now going to open the "+firstTaskName_forTaskTab+" task in Task tab and compare some values ";
			// String taskTypeTakenFromExcel=table.get("Task/AI_Type_created_just_after_InProcess");
			// String taskTypeTakenFromExcel=firstTaskName_forTaskTab;
			 
			fnpDisplayingMessageInFrame(textMessage, 5);
			// fnpClick_OR("TaskTabISR_DeskAudit_OpenIcon");
			String firstTaskOpenIconXpath = OR.getProperty("TaskTabISR_DeskAudit_OpenIconPart1") + firstTaskName_forTaskTab+ OR.getProperty("TaskTabISR_DeskAudit_OpenIconPart2");
			fnpClick_NOR(firstTaskOpenIconXpath);
			Thread.sleep(3000);
			
			fnpMymsg("Expected task '"+firstTaskName_forTaskTab+"' 's billing code  is ---" + firstTaskBillCode);
			fnpMymsg("Expected task '"+firstTaskName_forTaskTab+"' 's quantity  is ---" + firstTaskQuantity);
			fnpMymsg("Expected task '"+firstTaskName_forTaskTab+"' 's Audit days  is ---" + firstTaskDuration);
			fnpMymsg("");
			fnpMymsg("");

			

			
			
			
			
			
			int taskAINameIndex = fnpFindColumnIndex("TaskTabTable_HeaderRow", WOISOTaskTab_TaskAISummaryTableColName_TaskAIName);
		//	int deskRow = fnpFindRowContainsName("TaskTabTable", "Desk", taskAINameIndex);
			int firstTaskRow = fnpFindRow("TaskTabTable", firstTaskName_forTaskTab, taskAINameIndex);

			String firstTaskInnerTableHeaderXpath = OR.getProperty("ISRTaskInnerTableForBillingCodeTableHeader_part1") + (firstTaskRow - 1)
					+ OR.getProperty("ISRTaskInnerTableForBillingCodeTableHeader_part2");

			int billingCodeInnerTableIndex = fnpFindColumnIndex_NOR(firstTaskInnerTableHeaderXpath, "Billing Code");
			int quantityInnerTableIndex = fnpFindColumnIndex_NOR(firstTaskInnerTableHeaderXpath, "Qty.");

			String firstTaskInnerTableDataXpath = OR.getProperty("ISRTaskInnerTableForBillingCodeTableData_part1") + (firstTaskRow - 1)
					+ OR.getProperty("ISRTaskInnerTableForBillingCodeTableData_part2");
			
			
			////Not working for bill code for multi bill codes exists and others bill codes --satya
		//	int billingDescIndex = fnpFindColumnIndex_NOR(firstTaskInnerTableHeaderXpath, "Billing Desc"); 
		//	int innerFirstTaskRow = fnpFindRowContainsName_NOR(firstTaskInnerTableDataXpath, "Audit", billingDescIndex); 
		
		
			
			
			int billingCodeIndex = fnpFindColumnIndex_NOR(firstTaskInnerTableHeaderXpath, "Billing Code");
			int innerFirstTaskRow = fnpFindRowContainsName_NOR(firstTaskInnerTableDataXpath, expectedbillCode, billingCodeIndex);
			if(innerFirstTaskRow==0){
				throw new Exception("FAILED == Task TAB : Bill Code < "+expectedbillCode+" > records is not found into the table.");
			}
			
			
			
//			int innerFirstTaskRow = fnpFindRowContainsName_NOR(firstTaskInnerTableDataXpath, expectedbillCode_Des, billingDescIndex); 
			/*expectedbillCode_Des
			if(expectedbillCode.contains("BKAUDIT")){
				innerFirstTaskRow = fnpFindRowContainsName_NOR(firstTaskInnerTableDataXpath, "Burger", billingDescIndex); 
			}
			if (expectedbillCode.contains("CTFSAD")){
				innerFirstTaskRow = fnpFindRowContainsName_NOR(firstTaskInnerTableDataXpath, "Food", billingDescIndex); 
			} 
			if (expectedbillCode.contains("TIMSA")){
				innerFirstTaskRow = fnpFindRowContainsName_NOR(firstTaskInnerTableDataXpath, "Hortons", billingDescIndex); 
			}*/
			

			
			
			
			
			
			
			
			
			
			String actualFirstTaskBillingCode = fnpFetchFromTable_NOR(firstTaskInnerTableDataXpath, innerFirstTaskRow, billingCodeInnerTableIndex);
			fnpMymsg("Actual '"+firstTaskName_forTaskTab+"' 's  billing code  is ---" + actualFirstTaskBillingCode);
			String actualFirstTaskQuantityInString = fnpFetchFromTable_NOR(firstTaskInnerTableDataXpath, innerFirstTaskRow, quantityInnerTableIndex);
			double actualFirstTaskQuantity = Double.parseDouble(actualFirstTaskQuantityInString);
			fnpMymsg("Actual '"+firstTaskName_forTaskTab+"' 's   quantity  is ---" + actualFirstTaskQuantity);
			
			
			

			String firstTaskExpansionPanelXpath = OR.getProperty("ISRTaskExpansionPanelGrid_part1") + (firstTaskRow - 1) + OR.getProperty("ISRTaskExpansionPanelGrid_part2");

			String auditDaysXpath = firstTaskExpansionPanelXpath + "//tr/td/label[text()='Audit Days']/../following-sibling::td/label";

			String auditdaysInString = fnpGetText_NOR(auditDaysXpath);
			double actualAuditdays = Double.parseDouble(auditdaysInString);
			fnpMymsg("Actual '"+firstTaskName_forTaskTab+"' 's audit days are ---" + actualAuditdays);

			Assert.assertTrue(actualFirstTaskBillingCode.equalsIgnoreCase(firstTaskBillCode), firstTaskName_forTaskTab+" 's   billing code is not matched on task tab.");
			textMessage = firstTaskName_forTaskTab+" 's  billing code is matched on task tab. Actual is --" + actualFirstTaskBillingCode + "   and expected is ---" + firstTaskBillCode;
			fnpDisplayingMessageInFrame(textMessage, 5);
			fnpMymsg(textMessage);

			Assert.assertTrue(actualFirstTaskQuantity == firstTaskQuantity, firstTaskName_forTaskTab+" 's  quantity is not matched on task tab.");
			textMessage = firstTaskName_forTaskTab+" 's  quantity is  matched on task tab. Actual is --" + actualFirstTaskQuantity + "   and expected is ---" + firstTaskQuantity;
			fnpDisplayingMessageInFrame(textMessage, 5);
			fnpMymsg(textMessage);

			Assert.assertTrue(actualAuditdays == firstTaskDuration, firstTaskName_forTaskTab+" 's  Audit days are not matched on task tab.");
			textMessage = firstTaskName_forTaskTab+" 's  Audit days are   matched on task tab. Actual is --" + actualAuditdays + "   and expected is ---" + firstTaskDuration;
			fnpDisplayingMessageInFrame(textMessage, 5);
			fnpMymsg(textMessage);

			/*************************************************************************************/

			String boundaryStartDateXpath = firstTaskExpansionPanelXpath + "//tr/td/label[contains(text(),'Boundary Start Date')]/../following-sibling::td/label";
			String boundaryEndDateXpath = firstTaskExpansionPanelXpath + "//tr/td/label[contains(text(),'Boundary End Date')]/../following-sibling::td/label";

			String boudaryStartDateInStringformatAtTaskTab = fnpGetText_NOR(boundaryStartDateXpath);
			textMessage = "Boundary Start Date present on this page is ---" + boudaryStartDateInStringformatAtTaskTab;
			fnpMymsg(textMessage);
			fnpDisplayingMessageInFrame(textMessage, 5);
			
			SimpleDateFormat sdfmt1 = new SimpleDateFormat("dd-MMM-yyyy");
			Date boudaryStartDateInDateformatAtTaskTab = sdfmt1.parse(boudaryStartDateInStringformatAtTaskTab);

			String boundaryEndDateInStringformatAtTaskTab = fnpGetText_NOR(boundaryEndDateXpath);
			fnpMymsg(textMessage);
			textMessage = "Boundary End Date present on this page is ---" + boundaryEndDateInStringformatAtTaskTab;
			fnpDisplayingMessageInFrame(textMessage, 5);
						
			Date boundaryEndDateInDateformatAtTaskTab = sdfmt1.parse(boundaryEndDateInStringformatAtTaskTab);
			
			

			SimpleDateFormat sdfmt2 = new SimpleDateFormat("MM/dd/yy");

			String expectedStartDate_string = table.get("boundary_start_date_for_initial_audit");
			//System.out.println("@  ---expected start date is ----"+expectedStartDate_string);
			//Date expectedStartDate = sdfmt1.parse(expectedStartDate_string);
			Date expectedStartDate = sdfmt2.parse(expectedStartDate_string);
			String expectedEndDate_string = table.get("boundary_end_date_for_initial_audit");
			//Date expectedEndDate = sdfmt1.parse(expectedEndDate_string);
			Date expectedEndDate = sdfmt2.parse(expectedEndDate_string);
			
			
			
			Date Exected_StartDate = new Date(expectedStartDate_string);
			Format Formatter01 = new SimpleDateFormat("dd-MMM-yyyy");
			String Expected_StartDate = Formatter01.format(Exected_StartDate);
			//System.out.println("========== Satya === Expected_StartDate  ==== "+Expected_StartDate);
			assert ( boudaryStartDateInStringformatAtTaskTab.toLowerCase().contains(Expected_StartDate.toLowerCase()) ):"FAILED == Initial Audit : Excel expected boundary_start_date < "+Expected_StartDate+" > is not matched with the Actual boundary_start_date < "+boudaryStartDateInStringformatAtTaskTab+" > displayed into WO.";
			fnpMymsg("Initial Audit : Successfully verified that Excel expected boundary_start_date < "+Expected_StartDate+" > is matched with the Actual boundary_start_date < "+boudaryStartDateInStringformatAtTaskTab+" > displayed into WO.");
			
			
						
			Date Exected_EndDate = new Date(expectedEndDate_string);
			Format Formatter02 = new SimpleDateFormat("dd-MMM-yyyy");
			String Expected_EndDate = Formatter02.format(Exected_EndDate);
			//System.out.println("========== Satya === Expected_EndDate  ==== "+Expected_EndDate);
			assert ( boundaryEndDateInStringformatAtTaskTab.toLowerCase().contains(Expected_EndDate.toLowerCase()) ):"FAILED == Initial Audit : Excel expected boundary_End_date < "+Expected_EndDate+" > is not matched with the Actual boundary_End_date < "+boundaryEndDateInStringformatAtTaskTab+" > displayed into WO.";
			fnpMymsg("Initial Audit : Successfully verified that Excel expected boundary_End_date < "+Expected_EndDate+" > is matched with the Actual boundary_End_date < "+boundaryEndDateInStringformatAtTaskTab+" > displayed into WO.");
			
			
			
			
			
			
			
			// Message was not clear karen ask
			/*
			if (expectedStartDate.compareTo(boudaryStartDateInDateformatAtTaskTab) > 0) {
				textMessage = "boundary_start_date_for_initial_audit in excel '"+expectedStartDate+"' is after boudaryStartDateInDateformatAtTaskTab '"+boudaryStartDateInDateformatAtTaskTab+"', hence failed. " ;
				fnpMymsg(textMessage);
				fnpDisplayingMessageInFrame(textMessage, 5);
				throw new Exception(textMessage);
			} else if (expectedStartDate.compareTo(boudaryStartDateInDateformatAtTaskTab) < 0) {
				textMessage = "boundary_start_date_for_initial_audit in excel '"+expectedStartDate+"' is before boudaryStartDateInDateformatAtTaskTab '"+boudaryStartDateInDateformatAtTaskTab+"', hence failed. " ;
				fnpMymsg(textMessage);
				fnpDisplayingMessageInFrame(textMessage, 5);
				throw new Exception(textMessage);
			} else {
				textMessage = "boundary_start_date_for_initial_audit in excel  '" + expectedStartDate + "'   is equal to Boundary start date in Tasks tab of WO";
				fnpMymsg(textMessage);
				fnpDisplayingMessageInFrame(textMessage, 5);
			}
			*/
			
			
			
			
			
			
			// Message was not clear karen ask
			
			/*if (expectedEndDate.compareTo(boundaryEndDateInDateformatAtTaskTab) > 0) {
				textMessage = "boundary_end_date_for_initial_audit in excel '"+expectedEndDate+"' is after boudaryEndDateInDateformatAtTaskTab '"+boundaryEndDateInDateformatAtTaskTab+"', hence failed. " ;
				fnpMymsg(textMessage);
				fnpDisplayingMessageInFrame(textMessage, 5);
				throw new Exception(textMessage);
			} else if (expectedEndDate.compareTo(boundaryEndDateInDateformatAtTaskTab) < 0) {
				textMessage = "boundary_end_date_for_initial_audit in excel '"+expectedEndDate+"' is before boudaryEndDateInDateformatAtTaskTab '"+boundaryEndDateInDateformatAtTaskTab+"', hence failed. " ;
				fnpMymsg(textMessage);
				fnpDisplayingMessageInFrame(textMessage, 5);
				throw new Exception(textMessage);
			} else {
				textMessage = "boundary_end_date_for_initial_audit in excel  '" + expectedEndDate + "'   is equal to Boundary end date in Tasks tab of WO";
				fnpMymsg(textMessage);
				fnpDisplayingMessageInFrame(textMessage, 5);
			}
			*/
			
			
			
			
			String auditNoInFirstTaskXpath = OR.getProperty("AuditNoAtTaskTab_part1") + (firstTaskRow - 1) + OR.getProperty("AuditNoAtTaskTab_part2");
			String auditNo = fnpGetText_NOR(auditNoInFirstTaskXpath);
			
			
			
			

			String editBtnForEditFirstTaskTaskXpath = OR.getProperty("EditBtnForEditTaskAtTaskTab_part1") + (firstTaskRow - 1) + OR.getProperty("EditBtnForEditTaskAtTaskTab_part2");
			
			fnpClick_NOR(editBtnForEditFirstTaskTaskXpath);
			
			
			
			 Date todayDate = new Date();
			sdfmt1 = new SimpleDateFormat("dd-MMM-yyyy");
			String todaydateInStringformat = sdfmt1.format(todayDate);

			fnpClick_OR_WithoutWait("BoundaryStartDateBtnAtEditTaskDialog");
			Thread.sleep(2000);			
			fnpCalendarDatePicker_BySelectValues_From_NextAndBack(todaydateInStringformat);
			
			Thread.sleep(2000);	
			
			
			
			
			 todayDate=new Date();
			 Calendar c = Calendar.getInstance();
			c.setTime(todayDate);
			c.add(Calendar.DAY_OF_MONTH, 7);
			Date afterOneWeekdate = c.getTime();

			sdfmt1 = new SimpleDateFormat("dd-MMM-yyyy");
			String afterOneWeekdateInStringformat = sdfmt1.format(afterOneWeekdate);

			fnpClick_OR_WithoutWait("BoundaryEndDateBtnAtEditTaskDialog");
			Thread.sleep(2000);			
			fnpCalendarDatePicker_BySelectValues_From_NextAndBack(afterOneWeekdateInStringformat);
			
			//Thread.sleep(2000);	
			Thread.sleep(5000);	
/*			Thread.sleep(6000);	// to avoid we are sorry error some time
			Thread.sleep(10000);
			*/
			
			fnpClick_OR("SaveBtnAtEditTask");
			
			
			fnpClick_OR("ISRSFTabSaveBtn");
			
			
			
			
			
			
			
			
			if ( (fnpCheckElementDisplayedImmediately_NOR(firstTaskOpenIconXpath) & (!fnpCheckElementDisplayedImmediately_NOR(auditNoInFirstTaskXpath)) )  ) {				
				fnpClick_NOR(firstTaskOpenIconXpath);
			}
			
			
			
/*			fnpGetORObjectX___NOR(auditNoInFirstTaskXpath).click();
			// fnpClick_OR("EditBtnOnViewAuditPage");
			Thread.sleep(1000);
			
			
			fnpWaitForVisible("viewFinalReportBtnAtViewAuditScreen");			
			String visitNoAtViewAuditPage = fnpGetText_NOR("visitLinkInInfoTab").trim();
			fnpMymsg("Going to check visit no , as if visit no is not present/blank then it will go for clicking bundle audit button. So here visit no. is--'"+visitNoAtViewAuditPage+"'.");
			if (visitNoAtViewAuditPage.equalsIgnoreCase("")) {				
				fnpClick_OR("BundleAuditBtn");
			}
			
			
			//fnpClick_OR("BundleAuditBtn");
			fnpClick_OR("ISRBackBtn");
			fnpClick_OR("ISRTaskTab");
			
			*/
			
			
			String visitNoInFirstTaskXpath = OR.getProperty("VisitNoAtTaskTab_part1") + (firstTaskRow - 1) + OR.getProperty("VisitNoAtTaskTab_part2");

			String visitNo = fnpGetText_NOR(visitNoInFirstTaskXpath).trim();
			fnpMymsg("Going to check visit no, as if visit no is not present/blank then it will go for clicking bundle audit button. So here visit no. is--'"+visitNo+"'.");			 
			if (visitNo.equalsIgnoreCase("")) {
				fnpGetORObjectX___NOR(auditNoInFirstTaskXpath).click();				
				fnpWaitForVisible("viewFinalReportBtnAtViewAuditScreen");
				
				String visitNoAtViewAuditPage = fnpGetText_NOR("visitLinkInInfoTab").trim();
				fnpMymsg("Going to check visit no AGAIN, as if visit no is not present/blank then it will go for clicking bundle audit button. So here visit no. is--'"+visitNoAtViewAuditPage+"'.");
				if (visitNoAtViewAuditPage.equalsIgnoreCase("")) {				
					fnpClick_OR("BundleAuditBtn");
				}

				fnpClick_OR("ISRBackBtn");
				fnpClick_OR("ISRTaskTab");
				
				if (fnpCheckElementDisplayedImmediately("EditWOBtn")) {				
					fnpClick_OR("EditWOBtn");
				}
				
				// fnpClick_OR("TaskTabISR_DeskAudit_OpenIcon");
				fnpClick_NOR(firstTaskOpenIconXpath);

				visitNo = fnpGetText_NOR(visitNoInFirstTaskXpath).trim();
			}
			

			// fnpClick_OR("TaskTabISR_DeskAudit_OpenIcon");
			fnpClick_NOR(firstTaskOpenIconXpath);

			visitNoInFirstTaskXpath = OR.getProperty("VisitNoAtTaskTab_part1") + (firstTaskRow - 1) + OR.getProperty("VisitNoAtTaskTab_part2");

			visitNo = fnpGetText_NOR(visitNoInFirstTaskXpath).trim();
			
			
			
			
			
			fnpGetORObjectX___NOR(visitNoInFirstTaskXpath).click();
			fnpClick_OR("EditBtnOnViewVisitPage");

		//	fnpCommonCodeForAuditorLookup(table); // Commented by satya as filter box are not coming
			fnsCommonCodeForAuditorLookup(table);
			
			
			

			
			
			
			
			
			
	
			
			
			
			
			
			
			 todaydateInStringformat = sdfmt1.format(new Date());
			 

			
			
			//fnpClick_OR_WithoutWait("ScheduleStartDateCalBtn_xpath");
			fnpClick_OR("ScheduleStartDateCalBtn_xpath");
			Thread.sleep(2000);			
			fnpCalendarDatePicker_BySelectValues_From_DropDown2(todaydateInStringformat,
					OR.getProperty("calendarDatePickerSelectYear_xpath"), OR.getProperty("calendarDatePickerSelectMonth_xpath"));
			
			
			String scheduleStartDateforDeskAudit=todaydateInStringformat;
			
			
			Thread.sleep(2000);	
			Thread.sleep(4000);	
			
			
			//fnpClick_OR_WithoutWait("ScheduleEndDateCalBtn_xpath");
			fnpClick_OR("ScheduleEndDateCalBtn_xpath");
			Thread.sleep(2000);			
/*			fnpCalendarDatePicker_BySelectValues_From_DropDown2(afterOneWeekdateInStringformat,
					OR.getProperty("calendarDatePickerSelectYear_xpath"), OR.getProperty("calendarDatePickerSelectMonth_xpath"));
			
			*/
			
			fnpCalendarDatePicker_BySelectValues_From_DropDown2(todaydateInStringformat,
					OR.getProperty("calendarDatePickerSelectYear_xpath"), OR.getProperty("calendarDatePickerSelectMonth_xpath"));
			
			
			
			
			String scheduleEndDateforDeskAudit=afterOneWeekdateInStringformat;
			Thread.sleep(4000);
/*			Thread.sleep(6000);	// to avoid we are sorry error some time
			Thread.sleep(10000);
			*/
			
			
			
			
			fnpClick_OR("SaveBtnAtViewVisitPage");
			
			
			
			fnpWaitForVisibleHavingMultipleElements("TopMessage3");
			fnpMymsg("Top Message after saving dates are  ----" + fnpGetText_OR("TopMessage3"));
			 SuccessfulMsg = fnpGetText_OR("TopMessage3");
			Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"), "Top message does not contain 'success' word, so might be Scheduled Stard and End dates  have NOT  been saved successfully");
			fnpMymsg("Scheduled Stard and End dates  have  been saved successfully");
			
			
			
			int intAuditNo=Integer.parseInt(auditNo);
			
			
			//int copyFromAudit=Integer.parseInt( (String)table.get("Golden_audit_number").trim());
			int copyFromAudit=(int)Double.parseDouble( (String)table.get("Golden_audit_number").trim());
			int copyToAudit=intAuditNo;
			
			/*fnpMymsg("Just before running golden procedure");
			fnpCallGoldenProcedure(copyFromAudit,copyToAudit,(String) table.get("Auditor"));
			fnpMymsg("Golden procedure ran successfully.");
			*/
	
		/*	fnpMymsg("Just before running golden procedure - COPY_AUDIT_TRANSACTION");
			fnpCallGoldenProcedure_COPY_AUDIT_TRANSACTION(copyFromAudit,copyToAudit,(String) table.get("Auditor"));
			fnpMymsg("Golden procedure 'COPY_AUDIT_TRANSACTION' ran successfully.");*/
			
			
			fnpMymsg("Just before running golden procedure - COPY_AUDIT_TRANSACTION_SUBMIT");
			fnpCallGoldenProcedure_COPY_AUDIT_TRANSACTION_SUBMIT(copyFromAudit,copyToAudit,(String) table.get("Auditor"));
			fnpMymsg("Golden procedure 'COPY_AUDIT_TRANSACTION_SUBMIT' ran successfully.");
			
			
			
			//Not Working satya
			/*
			fnpClick_OR("ISRBackToViewBtn");
			fnpLoading_wait();
			fnpClick_OR("ISRBackBtn");

			*/
			
			fnpSearchWorkOrderOnly(workOrderNo);

			fnpClick_OR("Complete_Perform_Audit_AI_button");
		
			
			fnpWaitForVisibleHavingMultipleElements("TopMessage3");
			SuccessfulMsg = fnpGetText_OR("TopMessage3");
			assert ( !(SuccessfulMsg.toLowerCase().contains("no records")) ):  "Getting error < "+SuccessfulMsg+" >, after clicking on 'Complete_Perform_Audit_AI' button.";
			fnpMymsg("Successfully verify message 'Complete Perform_Audit_AI Processed'.");

			
			try{
			String techReviewFlagValue=((String)table.get("Tech_Review_Flag")).trim();
			}catch(Throwable t){
				throw new Exception("Might be column for 'Tech_Review_Flag' is not present in excel or may be some other issue . \n \n  Error thrown is --\n \n "+t.getMessage());
			}
			
			
		//	  
			
			
			hashXlData=new HashMap(table); 
			hashXlData.put("Lead_Auditor_Rating", "Excellent");
			hashXlData.put("Reviewer_Recommendation", "Pass");
			
			//Tech_Review_Flag
			
			int firstCreatdTaskRowNo=0;
			String firstCreatedTaskOpenIconXpath=null;
			String firstTaskCreated_InnerTableDataXpathForAI=null;
			String firstTaskCreated_InnerTableHeaderXpathForAI=null;
			
			if (  ((String)table.get("Tech_Review_Flag")).equalsIgnoreCase("Y")) {
				
				
				fnpClick_OR("ISRTaskTab");
				
				firstCreatdTaskRowNo=fnpFindRow("TaskTabTable", firstCreatedTaskNo, taskAINoColIndex);
							
				firstCreatedTaskOpenIconXpath = OR.getProperty("TaskTabISR_DeskAudit_OpenIconPart1_usingRowNo") + firstCreatdTaskRowNo+ OR.getProperty("TaskTabISR_DeskAudit_OpenIconPart2_usingRowNo");
				fnpClick_NOR(firstCreatedTaskOpenIconXpath);	
					
				firstTaskCreated_InnerTableDataXpathForAI = OR.getProperty("ISRTaskInnerTableForActionItemsTableData_part1") + (firstCreatdTaskRowNo - 1)
						+ OR.getProperty("ISRTaskInnerTableForActionItemsTableData_part2");
				
				firstTaskCreated_InnerTableHeaderXpathForAI = OR.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part1") + (firstCreatdTaskRowNo - 1)
						+ OR.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part2");
				
				fnpProcessAI_ISR_TaskTab_1(firstTaskCreated_InnerTableDataXpathForAI,firstTaskCreated_InnerTableHeaderXpathForAI,"TechnicalReview", "Completed","SaveButton__OnConsolidatedScreen");
				
				//fnpProcessAI_ISR_TaskTab(WOISOTaskType_CertificationAudit,WOISOTaskTab_Certification_MainTaskAIName,"TechnicalReview", "Completed","SaveButton__OnConsolidatedScreen");
				
				
				firstCreatdTaskRowNo=fnpFindRow("TaskTabTable", firstCreatedTaskNo, taskAINoColIndex);				
				firstCreatedTaskOpenIconXpath = OR.getProperty("TaskTabISR_DeskAudit_OpenIconPart1_usingRowNo") + firstCreatdTaskRowNo+ OR.getProperty("TaskTabISR_DeskAudit_OpenIconPart2_usingRowNo");
				fnpClick_NOR(firstCreatedTaskOpenIconXpath);						
				firstTaskCreated_InnerTableDataXpathForAI = OR.getProperty("ISRTaskInnerTableForActionItemsTableData_part1") + (firstCreatdTaskRowNo - 1)
						+ OR.getProperty("ISRTaskInnerTableForActionItemsTableData_part2");				
				firstTaskCreated_InnerTableHeaderXpathForAI = OR.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part1") + (firstCreatdTaskRowNo - 1)
						+ OR.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part2");												
				fnpVerifyAIStatusInISR( firstCreatedTaskOpenIconXpath, firstTaskCreated_InnerTableHeaderXpathForAI, firstTaskCreated_InnerTableDataXpathForAI, "TechnicalReview", "Completed");
				
				
				//fnpClick_OR("ISRTaskTab");
				//fnpClick_NOR(firstCreatedTaskOpenIconXpath);
				

				String firstCreatedTaskExpansionPanelXpath = OR.getProperty("ISRTaskExpansionPanelGrid_part1") + (firstCreatdTaskRowNo - 1) + OR.getProperty("ISRTaskExpansionPanelGrid_part2");
				String firstCreatedTaskAuditStatusXpath = firstCreatedTaskExpansionPanelXpath + "//tr/td/label[text()='Audit Status']/../following-sibling::td/label";
				String firstCreatedTaskAuditStatus = fnpGetText_NOR(firstCreatedTaskAuditStatusXpath);
				
				String expectedAuditStatusOfFirstTask="REVIEWED";
				if (firstCreatedTaskAuditStatus.equalsIgnoreCase(expectedAuditStatusOfFirstTask)) {
					fnpMymsg("First created task '"+firstTaskName_forTaskTab +"' 's audit status  at task tab is  become '"+expectedAuditStatusOfFirstTask+"'  as its value is ---'"+firstCreatedTaskAuditStatus +"' and expected is --'"+expectedAuditStatusOfFirstTask +"' .");
				} else {
					fnpMymsg("First created task '"+firstTaskName_forTaskTab +"' 's audit status   at task tabis NOT become '"+expectedAuditStatusOfFirstTask+"'   as its value is ---"+firstCreatedTaskAuditStatus+"' and expected is --'"+expectedAuditStatusOfFirstTask +"' .");
					//throw new Exception("Cert Audit status is NOT become 'SUBMITTED'");
					throw new Exception("First created task '"+firstTaskName_forTaskTab +"' 's audit status  at task tab is NOT become '"+expectedAuditStatusOfFirstTask+"'   as its value is ---"+firstCreatedTaskAuditStatus+"' and expected is --'"+expectedAuditStatusOfFirstTask +"' .");
				}
				
			
				
				
				
				
				/* ------Verifying audit status at snapshot tab------------- */
				fnpClick_OR("SnapshotTabLink");
				
				int taskNoColIndex = fnpFindColumnIndex("WO_SnapshotsTAB_Audit_Table_Header", WOISOSnapshotTab_AuditsTableColName_TaskNo);
				int statusColIndex = fnpFindColumnIndex("WO_SnapshotsTAB_Audit_Table_Header", WOISOSnapshotTab_AuditsTableColName_Status);
				int firstCreatdTaskRowNo_SnapshotTabAuditTable=fnpFindRow("WO_SnapshotsTAB_Audit_Table_Data", firstCreatedTaskNo, taskNoColIndex);
				
				String actualStatusOfFirstAudit = fnpFetchFromTable("WO_SnapshotsTAB_Audit_Table_Data", firstCreatdTaskRowNo_SnapshotTabAuditTable, statusColIndex);	
				
				
				
				if (actualStatusOfFirstAudit.equalsIgnoreCase(expectedAuditStatusOfFirstTask)) {
					fnpMymsg("First created task '"+firstTaskName_forTaskTab +"' 's audit status at snapshot tab is  become '"+expectedAuditStatusOfFirstTask+"'  as its value is ---'"+actualStatusOfFirstAudit +"' and expected is --'"+expectedAuditStatusOfFirstTask +"' .");
				} else {
					fnpMymsg("First created task '"+firstTaskName_forTaskTab +"' 's audit status  at snapshot tab  is NOT become '"+expectedAuditStatusOfFirstTask+"'   as its value is ---"+actualStatusOfFirstAudit+"' and expected is --'"+expectedAuditStatusOfFirstTask +"' .");
					//throw new Exception("Cert Audit status is NOT become 'SUBMITTED'");
					throw new Exception("First created task '"+firstTaskName_forTaskTab +"' 's audit status at snapshot tab  is NOT become '"+expectedAuditStatusOfFirstTask+"'   as its value is ---"+actualStatusOfFirstAudit+"' and expected is --'"+expectedAuditStatusOfFirstTask +"' .");
				}
				
				/* ------------------------------------------------------------------- */
				
				
				
				
				
				
				
				
				
				
			} else {
				if ( ((String)table.get("Tech_Review_Flag")).trim().equalsIgnoreCase("")) {
					//fnpMymsg("Nothing to do for the case where 'Tech_Review_Flag' value is not present.");
					fnpMymsg("Value for 'Tech_Review_Flag' column must be defined in excel.");
					throw new Exception("Value for 'Tech_Review_Flag' column must be defined in excel.");
				} else {
					throw new Exception("Code for this case has not been written where Tech_Review_Flag's value is 'N'");
				}
			}
			
			
			
			
			
			driver.quit();
			
			
			
			killprocess();
			Thread.sleep(2000);
			
			String oldBrowser = browserName;
			browserName = "IE";
			//browserName = "chrome";
			fnpLaunchBrowser();
			browserName=oldBrowser;
			

			
			String oasisURL = excelSiteURL.split("Oasis_Url:")[1];	
			String oasisUserName = CONFIG.getProperty("adminUsername");
			String oasisLoginPassword = CONFIG.getProperty("adminPassword");
						
			
			fnpLoginIntoOasis( oasisURL, oasisUserName, oasisLoginPassword);
			
			

			Thread.sleep(1000);
			fnpMouseHover("Oasis_Admin_menu");
			Thread.sleep(2000);
			fnpClick_OR_WithoutWait("Oasis_Admin_SupportSubmenu");
		//	Thread.sleep(10000);
			Thread.sleep(2000);
			fnpCheckErrorUsingPageSource_Oasis();
			fnpWaitForVisible("Oasis_ManuallyRunReportSchedulerLink");
			
			fnpClick_OR_WithoutWait("Oasis_ManuallyRunReportSchedulerLink");
			fnpWaitForVisible("Oasis_RunIQAULN_btn");
			fnpMouseHover("Oasis_RunIQAULN_btn");
			Thread.sleep(4000);
			try{
			driver.manage().timeouts().pageLoadTimeout(240, TimeUnit.SECONDS);
			fnpClick_OR_WithoutWait("Oasis_RunIQAULN_btn");
			}catch(org.openqa.selenium.TimeoutException t){
				
/*				if (!(t.getMessage().contains("page to load"))){
					throw t;
				}
				*/
				throw t;
			}
			finally{
				driver.manage().timeouts().pageLoadTimeout(Long.parseLong(CONFIG.getProperty("pageLoadTime")), TimeUnit.SECONDS);
			}
			
			
			fnpWaitForVisible("Oasis_ManuallyRunReportSchedulerLink",180);
			
			

			driver.close();
			// driver.quit();


			fnpLaunchBrowserAndLogin();
			fnpSearchWorkOrderOnly(workOrderNo);
			
			//fnpWaitForVisible("Complete_Perform_Audit_AI_button");
			
			
			
			
			
/*						
			boolean NewlyCreatedAudit_Found = false;
			for(int rowno=1; rowno<=3; rowno++){
				String RowXpaths = OR.getProperty("WO_SnapshotsTAB_Audit_Table_Data")+"/tr["+rowno+"]";
				if(driver.findElements(By.xpath(RowXpaths)).size()>0){
					String Row_Text = fnpGetText_NOR(RowXpaths);
					if(Row_Text.toLowerCase().contains("created")){
						fnpMymsg("Successfully verified that Next Audit created successfully.");
						
						String NextAudit_type = table.get("NextAudit_Type");
						assert ( Row_Text.toLowerCase().contains(NextAudit_type.toLowerCase()) ):"FAILED == Next Audit : Excel expected 'Type' is not matched with the Actual value(displayed into WO). ";
						fnpMymsg("Successfully verified that Next Audit 'Type' from excel is matched with the Actual value from WO.");
						
						
						String NextAudit_expected_StartDate_String = table.get("NextAudit_boundary_start_date");
						Date NextAuditExected_StartDate = new Date(NextAudit_expected_StartDate_String);
						Format Formatter1 = new SimpleDateFormat("dd-MMM-yyyy");
						String NextAudit_Expected_StartDate = Formatter1.format(NextAuditExected_StartDate);
						//System.out.println("========== Saty === NextAudit_expected_EndDate  ==== "+NextAudit_Expected_StartDate);
						assert ( Row_Text.toLowerCase().contains(NextAudit_Expected_StartDate.toLowerCase()) ):"FAILED == Next Audit : Excel expected 'boundary_start_date' is not matched with the Actual value(displayed into WO).";
						fnpMymsg("Successfully verified that Next Audit 'boundary_start_date' from excel is matched with the Actual value from WO.");
						
						
						
						
						
						String NextAudit_expectedEndDate_string = table.get("NextAudit_boundary_end_date");
						Date NextAuditExpectedEndDate = new Date(NextAudit_expectedEndDate_string);
						Format Formatter2 = new SimpleDateFormat("dd-MMM-yyyy");
						String NextAudit_Expected_EndDate = Formatter2.format(NextAuditExpectedEndDate);
						//System.out.println("========== Saty === NextAudit_expected_EndDate  ==== "+NextAudit_Expected_EndDate);
						assert ( Row_Text.toLowerCase().contains(NextAudit_Expected_EndDate.toLowerCase()) ):"FAILED == Next Audit : Excel expected 'boundary_end_date' is not matched with the Actual value(displayed into WO).";
						fnpMymsg("Successfully verified that Next Audit 'boundary_end_date' from excel is matched with the Actual value from WO.");
						
						
						
						
						
						NewlyCreatedAudit_Found = true;
						break;
					}	
				}else{
					break;
				}
				
			}
			
			if( !(NewlyCreatedAudit_Found) ){
				throw new Exception ("FAILED == Next Audit is not created in WO (Which is suppose to be created after clicking on RunIQAULN button from Oasis).");
			}
			
			*/
			
			
			
			
			
			//This is to check on Snapshot tab
			String NextAudit_type = table.get("NextAudit_Type");
			if (!(NextAudit_type.trim().equalsIgnoreCase("N/A"))) {
			
					String totalRowXpaths = OR.getProperty("WO_SnapshotsTAB_Audit_Table_Data")+"/tr";
					int totalRowsInWO_SnapshotsTAB_Audit_Table_Data=driver.findElements(By.xpath(totalRowXpaths)).size();
											
					boolean NewlyCreatedAudit_Found = false;
					for(int rowno=1; rowno<=totalRowsInWO_SnapshotsTAB_Audit_Table_Data; rowno++){
						String RowXpaths = OR.getProperty("WO_SnapshotsTAB_Audit_Table_Data")+"/tr["+rowno+"]";
						if(driver.findElements(By.xpath(RowXpaths)).size()>0){
							String Row_Text = fnpGetText_NOR(RowXpaths);
							if(Row_Text.toLowerCase().contains("created")){
								fnpMymsg("Successfully verified that Next Audit created successfully.");
								
								NextAudit_type = table.get("NextAudit_Type");
								assert ( Row_Text.toLowerCase().contains(NextAudit_type.toLowerCase()) ):"FAILED == Next Audit : Excel expected 'Type' is not matched with the Actual value(displayed into WO). ";
								fnpMymsg("Successfully verified that Next Audit 'Type' from excel is matched with the Actual value from WO.");
								
								
								String NextAudit_expected_StartDate_String = table.get("NextAudit_boundary_start_date");
								Date NextAuditExected_StartDate = new Date(NextAudit_expected_StartDate_String);
								Format Formatter1 = new SimpleDateFormat("dd-MMM-yyyy");
								String NextAudit_Expected_StartDate = Formatter1.format(NextAuditExected_StartDate);
								//System.out.println("========== Saty === NextAudit_expected_EndDate  ==== "+NextAudit_Expected_StartDate);
								assert ( Row_Text.toLowerCase().contains(NextAudit_Expected_StartDate.toLowerCase()) ):"FAILED == Next Audit : Excel expected 'boundary_start_date' is not matched with the Actual value(displayed into WO).";
								fnpMymsg("Successfully verified that Next Audit 'boundary_start_date' from excel is matched with the Actual value from WO.");
								
								
								
								
								
								String NextAudit_expectedEndDate_string = table.get("NextAudit_boundary_end_date");
								Date NextAuditExpectedEndDate = new Date(NextAudit_expectedEndDate_string);
								Format Formatter2 = new SimpleDateFormat("dd-MMM-yyyy");
								String NextAudit_Expected_EndDate = Formatter2.format(NextAuditExpectedEndDate);
								//System.out.println("========== Saty === NextAudit_expected_EndDate  ==== "+NextAudit_Expected_EndDate);
								assert ( Row_Text.toLowerCase().contains(NextAudit_Expected_EndDate.toLowerCase()) ):"FAILED == Next Audit : Excel expected 'boundary_end_date' is not matched with the Actual value(displayed into WO).";
								fnpMymsg("Successfully verified that Next Audit 'boundary_end_date' from excel is matched with the Actual value from WO.");
								
								
								
								
								
								NewlyCreatedAudit_Found = true;
								break;
							}	
						}else{
							break;
						}
						
					}
					
					if( !(NewlyCreatedAudit_Found) ){
						throw new Exception ("FAILED == Next Audit is not created in WO (Which is suppose to be created after clicking on RunIQAULN button from Oasis).");
					}
					
			
			
					
			} else {
				//nothing to do when next audit type is N/A				
			}
			
			
			
			
			
			//This is to check on Task tab
			//String NextAudit_type = table.get("NextAudit_Type");
			if (!(NextAudit_type.trim().equalsIgnoreCase("N/A"))) {
										
					fnpClick_OR("ISRTaskTab");
					
					String RowXpaths = OR.getProperty("TaskTabTable")+"/tr";
					int totalRowsInTaskTable=driver.findElements(By.xpath(RowXpaths)).size();
					//Assert.assertEquals(totalRowsInTaskTable, 2, "Total Task created at this moment should be only 2 but now their count is --"+totalRowsInTaskTable);
					if( (totalRowsInTaskTable==1) ){
						throw new Exception ("FAILED == Next Audit is not created in this WO (Which is suppose to be created after clicking on RunIQAULN button from Oasis).");
					}
					
					if (totalRowsInTaskTable>2) {
						throw new Exception ("FAILED == Only 1 next audit should be created in this WO (Which is suppose to be created after clicking on RunIQAULN button from Oasis).");
					} else {
						//nothing to do
					}
					
					
					
					firstCreatdTaskRowNo=fnpFindRow("TaskTabTable", firstCreatedTaskNo, taskAINoColIndex);
					
					int secondCreatedTaskRowNo=0;
					for(int rowno=1; rowno<=totalRowsInTaskTable; rowno++){
						if (rowno==firstCreatdTaskRowNo) {
							//nothing to do
						} else {
							secondCreatedTaskRowNo=rowno;
						}
					}
					
		
					int taskAITypeColIndex = fnpFindColumnIndex("TaskTabTable_HeaderRow", WOISOTaskTab_TaskAISummaryTableColName_TaskAIType);
					String secondCreatedAuditName = fnpFetchFromTable("TaskTabTable", secondCreatedTaskRowNo, taskAITypeColIndex);										 
					assert ( secondCreatedAuditName.toLowerCase().contains(NextAudit_type.toLowerCase()) ):"FAILED == NextAudit_Type : Excel expected 'NextAudit_Type' is not matched with the Actual value(displayed into WO). ";
					fnpMymsg("Successfully verified that Next Audit 'Type' from excel is matched with the Actual value from WO.");
					
					
					
					
					
								
					String secondCreatedTaskOpenIconXpath = OR.getProperty("TaskTabISR_DeskAudit_OpenIconPart1_usingRowNo") + secondCreatedTaskRowNo+ OR.getProperty("TaskTabISR_DeskAudit_OpenIconPart2_usingRowNo");
					fnpClick_NOR(secondCreatedTaskOpenIconXpath);	
					
					
					
					String secondCreatedTaskExpansionPanelXpath = OR.getProperty("ISRTaskExpansionPanelGrid_part1") + (secondCreatedTaskRowNo - 1) + OR.getProperty("ISRTaskExpansionPanelGrid_part2");
		
					
					
		
					String boundaryStartDateXpath_forSecondAudit = secondCreatedTaskExpansionPanelXpath + "//tr/td/label[contains(text(),'Boundary Start Date')]/../following-sibling::td/label";
					String boundaryEndDateXpath_forSecondAudit = secondCreatedTaskExpansionPanelXpath + "//tr/td/label[contains(text(),'Boundary End Date')]/../following-sibling::td/label";
		
					String boudaryStartDateInStringformatAtTaskTab_forSecondAudit = fnpGetText_NOR(boundaryStartDateXpath_forSecondAudit);
					textMessage = "Boundary Start Date present for Next Audit is ---" + boudaryStartDateInStringformatAtTaskTab_forSecondAudit;
					fnpMymsg(textMessage);
					fnpDisplayingMessageInFrame(textMessage, 5);
					
					sdfmt1 = new SimpleDateFormat("dd-MMM-yyyy");
					Date boudaryStartDateInDateformatAtTaskTab_forSecondAudit = sdfmt1.parse(boudaryStartDateInStringformatAtTaskTab_forSecondAudit);
		
					String boundaryEndDateInStringformatAtTaskTab_forSecondAudit = fnpGetText_NOR(boundaryEndDateXpath_forSecondAudit);
					fnpMymsg(textMessage);
					textMessage = "Boundary End Date present for Next Audit  is ---" + boundaryEndDateInStringformatAtTaskTab_forSecondAudit;
					fnpDisplayingMessageInFrame(textMessage, 5);
								
					Date boundaryEndDateInDateformatAtTaskTab_forSecondAudit = sdfmt1.parse(boundaryEndDateInStringformatAtTaskTab_forSecondAudit);
					
					
		
					 sdfmt2 = new SimpleDateFormat("MM/dd/yy");
		
					String expectedStartDate_string_forSecondAudit = table.get("NextAudit_boundary_start_date");
					////System.out.println("@  ---expected start date for Next Audit is ----"+expectedStartDate_string_forSecondAudit);
					Date expectedStartDate_forSecondAudit = sdfmt2.parse(expectedStartDate_string_forSecondAudit);
					String expectedEndDate_string_forSecondAudit = table.get("NextAudit_boundary_end_date");
					Date expectedEndDate_forSecondAudit = sdfmt2.parse(expectedEndDate_string_forSecondAudit);
					
					
					
					Date Exected_StartDate_forSecondAudit = new Date(expectedStartDate_string_forSecondAudit);
					 Formatter01 = new SimpleDateFormat("dd-MMM-yyyy");
					String Expected_StartDate_forSecondAudit = Formatter01.format(Exected_StartDate_forSecondAudit);
				//	//System.out.println("============= Expected_StartDate for Next Audit  ==== "+Expected_StartDate_forSecondAudit);
					assert ( boudaryStartDateInStringformatAtTaskTab_forSecondAudit.toLowerCase().contains(Expected_StartDate_forSecondAudit.toLowerCase()) ):"FAILED == Next Audit : Excel expected boundary_start_date < "+Expected_StartDate_forSecondAudit+" > is not matched with the Actual boundary_start_date < "+boudaryStartDateInStringformatAtTaskTab_forSecondAudit+" > displayed into WO.";
					fnpMymsg("Next Audit : Successfully verified that Excel expected boundary_start_date < "+Expected_StartDate_forSecondAudit+" > is matched with the Actual boundary_start_date < "+boudaryStartDateInStringformatAtTaskTab_forSecondAudit+" > displayed into WO.");
					
					
								
					Date Exected_EndDate_forSecondAudit = new Date(expectedEndDate_string_forSecondAudit);
					 Formatter02 = new SimpleDateFormat("dd-MMM-yyyy");
					String Expected_EndDate_forSecondAudit = Formatter02.format(Exected_EndDate_forSecondAudit);
					////System.out.println("==========  === Expected_EndDate  ==== "+Expected_EndDate_forSecondAudit);
					assert ( boundaryEndDateInStringformatAtTaskTab_forSecondAudit.toLowerCase().contains(Expected_EndDate_forSecondAudit.toLowerCase()) ):"FAILED == Next Audit : Excel expected boundary_End_date < "+Expected_EndDate_forSecondAudit+" > is not matched with the Actual boundary_End_date < "+boundaryEndDateInStringformatAtTaskTab_forSecondAudit+" > displayed into WO.";
					fnpMymsg("Next Audit : Successfully verified that Excel expected boundary_End_date < "+Expected_EndDate_forSecondAudit+" > is matched with the Actual boundary_End_date < "+boundaryEndDateInStringformatAtTaskTab_forSecondAudit+" > displayed into WO.");
					
					
			
			
			
	
			} else {
				//nothing to do when next audit type is N/A				
			}
			
			
			
	
							
			
			
			

			
			
		
			
			
			
		//	driver.quit();
		} catch (Throwable t) {


			//IsBrowserPresentAlready = false;
		//	fnpCommonFinalCatchBlock(t, ". \n\n    Hence SQF_Non_Cert_flow flow  is fail . ", "SQF_Non_Cert_flow_Fail_"+(count + 1)+"__");
		//	driver.quit(); // for time being
			
			
			
			fnpCommonFinalCatchBlock(t, "  SCFS_Non_Cert_flow   is fail . ", "SCFS_Non_Cert_flowFail_Client--"+(String) hashXlData.get("Corp/Facility#")+"_Standard--"+ (String) hashXlData.get("Standard")+"----");

		}

	}
	
	
	
	

	@AfterMethod
	public void reportDataSetResult() throws Throwable {
		
		fnpCommonCloseBrowsersAndKillProcess();
		
		
		if (skip)
				fnpMymsg("");
			//comment below line to not write skip in result column  OR not overwrite previous result if current test case for current data set is skipped
			//TestUtil.reportDataSetResult(currentSuiteXlsReference, this.getClass().getSimpleName(), count + 2, "SKIP");
		else if (fail) {
			isTestPass = false;
			TestUtil.reportDataSetResult(currentSuiteXlsReference, this.getClass().getSimpleName(), count + 2, "FAIL");
			fnpElapsedTime(currentSuiteName, this.getClass().getSimpleName(), currentScriptCode +" ====>     Client --> "+(String) hashXlData.get("Corp/Facility#")+"   And Standard --> "+ (String) hashXlData.get("Standard"), "FAIL");
		} else
			{
			TestUtil.reportDataSetResult(currentSuiteXlsReference, this.getClass().getSimpleName(), count + 2, "PASS");			
			//currentSuiteXlsReference.setCellData("Test Cases", "Last_Successful_Execution", rowNum, fnReturnDateWithTimeForExcel());
			currentSuiteXlsReference.setCellData(this.getClass().getSimpleName(), "Last_Successful_Execution", count + 2, fnReturnDateWithTimeForExcel());
			fnpElapsedTime(currentSuiteName, this.getClass().getSimpleName(), currentScriptCode +" ====>     Client --> "+(String) hashXlData.get("Corp/Facility#")+"   And Standard --> "+ (String) hashXlData.get("Standard"), "PASS");
			}
		skip = false;
		fail = false;
		
		hashXlData.clear();

	}

	@AfterTest
	public void reportTestResult() throws Throwable {
		
		//fnpCommonCloseBrowsersAndKillProcess();
		
		
		int rowNum = currentSuiteXlsReference.getCellRowNum("Test Cases", "TCID", this.getClass().getSimpleName());
		currentSuiteXlsReference.setCellData("Test Cases", "Last_Execution", rowNum, fnReturnDateWithTimeForExcel());

		if (isTestPass) {
			TestUtil.reportDataSetResult(currentSuiteXlsReference, "Test Cases", TestUtil.getRowNum(currentSuiteXlsReference, this.getClass().getSimpleName()), "PASS");
			currentSuiteXlsReference.setCellData("Test Cases", "Last_Successful_Execution", rowNum, fnReturnDateWithTimeForExcel());
			//fnpElapsedTime(currentSuiteName, this.getClass().getSimpleName(), currentScriptCode, "PASS");
		} else {
			TestUtil.reportDataSetResult(currentSuiteXlsReference, "Test Cases", TestUtil.getRowNum(currentSuiteXlsReference, this.getClass().getSimpleName()), "FAIL");
			//fnpElapsedTime(currentSuiteName, this.getClass().getSimpleName(), currentScriptCode, "FAIL");
		}
		
		fnpMymsg("=========================================================================================================================================");
		
		hashXlData.clear();

		fnpCommonCloseBrowsersAndKillProcess();

	}

	
	

	@AfterTest
	public void closebrowser() throws InterruptedException {

		// for time being,later uncomment it
		//driver.quit();
		//IsBrowserPresentAlready = false;
		setIsBrowserPresentAlready_false();
		killprocess();

	}

	
	
}
