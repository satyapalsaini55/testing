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

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

import nsf.ecap.ISR_suite.TestSuiteBase_ISR_suite;
import nsf.ecap.Work_Order_suite.TestSuiteBase;
import nsf.ecap.config.IPULSE_CONSTANTS;
import nsf.ecap.util.ErrorUtil;
import nsf.ecap.util.TestUtil;

public class CreateWOforSustainabilitySingle extends TestSuiteBase_Sustain_Suite{
	String deskAuditTaskTab_expectedBillCode = "";
	double deskAuditTaskTab_expectedQuantity = 0;
	double deskAuditTaskTab_expectedAuditDays = 0;
	String certAuditTaskTab_expectedBillCode = "";
	double certAuditTaskTab_expectedQuantity = 0;
	double certAuditTaskTab_expectedAuditDays = 0;
	
	// Runmode of test case in a suite
	@BeforeTest
	@Parameters({ "className" })
	public void checkTestSkip(String className) throws Throwable {
		try {
			if (className.isEmpty()) {
				className = this.getClass().getSimpleName();
			}
			classNameText = className;
			currentScriptCode = fnpMappingClassNameWithScenarioCode(classNameText).trim();

			fnpMymsg("             ");
			fnpMymsg("             ");
			fnpMymsg("             ");
			fnpMymsg("             ");
			fnpMymsg("             ");
			fnpMymsg("=========================================================================================================================================");
			fnpMymsg("Checking Runmode of testcase '" + className + "' .");

			if (!TestUtil.isTestCaseRunnable(currentSuiteXls, className)) {

				fnpMymsg("Skipping Test Case" + className + " as runmode set to NO");
				fnpMymsg("=========================================================================================================================================");
				throw new SkipException("Skipping Test Case" + className + " as runmode set to NO");
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
			fngCommonFinalCatchBlock(t, "  checkTestSkip method  is fail . ", "checkTestSkipFail");
		}
	}

	@Test(priority = 1)
	public void CreateWOFlow() throws Throwable {
		fnpMymsg(" **************************************************************");
		fnpMymsg(" Executing  SUST_ISO CreateWOFlow");
		try {
			if ((classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.Sus_SingleTestCaseName))
					|(classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.Cert_StandardsTestCaseName))
					) {
				fnpMymsg("@  ---Before query starts for test case ---"+classNameText);
				dropWOSusandDeleteSomeDataFromDB((String) hashXlData.get("Corp/Facility#"),(String) hashXlData.get("Standard"));
				//dropWOSusandDeleteSomeDataFromDB("45304", "QMSI9K15");
				fnpMymsg("@  ---After query finishes for test case ---"+classNameText);
				}
			createSusWO_excludingDBDropQuery();
			fnpMymsg(" **************************************************************");
		} catch (Throwable t) {
			fngCommonFinalCatchBlock(t, "  Create Work Order flow  is fail . ", "CreateWorkOrderFail");
		}
	}
	@Test(priority = 2, dependsOnMethods = { "CreateWOFlow" })
	public void Standard_Facility_Tab() throws Throwable {
		try {
			fnpMymsg(" **************************************************************");
			fnpMymsg(" Executing Standard_Facility_Tab");
			//Code Start for IPM-13919 15/Oct/2020	
			fngClick_OR("Accreditation_Panel");
			Thread.sleep(2000);
			fngClick_OR("FacilityInfo_Panel");
			Thread.sleep(2000);
			fngClick_OR("FacilityStndrdCycleInfo");
			Thread.sleep(2000);
			fngClick_OR("FacilityStndrdInfo");
			Thread.sleep(2000);
			//Code End for IPM-13919 15/Oct/2020
			callRemoveAlreadyAddedIndustryCodeUsingScript() ;
		
			
			fngType("OR_SUS", "ScopeofthefacilityTxtBx", (String) hashXlData.get("Scope_of_the_facility"));
			fngType("OR_SUS", "Exclusion_of_the_facilityTxtBx", (String) hashXlData.get("Exclusion_of_the_facility"));
			
			
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
						Thread.sleep(1000);
						addingIndustryCodeAndProductDetail(industryCode,productValue);
											
						//indusC = fngFindRow_usingForPagingToNotThrowErrorIfNotFoundInCurrentPage("IndustryCodeProductTable", industryCode, 2);
						//indusPDetail = fngFindRow_usingForPagingToNotThrowErrorIfNotFoundInCurrentPage("IndustryCodeProductTable", productValue, 3);
						
						
						if (classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.Sus_SingleTestCaseName)){
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
			//code for select Supporting Certifications & certified n uncertified vendors 
			
			fngcheckSupportingCertification((String) hashXlData.get("NSF_Supporting_Certification"));
			fngType("OR_SUS", "Certified_Vendor_Count_Fm_txtbx",(String) hashXlData.get("CERTIFIED_VENDOR_COUNT_FM"));
			fngType("OR_SUS", "Certified_Vendor_Count_Nonfm_txtbx",(String) hashXlData.get("CERTIFIED_VENDOR_COUNT_NONFM"));
			fngType("OR_SUS", "Uncertified_Vendor_Count_Fm_txtbx",(String) hashXlData.get("UNCERTIFIED_VENDOR_COUNT_FM"));
			fngType("OR_SUS", "Uncertified_Vendor_Count_Nonfm_txtbx",(String) hashXlData.get("UNCERTIFIED_VENDOR_COUNT_NONFM"));
			fngClick_OR("ISRSFTabSaveBtn");
			
			//Code Start for IPM-13919 15/Oct/2020
			Thread.sleep(2000);
			fngClick_OR("FacilityInfo_Panel");
			Thread.sleep(2000);
			//Code End for IPM-13919 15/Oct/2020
			
			fngPFList("SizeUnitPFList", "SizeUnitPFListOptions", (String) hashXlData.get("Facility_Size"));
			//fnpLoading_wait();
			int rowbeforeAddingInFacilitySizeTable = fnpCountRowsInTable("FacilitySizeTable");
			fnpMymsg("Before  deleting (if present)  Facility Size --no. of rows present are -- " + rowbeforeAddingInFacilitySizeTable);

			
			fnpDeleteFacilitySizeWhichIsAlreadyAdded();
			fngDeleteFacilityEmployeeCountWhichIsAlreadyAdded();
			
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
				//fnpClick_OR("FacilitySizeAddBtn");
				fngClick_OR("FacilitySizeAddBtn");
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
			
			//int rowAfterAddingInFacilitySizeTable = fnpCountRowsInTable("FacilitySizeTable");
			int rowAfterAddingInFacilitySizeTable = fngCountRowsInTable("FacilitySizeTable");
			fnpMymsg("After adding Facility Size --no. of rows present are -- " + rowAfterAddingInFacilitySizeTable);
			Assert.assertEquals(rowAfterAddingInFacilitySizeTable, (rowbeforeAddingInFacilitySizeTable + noOfFacilityTypeArraySize),
					" Row has not been added properly in Facility Size table. ");

			//int amountColIndex = fnpFindColumnIndex("FacilitySizeTableHeader", "Amount");
			int amountColIndex = fngFindColumnIndex("FacilitySizeTableHeader", "Amount");
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

			
			int rowbeforeAddingInFacilityEmployeeCountTable = fngCountRowsInTable("FacilityEmployeeCountTable");
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
				fngClick_OR("FacilityEmployeeCount_AddBtn");
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
			
			int rowAfterAddingInFacilityEmployeeCountTable = fngCountRowsInTable("FacilityEmployeeCountTable");
			fnpMymsg("After adding Facility Employee Count --no. of rows present are -- " + rowAfterAddingInFacilityEmployeeCountTable);
			Assert.assertEquals(rowAfterAddingInFacilityEmployeeCountTable, (rowbeforeAddingInFacilityEmployeeCountTable + noOfshiftValueArraySize),
					" Row has not been added properly in Facility Employee Count table. ");
			int rowbeforeAddingInFacilityStandardContactsTable = fngCountRowsInTable("FacilityStandardContactsTable");
			fnpMymsg("Before adding Facility Standard Contacts --no. of rows present are -- " + rowbeforeAddingInFacilityStandardContactsTable);
			fngClick_OR("FacilityStandardContactsAddBtn");
			//fnpPFListByLiNo("ContactFromPLandCOPFList", "ContactFromPLandCOPFListOptions", 2);
			fngPFListByLiNo("ContactFromPLandCOPFList", "ContactFromPLandCOPFListOptions", 1);
			fngLoading_waitInsideDialogBox();

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
					fngClick_NOR_withoutWait(chkBxLabelXpath);
					fngLoading_wait();
					Thread.sleep(1000);
					
				}

			}
			fngClick_OR("Save&ReturnBtnInFacilityStandContDialBx");
			fngLoading_waitInsideDialogBox();
	
			if ((classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.Cert_StandardsTestCaseName))) {

				fngClick_OR("AuditReportForLanguage");
				fngClick_OR("AuditReportForLanguageFilterChkBx");

				fngClick_OR("AuditReportForLanguageFilterChkBx");

				fngClick_OR("AuditReportForLanguage_OKLink");

				fngClick_OR("AuditReportForLanguage");


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

			fngClick_OR("ISRSFTabSaveBtn");// Modify it.

			fnpWaitForVisibleHavingMultipleElements("TopMessage3");
			fnpMymsg("Top Message after create ----" + fnpGetText_OR("TopMessage3"));

			String SuccessfulMsg = fnpGetText_OR("TopMessage3");

			Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"),
					"Top message does not contain 'success' word, so might be data on Standard Facility tab has not been saved successfully");

			fnpMymsg("Data on Standard Facility tab has  been saved successfully");
			fnpMymsg("===============================================================================================================");

		} catch (Throwable t) {
			fnpMymsg(" **************************************************************");
			fngCommonFinalCatchBlock(t, "  Standard_Facility_Tab flow  is fail . ", "Standard_Facility_Tab_Fail");

		}

	}
	
	
	@Test(priority = 3, dependsOnMethods = { "Standard_Facility_Tab" })

	public void Financial_Tab_And_InReviewToInProcess() throws Throwable {

		try {
			fngCommonCodeOfFinancialTabOfSingleWO_CorporateWO();
			 String textMessage = "";
			
			int taskIndex = fnpFindColumnIndex("AuditDurationTable_HeaderRow", "Task");
			int calculatedDurationIndex = fnpFindColumnIndex("AuditDurationTable_HeaderRow", "Calculated Duration");

			fnpMymsg(" Now going to compare the Audit Duration values .");

			fnpDisplayingMessageInFrame("Now going to compare the Audit Duration values .", 5);

			String durationSets = (String) hashXlData.get("Audit_Duration");
			String NoOfSets[] = durationSets.split("::");
			fnpMymsg("No. of duration sets are ---" + NoOfSets.length);
			fnpMymsg("");
			fnpMymsg("");

			int totalrowsInAuditDurationTable = fnpCountRowsInTable("AuditDurationTable");

			boolean foundThistask = false;
			for (int i = 0; i < NoOfSets.length; i++) {
				foundThistask = false;
				fnpMymsg("");
				fnpMymsg("");
				fnpMymsg("Each duration set value are--" + NoOfSets[i]);
				String auditSet[] = NoOfSets[i].split(",");
				String auditTaskName = fnpremoveFormatting_forISR(auditSet[0]);
				//String auditTaskName = fngremoveFormatting_forSus(auditSet[0]);
				fnpMymsg("Audit Task is--" + auditTaskName);

				String expectedAuditTaskDuration = fnpremoveFormatting_forISR(auditSet[1]);
				//String expectedAuditTaskDuration = fngremoveFormatting_forSus(auditSet[1]);
				fnpMymsg("Audit Task Duration is--" + expectedAuditTaskDuration);

				for (int j = 1; j <= totalrowsInAuditDurationTable; j++) {

					String taskName = fngFetchFromTable("AuditDurationTable", j, taskIndex);
					if (taskName.equalsIgnoreCase(auditTaskName)) {
						foundThistask = true;
						String taskduration = fngFetchFromTable("AuditDurationTable", j, calculatedDurationIndex);
						double actualTaskDuration = Double.parseDouble(taskduration);
						double expectedTaskDuration = Double.parseDouble(expectedAuditTaskDuration);
						if (actualTaskDuration != expectedTaskDuration) {
							textMessage = "Audit task duraton is not matched for task '" + taskName + "' as expected is --'" + expectedTaskDuration + "' but actual is --'"
									+ actualTaskDuration + "'.";
							fnpMymsg(textMessage);
							fnpDisplayingMessageInFrame(textMessage, 10);
							throw new Exception(textMessage);

						
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
				
				*//**************
			 * Matching bill code's quantity with having Audit work in bill
			 * code description
			 ******************/
	
			/************** Matching bill code's quantity for given bill code ******************/

			int taskTypeIndex = fnpFindColumnIndex("BillCodesTable_HeaderRow", WOISOFinancialTab_BillCodeTableColName_TaskTypeFeeType);
			int billCodesIndex = fnpFindColumnIndex("BillCodesTable_HeaderRow", WOISOFinancialTab_BillCodeTableColName_BillCodes);
			int quantityIndex = fnpFindColumnIndex("BillCodesTable_HeaderRow", WOISOFinancialTab_BillCodeTableColName_Quantity);

			textMessage = " Now going to compare the bill code quantity values .";
			fnpMymsg(textMessage);
			fnpDisplayingMessageInFrame(textMessage, 5);
			String billCodeQuantityString = (String) hashXlData.get("BillCodeTableMatch_Quantity_forGivenBillCode");

			String billCodeQuantitySets[] = billCodeQuantityString.split("::");
			fnpMymsg("No. of bill code quantity sets are ---" + billCodeQuantitySets.length);
			fnpMymsg("");
			fnpMymsg("");

			int totalrowsInBillCodesTable = fnpCountRowsInTable("BillCodesTable");

			boolean foundThistaskWithGivenBillCode = false;
			for (int i = 0; i < billCodeQuantitySets.length; i++) {
				foundThistaskWithGivenBillCode = false;
				fnpMymsg("");
				fnpMymsg("");
				fnpMymsg("Each bill code quantity set value are--" + billCodeQuantitySets[i]);
				String eachQuantitySet[] = billCodeQuantitySets[i].split(",");
				String expectedTaskTypeName = fnpremoveFormatting_forISR(eachQuantitySet[0]);
				//String expectedTaskTypeName = fngremoveFormatting_forSus(eachQuantitySet[0]);
				fnpMymsg("Expected Task type name is--" + expectedTaskTypeName);

				String expectedTaskQuantity = fnpremoveFormatting_forISR(eachQuantitySet[1]);
				//String expectedTaskQuantity = fngremoveFormatting_forSus(eachQuantitySet[1]);
				fnpMymsg("Expected Task Quantity is--" + expectedTaskQuantity);

				String expectedbillCode = fnpremoveFormatting_forISR(eachQuantitySet[2]);
				//String expectedbillCode = fngremoveFormatting_forSus(eachQuantitySet[2]);
				fnpMymsg("Expected bill code  is--" + expectedbillCode);

				
				
				
			if (expectedTaskTypeName.equalsIgnoreCase(WOISOTaskName_DeskAudit)) {
				deskAuditTaskTab_expectedBillCode =expectedbillCode;
			}
			
			
			if (expectedTaskTypeName.equalsIgnoreCase(WOISOTaskName_CertificationAudit)) {
				certAuditTaskTab_expectedBillCode =expectedbillCode;
			}
		/**************Check only any bill code is present for SQF cert and check for ISO********************/	
			if (!(expectedbillCode.equalsIgnoreCase("N/A")) ){	
				
				for (int j = 1; j <= totalrowsInBillCodesTable; j++) {

					String taskName = fngFetchFromTable("BillCodesTable", j, taskTypeIndex);
					String billcodeInTable = fngFetchFromTable("BillCodesTable", j, billCodesIndex);

					if ((taskName.equalsIgnoreCase(expectedTaskTypeName)) & (billcodeInTable.equalsIgnoreCase(expectedbillCode))) {
						foundThistaskWithGivenBillCode = true;
						String actualTaskQuantity = fngFetchFromTable("BillCodesTable", j, quantityIndex);

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

				}

			} 
				else{
				for (int j = 1; j <= totalrowsInBillCodesTable; j++) {

					String taskName = fngFetchFromTable("BillCodesTable", j, taskTypeIndex);
					String billcodeInTable = fngFetchFromTable("BillCodesTable", j, billCodesIndex);

					if ((taskName.equalsIgnoreCase(expectedTaskTypeName)) ) {
						foundThistaskWithGivenBillCode = true;
						String actualbillCode= fngFetchFromTable("BillCodesTable", j, billCodesIndex);

						if ( (actualbillCode.trim().equalsIgnoreCase("")) ){
							textMessage = "FAILED ==> Not a single Bill Code is  present.";
							fnpDisplayingMessageInFrame(textMessage, 10);
							throw new Exception(textMessage);
						} else {
							textMessage = "PASSED ==> Bill Code is present.";
							fnpDisplayingMessageInFrame(textMessage, 5);
							fnpMymsg(textMessage);
							break;
						}

					}
				}
			}

			}
			
			
			/*******************************************************************************************************************/

			//if (!(classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.Cert_StandardsTestCaseName)) ){
			if (   (!(classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.Cert_StandardsTestCaseName)) &
					(!(classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.Non_Cert_StandardsTestCaseName)) ))){
						
						/**************
						 * Check that Quantity of the following Bill codes have quantity as
						 * either 1 or 0
						 ******************/
			
						taskTypeIndex = fnpFindColumnIndex("BillCodesTable_HeaderRow", WOISOFinancialTab_BillCodeTableColName_TaskTypeFeeType);
						billCodesIndex = fnpFindColumnIndex("BillCodesTable_HeaderRow", WOISOFinancialTab_BillCodeTableColName_BillCodes);
						quantityIndex = fnpFindColumnIndex("BillCodesTable_HeaderRow", WOISOFinancialTab_BillCodeTableColName_Quantity);
			
						textMessage = " Now going to Check that Quantity of the following Bill codes have quantity as either 1 or 0 .";
						fnpDisplayingMessageInFrame(textMessage, 5);
						fnpMymsg(textMessage);
						billCodeQuantityString = (String) hashXlData.get("Following_billCode_have_quantity_either_zeroOrOne");
						String billCodeQuantitySets2[] = billCodeQuantityString.split("::");
			
						fnpMymsg("No. of bill code quantity sets are ---" + billCodeQuantitySets2.length);
						fnpMymsg("");
						fnpMymsg("");
			
						totalrowsInBillCodesTable = fnpCountRowsInTable("BillCodesTable");
						
						for ( int i = 0; i < billCodeQuantitySets2.length; i++) {
							foundThistaskWithGivenBillCode = false;
							fnpMymsg("");
							fnpMymsg("");
							fnpMymsg("Each bill code quantity set value are--" + billCodeQuantitySets2[i]);
							String eachQuantitySet[] = billCodeQuantitySets2[i].split(",");
							String expectedTaskTypeName = fnpremoveFormatting_forISR(eachQuantitySet[0]); 
							//String expectedTaskTypeName = fngremoveFormatting_forSus(eachQuantitySet[0]);
							fnpMymsg("Expected Task type name is--" + expectedTaskTypeName);
			
							String expectedTaskQuantity = fnpremoveFormatting_forISR(eachQuantitySet[1]);
							//String expectedTaskQuantity = fngremoveFormatting_forSus(eachQuantitySet[1]);
							fnpMymsg("Expected Task Quantity is--" + expectedTaskQuantity);
			
							String expectedbillCode = fnpremoveFormatting_forISR(eachQuantitySet[2]);
							//String expectedbillCode = fngremoveFormatting_forSus(eachQuantitySet[2]);
							fnpMymsg("Expected bill code  is--" + expectedbillCode);
			
							for (int j = 1; j <= totalrowsInBillCodesTable; j++) {
			
								String taskName = fngFetchFromTable("BillCodesTable", j, taskTypeIndex);
								String billcodeInTable = fngFetchFromTable("BillCodesTable", j, billCodesIndex);
			
								if ((taskName.equalsIgnoreCase(expectedTaskTypeName)) & (billcodeInTable.equalsIgnoreCase(expectedbillCode))) {
									foundThistaskWithGivenBillCode = true;
									String actualTaskQuantity = fngFetchFromTable("BillCodesTable", j, quantityIndex);
			
									double actualTaskQuantityInDouble = Double.parseDouble(actualTaskQuantity);
									double expectedTaskQuantityInDouble = Double.parseDouble(expectedTaskQuantity);
									if (actualTaskQuantityInDouble != expectedTaskQuantityInDouble) {
										textMessage = " FAILED ==>Actual Task Quantity is not matched for task type'" + taskName + "'  and for bill code '" + expectedbillCode
												+ "'    as expected is --'" + expectedTaskQuantityInDouble + "' but actual is --'" + actualTaskQuantityInDouble + "' at row no --" + j;
										fnpDisplayingMessageInFrame(textMessage, 10);
										fnpMymsg(textMessage);
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
										+ "' is not present in the table when we are  going to check that Quantity of the following Bill codes have quantity as either 1 or 0 .";
								fnpDisplayingMessageInFrame(textMessage, 10);
								fnpMymsg(textMessage);
								throw new Exception(textMessage);
			
							}
			
						}
			
						/*******************************************************************************************************************/
			
						fnpMymsg("===============================================================================================================");
						/**********************************************************************************************************************/
			
						fnpMymsg("");
						fnpMymsg("");
			
						fnpMymsg("===============================================================================================================");

			
			
			
						
						/**************
						 * Calculating Total Amount for all rows (not for specific bill
						 * code, so not picking any value of bill code for
						 * checking/calculating amount )
						 ******************/
			
						int billCodeScheduledPriceIndex = fnpFindColumnIndex("BillCodesTable_HeaderRow", WOISOFinancialTab_BillCodeTableColName_BillCodeScheduledPrice);
						quantityIndex = fnpFindColumnIndex("BillCodesTable_HeaderRow", WOISOFinancialTab_BillCodeTableColName_Quantity);
						int totalAmountIndex = fnpFindColumnIndex("BillCodesTable_HeaderRow", WOISOFinancialTab_BillCodeTableColName_TotalAmount);
			
						textMessage = " Calculating Total Amount for all rows (not for specific bill code, so not picking any value of bill code for checking/calculating amount )";
						fnpDisplayingMessageInFrame(textMessage, 5);
						fnpMymsg(" Now going to calculate and matching total amount .");
			
						totalrowsInBillCodesTable = fnpCountRowsInTable("BillCodesTable");
			
						for (int j = 1; j <= totalrowsInBillCodesTable; j++) {
			
							String billCodeScheduledPrice = fngFetchFromTable("BillCodesTable", j, billCodeScheduledPriceIndex);
							billCodeScheduledPrice = billCodeScheduledPrice.replaceAll(",", "");
			
							String quantity = fngFetchFromTable("BillCodesTable", j, quantityIndex);
							quantity = quantity.replaceAll(",", "");
			
							String totalAmount = fngFetchFromTable("BillCodesTable", j, totalAmountIndex);
							totalAmount = totalAmount.replaceAll(",", "");
			
							double billCodeScheduledPriceDouble = Double.parseDouble(billCodeScheduledPrice.replace("$", "").trim());
							double quantityDouble = Double.parseDouble(quantity);
							double actualTotalAmountDouble = Double.parseDouble(totalAmount.replace("$", "").trim());
			
							double expectedTotalAmountDouble = billCodeScheduledPriceDouble * quantityDouble;
			
							if (actualTotalAmountDouble != expectedTotalAmountDouble) {
								textMessage = "FAILED ==>Total amount is not matched in Bill Code table in row no.--'" + j + "'   as actual is -- '" + actualTotalAmountDouble
										+ "'  but expected is --'" + expectedTotalAmountDouble + "'.";
								fnpDisplayingMessageInFrame(textMessage, 10);
								fnpMymsg(textMessage);
								throw new Exception(textMessage);
							} else {
								textMessage = "PASSED ==>Total amount is  matched in Bill Code table in row no.--'" + j + "'   as actual is -- '" + actualTotalAmountDouble
										+ "'  and  expected is --'" + expectedTotalAmountDouble + "'.";
								fnpDisplayingMessageInFrame(textMessage, 5);
								fnpMymsg(textMessage);
							}
						}
			
						fnpMymsg("===============================================================================================================");
			
						/**********************************************************************************************************************/

			}
			
			
		if (!(classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.Non_Cert_StandardsTestCaseName)) ){	
			
			fnpClick_OR("WOISR_editBtnForEditingFinops");
			fnpWaitForVisible("WOISR_EditingFinops_SaveNCloseBtn");


			fnpMymsg("");
			fnpMymsg("");

			/************** Adjusted the duration ****************************************************/

			fnpMymsg("**************Going to Adjusted the duration**************************************");
			int taskColIndex = fnpFindColumnIndex("EditAuditDurationTable_HeaderRow",WOISOFinancialTab_AuditDurationTableColName_Task);
			int adjustedDurationColIndex = fnpFindColumnIndex("EditAuditDurationTable_HeaderRow", WOISOFinancialTab_AuditDurationTableColName_AdjustedDuration);


			String adjustedTabletask;

			textMessage = " Now going to adjust the duration values .";
			fnpDisplayingMessageInFrame(textMessage, 5);
			fnpMymsg(textMessage);
			billCodeQuantityString = (String) hashXlData.get("AdjustDurationForFollowingTask");
			String billCodeQuantitySets3[] = billCodeQuantityString.split("::");

			fnpMymsg("No. of bill code quantity sets for adjusted duration are ---" + billCodeQuantitySets3.length);
			fnpMymsg("");
			fnpMymsg("");

			int totalrowsInEditAdjustedDurationTable = fnpCountRowsInTable("EditAuditDurationTable");

			boolean foundThisBillCode;
			for (int i = 0; i < billCodeQuantitySets3.length; i++) {
				foundThisBillCode = false;
				fnpMymsg("");
				fnpMymsg("");
				fnpMymsg("Each bill code quantity set value are--" + billCodeQuantitySets3[i]);
				String eachQuantitySet[] = billCodeQuantitySets3[i].split(",");
				String expectedTaskTypeName = fnpremoveFormatting_forISR(eachQuantitySet[0]); 
				//String expectedTaskTypeName = fngremoveFormatting_forSus(eachQuantitySet[0]);
				fnpMymsg("Expected Task type name is--" + expectedTaskTypeName);

				String expectedTaskQuantity = fnpremoveFormatting_forISR(eachQuantitySet[1]);
				//String expectedTaskQuantity = fngremoveFormatting_forSus(eachQuantitySet[1]);
				fnpMymsg("Expected Task Quantity is--" + expectedTaskQuantity);

				for (int j = 1; j <= totalrowsInEditAdjustedDurationTable; j++) {
					String editDurationtaskName = fngFetchFromTable("EditAuditDurationTable", j, taskColIndex);

					String inputBxForEditDuration = OR_SUS.getProperty("AdjustedDurationInputBx_part1") + (j - 1) + OR_SUS.getProperty("AdjustedDurationInputBx_part2");

					if (editDurationtaskName.equalsIgnoreCase(expectedTaskTypeName)) {
						fngType("", inputBxForEditDuration, expectedTaskQuantity);
						foundThisBillCode = true;
					}
				}

				if (!foundThisBillCode) {
					textMessage = "Failed==> This task '" + expectedTaskTypeName + "' code is not present in edit duration table .";
					fnpDisplayingMessageInFrame(textMessage, 10);
					fnpMymsg(textMessage);
					throw new Exception(textMessage);

				}

			}

			fngType("OR_SUS", "AdjustmentReasonTxtBx", (String) hashXlData.get("Adjustment_reason"));

			fngPFList("AuditCostFlagDD", "AuditCostFlaglistOptions", "Yes");
			
			fnpClick_OR("WOISR_EditingFinops_SaveNCloseBtn");

			/*******************************************************************************************************************/



			/**************************** Recheck Adjusted Duration ***************************************************************************************/

			taskIndex = fnpFindColumnIndex("AuditDurationTable_HeaderRow", "Task");
			int adjustedDurationIndex = fnpFindColumnIndex("AuditDurationTable_HeaderRow", WOISOFinancialTab_AuditDurationTableColName_AdjustedDuration);
			calculatedDurationIndex = fnpFindColumnIndex("AuditDurationTable_HeaderRow", WOISOFinancialTab_AuditDurationTableColName_CalculatedDuration);

			textMessage = " Now going to compare the Re-adusted Audit Duration values .";
			fnpDisplayingMessageInFrame(textMessage, 5);
			fnpMymsg(textMessage);
			durationSets = (String) hashXlData.get("AdjustDurationForFollowingTask");
			String NoOfSets2[] = durationSets.split("::");
			fnpMymsg("No. of duration sets are ---" + NoOfSets2.length);
			fnpMymsg("");
			fnpMymsg("");

			totalrowsInAuditDurationTable = fnpCountRowsInTable("AuditDurationTable");

			for (int i = 0; i < NoOfSets2.length; i++) {
				foundThisBillCode = false;
				fnpMymsg("");
				fnpMymsg("");
				fnpMymsg("Each duration set value are--" + NoOfSets2[i]);
				String auditSet[] = NoOfSets2[i].split(",");
				String auditTaskName = fnpremoveFormatting_forISR(auditSet[0]);
				//String auditTaskName = fngremoveFormatting_forSus(auditSet[0]);
				fnpMymsg("Audit Task is--" + auditTaskName);

				String expectedAuditTaskDuration = fnpremoveFormatting_forISR(auditSet[1]);
				//String expectedAuditTaskDuration = fngremoveFormatting_forSus(auditSet[1]);
				fnpMymsg("Audit Task Duration is--" + expectedAuditTaskDuration);

				for (int j = 1; j <= totalrowsInAuditDurationTable; j++) {

					String taskName = fngFetchFromTable("AuditDurationTable", j, taskIndex);

	

					/****
					 * Fetching here value and going to use for Audit days in further steps
					 * later
					 *****/					
				if (auditTaskName.equalsIgnoreCase(WOISOTaskName_CertificationAudit)) {
					if (taskName.equalsIgnoreCase(WOISOTaskName_DeskAudit)) {
						String taskduration = fngFetchFromTable("AuditDurationTable", j, calculatedDurationIndex);
						deskAuditTaskTab_expectedAuditDays = Double.parseDouble(taskduration);
					}
					
					if (taskName.equalsIgnoreCase(WOISOTaskName_CertificationAudit)) {
						String CertTaskduration = fngFetchFromTable("AuditDurationTable", j, adjustedDurationColIndex);
						//certAuditTaskTab_expectedAuditDays = Double.parseDouble(CertTaskduration);
						
						if (!(CertTaskduration.trim().equalsIgnoreCase(""))) {
							certAuditTaskTab_expectedAuditDays = Double.parseDouble(CertTaskduration);
						} else {
							throw new Exception("Adjusted Duration for this task '"+WOISOTaskName_CertificationAudit+"' is not displayed/present in row -"+j);
						}
						
						
						
					}
				}else{
					if (auditTaskName.equalsIgnoreCase(WOISOTaskName_DeskAudit)) {
						if (taskName.equalsIgnoreCase(WOISOTaskName_DeskAudit)) {
							String taskduration = fngFetchFromTable("AuditDurationTable", j, adjustedDurationColIndex);
							//deskAuditTaskTab_expectedAuditDays = Double.parseDouble(taskduration);
							if (!(taskduration.trim().equalsIgnoreCase(""))) {
								deskAuditTaskTab_expectedAuditDays = Double.parseDouble(taskduration);
							} else {
								throw new Exception("Adjusted Duration for this task '"+WOISOTaskName_DeskAudit+"' is not displayed/present in row -"+j);
							}
							
						}
						
						if (taskName.equalsIgnoreCase(WOISOTaskName_CertificationAudit)) {
							String CertTaskduration = fngFetchFromTable("AuditDurationTable", j, calculatedDurationIndex);
							certAuditTaskTab_expectedAuditDays = Double.parseDouble(CertTaskduration);
						}
					}
				}
				
				
				
				
					/****************************************************************/

					if (taskName.equalsIgnoreCase(auditTaskName)) {
						foundThisBillCode = true;
						String taskduration = fngFetchFromTable("AuditDurationTable", j, adjustedDurationIndex);
						double actualTaskDuration = Double.parseDouble(taskduration);
						double expectedTaskDuration = Double.parseDouble(expectedAuditTaskDuration);
						if (actualTaskDuration != expectedTaskDuration) {
							textMessage = "FAILED -->Audit task duraton is not matched for task '" + taskName + "' as expected is --'" + expectedTaskDuration
									+ "' but actual is --'" + actualTaskDuration + "'.";
							fnpDisplayingMessageInFrame(textMessage, 10);
							fnpMymsg(textMessage);
							throw new Exception(textMessage);

						} else {
							textMessage = "Audit task duraton is  matched successfully for task '" + taskName + "' as expected is --'" + expectedTaskDuration
									+ "' and actual is --'" + actualTaskDuration + "'.";
							fnpDisplayingMessageInFrame(textMessage, 5);
							fnpMymsg(textMessage);
							// break;
						}

					}
				}

				if (!foundThisBillCode) {
					textMessage = "This task '" + auditTaskName + "' code is not present in  duration table .";
					fnpDisplayingMessageInFrame(textMessage, 10);
					fnpMymsg(textMessage);
					throw new Exception(textMessage);

				}

			}

			/*******************************************************************************************************************/

			
			if (!(classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.Cert_StandardsTestCaseName)) ){
			
						/************** Re-Matching bill code's quantity for given ADJUSTED bill code ******************/
			
						
						textMessage = " Now going to RE-COMPARE  the bill code quantity values again .";
						fnpDisplayingMessageInFrame(textMessage, 5);
						
						taskTypeIndex = fnpFindColumnIndex("BillCodesTable_HeaderRow", WOISOFinancialTab_BillCodeTableColName_TaskTypeFeeType);
						billCodesIndex = fnpFindColumnIndex("BillCodesTable_HeaderRow", WOISOFinancialTab_BillCodeTableColName_BillCodes);
						quantityIndex = fnpFindColumnIndex("BillCodesTable_HeaderRow", WOISOFinancialTab_BillCodeTableColName_Quantity);
			
						
						int totalAmountIndex = fnpFindColumnIndex("BillCodesTable_HeaderRow", WOISOFinancialTab_BillCodeTableColName_TotalAmount);
						int billCodeScheduledPriceIndex = fnpFindColumnIndex("BillCodesTable_HeaderRow", WOISOFinancialTab_BillCodeTableColName_BillCodeScheduledPrice);
						
						
						
						
			
			
						fnpMymsg(textMessage);
						billCodeQuantityString = (String) hashXlData.get("AdjustDurationForFollowingTask");
						String billCodeQuantitySets4[] = billCodeQuantityString.split("::");
						fnpMymsg("No. of bill code quantity sets are ---" + billCodeQuantitySets4.length);
						fnpMymsg("");
						fnpMymsg("");
			
						totalrowsInBillCodesTable = fnpCountRowsInTable("BillCodesTable");
						String taskName = null;
						for (int i = 0; i < billCodeQuantitySets4.length; i++) {
							foundThisBillCode = false;
							fnpMymsg("");
							fnpMymsg("");
							fnpMymsg("Each bill code quantity set value are--" + billCodeQuantitySets4[i]);
							String eachQuantitySet[] = billCodeQuantitySets4[i].split(",");
							String expectedTaskTypeName = fnpremoveFormatting_forISR(eachQuantitySet[0]); //fngremoveFormatting
							//String expectedTaskTypeName = fngremoveFormatting_forSus(eachQuantitySet[0]);
							fnpMymsg("Expected Task type name is--" + expectedTaskTypeName);
			
							String expectedTaskQuantity = fnpremoveFormatting_forISR(eachQuantitySet[1]);
							//String expectedTaskQuantity = fngremoveFormatting_forSus(eachQuantitySet[1]);
							fnpMymsg("Expected Task Quantity is--" + expectedTaskQuantity);
			
							String expectedbillCode = fnpremoveFormatting_forISR(eachQuantitySet[2]);
							//String expectedbillCode = fngremoveFormatting_forSus(eachQuantitySet[2]);
							fnpMymsg("Expected bill code  is--" + expectedbillCode);
			
			
							
							
							
							
							foundThisBillCode = false;
							for (int j = 1; j <= totalrowsInBillCodesTable; j++) {
			
								taskName = fngFetchFromTable("BillCodesTable", j, taskTypeIndex);
								String billcodeInTable = fngFetchFromTable("BillCodesTable", j, billCodesIndex);
			
								
								
							if (billcodeInTable.equalsIgnoreCase(deskAuditTaskTab_expectedBillCode) && taskName.equalsIgnoreCase("Stage 1/Desk Audit/Document Review") ) {
								String quantity = fngFetchFromTable("BillCodesTable", j, quantityIndex);
								deskAuditTaskTab_expectedQuantity = Double.parseDouble(quantity);
							}
							
							
							if (billcodeInTable.equalsIgnoreCase(certAuditTaskTab_expectedBillCode) & taskName.equalsIgnoreCase("Certification (Stage 2) Audit")) {
								String quantity = fngFetchFromTable("BillCodesTable", j, quantityIndex);
								certAuditTaskTab_expectedQuantity = Double.parseDouble(quantity);
							}
							
							
							
								
								
								
								/****************************************************************/
			
								if ((taskName.equalsIgnoreCase(expectedTaskTypeName)) & (billcodeInTable.equalsIgnoreCase(expectedbillCode))) {
									foundThisBillCode = true;
									String actualTaskQuantity = fngFetchFromTable("BillCodesTable", j, quantityIndex);
			
									double actualTaskQuantityInDouble = Double.parseDouble(actualTaskQuantity);
									double expectedTaskQuantityInDouble = Double.parseDouble(expectedTaskQuantity);
									if (actualTaskQuantityInDouble != expectedTaskQuantityInDouble) {
										textMessage = "FAILED ==>Actual Task Quantity after RE_ADJUSTMENT is not matched for task type  '" + taskName + "'  and for bill code '"
												+ expectedbillCode + "'    as expected is --'" + expectedTaskQuantityInDouble + "' but actual is --'" + actualTaskQuantityInDouble
												+ "' at row no --" + j;
										fnpDisplayingMessageInFrame(textMessage, 10);
										fnpMymsg(textMessage);
										throw new Exception(textMessage);
									} else {
										textMessage = "PASSED ==> Actual Task Quantity  after RE_ADJUSTMENT is  matched successfully for task type'" + taskName + "'  and for bill code '"
												+ expectedbillCode + "'    as expected is --'" + expectedTaskQuantityInDouble + "' and actual is --'" + actualTaskQuantityInDouble
												+ "' at row no --" + j;
										fnpDisplayingMessageInFrame(textMessage, 5);
										fnpMymsg(textMessage);
			
									}
			
									String totalAmount = fngFetchFromTable("BillCodesTable", j, totalAmountIndex);
									totalAmount = totalAmount.replaceAll(",", "");
			
									String billCodeScheduledPrice = fngFetchFromTable("BillCodesTable", j, billCodeScheduledPriceIndex);
									billCodeScheduledPrice = billCodeScheduledPrice.replaceAll(",", "");
			
									double billCodeScheduledPriceDouble = Double.parseDouble(billCodeScheduledPrice.replace("$", "").trim());
									double quantityDouble = Double.parseDouble(actualTaskQuantity);
									double actualTotalAmountDouble = Double.parseDouble(totalAmount.replace("$", "").trim());
			
									double expectedTotalAmountDouble = billCodeScheduledPriceDouble * quantityDouble;
			
									if (actualTotalAmountDouble != expectedTotalAmountDouble) {
										textMessage = "FAILED ==>Total amount is not matched in Bill Code table in row no.--'" + j + "'   as actual is -- '" + actualTotalAmountDouble
												+ "'  but expected is --'" + expectedTotalAmountDouble + "'.";
										fnpDisplayingMessageInFrame(textMessage, 10);
										fnpMymsg(textMessage);
										throw new Exception(textMessage);
									} else {
										textMessage = "PASSED ==>Total amount is  matched in Bill Code table in row no.--'" + j + "'   as actual is -- '" + actualTotalAmountDouble
												+ "'  and expected is --'" + expectedTotalAmountDouble + "'.";
										fnpDisplayingMessageInFrame(textMessage, 5);
										fnpMymsg(textMessage);
									}
			
									// break;
			
								}
			
							}
			
							if (!foundThisBillCode) {
								textMessage = "This task '" + taskName + "' code is not present in  duration table after RE_ADJUSTMENT.";
								fnpDisplayingMessageInFrame(textMessage, 10);
								fnpMymsg(textMessage);
								throw new Exception(textMessage);
			
							}
			
						}
			
						/*******************************************************************************************************************/
						
			}		
			
			
		}
			

			 if((classNameText.equalsIgnoreCase(Sus_SingleTestCaseName)))
			 {
				
				fnpProcessAI_ISR("WOISR_FinopActionItemTable", "WOISR_FinopActionItemTable_HeaderRow", (String) hashXlData.get("Finops_Action_item_name"), "Inprocess", "WOISR_FinopActionItemSaveNCloseBtn","");
				fnpProcessAI_ISR("WOISR_FinopActionItemTable", "WOISR_FinopActionItemTable_HeaderRow", (String) hashXlData.get("Finops_Action_item_name"), "Completed", "WOISR_FinopActionItemSaveNCloseBtn","");
				
				
			 }else{
				 fnpClick_OR("MoveToInProcessBtn");
			 }
			 /** for this IPM-14262  */
			 List<WebElement> MoveToInProcessBtnElement=driver.findElements(By.xpath(".//*[@id='mainForm:move_inprs']"));
			 if(MoveToInProcessBtnElement.size()>1){
			 	 Thread.sleep(5000);
				 fnpClick_OR("MoveToInProcessBtn");
				 Thread.sleep(5000);
			 }
			// fnpClick_OR("MoveToInProcessBtn"); // without of this it is in
			// INPROCESS as soon as you completed finops AI

			fngWaitTillClickable("NewlyCrWOTopBannerInfo");
			fngGetORObjectX("NewlyCrWOTopBannerInfo").click();
			// fnpLoading_wait();

			fnpWaitForVisible("TopBannerWOStatus");
			String changedWOStatus = fngGetORObjectX("TopBannerWOStatus").getText();
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

			int taskNameColIndex = fnpFindColumnIndex("TaskTabTable_HeaderRow", WOISOTaskTab_TaskAISummaryTableColName_TaskAIName);

			String[] expectedTaskNameArray = ((String) hashXlData.get("Task_created_just_after_InProcess")).split(",");

			int totalRowGeneratedInTaskTable = fnpCountRowsInTable("TaskTabTable");


			boolean foundTaskflag = false;
			for (int i = 0; i < expectedTaskNameArray.length; i++) {

				for (int j = 0; j < totalRowGeneratedInTaskTable; j++) {
					String taskTypeValue = fngFetchFromTable("TaskTabTable", (j + 1), taskNameColIndex);
					if (taskTypeValue.equalsIgnoreCase(expectedTaskNameArray[i])) {
						fnpMymsg(" Task Name '" + expectedTaskNameArray[i] + "' is present at row no '" + (j + 1) + "' .");
						foundTaskflag = true;
						break;
					}
				}

				if (!foundTaskflag) {

					throw new Exception("  Task Name'" + expectedTaskNameArray[i] + "' is NOT present in any row in Task table .");

				}

			}
			fnpMymsg("===============================================================================================================");

			// }

		} catch (Throwable t) {
			//isTestPass = true;
			fnpMymsg(" **************************************************************");
			fngCommonFinalCatchBlock(t, "  Financial_Tab_And_InReviewToInProcess flow  is fail . ", "Financial_Tab_And_InReviewToInProcess flow");

		}
	}
	
	
	
	
	
	
	
	@Test(priority = 4, dependsOnMethods = { "Financial_Tab_And_InReviewToInProcess" })

	public void DeskAuditTask() throws Throwable {

		
		try {
	

			/******** Open the desk audit task in Task tab and compare some values *************************/
			fnpClick_OR("ISRTaskTab");
			String textMessage = "Now going to open the desk audit task in Task tab and compare some values ";
			fnpDisplayingMessageInFrame(textMessage, 5);

			String deskOpenIconXpath = OR_SUS.getProperty("TaskTabISR_DeskAudit_OpenIconPart1") + WOISOTaskName_DeskAudit+ OR_SUS.getProperty("TaskTabISR_DeskAudit_OpenIconPart2");
			fnpClick_NOR(deskOpenIconXpath);

			
			fnpMymsg("Expected Desk Audit billing code  is ---" + deskAuditTaskTab_expectedBillCode);
			fnpMymsg("Expected Desk Audit quantity  is ---" + deskAuditTaskTab_expectedQuantity);
			fnpMymsg("Expected Desk Audit Audit days  is ---" + deskAuditTaskTab_expectedAuditDays);
			fnpMymsg("");
			fnpMymsg("");

			int taskAINameIndex = fnpFindColumnIndex("TaskTabTable_HeaderRow", WOISOTaskTab_TaskAISummaryTableColName_TaskAIName);

			int deskRow = fnpFindRow("TaskTabTable", WOISOTaskName_DeskAudit, taskAINameIndex);

			String deskInnerTableHeaderXpath = OR_SUS.getProperty("ISRTaskInnerTableForBillingCodeTableHeader_part1") + (deskRow - 1)
					+ OR_SUS.getProperty("ISRTaskInnerTableForBillingCodeTableHeader_part2");

			int billingCodeInnerTableIndex = fnpFindColumnIndex_NOR(deskInnerTableHeaderXpath, "Billing Code");
			int quantityInnerTableIndex = fnpFindColumnIndex_NOR(deskInnerTableHeaderXpath, "Qty.");

			String deskInnerTableDataXpath = OR_SUS.getProperty("ISRTaskInnerTableForBillingCodeTableData_part1") + (deskRow - 1)
					+ OR_SUS.getProperty("ISRTaskInnerTableForBillingCodeTableData_part2");
			
			int billingDescIndex = fnpFindColumnIndex_NOR(deskInnerTableHeaderXpath, "Billing Desc");
			int innerdeskRow = fngFindRowContainsName_NOR(deskInnerTableDataXpath, "R2 Readiness Review", billingDescIndex);
			
			
			String actualDeskBillingCode = fnpFetchFromTable_NOR(deskInnerTableDataXpath, innerdeskRow, billingCodeInnerTableIndex);
			fnpMymsg("Actual Desk Audit billing code  is ---" + actualDeskBillingCode);
			String actualDeskQuantityInString = fnpFetchFromTable_NOR(deskInnerTableDataXpath, innerdeskRow, quantityInnerTableIndex);
			double actualDeskQuantity = Double.parseDouble(actualDeskQuantityInString);
			fnpMymsg("Actual Desk Audit quantity  is ---" + actualDeskQuantity);

			String deskExpansionPanelXpath = OR_SUS.getProperty("ISRTaskExpansionPanelGrid_part1") + (deskRow - 1) + OR_SUS.getProperty("ISRTaskExpansionPanelGrid_part2");

			String auditDaysXpath = deskExpansionPanelXpath + "//tr/td/label[text()='Audit Days']/../following-sibling::td/label";

			String auditdaysInString=fnpGetText_NOR(auditDaysXpath);
			
			if (auditdaysInString.trim().equalsIgnoreCase("") ){
				fnpMymsg("Audit days are not coming means blank space. Hene failed ");
				throw new Exception("Audit days are not coming means blank space. Hene failed ");				
			} 
			
			
			
			
			
			
			double actualAuditdays = Double.parseDouble(auditdaysInString);
			fnpMymsg("Actual Desk Audit audit days are ---" + actualAuditdays);

			
			if (!(classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.Cert_StandardsTestCaseName)) ){	
			
					Assert.assertTrue(actualDeskBillingCode.equalsIgnoreCase(deskAuditTaskTab_expectedBillCode), "Desk Audit billing code is not matched on task tab.");
					textMessage = "Desk Audit billing code is matched on task tab. Actual is --" + actualDeskBillingCode + "   and expected is ---" + deskAuditTaskTab_expectedBillCode;
					fnpDisplayingMessageInFrame(textMessage, 5);
					fnpMymsg(textMessage);
		
							
					Assert.assertTrue(actualDeskQuantity == deskAuditTaskTab_expectedQuantity, "Desk Audit quantity is not matched on task tab.");
					textMessage = "Desk Audit quantity is  matched on task tab. Actual is --" + actualDeskQuantity + "   and expected is ---" + deskAuditTaskTab_expectedQuantity;
					fnpDisplayingMessageInFrame(textMessage, 5);
					fnpMymsg(textMessage);
					
					
					
					
		
					Assert.assertTrue(actualAuditdays == deskAuditTaskTab_expectedAuditDays, "Desk Audit Audit days are not matched on task tab.");
					textMessage = "Desk Audit Audit days are   matched on task tab. Actual is --" + actualAuditdays + "   and expected is ---" + deskAuditTaskTab_expectedAuditDays;
					fnpDisplayingMessageInFrame(textMessage, 5);
					fnpMymsg(textMessage);

			
			}else{
				Assert.assertTrue((!actualDeskBillingCode.equalsIgnoreCase("")), "Desk Audit billing code is blank on task tab.");
				textMessage = "Desk Audit billing code is not blank on task tab. Actual is --" + actualDeskBillingCode ;
				fnpDisplayingMessageInFrame(textMessage, 5);
				fnpMymsg(textMessage);
				
				
				
				Assert.assertTrue(actualAuditdays == deskAuditTaskTab_expectedAuditDays, "Desk Audit Audit days are not matched on task tab.");
				textMessage = "Desk Audit Audit days are   matched on task tab. Actual is --" + actualAuditdays + "   and expected is ---" + deskAuditTaskTab_expectedAuditDays;
				fnpDisplayingMessageInFrame(textMessage, 5);
				fnpMymsg(textMessage);
			}
			
			/*************************************************************************************/

			String boundaryStartDateXpath = deskExpansionPanelXpath + "//tr/td/label[contains(text(),'Boundary Start Date')]/../following-sibling::td/label";
			String boundaryEndDateXpath = deskExpansionPanelXpath + "//tr/td/label[contains(text(),'Boundary End Date')]/../following-sibling::td/label";

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
			
			
	/*		
			

			fnpMymsg("Now we are going to  Scheme Management.");
			textMessage = "Now we are going to  Scheme Management.";
			fnpDisplayingMessageInFrame(textMessage, 5);
			fnpCommonClickTopMainMenu("Menu", "FirstRefrenceLinkJustBelowToMenu", "SearchSchemeLink", "SchemeCodeTxtBx");

			fngType("OR_SUS", "SchemeCodeTxtBx", (String) hashXlData.get("Standard"));
			fnpClick_OR("MainSearchButton");

			String s = fnpFetchFromStSearchTable(1, 1);
			int j = 0;

			while (s.contains("No records found") & j < 15) {
				j++;
				Thread.sleep(1000);
				s = fnpFetchFromStSearchTable(1, 1);
			}
			// fnpMymsg("just before clicking first record");
			fnpWaitTillVisiblityOfElementNClickable(".//a[text()='" + s + "']");
			driver.findElement(By.linkText(s)).click();
			// fnpMymsg("just after clicking first record");
			fnpLoading_wait();

			fnpClick_OR("ConfigureSchemeBtn");

			fnpClick_OR("SchemeMgtWorkOrderTasksTabLink");

			List<WebElement> taskTypeLastLabelLinks = driver.findElements(By.xpath(OR_SUS.getProperty("WorkOrderTaskTypeLinkInSchemeMgt_lastLabel")));
			int taskTypeLastLabelLinksCount = taskTypeLastLabelLinks.size();
			int indexForInnerTableForSingleWO = 0;
			String labelValue;

			for (int i = 0; i < taskTypeLastLabelLinksCount; i++) {

				labelValue = taskTypeLastLabelLinks.get(i).getText();
				if (labelValue.trim().equalsIgnoreCase("Single")) {
					indexForInnerTableForSingleWO = i;
					break;
				}
			}

			fnpClick_OR("WorkOrderSingleTaskTypeLinkInSchemeMgt");

			String taskTypeInnerTableHeader_part1 = ".//*[@id='mainForm:tabView:schmwotabview:schmwottaccpnl:";
			String taskTypeInnerTableHeader_part2 = ":stdAppJobTypeDT_head']";

			String taskTypeInnerTableData_part1 = ".//*[@id='mainForm:tabView:schmwotabview:schmwottaccpnl:";
			String taskTypeInnerTableData_part2 = ":stdAppJobTypeDT_data']";

			String taskTypeInnerTableHeaderFullXpath = taskTypeInnerTableHeader_part1 + indexForInnerTableForSingleWO + taskTypeInnerTableHeader_part2;
			String taskTypeInnerTableDataFullXpath = taskTypeInnerTableData_part1 + indexForInnerTableForSingleWO + taskTypeInnerTableData_part2;

			int taskNameInInnerTaskTableColIndex = fnpFindColumnIndex_NOR(taskTypeInnerTableHeaderFullXpath, "Task Name");
			int optionInInnerTaskTableColIndex = fnpFindColumnIndex_NOR(taskTypeInnerTableHeaderFullXpath, "Option");

			int rowNoForDeskAudit = fnpFindRow_NOR(taskTypeInnerTableDataFullXpath, WOISOTaskName_DeskAudit, taskNameInInnerTaskTableColIndex);

			String xpathForXpendingDeskAuditIcon = taskTypeInnerTableDataFullXpath + "/tr[" + rowNoForDeskAudit
					+ "]/td/div[@class='ui-row-toggler ui-icon ui-icon-circle-triangle-e']";

			fnpClick_NOR_withoutWait(xpathForXpendingDeskAuditIcon);
			fnpLoading_wait();
			//Thread.sleep(5000);

			String xpathForBoundaryStartDateBNF = taskTypeInnerTableDataFullXpath + "/tr[" + (rowNoForDeskAudit + 1)
					+ "]//td/label[text()='Boundary Start Date BNF']/../following-sibling::td/label";
			String xpathForBoundaryEndDateBNF = taskTypeInnerTableDataFullXpath + "/tr[" + (rowNoForDeskAudit + 1)
					+ "]//td/label[text()='Boundary End Date BNF']/../following-sibling::td/label";

			String boundaryStartDateBNF = fnpGetText_NOR(xpathForBoundaryStartDateBNF);
			textMessage = "BNF formula for Start Date is ---" + boundaryStartDateBNF;
			fnpDisplayingMessageInFrame(textMessage, 5);
			Thread.sleep(1000);

			String boundaryEndDateBNF = fnpGetText_NOR(xpathForBoundaryEndDateBNF);
			textMessage = "BNF formula for End Date is ---" + boundaryEndDateBNF;
			fnpDisplayingMessageInFrame(textMessage, 5);
			Thread.sleep(1000);

			String[] startDateNoArray = boundaryStartDateBNF.split("WO_CERT_TARG_DT_M");
			String startDateNoInString = startDateNoArray[1];
			startDateNoInString = fnpremoveFormatting(startDateNoInString);
			int startDateNo = Integer.parseInt(startDateNoInString);
			textMessage = " So we have to minus no. of days for Start date ---" + startDateNo;
			fnpDisplayingMessageInFrame(textMessage, 5);
			Thread.sleep(1000);

			String[] endDateNoArray = boundaryEndDateBNF.split("WO_CERT_TARG_DT_M");
			String endDateNoInString = endDateNoArray[1];
			endDateNoInString = fnpremoveFormatting(endDateNoInString);
			int endDateNo = Integer.parseInt(endDateNoInString);

			textMessage = " So we have to minus no. of days for End date ---" + endDateNo;
			fnpDisplayingMessageInFrame(textMessage, 5);
			Thread.sleep(1000);

			
			
			
			fnpSearchWorkOrderOnly(workOrderNo);
			
			*/
			
			int startDateNo=0;
			int endDateNo=0;
			
			
			//SimpleDateFormat sdfmt1;
			
			
/*			 if((classNameText.equalsIgnoreCase("SQFAudit_SingleWO_InProcess"))){
				 startDateNo=120;
				 endDateNo=90;
		 }
		*/
			
			 if((classNameText.equalsIgnoreCase(Sus_SingleTestCaseName))){
					 startDateNo=120;
					 endDateNo=90; 
			 
			 

					
		
					fnpClick_OR("ISRInfoTab_EditWO");
		
					if (fnpCheckElementDisplayedImmediately("EditWOBtn")) {				
						fnpClick_OR("EditWOBtn");
					}
					fngSelectTechnicalReviewer();
					String actualCertTargetGivenAtInfoTab = fngWaitTillTextBoxDontHaveValue("CertTargetDateInputBxInfoTab", "value");
		
					 sdfmt1 = new SimpleDateFormat("dd-MMM-yyyy");
		
					Date date_actualCertTargetGivenAtInfoTab = sdfmt1.parse(actualCertTargetGivenAtInfoTab);
		
					Calendar c = Calendar.getInstance();
					c.setTime(date_actualCertTargetGivenAtInfoTab);
					c.add(Calendar.DAY_OF_MONTH, -startDateNo);
					Date certTargetDateMinusStartDateFromBNF = c.getTime();
		
		
				//	Date boudaryStartDateInDateformatAtTaskTab = sdfmt1.parse(boudaryStartDateInStringformatAtTaskTab);
		
					fnpMymsg("CertTarget start date given at Info tab is ---"+actualCertTargetGivenAtInfoTab);
					//fnpMymsg("Using BNF rule, for start date it should be minus by --"+startDateNo);
					fnpMymsg("Boundary start date given at Task tab for Desk Audit is ---"+boudaryStartDateInStringformatAtTaskTab);
					
					
					if (certTargetDateMinusStartDateFromBNF.compareTo(boudaryStartDateInDateformatAtTaskTab) > 0) {
						textMessage = "certTargetDateMinusStartDateFromBNF is after boudaryStartDateInDateformatAtTaskTab, hence failed. " +
								"certTargetDateMinusStartDateFromBNF is '"+actualCertTargetGivenAtInfoTab+" minus (-) "+startDateNo +"  should be equal to "+boudaryStartDateInStringformatAtTaskTab ;
						fnpMymsg(textMessage);
						fnpDisplayingMessageInFrame(textMessage, 5);
						throw new Exception(textMessage);
					} else if (certTargetDateMinusStartDateFromBNF.compareTo(boudaryStartDateInDateformatAtTaskTab) < 0) {
						textMessage = "certTargetDateMinusStartDateFromBNF is before boudaryStartDateInDateformatAtTaskTab, hence failed. " +
								"certTargetDateMinusStartDateFromBNF is '"+actualCertTargetGivenAtInfoTab+" minus (-) "+startDateNo +"  should be equal to "+boudaryStartDateInStringformatAtTaskTab ;
						fnpMymsg(textMessage);
						fnpDisplayingMessageInFrame(textMessage, 5);
						throw new Exception(textMessage);
					} else {
						textMessage = "Cert target Date - (Minus)  " + startDateNo + " days is equal to Boundary start date in Tasks tab of WO";
						fnpMymsg(textMessage);
						fnpDisplayingMessageInFrame(textMessage, 5);
					}
		
					c = Calendar.getInstance();
					c.setTime(date_actualCertTargetGivenAtInfoTab);
					c.add(Calendar.DAY_OF_MONTH, -endDateNo);
					Date certTargetDateMinusEndDateFromBNF = c.getTime();
		
					// boundaryEndDateInStringformatAtTaskTab =
					// sdfmt1.format(boundaryEndDateInStringformatAtTaskTab);
					//Date boundaryEndDateInDateformatAtTaskTab = sdfmt1.parse(boundaryEndDateInStringformatAtTaskTab);
		
					fnpMymsg("CertTargetDate given at Info tab is ---"+actualCertTargetGivenAtInfoTab);
				//	fnpMymsg("Using BNF rule, for end date it should be minus by --"+endDateNo);
					fnpMymsg("Boundary End date given at Task tab  Desk Audit is ---"+boundaryEndDateInStringformatAtTaskTab);
					
					
					if (certTargetDateMinusEndDateFromBNF.compareTo(boundaryEndDateInDateformatAtTaskTab) > 0) {
						textMessage = "certTargetDateMinusEndDateFromBNF is after boundaryEndDateInDateformatAtTaskTab, hence failed. " +
								"certTargetDateMinusEndDateFromBNF is '"+actualCertTargetGivenAtInfoTab+" minus (-) "+endDateNo +"  should be equal to "+boundaryEndDateInStringformatAtTaskTab ;
						fnpMymsg(textMessage);
						fnpDisplayingMessageInFrame(textMessage, 5);
						throw new Exception(textMessage);
					} else if (certTargetDateMinusEndDateFromBNF.compareTo(boundaryEndDateInDateformatAtTaskTab) < 0) {
						textMessage = "certTargetDateMinusEndDateFromBNF is before boundaryEndDateInDateformatAtTaskTab, hence failed. " +
								"certTargetDateMinusEndDateFromBNF is '"+actualCertTargetGivenAtInfoTab+" minus (-) "+endDateNo +"  should be equal to "+boundaryEndDateInStringformatAtTaskTab ;
						fnpMymsg(textMessage);
						fnpDisplayingMessageInFrame(textMessage, 5);
						throw new Exception(textMessage);
					} else {
						textMessage = "Cert target Date - (Minus) " + endDateNo + " days is equal to Boundary End date  in Tasks tab of WO";
						fnpMymsg(textMessage);
						fnpDisplayingMessageInFrame(textMessage, 5);
					}
		
					
					
					fnpClick_OR("ISRTaskTab");
			 }
			
			
			
			
			
			 if((classNameText.equalsIgnoreCase(Sus_SingleTestCaseName))){
			
						textMessage = "Now going to click Audit no. in Desk Audit at Task Tab ";
						fnpDisplayingMessageInFrame(textMessage, 5);
						if ( (fnpCheckElementDisplayedImmediately_NOR(deskOpenIconXpath)  )  ) {				
							fnpClick_NOR(deskOpenIconXpath);
							fnpMymsg("Clicked desk open icon");
						}
						
						
			
						String auditNoInDeskAuditXpath = OR_SUS.getProperty("AuditNoAtTaskTab_part1") + (deskRow - 1) + OR_SUS.getProperty("AuditNoAtTaskTab_part2");
			
						String auditNo = fnpGetText_NOR(auditNoInDeskAuditXpath);
						fnpDisplayingMessageInFrame("Going to click this Audit no.  --" + auditNo, 5);
			
						fnpGetORObjectX___NOR(auditNoInDeskAuditXpath).click();
						fnpClick_OR("EditBtnOnViewAuditPage");
						fnpClick_OR("Audit_InfoTabLink");
			
						String actualDurationText = fnpGetText_OR("DurationAtInfoTabOfViewAudit").trim();
			
						String[] actualDurationInStringArray = actualDurationText.split(" ");
			
						Double actualDuration = Double.parseDouble(actualDurationInStringArray[0]);
			
						if (actualDuration != actualAuditdays) {
							// fnpMymsg("Duration --'"+actualDuration+"' and Audit days --'"+actualAuditdays+"' are NOT equal, hence failed.");
							textMessage = "Duration --'" + actualDuration + "' and Audit days --'" + actualAuditdays + "' are NOT equal, hence failed.";
							fnpMymsg(textMessage);
							fnpDisplayingMessageInFrame(textMessage, 5);
							throw new Exception(textMessage);
						} else {
							textMessage = "Duration --'" + actualDuration + "' and Audit days --'" + actualAuditdays + "' are  equal.";
							fnpMymsg(textMessage);
							fnpDisplayingMessageInFrame(textMessage, 5);
						}
			
						String doNotScheduleBeforeText = fnpGetText_OR("DoNotScheduleBeforeAtInfoTabOfViewAudit").trim();
			
						 sdfmt1 = new SimpleDateFormat("dd-MMM-yyyy");
						Date date_doNotScheduleBeforeGivenAtViewAuditInfoTab = sdfmt1.parse(doNotScheduleBeforeText);
			
						if (date_doNotScheduleBeforeGivenAtViewAuditInfoTab.compareTo(boudaryStartDateInDateformatAtTaskTab) > 0) {
							textMessage = " Do not Schedule Before---'" + doNotScheduleBeforeText + "' is after to Boundary start date --'" + boudaryStartDateInStringformatAtTaskTab
									+ "' , hence failed.";
							fnpMymsg(textMessage);
							fnpDisplayingMessageInFrame(textMessage, 5);
							throw new Exception(textMessage);
						} else if (date_doNotScheduleBeforeGivenAtViewAuditInfoTab.compareTo(boudaryStartDateInDateformatAtTaskTab) < 0) {
							textMessage = " Do not Schedule Before---'" + doNotScheduleBeforeText + "' is before to Boundary start date --'" + boudaryStartDateInStringformatAtTaskTab
									+ "' , hence failed.";
							fnpMymsg(textMessage);
							fnpDisplayingMessageInFrame(textMessage, 5);
							throw new Exception(textMessage);
						} else {
							textMessage = " Do not Schedule Before---'" + doNotScheduleBeforeText + "' is equal to Boundary start date --'" + boudaryStartDateInStringformatAtTaskTab
									+ "' in Tasks tab of WO";
							fnpMymsg(textMessage);
							fnpDisplayingMessageInFrame(textMessage, 5);
						}
			
						String deadLineDateText = fnpGetText_OR("DeadLineDateAtInfoTabOfViewAudit").trim();
			
						sdfmt1 = new SimpleDateFormat("dd-MMM-yyyy");
						Date deadLineDateAtViewAuditInfoTab = sdfmt1.parse(deadLineDateText);
			
						if (deadLineDateAtViewAuditInfoTab.compareTo(boundaryEndDateInDateformatAtTaskTab) > 0) {
							textMessage = " DeadLine date---'" + deadLineDateText + "' is after to Boundary End date --'" + boundaryEndDateInStringformatAtTaskTab + "' , hence failed.";
							fnpMymsg(textMessage);
							fnpDisplayingMessageInFrame(textMessage, 5);
							throw new Exception(textMessage);
						} else if (deadLineDateAtViewAuditInfoTab.compareTo(boundaryEndDateInDateformatAtTaskTab) < 0) {
							textMessage = " DeadLine date---'" + deadLineDateText + "' is before to Boundary End date --'" + boundaryEndDateInStringformatAtTaskTab + "' , hence failed.";
							fnpMymsg(textMessage);
							fnpDisplayingMessageInFrame(textMessage, 5);
							throw new Exception(textMessage);
						} else {
							textMessage = " DeadLine date---'" + deadLineDateText + "' is equal to Boundary End date --'" + boundaryEndDateInStringformatAtTaskTab + "' in Tasks tab of WO";
							fnpMymsg(textMessage);
							fnpDisplayingMessageInFrame(textMessage, 5);
						}
			
						fnpClick_OR("ISRBackToViewBtn");
						fnpClick_OR("ISRBackBtn");
						
						fnpClick_OR("ISRTaskTab");
						
						
						if (fnpCheckElementDisplayedImmediately("EditWOBtn")) {				
							fnpClick_OR("EditWOBtn");
						}

			 }
			

			

			
			if ( (fnpCheckElementDisplayedImmediately_NOR(deskOpenIconXpath)  )  ) {				
				fnpClick_NOR(deskOpenIconXpath);
				fnpMymsg("Clicked desk open icon");
			}
			

			String visitNoInDeskAuditXpath = OR_SUS.getProperty("VisitNoAtTaskTab_part1") + (deskRow - 1) + OR_SUS.getProperty("VisitNoAtTaskTab_part2");

			String visitNo = fnpGetText_NOR(visitNoInDeskAuditXpath).trim();
			fnpMymsg("Going to check visit no, as if visit no is not present/blank then it will go for clicking bundle audit button. So here visit no. is--'"+visitNo+"'.");



			
			
			
			
			

			String auditNoInDeskAuditXpath = OR_SUS.getProperty("AuditNoAtTaskTab_part1") + (deskRow - 1) + OR_SUS.getProperty("AuditNoAtTaskTab_part2");
			String auditNo = fnpGetText_NOR(auditNoInDeskAuditXpath);
			
			
			if (visitNo.equalsIgnoreCase("")) {
				fnpGetORObjectX___NOR(auditNoInDeskAuditXpath).click();
				// fnpClick_OR("EditBtnOnViewAuditPage");
				//Thread.sleep(1000);
				
				//fnpWaitForVisible("viewFinalReportBtnAtViewAuditScreen");
				//fnpWaitForVisible("viewFinalReportLink_AtViewAuditScreen");
				
				fnpWaitForVisible("ViewAudit_InfoTabLink");
				
				//String visitNoAtViewAuditPage = fnpGetText_NOR("visitLinkInInfoTab").trim();
				String visitNoAtViewAuditPage="";
				if (fnpCheckElementPresenceImmediately("visitLinkInInfoTab")) {
					 visitNoAtViewAuditPage = fnpGetText_OR("visitLinkInInfoTab").trim();
				} else {
					//nothing to do
				}
				
				fnpMymsg("Going to check visit no AGAIN, as if visit no is not present/blank then it will go for clicking bundle audit button. So here visit no. is--'"+visitNoAtViewAuditPage+"'.");
				if (visitNoAtViewAuditPage.equalsIgnoreCase("")) {	
					Thread.sleep(2000); // sometime even after clicking bundle audit button, visit not generated, so giving a wait before clicking this bundle button
					fnpClick_OR("BundleAuditBtn");
				}
				fnpClick_OR("ISRBackBtn");
				fnpClick_OR("ISRTaskTab");
				if (fnpCheckElementDisplayedImmediately("EditWOBtn")) {				
					fnpClick_OR("EditWOBtn");
				}
				
				// fnpClick_OR("TaskTabISR_DeskAudit_OpenIcon");
				//fnpClick_NOR(deskOpenIconXpath);
				if ( (fnpCheckElementDisplayedImmediately_NOR(deskOpenIconXpath)  )  ) {				
					fnpClick_NOR(deskOpenIconXpath);
					fnpMymsg("Clicked desk open icon");
				}

				visitNo = fnpGetText_NOR(visitNoInDeskAuditXpath).trim();
			}


			/*****************IPM-6227***********************************************/
/*			String editBtnForEditDeskAuditTaskXpath = OR_SUS.getProperty("EditBtnForEditTaskAtTaskTab_part1") + (deskRow - 1) + OR_SUS.getProperty("EditBtnForEditTaskAtTaskTab_part2");	
			fnpClick_NOR(editBtnForEditDeskAuditTaskXpath);
			*/
		
			
			
/*			String expenseAllocationbtnXpath = OR_SUS.getProperty("ExpenseAllocationBtn_TaskAtTaskTab_part1") + (deskRow - 1) + OR_SUS.getProperty("ExpenseAllocationBtn_TaskAtTaskTab_part2");	
			fnpClick_NOR(expenseAllocationbtnXpath);
			*/
			
			String editFeesbtnXpath = OR_SUS.getProperty("EditFeesBtnForEditTaskAtTaskTab_part1") + (deskRow - 1) + OR_SUS.getProperty("EditFeesBtnForEditTaskAtTaskTab_part2");	
			fnpClick_NOR(editFeesbtnXpath);
			
			
			
			
			/***************************************************************************/
			
			Date todayDate=new Date();
			sdfmt1 = new SimpleDateFormat("dd-MMM-yyyy");
			String todaydateInStringformat = sdfmt1.format(todayDate);
		//	fngType("OR_SUS", "BoundaryStartDateEditTxtBxAtEditTaskDialog", todaydateInStringformat);
			
			
			fnpClick_OR_WithoutWait("BoundaryStartDateBtnAtEditTaskDialog");
			Thread.sleep(2000);			
			fngCalendarDatePicker_BySelectValues_From_NextAndBack(todaydateInStringformat);  // Boundry start date && End date wil be same  discussion with ruchi 21-04-2022
			
			Thread.sleep(2000);	
			
			
			
			
			todayDate=new Date();
			Calendar c = Calendar.getInstance();
			c.setTime(todayDate);
			c.add(Calendar.DAY_OF_MONTH, 7);
			Date afterOneWeekdate = c.getTime();

			sdfmt1 = new SimpleDateFormat("dd-MMM-yyyy");
			String afterOneWeekdateInStringformat = sdfmt1.format(afterOneWeekdate);
	//		fngType("OR_SUS", "BoundaryEndDateEditTxtBxAtEditTaskDialog", afterOneWeekdateInStringformat);	
			
			
			fnpClick_OR_WithoutWait("BoundaryEndDateBtnAtEditTaskDialog");
			Thread.sleep(2000);			
			fngCalendarDatePicker_BySelectValues_From_NextAndBack(todaydateInStringformat);
			
			Thread.sleep(2000);	
		//	Thread.sleep(5000);	
			//Thread.sleep(6000);	// to avoid we are sorry error some time
			//Thread.sleep(10000);
			
			//25July2019 New Field(Justification Reason) added into Edit Task Fee Popup.
			fngPFList("Justification_Reason_EditTaskFeePopup_List", "Justification_Reason_EditTaskFee_Popup_ListOptions",(String) hashXlData.get("JustificationReason_EditTaskFee"));
			//20Apr2021 New Field(Duration Justification Reason) added into Edit Task Fee Popup.
			Thread.sleep(2000);	
			fngPFList("Duration_Justification_Reason_EditTaskFeePopup_List", "Duration_Justification_Reason_EditTaskFee_Popup_ListOptions",(String) hashXlData.get("Duration_JustificReason_EditTaskFee"));
			Thread.sleep(2000);
			WebElement wb = driver.findElement(By.xpath(".//button[@id='mainForm:tabView:edittasksave']"));
	        JavascriptExecutor executor = (JavascriptExecutor)driver;
	        executor.executeScript("arguments[0].click();", wb);
			//fnpClick_OR("SaveBtnAtEditTask");
			fnpLoading_waitInsideDialogBox();
			
			fnpClick_OR("ISRSFTabSaveBtn");
			
			
			
			
			
			
			
			if ( (fnpCheckElementDisplayedImmediately_NOR(deskOpenIconXpath) & (!fnpCheckElementDisplayedImmediately_NOR(visitNoInDeskAuditXpath)) )  ) {				
				fnpClick_NOR(deskOpenIconXpath);
			}
			
			
			
			
			
			
			
			
			visitNo = fnpGetText_NOR(visitNoInDeskAuditXpath).trim();
			if (visitNo.equalsIgnoreCase("")) {		
			throw new Exception("Visit No. is still not coming in desk audit task even after clicking bundle audit button at view audit screen.");			
			}
			
			
			fnpDisplayingMessageInFrame("Going to click this Visit no.  --" + visitNo, 5);

		
			
			
			fnpGetORObjectX___NOR(visitNoInDeskAuditXpath).click();
			fnpClick_OR("EditBtnOnViewVisitPage");
		//	fnpClick_OR("AuditorLkpBtnAtVisitPage");

			
			
			
/*			
			fnpCommonCodeForAuditorLookup();

			
			todaydateInStringformat = sdfmt1.format(new Date());
			fnpClick_OR("ScheduleStartDateCalBtn_xpath");
			Thread.sleep(2000);			
			fnpCalendarDatePicker_BySelectValues_From_DropDown2(todaydateInStringformat,
					OR_SUS.getProperty("calendarDatePickerSelectYear_xpath"), OR_SUS.getProperty("calendarDatePickerSelectMonth_xpath"));
			
			
			String scheduleStartDateforDeskAudit=todaydateInStringformat;
			
			
			Thread.sleep(2000);	
		//	Thread.sleep(4000);	
			fnpClick_OR("ScheduleEndDateCalBtn_xpath");
			Thread.sleep(2000);			
			fnpCalendarDatePicker_BySelectValues_From_DropDown2(afterOneWeekdateInStringformat,
					OR_SUS.getProperty("calendarDatePickerSelectYear_xpath"), OR_SUS.getProperty("calendarDatePickerSelectMonth_xpath"));
			
			
			
			String scheduleEndDateforDeskAudit=afterOneWeekdateInStringformat;
			Thread.sleep(2000);	
		//	Thread.sleep(4000);	
		//	Thread.sleep(6000);	// to avoid we are sorry error some time
		//	Thread.sleep(10000);
			
			
			
			
			
		//	fnpClick_OR("SaveBtnAtViewVisitPage");	
			
			
			*/
			
			
			
			
			todaydateInStringformat = sdfmt1.format(new Date());
			
			String scheduleEndDateforDeskAudit = fnaSelectingAuditorThroughSAM();
			String scheduleStartDateforDeskAudit=todaydateInStringformat;
			//String scheduleEndDateforDeskAudit=todaydateInStringformat;
			
/*			
			
			fnpClick_OR("EditBtnOnViewVisitPage");
			fngGetORObjectX("SaveBtnAtViewVisitPage").click();
			Thread.sleep(5000);
			String ignoreWarning="Auditor TestScriptUser TestScriptUser is already booked as a lead auditor";			
			fnpIgnoreWarningInIPulse(ignoreWarning);
			
			//commenting on 06-04-2017 for timebeing as  success message is not coming and it should come...uncomment these lines later
			fnpWaitForVisibleHavingMultipleElements("TopMessage3");
			fnpMymsg("Top Message after saving dates are  ----" + fnpGetText_OR("TopMessage3"));
			String SuccessfulMsg = fnpGetText_OR("TopMessage3");
			Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"), "Top message does not contain 'success' word, so might be Scheduled Stard and End dates  have NOT  been saved successfully");
			fnpMymsg("Scheduled Stard and End dates  have  been saved successfully");
			
			*/
			

			
			
/*			//fnpVerifyFRSReportOnViewVistPage(deskOpenIconXpath,visitNoInDeskAuditXpath) ;
			fnpVerifyFRSReportOnViewVistPage_inChrome() ;
			*/
			
			
			

			int copyFromAudit_ForDeskAudit;
			int copyToAudit_ForDeskAudit;
			String usingGoldenProcedureOrOasis = (String) hashXlData.get("Audit_usingGoldenProcedureOrOasis");
			if (usingGoldenProcedureOrOasis.equalsIgnoreCase("golden")) {
				 copyFromAudit_ForDeskAudit=Integer.parseInt((String) hashXlData.get("copy_From_Audit_ForDeskAuditTask").trim());
				 copyToAudit_ForDeskAudit=Integer.parseInt(auditNo.trim());
				
				fnpMymsg("Just before running golden procedure - COPY_AUDIT_TRANSACTION_SUBMIT");
				fnpCallGoldenProcedure_COPY_AUDIT_TRANSACTION_SUBMIT(copyFromAudit_ForDeskAudit,copyToAudit_ForDeskAudit,(String) hashXlData.get("Auditor").trim());
				fnpMymsg("Golden procedure 'COPY_AUDIT_TRANSACTION_SUBMIT' ran successfully.");
			} else {
				
				if (usingGoldenProcedureOrOasis.equalsIgnoreCase("oasis")) {
					
					fnpOasisPartInISO("desk", visitNo);
					fngLaunchBrowserAndLogin();



				} else {
					throw new Exception("Value is not assigned to usingGoldenOrOasis");
				}

			}

			
			
	


		//	fnpLaunchBrowserAndLogin();
			fnpSearchWorkOrderOnly(workOrderNo);
			

			if (fnpCheckElementDisplayedImmediately("EditWOBtn")) {
				fnpClick_OR("EditWOBtn");
				fnpMymsg("@  ---Here EditWOBtn is visible , so going to click it");

			}else{
				fnpMymsg("@  ---Here EditWOBtn is NOT visible , so ignore  it");
			}
			
						
			
			
			fnpClick_OR("ISRTaskTab");
			
			
			
			
			if ( (fnpCheckElementDisplayedImmediately_NOR(deskOpenIconXpath) & (!fnpCheckElementDisplayedImmediately_NOR(visitNoInDeskAuditXpath)) )  ) {				
				fnpClick_NOR(deskOpenIconXpath);
			}
			

			String deskAuditorXpath = deskExpansionPanelXpath + "//tr/td/label[text()='Auditor']/../following-sibling::td/label";
			String deskAuditor = fnpGetText_NOR(deskAuditorXpath);
			
			if (deskAuditor.equalsIgnoreCase((String)hashXlData.get("AccountManager"))) {
				fnpMymsg("Auditor is present in desk Audit and its value is ---"+deskAuditor);
			} else {
				fnpMymsg("Auditor is NOT present in desk Audit and its value is ---"+deskAuditor);
				throw new Exception("Auditor is NOT present in desk Audit");
			}
			
			
			
			
			
			

			String scheduleStartDateXpath = deskExpansionPanelXpath + "//tr/td/label[text()='Schedule Start Date']/../following-sibling::td/label";
			String actualScheduleStartDate = fnpGetText_NOR(scheduleStartDateXpath);
			
			if (actualScheduleStartDate.equalsIgnoreCase(scheduleStartDateforDeskAudit)) {
				fnpMymsg("Schedule Audit start date is present and its value is ---'"+actualScheduleStartDate +"' and expected is --'"+scheduleStartDateforDeskAudit +"' .");
			} else {
				fnpMymsg("Schedule Audit start date is NOT  matched as its value is ---"+actualScheduleStartDate+"' and expected is --'"+scheduleStartDateforDeskAudit +"' .");
				//throw new Exception("Schedule Audit start date is NOT  present");
				throw new Exception("Schedule Audit start date is NOT  matched as its value is ---"+actualScheduleStartDate+"' and expected is --'"+scheduleStartDateforDeskAudit +"' .");
			}
			
			
			
			
			String scheduleEndDateXpath = deskExpansionPanelXpath + "//tr/td/label[text()='Schedule End Date']/../following-sibling::td/label";
			String actualScheduleEndDate = fnpGetText_NOR(scheduleEndDateXpath);
			
			if (actualScheduleEndDate.equalsIgnoreCase(scheduleEndDateforDeskAudit)) {
				fnpMymsg("Schedule Audit End date is present and its value is ---'"+actualScheduleEndDate +"' and expected is --'"+scheduleEndDateforDeskAudit +"' .");
			} else {
				fnpMymsg("Schedule Audit End date is NOT  matched as its value is ---"+actualScheduleEndDate+"' and expected is --'"+scheduleEndDateforDeskAudit +"' .");
				//throw new Exception("Schedule Audit End date is NOT  present");
				throw new Exception("Schedule Audit End date is NOT  matched as its value is ---"+actualScheduleEndDate+"' and expected is --'"+scheduleEndDateforDeskAudit +"' .");
			}
			
			
			
			String DeskAuditStatusXpath = deskExpansionPanelXpath + "//tr/td/label[text()='Audit Status']/../following-sibling::td/label";
			String DeskAuditStatus = fnpGetText_NOR(DeskAuditStatusXpath);
			
			if (DeskAuditStatus.equalsIgnoreCase("SUBMITTED")) {
				fnpMymsg("Desk Audit status is  become 'SUBMITTED'  as its value is ---'"+DeskAuditStatus +"' and expected is --'"+"SUBMITTED" +"' .");
			} else {
				fnpMymsg("Desk Audit status is NOT become 'SUBMITTED'  as its value is ---"+DeskAuditStatus+"' and expected is --'"+"SUBMITTED" +"' .");
				//throw new Exception("Desk Audit status is NOT become 'SUBMITTED'");
				throw new Exception("Desk Audit status is NOT become 'SUBMITTED'  as its value is ---"+DeskAuditStatus+"' and expected is --'"+"SUBMITTED" +"' .");
			}
			
		
	
	
			
			/******************New steps for desk audit in SQF on 10-07-2017 ******************/
			if((classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.Cert_StandardsTestCaseName))){		 
					taskAINameIndex = fnpFindColumnIndex("TaskTabTable_HeaderRow", WOISOTaskTab_TaskAISummaryTableColName_TaskAIName);
					deskRow = fnpFindRow("TaskTabTable", WOISOTaskName_DeskAudit, taskAINameIndex);
					 
					String deskInnerTableDataXpathForAI = OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableData_part1") + (deskRow - 1)
							+ OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableData_part2");
					
					String deskInnerTableHeaderXpathForAI = OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part1") + (deskRow - 1)
							+ OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part2");
					
					
					
		
					int aiTypeInnerTableIndex = fnpFindColumnIndex_NOR(deskInnerTableHeaderXpathForAI, "AI Type");
					int aiNumberInnerTableIndex = fnpFindColumnIndex_NOR(deskInnerTableHeaderXpathForAI, "AI Number");
		
					
					
					int performAuditRow = fngFindRowContainsName_NOR(deskInnerTableDataXpathForAI, WOISOTaskTab_PerformAuditAIType, aiTypeInnerTableIndex);
					
					if (performAuditRow>0) {
						fnpMymsg("Perform Audit action item has been generated for Desk Audit task.");
					} else {
						fnpMymsg("Perform Audit action item has NOT been generated for Desk Audit task.");
						throw new Exception("Perform Audit action item has NOT been generated for Desk Audit task.");
					}
					
					String performAuditAINo = fnpFetchFromTable_NOR(deskInnerTableDataXpathForAI, performAuditRow, aiNumberInnerTableIndex);
					fnpMymsg("Perform Audit action item no.   is ---" + performAuditAINo);
		
					
					int statusInnerTableColIndex = fnpFindColumnIndex_NOR(deskInnerTableHeaderXpathForAI, "Status");
					int waitCount = 0;
					String performAuditStatus;
		
						
					//fnpClick_OR("Complete_Perform_Audit_AI_button");
					//IPM-14010
					/*fngRunJob("CompletePerformAuditAIQJob");1/Oct/2020
					fngClick_OR("ISRTaskTab");*/
					
					if  (fnpCheckElementDisplayedImmediately_NOR(deskOpenIconXpath)  ) {				
						fnpClick_NOR(deskOpenIconXpath);
					}

					
					fngCheckError(" after loading ");
							
					waitCount=0;
					int maxWaitTimeInMinutes=25;
					
					 deskInnerTableHeaderXpathForAI = OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part1") + (deskRow - 1)
							+ OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part2");
					
					
					while (true) {
						waitCount++;
		
						taskAINameIndex = fnpFindColumnIndex("TaskTabTable_HeaderRow", WOISOTaskTab_TaskAISummaryTableColName_TaskAIName);
						deskRow = fnpFindRowContainsName("TaskTabTable", WOISOTaskTab_Stage1DeskAudit_MainTaskAIName, taskAINameIndex);
						deskInnerTableDataXpathForAI  = OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableData_part1") + (deskRow - 1)
								+ OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableData_part2");
						performAuditRow = fngFindRowContainsName_NOR(deskInnerTableDataXpathForAI, "PerformAudit", aiTypeInnerTableIndex);
						deskInnerTableHeaderXpathForAI = OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part1") + (deskRow - 1)
								+ OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part2");
						
						statusInnerTableColIndex = fnpFindColumnIndex_NOR(deskInnerTableHeaderXpathForAI, "Status");
						
						performAuditStatus = fnpFetchFromTable_NOR(deskInnerTableDataXpathForAI, performAuditRow, statusInnerTableColIndex);
						fnpMymsg("performAuditStatus ----" + performAuditStatus);
						
						fngCheckError(" after loading ");
		
						if (performAuditStatus.equalsIgnoreCase("AI_COMPLETED")) {
							fnpMymsg("PerformAudit status has become to 'AI_COMPLETED' state. ");
							break;
						} else {
							
							
							if (waitCount >(maxWaitTimeInMinutes*2)) {
								fnpMymsg("Script waited for approx. more than "+(maxWaitTimeInMinutes)+" min. [after running job from job url] to become 'AI_COMPLETED' state of PerformAudit but it is still showing '" + performAuditStatus + "' .");
								throw new Exception("Script waited for approx. more than "+(maxWaitTimeInMinutes)+" min. [after running job from job url] to become 'AI_COMPLETED' state of PerformAudit but it is still showing '" + performAuditStatus + "' .");
							}
							
							
							fngCheckError(" after loading ");
							fnpMymsg(" we are in  waiting loop for PerformAudit become in Ready ----" + waitCount);
							fnpDisplayingMessageInFrame("Now we are in  waiting loop for status of PerformAudit action item in desk audit task to become  Ready (by batch job behind) ----" + ((double)waitCount/2)+" approx. min.", 8);
							Thread.sleep(1000 * 30 * 1);
							fngCheckError(" after loading ");
							driver.navigate().refresh();
							// fnpLoading_wait();
							//Thread.sleep(8000);
		
							// fnpLoading_wait();
		
		
							fngCheckError(" after loading ");
							fnpClick_OR("ISRTaskTab");
							fngCheckError(" after loading ");
							if ( (fnpCheckElementDisplayedImmediately_NOR(deskOpenIconXpath) & (!(fnpCheckElementDisplayedImmediately_NOR(deskInnerTableHeaderXpathForAI))) )  ) {				
								fnpClick_NOR(deskOpenIconXpath);
							}
		
							
						}
		
						//if (waitCount > 60) {
/*						if (waitCount > 4) {
		
							fnpMymsg("Script waited for approx. 2 min to become 'AI_COMPLETED' state of PerformAudit but it is still showing '" + performAuditStatus + "' .");
							throw new Exception("Script waited for approx. 2 min to become 'AI_COMPLETED' state of PerformAudit but it is still showing '" + performAuditStatus + "' .");
		
						}*/
		
					}
				
					 aiTypeInnerTableIndex = fnpFindColumnIndex_NOR(deskInnerTableHeaderXpathForAI, "AI Type");
					 aiNumberInnerTableIndex = fnpFindColumnIndex_NOR(deskInnerTableHeaderXpathForAI, "AI Number");
					
					
					int technicalReviewAIRowNo = fngFindRowContainsName_NOR(deskInnerTableDataXpathForAI, WOISOTaskTab_TechnicalReviewAIName, aiTypeInnerTableIndex);
					
					if (technicalReviewAIRowNo>0) {
						fnpMymsg("TechnicalReview action item has been generated for Desk Audit task.");
					} else {
						fnpMymsg("TechnicalReview action item has NOT been generated for Desk Audit task.");
						throw new Exception("TechnicalReview action item has NOT been generated for Desk Audit task.");
					}
					
					String technicalReviewAINo = fnpFetchFromTable_NOR(deskInnerTableDataXpathForAI, technicalReviewAIRowNo, aiNumberInnerTableIndex);
					fnpMymsg("TechnicalReview action item no.   is ---" + technicalReviewAINo);
		
					String technicalReviewStatus = fnpFetchFromTable_NOR(deskInnerTableDataXpathForAI, technicalReviewAIRowNo, statusInnerTableColIndex);
					
					if (!(technicalReviewStatus.equalsIgnoreCase("AI_CREATED"))) {
						
						fnpMymsg("Current Status of TechnicalReview action item is NOT 'AI_CREATED'  b/c its current status is --"+technicalReviewStatus);
						throw new Exception ("Current Status of TechnicalReview action item is NOT 'AI_CREATED'  b/c its current status is --"+technicalReviewStatus);
					} else {
						fnpMymsg("Current Status of TechnicalReview action item is AI_CREATED.");
					}
		
					fngProcessAI_ISR_TaskTab_1(deskInnerTableDataXpathForAI,deskInnerTableHeaderXpathForAI,WOISOTaskTab_TechnicalReviewAIName, "Completed","SaveButton__OnConsolidatedScreen");
					
		
					fnpVerifyAIStatusInISR(WOISOTaskType_DeskAudit,WOISOTaskTab_Stage1DeskAudit_MainTaskAIName,WOISOTaskTab_TechnicalReviewAIName,"Completed");
					
					
					fnpRecordChangeAI(WOISOTaskTab_Stage1DeskAudit_MainTaskAIName,WOISOTaskType_DeskAudit);
					
			}


			 /*************************************************************************************/
			
	
			
			fnpMymsg("===============================================================================================================");

			// }

		} catch (Throwable t) {
			//isTestPass = true;
			fnpMymsg(" **************************************************************");
			fngCommonFinalCatchBlock(t, "  DeskAuditTask flow  is fail . ", "DeskAuditTask");

		}

	}
	
	
	@Test(priority = 5, dependsOnMethods = { "DeskAuditTask" })

	public void CertAuditTask() throws Throwable {
		

		try {
	
			fnpClick_OR("ISRTaskTab");

			String textMessage = "Now going to perform Cert Audit task. ";
			fnpDisplayingMessageInFrame(textMessage, 5);
			String certOpenIconXpath = OR_SUS.getProperty("TaskTabISR_CertAudit_OpenIconPart1") + WOISOTaskType_CertificationAudit+ OR_SUS.getProperty("TaskTabISR_CertAudit_OpenIconPart2");
			//fnpClick_NOR(certOpenIconXpath);

			if(  fnpCheckElementDisplayedImmediately_NOR(certOpenIconXpath)   ) {				
				fnpClick_NOR(certOpenIconXpath);
			}
			
			
			 int taskAINameIndex = fnpFindColumnIndex("TaskTabTable_HeaderRow", WOISOTaskTab_TaskAISummaryTableColName_TaskAIName);
			//int certRow = fnpFindRowContainsName("TaskTabTable", "Certification", taskAINameIndex);
			int certRow = fnpFindRowContainsName("TaskTabTable", WOISOTaskTab_Certification_MainTaskAIName, taskAINameIndex);
			
			if (!(classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.Cert_StandardsTestCaseName)) ){
			
						
						////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
						
						fnpMymsg("Expected Cert Audit billing code  is ---" + certAuditTaskTab_expectedBillCode);
						fnpMymsg("Expected Cert Audit quantity  is ---" + certAuditTaskTab_expectedQuantity);
						fnpMymsg("Expected Cert Audit Audit days  is ---" + certAuditTaskTab_expectedAuditDays);
						fnpMymsg("");
						fnpMymsg("");
			

			
						String certInnerTableHeaderXpath = OR_SUS.getProperty("ISRTaskInnerTableForBillingCodeTableHeader_part1") + (certRow - 1)
								+ OR_SUS.getProperty("ISRTaskInnerTableForBillingCodeTableHeader_part2");
			
						 int billingCodeInnerTableIndex = fnpFindColumnIndex_NOR(certInnerTableHeaderXpath, "Billing Code");
						 int quantityInnerTableIndex = fnpFindColumnIndex_NOR(certInnerTableHeaderXpath, "Qty.");
			
						String certInnerTableDataXpath = OR_SUS.getProperty("ISRTaskInnerTableForBillingCodeTableData_part1") + (certRow - 1)
								+ OR_SUS.getProperty("ISRTaskInnerTableForBillingCodeTableData_part2");
						
						
						int billingDescIndex = fnpFindColumnIndex_NOR(certInnerTableHeaderXpath, "Billing Desc");
						int innercertRow = fngFindRowContainsName_NOR_CertAudit(certInnerTableDataXpath, "R2 Certification Audit", billingDescIndex);//Certification Audit
						
						
						String actualCertBillingCode = fnpFetchFromTable_NOR(certInnerTableDataXpath, innercertRow, billingCodeInnerTableIndex);
						fnpMymsg("Actual Cert Audit billing code  is ---" + actualCertBillingCode);
						String actualCertQuantityInString = fnpFetchFromTable_NOR(certInnerTableDataXpath, innercertRow, quantityInnerTableIndex);
						double actualCertQuantity = Double.parseDouble(actualCertQuantityInString);
						fnpMymsg("Actual Cert Audit quantity  is ---" + actualCertQuantity);
			
						String certExpansionPanelXpath = OR_SUS.getProperty("ISRTaskExpansionPanelGrid_part1") + (certRow - 1) + OR_SUS.getProperty("ISRTaskExpansionPanelGrid_part2");
			
						String certAuditDaysXpath = certExpansionPanelXpath + "//tr/td/label[text()='Audit Days']/../following-sibling::td/label";
			
						String certAuditdaysInString = fnpGetText_NOR(certAuditDaysXpath);
						double actualCertAuditdays = Double.parseDouble(certAuditdaysInString);
						fnpMymsg("Actual Cert Audit audit days are ---" + actualCertAuditdays);
						fnpMymsg("Expected Cert Audit audit days are ---" + certAuditTaskTab_expectedAuditDays);
			
						Assert.assertTrue(actualCertBillingCode.equalsIgnoreCase(certAuditTaskTab_expectedBillCode), "Cert Audit billing code is not matched on task tab.");
						textMessage = "Cert Audit billing code is matched on task tab. Actual is --" + actualCertBillingCode + "   and expected is ---" + certAuditTaskTab_expectedBillCode;
						fnpDisplayingMessageInFrame(textMessage, 5);
						fnpMymsg(textMessage);
			
						Assert.assertTrue(actualCertQuantity == certAuditTaskTab_expectedQuantity, "Cert Audit quantity is not matched on task tab.");
						textMessage = "Cert Audit quantity is  matched on task tab. Actual is --" + actualCertQuantity + "   and expected is ---" + certAuditTaskTab_expectedQuantity;
						fnpDisplayingMessageInFrame(textMessage, 5);
						fnpMymsg(textMessage);
			
						Assert.assertTrue(actualCertAuditdays == certAuditTaskTab_expectedAuditDays, "Cert Audit Audit days are not matched on task tab.");
						textMessage = "Cert Audit Audit days are   matched on task tab. Actual is --" + actualCertAuditdays + "   and expected is ---" + certAuditTaskTab_expectedAuditDays;
						fnpDisplayingMessageInFrame(textMessage, 5);
						fnpMymsg(textMessage);
			
						/*************************************************************************************/
			
						String certAuditboundaryStartDateXpath = certExpansionPanelXpath + "//tr/td/label[contains(text(),'Boundary Start Date')]/../following-sibling::td/label";
						String certAuditboundaryEndDateXpath = certExpansionPanelXpath + "//tr/td/label[contains(text(),'Boundary End Date')]/../following-sibling::td/label";
			
						String certAuditBoudaryStartDateInStringformatAtTaskTab = fnpGetText_NOR(certAuditboundaryStartDateXpath);
						textMessage = "Cert Audit =>Boundary Start Date present on this page is ---" + certAuditBoudaryStartDateInStringformatAtTaskTab;
						fnpMymsg(textMessage);
						fnpDisplayingMessageInFrame(textMessage, 5);
						
						SimpleDateFormat sdfmt1 = new SimpleDateFormat("dd-MMM-yyyy");
						Date boudaryStartDateInDateformatAtTaskTab = sdfmt1.parse(certAuditBoudaryStartDateInStringformatAtTaskTab);
						
			
						String certAuditboundaryEndDateInStringformatAtTaskTab = fnpGetText_NOR(certAuditboundaryEndDateXpath);
						textMessage = "Cert Audit =>Boundary End Date present on this page is ---" + certAuditboundaryEndDateInStringformatAtTaskTab;
						fnpMymsg(textMessage);
						fnpDisplayingMessageInFrame(textMessage, 5);
			
						
						Date boundaryEndDateInDateformatAtTaskTab = sdfmt1.parse(certAuditboundaryEndDateInStringformatAtTaskTab);
						
			
						
						
						int startDateNo;
						int endDateNo;
						
						
			
						
						 if((classNameText.equalsIgnoreCase(Sus_SingleTestCaseName))){
								 startDateNo=80;
								 endDateNo=60; 
						
						
						
									fnpClick_OR("ISRInfoTab_EditWO");
						
									if (fnpCheckElementDisplayedImmediately("EditWOBtn")) {				
										fnpClick_OR("EditWOBtn");
									}
						
									 String actualCertTargetGivenAtInfoTab = fnpWaitTillTextBoxDontHaveValue("CertTargetDateInputBxInfoTab", "value");
						
									  sdfmt1 = new SimpleDateFormat("dd-MMM-yyyy");
									 Date date_actualCertTargetGivenAtInfoTab = sdfmt1.parse(actualCertTargetGivenAtInfoTab);
						
									 Calendar c = Calendar.getInstance();
									 c.setTime(date_actualCertTargetGivenAtInfoTab);
									 c.add(Calendar.DAY_OF_MONTH, -startDateNo);
									 Date certTargetDateMinusStartDateFromBNF = c.getTime();
						
									 
									// Date boudaryStartDateInDateformatAtTaskTab = sdfmt1.parse(certAuditBoudaryStartDateInStringformatAtTaskTab);
						
									 
									fnpMymsg("CertTarget start date given at Info tab is ---"+actualCertTargetGivenAtInfoTab);
									fnpMymsg("Using BNF rule, for start date it should be minus by --"+startDateNo);
									fnpMymsg("Boundary start date given at Task tab for Cert Audit is ---"+certAuditBoudaryStartDateInStringformatAtTaskTab);
						
						
						
									 
									 
									 
									if (certTargetDateMinusStartDateFromBNF.compareTo(boudaryStartDateInDateformatAtTaskTab) > 0) {
										textMessage = "certTargetDateMinusStartDateFromBNF is after certAuditBoudaryStartDateInStringformatAtTaskTab, hence failed. " +
												"certTargetDateMinusStartDateFromBNF is '"+actualCertTargetGivenAtInfoTab+" minus (-) "+startDateNo +"  should be equal to "+certAuditBoudaryStartDateInStringformatAtTaskTab ;
										fnpMymsg(textMessage);
										fnpDisplayingMessageInFrame(textMessage, 5);
										throw new Exception(textMessage);
									} else if (certTargetDateMinusStartDateFromBNF.compareTo(boudaryStartDateInDateformatAtTaskTab) < 0) {
										textMessage = "certTargetDateMinusStartDateFromBNF is before certAuditBoudaryStartDateInStringformatAtTaskTab, hence failed. " +
												"certTargetDateMinusStartDateFromBNF is '"+actualCertTargetGivenAtInfoTab+" minus (-) "+startDateNo +"  should be equal to "+certAuditBoudaryStartDateInStringformatAtTaskTab ;
										fnpMymsg(textMessage);
										fnpDisplayingMessageInFrame(textMessage, 5);
										throw new Exception(textMessage);
									} else {
										textMessage = "Cert target Date - (Minus)  " + startDateNo + " days is equal to Boundary start date for Cert Audit in Tasks tab of WO";
										fnpMymsg(textMessage);
										fnpDisplayingMessageInFrame(textMessage, 5);
									}
						
									c = Calendar.getInstance();
									c.setTime(date_actualCertTargetGivenAtInfoTab);
									c.add(Calendar.DAY_OF_MONTH, -endDateNo);
									 Date certTargetDateMinusEndDateFromBNF = c.getTime();
						
						
									// Date boundaryEndDateInDateformatAtTaskTab = sdfmt1.parse(certAuditboundaryEndDateInStringformatAtTaskTab);
						
										fnpMymsg("CertTarget start date given at Info tab is ---"+actualCertTargetGivenAtInfoTab);
										fnpMymsg("Using BNF rule, for start date it should be minus by --"+endDateNo);
										fnpMymsg("Boundary End date given at Task tab for Cert Audit is ---"+certAuditboundaryEndDateInStringformatAtTaskTab);
						
						
									 
									if (certTargetDateMinusEndDateFromBNF.compareTo(boundaryEndDateInDateformatAtTaskTab) > 0) {
										textMessage = "certTargetDateMinusEndDateFromBNF is after certAuditboundaryEndDateInStringformatAtTaskTab, hence failed. " +
												"certTargetDateMinusEndDateFromBNF is '"+actualCertTargetGivenAtInfoTab+" minus (-) "+endDateNo +"  should be equal to "+certAuditboundaryEndDateInStringformatAtTaskTab ;
										fnpMymsg(textMessage);
										fnpDisplayingMessageInFrame(textMessage, 5);
										throw new Exception(textMessage);
									} else if (certTargetDateMinusEndDateFromBNF.compareTo(boundaryEndDateInDateformatAtTaskTab) < 0) {
										textMessage = "certTargetDateMinusEndDateFromBNF is before certAuditboundaryEndDateInStringformatAtTaskTab, hence failed. " +
												"certTargetDateMinusEndDateFromBNF is '"+actualCertTargetGivenAtInfoTab+" minus (-) "+endDateNo +"  should be equal to "+certAuditboundaryEndDateInStringformatAtTaskTab ;
										fnpMymsg(textMessage);
										fnpDisplayingMessageInFrame(textMessage, 5);
										throw new Exception(textMessage);
									} else {
										textMessage = "Cert target Date - (Minus) " + endDateNo + " days is equal to Boundary End date  in Tasks tab of WO";
										fnpMymsg(textMessage);
										fnpDisplayingMessageInFrame(textMessage, 5);
									}
						
									fnpClick_OR("ISRTaskTab");
							
						
						 }
			
						textMessage = "Now going to click Audit no. in Cert Audit at Task Tab ";
						fnpDisplayingMessageInFrame(textMessage, 5);
						//fnpClick_NOR(certOpenIconXpath);
						
						
						if ( (fnpCheckElementDisplayedImmediately_NOR(certOpenIconXpath)  )  ) {				
							fnpClick_NOR(certOpenIconXpath);
							fnpMymsg("Clicked cert open icon");
						}
						
	
						certRow = fnpFindRowContainsName("TaskTabTable", WOISOTaskTab_Certification_MainTaskAIName, taskAINameIndex);
						String auditNoInCertAuditXpath = OR_SUS.getProperty("AuditNoAtTaskTab_part1") + (certRow - 1) + OR_SUS.getProperty("AuditNoAtTaskTab_part2");
			
						 String auditNo = fnpGetText_NOR(auditNoInCertAuditXpath);
						fnpDisplayingMessageInFrame("Going to click this Audit no. in Cert Audit  --" + auditNo, 5);
			
						fnpGetORObjectX___NOR(auditNoInCertAuditXpath).click();
						fnpClick_OR("EditBtnOnViewAuditPage");
						fnpClick_OR("Audit_InfoTabLink");
			
						 String actualDurationText = fnpGetText_OR("DurationAtInfoTabOfViewAudit").trim();
			
						 String[] actualDurationInStringArray = actualDurationText.split(" ");
			
						 double actualDuration = Double.parseDouble(actualDurationInStringArray[0]);
			
						if (actualDuration != actualCertAuditdays) {
			
							textMessage = "Duration --'" + actualDuration + "' and Cert Audit days --'" + actualCertAuditdays + "' are NOT equal, hence failed.";
							fnpMymsg(textMessage);
							fnpDisplayingMessageInFrame(textMessage, 5);
							throw new Exception(textMessage);
						} else {
							textMessage = "Duration --'" + actualDuration + "' and Audit days --'" + actualCertAuditdays + "' are  equal.";
							fnpMymsg(textMessage);
							fnpDisplayingMessageInFrame(textMessage, 5);
						}
			
						 String doNotScheduleBeforeText = fnpGetText_OR("DoNotScheduleBeforeAtInfoTabOfViewAudit").trim();
			
						 sdfmt1 = new SimpleDateFormat("dd-MMM-yyyy");
						 Date date_doNotScheduleBeforeGivenAtViewAuditInfoTab = sdfmt1.parse(doNotScheduleBeforeText);
			
						if (date_doNotScheduleBeforeGivenAtViewAuditInfoTab.compareTo(boudaryStartDateInDateformatAtTaskTab) > 0) {
							textMessage = " Do not Schedule Before---'" + doNotScheduleBeforeText + "' is after to Boundary start date --'" + certAuditBoudaryStartDateInStringformatAtTaskTab
									+ "' , hence failed.";
							fnpMymsg(textMessage);
							fnpDisplayingMessageInFrame(textMessage, 5);
							throw new Exception(textMessage);
						} else if (date_doNotScheduleBeforeGivenAtViewAuditInfoTab.compareTo(boudaryStartDateInDateformatAtTaskTab) < 0) {
							textMessage = " Do not Schedule Before---'" + doNotScheduleBeforeText + "' is before to Boundary start date --'" + certAuditBoudaryStartDateInStringformatAtTaskTab
									+ "' , hence failed.";
							fnpMymsg(textMessage);
							fnpDisplayingMessageInFrame(textMessage, 5);
							throw new Exception(textMessage);
						} else {
							textMessage = " Do not Schedule Before---'" + doNotScheduleBeforeText + "' is equal to Boundary start date --'" + certAuditBoudaryStartDateInStringformatAtTaskTab
									+ "' in Tasks tab of WO";
							fnpMymsg(textMessage);
							fnpDisplayingMessageInFrame(textMessage, 5);
						}
			
						 String deadLineDateText = fnpGetText_OR("DeadLineDateAtInfoTabOfViewAudit").trim();
			
						sdfmt1 = new SimpleDateFormat("dd-MMM-yyyy");
						 Date deadLineDateAtViewAuditInfoTab = sdfmt1.parse(deadLineDateText);
			
						if (deadLineDateAtViewAuditInfoTab.compareTo(boundaryEndDateInDateformatAtTaskTab) > 0) {
							textMessage = " DeadLine date---'" + deadLineDateText + "' is after to Boundary End date --'" + certAuditboundaryEndDateInStringformatAtTaskTab + "' , hence failed.";
							fnpMymsg(textMessage);
							fnpDisplayingMessageInFrame(textMessage, 5);
							throw new Exception(textMessage);
						} else if (deadLineDateAtViewAuditInfoTab.compareTo(boundaryEndDateInDateformatAtTaskTab) < 0) {
							textMessage = " DeadLine date---'" + deadLineDateText + "' is before to Boundary End date --'" + certAuditboundaryEndDateInStringformatAtTaskTab + "' , hence failed.";
							fnpMymsg(textMessage);
							fnpDisplayingMessageInFrame(textMessage, 5);
							throw new Exception(textMessage);
						} else {
							textMessage = " DeadLine date---'" + deadLineDateText + "' is equal to Boundary End date --'" + certAuditboundaryEndDateInStringformatAtTaskTab + "' in Tasks tab of WO";
							fnpMymsg(textMessage);
							fnpDisplayingMessageInFrame(textMessage, 5);
						}
			
						fnpClick_OR("ISRBackToViewBtn");
						fnpClick_OR("ISRBackBtn");
						
						
						
			}
						
						
						

			fnpClick_OR("ISRTaskTab");

			
			//fnpClick_NOR(certOpenIconXpath);
			if ( (fnpCheckElementDisplayedImmediately_NOR(certOpenIconXpath)  )  ) {				
				fnpClick_NOR(certOpenIconXpath);
				fnpMymsg("Clicked cert open icon");
			}
			
			certRow = fnpFindRowContainsName("TaskTabTable", WOISOTaskTab_Certification_MainTaskAIName, taskAINameIndex);
			String auditNoInCertAuditXpath = OR_SUS.getProperty("AuditNoAtTaskTab_part1") + (certRow - 1) + OR_SUS.getProperty("AuditNoAtTaskTab_part2");
			
			String auditNo = fnpGetText_NOR(auditNoInCertAuditXpath);
			
			

			String visitNoInCertAuditXpath = OR_SUS.getProperty("VisitNoAtTaskTab_part1") + (certRow - 1) + OR_SUS.getProperty("VisitNoAtTaskTab_part2");

			 String visitNo = fnpGetText_NOR(visitNoInCertAuditXpath).trim();
			 fnpMymsg("Going to check visit no, as if visit no is not present/blank then it will go for clicking bundle audit button. So here visit no. is--'"+visitNo+"'.");			 
			if (visitNo.equalsIgnoreCase("")) {
				fnpGetORObjectX___NOR(auditNoInCertAuditXpath).click();
				fnpWaitForVisible("ViewAudit_InfoTabLink");
				String visitNoAtViewAuditPage="";
				if (fnpCheckElementPresenceImmediately("visitLinkInInfoTab")) {
					 visitNoAtViewAuditPage = fnpGetText_OR("visitLinkInInfoTab").trim();
				} else {
					//nothing to do
				}
				
				fnpMymsg("Going to check visit no AGAIN, as if visit no is not present/blank then it will go for clicking bundle audit button. So here visit no. is--'"+visitNoAtViewAuditPage+"'.");
				if (visitNoAtViewAuditPage.equalsIgnoreCase("")) {	
					Thread.sleep(2000); // sometime even after clicking bundle audit button, visit not generated, so giving a wait before clicking this bundle button
					fnpClick_OR("BundleAuditBtn");
				}

				fnpClick_OR("ISRBackBtn");
				fnpClick_OR("ISRTaskTab");
				
				if (fnpCheckElementDisplayedImmediately("EditWOBtn")) {				
					fnpClick_OR("EditWOBtn");
				}
				if ( (fnpCheckElementDisplayedImmediately_NOR(certOpenIconXpath)  )  ) {				
					fnpClick_NOR(certOpenIconXpath);
					fnpMymsg("Clicked cert open icon");
				}

				visitNo = fnpGetText_NOR(visitNoInCertAuditXpath).trim();
			}

						
						
			
			
			
			
			 visitNoInCertAuditXpath = OR_SUS.getProperty("VisitNoAtTaskTab_part1") + (certRow - 1) + OR_SUS.getProperty("VisitNoAtTaskTab_part2");
			
			 auditNoInCertAuditXpath = OR_SUS.getProperty("AuditNoAtTaskTab_part1") + (certRow - 1) + OR_SUS.getProperty("AuditNoAtTaskTab_part2");
			
			
			 auditNo = fnpGetText_NOR(auditNoInCertAuditXpath);
			
			
			
			
			
			
			
			
			
					
			if (fnpCheckElementDisplayedImmediately("EditWOBtn")) {				
				fnpClick_OR("EditWOBtn");
			}
			


			
			
			/*****************IPM-6227***********************************************/
/*			
			String editBtnForEditCertAuditTaskXpath = OR_SUS.getProperty("EditBtnForEditTaskAtTaskTab_part1") + (certRow - 1) + OR_SUS.getProperty("EditBtnForEditTaskAtTaskTab_part2");			
			fnpClick_NOR(editBtnForEditCertAuditTaskXpath);
			*/
			
/*			String expenseAllocationbtnXpath = OR_SUS.getProperty("ExpenseAllocationBtn_TaskAtTaskTab_part1") + (certRow - 1) + OR_SUS.getProperty("ExpenseAllocationBtn_TaskAtTaskTab_part2");	
			fnpClick_NOR(expenseAllocationbtnXpath);
			*/
			
			
			String editFeesbtnXpath = OR_SUS.getProperty("EditFeesBtnForEditTaskAtTaskTab_part1") + (certRow - 1) + OR_SUS.getProperty("EditFeesBtnForEditTaskAtTaskTab_part2");	
			fnpClick_NOR(editFeesbtnXpath);
			
			
			/***************************************************************************/
			
			
			Date todayDate=new Date();
			SimpleDateFormat sdfmt1 = new SimpleDateFormat("dd-MMM-yyyy");
			String todaydateInStringformat = sdfmt1.format(todayDate);
		//	fngType("OR_SUS", "BoundaryStartDateEditTxtBxAtEditTaskDialog", todaydateInStringformat);
			
			
			fnpClick_OR_WithoutWait("BoundaryStartDateBtnAtEditTaskDialog");
			Thread.sleep(2000);			
			fngCalendarDatePicker_BySelectValues_From_NextAndBack(todaydateInStringformat);
			
			//Thread.sleep(4000);
			Thread.sleep(2000);	
			
			
			
			
			
			todayDate=new Date();
			Calendar c = Calendar.getInstance();
			c.setTime(todayDate);
			c.add(Calendar.DAY_OF_MONTH, 7);
			Date afterOneWeekdate = c.getTime();

			sdfmt1 = new SimpleDateFormat("dd-MMM-yyyy");
			String afterOneWeekdateInStringformat = sdfmt1.format(afterOneWeekdate);
		//	fngType("OR_SUS", "BoundaryEndDateEditTxtBxAtEditTaskDialog", afterOneWeekdateInStringformat);	
			
			
			fnpClick_OR_WithoutWait("BoundaryEndDateBtnAtEditTaskDialog");
			Thread.sleep(2000);			
			fngCalendarDatePicker_BySelectValues_From_NextAndBack(afterOneWeekdateInStringformat);
			
			Thread.sleep(2000);	
			
			
			//25July2019 New Field(Justification Reason) added into Edit Task Fee Popup.
			fngPFList("Justification_Reason_EditTaskFeePopup_List", "Justification_Reason_EditTaskFee_Popup_ListOptions",(String) hashXlData.get("JustificationReason_EditTaskFee"));
			//20Apr2021 New Field(Duration Justification Reason) added into Edit Task Fee Popup.
			Thread.sleep(2000);	
			fngPFList("Duration_Justification_Reason_EditTaskFeePopup_List", "Duration_Justification_Reason_EditTaskFee_Popup_ListOptions",(String) hashXlData.get("Duration_JustificReason_EditTaskFee"));
			Thread.sleep(2000);
			WebElement wb = driver.findElement(By.xpath(".//button[@id='mainForm:tabView:edittasksave']"));
	        JavascriptExecutor executor = (JavascriptExecutor)driver;
	        executor.executeScript("arguments[0].click();", wb);
			//fnpClick_OR("SaveBtnAtEditTask");
			fnpLoading_waitInsideDialogBox();
			
			fnpClick_OR("ISRSFTabSaveBtn");
			
			
			
			
			
			
			
			
			if ( (fnpCheckElementDisplayedImmediately_NOR(certOpenIconXpath) & (!fnpCheckElementDisplayedImmediately_NOR(visitNoInCertAuditXpath)) )  ) {				
				fnpClick_NOR(certOpenIconXpath);
			}
			


			visitNo = fnpGetText_NOR(visitNoInCertAuditXpath).trim();
			if (visitNo.equalsIgnoreCase("")) {		
			throw new Exception("Visit No. is still not coming in cert audit task even after clicking bundle audit button at view audit screen.");			
			}
			
			fnpDisplayingMessageInFrame("Going to click this Visit no.  --" + visitNo, 5);

			
			
			fnpGetORObjectX___NOR(visitNoInCertAuditXpath).click();
			fnpClick_OR("EditBtnOnViewVisitPage");

	
			
			todaydateInStringformat = sdfmt1.format(new Date());
			String scheduleEndDateforCertAudit = fnaSelectingAuditorThroughSAM();
			String scheduleStartDateforCertAudit=todaydateInStringformat;
			//String scheduleEndDateforCertAudit=todaydateInStringformat;

			int copyFromAudit_ForCertAudit;
			int copyToAudit_ForCertAudit;
			String usingGoldenProcedureOrOasis = (String) hashXlData.get("Audit_usingGoldenProcedureOrOasis");
			if (usingGoldenProcedureOrOasis.equalsIgnoreCase("golden")) {
				copyFromAudit_ForCertAudit=Integer.parseInt((String) hashXlData.get("copy_From_Audit_ForCertAuditTask").trim());
				copyToAudit_ForCertAudit=Integer.parseInt(auditNo.trim());
				
				fnpMymsg("Just before running golden procedure - COPY_AUDIT_TRANSACTION_SUBMIT");
				fnpCallGoldenProcedure_COPY_AUDIT_TRANSACTION_SUBMIT(copyFromAudit_ForCertAudit,copyToAudit_ForCertAudit,(String) hashXlData.get("Auditor").trim());
				fnpMymsg("Golden procedure 'COPY_AUDIT_TRANSACTION_SUBMIT' ran successfully.");
			} else {
				
				if (usingGoldenProcedureOrOasis.equalsIgnoreCase("oasis")) {
					fnpOasisPartInISO("cert", visitNo);
					fngLaunchBrowserAndLogin();
				} else {
					throw new Exception("Value is not assigned to usingGoldenOrOasis");
				}
			
			}
			
			
			// some part of test case is remaining here
			
			

			//fnpLaunchBrowserAndLogin();
			fnpSearchWorkOrderOnly(workOrderNo);
			

			if (fnpCheckElementDisplayedImmediately("EditWOBtn")) {
				fnpClick_OR("EditWOBtn");
				fnpMymsg("@  ---Here EditWOBtn is visible , so going to click it");

			}else{
				fnpMymsg("@  ---Here EditWOBtn is NOT visible , so ignore  it");
			}
			
						
			
			
			fnpClick_OR("ISRTaskTab");
			
			
			
			
			if ( (fnpCheckElementDisplayedImmediately_NOR(certOpenIconXpath) & (!fnpCheckElementDisplayedImmediately_NOR(visitNoInCertAuditXpath)) )  ) {				
				fnpClick_NOR(certOpenIconXpath);
			}
			
			
			
			
			
			
			
			taskAINameIndex = fnpFindColumnIndex("TaskTabTable_HeaderRow", WOISOTaskTab_TaskAISummaryTableColName_TaskAIName);
			//certRow = fnpFindRowContainsName("TaskTabTable", "Certification", taskAINameIndex);
			certRow = fnpFindRowContainsName("TaskTabTable", WOISOTaskTab_Certification_MainTaskAIName, taskAINameIndex);
			
			String certExpansionPanelXpath = OR_SUS.getProperty("ISRTaskExpansionPanelGrid_part1") + (certRow - 1) + OR_SUS.getProperty("ISRTaskExpansionPanelGrid_part2");
			
			
			
			
			
			taskAINameIndex = fnpFindColumnIndex("TaskTabTable_HeaderRow", WOISOTaskTab_TaskAISummaryTableColName_TaskAIName);
			//certRow = fnpFindRowContainsName("TaskTabTable", "Certification", taskAINameIndex);
			certRow = fnpFindRowContainsName("TaskTabTable", WOISOTaskTab_Certification_MainTaskAIName, taskAINameIndex);
			String certInnerTableDataXpathForAI = OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableData_part1") + (certRow - 1)
					+ OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableData_part2");
			
			String certInnerTableHeaderXpathForAI = OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part1") + (certRow - 1)
					+ OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part2");
			
			

			
			String certAuditorXpath = certExpansionPanelXpath + "//tr/td/label[text()='Auditor']/../following-sibling::td/label";
			String certAuditor = fnpGetText_NOR(certAuditorXpath);
			
			if (certAuditor.equalsIgnoreCase((String)hashXlData.get("AccountManager"))) {
				fnpMymsg("Auditor is present in Cert Audit and its value is ---"+certAuditor);
			} else {
				fnpMymsg("Auditor is NOT present in Cert Audit and its value is ---"+certAuditor);
				throw new Exception("Auditor is NOT present in Cert Audit");
			}
			
			
			
			
			
			

			String scheduleStartDateXpath = certExpansionPanelXpath + "//tr/td/label[text()='Schedule Start Date']/../following-sibling::td/label";
			String actualScheduleStartDate = fnpGetText_NOR(scheduleStartDateXpath);
			
			if (actualScheduleStartDate.equalsIgnoreCase(scheduleStartDateforCertAudit)) {
				fnpMymsg("Schedule Audit start date in Cert Audit is present and its value is ---'"+actualScheduleStartDate +"' and expected is --'"+scheduleStartDateforCertAudit +"' .");
			} else {
				fnpMymsg("Schedule Audit start date in Cert Audit  is NOT  matched as its value is ---"+actualScheduleStartDate+"' and expected is --'"+scheduleStartDateforCertAudit +"' .");
				//throw new Exception("Schedule Audit start date is NOT  present");
				throw new Exception("Schedule Audit start date in Cert Audit  is NOT  matched as its value is ---"+actualScheduleStartDate+"' and expected is --'"+scheduleStartDateforCertAudit +"' .");
			}
			
			
			
			
			String scheduleEndDateXpath = certExpansionPanelXpath + "//tr/td/label[text()='Schedule End Date']/../following-sibling::td/label";
			String actualScheduleEndDate = fnpGetText_NOR(scheduleEndDateXpath);
	
			
			if (actualScheduleEndDate.equalsIgnoreCase(scheduleEndDateforCertAudit)) {
				fnpMymsg("Schedule Audit End date in Cert Audit  is present and its value is ---'"+actualScheduleEndDate +"' and expected is --'"+scheduleEndDateforCertAudit +"' .");
			} else {
				fnpMymsg("Schedule Audit End date in Cert Audit  is NOT  matched as its value is ---"+actualScheduleEndDate+"' and expected is --'"+scheduleEndDateforCertAudit +"' .");
				//throw new Exception("Schedule Audit End date is NOT  present");
				throw new Exception("Schedule Audit End date in Cert Audit  is NOT  matched as its value is ---"+actualScheduleEndDate+"' and expected is --'"+scheduleEndDateforCertAudit +"' .");
			}
		
			
			
			String CertAuditStatusXpath = certExpansionPanelXpath + "//tr/td/label[text()='Audit Status']/../following-sibling::td/label";
			String CertAuditStatus = fnpGetText_NOR(CertAuditStatusXpath);
			
			if (CertAuditStatus.equalsIgnoreCase("SUBMITTED")) {
				fnpMymsg("Cert Audit status is  become 'SUBMITTED'  as its value is ---'"+CertAuditStatus +"' and expected is --'"+"SUBMITTED" +"' .");
			} else {
				fnpMymsg("Cert Audit status is NOT become 'SUBMITTED'  as its value is ---"+CertAuditStatus+"' and expected is --'"+"SUBMITTED" +"' .");
				//throw new Exception("Cert Audit status is NOT become 'SUBMITTED'");
				throw new Exception("Cert Audit status is NOT become 'SUBMITTED'  as its value is ---"+CertAuditStatus+"' and expected is --'"+"SUBMITTED" +"' .");
			}
			
		
	
	
			
			


			 certInnerTableHeaderXpathForAI = OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part1") + (certRow - 1)
					+ OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part2");

			int aiTypeInnerTableIndex = fnpFindColumnIndex_NOR(certInnerTableHeaderXpathForAI, "AI Type");
			int aiNumberInnerTableIndex = fnpFindColumnIndex_NOR(certInnerTableHeaderXpathForAI, "AI Number");

			 certInnerTableDataXpathForAI  = OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableData_part1") + (certRow - 1)
					+ OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableData_part2");
			
			
			int performAuditRow = fngFindRowContainsName_NOR_ForAI(certInnerTableDataXpathForAI, "PerformAudit", aiTypeInnerTableIndex);
			
			if (performAuditRow>0) {
				fnpMymsg("Perform Audit action item has been generated for Cert Audit task.");
			} else {
				fnpMymsg("Perform Audit action item has NOT been generated for Cert Audit task.");
				throw new Exception("Perform Audit action item has NOT been generated for Cert Audit task.");
			}
			
			String performAuditAINo = fnpFetchFromTable_NOR(certInnerTableDataXpathForAI, performAuditRow, aiNumberInnerTableIndex);
			fnpMymsg("Perform Audit action item no.   is ---" + performAuditAINo);
			
			

			
			
			int statusInnerTableColIndex = fnpFindColumnIndex_NOR(certInnerTableHeaderXpathForAI, "Status");
			int waitCount = 0;
			String performAuditStatus;


				
			//fnpClick_OR("Complete_Perform_Audit_AI_button");
			/*IPM-14010
			 * fngRunJob("CompletePerformAuditAIQJob"); fngClick_OR("ISRTaskTab");
			 */
			if  (fnpCheckElementDisplayedImmediately_NOR(certOpenIconXpath) ) {				
				fnpClick_NOR(certOpenIconXpath);
			}

			
			fngCheckError(" after loading ");
					
			waitCount=0;
			int maxWaitTimeInMinutes=25;
			while (true) {
				waitCount++;

				taskAINameIndex = fnpFindColumnIndex("TaskTabTable_HeaderRow", WOISOTaskTab_TaskAISummaryTableColName_TaskAIName);
				certRow = fnpFindRowContainsName("TaskTabTable", WOISOTaskTab_Certification_MainTaskAIName, taskAINameIndex);
				certInnerTableDataXpathForAI  = OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableData_part1") + (certRow - 1)
						+ OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableData_part2");
				performAuditRow = fngFindRowContainsName_NOR_ForAI(certInnerTableDataXpathForAI, "PerformAudit", aiTypeInnerTableIndex);
				certInnerTableHeaderXpathForAI = OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part1") + (certRow - 1)
						+ OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part2");
				
				statusInnerTableColIndex = fnpFindColumnIndex_NOR(certInnerTableHeaderXpathForAI, "Status");
				
				performAuditStatus = fnpFetchFromTable_NOR(certInnerTableDataXpathForAI, performAuditRow, statusInnerTableColIndex);
				fnpMymsg("performAuditStatus ----" + performAuditStatus);
				
				fngCheckError(" after loading ");

				if (performAuditStatus.equalsIgnoreCase("AI_COMPLETED")) {
					fnpMymsg("PerformAudit status has become to 'AI_COMPLETED' state. ");
					break;
				} else {
					
					
					if (waitCount >(maxWaitTimeInMinutes*2)) {
						fnpMymsg("Script waited for approx. more than "+(maxWaitTimeInMinutes)+" min. [after running job from job url] to become 'AI_COMPLETED' state of PerformAudit but it is still showing '" + performAuditStatus + "' .");
						throw new Exception("Script waited for approx. more than "+(maxWaitTimeInMinutes)+" min. [after running job from job url] to become 'AI_COMPLETED' state of PerformAudit but it is still showing '" + performAuditStatus + "' .");
					}
					
					
					fngCheckError(" after loading ");
					fnpMymsg(" we are in  waiting loop for PerformAudit become in Ready ----" + waitCount);
					fnpDisplayingMessageInFrame("Now we are in  waiting loop for status of PerformAudit action item to become  Ready (by batch job behind) ----" + ((double)waitCount/2)+" approx. min.", 8);
					Thread.sleep(1000 * 30 * 1);
					fngCheckError(" after loading ");
					driver.navigate().refresh();
					// fnpLoading_wait();
					//Thread.sleep(8000);

					// fnpLoading_wait();
					//23/Nov/2020 start Code Change
					//fngClick_OR("ISRInfoTab_EditWO");
					fngCheckError(" after loading ");
					fnpClick_OR("ISRTaskTab");
					fngCheckError(" after loading ");
					/*if (fngCheckElementDisplayed("EditWOBtn", 5)) {
						fngClick_OR("EditWOBtn");
					}*/
					//23/Nov/2020 end Code Change
					if ( (fnpCheckElementDisplayedImmediately_NOR(certOpenIconXpath) & (!(fnpCheckElementDisplayedImmediately_NOR(certInnerTableHeaderXpathForAI))) )  ) {				
						fnpClick_NOR(certOpenIconXpath);
					}

					
				}

/*				if (waitCount > 60) {

					fnpMymsg("Script waited for approx. 30 min to become 'AI_COMPLETED' state of PerformAudit but it is still showing '" + performAuditStatus + "' .");
					throw new Exception("Script waited for approx. 30 min to become 'AI_COMPLETED' state of PerformAudit but it is still showing '" + performAuditStatus + "' .");

				}
*/
				
				
/*				if (waitCount > 4) {

					fnpMymsg("Script waited for approx. 2 min to become 'AI_COMPLETED' state of PerformAudit but it is still showing '" + performAuditStatus + "' .");
					throw new Exception("Script waited for approx. 2 min to become 'AI_COMPLETED' state of PerformAudit but it is still showing '" + performAuditStatus + "' .");

				}
				
				*/
				
				
			}
		
			//}
			//22/Nov/2020 start Temp		
			driver.close();
			fnpLaunchBrowserAndLogin();
			fnpSearchWorkOrderOnly(workOrderNo);
			fnpClick_OR("ISRTaskTab");
			if (fnpCheckElementDisplayed("EditWOBtn", 5)) {
				fnpClick_OR("EditWOBtn");
			}
			
			certOpenIconXpath = OR.getProperty("TaskTabISR_CertAudit_OpenIconPart1") + WOISOTaskType_CertificationAudit+ OR.getProperty("TaskTabISR_CertAudit_OpenIconPart2");
			if ( (fnpCheckElementDisplayedImmediately_NOR(certOpenIconXpath) & (!(fnpCheckElementDisplayedImmediately_NOR(certInnerTableHeaderXpathForAI))) )  ) {				
				fnpClick_NOR(certOpenIconXpath);
			}
			
			taskAINameIndex = fnpFindColumnIndex("TaskTabTable_HeaderRow", WOISOTaskTab_TaskAISummaryTableColName_TaskAIName);
			certRow = fnpFindRowContainsName("TaskTabTable", WOISOTaskTab_Certification_MainTaskAIName, taskAINameIndex);
			certInnerTableDataXpathForAI  = OR.getProperty("ISRTaskInnerTableForActionItemsTableData_part1") + (certRow - 1)
					+ OR.getProperty("ISRTaskInnerTableForActionItemsTableData_part2");
	
			certInnerTableHeaderXpathForAI = OR.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part1") + (certRow - 1)
					+ OR.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part2");
			
			//22/Nov/2020 End Temp
			
			
			 aiTypeInnerTableIndex = fnpFindColumnIndex_NOR(certInnerTableHeaderXpathForAI, "AI Type");
			 aiNumberInnerTableIndex = fnpFindColumnIndex_NOR(certInnerTableHeaderXpathForAI, "AI Number");
			
			
			
			
			
			
			int technicalReviewAIRowNo = fngFindRowContainsName_NOR_ForAI(certInnerTableDataXpathForAI, WOISOTaskTab_TechnicalReviewAIName, aiTypeInnerTableIndex);
			
			if (technicalReviewAIRowNo>0) {
				fnpMymsg("TechnicalReview action item has been generated for Cert Audit task.");
			} else {
				fnpMymsg("TechnicalReview action item has NOT been generated for Cert Audit task.");
				throw new Exception("TechnicalReview action item has NOT been generated for Cert Audit task.");
			}
			
			String technicalReviewAINo = fnpFetchFromTable_NOR(certInnerTableDataXpathForAI, technicalReviewAIRowNo, aiNumberInnerTableIndex);
			fnpMymsg("TechnicalReview action item no.   is ---" + technicalReviewAINo);
			
			
			

			String technicalReviewStatus = fnpFetchFromTable_NOR(certInnerTableDataXpathForAI, technicalReviewAIRowNo, statusInnerTableColIndex);
			
			if (!(technicalReviewStatus.equalsIgnoreCase("AI_CREATED"))) {
				
				fnpMymsg("Current Status of TechnicalReview action item is NOT 'AI_CREATED'  b/c its current status is --"+technicalReviewStatus);
				throw new Exception ("Current Status of TechnicalReview action item is NOT 'AI_CREATED'  b/c its current status is --"+technicalReviewStatus);
			} else {
				fnpMymsg("Current Status of TechnicalReview action item is AI_CREATED.");
			}
			
			
			/*******************New Changes 21 Jan 2021*********************/
			fngSwitchUserToProcessTechnicalReviewAI();
			fngSearchWO_and_ReachToTaskTab_OpenCertExpandIcon_ISR();
			/**************************************************************/
			fngProcessAI_ISR_TaskTab_1(certInnerTableDataXpathForAI,certInnerTableHeaderXpathForAI,WOISOTaskTab_TechnicalReviewAIName, "Completed","SaveButton__OnConsolidatedScreen");
			

			fnpVerifyAIStatusInISR(WOISOTaskType_CertificationAudit,WOISOTaskTab_Certification_MainTaskAIName,WOISOTaskTab_TechnicalReviewAIName,"Completed");
			

		
			
			
			if ((classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.Cert_StandardsTestCaseName))) {
				
						certRow = fnpFindRowContainsName("TaskTabTable", WOISOTaskTab_Certification_MainTaskAIName, taskAINameIndex);
						certOpenIconXpath = OR_SUS.getProperty("TaskTabISR_CertAudit_OpenIconPart1") + WOISOTaskType_CertificationAudit+ OR_SUS.getProperty("TaskTabISR_CertAudit_OpenIconPart2");
						if ( (fnpCheckElementDisplayedImmediately_NOR(certOpenIconXpath) & (!(fnpCheckElementDisplayedImmediately_NOR(certInnerTableHeaderXpathForAI))) )  ) {				
							fnpClick_NOR(certOpenIconXpath);
						}
												
						auditNoInCertAuditXpath = OR_SUS.getProperty("AuditNoAtTaskTab_part1") + (certRow - 1) + OR_SUS.getProperty("AuditNoAtTaskTab_part2");		
						auditNo = fnpGetText_NOR(auditNoInCertAuditXpath);
						fnpDisplayingMessageInFrame("Going to click this Audit no. in Cert Audit  --" + auditNo, 5);
		
						fnpGetORObjectX___NOR(auditNoInCertAuditXpath).click();
						
						/*********If we click this button it will automatically takes you to car tab  **********/
						fnpClick_OR("EditCarButtonOnViewAuditScreen");
						
						
						
						fnpProcessedCAR();

						
						
						
						
						
						
						fnpSearchWorkOrderOnly(workOrderNo);								
						if (fnpCheckElementDisplayedImmediately("EditWOBtn")) {
							fnpClick_OR("EditWOBtn");
						}
						fnpClick_OR("ISRTaskTab");								
						if ( (fnpCheckElementDisplayedImmediately_NOR(certOpenIconXpath) & (!fnpCheckElementDisplayedImmediately_NOR(visitNoInCertAuditXpath)) )  ) {				
							fnpClick_NOR(certOpenIconXpath);
						}
						
				
			}
			
			
			
			fnpRecordChangeAI(WOISOTaskTab_Certification_MainTaskAIName,WOISOTaskType_CertificationAudit);

			//startCode 26Nov2020
			int count=2;
			boolean certAuditStatusNotCompleted=false;
			for(int i=1;i<=2;i++){
			
			fnpRunOasisJob("Oasis_RunIQAULN_btn");
			
			fngLaunchBrowserAndLogin();
			fnpSearchWorkOrderOnly(workOrderNo);
			

			if (fnpCheckElementDisplayedImmediately("EditWOBtn")) {
				fnpClick_OR("EditWOBtn");
				fnpMymsg("@  ---Here EditWOBtn is visible , so going to click it");

			}else{
				fnpMymsg("@  ---Here EditWOBtn is NOT visible , so ignore  it");
			}
			
		
			
			
			fnpClick_OR("ISRTaskTab");
			
			
			if ((classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.Sus_SingleTestCaseName))) { //ISO9001_SingleTestCaseName
				
					//taskAINameIndex = fnpFindColumnIndex("TaskTabTable_HeaderRow", WOISOTaskTab_TaskAISummaryTableColName_TaskAIName);
					int TaskRowNo = fnpFindRowContainsName("TaskTabTable", WOISOTaskTab_Certification_MainTaskAIName, taskAINameIndex);
					String innerTableDataXpathForAI = OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableData_part1") + (TaskRowNo - 1)
								+ OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableData_part2");
					String innerTableHeaderXpathForAI = OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part1") + (TaskRowNo - 1)
								+ OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part2");
						
				
					 String openIconXpath = OR_SUS.getProperty("TaskTabISR_Task_OpenIconPart1") + WOISOTaskType_CertificationAudit+ OR_SUS.getProperty("TaskTabISR_Task_OpenIconPart2");
					
					
					
					if ( (fnpCheckElementDisplayedImmediately_NOR(openIconXpath) & (!fnpCheckElementDisplayedImmediately_NOR(visitNoInCertAuditXpath)) )  ) {				
						fnpClick_NOR(openIconXpath);
					}
					
					
					certRow=TaskRowNo;
		
					
					taskAINameIndex = fnpFindColumnIndex("TaskTabTable_HeaderRow", WOISOTaskTab_TaskAISummaryTableColName_TaskAIName);
					//certRow = fnpFindRowContainsName("TaskTabTable", "Certification", taskAINameIndex);
					certRow = fnpFindRowContainsName("TaskTabTable", WOISOTaskTab_Certification_MainTaskAIName, taskAINameIndex);
					
					
					certExpansionPanelXpath = OR_SUS.getProperty("ISRTaskExpansionPanelGrid_part1") + (certRow - 1) + OR_SUS.getProperty("ISRTaskExpansionPanelGrid_part2");
					
					taskAINameIndex = fnpFindColumnIndex("TaskTabTable_HeaderRow", WOISOTaskTab_TaskAISummaryTableColName_TaskAIName);
					
					//certRow = fnpFindRowContainsName("TaskTabTable", "Certification", taskAINameIndex);
					certRow = fnpFindRowContainsName("TaskTabTable", WOISOTaskTab_Certification_MainTaskAIName, taskAINameIndex);
					
					certInnerTableDataXpathForAI = OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableData_part1") + (certRow - 1)
							+ OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableData_part2");
					
					certInnerTableHeaderXpathForAI = OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part1") + (certRow - 1)
							+ OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part2");
					
					
					 CertAuditStatusXpath = certExpansionPanelXpath + "//tr/td/label[text()='Audit Status']/../following-sibling::td/label";
					 CertAuditStatus = fnpGetText_NOR(CertAuditStatusXpath);
					/* if (CertAuditStatus.equalsIgnoreCase("COMPLETED")) {*///26Nov2020 condition added
						if (CertAuditStatus.equalsIgnoreCase("COMPLETED")) {
							fnpMymsg("Cert Audit status is  become 'COMPLETED'  as its value is ---'"+CertAuditStatus +"' and expected is --'"+"COMPLETED" +"' .");
							i=count;
						} else {
							fnpMymsg("Roun: "+i+">>>>>>>>>>>>>>>>>>Cert Audit status is NOT become 'COMPLETED'  as its value is ---"+CertAuditStatus+"' and expected is --'"+"COMPLETED" +"' .");
							certAuditStatusNotCompleted=true;
							//fnpMymsg("Cert Audit status is NOT become 'COMPLETED'  as its value is ---"+CertAuditStatus+"' and expected is --'"+"COMPLETED" +"' .");
							//throw new Exception("Cert Audit status is NOT become 'COMPLETED'  as its value is ---"+CertAuditStatus+"' and expected is --'"+"COMPLETED" +"' .");
						}
					// }
					 /*if(certAuditStatusNotCompleted){
						 fnpMymsg("Cert Audit status is NOT become 'COMPLETED'  as its value is ---"+CertAuditStatus+"' and expected is --'"+"COMPLETED" +"' .");
						 throw new Exception("Cert Audit status is NOT become 'COMPLETED'  as its value is ---"+CertAuditStatus+"' and expected is --'"+"COMPLETED" +"' .");
					 }*/
		
			}
			}//count loop close
			if(certAuditStatusNotCompleted){
				 fnpMymsg("Cert Audit status is NOT become 'COMPLETED'  as its value is ---"+CertAuditStatus+"' and expected is --'"+"COMPLETED" +"' .");
				 throw new Exception("Cert Audit status is NOT become 'COMPLETED'  as its value is ---"+CertAuditStatus+"' and expected is --'"+"COMPLETED" +"' .");
			 }
			
			
			
		//	fnpClick_OR("WOISR_ProcessWorkFlowBtn");
			
			
			fngRunJob("ProcessWorkflowQueueQJob");
			fngClick_OR("ISRTaskTab");
			
			
			 String openIconXpath = OR_SUS.getProperty("TaskTabISR_Task_OpenIconPart1") + WOISOTaskType_CertificationAudit+ OR_SUS.getProperty("TaskTabISR_Task_OpenIconPart2");
			//if  (fnpCheckElementDisplayedImmediately_NOR(openIconXpath)  ) {	
			if  (fnpCheckElementDisplayed_NOR(openIconXpath, 20) ) {
				fnpClick_NOR(openIconXpath);
			}
			
			 waitCount=0;
			 int certDecAIRowNo=0;
			int TaskRowNo;
			String innerTableDataXpathForAI;
			String innerTableHeaderXpathForAI;
			//String openIconXpath;
			while (true) {
				
				waitCount++;
				certDecAIRowNo = fngFindRowContainsName_NOR_ForAI(certInnerTableDataXpathForAI, "ecision", aiTypeInnerTableIndex);
				
				if (certDecAIRowNo>0) {
					fnpMymsg("Cert Decision action item has been generated for Cert Audit task.");
					break;
				} else {
				//	fnpMymsg("Cert Decision action item has NOT been generated for Cert Audit task.");
				//	throw new Exception("Cert Decision action item has NOT been generated for Cert Audit task.");
				

					fngCheckError(" after loading ");
					fnpMymsg(" we are in  waiting loop for waiting cert decision action item generated.  ----" + waitCount);
					fnpDisplayingMessageInFrame("Now  we are in  waiting loop for waiting cert decision action item generated.----" + ((double)waitCount/2)+" approx. min.", 8);
					Thread.sleep(1000 * 20 * 1);
					fngCheckError(" after loading ");
					driver.navigate().refresh();
					
					fngCheckError(" after loading ");					
					fnpClick_OR("ISRTaskTab");
					fngCheckError(" after loading ");
					
					//certRow = fnpFindRowContainsName("TaskTabTable", WOISOTaskTab_Certification_MainTaskAIName, taskAINameIndex);
					TaskRowNo = fnpFindRowContainsName("TaskTabTable", WOISOTaskTab_Certification_MainTaskAIName, taskAINameIndex);
					innerTableDataXpathForAI = OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableData_part1") + (TaskRowNo - 1)
								+ OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableData_part2");
					innerTableHeaderXpathForAI = OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part1") + (TaskRowNo - 1)
								+ OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part2");
						
				
					 openIconXpath = OR_SUS.getProperty("TaskTabISR_Task_OpenIconPart1") + WOISOTaskType_CertificationAudit+ OR_SUS.getProperty("TaskTabISR_Task_OpenIconPart2");
					
					if ( (fnpCheckElementDisplayedImmediately_NOR(openIconXpath) & (!(fnpCheckElementDisplayedImmediately_NOR(innerTableDataXpathForAI))) )  ) {				
						fnpClick_NOR(openIconXpath);
					}
					
				}

				if (waitCount > 60) {

					fnpMymsg("Script waited for approx. 30 min to generate Cert Decision action item but it is still not generated .");
					throw new Exception("Script waited for approx. 30 min to generate Cert Decision action item but it is still not generated .");

				}

			}



			
			
			String certDecAINo = fnpFetchFromTable_NOR(certInnerTableDataXpathForAI, certDecAIRowNo, aiNumberInnerTableIndex);
			fnpMymsg("Cert Decision action item no.   is ---" + certDecAINo);
			
			

			fngProcessAI_ISR_TaskTab_1(certInnerTableDataXpathForAI,certInnerTableHeaderXpathForAI,"CertDecision", "Completed","SaveBtnOnPerformCertDecisionAI");

			fnpVerifyAIStatusInISR(WOISOTaskType_CertificationAudit,WOISOTaskTab_Certification_MainTaskAIName,"CertDecision","Completed");
			
			
			
			taskAINameIndex = fngFindColumnIndex("TaskTabTable_HeaderRow", WOISOTaskTab_TaskAISummaryTableColName_TaskAIName);
			TaskRowNo = fnpFindRowContainsName("TaskTabTable", WOISOTaskTab_Certification_MainTaskAIName, taskAINameIndex);
			innerTableDataXpathForAI = OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableData_part1") + (TaskRowNo - 1)
						+ OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableData_part2");
			innerTableHeaderXpathForAI = OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part1") + (TaskRowNo - 1)
						+ OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part2");
						
			openIconXpath = OR_SUS.getProperty("TaskTabISR_Task_OpenIconPart1") + WOISOTaskType_CertificationAudit+ OR_SUS.getProperty("TaskTabISR_Task_OpenIconPart2");
			
			if ( (fnpCheckElementDisplayedImmediately_NOR(openIconXpath)  )  ) {				
				fnpClick_NOR(openIconXpath);
				fnpMymsg("Clicked  open icon");
			}
			
			
			aiTypeInnerTableIndex = fnpFindColumnIndex_NOR(innerTableHeaderXpathForAI, "AI Type");
			aiNumberInnerTableIndex = fnpFindColumnIndex_NOR(innerTableHeaderXpathForAI, "AI Number");
			int certIssueAIRowNo = fngFindRowContainsName_NOR_ForAI(innerTableDataXpathForAI, "CertIssue", aiTypeInnerTableIndex);
			
			if (certIssueAIRowNo>0) {
				fnpMymsg("Cert Issue action item has been generated for Cert Audit task.");
				
			} else {
				fnpMymsg("Cert Issue action item has NOT been generated for Cert Audit task.");
				throw new Exception("Cert Issue action item has NOT been generated for Cert Audit task.");
			}
			
			
			
			
			fngProcessAI_ISR_TaskTab_1(innerTableDataXpathForAI,innerTableHeaderXpathForAI,"CertIssue", "Completed","SaveButton__OnConsolidatedScreen");

			fnpVerifyAIStatusInISR(WOISOTaskType_CertificationAudit,WOISOTaskTab_Certification_MainTaskAIName,"CertIssue","Completed");
			
			//new changes on 23nd October,2020
			fnpClick_OR("ISR_CorrespondenceTab");
			fnpWaitForVisible("ISR_Corresondence_table_header");
			int initialCorrespondenceCount = fnpCountRowsInTable("ISR_Corresondence_table_data");
			fnpMymsg("***********************Initial Count of rows in Correspondence tab is --"+initialCorrespondenceCount);
			fngClick_OR("ISRTaskTab");
			openIconXpath = OR_SUS.getProperty("TaskTabISR_Task_OpenIconPart1") + WOISOTaskType_CertificationAudit+ OR_SUS.getProperty("TaskTabISR_Task_OpenIconPart2");
			if ( (fnpCheckElementDisplayedImmediately_NOR(openIconXpath)  )  ) {				
				fnpClick_NOR(openIconXpath);
				fnpMymsg("Clicked  open icon");
			}
			
			taskAINameIndex = fngFindColumnIndex("TaskTabTable_HeaderRow", WOISOTaskTab_TaskAISummaryTableColName_TaskAIName);
			TaskRowNo = fnpFindRowContainsName("TaskTabTable", WOISOTaskTab_Certification_MainTaskAIName, taskAINameIndex);
			innerTableDataXpathForAI = OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableData_part1") + (TaskRowNo - 1)
						+ OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableData_part2");
			innerTableHeaderXpathForAI = OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part1") + (TaskRowNo - 1)
						+ OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part2");

		//	openIconXpath = OR_SUS.getProperty("TaskTabISR_Task_OpenIconPart1") + WOISOTaskType_CertificationAudit+ OR_SUS.getProperty("TaskTabISR_Task_OpenIconPart2");
			
			
			
			
			
			
			
			 if((classNameText.equalsIgnoreCase(Sus_SingleTestCaseName))){
					
					int ActionItemColIndex = fnpFindColumnIndex_NOR(innerTableHeaderXpathForAI, "AI Number");
					int itemDescColIndex = fnpFindColumnIndex_NOR(innerTableHeaderXpathForAI, "AI Name");
					
					int actionItemInfoRowNo = fnpFindRow_NOR(innerTableDataXpathForAI, "CertIssue", itemDescColIndex);
					String actionItemNo = fnpFetchFromTable_NOR(innerTableDataXpathForAI, actionItemInfoRowNo, ActionItemColIndex);
					int start = 1;
					while (actionItemNo.isEmpty()) {
						Thread.sleep(1000);
						actionItemNo = fnpFetchFromTable_NOR(innerTableDataXpathForAI, actionItemInfoRowNo, ActionItemColIndex);

						if (start > 50) {
							break;
						}
					}
					Thread.sleep(500);

					
					fnpClickALinkInATable(actionItemNo);
					
					fnpMymsg("Clicked on  Action Item '" + "CertIssue" + "' in Table having no. is '" + actionItemNo + "' .");
					fnpLoading_wait();
					
					
					
					fngClick_OR("EmailCertBtn_id");			
					fngPFList("ISR_EmailTemplate_PFList", "ISR_EmailTemplate_PFListOptions", hashXlData.get("Email_Template"));
					fngClick_OR("ISR_EmailTemplateContinueBtn");
					fngWaitForVisible("ISR_EmailToTxt");
					fngGetORObjectX("ISR_EmailFromTxt").clear();
					fngType("OR_SUS","ISR_EmailFromTxt",hashXlData.get("From_emailCorrespondence") );
					fngGetORObjectX("ISR_EmailToTxt").clear();
					fngType("OR_SUS","ISR_EmailToTxt",hashXlData.get("To_emailCorrespondence") );
					fngClickInDialog_OR("ISR_CorresEmailSendBtn");
					
					
					fngClick_OR("WorkOrderNoInViewActionItem_id");	
					fngClick_OR("ISR_CorrespondenceTab");
					
					
				
				
					fngWaitForVisible("ISR_Corresondence_table_header");
					int rowCount = fngCountRowsInTable("ISR_Corresondence_table_data");

					//if (rowCount == 1) {
					//if (rowCount == (initialCorrespondenceCount_assumed+ 1)) {
						if (rowCount == (initialCorrespondenceCount+ 1)) {						
						fnpMymsg(" Email has been generated on clickng 'Email Cert' button in cert issue ai because row count in correpondence tab is--"+rowCount);

					} else {
						fnpMymsg(" Email has NOT been generated on clickng 'Email Cert' button in cert issue ai because row count in correpondence tab is--"+rowCount);
						throw new Exception(" Email has NOT been generated on clickng 'Email Cert' button in cert issue ai because row count in correpondence tab is--"+rowCount);

					}

					
					fnpClick_OR("EditWOBtn");
					
					if (fngCheckElementPresenceImmediately("ISR_Corres_MessageIcon_id")) {
						fnpMymsg(" Option Column has message Icon present");

					} else {
						fnpMymsg(" Option Column has NOT message Icon present");
						throw new Exception(" Option Column has NOT message Icon present.");
					}

					
					
					fngClick_OR("ISR_Correspondence_AddBtn_id");
					
					
					fngPFList("ISR_CorresTab_EmailTemplate_PFList", "ISR_CorresTab_EmailTemplate_PFListOptions", hashXlData.get("Email_Template"));
					fngClick_OR("ISR_CorresTab_EmailTemplateContinueBtn");
					fngWaitForVisible("ISR_CorresTab_EmailToTxt");
					fngGetORObjectX("ISR_CorresTab_EmailFromTxt").clear();
					fngType("OR_SUS","ISR_CorresTab_EmailFromTxt",hashXlData.get("From_emailCorrespondence") );
					fngGetORObjectX("ISR_CorresTab_EmailToTxt").clear();
					fngType("OR_SUS","ISR_CorresTab_EmailToTxt",hashXlData.get("To_emailCorrespondence") );
					fngClickInDialog_OR("ISR_CorresTab_CorresEmailSendBtn");
					
					
					fngLoading_waitInsideDialogBox();
					fnpLoading_wait();
					
					rowCount = fngCountRowsInTable("ISR_Corresondence_table_data");

					//if (rowCount == 2) {
					if (rowCount == (initialCorrespondenceCount+ 2)) {
						fnpMymsg(" 2nd Email has been generated using 'Add' button in correspondence tab because row count in correpondence tab is--"+rowCount);

					} else {
						fnpMymsg("  2nd Email has NOT been generated using 'Add' button in correspondence tab because row count in correpondence tab is--"+rowCount);
						throw new Exception("  2nd Email has  NOT been generated using 'Add' button in correspondence tab because row count in correpondence tab is--"+rowCount);

					}
					
					
			 }

			
			/* if((classNameText.equalsIgnoreCase(Sus_SingleTestCaseName))){ 
			 
				fnpMymsg("Now we are going to check FRS Report for Desk audit task");			 
				fnpSearchWorkOrderOnly(workOrderNo);
				if (fnpCheckElementDisplayedImmediately("EditWOBtn")) {
					fnpClick_OR("EditWOBtn");
					fnpMymsg("@  ---Here EditWOBtn is visible , so going to click it");
	
				}else{
					fnpMymsg("@  ---Here EditWOBtn is NOT visible , so ignore  it");
				}
				fnpClick_OR("ISRTaskTab");
				String deskOpenIconXpath = OR_SUS.getProperty("TaskTabISR_DeskAudit_OpenIconPart1") + WOISOTaskName_DeskAudit+ OR_SUS.getProperty("TaskTabISR_DeskAudit_OpenIconPart2");
				taskAINameIndex = fnpFindColumnIndex("TaskTabTable_HeaderRow", WOISOTaskTab_TaskAISummaryTableColName_TaskAIName);
				int deskRow = fnpFindRow("TaskTabTable", WOISOTaskName_DeskAudit, taskAINameIndex);
				String visitNoInDeskAuditXpath = OR_SUS.getProperty("VisitNoAtTaskTab_part1") + (deskRow - 1) + OR_SUS.getProperty("VisitNoAtTaskTab_part2");
				fnpVerifyFRSReportOnViewVistPage_VerifyAtEnd_inChrome(deskOpenIconXpath,visitNoInDeskAuditXpath);
				
	
				
				fnpMymsg("Now we are going to check FRS Report for Cert audit task");	
				fnpSearchWorkOrderOnly(workOrderNo);
				if (fnpCheckElementDisplayedImmediately("EditWOBtn")) {
					fnpClick_OR("EditWOBtn");
					fnpMymsg("@  ---Here EditWOBtn is visible , so going to click it");
	
				}else{
					fnpMymsg("@  ---Here EditWOBtn is NOT visible , so ignore  it");
				}
				fnpClick_OR("ISRTaskTab");			
				certOpenIconXpath = OR_SUS.getProperty("TaskTabISR_CertAudit_OpenIconPart1") + WOISOTaskType_CertificationAudit+ OR_SUS.getProperty("TaskTabISR_CertAudit_OpenIconPart2");
				certRow = fnpFindRowContainsName("TaskTabTable", WOISOTaskTab_Certification_MainTaskAIName, taskAINameIndex);
				visitNoInCertAuditXpath = OR_SUS.getProperty("VisitNoAtTaskTab_part1") + (certRow - 1) + OR_SUS.getProperty("VisitNoAtTaskTab_part2");
				fnpVerifyFRSReportOnViewVistPage_VerifyAtEnd_inChrome(certOpenIconXpath,visitNoInCertAuditXpath) ;
			
			
			
			 }*/
			
			
			
			
			
			 /*if((classNameText.equalsIgnoreCase(Sus_SingleTestCaseName))){
					
					fnpCommonClickTopMainMenu("Menu", "FirstRefrenceLinkJustBelowToMenu", "SearchClientLink", "ClientLkpBtnOnSearchClient_id");
					
					fnpClick_OR("ClientLkpBtnOnSearchClient_id");
				
					
					String clientValue = (String) hashXlData.get("Corp/Facility#");
					fnpSearchNSelectFirstRadioBtn(1, clientValue, 1);
					String EnteredClient = fnpWaitTillTextBoxDontHaveValue("Corp_FacilityTxtBx", "value");
					Assert.assertTrue(EnteredClient.contains(clientValue), "Client Value is not selected properly from lookup as expected is --'" + clientValue
							+ "' but actual is --'" + EnteredClient + "'.");
					fnpMymsg(" Client value is properly selected from client lookup");
				
					fnpClick_OR("MainSearchButton");
				
					String s = fnpFetchFromStSearchTable(1, 1);
					int j = 0;
					while (s.contains("No records found") & j < 15) {
						j++;
						Thread.sleep(1000);
						s = fnpFetchFromStSearchTable(1, 1);
					}

					fnpClickALinkInATable(s);
					
					fnpLoading_wait();
								
					fnpCheckFileIsDownloadedOrNotIniPulse(OR_SUS.getProperty("FRSBtnOnViewClient")) ;
					
					
			 }*/
			
			 
			 
			 
			 
			 
			 
			 
			
			fnpMymsg("===============================================================================================================");

			// }

		} catch (Throwable t) {
			//isTestPass = true;
			fnpMymsg(" **************************************************************");
			fngCommonFinalCatchBlock(t, "  CertAuditTask flow  is fail . ", "CertAuditTask");

		}

	}
	//Commented this cleanup procedure after discussion with Pradeep Singh, 
	/*@Test(priority = 6, dependsOnMethods = { "CertAuditTask" })
	public void CLEANUP_WO_AUTOMATION_DATA() throws Throwable {
		fnpMymsg(" **************************************************************");
		fnpMymsg("ECAP.CLEANUP_WO_AUTOMATION_DATA");
		try {
			if (env.equalsIgnoreCase("Test") ) {
				fngCallProcedure_DELETE_WO_AUTOMATION_DATA_SustainabilityProfile(workOrderNo);
			}else{
				//throw new SkipException( " \n\n Skipping this as this procedure is only available in TEST environment" );
			}
		} catch (Throwable t) {

			fngCommonFinalCatchBlock(t, "  CLEANUP_WO_AUTOMATION_DATA  method is failed . ", "CLEANUP_WO_AUTOMATION_DATA_Failed");
		}
	}*/

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
