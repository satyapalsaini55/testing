package nsf.ecap.sustainability_Suite;

import static nsf.ecap.config.IPULSE_CONSTANTS.CustomizeErrorMsgWhenLoadingNotOver;
import static nsf.ecap.config.IPULSE_CONSTANTS.WOISOTaskTab_Certification_ClientRecordChangeAIName;
import static nsf.ecap.config.IPULSE_CONSTANTS.WOISOTaskTab_Certification_MainTaskAIName;
import static nsf.ecap.config.IPULSE_CONSTANTS.WOISOTaskTab_TaskAISummaryTableColName_TaskAIName;
import static nsf.ecap.config.IPULSE_CONSTANTS.WOISOTaskTab_TaskAISummaryTableColName_TaskAINo;
import static nsf.ecap.config.IPULSE_CONSTANTS.WOISOTaskTab_TaskAISummaryTableColName_TaskAIType;
import static nsf.ecap.config.IPULSE_CONSTANTS.WOISOTaskTab_TechnicalReviewAIName;
import static nsf.ecap.config.IPULSE_CONSTANTS.WOISOTaskType_CertificationAudit;
import static nsf.ecap.config.IPULSE_CONSTANTS.WOISOTaskType_ReCertificationAudit;
import static nsf.ecap.config.IPULSE_CONSTANTS.actionItemTableStatusColName;
import static org.testng.Assert.assertTrue;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.google.common.base.Throwables;

import nsf.ecap.ISR_suite.ISO9001_Single;

import nsf.ecap.Proposals_suite.TestSuiteBase_Proposal;

import nsf.ecap.Work_Order_suite.TestSuiteBase;
import nsf.ecap.config.IPULSE_CONSTANTS;
import nsf.ecap.util.ErrorUtil;
import nsf.ecap.util.TestUtil;

public class TestSuiteBase_Sustain_Suite extends Extends_Method_of_WO{



	public static String runmodes[] = null;
	public static int count = -1;

	//public static String usingGoldenProcedureOrOasis="Oasis";//"golden";//"Oasis" or "golden" value

	public static CreateWOforSustainabilitySingle SUS_BS01;
	//public static CreateWOforSustainabilityCampus SUS_BS02;
	// check if the suite has to be skipped or not
	@BeforeSuite
	public void checkSuiteSkip() throws Throwable {

		fnInitialize();

		currentSuiteName = "SUS_suite";
		fnpMymsg("             ");
		fnpMymsg("             ");
		fnpMymsg("=========================================================================================================================================");
		fnpMymsg("####################" + currentSuiteName + " Start ############################################################");
		fnpMymsg("Checking Runmode of " + currentSuiteName);
		if (!TestUtil.isSuiteRunnable(suiteXls, currentSuiteName)) {
			fnpMymsg("Skipped " + currentSuiteName + "  as the runmode was set to NO");
			fnpMymsg("####################  " + currentSuiteName + "  End ############################################################");
			fnpMymsg("=========================================================================================================================================");
			throw new SkipException("Runmode of " + currentSuiteName + " set to no. So Skipping all tests in " + currentSuiteName);
		}

		if (TestUtil.isSuiteRunnable(suiteXls, currentSuiteName)) {
			browserName = TestUtil.getBrowserName(suiteXls, currentSuiteName);
			excelSiteURL = TestUtil.getExcelSiteURL(suiteXls, currentSuiteName);
			fnpMymsg("Browser Name is :" + browserName);
			fnpMymsg("======================");
			//currentSuiteCode = "ISR";
			currentSuiteCode = "SUS_suite";

			int rowNum = suiteXls.getCellRowNum("Test Suite", "TSID", currentSuiteName);
			suiteXls.setCellData("Test Suite", "Last_Execution", rowNum, fnReturnDateWithTimeForExcel());

			currentSuiteXls = Sustainability_suitexls;
		}

	}

	// To remove IEDriver.exe from Windows Task Manager
	// @AfterSuite(alwaysRun=true)
	@AfterSuite
	public void cleanUp() {
		try {
			referenceSuite = currentSuiteName;
			fnpMymsg("#################### Suite End ############################################################");
			driver.quit();
			IsBrowserPresentAlready = false;
			killprocess();
		} catch (Throwable t) {
			// Nothing to do
		}

	}

	/**** Common code for clicking Info tab ****/
	public static void fngCommonClickInfoTabISR() throws Throwable {

		fnpWorkAroundToClickbottomFooter();
		fnpClick_OR("ISRInfoTab_EditWO");

		// fnpLoading_wait();

		if (fnpCheckElementPresenceImmediately("EditWOBtn")) {
			fngGetORObjectX("EditWOBtn").click();
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
				String xpathRow = OR_SUS.getProperty(TableDataXpathName) + "/tr";

				int TotalRowCount = fngGetORObject_list(xpathRow, Integer.parseInt(CONFIG.getProperty("implicitWaitTime"))).size();
				int rowcountJ = 0;
				while (TotalRowCount == 0) {
					rowcountJ++;
					Thread.sleep(1000);

					TotalRowCount = fngGetORObject_list(xpathRow, Integer.parseInt(CONFIG.getProperty("implicitWaitTime"))).size();
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
						woNo = fngFetchFromTable("StandardSearchTable", i, woColNo);
						woNotdropHashMap.put((actualRow), woNo);
						// break;

					}

				}

				//System.out.println("@  ---actual row value at this moment is --" + actualRow);

				try {
					driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
					pages = driver.findElements(By.xpath(OR_SUS.getProperty("paginationPagesOfMainStandardSearchPage")));
				} catch (Throwable t) {
					pages = null;
				} finally {
					driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);
				}
				int a = pages.size();
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
		fngCommonClickInfoTabISR();

		fnpPFListModify_NOR(OR_SUS.getProperty("ISRInfoTab_WOStatusPFList"), "DROP");
		fngGetORObjectX("ProAddDocSaveBtn").click();
		fnpLoading_wait();
		fngCheckError("After trying to drop the already created work order, ");
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

			String defaultValue = fngGetORObjectX("ProductsTxtBx").getText().trim();
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
				fngLogicForSelectingIndustryDetailFromLeftAndTransferToRight2(productsDetailValue);
			    }
			
			
			
			
			

		}

		fnpClick_OR("AddIndustryCodeSave&Return");


		int rowAfterAddingInIndustryProductTable = fnpCountRowsInTable("IndustryCodeProductTable");
		fnpMymsg("After adding Industry Code --no. of rows present are -- " + rowAfterAddingInIndustryProductTable);


	}

	/*public static void addingIndustryCodeAndProductDetail(String industryCode, String products) throws Throwable {

		int rowbeforeAddingInIndustryProductTable = fnpCountRowsInTable("IndustryCodeProductTable");
		fnpMymsg("Before adding Industry Code --no. of rows present are -- " + rowbeforeAddingInIndustryProductTable);
		//fnpClick_OR("IndustryCodeProductDetail_AddBtn");
		fngClick_OR("IndustryCodeProductDetail_AddBtn");
		
		//fnpPFList("IndustryCodePFList", "IndustryCodePFListOptions", industryCode);
		//fngPFListIndustryCode("IndustryCodePFList", "IndustryCodePFListOptions", industryCode);
		fnpMultipleSelectionFilter_dropdown_with_Ok_button("AddIndustryCode_dropdown_triangleExpandIcon","AddIndustryCod_Ok_link",industryCode,"IndustryCode_AllLabels_xpath");
		// Thread.sleep(5000);
		fngLoading_waitInsideDialogBox();
		// fnpType("OR", "ProductsTxtBx", (String) hashXlData.get("Products"));

		String productsDetailValue = products;
		if ((classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.Sus_SingleTestCaseName))) {
			// fnpType("OR", "ProductsTxtBx", productsDetailValue);

			String defaultValue = fngGetORObjectX("ProductsTxtBx").getText().trim();
			if ((!defaultValue.equalsIgnoreCase("")) & (defaultValue.contains(productsDetailValue))) {
				fnpMymsg("@  - default value is same as expected, so returning back.");
			} else {
				fngType("OR", "ProductsTxtBx", productsDetailValue);
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
				fngLogicForSelectingIndustryDetailFromLeftAndTransferToRight2(productsDetailValue);
			    }
			
			
			
			
			

		}

		fngClick_OR("AddIndustryCodeSave&Return");
		//Code Start for IPM-13919 15/Oct/2020
		Thread.sleep(2000);
		fngClick_OR("FacilityStndrdInfo");
		Thread.sleep(2000);
		//Code End for IPM-13919 15/Oct/2020
		int rowAfterAddingInIndustryProductTable = fngCountRowsInTable("IndustryCodeProductTable");
		fnpMymsg("After adding Industry Code --no. of rows present are -- " + rowAfterAddingInIndustryProductTable);


	}*/

	
	public static void fnpLogicForSelectingIndustryDetailFromLeftAndTransferToRight(String productsDetailValue) throws Throwable {
		int alreadyNoOfValuesPresentInChosen = fnpFindNoOfElementsWithThisXpath("IndustryProDetailChosenCountRow_xpath");

		String AlreadychosenValuePresent;
		String AlreadyChosenValueChkBxXpath;
		boolean flagForNotRequiredValuePresent = false;
		for (int i = 1; i <= alreadyNoOfValuesPresentInChosen; i++) {
			AlreadychosenValuePresent = fnpGetORObjectX___NOR(OR_SUS.getProperty("IndustryProDetailChosenCountRow_xpath") + "[" + i + "]").getAttribute("data-item-value").trim();
			if (productsDetailValue.contains(AlreadychosenValuePresent)) {
				// nothing to do
			} else {
				//sending chosen detail from right to Left
				AlreadyChosenValueChkBxXpath = OR_SUS.getProperty("IndustryProDetailChosenPortionChkBx_UsingLiNo_part1") + i
						+ OR_SUS.getProperty("IndustryProDetailChosenPortionChkBx_UsingLiNo_part2");
				driver.findElement(By.xpath(AlreadyChosenValueChkBxXpath)).click();
				// Thread.sleep(100);
				Thread.sleep(2000);
				flagForNotRequiredValuePresent = true;
			}
		}

		if (flagForNotRequiredValuePresent) {
			Thread.sleep(5000);
			driver.findElement(By.xpath(OR_SUS.getProperty("IndustryProDetailLeftTransferIcon_Xpath"))).click();
			Thread.sleep(5000);
		}

		String[] productDetailArray = productsDetailValue.split(":");
		String availableValueChkBxXpath;
		String availableValueLabelXpath;
		boolean flagForRequiredValuePresent = false;
		for (int i = 0; i < productDetailArray.length; i++) {
			availableValueChkBxXpath = OR_SUS.getProperty("IndustryProDetailAvailablePortionChkBx_part1") + productDetailArray[i]
					+ OR_SUS.getProperty("IndustryProDetailAvailablePortionChkBx_part2");
			availableValueLabelXpath = OR_SUS.getProperty("IndustryProDetailAvailablePortionLabel_part1") + productDetailArray[i]
					+ OR_SUS.getProperty("IndustryProDetailAvailablePortionLabel_part2");

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
			driver.findElement(By.xpath(OR_SUS.getProperty("IndustryProDetailRightTransferIcon_Xpath"))).click();
			Thread.sleep(5000);
		}

	}
	
	
	
	
	public static void fngLogicForSelectingIndustryDetailFromLeftAndTransferToRight2(String productsDetailValue) throws Throwable {
		
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
			AlreadychosenValuePresent = fnpGetORObjectX___NOR(OR_SUS.getProperty("IndustryProDetailChosenCountRow_xpath") + "[" + i + "]").getAttribute("data-item-value").trim();
			if (productsDetailValue.contains(AlreadychosenValuePresent)) {
				// nothing to do
			} else {
				//sending chosen detail from right to Left
				AlreadyChosenValueChkBxXpath = OR_SUS.getProperty("IndustryProDetailChosenPortionChkBx_UsingLiNo_part1") + i
						+ OR_SUS.getProperty("IndustryProDetailChosenPortionChkBx_UsingLiNo_part2");
				driver.findElement(By.xpath(AlreadyChosenValueChkBxXpath)).click();
				// Thread.sleep(100);
				Thread.sleep(2000);
				flagForNotRequiredValuePresent = true;
			}
		}

		if (flagForNotRequiredValuePresent) {
			Thread.sleep(5000);
			driver.findElement(By.xpath(OR_SUS.getProperty("IndustryProDetailLeftTransferIcon_Xpath"))).click();
			Thread.sleep(5000);
		}

		productDetailArray = productsDetailValue.split(":");
		String availableValueChkBxXpath;
		String availableValueLabelXpath;
		boolean flagForRequiredValuePresent = false;
		for (int i = 0; i < productDetailArray.length; i++) {
			availableValueChkBxXpath = OR_SUS.getProperty("IndustryProDetailAvailablePortionChkBx_part1") + productDetailArray[i]
					+ OR_SUS.getProperty("IndustryProDetailAvailablePortionChkBx_part2");
			availableValueLabelXpath = OR_SUS.getProperty("IndustryProDetailAvailablePortionLabel_part1") + productDetailArray[i]
					+ OR_SUS.getProperty("IndustryProDetailAvailablePortionLabel_part2");

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
			driver.findElement(By.xpath(OR_SUS.getProperty("IndustryProDetailRightTransferIcon_Xpath"))).click();
			Thread.sleep(5000);
		}

		alreadyNoOfValuesPresentInChosen = fnpFindNoOfElementsWithThisXpath("IndustryProDetailChosenCountRow_xpath");
		}while(alreadyNoOfValuesPresentInChosen!=productDetailArray.length);
		
		
		
	}

	
	
	
	
	

	// Function to Drop WO ISR and delete some Data From DB from DB
	public static void dropWOSusandDeleteSomeDataFromDB(String facilityNo, String standard) throws Throwable {
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
		
			connection=fnpGetDBConnection();
			
			Statement stmt = connection.createStatement();

			String updateQuery1 = "UPDATE IQ_APPLICATIONS" + "  SET APP_STATUS = 'DROP' " + " WHERE APP_NO IN "
					+ "  (SELECT B.APP_NO FROM IQ_APP_CSX_XREF a,IQ_APPLICATIONS B WHERE CUSSX_SEQ IN (SELECT seq from CUS_STD_XREF WHERE STD_CODE = '" + standard + "' "
					+ "			AND CUS_SEQ = (SELECT SEQ FROM CUSTOMERS WHERE CODE = '" + facilityNo + "' ))" + "		AND A.APP_NO = B.APP_NO AND B.APP_STATUS <> 'DROP')";

			String deleteQuery2 = "Delete from CUS_FACILITY_SIZE where CUS_SEQ=(select seq from customers where code= '" + facilityNo + "')";

			String deleteQuery3 = "Delete from CUS_EMPLOYEE_COUNT where CUS_SEQ=(select seq from customers where code= '" + facilityNo + "')";

			String updateQuery4 = "update OA_AUDIT_PERFORM ap set AP.AUDIT_STATUS = 'CANCELLED' " + " where AP.AUDIT_NO in ("
					+ " select distinct AP.AUDIT_NO from OA_AUDIT_PERFORM ap, oa_audits a " + " where ap.audit_no = a.audit_no and AP.AUDIT_STATUS = 'CREATED'  and "
					+ " A.IQ_JOB_NO in ( select distinct job_no from IQ_APP_JOBS j, IQ_APP_CSX_XREF acsx where j.app_no = acsx.app_no "
					+ "and acsx.cussx_seq  IN (SELECT seq from CUS_STD_XREF  WHERE STD_CODE = '" + standard + "' " + " AND CUS_SEQ = (SELECT SEQ FROM CUSTOMERS WHERE CODE = '"
					+ facilityNo + "')) ) )";

			
			//IPM-7189
			//This query is for deleting contacts from PL and CO from table, otherwise drop down list will be blank after adding all in table
			String deleteQuery5 = "Delete FROM  NUCLEUS.CUS_STD_CONTACT_XREF " +
					"where CSX_SEQ=(SELECT seq FROM  NUCLEUS.CUS_STD_XREF where CUS_SEQ = (SELECT seq FROM NUCLEUS.customers where code='" + facilityNo + "') and STD_CODE ='" + standard + "')";
			
			
			
			
			
			
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
			fnpMymsg("@--Delete FROM  CUS_STD_CONTACT_XREF rows are--" + e);
			connection.commit();
			

			connection.close();

		} catch (SQLException e) {
			fnpMymsg("*********************************************** Either connection Failed or some different error with Database,   Getting Error >>  " + e.getMessage().trim());
			fnpMymsg("=========================================================================================================================================");
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		fnpMymsg("******************");

		// return rate;
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

			fngClick_OR("ISRFinaceTabAddBtn");
			fngWaitTillVisiblityOfElementNClickable_OR("ISRFinaceTabUploadSave&CloseBtn");
			fngWaitForVisible("ISRFinaceTabUploadSave&CloseBtn");

			fileName = System.getProperty("user.dir") + "\\docs\\" + fileCount[f];
			driver.findElement(By.xpath(OR.getProperty("ISRFinaceTabUploadBrowseBtn"))).sendKeys(fileName);
			Thread.sleep(1000);
			while (fnpCheckElementPresenceImmediately("BrowseCancelUploadBtn")) {
				Thread.sleep(1000);
			}

			fngWaitForVisible("ISRFinaceTabUploadDoc_TypePFList");
			fngPFList("ISRFinaceTabUploadDoc_TypePFList", "ISRFinaceTabUploadDoc_TypePFListOptions", fileTypeCount[f]);
			fngPFList("ISRFinaceTabUploadDoc_AccessPFList", "ISRFinaceTabUploadDoc_AccessPFListOptions", fileAccessCount[f]);
			fnpClickInDialog_OR("ISRFinaceTabUploadSave&CloseBtn");

			fngCheckError("Error is thrown by application while adding documents in Financial Tab");

		}
	
	
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
			
				connection=fnpGetDBConnection();
				
				Statement stmt = connection.createStatement();

				String updateQuery1 = "UPDATE IQ_APPLICATIONS" + "  SET APP_STATUS = 'DROP' " + " WHERE APP_NO IN "
						+ "  (SELECT B.APP_NO FROM IQ_APP_CSX_XREF a,IQ_APPLICATIONS B WHERE CUSSX_SEQ IN (SELECT seq from CUS_STD_XREF WHERE STD_CODE = '" + standard + "' "
						+ "			AND CUS_SEQ = (SELECT SEQ FROM CUSTOMERS WHERE CODE = '" + facilityNo + "' ))" + "		AND A.APP_NO = B.APP_NO AND B.APP_STATUS <> 'DROP')";

				String deleteQuery2 = "Delete from CUS_FACILITY_SIZE where CUS_SEQ=(select seq from customers where code= '" + facilityNo + "')";

				String deleteQuery3 = "Delete from CUS_EMPLOYEE_COUNT where CUS_SEQ=(select seq from customers where code= '" + facilityNo + "')";

				String updateQuery4 = "update OA_AUDIT_PERFORM ap set AP.AUDIT_STATUS = 'CANCELLED' " + " where AP.AUDIT_NO in ("
						+ " select distinct AP.AUDIT_NO from OA_AUDIT_PERFORM ap, oa_audits a " + " where ap.audit_no = a.audit_no and AP.AUDIT_STATUS = 'CREATED'  and "
						+ " A.IQ_JOB_NO in ( select distinct job_no from IQ_APP_JOBS j, IQ_APP_CSX_XREF acsx where j.app_no = acsx.app_no "
						+ "and acsx.cussx_seq  IN (SELECT seq from CUS_STD_XREF  WHERE STD_CODE = '" + standard + "' " + " AND CUS_SEQ = (SELECT SEQ FROM CUSTOMERS WHERE CODE = '"
						+ facilityNo + "')) ) )";

				
				//IPM-7189
				//This query is for deleting contacts from PL and CO from table, otherwise drop down list will be blank after adding all in table
				String deleteQuery5 = "Delete FROM  NUCLEUS.CUS_STD_CONTACT_XREF " +
						"where CSX_SEQ=(SELECT seq FROM  NUCLEUS.CUS_STD_XREF where CUS_SEQ = (SELECT seq FROM NUCLEUS.customers where code='" + facilityNo + "') and STD_CODE ='" + standard + "')";
				
				
				
				
				
				
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
				fnpMymsg("@--Delete FROM  CUS_STD_CONTACT_XREF rows are--" + e);
				connection.commit();
				

				connection.close();

			} catch (SQLException e) {
				fnpMymsg("*********************************************** Either connection Failed or some different error with Database,   Getting Error >>  " + e.getMessage().trim());
				fnpMymsg("=========================================================================================================================================");
				e.printStackTrace();
				throw new Exception(e.getMessage());
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
			
			
		
			String extratWOQuery = "SELECT APP_NO  FROM  iq_applications app, cus_std_xref csdx where app.std_code= csdx.std_code " +
					"and csdx.cus_seq= app.cus_seq and app.app_status != 'DROP' and " +
					"app.std_code='" +standard+"' and app.cus_seq =(SELECT seq FROM  customers where code='" +facilityNo+"')";
			
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
				fnpMymsg("Work Order for this comination standard --'"+standard+"' and facility --'"+facilityNo+"' is already dropped.");
			}
			


			connection.close();

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
			
			
		
			String extractWOQuery = "SELECT APP_NO  FROM  iq_applications app, cus_std_xref csdx where app.std_code= csdx.std_code " +
					"and csdx.cus_seq= app.cus_seq and app.app_status != 'DROP' and " +
					"app.std_code='" +standard+"' and app.cus_seq =(SELECT seq FROM  customers where code='" +facilityNo+"')";
			
			String woNo="";        
	        ResultSet rs = stmt.executeQuery(extractWOQuery);
	        while (rs.next()) {
	             woNo = rs.getString(1);
	        }
	        
	        if (!(woNo.trim().equalsIgnoreCase(""))) {
	        	

				
				// UPDATE IQ_APPLICATIONS SET APP_STATUS ='DROP', DROP_REASON = 'DRC-18' WHERE APP_NO = '"+woNo+"'" ;

				// DELETE FROM IQ_LIST_SUPPORT_SITES WHERE cussx_seq=(SELECT cussx_seq FROM  iq_app_csx_xref where app_no='W0397690');
				


				String updateQuery1 = "UPDATE IQ_APPLICATIONS SET APP_STATUS ='DROP', DROP_REASON = 'DRC-18' WHERE APP_NO = '"+woNo+"'" ;

				String deleteQuery2 = "DELETE FROM IQ_LIST_SUPPORT_SITES WHERE cussx_seq=(SELECT cussx_seq FROM  iq_app_csx_xref where app_no='"+woNo+"')" ;


				int a = stmt.executeUpdate(updateQuery1);
				fnpMymsg("@  ---updated rows  for query '"+updateQuery1+"are--" + a);
				connection.commit();

				int b = stmt.executeUpdate(deleteQuery2);
				fnpMymsg("@  ---deleted rows  for query '"+deleteQuery2+"are--" + b);
				connection.commit();
			 

				
			} else {
				fnpMymsg("Work Order for this comination standard --'"+standard+"' and facility --'"+facilityNo+"' is already dropped.");
			}
			


			connection.close();

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

			String updateQuery1 = "UPDATE IQ_APPLICATIONS" + "  SET APP_STATUS = 'DROP' " + " WHERE APP_NO IN "
					+ "  (SELECT B.APP_NO FROM IQ_APP_CSX_XREF a,IQ_APPLICATIONS B WHERE CUSSX_SEQ IN (SELECT seq from CUS_STD_XREF WHERE STD_CODE = '" + standard + "' "
					+ "			AND CUS_SEQ = (SELECT SEQ FROM CUSTOMERS WHERE CODE = '" + facilityNo + "' ))" + "		AND A.APP_NO = B.APP_NO AND B.APP_STATUS <> 'DROP')";
			
			

			
			
			
			int a = stmt.executeUpdate(updateQuery1);
			fnpMymsg("@  ---updated rows are--" + a);
			// //System.out.println("@  ---updated rows are--"+a);
			

			connection.commit();

			connection.close();

		} catch (SQLException e) {
			fnpMymsg("*********************************************** Either connection Failed or some different error with Database,   Getting Error >>  " + e.getMessage().trim());
			fnpMymsg("=========================================================================================================================================");
			e.printStackTrace();
			throw new Exception(e.getMessage());
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
	public static String fngFetchFromTable(String TableXpathKey, int row, int col) throws Exception {

		int retries = 0;
		while (true) {

			try {


				driver.manage().timeouts().implicitlyWait(1, TimeUnit.MILLISECONDS);
				String[] idString = OR_SUS.getProperty(TableXpathKey).split("'", -1);
				String CssExpression = "tbody[id='" + idString[1] + "'] tr:nth-child(" + row + ") td:nth-child(" + col + ")";

				Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(30, TimeUnit.SECONDS).pollingEvery(1, TimeUnit.SECONDS)
						.ignoring(org.openqa.selenium.NoSuchElementException.class).ignoring(org.openqa.selenium.StaleElementReferenceException.class).ignoring(org.openqa.selenium.WebDriverException.class)
						.ignoring(org.openqa.selenium.InvalidSelectorException.class);

				wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(CssExpression)));
				
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
				throw new Exception("Unable to fecth data from  Table having xpath [" + TableXpathKey + "] for row and col [" + row + "," + col
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
		WebElement auditortextboxElement = driver.findElement(By.xpath(OR_SUS.getProperty("AuditorTxtBx")));
		String enteredAuditor = auditortextboxElement.getAttribute("value");
		Assert.assertTrue(enteredAuditor.contains((String) hashXlData.get("Auditor")), "Auditor Value is not selected properly from lookup");

		fnpMymsg(" Auditor value is properly selected from  lookup");

	}

	public static void fnpCommonCodeForAuditorLookup(Hashtable<String, String> table) throws Throwable {



		fnpClick_OR("AuditorLkpBtnAtVisitPage");
		// fngGetORObjectX("AuditorLkpBtnAtVisitPage").click();
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

			// fnpCheckElementDisplayed_NOR(OR_SUS.getProperty("FirstRadioBtnInLkpAFSch"),
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

		WebElement auditortextboxElement = driver.findElement(By.xpath(OR_SUS.getProperty("AuditorTxtBx")));
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

			// fnpCheckElementDisplayed_NOR(OR_SUS.getProperty("FirstRadioBtnInLkpAFSch"),
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

		WebElement auditortextboxElement = driver.findElement(By.xpath(OR_SUS.getProperty("AuditorTxtBx")));
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

		ArrayList<String> listoldtabs = new ArrayList<String>(driver.getWindowHandles());
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
		List<WebElement> PerformAuditBtns = driver.findElements(By.xpath(OR_SUS.getProperty("Oasis_Calendar_PerformAuditBtn")));

		fnpMymsg("Total Perform Audit buttons =>" + PerformAuditBtns.size());
		

		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		int whileLoopCounterForPerformAuditBtn=0;
		while(true){
			PerformAuditBtns = driver.findElements(By.xpath(OR_SUS.getProperty("Oasis_Calendar_PerformAuditBtn")));
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

		String perfomrAuditXpath = OR_SUS.getProperty("Oasis_Calendar_PerformAuditBtn2") + " and contains(@onclick,'" + visitNo + "')]";
		fnpMymsg("@@@@   visit no is ----" + visitNo);
		List<WebElement> totalPerformAuditBtns = driver.findElements(By.xpath(perfomrAuditXpath));
		int totalCountPerformBtns= totalPerformAuditBtns.size();;


		totalPerformAuditBtns = driver.findElements(By.xpath(perfomrAuditXpath));

		fnpMymsg("Total Perform Audit buttons =>" + totalPerformAuditBtns.size());

		totalCountPerformBtns = totalPerformAuditBtns.size();
		if (totalCountPerformBtns > 0) {

			 int whileCounter = 0;
			while (whileCounter < totalCountPerformBtns) {
				if (totalPerformAuditBtns.get(whileCounter).isDisplayed() & totalPerformAuditBtns.get(whileCounter).isEnabled()) {
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

		fngGetORObjectX("Oasis_CompleteAuditFormBtn").click();
		Thread.sleep(2000);
		fnpCheckErrorUsingPageSource_Oasis();

		fnpWaitForVisible("Oasis_AuditNo_leftside");// It throws timeout
													// exception b/c button
													// not
		// visible as in oasis it is on same page and little fraction of
		// time page load something like that
		fnpWaitTillVisiblityOfElementNClickable_OR("Oasis_AuditNo_leftside");
		fnpCheckErrorUsingPageSource_Oasis();
		fngGetORObjectX("Oasis_AuditNo_leftside").click();
		Thread.sleep(2000);
		fnpCheckErrorUsingPageSource_Oasis();
		
		if ((classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.Cert_StandardsTestCaseName))) {
			 if (taskName.toLowerCase().contains("desk")) {
					java.util.List<WebElement> oasisSaveBtn = driver.findElements(By.xpath(OR_SUS.getProperty("Oasis_SaveBtn")));
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
					java.util.List<WebElement> oasisSaveBtn = driver.findElements(By.xpath(OR_SUS.getProperty("Oasis_SaveBtn")));
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
					oasisSaveBtn = driver.findElements(By.xpath(OR_SUS.getProperty("Oasis_SaveBtn")));
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
					oasisSaveBtn = driver.findElements(By.xpath(OR_SUS.getProperty("Oasis_SaveBtn")));
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
					oasisSaveBtn = driver.findElements(By.xpath(OR_SUS.getProperty("Oasis_SaveBtn")));
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
					oasisSaveBtn = driver.findElements(By.xpath(OR_SUS.getProperty("Oasis_SaveBtn")));
					oasisSaveBtn.get(0).click();
					Thread.sleep(5000);
			 }

			
			
		}else{
			
			 if((classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.ISO9001_SingleTestCaseName))){
				 
				 
				 if (taskName.toLowerCase().contains("desk")) {
						java.util.List<WebElement> oasisSaveBtn = driver.findElements(By.xpath(OR_SUS.getProperty("Oasis_SaveBtn")));
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


							java.util.List<WebElement> oasisSaveBtn = driver.findElements(By.xpath(OR_SUS.getProperty("Oasis_SaveBtn")));
							oasisSaveBtn.get(0).click();
							Thread.sleep(5000);
					
							String valueToBeSelect2 = (String) hashXlData.get("Go_to_Section_2").trim();
							fnpSimpleSelectList("Oasis_GoToSectionList", valueToBeSelect2);
						//	fnpSimpleSelectList_contains("Oasis_GoToSectionList", valueToBeSelect2);
							//fnpSimpleSelectList("Oasis_GoToSectionList", valueToBeSelect2); // commented on 20-04-2017  as in Oasis sometime 'Audit Information:Time. Audit Duration per Day' comes and sometime 'Audit Information:Time. Audit Duration per Day-01' comes
							fnpSimpleSelectList_contains("Oasis_GoToSectionList", valueToBeSelect2);
							Thread.sleep(5000);

							fnpVerifyingSubHeadingForQuestinnairesInOasis(valueToBeSelect2);
	
					
							fngGetORObjectX("Oasis_NACheckBoxes").click();
							Thread.sleep(2000);
		
							
							oasisSaveBtn = driver.findElements(By.xpath(OR_SUS.getProperty("Oasis_SaveBtn")));
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
		fngGetORObjectX("Oasis_AuditSubmitBtn").click();
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
		fngGetORObjectX("Oasis_ExitBtn").click();
		// Thread.sleep(2000);
		Thread.sleep(5000);

		driver.close();
		// driver.quit();

	}

	
	
	
	
	
	
	
	
	
	public static void fngProcessAI_ISR_TaskTab_1(String dataTableXpathNameNOR,String headerTableXpathNameNOR,String ActionItemName, String statusToChange,String saveBtnInOR) throws Throwable {
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
				fnpSearchNSelectFirstRadioBtn(2, (String) hashXlData.get("AccountManager"), 1);
				fnpClick_OR("SaveBtnOnCertDecisionAIConsolidatedScreen");
				fnpWaitForVisibleHavingMultipleElements("TopMessage3");
				String SuccessfulMsg = fnpGetText_OR("TopMessage3");
				fnpMymsg("Top Message after Reassign cert Decision AI ----" + SuccessfulMsg);
				//Start 21/oct/2020
				List<WebElement> performAI_BeforeTechnicalPanel=driver.findElements(By.xpath(".//button[@id='mainForm:performaicml']"));
				if(performAI_BeforeTechnicalPanel.size()<1){
					fngClick_OR("PerformBtn_TechnicalPanel");
				}
				//End 21/oct/2020
				fngClick_OR("PerformAIBtn");
				fnpWaitForVisibleHavingMultipleElements("TopMessage3");
				SuccessfulMsg = fnpGetText_OR("TopMessage3");
				fnpMymsg("Top Message after clicking Perform AI button ----" + SuccessfulMsg);
				fngClick_OR("MakeDecisionBtn");
				String yesRadioBtnXpath=OR_SUS.getProperty("CertDecisionCheckListRadioYesBtn_Firstpart")+"Yes"+OR_SUS.getProperty("CertDecisionCheckListRadioYesBtn_Secondpart");
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
					Thread.sleep(2000);
				}
				
				Thread.sleep(2000);
				
				//sometime this drop down not getting refreshed having Yes value, it takes time based on some upper radio buttons.
				try{
				fnpPFList("DecisionOnCertDecisionAIList", "DecisionOnCertDecisionAIListOptions", "Yes");
				}catch(Throwable t){
					fnpPFList("DecisionOnCertDecisionAIList", "DecisionOnCertDecisionAIListOptions", "Yes");
				}
				Thread.sleep(2000);
			}
			
			
			
			
			
			
			/*if (  (ActionItemName.equalsIgnoreCase("TechnicalReview")) ){
				//start 16Sept2020
				fngLoading_wait();               
				fngClick_OR("EditWOBtn");
				fngLoading_wait();
				//end 16Sept2020
				fnpWaitForVisible("ReassignAILkpBtn_OnConsolidatedScreen");
				String alreadyAssingee=fngGetORObjectX("AssignedToLabelValue_OnConsolidatedScreen").getText().trim();				
				String reassignee=null;
				if (classNameText.equalsIgnoreCase("SQF_Non_Cert")) {
					reassignee=(String) hashXlData.get("Auditor").trim();
				} else {
					reassignee=(String) hashXlData.get("AccountManager").trim();
				}			
				if (!(alreadyAssingee.equalsIgnoreCase(reassignee))) {					
					String defaultValue = fngGetORObjectX("ReassignAITxtBx_OnConsolidatedScreen").getAttribute("value").trim();
					if (defaultValue.equalsIgnoreCase(reassignee)) {
						fnpMymsg("@  - default value is same as expected, so returning back.");
					} else {					
							fnpClick_OR("ReassignAILkpBtn_OnConsolidatedScreen");
		
							fnpMymsg("Just after  click ReassignAILkpBtn");
							fnpMymsg("Just before going to insert value of Account Manger");
							fnpSearchNSelectFirstRadioBtn(2, reassignee, 1);
							fnpMymsg("Just after  inserting value of Account Manger");

							String EnteredAccountManager = fnpWaitTillTextBoxDontHaveValue("ReassignAITxtBx_OnConsolidatedScreen", "value");
							Assert.assertTrue(EnteredAccountManager.contains(reassignee), "Reassignee is not selected properly from lookup");
							fnpMymsg(" Reassignee is properly selected from client lookup");
					}
					fngGetORObjectX(saveBtnInOR).click();
					fnpLoading_wait();

				}			
			}*/
			// Below edit condition Code Committed for IPM-14471, 2Feb21  
			/*if (  (ActionItemName.equalsIgnoreCase("TechnicalReview")) ){						
				fngLoading_wait();               
				fngClick_OR("EditWOBtn");
				fngLoading_wait();
			}*/
			
			
			
			if (  (ActionItemName.equalsIgnoreCase(WOISOTaskTab_Certification_ClientRecordChangeAIName)) ){				
				
				fnpWaitForVisible("ReassignAILkpBtn");
				String alreadyAssingee=fngGetORObjectX("AssignedToLabelValue").getText().trim();				
				String reassignee=null;
				if (classNameText.equalsIgnoreCase("SQF_Non_Cert")) {
					reassignee=(String) hashXlData.get("Auditor").trim();
				} else {
					reassignee=(String) hashXlData.get("AccountManager").trim();
				}			
				if (!(alreadyAssingee.equalsIgnoreCase(reassignee))) {					
					String defaultValue = fngGetORObjectX("ReassignAITxtBx").getAttribute("value").trim();
					if (defaultValue.equalsIgnoreCase(reassignee)) {
						fnpMymsg("@  - default value is same as expected, so returning back.");
					} else {					
							fnpClick_OR("ReassignAILkpBtn");
		
							fnpMymsg("Just after  click ReassignAILkpBtn");
							fnpMymsg("Just before going to insert value of Account Manger");
							fnpSearchNSelectFirstRadioBtn(2, reassignee, 1);
							fnpMymsg("Just after  inserting value of Account Manger");

							String EnteredAccountManager = fnpWaitTillTextBoxDontHaveValue("ReassignAITxtBx", "value");
							Assert.assertTrue(EnteredAccountManager.contains(reassignee), "Reassignee is not selected properly from lookup");
							fnpMymsg(" Reassignee is properly selected from client lookup");
					}
					//fngGetORObjectX(saveBtnInOR).click();
					//fnpLoading_wait();

				}	
				
					
				}
				





			if (statusToChange.equalsIgnoreCase("completed") & ActionItemName.equalsIgnoreCase("TechnicalReview")) {
				//start 16Sept2020
				fngLoading_wait();
				fngClick_OR("StartReview_ViewActionItemPage");
				fngLoading_wait();
				/*fngClick_OR("EditButton_ViewActionItemPage");
				fngLoading_wait();*/
				fngClick_OR("ReviewCheckList_ViewActionItemPage");
				//fnpClick_OR("ReviewSpecificInformationTab_OnViewActionItemPage");
				fngLoading_wait();
				//fnpWaitForVisible("ReviewSpecificInformationTab_OnViewActionItemPage");
				Thread.sleep(1000);
				// End 16Sept2020
				
				
				//fngWaitTillVisiblityOfElementNClickable_OR("QuestionnaireOptionsLinkInTechReviewAI");   // comment  16Sept2020
				//fngClickInDialog_OR("QuestionnaireOptionsLinkInTechReviewAI");          //comment 16Sept2020
			///*TestSuiteBase_Proposal.*/fngCommonProcessQuestionnairesSet( (String) hashXlData.get("QuestionNAnswerSets").trim());
				
				
				
				fnpCommonProcessQuestionnairesSet_ISR();
				
				fngWaitForVisible("QuestionnaireDetailsTableInWOISO");
 
				//int colIndex = fngFindColumnIndex("QuestionnaireDetailsTableInWOISO_header", "Unanswered Questions"); // comment  16Sept2020
				//String unAnsweredQuestNo = fngFetchFromTable("QuestionnaireDetailsTableInWOISO", 1, colIndex);  // comment  16Sept2020
				String unAnsweredQuestNo=fngGetText_OR("UnansweredQuestions");   // 16Sept2020
				int unAnsweredQuestInt=Integer.parseInt(unAnsweredQuestNo);
				int counter = 0;
				while (unAnsweredQuestInt!=0) {
					Thread.sleep(1000);
					counter = counter + 1;
					//unAnsweredQuestNo = fngFetchFromTable("QuestionnaireDetailsTableInWOISO", 1, colIndex);  // comment  16Sept2020
					unAnsweredQuestNo=fngGetText_OR("UnansweredQuestions");  //   16Sept2020
					unAnsweredQuestInt=Integer.parseInt(unAnsweredQuestNo);
					if (counter > Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
						break;
					}
				}
				Assert.assertEquals(unAnsweredQuestInt, 0, "Unanswered Questions are not equal to '0'. ");

				fngLoading_wait();
				fngClick_OR("ApproveButton_ViewActionItemPage");   //  16Sept2020
				
				fnpLoading_wait();
				//fngPFList("ISR_AI_LeadAuditorRatingList", "ISR_AI_LeadAuditorRatingListOptions", (String) hashXlData.get("Lead_Auditor_Rating").trim());  // comment  16Sept2020
				fngPFList("LeadAuditorRatingList", "LeadAuditorRatingListOptions", (String) hashXlData.get("Lead_Auditor_Rating").trim());  //   16Sept2020
				fnpLoading_wait();
				
				if ((classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.Sus_SingleTestCaseName))) {
					//fngPFList("ISR_AI_ReviewerRecommendationList", "ISR_AI_ReviewerRecommendationListOptions", (String) hashXlData.get("Reviewer_Recommendation").trim()); // comment  16Sept2020
					fngPFList("ReviewerRecommendationList", "ReviewerRecommendationListOptions", (String) hashXlData.get("Reviewer_Recommendation").trim()); //   16Sept2020
					fngLoading_wait();    //   16Sept2020
					fngClick_OR("SaveButton_ApproveAuditPopup");   //   16Sept2020
					fnpLoading_wait();    //   16Sept2020
				}else{
					if ((classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.Cert_StandardsTestCaseName))){
					//fnpPFListByLiNo("ISR_AI_ReviewerRecommendationList", "ISR_AI_ReviewerRecommendationListOptions", 2);
					fngPFListByLiNo("ISR_AI_ReviewerRecommendationList", "ISR_AI_ReviewerRecommendationListOptions", 1);
					} else {
						//dont know till now
					}
				}
				
			}
			

			
			
			
			if (ActionItemName.equalsIgnoreCase("CertIssue")) {


				String alreadyAssingee=fngGetORObjectX("AssignedToLabelValue_OnConsolidatedScreen").getText().trim();				
				String reassignee=null;
			
				reassignee=(String) hashXlData.get("AccountManager").trim();
				
				if (!(alreadyAssingee.equalsIgnoreCase(reassignee))) {
					
					String defaultValue = fngGetORObjectX("ReassignAITxtBx_OnConsolidatedScreen").getAttribute("value").trim();
					if (defaultValue.equalsIgnoreCase(reassignee)) {
						fnpMymsg("@  - default value is same as expected, so returning back.");
					} else {
					
							fnpClick_OR("ReassignAILkpBtn_OnConsolidatedScreen");
		
							fnpMymsg("Just after  click ReassignAILkpBtn");
							fnpMymsg("Just before going to insert value of Account Manger");
							fnpSearchNSelectFirstRadioBtn(2, reassignee, 1);
							fnpMymsg("Just after  inserting value of Account Manger");

							String enteredAccountManager = fnpWaitTillTextBoxDontHaveValue("ReassignAITxtBx_OnConsolidatedScreen", "value");
							Assert.assertTrue(enteredAccountManager.contains(reassignee), "Reassignee is not selected properly from lookup");
							fnpMymsg(" Reassignee is properly selected from client lookup");
					}
					fngGetORObjectX(saveBtnInOR).click();
					fnpLoading_wait();

					}
				
				if (fnpCheckElementDisplayedImmediately("CertLanguagePFList")) {
					fnpPFList("CertLanguagePFList", "CertLanguagePFListOptions", (String) hashXlData.get("Cert_Language"));
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
				
				String ourNewlyAddedCertificateDate = fngFetchFromTable("CertificateNameTable", ourNewlyAddedTemplateRowNo, certificateDateColIndex);
				

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
					defaultValue = fngGetORObjectX("PerformDecisionAI_ChangeStatusPFList").getText().trim().toLowerCase();
				}else{
					defaultValue = fngGetORObjectX("ActionItemTab_ChangeStatusPFList").getText().trim().toLowerCase();
				}
				if (!(ActionItemName.equalsIgnoreCase(WOISOTaskTab_Certification_ClientRecordChangeAIName))) {
					
					if (ActionItemName.equalsIgnoreCase("CertDecision")) {
						fnpWaitForVisible("PerformDecisionAI_ChangeStatusPFList");
						fngPFList("PerformDecisionAI_ChangeStatusPFList", "PerformDecisionAI_ChangeStatusPFListOptions", "Completed");
					}else{
						fngWaitForVisible("ActionItemTab_ChangeStatusPFList");
						fngPFList("ActionItemTab_ChangeStatusPFList", "ActionItemTab_ChangeStatusPFListOptions", statusToChange);
					}
					if(ActionItemName.equalsIgnoreCase("CertIssue")){
						fngGetORObjectX("SaveButton__OnConsolidatedScreen_CertIssue").click();
						fnpLoading_wait();
					}else{
						fngGetORObjectX(saveBtnInOR).click();
						fnpLoading_wait();
					}
				}else{
					fngWaitForVisible("ActionItemTab_ChangeStatusPFList");
					fngPFList("ActionItemTab_ChangeStatusPFList", "ActionItemTab_ChangeStatusPFListOptions", statusToChange);
					fnpClick_OR(saveBtnInOR);
				}
			}


		//	if (!(ActionItemName.equalsIgnoreCase("CertDecision"))) {
			
					
					/************It will come always for changing from created to completed from today onwards 11-07-2017  against IPM-6594********************/
					
					if (statusToChange.equalsIgnoreCase("completed")) {
						//String defaultValue = fngGetORObjectX("ActionItemTab_ChangeStatusPFList").getText().trim().toLowerCase();
		/*				
						if ((defaultValue.contains("create")) | (defaultValue.equalsIgnoreCase(""))) {												
							fnpClickInDialog_OR("WOISR_AIConfirmationBtn");
						}
						
		*/				if((!ActionItemName.equalsIgnoreCase("TechnicalReview"))/* & (ActionItemName.equalsIgnoreCase("CertIssue"))*/){//CertDecision Condition added 16Sept2020  ghanshyam
						 	//fnpClickInDialog_OR("WOISR_AIConfirmationBtn");
							fngClickInDialog_OR("WOISR_AIConfirmationBtn");
						}
					}
		
					/***************************************************************************************/
					
		//}
			
			fnpLoading_waitInsideDialogBox();
			fnpLoading_wait();


			fngCheckError("while Going to " + statusToChange + "  the  Action item ---" + ActionItemName);

			// fnpWaitForVisible("TopMessage3");
			fnpWaitForVisibleHavingMultipleElements("TopMessage3");
			String SuccessfulMsg = fnpGetText_OR("TopMessage3");
			fnpMymsg("Top Message after " + statusToChange + "  the  Action item '" + ActionItemName + "' ----" + SuccessfulMsg);

			Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"), "Top message does not contain 'success' word, so might be action of " + statusToChange
					+ "  the  Action item '" + ActionItemName + "'  is NOT successful.");


			//for coming back from Consolidated screen
			if (( ActionItemName.equalsIgnoreCase("TechnicalReview")) | (ActionItemName.equalsIgnoreCase("CertIssue"))){
			//	fnpClick_OR("WO_BackBtn");	//PUKA-3391 new change sprint 12.1	
			//fnpClick_OR("WO_BackBtnOnViewOnlyScreenOnCompletedAI");
				
				//2Feb21
				/*if(!ActionItemName.equalsIgnoreCase("CertIssue")){//Line added 16Sept2020
					fngClick_OR("ISR_BackToViewBtn"); 
				}*/
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
			String innerTableDataXpathForAI = OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableData_part1") + (TaskRowNo - 1)
					+ OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableData_part2");
			String innerTableHeaderXpathForAI = OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part1") + (TaskRowNo - 1)
					+ OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part2");
			
		
			String openIconXpath = OR_SUS.getProperty("TaskTabISR_Task_OpenIconPart1") + task+ OR_SUS.getProperty("TaskTabISR_Task_OpenIconPart2");
			
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
				fnpSearchNSelectFirstRadioBtn(2, (String) hashXlData.get("AccountManager"), 1);
				fnpClick_OR("SaveBtnOnCertDecisionAIConsolidatedScreen");
				fnpWaitForVisibleHavingMultipleElements("TopMessage3");
				String SuccessfulMsg = fnpGetText_OR("TopMessage3");
				fnpMymsg("Top Message after Reassign cert Decision AI ----" + SuccessfulMsg);
				fnpClick_OR("PerformAIBtn");
				fnpWaitForVisibleHavingMultipleElements("TopMessage3");
				SuccessfulMsg = fnpGetText_OR("TopMessage3");
				fnpMymsg("Top Message after clicking Perform AI button ----" + SuccessfulMsg);
				fnpClick_OR("MakeDecisionBtn");
				String yesRadioBtnXpath=OR_SUS.getProperty("CertDecisionCheckListRadioYesBtn_Firstpart")+"Yes"+OR_SUS.getProperty("CertDecisionCheckListRadioYesBtn_Secondpart");
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

				String alreadyAssingee=fngGetORObjectX("AssignedToLabelValue_OnConsolidatedScreen").getText().trim();
				
				String reassignee=null;
				if (classNameText.equalsIgnoreCase("SQF_Non_Cert")) {
					reassignee=(String) hashXlData.get("Auditor").trim();
				} else {
					reassignee=(String) hashXlData.get("AccountManager").trim();
				}
				
				
				
				if (!(alreadyAssingee.equalsIgnoreCase(reassignee))) {
					
					String defaultValue = fngGetORObjectX("ReassignAITxtBx_OnConsolidatedScreen").getAttribute("value").trim();
					if (defaultValue.equalsIgnoreCase(reassignee)) {
						fnpMymsg("@  - default value is same as expected, so returning back.");
					} else {
					
							fnpClick_OR("ReassignAILkpBtn_OnConsolidatedScreen");
		
							fnpMymsg("Just after  click ReassignAILkpBtn");
							fnpMymsg("Just before going to insert value of Account Manger");
							fnpSearchNSelectFirstRadioBtn(2, reassignee, 1);
							fnpMymsg("Just after  inserting value of Account Manger");

							String EnteredAccountManager = fnpWaitTillTextBoxDontHaveValue("ReassignAITxtBx_OnConsolidatedScreen", "value");
							Assert.assertTrue(EnteredAccountManager.contains(reassignee), "Reassignee is not selected properly from lookup");
							fnpMymsg(" Reassignee is properly selected from client lookup");
					}
					fngGetORObjectX(saveBtnInOR).click();
					fnpLoading_wait();

				}
				
				
				
			}
			
			
			
			
			




			if (statusToChange.equalsIgnoreCase("completed") & ActionItemName.equalsIgnoreCase("TechnicalReview")) {
				//p
				Thread.sleep(1000);
				
				fnpWaitTillVisiblityOfElementNClickable_OR("QuestionnaireOptionsLinkInTechReviewAI");
				fnpClickInDialog_OR("QuestionnaireOptionsLinkInTechReviewAI");

				TestSuiteBase_Proposal.fnpCommonProcessQuestionnairesSet( (String) hashXlData.get("QuestionNAnswerSets").trim());

				fnpWaitForVisible("QuestionnaireDetailsTableInWOISO");

				int colIndex = fnpFindColumnIndex("QuestionnaireDetailsTableInWOISO_header", "Unanswered Questions");
				String unAnsweredQuestNo = fngFetchFromTable("QuestionnaireDetailsTableInWOISO", 1, colIndex);
				int unAnsweredQuestInt=Integer.parseInt(unAnsweredQuestNo);

				int counter = 0;

				while (unAnsweredQuestInt!=0) {
					Thread.sleep(1000);
					counter = counter + 1;
					unAnsweredQuestNo = fngFetchFromTable("QuestionnaireDetailsTableInWOISO", 1, colIndex);
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


				String alreadyAssingee=fngGetORObjectX("AssignedToLabelValue_OnConsolidatedScreen").getText().trim();				
				String reassignee=null;

				
				reassignee=(String) hashXlData.get("AccountManager").trim();
				
				if (!(alreadyAssingee.equalsIgnoreCase(reassignee))) {
					
					String defaultValue = fngGetORObjectX("ReassignAITxtBx_OnConsolidatedScreen").getAttribute("value").trim();
					if (defaultValue.equalsIgnoreCase(reassignee)) {
						fnpMymsg("@  - default value is same as expected, so returning back.");
					} else {
					
							fnpClick_OR("ReassignAILkpBtn_OnConsolidatedScreen");
		
							fnpMymsg("Just after  click ReassignAILkpBtn");
							fnpMymsg("Just before going to insert value of Account Manger");
							fnpSearchNSelectFirstRadioBtn(2, reassignee, 1);
							fnpMymsg("Just after  inserting value of Account Manger");

							String enteredAccountManager = fnpWaitTillTextBoxDontHaveValue("ReassignAITxtBx_OnConsolidatedScreen", "value");
							Assert.assertTrue(enteredAccountManager.contains(reassignee), "Reassignee is not selected properly from lookup");
							fnpMymsg(" Reassignee is properly selected from client lookup");
					}
					fngGetORObjectX(saveBtnInOR).click();
					fnpLoading_wait();

					}
				
				if (fnpCheckElementDisplayedImmediately("CertLanguagePFList")) {
					fnpPFList("CertLanguagePFList", "CertLanguagePFListOptions", (String) hashXlData.get("Cert_Language"));
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
				
				String ourNewlyAddedCertificateDate = fngFetchFromTable("CertificateNameTable", ourNewlyAddedTemplateRowNo, certificateDateColIndex);
				

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
			
			

			String defaultValue = fngGetORObjectX("ActionItemTab_ChangeStatusPFList").getText().trim().toLowerCase();
			if (ActionItemName.equalsIgnoreCase("CertDecision")) {
				fnpWaitForVisible("PerformDecisionAI_ChangeStatusPFList");
				fngPFList("PerformDecisionAI_ChangeStatusPFList", "PerformDecisionAI_ChangeStatusPFListOptions", "Completed");
			}else{
				fngWaitForVisible("ActionItemTab_ChangeStatusPFList");
				fngPFList("ActionItemTab_ChangeStatusPFList", "ActionItemTab_ChangeStatusPFListOptions", statusToChange);
			}
			

			fngGetORObjectX(saveBtnInOR).click();
			fnpLoading_wait();

			
			
			/************It will come always for changing from created to completed from today onwards 11-07-2017  against IPM-6594********************/
			
			if (statusToChange.equalsIgnoreCase("completed")) {
				//String defaultValue = fngGetORObjectX("ActionItemTab_ChangeStatusPFList").getText().trim().toLowerCase();
				/*				
				if ((defaultValue.contains("create")) | (defaultValue.equalsIgnoreCase(""))) {												
					fnpClickInDialog_OR("WOISR_AIConfirmationBtn");
				}
				
*/				
				fnpClickInDialog_OR("WOISR_AIConfirmationBtn");
			}

			/***************************************************************************************/
			
				
				
				

				
				
		//	}
			
			



			fngCheckError("while Going to " + statusToChange + "  the  Action item ---" + ActionItemName);

			fnpWaitForVisibleHavingMultipleElements("TopMessage3");
			String SuccessfulMsg = fnpGetText_OR("TopMessage3");
			fnpMymsg("Top Message after " + statusToChange + "  the  Action item '" + ActionItemName + "' ----" + SuccessfulMsg);

			Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"), "Top message does not contain 'success' word, so might be action of " + statusToChange
					+ "  the pending Action item '" + ActionItemName + "'  is NOT successful.");


			//for coming back from Consolidated screen
			if ( ActionItemName.equalsIgnoreCase("TechnicalReview")| ( ActionItemName.equalsIgnoreCase("CertIssue")  )  ) {

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
			fngCheckError(" after loading ");	
			

			
			
			int StatusColIndex = 0;
			String Status_ActionItemTable = null;
			int whileIchance = 0;
			while (true) {

				
				 taskAINameIndex = fnpFindColumnIndex("TaskTabTable_HeaderRow", WOISOTaskTab_TaskAISummaryTableColName_TaskAIName);
				 TaskRowNo = fnpFindRowContainsName("TaskTabTable", taskName, taskAINameIndex);
				 innerTableDataXpathForAI = OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableData_part1") + (TaskRowNo - 1)
						+ OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableData_part2");
				 innerTableHeaderXpathForAI = OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part1") + (TaskRowNo - 1)
						+ OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part2");
							
				  openIconXpath = OR_SUS.getProperty("TaskTabISR_Task_OpenIconPart1") + task+ OR_SUS.getProperty("TaskTabISR_Task_OpenIconPart2");
				
				
				
				
				
				
				
				
				
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

	
	
	
	
	
	public static void fnpLoginIntoOasis(String oasisURL,String oasisUserName,String oasisLoginPassword) throws Throwable{
		
		ArrayList<String> listoldtabs = new ArrayList<String>(driver.getWindowHandles());
		String originalHandle = driver.getWindowHandle();
		
		driver.get(oasisURL);
		
		

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
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_SUS.getProperty("OasisUserNameTxt"))));
*/
		
		
		
		fnpWaitTillVisiblityOfElementNClickable_OR("OasisUserNameTxt");

		fnpType("OR", "OasisUserNameTxt", oasisUserName);

		Thread.sleep(1000);

		fnpType("OR", "OasisPasswordTxt", oasisLoginPassword);

		fnpMymsg("Just before login clicked");

		fngGetORObjectX("OasisLoginBtn").click();
		Thread.sleep(5000);
		fnpMymsg("Just login(Enter button) clicked");

		fnpMymsg("Just after loading wait after login(Enter button) clicked");
		//errorMessage
		if (fnpCheckElementDisplayedImmediately("Oasis_error_inDiv")) {
			throw new Exception("Login is not successfull.");
		}
		/**
		 * Handling Alerts, Which is coming after login.
		 */
		fnpClick_OR("xpathForAck");
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
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_SUS.getProperty("OasisUserNameTxt"))));

*/		
		
		fnpWaitTillVisiblityOfElementNClickable_OR("OasisUserNameTxt");
		

		fnpType("OR", "OasisUserNameTxt", oasisUserName);

		Thread.sleep(1000);

		fnpType("OR", "OasisPasswordTxt", oasisLoginPassword);

		fnpMymsg("Just before login clicked");


		fngGetORObjectX("OasisLoginBtn").click();
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

			connection.close();

		} catch (SQLException e) {
			fnpMymsg("*********************************************** Either connection Failed or some different error with Database,   Getting Error >>  " + e.getMessage().trim());
			fnpMymsg("=========================================================================================================================================");
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		fnpMymsg("******************");

		// return rate;
	}
	
	
	
	
	

	


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void fnsUploadFile(String BrowseBttnXpath,String BrowsePopupName) throws Throwable{
		try{
			String FileUploadPath = System.getProperty("user.dir") + "\\docs\\This is test pdf document.pdf";
			WebElement Browser = driver.findElement(By.xpath(OR_SUS.getProperty(BrowseBttnXpath)));
			//WebElement Browser = fngGetORObjectX(BrowseBttnXpath);
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
			if (fngGetORObject_list("ErrorMessage", 2).size() > 0)
			{
				List<WebElement> objList = fngGetORObject_list("ErrorMessage", 1);

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
		fngCheckError(" after loading ");	
		
		
		
		
		
		

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
		
		
		
		
		
		
		
		
		if (fnpCheckElementDisplayedImmediately("EditWOBtn")) {
			fnpClick_OR("EditWOBtn");// if edit button present
		}
		
		
		if (fnpCheckElementDisplayedImmediately("TaskTableAtSnapshotTab")) {
			fnpClick_OR("ISRTaskTab");// if goes to snapshot tab
		}				
		fngCheckError(" after loading ");	
		
		
		
		
		
		

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
			 innerTableDataXpathForAI = OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableData_part1") + (TaskRowNo - 1)
						+ OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableData_part2");
			 innerTableHeaderXpathForAI = OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part1") + (TaskRowNo - 1)
						+ OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part2");						
			 openIconForExpandingXpathNOR = OR_SUS.getProperty("TaskTabISR_Task_OpenIconPart1") + taskType+ OR_SUS.getProperty("TaskTabISR_Task_OpenIconPart2");
			
			
			
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
	
	
	
	
	//it is used when two same task name present in a table
public static void fnpVerifyAIStatusInISR_basedOnTaskNo(String taskNoAtTaskTab,   String taskType, String taskName,  String ActionItemName,  String statusToChange) throws Throwable{	
	
	String taskNo=taskNoAtTaskTab;//Integer.toString(intTaskNo);
	
	if (fnpCheckElementDisplayedImmediately("EditWOBtn")) {
		fnpClick_OR("EditWOBtn");// if edit button present
	}
	
	
	if (fnpCheckElementDisplayedImmediately("TaskTableAtSnapshotTab")) {
		fnpClick_OR("ISRTaskTab");// if goes to snapshot tab
	}				
	fngCheckError(" after loading ");	
	
	
	
	
	
	

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
		 innerTableDataXpathForAI = OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableData_part1") + (TaskRowNo - 1)
					+ OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableData_part2");
		 innerTableHeaderXpathForAI = OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part1") + (TaskRowNo - 1)
					+ OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part2");						
		
		 openIconXpath = OR_SUS.getProperty("TaskTabISR_Task_OpenIcon_BasedOnTaskNo_Part1") + taskNo+ OR_SUS.getProperty("TaskTabISR_Task_OpenIcon_BasedOnTaskNo__Part2");
		
		
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
		 if((classNameText.equalsIgnoreCase("ISO9001_SingleWo_InProcess"))){
				
				driver.close();
				oldBrowser=browserName;
				browserName="firefox";
				fngLaunchBrowserAndLogin();	
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
				
				fngGetORObjectX("DocumentsTabLink_viewVistPage").click();

				
				fnpLoading_wait();
				if (fnpCheckElementDisplayedImmediately("CreateFRSReportBtn_DocumentsTab")) {
					fnpClick_OR("CreateFRSReportBtn_DocumentsTab");
				}
				
				fnpWaitForVisible("FRSOpenBtn_DocumentsTab");					
				fnpCheckFileIsDownloadedOrNotIniPulse(OR_SUS.getProperty("FRSOpenBtn_DocumentsTab")) ;
				
		 }
		
		
	}
	
	
	
	
	public void fnpVerifyFRSReportOnViewVistPage_VerifyAtEnd_inChrome(String taskOpenIconXpath,String visitNoInTasksAuditXpath) throws Throwable{
		

		 if((classNameText.equalsIgnoreCase("ISO9001_SingleWo_InProcess"))){
				

				fnpClick_OR("ISRTaskTab");
		
				if ( (fnpCheckElementDisplayedImmediately_NOR(taskOpenIconXpath) & (!fnpCheckElementDisplayedImmediately_NOR(visitNoInTasksAuditXpath)) )  ) {				
					fnpClick_NOR(taskOpenIconXpath);
				}
				
				fnpGetORObjectX___NOR(visitNoInTasksAuditXpath).click();

				
				fngGetORObjectX("DocumentsTabLink_viewVistPage").click();
				

				
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
				//fnpCheckFileIsDownloadedOrNotIniPulse(OR_SUS.getProperty("FRSOpenBtn_DocumentsTab")) ;
				//fnpVerify_File_Download_Successfully(OR_SUS.getProperty("FRSOpenBtn_DocumentsTab"));
				fnpCheckFileIsDownloadedOrNotIniPulse(OR_SUS.getProperty("FRSOpenBtn_DocumentsTab"));
				
				
				
		 }
		
		
	}
	
	
	
	

	
	public static void fngVerifyFRSReportOnViewVistPage_inChrome() throws Throwable{
		
		
		String oldBrowser;
		//classNameText="ISO9001_SingleWo_InProcess";
		 if((classNameText.equalsIgnoreCase("ISO9001_SingleWo_InProcess"))){

				fngGetORObjectX("DocumentsTabLink_viewVistPage").click();

				
				fnpLoading_wait();
				if (fngCheckElementDisplayedImmediately("CreateFRSReportBtn_DocumentsTab")) {
					fnpClick_OR("CreateFRSReportBtn_DocumentsTab");
					fnpLoading_wait();
					if (!(fngCheckElementDisplayedImmediately("FRSOpenBtn_DocumentsTab"))) {
						fnpMymsg("Here after clicking CreateFRSReportBtn_DocumentsTab, FRS Open Report button is not visible , " +
								"so going to refresh the browser, may be come after refreshing browser");
						driver.navigate().refresh();
					}
					
				}
				
				fngWaitForVisible("FRSOpenBtn_DocumentsTab");					

				fnpCheckFileIsDownloadedOrNotIniPulse(OR_SUS.getProperty("FRSOpenBtn_DocumentsTab"));
				
		 }
		
		
	}
	
	
	public static void fngCommonCodeOfFinancialTabOfSingleWO_CorporateWO() throws Throwable{

		fngCommonCodeOfFinancialTabTillInReviewForISRSCFS();
		
		 if((classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.Sus_SingleTestCaseName))){//"ISO9001_SingleWo_InProcess"		
			//Regarding query in ATA-67 on 14-12-2017 --Mail subject is -->Regarding query in ATA-67
			fngRunQueryAfterMovingWoToInReviewForBillCodes( (String) hashXlData.get("Standard"),workOrderNo);
		}
		 
		 

		// if((classNameText.equalsIgnoreCase("ISO9001_SingleWo_InProcess")))
		 if((classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.Sus_SingleTestCaseName)) | (classNameText.equalsIgnoreCase("ISO9001_Corporate_InProcess")))//"ISO9001_SingleWo_InProcess"
			 
		 {

			String FirstValueInTable = fngFetchFromTable("FinopsActionItemTable", 1, 1);
			Assert.assertFalse(FirstValueInTable.contains("No records found"), "FINOPS Action item should be generated under the Finops Action Item Table.");
			fnpMymsg(" FINOPS Action item has been  generated under the Finops Action Item Table.");

			int actionItemNameColIndex = fngFindColumnIndex("FinopsActionItemTable_HeaderRow", "Action Item Name");
			String actionItemName_FirstValue = fngFetchFromTable("FinopsActionItemTable", 1, actionItemNameColIndex);

			String[] expectedAINameArray = ((String) hashXlData.get("Finops_Action_item_name")).split(",");

			int totalRowGenerated = fngCountRowsInTable("FinopsActionItemTable");
			Assert.assertTrue(totalRowGenerated == 1, "Total Action Items generated must be 1 but here they are only '" + totalRowGenerated + "'  .");

			String actionItemName_Value = "";
			for (int p = 0; p < expectedAINameArray.length; p++) {
				for (int i1 = 1; i1 <= totalRowGenerated; i1++) {
					actionItemName_Value = fngFetchFromTable("FinopsActionItemTable", i1, actionItemNameColIndex).trim();
					if ((expectedAINameArray[p].trim()).equalsIgnoreCase(actionItemName_Value)) {
						fnpMymsg(" Action Item '" + expectedAINameArray[p] + "' is present at row no --" + (i1));
						continue;
					}

					fnpMymsg("This action item '" + expectedAINameArray[p] + "' is not present in any row  .");
					throw new Exception("This action item '" + expectedAINameArray[p] + "' is not present in any row  .");
				}
			}
			int StatusColIndex = fngFindColumnIndex("FinopsActionItemTable_HeaderRow", "Status");
			String Status_FirstValue = fngFetchFromTable("FinopsActionItemTable", 1, StatusColIndex).trim();

			String expectedStatus = "AI_CREATED";

			Assert.assertTrue(Status_FirstValue.equalsIgnoreCase(expectedStatus), "Finops action item Status is not [" + expectedStatus + "] .");

			fnpMymsg("Finops Action Items has been generated with AI_CREATED status. ");
			
		 }
	}
	
	
	public static void fngCommonCodeOfFinancialTabTillInReviewForISRSCFS() throws Throwable{
		Thread.sleep(5000);
		fngClick_OR("ISRFinancialTabLink");
		
		//since 5.1 release , it will be present in all			
/*			if ((classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.WOSQFSingleWOTillInProcessTestCaseName))) {
			fnpPFList("UpchargePFList", "UpchargePFListOptions", (String) hashXlData.get("Upcharge"));
		}
*/
		/**********code for Surveillance Cycle value 26March2k19 **********************************************************/
		fngPFList("SUSSurveillanceCycleList", "SUSSurveillanceCycleListOptions",(String) hashXlData.get("SurveillanceCycle"));
		String insertedSurveillanceValue = fngGetText_OR("SUSSurveillanceCycleList");
		Assert.assertEquals(insertedSurveillanceValue, (String) hashXlData.get("SurveillanceCycle"), " Surveillance Cycle value has not been inserted properly ");
		
		
		/**********New mandatory field introduce on 25 July 2016**********************************************************/
		fngPFList("ISRBillToOfficePFList", "ISRBillToOfficePFListOptions", (String) hashXlData.get("BillToOffice"));
		String insertedBillToOffice = fngGetText_OR("ISRBillToOfficePFList");
		Assert.assertEquals(insertedBillToOffice, (String) hashXlData.get("BillToOffice"), "Bill To Office value has not been inserted properly ");
		
		
		/*************************Commenting agian on 17-05-2017 as from today onwards it will be coming in all wo type  IPM-5707********************/
		
/*		//not present in this release , but it will come again in next sprint in june 2017	, for timebeing it started coming only in SQF
		//fnpPFList("UpchargePFList", "UpchargePFListOptions", (String) hashXlData.get("Upcharge"));
		if ((classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.WOSQFSingleWOTillInProcessTestCaseName))) {
		fnpPFList("UpchargePFList", "UpchargePFListOptions", (String) hashXlData.get("Upcharge"));
		}
		*/
		/********************************************************************************************************************************************/
		
		fngPFList("UpchargePFList", "UpchargePFListOptions", (String) hashXlData.get("Upcharge"));
		//23/Nov/2021 Start Code 
		Thread.sleep(3000);
		fngPFList("InvoiceCurrencyList", "InvoiceCurrencyListOptions", (String) hashXlData.get("InvoiceCurrency"));
		Thread.sleep(3000);
		//23/Nov/2021 end Code
		fngClick_OR("SUSSFTabSaveBtn");
		
		fngFileUploadInSus();
	
		
		fngMoveToInReviewByClickingButtonAndVerifyInReview(); //use here code to go on info tab
	}
	
	/*public void gotoTabs(String tabName) throws Exception{
		fnpMymsg("EditWorkOrderPage: gotoTabs() : Control is on "+tabName);
		scrollOperation();
		if(tabName.equalsIgnoreCase(FINANCIALTAB)){
			assertTrue(isElementAvailableByXpath(OR_Constants.FINANCIAL_TAB),"Either Financial Tab is not available or Xpath has been changed.");
			financialTabLink.click();
			ExpectedCondition.waitForLoadImage(OR_Constants.LOAD_IMG_FOR_CLIENTLOOKUP);
		}else if (tabName.equalsIgnoreCase(INFOTAB)) {
			assertTrue(isElementAvailableByXpath(OR_Constants.INFO_TAB),"Either Info Tab is not available or Xpath has been changed.");
			infoTab.click();
			ExpectedCondition.waitForLoadImage(OR_Constants.LOAD_IMG_FOR_CLIENTLOOKUP);
			Thread.sleep(3000);
		}else if(tabName.equalsIgnoreCase(TASKSTAB)){
			assertTrue(isElementAvailableByXpath(OR_Constants.TASKS_TAB),"Either Tasks Tab is not available or Xpath has been changed.");
			tasksTab.click();
			ExpectedCondition.waitForLoadImage(OR_Constants.LOAD_IMG_FOR_CLIENTLOOKUP);
			ExpectedCondition.waitForVisibilityOfElement(addTaskButton);
			Thread.sleep(3000);
		}
	}*/
	/**
	 * For scrolling!!
	 */
	/*private void scrollOperation(){
		fnpMymsg("EditWorkOrderPage: scrollOperation() : scrolling to the particular position.");
		JavascriptExecutor script=((JavascriptExecutor) driver);
		script.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath(".//div[@class='ui-tabs-navscroller']/ul/li[6]/a[@href='#mainForm:tabView:irsfnclsapptab']"))financialTabLink);
	}*/
	public static void fngFileUploadInSus() throws Throwable{
		
		//Added by satya 21.01.2222
		boolean IntegratedWOComing = false;
		fngClick_OR("ISRIntegratedStandardsTabLink");
		
		fngWaitForVisible("ISRIntegratedStandardsTab_Isthisworkorderpartofintegratedstandardsdelivery_Text");
		
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		if( !(driver.findElements(By.xpath(OR_SUS.getProperty("ISRIntegratedStandardsTab_Integratedstandardsdelivery_No_RadioBttnSelected"))).size()>0) ){
			IntegratedWOComing=true;
		}
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(CONFIG.getProperty("genMax_waitTime")), TimeUnit.SECONDS);
		
		fngClick_OR("ISRFinancialTabLink");
		
		
		
		
		
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

			fngClick_OR("ISRFinaceTabAddBtn");
			fngWaitTillVisiblityOfElementNClickable_OR("ISRFinaceTabUploadSave&CloseBtn");
			fngWaitForVisible("ISRFinaceTabUploadSave&CloseBtn");

			fileName = System.getProperty("user.dir") + "\\docs\\" + fileCount[f];
			driver.findElement(By.xpath(OR_SUS.getProperty("ISRFinaceTabUploadBrowseBtn"))).sendKeys(fileName);
			Thread.sleep(1000);
			while (fnpCheckElementPresenceImmediately("BrowseCancelUploadBtn")) {
				Thread.sleep(1000);
			}

			fngWaitForVisible("ISRFinaceTabUploadDoc_TypePFList");
			fngPFList("ISRFinaceTabUploadDoc_TypePFList", "ISRFinaceTabUploadDoc_TypePFListOptions", fileTypeCount[f]);
			fngPFList("ISRFinaceTabUploadDoc_AccessPFList", "ISRFinaceTabUploadDoc_AccessPFListOptions", fileAccessCount[f]);
			
			
			//new changes -- IPM-15605
			if(fileTypeCount[f].equalsIgnoreCase(" Application")) {
				fngClickInDialog_OR("ISRFinaceTabUploadSave&CloseBtn");
			   break;
			}else {
				fngClickInDialog_OR("ISRFinaceTabUploadSave&CloseBtn");
			}
			
			if(IntegratedWOComing){
				fngWaitTillVisiblityOfElementNClickable_OR("SusFinanceTabUploadConfBoxNobtn");
				fngWaitForVisible("SusFinanceTabUploadConfBoxNobtn");
				fngClick_OR("SusFinanceTabUploadConfBoxNobtn");
			}
			fngCheckError("Error is thrown by application while adding documents in Financial Tab");

		}
	
	
	}
	
	
	public static void fngMoveToInReviewByClickingButtonAndVerifyInReview() throws Throwable{
		String textMessage = "";
		fngClick_OR("ISRMoveToInReviewBtn");

		fngWaitTillClickable("NewlyCrWOTopBannerInfo");
		fngGetORObjectX("NewlyCrWOTopBannerInfo").click();


		fngWaitForVisible("TopBannerWOStatus");
		String changedWOStatus = fngGetORObjectX("TopBannerWOStatus").getText();
		Assert.assertTrue(changedWOStatus.equalsIgnoreCase("INREVIEW"), " WO status is not changed from DRAFT to INREVIEW.");
		fnpMymsg("Now  WO status has been changed from 'DRAFT' to  INREVIEW.");
	}
	
	





	public static void callRemoveAlreadyAddedIndustryCodeUsingScript() throws Throwable{
		
		//String deleteXpathForIndustryCode=".//*[@id='mainForm:tabView:dtcusindprd:0:delmptask']";
		String deleteXpathForIndustryCode=".//a[contains(@id,':dtcusindprd:')][contains(@id,':delmptask')]";
		//String deleteXpathForConfirmationBtn=".//*[@id='mainForm:tabView:deltaskyesbtn']";
		String deleteXpathForConfirmationBtn=".//*[@id='mainForm:tabView:facStdInfoAccordPnl:deltaskyesbtn']";
		
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
				fnpMymsg("  --Going to delete this Industry code --"+fnpFetchFromTable_NOR(OR_SUS.getProperty("IndustryCodeProductTable"),1,2));
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
		
		fngLaunchBrowserAndLogin();
		
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
		while (s.contains("No records found") & j < 15) {
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
			dropWOSusandDeleteSomeDataFromDB(facility, standard);

			
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


		//ISR_BS01.CreateWOFlow(); //Sus_BS01
		SUS_BS01.CreateWOFlow(); //Ghanshyam
		//SUS_BS02.CreateWOFlow();
		

		String workOrderNo_text = fngGetORObjectX("NewlyCreatedWorkOrderNo").getText();
		newlyCreatedWorkOrderNo = ((workOrderNo_text.split(" "))[0]).trim();
		fnpMymsg(" Newly Work Order No is:" + newlyCreatedWorkOrderNo);
		
		//ISR_BS01.Standard_Facility_Tab();//Sus_BS01
		SUS_BS01.Standard_Facility_Tab();
		//SUS_BS02.Standard_Facility_Tab();
		
		fngCommonCodeOfFinancialTabOfSingleWO_CorporateWO();
		
		fnpProcessAI_ISR("WOISR_FinopActionItemTable", "WOISR_FinopActionItemTable_HeaderRow", (String) hashXlData.get("Finops_Action_item_name"),
				"Inprocess", "WOISR_FinopActionItemSaveNCloseBtn",(String) hashXlData.get("Finops_ReassignedTo"));
		fnpProcessAI_ISR("WOISR_FinopActionItemTable", "WOISR_FinopActionItemTable_HeaderRow", (String) hashXlData.get("Finops_Action_item_name"), 
				"Completed", "WOISR_FinopActionItemSaveNCloseBtn",(String) hashXlData.get("Finops_ReassignedTo"));
		
		
		fngWaitTillClickable("NewlyCrWOTopBannerInfo");
		fngGetORObjectX("NewlyCrWOTopBannerInfo").click();

		fnpWaitForVisible("TopBannerWOStatus");
		String changedWOStatus = fngGetORObjectX("TopBannerWOStatus").getText();
		Assert.assertTrue(changedWOStatus.equalsIgnoreCase("INPROCESS"), workOrderName+" WO status is not changed from INREVIEW to INPROCESS.");
		fnpMymsg("Now "+workOrderName+"  WO  status has been changed from 'INREVIEW' to  INPROCESS.");
		
		return newlyCreatedWorkOrderNo;
	}
	
	
	
	public static String fnpCreateANewSingleWOTillStatus(String status) throws Throwable{

		String newlyCreatedWorkOrderNo="";
		
		String workOrderName = "Single";


		//ISR_BS01.CreateWOFlow();
		createSusWO_excludingDBDropQuery();
		

		String workOrderNo_text = fngGetORObjectX("NewlyCreatedWorkOrderNo").getText();
		newlyCreatedWorkOrderNo = ((workOrderNo_text.split(" "))[0]).trim();
		fnpMymsg(" Newly Work Order No is:" + newlyCreatedWorkOrderNo);
		
		if (!(status.equalsIgnoreCase("DRAFT"))) {
			//ISR_BS01.Standard_Facility_Tab();
			SUS_BS01.Standard_Facility_Tab();
			
			fngCommonCodeOfFinancialTabOfSingleWO_CorporateWO();
			
			if (!(status.equalsIgnoreCase("INREVIEW")))     {
				
				fnpProcessAI_ISR("WOISR_FinopActionItemTable", "WOISR_FinopActionItemTable_HeaderRow", (String) hashXlData.get("Finops_Action_item_name"),
						"Inprocess", "WOISR_FinopActionItemSaveNCloseBtn",(String) hashXlData.get("Finops_ReassignedTo"));
				fnpProcessAI_ISR("WOISR_FinopActionItemTable", "WOISR_FinopActionItemTable_HeaderRow", (String) hashXlData.get("Finops_Action_item_name"), 
						"Completed", "WOISR_FinopActionItemSaveNCloseBtn",(String) hashXlData.get("Finops_ReassignedTo"));
				
				
				fngWaitTillClickable("NewlyCrWOTopBannerInfo");
				fngGetORObjectX("NewlyCrWOTopBannerInfo").click();

				fnpWaitForVisible("TopBannerWOStatus");
				String changedWOStatus = fngGetORObjectX("TopBannerWOStatus").getText();
				Assert.assertTrue(changedWOStatus.equalsIgnoreCase("INPROCESS"), workOrderName+" WO status is not changed from INREVIEW to INPROCESS.");
				fnpMymsg("Now "+workOrderName+"  WO  status has been changed from 'INREVIEW' to  INPROCESS.");
			}
			
			if (!(status.equalsIgnoreCase("INPROCESS")))     {
						// to proceed this work order to complete status
			}
		}

		

		
		return newlyCreatedWorkOrderNo;
	}
	
	
	
	
	


	public static boolean callVerifySingleWOPresentOnThisStandardFacilityOtherwiseCreate(String facility, String standard,String []statusToCheck) throws Throwable{
		
		boolean needToCreateSingleWO=false;
		
		fngLaunchBrowserAndLogin();
		
		fnpCommonClickTopMainMenu("Menu", "FirstRefrenceLinkJustBelowToMenu", "SearchWorkOrderLink", "WorkOrderNoField");

		// Thread.sleep(2000);
		fnpMymsg("Just before waiting for clickable workorderfield");
		fnpWaitTillVisiblityOfElementNClickable_OR("WorkOrderNoField");
		fnpWaitTillVisiblityOfElementNClickable_OR("MainSearchButton");
		
		
		fnpMultipleSelectDropDown2("WorkOrderTypeMultipleSelectDropDown", "Single");
		
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
		while (s.contains("No records found") & j < 15) {
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
			while (s.contains("No records found") & j < 15) {
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
			int statusColNo=fnpFindColumnIndex("StandardSearchTableHeader", "WO Status");
			int workOrderColNo=fnpFindColumnIndex("StandardSearchTableHeader", "WO #");
			for (int i = 1; i < totalRows+1; i++) {
				temp=fnpFetchFromStSearchTable(i, statusColNo);
				if ((temp.trim().equalsIgnoreCase("DROP"))) {
					//nothing to do
				} else {
					boolean tempFoundFlag=false;
					for (int k = 0; k < statuses.length; k++) {
						if (temp.trim().equalsIgnoreCase(statuses[k])) {
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
			dropWOSusandDeleteSomeDataFromDB(facility, standard);
			
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

	
	
	
	
	public static void createSusWO_excludingDBDropQuery() throws Throwable{

			
			fngCommonLoginPart(classNameText);


			// -------First Create Work Order Page ----------------------------

			fngWaitTillClickable("ClientLKPBtn");
			fngClick_OR("ClientLKPBtn");

			String[][] lookup;
			lookup = lookup_criteria_processing((String) hashXlData.get("Client"));
			fngSearchNSelectFirstRadioBtn(lookup, 1);

			//String clientNameExpected = lookup[3][2];			
			String clientNameExpected="";
			for (int i = 0; i < lookup.length; i++) {
				if(lookup[i][0].equalsIgnoreCase("Client #") | lookup[i][0].equalsIgnoreCase("Client Name")){
					clientNameExpected=lookup[i][2];
					break;
				}
			}
			
			

			String EnteredClient = fngWaitTillTextBoxDontHaveValue("ClientTxtBox", "value");
			Assert.assertTrue(EnteredClient.contains(clientNameExpected), "Client Value is not selected properly from lookup as expected is --'" + clientNameExpected
					+ "' but actual is --'" + EnteredClient + "'.");
			fnpMymsg(" Client value is properly selected from client lookup");

			
			
/*			if ( (classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.ISO9001_CorporateTestCaseName))
					| (classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.ISO9001_CampusTestCaseName))
					) {
			fnpPFList("ISR_ORGUnitList", "ISR_ORGUnitListOptions", "ISR");
			}
			*/
			
			
			if (currentSuiteName.equalsIgnoreCase("SUS_suite")) {
				if (fngCheckElementDisplayed("ISR_ORGUnitList", 5)) {
					
					fngGetORObjectX("ISR_ORGUnitList").click();
					//int l=fnpCountChildElementPresenceImmediately_NotInOR(fngGetORObjectX("ISR_ORGUnitListOptions"), "li");

					List<WebElement> objectlist5 = fngGetORObjectX("ISR_ORGUnitListOptions").findElements(By.tagName("li"));
					int size = objectlist5.size();
					int validValues=0;
					String temp=null;
					for (int i = 0; i < size; i++) {
						temp=objectlist5.get(i).getText();
						if ((temp.contains("select")) | (temp.contains("Select"))) {
							//nothing for now
						}else{
							validValues++;
						}					
					}
					
					if (validValues<2) {
						Thread.sleep(2000);
						throw new Exception("This Org Unit drop down should be visible only in that case when there are more than 1 value for this field and here total values in this are --"+validValues);					
					} 
					fngGetORObjectX("ISR_ORGUnitList").click();				
					fnpPFList("ISR_ORGUnitList", "ISR_ORGUnitListOptions", "ISR");
				}
				
			}
			

			
			
			
			fngClick_OR("StandardLkpBtn");

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
				fngSearchNSelectFirstRadioBtn(1, (String) hashXlData.get("Standard"), 1);
			}
			
			
			
			
			


			fnpPFListExactly("WOTypeList", "WOTypeListOptions", (String) hashXlData.get("WOType"));

			fnpClick_OR("NextBtn_id");

			fnpCheckErrMsg("Error thrown by applciation After Click Next Button  ");
			// -------Second Create Work Order Page ----------------------------

			fnpMymsg("Just before going to click AccountManagerLkpBtn");

			fnpWaitForVisible("AccountManagerTxtBox");
			String AlreadyAccountManager = fngGetORObjectX("AccountManagerTxtBox").getAttribute("value");
			
			

			if (AlreadyAccountManager.contains((String) hashXlData.get("AccountManager").trim())) {
				// nothing to do now

			} else {
				fnpClick_OR("AccountManagerLkpBtn");
				
				fnpMymsg("Just after  click AccountManagerLkpBtn");
				fnpMymsg("Just before going to insert value of Account Manger");
				//For Condition use for Masking user 10Jan2k19
				boolean masking="true".equals(OR_SUS.getProperty("ClientName_Masking"));
				if(masking){
					fnpSearchNSelectFirstRadioBtn(1, (String) hashXlData.get("Masking_EmpCode"), 1);
					fnpMymsg("Just after  inserting value of Account Manger");
					String EnteredEmployeeCode = fngWaitTillTextBoxDontHaveValue("AccountManagerTxtBox", "value");
					Assert.assertTrue(EnteredEmployeeCode.contains((String) hashXlData.get("Masking_EmpCode")), "Employee Code is not selected properly from lookup");
					fnpMymsg(" Employee Code is properly selected from client lookup");
				}else{
					fnpSearchNSelectFirstRadioBtn(2, (String) hashXlData.get("AccountManager"), 1);
					fnpMymsg("Just after  inserting value of Account Manger");
					String EnteredAccountManager = fngGetORObjectX("AccountManagerTxtBox").getAttribute("value");
					EnteredAccountManager = fngWaitTillTextBoxDontHaveValue("AccountManagerTxtBox", "value");
					Assert.assertTrue(EnteredAccountManager.contains((String) hashXlData.get("AccountManager")), "Account Manager is not selected properly from lookup");
					fnpMymsg(" Account Manager is properly selected from client lookup");
				}
					
			}

			
			
			
			
			//doing changes for BS-04 script where account manager not to be selected for Associate wo
			
			/*
			 String expectedAccountManager=(String) hashXlData.get("AccountManager").trim();
			
			if (!(expectedAccountManager.equalsIgnoreCase(""))) {
				if (AlreadyAccountManager.contains((String) hashXlData.get("AccountManager").trim())) {
					// nothing to do now

				} else {
					fnpClick_OR("AccountManagerLkpBtn");

					fnpMymsg("Just after  click AccountManagerLkpBtn");
					fnpMymsg("Just before going to insert value of Account Manger");
					fnpSearchNSelectFirstRadioBtn(2, (String) hashXlData.get("AccountManager"), 1);
					fnpMymsg("Just after  inserting value of Account Manger");
					String EnteredAccountManager = fngGetORObjectX("AccountManagerTxtBox").getAttribute("value");
					EnteredAccountManager = fnpWaitTillTextBoxDontHaveValue("AccountManagerTxtBox", "value");
					Assert.assertTrue(EnteredAccountManager.contains((String) hashXlData.get("AccountManager")), "Account Manager is not selected properly from lookup");
					fnpMymsg(" Account Manager is properly selected from client lookup");
				}

			}*/
			

			if (   ((String) hashXlData.get("WOType").trim()).equalsIgnoreCase("Associated Facility")) {
				String completeXpathForWorkOrdertoassociatewith=OR_SUS.getProperty("WorkOrdertoassociatewith_part1")+(String) hashXlData.get("FirstWoNo")+OR_SUS.getProperty("WorkOrdertoassociatewith_part2");
				fnpGetORObjectX___NOR(completeXpathForWorkOrdertoassociatewith).click();
				Thread.sleep(3000);
			}
			
/*			
			*//*** Some time after selecting the value in WO Prime Contact, and entered the value in summary,
			 *  value in this drop down gets clear and also there is no loading icon and might be this drop down gets refreshed, 
			 *  so giving here hard code weight
			 *//*
			Thread.sleep(5000);
			
			
			*/
			
			
			
		//	fnpPFList("WoPrimContactList", "WoPrimContactListOptions", (String) hashXlData.get("WOPrimaryContact"));
		//	fnpPFListByLiNo("WoPrimContactList", "WoPrimContactListOptions", 2);
			fnpPFListByLiNo("WoPrimContactList", "WoPrimContactListOptions", 1);
			
			Thread.sleep(1000);

			fngGetORObjectX("WOSummaryTxtBox").sendKeys((String) hashXlData.get("WOSummary") + fnTimestampDateWithTime());


			String IsThisTransferToNSFRadioBtnXpath = OR_SUS.getProperty("IsThisTransferToNSFRadioBtn_part1") + ((String) hashXlData.get("IsThisTransferToNSFRadioBtn"))
					+ OR_SUS.getProperty("IsThisTransferToNSFRadioBtn_part2");
			fnpGetORObjectX___NOR(IsThisTransferToNSFRadioBtnXpath).click();
			Thread.sleep(1000);


			
			if ((classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.Cert_StandardsTestCaseName))) {
				String IsthisforaspecifyingClientRadioBtn = OR_SUS.getProperty("IsthisforaspecifyingClientRadioBtn_part1")
						+ ((String) hashXlData.get("IsthisforaspecifyingClientRadioBtn")) + OR_SUS.getProperty("IsthisforaspecifyingClientRadioBtn_part2");
				fnpGetORObjectX___NOR(IsthisforaspecifyingClientRadioBtn).click();
				Thread.sleep(1000);
			}
			
			
			
			
			
			if ((classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.Non_Cert_StandardsTestCaseName))) {
				
				String IsthisforaspecifyingClientRadioBtn = OR_SUS.getProperty("IsthisforaspecifyingClientRadioBtn_part1")
						+ ((String) hashXlData.get("Is_Specifying_Client")) + OR_SUS.getProperty("IsthisforaspecifyingClientRadioBtn_part2");
				fnpGetORObjectX___NOR(IsthisforaspecifyingClientRadioBtn).click();
				Thread.sleep(1000);
				if (hashXlData.get("Is_Specifying_Client").equalsIgnoreCase("Yes")) {
					Thread.sleep(1000); // ADDED by satya
					fnpClick_OR("ChooseClientLkpBtn");
					String Specified_Client_Text = hashXlData.get("Specified_Client");
					fnpSearchNSelectFirstRadioBtn(1, Specified_Client_Text, 1);								
				}
		
			}
			
			
			
			
			
			
			fngGetORObjectX("QuoteReceiveDateBtn").click();
			Thread.sleep(2000);

			String p = fngGetORObjectX("QuoteReceiveTodayDate").getText();
			//System.out.println("@  ---today date is --" + p);
			fnpMymsg("@  ---today date is --" + p);



			/********* Using date Picker calender *********************************/
			if (Integer.parseInt(p) == 1) {
				
			//	driver.findElement(By.xpath(".//div[@id='ui-datepicker-div']//span[@class='ui-icon ui-icon-circle-triangle-w'][text()='Prev']")).click();
				fngGetORObjectX("CalendarPrevIcon").click();
				
				Thread.sleep(1000);

				
				
				
				//List<WebElement> totaldays = driver.findElements(By.xpath(".//div[@id='ui-datepicker-div']//td/a"));
				//List<WebElement> totaldays = driver.findElements(By.xpath(OR_SUS.getProperty("CalendarAllDatesLink")));
				List<WebElement> totaldays =fngGetORObject_list("CalendarAllDatesLink", 2);
				totaldays.get((totaldays.size()) - 1).click();
				Thread.sleep(1000);

			} else {
				//String yesterdayXpath = ".//div[@id='ui-datepicker-div']//td/a[text()='" + ((Integer.parseInt(p)) - 1) + "']";
				String yesterdayXpath = OR_SUS.getProperty("CalendarAllDatesLink")+"[text()='" + ((Integer.parseInt(p)) - 1) + "']";
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
			 * fngGetORObjectX("QuoteReceiveDateTxtBx").clear(); Date todayDate
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


			String RushRadioBtnXpath = OR_SUS.getProperty("RushRadioBtn_part1") + ((String) hashXlData.get("RushRadioBtn")) + OR_SUS.getProperty("RushRadioBtn_part2");
			fnpGetORObjectX___NOR(RushRadioBtnXpath).click();
			Thread.sleep(1000);


			String insertedCertTargetDate;

			if ((classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.Sus_SingleTestCaseName))
					/*| (classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.ISO9001_CorporateTestCaseName))*/
					| (classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.Sus_CampusTestCaseName))
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



			String preassessmentRadioBtnXpath = OR_SUS.getProperty("PreassessmentRadioBtn_part1") + ((String) hashXlData.get("PreassessmentRadioBtn"))
					+ OR_SUS.getProperty("PreassessmentRadioBtn_part2");
			fnpGetORObjectX___NOR(preassessmentRadioBtnXpath).click();
			Thread.sleep(1000);

			// SurveillanceCycle field has been removed 28/Feb/2019 by Ghanshyam due to 10037
			
			/*if ((classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.Sus_SingleTestCaseName))
					| (classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.ISO9001_CorporateTestCaseName))
					| (classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.Sus_CampusTestCaseName))
					) {
				fnpPFList("SurveillanceCyclePFList", "SurveillanceCyclePFListOptions", (String) hashXlData.get("SurveillanceCycle"));

			}
*/
			fnpClick_OR("CreateWOBtn");

			fngCheckError(" while creating new WO");


			String workOrderNo_text = fnpGetText_OR("NewlyCreatedWorkOrderNo");
			workOrderNo = ((workOrderNo_text.split(" "))[0]).trim();
			fnpMymsg("Newly created WO no. is:" + workOrderNo);
	}


	
	//ex: WOISOTaskType =WOISOTaskType_DeskAudit
	
	public void fnpRecordChangeAI(String mainTaskName,String WOISOTaskType) throws Throwable{
		
		int taskAINameIndex = fnpFindColumnIndex("TaskTabTable_HeaderRow", WOISOTaskTab_TaskAISummaryTableColName_TaskAIName);
		int taskRowNo = fnpFindRowContainsName("TaskTabTable", mainTaskName, taskAINameIndex);
		//certRow=TaskRowNo;
		
		
		String taskOpenIconXpath = OR_SUS.getProperty("TaskTabISR_Task_OpenIconPart1") + WOISOTaskType+ OR_SUS.getProperty("TaskTabISR_Task_OpenIconPart2");
		
		
		if(  fnpCheckElementDisplayedImmediately_NOR(taskOpenIconXpath)   ) {				
			fnpClick_NOR(taskOpenIconXpath);
		}
		
		
		
		String taskInnerTableHeaderXpathForAI = OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part1") + (taskRowNo - 1)
				+ OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part2");

		String taskInnerTableDataXpathForAI = OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableData_part1") + (taskRowNo - 1)
				+ OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableData_part2");
		
		String taskExpansionPanelXpath = OR_SUS.getProperty("ISRTaskExpansionPanelGrid_part1") + (taskRowNo - 1) + OR_SUS.getProperty("ISRTaskExpansionPanelGrid_part2");

		
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

			fngRunJob("CreateClientRecordChangeAIQJob");
			
			
			
			
			
			fnpClick_OR("ISRTaskTab");
			taskOpenIconXpath = OR_SUS.getProperty("TaskTabISR_Task_OpenIconPart1") + WOISOTaskType+ OR_SUS.getProperty("TaskTabISR_Task_OpenIconPart2");
			taskRowNo = fnpFindRowContainsName("TaskTabTable", mainTaskName, taskAINameIndex);
			String visitNoInGivenTaskXpath = OR_SUS.getProperty("VisitNoAtTaskTab_part1") + (taskRowNo - 1) + OR_SUS.getProperty("VisitNoAtTaskTab_part2");
			if ( (fnpCheckElementDisplayedImmediately_NOR(taskOpenIconXpath) & (!fnpCheckElementDisplayedImmediately_NOR(visitNoInGivenTaskXpath)) )  ) {				
				fnpClick_NOR(taskOpenIconXpath);
			}
			
			
			
			try{
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
							taskOpenIconXpath = OR_SUS.getProperty("TaskTabISR_CertAudit_OpenIconPart1") + WOISOTaskType+ OR_SUS.getProperty("TaskTabISR_CertAudit_OpenIconPart2");
							taskRowNo = fnpFindRowContainsName("TaskTabTable", mainTaskName, taskAINameIndex);
							String visitNoInGivenTaskXpath = OR_SUS.getProperty("VisitNoAtTaskTab_part1") + (taskRowNo - 1) + OR_SUS.getProperty("VisitNoAtTaskTab_part2");
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
								taskOpenIconXpath = OR_SUS.getProperty("TaskTabISR_CertAudit_OpenIconPart1") + WOISOTaskType+ OR_SUS.getProperty("TaskTabISR_CertAudit_OpenIconPart2");
								taskRowNo = fnpFindRowContainsName("TaskTabTable", mainTaskName, taskAINameIndex);
								String visitNoInGivenTaskXpath = OR_SUS.getProperty("VisitNoAtTaskTab_part1") + (taskRowNo - 1) + OR_SUS.getProperty("VisitNoAtTaskTab_part2");
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
		fngProcessAI_ISR_TaskTab_1(taskInnerTableDataXpathForAI,taskInnerTableHeaderXpathForAI,WOISOTaskTab_Certification_ClientRecordChangeAIName, "Completed","AISaveNCloseBtn");
		fnpVerifyAIStatusInISR(WOISOTaskType,mainTaskName,WOISOTaskTab_Certification_ClientRecordChangeAIName,"Completed");
		/**************************************************************/
		
	
		
		
	}
	
	
	
public void fngRunJob(String jobName) throws Throwable{
		
		//String jobNameForGeneratingClientRecordChange="CreateClientRecordChangeAIQJob";
		
/*		String testUrlForRunningJob="http://oraapp10.nsf.org:8071/jobsecap/";
		String stageUrlForRunningJob="http://oraapp30c1.nsf.org:8071/jobsecap/";
		*/
		

		
	
			
		/****************Going to login in jobs url for clicking job for creating ClientRecordChange action item ***************/	
			// as per discussion with Inderjeet/Ruchi/Pradeep in Team meeting 21/July/2020 
			
			//driver.close();			
			//driver.quit();
		if (driver!=null) {
			//fnpCommonLogout();
			driver.quit();	
		}
		
			env=CONFIG.getProperty("Environment");
			String urlToOpenForJob=null;
			
			if (env.equalsIgnoreCase("Test")) {
				//urlToOpenForJob=testUrlForRunningJob;
				urlToOpenForJob=CONFIG.getProperty("TEST_Jobs_site_url");
			}else{
				//urlToOpenForJob=stageUrlForRunningJob;
				urlToOpenForJob=CONFIG.getProperty("STAGE_Jobs_site_url");
			}

			fnpLaunchBrowser();

			String userName = CONFIG.getProperty("adminUsername");
			String password = CONFIG.getProperty("adminPassword");
			String ssoUserName = CONFIG.getProperty("MainUserForSSO_withCompleteEmailID"); //SSO 13/Oct/2020
							
			driver.get(urlToOpenForJob);
			//Start code for SSO 13/Oct/2020
			Thread.sleep(10000);
			fngType("OR_SUS", "SSO_UserName", ssoUserName);
			Thread.sleep(6000);
			fngClick_OR("SSO_NexButton");
			Thread.sleep(10000);
			Runtime.getRuntime().exec(System.getProperty("user.dir") + "\\AutoIt_scripts\\sso_chrome.exe");
			Thread.sleep(25000);
			//Start code for SSO 13/Oct/2020
			// Comment SSO 13/Oct/2020
			/*fnpType("OR", "UserName_id", userName);
			fnpType("OR", "password_id", password);
			fnpMymsg("Just before login clicked");
			fngClick_OR("Login_id");*/
			/**
			 * Handling Alerts, Which is coming after login.
			 */
			fnpClick_OR("xpathForAck");
			Thread.sleep(1000);
			fnpCommonClickTopMainMenu("Menu", "FirstRefrenceLinkJustBelowToMenu", "QuartzJobsTopMenuLink", "JobNameFilterBox");				
			fnpType("OR", "JobNameFilterBox", jobName);
			
			int totalRows=fngCountRowsInTable("JobTable");
			int jRowCountCounter = 0;
			while ((totalRows>1) & (jRowCountCounter < 60) ){
				jRowCountCounter++;
				Thread.sleep(1000);
				totalRows=fngCountRowsInTable("JobTable");
			}

			String dataInFirstRowFirstColumn = fngFetchFromTable("JobTable", 1, 1);
			int j = 0;
			while (((dataInFirstRowFirstColumn.contains("No Jobs found.")) | (dataInFirstRowFirstColumn.toLowerCase().contains("found")))      & j < 15) {
				j++;
				Thread.sleep(1000);
				dataInFirstRowFirstColumn = fngFetchFromTable("JobTable", 1, 1);
			}

			if (((dataInFirstRowFirstColumn.toLowerCase().contains("found"))) | ((dataInFirstRowFirstColumn.contains("No Jobs found")))) {
				fnpMymsg("Qurartz job -'" + jobName + "' is  NOT present in Jobs table but it should  be present in this table after search. ");
				throw new Exception("Qurartz job -'" + jobName + "' is  NOT present in Jobs table but it should  be present in this table after search. ");
			} else {
				//nothing for now
			}

			
			String dataInJobNameColumn = fngFetchFromTable("JobTable", 1, 2);				
			if (dataInJobNameColumn.equalsIgnoreCase(jobName)) {
				//Thread.sleep(2000);
				fngClick_OR("FirstRunButtonInJobsTable");
				fngLoading_wait();
				Thread.sleep(3000);
				
				fnpWaitTillVisiblityOfElementNClickable("FirstRunButtonInJobsTable");
				fngClick_OR("CurrentlyExecutingJob_linkXpath");
				
				
				int statusColIndex = fngFindColumnIndex("JobTableHeader", "Status");
				String statusInFirstRowFirstColumn = fngFetchFromTable("JobTable", 1, statusColIndex);
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
			//int maxWaitTimeInMinutes=5;
			int maxWaitTimeInMinutes=15;
			boolean exitCond1=false;
			boolean exitCond2=false;
			while (true) {
				//try{
				
				
				//Thread.sleep(30000);
				
				
				
				
/*				waitCount++;
				Thread.sleep(1000 * 30 * 1);
				driver.navigate().refresh();
				
				fnpType("OR", "JobNameFilterBox", jobName);
				Thread.sleep(1000);
				
				*/
//				fnpClick_OR(APP_LOGS, driver, "ReloadCurrentlyExecutionJob");
				
				int statusColIndex = fngFindColumnIndex("JobTableHeader", "Status");
				String statusInFirstRowFirstColumn = fngFetchFromTable("JobTable", 1, statusColIndex);
				if (statusInFirstRowFirstColumn.equalsIgnoreCase("NORMAL")) {
					//break;
					fnpMymsg("Current status of this job '"+jobName+"' after approx. "+(waitCount/2)+" min. is --"+statusInFirstRowFirstColumn);
					exitCond1=true;
				}else{
					if (waitCount> (maxWaitTimeInMinutes*2) ) {
						throw new Exception("Status of Job is not changed to NORMAL even after approx. "+maxWaitTimeInMinutes+" min. as it is currenlty --"+statusInFirstRowFirstColumn);
					} else {
						//continue in while loop
						fnpMymsg("Current status of this job '"+jobName+"' after approx. "+(waitCount/2)+" min. is --"+statusInFirstRowFirstColumn);
					}
				}
				
				
			//	fnpClick_OR("CurrentlyExecutingJob_linkXpath");
				/*			if (!(fnpCheckElementDisplayedImmediately(APP_LOGS, driver, "CurrentlyExecutingJobTable"))) {
				fnpClick_OR(APP_LOGS, driver, "CurrentlyExecutingJob_linkXpath");
				
			}
			*/
				
				int whileCounter=0;
				while(whileCounter<=3){
					whileCounter++;
					try{
						if (!(fngCheckElementDisplayedImmediately("CurrentlyExecutingJobTable"))) {
							fnpClick_OR("CurrentlyExecutingJob_linkXpath");
							//fnpWaitForVisible(APP_LOGS, driver, "CurrentlyExecutingJobTable");
							fngWaitForVisible("CurrentlyExecutingJobTable", 20);
							break;
							}
						}catch(Throwable t){
							fnpMymsg("Currently Executing Job table is not visible after clicking Currently Executiong Job link, so going to click again--"+whileCounter);
							if (whileCounter>3) {
								throw t;
								
							}
							
					}
					
				}
				String dataInFirstRow = fngFetchFromTable("CurrentlyExecutingJobTable", 1, 1);
				//if (dataInFirstRow.equalsIgnoreCase("No jobs are currently executing.")) {
				if (dataInFirstRow.contains("No jobs are currently")) {
					//break;
					fnpMymsg("No jobs are currently execution in the Executing Job table.");
					exitCond2=true;
				} else {
					//continue in loop
					//fnpMymsg("Currenlty executing job in first row is----"+dataInFirstRow);
					int ourjobRow=fngFindRow_usingForPagingToNotThrowErrorIfNotFoundInCurrentPage("CurrentlyExecutingJobTable", jobName, 1);
					
					if (ourjobRow>0) {
						fnpMymsg("Currenlty executing job '"+jobName+"' 's row no. is----"+ourjobRow);
						exitCond2=false;
					} else {
						exitCond2=true;
					}
				}
				
				
				if ((exitCond1==true) & (exitCond2==true)) {
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
				Thread.sleep(1000);
			*/	
				
				fngClick_OR("ReloadCurrentlyExecutionJob");
				fngWaitForVisible("FirstRunButtonInJobsTable", 30);
				
			
				/*		}catch(Throwable t){
				if (catchCounter>10) {
					throw t;
				} else {
					catchCounter++;
				}
			}
			*/
				
				
				
				
			}
			
			
			
			
			
			
			/*****Now login back in normal iPulse application *****************/
			fnpCommonLogout() ;
			driver.close();
			// driver.quit();
			fngLaunchBrowserAndLogin();
			fnpSearchWorkOrderOnly(workOrderNo);				
			if (fngCheckElementDisplayedImmediately("EditWOBtn")) {
				fnpClick_OR("EditWOBtn");
				fnpMymsg("@  ---Here EditWOBtn is visible , so going to click it");

			}else{
				fnpMymsg("@  ---Here EditWOBtn is NOT visible , so ignore  it");
			}			

			
			
			
			
			/***********************************************************/
			
		
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
					//if (fnpCheckElementDisplayed_NOR(OR_SUS.getProperty("Oasis_error_inDiv"), 15)) {
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
		
		
		String taskOpenIconXpath = OR_SUS.getProperty("TaskTabISR_Task_OpenIconPart1") + WOISOTaskType+ OR_SUS.getProperty("TaskTabISR_Task_OpenIconPart2");
		
		
		if(  fnpCheckElementDisplayedImmediately_NOR(taskOpenIconXpath)   ) {				
			fnpClick_NOR(taskOpenIconXpath);
		}
		
		
		
		String taskInnerTableHeaderXpathForAI = OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part1") + (taskRowNo - 1)
				+ OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part2");

		String taskInnerTableDataXpathForAI = OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableData_part1") + (taskRowNo - 1)
				+ OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableData_part2");
		
		String taskExpansionPanelXpath = OR_SUS.getProperty("ISRTaskExpansionPanelGrid_part1") + (taskRowNo - 1) + OR_SUS.getProperty("ISRTaskExpansionPanelGrid_part2");

		
		
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
		
		String taskOpenIconXpath = OR_SUS.getProperty("TaskTabISR_Task_OpenIcon_BasedOnTaskNo_Part1") + taskNo+ OR_SUS.getProperty("TaskTabISR_Task_OpenIcon_BasedOnTaskNo__Part2");
		
		if(  fnpCheckElementDisplayedImmediately_NOR(taskOpenIconXpath)   ) {				
			fnpClick_NOR(taskOpenIconXpath);
		}
		
		
		
		String taskInnerTableHeaderXpathForAI = OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part1") + (taskRowNo - 1)
				+ OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part2");

		String taskInnerTableDataXpathForAI = OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableData_part1") + (taskRowNo - 1)
				+ OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableData_part2");
		
		String taskExpansionPanelXpath = OR_SUS.getProperty("ISRTaskExpansionPanelGrid_part1") + (taskRowNo - 1) + OR_SUS.getProperty("ISRTaskExpansionPanelGrid_part2");

		
		
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
		String openIconXpath = OR_SUS.getProperty("TaskTabISR_CertAudit_OpenIconPart1") + taskType+ OR_SUS.getProperty("TaskTabISR_CertAudit_OpenIconPart2");


		if(  fnpCheckElementDisplayedImmediately_NOR(openIconXpath)   ) {				
			fnpClick_NOR(openIconXpath);
		}
		
		
		 int taskAINameIndex = fnpFindColumnIndex("TaskTabTable_HeaderRow", WOISOTaskTab_TaskAISummaryTableColName_TaskAIName);

		int taskRow = fnpFindRowContainsName("TaskTabTable", mainTaskName, taskAINameIndex);
	
		
		String auditNoInGivenTaskAuditXpath = OR_SUS.getProperty("AuditNoAtTaskTab_part1") + (taskRow - 1) + OR_SUS.getProperty("AuditNoAtTaskTab_part2");
		
		String auditNo = fnpGetText_NOR(auditNoInGivenTaskAuditXpath);
		
		

		String visitNoInGivenTaskAuditXpath = OR_SUS.getProperty("VisitNoAtTaskTab_part1") + (taskRow - 1) + OR_SUS.getProperty("VisitNoAtTaskTab_part2");

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

					
					
		
		
		
		
		visitNoInGivenTaskAuditXpath = OR_SUS.getProperty("VisitNoAtTaskTab_part1") + (taskRow - 1) + OR_SUS.getProperty("VisitNoAtTaskTab_part2");		
		auditNoInGivenTaskAuditXpath = OR_SUS.getProperty("AuditNoAtTaskTab_part1") + (taskRow - 1) + OR_SUS.getProperty("AuditNoAtTaskTab_part2");
				
		auditNo = fnpGetText_NOR(auditNoInGivenTaskAuditXpath);				
		if (fnpCheckElementDisplayedImmediately("EditWOBtn")) {				
			fnpClick_OR("EditWOBtn");
		}
		


		
		
		/*****************IPM-6227***********************************************/
/*		
		String editBtnForEditGivenAuditTaskXpath = OR_SUS.getProperty("EditBtnForEditTaskAtTaskTab_part1") + (taskRow - 1) + OR_SUS.getProperty("EditBtnForEditTaskAtTaskTab_part2");			
		fnpClick_NOR(editBtnForEditGivenAuditTaskXpath);
		*/
		
/*		String expenseAllocationbtnXpath = OR_SUS.getProperty("ExpenseAllocationBtn_TaskAtTaskTab_part1") + (taskRow - 1) + OR_SUS.getProperty("ExpenseAllocationBtn_TaskAtTaskTab_part2");	
		fnpClick_NOR(expenseAllocationbtnXpath);
		*/
		
		
		
		String editFeesbtnXpath = OR_SUS.getProperty("EditFeesBtnForEditTaskAtTaskTab_part1") + (taskRow - 1) + OR_SUS.getProperty("EditFeesBtnForEditTaskAtTaskTab_part2");	
		fnpClick_NOR(editFeesbtnXpath);
		
		/***************************************************************************/
		
		
		
		Date todayDate=new Date();
		SimpleDateFormat sdfmt1 = new SimpleDateFormat("dd-MMM-yyyy");
		String todaydateInStringformat = sdfmt1.format(todayDate);
		
		fnpClick_OR_WithoutWait("BoundaryStartDateBtnAtEditTaskDialog");
		Thread.sleep(2000);			
		fnpCalendarDatePicker_BySelectValues_From_NextAndBack(todaydateInStringformat);
		
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
		fnpCalendarDatePicker_BySelectValues_From_NextAndBack(afterOneWeekdateInStringformat);
		Thread.sleep(2000);	
		
		fnpClick_OR("SaveBtnAtEditTask");
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
		fnaSelectingAuditorThroughSAM();
		String scheduleStartDateforThisAudit=todaydateInStringformat2;
		String scheduleEndDateforThisAudit=afterOneWeekdateInStringformat;
		
	/*	
		
		fnpCommonCodeForAuditorLookup();
		

		todaydateInStringformat = sdfmt1.format(new Date());

		fnpClick_OR("ScheduleStartDateCalBtn_xpath");
		Thread.sleep(2000);			
		fnpCalendarDatePicker_BySelectValues_From_DropDown2(todaydateInStringformat,
				OR_SUS.getProperty("calendarDatePickerSelectYear_xpath"), OR_SUS.getProperty("calendarDatePickerSelectMonth_xpath"));
		
		
		String scheduleStartDateforCertAudit=todaydateInStringformat;
		
		
		Thread.sleep(2000);	

		fnpClick_OR("ScheduleEndDateCalBtn_xpath");
		Thread.sleep(2000);			
		fnpCalendarDatePicker_BySelectValues_From_DropDown2(afterOneWeekdateInStringformat,
				OR_SUS.getProperty("calendarDatePickerSelectYear_xpath"), OR_SUS.getProperty("calendarDatePickerSelectMonth_xpath"));
		
		
		
		String scheduleEndDateforCertAudit=afterOneWeekdateInStringformat;
		
		Thread.sleep(2000);	

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
			fnpCallGoldenProcedure_COPY_AUDIT_TRANSACTION_SUBMIT(copyFromAudit_ForGivenTaskAudit,copyToAudit_ForGivenTaskAudit,(String) hashXlData.get("Auditor").trim());
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
		
		String givenTaskExpansionPanelXpath = OR_SUS.getProperty("ISRTaskExpansionPanelGrid_part1") + (taskRow - 1) + OR_SUS.getProperty("ISRTaskExpansionPanelGrid_part2");
		
		
		
		
		String givenTaskInnerTableDataXpathForAI = OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableData_part1") + (taskRow - 1)
				+ OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableData_part2");
		
		String givenTaskInnerTableHeaderXpathForAI = OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part1") + (taskRow - 1)
				+ OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part2");
		
		

		
		String taskAuditorXpath = givenTaskExpansionPanelXpath + "//tr/td/label[text()='Auditor']/../following-sibling::td/label";
		String taskAuditor = fnpGetText_NOR(taskAuditorXpath);
		
		if (taskAuditor.equalsIgnoreCase((String)hashXlData.get("AccountManager"))) {
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
		
	


		
		


		 givenTaskInnerTableHeaderXpathForAI = OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part1") + (taskRow - 1)
				+ OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part2");

		int aiTypeInnerTableIndex = fnpFindColumnIndex_NOR(givenTaskInnerTableHeaderXpathForAI, "AI Type");
		int aiNumberInnerTableIndex = fnpFindColumnIndex_NOR(givenTaskInnerTableHeaderXpathForAI, "AI Number");

		givenTaskInnerTableDataXpathForAI  = OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableData_part1") + (taskRow - 1)
				+ OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableData_part2");
		
		
		int performAuditRow = fngFindRowContainsName_NOR(givenTaskInnerTableDataXpathForAI, "PerformAudit", aiTypeInnerTableIndex);
		
		if (performAuditRow>0) {
			fnpMymsg("Perform Audit action item has been generated for   "+mainTaskName+"     Audit task.");
		} else {
			fnpMymsg("Perform Audit action item has NOT been generated for   "+mainTaskName+"     Audit task.");
			throw new Exception("Perform Audit action item has NOT been generated for   "+mainTaskName+"     Audit task.");
		}
		
		String performAuditAINo = fnpFetchFromTable_NOR(givenTaskInnerTableDataXpathForAI, performAuditRow, aiNumberInnerTableIndex);
		fnpMymsg("Perform Audit action item no.   is ---" + performAuditAINo);
		
		

		
		
		int statusInnerTableColIndex = fnpFindColumnIndex_NOR(givenTaskInnerTableHeaderXpathForAI, "Status");
		int waitCount = 0;
		String performAuditStatus;


			
		//fnpClick_OR("Complete_Perform_Audit_AI_button");
		fngRunJob("CompletePerformAuditAIQJob");
		fnpClick_OR("ISRTaskTab");
		if  (fnpCheckElementDisplayedImmediately_NOR(openIconXpath) ) {				
			fnpClick_NOR(openIconXpath);
		}

		
		fngCheckError(" after loading ");
				
		waitCount=0;
		int maxWaitTimeInMinutes=2;
		while (true) {
			waitCount++;

			taskAINameIndex = fnpFindColumnIndex("TaskTabTable_HeaderRow", WOISOTaskTab_TaskAISummaryTableColName_TaskAIName);
			taskRow = fnpFindRowContainsName("TaskTabTable", mainTaskName, taskAINameIndex);
			givenTaskInnerTableDataXpathForAI  = OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableData_part1") + (taskRow - 1)
					+ OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableData_part2");
			performAuditRow = fngFindRowContainsName_NOR(givenTaskInnerTableDataXpathForAI, "PerformAudit", aiTypeInnerTableIndex);
			givenTaskInnerTableHeaderXpathForAI = OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part1") + (taskRow - 1)
					+ OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part2");
			
			statusInnerTableColIndex = fnpFindColumnIndex_NOR(givenTaskInnerTableHeaderXpathForAI, "Status");
			
			performAuditStatus = fnpFetchFromTable_NOR(givenTaskInnerTableDataXpathForAI, performAuditRow, statusInnerTableColIndex);
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
				Thread.sleep(1000 * 20 * 1);
				fngCheckError(" after loading ");
				driver.navigate().refresh();
				// fnpLoading_wait();
				//Thread.sleep(8000);

				// fnpLoading_wait();


				fngCheckError(" after loading ");
				fnpClick_OR("ISRTaskTab");
				fngCheckError(" after loading ");
				if ( fnpCheckElementDisplayedImmediately_NOR(openIconXpath)  ) {				
					fnpClick_NOR(openIconXpath);
				}

				
			}

/*			if (waitCount > 60) {

				fnpMymsg("Script waited for approx. 30 min to become 'AI_COMPLETED' state of PerformAudit but it is still showing '" + performAuditStatus + "' .");
				throw new Exception("Script waited for approx. 30 min to become 'AI_COMPLETED' state of PerformAudit but it is still showing '" + performAuditStatus + "' .");

			}
			*/
			
/*			if (waitCount > 4) {

				fnpMymsg("Script waited for approx. 2 min to become 'AI_COMPLETED' state of PerformAudit but it is still showing '" + performAuditStatus + "' .");
				throw new Exception("Script waited for approx. 2 min to become 'AI_COMPLETED' state of PerformAudit but it is still showing '" + performAuditStatus + "' .");

			}
			
			*/
			

		}
	
		//}
				
		
		
		
		 aiTypeInnerTableIndex = fnpFindColumnIndex_NOR(givenTaskInnerTableHeaderXpathForAI, "AI Type");
		 aiNumberInnerTableIndex = fnpFindColumnIndex_NOR(givenTaskInnerTableHeaderXpathForAI, "AI Number");
		
		
		
		
		
		
		int technicalReviewAIRowNo = fngFindRowContainsName_NOR(givenTaskInnerTableDataXpathForAI, WOISOTaskTab_TechnicalReviewAIName, aiTypeInnerTableIndex);
		
		if (technicalReviewAIRowNo>0) {
			fnpMymsg("TechnicalReview action item has been generated for   "+mainTaskName+"     Audit task.");
		} else {
			fnpMymsg("TechnicalReview action item has NOT been generated for   "+mainTaskName+"     Audit task.");
			throw new Exception("TechnicalReview action item has NOT been generated for   "+mainTaskName+"     Audit task.");
		}
		
		String technicalReviewAINo = fnpFetchFromTable_NOR(givenTaskInnerTableDataXpathForAI, technicalReviewAIRowNo, aiNumberInnerTableIndex);
		fnpMymsg("TechnicalReview action item no.   is ---" + technicalReviewAINo);
		
		
		

		String technicalReviewStatus = fnpFetchFromTable_NOR(givenTaskInnerTableDataXpathForAI, technicalReviewAIRowNo, statusInnerTableColIndex);
		
		if (!(technicalReviewStatus.equalsIgnoreCase("AI_CREATED"))) {
			
			fnpMymsg("Current Status of TechnicalReview action item is NOT 'AI_CREATED'  b/c its current status is --"+technicalReviewStatus);
			throw new Exception ("Current Status of TechnicalReview action item is NOT 'AI_CREATED'  b/c its current status is --"+technicalReviewStatus);
		} else {
			fnpMymsg("Current Status of TechnicalReview action item is AI_CREATED.");
		}
		
		
		
		
		
		
		
		

		

		
		

		fngProcessAI_ISR_TaskTab_1(givenTaskInnerTableDataXpathForAI,givenTaskInnerTableHeaderXpathForAI,WOISOTaskTab_TechnicalReviewAIName, "Completed","SaveButton__OnConsolidatedScreen");
		

		fnpVerifyAIStatusInISR(taskType,mainTaskName,WOISOTaskTab_TechnicalReviewAIName,"Completed");
		//fnpVerifyAIStatusInISR(Integer.parseInt(oldTaskAINo),    taskType,  mainTaskName,  "CertDecision","Completed");
		

	
		
		
		if ((classNameText.equalsIgnoreCase(IPULSE_CONSTANTS.Cert_StandardsTestCaseName))) {
			
			taskRow = fnpFindRowContainsName("TaskTabTable", mainTaskName, taskAINameIndex);
			openIconXpath = OR_SUS.getProperty("TaskTabISR_CertAudit_OpenIconPart1") + taskType+ OR_SUS.getProperty("TaskTabISR_CertAudit_OpenIconPart2");
			if ( (fnpCheckElementDisplayedImmediately_NOR(openIconXpath) & (!(fnpCheckElementDisplayedImmediately_NOR(givenTaskInnerTableHeaderXpathForAI))) )  ) {				
				fnpClick_NOR(openIconXpath);
			}
											
			auditNoInGivenTaskAuditXpath = OR_SUS.getProperty("AuditNoAtTaskTab_part1") + (taskRow - 1) + OR_SUS.getProperty("AuditNoAtTaskTab_part2");		
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
		String oldTaskAINo = fngFetchFromTable("TaskTabTable", taskRow, taskAINoIndex).trim();	
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
		
		
		fngRunJob("ProcessWorkflowQueueQJob");
		fnpClick_OR("ISRTaskTab");
		
		

		
		
		openIconXpath = OR_SUS.getProperty("TaskTabISR_Task_OpenIcon_BasedOnTaskNo_Part1") + oldTaskAINo+ OR_SUS.getProperty("TaskTabISR_Task_OpenIcon_BasedOnTaskNo__Part2");
		taskRow = fnpFindRowContainsName("TaskTabTable", oldTaskAINo, taskAINoIndex);
		givenTaskInnerTableDataXpathForAI  = OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableData_part1") + (taskRow - 1)
				+ OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableData_part2");

		givenTaskInnerTableHeaderXpathForAI = OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part1") + (taskRow - 1)
				+ OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part2");
		
		
		
		
		
		//if  (fnpCheckElementDisplayedImmediately_NOR(openIconXpath)  ) {	
		if  (fnpCheckElementDisplayed_NOR(openIconXpath, 20) ) {
			fnpClick_NOR(openIconXpath);
		}
		
		
	taskRow = fnpFindRowContainsName("TaskTabTable", oldTaskAINo, taskAINoIndex);
	String innerTableDataXpathForAI = OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableData_part1") + (taskRow - 1)
				+ OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableData_part2");
	String innerTableHeaderXpathForAI = OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part1") + (taskRow - 1)
				+ OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part2");
		

	openIconXpath = OR_SUS.getProperty("TaskTabISR_Task_OpenIcon_BasedOnTaskNo_Part1") + oldTaskAINo+ OR_SUS.getProperty("TaskTabISR_Task_OpenIcon_BasedOnTaskNo__Part2");
		
		
		 waitCount=0;
		 int certDecAIRowNo=0;

		while (true) {
			
			waitCount++;
			certDecAIRowNo = fngFindRowContainsName_NOR(givenTaskInnerTableDataXpathForAI, "ecision", aiTypeInnerTableIndex);
			
			if (certDecAIRowNo>0) {
				fnpMymsg("Cert Decision action item has been generated for   "+mainTaskName+"   Audit task.");
				break;
			} else {

				fngCheckError(" after loading ");
				fnpMymsg(" we are in  waiting loop for waiting cert decision action item generated.  ----" + waitCount);
				fnpDisplayingMessageInFrame("Now  we are in  waiting loop for waiting cert decision action item generated.----" + ((double)waitCount/2)+" approx. min.", 8);
				Thread.sleep(1000 * 20 * 1);
				fngCheckError(" after loading ");
				driver.navigate().refresh();
				// fnpLoading_wait();
				//Thread.sleep(8000);

				// fnpLoading_wait();

				
				fngCheckError(" after loading ");					
				fnpClick_OR("ISRTaskTab");
				fngCheckError(" after loading ");
				
				

				
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
		
		

		fngProcessAI_ISR_TaskTab_1(innerTableDataXpathForAI,innerTableHeaderXpathForAI,"CertDecision", "Completed","SaveBtnOnPerformCertDecisionAI");

		//fnpVerifyAIStatusInISR(taskType,mainTaskName,"CertDecision","Completed");
		//fnpVerifyAIStatusInISR(Integer.parseInt(oldTaskAINo),    taskType,  mainTaskName,  "CertDecision","Completed");
		fnpVerifyAIStatusInISR_basedOnTaskNo(oldTaskAINo,    taskType,  mainTaskName,  "CertDecision","Completed");
		
		
		
		taskAINameIndex = fnpFindColumnIndex("TaskTabTable_HeaderRow", WOISOTaskTab_TaskAISummaryTableColName_TaskAIName);
		//TaskRowNo = fnpFindRowContainsName("TaskTabTable", WOISOTaskTab_Certification_MainTaskAIName, taskAINameIndex);
		taskRow = fnpFindRowContainsName("TaskTabTable", oldTaskAINo, taskAINoIndex);
		innerTableDataXpathForAI = OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableData_part1") + (taskRow - 1)
					+ OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableData_part2");
		innerTableHeaderXpathForAI = OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part1") + (taskRow - 1)
					+ OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part2");
					
		openIconXpath = OR_SUS.getProperty("TaskTabISR_Task_OpenIcon_BasedOnTaskNo_Part1") + oldTaskAINo+ OR_SUS.getProperty("TaskTabISR_Task_OpenIcon_BasedOnTaskNo__Part2");
		
		if ( (fnpCheckElementDisplayedImmediately_NOR(openIconXpath)  )  ) {				
			fnpClick_NOR(openIconXpath);
			fnpMymsg("Clicked  open icon");
		}
		
		
		aiTypeInnerTableIndex = fnpFindColumnIndex_NOR(innerTableHeaderXpathForAI, "AI Type");
		aiNumberInnerTableIndex = fnpFindColumnIndex_NOR(innerTableHeaderXpathForAI, "AI Number");
		int certIssueAIRowNo = fngFindRowContainsName_NOR(innerTableDataXpathForAI, "CertIssue", aiTypeInnerTableIndex);
		
		if (certIssueAIRowNo>0) {
			fnpMymsg("Cert Issue action item has been generated for Cert Audit task.");
			
		} else {
			fnpMymsg("Cert Issue action item has NOT been generated for Cert Audit task.");
			throw new Exception("Cert Issue action item has NOT been generated for Cert Audit task.");
		}
		
		
		
		
		fngProcessAI_ISR_TaskTab_1(innerTableDataXpathForAI,innerTableHeaderXpathForAI,"CertIssue", "Completed","SaveButton__OnConsolidatedScreen");

	
		fnpVerifyAIStatusInISR_basedOnTaskNo(oldTaskAINo,    taskType,  mainTaskName,  "CertIssue","Completed");
		
		
		
		taskAINameIndex = fnpFindColumnIndex("TaskTabTable_HeaderRow", WOISOTaskTab_TaskAISummaryTableColName_TaskAIName);
		//int TaskRowNo = fnpFindRowContainsName("TaskTabTable", WOISOTaskTab_Certification_MainTaskAIName, taskAINameIndex);
		taskRow = fnpFindRowContainsName("TaskTabTable", oldTaskAINo, taskAINoIndex);
		innerTableDataXpathForAI = OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableData_part1") + (taskRow - 1)
					+ OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableData_part2");
		innerTableHeaderXpathForAI = OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part1") + (taskRow - 1)
					+ OR_SUS.getProperty("ISRTaskInnerTableForActionItemsTableHeader_part2");

	//	openIconXpath = OR_SUS.getProperty("TaskTabISR_Task_OpenIconPart1") + WOISOTaskType_CertificationAudit+ OR_SUS.getProperty("TaskTabISR_Task_OpenIconPart2");
		
		

		
		fnpTaskAuditStatusVerify_basedOnTaskNo( oldTaskAINo,mainTaskName, "REVIEWED");
		
		
		int totalRows=fnpCountRowsInTable("TaskTabTable");
		int taskAITypeIndex = fnpFindColumnIndex("TaskTabTable_HeaderRow", WOISOTaskTab_TaskAISummaryTableColName_TaskAIType);
		int totalRecertAuditRows=0;
		String taskAIType="";
		for (int i = 0; i < totalRows; i++) {
			
			taskAIType=fngFetchFromTable("TaskTabTable", (i+1), taskAITypeIndex);
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
		
		
		
		
		fngLaunchBrowserAndLogin();
		fnpSearchWorkOrderOnly(workOrderNo);
		

		if (fnpCheckElementDisplayedImmediately("EditWOBtn")) {
			fnpClick_OR("EditWOBtn");
		}
		
					
		
		
		fnpClick_OR("ISRTaskTab");
		
		
		
		fnpTaskAuditStatusVerify_basedOnTaskNo(  oldTaskAINo,mainTaskName, "COMPLETED" );
		
		
		
	
		
		
		
		
		
		

		
		
	}
	
	
	
	
	public static String fnaSelectingAuditorThroughSAM() throws Throwable{
		
		String EndOnDate = "";
		String originalHandle = driver.getWindowHandle();

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
	

		fnaLoading_wait_In_SAM();
/*		
		if (!(fnpCheckElementDisplayed("AuditorNameTxtBxInSAM", 120))) {
			throw new Exception("Either SAM is not opened properly or Auditor Name text box is not visible. See screenshot.");
			
		} 
		*/
	
		try{
		fngWaitForVisible( "AuditorNameTxtBxInSAM", 120);
		}catch(Throwable t){
			fnpMymsg( "Either SAM is not opened properly or Auditor Name text box is not visible. See screenshot.");
			throw new Exception("Either SAM is not opened properly or Auditor Name text box is not visible. See screenshot.");	
		}
		
		fngType("OR_SUS", "AuditorNameTxtBxInSAM", (String) hashXlData.get("Auditor"));

	//	fngGetORObjectX("SearchBtnInSAM").click();
		//fngClick_InSAM_OR("SearchBtnInSAM"); //2/Nov/2020 This is commented
		int iWhileCounter=0;
		while(true){
			iWhileCounter++;
			try{
				fnpScrollToElement(fnpGetORObjectX("SearchBtnInSAM"));
				//fnpGetORObjectX("SearchBtnInSAM").click();
				JavascriptExecutor executor = (JavascriptExecutor)driver;
				executor.executeScript("arguments[0].click();", fnpGetORObjectX("SearchBtnInSAM"));
				fnpMymsg("Clicked Search button after inserting Auditor value in SAM");
				fnaLoading_wait_In_SAM();
				break;
			}catch(Throwable t){
				if (t.getMessage().contains("is not clickable at point")) {
					fnpMymsg("Search button in SAM is not clickable, so going to scroll");
					fnpScrollToElement(fnpGetORObjectX("SearchBtnInSAM"));
					try{
					fnpGetORObjectX("SearchBtnInSAM").click();
					fnpMymsg("Clicked Search button after inserting Auditor value in SAM");
					fnaLoading_wait_In_SAM();
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
		
		
		fnpMymsg("Clicked Search button after inserting Auditor value in SAM");

		//fnpLoading_wait_In_SAM();
		

		

		//fnpLoading_wait_In_SAM();
		int iwhileCounter=0;
		while(true){
			iwhileCounter++;			
			try{
				//if else conditions added for Masking By GP(24/01/2k19)
				
				if(CONFIG.getProperty("Environment").equalsIgnoreCase("Staging")){  
					fngWaitForVisible("TestScriptUserLinkInCardInSAM",10);
					//fngGetORObjectX("TestScriptUserLinkInCardInSAM").click();
					fngClick_InSAM_OR("TestScriptUserLinkInCardInSAM");
				}else if(CONFIG.getProperty("Environment").equalsIgnoreCase("Test")){ 
					fngWaitForVisible("TestScriptUserLinkInCardInSAMForMasking",10);
					//fngGetORObjectX("TestScriptUserLinkInCardInSAMForMasking").click();
					fngClick_InSAM_OR("TestScriptUserLinkInCardInSAMForMasking");
				}
				break;
			}catch(Throwable t){
				
				
				
				if (iwhileCounter < 3) {
					//nothing to do					
				} else {
					//break;
					
					if  (   (t.getMessage().contains("Timed out"))   |  (t.getMessage().contains("visibility of element located by"))   ) {
						throw new Exception("Auditor link in SAM is not present in search auditor result.");
					} else {
						throw t;
					}
					
					
					
				}
				

				
				
			}
			
		}
		
		
	
		fnpMymsg("Clicked Auditor link in Search Results in SAM");

		fnaLoading_wait_In_SAM();
		
		
		//fnpSimpleSelectList(ORXpath, expvalue)
		//Handling Justification Reason By Ghanshyam 09Apr2k19
		fngWaitForVisible("AssignVisitInSAM");
		
		try {
			
			
		if (fngCheckElementDisplayedImmediately("AssignVist_JustifyReason1") | fngCheckElementDisplayedImmediately("AssignVist_JustifyReason2")) {
			 fnpSelectValue_By_SelectClass(OR_SUS.getProperty("AssignVist_JustifyReason1"), (String) hashXlData.get("Assign_Visit_Justify_Reason_In_SAM"));
			 fnpMymsg("AssignVist_JustifyReason1");
		 }
		}catch(Throwable t) {
			 fnpSelectValue_By_SelectClass(OR_SUS.getProperty("AssignVist_JustifyReason2"), (String) hashXlData.get("Assign_Visit_Justify_Reason_In_SAM"));
			 fnpMymsg("AssignVist_JustifyReason2");
		}
		
		//fngGetORObjectX("AssignVisitInSAM").click();
		fngClick_InSAM_OR("AssignVisitInSAM");
		fnpMymsg("Clicked AssignVisitInSAM in SAM");
		
		//Handling AssignAuditorPopupInSAM is coming after handle Justification Reason By Ghanshyam 09Apr2k19
		if(fngCheckElementDisplayedImmediately("AssignAuditorSuccessPopupInSAM")){
			String successMsgText = fnpGetText_NOR(OR_SUS.getProperty("AssignAuditorSuccessPopupInSAM"));
			fngClick_InSAM_OR("AssignAuditorOKButtonInSAM");
			fnpMymsg("Clicked OK Button in Assign Auditor Popup in SAM and " + successMsgText);
		}
		
		//if else conditions added for Masking By Ghanshyam(24/01/2k19)
		if(CONFIG.getProperty("Environment").equalsIgnoreCase("Staging")){
			fngClick_InSAM_OR("UnscheduledLinkInAssignedAuditorInSAMForMasking");
			fnpMymsg("Clicked AuditorLinkInSearchResultInSAM in SAM");
		}else if(CONFIG.getProperty("Environment").equalsIgnoreCase("Test")){
			fngClick_InSAM_OR("UnscheduledLinkInAssignedAuditorInSAMForMasking");             
			fnpMymsg("Clicked on Unscheduled Link In AssignedAuditor InSAMForMasking.");
		}
		
		fnaLoading_wait_In_SAM();
		
		fnaSelectDateFromCalenderInSam("Starts on");
		
		
		WebElement EndOnDateLabel =  fngGetORObjectX("SAM_EndOnDate_Label");
		Actions act = new Actions(driver) ;
		act.moveToElement(EndOnDateLabel).doubleClick().build().perform();
		Thread.sleep(2000);
		EndOnDate = fngGetORObjectX("SAM_EndOnDate_Input").getAttribute("value").trim();
		 
		
		//fnaSelectDateFromCalenderInSam(7);

		//Thread.sleep(1000);
		//fnpType("OR", "Schedule_EndsOnTxtBx", endDateInString);

		//fngGetORObjectX("SaveBtnInSAM").click();
		fngClick_InSAM_OR("SaveBtnInSAM");
		fnpMymsg("Clicked SaveBtnInSAM in SAM");

		fnaLoading_wait_In_SAM();
		
		if (fnpCheckElementDisplayed_NOR(OR_SUS.getProperty("OKBtnInSAM"),5)) {

			//fngGetORObjectX("OKBtnInSAM").click();
			fngClick_InSAM_OR("OKBtnInSAM");
			fnpMymsg("Clicked OKBtnInSAM in SAM");

			//fnpLoading_wait_In_SAM();
		}
		
		fnaLoading_wait_In_SAM();
		fngCheckError_In_SAM(" ");
		
		
		driver.close();
		driver.switchTo().window(originalHandle);
		return EndOnDate;
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

			fnpClick_NOR(OR_SUS.getProperty("CarExpandIcon_xpath_FirstPart")+(i+1)+OR_SUS.getProperty("CarExpandIcon_xpath_SecondPart"));	
			Thread.sleep(2000);
			Thread.sleep(2000);
			//fnpPFListModify_NOR(OR_SUS.getProperty("CAR_Status_PFList_part1")+i+OR_SUS.getProperty("CAR_Status_PFList_part2"), "Submitted");
			listXpath=OR_SUS.getProperty("CAR_Status_PFList_part1")+i+OR_SUS.getProperty("CAR_Status_PFList_part2");
			listXpathOptions=OR_SUS.getProperty("CAR_Status_PFListOptions_part1")+i+OR_SUS.getProperty("CAR_Status_PFListOptions_part2");
			fnpPFList_NOR(listXpath, listXpathOptions, "Submitted");

			fnpClick_NOR(OR_SUS.getProperty("CARStatusSaveBtn_part1")+i+OR_SUS.getProperty("CARStatusSaveBtn_part2"));
			if (fnpCheckElementDisplayedImmediately_NOR(OR_SUS.getProperty("CarExpandIcon_xpath_FirstPart")+(i+1)+OR_SUS.getProperty("CarExpandIcon_xpath_SecondPart"))) {
				fnpClick_NOR(OR_SUS.getProperty("CarExpandIcon_xpath_FirstPart")+(i+1)+OR_SUS.getProperty("CarExpandIcon_xpath_SecondPart"));
				Thread.sleep(2000);
			}
			Thread.sleep(2000);
		//	fnpPFListModify_NOR(OR_SUS.getProperty("CAR_Status_PFList_part1")+i+OR_SUS.getProperty("CAR_Status_PFList_part2"), "Approved");
			listXpath=OR_SUS.getProperty("CAR_Status_PFList_part1")+i+OR_SUS.getProperty("CAR_Status_PFList_part2");
			listXpathOptions=OR_SUS.getProperty("CAR_Status_PFListOptions_part1")+i+OR_SUS.getProperty("CAR_Status_PFListOptions_part2");
			fnpPFList_NOR(listXpath, listXpathOptions, "Approved");
			
			fnpClick_NOR(OR_SUS.getProperty("CARStatusSaveBtn_part1")+i+OR_SUS.getProperty("CARStatusSaveBtn_part2"));
			
			
		}
		
		

	}
	
	
	
	
	
	
	
	
	
	
	
	//Regarding query in ATA-67 on 14-12-2017 --Mail subject is -->Regarding query in ATA-67
	public static void fngRunQueryAfterMovingWoToInReviewForBillCodes( String standard, String woNo) throws Throwable{
		

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


			
			String query1 =	"SELECT decode(count(*),0,'N','Y') cou_code_setup_flg "
							+" FROM IQ.IQI_STD_BILL_CODES t "
							+" WHERE t.STD_CODE = '"+standard+"' "
							+" AND t.BILL_TO_OFFICE ='NSFUSA' --IQ_APP_ATTRIBUTES.BILL_TO_OFFICE "
							+" AND t.CURRENCY = 'USD' --IQ_APPLICATIONS.CURRENCY_CODE "
							+" AND t.program_code ='ISR' --CUSTOMERS.PRIM_PROG_CODE "
							+" AND t.cou_code='USA' -- CUSTOMERS.cou_code ";

			
			String query2="SELECT decode(count(*),0,'N','Y') cou_code_setup_flg "
							+" FROM IQ.IQI_STD_BILL_CODES t "
							+" WHERE t.STD_CODE = '"+standard+"' "
							+" AND t.BILL_TO_OFFICE ='NSFUSA' --IQ_APP_ATTRIBUTES.BILL_TO_OFFICE "
							+" AND t.CURRENCY = 'USD' --IQ_APPLICATIONS.CURRENCY_CODE "
							+" AND t.program_code ='ISR' --CUSTOMERS.PRIM_PROG_CODE "
							+" -- AND t.cou_code='USA' -- CUSTOMERS.cou_code jin comment out this line if no cou_code set up "
							+" and not exists ( select 1 from IQ_CUS_STD_FINOPS f where F.ISBC_SEQ=t.seq and f.app_no='"+woNo+"' ) ";

			

			
			
			String query3="SELECT decode(count(*),0,'N','Y') cou_code_setup_flg "
							+" FROM IQ.IQI_STD_BILL_CODES t "		
							+" WHERE t.STD_CODE = '"+standard+"' "
							+" AND t.BILL_TO_OFFICE ='NSFUSA' --IQ_APP_ATTRIBUTES.BILL_TO_OFFICE "
							+" AND t.CURRENCY = 'USD' --IQ_APPLICATIONS.CURRENCY_CODE "
							+" AND t.program_code ='ISR' --CUSTOMERS.PRIM_PROG_CODE "
							+" AND t.cou_code='USA' -- CUSTOMERS.cou_code "
							+" and not exists ( select 1 from IQ_CUS_STD_FINOPS f where F.ISBC_SEQ=t.seq and f.app_no='"+woNo+"' ) ";


			
			

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


			

			connection.close();

		} catch (SQLException e) {
			fnpMymsg("*********************************************** Either connection Failed or some different error with Database,   Getting Error >>  " + e.getMessage().trim());
			fnpMymsg("=========================================================================================================================================");
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		fnpMymsg("******************");

		// return rate;
		
	}
	
	
	
	
	
	public static void fnpDeleteFacilitySizeWhichIsAlreadyAdded() throws Throwable{
		
		String xpathForDeleteFacilityIcon=".//*[@id='mainForm:tabView:facSizeDT:0:facSizeDelete']";
		String xpathForDeleteFacilityConfirmationYesBtn=".//*[@id='mainForm:tabView:confirmDeleteYes']";
		
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
	
	public static void fngDeleteFacilityEmployeeCountWhichIsAlreadyAdded() throws Throwable{
		
		String xpathForDeleteFacilityEmployeeCountIcon=".//*[@id='mainForm:tabView:empCountDtFcInfo:0:delShiftEmpFac']";
		String xpathForDeleteFacilityEmployeeCountConfirmationYesBtn=".//*[@id='mainForm:tabView:yesDelEmpCountFac']";
		
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
	/*********** Function to Launch the browser and login with user credential details *************/
	public static boolean fngLaunchBrowserAndLogin() throws Exception {
		boolean present;
		try {

			killprocess();

			fnpLaunchBrowser();
			
			//String loginAs = CONFIG.getProperty("loginAsSust"); 
			String userName = CONFIG.getProperty("adminUsername");
			String password = CONFIG.getProperty("adminPassword");
			String ssoUserName = CONFIG.getProperty("MainUserForSSO_withCompleteEmailID");//SSO 13/Oct/2020
			String siteUrl = null;

			if (excelSiteURL != null) {
				if (excelSiteURL.equalsIgnoreCase("")) {
					siteUrl = CONFIG.getProperty("testSiteName");
				} else {
					// siteUrl = excelSiteURL;

					if (excelSiteURL.contains("Oasis_Url")) {// for Oasis site
																// in WO ISR
						siteUrl = excelSiteURL.split("Oasis_Url:")[0];
					} else {
						// rest for all
						siteUrl = excelSiteURL.split("LoginAsTestSiteName:")[0];
					}
					// siteUrl = excelSiteURL.split("LoginAsTestSiteName:")[0];
				}
			} else {
				siteUrl = CONFIG.getProperty("testSiteName");
			}

			driver.get(siteUrl);
			//Thread.sleep(10000);
			
			fnaLoading_PageLoad();
			fnpMymsg("Navigating to url --->" + siteUrl);
			fnpCheckErrorPageNotFound();

			//fnpWaitTillVisiblityOfElementNClickable_OR("Login_id");  //SSO 13/Oct/2020

			// Thread.sleep(1000);
			String handle1 = driver.getWindowHandle();

			String originalHandle1 = driver.getWindowHandle();

			/*if (!iPulse_Invalid_Password_Verification) {//comment this condition 13/Oct/2020

				fnpMymsg("First going to check login with invalid/wrong password.");
				fnpDisplayingMessageInFrame_fnpMymsg("First going to check login with invalid/wrong password.", 10);//Login_As
				//fngType("OR_SUS", "Login_As", loginAs);
				
				fngType("OR_SUS", "UserName_id", userName);
				fngType("OR_SUS", "password_id", "wrong_password"); 
				
				fnpMymsg("Just before login clicked");

				// fnpClick_OR("Login");
				fngGetORObjectX("Login_id").click();
				// Thread.sleep(2000);
				fnpLoading_wait();
				fnpMymsg("Just login clicked");
				fnpMymsg("Just after loading wait after login clicked");

				//if (fnpCheckElementDisplayed("errorMessage_id", 30)) {
				if (fnpCheckElementDisplayed("errorMessage_id", 30)) {
					// throw new Exception("Login is not successfull.");
					fnpMymsg("Login is failed as expected due to wrong password");
					fnpMouseHover_OR_EvenMultipleObjectsHavingSameXpath_usingSpecialForErrorClassOnly("errorMessage_id");
					String errMsg = fnpGetText_OR_EvenMultipleObjectsHavingSameXpath_specialForExtractingErrorMsgTextOnlyEvenIfNotVisible("errorMessage_id");
					fnpMymsg(" Error  thrown by application after inserting wrong password is --- " + errMsg);

				} else {
					String text;
					if (fnpCheckElementDisplayedImmediately("logOutBtn2_linkText")) {
						text="Login should not be successfull with wrong password.";
						fnpDisplayingMessageInFrame_fnpMymsg(text, 10);
						Thread.sleep(1000);
						throw new Exception(text);
					}else{
						text="Error message on inserting either wrong username or wrong password is not coming, hence failed as error message should come on wrong credential details.";
						fnpMymsg(text);
						fnpDisplayingMessageInFrame_fnpMymsg(text, 10);
						Thread.sleep(1000);
						throw new Exception(text);

					}
				}

				fnpMymsg("Now going to refresh the screen and again login with valid user credential details.");
				driver.navigate().refresh();
				// Thread.sleep(2000);
				iPulse_Invalid_Password_Verification = true;
			}*/
			//fngType("OR_SUS", "Login_As", loginAs);
			//Thread.sleep(8000);
			//fngType("OR_SUS", "UserName_id", userName);//comment 13/Oct/2020
			fngType("OR_SUS", "SSO_UserName", ssoUserName);

			//fngType("OR_SUS", "password_id", password);//comment 13/Oct/2020
			//fnpMymsg("Just before login clicked");

			/*fnpMouseHover("Login_id");//comment 13/Oct/2020
			fnpClick_OR("Login_id");*/
			fngClick_OR("SSO_NexButton");
			//Thread.sleep(5000);
			//Thread.sleep(5000);
			Runtime.getRuntime().exec(System.getProperty("user.dir") + "\\AutoIt_scripts\\sso_chrome.exe");
			//Thread.sleep(15000);
			fnpMymsg("Just login clicked");
			fnpMymsg("Just after loading wait after login clicked");

			Set<String> handle2 = driver.getWindowHandles();
			int a = handle2.size();
			if (fnpCheckElementDisplayedImmediately("errorMessage_id")) {
				throw new Exception("Login is not successfull.");
			}

			/**
			 * Handling Alerts, Which is coming after login.
			 */
			fnpClick_OR("xpathForAck");
			try {

				fnpWaitTillVisiblityOfElementNClickable_OR("logOutBtn2_linkText");

			} catch (Throwable t) {
				fnpDesireScreenshot("justBeforeRefreshing");
				fnpMymsg("@  -----due to invalid selector , logoutout link not identified, so refreshing it again. See screenshot 'justBeforeRefreshing' .");
				driver.navigate().refresh();
				Thread.sleep(8000);
				fnpWaitForVisible("logOutBtn2_linkText");
			}

			// fnpWaitTillVisiblityOfElementNClickable_OR("logOutBtn2_linkText");

			present = true;

			if (!referenceSuite.equalsIgnoreCase(currentSuiteName)) {
				fnpFetchApplicationVersion(currentSuiteName);
				referenceSuite = currentSuiteName;
			}

			fnpClickAtTopWorkAroundForClickingMenu();

		} catch (Throwable t) {

			fnpDesireScreenshot("LoginFailed");
			present = false;
			String stackTrace = Throwables.getStackTraceAsString(t);
			String errorMsg = t.getMessage();
			errorMsg = "\n\n\n\n Login failed.See screenshot 'LoginFailed' \n\n\n\n" + errorMsg + stackTrace;
			Exception c = new Exception(errorMsg);
			ErrorUtil.addVerificationFailure(c);

			killprocess();
			IsBrowserPresentAlready = false;

			throw new Exception(errorMsg);// reports

		}

		return present;
	}
	
	
	
	
	
	public static Integer fnaLoading_PageLoad() throws Throwable{
		Integer PageWait = 0;
		try{
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			for(int k=2;k<=Long.parseLong(CONFIG.getProperty("Element_MAX_WaitTime")); k++){
				String Text = (String) jse.executeScript("return document.readyState");//.equals("complete");
				if(Text.equals("complete")){
					//fnsApps_Report_Logs("Page is loaded in '"+k+"' seconds.");
					PageWait = k;
					break;
				}else{
					Thread.sleep(1000);
				}
				if(k==Long.parseLong(CONFIG.getProperty("Element_MAX_WaitTime"))){
					fnpDesireScreenshot("PageLoad_Fail");
					throw new Exception("FAIL == after '"+Long.parseLong(CONFIG.getProperty("Element_MAX_WaitTime"))+"' seconds wait page is not loading.");
				}
			}
		}catch(Throwable t){
			throw new Exception(Throwables.getStackTraceAsString(t));
		} return PageWait;
	}
	
	
	
	public static void fngCommonLoginPart(String className) throws Throwable {

		start = new Date();

		if (!IsBrowserPresentAlready) {
			
			
			IsBrowserPresentAlready = fngLaunchBrowserAndLogin();
			
			
			// fnpMymsg(" Browser is launched");
		} else {
			fnpMymsg("@  ----Browser is not already present, something error happened.");
		}

		if (className.equalsIgnoreCase("BulkAssign_Unassign_To_Assign") | className.equalsIgnoreCase("BulkAssign_ChangeAssignTo")) {
			fnpCommonClickTopMainMenu("Menu", "FirstRefrenceLinkJustBelowToMenu", "BulkAssignmentLink", "WorkLoadPlannerList");

			fnpLoadHashData(hashXlData, currentSuiteXls, className, 2);
			return;

		}

		fnpCommonClickTopMainMenu("Menu", "FirstRefrenceLinkJustBelowToMenu", "CreateWorkOrderLink", "ClientLKPBtn");

		// fnpLoadHashData(hashXlData, currentSuiteXls, className, 2);

	}
	/**
	 * to check the Support Certification check box
	 * according the value going to pass. 
	 * @param supportinCertificationValue
	 * @throws Throwable
	 */
	public static void fngcheckSupportingCertification(String supportinCertificationValue) throws Throwable{
		fnpMymsg("......Going to click on the YES for check box of supportingCert......");
		if(supportinCertificationValue.equalsIgnoreCase("YES")){
			if(driver.findElement(By.xpath(".//tr/td/label[contains(text(),'Is NSF the CB for the supporting')]/../following-sibling::td/table/tbody/tr/td/label[text()='Yes']")).isSelected()){
				fnpMymsg("Supporting Certification YES is already selected!!!.");
			}else{
				if(driver.findElement(By.xpath(".//tr/td/label[contains(text(),'Is NSF the CB for the supporting')]/../following-sibling::td/table/tbody/tr/td/label[text()='Yes']")).isEnabled()){
					fngClick_OR_WithoutWait("Checkbox_Supporting_Certifications_Y");
					fngCheckError("     Supporting Certification YES option is not available or xpath has been changed         ");
				}else{
					fnpMymsg("     Supporting Certification YES option is not available or xpath has been changed         ");
					fngCheckError("     Supporting Certification YES option is not available or xpath has been changed         ");
				}
			}
		}else if(supportinCertificationValue.equalsIgnoreCase("NO")){
			if(driver.findElement(By.xpath(".//tr/td/label[contains(text(),'Is NSF the CB for the supporting')]/../following-sibling::td/table/tbody/tr/td/label[text()='No']")).isSelected()){
				fnpMymsg("Supporting Certification NO is already selected!!!.");
			}else{
				if(driver.findElement(By.xpath(".//tr/td/label[contains(text(),'Is NSF the CB for the supporting')]/../following-sibling::td/table/tbody/tr/td/label[text()='No']")).isEnabled()){
					fngClick_OR_WithoutWait("Checkbox_Supporting_Certifications_N");
					fngCheckError("     Supporting Certification NO option is not available or xpath has been changed         ");
				}else{
					fnpMymsg("     Unable to click on the NO for check box of supportingCert.         ");
					fngCheckError("     Unable to click on the NO for check box of supportingCert.         ");
				}
			}
		}
	}
	/***** Common final catch block for @Test ***/
	public static void fngCommonFinalCatchBlock(Throwable t, String errCustomMsg, String screenshotName) throws Throwable {

		fail = true;
		isTestPass = false;
		String errMsg = "";
		try {
			if (fngCheckElementPresenceImmediately("ErrorMessage")) {

				fngMouseHover_OR_EvenMultipleObjectsHavingSameXpath_usingSpecialForErrorClassOnly("ErrorMessage");
				errMsg = fngGetText_OR_EvenMultipleObjectsHavingSameXpath_specialForExtractingErrorMsgTextOnlyEvenIfNotVisible("ErrorMessage");
			}
		} catch (Throwable tt) {
			// nothing to mention here for now
		}

		fnpMymsg(errCustomMsg + " . Error is -->" + t.getMessage() + "'\n\n" + errMsg);

		String textMessage = errCustomMsg + " . Error is -->" + t.getMessage() + "'\n\n" + errMsg;

		if (Boolean.parseBoolean((CONFIG.getProperty("ShowinglogMessagesinPopupFrameFlag")))) {
			fnpDisplayingMessageInFrame(textMessage, 10);
		}
		
		//fnaTake_Screen_Shot(screenshotName);
		fnpDesireScreenshot_old(screenshotName);
		

		File a = new File(".\\..\\screenshots" + "//screenshots_" + currentSuiteCode + "//" + currentScriptCode + "//" + screenshotName + "--"+SShots + ".png");
		Reporter.log("<a href=\"" + a + "\">  Click here to see screenshot (if going through email attachment or log history folder)</a>");

		fnpMymsg("");

		File b = new File(".\\..\\..\\" + screenshots_path + "//screenshots_" + currentSuiteCode + "//" + currentScriptCode + "//" + screenshotName + "--"+ SShots + ".png");

		Reporter.log("<a href=\"" + b + "\">  Click here to see screenshot (if going through local XSLT folder) </a>");

		String stackTrace = Throwables.getStackTraceAsString(t);
		String errorMsg = t.getMessage();

		errorMsg = errCustomMsg + "   .See screenshot '" + screenshotName + "'\n\n" + errorMsg + " --->\n\n\n\n \"" + errMsg + "\" \n\n\n\n " + stackTrace;

		Exception c = new Exception(errorMsg);
		ErrorUtil.addVerificationFailure(c);

		// killprocess();
		IsBrowserPresentAlready = false;
		throw new Exception(c);

	}
	/***** Wait till text box dont have value entered correctly ***/
	public static String fngWaitTillTextBoxDontHaveValue(String OR_objectName, String attributeName) throws Throwable {
		String EnteredClient = "";
		int i = 1;
		while (true) {
			i++;
			if (EnteredClient.equalsIgnoreCase("")) {
				Thread.sleep(500);

				EnteredClient = fngGetORObjectX(OR_objectName).getAttribute(attributeName).trim();
				// fnpMymsg("  ---Singh----value is---"+EnteredClient);
				if (i > (Integer.parseInt(CONFIG.getProperty("genMax_waitTime")) * 2)) {

					break;
				}
			} else {
				break;
			}
		}

		return EnteredClient;
	}
	public static void fngProcessAI_ISR(String dataTableXpathNameInOR, String headerTableXpathNameInOR, String ActionItemName, String statusToChange, String saveNCloseBtnInOR,
			String ReassignTo) throws Throwable {
		try {

			// ***************Process the pending Action item***********

			//
			fnpMymsg(" Going to " + statusToChange + "  the pending Action item ---" + ActionItemName);
			fngWaitForVisible(dataTableXpathNameInOR);

			int ActionItemColIndex = fngFindColumnIndex(headerTableXpathNameInOR, "Action Items");

			int itemDescColIndex = fngFindColumnIndex(headerTableXpathNameInOR, "Action Item Name");

			int actionItemInfoRowNo = fngFindRow(dataTableXpathNameInOR, ActionItemName, itemDescColIndex);
			String actionItemNo = fngFetchFromTable(dataTableXpathNameInOR, actionItemInfoRowNo, ActionItemColIndex);
			int start = 1;
			while (actionItemNo.isEmpty()) {
				Thread.sleep(1000);
				actionItemNo = fngFetchFromTable(dataTableXpathNameInOR, actionItemInfoRowNo, ActionItemColIndex);

				if (start > 50) {
					break;
				}
			}
			Thread.sleep(500);

			fngClickALinkInATable(actionItemNo);

			fnpMymsg("Clicked on  Action Item '" + ActionItemName + "' in Table having no. is '" + actionItemNo + "' .");

			fnpLoading_wait();

			if (!(ReassignTo.equalsIgnoreCase(""))) {
				String alreadyAssingee = fnpGetORObjectX("AssignedToLabelValue").getText().trim();
				if (alreadyAssingee.equalsIgnoreCase(ReassignTo)) {
					fnpMymsg("@  - default value is same as expected, so returning back.");
				} else {
					fnpClick_OR("ReassignAILkpBtn");
					// fnpSearchNSelectFirstRadioBtn(String[][] lookup , int
					// level) //we can use this later when making independent of
					// lookup text box no.
					fnpSearchNSelectFirstRadioBtn(2, ReassignTo, 1);
				}

			}

			fnpWaitForVisible("ActionItemTab_ChangeStatusPFList");
			String defaultValue = fnpGetORObjectX("ActionItemTab_ChangeStatusPFList").getText().trim().toLowerCase();
			fnpPFList("ActionItemTab_ChangeStatusPFList", "ActionItemTab_ChangeStatusPFListOptions", statusToChange);

			// fnpGetORObjectX(saveNCloseBtnInOR).click(); //commented on
			// 03-10-2017 as error message on click after loading is not
			// capturing
			fnpClick_OR(saveNCloseBtnInOR);

			/************
			 * It will come always for changing from created to completed from
			 * today onwards 11-07-2017 against IPM-6594
			 ********************/

			if (statusToChange.equalsIgnoreCase("completed")) {

				fnpClickInDialog_OR("WOISR_AIConfirmationBtn");
			}

			/***************************************************************************************/

			fnpCheckError("while Going to " + statusToChange + "  the pending Action item ---" + ActionItemName);

			fnpWaitForVisibleHavingMultipleElements("TopMessage3");
			String SuccessfulMsg = fnpGetText_OR("TopMessage3");
			fnpMymsg("Top Message after " + statusToChange + "  the pending Action item '" + ActionItemName + "' ----" + SuccessfulMsg);

			Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"), "Top message does not contain 'success' word, so might be action of " + statusToChange
					+ "  the pending Action item '" + ActionItemName + "'  is NOT successful.");

			int StatusColIndex = 0;
			String Status_ActionItemTable = null;
			int whileIchance = 0;
			while (true) {

				try {
					whileIchance++;
					StatusColIndex = fnpFindColumnIndex(headerTableXpathNameInOR, actionItemTableStatusColName);
					Status_ActionItemTable = fnpFetchFromTable(dataTableXpathNameInOR, actionItemInfoRowNo, StatusColIndex);
					fnpMymsg("Current Status of this action item '" + ActionItemName + "' is ---" + Status_ActionItemTable + "  ---- after chances ---" + whileIchance);

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

				fnpCheckError("while Going to " + statusToChange + "  the pending Action item ---" + ActionItemName);

			}

			Assert.assertTrue(Status_ActionItemTable.toLowerCase().contains(statusToChange.toLowerCase()), "After " + statusToChange + " the '" + ActionItemName
					+ "' action item , status has not been changed  to '" + Status_ActionItemTable + "' . ");

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
	public static void fngClickALinkInATable(String linkNo) throws Throwable {

		String Link_Xpath = "//td[@role='gridcell']/a[text()='" + linkNo + "']";
		fngWaitTillVisiblityOfElementNClickable_NOR(Link_Xpath);
		fngGetORObjectX_WhenMultipleObjectsHavingSameXpath_NOR(Link_Xpath).click();
		fngCheckError("");
		
	}
	/****
	 * Wait till Element gets visible and in clickable state when object not in
	 * OR
	 ****/

	public static void fngWaitTillVisiblityOfElementNClickable_NOR(String Xpath) throws Throwable {

		WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(CONFIG.getProperty("genMax_waitTime")));
		int retries = 0;
		while (true) {
			try {

				wait.until(ExpectedConditions.elementToBeClickable(fngGetORObjectX_WhenMultipleObjectsHavingSameXpath_NOR(Xpath)));
				return;
			} catch (org.openqa.selenium.StaleElementReferenceException e) {
				if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
					retries++;
					fnpMymsg(retries + "In staleElementException catch block of fnpWaitTillVisiblityOfElementNClickable_NOR function for " + Xpath);
					continue;
				} else {
					throw e;
				}
			} catch (Throwable t) {
				if (retries < 2) {
					retries++;
					fnpMymsg(retries + "In try catch block of fnpWaitTillVisiblityOfElementNClickable_NOR function for " + Xpath);
					continue;
				} else {
					throw t;
				}

			}

		}

	}
	/***********
	 * Function to find and return the object using Xpath which are present in
	 * OR
	 *************/
	public static WebElement fngGetORObjectX_WhenMultipleObjectsHavingSameXpath_NOR(String Xpath) throws Exception {
		int retries = 0;
		WebElement requiredObj = null;
		try {

			if (driver.findElements(By.xpath(Xpath)).size() > 0) {

				List<WebElement> objList = fngGetORObject_list_NOR(Xpath, 2); 

				for (int i = 0; i < objList.size(); i++) {
					if ((objList.get(i).isDisplayed()) & (!((objList.get(i).getText()).equalsIgnoreCase("")))) {

						requiredObj = objList.get(i);
						break;
					}
				}

			} else {
				throw new Exception("Element is not Visible having Xpath  [ " + Xpath + " ]");
			}

		} catch (org.openqa.selenium.StaleElementReferenceException s) {
			if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
				Thread.sleep(1000);
				retries++;
				fnpMymsg(retries + "In staleElementException catch block of fnpGetORObjectX_WhenMultipleObjectsHavingSameXpath function for " + Xpath);
				// continue;
			}
		}

		catch (org.openqa.selenium.InvalidSelectorException is) {
			if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
				Thread.sleep(1000);
				retries++;
				fnpMymsg(retries + "In org.openqa.selenium.InvalidSelectorException catch block of fnpGetORObjectX_WhenMultipleObjectsHavingSameXpath function for " + Xpath);
				// continue;
			} else {
				throw is;
			}
		}

		catch (Throwable t) {
			// fnpDesireScreenshot("NotPresent" + Xpath); //commented on
			// 27-09-2017 because image is not png as xpath have some non
			// acceptable value
			fnpDesireScreenshot("NotPresent");
			throw new Exception(t.getMessage() + "   So,Unable to find element with name [" + Xpath + "] ,plz see screenshot [NotPresent" + Xpath + SShots + "]");

		}

		return requiredObj;
	}
	public static List<WebElement> fngGetORObject_list_NOR(String Xpath, int maxTimeInSeconds) throws Exception {

		int retries = 0;
		while (true) {
			try {

				driver.manage().timeouts().implicitlyWait(maxTimeInSeconds, TimeUnit.SECONDS);
				return driver.findElements(By.xpath(Xpath));

			} catch (org.openqa.selenium.StaleElementReferenceException s) {
				if (retries < maxTimeInSeconds) {
					Thread.sleep(1000);
					retries++;
					fnpMymsg(retries + "In staleElementException catch block of fnpGetORObject_list 2 arguments  function for " + Xpath);
					// continue;
				} else {
					throw s;
				}
			}

			catch (org.openqa.selenium.InvalidSelectorException is) {
				if (retries < 10) {
					Thread.sleep(1000);
					retries++;
					fnpMymsg(retries + "In InvalidSelectorException catch block of fnpGetORObject_list 2 arguments  function for " + Xpath);
					// continue;
				} else {
					throw is;
				}
			} catch (Throwable t) {
				if (retries < maxTimeInSeconds) {
					// Thread.sleep(1000);
					retries++;
					fnpMymsg(retries + "In Throwable catch block of fnpGetORObject_list 2 arguments  function for " + Xpath);

				} else {
					fnpDesireScreenshot("NotPresent" + Xpath);
					throw new Exception(t.getMessage() + "   So,Unable to find element with name [" + Xpath + "] ,plz see screenshot [NotPresent" + Xpath + SShots + "]");
				}
			}

			finally {
				driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);
			}
		}

	}
	/*********** Function to count no. of rows in a table *************/
	public static int fngCountRowsInTable(String TableXpathKey) throws Exception {
		try {


			String newXpath = OR_SUS.getProperty(TableXpathKey) + "/tr";
			int NoOfRows = fngGetORObject_list_NOR(newXpath, Integer.parseInt(CONFIG.getProperty("implicitWaitTime"))).size();

			if (NoOfRows == 1) {

				if (fnpCheckElementPresenceImmediately_NotInOR(OR_SUS.getProperty(TableXpathKey) + "/tr/td[2]")) {
					// nothing to do

				} else {
					NoOfRows = NoOfRows - 1;

				}

			}

			fnpMymsg("@  ---Total rows are---" + NoOfRows);
			return NoOfRows;

		} catch (Throwable t) {
			fnpDesireScreenshot("CountRowFailFrom" + TableXpathKey);
			String errorMsg = t.getMessage();
			throw new Exception("Unable to count rows from  Table having xpath [" + TableXpathKey + "],plz see screenshot [CountRowFailFrom" + TableXpathKey + SShots
					+ "]and this is due to --" + errorMsg);

		}
	}
	/***********
	 * Function to find and return the object using Xpath which are present in
	 * OR
	 *************/
	public static WebElement fngGetORObjectX_WhenMultipleObjectsHavingSameXpath_NOR_immediately(String Xpath, String nameOfField) throws Exception {
		int retries = 0;
		WebElement requiredObj = null;
		try {

			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			if (driver.findElements(By.xpath(Xpath)).size() > 0) {

				List<WebElement> objList = fnpGetORObject_list_NOR(Xpath, 2);

				for (int i = 0; i < objList.size(); i++) {
					if ((objList.get(i).isDisplayed()) & (!((objList.get(i).getText()).equalsIgnoreCase("")))) {

						requiredObj = objList.get(i);
						break;
					}
				}

			} else {
				throw new Exception("Element is not Visible having Xpath  [ " + Xpath + " ]");
			}

		} catch (org.openqa.selenium.StaleElementReferenceException s) {
			if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
				Thread.sleep(1000);
				retries++;
				fnpMymsg(retries + "In staleElementException catch block of fnpGetORObjectX_WhenMultipleObjectsHavingSameXpath function for " + Xpath);
				// continue;
			}
		}

		catch (org.openqa.selenium.InvalidSelectorException is) {
			if (retries < Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
				Thread.sleep(1000);
				retries++;
				fnpMymsg(retries + "In org.openqa.selenium.InvalidSelectorException catch block of fnpGetORObjectX_WhenMultipleObjectsHavingSameXpath function for " + Xpath);
				// continue;
			} else {
				throw is;
			}
		}

		catch (Throwable t) {

			throw new Exception(t.getMessage() + "   So,Unable to find element with name [" + nameOfField + "] ,plz see screenshot [NotPresent" + nameOfField + SShots + "]");
		}

		finally {
			driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);

			return requiredObj;
		}

	}
	public static void fngCallProcedure_DELETE_WO_AUTOMATION_DATA_SustainabilityProfile(String woNo) throws Throwable {
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

			connection.close();

		} catch (SQLException e) {
			fnpMymsg("*********************************************** Either connection Failed or some different error with Database,   Getting Error >>  " + e.getMessage().trim());
			fnpMymsg("=========================================================================================================================================");
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		fnpMymsg("******************");

		// return rate;
	}
public static void fngSelectTechnicalReviewer() throws Throwable{
		
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
		/*String technicalReviewer=null;
		String EnteredEmployeeCode=null;
		boolean masking="true".equals(OR_SUS.getProperty("ClientName_Masking"));
		if(masking){
			fnpSearchNSelectFirstRadioBtn(1, (String) hashXlData.get("Masking_EmpCode"), 1);
			fnpMymsg("Just after  inserting value of Technical Reviewer");
			EnteredEmployeeCode = fngWaitTillTextBoxDontHaveValue("AccountManagerTxtBox", "value");
			Assert.assertTrue(EnteredEmployeeCode.contains((String) hashXlData.get("Masking_EmpCode")), "Employee Code is not selected properly from lookup");
			fnpMymsg(" Employee Code is properly selected from client lookup");
		}else{
			fnpSearchNSelectFirstRadioBtn(2, (String) hashXlData.get("AccountManager"), 1);
			fnpMymsg("Just after  inserting value of Technical Reviewer");
			EnteredEmployeeCode = fngGetORObjectX("AccountManagerTxtBox").getAttribute("value");
			technicalReviewer = fngWaitTillTextBoxDontHaveValue("AccountManagerTxtBox", "value");
			Assert.assertTrue(technicalReviewer.contains((String) hashXlData.get("AccountManager")), "Account Manager is not selected properly from lookup");
			fnpMymsg(" Account Manager is properly selected from client lookup");
		}*/
		
		String technicalReviewer;
		String technicalReviewer_code;
		if (  (loginAsFullName==null) ||loginAsFullName.equalsIgnoreCase("") ) {
			technicalReviewer=(String) hashXlData.get("TechReviewer_FullName").trim();
			technicalReviewer_code=(String) hashXlData.get("TechReviewer_Code").trim();
			
		} else {
			technicalReviewer=loginAsFullName;
			technicalReviewer_code=loginUser_code;
		}
		
		
		
		if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
			fnpSearchNSelectFirstRadioBtn(1, technicalReviewer_code, 1);
		}else{
			fnpSearchNSelectFirstRadioBtn(2, technicalReviewer, 1);
		}
		

		String enteredTechnicalReviewer = fngGetORObjectX("ISR_InfoTab_TechnicalReviewer_txtbx").getAttribute("value");
		enteredTechnicalReviewer = fnpWaitTillTextBoxDontHaveValue("ISR_InfoTab_TechnicalReviewer_txtbx", "value");

		if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
			Assert.assertTrue(enteredTechnicalReviewer.contains(technicalReviewer_code), "Technical Reviewer is not selected properly from lookup because actual value is ---'"+enteredTechnicalReviewer+"' and expected is --'"+technicalReviewer_code+"'.");//technicalReviewer_code
		}else{
			Assert.assertTrue(enteredTechnicalReviewer.contains(technicalReviewer), "Technical Reviewer  is not selected properly from lookup because actual value is ---'"+enteredTechnicalReviewer+"' and expected is --'"+technicalReviewer+"'.");
		}
		
		fnpClick_OR("ISRSFTabSaveBtn");
		/*********************************************************************/
	}
	public static void fngSwitchUserToProcessTechnicalReviewAI() throws Throwable{
		String techReviewer_user=(String) hashXlData.get("TechReviewer_UserName").trim();
		fnpSwitchUser(techReviewer_user) ;
	}

	public static void fngSearchWO_and_ReachToTaskTab_OpenCertExpandIcon_ISR() throws Throwable{
		
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
	/**************** Taking full screenshot with url, otherwise if we use this
	 ((TakesScreenshot) driver).getScreenshotAs then url top section is not
	 being captured 24-03-2017 ***************/
	public static void fngDesireScreenshot(String ImageName) {

		try {
			SShots = SShots + 1;
			ImageName = fnpRemoveInvalidCharactersFromSavingFileName(ImageName);
			fnpMymsg("error screenshot name is---" + ImageName);
			String updatedImageName = ImageName + "--"+ SShots + ".png";
		//	FileUtils.forceMkdir(new File((System.getProperty("user.dir") + screenshots_path + "//screenshots_" + currentSuiteCode + "//" + currentScriptCode + "//")));
			
			
			File screenshot_folder = new File((System.getProperty("user.dir") + screenshots_path + "//screenshots_"+ currentSuiteCode + "//" + currentScriptCode ));
			if (!screenshot_folder.exists()) {
				FileUtils.forceMkdir(screenshot_folder);
			}
			
			
			
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			Rectangle screenRectangle = new Rectangle(screenSize);
			Robot robot = new Robot();
			BufferedImage image = robot.createScreenCapture(screenRectangle);

			ImageIO.write(image, "png", new File(
					(System.getProperty("user.dir") + screenshots_path + "//screenshots_" + currentSuiteCode + "//" + currentScriptCode + "//" + updatedImageName)));
		} catch (Throwable t) {
			t.printStackTrace();
		}

		


	}
	

	
	// added from proposal base
	
	
public static void fnpCommonProcessQuestionnairesSet_ISR() throws Throwable{
		
		
		fnpMymsg("");
		fnpMymsg(" Now going to insert the values in Questionnaire .");
		
		
		boolean unMarkSectionButtonClick = false;
		boolean MarkSectionButtonDisplay = false;
		
		fnpMymsg("");
		fnpMymsg("");
		String classValue;
		do{

			//below is work both for tabview in ISR and without tabview in proposal
			String questionnaire_xpath=".//div[contains(@id,'mainForm:')][contains(@id,':IQMAINQUES_')][contains(@id,'_content')]/table/tbody/tr[@class='ui-widget-content']/td/table/tbody/tr/td/label";
			
			//boolean unMarkSectionButtondisplay = fnpCheckElementDisplayed(OR_SUS.getProperty("Prop_Questionnaire_UnmarkSectionNA_Btn"));
			
			List<WebElement> questionnaireList = driver.findElements(By.xpath(questionnaire_xpath));
			
			int totalQuestionnaire=questionnaireList.size();

			String question;
			String questionNo;
			for (int i = 0; i < questionnaireList.size(); i++) {
				question=questionnaireList.get(i).getText();
				

			//if(question.toLowerCase().contains("For Fully Remote Audits".toLowerCase()) && unMarkSectionButtondisplay==true ) {
					
//				
//				for (int j = 0; j <= 5; j++) {
//					if (driver.findElements(By.xpath(OR_SUS.getProperty("Prop_Questionnaire_UnmarkSectionNA_Btn"))).size() > 0) {
//						//unMarkSectionButtonClick = true;
//						fnaTake_Screen_Shot("UnMarkSectionButton");
//						throw new Exception("UnMark Section Button is display instead of Mark section N/A button.");
//					} 
//
//				}
				
				
				
				
		if(question.toLowerCase().contains("For Special Audits:".toLowerCase()) && MarkSectionButtonDisplay==false ) {
					
					for (int a = 0; a <= 120; a++) {
						if (driver.findElements(By.xpath(OR_SUS.getProperty("Prop_Questionnaire_markSectionNA_Btn"))).size() > 0) {
							MarkSectionButtonDisplay = true;
							fnpClick_OR("Prop_Questionnaire_markSectionNA_Btn");
							Thread.sleep(500);
							
							for (int j = 0; j <= 120; j++) {
								if (driver.findElements(By.xpath(OR_SUS.getProperty("Prop_Questionnaire_UnmarkSectionNA_Btn"))).size() > 0) {
									unMarkSectionButtonClick = true;
									break;
								} else {
									Thread.sleep(1000);
								}
		
								if (j == 120) {
								fnaTake_Screen_Shot("UnMarkSectionButton");
								throw new Exception("UnMark Section Button is not coming after 120 sec. ");
								}

							}
							
							
							if(driver.findElements(By.xpath(OR_SUS.getProperty("question_RadioButton_xpath"))).size() > 0) {
								fnpMymsg("Radio buttons are disable which are expected.");
								break;
							}else {
								fnaTake_Screen_Shot("Radio_button_editable");
								throw new Exception("Radio button is editable .......");
							}
							


					} else {
					 Thread.sleep(1000);
					//throw new Exception("Mark Section Button is not visible on the screen.");
					}

				if (a == 120) {
					fnaTake_Screen_Shot("Mark_Section_Button");
					throw new Exception("Mark Section Button is not coming after 120 sec. ");
				}

			}
					
		}
				
				
                // IPM-15127
				
				
//				if(question.toLowerCase().contains("For Fully Remote Audits".toLowerCase()) && unMarkSectionButtonClick==false ) {
//					
//					for (int a = 0; a <= 120; a++) {
//						if (driver.findElements(By.xpath(OR_SUS.getProperty("Prop_Questionnaire_UnmarkSectionNA_Btn"))).size() > 0) {
//							unMarkSectionButtonClick = true;
//							fnpClick_OR("Prop_Questionnaire_UnmarkSectionNA_Btn");
//							Thread.sleep(500);
//							
//							for (int j = 0; j <= 120; j++) {
//								if (driver.findElements(By.xpath(OR_SUS.getProperty("Prop_Questionnaire_markSectionNA_Btn"))).size() > 0) {
//									MarkSectionButtonDisplay = true;
//									break;
//								} else {
//									Thread.sleep(1000);
//								}
//		
//								if (j == 120) {
//								fnaTake_Screen_Shot("MarkSectionButton");
//								throw new Exception("Mark Section Button is not coming after 120 sec. ");
//								}
//
//							}
//							
//							
//							 if (MarkSectionButtonDisplay) { 
//							 break;
//							 }
//							 
//
//
//					} else {
//					 Thread.sleep(1000);
//					throw new Exception("UnMark Section Button is not visible on the screen.");
//					}
//
//				if (a == 120) {
//					fnaTake_Screen_Shot("Unmark_Button");
//					throw new Exception("UnMark Section Button is not coming after 120 sec. ");
//				}
//
//			}
//					
//		}
				
				
//		if(question.toLowerCase().contains("For Special Audits".toLowerCase())){
//			fnpClick_OR("Prop_Questionnaire_SaveNNextBtn");
//		}
				
				
//				if(question.toLowerCase().contains("For Stage 1/Readiness Reviews only:".toLowerCase())){
//					fnpClick_OR("Prop_Questionnaire_SaveNNextBtn");
//				}
			
				if(unMarkSectionButtonClick) 
				break;
				
				
				fnpMymsg("Question "+(i+1)+" is --"+question);
				questionNo=question.split(":")[0].trim();
				fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
				//fnpFillAnswerToPropQuestionnaireDynamically_processHiddenAlso(questionNo, "Yes"); //working fine for only radio button
				fnpFillAnswerToPropQuestionnaireDynamically_processHiddenAlso_new(questionNo); //I think it will work for all radio, first value in drop down and text box
						
				//fnpLoading_wait();
				fnpLoading_wait_CheckLoadingIconOnlyIniPulse();	
				
				questionnaireList = driver.findElements(By.xpath(questionnaire_xpath));
			}

			WebElement wb = fnpGetORObjectX_withoutCheckingElementClickable("Prop_Questionnaire_SaveNNextBtn");
			classValue = wb.getAttribute("class").trim();
			if (!(classValue.contains("ui-state-disabled"))) {

				
				WebElement saveNnextBtn = driver.findElement(By.xpath(OR.getProperty("Prop_Questionnaire_SaveNNextBtn")));
				Actions action = new Actions(driver);
				action.moveToElement(saveNnextBtn).build().perform();
	
				fnpClick_OR("Prop_Questionnaire_SaveNNextBtn");

				
				
					
				

			}

		//} while(fnpCheckElementDisplayedImmediately("Prop_Questionnaire_SaveNNextBtn"));
		} while(!(classValue.contains("ui-state-disabled")));
			


		if  (    (currentSuiteName.equalsIgnoreCase("ISR_suite"))   || (currentSuiteName.equalsIgnoreCase("SCFS_suite")) ||  (currentSuiteName.equalsIgnoreCase("SUS_suite"))) {   
/*			
			fnpLoading_wait_specialCase(10);
			fnpClick_OR("ISR_Prop_Questionnaire_Save_btn");
			fnpLoading_wait_specialCase(10);
			fnpClickInDialog_OR("Prop_QuestionnaireCloseBtnInISR");
			*/
			
			
/*			
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", fnpGetORObjectX("Prop_QuestionnaireCloseBtnInISR"));
			Thread.sleep(1000*60);
			fnpLoading_wait();
			
			*/
			
			fnpClickInDialog_OR("Prop_QuestionnaireCloseBtnInISR");
			
			fnpWaitTillThisElementIsNoLongerDisplayedTillMaxWait_OR("Prop_QuestionnaireCloseBtnInISR", "After clicking Save&Close button in questionnaire, it is visible after 300 seconds", 300);
		} else {
			fnpClickInDialog_OR("Prop_QuestionnaireCloseBtn");
		}
		
		//}

	
	
}


//Question No,Answer,AnswerFieldType,AnswerFieldIndexAtThispointOfTime
	public static void fnpFillAnswerToPropQuestionnaireDynamically_processHiddenAlso_new(String questNo) throws Throwable {

		fnpMymsg("Going to select the value  for Question --" + questNo);


		String questXpath = ".//label[contains(text(),'" + questNo + " :')]";
	//	String questXpath = ".//label[contains(normalize-space(text()),'" + questNo + " :')]";
	
		WebElement quest=null;
		try{
		  quest = driver.findElement(By.xpath(questXpath));
		}		
		catch(org.openqa.selenium.NoSuchElementException  nse){
			fnpMymsg("Might be element with this xpath '"+".//label[contains(text(),'" + questNo + " :')]' is not present or visible.");
			throw new Exception("Might be element with this xpath '"+".//label[contains(text(),'" + questNo + " :')]' is not present or visible.");
		}
		


		Actions action0 = new Actions(driver);
		 action0.moveToElement(quest).doubleClick().build().perform();
		//action0.moveToElement(fnpGetORObjectX___NOR(questXpath)).doubleClick().build().perform();
		Thread.sleep(1000);

		 quest = driver.findElement(By.xpath(questXpath));
	
			Actions action = new Actions(driver);

			/*
			 * action.moveToElement(quest).build().perform(); quest.sendKeys(Keys.ARROW_DOWN);
			 */
			action.moveToElement(quest).build().perform();
		//	quest.sendKeys(Keys.ARROW_DOWN);
			
			if (!browserName.equalsIgnoreCase("chrome")) 
				quest.sendKeys(Keys.ARROW_DOWN);


		
		//fnpMymsg(" Complete Question is this--" + fnpGetText_NOR(questXpath));
		//quest = fnpGetORObjectX___NOR(questXpath);
		quest = driver.findElement(By.xpath(questXpath));
		String questid = quest.getAttribute("id");
	
		
		String answerListXpath = ".//tr/td/label[starts-with(text(),'" + questNo+ " :')]/../../following-sibling::tr/td/div[contains(@class,'ui-selectonemenu ui-widget')]/label";
		
		
		String answerRadioXpath = ".//tr/td/label[starts-with(text(),'" + questNo+ " :')]/../../following-sibling::tr/td/table[contains(@class,'ui-selectoneradio ui-widget')]";
		/*String answerTextBoxXpath = ".//tr/td/label[starts-with(text(),'" + questNo
				+ " :')]/../../following-sibling::tr/td/input[contains(@class,'ui-inputfield ui-inputtext')]";
	*/String answerTextBoxXpath = ".//tr/td/label[starts-with(text(),'" + questNo
			+ " :')]/../../following-sibling::tr/td/*[contains(@class,'ui-inputfield ui-inputtext')]";
	
		
		
		
		String answervalue;
		boolean selectedProperly = false;
		String finalValueToBeSelect = "";
		if (fnpCheckElementPresenceImmediately_NotInOR(answerListXpath)) {
			//fnpWaitForVisible_NotInOR(answerListXpath);
			int liNo=1;
			fnpMymsg("List is present for Question--" + questNo);
			String p;
			WebElement oList;
			try {
				 oList = driver.findElement(By.xpath(answerListXpath));
				//oList = fnpGetORObjectX___NOR(answerListXpath);
				p = oList.getAttribute("id");
			} catch (StaleElementReferenceException s) {
				//WebElement saveNnextBtn = fnpGetORObjectX("Prop_Questionnaire_SaveNNextBtn");
				WebElement saveNnextBtn = driver.findElement(By.xpath((OR.getProperty("Prop_Questionnaire_SaveNNextBtn"))));

				fnpMymsg("As question is not visible properly, so going to move down in If block");
				 action = new Actions(driver);
				action.moveToElement(saveNnextBtn).build().perform();
			//	saveNnextBtn.sendKeys(Keys.ARROW_DOWN);
				if (!browserName.equalsIgnoreCase("chrome")) 
					quest.sendKeys(Keys.ARROW_DOWN);
				// Thread.sleep(2000);
				// Thread.sleep(500);
				// oList = driver.findElement(By.xpath(answerListXpath));
				oList = fnpGetORObjectX___NOR(answerListXpath);
				p = oList.getAttribute("id");

			}

		//	fnpMymsg("  ---id for list is-" + p);

			// String labelid = p + "_label";

			String pp = p.replace("_label", "");
			pp = pp.trim();
			String labelid = pp.trim() + "_label";
			// oList.click();
			int timer = 0;
			while (true) {
				try {
					// fnpWaitTillVisiblityOfElementNClickable(answerListXpath);
					 oList = driver.findElement(By.xpath(answerListXpath));
					//oList = fnpGetORObjectX___NOR(answerListXpath);
					oList.click();
				//	fnpLoading_wait();
					break;
				} catch (StaleElementReferenceException s) {
					Thread.sleep(1000);
					timer = timer + 1;
					if (timer > Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))) {
						break;
					}
				}
			}
			Thread.sleep(1500);

			// String panelId = p + "_panel";
			String panelId = pp.trim() + "_panel";
			//String listValue = ".//*[@id='" + panelId + "']" + "/div/ul/li[@data-label='" + answervalue + "']";
			String listValue = ".//*[@id='" + panelId + "']" + "/div/ul/li[" + liNo + "]";
			int iCounter = 1;
			while (true) {
				try {
					
					int totalValues = driver.findElements(By.xpath(".//*[@id='" + panelId + "']"+ "/div/ul/li")).size();
					if (!(totalValues > 0)) {
						throw new Exception("Drop down is blank. " );

					}

					if ((totalValues == 1)) {
						String onlyGivenValue = driver.findElement(By.xpath(".//*[@id='" + panelId + "']"+ "/div/ul/li[1]")).getText();
						if ((onlyGivenValue.toLowerCase().contains("select")) || (onlyGivenValue.trim().equalsIgnoreCase(""))) {
							throw new Exception("Drop down is blank --" );
						}

					}

					listValue = ".//*[@id='" + panelId + "']" + "/div/ul/li[" + liNo + "]";

					fnpWaitTillVisiblityOfElementNClickable_NOR_Immediately(listValue);


					String valueToBeSelect = fnpGetText_NOR(listValue);
					if ((valueToBeSelect.toLowerCase().contains("select")) || (valueToBeSelect.trim().equalsIgnoreCase(""))) {
						listValue = ".//*[@id='" + panelId + "']" + "/div/ul/li[" + (liNo + 1) + "]";
					} else {
						listValue = ".//*[@id='" + panelId + "']" + "/div/ul/li[" + liNo + "]";
					}

					finalValueToBeSelect = driver.findElement(By.xpath(listValue)).getText();
					driver.findElement(By.xpath(listValue)).click();

					Thread.sleep(1000);

					fnpMymsg("@ -  Now going to fetch the selected value.");
					//String FinalSelectedValue = fnpGetText_OR(XpathKey).trim();
					String FinalSelectedValue =fnpGetText_NOR(answerListXpath);
					fnpMymsg("@ -  Fetched/Selected value is ---" + FinalSelectedValue);
					String value2 = StringUtils.normalizeSpace(FinalSelectedValue);


					if (!(((finalValueToBeSelect.contains(FinalSelectedValue))) || ((finalValueToBeSelect.contains(value2))))) {
						msg="@ - '" + finalValueToBeSelect + "'  is NOT selected properly in  DropDown as expected is" + "--'" + finalValueToBeSelect + "' and actual is --'"
								+ FinalSelectedValue + "' .";
						fnpMymsg(msg);
						throw new Exception(msg);
					} else {
						fnpMymsg("@ - '" + finalValueToBeSelect + "'  is  selected properly in  DropDown as expected is" + "--'" + finalValueToBeSelect + "' and actual is --'"
								+ FinalSelectedValue + "' .");
						selectedProperly = true;
						break;
					}
					
					
					
					
				} catch (Throwable t) {
					

					
					
					//fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
					if (t.getMessage().contains(CustomizeErrorMsgWhenLoadingNotOver.substring(0, 28))){
						throw t;
					}
					
					
					if (t.getMessage().contains("Value   is not matched with DropDown Value.")) {
						if (iCounter < 3) {
							iCounter++;
							String newXpath=".//[@id='"+p+"']";
							oList = driver.findElement(By.xpath(answerListXpath));
							
							fnpMymsg("Here assumption is that list is already expanded so first close and then open it again.");
							//fnpClick_NOR_withoutWait(newXpath);
							oList.click();
							//Thread.sleep(1000);
							fnpLoading_wait();
							//fnpClick_NOR_withoutWait(newXpath);
							oList.click();
							fnpLoading_wait();
							
						} else {
							throw t;

						}
					} else {
						WebElement saveNnextBtn = driver.findElement(By.xpath((OR.getProperty("Prop_Questionnaire_SaveNNextBtn"))));

						Actions action11 = new Actions(driver);
						action11.moveToElement(saveNnextBtn).build().perform();
					//	saveNnextBtn.sendKeys(Keys.ARROW_DOWN);
						if (!browserName.equalsIgnoreCase("chrome")) 
							quest.sendKeys(Keys.ARROW_DOWN);
						Thread.sleep(1000);
						//driver.findElement(By.id(p)).click();
						
						
						String newXpath=".//[@id='"+p+"']";
						if (!(fnpCheckElementDisplayedImmediately(newXpath))) {
							fnpMymsg("Here assumption is that list is not expanded.");
							//fnpClick_NOR_withoutWait(newXpath);
							oList = driver.findElement(By.xpath(answerListXpath));
							oList.click();
							fnpLoading_wait();
						} else {
							// This will work when try again in 2nd chance, first close
							// already opened one and then click again to expand it.
							fnpMymsg("Here assumption is that list is already expanded so first close and then open it again.");
							//fnpClick_NOR_withoutWait(newXpath);
							oList = driver.findElement(By.xpath(answerListXpath));
							oList.click();
							fnpLoading_wait();
							//Thread.sleep(1000);
							//fnpClick_NOR_withoutWait(newXpath);
							oList.click();
							fnpLoading_wait();
						}

						Thread.sleep(500);
						
						
						
						//fnpLoading_wait();
					//	Thread.sleep(1000);
					//	fnpLoading_wait();
						if (iCounter < 3) {
							iCounter++;
						} else {
							break;

						}
					}
					
					
					
					
				}
			}

			// Thread.sleep(1000);
			String selectedlistValue = "";
			try {
				selectedlistValue = driver.findElement(By.xpath(answerListXpath)).getText();
			} catch (Throwable t) {
				// Thread.sleep(5000);
				Thread.sleep(2000);
				selectedlistValue = driver.findElement(By.xpath(answerListXpath)).getText();
			}
			fnpMymsg("Value has been selected ie. '" + selectedlistValue + "'  for Question --" + questNo);

			Assert.assertEquals(selectedlistValue, finalValueToBeSelect, "Fail due to Actual '" + selectedlistValue + "' and expected '" + finalValueToBeSelect + "' are not matched.");

			fnpMymsg("");
			fnpMymsg("");

		}

		if (fnpCheckElementPresenceImmediately_NotInOR(answerRadioXpath)) {
			fnpMymsg("Radio is present for Question--" + questNo);
			
/*			answervalue="Yes";
			String answerRadioXpathLabel = answerRadioXpath + "/tbody/tr/td/label[contains(text(),'" + answervalue + "')]";
			 */
			
			//String answerRadioXpathLabel = answerRadioXpath + "/tbody/tr/td[2]/label";  // here 2 in td because first td for radio and second td for its label
			String answerRadioXpathLabel = answerRadioXpath + "/tbody/tr/td[1]/label";  // both radio and its label is in same td in new primefaces libraray in June/July 2020
			int whileloopCount = 0;
			while (true) {

				// Thread.sleep(2000); commented on 19th feb
				String checkedRadioLabelForAttribute;
				String checkedLabel = null;

						try {
							WebElement fac = driver.findElement(By.xpath(answerRadioXpathLabel));
							//WebElement fac = fnpGetORObjectX___NOR(answerRadioXpathLabel);
							 action = new Actions(driver);
							action.moveToElement(fac).doubleClick().build().perform();							
							//action.moveToElement(fac).build().perform();
							
/*							action.moveToElement(fac).click().build().perform();
							action.moveToElement(fac).click().perform();				
							action.moveToElement(fac).click().doubleClick().build().perform();
							
							*/
							
							
							
						//	Thread.sleep(1000);		
							fnpLoading_wait();
							
							// commented below on 02-11-2020
/*							fac.click();  // because This is working on my machine but above is working on all machine
							fnpLoading_wait();
							*/
							
							
							
							
						//	 Thread.sleep(1000);
					
						//	 Thread.sleep(1000); //commented on 19th feb
							//Thread.sleep(1000);
						//	checkedRadioLabelForAttribute = driver.findElement(By.xpath(answerRadioXpathLabel)).getAttribute("for");
						//	checkedRadioLabelForAttribute = fnpGetORObjectX___NOR(answerRadioXpathLabel).getAttribute("for");
									while (true) {
										Thread.sleep(500);
										checkedRadioLabelForAttribute = driver.findElement(By.xpath(answerRadioXpathLabel)).getAttribute("for");
										//checkedRadioLabelForAttribute = fnpGetORObjectX___NOR(answerRadioXpathLabel).getAttribute("for");						
										String checkedRadioXpath2 = ".//td/div/div/input[@id='" + checkedRadioLabelForAttribute + "']";
										WebElement checkedRadio = driver.findElement(By.xpath(checkedRadioXpath2));
									//	WebElement checkedRadio = fnpGetORObjectX___NOR(checkedRadioXpath2);
										checkedLabel = checkedRadio.getAttribute("checked");
										fnpMymsg("Checked attribute value is--" + checkedLabel);

										if (checkedLabel != null) {
											break;
				
										} else {
				
											whileloopCount++;
											if (whileloopCount < 3) {
												fnpMymsg("@@@    ---As checked attribute value is null so going to double click again--");
												action.moveToElement(fac).doubleClick().build().perform();
												//Thread.sleep(2000);
												fnpLoading_wait();
											} else {
												break;
											}
										}
										}

										if (checkedLabel != null) {
											if (checkedLabel.equalsIgnoreCase("true")) {
												fnpMymsg("Radio button for question '" + questNo + "' has  been selected properly.");
												break;
											} else {
												// continue;
												fnpMymsg("  checked label is not true in question no.--" + questNo + " and checkedlabel value  is --" + checkedLabel);
												throw new Exception("  checked label is not true in question no.--" + questNo + " and checkedlabel value  is --" + checkedLabel);
											}
					
										} else {
											// continue;
											fnpMymsg("  checked label is null in question no.--" + questNo + " and checkedlabel value  is --" + checkedLabel);
											throw new Exception("  checked label is null in question no.--" + questNo + " and checkedlabel value  is --" + checkedLabel);
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
					// WebElement saveNnextBtn = fnpGetORObjectX("Prop_Questionnaire_SaveNNextBtn");
					
				//	WebElement saveNnextBtn = fnpGetORObjectX("Prop_Questionnaire_SaveNCloseBtn");// for last page if error thrown as saveNNext button not
																									// enabled there
					
					
					
					
					WebElement saveNnextBtn = null;
					
					if (fnpCheckElementDisplayedImmediately( "Prop_Questionnaire_SaveNCloseBtn")) {
						saveNnextBtn = fnpGetORObjectX( "Prop_Questionnaire_SaveNCloseBtn");// for
						action = new Actions(driver);
						action.moveToElement(saveNnextBtn).build().perform();
					} else {
						if (fnpCheckElementDisplayedImmediately( "Prop_QuestionnaireCloseBtnInISR")) {
							saveNnextBtn = fnpGetORObjectX("Prop_QuestionnaireCloseBtnInISR");// for
							action = new Actions(driver);
							action.moveToElement(saveNnextBtn).build().perform();
						}
						
					}
					
					
					
/*					
					action = new Actions(driver);
					action.moveToElement(saveNnextBtn).build().perform();
					*/
					
					
				//	saveNnextBtn.sendKeys(Keys.ARROW_DOWN);
					if (!browserName.equalsIgnoreCase("chrome")) 
						quest.sendKeys(Keys.ARROW_DOWN);
					Thread.sleep(1000);
					// saveNnextBtn.sendKeys(Keys.ARROW_DOWN);
					// Thread.sleep(1000);

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
		}

		if (fnpCheckElementPresenceImmediately_NotInOR(answerTextBoxXpath)) {
			answervalue="test";
			String insertedValue;
			fnpMymsg("Text box is present for Question--" + questNo);
		//	fnpWaitForVisible_NotInOR(answerTextBoxXpath);
			
			
		//	driver.findElement(By.xpath(answerTextBoxXpath)).sendKeys(answervalue);
			
			WebElement inputField = driver.findElement(By.xpath(answerTextBoxXpath));
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("arguments[0].setAttribute('value', '" + answervalue +"')", inputField);
			
			
			Thread.sleep(1000);
			try{
			fnpType(null,answerTextBoxXpath,answervalue );
			}catch(Throwable t){
				//we are not interested in catching the issue here, either javascript will work or this type method will work
			}
			Thread.sleep(1000);

			// commented on 19th feb
			/*
			 * if (fnpGetCurrRunningBrowserName().equalsIgnoreCase("IE")) { driver.findElement(By.xpath(answerTextBoxXpath)).click(); Thread.sleep(1000); }
			 */
			fnpMymsg("Value has been inserted and now going to verify whether right value is inserted or not.");

			insertedValue = driver.findElement(By.xpath(answerTextBoxXpath)).getAttribute("value");



			int whileloopCountTxtbox = 0;
			while (!insertedValue.equalsIgnoreCase(answervalue)) {
				whileloopCountTxtbox++;
				if (whileloopCountTxtbox < 3) {
					Thread.sleep(2000);
					insertedValue = driver.findElement(By.xpath(answerTextBoxXpath)).getAttribute("value");
				} else {
					break;
				}

			}
			fnpMymsg("Inserted value in text box is--" + insertedValue);
			Assert.assertEquals(insertedValue, answervalue, "Value in text box for this question '" + questNo + "' has NOT been inserted  properly.");
			fnpMymsg("Value in text box for this question '" + questNo + "' has  been inserted  properly.");

		}

	}


	public static void fnpMultipleSelectionFilter_dropdown_with_Ok_button(String fieldtriangleExpandIcon,String Ok_link,String value,String AllLabels_xpath) throws Throwable{
		fnpClick_OR(fieldtriangleExpandIcon);
		Thread.sleep(500);
		
/*		fnpClick_OR("AuditReportForLanguageFilterChkBx");
		Thread.sleep(2000);
		fnpClick_OR("AuditReportForLanguageFilterChkBx");
		*/

	//	fnpClick_OR("AddIndustryCod_Ok_link");
		
		//String OK_link=OR.getProperty("MultipleSelectionFilter_Ok_link_part1")+panelid+OR.getProperty("MultipleSelectionFilter_Ok_link_part2");
		//fnpClick_NOR(OK_link);

	//	fnpClick_OR("AuditReportForLanguage");


/*		String AuditReport_Language = (String) hashXlData.get("AuditReport_Language").trim();
		String AuditReport_LanguageArray[] = AuditReport_Language.split(":");
		int AuditReport_LanguageArraySize = AuditReport_LanguageArray.length;
		
		*/
		String valueCompleteValue = value.trim();
		String valueArray[] = valueCompleteValue.split(":");
		int valueArraySize = valueArray.length;
		
		
		

		String allLabelXpath = OR.getProperty(AllLabels_xpath);
		List<WebElement> allLabelElements = driver.findElements(By.xpath(allLabelXpath));
		int allLabelElementsSize = allLabelElements.size();

		WebElement hoverElement;
		Actions action;
		String currentValue;
		int selectionCounter = 0;
		for (int i1 = 0; i1 < allLabelElementsSize; i1++) {

			hoverElement = allLabelElements.get(i1);
			action = new Actions(driver);
			action.moveToElement(hoverElement).perform();
			// Thread.sleep(500);
			action.moveToElement(hoverElement).sendKeys(Keys.ARROW_DOWN).build().perform();
			// Thread.sleep(500);

			currentValue = hoverElement.getText();
			if (value.contains(currentValue)) {
				fnpMymsg("@  --now going to select/click on ---" + currentValue);
				Thread.sleep(1000);
				hoverElement.click();
				Thread.sleep(1000);
				selectionCounter++;
				fnpMymsg("@  -- selected/clicked on ---" + currentValue);
				fnpMymsg("@  -- selectionCounter value is just after clicked '" + currentValue + "' is -- -" + selectionCounter);
			}


			if (selectionCounter == (valueArraySize)) {
				fnpMymsg("@  -- selectionCounter value is--" + selectionCounter);
				fnpMymsg("@  -- valueArraySize value is--" + valueArraySize);
				fnpMymsg("both are equal");
				fnpClick_OR(Ok_link);
				break;
			}

		}
	}
}

	
	
	
	
	

