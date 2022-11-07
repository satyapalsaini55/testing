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
import nsf.ecap.util.TestUtil;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Uploading_Downloading_Docs extends TestSuiteBase_Generic_suite {


	
	
	@BeforeTest
	public void checkTestSkip() throws Throwable {
		BS01 = new NewNew_Draft_InReview_No_Fac();
		BS01.checkTestSkip(this.getClass().getSimpleName());
		

	}
	
	
	
	

	
	
	
	//@Test(enabled = false)
	@Test(priority = 1)
	public void UploadDownloadFlow() throws Throwable {
		
		start = new Date();
		try{
			
				fnpLaunchBrowserAndLogin();
	
				HashMap<String, String> copyFileNameHashMap = new HashMap();
				copyFileNameHashMap=fnpCopyFile_Save_Somewhere_AppendWithCurrentDate((String) hashXlData.get("Upload_fileName").trim());
				String copyFileName_withCompletePath=copyFileNameHashMap.get("NameWithCompletePath").trim();
				String copyFileNameOnly=copyFileNameHashMap.get("FileNameOnly").trim();

				
				
				
				

				
				
				/**********AI screen

								Upload/View document **********************************************/
				
				String aiTypes=(String) hashXlData.get("AI_Type").trim();
				String[] aiTypesArray = aiTypes.split("::");
				
				String aiStatus=(String) hashXlData.get("AI_Status").trim();
				String[] aiStatusArray = aiStatus.split(",");
				
				String status;
				String[] singleSelectingCriteria ;
				String aiName = null;
				for (int m1 = 0; m1 < aiTypesArray.length; m1++) {
					//fnpCommonClickTopMainMenu("Menu", "FirstRefrenceLinkJustBelowToMenu", "SearchActionItemsLink", "AITypeMultipleSelectDropDown");
					fnpCommonClickTopMainMenu("Menu", "FirstRefrenceLinkJustBelowToMenu", "SearchActionItemsLink", "SearchActionItemScreen_AIType_LKPBtn_id");
					
					String completeAI_withSelectingCriteria=aiTypesArray[m1].trim();
					status=aiStatusArray[m1].trim();
					
					
					
					singleSelectingCriteria = completeAI_withSelectingCriteria.split(",");
					for (int i = 0; i < singleSelectingCriteria.length; i++) {
						
						//if (singleSelectingCriteria[i].contains("Type Name")) {
						if (singleSelectingCriteria[i].contains("Type Description")) {
							
							aiName=fnpremoveFormatting(singleSelectingCriteria[i].split(":")[2]).trim();
							break;
						}
					}
/*					
					String completeAI_WithStatus=aiTypesArray[m1].trim();
					String[] completeAI_WithStatus_Array = completeAI_WithStatus.split(",");
							
					String ai_name = fnpremoveFormatting(completeAI_WithStatus_Array[0]);
					fnpMymsg("AI name is--" + ai_name);
					
					String aiStatus = fnpremoveFormatting(completeAI_WithStatus_Array[1]);
					fnpMymsg("AI status is--" + aiStatus);
					
					fnpMymsg("***********************Start ******Action Item ==>"+ai_name+"*************");
					//fnpMultipleSelectDropDown3("AITypeMultipleSelectDropDown",ai_name );
					
					*/
					
					fnpMymsg("AI name is--" + aiName);
					fnpMymsg("AI status is--" + status);
					
					fnpMymsg("***********************Start ******Action Item ==>"+aiName+"  and status is '"+status+"'   *************");
					
					fnpClick_OR("SearchActionItemScreen_AIType_LKPBtn_id");
				//	fnpSearchNSelectFirstRadioBtn(1, (String) hashXlData.get("Client"), 1);
					
					//String completeSearchCriteria="(Type Name:Starts With:TechnicalReview),(Business Profile:Starts With:ISR_Profile)";
					fnpSearchNSelectFirstRadioBtn( completeAI_withSelectingCriteria);
					
					
					//String aiStatus=(String) hashXlData.get("AI_Status").trim();
					
/*					
					if (ai_name .equalsIgnoreCase(cerDecisionAIName)) {
						aiStatus="Inprocess";
					}
					
					*/
					
					//fnpMultipleSelectDropDown3("AIStatusMultipleSelectDropDown", aiStatus);
					
/*					
					fnpClick_OR("SearchActionItemScreen_AIStatus_LKPBtn_id");
					fnpSearchNSelectFirstRadioBtn(1, status, 1);
					*/
					
					
					fnpPFList("SearchActionItemScreen_AIStatus_List", "SearchActionItemScreen_AIStatus_ListOptions", status);
					
/*					if (aiName .equalsIgnoreCase(transferReviewAIName)) {
						fnpType("OR", "SearchActionItemScreen_ItemNameTxtBx", aiName);
					}
					*/
					
/*					
					//new changes for tech review
					if (ai_name .equalsIgnoreCase(technicalReviewAIName)) {
						
						fnpClick_OR("SearchAIScreen_AIAssignedTo_lkp_btn");
						//fnpSearchNSelectFirstRadioBtn(1, "117817", 1);
						
						String techReviewerCode_inExcel=(String) hashXlData.get("Technical_Review_AI_Reviewer_Code").trim();
						fnpSearchNSelectFirstRadioBtn(1, techReviewerCode_inExcel, 1);						
						String expectedUser=fnpGetUser_fullName(techReviewerCode_inExcel.trim());						
						String actual_aiAssignedTo = fnpWaitTillTextBoxDontHaveValue("SearchAIScreen_AIAssignedTo_txt_bx", "value");
						//Assert.assertTrue(actual_aiAssignedTo.equalsIgnoreCase(expectedUser), "'AI Assigned To' is not selected properly from lookup");
												
						if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){						
							Assert.assertTrue(actual_aiAssignedTo.contains(techReviewerCode_inExcel), "'AI Assigned To' is not selected properly from lookup because actual value is ---'"+actual_aiAssignedTo+"' and expected is --'"+techReviewerCode_inExcel+"'.");
						}else{
							Assert.assertTrue(actual_aiAssignedTo.contains(expectedUser), "'AI Assigned To' is not selected properly from lookup because actual value is ---'"+actual_aiAssignedTo+"' and expected is --'"+expectedUser+"'.");
						}												
						fnpMymsg(" 'AI Assigned To' value is properly selected from  lookup");
						Thread.sleep(100);						
					}
				*/	
					
					if (aiName .equalsIgnoreCase(technicalReviewAIName)) {
						//fnpSelectingValueInOrgUnit_SearchAI_InIpulse("ISR");
						fnpSelectingValueInOrgUnit_SearchAI_InIpulse("ISR - NSF International Strategic Registrations, Ltd.");
						
						
					}
					
					
					fnpClick_OR("MainSearchButton");
			
					String s = fnpFetchFromStSearchTable(1, 1);
					int j = 0;
					// fnpLoading_wait();
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
						int aiCol = fnpFindColumnIndex("StandardSearchTableHeader", "AI No");
						s = fnpFetchFromStSearchTable(1, aiCol);
					}
			
					
					/*-------Verify All records with given status--------*/
					
			        int  aiStatusColNo=fnpFindColumnIndex("StandardSearchTableHeader", "AI Status");
			        int totalSearchRows=fnpCountRowsInTable("StandardSearchTable");
			        String tempStatus=null;
			        for (int i = 0; i <totalSearchRows; i++) {
			        	tempStatus=fnpFetchFromTable("StandardSearchTable", (i+1), aiStatusColNo).trim();

			        	if (tempStatus.equalsIgnoreCase(status)) {
			        		fnpMymsg("This row '"+(i+1)+"' has AI Status '"+tempStatus+"', hence it is correct.");
							
						} else {
							msg="This row '"+(i+1)+"' has AI Status '"+tempStatus+"' but it should be '"+status+"', hence it is wrong.";
							fnpMymsg(msg);
							throw new Exception(msg);
						}
						
					}
			        
					
					
					fnpMymsg("First hyperlink ai no. is --"+s);
					
/*					if (!(ai_name .equalsIgnoreCase(technicalReviewAIName)) ){
						fnpClickALinkInATable(s);						
						fnpLoading_wait_specialCase(15);
					}

					*/
					
					
					fnpClickALinkInATable(s);						
					//fnpLoading_wait_specialCase(15);
					fnpLoading_wait_CheckLoadingIconOnlyIniPulse();

					if (aiName .equalsIgnoreCase(cerDecisionAIName)) {
						//This Edit button is only present in Cert Decision Action item
						//if (fnpCheckElementDisplayedImmediately("WOISR_AIEditBtn_OnConsolidatedScreen")) {
						if (fnpCheckElementDisplayed("WOISR_AIEditBtn_OnConsolidatedScreen",60)) {
							fnpClick_OR("WOISR_AIEditBtn_OnConsolidatedScreen");
						}
					}
					
					
					//new changes which is postponed to Nov build 2020
					if (aiName .equalsIgnoreCase(technicalReviewAIName)) {	
						
						fnpWaitForVisible("ReassignAILkpBtn_OnConsolidatedScreen");
						String alreadyAssingee=fnpGetORObjectX("AssignedToLabelValue_OnConsolidatedScreen").getText().trim();				
						String reassignee=null;
						
						if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
							reassignee=(String) hashXlData.get("TechReviewer_Code").trim();
						}else{
							reassignee=(String) hashXlData.get("TechReviewer_fullName").trim();
						}
						
						
						if (!(alreadyAssingee.contains(reassignee))) {	
							String defaultValue = fnpGetORObjectX("ReassignAITxtBx_OnConsolidatedScreen").getAttribute("value").trim();
							//if (defaultValue.equalsIgnoreCase(reassignee)) {
							if (defaultValue.contains(reassignee)) {
								fnpMymsg("@  - default value is same as expected, so returning back.");
							} else {					
									fnpClick_OR("ReassignAILkpBtn_OnConsolidatedScreen");
				
									fnpMymsg("Just after  click ReassignAILkpBtn");
									fnpMymsg("Just before going to insert value of Account Manger");
									//fnpSearchNSelectFirstRadioBtn(2, reassignee, 1);
									if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
										fnpSearchNSelectFirstRadioBtn(1, reassignee, 1);
										fnpMymsg("Debug: 6");
									}else{
										fnpSearchNSelectFirstRadioBtn(2, reassignee, 1);
										fnpMymsg("Debug: 7");
									}
									fnpMymsg("Just after  inserting value of Account Manger");

									String EnteredAccountManager = fnpWaitTillTextBoxDontHaveValue("ReassignAITxtBx_OnConsolidatedScreen", "value");
									Assert.assertTrue(EnteredAccountManager.contains(reassignee), "Reassignee is not selected properly from lookup");
									fnpMymsg(" Reassignee is properly selected from client lookup");
							}

							fnpGetORObjectX("SaveButton__OnConsolidatedScreen_TechnicalReviewAI").click();
							fnpLoading_wait();

						}			
					//}
						
					fnpSwitchUser((String) hashXlData.get("TechReviewer_UserName").trim());	
					fnpSearchActionItemsOnly(s);	
						
					if (fnpCheckElementPresenceImmediately("EditBtn")) {
						fnpClick_OR("EditBtn");
					} 
						
						
						
						
						
						
/*						
					fnpFindAIUser_SwitchUser_N_SearchAI(s);
					fnpClick_OR("EditBtn");
					*/
					
					
					
					
					fnpClick_OR("AddNewNotesandDocuments_heading_expandIcon");
					}
					
					
					//added on 09-07-2021
					if ((aiName .equalsIgnoreCase(transferReviewAIName))) {
							// here we are assignin it to Testscriptuser and it is our login user also
							fnpActionItemReassignedTo_normalAI(loginAsFullName, loginUser_code,"Login_user");
						}			
						
					
					
					
					
					fnpScrollToElement(fnpGetORObjectX("DocTypeInAI_PFList"));
					fnpScroll(0, 300);
					fnpClick_OR("AddAIDocuments_heading");
					fnpPFListByLiNo("DocTypeInAI_PFList", "DocTypeInAI_PFListOptions", 1);
					
/*					String fileName2 = System.getProperty("user.dir") + "\\docs\\" + (String) hashXlData.get("Upload_fileName");
					driver.findElement(By.xpath(OR.getProperty("DocUploadBrowseBtn"))).sendKeys(fileName2);
					*/
					driver.findElement(By.xpath(OR.getProperty("DocUploadBrowseBtn"))).sendKeys(copyFileName_withCompletePath);
					
					fnpBrowseLoading();
					
					
					Date todayDate=new Date();
					SimpleDateFormat sdfmt1 = new SimpleDateFormat("dd-MMM-yyyy");
					String todaydateInStringformat = sdfmt1.format(todayDate);
					if (fnpCheckElementDisplayedImmediately("TransferReviewAI_lastListedDateTxtBx")) {
						fnpMymsg("Last Listed Date field is present in this action item.");
						String  lastListedDate=fnpGetAttribute_OR("TransferReviewAI_lastListedDateTxtBx", "value").trim();
						if (lastListedDate.equalsIgnoreCase("")) {
							fnpMymsg("Last Listed Date is not present there, so going to pick value using calendar.");
							fnpClick_OR_WithoutWait("TransferReviewAI_lastListedDateCalendarIcon");
							Thread.sleep(2000);			
							//fnpCalendarDatePicker_BySelectValues_From_NextAndBack(todaydateInStringformat);
							fnpCalendarDatePicker_BySelectValues_From_DropDown2(todaydateInStringformat,
									OR.getProperty("calendarDatePickerSelectYear_xpath"), OR.getProperty("calendarDatePickerSelectMonth_xpath"));
						}else{
							fnpMymsg("Last Listed Date is already present there.");
						}
						
					}
					

					if (fnpCheckElementDisplayedImmediately("TransferReviewAI_CertExpirationDateTxtBx")) {
						fnpMymsg("Cert Expiration Date field is present in this action item.");
						String  certExpDate=fnpGetAttribute_OR("TransferReviewAI_CertExpirationDateTxtBx", "value").trim();
						if (certExpDate.equalsIgnoreCase("")) {
							fnpMymsg("Cert Expiration Date is not present there, so going to pick value using calendar.");
							fnpClick_OR_WithoutWait("TransferReviewAI_CertExpirationDateCalendarIcon");
							Thread.sleep(2000);	
							fnpCalendarDatePicker_BySelectValues_From_DropDown2(todaydateInStringformat,
									OR.getProperty("calendarDatePickerSelectYear_xpath"), OR.getProperty("calendarDatePickerSelectMonth_xpath"));
						}else{
							fnpMymsg("Cert Expiration Date is already present there.");
						}
						
					}
					

					
					switch (aiName) {
					case cerDecisionAIName:
						fnpClick_OR("SaveBtnOnCertDecisionAIConsolidatedScreen");
						break;
					
		
					case technicalReviewAIName:
						//new changes which is postponed to Nov build 2020
						fnpClick_OR("WO_Save_btn");
						//fnpClick_OR("SaveButton__OnConsolidatedScreen");
						break;
						
					case transferReviewAIName:
						//new changes which came to me on 25-06-2021 on Teams meeting with Ruchi
						fnpClick_OR("SaveBtnOnTransferReviewAIScreen");

						break;
						
						
						
		
					default:
						fnpClick_OR("SaveButton__OnConsolidatedScreen");
		
					}
				
					
					
					
					
					
					
					fnpWaitForVisibleHavingMultipleElements("TopMessage3");
					String SuccessfulMsg = fnpGetText_OR("TopMessage3");
					fnpMymsg("Top Message after uploading documents ----" + SuccessfulMsg);

					Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"),
							"Top message does not contain 'success' word, while uploading the docs.");

					
					
					if ((aiName .equalsIgnoreCase(transferReviewAIName))) {
						// 
/*						
						fnpScrollToElement( fnpGetORObjectX( "TransferReviewAI_ItemNotesAndDocuments_dataTable"));
						fnpScroll( 500, 200);
						
						int documentsColNo = fnpFindColumnIndex2("TransferReviewAI_ItemNotesAndDocuments_headerTable", "Documents and Description");
					
						
						String xpathForFirstRowFirstdoclink_xpath=OR.getProperty("TransferReviewAI_ItemNotesAndDocuments_dataTable")+"/tr[1]/td["+documentsColNo+"]/a[1]";
						//List<WebElement> firstRowsDocsLinks = fnpGetORObject_list_NOR(xpathForFirstRowFirstdoclink_xpath, 2);
								
						fnpCheckFileIsDownloadedOrNotIniPulse(xpathForFirstRowFirstdoclink_xpath) ;
						
						*/
						
						
						
					}else{
						fnpScrollToElement( fnpGetORObjectX( "ItemNotesAndDocuments_dataTable"));
						fnpScroll( 500, 200);
						
						int documentsColNo = fnpFindColumnIndex2("ItemNotesAndDocuments_headerTable", "Documents and Description");
					
						
						String xpathForFirstRowFirstdoclink_xpath=OR.getProperty("ItemNotesAndDocuments_dataTable")+"/tr[1]/td["+documentsColNo+"]/a[1]";
						//List<WebElement> firstRowsDocsLinks = fnpGetORObject_list_NOR(xpathForFirstRowFirstdoclink_xpath, 2);
								
						fnpCheckFileIsDownloadedOrNotIniPulse(xpathForFirstRowFirstdoclink_xpath) ;
					}
						fnpCheckError("");
						fnpMymsg("***********************End ******Action Item ==>"+aiName+"*************");	
						
							
						
						/**********************************/
						fnpCommonCode_ClickWOFromItemNotesAndDocumentsTable_GoToDocumentTab_AddDoc_Upload_download_GoToTaskTab_clickTaskNo_GoToDocumentTab_AddDoc_Upload_download(aiName,copyFileName_withCompletePath,copyFileNameOnly);
						/***************************************/
						
					//}
					
					
	
						if (aiName .equalsIgnoreCase(technicalReviewAIName)) {
							driver.quit();
							fnpLaunchBrowserAndLogin();
						}

					
					
				}
				/***************************************************************************/
				
				
				
				
				
				
				/****GFSI*******************************************************/
				Hashtable<String, String> ht1 = new Hashtable<String,String>();
				ht1.putAll(hashXlData);
				
				String GFSIAlertNames1=(String) hashXlData.get("GFSI_Alerts").trim();
				String[] alertArray1 = GFSIAlertNames1.split("::");
				String alertName1;
				
				for (int m1 = 0; m1 < alertArray1.length; m1++) {
						
						fnpClickTopHomeMenu();
						fnpClick_OR("Alert_For_OrgUnit_label");
						fnpPFList("Alert_For_SelectOrgUnit_List", "Alert_For_SelectOrgUnit_ListOptions", (String) hashXlData.get("GFSI_name"));
						fnpClick_OR("ShowAlertsBtn");
						
						
						

						
						alertName1=alertArray1[m1];
						switch (alertName1) {
			
						case "PrePlan Action item(s) in Created Status":
							
							TestSuiteBase_HealthScience.fnpCommonAlertClickedFirstRecord_in_specifiedColumnName_withoutSearching(ht1, alertName1, "PrePlan_Action_item_in_Created_Status_AlertTable_header",
									"Item", "PrePlan_Action_item_in_Created_Status_AlertTable");
							
							
							fnpClick_OR("ViewActionItem_SendEmail_button");
							
							//fnpPFList("ISR_EmailTemplate_PFList", "ISR_EmailTemplate_PFListOptions", hashXlData.get("Email_Template"));
							fnpPFListByLiNo("ISR_EmailTemplate_PFList", "ISR_EmailTemplate_PFListOptions", 1);
							fnpClick_OR("ISR_EmailTemplateContinueBtn");
							fnpWaitForVisible("ISR_EmailToTxt");
							fnpGetORObjectX("ISR_EmailFromTxt").clear();
							fnpType("OR","ISR_EmailFromTxt",hashXlData.get("From_emailCorrespondence") );
							fnpGetORObjectX("ISR_EmailToTxt").clear();
							fnpType("OR","ISR_EmailToTxt",hashXlData.get("To_emailCorrespondence") );
							String cc=fnpGetAttribute_OR("ISR_EmailCCTxt", "value").trim();
							if (cc.equalsIgnoreCase("")) {
								//nothing to do for now
							}else{
								fnpGetORObjectX("ISR_EmailCCTxt").clear();
							}
							
							
							
							//fnpScroll(0, 500) ;
							fnpMouseHover("ISR_CorresEmailSendBtn");
							
/*							WebElement wb=fnpGetORObjectX_withoutCheckingElementClickable("ISR_CorresEmailSendBtn");
							JavascriptExecutor executor = (JavascriptExecutor)driver;
							executor.executeScript("arguments[0].click();", wb);
							fnpLoading_waitInsideDialogBox();
							fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
							*/
							fnpClickInDialog_OR("ISR_CorresEmailSendBtn");
							
							
							fnpClick_OR("AddAIDocuments_heading");
							
							fnpScroll(0, 500);
							fnpPFListByLiNo("DocTypeInAI_PFList", "DocTypeInAI_PFListOptions", 1);
							fnpClick_OR("AddAIDocuments_heading");
							Thread.sleep(2000);
							
							//fnpPFListByLiNo("DocTypeInAI_PFList", "DocTypeInAI_PFListOptions", 1);
			
							driver.findElement(By.xpath(OR.getProperty("DocUploadBrowseBtn"))).sendKeys(copyFileName_withCompletePath);
							fnpBrowseLoading();
							break;
							
							
						case "Preplanning Record Verification Action Item Follow up Required":
							
							
							TestSuiteBase_HealthScience.fnpCommonAlertClickedFirstRecord_in_specifiedColumnName_withoutSearching(ht1, alertName1, "Preplanning_Record_Verification_Action_Item_Follow_up_Required_AlertTable_header",
									"Item", "Preplanning_Record_Verification_Action_Item_Follow_up_Required_AlertTable");
							
							
							TestSuiteBase_HealthScience.fnpCommonAlertClickedFirstRecord_in_specifiedColumnName_withoutSearching_BasedOnAlertNameContains(ht1, alertName1,  "Item");
							
							
							fnpClick_OR("ViewActionItem_SendEmail_button");
							
							//fnpPFList("ISR_EmailTemplate_PFList", "ISR_EmailTemplate_PFListOptions", hashXlData.get("Email_Template"));
							fnpPFListByLiNo("ISR_EmailTemplate_PFList", "ISR_EmailTemplate_PFListOptions", 1);
							fnpClick_OR("ISR_EmailTemplateContinueBtn");
							fnpWaitForVisible("ISR_EmailToTxt");
							fnpGetORObjectX("ISR_EmailFromTxt").clear();
							fnpType("OR","ISR_EmailFromTxt",hashXlData.get("From_emailCorrespondence") );
							fnpGetORObjectX("ISR_EmailToTxt").clear();
							fnpType("OR","ISR_EmailToTxt",hashXlData.get("To_emailCorrespondence") );
							 cc=fnpGetAttribute_OR("ISR_EmailCCTxt", "value").trim();
							if (cc.equalsIgnoreCase("")) {
								//nothing to do for now
							}else{
								fnpGetORObjectX("ISR_EmailCCTxt").clear();
							}
							
							
							//fnpScroll(0, 500) ;
							fnpMouseHover("ISR_CorresEmailSendBtn");
							
/*							 wb=fnpGetORObjectX_withoutCheckingElementClickable("ISR_CorresEmailSendBtn");
							 executor = (JavascriptExecutor)driver;
							executor.executeScript("arguments[0].click();", wb);
							fnpLoading_waitInsideDialogBox();
							fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
							*/
							
							
							fnpClickInDialog_OR("ISR_CorresEmailSendBtn");
							
							
							fnpClick_OR("AddAIDocuments_heading");
							
							fnpScroll(0, 500);
							fnpPFListByLiNo("DocTypeInAI_PFList", "DocTypeInAI_PFListOptions", 1);
							fnpClick_OR("AddAIDocuments_heading");
							Thread.sleep(2000);
							
							
							//fnpPFListByLiNo("DocTypeInAI_PFList", "DocTypeInAI_PFListOptions", 1);
			
							driver.findElement(By.xpath(OR.getProperty("DocUploadBrowseBtn"))).sendKeys(copyFileName_withCompletePath);
							fnpBrowseLoading();
							break;	
							
							
							
						case "Cert issue Action item(s) assigned  in Created Status":
							TestSuiteBase_HealthScience.fnpCommonAlertClickedFirstRecord_in_specifiedColumnName_withoutSearching(ht1, alertName1, "Cert_issue_Action_item_assigned_in_created_Status_AlertNameTable_header",
									"Item", "Cert_issue_Action_item_assigned_in_created_Status_AlertNameTable");
							
							fnpClick_OR("EmailCertBtn");
							fnpPFListByLiNo("ISR_EmailTemplate_PFList", "ISR_EmailTemplate_PFListOptions", 1);
							fnpClick_OR("ISR_EmailTemplateContinueBtn");
							fnpWaitForVisible("ISR_EmailToTxt");
							fnpGetORObjectX("ISR_EmailFromTxt").clear();
							fnpType("OR","ISR_EmailFromTxt",hashXlData.get("From_emailCorrespondence") );
							fnpGetORObjectX("ISR_EmailToTxt").clear();
							fnpType("OR","ISR_EmailToTxt",hashXlData.get("To_emailCorrespondence") );
							cc=fnpGetAttribute_OR("ISR_EmailCCTxt", "value").trim();
							if (cc.equalsIgnoreCase("")) {
								//nothing to do for now
							}else{
								fnpGetORObjectX("ISR_EmailCCTxt").clear();
							}
							
							//fnpScroll(0, 500) ;
							fnpMouseHover("ISR_CorresEmailSendBtn");
							
							
/*							 wb=fnpGetORObjectX_withoutCheckingElementClickable("ISR_CorresEmailSendBtn");
							 executor = (JavascriptExecutor)driver;
							executor.executeScript("arguments[0].click();", wb);
							fnpLoading_waitInsideDialogBox();
							fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
							
							*/
							
							fnpClickInDialog_OR("ISR_CorresEmailSendBtn");
							
							fnpClick_OR("AddAIDocuments_heading");
							
							fnpScroll(0, 500);
							fnpPFListByLiNo("DocType2InAI_PFList", "DocType2InAI_PFListOptions", 1);
							fnpClick_OR("AddAIDocuments_heading");
							Thread.sleep(2000);
			
							driver.findElement(By.xpath(OR.getProperty("DocUpload2BrowseBtn"))).sendKeys(copyFileName_withCompletePath);
							fnpBrowseLoading();
							break;
							
			
						default:
							loginAs=CONFIG.getProperty("Default").split(":")[1];
							loginAsFullName=CONFIG.getProperty("Default").split(":")[1];
							loginUser_code=CONFIG.getProperty("Default").split(":")[2];
			
							
						}


						

						
						fnpBrowseLoading();
						
						if (aiTypesArray[m1] .equalsIgnoreCase(cerDecisionAIName)) {
							fnpClick_OR("SaveBtnOnCertDecisionAIConsolidatedScreen");
						}else{
							fnpClick_OR("SaveButton__OnConsolidatedScreen");
						}
		
						fnpClick_OR("SaveButton__OnConsolidatedScreen");
						
						fnpWaitForVisibleHavingMultipleElements("TopMessage3");
						String SuccessfulMsg = fnpGetText_OR("TopMessage3");
						fnpMymsg("Top Message after uploading documents ----" + SuccessfulMsg);
						Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"),
								"Top message does not contain 'success' word, while uploading the docs.");
						
						
						int documentsColNo = fnpFindColumnIndex2("ItemNotesAndDocuments_headerTable", "Documents and Description");
					
						
						String xpathForFirstRowFirstdoclink_xpath=OR.getProperty("ItemNotesAndDocuments_dataTable")+"/tr[1]/td["+documentsColNo+"]/a[1]";
						//List<WebElement> firstRowsDocsLinks = fnpGetORObject_list_NOR(xpathForFirstRowFirstdoclink_xpath, 2);
								
						fnpCheckFileIsDownloadedOrNotIniPulse(xpathForFirstRowFirstdoclink_xpath) ;
						fnpCheckError("");
						//no delete doc icon here
					
						/**********************************/
						fnpCommonCode_ClickWOFromItemNotesAndDocumentsTable_GoToDocumentTab_AddDoc_Upload_download_GoToTaskTab_clickTaskNo_GoToDocumentTab_AddDoc_Upload_download(null,copyFileName_withCompletePath,copyFileNameOnly);
						/***************************************/
						
				}
				
				/****GFSI  End *******************************************************/
				
				
				
				
				/****Dietary Supplement*******************************************************/
				
				fnpClickTopHomeMenu();
				fnpClick_OR("Alert_For_OrgUnit_label");
				fnpPFList("Alert_For_SelectOrgUnit_List", "Alert_For_SelectOrgUnit_ListOptions", (String) hashXlData.get("DS_name"));
				fnpClick_OR("ShowAlertsBtn");
				
				
				alertName1=(String) hashXlData.get("Cert_issue_Action_item_in_Inprocess_Status_AlertName").trim();
				TestSuiteBase_HealthScience.fnpCommonAlertClickedFirstRecord_in_specifiedColumnName_withoutSearching(ht1, alertName1, "Cert_issue_Action_item_in_Inprocess_Status_AlertNameTable_header",
						"Item", "Cert_issue_Action_item_in_Inprocess_Status_AlertNameTable");
				
				
/*				fnpClick_OR("ViewActionItem_SendEmail_button");
				
				//fnpPFList("ISR_EmailTemplate_PFList", "ISR_EmailTemplate_PFListOptions", hashXlData.get("Email_Template"));
				fnpPFListByLiNo("ISR_EmailTemplate_PFList", "ISR_EmailTemplate_PFListOptions", 1);
				fnpClick_OR("ISR_EmailTemplateContinueBtn");
				fnpWaitForVisible("ISR_EmailToTxt");
				fnpGetORObjectX("ISR_EmailFromTxt").clear();
				fnpType("OR","ISR_EmailFromTxt",hashXlData.get("From_emailCorrespondence") );
				fnpGetORObjectX("ISR_EmailToTxt").clear();
				fnpType("OR","ISR_EmailToTxt",hashXlData.get("To_emailCorrespondence") );
				fnpClickInDialog_OR("ISR_CorresEmailSendBtn");
				*/
				fnpClick_OR("AddAIDocuments_heading");
				fnpPFListByLiNo("DocTypeInAI_PFList", "DocTypeInAI_PFListOptions", 1);

				driver.findElement(By.xpath(OR.getProperty("DocUploadBrowseBtn"))).sendKeys(copyFileName_withCompletePath);
				
				fnpBrowseLoading();
				
/*				if (aiTypesArray[m] .equalsIgnoreCase(cerDecisionAIName)) {
					fnpClick_OR("SaveBtnOnCertDecisionAIConsolidatedScreen");
				}else{
					fnpClick_OR("SaveButton__OnConsolidatedScreen");
				}
*/
				fnpClick_OR("SaveButton__OnConsolidatedScreen");
				
				fnpWaitForVisibleHavingMultipleElements("TopMessage3");
				 String SuccessfulMsg = fnpGetText_OR("TopMessage3");
				fnpMymsg("Top Message after uploading documents ----" + SuccessfulMsg);
				Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"),
						"Top message does not contain 'success' word, while uploading the docs.");
				
				
				int documentsColNo = fnpFindColumnIndex2("ItemNotesAndDocuments_headerTable", "Documents and Description");
			
				
				String xpathForFirstRowFirstdoclink_xpath = OR.getProperty("ItemNotesAndDocuments_dataTable")+"/tr[1]/td["+documentsColNo+"]/a[1]";
				//List<WebElement> firstRowsDocsLinks = fnpGetORObject_list_NOR(xpathForFirstRowFirstdoclink_xpath, 2);
						
				fnpCheckFileIsDownloadedOrNotIniPulse(xpathForFirstRowFirstdoclink_xpath) ;
				fnpCheckError("");
				
			
				/**********************************/
				fnpCommonCode_ClickWOFromItemNotesAndDocumentsTable_GoToDocumentTab_AddDoc_Upload_download_GoToTaskTab_clickTaskNo_GoToDocumentTab_AddDoc_Upload_download(null,copyFileName_withCompletePath,copyFileNameOnly);
				/***************************************/
				
				/****Dietary Supplement End*******************************************************/
				
		
				
				
				
				
				
				
				
				
				
				
			}catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "  UploadDownloadFlow flow  is fail . ", "UploadDownloadFlowFail");

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