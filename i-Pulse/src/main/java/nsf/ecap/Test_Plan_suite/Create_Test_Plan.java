package nsf.ecap.Test_Plan_suite;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import nsf.ecap.Work_Order_suite.NewNew_Draft_InReview_No_Fac;
//import nsf.ecap.config.IPULSE_CONSTANTS;
import static nsf.ecap.config.IPULSE_CONSTANTS.*;
import nsf.ecap.util.TestUtil;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Create_Test_Plan extends TestSuiteBase_Test_Plan_suite {


	
	
	@BeforeTest
	public void checkTestSkip() throws Throwable {
		BS01 = new NewNew_Draft_InReview_No_Fac();
		BS01.checkTestSkip(this.getClass().getSimpleName());
		

	}
	
	
	
	

	
	
	
	//@Test(enabled = false)
	@Test(priority = 1)
	public void CreateTestPlanFlow() throws Throwable {
		
		start = new Date();
		try{
			

			
				fnpLaunchBrowserAndLogin();
				
				/**********************************************/
				
				//IPM-13215 13-04-2020
				String actualLoginUser=CONFIG.getProperty("adminUsername").trim(); // Not the secure login user, but actual user
				
				
				
/*				String TestPlan_Parent1Normalization_Progeny1_SamplePrepCode_listValue1=(String) hashXlData.get("TestPlan_Parent1Normalization_Progeny1_SamplePrepCode_listValue").trim();
				fnpPFListModify("TestPlan_Parent1Normalization_Progeny1_SamplePrepCode_List", TestPlan_Parent1Normalization_Progeny1_SamplePrepCode_listValue1);	
				*/
				/***********************/
				
				
				fnpCommonClickTopMainMenu("Menu", "FirstRefrenceLinkJustBelowToMenu", "SearchTaskLink", "TaskTypeMultipleSelectDropDown");
				
				fnpMultipleSelectDropDown3("TaskTypeMultipleSelectDropDown", (String) hashXlData.get("Task_Type").trim());
				
				fnpMultipleSelectDropDown_havingFilterBox_and_checkboxes("SearchTaskScreen_OrgUnit_DropDown","","OrgUnit_OKLink",(String) hashXlData.get("Org_Unit").trim(),"OrgLabelXpath");
				
				fnpMultipleSelectDropDown3("TaskStatusMultipleSelectDropDown", (String) hashXlData.get("Task_Status").trim());
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
					int woCol = fnpFindColumnIndex("StandardSearchTableHeader", "WO No");
					s = fnpFetchFromStSearchTable(1, woCol);
				}
		
				fnpMymsg("First hyperlink wo no. is --"+s);
				fnpClickALinkInATable(s);
		
				fnpLoading_wait();
				fnpIpulseDuringLoading();
				
				fnpCommonClickTaskTab();
				
				fnpCommonCodeForSelectingTestingTaskMultipleTimes() ;
					
				
				fnpCommonCodeFor_EPSFHeaderTab((String) hashXlData.get("Collection_Type").trim(),(String) hashXlData.get("Test_Category"),
						(String) hashXlData.get("Standard"),(String) hashXlData.get("Test_Location"),(String) hashXlData.get("Ship_to_Location"));
						
				//EPSF Fields tab
				fnpType("OR", "EPSF_TradeDesignationModalNoTxtBox", (String) hashXlData.get("ProductName_ModelNumber"));
				fnpType("OR", "EPSF_PhysicalDescriptionofSampleTxtBox", (String) hashXlData.get("Test_Description"));
				fnpType("OR", "EPSF_PerformanceStandardTxtBox", (String) hashXlData.get("Performance_Standard"));
				fnpType("OR", "EPSF_PerformanceStandardYearTxtBox", (String) hashXlData.get("Performance_Standard_Year"));
				fnpType("OR", "EPSF_TestDescriptionTxtBox", (String) hashXlData.get("Test_Description"));
				
				fnpCommonCodeFor_EPSF_NextNext();		
				String epsfStatus=fnpGetText_OR("EPSF_Status").trim();
				Assert.assertEquals("DRAFT", epsfStatus, "Newly created epsf status should be DRAFT");		
				fnpClick_OR("EPSF_SaveBtn");
				
				
		
				fnpCommonBackToViewNBackBtnClick_2() ;
				
				
				
				
				
				//******************************************************
				
				fnpCommonCodeForSelectingTestingTaskMultipleTimes() ;
				
				fnpCommonCodeFor_EPSFHeaderTab((String) hashXlData.get("Collection_Type_2").trim(),(String) hashXlData.get("Test_Category_2"),
						(String) hashXlData.get("Standard_2"),(String) hashXlData.get("Test_Location_2"),(String) hashXlData.get("Ship_to_Location_2"));
						
				//EPSF Fields tab
				
				fnpType("OR", "EPSF_TestDescriptionTxtBox", (String) hashXlData.get("Test_Description"));
				fnpType("OR", "EPSF_TradeDesignationModalNoTxtBox", (String) hashXlData.get("Test_Description"));
				fnpType("OR", "EPSF_PhysicalDescriptionofSampleTxtBox", (String) hashXlData.get("Test_Description"));
				//fnpType("OR", "EPSF_ParentDCCNumberTxtBox", (String) hashXlData.get("Test_Description"));
				fnpClick_OR("EPSF_ParentDCCNumberLkpBtn");
				fnpWithoutSearchSelectFirstRadioBtn();
				fnpClick_OR("EPSF_TestedDCCNumberLkpBtn");
				fnpWithoutSearchSelectFirstRadioBtn();
				fnpType("OR", "EPSF_ClassFunctionTxtBox", (String) hashXlData.get("Test_Description"));
						
				fnpCommonCodeFor_EPSF_NextNext();
				 epsfStatus=fnpGetText_OR("EPSF_Status_in_EPSF_No").trim();
				 epsfStatus=fnpremoveFormatting(epsfStatus);
				Assert.assertEquals("DRAFT", epsfStatus, "Newly created epsf status should be DRAFT");
				
				fnpWaitTillVisiblityOfElementNClickable("TestPlanTab_CreateNew_btn");
				fnpWaitTillVisiblityOfElementNClickable("TestPlanTab_createExistTestPlan_btn");
		
				fnpClick_OR("TestPlanTab_CreateNew_btn");
				
				fnpWaitForVisible("TestPlanTab_TestPlanGeneralInformationSection");
				fnpWaitForVisible("TestPlanTab_ParentNormalization");
				
				String classOfRequiredToxEval=fnpGetAttribute_OR("TestPlanTab_RequiredToxEvalCheckBx", "class");
				if (classOfRequiredToxEval.contains("ui-icon-blank")) {
					msg="Required Tox Eval checkbox is unchecked.";
					fnpMymsg(msg);			
				}else{
					msg="Required Tox Eval checkbox is NOT unchecked.";
					fnpMymsg(msg);
					throw new Exception(msg);
				}
				
				
				String classOfRVCM=fnpGetAttribute_OR("TestPlanTab_RVCMCheckBx", "class");
				if (classOfRequiredToxEval.contains("ui-icon-blank")) {
					msg="RVCM checkbox is unchecked.";
					fnpMymsg(msg);			
				}else{
					msg="RVCM checkbox is NOT unchecked.";
					fnpMymsg(msg);
					throw new Exception(msg);
				}
				
				
				// String initial_ProductTemplate_value_WhichWillBeVerifiedAgainAtThelastStep = fnpGetText_OR("TestPlan_ProductTemplate_List");
				
				
/*
				fnpGetORObjectX("EPSF_SaveBtn").click();
				fnpWaitingForExpectedErrorMsg(errorMsgOnSavingWithoutSelectingProductTemplate);
				String Msg = fnpGetText_OR_EvenMultipleObjectsHavingSameXpath_specialForExtractingErrorMsgTextOnlyEvenIfNotVisible("ErrorMessage");

				fnpMymsg("Top Message is coming on saving without selecting Product Template  ----" + Msg);
				Assert.assertEquals(
						Msg,
						errorMsgOnSavingWithoutSelectingProductTemplate,
						"Top message does not contain this error message '"+errorMsgOnSavingWithoutSelectingProductTemplate+"'  on saving Test Plan without Selecting Product Template.");

		*/
				
				//************************
				
				
				 String valuesAll = (String) hashXlData.get("TestPlan_Statuses_DropDownValues_JustAfterDraft").trim();
				String[] valuesArray = valuesAll.split(",");
				List<String> values = Arrays.asList(valuesArray); 
				//List<String> values = new ArrayList<>(); 
				//Collections.addAll(values, valuesArray); 
				fnpPFList_VerifyOptionsValues("TestPlan_Status_List", "TestPlan_Status_ListOptions", values) ;
				
/*				
				for (int i = 0; i < valuesArray.length; i++) {
					fnpPFListModify_usingWhenIgnoringErrorOnSelectingValues( "TestPlan_Status_List",  valuesArray[i]) ;					 
					fnpGetORObjectX("EPSF_SaveBtn").click();
					fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
					fnpWaitingForExpectedErrorMsg(errorMsgOnSavingWithoutSelectingProductTemplate);
					 msg = fnpGetText_OR_EvenMultipleObjectsHavingSameXpath_specialForExtractingErrorMsgTextOnlyEvenIfNotVisible("ErrorMessage");

					fnpMymsg("Top Message is coming on saving the status '"+valuesArray[i]+"' without selecting Product Template  ----" + msg);
					Assert.assertEquals(
							msg,
							errorMsgOnSavingWithoutSelectingProductTemplate,
							"Top message does not contain this error message '"+errorMsgOnSavingWithoutSelectingProductTemplate+"'  on saving Test Plan  status '"+valuesArray[i]+"' without Selecting Product Template.");					
				}
				*/
				
				fnpPFListModify_usingWhenIgnoringErrorOnSelectingValues( "TestPlan_Status_List",  "Draft") ;	
				//fnpLoading_wait();
				//Thread.sleep(1000);
				fnpLoading_wait_withoutErrorChecking() ;
				
				
				String productTemplateValuesAll=(String) hashXlData.get("ProductTemplate_DropDownValues_Initially").trim();
				String[] productTemplateValuesArray = productTemplateValuesAll.split(",");
				List<String> productTemplateValues = Arrays.asList(productTemplateValuesArray); 
				fnpPFList_VerifyOptionsValues_Contains("TestPlan_ProductTemplate_List", "TestPlan_ProductTemplate_ListOptions", productTemplateValues) ;
				//Thread.sleep(1000);
				fnpLoading_wait_withoutErrorChecking() ;
				fnpPFListModify_VerifyNoOfValuesInTheDropDown("TestPlan_ProductTemplate_List",3);
				

				//fnpClick_OR("TestPlan_ProductTemplate_List");
				fnpGetORObjectX("TestPlan_ProductTemplate_List").click();
				//Thread.sleep(1000);
				fnpLoading_wait_withoutErrorChecking() ;
				fnpWorkAroundToClickbottomFooter();
				//fnpPFListModify("TestPlan_ProductTemplate_List", "See More");
				fnpPFListModify_usingWhenIgnoringErrorOnSelectingValues( "TestPlan_ProductTemplate_List",  "See More") ;	
				//Thread.sleep(5000);
				fnpLoading_wait_withoutErrorChecking() ;
				fnpWorkAroundToClickbottomFooter();
				//fnpPFListModify("TestPlan_ProductTemplate_List", (String) hashXlData.get("Product_Template").trim());
				fnpPFListModify_usingWhenIgnoringErrorOnSelectingValues( "TestPlan_ProductTemplate_List",   (String) hashXlData.get("Product_Template").trim()) ;
			//	Thread.sleep(1);
				
/*				Thread.sleep(5000);
				fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
				*/
				Thread.sleep(1000);
				fnpLoading_wait_withoutErrorChecking() ;
				
				fnpWorkAroundToClickbottomFooter();
				
				
				fnpGetORObjectX("EPSF_SaveBtn").click();
				fnpLoading_wait_withoutErrorChecking() ;
				fnpCheckError("");
				
				
				String testPlanNo = fnpGetText_OR("TestPlan_TestPlanNo_at_banner_xpath");
				if (!(testPlanNo.trim().equalsIgnoreCase(""))) {
					fnpMymsg("Test Plan no. is generated and number is --"+testPlanNo);
					
				} else {
					msg="Test Plan no. is not generated as currently its value shown here is blank i.e. --'"+testPlanNo+"'. ";
					fnpMymsg(msg);
					throw new Exception(msg);
				}
				
				
				String testPlanStatus = fnpGetText_OR("TestPlan_TestPlanStatus_at_banner_xpath");
				if (!(testPlanStatus.trim().equalsIgnoreCase("Draft"))) {
					fnpMymsg("Test Plan no. is become in Draft status. ");
					
				} else {
					msg="Test Plan status is not coming 'Draft' as currently test plan status  shown here is  --'"+testPlanStatus+"'. ";
					fnpMymsg(msg);
					throw new Exception(msg);
				}
				
				
				String defaultValue = fnpGetText_OR("TestPlan_ParentTemplate_List");
				String expectedParentTemplateValue=(String) hashXlData.get("Parent_Template_default").trim();
				if (!( (defaultValue.equalsIgnoreCase(expectedParentTemplateValue)))) {
					msg="Default value in Parent Template must be '"+expectedParentTemplateValue+"' but here it is coming --'"+defaultValue+"'.";
					fnpMymsg(msg);
					throw new Exception(msg);
				}else{
					msg="Default value in Parent Template is matched as expected is  '"+expectedParentTemplateValue+"' and here it is coming --'"+defaultValue+"'.";
					fnpMymsg(msg);
				}
				
				
				String lastModifiedBy=fnpGetText_OR("TestPlan_LastModified_By_xpath");
				String lastModifiedDate=fnpGetText_OR("TestPlan_LastModified_Date_xpath");
			
				//IPM-13215 13-04-2020
			
/*				if (!( (lastModifiedBy.equalsIgnoreCase(actualLoginUser)))) {
					msg=" 'Last Modified By' should be '"+actualLoginUser+"' but here it is coming --'"+lastModifiedBy+"'.";
					fnpMymsg(msg);
					throw new Exception(msg);
				}else{
					msg="'Last Modified By' is matched as expected is  '"+actualLoginUser+"' and here it is coming --'"+lastModifiedBy+"'.";
					fnpMymsg(msg);
				}
				*/
				
				if (!( (lastModifiedBy.equalsIgnoreCase(loginAs)))) {
					msg=" 'Last Modified By' should be '"+loginAs+"' but here it is coming --'"+lastModifiedBy+"'.";
					fnpMymsg(msg);
					throw new Exception(msg);
				}else{
					msg="'Last Modified By' is matched as expected is  '"+loginAs+"' and here it is coming --'"+lastModifiedBy+"'.";
					fnpMymsg(msg);
				}
				
				
				
				
				 //Date todayDate = new Date();
				TimeZone timeZone = TimeZone.getTimeZone("US/Eastern");
				String timeFormat = "dd-MMM-yyyy";		
				String dateInString=getCurrentTime(timeFormat,timeZone);

				SimpleDateFormat formatter = new SimpleDateFormat(timeFormat );
				Date todayDate = formatter.parse(dateInString);
				 
				 
				 
				 
				 
				// SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
				 String expectedDateString = formatter.format(todayDate);				
				 Date expectedDate = formatter.parse(expectedDateString);
				
				
				
				//uncomment later because if I uncomment these then I can not run it before 10:30 AM IST
				Date actualDate=formatter.parse(lastModifiedDate);
				if (expectedDate.compareTo(actualDate) > 0) {
					msg="Last modifed date is less than  to the current today date, expected is this '"+expectedDate+"' but actual is '"+actualDate+"'.";
					fnpMymsg(msg);
					throw new Exception(msg);
				} else {
					if (expectedDate.compareTo(actualDate) < 0) {
						msg="Last modifed date is more than  to the current today date, expected is this '"+expectedDate+"' but actual is '"+actualDate+"'.";
						fnpMymsg(msg);
						throw new Exception(msg);
					} else {
						msg="Last modifed date is equal  to the current today date, expected is this '"+expectedDate+"' and actual is '"+actualDate+"'.";
						fnpMymsg(msg);
					}
				}
				
				
				
				
				
				
				fnpPFListModify("TestPlan_ParentTemplate_List", "Please Select");	
				//Thread.sleep(5000);
				Thread.sleep(1000);
				fnpLoading_wait_withoutErrorChecking() ;
				fnpWorkAroundToClickbottomFooter();
				 String changeStatus = "Approved";
				fnpPFListModify_usingWhenIgnoringErrorOnSelectingValues( "TestPlan_Status_List",  changeStatus) ;
				fnpLoading_wait_withoutErrorChecking() ;
				fnpGetORObjectX("EPSF_SaveBtn").click();
				//Thread.sleep(1000);
				fnpLoading_wait_withoutErrorChecking() ;
				fnpWaitingForExpectedErrorMsg(errorMsgOnSavingWithoutSelectingParentTemplate);
				fnpMouseHover_OR_EvenMultipleObjectsHavingSameXpath_usingSpecialForErrorClassOnly("ErrorMessage");
				 msg = fnpGetText_OR_EvenMultipleObjectsHavingSameXpath_specialForExtractingErrorMsgTextOnlyEvenIfNotVisible("ErrorMessage");

				fnpMymsg("Top Message is coming on saving the status '"+changeStatus+"' without selecting Parent Template  ----" + msg);
				Assert.assertEquals(
						msg,
						errorMsgOnSavingWithoutSelectingParentTemplate,
						"Top message does not contain this error message '"+errorMsgOnSavingWithoutSelectingParentTemplate+"'  on saving Test Plan  status '"+changeStatus+"' without Selecting Parent Template.");					

				
				
				
				changeStatus="Reviewed";			
				fnpPFListModify_usingWhenIgnoringErrorOnSelectingValues( "TestPlan_Status_List",  changeStatus) ;
				fnpLoading_wait_withoutErrorChecking() ;
				fnpGetORObjectX("EPSF_SaveBtn").click();
				fnpLoading_wait_withoutErrorChecking() ;
				fnpWaitingForExpectedErrorMsg(errorMsgOnSavingWithoutSelectingParentTemplate);
				fnpMouseHover_OR_EvenMultipleObjectsHavingSameXpath_usingSpecialForErrorClassOnly("ErrorMessage");
				 msg = fnpGetText_OR_EvenMultipleObjectsHavingSameXpath_specialForExtractingErrorMsgTextOnlyEvenIfNotVisible("ErrorMessage");

				fnpMymsg("Top Message is coming on saving the status '"+changeStatus+"' without selecting Parent Template  ----" + msg);
				Assert.assertEquals(
						msg,
						errorMsgOnSavingWithoutSelectingParentTemplate,
						"Top message does not contain this error message '"+errorMsgOnSavingWithoutSelectingParentTemplate+"'  on saving Test Plan  status '"+changeStatus+"' without Selecting Parent Template.");					

				
				fnpPFListModify_usingWhenIgnoringErrorOnSelectingValues( "TestPlan_Status_List",  "Draft") ;
				
/*				Thread.sleep(5000);
				fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
				*/
				Thread.sleep(1000);
				fnpLoading_wait_withoutErrorChecking() ;
				fnpWorkAroundToClickbottomFooter();
				
				
				
				
				
				fnpPFListModify_usingWhenIgnoringErrorOnSelectingValues( "TestPlan_ParentTemplate_List",  expectedParentTemplateValue) ;	
				Thread.sleep(2000);
				fnpLoading_wait_withoutErrorChecking() ;
				fnpWorkAroundToClickbottomFooter();
				fnpGetORObjectX("TestPlan_ParentTemplate_CreateBtn").click();
				Thread.sleep(3000);// because screen moves little instead of loading icon there
				fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
				fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
				fnpGetORObjectX("EPSF_SaveBtn").click();
				Thread.sleep(3000);
				//fnpClick_OR("EPSF_SaveBtn");
				fnpLoading_wait_withoutErrorChecking() ;
				fnpCheckError("");
				
				fnpLoading_wait();
				
				
				
			//	List<WebElement> parentElements = fnpGetORObject_list_NOR(OR.getProperty("TestPlan_ParentSections_Header_xpath")+"/h3/span", 10);		
			//	List<WebElement> parentElements = fnpGetORObject_list_NOR(OR.getProperty("TestPlan_ParentSections_Header_xpath")+"/h3", 10);	
				  List<WebElement> parentElements = fnpGetORObject_list_NOR(OR.getProperty("TestPlan_ParentSections_Header_xpath"), 10);
				 int parentElementSize = parentElements.size();
				if (parentElementSize!=2) {
					msg="According to the test case there should be 2 Parent Sections but here parent sections are --"+parentElementSize;
					fnpMymsg(msg);
					throw new Exception(msg);
					
				} else {
					fnpMymsg("2 Parent Sections are present.");
					String parentName;
					for (int i = 0; i < parentElementSize; i++) {
						//parentName=parentElements.get(i).getText().trim();
						//parentName=parentElements.get(i).findElement(By.xpath("./h3")).getText().trim();
						parentName=parentElements.get(i).findElement(By.xpath("./div[1]")).getText().trim();
						if (i==0) {
							if (parentName.equalsIgnoreCase(TestPlan_FirstParentSectionName)) {
								fnpMymsg("First parent name is correct i.e. '"+parentName+"'. ");
								
								int parentTemplateDropDownCount=parentElements.get(i).findElements(By.xpath(OR.getProperty("TestPlan_ParentSections_ParentTemplate_dropdown_xpath"))).size();
								
								if (parentTemplateDropDownCount==0) {
									msg="Parent Template drop down is not present in First Parent Section.";
									fnpMymsg(msg);
									throw new Exception(msg);
								} else {
									if (parentTemplateDropDownCount>1) {
										msg="There are multiple ("+parentTemplateDropDownCount+") Parent Template drop downs are  present in First Parent Section and only 1 should be present";
										fnpMymsg(msg);
										throw new Exception(msg);
									} else {
										String firstParentTemplateDropdownValue=parentElements.get(i).findElements(By.xpath(OR.getProperty("TestPlan_ParentSections_ParentTemplate_dropdown_xpath"))).get(0).getText();
										String defaultFirstParentTemplateDDValue="PIPE";
										if (firstParentTemplateDropdownValue.equalsIgnoreCase(defaultFirstParentTemplateDDValue)) {
											fnpMymsg("First Parent Template drop down has default value i.e. '"+defaultFirstParentTemplateDDValue+"'.");
										} else {
											msg="First Parent Template drop down default value is not matched as actual is this '"+firstParentTemplateDropdownValue+"' and expected is this '"+defaultFirstParentTemplateDDValue+"'.";
											fnpMymsg(msg);
											throw new Exception(msg);
										}
									}
								}
								
								
							} else {
								msg="First parent name has been changed. Actual is this --'"+parentName+"' and expected is this --'"+TestPlan_FirstParentSectionName+"'.";
								fnpMymsg(msg);
								throw new Exception(msg);
							}
						}else{
							if (i==1) {
								if (parentName.equalsIgnoreCase(TestPlan_SecondParentSectionName)) {
									fnpMymsg("Second parent name is correct i.e. '"+parentName+"'. ");
									
									int parentTemplateDropDownCount=parentElements.get(i).findElements(By.xpath(OR.getProperty("TestPlan_ParentSections_ParentTemplate_dropdown_xpath"))).size();
									
									if (parentTemplateDropDownCount==0) {
										msg="Parent Template drop down is not present in second Parent Section.";
										fnpMymsg(msg);
										throw new Exception(msg);
									} else {
										if (parentTemplateDropDownCount>1) {
											msg="There are multiple ("+parentTemplateDropDownCount+") Parent Template drop downs are  present in second Parent Section and only 1 should be present";
											fnpMymsg(msg);
											throw new Exception(msg);
										} else {
											String secondParentTemplateDropdownValue=parentElements.get(i).findElements(By.xpath(OR.getProperty("TestPlan_ParentSections_ParentTemplate_dropdown_xpath"))).get(0).getText();
											String defaultSecondParentTemplateDDValue="LCV";
											if (secondParentTemplateDropdownValue.equalsIgnoreCase(defaultSecondParentTemplateDDValue)) {
												fnpMymsg("Second Parent Template drop down has default value i.e. '"+defaultSecondParentTemplateDDValue+"'.");
											} else {
												msg="Second Parent Template drop down default value is not matched as actual is this '"+secondParentTemplateDropdownValue+"' and expected is this '"+defaultSecondParentTemplateDDValue+"'.";
												fnpMymsg(msg);
												throw new Exception(msg);
											}
										}
									}
									
									
									
								} else {
									msg="Second parent name has been changed. Actual is this --'"+parentName+"' and expected is this --'"+TestPlan_SecondParentSectionName+"'.";
									fnpMymsg(msg);
									throw new Exception(msg);
								}
							}
						}
						
					}
				}
				
				
				
				if (fnpCheckElementDisplayedImmediately("TestPlan_ParentTemplate_CreateBtn")) {
					msg="Create button should no longer visible at this step but here it is still visible";
					fnpMymsg(msg);
					throw new Exception(msg);
				} else {
					fnpMymsg("Create button is no longer visible as expected.");
				}
				
				
				if (!(fnpCheckElementDisplayedImmediately("TestPlan_AddParents_Btn"))) {
					msg="Add Parents button should be visible at this step but here it is not visible";
					fnpMymsg(msg);
					throw new Exception(msg);
				} else {
					fnpMymsg("Add Parents button is visible.");
				}
				
				
				
				Thread.sleep(1);
				
				//Within the PIPE Parent, the following buttons are visible: 'Add Normalization Field', 'Add Progeny(s)', 'Update Parent Template'
				fnpWaitForVisible("TestPlan_FirstParentNormalization_AddNormalizationFieldBtn");
				fnpWaitForVisible("TestPlan_FirstParentNormalization_AddProgenyBtn");
				fnpWaitForVisible("TestPlan_FirstParentNormalization_UpdateParentTemplateBtn");

				
				
				WebElement firstChildOfFirstParent = fnpGetORObjectX("TestPlan_FirstParentNormalization_FirstChild_ProgencyAnalysesSection");
				//String firstChildExpandValue=firstChildOfFirstParent.findElement(By.xpath("./h3")).getAttribute("aria-expanded").trim();
				String firstChildExpandValue=firstChildOfFirstParent.findElement(By.xpath("./div[1]")).getAttribute("aria-expanded").trim();
				boolean firstChildExpanded=Boolean.parseBoolean(firstChildExpandValue);
				
				//#IPM 12434
/*				if (firstChildExpanded) {
					msg="Within the PIPE Parent, there should be one progeny section default collapsed but here it is not collapsed,hence failed.";
					fnpMymsg(msg);
					throw new Exception(msg);
				} else {
					fnpMymsg("Within the PIPE Parent, there is one progeny section default collapsed.");
				}
				
				firstChildOfFirstParent.findElement(By.xpath("./h3")).click();
				
				*/	
				
				if (firstChildExpanded) {
					fnpMymsg("Within the PIPE Parent, there is one progeny section default expanded.");

				} else {
					msg="Within the PIPE Parent, there should be one progeny section default expanded but here it is collapsed,hence failed.";
					fnpMymsg(msg);
					throw new Exception(msg);
				}
					
				
				
				
				
				fnpWorkAroundToClickbottomFooter();
				//fnpScroll(0,1000);

				Thread.sleep(2000);
				int valueCountInDropDown=fnpPFListModify_CountNoOfValuesInTheDropDown("TestPlan_SamplePrepCode_List");
				fnpMymsg("Total values in the Sample Prep Code drop down are --"+valueCountInDropDown);
				
				Assert.assertTrue(valueCountInDropDown>1, "According to the testcase, values in this Sample Prep Code drop down should be more than 1 but here values count is --"+valueCountInDropDown);
				
				/***********6. Within the PIPE Parent/Progeny 1, the sample prep code drop downs include various options , days field is visible with an edit icon, and the analytes section is visible with the option to add (+), look-up, and Import and the option to 'Add Progeny Exposure Notes' and 'Add analyst notes' are present.**********/
				fnpWaitForVisible("TestPlan_Days_field");
				fnpWaitForVisible("TestPlan_Days_field_editIcon");
				fnpWaitForVisible("TestPlan_AnalyteCode_firstTextBox");
				//fnpWaitForVisible("TestPlan_AnalyteDescription_firstTextBox");
				//fnpIfORElementDisplayed("TestPlan_AnalyteDescription_firstTextBox",10);
				if (!(fnpIfORElementDisplayed("TestPlan_AnalyteDescription_firstTextBox",10))) {
					msg="TestPlan_AnalyteDescription_firstTextBox is not visible.";
					fnpMymsg(msg);
					throw new Exception(msg);					
				}
				fnpWaitForVisible("TestPlan_AddAnalytePlusSign");
				fnpWaitForVisible("TestPlan_AnalyteLookupIcon");
				fnpWaitForVisible("TestPlan_AnalyteImportIcon");
				fnpWaitForVisible("TestPlan_AddProgenyExposureNotesLink");
				fnpWaitForVisible("TestPlan_AddAnalystNotesLink");
				/******************************************************************************************/
				
				
				fnpScrollToUp() ;
				/*******************7. HIstory links should be present in each parent and progeny section.********************************/
				fnpWaitForVisible("TestPlan_GeneralInformation_History_link");
				fnpWaitForVisible("TestPlan_Parent1Normalization_History_link");
				fnpScrollToBottom();
				fnpWaitForVisible("TestPlan_Progeny1Analyses_History_link");
				fnpWaitForVisible("TestPlan_Parent2_History_link");
				/***********************************************************************************************/
				
				
				/*****************8. within the LCV parent, the leaded component note text box should be present and the acid digestion only check box should be present and default to null.******/
				fnpWaitForVisible("TestPlan_LeadedComponentNotes_TxtArea");
				fnpWaitForVisible("TestPlan_AcidDigestionOnly_chkbx");
				
				String classOfAcidDigestionOnly=fnpGetAttribute_OR("TestPlan_AcidDigestionOnly_chkbx", "class");
				if (classOfAcidDigestionOnly.contains("ui-chkbox-icon ui-icon ui-icon-blank")) {
					fnpMymsg("Acid Digestion Only checkbox is blank.");
				} else {
					msg="Acid Digestion Only checkbox is not blank as its class attribute value is --'"+classOfAcidDigestionOnly+"' but expected class contains is --'ui-chkbox-icon ui-icon ui-icon-blank'";
					fnpMymsg(msg);
					throw new Exception(msg);
				}
				/*************************************************************************************************/
				
				fnpScrollToUp() ;
				
				
				
				
				/******************Exposure Notes***************************************************/
				
				/************1. Within the General Information Section, select the "Add Exposure Note" link*********/
				fnpClick_OR("TestPlan_AddExposureNote_link");
				fnpWaitForVisible("TestPlan_ExposureNotes_TxtArea");//A new field titled 'Exposure Notes' appears in the general information section.
				
				fnpClick_OR("TestPlan_SaveBtn");
				
				if (fnpCheckElementDisplayedImmediately("TestPlan_ExposureNotes_TxtArea")) {
					msg="According to the test case after clicking save button (without entering data in Exposure Notes), this Exposure Notes  textarea should not be visible.";
					fnpMymsg(msg);
					throw new Exception(msg);
				} else {
					msg="According to the test case after clicking save button (without entering data in Exposure Notes), this Exposure Notes  textarea is no longer visible, hence passed.";
					fnpMymsg(msg);
				}
				
				fnpClick_OR("TestPlan_AddExposureNote_link");
				 int noOfCharacterWeEntered = 4000;
				 WebElement wb = fnpGetORObjectX("TestPlan_ExposureNotes_TxtArea");
				for(int i=1;i<=1000;i++){//Enter text (up to 4,000 characters) in the exposure note field (include both alphanumeric & symbols) and select 'SAVE'
					wb.sendKeys("A1!@");
					//System.out.println("Value of i is---"+i);
					//fnpMymsg("For time being, later delete this --"+i);
				}
				
			//	fnpMymsg("Debug 1");
				// int exposureNotes_len = fnpGetORObjectX("TestPlan_ExposureNotes_TxtArea").getText().length();
				int exposureNotes_len = fnpGetORObjectX("TestPlan_ExposureNotes_TxtArea").getAttribute("value").length();
				if (exposureNotes_len!=noOfCharacterWeEntered) {
					//fnpMymsg("Actual characters are --'"+fnpGetORObjectX("TestPlan_ExposureNotes_TxtArea").getAttribute("value")+"'.");
					msg="Exposure Note text area does not accepting "+noOfCharacterWeEntered+" characters as we have entered "+noOfCharacterWeEntered+ " characters but "
							+ "it is accepting only "+exposureNotes_len+" characters.";
					fnpMymsg(msg);
					throw new Exception(msg);											
				}
				
			//	fnpMymsg("Debug 2");
				fnpClick_OR("TestPlan_SaveBtn");
				String tagName=fnpGetORObjectX("TestPlan_AddExposureNote_Withoutlink").getTagName();
				if (tagName.equalsIgnoreCase("a")) {
					msg="The AddExposureNote hyperlink should no longer be enabled.";
					fnpMymsg(msg);
					throw new Exception(msg);
				} else {
					msg="The AddExposureNote hyperlink is no longer enabled.";
					fnpMymsg(msg);
				}
				
			//	fnpMymsg("Debug 3");
				exposureNotes_len=fnpGetORObjectX("TestPlan_ExposureNotes_TxtArea").getText().length();
				if (exposureNotes_len!=noOfCharacterWeEntered) {
					//fnpMymsg("Actual characters (After click Save button) are --'"+fnpGetORObjectX("TestPlan_ExposureNotes_TxtArea").getText()+"'.");
					msg="After click Save button Exposure Note text area does not have "+noOfCharacterWeEntered+" characters as we have entered "+noOfCharacterWeEntered+ "characters but "
							+ "it has only "+exposureNotes_len+" characters after saving.";
					fnpMymsg(msg);
					throw new Exception(msg);											
				}
				
				
				fnpClick_OR("EditEpst_TasksTabLink");
				fnpClick_OR("EditEpst_TestPlan_TabLink");
				exposureNotes_len=fnpGetORObjectX("TestPlan_ExposureNotes_TxtArea").getText().length();
				if (exposureNotes_len!=noOfCharacterWeEntered) {
					//fnpMymsg("Actual characters (After switching tab change) are --'"+fnpGetORObjectX("TestPlan_ExposureNotes_TxtArea").getText()+"'.");
					msg="After switching tab change, Exposure Note text area does not have "+noOfCharacterWeEntered+" characters as we have entered "+noOfCharacterWeEntered+ " characters but "
							+ "it has only "+exposureNotes_len+" characters after saving.";
					fnpMymsg(msg);
					throw new Exception(msg);											
				}
				
				
				/***************************************************************************************************/
				
				
				/******************Toxicology Notes***************************************************/
				fnpClick_OR("TestPlan_AddToxicologyNote_Link");
				fnpWaitForVisible("TestPlan_AddToxicologyNote_TxtArea");//A new field titled 'Add Toxicology Note' appears in the general information section.				
				fnpClick_OR("TestPlan_SaveBtn");
				
				if (fnpCheckElementDisplayedImmediately("TestPlan_AddToxicologyNote_TxtArea")) {
					msg="According to the test case after clicking save button (without entering data in Add Toxicology Note), this Add Toxicology Note  textarea should not be visible.";
					fnpMymsg(msg);
					throw new Exception(msg);
				} else {
					msg="According to the test case after clicking save button (without entering data in Add Toxicology Note), this Add Toxicology Note  textarea is no longer visible, hence passed.";
					fnpMymsg(msg);
				}
								
				fnpClick_OR("TestPlan_AddToxicologyNote_Link");
				
				noOfCharacterWeEntered=4000;
				 wb=fnpGetORObjectX("TestPlan_AddToxicologyNote_TxtArea");
				for(int i=1;i<=1000;i++){//Enter text (up to 4,000 characters) in the  Toxicology Note field (include both alphanumeric & symbols) and select 'SAVE'
					wb.sendKeys("A1!@");
				}
				
				//int toxicologyNotes_len=fnpGetORObjectX("TestPlan_AddToxicologyNote_TxtArea").getText().length();
				int toxicologyNotes_len = fnpGetORObjectX("TestPlan_AddToxicologyNote_TxtArea").getAttribute("value").length();
				if (toxicologyNotes_len!=noOfCharacterWeEntered) {
					//fnpMymsg("Actual characters are --'"+fnpGetORObjectX("TestPlan_AddToxicologyNote_TxtArea").getText()+"'.");
					msg="Toxicology Note text area does not accepting "+noOfCharacterWeEntered+" characters as we have entered "+noOfCharacterWeEntered+ " characters but "
							+ "it is accepting only "+toxicologyNotes_len+" characters.";
					fnpMymsg(msg);
					throw new Exception(msg);											
				}
				
				fnpClick_OR("TestPlan_SaveBtn");
				tagName=fnpGetORObjectX("TestPlan_AddToxicologyNote_Withoutlink").getTagName();
				if (tagName.equalsIgnoreCase("a")) {
					msg="The AddToxicologyNote hyperlink should no longer be enabled.";
					fnpMymsg(msg);
					throw new Exception(msg);
				} else {
					msg="The AddToxicologyNote hyperlink is no longer enabled.";
					fnpMymsg(msg);
				}
								
				
				 toxicologyNotes_len=fnpGetORObjectX("TestPlan_AddToxicologyNote_TxtArea").getText().length();
				if (toxicologyNotes_len!=noOfCharacterWeEntered) {
					//fnpMymsg("Actual characters (After click Save button) are --'"+fnpGetORObjectX("TestPlan_AddToxicologyNote_TxtArea").getText()+"'.");
					msg="After click Save button Toxicology Note text area does not have "+noOfCharacterWeEntered+" characters as we have entered "+noOfCharacterWeEntered+ " characters but "
							+ "it has only "+toxicologyNotes_len+" characters after saving.";
					fnpMymsg(msg);
					throw new Exception(msg);											
				}
				
				
				fnpClick_OR("EditEpst_TasksTabLink");
				fnpClick_OR("EditEpst_TestPlan_TabLink");
				toxicologyNotes_len=fnpGetORObjectX("TestPlan_AddToxicologyNote_TxtArea").getText().length();
				if (toxicologyNotes_len!=noOfCharacterWeEntered) {
					//fnpMymsg("Actual characters (After switching tab change) are --'"+fnpGetORObjectX("TestPlan_AddToxicologyNote_TxtArea").getText()+"'.");
					msg="After switching tab change, Toxicology Note text area does not have "+noOfCharacterWeEntered+" characters as we have entered "+noOfCharacterWeEntered+ " characters but "
							+ "it has only "+toxicologyNotes_len+" characters after saving.";
					fnpMymsg(msg);
					throw new Exception(msg);											
				}
				
				
				/*********************************************************************/
				
				
				
				
				/************Select "Required Tox Eval?" and 'RVCM' checkbox and select 'SAVE'*****************/
				/************No error message should be received.  Upon exiting the test plan and re-entering, the checkbox selection has been saved*************************************/
				fnpClick_OR("TestPlanTab_RequiredToxEvalCheckBx");
				fnpClick_OR("TestPlanTab_RVCMCheckBx");
				fnpClick_OR("TestPlan_SaveBtn");
				fnpClick_OR("EditEpst_TasksTabLink");
				fnpClick_OR("EditEpst_TestPlan_TabLink");
				
				classOfRequiredToxEval=fnpGetAttribute_OR("TestPlanTab_RequiredToxEvalCheckBx", "class");
				if (classOfRequiredToxEval.contains("ui-icon-blank")) {
					msg="Required Tox Eval checkbox checked should be saved but it is not saved.";
					fnpMymsg(msg);	
					throw new Exception(msg);
				}else{
					if (classOfRequiredToxEval.contains("ui-icon-check")) {//class for checked checkbox
						msg="Required Tox Eval checkbox checked is saved successfully.";
						fnpMymsg(msg);
					} else {
						msg="Required Tox Eval checkbox checked should be saved but it is not saved but its class also does not contain blank as well as check text.";
						fnpMymsg(msg);	
						throw new Exception(msg);
					}
					
				}
				
				classOfRVCM=fnpGetAttribute_OR("TestPlanTab_RVCMCheckBx", "class");
				if (classOfRVCM.contains("ui-icon-blank")) {
					msg="RVCM checkbox checked should be saved but it is not saved.";
					fnpMymsg(msg);	
					throw new Exception(msg);
				}else{
					if (classOfRVCM.contains("ui-icon-check")) {//class for checked checkbox
						msg="RVCM checkbox checked is saved successfully.";
						fnpMymsg(msg);
					} else {
						msg="RVCMcheckbox checked should be saved but it is not saved and its class also does not contain blank as well as check text.";
						fnpMymsg(msg);	
						throw new Exception(msg);
					}
					
				}
				

				/*************************************************************************************************/
				
			
				
				
				/************UnSelect "Required Tox Eval?" and 'RVCM' checkbox and select 'SAVE'*****************/
				/************No error message should be received.  Upon exiting the test plan and re-entering, the checkbox selection has been saved*************************************/
				fnpClick_OR("TestPlanTab_RequiredToxEvalCheckBx");
				fnpClick_OR("TestPlanTab_RVCMCheckBx");
				fnpClick_OR("TestPlan_SaveBtn");
				fnpClick_OR("EditEpst_TasksTabLink");
				fnpClick_OR("EditEpst_TestPlan_TabLink");
				
				classOfRequiredToxEval=fnpGetAttribute_OR("TestPlanTab_RequiredToxEvalCheckBx", "class");
				if (classOfRequiredToxEval.contains("ui-icon-blank")) {
					msg="Required Tox Eval  checkbox unchecked is saved successfully.";
					fnpMymsg(msg);	
					//throw new Exception(msg);
				}else{
					if (classOfRequiredToxEval.contains("ui-icon-check")) {//class for checked checkbox
						msg="Required Tox Eval checkbox unchecked should be saved but it is not saved.";
						fnpMymsg(msg);
						throw new Exception(msg);
					} else {
						msg="Required Tox Eval checkbox unchecked should be saved but it is not saved and its class also does not contain blank as well as check text.";
						fnpMymsg(msg);	
						throw new Exception(msg);
					}
					
				}
				
				classOfRVCM=fnpGetAttribute_OR("TestPlanTab_RVCMCheckBx", "class");
				if (classOfRVCM.contains("ui-icon-blank")) {
					msg="RVCM  checkbox unchecked is saved successfully.";
					fnpMymsg(msg);	
					//throw new Exception(msg);
				}else{
					if (classOfRVCM.contains("ui-icon-check")) {//class for checked checkbox
						msg="RVCM checkbox unchecked should be saved but it is not saved.";
						fnpMymsg(msg);
						throw new Exception(msg);
					} else {
						msg="RVCM checkbox unchecked should be saved but it is not saved and its class also does not contain blank as well as check text.";
						fnpMymsg(msg);	
						throw new Exception(msg);
					}
					
				}
				

				/*************************************************************************************************/
				
				
			fnpClick_OR("TestPlan_AddParents_Btn");
			if (!(fnpIfORElementDisplayed("TestPlan_Parent3Normalization_Header_xpath",10))) {
				msg="Parent 3 Normalization is not visible after clicking Add Parents button";
				fnpMymsg(msg);
				throw new Exception(msg);					
			}else{
				msg="Parent 3 Normalization is  visible after clicking Add Parents button";
				fnpMymsg(msg);
				String parent3Title=fnpGetText_OR("TestPlan_Parent3Normalization_Header_xpath");
				String expectedTitle=TestPlan_ThirdParentSectionName;
				if (parent3Title.equals(expectedTitle)) {
					msg=" and Parent 3 Normalization's titile is --'"+expectedTitle+"'.";
					fnpMymsg(msg);
				} else {
					msg=" and Parent 3 Normalization's titile should have  --'"+expectedTitle+"'but actual it is coming --'"+parent3Title+"'.";
					fnpMymsg(msg);
					throw new Exception(msg);
				}
			}
				

			fnpPFList("TestPlan_Parent3Normalization_List", "TestPlan_Parent3Normalization_ListOptions", "LCV (X0172)");	
			//fnpClick_OR("TestPlan_Parent3Normalization_Create_Button");	
			
			fnpGetORObjectX("TestPlan_Parent3Normalization_Create_Button").click();
			fnpLoading_wait_withoutErrorChecking() ;
			fnpWaitingForExpectedErrorMsg(errorMsgOnSavingWithoutSelectingParentTemplate);
			fnpMouseHover_OR_EvenMultipleObjectsHavingSameXpath_usingSpecialForErrorClassOnly("ErrorMessage");
			 msg = fnpGetText_OR_EvenMultipleObjectsHavingSameXpath_specialForExtractingErrorMsgTextOnlyEvenIfNotVisible("ErrorMessage");

			fnpMymsg("Top Message is coming on clicking Create button after selecting LCV (X0172) in Parent-3 Normaalization  ----" + msg);
			Assert.assertEquals(
					msg,
					errorMsgOnselectLCVX0172andClickCreateBtn,
					"Top message does not contain this error message '"+errorMsgOnselectLCVX0172andClickCreateBtn+"'  on on clicking Create button after selecting LCV (X0172) in Parent-3 Normaalization.");					

/*			
			fnpPFList("TestPlan_Parent3Normalization_List", "TestPlan_Parent3Normalization_ListOptions", "PIPE-MTL (L0010)");
			fnpClick_OR("TestPlan_Parent3Normalization_Create_Button");
			*/
			fnpPFListModify_usingWhenIgnoringErrorOnSelectingValues( "TestPlan_Parent3Normalization_List",  "PIPE-MTL (L0010)") ;	
			fnpLoading_wait_withoutErrorChecking() ;
			fnpGetORObjectX("TestPlan_Parent3Normalization_Create_Button").click();
			fnpLoading_wait_withoutErrorChecking() ;
			fnpCheckError("");
			
			
			
			parentElements = fnpGetORObject_list_NOR(OR.getProperty("TestPlan_ParentSections_Header_xpath"), 10);
			parentElementSize=parentElements.size();
			if (parentElementSize!=3) {
				msg="According to the test case there should be 3 Parent Sections but here parent sections are --"+parentElementSize;
				fnpMymsg(msg);
				throw new Exception(msg);
				
			} else {
				fnpMymsg("3 Parent Sections are present.");
				String parentName;
				for (int i = 0; i < parentElementSize; i++) {
					//parentName=parentElements.get(i).findElement(By.xpath("./h3")).getText().trim();
					parentName=parentElements.get(i).findElement(By.xpath("./div[1]")).getText().trim();
					if (i==0) {
						if (parentName.equalsIgnoreCase(TestPlan_FirstParentSectionName)) {
							fnpMymsg("First parent name is correct i.e. '"+parentName+"'. ");
							
							int parentTemplateDropDownCount=parentElements.get(i).findElements(By.xpath(OR.getProperty("TestPlan_ParentSections_ParentTemplate_dropdown_xpath"))).size();
							
							if (parentTemplateDropDownCount==0) {
								msg="Parent Template drop down is not present in First Parent Section.";
								fnpMymsg(msg);
								throw new Exception(msg);
							} else {
								if (parentTemplateDropDownCount>1) {
									msg="There are multiple ("+parentTemplateDropDownCount+") Parent Template drop downs are  present in First Parent Section and only 1 should be present";
									fnpMymsg(msg);
									throw new Exception(msg);
								} else {
									String firstParentTemplateDropdownValue=parentElements.get(i).findElements(By.xpath(OR.getProperty("TestPlan_ParentSections_ParentTemplate_dropdown_xpath"))).get(0).getText();
									String defaultFirstParentTemplateDDValue="PIPE";
									if (firstParentTemplateDropdownValue.equalsIgnoreCase(defaultFirstParentTemplateDDValue)) {
										fnpMymsg("First Parent Template drop down has default value i.e. '"+defaultFirstParentTemplateDDValue+"'.");
									} else {
										msg="First Parent Template drop down default value is not matched as actual is this '"+firstParentTemplateDropdownValue+"' and expected is this '"+defaultFirstParentTemplateDDValue+"'.";
										fnpMymsg(msg);
										throw new Exception(msg);
									}
								}
							}
							
							
						} else {
							msg="First parent name has been changed. Actual is this --'"+parentName+"' and expected is this --'"+TestPlan_FirstParentSectionName+"'.";
							fnpMymsg(msg);
							throw new Exception(msg);
						}
					}else{
						if (i==1) {//Now second becomes 3rd and 3rd becomes Second Parent Section
							if (parentName.equalsIgnoreCase(TestPlan_ThirdParentSection_become_SecondParentSection_Name)) {
								fnpMymsg("Second parent name is correct i.e. '"+parentName+"'. ");
								
								//WebElement secondparent = parentElements.get(i).findElement(By.xpath("./h3"));
								WebElement secondparent = parentElements.get(i).findElement(By.xpath("./div[1]"));
								String color = secondparent.getCssValue("background-color");//worked
								String colorXexaValue=fnpReturnHexaCode( color);
								fnpMymsg("Color hexa code is "+colorXexaValue);
								String expectedGreenHexaColorCode="#4b710c";
								if (colorXexaValue.equals(expectedGreenHexaColorCode)) {
									msg="The PIPE-MTL parent should now be Parent-2 Noramlization, the section color is green.";
									fnpMymsg(msg);		
								} else {
									msg="The PIPE-MTL parent should now be Parent-2 Noramlization, the section color should be green i.e. '"+expectedGreenHexaColorCode+"' but it is coming --'"+colorXexaValue+"'.";
									fnpMymsg(msg);
									throw new Exception(msg);
								}
								
								int parentTemplateDropDownCount=parentElements.get(i).findElements(By.xpath(OR.getProperty("TestPlan_ParentSections_ParentTemplate_dropdown_xpath"))).size();
								
								if (parentTemplateDropDownCount==0) {
									msg="Parent Template drop down is not present in second Parent Section.";
									fnpMymsg(msg);
									throw new Exception(msg);
								} else {
									if (parentTemplateDropDownCount>1) {
										msg="There are multiple ("+parentTemplateDropDownCount+") Parent Template drop downs are  present in second Parent Section and only 1 should be present";
										fnpMymsg(msg);
										throw new Exception(msg);
									} else {
										String secondParentTemplateDropdownValue=parentElements.get(i).findElements(By.xpath(OR.getProperty("TestPlan_ParentSections_ParentTemplate_dropdown_xpath"))).get(0).getText();
										String defaultSecondParentTemplateDDValue="PIPE-MTL";
										if (secondParentTemplateDropdownValue.equalsIgnoreCase(defaultSecondParentTemplateDDValue)) {
											fnpMymsg("Second Parent Template drop down has default value i.e. '"+defaultSecondParentTemplateDDValue+"'.");
										} else {
											msg="Second Parent Template drop down default value is not matched as actual is this '"+secondParentTemplateDropdownValue+"' and expected is this '"+defaultSecondParentTemplateDDValue+"'.";
											fnpMymsg(msg);
											throw new Exception(msg);
										}
									}
								}
								
								
								
							} else {
								msg="Second parent name has been changed. Actual is this --'"+parentName+"' and expected is this --'"+TestPlan_ThirdParentSection_become_SecondParentSection_Name+"'.";
								fnpMymsg(msg);
								throw new Exception(msg);
							}
						}
						if (i==2) {//Now second becomes 3rd and 3rd becomes Second Parent Section
							if (parentName.equalsIgnoreCase(TestPlan_SecondParentSection_become_ThirdParentSection_Name)) {
								fnpMymsg("Third parent name is correct i.e. '"+parentName+"'. ");
								
								int parentTemplateDropDownCount=parentElements.get(i).findElements(By.xpath(OR.getProperty("TestPlan_ParentSections_ParentTemplate_dropdown_xpath"))).size();
								
								if (parentTemplateDropDownCount==0) {
									msg="Parent Template drop down is not present in third Parent Section.";
									fnpMymsg(msg);
									throw new Exception(msg);
								} else {
									if (parentTemplateDropDownCount>1) {
										msg="There are multiple ("+parentTemplateDropDownCount+") Parent Template drop downs are  present in third Parent Section and only 1 should be present";
										fnpMymsg(msg);
										throw new Exception(msg);
									} else {
										String secondParentTemplateDropdownValue=parentElements.get(i).findElements(By.xpath(OR.getProperty("TestPlan_ParentSections_ParentTemplate_dropdown_xpath"))).get(0).getText();
										String defaultSecondParentTemplateDDValue="LCV";
										if (secondParentTemplateDropdownValue.equalsIgnoreCase(defaultSecondParentTemplateDDValue)) {
											fnpMymsg("Third Parent Template drop down has default value i.e. '"+defaultSecondParentTemplateDDValue+"'.");
										} else {
											msg="Third Parent Template drop down default value is not matched as actual is this '"+secondParentTemplateDropdownValue+"' and expected is this '"+defaultSecondParentTemplateDDValue+"'.";
											fnpMymsg(msg);
											throw new Exception(msg);
										}
									}
								}
								
								
								
							} else {
								msg="Third parent name has been changed. Actual is this --'"+parentName+"' and expected is this --'"+TestPlan_SecondParentSection_become_ThirdParentSection_Name+"'.";
								fnpMymsg(msg);
								throw new Exception(msg);
							}
						}
					}
					
				}
			}
			
			
			

			
				
		
								
			fnpCheckFieldIsAcceptingNoOfCharsAndSaveEvenAfterTabChange(4000,OR.getProperty("TestPlan_LeadedComponentNotes_TxtArea_afterChangedTo3rdPosition"),
					"Lead Component Notes",OR.getProperty("TestPlan_SaveBtn"),OR.getProperty("EditEpst_TestPlan_TabLink"),OR.getProperty("EditEpst_TasksTabLink"));
				
			
			
			/*************No error message is received. Progeny #2 section appears directly below Progeny #1 and is default expanded.
			 *  The Sample Prep Code defaults to 'Please Select', the Days section is visible and the edit icon is present. 
			 *  The analytes section is visible and all fields are defaulted to null.  The 'Add Progeny Exposure Notes' and 'Add  Analyst Notes' are present.
			 */

			
			fnpClick_OR("TestPlan_FirstParentNormalization_AddProgenyBtn");
			fnpClick_OR("TestPlan_SaveBtn");
			
			//Progeny #2 section appears directly below Progeny #1 
			fnpWaitForVisible("TestPlan_Parent1Normalization_Progeny2_Header_xpath");
			
			if (fnpCheckElementDisplayedImmediately("TestPlan_Parent1Normalization_Progeny2_SamplePrepCode_List")) {
				fnpMymsg("Parent1 Normalization_Progeny2 section is default expanded .");
			} else {
				msg="Parent1 Normalization_Progeny2 section is NOT expanded default and according to the testcase, it should have expanded bydefault.";
				fnpMymsg(msg);
				throw new Exception(msg);
				//fnpClick_OR("TestPlan_Parent1Normalization_Progeny2_Header_xpath");
			}
			
			fnpMouseHover("TestPlan_Parent1Normalization_Progeny2_SamplePrepCode_List");
			String expectedDefaultValueInProgeny2="Please Select";//"pH 10 CL 23ºC (S0017)";
			String defaultPrepCodeValue=fnpGetText_OR("TestPlan_Parent1Normalization_Progeny2_SamplePrepCode_List");
			Assert.assertTrue(defaultPrepCodeValue.equalsIgnoreCase(expectedDefaultValueInProgeny2), "Default value in Parent-1 Progeny-2 Sample Prep Code should have '"+expectedDefaultValueInProgeny2+"' but actual is -- "+defaultPrepCodeValue);
			
			
			
			fnpWaitForVisible("TestPlan_Parent1Normalization_Progeny2_Days_field");
			fnpWaitForVisible("TestPlan_Parent1Normalization_Progeny2_Days_field_editIcon");
			fnpWaitForVisible("TestPlan_Parent1Normalization_Progeny2_AnalyteCode_firstTextBox");

			if (!(fnpIfORElementDisplayed("TestPlan_Parent1Normalization_Progeny2_AnalyteDescription_firstTextBox",10))) {
				msg="TestPlan_Parent1Normalization_Progeny2_AnalyteDescription_firstTextBox is not visible.";
				fnpMymsg(msg);
				throw new Exception(msg);					
			}
			fnpWaitForVisible("TestPlan_Parent1Normalization_Progeny2_AddAnalytePlusSign");
			fnpWaitForVisible("TestPlan_Parent1Normalization_Progeny2_AnalyteLookupIcon");//extra
			fnpWaitForVisible("TestPlan_Parent1Normalization_Progeny2_AnalyteImportIcon");//extra
			fnpWaitForVisible("TestPlan_Parent1Normalization_Progeny2_AddProgenyExposureNotesLink");
			fnpWaitForVisible("TestPlan_Parent1Normalization_Progeny2_AddAnalystNotesLink");
			
			
			/******************************************************************************************/
			
			
			
			
			
			/****************Update the status to 'REVIEWED' and select 'Save'
			 * The user should receive the following error message: 'Sample Prep Code not selected for Progeny #1. Please select a code.'
			 */
			changeStatus="Reviewed";	
			fnpDisplayingMessageInFrame("Changing status to "+changeStatus, 10);
			fnpPFListModify_usingWhenIgnoringErrorOnSelectingValues( "TestPlan_Status_List",  changeStatus) ;
			fnpDisplayingMessageInFrame("waiting 10 seconds wait for loading the below sections on changing status to "+changeStatus, 10);
			Thread.sleep(10000);
			fnpLoading_wait_withoutErrorChecking() ;
			fnpLoading_wait_withoutErrorChecking() ;
			fnpDisplayingMessageInFrame("Saving status "+changeStatus, 10);
			fnpGetORObjectX("EPSF_SaveBtn").click();
			fnpLoading_wait_withoutErrorChecking() ;
			fnpWaitingForExpectedErrorMsg(errorMsg_SamplePrepCodenotselectedforProgeny1);
			fnpMouseHover_OR_EvenMultipleObjectsHavingSameXpath_usingSpecialForErrorClassOnly("ErrorMessage");
			 msg = fnpGetText_OR_EvenMultipleObjectsHavingSameXpath_specialForExtractingErrorMsgTextOnlyEvenIfNotVisible("ErrorMessage");

			fnpMymsg("Top Message is coming on saving the status '"+changeStatus+"'   ----" + msg);
			Assert.assertEquals(
					msg,
					errorMsg_SamplePrepCodenotselectedforProgeny1,
					"Top message does not contain this error message '"+errorMsg_SamplePrepCodenotselectedforProgeny1+"'  on saving Test Plan  status '"+changeStatus+"'.");					

			
			/*************************************************************************************/
			
			
			
			
			
			
			
			/****************Update the status to 'APPROVED' and select 'Save'
			 * The user should receive the following error message: 'Sample Prep Code not selected for Progeny #1. Please select a code.'
			 */
			changeStatus="APPROVED";	
			fnpDisplayingMessageInFrame("Changing status to "+changeStatus, 10);
			fnpPFListModify_usingWhenIgnoringErrorOnSelectingValues( "TestPlan_Status_List",  changeStatus) ;
			fnpDisplayingMessageInFrame("waiting 10 seconds wait for loading the below sections on changing status to "+changeStatus, 10);
			Thread.sleep(10000);
			fnpLoading_wait_withoutErrorChecking() ;
			fnpLoading_wait_withoutErrorChecking() ;
			fnpDisplayingMessageInFrame("Saving status "+changeStatus, 10);
			fnpGetORObjectX("EPSF_SaveBtn").click();
			fnpLoading_wait_withoutErrorChecking() ;
			fnpWaitingForExpectedErrorMsg(errorMsg_SamplePrepCodenotselectedforProgeny1);
			fnpMouseHover_OR_EvenMultipleObjectsHavingSameXpath_usingSpecialForErrorClassOnly("ErrorMessage");
			 msg = fnpGetText_OR_EvenMultipleObjectsHavingSameXpath_specialForExtractingErrorMsgTextOnlyEvenIfNotVisible("ErrorMessage");

			fnpMymsg("Top Message is coming on saving the status '"+changeStatus+"'   ----" + msg);
			Assert.assertEquals(
					msg,
					errorMsg_SamplePrepCodenotselectedforProgeny1,
					"Top message does not contain this error message '"+errorMsg_SamplePrepCodenotselectedforProgeny1+"'  on saving Test Plan  status '"+changeStatus+"'.");					

			
			/*************************************************************************************/
			
			
			changeStatus="DRAFT";
			fnpDisplayingMessageInFrame("Changing status to "+changeStatus, 10);
			fnpPFListModify_usingWhenIgnoringErrorOnSelectingValues( "TestPlan_Status_List",  changeStatus) ;
			fnpDisplayingMessageInFrame("waiting 10 seconds wait for loading the below sections on changing status to "+changeStatus, 10);
			Thread.sleep(10000);
			fnpLoading_wait_withoutErrorChecking() ;
			fnpLoading_wait_withoutErrorChecking() ;
			//fnpClick_OR("TestPlan_Parent1Normalization_Progeny2_Header_xpath");
			
			//by default opened
/*			fnpDisplayingMessageInFrame("Going to click Progeny1", 10);
			fnpGetORObjectX("TestPlan_Parent1Normalization_Progeny1_Header_xpath").click();
			fnpDisplayingMessageInFrame("Clicked Progeny1", 10);
			*/
			
			fnpGetORObjectX("TestPlan_Parent1Normalization_Progeny1_Days_field_editIcon").click();
			fnpWaitForVisible("TestPlan_EditAnalysisDay_popup");
			
			int dayColIndex = fnpFindColumnIndex("TestPlan_EditAnalysisDayTable_HeaderRow", "Day");
			int criticalColIndex = fnpFindColumnIndex("TestPlan_EditAnalysisDayTable_HeaderRow", "Critical");
			//int sampleSelectionrowNo = fnpFindRow("TasksTable_EditWO", taskType_SampleSelection, TaskTypeColIndex);
			//String sampleSelectionTaskStatus_TaskTable = fnpFetchFromTable("TasksTable_EditWO", sampleSelectionrowNo, StatusColIndex);
			
			fnpWaitForVisible("TestPlan_EditAnalysisDay_popup_AddCustomDaysLink");
			fnpWaitForVisible("TestPlan_EditAnalysisDay_popup_ModifyBtn");
			fnpWaitForVisible("TestPlan_EditAnalysisDay_popup_CancelLink");
			
			fnpCheckAllRowsHaveCheckboxColumns("TestPlan_EditAnalysisDayTable_data",OR.getProperty("TestPlan_EditAnalysisDayTable_data"), 1) ;
			fnpCheckAllRowsHaveCheckboxColumns("TestPlan_EditAnalysisDayTable_data",OR.getProperty("TestPlan_EditAnalysisDayTable_data"), criticalColIndex) ;
			
			//. Select 1, 17, and 107
			fnpCheckCheckbox_basedOnDayData( 1, 1);
			fnpCheckCheckbox_basedOnDayData( 17, 1);
			fnpCheckCheckbox_basedOnDayData( 107, 1);
			fnpGetORObjectX("TestPlan_EditAnalysisDay_popup_ModifyBtn").click();
			fnpLoading_wait_withoutErrorChecking() ;
			fnpWaitingForExpectedErrorMsg(errorMsg_forCriticalDay);
			fnpMouseHover_OR_EvenMultipleObjectsHavingSameXpath_usingSpecialForErrorClassOnly("ErrorMessage");
			 msg = fnpGetText_OR_EvenMultipleObjectsHavingSameXpath_specialForExtractingErrorMsgTextOnlyEvenIfNotVisible("ErrorMessage");

			fnpMymsg("Top Message is coming on saving the status '"+changeStatus+"'   ----" + msg);
			Assert.assertEquals(
					msg,
					errorMsg_forCriticalDay,
					"Top message does not contain this error message '"+errorMsg_forCriticalDay+"'.");					

			fnpCheckCheckbox_basedOnDayData( 17, criticalColIndex);
			fnpGetORObjectX("TestPlan_EditAnalysisDay_popup_ModifyBtn").click();
			fnpLoading_wait();
			fnpClick_OR("EPSF_SaveBtn");
			
			//ArrayList<Integer> daysList=new ArrayList<Integer>();
		    ArrayList<Integer> daysList = new ArrayList<Integer>( 
		               Arrays.asList(1, 
		                             17, 
		                             107)); 
		    ArrayList<Integer> criticalDaysList = new ArrayList<Integer>( 
		               Arrays.asList(17)); 
			fnpCheckIsDaysCriticalIn_ascendingOrder_and_its_color(daysList,criticalDaysList);
			
			
			fnpGetORObjectX("TestPlan_Parent1Normalization_Progeny1_Days_field_editIcon").click();
			fnpWaitForVisible("TestPlan_EditAnalysisDay_popup");
			
			String xpathRow = OR.getProperty("TestPlan_EditAnalysisDayTable_data") + "/tr";
			int totalRowCount_beforeClickingAddCustomDaysLink = driver.findElements(By.xpath(xpathRow)).size();			
			fnpClick_OR("TestPlan_Progeny_1_EditDaysPopup_AddCustomDaysLink");			
			int totalRowCount_AfterClickingAddCustomDaysLink = driver.findElements(By.xpath(xpathRow)).size();
			if ((totalRowCount_beforeClickingAddCustomDaysLink+1)==totalRowCount_AfterClickingAddCustomDaysLink) {
				fnpMymsg("A new row is appear after clicking Add Custom days link. ");
			} else {
				msg="A new row should appear after clicking Add Custom days link, but it is not happening because before clicking no. of rows"+
						" were '"+totalRowCount_beforeClickingAddCustomDaysLink+"' and after clicking no. of rows are '"+totalRowCount_AfterClickingAddCustomDaysLink+"'.";
				fnpMymsg(msg);
				throw new Exception(msg);
			}
			
			
			String xpathDays_forLastRow_forDayInputBx = OR.getProperty("TestPlan_EditAnalysisDayTable_data") + "/tr["+totalRowCount_AfterClickingAddCustomDaysLink+"]/td[2]/input";
			String value_forLastRow_forDayInputBx=driver.findElement(By.xpath(xpathDays_forLastRow_forDayInputBx)).getAttribute("value").trim();
			int value_forLastRow_forDayInputBx_int=Integer.parseInt(value_forLastRow_forDayInputBx);
			if (value_forLastRow_forDayInputBx_int==0) {
				fnpMymsg("Day value in last row is bydefault 0. ");
			} else {
				msg="Day value in last row (after clicking Add Custom Days link) should be default 0 but here its value is '"+value_forLastRow_forDayInputBx_int+"'.";
				fnpMymsg(msg);
				throw new Exception(msg);
			}
			
			//fnpCheckCheckbox_basedOnDayData( 0, 1);
			fnpGetORObjectX___NOR(xpathDays_forLastRow_forDayInputBx).clear();
			fnpGetORObjectX___NOR(xpathDays_forLastRow_forDayInputBx).sendKeys((String) hashXlData.get("Newly_added_row_day").trim());
			String xpathExpression_forCheckbox = OR.getProperty("TestPlan_EditAnalysisDayTable_data") + "/tr[" + totalRowCount_AfterClickingAddCustomDaysLink + "]/td[1]/div//span[contains(@class,'ui-chkbox-icon')]";
			fnpGetORObjectX___NOR(xpathExpression_forCheckbox).click();
			
			
			
/*			String xpathDays_forFirstRow_forDayInputBx = OR.getProperty("TestPlan_EditAnalysisDayTable_data") + "/tr[1]/td[2]/input";
			String value_forFirstRow_forDayInputBx=driver.findElement(By.xpath(xpathDays_forFirstRow_forDayInputBx)).getAttribute("value").trim();
			int value_forFirstRow_forDayInputBx_int=Integer.parseInt(value_forFirstRow_forDayInputBx);
			if (value_forFirstRow_forDayInputBx_int==0) {
				fnpMymsg("Day value in first row is bydefault 0. ");
			} else {
				msg="Day value in first row (after clicking Add Custom Days link) should be default 0 but here its value is '"+value_forFirstRow_forDayInputBx_int+"'.";
				fnpMymsg(msg);
				throw new Exception(msg);
			}
			
			//fnpCheckCheckbox_basedOnDayData( 0, 1);
			fnpGetORObjectX___NOR(xpathDays_forFirstRow_forDayInputBx).clear();
			fnpGetORObjectX___NOR(xpathDays_forFirstRow_forDayInputBx).sendKeys((String) hashXlData.get("Newly_added_row_day").trim());
			String xpathExpression_forCheckbox = OR.getProperty("TestPlan_EditAnalysisDayTable_data") + "/tr[1]/td[1]/div//span[contains(@class,'ui-chkbox-icon')]";
			fnpGetORObjectX___NOR(xpathExpression_forCheckbox).click();
			
			*/
			
			
			
			
			Thread.sleep(1000);
			fnpGetORObjectX("TestPlan_EditAnalysisDay_popup_ModifyBtn").click();
			fnpLoading_wait();
			fnpClick_OR("EPSF_SaveBtn");
			
			daysList.add(Integer.parseInt((String) hashXlData.get("Newly_added_row_day").trim()));
			  /* Collections.sort method is sorting the 
	        elements of ArrayList in ascending order. */
	        Collections.sort(daysList); 
	        fnpCheckIsDaysCriticalIn_ascendingOrder_and_its_color(daysList,criticalDaysList);
			
	        /***********TC_35  Within Parent-1 Normalization/Progeny 1, select the green ‘+’ button (Add Row)************/
	       // fnpScrollToBottom();
	        fnpScroll(0, 1000) ;
	        int noOfAnalytesRows_BeforeClickingPlusSign=fnpCountRowsInTable("TestPlan_Parent1Normalization_Progeny1_AnalytesTable_data_xpath");
	        fnpClick_OR("TestPlan_Parent1Normalization_Progeny1_AddAnalytePlusSign");
	        int noOfAnalytesRows_AfterClickingPlusSign=fnpCountRowsInTable("TestPlan_Parent1Normalization_Progeny1_AnalytesTable_data_xpath");
	        if ((noOfAnalytesRows_BeforeClickingPlusSign+1)==noOfAnalytesRows_AfterClickingPlusSign) {
				fnpMymsg("1 new row should be added after clicking Analytes Plus sign. ");
			} else {
				msg="1 new row should be added after clicking Analytes Plus sign but here it is not happening because before clicking "
						+ "no. of rows were '"+noOfAnalytesRows_BeforeClickingPlusSign+"' but after clicking no. of rows are '"+noOfAnalytesRows_AfterClickingPlusSign+"'.";
				fnpMymsg(msg);
				throw new Exception(msg);
						
			}
	        
	        String allInputBox_analytesSection_xpath = OR.getProperty("TestPlan_Parent1Normalization_Progeny1_AnalytesTable_data_xpath")+"/tr/td/input";
	        List<WebElement> wb1=driver.findElements(By.xpath(allInputBox_analytesSection_xpath));
	        int noOfInputBox=wb1.size();
	        String temp;
	        for (int i = 0; i <noOfInputBox; i++) {
				temp=wb1.get(i).getAttribute("value").trim();
				if (temp.equalsIgnoreCase("")){
					//
					
				}else{
					msg="  All Analytes text box should be blank but here they are not blank.";
					fnpMymsg(msg);
					throw new Exception(msg);
				}
				
				
			}
	        
	        
	        /**************************************************************************************************/
	        
	        
	        /**********Within Parent-1 Normalization/Progeny 1, select the magnifying glass (Analyte Selection Tool) ***********/
	        fnpClick_OR("TestPlan_Parent1Normalization_Progeny1_MagnifyingGlassIcon");
	        
	        String searchingText="23";
	        fnpType("OR", "TestPlan_Parent1Normalization_Progeny1_AnalytesLookup_AnalysisIdFilter", searchingText);
	        fnpLoading_wait();
	        int  analyteIdColNo=fnpFindColumnIndex("TestPlan_Parent1Normalization_Progeny1_AnalytesLookup_AnalyteSearch_headerTable", "Analysis Id");
	        int totalSearchRows=fnpCountRowsInTable("TestPlan_Parent1Normalization_Progeny1_AnalytesLookup_AnalyteSearch_dataTable");
	        String tempAnalysisId=null;
	        for (int i = 0; i <totalSearchRows; i++) {
	        	tempAnalysisId=fnpFetchFromTable("TestPlan_Parent1Normalization_Progeny1_AnalytesLookup_AnalyteSearch_dataTable", (i+1), analyteIdColNo).trim();
	        	if (tempAnalysisId.contains(searchingText)) {
	        		fnpMymsg("This row '"+(i+1)+"' has Analysis Id '"+tempAnalysisId+"' and it contains this searaching text '"+searchingText+"', hence it is correct.");
					
				} else {
					msg="This row '"+(i+1)+"' has Analysis Id '"+tempAnalysisId+"' and it does not contain this searaching text '"+searchingText+"', hence it is wrong.";
					fnpMymsg(msg);
					throw new Exception(msg);
				}
				
			}
	        
	        
	        
	        
	        
	        /********TC_38 ****"1. Select the check box for 'C2023'
								2. Select 'Add selected Analyte(s)'
								3. Select 'Save & Close'
								4. Select 'Save'"
	         						****************************************************/
	        String needToCheckThisAnalysisId="C2023";
	        tempAnalysisId=null;
	        int expectedRowNo=0;
	        boolean found=false;
	        for (int i = 0; i <totalSearchRows; i++) {
	        	tempAnalysisId=fnpFetchFromTable("TestPlan_Parent1Normalization_Progeny1_AnalytesLookup_AnalyteSearch_dataTable", (i+1), analyteIdColNo).trim();
	        	if (tempAnalysisId.equalsIgnoreCase(needToCheckThisAnalysisId)) {
	        		fnpMymsg("This row '"+(i+1)+"' has Analysis Id '"+tempAnalysisId+"' and it contains this searaching text '"+searchingText+"', hence it is correct.");
	        		expectedRowNo=i+1;
	        		found=true;
	        		break;
				} 
				
			}
	        
	        if (found) {
				String chkbox_xpath=OR.getProperty("TestPlan_Parent1Normalization_Progeny1_AnalytesLookup_AnalyteSearch_dataTable")+"/tr["+expectedRowNo+"]/td/div/div[contains(@class,'ui-chkbox-box')]";
				fnpGetORObjectX___NOR(chkbox_xpath).click();
			}else{
				msg="This Analysis Id '"+needToCheckThisAnalysisId+"' is not present in the first screen/page of this lookup.";
				fnpMymsg(msg);
				throw new Exception(msg);
			}
	        
	        fnpClick_OR("TestPlan_Parent1Normalization_Progeny1_AnalytesLookup_AddSelectedAnalytes_button");
	        fnpClick_OR("TestPlan_Parent1Normalization_Progeny1_AnalytesLookup_SaveAndCloseButton");
	        fnpClick_OR("EPSF_SaveBtn");
	        
	        String firstInputBox_analytesSection_AnalyteCode_xpath = OR.getProperty("TestPlan_Parent1Normalization_Progeny1_AnalytesTable_data_xpath")+"/tr[1]/td[1]/input";
	        String firstInputBox_analytesSection_AnalyteDescription_xpath = OR.getProperty("TestPlan_Parent1Normalization_Progeny1_AnalytesTable_data_xpath")+"/tr[1]/td[2]/input";
	        
	        String actualValueIn_firstInputBox_analytesSection_AnalyteCode=fnpGetORObjectX___NOR(firstInputBox_analytesSection_AnalyteCode_xpath).getAttribute("value").trim();
	        //String actualValueIn_firstInputBox_analytesSection_AnalyteDescription=fnpGetORObjectX___NOR(firstInputBox_analytesSection_AnalyteDescription_xpath).getAttribute("value").trim();        
	         String actualValueIn_firstInputBox_analytesSection_AnalyteDescription = driver.findElement(By.xpath(firstInputBox_analytesSection_AnalyteDescription_xpath)).getAttribute("value").trim();
	        
	        
	        if (actualValueIn_firstInputBox_analytesSection_AnalyteCode.equalsIgnoreCase(needToCheckThisAnalysisId)) {
	        	fnpMymsg("This value '"+needToCheckThisAnalysisId+"' is displayed in the first Analyte Code text box.");
				
			} else {
				msg="This value '"+needToCheckThisAnalysisId+"' is NOT displayed in the first Analyte Code text box.";
				fnpMymsg(msg);
				throw new Exception(msg);
			}
	        
	        String expectedValueIn_firstInputBox_analytesSection_AnalyteDescription="BNA Scan";
	        if (actualValueIn_firstInputBox_analytesSection_AnalyteDescription.equalsIgnoreCase(expectedValueIn_firstInputBox_analytesSection_AnalyteDescription)) {
	        	fnpMymsg("This value '"+expectedValueIn_firstInputBox_analytesSection_AnalyteDescription+"' is displayed in the first Analyte Code description text box.");
				
			} else {
				msg="This value '"+expectedValueIn_firstInputBox_analytesSection_AnalyteDescription+"' is NOT displayed in the first Analyte Code descriptiontext box.";
				fnpMymsg(msg);
				throw new Exception(msg);
			}
	        
	        /*****************************************************************************************************************/
	        
	        
	        
	        
	        /***************TC_39 **********1. Within Parent-1 Normalization/Progeny 1, manually enter 'C2023'.
											2. Select 'Save'
											*************************************/
	        
	        String secondInputBox_analytesSection_AnalyteCode_xpath = OR.getProperty("TestPlan_Parent1Normalization_Progeny1_AnalytesTable_data_xpath")+"/tr[2]/td[1]/input";
	       // String secondInputBox_analytesSection_AnalyteDescription_xpath = OR.getProperty("TestPlan_Parent1Normalization_Progeny1_AnalytesTable_data_xpath")+"/tr[2]/td[2]/input";
	        fnpType("", secondInputBox_analytesSection_AnalyteCode_xpath, needToCheckThisAnalysisId);
	        Thread.sleep(1000);
	        fnpLoading_wait_withoutErrorChecking();
	        fnpLoading_wait_withoutErrorChecking();
	        fnpGetORObjectX___NOR(secondInputBox_analytesSection_AnalyteCode_xpath).sendKeys(Keys.TAB);
	        Thread.sleep(1000);
	        fnpLoading_wait_withoutErrorChecking();
	        fnpLoading_wait_withoutErrorChecking();
			fnpGetORObjectX("EPSF_SaveBtn").click();
			fnpLoading_wait_withoutErrorChecking() ;
			fnpLoading_wait_withoutErrorChecking() ;
			fnpWaitingForExpectedErrorMsg(errorMsgOnSavingDuplicateAnalyteCode);
			fnpMouseHover_OR_EvenMultipleObjectsHavingSameXpath_usingSpecialForErrorClassOnly("ErrorMessage");
			 msg = fnpGetText_OR_EvenMultipleObjectsHavingSameXpath_specialForExtractingErrorMsgTextOnlyEvenIfNotVisible("ErrorMessage");

			fnpMymsg("Top Message is coming on saving duplicate Analyted Code  ----" + msg);
			Assert.assertEquals(
					msg,
					errorMsgOnSavingDuplicateAnalyteCode,
					"Top message does not contain this error message '"+errorMsgOnSavingDuplicateAnalyteCode+"'  on saving  duplicate Analyted Code.");					

	        /******************************************************************************************/
	        
			
			
			
			
	        /***************TC_40 **********1. Within Parent-1 Normalization/Progeny 1, update the second 'C2023' to 'CCCC'.
											2. Select 'Save'
			*************************************/
			String editingAnalyteCode="CCCC";
			fnpLoading_wait_withoutErrorChecking() ;
			//fnpType("", secondInputBox_analytesSection_AnalyteCode_xpath, editingAnalyteCode);
			fnpGetORObjectX___NOR(secondInputBox_analytesSection_AnalyteCode_xpath).clear();
			Thread.sleep(1000);
	        fnpLoading_wait_withoutErrorChecking();
	        fnpLoading_wait_withoutErrorChecking();
	        fnpGetORObjectX___NOR(secondInputBox_analytesSection_AnalyteCode_xpath).sendKeys(editingAnalyteCode);
	        Thread.sleep(1000);
	        fnpLoading_wait_withoutErrorChecking();
	        fnpLoading_wait_withoutErrorChecking();
	        fnpGetORObjectX___NOR(secondInputBox_analytesSection_AnalyteCode_xpath).sendKeys(Keys.TAB);
	        Thread.sleep(1000);
	        fnpLoading_wait_withoutErrorChecking();
	        Thread.sleep(1000);
	        fnpLoading_wait_withoutErrorChecking();
			fnpGetORObjectX("EPSF_SaveBtn").click();
			fnpLoading_wait_withoutErrorChecking() ;
			fnpWaitingForExpectedErrorMsg(errorMsgOnSavingInvalidAnalyteCode);
			fnpMouseHover_OR_EvenMultipleObjectsHavingSameXpath_usingSpecialForErrorClassOnly("ErrorMessage");
			msg = fnpGetText_OR_EvenMultipleObjectsHavingSameXpath_specialForExtractingErrorMsgTextOnlyEvenIfNotVisible("ErrorMessage");
			
			fnpMymsg("Top Message is coming on edit and saving '"+editingAnalyteCode+"' Analyted Code  ----" + msg);
			Assert.assertEquals(
			msg,
			errorMsgOnSavingInvalidAnalyteCode,
			"Top message does not contain this error message '"+errorMsgOnSavingInvalidAnalyteCode+"'  on edit and saving '"+editingAnalyteCode+"'   Analyted Code.");					

			/******************************************************************************************/

			
			
			
			
			/****************TC_41***************1. Within Parent-1 Normalization/Progeny 1, update the 'CCCC' to 'c2024'.
											2. Select 'Save'
			
											************************************************/
			fnpLoading_wait_withoutErrorChecking() ;
			editingAnalyteCode="c2024";
			//fnpType("", secondInputBox_analytesSection_AnalyteCode_xpath, editingAnalyteCode);
			fnpGetORObjectX___NOR(secondInputBox_analytesSection_AnalyteCode_xpath).clear();
			Thread.sleep(1000);
	        fnpLoading_wait_withoutErrorChecking();
	        fnpLoading_wait_withoutErrorChecking();
	        fnpGetORObjectX___NOR(secondInputBox_analytesSection_AnalyteCode_xpath).sendKeys(editingAnalyteCode);
	        Thread.sleep(1000);
	        fnpLoading_wait_withoutErrorChecking();
	        fnpLoading_wait_withoutErrorChecking();
	        fnpGetORObjectX___NOR(secondInputBox_analytesSection_AnalyteCode_xpath).sendKeys(Keys.TAB);
	        Thread.sleep(1000);
	        fnpLoading_wait_withoutErrorChecking();
	        Thread.sleep(1000);
	        fnpLoading_wait_withoutErrorChecking();
	      //  fnpClick_OR("EPSF_SaveBtn");
	        fnpGetORObjectX("EPSF_SaveBtn").click();
	        fnpLoading_wait_withoutErrorChecking();
	        Thread.sleep(1000);
	        fnpLoading_wait_withoutErrorChecking();
	        fnpLoading_wait();
	        //String secondInputBox_analytesSection_AnalyteCode_xpath = OR.getProperty("TestPlan_Parent1Normalization_Progeny1_AnalytesTable_data_xpath")+"/tr[2]/td[1]/input";
		    String secondInputBox_analytesSection_AnalyteDescription_xpath = OR.getProperty("TestPlan_Parent1Normalization_Progeny1_AnalytesTable_data_xpath")+"/tr[2]/td[2]/input";	        
	        
		    String actualValueIn_secondInputBox_analytesSection_AnalyteCode=fnpGetORObjectX___NOR(secondInputBox_analytesSection_AnalyteCode_xpath).getAttribute("value").trim();
	       // String actualValueIn_secondInputBox_analytesSection_AnalyteDescription=fnpGetORObjectX___NOR(secondInputBox_analytesSection_AnalyteDescription_xpath).getAttribute("value").trim();
	        String actualValueIn_secondInputBox_analytesSection_AnalyteDescription=driver.findElement(By.xpath(secondInputBox_analytesSection_AnalyteDescription_xpath)).getAttribute("value").trim();
	        
	        if (actualValueIn_secondInputBox_analytesSection_AnalyteCode.equalsIgnoreCase(editingAnalyteCode)) {
	        	fnpMymsg("This value '"+editingAnalyteCode+"' is displayed in the second Analyte Code text box.");
				
			} else {
				msg="This value '"+editingAnalyteCode+"' is NOT displayed in the second Analyte Code text box.";
				fnpMymsg(msg);
				throw new Exception(msg);
			}
	        
	        String expectedValueIn_secondInputBox_analytesSection_AnalyteDescription="BNA Target";
	        if (actualValueIn_secondInputBox_analytesSection_AnalyteDescription.equalsIgnoreCase(expectedValueIn_secondInputBox_analytesSection_AnalyteDescription)) {
	        	fnpMymsg("This value '"+expectedValueIn_secondInputBox_analytesSection_AnalyteDescription+"' is displayed in the second Analyte Code description text box.");
				
			} else {
				msg="This value '"+expectedValueIn_secondInputBox_analytesSection_AnalyteDescription+"' is NOT displayed in the second Analyte Code descriptiontext box.";
				fnpMymsg(msg);
				throw new Exception(msg);
			}
			
			
			/****************************************************************************/
			
			//Thread.sleep(1);
			
			/*******TC_42*******1. Within Parent-1 Normalization/Progeny 1, select "Add Progeny Exposure Note" icon and enter up to 4,000 characters.
							2. Select 'Save'
							***************************************/
			
			fnpClick_OR("TestPlan_Parent1Normalization_Progeny1_AddExposureNote_link");
			noOfCharacterWeEntered = 4000;
			wb = fnpGetORObjectX("TestPlan_Parent1Normalization_Progeny1_ExposureNotes_TxtArea");
			for(int i=1;i<=1000;i++){//Enter text (up to 4,000 characters) in the exposure note field (include both alphanumeric & symbols) and select 'SAVE'
				wb.sendKeys("A1!@");
			}
			
			 exposureNotes_len = fnpGetORObjectX("TestPlan_Parent1Normalization_Progeny1_ExposureNotes_TxtArea").getAttribute("value").length();
			if (exposureNotes_len!=noOfCharacterWeEntered) {
				msg="TestPlan_Parent1Normalization_Progeny1_ExposureNotes_TxtArea does not accepting "+noOfCharacterWeEntered+" characters as we have entered "+noOfCharacterWeEntered+ " characters but "
						+ "it is accepting only "+exposureNotes_len+" characters.";
				fnpMymsg(msg);
				throw new Exception(msg);											
			}
			
			fnpClick_OR("TestPlan_SaveBtn");
			tagName=fnpGetORObjectX("TestPlan_Parent1Normalization_Progeny1_AddExposureNote_Withoutlink").getTagName();
			if (tagName.equalsIgnoreCase("a")) {
				msg="TestPlan_Parent1Normalization_Progeny1_AddExposureNote_link hyperlink should no longer be enabled.";
				fnpMymsg(msg);
				throw new Exception(msg);
			} else {
				msg=" TestPlan_Parent1Normalization_Progeny1_AddExposureNote_link hyperlink is no longer enabled.";
				fnpMymsg(msg);
			}

			exposureNotes_len=fnpGetORObjectX("TestPlan_Parent1Normalization_Progeny1_ExposureNotes_TxtArea").getText().length();
			if (exposureNotes_len!=noOfCharacterWeEntered) {
				msg="After click Save button TestPlan_Parent1Normalization_Progeny1_ExposureNotes_TxtArea does not have "+noOfCharacterWeEntered+" characters as we have entered "+noOfCharacterWeEntered+ "characters but "
						+ "it has only "+exposureNotes_len+" characters after saving.";
				fnpMymsg(msg);
				throw new Exception(msg);											
			}
			
			
			fnpClick_OR("EditEpst_TasksTabLink");
			fnpClick_OR("EditEpst_TestPlan_TabLink");
			exposureNotes_len=fnpGetORObjectX("TestPlan_Parent1Normalization_Progeny1_ExposureNotes_TxtArea").getText().length();
			if (exposureNotes_len!=noOfCharacterWeEntered) {
				msg="After switching tab change, TestPlan_Parent1Normalization_Progeny1_ExposureNotes_TxtArea does not have "+noOfCharacterWeEntered+" characters as we have entered "+noOfCharacterWeEntered+ " characters but "
						+ "it has only "+exposureNotes_len+" characters after saving.";
				fnpMymsg(msg);
				throw new Exception(msg);											
			}
			
			
			/***************************************************************************************************/
			
			
			/*******TC_43*******1. Within Parent-1 Normalization/Progeny 1, select "Add Analyst Note" icon and enter up to 4,000 characters.
								2. Select 'Save'
			***************************************/
			
						
						
			fnpClick_OR("TestPlan_Parent1Normalization_Progeny1_AddAnalystNote_Link");			
			noOfCharacterWeEntered=4000;
			 wb=fnpGetORObjectX("TestPlan_Parent1Normalization_Progeny1_AddAnalystNote_TxtArea");
			for(int i=1;i<=1000;i++){//Enter text (up to 4,000 characters) in the  Toxicology Note field (include both alphanumeric & symbols) and select 'SAVE'
				wb.sendKeys("A1!@");
			}			

			int analystNotes_len = fnpGetORObjectX("TestPlan_Parent1Normalization_Progeny1_AddAnalystNote_TxtArea").getAttribute("value").length();
			if (analystNotes_len!=noOfCharacterWeEntered) {

				msg="TestPlan_Parent1Normalization_Progeny1_AddAnalystNote_TxtArea does not accepting "+noOfCharacterWeEntered+" characters as we have entered "+noOfCharacterWeEntered+ " characters but "
						+ "it is accepting only "+analystNotes_len+" characters.";
				fnpMymsg(msg);
				throw new Exception(msg);											
			}
			
			fnpClick_OR("TestPlan_SaveBtn");
			tagName=fnpGetORObjectX("TestPlan_Parent1Normalization_Progeny1_AddAnalystNote_Withoutlink").getTagName();
			if (tagName.equalsIgnoreCase("a")) {
				msg=" TestPlan_Parent1Normalization_Progeny1_AddAnalystNote_link hyperlink should no longer be enabled.";
				fnpMymsg(msg);
				throw new Exception(msg);
			} else {
				msg=" TestPlan_Parent1Normalization_Progeny1_AddAnalystNote_link hyperlink is no longer enabled.";
				fnpMymsg(msg);
			}
							
			
			analystNotes_len=fnpGetORObjectX("TestPlan_Parent1Normalization_Progeny1_AddAnalystNote_TxtArea").getText().length();
			if (analystNotes_len!=noOfCharacterWeEntered) {
				msg="After click Save button TestPlan_Parent1Normalization_Progeny1_AddAnalystNote_TxtArea does not have "+noOfCharacterWeEntered+" characters as we have entered "+noOfCharacterWeEntered+ " characters but "
						+ "it has only "+analystNotes_len+" characters after saving.";
				fnpMymsg(msg);
				throw new Exception(msg);											
			}
						
			fnpClick_OR("EditEpst_TasksTabLink");
			fnpClick_OR("EditEpst_TestPlan_TabLink");
			analystNotes_len=fnpGetORObjectX("TestPlan_Parent1Normalization_Progeny1_AddAnalystNote_TxtArea").getText().length();
			if (analystNotes_len!=noOfCharacterWeEntered) {
				msg="After switching tab change, TestPlan_Parent1Normalization_Progeny1_AddAnalystNote_TxtArea does not have "+noOfCharacterWeEntered+" characters as we have entered "+noOfCharacterWeEntered+ " characters but "
						+ "it has only "+analystNotes_len+" characters after saving.";
				fnpMymsg(msg);
				throw new Exception(msg);											
			}
			
			
			/*********************************************************************/
			
			
			
			
			
			/*******TC_44*******1. Within Parent-1 Normalization/Progeny 1, select the sample prep code pH 5 CL 23°c (S0015)
									2. Select 'Save'
								***************************************/
			String TestPlan_Parent1Normalization_Progeny1_SamplePrepCode_listValue=(String) hashXlData.get("TestPlan_Parent1Normalization_Progeny1_SamplePrepCode_listValue").trim();
			fnpPFListModify("TestPlan_Parent1Normalization_Progeny1_SamplePrepCode_List", TestPlan_Parent1Normalization_Progeny1_SamplePrepCode_listValue);	
			
			fnpClick_OR("TestPlan_Floating_SaveBtn");
			fnpClick_OR("EditEpst_TasksTabLink");
			fnpClick_OR("EditEpst_TestPlan_TabLink");
			String selectedPrepCodeValue=fnpGetText_OR("TestPlan_Parent1Normalization_Progeny1_SamplePrepCode_List").trim();
			if (!(selectedPrepCodeValue.equalsIgnoreCase(TestPlan_Parent1Normalization_Progeny1_SamplePrepCode_listValue))) {
				msg="After switching tab change, TestPlan_Parent1Normalization_Progeny1_SamplePrepCode_List does not have this '"+TestPlan_Parent1Normalization_Progeny1_SamplePrepCode_listValue+"' value as we have selected '"+TestPlan_Parent1Normalization_Progeny1_SamplePrepCode_listValue+"' value but "
						+ " here this drop down displayed this value '"+selectedPrepCodeValue+"' after saving and tab change";
				fnpMymsg(msg);
				throw new Exception(msg);											
			}else{
				msg="After switching tab change, TestPlan_Parent1Normalization_Progeny1_SamplePrepCode_List saving this value  '"+TestPlan_Parent1Normalization_Progeny1_SamplePrepCode_listValue+"' successfully after saving and tab change";
				fnpMymsg(msg);
						
			}
			
			/*********************************************************************/
			
			
			
			/*******TC_45 and 46*******1. Within Parent-2 Normalization section for the PIPE-MTL parent, select the red "x"
										1. Select 'Cancel'
										and Expected result is --1. User should receive the following confirmation pop-up: This action will remove all normalization and progeny information in this section. Continue? And 'Reset' and 'Cancel' button.
										2.No parents should be deleted. There should be three parents - Parent 1: (PIPE), Parent 2: (PIPE-MTL), and Parent 3: (LCV).
			 						***************************************/
			
		    ArrayList<String> parentSectionsHeadingName = new ArrayList<String>( 
		               Arrays.asList("Parent-1 Normalization", 
		                             "Parent-2 Normalization", 
		                             "Parent-3 Lead Content Verification")); 
			
			fnpClick_OR("TestPlan_Parent2_Normalization_deleteCrossRed_link");
			
			fnpWaitForVisible("Reset_Parent_dialog_ResetBtn");
			fnpWaitForVisible("Reset_Parent_dialog_CancelBtn");
			 String confirmationMsg = fnpGetText_OR("TestPlan_ParentSection_Delete_Confirmation_msg_xpath");
			if (confirmationMsg.equalsIgnoreCase(confirmationMessageOnDeletingParentSection_otherThanFirstParentSection)) {
				fnpMymsg("Confirmation message is correct on trying to delete 2nd Parent Section.");
				
			} else {
				msg="Confirmation message is NOT correct on trying to delete 2nd Parent Section because actual is this '"+confirmationMsg+
						"' and expected is this '"+confirmationMessageOnDeletingParentSection_otherThanFirstParentSection+"'.";
				fnpMymsg(msg);
				throw new Exception(msg);
				
			}
			fnpClick_OR("Reset_Parent_dialog_CancelBtn");
			
			
			
			int total_parentSection=fnpFindNoOfElementsWithThisXpath("TestPlan_AllParentSection_xpath");
			int expectedParentSection=parentSectionsHeadingName.size();
			
			if (total_parentSection!=expectedParentSection) {
				msg="Total Parent section should be "+expectedParentSection+" but after clicking delete and Cancel button, might be any parent section has got deleted as current"+
						"parent section counts are --"+total_parentSection;
				fnpMymsg(msg);
				throw new Exception(msg);
			} else {
				fnpMymsg("No parents is deleted.");
			}
			
			//String parentSectionList_xpath =OR.getProperty("TestPlan_AllParentSection_xpath")+"/h3";
			String parentSectionList_xpath =OR.getProperty("TestPlan_AllParentSection_xpath")+"/div[1]";
			List<WebElement> wbList = fnpGetORObject_list_NOR(parentSectionList_xpath);
			String tempParentSectionExpected;
			String tempParentSectionActual;
			for (int i = 0; i < expectedParentSection; i++) {
				tempParentSectionExpected=parentSectionsHeadingName.get(i);
				tempParentSectionActual=wbList.get(i).getText().trim();
				if (tempParentSectionExpected.equalsIgnoreCase(tempParentSectionActual)) {
					fnpMymsg("This "+(i+1)+" parent section name is correct i.e. '"+tempParentSectionExpected+"'.");
				}else{
					msg="This "+(i+1)+" parent section name is NOT correct because expected is this '"+tempParentSectionExpected+
							"' but actual is '"+tempParentSectionActual+"'.";
					fnpMymsg(msg);
					throw new Exception(msg);
				}
				
			}
			/*********************************************************************/
			
			
			/*******TC_47*******1. Within Parent-2 Normalization section for the PIPE-MTL parent, select the red "x"
								2. Within the confirmation box, select 'Reset'
								3. Select 'Save'
					and Expected result is --The PIPE-MTL parent is deleted, there are two remaining parent normalization sections, Parent 1 (PIPE) and Parent-2 (LCV) parent.
			***************************************/
			
			
			
					parentSectionsHeadingName = new ArrayList<String>( 
					Arrays.asList("Parent-1 Normalization", 
									"Parent-2 Lead Content Verification")); 
					
			fnpClick_OR("TestPlan_Parent2_Normalization_deleteCrossRed_link");
			//parentSectionsHeadingName.remove(1); 
			fnpClick_OR("Reset_Parent_dialog_ResetBtn");
			fnpClick_OR("TestPlan_Floating_SaveBtn");
			fnpLoading_wait();
			
			
			total_parentSection=fnpFindNoOfElementsWithThisXpath("TestPlan_AllParentSection_xpath");
			expectedParentSection=parentSectionsHeadingName.size();
			
			if (total_parentSection!=expectedParentSection) {
				msg="Total Parent section should be "+expectedParentSection+" but after clicking delete and Reset button,might be 2nd  parent section is not getting deleted as current"+
				"parent section counts are --"+total_parentSection;
				fnpMymsg(msg);
				throw new Exception(msg);
			} else {
				fnpMymsg("2nd Parent Section is deleted.");
			}
			
			//parentSectionList_xpath =OR.getProperty("TestPlan_AllParentSection_xpath")+"/h3";
			parentSectionList_xpath =OR.getProperty("TestPlan_AllParentSection_xpath")+"/div[1]";
			wbList = fnpGetORObject_list_NOR(parentSectionList_xpath);
			for (int i = 0; i < expectedParentSection; i++) {
				tempParentSectionExpected=parentSectionsHeadingName.get(i);
				tempParentSectionActual=wbList.get(i).getText().trim();
				if (tempParentSectionExpected.equalsIgnoreCase(tempParentSectionActual)) {
					fnpMymsg("This "+(i+1)+" parent section name is correct i.e. '"+tempParentSectionExpected+"'.");
				}else{
					msg="This "+(i+1)+" parent section name is NOT correct because expected is this '"+tempParentSectionExpected+
					"' but actual is '"+tempParentSectionActual+"'.";
					fnpMymsg(msg);
					throw new Exception(msg);
				}
			
			}
			/*********************************************************************/

			
			
			
			
			/*******TC_48 and TC_49*******1. Within Parent-1 Normalization/Progeny 1, select the red "x"
			1. Select 'Cancel'
			and Expected result is --User should receive the following confirmation pop-up: This action will remove progeny 2 information from this parent. Continue? And 'Remove' and 'Cancel' buttons.
			***************************************/
		    ArrayList<String> parent_1_Progeny_SectionsHeadingName = new ArrayList<String>( 
		               Arrays.asList("Progeny# 1 Analyses", 
		                             "Progeny# 2 Analyses")); 
/*			
			fnpClick_OR("TestPlan_Parent1_Normalization_Progeny1_deleteCrossRed_link");
			confirmationMsg=fnpGetText_OR("TestPlan_ParentProgenySection_Delete_Confirmation_msg_xpath");
			if (confirmationMsg.equalsIgnoreCase(confirmationMessageOnDeletingParent_Progeny1_Section)) {
				fnpMymsg("Confirmation message is correct on trying to delete first Parent's First Progeny Section.");
				
			} else {
				msg="Confirmation message is NOT correct on trying to delete first Parent's First Progeny Section because actual is this '"+confirmationMsg+
						"' and expected is this '"+confirmationMessageOnDeletingParent_Progeny1_Section+"'.";
				fnpMymsg(msg);
				throw new Exception(msg);
				
			}
			*/
			
			
			
			fnpClick_OR("TestPlan_Parent1_Normalization_Progeny2_deleteCrossRed_link");
			confirmationMsg=fnpGetText_OR("TestPlan_ParentProgenySection_Delete_Confirmation_msg_xpath");
			if (confirmationMsg.equalsIgnoreCase(confirmationMessageOnDeletingParent_Progeny2_Section)) {
				fnpMymsg("Confirmation message is correct on trying to delete first Parent's  Progeny 2 Section.");
				
			} else {
				msg="Confirmation message is NOT correct on trying to delete first Parent's  Progeny 2 Section because actual is this '"+confirmationMsg+
						"' and expected is this '"+confirmationMessageOnDeletingParent_Progeny2_Section+"'.";
				fnpMymsg(msg);
				throw new Exception(msg);
				
			}
			
			
			
			
			
			fnpWaitForVisible("Reset_ParentProgeny_dialog_RemoveBtn");
			fnpWaitForVisible("Reset_ParentProgeny_dialog_CancelBtn"); 
			fnpClick_OR("Reset_ParentProgeny_dialog_CancelBtn");
						
			int total_Parent_1_Progeny_Sections=fnpFindNoOfElementsWithThisXpath("TestPlan_Parent1_AllProgenty_Section_xpath");
			int expectedParent_1_ProgenySections=parent_1_Progeny_SectionsHeadingName.size();
			
			if (total_Parent_1_Progeny_Sections!=expectedParent_1_ProgenySections) {
				msg="Total Parent 1's Progeny section should be "+expectedParent_1_ProgenySections+" but after clicking Remove and Cancel button, might be any progeny section has got deleted as current"+
						"progeny section counts are --"+total_Parent_1_Progeny_Sections;
				fnpMymsg(msg);
				throw new Exception(msg);
			} else {
				fnpMymsg("No progeny is deleted.");
			}
						
			
			
			
			
			/*******TC_50*******1. Within Parent-1 Normalization/Progeny 1, select the red "x"
								2. Within the confirmation box, select 'Remove'
								3. Select 'Save'
			and Expected result is --
			***************************************/
			
			
			//fnpClick_OR("TestPlan_Parent1_Normalization_Progeny1_deleteCrossRed_link");
			fnpClick_OR("TestPlan_Parent1_Normalization_Progeny2_deleteCrossRed_link");
			fnpClick_OR("Reset_ParentProgeny_dialog_RemoveBtn");
			fnpClick_OR("TestPlan_Floating_SaveBtn");
			fnpLoading_wait();
			
			total_Parent_1_Progeny_Sections=fnpFindNoOfElementsWithThisXpath("TestPlan_Parent1_AllProgenty_Section_xpath");
			expectedParent_1_ProgenySections=parent_1_Progeny_SectionsHeadingName.size()-1;
			
			if (total_Parent_1_Progeny_Sections!=expectedParent_1_ProgenySections) {
				msg="Total Parent 1's Progeny section should be "+expectedParent_1_ProgenySections+" but after clicking Remove button, might be any progeny section is not getting deleted as current"+
						"progeny section counts are --"+total_Parent_1_Progeny_Sections;
				fnpMymsg(msg);
				throw new Exception(msg);
			} else {
				fnpMymsg(" One Progeny is deleted.");
			}
				
			/****************************************************************************/
			
			
			
			
			/*******TC_51*******1. Within Parent-1 Normalization Section, enter '123' in the Field Surface Area field.  
								2. Select 'Save.
								and Expected result is --
			 					***************************************/
			String fieldSurfaceArea="123";
			fnpType("OR", "TestPlan_Parent1_FieldSurfaceArea", fieldSurfaceArea);
			fnpClick_OR("TestPlan_Floating_SaveBtn");
			fnpClick_OR("EditEpst_TasksTabLink");
			fnpClick_OR("EditEpst_TestPlan_TabLink");
			//String savedFieldSurfaceValue=fnpGetText_OR("TestPlan_Parent1_FieldSurfaceArea");
			String savedFieldSurfaceValue=fnpGetORObjectX("TestPlan_Parent1_FieldSurfaceArea").getAttribute("value").trim();
			if (!(savedFieldSurfaceValue.equalsIgnoreCase(fieldSurfaceArea))) {
				msg="After switching tab change, TestPlan_Parent1_FieldSurfaceArea does not have this '"+fieldSurfaceArea+"' value as we have inserted this '"+fieldSurfaceArea+"' value but "
						+ " here this Field Surface Area has this value ' "+savedFieldSurfaceValue+" after saving and tab change";
				fnpMymsg(msg);
				throw new Exception(msg);											
			}else{
				msg="After switching tab change, TestPlan_Parent1_FieldSurfaceArea saving this value  '"+savedFieldSurfaceValue+"' successfully after saving and tab change";
				fnpMymsg(msg);
						
			}
			
			
			/********************************************************************************/
			
			
			
			
			/*******TC_52 and 53*******1. Within Parent-1 Normalization Section, select the arrow icon next to the Field surface area field 
			 							 1. Select 'Cancel'
			
								and Expected result is --The Modify Normalization Field pop-up should appear. The pop-up should state the following "This will modify the Normalization Field from Field Surface Area (in2) to Field Number of Units (units). Continue? And 'Modify' and 'Cancel' buttons.
								No changes should be made.
				/********************************************************************************/
			
			
			
			fnpClick_OR("TestPlan_Parent1_FieldSurfaceArea_arrowIcon");
			
			fnpWaitForVisible("TestPlan_Parent1_FieldSurfaceArea_ModifyDialogBox_ModifyBtn");
			fnpWaitForVisible("TestPlan_Parent1_FieldSurfaceArea_ModifyDialogBox_CancelBtn"); 

			confirmationMsg=fnpGetText_OR("TestPlan_Parent1_FieldSurfaceArea_ModifyDialogBox_Confirmation_msg_xpath");
			if (confirmationMsg.equalsIgnoreCase(TestPlan_Parent1_FieldSurfaceArea_ModifyDialogBox_Confirmation_msg)) {
				fnpMymsg("Confirmation message is correct on trying to modify field surface area .");
				
			} else {
				msg="Confirmation message is NOT correct on trying to modify field surface area because actual is this '"+confirmationMsg+
						"' and expected is this '"+TestPlan_Parent1_FieldSurfaceArea_ModifyDialogBox_Confirmation_msg+"'.";
				fnpMymsg(msg);
				throw new Exception(msg);
				
			}
			
			
			fnpClick_OR("TestPlan_Parent1_FieldSurfaceArea_ModifyDialogBox_CancelBtn");
			
			if (fnpCheckElementDisplayed("TestPlan_Parent1_FieldSurfaceArea")) {
				fnpMymsg("Nothing changed on clicking Cancel button in modify dialog box because Field Surface area field is still visible.");
			} else {
				msg="Something changed on clicking Cancel button in modify dialog box because Field Surface area field is NOT visible.";
				fnpMymsg(msg);
				throw new Exception(msg);
			}
			
			
			String modifyFieldSurfaceValue_labelname = fnpGetText_OR("TestPlan_Parent1_FieldSurfaceArea_label");
			if (modifyFieldSurfaceValue_labelname.equalsIgnoreCase("Field Surface Area")) {
				fnpMymsg("Nothing changed on clicking Cancel button in modify dialog box because Field Surface area label is still visible.");
			}else{
				msg="Something changed on clicking Cancel button in modify dialog box because Field Surface area label is NOT visible because here label name is '"+modifyFieldSurfaceValue_labelname+"'.";
				fnpMymsg(msg);
				throw new Exception(msg);				
			}
				
			
				/********************************************************************************/
			
			
			
			
			
			
			
			/*******TC_54*******1. Within Parent-1 Normalization Section, select the arrow icon next to the Field surface area field 
								2. Select 'Modify'
								3. Select 'Save'

								and Expected result is --No error message should be receieved. The Field Surface Area field should be replaced by 'Field Number of Units' and the field should be blank.
							/********************************************************************************/
			
			
			fnpClick_OR("TestPlan_Parent1_FieldSurfaceArea_arrowIcon");
			fnpClick_OR("TestPlan_Parent1_FieldSurfaceArea_ModifyDialogBox_ModifyBtn");
			fnpClick_OR("TestPlan_Floating_SaveBtn");
			fnpLoading_wait();
			String modifyFieldSurfaceValue_labelChange = fnpGetText_OR("TestPlan_Parent1_FieldSurfaceArea_label");
			if (modifyFieldSurfaceValue_labelChange.equalsIgnoreCase("Field Number of Units")) {
				fnpMymsg("The Field Surface Area field is replaced by 'Field Number of Units'");
			}else{
				msg="The Field Surface Area field should be replaced by 'Field Number of Units' but here label name is '"+modifyFieldSurfaceValue_labelChange+"'.";
				fnpMymsg(msg);
				throw new Exception(msg);				
			}
					
			
			//String modifyFieldSurfaceValue = fnpGetText_OR("TestPlan_Parent1_FieldSurfaceArea").trim();
			String modifyFieldSurfaceValue=fnpGetORObjectX("TestPlan_Parent1_FieldSurfaceArea").getAttribute("value").trim();
			if (modifyFieldSurfaceValue.equalsIgnoreCase("")) {
				fnpMymsg("This field 'Field Number of Units' is coming blank as expected.");
			}else{
				msg="This field 'Field Number of Units' is NOT coming blank as expected.";
				fnpMymsg(msg);
				throw new Exception(msg);
			}
			
			
			/********************************************************************************/
			
			
			
			
			
			/*******TC_55*******1. Within Parent-1 Normalization Section, select the arrow icon next to the Field Number of Units' field 
								2. Select 'Modify'
								3. Select 'Save'

								and Expected result is --No error message should be receieved. The Field Number of Units field should be replaced by 'Field Surface Area' and the field should be blank..
							/********************************************************************************/
			
			
			fnpClick_OR("TestPlan_Parent1_FieldSurfaceArea_arrowIcon");
			fnpClick_OR("TestPlan_Parent1_FieldSurfaceArea_ModifyDialogBox_ModifyBtn");
			fnpClick_OR("TestPlan_Floating_SaveBtn");
			fnpLoading_wait();
			modifyFieldSurfaceValue_labelChange = fnpGetText_OR("TestPlan_Parent1_FieldSurfaceArea_label");
			if (modifyFieldSurfaceValue_labelChange.equalsIgnoreCase("Field Surface Area")) {
				fnpMymsg("The Field Number of Units field is again replaced by 'Field Surface Area'");
			}else{
				msg="The Field Number of Units field should be replaced by 'Field Surface Area' but here label name is '"+modifyFieldSurfaceValue_labelChange+"'.";
				fnpMymsg(msg);
				throw new Exception(msg);				
			}
					
			
			//modifyFieldSurfaceValue = fnpGetText_OR("TestPlan_Parent1_FieldSurfaceArea").trim();
			modifyFieldSurfaceValue=fnpGetORObjectX("TestPlan_Parent1_FieldSurfaceArea").getAttribute("value").trim();
			if (modifyFieldSurfaceValue.equalsIgnoreCase("")) {
				fnpMymsg("This field 'Field Surface Area' is coming blank as expected.");
			}else{
				msg="This field 'Field Surface Area' is NOT coming blank as expected.";
				fnpMymsg(msg);
				throw new Exception(msg);
			}
			
			
			/********************************************************************************/
			
			
			
			
			
			/*******TC_56*******1. Within Parent-1 Normalization Section, select the 'Add Normalization field' button.
			
				and Expected result is --The Add Normalization Field pop-up should appear and should contain the following: Table with three columns: checkbox, Normalization Type and unit. An 'Add Selected Field(s)' button and a cancel link.
							/********************************************************************************/
			
			fnpClick_OR("TestPlan_Parent1_AddNormalizationFieldButton");
			
			
			ArrayList<String> addNormalizationTableColumnNames = new ArrayList<String>( 
			Arrays.asList("", 
							"Normalization Type",
							"Unit")); 
			

	
	       String actualHeaderNames_xpath = OR.getProperty("TestPlan_Parent1_AddNormalizationField_headerTable")+"//th/span";
	       String actualColName;
	       String expectedColName;
	       List<WebElement> actualHeaderNamesElement = fnpGetORObject_list_NOR(actualHeaderNames_xpath);
			for (int i = 0; i < addNormalizationTableColumnNames.size(); i++) {
				expectedColName=addNormalizationTableColumnNames.get(i);
				actualColName=actualHeaderNamesElement.get(i).getText().trim();
				if (actualColName.equalsIgnoreCase(expectedColName)) {
					fnpMymsg("Expected  and actual col name is mathced i.e. '"+actualColName+"'.");
				}else{
					msg="Expected col in the Add Normalization table is '"+expectedColName+"' but here actual is '"+actualColName+"'.";
					fnpMymsg(msg);
					throw new Exception(msg);
				}
			
			}
			
			fnpWaitForVisible("TestPlan_Parent1_AddNormalizationField_AddSelectedFieldButton");
			fnpWaitForVisible("TestPlan_Parent1_AddNormalizationField_Cancel_link");
			
			/********************************************************************************/
		
			
			
			
			
			
			
			/*******TC_57*******1. Select Misc. Factor and Constant N2
								2. Select 'Add Selected Field(s) button
			
			and Expected result is --No error message should be received.  The Constant N2 and Misc. Field should appear to the right of the default normalization fields in Parent-1 Normalization section and should default to null.
						/********************************************************************************/
			
			fnpCheckCheckbox_basedOn_ColData( "TestPlan_Parent1_AddNormalizationField_dataTable","TestPlan_Parent1_AddNormalizationField_headerTable",
					"Normalization Type","Misc. Factor",  1);
			
			fnpLoading_wait();
			
			fnpCheckCheckbox_basedOn_ColData( "TestPlan_Parent1_AddNormalizationField_dataTable","TestPlan_Parent1_AddNormalizationField_headerTable",
					"Normalization Type","Constant N2",  1);
			
			fnpClick_OR("TestPlan_Parent1_AddNormalizationField_AddSelectedFieldButton");
			
			fnpWaitForVisible("TestPlan_Parent1_Constant_N2");
			fnpWaitForVisible("TestPlan_Parent1_Misc_Factor");
			
			String value = fnpGetORObjectX("TestPlan_Parent1_Constant_N2").getAttribute("value");
			if (value.equalsIgnoreCase("")) {
				fnpMymsg("This field 'Constant N2' is coming blank as expected.");
			}else{
				msg="This field 'Constant N2' is NOT coming blank as expected.";
				fnpMymsg(msg);
				throw new Exception(msg);
			}
			 value = fnpGetORObjectX("TestPlan_Parent1_Misc_Factor").getAttribute("value");
			if (value.equalsIgnoreCase("")) {
				fnpMymsg("This field 'Misc Factor' is coming blank as expected.");
			}else{
				msg="This field 'Misc Factor' is NOT coming blank as expected.";
				fnpMymsg(msg);
				throw new Exception(msg);
			}
			
			/********************************************************************************/
			
			
			
			
			
			
			
			/*******TC_58*******1. Enter '123' in the Misc. Factor field 
								2. Enter '456' in the Constant N2 field.
								3. Select 'Save'
			
			and Expected result is --No error message should be received. Upon exiting and returning, the values should be saved.
						/********************************************************************************/
			
			
			
			String miscFactor="123";
			fnpType("OR", "TestPlan_Parent1_Misc_Factor", miscFactor);
			
			String constantN2="456";
			fnpType("OR", "TestPlan_Parent1_Constant_N2", constantN2);
			
			fnpClick_OR("TestPlan_Floating_SaveBtn");
			fnpLoading_wait();
			fnpClick_OR("EditEpst_TasksTabLink");
			fnpClick_OR("EditEpst_TestPlan_TabLink");
			//String savedMiscFactor=fnpGetText_OR("TestPlan_Parent1_Misc_Factor");
			String savedMiscFactor=fnpGetORObjectX("TestPlan_Parent1_Misc_Factor").getAttribute("value").trim();
			if (!(savedMiscFactor.equalsIgnoreCase(miscFactor))) {
				msg="After switching tab change, TestPlan_Parent1_Misc_Factor does not have this '"+miscFactor+"' value as we have inserted this '"+miscFactor+"' value but "
						+ " here this Field TestPlan_Parent1_Misc_Factor has this value ' "+savedMiscFactor+" after saving and tab change";
				fnpMymsg(msg);
				throw new Exception(msg);											
			}else{
				msg="After switching tab change, TestPlan_Parent1_Misc_Factor saving this value  '"+savedMiscFactor+"' successfully after saving and tab change";
				fnpMymsg(msg);
						
			}
			
			
			//String savedconstantN2=fnpGetText_OR("TestPlan_Parent1_Constant_N2");
			String savedconstantN2=fnpGetORObjectX("TestPlan_Parent1_Constant_N2").getAttribute("value").trim();
			if (!(savedconstantN2.equalsIgnoreCase(constantN2)) ){
				msg="After switching tab change, TestPlan_Parent1_Constant_N2 does not have this '"+constantN2+"' value as we have inserted this '"+constantN2+"' value but "
						+ " here this Field TestPlan_Parent1_Constant_N2 has this value ' "+savedconstantN2+" after saving and tab change";
				fnpMymsg(msg);
				throw new Exception(msg);											
			}else{
				msg="After switching tab change, TestPlan_Parent1_Constant_N2 saving this value  '"+savedconstantN2+"' successfully after saving and tab change";
				fnpMymsg(msg);
						
			}
			
			
			/********************************************************************************/
			
			
			
			
			/*******TC_59*******1. Within Parent-1 Normalization Section, select the 'Update Parent Template' button.
			
			and Expected result is --The Update Parent Template pop-up should be received that states the following: "Editing the Parent template may result in a change to the normalization fields and values, #parents, and progeny information. Please carefully review and update the test plan as necessary."  The pop-up should contain a New Parent Template field that defaults to 'Please Select'.,  and 'Cancel' and 'Continue' buttons.
						/********************************************************************************/
			
			
			
			fnpClick_OR("TestPlan_Parent1_UpdateParentTemplate_Button");
			confirmationMsg=fnpGetText_OR("TestPlan_Parent1_UpdateParentTemplate_Confirmation_msg_xpath");
			if (confirmationMsg.equalsIgnoreCase(TestPlan_Parent1_UpdateParentTemplate_Confirmation_msg_xpath)) {
				fnpMymsg("Confirmation message is correct on trying to update Parent Template.");
				
			} else {
				msg="Confirmation message is NOT correct on trying to update Parent Template because actual is this '"+confirmationMsg+
						"' and expected is this '"+TestPlan_Parent1_UpdateParentTemplate_Confirmation_msg_xpath+"'.";
				fnpMymsg(msg);
				throw new Exception(msg);
				
			}
			
			String defaultValueInDropDown=fnpGetText_OR("TestPlan_Parent1_UpdateParentTemplate_NewParentTemplate_List").trim();
			if (defaultValueInDropDown.equalsIgnoreCase("Please Select")) {
				fnpMymsg("Default value in New Parent Template list is blank");
				
			} else {
				msg="Default value in New Parent Template list is NOT blank, as current value is '"+defaultValueInDropDown+"'.";
				fnpMymsg(msg);
				throw new Exception(msg);
				
			}
			
			fnpWaitForVisible("TestPlan_Parent1_UpdateParentTemplate_Cancel_button");
			fnpWaitForVisible("TestPlan_Parent1_UpdateParentTemplate_Continue_button"); 
			
			/********************************************************************************/
			
			
			
			
			/*******TC_60*******1. Select 'Cancel' and check that  no error message is received
			
			and Expected result is --No error message should be received
						/********************************************************************************/
			
			fnpClick_OR("TestPlan_Parent1_UpdateParentTemplate_Cancel_button");
			
			/********************************************************************************/
			
			
			
			
			
			/*******TC_61*******1. Within Parent-1 Normalization Section, select the 'Update Parent Template' button.
								2. Select 'Continue'
			
			and Expected result is --The user should receive the following error message: Please select a parent template other than the current parent template to proceed"
						/********************************************************************************/
			
			fnpClick_OR("TestPlan_Parent1_UpdateParentTemplate_Button");
			//fnpClick_OR("TestPlan_Parent1_UpdateParentTemplate_Continue_button");
			fnpGetORObjectX("TestPlan_Parent1_UpdateParentTemplate_Continue_button").click();
			fnpLoading_wait_withoutErrorChecking() ;
			fnpWaitingForExpectedErrorMsg(errorMsgOnSavingWithoutSelectingNewParentTemplate);
			fnpMouseHover_OR_EvenMultipleObjectsHavingSameXpath_usingSpecialForErrorClassOnly("ErrorMessage");
			 msg = fnpGetText_OR_EvenMultipleObjectsHavingSameXpath_specialForExtractingErrorMsgTextOnlyEvenIfNotVisible("ErrorMessage");

			fnpMymsg("Top Message is coming on saving the New Parent Template without selecting Parent Template  ----" + msg);
			Assert.assertEquals(
					msg,
					errorMsgOnSavingWithoutSelectingNewParentTemplate,
					"Top message does not contain this error message '"+errorMsgOnSavingWithoutSelectingNewParentTemplate+"'  on saving New Parent Template without selecting Parent Template.");					

			
			

			
			
			
			/********************************************************************************/
			
			
			
			/*******TC_62*******1. Select 'PIPE' from the parent template drop down list.
								2. Selection 'Continue'
			and Expected result is --The user should receive the following error message: "Please select a different template than the current parent template type."
						/********************************************************************************/
			fnpPFListModify_usingWhenIgnoringErrorOnSelectingValues( "TestPlan_Parent1_UpdateParentTemplate_NewParentTemplate_List",  "PIPE (L0009)") ;
			fnpGetORObjectX("TestPlan_Parent1_UpdateParentTemplate_Continue_button").click();
			fnpLoading_wait_withoutErrorChecking() ;
			fnpWaitingForExpectedErrorMsg(errorMsgOnSavingWithoutSelectingNewParentTemplate);
			fnpMouseHover_OR_EvenMultipleObjectsHavingSameXpath_usingSpecialForErrorClassOnly("ErrorMessage");
			 msg = fnpGetText_OR_EvenMultipleObjectsHavingSameXpath_specialForExtractingErrorMsgTextOnlyEvenIfNotVisible("ErrorMessage");

			fnpMymsg("Top Message is coming on saving the same current Parent Template  ----" + msg);
			Assert.assertEquals(
					msg,
					errorMsgOnSavingSameNewParentTemplate,
					"Top message does not contain this error message '"+errorMsgOnSavingSameNewParentTemplate+"'  on saving  same current Parent Template .");					

			
			
				/********************************************************************************/
			//411--Test Plan no.
			
			
			
			
			
			/*******TC_63*******1. Select 'PIPE-MTL' from the parent template drop down list.
								2. Select 'Continue'
								3. Select 'Save'
			and Expected result is --The Parent Template for Parent-1 should now be PIPE-MTL. 

 					The Progeny #1 days should be (1,17, 49, 107), the Analytes should be C2023 and C2024, the progeny exposure notes and analyst notes should be present, and the sample prep code should be pH 5 CL 23°C.  

					The normalization fields should be as follows: CMPD Ref Key: TAC, Field expsoure time = '16 hours', Field static volume = '1 L', 'Misc. Factor = '123', 'Constant N2' = '456'.
				/********************************************************************************/
			
			fnpPFListModify_usingWhenIgnoringErrorOnSelectingValues( "TestPlan_Parent1_UpdateParentTemplate_NewParentTemplate_List",  "PIPE-MTL (L0010)") ;
			fnpGetORObjectX("TestPlan_Parent1_UpdateParentTemplate_Continue_button").click();
			fnpLoading_wait_withoutErrorChecking() ;
			fnpClick_OR("TestPlan_Floating_SaveBtn");
			fnpLoading_wait();
			
			String parent1TemplateValue=fnpGetText_OR("TestPlan_Parent1_ParentTemplate_List").trim();
			String expectingParentTemplateValue="PIPE-MTL";
			if (parent1TemplateValue.equalsIgnoreCase(expectingParentTemplateValue)) {
				fnpMymsg("Parent1 Template value is '"+parent1TemplateValue+"' as expected.");
				
			} else {
				msg="Parent1 Template value is '"+parent1TemplateValue+"' but we were expecting this '"+expectingParentTemplateValue+"' value.";
				fnpMymsg(msg);
				throw new Exception(msg);
				
			}
			
			
			fnpCheckIsDaysCriticalIn_ascendingOrder_and_its_color(daysList,criticalDaysList);
			
	        firstInputBox_analytesSection_AnalyteCode_xpath = OR.getProperty("TestPlan_Parent1Normalization_Progeny1_AnalytesTable_data_xpath")+"/tr[1]/td[1]/input";
	        firstInputBox_analytesSection_AnalyteDescription_xpath = OR.getProperty("TestPlan_Parent1Normalization_Progeny1_AnalytesTable_data_xpath")+"/tr[1]/td[2]/input";
	        
	        actualValueIn_firstInputBox_analytesSection_AnalyteCode=fnpGetORObjectX___NOR(firstInputBox_analytesSection_AnalyteCode_xpath).getAttribute("value").trim();
	        //String actualValueIn_firstInputBox_analytesSection_AnalyteDescription=fnpGetORObjectX___NOR(firstInputBox_analytesSection_AnalyteDescription_xpath).getAttribute("value").trim();        
	        actualValueIn_firstInputBox_analytesSection_AnalyteDescription = driver.findElement(By.xpath(firstInputBox_analytesSection_AnalyteDescription_xpath)).getAttribute("value").trim();
	        
	        if (actualValueIn_firstInputBox_analytesSection_AnalyteCode.equalsIgnoreCase(needToCheckThisAnalysisId)) {
	        	fnpMymsg("This value '"+needToCheckThisAnalysisId+"' is displayed in the first Analyte Code text box.");
				
			} else {
				msg="This value '"+needToCheckThisAnalysisId+"' is NOT displayed in the first Analyte Code text box.";
				fnpMymsg(msg);
				throw new Exception(msg);
			}
	        
	        expectedValueIn_firstInputBox_analytesSection_AnalyteDescription="BNA Scan";
	        if (actualValueIn_firstInputBox_analytesSection_AnalyteDescription.equalsIgnoreCase(expectedValueIn_firstInputBox_analytesSection_AnalyteDescription)) {
	        	fnpMymsg("This value '"+expectedValueIn_firstInputBox_analytesSection_AnalyteDescription+"' is displayed in the first Analyte Code description text box.");
				
			} else {
				msg="This value '"+expectedValueIn_firstInputBox_analytesSection_AnalyteDescription+"' is NOT displayed in the first Analyte Code descriptiontext box.";
				fnpMymsg(msg);
				throw new Exception(msg);
			}
		        
		
			
			
		    secondInputBox_analytesSection_AnalyteDescription_xpath = OR.getProperty("TestPlan_Parent1Normalization_Progeny1_AnalytesTable_data_xpath")+"/tr[2]/td[2]/input";	        
	        
		    actualValueIn_secondInputBox_analytesSection_AnalyteCode=fnpGetORObjectX___NOR(secondInputBox_analytesSection_AnalyteCode_xpath).getAttribute("value").trim();
	       // String actualValueIn_secondInputBox_analytesSection_AnalyteDescription=fnpGetORObjectX___NOR(secondInputBox_analytesSection_AnalyteDescription_xpath).getAttribute("value").trim();
	        actualValueIn_secondInputBox_analytesSection_AnalyteDescription=driver.findElement(By.xpath(secondInputBox_analytesSection_AnalyteDescription_xpath)).getAttribute("value").trim();
	        
	        if (actualValueIn_secondInputBox_analytesSection_AnalyteCode.equalsIgnoreCase(editingAnalyteCode)) {
	        	fnpMymsg("This value '"+editingAnalyteCode+"' is displayed in the second Analyte Code text box.");
				
			} else {
				msg="This value '"+editingAnalyteCode+"' is NOT displayed in the second Analyte Code text box.";
				fnpMymsg(msg);
				throw new Exception(msg);
			}
	        
	        expectedValueIn_secondInputBox_analytesSection_AnalyteDescription="BNA Target";
	        if (actualValueIn_secondInputBox_analytesSection_AnalyteDescription.equalsIgnoreCase(expectedValueIn_secondInputBox_analytesSection_AnalyteDescription)) {
	        	fnpMymsg("This value '"+expectedValueIn_secondInputBox_analytesSection_AnalyteDescription+"' is displayed in the second Analyte Code description text box.");
				
			} else {
				msg="This value '"+expectedValueIn_secondInputBox_analytesSection_AnalyteDescription+"' is NOT displayed in the second Analyte Code descriptiontext box.";
				fnpMymsg(msg);
				throw new Exception(msg);
			}
			
			
			
			
			
			exposureNotes_len=fnpGetORObjectX("TestPlan_Parent1Normalization_Progeny1_ExposureNotes_TxtArea").getText().length();
			if (exposureNotes_len!=noOfCharacterWeEntered) {
				msg="TestPlan_Parent1Normalization_Progeny1_ExposureNotes_TxtArea does not have "+noOfCharacterWeEntered+" characters as we have entered "+noOfCharacterWeEntered+ "characters but "
						+ "it has only "+exposureNotes_len+" characters.";
				fnpMymsg(msg);
				throw new Exception(msg);											
			}
			
	        
			analystNotes_len=fnpGetORObjectX("TestPlan_Parent1Normalization_Progeny1_AddAnalystNote_TxtArea").getText().length();
			if (analystNotes_len!=noOfCharacterWeEntered) {
				msg="TestPlan_Parent1Normalization_Progeny1_AddAnalystNote_TxtArea does not have "+noOfCharacterWeEntered+" characters as we have entered "+noOfCharacterWeEntered+ " characters but "
						+ "it has only "+analystNotes_len+" characters.";
				fnpMymsg(msg);
				throw new Exception(msg);											
			}
			
			
			TestPlan_Parent1Normalization_Progeny1_SamplePrepCode_listValue=(String) hashXlData.get("TestPlan_Parent1Normalization_Progeny1_SamplePrepCode_listValue").trim();
			selectedPrepCodeValue=fnpGetText_OR("TestPlan_Parent1Normalization_Progeny1_SamplePrepCode_List").trim();
			if (!(selectedPrepCodeValue.equalsIgnoreCase(TestPlan_Parent1Normalization_Progeny1_SamplePrepCode_listValue))) {
				msg=" TestPlan_Parent1Normalization_Progeny1_SamplePrepCode_List does not have this '"+TestPlan_Parent1Normalization_Progeny1_SamplePrepCode_listValue+"' value as we have selected '"+TestPlan_Parent1Normalization_Progeny1_SamplePrepCode_listValue+"' value but "
						+ " here this drop down displayed this value '"+selectedPrepCodeValue+"' ";
				fnpMymsg(msg);
				throw new Exception(msg);											
			}else{
				msg=" TestPlan_Parent1Normalization_Progeny1_SamplePrepCode_List having this value  '"+TestPlan_Parent1Normalization_Progeny1_SamplePrepCode_listValue+"' .";
				fnpMymsg(msg);
						
			}
			
			
			
			String cmpdRefKeyValue=fnpGetText_OR("TestPlan_Parent1_CmpdRefKey_List").trim();
			String expectedcmpdRefKeyValue="TAC";
			if (!(cmpdRefKeyValue.equalsIgnoreCase(expectedcmpdRefKeyValue))) {
				msg=" TestPlan_Parent1_CmpdRefKey_List does not have this '"+expectedcmpdRefKeyValue+"' value  but "
						+ " here this drop down displayed this value '"+cmpdRefKeyValue+"' ";
				fnpMymsg(msg);
				throw new Exception(msg);											
			}else{
				msg=" TestPlan_Parent1_CmpdRefKey_List having this value  '"+cmpdRefKeyValue+"' .";
				fnpMymsg(msg);
						
			}
			
			
			
			
			//String actualFieldStaticVolumeValue=fnpGetText_OR("TestPlan_Parent1_FieldStaticVolume").trim();
			String actualFieldStaticVolumeValue = fnpGetORObjectX("TestPlan_Parent1_FieldStaticVolume").getAttribute("value").trim();
			String expectedFieldStaticVolumeValue="1";
			if (!(actualFieldStaticVolumeValue.equalsIgnoreCase(expectedFieldStaticVolumeValue))) {
				msg=" TestPlan_Parent1_FieldStaticVolume does not have this '"+expectedFieldStaticVolumeValue+"' value  but "
						+ " here this field displayed this value '"+actualFieldStaticVolumeValue+"' ";
				fnpMymsg(msg);
				throw new Exception(msg);											
			}else{
				msg=" TestPlan_Parent1_FieldStaticVolume having this value  '"+expectedFieldStaticVolumeValue+"' .";
				fnpMymsg(msg);
						
			}
			
			
			
			
			//String actualFieldExposureValue=fnpGetText_OR("TestPlan_Parent1_FieldExposureVolume").trim();
			String actualFieldExposureValue=fnpGetORObjectX("TestPlan_Parent1_FieldExposureVolume").getAttribute("value").trim();
			String expectedFieldExposureValue="16";
			//String expectedFieldExposureValue="1";
			if (!(actualFieldExposureValue.equalsIgnoreCase(expectedFieldExposureValue))) {
				msg=" TestPlan_Parent1_FieldExposureVolume does not have this '"+expectedFieldExposureValue+"' value  but "
						+ " here this field displayed this value '"+actualFieldExposureValue+"' ";
				fnpMymsg(msg);
				fnpDisplayingMessageInFrame_fnpMymsg(msg, 5);
				fnpMouseHover("TestPlan_Parent1_FieldExposureVolume");
				fnpDesireScreenshot("TestPlan_Parent1_FieldExposureVolume");
				throw new Exception(msg);											
			}else{
				msg=" TestPlan_Parent1_FieldExposureVolume having this value  '"+expectedFieldExposureValue+"' .";
				fnpMymsg(msg);
						
			}
				
			
			 //savedMiscFactor=fnpGetText_OR("TestPlan_Parent1_Misc_Factor");
			 savedMiscFactor=fnpGetORObjectX("TestPlan_Parent1_Misc_Factor").getAttribute("value").trim();
			if (!(savedMiscFactor.equalsIgnoreCase(miscFactor))) {
				msg="TestPlan_Parent1_Misc_Factor does not have this '"+miscFactor+"' value as we are expecting this value ' "+miscFactor+"'  and actual is this '"+savedMiscFactor+".";
				fnpMymsg(msg);
				throw new Exception(msg);											
			}else{
				msg="TestPlan_Parent1_Misc_Factor having this value  '"+savedMiscFactor+"'. ";
				fnpMymsg(msg);
						
			}
			
			
			//savedconstantN2=fnpGetText_OR("TestPlan_Parent1_Constant_N2");
			savedconstantN2=fnpGetORObjectX("TestPlan_Parent1_Constant_N2").getAttribute("value").trim();
			if (!(savedconstantN2.equalsIgnoreCase(constantN2))) {
				msg="TestPlan_Parent1_Constant_N2 does not have this '"+constantN2+"' value as we are expecting this value ' "+constantN2+"'  and actual is this '"+savedconstantN2+".";
				fnpMymsg(msg);
				throw new Exception(msg);											
			}else{
				msg="TestPlan_Parent1_Constant_N2 having this value  '"+constantN2+"'. ";
				fnpMymsg(msg);
						
			}
			
			
			

			//TP00000441
			
			
			/********************************************************************************/
			
			
			/*******TC_64*******Update the status from 'DRAFT' to 'REVIEWED'. Select 'SAVE'
				and Expected result is --No error message should be received. None of the fields are editable and all buttons are disabled.  The only editable field is the status.
						/********************************************************************************/
		
			changeStatus="Reviewed";	
			fnpPFList("TestPlan_Status_List", "TestPlan_Status_ListOptions", changeStatus);	
			fnpClick_OR("TestPlan_Floating_SaveBtn");
			fnpLoading_wait();
			
			fnpCheckField_IsDisable("OR","TestPlan_ProductTemplate_List_TopDiv","Product Template",true);
			fnpCheckField_IsDisable("OR","TestPlan_Status_List_TopDiv","Status",false);
			fnpCheckField_IsDisable("OR","TestPlan_Function_List_TopDiv","Function",true);
			fnpCheckField_IsDisable("OR","TestPlan_CollectionNotes_TxtArea","Collection Notes",true);
			fnpCheckField_IsDisable("OR","TestPlan_ExposureNotes_TxtArea","Exposure Notes",true);
			fnpCheckField_IsDisable("OR","TestPlan_AddToxicologyNote_TxtArea","Toxicology Notes",true);
			fnpCheckField_IsDisable("OR","TestPlan_ViewCollectionNoteButton","ViewCollectionNoteButton",false);
			fnpCheckField_IsDisable("OR","TestPlan_AddParents_Btn","TestPlan_AddParents_Btn",true);
						
			//Parent 1 Normalization
			fnpCheckField_IsDisable("OR","TestPlan_Parent1_ParentTemplate_List_TopDiv","TestPlan_Parent1_ParentTemplate",true);
			fnpCheckField_IsDisable("OR","TestPlan_Parent1_CmpdRefKey_List_TopDiv","TestPlan_Parent1_CmpdRefKey",true);
			fnpCheckField_IsDisable("OR","TestPlan_Parent1_FieldSurfaceArea","TestPlan_Parent1_FieldSurfaceArea",true);
			fnpCheckField_IsDisable("OR","TestPlan_Parent1_FieldStaticVolume","TestPlan_Parent1_FieldStaticVolume",true);
			fnpCheckField_IsDisable("OR","TestPlan_Parent1_FieldExposureVolume","TestPlan_Parent1_FieldExposureVolume",true);			
			fnpCheckField_IsDisable("OR","TestPlan_Parent1_Misc_Factor","TestPlan_Parent1_Misc_Factor",true);
			fnpCheckField_IsDisable("OR","TestPlan_Parent1_Constant_N2","TestPlan_Parent1_Constant_N2",true);
			fnpCheckField_IsDisable("OR","TestPlan_FirstParentNormalization_AddNormalizationFieldBtn","TestPlan_FirstParentNormalization_AddNormalizationFieldBtn",true);
			fnpCheckField_IsDisable("OR","TestPlan_FirstParentNormalization_AddProgenyBtn","TestPlan_FirstParentNormalization_AddProgenyBtn",true);
									
			//Parent 1 Progeny 1
			fnpCheckField_IsDisable("OR","TestPlan_SamplePrepCode_List_TopDiv","TestPlan_SamplePrepCode_List",true);
			fnpCheckField_IsDisable("OR","TestPlan_Parent1Normalization_Progeny1_ExposureNotes_TxtArea","TestPlan_Parent1Normalization_Progeny1_ExposureNotes_TxtArea",true);
			fnpCheckField_IsDisable("OR","TestPlan_Parent1Normalization_Progeny1_AddAnalystNote_TxtArea","TestPlan_Parent1Normalization_Progeny1_AddAnalystNote_TxtArea",true);
			fnpCheckField_IsDisable("OR","TestPlan_AnalyteCode_firstTextBox","TestPlan_AnalyteCode_firstTextBox",true);
			fnpCheckField_IsDisable("OR","TestPlan_AnalyteDescription_firstTextBox","TestPlan_AnalyteDescription_firstTextBox",true);						
			
			//Parent 2
			fnpCheckField_IsDisable("OR","TestPlan_Parent2_ParentTemplate_DropDown_TopDiv","TestPlan_Parent2_ParentTemplate_DropDown",true);
			fnpCheckField_IsDisable("OR","TestPlan_LeadedComponentNotes_TxtArea","TestPlan_LeadedComponentNotes",true);
			fnpCheckField_IsDisable("OR","TestPlan_AcidDigestionOnly_chkbx_divToCheckDisable","TestPlan_AcidDigestionOnly_chkbx_divToCheckDisable",true);
			fnpCheckField_IsDisable("OR","TestPlan_WitnessTesting_chkbx_divToCheckDisable","TestPlan_WitnessTesting_chkbx_divToCheckDisable",true);
			
			
		  /********************************************************************************/
			
			
			
			
			/*******TC_65*******Update the status from 'REVIEWED' to 'DRAFT'. Select 'SAVE'
				and Expected result is --No error message should be receieved. All of the fields are editable and all buttons are enabled. 
						/********************************************************************************/
		
			changeStatus="Draft";	
			fnpPFList("TestPlan_Status_List", "TestPlan_Status_ListOptions", changeStatus);	
			fnpClick_OR("TestPlan_Floating_SaveBtn");
			fnpLoading_wait();
			
			
			
			fnpCheckField_IsDisable("OR","TestPlan_ProductTemplate_List_TopDiv","Product Template",true);
			fnpCheckField_IsDisable("OR","TestPlan_Status_List_TopDiv","Status",false);
			fnpCheckField_IsDisable("OR","TestPlan_Function_List_TopDiv","Function",false);
			fnpCheckField_IsDisable("OR","TestPlan_CollectionNotes_TxtArea","Collection Notes",true);
			fnpCheckField_IsDisable("OR","TestPlan_ExposureNotes_TxtArea","Exposure Notes",false);
			fnpCheckField_IsDisable("OR","TestPlan_AddToxicologyNote_TxtArea","Toxicology Notes",false);
			fnpCheckField_IsDisable("OR","TestPlan_ViewCollectionNoteButton","ViewCollectionNoteButton",false);
			fnpCheckField_IsDisable("OR","TestPlan_AddParents_Btn","TestPlan_AddParents_Btn",false);
			
			//Parent 1 Normalization
			fnpCheckField_IsDisable("OR","TestPlan_Parent1_ParentTemplate_List_TopDiv","TestPlan_Parent1_ParentTemplate",true);
			fnpCheckField_IsDisable("OR","TestPlan_Parent1_CmpdRefKey_List_TopDiv","TestPlan_Parent1_CmpdRefKey",false);
			fnpCheckField_IsDisable("OR","TestPlan_Parent1_FieldSurfaceArea","TestPlan_Parent1_FieldSurfaceArea",false);
			fnpCheckField_IsDisable("OR","TestPlan_Parent1_FieldStaticVolume","TestPlan_Parent1_FieldStaticVolume",false);
			fnpCheckField_IsDisable("OR","TestPlan_Parent1_FieldExposureVolume","TestPlan_Parent1_FieldExposureVolume",false);			
			fnpCheckField_IsDisable("OR","TestPlan_Parent1_Misc_Factor","TestPlan_Parent1_Misc_Factor",false);
			fnpCheckField_IsDisable("OR","TestPlan_Parent1_Constant_N2","TestPlan_Parent1_Constant_N2",false);
			fnpCheckField_IsDisable("OR","TestPlan_FirstParentNormalization_AddNormalizationFieldBtn","TestPlan_FirstParentNormalization_AddNormalizationFieldBtn",false);
			fnpCheckField_IsDisable("OR","TestPlan_FirstParentNormalization_AddProgenyBtn","TestPlan_FirstParentNormalization_AddProgenyBtn",false);
						
			//Parent 1 Progeny 1
			fnpCheckField_IsDisable("OR","TestPlan_SamplePrepCode_List_TopDiv","TestPlan_SamplePrepCode_List",false);
			fnpCheckField_IsDisable("OR","TestPlan_Parent1Normalization_Progeny1_ExposureNotes_TxtArea","TestPlan_Parent1Normalization_Progeny1_ExposureNotes_TxtArea",false);
			fnpCheckField_IsDisable("OR","TestPlan_Parent1Normalization_Progeny1_AddAnalystNote_TxtArea","TestPlan_Parent1Normalization_Progeny1_AddAnalystNote_TxtArea",false);
			fnpCheckField_IsDisable("OR","TestPlan_AnalyteCode_firstTextBox","TestPlan_AnalyteCode_firstTextBox",false);
			//fnpCheckField_IsDisable("OR","TestPlan_AnalyteDescription_firstTextBox","TestPlan_AnalyteDescription_firstTextBox",false);
			fnpCheckField_IsDisable("OR","TestPlan_AnalyteDescription_firstTextBox","TestPlan_AnalyteDescription_firstTextBox",true);
			
			//Parent 2
			fnpCheckField_IsDisable("OR","TestPlan_Parent2_ParentTemplate_DropDown_TopDiv","TestPlan_Parent2_ParentTemplate_DropDown",true);
			fnpCheckField_IsDisable("OR","TestPlan_LeadedComponentNotes_TxtArea","TestPlan_LeadedComponentNotes",false);
			fnpCheckField_IsDisable("OR","TestPlan_AcidDigestionOnly_chkbx_divToCheckDisable","TestPlan_AcidDigestionOnly_chkbx_divToCheckDisable",false);
			fnpCheckField_IsDisable("OR","TestPlan_WitnessTesting_chkbx_divToCheckDisable","TestPlan_WitnessTesting_chkbx_divToCheckDisable",false);

			/********************************************************************************/
			
			
			
			
			
			
			/*******TC_66*******Update the status to 'APPROVED'. Select 'SAVE'
				and Expected result is --No error message should be received. None of the fields are editable and all buttons are disabled. 
				 The only editable field is the status. Test Plan Approved By: {username} {date (MM-DDD-YYYY format)}” should appear. 
						/********************************************************************************/
		
			changeStatus="Approved";	
			fnpPFList("TestPlan_Status_List", "TestPlan_Status_ListOptions", changeStatus);	
			fnpClick_OR("TestPlan_Floating_SaveBtn");
			fnpLoading_wait();
			
			fnpCheckField_IsDisable("OR","TestPlan_ProductTemplate_List_TopDiv","Product Template",true);
			fnpCheckField_IsDisable("OR","TestPlan_Status_List_TopDiv","Status",false);
			fnpCheckField_IsDisable("OR","TestPlan_Function_List_TopDiv","Function",true);
			fnpCheckField_IsDisable("OR","TestPlan_CollectionNotes_TxtArea","Collection Notes",true);
			fnpCheckField_IsDisable("OR","TestPlan_ExposureNotes_TxtArea","Exposure Notes",true);
			fnpCheckField_IsDisable("OR","TestPlan_AddToxicologyNote_TxtArea","Toxicology Notes",true);
			fnpCheckField_IsDisable("OR","TestPlan_ViewCollectionNoteButton","ViewCollectionNoteButton",false);
			fnpCheckField_IsDisable("OR","TestPlan_AddParents_Btn","TestPlan_AddParents_Btn",true);
			
			
			//Parent 1 Normalization
			fnpCheckField_IsDisable("OR","TestPlan_Parent1_ParentTemplate_List_TopDiv","TestPlan_Parent1_ParentTemplate",true);
			fnpCheckField_IsDisable("OR","TestPlan_Parent1_CmpdRefKey_List_TopDiv","TestPlan_Parent1_CmpdRefKey",true);
			fnpCheckField_IsDisable("OR","TestPlan_Parent1_FieldSurfaceArea","TestPlan_Parent1_FieldSurfaceArea",true);
			fnpCheckField_IsDisable("OR","TestPlan_Parent1_FieldStaticVolume","TestPlan_Parent1_FieldStaticVolume",true);
			fnpCheckField_IsDisable("OR","TestPlan_Parent1_FieldExposureVolume","TestPlan_Parent1_FieldExposureVolume",true);			
			fnpCheckField_IsDisable("OR","TestPlan_Parent1_Misc_Factor","TestPlan_Parent1_Misc_Factor",true);
			fnpCheckField_IsDisable("OR","TestPlan_Parent1_Constant_N2","TestPlan_Parent1_Constant_N2",true);
			fnpCheckField_IsDisable("OR","TestPlan_FirstParentNormalization_AddNormalizationFieldBtn","TestPlan_FirstParentNormalization_AddNormalizationFieldBtn",true);
			fnpCheckField_IsDisable("OR","TestPlan_FirstParentNormalization_AddProgenyBtn","TestPlan_FirstParentNormalization_AddProgenyBtn",true);
						
			//Parent 1 Progeny 1
			fnpCheckField_IsDisable("OR","TestPlan_SamplePrepCode_List_TopDiv","TestPlan_SamplePrepCode_List",true);
			fnpCheckField_IsDisable("OR","TestPlan_Parent1Normalization_Progeny1_ExposureNotes_TxtArea","TestPlan_Parent1Normalization_Progeny1_ExposureNotes_TxtArea",true);
			fnpCheckField_IsDisable("OR","TestPlan_Parent1Normalization_Progeny1_AddAnalystNote_TxtArea","TestPlan_Parent1Normalization_Progeny1_AddAnalystNote_TxtArea",true);
			fnpCheckField_IsDisable("OR","TestPlan_AnalyteCode_firstTextBox","TestPlan_AnalyteCode_firstTextBox",true);
			fnpCheckField_IsDisable("OR","TestPlan_AnalyteDescription_firstTextBox","TestPlan_AnalyteDescription_firstTextBox",true);
									
			//Parent 2
			fnpCheckField_IsDisable("OR","TestPlan_Parent2_ParentTemplate_DropDown_TopDiv","TestPlan_Parent2_ParentTemplate_DropDown",true);
			fnpCheckField_IsDisable("OR","TestPlan_LeadedComponentNotes_TxtArea","TestPlan_LeadedComponentNotes",true);
			fnpCheckField_IsDisable("OR","TestPlan_AcidDigestionOnly_chkbx_divToCheckDisable","TestPlan_AcidDigestionOnly_chkbx_divToCheckDisable",true);
			fnpCheckField_IsDisable("OR","TestPlan_WitnessTesting_chkbx_divToCheckDisable","TestPlan_WitnessTesting_chkbx_divToCheckDisable",true);
			
			
			fnpScrollToUp();
			fnpWaitForVisible("TestPlan_ApprovedByLable_xpath");
			
			String approvedByText=fnpGetText_OR("TestPlan_ApprovedByLable_xpath").trim();//Approved By : EVALENTINE 22-Jan-2020
			
			String[] approvedBy_array=approvedByText.split(":");
			if (approvedBy_array.length!=2) {
				msg="Something has been changed in 'Approved By' label because expected format is this 'Approved By :  "+loginAs+" "+(new SimpleDateFormat("dd-MMM-yyyy").format(new Date()))+"'but actual is this '"+approvedByText+"'.";
				fnpMymsg(msg);
				throw new Exception(msg);
			}
			String expectedApprovedByLabel="Approved By";
			if (approvedBy_array[0].trim().equalsIgnoreCase(expectedApprovedByLabel)) {
				fnpMymsg("Approved By label is present");
				
			} else {
				msg="'"+expectedApprovedByLabel+"' label is NOT present because expected is '"+expectedApprovedByLabel+"' but actual is '"+expectedApprovedByLabel+"'.";
				fnpMymsg(msg);
				throw new Exception(msg);
			}
			
			
			
			String approvedByName_withDate=approvedBy_array[1].trim();
			String[] approvedByName_withDateArray=approvedByName_withDate.split(" ");
			String expectedApprovedByNameOnly=loginAs;//actualLoginUser;
			if (approvedByName_withDateArray[0].trim().equalsIgnoreCase(expectedApprovedByNameOnly)) {
				fnpMymsg("Approved By name is present i.e. '"+expectedApprovedByNameOnly+"'.");
				
			} else {
				
				if (approvedByName_withDateArray[0].trim().equalsIgnoreCase("")) {
					msg="Approved By name is  not present because expected is '"+expectedApprovedByNameOnly+"' but actual is '"+approvedByName_withDateArray[0]+"'.";
					fnpMymsg(msg);
					throw new Exception(msg);
				} else {
					msg="Approved By name is  not correct because expected is '"+expectedApprovedByNameOnly+"' but actual is '"+approvedByName_withDateArray[0]+"'.";
					fnpMymsg(msg);
					throw new Exception(msg);
				}

			}
			
			
			String actualApprovedByDate_string=approvedByName_withDateArray[1].trim();
			// todayDate = new Date();
			
			 dateInString=getCurrentTime(timeFormat,timeZone);
			 formatter = new SimpleDateFormat(timeFormat );
			 todayDate = formatter.parse(dateInString);
			
			
			 formatter = new SimpleDateFormat("dd-MMM-yyyy");
			 expectedDateString = formatter.format(todayDate);				
			 expectedDate = formatter.parse(expectedDateString);
			
			if (actualApprovedByDate_string.equalsIgnoreCase(expectedDateString)) {
				fnpMymsg(" Date  is correct in the string Approved By: i.e. '"+expectedDateString+"'.");
				
			} else {
				msg=" Either the date format is not corrrect or date is not correct in the string Approved By: because expected is '"+expectedDateString+"' but actual is '"+actualApprovedByDate_string+"'.";
				fnpMymsg(msg);
				throw new Exception(msg);
			}
			
			
			
			Date actualApprovedByDate=formatter.parse(actualApprovedByDate_string);
			if (expectedDate.compareTo(actualApprovedByDate) > 0) {
				msg="Approved By date is less than  to the current today date, expected is this '"+expectedDate+"' but actual is '"+actualApprovedByDate+"'.";
				fnpMymsg(msg);
				throw new Exception(msg);
			} else {
				if (expectedDate.compareTo(actualApprovedByDate) < 0) {
					msg="Approved By date is more than  to the current today date, expected is this '"+expectedDate+"' but actual is '"+actualApprovedByDate+"'.";
					fnpMymsg(msg);
					throw new Exception(msg);
				} else {
					msg="Approved By date is equal  to the current today date, expected is this '"+expectedDate+"' and actual is '"+actualApprovedByDate+"'.";
					fnpMymsg(msg);
				}
			}
			
			
			
			/********************************************************************************/
			
			
			
			
			
			
			/*******TC_67*******Update the status to 'APPROVED' to 'DRAFT' . Select 'SAVE'
				and Expected result is --No error message should be received. All of the fields are editable and all buttons are enabled. 
						/********************************************************************************/
		
			changeStatus="Draft";	
			fnpPFList("TestPlan_Status_List", "TestPlan_Status_ListOptions", changeStatus);	
			fnpClick_OR("TestPlan_Floating_SaveBtn");
			fnpLoading_wait();
			
			fnpCheckField_IsDisable("OR","TestPlan_ProductTemplate_List_TopDiv","Product Template",true);
			fnpCheckField_IsDisable("OR","TestPlan_Status_List_TopDiv","Status",false);
			fnpCheckField_IsDisable("OR","TestPlan_Function_List_TopDiv","Function",false);
			fnpCheckField_IsDisable("OR","TestPlan_CollectionNotes_TxtArea","Collection Notes",true);
			fnpCheckField_IsDisable("OR","TestPlan_ExposureNotes_TxtArea","Exposure Notes",false);
			fnpCheckField_IsDisable("OR","TestPlan_AddToxicologyNote_TxtArea","Toxicology Notes",false);
			fnpCheckField_IsDisable("OR","TestPlan_ViewCollectionNoteButton","ViewCollectionNoteButton",false);
			fnpCheckField_IsDisable("OR","TestPlan_AddParents_Btn","TestPlan_AddParents_Btn",false);
			
			//Parent 1 Normalization
			fnpCheckField_IsDisable("OR","TestPlan_Parent1_ParentTemplate_List_TopDiv","TestPlan_Parent1_ParentTemplate",true);
			fnpCheckField_IsDisable("OR","TestPlan_Parent1_CmpdRefKey_List_TopDiv","TestPlan_Parent1_CmpdRefKey",false);
			fnpCheckField_IsDisable("OR","TestPlan_Parent1_FieldSurfaceArea","TestPlan_Parent1_FieldSurfaceArea",false);
			fnpCheckField_IsDisable("OR","TestPlan_Parent1_FieldStaticVolume","TestPlan_Parent1_FieldStaticVolume",false);
			fnpCheckField_IsDisable("OR","TestPlan_Parent1_FieldExposureVolume","TestPlan_Parent1_FieldExposureVolume",false);			
			fnpCheckField_IsDisable("OR","TestPlan_Parent1_Misc_Factor","TestPlan_Parent1_Misc_Factor",false);
			fnpCheckField_IsDisable("OR","TestPlan_Parent1_Constant_N2","TestPlan_Parent1_Constant_N2",false);
			fnpCheckField_IsDisable("OR","TestPlan_FirstParentNormalization_AddNormalizationFieldBtn","TestPlan_FirstParentNormalization_AddNormalizationFieldBtn",false);
			fnpCheckField_IsDisable("OR","TestPlan_FirstParentNormalization_AddProgenyBtn","TestPlan_FirstParentNormalization_AddProgenyBtn",false);
						
			//Parent 1 Progeny 1
			fnpCheckField_IsDisable("OR","TestPlan_SamplePrepCode_List_TopDiv","TestPlan_SamplePrepCode_List",false);
			fnpCheckField_IsDisable("OR","TestPlan_Parent1Normalization_Progeny1_ExposureNotes_TxtArea","TestPlan_Parent1Normalization_Progeny1_ExposureNotes_TxtArea",false);
			fnpCheckField_IsDisable("OR","TestPlan_Parent1Normalization_Progeny1_AddAnalystNote_TxtArea","TestPlan_Parent1Normalization_Progeny1_AddAnalystNote_TxtArea",false);
			fnpCheckField_IsDisable("OR","TestPlan_AnalyteCode_firstTextBox","TestPlan_AnalyteCode_firstTextBox",false);
			//fnpCheckField_IsDisable("OR","TestPlan_AnalyteDescription_firstTextBox","TestPlan_AnalyteDescription_firstTextBox",false);
			fnpCheckField_IsDisable("OR","TestPlan_AnalyteDescription_firstTextBox","TestPlan_AnalyteDescription_firstTextBox",true);
			
			//Parent 2
			fnpCheckField_IsDisable("OR","TestPlan_Parent2_ParentTemplate_DropDown_TopDiv","TestPlan_Parent2_ParentTemplate_DropDown",true);
			fnpCheckField_IsDisable("OR","TestPlan_LeadedComponentNotes_TxtArea","TestPlan_LeadedComponentNotes",false);
			fnpCheckField_IsDisable("OR","TestPlan_AcidDigestionOnly_chkbx_divToCheckDisable","TestPlan_AcidDigestionOnly_chkbx_divToCheckDisable",false);
			fnpCheckField_IsDisable("OR","TestPlan_WitnessTesting_chkbx_divToCheckDisable","TestPlan_WitnessTesting_chkbx_divToCheckDisable",false);

			
			
			
				/********************************************************************************/
			
			
			
			
			/*******TC_68*******1. Within Parent-1 Normalization Section, select the red 'x'
				and Expected result is --The user should receive a pop-up titled “Reset Parent #1” 
										- The text should read “This action will remove ALL parent normalization sections and ALL progeny information from the Test Plan.   Continue?”
										-  There should be a “Reset” button and a “Cancel” button.

						/********************************************************************************/

			fnpClick_OR("TestPlan_Parent1_Normalization_deleteCrossRed_link");
			fnpWaitForVisible("Reset_Parent1_Normalization_deleteCrossRed_dialog_ResetBtn");
			fnpWaitForVisible("Reset_Parent1_Normalization_deleteCrossRed_dialog_CancelBtn");
			confirmationMsg=fnpGetText_OR("TestPlan_Parent1_Normalization_Delete_Confirmation_msg_xpath");
			if (confirmationMsg.equalsIgnoreCase(confirmationMessageOnDeletingParent1Normalization)) {
				fnpMymsg("Confirmation message is correct on trying to delete  Parent1 Normalization Section.");
				
			} else {
				msg="Confirmation message is NOT correct on trying to delete Parent1 Normalization  Section because actual is this '"+confirmationMsg+
						"' and expected is this '"+confirmationMessageOnDeletingParent1Normalization+"'.";
				fnpMymsg(msg);
				throw new Exception(msg);				
			}
		
			
			
			
			
			
			
				/********************************************************************************/
			
			
			
			/*******TC_69*******1. Select 'Cancel'
			and Expected result is --The user should be directed back to the edit test plan screen and no changes should be made.

					/********************************************************************************/

			fnpClick_OR("Reset_Parent1_Normalization_deleteCrossRed_dialog_CancelBtn");
			//Parent 2  --Checking only parent 2 section's some fields
			fnpCheckField_IsDisable("OR","TestPlan_Parent2_ParentTemplate_DropDown_TopDiv","TestPlan_Parent2_ParentTemplate_DropDown",true);
			fnpCheckField_IsDisable("OR","TestPlan_LeadedComponentNotes_TxtArea","TestPlan_LeadedComponentNotes",false);
			fnpCheckField_IsDisable("OR","TestPlan_AcidDigestionOnly_chkbx_divToCheckDisable","TestPlan_AcidDigestionOnly_chkbx_divToCheckDisable",false);
			fnpCheckField_IsDisable("OR","TestPlan_WitnessTesting_chkbx_divToCheckDisable","TestPlan_WitnessTesting_chkbx_divToCheckDisable",false);
			/********************************************************************************/
			
			
			
			
			
			/*******TC_70*******1. Within Parent-1 Normalization Section, select the red 'x'
								2. Select 'Reset'
			and Expected result is --The popup should close
							• All parent/progeny sections are removed from the screen and their data deleted from the database (if saved)
							• The Parent #1 Normalization section reverts back to its original state prior to selecting a parent template
							• The ‘Parent Template’ dropdown should revert to whatever the appropriate default parent is supposed to be, based on the selected product template; or if no default, then ‘Please Select’.
							• The ‘Product Template’ dropdown should become enabled again

					/********************************************************************************/

			fnpClick_OR("TestPlan_Parent1_Normalization_deleteCrossRed_link");
			fnpClick_OR("Reset_Parent1_Normalization_deleteCrossRed_dialog_ResetBtn");
			/********************************************************************************/
			
			
			
			//The popup should close
			if (!(fnpCheckElementDisplayedImmediately("Reset_Parent1_Normalization_deleteCrossRed_dialog_ResetBtn"))) {
				fnpMymsg("The popup is closed because Reset_Parent1_Normalization_deleteCrossRed_dialog_ResetBtn is not visible as expected.");
			} else {
				msg="The popup is NOT closed because Reset_Parent1_Normalization_deleteCrossRed_dialog_ResetBtn should not be visible at this step but here it is  visible";
				fnpMymsg(msg);
				throw new Exception(msg);
			}
			//The popup should close
			if (!(fnpCheckElementDisplayedImmediately("Reset_Parent1_Normalization_deleteCrossRed_dialog_CancelBtn"))) {
				fnpMymsg("The popup is closed because Reset_Parent1_Normalization_deleteCrossRed_dialog_CancelBtn is not visible as expected.");
			} else {
				msg="The popup is NOT closed because Reset_Parent1_Normalization_deleteCrossRed_dialog_CancelBtn should not be visible at this step but here it is  visible";
				fnpMymsg(msg);
				throw new Exception(msg);
			}
			
			
			
			parentElements = fnpGetORObject_list_NOR(OR.getProperty("TestPlan_ParentSections_Header_xpath"), 10);
			parentElementSize=parentElements.size();
			if (parentElementSize!=0) {
				msg="According to the test case there should be 0 Parent Sections but here parent sections are --"+parentElementSize;
				fnpMymsg(msg);
				throw new Exception(msg);
				
			}




			if (fnpCheckElementDisplayedImmediately("TestPlan_ParentTemplate_CreateBtn")) {
				fnpMymsg("Create button is  visible as expected.");
			} else {
				msg="Create button should be visible at this step but here it is not visible";
				fnpMymsg(msg);
				throw new Exception(msg);
			}



			if (!(fnpCheckElementDisplayedImmediately("TestPlan_FirstParentNormalization_AddNormalizationFieldBtn"))) {
				fnpMymsg("TestPlan_FirstParentNormalization_AddNormalizationFieldBtn is not visible as expected.");
			} else {
				msg="TestPlan_FirstParentNormalization_AddNormalizationFieldBtn should not be visible at this step but here it is  visible";
				fnpMymsg(msg);
				throw new Exception(msg);
			}
			
			

			if (!(fnpCheckElementDisplayedImmediately("TestPlan_FirstParentNormalization_AddProgenyBtn"))) {
				fnpMymsg("TestPlan_FirstParentNormalization_AddProgenyBtn is not visible as expected.");
			} else {
				msg="TestPlan_FirstParentNormalization_AddProgenyBtn should not be visible at this step but here it is  visible";
				fnpMymsg(msg);
				throw new Exception(msg);
			}
			
			

			if (!(fnpCheckElementDisplayedImmediately("TestPlan_FirstParentNormalization_UpdateParentTemplateBtn"))) {
				fnpMymsg("TestPlan_FirstParentNormalization_UpdateParentTemplateBtn is not visible as expected.");
			} else {
				msg="TestPlan_FirstParentNormalization_UpdateParentTemplateBtn should not be visible at this step but here it is  visible";
				fnpMymsg(msg);
				throw new Exception(msg);
			}
			
			

			if (!(fnpCheckElementDisplayedImmediately("TestPlan_Days_field"))) {
				fnpMymsg("TestPlan_Days_field is not visible as expected.");
			} else {
				msg="TestPlan_Days_field should not be visible at this step but here it is  visible";
				fnpMymsg(msg);
				throw new Exception(msg);
			}
			
			
			

			if (!(fnpCheckElementDisplayedImmediately("TestPlan_Days_field_editIcon"))) {
				fnpMymsg("TestPlan_Days_field_editIcon is not visible as expected.");
			} else {
				msg="TestPlan_Days_field_editIcon should not be visible at this step but here it is  visible";
				fnpMymsg(msg);
				throw new Exception(msg);
			}
			
			

			if (!(fnpCheckElementDisplayedImmediately("TestPlan_AnalyteCode_firstTextBox"))) {
				fnpMymsg("TestPlan_AnalyteCode_firstTextBox is not visible as expected.");
			} else {
				msg="TestPlan_AnalyteCode_firstTextBox should not be visible at this step but here it is  visible";
				fnpMymsg(msg);
				throw new Exception(msg);
			}
			
			
			
			
			
			
			
			
			String finalValueInProductTemplate=fnpGetText_OR("TestPlan_ProductTemplate_List");
			
/*			if (finalValueInProductTemplate.equalsIgnoreCase(initial_ProductTemplate_value_WhichWillBeVerifiedAgainAtThelastStep)) {
				fnpMymsg(" The ‘Parent Template’ dropdown is reverted to whatever the appropriate default parent is supposed to be, based on the selected product template; or if no default, then ‘Please Select’");
				
			} else {
				msg=" The ‘Parent Template’ dropdown should revert to whatever the appropriate default parent is supposed to be, based on the selected product template; or if no default, then ‘Please Select’ because expected is '"+initial_ProductTemplate_value_WhichWillBeVerifiedAgainAtThelastStep+"' but actual is '"+finalValueInProductTemplate+"'.";
				fnpMymsg(msg);
				throw new Exception(msg);
			}
			*/
			
			//changes for step no. 70 as per suggested by Indu on 24-03-2020
			if (finalValueInProductTemplate.equalsIgnoreCase((String) hashXlData.get("Product_Template").trim())) {
				fnpMymsg(" The ‘Product Template’ dropdown value is mtched to expected as per given in excel.");
				
			} else {
				msg=" The ‘Product Template’ dropdown value is Not mtched to expected as per given in excel because expected is '"+(String) hashXlData.get("Product_Template").trim()+"' but actual is '"+finalValueInProductTemplate+"'.";
				fnpMymsg(msg);
				throw new Exception(msg);
			}
			
			
			
			fnpCheckField_IsDisable("OR","TestPlan_ProductTemplate_List_TopDiv","Product Template",false);
			
			
			
			
			
			Thread.sleep(1);
			
			}catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "  Create Test Plan flow  is fail . ", "CreateTestPlanFail");

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