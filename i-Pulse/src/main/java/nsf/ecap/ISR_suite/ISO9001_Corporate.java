package nsf.ecap.ISR_suite;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import nsf.ecap.Work_Order_suite.NewNew_Draft_InReview_No_Fac;
import nsf.ecap.Work_Order_suite.TestSuiteBase;
import nsf.ecap.config.IPULSE_CONSTANTS;
import nsf.ecap.util.ErrorUtil;
import nsf.ecap.util.TestUtil;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

public class ISO9001_Corporate extends TestSuiteBase_ISR_suite {

	
	String deskAuditTaskTab_expectedBillCode = "";
	double deskAuditTaskTab_expectedQuantity = 0;
	double deskAuditTaskTab_expectedAuditDays = 0;
	
	String certAuditTaskTab_expectedBillCode = "";
	double certAuditTaskTab_expectedQuantity = 0;
	double certAuditTaskTab_expectedAuditDays = 0;
	
	
	public static Date Originalstart;
	
	public String FirstWoNo = null;
	public String SecondWoNo = null;
	
	public String workOrderName="";
	
	
	@BeforeTest
	public void checkTestSkip() throws Throwable {
		//ISR_BS01 = new ISO9001_Single();
		setISOSingleObject();
		ISR_BS01.checkTestSkip(this.getClass().getSimpleName());

	}

	// @Test(enabled = false)
	@Test(priority = 1)
	public void CreateWOFlow() throws Throwable {
		try{
			
			/*********** To create a single work order to associate to corporate**************/
			hashXlData.clear();
		//	fnpLoadHashData(hashXlData, ISR_suitexls, classNameText, 2, 3);
			fnpLoadHashData(hashXlData, ISR_suitexls, classNameText, 2, 5);
		
			
			
/*			
			//**********To drop the single wo intentially sometime  ************
			fnpDropWOISRandDeleteSomeDataFromDB_speciallyForCorporate("C0303282", "QMSI9K15");
			fnpDropWOISRandDeleteSomeDataFromDB("C0303282", "QMSI9K15");
			
			
			//**********To drop corporate wo  ************
			fnpDropWOISRandDeleteSomeDataFromDB_speciallyForCorporate("6U111", "QMSI9K15");
			fnpDropWOISRandDeleteSomeDataFromDB("6U111", "QMSI9K15");
			
			//************To drop Associated type WO
			fnpDropWOISRandDeleteSomeDataFromDB_speciallyForCorporate("C0035927", "QMSI9K15");
			fnpDropWOISRandDeleteSomeDataFromDB("C0035927", "QMSI9K15");
			
			*/
		
			
			
			

			
			
			
			String statuses = "DRAFT,INREVIEW,INPROCESS,COMPLETE";
			String[] statusToCheck = statuses.split(",");
			boolean needToCreateSingleWO=callVerifySingleWOPresentOnThisStandardFacilityOtherwiseCreate((String) hashXlData.get("Corp/Facility#"), (String) hashXlData.get("Standard"),statusToCheck);

			String singleWO;
			if (needToCreateSingleWO) {
				hashXlData.clear();
				fnpLoadHashData(hashXlData, ISR_suitexls, classNameText, 2, 5);
				String temp=classNameText;
				classNameText=IPULSE_CONSTANTS.ISO9001_SingleTestCaseName;
				singleWO=fnpCreateANewSingleWOTillStatus("DRAFT");
				classNameText=temp;
				fnpMymsg("Created Single work order for Corporate of this facility '"+(String) hashXlData.get("Corp/Facility#")+"' is ---"+singleWO);
				IsBrowserPresentAlready=true;
				
			}
			
			/******************************************************************************/
			
			hashXlData.clear();
			fnpLoadHashData(hashXlData, ISR_suitexls, classNameText, 2, 3);		
			fnpDropWOISRandDeleteSomeDataFromDB_speciallyForCorporate((String) hashXlData.get("Corp/Facility#"), (String) hashXlData.get("Standard"));
			
			hashXlData.clear();
			fnpLoadHashData(hashXlData, ISR_suitexls, classNameText, 2, 4);
			fnpMymsg("@  ---Before query starts");
			fnpDropWOISRandDeleteSomeDataFromDB((String) hashXlData.get("Corp/Facility#"), (String) hashXlData.get("Standard"));
			fnpMymsg("@  ---After query finishes");
			
			hashXlData.clear();
			fnpLoadHashData(hashXlData, ISR_suitexls, classNameText, 2, 3);
			fnpMymsg("@  ---Before query starts");
			fnpDropWOISRandDeleteSomeDataFromDB((String) hashXlData.get("Corp/Facility#"), (String) hashXlData.get("Standard"));
			fnpMymsg("@  ---After query finishes");
			

			
			
			hashXlData.clear();
			fnpLoadHashData(hashXlData, ISR_suitexls, classNameText, 2, 3);
			
			ISR_BS01.CreateWOFlow();
			Originalstart = start;
			String workOrderNo_text = fnpGetORObjectX("NewlyCreatedWorkOrderNo").getText();
			FirstWoNo = ((workOrderNo_text.split(" "))[0]).trim();
			fnpMymsg("First  WO no. is:" + FirstWoNo);
			workOrderName="Corporate";
		} catch (Throwable t) {
				
				fnpCommonFinalCatchBlock(t, "  CreateWOFlow flow  is fail . ", "CreateWOFlowFail");
			
			}

	}
	

	@Test(priority = 2, dependsOnMethods = { "CreateWOFlow" })
	public void Standard_Facility_Tab() throws Throwable {

		ISR_BS01.Standard_Facility_Tab();

	}
	
	@Test(priority = 3, dependsOnMethods = { "Standard_Facility_Tab" })
	public void Financial_Tab_Corporate() throws Throwable {

		try{
			fnpCommonCodeOfFinancialTabOfSingleWO_CorporateWO();
			String Finops_ReassignedTo;
			
			
/*			
			if (loginAsFullName.equalsIgnoreCase("") | (loginAsFullName==null) ) {
				Finops_ReassignedTo=(String) hashXlData.get("Finops_ReassignedTo").trim();
			} else {
				Finops_ReassignedTo=loginAsFullName;
			}
			*/
			
			if (  (loginAsFullName==null) ||loginAsFullName.equalsIgnoreCase("") ) {
				
				if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
					Finops_ReassignedTo=(String) hashXlData.get("Finops_ReassignedTo_Code").trim();
				
				}else{
					Finops_ReassignedTo=(String) hashXlData.get("Finops_ReassignedTo").trim();				
				}

			} else {
			
				if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
					Finops_ReassignedTo=loginUser_code;

				}else{
					Finops_ReassignedTo=loginAsFullName;

				}
			}
		

			
			
			
			
			
			
			
			fnpProcessAI_ISR("WOISR_FinopActionItemTable", "WOISR_FinopActionItemTable_HeaderRow", (String) hashXlData.get("Finops_Action_item_name"),
					"Inprocess", "WOISR_FinopActionItemSaveNCloseBtn",Finops_ReassignedTo);
			fnpProcessAI_ISR("WOISR_FinopActionItemTable", "WOISR_FinopActionItemTable_HeaderRow", (String) hashXlData.get("Finops_Action_item_name"), 
					"Completed", "WOISR_FinopActionItemSaveNCloseBtn",Finops_ReassignedTo);
			
			//IPM-14359
			//fnpClick_OR("MoveToInProcessBtn");
			if (fnpCheckElementDisplayedImmediately("MoveToInProcessBtn")) {
				fnpMymsg("'Move To InProcess' button is  visible, so clicking it.");
				fnpClick_OR("MoveToInProcessBtn");
			}else{
				fnpMymsg("'Move To InProcess' button is not visible, so moving forward without looking for it.");
			}
			
			
			fnpWaitTillClickable("NewlyCrWOTopBannerInfo");
			fnpGetORObjectX("NewlyCrWOTopBannerInfo").click();
	
			fnpWaitForVisible("TopBannerWOStatus");
			String changedWOStatus = fnpGetORObjectX("TopBannerWOStatus").getText();
			Assert.assertTrue(changedWOStatus.equalsIgnoreCase("INPROCESS"), workOrderName+" WO status is not changed from INREVIEW to INPROCESS.");
			fnpMymsg("Now "+workOrderName+"  WO  status has been changed from 'INREVIEW' to  INPROCESS.");
			
			
			
			
		//	fnpClick_OR("MoveToInProcessBtn");
			
			
	} catch (Throwable t) {
		
		fnpCommonFinalCatchBlock(t, "  Financial_Tab_Corporate flow  is fail . ", "Financial_Tab_CorporateFail");
	
	}

	}
	
	@Test(priority = 4, dependsOnMethods = { "Financial_Tab_Corporate" })
	public void Second_CreateWOFlow_forAssociatedFacility() throws Throwable {

		try {
			hashXlData.clear();
			fnpLoadHashData(hashXlData, ISR_suitexls, classNameText, 2, 4);
			
			hashXlData.put("FirstWoNo", FirstWoNo);
			
			workOrderName="Associate";

			ISR_BS01.CreateWOFlow();
			start = Originalstart;

			String workOrderNo_text = fnpGetORObjectX("NewlyCreatedWorkOrderNo").getText();
			SecondWoNo = ((workOrderNo_text.split(" "))[0]).trim();
			fnpMymsg(" Second Work Order No is:" + SecondWoNo);

		} catch (Throwable t) {
					
			fnpCommonFinalCatchBlock(t, "  Second_CreateWOFlow flow  is fail . ", "Second_CreateWOFlowFail");
		
		}
				
	}
	
	@Test(priority = 5, dependsOnMethods = { "Second_CreateWOFlow_forAssociatedFacility" })
	public void Associate_Standard_Facility_Tab() throws Throwable {

		ISR_BS01.Standard_Facility_Tab();

	}

	@Test(priority = 6, dependsOnMethods = { "Associate_Standard_Facility_Tab" })
	public void associate_Financial_Tab() throws Throwable {
		try{
			Financial_Tab_Corporate();
				
		
	} catch (Throwable t) {
		
		fnpCommonFinalCatchBlock(t, "  associate_Financial_Tab flow  is fail . ", "associate_Financial_TabFail");

	}
		

	}
	
	
	@Test(priority = 7, dependsOnMethods = { "associate_Financial_Tab" })
	public void corporate_WO_AddSingleWO() throws Throwable {
		try{
			fnpClick_OR("StandardNFacilityTabLink");
			
			if (standardFacilityTab_WOSI_Panel_present) {
				fnpMymsg("Accordian present for this 'Work Order Standard Information'.");
				fnpClick_OR("StandardFacilityTab_WorkOrderStandardInformationPanel_expandIcon");
			} else {
				fnpMymsg("No accordian present for this 'Work Order Standard Information'.");
			}
			
			
			
			String currentWorkOrderNo_text = fnpGetORObjectX("NewlyCreatedWorkOrderNo").getText();
			String currentWorkOrderNo = ((currentWorkOrderNo_text.split(" "))[0]).trim();
			
			Assert.assertEquals(currentWorkOrderNo, SecondWoNo, "Something went wrong as either assocaite work order no. '"+SecondWoNo+"'  is not clicked properly or any other error occured. ");
			
			
			String xpathOfCorporateWOInFacilityRelationshipTable=OR.getProperty("CorporateWOLinkInFacilityRelationshipTableOfAssociateWO_part1")+FirstWoNo+OR.getProperty("CorporateWOLinkInFacilityRelationshipTableOfAssociateWO_part2");
			fnpClick_NOR(xpathOfCorporateWOInFacilityRelationshipTable);
			
			fnpClick_OR("EditWOBtn");
			fnpClick_OR("StandardNFacilityTabLink");
			
			if (standardFacilityTab_WOSI_Panel_present) {
				fnpMymsg("Accordian present for this 'Work Order Standard Information'.");
				fnpClick_OR("StandardFacilityTab_WorkOrderStandardInformationPanel_expandIcon");
			} else {
				fnpMymsg("No accordian present for this 'Work Order Standard Information'.");
			}
			
			fnpLoadHashData(hashXlData, ISR_suitexls, classNameText, 2, 3);	
			
			String textMessage;
			int associateSingleWORowNo=0;
			int woTypeColNo = fnpFindColumnIndex("AssociateFacilityTable_Header", "WO Type");
			//int associateSingleWORowNo=fnpFindRow_usingForPagingToNotThrowErrorIfNotFoundInCurrentPage("AssociateFacilityTable_Data", "Single", woTypeColNo);
			
			int facilityColNoOutside = fnpFindColumnIndex("AssociateFacilityTable_Header", "Facility #");
			int associatedFacilityRowNo=fnpFindRow_usingForPagingToNotThrowErrorIfNotFoundInCurrentPage("AssociateFacilityTable_Data", (String) hashXlData.get("Facility_No_for_singleWO"), facilityColNoOutside);
			
			if (associatedFacilityRowNo>0) {
				String woTypeForGivenFacility=fnpFetchFromTable("AssociateFacilityTable_Data", associatedFacilityRowNo, woTypeColNo);
				if (woTypeForGivenFacility.equalsIgnoreCase("Single")) {					
					associateSingleWORowNo=associatedFacilityRowNo;
				} else {
					textMessage="WO type is not single for this  facility '"+(String) hashXlData.get("Facility_No_for_singleWO")+"'. ";
					fnpMymsg(textMessage);
					fnpDisplayingMessageInFrame(textMessage, 10);
					throw new Exception(textMessage);
				}
			}
			
			
			
			


			
			if (associateSingleWORowNo>0) {
				// already associated so not going to associate again
				textMessage="Single WO of this facility'"+(String) hashXlData.get("Facility_No_for_singleWO").trim()+"' is already associated to corporate, so not going to associate again.";
				fnpMymsg(textMessage);
				fnpDisplayingMessageInFrame(textMessage, 10);
			} else {
				textMessage="Single WO  is NOT already associated to Corporate, so  going to associate it.";
				fnpMymsg(textMessage);
				fnpDisplayingMessageInFrame(textMessage, 10);

				fnpClick_OR("AddFacilityBtn");
								
				woTypeColNo=fnpFindColumnIndex("AddFacilityTableInPopupNotInTabView_Header", "WO Type");
			//	int rowNo=fnpFindRow_usingForPagingToNotThrowErrorIfNotFoundInCurrentPage("AddFacilityTableInPopupNotInTabView_Data", "Single", woTypeColNo);
				
				int facilityColNoInside = fnpFindColumnIndex("AddFacilityTableInPopupNotInTabView_Header", "Facility#");// in popup its name is 'Facility#' but in main table i.e. outside it is 'Facility #'
				associatedFacilityRowNo=fnpFindRow_usingForPagingToNotThrowErrorIfNotFoundInCurrentPage("AddFacilityTableInPopupNotInTabView_Data", (String) hashXlData.get("Facility_No_for_singleWO"), facilityColNoInside);
				if (associatedFacilityRowNo>0) {
					fnpMymsg("Single wo on this facility "+((String) hashXlData.get("Facility_No_for_singleWO"))+" is present inside the table in dialog/popup.");
				}else{
					msg="Single wo on this facility "+((String) hashXlData.get("Facility_No_for_singleWO"))+" is NOT present inside the table in dialog/popup.";
					fnpMymsg(msg);
					throw new Exception(msg);
				}
				
				
				
				String woTypeForGivenFacility = fnpFetchFromTable("AddFacilityTableInPopupNotInTabView_Data", associatedFacilityRowNo, woTypeColNo);
				if (woTypeForGivenFacility.equalsIgnoreCase("Single")) {
					associateSingleWORowNo=associatedFacilityRowNo;
				} else {
					//nothing to do
				}
				
/*				
				if (associateSingleWORowNo<1) {
					textMessage="Single Work Order  is not present/visible.";
					fnpMymsg(textMessage);
					fnpDisplayingMessageInFrame(textMessage, 10);
					throw new Exception(textMessage);
					
				} else {
					//nothing to do...can use later
				}
	*/			
				
				
				
				String xpathOfCheckBoxForSingleWO=OR.getProperty("CheckBoxXpathOfAddFacilityTableInPopupNotInTabView_part1")+(associateSingleWORowNo)+OR.getProperty("CheckBoxXpathOfAddFacilityTableInPopupNotInTabView_part2");
				fnpClick_NOR(xpathOfCheckBoxForSingleWO);
				
				fnpClick_OR("SelectAndReturnBtn_inAddFacilityPopUpNotInTabView");
				
				
				

				
				woTypeColNo = fnpFindColumnIndex("AssociateFacilityTable_Header", "WO Type");					
				//facilityColNoOutside = fnpFindColumnIndex("AssociateFacilityTable_Header", "Facility #");
				
				int whileCounter=0;
				while(whileCounter<60){
					Thread.sleep(1000);
					whileCounter++;
					fnpMymsg("In looping for waiting for adding facility successfully for chance ---"+whileCounter);
					associatedFacilityRowNo=fnpFindRow_usingForPagingToNotThrowErrorIfNotFoundInCurrentPage("AssociateFacilityTable_Data", (String) hashXlData.get("Facility_No_for_singleWO"), facilityColNoOutside);
					if (associatedFacilityRowNo>0) {
						break;
					}
					
				}
				
				
				
				associatedFacilityRowNo=fnpFindRow_usingForPagingToNotThrowErrorIfNotFoundInCurrentPage("AssociateFacilityTable_Data", (String) hashXlData.get("Facility_No_for_singleWO"), facilityColNoOutside);
				try{
					woTypeForGivenFacility=fnpFetchFromTable("AssociateFacilityTable_Data", associatedFacilityRowNo, woTypeColNo);
					if (woTypeForGivenFacility.equalsIgnoreCase("Single")) {
						textMessage="Single WO of this facility'"+(String) hashXlData.get("Facility_No_for_singleWO").trim()+"' is  associated successfully to Corporate.";
						fnpMymsg(textMessage);
						fnpDisplayingMessageInFrame(textMessage, 10);
					} else {
						textMessage="Single WO  is  NOT associated successfully to Corporate.";
						fnpMymsg(textMessage);
						fnpDisplayingMessageInFrame(textMessage, 10);
						throw new Exception(textMessage);
					}
					
				}catch(Throwable t){
					textMessage="Single WO  is  NOT associated successfully to Corporate. Error is ---"+t.getMessage();
					fnpMymsg(textMessage);
					fnpDisplayingMessageInFrame(textMessage, 10);
					throw new Exception(textMessage);
				}
	
			}
			

			
			
			

			
			
		} catch (Throwable t) {
			
			fnpCommonFinalCatchBlock(t, "  corporate_WO_MoveToInReview_And_AddSingleWO flow  is fail . ", "corporate_WO_MoveToInReview_And_AddSingleWOFail");

		}
		
	}
	
	@Test(priority = 8, dependsOnMethods = { "corporate_WO_AddSingleWO" })
	public void CLEANUP_WO_AUTOMATION_DATA() throws Throwable {
		fnpMymsg(" **************************************************************");
		fnpMymsg("ECAP.CLEANUP_WO_AUTOMATION_DATA");
		try {
			if (env.equalsIgnoreCase("Test") ) {
			//	fnpCallProcedure_DELETE_WO_AUTOMATION_DATA_ISRProfile(FirstWoNo);
			//	fnpCallProcedure_DELETE_WO_AUTOMATION_DATA_ISRProfile(SecondWoNo);
			}else{
				//throw new SkipException( " \n\n Skipping this as this procedure is only available in TEST environment" );
			}

		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "  CLEANUP_WO_AUTOMATION_DATA  method is failed . ", "CLEANUP_WO_AUTOMATION_DATA_Failed");
		}

	}

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