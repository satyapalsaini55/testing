	package nsf.ecap.ISR_suite;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import nsf.ecap.Proposals_suite.TestSuiteBase_Proposal;
import nsf.ecap.Work_Order_suite.NewNew_InProc_Completed_No_Fac;
import nsf.ecap.Work_Order_suite.TestSuiteBase;
import nsf.ecap.config.IPULSE_CONSTANTS;
//import nsf.ecap.config.IPULSE_CONSTANTS;
import static nsf.ecap.config.IPULSE_CONSTANTS.*;
import nsf.ecap.util.ErrorUtil;
import nsf.ecap.util.TestUtil;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
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

public class ISO9001_Single extends TestSuiteBase_ISR_suite {


	
	String deskAuditTaskTab_expectedBillCode = "";
	double deskAuditTaskTab_expectedQuantity = 0;
	double deskAuditTaskTab_expectedAuditDays = 0;
	
	String certAuditTaskTab_expectedBillCode = "";
	double certAuditTaskTab_expectedQuantity = 0;
	double certAuditTaskTab_expectedAuditDays = 0;
	
	String msg;
	
	// Runmode of test case in a suite
	@BeforeTest
	@Parameters({ "className" })
	public void checkTestSkip(String className) throws Throwable {

		try {

			if (className.isEmpty()) {
				className = this.getClass().getSimpleName();

			}
			//classNameText = className;
			setClassNameText( className);
			currentScriptCode = fnpMappingClassNameWithScenarioCode(classNameText).trim();

			fnpMymsg("             ");
			fnpMymsg("             ");
			fnpMymsg("             ");
			fnpMymsg("             ");
			fnpMymsg("             ");
			fnpMymsg("=========================================================================================================================================");
			fnpMymsg("Checking Runmode of testcase '" + className + "' .");

			if (!TestUtil.isTestCaseRunnable(currentSuiteXls, className)) {
				msg="Skipping Test Case" + className + " as runmode set to NO";
				fnpMymsg(msg);
				fnpMymsg("=========================================================================================================================================");
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
		fnpMymsg(" Executing  ISO CreateWOFlow");


		
		
		
		//fnpLaunchBrowser();	



	
		
		try {
			
			//fnpCallGoldenProcedure_COPY_AUDIT_TRANSACTION_SUBMIT(1576143,2851598,"TestScriptUser TestScriptUser");
			
			
			//fnpCallGoldenProcedure_COPY_AUDIT_TRANSACTION_SUBMIT(1576143,2463189,"Joe Korpan");
			
			
			if ((classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.ISO9001_SingleTestCaseName))					
					||(classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.Cert_StandardsTestCaseName))
					) {
				fnpMymsg("@  ---Before query starts for test case ---"+classNameText);
				fnpDropWOISRandDeleteSomeDataFromDB((String) hashXlData.get("Corp/Facility#"), (String) hashXlData.get("Standard"));
			//	fnpDropWOISRandDeleteSomeDataFromDB("0Z762", "QMSI9K15");
				fnpMymsg("PASSED == DB Query Executed successfully."+classNameText);
				}

			fnpCreateISRWO_excludingDBDropQuery();
			fnpMymsg(" **************************************************************");

			
		}
		catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "  Create Work Order flow  is fail . ", "CreateWorkOrderFail");

		}

	}

	
	
	
	
	@Test(priority = 2, dependsOnMethods = { "CreateWOFlow" })
	public void Standard_Facility_Tab() throws Throwable {

		try {

			fnpMymsg(" **************************************************************");
			fnpMymsg(" Executing Standard_Facility_Tab");
			
			fnpClick_OR("StandardFacilityTab_FacilityStandardInformationPanel_expandIcon");//16-10-2020
			
			callRemoveAlreadyAddedIndustryCodeUsingScript() ;
			addingIndustryCode() ;
			fnpLoading_wait();
			fnpLoading_wait();
			
			driver.navigate().refresh();
			Thread.sleep(3000);
			fnpClick_OR("EditWOBtn");
			fnpLoading_wait();
			fnpClick_OR("StandardFacilityTab_FacilityStandardInformationPanel_expandIcon");
			fnpLoading_wait();
			
			//fnpClick_OR("StandardFacilityTab_FacilityStandardInformationPanel_expandIcon");//16-10-2020

			if ((classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.Cert_StandardsTestCaseName)) ){
				if (   ((String) hashXlData.get("Standard").trim()).equalsIgnoreCase("FDSQF200")){
					fnpClick_OR("StandardFacilityTab_WorkOrderStandardInformationPanel_expandIcon");
					fnpPFList("StandardLevelPFList", "StandardLevelPFListOptions", (String) hashXlData.get("Standard_Level"));
				}
				
			}
			
			
			
			if ((classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.ISO9001_CorporateTestCaseName)) ||
					(classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.ISO9001_CampusTestCaseName)) ){
				
				if (   ((String) hashXlData.get("WOType").trim()).equalsIgnoreCase("Associated Facility")) {
					// in this case , no scope field is present
				}else{
					//fnpClick_OR("StandardFacilityTab_WorkOrderStandardInformationPanel_expandIcon");
					if (fnpCheckElementDisplayedImmediately("StandardFacilityTab_WorkOrderStandardInformationPanel_expandIcon")) {
						fnpClick_OR("StandardFacilityTab_WorkOrderStandardInformationPanel_expandIcon");
					} 
					fnpType("OR", "ScopeTxtBx", (String) hashXlData.get("Scope"));
					fnpLoading_wait();
					//fnpWorkAroundToClickbottomFooter();
					
				}
	
			}
			
			
/*			fnpDoubleClick(fnpGetORObjectX("ScopeofthefacilityLabel"));
			fnpType("OR", "ScopeofthefacilityTxtBx", (String) hashXlData.get("Scope_of_the_facility"));
		*/
			
			
			

			
/*			
			Thread.sleep(1000*30);
			driver.findElement(By.xpath(OR.getProperty("ScopeofthefacilityTxtBx"))).clear();
			Thread.sleep(1000*30);
			fnpDoubleClick(fnpGetORObjectX("ScopeofthefacilityLabel"));
			Thread.sleep(1000*10);
			driver.findElement(By.xpath(OR.getProperty("ScopeofthefacilityTxtBx"))).sendKeys((String) hashXlData.get("Scope_of_the_facility").trim());
			Thread.sleep(1000*30);
			fnpDoubleClick(fnpGetORObjectX("ScopeofthefacilityLabel"));
			
		
			
			Thread.sleep(1000*30);
			driver.findElement(By.xpath(OR.getProperty("Exclusion_of_the_facilityTxtBx"))).clear();
			Thread.sleep(1000*30);
			fnpDoubleClick(fnpGetORObjectX("Exclusion_of_the_facilityLabel"));
			Thread.sleep(1000*10);
			driver.findElement(By.xpath(OR.getProperty("Exclusion_of_the_facilityTxtBx"))).sendKeys((String) hashXlData.get("Exclusion_of_the_facility").trim());
			Thread.sleep(1000*30);
			fnpDoubleClick(fnpGetORObjectX("Exclusion_of_the_facilityLabel"));
			
			
			*/
			
			
			
			
			
			//Thread.sleep(10000); // Wait added because of IPM-15746
			driver.findElement(By.xpath(OR.getProperty("ScopeofthefacilityTxtBx"))).clear();
			Thread.sleep(1000);
			fnpDoubleClick(fnpGetORObjectX("ScopeofthefacilityLabel"));
			Thread.sleep(1000);
			driver.findElement(By.xpath(OR.getProperty("ScopeofthefacilityTxtBx"))).sendKeys((String) hashXlData.get("Scope_of_the_facility").trim()+fns_Return_RequriedDateTime("hh:mm", 0,0,0,0,0));
			Thread.sleep(1000);
			fnpDoubleClick(fnpGetORObjectX("ScopeofthefacilityLabel"));
			
		
			

			driver.findElement(By.xpath(OR.getProperty("Exclusion_of_the_facilityTxtBx"))).clear();
			Thread.sleep(1000);
			fnpDoubleClick(fnpGetORObjectX("Exclusion_of_the_facilityLabel"));
			Thread.sleep(1000);
			driver.findElement(By.xpath(OR.getProperty("Exclusion_of_the_facilityTxtBx"))).sendKeys((String) hashXlData.get("Exclusion_of_the_facility").trim()+fns_Return_RequriedDateTime("hh:mm", 0,0,0,0,0));
			Thread.sleep(1000);
			fnpDoubleClick(fnpGetORObjectX("Exclusion_of_the_facilityLabel"));
			
			
			
			
			
			
			
			
			
			
			//This field is only coming in ISO9001_SingleWo_InProcess
			if ((classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.ISO9001_SingleTestCaseName))
					||(classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.ISO9001_CorporateTestCaseName))
					||(classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.ISO9001_CampusTestCaseName))
					) {
				fnpPFList("FacilityDesignResponsiblePFList", "FacilityDesignResponsiblePFListOptions", (String) hashXlData.get("Facility_Design_Responsible"));
			}
			
	
			fnpLoading_wait();
			
			//below is workaround, sometime this facility text box value gets cleared while saving
		//	fnpDoubleClick(fnpGetORObjectX("Exclusion_of_the_facilityLabel"));
			
			
/*
			if (!(classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.Cert_StandardsTestCaseName)) )
			{
				
				if ((classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.ISO9001_SingleTestCaseName)) ){
					// not clicking save because after removing all added industry codes (first statment of this method) , it throws error on clicking Save button as it 
				}else{
					fnpClick_OR("ISRSFTabSaveBtn");
				}
			} 
			
			*/
			//Thread.sleep(10000); // Wait added because of IPM-15746
			if (!(classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.Cert_StandardsTestCaseName)) )
			{
					fnpClick_OR("ISRSFTabSaveBtn");

			} 
			
			/*boolean ISRIntegratedConfirmationNOBtn_Displayed = false;
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			for(int i=0; i<30; i++){
				try{
					if(driver.findElements(By.xpath(OR.getProperty("ISRIntegratedConfirmationNOBtn"))).size()>0){
						ISRIntegratedConfirmationNOBtn_Displayed = true;
						break;
					}else{
						Thread.sleep(1000);
					}
				}catch(Throwable t){
					Thread.sleep(1000);
				}
			}		
			driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);
			if(ISRIntegratedConfirmationNOBtn_Displayed==false){
				fnpClick_OR("ISRSFTabSaveBtn");
			}*/
			
			if(ISR_IntegratedWOComing){
				//new changes -- IPM-15605
				fnpWaitTillVisiblityOfElementNClickable_OR("ISRIntegratedConfirmationNOBtn");
				fnpWaitForVisible("ISRIntegratedConfirmationNOBtn");
				fnpClick_OR("ISRIntegratedConfirmationNOBtn");
			}
			
			
			//If Effective Employee Count is already selected 'Yes' then Click No
			if ((classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.ISO9001_SingleTestCaseName)) )
			{

				//String xpath = ".//td/div/div[contains(@class,'ui-state-default ui-state-active')]/../../following-sibling::td[1]/label[contains(@for,':isrynId:')]";
				String xpath = ".//div[contains(@class,'ui-state-default ui-state-active')]/../../label[contains(@for,':isrynId:')]";
				
				String text=fnpGetText_NOR(xpath);
				
				if (text.equalsIgnoreCase("Yes")) {
					fnpClick_NOR(".//td/label[contains(@for,':isrynId:')][text()='No']");
				} else {
					//nothing to do for now
				}
			} 
			

			
					
					
					
					
					
			
					
			
			
			
			fnpClick_OR("StandardFacilityTab_FacilityInformationPanel_expandIcon");
			fnpPFList("SizeUnitPFList", "SizeUnitPFListOptions", (String) hashXlData.get("Facility_Size"));
			//fnpLoading_wait();
			int rowbeforeAddingInFacilitySizeTable = fnpCountRowsInTable("FacilitySizeTable");
			fnpMymsg("Before  deleting (if present)  Facility Size --no. of rows present are -- " + rowbeforeAddingInFacilitySizeTable);

			
			fnpDeleteFacilitySizeWhichIsAlreadyAdded();
			fnpDeleteFacilityEmployeeCountWhichIsAlreadyAdded() ;
			
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
				//String typeXpath = OR.getProperty("FacilitySizeTypePFListPart1") + (rowbeforeAddingInFacilitySizeTable + i) + OR.getProperty("FacilitySizeTypePFListPart2");
				String typeXpath = OR.getProperty("FacilitySizeTypePFListPart1") +  i + OR.getProperty("FacilitySizeTypePFListPart2");
				fnpPFListModify_NOR(typeXpath, noOfFacilityTypeArray[i]);
				//String amountXpath = OR.getProperty("FacilitySizeAmountPart1") + (rowbeforeAddingInFacilitySizeTable + i) + OR.getProperty("FacilitySizeAmountPart2");
				String amountXpath = OR.getProperty("FacilitySizeAmountPart1") +  i + OR.getProperty("FacilitySizeAmountPart2");
				//fnpType("", amountXpath, amountArray[i]);
				driver.findElement(By.xpath(amountXpath)).sendKeys("");
				driver.findElement(By.xpath(amountXpath)).sendKeys(amountArray[i]);
				Thread.sleep(1000);
				expectedTotalAmount = expectedTotalAmount + (Integer.parseInt(amountArray[i]));
				String saveFloppyIconXpath = OR.getProperty("FacilitySizeSaveFloppyIconPart1") + (rowbeforeAddingInFacilitySizeTable + i)
						+ OR.getProperty("FacilitySizeSaveFloppyIconPart2");
				
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
			String totalAmountxpath = OR.getProperty("FacilityTotalAmountxpath_part1") + amountColIndex + OR.getProperty("FacilityTotalAmountxpath_part2");

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
					 
					 totalAmountxpath = OR.getProperty("FacilityTotalAmountxpath_part1") + amountColIndex + OR.getProperty("FacilityTotalAmountxpath_part2");
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
				String shiftXpath = OR.getProperty("FacilityEmployeeCountShiftPart1") + 0 + OR.getProperty("FacilityEmployeeCountShiftPart2");
				fnpType("", shiftXpath, shiftValueArray[i].trim());
				
				String empCountXpath = OR.getProperty("FacilityEmployeeCountPart1") + 0 + OR.getProperty("FacilityEmployeeCountPart2");
				fnpType("", empCountXpath, empCountValueArray[i].trim());
				String startXpath = OR.getProperty("FacilityEmployeeStartPart1") + 0 + OR.getProperty("FacilityEmployeeStartPart2");
				fnpType("", startXpath, startValueArray[i].trim());
				String endXpath = OR.getProperty("FacilityEmployeeEndPart1") + 0 + OR.getProperty("FacilityEmployeeEndPart2");
				fnpType("", endXpath, endValueArray[i].trim());
				
				String saveFloppyIconInFacilityEmpCountXpath = OR.getProperty("FacilityEmpCountSaveFloppyIconPart1") + 0 + OR.getProperty("FacilityEmpCountSaveFloppyIconPart2");
				
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
				chkBxLabelXpath = OR.getProperty("FacilityStandardContactsAddress_TypeChkBxLabel_part1") + contactFromCOAndPLTypeArray[i]
						+ OR.getProperty("FacilityStandardContactsAddress_TypeChkBxLabel_part2");
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
			
			
			
			//Clicking No radio button for Effective Count for this standard .....always adding this on 08-10-2018
			
			try{
				//driver.findElement(By.xpath(".//*[@id='mainForm:tabView:isrynId']/tbody/tr/td[4]/label[contains(@for,':isrynId:')][normalize-space(text())='No']"));
				driver.findElement(By.xpath(".//table[contains(@id,':isrynId')]//label[contains(@for,':isrynId:')][normalize-space(text())='No']")).click();
				Thread.sleep(1000);
			}catch(Throwable t){
				//nothing for now
			}
			
			
			
			
	
			
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
				Thread.sleep(2000);
				fnpClick_OR("AuditReportForLanguageFilterChkBx");
				Thread.sleep(2000);
				fnpClick_OR("AuditReportForLanguageFilterChkBx");

				fnpClick_OR("AuditReportForLanguage_OKLink");

				fnpClick_OR("AuditReportForLanguage");


				String AuditReport_Language = (String) hashXlData.get("AuditReport_Language").trim();
				String AuditReport_LanguageArray[] = AuditReport_Language.split(":");
				int AuditReport_LanguageArraySize = AuditReport_LanguageArray.length;
				String languageLabelXpath;


				languageLabelXpath = ".//*[@id='mainForm:tabView:facInfoAccordPnl:audRepDrm_panel']/div[2]/ul/li/label";
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

				if (((String) hashXlData.get("Standard")).equalsIgnoreCase("FD_BRC")) {
					fnpClick_OR("SAndFTab_Warehouse_onsite");
					fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
					
				}
				
				
				fnpType("OR", "DistinctManufacturingProcessesTxtBx", (String) hashXlData.get("Distinct_Manufacturing_Processes"));
				fnpType("OR", "UniqueHACCPPlansTxtBx", (String) hashXlData.get("Unique_HACCP_Plans"));
				fnpType("OR", "NoofProductionLinesTxtBx", (String) hashXlData.get("No_of_Production_Lines"));
				
				

				
				

			}

			fnpClick_OR("ISRSFTabSaveBtn");

			fnpWaitForVisibleHavingMultipleElements("TopMessage3");
			fnpMymsg("Top Message after create ----" + fnpGetText_OR("TopMessage3"));

			String SuccessfulMsg = fnpGetText_OR("TopMessage3");

			Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"),
					"Top message does not contain 'success' word, so might be data on Standard Facility tab has not been saved successfully");

			fnpMymsg("Data on Standard Facility tab has  been saved successfully");
			
/*			//IPM-14141 For timebeing I am refreshing this for this week only 04-11-2020, later after fixing this in another bug which Indrajeet will create and fix then we can remove this refereshing.
			driver.navigate().refresh();
			Thread.sleep(1000);
			fnpClick_OR("EditWOBtn");//edit button

	*/
			
/*			if ((classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.WOISOCorporateWOTillInProcessTestCaseName))) {				
				fnpClick_OR("ISRInfoTab_EditWO");
				fnpPFListModify_NOR(OR.getProperty("ISRInfoTab_WOStatusPFList"), "INREVIEW");				
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
	
	
	
	
	
	
	
	
	@Test(priority = 3, dependsOnMethods = { "Standard_Facility_Tab" })

	public void Financial_Tab_And_InReviewToInProcess() throws Throwable {

		try {
	

			
			 fnpCommonCodeOfFinancialTabOfSingleWO_CorporateWO();
			
			
			
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
						if (actualTaskDuration != expectedTaskDuration) {
							textMessage = "Audit task duration is not matched for task '" + taskName + "' as expected is --'" + expectedTaskDuration + "' but actual is --'"
									+ actualTaskDuration + "'.";
							fnpMymsg(textMessage);
							fnpDisplayingMessageInFrame(textMessage, 10);
							throw new Exception(textMessage);

						
						} else {
							textMessage = "Audit task duration is  matched successfully for task '" + taskName + "' as expected is --'" + expectedTaskDuration
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
				fnpMymsg("Expected Task type name is--" + expectedTaskTypeName);

				String expectedTaskQuantity = fnpremoveFormatting_forISR(eachQuantitySet[1]);
				fnpMymsg("Expected Task Quantity is--" + expectedTaskQuantity);

				String expectedbillCode = fnpremoveFormatting_forISR(eachQuantitySet[2]);
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

					String taskName = fnpFetchFromTable("BillCodesTable", j, taskTypeIndex);
					String billcodeInTable = fnpFetchFromTable("BillCodesTable", j, billCodesIndex);

					if ((taskName.equalsIgnoreCase(expectedTaskTypeName)) && (billcodeInTable.equalsIgnoreCase(expectedbillCode))) {
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

				}

			} 
				else{
				for (int j = 1; j <= totalrowsInBillCodesTable; j++) {

					String taskName = fnpFetchFromTable("BillCodesTable", j, taskTypeIndex);
					String billcodeInTable = fnpFetchFromTable("BillCodesTable", j, billCodesIndex);

					if ((taskName.equalsIgnoreCase(expectedTaskTypeName)) ) {
						foundThistaskWithGivenBillCode = true;
						String actualbillCode= fnpFetchFromTable("BillCodesTable", j, billCodesIndex);

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
							fnpMymsg("Expected Task type name is--" + expectedTaskTypeName);
			
							String expectedTaskQuantity = fnpremoveFormatting_forISR(eachQuantitySet[1]);
							fnpMymsg("Expected Task Quantity is--" + expectedTaskQuantity);
			
							String expectedbillCode = fnpremoveFormatting_forISR(eachQuantitySet[2]);
							fnpMymsg("Expected bill code  is--" + expectedbillCode);
			
							for (int j = 1; j <= totalrowsInBillCodesTable; j++) {
			
								String taskName = fnpFetchFromTable("BillCodesTable", j, taskTypeIndex);
								String billcodeInTable = fnpFetchFromTable("BillCodesTable", j, billCodesIndex);
			
								if ((taskName.equalsIgnoreCase(expectedTaskTypeName)) && (billcodeInTable.equalsIgnoreCase(expectedbillCode))) {
									foundThistaskWithGivenBillCode = true;
									String actualTaskQuantity = fnpFetchFromTable("BillCodesTable", j, quantityIndex);
			
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
			
							String billCodeScheduledPrice = fnpFetchFromTable("BillCodesTable", j, billCodeScheduledPriceIndex);
							billCodeScheduledPrice = billCodeScheduledPrice.replaceAll(",", "");
			
							String quantity = fnpFetchFromTable("BillCodesTable", j, quantityIndex);
							quantity = quantity.replaceAll(",", "");
			
							String totalAmount = fnpFetchFromTable("BillCodesTable", j, totalAmountIndex);
							totalAmount = totalAmount.replaceAll(",", "");
			
							double billCodeScheduledPriceDouble = Double.parseDouble(billCodeScheduledPrice.replace("$", "").trim());
							double quantityDouble = Double.parseDouble(quantity);
							double actualTotalAmountDouble = Double.parseDouble(totalAmount.replace("$", "").trim());
			
							double expectedTotalAmountDouble = billCodeScheduledPriceDouble * quantityDouble;
			
/*							if (actualTotalAmountDouble != expectedTotalAmountDouble) {
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
							*/
							
							final double THRESHOLD = .0001;
							if (Math.abs(actualTotalAmountDouble - expectedTotalAmountDouble) < THRESHOLD){
								textMessage = "PASSED ==>Total amount is  matched in Bill Code table in row no.--'" + j + "'   as actual is -- '" + actualTotalAmountDouble
										+ "'  and  expected is --'" + expectedTotalAmountDouble + "'.";
								fnpDisplayingMessageInFrame(textMessage, 5);
								fnpMymsg(textMessage);
								}	else{
									textMessage = "FAILED ==>Total amount is not matched in Bill Code table in row no.--'" + j + "'   as actual is -- '" + actualTotalAmountDouble
											+ "'  but expected is --'" + expectedTotalAmountDouble + "'.";
									fnpDisplayingMessageInFrame(textMessage, 10);
									fnpMymsg(textMessage);
									throw new Exception(textMessage);
							
								}
							
							
							
							
						}
			
						fnpMymsg("===============================================================================================================");
			
						/**********************************************************************************************************************/

			}
			
			
		if (!(classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.Non_Cert_StandardsTestCaseName)) ){	
			
			fnpClick_OR("WOISR_editBtnForEditingFinops");
			fnpWaitForVisible("WOISR_EditingFinops_SaveNCloseBtn");
			
			if((classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.Cert_StandardsTestCaseName))) {
				fnpPFList("AuditCostFlagDD", "AuditCostFlaglistOptions", "Yes");
				fnpLoading_wait();
			}else {
				fnpPFList_EditFinopsTable("App Review Fee Task","Yes");
				fnpLoading_wait();
			}

			
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
				fnpMymsg("Expected Task type name is--" + expectedTaskTypeName);

				String expectedTaskQuantity = fnpremoveFormatting_forISR(eachQuantitySet[1]);
				fnpMymsg("Expected Task Quantity is--" + expectedTaskQuantity);

				for (int j = 1; j <= totalrowsInEditAdjustedDurationTable; j++) {
					String editDurationtaskName = fnpFetchFromTable("EditAuditDurationTable", j, taskColIndex);

					String inputBxForEditDuration = OR.getProperty("AdjustedDurationInputBx_part1") + (j - 1) + OR.getProperty("AdjustedDurationInputBx_part2");

					if (editDurationtaskName.equalsIgnoreCase(expectedTaskTypeName)) {
						fnpType("", inputBxForEditDuration, expectedTaskQuantity);
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

			fnpType("OR", "AdjustmentReasonTxtBx", (String) hashXlData.get("Adjustment_reason"));
			
			if(!(classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.Cert_StandardsTestCaseName))) {
				fnpPFList_EditFinopsTable("App Review Fee Task","Yes");
				fnpLoading_wait();
			}
			

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
				fnpMymsg("Audit Task is--" + auditTaskName);

				String expectedAuditTaskDuration = fnpremoveFormatting_forISR(auditSet[1]);
				fnpMymsg("Audit Task Duration is--" + expectedAuditTaskDuration);

				for (int j = 1; j <= totalrowsInAuditDurationTable; j++) {

					String taskName = fnpFetchFromTable("AuditDurationTable", j, taskIndex);

	

					/****
					 * Fetching here value and going to use for Audit days in further steps
					 * later
					 *****/					
				if (auditTaskName.equalsIgnoreCase(WOISOTaskName_CertificationAudit)) {
					if (taskName.equalsIgnoreCase(WOISOTaskName_DeskAudit)) {
						String taskduration = fnpFetchFromTable("AuditDurationTable", j, calculatedDurationIndex);
						deskAuditTaskTab_expectedAuditDays = Double.parseDouble(taskduration);
					}
					
					if (taskName.equalsIgnoreCase(WOISOTaskName_CertificationAudit)) {
						String CertTaskduration = fnpFetchFromTable("AuditDurationTable", j, adjustedDurationColIndex);
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
							String taskduration = fnpFetchFromTable("AuditDurationTable", j, adjustedDurationColIndex);
							//deskAuditTaskTab_expectedAuditDays = Double.parseDouble(taskduration);
							if (!(taskduration.trim().equalsIgnoreCase(""))) {
								deskAuditTaskTab_expectedAuditDays = Double.parseDouble(taskduration);
							} else {
								throw new Exception("Adjusted Duration for this task '"+WOISOTaskName_DeskAudit+"' is not displayed/present in row -"+j);
							}
							
						}
						
						if (taskName.equalsIgnoreCase(WOISOTaskName_CertificationAudit)) {
							String CertTaskduration = fnpFetchFromTable("AuditDurationTable", j, calculatedDurationIndex);
							certAuditTaskTab_expectedAuditDays = Double.parseDouble(CertTaskduration);
						}
					}
				}
				
				
				
				
					/****************************************************************/

					if (taskName.equalsIgnoreCase(auditTaskName)) {
						foundThisBillCode = true;
						String taskduration = fnpFetchFromTable("AuditDurationTable", j, adjustedDurationIndex);
						double actualTaskDuration = Double.parseDouble(taskduration);
						double expectedTaskDuration = Double.parseDouble(expectedAuditTaskDuration);
						if (actualTaskDuration != expectedTaskDuration) {
							textMessage = "FAILED -->Audit task duration is not matched for task '" + taskName + "' as expected is --'" + expectedTaskDuration
									+ "' but actual is --'" + actualTaskDuration + "'.";
							fnpDisplayingMessageInFrame(textMessage, 10);
							fnpMymsg(textMessage);
							throw new Exception(textMessage);

						} else {
							textMessage = "Audit task duration is  matched successfully for task '" + taskName + "' as expected is --'" + expectedTaskDuration
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
							String expectedTaskTypeName = fnpremoveFormatting_forISR(eachQuantitySet[0]);
							fnpMymsg("Expected Task type name is--" + expectedTaskTypeName);
			
							String expectedTaskQuantity = fnpremoveFormatting_forISR(eachQuantitySet[1]);
							fnpMymsg("Expected Task Quantity is--" + expectedTaskQuantity);
			
							String expectedbillCode = fnpremoveFormatting_forISR(eachQuantitySet[2]);
							fnpMymsg("Expected bill code  is--" + expectedbillCode);
			
			
							
							
							
							
							foundThisBillCode = false;
							for (int j = 1; j <= totalrowsInBillCodesTable; j++) {
			
								taskName = fnpFetchFromTable("BillCodesTable", j, taskTypeIndex);
								String billcodeInTable = fnpFetchFromTable("BillCodesTable", j, billCodesIndex);
			
								
								
							if (billcodeInTable.equalsIgnoreCase(deskAuditTaskTab_expectedBillCode)) {
								String quantity = fnpFetchFromTable("BillCodesTable", j, quantityIndex);
								deskAuditTaskTab_expectedQuantity = Double.parseDouble(quantity);
							}
							
							
							if (billcodeInTable.equalsIgnoreCase(certAuditTaskTab_expectedBillCode)) {
								String quantity = fnpFetchFromTable("BillCodesTable", j, quantityIndex);
								certAuditTaskTab_expectedQuantity = Double.parseDouble(quantity);
							}
							
							
							
								
								
								
								/****************************************************************/
			
								if ((taskName.equalsIgnoreCase(expectedTaskTypeName)) && (billcodeInTable.equalsIgnoreCase(expectedbillCode))) {
									foundThisBillCode = true;
									String actualTaskQuantity = fnpFetchFromTable("BillCodesTable", j, quantityIndex);
			
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
			
									String totalAmount = fnpFetchFromTable("BillCodesTable", j, totalAmountIndex);
									totalAmount = totalAmount.replaceAll(",", "");
			
									String billCodeScheduledPrice = fnpFetchFromTable("BillCodesTable", j, billCodeScheduledPriceIndex);
									billCodeScheduledPrice = billCodeScheduledPrice.replaceAll(",", "");
			
									double billCodeScheduledPriceDouble = Double.parseDouble(billCodeScheduledPrice.replace("$", "").trim());
									double quantityDouble = Double.parseDouble(actualTaskQuantity);
									double actualTotalAmountDouble = Double.parseDouble(totalAmount.replace("$", "").trim());
			
									double expectedTotalAmountDouble = billCodeScheduledPriceDouble * quantityDouble;
/*			
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
									*/
									
									final double THRESHOLD = .0001;
									if (Math.abs(actualTotalAmountDouble - expectedTotalAmountDouble) < THRESHOLD){
										textMessage = "PASSED ==>Total amount is  matched in Bill Code table in row no.--'" + j + "'   as actual is -- '" + actualTotalAmountDouble
												+ "'  and expected is --'" + expectedTotalAmountDouble + "'.";
										fnpDisplayingMessageInFrame(textMessage, 5);
										fnpMymsg(textMessage);
										}	else{
											textMessage = "FAILED ==>Total amount is not matched in Bill Code table in row no.--'" + j + "'   as actual is -- '" + actualTotalAmountDouble
													+ "'  but expected is --'" + expectedTotalAmountDouble + "'.";
											fnpDisplayingMessageInFrame(textMessage, 10);
											fnpMymsg(textMessage);
											throw new Exception(textMessage);
									
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
			
		// Commented on 07-09-2018
/*			 if((classNameText.equalsIgnoreCase(ISO9001_SingleTestCaseName)))
			 {
				
				fnpProcessAI_ISR("WOISR_FinopActionItemTable", "WOISR_FinopActionItemTable_HeaderRow", (String) hashXlData.get("Finops_Action_item_name"), "Inprocess", "WOISR_FinopActionItemSaveNCloseBtn","");
				fnpProcessAI_ISR("WOISR_FinopActionItemTable", "WOISR_FinopActionItemTable_HeaderRow", (String) hashXlData.get("Finops_Action_item_name"), "Completed", "WOISR_FinopActionItemSaveNCloseBtn","");
				
				
			 }else{
				 fnpClick_OR("MoveToInProcessBtn");
			 }
			 
			 */
			 
		
/*		
			String aiCompletor="";
			if (loginAsFullName.equalsIgnoreCase("") | (loginAsFullName==null) ) {
				aiCompletor=(String) hashXlData.get("AccountManager").trim();
			} else {
				aiCompletor=loginAsFullName;
			}
			fnpProcessAI_ISR("WOISR_FinopActionItemTable", "WOISR_FinopActionItemTable_HeaderRow", (String) hashXlData.get("Finops_Action_item_name"), "Inprocess", "WOISR_FinopActionItemSaveNCloseBtn",aiCompletor);
			fnpProcessAI_ISR("WOISR_FinopActionItemTable", "WOISR_FinopActionItemTable_HeaderRow", (String) hashXlData.get("Finops_Action_item_name"), "Completed", "WOISR_FinopActionItemSaveNCloseBtn",aiCompletor);
			
			*/
			 
			 

		
			//In ISR, this finops is automatically assigned to the login user/ account manager but in SCFS, we need to reassign it to the login user as per Rachel Helwig on 7 Sept,2018
			 if((classNameText.equalsIgnoreCase(ISO9001_SingleTestCaseName)))
			 {
				
				fnpProcessAI_ISR("WOISR_FinopActionItemTable", "WOISR_FinopActionItemTable_HeaderRow", (String) hashXlData.get("Finops_Action_item_name"), "Inprocess", "WOISR_FinopActionItemSaveNCloseBtn","");
				fnpProcessAI_ISR("WOISR_FinopActionItemTable", "WOISR_FinopActionItemTable_HeaderRow", (String) hashXlData.get("Finops_Action_item_name"), "Completed", "WOISR_FinopActionItemSaveNCloseBtn","");
				
				
			 }else{
					String aiCompletor="";
					
/*					
					if (loginAsFullName.equalsIgnoreCase("") | (loginAsFullName==null) ) {
						aiCompletor=(String) hashXlData.get("AccountManager").trim();
					} else {
						aiCompletor=loginAsFullName;
					}
					*/
					
					if (  (loginAsFullName==null) ||loginAsFullName.equalsIgnoreCase("") ) {
						
						if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
							aiCompletor=(String) hashXlData.get("AccountManager_Code").trim();
						}else{
							aiCompletor=(String) hashXlData.get("AccountManager").trim();
						}

					} else {
					
						if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
							aiCompletor=loginUser_code;

						}else{
							aiCompletor=loginAsFullName;
		
						}
					}
				

					
					
					
					fnpProcessAI_ISR("WOISR_FinopActionItemTable", "WOISR_FinopActionItemTable_HeaderRow", (String) hashXlData.get("Finops_Action_item_name"), "Inprocess", "WOISR_FinopActionItemSaveNCloseBtn",aiCompletor);
					fnpProcessAI_ISR("WOISR_FinopActionItemTable", "WOISR_FinopActionItemTable_HeaderRow", (String) hashXlData.get("Finops_Action_item_name"), "Completed", "WOISR_FinopActionItemSaveNCloseBtn",aiCompletor);
					
			 }
			 
			 
			//IPM-14359
			// fnpClick_OR("MoveToInProcessBtn");
			 
				if (fnpCheckElementDisplayedImmediately("MoveToInProcessBtn")) {
					fnpMymsg("'Move To InProcess' button is  visible, so clicking it.");
					fnpClick_OR("MoveToInProcessBtn");
				}else{
					fnpMymsg("'Move To InProcess' button is not visible, so moving forward without looking for it.");
				}
				
			 

			// fnpClick_OR("MoveToInProcessBtn"); // without of this it is in
			// INPROCESS as soon as you completed finops AI

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

			int taskNameColIndex = fnpFindColumnIndex("TaskTabTable_HeaderRow", WOISOTaskTab_TaskAISummaryTableColName_TaskAIName);

			String[] expectedTaskNameArray = ((String) hashXlData.get("Task_created_just_after_InProcess")).split(",");

			int totalRowGeneratedInTaskTable = fnpCountRowsInTable("TaskTabTable");


			boolean foundTaskflag = false;
			for (int i = 0; i < expectedTaskNameArray.length; i++) {

				for (int j = 0; j < totalRowGeneratedInTaskTable; j++) {
					String taskTypeValue = fnpFetchFromTable("TaskTabTable", (j + 1), taskNameColIndex);
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
			fnpCommonFinalCatchBlock(t, "  Financial_Tab_And_InReviewToInProcess flow  is fail . ", "Financial_Tab_And_InReviewToInProcess flow");

		}

	}
	
	
	
	
	
	
	
	@Test(priority = 4, dependsOnMethods = { "Financial_Tab_And_InReviewToInProcess" })

	public void DeskAuditTask() throws Throwable {

		
		try {
	

			/******** Open the desk audit task in Task tab and compare some values *************************/
			fnpClick_OR("ISRTaskTab");
			String textMessage = "Now going to open the desk audit task in Task tab and compare some values ";
			fnpDisplayingMessageInFrame(textMessage, 5);

			String deskOpenIconXpath = OR.getProperty("TaskTabISR_DeskAudit_OpenIconPart1") + WOISOTaskName_DeskAudit+ OR.getProperty("TaskTabISR_DeskAudit_OpenIconPart2");
			fnpClick_NOR(deskOpenIconXpath);

			
			fnpMymsg("Expected Desk Audit billing code  is ---" + deskAuditTaskTab_expectedBillCode);
			fnpMymsg("Expected Desk Audit quantity  is ---" + deskAuditTaskTab_expectedQuantity);
			fnpMymsg("Expected Desk Audit Audit days  is ---" + deskAuditTaskTab_expectedAuditDays);
			fnpMymsg("");
			fnpMymsg("");

			int taskAINameIndex = fnpFindColumnIndex("TaskTabTable_HeaderRow", WOISOTaskTab_TaskAISummaryTableColName_TaskAIName);

			int deskRow = fnpFindRow("TaskTabTable", WOISOTaskName_DeskAudit, taskAINameIndex);

			String deskInnerTableHeaderXpath = OR.getProperty("ISRTaskInnerTableForBillingCodeTableHeader_part1") + (deskRow - 1)
					+ OR.getProperty("ISRTaskInnerTableForBillingCodeTableHeader_part2");

			int billingCodeInnerTableIndex = fnpFindColumnIndex_NOR(deskInnerTableHeaderXpath, "Billing Code");
			int quantityInnerTableIndex = fnpFindColumnIndex_NOR(deskInnerTableHeaderXpath, "Qty.");

			String deskInnerTableDataXpath = OR.getProperty("ISRTaskInnerTableForBillingCodeTableData_part1") + (deskRow - 1)
					+ OR.getProperty("ISRTaskInnerTableForBillingCodeTableData_part2");
			
			int billingDescIndex = fnpFindColumnIndex_NOR(deskInnerTableHeaderXpath, "Billing Desc");
			int innerdeskRow = fnpFindRowContainsName_NOR(deskInnerTableDataXpath, "Audit", billingDescIndex);
			
			
			String actualDeskBillingCode = fnpFetchFromTable_NOR(deskInnerTableDataXpath, innerdeskRow, billingCodeInnerTableIndex);
			fnpMymsg("Actual Desk Audit billing code  is ---" + actualDeskBillingCode);
			String actualDeskQuantityInString = fnpFetchFromTable_NOR(deskInnerTableDataXpath, innerdeskRow, quantityInnerTableIndex);
			double actualDeskQuantity = Double.parseDouble(actualDeskQuantityInString);
			fnpMymsg("Actual Desk Audit quantity  is ---" + actualDeskQuantity);

			String deskExpansionPanelXpath = OR.getProperty("ISRTaskExpansionPanelGrid_part1") + (deskRow - 1) + OR.getProperty("ISRTaskExpansionPanelGrid_part2");

			String auditDaysXpath = deskExpansionPanelXpath + "//tr/td/label[text()='Audit Days']/../following-sibling::td/label";

			String auditdaysInString=fnpGetText_NOR(auditDaysXpath);
			
			if (auditdaysInString.trim().equalsIgnoreCase("") ){
				msg="Audit days are not coming means blank space. Hene failed ";
				fnpMymsg(msg);
				throw new Exception(msg);				
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

			fnpType("OR", "SchemeCodeTxtBx", (String) hashXlData.get("Standard"));
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

			List<WebElement> taskTypeLastLabelLinks = driver.findElements(By.xpath(OR.getProperty("WorkOrderTaskTypeLinkInSchemeMgt_lastLabel")));
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
			
/*			
			if((classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.Cert_StandardsTestCaseName))){	
				 
					fnpClick_OR("ISRInfoTab_EditWO");
					if (fnpCheckElementDisplayedImmediately("EditWOBtn")) {				
						fnpClick_OR("EditWOBtn");
					}
					
					//fnpSelectTechnicalReviewer() ;
					fnpSelectTechnicalSpecialist();
			 }
			
			*/
			
			 if((classNameText.equalsIgnoreCase(ISO9001_SingleTestCaseName))){
					 startDateNo=120;
					 endDateNo=90; 
			 
			 

					
		
					fnpClick_OR("ISRInfoTab_EditWO");
		
					if (fnpCheckElementDisplayedImmediately("EditWOBtn")) {				
						fnpClick_OR("EditWOBtn");
					}
					
/*					
					//New code on 19-11-2020 IPM-14277
					*//*******************************************************//*
					fnpClick_OR("ISR_InfoTab_TechnicalReviewer_lookup");
					
					fnpMymsg("Just after  click Technical Reviewer lookup");
					fnpMymsg("Just before going to insert value of Technical Reviewer ");	
					
					String technicalReviewer;
					String technicalReviewer_code;
					if (  (loginAsFullName==null) ||loginAsFullName.equalsIgnoreCase("") ) {
						technicalReviewer=(String) hashXlData.get("AccountManager").trim();
						technicalReviewer_code=(String) hashXlData.get("AccountManager_Code").trim();
						
					} else {
						technicalReviewer=loginAsFullName;
						technicalReviewer_code=loginUser_code;
					}
					
					
					
					if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
						fnpSearchNSelectFirstRadioBtn(1, technicalReviewer_code, 1);
					}else{
						fnpSearchNSelectFirstRadioBtn(2, technicalReviewer, 1);
					}
					

					String enteredTechnicalReviewer = fnpGetORObjectX("ISR_InfoTab_TechnicalReviewer_txtbx").getAttribute("value");
					enteredTechnicalReviewer = fnpWaitTillTextBoxDontHaveValue("ISR_InfoTab_TechnicalReviewer_txtbx", "value");

					if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
						Assert.assertTrue(enteredTechnicalReviewer.contains(technicalReviewer_code), "Technical Reviewer is not selected properly from lookup because actual value is ---'"+enteredTechnicalReviewer+"' and expected is --'"+technicalReviewer_code+"'.");
					}else{
						Assert.assertTrue(enteredTechnicalReviewer.contains(technicalReviewer), "Technical Reviewer  is not selected properly from lookup because actual value is ---'"+enteredTechnicalReviewer+"' and expected is --'"+technicalReviewer+"'.");
					}
					
					fnpClick_OR("ISRSFTabSaveBtn");
					*//*********************************************************************//*
					*/
					fnpSelectTechnicalReviewer() ;
					
					
		
					String actualCertTargetGivenAtInfoTab = fnpWaitTillTextBoxDontHaveValue("CertTargetDateInputBxInfoTab", "value");
		
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
			
			
			
			
			
			 if((classNameText.equalsIgnoreCase(ISO9001_SingleTestCaseName))){
			
				 
						textMessage = "Now going to click Audit no. in Desk Audit at Task Tab ";
						fnpDisplayingMessageInFrame(textMessage, 5);
						if ( (fnpCheckElementDisplayedImmediately_NOR(deskOpenIconXpath)  )  ) {				
							fnpClick_NOR(deskOpenIconXpath);
							fnpMymsg("Clicked desk open icon");
						}
						
						deskRow = fnpFindRow("TaskTabTable", WOISOTaskName_DeskAudit, taskAINameIndex);// Added this line on 13 Jan,2021	
						String auditNoInDeskAuditXpath = OR.getProperty("AuditNoAtTaskTab_part1") + (deskRow - 1) + OR.getProperty("AuditNoAtTaskTab_part2");
			
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
			

			String visitNoInDeskAuditXpath = OR.getProperty("VisitNoAtTaskTab_part1") + (deskRow - 1) + OR.getProperty("VisitNoAtTaskTab_part2");

			String visitNo = fnpGetText_NOR(visitNoInDeskAuditXpath).trim();
			fnpMymsg("Going to check visit no, as if visit no is not present/blank then it will go for clicking bundle audit button. So here visit no. is--'"+visitNo+"'.");



			
			
			
			
			

			String auditNoInDeskAuditXpath = OR.getProperty("AuditNoAtTaskTab_part1") + (deskRow - 1) + OR.getProperty("AuditNoAtTaskTab_part2");
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
/*			String editBtnForEditDeskAuditTaskXpath = OR.getProperty("EditBtnForEditTaskAtTaskTab_part1") + (deskRow - 1) + OR.getProperty("EditBtnForEditTaskAtTaskTab_part2");	
			fnpClick_NOR(editBtnForEditDeskAuditTaskXpath);
			*/
		
			
			
/*			String expenseAllocationbtnXpath = OR.getProperty("ExpenseAllocationBtn_TaskAtTaskTab_part1") + (deskRow - 1) + OR.getProperty("ExpenseAllocationBtn_TaskAtTaskTab_part2");	
			fnpClick_NOR(expenseAllocationbtnXpath);
			*/
			
			String editFeesbtnXpath = OR.getProperty("EditFeesBtnForEditTaskAtTaskTab_part1") + (deskRow - 1) + OR.getProperty("EditFeesBtnForEditTaskAtTaskTab_part2");	
			fnpClick_NOR(editFeesbtnXpath);
			
			
			
			
			/***************************************************************************/
			
			Date todayDate=new Date();
			sdfmt1 = new SimpleDateFormat("dd-MMM-yyyy");
			String todaydateInStringformat = sdfmt1.format(todayDate);
		//	fnpType("OR", "BoundaryStartDateEditTxtBxAtEditTaskDialog", todaydateInStringformat);
			
			
			fnpClick_OR_WithoutWait("BoundaryStartDateBtnAtEditTaskDialog");
			Thread.sleep(2000);			
			//fnpCalendarDatePicker_BySelectValues_From_NextAndBack(todaydateInStringformat);
			//IPM-9079
			fnpCalendarDatePicker_BySelectValues_From_DropDown2(todaydateInStringformat,
					OR.getProperty("calendarDatePickerSelectYear_xpath"), OR.getProperty("calendarDatePickerSelectMonth_xpath"));
			
			
			Thread.sleep(2000);	
			
			
			
			
			 todayDate=new Date();
			Calendar c = Calendar.getInstance();
			c.setTime(todayDate);
			c.add(Calendar.DAY_OF_MONTH, 7);
			Date afterOneWeekdate = c.getTime();

			sdfmt1 = new SimpleDateFormat("dd-MMM-yyyy");
			String afterOneWeekdateInStringformat = sdfmt1.format(afterOneWeekdate);
	//		fnpType("OR", "BoundaryEndDateEditTxtBxAtEditTaskDialog", afterOneWeekdateInStringformat);	
			
			
			fnpClick_OR_WithoutWait("BoundaryEndDateBtnAtEditTaskDialog");
			Thread.sleep(2000);			
			//fnpCalendarDatePicker_BySelectValues_From_NextAndBack(afterOneWeekdateInStringformat);
			//IPM-9079
			fnpCalendarDatePicker_BySelectValues_From_DropDown2(afterOneWeekdateInStringformat,
					OR.getProperty("calendarDatePickerSelectYear_xpath"), OR.getProperty("calendarDatePickerSelectMonth_xpath"));

			
			Thread.sleep(2000);	
		//	Thread.sleep(5000);	
			//Thread.sleep(6000);	// to avoid we are sorry error some time
			//Thread.sleep(10000);
			
			//IPM-10923
			fnpPFList("ISR_EditTaskFee_dialogBox_JustificationReason_dropDown_PFList", "ISR_EditTaskFee_dialogBox_JustificationReason_dropDown_PFListOptions", "Other");
			fnpPFList("ISR_EditTaskFee_dialogBox_DurationJustificationReason_dropDown_PFList", "ISR_EditTaskFee_dialogBox_DurationJustificationReason_dropDown_PFListOptions", "Other");
			
			
			
			//fnpClick_OR("SaveBtnAtEditTask");
			fnpClickUsingJavascript(OR.getProperty("SaveBtnAtEditTask"));
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
					OR.getProperty("calendarDatePickerSelectYear_xpath"), OR.getProperty("calendarDatePickerSelectMonth_xpath"));
			
			
			String scheduleStartDateforDeskAudit=todaydateInStringformat;
			
			
			Thread.sleep(2000);	
		//	Thread.sleep(4000);	
			fnpClick_OR("ScheduleEndDateCalBtn_xpath");
			Thread.sleep(2000);			
			fnpCalendarDatePicker_BySelectValues_From_DropDown2(afterOneWeekdateInStringformat,
					OR.getProperty("calendarDatePickerSelectYear_xpath"), OR.getProperty("calendarDatePickerSelectMonth_xpath"));
			
			
			
			String scheduleEndDateforDeskAudit=afterOneWeekdateInStringformat;
			Thread.sleep(2000);	
		//	Thread.sleep(4000);	
		//	Thread.sleep(6000);	// to avoid we are sorry error some time
		//	Thread.sleep(10000);
			
			
			
			
			
		//	fnpClick_OR("SaveBtnAtViewVisitPage");	
			
			
			*/
			
			
			
			
			todaydateInStringformat = sdfmt1.format(new Date());
			String scheduleEndDateforDeskAudit = fnpSelectingAuditorThroughSAM(todaydateInStringformat,afterOneWeekdateInStringformat);
			String scheduleStartDateforDeskAudit=todaydateInStringformat;
			/* String scheduleEndDateforDeskAudit=afterOneWeekdateInStringformat; */
			
/*			
			
			fnpClick_OR("EditBtnOnViewVisitPage");
			fnpGetORObjectX("SaveBtnAtViewVisitPage").click();
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
				//fnpCallGoldenProcedure_COPY_AUDIT_TRANSACTION_SUBMIT(copyFromAudit_ForDeskAudit,copyToAudit_ForDeskAudit,(String) hashXlData.get("Auditor").trim());
				String Auditor;
				if (  (loginAsFullName==null) ||loginAsFullName.equalsIgnoreCase("") ) {
					Auditor=(String) hashXlData.get("Auditor").trim();
				} else {
					Auditor=loginAsFullName;
				}
				fnpCallGoldenProcedure_COPY_AUDIT_TRANSACTION_SUBMIT(copyFromAudit_ForDeskAudit,copyToAudit_ForDeskAudit,Auditor);
				fnpMymsg("Copy from Desk Audit---'"+copyFromAudit_ForDeskAudit +"'  -- and auditor is---"+Auditor);
				
				fnpMymsg("Golden procedure 'COPY_AUDIT_TRANSACTION_SUBMIT' ran successfully.");
			} else {
				
				if (usingGoldenProcedureOrOasis.equalsIgnoreCase("oasis")) {
					
					fnpOasisPartInISO("desk", visitNo);
					fnpLaunchBrowserAndLogin();



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
			
			String expecteddeskAuditor;
			
/*			
			if (loginAsFullName.equalsIgnoreCase("") | (loginAsFullName==null) ) {
				expecteddeskAuditor=(String) hashXlData.get("AccountManager").trim();
			} else {
				expecteddeskAuditor=loginAsFullName;
			}
			*/
			if (  (loginAsFullName==null) ||loginAsFullName.equalsIgnoreCase("") ) {
				
				if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
					expecteddeskAuditor=(String) hashXlData.get("AccountManager_Code").trim();
				}else{
					expecteddeskAuditor=(String) hashXlData.get("AccountManager").trim();
				}

			} else {
			
				if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
					expecteddeskAuditor=loginUser_code;

				}else{
					expecteddeskAuditor=loginAsFullName;

				}
			}
		

			
			
			
			
			
			//if (deskAuditor.equalsIgnoreCase((String)hashXlData.get("AccountManager"))) {
			//if (deskAuditor.equalsIgnoreCase(expecteddeskAuditor)) {
			if (deskAuditor.contains(expecteddeskAuditor)) {
				fnpMymsg("Auditor is present in desk Audit and its value is ---"+deskAuditor);
			} else {
				msg="Auditor is NOT present in desk Audit because actual  value is ---'"+deskAuditor+"' and expected is --'"+expecteddeskAuditor+"'.";
				fnpMymsg(msg);
				throw new Exception(msg);
			}
			
			
			
			
			
			

			String scheduleStartDateXpath = deskExpansionPanelXpath + "//tr/td/label[text()='Schedule Start Date']/../following-sibling::td/label";
			String actualScheduleStartDate = fnpGetText_NOR(scheduleStartDateXpath);
			
			if (actualScheduleStartDate.equalsIgnoreCase(scheduleStartDateforDeskAudit)) {
				fnpMymsg("Schedule Audit start date is present and its value is ---'"+actualScheduleStartDate +"' and expected is --'"+scheduleStartDateforDeskAudit +"' .");
			} else {
				msg="Schedule Audit start date is NOT  matched as its value is ---"+actualScheduleStartDate+"' and expected is --'"+scheduleStartDateforDeskAudit +"' .";
				fnpMymsg(msg);
				//throw new Exception("Schedule Audit start date is NOT  present");
				throw new Exception(msg);
			}
			
			
			
			
			String scheduleEndDateXpath = deskExpansionPanelXpath + "//tr/td/label[text()='Schedule End Date']/../following-sibling::td/label";
			String actualScheduleEndDate = fnpGetText_NOR(scheduleEndDateXpath);
			
			if (actualScheduleEndDate.equalsIgnoreCase(scheduleEndDateforDeskAudit)) {
				fnpMymsg("Schedule Audit End date is present and its value is ---'"+actualScheduleEndDate +"' and expected is --'"+scheduleEndDateforDeskAudit +"' .");
			} else {
				msg="Schedule Audit End date is NOT  matched as its value is ---"+actualScheduleEndDate+"' and expected is --'"+scheduleEndDateforDeskAudit +"' .";
				fnpMymsg(msg);
				//throw new Exception("Schedule Audit End date is NOT  present");
				throw new Exception(msg);
			}
			
			
			
			String DeskAuditStatusXpath = deskExpansionPanelXpath + "//tr/td/label[text()='Audit Status']/../following-sibling::td/label";
			String DeskAuditStatus = fnpGetText_NOR(DeskAuditStatusXpath);
			
			if (DeskAuditStatus.equalsIgnoreCase("SUBMITTED")) {
				fnpMymsg("Desk Audit status is  become 'SUBMITTED'  as its value is ---'"+DeskAuditStatus +"' and expected is --'"+"SUBMITTED" +"' .");
			} else {
				msg="Desk Audit status is NOT become 'SUBMITTED'  as its value is ---"+DeskAuditStatus+"' and expected is --'"+"SUBMITTED" +"' .";
				fnpMymsg(msg);
				//throw new Exception("Desk Audit status is NOT become 'SUBMITTED'");
				throw new Exception(msg);
			}
			
		
	
	
			
			/******************New steps for desk audit in SQF on 10-07-2017 ******************/
			if((classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.Cert_StandardsTestCaseName))){		 
					taskAINameIndex = fnpFindColumnIndex("TaskTabTable_HeaderRow", WOISOTaskTab_TaskAISummaryTableColName_TaskAIName);
					deskRow = fnpFindRow("TaskTabTable", WOISOTaskName_DeskAudit, taskAINameIndex);
					 
					String deskInnerTableDataXpathForAI = OR.getProperty("ISRTaskInnerTableForActionItemsTableData_part1") + (deskRow - 1)
							+ OR.getProperty("ISRTaskInnerTableForActionItemsTableData_part2");
					
					String deskInnerTableHeaderXpathForAI = OR.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part1") + (deskRow - 1)
							+ OR.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part2");
					
					
					
		
					int aiTypeInnerTableIndex = fnpFindColumnIndex_NOR(deskInnerTableHeaderXpathForAI, "AI Type");
					int aiNumberInnerTableIndex = fnpFindColumnIndex_NOR(deskInnerTableHeaderXpathForAI, "AI Number");
		
					
					
					int performAuditRow = fnpFindRowContainsName_NOR(deskInnerTableDataXpathForAI, WOISOTaskTab_PerformAuditAIType, aiTypeInnerTableIndex);
					
					if (performAuditRow>0) {
						fnpMymsg("Perform Audit action item has been generated for Desk Audit task.");
					} else {
						msg="Perform Audit action item has NOT been generated for Desk Audit task.";
						fnpMymsg(msg);
						throw new Exception(msg);
					}
					
					String performAuditAINo = fnpFetchFromTable_NOR(deskInnerTableDataXpathForAI, performAuditRow, aiNumberInnerTableIndex);
					fnpMymsg("Perform Audit action item no.   is ---" + performAuditAINo);
		
					
/*					
					//*******************************PerformAuditAICompleted Automatically Through Scheduler***********************************************************************************	
					
					int statusInnerTableColIndex = fnpFindColumnIndex_NOR(deskInnerTableHeaderXpathForAI, "Status");
					int waitCount = 0;
					String performAuditStatus;
		
						
					//fnpClick_OR("Complete_Perform_Audit_AI_button");
					
					
					fnpRunJob("CompletePerformAuditAIQJob");
					fnpClick_OR("ISRTaskTab");
					
					
					
					if  (fnpCheckElementDisplayedImmediately_NOR(deskOpenIconXpath)  ) {				
						fnpClick_NOR(deskOpenIconXpath);
					}

					
					fnpCheckError(" after loading ");
							
					waitCount=0;
					int maxWaitTimeInMinutes=50;//25;
				
					
					 deskInnerTableHeaderXpathForAI = OR.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part1") + (deskRow - 1)
							+ OR.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part2");
					
					
					while (true) {
						waitCount++;
		
						taskAINameIndex = fnpFindColumnIndex("TaskTabTable_HeaderRow", WOISOTaskTab_TaskAISummaryTableColName_TaskAIName);
						deskRow = fnpFindRowContainsName("TaskTabTable", WOISOTaskTab_Stage1DeskAudit_MainTaskAIName, taskAINameIndex);
						deskInnerTableDataXpathForAI  = OR.getProperty("ISRTaskInnerTableForActionItemsTableData_part1") + (deskRow - 1)
								+ OR.getProperty("ISRTaskInnerTableForActionItemsTableData_part2");
						performAuditRow = fnpFindRowContainsName_NOR(deskInnerTableDataXpathForAI, "PerformAudit", aiTypeInnerTableIndex);
						deskInnerTableHeaderXpathForAI = OR.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part1") + (deskRow - 1)
								+ OR.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part2");
						
						statusInnerTableColIndex = fnpFindColumnIndex_NOR(deskInnerTableHeaderXpathForAI, "Status");
						
						performAuditStatus = fnpFetchFromTable_NOR(deskInnerTableDataXpathForAI, performAuditRow, statusInnerTableColIndex);
						fnpMymsg("performAuditStatus ----" + performAuditStatus);
						
						fnpCheckError(" after loading ");
		
						if (performAuditStatus.equalsIgnoreCase("AI_COMPLETED")) {
							fnpMymsg("PerformAudit status has become to 'AI_COMPLETED' state. ");
							break;
						} else {
							
							
							if (waitCount >(maxWaitTimeInMinutes/2)) {
								//msg="Script waited for approx. more than "+(maxWaitTimeInMinutes)+" min. [after running job from job url] to become 'AI_COMPLETED' state of PerformAudit but it is still showing '" + performAuditStatus + "' .";
								msg="Script waited for approx. more than "+(maxWaitTimeInMinutes)+" min. [waiting to complete automatically through scheduler job run] to become 'AI_COMPLETED' state of PerformAudit but it is still showing '" + performAuditStatus + "' .";
								fnpMymsg(msg);
								throw new Exception(msg);
							}
							
							
							fnpCheckError(" after loading ");
							fnpMymsg(" we are in  waiting loop for PerformAudit become in Ready ----" + waitCount);
							fnpDisplayingMessageInFrame("Now we are in  waiting loop for status of PerformAudit action item in desk audit task to become  Ready (by batch job behind) ----" + ((double)waitCount*2)+" approx. min.", 8);
							//Thread.sleep(1000 * 30 * 1);
							Thread.sleep(1000 * 30 * 4);
							fnpCheckError(" after loading ");
						//	driver.navigate().refresh();
							
							
							
							
							
							
							// fnpLoading_wait();
							//Thread.sleep(8000);
		
							// fnpLoading_wait();
		
		
							fnpClick_OR("ISRInfoTab_EditWO");
							fnpCheckError(" after loading ");
							fnpClick_OR("ISRTaskTab");
							fnpCheckError(" after loading ");
							if (fnpCheckElementDisplayed("EditWOBtn", 5)) {
								fnpClick_OR("EditWOBtn");
							}
							
							if ( (fnpCheckElementDisplayedImmediately_NOR(deskOpenIconXpath) & (!(fnpCheckElementDisplayedImmediately_NOR(deskInnerTableHeaderXpathForAI))) )  ) {				
								fnpClick_NOR(deskOpenIconXpath);
							}
		
							
						}
		
						//if (waitCount > 60) {
						if (waitCount > 4) {
		
							fnpMymsg("Script waited for approx. 2 min to become 'AI_COMPLETED' state of PerformAudit but it is still showing '" + performAuditStatus + "' .");
							throw new Exception("Script waited for approx. 2 min to become 'AI_COMPLETED' state of PerformAudit but it is still showing '" + performAuditStatus + "' .");
		
						}
		
					}
					
					//*******************************PerformAuditAICompleted Automatically Through Scheduler***********************************************************************************		
					*/
					
					fnpWaitForPerformAuditAIGetCompletedAutomaticallyThroughScheduler(WOISOTaskTab_Stage1DeskAudit_MainTaskAIName);
					
					
					
					
/*					
					*//**************New changes simailar to ISR on 21-01-2021************************//*
					fnpSwitchUserToProcessTechnicalReviewAI();
					fnpSearchWO_and_ReachToTaskTab_OpenDeskExpandIcon_ISR();
					*//************************************************************//*
					
					*/
					
				
					 aiTypeInnerTableIndex = fnpFindColumnIndex_NOR(deskInnerTableHeaderXpathForAI, "AI Type");
					 aiNumberInnerTableIndex = fnpFindColumnIndex_NOR(deskInnerTableHeaderXpathForAI, "AI Number");
					 
					 

					int statusInnerTableColIndex = fnpFindColumnIndex_NOR(deskInnerTableHeaderXpathForAI, "Status");
						
						
					 
					 
					
					
					int technicalReviewAIRowNo = fnpFindRowContainsName_NOR(deskInnerTableDataXpathForAI, WOISOTaskTab_TechnicalReviewAIName, aiTypeInnerTableIndex);
					
					if (technicalReviewAIRowNo>0) {
						fnpMymsg("TechnicalReview action item has been generated for Desk Audit task.");
					} else {
						msg="TechnicalReview action item has NOT been generated for Desk Audit task.";
						fnpMymsg(msg);
						throw new Exception(msg);
					}
					
					String technicalReviewAINo = fnpFetchFromTable_NOR(deskInnerTableDataXpathForAI, technicalReviewAIRowNo, aiNumberInnerTableIndex);
					fnpMymsg("TechnicalReview action item no.   is ---" + technicalReviewAINo);
		
					String technicalReviewStatus = fnpFetchFromTable_NOR(deskInnerTableDataXpathForAI, technicalReviewAIRowNo, statusInnerTableColIndex);
					
					if (!(technicalReviewStatus.equalsIgnoreCase("AI_CREATED"))) {
						msg="Current Status of TechnicalReview action item is NOT 'AI_CREATED'  b/c its current status is --"+technicalReviewStatus;
						fnpMymsg(msg);
						throw new Exception (msg);
					} else {
						fnpMymsg("Current Status of TechnicalReview action item is AI_CREATED.");
					}
		
					
					/***************************************************************************************/
					
					fnpCompleteExdbUpdateAI( deskInnerTableHeaderXpathForAI, deskInnerTableDataXpathForAI, WOISOTaskType_DeskAudit,WOISOTaskTab_Stage1DeskAudit_MainTaskAIName,"Completed");
					

					
					/***************************************************************************************/
					//Assign TechReview to cshileds/technical reviewer from excel  19-04-2021
					
					//**********************************************
					
					fnpClickALinkInATable(technicalReviewAINo);
										
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
								if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
									fnpSearchNSelectFirstRadioBtn(1, reassignee, 1);

								}else{
									fnpSearchNSelectFirstRadioBtn(2, reassignee, 1);
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
					fnpSearchWO_and_ReachToTaskTab_OpenDeskExpandIcon_ISR(WOISOTaskName_DeskAudit);
					
					//*********************************************
					
					//Technical Review reverted back
					fnpProcessAI_ISR_TaskTab_1(deskInnerTableDataXpathForAI,deskInnerTableHeaderXpathForAI,WOISOTaskTab_TechnicalReviewAIName, "Completed","SaveButton__OnConsolidatedScreen_TechnicalReviewAI");
					//fnpProcessAI_ISR_TaskTab_1_old(deskInnerTableDataXpathForAI,deskInnerTableHeaderXpathForAI,WOISOTaskTab_TechnicalReviewAIName, "Completed","SaveButton__OnConsolidatedScreen");
					
		
					fnpVerifyAIStatusInISR(WOISOTaskType_DeskAudit,WOISOTaskTab_Stage1DeskAudit_MainTaskAIName,WOISOTaskTab_TechnicalReviewAIName,"Completed");
					
					
/*					fnpLaunchBrowserAndLogin();
					fnpSearchWO_and_ReachToTaskTab_OpenDeskExpandIcon_ISR();
					*/
					
					fnpRecordChangeAI(WOISOTaskTab_Stage1DeskAudit_MainTaskAIName,WOISOTaskType_DeskAudit);
					
					
			}


			 /*************************************************************************************/
			
			
			
			
			
			
			
			
			
			
			
			
			
			
	
			
			fnpMymsg("===============================================================================================================");

			// }

		} catch (Throwable t) {
			//isTestPass = true;
			fnpMymsg(" **************************************************************");
			fnpCommonFinalCatchBlock(t, "  DeskAuditTask flow  is fail . ", "DeskAuditTask");

		}

	}
	
	
	@Test(priority = 5, dependsOnMethods = { "DeskAuditTask" })

	public void CertAuditTask() throws Throwable {

		try {
	
			fnpClick_OR("ISRTaskTab");

			String textMessage = "Now going to perform Cert Audit task. ";
			fnpDisplayingMessageInFrame(textMessage, 5);
			String certOpenIconXpath = OR.getProperty("TaskTabISR_CertAudit_OpenIconPart1") + WOISOTaskType_CertificationAudit+ OR.getProperty("TaskTabISR_CertAudit_OpenIconPart2");
			//fnpClick_NOR(certOpenIconXpath);
			
			//To close already expanded deskaudit task
			String deskAlreadyExpandedIconXpath = OR.getProperty("TaskTabISR_ToCloseAlreadyExpanded_IconPart1") + WOISOTaskType_DeskAudit+ OR.getProperty("TaskTabISR_ToCloseAlreadyExpanded_IconPart2");
			if(  fnpCheckElementDisplayedImmediately_NOR(deskAlreadyExpandedIconXpath)   ) {				
				fnpClick_NOR(deskAlreadyExpandedIconXpath);
			}
			

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
			

			
						String certInnerTableHeaderXpath = OR.getProperty("ISRTaskInnerTableForBillingCodeTableHeader_part1") + (certRow - 1)
								+ OR.getProperty("ISRTaskInnerTableForBillingCodeTableHeader_part2");
			
						 int billingCodeInnerTableIndex = fnpFindColumnIndex_NOR(certInnerTableHeaderXpath, "Billing Code");
						 int quantityInnerTableIndex = fnpFindColumnIndex_NOR(certInnerTableHeaderXpath, "Qty.");
			
						String certInnerTableDataXpath = OR.getProperty("ISRTaskInnerTableForBillingCodeTableData_part1") + (certRow - 1)
								+ OR.getProperty("ISRTaskInnerTableForBillingCodeTableData_part2");
						
						
						 int billingDescIndex = fnpFindColumnIndex_NOR(certInnerTableHeaderXpath, "Billing Desc");
						int innercertRow = fnpFindRowContainsName_NOR(certInnerTableDataXpath, "Certification Audit", billingDescIndex);
						
						
						String actualCertBillingCode = fnpFetchFromTable_NOR(certInnerTableDataXpath, innercertRow, billingCodeInnerTableIndex);
						fnpMymsg("Actual Cert Audit billing code  is ---" + actualCertBillingCode);
						String actualCertQuantityInString = fnpFetchFromTable_NOR(certInnerTableDataXpath, innercertRow, quantityInnerTableIndex);
						double actualCertQuantity = Double.parseDouble(actualCertQuantityInString);
						fnpMymsg("Actual Cert Audit quantity  is ---" + actualCertQuantity);
			
						String certExpansionPanelXpath = OR.getProperty("ISRTaskExpansionPanelGrid_part1") + (certRow - 1) + OR.getProperty("ISRTaskExpansionPanelGrid_part2");
			
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
						
						
			
						
						 if((classNameText.equalsIgnoreCase(ISO9001_SingleTestCaseName))){
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
						String auditNoInCertAuditXpath = OR.getProperty("AuditNoAtTaskTab_part1") + (certRow - 1) + OR.getProperty("AuditNoAtTaskTab_part2");
			
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
			String auditNoInCertAuditXpath = OR.getProperty("AuditNoAtTaskTab_part1") + (certRow - 1) + OR.getProperty("AuditNoAtTaskTab_part2");
			
			String auditNo = fnpGetText_NOR(auditNoInCertAuditXpath);
			
			

			String visitNoInCertAuditXpath = OR.getProperty("VisitNoAtTaskTab_part1") + (certRow - 1) + OR.getProperty("VisitNoAtTaskTab_part2");

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

						
						
			
			
			
			
			 visitNoInCertAuditXpath = OR.getProperty("VisitNoAtTaskTab_part1") + (certRow - 1) + OR.getProperty("VisitNoAtTaskTab_part2");
			
			 auditNoInCertAuditXpath = OR.getProperty("AuditNoAtTaskTab_part1") + (certRow - 1) + OR.getProperty("AuditNoAtTaskTab_part2");
			
			
			 auditNo = fnpGetText_NOR(auditNoInCertAuditXpath);
			
			
			
			
			
			
			
			
			
					
			if (fnpCheckElementDisplayedImmediately("EditWOBtn")) {				
				fnpClick_OR("EditWOBtn");
			}
			


			
			
			/*****************IPM-6227***********************************************/
/*			
			String editBtnForEditCertAuditTaskXpath = OR.getProperty("EditBtnForEditTaskAtTaskTab_part1") + (certRow - 1) + OR.getProperty("EditBtnForEditTaskAtTaskTab_part2");			
			fnpClick_NOR(editBtnForEditCertAuditTaskXpath);
			*/
			
/*			String expenseAllocationbtnXpath = OR.getProperty("ExpenseAllocationBtn_TaskAtTaskTab_part1") + (certRow - 1) + OR.getProperty("ExpenseAllocationBtn_TaskAtTaskTab_part2");	
			fnpClick_NOR(expenseAllocationbtnXpath);
			*/
			
			
			String editFeesbtnXpath = OR.getProperty("EditFeesBtnForEditTaskAtTaskTab_part1") + (certRow - 1) + OR.getProperty("EditFeesBtnForEditTaskAtTaskTab_part2");	
			fnpClick_NOR(editFeesbtnXpath);
			
			
			/***************************************************************************/
			
			
			Date todayDate=new Date();
			SimpleDateFormat sdfmt1 = new SimpleDateFormat("dd-MMM-yyyy");
			String todaydateInStringformat = sdfmt1.format(todayDate);
		//	fnpType("OR", "BoundaryStartDateEditTxtBxAtEditTaskDialog", todaydateInStringformat);
			
			
			fnpClick_OR_WithoutWait("BoundaryStartDateBtnAtEditTaskDialog");
			Thread.sleep(2000);			
			//fnpCalendarDatePicker_BySelectValues_From_NextAndBack(todaydateInStringformat);
			//IPM-9079
			fnpCalendarDatePicker_BySelectValues_From_DropDown2(todaydateInStringformat,
					OR.getProperty("calendarDatePickerSelectYear_xpath"), OR.getProperty("calendarDatePickerSelectMonth_xpath"));
			
			
			//Thread.sleep(4000);
			Thread.sleep(2000);	
			
			
			
			
			
			todayDate=new Date();
			Calendar c = Calendar.getInstance();
			c.setTime(todayDate);
			c.add(Calendar.DAY_OF_MONTH, 7);
			Date afterOneWeekdate = c.getTime();

			sdfmt1 = new SimpleDateFormat("dd-MMM-yyyy");
			String afterOneWeekdateInStringformat = sdfmt1.format(afterOneWeekdate);
		//	fnpType("OR", "BoundaryEndDateEditTxtBxAtEditTaskDialog", afterOneWeekdateInStringformat);	
			
			
			fnpClick_OR_WithoutWait("BoundaryEndDateBtnAtEditTaskDialog");
			Thread.sleep(2000);			
			//fnpCalendarDatePicker_BySelectValues_From_NextAndBack(afterOneWeekdateInStringformat);
			//IPM-9079
			fnpCalendarDatePicker_BySelectValues_From_DropDown2(afterOneWeekdateInStringformat,
					OR.getProperty("calendarDatePickerSelectYear_xpath"), OR.getProperty("calendarDatePickerSelectMonth_xpath"));
			
			
			Thread.sleep(2000);	
			
			//IPM-10923
			fnpPFList("ISR_EditTaskFee_dialogBox_JustificationReason_dropDown_PFList", "ISR_EditTaskFee_dialogBox_JustificationReason_dropDown_PFListOptions", "Other");	
			fnpPFList("ISR_EditTaskFee_dialogBox_DurationJustificationReason_dropDown_PFList", "ISR_EditTaskFee_dialogBox_DurationJustificationReason_dropDown_PFListOptions", "Other");
			
			
			//fnpClick_OR("SaveBtnAtEditTask");
			fnpClickUsingJavascript(OR.getProperty("SaveBtnAtEditTask"));
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
			 String scheduleEndDateforCertAudit = fnpSelectingAuditorThroughSAM(todaydateInStringformat,afterOneWeekdateInStringformat);
			String scheduleStartDateforCertAudit=todaydateInStringformat;
			/* String scheduleEndDateforCertAudit=afterOneWeekdateInStringformat; */

			int copyFromAudit_ForCertAudit;
			int copyToAudit_ForCertAudit;
			String usingGoldenProcedureOrOasis = (String) hashXlData.get("Audit_usingGoldenProcedureOrOasis");
			if (usingGoldenProcedureOrOasis.equalsIgnoreCase("golden")) {
				copyFromAudit_ForCertAudit=Integer.parseInt((String) hashXlData.get("copy_From_Audit_ForCertAuditTask").trim());
				copyToAudit_ForCertAudit=Integer.parseInt(auditNo.trim());
				
				fnpMymsg("Just before running golden procedure - COPY_AUDIT_TRANSACTION_SUBMIT");
				//fnpCallGoldenProcedure_COPY_AUDIT_TRANSACTION_SUBMIT(copyFromAudit_ForCertAudit,copyToAudit_ForCertAudit,(String) hashXlData.get("Auditor").trim());
				String Auditor;
				if (loginAsFullName.equalsIgnoreCase("") || (loginAsFullName==null) ) {
					Auditor=(String) hashXlData.get("Auditor").trim();
				} else {
					Auditor=loginAsFullName;
				}
				fnpCallGoldenProcedure_COPY_AUDIT_TRANSACTION_SUBMIT(copyFromAudit_ForCertAudit,copyToAudit_ForCertAudit,Auditor);
				fnpMymsg("Copy from Cert Audit---'"+copyFromAudit_ForCertAudit +"'  -- and auditor is---"+Auditor);
				
				
				fnpMymsg("Golden procedure 'COPY_AUDIT_TRANSACTION_SUBMIT' ran successfully.");
			} else {
				
				if (usingGoldenProcedureOrOasis.equalsIgnoreCase("oasis")) {
					fnpOasisPartInISO("cert", visitNo);
					fnpLaunchBrowserAndLogin();
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
			
			String certExpansionPanelXpath = OR.getProperty("ISRTaskExpansionPanelGrid_part1") + (certRow - 1) + OR.getProperty("ISRTaskExpansionPanelGrid_part2");
			
			
			
			
			
			taskAINameIndex = fnpFindColumnIndex("TaskTabTable_HeaderRow", WOISOTaskTab_TaskAISummaryTableColName_TaskAIName);
			//certRow = fnpFindRowContainsName("TaskTabTable", "Certification", taskAINameIndex);
			certRow = fnpFindRowContainsName("TaskTabTable", WOISOTaskTab_Certification_MainTaskAIName, taskAINameIndex);
			String certInnerTableDataXpathForAI = OR.getProperty("ISRTaskInnerTableForActionItemsTableData_part1") + (certRow - 1)
					+ OR.getProperty("ISRTaskInnerTableForActionItemsTableData_part2");
			
			String certInnerTableHeaderXpathForAI = OR.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part1") + (certRow - 1)
					+ OR.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part2");
			
			

			
			String certAuditorXpath = certExpansionPanelXpath + "//tr/td/label[text()='Auditor']/../following-sibling::td/label";
			String certAuditor = fnpGetText_NOR(certAuditorXpath);
			
		
			String expectedCertAuditor;
			
/*			
			if (loginAsFullName.equalsIgnoreCase("") | (loginAsFullName==null) ) {
				expectedCertAuditor=(String) hashXlData.get("AccountManager").trim();
			} else {
				expectedCertAuditor=loginAsFullName;
			}
			*/
			
			if (loginAsFullName.equalsIgnoreCase("") || (loginAsFullName==null) ) {
				
				if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
					expectedCertAuditor=(String) hashXlData.get("AccountManager_Code").trim();
				}else{
					expectedCertAuditor=(String) hashXlData.get("AccountManager").trim();
				}

			} else {
			
				if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
					expectedCertAuditor=loginUser_code;

				}else{
					expectedCertAuditor=loginAsFullName;

				}
			}
		

			
			
			
			//if (certAuditor.equalsIgnoreCase((String)hashXlData.get("AccountManager"))) {
			//if (certAuditor.equalsIgnoreCase(expectedCertAuditor)) {
			if (certAuditor.contains(expectedCertAuditor)) {
				fnpMymsg("Auditor is present in Cert Audit and its value is ---"+certAuditor);
			} else {
				msg="Auditor is NOT present in Cert Audit because actual  value is ---'"+certAuditor+"' and expected is --'"+expectedCertAuditor+"'.";
				fnpMymsg(msg);
				throw new Exception(msg);
			}
			
			
			
			
			
			

			String scheduleStartDateXpath = certExpansionPanelXpath + "//tr/td/label[text()='Schedule Start Date']/../following-sibling::td/label";
			String actualScheduleStartDate = fnpGetText_NOR(scheduleStartDateXpath);
			
			if (actualScheduleStartDate.equalsIgnoreCase(scheduleStartDateforCertAudit)) {
				fnpMymsg("Schedule Audit start date in Cert Audit is present and its value is ---'"+actualScheduleStartDate +"' and expected is --'"+scheduleStartDateforCertAudit +"' .");
			} else {
				msg="Schedule Audit start date in Cert Audit  is NOT  matched as its value is ---"+actualScheduleStartDate+"' and expected is --'"+scheduleStartDateforCertAudit +"' .";
				fnpMymsg(msg);
				//throw new Exception("Schedule Audit start date is NOT  present");
				throw new Exception(msg);
			}
			
			
			
			
			String scheduleEndDateXpath = certExpansionPanelXpath + "//tr/td/label[text()='Schedule End Date']/../following-sibling::td/label";
			String actualScheduleEndDate = fnpGetText_NOR(scheduleEndDateXpath);
			
			if (actualScheduleEndDate.equalsIgnoreCase(scheduleEndDateforCertAudit)) {
				fnpMymsg("Schedule Audit End date in Cert Audit  is present and its value is ---'"+actualScheduleEndDate +"' and expected is --'"+scheduleEndDateforCertAudit +"' .");
			} else {
				msg="Schedule Audit End date in Cert Audit  is NOT  matched as its value is ---"+actualScheduleEndDate+"' and expected is --'"+scheduleEndDateforCertAudit +"' .";
				fnpMymsg(msg);
				//throw new Exception("Schedule Audit End date is NOT  present");
				throw new Exception(msg);
			}
			
			
			
			String CertAuditStatusXpath = certExpansionPanelXpath + "//tr/td/label[text()='Audit Status']/../following-sibling::td/label";
			String CertAuditStatus = fnpGetText_NOR(CertAuditStatusXpath);
			
			if (CertAuditStatus.equalsIgnoreCase("SUBMITTED")) {
				fnpMymsg("Cert Audit status is  become 'SUBMITTED'  as its value is ---'"+CertAuditStatus +"' and expected is --'"+"SUBMITTED" +"' .");
			} else {
				msg="Cert Audit status is NOT become 'SUBMITTED'  as its value is ---"+CertAuditStatus+"' and expected is --'"+"SUBMITTED" +"' .";
				fnpMymsg(msg);
				//throw new Exception("Cert Audit status is NOT become 'SUBMITTED'");
				throw new Exception(msg);
			}
			
		
	
	
			
			


			 certInnerTableHeaderXpathForAI = OR.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part1") + (certRow - 1)
					+ OR.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part2");

			int aiTypeInnerTableIndex = fnpFindColumnIndex_NOR(certInnerTableHeaderXpathForAI, "AI Type");
			int aiNumberInnerTableIndex = fnpFindColumnIndex_NOR(certInnerTableHeaderXpathForAI, "AI Number");

			 certInnerTableDataXpathForAI  = OR.getProperty("ISRTaskInnerTableForActionItemsTableData_part1") + (certRow - 1)
					+ OR.getProperty("ISRTaskInnerTableForActionItemsTableData_part2");
			
			
			int performAuditRow = fnpFindRowContainsName_NOR(certInnerTableDataXpathForAI, "PerformAudit", aiTypeInnerTableIndex);
			
			if (performAuditRow>0) {
				fnpMymsg("Perform Audit action item has been generated for Cert Audit task.");
			} else {
				msg="Perform Audit action item has NOT been generated for Cert Audit task.";
				fnpMymsg(msg);
				throw new Exception(msg);
			}
			
			String performAuditAINo = fnpFetchFromTable_NOR(certInnerTableDataXpathForAI, performAuditRow, aiNumberInnerTableIndex);
			fnpMymsg("Perform Audit action item no.   is ---" + performAuditAINo);
			
			
/*
			//*******************************PerformAuditAICompleted Automatically Through Scheduler***********************************************************************************	
			
			int statusInnerTableColIndex = fnpFindColumnIndex_NOR(certInnerTableHeaderXpathForAI, "Status");
			int waitCount = 0;
			String performAuditStatus;


				
			//fnpClick_OR("Complete_Perform_Audit_AI_button");
			
			
			
			fnpRunJob("CompletePerformAuditAIQJob");
			fnpClick_OR("ISRTaskTab");			
			
			
			
			if  (fnpCheckElementDisplayedImmediately_NOR(certOpenIconXpath) ) {				
				fnpClick_NOR(certOpenIconXpath);
			}

			
			fnpCheckError(" after loading ");
					
			waitCount=0;
			int maxWaitTimeInMinutes=50;//25;
			while (true) {
				waitCount++;

				taskAINameIndex = fnpFindColumnIndex("TaskTabTable_HeaderRow", WOISOTaskTab_TaskAISummaryTableColName_TaskAIName);
				certRow = fnpFindRowContainsName("TaskTabTable", WOISOTaskTab_Certification_MainTaskAIName, taskAINameIndex);
				certInnerTableDataXpathForAI  = OR.getProperty("ISRTaskInnerTableForActionItemsTableData_part1") + (certRow - 1)
						+ OR.getProperty("ISRTaskInnerTableForActionItemsTableData_part2");
				performAuditRow = fnpFindRowContainsName_NOR(certInnerTableDataXpathForAI, "PerformAudit", aiTypeInnerTableIndex);
				certInnerTableHeaderXpathForAI = OR.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part1") + (certRow - 1)
						+ OR.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part2");
				
				statusInnerTableColIndex = fnpFindColumnIndex_NOR(certInnerTableHeaderXpathForAI, "Status");
				
				performAuditStatus = fnpFetchFromTable_NOR(certInnerTableDataXpathForAI, performAuditRow, statusInnerTableColIndex);
				fnpMymsg("performAuditStatus ----" + performAuditStatus);
				
				fnpCheckError(" after loading ");

				if (performAuditStatus.equalsIgnoreCase("AI_COMPLETED")) {
					fnpMymsg("PerformAudit status has become to 'AI_COMPLETED' state. ");
					break;
				} else {
					
					
					if (waitCount >(maxWaitTimeInMinutes/2)) {
						//msg="Script waited for approx. more than "+(maxWaitTimeInMinutes)+" min. [after running job from job url] to become 'AI_COMPLETED' state of PerformAudit but it is still showing '" + performAuditStatus + "' .";
						msg="Script waited for approx. more than "+(maxWaitTimeInMinutes)+" min. [waiting to complete automatically through scheduler job run] to become 'AI_COMPLETED' state of PerformAudit but it is still showing '" + performAuditStatus + "' .";
						fnpMymsg(msg);
						throw new Exception(msg);
					}
					
					
					fnpCheckError(" after loading ");
					fnpMymsg(" we are in  waiting loop for PerformAudit become in Ready ----" + waitCount);
					fnpDisplayingMessageInFrame("Now we are in  waiting loop for status of PerformAudit action item to become  Ready (by batch job behind) ----" + ((double)waitCount*2)+" approx. min.", 8);
					//Thread.sleep(1000 * 30 * 1);
					Thread.sleep(1000 * 30 * 4);
					fnpCheckError(" after loading ");
					
//					driver.navigate().refresh();
					

					
					
					fnpClick_OR("ISRInfoTab_EditWO");
					fnpCheckError(" after loading ");
					fnpClick_OR("ISRTaskTab");
					fnpCheckError(" after loading ");
					if (fnpCheckElementDisplayed("EditWOBtn", 5)) {
						fnpClick_OR("EditWOBtn");
					}
					
					
					
					
					

					fnpCheckError(" after loading ");
					fnpClick_OR("ISRTaskTab");
					fnpCheckError(" after loading ");

					if ( (fnpCheckElementDisplayedImmediately_NOR(certOpenIconXpath) & (!(fnpCheckElementDisplayedImmediately_NOR(certInnerTableHeaderXpathForAI))) )  ) {				
						fnpClick_NOR(certOpenIconXpath);
					}

					
				}

				if (waitCount > 60) {

					fnpMymsg("Script waited for approx. 30 min to become 'AI_COMPLETED' state of PerformAudit but it is still showing '" + performAuditStatus + "' .");
					throw new Exception("Script waited for approx. 30 min to become 'AI_COMPLETED' state of PerformAudit but it is still showing '" + performAuditStatus + "' .");

				}

				
				
				if (waitCount > 4) {

					fnpMymsg("Script waited for approx. 2 min to become 'AI_COMPLETED' state of PerformAudit but it is still showing '" + performAuditStatus + "' .");
					throw new Exception("Script waited for approx. 2 min to become 'AI_COMPLETED' state of PerformAudit but it is still showing '" + performAuditStatus + "' .");

				}
				
				
				
				
			}
		//*******************************PerformAuditAICompleted Automatically Through Scheduler***********************************************************************************	
		*/	
			fnpWaitForPerformAuditAIGetCompletedAutomaticallyThroughScheduler(WOISOTaskTab_Certification_MainTaskAIName);
			
			
		
			//}
					
			
			
			driver.close();
			fnpLaunchBrowserAndLogin();
			fnpSwitchUser((String) hashXlData.get("TechReviewer_UserName").trim());	
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
			
			
			
			
			
			
			 aiTypeInnerTableIndex = fnpFindColumnIndex_NOR(certInnerTableHeaderXpathForAI, "AI Type");
			 aiNumberInnerTableIndex = fnpFindColumnIndex_NOR(certInnerTableHeaderXpathForAI, "AI Number");
			
			
			
			
			
			
			int technicalReviewAIRowNo = fnpFindRowContainsName_NOR(certInnerTableDataXpathForAI, WOISOTaskTab_TechnicalReviewAIName, aiTypeInnerTableIndex);
			
			if (technicalReviewAIRowNo>0) {
				fnpMymsg("TechnicalReview action item has been generated for Cert Audit task.");
			} else {
				msg="TechnicalReview action item has NOT been generated for Cert Audit task.";
				fnpMymsg(msg);
				throw new Exception(msg);
			}
			
			String technicalReviewAINo = fnpFetchFromTable_NOR(certInnerTableDataXpathForAI, technicalReviewAIRowNo, aiNumberInnerTableIndex);
			fnpMymsg("TechnicalReview action item no.   is ---" + technicalReviewAINo);
			
			
			//new changes inside this was coded by Satya while I was on suffering from covid
			 if((classNameText.equalsIgnoreCase(ISO9001_SingleTestCaseName))){
					
					fnpClickALinkInATable(technicalReviewAINo);
					fnpWaitForVisible("StartReview_ViewActionItemPage");
					fnpClick_OR("StartReview_ViewActionItemPage");  
								
					fnpLoading_wait();
					fnpClick_OR("RejectButton_ViewActionItemPage");   			
					
					
					
					fnpLoading_wait();
					fnpPFList("ISR_AI_PrimaryRejectionReasonList", "ISR_AI_PrimaryRejectionReasonListOptions", "Other");
					fnpLoading_wait();
					
					fnpType("OR", "AI_RejectAudit_RejectReasonCommentsField", "Automation - Rejected");
					
					fnpClick_OR("AI_RejectAudit_SaveSendToAuditorButton");
					fnpLoading_wait();
					
					
					
					String ReviewerDecision = fnpGetText_OR("AI_ReviewSpecificInformationSection_ReviewerDecision");
					
					if (!(ReviewerDecision.equalsIgnoreCase("REJECTED on"))) {
						msg="Current Status of TechnicalReview action item is NOT 'REJECTED'  b/c its current status is --"+ReviewerDecision;
						fnpMymsg(msg);
						throw new Exception (msg);
					} else {
						fnpMymsg("Current Status of TechnicalReview action item is REJECTED.");
					}
					
					String LoginAs_Auditor=(String) hashXlData.get("Auditor").trim();
					LoginAs_Auditor = LoginAs_Auditor.split(" ")[0].toLowerCase().trim();
					
					fnpSwitchUser(LoginAs_Auditor);	
					
					fnpCommonClickTopMainMenu("Menu", "FirstRefrenceLinkJustBelowToMenu", "AdminMenu_AdminFunctionsSubMenu", "AdminUtilities_RefreshAlertsList");
					
					fnpLoading_wait();
					fnpPFList("AdminUtilities_RefreshAlertsList", "AdminUtilities_RefreshAlertsOptions", (String) hashXlData.get("Refresh_DD_REJECTED"));
					fnpLoading_wait();
					
					
					fnpClick_OR("AdminUtilities_RefreshButton");   			
					
					
					fnpWaitForVisibleHavingMultipleElements("TopMessage3");
					fnpMymsg("Top Message after refresh button click ----" + fnpGetText_OR("TopMessage3"));

					String SuccessfulMsg = fnpGetText_OR("TopMessage3");

					Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"),
							"Top message does not contain 'success' word, so might the alert is not refreshed.");

					fnpMymsg("Alert is successfully refreshed");
					
								
					fnpLoading_wait();
					fnpClick_OR("Home_topLink_id");   			
					fnpLoading_wait();
					
					fnpCommonAlertGeneratedVerification_AI("AI_Rejected", "AI_RejectedAlert_Table_header", "Item", "AI_RejectedAlert_Table", "AI_RejectedAlert_filterTxtBox", technicalReviewAINo);
					
					fnpLoading_wait();
					fnpClickALinkInATable(technicalReviewAINo);
				
					
					fnpWaitTillVisiblityOfElementNClickable_OR("AI_ReviewSpecificInformationSection_AuditReport_Icon");
					
					String iPulse_Original_WindowHandle = driver.getWindowHandle();
					
					fnpClick_OR("AI_ReviewSpecificInformationSection_AuditReport_Icon");   			
					fnpLoading_wait();


					for(int a=0; a<=120; a++){ 
						ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
						Integer tabsCount = tabs.size();
						if(tabsCount==2){
							for (int i = 0; i < tabsCount; i++) {
								if( !(iPulse_Original_WindowHandle.equalsIgnoreCase(tabs.get(i))) ){
									driver.switchTo().window(tabs.get(i));							
									fnpMymsg("Successfully Switch to Oasis Window.");
									break;
								}
							}
							break;
						}else{
							Thread.sleep(1000);
						}
						if(a==120){
							throw new Exception("Oasis window is not getting open after 120 Seconds wait");
						}
					}
							
					fnpCheckErrorUsingPageSource_Oasis();
					fnpWaitForVisible("Oasis_ResubmitButton");
					fnpWaitTillVisiblityOfElementNClickable_OR("Oasis_ResubmitButton");
					fnpCheckErrorUsingPageSource_Oasis();
					fnpGetORObjectX("Oasis_ResubmitButton").click();
					Thread.sleep(5000);

					driver.switchTo().alert().accept();
					Thread.sleep(14000);
					
					driver.switchTo().alert().accept();			
					
					fnpCheckErrorUsingPageSource_Oasis();

					driver.close();
					driver.switchTo().window(iPulse_Original_WindowHandle);
					Thread.sleep(1000);
					driver.navigate().refresh();
					Thread.sleep(3000);
					
					
					String AI_Status = fnpGetText_OR("AI_TechReivew_FirstRow");
					
					if (!(AI_Status.toLowerCase().contains("inprocess"))) {
						msg="Current Status of TechnicalReview action item is NOT 'Inprocess'  b/c its current status is --"+AI_Status;
						fnpMymsg(msg);
						throw new Exception (msg);
					} else {
						fnpMymsg("Current Status of TechnicalReview action item is Inprocess.");
					}
								
					fnpSwitchUser((String) hashXlData.get("TechReviewer_UserName").trim());
					
					fnpCommonClickTopMainMenu("Menu", "FirstRefrenceLinkJustBelowToMenu", "AdminMenu_AdminFunctionsSubMenu", "AdminUtilities_RefreshAlertsList");
					
					fnpLoading_wait();
					fnpPFList("AdminUtilities_RefreshAlertsList", "AdminUtilities_RefreshAlertsOptions", (String) hashXlData.get("Refresh_DD_RESUBMITTED"));
					fnpLoading_wait();
					
					
					fnpClick_OR("AdminUtilities_RefreshButton");   			
					fnpLoading_wait();
					
					fnpWaitForVisibleHavingMultipleElements("TopMessage3");
					fnpMymsg("Top Message after refresh button click ----" + fnpGetText_OR("TopMessage3"));

					SuccessfulMsg = fnpGetText_OR("TopMessage3");

					Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"),
							"Top message does not contain 'success' word, so might the alert is not refreshed.");

					fnpMymsg("Alert is successfully refreshed");
					
								
					
					fnpClick_OR("Home_topLink_id");   			
					fnpLoading_wait();
					
					
					fnpCommonAlertGeneratedVerification_AI("AI_Resubmitted", "AI_ResubmittedAlert_Table_header", "Item", "AI_ResubmittedAlert_Table", "AI_ResubmittedAlert_filterTxtBox", technicalReviewAINo);
					
					fnpLoading_wait();
					fnpClickALinkInATable(technicalReviewAINo);		
					fnpWaitForVisible("ReassignAILkpBtn_OnConsolidatedScreen");
					ClickOn_StartReview_ViewActionItemPage =false;
 
			 }
			

			
			
			
			/*String technicalReviewStatus = fnpFetchFromTable_NOR(certInnerTableDataXpathForAI, technicalReviewAIRowNo, statusInnerTableColIndex);
			
			if (!(technicalReviewStatus.equalsIgnoreCase("AI_CREATED"))) {
				msg="Current Status of TechnicalReview action item is NOT 'AI_CREATED'  b/c its current status is --"+technicalReviewStatus;
				fnpMymsg(msg);
				throw new Exception (msg);
			} else {
				fnpMymsg("Current Status of TechnicalReview action item is AI_CREATED.");
			}
			*/
			
			if ((classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.Cert_StandardsTestCaseName))) {
				/***************************************************************************************/
				
				//fnpProcessAI_ISR_TaskTab_NonConsoladatedScreen_and_basedOnAITypeCol(WOISOTaskType_CertificationAudit,WOISOTaskName_CertificationAudit,WOISOTaskTab_ExtDBUpdateAIType, "Completed");
				//fnpCompleteExdbUpdateAI( certInnerTableHeaderXpathForAI, certInnerTableDataXpathForAI, WOISOTaskType_CertificationAudit,WOISOTaskTab_Certification_MainTaskAIName,"Completed");
				
				/***************************************************************************************/
				//Assign TechReview to cshileds/technical reviewer from excel  19-04-2021
				
				//**********************************************
				
				fnpClickALinkInATable(technicalReviewAINo);
									
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
							if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
								fnpSearchNSelectFirstRadioBtn(1, reassignee, 1);

							}else{
								fnpSearchNSelectFirstRadioBtn(2, reassignee, 1);
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
				fnpSearchWO_and_ReachToTaskTab_OpenDeskExpandIcon_ISR(WOISOTaskName_CertificationAudit);

				
			}else{
				/**************New changes on 21-01-2021************************/
				fnpSwitchUserToProcessTechnicalReviewAI();
				fnpSearchWO_and_ReachToTaskTab_OpenCertExpandIcon_ISR();
				
				/************************************************************/
			}
			

			
			
			
			
			

	
			

			
			
			//Technical Review reverted back
			fnpProcessAI_ISR_TaskTab_1(certInnerTableDataXpathForAI,certInnerTableHeaderXpathForAI,WOISOTaskTab_TechnicalReviewAIName, "Completed","SaveButton__OnConsolidatedScreen_TechnicalReviewAI");
			//fnpProcessAI_ISR_TaskTab_1(certInnerTableDataXpathForAI,certInnerTableHeaderXpathForAI,WOISOTaskTab_TechnicalReviewAIName, "Completed","SaveButton__OnConsolidatedScreen");
			

			fnpVerifyAIStatusInISR(WOISOTaskType_CertificationAudit,WOISOTaskTab_Certification_MainTaskAIName,WOISOTaskTab_TechnicalReviewAIName,"Completed");
			

		
			
			
			if ((classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.Cert_StandardsTestCaseName))) {
				
				
						fnpLaunchBrowserAndLogin();
						fnpSearchWorkOrderOnly(workOrderNo);
						
		
						if (fnpCheckElementDisplayedImmediately("EditWOBtn")) {
							fnpClick_OR("EditWOBtn");
							fnpMymsg("@  ---Here EditWOBtn is visible , so going to click it");
		
						}else{
							fnpMymsg("@  ---Here EditWOBtn is NOT visible , so ignore  it");
						}
						
					
						
						
						fnpClick_OR("ISRTaskTab");
						
						certRow = fnpFindRowContainsName("TaskTabTable", WOISOTaskTab_Certification_MainTaskAIName, taskAINameIndex);
						certOpenIconXpath = OR.getProperty("TaskTabISR_CertAudit_OpenIconPart1") + WOISOTaskType_CertificationAudit+ OR.getProperty("TaskTabISR_CertAudit_OpenIconPart2");
						if ( (fnpCheckElementDisplayedImmediately_NOR(certOpenIconXpath) & (!(fnpCheckElementDisplayedImmediately_NOR(certInnerTableHeaderXpathForAI))) )  ) {				
							fnpClick_NOR(certOpenIconXpath);
						}
												
						auditNoInCertAuditXpath = OR.getProperty("AuditNoAtTaskTab_part1") + (certRow - 1) + OR.getProperty("AuditNoAtTaskTab_part2");		
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

			fnpRunOasisJob("Oasis_RunIQAULN_btn");
			
			fnpLaunchBrowserAndLogin();
			fnpSearchWorkOrderOnly(workOrderNo);
			

			if (fnpCheckElementDisplayedImmediately("EditWOBtn")) {
				fnpClick_OR("EditWOBtn");
				fnpMymsg("@  ---Here EditWOBtn is visible , so going to click it");

			}else{
				fnpMymsg("@  ---Here EditWOBtn is NOT visible , so ignore  it");
			}
			
		
			
			
			fnpClick_OR("ISRTaskTab");
			
			
			if ((classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.ISO9001_SingleTestCaseName))) { 
				
					//taskAINameIndex = fnpFindColumnIndex("TaskTabTable_HeaderRow", WOISOTaskTab_TaskAISummaryTableColName_TaskAIName);
					int TaskRowNo = fnpFindRowContainsName("TaskTabTable", WOISOTaskTab_Certification_MainTaskAIName, taskAINameIndex);
					String innerTableDataXpathForAI = OR.getProperty("ISRTaskInnerTableForActionItemsTableData_part1") + (TaskRowNo - 1)
								+ OR.getProperty("ISRTaskInnerTableForActionItemsTableData_part2");
					String innerTableHeaderXpathForAI = OR.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part1") + (TaskRowNo - 1)
								+ OR.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part2");
						
				
					 String openIconXpath = OR.getProperty("TaskTabISR_Task_OpenIconPart1") + WOISOTaskType_CertificationAudit+ OR.getProperty("TaskTabISR_Task_OpenIconPart2");
					
					
					
					if ( (fnpCheckElementDisplayedImmediately_NOR(openIconXpath) & (!fnpCheckElementDisplayedImmediately_NOR(visitNoInCertAuditXpath)) )  ) {				
						fnpClick_NOR(openIconXpath);
					}
					
					
					certRow=TaskRowNo;
		
					
					taskAINameIndex = fnpFindColumnIndex("TaskTabTable_HeaderRow", WOISOTaskTab_TaskAISummaryTableColName_TaskAIName);
					//certRow = fnpFindRowContainsName("TaskTabTable", "Certification", taskAINameIndex);
					certRow = fnpFindRowContainsName("TaskTabTable", WOISOTaskTab_Certification_MainTaskAIName, taskAINameIndex);
					
					
					certExpansionPanelXpath = OR.getProperty("ISRTaskExpansionPanelGrid_part1") + (certRow - 1) + OR.getProperty("ISRTaskExpansionPanelGrid_part2");
					
					taskAINameIndex = fnpFindColumnIndex("TaskTabTable_HeaderRow", WOISOTaskTab_TaskAISummaryTableColName_TaskAIName);
					
					//certRow = fnpFindRowContainsName("TaskTabTable", "Certification", taskAINameIndex);
					certRow = fnpFindRowContainsName("TaskTabTable", WOISOTaskTab_Certification_MainTaskAIName, taskAINameIndex);
					
					certInnerTableDataXpathForAI = OR.getProperty("ISRTaskInnerTableForActionItemsTableData_part1") + (certRow - 1)
							+ OR.getProperty("ISRTaskInnerTableForActionItemsTableData_part2");
					
					certInnerTableHeaderXpathForAI = OR.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part1") + (certRow - 1)
							+ OR.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part2");
					
					
					 CertAuditStatusXpath = certExpansionPanelXpath + "//tr/td/label[text()='Audit Status']/../following-sibling::td/label";
					 CertAuditStatus = fnpGetText_NOR(CertAuditStatusXpath);
					
					if (CertAuditStatus.equalsIgnoreCase("COMPLETED")) {
						fnpMymsg("Cert Audit status is  become 'COMPLETED'  as its value is ---'"+CertAuditStatus +"' and expected is --'"+"COMPLETED" +"' .");
					} else {
						msg="Cert Audit status is NOT become 'COMPLETED'  as its value is ---"+CertAuditStatus+"' and expected is --'"+"COMPLETED" +"' .";
						fnpMymsg(msg);
						throw new Exception(msg);
					}
			
		
			}

			
			
			
			
		//	fnpClick_OR("WOISR_ProcessWorkFlowBtn");
			
			
			fnpRunJob("ProcessWorkflowQueueQJob");
			fnpClick_OR("ISRTaskTab");
			
			
			String openIconXpath = OR.getProperty("TaskTabISR_Task_OpenIconPart1") + WOISOTaskType_CertificationAudit+ OR.getProperty("TaskTabISR_Task_OpenIconPart2");
			//if  (fnpCheckElementDisplayedImmediately_NOR(openIconXpath)  ) {	
			if  (fnpCheckElementDisplayed_NOR(openIconXpath, 20) ) {
				fnpClick_NOR(openIconXpath);
			}
			
			 int waitCount = 0;
			 int certDecAIRowNo=0;
			int TaskRowNo;
			String innerTableDataXpathForAI;
			String innerTableHeaderXpathForAI;
			//String openIconXpath;
			while (true) {
				
				waitCount++;
				certDecAIRowNo = fnpFindRowContainsName_NOR(certInnerTableDataXpathForAI, "ecision", aiTypeInnerTableIndex);
				
				if (certDecAIRowNo>0) {
					fnpMymsg("Cert Decision action item has been generated for Cert Audit task.");
					break;
				} else {
				//	fnpMymsg("Cert Decision action item has NOT been generated for Cert Audit task.");
				//	throw new Exception("Cert Decision action item has NOT been generated for Cert Audit task.");
				
				

					fnpCheckError(" after loading ");
					fnpMymsg(" we are in  waiting loop for waiting cert decision action item generated.  ----" + waitCount);
					fnpDisplayingMessageInFrame("Now  we are in  waiting loop for waiting cert decision action item generated.----" + ((double)waitCount/2)+" approx. min.", 8);
					Thread.sleep(1000 * 20 * 1);
					fnpCheckError(" after loading ");
					driver.navigate().refresh();
					
					fnpCheckError(" after loading ");					
					fnpClick_OR("ISRTaskTab");
					fnpCheckError(" after loading ");
					
					//certRow = fnpFindRowContainsName("TaskTabTable", WOISOTaskTab_Certification_MainTaskAIName, taskAINameIndex);
					TaskRowNo = fnpFindRowContainsName("TaskTabTable", WOISOTaskTab_Certification_MainTaskAIName, taskAINameIndex);
					innerTableDataXpathForAI = OR.getProperty("ISRTaskInnerTableForActionItemsTableData_part1") + (TaskRowNo - 1)
								+ OR.getProperty("ISRTaskInnerTableForActionItemsTableData_part2");
					innerTableHeaderXpathForAI = OR.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part1") + (TaskRowNo - 1)
								+ OR.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part2");
						
				
					 openIconXpath = OR.getProperty("TaskTabISR_Task_OpenIconPart1") + WOISOTaskType_CertificationAudit+ OR.getProperty("TaskTabISR_Task_OpenIconPart2");
					
					if ( (fnpCheckElementDisplayedImmediately_NOR(openIconXpath) & (!(fnpCheckElementDisplayedImmediately_NOR(innerTableDataXpathForAI))) )  ) {				
						fnpClick_NOR(openIconXpath);
					}
					
				}

				if (waitCount > 60) {
					msg="Script waited for approx. 30 min to generate Cert Decision action item but it is still not generated .";
					fnpMymsg(msg);
					throw new Exception(msg);

				}

			}



			
			
			String certDecAINo = fnpFetchFromTable_NOR(certInnerTableDataXpathForAI, certDecAIRowNo, aiNumberInnerTableIndex);
			fnpMymsg("Cert Decision action item no.   is ---" + certDecAINo);
			
			

			fnpProcessAI_ISR_TaskTab_1(certInnerTableDataXpathForAI,certInnerTableHeaderXpathForAI,"CertDecision", "Completed","SaveBtnOnPerformCertDecisionAI");

			fnpVerifyAIStatusInISR(WOISOTaskType_CertificationAudit,WOISOTaskTab_Certification_MainTaskAIName,"CertDecision","Completed");
			
			
			
			taskAINameIndex = fnpFindColumnIndex("TaskTabTable_HeaderRow", WOISOTaskTab_TaskAISummaryTableColName_TaskAIName);
			TaskRowNo = fnpFindRowContainsName("TaskTabTable", WOISOTaskTab_Certification_MainTaskAIName, taskAINameIndex);
			innerTableDataXpathForAI = OR.getProperty("ISRTaskInnerTableForActionItemsTableData_part1") + (TaskRowNo - 1)
						+ OR.getProperty("ISRTaskInnerTableForActionItemsTableData_part2");
			innerTableHeaderXpathForAI = OR.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part1") + (TaskRowNo - 1)
						+ OR.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part2");
						
			openIconXpath = OR.getProperty("TaskTabISR_Task_OpenIconPart1") + WOISOTaskType_CertificationAudit+ OR.getProperty("TaskTabISR_Task_OpenIconPart2");
			
			if ( (fnpCheckElementDisplayedImmediately_NOR(openIconXpath)  )  ) {				
				fnpClick_NOR(openIconXpath);
				fnpMymsg("Clicked  open icon");
			}
			
			
			aiTypeInnerTableIndex = fnpFindColumnIndex_NOR(innerTableHeaderXpathForAI, "AI Type");
			aiNumberInnerTableIndex = fnpFindColumnIndex_NOR(innerTableHeaderXpathForAI, "AI Number");
			int certIssueAIRowNo = fnpFindRowContainsName_NOR(innerTableDataXpathForAI, "CertIssue", aiTypeInnerTableIndex);
			
			if (certIssueAIRowNo>0) {
				fnpMymsg("Cert Issue action item has been generated for Cert Audit task.");
				
			} else {
				msg="Cert Issue action item has NOT been generated for Cert Audit task.";
				fnpMymsg(msg);
				throw new Exception(msg);
			}
			
			
			
			
			fnpProcessAI_ISR_TaskTab_1(innerTableDataXpathForAI,innerTableHeaderXpathForAI,"CertIssue", "Completed","SaveButton__OnConsolidatedScreen");

			
			fnpVerifyAIStatusInISR(WOISOTaskType_CertificationAudit,WOISOTaskTab_Certification_MainTaskAIName,"CertIssue","Completed");
			
			
						//new changes on 23nd October,2020
			fnpClick_OR("ISR_CorrespondenceTab");
			fnpWaitForVisible("ISR_Corresondence_table_header");
			int initialCorrespondenceCount = fnpCountRowsInTable("ISR_Corresondence_table_data");
			fnpMymsg("***********************Initial Count of rows in Correspondence tab is --"+initialCorrespondenceCount);
			
			fnpClick_OR("ISRTaskTab");
			if ( (fnpCheckElementDisplayedImmediately_NOR(openIconXpath)  )  ) {				
				fnpClick_NOR(openIconXpath);
				fnpMymsg("Clicked  open icon");
			}
			
			
			taskAINameIndex = fnpFindColumnIndex("TaskTabTable_HeaderRow", WOISOTaskTab_TaskAISummaryTableColName_TaskAIName);
			TaskRowNo = fnpFindRowContainsName("TaskTabTable", WOISOTaskTab_Certification_MainTaskAIName, taskAINameIndex);
			innerTableDataXpathForAI = OR.getProperty("ISRTaskInnerTableForActionItemsTableData_part1") + (TaskRowNo - 1)
						+ OR.getProperty("ISRTaskInnerTableForActionItemsTableData_part2");
			innerTableHeaderXpathForAI = OR.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part1") + (TaskRowNo - 1)
						+ OR.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part2");

		//	openIconXpath = OR.getProperty("TaskTabISR_Task_OpenIconPart1") + WOISOTaskType_CertificationAudit+ OR.getProperty("TaskTabISR_Task_OpenIconPart2");
			
			
			
			
			
			
			
			 if((classNameText.equalsIgnoreCase(ISO9001_SingleTestCaseName))){
				 
				 
				 /*					//new changes on 2nd October,2020
					if(  fnpCheckElementDisplayedImmediately_NOR(certOpenIconXpath)   ) {				
						fnpClick_NOR(certOpenIconXpath);
					}
					
					*/
					
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
					
					
					
					fnpClick_OR("EmailCertBtn_id");			
					fnpPFList("ISR_EmailTemplate_PFList", "ISR_EmailTemplate_PFListOptions", hashXlData.get("Email_Template"));
					fnpClick_OR("ISR_EmailTemplateContinueBtn");
					fnpWaitForVisible("ISR_EmailToTxt");
					fnpGetORObjectX("ISR_EmailFromTxt").clear();
					fnpType("OR","ISR_EmailFromTxt",hashXlData.get("From_emailCorrespondence") );
					fnpGetORObjectX("ISR_EmailToTxt").clear();
					fnpType("OR","ISR_EmailToTxt",hashXlData.get("To_emailCorrespondence") );
					fnpClickInDialog_OR("ISR_CorresEmailSendBtn");
					
					
					fnpClick_OR("WorkOrderNoInViewActionItem_id");	
					fnpClick_OR("ISR_CorrespondenceTab");
					
					
				
				
					fnpWaitForVisible("ISR_Corresondence_table_header");
					int rowCount = fnpCountRowsInTable("ISR_Corresondence_table_data");

					//int initialCorrespondenceCount_assumed=0;

					//if (rowCount == 1) {
					//if (rowCount == (initialCorrespondenceCount_assumed+ 1)) {
						if (rowCount == (initialCorrespondenceCount+ 1)) {
						fnpMymsg(" Email has been generated on clickng 'Email Cert' button in cert issue ai because row count in correpondence tab is--"+rowCount);

					} else {
						msg=" Email has NOT been generated on clickng 'Email Cert' button in cert issue ai because row count in correpondence tab is--"+rowCount +" and expected is--"+ (initialCorrespondenceCount+1);
						fnpMymsg(msg);
						throw new Exception(msg);

					}

					
					fnpClick_OR("EditWOBtn");
					
					if (fnpCheckElementPresenceImmediately("ISR_Corres_MessageIcon_id")) {
						fnpMymsg(" Option Column has message Icon present");

					} else {
						msg=" Option Column has NOT message Icon present";
						fnpMymsg(msg);
						throw new Exception(msg);
					}

					
					
					fnpClick_OR("ISR_Correspondence_AddBtn_id");
					
					
					fnpPFList("ISR_CorresTab_EmailTemplate_PFList", "ISR_CorresTab_EmailTemplate_PFListOptions", hashXlData.get("Email_Template"));
					fnpClick_OR("ISR_CorresTab_EmailTemplateContinueBtn");
					fnpWaitForVisible("ISR_CorresTab_EmailToTxt");
					fnpGetORObjectX("ISR_CorresTab_EmailFromTxt").clear();
					fnpType("OR","ISR_CorresTab_EmailFromTxt",hashXlData.get("From_emailCorrespondence") );
					fnpGetORObjectX("ISR_CorresTab_EmailToTxt").clear();
					fnpType("OR","ISR_CorresTab_EmailToTxt",hashXlData.get("To_emailCorrespondence") );
					fnpClickInDialog_OR("ISR_CorresTab_CorresEmailSendBtn");
					
					
					fnpLoading_waitInsideDialogBox();
					fnpLoading_wait();
					
					rowCount = fnpCountRowsInTable("ISR_Corresondence_table_data");

					//if (rowCount == 2) {
					//if (rowCount == (initialCorrespondenceCount_assumed+ 2)) {
					if (rowCount == (initialCorrespondenceCount+ 2)) {
						fnpMymsg(" 2nd Email has been generated using 'Add' button in correspondence tab because row count in correpondence tab is--"+rowCount);

					} else {
						msg="  2nd Email has NOT been generated using 'Add' button in correspondence tab because row count in correpondence tab is--"+rowCount +" and expected is ---"+(initialCorrespondenceCount+ 2);
						fnpMymsg(msg);
						throw new Exception(msg);

					}
					
					
			 }

			
			 if((classNameText.equalsIgnoreCase(ISO9001_SingleTestCaseName))){ 
			 
				fnpMymsg("Now we are going to check FRS Report for Desk audit task");			 
				fnpSearchWorkOrderOnly(workOrderNo);
				if (fnpCheckElementDisplayedImmediately("EditWOBtn")) {
					fnpClick_OR("EditWOBtn");
					fnpMymsg("@  ---Here EditWOBtn is visible , so going to click it");
	
				}else{
					fnpMymsg("@  ---Here EditWOBtn is NOT visible , so ignore  it");
				}
				fnpClick_OR("ISRTaskTab");
				String deskOpenIconXpath = OR.getProperty("TaskTabISR_DeskAudit_OpenIconPart1") + WOISOTaskName_DeskAudit+ OR.getProperty("TaskTabISR_DeskAudit_OpenIconPart2");
				taskAINameIndex = fnpFindColumnIndex("TaskTabTable_HeaderRow", WOISOTaskTab_TaskAISummaryTableColName_TaskAIName);
				int deskRow = fnpFindRow("TaskTabTable", WOISOTaskName_DeskAudit, taskAINameIndex);
				String visitNoInDeskAuditXpath = OR.getProperty("VisitNoAtTaskTab_part1") + (deskRow - 1) + OR.getProperty("VisitNoAtTaskTab_part2");
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
				certOpenIconXpath = OR.getProperty("TaskTabISR_CertAudit_OpenIconPart1") + WOISOTaskType_CertificationAudit+ OR.getProperty("TaskTabISR_CertAudit_OpenIconPart2");
				certRow = fnpFindRowContainsName("TaskTabTable", WOISOTaskTab_Certification_MainTaskAIName, taskAINameIndex);
				visitNoInCertAuditXpath = OR.getProperty("VisitNoAtTaskTab_part1") + (certRow - 1) + OR.getProperty("VisitNoAtTaskTab_part2");
				fnpVerifyFRSReportOnViewVistPage_VerifyAtEnd_inChrome(certOpenIconXpath,visitNoInCertAuditXpath) ;
			
			
			
			 }
			
			
			
			
			
			 if((classNameText.equalsIgnoreCase(ISO9001_SingleTestCaseName))){
					
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
					while (s.contains("No records found") && j < 15) {
						j++;
						Thread.sleep(1000);
						s = fnpFetchFromStSearchTable(1, 1);
					}

					fnpClickALinkInATable(s);
					
					fnpLoading_wait();
								
					fnpCheckFileIsDownloadedOrNotIniPulse(OR.getProperty("FRSBtnOnViewClient")) ;
					
					
			 }
			
			 
			 
			 
			 
			 
			 
			 
			 
			 /****************Only for SQF**********************************/

			 String mainTaskName = WOISOTaskTab_Certification_MainTaskAIName;
			 String WOISOTaskType = WOISOTaskType_CertificationAudit;
				/******12-07-2017*****New change for SQF,  review here in SQF and In ISO we have already checked *********************/
				if((classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.Cert_StandardsTestCaseName))){	
					
					/******12-07-2017*****New change for SQF,  review here in SQF and In ISO we have already checked *********/
					//Assumption we are on Task tab in ISR
					fnpTaskAuditStatusVerify_basedOnTaskName( mainTaskName, WOISOTaskType, "REVIEWED" );
					/*****************************************************************************/
					
					
					// Some code for this 
					//:Check that the system has created the Recertaud task with boundary dates.

					//Do we need to verify the boundary dates or just check that boundary dates are present?
					
					
					
					
					
					
					//code for AULN job
					fnpRunOasisJob("Oasis_RunAULN_btn");
					
					
					
					
					fnpLaunchBrowserAndLogin();
					fnpSearchWorkOrderOnly(workOrderNo);
					

					if (fnpCheckElementDisplayedImmediately("EditWOBtn")) {
						fnpClick_OR("EditWOBtn");
					}
					
								
					
					
					fnpClick_OR("ISRTaskTab");
					
					
					//Assumption we are on Task tab in ISR
					fnpTaskAuditStatusVerify_basedOnTaskName( mainTaskName, WOISOTaskType, "COMPLETED" );
					
					
					
				}
				/**************************************************************************************************/
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			
			
			fnpMymsg("===============================================================================================================");

			// }

		} catch (Throwable t) {
			//isTestPass = true;
			fnpMymsg(" **************************************************************");
			fnpCommonFinalCatchBlock(t, "  CertAuditTask flow  is fail . ", "CertAuditTask");

		}

	}
	
	@Test(priority = 6, dependsOnMethods = { "CertAuditTask" })
	public void CLEANUP_WO_AUTOMATION_DATA() throws Throwable {
		fnpMymsg(" **************************************************************");
		fnpMymsg("ECAP.CLEANUP_WO_AUTOMATION_DATA");
		try {
			if (env.equalsIgnoreCase("Test") ) {
				//fnpCallProcedure_DELETE_WO_AUTOMATION_DATA_ISRProfile(workOrderNo);
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