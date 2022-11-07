package nsf.ecap.FPC_Work_Order_suite;

import static nsf.ecap.config.IPULSE_CONSTANTS.SnapShot_taskTable_TaskDescColName;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;

import nsf.ecap.Proposals_suite.TestSuiteBase_Proposal;
import nsf.ecap.Work_Order_suite.NewNew_Draft_InReview_No_Fac;
import nsf.ecap.Work_Order_suite.TestSuiteBase;
import nsf.ecap.config.IPULSE_CONSTANTS;
import nsf.ecap.util.ErrorUtil;
import nsf.ecap.util.TestUtil;

public class BS_01_FPC_Custom_Certified extends TestSuiteBase_FPC_Work_Order_suite {

	String deskAuditTaskTab_expectedBillCode = "";
	double deskAuditTaskTab_expectedQuantity = 0;
	double deskAuditTaskTab_expectedAuditDays = 0;
	public String Testing_TaskNo = "";

	String certAuditTaskTab_expectedBillCode = "";
	double certAuditTaskTab_expectedQuantity = 0;
	double certAuditTaskTab_expectedAuditDays = 0;
	public String FACAUD_TaskNo = "";
	public String CertDecision_TaskNo = "";
	

	String msg;

	@BeforeTest
	@Parameters({ "className" })
	public void checkTestSkip(String className) throws Throwable {
		BS01 = new NewNew_Draft_InReview_No_Fac();
		try {

			if (className.isEmpty()) {
				className = this.getClass().getSimpleName();

			}
			setClassNameText(className);
			currentScriptCode = fnpMappingClassNameWithScenarioCode(classNameText).trim();

			fnpMymsg("             ");
			fnpMymsg("             ");
			fnpMymsg("             ");
			fnpMymsg("             ");
			fnpMymsg("             ");
			fnpMymsg(
					"=========================================================================================================================================");
			fnpMymsg("Checking Runmode of testcase '" + className + "' .");

			if (!TestUtil.isTestCaseRunnable(currentSuiteXls, className)) {
				msg = "Skipping Test Case" + className + " as runmode set to NO";
				fnpMymsg(msg);
				fnpMymsg(
						"=========================================================================================================================================");
				throw new SkipException(msg);
			}

			fnpMymsg("Going to execute the script for  '" + className + "'  as runmode set to Yes");

			fnpLoadHashData(hashXlData, currentSuiteXls, classNameText, 2);

			TestSuiteBase.fail = false;
			TestSuiteBase.isTestPass = true;

		}

		catch (SkipException sk) {
			skip = true;
			String stackTrace = Throwables.getStackTraceAsString(sk);
			String errorMsg = sk.getMessage();
			Exception c = new Exception(stackTrace);
			ErrorUtil.addVerificationFailure(c);
			throw new SkipException(errorMsg + " \n\nSo,Skipping Test Case" + className);
		} catch (Throwable t) {
			fnpCommonFinalCatchBlock(t, "  checkTestSkip method  is fail . ", "checkTestSkipFail");

		}

	}

	@Test(priority = 1)
	public void CreateWOFlow() throws Throwable {

		fnpMymsg(" **************************************************************");

		try {
			
			//fnpDB_UpdateAuditStatusToCancel((String) hashXlData.get("Client"), (String) hashXlData.get("StandardCode"));
			fnpDropWOFPCandDeleteSomeDataFromDB((String) hashXlData.get("Client"));
			
			BS01.CreateWOFlow();

		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "  Create Work Order flow  is fail . ", "CreateWorkOrderFail");

		}

	}

	@Test(priority = 2, dependsOnMethods = { "CreateWOFlow" })
	public void  Info_Tab() throws Throwable {
	  
	  fnpMymsg(" **************************************************************");
	  
		  try { 
			  fnpClick_OR("InfoTab_EditWO");
		  
			  fnpWaitForVisible("InfoTab_WOStatusLabel");
		  
		  
		  if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
			  fnpPFList("TechOpsManagerList", "TechOpsManagerListOptions", (String)			  hashXlData.get("TechOpsManager_Mask")); 
			  fnpPFList("BusUnitManagerList",			  "BusUnitManagerListOptions", (String) hashXlData.get("BusUnitManager_Mask"));
		  }else{ 
			  fnpPFList("TechOpsManagerList", "TechOpsManagerListOptions", (String)			  hashXlData.get("TechOpsManager")); 
			  fnpPFList("BusUnitManagerList",			  "BusUnitManagerListOptions", (String) hashXlData.get("BusUnitManager")); 
		  }
		  
		  fnpClick_OR("InfoTab_TechReviLKPBtn");
		 
		  if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
			  fnpSearchNSelectFirstRadioBtn(1, (String) hashXlData.get("Tech_Reviewer_Code"), 1); 
		  }else{
			  fnpSearchNSelectFirstRadioBtn(2, (String) hashXlData.get("Tech_Reviewer"),1); 
		 } 
		  String techReviewer =  fnpWaitTillTextBoxDontHaveValue("InfoTab_TechReviTxtBox", "value");
		 
		  if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
			  Assert.assertTrue(techReviewer.contains((String)  hashXlData.get("Tech_Reviewer_Code")),  "Tech Reviewer Value is not selected properly from lookup because expected is this '" +(String)  hashXlData.get("Tech_Reviewer_Code")+"' but actual is this '"+techReviewer+ "'."); 
		  }else{ 
			  Assert.assertTrue(techReviewer.contains((String) hashXlData.get("Tech_Reviewer")),		  "Tech Reviewer Value is not selected properly from lookup because expected is this '" +(String)		  hashXlData.get("Tech_Reviewer")+"' but actual is this '"+techReviewer+"'.");
		  } 
		  fnpMymsg(" Tech Reviewer value is properly selected from  lookup");
		  
		  
		  
		  
		  
		  
		  
		  
		  fnpClick_OR("InfoTab_ACLKPBtn");
			 
		  if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
			  fnpSearchNSelectFirstRadioBtn(2, (String) hashXlData.get("AccountManager_Mask"), 1); 
		  }else{
			  fnpSearchNSelectFirstRadioBtn(2, (String) hashXlData.get("AccountManager"),1); 
		 } 
		  String AC =  fnpWaitTillTextBoxDontHaveValue("InfoTab_ACTxtBox", "value");
		 
		  if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
			  Assert.assertTrue(AC.contains((String)  hashXlData.get("AccountManager_Mask")),  "AccountManager Value is not selected properly from lookup because expected is this '" +(String)  hashXlData.get("Tech_Reviewer_Code")+"' but actual is this '"+AC+ "'."); 
		  }else{ 
			  Assert.assertTrue(AC.contains((String) hashXlData.get("AccountManager")),		  "AccountManager Value is not selected properly from lookup because expected is this '" +(String)		  hashXlData.get("Tech_Reviewer")+"' but actual is this '"+AC+"'.");
		  } 
		  fnpMymsg(" AccountManager value is properly selected from  lookup");
		  
		  
		  
		  
		  
		  
		  fnpClick_OR("InfoTab_LiteEvalLKPBtn");  
		  if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
			  fnpSearchNSelectFirstRadioBtn(1, (String)		  hashXlData.get("LitEval_Reviewer_Code"), 1); 
		  }else{
			  fnpSearchNSelectFirstRadioBtn(2, (String) hashXlData.get("LitEval_Reviewer"),		  1); 
		  }
		  String liteEvalReviewer =	  fnpWaitTillTextBoxDontHaveValue("InfoTab_LiteEvalTxtBox", "value");		 
		  if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
			  Assert.assertTrue(liteEvalReviewer.contains((String)		  hashXlData.get("LitEval_Reviewer_Code")),  "Lite Eval Reviewer Value is not selected properly from lookup because expected is this '"		  +(String) hashXlData.get("LitEval_Reviewer_Code")+"' but actual is this '"	  +liteEvalReviewer+"'."); 
		  }else{
			  Assert.assertTrue(liteEvalReviewer.contains((String)		  hashXlData.get("LitEval_Reviewer")),		  "Lite Eval Reviewer Value is not selected properly from lookup because expected is this '"		  +(String)		  hashXlData.get("LitEval_Reviewer")+"' but actual is this '"+liteEvalReviewer+		  "'."); 
		  }
		  fnpMymsg(" Lite Eval  Reviewer value is properly selected from  lookup");
		  
		  
		  
		  
		  fnpClick_OR("InfoTab_CTDeciMakerLKPBtn"); 
		  if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
			  fnpSearchNSelectFirstRadioBtn(1, (String)		  hashXlData.get("QCCertDecision_Maker_Code"), 1); 
		  }else{
			  fnpSearchNSelectFirstRadioBtn(2, (String)		  hashXlData.get("QCCertDecision_Maker"), 1); 
		  } String certDeciMaker =		  fnpWaitTillTextBoxDontHaveValue("InfoTab_CTDeciMakerTxtBox", "value");
		  if  (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
			  Assert.assertTrue(certDeciMaker.contains((String)		  hashXlData.get("QCCertDecision_Maker_Code")),		  "Cert Decision Maker Value is not selected properly from lookup because expected is this '"
			  +(String)		  hashXlData.get("QCCertDecision_Maker_Code")+"' but actual is this '"		  +certDeciMaker+"'."); 		  
		  }else{
			  Assert.assertTrue(certDeciMaker.contains((String)		  hashXlData.get("QCCertDecision_Maker")),		  "Cert Decision Maker Value is not selected properly from lookup because expected is this '"
			  +(String)		  hashXlData.get("QCCertDecision_Maker")+"' but actual is this '"+certDeciMaker		  +"'.");
		  }
		  fnpMymsg(" Cert Decision Maker value is properly selected from  lookup");
		  
		  
		  if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
			  fnpPFList("InfoTab_CertDeciderPFList", "InfoTab_CertDeciderPFListOptions", (String) hashXlData.get("Cert_Decider_Mask"));		  
		  }else{ 
			  fnpPFList("InfoTab_CertDeciderPFList", "InfoTab_CertDeciderPFListOptions", (String) hashXlData.get("Cert_Decider"));
		  }
		  
		  
		  
		  fnsDD_Value_select_by_DDLabelName_and_Filter("Standard Version", (String)  hashXlData.get("Second_Standard_Version")); fnpClick_OR("ProAddDocSaveBtn");
		  
		  fnpCheckError("while Inserting Value For TR,LER,PER , QC and Cert decider and then added Standard Version."  );
		  
		  
		 } catch (Throwable t) {
	  
			 fnpCommonFinalCatchBlock(t, "  Info Tab flow  is fail . ", "InfoTab_Fail");
	  
		 }
	  
	  }

	
	
	@Test(priority = 3, dependsOnMethods = { "Info_Tab" })
	public void AddingData__Facility_Tab() throws Throwable {

		fnpMymsg(" **************************************************************");

		try {
			BS01.AddingData__Facility_Tab();

		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "  FacilityTab  is fail . ", "FacilityTab_Fail");

		}

	}

	@Test(priority = 4, dependsOnMethods = { "AddingData__Facility_Tab" })
	public void AddingDataTask_Tab() throws Throwable {

		fnpMymsg(" **************************************************************");

		try {
			BS01.AddingData__Tasks_Tab();

		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "  Task_Tab  is fail . ", "TaskTab_Fail");

		}

	}

	@Test(priority = 5, dependsOnMethods = { "AddingDataTask_Tab" })
	public void DraftToInReviewCode_Info_Tab() throws Throwable {

		fnpMymsg(" **************************************************************");

		try {
			fnpWaitTillClickable("InfoTab_EditWO");
			fnpGetORObjectX("InfoTab_EditWO").click();
			fnpLoading_wait();

			fnpWaitForVisible("InfoTab_WOStatusPFList");

			String status = "InReview";

			fnpPFList("InfoTab_WOStatusPFList", "InfoTab_WOStatusPFListOptions", status);
			fnpClick_OR("ProAddDocSaveBtn");
			fnpCheckErrMsg("Error is thrown by application while changing status from DRAFT to InReview in Info tab");

			fnpWaitForVisibleHavingMultipleElements("TopMessage3");
			String SuccessfulMsg = fnpGetText_OR("TopMessage3");
			fnpMymsg("Top Message after changing status from DRAFT to InReview in Info tab ----" + SuccessfulMsg);

			Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"),
					"Top message does not contain 'success' word, so might be changing status from DRAFT to InReview in Info tab is NOT successful.");

			fnpWaitTillClickable("NewlyCrWOTopBannerInfo");
			fnpGetORObjectX("NewlyCrWOTopBannerInfo").click();
			fnpWaitForVisible("TopBannerWOStatus");
			String changedWOStatus = fnpGetORObjectX("TopBannerWOStatus").getText();
			Assert.assertEquals(changedWOStatus, "INREVIEW", " WO status is not changed from 'DRAFT' to 'INREVIEW'.");
			fnpMymsg("Now  WO status has been changed from 'DRAFT' to 'INREVIEW' status.");

		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "  Task_Tab  is fail . ", "TaskTab_Fail");

		}

	}

	@Test(priority = 6, dependsOnMethods = { "DraftToInReviewCode_Info_Tab" })
	public void Snapshots_Tab() throws Throwable {

		fnpMymsg(" **************************************************************");

		try {
			fnpClick_OR("SnapshotTabLink");
			fnpLoading_wait();
			Thread.sleep(1000);
			fnpLoading_wait();
			fnpWaitForVisible("ActionItemTable_EditWO");

			String FirstValueInTable = fnpFetchFromTable("ActionItemTable_EditWO", 1, 1);

			String AI_Link_Xpath = "//a[text()='" + FirstValueInTable + "']";
			fnpGetORObjectX___NOR(AI_Link_Xpath).click();
			fnpCheckError(" while verifying Action Tab");
			fnpLoading_wait();

			String statusToChange = "Completed";
			fnpWaitForVisible("ActionItemTab_ChangeStatusPFList");

			fnpPFList("ActionItemTab_ChangeStatusPFList", "ActionItemTab_ChangeStatusPFListOptions", statusToChange);
			fnpClick_OR("SaveNCloseButton");

			fnpCheckError("while Going to " + statusToChange + "  the pending Action item ---" + FirstValueInTable);

			fnpWaitForVisibleHavingMultipleElements("TopMessage3");
			String SuccessfulMsg = fnpGetText_OR("TopMessage3");
			fnpMymsg("Top Message after " + statusToChange + "  the pending Action item '" + FirstValueInTable
					+ "' ----" + SuccessfulMsg);

			Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"),
					"Top message does not contain 'success' word, so might be action of " + statusToChange
							+ "  the pending Action item '" + FirstValueInTable + "'  is NOT successful.");
			
			
			Verify_AmountUnder_FinancialTabInfoSection(IPULSE_CONSTANTS.SnapShot_Client_App_Review_Task, true);
			
		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "  Snapshots_Tab  is fail . ", "Snapshots_Tab_Fail");

		}

	}


	@Test(priority = 7, dependsOnMethods = { "Snapshots_Tab" })	  
	  public void TestingTask_EPSFDetails() throws Throwable {
		
		  try { 
			  fnpClick_OR("SnapshotTabLink");
				fnpLoading_wait();
				Thread.sleep(1000);
				fnpLoading_wait();
			  fnpWaitForVisible("Snapshot_TasksSummaryTable");
			  
			  Testing_TaskNo =	  fnsFindNClickOn_TaskOrAIlink_FromTable("Snapshot_TasksSummaryTable",	  IPULSE_CONSTANTS.SnapShot_taskDesc_Testing_Task, 1);
			  
			  
			  fnpPFListByLiNo("FacilityLocationList", "FacilityLocationOptions", 1);
			  
			  fnpClick_OR("CreateNew_btn"); fnpLoading_wait(); fnpCheckError("");
			  fnpLoading_wait();
			  
			  fnpPFList("EPSF_CollectionTypeList", "EPSF_CollectionTypeListOptions",	  (String) hashXlData.get("CollectionType")); 
			  fnpLoading_wait();
			  
			  fnpPFList("EPSF_TestCategoryList", "EPSF_TestCategoryListOptions", (String)	  hashXlData.get("TestCategory"));
			  fnpLoading_wait();
			  
			  fnpPFList("EPSF_StandardList", "EPSF_StandardListOptions", (String)	  hashXlData.get("StandardList")); 
			  fnpLoading_wait();
			  fnpPFList("EPSF_TestLocationList", "EPSF_TestLocationListOptions", (String)  hashXlData.get("TestLocation")); 
			  fnpPFList("EPSF_ShipToLocationList",	  "EPSF_ShipToLocationListOptions", (String) hashXlData.get("ShipToLocation"));
			  
			  fnpClick_OR("EPSF_CreateNextBtn");
			  
			  
			  
			  fnpType("OR", "EPSF_TestDescriptionTxtBox", "Automation Test Description");
			  fnpType("OR", "EPSF_TradeDesignation_ProductIDTxtBox",	  "Automation Designation"); 
			  fnpType("OR",	  "EPSF_PhysicalDescriptionofSampleTxtBox", "Automation Phy Desc");
			  
			  
			  fnpClick_OR("EPSF_CreateNextBtn");
			  fnpLoading_wait(); fnpWaitForVisible("EPSF_INFO_highlightedTab");
			  fnpLoading_wait(); fnpClick_OR("EPSF_CreateNextBtn");
			  fnpLoading_wait();
			  
			  fnpClick_OR("CreateEPSFFirstBtn");
			  
			  
			  fnpClick_OR("EPSF_SaveBtn"); fnpLoading_wait(); fnpCheckError("");
			  fnpClick_OR("WOLinkATEPSF"); fnpLoading_wait(); fnpCheckError("");
			  
			  fnpVerifyTask_ColValue_at_SnapshotTab_ContainsMatch(IPULSE_CONSTANTS.taskTable_EPSFColName, "Task", Testing_TaskNo, "DRAFT");
			  
			  
			  
			  
			  fnpWaitTillClickable("InfoTab_EditWO");
			  fnpGetORObjectX("InfoTab_EditWO").click(); 
			  fnpLoading_wait();
			  
			  fnpWaitForVisible("InfoTab_WOStatusPFList");
			  
			  String status = "INPROCESS";
			  
			  fnpPFList("InfoTab_WOStatusPFList", "InfoTab_WOStatusPFListOptions", status);
			  fnpClick_OR("ProAddDocSaveBtn");
			  fnpCheckErrMsg("Error is thrown by application while changing status from DRAFT to InReview in Info tab" );
			  
			  fnpWaitForVisibleHavingMultipleElements("TopMessage3"); 
			  String SuccessfulMsg = fnpGetText_OR("TopMessage3");
			  fnpMymsg("Top Message after changing status from 'INREVIEW' to  INPROCESS in Info tab ----" + SuccessfulMsg);
			  
			  Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"),
			  "Top message does not contain 'success' word, so might be changing status from 'INREVIEW' to  INPROCESS in Info tab is NOT successful." );
			  
			  fnpWaitTillClickable("NewlyCrWOTopBannerInfo");
			  fnpGetORObjectX("NewlyCrWOTopBannerInfo").click();
			  fnpWaitForVisible("TopBannerWOStatus");
			  String changedWOStatus =	  fnpGetORObjectX("TopBannerWOStatus").getText();
			  Assert.assertTrue(changedWOStatus.equalsIgnoreCase("INPROCESS"),  " WO status is not changed from INREVIEW to INPROCESS."); 
			  fnpMymsg("Now "	  +"  WO  status has been changed from 'INREVIEW' to  INPROCESS.");
			  
			  
			  
			  fnpClick_OR("WO_CorrespondenceTabLink"); fnpLoading_wait();
			  fnpWaitForVisible("Prop_Corresondence_table");
			  
			  String NoRecordFound_Text =
			  fnpFetchFromTable_NOR_TraversingFromVariousNodes(OR.getProperty( "Prop_Corresondence_table"), 1, 1);
			  Assert.assertTrue(NoRecordFound_Text.toLowerCase().  contains("no records found"), "Record is coming under Correspondence Tab which is not expected.");
		  
		  
		  } catch (Throwable t) { 
			  fnpMymsg(" **************************************************************");
			  fnpCommonFinalCatchBlock(t,	  "  Financial_Tab_And_InReviewToInProcess flow  is fail . ",	  "TestingTask_EPSFDetails");
		  
		  }
	  
	  }  
	  
	 
	@Test(priority = 8 , dependsOnMethods = { "TestingTask_EPSFDetails" } )
	public void FACAUD_Task_CompleteAudit() throws Throwable {
		try {			
			  fnpCommonClickTaskTab();
			  
			  if(fnpCheckElementDisplayedImmediately("ApplyTaskTemplateBtn")) {
				  throw new  Exception("FAILED == 'ApplyTaskTemplate' button is coming which is not expected."  );
			  }
			  
			  
			  fnpClick_OR("SnapshotTabLink");
				fnpLoading_wait();
				Thread.sleep(1000);
				fnpLoading_wait();
			  
			  fnpWaitForVisible("Snapshot_TasksSummaryTable");
			  
			  fnpVerifyTask_ColValue_at_SnapshotTab_ContainsMatch(IPULSE_CONSTANTS. taskTable_EPSFColName, "Task", Testing_TaskNo, "REQUESTED");
			  
			 
			
			  FACAUD_TaskNo =	  fnsFindNClickOn_TaskOrAIlink_FromTable("Snapshot_TasksSummaryTable", IPULSE_CONSTANTS.SnapShot_taskDesc_FACAUD_Task, 1);			  
			  fnpCheckError(" while  Facility_audit_Task");
			  
			  
			  fnpWaitForVisible("FacilityAudit_AuditInfoTable_header");
			  String AuditNoColName = "Audit No."; 
			  int AuditNoColIndex = fnpFindColumnIndex("FacilityAudit_AuditInfoTable_header", AuditNoColName);
			  String AuditNo = fnpFetchFromTable("FacilityAudit_AuditInfoTable", 1, AuditNoColIndex);
			  
			  String visitNoColName = "Visit #"; 
			  int visitNoColIndex =  fnpFindColumnIndex("FacilityAudit_AuditInfoTable_header", visitNoColName);
			  String visitNo = fnpFetchFromTable("FacilityAudit_AuditInfoTable", 1,  visitNoColIndex);
			  
			  String visitNo_linkXpath = "//a[text()='" + visitNo + "']";
			  fnsWait_Click_on_First_Visible_Element_if_More_than_One_Coming("visitNo Link", visitNo_linkXpath);
			  fnpClick_OR("EditBtnOnViewVisitPage");
			  
			  fnpSelectingAuditorThroughSAM("1", "1");
			 


			fnpOasisPartInISO("cert", visitNo);
			
			
			fnpLaunchBrowserAndLogin();

			fnpSearchWorkOrderOnly(workOrderNo);

			if (fnpCheckElementDisplayedImmediately("EditWOBtn")) {
				fnpClick_OR("EditWOBtn");
				fnpMymsg("@  ---Here EditWOBtn is visible , so going to click it");

			} else {
				fnpMymsg("@  ---Here EditWOBtn is NOT visible , so ignore  it");
			}

			fnsFindNClickOn_TaskOrAIlink_FromTable("Snapshot_TasksSummaryTable", IPULSE_CONSTANTS.SnapShot_taskDesc_FACAUD_Task, 1);

			fnpWaitForVisible("FacilityAudit_AuditInfoTable");
			String StatusColName = "Status";
			int StatusColIndex = fnpFindColumnIndex("FacilityAudit_AuditInfoTable_header", StatusColName);
			String AuditInfoStatus = fnpFetchFromTable("FacilityAudit_AuditInfoTable", 1, StatusColIndex);
			
			  Assert.assertTrue(AuditInfoStatus.equalsIgnoreCase("SUBMITTED"),  "Status in Audit Inof table should have 'SUBMITTED' status but now it is --"  + AuditInfoStatus); 
			  fnpMymsg("After refreshing the page , Status in Audit Inof table become 'SUBMITTED' status"  );
			 

			int waitCount = 0;
			while (true) {
				waitCount++;
				
				AuditInfoStatus = fnpFetchFromTable("FacilityAudit_AuditInfoTable", 1, StatusColIndex);

				if (AuditInfoStatus.equalsIgnoreCase("SUBMITTED")) {
					fnpMymsg("After refreshing the page , Status in Audit Inof table become 'SUBMITTED' status ");
					break;
				} else {
					Thread.sleep(1000 * 20 * 1);
					driver.navigate().refresh();
					Thread.sleep(8000);

					fnpMymsg("We are in  waiting loop for Status in Audit Inof table to become 'SUBMITTED' status ----"
							+ (waitCount * 0.5) + "  minutes. ");
				}

				if (waitCount > 60) {

					fnpMymsg(
							"Script waited for approx. 30 min to become 'SUBMITTED' status in Audit Info table but it is still showing '"
									+ AuditInfoStatus + "' .");
					throw new Exception(
							"Script waited for approx. 30 min to become 'SUBMITTED' status in Audit Info table but it is  still showing '"
									+ AuditInfoStatus + "' .");

				}

			}

			fnpMymsg("Now going to check FACUD task status at detail page under Info tab ");
			
			
			
			fnpRunJob("CreateTechReviewAiQJob");
			
			String TechnicalReview_Link_Xpath = "(//td[text()='"+IPULSE_CONSTANTS.WOISOTaskTab_TechnicalReviewAIName+"']/preceding::a[1])[1]";
			fnpWaitForVisible_NotInOR(TechnicalReview_Link_Xpath); 
			fnsClick_NOR(TechnicalReview_Link_Xpath);
			fnpLoading_wait();
			fnpLoading_wait();
			
			String reassignee = "";
			if (  (loginAsFullName==null) ||loginAsFullName.equalsIgnoreCase("") ) {
				if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
					reassignee=(String) hashXlData.get("Auditor_Code").trim();
				}else{
					reassignee=(String) hashXlData.get("Auditor").trim();
				}				
			} else {
				if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
					reassignee=loginUser_code;
				}else{
					reassignee=loginAsFullName;
				}

				
			}
			
			fnpClick_OR("TechReviewAI_ReassignToLookup");
			
			fnpMymsg("Just after  click ReassignAILkpBtn");
			fnpMymsg("Just before going to insert value of Account Manger");
			if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
				fnpSearchNSelectFirstRadioBtn(1, reassignee, 1);
			}else{
				fnpSearchNSelectFirstRadioBtn(2, reassignee, 1);
			}
			
			fnpLoading_wait();
			fnpClick_OR("SaveButton__OnConsolidatedScreen_TechnicalReviewAI");
			fnpLoading_wait();
			
			
			
			fnpClick_OR("StartReview_ViewActionItemPage");
		
			fnpLoading_wait();
			fnpClick_OR("ReviewCheckList_ViewActionItemPage");
			fnpLoading_wait();
			Thread.sleep(1000);
			
			
			TestSuiteBase_Proposal.fnpCommonProcessQuestionnairesSet_ISR( );
			
			driver.navigate().refresh();
			Thread.sleep(3000);
			fnpLoading_wait();
			
			
			fnpClick_OR("TechReviewAI_CorrectiveActionTabLink");
			fnpLoading_wait();
			
			fnpClick_OR("TechReviewAI_CorrectiveActionTab_CreateCorrectiveActionBttn");
			fnpLoading_wait();
			
			fnpWaitForVisible("TechReviewAI_CorrectiveActionTab_CreateCorrectiveActionPopup_Title");
			
			
			
			fnpPFList("TechReviewAI_CorrectiveActionTab_CarTypeList", "TechReviewAI_CorrectiveActionTab_CarTypeOption", (String) hashXlData.get("CreateCorrectiveAction_CarType"));
			fnpLoading_wait();
			Thread.sleep(1000);
			fnpPFList("TechReviewAI_CorrectiveActionTab_CarCategoryList", "TechReviewAI_CorrectiveActionTab_CarCategoryOption", (String) hashXlData.get("CreateCorrectiveAction_CarCategory"));
			fnpLoading_wait();
			Thread.sleep(1000);
			fnpPFList("TechReviewAI_CorrectiveActionTab_carStdList", "TechReviewAI_CorrectiveActionTab_carStdOption", (String) hashXlData.get("CreateCorrectiveAction_Standard"));
			fnpType("OR", "TechReviewAI_CorrectiveActionTab_CreateCorrectiveActionPopup_TextArea", "Automation CAR");
			fnpClick_OR("TechReviewAI_CorrectiveActionTab_CreateCorrectiveActionPopup_SaveNCloseBttn");
			fnpLoading_wait();
			fnpLoading_wait();
			fnpCheckError("   while Saving Create CAR data under AI  ");
			
			String CAR_DownArrowIcon_Xpath = "//td[text()='"+FACAUD_TaskNo+"-1']/preceding-sibling::td[1]/div[contains(@class, 'ui-icon')]";
			fnpClick_NOR(CAR_DownArrowIcon_Xpath);
			fnpLoading_wait();
			fnpLoading_wait();
			
			fnpClick_OR("TechReviewAI_ReviewSpecificInformationTabLink");
			fnpLoading_wait();
			fnpLoading_wait();
			
			
			fnpLoading_wait();
			fnpClick_OR("ApproveButton_ViewActionItemPage");   		
			fnpLoading_wait();
			
			
//			fnpLoading_wait();
//			fnpPFList("ISR_AI_LeadAuditorRatingList", "ISR_AI_LeadAuditorRatingListOptions", (String) hashXlData.get("Lead_Auditor_Rating").trim());
//			fnpLoading_wait();
//			
//			
//			fnpPFList("ISR_AI_ReviewerRecommendationList", "ISR_AI_ReviewerRecommendationListOptions", (String) hashXlData.get("Reviewer_Recommendation").trim());
			
			
			fnpClick_OR("SaveButton_ApproveAuditPopup");   
			fnpLoading_wait();
			fnpLoading_wait();
			fnpCheckError("   while Saving Approve Checklist button data  ");
			
			
			fnpClick_OR("WO_BackBtn");   
			fnpLoading_wait();
			fnpLoading_wait();
			
			String FACAUDTaskLink_Xpath = "//a[text()='"+FACAUD_TaskNo+"']";
			fnsWait_Click_on_First_Visible_Element_if_More_than_One_Coming("FACAUD Task Link", FACAUDTaskLink_Xpath);
			fnpLoading_wait();
			fnpLoading_wait();
			
			
			String AuditStatusColName = "Status"; 
			int AuditStatusColIndex = fnpFindColumnIndex("FacilityAudit_AuditInfoTable_header", AuditStatusColName);
			String AuditStatus = fnpFetchFromTable("FacilityAudit_AuditInfoTable", 1, AuditStatusColIndex);
			
			Assert.assertTrue(AuditStatus.equalsIgnoreCase("REVIEWED"), "Status in Audit Inof table should have 'REVIEWED' status but now it is --" + AuditInfoStatus); 
			fnpMymsg("After refreshing the page , Status in Audit Inof table become 'REVIEWED' status");
			
			
			
			
			
			fnpRunOasisJob("Oasis_RunAU_btn");
			
			
			
			
			fnpLaunchBrowserAndLogin();
			fnpSearchWorkOrderOnly(workOrderNo);
			
			fnsWait_Click_on_First_Visible_Element_if_More_than_One_Coming("FACAUD Task Link", FACAUDTaskLink_Xpath);
			fnpLoading_wait();
			fnpLoading_wait();
			
			
			AuditStatusColName = "Status"; 
			AuditStatusColIndex = fnpFindColumnIndex("FacilityAudit_AuditInfoTable_header", AuditStatusColName);
			AuditStatus = fnpFetchFromTable("FacilityAudit_AuditInfoTable", 1, AuditStatusColIndex);
			
			Assert.assertTrue(AuditStatus.equalsIgnoreCase("Completed"), "Status in Audit Inof table should have 'Completed' status but now it is --" + AuditInfoStatus); 
			fnpMymsg("After refreshing the page , Status in Audit Inof table become 'Completed' status");
			
			fnpRunJob("MarkAuditTaskCompletedQJobScheduler");
			
			//Verify_AmountUnder_FinancialTabInfoSection(IPULSE_CONSTANTS.SnapShot_taskDesc_FACAUD_Task, false);
			Verify_AmountUnder_FinancialTabInfoSection("Initial Facility Audit", false);
			
			
		} catch (Throwable t) {
			 isTestPass = false;
			fnpMymsg(" **************************************************************");
			fnpCommonFinalCatchBlock(t, "  FACAUD_Task_CompleteAudit flow  is fail . ",	"FACAUD_Task_CompleteAudit");
		}
	}	
	
		@Test(priority = 9 , dependsOnMethods = { "FACAUD_Task_CompleteAudit" } )
		public void Testing_Task_CompleteEPSF() throws Throwable {
			try {	
		
		
			fnpClick_OR("SnapshotTabLink");
			fnpLoading_wait();
			Thread.sleep(1000);
			fnpLoading_wait();
			fnpWaitForVisible("Snapshot_TasksSummaryTable");

			int taskDescColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", SnapShot_taskTable_TaskDescColName);
			int FacilityAuditTaskRowNo = fnpFindRow_Contains("Snapshot_TasksSummaryTable", IPULSE_CONSTANTS.SnapShot_taskDesc_FACAUD_Task, taskDescColIndex);

			int taskStatusIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", IPULSE_CONSTANTS.SnapShot_taskTable_StatusColName);
			String FacilityAuditTaskStatus = fnpFetchFromTable("Snapshot_TasksSummaryTable", FacilityAuditTaskRowNo, taskStatusIndex);
			
			Assert.assertTrue(FacilityAuditTaskStatus.equalsIgnoreCase("COMPLETED"), "Status in Snapshot task Table should have 'COMPLETED' status but now it is --" + FacilityAuditTaskStatus); 
			fnpMymsg("After refreshing the page , Status in Snapshot task Table become 'COMPLETED' status");
			
			
			fnpClick_OR("REQUESTED_EPSFLink");   
			fnpLoading_wait();
			fnpLoading_wait();
			
			fnpClick_OR("EPSFEditBtn");   
			fnpLoading_wait();
			fnpLoading_wait();
			
			fnpPFList("EPSFStatusPFList", "EPSFStatusPFListOptions", "Received");
			
			fnpClick_OR("EPSF_SaveBtn");   
			fnpLoading_wait();
			fnpCheckError("EPSF Save Btn");
			
			
			String Testing_TaskLink_Xpath = "//a[text()='"+Testing_TaskNo+"']";
			fnsWait_Click_on_First_Visible_Element_if_More_than_One_Coming("Testing Task Link", Testing_TaskLink_Xpath);
			fnpLoading_wait();
			fnpLoading_wait();
			
			int EpsfStatusColIndex = fnpFindColumnIndex_NOR_TraversingFromVariousNodes(OR.getProperty("TestingTask_TestingDetailsTable_header"), "EPSF#");
			String EpsfStatus_Xpath = "//div[contains(text(), 'Testing Details')]/following::tbody[1]/tr[1]/td["+EpsfStatusColIndex+"]";
			String EpsfStatus = fnpGetText_NOR(EpsfStatus_Xpath);
			
			Assert.assertTrue(EpsfStatus.contains("RECEIVED"), "Status in Audit Inof table should have 'RECEIVED' status but now it is --" + EpsfStatus); 
			fnpMymsg("After refreshing the page , Status in Audit Inof table become 'RECEIVED' status");
			
			fnpClick_OR("TestingTask_CreateTestReportReviewAIBttn");   
			fnpLoading_wait();
			fnpCheckError("TestingTask_CreateTestReportReviewAIBttn");
			
			fnc_Type_IntoEmailTemplateBody();
			
			fnpClick_OR("CreateActionItem_SaveBtn");   
			fnpLoading_wait();
			fnpCheckError("CreateActionItem Save Btn");
			
			
			String TestReportReview_AI = fnpFetchFromTable("LiteEval_ActionItemsTab_ActionItemTable", 1, 1);
			String TestReportReview_AILink_Xpath = "//a[text()='"+TestReportReview_AI+"']";
			fnsWait_Click_on_First_Visible_Element_if_More_than_One_Coming("TestReportReview AI Link", TestReportReview_AILink_Xpath);
			fnpLoading_wait();
			fnpLoading_wait();
			
			
			fnpPFList("TestReportReviewAI_TestReportResult", "TestReportReviewAI_TestReportResultOptions", "Pass");
			
			
			fnpClick_OR("TestReportReviewAI_TestReportReceivedDateCalBttn");   
			fnpLoading_wait();
			Date todayDate=new Date();
			SimpleDateFormat sdfmt1 = new SimpleDateFormat("dd-MMM-yyyy");
			String todaydateInStringformat = sdfmt1.format(todayDate);
			fnpCalendarDatePicker_BySelectValues_From_DropDown2(todaydateInStringformat, OR.getProperty("calendarDatePickerSelectYear_xpath"), OR.getProperty("calendarDatePickerSelectMonth_xpath"));
			fnpLoading_wait();
			
			fnsUploadFile("TestReportReviewAI_AddDocumentBttn", "Add Document");
			fnpLoading_wait();
			fnpLoading_wait();
			
			fnpPFList("TestReportReviewAI_AddDocument_Type", "TestReportReviewAI_AddDocument_TypeOptions", "Test Report - Final");
			
			fnpClick_OR("TestReportReviewAI_SaveBttn");   
			fnpLoading_wait();
			fnpLoading_wait();
			
			fnpPFList("ActionItemTab_ChangeStatusPFList", "ActionItemTab_ChangeStatusPFListOptions", "Completed");
			
			fnpClick_OR("TestReportReviewAI_SaveNcloseBttn");   
			fnpLoading_wait();
			fnpLoading_wait();
			
			
			fnpWaitForVisible("LiteEval_ActionItemsTab_ActionItemTable");
			int AItemTable_StatusColIndex = fnpFindColumnIndex("LiteEval_ActionItemsTab_ActionItemTable_header", "Status");

			String ActionItemTable_status = fnpFetchFromTable("LiteEval_ActionItemsTab_ActionItemTable", 1, AItemTable_StatusColIndex);

			Assert.assertTrue(ActionItemTable_status.equalsIgnoreCase("completed"),
					" Action item for TestReportReview should also get changed from SUBMITTED to COMPLETED but it is coming  '" + ActionItemTable_status
							+ "' ");
			fnpMymsg(" Action item for TestReportReview task has become  to 'COMPLETED'  successfully.");

			fnpCheckError(" while Going to Complete the TestReportReview Task");
		
			
			fnpClick_OR("RECEIVED_EPSFLink");   
			fnpLoading_wait();
			fnpLoading_wait();
			
			fnpClick_OR("EPSFEditBtn");   
			fnpLoading_wait();
			fnpLoading_wait();
			
			fnpPFList("EPSFStatusPFList", "EPSFStatusPFListOptions", "Complete");
			
			fnpClick_OR("EPSF_SaveBtn");   
			fnpLoading_wait();
			fnpCheckError("EPSF Save Btn");
			
			
			fnsWait_Click_on_First_Visible_Element_if_More_than_One_Coming("Testing Task Link", Testing_TaskLink_Xpath);
			fnpLoading_wait();
			fnpLoading_wait();
			
			EpsfStatusColIndex = fnpFindColumnIndex_NOR_TraversingFromVariousNodes(OR.getProperty("TestingTask_TestingDetailsTable_header"), "EPSF#");
			EpsfStatus_Xpath = "//div[contains(text(), 'Testing Details')]/following::tbody[1]/tr[1]/td["+EpsfStatusColIndex+"]";
			EpsfStatus = fnpGetText_NOR(EpsfStatus_Xpath);
			
			Assert.assertTrue(EpsfStatus.toLowerCase().trim().contains("complete"), "Status in Audit Inof table should have 'Complete' status but now it is --" + EpsfStatus); 
			fnpMymsg("After refreshing the page , Status in Audit Inof table become 'Complete' status");
			
			
					
			taskStatusIndex = fnpFindColumnIndex("LiteEval_TaskSummaryTable_header", IPULSE_CONSTANTS.SnapShot_taskTable_ResultColName);
			String TestingStatus = fnpFetchFromTable("LiteEval_TaskSummaryTable", 1, taskStatusIndex);
			
			Assert.assertTrue(TestingStatus.toUpperCase().trim().contains("COMPLETE"), "Status in task Table should have 'COMPLETE' status but now it is --" + taskStatusIndex); 
			fnpMymsg("After refreshing the page , Status in task Table become 'COMPLETE' status");
			
			fnpRunJob("SetTasksReadyQJobScheduler");
			
//			Verify_AmountUnder_FinancialTabInfoSection(IPULSE_CONSTANTS.SnapShot_taskDesc_Testing_Task, false);
			Verify_AmountUnder_FinancialTabInfoSection("Testing", false);
			
			
	} catch (Throwable t) {
		 isTestPass = false;
		fnpMymsg(" **************************************************************");
		fnpCommonFinalCatchBlock(t, "  Testing_Task_CompleteEPSF flow  is fail . ",	"Testing_Task_CompleteEPSF");
	}
}		
			
			
	@Test(priority = 10 , dependsOnMethods = { "Testing_Task_CompleteEPSF" } )
	public void CertDec_Task() throws Throwable {
		try {			
			fnpClick_OR("SnapshotTabLink");
			fnpLoading_wait();
			Thread.sleep(1000);
			fnpLoading_wait();
			fnpWaitForVisible("Snapshot_TasksSummaryTable");

			
			
			int taskDescColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", SnapShot_taskTable_TaskDescColName);
			int CertTaskRowNo = fnpFindRow_Contains("Snapshot_TasksSummaryTable", IPULSE_CONSTANTS.SnapShot_taskDesc_CertDeci, taskDescColIndex);

			int taskStatusIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", IPULSE_CONSTANTS.SnapShot_taskTable_StatusColName);
			String CertTaskStatus = fnpFetchFromTable("Snapshot_TasksSummaryTable", CertTaskRowNo, taskStatusIndex);
			
			Assert.assertTrue(CertTaskStatus.toUpperCase().trim().contains("CREATED"), "Status in Snapshot task Table should have 'CREATED' status but now it is --" + CertTaskStatus); 
			fnpMymsg("After refreshing the page , Status in Snapshot task Table become 'CREATED' status");
			
			
			
			String FACAUDTaskLink_Xpath = "//a[text()='"+FACAUD_TaskNo+"']";
			fnsWait_Click_on_First_Visible_Element_if_More_than_One_Coming("FACAUD Task Link", FACAUDTaskLink_Xpath);
			fnpLoading_wait();
			fnpLoading_wait();
			
			
			fnpClick_OR("FacAuditAI_CorrectiveActionTabLink");
			fnpLoading_wait();
			fnpLoading_wait();
			
			
			String CAR_DownArrowIcon_Xpath = "(//div[contains(@class, 'ui-icon-circle-triangle-e')])[2]";
			fnpClick_NOR(CAR_DownArrowIcon_Xpath);
			fnpLoading_wait();
			fnpLoading_wait();
			
			fnpPFList("FacAuditAI_CorrectiveActionTab_Type", "FacAuditAI_CorrectiveActionTab_TypeOptions", "Approved");
			
			fnpClick_OR("FacAuditAI_CorrectiveActionTab_UpdateSingleCarBttn");
			fnpLoading_wait();
			fnpLoading_wait();
			fnpCheckError(" Update Car");
			
			
			fnpRunJob("SetTasksReadyQJobScheduler");
			
			
			
			taskDescColIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", SnapShot_taskTable_TaskDescColName);
			CertTaskRowNo = fnpFindRow_Contains("Snapshot_TasksSummaryTable", IPULSE_CONSTANTS.SnapShot_taskDesc_CertDeci, taskDescColIndex);

			taskStatusIndex = fnpFindColumnIndex("Snapshot_TasksSummary_HeaderRow", IPULSE_CONSTANTS.SnapShot_taskTable_StatusColName);
			CertTaskStatus = fnpFetchFromTable("Snapshot_TasksSummaryTable", CertTaskRowNo, taskStatusIndex);
			
			Assert.assertTrue(CertTaskStatus.toUpperCase().trim().contains("READY"), "Status in Snapshot task Table should have 'READY' status but now it is --" + CertTaskStatus); 
			fnpMymsg("After refreshing the page , Status in Snapshot task Table become 'READY' status");
			
			
			
			CertDecision_TaskNo = fnsFindNClickOn_TaskOrAIlink_FromTable("Snapshot_TasksSummaryTable",	IPULSE_CONSTANTS.SnapShot_taskDesc_CertDeci, 1);
			fnpCheckError(" while  CertDecision_Task");
			
			fnpClick_OR("EditWOBtnTaskInside");
			fnpLoading_wait();
			
			String CertDecUpdate_AI = fnpFetchFromTable("LiteEval_ActionItemsTab_ActionItemTable", 1, 1);
			String CertDecUpdate_AILink_Xpath = "//a[text()='"+CertDecUpdate_AI+"']";
			fnsWait_Click_on_First_Visible_Element_if_More_than_One_Coming("CertDecUpdate AI Link", CertDecUpdate_AILink_Xpath);
			fnpLoading_wait();
			fnpLoading_wait();
			
			fnpPFList("ActionItemTab_ChangeStatusPFList", "ActionItemTab_ChangeStatusPFListOptions", "Completed");
			
			fnpClick_OR("SaveNCloseButton");
			fnpLoading_wait();
			fnpLoading_wait();
			
			//edit button is missing
			
			fnpWaitForVisible("TaskTab_CertDeci_AssignTaskIcon");
			fnpCheckError("");
			fnpWaitTillVisiblityOfElementNClickable_OR("TaskTab_CertDeci_AssignTaskIcon");
			fnpGetORObjectX("TaskTab_CertDeci_AssignTaskIcon").click();

			fnpClickInDialog_OR("TaskTab_ScopeValidation_AssignTask_SaveBtn");

			fnpCheckErrMsg("Error is thrown by application in Certification_Decision_Task  method while Assigning the task");

			fnpWaitForVisible("TaskTab_CertDeci_PerformTaskIcon");
			fnpWaitTillVisiblityOfElementNClickable_OR("TaskTab_CertDeci_PerformTaskIcon");
			fnpGetORObjectX("TaskTab_CertDeci_PerformTaskIcon").click();
			fnpMymsg("Clicked on Cert Decision's Perform Task Icon.");
			
			
			fnpPFList("CertDecAI_MarkSelectedStandardStatusTo", "CertDecAI_MarkSelectedStandardStatusToOptions", "LIST");
			
			fnpClick_OR("CertDecAI_UpdateStandardStatusBttn");
			fnpLoading_wait();
			fnpCheckErrMsg("UpdateStandardStatusBttn click");
			
			
			
			fnpClick_OR("CertDecAI_CompleteCertDecisionBttn");
			fnpLoading_wait();
					
			
			fnpClick_OR("CertDecAI_CompleteCertDecision_ConfirmBttn");
			fnpLoading_wait();
			fnpCheckErrMsg("CertDecAI_CompleteCertDecision_ConfirmBttn click");
			
			fnpWaitForVisible("CertIssue_AI");
			
			String CheckBox_Xpath = OR.getProperty("CertDecAI_MarkSelectedStandardStatusTo")+"/following::span[@class='ui-chkbox-icon ui-icon ui-icon-blank ui-c'][1]";
			
			fnpPFList("CertDecAI_MarkSelectedStandardStatusTo", "CertDecAI_MarkSelectedStandardStatusToOptions", "DROP");
			
			fnpClick_NOR(CheckBox_Xpath);
			fnpLoading_wait();
		
			
			fnpClick_OR("CertDecAI_UpdateStandardStatusBttn");
			fnpLoading_wait();
			fnpCheckErrMsg("UpdateStandardStatusBttn click");
			
			
			
			fnpClick_OR("CertDecAI_CompleteCertDecisionBttn");
			fnpLoading_wait();
					
			
			fnpClick_OR("CertDecAI_CompleteCertDecision_ConfirmBttn");
			fnpLoading_wait();
			fnpCheckErrMsg("CertDecAI_CompleteCertDecision_ConfirmBttn click");
			
			fnpWaitForVisible("FailureResolution_AI");
			
			
			String CertIssue_AI = fnpFetchFromTable("LiteEval_ActionItemsTab_ActionItemTable", 2, 1);
			String CertIssue_AILink_Xpath = "//a[text()='"+CertIssue_AI+"']";
			fnsWait_Click_on_First_Visible_Element_if_More_than_One_Coming("CertIssue AI Link", CertIssue_AILink_Xpath);
			fnpLoading_wait();
			fnpLoading_wait();
			
			fnpPFList("CertIssueAI_ChangeStatusPFList", "CertIssueAI_ChangeStatusPFListOptions", "Completed");
			
			fnpClick_OR("SaveBtnOnTransferReviewAIScreen");
			fnpLoading_wait();
			fnpLoading_wait();
					
			fnpClick_OR("CertDecAI_Save_ConfirmationBttn");
			fnpLoading_wait();
			fnpCheckErrMsg("CertIssue AI  Update");
			
			
			fnpSearchWorkOrderOnly(workOrderNo);				
			if (fnpCheckElementDisplayedImmediately("EditWOBtn")) {
				fnpClick_OR("EditWOBtn");
				fnpMymsg("@  ---Here EditWOBtn is visible , so going to click it");

			}else{
				fnpMymsg("@  ---Here EditWOBtn is NOT visible , so ignore  it");
			}	
			
			Verify_AmountUnder_FinancialTabInfoSection(IPULSE_CONSTANTS.SnapShot_taskDesc_CertDeci, true);
			
		} catch (Throwable t) {
			 isTestPass = false;
			fnpMymsg(" **************************************************************");
			fnpCommonFinalCatchBlock(t, "  CertDec_Task flow  is fail . ",	"CertDec_Task");
		}

	}

//################################################################ Class ##################################################################################
	
	public void fnc_Type_IntoEmailTemplateBody () throws Throwable {
		try {
			Thread.sleep(2000);
			driver.switchTo().frame(0);
			Thread.sleep(2000);
			driver.findElement(By.xpath(OR.getProperty("TestingTask_CreateTestReportReviewAIPopup_AddNote"))).sendKeys("Automation Note");
			Thread.sleep(2000);			
			driver.switchTo().defaultContent();
			Thread.sleep(2000);
		}catch(Throwable t) {
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}
	
	public static void Verify_AmountUnder_FinancialTabInfoSection(String TaskDesc, boolean InvoicedVerification) throws Throwable {

		try {
			fnpCommonClickFinancialTab();
			fnpLoading_wait();
			Thread.sleep(2000);
			fnpLoading_wait();
			fnpWaitForVisible("FinancialTab_EditWO_FinancialInfoSec_Task_Link");

			fnpClick_OR("FinancialTab_EditWO_FinancialInfoSec_Task_Link");
			fnpLoading_wait();
			Thread.sleep(2000);
			fnpLoading_wait();
			fnpWaitForVisible("FinancialTab_EditWO_FinancialInfoTable_header");
			String TaskDesc_ColName = "Task Desc"; 
			int TaskDescIndex = fnpFindColumnIndex("FinancialTab_EditWO_FinancialInfoTable_header", TaskDesc_ColName);
			
			String RevenuePostedAmt_ColName = "Revenue Posted Amt"; 
			int RevenuePostedAmtIndex = fnpFindColumnIndex("FinancialTab_EditWO_FinancialInfoTable_header", RevenuePostedAmt_ColName);
			
			String QuotedAmt_Name = "Quoted Amt"; 
			int QuotedAmtIndex =  fnpFindColumnIndex("FinancialTab_EditWO_FinancialInfoTable_header", QuotedAmt_Name);
			
			int TaskName_RowIndex = fnpFindRow("FinancialTab_EditWO_FinancialInfoTable", TaskDesc, TaskDescIndex);
			
			String RevenuePostedAmt = fnpFetchFromTable("FinancialTab_EditWO_FinancialInfoTable", TaskName_RowIndex, RevenuePostedAmtIndex);
					
			String QuotedAmt = fnpFetchFromTable("FinancialTab_EditWO_FinancialInfoTable", TaskName_RowIndex,  QuotedAmtIndex);
			
			assert RevenuePostedAmt.equalsIgnoreCase(QuotedAmt): "FAILED == Revenue Posted Amount <"+RevenuePostedAmt+"> and Quoted Amount <"+QuotedAmt+"> are not matched for Task '"+TaskDesc+"'";
			fnpMymsg("PASSED == Revenue Posted Amount <"+RevenuePostedAmt+"> and Quoted Amount <"+QuotedAmt+"> are matched for Task '"+TaskDesc+"'");
			
			
			if(InvoicedVerification) {
				for(int i=0; i<=5; i++) {
					try {
						driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
						if(driver.findElement(By.xpath(OR.getProperty("Facility_InvoiceCompleteTaskBtn"))).isDisplayed()) {
							driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);
							fnpClick_OR("Facility_InvoiceCompleteTaskBtn");
							fnpClickInDialog_OR("Facility_InvoiceCompleteTask_YesBtn");
							break;
						}else { 
							Thread.sleep(1000);
						}
					}catch(Throwable e) {
						//nothing to do
					}
				}
				
				fnpWaitForVisible("FinancialTab_EditWO_FinancialInfoSec_Task_Link");
	
				fnpClick_OR("FinancialTab_EditWO_FinancialInfoSec_Task_Link");
				
				fnpWaitForVisible("FinancialTab_EditWO_FinancialInfoTable_header");
				String InvoicedAmount_ColName = "Revenue Posted Amt"; 
				int InvoicedAmountIndex = fnpFindColumnIndex("FinancialTab_EditWO_FinancialInfoTable_header", InvoicedAmount_ColName);
				
				String InvoicedAmount = fnpFetchFromTable("FinancialTab_EditWO_FinancialInfoTable", TaskName_RowIndex, InvoicedAmountIndex);
				
				assert RevenuePostedAmt.equalsIgnoreCase(InvoicedAmount): "FAILED == Revenue Posted Amount <"+RevenuePostedAmt+"> and Invoiced Amount <"+InvoicedAmount+"> are not matched for Task '"+TaskDesc+"'";
				fnpMymsg("PASSED == Revenue Posted Amount <"+RevenuePostedAmt+"> and Invoiced Amount <"+InvoicedAmount+"> are matched for Task '"+TaskDesc+"'");
			}
		} catch (Throwable t) { 
			throw new Exception(Throwables.getStackTraceAsString(t));

		}

	}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//######################################################################## Config ########################################################################
	@AfterMethod
	public void reportDataSetResult() {
		if (fail) {
			isTestPass = false;
		}
	}

	@AfterTest
	public void reportTestResult() throws Throwable {
		int rowNum = currentSuiteXls.getCellRowNum("Test Cases", "TCID", this.getClass().getSimpleName());
		currentSuiteXls.setCellData("Test Cases", "Last_Execution", rowNum, fnReturnDateWithTimeForExcel());

		// Add Results column to test case sheet
		if (isTestPass) {
			TestUtil.reportDataSetResult(currentSuiteXls, "Test Cases",
					TestUtil.getRowNum(currentSuiteXls, this.getClass().getSimpleName()), "PASS");
			currentSuiteXls.setCellData("Test Cases", "Last_Successful_Execution", rowNum,
					fnReturnDateWithTimeForExcel());
			fnpElapsedTime(currentSuiteName, this.getClass().getSimpleName(), currentScriptCode, "PASS");
		} else {
			TestUtil.reportDataSetResult(currentSuiteXls, "Test Cases",
					TestUtil.getRowNum(currentSuiteXls, this.getClass().getSimpleName()), "FAIL");
			fnpElapsedTime(currentSuiteName, this.getClass().getSimpleName(), currentScriptCode, "FAIL");
		}
		fnpMymsg(
				"=========================================================================================================================================");

		hashXlData.clear();

		fnpCommonCloseBrowsersAndKillProcess();

	}

}