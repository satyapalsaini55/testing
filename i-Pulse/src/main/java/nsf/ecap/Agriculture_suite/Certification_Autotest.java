package nsf.ecap.Agriculture_suite;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import nsf.ecap.Health_Science_suite.TestSuiteBase_HealthScience;
import nsf.ecap.ISR_suite.TestSuiteBase_ISR_suite;
import nsf.ecap.Work_Order_suite.NewNew_Draft_InReview_No_Fac;
import nsf.ecap.config.IPULSE_CONSTANTS;
import nsf.ecap.util.ErrorUtil;
import nsf.ecap.util.TestUtil;
import nsf.ecap.util.Xls_Reader;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.google.common.base.Throwables;
import static nsf.ecap.config.IPULSE_CONSTANTS.*;

//BS-02
public class Certification_Autotest extends TestSuiteBase_Agriculture_suite {
	
	
	SoftAssert softAssert = null ;
	Hashtable<String, String> ht = new Hashtable<String,String>();
	
	String productInFirstRow;
	String pathtoCopy;
	
	@BeforeTest
	public void checkTestSkip() throws Throwable {
		
		CM_BS01 = new Create_Membership();
		CM_BS01.checkTestSkip(this.getClass().getSimpleName());

		//hashXlData.clear();
		
		String className = "Create_Membership";
		//String className=classNameText;
		fnpLoadHashData(hashXlData, currentSuiteXls, className, 2);
		
		

	}

	 @Test(enabled = false)
	//@Test(priority = 1)
	public void Create() throws Throwable {
		CM_BS01.Create();

	}

	 @Test(enabled = false)
	//@Test(priority = 2, dependsOnMethods = { "Create" })
	public void Sites_Tab() throws Throwable {
		CM_BS01.Sites_Tab();

	}

	 @Test(enabled = false)
	//@Test(priority = 3, dependsOnMethods = { "Sites_Tab" })
	public void Contacts_Tab() throws Throwable {
		CM_BS01.Contacts_Tab();

	}

	 @Test(enabled = false)
	//@Test(priority = 4, dependsOnMethods = { "Contacts_Tab" })
	public void Products_Coverage_Tab() throws Throwable {
		CM_BS01.Products_Coverage_Tab();

	}

	 @Test(enabled = false)
	//@Test(priority = 5, dependsOnMethods = { "Products_Coverage_Tab" })
	public void Services_Tab() throws Throwable {
		CM_BS01.Services_Tab();

	}

	 @Test(enabled = false)
	//@Test(priority = 6, dependsOnMethods = { "Services_Tab" })
	public void Equipment_Tab() throws Throwable {
		CM_BS01.Equipment_Tab();

	}

	 @Test(enabled = false)
	//@Test(priority = 7, dependsOnMethods = { "Equipment_Tab" })
	public void Additional_Info_Tab() throws Throwable {
		CM_BS01.Additional_Info_Tab();

	}

	 @Test(enabled = false)
	//@Test(priority = 8, dependsOnMethods = { "Additional_Info_Tab" })
	public void Membership_Submission() throws Throwable {
		CM_BS01.Membership_Submission();
	}
	
	
	
	
	
	// @Test(enabled = false)
	@Test(priority = 1)
	public void Create_Membership() throws Throwable {
		
		
		
/*		
		//***********************************************************
		
		fnpLaunchBrowserAndLogin();
		test1();
		test();
		Thread.sleep(1);
	//*******************************************************
		
		*/
		


		
		
		
	//	if (alreadyCreatedMembershipThroughFirstScript.equals(null)) {
		if (alreadyCreatedMembershipThroughFirstScript==null) {
			fnpCommonCloseBrowsersAndKillProcess();
			hashXlData.clear();
			
/*			String className = "Create_Membership";
			//String className=classNameText;
			fnpLoadHashData(hashXlData, currentSuiteXls, className, 2);
			
			*/
			
/*			fnpMymsg("Just after clearing hashmap in create membership function");
			fnpPrintAllValuesInHashMap(hashXlData);
*/
			
			CM_BS01.Create();
			
/*			fnpMymsg("Just after create function");
			fnpPrintAllValuesInHashMap(hashXlData);
			*/
			
			CM_BS01.Sites_Tab();
			CM_BS01.Contacts_Tab();
			CM_BS01.Products_Coverage_Tab();
			CM_BS01.Services_Tab();
			CM_BS01.Equipment_Tab();
			CM_BS01.Additional_Info_Tab();
			CM_BS01.Membership_Submission();
			
		} else {
			//fnpSearchMembershipOnly(alreadyCreatedMembershipThroughFirstScript);
			start = new Date();//This start  time here refer from created membership onwards
		}
		

		
		
		
	}
	
	
	// @Test(enabled = false)
	@Test(priority = 9, dependsOnMethods = { "Create_Membership" })
	public void Complete_Technical_Review_Option1_Single_Site() throws Throwable {
		fnpMymsg(" **************************************************************");
		try {
			
			hashXlData.clear();
			fnpLoadHashData(hashXlData, currentSuiteXls, this.getClass().getSimpleName(), 2);

			// Step =>****Go to the Info tab > Edit> Change the status of the
			// Membership to Active > Membership Work Order Status:***********/
			
			if (fnpCheckElementDisplayedImmediately("EditWOBtn")) {
				fnpClick_OR("EditWOBtn");
			}
			
/*			if (fnpCheckElementPresenceImmediately("EditWOBtn")) {
				fnpClick_OR("EditWOBtn");
			}
			*/
			
				
			//fnpClick_OR("EditWOBtn");
			fnpClick_OR("iAg_InfoTab");
			fnpPFList("Membership_WO_Status_PFList", "Membership_WO_Status_PFListOptions", "ACTIVE");
			fnpLoading_wait();//using this on 04-03-2021 becasue now onwards after selecting any value in above 'Membership_WO_Status_PFList' loading is coming 
			Thread.sleep(30000);
			fnpWorkAroundToClickbottomFooter();
			Thread.sleep(30000);
			//fnpClick_OR("WO_Save_btn");
			//WebElement wb = fnpGetORObjectX_WhenMultipleObjectsHavingSameXpath("WO_Save_btn");
			//wb.click();
			
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("document.getElementById('mainForm:savebutton').click();");
			
			
			fnpWaitForVisibleHavingMultipleElements("TopMessage3");
			String SuccessfulMsg = fnpGetText_OR("TopMessage3");
			fnpMymsg("Top Message after changing the Membership statu to ACTIVE  ----" + SuccessfulMsg);

			// Step =>****Go to Snapshot > Verify that the task is created :
			// Membership Task (New/Renew)*******/
			fnpClick_OR("iAg_SnapshotTabLink");

			int taskTypeColIndex = fnpFindColumnIndex("TaskTabTable_HeaderRow", taskTable_TaskTypeColName);
			int taskDescriptionColIndex = fnpFindColumnIndex("TaskTabTable_HeaderRow", IPULSE_CONSTANTS.iAg_taskDesc_SnapshotTab);

			int newMemTaskTyperowNo;
			try {
				newMemTaskTyperowNo = fnpFindRow("TaskTabTable", IPULSE_CONSTANTS.iAg_taskType_NEWMEM, taskTypeColIndex);
			} catch (Throwable t) {
				throw new Exception("Either Task Type '" + IPULSE_CONSTANTS.iAg_taskType_NEWMEM + "' is not getting created OR  might be some other error. Getting this error -->"
						+ t.getMessage());
			}

			int newMemTaskDescriptonRowNo;
			try {
				newMemTaskDescriptonRowNo = fnpFindRow("TaskTabTable", IPULSE_CONSTANTS.iAg_task_NEWMEM_Description, taskDescriptionColIndex);
			} catch (Throwable t) {
				throw new Exception("Either this task '" + IPULSE_CONSTANTS.iAg_taskType_NEWMEM + "' 's description  is changed, as expected is '"
						+ IPULSE_CONSTANTS.iAg_task_NEWMEM_Description + "', OR might be   some other error. Getting this error -->" + t.getMessage());
			}

			// Step =>**** Expand the task > Open the Contract Review AI > Click
			// on Assign to me ,change it to In progress and click Save.
			// ***************/
			
			

			String innerTableDataXpathForAI = OR.getProperty("iAgSnapshotTab_InnerTableForActionItemsTableData_part1") + (newMemTaskTyperowNo - 1)
					+ OR.getProperty("iAgSnapshotTab_InnerTableForActionItemsTableData_part2");
			String innerTableHeaderXpathForAI = OR.getProperty("iAgSnapshotTab_InnerTableForActionItemsTableHeader_part1") + (newMemTaskTyperowNo - 1)
					+ OR.getProperty("iAgSnapshotTab_InnerTableForActionItemsTableHeader_part2");
			
		
		//	String openIconXpath = OR.getProperty("TaskTabISR_Task_OpenIconPart1") + task+ OR.getProperty("TaskTabISR_Task_OpenIconPart2");
			
		
			
			String xpathForExpandingNewMemTask = ".//*[@id='mainForm:tabView:taskDT_data']/tr[" + newMemTaskTyperowNo
					+ "]/td[1]/div[@class='ui-row-toggler ui-icon ui-icon-circle-triangle-e']";
			//fnpClick_OR_WithoutWait(xpathForExpandingNewMemTask);
			fnpClick_NOR_withoutWait(xpathForExpandingNewMemTask);
			

			fnpWaitForVisible_NotInOR(innerTableDataXpathForAI);

			
			//fnpProcessiAgAI( innerTableHeaderXpathForAI, innerTableDataXpathForAI, "ContractReview","Completed");
			fnpProcessiAgAI2(IPULSE_CONSTANTS.iAg_taskType_NEWMEM,IPULSE_CONSTANTS.iAg_task_NEWMEM_Description,"ContractReview","");
			fnpProcessiAgAI2(IPULSE_CONSTANTS.iAg_taskType_NEWMEM,IPULSE_CONSTANTS.iAg_task_NEWMEM_Description,"ContractReview","Completed");
			//fnpProcessiAgAI2(IPULSE_CONSTANTS.iAg_taskType_NEWMEM,IPULSE_CONSTANTS.iAg_task_NEWMEM_Description,"ContractReview","");
			
			
			
			//Bhavna told to comment this GG Interface related on 21-10-2020
/*			
			fnpCallWebService_GG_interface_Action_Items();
			driver.navigate().refresh();
			Thread.sleep(5000);
			if (fnpCheckElementEnabledImmediately_NOR(xpathForExpandingNewMemTask)) {
				fnpClick_NOR_withoutWait(xpathForExpandingNewMemTask);
				fnpWaitForVisible_NotInOR(innerTableDataXpathForAI);
				
			}
			  
			*/
			
			
			
			
			//fnpProcessiAgAI( innerTableHeaderXpathForAI, innerTableDataXpathForAI, "RegProducers","Completed");
			//fnpProcessiAgAI2(IPULSE_CONSTANTS.iAg_taskType_NEWMEM,IPULSE_CONSTANTS.iAg_task_NEWMEM_Description, "RegProducers","Completed");
			//fnpProcessiAgAI2(IPULSE_CONSTANTS.iAg_taskType_NEWMEM,IPULSE_CONSTANTS.iAg_task_NEWMEM_Description, "RegProducers","");
			//fnpProcessiAgAI2_tryingMultipleTimes(IPULSE_CONSTANTS.iAg_taskType_NEWMEM,IPULSE_CONSTANTS.iAg_task_NEWMEM_Description, "RegProducers","");
			fnpProcessiAgAI2_tryingMultipleTimes_2(IPULSE_CONSTANTS.iAg_taskType_NEWMEM,IPULSE_CONSTANTS.iAg_task_NEWMEM_Description, "RegProducers","");
			
			
			
			//fnpProcessiAgAI( innerTableHeaderXpathForAI, innerTableDataXpathForAI, "RegProducts","Completed");
			//fnpProcessiAgAI2(IPULSE_CONSTANTS.iAg_taskType_NEWMEM,IPULSE_CONSTANTS.iAg_task_NEWMEM_Description,"RegProducts","Completed");
			//fnpProcessiAgAI2(IPULSE_CONSTANTS.iAg_taskType_NEWMEM,IPULSE_CONSTANTS.iAg_task_NEWMEM_Description,"RegProducts","");
			//fnpProcessiAgAI2_tryingMultipleTimes(IPULSE_CONSTANTS.iAg_taskType_NEWMEM,IPULSE_CONSTANTS.iAg_task_NEWMEM_Description,"RegProducts","");
			fnpProcessiAgAI2_tryingMultipleTimes_2(IPULSE_CONSTANTS.iAg_taskType_NEWMEM,IPULSE_CONSTANTS.iAg_task_NEWMEM_Description,"RegProducts","");
			
			
			
			
			
			//fnpProcessiAgAI( innerTableHeaderXpathForAI, innerTableDataXpathForAI, "AcceptProducts","Completed");
			//fnpProcessiAgAI2(IPULSE_CONSTANTS.iAg_taskType_NEWMEM,IPULSE_CONSTANTS.iAg_task_NEWMEM_Description, "AcceptProducts","Completed");
			//fnpProcessiAgAI2(IPULSE_CONSTANTS.iAg_taskType_NEWMEM,IPULSE_CONSTANTS.iAg_task_NEWMEM_Description, "AcceptProducts","");
			//fnpProcessiAgAI2_tryingMultipleTimes(IPULSE_CONSTANTS.iAg_taskType_NEWMEM,IPULSE_CONSTANTS.iAg_task_NEWMEM_Description, "AcceptProducts","");
			fnpProcessiAgAI2_tryingMultipleTimes_2(IPULSE_CONSTANTS.iAg_taskType_NEWMEM,IPULSE_CONSTANTS.iAg_task_NEWMEM_Description, "AcceptProducts","");
			
			
			

			
			// taskTypeColIndex = fnpFindColumnIndex("TaskTabTable_HeaderRow", taskTable_TaskTypeColName);
			//int taskDescriptionColIndex = fnpFindColumnIndex("TaskTabTable_HeaderRow", IPULSE_CONSTANTS.iAg_taskDesc_SnapshotTab);

			int annualAuditTaskTyperowNo;
			try {
				annualAuditTaskTyperowNo = fnpFindRow("TaskTabTable", IPULSE_CONSTANTS.iAg_taskType_AnnualAudit, taskTypeColIndex);
			} catch (Throwable t) {
				throw new Exception("Either Task Type '" + IPULSE_CONSTANTS.iAg_taskType_AnnualAudit + "' is not getting created OR  might be some other error. Getting this error -->"
						+ t.getMessage());
			}

			int annualAuditDescriptonRowNo;
			try {
				annualAuditDescriptonRowNo = fnpFindRow("TaskTabTable", IPULSE_CONSTANTS.iAg_task_AnnualAudit_Description, taskDescriptionColIndex);
			} catch (Throwable t) {
				throw new Exception("Either this task '" + IPULSE_CONSTANTS.iAg_taskType_AnnualAudit + "' 's description  is changed, as expected is '"
						+ IPULSE_CONSTANTS.iAg_task_AnnualAudit_Description + "', OR might be   some other error. Getting this error -->" + t.getMessage());
			}

/*
			// -2 is here because task row is for Annual is here 3  but its action item have have index 3-2=1, hence -2 logic is used here
			 innerTableDataXpathForAI = OR.getProperty("iAgSnapshotTab_InnerTableForActionItemsTableData_part1") + (annualAuditTaskTyperowNo - 2)
					+ OR.getProperty("iAgSnapshotTab_InnerTableForActionItemsTableData_part2");
			 innerTableHeaderXpathForAI = OR.getProperty("iAgSnapshotTab_InnerTableForActionItemsTableHeader_part1") + (annualAuditTaskTyperowNo - 2)
					+ OR.getProperty("iAgSnapshotTab_InnerTableForActionItemsTableHeader_part2");
			

			 */
			 


				
				
			// -1 (x-1) is here because task row is for Annual is here 1  but its action item table have  index 1-1=0, hence -1 logic is used here
			 innerTableDataXpathForAI = OR.getProperty("iAgSnapshotTab_InnerTableForActionItemsTableData_part1") + (annualAuditTaskTyperowNo - 1)
					+ OR.getProperty("iAgSnapshotTab_InnerTableForActionItemsTableData_part2");
			 innerTableHeaderXpathForAI = OR.getProperty("iAgSnapshotTab_InnerTableForActionItemsTableHeader_part1") + (annualAuditTaskTyperowNo - 1)
					+ OR.getProperty("iAgSnapshotTab_InnerTableForActionItemsTableHeader_part2");
			

			 

			
			String xpathForExpandingAnnualAuditTask = ".//*[@id='mainForm:tabView:taskDT_data']/tr[" + annualAuditTaskTyperowNo
					+ "]/td[1]/div[@class='ui-row-toggler ui-icon ui-icon-circle-triangle-e']";
			fnpClick_NOR_withoutWait(xpathForExpandingAnnualAuditTask);
			

			fnpWaitForVisible_NotInOR(innerTableDataXpathForAI);
			
		
			

			//Removing or commenting this on 06-02-2019, no longer to perform this ai performAudit ai
			//fnpProcessiAgAI2(IPULSE_CONSTANTS.iAg_taskType_AnnualAudit,IPULSE_CONSTANTS.iAg_task_AnnualAudit_Description,"PerformAudit","Assigned");
	
			
			
/*			
			fnpDisplayingMessageInFrame("Now script is going to fetching the values and inserting into the excel.", 180);
			fnpFetchingAndInsertingValuesInUploadingExcel( ) ;
			fnpDisplayingMessageInFrame("Now script has  inserted the values into the excel.", 60);
			
			
			fnpCommonClickTopMainMenu("Menu", "FirstRefrenceLinkJustBelowToMenu", "Import_Audit_data_from_Excel", "AuditImport_TopPageHeading");
			driver.findElement(By.xpath(OR.getProperty("BrowseBtnOnAuditImportScreen"))).sendKeys(System.getProperty("user.dir") + "\\docs\\iAg2\\Upload\\Upload2\\B5.2 Test_PL04.xlsm");

			Thread.sleep(10000);
			
			
			int whileCounter=0;
			while(fnpCheckElementDisplayedImmediately("CancelUploadBtn")){
				whileCounter++;
				fnpMymsg("It is still uploading --"+whileCounter);
				Thread.sleep(1000);
				
				if (whileCounter > Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					throw new Exception("File is not uploaded successfully as it still showing cancel upload button " +
							"after --"+Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))+" seconds.");
				}
				
			}
			
			fnpClick_OR("ValidateBtn");
			
			if (fnpCheckElementPresenceImmediately("UploadedExcelValidationError")) {
				throw new Exception("Application throws 'File Validation error'. See attached screenshot for reference.");
				
			}
			
			*/

			//Removing or commenting this on 06-02-2019, no longer to perform this ai performAudit ai
		//	fnpProcessiAgAI2(IPULSE_CONSTANTS.iAg_taskType_AnnualAudit,IPULSE_CONSTANTS.iAg_task_AnnualAudit_Description,"PerformAudit","Completed");
			

			//Removing or commenting this on 06-02-2019, no longer to perform this ai TechnicalReview ai
		//	fnpProcessiAgAI2(IPULSE_CONSTANTS.iAg_taskType_AnnualAudit,IPULSE_CONSTANTS.iAg_task_AnnualAudit_Description,"TechnicalReview","Assigned");
			

			
			
			
			
			
			//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			/****************New Code/ New Logic***************************************************************************************************************************/
			
			
/*			
			int taskTypeColIndex = fnpFindColumnIndex("TaskTabTable_HeaderRow", taskTable_TaskTypeColName);
			int taskDescriptionColIndex = fnpFindColumnIndex("TaskTabTable_HeaderRow", IPULSE_CONSTANTS.iAg_taskDesc_SnapshotTab);

			
			int newMemTaskTyperowNo = fnpFindRow("TaskTabTable", taskType, taskTypeColIndex);
			int newMemTaskDescriptonRowNo = fnpFindRow("TaskTabTable", taskDesc, taskDescriptionColIndex);

			
			
			String innerTableDataXpathForAI = OR.getProperty("iAgSnapshotTab_InnerTableForActionItemsTableData_part1") + (newMemTaskTyperowNo - 1)
					+ OR.getProperty("iAgSnapshotTab_InnerTableForActionItemsTableData_part2");
			String innerTableHeaderXpathForAI = OR.getProperty("iAgSnapshotTab_InnerTableForActionItemsTableHeader_part1") + (newMemTaskTyperowNo - 1)
					+ OR.getProperty("iAgSnapshotTab_InnerTableForActionItemsTableHeader_part2");
			
		
			*/
			
			int ActionItemColIndex = fnpFindColumnIndex_NOR(innerTableHeaderXpathForAI, "Action Item #");
			
			int aiNameColIndex = fnpFindColumnIndex_NOR(innerTableHeaderXpathForAI, "Action Item Name");
			
			String aiName="PerformAudit";
			int actionItemInfoRowNo;
			String msg ;
			try{
				 actionItemInfoRowNo = fnpFindRow_NOR(innerTableDataXpathForAI, aiName, aiNameColIndex);
				 if (actionItemInfoRowNo>0) {
					 fnpMymsg(aiName+" is generated.");
					
				} else {
					 msg = "Either this AI '"+aiName+"' is not present or its name is changed.";
					fnpMymsg(msg);
					throw new Exception(msg);
				}
			}catch(Throwable t){
				fnpMymsg("Either this AI '"+aiName+"' is not present or its name is changed. ");
				throw new Exception("Either this AI '"+aiName+"' is not present or its name is changed as script getting this error --"+t.getMessage());
			}
			
			
			
		//	int auditColIndex = fnpFindColumnIndex("AuditTableAtSnapshotTabIniAg_HeaderRow", "Audit #");
		//	String auditValueInFirstRow = fnpFetchFromTable("AuditTableAtSnapshotTabIniAgTable_Data", 1, auditColIndex);
			
			
			
			
			//String Audit=auditValueInFirstRow;	
		//	long Audit=Long.parseLong(auditValueInFirstRow);
			
			
			/*	
			String cmi="CMi C0466403";
			String GGN="4059883267907";
			//long GGN=405988319;
			String Site="C0466405";
			//String Audit_String="1949387";
			//String Audit_String="1950060";
			 long Audit=2278189;
			 String Audit_Date="";
			// Audit_Date = "04-06-2018"; //not worked
			// Audit_Date = "04-6-2018"; //not worked
			// Audit_Date = "4-6-2018"; //working fine
			 Audit_Date = "19-2-2019"; 
			 
			 
			 */
			
					
			workOrderNo = fnpGetText_OR("Membership_WO_No");
			
			String cmi=fnpGetText_OR("MembershipNo").trim();
			fnpMymsg("Cmi value is---"+cmi);
			
			String GGN=fnpGetText_OR("GGN").trim();
			fnpMymsg("GGN value is---"+GGN);

			

			
			fnpClick_OR("iAg_InfoTab");	
			String site=fnpGetText_OR("PPUNo_atInfoTab").trim();
			fnpMymsg("Site/PPU value is---"+site);
					
			fnpClick_OR("iAg_SnapshotTabLink");	
					
			int auditColIndex = fnpFindColumnIndex("AuditTableAtSnapshotTabIniAg_HeaderRow", "Audit #");
			String auditValueInFirstRow = fnpFetchFromTable("AuditTableAtSnapshotTabIniAgTable_Data", 1, auditColIndex).trim();			
			//String Audit=auditValueInFirstRow;	
			long Audit=Long.parseLong(auditValueInFirstRow);	
			fnpMymsg("Auditvalue is---"+Audit);
			
			String Audit_Date;
			int boundaryScheduleDateColIndex = fnpFindColumnIndex("AuditTableAtSnapshotTabIniAg_HeaderRow", "Boundary Scheduled Date");
			String boundaryScheduleDateValueInFirstRow = fnpFetchFromTable("AuditTableAtSnapshotTabIniAgTable_Data", 1, boundaryScheduleDateColIndex).trim();
			
			if (boundaryScheduleDateValueInFirstRow.equalsIgnoreCase("")) {
				throw new Exception("Boundary Scheduled Date is not coming at snapshot tab.");
			}else{
				String[] auditDateArray=boundaryScheduleDateValueInFirstRow.split(" - ");
				int arrayLen=auditDateArray.length;
				if (arrayLen==2) {
					Audit_Date=auditDateArray[0];
				} else {
					throw new Exception("Boundary Date format is changed as separator ' - ' is changed.");
				}
				
			}
			
			fnpMymsg("boundaryScheduleDateValueInFirstRow is---"+boundaryScheduleDateValueInFirstRow);
			fnpMymsg("Our interest in this Audit_Date is---"+Audit_Date);
			
			
			 SimpleDateFormat sdfmt11 = new SimpleDateFormat("dd-MMM-yyyy");
			 Date dat1 = sdfmt11.parse(Audit_Date);
			 fnpMymsg("Our dat1 is --"+dat1);
			 
			 SimpleDateFormat sdfmt22 = new SimpleDateFormat("d-MM-yyyy");
			 String dat=sdfmt22.format(dat1).trim();
			 
/*			 SimpleDateFormat sdfmt33 = new SimpleDateFormat("dd-MMM-yy");
			 String dat=sdfmt33.format(dat1).trim();
			 */
			 
			 
			 
			 fnpMymsg("Our dat is --"+dat);
			
			 fnpMymsg("We are passing this value--"+dat);
			
			
			
			TestSuiteBase_Agriculture_suite.fnpFetchingAndInsertingValuesIn_Certification_AutoTest( cmi, GGN, site, Audit, dat);
			//TestSuiteBase_Agriculture_suite.fnpFetchingAndInsertingValuesInUploadingExcel();
			Thread.sleep(1000);
			
			
			
			fnpCommonClickTopMainMenu("Menu", "FirstRefrenceLinkJustBelowToMenu", "Import_Audit_data_from_Excel", "AuditImport_TopPageHeading");
			
			
			String fileName = null;
			switch (env) {

			

			case "Test":
				fileName = iAg_Certification_Autotest_FileName_TEST;
				break;
			
			case "Staging":
				fileName = iAg_Certification_Autotest_FileName_STAGE;
				break;
				
			case "Stage":
				fileName = iAg_Certification_Autotest_FileName_STAGE;
				break;
				

			}
			
			
			driver.findElement(By.xpath(OR.getProperty("BrowseBtnOnAuditImportScreen"))).sendKeys(System.getProperty("user.dir") + "\\docs\\iAg_Certification_Autotest\\ModifiedThoroughScript\\"+fileName);

			 
			
			
			Thread.sleep(10000);
			
			
			int whileCounter=0;
			while(fnpCheckElementDisplayedImmediately("CancelUploadBtn")){
				whileCounter++;
				fnpMymsg("It is still uploading --"+whileCounter);
				Thread.sleep(1000);
				
				if (whileCounter > Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					throw new Exception("File is not uploaded successfully as it still showing cancel upload button " +
							"after --"+Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))+" seconds.");
				}
				
			}
			
			//fnpClick_OR("ValidateBtn");
			fnpClick_OR("ValidateBtnid");
			
			if (fnpCheckElementPresenceImmediately("UploadedExcelValidationError")) {
				throw new Exception("Application throws 'File Validation error'. See attached screenshot for reference.");
				
			}
			


			
			fnpClick_OR("Continue_1_Btnid");
			fnpClick_OR("Continue_2_Btnid");
			
			
			int addedToColIndex = fnpFindColumnIndex("AuditImportTable_iAg_HeaderRow", "Added to");
			String addedToValue = fnpFetchFromTable("AuditImportTable_Ag_Table_Data", 1, addedToColIndex);
			fnpMymsg("Added to column has this value---"+addedToValue);
			
/*			long expectedValueInAddedToColumn_string=Integer.parseInt(auditValueInFirstRow)+1;
			String expectedValueInAddedToColumn=String.valueOf(expectedValueInAddedToColumn_string);
			*/
			if (auditValueInFirstRow.trim().equalsIgnoreCase(addedToValue.trim())) {
			//if (expectedValueInAddedToColumn.trim().equalsIgnoreCase(addedToValue.trim())) {
				fnpMymsg("Correct Audit is present under Added To column i.e. ---"+addedToValue);
				
			} else {
				msg="Correct Audit is NOT present under Added To column because expected is this--'"+auditValueInFirstRow+"' but actual present is --'"+addedToValue+"'.";
				fnpMymsg(msg);
				throw new Exception(msg);
			}
			
			//fnpClick_OR("FinishBtn");
			fnpClick_OR("FinishBtn_id");
			
			
			fnpSearchWorkOrderOnly(workOrderNo);
			hashXlData.put("currentwo", workOrderNo);
			
			fnpClickALinkInATable_advance_havingATagInside( OR.getProperty("iAgSnapshotTab_AuditTableData_contains"),  auditValueInFirstRow,  1);
			fnpMymsg("Clicked on  Audit no. '" + auditValueInFirstRow + "' in Audit Table.");
			fnpLoading_wait();
			
			//Fetch Status value
			//String status="";
			
			
			String statusAtTop=fnpGetText_OR("ViewAuditStatusAtTop");
			Assert.assertTrue(statusAtTop.equalsIgnoreCase("SUBMITTED"), "Expected status of the Audit at top should be SUBMITTED.");
/*			
			String statusInsideInfoTab=fnpGetText_OR("ViewAuditStatusAtInfoTabInside");
			Assert.assertTrue(statusInsideInfoTab.equalsIgnoreCase("SUBMITTED"), "Expected status of the Audit inside Info Tab should be SUBMITTED.");
			*/
			
			
			
			fnpClick_OR("ISRBackBtn");//id of this Back button is same as of Back button in ISR at view audit screen
			
			//Step =>**** Click on the Products & Coverage tab of Membership WO**********/
			fnpClick_OR("iAg_MembershipWO_ProductsCoverageTab");
			
			
			int auditDateColNo=fnpFindColumnIndex("ProdcutsAndCoverageTab_Table_HeaderRow", "Audit Date");
			String actualAuditDateValue=fnpFetchFromTable("ProdcutsAndCoverageTab_Table_data", 1, auditDateColNo);
			Assert.assertTrue(Audit_Date.equalsIgnoreCase(actualAuditDateValue), "Expected Audit date here is ---'"+Audit_Date+"' but present here is --"+actualAuditDateValue);
			
			
			fnpClick_OR("FirstProductExpandTriangleIcon");
			
			int auditDateInnerTableColNo=fnpFindColumnIndex("ProdcutsAndCoverageTab_FirstInnerProductTable_HeaderRow", "Audit Date");
			String auditDateInnerTableValue=fnpFetchFromTable("ProdcutsAndCoverageTab_FirstInnerProductTable_data", 1, auditDateInnerTableColNo);
			Assert.assertTrue(Audit_Date.equalsIgnoreCase(auditDateInnerTableValue), "Expected Audit date here is ---'"+Audit_Date+"' but present here in inner table is --"+auditDateInnerTableValue);

			fnpClick_OR("iAg_SnapshotTabLink");
			
		
			
			
			innerTableDataXpathForAI = OR.getProperty("iAgSnapshotTab_InnerTableForActionItemsTableData_part1") + (annualAuditTaskTyperowNo - 1)
						+ OR.getProperty("iAgSnapshotTab_InnerTableForActionItemsTableData_part2");
				 innerTableHeaderXpathForAI = OR.getProperty("iAgSnapshotTab_InnerTableForActionItemsTableHeader_part1") + (annualAuditTaskTyperowNo - 1)
						+ OR.getProperty("iAgSnapshotTab_InnerTableForActionItemsTableHeader_part2");
				

				 

				
			xpathForExpandingAnnualAuditTask = ".//*[@id='mainForm:tabView:taskDT_data']/tr[" + annualAuditTaskTyperowNo
						+ "]/td[1]/div[@class='ui-row-toggler ui-icon ui-icon-circle-triangle-e']";
			fnpClick_NOR_withoutWait(xpathForExpandingAnnualAuditTask);
			Thread.sleep(1000);
			fnpProcessiAgAI2(IPULSE_CONSTANTS.iAg_taskType_AnnualAudit,IPULSE_CONSTANTS.iAg_task_AnnualAudit_Description,"TechnicalReview","Assigned");	

			fnpWaitForVisible_NotInOR(innerTableDataXpathForAI);
			
/*			
			workOrderNo = fnpGetText_OR("Membership_WO_No");
			fnpClickTopHomeMenu();
			*/
	
					
			hashXlData.put("currentwo", workOrderNo);
			
			
			
			ht.putAll(hashXlData);
			fnpClickTopHomeMenu();
			
			// -------Audit_awaiting_Technical_Review_Assigned_to_me -------------------
			TestSuiteBase_HealthScience.fnpCommonAlertGeneratedVerificationHS(ht, "Audit_awaiting_Technical_Review_Assigned_to_me", "Audit_awaiting_Technical_Review_Assigned_to_me_AlertTable_header",
					"WO #", "Audit_awaiting_Technical_Review_Assigned_to_me_AlertTable", "Audit_awaiting_Technical_Review_Assigned_to_me_Alert_WO_filterTxtBox",
					workOrderNo);
			// ---------------------------------------------

			driver.findElement(By.linkText(workOrderNo)).click();
			fnpLoading_wait();
			
			fnpClick_OR("EditWOBtn");
			
			
			//fnpClick_OR("iAg_ProductsCoverageTab");
			fnpClick_OR("iAg_ProductsCoverageTab_whenComingFromAlerts");
			
			
			// do some steps for making notes for product which we will use in further steps
			
			
			int productsColIndex = fnpFindColumnIndex("RegisteredProducts_Table_HeaderRow", "Product");
			String productCodeWithProductNameInFirstRow = fnpFetchFromTable("RegisteredProducts_Table_data", 1, productsColIndex);
			
			String[] productCodeWithProductName = productCodeWithProductNameInFirstRow.split("-");
			//String productInFirstRow; decalaring this at top
			if (productCodeWithProductName.length==2) {
				 productInFirstRow=productCodeWithProductNameInFirstRow.split("-")[1].trim();
			} else {
				throw new Exception ("Product Code with Product Name is not displayed " +
						"properly as script is looking for product name after hypen (-). Actual complete value in column" +
						"is --'"+productCodeWithProductNameInFirstRow+"'.");
			}
			
			
			
			
			
			
			
			
			fnpClickTopHomeMenu();
			
			// -------Audit_awaiting_Technical_Review_Assigned_to_me -------------------
			TestSuiteBase_HealthScience.fnpCommonAlertGeneratedVerificationHS(ht, "Audit_awaiting_Technical_Review_Assigned_to_me", "Audit_awaiting_Technical_Review_Assigned_to_me_AlertTable_header",
					"WO #", "Audit_awaiting_Technical_Review_Assigned_to_me_AlertTable", "Audit_awaiting_Technical_Review_Assigned_to_me_Alert_WO_filterTxtBox",
					workOrderNo);
			// ---------------------------------------------
			
			
			int colIndex = fnpFindColumnIndex("Audit_awaiting_Technical_Review_Assigned_to_me_AlertTable_header", "Task #");
			String s = fnpFetchFromTable("Audit_awaiting_Technical_Review_Assigned_to_me_AlertTable", 1, colIndex);
			
			driver.findElement(By.linkText(s)).click();
			fnpLoading_wait();
			

			
			
			fnpWaitForVisible("iAg_ecapSPATaskHeader");
			
			String headerValue = fnpGetText_OR("iAg_ecapSPATaskHeader");
			
			if (!(headerValue.equalsIgnoreCase(iAg_Technical_Review_in_Progress_headerName))) {
			    softAssert = new SoftAssert();
				   //Assertion failing
/*			    
				   softAssert.fail("Technical Reivew in progress header name have been changed as actual is--'" +
						   headerValue+"' and expected is --'"+iAg_Technical_Review_in_Progress_headerName+"'.");
						     fnpDesireScreenshot("iAg_Technical_Review_in_Progress_headerName");
				   */
				   
				   
				   msg="Technical Reivew in progress header name have been changed as actual is--'" +
						   headerValue+"' and expected is --'"+iAg_Technical_Review_in_Progress_headerName+"'.";
				   fnpMymsg(msg);
				   throw new Exception(msg);
				 
			} else {
				fnpMymsg("Technical Review in Progress screen is opened successfully.");
			}
			

			String CurrentCertStatus = fnpGetText_OR("iAg_ecapSPATask_CurrentCertStatus");
			String expectedCertStatus="Awaiting Certification";
			if (!(CurrentCertStatus.equalsIgnoreCase(expectedCertStatus))) {
				   throw new Exception("Technical Reivew's current cert status is not '"+expectedCertStatus+"' as actual is--'" +
						   CurrentCertStatus+"' and expected is --'"+expectedCertStatus+"'.");
			
			} else {
				fnpMymsg("Technical Reviews's current cert status is ---"+expectedCertStatus);
			}
			
			
			fnpClick_InSAM_OR("iAg_ecapSPA_CurrentAuditsTab");
			
			String CurrentAuditStatus = fnpGetText_OR("iAg_ecapSPATask_AuditStatus");
			//String expectedAuditStatus="CREATED";
			String expectedAuditStatus="SUBMITTED";
			if (!(CurrentAuditStatus.equalsIgnoreCase(expectedAuditStatus))) {
				   throw new Exception("Technical Reivew's Audit status is not '"+expectedAuditStatus+"' as actual is--'" +
						   CurrentAuditStatus+"' and expected is --'"+expectedAuditStatus+"'.");
			
			} else {
				fnpMymsg("Technical Reviews's Audit status is ---"+expectedAuditStatus);
			}
			
			fnpClick_OR_WithoutWait("iAg_ViewProductsBtn");
			Thread.sleep(1000);
			
			
			String productName = fnpGetText_OR("iAg_ProductNameAtSPA");
			String expectedProductName=productInFirstRow;
			if (!(productName.equalsIgnoreCase(expectedProductName))) {
				   throw new Exception("Product displayed at ecapSPA is not same as displayed at Product Coverage tab because  actual is--'" +
						   productName+"' and expected is --'"+expectedProductName+"'.");
			
			} else {
				fnpMymsg("Product displayed at ecapSPA is  same as displayed at Product Coverage tab.");
			}
			
			
			
			
			
			fnpGetORObjectX("iAg_ecapSPA_ProductPopUpCloseLink").click();
			

			//commenting below line of opening Oasis on the request of Sudhir on 06-09-2018
			//softAssert=fnpVerifyOasisIsOpeningOrNotFromiAgAndReturnBack("Technical Review","iAg_ecapSPA_JumpToOasisBtn", softAssert);
			
			
			
			//fnpGetORObjectX("iAg_ecapSPA_DecisionTaskTab").click(); //PUKA-6072
			
			
			fnpClick_InSAM_OR("iAg_ecapSPA_DecisionTaskTab");
			
			String currentProduct=fnpGetText_OR("iAg_ecapSPA_DecisionTaskTab_ProductTable_FirstProduct");
			 expectedProductName=productInFirstRow;
			if (!(currentProduct.equalsIgnoreCase(expectedProductName))) {
				   throw new Exception("Product displayed in Technical Review Task at ecapSPA's Decision Task Tab is not same as displayed at Product Coverage tab because  actual is--'" +
						   currentProduct+"' and expected is --'"+expectedProductName+"'.");
			
			} else {
				fnpMymsg("Product displayed in Technical Review Task  at ecapSPA's Decision Task Tab  is  same as displayed at Product Coverage tab.");
			}
			
			
			
			
			Select actionDropDown=new Select(fnpGetORObjectX("iAg_ecapSPA_DecisionActionDropDown"));
			actionDropDown.selectByVisibleText("Ready for Certification Decision");
			
			//fnpGetORObjectX("iAg_ecapSPA_NoteToCertifierAuditor").sendKeys("XXXX");
			fnpGetORObjectX("iAg_ecapSPA_NoteToCertifierAuditor").sendKeys("Testing");
			
			//Clicking this button takes you to the iPulse Alert page
			fnpClick_InSAM_OR("iAg_ecapSPA_confirmBtn");
			
/*			
			//if there is any alert for ok then click it otherwise proceed further, not exception throw here
			try{
			driver.switchTo().alert().accept();
			}catch(Throwable t){
				//nothing
			}
			
			if (fnpCheckElementDisplayedImmediately("Ok_button_AlertMessage_AfterClickingConfirmButton")) {
				fnpClick_InSAM_OR("Ok_button_AlertMessage_AfterClickingConfirmButton");
			}
			*/
			
			String alertMessage=fnpGetText_OR("iAg_ecapSPA_alertMessage");
			String expected_alertmsg="Work Order Certification details updated";
			if (alertMessage.contains(expected_alertmsg)) {
				fnpMymsg("Alert message is --"+alertMessage);
			} else {
				fnpMymsg("Alert message is --"+alertMessage);
				msg="Either some error or validation is thrown by application because expected alert message is '"+expected_alertmsg+"' but here it is coming '"+alertMessage+"'.";
				fnpMymsg(msg);
				throw new Exception(msg);
			}
			fnpClick_InSAM_OR("Ok_button_AlertMessage_AfterClickingConfirmButton");
			
			fnpClick_InSAM_OR("iAg_ecapSPA_BackToWOBtn");
	
			
			
/*			
			//ESPA-118 , now it will come to snapshot tab of wo
			if (!(fnpCheckElementDisplayed("ShowAlertsBtn", 20))) {
				fnpClick_OR("AlertTabLink");
			}
			
			fnpWaitForVisible("ShowAlertsBtn");
			
			*/
			
			//fnpClick_OR("xpathForAck");
			
			try{
				
				//Commented for time being on request from Sudhir on 04-09-2020 as currently it is taking to alert page instead of snapshot and it will take time to fix it.
				//ESPA-118 , now it will come to snapshot tab of wo
				fnpWaitForVisible("iAg_SnapshotTabLink");
			}catch(Throwable t){
				//String msg="After clicking 'Confirm' button on 'Technical Review' task,it is not taking you to home/Alert screen, hence failed.";
				// msg="After clicking 'Confirm' button on 'Technical Review' task,it is not taking you to snapshot screen, hence failed.";
				  msg="After clicking 'Back To WO' button on 'Technical Review (in Progress)' screen in ecapSPA ,it is not taking you to snapshot screen, hence failed.";
				fnpMymsg(msg );
				throw new Exception(msg);
			}
			
			
			//fnpLoading_wait_In_SAM();
			
		
/*
			String a=hashXlData.get("currentwo");
			System.out.println("Value of current wo before loading other values is --"+a);
			fnpLoadHashData(hashXlData, currentSuiteXls, this.getClass().getSimpleName(), 2);
			Thread.sleep(10);
			String b=hashXlData.get("currentwo");
			
			*/
			

		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "  Complete_Technical_Review_Option1_Single_Site is fail . ", "Complete_Technical_Review_Option1_Single_Site");

		}
		
		finally{
			//Collates the assertion results and marks test as pass or fail
			if (softAssert!=null) {
				fail = true;
				isTestPass = false;	
				softAssert.assertAll();
			}
			

		}


	}
	
	
	// @Test(enabled = false)
	//@Test(priority = 10, dependsOnMethods = { "Membership_Submission" })
	@Test(priority = 10, dependsOnMethods = { "Complete_Technical_Review_Option1_Single_Site" })
	//@Test(priority = 10)
	public void Certification() throws Throwable {
		fnpMymsg(" **************************************************************");
		try {
			
			fnpClickTopHomeMenu();
			
			
/*			fnpWaitForVisible("Home_topLink_id");
			WebElement Home = fnpGetORObjectX("Home_topLink_id");
			Actions action = new Actions(driver);
			action.moveToElement(Home).perform();

			fnpWaitTillVisiblityOfElementNClickable_OR("Home_topLink_id");
			fnpGetORObjectX("Home_topLink_id").click();
			fnpLoading_wait();
			
			if ((fnpCheckElementDisplayed("xpathForAck", 20))) {
				fnpClick_OR("xpathForAck");
			}
			
			
			if (!(fnpCheckElementDisplayed("ShowAlertsBtn", 20))) {
				fnpClick_OR("AlertTabLink");
			}
			fnpWaitForVisible("ShowAlertsBtn");
			fnpWaitTillVisiblityOfElementNClickable_OR("ShowAlertsBtn");
			*/
			
			
			Hashtable<String, String> a = ht;
			HashMap<String, String> b = hashXlData;
			ht.putAll(hashXlData);
			
			String a2=hashXlData.get("currentwo");
			System.out.println("Value of current wo before loading other values is --"+a2);
			fnpLoadHashData(hashXlData, currentSuiteXls, this.getClass().getSimpleName(), 2);
			Thread.sleep(10);
			String b2=hashXlData.get("currentwo");
			
			// -------Certification_Requests_Unassigned -------------------
			TestSuiteBase_HealthScience.fnpCommonAlertGeneratedVerificationHS(ht, "Certification_Requests_Unassigned", "Certification_Requests_Unassigned_AlertTable_header",
					"WO #", "Certification_Requests_Unassigned_AlertTable", "Certification_Requests_Unassigned_Alert_WO_filterTxtBox",
					workOrderNo);
			// ---------------------------------------------
			
			
			
			driver.findElement(By.linkText(workOrderNo)).click();
			fnpLoading_wait();
			
			try{
				fnpWaitForVisible("iAg_SnapshotTabLink");
			}catch(Throwable t){
				String msg="Snapahsot screen is not getting opened after clicking wo no. from alert.";
				fnpMymsg(msg);
				throw new Exception(msg);
			}
			
			fnpClick_OR("EditWOBtn");
			
			

			int taskTypeColIndex = fnpFindColumnIndex("TaskTabTable_HeaderRow", taskTable_TaskTypeColName);
			int taskDescriptionColIndex = fnpFindColumnIndex("TaskTabTable_HeaderRow", IPULSE_CONSTANTS.iAg_taskDesc_SnapshotTab);
			

			int	annualAuditTaskTyperowNo = fnpFindRow("TaskTabTable", IPULSE_CONSTANTS.iAg_taskType_AnnualAudit, taskTypeColIndex);

			String expandingIconForNewMem=".//*[@id='mainForm:tabView:taskDT_data']/tr[1]/td[1]/" +
					"div[contains(@class,'ui-icon-circle-triangle-e')]/..//td[text()='"+iAg_taskType_NEWMEM+"']";
			
/*			
			int nextTaskRow=0;
			int nextTaskActionItemRow=0;
			if (fnpCheckElementDisplayedImmediately_NOR(expandingIconForNewMem)) {
				nextTaskRow=2;
				nextTaskActionItemRow=1;
			} else {
				nextTaskRow=3;
				nextTaskActionItemRow=1;
			}

			

			 String innerTableDataXpathForAI = OR.getProperty("iAgSnapshotTab_InnerTableForActionItemsTableData_part1") + (nextTaskActionItemRow)
					+ OR.getProperty("iAgSnapshotTab_InnerTableForActionItemsTableData_part2");
			 String innerTableHeaderXpathForAI = OR.getProperty("iAgSnapshotTab_InnerTableForActionItemsTableHeader_part1") + (nextTaskActionItemRow)
					+ OR.getProperty("iAgSnapshotTab_InnerTableForActionItemsTableHeader_part2");
			

			 */
			 
			 
			 
			int nextTaskActionItemRow=annualAuditTaskTyperowNo-1;
			
			
			
			
			 String innerTableDataXpathForAI = OR.getProperty("iAgSnapshotTab_InnerTableForActionItemsTableData_part1") + (nextTaskActionItemRow)
						+ OR.getProperty("iAgSnapshotTab_InnerTableForActionItemsTableData_part2");
				 String innerTableHeaderXpathForAI = OR.getProperty("iAgSnapshotTab_InnerTableForActionItemsTableHeader_part1") + (nextTaskActionItemRow)
						+ OR.getProperty("iAgSnapshotTab_InnerTableForActionItemsTableHeader_part2");
				
			 
			
			String xpathForExpandingAnnualAuditTask = ".//*[@id='mainForm:tabView:taskDT_data']/tr[" + annualAuditTaskTyperowNo
					+ "]/td[1]/div[@class='ui-row-toggler ui-icon ui-icon-circle-triangle-e']";
			fnpClick_NOR_withoutWait(xpathForExpandingAnnualAuditTask);
			

			fnpWaitForVisible_NotInOR(innerTableDataXpathForAI);
			
			String aiName = "TechnicalReview";
			String statusToChange = "Completed";
			int aiNameColIndex = fnpFindColumnIndex_NOR(innerTableHeaderXpathForAI, "Action Item Name");
			int aiStatusColIndex = fnpFindColumnIndex_NOR(innerTableHeaderXpathForAI, "Status");
			int actionItemInfoRowNo = fnpFindRow_NOR(innerTableDataXpathForAI,aiName , aiNameColIndex);
			String aiStatus = fnpFetchFromTable_NOR(innerTableDataXpathForAI, actionItemInfoRowNo, aiStatusColIndex);
			fnpMymsg("Current Status of this action item '" + aiName + "' is ---" + aiStatus ) ;
			if (aiStatus.equalsIgnoreCase(statusToChange)) {
				//nothing for now
			}else{
					throw new Exception("Status of AI '"+aiName+"' has not changed to '"+statusToChange+"'.");
			
				}
			
			
			
			
			//fnpProcessiAgAI( innerTableHeaderXpathForAI, innerTableDataXpathForAI, "CertificationDecision","Assigned");
			fnpProcessiAgAI2(IPULSE_CONSTANTS.iAg_taskType_AnnualAudit,IPULSE_CONSTANTS.iAg_task_AnnualAudit_Description,"CertificationDecision","Assigned");
			
			
			
			fnpClickTopHomeMenu();
			
			// -------Certification Requests Assigned to me row -------------------
			TestSuiteBase_HealthScience.fnpCommonAlertGeneratedVerificationHS(ht, "Certification_Requests_Assigned_to_me", "Certification_Requests_Assigned_to_me_AlertTable_header",
					"WO #", "Certification_Requests_Assigned_to_me_AlertTable", "Certification_Requests_Assigned_to_me_Alert_WO_filterTxtBox",
					workOrderNo);
			// ---------------------------------------------
			
			
			int colIndex = fnpFindColumnIndex("Certification_Requests_Assigned_to_me_AlertTable_header", "Task #");
			String s = fnpFetchFromTable("Certification_Requests_Assigned_to_me_AlertTable", 1, colIndex);
			
			driver.findElement(By.linkText(s)).click();
			fnpLoading_wait();
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			

			
			
			fnpWaitForVisible("iAg_ecapSPATaskHeader");
			
			String headerValue = fnpGetText_OR("iAg_ecapSPATaskHeader");
			
			if (!(headerValue.equalsIgnoreCase(iAg_Certification_in_Progress_headerName))) {
			    softAssert = new SoftAssert();
				   //Assertion failing
/*			    
				   softAssert.fail("Certification Decision in progress header name have been changed as actual is--'" +
						   headerValue+"' and expected is --'"+iAg_Certification_in_Progress_headerName+"'.");
				   fnpDesireScreenshot("iAg_Certification_in_Progress_headerName");
				   
				   */
				   msg="Certification Decision in progress header name have been changed as actual is--'" +
						   headerValue+"' and expected is --'"+iAg_Certification_in_Progress_headerName+"'.";
				   fnpMymsg(msg);
				   throw new Exception(msg);
				   
			} else {
				fnpMymsg("Certification Decision in Progress screen is opened successfully.");
			}
			
			
			fnpClick_OR_WithoutWait("iAg_ecapSPA_CurrentAuditsTab"); //PUKA-6072

			String CurrentCertStatus = fnpGetText_OR("iAg_ecapSPATask_CurrentCertStatus");
			String expectedCertStatus="Awaiting Certification";
			if (!(CurrentCertStatus.equalsIgnoreCase(expectedCertStatus))) {
				   throw new Exception("Certification Decision's current cert status is not '"+expectedCertStatus+"' as actual is--'" +
						   CurrentCertStatus+"' and expected is --'"+expectedCertStatus+"'.");
			
			} else {
				fnpMymsg("Certification Decision's current cert status is ---"+expectedCertStatus);
			}
			
			
			
			
			String CurrentAuditStatus = fnpGetText_OR("iAg_ecapSPATask_AuditStatus");
			//String expectedAuditStatus="CREATED";
			String expectedAuditStatus="SUBMITTED";
			if (!(CurrentAuditStatus.equalsIgnoreCase(expectedAuditStatus))) {
				   throw new Exception("Certification Decision's Audit status is not '"+expectedAuditStatus+"' as actual is--'" +
						   CurrentAuditStatus+"' and expected is --'"+expectedAuditStatus+"'.");
			
			} else {
				fnpMymsg("Certification Decision's Audit status is ---"+expectedAuditStatus);
			}
			
			fnpClick_OR_WithoutWait("iAg_ViewProductsBtn"); //PUKA-6072
			//fnpClick_OR_WithoutWait("iAg_ecapSPA_DecisionTab"); //PUKA-6072			
			Thread.sleep(1000);
			
			
			String productName = fnpGetText_OR("iAg_ProductNameAtSPA");
			//String productName = fnpGetText_OR("iAg_ProductNameAtSPA_at_DecisionTab");			
			String expectedProductName=productInFirstRow;
			if (!(productName.equalsIgnoreCase(expectedProductName))) {
				   throw new Exception("Product displayed in Certification Decision Task at ecapSPA is not same as displayed at Product Coverage tab because  actual is--'" +
						   productName+"' and expected is --'"+expectedProductName+"'.");
			
			} else {
				fnpMymsg("Product displayed in Certification Decision Task at ecapSPA is not same as displayed at Product Coverage tab.");
			}
			
			
			
			
			
			fnpGetORObjectX("iAg_ecapSPA_ProductPopUpCloseLink").click();

		//	commenting below line of opening Oasis on the request of Sudhir on 06-09-2018
		//	softAssert=fnpVerifyOasisIsOpeningOrNotFromiAgAndReturnBack("Certification Decision","iAg_ecapSPA_JumpToOasisBtn", softAssert);
			
			
			
			
			fnpClick_InSAM_OR("iAg_ecapSPA_DecisionTaskTab");
			fnpClick_InSAM_OR("iAg_ProductNameAtSPA_at_DecisionTab");
			
			
			
			String productCheckBoxXpath=".//*[@id='decViewProdGrid']/div[2]/table/tbody/tr/td[1]" +
					"/span[text()='"+expectedProductName+"']/../../td/input[contains(@class,'checkbox')]";
			
			
			fnpGetORObjectX___NOR(productCheckBoxXpath).click();
			
			//fnpLoading_wait_In_SAM();
			
			
			Select changeStatusOfSelectedProductDropDown=new Select(fnpGetORObjectX("iAg_ecapSPA_changeStatusOfSelectedProductDropDown"));
			changeStatusOfSelectedProductDropDown.selectByVisibleText("Certified");
			
			fnpClick_InSAM_OR("iAg_ecapSPA_ProductApplyBtn");
			
			
			
			
			Select actionDropDown=new Select(fnpGetORObjectX("iAg_ecapSPA_DecisionActionDropDown_InCertDecisionTask"));
			actionDropDown.selectByVisibleText("Confirm Product Status Changes");
			
			
			fnpGetORObjectX("iAg_ecapSPA_DecisionReason").sendKeys("XXXX");
			
			
			Select auditorScoreDropDown=new Select(fnpGetORObjectX("iAg_ecapSPA_ScoreAuditorDropDown"));
			auditorScoreDropDown.selectByVisibleText("3 - Average");
			
			
			//Clicking this button takes you to the iPulse Alert page
			fnpClick_InSAM_OR("iAg_ecapSPA_confirmBtn");
			Thread.sleep(2000);
			
			/*			
			//if there is any alert for ok then click it otherwise proceed further, not exception throw here
			try{
			driver.switchTo().alert().accept();
			}catch(Throwable t){
				//nothing
			}
			
			if (fnpCheckElementDisplayedImmediately("Ok_button_AlertMessage_AfterClickingConfirmButton")) {
				fnpClick_InSAM_OR("Ok_button_AlertMessage_AfterClickingConfirmButton");
			}
			*/
			
			String alertMessage=fnpGetText_OR("iAg_ecapSPA_alertMessage");
			String expected_alertmsg="Work Order Certification details updated";
			if (alertMessage.contains(expected_alertmsg)) {
				fnpMymsg("Alert message is --"+alertMessage);
			} else {
				fnpMymsg("Alert message is --"+alertMessage);
				msg="Either some error or validation is thrown by application because expected alert message is '"+expected_alertmsg+"' but here it is coming '"+alertMessage+"'.";
				fnpMymsg(msg);
				throw new Exception(msg);
			}
			fnpClick_InSAM_OR("Ok_button_AlertMessage_AfterClickingConfirmButton");
			fnpClick_InSAM_OR("iAg_ecapSPA_BackToWOBtn");
			
/*			
			//ESPA-118 , now it will come to snapshot tab of wo
			if (!(fnpCheckElementDisplayed("ShowAlertsBtn", 20))) {
				fnpClick_OR("AlertTabLink");
			}			
			fnpWaitForVisible("ShowAlertsBtn");
			
			*/
			
			
			
			
			//fnpClick_OR("xpathForAck");
			
			try{
				//Commented for time being on request from Sudhir on 04-09-2020 as currently it is taking to alert page instead of snapshot and it will take time to fix it.
				//ESPA-118 , now it will come to snapshot tab of wo
				fnpWaitForVisible("iAg_SnapshotTabLink");
			}catch(Throwable t){
				String msg="After clicking 'Back To WO' button on 'Certification Decision' task,it is not taking you to snapshot screen, hence failed.";
				
				fnpMymsg(msg );
				throw new Exception(msg);
			}
			
			
			
			
			
			

/*			
			fnpClick_NOR_withoutWait(xpathForExpandingAnnualAuditTask);
			fnpWaitForVisible_NotInOR(innerTableDataXpathForAI);
			fnpProcessiAgAI2(IPULSE_CONSTANTS.iAg_taskType_AnnualAudit,IPULSE_CONSTANTS.iAg_task_AnnualAudit_Description,"CertInterface","In Progress");
			
			*/
			
			
			
			
			
			
		} catch (Throwable t) {

		fnpCommonFinalCatchBlock(t, "  Certification Decision is fail . ", "Certification Decision");

	}
		finally{
			//Collates the assertion results and marks test as pass or fail
			if (softAssert!=null) {
				fail = true;
				isTestPass = false;	
				softAssert.assertAll();
			}
			

		}

}
		
/*	 @Test
	 public void justTesting(){
		 System.out.println("in justTesi");
	 }
		*/
		

	@AfterTest
	public void reportTestResult() throws Throwable {
		if (driver!=null) {
				driver.quit();
		}
		
		fnpReportTestResult(hashXlData, currentSuiteName, currentSuiteXls, currentScriptCode, classNameText, isTestPass);

	}

}