package nsf.ecap.Agriculture_suite;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import nsf.ecap.Health_Science_suite.TestSuiteBase_HealthScience;
import nsf.ecap.Work_Order_suite.NewNew_Draft_InReview_No_Fac;
import nsf.ecap.config.IPULSE_CONSTANTS;
import nsf.ecap.util.ErrorUtil;
import nsf.ecap.util.TestUtil;
import nsf.ecap.util.Xls_Reader;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
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

//BS-05
public class Alerts_testing_App_Form_Option2 extends TestSuiteBase_Agriculture_suite {
	
	
	//SoftAssert softAssert = null ;
	SoftAssert softAssert ;
	Hashtable<String, String> ht = new Hashtable<String,String>();
	
	String productInFirstRow;
	String pathtoCopy;
	
	@BeforeTest
	public void checkTestSkip() throws Throwable {
		
		CM_BS01 = new Create_Membership();
		CM_BS01.checkTestSkip(this.getClass().getSimpleName());

		//hashXlData.clear();
	

	}

	 @Test(priority = 1)

	public void Create() throws Throwable {
		// start = new Date();//This start  time here refer from created membership onwards
		 setStartDate();
		 
		 try{
			 
			    String textMessage;
				
			    fnpCommonOption2_code_from_Import_Till_CorrespondingWO_created();
			   
			    
			    String wholeString=fnpGetText_OR("iAg_ImportResults_Membership_No").trim();
			    String memberhipNo=wholeString.split(":")[1].trim();
			    
			    
			    fnpClickTopHomeMenu();
			   
				ht.putAll(hashXlData);
			    
			    //TestSuiteBase_HealthScience.fnpCommonAlertClickedFirstRecord_in_specifiedColumnName_afterSearching(ht, "Global Gap Pending Import Registration", 1, "Import No",memberhipNo);
			    fnpVerificationInAlert_GlobalGapPendingImportRegistration(ht, "Global Gap Pending Import Registration", 1, "Import No",memberhipNo);
			    
			    
			    
			    /***********************************************************/
				
				if (fnpCheckElementDisplayedImmediately("SelectCheckBox_unchecked")) {
					fnpClick_OR("SelectCheckBox_unchecked");
					Thread.sleep(1000);
					fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
					
				} else {
					throw new Exception("Unchecked Select checkbox is not present at the end of the Producer Group record.");
				}
				
				
				String checkedCheckboxClass=fnpGetAttribute_OR("SelectCheckBox", "class");
				if (checkedCheckboxClass.contains("ui-c ui-icon-check")) {
					fnpMymsg("Select checkbox is checked successfully.");
					
				} else {
					throw new Exception("Select checkbox is NOT checked successfully.");
				}
				
				String createNewRadioBtnClass=fnpGetAttribute_OR("CreateNewRadioButton", "class");
				if (createNewRadioBtnClass.contains("ui-icon-bullet")) {
					fnpMymsg("The Create New radio button has been checked.");
					
				} else {
					throw new Exception("The Create New radio button has NOT been checked automatically after checking Select checkbox at the end of the Producer Group record.");
				}
			    
			    /************************************************************/
			    
				
				
				
				fnpClick_OR("SubmitSelectedRowsBtn");
				
				
				
				
/*				fnpClick_OR("YesConfirmationBtnInSubmissionConfirmationPopup");
				fnpWaitForVisibleHavingMultipleElements("TopMessage3");
				String SuccessfulMsg = fnpGetText_OR("TopMessage3");
				fnpMymsg("Top Message after clicking Yes Confirmation Btn In Submission Confirmation Popup ----" + SuccessfulMsg);

				Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"),
						"Top message does not contain 'success' word, so might be submission is not successfull.");
				
			    
				*/
				
				
				
			/****************************************************************************************************/	
				
			//	fnpWorkAroundSubmittingSelectedRows();	
				 fnpWorkAroundSubmittingSelectedRows_ignoringUnknownErrorIfAny();
				fnpCheckError("Error is thrown by applicataion after clicking on Yes button in confirmation dialog for submitting the selected rows.");
				
		/****************************************************************************************************/	
				
				
				
			    
			    String scheme_wo_link_xpath = OR.getProperty("WoLinkAtImportResultsScreen_part1")+IPULSE_CONSTANTS.AH2_scheme_name+OR.getProperty("WoLinkAtImportResultsScreen_part2");
			    
				String woNo = fnpGetText_NOR(scheme_wo_link_xpath);
				fnpMymsg("Wo no. is ---"+woNo);
			    
				String actualClassNameText=classNameText;
				try{					
					classNameText=AH2_scheme_TestCase_Name;
					fnpMatchScheme(IPULSE_CONSTANTS.AH2_scheme_name);					
					fnpVerify_Services_tab();
				}catch(Throwable t){
					throw t;
				}finally{
					classNameText=actualClassNameText;
				}
				 
				 
			    fnpClickTopHomeMenu();
			    
			    
			    String alertName="Membership Work Order Created in InReview Status";			    
			    String AlertNameXpath = ".//legend[contains(text(),'" + alertName + "')]";
			    
			    fnpWaitForVisible_NotInOR(AlertNameXpath);
			    String completeAlertNameText=fnpGetText_NOR(AlertNameXpath).trim();
			    String alertCountInString=completeAlertNameText.split(alertName)[0].trim();
			    int initialAlertCount=Integer.parseInt(alertCountInString);
			    fnpMymsg("Initial alert count for this alert '"+alertName+"' is --"+initialAlertCount);
			    
			    TestSuiteBase_HealthScience.fnpCommonAlertClickedFirstRecord_in_specifiedColumnName_afterSearching(ht,alertName , 1, "WO #",woNo);
		
			    fnpClick_OR("EditWOBtn");
			    fnpClick_OR("iAg_InfoTab");
				fnpPFList("Membership_WO_Status_PFList", "Membership_WO_Status_PFListOptions", "ACTIVE");
				fnpLoading_wait();//using this on 04-03-2021 becasue now onwards after selecting any value in above 'Membership_WO_Status_PFList' loading is coming 
				fnpClick_OR("WO_Save_btn");
				fnpWaitForVisibleHavingMultipleElements("TopMessage3");
				String SuccessfulMsg = fnpGetText_OR("TopMessage3");
				fnpMymsg("Top Message after changing the Membership statu to ACTIVE  ----" + SuccessfulMsg);
			    
				fnpClickTopHomeMenu();
			   // fnpWaitForVisible_NotInOR(AlertNameXpath);
				int afterActive_AlertCount=0;
			    if (fnpCheckElementDisplayedImmediately_NOR(AlertNameXpath)) {
				    completeAlertNameText=fnpGetText_NOR(AlertNameXpath).trim();
				    alertCountInString=completeAlertNameText.split(alertName)[0].trim();
				    afterActive_AlertCount=Integer.parseInt(alertCountInString);
				}else{
					//nothing for now...
				}

			    fnpMymsg("After wo active, alert count for this alert '"+alertName+"' is --"+afterActive_AlertCount); 
			    Assert.assertEquals(afterActive_AlertCount, (initialAlertCount-1), "count for '"+alertName+"' has NOT decreased by one ");
			    fnpMymsg("count for '"+alertName+"'has decreased by one ");
			    if (afterActive_AlertCount!=0) {
			    	TestSuiteBase_HealthScience.fnpCommonAlertDeletedVerification(ht,alertName , 1, "WO #",woNo);
				}
			    
			    
			  
			    
			    
			    
			    
	
			    // Contractreviewassignedtome_alertName = "Contract review assigned to me";	
			     String Contractreviewassignedtome_alertName = "Contract Review Action, Assigned to me";	
			     String Contractreviewassignedtome_AlertNameXpath = ".//legend[contains(text(),'" + Contractreviewassignedtome_alertName + "')]";			    
			  //  fnpWaitForVisible_NotInOR(AlertNameXpath);
			    int initialCount_ContractReviewAssignedToMe=0;
			    if (fnpCheckElementDisplayedImmediately_NOR(Contractreviewassignedtome_AlertNameXpath)) {
				    completeAlertNameText=fnpGetText_NOR(Contractreviewassignedtome_AlertNameXpath).trim();
				    alertCountInString=completeAlertNameText.split(Contractreviewassignedtome_alertName)[0].trim();
				    initialCount_ContractReviewAssignedToMe=Integer.parseInt(alertCountInString);
				} else {
					initialCount_ContractReviewAssignedToMe=0;
				}			    
			    fnpMymsg("Initial alert count for this alert '"+Contractreviewassignedtome_alertName+"' is --"+initialCount_ContractReviewAssignedToMe);
			    
			    
			    
			    
		
			    String ContractReviewActionUnassigned_alertName = "Contract Review Action, Unassigned";	
			    String ContractReviewActionUnassigned_AlertNameXpath = ".//legend[contains(text(),'" + ContractReviewActionUnassigned_alertName + "')]";
			    fnpWaitForVisible_NotInOR(ContractReviewActionUnassigned_AlertNameXpath);
			    completeAlertNameText=fnpGetText_NOR(ContractReviewActionUnassigned_AlertNameXpath).trim();
			    alertCountInString=completeAlertNameText.split(ContractReviewActionUnassigned_alertName)[0].trim();
			    int contractReviewActionUnassigned__initialAlertCount = Integer.parseInt(alertCountInString);
			    fnpMymsg("Initial alert count for this alert '"+ContractReviewActionUnassigned_alertName+"' is --"+contractReviewActionUnassigned__initialAlertCount);
			    
			    
			    TestSuiteBase_HealthScience.fnpCommonAlertClickedFirstRecord_in_specifiedColumnName_afterSearching(ht,ContractReviewActionUnassigned_alertName , 1, "WO #",woNo);
				fnpClick_OR("SnapshotTab_TasksTable_MembershipTask_OpenIcon");
				
				
				
/*				
				String snapshotTab_TasksTable_InnerAITable_data_xpath=".//*[@id='mainForm:tabView:taskDT:0:actionItemDT_data']";
				String snapshotTab_TasksTable_InnerAITable_header_xpath=".//*[@id='mainForm:tabView:taskDT:0:actionItemDT_head']";
				fnpWaitForVisible_NotInOR(snapshotTab_TasksTable_InnerAITable_data_xpath);

				int ActionItemColIndex = fnpFindColumnIndex_NOR(snapshotTab_TasksTable_InnerAITable_header_xpath, "Action Item #");
				

				//int itemDescColIndex = fnpFindColumnIndex_NOR(snapshotTab_TasksTable_InnerAITable_header_xpath, "Action Item Name");
				int actionItemNameColIndex = fnpFindColumnIndex_NOR(snapshotTab_TasksTable_InnerAITable_header_xpath, "Action Item Name");
				
				String actionItemName="ContractReview";
				int actionItemInfoRowNo = fnpFindRow_NOR(snapshotTab_TasksTable_InnerAITable_data_xpath, actionItemName, actionItemNameColIndex);
				String actionItemNo = fnpFetchFromTable_NOR(snapshotTab_TasksTable_InnerAITable_data_xpath, actionItemInfoRowNo, ActionItemColIndex);
				int start = 1;
				while (actionItemNo.isEmpty()) {
					Thread.sleep(1000);
					actionItemNo = fnpFetchFromTable_NOR(snapshotTab_TasksTable_InnerAITable_data_xpath, actionItemInfoRowNo, ActionItemColIndex);

					if (start > 50) {
						break;
					}
				}
				Thread.sleep(500);				
				fnpClickALinkInATable(actionItemNo);								
				fnpMymsg("Clicked on  Action Item '" + actionItemName + "' in Table having no. is '" + actionItemNo + "' .");
*/
				
				
				
				fnpProcessiAgAI2(IPULSE_CONSTANTS.iAg_taskType_NEWMEM,IPULSE_CONSTANTS.iAg_task_NEWMEM_Description,"ContractReview","");
				
				fnpClickTopHomeMenu();
			    int laterCount_ContractReviewAssignedToMe=0;
			    if (fnpCheckElementDisplayedImmediately_NOR(Contractreviewassignedtome_AlertNameXpath)) {
				    completeAlertNameText=fnpGetText_NOR(Contractreviewassignedtome_AlertNameXpath).trim();
				    alertCountInString=completeAlertNameText.split(Contractreviewassignedtome_alertName)[0].trim();
				    laterCount_ContractReviewAssignedToMe=Integer.parseInt(alertCountInString);
				} else {
					laterCount_ContractReviewAssignedToMe=0;
					msg="This alert '"+Contractreviewassignedtome_alertName+"' is not generated after clicking button 'Assigned To Me' in ContractReview action item.";
					fnpMymsg(msg);
					throw new Exception(msg);
				}			    
			    fnpMymsg("After clicking button 'Assigned To Me'   alert count for this alert '"+Contractreviewassignedtome_alertName+"' is --"+laterCount_ContractReviewAssignedToMe);
			    Assert.assertEquals(laterCount_ContractReviewAssignedToMe, (initialCount_ContractReviewAssignedToMe+1), "count for '"+Contractreviewassignedtome_alertName+"' has NOT increased by one after clicking button 'Assigned To Me' in ContractReview action item.");
			    fnpMymsg("count for '"+Contractreviewassignedtome_alertName+"'has increased by one ");
			    
			   	TestSuiteBase_HealthScience.fnpCommonAlertClickedFirstRecord_in_specifiedColumnName_afterSearching(ht,Contractreviewassignedtome_alertName , 1, "WO #",woNo);
				fnpClick_OR("SnapshotTab_TasksTable_MembershipTask_OpenIcon");	
				
				
				
				
				
			
				fnpProcessiAgAI2(IPULSE_CONSTANTS.iAg_taskType_NEWMEM,IPULSE_CONSTANTS.iAg_task_NEWMEM_Description,"ContractReview","AddlInfoRqstd");
				
				
				
				fnpClickTopHomeMenu();
			    fnpWaitForVisible_NotInOR(ContractReviewActionUnassigned_AlertNameXpath);
			    completeAlertNameText=fnpGetText_NOR(ContractReviewActionUnassigned_AlertNameXpath).trim();
			    alertCountInString=completeAlertNameText.split(ContractReviewActionUnassigned_alertName)[0].trim();
			    afterActive_AlertCount=Integer.parseInt(alertCountInString);
			    fnpMymsg("After, alert count for this alert '"+ContractReviewActionUnassigned_alertName+"' is --"+afterActive_AlertCount); 
			  
				 
			    Assert.assertEquals(afterActive_AlertCount, (contractReviewActionUnassigned__initialAlertCount-1), "count for '"+ContractReviewActionUnassigned_alertName+"' has NOT decreased by one ");
			    fnpMymsg("count for '"+ContractReviewActionUnassigned_alertName+"'has decreased by one ");
			    			    
			    TestSuiteBase_HealthScience.fnpCommonAlertDeletedVerification(ht,ContractReviewActionUnassigned_alertName , 1, "WO #",woNo);
			    
			    fnpLoading_wait();
			    
			    
			    
			    
			   // alertName="Contract review assigned to me";	
			  // AlertNameXpath = ".//legend[contains(text(),'" + alertName + "')]";	
			     int Again_laterCount_ContractReviewAssignedToMe = 0;
			  //  if (fnpCheckElementDisplayedImmediately_NOR(Contractreviewassignedtome_AlertNameXpath)) {
			    if (fnpCheckElementDisplayed_NOR(Contractreviewassignedtome_AlertNameXpath, 15)){
				    completeAlertNameText=fnpGetText_NOR(Contractreviewassignedtome_AlertNameXpath).trim();
				    alertCountInString=completeAlertNameText.split(Contractreviewassignedtome_alertName)[0].trim();
				    Again_laterCount_ContractReviewAssignedToMe=Integer.parseInt(alertCountInString);
				} else {
					Again_laterCount_ContractReviewAssignedToMe=0;
					msg="This alert '"+Contractreviewassignedtome_alertName+"' is not present after AddlInfoRqstd in ContractReview action item.";
					fnpMymsg(msg);
				//	throw new Exception(msg);
				}			    
			    fnpMymsg("After  AddlInfoRqstd in ContractReview action item, count for this alert '"+Contractreviewassignedtome_alertName+"' is --"+Again_laterCount_ContractReviewAssignedToMe);
			    Assert.assertEquals(Again_laterCount_ContractReviewAssignedToMe, (laterCount_ContractReviewAssignedToMe-1), "count for '"+Contractreviewassignedtome_alertName+"' has NOT decreased by one after selecting this status 'AddlInfoRqstd' in ContractReview action item.");
			    fnpMymsg("count for '"+Contractreviewassignedtome_alertName+"'has decreased by one after selecting this status 'AddlInfoRqstd' in ContractReview action item.");
			   
			    if (Again_laterCount_ContractReviewAssignedToMe!=0) {
			    	 TestSuiteBase_HealthScience.fnpCommonAlertDeletedVerification(ht,Contractreviewassignedtome_alertName , 1, "WO #",woNo);
				}
			   
			    
			    
			    
			    
			    
			    
			    
			    
			    
			    
			    
			    
			    
			    
			    
			    
			    
			    
			    
			    
			    
			    
			    
			    
			    
			    
			    
			    
			    
			    
/*			    
			    
*//***********Commented as not valid for this user iag_test_int**************************************//*    
			    
			    
			    
			    *//******Step 35 and 36 "In the AI "Contract review" set to "AddInfoRequested" status and add "Add info request test" in the "Add notes" box"    end****//*
			    
			    
			    
			    *//*******step 37 is missing*********************//*
			    
			    *//********Step 38 start******Expand AI "EnterMemDetails" and click on <Assign to me and move to progress>********//*
			    
			    String alertName_ContractReviewActionUnassigned="Contract Review Action, Unassigned";	
			    String alertName_ContractReviewActionUnassigned_Xpath = ".//legend[contains(text(),'" + alertName_ContractReviewActionUnassigned + "')]";
			    fnpWaitForVisible_NotInOR(alertName_ContractReviewActionUnassigned_Xpath);
			    String completeAlertNameText_ContractReviewActionUnassigned = fnpGetText_NOR(alertName_ContractReviewActionUnassigned_Xpath).trim();
			    String alertCountInString_ContractReviewActionUnassigned = completeAlertNameText_ContractReviewActionUnassigned.split(alertName_ContractReviewActionUnassigned)[0].trim();
			    int initialAlertCount_ContractReviewActionUnassigned = Integer.parseInt(alertCountInString_ContractReviewActionUnassigned);
			    fnpMymsg("Initial alert count for this alert '"+alertName_ContractReviewActionUnassigned+"' is --"+initialAlertCount_ContractReviewActionUnassigned);
			    
			    
			    
			    String alertName_Contractreviewassignedtome = "Contract review assigned to me";	
			    String alertName_ContractreviewassignedtomeXpath = ".//legend[contains(text(),'" + alertName_Contractreviewassignedtome + "')]";			    
			  //  fnpWaitForVisible_NotInOR(AlertNameXpath);
			     initialCount_ContractReviewAssignedToMe=0;
			    if (fnpCheckElementDisplayedImmediately_NOR(alertName_ContractreviewassignedtomeXpath)) {
				    completeAlertNameText=fnpGetText_NOR(alertName_ContractreviewassignedtomeXpath).trim();
				    alertCountInString=completeAlertNameText.split(alertName_Contractreviewassignedtome)[0].trim();
				    initialCount_ContractReviewAssignedToMe=Integer.parseInt(alertCountInString);
				} else {
					//initialCount_ContractReviewAssignedToMe=0;
				}			    
			    fnpMymsg("Initial alert count for this alert '"+alertName_Contractreviewassignedtome+"' is --"+initialCount_ContractReviewAssignedToMe);
			    
			    
			    
			    
			    
			    
			    
			    
			    
			    
		
			    alertName="Contract Review Rejected Unassigned";			    
			    AlertNameXpath = ".//legend[contains(text(),'" + alertName + "')]";
			    fnpWaitForVisible_NotInOR(AlertNameXpath);
			    completeAlertNameText=fnpGetText_NOR(AlertNameXpath).trim();
			    alertCountInString=completeAlertNameText.split(alertName)[0].trim();
			    initialAlertCount=Integer.parseInt(alertCountInString);
			    fnpMymsg("Initial alert count for this alert '"+alertName+"' is --"+initialAlertCount);			    
			    TestSuiteBase_HealthScience.fnpCommonAlertGeneratedVerification( ht,  alertName,  "WO #",woNo);
				 TestSuiteBase_HealthScience.fnpCommonAlertClickedFirstRecord_in_specifiedColumnName_afterSearching(ht,alertName , 1, "WO #",woNo);
				
				
			    
			    
			    

			    
				
			    *//***************for time being I have to search the memebership because above alert 'Contract Review Rejected Unassigned' is not getting generated, so do not found other option to go to inside wo ******//*
			    fnpSearchWorkOrderOnly(woNo);
			    *//***************************************//*
			    fnpClick_OR("SnapshotTab_TasksTable_MembershipTask_OpenIcon");
			    fnpProcessiAgAI2(IPULSE_CONSTANTS.iAg_taskType_NEWMEM,IPULSE_CONSTANTS.iAg_task_NEWMEM_Description,"EnterMemDetails","");
			    
			    
			    fnpClickTopHomeMenu();
			    
			    fnpWaitForVisible_NotInOR(alertName_ContractReviewActionUnassigned_Xpath);
			    completeAlertNameText_ContractReviewActionUnassigned = fnpGetText_NOR(alertName_ContractReviewActionUnassigned_Xpath).trim();
			    alertCountInString_ContractReviewActionUnassigned = completeAlertNameText_ContractReviewActionUnassigned.split(alertName_ContractReviewActionUnassigned)[0].trim();
			    int laterAlertCount_ContractReviewActionUnassigned = Integer.parseInt(alertCountInString_ContractReviewActionUnassigned);
			    fnpMymsg("later, alert count for this alert '"+alertName+"' is --"+laterAlertCount_ContractReviewActionUnassigned); 
			    Assert.assertEquals(laterAlertCount_ContractReviewActionUnassigned, (initialAlertCount_ContractReviewActionUnassigned-1), "count for '"+alertName_ContractReviewActionUnassigned+"' has NOT decreased by one ");
			    fnpMymsg("count for '"+alertName_ContractReviewActionUnassigned+"'has decreased by one ");			    			    
			    TestSuiteBase_HealthScience.fnpCommonAlertDeletedVerification(ht,alertName_ContractReviewActionUnassigned , 1, "WO #",woNo);
			    
			    
			    String alertName_GLOBALGAPInterfaceActionItemPending = "GLOBALG.A.P. Interface Action Item Pending";	
			    String alertName_GLOBALGAPInterfaceActionItemPending_Xpath = ".//legend[contains(text(),'" + alertName_GLOBALGAPInterfaceActionItemPending + "')]";			    
			    int initialCount_GLOBALGAPInterfaceActionItemPending=0;
			    if (fnpCheckElementDisplayedImmediately_NOR(alertName_GLOBALGAPInterfaceActionItemPending_Xpath)) {
				    completeAlertNameText=fnpGetText_NOR(alertName_GLOBALGAPInterfaceActionItemPending_Xpath).trim();
				    alertCountInString=completeAlertNameText.split(alertName_GLOBALGAPInterfaceActionItemPending)[0].trim();
				    initialCount_GLOBALGAPInterfaceActionItemPending=Integer.parseInt(alertCountInString);
				} else {
					//initialCount_ContractReviewAssignedToMe=0;
				}			    
			    fnpMymsg("Initial alert count for this alert '"+alertName_GLOBALGAPInterfaceActionItemPending+"' is --"+initialCount_GLOBALGAPInterfaceActionItemPending);
			    
			    

			    int laterCount_ContractReviewAssignedToMe=0;
			    if (fnpCheckElementDisplayedImmediately_NOR(alertName_ContractreviewassignedtomeXpath)) {
				    completeAlertNameText=fnpGetText_NOR(alertName_ContractreviewassignedtomeXpath).trim();
				    alertCountInString=completeAlertNameText.split(alertName_Contractreviewassignedtome)[0].trim();
				    laterCount_ContractReviewAssignedToMe=Integer.parseInt(alertCountInString);
				} else {
					laterCount_ContractReviewAssignedToMe=0;
					msg="This alert '"+alertName_Contractreviewassignedtome+"' is not generated after clicking button 'Assigned To Me' in EnterMemDetails action item.";
					fnpMymsg(msg);
					throw new Exception(msg);
				}			    
			    fnpMymsg("After clicking button 'Assigned To Me'   alert count for this alert '"+alertName_Contractreviewassignedtome+"' is --"+laterCount_ContractReviewAssignedToMe);
			    Assert.assertEquals(laterCount_ContractReviewAssignedToMe, (initialCount_ContractReviewAssignedToMe+1), "count for '"+alertName_Contractreviewassignedtome+"' has NOT increased by one after clicking button 'Assigned To Me' in EnterMemDetails action item.");
			    fnpMymsg("count for '"+alertName_Contractreviewassignedtome+"'has increased by one ");
			   
			   // TestSuiteBase_HealthScience.fnpCommonAlertGeneratedVerification( ht,  alertName_Contractreviewassignedtome,  "WO #",woNo);
			    
			    *//********Step 38 end Expand AI "EnterMemDetails" and click on <Assign to me and move to progress>*************//*
			    
			    
			    
			    *//******Step 39 start*******************//*
			    TestSuiteBase_HealthScience.fnpCommonAlertClickedFirstRecord_in_specifiedColumnName_afterSearching(ht,alertName_Contractreviewassignedtome , 1, "WO #",woNo);
				fnpClick_OR("SnapshotTab_TasksTable_MembershipTask_OpenIcon");
				fnpProcessiAgAI2(IPULSE_CONSTANTS.iAg_taskType_NEWMEM,IPULSE_CONSTANTS.iAg_task_NEWMEM_Description,"ContractReview","Completed");
			    
			    
				 fnpClickTopHomeMenu();
			    
				int laterCount_ContractReviewAssignedToMe_afterContractReviewCompleted;
			    if (fnpCheckElementDisplayedImmediately_NOR(alertName_ContractreviewassignedtomeXpath)) {
				    completeAlertNameText=fnpGetText_NOR(alertName_ContractreviewassignedtomeXpath).trim();
				    alertCountInString=completeAlertNameText.split(alertName_Contractreviewassignedtome)[0].trim();
				    laterCount_ContractReviewAssignedToMe_afterContractReviewCompleted=Integer.parseInt(alertCountInString);				    
				    Assert.assertEquals(laterCount_ContractReviewAssignedToMe_afterContractReviewCompleted, (laterCount_ContractReviewAssignedToMe-1), "count for '"+alertName_Contractreviewassignedtome+"' has NOT decreased by one ");
				    fnpMymsg("count for '"+alertName_Contractreviewassignedtome+"'has decreased by one ");    
				    TestSuiteBase_HealthScience.fnpCommonAlertDeletedVerification(ht,alertName_Contractreviewassignedtome , 1, "WO #",woNo);
				} else {
					laterCount_ContractReviewAssignedToMe=0;
					msg="This alert '"+alertName_Contractreviewassignedtome+"' is not generated after completing ContractReview action item.";
					fnpMymsg(msg);
					
				}			    

				
				
				
				
			    int laterCount_GLOBALGAPInterfaceActionItemPending=0;
			    if (fnpCheckElementDisplayedImmediately_NOR(alertName_GLOBALGAPInterfaceActionItemPending_Xpath)) {
				    completeAlertNameText=fnpGetText_NOR(alertName_GLOBALGAPInterfaceActionItemPending_Xpath).trim();
				    alertCountInString=completeAlertNameText.split(alertName_GLOBALGAPInterfaceActionItemPending)[0].trim();
				    laterCount_GLOBALGAPInterfaceActionItemPending=Integer.parseInt(alertCountInString);
				} else {
					laterCount_GLOBALGAPInterfaceActionItemPending=0;
					msg="This alert '"+alertName_GLOBALGAPInterfaceActionItemPending+"' is not generated after completing ContractReview action item.";
					fnpMymsg(msg);
					throw new Exception(msg);
				}			    
			    fnpMymsg("After completing ContractReview action item  alert count for this alert '"+alertName_GLOBALGAPInterfaceActionItemPending+"' is --"+laterCount_GLOBALGAPInterfaceActionItemPending);
			    Assert.assertEquals(laterCount_GLOBALGAPInterfaceActionItemPending, (initialCount_GLOBALGAPInterfaceActionItemPending+1), "count for '"+alertName_GLOBALGAPInterfaceActionItemPending+"' has NOT increased by one after completing ContractReview action item.");
			    fnpMymsg("count for '"+alertName_GLOBALGAPInterfaceActionItemPending+"'has increased by one ");
			   
			    TestSuiteBase_HealthScience.fnpCommonAlertGeneratedVerification( ht,  alertName_GLOBALGAPInterfaceActionItemPending,  "WO #",woNo);
			    
			    

				
*//**************************************************************************//*				
				
				
				
	*/			
				
				
				
				
				
				
			    
				 
			
		} catch (Throwable t) {

		fnpCommonFinalCatchBlock(t, "  Create  is fail . ", "Create ");

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
		
		
		
		

	@AfterTest
	public void reportTestResult() throws Throwable {
		if (driver!=null) {
			driver.quit();
		}
		
		fnpReportTestResult(hashXlData, currentSuiteName, currentSuiteXls, currentScriptCode, classNameText, isTestPass);

	}

}