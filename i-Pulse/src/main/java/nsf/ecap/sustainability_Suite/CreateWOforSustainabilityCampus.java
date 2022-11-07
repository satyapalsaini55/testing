package nsf.ecap.sustainability_Suite;

import static nsf.ecap.config.IPULSE_CONSTANTS.Sus_SingleTestCaseName;
import static nsf.ecap.config.IPULSE_CONSTANTS.WOISOFinancialTab_AuditDurationTableColName_AdjustedDuration;
import static nsf.ecap.config.IPULSE_CONSTANTS.WOISOFinancialTab_AuditDurationTableColName_CalculatedDuration;
import static nsf.ecap.config.IPULSE_CONSTANTS.WOISOFinancialTab_AuditDurationTableColName_Task;
import static nsf.ecap.config.IPULSE_CONSTANTS.WOISOFinancialTab_BillCodeTableColName_BillCodeScheduledPrice;
import static nsf.ecap.config.IPULSE_CONSTANTS.WOISOFinancialTab_BillCodeTableColName_BillCodes;
import static nsf.ecap.config.IPULSE_CONSTANTS.WOISOFinancialTab_BillCodeTableColName_Quantity;
import static nsf.ecap.config.IPULSE_CONSTANTS.WOISOFinancialTab_BillCodeTableColName_TaskTypeFeeType;
import static nsf.ecap.config.IPULSE_CONSTANTS.WOISOFinancialTab_BillCodeTableColName_TotalAmount;
import static nsf.ecap.config.IPULSE_CONSTANTS.WOISOTaskName_CertificationAudit;
import static nsf.ecap.config.IPULSE_CONSTANTS.WOISOTaskName_DeskAudit;
import static nsf.ecap.config.IPULSE_CONSTANTS.WOISOTaskTab_Certification_MainTaskAIName;
import static nsf.ecap.config.IPULSE_CONSTANTS.WOISOTaskTab_PerformAuditAIType;
import static nsf.ecap.config.IPULSE_CONSTANTS.WOISOTaskTab_Stage1DeskAudit_MainTaskAIName;
import static nsf.ecap.config.IPULSE_CONSTANTS.WOISOTaskTab_TaskAISummaryTableColName_TaskAIName;
import static nsf.ecap.config.IPULSE_CONSTANTS.WOISOTaskTab_TechnicalReviewAIName;
import static nsf.ecap.config.IPULSE_CONSTANTS.WOISOTaskType_CertificationAudit;
import static nsf.ecap.config.IPULSE_CONSTANTS.WOISOTaskType_DeskAudit;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;

import nsf.ecap.Work_Order_suite.TestSuiteBase;
import nsf.ecap.config.IPULSE_CONSTANTS;
import nsf.ecap.util.ErrorUtil;
import nsf.ecap.util.TestUtil;

public class CreateWOforSustainabilityCampus extends TestSuiteBase_Sustain_Suite{
	String deskAuditTaskTab_expectedBillCode = "";
	double deskAuditTaskTab_expectedQuantity = 0;
	double deskAuditTaskTab_expectedAuditDays = 0;
	
	String certAuditTaskTab_expectedBillCode = "";
	double certAuditTaskTab_expectedQuantity = 0;
	double certAuditTaskTab_expectedAuditDays = 0;
	
	// Runmode of test case in a suite
	@BeforeTest
	@Parameters({ "classNameCampus" })
	public void checkTestSkip(String classNameCampus) throws Throwable {

		try {

			if (classNameCampus.isEmpty()) {
				classNameCampus = this.getClass().getSimpleName();
			}
			classNameText = classNameCampus;
			currentScriptCode = fnpMappingClassNameWithScenarioCode(classNameText).trim();

			fnpMymsg("             ");
			fnpMymsg("             ");
			fnpMymsg("             ");
			fnpMymsg("             ");
			fnpMymsg("             ");
			fnpMymsg("=========================================================================================================================================");
			fnpMymsg("Checking Runmode of testcase '" + classNameCampus + "' .");

			if (!TestUtil.isTestCaseRunnable(currentSuiteXls, classNameCampus)) {

				fnpMymsg("Skipping Test Case" + classNameCampus + " as runmode set to NO");
				fnpMymsg("=========================================================================================================================================");
				throw new SkipException("Skipping Test Case" + classNameCampus + " as runmode set to NO");
			}

			fnpMymsg("Going to execute the script for  '" + classNameCampus + "'  as runmode set to Yes");

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
			throw new SkipException(errorMsg + " \n\nSo,Skipping Test Case" + classNameCampus);
		} catch (Throwable t) {
			fnpCommonFinalCatchBlock(t, "  checkTestSkip method  is fail . ", "checkTestSkipFail");

		}

	}
/**
 * Creating New Work Order. 
 * @throws Throwable
 */
	@Test(priority = 1)
	public void CreateWOFlow() throws Throwable {

		fnpMymsg(" **************************************************************");
		fnpMymsg(" Executing  SUST_Campus CreateWOFlow");

		try {
			
			if ((classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.Sus_CampusTestCaseName))) {
				fnpMymsg("@  ---Before query starts for test case ---"+classNameText);
				dropWOSusandDeleteSomeDataFromDB((String) hashXlData.get("Corp/Facility#"), (String) hashXlData.get("Standard"));
				//dropWOSusandDeleteSomeDataFromDB("45304", "QMSI9K15");
				fnpMymsg("@  ---After query finishes for test case ---"+classNameText);
				}

			createSusWO_excludingDBDropQuery();
			fnpMymsg(" **************************************************************");

		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "  Create Work Order flow  is fail . ", "CreateWorkOrderFail");

		}

	}
	/**
	 * Filling info for Standard Facility Tab.
	 * @throws Throwable
	 */
	@Test(priority = 2, dependsOnMethods = { "CreateWOFlow" })
	public void Standard_Facility_Tab() throws Throwable {

		try {
			fnpMymsg(" **************************************************************");
			fnpMymsg(" Executing Standard_Facility_Tab");
			callRemoveAlreadyAddedIndustryCodeUsingScript() ;
			/*if ((classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.Cert_StandardsTestCaseName))
					){
				if (   ((String) hashXlData.get("Standard").trim()).equalsIgnoreCase("R2:2013")){
					fnpPFList("StandardLevelPFList", "StandardLevelPFListOptions", (String) hashXlData.get("Standard_Level"));
				}
				
			}*/
			
			
			/*if ((classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.ISO9001_CorporateTestCaseName)) |
					(classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.ISO9001_CampusTestCaseName)) ){
				
				if (   ((String) hashXlData.get("WOType").trim()).equalsIgnoreCase("Associated Facility")) {
					// in this case , no scope field is present
				}else{
					fngType("OR_SUS", "ScopeTxtBx", (String) hashXlData.get("Scope"));
				}
	
			}*/
			
			

			fngType("OR_SUS", "ScopeofthefacilityTxtBx", (String) hashXlData.get("Scope_of_the_facility"));
			fngType("OR_SUS", "Exclusion_of_the_facilityTxtBx", (String) hashXlData.get("Exclusion_of_the_facility"));
			
			
			
			
			//This field is only coming in ISO9001_SingleWo_InProcess
			/*if ((classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.Sus_SingleTestCaseName))
					|(classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.ISO9001_CorporateTestCaseName))
					|(classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.ISO9001_CampusTestCaseName))
					) {
				fnpPFList("FacilityDesignResponsiblePFList", "FacilityDesignResponsiblePFListOptions", (String) hashXlData.get("Facility_Design_Responsible"));
			}*/
			
			

			/*if (!(classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.Cert_StandardsTestCaseName)) )
			{
				fnpClick_OR("ISRSFTabSaveBtn");
			}*/ 
			
			
			
			String industryCodeCompleteStringValue=(String) hashXlData.get("Industry_Code");
			String productsCompleteStringValue=(String) hashXlData.get("Products");
			
			
			String[] industryCodeCompleteStringValueArray = industryCodeCompleteStringValue.split("::");
			String[] productsCompleteStringValueArray = productsCompleteStringValue.split("::");
					
			String industryCode;
			String productValue;
			for (int i = 0; i < industryCodeCompleteStringValueArray.length; i++) {
				
				industryCode=industryCodeCompleteStringValueArray[i].trim();
				productValue=	productsCompleteStringValueArray[i].trim();
				
				
				if (industryCodeCompleteStringValue.contains("::")) {
					industryCode=industryCode.substring(1, (industryCode.length()-1)); // excluding first and last character i.e. separator '[' and ']'
					productValue=productValue.substring(1, (productValue.length()-1)); // excluding first and last character i.e. separator '[' and ']'	
					
					
				}else{
					// below code is for if only single industry and product when someone forgot to remove [ and ] this symbol, so to avoid failure because of this we are removing through below code
					industryCode=industryCode.replace("[", "").trim();
					industryCode=industryCode.replace("]", "").trim();
					
					productValue=productValue.replace("[", "").trim();
					productValue=productValue.replace("]", "").trim();
				}
					int indusC = fngFindRow_usingForPagingToNotThrowErrorIfNotFoundInCurrentPage("IndustryCodeProductTable", industryCode, 2);
					int indusPDetail = fngFindRow_usingForPagingToNotThrowErrorIfNotFoundInCurrentPage("IndustryCodeProductTable", productValue, 3);

					if (indusC > 0) {
						fnpMymsg("@  --- Industry code '" + industryCode + "' is already present in row no--" + indusC);
						fnpMymsg("@  --- product detail '" + productValue + "' is already present in row no--" + indusPDetail);
						if (indusC != indusPDetail) {
							fnpMymsg("@  --- Industry code '" + industryCode + "' and product detail '" + productValue
									+ "' is NOT already present in same row no--" + indusC);
							fnpMymsg("@  ---So going to add them again.");
							//addingIndustryCodeAndProductDetail();
							addingIndustryCodeAndProductDetail(industryCode,productValue);
							
							indusC = fngFindRow_usingForPagingToNotThrowErrorIfNotFoundInCurrentPage("IndustryCodeProductTable", industryCode, 2);
							indusPDetail = fngFindRow_usingForPagingToNotThrowErrorIfNotFoundInCurrentPage("IndustryCodeProductTable", productValue, 3);
							fnpMymsg("@  --- Industry code '" + industryCode + "' is now present in row no--" + indusC);
							fnpMymsg("@  --- product detail '" + productValue + "' is now present in row no--" + indusPDetail);
							if (indusC != indusPDetail) {
								throw new Exception(" Row has not been added properly in Industry Code Product table in same row when tried again. ");
							}

						} else {
							// nothing to do
						}

					} else {
						fnpMymsg("@  --- Industry code '" + industryCode + "' is already NOT present , so going to add it.");
						//addingIndustryCodeAndProductDetail();
						Thread.sleep(10);
						addingIndustryCodeAndProductDetail(industryCode,productValue);
											
						//indusC = fngFindRow_usingForPagingToNotThrowErrorIfNotFoundInCurrentPage("IndustryCodeProductTable", industryCode, 2);
						//indusPDetail = fngFindRow_usingForPagingToNotThrowErrorIfNotFoundInCurrentPage("IndustryCodeProductTable", productValue, 3);
						
						
						if (classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.Sus_CampusTestCaseName)){
							indusC = fngFindRow_usingForPagingToNotThrowErrorIfNotFoundInCurrentPage("IndustryCodeProductTable", industryCode, 2);
							indusPDetail = fngFindRow_usingForPagingToNotThrowErrorIfNotFoundInCurrentPage("IndustryCodeProductTable", productValue, 3);
							fnpMymsg("@  --- Industry code '" + industryCode + "' is now present in row no--" + indusC);
							fnpMymsg("@  --- product detail '" + productValue + "' is now present in row no--" + indusPDetail);
							if (indusC != indusPDetail) {
								throw new Exception(" Row has not been added properly in Industry Code Product table in same row when tried again. ");
							}
						}else{
							if (classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.Cert_StandardsTestCaseName)) {
								String[] expectingIndustryInTableAfterRemovingNumberAndDotArray = industryCode.split("\\.");
								String expectingIndustryInTableAfterRemovingNumberAndDot=expectingIndustryInTableAfterRemovingNumberAndDotArray[1].trim();
								String expectingProductValue=productValue.replace(":", ",");
								indusC = fnpFindRowContainsName_usingForPagingToNotThrowErrorIfNotFoundInCurrentPage("IndustryCodeProductTable", expectingIndustryInTableAfterRemovingNumberAndDot, 2);
								
								//indusPDetail = fnpFindRowContainsName_usingForPagingToNotThrowErrorIfNotFoundInCurrentPage("IndustryCodeProductTable", expectingProductValue, 3);
								
								String [] expectingProductValueArray=expectingProductValue.split(",");
								int temp=0;
								for(int t=0;t<expectingProductValueArray.length;t++){
									
									if(t>0){
										 temp = indusPDetail;
									}
									
									indusPDetail = fnpFindRowContainsName_usingForPagingToNotThrowErrorIfNotFoundInCurrentPage("IndustryCodeProductTable", expectingProductValueArray[t], 3);

									if (t>0) {
										if (temp != indusPDetail) {
											throw new Exception(" Row has not been added properly in Industry Code Product table in same row when tried again. ");
										}
									}
									
								}
								
								
								fnpMymsg("@  --- Industry code '" + industryCode + "' is now present in row no--" + indusC);
								fnpMymsg("@  --- product detail '" + productValue + "' is now present in row no--" + indusPDetail);
								if (indusC != indusPDetail) {
									throw new Exception(" Row has not been added properly in Industry Code Product table in same row when tried again. ");
								}
							}
						}
					}
			}		
			fnpPFList("SizeUnitPFList", "SizeUnitPFListOptions", (String) hashXlData.get("Facility_Size"));
			//fnpLoading_wait();
			int rowbeforeAddingInFacilitySizeTable = fnpCountRowsInTable("FacilitySizeTable");
			fnpMymsg("Before  deleting (if present)  Facility Size --no. of rows present are -- " + rowbeforeAddingInFacilitySizeTable);

			
			fnpDeleteFacilitySizeWhichIsAlreadyAdded();
			fngDeleteFacilityEmployeeCountWhichIsAlreadyAdded() ;
			
			rowbeforeAddingInFacilitySizeTable = fnpCountRowsInTable("FacilitySizeTable");
			fnpMymsg("Before  adding  Facility Size --no. of rows present are -- " + rowbeforeAddingInFacilitySizeTable);
	
			
			
			String facilityType = (String) hashXlData.get("FacilitySizeTable_Type");
			String[] noOfFacilityTypeArray = facilityType.split(":");
			int noOfFacilityTypeArraySize = noOfFacilityTypeArray.length;
			String amountValue = (String) hashXlData.get("Amount");
			String[] amountArray = amountValue.split(":");
			int amountArraySize = amountArray.length;

			int expectedTotalAmount = 0;
			for (int i = 0; i < noOfFacilityTypeArraySize; i++) {
				fnpClick_OR("FacilitySizeAddBtn");
				//String typeXpath = OR_SUS.getProperty("FacilitySizeTypePFListPart1") + (rowbeforeAddingInFacilitySizeTable + i) + OR_SUS.getProperty("FacilitySizeTypePFListPart2");
				String typeXpath = OR_SUS.getProperty("FacilitySizeTypePFListPart1") +  i + OR_SUS.getProperty("FacilitySizeTypePFListPart2");
				fnpPFListModify_NOR(typeXpath, noOfFacilityTypeArray[i]);
				//String amountXpath = OR_SUS.getProperty("FacilitySizeAmountPart1") + (rowbeforeAddingInFacilitySizeTable + i) + OR_SUS.getProperty("FacilitySizeAmountPart2");
				String amountXpath = OR_SUS.getProperty("FacilitySizeAmountPart1") +  i + OR_SUS.getProperty("FacilitySizeAmountPart2");
				fngType("", amountXpath, amountArray[i]);
				expectedTotalAmount = expectedTotalAmount + (Integer.parseInt(amountArray[i]));
				String saveFloppyIconXpath = OR_SUS.getProperty("FacilitySizeSaveFloppyIconPart1") + (rowbeforeAddingInFacilitySizeTable + i)
						+ OR_SUS.getProperty("FacilitySizeSaveFloppyIconPart2");
				
				fnpClick_NOR(saveFloppyIconXpath);
			//	Thread.sleep(1000);
				
			//	fnpClick_NOR_withoutWait(saveFloppyIconXpath);
			}
			
			
			fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
			
			int rowAfterAddingInFacilitySizeTable = fnpCountRowsInTable("FacilitySizeTable");
			fnpMymsg("After adding Facility Size --no. of rows present are -- " + rowAfterAddingInFacilitySizeTable);
			Assert.assertEquals(rowAfterAddingInFacilitySizeTable, (rowbeforeAddingInFacilitySizeTable + noOfFacilityTypeArraySize),
					" Row has not been added properly in Facility Size table. ");

			int amountColIndex = fnpFindColumnIndex("FacilitySizeTableHeader", "Amount");
			String totalAmountxpath = OR_SUS.getProperty("FacilityTotalAmountxpath_part1") + amountColIndex + OR_SUS.getProperty("FacilityTotalAmountxpath_part2");

			String actualAmountValueString = fnpGetText_NOR(totalAmountxpath);
			int actualAmountValue = Integer.parseInt(actualAmountValueString);

			fnpMymsg("Actual amount coming is --" + actualAmountValue);
			fnpMymsg("Expected amount coming is --" + expectedTotalAmount);

			//Assert.assertEquals(actualAmountValue, expectedTotalAmount, "Facility total amount is not matched.");
			
			int totalAmountWhileCounter=0;
			while(true){
				totalAmountWhileCounter++;
				
				 if (actualAmountValue!=expectedTotalAmount) {
					 Thread.sleep(1000);
					 
					 totalAmountxpath = OR_SUS.getProperty("FacilityTotalAmountxpath_part1") + amountColIndex + OR_SUS.getProperty("FacilityTotalAmountxpath_part2");
					 actualAmountValueString = fnpGetText_NOR(totalAmountxpath);
					 actualAmountValue = Integer.parseInt(actualAmountValueString);	
				} else {
					break;
				}
				

				 if (totalAmountWhileCounter > 60) {
					 fnpMymsg("@   ---Facility total amount is not matched --"+totalAmountWhileCounter);
					break;
				}
				 

				
			}
			
			
			Assert.assertEquals(actualAmountValue, expectedTotalAmount, "Facility total amount is not matched.");
			
			fnpMymsg("Facility total amount is  matched.");

			
			int rowbeforeAddingInFacilityEmployeeCountTable = fnpCountRowsInTable("FacilityEmployeeCountTable");
			fnpMymsg("Before adding Facility Employee Count --no. of rows present are -- " + rowbeforeAddingInFacilityEmployeeCountTable);
			
			String shiftValue = (String) hashXlData.get("Shift");
			String[] shiftValueArray = shiftValue.split("::");
			int noOfshiftValueArraySize = shiftValueArray.length;
			
			String empCountValue = (String) hashXlData.get("Employee_Count");
			String[] empCountValueArray = empCountValue.split("::");
			int empCountValueArraySize = empCountValueArray.length;
			
			
			String startValue = (String) hashXlData.get("Start");
			String[] startValueArray = startValue.split("::");
			int startValueArraySize = startValueArray.length;
			
			
			String endValue = (String) hashXlData.get("End");
			String[] endValueArray = endValue.split("::");
			int endValueArraySize = endValueArray.length;
			
			
			
			
			
			
			for (int i = 0; i < noOfshiftValueArraySize; i++) {
				fnpClick_OR("FacilityEmployeeCount_AddBtn");
				String shiftXpath = OR_SUS.getProperty("FacilityEmployeeCountShiftPart1") + 0 + OR_SUS.getProperty("FacilityEmployeeCountShiftPart2");
				fngType("", shiftXpath, shiftValueArray[i].trim());
				
				String empCountXpath = OR_SUS.getProperty("FacilityEmployeeCountPart1") + 0 + OR_SUS.getProperty("FacilityEmployeeCountPart2");
				fngType("", empCountXpath, empCountValueArray[i].trim());
				String startXpath = OR_SUS.getProperty("FacilityEmployeeStartPart1") + 0 + OR_SUS.getProperty("FacilityEmployeeStartPart2");
				fngType("", startXpath, startValueArray[i].trim());
				String endXpath = OR_SUS.getProperty("FacilityEmployeeEndPart1") + 0 + OR_SUS.getProperty("FacilityEmployeeEndPart2");
				fngType("", endXpath, endValueArray[i].trim());
				
				String saveFloppyIconInFacilityEmpCountXpath = OR_SUS.getProperty("FacilityEmpCountSaveFloppyIconPart1") + 0 + OR_SUS.getProperty("FacilityEmpCountSaveFloppyIconPart2");
				
				fnpClick_NOR(saveFloppyIconInFacilityEmpCountXpath);
				Thread.sleep(1000);
			
			}
			

			
			int rowAfterAddingInFacilityEmployeeCountTable = fnpCountRowsInTable("FacilityEmployeeCountTable");
			fnpMymsg("After adding Facility Employee Count --no. of rows present are -- " + rowAfterAddingInFacilityEmployeeCountTable);
			Assert.assertEquals(rowAfterAddingInFacilityEmployeeCountTable, (rowbeforeAddingInFacilityEmployeeCountTable + noOfshiftValueArraySize),
					" Row has not been added properly in Facility Employee Count table. ");

			
			
			
			
			
			int rowbeforeAddingInFacilityStandardContactsTable = fnpCountRowsInTable("FacilityStandardContactsTable");
			fnpMymsg("Before adding Facility Standard Contacts --no. of rows present are -- " + rowbeforeAddingInFacilityStandardContactsTable);
			fnpClick_OR("FacilityStandardContactsAddBtn");
			//fnpPFListByLiNo("ContactFromPLandCOPFList", "ContactFromPLandCOPFListOptions", 2);
			fnpPFListByLiNo("ContactFromPLandCOPFList", "ContactFromPLandCOPFListOptions", 1);
			fnpLoading_waitInsideDialogBox();

			String contactFromCOAndPLType = (String) hashXlData.get("Type").trim();
			String contactFromCOAndPLTypeArray[] = contactFromCOAndPLType.split(":");
			int contactFromCOAndPLTypeSize = contactFromCOAndPLTypeArray.length;
			String chkBxLabelXpath;
			String chkBxXpath;
			String checkedStatusClass;
			for (int i = 0; i < contactFromCOAndPLTypeSize; i++) {
				chkBxLabelXpath = OR_SUS.getProperty("FacilityStandardContactsAddress_TypeChkBxLabel_part1") + contactFromCOAndPLTypeArray[i]
						+ OR_SUS.getProperty("FacilityStandardContactsAddress_TypeChkBxLabel_part2");
				chkBxXpath = chkBxLabelXpath + "/../../td[1]/div/div[2]";
				checkedStatusClass = driver.findElement(By.xpath(chkBxXpath)).getAttribute("class");
				if (!(checkedStatusClass.contains("ui-state-active"))) {
					//driver.findElement(By.xpath(chkBxLabelXpath)).click();
					fnpClick_NOR_withoutWait(chkBxLabelXpath);
					fnpLoading_wait();
					Thread.sleep(1000);
					
				}

			}
			fnpClick_OR("Save&ReturnBtnInFacilityStandContDialBx");
			fnpLoading_waitInsideDialogBox();
	
			
/*			
			//This field is only coming in ISO9001_SingleWo_InProcess
			if ((classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.WOISOSingleWOTillInProcessTestCaseName))
					|(classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.WOISOCorporateWOTillInProcessTestCaseName))
					|(classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.WOISOCampusWOTillInProcessTestCaseName))
					) {
				fnpPFList("FacilityDesignResponsiblePFList", "FacilityDesignResponsiblePFListOptions", (String) hashXlData.get("Facility_Design_Responsible"));
			}
			
			*/
			
			
			/*fnpClick_OR("Save&ReturnBtnInFacilityStandContDialBx");
			fnpLoading_waitInsideDialogBox();// uncomment on 14-07-16...comment it later  uncomment it because Filter box for language not come as it click this field while in loading so filter box not come
*/
			if ((classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.Cert_StandardsTestCaseName))) {

				fnpClick_OR("AuditReportForLanguage");
				fnpClick_OR("AuditReportForLanguageFilterChkBx");

				fnpClick_OR("AuditReportForLanguageFilterChkBx");

				fnpClick_OR("AuditReportForLanguage_OKLink");

				fnpClick_OR("AuditReportForLanguage");


				String AuditReport_Language = (String) hashXlData.get("AuditReport_Language").trim();
				String AuditReport_LanguageArray[] = AuditReport_Language.split(":");
				int AuditReport_LanguageArraySize = AuditReport_LanguageArray.length;
				String languageLabelXpath;


				languageLabelXpath = ".//*[@id='mainForm:tabView:audRepDrm_panel']/div[2]/ul/li/label";
				List<WebElement> languagesElement = driver.findElements(By.xpath(languageLabelXpath));
				int languagesElementSize = languagesElement.size();

				WebElement hoverElement;
				Actions action;
				String currentValue;
				int selectionCounter = 0;
				for (int i1 = 0; i1 < languagesElementSize; i1++) {

					hoverElement = languagesElement.get(i1);
					action = new Actions(driver);
					action.moveToElement(hoverElement).perform();
					// Thread.sleep(500);
					action.moveToElement(hoverElement).sendKeys(Keys.ARROW_DOWN).build().perform();
					// Thread.sleep(500);

					currentValue = hoverElement.getText();
					if (AuditReport_Language.contains(currentValue)) {
						fnpMymsg("@  --now going to select/click on ---" + currentValue);
						Thread.sleep(1000);
						hoverElement.click();
						Thread.sleep(1000);
						selectionCounter++;
						fnpMymsg("@  -- selected/clicked on ---" + currentValue);
						fnpMymsg("@  -- selectionCounter value is just after clicked '" + currentValue + "' is -- -" + selectionCounter);
					}


					if (selectionCounter == (AuditReport_LanguageArraySize)) {
						fnpMymsg("@  -- selectionCounter value is--" + selectionCounter);
						fnpMymsg("@  -- AuditReport_LanguageArraySize value is--" + AuditReport_LanguageArraySize);
						fnpMymsg("both are equal");
						fnpClick_OR("AuditReportForLanguage_OKLink");
						break;
					}

				}

				fngType("OR_SUS", "DistinctManufacturingProcessesTxtBx", (String) hashXlData.get("Distinct_Manufacturing_Processes"));
				fngType("OR_SUS", "UniqueHACCPPlansTxtBx", (String) hashXlData.get("Unique_HACCP_Plans"));
				fngType("OR_SUS", "NoofProductionLinesTxtBx", (String) hashXlData.get("No_of_Production_Lines"));

			}

			fnpClick_OR("ISRSFTabSaveBtn");

			fnpWaitForVisibleHavingMultipleElements("TopMessage3");
			fnpMymsg("Top Message after create ----" + fnpGetText_OR("TopMessage3"));

			String SuccessfulMsg = fnpGetText_OR("TopMessage3");

			Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"),
					"Top message does not contain 'success' word, so might be data on Standard Facility tab has not been saved successfully");

			fnpMymsg("Data on Standard Facility tab has  been saved successfully");

	
			
/*			if ((classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.WOISOCorporateWOTillInProcessTestCaseName))) {				
				fnpClick_OR("ISRInfoTab_EditWO");
				fnpPFListModify_NOR(OR_SUS.getProperty("ISRInfoTab_WOStatusPFList"), "INREVIEW");				
			}
			*/
			
			fnpMymsg("===============================================================================================================");

			// }

		} catch (Throwable t) {
			//isTestPass = true;
			fnpMymsg(" **************************************************************");
			fnpCommonFinalCatchBlock(t, "  Standard_Facility_Tab flow  is fail . ", "Standard_Facility_Tab_Fail");

		}

	}
	

}
