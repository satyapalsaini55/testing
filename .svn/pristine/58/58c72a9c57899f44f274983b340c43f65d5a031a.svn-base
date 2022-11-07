package nsf.ecap.ISR_suite;

import static nsf.ecap.config.IPULSE_CONSTANTS.*;



import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import nsf.ecap.Proposals_suite.TestSuiteBase_Proposal;
import nsf.ecap.Work_Order_suite.NewNew_Draft_InReview_No_Fac;
import nsf.ecap.Work_Order_suite.TestSuiteBase;
import nsf.ecap.config.IPULSE_CONSTANTS;
import nsf.ecap.sustainability_Suite.TestSuiteBase_Sustain_Suite;
import nsf.ecap.util.ErrorUtil;
import nsf.ecap.util.TestUtil;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.os.WindowsUtils;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.google.common.base.Throwables;
import com.google.common.base.Verify;

public class TestSuiteBase_ISR_suite extends TestSuiteBase {

	
	public static String runmodes[] = null;
	public static int count = -1;
	public static boolean ClickOn_StartReview_ViewActionItemPage = true;
	public static boolean ISR_IntegratedWOComing = false;
	public static boolean standardFacilityTab_WOSI_Panel_present=true;//false;
	//public static String usingGoldenProcedureOrOasis="Oasis";//"golden";//"Oasis" or "golden" value

	public static  ISO9001_Single ISR_BS01;
	//private static String loginCode;
	

	// check if the suite has to be skipped or not
	@BeforeSuite
	public void checkSuiteSkip() throws Throwable {

		fnInitialize();

	//	currentSuiteName = "ISR_suite";
		setCurrentSuiteName("ISR_suite");
		fnpMymsg("             ");
		fnpMymsg("             ");
		fnpMymsg("=========================================================================================================================================");
		fnpMymsg("####################" + currentSuiteName + " Start ############################################################");
		fnpMymsg("Checking Runmode of " + currentSuiteName);
		if (!TestUtil.isSuiteRunnable(suiteXls, currentSuiteName)) {
			fnpMymsg("Skipped " + currentSuiteName + "  as the runmode was set to NO");
			//fnpMymsg("####################  " + currentSuiteName + "  End ############################################################");
			//fnpMymsg("=========================================================================================================================================");
			throw new SkipException("Runmode of " + currentSuiteName + " set to no. So Skipping all tests in " + currentSuiteName);
		}

		if (TestUtil.isSuiteRunnable(suiteXls, currentSuiteName)) {
			browserName = TestUtil.getBrowserName(suiteXls, currentSuiteName);
			excelSiteURL = TestUtil.getExcelSiteURL(suiteXls, currentSuiteName);
			fnpMymsg("Browser Name is :" + browserName);
			fnpMymsg("======================");
			currentSuiteCode = "ISR";

			int rowNum = suiteXls.getCellRowNum("Test Suite", "TSID", currentSuiteName);
			suiteXls.setCellData("Test Suite", "Last_Execution", rowNum, fnReturnDateWithTimeForExcel());

			//currentSuiteXls = ISR_suitexls;
			setCurrentSuiteExcel(ISR_suitexls);
		}
		
		
		
		 //fnpDeleteSMTPMessages();

	}

	// To remove IEDriver.exe from Windows Task Manager
	 @AfterSuite(alwaysRun=true)
	//@AfterSuite
	public void cleanUp() {
		 
/*		 
		loginAs="";
		loginAsFullName="";
		*/
		setLoginAsAndLoginAsFullName_blank();
		
		try {
			referenceSuite = currentSuiteName;
			fnpMymsg("#################### "+currentSuiteName+" Suite End ############################################################");
			if (driver!=null) {
				driver.quit();
			}
			IsBrowserPresentAlready = false;
			killprocess();
		} catch (Throwable t) {
			// Nothing to do
		}

	}

	/**** Common code for clicking Info tab ****/
	public static void fnpCommonClickInfoTabISR() throws Throwable {

		fnpWorkAroundToClickbottomFooter();
		fnpClick_OR("ISRInfoTab_EditWO");

		// fnpLoading_wait();

		if (fnpCheckElementPresenceImmediately("EditWOBtn")) {
			fnpGetORObjectX("EditWOBtn").click();
			fnpLoading_wait();
		}
		// fnpWaitForVisible("InfoTab_WOStatusLabel"); // id is changed here in
		// ISR for Status label

	}

	public HashMap<Integer, String> fnpFindRowsOfWONotDropStatus_usingPaging() throws Throwable {

		String TableDataXpathName = "StandardSearchTable";
		int woStatusColNo = fnpFindColumnIndex("StandardSearchTableHeader", "WO Status");
		int woColNo = fnpFindColumnIndex("StandardSearchTableHeader", "WO #");

		HashMap woNotdropHashMap = new HashMap();
		int actualRow = 0;
		String woNo;

		int retries = 0;
		int pagingLoop = 1;
		List<WebElement> pages;
		try {
			while (true) {

				retries++;
				String xpathRow = OR.getProperty(TableDataXpathName) + "/tr";

				int TotalRowCount = fnpGetORObject_list(xpathRow, Integer.parseInt(CONFIG.getProperty("implicitWaitTime"))).size();
				int rowcountJ = 0;
				while (TotalRowCount == 0) {
					rowcountJ++;
					Thread.sleep(1000);

					TotalRowCount = fnpGetORObject_list(xpathRow, Integer.parseInt(CONFIG.getProperty("implicitWaitTime"))).size();
					if (rowcountJ > 5) {
						break;
					}

				}

				int resultRowNo = 0;
				// int resultColNo = 0;
				String xpathExpression = null;
				String Value = null;
				for (int i = 1; i < TotalRowCount + 1; i++) {
					actualRow++;
					xpathExpression = xpathRow + "[" + i + "]/td[" + woStatusColNo + "]";
					Value = driver.findElement(By.xpath(xpathExpression)).getText().trim();
					if (!(Value.equalsIgnoreCase("DROP"))) {
						// if (!(Value.equalsIgnoreCase("COMPLETE"))) {
						// resultRowNo = i;
						woNo = fnpFetchFromTable("StandardSearchTable", i, woColNo);
						woNotdropHashMap.put((actualRow), woNo);
						// break;

					}

				}

				//System.out.println("@  ---actual row value at this moment is --" + actualRow);

				try {
					driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
					pages = driver.findElements(By.xpath(OR.getProperty("paginationPagesOfMainStandardSearchPage")));
				} catch (Throwable t) {
					pages = null;
				} finally {
					driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);
				}
				//int a = pages.size();
				int a=0 ;
				if (pages!=null) {
					a = pages.size();
				}
				//System.out.println("@  ---pagination pages are --" + a);


				if (pagingLoop < a) {
					//System.out.println("@  --- page '" + (pagingLoop + 1) + "'  is displayed. ");
					fnpMymsg("@  --- page '" + (pagingLoop + 1) + "'  is displayed. ");
					Thread.sleep(1000);
					pages.get(pagingLoop).click();
					Thread.sleep(5000);
					fnpLoading_wait();
					Thread.sleep(1000);
					fnpLoading_wait();

					pagingLoop++;

				} else {
					//System.out.println("@  --- page '" + (pagingLoop + 1) + "'  is NOT displayed .");
					fnpMymsg("@  --- page '" + (pagingLoop + 1) + "'  is NOT displayed. ");
					break;
				}

			}

			return woNotdropHashMap;						
		}

		catch (Throwable t) {

			driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);
			throw t;
		}
		
		finally {
			driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);
		}

	}

	public static void fnpDropWO_common_code() throws Throwable {
		fnpLoading_wait();
		fnpIpulseDuringLoading();
		fnpCommonClickInfoTabISR();

		fnpPFListModify_NOR(OR.getProperty("ISRInfoTab_WOStatusPFList"), "DROP");
		fnpGetORObjectX("ProAddDocSaveBtn").click();
		fnpLoading_wait();
		fnpCheckError("After trying to drop the already created work order, ");
		fnpWaitForVisibleHavingMultipleElements("TopMessage3");
		String SuccessfulMsg = fnpGetText_OR("TopMessage3");
		fnpMymsg("Top Message after changing WO status to DROP in Info tab ----" + SuccessfulMsg);
		Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"),
				"Top message does not contain 'success' word, so might be changing  WO status to DROP in Info tab is NOT successful.");
	}

	
	public static void addingIndustryCodeAndProductDetail() throws Throwable {

		int rowbeforeAddingInIndustryProductTable = fnpCountRowsInTable("IndustryCodeProductTable");
		fnpMymsg("Before adding Industry Code --no. of rows present are -- " + rowbeforeAddingInIndustryProductTable);
		fnpClick_OR("IndustryCodeProductDetail_AddBtn");

		fnpPFList("IndustryCodePFList", "IndustryCodePFListOptions", (String) hashXlData.get("Industry_Code"));
		fnpLoading_waitInsideDialogBox();


		String productsDetailValue = (String) hashXlData.get("Products");
		if ((classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.ISO9001_SingleTestCaseName))) {
			// fnpType("OR", "ProductsTxtBx", productsDetailValue);

			String defaultValue = fnpGetORObjectX("ProductsTxtBx").getText().trim();
			if ((!defaultValue.equalsIgnoreCase("")) & (defaultValue.contains(productsDetailValue))) {
				fnpMymsg("@  - default value is same as expected, so returning back.");
			} else {
				fnpType("OR", "ProductsTxtBx", productsDetailValue);
			}
		} else {

			fnpLogicForSelectingIndustryDetailFromLeftAndTransferToRight(productsDetailValue);

		}

		fnpClick_OR("AddIndustryCodeSave&Return");
		//fnpLoading_waitInsideDialogBox();

		int rowAfterAddingInIndustryProductTable = fnpCountRowsInTable("IndustryCodeProductTable");
		fnpMymsg("After adding Industry Code --no. of rows present are -- " + rowAfterAddingInIndustryProductTable);


	}
	
	
	
	

	public static void addingIndustryCodeAndProductDetail_old(String industryCode, String products) throws Throwable {

		int rowbeforeAddingInIndustryProductTable = fnpCountRowsInTable("IndustryCodeProductTable");
		fnpMymsg("Before adding Industry Code --no. of rows present are -- " + rowbeforeAddingInIndustryProductTable);
		fnpClick_OR("IndustryCodeProductDetail_AddBtn");

		fnpPFList("IndustryCodePFList", "IndustryCodePFListOptions", industryCode);
		// Thread.sleep(5000);
		fnpLoading_waitInsideDialogBox();
		// fnpType("OR", "ProductsTxtBx", (String) hashXlData.get("Products"));

		String productsDetailValue = products;
		if ((classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.ISO9001_SingleTestCaseName))) {
			// fnpType("OR", "ProductsTxtBx", productsDetailValue);

			String defaultValue = fnpGetORObjectX("ProductsTxtBx").getText().trim();
			if ((!defaultValue.equalsIgnoreCase("")) & (defaultValue.contains(productsDetailValue))) {
				fnpMymsg("@  - default value is same as expected, so returning back.");
			} else {
				fnpType("OR", "ProductsTxtBx", productsDetailValue);
			}
		} else {


			
			String combineProductDetailValue="";
			if (fnpCheckElementDisplayed("ProductsTxtBx", 5)) {
				String[] productDetailArray = productsDetailValue.split(":");
				for (int i = 0; i < productDetailArray.length; i++) {
					if (i!=0) {
						combineProductDetailValue=combineProductDetailValue+","+productDetailArray[i];
					} else {
						combineProductDetailValue=combineProductDetailValue+productDetailArray[i];
					}
					}
								
				fnpType("OR", "ProductsTxtBx", combineProductDetailValue);
			  }else{
				fnpLogicForSelectingIndustryDetailFromLeftAndTransferToRight2(productsDetailValue);
			    }
			
			
			
			
			

		}

		fnpClick_OR("AddIndustryCodeSave&Return");


		int rowAfterAddingInIndustryProductTable = fnpCountRowsInTable("IndustryCodeProductTable");
		fnpMymsg("After adding Industry Code --no. of rows present are -- " + rowAfterAddingInIndustryProductTable);


	}
	
	

	public static void addingIndustryCodeAndProductDetail(String industryCode, String products) throws Throwable {

		int rowbeforeAddingInIndustryProductTable = fnpCountRowsInTable("IndustryCodeProductTable");
		fnpMymsg("Before adding Industry Code --no. of rows present are -- " + rowbeforeAddingInIndustryProductTable);
		fnpClick_OR("IndustryCodeProductDetail_AddBtn");

		//fnpPFList("IndustryCodePFList", "IndustryCodePFListOptions", industryCode);
		fnpMultipleSelectionFilter_dropdown_with_Ok_button("AddIndustryCode_dropdown_triangleExpandIcon","AddIndustryCod_Ok_link",industryCode,"IndustryCode_AllLabels_xpath");
		// Thread.sleep(5000);
		fnpLoading_waitInsideDialogBox();
		// fnpType("OR", "ProductsTxtBx", (String) hashXlData.get("Products"));

		String productsDetailValue = products;
		if ((classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.ISO9001_SingleTestCaseName))) {
			// fnpType("OR", "ProductsTxtBx", productsDetailValue);

			String defaultValue = fnpGetORObjectX("ProductsTxtBx").getText().trim();
			if ((!defaultValue.equalsIgnoreCase("")) & (defaultValue.contains(productsDetailValue))) {
				fnpMymsg("@  - default value is same as expected, so returning back.");
			} else {
				fnpType("OR", "ProductsTxtBx", productsDetailValue);
			}
		} else {


			
			String combineProductDetailValue="";
			if (fnpCheckElementDisplayed("ProductsTxtBx", 5)) {
				String[] productDetailArray = productsDetailValue.split(":");
				for (int i = 0; i < productDetailArray.length; i++) {
					if (i!=0) {
						combineProductDetailValue=combineProductDetailValue+","+productDetailArray[i];
					} else {
						combineProductDetailValue=combineProductDetailValue+productDetailArray[i];
					}
					}
								
				fnpType("OR", "ProductsTxtBx", combineProductDetailValue);
			  }else{
				fnpLogicForSelectingIndustryDetailFromLeftAndTransferToRight2(productsDetailValue);
			    }
			
			
			
			
			

		}

		fnpClick_OR("AddIndustryCodeSave&Return");


		int rowAfterAddingInIndustryProductTable = fnpCountRowsInTable("IndustryCodeProductTable");
		fnpMymsg("After adding Industry Code --no. of rows present are -- " + rowAfterAddingInIndustryProductTable);


	}


	
	public static void fnpLogicForSelectingIndustryDetailFromLeftAndTransferToRight(String productsDetailValue) throws Throwable {
		int alreadyNoOfValuesPresentInChosen = fnpFindNoOfElementsWithThisXpath("IndustryProDetailChosenCountRow_xpath");

		String AlreadychosenValuePresent;
		String AlreadyChosenValueChkBxXpath;
		boolean flagForNotRequiredValuePresent = false;
		for (int i = 1; i <= alreadyNoOfValuesPresentInChosen; i++) {
			AlreadychosenValuePresent = fnpGetORObjectX___NOR(OR.getProperty("IndustryProDetailChosenCountRow_xpath") + "[" + i + "]").getAttribute("data-item-value").trim();
			if (productsDetailValue.contains(AlreadychosenValuePresent)) {
				// nothing to do
			} else {
				//sending chosen detail from right to Left
				AlreadyChosenValueChkBxXpath = OR.getProperty("IndustryProDetailChosenPortionChkBx_UsingLiNo_part1") + i
						+ OR.getProperty("IndustryProDetailChosenPortionChkBx_UsingLiNo_part2");
				driver.findElement(By.xpath(AlreadyChosenValueChkBxXpath)).click();
				// Thread.sleep(100);
				Thread.sleep(2000);
				flagForNotRequiredValuePresent = true;
			}
		}

		if (flagForNotRequiredValuePresent) {
			Thread.sleep(5000);
			driver.findElement(By.xpath(OR.getProperty("IndustryProDetailLeftTransferIcon_Xpath"))).click();
			Thread.sleep(5000);
		}

		String[] productDetailArray = productsDetailValue.split(":");
		String availableValueChkBxXpath;
		String availableValueLabelXpath;
		boolean flagForRequiredValuePresent = false;
		for (int i = 0; i < productDetailArray.length; i++) {
			availableValueChkBxXpath = OR.getProperty("IndustryProDetailAvailablePortionChkBx_part1") + productDetailArray[i]
					+ OR.getProperty("IndustryProDetailAvailablePortionChkBx_part2");
			availableValueLabelXpath = OR.getProperty("IndustryProDetailAvailablePortionLabel_part1") + productDetailArray[i]
					+ OR.getProperty("IndustryProDetailAvailablePortionLabel_part2");

			if (fnpCheckElementDisplayedImmediately_NOR(availableValueChkBxXpath)) {
				Thread.sleep(2000);
				// driver.findElement(By.xpath(availableValueChkBxXpath)).click();
				driver.findElement(By.xpath(availableValueLabelXpath)).click();
				Thread.sleep(2000);
				flagForRequiredValuePresent = true;
			}
		}

		if (flagForRequiredValuePresent) {
			Thread.sleep(5000);
			driver.findElement(By.xpath(OR.getProperty("IndustryProDetailRightTransferIcon_Xpath"))).click();
			Thread.sleep(5000);
		}

	}
	
	
	
	
	public static void fnpLogicForSelectingIndustryDetailFromLeftAndTransferToRight2(String productsDetailValue) throws Throwable {
		
		int whileCounter=0;
		int alreadyNoOfValuesPresentInChosen;
		String[] productDetailArray = productsDetailValue.split(":");
		
		
/*		
		String combineProductDetailValue="";
		if (fnpCheckElementDisplayed("ProductsTxtBx", 5)) {
			for (int i = 0; i < productDetailArray.length; i++) {
				if (i!=0) {
					combineProductDetailValue=combineProductDetailValue+","+productDetailArray[i];
				} else {
					combineProductDetailValue=combineProductDetailValue+productDetailArray[i];
				}
				
				
				fnpType("OR", "ProductsTxtBx", combineProductDetailValue);
				break;
				
			}
		}
		
		*/
		
		
		do{
			whileCounter++;
			if (whileCounter>10) {
				fnpMymsg("Unable to shifting/selecting exactly right vlaues (Industry Detail/Products) from right to left after ---"+whileCounter +" times.");
				throw new Exception ("Unable to shifting/selecting exactly right vlaues (Industry Detail/Products) from right to left after ---"+whileCounter +" times.");
			}else{
				fnpMymsg("trying again from shifting/selecting exactly given vlaues (Industry Detail/Products) from right to left ----"+whileCounter);
			}
		 alreadyNoOfValuesPresentInChosen = fnpFindNoOfElementsWithThisXpath("IndustryProDetailChosenCountRow_xpath");

		String AlreadychosenValuePresent;
		String AlreadyChosenValueChkBxXpath;
		boolean flagForNotRequiredValuePresent = false;
		for (int i = 1; i <= alreadyNoOfValuesPresentInChosen; i++) {
			AlreadychosenValuePresent = fnpGetORObjectX___NOR(OR.getProperty("IndustryProDetailChosenCountRow_xpath") + "[" + i + "]").getAttribute("data-item-value").trim();
			if (productsDetailValue.contains(AlreadychosenValuePresent)) {
				// nothing to do
			} else {
				//sending chosen detail from right to Left
				AlreadyChosenValueChkBxXpath = OR.getProperty("IndustryProDetailChosenPortionChkBx_UsingLiNo_part1") + i
						+ OR.getProperty("IndustryProDetailChosenPortionChkBx_UsingLiNo_part2");
				driver.findElement(By.xpath(AlreadyChosenValueChkBxXpath)).click();
				// Thread.sleep(100);
				Thread.sleep(2000);
				flagForNotRequiredValuePresent = true;
			}
		}

		if (flagForNotRequiredValuePresent) {
			Thread.sleep(5000);
			driver.findElement(By.xpath(OR.getProperty("IndustryProDetailLeftTransferIcon_Xpath"))).click();
			Thread.sleep(5000);
		}

		productDetailArray = productsDetailValue.split(":");
		String availableValueChkBxXpath;
		String availableValueLabelXpath;
		boolean flagForRequiredValuePresent = false;
		for (int i = 0; i < productDetailArray.length; i++) {
			availableValueChkBxXpath = OR.getProperty("IndustryProDetailAvailablePortionChkBx_part1") + productDetailArray[i]
					+ OR.getProperty("IndustryProDetailAvailablePortionChkBx_part2");
			availableValueLabelXpath = OR.getProperty("IndustryProDetailAvailablePortionLabel_part1") + productDetailArray[i]
					+ OR.getProperty("IndustryProDetailAvailablePortionLabel_part2");

			if (fnpCheckElementDisplayedImmediately_NOR(availableValueChkBxXpath)) {
				Thread.sleep(2000);
				// driver.findElement(By.xpath(availableValueChkBxXpath)).click();
				driver.findElement(By.xpath(availableValueLabelXpath)).click();
				Thread.sleep(2000);
				flagForRequiredValuePresent = true;
			}
		}

		if (flagForRequiredValuePresent) {
			Thread.sleep(5000);
			driver.findElement(By.xpath(OR.getProperty("IndustryProDetailRightTransferIcon_Xpath"))).click();
			Thread.sleep(5000);
		}

		alreadyNoOfValuesPresentInChosen = fnpFindNoOfElementsWithThisXpath("IndustryProDetailChosenCountRow_xpath");
		}while(alreadyNoOfValuesPresentInChosen!=productDetailArray.length);
		
		
		
	}

	
	
	
	
	

	// Function to Drop WO ISR and delete some Data From DB from DB
	public static void fnpDropWOISRandDeleteSomeDataFromDB(String facilityNo, String standard) throws Throwable {
		//String rate = null;

		fnpMymsg("******************");
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			fnpMymsg("******* Oracle JDBC Driver Registered *******");
		} catch (ClassNotFoundException e) {
			fnpMymsg("****************Oracle JDBC Driver is NOT found *************");
			fnpMymsg("########################################################################");
			e.printStackTrace();
			throw new Exception(e.getMessage());
			// return;
		}

		Connection connection = null;
		try {
		
			connection=fnpGetDBConnection();//donep
			
			Statement stmt = connection.createStatement();

/*			@SuppressWarnings("SQL_PREPARED_STATEMENT_GENERATED_FROM_NONCONSTANT_STRING")
			String updateQuery1 = "UPDATE IQ_APPLICATIONS" + "  SET APP_STATUS = 'DROP' " + " WHERE APP_NO IN "
					+ "  (SELECT B.APP_NO FROM IQ_APP_CSX_XREF a,IQ_APPLICATIONS B WHERE CUSSX_SEQ IN (SELECT seq from CUS_STD_XREF WHERE STD_CODE = '" + standard + "' "
					+ "			AND CUS_SEQ = (SELECT SEQ FROM CUSTOMERS WHERE CODE = '" + facilityNo + "' ))" + "		AND A.APP_NO = B.APP_NO AND B.APP_STATUS <> 'DROP')";
			
			*/
			
			String updateQuery1 = String.format("UPDATE IQ_APPLICATIONS  SET APP_STATUS = 'DROP'  WHERE APP_NO IN   (SELECT B.APP_NO FROM IQ_APP_CSX_XREF a,IQ_APPLICATIONS B WHERE CUSSX_SEQ IN (SELECT seq from CUS_STD_XREF WHERE STD_CODE = '%s' 	AND CUS_SEQ = (SELECT SEQ FROM CUSTOMERS WHERE CODE = '%s' ))	AND A.APP_NO = B.APP_NO AND B.APP_STATUS <> 'DROP')",standard,facilityNo);
			
			
/*			@SuppressWarnings("SQL_PREPARED_STATEMENT_GENERATED_FROM_NONCONSTANT_STRING")
			String deleteQuery2 = "Delete from CUS_FACILITY_SIZE where CUS_SEQ=(select seq from customers where code= '" + facilityNo + "')";
			*/
			String deleteQuery2 = String.format("Delete from CUS_FACILITY_SIZE where CUS_SEQ=(select seq from customers where code= '%s')",facilityNo);

/*			@SuppressWarnings("SQL_PREPARED_STATEMENT_GENERATED_FROM_NONCONSTANT_STRING")
			String deleteQuery3 = "Delete from CUS_EMPLOYEE_COUNT where CUS_SEQ=(select seq from customers where code= '" + facilityNo + "')";
			
		*/	
			String deleteQuery3 = String.format("Delete from CUS_EMPLOYEE_COUNT where CUS_SEQ=(select seq from customers where code= '%s')",facilityNo);
			
			
			
			
			
			
			
/*
			@SuppressWarnings("SQL_PREPARED_STATEMENT_GENERATED_FROM_NONCONSTANT_STRING")
			String updateQuery4 = "update OA_AUDIT_PERFORM ap set AP.AUDIT_STATUS = 'CANCELLED' " + " where AP.AUDIT_NO in ("
					+ " select distinct AP.AUDIT_NO from OA_AUDIT_PERFORM ap, oa_audits a " + " where ap.audit_no = a.audit_no and AP.AUDIT_STATUS = 'CREATED'  and "
					+ " A.IQ_JOB_NO in ( select distinct job_no from IQ_APP_JOBS j, IQ_APP_CSX_XREF acsx where j.app_no = acsx.app_no "
					+ "and acsx.cussx_seq  IN (SELECT seq from CUS_STD_XREF  WHERE STD_CODE = '" + standard + "' " + " AND CUS_SEQ = (SELECT SEQ FROM CUSTOMERS WHERE CODE = '"
					+ facilityNo + "')) ) )";

			*/
			
			String updateQuery4 = String.format("update OA_AUDIT_PERFORM ap set AP.AUDIT_STATUS = 'CANCELLED'  where AP.AUDIT_NO in ( select distinct AP.AUDIT_NO from OA_AUDIT_PERFORM ap, oa_audits a  where ap.audit_no = a.audit_no and AP.AUDIT_STATUS = 'CREATED'  and  A.IQ_JOB_NO in ( select distinct job_no from IQ_APP_JOBS j, IQ_APP_CSX_XREF acsx where j.app_no = acsx.app_no and acsx.cussx_seq  IN (SELECT seq from CUS_STD_XREF  WHERE STD_CODE = '%s'  AND CUS_SEQ = (SELECT SEQ FROM CUSTOMERS WHERE CODE = '%s')) ) )",standard,facilityNo);
			
			
			
			
			//IPM-7189
			//This query is for deleting contacts from PL and CO from table, otherwise drop down list will be blank after adding all in table
			
/*			@SuppressWarnings("SQL_PREPARED_STATEMENT_GENERATED_FROM_NONCONSTANT_STRING")
			String deleteQuery5 = "Delete FROM  NUCLEUS.CUS_STD_CONTACT_XREF " +
					"where CSX_SEQ=(SELECT seq FROM  NUCLEUS.CUS_STD_XREF where CUS_SEQ = (SELECT seq FROM NUCLEUS.customers where code='" + facilityNo + "') and STD_CODE ='" + standard + "')";
			
			*/
			//String deleteQuery5 = String.format("Delete FROM  NUCLEUS.CUS_STD_CONTACT_XREF where CSX_SEQ=(SELECT seq FROM  NUCLEUS.CUS_STD_XREF where CUS_SEQ = (SELECT seq FROM NUCLEUS.customers where code='%s') and STD_CODE ='%s')",facilityNo,standard);
			
			String deleteQuery5 = String.format("Delete FROM  NUCLEUS.CUS_STD_CONTACT_XREF where CSX_SEQ IN (SELECT seq FROM  NUCLEUS.CUS_STD_XREF where CUS_SEQ = (SELECT seq FROM NUCLEUS.customers where code='%s') and STD_CODE ='%s')",facilityNo,standard);
			
			


			
			
			int a = stmt.executeUpdate(updateQuery1);
			fnpMymsg("@  ---updated rows are--" + a);
			connection.commit();

			int b = stmt.executeUpdate(deleteQuery2);
			fnpMymsg("@  ---deleted CUS_FACILITY_SIZE rows are--" + b);
			connection.commit();

			int c = stmt.executeUpdate(deleteQuery3);
			fnpMymsg("@  ---deleted CUS_EMPLOYEE_COUNT rows are--" + c);
			connection.commit();

			int d = stmt.executeUpdate(updateQuery4);
			fnpMymsg("@  ---updated OA_AUDIT_PERFORM rows are--" + d);
			connection.commit();
			
			
			int e = stmt.executeUpdate(deleteQuery5);
			fnpMymsg("@--Delete FROM  NUCLEUS.CUS_STD_CONTACT_XREF rows are--" + e);
			connection.commit();
			
			
			
			
			
			
			
		/*			
		SELECT iacx.app_no FROM IQ.IQ_APP_CSX_XREF iacx, NUCLEUS.CUS_STD_XREF csx,IQ.IQ_APPLICATIONS apps  where iacx.cussx_seq = csx.seq and 
		apps.app_no= iacx.app_no and APPS.APP_STATUS not in ('DROP','COMPLETED') and csx.cus_seq ='127262' and csx.std_code ='R2:2013' ;
		
		
			Also, below query will not impact you whether you are dropping the Ai before or after you dropped WO , it works always but with the STD +FAC combination you have to keep track otherwise query will not work
			--Drop AI on Task level
			update ECAP.AI_ACTION_ITEMS set ACTION_ITEM_STATUS='AI_CANCELLED' , MODIFY_DATE= SYSDATE, MODIFY_USER ='DROP_WO' where ENTITY_ID in (SELECT job_no FROM IQ.IQ_APP_JOBS where app_no='WO#');
			--Drop AI on WO level
			update ECAP.AI_ACTION_ITEMS set ACTION_ITEM_STATUS='AI_CANCELLED' , MODIFY_DATE= SYSDATE, MODIFY_USER ='DROP_WO' where ENTITY_ID = 'WO#';

		
					*/
					
					
			
			String extractWOQuery = String.format("SELECT iacx.app_no FROM IQ.IQ_APP_CSX_XREF iacx, NUCLEUS.CUS_STD_XREF csx,IQ.IQ_APPLICATIONS apps, customers cus   where cus.seq= apps.cus_seq and iacx.cussx_seq = csx.seq and apps.app_no= iacx.app_no and APPS.APP_STATUS not in ('DROP','COMPLETED') and cus.code ='%s' and csx.std_code ='%s' ",facilityNo,standard);
						
			String woNo="";        
	        ResultSet rs = stmt.executeQuery(extractWOQuery);
	        while (rs.next()) {
	             woNo = rs.getString(1);
	        }
	        
	        if (!(woNo.trim().equalsIgnoreCase(""))) {
	        	fnpMymsg(" Going to delete the AI of  Work Order for this combination standard --'"+standard+"' and facility --'"+facilityNo+"' .");
	        	
				String updateQuery11 = String.format("update ECAP.AI_ACTION_ITEMS set ACTION_ITEM_STATUS='AI_CANCELLED' , MODIFY_DATE= SYSDATE, MODIFY_USER ='DROP_WO' where ENTITY_ID in (SELECT job_no FROM IQ.IQ_APP_JOBS where app_no='%s')" ,woNo);

				
				String updateQuery12 = String.format("update ECAP.AI_ACTION_ITEMS set ACTION_ITEM_STATUS='AI_CANCELLED' , MODIFY_DATE= SYSDATE, MODIFY_USER ='DROP_WO' where ENTITY_ID = '%s'" ,woNo);


				 a = stmt.executeUpdate(updateQuery11);
				fnpMymsg("@  ---updated rows  for query '"+updateQuery11+"are--" + a);
				connection.commit();

				 b = stmt.executeUpdate(updateQuery12);
				fnpMymsg("@  ---updated rows  for query '"+updateQuery12+"are--" + b);
				connection.commit();
			 

				
			} else {
				fnpMymsg(" AI of  Work Order for this combination standard --'"+standard+"' and facility --'"+facilityNo+"' is already dropped.");
			}
			
			
		
			

		//	connection.close();

		} catch (SQLException e) {
			fnpMymsg("*********************************************** Either connection Failed or some different error with Database,   Getting Error >>  " + e.getMessage().trim());
			fnpMymsg("=========================================================================================================================================");
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		
		finally{
			connection.close();
		}
		fnpMymsg("******************");

		// return rate;
	}
	
	

	

	
	public static void fnpDropWOISRandDeleteSomeDataFromDB_speciallyForCorporate(String facilityNo, String standard) throws Throwable {
		String rate = null;

		fnpMymsg("******************");
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			fnpMymsg("******* Oracle JDBC Driver Registered *******");
		} catch (ClassNotFoundException e) {
			fnpMymsg("****************Oracle JDBC Driver is NOT found *************");
			fnpMymsg("########################################################################");
			e.printStackTrace();
			throw new Exception(e.getMessage());
			// return;
		}

		Connection connection = null;
		Statement stmt=null;
		try {

			
			connection=fnpGetDBConnection();
			
			stmt = connection.createStatement();
			
/*			
			@SuppressWarnings("SQL_PREPARED_STATEMENT_GENERATED_FROM_NONCONSTANT_STRING")
			String extratWOQuery = "SELECT APP_NO  FROM  iq_applications app, cus_std_xref csdx where app.std_code= csdx.std_code " +
					"and csdx.cus_seq= app.cus_seq and app.app_status != 'DROP' and " +
					"app.std_code='" +standard+"' and app.cus_seq =(SELECT seq FROM  customers where code='" +facilityNo+"')";
			*/
			
			String extratWOQuery = String.format("SELECT APP_NO  FROM  iq_applications app, cus_std_xref csdx where app.std_code= csdx.std_code and csdx.cus_seq= app.cus_seq and app.app_status != 'DROP' and app.std_code='%s' and app.cus_seq =(SELECT seq FROM  customers where code='%s')",standard,facilityNo);
			
			String woNo="";        
	        ResultSet rs = stmt.executeQuery(extratWOQuery);
	        while (rs.next()) {
	             woNo = rs.getString(1);
	        }
	        
	        if (!(woNo.trim().equalsIgnoreCase(""))) {
	        	
				String stringProcedure = "{call  ECAP.EC_SUPPORT.PRO_DROP_WO(?)}";
				CallableStatement cStmt = connection.prepareCall(stringProcedure);

				cStmt.setString(1, woNo);


				// execute COPY_AUDIT_TRANSACTION store procedure
				cStmt.executeUpdate();
				connection.commit();
				
			} else {
				fnpMymsg("Work Order for this combination standard --'"+standard+"' and facility --'"+facilityNo+"' is already dropped.");
			}
			


			//connection.close();

		} catch (SQLException e) {
			fnpMymsg("*********************************************** Either connection Failed or some different error with Database,   Getting Error >>  " + e.getMessage().trim());
			fnpMymsg("=========================================================================================================================================");
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		finally {
	        if (stmt != null) { 
	        	stmt.close(); 
	        	}
	        
	        connection.close();
	    }
		fnpMymsg("******************");

		// return rate;
	}
	
	

	
	public static void fnpDropCampusWOWithAllItsAssociation(String facilityNo, String standard) throws Throwable {
		String rate = null;

		fnpMymsg("******************");
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			fnpMymsg("******* Oracle JDBC Driver Registered *******");
		} catch (ClassNotFoundException e) {
			fnpMymsg("****************Oracle JDBC Driver is NOT found *************");
			fnpMymsg("########################################################################");
			e.printStackTrace();
			throw new Exception(e.getMessage());
			// return;
		}

		Connection connection = null;
		Statement stmt=null;
		try {

			
			connection=fnpGetDBConnection();
			
			stmt = connection.createStatement();
			
			
			@SuppressWarnings("SQL_PREPARED_STATEMENT_GENERATED_FROM_NONCONSTANT_STRING")
			
/*			String extractWOQuery = "SELECT APP_NO  FROM  iq_applications app, cus_std_xref csdx where app.std_code= csdx.std_code " +
					"and csdx.cus_seq= app.cus_seq and app.app_status != 'DROP' and " +
					"app.std_code='" +standard+"' and app.cus_seq =(SELECT seq FROM  customers where code='" +facilityNo+"')";
			
			*/
			
			String extractWOQuery = String.format("SELECT APP_NO  FROM  iq_applications app, cus_std_xref csdx where app.std_code= csdx.std_code and csdx.cus_seq= app.cus_seq and app.app_status != 'DROP' and app.std_code='%s' and app.cus_seq =(SELECT seq FROM  customers where code='%s')",standard,facilityNo);
			
			
			String woNo="";        
	        ResultSet rs = stmt.executeQuery(extractWOQuery);
	        while (rs.next()) {
	             woNo = rs.getString(1);
	        }
	        
	        if (!(woNo.trim().equalsIgnoreCase(""))) {
	        	

				
				// UPDATE IQ_APPLICATIONS SET APP_STATUS ='DROP', DROP_REASON = 'DRC-18' WHERE APP_NO = '"+woNo+"'" ;

				// DELETE FROM IQ_LIST_SUPPORT_SITES WHERE cussx_seq=(SELECT cussx_seq FROM  iq_app_csx_xref where app_no='W0397690');
				


				//String updateQuery1 = "UPDATE IQ_APPLICATIONS SET APP_STATUS ='DROP', DROP_REASON = 'DRC-18' WHERE APP_NO = '"+woNo+"'" ;
				String updateQuery1 = String.format("UPDATE IQ_APPLICATIONS SET APP_STATUS ='DROP', DROP_REASON = 'DRC-18' WHERE APP_NO = '%s'" ,woNo);

				//String deleteQuery2 = "DELETE FROM IQ_LIST_SUPPORT_SITES WHERE cussx_seq=(SELECT cussx_seq FROM  iq_app_csx_xref where app_no='"+woNo+"')" ;
				String deleteQuery2 = String.format("DELETE FROM IQ_LIST_SUPPORT_SITES WHERE cussx_seq=(SELECT cussx_seq FROM  iq_app_csx_xref where app_no='%s')" ,woNo);


				int a = stmt.executeUpdate(updateQuery1);
				fnpMymsg("@  ---updated rows  for query '"+updateQuery1+"are--" + a);
				connection.commit();

				int b = stmt.executeUpdate(deleteQuery2);
				fnpMymsg("@  ---deleted rows  for query '"+deleteQuery2+"are--" + b);
				connection.commit();
			 

				
			} else {
				fnpMymsg("Work Order for this combination standard --'"+standard+"' and facility --'"+facilityNo+"' is already dropped.");
			}
			


			//connection.close();

		} catch (SQLException e) {
			fnpMymsg("*********************************************** Either connection Failed or some different error with Database,   Getting Error >>  " + e.getMessage().trim());
			fnpMymsg("=========================================================================================================================================");
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		finally {
	        if (stmt != null) { 
	        	stmt.close(); 
	        	}
	        
	        connection.close();
	    }
		fnpMymsg("******************");

		// return rate;
	}
	

	// Function to Drop WO ISR and delete some Data From DB from DB
	public static void fnpDropWOISRFromDB(String facilityNo, String standard) throws Throwable {
		String rate = null;

		fnpMymsg("******************");
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			fnpMymsg("******* Oracle JDBC Driver Registered *******");
		} catch (ClassNotFoundException e) {
			fnpMymsg("****************Oracle JDBC Driver is NOT found *************");
			fnpMymsg("########################################################################");
			e.printStackTrace();
			throw new Exception(e.getMessage());
			// return;
		}

		Connection connection = null;
		try {
		
			connection=fnpGetDBConnection();
			Statement stmt = connection.createStatement();

/*			@SuppressWarnings("SQL_PREPARED_STATEMENT_GENERATED_FROM_NONCONSTANT_STRING")
			String updateQuery1 = "UPDATE IQ_APPLICATIONS" + "  SET APP_STATUS = 'DROP' " + " WHERE APP_NO IN "
					+ "  (SELECT B.APP_NO FROM IQ_APP_CSX_XREF a,IQ_APPLICATIONS B WHERE CUSSX_SEQ IN (SELECT seq from CUS_STD_XREF WHERE STD_CODE = '" + standard + "' "
					+ "			AND CUS_SEQ = (SELECT SEQ FROM CUSTOMERS WHERE CODE = '" + facilityNo + "' ))" + "		AND A.APP_NO = B.APP_NO AND B.APP_STATUS <> 'DROP')";
			
			*/

			String updateQuery1 = String.format("UPDATE IQ_APPLICATIONS  SET APP_STATUS = 'DROP'  WHERE APP_NO IN   (SELECT B.APP_NO FROM IQ_APP_CSX_XREF a,IQ_APPLICATIONS B WHERE CUSSX_SEQ IN (SELECT seq from CUS_STD_XREF WHERE STD_CODE = '%s' 	AND CUS_SEQ = (SELECT SEQ FROM CUSTOMERS WHERE CODE = '%s' ))	AND A.APP_NO = B.APP_NO AND B.APP_STATUS <> 'DROP')",standard,facilityNo);
			
			
			int a = stmt.executeUpdate(updateQuery1);
			fnpMymsg("@  ---updated rows are--" + a);
			// //System.out.println("@  ---updated rows are--"+a);
			

			connection.commit();

			//connection.close();

		} catch (SQLException e) {
			fnpMymsg("*********************************************** Either connection Failed or some different error with Database,   Getting Error >>  " + e.getMessage().trim());
			fnpMymsg("=========================================================================================================================================");
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		
		finally{
			connection.close();
		}
		fnpMymsg("******************");

		// return rate;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	/*******
	 * The same method is present in TestSuiteBase, here we are overriding the
	 * method only because of mousehover double click in ISR but double click in
	 * WO BS-16 in revision execution quote create some problem as while
	 * fetching data from table it click the link in snapshot tab.
	 *****/
	/*********** Function to fetch and return data from a Table *************/
	public static String fnpFetchFromTable(String TableXpathKey, int row, int col) throws Exception {

		int retries = 0;
		while (true) {

			try {


				driver.manage().timeouts().implicitlyWait(1, TimeUnit.MILLISECONDS);
				String[] idString = OR.getProperty(TableXpathKey).split("'", -1);
				String CssExpression = "tbody[id='" + idString[1] + "'] tr:nth-child(" + row + ") td:nth-child(" + col + ")";

				Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(30, TimeUnit.SECONDS).pollingEvery(1, TimeUnit.SECONDS)
						.ignoring(org.openqa.selenium.NoSuchElementException.class).ignoring(org.openqa.selenium.StaleElementReferenceException.class).ignoring(org.openqa.selenium.WebDriverException.class)
						.ignoring(org.openqa.selenium.InvalidSelectorException.class);

				wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(CssExpression)));
				
/*				
				Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout((Long.parseLong(CONFIG.getProperty("genMax_waitTime"))), TimeUnit.SECONDS)
						.pollingEvery(1, TimeUnit.SECONDS).ignoring(org.openqa.selenium.StaleElementReferenceException.class)
						.ignoring(org.openqa.selenium.WebDriverException.class).ignoring(org.openqa.selenium.InvalidSelectorException.class)
						.ignoring(org.openqa.selenium.NoSuchElementException.class);
				
				*/
				
				
				String value = driver.findElement(By.cssSelector(CssExpression)).getText().trim();
				return value;

			}

			catch (org.openqa.selenium.StaleElementReferenceException e) {
				if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					retries++;
					fnpMymsg(retries + "In staleElementException catch block of fnpFetchFromTable function for " + TableXpathKey);
					continue;
				} else {
					throw e;
				}
			}

			catch (Throwable t) {
				fnpDesireScreenshot("FetchingDataFail");
				String errorMsg = t.getMessage();
				throw new Exception("Unable to fetch data from  Table having xpath [" + TableXpathKey + "] for row and col [" + row + "," + col
						+ "],plz see screenshot [FetchingDataFail" + SShots + "]  and this is due to --" + errorMsg);

			}

			finally {
				driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);
			}
		}

	}

	public static void fnpCommonCodeForAuditorLookup() throws Throwable {


		fnpClick_OR("AuditorLkpBtnAtVisitPage");
		fnpMymsg("  ---Clicked AuditorLkpBtnAtVisitPage");

		try {
			// 1*1000*60*20); // wait for 20 max minutes
			// fnpMymsg("  ---Start waiting for LookupSearchBtn for approx 20 min ");
			fnpWaitForVisible("LookupSearchBtn", 60 * 20);

		} catch (Throwable t) {
			if (!(fnpCheckElementDisplayedImmediately("LookupSearchBtn"))) {
				throw new Exception("Auditor Lookup is not getting opened even after approx. 20 minutes");
			} else {
				throw t;
			}
		}

		fnpType("OR", "AuditorLkpEmployeeNamefilterTxtBx2InLkp", (String) hashXlData.get("Auditor"));

		fnpLoading_wait();
		fnpIpulseDuringLoading();


		try {

			// 1*1000*10); // wait for 10 seconds
			fnpWaitForVisible("FirstRadioBtnInLkpAFSch", 10);
		} catch (Throwable t) {
			String searchedText = "";
			try {
				searchedText = driver.findElement(By.xpath(".//*[@id='mainForm:lookuptableid_data']/tr[1]/td[1]")).getText();
			} catch (Throwable tsearchText) {
				// nothing to do
			}
			if (searchedText.toLowerCase().contains("no records found")) {
				throw new Exception("The auditor '" + (String) hashXlData.get("Auditor")
						+ "'  which is to be searched in auditor lookup is not present in the lookup after search");
			} else {
				throw t;
			}
		}

		fnpClick_OR_WithoutWait("FirstRadioBtnInLkpAFSch");

		fnpLoading_waitInsideDialogBox();
		fnpLoading_wait();
		Thread.sleep(1000);
		WebElement auditortextboxElement = driver.findElement(By.xpath(OR.getProperty("AuditorTxtBx")));
		String enteredAuditor = auditortextboxElement.getAttribute("value");
		Assert.assertTrue(enteredAuditor.contains((String) hashXlData.get("Auditor")), "Auditor Value is not selected properly from lookup");

		fnpMymsg(" Auditor value is properly selected from  lookup");

	}

	public static void fnpCommonCodeForAuditorLookup(Hashtable<String, String> table) throws Throwable {



		fnpClick_OR("AuditorLkpBtnAtVisitPage");
		// fnpGetORObjectX("AuditorLkpBtnAtVisitPage").click();
		fnpMymsg("  ---Clicked AuditorLkpBtnAtVisitPage");

		try {
			// 1*1000*60*20); // wait for 20 max minutes
			// fnpMymsg("  ---Start waiting for LookupSearchBtn for approx 20 min ");
			fnpWaitForVisible("LookupSearchBtn", 60 * 20);

		} catch (Throwable t) {
			if (!(fnpCheckElementDisplayedImmediately("LookupSearchBtn"))) {
				throw new Exception("Auditor Lookup is not getting opened even after approx. 20 minutes");
			} else {
				throw t;
			}
		}

		fnpType("OR", "AuditorLkpEmployeeNamefilterTxtBx2InLkp", (String) table.get("Auditor"));

		fnpLoading_wait();
		fnpIpulseDuringLoading();


		try {

			// fnpCheckElementDisplayed_NOR(OR.getProperty("FirstRadioBtnInLkpAFSch"),
			// 1*1000*10); // wait for 10 seconds
			fnpWaitForVisible("FirstRadioBtnInLkpAFSch", 10);
		} catch (Throwable t) {
			String searchedText = "";
			try {
				searchedText = driver.findElement(By.xpath(".//*[@id='mainForm:lookuptableid_data']/tr[1]/td[1]")).getText();
			} catch (Throwable tsearchText) {
				// nothing to do
			}
			if (searchedText.toLowerCase().contains("no records found")) {
				throw new Exception("The auditor '" + (String) table.get("Auditor")
						+ "'  which is to be searched in auditor lookup is not present in the lookup after search");
			} else {
				throw t;
			}
		}

		fnpClick_OR_WithoutWait("FirstRadioBtnInLkpAFSch");

		fnpLoading_waitInsideDialogBox();
		fnpLoading_wait();
		Thread.sleep(1000);

		WebElement auditortextboxElement = driver.findElement(By.xpath(OR.getProperty("AuditorTxtBx")));
		String enteredAuditor = auditortextboxElement.getAttribute("value");
		Assert.assertTrue(enteredAuditor.contains((String) table.get("Auditor")), "Auditor Value is not selected properly from lookup");

		fnpMymsg(" Auditor value is properly selected from  lookup");

	}

	//Added by satya as filter are not coming into the lookup
	public static void fnsCommonCodeForAuditorLookup(Hashtable<String, String> table) throws Throwable {



		fnpClick_OR("AuditorLkpBtnAtVisitPage");
		fnpMymsg("  ---Clicked AuditorLkpBtnAtVisitPage");

		try {

			// 1*1000*60*20); // wait for 20 max minutes
			// fnpMymsg("  ---Start waiting for LookupSearchBtn for approx 20 min ");
			fnpWaitForVisible("LookupSearchBtn", 60 * 20);

		} catch (Throwable t) {
			if (!(fnpCheckElementDisplayedImmediately("LookupSearchBtn"))) {
				throw new Exception("Auditor Lookup is not getting opened even after approx. 20 minutes");
			} else {
				throw t;
			}
		}

		fnpType("OR", "AuditorLkpEmployeeNamefilterTxtBx2InLkp_s", (String) table.get("Auditor"));

		
		fnpClick_OR("LookupSearchBtn");
		
		
		fnpLoading_wait();
		fnpIpulseDuringLoading();


		try {

			// fnpCheckElementDisplayed_NOR(OR.getProperty("FirstRadioBtnInLkpAFSch"),
			// 1*1000*10); // wait for 10 seconds
			fnpWaitForVisible("FirstRadioBtnInLkpAFSch", 10);
		} catch (Throwable t) {
			String searchedText = "";
			try {
				searchedText = driver.findElement(By.xpath(".//*[@id='mainForm:lookuptableid_data']/tr[1]/td[1]")).getText();
			} catch (Throwable tsearchText) {
				// nothing to do
			}
			if (searchedText.toLowerCase().contains("no records found")) {
				throw new Exception("The auditor '" + (String) table.get("Auditor")
						+ "'  which is to be searched in auditor lookup is not present in the lookup after search");
			} else {
				throw t;
			}
		}

		fnpClick_OR_WithoutWait("FirstRadioBtnInLkpAFSch");

		fnpLoading_waitInsideDialogBox();
		fnpLoading_wait();
		Thread.sleep(1000);

		WebElement auditortextboxElement = driver.findElement(By.xpath(OR.getProperty("AuditorTxtBx")));
		String enteredAuditor = auditortextboxElement.getAttribute("value");
		Assert.assertTrue(enteredAuditor.contains((String) table.get("Auditor")), "Auditor Value is not selected properly from lookup");

		fnpMymsg(" Auditor value is properly selected from  lookup");

	}
	
	public static void fnpOasisPartInISO(String taskName,String visitNo) throws Throwable {

		// Thread.sleep(5000);
		killprocess();

		String oldBrowser = browserName;
		browserName = "IE";
		// browserName = "chrome";
		fnpLaunchBrowser();
		browserName = oldBrowser;

		//ArrayList<String> listoldtabs = new ArrayList<String>(driver.getWindowHandles());
		String originalHandle = driver.getWindowHandle();

		String oasisURL = excelSiteURL.split("Oasis_Url:")[1];	
				
		String oasisUserName = CONFIG.getProperty("adminUsername");
		String oasisLoginPassword = CONFIG.getProperty("adminPassword");
	
		
		fnpLoginIntoOasis( oasisURL, oasisUserName, oasisLoginPassword);
		
		
		Thread.sleep(1000);
		fnpMouseHover("Oasis_Home_menu");
		Thread.sleep(2000);
		fnpClick_OR_WithoutWait("Oasis_Home_MyCalendarSubmenu");
		Thread.sleep(10000);
		//Thread.sleep(2000);
		fnpCheckErrorUsingPageSource_Oasis();
		fnpWaitForVisible("SwitchCalendarBtnPresentAtPerformAuditPage");

		/*******
		 * For time being Just to find total how much perform audit buttons and
		 * what property they have
		 *****/
		List<WebElement> PerformAuditBtns = driver.findElements(By.xpath(OR.getProperty("Oasis_Calendar_PerformAuditBtn")));

		fnpMymsg("Total Perform Audit buttons =>" + PerformAuditBtns.size());
		

		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		int whileLoopCounterForPerformAuditBtn=0;
		while(true){
			PerformAuditBtns = driver.findElements(By.xpath(OR.getProperty("Oasis_Calendar_PerformAuditBtn")));
			if (PerformAuditBtns.size()>0) {
				break;					
			} else {
				Thread.sleep(1000);
			}
			if (whileLoopCounterForPerformAuditBtn==60) {
				throw new Exception("Failed ==> There is no Perform Audit button on this screen.  Total Perform Audit buttons =>" + PerformAuditBtns.size());
			}
			whileLoopCounterForPerformAuditBtn++;
			
		}
		driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);
		
		
		for (int pai = 0; pai < PerformAuditBtns.size(); pai++) {

			fnpMymsg("@@@   ---this is button no ---" + pai + "----and its onclick " + "property is ---" + PerformAuditBtns.get(pai).getAttribute("onclick"));

		}

		/*******
		 * For time being Just to find total how much perform audit buttons and
		 * what property they have
		 *****/

		String perfomrAuditXpath = OR.getProperty("Oasis_Calendar_PerformAuditBtn2") + " and contains(@onclick,'" + visitNo + "')]";
		fnpMymsg("@@@@   visit no is ----" + visitNo);
		List<WebElement> totalPerformAuditBtns = driver.findElements(By.xpath(perfomrAuditXpath));
		int totalCountPerformBtns= totalPerformAuditBtns.size();;


		totalPerformAuditBtns = driver.findElements(By.xpath(perfomrAuditXpath));

		fnpMymsg("Total Perform Audit buttons =>" + totalPerformAuditBtns.size());

		totalCountPerformBtns = totalPerformAuditBtns.size();
		if (totalCountPerformBtns > 0) {

			 int whileCounter = 0;
			while (whileCounter < totalCountPerformBtns) {
				if (totalPerformAuditBtns.get(whileCounter).isDisplayed() && totalPerformAuditBtns.get(whileCounter).isEnabled()) {
					fnpMymsg("Going to click " + whileCounter + " no. Perform Audit button");
					totalPerformAuditBtns.get(whileCounter).click();
					break;
				}

				whileCounter++;

			}

		}


		if (totalCountPerformBtns==0) {
			fnpMymsg("As there is no Perform Audit button on this page corresponding to " + "this visit no '" + visitNo + "', so unable to proceed further. ");
			throw new Exception("As there is no Perform Audit button on this page corresponding to " + "this visit no '" + visitNo + "', so unable to proceed further. ");
			
		}
		


		fnpCheckErrorUsingPageSource_Oasis();

		fnpGetORObjectX("Oasis_CompleteAuditFormBtn").click();
		Thread.sleep(2000);
		fnpCheckErrorUsingPageSource_Oasis();

		fnpWaitForVisible("Oasis_AuditNo_leftside");// It throws timeout
													// exception b/c button
													// not
		// visible as in oasis it is on same page and little fraction of
		// time page load something like that
		fnpWaitTillVisiblityOfElementNClickable_OR("Oasis_AuditNo_leftside");
		fnpCheckErrorUsingPageSource_Oasis();
		fnpGetORObjectX("Oasis_AuditNo_leftside").click();
		Thread.sleep(2000);
		fnpCheckErrorUsingPageSource_Oasis();
		
		if ((classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.Cert_StandardsTestCaseName))) {
			 if (taskName.toLowerCase().contains("desk")) {
					java.util.List<WebElement> oasisSaveBtn = driver.findElements(By.xpath(OR.getProperty("Oasis_SaveBtn")));
					oasisSaveBtn.get(0).click();
					Thread.sleep(2000);
			 }else{
					
					String valueToBeSelect1 = (String) hashXlData.get("Go_to_Section_1").trim();
					//fnpSimpleSelectList("Oasis_GoToSectionList", valueToBeSelect1); // commented on 20-04-2017  as in Oasis sometime 'Audit Information:Time. Audit Duration per Day' comes and sometime 'Audit Information:Time. Audit Duration per Day-01' comes
					fnpSimpleSelectList_contains("Oasis_GoToSectionList", valueToBeSelect1);
					
					List<WebElement> headingList = driver.findElements(By.xpath(".//td[@class='titleSubPage']"));
					for (int i = 0; i < headingList.size(); i++) {
						try{
						fnpMymsg("  --Going to click Sub heading ---'"+i+"' out of total '"+headingList.size()+"' in Oasis.");				
						headingList.get(i).click();
						fnpMymsg("  --Clicked Sub heading ---'"+i+"' out of total '"+headingList.size()+"' in Oasis.");
						Thread.sleep(1000);
						}catch(Throwable t){
							
						}
					}
					
					
					Thread.sleep(5000);			
					fnpVerifyingSubHeadingForQuestinnairesInOasis(valueToBeSelect1);		
					String Oasis_QuestionNAnswerSets_For_Go_to_Section_1=(String) hashXlData.get("Oasis_QuestionNAnswerSets_For_Go_to_Section_1").trim();
					fnpCommonProcessQuestionnairesSet__In_Oasis(Oasis_QuestionNAnswerSets_For_Go_to_Section_1);			
					java.util.List<WebElement> oasisSaveBtn = driver.findElements(By.xpath(OR.getProperty("Oasis_SaveBtn")));
					oasisSaveBtn.get(0).click();
					Thread.sleep(5000);
					
					
					
					
					
					String valueToBeSelect2 = (String) hashXlData.get("Go_to_Section_2").trim();
					//fnpSimpleSelectList("Oasis_GoToSectionList", valueToBeSelect2); // commented on 20-04-2017  as in Oasis sometime 'Audit Information:Time. Audit Duration per Day' comes and sometime 'Audit Information:Time. Audit Duration per Day-01' comes
					fnpSimpleSelectList_contains("Oasis_GoToSectionList", valueToBeSelect2);
					
					 headingList = driver.findElements(By.xpath(".//td[@class='titleSubPage']"));
					for (int i = 0; i < headingList.size(); i++) {
						try{
						fnpMymsg("  --Going to click Sub heading ---'"+i+"' out of total '"+headingList.size()+"' in Oasis.");				
						headingList.get(i).click();
						fnpMymsg("  --Clicked Sub heading ---'"+i+"' out of total '"+headingList.size()+"' in Oasis.");
						Thread.sleep(1000);
						}catch(Throwable t){
							
						}
					}
					
					
					
					Thread.sleep(5000);			
					fnpVerifyingSubHeadingForQuestinnairesInOasis(valueToBeSelect2);			
					String Oasis_QuestionNAnswerSets_For_Go_to_Section_2=(String) hashXlData.get("Oasis_QuestionNAnswerSets_For_Go_to_Section_2").trim();
					fnpCommonProcessQuestionnairesSet__In_Oasis(Oasis_QuestionNAnswerSets_For_Go_to_Section_2);			
					oasisSaveBtn = driver.findElements(By.xpath(OR.getProperty("Oasis_SaveBtn")));
					oasisSaveBtn.get(0).click();
					Thread.sleep(5000);
					
					
					
					
					
					
					String valueToBeSelect3 = (String) hashXlData.get("Go_to_Section_3").trim();
					//fnpSimpleSelectList("Oasis_GoToSectionList", valueToBeSelect3); // commented on 20-04-2017  as in Oasis sometime 'Audit Information:Time. Audit Duration per Day' comes and sometime 'Audit Information:Time. Audit Duration per Day-01' comes
					fnpSimpleSelectList_contains("Oasis_GoToSectionList", valueToBeSelect3);
					
					 headingList = driver.findElements(By.xpath(".//td[@class='titleSubPage']"));
					for (int i = 0; i < headingList.size(); i++) {
						try{
						fnpMymsg("  --Going to click Sub heading ---'"+i+"' out of total '"+headingList.size()+"' in Oasis.");				
						headingList.get(i).click();
						fnpMymsg("  --Clicked Sub heading ---'"+i+"' out of total '"+headingList.size()+"' in Oasis.");
						Thread.sleep(1000);
						}catch(Throwable t){
							
						}
					}
					
					
					
					Thread.sleep(5000);			
					fnpVerifyingSubHeadingForQuestinnairesInOasis(valueToBeSelect3);			
					String Oasis_QuestionNAnswerSets_For_Go_to_Section_3=(String) hashXlData.get("Oasis_QuestionNAnswerSets_For_Go_to_Section_3").trim();
					fnpCommonProcessQuestionnairesSet__In_Oasis(Oasis_QuestionNAnswerSets_For_Go_to_Section_3);			
					oasisSaveBtn = driver.findElements(By.xpath(OR.getProperty("Oasis_SaveBtn")));
					oasisSaveBtn.get(0).click();
					Thread.sleep(5000);
					
					
					
					
					
					
					String valueToBeSelect4 = (String) hashXlData.get("Go_to_Section_4").trim();
					//fnpSimpleSelectList("Oasis_GoToSectionList", valueToBeSelect4); // commented on 20-04-2017  as in Oasis sometime 'Audit Information:Time. Audit Duration per Day' comes and sometime 'Audit Information:Time. Audit Duration per Day-01' comes
					fnpSimpleSelectList_contains("Oasis_GoToSectionList", valueToBeSelect4);
					
					 headingList = driver.findElements(By.xpath(".//td[@class='titleSubPage']"));
					for (int i = 0; i < headingList.size(); i++) {
						try{
						fnpMymsg("  --Going to click Sub heading ---'"+i+"' out of total '"+headingList.size()+"' in Oasis.");				
						headingList.get(i).click();
						fnpMymsg("  --Clicked Sub heading ---'"+i+"' out of total '"+headingList.size()+"' in Oasis.");
						Thread.sleep(1000);
						}catch(Throwable t){
							
						}

					}
					
					
					
					Thread.sleep(5000);			
					fnpVerifyingSubHeadingForQuestinnairesInOasis(valueToBeSelect4);			
					String Oasis_QuestionNAnswerSets_For_Go_to_Section_4=(String) hashXlData.get("Oasis_QuestionNAnswerSets_For_Go_to_Section_4").trim();
					fnpCommonProcessQuestionnairesSet__In_Oasis(Oasis_QuestionNAnswerSets_For_Go_to_Section_4);			
					oasisSaveBtn = driver.findElements(By.xpath(OR.getProperty("Oasis_SaveBtn")));
					oasisSaveBtn.get(0).click();
					Thread.sleep(5000);
					
			
					
					
					String valueToBeSelect5 = (String) hashXlData.get("Go_to_Section_5").trim();
					//fnpSimpleSelectList("Oasis_GoToSectionList", valueToBeSelect5); // commented on 20-04-2017  as in Oasis sometime 'Audit Information:Time. Audit Duration per Day' comes and sometime 'Audit Information:Time. Audit Duration per Day-01' comes
					fnpSimpleSelectList_contains("Oasis_GoToSectionList", valueToBeSelect5);
					
					 headingList = driver.findElements(By.xpath(".//td[@class='titleSubPage']"));
					for (int i = 0; i < headingList.size(); i++) {
						try{
						fnpMymsg("  --Going to click Sub heading ---'"+i+"' out of total '"+headingList.size()+"' in Oasis.");				
						headingList.get(i).click();
						fnpMymsg("  --Clicked Sub heading ---'"+i+"' out of total '"+headingList.size()+"' in Oasis.");
						Thread.sleep(1000);
						}catch(Throwable t){
							
						}
					}
					
					
					
					Thread.sleep(5000);			
					fnpVerifyingSubHeadingForQuestinnairesInOasis(valueToBeSelect5);			
					String Oasis_QuestionNAnswerSets_For_Go_to_Section_5=(String) hashXlData.get("Oasis_QuestionNAnswerSets_For_Go_to_Section_5").trim();
					fnpCommonProcessQuestionnairesSet__In_Oasis(Oasis_QuestionNAnswerSets_For_Go_to_Section_5);			
					oasisSaveBtn = driver.findElements(By.xpath(OR.getProperty("Oasis_SaveBtn")));
					oasisSaveBtn.get(0).click();
					Thread.sleep(5000);
			 }

			
			
		}else{
			
			 if((classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.ISO9001_SingleTestCaseName))){
				 
				 
				 if (taskName.toLowerCase().contains("desk")) {
						java.util.List<WebElement> oasisSaveBtn = driver.findElements(By.xpath(OR.getProperty("Oasis_SaveBtn")));
						oasisSaveBtn.get(0).click();
						Thread.sleep(2000);
					
				} else {
					 if (taskName.toLowerCase().contains("cert")) {
						 String valueToBeSelect1 = (String) hashXlData.get("Go_to_Section_1").trim();
							//fnpSimpleSelectList("Oasis_GoToSectionList", valueToBeSelect1); // commented on 20-04-2017  as in Oasis sometime 'Audit Information:Time. Audit Duration per Day' comes and sometime 'Audit Information:Time. Audit Duration per Day-01' comes
							fnpSimpleSelectList_contains("Oasis_GoToSectionList", valueToBeSelect1);
							Thread.sleep(5000);

							fnpVerifyingSubHeadingForQuestinnairesInOasis(valueToBeSelect1);
						 				 
							List<WebElement> allNoRadioBtns = driver.findElements(By.xpath(".//input[@type='radio'][contains(@value,'@@No')]"));
							for (int i = 0; i < allNoRadioBtns.size(); i++) {
								allNoRadioBtns.get(i).click();
								Thread.sleep(1000);
							}


							java.util.List<WebElement> oasisSaveBtn = driver.findElements(By.xpath(OR.getProperty("Oasis_SaveBtn")));
							oasisSaveBtn.get(0).click();
							Thread.sleep(5000);
					
							String valueToBeSelect2 = (String) hashXlData.get("Go_to_Section_2").trim();
							fnpSimpleSelectList("Oasis_GoToSectionList", valueToBeSelect2);
						//	fnpSimpleSelectList_contains("Oasis_GoToSectionList", valueToBeSelect2);
							//fnpSimpleSelectList("Oasis_GoToSectionList", valueToBeSelect2); // commented on 20-04-2017  as in Oasis sometime 'Audit Information:Time. Audit Duration per Day' comes and sometime 'Audit Information:Time. Audit Duration per Day-01' comes
							fnpSimpleSelectList_contains("Oasis_GoToSectionList", valueToBeSelect2);
							Thread.sleep(5000);

							fnpVerifyingSubHeadingForQuestinnairesInOasis(valueToBeSelect2);
	
					
							fnpGetORObjectX("Oasis_NACheckBoxes").click();
							Thread.sleep(2000);
		
							
							oasisSaveBtn = driver.findElements(By.xpath(OR.getProperty("Oasis_SaveBtn")));
							oasisSaveBtn.get(0).click();
							Thread.sleep(5000);
					 }else{
						 throw new Exception("task is not defined for processing audit in Oasis in method fnpOasisPartInISO ");
					 }
					
				}
				 



		
			 }
				
		}
		
		
		
		fnpCheckErrorUsingPageSource_Oasis();
		fnpWaitForVisible("Oasis_AuditSubmitBtn");// It throws timeout
													// exception b/c button
													// not
		// visible as in oasis it is on same page and little fraction of
		// time page load something like that
		fnpWaitTillVisiblityOfElementNClickable_OR("Oasis_AuditSubmitBtn");
		// fnpWaitForVisible("Oasis_AuditSubmitBtn");

		fnpCheckErrorUsingPageSource_Oasis();
		fnpGetORObjectX("Oasis_AuditSubmitBtn").click();
		Thread.sleep(5000);

		Alert alert = driver.switchTo().alert();
		alert.accept();
		// driver.switchTo().alert().accept();

		Thread.sleep(4000); // mandatory as page refreshing/changing
							// otherwise throwing error
		// fnpWaitForVisible("Oasis_Audits_VisitSavedSuccessfullyMsg");

		fnpCheckErrorUsingPageSource_Oasis();

		fnpWaitForVisible("Oasis_VisitSuccessfullySubmittedMsg");
		Thread.sleep(2000);
		fnpGetORObjectX("Oasis_ExitBtn").click();
		// Thread.sleep(2000);
		Thread.sleep(5000);

		driver.close();
		// driver.quit();

	}

	
	
	
	
	
	
	
	
	//p
	public static void fnpProcessAI_ISR_TaskTab_1(String dataTableXpathNameNOR,String headerTableXpathNameNOR,String ActionItemName, String statusToChange,String saveBtnInOR) throws Throwable {
		try {
			
			

			// ***************Process the pending Action item***********

			//
			fnpMymsg(" Going to " + statusToChange + "  the pending Action item ---" + ActionItemName);
			fnpWaitForVisible_NotInOR(dataTableXpathNameNOR);

			int ActionItemColIndex = fnpFindColumnIndex_NOR(headerTableXpathNameNOR, "AI Number");
			

			int itemDescColIndex = fnpFindColumnIndex_NOR(headerTableXpathNameNOR, "AI Name");
			
			int actionItemInfoRowNo = fnpFindRow_NOR(dataTableXpathNameNOR, ActionItemName, itemDescColIndex);
			String actionItemNo = fnpFetchFromTable_NOR(dataTableXpathNameNOR, actionItemInfoRowNo, ActionItemColIndex);
			int start = 1;
			while (actionItemNo.isEmpty()) {
				Thread.sleep(1000);
				actionItemNo = fnpFetchFromTable_NOR(dataTableXpathNameNOR, actionItemInfoRowNo, ActionItemColIndex);

				if (start > 50) {
					break;
				}
			}
			Thread.sleep(500);

			
			fnpClickALinkInATable(actionItemNo);
			
			
			fnpMymsg("Clicked on  Action Item '" + ActionItemName + "' in Table having no. is '" + actionItemNo + "' .");

			fnpLoading_wait();
	
			
			
			
			
			
			
			
			if (ActionItemName.equalsIgnoreCase("CertDecision")) {
				fnpClick_OR("WOISR_AIEditBtn_OnConsolidatedScreen");
				fnpClick_OR("ReassignToCertDecisionAI_LKPBtn");
				//fnpSearchNSelectFirstRadioBtn(2, (String) hashXlData.get("AccountManager"), 1);
				String accountManager;
				
				
/*				if (loginAsFullName.equalsIgnoreCase("") | (loginAsFullName==null) ) {
					accountManager=(String) hashXlData.get("AccountManager").trim();
				} else {
					accountManager=loginAsFullName;
				}
				fnpSearchNSelectFirstRadioBtn(2, accountManager, 1);
				
				
				*/
				
				
				
				if (  (loginAsFullName==null) ||loginAsFullName.equalsIgnoreCase("") ) {
					//reassignee=(String) hashXlData.get("AccountManager").trim();
					if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
						accountManager=(String) hashXlData.get("AccountManager_Code").trim();
						fnpSearchNSelectFirstRadioBtn(1, accountManager, 1);
					}else{
						accountManager=(String) hashXlData.get("AccountManager").trim();
						fnpSearchNSelectFirstRadioBtn(2, accountManager, 1);
					}

				} else {
					//reassignee=loginAsFullName;
					if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
						accountManager=loginUser_code;
						fnpSearchNSelectFirstRadioBtn(1, loginUser_code, 1);
					}else{
						accountManager=loginAsFullName;
						fnpSearchNSelectFirstRadioBtn(2, accountManager, 1);
					}
				}
				
				
				
				
				
				
				
				
				
				
				
				
				fnpClick_OR("SaveBtnOnCertDecisionAIConsolidatedScreen");
				fnpWaitForVisibleHavingMultipleElements("TopMessage3");
				String SuccessfulMsg = fnpGetText_OR("TopMessage3");
				fnpMymsg("Top Message after Reassign cert Decision AI ----" + SuccessfulMsg);
				
				if (fnpCheckElementDisplayed("CertDecisionAI_TechnicalReviewConfirm_btn", 10)) {
					fnpMymsg("Tell Vidushi this button is coming--CertDecisionAI_TechnicalReviewConfirm_btn");
					//fnpDesireScreenshot_old("CertDecisionAI_TechnicalReviewConfirm_btn button is coming");
					fnpClick_OR("CertDecisionAI_TechnicalReviewConfirm_btn");
				}
				
				
				fnpClick_OR("PerformAIBtn");
				fnpWaitForVisibleHavingMultipleElements("TopMessage3");
				SuccessfulMsg = fnpGetText_OR("TopMessage3");
				fnpMymsg("Top Message after clicking Perform AI button ----" + SuccessfulMsg);
				fnpClick_OR("MakeDecisionBtn");
				String yesRadioBtnXpath=OR.getProperty("CertDecisionCheckListRadioYesBtn_Firstpart")+"Yes"+OR.getProperty("CertDecisionCheckListRadioYesBtn_Secondpart");
				List<WebElement> allYesRadioBtns = driver.findElements(By.xpath(yesRadioBtnXpath));
				
				if (allYesRadioBtns.size() > 0) {
					//
				}else{
					fnpMymsg("Either Yes radio buttons are not present or xpath changed." );
					throw new Exception("Either Yes radio buttons are not present or xpath changed." );
				}

				for (int i = 0; i < allYesRadioBtns.size(); i++) {
					try{
						fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
						allYesRadioBtns.get(i).click();
						fnpLoading_wait();
						fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
					}catch(Throwable t){
						fnpMymsg("Some exception while clicking on Yes button either due to xpath changes or something other reason. Exception is --"+t.getMessage());
						throw new Exception("Some exception while clicking on Yes button either due to xpath changes or something other reason. Exception is --"+t.getMessage());
					}
					Thread.sleep(2000);
				}
				
				Thread.sleep(2000);
				
				//sometime this drop down not getting refreshed having Yes value, it takes time based on some upper radio buttons.
				try{
					fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
					fnpPFList("DecisionOnCertDecisionAIList", "DecisionOnCertDecisionAIListOptions", "Yes");
					fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
				}catch(Throwable t){
					fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
					fnpPFList("DecisionOnCertDecisionAIList", "DecisionOnCertDecisionAIListOptions", "Yes");
					fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
				}
				Thread.sleep(2000);
			}
			
			
			
			
			
/*			
			if (  (ActionItemName.equalsIgnoreCase("TechnicalReview")) ){		
				
				
			
				fnpLoading_wait();               
				fnpClick_OR("EditWOBtn");
				fnpLoading_wait();
				
				
				
				
				
				fnpWaitForVisible("ReassignAILkpBtn_OnConsolidatedScreen");
				String alreadyAssingee=fnpGetORObjectX("AssignedToLabelValue_OnConsolidatedScreen").getText().trim();				
				String reassignee=null;
				//if (classNameText.equalsIgnoreCase("SQF_Non_Cert")) {
				
				
				
				
				
				if (classNameText.equalsIgnoreCase(Non_Cert_StandardsTestCaseName)) {
					//reassignee=(String) hashXlData.get("Auditor").trim();
					if (  (loginAsFullName==null) ||loginAsFullName.equalsIgnoreCase("") ) {
						//reassignee=(String) hashXlData.get("Auditor").trim();
						if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
							reassignee=(String) hashXlData.get("Auditor_Code").trim();
						}else{
							reassignee=(String) hashXlData.get("Auditor").trim();
						}

						
					} else {
						//reassignee=loginAsFullName;
						if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
							reassignee=loginUser_code;
							
						}else{
							reassignee=loginAsFullName;
						}

						
					}

				} else {
					//reassignee=(String) hashXlData.get("AccountManager").trim();
					//if (  (loginAsFullName==null) ||loginAsFullName.equalsIgnoreCase("") ) {
					if (  (loginAsFullName==null) ||loginAsFullName.equalsIgnoreCase("") ) {
						//reassignee=(String) hashXlData.get("AccountManager").trim();
						fnpMymsg("Debug: 1");
						
						if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
							reassignee=(String) hashXlData.get("AccountManager_Code").trim();
							fnpMymsg("Debug: 2");
						}else{
							reassignee=(String) hashXlData.get("AccountManager").trim();
							fnpMymsg("Debug: 3");
						}

					} else {
						//reassignee=loginAsFullName;
						if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
							reassignee=loginUser_code;
							fnpMymsg("Debug: 4");
						}else{
							reassignee=loginAsFullName;
							fnpMymsg("Debug: 5");
						}
					}
				}	
				
				
				
				
				fnpMymsg("Debug: reassignee value is --"+reassignee);
				
				
				
				
				
				
				
				
				//if (!(alreadyAssingee.equalsIgnoreCase(reassignee))) {		
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
					fnpMymsg("Debug: 8");
					fnpGetORObjectX(saveBtnInOR).click();
					fnpLoading_wait();

				}			
			}
			
			
			
*/			
			
			
			
			
			if (  (ActionItemName.equalsIgnoreCase("TechnicalReview")) ){						
				
				fnpLoading_wait();  
				//This edit button will no longer visible in Technical Review ai from 02-02-2021
				//IPM-14471   --Commented on 02-02-2021
				
/*				fnpClick_OR("EditWOBtn");
				fnpLoading_wait();
				*/
				

			}
			
			
			
			
			
			
			
			
			
			if (  (ActionItemName.equalsIgnoreCase(WOISOTaskTab_Certification_ClientRecordChangeAIName)) ){				
				
				fnpWaitForVisible("ReassignAILkpBtn");
				String alreadyAssingee=fnpGetORObjectX("AssignedToLabelValue").getText().trim();				
				String reassignee=null;
				if (classNameText.equalsIgnoreCase(Non_Cert_StandardsTestCaseName)) {
					//reassignee=(String) hashXlData.get("Auditor").trim();
					
/*					if (loginAsFullName.equalsIgnoreCase("") | (loginAsFullName==null) ) {
						reassignee=(String) hashXlData.get("Auditor").trim();
					} else {
						reassignee=loginAsFullName;
					}
					*/
					
					if (  (loginAsFullName==null) ||loginAsFullName.equalsIgnoreCase("") ) {
						//reassignee=(String) hashXlData.get("Auditor").trim();
						if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
							reassignee=(String) hashXlData.get("Auditor_Code").trim();
						}else{
							reassignee=(String) hashXlData.get("Auditor").trim();
						}

						
					} else {
						//reassignee=loginAsFullName;
						if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
							reassignee=loginUser_code;
						}else{
							reassignee=loginAsFullName;
						}

						
					}
					
					
					
					
					
					
					
				} else {
					//reassignee=(String) hashXlData.get("AccountManager").trim();
/*					
					if (loginAsFullName.equalsIgnoreCase("") | (loginAsFullName==null) ) {
						reassignee=(String) hashXlData.get("AccountManager").trim();
					} else {
						reassignee=loginAsFullName;
					}
					
				*/	
					if (  (loginAsFullName==null) ||loginAsFullName.equalsIgnoreCase("") ) {
						//reassignee=(String) hashXlData.get("AccountManager").trim();
						if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
							reassignee=(String) hashXlData.get("AccountManager_Code").trim();
						}else{
							reassignee=(String) hashXlData.get("AccountManager").trim();
						}

					} else {
						//reassignee=loginAsFullName;
						if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
							reassignee=loginUser_code;
						}else{
							reassignee=loginAsFullName;
						}
					}
					
					
					
					
				}			
				//if (!(alreadyAssingee.equalsIgnoreCase(reassignee))) {	
				if (!(alreadyAssingee.contains(reassignee))) {
					String defaultValue = fnpGetORObjectX("ReassignAITxtBx").getAttribute("value").trim();
					//if (defaultValue.equalsIgnoreCase(reassignee)) {
					if (defaultValue.contains(reassignee)) {
						fnpMymsg("@  - default value is same as expected, so returning back.");
					} else {					
							fnpClick_OR("ReassignAILkpBtn");
		
							fnpMymsg("Just after  click ReassignAILkpBtn");
							fnpMymsg("Just before going to insert value of Account Manger");
							//fnpSearchNSelectFirstRadioBtn(2, reassignee, 1);
							if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
								fnpSearchNSelectFirstRadioBtn(1, reassignee, 1);
							}else{
								fnpSearchNSelectFirstRadioBtn(2, reassignee, 1);
							}

							
							fnpMymsg("Just after  inserting value of Account Manger");

							String EnteredAccountManager = fnpWaitTillTextBoxDontHaveValue("ReassignAITxtBx", "value");
							Assert.assertTrue(EnteredAccountManager.contains(reassignee), "Reassignee is not selected properly from lookup");
							fnpMymsg(" Reassignee is properly selected from client lookup");
					}
					//fnpGetORObjectX(saveBtnInOR).click();
					//fnpLoading_wait();

				}	
				
					
				}
				





			if (statusToChange.equalsIgnoreCase("completed") && ActionItemName.equalsIgnoreCase("TechnicalReview")) {
			

				/************ -Technical Review reverted back****/
				if(ClickOn_StartReview_ViewActionItemPage){
					fnpLoading_wait();
					fnpClick_OR("StartReview_ViewActionItemPage");
				}
				ClickOn_StartReview_ViewActionItemPage=true;
				fnpLoading_wait();
				fnpClick_OR("ReviewCheckList_ViewActionItemPage");
				fnpLoading_wait();
				Thread.sleep(1000);

				/***********************/
				
				
/*				
				*//************ --30 September 2020****//*
				fnpWaitTillVisiblityOfElementNClickable_OR("QuestionnaireOptionsLinkInTechReviewAI");
				fnpClickInDialog_OR("QuestionnaireOptionsLinkInTechReviewAI");
				*//***********************//*
				*/
				
				
				
				//TestSuiteBase_Proposal.fnpCommonProcessQuestionnairesSet( (String) hashXlData.get("QuestionNAnswerSets").trim());
				TestSuiteBase_Proposal.fnpCommonProcessQuestionnairesSet_ISR( );

			
				//fnpWaitForVisible("QuestionnaireDetailsTableInWOISO");

/*				
				*//************ --30 September 2020****//*
				int colIndex = fnpFindColumnIndex("QuestionnaireDetailsTableInWOISO_header", "Unanswered Questions");
				String unAnsweredQuestNo = fnpFetchFromTable("QuestionnaireDetailsTableInWOISO", 1, colIndex);
				*/		
				
				//************ -Technical Review reverted back****//*
				String unAnsweredQuestNo=fnpGetText_OR("UnansweredQuestions");   
				
				
				
				int unAnsweredQuestInt=Integer.parseInt(unAnsweredQuestNo);

				int counter = 0;

				while (unAnsweredQuestInt!=0) {
					Thread.sleep(1000);
					counter = counter + 1;
					//************ -Technical Review reverted back****//*
					unAnsweredQuestNo = fnpGetText_OR("UnansweredQuestions"); 
					//unAnsweredQuestNo = fnpFetchFromTable("QuestionnaireDetailsTableInWOISO", 1, colIndex);
					unAnsweredQuestInt=Integer.parseInt(unAnsweredQuestNo);
					if (counter > Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
						break;
					}
				}

				Assert.assertEquals(unAnsweredQuestInt, 0, "Unanswered Questions are not equal to '0'. ");
				
				
				//************ -Technical Review reverted back****//*
				fnpLoading_wait();
				fnpClick_OR("ApproveButton_ViewActionItemPage");   //  130 Sept2020				
				fnpLoading_wait();

				
				
				

				//Thread.sleep(100000); // 100 seconds ,Pradeep below field is dependent drop down, so putting this wait intentially as iPulse is too slow, plz remove it later
				fnpLoading_wait();
				fnpPFList("ISR_AI_LeadAuditorRatingList", "ISR_AI_LeadAuditorRatingListOptions", (String) hashXlData.get("Lead_Auditor_Rating").trim());
				fnpLoading_wait();
				
			
			//	fnpClick_OR("SaveButton_ApproveAuditPopup");   
				
				//Thread.sleep(100000); // 100 seconds ,Pradeep below field is dependent drop down, so putting this wait intentially as iPulse is too slow, plz remove it later
				
				if ((classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.ISO9001_SingleTestCaseName))) {
					fnpPFList("ISR_AI_ReviewerRecommendationList", "ISR_AI_ReviewerRecommendationListOptions", (String) hashXlData.get("Reviewer_Recommendation").trim());
				}else{
					if ((classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.Cert_StandardsTestCaseName))){
					//fnpPFListByLiNo("ISR_AI_ReviewerRecommendationList", "ISR_AI_ReviewerRecommendationListOptions", 2);
					fnpPFListByLiNo("ISR_AI_ReviewerRecommendationList", "ISR_AI_ReviewerRecommendationListOptions", 1);
					} else {
						//dont know till now
					}
				}
				
				//************ -Technical Review reverted back****//*
				fnpClick_OR("SaveButton_ApproveAuditPopup");   
				
				
			}
			

			
			
			
			if (ActionItemName.equalsIgnoreCase("CertIssue")) {


				String alreadyAssingee=fnpGetORObjectX("AssignedToLabelValue_OnConsolidatedScreen").getText().trim();				
				String reassignee=null;
			
/*				//reassignee=(String) hashXlData.get("AccountManager").trim();
				if (loginAsFullName.equalsIgnoreCase("") | (loginAsFullName==null) ) {p
					reassignee=(String) hashXlData.get("AccountManager").trim();
				} else {
					reassignee=loginAsFullName;
				}
				
				*/
				
				
				if (  (loginAsFullName==null) ||loginAsFullName.equalsIgnoreCase("") ) {
					//reassignee=(String) hashXlData.get("AccountManager").trim();
					if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
						reassignee=(String) hashXlData.get("AccountManager_Code").trim();
					}else{
						reassignee=(String) hashXlData.get("AccountManager").trim();
					}

				} else {
					//reassignee=loginAsFullName;
					if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
						reassignee=loginUser_code;

					}else{
						reassignee=loginAsFullName;
	
					}
				}
				
				
				
				
				
				
				
				//if (!(alreadyAssingee.equalsIgnoreCase(reassignee))) {
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
							}else{
								fnpSearchNSelectFirstRadioBtn(2, reassignee, 1);
							}
							fnpMymsg("Just after  inserting value of Account Manger");

							String enteredAccountManager = fnpWaitTillTextBoxDontHaveValue("ReassignAITxtBx_OnConsolidatedScreen", "value");
							Assert.assertTrue(enteredAccountManager.contains(reassignee), "Reassignee is not selected properly from lookup");
							fnpMymsg(" Reassignee is properly selected from client lookup");
					}
					fnpGetORObjectX(saveBtnInOR).click();
					fnpLoading_wait();

					}
				
				if(!((classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.Cert_StandardsTestCaseName)))){	
					fnpGetORObjectX("HaveyoureviewedthereisasignedcontractChkBx").click();
					Thread.sleep(1000);
					fnpLoading_wait();
				}

				
				
				if (fnpCheckElementDisplayedImmediately("CertLanguagePFList")) {
					fnpPFList("CertLanguagePFList", "CertLanguagePFListOptions", (String) hashXlData.get("Cert_Language"));
				}
				
				
				
				if(classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.Cert_StandardsTestCaseName)){	
					fnpType("OR", "CertIssue_ExternalID_txt", "1111");
					Thread.sleep(2000);
					fnpLoading_wait();
					fnpType("OR", "CertIssue_TaskRequest_txt", "1254");//	W0678008
					Thread.sleep(2000);
					fnpLoading_wait();
					
/*					fnpGetORObjectX("CertIssue_TaskRequest_txt").sendKeys("1254");
					Thread.sleep(20000);
					fnpLoading_wait();
					fnpDoubleClick(fnpGetORObjectX("CertIssue_TaskRequest_Label"));
					Thread.sleep(10000);
					*/
					
					
					
					fnpClick_OR(saveBtnInOR);
					
					
					
					//fnpType("OR", "CertIssue_SQFCertNo_txt", "1234");
				//	fnpType("OR", "CertIssue_IATFCertNo_txt", "1234");
					
				}
				
				
			//	fnpPFList("CertTemplatePFList", "CertTemplatePFListOptions", (String) hashXlData.get("Cert_Template"));
			//	fnpPFListByLiNo("CertTemplatePFList", "CertTemplatePFListOptions", 2);
				fnpPFListByLiNo("CertTemplatePFList", "CertTemplatePFListOptions", 1);
				String selectedCertTemplate=fnpGetText_OR("CertTemplatePFList");
				

				
				
				fnpClick_OR("IssueCertBtn");
				
			
				
				int templateNameColIndex = fnpFindColumnIndex("CertificateNameTable_HeaderRow", "Template Name");
				int certificateDateColIndex = fnpFindColumnIndex("CertificateNameTable_HeaderRow", "Certificate Date");
				//int ourNewlyAddedTemplateRowNo = fnpFindRow("CertificateNameTable", (String) hashXlData.get("Cert_Template"), templateNameColIndex);
				int ourNewlyAddedTemplateRowNo = fnpFindRow("CertificateNameTable", selectedCertTemplate, templateNameColIndex);
				
				String ourNewlyAddedCertificateDate = fnpFetchFromTable("CertificateNameTable", ourNewlyAddedTemplateRowNo, certificateDateColIndex);
				

				SimpleDateFormat sdfmt1 = new SimpleDateFormat("dd-MMM-yyyy");
				java.util.Date dateToCompareInDateFormat = sdfmt1.parse(ourNewlyAddedCertificateDate);
				
				
				
				Date todayDate = new Date();
				String todayDateInStringformat = sdfmt1.format(todayDate);		

				java.util.Date withCompareDateInDateFormat = sdfmt1.parse(todayDateInStringformat);
					
				Date withCompareDate = withCompareDateInDateFormat;
		
				String textMessage="";
				if (dateToCompareInDateFormat.compareTo(withCompareDate) > 0) {
					textMessage = "ourNewlyAddedCertificateDate '"+ourNewlyAddedCertificateDate+"' is after today's date '"+withCompareDate+"', hence failed. ";				
					fnpMymsg(textMessage);
					fnpDisplayingMessageInFrame(textMessage, 5);
					throw new Exception(textMessage);
				} else if (dateToCompareInDateFormat.compareTo(withCompareDate) < 0) {
					textMessage = "ourNewlyAddedCertificateDate '"+ourNewlyAddedCertificateDate+"' is before today's date '"+withCompareDate+"', hence failed. ";	
					fnpMymsg(textMessage);
					fnpDisplayingMessageInFrame(textMessage, 5);
					throw new Exception(textMessage);
				} else {
					textMessage = "ourNewlyAddedCertificateDate '"+ourNewlyAddedCertificateDate+"' is equal to  today's date '"+withCompareDate+"', hence passed. ";	
					fnpMymsg(textMessage);
					fnpDisplayingMessageInFrame(textMessage, 5);
				}

				
				
				
				
				
			}

			
			//************ -Technical Review reverted back****//*
			if(!ActionItemName.equalsIgnoreCase("TechnicalReview")){
				String defaultValue ="";
				if (ActionItemName.equalsIgnoreCase("CertDecision")) {
					defaultValue = fnpGetORObjectX("PerformDecisionAI_ChangeStatusPFList").getText().trim().toLowerCase();
				}else{
					defaultValue = fnpGetORObjectX("ActionItemTab_ChangeStatusPFList").getText().trim().toLowerCase();

				}
				
				
				if (!(ActionItemName.equalsIgnoreCase(WOISOTaskTab_Certification_ClientRecordChangeAIName))) {
					
					if (ActionItemName.equalsIgnoreCase("CertDecision")) {
						fnpWaitForVisible("PerformDecisionAI_ChangeStatusPFList");
						fnpPFList("PerformDecisionAI_ChangeStatusPFList", "PerformDecisionAI_ChangeStatusPFListOptions", "Completed");
					}else{
						fnpWaitForVisible("ActionItemTab_ChangeStatusPFList");
						fnpPFList("ActionItemTab_ChangeStatusPFList", "ActionItemTab_ChangeStatusPFListOptions", statusToChange);
					}
					
					

						fnpGetORObjectX(saveBtnInOR).click();
						fnpLoading_wait();
				


				}else{
					
					
					fnpWaitForVisible("ActionItemTab_ChangeStatusPFList");
					fnpPFList("ActionItemTab_ChangeStatusPFList", "ActionItemTab_ChangeStatusPFListOptions", statusToChange);
					
					
					fnpClick_OR(saveBtnInOR);
				}
				


			//	if (!(ActionItemName.equalsIgnoreCase("CertDecision"))) {
				
						
						/************It will come always for changing from created to completed from today onwards 11-07-2017  against IPM-6594********************/
						
						if (statusToChange.equalsIgnoreCase("completed")) {
							//String defaultValue = fnpGetORObjectX("ActionItemTab_ChangeStatusPFList").getText().trim().toLowerCase();
			/*				
							if ((defaultValue.contains("create")) | (defaultValue.equalsIgnoreCase(""))) {												
								fnpClickInDialog_OR("WOISR_AIConfirmationBtn");
							}
							
			*/			
								fnpClickInDialog_OR("WOISR_AIConfirmationBtn");
							
						}
			
						/***************************************************************************************/
						
			//}
						
						
						
						
						
			}
			

			
			fnpLoading_waitInsideDialogBox();
			fnpLoading_wait();


			fnpCheckError("while Going to " + statusToChange + "  the  Action item ---" + ActionItemName);

			// fnpWaitForVisible("TopMessage3");
			fnpWaitForVisibleHavingMultipleElements("TopMessage3");
			String SuccessfulMsg = fnpGetText_OR("TopMessage3");
			fnpMymsg("Top Message after " + statusToChange + "  the  Action item '" + ActionItemName + "' ----" + SuccessfulMsg);

			Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"), "Top message does not contain 'success' word, so might be action of " + statusToChange
					+ "  the  Action item '" + ActionItemName + "'  is NOT successful.");


			//for coming back from Consolidated screen
			if ( ActionItemName.equalsIgnoreCase("TechnicalReview")|| ( ActionItemName.equalsIgnoreCase("CertIssue")  )  ) {
			//	fnpClick_OR("WO_BackBtn");	//PUKA-3391 new change sprint 12.1	
			//fnpClick_OR("WO_BackBtnOnViewOnlyScreenOnCompletedAI");
				
				
				//************ -Technical Review reverted back***
				
				
				//IPM-14471   --Commented on 02-02-2021
/*				if ( ActionItemName.equalsIgnoreCase("TechnicalReview")){
				//fnpClick_OR("ISR_BackToViewBtn");
				}
				*/
				
				
				
				
				
				fnpClick_OR("WO_BackBtn");	
			}
			
			
			
			
			if (ActionItemName.equalsIgnoreCase("CertDecision")) {			
				fnpClick_OR("WorkOrderNoOnPerformCertDecisionAI");

			}
			
			


		} catch (Throwable t) {

			fnpMymsg("  Failed while completing action item '" + ActionItemName + "  . Error is ---" + t.getMessage());
			fnpDesireScreenshot(ActionItemName + "Failed");

			String stackTrace = Throwables.getStackTraceAsString(t);
			String errorMsg = t.getMessage();
			errorMsg = errorMsg + "\n\n\n\n Failed while completing action item '" + ActionItemName + "    .See screenshot '" + ActionItemName + "Failed.' \n\n" + stackTrace;
			Exception c = new Exception(errorMsg);
			ErrorUtil.addVerificationFailure(c);
			throw c;

		}

	}

	
	
	public static void fnpProcessAI_ISR_TaskTab_1_oldTechnicalReview(String dataTableXpathNameNOR,String headerTableXpathNameNOR,String ActionItemName, String statusToChange,String saveBtnInOR) throws Throwable {
		try {
			
			

			// ***************Process the pending Action item***********

			//
			fnpMymsg(" Going to " + statusToChange + "  the pending Action item ---" + ActionItemName);
			fnpWaitForVisible_NotInOR(dataTableXpathNameNOR);

			int ActionItemColIndex = fnpFindColumnIndex_NOR(headerTableXpathNameNOR, "AI Number");
			

			int itemDescColIndex = fnpFindColumnIndex_NOR(headerTableXpathNameNOR, "AI Name");
			
			int actionItemInfoRowNo = fnpFindRow_NOR(dataTableXpathNameNOR, ActionItemName, itemDescColIndex);
			String actionItemNo = fnpFetchFromTable_NOR(dataTableXpathNameNOR, actionItemInfoRowNo, ActionItemColIndex);
			int start = 1;
			while (actionItemNo.isEmpty()) {
				Thread.sleep(1000);
				actionItemNo = fnpFetchFromTable_NOR(dataTableXpathNameNOR, actionItemInfoRowNo, ActionItemColIndex);

				if (start > 50) {
					break;
				}
			}
			Thread.sleep(500);

			
			fnpClickALinkInATable(actionItemNo);
			
			
			fnpMymsg("Clicked on  Action Item '" + ActionItemName + "' in Table having no. is '" + actionItemNo + "' .");

			fnpLoading_wait();
	
			
			
			
			
			
			
			
			if (ActionItemName.equalsIgnoreCase("CertDecision")) {
				fnpClick_OR("WOISR_AIEditBtn_OnConsolidatedScreen");
				fnpClick_OR("ReassignToCertDecisionAI_LKPBtn");
				//fnpSearchNSelectFirstRadioBtn(2, (String) hashXlData.get("AccountManager"), 1);
				String accountManager;
				
				
/*				if (loginAsFullName.equalsIgnoreCase("") | (loginAsFullName==null) ) {
					accountManager=(String) hashXlData.get("AccountManager").trim();
				} else {
					accountManager=loginAsFullName;
				}
				fnpSearchNSelectFirstRadioBtn(2, accountManager, 1);
				
				
				*/
				
				
				
				if (  (loginAsFullName==null) ||loginAsFullName.equalsIgnoreCase("") ) {
					//reassignee=(String) hashXlData.get("AccountManager").trim();
					if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
						accountManager=(String) hashXlData.get("AccountManager_Code").trim();
						fnpSearchNSelectFirstRadioBtn(1, accountManager, 1);
					}else{
						accountManager=(String) hashXlData.get("AccountManager").trim();
						fnpSearchNSelectFirstRadioBtn(2, accountManager, 1);
					}

				} else {
					//reassignee=loginAsFullName;
					if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
						accountManager=loginUser_code;
						fnpSearchNSelectFirstRadioBtn(1, loginUser_code, 1);
					}else{
						accountManager=loginAsFullName;
						fnpSearchNSelectFirstRadioBtn(2, accountManager, 1);
					}
				}
				
				
				
				
				
				
				
				
				
				
				
				
				fnpClick_OR("SaveBtnOnCertDecisionAIConsolidatedScreen");
				fnpWaitForVisibleHavingMultipleElements("TopMessage3");
				String SuccessfulMsg = fnpGetText_OR("TopMessage3");
				fnpMymsg("Top Message after Reassign cert Decision AI ----" + SuccessfulMsg);
				fnpClick_OR("PerformAIBtn");
				fnpWaitForVisibleHavingMultipleElements("TopMessage3");
				SuccessfulMsg = fnpGetText_OR("TopMessage3");
				fnpMymsg("Top Message after clicking Perform AI button ----" + SuccessfulMsg);
				fnpClick_OR("MakeDecisionBtn");
				String yesRadioBtnXpath=OR.getProperty("CertDecisionCheckListRadioYesBtn_Firstpart")+"Yes"+OR.getProperty("CertDecisionCheckListRadioYesBtn_Secondpart");
				List<WebElement> allYesRadioBtns = driver.findElements(By.xpath(yesRadioBtnXpath));
				
				if (allYesRadioBtns.size() > 0) {
					//
				}else{
					fnpMymsg("Either Yes radio buttons are not present or xpath changed." );
					throw new Exception("Either Yes radio buttons are not present or xpath changed." );
				}

				for (int i = 0; i < allYesRadioBtns.size(); i++) {
					try{
						fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
						allYesRadioBtns.get(i).click();
						fnpLoading_wait();
						fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
					}catch(Throwable t){
						fnpMymsg("Some exception while clicking on Yes button either due to xpath changes or something other reason. Exception is --"+t.getMessage());
						throw new Exception("Some exception while clicking on Yes button either due to xpath changes or something other reason. Exception is --"+t.getMessage());
					}
					Thread.sleep(2000);
				}
				
				Thread.sleep(2000);
				
				//sometime this drop down not getting refreshed having Yes value, it takes time based on some upper radio buttons.
				try{
					fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
					fnpPFList("DecisionOnCertDecisionAIList", "DecisionOnCertDecisionAIListOptions", "Yes");
					fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
				}catch(Throwable t){
					fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
					fnpPFList("DecisionOnCertDecisionAIList", "DecisionOnCertDecisionAIListOptions", "Yes");
					fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
				}
				Thread.sleep(2000);
			}
			
			
			
			
			
			
			if (  (ActionItemName.equalsIgnoreCase("TechnicalReview")) ){		
				
/*				
				*//****  Technical Review reverted back*****//*
				fnpLoading_wait();               
				fnpClick_OR("EditWOBtn");
				fnpLoading_wait();
				
				*/
				
				
				
				fnpWaitForVisible("ReassignAILkpBtn_OnConsolidatedScreen");
				String alreadyAssingee=fnpGetORObjectX("AssignedToLabelValue_OnConsolidatedScreen").getText().trim();				
				String reassignee=null;
				//if (classNameText.equalsIgnoreCase("SQF_Non_Cert")) {
				
				
				
				
				
				if (classNameText.equalsIgnoreCase(Non_Cert_StandardsTestCaseName)) {
					//reassignee=(String) hashXlData.get("Auditor").trim();
					if (  (loginAsFullName==null) ||loginAsFullName.equalsIgnoreCase("") ) {
						//reassignee=(String) hashXlData.get("Auditor").trim();
						if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
							reassignee=(String) hashXlData.get("Auditor_Code").trim();
						}else{
							reassignee=(String) hashXlData.get("Auditor").trim();
						}

						
					} else {
						//reassignee=loginAsFullName;
						if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
							reassignee=loginUser_code;
							
						}else{
							reassignee=loginAsFullName;
						}

						
					}

				} else {
					//reassignee=(String) hashXlData.get("AccountManager").trim();
					//if (  (loginAsFullName==null) ||loginAsFullName.equalsIgnoreCase("") ) {
					if (  (loginAsFullName==null) ||loginAsFullName.equalsIgnoreCase("") ) {
						//reassignee=(String) hashXlData.get("AccountManager").trim();
						if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
							reassignee=(String) hashXlData.get("AccountManager_Code").trim();
						}else{
							reassignee=(String) hashXlData.get("AccountManager").trim();
						}

					} else {
						//reassignee=loginAsFullName;
						if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
							reassignee=loginUser_code;
						}else{
							reassignee=loginAsFullName;
						}
					}
				}	
				
				
				
				
				
				
				
				
				
				
				
				
				
				//if (!(alreadyAssingee.equalsIgnoreCase(reassignee))) {		
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
							}else{
								fnpSearchNSelectFirstRadioBtn(2, reassignee, 1);
							}
							fnpMymsg("Just after  inserting value of Account Manger");

							String EnteredAccountManager = fnpWaitTillTextBoxDontHaveValue("ReassignAITxtBx_OnConsolidatedScreen", "value");
							Assert.assertTrue(EnteredAccountManager.contains(reassignee), "Reassignee is not selected properly from lookup");
							fnpMymsg(" Reassignee is properly selected from client lookup");
					}
					fnpGetORObjectX(saveBtnInOR).click();
					fnpLoading_wait();

				}			
			}
			
			
			
			
			if (  (ActionItemName.equalsIgnoreCase(WOISOTaskTab_Certification_ClientRecordChangeAIName)) ){				
				
				fnpWaitForVisible("ReassignAILkpBtn");
				String alreadyAssingee=fnpGetORObjectX("AssignedToLabelValue").getText().trim();				
				String reassignee=null;
				if (classNameText.equalsIgnoreCase(Non_Cert_StandardsTestCaseName)) {
					//reassignee=(String) hashXlData.get("Auditor").trim();
					
/*					if (loginAsFullName.equalsIgnoreCase("") | (loginAsFullName==null) ) {
						reassignee=(String) hashXlData.get("Auditor").trim();
					} else {
						reassignee=loginAsFullName;
					}
					*/
					
					if (  (loginAsFullName==null) ||loginAsFullName.equalsIgnoreCase("") ) {
						//reassignee=(String) hashXlData.get("Auditor").trim();
						if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
							reassignee=(String) hashXlData.get("Auditor_Code").trim();
						}else{
							reassignee=(String) hashXlData.get("Auditor").trim();
						}

						
					} else {
						//reassignee=loginAsFullName;
						if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
							reassignee=loginUser_code;
						}else{
							reassignee=loginAsFullName;
						}

						
					}
					
					
					
					
					
					
					
				} else {
					//reassignee=(String) hashXlData.get("AccountManager").trim();
/*					
					if (loginAsFullName.equalsIgnoreCase("") | (loginAsFullName==null) ) {
						reassignee=(String) hashXlData.get("AccountManager").trim();
					} else {
						reassignee=loginAsFullName;
					}
					
				*/	
					if (  (loginAsFullName==null) ||loginAsFullName.equalsIgnoreCase("") ) {
						//reassignee=(String) hashXlData.get("AccountManager").trim();
						if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
							reassignee=(String) hashXlData.get("AccountManager_Code").trim();
						}else{
							reassignee=(String) hashXlData.get("AccountManager").trim();
						}

					} else {
						//reassignee=loginAsFullName;
						if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
							reassignee=loginUser_code;
						}else{
							reassignee=loginAsFullName;
						}
					}
					
					
					
					
				}			
				//if (!(alreadyAssingee.equalsIgnoreCase(reassignee))) {	
				if (!(alreadyAssingee.contains(reassignee))) {
					String defaultValue = fnpGetORObjectX("ReassignAITxtBx").getAttribute("value").trim();
					//if (defaultValue.equalsIgnoreCase(reassignee)) {
					if (defaultValue.contains(reassignee)) {
						fnpMymsg("@  - default value is same as expected, so returning back.");
					} else {					
							fnpClick_OR("ReassignAILkpBtn");
		
							fnpMymsg("Just after  click ReassignAILkpBtn");
							fnpMymsg("Just before going to insert value of Account Manger");
							//fnpSearchNSelectFirstRadioBtn(2, reassignee, 1);
							if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
								fnpSearchNSelectFirstRadioBtn(1, reassignee, 1);
							}else{
								fnpSearchNSelectFirstRadioBtn(2, reassignee, 1);
							}

							
							fnpMymsg("Just after  inserting value of Account Manger");

							String EnteredAccountManager = fnpWaitTillTextBoxDontHaveValue("ReassignAITxtBx", "value");
							Assert.assertTrue(EnteredAccountManager.contains(reassignee), "Reassignee is not selected properly from lookup");
							fnpMymsg(" Reassignee is properly selected from client lookup");
					}
					//fnpGetORObjectX(saveBtnInOR).click();
					//fnpLoading_wait();

				}	
				
					
				}
				





			if (statusToChange.equalsIgnoreCase("completed") && ActionItemName.equalsIgnoreCase("TechnicalReview")) {
			
/*
				*//************ -Technical Review reverted back****//*
				fnpLoading_wait();
				fnpClick_OR("StartReview_ViewActionItemPage");
				fnpLoading_wait();
				fnpClick_OR("ReviewCheckList_ViewActionItemPage");
				fnpLoading_wait();
				Thread.sleep(1000);

				*//***********************//*
				*/
				
				
				/************ --30 September 2020****/
				fnpWaitTillVisiblityOfElementNClickable_OR("QuestionnaireOptionsLinkInTechReviewAI");
				fnpClickInDialog_OR("QuestionnaireOptionsLinkInTechReviewAI");
				/***********************/
				
				
				
				
				//TestSuiteBase_Proposal.fnpCommonProcessQuestionnairesSet( (String) hashXlData.get("QuestionNAnswerSets").trim());
				TestSuiteBase_Proposal.fnpCommonProcessQuestionnairesSet_ISR( );

			
				//fnpWaitForVisible("QuestionnaireDetailsTableInWOISO");

				
				/************ --30 September 2020****/
				int colIndex = fnpFindColumnIndex("QuestionnaireDetailsTableInWOISO_header", "Unanswered Questions");
				String unAnsweredQuestNo = fnpFetchFromTable("QuestionnaireDetailsTableInWOISO", 1, colIndex);
						
				
				//************ -Technical Review reverted back****//*
				//String unAnsweredQuestNo=fnpGetText_OR("UnansweredQuestions");   
				
				
				
				int unAnsweredQuestInt=Integer.parseInt(unAnsweredQuestNo);

				int counter = 0;

				while (unAnsweredQuestInt!=0) {
					Thread.sleep(1000);
					counter = counter + 1;
					//************ -Technical Review reverted back****//*
					//unAnsweredQuestNo = fnpGetText_OR("UnansweredQuestions"); 
					unAnsweredQuestNo = fnpFetchFromTable("QuestionnaireDetailsTableInWOISO", 1, colIndex);
					unAnsweredQuestInt=Integer.parseInt(unAnsweredQuestNo);
					if (counter > Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
						break;
					}
				}

				Assert.assertEquals(unAnsweredQuestInt, 0, "Unanswered Questions are not equal to '0'. ");
				
				
				//************ -Technical Review reverted back****//*
/*				fnpLoading_wait();
				fnpClick_OR("ApproveButton_ViewActionItemPage");   //  130 Sept2020				
				fnpLoading_wait();

				*/
				
				

				//Thread.sleep(100000); // 100 seconds ,Pradeep below field is dependent drop down, so putting this wait intentially as iPulse is too slow, plz remove it later
				fnpLoading_wait();
				fnpPFList("ISR_AI_LeadAuditorRatingList", "ISR_AI_LeadAuditorRatingListOptions", (String) hashXlData.get("Lead_Auditor_Rating").trim());
				fnpLoading_wait();
				
			
			//	fnpClick_OR("SaveButton_ApproveAuditPopup");   
				
				//Thread.sleep(100000); // 100 seconds ,Pradeep below field is dependent drop down, so putting this wait intentially as iPulse is too slow, plz remove it later
				
				if ((classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.ISO9001_SingleTestCaseName))) {
					fnpPFList("ISR_AI_ReviewerRecommendationList", "ISR_AI_ReviewerRecommendationListOptions", (String) hashXlData.get("Reviewer_Recommendation").trim());
				}else{
					if ((classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.Cert_StandardsTestCaseName))){
					//fnpPFListByLiNo("ISR_AI_ReviewerRecommendationList", "ISR_AI_ReviewerRecommendationListOptions", 2);
					fnpPFListByLiNo("ISR_AI_ReviewerRecommendationList", "ISR_AI_ReviewerRecommendationListOptions", 1);
					} else {
						//dont know till now
					}
				}
				
				//************ -Technical Review reverted back****//*
				//fnpClick_OR("SaveButton_ApproveAuditPopup");   
				
				
			}
			

			
			
			
			if (ActionItemName.equalsIgnoreCase("CertIssue")) {


				String alreadyAssingee=fnpGetORObjectX("AssignedToLabelValue_OnConsolidatedScreen").getText().trim();				
				String reassignee=null;
			
/*				//reassignee=(String) hashXlData.get("AccountManager").trim();
				if (loginAsFullName.equalsIgnoreCase("") | (loginAsFullName==null) ) {p
					reassignee=(String) hashXlData.get("AccountManager").trim();
				} else {
					reassignee=loginAsFullName;
				}
				
				*/
				
				
				if (  (loginAsFullName==null) ||loginAsFullName.equalsIgnoreCase("") ) {
					//reassignee=(String) hashXlData.get("AccountManager").trim();
					if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
						reassignee=(String) hashXlData.get("AccountManager_Code").trim();
					}else{
						reassignee=(String) hashXlData.get("AccountManager").trim();
					}

				} else {
					//reassignee=loginAsFullName;
					if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
						reassignee=loginUser_code;

					}else{
						reassignee=loginAsFullName;
	
					}
				}
				
				
				
				
				
				
				
				//if (!(alreadyAssingee.equalsIgnoreCase(reassignee))) {
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
							}else{
								fnpSearchNSelectFirstRadioBtn(2, reassignee, 1);
							}
							fnpMymsg("Just after  inserting value of Account Manger");

							String enteredAccountManager = fnpWaitTillTextBoxDontHaveValue("ReassignAITxtBx_OnConsolidatedScreen", "value");
							Assert.assertTrue(enteredAccountManager.contains(reassignee), "Reassignee is not selected properly from lookup");
							fnpMymsg(" Reassignee is properly selected from client lookup");
					}
					fnpGetORObjectX(saveBtnInOR).click();
					fnpLoading_wait();

					}
				
				
				if(!((classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.Cert_StandardsTestCaseName)))){	
					fnpGetORObjectX("HaveyoureviewedthereisasignedcontractChkBx").click();
					Thread.sleep(1000);
					fnpLoading_wait();
				}

				
				
				if (fnpCheckElementDisplayedImmediately("CertLanguagePFList")) {
					fnpPFList("CertLanguagePFList", "CertLanguagePFListOptions", (String) hashXlData.get("Cert_Language"));
				}
				
				
				if(classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.Cert_StandardsTestCaseName)){	
					fnpType("OR", "CertIssue_ExternalID_txt", "1111");
					Thread.sleep(2000);
					fnpLoading_wait();
					fnpType("OR", "CertIssue_TaskRequest_txt", "1254");//	W0678008
					Thread.sleep(2000);
					fnpLoading_wait();
					
/*					fnpGetORObjectX("CertIssue_TaskRequest_txt").sendKeys("1254");
					Thread.sleep(20000);
					fnpLoading_wait();
					fnpDoubleClick(fnpGetORObjectX("CertIssue_TaskRequest_Label"));
					Thread.sleep(10000);
					*/
					
					
					
					fnpClick_OR(saveBtnInOR);
					
					
					
					//fnpType("OR", "CertIssue_SQFCertNo_txt", "1234");
				//	fnpType("OR", "CertIssue_IATFCertNo_txt", "1234");
					
				}
				
				
				
			//	fnpPFList("CertTemplatePFList", "CertTemplatePFListOptions", (String) hashXlData.get("Cert_Template"));
			//	fnpPFListByLiNo("CertTemplatePFList", "CertTemplatePFListOptions", 2);
				fnpPFListByLiNo("CertTemplatePFList", "CertTemplatePFListOptions", 1);
				String selectedCertTemplate=fnpGetText_OR("CertTemplatePFList");
				

				
				fnpClick_OR("IssueCertBtn");
				
			
				
				int templateNameColIndex = fnpFindColumnIndex("CertificateNameTable_HeaderRow", "Template Name");
				int certificateDateColIndex = fnpFindColumnIndex("CertificateNameTable_HeaderRow", "Certificate Date");
				//int ourNewlyAddedTemplateRowNo = fnpFindRow("CertificateNameTable", (String) hashXlData.get("Cert_Template"), templateNameColIndex);
				int ourNewlyAddedTemplateRowNo = fnpFindRow("CertificateNameTable", selectedCertTemplate, templateNameColIndex);
				
				String ourNewlyAddedCertificateDate = fnpFetchFromTable("CertificateNameTable", ourNewlyAddedTemplateRowNo, certificateDateColIndex);
				

				SimpleDateFormat sdfmt1 = new SimpleDateFormat("dd-MMM-yyyy");
				java.util.Date dateToCompareInDateFormat = sdfmt1.parse(ourNewlyAddedCertificateDate);
				
				
				
				Date todayDate = new Date();
				String todayDateInStringformat = sdfmt1.format(todayDate);		

				java.util.Date withCompareDateInDateFormat = sdfmt1.parse(todayDateInStringformat);
					
				Date withCompareDate = withCompareDateInDateFormat;
		
				String textMessage="";
				if (dateToCompareInDateFormat.compareTo(withCompareDate) > 0) {
					textMessage = "ourNewlyAddedCertificateDate '"+ourNewlyAddedCertificateDate+"' is after today's date '"+withCompareDate+"', hence failed. ";				
					fnpMymsg(textMessage);
					fnpDisplayingMessageInFrame(textMessage, 5);
					throw new Exception(textMessage);
				} else if (dateToCompareInDateFormat.compareTo(withCompareDate) < 0) {
					textMessage = "ourNewlyAddedCertificateDate '"+ourNewlyAddedCertificateDate+"' is before today's date '"+withCompareDate+"', hence failed. ";	
					fnpMymsg(textMessage);
					fnpDisplayingMessageInFrame(textMessage, 5);
					throw new Exception(textMessage);
				} else {
					textMessage = "ourNewlyAddedCertificateDate '"+ourNewlyAddedCertificateDate+"' is equal to  today's date '"+withCompareDate+"', hence passed. ";	
					fnpMymsg(textMessage);
					fnpDisplayingMessageInFrame(textMessage, 5);
				}

				
				
				
				
				
			}

			
			//************ -Technical Review reverted back****//*
			//if(!ActionItemName.equalsIgnoreCase("TechnicalReview")){
				String defaultValue ="";
				if (ActionItemName.equalsIgnoreCase("CertDecision")) {
					defaultValue = fnpGetORObjectX("PerformDecisionAI_ChangeStatusPFList").getText().trim().toLowerCase();
				}else{
					defaultValue = fnpGetORObjectX("ActionItemTab_ChangeStatusPFList").getText().trim().toLowerCase();

				}
				
				
				if (!(ActionItemName.equalsIgnoreCase(WOISOTaskTab_Certification_ClientRecordChangeAIName))) {
					
					if (ActionItemName.equalsIgnoreCase("CertDecision")) {
						fnpWaitForVisible("PerformDecisionAI_ChangeStatusPFList");
						fnpPFList("PerformDecisionAI_ChangeStatusPFList", "PerformDecisionAI_ChangeStatusPFListOptions", "Completed");
					}else{
						fnpWaitForVisible("ActionItemTab_ChangeStatusPFList");
						fnpPFList("ActionItemTab_ChangeStatusPFList", "ActionItemTab_ChangeStatusPFListOptions", statusToChange);
					}
					
					

						fnpGetORObjectX(saveBtnInOR).click();
						fnpLoading_wait();
				


				}else{
					
					
					fnpWaitForVisible("ActionItemTab_ChangeStatusPFList");
					fnpPFList("ActionItemTab_ChangeStatusPFList", "ActionItemTab_ChangeStatusPFListOptions", statusToChange);
					
					
					fnpClick_OR(saveBtnInOR);
				}
				


			//	if (!(ActionItemName.equalsIgnoreCase("CertDecision"))) {
				
						
						/************It will come always for changing from created to completed from today onwards 11-07-2017  against IPM-6594********************/
						
						if (statusToChange.equalsIgnoreCase("completed")) {
							//String defaultValue = fnpGetORObjectX("ActionItemTab_ChangeStatusPFList").getText().trim().toLowerCase();
			/*				
							if ((defaultValue.contains("create")) | (defaultValue.equalsIgnoreCase(""))) {												
								fnpClickInDialog_OR("WOISR_AIConfirmationBtn");
							}
							
			*/			
								fnpClickInDialog_OR("WOISR_AIConfirmationBtn");
							
						}
			
						/***************************************************************************************/
						
			//}
			//}
			

			
			fnpLoading_waitInsideDialogBox();
			fnpLoading_wait();


			fnpCheckError("while Going to " + statusToChange + "  the  Action item ---" + ActionItemName);

			// fnpWaitForVisible("TopMessage3");
			fnpWaitForVisibleHavingMultipleElements("TopMessage3");
			String SuccessfulMsg = fnpGetText_OR("TopMessage3");
			fnpMymsg("Top Message after " + statusToChange + "  the  Action item '" + ActionItemName + "' ----" + SuccessfulMsg);

			Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"), "Top message does not contain 'success' word, so might be action of " + statusToChange
					+ "  the  Action item '" + ActionItemName + "'  is NOT successful.");


			//for coming back from Consolidated screen
			if ( ActionItemName.equalsIgnoreCase("TechnicalReview")|| ( ActionItemName.equalsIgnoreCase("CertIssue")  )  ) {
			//	fnpClick_OR("WO_BackBtn");	//PUKA-3391 new change sprint 12.1	
			//fnpClick_OR("WO_BackBtnOnViewOnlyScreenOnCompletedAI");
				
				
				//************ -Technical Review reverted back****//*
				
/*				if ( ActionItemName.equalsIgnoreCase("TechnicalReview")){
				fnpClick_OR("ISR_BackToViewBtn");
				}
				*/
				fnpClick_OR("WO_BackBtn");	
			}
			
			
			
			
			if (ActionItemName.equalsIgnoreCase("CertDecision")) {			
				fnpClick_OR("WorkOrderNoOnPerformCertDecisionAI");

			}
			
			


		} catch (Throwable t) {

			fnpMymsg("  Failed while completing action item '" + ActionItemName + "  . Error is ---" + t.getMessage());
			fnpDesireScreenshot(ActionItemName + "Failed");

			String stackTrace = Throwables.getStackTraceAsString(t);
			String errorMsg = t.getMessage();
			errorMsg = errorMsg + "\n\n\n\n Failed while completing action item '" + ActionItemName + "    .See screenshot '" + ActionItemName + "Failed.' \n\n" + stackTrace;
			Exception c = new Exception(errorMsg);
			ErrorUtil.addVerificationFailure(c);
			throw c;

		}

	}

	
	
	
	public static void fnpProcessAI_ISR_TaskTab_1_old(String dataTableXpathNameNOR,String headerTableXpathNameNOR,String ActionItemName, String statusToChange,String saveBtnInOR) throws Throwable {
		try {
			
			

			// ***************Process the pending Action item***********

			//
			fnpMymsg(" Going to " + statusToChange + "  the pending Action item ---" + ActionItemName);
			fnpWaitForVisible_NotInOR(dataTableXpathNameNOR);

			int ActionItemColIndex = fnpFindColumnIndex_NOR(headerTableXpathNameNOR, "AI Number");
			

			int itemDescColIndex = fnpFindColumnIndex_NOR(headerTableXpathNameNOR, "AI Name");
			
			int actionItemInfoRowNo = fnpFindRow_NOR(dataTableXpathNameNOR, ActionItemName, itemDescColIndex);
			String actionItemNo = fnpFetchFromTable_NOR(dataTableXpathNameNOR, actionItemInfoRowNo, ActionItemColIndex);
			int start = 1;
			while (actionItemNo.isEmpty()) {
				Thread.sleep(1000);
				actionItemNo = fnpFetchFromTable_NOR(dataTableXpathNameNOR, actionItemInfoRowNo, ActionItemColIndex);

				if (start > 50) {
					break;
				}
			}
			Thread.sleep(500);

			
			fnpClickALinkInATable(actionItemNo);
			
			
			fnpMymsg("Clicked on  Action Item '" + ActionItemName + "' in Table having no. is '" + actionItemNo + "' .");

			fnpLoading_wait();
	
			
			
			
			
			
			
			
			if (ActionItemName.equalsIgnoreCase("CertDecision")) {
				fnpClick_OR("WOISR_AIEditBtn_OnConsolidatedScreen");
				fnpClick_OR("ReassignToCertDecisionAI_LKPBtn");
				//fnpSearchNSelectFirstRadioBtn(2, (String) hashXlData.get("AccountManager"), 1);
				String accountManager;
				
				
				if (  (loginAsFullName==null) ||loginAsFullName.equalsIgnoreCase("") ) {
					//reassignee=(String) hashXlData.get("AccountManager").trim();
					if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
						accountManager=(String) hashXlData.get("AccountManager_Code").trim();
						fnpSearchNSelectFirstRadioBtn(1, accountManager, 1);
					}else{
						accountManager=(String) hashXlData.get("AccountManager").trim();
						fnpSearchNSelectFirstRadioBtn(2, accountManager, 1);
					}

				} else {
					//reassignee=loginAsFullName;
					if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
						accountManager=loginUser_code;
						fnpSearchNSelectFirstRadioBtn(1, loginUser_code, 1);
					}else{
						accountManager=loginAsFullName;
						fnpSearchNSelectFirstRadioBtn(2, accountManager, 1);
					}
				}
				
				
				
				
				
				
				
				
				
				
				
				
				fnpClick_OR("SaveBtnOnCertDecisionAIConsolidatedScreen");
				fnpWaitForVisibleHavingMultipleElements("TopMessage3");
				String SuccessfulMsg = fnpGetText_OR("TopMessage3");
				fnpMymsg("Top Message after Reassign cert Decision AI ----" + SuccessfulMsg);
				fnpClick_OR("PerformAIBtn");
				fnpWaitForVisibleHavingMultipleElements("TopMessage3");
				SuccessfulMsg = fnpGetText_OR("TopMessage3");
				fnpMymsg("Top Message after clicking Perform AI button ----" + SuccessfulMsg);
				fnpClick_OR("MakeDecisionBtn");
				String yesRadioBtnXpath=OR.getProperty("CertDecisionCheckListRadioYesBtn_Firstpart")+"Yes"+OR.getProperty("CertDecisionCheckListRadioYesBtn_Secondpart");
				List<WebElement> allYesRadioBtns = driver.findElements(By.xpath(yesRadioBtnXpath));
				
				if (allYesRadioBtns.size() > 0) {
					//
				}else{
					fnpMymsg("Either Yes radio buttons are not present or xpath changed." );
					throw new Exception("Either Yes radio buttons are not present or xpath changed." );
				}

				for (int i = 0; i < allYesRadioBtns.size(); i++) {
					try{
						fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
						allYesRadioBtns.get(i).click();
						fnpLoading_wait();
						fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
					}catch(Throwable t){
						fnpMymsg("Some exception while clicking on Yes button either due to xpath changes or something other reason. Exception is --"+t.getMessage());
						throw new Exception("Some exception while clicking on Yes button either due to xpath changes or something other reason. Exception is --"+t.getMessage());
					}
					Thread.sleep(2000);
				}
				
				Thread.sleep(2000);
				
				//sometime this drop down not getting refreshed having Yes value, it takes time based on some upper radio buttons.
				try{
					fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
					fnpPFList("DecisionOnCertDecisionAIList", "DecisionOnCertDecisionAIListOptions", "Yes");
					fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
				}catch(Throwable t){
					fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
					fnpPFList("DecisionOnCertDecisionAIList", "DecisionOnCertDecisionAIListOptions", "Yes");
					fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
				}
				Thread.sleep(2000);
			}
			
			
			
			
			
			
			if (  (ActionItemName.equalsIgnoreCase("TechnicalReview")) ){		
								
				fnpWaitForVisible("ReassignAILkpBtn_OnConsolidatedScreen");
				String alreadyAssingee=fnpGetORObjectX("AssignedToLabelValue_OnConsolidatedScreen").getText().trim();				
				String reassignee=null;
				//if (classNameText.equalsIgnoreCase("SQF_Non_Cert")) {
				
				
				
				
				
				if (classNameText.equalsIgnoreCase(Non_Cert_StandardsTestCaseName)) {
					//reassignee=(String) hashXlData.get("Auditor").trim();
					if (  (loginAsFullName==null) ||loginAsFullName.equalsIgnoreCase("") ) {
						//reassignee=(String) hashXlData.get("Auditor").trim();
						if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
							reassignee=(String) hashXlData.get("Auditor_Code").trim();
						}else{
							reassignee=(String) hashXlData.get("Auditor").trim();
						}

						
					} else {
						//reassignee=loginAsFullName;
						if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
							reassignee=loginUser_code;
							
						}else{
							reassignee=loginAsFullName;
						}

						
					}

				} else {
					//reassignee=(String) hashXlData.get("AccountManager").trim();
					//if (  (loginAsFullName==null) ||loginAsFullName.equalsIgnoreCase("") ) {
					if (  (loginAsFullName==null) ||loginAsFullName.equalsIgnoreCase("") ) {
						//reassignee=(String) hashXlData.get("AccountManager").trim();
						if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
							reassignee=(String) hashXlData.get("AccountManager_Code").trim();
						}else{
							reassignee=(String) hashXlData.get("AccountManager").trim();
						}

					} else {
						//reassignee=loginAsFullName;
						if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
							reassignee=loginUser_code;
						}else{
							reassignee=loginAsFullName;
						}
					}
				}	
				
				
				
				
				
				
				
				
				
				
				
				
				
				//if (!(alreadyAssingee.equalsIgnoreCase(reassignee))) {		
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
							}else{
								fnpSearchNSelectFirstRadioBtn(2, reassignee, 1);
							}
							fnpMymsg("Just after  inserting value of Account Manger");

							String EnteredAccountManager = fnpWaitTillTextBoxDontHaveValue("ReassignAITxtBx_OnConsolidatedScreen", "value");
							Assert.assertTrue(EnteredAccountManager.contains(reassignee), "Reassignee is not selected properly from lookup");
							fnpMymsg(" Reassignee is properly selected from client lookup");
					}
					fnpGetORObjectX(saveBtnInOR).click();
					fnpLoading_wait();

				}			
			}
			
			
			
			
			if (  (ActionItemName.equalsIgnoreCase(WOISOTaskTab_Certification_ClientRecordChangeAIName)) ){				
				
				fnpWaitForVisible("ReassignAILkpBtn");
				String alreadyAssingee=fnpGetORObjectX("AssignedToLabelValue").getText().trim();				
				String reassignee=null;
				if (classNameText.equalsIgnoreCase(Non_Cert_StandardsTestCaseName)) {
					//reassignee=(String) hashXlData.get("Auditor").trim();
					
/*					if (loginAsFullName.equalsIgnoreCase("") | (loginAsFullName==null) ) {
						reassignee=(String) hashXlData.get("Auditor").trim();
					} else {
						reassignee=loginAsFullName;
					}
					*/
					
					if (  (loginAsFullName==null) ||loginAsFullName.equalsIgnoreCase("") ) {
						//reassignee=(String) hashXlData.get("Auditor").trim();
						if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
							reassignee=(String) hashXlData.get("Auditor_Code").trim();
						}else{
							reassignee=(String) hashXlData.get("Auditor").trim();
						}

						
					} else {
						//reassignee=loginAsFullName;
						if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
							reassignee=loginUser_code;
						}else{
							reassignee=loginAsFullName;
						}

						
					}
					
					
					
					
					
					
					
				} else {
					//reassignee=(String) hashXlData.get("AccountManager").trim();
/*					
					if (loginAsFullName.equalsIgnoreCase("") | (loginAsFullName==null) ) {
						reassignee=(String) hashXlData.get("AccountManager").trim();
					} else {
						reassignee=loginAsFullName;
					}
					
				*/	
					if (  (loginAsFullName==null) ||loginAsFullName.equalsIgnoreCase("") ) {
						//reassignee=(String) hashXlData.get("AccountManager").trim();
						if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
							reassignee=(String) hashXlData.get("AccountManager_Code").trim();
						}else{
							reassignee=(String) hashXlData.get("AccountManager").trim();
						}

					} else {
						//reassignee=loginAsFullName;
						if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
							reassignee=loginUser_code;
						}else{
							reassignee=loginAsFullName;
						}
					}
					
					
					
					
				}			
				//if (!(alreadyAssingee.equalsIgnoreCase(reassignee))) {	
				if (!(alreadyAssingee.contains(reassignee))) {
					String defaultValue = fnpGetORObjectX("ReassignAITxtBx").getAttribute("value").trim();
					//if (defaultValue.equalsIgnoreCase(reassignee)) {
					if (defaultValue.contains(reassignee)) {
						fnpMymsg("@  - default value is same as expected, so returning back.");
					} else {					
							fnpClick_OR("ReassignAILkpBtn");
		
							fnpMymsg("Just after  click ReassignAILkpBtn");
							fnpMymsg("Just before going to insert value of Account Manger");
							//fnpSearchNSelectFirstRadioBtn(2, reassignee, 1);
							if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
								fnpSearchNSelectFirstRadioBtn(1, reassignee, 1);
							}else{
								fnpSearchNSelectFirstRadioBtn(2, reassignee, 1);
							}

							
							fnpMymsg("Just after  inserting value of Account Manger");

							String EnteredAccountManager = fnpWaitTillTextBoxDontHaveValue("ReassignAITxtBx", "value");
							Assert.assertTrue(EnteredAccountManager.contains(reassignee), "Reassignee is not selected properly from lookup");
							fnpMymsg(" Reassignee is properly selected from client lookup");
					}
					//fnpGetORObjectX(saveBtnInOR).click();
					//fnpLoading_wait();

				}	
				
					
				}
				





			if (statusToChange.equalsIgnoreCase("completed") && ActionItemName.equalsIgnoreCase("TechnicalReview")) {
			
				
				
				fnpWaitTillVisiblityOfElementNClickable_OR("QuestionnaireOptionsLinkInTechReviewAI");
				fnpClickInDialog_OR("QuestionnaireOptionsLinkInTechReviewAI");
				
				//TestSuiteBase_Proposal.fnpCommonProcessQuestionnairesSet( (String) hashXlData.get("QuestionNAnswerSets").trim());
				TestSuiteBase_Proposal.fnpCommonProcessQuestionnairesSet_ISR( );

			
				fnpWaitForVisible("QuestionnaireDetailsTableInWOISO");

				
				/************ --30 September 2020****/
				int colIndex = fnpFindColumnIndex("QuestionnaireDetailsTableInWOISO_header", "Unanswered Questions");
				String unAnsweredQuestNo = fnpFetchFromTable("QuestionnaireDetailsTableInWOISO", 1, colIndex).trim();
												
				int unAnsweredQuestInt=Integer.parseInt(unAnsweredQuestNo);

				int counter = 0;

				while (unAnsweredQuestInt!=0) {
					Thread.sleep(1000);
					counter = counter + 1;
					unAnsweredQuestNo = fnpFetchFromTable("QuestionnaireDetailsTableInWOISO", 1, colIndex).trim();
					unAnsweredQuestInt=Integer.parseInt(unAnsweredQuestNo);
					if (counter > Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
						break;
					}
				}

				Assert.assertEquals(unAnsweredQuestInt, 0, "Unanswered Questions are not equal to '0'. ");

				//Thread.sleep(100000); // 100 seconds ,Pradeep below field is dependent drop down, so putting this wait intentially as iPulse is too slow, plz remove it later
				fnpLoading_wait();
				fnpPFList("ISR_AI_LeadAuditorRatingList", "ISR_AI_LeadAuditorRatingListOptions", (String) hashXlData.get("Lead_Auditor_Rating").trim());
				fnpLoading_wait();
				
			
			//	fnpClick_OR("SaveButton_ApproveAuditPopup");   
				
				//Thread.sleep(100000); // 100 seconds ,Pradeep below field is dependent drop down, so putting this wait intentially as iPulse is too slow, plz remove it later
				
				if ((classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.ISO9001_SingleTestCaseName))) {
					fnpPFList("ISR_AI_ReviewerRecommendationList", "ISR_AI_ReviewerRecommendationListOptions", (String) hashXlData.get("Reviewer_Recommendation").trim());
				}else{
					if ((classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.Cert_StandardsTestCaseName))){
					//fnpPFListByLiNo("ISR_AI_ReviewerRecommendationList", "ISR_AI_ReviewerRecommendationListOptions", 2);
					fnpPFListByLiNo("ISR_AI_ReviewerRecommendationList", "ISR_AI_ReviewerRecommendationListOptions", 1);
					} else {
						//dont know till now
					}
				}
				
				fnpClick_OR("SaveButton_ApproveAuditPopup");   
				
			}
			

			
			
			
			if (ActionItemName.equalsIgnoreCase("CertIssue")) {


				String alreadyAssingee=fnpGetORObjectX("AssignedToLabelValue_OnConsolidatedScreen").getText().trim();				
				String reassignee=null;
			
/*				//reassignee=(String) hashXlData.get("AccountManager").trim();
				if (loginAsFullName.equalsIgnoreCase("") | (loginAsFullName==null) ) {p
					reassignee=(String) hashXlData.get("AccountManager").trim();
				} else {
					reassignee=loginAsFullName;
				}
				
				*/
				
				
				if (  (loginAsFullName==null) ||loginAsFullName.equalsIgnoreCase("") ) {
					//reassignee=(String) hashXlData.get("AccountManager").trim();
					if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
						reassignee=(String) hashXlData.get("AccountManager_Code").trim();
					}else{
						reassignee=(String) hashXlData.get("AccountManager").trim();
					}

				} else {
					//reassignee=loginAsFullName;
					if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
						reassignee=loginUser_code;

					}else{
						reassignee=loginAsFullName;
	
					}
				}
				
				
				
				
				
				
				
				//if (!(alreadyAssingee.equalsIgnoreCase(reassignee))) {
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
							}else{
								fnpSearchNSelectFirstRadioBtn(2, reassignee, 1);
							}
							fnpMymsg("Just after  inserting value of Account Manger");

							String enteredAccountManager = fnpWaitTillTextBoxDontHaveValue("ReassignAITxtBx_OnConsolidatedScreen", "value");
							Assert.assertTrue(enteredAccountManager.contains(reassignee), "Reassignee is not selected properly from lookup");
							fnpMymsg(" Reassignee is properly selected from client lookup");
					}
					fnpGetORObjectX(saveBtnInOR).click();
					fnpLoading_wait();

					}
				
				
				if(!((classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.Cert_StandardsTestCaseName)))){	
					fnpGetORObjectX("HaveyoureviewedthereisasignedcontractChkBx").click();
					Thread.sleep(1000);
					fnpLoading_wait();
				}

				
				
				if (fnpCheckElementDisplayedImmediately("CertLanguagePFList")) {
					fnpPFList("CertLanguagePFList", "CertLanguagePFListOptions", (String) hashXlData.get("Cert_Language"));
				}
				
				
				
				if(classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.Cert_StandardsTestCaseName)){	
					fnpType("OR", "CertIssue_ExternalID_txt", "1111");
					Thread.sleep(2000);
					fnpLoading_wait();
					fnpType("OR", "CertIssue_TaskRequest_txt", "1254");//	W0678008
					Thread.sleep(2000);
					fnpLoading_wait();
					
/*					fnpGetORObjectX("CertIssue_TaskRequest_txt").sendKeys("1254");
					Thread.sleep(20000);
					fnpLoading_wait();
					fnpDoubleClick(fnpGetORObjectX("CertIssue_TaskRequest_Label"));
					Thread.sleep(10000);
					*/
					
					
					
					fnpClick_OR(saveBtnInOR);
					
					
					
					//fnpType("OR", "CertIssue_SQFCertNo_txt", "1234");
				//	fnpType("OR", "CertIssue_IATFCertNo_txt", "1234");
					
				}
				
				
				
				
				
			//	fnpPFList("CertTemplatePFList", "CertTemplatePFListOptions", (String) hashXlData.get("Cert_Template"));
			//	fnpPFListByLiNo("CertTemplatePFList", "CertTemplatePFListOptions", 2);
				fnpPFListByLiNo("CertTemplatePFList", "CertTemplatePFListOptions", 1);
				String selectedCertTemplate=fnpGetText_OR("CertTemplatePFList");
				

				
				fnpClick_OR("IssueCertBtn");
				
			
				
				int templateNameColIndex = fnpFindColumnIndex("CertificateNameTable_HeaderRow", "Template Name");
				int certificateDateColIndex = fnpFindColumnIndex("CertificateNameTable_HeaderRow", "Certificate Date");
				//int ourNewlyAddedTemplateRowNo = fnpFindRow("CertificateNameTable", (String) hashXlData.get("Cert_Template"), templateNameColIndex);
				int ourNewlyAddedTemplateRowNo = fnpFindRow("CertificateNameTable", selectedCertTemplate, templateNameColIndex);
				
				String ourNewlyAddedCertificateDate = fnpFetchFromTable("CertificateNameTable", ourNewlyAddedTemplateRowNo, certificateDateColIndex);
				

				SimpleDateFormat sdfmt1 = new SimpleDateFormat("dd-MMM-yyyy");
				java.util.Date dateToCompareInDateFormat = sdfmt1.parse(ourNewlyAddedCertificateDate);
				
				
				
				Date todayDate = new Date();
				String todayDateInStringformat = sdfmt1.format(todayDate);		

				java.util.Date withCompareDateInDateFormat = sdfmt1.parse(todayDateInStringformat);
					
				Date withCompareDate = withCompareDateInDateFormat;
		
				String textMessage="";
				if (dateToCompareInDateFormat.compareTo(withCompareDate) > 0) {
					textMessage = "ourNewlyAddedCertificateDate '"+ourNewlyAddedCertificateDate+"' is after today's date '"+withCompareDate+"', hence failed. ";				
					fnpMymsg(textMessage);
					fnpDisplayingMessageInFrame(textMessage, 5);
					throw new Exception(textMessage);
				} else if (dateToCompareInDateFormat.compareTo(withCompareDate) < 0) {
					textMessage = "ourNewlyAddedCertificateDate '"+ourNewlyAddedCertificateDate+"' is before today's date '"+withCompareDate+"', hence failed. ";	
					fnpMymsg(textMessage);
					fnpDisplayingMessageInFrame(textMessage, 5);
					throw new Exception(textMessage);
				} else {
					textMessage = "ourNewlyAddedCertificateDate '"+ourNewlyAddedCertificateDate+"' is equal to  today's date '"+withCompareDate+"', hence passed. ";	
					fnpMymsg(textMessage);
					fnpDisplayingMessageInFrame(textMessage, 5);
				}

				
				
				
				
				
			}

			
			if(!ActionItemName.equalsIgnoreCase("TechnicalReview")){
				String defaultValue ="";
				if (ActionItemName.equalsIgnoreCase("CertDecision")) {
					defaultValue = fnpGetORObjectX("PerformDecisionAI_ChangeStatusPFList").getText().trim().toLowerCase();
				}else{
					defaultValue = fnpGetORObjectX("ActionItemTab_ChangeStatusPFList").getText().trim().toLowerCase();

				}
				
				
				if (!(ActionItemName.equalsIgnoreCase(WOISOTaskTab_Certification_ClientRecordChangeAIName))) {
					
					if (ActionItemName.equalsIgnoreCase("CertDecision")) {
						fnpWaitForVisible("PerformDecisionAI_ChangeStatusPFList");
						fnpPFList("PerformDecisionAI_ChangeStatusPFList", "PerformDecisionAI_ChangeStatusPFListOptions", "Completed");
					}else{
						fnpWaitForVisible("ActionItemTab_ChangeStatusPFList");
						fnpPFList("ActionItemTab_ChangeStatusPFList", "ActionItemTab_ChangeStatusPFListOptions", statusToChange);
					}
					
					

						fnpGetORObjectX(saveBtnInOR).click();
						fnpLoading_wait();
				


				}else{
					
					
					fnpWaitForVisible("ActionItemTab_ChangeStatusPFList");
					fnpPFList("ActionItemTab_ChangeStatusPFList", "ActionItemTab_ChangeStatusPFListOptions", statusToChange);
					
					
					fnpClick_OR(saveBtnInOR);
				}
				


			//	if (!(ActionItemName.equalsIgnoreCase("CertDecision"))) {
				
						
						/************It will come always for changing from created to completed from today onwards 11-07-2017  against IPM-6594********************/
						
						if (statusToChange.equalsIgnoreCase("completed")) {
							//String defaultValue = fnpGetORObjectX("ActionItemTab_ChangeStatusPFList").getText().trim().toLowerCase();
			/*				
							if ((defaultValue.contains("create")) | (defaultValue.equalsIgnoreCase(""))) {												
								fnpClickInDialog_OR("WOISR_AIConfirmationBtn");
							}
							
			*/			
								fnpClickInDialog_OR("WOISR_AIConfirmationBtn");
							
						}
			
						/***************************************************************************************/
						
			//}
			}
			

			
			fnpLoading_waitInsideDialogBox();
			fnpLoading_wait();


			fnpCheckError("while Going to " + statusToChange + "  the  Action item ---" + ActionItemName);

			// fnpWaitForVisible("TopMessage3");
			fnpWaitForVisibleHavingMultipleElements("TopMessage3");
			String SuccessfulMsg = fnpGetText_OR("TopMessage3");
			fnpMymsg("Top Message after " + statusToChange + "  the  Action item '" + ActionItemName + "' ----" + SuccessfulMsg);

			Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"), "Top message does not contain 'success' word, so might be action of " + statusToChange
					+ "  the  Action item '" + ActionItemName + "'  is NOT successful.");


			//for coming back from Consolidated screen
			if ( ActionItemName.equalsIgnoreCase("TechnicalReview")|| ( ActionItemName.equalsIgnoreCase("CertIssue")  )  ) {
			//	fnpClick_OR("WO_BackBtn");	//PUKA-3391 new change sprint 12.1	
			//fnpClick_OR("WO_BackBtnOnViewOnlyScreenOnCompletedAI");
				
/*				if ( ActionItemName.equalsIgnoreCase("TechnicalReview")){
				fnpClick_OR("ISR_BackToViewBtn");
				}
				*/
				
				
				
				fnpClick_OR("WO_BackBtn");	
			}
			
			
			
			
			if (ActionItemName.equalsIgnoreCase("CertDecision")) {			
				fnpClick_OR("WorkOrderNoOnPerformCertDecisionAI");

			}
			
			


		} catch (Throwable t) {

			fnpMymsg("  Failed while completing action item '" + ActionItemName + "  . Error is ---" + t.getMessage());
			fnpDesireScreenshot(ActionItemName + "Failed");

			String stackTrace = Throwables.getStackTraceAsString(t);
			String errorMsg = t.getMessage();
			errorMsg = errorMsg + "\n\n\n\n Failed while completing action item '" + ActionItemName + "    .See screenshot '" + ActionItemName + "Failed.' \n\n" + stackTrace;
			Exception c = new Exception(errorMsg);
			ErrorUtil.addVerificationFailure(c);
			throw c;

		}

	}

	
	
	
	
	
	
	
	

	//public static void fnpProcessAI_ISR_TaskTab(String dataTableXpathNameNOR,String headerTableXpathNameNOR,String ActionItemName, String statusToChange,String saveBtnInOR,String openIconForExpandingXpathNOR) throws Throwable {
	public static void fnpProcessAI_ISR_TaskTab_2(String task,String taskName,String ActionItemName, String statusToChange,String saveBtnInOR) throws Throwable {
		try {
			
			
			int taskAINameIndex = fnpFindColumnIndex("TaskTabTable_HeaderRow", WOISOTaskTab_TaskAISummaryTableColName_TaskAIName);
			int TaskRowNo = fnpFindRowContainsName("TaskTabTable", taskName, taskAINameIndex);
			String innerTableDataXpathForAI = OR.getProperty("ISRTaskInnerTableForActionItemsTableData_part1") + (TaskRowNo - 1)
					+ OR.getProperty("ISRTaskInnerTableForActionItemsTableData_part2");
			String innerTableHeaderXpathForAI = OR.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part1") + (TaskRowNo - 1)
					+ OR.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part2");
			
		
			String openIconXpath = OR.getProperty("TaskTabISR_Task_OpenIconPart1") + task+ OR.getProperty("TaskTabISR_Task_OpenIconPart2");
			
			// ***************Process the pending Action item***********

			//
			fnpMymsg(" Going to " + statusToChange + "  the pending Action item ---" + ActionItemName);
			fnpWaitForVisible_NotInOR(innerTableDataXpathForAI);

			int ActionItemColIndex = fnpFindColumnIndex_NOR(innerTableHeaderXpathForAI, "AI Number");
			
			int itemDescColIndex = fnpFindColumnIndex_NOR(innerTableHeaderXpathForAI, "AI Name");
			
			int actionItemInfoRowNo = fnpFindRow_NOR(innerTableDataXpathForAI, ActionItemName, itemDescColIndex);
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
			
			fnpMymsg("Clicked on  Action Item '" + ActionItemName + "' in Table having no. is '" + actionItemNo + "' .");

			fnpLoading_wait();
			
			
			
			if (ActionItemName.equalsIgnoreCase("CertDecision")) {
				fnpClick_OR("WOISR_AIEditBtn_OnConsolidatedScreen");
				fnpClick_OR("ReassignToCertDecisionAI_LKPBtn");
				//fnpSearchNSelectFirstRadioBtn(2, (String) hashXlData.get("AccountManager"), 1);
				
				String accountManager;
/*				
				if (loginAsFullName.equalsIgnoreCase("") | (loginAsFullName==null) ) {p
					accountManager=(String) hashXlData.get("AccountManager").trim();
				} else {
					accountManager=loginAsFullName;
				}
				fnpSearchNSelectFirstRadioBtn(2, accountManager, 1);
				
				*/
				
				if (  (loginAsFullName==null) ||loginAsFullName.equalsIgnoreCase("") ) {
					//reassignee=(String) hashXlData.get("AccountManager").trim();
					if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
						accountManager=(String) hashXlData.get("AccountManager_Code").trim();
						fnpSearchNSelectFirstRadioBtn(1, accountManager, 1);
					}else{
						accountManager=(String) hashXlData.get("AccountManager").trim();
						fnpSearchNSelectFirstRadioBtn(2, accountManager, 1);
					}

				} else {
					//reassignee=loginAsFullName;
					if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
						accountManager=loginUser_code;
						fnpSearchNSelectFirstRadioBtn(1, accountManager, 1);

					}else{
						accountManager=loginAsFullName;
						fnpSearchNSelectFirstRadioBtn(2, accountManager, 1);
	
					}
				}
				
				
				
				
				
				
				
				fnpClick_OR("SaveBtnOnCertDecisionAIConsolidatedScreen");
				fnpWaitForVisibleHavingMultipleElements("TopMessage3");
				String SuccessfulMsg = fnpGetText_OR("TopMessage3");
				fnpMymsg("Top Message after Reassign cert Decision AI ----" + SuccessfulMsg);
				fnpClick_OR("PerformAIBtn");
				fnpWaitForVisibleHavingMultipleElements("TopMessage3");
				SuccessfulMsg = fnpGetText_OR("TopMessage3");
				fnpMymsg("Top Message after clicking Perform AI button ----" + SuccessfulMsg);
				fnpClick_OR("MakeDecisionBtn");
				String yesRadioBtnXpath=OR.getProperty("CertDecisionCheckListRadioYesBtn_Firstpart")+"Yes"+OR.getProperty("CertDecisionCheckListRadioYesBtn_Secondpart");
				List<WebElement> allYesRadioBtns = driver.findElements(By.xpath(yesRadioBtnXpath));
				
				if (allYesRadioBtns.size() > 0) {
					//
				}else{
					fnpMymsg("Either Yes radio buttons are not present or xpath changed." );
					throw new Exception("Either Yes radio buttons are not present or xpath changed." );
				}

				for (int i = 0; i < allYesRadioBtns.size(); i++) {
					try{
					allYesRadioBtns.get(i).click();
					}catch(Throwable t){
						fnpMymsg("Some exception while clicking on Yes button either due to xpath changes or something other reason. Exception is --"+t.getMessage());
						throw new Exception("Some exception while clicking on Yes button either due to xpath changes or something other reason. Exception is --"+t.getMessage());
					}
					Thread.sleep(1000);

				}

				fnpPFList("DecisionOnCertDecisionAIList", "DecisionOnCertDecisionAIListOptions", "Yes");

				
			}
			
			
			
			
			
			
			if (ActionItemName.equalsIgnoreCase("TechnicalReview")) {
				

				fnpWaitForVisible("ReassignAILkpBtn_OnConsolidatedScreen");

				String alreadyAssingee=fnpGetORObjectX("AssignedToLabelValue_OnConsolidatedScreen").getText().trim();
				
				String reassignee=null;
				
/*				
				if (classNameText.equalsIgnoreCase(Non_Cert_StandardsTestCaseName)) {
					//reassignee=(String) hashXlData.get("Auditor").trim();
					if (loginAsFullName.equalsIgnoreCase("") | (loginAsFullName==null) ) {
						reassignee=(String) hashXlData.get("Auditor").trim();
					} else {
						reassignee=loginAsFullName;
					}
				} else {
					//reassignee=(String) hashXlData.get("AccountManager").trim();
					if (loginAsFullName.equalsIgnoreCase("") | (loginAsFullName==null) ) {
						reassignee=(String) hashXlData.get("AccountManager").trim();
					} else {
						reassignee=loginAsFullName;
					}
				}
				
				*/
/*				
				if (loginAsFullName.equalsIgnoreCase("") | (loginAsFullName==null) ) {
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
			*/
				
				
				if (classNameText.equalsIgnoreCase(Non_Cert_StandardsTestCaseName)) {
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
				
				} else {
					
					if (  (loginAsFullName==null) ||loginAsFullName.equalsIgnoreCase("") ) {
						if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
							reassignee=(String) hashXlData.get("AccountManager_Code").trim();
						}else{
							reassignee=(String) hashXlData.get("AccountManager").trim();
						}

					} else {
						if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
							reassignee=loginUser_code;

						}else{
							reassignee=loginAsFullName;
		
						}
					}
				}
				
				
				
				
				
				//if (!(alreadyAssingee.equalsIgnoreCase(reassignee))) {
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
							}else{
								fnpSearchNSelectFirstRadioBtn(2, reassignee, 1);
							}
							fnpMymsg("Just after  inserting value of Account Manger");

							String EnteredAccountManager = fnpWaitTillTextBoxDontHaveValue("ReassignAITxtBx_OnConsolidatedScreen", "value");
							Assert.assertTrue(EnteredAccountManager.contains(reassignee), "Reassignee is not selected properly from lookup");
							fnpMymsg(" Reassignee is properly selected from client lookup");
					}
					fnpGetORObjectX(saveBtnInOR).click();
					fnpLoading_wait();

				}
				
				
				
			}
			
			
			
			
			




			if (statusToChange.equalsIgnoreCase("completed") && ActionItemName.equalsIgnoreCase("TechnicalReview")) {
				//p
				Thread.sleep(1000);
				
				fnpWaitTillVisiblityOfElementNClickable_OR("QuestionnaireOptionsLinkInTechReviewAI");
				fnpClickInDialog_OR("QuestionnaireOptionsLinkInTechReviewAI");

				TestSuiteBase_Proposal.fnpCommonProcessQuestionnairesSet( (String) hashXlData.get("QuestionNAnswerSets").trim());

				fnpWaitForVisible("QuestionnaireDetailsTableInWOISO");

				int colIndex = fnpFindColumnIndex("QuestionnaireDetailsTableInWOISO_header", "Unanswered Questions");
				String unAnsweredQuestNo = fnpFetchFromTable("QuestionnaireDetailsTableInWOISO", 1, colIndex);
				int unAnsweredQuestInt=Integer.parseInt(unAnsweredQuestNo);

				int counter = 0;

				while (unAnsweredQuestInt!=0) {
					Thread.sleep(1000);
					counter = counter + 1;
					unAnsweredQuestNo = fnpFetchFromTable("QuestionnaireDetailsTableInWOISO", 1, colIndex);
					unAnsweredQuestInt=Integer.parseInt(unAnsweredQuestNo);
					if (counter > Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
						break;
					}
				}

				Assert.assertEquals(unAnsweredQuestInt, 0, "Unanswered Questions are not equal to '0'. ");

				fnpLoading_wait();
				fnpPFList("ISR_AI_LeadAuditorRatingList", "ISR_AI_LeadAuditorRatingListOptions", (String) hashXlData.get("Lead_Auditor_Rating").trim());
				fnpLoading_wait();
				fnpPFList("ISR_AI_ReviewerRecommendationList", "ISR_AI_ReviewerRecommendationListOptions", (String) hashXlData.get("Reviewer_Recommendation").trim());
				
				//p
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			if (ActionItemName.equalsIgnoreCase("CertIssue")) {


				String alreadyAssingee=fnpGetORObjectX("AssignedToLabelValue_OnConsolidatedScreen").getText().trim();				
				String reassignee=null;

/*				
			//	reassignee=(String) hashXlData.get("AccountManager").trim();
				if (loginAsFullName.equalsIgnoreCase("") | (loginAsFullName==null) ) {
					reassignee=(String) hashXlData.get("AccountManager").trim();
				} else {
					reassignee=loginAsFullName;
				}
				
			*/	
				if (  (loginAsFullName==null) ||loginAsFullName.equalsIgnoreCase("") ) {
					
					if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
						reassignee=(String) hashXlData.get("AccountManager_Code").trim();
					}else{
						reassignee=(String) hashXlData.get("AccountManager").trim();
					}

				} else {
				
					if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
						reassignee=loginUser_code;

					}else{
						reassignee=loginAsFullName;
	
					}
				}
			

				
				
				
				
				
				
				//if (!(alreadyAssingee.equalsIgnoreCase(reassignee))) {
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
							}else{
								fnpSearchNSelectFirstRadioBtn(2, reassignee, 1);
							}
							fnpMymsg("Just after  inserting value of Account Manger");

							String enteredAccountManager = fnpWaitTillTextBoxDontHaveValue("ReassignAITxtBx_OnConsolidatedScreen", "value");
							Assert.assertTrue(enteredAccountManager.contains(reassignee), "Reassignee is not selected properly from lookup");
							fnpMymsg(" Reassignee is properly selected from client lookup");
					}
					fnpGetORObjectX(saveBtnInOR).click();
					fnpLoading_wait();

					}
				
				if (fnpCheckElementDisplayedImmediately("CertLanguagePFList")) {
					fnpPFList("CertLanguagePFList", "CertLanguagePFListOptions", (String) hashXlData.get("Cert_Language"));
				}
				
				
				if(classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.Cert_StandardsTestCaseName)){	
					fnpType("OR", "CertIssue_ExternalID_txt", "1111");
					Thread.sleep(2000);
					fnpLoading_wait();
					fnpType("OR", "CertIssue_TaskRequest_txt", "1254");//	W0678008
					Thread.sleep(2000);
					fnpLoading_wait();
					
/*					fnpGetORObjectX("CertIssue_TaskRequest_txt").sendKeys("1254");
					Thread.sleep(20000);
					fnpLoading_wait();
					fnpDoubleClick(fnpGetORObjectX("CertIssue_TaskRequest_Label"));
					Thread.sleep(10000);
					*/
					
					
					
					fnpClick_OR(saveBtnInOR);
					
					
					
					//fnpType("OR", "CertIssue_SQFCertNo_txt", "1234");
				//	fnpType("OR", "CertIssue_IATFCertNo_txt", "1234");
					
				}
				
				
				
				//fnpPFList("CertTemplatePFList", "CertTemplatePFListOptions", (String) hashXlData.get("Cert_Template"));
				//fnpPFListByLiNo("CertTemplatePFList", "CertTemplatePFListOptions", 2);
				fnpPFListByLiNo("CertTemplatePFList", "CertTemplatePFListOptions", 1);
				String selectedCertTemplate=fnpGetText_OR("CertTemplatePFList");
				

				
				fnpClick_OR("IssueCertBtn");
				
			
				
				int templateNameColIndex = fnpFindColumnIndex("CertificateNameTable_HeaderRow", "Template Name");
				int certificateDateColIndex = fnpFindColumnIndex("CertificateNameTable_HeaderRow", "Certificate Date");
			//	int ourNewlyAddedTemplateRowNo = fnpFindRow("CertificateNameTable", (String) hashXlData.get("Cert_Template"), templateNameColIndex);
				int ourNewlyAddedTemplateRowNo = fnpFindRow("CertificateNameTable", selectedCertTemplate, templateNameColIndex);
				
				String ourNewlyAddedCertificateDate = fnpFetchFromTable("CertificateNameTable", ourNewlyAddedTemplateRowNo, certificateDateColIndex);
				

				SimpleDateFormat sdfmt1 = new SimpleDateFormat("dd-MMM-yyyy");
				java.util.Date dateToCompareInDateFormat = sdfmt1.parse(ourNewlyAddedCertificateDate);
				
				
				
				Date todayDate = new Date();
				String todayDateInStringformat = sdfmt1.format(todayDate);		

				java.util.Date withCompareDateInDateFormat = sdfmt1.parse(todayDateInStringformat);
					
				Date withCompareDate = withCompareDateInDateFormat;
		
				String textMessage="";
				if (dateToCompareInDateFormat.compareTo(withCompareDate) > 0) {
					textMessage = "ourNewlyAddedCertificateDate '"+ourNewlyAddedCertificateDate+"' is after today's date '"+withCompareDate+"', hence failed. ";				
					fnpMymsg(textMessage);
					fnpDisplayingMessageInFrame(textMessage, 5);
					throw new Exception(textMessage);
				} else if (dateToCompareInDateFormat.compareTo(withCompareDate) < 0) {
					textMessage = "ourNewlyAddedCertificateDate '"+ourNewlyAddedCertificateDate+"' is before today's date '"+withCompareDate+"', hence failed. ";	
					fnpMymsg(textMessage);
					fnpDisplayingMessageInFrame(textMessage, 5);
					throw new Exception(textMessage);
				} else {
					textMessage = "ourNewlyAddedCertificateDate '"+ourNewlyAddedCertificateDate+"' is equal to  today's date '"+withCompareDate+"', hence passed. ";	
					fnpMymsg(textMessage);
					fnpDisplayingMessageInFrame(textMessage, 5);
				}

				
				
				
				
				
			}
			
			

			String defaultValue = fnpGetORObjectX("ActionItemTab_ChangeStatusPFList").getText().trim().toLowerCase();
			if (ActionItemName.equalsIgnoreCase("CertDecision")) {
				fnpWaitForVisible("PerformDecisionAI_ChangeStatusPFList");
				fnpPFList("PerformDecisionAI_ChangeStatusPFList", "PerformDecisionAI_ChangeStatusPFListOptions", "Completed");
			}else{
				fnpWaitForVisible("ActionItemTab_ChangeStatusPFList");
				fnpPFList("ActionItemTab_ChangeStatusPFList", "ActionItemTab_ChangeStatusPFListOptions", statusToChange);
			}
			

			fnpGetORObjectX(saveBtnInOR).click();
			fnpLoading_wait();

			
			
			/************It will come always for changing from created to completed from today onwards 11-07-2017  against IPM-6594********************/
			
			if (statusToChange.equalsIgnoreCase("completed")) {
				//String defaultValue = fnpGetORObjectX("ActionItemTab_ChangeStatusPFList").getText().trim().toLowerCase();
				/*				
				if ((defaultValue.contains("create")) | (defaultValue.equalsIgnoreCase(""))) {												
					fnpClickInDialog_OR("WOISR_AIConfirmationBtn");
				}
				
*/				
				fnpClickInDialog_OR("WOISR_AIConfirmationBtn");
			}

			/***************************************************************************************/
			
				
				
				

				
				
		//	}
			
			



			fnpCheckError("while Going to " + statusToChange + "  the  Action item ---" + ActionItemName);

			fnpWaitForVisibleHavingMultipleElements("TopMessage3");
			String SuccessfulMsg = fnpGetText_OR("TopMessage3");
			fnpMymsg("Top Message after " + statusToChange + "  the  Action item '" + ActionItemName + "' ----" + SuccessfulMsg);

			Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"), "Top message does not contain 'success' word, so might be action of " + statusToChange
					+ "  the pending Action item '" + ActionItemName + "'  is NOT successful.");


			//for coming back from Consolidated screen
			if ( ActionItemName.equalsIgnoreCase("TechnicalReview")|| ( ActionItemName.equalsIgnoreCase("CertIssue")  )  ) {

				fnpClick_OR("WO_BackBtn");	
			}
			
			
			
			
			if (ActionItemName.equalsIgnoreCase("CertDecision")) {
				
/*				fnpClick_OR("WO_BackBtn");
				Thread.sleep(1000);
				fnpClick_OR("WO_BackBtn");	
				*/
				
				fnpClick_OR("WorkOrderNoOnPerformCertDecisionAI");
				//fnpLoading_wait();
				
			}
			
			
			
			
			
			
			
			
			
			
			if (fnpCheckElementDisplayedImmediately("EditWOBtn")) {
				fnpClick_OR("EditWOBtn");// if edit button present
			}
			
			
			if (fnpCheckElementDisplayedImmediately("TaskTableAtSnapshotTab")) {
				fnpClick_OR("ISRTaskTab");// if goes to snapshot tab
			}				
			fnpCheckError(" after loading ");	
			

			
			
			int StatusColIndex = 0;
			String Status_ActionItemTable = null;
			int whileIchance = 0;
			while (true) {

				
				 taskAINameIndex = fnpFindColumnIndex("TaskTabTable_HeaderRow", WOISOTaskTab_TaskAISummaryTableColName_TaskAIName);
				 TaskRowNo = fnpFindRowContainsName("TaskTabTable", taskName, taskAINameIndex);
				 innerTableDataXpathForAI = OR.getProperty("ISRTaskInnerTableForActionItemsTableData_part1") + (TaskRowNo - 1)
						+ OR.getProperty("ISRTaskInnerTableForActionItemsTableData_part2");
				 innerTableHeaderXpathForAI = OR.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part1") + (TaskRowNo - 1)
						+ OR.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part2");
							
				  openIconXpath = OR.getProperty("TaskTabISR_Task_OpenIconPart1") + task+ OR.getProperty("TaskTabISR_Task_OpenIconPart2");
				
				
				
				
				
				
				
				
				
				try {
					whileIchance++;

					if ( (fnpCheckElementDisplayedImmediately_NOR(openIconXpath) & (!(fnpCheckElementDisplayedImmediately_NOR(innerTableHeaderXpathForAI))) )  ) {				
						fnpClick_NOR(openIconXpath);
					}
					
					
					
					itemDescColIndex = fnpFindColumnIndex_NOR(innerTableHeaderXpathForAI, "AI Name");
					actionItemInfoRowNo = fnpFindRow_NOR(innerTableDataXpathForAI, ActionItemName, itemDescColIndex);
					StatusColIndex = fnpFindColumnIndex_NOR(innerTableHeaderXpathForAI, actionItemTableStatusColName);
					
					Status_ActionItemTable = fnpFetchFromTable_NOR(innerTableDataXpathForAI, actionItemInfoRowNo, StatusColIndex);
					fnpMymsg("Current Status of this action item '" + ActionItemName + "' is ---" + Status_ActionItemTable + "  ---- after chances ---" + whileIchance);
				//	if (Status_ActionItemTable.equalsIgnoreCase(statusToChange)) {
					if (Status_ActionItemTable.toLowerCase().contains(statusToChange.toLowerCase())) {
						break;

					} else {
						if (whileIchance > Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
							break;
						} else {
							Thread.sleep(1000);
							// nothing to do...in loop
						}
					}

				} catch (org.openqa.selenium.StaleElementReferenceException s) {
					// nothing to do
					if (whileIchance > Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
						break;
					} else {
						Thread.sleep(1000);
						// nothing to do...in loop
					}
				}

			}
			

			Assert.assertTrue(Status_ActionItemTable.toLowerCase().contains(statusToChange.toLowerCase()), "After " + statusToChange + " the '" + ActionItemName
					+ "' action item , status is '" + Status_ActionItemTable + "' . ");
			

			fnpMymsg(Status_ActionItemTable + "  is the current status of  Action item  ---" + ActionItemName);
			// ***************Complete the pending Actionitem***********
			
			
			

			
			
			

		} catch (Throwable t) {

			fnpMymsg("  Failed while completing action item '" + ActionItemName + "  . Error is ---" + t.getMessage());
			fnpDesireScreenshot(ActionItemName + "Failed");

			String stackTrace = Throwables.getStackTraceAsString(t);
			String errorMsg = t.getMessage();
			errorMsg = errorMsg + "\n\n\n\n Failed while completing action item '" + ActionItemName + "    .See screenshot '" + ActionItemName + "Failed.' \n\n" + stackTrace;
			Exception c = new Exception(errorMsg);
			ErrorUtil.addVerificationFailure(c);
			throw c;

		}

	}

	
	public static void fnpProcessAI_ISR_TaskTab_NonConsoladatedScreen_and_basedOnAITypeCol(String task,String taskName,String ActionItemType, String statusToChange) throws Throwable {
		try {
			
			
			int taskAINameIndex = fnpFindColumnIndex("TaskTabTable_HeaderRow", WOISOTaskTab_TaskAISummaryTableColName_TaskAIName);
			int TaskRowNo = fnpFindRowContainsName("TaskTabTable", taskName, taskAINameIndex);
			String innerTableDataXpathForAI = OR.getProperty("ISRTaskInnerTableForActionItemsTableData_part1") + (TaskRowNo - 1)
					+ OR.getProperty("ISRTaskInnerTableForActionItemsTableData_part2");
			String innerTableHeaderXpathForAI = OR.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part1") + (TaskRowNo - 1)
					+ OR.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part2");
			
		
			String openIconXpath = OR.getProperty("TaskTabISR_Task_OpenIconPart1") + task+ OR.getProperty("TaskTabISR_Task_OpenIconPart2");
			
			// ***************Process the pending Action item***********

			//
			fnpMymsg(" Going to " + statusToChange + "  the pending Action item ---" + ActionItemType);
			fnpWaitForVisible_NotInOR(innerTableDataXpathForAI);

			int ActionItemColIndex = fnpFindColumnIndex_NOR(innerTableHeaderXpathForAI, "AI Number");
			
			int aiTypeColIndex = fnpFindColumnIndex_NOR(innerTableHeaderXpathForAI, "	AI Type");
			
			int actionItemInfoRowNo = fnpFindRow_NOR(innerTableDataXpathForAI, ActionItemType, aiTypeColIndex);
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
			
			fnpMymsg("Clicked on  Action Item '" + ActionItemType + "' in Table having no. is '" + actionItemNo + "' .");

			//fnpLoading_wait();
			
			
			
			if (ActionItemType.equalsIgnoreCase(WOISOTaskTab_ExtDBUpdateAIType)) {
				
				fnpWaitForVisible("ReassignAILkpBtn");
				String alreadyAssingee=fnpGetORObjectX("AssignedToLabelValue").getText().trim();				
				String reassignee=null;
				
				
				if (  (loginAsFullName==null) ||loginAsFullName.equalsIgnoreCase("") ) {
					//reassignee=(String) hashXlData.get("AccountManager").trim();
					if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
						reassignee=(String) hashXlData.get("AccountManager_Code").trim();
					}else{
						reassignee=(String) hashXlData.get("AccountManager").trim();
					}

				} else {
					//reassignee=loginAsFullName;
					if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
						reassignee=loginUser_code;
					}else{
						reassignee=loginAsFullName;
					}
				}
				
				
				
				
				
				//if (!(alreadyAssingee.equalsIgnoreCase(reassignee))) {	
				if (!(alreadyAssingee.contains(reassignee))) {
					String defaultValue = fnpGetORObjectX("ReassignAITxtBx").getAttribute("value").trim();
					//if (defaultValue.equalsIgnoreCase(reassignee)) {
					if (defaultValue.contains(reassignee)) {
						fnpMymsg("@  - default value is same as expected, so returning back.");
					} else {					
							fnpClick_OR("ReassignAILkpBtn");
		
							fnpMymsg("Just after  click ReassignAILkpBtn");
							fnpMymsg("Just before going to insert value of Account Manger");
							//fnpSearchNSelectFirstRadioBtn(2, reassignee, 1);
							if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
								fnpSearchNSelectFirstRadioBtn(1, reassignee, 1);
							}else{
								fnpSearchNSelectFirstRadioBtn(2, reassignee, 1);
							}

							
							fnpMymsg("Just after  inserting value of Account Manger");

							String EnteredAccountManager = fnpWaitTillTextBoxDontHaveValue("ReassignAITxtBx", "value");
							Assert.assertTrue(EnteredAccountManager.contains(reassignee), "Reassignee is not selected properly from lookup");
							fnpMymsg(" Reassignee is properly selected from client lookup");
					}
					//fnpGetORObjectX(saveBtnInOR).click();
					//fnpLoading_wait();

				}	
				
					

				
			}
			
			
			

			
			

			fnpWaitForVisible("ActionItemTab_ChangeStatusPFList");
			fnpPFList("ActionItemTab_ChangeStatusPFList", "ActionItemTab_ChangeStatusPFListOptions", statusToChange);
			

		//	fnpGetORObjectX(saveBtnInOR).click();
			
			fnpClick_OR("iAg_AI_SaveBtn");
			
			//fnpClick_OR("ContractReviewActionItemDialogCloseLink");
			
			fnpClickInDialog_OR("WOISR_AIConfirmationBtn");
			fnpLoading_wait_CheckLoadingIconOnlyIniPulse();


			fnpCheckError("while Going to " + statusToChange + "  the  Action item ---" + ActionItemType);

			fnpWaitForVisibleHavingMultipleElements("TopMessage3");
			String SuccessfulMsg = fnpGetText_OR("TopMessage3");
			fnpMymsg("Top Message after " + statusToChange + "  the  Action item '" + ActionItemType + "' ----" + SuccessfulMsg);

			Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"), "Top message does not contain 'success' word, so might be action of " + statusToChange
					+ "  the pending Action item '" + ActionItemType + "'  is NOT successful.");


			

			
			
			
			
			
			
			
			
			if (fnpCheckElementDisplayedImmediately("EditWOBtn")) {
				fnpClick_OR("EditWOBtn");// if edit button present
			}
			
			
			if (fnpCheckElementDisplayedImmediately("TaskTableAtSnapshotTab")) {
				fnpClick_OR("ISRTaskTab");// if goes to snapshot tab
			}				
			fnpCheckError(" after loading ");	
			

			
			
			int StatusColIndex = 0;
			String Status_ActionItemTable = null;
			int whileIchance = 0;
			while (true) {

				
				 taskAINameIndex = fnpFindColumnIndex("TaskTabTable_HeaderRow", WOISOTaskTab_TaskAISummaryTableColName_TaskAIName);
				 TaskRowNo = fnpFindRowContainsName("TaskTabTable", taskName, taskAINameIndex);
				 innerTableDataXpathForAI = OR.getProperty("ISRTaskInnerTableForActionItemsTableData_part1") + (TaskRowNo - 1)
						+ OR.getProperty("ISRTaskInnerTableForActionItemsTableData_part2");
				 innerTableHeaderXpathForAI = OR.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part1") + (TaskRowNo - 1)
						+ OR.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part2");
							
				  openIconXpath = OR.getProperty("TaskTabISR_Task_OpenIconPart1") + task+ OR.getProperty("TaskTabISR_Task_OpenIconPart2");
				
				
				
				
				
				
				
				
				
				try {
					whileIchance++;

					if ( (fnpCheckElementDisplayedImmediately_NOR(openIconXpath) & (!(fnpCheckElementDisplayedImmediately_NOR(innerTableHeaderXpathForAI))) )  ) {				
						fnpClick_NOR(openIconXpath);
					}
					
					
					
					 aiTypeColIndex = fnpFindColumnIndex_NOR(innerTableHeaderXpathForAI, "	AI Type");
					actionItemInfoRowNo = fnpFindRow_NOR(innerTableDataXpathForAI, ActionItemType, aiTypeColIndex);
					StatusColIndex = fnpFindColumnIndex_NOR(innerTableHeaderXpathForAI, actionItemTableStatusColName);
					
					Status_ActionItemTable = fnpFetchFromTable_NOR(innerTableDataXpathForAI, actionItemInfoRowNo, StatusColIndex);
					fnpMymsg("Current Status of this action item '" + ActionItemType + "' is ---" + Status_ActionItemTable + "  ---- after chances ---" + whileIchance);
				//	if (Status_ActionItemTable.equalsIgnoreCase(statusToChange)) {
					if (Status_ActionItemTable.toLowerCase().contains(statusToChange.toLowerCase())) {
						break;

					} else {
						if (whileIchance > Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
							break;
						} else {
							Thread.sleep(1000);
							// nothing to do...in loop
						}
					}

				} catch (org.openqa.selenium.StaleElementReferenceException s) {
					// nothing to do
					if (whileIchance > Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
						break;
					} else {
						Thread.sleep(1000);
						// nothing to do...in loop
					}
				}

			}
			

			Assert.assertTrue(Status_ActionItemTable.toLowerCase().contains(statusToChange.toLowerCase()), "After " + statusToChange + " the '" + ActionItemType
					+ "' action item , status is '" + Status_ActionItemTable + "' . ");
			

			fnpMymsg(Status_ActionItemTable + "  is the current status of  Action item  ---" + ActionItemType);
			// ***************Complete the pending Actionitem***********
			
			
			

			
			
			

		} catch (Throwable t) {

			fnpMymsg("  Failed while completing action item '" + ActionItemType + "  . Error is ---" + t.getMessage());
			fnpDesireScreenshot(ActionItemType + "Failed");

			String stackTrace = Throwables.getStackTraceAsString(t);
			String errorMsg = t.getMessage();
			errorMsg = errorMsg + "\n\n\n\n Failed while completing action item '" + ActionItemType + "    .See screenshot '" + ActionItemType + "Failed.' \n\n" + stackTrace;
			Exception c = new Exception(errorMsg);
			ErrorUtil.addVerificationFailure(c);
			throw c;

		}

	}

	
	
	
	public static void fnpLoginIntoOasis(String oasisURL,String oasisUserName,String oasisLoginPassword) throws Throwable{
		
		ArrayList<String> listoldtabs = new ArrayList<String>(driver.getWindowHandles());
		String originalHandle = driver.getWindowHandle();
		
		driver.get(oasisURL);
		Thread.sleep(15000);
		

		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());

		int oldTotalcount = listoldtabs.size();
		int newTotaltabsCount = tabs.size();
		int ii = 0;
		while (newTotaltabsCount != (oldTotalcount + 1)) {
			Thread.sleep(1000);
			tabs = new ArrayList<String>(driver.getWindowHandles());
			newTotaltabsCount = tabs.size();
			ii = ii + 1;
			//System.out.println("  ----waiting for tabs --"+ii);
			if (ii > Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
				
				break;
			}
		}

		// Thread.sleep(5000);
		Thread.sleep(2000);
		
		
		for (int i = 0; i < newTotaltabsCount; i++) {
			if (originalHandle.equalsIgnoreCase(tabs.get(i))) { // nothing
																// to do

			} else {
				driver.switchTo().window(tabs.get(i));
				Thread.sleep(1000);
				break;
			}

		}
		
		
		
		
		
		
		
		
		
		fnpMymsg("Navigating to url --->" + oasisURL);
		fnpMymsg("Login with this username --->" + oasisUserName);
		fnpMymsg("Login with this password --->" + oasisLoginPassword);
		driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(Long.parseLong(CONFIG.getProperty("pageLoadTime")), TimeUnit.SECONDS);

		
		
		
		//Thread.sleep(15000);
		
		

		
/*		
		WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(CONFIG.getProperty("genMax_waitTime")));
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("OasisUserNameTxt"))));
*/
		
		
		
		fnpWaitTillVisiblityOfElementNClickable_OR("OasisUserNameTxt");

		//fnpType("OR", "OasisUserNameTxt", oasisUserName);
		fnpType2_afterClearingTextGivingMuchTime("OR", "OasisUserNameTxt", oasisUserName);
		

		Thread.sleep(1000);

		//fnpType("OR", "OasisPasswordTxt", oasisLoginPassword);
		fnpType2_afterClearingTextGivingMuchTime("OR", "OasisPasswordTxt", oasisLoginPassword);

		fnpMymsg("Just before login clicked");

		fnpGetORObjectX("OasisLoginBtn").click();
		Thread.sleep(5000);
		fnpMymsg("Just login(Enter button) clicked");

		fnpMymsg("Just after loading wait after login(Enter button) clicked");
		//errorMessage
		if (fnpCheckElementDisplayedImmediately("Oasis_error_inDiv")) {
			throw new Exception("Login is not successfull.");
		}
		
		int whileCounter=0;
		int maxWhileCounter=5;
		while(whileCounter<maxWhileCounter){
			try {
				whileCounter++;
				fnpWaitForVisible("OasislogOut_xpath", 90);
				break;
			} catch (Throwable t) {
				if (whileCounter==maxWhileCounter) {
					throw t;
				}
				driver.navigate().refresh();
				Thread.sleep(20000);
			}					
		}
		
		
		//Assert.assertTrue(fnpCheckElementDisplayed("OasislogOut_xpath"), "Oasis Logout link is not present.");	
		fnpMymsg("Oasis Logout link is present .");
		
		Assert.assertTrue(fnpCheckElementPresenceImmediately("OasisMenuHeader"), "Oasis's Menu header is not present.");
		fnpMymsg("Oasis's Menu header  is present .");
		
	}
	
	
	public static void fnpLoginIntoOasisForOasis_tryingMulitpleTimes_NotCheckingLogoutBtn(String oasisURL,String oasisUserName,String oasisLoginPassword) throws Throwable{
		
		ArrayList<String> listoldtabs = new ArrayList<String>(driver.getWindowHandles());
		String originalHandle = driver.getWindowHandle();
		
		driver.get(oasisURL);
		
		Thread.sleep(15000);

		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());

		int oldTotalcount = listoldtabs.size();
		int newTotaltabsCount = tabs.size();
		int ii = 0;
		while (newTotaltabsCount != (oldTotalcount + 1)) {
			Thread.sleep(1000);
			tabs = new ArrayList<String>(driver.getWindowHandles());
			newTotaltabsCount = tabs.size();
			ii = ii + 1;
			//System.out.println("  ----waiting for tabs --"+ii);
			if (ii > Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
				
				break;
			}
		}

		// Thread.sleep(5000);
		Thread.sleep(2000);
		
		
		for (int i = 0; i < newTotaltabsCount; i++) {
			if (originalHandle.equalsIgnoreCase(tabs.get(i))) { // nothing
																// to do

			} else {
				driver.switchTo().window(tabs.get(i));
				Thread.sleep(1000);
				break;
			}

		}
		
		
		
		
		
		
		
		
		
		fnpMymsg("Navigating to url --->" + oasisURL);
		fnpMymsg("Login with this username --->" + oasisUserName);
		fnpMymsg("Login with this password --->" + oasisLoginPassword);
		driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(Long.parseLong(CONFIG.getProperty("pageLoadTime")), TimeUnit.SECONDS);

		
		
/*		
		WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(CONFIG.getProperty("genMax_waitTime")));
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("OasisUserNameTxt"))));

*/		
		
		fnpWaitTillVisiblityOfElementNClickable_OR("OasisUserNameTxt");
		

		//fnpType("OR", "OasisUserNameTxt", oasisUserName);
		fnpType2_afterClearingTextGivingMuchTime("OR", "OasisUserNameTxt", oasisUserName);
		

		Thread.sleep(1000);

		//fnpType("OR", "OasisPasswordTxt", oasisLoginPassword);
		fnpType2_afterClearingTextGivingMuchTime("OR", "OasisPasswordTxt", oasisLoginPassword);

		fnpMymsg("Just before login clicked");


		fnpGetORObjectX("OasisLoginBtn").click();
		Thread.sleep(5000);
		fnpMymsg("Just login(Enter button) clicked");
	//	fnpLoading_wait();
		fnpMymsg("Just after loading wait after login(Enter button) clicked");
		//errorMessage

		
		Thread.sleep(5000);
		driver.switchTo().alert().accept();
		Thread.sleep(5000);
		
		
	}
	
	
	public static void fnpCommonProcessQuestionnairesSet__In_Oasis(String questionnaireSet) throws Throwable{
		
		
		fnpMymsg("");
		fnpMymsg(" Now going to insert the values in Oasis Questionnaire .");
		//String NoOfSets[] = table.get("QuestionNAnswerSets").split("::");
		String NoOfSets[] = questionnaireSet.split("::");
		fnpMymsg("No. of question sets are ---" + NoOfSets.length);
		fnpMymsg("");
		fnpMymsg("");

		for (int j = 0; j < NoOfSets.length; j++) {
/*			if (NoOfSets.length > 1 && j < (NoOfSets.length - 1)) {
				// Thread.sleep(1000);
				fnpWaitForVisible("Prop_Questionnaire_SaveNNextBtn");
			}*/
			fnpMymsg("***********************************************");
			String QuestionsSet = NoOfSets[j];
			fnpMymsg("Question set is--" + QuestionsSet);
			String NoOfQuestionsInCurrentSet[] = QuestionsSet.split(":");

			fnpMymsg("No. of questions are ---" + NoOfQuestionsInCurrentSet.length);
			fnpMymsg("");
			fnpMymsg("");
			for (int i = 0; i < NoOfQuestionsInCurrentSet.length; i++) {
				fnpMymsg("");
				fnpMymsg("");
				fnpMymsg("Each question and its value are--" + NoOfQuestionsInCurrentSet[i]);
				String eachQuestion[] = NoOfQuestionsInCurrentSet[i].split(",");
				String QuestionNo = fnpremoveFormatting(eachQuestion[0]);
				fnpMymsg("Question no. is--" + QuestionNo);

				String AnswerValue = fnpremoveFormatting(eachQuestion[1]);
				fnpMymsg("Its answer is--" + AnswerValue);


				String type = fnpremoveFormatting(eachQuestion[2]);
				fnpMymsg("Its type is--" + type);
				
				
			//	fnpFillAnswerTo_Oasis_QuestionnaireDynamically_processHiddenAlso(QuestionNo, AnswerValue);
				fnpFillAnswerTo_OasisQuestionnaireDynamically_processHiddenAlso(QuestionNo, AnswerValue,type);


				Thread.sleep(500);

			}


		}

		
	}
	
	
	
	
	
	
	
	
	
	

	// Question No,Answer,AnswerFieldType,AnswerFieldIndexAtThispointOfTime
	public static void fnpFillAnswerTo_Oasis_QuestionnaireDynamically_processHiddenAlso(
																	String questNo,
																	String answervalue) throws Throwable {

		fnpMymsg("Going to select the value '" + answervalue + "' for Question --" + questNo);
		int questionNo=Integer.parseInt(questNo);

		//String questXpath = ".//label[contains(text(),'" + questNo + " :')]";
		String questXpath = ".//*[@id='questionList']/table/tbody/tr[" + (questionNo+1) + " ]/td[2]/input[contains(@value,'"+answervalue+"')]";
		WebElement quest=null;
		try{
		  quest = driver.findElement(By.xpath(questXpath));
		}		
		catch(org.openqa.selenium.NoSuchElementException  nse){
			fnpMymsg("Might be element with this xpath '"+questXpath+"' is not present or visible.");
			throw new Exception("Might be element with this xpath '"+questXpath+"' is not present or visible.");
		}
		

		quest.click();

	}
	
	
	
	public static void fnpVerifyingSubHeadingForQuestinnairesInOasis(String valueToBeSelect) throws Throwable{

		String subPageHeadingContains = (valueToBeSelect.split(":"))[0].trim();
		//String subPageHeadingXpath = ".//td[@class='titleSubPage'][contains(text(),'" + subPageHeadingContains + "')]";
		//fnpWaitForVisible_NotInOR(subPageHeadingXpath, 15);
		
		try{
			int waitTime=60;//here for 60 seconds
			int whileCounterForHeading=0;
			List<WebElement> headingList;
			String headingText="";
			while(whileCounterForHeading<waitTime){// here for 60 seconds
				headingList = driver.findElements(By.xpath(".//td[@class='titleSubPage']"));
				for (Iterator iterator = headingList.iterator(); iterator.hasNext();) {
					WebElement webElement = (WebElement) iterator.next();
					headingText=webElement.getText();
					if (headingText.contains(subPageHeadingContains)) {
						break;						
					}
					
				}
				if (headingText.contains(subPageHeadingContains)) {
					break;					
				}
				
				if (  (!(headingText.contains(subPageHeadingContains)))  & (whileCounterForHeading==60) ){
					fnpMymsg("SubHeading does not contains '"+subPageHeadingContains+"' even after "+waitTime+" seconds");
					throw new Exception("SubHeading does not contains '"+subPageHeadingContains+"' even after "+waitTime+" seconds");
								
				}
				Thread.sleep(1000);
				whileCounterForHeading++;
			}
			}catch(Throwable t){
				fnpMymsg("Error is thrown while matching subTitle heading  --"+t.getMessage());
				throw new Exception("Error is thrown while matching subTitle heading  --"+t.getMessage());
			}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


	// Question No,Answer,AnswerFieldType,AnswerFieldIndexAtThispointOfTime
	// This is based on sequence of Question no. , not name of Question  i.e. Question no.2 means row 2+1=3
	public static void fnpFillAnswerTo_OasisQuestionnaireDynamically_processHiddenAlso(
																	String questNo,
																	String answervalue,
																	String type) throws Throwable {

		fnpMymsg("Going to select the value '" + answervalue + "' for Question --" + questNo);

		int rowNo=Integer.parseInt(questNo)+1;
		
		String questXpath =".//*[@id='questionList']/table/tbody/tr["+rowNo+"]/td[1]";
		

		String answerRadioXpath = questXpath+"/following-sibling::td/input[@type='radio'][contains(@value,'"+answervalue+"')]";
		String selectedRadioValueXpath=".//*[@id='questionList']/table/tbody/tr["+rowNo+"]/td[2]/input[contains(@id,'answer')]";
		
		String answerTextAreaBoxXpath = questXpath+"/following-sibling::td/textarea[contains(@id,'answer')]";



		//if (fnpCheckElementPresenceImmediately_NotInOR(answerRadioXpath)) {
		if (type.equalsIgnoreCase("radio")) {					
		if (fnpCheckElementPresence_NOR(answerRadioXpath)) {
			fnpMymsg("Radio is present for Question--" + questNo);


			int whileloopCount = 0;
			while (true) {

				String checkedAttributeOfRadioBtn;
				String checkedLabel = null;

						try {
							WebElement fac = driver.findElement(By.xpath(answerRadioXpath));
							 Actions action = new Actions(driver);
						     action.moveToElement(fac).click().build().perform();
						     Thread.sleep(1000);
						     String finalSelectedValue="";
						     String checkedXpathOFRadioBtn="";

									while (true) {
										Thread.sleep(500);
	
										//String checkedXpathOFRadioBtn=answerRadioXpath+"[@checked='']";
										
										// checkedXpathOFRadioBtn=answerRadioXpath+"/../input[contains(@id,'answer')]";
										// finalSelectedValue=fnpGetORObjectX___NOR(checkedXpathOFRadioBtn).getAttribute("value");
										
										// finalSelectedValue=fnpGetORObjectX___NOR(selectedRadioValueXpath).getAttribute("value");
										 finalSelectedValue=driver.findElement(By.xpath(selectedRadioValueXpath)).getAttribute("value");
										
										//if (fnpCheckElementDisplayedImmediately_NOR(checkedXpathOFRadioBtn)) {
										if (!(finalSelectedValue.contains(answervalue))) {
											fnpMymsg("Radio button is NOT selected properly for for Question--" + questNo);
											throw new Exception("Radio button is NOT selected properly for for Question--" + questNo);
										}else{
											fnpMymsg("Radio button is selected properly for for Question--" + questNo);
											checkedLabel="checked properly";
										}


										if (checkedLabel != null) {
											break;
				
										} else {
				
											whileloopCount++;
											if (whileloopCount < 3) {
												fnpMymsg("@@@    ---Radio button is not checked properly  for  Question--" + questNo+
														"  as expected value attribute contains--'"+answervalue+"' but  currently it is --'"+finalSelectedValue+"' for chance -----"+whileloopCount);
												fac = driver.findElement(By.xpath(answerRadioXpath));
												action.moveToElement(fac).click().build().perform();
												Thread.sleep(500);
											} else {
												break;
											}
										}
										}

										if (checkedLabel != null) {
											if (checkedLabel.equalsIgnoreCase("checked properly")) {
												fnpMymsg("Radio button for question '" + questNo + "' has  been selected properly.");
												break;
											} else {
												// continue;
/*												fnpMymsg("  checked attribute is present in question no.--" + questNo + " and it means button is not checked properly .");
												throw new Exception("   checked attribute is present in question no.--" + questNo + " and it means button is not checked properly .");
												*/
												
												fnpMymsg(" Radio button is not checked properly   for  Question--" + questNo+
														"  as expected value attribute contains--'"+answervalue+"' but  currently it is --'"+finalSelectedValue+"'.");
												throw new Exception("   Radio button is not checked properly   for  Question--" + questNo+
														"  as expected value attribute contains--'"+answervalue+"' but  currently it is --'"+finalSelectedValue+"'.");
												
											}
					
										} else {
											// continue;
/*											fnpMymsg("  checked attribute is present in question no.--" + questNo + " and it means button is not checked properly .");
											throw new Exception("   checked attribute is present in question no.--" + questNo + " and it means button is not checked properly .");
											
											*/
											fnpMymsg(" Radio button is not checked properly   for  Question--" + questNo+
													"  as expected value attribute contains--'"+answervalue+"' but  currently it is --'"+finalSelectedValue+"'.");
											throw new Exception("   Radio button is not checked properly   for  Question--" + questNo+
													"  as expected value attribute contains--'"+answervalue+"' but  currently it is --'"+finalSelectedValue+"'.");
												
										}
										
										
										
										
										

									} catch (StaleElementReferenceException s) {
										whileloopCount++;
										if (whileloopCount < 3) {
											Thread.sleep(2000);
										} else {
											throw new Exception(s.getMessage());
										}
									}
				
								catch (Throwable t) {
									fnpMymsg("********   Watch here in catch block is ---" + t.getMessage());
										Thread.sleep(1000);
										whileloopCount++;
										if (whileloopCount < 3) {
											// Thread.sleep(2000);
											// continue;
											Thread.sleep(2000);
										} else {
											throw new Exception(t.getMessage() + "\n\n Error thrown in question no.--" + questNo + " and error is --" + t.getMessage());
										}
								}
						}
				}else{
					fnpMymsg("Radio button is NOT present for Question--" + questNo);
					throw new Exception("Radio button is NOT present for Question--" + questNo);
				}
		}

		//if (fnpCheckElementPresenceImmediately_NotInOR(answerTextAreaBoxXpath)) {
		if (type.equalsIgnoreCase("textarea")) {					
		if (fnpCheckElementPresence_NOR(answerTextAreaBoxXpath)) {
			String insertedValue;
			fnpMymsg("Text box is present for Question--" + questNo);
			driver.findElement(By.xpath(answerTextAreaBoxXpath)).clear();
			Thread.sleep(500);
			driver.findElement(By.xpath(answerTextAreaBoxXpath)).sendKeys(answervalue);
			Thread.sleep(1000);

			fnpMymsg("Value has been inserted and now going to verify whether right value is inserted or not.");

			
			insertedValue = driver.findElement(By.xpath(answerTextAreaBoxXpath)).getText();
			fnpMymsg("   just to watch inserted value in textarea is --"+insertedValue);
		
			insertedValue = driver.findElement(By.xpath(answerTextAreaBoxXpath)).getAttribute("value");
			fnpMymsg("   just to watch inserted value in textarea is --"+insertedValue);
			


			int whileloopCountTxtbox = 0;
			while (!insertedValue.equalsIgnoreCase(answervalue)) {
				whileloopCountTxtbox++;
				if (whileloopCountTxtbox < 3) {
					Thread.sleep(2000);
					insertedValue = driver.findElement(By.xpath(answerTextAreaBoxXpath)).getAttribute("value");
					insertedValue = driver.findElement(By.xpath(answerTextAreaBoxXpath)).getText();
				} else {
					break;
				}

			}
			fnpMymsg("Inserted value in text box is--" + insertedValue);
			Assert.assertEquals(insertedValue, answervalue, "Value in text box for this question '" + questNo + "' has NOT been inserted  properly.");
			fnpMymsg("Value in text box for this question '" + questNo + "' has  been inserted  properly.");

		}else{
			fnpMymsg("textarea is NOT present for Question--" + questNo);
			throw new Exception("textarea is NOT present for Question--" + questNo);
		}
		}
		
		
		
		
}


	
	
	
	// Function to Drop WO ISR and delete some Data From DB from DB
	//public static void fnpCallGoldenProcedure(int copyFromAudit, int copyToAudit, String user, String result) throws Throwable {
	public static void fnpCallGoldenProcedure_COPY_AUDIT_TRANSACTION_NOTUSINGTHISNOW(int copyFromAudit, int copyToAudit, String user) throws Throwable {
		String rate = null;
		
		//System.out.println("copyFromAudit = "+copyFromAudit+"   copyToAudit = "+copyToAudit+"   user = "+user);

		fnpMymsg("******************");
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			fnpMymsg("******* Oracle JDBC Driver Registered *******");
		} catch (ClassNotFoundException e) {
			fnpMymsg("****************Oracle JDBC Driver is NOT found *************");
			fnpMymsg("########################################################################");
			e.printStackTrace();
			throw new Exception(e.getMessage());
			// return;
		}

		Connection connection = null;
		try {
			connection=fnpGetDBConnection();
			String stringOFCallingGoldenProcedure = "{call ECAP.EC_PKG_AUDIT_UTIL.COPY_AUDIT_TRANSACTION(?,?,?,?)}";
			CallableStatement cStmt = connection.prepareCall(stringOFCallingGoldenProcedure);

			cStmt.setInt(1, copyFromAudit);
			cStmt.setInt(2, copyToAudit);
			cStmt.setString(3, user);
			cStmt.registerOutParameter(4, java.sql.Types.VARCHAR);

			// execute COPY_AUDIT_TRANSACTION store procedure
			cStmt.executeUpdate();
			String outResult = cStmt.getString(4);
			//System.out.println("Stored Procedure Result : " + outResult);
			fnpMymsg("Stored Procedure 1 Result : " + outResult);
			
			
			
			connection.commit();

			//connection.close();

		} catch (SQLException e) {
			fnpMymsg("*********************************************** Either connection Failed or some different error with Database,   Getting Error >>  " + e.getMessage().trim());
			fnpMymsg("=========================================================================================================================================");
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		
		finally{
			connection.close();
		}
		fnpMymsg("******************");

		// return rate;
	}
	
	
	
	
	

	


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void fnsUploadFile(String BrowseBttnXpath,String BrowsePopupName) throws Throwable{
		try{
			String FileUploadPath = System.getProperty("user.dir") + "\\docs\\This is test pdf document.pdf";
			WebElement Browser = driver.findElement(By.xpath(OR.getProperty(BrowseBttnXpath)));
			//WebElement Browser = fnpGetORObjectX(BrowseBttnXpath);
			Browser.sendKeys(FileUploadPath);
			Thread.sleep(5000);

		
		}catch(NoSuchWindowException W){
			isTestCasePass = false;
			throw new SkipException(W.getMessage());
		}catch(Throwable t){
			isTestCasePass = false;
			fnpDesireScreenshot("FileUploadFail");
			throw new Exception(Throwables.getStackTraceAsString(t));	}
}
	
	
	public static void fnpIgnoreWarningInIPulse(String expectedWarning) throws Throwable{
		

		
		
		fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
		fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
		String errorToCheck=null;
		for (int wait = 0; wait < 2; wait++) 
		{
			if (fnpGetORObject_list("ErrorMessage", 2).size() > 0)
			{
				List<WebElement> objList = fnpGetORObject_list("ErrorMessage", 1);

				for (int i = 0; i < objList.size(); i++)
				{
					if ((objList.get(i).isDisplayed()) & (!((fnpReturnText_notCallItDirectly(objList.get(i)).equalsIgnoreCase(""))))) 
					{
						errorToCheck=objList.get(i).getText();
						if (errorToCheck.contains(expectedWarning)) 
						{
							//ignore nothing to do
						} else {
							throw new Exception("Error thrown by application is ----"+errorToCheck);
						}
					}

				}
			}
		}

		
	}
	
	
	
	
	

	public static void fnpVerifyAIStatusInISR(String openIconForExpandingXpathNOR,String innerTableHeaderXpathForAI,String innerTableDataXpathForAI,String ActionItemName,String statusToChange) throws Throwable{	
		
		
		
		if (fnpCheckElementDisplayedImmediately("EditWOBtn")) {
			fnpClick_OR("EditWOBtn");// if edit button present
		}
		
		
		if (fnpCheckElementDisplayedImmediately("TaskTableAtSnapshotTab")) {
			fnpClick_OR("ISRTaskTab");// if goes to snapshot tab
		}				
		fnpCheckError(" after loading ");	
		
		
		
		
		
		

		int StatusColIndex = 0;
		String Status_ActionItemTable = null;
		int whileIchance = 0;
		
		int taskAINameIndex=0;

		while (true) {

	
			try {
				whileIchance++;
				

				
				if ( (fnpCheckElementDisplayedImmediately_NOR(openIconForExpandingXpathNOR) & (!(fnpCheckElementDisplayedImmediately_NOR(innerTableHeaderXpathForAI))) )  ) {				
					fnpClick_NOR(openIconForExpandingXpathNOR);
				}
				
				
				
				int itemDescColIndex = fnpFindColumnIndex_NOR(innerTableHeaderXpathForAI, "AI Name");
				int actionItemInfoRowNo = fnpFindRow_NOR(innerTableDataXpathForAI, ActionItemName, itemDescColIndex);
				StatusColIndex = fnpFindColumnIndex_NOR(innerTableHeaderXpathForAI, actionItemTableStatusColName);
				
				Status_ActionItemTable = fnpFetchFromTable_NOR(innerTableDataXpathForAI, actionItemInfoRowNo, StatusColIndex);
				fnpMymsg("Current Status of this action item '" + ActionItemName + "' is ---" + Status_ActionItemTable + "  ---- after chances ---" + whileIchance);
			//	if (Status_ActionItemTable.equalsIgnoreCase(statusToChange)) {
				if (Status_ActionItemTable.toLowerCase().contains(statusToChange.toLowerCase())) {
					break;

				} else {
					if (whileIchance > Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
						break;
					} else {
						Thread.sleep(1000);
						// nothing to do...in loop
					}
				}

			} catch (org.openqa.selenium.StaleElementReferenceException s) {
				// nothing to do
				if (whileIchance > Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					break;
				} else {
					Thread.sleep(1000);
					// nothing to do...in loop
				}
			}

		}
		

		
		Assert.assertTrue(Status_ActionItemTable.toLowerCase().contains(statusToChange.toLowerCase()), "After " + statusToChange + " the '" + ActionItemName
				+ "' action item , status is '" + Status_ActionItemTable + "' . ");
		

		fnpMymsg(Status_ActionItemTable + "  is the current status of  Action item  ---" + ActionItemName);
		// ***************Complete the pending Actionitem***********
		
		
	}
	
	
	
	
	
	
	
	
	
	//public static void fnpVerifyAIStatusInISR(String openIconForExpandingXpathNOR,String headerTableXpathNameNOR,String dataTableXpathNameNOR,String ActionItemName,String statusToChange) throws Throwable{
		
	public static void fnpVerifyAIStatusInISR(String taskType,String taskName,String ActionItemName,String statusToChange) throws Throwable{	
		
		driver.navigate().refresh(); // workaround as sometime after coming from inside task using Back to button, tasks on which we are working is not visible/present. Faced this multiple times		
	//	driver.get(driver.getCurrentUrl());// workaround as sometime after coming from inside task using Back to button, tasks on which we are working is not visible/present. Faced this multiple times		
	//	fnpWaitForVisible("TaskTabTable_HeaderRow");
		fnpLoading_wait();
		
		if (fnpCheckElementDisplayedImmediately("EditWOBtn")) {
			fnpClick_OR("EditWOBtn");// if edit button present
		}
		
		
		if (fnpCheckElementDisplayedImmediately("TaskTableAtSnapshotTab")) {
			fnpClick_OR("ISRTaskTab");// if goes to snapshot tab
		}				
		fnpCheckError(" after loading ");	
		
		
		
		
		
		

		int StatusColIndex = 0;
		String Status_ActionItemTable = null;
		int whileIchance = 0;
		
		int taskAINameIndex=0;
		int TaskRowNo=0;
		String innerTableDataXpathForAI ="";
		String innerTableHeaderXpathForAI ="";
		String openIconForExpandingXpathNOR = "";
		while (true) {

			
			 taskAINameIndex = fnpFindColumnIndex("TaskTabTable_HeaderRow", WOISOTaskTab_TaskAISummaryTableColName_TaskAIName);
			 TaskRowNo = fnpFindRowContainsName("TaskTabTable", taskName, taskAINameIndex);
			 innerTableDataXpathForAI = OR.getProperty("ISRTaskInnerTableForActionItemsTableData_part1") + (TaskRowNo - 1)
						+ OR.getProperty("ISRTaskInnerTableForActionItemsTableData_part2");
			 innerTableHeaderXpathForAI = OR.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part1") + (TaskRowNo - 1)
						+ OR.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part2");						
			 openIconForExpandingXpathNOR = OR.getProperty("TaskTabISR_Task_OpenIconPart1") + taskType+ OR.getProperty("TaskTabISR_Task_OpenIconPart2");
			
			
			
			try {
				whileIchance++;
				

				
				if ( (fnpCheckElementDisplayedImmediately_NOR(openIconForExpandingXpathNOR) & (!(fnpCheckElementDisplayedImmediately_NOR(innerTableHeaderXpathForAI))) )  ) {				
					fnpClick_NOR(openIconForExpandingXpathNOR);
				}
				
				
				
				int itemDescColIndex = fnpFindColumnIndex_NOR(innerTableHeaderXpathForAI, "AI Name");
				int actionItemInfoRowNo = fnpFindRow_NOR(innerTableDataXpathForAI, ActionItemName, itemDescColIndex);
				StatusColIndex = fnpFindColumnIndex_NOR(innerTableHeaderXpathForAI, actionItemTableStatusColName);
				
				Status_ActionItemTable = fnpFetchFromTable_NOR(innerTableDataXpathForAI, actionItemInfoRowNo, StatusColIndex);
				fnpMymsg("Current Status of this action item '" + ActionItemName + "' is ---" + Status_ActionItemTable + "  ---- after chances ---" + whileIchance);
			//	if (Status_ActionItemTable.equalsIgnoreCase(statusToChange)) {
				if (Status_ActionItemTable.toLowerCase().contains(statusToChange.toLowerCase())) {
					break;

				} else {
					if (whileIchance > Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
						break;
					} else {
						Thread.sleep(1000);
						// nothing to do...in loop
					}
				}

			} catch (org.openqa.selenium.StaleElementReferenceException s) {
				// nothing to do
				if (whileIchance > Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					break;
				} else {
					Thread.sleep(1000);
					// nothing to do...in loop
				}
			}

		}
		

		
		Assert.assertTrue(Status_ActionItemTable.toLowerCase().contains(statusToChange.toLowerCase()), "After " + statusToChange + " the '" + ActionItemName
				+ "' action item , status is '" + Status_ActionItemTable + "' . ");
		

		fnpMymsg(Status_ActionItemTable + "  is the current status of  Action item  ---" + ActionItemName);
		// ***************Complete the pending Actionitem***********
		
		
	}
	
	
	//This is generic and can be used for all, using when col name is AI name or using AI Type , I have created this becasue ExtDBUpdate ai name is dynamic, so taking ai type
	public static void fnpVerifyAIStatusInISR_2(String taskType,String taskName,String colNameForAINameOrAIType,String ActionItemNameOrAIType,String statusToChange) throws Throwable{	
		
		
		
		if (fnpCheckElementDisplayedImmediately("EditWOBtn")) {
			fnpClick_OR("EditWOBtn");// if edit button present
		}
		
		
		if (fnpCheckElementDisplayedImmediately("TaskTableAtSnapshotTab")) {
			fnpClick_OR("ISRTaskTab");// if goes to snapshot tab
		}				
		fnpCheckError(" after loading ");	
		
		
		
		
		
		

		int StatusColIndex = 0;
		String Status_ActionItemTable = null;
		int whileIchance = 0;
		
		int taskAINameIndex=0;
		int TaskRowNo=0;
		String innerTableDataXpathForAI ="";
		String innerTableHeaderXpathForAI ="";
		String openIconForExpandingXpathNOR = "";
		while (true) {

			
			 taskAINameIndex = fnpFindColumnIndex("TaskTabTable_HeaderRow", WOISOTaskTab_TaskAISummaryTableColName_TaskAIName);
			 TaskRowNo = fnpFindRowContainsName("TaskTabTable", taskName, taskAINameIndex);
			 innerTableDataXpathForAI = OR.getProperty("ISRTaskInnerTableForActionItemsTableData_part1") + (TaskRowNo - 1)
						+ OR.getProperty("ISRTaskInnerTableForActionItemsTableData_part2");
			 innerTableHeaderXpathForAI = OR.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part1") + (TaskRowNo - 1)
						+ OR.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part2");						
			 openIconForExpandingXpathNOR = OR.getProperty("TaskTabISR_Task_OpenIconPart1") + taskType+ OR.getProperty("TaskTabISR_Task_OpenIconPart2");
			
			
			
			try {
				whileIchance++;
				

				
				if ( (fnpCheckElementDisplayedImmediately_NOR(openIconForExpandingXpathNOR) & (!(fnpCheckElementDisplayedImmediately_NOR(innerTableHeaderXpathForAI))) )  ) {				
					fnpClick_NOR(openIconForExpandingXpathNOR);
				}
				
				
				
				int colNameForAINameOrAITypeColIndex = fnpFindColumnIndex_NOR(innerTableHeaderXpathForAI, colNameForAINameOrAIType);
				int actionItemInfoRowNo = fnpFindRow_NOR(innerTableDataXpathForAI, ActionItemNameOrAIType, colNameForAINameOrAITypeColIndex);
				StatusColIndex = fnpFindColumnIndex_NOR(innerTableHeaderXpathForAI, actionItemTableStatusColName);
				
				Status_ActionItemTable = fnpFetchFromTable_NOR(innerTableDataXpathForAI, actionItemInfoRowNo, StatusColIndex);
				fnpMymsg("Current Status of this action item '" + ActionItemNameOrAIType + "' is ---" + Status_ActionItemTable + "  ---- after chances ---" + whileIchance);
			//	if (Status_ActionItemTable.equalsIgnoreCase(statusToChange)) {
				if (Status_ActionItemTable.toLowerCase().contains(statusToChange.toLowerCase())) {
					break;

				} else {
					if (whileIchance > Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
						break;
					} else {
						Thread.sleep(1000);
						// nothing to do...in loop
					}
				}

			} catch (org.openqa.selenium.StaleElementReferenceException s) {
				// nothing to do
				if (whileIchance > Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					break;
				} else {
					Thread.sleep(1000);
					// nothing to do...in loop
				}
			}

		}
		

		
		Assert.assertTrue(Status_ActionItemTable.toLowerCase().contains(statusToChange.toLowerCase()), "After " + statusToChange + " the '" + ActionItemNameOrAIType
				+ "' action item , status is '" + Status_ActionItemTable + "' . ");
		

		fnpMymsg(Status_ActionItemTable + "  is the current status of  Action item  ---" + ActionItemNameOrAIType);
		// ***************Complete the pending Actionitem***********
		
		
	}
	
	
	
	
	
	//it is used when two same task name present in a table
public static void fnpVerifyAIStatusInISR_basedOnTaskNo(String taskNoAtTaskTab,   String taskType, String taskName,  String ActionItemName,  String statusToChange) throws Throwable{	
	
	driver.navigate().refresh();// sometime refreshing issue comes 
	fnpLoading_wait();
	fnpWaitForVisible("ISRTaskTab");
	fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
	
	String taskNo=taskNoAtTaskTab;//Integer.toString(intTaskNo);
	
	if (fnpCheckElementDisplayedImmediately("EditWOBtn")) {
		fnpClick_OR("EditWOBtn");// if edit button present
	}
	
	
	if (fnpCheckElementDisplayedImmediately("TaskTableAtSnapshotTab")) {
		fnpClick_OR("ISRTaskTab");// if goes to snapshot tab
	}				
	fnpCheckError(" after loading ");	
	
	
	
	
	
	

	int StatusColIndex = 0;
	String Status_ActionItemTable = null;
	int whileIchance = 0;
	
	//int taskAINameIndex=0;
	int taskAINoIndex=0;
	int TaskRowNo=0;
	String innerTableDataXpathForAI ="";
	String innerTableHeaderXpathForAI ="";
	String openIconXpath = "";
	while (true) {

		 taskAINoIndex = fnpFindColumnIndex("TaskTabTable_HeaderRow", WOISOTaskTab_TaskAISummaryTableColName_TaskAINo);
		 TaskRowNo = fnpFindRowContainsName("TaskTabTable", taskNo, taskAINoIndex);
		 innerTableDataXpathForAI = OR.getProperty("ISRTaskInnerTableForActionItemsTableData_part1") + (TaskRowNo - 1)
					+ OR.getProperty("ISRTaskInnerTableForActionItemsTableData_part2");
		 innerTableHeaderXpathForAI = OR.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part1") + (TaskRowNo - 1)
					+ OR.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part2");						
		
		 openIconXpath = OR.getProperty("TaskTabISR_Task_OpenIcon_BasedOnTaskNo_Part1") + taskNo+ OR.getProperty("TaskTabISR_Task_OpenIcon_BasedOnTaskNo__Part2");
		
		
		try {
			whileIchance++;
			

			
			if ( (fnpCheckElementDisplayedImmediately_NOR(openIconXpath) & (!(fnpCheckElementDisplayedImmediately_NOR(innerTableHeaderXpathForAI))) )  ) {				
				fnpClick_NOR(openIconXpath);
			}
			
			
			
			int itemDescColIndex = fnpFindColumnIndex_NOR(innerTableHeaderXpathForAI, "AI Name");
			int actionItemInfoRowNo = fnpFindRow_NOR(innerTableDataXpathForAI, ActionItemName, itemDescColIndex);
			StatusColIndex = fnpFindColumnIndex_NOR(innerTableHeaderXpathForAI, actionItemTableStatusColName);
			
			Status_ActionItemTable = fnpFetchFromTable_NOR(innerTableDataXpathForAI, actionItemInfoRowNo, StatusColIndex);
			fnpMymsg("Current Status of this action item '" + ActionItemName + "' is ---" + Status_ActionItemTable + "  ---- after chances ---" + whileIchance);
		//	if (Status_ActionItemTable.equalsIgnoreCase(statusToChange)) {
			if (Status_ActionItemTable.toLowerCase().contains(statusToChange.toLowerCase())) {
				break;

			} else {
				if (whileIchance > Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					break;
				} else {
					Thread.sleep(1000);
					// nothing to do...in loop
				}
			}

		} catch (org.openqa.selenium.StaleElementReferenceException s) {
			// nothing to do
			if (whileIchance > Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
				break;
			} else {
				Thread.sleep(1000);
				// nothing to do...in loop
			}
		}

	}
	

	
	Assert.assertTrue(Status_ActionItemTable.toLowerCase().contains(statusToChange.toLowerCase()), "After " + statusToChange + " the '" + ActionItemName
			+ "' action item , status is '" + Status_ActionItemTable + "' . ");
	

	fnpMymsg(Status_ActionItemTable + "  is the current status of  Action item  ---" + ActionItemName);
	// ***************Complete the pending Actionitem***********
	
	
}


	
	
	public void fnpVerifyFRSReportOnViewVistPage(String taskOpenIconXpath,String visitNoInTasksAuditXpath) throws Throwable{
		
		
		String oldBrowser;
		 //if((classNameText.equalsIgnoreCase("ISO9001_SingleWo_InProcess"))){
		if((classNameText.equalsIgnoreCase(ISO9001_SingleTestCaseName))){	 
				
				driver.close();
				oldBrowser=browserName;
				browserName="firefox";
				fnpLaunchBrowserAndLogin();	
				browserName=oldBrowser;

				fnpSearchWorkOrderOnly(workOrderNo);
				
				if (fnpCheckElementDisplayedImmediately("EditWOBtn")) {
					fnpClick_OR("EditWOBtn");
					fnpMymsg("@  ---Here EditWOBtn is visible , so going to click it");

				}else{
					fnpMymsg("@  ---Here EditWOBtn is NOT visible , so ignore  it");
				}

				fnpClick_OR("ISRTaskTab");
		
				if ( (fnpCheckElementDisplayedImmediately_NOR(taskOpenIconXpath) & (!fnpCheckElementDisplayedImmediately_NOR(visitNoInTasksAuditXpath)) )  ) {				
					fnpClick_NOR(taskOpenIconXpath);
				}
				
				fnpGetORObjectX___NOR(visitNoInTasksAuditXpath).click();
				fnpClick_OR("EditBtnOnViewVisitPage");
				
				fnpGetORObjectX("DocumentsTabLink_viewVistPage").click();

				
				fnpLoading_wait();
				if (fnpCheckElementDisplayedImmediately("CreateFRSReportBtn_DocumentsTab")) {
					fnpClick_OR("CreateFRSReportBtn_DocumentsTab");
				}
				
				fnpWaitForVisible("FRSOpenBtn_DocumentsTab");					
				fnpCheckFileIsDownloadedOrNotIniPulse(OR.getProperty("FRSOpenBtn_DocumentsTab")) ;
				
		 }
		
		
	}
	
	
	
	
	public void fnpVerifyFRSReportOnViewVistPage_VerifyAtEnd_inChrome(String taskOpenIconXpath,String visitNoInTasksAuditXpath) throws Throwable{
		

		// if((classNameText.equalsIgnoreCase("ISO9001_SingleWo_InProcess"))){
		if((classNameText.equalsIgnoreCase(ISO9001_SingleTestCaseName))){	 
			 
			 
				

				fnpClick_OR("ISRTaskTab");
		
				if ( (fnpCheckElementDisplayedImmediately_NOR(taskOpenIconXpath) & (!fnpCheckElementDisplayedImmediately_NOR(visitNoInTasksAuditXpath)) )  ) {				
					fnpClick_NOR(taskOpenIconXpath);
				}
				
				fnpGetORObjectX___NOR(visitNoInTasksAuditXpath).click();

				
				fnpGetORObjectX("DocumentsTabLink_viewVistPage").click();
				

				
				fnpLoading_wait();
				if (fnpCheckElementDisplayedImmediately("CreateFRSReportBtn_DocumentsTab")) {
					fnpClick_OR("CreateFRSReportBtn_DocumentsTab");
					fnpLoading_wait();
					fnpLoading_wait();
					if (!(fnpCheckElementDisplayedImmediately("FRSOpenBtn_DocumentsTab"))) {
						fnpMymsg("Here after clicking CreateFRSReportBtn_DocumentsTab, FRS Open Report button is not visible , " +
								"so going to refresh the browser, may be come after refreshing browser");
						driver.navigate().refresh();
					}
					
				}
				
				fnpWaitForVisible("FRSOpenBtn_DocumentsTab");					
				//fnpCheckFileIsDownloadedOrNotIniPulse(OR.getProperty("FRSOpenBtn_DocumentsTab")) ;
				//fnpVerify_File_Download_Successfully(OR.getProperty("FRSOpenBtn_DocumentsTab"));
				fnpCheckFileIsDownloadedOrNotIniPulse(OR.getProperty("FRSOpenBtn_DocumentsTab"));
				
				
				
		 }
		
		
	}
	
	
	
	

	
	public static void fnpVerifyFRSReportOnViewVistPage_inChrome() throws Throwable{
		
		
		String oldBrowser;
		//classNameText="ISO9001_SingleWo_InProcess";
		 //if((classNameText.equalsIgnoreCase("ISO9001_SingleWo_InProcess"))){
		if((classNameText.equalsIgnoreCase(ISO9001_SingleTestCaseName))){	

				fnpGetORObjectX("DocumentsTabLink_viewVistPage").click();

				
				fnpLoading_wait();
				if (fnpCheckElementDisplayedImmediately("CreateFRSReportBtn_DocumentsTab")) {
					fnpClick_OR("CreateFRSReportBtn_DocumentsTab");
					fnpLoading_wait();
					if (!(fnpCheckElementDisplayedImmediately("FRSOpenBtn_DocumentsTab"))) {
						fnpMymsg("Here after clicking CreateFRSReportBtn_DocumentsTab, FRS Open Report button is not visible , " +
								"so going to refresh the browser, may be come after refreshing browser");
						driver.navigate().refresh();
					}
					
				}
				
				fnpWaitForVisible("FRSOpenBtn_DocumentsTab");					

				fnpCheckFileIsDownloadedOrNotIniPulse(OR.getProperty("FRSOpenBtn_DocumentsTab"));
				
		 }
		
		
	}
	
	
	public static void fnpCommonCodeOfFinancialTabOfSingleWO_CorporateWO() throws Throwable{

		fnpCommonCodeOfFinancialTabTillInReviewForISRSCFS();
		
		 //if((classNameText.equalsIgnoreCase("ISO9001_SingleWo_InProcess"))){
		if((classNameText.equalsIgnoreCase(ISO9001_SingleTestCaseName))){		
			//Regarding query in ATA-67 on 14-12-2017 --Mail subject is -->Regarding query in ATA-67
			fnpRunQueryAfterMovingWoToInReviewForBillCodes( (String) hashXlData.get("Standard"),workOrderNo);
		}
		 
		 

		// if((classNameText.equalsIgnoreCase("ISO9001_SingleWo_InProcess")))
		// if((classNameText.equalsIgnoreCase("ISO9001_SingleWo_InProcess")) | (classNameText.equalsIgnoreCase("ISO9001_Corporate_InProcess")))
		if((classNameText.equalsIgnoreCase(ISO9001_SingleTestCaseName)) || (classNameText.equalsIgnoreCase(ISO9001_CorporateTestCaseName)))  	
			 
		 {

			String FirstValueInTable = fnpFetchFromTable("FinopsActionItemTable", 1, 1);
			Assert.assertFalse(FirstValueInTable.contains("No records found"), "FINOPS Action item should be generated under the Finops Action Item Table.");
			fnpMymsg(" FINOPS Action item has been  generated under the Finops Action Item Table.");

			int actionItemNameColIndex = fnpFindColumnIndex("FinopsActionItemTable_HeaderRow", "Action Item Name");
			String actionItemName_FirstValue = fnpFetchFromTable("FinopsActionItemTable", 1, actionItemNameColIndex);

			String[] expectedAINameArray = ((String) hashXlData.get("Finops_Action_item_name")).split(",");

			int totalRowGenerated = fnpCountRowsInTable("FinopsActionItemTable");
			Assert.assertTrue(totalRowGenerated == 1, "Total Action Items generated must be 1 but here they are only '" + totalRowGenerated + "'  .");

			String actionItemName_Value = "";
			for (int p = 0; p < expectedAINameArray.length; p++) {
				for (int i1 = 1; i1 <= totalRowGenerated; i1++) {
					actionItemName_Value = fnpFetchFromTable("FinopsActionItemTable", i1, actionItemNameColIndex).trim();
					if ((expectedAINameArray[p].trim()).equalsIgnoreCase(actionItemName_Value)) {
						fnpMymsg(" Action Item '" + expectedAINameArray[p] + "' is present at row no --" + (i1));
						continue;
					}

					fnpMymsg("This action item '" + expectedAINameArray[p] + "' is not present in any row  .");
					throw new Exception("This action item '" + expectedAINameArray[p] + "' is not present in any row  .");
				}
			}
			int StatusColIndex = fnpFindColumnIndex("FinopsActionItemTable_HeaderRow", "Status");
			String Status_FirstValue = fnpFetchFromTable("FinopsActionItemTable", 1, StatusColIndex).trim();

			String expectedStatus = "AI_CREATED";

			Assert.assertTrue(Status_FirstValue.equalsIgnoreCase(expectedStatus), "Finops action item Status is not [" + expectedStatus + "] .");

			fnpMymsg("Finops Action Items has been generated with AI_CREATED status. ");
			
		 }
	}
	
	
	public static void fnpCommonCodeOfFinancialTabTillInReviewForISRSCFS() throws Throwable{
		Thread.sleep(5000);
		fnpClick_OR("ISRFinancialTabLink");
		
		//since 5.1 release , it will be present in all			
/*			if ((classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.WOSQFSingleWOTillInProcessTestCaseName))) {
			fnpPFList("UpchargePFList", "UpchargePFListOptions", (String) hashXlData.get("Upcharge"));
		}
*/
		
		
		
/*		
		if ((currentSuiteName.equalsIgnoreCase("SCFS_suite"))) {
			fnpPFList("Surveillance_Cycle_PFList", "Surveillance_Cycle_PFListOptions", (String) hashXlData.get("SurveillanceCycle"));
		}
		*/
		
		if (!((classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.Cert_StandardsTestCaseName))
				|
				(classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.Non_Cert_StandardsTestCaseName))
				)) {
			fnpPFList("Surveillance_Cycle_PFList", "Surveillance_Cycle_PFListOptions", (String) hashXlData.get("SurveillanceCycle"));
		}
		
		
		
		/**********LAST MINUTE CHANGED ==>New mandatory field introduce on 06 June 2019   IPM-10430 **********************************************************/
/*		
		String BillToLocation="42172-B1 ORC_DEFAULT_BILL : Y";
		//fnpPFListContains_ifMatchingMultipleThenSelectFirstOne("WOBillToLocationPFList", "42172") ;
		fnpPFList("WOBillToLocationPFList", "WOBillToLocationPFListOptions", BillToLocation);
		String insertedBillToLocation = fnpGetText_OR("WOBillToLocationPFList");
		Assert.assertEquals(insertedBillToLocation, BillToLocation, "Bill To Location value has not been inserted properly ");
		*/
		

		
		
		
		
		
		fnpLoading_wait();
		
		/********************************************************************************************************************/
		
		
		
		/**********New mandatory field introduce on 25 July 2016**********************************************************/
		fnpPFList("ISRBillToOfficePFList", "ISRBillToOfficePFListOptions", (String) hashXlData.get("BillToOffice"));
		fnpLoading_wait();
		String insertedBillToOffice = fnpGetText_OR("ISRBillToOfficePFList");
		//Assert.assertEquals(insertedBillToOffice, (String) hashXlData.get("BillToOffice"), "Bill To Office value has not been inserted properly ");
		Assert.assertTrue(insertedBillToOffice.contains((String) hashXlData.get("BillToOffice")), "Bill To Office value has not been inserted properly ");
		
		
		if (!(currentSuiteName.equalsIgnoreCase("SCFS_suite"))) {
			fnpPFListContains_ifMatchingMultipleThenSelectFirstOne("WOBillToLocationPFList",  (String) hashXlData.get("BillToOffice")) ;
			//fnpPFListByLiNo("WOBillToLocationPFList","WOBillToLocationPFListOptions", 1) ;
		}
		
		/*************************Commenting agian on 17-05-2017 as from today onwards it will be coming in all wo type  IPM-5707********************/
		
/*		//not present in this release , but it will come again in next sprint in june 2017	, for timebeing it started coming only in SQF
		//fnpPFList("UpchargePFList", "UpchargePFListOptions", (String) hashXlData.get("Upcharge"));
		if ((classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.WOSQFSingleWOTillInProcessTestCaseName))) {
		fnpPFList("UpchargePFList", "UpchargePFListOptions", (String) hashXlData.get("Upcharge"));
		}
		*/
		/********************************************************************************************************************************************/
		fnpLoading_wait();
		fnpPFList("UpchargePFList", "UpchargePFListOptions", (String) hashXlData.get("Upcharge"));
		fnpLoading_wait();
		
		
		if ((classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.Cert_StandardsTestCaseName))){
			if (((String) hashXlData.get("Standard")).equalsIgnoreCase("FD_BRC")) {
				//fnpPFList("InvoiceCurrencyPFList", "InvoiceCurrencyPFListOptions","USD - US Dollar");
				fnpPFList_usingFilterBox("InvoiceCurrencyPFList_expandIcon","InvoiceCurrencyPFList_filterBox_xpath", "InvoiceCurrencyPFList", "InvoiceCurrencyPFListOptions",(String) hashXlData.get("Invoice_Currency"));
			
			}
		}
		

		
		
		fnpClick_OR("ISRSFTabSaveBtn");
		
		if(currentSuiteName.equalsIgnoreCase("SCFS_suite")) {
			TestSuiteBase_Sustain_Suite.fngFileUploadInSus();
		}else {
			fnpFileUploadInISR();
		}
	
		fnpMoveToInReviewByClickingButtonAndVerifyInReview();
	}
	
	
	public static void fnpFileUploadInISR() throws Throwable{
		
		String fileNames = (String) hashXlData.get("FinancialTab_docUpload_FileName");
		String[] fileCount = fileNames.split(",");
		int fileCountSize = fileCount.length;

		if (!(fileCountSize > 0)) {
			throw new Exception("Upload file names should be given in excel.");
		}

		String fileTypes = (String) hashXlData.get("FinancialTab_docUpload_FileType");
		String[] fileTypeCount = fileTypes.split(",");
		int fileTypesCountSize = fileCount.length;

		if (!(fileTypesCountSize == fileCountSize)) {
			throw new Exception("Upload file type should be equal to the no. of files in excel.");
		}

		String fileAccessNames = (String) hashXlData.get("FinancialTab_docUpload_FileAccess");
		String[] fileAccessCount = fileAccessNames.split(",");
		int fileAccessCountSize = fileAccessCount.length;

		if (!(fileAccessCountSize == fileCountSize)) {
			throw new Exception("Upload file access should be equal to the no. of files  in excel.");
		}

		String fileName = "";

		String fname;

		for (int f = 0; f < fileCountSize; f++) {

			fnpClick_OR("ISRFinaceTabAddBtn");
			fnpWaitTillVisiblityOfElementNClickable_OR("ISRFinaceTabUploadSave&CloseBtn");
			fnpWaitForVisible("ISRFinaceTabUploadSave&CloseBtn");

			fileName = System.getProperty("user.dir") + "\\docs\\" + fileCount[f];
			driver.findElement(By.xpath(OR.getProperty("ISRFinaceTabUploadBrowseBtn"))).sendKeys(fileName);
			Thread.sleep(1000);
			while (fnpCheckElementPresenceImmediately("BrowseCancelUploadBtn")) {
				Thread.sleep(1000);
			}

			fnpWaitForVisible("ISRFinaceTabUploadDoc_TypePFList");
			fnpPFList("ISRFinaceTabUploadDoc_TypePFList", "ISRFinaceTabUploadDoc_TypePFListOptions", fileTypeCount[f]);
			fnpPFList("ISRFinaceTabUploadDoc_AccessPFList", "ISRFinaceTabUploadDoc_AccessPFListOptions", fileAccessCount[f]);
			Thread.sleep(5000);
			fnpClickInDialog_OR("ISRFinaceTabUploadSave&CloseBtn");

			if(ISR_IntegratedWOComing){
				//new changes -- IPM-15605
				fnpWaitTillVisiblityOfElementNClickable_OR("ISRFinaceTabUploadConfirmationNOBtn");
				fnpWaitForVisible("ISRFinaceTabUploadConfirmationNOBtn");
				fnpClick_OR("ISRFinaceTabUploadConfirmationNOBtn");
			}
			
			fnpCheckError("Error is thrown by application while adding documents in Financial Tab");

		}
	
	
	}
	
	
	public static void fnpMoveToInReviewByClickingButtonAndVerifyInReview() throws Throwable{
		
		
		/*			
		if ((classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.Non_Cert_StandardsTestCaseName))
				|(classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.Cert_StandardsTestCaseName))
				) {
			
		*/
		//below is added on 07-08-2018
		if (classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.Cert_StandardsTestCaseName)) {
			
			fnpClick_OR("ISRInfoTab_EditWO");
			if (fnpCheckElementDisplayedImmediately("EditWOBtn")) {				
				fnpClick_OR("EditWOBtn");
			}
			
			String expectedAuditAllocator;
		
			
/*			if (loginAsFullName.equalsIgnoreCase("") | (loginAsFullName==null) ) {
				expectedAuditAllocator=(String) hashXlData.get("AccountManager").trim();
			} else {
				expectedAuditAllocator=loginAsFullName;
			}
			
			*/
			
			if (((String) hashXlData.get("Standard").trim()).equalsIgnoreCase("FDSQF200")
					
					| ((String) hashXlData.get("Standard").trim()).equalsIgnoreCase("FD_BRC")
										
					) {
				//expectedAuditAllocator=(String) hashXlData.get("Audit_Allocator").trim();
				if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
					expectedAuditAllocator=(String) hashXlData.get("Audit_Allocator_Code").trim();
				}else{
					expectedAuditAllocator=(String) hashXlData.get("Audit_Allocator").trim();
				}
				
				fnpClick_OR("AuditAllocatorLkpBtn");
				//fnpSearchNSelectFirstRadioBtn(2, expectedAuditAllocator, 1);
				if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
					fnpSearchNSelectFirstRadioBtn(1, expectedAuditAllocator, 1);
				}else{
					fnpSearchNSelectFirstRadioBtn(2, expectedAuditAllocator, 1);
				}
			}
			
			
			
			
			
			
			fnpClick_OR("GFSI_InfoTabBDMLkpBtn_id");
			if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
				fnpSearchNSelectFirstRadioBtn(1, (String) hashXlData.get("BDM_Code"), 1);
			}else{
				fnpSearchNSelectFirstRadioBtn(2, (String) hashXlData.get("BDM"), 1);
			}			
			String EnteredBDM = fnpGetORObjectX("GFSI_InfoTabBDMTxtBox_id").getAttribute("value");
			EnteredBDM = fnpWaitTillTextBoxDontHaveValue("GFSI_InfoTabBDMTxtBox_id", "value");
			if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
				Assert.assertTrue(EnteredBDM.contains((String) hashXlData.get("BDM_Code")), "BDM is not selected properly from lookup  because actual value is ---'"+EnteredBDM+"' and expected is --'"+(String) hashXlData.get("BDM_Code")+"'.");
			}else{
				Assert.assertTrue(EnteredBDM.contains((String) hashXlData.get("BDM")), "BDM is not selected properly from lookup  because actual value is ---'"+EnteredBDM+"' and expected is --'"+(String) hashXlData.get("BDM")+"'.");
			}
			fnpMymsg(" BDM is properly selected from client lookup");
			
			
			if ((currentSuiteName.equalsIgnoreCase("SCFS_suite"))) {
				fnpSelectTechnicalSpecialist();
			}
     	
			
		
			
			

			fnpClick_OR("ISRSFTabSaveBtn");
			fnpClick_OR("ISRFinancialTabLink");
		}
		
		
/*		
		if (fnpCheckElementDisplayed("ISR_Financials_Tab_CreateAuditProgram_button", 5)) {
			fnpClick_OR("ISR_Financials_Tab_CreateAuditProgram_button");
		}
		*/
		
		String textMessage = "";
		fnpClick_OR("ISRMoveToInReviewBtn");

		fnpWaitTillClickable("NewlyCrWOTopBannerInfo");
		fnpGetORObjectX("NewlyCrWOTopBannerInfo").click();


		fnpWaitForVisible("TopBannerWOStatus");
		String changedWOStatus = fnpGetORObjectX("TopBannerWOStatus").getText();
		Assert.assertTrue(changedWOStatus.equalsIgnoreCase("INREVIEW"), " WO status is not changed from DRAFT to INREVIEW.");
		fnpMymsg("Now  WO status has been changed from 'DRAFT' to  INREVIEW.");
	}
	
	





public static void callRemoveAlreadyAddedIndustryCodeUsingScript() throws Throwable{
	
	//String deleteXpathForIndustryCode=".//*[@id='mainForm:tabView:dtcusindprd:0:delmptask']";
	String deleteXpathForIndustryCode=".//a[contains(@id,':dtcusindprd:')][contains(@id,':delmptask')]";
	//String deleteXpathForConfirmationBtn=".//*[@id='mainForm:tabView:deltaskyesbtn']";
	String deleteXpathForConfirmationBtn=".//*[@id='mainForm:tabView:facStdInfoAccordPnl:deltaskyesbtn']";
	
	fnpWaitForVisible("IndustryCodeProductTable");

	if (fnpCheckElementDisplayedImmediately_NOR(deleteXpathForIndustryCode)) {
		List<WebElement> alreadyAddedIndustryCode = driver.findElements(By.xpath(deleteXpathForIndustryCode));
		int sizeOfAlreadyAddedIndustryCode=alreadyAddedIndustryCode.size();
		fnpMymsg("  --Already added Industry codes are --"+sizeOfAlreadyAddedIndustryCode);
		int iwhileCounterForDeletingIndustryCode=0;
		while(sizeOfAlreadyAddedIndustryCode>0){
			iwhileCounterForDeletingIndustryCode++;
			if (iwhileCounterForDeletingIndustryCode>10) {
				break;
			}
			//fnpMymsg("  --Going to delete this Industry code --"+alreadyAddedIndustryCode.get(0));
			fnpMymsg("  --Going to delete this Industry code --"+fnpFetchFromTable_NOR(OR.getProperty("IndustryCodeProductTable"),1,2));
			alreadyAddedIndustryCode.get(0).click();
			fnpClick_NOR(deleteXpathForConfirmationBtn);
			fnpLoading_wait();
			
			driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
			 alreadyAddedIndustryCode = driver.findElements(By.xpath(deleteXpathForIndustryCode));
			 sizeOfAlreadyAddedIndustryCode=alreadyAddedIndustryCode.size();
			 driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);
		}
	}

	
	
}

	public static boolean callVerifySingleWOPresentOnThisStandardFacilityOtherwiseCreate(String facility, String standard) throws Throwable{
		
		boolean needToCreateSingleWO=false;
		
		fnpLaunchBrowserAndLogin();
		
		fnpCommonClickTopMainMenu("Menu", "FirstRefrenceLinkJustBelowToMenu", "SearchWorkOrderLink", "WorkOrderNoField");

		// Thread.sleep(2000);
		fnpMymsg("Just before waiting for clickable workorderfield");
		fnpWaitTillVisiblityOfElementNClickable_OR("WorkOrderNoField");
		fnpWaitTillVisiblityOfElementNClickable_OR("MainSearchButton");
		
		

		
		
		
		fnpType("OR", "SearchCorpFacilityTxtBx", facility);		
		fnpClick_OR("StandardLkpBtnInSearchCriteria");
		fnpSearchNSelectFirstRadioBtn(1, standard, 1);
		
		String EnteredClient = fnpWaitTillTextBoxDontHaveValue("SearchStandardTxtBx_id", "value");
		Assert.assertTrue(EnteredClient.contains(standard), "Standard is not selected properly from lookup");
		fnpMymsg(" Standard is properly selected from client lookup");
		
		
		
		
		fnpMultipleSelectDropDown2("WorkOrderTypeMultipleSelectDropDown", "Single");
		
		
		
		
		
		
		
		fnpWaitForVisible("MainSearchButton");
		fnpMymsg("Just before inserting value in workorderfield");
		fnpMymsg("Just after inserting value in workorderfield");
		fnpWaitTillVisiblityOfElementNClickable_OR("MainSearchButton");
		fnpClick_OR("MainSearchButton");
		// fnpLoading_wait();


		// fnpMymsg("just before picking value from table");
		String s = fnpFetchFromStSearchTable(1, 1);
		int j = 0;
		// fnpLoading_wait();
		while (s.contains("No records found") && j < 15) {
			j++;
			Thread.sleep(1000);
			s = fnpFetchFromStSearchTable(1, 1);
		}
		
		//when it refreshed from prod or stage then initially no records present
		boolean dropWOFlag=false;
		boolean createNewSingleWOTillInProcessFlag=false;
		String singleWOTillInProcess;
		String workOrderNoToDrop="";
		int countSingleWOTillInProcess=0;
		String textMessage;
		if (!(s.contains("No records found"))) {


			int totalRows=fnpCountRowsInTable("StandardSearchTable");
			String temp;
			int statusColNo=fnpFindColumnIndex("StandardSearchTableHeader", "WO Status");
			int workOrderColNo=fnpFindColumnIndex("StandardSearchTableHeader", "WO #");
			for (int i = 1; i < totalRows+1; i++) {
				temp=fnpFetchFromStSearchTable(i, statusColNo);
				if ((temp.trim().equalsIgnoreCase("DROP"))) {
					//nothing to do
				} else {
					if (temp.trim().equalsIgnoreCase("INPROCESS")) {
						countSingleWOTillInProcess++;
					}else  {
						
						workOrderNoToDrop=fnpFetchFromStSearchTable(i, workOrderColNo);
						fnpMymsg("We need to drop this work order '"+workOrderColNo+"' as its status is ---"+temp);
					}
					
				}

				}
			
			if (countSingleWOTillInProcess>1) {
				throw new Exception("There should be only 1 Single Work Order in INPROCESS status for this " +
						"facility '"+(String) hashXlData.get("Facility_No_for_singleWO")+"' but here they are --"+countSingleWOTillInProcess);
				
			} else {
					if (countSingleWOTillInProcess==0) {
						//here  no single work order present till INPROCESS status
						if (workOrderNoToDrop.equalsIgnoreCase("")) {
							//also no need to drop anyone
							//needToCreateSingleWO=true;
							createNewSingleWOTillInProcessFlag=true;
						}else{
							dropWOFlag=true;
							// need to drop this work order no.
							// Step 1:call either method or query to drop this work order
							// Step 2: create a single work order till inprocess
						}
					} else {
						// nothing to do as single work order present till INPROCESS status is PRESENT
					}
			}
			
		}else{
			// we need to create wo till inprocess
			createNewSingleWOTillInProcessFlag=true;
		}

		
		
		
		if (dropWOFlag) {
			textMessage="Going to DROP this work order '"+workOrderNoToDrop +"' .";
			fnpMymsg(textMessage);
			fnpDisplayingMessageInFrame(textMessage, 10);
			//fnpDropWOISRandDeleteSomeDataFromDB(facility, standard);
			fnpDropWOISRandDeleteSomeDataFromDB_speciallyForCorporate(facility, standard);
			fnpDropWOISRandDeleteSomeDataFromDB(facility, standard);

			
			createNewSingleWOTillInProcessFlag=true;
		}
		
		
		if (createNewSingleWOTillInProcessFlag) {
			textMessage="Going to create a new single wo till InProcess";
			fnpMymsg(textMessage);
			fnpDisplayingMessageInFrame(textMessage, 10);
			needToCreateSingleWO=true;

		}
		
		return needToCreateSingleWO;
	}

	
	
	
	

	public static String fnpCreateANewSingleWOTillInProcess() throws Throwable{

		String newlyCreatedWorkOrderNo="";
		
		String workOrderName = "Single";


		ISR_BS01.CreateWOFlow();
		

		String workOrderNo_text = fnpGetORObjectX("NewlyCreatedWorkOrderNo").getText();
		newlyCreatedWorkOrderNo = ((workOrderNo_text.split(" "))[0]).trim();
		fnpMymsg(" Newly Work Order No is:" + newlyCreatedWorkOrderNo);
		
		ISR_BS01.Standard_Facility_Tab();
		
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
		
		return newlyCreatedWorkOrderNo;
	}
	
	
	
	public static String fnpCreateANewSingleWOTillStatus(String status) throws Throwable{

		String newlyCreatedWorkOrderNo="";
		
		String workOrderName = "Single";


		//ISR_BS01.CreateWOFlow();
		fnpCreateISRWO_excludingDBDropQuery();
		

		String workOrderNo_text = fnpGetORObjectX("NewlyCreatedWorkOrderNo").getText();
		newlyCreatedWorkOrderNo = ((workOrderNo_text.split(" "))[0]).trim();
		fnpMymsg(" Newly Work Order No is:" + newlyCreatedWorkOrderNo);
		
		if (!(status.equalsIgnoreCase("DRAFT"))) {
			ISR_BS01.Standard_Facility_Tab();
			
			fnpCommonCodeOfFinancialTabOfSingleWO_CorporateWO();
			
			if (!(status.equalsIgnoreCase("INREVIEW")))     {
				String Finops_ReassignedTo;
				
				
/*				if (loginAsFullName.equalsIgnoreCase("") | (loginAsFullName==null) ) {
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
			}
			
			
/*			if (!(status.equalsIgnoreCase("INPROCESS")))     {
						// to proceed this work order to complete status
			}
			*/
			
		}

		

		
		return newlyCreatedWorkOrderNo;
	}
	
	
	
	
	


	public static boolean callVerifySingleWOPresentOnThisStandardFacilityOtherwiseCreate2(String facility, String standard,String []statusToCheck) throws Throwable{
		
		boolean needToCreateSingleWO=false;
		
		fnpLaunchBrowserAndLogin();
		
		fnpCommonClickTopMainMenu("Menu", "FirstRefrenceLinkJustBelowToMenu", "SearchWorkOrderLink", "WorkOrderNoField");

		// Thread.sleep(2000);
		fnpMymsg("Just before waiting for clickable workorderfield");
		fnpWaitTillVisiblityOfElementNClickable_OR("WorkOrderNoField");
		fnpWaitTillVisiblityOfElementNClickable_OR("MainSearchButton");
		
		
	//	fnpMultipleSelectDropDown2("WorkOrderTypeMultipleSelectDropDown", "Single");
		
		fnpType("OR", "SearchCorpFacilityTxtBx", facility);		
		fnpClick_OR("StandardLkpBtnInSearchCriteria");
		fnpSearchNSelectFirstRadioBtn(1, standard, 1);
		
		String EnteredClient = fnpWaitTillTextBoxDontHaveValue("SearchStandardTxtBx_id", "value");
		Assert.assertTrue(EnteredClient.contains(standard), "Standard is not selected properly from lookup");
		fnpMymsg(" Standard is properly selected from client lookup");

		//fnpMultipleSelectDropDown2("WorkOrderTypeMultipleSelectDropDown", "Single");

		
		fnpWaitForVisible("MainSearchButton");
		fnpMymsg("Just before inserting value in workorderfield");
		fnpMymsg("Just after inserting value in workorderfield");
		fnpWaitTillVisiblityOfElementNClickable_OR("MainSearchButton");
		
		fnpClick_OR("MainSearchButton");
	//	fnpLoading_wait();
		

		// fnpMymsg("just before picking value from table");
		String s = fnpFetchFromStSearchTable(1, 1);
		int j = 0;
		// fnpLoading_wait();
		while (s.contains("No records found") && j < 15) {
			j++;
			Thread.sleep(1000);
			s = fnpFetchFromStSearchTable(1, 1);
		}
		
		
		
		/*****************Tempraray for Time being to click Search button again, later you can remove these below lines ***********/
		s = fnpFetchFromStSearchTable(1, 1);
		fnpMymsg("@  ---After clicking once Search button  for search single wo, data present in 1st row 1st column is ---"+s);
		if (s.contains("No records found")) {
			fnpMymsg("@  ---Clicking ONCE AGAIN this SEARCH BUTTON ::::::: IMPORTANT::::::This is a bug as in single click no records are found coming.");
			fnpClick_OR("MainSearchButton");
			s = fnpFetchFromStSearchTable(1, 1);
			while (s.contains("No records found") && j < 15) {
				j++;
				Thread.sleep(1000);
				s = fnpFetchFromStSearchTable(1, 1);
			}
		}		
		/**************************************************************************/
		
		
		
		//when it refreshed from prod or stage then initially no records present
		String [] statuses=statusToCheck;
		boolean dropWOFlag=false;
		boolean createNewSingleWOFlag=false;
		String singleWO;
		String workOrderNoToDrop="";
		int countSingleWO=0;
		String textMessage;
		
		fnpMymsg("After SearchCorpFacilityTxtBx --"+facility+"   ,SearchStandardTxtBx_id --"+EnteredClient+"  ," +
				"WorkOrderTypeMultipleSelectDropDown ---"+" Single  and clicked Search button then value in 1 row 1 column is ---"+s);
		if (!(s.contains("No records found"))) {


			int totalRows=fnpCountRowsInTable("StandardSearchTable");
			String temp;
			String woType;
			int statusColNo=fnpFindColumnIndex("StandardSearchTableHeader", "WO Status");
			int workOrderColNo=fnpFindColumnIndex("StandardSearchTableHeader", "WO #");
			
			for (int i = 1; i < totalRows+1; i++) {
				temp=fnpFetchFromStSearchTable(i, statusColNo);
				if ((temp.trim().equalsIgnoreCase("DROP"))) {
					//nothing to do
				} else {
					boolean tempFoundFlag=false;
					for (int k = 0; k < statuses.length; k++) {
						if  (temp.trim().equalsIgnoreCase(statuses[k])){
							countSingleWO++;
							tempFoundFlag=true;
						}
					}
					if (!tempFoundFlag) {
						workOrderNoToDrop=fnpFetchFromStSearchTable(i, workOrderColNo);
						fnpMymsg("We need to drop this work order '"+workOrderColNo+"' as its status is ---"+temp);
					}
					
					
				}

				}
			
			if (countSingleWO>1) {
				throw new Exception("There should be only 1 Single Work Order not in DROP status for this " +
						"facility '"+(String) hashXlData.get("Facility_No_for_singleWO")+"' but here they are --"+countSingleWO);
				
			} else {
					if (countSingleWO==0) {
						//here  no single work order present till INPROCESS status
						if (workOrderNoToDrop.equalsIgnoreCase("")) {
							//also no need to drop anyone
							//needToCreateSingleWO=true;
							createNewSingleWOFlag=true;
						}else{
							dropWOFlag=true;
							// need to drop this work order no.
							// Step 1:call either method or query to drop this work order
							// Step 2: create a single work order till inprocess
						}
					} else {
						// nothing to do as single work order present till INPROCESS status is PRESENT
					}
			}
			
		}else{
			// we need to create wo till inprocess
			fnpMymsg("After SearchCorpFacilityTxtBx --"+facility+"   ,SearchStandardTxtBx_id --"+EnteredClient+"  ," +
					"WorkOrderTypeMultipleSelectDropDown ---"+" Single  and clicked Search button then value in 1 row 1 column is ---"+s);
			fnpMymsg(" And total no. of rows after searching a wo using above cretaria are ---"+fnpCountRowsInTable("StandardSearchTable"));
			fnpMymsg("");
			fnpMymsg("");
			fnpMymsg("");
			createNewSingleWOFlag=true;
		}

		
		
		
		if (dropWOFlag) {
			textMessage="Going to DROP this work order '"+workOrderNoToDrop +"' .";
			fnpMymsg(textMessage);
			fnpDisplayingMessageInFrame(textMessage, 10);
			//fnpDropWOISRandDeleteSomeDataFromDB(facility, standard);
			fnpDropWOISRandDeleteSomeDataFromDB_speciallyForCorporate(facility, standard);
			fnpDropWOISRandDeleteSomeDataFromDB(facility, standard);
			
			createNewSingleWOFlag=true;
		}
		
		
		if (createNewSingleWOFlag) {
			textMessage="Going to create a new single wo ";
			fnpMymsg(textMessage);
			fnpDisplayingMessageInFrame(textMessage, 10);
			needToCreateSingleWO=true;

		}
		
		return needToCreateSingleWO;
	}

	

//	In this we have removed selection of 'Single' from multiple Selection drop down and also later get its wo type and put that wo type also in condition
	public static boolean callVerifySingleWOPresentOnThisStandardFacilityOtherwiseCreate(String facility, String standard,String []statusToCheck) throws Throwable{
		
		boolean needToCreateSingleWO=false;
		
		fnpLaunchBrowserAndLogin();
		
		fnpCommonClickTopMainMenu("Menu", "FirstRefrenceLinkJustBelowToMenu", "SearchWorkOrderLink", "WorkOrderNoField");

		// Thread.sleep(2000);
		fnpMymsg("Just before waiting for clickable workorderfield");
		fnpWaitTillVisiblityOfElementNClickable_OR("WorkOrderNoField");
		fnpWaitTillVisiblityOfElementNClickable_OR("MainSearchButton");
		
		
	//	fnpMultipleSelectDropDown2("WorkOrderTypeMultipleSelectDropDown", "Single");
		
		fnpType("OR", "SearchCorpFacilityTxtBx", facility);		
		fnpClick_OR("StandardLkpBtnInSearchCriteria");
		fnpSearchNSelectFirstRadioBtn(1, standard, 1);
		
		String EnteredClient = fnpWaitTillTextBoxDontHaveValue("SearchStandardTxtBx_id", "value");
		Assert.assertTrue(EnteredClient.contains(standard), "Standard is not selected properly from lookup");
		fnpMymsg(" Standard is properly selected from client lookup");

		//fnpMultipleSelectDropDown2("WorkOrderTypeMultipleSelectDropDown", "Single");

		
		fnpWaitForVisible("MainSearchButton");
		fnpMymsg("Just before inserting value in workorderfield");
		fnpMymsg("Just after inserting value in workorderfield");
		fnpWaitTillVisiblityOfElementNClickable_OR("MainSearchButton");
		
		fnpClick_OR("MainSearchButton");
	//	fnpLoading_wait();
		

		// fnpMymsg("just before picking value from table");
		String s = fnpFetchFromStSearchTable(1, 1);
		int j = 0;
		// fnpLoading_wait();
		while (s.contains("No records found") && j < 15) {
			j++;
			Thread.sleep(1000);
			s = fnpFetchFromStSearchTable(1, 1);
		}
		
		
		
		/*****************Tempraray for Time being to click Search button again, later you can remove these below lines ***********/
		s = fnpFetchFromStSearchTable(1, 1);
		fnpMymsg("@  ---After clicking once Search button  for search single wo, data present in 1st row 1st column is ---"+s);
		if (s.contains("No records found")) {
			fnpMymsg("@  ---Clicking ONCE AGAIN this SEARCH BUTTON ::::::: IMPORTANT::::::This is a bug as in single click no records are found coming.");
			fnpClick_OR("MainSearchButton");
			s = fnpFetchFromStSearchTable(1, 1);
			while (s.contains("No records found") && j < 15) {
				j++;
				Thread.sleep(1000);
				s = fnpFetchFromStSearchTable(1, 1);
			}
		}		
		/**************************************************************************/
		
		
		
		//when it refreshed from prod or stage then initially no records present
		String [] statuses=statusToCheck;
		boolean dropWOFlag=false;
		boolean createNewSingleWOFlag=false;
		String singleWO;
		String workOrderNoToDrop="";
		int countSingleWO=0;
		String textMessage;
		
		fnpMymsg("After SearchCorpFacilityTxtBx --"+facility+"   ,SearchStandardTxtBx_id --"+EnteredClient+"  ," +
				"WorkOrderTypeMultipleSelectDropDown ---"+" Single  and clicked Search button then value in 1 row 1 column is ---"+s);
		if (!(s.contains("No records found"))) {


			int totalRows=fnpCountRowsInTable("StandardSearchTable");
			String temp;
			String woType;
			int statusColNo=fnpFindColumnIndex("StandardSearchTableHeader", "WO Status");
			int workOrderColNo=fnpFindColumnIndex("StandardSearchTableHeader", "WO #");
			int woTypeColNo=fnpFindColumnIndex("StandardSearchTableHeader", "WO Type");
			
			String workOrderNo;
			for (int i = 1; i < totalRows+1; i++) {
				temp=fnpFetchFromStSearchTable(i, statusColNo);
				if ((temp.trim().equalsIgnoreCase("DROP"))) {
					//nothing to do
				} else {
					boolean tempFoundFlag=false;
					for (int k = 0; k < statuses.length; k++) {
						woType=fnpFetchFromStSearchTable(i, woTypeColNo);
						if ( (temp.trim().equalsIgnoreCase(statuses[k])) && (woType.trim().equalsIgnoreCase("SINGLE"))  ) {
							countSingleWO++;
							tempFoundFlag=true;
							workOrderNo=fnpFetchFromStSearchTable(i, workOrderColNo);
							fnpMymsg("We have found this work order '"+workOrderNo+"' which is of 'Single' type and whose status is '"+statuses[k]+"', So no need to create this Single type wo and we will use this wo in our script.");
							//break;
						}
					}
					if (!tempFoundFlag) {
						workOrderNoToDrop=fnpFetchFromStSearchTable(i, workOrderColNo);
						fnpMymsg("We need to drop this work order '"+workOrderNoToDrop+"' as its status is ---"+temp);
					}
					
					
				}
				

				}
			
			if (countSingleWO>1) {
				throw new Exception("There should be only 1 Single Work Order not in DROP status for this " +
						"facility '"+(String) hashXlData.get("Facility_No_for_singleWO")+"' but here they are --"+countSingleWO);
				
			} else {
					if (countSingleWO==0) {
						//here  no single work order present till INPROCESS status
						if (workOrderNoToDrop.equalsIgnoreCase("")) {
							//also no need to drop anyone
							//needToCreateSingleWO=true;
							createNewSingleWOFlag=true;
						}else{
							dropWOFlag=true;
							// need to drop this work order no.
							// Step 1:call either method or query to drop this work order
							// Step 2: create a single work order till inprocess
						}
					} else {
						// nothing to do as single work order present till INPROCESS status is PRESENT
					}
			}
			
		}else{
			// we need to create wo till inprocess
			fnpMymsg("After SearchCorpFacilityTxtBx --"+facility+"   ,SearchStandardTxtBx_id --"+EnteredClient+"  ," +
					"WorkOrderTypeMultipleSelectDropDown ---"+" Single  and clicked Search button then value in 1 row 1 column is ---"+s);
			fnpMymsg(" And total no. of rows after searching a wo using above cretaria are ---"+fnpCountRowsInTable("StandardSearchTable"));
			fnpMymsg("");
			fnpMymsg("");
			fnpMymsg("");
			createNewSingleWOFlag=true;
		}

		
		
		
		if (dropWOFlag) {
			textMessage="Going to DROP this work order '"+workOrderNoToDrop +"' .";
			fnpMymsg(textMessage);
			fnpDisplayingMessageInFrame(textMessage, 10);
			//fnpDropWOISRandDeleteSomeDataFromDB(facility, standard);
			fnpDropWOISRandDeleteSomeDataFromDB_speciallyForCorporate(facility, standard);
			fnpDropWOISRandDeleteSomeDataFromDB(facility, standard);
			
			createNewSingleWOFlag=true;
		}
		
		
		if (createNewSingleWOFlag) {
			textMessage="Going to create a new single wo ";
			fnpMymsg(textMessage);
			fnpDisplayingMessageInFrame(textMessage, 10);
			needToCreateSingleWO=true;

		}
		
		return needToCreateSingleWO;
	}

	
	
	
	public static void fnpCreateISRWO_excludingDBDropQuery() throws Throwable{

			
			fnpCommonLoginPart(classNameText);


			// -------First Create Work Order Page ----------------------------

			fnpWaitTillClickable("ClientLKPBtn");
			fnpClick_OR("ClientLKPBtn");

			String[][] lookup;
			lookup = lookup_criteria_processing((String) hashXlData.get("Client"));
			fnpSearchNSelectFirstRadioBtn(lookup, 1);

			//String clientNameExpected = lookup[3][2];			
			String clientNameExpected="";
			for (int i = 0; i < lookup.length; i++) {
				if(lookup[i][0].equalsIgnoreCase("Client #") || lookup[i][0].equalsIgnoreCase("Client Name")){
					clientNameExpected=lookup[i][2];
					break;
				}
			}
			
			

			String EnteredClient = fnpWaitTillTextBoxDontHaveValue("ClientTxtBox", "value");
			Assert.assertTrue(EnteredClient.contains(clientNameExpected), "Client Value is not selected properly from lookup as expected is --'" + clientNameExpected
					+ "' but actual is --'" + EnteredClient + "'.");
			fnpMymsg(" Client value is properly selected from client lookup");

			
			
/*			if ( (classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.ISO9001_CorporateTestCaseName))
					| (classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.ISO9001_CampusTestCaseName))
					) {
			fnpPFList("ISR_ORGUnitList", "ISR_ORGUnitListOptions", "ISR");
			}
			*/
			
			
			if (currentSuiteName.equalsIgnoreCase("ISR_suite")) {
				if (fnpCheckElementDisplayed("ISR_ORGUnitList", 5)) {
					
					fnpGetORObjectX("ISR_ORGUnitList").click();
					//int l=fnpCountChildElementPresenceImmediately_NotInOR(fnpGetORObjectX("ISR_ORGUnitListOptions"), "li");

					List<WebElement> objectlist5 = fnpGetORObjectX("ISR_ORGUnitListOptions").findElements(By.tagName("li"));
					int size = objectlist5.size();
					int validValues=0;
					String temp=null;
					for (int i = 0; i < size; i++) {
						temp=objectlist5.get(i).getText();
						if ((temp.contains("select")) || (temp.contains("Select"))) {
							//nothing for now
						}else{
							validValues++;
						}					
					}
					
					if (validValues<2) {
						Thread.sleep(2000);
						throw new Exception("This Org Unit drop down should be visible only in that case when there are more than 1 value for this field and here total values in this are --"+validValues);					
					} 
					fnpGetORObjectX("ISR_ORGUnitList").click();				
					fnpPFList("ISR_ORGUnitList", "ISR_ORGUnitListOptions", "ISR");
				}
				
			}
			

			
			
			
			fnpClick_OR("StandardLkpBtn");

			long totalRecords=0;
			try{
			String totalCompleteString=fnpGetText_OR("totalRecordsInFooterOfLookup_xpath").trim();
			String totalString=totalCompleteString.split("out of")[1].trim();
			totalRecords=Long.parseLong(totalString);
			}catch(Throwable t){
				fnpMymsg("Some error in fetching the total no. of records in Standard lookup");
				throw new Exception("Some error in fetching the total no. of records in Standard lookup");
			}
			
			if(totalRecords >0){
				fnpMymsg("Records are present in Standard lookup and their count are --"+totalRecords);
			}else{
				fnpMymsg("Either records are not present in lookup or some error in fetching the total no. of records in Standard lookup");
				throw new Exception("Either records are not present in lookup or some error in fetching the total no. of records in Standard lookup");
			}
			
			
			if(totalRecords<=100){
				Assert.assertTrue(fnpCheckElementDisplayed("StandardCodefilterTxtBxInLkp"), "Standard Code filter box is NOT  present as according to the condition if records " +
						"are less than or equal to 100 then filter box should come.");
				fnpType("OR", "StandardCodefilterTxtBxInLkp", (String) hashXlData.get("Standard"));
				fnpLoading_wait();
				
				int afterSearchRow =  fnpCountRowsIn_LOOKUPTable("LookupRowsForCountPurpose");
				int iwhileloopCounter=0;
				while (afterSearchRow > 1) {
					iwhileloopCounter++;
					Thread.sleep(1000);
					afterSearchRow =  fnpCountRowsIn_LOOKUPTable("LookupRowsForCountPurpose");
					if (iwhileloopCounter > (Integer.parseInt(CONFIG.getProperty("genMax_waitTime")))) {
						break;

					}
				}
				
				fnpClick_OR_WithoutWait("FirstRadioBtnInLkpAFSch");
				//fnpLoading_waitInsideDialogBox();
				fnpLoading_wait();

				Thread.sleep(1000);
			}else{
				fnpSearchNSelectFirstRadioBtn(1, (String) hashXlData.get("Standard"), 1);
			}
			
			
			
			

			String insertedStandardThroughLookup = fnpWaitTillTextBoxDontHaveValue("StandardTxtBox", "value");
			Assert.assertTrue(insertedStandardThroughLookup.contains((String) hashXlData.get("Standard")), "Standard value is not selected properly from lookup as expected is --'" + (String) hashXlData.get("Standard")
					+ "' but actual is --'" + insertedStandardThroughLookup + "'.");
			fnpMymsg(" Standard value is properly selected from client lookup");



			//fnpPFList("WOTypeList", "WOTypeListOptions", (String) hashXlData.get("WOType"));
			fnpPFListExactly("WOTypeList", "WOTypeListOptions", (String) hashXlData.get("WOType"));


			fnpClick_OR("NextBtn_id");

			fnpCheckErrMsg("Error thrown by applciation After Click Next Button  ");
			// -------Second Create Work Order Page ----------------------------

			fnpMymsg("Just before going to click AccountManagerLkpBtn");

			fnpWaitForVisible("AccountManagerTxtBox");
			String AlreadyAccountManager = fnpGetORObjectX("AccountManagerTxtBox").getAttribute("value");
			
			
/*
			if (AlreadyAccountManager.contains((String) hashXlData.get("AccountManager").trim())) {
				// nothing to do now

			} else {
				fnpClick_OR("AccountManagerLkpBtn");

				fnpMymsg("Just after  click AccountManagerLkpBtn");
				fnpMymsg("Just before going to insert value of Account Manger");
				fnpSearchNSelectFirstRadioBtn(2, (String) hashXlData.get("AccountManager"), 1);
				fnpMymsg("Just after  inserting value of Account Manger");
				String EnteredAccountManager = fnpGetORObjectX("AccountManagerTxtBox").getAttribute("value");
				EnteredAccountManager = fnpWaitTillTextBoxDontHaveValue("AccountManagerTxtBox", "value");
				Assert.assertTrue(EnteredAccountManager.contains((String) hashXlData.get("AccountManager")), "Account Manager is not selected properly from lookup");
				fnpMymsg(" Account Manager is properly selected from client lookup");
			}

			*/
			
			
			
			//doing changes for BS-04 script where account manager not to be selected for Associate wo
			
			//String expectedAccountManager=(String) hashXlData.get("AccountManager").trim();
			String expectedAccountManager;
			//if (loginAsFullName.equalsIgnoreCase("") | (loginAsFullName==null) ) {
			
/*			
			if (loginAsFullName.equalsIgnoreCase("") | (loginAsFullName==null) ) {
				expectedAccountManager=(String) hashXlData.get("AccountManager").trim();
			} else {
				expectedAccountManager=loginAsFullName;
			}
			*/
			
			if (  (loginAsFullName==null) ||loginAsFullName.equalsIgnoreCase("") ) {
			//String mainUser=CONFIG.getProperty("MainUserForSSO_withCompleteEmailID").trim();
			//if (mainUser.contains(loginAs)) {p
				
				if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
					expectedAccountManager=(String) hashXlData.get("AccountManager_Code").trim();
				
				}else{
					expectedAccountManager=(String) hashXlData.get("AccountManager").trim();				
				}

			} else {
			
				if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
					expectedAccountManager=loginUser_code;

				}else{
					expectedAccountManager=loginAsFullName;

				}
			}
		

			
			
			
			
			
			if (!(expectedAccountManager.equalsIgnoreCase(""))) {
				//if (AlreadyAccountManager.contains((String) hashXlData.get("AccountManager").trim())) {
				if (AlreadyAccountManager.contains(expectedAccountManager)) {
					// nothing to do now

				} else {
					fnpClick_OR("AccountManagerLkpBtn");

					fnpMymsg("Just after  click AccountManagerLkpBtn");
					fnpMymsg("Just before going to insert value of Account Manger");
					//fnpSearchNSelectFirstRadioBtn(2, expectedAccountManager, 1);
					




					if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
						fnpSearchNSelectFirstRadioBtn(1, expectedAccountManager, 1);
					}else{
						fnpSearchNSelectFirstRadioBtn(2, expectedAccountManager, 1);
					}
					
					
					
					fnpMymsg("Just after  inserting value of Account Manger");
					String EnteredAccountManager = fnpGetORObjectX("AccountManagerTxtBox").getAttribute("value");
					EnteredAccountManager = fnpWaitTillTextBoxDontHaveValue("AccountManagerTxtBox", "value");
					System.out.println("Entered ----"+EnteredAccountManager);
					System.out.println("Expected ----"+expectedAccountManager);
					Assert.assertTrue(EnteredAccountManager.contains(expectedAccountManager), "Account Manager is not selected properly from lookup");
					fnpMymsg(" Account Manager is properly selected from client lookup");
				}

			}
			

			if (   ((String) hashXlData.get("WOType").trim()).equalsIgnoreCase("Associated Facility")) {
				String completeXpathForWorkOrdertoassociatewith=OR.getProperty("WorkOrdertoassociatewith_part1")+(String) hashXlData.get("FirstWoNo")+OR.getProperty("WorkOrdertoassociatewith_part2");
				fnpGetORObjectX___NOR(completeXpathForWorkOrdertoassociatewith).click();
				Thread.sleep(3000);
			}
			
			
			/*** Some time after selecting the value in WO Prime Contact, and entered the value in summary,
			 *  value in this drop down gets clear and also there is no loading icon and might be this drop down gets refreshed, 
			 *  so giving here hard code wait or put fnpLoading_wait
			 */
			//Thread.sleep(5000);
			fnpLoading_wait();
			
			
			
			
			
			
		//	fnpPFList("WoPrimContactList", "WoPrimContactListOptions", (String) hashXlData.get("WOPrimaryContact"));
		//	fnpPFListByLiNo("WoPrimContactList", "WoPrimContactListOptions", 2);
			fnpPFListByLiNo("WoPrimContactList", "WoPrimContactListOptions", 1);
			
			//Thread.sleep(1000);
			fnpLoading_wait();
			
			
			

			if (classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.Cert_StandardsTestCaseName))
						 {	
				fnpPFListByLiNo("AccreditationBodyList", "AccreditationBodyListOptions", 1);
				fnpLoading_wait();
				
				fnpPFListByLiNo("ManagedByOfficeList", "ManagedByOfficeListOptions", 1);
				fnpLoading_wait();
				
			}
			
			
			
			

			fnpGetORObjectX("WOSummaryTxtBox").sendKeys((String) hashXlData.get("WOSummary") + fnTimestampDateWithTime());


			String IsThisTransferToNSFRadioBtnXpath = OR.getProperty("IsThisTransferToNSFRadioBtn_part1") + ((String) hashXlData.get("IsThisTransferToNSFRadioBtn"))
					+ OR.getProperty("IsThisTransferToNSFRadioBtn_part2");
			fnpGetORObjectX___NOR(IsThisTransferToNSFRadioBtnXpath).click();
			Thread.sleep(1000);


			
			if ((classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.Cert_StandardsTestCaseName))) {
				String IsthisforaspecifyingClientRadioBtn = OR.getProperty("IsthisforaspecifyingClientRadioBtn_part1")
						+ ((String) hashXlData.get("IsthisforaspecifyingClientRadioBtn")) + OR.getProperty("IsthisforaspecifyingClientRadioBtn_part2");
				fnpGetORObjectX___NOR(IsthisforaspecifyingClientRadioBtn).click();
				Thread.sleep(1000);
			}
			
			
			
			
			
			if ((classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.Non_Cert_StandardsTestCaseName))) {
				
				String IsthisforaspecifyingClientRadioBtn = OR.getProperty("IsthisforaspecifyingClientRadioBtn_part1")
						+ ((String) hashXlData.get("Is_Specifying_Client")) + OR.getProperty("IsthisforaspecifyingClientRadioBtn_part2");
				fnpGetORObjectX___NOR(IsthisforaspecifyingClientRadioBtn).click();
				Thread.sleep(1000);
				if (hashXlData.get("Is_Specifying_Client").equalsIgnoreCase("Yes")) {
					Thread.sleep(1000); // ADDED by satya
					fnpClick_OR("ChooseClientLkpBtn");
					String Specified_Client_Text = hashXlData.get("Specified_Client");
					fnpSearchNSelectFirstRadioBtn(1, Specified_Client_Text, 1);								
				}
		
			}
			
			
			
			
			
			
			fnpGetORObjectX("QuoteReceiveDateBtn").click();
			Thread.sleep(2000);

			String p = fnpGetORObjectX("QuoteReceiveTodayDate").getText();
			//System.out.println("@  ---today date is --" + p);
			fnpMymsg("@  ---today date is --" + p);



			/********* Using date Picker calender *********************************/
			if (Integer.parseInt(p) == 1) {
				
			//	driver.findElement(By.xpath(".//div[@id='ui-datepicker-div']//span[@class='ui-icon ui-icon-circle-triangle-w'][text()='Prev']")).click();
				fnpGetORObjectX("CalendarPrevIcon").click();
				
				Thread.sleep(1000);

				
				
				
				//List<WebElement> totaldays = driver.findElements(By.xpath(".//div[@id='ui-datepicker-div']//td/a"));
				//List<WebElement> totaldays = driver.findElements(By.xpath(OR.getProperty("CalendarAllDatesLink")));
				List<WebElement> totaldays =fnpGetORObject_list("CalendarAllDatesLink", 2);
				totaldays.get((totaldays.size()) - 1).click();
				Thread.sleep(1000);

			} else {
				//String yesterdayXpath = ".//div[@id='ui-datepicker-div']//td/a[text()='" + ((Integer.parseInt(p)) - 1) + "']";
				String yesterdayXpath = OR.getProperty("CalendarAllDatesLink")+"[text()='" + ((Integer.parseInt(p)) - 1) + "']";
				fnpGetORObjectX___NOR(yesterdayXpath).click();
				Thread.sleep(2000);
			}

			/****************************************************************************/

			Date todayDate;
			Calendar c;
			SimpleDateFormat sdfmt1;

			/*			
		*//*********
			 * Without Using date Picker calender,directly inserted in text
			 * box
			 *********************************/
			/*
			 * fnpGetORObjectX("QuoteReceiveDateTxtBx").clear(); Date todayDate
			 * = new Date(); Calendar c = Calendar.getInstance();
			 * 
			 * c = Calendar.getInstance(); c.setTime(todayDate);
			 * c.add(Calendar.DAY_OF_MONTH, -1); Date yesterdaydate =
			 * c.getTime();
			 * 
			 * SimpleDateFormat sdfmt1 = new SimpleDateFormat("dd-MMM-yyyy");
			 * String yesterdaydateInStringformat =
			 * sdfmt1.format(yesterdaydate); yesterdaydate =
			 * sdfmt1.parse(yesterdaydateInStringformat);
			 * 
			 * String expectedStringDateofYesterday =
			 * sdfmt1.format(yesterdaydate); fnpType("OR",
			 * "QuoteReceiveDateTxtBx", expectedStringDateofYesterday);
			 *//****************************************************************************/
			/*
			
		*/

			String entered = fnpGetAttribute_OR("QuoteReceiveDateTxtBx", "value");

			Thread.sleep(500);

			Date dt = new Date();

			c = Calendar.getInstance();
			c.setTime(dt);
			c.add(Calendar.DATE, -1);
			dt = c.getTime();

			String insertedDate = entered;
			sdfmt1 = new SimpleDateFormat("dd-MMM-yyyy");
			java.util.Date insertedDateViaCalender = sdfmt1.parse(insertedDate);

			Calendar c2 = Calendar.getInstance();
			c2.setTime(dt);
			Calendar c3 = Calendar.getInstance();
			c3.setTime(insertedDateViaCalender);

			int d1 = c2.get(Calendar.DAY_OF_MONTH);
			int d2 = c3.get(Calendar.DAY_OF_MONTH);

			Assert.assertEquals(d2, d1, "Date (QuoteReceiveDate is not inserted correctly through calender");


			String RushRadioBtnXpath = OR.getProperty("RushRadioBtn_part1") + ((String) hashXlData.get("RushRadioBtn")) + OR.getProperty("RushRadioBtn_part2");
			fnpGetORObjectX___NOR(RushRadioBtnXpath).click();
			Thread.sleep(1000);


			String insertedCertTargetDate;

			if ((classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.ISO9001_SingleTestCaseName))
					|| (classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.ISO9001_CorporateTestCaseName))
					|| (classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.ISO9001_CampusTestCaseName))
					) {
				todayDate = new Date();

				c = Calendar.getInstance();
				c.setTime(todayDate);
				c.add(Calendar.MONTH, 6);
				Date after6monthsdate = c.getTime();

				sdfmt1 = new SimpleDateFormat("dd-MMM-yyyy");
				String after6monthsdateInStringformat = sdfmt1.format(after6monthsdate);
				after6monthsdate = sdfmt1.parse(after6monthsdateInStringformat);

				String expectedStringDateAfterSixMonths = sdfmt1.format(after6monthsdate);
				insertedCertTargetDate = expectedStringDateAfterSixMonths;
				fnpType("OR", "CertTargateTxtBx", expectedStringDateAfterSixMonths);

			}



			String preassessmentRadioBtnXpath = OR.getProperty("PreassessmentRadioBtn_part1") + ((String) hashXlData.get("PreassessmentRadioBtn"))
					+ OR.getProperty("PreassessmentRadioBtn_part2");
			fnpGetORObjectX___NOR(preassessmentRadioBtnXpath).click();
			Thread.sleep(1000);
			
			//IPM 10037

/*			if ((classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.ISO9001_SingleTestCaseName))
					| (classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.ISO9001_CorporateTestCaseName))
					| (classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.ISO9001_CampusTestCaseName))
					) {
				fnpPFList("SurveillanceCyclePFList", "SurveillanceCyclePFListOptions", (String) hashXlData.get("SurveillanceCycle"));

			}

			
			*/
			
			fnpClick_OR("CreateWOBtn");

			fnpCheckError(" while creating new WO");


			String workOrderNo_text = fnpGetText_OR("NewlyCreatedWorkOrderNo");
			workOrderNo = ((workOrderNo_text.split(" "))[0]).trim();
			fnpMymsg("Newly created WO no. is:" + workOrderNo);
			
			ISR_IntegratedWOComing=false;
			if ((classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.ClassName_ISR_Single)) || (classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.ClassName_ISR_Corporate)) || (classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.ClassName_ISR_Campus))){
				String EditWO_DefaultSelectedTabXpath = "//li[@aria-selected='true']/a";
				String DefaultSelectedTabName = fnpGetText_NOR(EditWO_DefaultSelectedTabXpath);
				String DefaultSelectedTab_Xpath = "//li[@aria-selected='false']/a[contains(text(), '"+DefaultSelectedTabName.trim()+"')]";
				
				fnpClick_OR("ISRIntegratedStandardsTabLink");
				
				fnpWaitForVisible("ISRIntegratedStandardsTab_Isthisworkorderpartofintegratedstandardsdelivery_Text");
				
				driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
				if( (driver.findElements(By.xpath(OR.getProperty("ISRIntegratedStandardsTab_Integratedstandardsdelivery_No_RadioBttnSelected"))).size()>0) ){
					ISR_IntegratedWOComing=false;
				}else{
					ISR_IntegratedWOComing=true;
				}
				driver.manage().timeouts().implicitlyWait(Integer.parseInt(CONFIG.getProperty("genMax_waitTime")), TimeUnit.SECONDS);
			
				fnpGetORObjectX___NOR(DefaultSelectedTab_Xpath).click();
				fnpMymsg("PASSED == Clicked done on '"+DefaultSelectedTabName+"' Tab");
				fnpLoading_wait();
				fnpIpulseDuringLoading();
				
				
			}
			
			
	}


	
	//ex: WOISOTaskType =WOISOTaskType_DeskAudit
	
	public void fnpRecordChangeAI(String mainTaskName,String WOISOTaskType) throws Throwable{
		
		int taskAINameIndex = fnpFindColumnIndex("TaskTabTable_HeaderRow", WOISOTaskTab_TaskAISummaryTableColName_TaskAIName);
		int taskRowNo = fnpFindRowContainsName("TaskTabTable", mainTaskName, taskAINameIndex);
		//certRow=TaskRowNo;
		
		
		String taskOpenIconXpath = OR.getProperty("TaskTabISR_Task_OpenIconPart1") + WOISOTaskType+ OR.getProperty("TaskTabISR_Task_OpenIconPart2");
		
		
		if(  fnpCheckElementDisplayedImmediately_NOR(taskOpenIconXpath)   ) {				
			fnpClick_NOR(taskOpenIconXpath);
		}
		
		
		
		String taskInnerTableHeaderXpathForAI = OR.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part1") + (taskRowNo - 1)
				+ OR.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part2");

		String taskInnerTableDataXpathForAI = OR.getProperty("ISRTaskInnerTableForActionItemsTableData_part1") + (taskRowNo - 1)
				+ OR.getProperty("ISRTaskInnerTableForActionItemsTableData_part2");
		
		String taskExpansionPanelXpath = OR.getProperty("ISRTaskExpansionPanelGrid_part1") + (taskRowNo - 1) + OR.getProperty("ISRTaskExpansionPanelGrid_part2");

		
		/******12-07-2017*****New change for SQF, not review here in SQF but working fine for ISO *********************/
		if(!(classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.Cert_StandardsTestCaseName))){	
			
			
/*			 String taskAuditStatusXpath = taskExpansionPanelXpath + "//tr/td/label[text()='Audit Status']/../following-sibling::td/label";
			 String taskAuditStatus = fnpGetText_NOR(taskAuditStatusXpath);
			
			if (taskAuditStatus.equalsIgnoreCase("REVIEWED")) {
				fnpMymsg(mainTaskName+"  Audit status is  become 'REVIEWED'  as its value is ---'"+taskAuditStatus +"' and expected is --'"+"REVIEWED" +"' .");
			} else {
				fnpMymsg(mainTaskName+"  Audit status is NOT become 'REVIEWED'  as its value is ---"+taskAuditStatus+"' and expected is --'"+"REVIEWED" +"' .");
				throw new Exception(mainTaskName+"  Audit status is NOT become 'REVIEWED'  as its value is ---"+taskAuditStatus+"' and expected is --'"+"REVIEWED" +"' .");
			}
			*/
			
			
			//Assumption we are on Task tab in ISR
			fnpTaskAuditStatusVerify_basedOnTaskName( mainTaskName, WOISOTaskType, "REVIEWED" );
			
			
			
		}
		/**************************************************************************************************/
		
		boolean runJobForGeneratingClientRecordChange=true;
		String jobNameForGeneratingClientRecordChange="CreateClientRecordChangeAIQJob";
/*		String testUrlForRunningJob="http://oraapp10.nsf.org:8071/jobsecap/";
		String stageUrlForRunningJob="http://oraapp30c1.nsf.org:8071/jobsecap/";*/
		
		
	//	String testUrlForRunningJob=CONFIG.getProperty("TEST_Jobs_site_url");
	//	String stageUrlForRunningJob=CONFIG.getProperty("STAGE_Jobs_site_url");
		
		if (runJobForGeneratingClientRecordChange) {

			fnpRunJob("CreateClientRecordChangeAIQJob");
			
			
			
			
			
			fnpClick_OR("ISRTaskTab");
			taskOpenIconXpath = OR.getProperty("TaskTabISR_Task_OpenIconPart1") + WOISOTaskType+ OR.getProperty("TaskTabISR_Task_OpenIconPart2");
			taskRowNo = fnpFindRowContainsName("TaskTabTable", mainTaskName, taskAINameIndex);
			String visitNoInGivenTaskXpath = OR.getProperty("VisitNoAtTaskTab_part1") + (taskRowNo - 1) + OR.getProperty("VisitNoAtTaskTab_part2");
			if ( (fnpCheckElementDisplayedImmediately_NOR(taskOpenIconXpath) & (!fnpCheckElementDisplayedImmediately_NOR(visitNoInGivenTaskXpath)) )  ) {				
				fnpClick_NOR(taskOpenIconXpath);
			}
			
			 taskInnerTableHeaderXpathForAI = OR.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part1") + (taskRowNo - 1)
					+ OR.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part2");

			 taskInnerTableDataXpathForAI = OR.getProperty("ISRTaskInnerTableForActionItemsTableData_part1") + (taskRowNo - 1)
					+ OR.getProperty("ISRTaskInnerTableForActionItemsTableData_part2");
			
			try{
				fnpWaitForVisible_NotInOR(taskInnerTableHeaderXpathForAI);
				int itemDescColIndex1 = fnpFindColumnIndex_NOR(taskInnerTableHeaderXpathForAI, "AI Name");	
				int  actionItemInfoRowNo1 = fnpFindRow_NOR(taskInnerTableDataXpathForAI, WOISOTaskTab_Certification_ClientRecordChangeAIName, itemDescColIndex1);	
				 if (actionItemInfoRowNo1>0) {
					 fnpMymsg("@  ---'"+WOISOTaskTab_Certification_ClientRecordChangeAIName+"' action item is present in row no.---"+actionItemInfoRowNo1);
				 } else{
						 //throw new Exception("ClientRecordChange action item is not generated even after clicking/running its corresponding job");
						 throw new Exception("ClientRecordChange action item is not generated even after running job for this.");
					 }
			}catch(Throwable tClientRecordChange){
				 throw new Exception("Either ClientRecordChange action item is not generated even after running job for this or might be some other issue. Error is ---  "+tClientRecordChange.getMessage());
			}

			
			
			
			
			/***********************************************************/
			
		} else {
			
					
	
					/********************Looping here for ClientRecordChange untill we don't have any Run button **************/		
					int itemDescColIndex1 = fnpFindColumnIndex_NOR(taskInnerTableHeaderXpathForAI, "AI Name");	
					int actionItemInfoRowNo1=0;
					int whileloop=0;
					long maxWaitTime=60*30; //seconds
					while(true){
						
						whileloop++;
						fnpMymsg("@  ---Waiting for ClientRecordChange batch Job ---"+whileloop);
						try{
							actionItemInfoRowNo1 = fnpFindRow_NOR(taskInnerTableDataXpathForAI, WOISOTaskTab_Certification_ClientRecordChangeAIName, itemDescColIndex1);	
						 if (actionItemInfoRowNo1>0) {
							 fnpMymsg("@  ---'"+WOISOTaskTab_Certification_ClientRecordChangeAIName+"' action item is present in row no.---"+actionItemInfoRowNo1);
							break;
						} else{
							fnpMymsg("@  ---'"+WOISOTaskTab_Certification_ClientRecordChangeAIName+"' action item  is not present, so going to refresh it.");
							driver.navigate().refresh();
							taskOpenIconXpath = OR.getProperty("TaskTabISR_CertAudit_OpenIconPart1") + WOISOTaskType+ OR.getProperty("TaskTabISR_CertAudit_OpenIconPart2");
							taskRowNo = fnpFindRowContainsName("TaskTabTable", mainTaskName, taskAINameIndex);
							String visitNoInGivenTaskXpath = OR.getProperty("VisitNoAtTaskTab_part1") + (taskRowNo - 1) + OR.getProperty("VisitNoAtTaskTab_part2");
							if ( (fnpCheckElementDisplayedImmediately_NOR(taskOpenIconXpath) & (!fnpCheckElementDisplayedImmediately_NOR(visitNoInGivenTaskXpath)) )  ) {				
								fnpClick_NOR(taskOpenIconXpath);
							}
							
							
						}
						}catch(Throwable t){
							if (whileloop>maxWaitTime) {
								throw new Exception("'"+WOISOTaskTab_Certification_ClientRecordChangeAIName+"'  action item is not present even after waiting "+maxWaitTime+ "seconds");
							}else{
								fnpMymsg("@  ---'"+WOISOTaskTab_Certification_ClientRecordChangeAIName+"' action item  is not present, so going to refresh it in catch block -----"+whileloop);
								driver.navigate().refresh();
								taskOpenIconXpath = OR.getProperty("TaskTabISR_CertAudit_OpenIconPart1") + WOISOTaskType+ OR.getProperty("TaskTabISR_CertAudit_OpenIconPart2");
								taskRowNo = fnpFindRowContainsName("TaskTabTable", mainTaskName, taskAINameIndex);
								String visitNoInGivenTaskXpath = OR.getProperty("VisitNoAtTaskTab_part1") + (taskRowNo - 1) + OR.getProperty("VisitNoAtTaskTab_part2");
								if ( (fnpCheckElementDisplayedImmediately_NOR(taskOpenIconXpath) & (!fnpCheckElementDisplayedImmediately_NOR(visitNoInGivenTaskXpath)) )  ) {				
									fnpClick_NOR(taskOpenIconXpath);
								}
								
							}
						}
						
						finally{
							fnpMymsg("Executing finally block ----"+whileloop);
						Thread.sleep(1000);
						}
					}
					
					/******************************************************************************************************************************/
			
		}
		
		

		

		
		
		/***************New change in sprint 6.1***********************/
		fnpProcessAI_ISR_TaskTab_1(taskInnerTableDataXpathForAI,taskInnerTableHeaderXpathForAI,WOISOTaskTab_Certification_ClientRecordChangeAIName, "Completed","AISaveNCloseBtn");
		fnpVerifyAIStatusInISR(WOISOTaskType,mainTaskName,WOISOTaskTab_Certification_ClientRecordChangeAIName,"Completed");
		/**************************************************************/
		
	
		
		
	}
	
	
	
	public void fnpRunJob(String jobName) throws Throwable{
		
		Date startTimeOfWait_afterCickingJobRunButton;
		
		//String jobNameForGeneratingClientRecordChange="CreateClientRecordChangeAIQJob";
		
/*		String testUrlForRunningJob="http://oraapp10.nsf.org:8071/jobsecap/";
		String stageUrlForRunningJob="http://oraapp30c1.nsf.org:8071/jobsecap/";
		*/
		

		
	
			
		/****************Going to login in jobs url for clicking job for creating ClientRecordChange action item ***************/	
			//driver.close();
		//	driver.quit();	
		
		if (driver!=null) {
			driver.quit();	
		}							
			env=CONFIG.getProperty("Environment");
			String urlToOpenForJob=null;

			
			
			switch (env) {
			case "Dev":
				urlToOpenForJob=CONFIG.getProperty("DEV_Jobs_site_url").trim();
				//break;
				if (urlToOpenForJob.equalsIgnoreCase("")) {
					throw new Exception("Please provide job url on Dev environment");
				}else{
					break;
				}
				
			

			case "Test":
				urlToOpenForJob=CONFIG.getProperty("TEST_Jobs_site_url");
				break;
			
			case "Staging":
				urlToOpenForJob=CONFIG.getProperty("STAGE_Jobs_site_url");
				break;
				
			default:
				 throw new Exception ("FAILED == Environment variable is not defined.");

			}
		  
			

			fnpLaunchBrowser();

			String userName = CONFIG.getProperty("adminUsername");
			String password = CONFIG.getProperty("adminPassword");
							
			driver.get(urlToOpenForJob);
			
			
			
/*			
			if (  (loginAsFullName==null) ||loginAsFullName.equalsIgnoreCase("") ) {
				//nothing to do as we expect secure url is not here
			}else{
				fnpType("OR", "loginAs", loginAs);
				
			}
			
			fnpType("OR", "UserName_id", userName);
			fnpType("OR", "password_id", password);
			fnpMymsg("Just before login clicked");
			fnpClick_OR("Login_id");
			fnpClick_OR("xpathForAck");
			
			*/
			
			
			String mainUser=CONFIG.getProperty("MainUserForSSO_withCompleteEmailID").trim();
			fnpSSO_login(mainUser,  "");
			
			
			
			
			
			fnpCommonClickTopMainMenu("Menu", "FirstRefrenceLinkJustBelowToMenu", "QuartzJobsTopMenuLink", "JobNameFilterBox");				
			fnpType("OR", "JobNameFilterBox", jobName);
			
			int totalRows=fnpCountRowsInTable("JobTable");
			int jRowCountCounter = 0;
			while ((totalRows>1) & (jRowCountCounter < 60) ){
				jRowCountCounter++;
				Thread.sleep(1000);
				totalRows=fnpCountRowsInTable("JobTable");
			}

			String dataInFirstRowFirstColumn = fnpFetchFromTable("JobTable", 1, 1);
			int j = 0;
			while (((dataInFirstRowFirstColumn.contains("No Jobs found.")) || (dataInFirstRowFirstColumn.toLowerCase().contains("found")))      & j < 15) {
				j++;
				Thread.sleep(1000);
				dataInFirstRowFirstColumn = fnpFetchFromTable("JobTable", 1, 1);
			}

			if (((dataInFirstRowFirstColumn.toLowerCase().contains("found"))) || ((dataInFirstRowFirstColumn.contains("No Jobs found")))) {
				fnpMymsg("Qurartz job -'" + jobName + "' is  NOT present in Jobs table but it should  be present in this table after search. ");
				throw new Exception("Qurartz job -'" + jobName + "' is  NOT present in Jobs table but it should  be present in this table after search. ");
			} else {
				//nothing for now
			}

			
			String dataInJobNameColumn = fnpFetchFromTable("JobTable", 1, 2);				
			if (dataInJobNameColumn.equalsIgnoreCase(jobName)) {
				//Thread.sleep(2000);
				fnpClick_OR("FirstRunButtonInJobsTable");
				startTimeOfWait_afterCickingJobRunButton = new Date();
				fnpLoading_wait();
				Thread.sleep(3000);
				fnpWaitTillVisiblityOfElementNClickable("FirstRunButtonInJobsTable");//When application is too slow, then this table JobTable takes time to load
				
				fnpClick_OR("CurrentlyExecutingJob_linkXpath");
				
				
				int statusColIndex = fnpFindColumnIndex("JobTableHeader", "Status");
				String statusInFirstRowFirstColumn = fnpFetchFromTable("JobTable", 1, statusColIndex);
				if (statusInFirstRowFirstColumn.equalsIgnoreCase("NORMAL")) {
					String textMessage = "After clicking Run Now button, this job '"+jobName+"' is still in NORMAL status, so going to wait/pause here for few seconds to try again. ";
					fnpMymsg(textMessage);
					fnpDisplayingMessageInFrame(textMessage, 15);
					//Thread.sleep(30000);
					
					Thread.sleep(10000);
					fnpClick_OR("FirstRunButtonInJobsTable");//This is a workaround to click it again becasue sometime even after clicking run button, job does not start as it shows Normal sometime
					Thread.sleep(2000);
				}
				
			
				
				
/*				fnpClick_OR("ReloadCurrentlyExecutionJob");
				fnpLoading_wait();
				Thread.sleep(3000);
				
				*/
				
				
				
			} else {
				fnpMymsg("Qurartz job -'" + jobName + "' is  NOT present in Jobs table but it should  be present in this table after search. ");
				throw new Exception("Qurartz job -'" + jobName + "' is  NOT present in Jobs table but it should  be present in this table after search. ");
			}

			/*************************************************************************************************************/
			
			int waitCount = 0;
			//int maxWaitTimeInMinutes=5;
			int maxWaitTimeInMinutes=60;//30;//15;
			boolean exitCond1=false;
			boolean exitCond2=false;
			
			//Date startTimeOfWait_afterCickingJobRunButton = new Date();
			Date tempEndTime;
			long durationInMilliseconds;
			long diffMinutes;
			while (true) {
				
				
				
				//Thread.sleep(30000);
				
				
				
				
/*				waitCount++;
				Thread.sleep(1000 * 30 * 1);
				driver.navigate().refresh();
				
				fnpType("OR", "JobNameFilterBox", jobName);
				Thread.sleep(1000);
				
				*/
				
				
/*				fnpClick_OR("ReloadCurrentlyExecutionJob");
				fnpWaitForVisible("FirstRunButtonInJobsTable", 10);*/
				
				
				int statusColIndex = fnpFindColumnIndex("JobTableHeader", "Status");
				String statusInFirstRowFirstColumn = fnpFetchFromTable("JobTable", 1, statusColIndex);
				
				
				tempEndTime= new Date();
				durationInMilliseconds = tempEndTime.getTime()-startTimeOfWait_afterCickingJobRunButton.getTime();
				diffMinutes = durationInMilliseconds / (60 * 1000) % 60;
				
				
				if (statusInFirstRowFirstColumn.equalsIgnoreCase("NORMAL")) {
					//break;
					//fnpMymsg("Current status of this job '"+jobName+"' after approx. "+(waitCount/2)+" min. is --"+statusInFirstRowFirstColumn);
					fnpMymsg("Current status of this job '"+jobName+"' after approx. "+diffMinutes+" min. is --"+statusInFirstRowFirstColumn);
					exitCond1=true;
				}else{
					if (waitCount> (maxWaitTimeInMinutes*2) ) {// multiply by 2 here b/c loop takes approx. 30 seconds to complete
						msg="Status of Job is not changed to NORMAL even after approx. "+diffMinutes+" min. as it is currenlty --"+statusInFirstRowFirstColumn;
						throw new Exception(msg);
					} else {
						//continue in while loop
						fnpMymsg("Current status of this job '"+jobName+"' after approx. "+diffMinutes+" min. is --"+statusInFirstRowFirstColumn);
					}
				}
				
				
			//	fnpClick_OR("CurrentlyExecutingJob_linkXpath");
/*				
				if (!(fnpCheckElementDisplayedImmediately( "CurrentlyExecutingJobTable"))) {
					fnpClick_OR("CurrentlyExecutingJob_linkXpath");
				}
				*/
				
				
				int whileCounter=0;
				while(whileCounter<=3){
					whileCounter++;
					try{
						Thread.sleep(1000);
						//if (!(fnpCheckElementDisplayedImmediately("CurrentlyExecutingJobTable"))) {
						if (!(fnpCheckElementDisplayed("CurrentlyExecutingJobTable",10))) {
							fnpClick_OR("CurrentlyExecutingJob_linkXpath");
							//fnpWaitForVisible(APP_LOGS, driver, "CurrentlyExecutingJobTable");
							fnpWaitForVisible("CurrentlyExecutingJobTable", 20);
							break;
							}
						}catch(Throwable t){
							fnpMymsg("Currently Executing Job table is not visible after clicking Currently Executiong Job link, so going to click again--"+whileCounter);
							if (whileCounter>3) {
								throw t;
								
							}
							
					}
					
				}
					
				
				
				
				
				String dataInFirstRow = fnpFetchFromTable("CurrentlyExecutingJobTable", 1, 1);
				//if (dataInFirstRow.equalsIgnoreCase("No jobs are currently executing.")) {
				if (dataInFirstRow.contains("No jobs are currently")) {
					//break;
					fnpMymsg("No jobs are currently execution in the Executing Job table.");
					exitCond2=true;
				} else {
					//continue in loop
				//	fnpMymsg("Currenlty executing job in first row is----"+dataInFirstRow);
					
					int ourjobRow=fnpFindRow_usingForPagingToNotThrowErrorIfNotFoundInCurrentPage("CurrentlyExecutingJobTable", jobName, 1);
					
					if (ourjobRow>0) {
						fnpMymsg("Currenlty executing job '"+jobName+"' 's row no. is----"+ourjobRow);
						exitCond2=false;
					} else {
						fnpMymsg("Currenlty executing job '"+jobName+"' 's row no. is----"+ourjobRow);
						exitCond2=true;
					}
					
				}
				
				
				if ((exitCond1==true) & (exitCond2==true)) {
					fnpMymsg("both exit conditon 1 and 2 have become ture."); 
					break;
					
				}else{
					 exitCond1=false;
					 exitCond2=false;
				}
				
				
				
				
				waitCount++;
				Thread.sleep(1000 * 30 * 1);
/*				
				driver.navigate().refresh();				
				fnpType("OR", "JobNameFilterBox", jobName);
			*/	
				

				fnpClick_OR("ReloadCurrentlyExecutionJob");
				fnpWaitForVisible("FirstRunButtonInJobsTable", 30);
				
				
			//	Thread.sleep(1000);
				
				
				
				
				
			}
			
			
			
			
			
			
			/*****Now login back in normal iPulse application *****************/
		
			 driver.quit();
			fnpLaunchBrowserAndLogin();
			fnpSearchWorkOrderOnly(workOrderNo);				
			if (fnpCheckElementDisplayedImmediately("EditWOBtn")) {
				fnpClick_OR("EditWOBtn");
				fnpMymsg("@  ---Here EditWOBtn is visible , so going to click it");

			}else{
				fnpMymsg("@  ---Here EditWOBtn is NOT visible , so ignore  it");
			}			

			
			
			
			
			/***********************************************************/
			
		
	}
	
	
	
	
	

	
	public static void fnpProcessJob(String jobName,String jobAction,String expectedStatusAfterClickingJobButton) throws Throwable{
		

			
		/****************Going to login in jobs url for clicking job for creating ClientRecordChange action item ***************/	
			//driver.close();
		
		//	driver.quit();	
			
			if (driver!=null) {
				driver.quit();	
			}
			
			
			env=CONFIG.getProperty("Environment");
			String urlToOpenForJob=null;

			
			if (  (loginAsFullName==null) ||loginAsFullName.equalsIgnoreCase("") ) {
				if (env.equalsIgnoreCase("Test")) {
					urlToOpenForJob=CONFIG.getProperty("TEST_Jobs_site_url");
				}else{
					urlToOpenForJob=CONFIG.getProperty("STAGE_Jobs_site_url");
				}
				
			} else {
				if (env.equalsIgnoreCase("Test")) {
					urlToOpenForJob=CONFIG.getProperty("TEST_Jobs_Secure_site_url");
				}else{
					urlToOpenForJob=CONFIG.getProperty("STAGE_Jobs_Secure_site_url");
				}
				
			}
			
			

			fnpLaunchBrowser();

			String userName = CONFIG.getProperty("adminUsername");
			String password = CONFIG.getProperty("adminPassword");
							
			driver.get(urlToOpenForJob);
			
			if (  (loginAsFullName==null) ||loginAsFullName.equalsIgnoreCase("") ) {
				//nothing to do as we expect secure url is not here
			}else{
				fnpType("OR", "loginAs", loginAs);
				
			}
			
			fnpType("OR", "UserName_id", userName);
			fnpType("OR", "password_id", password);
			fnpMymsg("Just before login clicked");
			fnpClick_OR("Login_id");
			
			fnpLoading_wait();
			fnpClick_OR("xpathForAck");
			fnpMymsg("Just after clicking Acknowledge button");
			fnpWaitTillThisElementIsNoLongerDisplayedTillMaxWait_OR("xpathForAck", "Acknowledge button is still visible after clicking it", 120);
			fnpMymsg("Just after waiting for Acknowledge button visiblity over");
			
			
			fnpCommonClickTopMainMenu("Menu", "FirstRefrenceLinkJustBelowToMenu", "QuartzJobsTopMenuLink", "JobNameFilterBox");				
			fnpType("OR", "JobNameFilterBox", jobName);
			
			int totalRows=fnpCountRowsInTable("JobTable");
			int jRowCountCounter = 0;
			while ((totalRows>1) & (jRowCountCounter < 60) ){
				jRowCountCounter++;
				Thread.sleep(1000);
				totalRows=fnpCountRowsInTable("JobTable");
			}

			String dataInFirstRowFirstColumn = fnpFetchFromTable("JobTable", 1, 1);
			int j = 0;
			while (((dataInFirstRowFirstColumn.contains("No Jobs found.")) || (dataInFirstRowFirstColumn.toLowerCase().contains("found")))      & j < 15) {
				j++;
				Thread.sleep(1000);
				dataInFirstRowFirstColumn = fnpFetchFromTable("JobTable", 1, 1);
			}

			if (((dataInFirstRowFirstColumn.toLowerCase().contains("found"))) || ((dataInFirstRowFirstColumn.contains("No Jobs found")))) {
				fnpMymsg("Qurartz job -'" + jobName + "' is  NOT present in Jobs table but it should  be present in this table after search. ");
				throw new Exception("Qurartz job -'" + jobName + "' is  NOT present in Jobs table but it should  be present in this table after search. ");
			} else {
				//nothing for now
			}

			
			String dataInJobNameColumn = fnpFetchFromTable("JobTable", 1, 2);				
			if (dataInJobNameColumn.equalsIgnoreCase(jobName)) {
				//Thread.sleep(2000);
				//fnpClick_OR("FirstRunButtonInJobsTable");
				//fnpClick_OR("FirstPauseButtonInJobsTable");
				
				if (jobAction.equalsIgnoreCase("Run Now")) {
					fnpClick_OR("FirstRunButtonInJobsTable");
				} else {
					if (jobAction.equalsIgnoreCase("Pause")) {
						fnpClick_OR("FirstPauseButtonInJobsTable");
					}else{
						if (jobAction.equalsIgnoreCase("Resume")) {
							fnpClick_OR("FirstResumeButtonInJobsTable");
						}else{
							throw new Exception("code for this jobAction '"+jobAction+"' is not included/present in code.");
						}
						
					}
				}
				
				fnpLoading_wait();
				Thread.sleep(3000);
				//fnpWaitTillVisiblityOfElementNClickable("FirstRunButtonInJobsTable");//When application is too slow, then this table JobTable takes time to load
				//fnpWaitTillVisiblityOfElementNClickable("FirstPauseButtonInJobsTable");//When application is too slow, then this table JobTable takes time to load
				
				if (jobAction.equalsIgnoreCase("Run Now")) {
					fnpWaitTillVisiblityOfElementNClickable("FirstRunButtonInJobsTable");//When application is too slow, then this table JobTable takes time to load
				} else {
					if (jobAction.equalsIgnoreCase("Pause")) {
						fnpWaitTillVisiblityOfElementNClickable("FirstPauseButtonInJobsTable");//When application is too slow, then this table JobTable takes time to load
					}else{
						if (jobAction.equalsIgnoreCase("Resume")) {
							fnpWaitTillVisiblityOfElementNClickable("FirstResumeButtonInJobsTable");//When application is too slow, then this table JobTable takes time to load
						}else{
							throw new Exception("code for this jobAction '"+jobAction+"' is not included/present in code.");
						}
					}
				}
				
				fnpClick_OR("CurrentlyExecutingJob_linkXpath");
				
				
				int statusColIndex = fnpFindColumnIndex("JobTableHeader", "Status");
				String statusInFirstRowFirstColumn = fnpFetchFromTable("JobTable", 1, statusColIndex);
				if (statusInFirstRowFirstColumn.equalsIgnoreCase("NORMAL")) {
					String textMessage = "After clicking Run Now button, this job '"+jobName+"' is still in NORMAL status, so going to wait/pause here for 30 seconds. ";
					fnpMymsg(textMessage);
					fnpDisplayingMessageInFrame(textMessage, 25);
					Thread.sleep(30000);
				}
				
			
				
				
/*				fnpClick_OR("ReloadCurrentlyExecutionJob");
				fnpLoading_wait();
				Thread.sleep(3000);
				
				*/
				
				
				
			} else {
				fnpMymsg("Qurartz job -'" + jobName + "' is  NOT present in Jobs table but it should  be present in this table after search. ");
				throw new Exception("Qurartz job -'" + jobName + "' is  NOT present in Jobs table but it should  be present in this table after search. ");
			}

			/*************************************************************************************************************/
			
			int waitCount = 0;
			int maxWaitTimeInMinutes=5;
			boolean exitCond1=false;
			boolean exitCond2=false;
			while (true) {
				
				
				
				//Thread.sleep(30000);
				
				
				
				
/*				waitCount++;
				Thread.sleep(1000 * 30 * 1);
				driver.navigate().refresh();
				
				fnpType("OR", "JobNameFilterBox", jobName);
				Thread.sleep(1000);
				
				*/
				
				
/*				fnpClick_OR("ReloadCurrentlyExecutionJob");
				fnpWaitForVisible("FirstRunButtonInJobsTable", 10);*/
				
				
				int statusColIndex = fnpFindColumnIndex("JobTableHeader", "Status");
				String statusInFirstRowFirstColumn = fnpFetchFromTable("JobTable", 1, statusColIndex);
				//if (statusInFirstRowFirstColumn.equalsIgnoreCase("NORMAL")) {
				if (statusInFirstRowFirstColumn.equalsIgnoreCase(expectedStatusAfterClickingJobButton)) {
					//break;
					fnpMymsg("Current status of this job '"+jobName+"' after approx. "+(waitCount/2)+" min. is --"+statusInFirstRowFirstColumn);
					exitCond1=true;
				}else{
					if (waitCount> (maxWaitTimeInMinutes*2) ) {
						throw new Exception("Status of Job is not changed to "+expectedStatusAfterClickingJobButton+" even after approx. "+maxWaitTimeInMinutes+" min. as it is currenlty --"+statusInFirstRowFirstColumn);
					} else {
						//continue in while loop
						fnpMymsg("Current status of this job '"+jobName+"' after approx. "+(waitCount/2)+" min. is --"+statusInFirstRowFirstColumn);
					}
				}
				
				
			//	fnpClick_OR("CurrentlyExecutingJob_linkXpath");
/*				
				if (!(fnpCheckElementDisplayedImmediately( "CurrentlyExecutingJobTable"))) {
					fnpClick_OR("CurrentlyExecutingJob_linkXpath");
				}
				*/
				
				
				int whileCounter=0;
				while(whileCounter<=3){
					whileCounter++;
					try{
						Thread.sleep(1000);
						//if (!(fnpCheckElementDisplayedImmediately("CurrentlyExecutingJobTable"))) {
						if (!(fnpCheckElementDisplayed("CurrentlyExecutingJobTable",10))) {
							fnpClick_OR("CurrentlyExecutingJob_linkXpath");
							//fnpWaitForVisible(APP_LOGS, driver, "CurrentlyExecutingJobTable");
							fnpWaitForVisible("CurrentlyExecutingJobTable", 20);
							break;
							}
						}catch(Throwable t){
							fnpMymsg("Currently Executing Job table is not visible after clicking Currently Executiong Job link, so going to click again--"+whileCounter);
							if (whileCounter>3) {
								throw t;
								
							}
							
					}
					
				}
					
				
				
				
				
				String dataInFirstRow = fnpFetchFromTable("CurrentlyExecutingJobTable", 1, 1);
				//if (dataInFirstRow.equalsIgnoreCase("No jobs are currently executing.")) {
				if (dataInFirstRow.contains("No jobs are currently")) {
					//break;
					fnpMymsg("No jobs are currently execution in the Executing Job table.");
					exitCond2=true;
				} else {
					//continue in loop
				//	fnpMymsg("Currenlty executing job in first row is----"+dataInFirstRow);
					
					int ourjobRow=fnpFindRow_usingForPagingToNotThrowErrorIfNotFoundInCurrentPage("CurrentlyExecutingJobTable", jobName, 1);
					
					if (ourjobRow>0) {
						fnpMymsg("Currenlty executing job '"+jobName+"' 's row no. is----"+ourjobRow);
						exitCond2=false;
					} else {
						fnpMymsg("Currenlty executing job '"+jobName+"' 's row no. is----"+ourjobRow);
						exitCond2=true;
					}
					
				}
				
				
				if ((exitCond1==true) & (exitCond2==true)) {
					fnpMymsg("both exit conditon 1 and 2 have become ture."); 
					break;
					
				}else{
					 exitCond1=false;
					 exitCond2=false;
				}
				
				
				
				
				waitCount++;
				Thread.sleep(1000 * 30 * 1);
/*				
				driver.navigate().refresh();				
				fnpType("OR", "JobNameFilterBox", jobName);
			*/	
				

				fnpClick_OR("ReloadCurrentlyExecutionJob");
				fnpWaitForVisible("FirstRunButtonInJobsTable", 30);
				
				
			//	Thread.sleep(1000);
				
				
				
				
				
			}
			
			
			if (driver!=null) {
				driver.quit();	
			}
			
			
		
	}
	
	
	
	
	
	
	
	public static void fnpProcessJob(String url,String loginAs,String username,String password,String jobName,String jobAction,String expectedStatusAfterClickingJobButton) throws Throwable{
		
		boolean CompleteAutoAIJob_InActive = true;
			
		/****************Going to login in jobs url for clicking job for creating ClientRecordChange action item ***************/	
			//driver.close();
		
		//	driver.quit();	
			
			if (driver!=null) {
				driver.quit();	
			}
			
			

			

			fnpLaunchBrowser();

							
			driver.get(url);
			
/*			if (  loginAs.equalsIgnoreCase("") ) {
				//nothing to do as we expect secure url is not here
			}else{
				fnpType("OR", "loginAs", loginAs);
				
			}
			*/
			
			
			String mainUser=CONFIG.getProperty("MainUserForSSO_withCompleteEmailID").trim();
/*			//if (mainUser.equalsIgnoreCase(loginAs)) {
			if (mainUser.contains(loginAs)) {
				fnpSSO_login(mainUser,  "");
			} else {
				fnpSSO_login(mainUser,  loginAs);
			}
			*/
			
/*			
			
			fnpType("OR", "UserName_id", username);
			fnpType("OR", "password_id", password);
			fnpMymsg("Just before login clicked");
			fnpClick_OR("Login_id");
			
			fnpLoading_wait();
			fnpClick_OR("xpathForAck");
			fnpMymsg("Just after clicking Acknowledge button");
			fnpWaitTillThisElementIsNoLongerDisplayedTillMaxWait_OR("xpathForAck", "Acknowledge button is still visible after clicking it", 120);
			fnpMymsg("Just after waiting for Acknowledge button visiblity over");
			
			
			*/
			
			
			
			fnpSSO_login(mainUser,  "");
			
			
			
			fnpCommonClickTopMainMenu("Menu", "FirstRefrenceLinkJustBelowToMenu", "QuartzJobsTopMenuLink", "JobNameFilterBox");				
			fnpType("OR", "JobNameFilterBox", jobName);
			
			int totalRows=fnpCountRowsInTable("JobTable");
			int jRowCountCounter = 0;
			while ((totalRows>1) & (jRowCountCounter < 60) ){
				jRowCountCounter++;
				Thread.sleep(1000);
				totalRows=fnpCountRowsInTable("JobTable");
			}

			String dataInFirstRowFirstColumn = fnpFetchFromTable("JobTable", 1, 1);
			int j = 0;
			while (((dataInFirstRowFirstColumn.contains("No Jobs found.")) || (dataInFirstRowFirstColumn.toLowerCase().contains("found")))      & j < 15) {
				j++;
				Thread.sleep(1000);
				dataInFirstRowFirstColumn = fnpFetchFromTable("JobTable", 1, 1);
			}

			if (((dataInFirstRowFirstColumn.toLowerCase().contains("found"))) || ((dataInFirstRowFirstColumn.contains("No Jobs found")))) {
				if(jobName.equalsIgnoreCase("CompleteAutoAIJob")) {
					CompleteAutoAIJob_InActive = false;
				}else {
					fnpMymsg("Qurartz job -'" + jobName + "' is  NOT present in Jobs table but it should  be present in this table after search. ");
					throw new Exception("Qurartz job -'" + jobName + "' is  NOT present in Jobs table but it should  be present in this table after search. ");
				}
			} else {
				//nothing for now
			}

			if(CompleteAutoAIJob_InActive) {
				String dataInJobNameColumn = fnpFetchFromTable("JobTable", 1, 2);				
				if (dataInJobNameColumn.equalsIgnoreCase(jobName)) {
					//Thread.sleep(2000);
					//fnpClick_OR("FirstRunButtonInJobsTable");
					//fnpClick_OR("FirstPauseButtonInJobsTable");
					
					if (jobAction.equalsIgnoreCase("Run Now")) {
						fnpClick_OR("FirstRunButtonInJobsTable");
					} else {
						if (jobAction.equalsIgnoreCase("Pause")) {
							fnpClick_OR("FirstPauseButtonInJobsTable");
						}else{
							if (jobAction.equalsIgnoreCase("Resume")) {
								fnpClick_OR("FirstResumeButtonInJobsTable");
							}else{
								throw new Exception("code for this jobAction '"+jobAction+"' is not included/present in code.");
							}
							
						}
					}
					
					fnpLoading_wait();
					Thread.sleep(3000);
					//fnpWaitTillVisiblityOfElementNClickable("FirstRunButtonInJobsTable");//When application is too slow, then this table JobTable takes time to load
					//fnpWaitTillVisiblityOfElementNClickable("FirstPauseButtonInJobsTable");//When application is too slow, then this table JobTable takes time to load
					
					if (jobAction.equalsIgnoreCase("Run Now")) {
						fnpWaitTillVisiblityOfElementNClickable("FirstRunButtonInJobsTable");//When application is too slow, then this table JobTable takes time to load
					} else {
						if (jobAction.equalsIgnoreCase("Pause")) {
							fnpWaitTillVisiblityOfElementNClickable("FirstPauseButtonInJobsTable");//When application is too slow, then this table JobTable takes time to load
						}else{
							if (jobAction.equalsIgnoreCase("Resume")) {
								fnpWaitTillVisiblityOfElementNClickable("FirstResumeButtonInJobsTable");//When application is too slow, then this table JobTable takes time to load
							}else{
								throw new Exception("code for this jobAction '"+jobAction+"' is not included/present in code.");
							}
						}
					}
					
					fnpClick_OR("CurrentlyExecutingJob_linkXpath");
					
					
					int statusColIndex = fnpFindColumnIndex("JobTableHeader", "Status");
					String statusInFirstRowFirstColumn = fnpFetchFromTable("JobTable", 1, statusColIndex);
					if (statusInFirstRowFirstColumn.equalsIgnoreCase("NORMAL")) {
						String textMessage = "After clicking Run Now button, this job '"+jobName+"' is still in NORMAL status, so going to wait/pause here for 30 seconds. ";
						fnpMymsg(textMessage);
						fnpDisplayingMessageInFrame(textMessage, 25);
						Thread.sleep(30000);
					}
					
				
					
					
	/*				fnpClick_OR("ReloadCurrentlyExecutionJob");
					fnpLoading_wait();
					Thread.sleep(3000);
					
					*/
					
					
					
				} else {
					fnpMymsg("Qurartz job -'" + jobName + "' is  NOT present in Jobs table but it should  be present in this table after search. ");
					throw new Exception("Qurartz job -'" + jobName + "' is  NOT present in Jobs table but it should  be present in this table after search. ");
				}
	
				/*************************************************************************************************************/
				
				int waitCount = 0;
				int maxWaitTimeInMinutes=5;
				boolean exitCond1=false;
				boolean exitCond2=false;
				while (true) {
					
					
					
					//Thread.sleep(30000);
					
					
					
					
	/*				waitCount++;
					Thread.sleep(1000 * 30 * 1);
					driver.navigate().refresh();
					
					fnpType("OR", "JobNameFilterBox", jobName);
					Thread.sleep(1000);
					
					*/
					
					
	/*				fnpClick_OR("ReloadCurrentlyExecutionJob");
					fnpWaitForVisible("FirstRunButtonInJobsTable", 10);*/
					
					
					int statusColIndex = fnpFindColumnIndex("JobTableHeader", "Status");
					String statusInFirstRowFirstColumn = fnpFetchFromTable("JobTable", 1, statusColIndex);
					//if (statusInFirstRowFirstColumn.equalsIgnoreCase("NORMAL")) {
					if (statusInFirstRowFirstColumn.equalsIgnoreCase(expectedStatusAfterClickingJobButton)) {
						//break;
						fnpMymsg("Current status of this job '"+jobName+"' after approx. "+(waitCount/2)+" min. is --"+statusInFirstRowFirstColumn);
						exitCond1=true;
					}else{
						if (waitCount> (maxWaitTimeInMinutes*2) ) {
							throw new Exception("Status of Job is not changed to "+expectedStatusAfterClickingJobButton+" even after approx. "+maxWaitTimeInMinutes+" min. as it is currenlty --"+statusInFirstRowFirstColumn);
						} else {
							//continue in while loop
							fnpMymsg("Current status of this job '"+jobName+"' after approx. "+(waitCount/2)+" min. is --"+statusInFirstRowFirstColumn);
						}
					}
					
					
				//	fnpClick_OR("CurrentlyExecutingJob_linkXpath");
	/*				
					if (!(fnpCheckElementDisplayedImmediately( "CurrentlyExecutingJobTable"))) {
						fnpClick_OR("CurrentlyExecutingJob_linkXpath");
					}
					*/
					
					
					int whileCounter=0;
					while(whileCounter<=3){
						whileCounter++;
						try{
							Thread.sleep(1000);
							//if (!(fnpCheckElementDisplayedImmediately("CurrentlyExecutingJobTable"))) {
							if (!(fnpCheckElementDisplayed("CurrentlyExecutingJobTable",10))) {
								fnpClick_OR("CurrentlyExecutingJob_linkXpath");
								//fnpWaitForVisible(APP_LOGS, driver, "CurrentlyExecutingJobTable");
								fnpWaitForVisible("CurrentlyExecutingJobTable", 20);
								break;
								}
							}catch(Throwable t){
								fnpMymsg("Currently Executing Job table is not visible after clicking Currently Executiong Job link, so going to click again--"+whileCounter);
								if (whileCounter>3) {
									throw t;
									
								}
								
						}
						
					}
						
					
					
					
					
					String dataInFirstRow = fnpFetchFromTable("CurrentlyExecutingJobTable", 1, 1);
					//if (dataInFirstRow.equalsIgnoreCase("No jobs are currently executing.")) {
					if (dataInFirstRow.contains("No jobs are currently")) {
						//break;
						fnpMymsg("No jobs are currently execution in the Executing Job table.");
						exitCond2=true;
					} else {
						//continue in loop
					//	fnpMymsg("Currenlty executing job in first row is----"+dataInFirstRow);
						
						int ourjobRow=fnpFindRow_usingForPagingToNotThrowErrorIfNotFoundInCurrentPage("CurrentlyExecutingJobTable", jobName, 1);
						
						if (ourjobRow>0) {
							fnpMymsg("Currenlty executing job '"+jobName+"' 's row no. is----"+ourjobRow);
							exitCond2=false;
						} else {
							fnpMymsg("Currenlty executing job '"+jobName+"' 's row no. is----"+ourjobRow);
							exitCond2=true;
						}
						
					}
					
					
					if ((exitCond1==true) & (exitCond2==true)) {
						fnpMymsg("both exit conditon 1 and 2 have become ture."); 
						break;
						
					}else{
						 exitCond1=false;
						 exitCond2=false;
					}
					
					
					
					
					waitCount++;
					Thread.sleep(1000 * 30 * 1);
	/*				
					driver.navigate().refresh();				
					fnpType("OR", "JobNameFilterBox", jobName);
				*/	
					
	
					fnpClick_OR("ReloadCurrentlyExecutionJob");
					fnpWaitForVisible("FirstRunButtonInJobsTable", 30);
					
					
				//	Thread.sleep(1000);
					
					
					
					
					
				}
	}
			
			if (driver!=null) {
				driver.quit();	
			}
			
			
		
	}
	
	
	
	
	
	
	public void fnpRunOasisJob(String oasisJobButtonOR) throws Throwable{

		
		
		int maxTryOasisChance=3;
		int whiletrueCounter=0;
		while (true) {
			try{
				whiletrueCounter++;
					killprocess();
					
					String oldBrowser = browserName;
					//browserName = "IE";
					browserName = "chrome";
					fnpLaunchBrowser();
					browserName=oldBrowser;
					
	
					
					String oasisURL = excelSiteURL.split("Oasis_Url:")[1];	
					String oasisUserName = CONFIG.getProperty("adminUsername");
					String oasisLoginPassword = CONFIG.getProperty("adminPassword");
					
								
					
					fnpLoginIntoOasisForOasis_tryingMulitpleTimes_NotCheckingLogoutBtn( oasisURL, oasisUserName, oasisLoginPassword);
					
					if (fnpCheckElementDisplayedImmediately("Oasis_error_inDiv")) {
					//if (fnpCheckElementDisplayed_NOR(OR.getProperty("Oasis_error_inDiv"), 15)) {
						throw new Exception("Login is not successfull.");
					}
					
	

					

					fnpWaitForVisible("OasislogOut_xpath", 180);

					
					
					
					
					
					//Assert.assertTrue(fnpCheckElementDisplayed("OasislogOut_xpath"), "Oasis Logout link is not present.");	
					fnpMymsg("Oasis Logout link is present .");
					
					Assert.assertTrue(fnpCheckElementPresenceImmediately("OasisMenuHeader"), "Oasis's Menu header is not present.");
					fnpMymsg("Oasis's Menu header  is present .");
					
	
					Thread.sleep(1000);
					fnpMouseHover("Oasis_Admin_menu");
					fnpMymsg("@  ---Oasis opened successfully in chance --"+whiletrueCounter);
					break;
			}catch(Throwable t){
				if (whiletrueCounter>maxTryOasisChance) {
					throw new Exception("Unable to open Oasis successfully." + t.getMessage());
					
				}else{
					fnpMymsg("@  ---Oasis NOt opened successfully in chance --"+whiletrueCounter+"   Error is ----"+ t.getMessage());
				}
			}
			
		}
			

		
		
		
		
		
		
		

		Thread.sleep(2000);
		fnpClick_OR_WithoutWait("Oasis_Admin_SupportSubmenu");
	//	Thread.sleep(10000);
		Thread.sleep(2000);
		fnpCheckErrorUsingPageSource_Oasis();
		fnpWaitForVisible("Oasis_ManuallyRunReportSchedulerLink");
		
		fnpClick_OR_WithoutWait("Oasis_ManuallyRunReportSchedulerLink");
		
		
		
		fnpWaitForVisible(oasisJobButtonOR);
		fnpMouseHover(oasisJobButtonOR);
		Thread.sleep(4000);
		try{
		driver.manage().timeouts().pageLoadTimeout(600, TimeUnit.SECONDS);
		//driver.manage().timeouts().pageLoadTimeout(1200, TimeUnit.SECONDS);
		fnpClick_OR_WithoutWait(oasisJobButtonOR);
		
		
		
		
		
		
		}catch(org.openqa.selenium.TimeoutException t){
			
/*				if (!(t.getMessage().contains("page to load"))){
				throw t;
			}
			*/
			throw t;
		}
		finally{
			driver.manage().timeouts().pageLoadTimeout(Long.parseLong(CONFIG.getProperty("pageLoadTime")), TimeUnit.SECONDS);
		}
		
		
		fnpWaitForVisible("Oasis_ManuallyRunReportSchedulerLink",450);// means 15 minutes total as sometimes it takes more than 10 minutes
		
		

		driver.close();
		// driver.quit();

	}
	
	
	
	//Assumption we are on Task tab in ISR
	public static void fnpTaskAuditStatusVerify_basedOnTaskName(String mainTaskName,String WOISOTaskType , String expectedStatus) throws Throwable{
		int taskAINameIndex = fnpFindColumnIndex("TaskTabTable_HeaderRow", WOISOTaskTab_TaskAISummaryTableColName_TaskAIName);
		int taskRowNo = fnpFindRowContainsName("TaskTabTable", mainTaskName, taskAINameIndex);
		
		
		String taskOpenIconXpath = OR.getProperty("TaskTabISR_Task_OpenIconPart1") + WOISOTaskType+ OR.getProperty("TaskTabISR_Task_OpenIconPart2");
		
		
		if(  fnpCheckElementDisplayedImmediately_NOR(taskOpenIconXpath)   ) {				
			fnpClick_NOR(taskOpenIconXpath);
		}
		
		
		
		String taskInnerTableHeaderXpathForAI = OR.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part1") + (taskRowNo - 1)
				+ OR.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part2");

		String taskInnerTableDataXpathForAI = OR.getProperty("ISRTaskInnerTableForActionItemsTableData_part1") + (taskRowNo - 1)
				+ OR.getProperty("ISRTaskInnerTableForActionItemsTableData_part2");
		
		String taskExpansionPanelXpath = OR.getProperty("ISRTaskExpansionPanelGrid_part1") + (taskRowNo - 1) + OR.getProperty("ISRTaskExpansionPanelGrid_part2");

		
		
		 String taskAuditStatusXpath = taskExpansionPanelXpath + "//tr/td/label[text()='Audit Status']/../following-sibling::td/label";
		 String taskAuditStatus = fnpGetText_NOR(taskAuditStatusXpath);
		
		if (taskAuditStatus.equalsIgnoreCase(expectedStatus)) {
			fnpMymsg(mainTaskName+"  Audit status is  become '"+expectedStatus+"'  as its value is ---'"+taskAuditStatus +"' and expected is --'"+expectedStatus +"' .");
		} else {
			fnpMymsg(mainTaskName+"  Audit status is NOT become '"+expectedStatus+"'  as its value is ---"+taskAuditStatus+"' and expected is --'"+expectedStatus +".");
			throw new Exception(mainTaskName+"  Audit status is NOT become '"+expectedStatus+"'  as its value is ---"+taskAuditStatus+"' and expected is --'"+expectedStatus +".");
		}
	}
	
	
	
	//Assumption we are on Task tab in ISR
	public static void fnpTaskAuditStatusVerify_basedOnTaskNo(String taskNo,String mainTaskName, String expectedStatus) throws Throwable{
		
		 int taskAINoIndex = fnpFindColumnIndex("TaskTabTable_HeaderRow", WOISOTaskTab_TaskAISummaryTableColName_TaskAINo);
		 int taskRowNo  = fnpFindRowContainsName("TaskTabTable", taskNo, taskAINoIndex);
		
		String taskOpenIconXpath = OR.getProperty("TaskTabISR_Task_OpenIcon_BasedOnTaskNo_Part1") + taskNo+ OR.getProperty("TaskTabISR_Task_OpenIcon_BasedOnTaskNo__Part2");
		
		if(  fnpCheckElementDisplayedImmediately_NOR(taskOpenIconXpath)   ) {				
			fnpClick_NOR(taskOpenIconXpath);
		}
		
		
		
		String taskInnerTableHeaderXpathForAI = OR.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part1") + (taskRowNo - 1)
				+ OR.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part2");

		String taskInnerTableDataXpathForAI = OR.getProperty("ISRTaskInnerTableForActionItemsTableData_part1") + (taskRowNo - 1)
				+ OR.getProperty("ISRTaskInnerTableForActionItemsTableData_part2");
		
		String taskExpansionPanelXpath = OR.getProperty("ISRTaskExpansionPanelGrid_part1") + (taskRowNo - 1) + OR.getProperty("ISRTaskExpansionPanelGrid_part2");

		
		
		 String taskAuditStatusXpath = taskExpansionPanelXpath + "//tr/td/label[text()='Audit Status']/../following-sibling::td/label";
		 String taskAuditStatus = fnpGetText_NOR(taskAuditStatusXpath);
		
		if (taskAuditStatus.equalsIgnoreCase(expectedStatus)) {
			fnpMymsg(mainTaskName+"  Audit status is  become '"+expectedStatus+"'  as its value is ---'"+taskAuditStatus +"' and expected is --'"+expectedStatus +"' .");
		} else {
			fnpMymsg(mainTaskName+"  Audit status is NOT become '"+expectedStatus+"'  as its value is ---"+taskAuditStatus+"' and expected is --'"+expectedStatus +".");
			throw new Exception(mainTaskName+"  Audit status is NOT become '"+expectedStatus+"'  as its value is ---"+taskAuditStatus+"' and expected is --'"+expectedStatus +".");
		}
	}
	
	
	

	
	public void fnReCertAuditTask(String taskType,String mainTaskName,String fromAuditString) throws Throwable{
		
		
		fnpClick_OR("ISRTaskTab");

		String textMessage = "Now going to perform ReCert Audit task. ";
		fnpDisplayingMessageInFrame(textMessage, 5);
		String openIconXpath = OR.getProperty("TaskTabISR_CertAudit_OpenIconPart1") + taskType+ OR.getProperty("TaskTabISR_CertAudit_OpenIconPart2");


		if(  fnpCheckElementDisplayedImmediately_NOR(openIconXpath)   ) {				
			fnpClick_NOR(openIconXpath);
		}
		
		
		 int taskAINameIndex = fnpFindColumnIndex("TaskTabTable_HeaderRow", WOISOTaskTab_TaskAISummaryTableColName_TaskAIName);

		int taskRow = fnpFindRowContainsName("TaskTabTable", mainTaskName, taskAINameIndex);
	
		
		String auditNoInGivenTaskAuditXpath = OR.getProperty("AuditNoAtTaskTab_part1") + (taskRow - 1) + OR.getProperty("AuditNoAtTaskTab_part2");
		
		String auditNo = fnpGetText_NOR(auditNoInGivenTaskAuditXpath);
		
		

		String visitNoInGivenTaskAuditXpath = OR.getProperty("VisitNoAtTaskTab_part1") + (taskRow - 1) + OR.getProperty("VisitNoAtTaskTab_part2");

		 String visitNo = fnpGetText_NOR(visitNoInGivenTaskAuditXpath).trim();
		 fnpMymsg("Going to check visit no, as if visit no is not present/blank then it will go for clicking bundle audit button. So here visit no. is--'"+visitNo+"'.");			 
		if (visitNo.equalsIgnoreCase("")) {
			fnpGetORObjectX___NOR(auditNoInGivenTaskAuditXpath).click();
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

			if ( (fnpCheckElementDisplayedImmediately_NOR(openIconXpath)  )  ) {				
				fnpClick_NOR(openIconXpath);
				fnpMymsg("Clicked task open icon");
			}

			visitNo = fnpGetText_NOR(visitNoInGivenTaskAuditXpath).trim();
		}

					
					
		
		
		
		
		visitNoInGivenTaskAuditXpath = OR.getProperty("VisitNoAtTaskTab_part1") + (taskRow - 1) + OR.getProperty("VisitNoAtTaskTab_part2");		
		auditNoInGivenTaskAuditXpath = OR.getProperty("AuditNoAtTaskTab_part1") + (taskRow - 1) + OR.getProperty("AuditNoAtTaskTab_part2");
				
		auditNo = fnpGetText_NOR(auditNoInGivenTaskAuditXpath);				
		if (fnpCheckElementDisplayedImmediately("EditWOBtn")) {				
			fnpClick_OR("EditWOBtn");
		}
		


		
		
		/*****************IPM-6227***********************************************/
/*		
		String editBtnForEditGivenAuditTaskXpath = OR.getProperty("EditBtnForEditTaskAtTaskTab_part1") + (taskRow - 1) + OR.getProperty("EditBtnForEditTaskAtTaskTab_part2");			
		fnpClick_NOR(editBtnForEditGivenAuditTaskXpath);
		*/
		
/*		String expenseAllocationbtnXpath = OR.getProperty("ExpenseAllocationBtn_TaskAtTaskTab_part1") + (taskRow - 1) + OR.getProperty("ExpenseAllocationBtn_TaskAtTaskTab_part2");	
		fnpClick_NOR(expenseAllocationbtnXpath);
		*/
		
		
		
		String editFeesbtnXpath = OR.getProperty("EditFeesBtnForEditTaskAtTaskTab_part1") + (taskRow - 1) + OR.getProperty("EditFeesBtnForEditTaskAtTaskTab_part2");	
		fnpClick_NOR(editFeesbtnXpath);
		
		/***************************************************************************/
		
		
		
		Date todayDate=new Date();
		SimpleDateFormat sdfmt1 = new SimpleDateFormat("dd-MMM-yyyy");
		String todaydateInStringformat = sdfmt1.format(todayDate);
		
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
/*		
		fnpClick_OR("SaveBtnAtEditTask");
		fnpLoading_waitInsideDialogBox();
		*/
		
		fnpClickUsingJavascript(OR.getProperty("SaveBtnAtEditTask"));
		fnpLoading_waitInsideDialogBox();
		
		
		
		
		fnpClick_OR("ISRSFTabSaveBtn");
		
		
		if  (fnpCheckElementDisplayedImmediately_NOR(openIconXpath)  ) {				
			fnpClick_NOR(openIconXpath);
		}


		visitNo = fnpGetText_NOR(visitNoInGivenTaskAuditXpath).trim();
		if (visitNo.equalsIgnoreCase("")) {		
		throw new Exception("Visit No. is  not coming in cert audit task even after clicking bundle audit button at view audit screen.");			
		}
		
		fnpDisplayingMessageInFrame("Going to click this Visit no.  --" + visitNo, 5);

		
		
		fnpGetORObjectX___NOR(visitNoInGivenTaskAuditXpath).click();
		fnpClick_OR("EditBtnOnViewVisitPage");

		
		
		String todaydateInStringformat2 = sdfmt1.format(new Date());
		String scheduleEndDateforThisAudit = fnpSelectingAuditorThroughSAM(todaydateInStringformat2,afterOneWeekdateInStringformat);
		String scheduleStartDateforThisAudit=todaydateInStringformat2;
		//String scheduleEndDateforThisAudit=afterOneWeekdateInStringformat;
		
	/*	
		
		fnpCommonCodeForAuditorLookup();
		

		todaydateInStringformat = sdfmt1.format(new Date());

		fnpClick_OR("ScheduleStartDateCalBtn_xpath");
		Thread.sleep(2000);			
		fnpCalendarDatePicker_BySelectValues_From_DropDown2(todaydateInStringformat,
				OR.getProperty("calendarDatePickerSelectYear_xpath"), OR.getProperty("calendarDatePickerSelectMonth_xpath"));
		
		
		String scheduleStartDateforCertAudit=todaydateInStringformat;
		
		
		Thread.sleep(2000);	

		fnpClick_OR("ScheduleEndDateCalBtn_xpath");
		Thread.sleep(2000);			
		fnpCalendarDatePicker_BySelectValues_From_DropDown2(afterOneWeekdateInStringformat,
				OR.getProperty("calendarDatePickerSelectYear_xpath"), OR.getProperty("calendarDatePickerSelectMonth_xpath"));
		
		
		
		String scheduleEndDateforCertAudit=afterOneWeekdateInStringformat;
		
		Thread.sleep(2000);	

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


		
/*			
		//fnpVerifyFRSReportOnViewVistPage(certOpenIconXpath,visitNoInCertAuditXpath) ;
		fnpVerifyFRSReportOnViewVistPage_inChrome() ;
		
		*/
		
		

		int copyFromAudit_ForGivenTaskAudit;
		int copyToAudit_ForGivenTaskAudit;
		String usingGoldenProcedureOrOasis = (String) hashXlData.get("Audit_usingGoldenProcedureOrOasis");
		if (usingGoldenProcedureOrOasis.equalsIgnoreCase("golden")) {
			//copyFromAudit_ForGivenTaskAudit=Integer.parseInt((String) hashXlData.get("copy_From_Audit_ForCertAuditTask").trim());
			copyFromAudit_ForGivenTaskAudit=Integer.parseInt((String) fromAuditString.trim());
			copyToAudit_ForGivenTaskAudit=Integer.parseInt(auditNo.trim());
			
			fnpMymsg("Just before running golden procedure - COPY_AUDIT_TRANSACTION_SUBMIT");
			//fnpCallGoldenProcedure_COPY_AUDIT_TRANSACTION_SUBMIT(copyFromAudit_ForGivenTaskAudit,copyToAudit_ForGivenTaskAudit,(String) hashXlData.get("Auditor").trim());
			String Auditor;
			if (  (loginAsFullName==null) ||loginAsFullName.equalsIgnoreCase("") ) {
				Auditor=(String) hashXlData.get("Auditor").trim();
			} else {
				Auditor=loginAsFullName;
			}
			fnpCallGoldenProcedure_COPY_AUDIT_TRANSACTION_SUBMIT(copyFromAudit_ForGivenTaskAudit,copyToAudit_ForGivenTaskAudit,Auditor);
			fnpMymsg("Copy from "+mainTaskName+" Audit---'"+copyFromAudit_ForGivenTaskAudit +"'  -- and auditor is---"+Auditor);
			
			fnpMymsg("Golden procedure 'COPY_AUDIT_TRANSACTION_SUBMIT' ran successfully.");
		} else {
			
/*			
			if (usingGoldenProcedureOrOasis.equalsIgnoreCase("oasis")) {
				fnpOasisPartInISO("cert", visitNo);
				fnpLaunchBrowserAndLogin();
			} else {
				throw new Exception("Value is not assigned to usingGoldenOrOasis");
			}
		*/
			
			
			
		}
		

		//fnpLaunchBrowserAndLogin();
		fnpSearchWorkOrderOnly(workOrderNo);
		

		if (fnpCheckElementDisplayedImmediately("EditWOBtn")) {
			fnpClick_OR("EditWOBtn");
			fnpMymsg("@  ---Here EditWOBtn is visible , so going to click it");

		}else{
			fnpMymsg("@  ---Here EditWOBtn is NOT visible , so ignore  it");
		}
		
					
		
		
		fnpClick_OR("ISRTaskTab");
		
		
		if  (fnpCheckElementDisplayedImmediately_NOR(openIconXpath)  ) {				
			fnpClick_NOR(openIconXpath);
		}
		
		
		
		
		taskAINameIndex = fnpFindColumnIndex("TaskTabTable_HeaderRow", WOISOTaskTab_TaskAISummaryTableColName_TaskAIName);

		taskRow = fnpFindRowContainsName("TaskTabTable", mainTaskName, taskAINameIndex);
		
		String givenTaskExpansionPanelXpath = OR.getProperty("ISRTaskExpansionPanelGrid_part1") + (taskRow - 1) + OR.getProperty("ISRTaskExpansionPanelGrid_part2");
		
		
		
		
		String givenTaskInnerTableDataXpathForAI = OR.getProperty("ISRTaskInnerTableForActionItemsTableData_part1") + (taskRow - 1)
				+ OR.getProperty("ISRTaskInnerTableForActionItemsTableData_part2");
		
		String givenTaskInnerTableHeaderXpathForAI = OR.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part1") + (taskRow - 1)
				+ OR.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part2");
		
		

		
		String taskAuditorXpath = givenTaskExpansionPanelXpath + "//tr/td/label[text()='Auditor']/../following-sibling::td/label";
		String taskAuditor = fnpGetText_NOR(taskAuditorXpath);
		
		
		String accountManager;
		
		
/*		if (loginAsFullName.equalsIgnoreCase("") | (loginAsFullName==null) ) {
			accountManager=(String) hashXlData.get("AccountManager").trim();
		} else {
			accountManager=loginAsFullName;
		}
		*/
		
		if (  (loginAsFullName==null) ||loginAsFullName.equalsIgnoreCase("") ) {
			
			if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
				accountManager=(String) hashXlData.get("AccountManager_Code").trim();
			}else{
				accountManager=(String) hashXlData.get("AccountManager").trim();
			}

		} else {
		
			if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
				accountManager=loginUser_code;

			}else{
				accountManager=loginAsFullName;

			}
		}
	

		
		
		
		//if (taskAuditor.equalsIgnoreCase((String)hashXlData.get("AccountManager"))) {
		//if (taskAuditor.equalsIgnoreCase(accountManager)) {
		if (taskAuditor.contains(accountManager)) {
			fnpMymsg("Auditor is present in "+mainTaskName+" Audit and its value is ---"+taskAuditor);
		} else {
			fnpMymsg("Auditor is NOT present in  "+mainTaskName+"  Audit and its value is ---"+taskAuditor);
			throw new Exception("Auditor is NOT present in  "+mainTaskName+"  Audit");
		}
		
		
		
		
		
		

		String scheduleStartDateXpath = givenTaskExpansionPanelXpath + "//tr/td/label[text()='Schedule Start Date']/../following-sibling::td/label";
		String actualScheduleStartDate = fnpGetText_NOR(scheduleStartDateXpath);
		
		if (actualScheduleStartDate.equalsIgnoreCase(scheduleStartDateforThisAudit)) {
			fnpMymsg("Schedule Audit start date in   "+mainTaskName+"   Audit is present and its value is ---'"+actualScheduleStartDate +"' and expected is --'"+scheduleStartDateforThisAudit +"' .");
		} else {
			fnpMymsg("Schedule Audit start date in   "+mainTaskName+"   Audit  is NOT  matched as its value is ---"+actualScheduleStartDate+"' and expected is --'"+scheduleStartDateforThisAudit +"' .");
			//throw new Exception("Schedule Audit start date is NOT  present");
			throw new Exception("Schedule Audit start date in   "+mainTaskName+"   Audit  is NOT  matched as its value is ---"+actualScheduleStartDate+"' and expected is --'"+scheduleStartDateforThisAudit +"' .");
		}
		
		
		
		
		String scheduleEndDateXpath = givenTaskExpansionPanelXpath + "//tr/td/label[text()='Schedule End Date']/../following-sibling::td/label";
		String actualScheduleEndDate = fnpGetText_NOR(scheduleEndDateXpath);
		
		if (actualScheduleEndDate.equalsIgnoreCase(scheduleEndDateforThisAudit)) {
			fnpMymsg("Schedule Audit End date in   "+mainTaskName+"    Audit  is present and its value is ---'"+actualScheduleEndDate +"' and expected is --'"+scheduleEndDateforThisAudit +"' .");
		} else {
			fnpMymsg("Schedule Audit End date in   "+mainTaskName+"    Audit  is NOT  matched as its value is ---"+actualScheduleEndDate+"' and expected is --'"+scheduleEndDateforThisAudit +"' .");
			//throw new Exception("Schedule Audit End date is NOT  present");
			throw new Exception("Schedule Audit End date in   "+mainTaskName+"    Audit  is NOT  matched as its value is ---"+actualScheduleEndDate+"' and expected is --'"+scheduleEndDateforThisAudit +"' .");
		}
		
		
		
		String givenTaskAuditStatusXpath = givenTaskExpansionPanelXpath + "//tr/td/label[text()='Audit Status']/../following-sibling::td/label";
		String givenTaskAuditStatus = fnpGetText_NOR(givenTaskAuditStatusXpath);
		
		if (givenTaskAuditStatus.equalsIgnoreCase("SUBMITTED")) {
			fnpMymsg(mainTaskName+"     Audit status is  become 'SUBMITTED'  as its value is ---'"+givenTaskAuditStatus +"' and expected is --'"+"SUBMITTED" +"' .");
		} else {
			fnpMymsg(mainTaskName+"     Audit status is NOT become 'SUBMITTED'  as its value is ---"+givenTaskAuditStatus+"' and expected is --'"+"SUBMITTED" +"' .");
			//throw new Exception("Cert Audit status is NOT become 'SUBMITTED'");
			throw new Exception(mainTaskName+"     Audit status is NOT become 'SUBMITTED'  as its value is ---"+givenTaskAuditStatus+"' and expected is --'"+"SUBMITTED" +"' .");
		}
		
	


		
		


		 givenTaskInnerTableHeaderXpathForAI = OR.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part1") + (taskRow - 1)
				+ OR.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part2");

		int aiTypeInnerTableIndex = fnpFindColumnIndex_NOR(givenTaskInnerTableHeaderXpathForAI, "AI Type");
		int aiNumberInnerTableIndex = fnpFindColumnIndex_NOR(givenTaskInnerTableHeaderXpathForAI, "AI Number");

		givenTaskInnerTableDataXpathForAI  = OR.getProperty("ISRTaskInnerTableForActionItemsTableData_part1") + (taskRow - 1)
				+ OR.getProperty("ISRTaskInnerTableForActionItemsTableData_part2");
		
		
		int performAuditRow = fnpFindRowContainsName_NOR(givenTaskInnerTableDataXpathForAI, "PerformAudit", aiTypeInnerTableIndex);
		
		if (performAuditRow>0) {
			fnpMymsg("Perform Audit action item has been generated for   "+mainTaskName+"     Audit task.");
		} else {
			fnpMymsg("Perform Audit action item has NOT been generated for   "+mainTaskName+"     Audit task.");
			throw new Exception("Perform Audit action item has NOT been generated for   "+mainTaskName+"     Audit task.");
		}
		
		String performAuditAINo = fnpFetchFromTable_NOR(givenTaskInnerTableDataXpathForAI, performAuditRow, aiNumberInnerTableIndex);
		fnpMymsg("Perform Audit action item no.   is ---" + performAuditAINo);
		
		
/*
		//*******************************PerformAuditAICompleted Automatically Through Scheduler***********************************************************************************		
		
		int statusInnerTableColIndex = fnpFindColumnIndex_NOR(givenTaskInnerTableHeaderXpathForAI, "Status");
		int waitCount = 0;
		String performAuditStatus;


			
		//fnpClick_OR("Complete_Perform_Audit_AI_button");
	//	fnpRunJob("CompletePerformAuditAIQJob");
		fnpClick_OR("ISRTaskTab");
		if  (fnpCheckElementDisplayedImmediately_NOR(openIconXpath) ) {				
			fnpClick_NOR(openIconXpath);
		}

		
		fnpCheckError(" after loading ");
		

		
		
		
				
		waitCount=0;
		int maxWaitTimeInMinutes=50;//25;
		while (true) {
			waitCount++;

			taskAINameIndex = fnpFindColumnIndex("TaskTabTable_HeaderRow", WOISOTaskTab_TaskAISummaryTableColName_TaskAIName);
			taskRow = fnpFindRowContainsName("TaskTabTable", mainTaskName, taskAINameIndex);
			givenTaskInnerTableDataXpathForAI  = OR.getProperty("ISRTaskInnerTableForActionItemsTableData_part1") + (taskRow - 1)
					+ OR.getProperty("ISRTaskInnerTableForActionItemsTableData_part2");
			performAuditRow = fnpFindRowContainsName_NOR(givenTaskInnerTableDataXpathForAI, "PerformAudit", aiTypeInnerTableIndex);
			givenTaskInnerTableHeaderXpathForAI = OR.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part1") + (taskRow - 1)
					+ OR.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part2");
			
			statusInnerTableColIndex = fnpFindColumnIndex_NOR(givenTaskInnerTableHeaderXpathForAI, "Status");
			
			performAuditStatus = fnpFetchFromTable_NOR(givenTaskInnerTableDataXpathForAI, performAuditRow, statusInnerTableColIndex);
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
				
//				driver.navigate().refresh();


				fnpClick_OR("ISRInfoTab_EditWO");
				fnpCheckError(" after loading ");
				fnpClick_OR("ISRTaskTab");
				fnpCheckError(" after loading ");
				if ( fnpCheckElementDisplayedImmediately_NOR(openIconXpath)  ) {				
					fnpClick_NOR(openIconXpath);
				}

				
			}

			

		}
	
		//}
				
		
		
		//*******************************PerformAuditAICompleted Automatically Through Scheduler***********************************************************************************		
		
		*/
		fnpWaitForPerformAuditAIGetCompletedAutomaticallyThroughScheduler(mainTaskName);
		
		
		
		
		
		
		
		 aiTypeInnerTableIndex = fnpFindColumnIndex_NOR(givenTaskInnerTableHeaderXpathForAI, "AI Type");
		 aiNumberInnerTableIndex = fnpFindColumnIndex_NOR(givenTaskInnerTableHeaderXpathForAI, "AI Number");
		
		
		
		
		
		
		int technicalReviewAIRowNo = fnpFindRowContainsName_NOR(givenTaskInnerTableDataXpathForAI, WOISOTaskTab_TechnicalReviewAIName, aiTypeInnerTableIndex);
		
		if (technicalReviewAIRowNo>0) {
			fnpMymsg("TechnicalReview action item has been generated for   "+mainTaskName+"     Audit task.");
		} else {
			fnpMymsg("TechnicalReview action item has NOT been generated for   "+mainTaskName+"     Audit task.");
			throw new Exception("TechnicalReview action item has NOT been generated for   "+mainTaskName+"     Audit task.");
		}
		
		String technicalReviewAINo = fnpFetchFromTable_NOR(givenTaskInnerTableDataXpathForAI, technicalReviewAIRowNo, aiNumberInnerTableIndex);
		fnpMymsg("TechnicalReview action item no.   is ---" + technicalReviewAINo);
		
		
		int statusInnerTableColIndex = fnpFindColumnIndex_NOR(givenTaskInnerTableHeaderXpathForAI, "Status");

		String technicalReviewStatus = fnpFetchFromTable_NOR(givenTaskInnerTableDataXpathForAI, technicalReviewAIRowNo, statusInnerTableColIndex);
		
		if (!(technicalReviewStatus.equalsIgnoreCase("AI_CREATED"))) {
			
			fnpMymsg("Current Status of TechnicalReview action item is NOT 'AI_CREATED'  b/c its current status is --"+technicalReviewStatus);
			throw new Exception ("Current Status of TechnicalReview action item is NOT 'AI_CREATED'  b/c its current status is --"+technicalReviewStatus);
		} else {
			fnpMymsg("Current Status of TechnicalReview action item is AI_CREATED.");
		}
		
		
		
		
		
		
		
		//fnpProcessAI_ISR_TaskTab_NonConsoladatedScreen_and_basedOnAITypeCol(WOISOTaskType_CertificationAudit,WOISOTaskName_CertificationAudit,WOISOTaskTab_ExtDBUpdateAIType, "Completed");
		//fnpCompleteExdbUpdateAI( givenTaskInnerTableHeaderXpathForAI, givenTaskInnerTableDataXpathForAI, WOISOTaskType_ReCertificationAudit,WOISOTaskTab_ReCertification_MainTaskAIName,"Completed");
		
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
		fnpSearchWO_and_ReachToTaskTab_OpenDeskExpandIcon_ISR(mainTaskName);

		

		

		
		

		fnpProcessAI_ISR_TaskTab_1(givenTaskInnerTableDataXpathForAI,givenTaskInnerTableHeaderXpathForAI,WOISOTaskTab_TechnicalReviewAIName, "Completed","SaveButton__OnConsolidatedScreen");
		

		fnpVerifyAIStatusInISR(taskType,mainTaskName,WOISOTaskTab_TechnicalReviewAIName,"Completed");
		//fnpVerifyAIStatusInISR(Integer.parseInt(oldTaskAINo),    taskType,  mainTaskName,  "CertDecision","Completed");
		

	
		
		
		if ((classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.Cert_StandardsTestCaseName))) {
			
			taskRow = fnpFindRowContainsName("TaskTabTable", mainTaskName, taskAINameIndex);
			openIconXpath = OR.getProperty("TaskTabISR_CertAudit_OpenIconPart1") + taskType+ OR.getProperty("TaskTabISR_CertAudit_OpenIconPart2");
			if ( (fnpCheckElementDisplayedImmediately_NOR(openIconXpath) & (!(fnpCheckElementDisplayedImmediately_NOR(givenTaskInnerTableHeaderXpathForAI))) )  ) {				
				fnpClick_NOR(openIconXpath);
			}
											
			auditNoInGivenTaskAuditXpath = OR.getProperty("AuditNoAtTaskTab_part1") + (taskRow - 1) + OR.getProperty("AuditNoAtTaskTab_part2");		
			auditNo = fnpGetText_NOR(auditNoInGivenTaskAuditXpath);
			fnpDisplayingMessageInFrame("Going to click this Audit no. in   "+mainTaskName+"   Audit  --" + auditNo, 5);
	
			fnpGetORObjectX___NOR(auditNoInGivenTaskAuditXpath).click();
					
			/*********If we click this button it will automatically takes you to car tab  **********/
			fnpClick_OR("EditCarButtonOnViewAuditScreen");
			
			
			fnpProcessedCAR();
			

			
			fnpSearchWorkOrderOnly(workOrderNo);								
			if (fnpCheckElementDisplayedImmediately("EditWOBtn")) {
				fnpClick_OR("EditWOBtn");
			}
			fnpClick_OR("ISRTaskTab");								
			if ( (fnpCheckElementDisplayedImmediately_NOR(openIconXpath) & (!fnpCheckElementDisplayedImmediately_NOR(visitNoInGivenTaskAuditXpath)) )  ) {				
				fnpClick_NOR(openIconXpath);
			}
			
			
		}
		
		
		
		/*******For calulating task no and row no. for old recert audit task******************************************/		
		int taskAINoIndex = fnpFindColumnIndex("TaskTabTable_HeaderRow", WOISOTaskTab_TaskAISummaryTableColName_TaskAINo);
		taskAINameIndex = fnpFindColumnIndex("TaskTabTable_HeaderRow", WOISOTaskTab_TaskAISummaryTableColName_TaskAIName);
		taskRow = fnpFindRowContainsName("TaskTabTable", mainTaskName, taskAINameIndex);
		String oldTaskAINo = fnpFetchFromTable("TaskTabTable", taskRow, taskAINoIndex).trim();	
		fnpMymsg("old Recert Task no. is --"+oldTaskAINo);
		/****************************************************/
		
		
		
		
		
		fnpRecordChangeAI(mainTaskName,taskType);

		fnpRunOasisJob("Oasis_RunIQAULN_btn");
		
		
/*		
		fnpLaunchBrowserAndLogin();
		fnpSearchWorkOrderOnly(workOrderNo);
		

		if (fnpCheckElementDisplayedImmediately("EditWOBtn")) {
			fnpClick_OR("EditWOBtn");
			fnpMymsg("@  ---Here EditWOBtn is visible , so going to click it");

		}else{
			fnpMymsg("@  ---Here EditWOBtn is NOT visible , so ignore  it");
		}
		
	
		
		
		fnpClick_OR("ISRTaskTab");
		
		*/
		
		
		fnpRunJob("ProcessWorkflowQueueQJob");
		fnpClick_OR("ISRTaskTab");
		
		

		
		
		openIconXpath = OR.getProperty("TaskTabISR_Task_OpenIcon_BasedOnTaskNo_Part1") + oldTaskAINo+ OR.getProperty("TaskTabISR_Task_OpenIcon_BasedOnTaskNo__Part2");
		taskRow = fnpFindRowContainsName("TaskTabTable", oldTaskAINo, taskAINoIndex);
		givenTaskInnerTableDataXpathForAI  = OR.getProperty("ISRTaskInnerTableForActionItemsTableData_part1") + (taskRow - 1)
				+ OR.getProperty("ISRTaskInnerTableForActionItemsTableData_part2");

		givenTaskInnerTableHeaderXpathForAI = OR.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part1") + (taskRow - 1)
				+ OR.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part2");
		
		
		
		
		
		//if  (fnpCheckElementDisplayedImmediately_NOR(openIconXpath)  ) {	
		if  (fnpCheckElementDisplayed_NOR(openIconXpath, 20) ) {
			fnpClick_NOR(openIconXpath);
		}
		
		
	taskRow = fnpFindRowContainsName("TaskTabTable", oldTaskAINo, taskAINoIndex);
	String innerTableDataXpathForAI = OR.getProperty("ISRTaskInnerTableForActionItemsTableData_part1") + (taskRow - 1)
				+ OR.getProperty("ISRTaskInnerTableForActionItemsTableData_part2");
	String innerTableHeaderXpathForAI = OR.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part1") + (taskRow - 1)
				+ OR.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part2");
		

	openIconXpath = OR.getProperty("TaskTabISR_Task_OpenIcon_BasedOnTaskNo_Part1") + oldTaskAINo+ OR.getProperty("TaskTabISR_Task_OpenIcon_BasedOnTaskNo__Part2");
		
		
		 int waitCount = 0;
		 int certDecAIRowNo=0;

		while (true) {
			
			waitCount++;
			certDecAIRowNo = fnpFindRowContainsName_NOR(givenTaskInnerTableDataXpathForAI, "ecision", aiTypeInnerTableIndex);
			
			if (certDecAIRowNo>0) {
				fnpMymsg("Cert Decision action item has been generated for   "+mainTaskName+"   Audit task i.e. '"+oldTaskAINo+"'.");
				break;
			} else {

				fnpCheckError(" after loading ");
				fnpMymsg(" we are in  waiting loop for waiting cert decision action item generated.  ----" + waitCount);
				fnpDisplayingMessageInFrame("Now  we are in  waiting loop for waiting cert decision action item generated.----" + ((double)waitCount/2)+" approx. min.", 8);
				Thread.sleep(1000 * 20 * 1);
				fnpCheckError(" after loading ");
				driver.navigate().refresh();
				// fnpLoading_wait();
				//Thread.sleep(8000);

				// fnpLoading_wait();

				
				fnpCheckError(" after loading ");					
				fnpClick_OR("ISRTaskTab");
				fnpCheckError(" after loading ");
				
				

				
				if ( (fnpCheckElementDisplayedImmediately_NOR(openIconXpath) & (!(fnpCheckElementDisplayedImmediately_NOR(innerTableDataXpathForAI))) )  ) {				
					fnpClick_NOR(openIconXpath);
				}
				
			}

			if (waitCount > 60) {

				fnpMymsg("Script waited for approx. 30 min to generate Cert Decision action item but it is still not generated .");
				throw new Exception("Script waited for approx. 30 min to generate Cert Decision action item but it is still not generated .");

			}

		}



		
		
		String certDecAINo = fnpFetchFromTable_NOR(innerTableDataXpathForAI, certDecAIRowNo, aiNumberInnerTableIndex);
		fnpMymsg("Cert Decision action item no.   is ---" + certDecAINo);
		
		

		fnpProcessAI_ISR_TaskTab_1(innerTableDataXpathForAI,innerTableHeaderXpathForAI,"CertDecision", "Completed","SaveBtnOnPerformCertDecisionAI");

		//fnpVerifyAIStatusInISR(taskType,mainTaskName,"CertDecision","Completed");
		//fnpVerifyAIStatusInISR(Integer.parseInt(oldTaskAINo),    taskType,  mainTaskName,  "CertDecision","Completed");
		fnpVerifyAIStatusInISR_basedOnTaskNo(oldTaskAINo,    taskType,  mainTaskName,  "CertDecision","Completed");
		
		
		
		taskAINameIndex = fnpFindColumnIndex("TaskTabTable_HeaderRow", WOISOTaskTab_TaskAISummaryTableColName_TaskAIName);
		//TaskRowNo = fnpFindRowContainsName("TaskTabTable", WOISOTaskTab_Certification_MainTaskAIName, taskAINameIndex);
		taskRow = fnpFindRowContainsName("TaskTabTable", oldTaskAINo, taskAINoIndex);
		innerTableDataXpathForAI = OR.getProperty("ISRTaskInnerTableForActionItemsTableData_part1") + (taskRow - 1)
					+ OR.getProperty("ISRTaskInnerTableForActionItemsTableData_part2");
		innerTableHeaderXpathForAI = OR.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part1") + (taskRow - 1)
					+ OR.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part2");
					
		openIconXpath = OR.getProperty("TaskTabISR_Task_OpenIcon_BasedOnTaskNo_Part1") + oldTaskAINo+ OR.getProperty("TaskTabISR_Task_OpenIcon_BasedOnTaskNo__Part2");
		
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
			fnpMymsg("Cert Issue action item has NOT been generated for Cert Audit task.");
			throw new Exception("Cert Issue action item has NOT been generated for Cert Audit task.");
		}
		
		
		
		
		fnpProcessAI_ISR_TaskTab_1(innerTableDataXpathForAI,innerTableHeaderXpathForAI,"CertIssue", "Completed","SaveButton__OnConsolidatedScreen");

	
		fnpVerifyAIStatusInISR_basedOnTaskNo(oldTaskAINo,    taskType,  mainTaskName,  "CertIssue","Completed");
		
		
		
		taskAINameIndex = fnpFindColumnIndex("TaskTabTable_HeaderRow", WOISOTaskTab_TaskAISummaryTableColName_TaskAIName);
		//int TaskRowNo = fnpFindRowContainsName("TaskTabTable", WOISOTaskTab_Certification_MainTaskAIName, taskAINameIndex);
		taskRow = fnpFindRowContainsName("TaskTabTable", oldTaskAINo, taskAINoIndex);
		innerTableDataXpathForAI = OR.getProperty("ISRTaskInnerTableForActionItemsTableData_part1") + (taskRow - 1)
					+ OR.getProperty("ISRTaskInnerTableForActionItemsTableData_part2");
		innerTableHeaderXpathForAI = OR.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part1") + (taskRow - 1)
					+ OR.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part2");

	//	openIconXpath = OR.getProperty("TaskTabISR_Task_OpenIconPart1") + WOISOTaskType_CertificationAudit+ OR.getProperty("TaskTabISR_Task_OpenIconPart2");
		
		

		
		fnpTaskAuditStatusVerify_basedOnTaskNo( oldTaskAINo,mainTaskName, "REVIEWED");
		
		
		int totalRows=fnpCountRowsInTable("TaskTabTable");
		int taskAITypeIndex = fnpFindColumnIndex("TaskTabTable_HeaderRow", WOISOTaskTab_TaskAISummaryTableColName_TaskAIType);
		int totalRecertAuditRows=0;
		String taskAIType="";
		for (int i = 0; i < totalRows; i++) {
			
			taskAIType=fnpFetchFromTable("TaskTabTable", (i+1), taskAITypeIndex);
			fnpMymsg("Task/AI Type in "+(i+1)+"' is ---"+taskAIType);
			if (taskAIType.equalsIgnoreCase(WOISOTaskType_ReCertificationAudit)) {
				totalRecertAuditRows++;
			}
		}
				
		
		if (totalRecertAuditRows!=2) {
			throw new Exception("System has NOT created another Recertaud task  means 2 recert audit should be present at this moment. Total rows for recert audit are -- "+totalRecertAuditRows);
		}else{
			fnpMymsg("System has created another Recertaud task.");
		}
		
		
		
		
		fnpRunOasisJob("Oasis_RunAULN_btn");
		
		
		
		
		fnpLaunchBrowserAndLogin();
		fnpSearchWorkOrderOnly(workOrderNo);
		

		if (fnpCheckElementDisplayedImmediately("EditWOBtn")) {
			fnpClick_OR("EditWOBtn");
		}
		
					
		
		
		fnpClick_OR("ISRTaskTab");
		
		
		
		fnpTaskAuditStatusVerify_basedOnTaskNo(  oldTaskAINo,mainTaskName, "COMPLETED" );
		
		
		
	
		
		
		
		
		
		

		
		
	}
	
	
	
	
	public static String fnpSelectingAuditorThroughSAM(String startDateInString,String endDateInString) throws Throwable{
		String EndOnDate = "";
		Dimension originalDimensions=driver.manage().window().getSize();
		fnpMymsg(" before opening SAM, Dimensions is --"+originalDimensions);
		String originalHandle = driver.getWindowHandle();


		fnpMymsg(" Now going to Click SAM button.");
		ArrayList<String> listoldtabs2 = new ArrayList<String>(driver.getWindowHandles());
		fnsClick_OR("SAM_button");

		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		
		
		int oldtabscount2 = listoldtabs2.size();
		int newTotal = tabs.size();
		int ii = 0;
		while (newTotal != (oldtabscount2 + 1)) {
			Thread.sleep(2000);
			tabs = new ArrayList<String>(driver.getWindowHandles());
			newTotal = tabs.size();
			ii = ii + 1;
			if (ii > Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
				break;
			}

		}

	
		int tabsCount = tabs.size();
		for (int i = 0; i < tabsCount; i++) {

			fnpMymsg((i + 1) + "  window handle is:" + tabs.get(i));
			if (originalHandle.equalsIgnoreCase(tabs.get(i))) {
				// nothing to do

			} else {
				driver.switchTo().window(tabs.get(i));
				Thread.sleep(1000);
				break;
			}

		}
		Thread.sleep(1000);
		
		

		fnsLoading_wait_In_SAM(2);
		fnsLoading_wait_In_SAM(2);
		fnsLoading_wait_In_SAM(2);
		
	
		fnpMymsg(" SAM Dimensions are --"+driver.manage().window().getSize());
		
		fnpMymsg(" Now Dimensions are --"+driver.manage().window().getSize());
		
		

		try{
			fnsGet_Element_Enabled("AuditorNameTxtBxInSAM");
		}catch(Throwable t){
			fnpMymsg( "Either SAM is not opened properly or Auditor Name text box is not visible. See screenshot.");
			throw new Exception("Either SAM is not opened properly or Auditor Name text box is not visible. See screenshot.");	
		}
		
		
		
		
		
		
	
		String Auditor;
		if (  (loginAsFullName==null) ||loginAsFullName.equalsIgnoreCase("") ) {
			Auditor=(String) hashXlData.get("Auditor").trim();
		} else {
			Auditor=loginAsFullName;
		}
		
		
		
		if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
			Auditor=Auditor.trim().split(" ")[0].trim();
		}
	
	
		fnsClick_OR("AuditorNameTxtBxInSAM");
		fnpType("OR", "AuditorNameTxtBxInSAM", Auditor);
		fnsLoading_wait_In_SAM(1);
	
		int iWhileCounter=0;
		while(true){
			iWhileCounter++;
			try{
				fnpScrollToElement(fnpGetORObjectX("SearchBtnInSAM"));
				JavascriptExecutor executor = (JavascriptExecutor)driver;
				executor.executeScript("arguments[0].click();", fnpGetORObjectX("SearchBtnInSAM"));
				fnpMymsg("PASSED == Clicked Search button after inserting Auditor value in SAM");
				fnsLoading_wait_In_SAM(2);
				fnsLoading_wait_In_SAM(2);
				break;
			}catch(Throwable t){
				if (t.getMessage().contains("is not clickable at point")) {
					fnpMymsg("Search button in SAM is not clickable, so going to scroll");
					fnpScrollToElement(fnpGetORObjectX("SearchBtnInSAM"));
					try{
					fnpGetORObjectX("SearchBtnInSAM").click();
					fnpMymsg("PASSED == Clicked Search button after inserting Auditor value in SAM");
					fnsLoading_wait_In_SAM(2);
					fnsLoading_wait_In_SAM(2);
					break;
					}catch(Throwable tt){
						//just suppresssing error here
					}

				}
				
				if (iWhileCounter<3) {
					//fnpScroll(10, 200);
					Thread.sleep(2000);
				}else{
					throw t;
				}
					
				}
		}
		
		
		
		String xpathForAuditorLinkInSearchResultInSAM="";
		if(env.equalsIgnoreCase("test")){
			xpathForAuditorLinkInSearchResultInSAM="//a[contains(text(), '"+(String) hashXlData.get("Auditor_Code").trim()+"')]";
		}else{
			xpathForAuditorLinkInSearchResultInSAM="//a[contains(text(), '"+(String) hashXlData.get("Auditor").trim()+"')]";
		}
		fnsGet_Element_Displayed_NOR(xpathForAuditorLinkInSearchResultInSAM);
		fnsClick_NOR(xpathForAuditorLinkInSearchResultInSAM);
		fnsLoading_wait_In_SAM(1);
		fnsLoading_wait_In_SAM(1);
	
		fnsGet_Element_Enabled("AssignVisitInSAM");
		/*if (fnpCheckElementDisplayedImmediately("AssignVist_JustifyReason")) {
			fnpSimpleSelectList_usingSelectClass("AssignVist_JustifyReason", (String) hashXlData.get("Assign_Visit_Justify_Reason_In_SAM"));
			fnsLoading_wait_In_SAM(1);
		}*/


		

	
		fnsClick_OR("AssignVisitInSAM");
		fnpMymsg("Clicked AssignVisitInSAM in SAM");
		fnsLoading_wait_In_SAM(2);
		fnsLoading_wait_In_SAM(2);
		
		fnsGet_Element_Enabled("AssignVisitInSAM_OkContinue_Button");
		fnsClick_OR("AssignVisitInSAM_OkContinue_Button");
		fnpMymsg("Clicked AssignVisitInSAM_OkContinue_Button in SAM");
		fnsLoading_wait_In_SAM(1);
		fnsLoading_wait_In_SAM(1);
		
		
	

		fnsGet_Element_Enabled("AssignSchedulePopup_UserLinkInCardInSAM");
		fnsClick_OR("AssignSchedulePopup_UserLinkInCardInSAM");
	
		fnpMymsg("Clicked UserLinkInCardInSAM in SAM");

		fnsLoading_wait_In_SAM(2);
		
		/*
		 * fns_SelectDateFromCalendar_InSAM("Starts on", "1");
		 * fns_SelectDateFromCalendar_InSAM("Ends on", "8");
		 */
		 fns_SelectDateFromCalendar_InSAM_New("Starts on");
		 
		 
		WebElement EndOnDateLabel =  fnpGetORObjectX("SAM_EndOnDate_Label");
		Actions act = new Actions(driver) ;
		act.moveToElement(EndOnDateLabel).doubleClick().build().perform();
		Thread.sleep(2000);
		EndOnDate = fnpGetORObjectX("SAM_EndOnDate_Input").getAttribute("value").trim();
		 
		 
		 
		 
		 
		fnsClick_OR("SaveBtnInSAM");
		fnpMymsg("Clicked SaveBtnInSAM in SAM");
		fnsLoading_wait_In_SAM(2);
		fnsLoading_wait_In_SAM(2);
		
		
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		for(int i=0; i<=120; i++) {
			if(driver.findElements(By.xpath(OR.getProperty("AssignVisitInSAM_OkContinue_Button"))).size()>0) {
				fnsGet_Element_Enabled("AssignVisitInSAM_OkContinue_Button");
				fnsClick_OR("AssignVisitInSAM_OkContinue_Button");
				fnpMymsg("Clicked AssignVisitInSAM_OkContinue_Button in SAM");
				break;
			}else if(driver.findElements(By.xpath(OR.getProperty("AssignVisitInSAM_AlertSuccessMessage"))).size()>0) {
				fnpMymsg("PASSED == success message is coming into the SAM");
				break;
			}else {
				Thread.sleep(1000);
			}
		}
		driver.manage().timeouts().implicitlyWait(Element_Max_Wait_Time_SAM, TimeUnit.SECONDS);	
		fnsLoading_wait_In_SAM(1);
		fnsLoading_wait_In_SAM(1);
		
		driver.close();
		driver.switchTo().window(originalHandle);	
		return EndOnDate;
	}
	

public static void fnpSelectingAuditorThroughSAM_OLD(String startDateInString,String endDateInString) throws Throwable{
		
		Dimension originalDimensions=driver.manage().window().getSize();
		fnpMymsg(" before opening SAM, Dimensions is --"+originalDimensions);
		String originalHandle = driver.getWindowHandle();
		
/*		
		fnpMymsg("######### Setting the size of screen for SAM work around to max the screen###############################");
	//	driver.manage().window().setSize(new org.openqa.selenium.Dimension(800,700));
		driver.manage().window().setSize(new org.openqa.selenium.Dimension(1920,1080));
		Thread.sleep(2000);
		Dimension workaroundDimensions=driver.manage().window().getSize();
		fnpMymsg(" workaroundDimensions is --"+workaroundDimensions);
		
		fnpMymsg("######### Setting the size of screen for SAM work around to max the screen###############################");
		*/
		

		fnpMymsg(" Now going to Click SAM button.");
		ArrayList<String> listoldtabs2 = new ArrayList<String>(driver.getWindowHandles());
		fnpClick_OR("SAM_button");

		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		
		// wait till new tab is not opened successfully.
		int oldtabscount2 = listoldtabs2.size();
		int newTotal = tabs.size();
		int ii = 0;
		while (newTotal != (oldtabscount2 + 1)) {
			Thread.sleep(2000);
			tabs = new ArrayList<String>(driver.getWindowHandles());
			newTotal = tabs.size();
			ii = ii + 1;
			if (ii > Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
				break;
			}

		}

		// Now switch to new tab
		int tabsCount = tabs.size();
		for (int i = 0; i < tabsCount; i++) {

			fnpMymsg((i + 1) + "  window handle is:" + tabs.get(i));
			if (originalHandle.equalsIgnoreCase(tabs.get(i))) {
				// nothing to do

			} else {
				driver.switchTo().window(tabs.get(i));
				Thread.sleep(1000);
				break;
			}

		}
		Thread.sleep(1000);
		
		

		fnsLoading_wait_In_SAM(2);
		
	//	Thread.sleep(60000);
		fnpMymsg(" SAM Dimensions are --"+driver.manage().window().getSize());
		
		// Dimension d = new Dimension(1936,1056);//psingh machine
		// Dimension d = new Dimension(1080,800);
	     //Resize current window to the set dimension
	   //  driver.manage().window().setSize(orininalDimensions);
	    // driver.manage().window().setSize(new org.openqa.selenium.Dimension(1920,1080));
	  //   driver.manage().window().setSize(new org.openqa.selenium.Dimension(500,500));
		
	    // Thread.sleep(10000);
		fnsLoading_wait_In_SAM(2);
		
	     fnpMymsg(" Now Dimensions are --"+driver.manage().window().getSize());
		
		
		
		
/*		
		if (!(fnpCheckElementDisplayed("AuditorNameTxtBxInSAM", 120))) {
			throw new Exception("Either SAM is not opened properly or Auditor Name text box is not visible. See screenshot.");
			
		} 
		*/
		
		try{
		fnpWaitForVisible( "AuditorNameTxtBxInSAM", 120);
		}catch(Throwable t){
			fnpMymsg( "Either SAM is not opened properly or Auditor Name text box is not visible. See screenshot.");
			throw new Exception("Either SAM is not opened properly or Auditor Name text box is not visible. See screenshot.");	
		}
		
		
		
		
		
		
		//fnpType("OR", "AuditorNameTxtBxInSAM", (String) hashXlData.get("Auditor"));
		String Auditor;
		if (  (loginAsFullName==null) ||loginAsFullName.equalsIgnoreCase("") ) {
			Auditor=(String) hashXlData.get("Auditor").trim();
		} else {
			Auditor=loginAsFullName;
		}
		
		
		
		if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
			Auditor=Auditor.trim().split(" ")[0].trim();
		}
		
		
/*		fnpWaitTillVisiblityOfElementNClickable_OR("SearchBtnInSAM");
		fnpLoading_wait_In_SAM();
		fnpType("OR", "AuditorNameTxtBxInSAM", Auditor);
		*/
		
		fnpWaitTillVisiblityOfElementNClickable_OR("SearchBtnInSAM");
		fnsLoading_wait_In_SAM(2);
		//fnpDesireScreenshot_old("Before inseting into AuditorTxtBox");
		//fnpDesireScreenshot("Before inseting into AuditorTxtBox");
		//Thread.sleep(1000*60*2);
		fnpClick_InSAM_OR("AuditorNameTxtBxInSAM");
		fnpType("OR", "AuditorNameTxtBxInSAM", Auditor);
		fnsLoading_wait_In_SAM(2);
		//fnpDesireScreenshot_old("After inseting into AuditorTxtBox");
	//	fnpDesireScreenshot("After inseting into AuditorTxtBox");
		
		
		//fnpClick_InSAM_OR("SearchBtnInSAM");
		
		int iWhileCounter=0;
		while(true){
			iWhileCounter++;
			try{
				fnpScrollToElement(fnpGetORObjectX("SearchBtnInSAM"));
				//fnpGetORObjectX("SearchBtnInSAM").click();
				JavascriptExecutor executor = (JavascriptExecutor)driver;
				executor.executeScript("arguments[0].click();", fnpGetORObjectX("SearchBtnInSAM"));
				fnpMymsg("Clicked Search button after inserting Auditor value in SAM");
				fnsLoading_wait_In_SAM(2);
				break;
			}catch(Throwable t){
				if (t.getMessage().contains("is not clickable at point")) {
					fnpMymsg("Search button in SAM is not clickable, so going to scroll");
					fnpScrollToElement(fnpGetORObjectX("SearchBtnInSAM"));
					try{
					fnpGetORObjectX("SearchBtnInSAM").click();
					fnpMymsg("Clicked Search button after inserting Auditor value in SAM");
					fnsLoading_wait_In_SAM(2);
					break;
					}catch(Throwable tt){
						//just suppresssing error here
					}

				}
				
				if (iWhileCounter<3) {
					//fnpScroll(10, 200);
					Thread.sleep(2000);
				}else{
					throw t;
				}
					
				}
		}
		
		
		

		//fnpLoading_wait_In_SAM();
		

		//String xpathForAuditorLinkInSearchResultInSAM=OR.getProperty("AuditorLinkInSearchResultInSAM_part1")+Auditor+OR.getProperty("AuditorLinkInSearchResultInSAM_part2");
		String xpathForAuditorLinkInSearchResultInSAM=OR.getProperty("AuditorLinkInSearchResultInSAM");
		
		//fnpLoading_wait_In_SAM();
		int iwhileCounter=0;
		while(true){
			iwhileCounter++;			
			try{
				//fnpWaitForVisible("AuditorLinkInSearchResultInSAM",10);
				fnpWaitForVisible_NotInOR(xpathForAuditorLinkInSearchResultInSAM, 10);
				//fnpGetORObjectX("AuditorLinkInSearchResultInSAM").click();
				//fnpClick_InSAM_OR("AuditorLinkInSearchResultInSAM");
				fnpClick_NOR_withoutWait(xpathForAuditorLinkInSearchResultInSAM);
				fnsLoading_wait_In_SAM(2);
				
				break;
			}catch(Throwable t){
				
				
				
				if (iwhileCounter < 3) {
					//nothing to do					
				} else {
					//break;
					
					if  (   (t.getMessage().contains("Timed out"))   ||  (t.getMessage().contains("visibility of element located by"))   ) {
						throw new Exception(" "+Auditor+" link is not present in search auditor result.");
					} else {
						throw t;
					}
					
					
					
				}
				

				
				
			}
			
		}
		
		
	
		fnpMymsg("Clicked Auditor link in Search Results in SAM");
		fnpWaitForVisible("AssignVisitInSAM");
		if (fnpCheckElementDisplayedImmediately("AssignVist_JustifyReason")) {
			fnpSimpleSelectList_usingSelectClass("AssignVist_JustifyReason", (String) hashXlData.get("Assign_Visit_Justify_Reason_In_SAM"));
			
		}

	//	fnpLoading_wait_In_SAM();
		
		
		//fnpSimpleSelectList(ORXpath, expvalue)
		
/*		fnpWaitForVisible("AssignVisitInSAM");
		//fnpSimpleSelectList_usingSelectClass("AssignVist_JustifyReason", (String) hashXlData.get("Assign_Visit_Justify_Reason_In_SAM"));
		if (fnpCheckElementDisplayedImmediately("AssignVist_JustifyReason")) {
			fnpSimpleSelectList_usingSelectClass("AssignVist_JustifyReason", (String) hashXlData.get("Assign_Visit_Justify_Reason_In_SAM"));	
		}
				
	*/
		

		//fnpGetORObjectX("AssignVisitInSAM").click();
		fnpClick_InSAM_OR("AssignVisitInSAM");
		fnpMymsg("Clicked AssignVisitInSAM in SAM");

		//fnpLoading_wait_In_SAM();

		//fnpGetORObjectX("TestScriptUserLinkInCardInSAM").click();
		
		//fnpClick_InSAM_OR("TestScriptUserLinkInCardInSAM");
		
/*		
		String xpathForUserLinkInCardInSAM=OR.getProperty("LoginUserLinkInCardInSAM_part1")+Auditor+OR.getProperty("LoginUserLinkInCardInSAM_part2");
		fnpClick_NOR_withoutWait(xpathForUserLinkInCardInSAM);
		fnpLoading_wait_In_SAM();
		*/
		
		
/*		if (fnpCheckElementDisplayed("SAM_AlreadyAssigned_Ok_button", 10)) {
			fnpClick_InSAM_OR("SAM_AlreadyAssigned_Ok_button");
		}
		*/
		
		//fnpClick_InSAM_OR("AssignSchedulePopup_UserLinkInCardInSAM");
		
		
		
	//	if (fnpCheckElementDisplayed("AssignSchedulePopup_UserLinkInCardInSAM", 25)) {
		if (fnpCheckElementClickableOrNot_NOR(OR.getProperty("AssignSchedulePopup_UserLinkInCardInSAM"), 25)) {
			fnpClick_InSAM_OR("AssignSchedulePopup_UserLinkInCardInSAM");
		
			fnpMymsg("Clicked UserLinkInCardInSAM in SAM");
	
			fnsLoading_wait_In_SAM(2);
			
			
			/***********directly inserting the date in text box *************/
			fnpType("OR", "Schedule_StartOnTxtBx", startDateInString);
			/****************************************************************/
			
	/*		
			*//***********Selecting date through Calendar *************//*		
			fnpClick_OR_WithoutWait("StartsOnCalendarIcon");
			 Thread.sleep(1000);		 
			fnpCalendarDatePicker_BySelectValues_From_NextAndBack_SAM(startDateInString);
			Thread.sleep(1000);
			*//****************************************************************//*
			*/
			
			
			
			
			/***********directly inserting the date in text box *************/
			Thread.sleep(1000);
			fnpType("OR", "Schedule_EndsOnTxtBx", endDateInString);
			/****************************************************************/
			
			
	/*		
			*//***********Selecting date through Calendar *************//*
			fnpClick_OR_WithoutWait("EndsOnCalendarIcon");
			Thread.sleep(1000);
			fnpCalendarDatePicker_BySelectValues_From_NextAndBack_SAM(endDateInString);
			Thread.sleep(1000);
			*//****************************************************************//*
			*/
			
	
			//fnpGetORObjectX("SaveBtnInSAM").click();
			fnpClick_InSAM_OR("SaveBtnInSAM");
			fnpMymsg("Clicked SaveBtnInSAM in SAM");
	
			fnsLoading_wait_In_SAM(2);
		}else{
			
/*			if (fnpCheckElementDisplayed("SAM_AlreadyAssigned_Ok_button", 10)) {
				fnpClick_InSAM_OR("SAM_AlreadyAssigned_Ok_button");
			}
			*/
			
		}
		
		
		if (fnpCheckElementDisplayed_NOR(OR.getProperty("OKBtnInSAM"),5)) {

			//fnpGetORObjectX("OKBtnInSAM").click();
			fnpClick_InSAM_OR("OKBtnInSAM");
			fnpMymsg("Clicked OKBtnInSAM in SAM");

			//fnpLoading_wait_In_SAM();
		}
		
		fnsLoading_wait_In_SAM(2);
		fnpCheckError_In_SAM(" ");
		
		
		driver.close();
		driver.switchTo().window(originalHandle);
		
		// driver.manage().window().setSize(originalDimensions);
		
	//	driver.navigate().refresh();
		
		
		
	}
	public static void fnpProcessedCAR() throws Throwable{
		
		
		int totalCars=fnpCountRowsInTable("ViewAuditCorrectiveActions_table_data");
		
/*						
		if (totalCars>1) {
			throw new Exception("There should be only 1 car generated but they are ---"+totalCars);					
		} else{
			if (totalCars!=1) {
				throw new Exception("CAR has not been generated as it should be  1 car generated at this moment.");	
			} else {
				fnpMymsg("1 CAR is present here.");
			}
		}
		
		fnpClick_OR("FirstCarExpandIcon_xpath");
		fnpPFList("CAR_Status_PFList", "CAR_Status_PFListOptions", "Submitted");	
		fnpClick_OR("CARStatusSaveBtn");
		
		
		if (fnpCheckElementDisplayedImmediately("FirstCarExpandIcon_xpath")) {
			fnpClick_OR("FirstCarExpandIcon_xpath");
		}
		fnpPFList("CAR_Status_PFList", "CAR_Status_PFListOptions", "Approved");	
		fnpClick_OR("CARStatusSaveBtn");
		
	*/	
		
		
		if (totalCars>0) {
			fnpMymsg("Total cars are generated ---"+totalCars)	;				
		} else{
			if (totalCars==0) {
				throw new Exception("CAR has not been generated as CAR count  at this moment is --"+totalCars);	
			} 
		}
		
		
		String listXpath;
		String listXpathOptions;
		for(int i=0;i<totalCars;i++){
			fnpMymsg("Going to submit and approved car no. --"+(i+1));
			
			Thread.sleep(2000);

			fnpClick_NOR(OR.getProperty("CarExpandIcon_xpath_FirstPart")+(i+1)+OR.getProperty("CarExpandIcon_xpath_SecondPart"));	
			Thread.sleep(2000);
			Thread.sleep(2000);
			//fnpPFListModify_NOR(OR.getProperty("CAR_Status_PFList_part1")+i+OR.getProperty("CAR_Status_PFList_part2"), "Submitted");
			listXpath=OR.getProperty("CAR_Status_PFList_part1")+i+OR.getProperty("CAR_Status_PFList_part2");
			listXpathOptions=OR.getProperty("CAR_Status_PFListOptions_part1")+i+OR.getProperty("CAR_Status_PFListOptions_part2");
			fnpPFList_NOR(listXpath, listXpathOptions, "Submitted");

			fnpClick_NOR(OR.getProperty("CARStatusSaveBtn_part1")+i+OR.getProperty("CARStatusSaveBtn_part2"));
			if (fnpCheckElementDisplayedImmediately_NOR(OR.getProperty("CarExpandIcon_xpath_FirstPart")+(i+1)+OR.getProperty("CarExpandIcon_xpath_SecondPart"))) {
				fnpClick_NOR(OR.getProperty("CarExpandIcon_xpath_FirstPart")+(i+1)+OR.getProperty("CarExpandIcon_xpath_SecondPart"));
				Thread.sleep(2000);
			}
			Thread.sleep(2000);
		//	fnpPFListModify_NOR(OR.getProperty("CAR_Status_PFList_part1")+i+OR.getProperty("CAR_Status_PFList_part2"), "Approved");
			listXpath=OR.getProperty("CAR_Status_PFList_part1")+i+OR.getProperty("CAR_Status_PFList_part2");
			listXpathOptions=OR.getProperty("CAR_Status_PFListOptions_part1")+i+OR.getProperty("CAR_Status_PFListOptions_part2");
			fnpPFList_NOR(listXpath, listXpathOptions, "Approved");
			
			fnpClick_NOR(OR.getProperty("CARStatusSaveBtn_part1")+i+OR.getProperty("CARStatusSaveBtn_part2"));
			
			
		}
		
		

	}
	
	
	
	
	
	
	
	
	
	
	
	//Regarding query in ATA-67 on 14-12-2017 --Mail subject is -->Regarding query in ATA-67
	public static void fnpRunQueryAfterMovingWoToInReviewForBillCodes( String standard, String woNo) throws Throwable{
		

		fnpMymsg("******************");
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			fnpMymsg("******* Oracle JDBC Driver Registered *******");
		} catch (ClassNotFoundException e) {
			fnpMymsg("****************Oracle JDBC Driver is NOT found *************");
			fnpMymsg("########################################################################");
			e.printStackTrace();
			throw new Exception(e.getMessage());
			// return;
		}

		Connection connection = null;
		try {
		
			connection=fnpGetDBConnection();
			
			Statement stmt = connection.createStatement();

/*
			@SuppressWarnings("SQL_PREPARED_STATEMENT_GENERATED_FROM_NONCONSTANT_STRING")
			String query1 =	"SELECT decode(count(*),0,'N','Y') cou_code_setup_flg "
							+" FROM IQ.IQI_STD_BILL_CODES t "
							+" WHERE t.STD_CODE = '"+standard+"' "
							+" AND t.BILL_TO_OFFICE ='NSFUSA' --IQ_APP_ATTRIBUTES.BILL_TO_OFFICE "
							+" AND t.CURRENCY = 'USD' --IQ_APPLICATIONS.CURRENCY_CODE "
							+" AND t.program_code ='ISR' --CUSTOMERS.PRIM_PROG_CODE "
							+" AND t.cou_code='USA' -- CUSTOMERS.cou_code ";

*/
			String query1 = String.format("SELECT decode(count(*),0,'N','Y') cou_code_setup_flg  FROM IQ.IQI_STD_BILL_CODES t  WHERE t.STD_CODE = '%s'  AND t.BILL_TO_OFFICE ='NSFUSA' --IQ_APP_ATTRIBUTES.BILL_TO_OFFICE  AND t.CURRENCY = 'USD' --IQ_APPLICATIONS.CURRENCY_CODE  AND t.program_code ='ISR' --CUSTOMERS.PRIM_PROG_CODE  AND t.cou_code='USA' -- CUSTOMERS.cou_code ",standard);


/*
			@SuppressWarnings("SQL_PREPARED_STATEMENT_GENERATED_FROM_NONCONSTANT_STRING")
			String query2="SELECT decode(count(*),0,'N','Y') cou_code_setup_flg "
							+" FROM IQ.IQI_STD_BILL_CODES t "
							+" WHERE t.STD_CODE = '"+standard+"' "
							+" AND t.BILL_TO_OFFICE ='NSFUSA' --IQ_APP_ATTRIBUTES.BILL_TO_OFFICE "
							+" AND t.CURRENCY = 'USD' --IQ_APPLICATIONS.CURRENCY_CODE "
							+" AND t.program_code ='ISR' --CUSTOMERS.PRIM_PROG_CODE "
							+" -- AND t.cou_code='USA' -- CUSTOMERS.cou_code jin comment out this line if no cou_code set up "
							+" and not exists ( select 1 from IQ_CUS_STD_FINOPS f where F.ISBC_SEQ=t.seq and f.app_no='"+woNo+"' ) ";

			
			*/
			
			String query2 = String.format("SELECT decode(count(*),0,'N','Y') cou_code_setup_flg  FROM IQ.IQI_STD_BILL_CODES t  WHERE t.STD_CODE = '%s'  AND t.BILL_TO_OFFICE ='NSFUSA' --IQ_APP_ATTRIBUTES.BILL_TO_OFFICE  AND t.CURRENCY = 'USD' --IQ_APPLICATIONS.CURRENCY_CODE  AND t.program_code ='ISR' --CUSTOMERS.PRIM_PROG_CODE  -- AND t.cou_code='USA' -- CUSTOMERS.cou_code jin comment out this line if no cou_code set up  and not exists ( select 1 from IQ_CUS_STD_FINOPS f where F.ISBC_SEQ=t.seq and f.app_no='%s' ) ",standard,woNo);
			
			

/*			
			@SuppressWarnings("SQL_PREPARED_STATEMENT_GENERATED_FROM_NONCONSTANT_STRING")
			String query3="SELECT decode(count(*),0,'N','Y') cou_code_setup_flg "
							+" FROM IQ.IQI_STD_BILL_CODES t "		
							+" WHERE t.STD_CODE = '"+standard+"' "
							+" AND t.BILL_TO_OFFICE ='NSFUSA' --IQ_APP_ATTRIBUTES.BILL_TO_OFFICE "
							+" AND t.CURRENCY = 'USD' --IQ_APPLICATIONS.CURRENCY_CODE "
							+" AND t.program_code ='ISR' --CUSTOMERS.PRIM_PROG_CODE "
							+" AND t.cou_code='USA' -- CUSTOMERS.cou_code "
							+" and not exists ( select 1 from IQ_CUS_STD_FINOPS f where F.ISBC_SEQ=t.seq and f.app_no='"+woNo+"' ) ";
*/

			String query3 = String.format("SELECT decode(count(*),0,'N','Y') cou_code_setup_flg  FROM IQ.IQI_STD_BILL_CODES t  WHERE t.STD_CODE = '%s'  AND t.BILL_TO_OFFICE ='NSFUSA' --IQ_APP_ATTRIBUTES.BILL_TO_OFFICE  AND t.CURRENCY = 'USD' --IQ_APPLICATIONS.CURRENCY_CODE  AND t.program_code ='ISR' --CUSTOMERS.PRIM_PROG_CODE  AND t.cou_code='USA' -- CUSTOMERS.cou_code  and not exists ( select 1 from IQ_CUS_STD_FINOPS f where F.ISBC_SEQ=t.seq and f.app_no='%s' ) ",standard,woNo);
			

			fnpMymsg("Going to run first query  in fnpRunQueryAfterMovingWoToInReviewForBillCodes.");
			
			ResultSet rs = stmt.executeQuery(query1);
			String returnedValue="";
	        while (rs.next()) {	        	
	        	returnedValue= rs.getString("cou_code_setup_flg");
	        }
			
	        
        	///System.out.println( " First query in fnpRunQueryAfterMovingWoToInReviewForBillCodes, returned this value --"+returnedValue);
        	fnpMymsg(" First query in fnpRunQueryAfterMovingWoToInReviewForBillCodes, returned this value --"+returnedValue);

	        
	        
	        if (returnedValue.equalsIgnoreCase("N")) {
	        	fnpMymsg("Going to run second query  in fnpRunQueryAfterMovingWoToInReviewForBillCodes.");
				 rs = stmt.executeQuery(query2);
				 
		        while (rs.next()) {	        	
		        	returnedValue= rs.getString("cou_code_setup_flg");
		        }
				
		        
	        	//System.out.println(" Second query in fnpRunQueryAfterMovingWoToInReviewForBillCodes, returned this value --"+returnedValue);
	        	fnpMymsg(" Second query in fnpRunQueryAfterMovingWoToInReviewForBillCodes, returned this value --"+returnedValue);


			} else {
				if (returnedValue.equalsIgnoreCase("Y"))  {
					fnpMymsg("Going to run third query  in fnpRunQueryAfterMovingWoToInReviewForBillCodes.");
					 rs = stmt.executeQuery(query3);
					 
				        while (rs.next()) {	        	
				        	returnedValue= rs.getString("cou_code_setup_flg");
				        }
						
				        
			        //	System.out.println(" Third query in fnpRunQueryAfterMovingWoToInReviewForBillCodes, returned this value --"+returnedValue);
			        	fnpMymsg(" Third query in fnpRunQueryAfterMovingWoToInReviewForBillCodes, returned this value --"+returnedValue);

				} else {
					//throw new Exception("Not returning any value (Y or N) from executing first query in fnpRunQueryAfterMovingWoToInReviewForBillCodes");
				}

			}


			

			//connection.close();

		} catch (SQLException e) {
			fnpMymsg("*********************************************** Either connection Failed or some different error with Database,   Getting Error >>  " + e.getMessage().trim());
			fnpMymsg("=========================================================================================================================================");
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		
		finally{
			connection.close();
		}
		fnpMymsg("******************");

		// return rate;
		
	}
	
	
	
	
	
	public static void fnpDeleteFacilitySizeWhichIsAlreadyAdded() throws Throwable{
		
/*		String xpathForDeleteFacilityIcon=".//*[@id='mainForm:tabView:facSizeDT:0:facSizeDelete']";
		String xpathForDeleteFacilityConfirmationYesBtn=".//*[@id='mainForm:tabView:confirmDeleteYes']";
		*/
		String xpathForDeleteFacilityIcon=".//a[contains(@id,'mainForm:tabView:facInfoAccordPnl:facSizeDT:')] [contains(@id,':facSizeDelete')]";
		String xpathForDeleteFacilityConfirmationYesBtn=".//*[contains(@id,':confirmDeleteYes')]";
		
		while(fnpCheckElementDisplayedImmediately_NOR(xpathForDeleteFacilityIcon)){
			//fnpMymsg("Going to delete already added by someone Facility Size through script.");
			String textMessage="Going to delete  Facility Size (already added by someone) through script.";
			fnpMymsg(textMessage);
			fnpDisplayingMessageInFrame(textMessage, 10);
			fnpClick_NOR_withoutWait(xpathForDeleteFacilityIcon);
			
			
			//fnpMymsg("Now clicking confirmation Yes button to delete facility size.");
			textMessage="Now clicking confirmation Yes button to delete facility size.";
			fnpMymsg(textMessage);
			fnpDisplayingMessageInFrame(textMessage, 10);			
			fnpClick_NOR(xpathForDeleteFacilityConfirmationYesBtn);
			
			Thread.sleep(1000);
			
		}
		
		
	}
	
	
	
	
	
	
	public static void fnpDeleteFacilityEmployeeCountWhichIsAlreadyAdded() throws Throwable{
		
/*		String xpathForDeleteFacilityEmployeeCountIcon=".//*[@id='mainForm:tabView:empCountDtFcInfo:0:delShiftEmpFac']";
		String xpathForDeleteFacilityEmployeeCountConfirmationYesBtn=".//*[@id='mainForm:tabView:yesDelEmpCountFac']";
		*/
		String xpathForDeleteFacilityEmployeeCountIcon=".//a[contains(@id,'mainForm:tabView:facInfoAccordPnl:empCountDtFcInfo:')] [contains(@id,':delShiftEmpFac')]";
		String xpathForDeleteFacilityEmployeeCountConfirmationYesBtn=".//*[contains(@id,':yesDelEmpCountFac')]";
		
		
		
		while(fnpCheckElementDisplayedImmediately_NOR(xpathForDeleteFacilityEmployeeCountIcon)){
			//fnpMymsg("Going to delete Facility Employee count already added t through script");
			String textMessage="Going to delete Facility Employee count (already added by someone) through script.";
			fnpMymsg(textMessage);
			fnpDisplayingMessageInFrame(textMessage, 10);
			fnpClick_NOR_withoutWait(xpathForDeleteFacilityEmployeeCountIcon);
			
			//fnpMymsg("Now clicking confirmation Yes button to delete facility employee count.");			
			textMessage="Now clicking confirmation Yes button to delete facility employee count.";
			fnpMymsg(textMessage);
			fnpDisplayingMessageInFrame(textMessage, 10);
			fnpClick_NOR(xpathForDeleteFacilityEmployeeCountConfirmationYesBtn);
			
			Thread.sleep(1000);
			
		}
	}
	
	public static void  setISOSingleObject(){
		ISR_BS01 = new ISO9001_Single();
	}
	
	
	public static void addingIndustryCode() throws Throwable{
		
		
		
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

				

				int indusC = fnpFindRow_usingForPagingToNotThrowErrorIfNotFoundInCurrentPage("IndustryCodeProductTable", industryCode, 2);
				int indusPDetail = fnpFindRow_usingForPagingToNotThrowErrorIfNotFoundInCurrentPage("IndustryCodeProductTable", productValue, 3);

				if (indusC > 0) {
					fnpMymsg("@  --- Industry code '" + industryCode + "' is already present in row no--" + indusC);
					fnpMymsg("@  --- product detail '" + productValue + "' is already present in row no--" + indusPDetail);
					if (indusC != indusPDetail) {
						fnpMymsg("@  --- Industry code '" + industryCode + "' and product detail '" + productValue
								+ "' is NOT already present in same row no--" + indusC);
						fnpMymsg("@  ---So going to add them again.");
						//addingIndustryCodeAndProductDetail();
						addingIndustryCodeAndProductDetail(industryCode,productValue);
						
						indusC = fnpFindRow_usingForPagingToNotThrowErrorIfNotFoundInCurrentPage("IndustryCodeProductTable", industryCode, 2);
						indusPDetail = fnpFindRow_usingForPagingToNotThrowErrorIfNotFoundInCurrentPage("IndustryCodeProductTable", productValue, 3);
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
										
					//indusC = fnpFindRow_usingForPagingToNotThrowErrorIfNotFoundInCurrentPage("IndustryCodeProductTable", industryCode, 2);
					//indusPDetail = fnpFindRow_usingForPagingToNotThrowErrorIfNotFoundInCurrentPage("IndustryCodeProductTable", productValue, 3);
					
					
					if (classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.ISO9001_SingleTestCaseName)){
						indusC = fnpFindRow_usingForPagingToNotThrowErrorIfNotFoundInCurrentPage("IndustryCodeProductTable", industryCode, 2);
						indusPDetail = fnpFindRow_usingForPagingToNotThrowErrorIfNotFoundInCurrentPage("IndustryCodeProductTable", productValue, 3);
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
				
				
	}
	
	
	
	
	
	
	
	public static void addingIndustryCode_usingIn_addFacilityOnCampusWOPopup() throws Throwable{
		
		
		
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

				

				int indusC = fnpFindRow_usingForPagingToNotThrowErrorIfNotFoundInCurrentPage("IndustryCodeProductTable_in_addFacilityOnCampusWOPopup", industryCode, 2);
				int indusPDetail = fnpFindRow_usingForPagingToNotThrowErrorIfNotFoundInCurrentPage("IndustryCodeProductTable_in_addFacilityOnCampusWOPopup", productValue, 3);
				

				if (indusC > 0) {
					fnpMymsg("@  --- Industry code '" + industryCode + "' is already present in row no--" + indusC);
					fnpMymsg("@  --- product detail '" + productValue + "' is already present in row no--" + indusPDetail);
					if (indusC != indusPDetail) {
						fnpMymsg("@  --- Industry code '" + industryCode + "' and product detail '" + productValue
								+ "' is NOT already present in same row no--" + indusC);
						fnpMymsg("@  ---So going to add them again.");
						//addingIndustryCodeAndProductDetail();
						addingIndustryCodeAndProductDetail_usingIn_addFacilityOnCampusWOPopup(industryCode,productValue);
						
						indusC = fnpFindRow_usingForPagingToNotThrowErrorIfNotFoundInCurrentPage("IndustryCodeProductTable_in_addFacilityOnCampusWOPopup", industryCode, 2);
						indusPDetail = fnpFindRow_usingForPagingToNotThrowErrorIfNotFoundInCurrentPage("IndustryCodeProductTable_in_addFacilityOnCampusWOPopup", productValue, 3);
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
					addingIndustryCodeAndProductDetail_usingIn_addFacilityOnCampusWOPopup(industryCode,productValue);
										
					//indusC = fnpFindRow_usingForPagingToNotThrowErrorIfNotFoundInCurrentPage("IndustryCodeProductTable", industryCode, 2);
					//indusPDetail = fnpFindRow_usingForPagingToNotThrowErrorIfNotFoundInCurrentPage("IndustryCodeProductTable", productValue, 3);
					
					
					if (classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.ISO9001_SingleTestCaseName)){
						indusC = fnpFindRow_usingForPagingToNotThrowErrorIfNotFoundInCurrentPage("IndustryCodeProductTable_in_addFacilityOnCampusWOPopup", industryCode, 2);
						indusPDetail = fnpFindRow_usingForPagingToNotThrowErrorIfNotFoundInCurrentPage("IndustryCodeProductTable_in_addFacilityOnCampusWOPopup", productValue, 3);
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
							indusC = fnpFindRowContainsName_usingForPagingToNotThrowErrorIfNotFoundInCurrentPage("IndustryCodeProductTable_in_addFacilityOnCampusWOPopup", expectingIndustryInTableAfterRemovingNumberAndDot, 2);
							
							//indusPDetail = fnpFindRowContainsName_usingForPagingToNotThrowErrorIfNotFoundInCurrentPage("IndustryCodeProductTable", expectingProductValue, 3);
							
							String [] expectingProductValueArray=expectingProductValue.split(",");
							int temp=0;
							for(int t=0;t<expectingProductValueArray.length;t++){
								
								if(t>0){
									 temp = indusPDetail;
								}
								
								indusPDetail = fnpFindRowContainsName_usingForPagingToNotThrowErrorIfNotFoundInCurrentPage("IndustryCodeProductTable_in_addFacilityOnCampusWOPopup", expectingProductValueArray[t], 3);

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
				
				
	}
	
	
	
	
	
	
	
	
	
	
	


	public static void addingIndustryCodeAndProductDetail_usingIn_addFacilityOnCampusWOPopup(String industryCode, String products) throws Throwable {

		int rowbeforeAddingInIndustryProductTable = fnpCountRowsInTable("IndustryCodeProductTable_in_addFacilityOnCampusWOPopup");
		fnpMymsg("Before adding Industry Code --no. of rows present are -- " + rowbeforeAddingInIndustryProductTable);
		fnpClick_OR("IndustryCodeProductDetail_AddBtn_in_addFacilityOnCampusWOPopup");

	//	fnpPFList("IndustryCodePFList_in_addFacilityOnCampusWOPopup", "IndustryCodePFListOptions_in_addFacilityOnCampusWOPopup", industryCode);
		fnpMultipleSelectionFilter_dropdown_with_Ok_button("AddFacilityOnCampusWOPopup_AddIndustryCode_dropdown_triangleExpandIcon","AddFacilityOnCampusWOPopup_AddIndustryCod_Ok_link",industryCode,"AddFacilityOnCampusWOPopup_IndustryCode_AllLabels_xpath");
		
		
		
		// Thread.sleep(5000);
		fnpLoading_waitInsideDialogBox();
		// fnpType("OR", "ProductsTxtBx", (String) hashXlData.get("Products"));

		String productsDetailValue = products;
		if ((classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.ISO9001_SingleTestCaseName))) {
			// fnpType("OR", "ProductsTxtBx", productsDetailValue);

			String defaultValue = fnpGetORObjectX("ProductsTxtBx_in_addFacilityOnCampusWOPopup").getText().trim();
			if ((!defaultValue.equalsIgnoreCase("")) & (defaultValue.contains(productsDetailValue))) {
				fnpMymsg("@  - default value is same as expected, so returning back.");
			} else {
				fnpType("OR", "ProductsTxtBx_in_addFacilityOnCampusWOPopup", productsDetailValue);
			}
		} else {


			
			String combineProductDetailValue="";
			if (fnpCheckElementDisplayed("ProductsTxtBx_in_addFacilityOnCampusWOPopup", 5)) {
				String[] productDetailArray = productsDetailValue.split(":");
				for (int i = 0; i < productDetailArray.length; i++) {
					if (i!=0) {
						combineProductDetailValue=combineProductDetailValue+","+productDetailArray[i];
					} else {
						combineProductDetailValue=combineProductDetailValue+productDetailArray[i];
					}
					}
								
				fnpType("OR", "ProductsTxtBx_in_addFacilityOnCampusWOPopup", combineProductDetailValue);
			  }else{
				fnpLogicForSelectingIndustryDetailFromLeftAndTransferToRight2(productsDetailValue);
			    }
			
			
			
			
			

		}

		fnpClick_OR("AddIndustryCodeSave&Return_in_addFacilityOnCampusWOPopup");


		int rowAfterAddingInIndustryProductTable = fnpCountRowsInTable("IndustryCodeProductTable_in_addFacilityOnCampusWOPopup");
		fnpMymsg("After adding Industry Code --no. of rows present are -- " + rowAfterAddingInIndustryProductTable);


	}

	
	
	
	
	
	
	
	
	public static void fnpCallProcedure_DELETE_WO_AUTOMATION_DATA_ISRProfile(String woNo) throws Throwable {
		String rate = null;


		fnpMymsg("******************");
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			fnpMymsg("******* Oracle JDBC Driver Registered *******");
		} catch (ClassNotFoundException e) {
			fnpMymsg("****************Oracle JDBC Driver is NOT found *************");
			fnpMymsg("########################################################################");
			e.printStackTrace();
			throw new Exception(e.getMessage());
			// return;
		}

		Connection connection = null;
		try {

			connection = fnpGetDBConnection();
			fnpMymsg("Here work order no. is --'"+woNo+"'.");
			//String stringOFCallingGoldenProcedure2 = "{call ECAP.EC_PKG_AUDIT_UTIL.COPY_AUDIT_TRANSACTION_SUBMIT(?,?,?,?)}";
			String stringOFCallingProcedure = "{call ECAP.ISR_CLEANUP_PROC(?,?)}";
			CallableStatement cStmt = connection.prepareCall(stringOFCallingProcedure);

			//cStmt.setInt(1, copyFromAudit);
			cStmt.setString(1, woNo);
			cStmt.registerOutParameter(2, java.sql.Types.VARCHAR);

			// execute COPY_AUDIT_TRANSACTION store procedure
			cStmt.executeUpdate();
			String outResult = cStmt.getString(2);
			// System.out.println("Stored Procedure 2 Result : " + outResult);
			fnpMymsg("Stored Procedure  Result : " + outResult);

			connection.commit();

			//connection.close();

		} catch (SQLException e) {
			fnpMymsg("*********************************************** Either connection Failed or some different error with Database,   Getting Error >>  " + e.getMessage().trim());
			fnpMymsg("=========================================================================================================================================");
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		
		finally{
			connection.close();
		}
		fnpMymsg("******************");

		// return rate;
	}
	
	
	public void testing() throws Throwable {
		try {
			hashXlData.clear();
			fnpLoadHashData(hashXlData, ISR_suitexls, classNameText, 2, 3);
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
		

			
			try{
				driver.findElement(By.xpath(expndingLinkXpathForFinopsForSingle)).click();
				fnpMymsg("after 2");
				fnpLoading_wait();
				fnpCheckError("");

			}catch(Throwable t){
				fnpMymsg("This is not worked normal");
			}
			
			
			try{
				driver.findElement(By.xpath(".//*[@id='mainForm:tabView:accordionPanel']/div[contains(@class,'ui-accordion-header')][contains(text(),'Supporting Facility')][contains(text(),'42172')]"+"/span")).click();
				fnpMymsg("after 3");
				fnpLoading_wait();
				fnpCheckError("");

			}catch(Throwable t){
				fnpMymsg("This is not worked span");
			}
			
			try{
			fnpClick_NOR(".//*[@id='mainForm:tabView:accordionPanel']/div[contains(@class,'ui-accordion-header')][contains(text(),'Supporting Facility')][contains(text(),'42172')]");
			fnpMymsg("after 1");
			}catch(Throwable t){
				fnpMymsg("This is not worked expndingLinkXpathForFinopsForSingle");
			}
			
			
			/*			
			 String singleFinopsTableXpathHeader = expndingLinkXpathForFinopsForSingle+"/following-sibling::div[1]//table/thead[contains(@id,':finOpsAiList_head')]";
			 String singleFinopsTableXpathData = expndingLinkXpathForFinopsForSingle+"/following-sibling::div[1]//tbody[contains(@id,':finOpsAiList_data')]";

			 */
			 
			
			 String singleFinopsTableXpathHeader = expndingLinkXpathForFinopsForSingle+"/following-sibling::div[1]//thead[contains(@id,':actionItemDataTable_head')]";
			 String singleFinopsTableXpathData = expndingLinkXpathForFinopsForSingle+"/following-sibling::div[1]//tbody[contains(@id,':actionItemDataTable_data')]";


			
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

			
			
			
			
/*			String campusFinopsTableXpathHeader=expndingLinkXpathForFinopsForCampus+"/following-sibling::div[1]//thead[contains(@id,':finOpsAiList_head')]";
			String campusFinopsTableXpathData=expndingLinkXpathForFinopsForCampus+"/following-sibling::div[1]//tbody[contains(@id,':finOpsAiList_data')]";*/
			


			String campusFinopsTableXpathHeader=expndingLinkXpathForFinopsForCampus+"/following-sibling::div[1]//thead[contains(@id,':actionItemDataTable_head')]";
			String campusFinopsTableXpathData=expndingLinkXpathForFinopsForCampus+"/following-sibling::div[1]//tbody[contains(@id,':actionItemDataTable_data')]";
			
			
			
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
		//	fnpClick_OR("MoveToInProcessBtn");
			
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
			//Assert.assertTrue(changedWOStatus.equalsIgnoreCase("INPROCESS"), workOrderName+" WO status is not changed from INREVIEW to INPROCESS.");
			//fnpMymsg("Now "+workOrderName+"  WO  status has been changed from 'INREVIEW' to  INPROCESS.");
			
			
			
		
		} catch (Throwable t) {
			
			fnpCommonFinalCatchBlock(t, "  MoveToInProcess_Campus flow  is fail . ", "MoveToInProcess_CampusFail");
		
		}
	}

	
	
	
	public static void fnpSelectTechnicalReviewer() throws Throwable{
		
		fnpLoading_wait();
		if (fnpCheckElementDisplayedImmediately("EditWOBtn")) {				
			fnpClick_OR("EditWOBtn");
		}
		
		if (!(fnpCheckElementDisplayedImmediately("ISR_InfoTab_TechnicalReviewer_lookup"))) {				
			fnpClick_OR("ISRInfoTab_EditWO");
		}
		

		
		
		
		//New code on 19-11-2020 IPM-14277
		/*******************************************************/
		fnpClick_OR("ISR_InfoTab_TechnicalReviewer_lookup");
		
		fnpMymsg("Just after  click Technical Reviewer lookup");
		fnpMymsg("Just before going to insert value of Technical Reviewer ");	
		
		String technicalReviewer;
		String technicalReviewer_code;
		
/*		if (  (loginAsFullName==null) ||loginAsFullName.equalsIgnoreCase("") ) {
			technicalReviewer=(String) hashXlData.get("AccountManager").trim();
			technicalReviewer_code=(String) hashXlData.get("AccountManager_Code").trim();
			
		} else {
			technicalReviewer=loginAsFullName;
			technicalReviewer_code=loginUser_code;
		}
		
		*/
		
		
		technicalReviewer=(String) hashXlData.get("TechReviewer_fullName").trim();
		technicalReviewer_code=(String) hashXlData.get("TechReviewer_Code").trim();
		
		
		
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
		/*********************************************************************/
	}
	
	
	
	
	
	
	public static void fnpSelectTechnicalSpecialist() throws Throwable{
		
//fnpLoading_wait();
		if (fnpCheckElementDisplayedImmediately("EditWOBtn")) {				
			fnpClick_OR("EditWOBtn");
		}
		
		if (!(fnpCheckElementDisplayedImmediately("ISR_InfoTab_TechnicalSpecialist_lookup"))) {				
			fnpClick_OR("ISRInfoTab_EditWO");
		}
		
		String technicalSpecialist;
		String technicalSpecialist_code;
		
		
		technicalSpecialist=(String) hashXlData.get("Technical_Specialist_fullName").trim();
		technicalSpecialist_code=(String) hashXlData.get("Technical_Specialist_Code").trim();
		
		String alreadyTechnicalSpecialist=fnpGetORObjectX("ISR_InfoTab_TechnicalSpecialist_txtbx").getAttribute("value");
		
		String expectedValue=null;


		if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
			expectedValue=technicalSpecialist_code;
		}else{
			expectedValue=technicalSpecialist;
		}
		
		if (alreadyTechnicalSpecialist.contains(expectedValue)){
			fnpMymsg("Expected Techncial Specialist is already present there so moving forward.");
			
			
		}else{
			
			
			//New code on 19-11-2020 IPM-14277
			/*******************************************************/
			fnpClick_OR("ISR_InfoTab_TechnicalSpecialist_lookup");
			
			fnpMymsg("Just after  click Technical Specialist lookup");
			fnpMymsg("Just before going to insert value of Technical Specialist ");	
			

			
			
			
			technicalSpecialist=technicalSpecialist.trim();//(String) hashXlData.get("TechSpecialist_fullName").trim();
			technicalSpecialist_code=technicalSpecialist_code.trim();//(String) hashXlData.get("TechSpecialist_Code").trim();
			
			
			
			if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
				
				fnpSearchNSelectFirstRadioBtn(1, technicalSpecialist_code, 1);
			}else{
				fnpSearchNSelectFirstRadioBtn(2, technicalSpecialist, 1);
			}
			

			String enteredTechnicalReviewer = fnpGetORObjectX("ISR_InfoTab_TechnicalSpecialist_txtbx").getAttribute("value");
			enteredTechnicalReviewer = fnpWaitTillTextBoxDontHaveValue("ISR_InfoTab_TechnicalSpecialist_txtbx", "value");

			if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
				Assert.assertTrue(enteredTechnicalReviewer.contains(technicalSpecialist_code), "Technical Specialist is not selected properly from lookup because actual value is ---'"+enteredTechnicalReviewer+"' and expected is --'"+technicalSpecialist_code+"'.");
			}else{
				Assert.assertTrue(enteredTechnicalReviewer.contains(technicalSpecialist), "Technical Specialist  is not selected properly from lookup because actual value is ---'"+enteredTechnicalReviewer+"' and expected is --'"+technicalSpecialist+"'.");
			}
			
			fnpClick_OR("ISRSFTabSaveBtn");
			/*********************************************************************/
		}
		

	}
	
	
	
	
	
	
	public static void fnpSelectTechnicalReviewer_certAuditTask() throws Throwable{
		
		fnpLoading_wait();
		if (fnpCheckElementDisplayedImmediately("EditWOBtn")) {				
			fnpClick_OR("EditWOBtn");
		}
		
		if (!(fnpCheckElementDisplayedImmediately("ISR_InfoTab_TechnicalReviewer_lookup"))) {				
			fnpClick_OR("ISRInfoTab_EditWO");
		}
		

		
		
		
		//New code on 19-11-2020 IPM-14277
		/*******************************************************/
		fnpClick_OR("ISR_InfoTab_TechnicalReviewer_lookup");
		
		fnpMymsg("Just after  click Technical Reviewer lookup");
		fnpMymsg("Just before going to insert value of Technical Reviewer ");	
		
		String technicalReviewer;
		String technicalReviewer_code;
		
/*		if (  (loginAsFullName==null) ||loginAsFullName.equalsIgnoreCase("") ) {
			technicalReviewer=(String) hashXlData.get("AccountManager").trim();
			technicalReviewer_code=(String) hashXlData.get("AccountManager_Code").trim();
			
		} else {
			technicalReviewer=loginAsFullName;
			technicalReviewer_code=loginUser_code;
		}
		
		*/
		
		
		technicalReviewer=(String) hashXlData.get("TechReviewer_fullName").trim();
		technicalReviewer_code=(String) hashXlData.get("TechReviewer_Code").trim();
		
		
		
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
		/*********************************************************************/
	}
	
	
	
	
	public static void fnpSwitchUserToProcessTechnicalReviewAI() throws Throwable{
		String techReviewer_user=(String) hashXlData.get("TechReviewer_UserName").trim();
		fnpSwitchUser(techReviewer_user) ;
		
	}
	
	public static void fnpSearchWO_and_ReachToTaskTab_OpenCertExpandIcon_ISR() throws Throwable{
		
		fnpSearchWorkOrderOnly(workOrderNo);
		fnpClick_OR("ISRTaskTab");
		if (fnpCheckElementDisplayed("EditWOBtn", 5)) {
			fnpClick_OR("EditWOBtn");
		}
		
		
		int taskAINameIndex = fnpFindColumnIndex("TaskTabTable_HeaderRow", WOISOTaskTab_TaskAISummaryTableColName_TaskAIName);
		int certRow = fnpFindRowContainsName("TaskTabTable", WOISOTaskTab_Certification_MainTaskAIName, taskAINameIndex);
		String certInnerTableDataXpathForAI = OR.getProperty("ISRTaskInnerTableForActionItemsTableData_part1") + (certRow - 1)
				+ OR.getProperty("ISRTaskInnerTableForActionItemsTableData_part2");
		
		String certInnerTableHeaderXpathForAI = OR.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part1") + (certRow - 1)
				+ OR.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part2");
		
		
		String certOpenIconXpath = OR.getProperty("TaskTabISR_CertAudit_OpenIconPart1") + WOISOTaskType_CertificationAudit+ OR.getProperty("TaskTabISR_CertAudit_OpenIconPart2");
		if ( (fnpCheckElementDisplayedImmediately_NOR(certOpenIconXpath) & (!(fnpCheckElementDisplayedImmediately_NOR(certInnerTableHeaderXpathForAI))) )  ) {				
			fnpClick_NOR(certOpenIconXpath);
		}
		
	}
	
	
	public static void fnpSearchWO_and_ReachToTaskTab_OpenDeskExpandIcon_ISR(String taskName) throws Throwable{
		
		fnpSearchWorkOrderOnly(workOrderNo);
		fnpClick_OR("ISRTaskTab");
		if (fnpCheckElementDisplayed("EditWOBtn", 5)) {
			fnpClick_OR("EditWOBtn");
		}
		
		
		String openIconXpath = OR.getProperty("TaskTabISR_DeskAudit_OpenIconPart1") + taskName+ OR.getProperty("TaskTabISR_DeskAudit_OpenIconPart2");
		fnpClick_NOR(openIconXpath);
		
	}
	
	
	
	public static void fnpCompleteExdbUpdateAI(String deskInnerTableHeaderXpathForAI,String deskInnerTableDataXpathForAI,String taskType,String taskName,String statusToChange) throws Throwable{
		 int aiTypeInnerTableIndex = fnpFindColumnIndex_NOR(deskInnerTableHeaderXpathForAI, "AI Type");
		int  aiNumberInnerTableIndex = fnpFindColumnIndex_NOR(deskInnerTableHeaderXpathForAI, "AI Number");
		int statusInnerTableColIndex = fnpFindColumnIndex_NOR(deskInnerTableHeaderXpathForAI, "Status");
		
		
		int extDBUpdateAIRowNo = fnpFindRowContainsName_NOR(deskInnerTableDataXpathForAI, WOISOTaskTab_ExtDBUpdateAIType, aiTypeInnerTableIndex);
		
		if (extDBUpdateAIRowNo>0) {
			fnpMymsg(WOISOTaskTab_ExtDBUpdateAIType+ " action item has been generated for "+taskName+" task.");
		} else {
			msg=WOISOTaskTab_ExtDBUpdateAIType+"  action item has NOT been generated for "+taskName+" task.";
			fnpMymsg(msg);
			throw new Exception(msg);
		}
		
		String extDBUpdateAINo = fnpFetchFromTable_NOR(deskInnerTableDataXpathForAI, extDBUpdateAIRowNo, aiNumberInnerTableIndex);
		fnpMymsg("ExtDBUpdate action item no.   is ---" + extDBUpdateAINo);

		String extDBUpdateStatus = fnpFetchFromTable_NOR(deskInnerTableDataXpathForAI, extDBUpdateAIRowNo, statusInnerTableColIndex);
		
		if (!(extDBUpdateStatus.equalsIgnoreCase("AI_CREATED"))) {
			msg="Current Status of "+WOISOTaskTab_ExtDBUpdateAIType+" action item is NOT 'AI_CREATED'  b/c its current status is --"+extDBUpdateStatus;
			fnpMymsg(msg);
			throw new Exception (msg);
		} else {
			fnpMymsg("Current Status of "+WOISOTaskTab_ExtDBUpdateAIType+" action item is AI_CREATED.");
		}
		
		


		
		fnpProcessAI_ISR_TaskTab_NonConsoladatedScreen_and_basedOnAITypeCol(WOISOTaskType_DeskAudit,WOISOTaskName_DeskAudit,WOISOTaskTab_ExtDBUpdateAIType, "Completed");

		//fnpVerifyAIStatusInISR(WOISOTaskType_DeskAudit,WOISOTaskTab_Stage1DeskAudit_MainTaskAIName,WOISOTaskTab_TechnicalReviewAIName,"Completed");
		fnpVerifyAIStatusInISR_2( taskType, taskName, "AI Type", WOISOTaskTab_ExtDBUpdateAIType, statusToChange);
		
		
		
		
		
	}
	
	
	
	
	
	public static void fnpWaitForPerformAuditAIGetCompletedAutomaticallyThroughScheduler(String taskName) throws Throwable{
		
		//int statusInnerTableColIndex = fnpFindColumnIndex_NOR(InnerTableHeaderXpathForAI, "Status");
		int waitCount = 0;
		String performAuditStatus;

			
		//fnpClick_OR("Complete_Perform_Audit_AI_button");
		
/*					
		fnpRunJob("CompletePerformAuditAIQJob");
		fnpClick_OR("ISRTaskTab");
		*/
		
		
		String openIconXpath = OR.getProperty("TaskTabISR_DeskAudit_OpenIconPart1") + taskName+ OR.getProperty("TaskTabISR_DeskAudit_OpenIconPart2");
		
		if  (fnpCheckElementDisplayedImmediately_NOR(openIconXpath)  ) {				
			fnpClick_NOR(openIconXpath);
		}

		
		fnpCheckError(" after loading ");
				
		waitCount=0;
		int maxWaitTimeInMinutes=60;//25;
	

		
		
		int taskAINameIndex;
		int taskRow;
		String innerTableHeaderXpathForAI ;
		String innerTableDataXpathForAI;
		int performAuditRow;
		int aiTypeInnerTableIndex;
		int aiNumberInnerTableIndex;
		int statusInnerTableColIndex;
		
		
		
	//	String innerTableHeaderXpathForAI = OR.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part1") + (deskRow - 1)+ OR.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part2");
		
	//	int aiTypeInnerTableIndex = fnpFindColumnIndex_NOR(deskInnerTableHeaderXpathForAI, "AI Type");
	//	int aiNumberInnerTableIndex = fnpFindColumnIndex_NOR(deskInnerTableHeaderXpathForAI, "AI Number");
		
		
		
		while (true) {
			waitCount++;

			taskAINameIndex = fnpFindColumnIndex("TaskTabTable_HeaderRow", WOISOTaskTab_TaskAISummaryTableColName_TaskAIName);
			taskRow = fnpFindRowContainsName("TaskTabTable", taskName, taskAINameIndex);
			innerTableDataXpathForAI  = OR.getProperty("ISRTaskInnerTableForActionItemsTableData_part1") + (taskRow - 1)
					+ OR.getProperty("ISRTaskInnerTableForActionItemsTableData_part2");
			
			innerTableHeaderXpathForAI = OR.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part1") + (taskRow - 1)+ OR.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part2");
			aiTypeInnerTableIndex = fnpFindColumnIndex_NOR(innerTableHeaderXpathForAI, "AI Type");
			aiNumberInnerTableIndex = fnpFindColumnIndex_NOR(innerTableHeaderXpathForAI, "AI Number");
			
			 performAuditRow = fnpFindRowContainsName_NOR(innerTableDataXpathForAI, "PerformAudit", aiTypeInnerTableIndex);
			// innerTableHeaderXpathForAI = OR.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part1") + (taskRow - 1)+ OR.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part2");
			
			statusInnerTableColIndex = fnpFindColumnIndex_NOR(innerTableHeaderXpathForAI, "Status");
			
			performAuditStatus = fnpFetchFromTable_NOR(innerTableDataXpathForAI, performAuditRow, statusInnerTableColIndex);
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
				
				if ( (fnpCheckElementDisplayedImmediately_NOR(openIconXpath) & (!(fnpCheckElementDisplayedImmediately_NOR(innerTableHeaderXpathForAI))) )  ) {				
					fnpClick_NOR(openIconXpath);
				}

				
			}

			//if (waitCount > 60) {
/*						if (waitCount > 4) {

				fnpMymsg("Script waited for approx. 2 min to become 'AI_COMPLETED' state of PerformAudit but it is still showing '" + performAuditStatus + "' .");
				throw new Exception("Script waited for approx. 2 min to become 'AI_COMPLETED' state of PerformAudit but it is still showing '" + performAuditStatus + "' .");

			}*/

		}
	}
	
	
	
	
	
}
