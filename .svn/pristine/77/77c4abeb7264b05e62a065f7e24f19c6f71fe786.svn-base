package nsf.ecap.ISR_suite;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

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

public class ISO9001_Campus extends TestSuiteBase_ISR_suite {

	
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
	
	public String textMessage=null;
	String msg;
	
	
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
			start = Originalstart;

			
/*			fnpCommonLoginPart(classNameText);
			testing() ;
			*/
			
/*			//fnpDropWOISRandDeleteSomeDataFromDB("42172", "QMSI9K15");
			Thread.sleep(1000);
			fnpDropWOISRandDeleteSomeDataFromDB("42176", "QMSI9K15");
			Thread.sleep(1000);
			*/
			
			
/*			hashXlData.clear();
			fnpLoadHashData(hashXlData, Supply_Chain_and_ISR_suitexls, classNameText, 2, 3);
			boolean needToCreateSingleTillInProecss=callVerifySingleWOPresentOnThisStandardFacilityOtherwiseCreate((String) hashXlData.get("Facility_No_for_singleWO"), (String) hashXlData.get("Standard"));
			
			String singleWOTillInProcess;
			if (needToCreateSingleTillInProecss) {
				hashXlData.clear();
				fnpLoadHashData(hashXlData, Supply_Chain_and_ISR_suitexls, classNameText, 2, 4);
				String temp=classNameText;
				classNameText=IPULSE_CONSTANTS.WOISOSingleWOTillInProcessTestCaseName;
				singleWOTillInProcess=fnpCreateANewSingleWOTillInProcess();
				classNameText=temp;
				fnpMymsg("Created Single work order for Campus of this facility '"+(String) hashXlData.get("Facility_No_for_singleWO")+"' is ---"+singleWOTillInProcess);
				IsBrowserPresentAlready=true;
				
			}
			*/
			
			

			
			
			/*********** To create a single work order for campus**************/
			hashXlData.clear();
			fnpLoadHashData(hashXlData, ISR_suitexls, classNameText, 2, 3);
			
/*			*//************For time being to drop intentially (When Arun Jairaman create some finops on this facility..karen advised him not to use this facility [mail 06-06-2017 Subject random fin ops AI --comment it later *****************//*
			fnpDropWOISRandDeleteSomeDataFromDB_speciallyForCorporate((String) hashXlData.get("Facility_No_for_singleWO"), (String) hashXlData.get("Standard"));
			fnpDropWOISRandDeleteSomeDataFromDB((String) hashXlData.get("Facility_No_for_singleWO"), (String) hashXlData.get("Standard"));
			*//*************************************************************************************//*
			*/
			
			String statuses = "INPROCESS";
			String[] statusToCheck = statuses.split(",");
			boolean needToCreateSingleWO=callVerifySingleWOPresentOnThisStandardFacilityOtherwiseCreate((String) hashXlData.get("Facility_No_for_singleWO"), (String) hashXlData.get("Standard"),statusToCheck);
			
			String singleWO;
			if (needToCreateSingleWO) {
				hashXlData.clear();
				fnpLoadHashData(hashXlData, ISR_suitexls, classNameText, 2, 4);
				String temp=classNameText;
				classNameText=IPULSE_CONSTANTS.ISO9001_SingleTestCaseName;
				singleWO=fnpCreateANewSingleWOTillStatus("INPROCESS");
				classNameText=temp;
				fnpMymsg("Created Single work order for Corporate of this facility '"+(String) hashXlData.get("Facility_No_for_singleWO")+"' is ---"+singleWO);
				IsBrowserPresentAlready=true;
				
			}
			
			/******************************************************************************/
			
			
			
		
			hashXlData.clear();
			fnpLoadHashData(hashXlData, ISR_suitexls, classNameText, 2, 3);
			
/*			fnpMymsg("@  ---Before query starts");
			fnpDropCampusWOWithAllItsAssociation((String) hashXlData.get("Corp/Facility#"), (String) hashXlData.get("Standard"));
			fnpMymsg("@  ---After query finishes");
			*/
			
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
			workOrderName="Campus";
		} catch (Throwable t) {
				
				fnpCommonFinalCatchBlock(t, "  CreateWOFlow flow  is fail . ", "CreateWOFlowFail");
			
			}

	}
	

	@Test(priority = 2, dependsOnMethods = { "CreateWOFlow" })
	public void Standard_Facility_Tab() throws Throwable {
		try{
			
/*			if (fnpCheckElementDisplayedImmediately("StandardFacilityTab_WorkOrderStandardInformationPanel_expandIcon")) {
				fnpMymsg("Debug1: StandardFacilityTab_WorkOrderStandardInformationPanel_expandIcon  is present");
				fnpClick_OR("StandardFacilityTab_WorkOrderStandardInformationPanel_expandIcon");
			} else {
				fnpMymsg("Debug2: StandardFacilityTab_WorkOrderStandardInformationPanel_expandIcon  is NOT present");
			}
		*/
			
			
			//IPM-14141
			//fnpClick_OR("StandardFacilityTab_WorkOrderStandardInformationPanel_expandIcon");
			
			
			if (standardFacilityTab_WOSI_Panel_present) {
				fnpMymsg("Accordian present for this 'Work Order Standard Information'.");
				fnpClick_OR("StandardFacilityTab_WorkOrderStandardInformationPanel_expandIcon");
			} else {
				fnpMymsg("No accordian present for this 'Work Order Standard Information'.");
			}
			
			
				//fnpDeleteAllAssociatedFacilities();
				fnpDeleteAssociatedFacilities((String) hashXlData.get("Facility_No"));
				
				ISR_BS01.Standard_Facility_Tab();
				
/*				
				//IPM-14141 For timebeing I am refreshing the page in this above method 'Standard_Facility_Tab' for this week only 04-11-2020, later after fixing this in another bug which Indrajeet will create and fix then we can remove this .
				//so because of refreshing this accordian got closed, so opening it
				fnpClick_OR("StandardFacilityTab_WorkOrderStandardInformationPanel_expandIcon");
				*/
				
				
				int associateFacilityRowNo=0;
				int  facilityColNoOutside=0;
				
				
				int totalRows=fnpCountRowsInTable("AssociateFacilityTable_Data");
				
				//becuase at least one facility should be always associated otherwise it throws error
				//Work order type CAMPUS is required to have facilities in facilities map. If only 1 facility remains, a single work work order type should be created.
				//if (totalRows>1) {
					
					// going to check whether Facility # is already attached or associate to campus wo  in associate facility table
					facilityColNoOutside=fnpFindColumnIndex("AssociateFacilityTable_Header", "Facility #");
					associateFacilityRowNo=fnpFindRow_usingForPagingToNotThrowErrorIfNotFoundInCurrentPage("AssociateFacilityTable_Data", (String) hashXlData.get("Facility_No").trim(), facilityColNoOutside);
			
					
					
				//	if ((associateFacilityRowNo>0)  &(totalRows>1)){	
					if ((associateFacilityRowNo>0)  &(totalRows>1)){	
						// should not be associated already
						textMessage="Facility '"+(String) hashXlData.get("Facility_No").trim()+"' is already associated to campus, " +
								"but it should not be associated already. Hence this script is failed now.";
						fnpMymsg(textMessage);
						fnpDisplayingMessageInFrame(textMessage, 20);
						
						throw new Exception (textMessage);
					} else {
						if ((associateFacilityRowNo>0)  &(totalRows==1)){
							// no need to add

						} else {
							textMessage="Facility '"+(String) hashXlData.get("Facility_No").trim()+"' is not associated to campus, so  going to associate it.";
							fnpMymsg(textMessage);
							fnpDisplayingMessageInFrame(textMessage, 10);
							
							//fnpPFList("AssociatedFacilitiesPFList", "AssociatedFacilitiesPFListOptions", (String) hashXlData.get("Associated_Facilities"));
							//ADD FACILITY PART OF Single Plus AUDIT
							fnpPFList_usingForCaseInsensitive("AssociatedFacilitiesPFList", "AssociatedFacilitiesPFListOptions", (String) hashXlData.get("Associated_Facilities"));
							
							fnpClick_OR("AddFacilityBtn");
							fnpType("OR", "FacilityFilterBoxInAddFacilityPopup", (String) hashXlData.get("Facility_No"));
							fnpLoading_waitInsideDialogBox();
							
							int facilityColNoInside = fnpFindColumnIndex("AddFacilityTableInPopupForCampus_Header", "Facility#");
							
							int rowNo=fnpFindRow_usingForPagingToNotThrowErrorIfNotFoundInCurrentPage("AddFacilityTableInPopupForCampus_Data", (String) hashXlData.get("Facility_No").trim(), facilityColNoInside);
							
							
							//loop because sometime application becomes too slow.
							int iWhileCounter=0;
							while(true){
								iWhileCounter++;
								
								rowNo = fnpFindRow_usingForPagingToNotThrowErrorIfNotFoundInCurrentPage( "AddFacilityTableInPopupForCampus_Data", (String) hashXlData.get("Facility_No").trim(), facilityColNoInside);
								
								if (rowNo>0) {
									break;
									
								} else {
									if (iWhileCounter>10) {
										break;
										
									} else {
										Thread.sleep(5000);
										fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
									}

								}
								
							}
					
							
							
							
							
							
							if (rowNo<1) {
								msg="Facility '"+(String) hashXlData.get("Facility_No").trim()+"' is not present/visible.";
								fnpMymsg(msg);
								throw new Exception(msg);
								
							} else {
								//nothing to do...can use later
							}
							
							String xpathForFirstradioBtnInAddFacilityPopup=OR.getProperty("FirstFacilityRadioBtnInAddFacilityPopup_Firstpart")
									+(String) hashXlData.get("Facility_No")
									+OR.getProperty("FirstFacilityRadioBtnInAddFacilityPopup_Secondpart");
							
							//fnpClick_NOR(xpathForFirstradioBtnInAddFacilityPopup);
							fnpClick_NOR_withoutWait(xpathForFirstradioBtnInAddFacilityPopup);
							fnpClick_OR("AddFacilityPopup_ContinueBtn");
							
							/**********as per Karen on  16-06-2017 mail having subject line --need adjustment to ISR campus work order   ************/
							if (fnpCheckElementDisplayed_NOR(OR.getProperty("ScopeOfFacility_inAddFacilityOnCampusWO_popUp"), 20)) {
								fnpMymsg("'Scope Of the facility' text box is  displayed, so going to insert values in it.");
								//fnpType("OR", "ScopeOfFacility_inAddFacilityOnCampusWO_popUp", "test scope");
								String alreadyInsertedValue = fnpGetAttribute_OR("ScopeOfFacility_inAddFacilityOnCampusWO_popUp", "value").trim();
								if ((alreadyInsertedValue.equalsIgnoreCase(""))) {
									fnpType("OR", "ScopeOfFacility_inAddFacilityOnCampusWO_popUp", (String) hashXlData.get("Scope_of_the_facility_in_associated_facility_in_same_audit"));//this value provided by karen
								}
								
								
							}else{
								msg="'Scope Of the facility' text box is not displayed, so unable to insert values in it.";
								fnpMymsg(msg);
								throw new Exception(msg);
							}
							/***********************************************************************/
							
							int rowsInIndustryCodeProductDetailInPopup=fnpCountRowsInTable("IndustryCodeProductTable_in_addFacilityOnCampusWOPopup");
							if (!(rowsInIndustryCodeProductDetailInPopup>0)) {
								addingIndustryCode_usingIn_addFacilityOnCampusWOPopup();								
							}
						
							
							
							fnpClick_OR("Save&ReturnBtnInAddFacilityOnCampusWO");
/*							
							//IPM-14141 For timebeing 
							if (fnpCheckElementDisplayedImmediately("StandardFacilityTab_WorkOrderStandardInformationPanel_expandIcon")) {
								fnpMymsg("Debug3: StandardFacilityTab_WorkOrderStandardInformationPanel_expandIcon  is present");
								fnpClick_OR("StandardFacilityTab_WorkOrderStandardInformationPanel_expandIcon");
							} else {
								fnpMymsg("Debug4: StandardFacilityTab_WorkOrderStandardInformationPanel_expandIcon  is NOT present");
							}
							*/
							
							//Thread.sleep(5000);
							int whileCounter=0;
							while(whileCounter<60){
								Thread.sleep(1000);
								whileCounter++;
								fnpMymsg("In looping for waiting for adding facility successfully for chance ---"+whileCounter);
								associateFacilityRowNo=fnpFindRow_usingForPagingToNotThrowErrorIfNotFoundInCurrentPage("AssociateFacilityTable_Data", (String) hashXlData.get("Facility_No").trim(), facilityColNoOutside);
								if (associateFacilityRowNo>0) {
									break;
								}
								
							}
							
							associateFacilityRowNo=fnpFindRow_usingForPagingToNotThrowErrorIfNotFoundInCurrentPage("AssociateFacilityTable_Data", (String) hashXlData.get("Facility_No").trim(), facilityColNoOutside);
							if (associateFacilityRowNo>0) {
								textMessage="Facility '"+(String) hashXlData.get("Facility_No").trim()+"' is  associated successfully to campus.";
								fnpMymsg(textMessage);
								fnpDisplayingMessageInFrame(textMessage, 10);
							
							} else {
								textMessage="Facility '"+(String) hashXlData.get("Facility_No").trim()+"' is  NOT associated successfully to campus.";
								fnpMymsg(textMessage);
								fnpDisplayingMessageInFrame(textMessage, 10);
								throw new Exception(textMessage);
							}
							
							
						}
						
						}
						
					

					
					
			//	}
			

				
				
				

				
				
		
		
		} catch (Throwable t) {
			
			fnpCommonFinalCatchBlock(t, "  Standard_Facility_Tab flow  is fail . ", "Standard_Facility_TabFail");
		
		}

	}
	
	

	
/*
	
	@Test(priority = 3, dependsOnMethods = { "Standard_Facility_Tab" })
	public void Second_CreateSingleWOFlow() throws Throwable {

		try {
			
			hashXlData.clear();
			fnpLoadHashData(hashXlData, Supply_Chain_and_ISR_suitexls, classNameText, 2, 4);
			
			hashXlData.put("FirstWoNo", FirstWoNo);
			
			workOrderName="Single";


			fnpClickAtTopWorkAroundForClickingMenu();
			fnpWaitForVisible_usingLinkNameInOR("Menu");
			fnpMouseHover_LinkNameInOR("Menu");
			fnpMouseHover_LinkNameInOR("CreateIssue");
			fnpGetORObjectX_usingLinkText("CreateWorkOrderLink").click();
			fnpLoading_wait();
			

			ISR_BS01.CreateWOFlow();
			start = Originalstart;

			String workOrderNo_text = fnpGetORObjectX("NewlyCreatedWorkOrderNo").getText();
			SecondWoNo = ((workOrderNo_text.split(" "))[0]).trim();
			fnpMymsg(" Second Work Order No is:" + SecondWoNo);

		} catch (Throwable t) {
					
			fnpCommonFinalCatchBlock(t, "  Second_CreateWOFlow flow  is fail . ", "Second_CreateWOFlowFail");
		
		}
				
	}
	
	
	*/
	
	
	
	//@Test(priority = 4, dependsOnMethods = { "Second_CreateSingleWOFlow" })
	@Test(priority = 3, dependsOnMethods = { "Standard_Facility_Tab" })
	public void Associate_SingleToCampus() throws Throwable {
		try {
/*			
			if (fnpCheckElementDisplayedImmediately("StandardFacilityTab_WorkOrderStandardInformationPanel_expandIcon")) {
				fnpMymsg("Debug5: StandardFacilityTab_WorkOrderStandardInformationPanel_expandIcon  is present");
				fnpClick_OR("StandardFacilityTab_WorkOrderStandardInformationPanel_expandIcon");
			} else {
				fnpMymsg("Debug6: StandardFacilityTab_WorkOrderStandardInformationPanel_expandIcon  is NOT present");
			}
			
			*/
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
					textMessage="WO type is not single for this assoicated facility '"+(String) hashXlData.get("Facility_No_for_singleWO")+"'. ";
					fnpMymsg(textMessage);
					fnpDisplayingMessageInFrame(textMessage, 10);
					throw new Exception(textMessage);
				}
			}
			
			
			

			
			if (associateSingleWORowNo>0) {
				// already associated so not going to associate again
				textMessage="Single WO  is already associated to Campus, so no need to associate it.";
				fnpMymsg(textMessage);
				fnpDisplayingMessageInFrame(textMessage, 10);
			} else {
				textMessage="Single WO  is NOT already associated to Campus, so  going to associate it.";
				fnpMymsg(textMessage);
				fnpDisplayingMessageInFrame(textMessage, 10);

				
				fnpPFList("AssociatedFacilitiesPFList", "AssociatedFacilitiesPFListOptions", (String) hashXlData.get("Associated_Facilities_Again"));
				fnpClick_OR("AddFacilityBtn");
								
				woTypeColNo=fnpFindColumnIndex("AddFacilityTableInPopupNotInTabView_Header", "WO Type");
			//	int rowNo=fnpFindRow_usingForPagingToNotThrowErrorIfNotFoundInCurrentPage("AddFacilityTableInPopupNotInTabView_Data", "Single", woTypeColNo);
				
				int facilityColNoInside = fnpFindColumnIndex("AddFacilityTableInPopupNotInTabView_Header", "Facility#");// in popup its name is 'Facility#' but in main table i.e. outside it is 'Facility #'
				associatedFacilityRowNo=fnpFindRow_usingForPagingToNotThrowErrorIfNotFoundInCurrentPage("AddFacilityTableInPopupNotInTabView_Data", (String) hashXlData.get("Facility_No_for_singleWO"), facilityColNoInside);
				 String woTypeForGivenFacility = fnpFetchFromTable("AddFacilityTableInPopupNotInTabView_Data", associatedFacilityRowNo, woTypeColNo);
				if (woTypeForGivenFacility.equalsIgnoreCase("Single")) {
					associateSingleWORowNo=associatedFacilityRowNo;
				} else {
					//nothing to do
				}
				
				
				if (associateSingleWORowNo<1) {
					textMessage="Single Work Order  is not present/visible.";
					fnpMymsg(textMessage);
					fnpDisplayingMessageInFrame(textMessage, 10);
					throw new Exception(textMessage);
					
				} else {
					//nothing to do...can use later
				}
				
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
						textMessage="Single WO of this facility'"+(String) hashXlData.get("Facility_No_for_singleWO").trim()+"' is  associated successfully to Campus.";
						fnpMymsg(textMessage);
						fnpDisplayingMessageInFrame(textMessage, 10);
					} else {
						textMessage="Single WO  is  NOT associated successfully to Campus.";
						fnpMymsg(textMessage);
						fnpDisplayingMessageInFrame(textMessage, 10);
						throw new Exception(textMessage);
					}
					
				}catch(Throwable t){
					textMessage="Single WO  is  NOT associated successfully to Campus. Error is ---"+t.getMessage();
					fnpMymsg(textMessage);
					fnpDisplayingMessageInFrame(textMessage, 10);
					throw new Exception(textMessage);
				}
	
			}
			

			
			
			int totalRows=fnpCountRowsInTable("AssociateFacilityTable_Data");
			Assert.assertTrue(totalRows==2, "Total rows count are not 2 (one for single wo and other for facility) but they are ---"+totalRows);
			fnpMymsg("Total rows count are  2 (one for single wo and other for facility) ");

			
			//facilityColNoOutside = fnpFindColumnIndex("AssociateFacilityTable_Header", "Facility #");
			int associateFacilityRowNo = fnpFindRow_usingForPagingToNotThrowErrorIfNotFoundInCurrentPage("AssociateFacilityTable_Data", (String) hashXlData.get("Facility_No").trim(), facilityColNoOutside);
			if (associateFacilityRowNo>0) {
				fnpMymsg("Associated Facility '"+(String) hashXlData.get("Facility_No").trim()+"' is  present in row no. ---"+associateFacilityRowNo);
			
			} else {
				textMessage="Associated Facility '"+(String) hashXlData.get("Facility_No").trim()+"' is  NOT present in any row.";
				fnpMymsg(textMessage);
				fnpDisplayingMessageInFrame(textMessage, 10);
				throw new Exception(textMessage);
			}
			
			
			
			
			
		
		} catch (Throwable t) {
			
			fnpCommonFinalCatchBlock(t, "  Associate_SingleToCampus flow  is fail . ", "Associate_SingleToCampusFail");
		
		}
	}



	

	@Test(priority = 4, dependsOnMethods = { "Associate_SingleToCampus" })
	public void MoveToInReview_Campus() throws Throwable {
		try {
			
			fnpClick_OR("ISRFinancialTabLink");
			
			
			//fnpPFList("Surveillance_Cycle_PFList", "Surveillance_Cycle_PFListOptions", "6 months");
			fnpPFList("Surveillance_Cycle_PFList", "Surveillance_Cycle_PFListOptions", (String) hashXlData.get("SurveillanceCycle"));
			
			/**********LAST MINUTE CHANGED ==>New mandatory field introduce on 06 June 2019   IPM-10430 **********************************************************/
			//fnpPFListContains_ifMatchingMultipleThenSelectFirstOne("WOBillToLocationPFList",  (String) hashXlData.get("Corp/Facility#")) ;
			//fnpPFListByLiNo("WOBillToLocationPFList","WOBillToLocationPFListOptions", 1) ;
			/********************************************************************************************************************/
	
			
			fnpPFList("ISRBillToOfficePFList", "ISRBillToOfficePFListOptions", (String) hashXlData.get("BillToOffice"));
			fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
			fnpLoading_wait();
			fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
			String insertedBillToOffice = fnpGetText_OR("ISRBillToOfficePFList");
			Assert.assertEquals(insertedBillToOffice, (String) hashXlData.get("BillToOffice"), "Bill To Office value has not been inserted properly ");	
			fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
			
			fnpPFListContains_ifMatchingMultipleThenSelectFirstOne("WOBillToLocationPFList",  (String) hashXlData.get("BillToOffice")) ;
			
			fnpPFList("UpchargePFList", "UpchargePFListOptions", (String) hashXlData.get("Upcharge"));
			fnpClick_OR("ISRSFTabSaveBtn");
			
			fnpFileUploadInISR();
		
			fnpMoveToInReviewByClickingButtonAndVerifyInReview();
		
		} catch (Throwable t) {
			
			fnpCommonFinalCatchBlock(t, "  MoveToInReview_Campus flow  is fail . ", "MoveToInReview_CampusFail");
		
		}
	}


	
	@Test(priority = 5, dependsOnMethods = { "MoveToInReview_Campus" })
	public void MoveToInProcess_Campus() throws Throwable {
		try {
			
			String expndingLinkXpathForFinopsForSingle=OR.getProperty("SingleWOFinopsExpandingLinkInCampusWO_part1")+(String) hashXlData.get("Facility_No_for_singleWO")+OR.getProperty("SingleWOFinopsExpandingLinkInCampusWO_part2");
			
			
			
/*			
			int whileCounter=0;
			Actions action = new Actions(driver);
			while(!fnpCheckElementDisplayed_NOR(expndingLinkXpathForFinopsForSingle, 0)){
				whileCounter++;
				fnpMymsg("This element is not visible, so going down --"+whileCounter);
			
				action.moveToElement(refElement).build().perform();
				refElement.sendKeys(Keys.ARROW_DOWN);
				
				action.sendKeys(Keys.ARROW_DOWN);
				Thread.sleep(500);
				if (whileCounter > Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))*10) {
					fnpMymsg("This element is not visible in max time, so exiting the loop");
					break;
				}
				
			}
			*/
			
			fnpWorkAroundToClickbottomFooter();
			fnpClick_NOR(expndingLinkXpathForFinopsForSingle);

/*			
			try{
				driver.findElement(By.xpath(expndingLinkXpathForFinopsForSingle)).click();
				fnpMymsg("after 2");
				fnpLoading_wait();
				fnpCheckError("");

			}catch(Throwable t){
				fnpMymsg("This is not worked normal");
			}
			
			
			try{
				driver.findElement(By.xpath(expndingLinkXpathForFinopsForSingle+"/span")).click();
				fnpMymsg("after 3");
				fnpLoading_wait();
				fnpCheckError("");

			}catch(Throwable t){
				fnpMymsg("This is not worked span");
			}
			
			try{
			fnpClick_NOR(expndingLinkXpathForFinopsForSingle);
			fnpMymsg("after 1");
			}catch(Throwable t){
				fnpMymsg("This is not worked expndingLinkXpathForFinopsForSingle");
			}
			
			*/
						
			 String singleFinopsTableXpathHeader = expndingLinkXpathForFinopsForSingle+"/following-sibling::div[1]//table/thead[contains(@id,':finOpsAiList_head')]";
			 String singleFinopsTableXpathData = expndingLinkXpathForFinopsForSingle+"/following-sibling::div[1]//tbody[contains(@id,':finOpsAiList_data')]";

			 
			 
/*			
			 String singleFinopsTableXpathHeader = expndingLinkXpathForFinopsForSingle+"/following-sibling::div[1]//thead[contains(@id,':actionItemDataTable_head')]";
			 String singleFinopsTableXpathData = expndingLinkXpathForFinopsForSingle+"/following-sibling::div[1]//tbody[contains(@id,':actionItemDataTable_data')]";

*/
			
			String FirstValueInTable = fnpFetchFromTable_NOR_TraversingFromVariousNodes(singleFinopsTableXpathData, 1, 1);
			
			Assert.assertFalse(FirstValueInTable.contains("No records found"), "FINOPS Action item should be generated under the Finops Action Item Table.");
			fnpMymsg(" FINOPS Action item has been  generated under the Finops Action Item Table.");

			int aiNameColNo=fnpFindColumnIndex_NOR_TraversingFromVariousNodes(singleFinopsTableXpathHeader, "Action Item Name");
			String actionItemName_FirstValue = fnpFetchFromTable_NOR_TraversingFromVariousNodes(singleFinopsTableXpathData, 1, aiNameColNo);

			String[] expectedAINameArray = ((String) hashXlData.get("Finops_Action_item_name_ForSingle")).split(",");

			int totalRowGenerated = fnpCountRowsInTable_NOR(singleFinopsTableXpathData);
			//Assert.assertTrue(totalRowGenerated == 1, "Total Action Items generated must be 1 but here they are only '" + totalRowGenerated + "'  .");

			String actionItemName_Value = "";
			boolean foundFlag=false;
			for (int p = 0; p < expectedAINameArray.length; p++) {
				foundFlag=false;
				for (int i1 = 1; i1 <= totalRowGenerated; i1++) {
					actionItemName_Value = fnpFetchFromTable_NOR_TraversingFromVariousNodes(singleFinopsTableXpathData, i1, aiNameColNo).trim();
					if ((expectedAINameArray[p].trim()).equalsIgnoreCase(actionItemName_Value)) {
						fnpMymsg(" Action Item '" + expectedAINameArray[p] + "' is present at row no --" + (i1));
						foundFlag=true;
						break;
						
						//continue;
					}
				}
				if (!(foundFlag)) {
					msg="This action item '" + expectedAINameArray[p] + "' is not present in any row  .";
					fnpMymsg(msg);
					throw new Exception(msg);
				}

				}
			
			
			
			
			
			String expndingLinkXpathForFinopsForCampus=OR.getProperty("CampusFinopsExpandingLinkInCampusWO_part1")+(String) hashXlData.get("Corp/Facility#")+OR.getProperty("CampusFinopsExpandingLinkInCampusWO_part2");
			//fnpMove_To_Element_and_DoubleClick_NOR(expndingLinkXpathForFinopsForCampus);
			fnpClick_NOR(expndingLinkXpathForFinopsForCampus);

			
			
			
			
			String campusFinopsTableXpathHeader=expndingLinkXpathForFinopsForCampus+"/following-sibling::div[1]//thead[contains(@id,':finOpsAiList_head')]";
			String campusFinopsTableXpathData=expndingLinkXpathForFinopsForCampus+"/following-sibling::div[1]//tbody[contains(@id,':finOpsAiList_data')]";
			

/*
			String campusFinopsTableXpathHeader=expndingLinkXpathForFinopsForCampus+"/following-sibling::div[1]//thead[contains(@id,':actionItemDataTable_head')]";
			String campusFinopsTableXpathData=expndingLinkXpathForFinopsForCampus+"/following-sibling::div[1]//tbody[contains(@id,':actionItemDataTable_data')]";
			*/
			
			
			String Finops_ReassignedTo;
			
/*			
			if (loginAsFullName.equalsIgnoreCase("") | (loginAsFullName==null) ) {
				Finops_ReassignedTo=(String) hashXlData.get("Finops_ReassignedTo").trim();
			} else {
				Finops_ReassignedTo=loginAsFullName;
			}
			*/
			
			
			if (  (loginAsFullName==null) ||loginAsFullName.equalsIgnoreCase("") ) {
				//reassignee=(String) hashXlData.get("AccountManager").trim();
				if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
					Finops_ReassignedTo=(String) hashXlData.get("Finops_ReassignedTo_Code").trim();
				}else{
					Finops_ReassignedTo=(String) hashXlData.get("Finops_ReassignedTo").trim();
				}

			} else {
				//reassignee=loginAsFullName;
				if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
					Finops_ReassignedTo=loginUser_code;
				}else{
					Finops_ReassignedTo=loginAsFullName;
				}
			}
			
			
			
			fnpProcessAI_ISR_NOR( campusFinopsTableXpathData, campusFinopsTableXpathHeader, hashXlData.get("Finops_Action_item_name_ForCampus"), 
					"COMPLETED", "WOISR_FinopActionItemSaveNCloseBtn", Finops_ReassignedTo);
			
			//IPM-14359
			//fnpClick_OR("MoveToInProcessBtn");
			
			if (fnpCheckElementDisplayedImmediately("MoveToInProcessBtn")) {
				fnpMymsg("'Move To InProcess' button is  visible, so clicking it.");
				fnpClick_OR("MoveToInProcessBtn");
			}else{
				fnpMymsg("'Move To InProcess' button is not visible, so moving forward without looking for it.");
			}
			
			
			
			fnpMouseHover("TopMessage3");

			
			fnpWaitTillClickable("NewlyCrWOTopBannerInfo");
			fnpGetORObjectX("NewlyCrWOTopBannerInfo").click();
	
			fnpWaitForVisible("TopBannerWOStatus");
			
			
			String changedWOStatus = fnpGetORObjectX("TopBannerWOStatus").getText();
			Assert.assertTrue(changedWOStatus.equalsIgnoreCase("INPROCESS"), workOrderName+" WO status is not changed from INREVIEW to INPROCESS.");
			fnpMymsg("Now "+workOrderName+"  WO  status has been changed from 'INREVIEW' to  INPROCESS.");
			
			
			
		
		} catch (Throwable t) {
			
			fnpCommonFinalCatchBlock(t, "  MoveToInProcess_Campus flow  is fail . ", "MoveToInProcess_CampusFail");
		
		}
	}


	
	@Test(priority = 6, dependsOnMethods = { "MoveToInProcess_Campus" })
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

	
	
	public static void fnpDeleteAllAssociatedFacilities() throws Throwable{
		
		String deleteXpathForAssociatedFacilities=".//a[contains(@id,':facMp:')][contains(@id,':delCusStContXref')]";
		String deleteXpathForAssociatedFacilitiesConfirmationBtn=".//*[@id='mainForm:deltaskyesbtnCusSTdlisCf']";
		
		
		
		List<WebElement> alreadyAssociatedFacilities = driver.findElements(By.xpath(deleteXpathForAssociatedFacilities));
		int sizeOfAlreadyAssociatedFacilities=alreadyAssociatedFacilities.size();
		fnpMymsg("  --Already Associated Facilities are --"+sizeOfAlreadyAssociatedFacilities);
		int iwhileCounterForDeletingAssociatedFacilities=0;
		while(sizeOfAlreadyAssociatedFacilities>0){
			iwhileCounterForDeletingAssociatedFacilities++;
			if (iwhileCounterForDeletingAssociatedFacilities>10) {
				break;
			}
			fnpMymsg("  --Going to delete this Associated Facility --"+fnpFetchFromTable_NOR(OR.getProperty("AssociateFacilityTable_Data"),1,3));
			alreadyAssociatedFacilities.get(0).click();
			fnpClick_NOR(deleteXpathForAssociatedFacilitiesConfirmationBtn);
			fnpLoading_wait();
			
			driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
			alreadyAssociatedFacilities = driver.findElements(By.xpath(deleteXpathForAssociatedFacilities));
			sizeOfAlreadyAssociatedFacilities=alreadyAssociatedFacilities.size();
			 driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);
	
		}
		
		
		

		
		
	}
	
	
	
	
	
	public static void fnpDeleteAssociatedFacilities(String facilityNo) throws Throwable{
		
		
		int totalRows=fnpCountRowsInTable("AssociateFacilityTable_Data");
		
		//becuase at least one facility should be always associated otherwise it throws error
		//Work order type CAMPUS is required to have facilities in facilities map. If only 1 facility remains, a single work work order type should be created.
		if (totalRows>1) {
			
			String deleteXpathForAssociatedFacilities=".//a[contains(@id,':facMp:')][contains(@id,':delCusStContXref')]";
			String deleteXpathForAssociatedFacilitiesConfirmationBtn=".//*[@id='mainForm:deltaskyesbtnCusSTdlisCf']";
			

			//String deleteFacilityXpath_part1=".//*[@id='mainForm:tabView:woStdInfoAccordPnl:facMp:";
			String deleteFacilityXpath_part1;
			if (standardFacilityTab_WOSI_Panel_present) {
				fnpMymsg("Accordian present for this 'Work Order Standard Information'.");
				deleteFacilityXpath_part1=".//*[@id='mainForm:tabView:woStdInfoAccordPnl:facMp:";
			} else {
				deleteFacilityXpath_part1=".//*[@id='mainForm:tabView:facMp:";
			}
			String deleteFacilityXpath_part2=":delCusStContXref']";
			
			
				 
			int facilityColNoOutside = fnpFindColumnIndex("AssociateFacilityTable_Header", "Facility #");
			
			int associateFacilityRowNo = fnpFindRow_usingForPagingToNotThrowErrorIfNotFoundInCurrentPage("AssociateFacilityTable_Data", facilityNo, facilityColNoOutside);
			
			if (associateFacilityRowNo>0) {
				String textMessage="Going to delete this associated facility '"+(String) hashXlData.get("Facility_No").trim()+"' .";
				fnpMymsg(textMessage);
				fnpDisplayingMessageInFrame(textMessage, 10);
				
				String completeDeleteXpath=deleteFacilityXpath_part1+(associateFacilityRowNo-1)+deleteFacilityXpath_part2;
				fnpClick_NOR(completeDeleteXpath);
				fnpClick_NOR(deleteXpathForAssociatedFacilitiesConfirmationBtn);
				fnpLoading_wait();
			}
			
			
		}



		
		
	}
	
}