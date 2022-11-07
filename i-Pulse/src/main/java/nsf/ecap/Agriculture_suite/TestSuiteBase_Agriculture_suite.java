package nsf.ecap.Agriculture_suite;

//testing for new eclipse

import static nsf.ecap.config.IPULSE_CONSTANTS.*;
import static org.testng.Assert.assertEquals;

import java.io.File;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
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
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import nsf.ecap.Health_Science_suite.TestSuiteBase_HealthScience;
import nsf.ecap.ISR_suite.ISO9001_Single;
import nsf.ecap.ISR_suite.TestSuiteBase_ISR_suite;
import nsf.ecap.Proposals_suite.TestSuiteBase_Proposal;
import nsf.ecap.Work_Order_suite.NewNew_Draft_InReview_No_Fac;
import nsf.ecap.Work_Order_suite.TestSuiteBase;
import nsf.ecap.config.IPULSE_CONSTANTS;
import nsf.ecap.util.ErrorUtil;
import nsf.ecap.util.TestUtil;
import nsf.ecap.util.Xls_Reader;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.os.WindowsUtils;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import com.google.common.base.Throwables;
import com.google.common.base.Verify;

public class TestSuiteBase_Agriculture_suite extends TestSuiteBase {

	public static String runmodes[] = null;
	public static int count = -1;
	
	
	//public static Create_Membership CM_BS01;
	public  Create_Membership CM_BS01;
	public static String alreadyCreatedMembershipThroughFirstScript=null;
	
	//public static String iAgOption2FileName="GG-Option2-V0.25- 26-07aRT.xlsm";
	public static String iAgOption2FileName="GG-Option2-V0.25-26-07aRT.xlsm";
	
	public static String import_USTime=null;
	
	
	
	//public static String iAg_Certification_Autotest_FileName="PPU Test 1 GGFV51FA Audit.xlsm";
//	public static String iAg_Certification_Autotest_FileName="GGFV52FA English (Test) Test team.xlsm";
	//public static String iAg_Certification_Autotest_FileName="GGFV52FA English STAGING (issue 40).xlsm";
	
	public static String iAg_Certification_Autotest_FileName_STAGE="GGFV52FA English STAGING (issue 40) Test team.xlsm"; //for old code of ecapSAM..currently for STAGE
	//public static String iAg_Certification_Autotest_FileName_TEST="GGFV52FA English TEST (2019-11-05) Test.xlsm";  // for new code of ecapSAM...currently for TEST
	public static String iAg_Certification_Autotest_FileName_TEST="GGFV52FA English TEST (2021-02-11) 2846488.xlsm";  // for new code of ecapSAM...currently for TEST
	
	public static boolean iAgJobPausedAlreadyFlag=false;

	// check if the suite has to be skipped or not
	@BeforeSuite
	public void checkSuiteSkip() throws Throwable {

		fnInitialize();

		//currentSuiteName = iAg_suite_Name;
		setCurrentSuiteName(iAg_suite_Name);
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
			currentSuiteCode = "Agriculture";

			int rowNum = suiteXls.getCellRowNum("Test Suite", "TSID", currentSuiteName);
			suiteXls.setCellData("Test Suite", "Last_Execution", rowNum, fnReturnDateWithTimeForExcel());

			//currentSuiteXls = Agriculture_suitexls;
			setCurrentSuiteExcel(Agriculture_suitexls);
		}

	}

	// To remove IEDriver.exe from Windows Task Manager
	 @AfterSuite(alwaysRun=true)
	//@AfterSuite
	public void cleanUp() {
		try {
			referenceSuite = currentSuiteName;
			fnpMymsg("#################### "+currentSuiteName+" Suite End ############################################################");
			if (driver!=null) {
				driver.quit();
			}
			
			IsBrowserPresentAlready = false;
			killprocess();
			
			
			 
			if (iAgJobPausedAlreadyFlag) {
/*				
				 TestSuiteBase_ISR_suite.fnpProcessJob("CompleteAutoAIJob","Resume","Normal");	
				 //iAgJobPausedAlreadyFlag=true;
				 
				 */
				 
					env=CONFIG.getProperty("Environment");
					String urlToOpenForJob;
					if (env.equalsIgnoreCase("Test")) {
						urlToOpenForJob=CONFIG.getProperty("TEST_Jobs_site_url");
					}else{
						urlToOpenForJob=CONFIG.getProperty("STAGE_Jobs_site_url");
					}
					
					String userName = CONFIG.getProperty("adminUsername");
					String password = CONFIG.getProperty("adminPassword");
					
					TestSuiteBase_ISR_suite.fnpProcessJob(urlToOpenForJob,"",userName,password,"CompleteAutoAIJob","Resume","Normal");
				 
				 
			}

			
		} catch (Throwable t) {
			// Nothing to do
		}

	}
	
	
	
	
	

	public static void callCompleteTheAlreadyCreatedMembership() throws Throwable{
		

		
		TestSuiteBase_HealthScience.fnpLaunchBrowserAndLogin(hashXlData.get("iAg_LoginAsName"));
		
		fnpCommonClickTopMainMenu("Menu", "FirstRefrenceLinkJustBelowToMenu_foriAG", "SearchMembershipLink", "SearchCorpFacilityTxtBx");

		
		
		fnpType("OR", "SearchCorpFacilityTxtBx", hashXlData.get("Client"));		

		
		fnpClick_OR("MainSearchButton");

		

		// fnpMymsg("just before picking value from table");
		String s = fnpFetchFromStSearchTable(1, 1);
		int j = 0;
		// fnpLoading_wait();
		//while  (  (s.contains("No records found")) & (j < 15) ){
		while  (  (s.contains("No records found")) && (j < 15) ){
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
			//while (s.contains("No records found") & j < 15) {
			while (s.contains("No records found") && j < 15) {
				j++;
				Thread.sleep(1000);
				s = fnpFetchFromStSearchTable(1, 1);
			}
		}		
		/**************************************************************************/
		
		
		
		if (!(s.contains("No records found"))) {


			int totalRows=fnpCountRowsInTable("StandardSearchTable");
			
/*			
			if (totalRows>1) {
				throw new Exception("There should be only 1 Membership Work Order not in DROP status for this " +
						"facility '"+(String) hashXlData.get("Facility_No_for_singleWO")+"' but here they are --"+countSingleWO);
				
			} else {
			}
			}
			*/
			
			
			
			String temp;
			int statusColNo=fnpFindColumnIndex("StandardSearchTableHeader", "WO Status");
			for (int i = 1; i < totalRows+1; i++) {
				temp=fnpFetchFromStSearchTable(i, statusColNo);
				if ((temp.trim().equalsIgnoreCase("COMPLETE"))) {
					//nothing to do
				} else {
					

									
									if (!(s.contains("No records found")) ){
									
									fnpClickALinkInATable(s);
									
									
									fnpClick_OR("iAg_InfoTab");
									fnpClick_OR("EditWOBtn");
									
									fnpWaitForVisible("Membership_WO_Status_PFList");
									String Membership_WO_Status = fnpGetText_OR("Membership_WO_Status_PFList");//fnpGetORObjectX("Membership_WO_Status_PFList").getAttribute("value");
									
									if (Membership_WO_Status.equalsIgnoreCase("DRAFT")) {
										
										fnpPFList("Membership_WO_Status_PFList", "Membership_WO_Status_PFListOptions", "INREVIEW");
										
										//insert change reason
										fnpType("OR", "ReasonForChangeTxtBx", "Test");		
										
										
										
										
										fnpClick_OR("EditWO_SaveBtn");
										
										fnpWaitForVisibleHavingMultipleElements("TopMessage3_classname");
										fnpMymsg("Top Message after create ----" + fnpGetText_OR("TopMessage3_classname"));
										String SuccessfulMsg = fnpGetText_OR("TopMessage3_classname");
										Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"), "Top message does not contain 'success' word, so might be work order has not been created successfully");
				
										
									
										
										
										
									}
									
									
									if (Membership_WO_Status.equalsIgnoreCase("INREVIEW")) {
									
									
										
										fnpPFList("Membership_WO_Status_PFList", "Membership_WO_Status_PFListOptions", "ACTIVE");
										fnpType("OR", "ReasonForChangeTxtBx", "Test");	
										fnpClick_OR("EditWO_SaveBtn");
										fnpWaitForVisibleHavingMultipleElements("TopMessage3_classname");
										
										if(fnpCheckElementDisplayedImmediately("WO_Main_BackToViewBtn")){
										fnpClick_OR("WO_Main_BackToViewBtn");
										}
										
									
									
									}
									
								
									
									
									if (Membership_WO_Status.equalsIgnoreCase("ACTIVE")) {
										fnpPFList("Membership_WO_Status_PFList", "Membership_WO_Status_PFListOptions", "COMPLETE");
										fnpType("OR", "ReasonForChangeTxtBx", "Test");	
										fnpClick_OR("EditWO_SaveBtn");
										fnpWaitForVisibleHavingMultipleElements("TopMessage3_classname");
									
									
									
									}
									
									
								}
									
									
				}
					

				}
		
		
		
		
		
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
/*
	// Function to Drop WO ISR and delete some Data From DB from DB
	public static void fnpCleanMembershipUsingQueries(String customerCode,String standadCode,String optionType) throws Throwable {
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
			//connection=fnpGetDBConnection("iAg_test_int","nsfpass2017");
			
			Statement stmt = connection.createStatement();
		
			
			
			//--Customer membership Producer packhouse Details
			String deleteQuery1 = "DELETE FROM PRODUCER_PH_PRODUCT_XREF WHERE PROD_SITE_XREF_SEQ IN ("
									+ " select seq FROM PROD_SITE_XREF WHERE SUPPORT_SITE_SEQ IN ("
									+ " SELECT SEQ FROM IQ_LIST_SUPPORT_SITES WHERE CUSSX_SEQ =  "
									+ " (SELECT SEQ FROM CUS_STD_XREF WHERE CUS_SEQ = (SELECT SEQ FROM  CUSTOMERS WHERE CODE='"+customerCode+"') "
									+ " AND STD_CODE=(SELECT CODE FROM STANDARDS WHERE CODE='"+standadCode+"'))"
									+ " ))";
				
			
			
			//--Customer membership Producer packhouse Details
			
			String deleteQuery1 = "DELETE FROM PRODUCER_PH_PRODUCT_XREF WHERE PROD_SITE_XREF_SEQ IN ( select seq FROM PROD_SITE_XREF WHERE SUPPORT_SITE_SEQ IN ( SELECT SEQ FROM IQ_LIST_SUPPORT_SITES WHERE CUSSX_SEQ =  (SELECT SEQ FROM CUS_STD_XREF WHERE CUS_SEQ = (SELECT SEQ FROM  CUSTOMERS WHERE CODE='"+customerCode+"')  AND STD_CODE=(SELECT CODE FROM STANDARDS WHERE CODE='"+standadCode+"')) ))";
			
			

			//--Customer membership Packhouse country Details
			String deleteQuery2 = "DELETE FROM PSX_DEST_COUNTRIES WHERE PROD_SITE_XREF_SEQ IN ("
									+ " select seq FROM PROD_SITE_XREF WHERE SUPPORT_SITE_SEQ IN ("
									+ " SELECT SEQ FROM IQ_LIST_SUPPORT_SITES WHERE CUSSX_SEQ = "
									+ " (SELECT SEQ FROM CUS_STD_XREF WHERE CUS_SEQ = (SELECT SEQ FROM  CUSTOMERS WHERE CODE='"+customerCode+"') "
									+ " AND STD_CODE=(SELECT CODE FROM STANDARDS WHERE CODE='"+standadCode+"'))"
									+ " ))";
			
			
			
			//--Customer membership Packhouse country Details
			
			String deleteQuery2 = "DELETE FROM PSX_DEST_COUNTRIES WHERE PROD_SITE_XREF_SEQ IN ( select seq FROM PROD_SITE_XREF WHERE SUPPORT_SITE_SEQ IN (SELECT SEQ FROM IQ_LIST_SUPPORT_SITES WHERE CUSSX_SEQ =  (SELECT SEQ FROM CUS_STD_XREF WHERE CUS_SEQ = (SELECT SEQ FROM  CUSTOMERS WHERE CODE='"+customerCode+"') AND STD_CODE=(SELECT CODE FROM STANDARDS WHERE CODE='"+standadCode+"')) ))";

			
			
			//--Customer membership Product Details
			String deleteQuery3 =	"DELETE FROM PROD_SITE_XREF WHERE SUPPORT_SITE_SEQ IN ("
									+ " SELECT SEQ FROM IQ_LIST_SUPPORT_SITES WHERE CUSSX_SEQ = "
									+ " (SELECT SEQ FROM CUS_STD_XREF WHERE CUS_SEQ = (SELECT SEQ FROM  CUSTOMERS WHERE CODE='"+customerCode+"') "
									+ " AND STD_CODE=(SELECT CODE FROM STANDARDS WHERE CODE='"+standadCode+"'))"
									+ " )";
		
			
			
			//--Customer membership Product Details
			
			String deleteQuery3 =	"DELETE FROM PROD_SITE_XREF WHERE SUPPORT_SITE_SEQ IN ( SELECT SEQ FROM IQ_LIST_SUPPORT_SITES WHERE CUSSX_SEQ = (SELECT SEQ FROM CUS_STD_XREF WHERE CUS_SEQ = (SELECT SEQ FROM  CUSTOMERS WHERE CODE='"+customerCode+"')  AND STD_CODE=(SELECT CODE FROM STANDARDS WHERE CODE='"+standadCode+"')) )";
			
			
			
			
			
			
			
			//--Customer membership Sites Details
			String deleteQuery4 ="DELETE FROM IQ_LIST_SUPPORT_SITES WHERE CUSSX_SEQ = "
								+" (SELECT SEQ FROM CUS_STD_XREF WHERE CUS_SEQ = (SELECT SEQ FROM  CUSTOMERS WHERE CODE='"+customerCode+"') "
								+ "AND STD_CODE=(SELECT CODE FROM STANDARDS WHERE CODE='"+standadCode+"'))";
			
			
			
			
			//--Customer membership Sites Details
			
			String deleteQuery4 ="DELETE FROM IQ_LIST_SUPPORT_SITES WHERE CUSSX_SEQ = (SELECT SEQ FROM CUS_STD_XREF WHERE CUS_SEQ = (SELECT SEQ FROM  CUSTOMERS WHERE CODE='"+customerCode+"') AND STD_CODE=(SELECT CODE FROM STANDARDS WHERE CODE='"+standadCode+"'))";
			
			
			
			
			
			
			
			
			
			//--Work Order Action items Status History
			String deleteQuery5 = "DELETE FROM AI_STATUS_HISTORY WHERE AI_NO IN ("
									+" SELECT AI_NO FROM AI_ACTION_ITEMS WHERE ENTITY_ID IN ("
									+" SELECT JOB_NO FROM IQ_APP_JOBS WHERE APP_NO IN ("
									+" SELECT APP_NO FROM IQ_APP_CSX_XREF WHERE MEMBERSHIP_SEQ = (SELECT SEQ FROM CUS_STD_MEMBERSHIP WHERE "
									+" SMTR_SEQ = (SELECT SEQ FROM STD_MEMBERTYPE_ROLE_XREF WHERE "
									+" MEMBERSHIP_TYPE_SEQ = (SELECT SEQ FROM STD_MEMBERSHIP_TYPES WHERE STD_CODE='"+standadCode+"' AND MEMBERSHIP_TYPE_DESC='"+optionType+"')"
									+" AND ROLE_SEQ = (SELECT SEQ FROM STD_MEMBERSHIP_ROLES WHERE STD_CODE='"+standadCode+"' AND ROLE_NAME='Producer'))"
									+" AND"
									+" CUSSX_SEQ = "
									+" (SELECT SEQ FROM CUS_STD_XREF WHERE CUS_SEQ = (SELECT SEQ FROM  CUSTOMERS WHERE CODE='"+customerCode+"') "
									+" AND STD_CODE=(SELECT CODE FROM STANDARDS WHERE CODE='"+standadCode+"')))"
									+" )))";
			
			
			
			
			
			//--Work Order Action items Status History
			
			String deleteQuery5 = "DELETE FROM AI_STATUS_HISTORY WHERE AI_NO IN ( SELECT AI_NO FROM AI_ACTION_ITEMS WHERE ENTITY_ID IN ( SELECT JOB_NO FROM IQ_APP_JOBS WHERE APP_NO IN ( SELECT APP_NO FROM IQ_APP_CSX_XREF WHERE MEMBERSHIP_SEQ = (SELECT SEQ FROM CUS_STD_MEMBERSHIP WHERE  SMTR_SEQ = (SELECT SEQ FROM STD_MEMBERTYPE_ROLE_XREF WHERE  MEMBERSHIP_TYPE_SEQ = (SELECT SEQ FROM STD_MEMBERSHIP_TYPES WHERE STD_CODE='"+standadCode+"' AND MEMBERSHIP_TYPE_DESC='"+optionType+"') AND ROLE_SEQ = (SELECT SEQ FROM STD_MEMBERSHIP_ROLES WHERE STD_CODE='"+standadCode+"' AND ROLE_NAME='Producer')) AND CUSSX_SEQ =  (SELECT SEQ FROM CUS_STD_XREF WHERE CUS_SEQ = (SELECT SEQ FROM  CUSTOMERS WHERE CODE='"+customerCode+"')  AND STD_CODE=(SELECT CODE FROM STANDARDS WHERE CODE='"+standadCode+"'))) )))";
			
			
			
			
			
			//--Work Order Action items Assignement History
			String deleteQuery6 = "DELETE FROM AI_ASSIGNMENT_HISTORY WHERE AI_NO IN ("
									+" SELECT AI_NO FROM AI_ACTION_ITEMS WHERE ENTITY_ID IN ("
									+" SELECT JOB_NO FROM IQ_APP_JOBS WHERE APP_NO IN ("
									+" SELECT APP_NO FROM IQ_APP_CSX_XREF WHERE MEMBERSHIP_SEQ = (SELECT SEQ FROM CUS_STD_MEMBERSHIP WHERE "
									+" SMTR_SEQ = (SELECT SEQ FROM STD_MEMBERTYPE_ROLE_XREF WHERE "
									+" MEMBERSHIP_TYPE_SEQ = (SELECT SEQ FROM STD_MEMBERSHIP_TYPES WHERE STD_CODE='"+standadCode+"' AND MEMBERSHIP_TYPE_DESC='"+optionType+"')"
									+" AND ROLE_SEQ = (SELECT SEQ FROM STD_MEMBERSHIP_ROLES WHERE STD_CODE='"+standadCode+"' AND ROLE_NAME='Producer'))"
									+" AND"
									+" CUSSX_SEQ = "
									+" (SELECT SEQ FROM CUS_STD_XREF WHERE CUS_SEQ = (SELECT SEQ FROM  CUSTOMERS WHERE CODE='"+customerCode+"') "
									+" AND STD_CODE=(SELECT CODE FROM STANDARDS WHERE CODE='"+standadCode+"')))"
									+" )))";

			
			
			
			
			
			//--Work Order Action items Assignement History
			
			String deleteQuery6 = "DELETE FROM AI_ASSIGNMENT_HISTORY WHERE AI_NO IN ( SELECT AI_NO FROM AI_ACTION_ITEMS WHERE ENTITY_ID IN ( SELECT JOB_NO FROM IQ_APP_JOBS WHERE APP_NO IN ( SELECT APP_NO FROM IQ_APP_CSX_XREF WHERE MEMBERSHIP_SEQ = (SELECT SEQ FROM CUS_STD_MEMBERSHIP WHERE  SMTR_SEQ = (SELECT SEQ FROM STD_MEMBERTYPE_ROLE_XREF WHERE  MEMBERSHIP_TYPE_SEQ = (SELECT SEQ FROM STD_MEMBERSHIP_TYPES WHERE STD_CODE='"+standadCode+"' AND MEMBERSHIP_TYPE_DESC='"+optionType+"') AND ROLE_SEQ = (SELECT SEQ FROM STD_MEMBERSHIP_ROLES WHERE STD_CODE='"+standadCode+"' AND ROLE_NAME='Producer')) AND CUSSX_SEQ =  (SELECT SEQ FROM CUS_STD_XREF WHERE CUS_SEQ = (SELECT SEQ FROM  CUSTOMERS WHERE CODE='"+customerCode+"')  AND STD_CODE=(SELECT CODE FROM STANDARDS WHERE CODE='"+standadCode+"'))) )))";
			
			

			//-- To delete action item Notes if any
			
			String deleteQuery7 = " DELETE FROM AI_ACTION_ITEM_NOTES where AI_ACTION_ITEM_NOTES.AI_NO in ( select AI_NO FROM AI_ACTION_ITEMS WHERE ENTITY_ID IN (SELECT job_no FROM IQ_APP_JOBS WHERE APP_NO IN (SELECT app_no FROM IQ_APP_CSX_XREF WHERE MEMBERSHIP_SEQ = (SELECT seq FROM CUS_STD_MEMBERSHIP WHERE SMTR_SEQ = (SELECT SEQ FROM STD_MEMBERTYPE_ROLE_XREF WHERE MEMBERSHIP_TYPE_SEQ = (SELECT SEQ FROM STD_MEMBERSHIP_TYPES WHERE STD_CODE  ='"+standadCode+"' AND MEMBERSHIP_TYPE_DESC='"+optionType+"') AND ROLE_SEQ = (SELECT SEQ FROM STD_MEMBERSHIP_ROLES WHERE STD_CODE='"+standadCode+"' AND ROLE_NAME ='Producer') ) AND CUSSX_SEQ = (SELECT SEQ FROM CUS_STD_XREF WHERE CUS_SEQ = (SELECT SEQ FROM CUSTOMERS WHERE CODE='"+customerCode+"') AND STD_CODE= (SELECT CODE FROM STANDARDS WHERE CODE='"+standadCode+"') ) ))))";




			
			
		
			//-- Work Order Action items
			String deleteQuery8 = "DELETE FROM AI_ACTION_ITEMS WHERE ENTITY_ID IN ("
									+ " select job_no FROM IQ_APP_JOBS WHERE APP_NO IN ("
									+" SELECT app_no FROM IQ_APP_CSX_XREF WHERE MEMBERSHIP_SEQ = (SELECT seq FROM CUS_STD_MEMBERSHIP WHERE "
									+" SMTR_SEQ = (SELECT SEQ FROM STD_MEMBERTYPE_ROLE_XREF WHERE "
									+" MEMBERSHIP_TYPE_SEQ = (SELECT SEQ FROM STD_MEMBERSHIP_TYPES WHERE STD_CODE='"+standadCode+"' AND MEMBERSHIP_TYPE_DESC='"+optionType+"')"
									+" AND ROLE_SEQ = (SELECT SEQ FROM STD_MEMBERSHIP_ROLES WHERE STD_CODE='"+standadCode+"' AND ROLE_NAME='Producer'))"
									+" AND"
									+" CUSSX_SEQ = "
									+" (SELECT SEQ FROM CUS_STD_XREF WHERE CUS_SEQ = (SELECT SEQ FROM  CUSTOMERS WHERE CODE='"+customerCode+"') "
									+" AND STD_CODE=(SELECT CODE FROM STANDARDS WHERE CODE='"+standadCode+"')))"
									+" ))";
			
			
			
						
			//-- Work Order Action items
			
			String deleteQuery8 = "DELETE FROM AI_ACTION_ITEMS WHERE ENTITY_ID IN ( select job_no FROM IQ_APP_JOBS WHERE APP_NO IN ( SELECT app_no FROM IQ_APP_CSX_XREF WHERE MEMBERSHIP_SEQ = (SELECT seq FROM CUS_STD_MEMBERSHIP WHERE  SMTR_SEQ = (SELECT SEQ FROM STD_MEMBERTYPE_ROLE_XREF WHERE  MEMBERSHIP_TYPE_SEQ = (SELECT SEQ FROM STD_MEMBERSHIP_TYPES WHERE STD_CODE='"+standadCode+"' AND MEMBERSHIP_TYPE_DESC='"+optionType+"') AND ROLE_SEQ = (SELECT SEQ FROM STD_MEMBERSHIP_ROLES WHERE STD_CODE='"+standadCode+"' AND ROLE_NAME='Producer')) AND CUSSX_SEQ =  (SELECT SEQ FROM CUS_STD_XREF WHERE CUS_SEQ = (SELECT SEQ FROM  CUSTOMERS WHERE CODE='"+customerCode+"')  AND STD_CODE=(SELECT CODE FROM STANDARDS WHERE CODE='"+standadCode+"'))) ))";
			
			

			
			
			//-- Work Order's Task History Details..
			String deleteQuery9 ="DELETE FROM IQ_JOB_STATUS_HISTORY WHERE JOB_NO IN ("
								+" SELECT JOB_NO FROM IQ_APP_JOBS WHERE APP_NO IN ("
								+" SELECT app_no FROM IQ_APP_CSX_XREF WHERE MEMBERSHIP_SEQ = (SELECT seq FROM CUS_STD_MEMBERSHIP WHERE "
								+" SMTR_SEQ = (SELECT SEQ FROM STD_MEMBERTYPE_ROLE_XREF WHERE "
								+" MEMBERSHIP_TYPE_SEQ = (SELECT SEQ FROM STD_MEMBERSHIP_TYPES WHERE STD_CODE='"+standadCode+"' AND MEMBERSHIP_TYPE_DESC='"+optionType+"')"
								+" AND ROLE_SEQ = (SELECT SEQ FROM STD_MEMBERSHIP_ROLES WHERE STD_CODE='"+standadCode+"' AND ROLE_NAME='Producer'))"
								+" AND"
								+" CUSSX_SEQ = "
								+" (SELECT SEQ FROM CUS_STD_XREF WHERE CUS_SEQ = (SELECT SEQ FROM  CUSTOMERS WHERE CODE='"+customerCode+"')"
								+" AND STD_CODE=(SELECT CODE FROM STANDARDS WHERE CODE='"+standadCode+"')))"
								+" ))";
											
								
			
			
			
			//-- Work Order's Task History Details..
			
			String deleteQuery9 ="DELETE FROM IQ_JOB_STATUS_HISTORY WHERE JOB_NO IN ( SELECT JOB_NO FROM IQ_APP_JOBS WHERE APP_NO IN ( SELECT app_no FROM IQ_APP_CSX_XREF WHERE MEMBERSHIP_SEQ = (SELECT seq FROM CUS_STD_MEMBERSHIP WHERE  SMTR_SEQ = (SELECT SEQ FROM STD_MEMBERTYPE_ROLE_XREF WHERE  MEMBERSHIP_TYPE_SEQ = (SELECT SEQ FROM STD_MEMBERSHIP_TYPES WHERE STD_CODE='"+standadCode+"' AND MEMBERSHIP_TYPE_DESC='"+optionType+"') AND ROLE_SEQ = (SELECT SEQ FROM STD_MEMBERSHIP_ROLES WHERE STD_CODE='"+standadCode+"' AND ROLE_NAME='Producer')) AND CUSSX_SEQ =  (SELECT SEQ FROM CUS_STD_XREF WHERE CUS_SEQ = (SELECT SEQ FROM  CUSTOMERS WHERE CODE='"+customerCode+"') AND STD_CODE=(SELECT CODE FROM STANDARDS WHERE CODE='"+standadCode+"'))) ))";
			
			
			
			
			
			
			//-- Work Order's Task Details..
			String deleteQuery10 ="DELETE FROM IQ_APP_JOBS WHERE APP_NO IN ("
									+" SELECT app_no FROM IQ_APP_CSX_XREF WHERE MEMBERSHIP_SEQ = (SELECT seq FROM CUS_STD_MEMBERSHIP WHERE "
									+" SMTR_SEQ = (SELECT SEQ FROM STD_MEMBERTYPE_ROLE_XREF WHERE "
									+" MEMBERSHIP_TYPE_SEQ = (SELECT SEQ FROM STD_MEMBERSHIP_TYPES WHERE STD_CODE='"+standadCode+"' AND MEMBERSHIP_TYPE_DESC='"+optionType+"')"
									+" AND ROLE_SEQ = (SELECT SEQ FROM STD_MEMBERSHIP_ROLES WHERE STD_CODE='"+standadCode+"' AND ROLE_NAME='Producer'))"
									+" AND"
									+" CUSSX_SEQ = "
									+" (SELECT SEQ FROM CUS_STD_XREF WHERE CUS_SEQ = (SELECT SEQ FROM  CUSTOMERS WHERE CODE='"+customerCode+"') "
									+" AND STD_CODE=(SELECT CODE FROM STANDARDS WHERE CODE='"+standadCode+"')))"
									+" )";
			
			
			
			
			
			//-- Work Order's Task Details..
			
			String deleteQuery10 ="DELETE FROM IQ_APP_JOBS WHERE APP_NO IN ( SELECT app_no FROM IQ_APP_CSX_XREF WHERE MEMBERSHIP_SEQ = (SELECT seq FROM CUS_STD_MEMBERSHIP WHERE  SMTR_SEQ = (SELECT SEQ FROM STD_MEMBERTYPE_ROLE_XREF WHERE  MEMBERSHIP_TYPE_SEQ = (SELECT SEQ FROM STD_MEMBERSHIP_TYPES WHERE STD_CODE='"+standadCode+"' AND MEMBERSHIP_TYPE_DESC='"+optionType+"') AND ROLE_SEQ = (SELECT SEQ FROM STD_MEMBERSHIP_ROLES WHERE STD_CODE='"+standadCode+"' AND ROLE_NAME='Producer')) AND CUSSX_SEQ =  (SELECT SEQ FROM CUS_STD_XREF WHERE CUS_SEQ = (SELECT SEQ FROM  CUSTOMERS WHERE CODE='"+customerCode+"')  AND STD_CODE=(SELECT CODE FROM STANDARDS WHERE CODE='"+standadCode+"'))) )";
			
			
												
			
			
			
			//-- Customer membership and work order mapping Details
			String deleteQuery11 ="DELETE FROM IQ_APP_CSX_XREF WHERE MEMBERSHIP_SEQ = (SELECT seq FROM CUS_STD_MEMBERSHIP WHERE "
									+" SMTR_SEQ = (SELECT SEQ FROM STD_MEMBERTYPE_ROLE_XREF WHERE "
									+" MEMBERSHIP_TYPE_SEQ = (SELECT SEQ FROM STD_MEMBERSHIP_TYPES WHERE STD_CODE='"+standadCode+"' AND MEMBERSHIP_TYPE_DESC='"+optionType+"')"
									+" AND ROLE_SEQ = (SELECT SEQ FROM STD_MEMBERSHIP_ROLES WHERE STD_CODE='"+standadCode+"' AND ROLE_NAME='Producer'))"
									+" AND"
									+" CUSSX_SEQ = "
									+" (SELECT SEQ FROM CUS_STD_XREF WHERE CUS_SEQ = (SELECT SEQ FROM  CUSTOMERS WHERE CODE='"+customerCode+"') "
									+" and STD_CODE=(SELECT CODE FROM STANDARDS WHERE CODE='"+standadCode+"')))";
			
			
			
			
			
			//-- Customer membership and work order mapping Details
			
			String deleteQuery11 ="DELETE FROM IQ_APP_CSX_XREF WHERE MEMBERSHIP_SEQ = (SELECT seq FROM CUS_STD_MEMBERSHIP WHERE  SMTR_SEQ = (SELECT SEQ FROM STD_MEMBERTYPE_ROLE_XREF WHERE  MEMBERSHIP_TYPE_SEQ = (SELECT SEQ FROM STD_MEMBERSHIP_TYPES WHERE STD_CODE='"+standadCode+"' AND MEMBERSHIP_TYPE_DESC='"+optionType+"') AND ROLE_SEQ = (SELECT SEQ FROM STD_MEMBERSHIP_ROLES WHERE STD_CODE='"+standadCode+"' AND ROLE_NAME='Producer')) AND CUSSX_SEQ =  (SELECT SEQ FROM CUS_STD_XREF WHERE CUS_SEQ = (SELECT SEQ FROM  CUSTOMERS WHERE CODE='"+customerCode+"')  and STD_CODE=(SELECT CODE FROM STANDARDS WHERE CODE='"+standadCode+"')))";
			
			
			
			
			
			//--Work Order History
			String deleteQuery12 ="DELETE FROM IQ_APP_STATUS_HISTORY WHERE APP_NO IN ("
									+" SELECT app_no FROM IQ_APP_CSX_XREF WHERE MEMBERSHIP_SEQ = (SELECT seq FROM CUS_STD_MEMBERSHIP WHERE "
									+" SMTR_SEQ = (SELECT SEQ FROM STD_MEMBERTYPE_ROLE_XREF WHERE "
									+" MEMBERSHIP_TYPE_SEQ = (SELECT SEQ FROM STD_MEMBERSHIP_TYPES WHERE STD_CODE='"+standadCode+"' AND MEMBERSHIP_TYPE_DESC='"+optionType+"')"
									+" AND ROLE_SEQ = (SELECT SEQ FROM STD_MEMBERSHIP_ROLES WHERE STD_CODE='"+standadCode+"' AND ROLE_NAME='Producer'))"
									+" AND"
									+" CUSSX_SEQ = "
									+" (SELECT SEQ FROM CUS_STD_XREF WHERE CUS_SEQ = (SELECT SEQ FROM  CUSTOMERS WHERE CODE='"+customerCode+"') "
									+" AND STD_CODE=(SELECT CODE FROM STANDARDS WHERE CODE='"+standadCode+"')))"
									+" )";
			
			
			
			//--Work Order History
			
			String deleteQuery12 ="DELETE FROM IQ_APP_STATUS_HISTORY WHERE APP_NO IN ( SELECT app_no FROM IQ_APP_CSX_XREF WHERE MEMBERSHIP_SEQ = (SELECT seq FROM CUS_STD_MEMBERSHIP WHERE  SMTR_SEQ = (SELECT SEQ FROM STD_MEMBERTYPE_ROLE_XREF WHERE  MEMBERSHIP_TYPE_SEQ = (SELECT SEQ FROM STD_MEMBERSHIP_TYPES WHERE STD_CODE='"+standadCode+"' AND MEMBERSHIP_TYPE_DESC='"+optionType+"') AND ROLE_SEQ = (SELECT SEQ FROM STD_MEMBERSHIP_ROLES WHERE STD_CODE='"+standadCode+"' AND ROLE_NAME='Producer')) AND CUSSX_SEQ =  (SELECT SEQ FROM CUS_STD_XREF WHERE CUS_SEQ = (SELECT SEQ FROM  CUSTOMERS WHERE CODE='"+customerCode+"')  AND STD_CODE=(SELECT CODE FROM STANDARDS WHERE CODE='"+standadCode+"'))) )";
			
			
			
			
			//---- Work Order Details
			String deleteQuery13 ="DELETE FROM IQ_APPLICATIONS WHERE APP_NO=("
									+" SELECT app_no FROM IQ_APP_CSX_XREF WHERE MEMBERSHIP_SEQ = (SELECT seq FROM CUS_STD_MEMBERSHIP WHERE "
									+" SMTR_SEQ = (SELECT SEQ FROM STD_MEMBERTYPE_ROLE_XREF WHERE "
									+" MEMBERSHIP_TYPE_SEQ = (SELECT SEQ FROM STD_MEMBERSHIP_TYPES WHERE STD_CODE='"+standadCode+"' AND MEMBERSHIP_TYPE_DESC='"+optionType+"')"
									+" AND ROLE_SEQ = (SELECT SEQ FROM STD_MEMBERSHIP_ROLES WHERE STD_CODE='"+standadCode+"' AND ROLE_NAME='Producer'))"
									+" AND"
									+" CUSSX_SEQ = "
									+" (SELECT SEQ FROM CUS_STD_XREF WHERE CUS_SEQ = (SELECT SEQ FROM  CUSTOMERS WHERE CODE='"+customerCode+"') "
									+" AND STD_CODE=(SELECT CODE FROM STANDARDS WHERE CODE='"+standadCode+"')))"
									+" )";
											
			
			
			
			
			//---- Work Order Details
			
			String deleteQuery13 ="DELETE FROM IQ_APPLICATIONS WHERE APP_NO=( SELECT app_no FROM IQ_APP_CSX_XREF WHERE MEMBERSHIP_SEQ = (SELECT seq FROM CUS_STD_MEMBERSHIP WHERE  SMTR_SEQ = (SELECT SEQ FROM STD_MEMBERTYPE_ROLE_XREF WHERE  MEMBERSHIP_TYPE_SEQ = (SELECT SEQ FROM STD_MEMBERSHIP_TYPES WHERE STD_CODE='"+standadCode+"' AND MEMBERSHIP_TYPE_DESC='"+optionType+"') AND ROLE_SEQ = (SELECT SEQ FROM STD_MEMBERSHIP_ROLES WHERE STD_CODE='"+standadCode+"' AND ROLE_NAME='Producer')) AND CUSSX_SEQ =  (SELECT SEQ FROM CUS_STD_XREF WHERE CUS_SEQ = (SELECT SEQ FROM  CUSTOMERS WHERE CODE='"+customerCode+"')  AND STD_CODE=(SELECT CODE FROM STANDARDS WHERE CODE='"+standadCode+"'))) )";
			
			
			
		
			
			//-- Customer membership flex field Details
			String deleteQuery14 ="DELETE FROM EC_FLEX_FIELD_DATA"
									+" WHERE EFFPX_SEQ IN ("
									+" SELECT SEQ FROM EC_FF_PROFILE_XREF WHERE "
									+" EFPM_SEQ = (SELECT SEQ FROM EC_FF_PROFILE_MASTER WHERE PROFILE_NAME='GG Producer' AND DATA_EEM_SEQ = "
									+" (SELECT SEQ FROM EC_ENTITY_MASTER WHERE ENTITY_NAME = 'CUS_STD_MEMBERSHIP')) AND"
									+" EFFM_SEQ IN (SELECT SEQ FROM EC_FLEX_FIELD_MASTER WHERE FIELD_NAME IN ('Parallel Ownership','Parallel Production','Harvest Excluded'))"
									+" )"
									+" AND ENTITY_ID = ("
									+" SELECT seq FROM CUS_STD_MEMBERSHIP WHERE "
									+" SMTR_SEQ = (SELECT SEQ FROM STD_MEMBERTYPE_ROLE_XREF WHERE "
									+" MEMBERSHIP_TYPE_SEQ = (SELECT SEQ FROM STD_MEMBERSHIP_TYPES WHERE STD_CODE='"+standadCode+"' AND MEMBERSHIP_TYPE_DESC='"+optionType+"')"
									+" AND ROLE_SEQ = (SELECT SEQ FROM STD_MEMBERSHIP_ROLES WHERE STD_CODE='"+standadCode+"' AND ROLE_NAME='Producer'))"
									+" AND"
									+" CUSSX_SEQ = "
									+" (SELECT SEQ FROM CUS_STD_XREF WHERE CUS_SEQ = (SELECT SEQ FROM  CUSTOMERS WHERE CODE='"+customerCode+"') "
									+" AND STD_CODE=(SELECT CODE FROM STANDARDS WHERE CODE='"+standadCode+"'))"
									+" )";
												
			
		
			
			
			
			
			//-- Customer membership flex field Details
			
			String deleteQuery14 ="DELETE FROM EC_FLEX_FIELD_DATA WHERE EFFPX_SEQ IN ( SELECT SEQ FROM EC_FF_PROFILE_XREF WHERE  EFPM_SEQ = (SELECT SEQ FROM EC_FF_PROFILE_MASTER WHERE PROFILE_NAME='GG Producer' AND DATA_EEM_SEQ =  (SELECT SEQ FROM EC_ENTITY_MASTER WHERE ENTITY_NAME = 'CUS_STD_MEMBERSHIP')) AND EFFM_SEQ IN (SELECT SEQ FROM EC_FLEX_FIELD_MASTER WHERE FIELD_NAME IN ('Parallel Ownership','Parallel Production','Harvest Excluded')) ) AND ENTITY_ID = ( SELECT seq FROM CUS_STD_MEMBERSHIP WHERE  SMTR_SEQ = (SELECT SEQ FROM STD_MEMBERTYPE_ROLE_XREF WHERE  MEMBERSHIP_TYPE_SEQ = (SELECT SEQ FROM STD_MEMBERSHIP_TYPES WHERE STD_CODE='"+standadCode+"' AND MEMBERSHIP_TYPE_DESC='"+optionType+"') AND ROLE_SEQ = (SELECT SEQ FROM STD_MEMBERSHIP_ROLES WHERE STD_CODE='"+standadCode+"' AND ROLE_NAME='Producer')) AND CUSSX_SEQ =  (SELECT SEQ FROM CUS_STD_XREF WHERE CUS_SEQ = (SELECT SEQ FROM  CUSTOMERS WHERE CODE='"+customerCode+"')  AND STD_CODE=(SELECT CODE FROM STANDARDS WHERE CODE='"+standadCode+"')) )";
			
			
			
			
			
			//-- Customer Membership extra ...
			String deleteQuery15 ="DELETE from CUS_STD_MEMBERSHIP_EXTRAS where MEMBERSHIP_SEQ = (SELECT seq FROM CUS_STD_MEMBERSHIP WHERE "
									+" SMTR_SEQ = (SELECT SEQ FROM STD_MEMBERTYPE_ROLE_XREF WHERE "
									+" MEMBERSHIP_TYPE_SEQ = (SELECT SEQ FROM STD_MEMBERSHIP_TYPES WHERE STD_CODE='"+standadCode+"' AND MEMBERSHIP_TYPE_DESC='"+optionType+"')"
									+" AND ROLE_SEQ = (SELECT SEQ FROM STD_MEMBERSHIP_ROLES WHERE STD_CODE='"+standadCode+"' AND ROLE_NAME='Producer'))"
									+" AND"
									+" CUSSX_SEQ = "
									+" (SELECT SEQ FROM CUS_STD_XREF WHERE CUS_SEQ = (SELECT SEQ FROM  CUSTOMERS WHERE CODE='"+customerCode+"') "
									+" AND STD_CODE=(SELECT CODE FROM STANDARDS WHERE CODE='"+standadCode+"')))";
												
			
			
			
			//-- Customer Membership extra ...
			
			String deleteQuery15 ="DELETE from CUS_STD_MEMBERSHIP_EXTRAS where MEMBERSHIP_SEQ = (SELECT seq FROM CUS_STD_MEMBERSHIP WHERE  SMTR_SEQ = (SELECT SEQ FROM STD_MEMBERTYPE_ROLE_XREF WHERE  MEMBERSHIP_TYPE_SEQ = (SELECT SEQ FROM STD_MEMBERSHIP_TYPES WHERE STD_CODE='"+standadCode+"' AND MEMBERSHIP_TYPE_DESC='"+optionType+"')AND ROLE_SEQ = (SELECT SEQ FROM STD_MEMBERSHIP_ROLES WHERE STD_CODE='"+standadCode+"' AND ROLE_NAME='Producer')) AND CUSSX_SEQ =  (SELECT SEQ FROM CUS_STD_XREF WHERE CUS_SEQ = (SELECT SEQ FROM  CUSTOMERS WHERE CODE='"+customerCode+"')  AND STD_CODE=(SELECT CODE FROM STANDARDS WHERE CODE='"+standadCode+"')))";
			
			
			
			
			
			
			
			//-- Customer membership Cycle Details
			String deleteQuery16 ="DELETE from CUS_STD_MEMBERSHIP_CYCLE where MEMBERSHIP_SEQ = (SELECT seq FROM CUS_STD_MEMBERSHIP WHERE "
									+" SMTR_SEQ = (SELECT SEQ FROM STD_MEMBERTYPE_ROLE_XREF WHERE "
									+" MEMBERSHIP_TYPE_SEQ = (SELECT SEQ FROM STD_MEMBERSHIP_TYPES WHERE STD_CODE='"+standadCode+"' AND MEMBERSHIP_TYPE_DESC='"+optionType+"')"
									+" AND ROLE_SEQ = (SELECT SEQ FROM STD_MEMBERSHIP_ROLES WHERE STD_CODE='"+standadCode+"' AND ROLE_NAME='Producer'))"
									+" AND"
									+" CUSSX_SEQ = "
									+" (SELECT SEQ FROM CUS_STD_XREF WHERE CUS_SEQ = (SELECT SEQ FROM  CUSTOMERS WHERE CODE='"+customerCode+"') "
									+" AND STD_CODE=(SELECT CODE FROM STANDARDS WHERE CODE='"+standadCode+"')))";
			
			
			
			//-- Customer membership Cycle Details
			
			String deleteQuery16 ="DELETE from CUS_STD_MEMBERSHIP_CYCLE where MEMBERSHIP_SEQ = (SELECT seq FROM CUS_STD_MEMBERSHIP WHERE  SMTR_SEQ =(SELECT SEQ FROM STD_MEMBERTYPE_ROLE_XREF WHERE MEMBERSHIP_TYPE_SEQ = (SELECT SEQ FROM STD_MEMBERSHIP_TYPES WHERE STD_CODE='"+standadCode+"' AND MEMBERSHIP_TYPE_DESC='"+optionType+"') AND ROLE_SEQ = (SELECT SEQ FROM STD_MEMBERSHIP_ROLES WHERE STD_CODE='"+standadCode+"' AND ROLE_NAME='Producer')) AND CUSSX_SEQ =  (SELECT SEQ FROM CUS_STD_XREF WHERE CUS_SEQ = (SELECT SEQ FROM  CUSTOMERS WHERE CODE='"+customerCode+"')  AND STD_CODE=(SELECT CODE FROM STANDARDS WHERE CODE='"+standadCode+"')))";
			
			
			
			//-- Customer membership Details
			String deleteQuery17 ="DELETE FROM CUS_STD_MEMBERSHIP WHERE "
									+" SMTR_SEQ = (SELECT SEQ FROM STD_MEMBERTYPE_ROLE_XREF WHERE "
									+" MEMBERSHIP_TYPE_SEQ = (SELECT SEQ FROM STD_MEMBERSHIP_TYPES WHERE STD_CODE='"+standadCode+"' AND MEMBERSHIP_TYPE_DESC='"+optionType+"')"
									+" AND ROLE_SEQ = (SELECT SEQ FROM STD_MEMBERSHIP_ROLES WHERE STD_CODE='"+standadCode+"' AND ROLE_NAME='Producer'))"
									+" AND"
									+" CUSSX_SEQ = "
									+" (SELECT SEQ FROM CUS_STD_XREF WHERE CUS_SEQ = (SELECT SEQ FROM  CUSTOMERS WHERE CODE='"+customerCode+"') "
									+" and STD_CODE=(SELECT CODE FROM STANDARDS WHERE CODE='"+standadCode+"'))";
												
		
			
			
			
			//-- Customer membership Details
			
			String deleteQuery17 ="DELETE FROM CUS_STD_MEMBERSHIP WHERE  SMTR_SEQ = (SELECT SEQ FROM STD_MEMBERTYPE_ROLE_XREF WHERE  MEMBERSHIP_TYPE_SEQ = (SELECT SEQ FROM STD_MEMBERSHIP_TYPES WHERE STD_CODE='"+standadCode+"' AND MEMBERSHIP_TYPE_DESC='"+optionType+"') AND ROLE_SEQ = (SELECT SEQ FROM STD_MEMBERSHIP_ROLES WHERE STD_CODE='"+standadCode+"' AND ROLE_NAME='Producer')) AND CUSSX_SEQ =  (SELECT SEQ FROM CUS_STD_XREF WHERE CUS_SEQ = (SELECT SEQ FROM  CUSTOMERS WHERE CODE='"+customerCode+"')  and STD_CODE=(SELECT CODE FROM STANDARDS WHERE CODE='"+standadCode+"'))";
			
			
			
			

			
			//-- Contact Tab details
			String deleteQuery18 ="DELETE FROM CUS_STD_CONTACT_XREF WHERE CSX_SEQ IN ("
								+" SELECT SEQ FROM CUS_STD_XREF WHERE CUS_SEQ = (SELECT SEQ FROM  CUSTOMERS WHERE CODE='"+customerCode+"')  "
								+ " AND STD_CODE=(SELECT CODE FROM STANDARDS WHERE CODE='"+standadCode+"'))";
			
			
			
			
			//-- Contact Tab details
			
			String deleteQuery18 ="DELETE FROM CUS_STD_CONTACT_XREF WHERE CSX_SEQ IN ( SELECT SEQ FROM CUS_STD_XREF WHERE CUS_SEQ = (SELECT SEQ FROM  CUSTOMERS WHERE CODE='"+customerCode+"')   AND STD_CODE=(SELECT CODE FROM STANDARDS WHERE CODE='"+standadCode+"'))";
			
			
			
			int a1 = stmt.executeUpdate(deleteQuery1);
			fnpMymsg("@-- deleted Customer membership Producer packhouse Details rows are --" + a1);
			connection.commit();

			
			int a2 = stmt.executeUpdate(deleteQuery2);
			fnpMymsg("@--deleted Customer membership Packhouse country Detailsare  --" + a2);
			connection.commit();

			
			int a3 = stmt.executeUpdate(deleteQuery3);
			fnpMymsg("@--deleted Customer membership Product Details  rows are  --"  + a3);
			connection.commit();

			
			int a4 = stmt.executeUpdate(deleteQuery4);
			fnpMymsg("@---deleted  Customer membership Sites Details are  --" + a4);
			connection.commit();
			
			
			int a5 = stmt.executeUpdate(deleteQuery5);
			fnpMymsg("@---Work Order Action items History rows are  --" + a5);
			connection.commit();
			
			
			
			
			int a6 = stmt.executeUpdate(deleteQuery6);
			fnpMymsg("@---Work Order Action items Assignment History rows are  --" + a6);
			connection.commit();
			
			
			int a7= stmt.executeUpdate(deleteQuery7);
			fnpMymsg("@---deleted  Work Order Action item notes rows are  --" + a7);
			connection.commit();
			
			
			int a8= stmt.executeUpdate(deleteQuery8);
			fnpMymsg("@---deleted  Work Order Action items  rows are  --" + a8);
			connection.commit();
			
			
			int a9 = stmt.executeUpdate(deleteQuery9);
			fnpMymsg("@---deleted Work Order's Task History Details..  rows are  --" + a9);
			connection.commit();
			
			
			int a10 = stmt.executeUpdate(deleteQuery10);
			fnpMymsg("@---deleted  Work Order's Task Details..  rows are  -" + a10);
			connection.commit();
			
			
			int a11 = stmt.executeUpdate(deleteQuery11);
			fnpMymsg("@---deleted  Customer membership and work order mapping Details  rows are  --" + a11);
			connection.commit();
			
			
			int a12 = stmt.executeUpdate(deleteQuery12);
			fnpMymsg("@---deleted Work Order History  rows are  --" + a12);
			connection.commit();
			
			
			int a13 = stmt.executeUpdate(deleteQuery13);
			fnpMymsg("@---deleted  Work Order Details  rows are  --" + a13);
			connection.commit();
			
			
			int a14 = stmt.executeUpdate(deleteQuery14);
			fnpMymsg("@---deleted  Customer membership flex field Details  rows are  --" + a14);
			connection.commit();
			
			
			int a15 = stmt.executeUpdate(deleteQuery15);
			fnpMymsg("@---deleted  Customer Membership extra ... rows are  --" + a15);
			connection.commit();
			
			
			int a16 = stmt.executeUpdate(deleteQuery16);
			fnpMymsg("@---deleted Customer membership Cycle Details  rows are  --" + a16);
			connection.commit();
			
			
			int a17 = stmt.executeUpdate(deleteQuery17);
			fnpMymsg("@---deleted Customer membership Details  rows are  --" + a17);
			connection.commit();
			
			
			int a18 = stmt.executeUpdate(deleteQuery18);
			fnpMymsg("@---deleted Contacts  rows are  --" + a18);
			//System.out.println("@---deleted Contacts  rows are  --" + a16);
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
	*/
	
	
	public void fnpAddingdataInSitesTab(String name,String unitType,String addressLine1,
			String town,String country,String state,String postalCode,String longitude,
			String latitutde,String salutation,String fName,String lName,String jobTitle,
			String phone,String email,String fax,String mobile,String contPref) throws Throwable{
		
		
		
		int totalRow_beforeAddingNewOne = fnpCountRowsInTable("OperationalUnitsTable_EditWO");
		
		
		//Step =>**** Click on the Add New button***********/
		fnpClick_OR("AddNewBtn");
		
		
		//Step =>**** In the Name Field enter***********/
		fnpType("OR", "Name_onCreateNewOperationUnit", name);
		
		 
		//Step =>****In the Unit Type dropdown box select : ***********/
		fnpPFList("UnitType_PFList", "UnitType_PFListOptions", unitType);
		
		
		
		
		//Step =>**** In the field headed Building Name/Number enter***********/
		fnpType("OR", "AddressLine1", addressLine1);
		
		
		
		//Step =>****  In the field headed Street enter :***********/
		fnpType("OR", "TownCity", town);
		
		
		
		//Step =>**** From the Country dropdown box select : ***********/
		fnpPFList("Country_CNOU_PFList", "Country_CNOU_PFListOptions",country);
		
		Thread.sleep(1000);// intentionally as state field takes sometime to refresh after entering country value
		
		//Step =>**** From the State dropdown box select : ***********/
		fnpPFList("State_CNOU_PFList", "State_CNOU_PFListOptions", state);
		
		
		//Step =>****  . In the Postal Code field enter :***********/
		fnpType("OR", "PostalCode_CNOU", postalCode);
		
	
		
		//Step =>****  In the Logitude field enter  :***********/
		fnpType("OR", "Longitude_CNOU", longitude);
		
		
		
		
		
		//Step =>****   In the Latitude field enter  :***********/
		fnpType("OR", "Latitude_CNOU", latitutde);
		
		
		
		
		//Step =>****  From the Salutation dropdown box select : ***********/
		fnpPFList("Salutation_CNOU_PFList", "Salutation_CNOU_PFListOptions", salutation);
		
		
		
		//Step =>****   In the field headed First name enter  :***********/
		fnpType("OR", "FirstName_CNOU", fName);
		
		
		
		//Step =>****   In the field headed Last name enter  :***********/
		fnpType("OR", "LastName_CNOU", lName);
		
		
		//Step =>****    In the field headed Job Title enter   :***********/
		fnpType("OR", "JobTitle_CNOU", jobTitle);
		

		//System.out.println("Pradeep--phone no is ---"+phone);
		fnpMymsg("Pradeep--phone no is ---"+phone);
		
		//Step =>****    In the field headed Phone enter    :***********/
		fnpType("OR", "Phone_CNOU", phone);
		
		
		
		//Step =>****     In the field headed Email enter     :***********/
		fnpType("OR", "Email_CNOU", email);
		
		
		//Step =>****     In the field headed Fax enter     :***********/
		fnpType("OR", "Fax_CNOU", fax);
		
		
		
		//Step =>****     In the field headed Mobile enter     :***********/
		fnpType("OR", "Mobile_CNOU", mobile);
		
		fnpLoading_wait();
		
		//Step =>****  In the dropdown box headed Cont Pref select : ***********/
		fnpPFList("ContPref_CNOU_PFList", "ContPref_CNOU_PFListOptions", contPref); 
		
		
		
		
		//Step =>**** Click on the Save button***********/
		fnpClick_OR("SaveBtn_CNOU");
		
/*		int maxWait=Integer.parseInt(CONFIG.getProperty("genMax_waitTime"));
		int iwhilecounter=0;
		while(true){
			iwhilecounter++;
			if (fnpCheckElementDisplayedImmediately("SaveBtn_CNOU")) {
				fnpMymsg("After clicking 'Save' button in popup of adding new operational unit at sites tab, it is still visible after --"+iwhilecounter+" seconds.");
				Thread.sleep(1000);
				if (iwhilecounter>maxWait) {
					throw new Exception("After clicking 'Save' button in pop up of adding new operational unit at sites tab, " +
							"nothing is happend even after approx. "+maxWait+" seconds.");
					
				}
				
			} else {
				break;
			}
		}
		*/
		fnpLoading_wait();
		fnpCheckError(" after loading ");
		fnpWaitTillThisElementIsNoLongerDisplayedTillMaxWait_OR("SaveBtn_CNOU",
				"After clicking 'Save' button in pop up of adding new operational unit at sites tab, " +
						"nothing is happend even after approx. ",Integer.parseInt(CONFIG.getProperty("genMax_waitTime")));
		
		
		/**********Here Assumption is that latest added will be comes to first row  (means added at top i.e. in row 1)
		//****Logic to check first row count should not be greater than 2 now or not less than 2     AND also value in name column of first row is same as we have entered in Name field **********/
		 int totalRow_afterAddingNewOne = fnpCountRowsInTable("OperationalUnitsTable_EditWO");
		 int nameColIndex = fnpFindColumnIndex("OperationalUnitsTable_HeaderRow", "Name");
		
		if (!(totalRow_afterAddingNewOne>totalRow_beforeAddingNewOne)) {
			throw new Exception("A new row for a Site is NOT displayed." +
							" Before adding rows they were --'"+totalRow_beforeAddingNewOne+"' and after adding new one total Rows are --'"+totalRow_afterAddingNewOne+"'.");
			
		} else {
				if (totalRow_afterAddingNewOne>(totalRow_beforeAddingNewOne+1)) {
					throw new Exception("Not A new row but MULTIPLE rows have been generated for a Site is displayed." +
							" Before adding rows they were --'"+totalRow_beforeAddingNewOne+"' and after adding new one total Rows are --'"+totalRow_afterAddingNewOne+"'.");
				} else {

					String nameValueInFirstRow = fnpFetchFromTable("OperationalUnitsTable_EditWO", 1, nameColIndex);
					Assert.assertEquals(nameValueInFirstRow, name, "Site is not added same as 'Name' is not matching. " +
							"Actual is--'"+nameValueInFirstRow+"'  and expected is ---"+name);
					
				}
		}
		
		
	}
	
	
	
	public static void fnpProcessiAgAI(String innerTableHeaderXpathForAI,String innerTableDataXpathForAI,String aiName,String statusToChange) throws Throwable{

		int ActionItemColIndex = fnpFindColumnIndex_NOR(innerTableHeaderXpathForAI, "Action Item #");
		
		int aiNameColIndex = fnpFindColumnIndex_NOR(innerTableHeaderXpathForAI, "Action Item Name");
		
	//	String aiName="ContractReview";
		int actionItemInfoRowNo;
		try{
			 actionItemInfoRowNo = fnpFindRow_NOR(innerTableDataXpathForAI, aiName, aiNameColIndex);
		}catch(Throwable t){
			fnpMymsg("Either this AI '"+aiName+"' is not present or its name is changed. ");
			throw new Exception("Either this AI '"+aiName+"' is not present or its name is changed as script getting this error --"+t.getMessage());
		}
		
		
		
		
		
		String actionItemNo = fnpFetchFromTable_NOR(innerTableDataXpathForAI, actionItemInfoRowNo, ActionItemColIndex);
		

		//fnpClickALinkInATable_advance( OR.getProperty("iAgSnapshotTab_InnerTableForActionItemsTableData_part2"),  actionItemNo,  actionItemInfoRowNo);
		//fnpClickALinkInATable_advance( OR.getProperty("iAgSnapshotTab_InnerTableForActionItemsTableData_contains"),  actionItemNo,  actionItemInfoRowNo);
		
		
		fnpClickALinkInATable_advance_havingATagInside( OR.getProperty("iAgSnapshotTab_InnerTableForActionItemsTableData_contains"),  actionItemNo,  actionItemInfoRowNo);
		
		fnpMymsg("Clicked on  Action Item '" + aiName + "' in Table having no. is '" + actionItemNo + "' .");

		fnpLoading_wait();
		
		
		if ( (aiName.equalsIgnoreCase("CertificationDecision"))
				|| (aiName.equalsIgnoreCase("TechnicalReview"))
				|| (aiName.equalsIgnoreCase("PerformAudit"))
				|| (aiName.equalsIgnoreCase("CertificationIssue"))
						) {
			//nothing to do for now
		}else{
			fnpClick_OR("AssignToMeAndMoveInProgress_btn");
		}
		
		//String expectedAssignedTo=(String) hashXlData.get("Employee_Name").trim();
		String expectedAssignedTo=(String) hashXlData.get("Employee_Code").trim();
		
		//String expectedAssignedTo="iAg_test_ int";
		if (statusToChange.equalsIgnoreCase("assigned")) {
			
			fnpWaitForVisible("iAg_AI_TxtBx");
			String alreadyAssignee = fnpGetORObjectX("iAg_AI_TxtBx").getAttribute("value");

			if (alreadyAssignee.contains(expectedAssignedTo)) {
				// nothing to do now

			} else {
				fnpClick_OR("iAg_AI_AssignLkp");
				//fnpSearchNSelectFirstRadioBtn(2, ReassignTo, 1);
				//String expectedAssignedTo="iAg_test_ int";
				//fnpSearchNSelectFirstRadioBtn(2, (String) hashXlData.get("iAg_Login_user"), 1);
				//fnpSearchNSelectFirstRadioBtn(2, expectedAssignedTo, 1);
				fnpSearchNSelectFirstRadioBtn(1, (String) hashXlData.get("Employee_Code"), 1);
			
			}
		}
		
		fnpPFList("memshpAiStatus_PFList", "memshpAiStatus_PFListOptions", statusToChange);
		
		fnpClick_OR("iAg_AI_SaveBtn");
		
		fnpClick_OR("ContractReviewActionItemDialogCloseLink");
		
		int aiStatusColIndex = fnpFindColumnIndex_NOR(innerTableHeaderXpathForAI, "Status");
		actionItemInfoRowNo = fnpFindRow_NOR(innerTableDataXpathForAI, aiName, aiNameColIndex);
		String aiStatus = fnpFetchFromTable_NOR(innerTableDataXpathForAI, actionItemInfoRowNo, aiStatusColIndex);
		fnpMymsg("Current Status of this action item '" + aiName + "' is ---" + aiStatus ) ;
		if (aiStatus.equalsIgnoreCase(statusToChange)) {
			//nothing for now
		}else{
				throw new Exception("Status of AI '"+aiName+"' has not changed to '"+statusToChange+"'.");
		
			}
		
		
		
		if (statusToChange.equalsIgnoreCase("assigned")) {
			int assignedToColIndex = fnpFindColumnIndex_NOR(innerTableHeaderXpathForAI, "Assigned To");
			actionItemInfoRowNo = fnpFindRow_NOR(innerTableDataXpathForAI, aiName, aiNameColIndex);
			String assignedToValue = fnpFetchFromTable_NOR(innerTableDataXpathForAI, actionItemInfoRowNo, assignedToColIndex);
			fnpMymsg("Now this action item  '" + aiName + "' is assigned to---" + assignedToValue ) ;
			//if (assignedToValue.equalsIgnoreCase(expectedAssignedTo)) {
			if (assignedToValue.contains(expectedAssignedTo)) {
				fnpMymsg("Now this action item  '" + aiName + "' is assigned successfully to---" + expectedAssignedTo ) ;
			}else{
				throw new Exception("Now this action item  '" + aiName + "' is NOT assigned successfully to---'" + expectedAssignedTo +"'. " +
						" Actual is --'"+assignedToValue+"' and expected is --'"+expectedAssignedTo+"'" ) ;
			}
			
		}
		
		
		
		
	}
	

	public static void fnpProcessiAgAI2(String taskType,String taskDesc,String aiName,String statusToChange) throws Throwable{
		
		String expectedStatusInCaseWhenWeClickAssignToMeAndMoveInProgressONLY="";
		
		
		int taskTypeColIndex = fnpFindColumnIndex("TaskTabTable_HeaderRow", taskTable_TaskTypeColName);
		int taskDescriptionColIndex = fnpFindColumnIndex("TaskTabTable_HeaderRow", IPULSE_CONSTANTS.iAg_taskDesc_SnapshotTab);

		
		int newMemTaskTyperowNo = fnpFindRow("TaskTabTable", taskType, taskTypeColIndex);
		int newMemTaskDescriptonRowNo = fnpFindRow("TaskTabTable", taskDesc, taskDescriptionColIndex);

		
		
		String innerTableDataXpathForAI = OR.getProperty("iAgSnapshotTab_InnerTableForActionItemsTableData_part1") + (newMemTaskTyperowNo - 1)
				+ OR.getProperty("iAgSnapshotTab_InnerTableForActionItemsTableData_part2");
		String innerTableHeaderXpathForAI = OR.getProperty("iAgSnapshotTab_InnerTableForActionItemsTableHeader_part1") + (newMemTaskTyperowNo - 1)
				+ OR.getProperty("iAgSnapshotTab_InnerTableForActionItemsTableHeader_part2");
		
	
		
		
		int ActionItemColIndex = fnpFindColumnIndex_NOR(innerTableHeaderXpathForAI, "Action Item #");
		
		int aiNameColIndex = fnpFindColumnIndex_NOR(innerTableHeaderXpathForAI, "Action Item Name");
		
	//	String aiName="ContractReview";
		int actionItemInfoRowNo;
		try{
			 actionItemInfoRowNo = fnpFindRow_NOR(innerTableDataXpathForAI, aiName, aiNameColIndex);
		}catch(Throwable t){
			fnpMymsg("Either this AI '"+aiName+"' is not present or its name is changed. ");
			throw new Exception("Either this AI '"+aiName+"' is not present or its name is changed as script getting this error --"+t.getMessage());
		}
		
		
		
		
		
		String actionItemNo = fnpFetchFromTable_NOR(innerTableDataXpathForAI, actionItemInfoRowNo, ActionItemColIndex);
		

		//fnpClickALinkInATable_advance( OR.getProperty("iAgSnapshotTab_InnerTableForActionItemsTableData_part2"),  actionItemNo,  actionItemInfoRowNo);
		//fnpClickALinkInATable_advance( OR.getProperty("iAgSnapshotTab_InnerTableForActionItemsTableData_contains"),  actionItemNo,  actionItemInfoRowNo);
		
		
		fnpClickALinkInATable_advance_havingATagInside( OR.getProperty("iAgSnapshotTab_InnerTableForActionItemsTableData_contains"),  actionItemNo,  actionItemInfoRowNo);
		
		fnpMymsg("Clicked on  Action Item '" + aiName + "' in Table having no. is '" + actionItemNo + "' .");

		fnpLoading_wait();
		fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
		
		
		if ( (aiName.equalsIgnoreCase("CertificationDecision"))
				|| (aiName.equalsIgnoreCase("TechnicalReview"))
				|| (aiName.equalsIgnoreCase("PerformAudit"))
				|| (aiName.equalsIgnoreCase("CertificationIssue"))
						) {
			//nothing to do for now
		}else{
			//fnpClick_OR("AssignToMeAndMoveInProgress_btn");
			
			
			//sometime it is INPROCESS autoamtically through JOBS so sometime this button AssignToMeAndMoveInProgress_btn and sometime not
			String msg;
			if ((aiName.equalsIgnoreCase("RegProducts"))) {
				
				fnpLoading_wait();
				if (fnpCheckElementDisplayedImmediately("AssignToMeAndMoveInProgress_btn")) {
					msg="AssignToMeAndMoveInProgress_btn is visible in RegProducts AI.";
					fnpMymsg(msg);
					fnpClick_OR("AssignToMeAndMoveInProgress_btn");
				} else {
					msg="*************************************************************************************";
					fnpMymsg(msg);
					msg="AssignToMeAndMoveInProgress_btn is NOT visible in RegProducts AI, so assume  that it is IN Process by job. So going to verify its In Progress status";
					fnpMymsg(msg);
					String defaultValue = fnpGetText_OR("memshpAiStatus_PFList");
					if ((!defaultValue.equalsIgnoreCase("In Progress")) ) {
						 msg="AssignToMeAndMoveInProgress_btn button is not visisble, so  in that case it is assumption that it is completed automatically though batch job, so the status must be In Progress.";
						fnpMymsg(msg);
						Assert.assertEquals(defaultValue, "In Progress", msg);
					}else{
						msg="Status is automatically In Progress.";
						fnpMymsg(msg);
					}
					
					msg="*************************************************************************************";
					fnpMymsg(msg);
				}
				
				
			}else{
				//fnpClick_OR("AssignToMeAndMoveInProgress_btn");
				
				if (aiName.equalsIgnoreCase("ContractReview")) {
					if (statusToChange.equalsIgnoreCase("")) {
						fnpClick_OR("AssignToMeAndMoveInProgress_btn");
					}else{
						//leave it , it means we have alread assigned when we came here in this ai first time , now we are here in second time to change the status
					}
					
				} else {
					fnpClick_OR("AssignToMeAndMoveInProgress_btn");
				}
				
				
				
				
				
				
				
				
			}
			
			
			
			
			
			expectedStatusInCaseWhenWeClickAssignToMeAndMoveInProgressONLY="INPROGRESS";
		}
		
		
		
		
		
		if ( (aiName.equalsIgnoreCase("ContractReview"))){
			fnpType("OR", "iAgAi_actionitem_Notes", "Add info request test");
			
		}
		
		
		
		
		//String expectedAssignedTo=(String) hashXlData.get("Employee_Name").trim();
		//String expectedAssignedTo=(String) hashXlData.get("Employee_Code").trim();
		String expectedAssignedTo;
		long assigneeCode;
		if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
			expectedAssignedTo=(String) hashXlData.get("Employee_Code").trim();
			 assigneeCode=Integer.parseInt(expectedAssignedTo);
			fnpMymsg("Pradeep--int expectedAssignedTo is ---"+assigneeCode);
		}else{
			expectedAssignedTo=(String) hashXlData.get("Employee_Name").trim();
			fnpMymsg("Pradeep--String expectedAssignedTo is ---"+expectedAssignedTo);
		}
		
		
		
		
		//String expectedAssignedTo= hashXlData.get("Employee_Code").trim();
		

		
		//String expectedAssignedTo="iAg_test_ int";
		if (statusToChange.equalsIgnoreCase("assigned")) {
			
			fnpWaitForVisible("iAg_AI_TxtBx");
			String alreadyAssignee = fnpGetORObjectX("iAg_AI_TxtBx").getAttribute("value");

			if (alreadyAssignee.contains(expectedAssignedTo)) {
				// nothing to do now

			} else {
				fnpClick_OR("iAg_AI_AssignLkp");
				//fnpSearchNSelectFirstRadioBtn(2, ReassignTo, 1);
				//String expectedAssignedTo="iAg_test_ int";
				//fnpSearchNSelectFirstRadioBtn(2, (String) hashXlData.get("iAg_Login_user"), 1);
				//fnpSearchNSelectFirstRadioBtn(2, expectedAssignedTo, 3);
				//fnpSearchNSelectFirstRadioBtn(1, (String) hashXlData.get("Employee_Code"), 1);
				
				if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
					//fnpSearchNSelectFirstRadioBtn(1, (String) hashXlData.get("Employee_Code"), 1);
					fnpSearchNSelectFirstRadioBtn(1, expectedAssignedTo, 1);
				}else{
					//fnpSearchNSelectFirstRadioBtn(2, (String) hashXlData.get("iAg_Login_user"), 1);//iAg_test_int
					//fnpSearchNSelectFirstRadioBtn(2, (String) hashXlData.get("Employee_Name"), 1);//iAg_test_ int
					fnpSearchNSelectFirstRadioBtn(2,expectedAssignedTo, 1);//iAg_test_ int
					
				}
				
				
				
				fnpMymsg("Just after  selecting value for Assignee");
				String selectedAssignee = fnpGetORObjectX("iAg_AI_TxtBx").getAttribute("value");
				selectedAssignee = fnpWaitTillTextBoxDontHaveValue("iAg_AI_TxtBx", "value");
				Assert.assertTrue(selectedAssignee.contains(expectedAssignedTo), "Assignee is not selected properly from lookup");
				fnpMymsg(" Assignee is properly selected from  lookup");
			
			}
		}
		
		
		if (!(statusToChange.equalsIgnoreCase(""))) {
			//fnpPFList("memshpAiStatus_PFList", "memshpAiStatus_PFListOptions", statusToChange);
			fnpPFList_NotCheckingDefaultIsSameOrNot_specialCaseForiAg("memshpAiStatus_PFList", "memshpAiStatus_PFListOptions", statusToChange);
		}
		
		
		fnpClick_OR("iAg_AI_SaveBtn");
		
		fnpClick_OR("ContractReviewActionItemDialogCloseLink");
		
		fnpWaitTillThisElementIsNoLongerDisplayedTillMaxWait_OR("ContractReviewActionItemDialogCloseLink", "Action item popup is not closing after 60 seconds of clicking Close link.", 60);
		
		
			
		
		
		/**************After processing an action item, sometime new task row introduced **************/
		 newMemTaskTyperowNo = fnpFindRow("TaskTabTable", taskType, taskTypeColIndex);
		 newMemTaskDescriptonRowNo = fnpFindRow("TaskTabTable", taskDesc, taskDescriptionColIndex);

		
		
		 innerTableDataXpathForAI = OR.getProperty("iAgSnapshotTab_InnerTableForActionItemsTableData_part1") + (newMemTaskTyperowNo - 1)
				+ OR.getProperty("iAgSnapshotTab_InnerTableForActionItemsTableData_part2");
		 innerTableHeaderXpathForAI = OR.getProperty("iAgSnapshotTab_InnerTableForActionItemsTableHeader_part1") + (newMemTaskTyperowNo - 1)
				+ OR.getProperty("iAgSnapshotTab_InnerTableForActionItemsTableHeader_part2");
		
		 /*********************************************************************************/
		
		
				
				
		
		
		
		
		int aiStatusColIndex = fnpFindColumnIndex_NOR(innerTableHeaderXpathForAI, "Status");
		actionItemInfoRowNo = fnpFindRow_NOR(innerTableDataXpathForAI, aiName, aiNameColIndex);
		String aiStatus = fnpFetchFromTable_NOR(innerTableDataXpathForAI, actionItemInfoRowNo, aiStatusColIndex);
		fnpMymsg("Current Status of this action item '" + aiName + "' is ---" + aiStatus ) ;
		
		
		
		

		if (aiStatus.equalsIgnoreCase("failed")) {
			throw new Exception("Status of AI '"+aiName+"' has become failed.");			
		}else{
			//nothing for now
			}
		
		
		
		if (!(statusToChange.equalsIgnoreCase(""))) {
		
				if (aiStatus.equalsIgnoreCase(statusToChange)) {
					//nothing for now
				}else{
						throw new Exception("Status of AI '"+aiName+"' has not changed to '"+statusToChange+"'.");
				
					}
		}else{
			
			//No need to verify this, so commenting below code, may be verify in future
			
/*					
					//String expectedStatusInCaseWhenWeClickAssignToMeAndMoveInProgressONLY="INPROGRESS";
					if (aiStatus.equalsIgnoreCase(expectedStatusInCaseWhenWeClickAssignToMeAndMoveInProgressONLY)) {
						//nothing for now
					}else{
							throw new Exception("Status of AI '"+aiName+"' has not changed to '"+expectedStatusInCaseWhenWeClickAssignToMeAndMoveInProgressONLY+"'.");
					
						}
					
					*/
			
			
		}
		
		
		if ((statusToChange.equalsIgnoreCase("assigned"))  | (!(expectedStatusInCaseWhenWeClickAssignToMeAndMoveInProgressONLY.equalsIgnoreCase("")))  )  {
			int assignedToColIndex = fnpFindColumnIndex_NOR(innerTableHeaderXpathForAI, "Assigned To");
			actionItemInfoRowNo = fnpFindRow_NOR(innerTableDataXpathForAI, aiName, aiNameColIndex);
			String assignedToValue = fnpFetchFromTable_NOR(innerTableDataXpathForAI, actionItemInfoRowNo, assignedToColIndex);
			fnpMymsg("Now this action item  '" + aiName + "' is assigned to---" + assignedToValue ) ;
			//if (assignedToValue.equalsIgnoreCase(expectedAssignedTo)) {
			if (assignedToValue.contains(expectedAssignedTo)) {
				fnpMymsg("Now this action item  '" + aiName + "' is assigned successfully to---" + expectedAssignedTo ) ;
			}else{
				throw new Exception("Now this action item  '" + aiName + "' is NOT assigned successfully to---'" + expectedAssignedTo +"'. " +
						" Actual is --'"+assignedToValue+"' and expected is --'"+expectedAssignedTo+"'" ) ;
			}
	
			}

		}
		
		

	public static void fnpProcessiAgAI2_tryingMultipleTimes(String taskType,String taskDesc,String aiName,String statusToChange) throws Throwable{
		
		int maxTry=3;
		boolean failedDueToFailedStatus=false;
		//String aiStatus;
		
		int whileCounter=0;
		while(true){
			whileCounter++;
		
			try{
				String expectedStatusInCaseWhenWeClickAssignToMeAndMoveInProgressONLY="";
				
				
				int taskTypeColIndex = fnpFindColumnIndex("TaskTabTable_HeaderRow", taskTable_TaskTypeColName);
				int taskDescriptionColIndex = fnpFindColumnIndex("TaskTabTable_HeaderRow", IPULSE_CONSTANTS.iAg_taskDesc_SnapshotTab);
		
				
				int newMemTaskTyperowNo = fnpFindRow("TaskTabTable", taskType, taskTypeColIndex);
				int newMemTaskDescriptonRowNo = fnpFindRow("TaskTabTable", taskDesc, taskDescriptionColIndex);
		
				
				
				String innerTableDataXpathForAI = OR.getProperty("iAgSnapshotTab_InnerTableForActionItemsTableData_part1") + (newMemTaskTyperowNo - 1)
						+ OR.getProperty("iAgSnapshotTab_InnerTableForActionItemsTableData_part2");
				String innerTableHeaderXpathForAI = OR.getProperty("iAgSnapshotTab_InnerTableForActionItemsTableHeader_part1") + (newMemTaskTyperowNo - 1)
						+ OR.getProperty("iAgSnapshotTab_InnerTableForActionItemsTableHeader_part2");
				
			
				
				
				int ActionItemColIndex = fnpFindColumnIndex_NOR(innerTableHeaderXpathForAI, "Action Item #");
				
				int aiNameColIndex = fnpFindColumnIndex_NOR(innerTableHeaderXpathForAI, "Action Item Name");
				
			//	String aiName="ContractReview";
				int actionItemInfoRowNo;
				try{
					 actionItemInfoRowNo = fnpFindRow_NOR(innerTableDataXpathForAI, aiName, aiNameColIndex);
				}catch(Throwable t){
					fnpMymsg("Either this AI '"+aiName+"' is not present or its name is changed. ");
					throw new Exception("Either this AI '"+aiName+"' is not present or its name is changed as script getting this error --"+t.getMessage());
				}
				
				
				
				
				
				String actionItemNo = fnpFetchFromTable_NOR(innerTableDataXpathForAI, actionItemInfoRowNo, ActionItemColIndex);
				
		
				//fnpClickALinkInATable_advance( OR.getProperty("iAgSnapshotTab_InnerTableForActionItemsTableData_part2"),  actionItemNo,  actionItemInfoRowNo);
				//fnpClickALinkInATable_advance( OR.getProperty("iAgSnapshotTab_InnerTableForActionItemsTableData_contains"),  actionItemNo,  actionItemInfoRowNo);
				
				
				fnpClickALinkInATable_advance_havingATagInside( OR.getProperty("iAgSnapshotTab_InnerTableForActionItemsTableData_contains"),  actionItemNo,  actionItemInfoRowNo);
				
				fnpMymsg("Clicked on  Action Item '" + aiName + "' in Table having no. is '" + actionItemNo + "' .");
		
				fnpLoading_wait();
				fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
				
				
				if ( (aiName.equalsIgnoreCase("CertificationDecision"))
						|| (aiName.equalsIgnoreCase("TechnicalReview"))
						|| (aiName.equalsIgnoreCase("PerformAudit"))
						|| (aiName.equalsIgnoreCase("CertificationIssue"))
								) {
					//nothing to do for now
				}else{
					//fnpClick_OR("AssignToMeAndMoveInProgress_btn");
					
					
					//sometime it is INPROCESS autoamtically through JOBS so sometime this button AssignToMeAndMoveInProgress_btn and sometime not
					String msg;
					if ((aiName.equalsIgnoreCase("RegProducts"))) {
						
						fnpLoading_wait();
						if (fnpCheckElementDisplayedImmediately("AssignToMeAndMoveInProgress_btn")) {
							msg="AssignToMeAndMoveInProgress_btn is visible in RegProducts AI.";
							fnpMymsg(msg);
							fnpClick_OR("AssignToMeAndMoveInProgress_btn");
						} else {
							msg="*************************************************************************************";
							fnpMymsg(msg);
							msg="AssignToMeAndMoveInProgress_btn is NOT visible in RegProducts AI, so assume  that it is IN Process by job. So going to verify its In Progress status";
							fnpMymsg(msg);
							String defaultValue = fnpGetText_OR("memshpAiStatus_PFList");
							if ((!defaultValue.equalsIgnoreCase("In Progress")) ) {
								 msg="AssignToMeAndMoveInProgress_btn button is not visisble, so  in that case it is assumption that it is completed automatically though batch job, so the status must be In Progress.";
								fnpMymsg(msg);
								Assert.assertEquals(defaultValue, "In Progress", msg);
							}else{
								msg="Status is automatically In Progress.";
								fnpMymsg(msg);
							}
							
							msg="*************************************************************************************";
							fnpMymsg(msg);
						}
						
						
					}else{
					//	fnpClick_OR("AssignToMeAndMoveInProgress_btn");
						
						if (whileCounter==1) {
							fnpClick_OR("AssignToMeAndMoveInProgress_btn");
						} else {//if already clicked this button in first chance when whileCounter=1, then may or may not be it will come again
							if (fnpCheckElementDisplayedImmediately("AssignToMeAndMoveInProgress_btn")) {
								msg="AssignToMeAndMoveInProgress_btn is visible in RegProducts AI.";
								fnpMymsg(msg);
								fnpClick_OR("AssignToMeAndMoveInProgress_btn");
								}
						}
					
						}
					
					
					
					expectedStatusInCaseWhenWeClickAssignToMeAndMoveInProgressONLY="INPROGRESS";
				}
				
				//String expectedAssignedTo=(String) hashXlData.get("Employee_Name").trim();
				//String expectedAssignedTo=(String) hashXlData.get("Employee_Code").trim();
				String expectedAssignedTo;
				long assigneeCode;
				if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
					expectedAssignedTo=(String) hashXlData.get("Employee_Code").trim();
					 assigneeCode=Integer.parseInt(expectedAssignedTo);
					fnpMymsg("Pradeep--int expectedAssignedTo is ---"+assigneeCode);
				}else{
					expectedAssignedTo=(String) hashXlData.get("Employee_Name").trim();
					fnpMymsg("Pradeep--String expectedAssignedTo is ---"+expectedAssignedTo);
				}
				
				
				
				
				//String expectedAssignedTo= hashXlData.get("Employee_Code").trim();
				
		
				
				//String expectedAssignedTo="iAg_test_ int";
				if (statusToChange.equalsIgnoreCase("assigned")) {
					
					fnpWaitForVisible("iAg_AI_TxtBx");
					String alreadyAssignee = fnpGetORObjectX("iAg_AI_TxtBx").getAttribute("value");
		
					if (alreadyAssignee.contains(expectedAssignedTo)) {
						// nothing to do now
		
					} else {
						fnpClick_OR("iAg_AI_AssignLkp");
						//fnpSearchNSelectFirstRadioBtn(2, ReassignTo, 1);
						//String expectedAssignedTo="iAg_test_ int";
						//fnpSearchNSelectFirstRadioBtn(2, (String) hashXlData.get("iAg_Login_user"), 1);
						//fnpSearchNSelectFirstRadioBtn(2, expectedAssignedTo, 3);
						//fnpSearchNSelectFirstRadioBtn(1, (String) hashXlData.get("Employee_Code"), 1);
						
						if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
							//fnpSearchNSelectFirstRadioBtn(1, (String) hashXlData.get("Employee_Code"), 1);
							fnpSearchNSelectFirstRadioBtn(1, expectedAssignedTo, 1);
						}else{
							//fnpSearchNSelectFirstRadioBtn(2, (String) hashXlData.get("iAg_Login_user"), 1);//iAg_test_int
							//fnpSearchNSelectFirstRadioBtn(2, (String) hashXlData.get("Employee_Name"), 1);//iAg_test_ int
							fnpSearchNSelectFirstRadioBtn(2,expectedAssignedTo, 1);//iAg_test_ int
							
						}
						
						
						
						fnpMymsg("Just after  selecting value for Assignee");
						String selectedAssignee = fnpGetORObjectX("iAg_AI_TxtBx").getAttribute("value");
						selectedAssignee = fnpWaitTillTextBoxDontHaveValue("iAg_AI_TxtBx", "value");
						Assert.assertTrue(selectedAssignee.contains(expectedAssignedTo), "Assignee is not selected properly from lookup");
						fnpMymsg(" Assignee is properly selected from  lookup");
					
					}
				}
				
				
				if (!(statusToChange.equalsIgnoreCase(""))) {
					//fnpPFList("memshpAiStatus_PFList", "memshpAiStatus_PFListOptions", statusToChange);
					fnpPFList_NotCheckingDefaultIsSameOrNot_specialCaseForiAg("memshpAiStatus_PFList", "memshpAiStatus_PFListOptions", statusToChange);
				}
				
				
				fnpClick_OR("iAg_AI_SaveBtn");
				
				fnpClick_OR("ContractReviewActionItemDialogCloseLink");
				
				fnpWaitTillThisElementIsNoLongerDisplayedTillMaxWait_OR("ContractReviewActionItemDialogCloseLink", "Action item popup is not closing after 60 seconds of clicking Close link.", 60);
				
				
					
				
				
				/**************After processing an action item, sometime new task row introduced **************/
				 newMemTaskTyperowNo = fnpFindRow("TaskTabTable", taskType, taskTypeColIndex);
				 newMemTaskDescriptonRowNo = fnpFindRow("TaskTabTable", taskDesc, taskDescriptionColIndex);
		
				
				
				 innerTableDataXpathForAI = OR.getProperty("iAgSnapshotTab_InnerTableForActionItemsTableData_part1") + (newMemTaskTyperowNo - 1)
						+ OR.getProperty("iAgSnapshotTab_InnerTableForActionItemsTableData_part2");
				 innerTableHeaderXpathForAI = OR.getProperty("iAgSnapshotTab_InnerTableForActionItemsTableHeader_part1") + (newMemTaskTyperowNo - 1)
						+ OR.getProperty("iAgSnapshotTab_InnerTableForActionItemsTableHeader_part2");
				
				 /*********************************************************************************/
				
				
						
						
				
				
				
				
				int aiStatusColIndex = fnpFindColumnIndex_NOR(innerTableHeaderXpathForAI, "Status");
				actionItemInfoRowNo = fnpFindRow_NOR(innerTableDataXpathForAI, aiName, aiNameColIndex);
				String aiStatus = fnpFetchFromTable_NOR(innerTableDataXpathForAI, actionItemInfoRowNo, aiStatusColIndex);
				fnpMymsg("Current Status of this action item '" + aiName + "' is ---" + aiStatus ) ;
				
				
				
				
		
				if (aiStatus.equalsIgnoreCase("failed")) {
					failedDueToFailedStatus=true;
					msg="Status of AI '"+aiName+"' has become failed for chance --"+whileCounter;
					fnpMymsg(msg);
					throw new Exception(msg);			
				}else{
					//nothing for now
					}
				
			
		
					if (!(statusToChange.equalsIgnoreCase(""))) {
					
							if (aiStatus.equalsIgnoreCase(statusToChange)) {
								//nothing for now
							}else{
									throw new Exception("Status of AI '"+aiName+"' has not changed to '"+statusToChange+"'.");
							
								}
					}else{
						
						//No need to verify this, so commenting below code, may be verify in future
						
			/*					
								//String expectedStatusInCaseWhenWeClickAssignToMeAndMoveInProgressONLY="INPROGRESS";
								if (aiStatus.equalsIgnoreCase(expectedStatusInCaseWhenWeClickAssignToMeAndMoveInProgressONLY)) {
									//nothing for now
								}else{
										throw new Exception("Status of AI '"+aiName+"' has not changed to '"+expectedStatusInCaseWhenWeClickAssignToMeAndMoveInProgressONLY+"'.");
								
									}
								
								*/
						
						
					}
					
					
					if ((statusToChange.equalsIgnoreCase("assigned"))  | (!(expectedStatusInCaseWhenWeClickAssignToMeAndMoveInProgressONLY.equalsIgnoreCase("")))  )  {
						int assignedToColIndex = fnpFindColumnIndex_NOR(innerTableHeaderXpathForAI, "Assigned To");
						actionItemInfoRowNo = fnpFindRow_NOR(innerTableDataXpathForAI, aiName, aiNameColIndex);
						String assignedToValue = fnpFetchFromTable_NOR(innerTableDataXpathForAI, actionItemInfoRowNo, assignedToColIndex);
						fnpMymsg("Now this action item  '" + aiName + "' is assigned to---" + assignedToValue ) ;
						//if (assignedToValue.equalsIgnoreCase(expectedAssignedTo)) {
						if (assignedToValue.contains(expectedAssignedTo)) {
							fnpMymsg("Now this action item  '" + aiName + "' is assigned successfully to---" + expectedAssignedTo ) ;
						}else{
							throw new Exception("Now this action item  '" + aiName + "' is NOT assigned successfully to---'" + expectedAssignedTo +"'. " +
									" Actual is --'"+assignedToValue+"' and expected is --'"+expectedAssignedTo+"'" ) ;
						}
				
						}
					
					
					break;
					
					
			}catch(Throwable t){
				
				if (failedDueToFailedStatus) {
					if (whileCounter<maxTry) {
						fnpMymsg("repeat again all steps after refresh the browser once as suggested by Sudhir");
						driver.navigate().refresh();
						Thread.sleep(5000);
					} else {
						throw t;
					}
					
				} else {
				throw t;
			}
		}
	
	}		
					
					
					

}
	

	public static void fnpProcessiAgAI2_tryingMultipleTimes_2(String taskType,String taskDesc,String aiName,String statusToChange) throws Throwable{
		
		String expectedStatusInCaseWhenWeClickAssignToMeAndMoveInProgressONLY="";
		
		
		int taskTypeColIndex = fnpFindColumnIndex("TaskTabTable_HeaderRow", taskTable_TaskTypeColName);
		int taskDescriptionColIndex = fnpFindColumnIndex("TaskTabTable_HeaderRow", IPULSE_CONSTANTS.iAg_taskDesc_SnapshotTab);

		
		int newMemTaskTyperowNo = fnpFindRow("TaskTabTable", taskType, taskTypeColIndex);
		int newMemTaskDescriptonRowNo = fnpFindRow("TaskTabTable", taskDesc, taskDescriptionColIndex);

		
		
		String innerTableDataXpathForAI = OR.getProperty("iAgSnapshotTab_InnerTableForActionItemsTableData_part1") + (newMemTaskTyperowNo - 1)
				+ OR.getProperty("iAgSnapshotTab_InnerTableForActionItemsTableData_part2");
		String innerTableHeaderXpathForAI = OR.getProperty("iAgSnapshotTab_InnerTableForActionItemsTableHeader_part1") + (newMemTaskTyperowNo - 1)
				+ OR.getProperty("iAgSnapshotTab_InnerTableForActionItemsTableHeader_part2");
		
	
		
		
		int ActionItemColIndex = fnpFindColumnIndex_NOR(innerTableHeaderXpathForAI, "Action Item #");
		
		int aiNameColIndex = fnpFindColumnIndex_NOR(innerTableHeaderXpathForAI, "Action Item Name");
		
	//	String aiName="ContractReview";
		int actionItemInfoRowNo;
		try{
			 actionItemInfoRowNo = fnpFindRow_NOR(innerTableDataXpathForAI, aiName, aiNameColIndex);
		}catch(Throwable t){
			fnpMymsg("Either this AI '"+aiName+"' is not present or its name is changed. ");
			throw new Exception("Either this AI '"+aiName+"' is not present or its name is changed as script getting this error --"+t.getMessage());
		}
		
		
		
		
		
		String actionItemNo = fnpFetchFromTable_NOR(innerTableDataXpathForAI, actionItemInfoRowNo, ActionItemColIndex);
		

		//fnpClickALinkInATable_advance( OR.getProperty("iAgSnapshotTab_InnerTableForActionItemsTableData_part2"),  actionItemNo,  actionItemInfoRowNo);
		//fnpClickALinkInATable_advance( OR.getProperty("iAgSnapshotTab_InnerTableForActionItemsTableData_contains"),  actionItemNo,  actionItemInfoRowNo);
		
		
		fnpClickALinkInATable_advance_havingATagInside( OR.getProperty("iAgSnapshotTab_InnerTableForActionItemsTableData_contains"),  actionItemNo,  actionItemInfoRowNo);
		
		fnpMymsg("Clicked on  Action Item '" + aiName + "' in Table having no. is '" + actionItemNo + "' .");

		fnpLoading_wait();
		fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
		
		
		if ( (aiName.equalsIgnoreCase("CertificationDecision"))
				|| (aiName.equalsIgnoreCase("TechnicalReview"))
				|| (aiName.equalsIgnoreCase("PerformAudit"))
				|| (aiName.equalsIgnoreCase("CertificationIssue"))
						) {
			//nothing to do for now
		}else{
			//fnpClick_OR("AssignToMeAndMoveInProgress_btn");
			
			
			//sometime it is INPROCESS autoamtically through JOBS so sometime this button AssignToMeAndMoveInProgress_btn and sometime not
			String msg;
			if ((aiName.equalsIgnoreCase("RegProducts"))) {
				
				fnpLoading_wait();
				if (fnpCheckElementDisplayedImmediately("AssignToMeAndMoveInProgress_btn")) {
					msg="AssignToMeAndMoveInProgress_btn is visible in RegProducts AI.";
					fnpMymsg(msg);
					fnpClick_OR("AssignToMeAndMoveInProgress_btn");
				} else {
					msg="*************************************************************************************";
					fnpMymsg(msg);
					msg="AssignToMeAndMoveInProgress_btn is NOT visible in RegProducts AI, so assume  that it is IN Process by job. So going to verify its In Progress status";
					fnpMymsg(msg);
					String defaultValue = fnpGetText_OR("memshpAiStatus_PFList");
					if ((!defaultValue.equalsIgnoreCase("In Progress")) ) {
						 msg="AssignToMeAndMoveInProgress_btn button is not visisble, so  in that case it is assumption that it is completed automatically though batch job, so the status must be In Progress.";
						fnpMymsg(msg);
						Assert.assertEquals(defaultValue, "In Progress", msg);
					}else{
						msg="Status is automatically In Progress.";
						fnpMymsg(msg);
					}
					
					msg="*************************************************************************************";
					fnpMymsg(msg);
				}
				
				
			}else{
				fnpClick_OR("AssignToMeAndMoveInProgress_btn");
			}
			
			
			
			
			
			expectedStatusInCaseWhenWeClickAssignToMeAndMoveInProgressONLY="INPROGRESS";
		}
		
		//String expectedAssignedTo=(String) hashXlData.get("Employee_Name").trim();
		//String expectedAssignedTo=(String) hashXlData.get("Employee_Code").trim();
		String expectedAssignedTo;
		long assigneeCode;
		if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
			expectedAssignedTo=(String) hashXlData.get("Employee_Code").trim();
			 assigneeCode=Integer.parseInt(expectedAssignedTo);
			fnpMymsg("Pradeep--int expectedAssignedTo is ---"+assigneeCode);
		}else{
			expectedAssignedTo=(String) hashXlData.get("Employee_Name").trim();
			fnpMymsg("Pradeep--String expectedAssignedTo is ---"+expectedAssignedTo);
		}
		
		
		
		
		//String expectedAssignedTo= hashXlData.get("Employee_Code").trim();
		

		
		//String expectedAssignedTo="iAg_test_ int";
		if (statusToChange.equalsIgnoreCase("assigned")) {
			
			fnpWaitForVisible("iAg_AI_TxtBx");
			String alreadyAssignee = fnpGetORObjectX("iAg_AI_TxtBx").getAttribute("value");

			if (alreadyAssignee.contains(expectedAssignedTo)) {
				// nothing to do now

			} else {
				fnpClick_OR("iAg_AI_AssignLkp");
				//fnpSearchNSelectFirstRadioBtn(2, ReassignTo, 1);
				//String expectedAssignedTo="iAg_test_ int";
				//fnpSearchNSelectFirstRadioBtn(2, (String) hashXlData.get("iAg_Login_user"), 1);
				//fnpSearchNSelectFirstRadioBtn(2, expectedAssignedTo, 3);
				//fnpSearchNSelectFirstRadioBtn(1, (String) hashXlData.get("Employee_Code"), 1);
				
				if (Boolean.parseBoolean(CONFIG.getProperty("ClientName_Masking")) ){
					//fnpSearchNSelectFirstRadioBtn(1, (String) hashXlData.get("Employee_Code"), 1);
					fnpSearchNSelectFirstRadioBtn(1, expectedAssignedTo, 1);
				}else{
					//fnpSearchNSelectFirstRadioBtn(2, (String) hashXlData.get("iAg_Login_user"), 1);//iAg_test_int
					//fnpSearchNSelectFirstRadioBtn(2, (String) hashXlData.get("Employee_Name"), 1);//iAg_test_ int
					fnpSearchNSelectFirstRadioBtn(2,expectedAssignedTo, 1);//iAg_test_ int
					
				}
				
				
				
				fnpMymsg("Just after  selecting value for Assignee");
				String selectedAssignee = fnpGetORObjectX("iAg_AI_TxtBx").getAttribute("value");
				selectedAssignee = fnpWaitTillTextBoxDontHaveValue("iAg_AI_TxtBx", "value");
				Assert.assertTrue(selectedAssignee.contains(expectedAssignedTo), "Assignee is not selected properly from lookup");
				fnpMymsg(" Assignee is properly selected from  lookup");
			
			}
		}
		
		
		if (!(statusToChange.equalsIgnoreCase(""))) {
			//fnpPFList("memshpAiStatus_PFList", "memshpAiStatus_PFListOptions", statusToChange);
			fnpPFList_NotCheckingDefaultIsSameOrNot_specialCaseForiAg("memshpAiStatus_PFList", "memshpAiStatus_PFListOptions", statusToChange);
		}
		
		
		
		String currentValue = fnpGetText_OR("memshpAiStatus_PFList").toLowerCase().trim();
		if (!((currentValue.equalsIgnoreCase("failed")) & (currentValue.contains("fail")))) {
			fnpClick_OR("iAg_AI_SaveBtn");
		}
		
		
		
		
		
		
		int maxTry=3;
		int maxWaitForAPI=20; // in seconds
		boolean failedDueToFailedStatus=false;
		//String aiStatus;
		
		int whileCounter=0;
		ArrayList<String> dropDownValues=new ArrayList<String>();
		dropDownValues.add("In Progress");
		while(true){
			whileCounter++;
		
			try{
		
				//fnpPFList(XpathKey, XpathOptionsKey, value);
				
				
				 currentValue = fnpGetText_OR("memshpAiStatus_PFList").toLowerCase().trim();
				if ((currentValue.equalsIgnoreCase("failed")) & (currentValue.contains("fail"))) {
					//Thread.sleep(25000);
					fnpMymsg("Status of AI '"+aiName+"' has become failed.");
					Thread.sleep(maxWaitForAPI*1000);
					
					fnpMymsg("@ - Now going to try again as suggested by sudhir sir on 02-04-2020-- chance --"+whileCounter);
					
					if (whileCounter<maxTry) {
						//fnpMymsg("repeat again all steps after refresh the browser once as suggested by Sudhir");
						//driver.navigate().refresh();
						//Thread.sleep(5000);
					} else {
						msg="Status of AI '"+aiName+"' is failed even after trying '"+maxTry+"' times.";
						fnpMymsg(msg);
						throw new Exception(msg);
					}

					
					
					try{
						fnpPFList_VerifyOptionsValues("memshpAiStatus_PFList", "memshpAiStatus_PFListOptions", dropDownValues) ;
						//fnpPFList_VerifyOptionsValues_Contains(String XpathKey, String XpathOptionsKey, List <String>values)
					}catch(Throwable t){
						if  ( (t.getMessage().contains("This value") ) &
								(t.getMessage().contains("is NOT present in the drop down."))) {
							
							Thread.sleep(10000);
						} else {
							//nothing for now
						}
						
					}
					
					//Thread.sleep(10000);
					fnpPFList_NotCheckingDefaultIsSameOrNot_specialCaseForiAg("memshpAiStatus_PFList", "memshpAiStatus_PFListOptions", "In Progress");
					Thread.sleep(10000);
					fnpClick_OR("iAg_AI_SaveBtn");
					

					
				}else{
					break;
				}
				
				
				//break;
			}catch(Throwable t){
				
				if (whileCounter>maxTry) {
					throw t;
					
				}
				
			}
		
		}
		
		
		fnpClick_OR("ContractReviewActionItemDialogCloseLink");
		
		fnpWaitTillThisElementIsNoLongerDisplayedTillMaxWait_OR("ContractReviewActionItemDialogCloseLink", "Action item popup is not closing after 60 seconds of clicking Close link.", 60);
		
		
			
		
		
		/**************After processing an action item, sometime new task row introduced **************/
		 newMemTaskTyperowNo = fnpFindRow("TaskTabTable", taskType, taskTypeColIndex);
		 newMemTaskDescriptonRowNo = fnpFindRow("TaskTabTable", taskDesc, taskDescriptionColIndex);

		
		
		 innerTableDataXpathForAI = OR.getProperty("iAgSnapshotTab_InnerTableForActionItemsTableData_part1") + (newMemTaskTyperowNo - 1)
				+ OR.getProperty("iAgSnapshotTab_InnerTableForActionItemsTableData_part2");
		 innerTableHeaderXpathForAI = OR.getProperty("iAgSnapshotTab_InnerTableForActionItemsTableHeader_part1") + (newMemTaskTyperowNo - 1)
				+ OR.getProperty("iAgSnapshotTab_InnerTableForActionItemsTableHeader_part2");
		
		 /*********************************************************************************/
		
		
				
				
		
		
		
		
		int aiStatusColIndex = fnpFindColumnIndex_NOR(innerTableHeaderXpathForAI, "Status");
		actionItemInfoRowNo = fnpFindRow_NOR(innerTableDataXpathForAI, aiName, aiNameColIndex);
		String aiStatus = fnpFetchFromTable_NOR(innerTableDataXpathForAI, actionItemInfoRowNo, aiStatusColIndex);
		fnpMymsg("Current Status of this action item '" + aiName + "' is ---" + aiStatus ) ;
		
		
		
		

		if (aiStatus.equalsIgnoreCase("failed")) {
			throw new Exception("Status of AI '"+aiName+"' has become failed.");			
		}else{
			//nothing for now
			}
		
		
		
		if (!(statusToChange.equalsIgnoreCase(""))) {
		
				if (aiStatus.equalsIgnoreCase(statusToChange)) {
					//nothing for now
				}else{
						throw new Exception("Status of AI '"+aiName+"' has not changed to '"+statusToChange+"'.");
				
					}
		}else{
			
			//No need to verify this, so commenting below code, may be verify in future
			
/*					
					//String expectedStatusInCaseWhenWeClickAssignToMeAndMoveInProgressONLY="INPROGRESS";
					if (aiStatus.equalsIgnoreCase(expectedStatusInCaseWhenWeClickAssignToMeAndMoveInProgressONLY)) {
						//nothing for now
					}else{
							throw new Exception("Status of AI '"+aiName+"' has not changed to '"+expectedStatusInCaseWhenWeClickAssignToMeAndMoveInProgressONLY+"'.");
					
						}
					
					*/
			
			
		}
		
		
		if ((statusToChange.equalsIgnoreCase("assigned"))  | (!(expectedStatusInCaseWhenWeClickAssignToMeAndMoveInProgressONLY.equalsIgnoreCase("")))  )  {
			int assignedToColIndex = fnpFindColumnIndex_NOR(innerTableHeaderXpathForAI, "Assigned To");
			actionItemInfoRowNo = fnpFindRow_NOR(innerTableDataXpathForAI, aiName, aiNameColIndex);
			String assignedToValue = fnpFetchFromTable_NOR(innerTableDataXpathForAI, actionItemInfoRowNo, assignedToColIndex);
			fnpMymsg("Now this action item  '" + aiName + "' is assigned to---" + assignedToValue ) ;
			//if (assignedToValue.equalsIgnoreCase(expectedAssignedTo)) {
			if (assignedToValue.contains(expectedAssignedTo)) {
				fnpMymsg("Now this action item  '" + aiName + "' is assigned successfully to---" + expectedAssignedTo ) ;
			}else{
				throw new Exception("Now this action item  '" + aiName + "' is NOT assigned successfully to---'" + expectedAssignedTo +"'. " +
						" Actual is --'"+assignedToValue+"' and expected is --'"+expectedAssignedTo+"'" ) ;
			}
	
			}

		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static SoftAssert fnpVerifyOasisIsOpeningOrNotFromiAgAndReturnBack(String taskName,String ORObjectToClick,SoftAssert softAssert) throws Throwable{
		
		String originalHandle = driver.getWindowHandle();
		
		
		
		Thread.sleep(2000);
		ArrayList<String> listoldtabs2 = new ArrayList<String>(driver.getWindowHandles());
		fnpGetORObjectX("iAg_ecapSPA_JumpToOasisBtn").click();
		Thread.sleep(2000);
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

		Thread.sleep(5000);

		fnpMymsg("we are in Oasis tab");
		int tabsCount = tabs.size();
		for (int i = 0; i < tabsCount; i++) {

			fnpMymsg((i + 1) + "  window handle is:" + tabs.get(i));

			if (originalHandle.equalsIgnoreCase(tabs.get(i))) {
				// nothing to do

			} else {

				driver.switchTo().window(tabs.get(i));
				Thread.sleep(2000);
				// Thread.sleep(500);
				break;
			}

		}

		fnpMymsg("Going to check oasis is opened or not.");
		
		
		try{
			fnpCheckErrorUsingPageSource_Oasis_Skipping_NoDataFound();
			fnpCheckErrorPageNotFound() ;
			fnpWaitForVisible("Oasis_Home_menu");
			fnpMymsg("Oasis is opened successfully.");
		}catch(Throwable t){
			fnpMymsg("Oasis is not getting opened successfully.");
		//	throw new Exception("Oasis is not getting opened.");
			
		//	fnpDesireScreenshot("Oasis is not getting opened successfully from task ---"+taskName);
			softAssert = new SoftAssert();
			fnpMymsg("Oasis is not getting opened successfully.");
			softAssert.fail("Oasis is not getting opened successfully from task '"+taskName+"' as getting this error ---"+t.getMessage());
			fnpDesireScreenshot("Oasis is not getting opened successfully from task ---"+taskName);
		}
		
		

		
		
		
		
		driver.close();
		// driver.quit();

		driver.switchTo().window(originalHandle);
		
		
		return softAssert;
		
	}
	
	
	
	
	
	
	
	

	
	
	

	/******* Check error using page source ****/
	public static void fnpCheckErrorUsingPageSource_Oasis_Skipping_NoDataFound() throws Throwable {

		try {
			String PageSourceText = driver.getPageSource().toLowerCase();

			if (PageSourceText.contains("#ff0000")) {

				List<WebElement> errMsgList;

				errMsgList = fnpGetORObject_list("Oasis_error_message_xpath", 5);
				int sizeErrMsgList = errMsgList.size();

				driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);
				for (Iterator iterator = errMsgList.iterator(); iterator.hasNext();) {

					try {
						WebElement webElement = (WebElement) iterator.next();

						if (webElement.isDisplayed()) {
							// fnpCheckError_Oasis("");

							fnpMymsg("@  ---running function fnpCheckError_Oasis");
							if (fnpCheckElementDisplayedImmediately("Oasis_error_message_xpath")) {
	
								fnpMouseHover_OR_EvenMultipleObjectsHavingSameXpath_usingSpecialForErrorClassOnly("Oasis_error_message_xpath");
								String errMsg = fnpGetText_OR_EvenMultipleObjectsHavingSameXpath_specialForExtractingErrorMsgTextOnlyEvenIfNotVisible("Oasis_error_message_xpath");


								
/*								if ((errMsg.length() > 3) & (!(errMsg.equalsIgnoreCase("* Lead Audit")))) {
									fnpMymsg(" Error is thrown by Oasis  application " + errMsg);
									throw new Exception(" Error is thrown by application. " + "\n\n Error is --->" + errMsg);
								}
								*/
								
								if (    (errMsg.length() > 3) & 
										(!(errMsg.equalsIgnoreCase("* Lead Audit")))
										& (!(errMsg.equalsIgnoreCase("No Data Found.")))
										//& (!(errMsg.contains("No Data Found")))
										
										) {
									fnpMymsg(" Error is thrown by Oasis  application " + errMsg);
									throw new Exception(" Error is thrown by application. " + "\n\n Error is --->" + errMsg);
								}
								
									
								

							}

						}
					} catch (org.openqa.selenium.StaleElementReferenceException s) {
						// nothing to do
					} catch (org.openqa.selenium.NoSuchWindowException N) {
						// nothing to do

					} finally {
						driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);

					}

				}

			}

			if (PageSourceText.contains("#ff0000")) {

				List<WebElement> errMsgList;

				errMsgList = fnpGetORObject_list("Oasis_error_message_xpath2", 5);

				int sizeErrMsgList = errMsgList.size();
				fnpMymsg("   --sizeErrMsgList of Oasis (Oasis error count) Oasis_error_message_xpath2 --" + sizeErrMsgList);

				driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);
				for (Iterator iterator = errMsgList.iterator(); iterator.hasNext();) {

					try {
						WebElement webElement = (WebElement) iterator.next();

						fnpMymsg("   ---Oasis error ----is displayed? --" + webElement.isDisplayed());
						if (webElement.isDisplayed()) {

							fnpMymsg("@  ---running function fnpCheckError_Oasis");
							if (fnpCheckElementDisplayedImmediately("Oasis_error_message_xpath2")) {

								fnpMouseHover_OR_EvenMultipleObjectsHavingSameXpath_usingSpecialForErrorClassOnly("Oasis_error_message_xpath2");
								String errMsg = fnpGetText_OR_EvenMultipleObjectsHavingSameXpath_specialForExtractingErrorMsgTextOnlyEvenIfNotVisible("Oasis_error_message_xpath2");

								// if (errMsg.length() > 3) {
								//if ((errMsg.length() > 3) & (!(errMsg.equalsIgnoreCase("* Lead Audit")))) {
								if (    (errMsg.length() > 3) & 
										(!(errMsg.equalsIgnoreCase("* Lead Audit")))
										& (!(errMsg.equalsIgnoreCase("No Data Found.")))
										//& (!(errMsg.contains("No Data Found")))
										
										) {	
									
									
									
									
									fnpMymsg(" Error is thrown by Oasis  application " + errMsg);
									// htmlSubMessage=htmlSubMessage+"<Font Color=Red> Error is thrown by application in --"+classNameText+" i.e. '"+errMsg+"'  </Font> ";
									throw new Exception(" Error is thrown by application. " + "\n\n Error is --->" + errMsg);

								}

							}

						}
					} catch (org.openqa.selenium.StaleElementReferenceException s) {
						// nothing to do
					} catch (org.openqa.selenium.NoSuchWindowException N) {
						// nothing to do

					} finally {
						driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);

					}

				}

			}

			if (PageSourceText.contains("diverror")) {

				List<WebElement> errMsgList;

				errMsgList = fnpGetORObject_list("Oasis_error_inDiv", 5);

				int sizeErrMsgList = errMsgList.size();

				driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);
				for (Iterator iterator = errMsgList.iterator(); iterator.hasNext();) {

					try {
						WebElement webElement = (WebElement) iterator.next();

						if (webElement.isDisplayed()) {
							// fnpCheckError_Oasis("");

							fnpMymsg("@  ---running function fnpCheckError_Oasis");
							if (fnpCheckElementDisplayedImmediately("Oasis_error_inDiv")) {

								fnpMouseHover_OR_EvenMultipleObjectsHavingSameXpath_usingSpecialForErrorClassOnly("Oasis_error_inDiv");
								String errMsg = fnpGetText_OR_EvenMultipleObjectsHavingSameXpath_specialForExtractingErrorMsgTextOnlyEvenIfNotVisible("Oasis_error_inDiv");

								if (errMsg.length() > 3) {
									fnpMymsg(" Error is thrown by Oasis  application " + errMsg);
									// htmlSubMessage=htmlSubMessage+"<Font Color=Red> Error is thrown by application in --"+classNameText+" i.e. '"+errMsg+"'  </Font> ";
									throw new Exception(" Error is thrown by application. " + "\n\n Error is --->" + errMsg);

								}
								
								
								
								
								
								
								

							}

						}
					} catch (org.openqa.selenium.StaleElementReferenceException s) {
						// nothing to do
					} catch (org.openqa.selenium.NoSuchWindowException N) {
						// nothing to do

					} finally {
						driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);

					}

				}

			}
		} catch (org.openqa.selenium.WebDriverException we) {
			String errorMessagg = we.getMessage();
			if (errorMessagg.contains("Error determining if element is displayed")) {
				// nothing to do
				fnpMymsg("@  ---@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
				fnpMymsg("@  ---Error is thrown by webDriver in function fnpCheckErrorUsingPageSource_Oasis. And error is --" + errorMessagg);
				fnpMymsg("@  ---@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			} else {
				throw we;
			}

		}

	}

	
	
	
	public static void fnpCallWebService_GG_interface_Action_Items() throws Throwable{
		String originalHandle1 = driver.getWindowHandle();
		
		String a1 = "window.open('about:blank','_blank');";
		((JavascriptExecutor)driver).executeScript(a1);
		
		
		ArrayList<String> tabs1 = new ArrayList<String> (driver.getWindowHandles());

		driver.switchTo().window(tabs1.get(1));
		
		env=CONFIG.getProperty("Environment");
		
		
		try{
			if (env.equalsIgnoreCase("Test")) {
			//driver.navigate().to("https://ecapGG:NSFukiag1@test.globalgap.org/globalgapaxis/services/Globalgap?wsdl");
			//driver.navigate().to("https://PHooker:NSFukiag1@test.globalgap.org/globalgapaxis/services/Globalgap?wsdl");
			driver.navigate().to("https://PHooker:NSFukiag1@training.globalgap.org/globalgapaxis/services/Globalgap?wsdl");
			}else{
				//driver.navigate().to("https://ecapGG:NSFukiag1@test.globalgap.org/globalgapaxis/services/Globalgap?wsdl");
				//driver.navigate().to("https://PHooker:NSFukiag1@test.globalgap.org/globalgapaxis/services/Globalgap?wsdl");
				driver.navigate().to("https://PHooker:NSFukiag1@training.globalgap.org/globalgapaxis/services/Globalgap?wsdl");
				//throw new Exception("Please provide url [GLOBALG.A.P. web service ] for STAGE");
			}
		}catch(Throwable t){
			msg="Global gap url or credentials are not working.";
			fnpDesireScreenshot("global_gap_url_OR_credentials_not_working");
			fnpMymsg(msg);
			throw new Exception(msg);
		}
		Thread.sleep(5000);
		
		//String PageSourceText = driver.getPageSource().toLowerCase();
		String PageSourceText = driver.getPageSource();
		//if (PageSourceText.contains("Error 503")  | PageSourceText.contains("We are really sorry") ) {
		if (PageSourceText.contains("Error 503")  || PageSourceText.contains("We are really sorry") ) {
			String msg="GlobalGap url is not working properly. See screenshot for reference.";
			fnpMymsg(msg);
			throw new Exception(msg);
			
		}
		
		
		
		driver.close();
		
		driver.switchTo().window(originalHandle1);
	}
	
	
	
	
	
	
	
	
	
	
	
public static void fnpFetchingAndInsertingValuesInUploadingExcelForOption2() throws Throwable{

		
		
		fnpDisplayingMessageInFrame("Now script is going to fetch the values and inserting into the excel.", 20);
		
		
		String uploadFolderName1 = System.getProperty("user.dir") + "\\docs\\iAg_option2\\copyThroughScript";
		File folder1 = new File(uploadFolderName1);
		cleanDirectory(folder1);
		
		
		String uploadFolderName2 = System.getProperty("user.dir") + "\\docs\\iAg_option2\\ModifiedThoroughScript";
		File folder2 = new File(uploadFolderName2);
		cleanDirectory(folder2);
		
		
		String pathfromCopy=System.getProperty("user.dir")+"\\docs\\iAg_option2\\original\\"+iAgOption2FileName;
		String pathtoCopy1 = uploadFolderName1+"\\"+iAgOption2FileName;
		
		File file = new File(pathfromCopy);
		
		if (file.exists()) {

			FileUtils.copyFile(new File(pathfromCopy), new File(pathtoCopy1));
			Thread.sleep(5000);
			// requesting JVM for running Garbage Collector
	        System.gc();
			
		}else{
			throw new Exception("File is not present at this location ---"+pathfromCopy);
		}
		
		File folder = new File(uploadFolderName1);
		File[] listOfFiles = folder.listFiles();
		//int filecount = listOfFiles.length;
		
		int filecount=0;
		if (listOfFiles!=null) {
			 filecount = listOfFiles.length;
		}
		
		
		String txt;
		if (filecount > 0) {
			if (filecount==1) {
				fnpMymsg("File is copied successfully.");
			} else {
				txt="File is NOT copied successfully.";
				fnpMymsg(txt);
				throw new Exception(txt);
			}
		} else {
			txt="File is not copied to this location --"+uploadFolderName1;
			fnpMymsg(txt);
			throw new Exception(txt);
		}
		
		String pathtoCopy2 = uploadFolderName2+"\\"+iAgOption2FileName;


		
		
	//	String dateTimeStmp=fnTimestampDateWithTime();
		String dateTimeStmp=fnTimestampDateWithTime_2DigitYear();
		
		
		
		dateTimeStmp=dateTimeStmp.replaceAll(":", "-");
		System.out.println("CurrentTimeStamp---"+dateTimeStmp);
		
		Xls_Reader option2Excel=new Xls_Reader(pathtoCopy1);
		
		
		option2Excel.setCellDataForiAgOption2Sheet(pathtoCopy2,dateTimeStmp);
		
		

		
		fnpDisplayingMessageInFrame("Now script has  inserted the values into the excel.", 20);	 
	}
	
	
	
	
	




public static void fnpFetchingAndInsertingValuesIn_Certification_AutoTest(String CMI,String ggn,String site,long auditNo,String todayDate) throws Throwable{

	
	
	fnpDisplayingMessageInFrame("Now script is going to fetch the values and inserting into the excel.", 20);
	
	
	String uploadFolderName1 = System.getProperty("user.dir") + "\\docs\\iAg_Certification_Autotest\\copyThroughScript";
	File folder1 = new File(uploadFolderName1);
	cleanDirectory(folder1);
	
	
	String uploadFolderName2 = System.getProperty("user.dir") + "\\docs\\iAg_Certification_Autotest\\ModifiedThoroughScript";
	File folder2 = new File(uploadFolderName2);
	cleanDirectory(folder2);
	
	String fileName = null;
	switch (env) {

	

	case "Test":
		fileName = iAg_Certification_Autotest_FileName_TEST;
		break;
	
	case "Staging":
		fileName = iAg_Certification_Autotest_FileName_STAGE;
		break;
		
	case "Stage":
		fileName = iAg_Certification_Autotest_FileName_STAGE;
		break;
		

	}
	
	String pathfromCopy=System.getProperty("user.dir")+"\\docs\\iAg_Certification_Autotest\\original\\"+fileName;
	String pathtoCopy1 = uploadFolderName1+"\\"+fileName;
	
	File file = new File(pathfromCopy);
	
	if (file.exists()) {

		FileUtils.copyFile(new File(pathfromCopy), new File(pathtoCopy1));
		Thread.sleep(5000);
		
	}else{
		throw new Exception("File is not present at this location ---"+pathfromCopy);
	}
	
	File folder = new File(uploadFolderName1);
	File[] listOfFiles = folder.listFiles();
	//int filecount = listOfFiles.length;
	int filecount =0;
	if (listOfFiles!=null) {
		 filecount = listOfFiles.length;
	}
	
	String txt;
	if (filecount > 0) {
		if (filecount==1) {
			fnpMymsg("File is copied successfully.");
		} else {
			txt="File is NOT copied successfully.";
			fnpMymsg(txt);
			throw new Exception(txt);
		}
	} else {
		txt="File is not copied to this location --"+uploadFolderName1;
		fnpMymsg(txt);
		throw new Exception(txt);
	}
	
	String pathtoCopy2 = uploadFolderName2+"\\"+fileName;


	
	
	String dateTimeStmp=fnTimestampDateWithTime();
	dateTimeStmp=dateTimeStmp.replaceAll(":", "-");
	System.out.println("CurrentTimeStamp---"+dateTimeStmp);
	
	Xls_Reader option2Excel=new Xls_Reader(pathtoCopy1);
	
/*	
	String cmi="CMi C0466403";
	String GGN="4059883267907";
	//long GGN=405988319;
	String Site="C0466405";
	//String Audit_String="1949387";
	//String Audit_String="1950060";
	 long Audit=2278189;
	 String Audit_Date="";
	// Audit_Date = "04-06-2018"; //not worked
	// Audit_Date = "04-6-2018"; //not worked
	// Audit_Date = "4-6-2018"; //working fine
	 Audit_Date = "19-2-2019"; 
	 
	 
	 */
	 
	 
	 
	option2Excel.setCellDataForiAg_Certification_Autotest(pathtoCopy2,dateTimeStmp,  CMI, ggn, site, auditNo, todayDate);
	
	

	
	fnpDisplayingMessageInFrame("Now script has  inserted the values into the excel.", 20);	 
}



	public void fnpCommonOption2_code_from_Import_Till_CorrespondingWO_created() throws Throwable{

		
		fnpLaunchBrowserAndLogin();
		
		

		// *******Click Create Memebrship from top menu  ****/
		// Step =>Hover over the Menu option from the Home Page and select Import Form
		fnpCommonClickTopMainMenu("Menu", "FirstRefrenceLinkJustBelowToMenu_foriAG", "ImportFormLink", "FormImportLabel");
		
		
		//System.out.println("Start time is--"+fnTimestampDateWithTime());
		TestSuiteBase_Agriculture_suite.fnpFetchingAndInsertingValuesInUploadingExcelForOption2();
		//System.out.println("End time is--"+fnTimestampDateWithTime());
		
		driver.findElement(By.xpath(OR.getProperty("BrowseBtnOnFormImportScreen"))).sendKeys(System.getProperty("user.dir") + "\\docs\\iAg_option2\\ModifiedThoroughScript\\"+iAgOption2FileName);
		
		
		
		
		
		
		
		
		Thread.sleep(10000);
		
		
		int whileCounter=0;
		while(fnpCheckElementDisplayedImmediately("CancelUploadBtn")){
			whileCounter++;
			fnpMymsg("It is still uploading --"+whileCounter);
			Thread.sleep(1000);
			
			if (whileCounter > Integer.parseInt(CONFIG.getProperty("loadingIcon_Max_waitTime"))) {
				throw new Exception("File is not uploaded successfully as it still showing cancel upload button " +
						"after --"+Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))+" seconds.");
			}
			
		}
		
		Thread.sleep(5000);
		fnpClick_OR("ImportBtn");		
		import_USTime=getCurrent_US_Time();
		System.out.println("US Time just after importing Excel:"+import_USTime);
		fnpClick_OR("ViewResultsBtn");
		

		String memberMatchingLabelValue=fnpGetText_OR_EvenMultipleObjectsHavingSameXpath("MemberMatchingScreenLabel");
		String[] labelTextArray = memberMatchingLabelValue.split(":");
		String imNumber;
		if (labelTextArray.length!=2) {
			throw new Exception("Either member matching IM number is not present or its dispaly format has been changed.");
			
		} else {
			imNumber=labelTextArray[1];
		}
					
		fnpMymsg("Member matching no. is displayed and its value is  --"+imNumber);
		
		//TimeZone timeZone = TimeZone.getTimeZone("US/Eastern");
		String timezone_string="US/Eastern";
		String timeFormat = "dd-MMM-yyyy  hh:mm a";
		System.out.println("System Date in EST: "+formatDateToString(new Date(), timeFormat, timezone_string ));
		
		
		
		//int matchTypeColNo=fnpFindColumnIndex2("ProducerGroupTable_HeaderRow", "Match Type");
		int matchTypeColNo=fnpFindColumnIndex("ProducerGroupTable_HeaderRow", "Match Type");
		String matchTypeValue=fnpFetchFromTable("ProducerGroupTable_dataRow", 1, matchTypeColNo).trim();
		if (matchTypeValue.equalsIgnoreCase("No Match")) {
			fnpMymsg("The Producer Group record is presented as a No Match");
		} else {
			throw new Exception("The Producer Group record is NOT presented as a No Match, actual value is ---"+matchTypeValue);
		}
		
		
		
		
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
		
		fnpClick_OR("SubmitSelectedRowsBtn");
		
		if ((classNameText.contains("Alerts_testing_App_Form_Option2"))) {
			fnpClick_OR("NoConfirmationBtnInSubmissionConfirmationPopup");
		}else{
			
			
/*			
			fnpClick_OR("YesConfirmationBtnInSubmissionConfirmationPopup");
			fnpWaitForVisibleHavingMultipleElements("TopMessage3");
			String SuccessfulMsg = fnpGetText_OR("TopMessage3");
			fnpMymsg("Top Message after clicking Yes Confirmation Btn In Submission Confirmation Popup ----" + SuccessfulMsg);

			Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"),
					"Top message does not contain 'success' word, so might be submission is not successfull.");
			
			
			*/
			
			
		/****************************************************************************************************/	
			
			//fnpWorkAroundSubmittingSelectedRows();	
			fnpWorkAroundSubmittingSelectedRows_ignoringUnknownErrorIfAny();
			fnpCheckError("Error is thrown by applicataion after clicking on Yes button in confirmation dialog for submitting the selected rows.");
			
	/****************************************************************************************************/	
			
	
			
			
			
		}

		
		
	}

	
	public Xls_Reader fnpCommonCode_for_matching_VAT_No(Xls_Reader option2Excel) throws Throwable{
		
		/**********Matching VAT NO************************************/
		fnpClick_OR("iAg_InfoTab");
		String vatNo = fnpGetText_OR("VatNo");
		

		String currentWorkingSheet="Producer Group";

		int vatNoRowIndex=10;
		int vatNoColIndex=9;
		String vatNo_in_excel=option2Excel.getCellData_withoutCheckingAnyCellType(currentWorkingSheet,vatNoColIndex,vatNoRowIndex);
		if (vatNo_in_excel.equalsIgnoreCase(vatNo)) {
			fnpMymsg("VAT No. is matched.");
		} else {
			msg="VAT No. is NOT matched. Value in excel is this '"+vatNo_in_excel+"' but in application it is '"+vatNo+"'.";
			fnpMymsg(msg);
			throw new Exception(msg);
		}

		return option2Excel;
		/*********************************************/
	}
	
	
	public void fnpVerify_Producer_sheet_some_data(Xls_Reader option2Excel) throws Throwable{
		
		/**************Producer Data looks like*****************************************************************************/
		
		fnpClick_OR("iAg_ProducersTabLink");
		int producersRow = fnpCountRowsInTable("ProducersTab_Table_data");
		
		 String currentWorkingSheet = "Producers";

		int producerNameStartRowIndex=13;
		int producerNameStartColIndex=4;
		String producerNameColValue=option2Excel.getCellData_withoutCheckingAnyCellType(currentWorkingSheet,producerNameStartColIndex,producerNameStartRowIndex);
		if (producerNameColValue.equalsIgnoreCase("*Producer Name")) {
			fnpMymsg("Producer Name column is present.");
		} else {
			throw new Exception("Producer Name column is not present at row index '"+producerNameStartRowIndex+"' and col index '"+producerNameStartColIndex+"' . It means excel has been changed or new or corrupted.");
		}

		
		
		String[] namesPresent=null;
		int expectedTotalRows = 0;
		
		
/*		
		if (classNameText.equalsIgnoreCase(GRASP_scheme_TestCase_Name)) {
			//String[] namesPresent=new String 
			String[] namesPresent1  = {"P1", "P8", "P9"};
			namesPresent=namesPresent1;
			 expectedTotalRows=3;
			if (producersRow==expectedTotalRows) {
				fnpMymsg("Producer rows are "+expectedTotalRows);
			} else {
				msg=" Producer tab rows count are not equal to "+expectedTotalRows+" as expected . Currently total rows are --"+producersRow;
				fnpMymsg(msg);
				throw new Exception(msg);
			}
		}else{
			if (classNameText.equalsIgnoreCase("Create_Membership_With_Option2")) {
				String[] namesPresent1  = {"P1", "P2", "P3","P4", "P5"};
				namesPresent=namesPresent1;
				expectedTotalRows=5;
				if (producersRow==expectedTotalRows) {
					fnpMymsg("Producer rows are "+expectedTotalRows);
				} else {
					msg=" Producer tab rows count are not equal to "+expectedTotalRows+" as expected . Currently total rows are --"+producersRow;
					fnpMymsg(msg);
					throw new Exception(msg);
				}
			}
		}
		
		*/
		

		switch (classNameText) {
		case GRASP_scheme_TestCase_Name:
			//String[] namesPresent1  = {"P1", "P8", "P9"};// Sudhir change in excel
			String[] namesPresent1  = {"P1", "P2", "P3"};
			namesPresent=namesPresent1;
			expectedTotalRows=3;
			break;
		

		case "Create_Membership_With_Option2":
			String[] namesPresent2  = {"P1", "P2", "P3","P4", "P5"};
			namesPresent=namesPresent2;
			expectedTotalRows=5;
			break;

		case AH2_scheme_TestCase_Name:
			String[] namesPresent3  = {"P1", "P2", "P3", "P4", "P5"};
			namesPresent=namesPresent3;
			expectedTotalRows=5;
			break;
			
		case Alerts_testing_App_Form_Option2_TestCase_Name:
			String[] namesPresent4  = {"P1", "P2", "P3", "P4", "P5"};
			namesPresent=namesPresent4;
			expectedTotalRows=5;
			break;

		}
	
		if (producersRow==expectedTotalRows) {
			fnpMymsg("Producer rows are "+expectedTotalRows);
		} else {
			msg=" Producer tab rows count are not equal to "+expectedTotalRows+" as expected . Currently total rows are --"+producersRow;
			fnpMymsg(msg);
			throw new Exception(msg);
		}

		
		
		
		
		
		
		
		
		
		
		
		String firstProducerName="";
		int nameColIndex = fnpFindColumnIndex("ProducersTab_Table_HeaderRow", "Name");
		String name ;
		String temp=null;
		boolean found=false;
		for(int i=0; i <producersRow ;i++ ){
			found=false;
			 name = fnpFetchFromTable("ProducersTab_Table_data", (i+1), nameColIndex);
			 fnpMymsg("Producer at row no. '"+(i+1)+"' is--'"+name+"'.");
				for(int j=(producerNameStartRowIndex+1); j <= option2Excel.getRowCount(currentWorkingSheet) ;j++ ){							
					 temp = option2Excel.getCellData_withoutCheckingAnyCellType(currentWorkingSheet, producerNameStartColIndex, j).trim();
					if(temp.equalsIgnoreCase(name)){
						found=true;
						fnpMymsg("This producer '"+name+"' is present in excel at row no. --'"+j+"'.");
						
						if (  (classNameText.equalsIgnoreCase(GRASP_scheme_TestCase_Name))  |  (classNameText.equalsIgnoreCase(AH2_scheme_TestCase_Name)) 
								|  (classNameText.equalsIgnoreCase(Alerts_testing_App_Form_Option2_TestCase_Name))){
							if(name.contains(namesPresent[i])){
								msg=" As expected this row '"+(i+1)+"' contains name '"+namesPresent[i]+"'.";
								fnpMymsg(msg);
							}else{
								msg=" As expected this row '"+(i+1)+"' does not contain name '"+namesPresent[i]+"'. Actual is this --'"+name+"'.";
								fnpMymsg(msg);
								throw new Exception(msg);
							}
						}

						
						
						break;
					}
				}
				
				if (!(found)) {
					msg="This producer '"+name+"' is NOT present in any row in excel sheet '"+currentWorkingSheet+"'.";
					fnpMymsg(msg);
					throw new Exception(msg);
					
				} 
				if (i==0) {//This first producer name will be use for comparison while comparing first site of apple in Product & Coverage tab
					firstProducerName=temp;
				}

		}
						
		/************************************************************************************************/
		
		
	}
	
	
	
	
	
	
	
	public void fnpVerify_Sites_sheet_some_data(Xls_Reader option2Excel) throws Throwable{
		
		
		/**************Sites data looks like*****************************************************************************/
		//here we are just count no. of rows and count should be 7
		
		fnpClick_OR("iAg_SitesTabLink");
		int sitesRow = fnpCountRowsInTable("SitesTab_Table_data");
		
		fnpMymsg("Total Sites rows in iPulse for current work order are ---"+sitesRow);
		
/*		int expectedSitesRows=10;
		if (sitesRow!=expectedSitesRows){
			throw new Exception("Total count of rows in Sites are '"+sitesRow+"' but they must be exact "+expectedSitesRows+" rows .");
		}else{
			fnpMymsg("Total count of rows in Sites are '"+sitesRow+"'.");
		}
		*/
		
		int expectedSitesRows = 0;
		
/*		
		if (classNameText.equalsIgnoreCase(GRASP_scheme_TestCase_Name)) {
			expectedSitesRows=8;
		}else{
			if (classNameText.equalsIgnoreCase("Create_Membership_With_Option2")) {
				expectedSitesRows=10;
			}
		}
		*/
		
		switch (classNameText) {
		case GRASP_scheme_TestCase_Name:
			expectedSitesRows=8;
			break;
		

		case "Create_Membership_With_Option2":
			expectedSitesRows=10;
			break;

		case AH2_scheme_TestCase_Name:
			expectedSitesRows=10;
			break;
			
		case Alerts_testing_App_Form_Option2_TestCase_Name:
			expectedSitesRows=10;
			break;

		}

		
		
		
		
		
		
		if (sitesRow!=expectedSitesRows){
			throw new Exception("Total count of rows in Sites are '"+sitesRow+"' but they must be exact "+expectedSitesRows+" rows .");
		}else{
			fnpMymsg("Total count of rows in Sites are '"+sitesRow+"'.");
		}
		
		
		
		
		
/*		
		*//*************Top 5 sites present in excel should be present at sites tab of application*********************//*
		String sitesName="";
		int nameColIndex_sitesTab = fnpFindColumnIndex("SitesTab_Table_HeaderRow", "Name");
		String temp = null;
		boolean found = false;
		int sitesNameStartRowIndex=14;
		int sitesNameStartColIndex=6;
		String currentWorkingSheet = "Sites";
		for(int j=(sitesNameStartRowIndex); j < (sitesNameStartRowIndex+5) ;j++ ){	
			found=false;
			sitesName = option2Excel.getCellData_withoutCheckingAnyCellType(currentWorkingSheet, sitesNameStartColIndex, j).trim();
			 
			 fnpMymsg("Sites (in excel) at row no. '"+(j)+"' is--'"+sitesName+"'.");
				for(int m=1; m <= sitesRow ;m++ ){							
					temp = fnpFetchFromTable("SitesTab_Table_data", (m), nameColIndex_sitesTab);
					if(sitesName.equalsIgnoreCase(temp)){
						found=true;
						fnpMymsg("This Site '"+sitesName+"' is present in excel at row no. --'"+m+"'.");
						break;
					}
				}
				
				if (!(found)) {
					msg="This Site '"+sitesName+"' is NOT present in any row in table of Sites tab.";
					fnpMymsg(msg);
					throw new Exception(msg);
					
				} 

		}
		*//**********************************************************************************************************************//*
		*/
		
		
		
		/************Matching Sites**********************************************************/
		//String packHouseArray[]= new String []{ "PHU 1", "PHU 2", "PHU 3","PHU 6"};
		
		String sitesArray[] = null;
		
		
/*		
		if (classNameText.equalsIgnoreCase(GRASP_scheme_TestCase_Name)) {
			sitesArray=new String []{ "P1 auto", "P7 auto", "P8 auto"};
		}else{
			if (classNameText.equalsIgnoreCase("Create_Membership_With_Option2")) {
				sitesArray=new String []{ "P1 auto", "P2 auto", "P3 auto","P4 auto","P5 auto"};
			}
		}
		
		*/
		
		switch (classNameText) {
		case GRASP_scheme_TestCase_Name:
			//sitesArray=new String []{ "P1 auto", "P7 auto", "P8 auto"};
			sitesArray=new String []{ "P1 auto", "P2 auto", "P3 auto"};// Sudhir change in excel
			break;
		

		case "Create_Membership_With_Option2":
			sitesArray=new String []{ "P1 auto", "P2 auto", "P3 auto","P4 auto","P5 auto"};
			break;

		case AH2_scheme_TestCase_Name:
			sitesArray=new String []{ "P1 auto", "P2 auto", "P3 auto","P4 auto","P5 auto"};
			break;
			
		case Alerts_testing_App_Form_Option2_TestCase_Name:
			sitesArray=new String []{ "P1 auto", "P2 auto", "P3 auto","P4 auto","P5 auto"};
			break;

		}
		
		
		
		
		
		String siteName;
		boolean found;
		String temp;
		int nameColIndex_sitesTab = fnpFindColumnIndex("SitesTab_Table_HeaderRow", "Name");
		for(int j=0; j <sitesArray.length ;j++ ){	
			found=false;
			siteName = sitesArray[j];

			for(int m=1; m <= sitesRow ;m++ ){							
				temp = fnpFetchFromTable("SitesTab_Table_data", (m), nameColIndex_sitesTab);
				if(temp.contains(siteName)){
					found=true;
					fnpMymsg("This Site '"+siteName+"' is present at sites tab in application at row no. --'"+m+"'.");
					break;
				}
			}
				
			if (!(found)) {
				msg="This Site '"+siteName+"' is NOT present in any row in table of Sites tab.";
				fnpMymsg(msg);
				throw new Exception(msg);
				
			} 

		}
		
		/****************************************************************************/
		
		
		
		
		/************Matching Pack Houses**********************************************************/
		//String packHouseArray[]= new String []{ "PHU 1", "PHU 2", "PHU 3","PHU 6"};
		
		String packHouseArray[] = null;
		
		
/*		if (classNameText.equalsIgnoreCase(GRASP_scheme_TestCase_Name)) {
			packHouseArray=new String []{ "PHU 1", "PHU 2", "PHU 9","PHU 10"};
		}else{
			if (classNameText.equalsIgnoreCase("Create_Membership_With_Option2")) {
				packHouseArray=new String []{ "PHU 1", "PHU 2", "PHU 3","PHU 6"};
			}
		}
		*/
		
		switch (classNameText) {
		case GRASP_scheme_TestCase_Name:
			//packHouseArray=new String []{ "PHU 1", "PHU 2", "PHU 9","PHU 10"};// Sudhir change in excel
			packHouseArray=new String []{ "PHU 1", "PHU 2", "PHU 3","PHU 4"};
			break;
		

		case "Create_Membership_With_Option2":
			packHouseArray=new String []{ "PHU 1", "PHU 2", "PHU 3","PHU 6"};
			break;

		case AH2_scheme_TestCase_Name:
			packHouseArray=new String []{ "PHU 1", "PHU 2", "PHU 3","PHU 6"};
			break;

			
		case Alerts_testing_App_Form_Option2_TestCase_Name:
			packHouseArray=new String []{ "PHU 1", "PHU 2", "PHU 3","PHU 6"};
			break;
		}
		
		
		
		
		String packHouseName;
		for(int j=0; j <packHouseArray.length ;j++ ){	
			found=false;
			packHouseName = packHouseArray[j];
			 
			// fnpMymsg("PackHouse (in excel) at row no. '"+(j)+"' is--'"+sitesName+"'.");
				for(int m=1; m <= sitesRow ;m++ ){							
					temp = fnpFetchFromTable("SitesTab_Table_data", (m), nameColIndex_sitesTab);
					if(packHouseName.equalsIgnoreCase(temp)){
						found=true;
						fnpMymsg("This PackHouse '"+packHouseName+"' is present at sites tab in application at row no. --'"+m+"'.");
						break;
					}
				}
				
				if (!(found)) {
					msg="This PackHouse '"+packHouseName+"' is NOT present in any row in table of Sites tab.";
					fnpMymsg(msg);
					throw new Exception(msg);
					
				} 

		}
		
		/****************************************************************************/
		
		
		
		
		
		
		
		
		
		
		
		
		int unitTypeColIndex_sitesTab = fnpFindColumnIndex("SitesTab_Table_HeaderRow", "Unit Type");
		
		//int rowNoAtWhichQMSPresent=10;		
		
		int rowNoAtWhichQMSPresent = expectedSitesRows;
		
		String unitTypeAt10thRow = fnpFetchFromTable("SitesTab_Table_data", rowNoAtWhichQMSPresent, unitTypeColIndex_sitesTab);
		if(unitTypeAt10thRow.contains("QMS")){
			fnpMymsg("This QMS '"+unitTypeAt10thRow+"' is present in last row i.e. "+rowNoAtWhichQMSPresent+" at sites tab in application.");
		}else{
			msg="This QMS '"+unitTypeAt10thRow+"' is NOT present in last row i.e. "+rowNoAtWhichQMSPresent+" at sites tab in application. Last row data in Unit Type column is --'"+unitTypeAt10thRow+"'.";
			fnpMymsg(msg);
			throw new Exception(msg);
		}
		/************************************************************************************************/
		
		
	}
	
	
	
	
	public void fnpVerify_Contacts_tab_some_data(Xls_Reader option2Excel) throws Throwable{
		
		/**************Contacts data looks like*****************************************************************************/
		//here we are just count no. of rows and count should be 1
		
		fnpClick_OR("iAg_ContactsTabLink");
		int contactsRow = fnpCountRowsInTable("ContactsTab_Table_data");
		
		fnpMymsg("Total Contacts rows in iPulse for current work order are ---"+contactsRow);
		if (contactsRow!=1){
			throw new Exception("Total count of rows in Contacts are '"+contactsRow+"' but they must be exact 1 rows .");
		}else{
			fnpMymsg("Total count of rows in Contacts are '"+contactsRow+"'.");
		}
										
		String currentWorkingSheet = "Producer Group";
		int managerFirstNameStartRowIndex=14;
		int managerFirstNameStartColIndex=9;
		 String managertmpExcelValue="";
		 String managertmpApplicationValue="";
		int firstNameColNo=fnpFindColumnIndex("ContactsTab_Table_HeaderRow", "First Name");
		managertmpExcelValue=option2Excel.getCellData_withoutCheckingAnyCellType(currentWorkingSheet, managerFirstNameStartColIndex, (managerFirstNameStartRowIndex)).trim();
		managertmpApplicationValue=fnpFetchFromTable("ContactsTab_Table_data", 1, firstNameColNo);
		if(managertmpExcelValue.equalsIgnoreCase(managertmpApplicationValue)){
			fnpMymsg("Manager First Name is matched with excel i.e. in excel it is --'"+managertmpExcelValue+"' and in application it is --'"+managertmpApplicationValue+"'.");
		}else{
			fnpMymsg("Manager First Name is NOT matched with excel. Value in excel is this '"+managertmpExcelValue+"' but in application it is '"+managertmpApplicationValue+"'.");
			throw new Exception("Manager First Name is NOT matched with excel. Value in excel is this '"+managertmpExcelValue+"' but in application it is '"+managertmpApplicationValue+"'.");
		}

		
		int mobileStartRowIndex=19;
		int mobileStartColIndex=9;
		String mobileExcelValue="";
		 String mobileApplicationValue="";
		int mobileColNo=fnpFindColumnIndex("ContactsTab_Table_HeaderRow", "Cell/Mobile");
		mobileExcelValue=option2Excel.getCellData_withoutCheckingAnyCellType(currentWorkingSheet, mobileStartColIndex, (mobileStartRowIndex)).trim();
		mobileApplicationValue=fnpFetchFromTable("ContactsTab_Table_data", 1, mobileColNo);
		if(mobileExcelValue.equalsIgnoreCase(mobileApplicationValue)){
			fnpMymsg("Cell/Mobile is matched with excel i.e. in excel it is --'"+mobileExcelValue+"' and in application it is --'"+mobileApplicationValue+"'.");
		}else{
			msg="Cell/Mobile NOT matched with excel. Value in excel is this '"+mobileExcelValue+"' but in application it is '"+mobileApplicationValue+"'.";
			fnpMymsg(msg);
			throw new Exception(msg);
		}

		
		
		/************************************************************************************************/
		
		
	}
	
	
	
	public void fnpVerify_Products_and_Coverage_tab_data(Xls_Reader option2Excel) throws Throwable{
		
		fnpClick_OR("iAg_ProductsAndCoverageTabLink");
		int productCoverageRow = fnpCountRowsInTable("ProdcutsAndCoverageTab_Table_data");
		
		int expectedProductCoverageTabRow=0;
		
/*		if (classNameText.equalsIgnoreCase(Create_Membership_With_Option2_TestCase_Name)) {
			expectedProductCoverageTabRow=4;
		}else{
			if (classNameText.equalsIgnoreCase(GRASP_scheme_TestCase_Name)) {
				expectedProductCoverageTabRow=1;
			}
		}
		*/
		
		switch (classNameText) {
		
		case "Create_Membership_With_Option2":
			expectedProductCoverageTabRow=4;
			break;
		
		case GRASP_scheme_TestCase_Name:
			expectedProductCoverageTabRow=1;
			break;
		



		case AH2_scheme_TestCase_Name:
			expectedProductCoverageTabRow=4;
			break;
			
		case Alerts_testing_App_Form_Option2_TestCase_Name:
			expectedProductCoverageTabRow=4;
			break;

		}
		
		
		
		fnpMymsg("Total Products & Coverage tab rows in iPulse for current work order are ---"+productCoverageRow);
		if (productCoverageRow!=expectedProductCoverageTabRow){
			throw new Exception("Total count of rows in Products & Coverage tab are '"+productCoverageRow+"'  but they must be exact "+expectedProductCoverageTabRow+" rows .");
		}else{
			fnpMymsg("Total count of rows in Products & Coverage tab are '"+productCoverageRow+"'.");
		}
		
		

		
		
		
		
		
		fnpClick_OR("FirstProductExpandTriangleIcon");
		int firstInnerProductRowsAfterExpand = fnpCountRowsInTable("ProdcutsAndCoverageTab_FirstInnerProductTable_data");
		
		fnpMymsg("Total Products & Coverage tab's first product inner rows in iPulse for current work order are ---"+firstInnerProductRowsAfterExpand);
		
		int expectedfirstInnerProductRowsAfterExpand=0;
		
/*		if (classNameText.equalsIgnoreCase(Create_Membership_With_Option2_TestCase_Name)) {
			expectedfirstInnerProductRowsAfterExpand=2;
		}else{
			if (classNameText.equalsIgnoreCase(GRASP_scheme_TestCase_Name)) {
				expectedfirstInnerProductRowsAfterExpand=3;
			}
		}
		*/
		
		switch (classNameText) {
		case "Create_Membership_With_Option2":
			expectedfirstInnerProductRowsAfterExpand=2;
			break;
		
		case GRASP_scheme_TestCase_Name:		
			expectedfirstInnerProductRowsAfterExpand=3;
			break;

		case AH2_scheme_TestCase_Name:
			expectedfirstInnerProductRowsAfterExpand=2;
			break;
			
		case Alerts_testing_App_Form_Option2_TestCase_Name:
			expectedfirstInnerProductRowsAfterExpand=2;
			break;

		}
		
		
		
		
		if (firstInnerProductRowsAfterExpand!=expectedfirstInnerProductRowsAfterExpand){
			throw new Exception("Total count of rows in Products & Coverage's first product inner  table are '"+firstInnerProductRowsAfterExpand+"' but they must be exact "+expectedfirstInnerProductRowsAfterExpand+" rows .");
		}else{
			fnpMymsg("Total count of rows in Products & Coverage's first product inner  table  are '"+firstInnerProductRowsAfterExpand+"'.");
		}
		
		
		
/*		
		int siteColNo=fnpFindColumnIndex("ProdcutsAndCoverageTab_FirstInnerProductTable_HeaderRow", "Site");
		String actualFirstSiteValue=fnpFetchFromTable("ProdcutsAndCoverageTab_FirstInnerProductTable_data", 1, siteColNo);
		if(actualFirstSiteValue.equalsIgnoreCase(firstProducerName)){
			fnpMymsg("Actual first Site value is matched with excel first producer name i.e. in application it is --'"+actualFirstSiteValue+"' and in excel it is --'"+firstProducerName+"'.");
		}else{
			textMessage = "Actual first Site value is NOT matched with excel first producer name i.e. in application it is --'"+actualFirstSiteValue+"' and in excel it is --'"+firstProducerName+"'.";
			fnpMymsg(textMessage);
			fnpDisplayingMessageInFrame(textMessage, 10);
			throw new Exception(textMessage);
			
		}

		
		*/
		
		
		String currentWorkingSheet = "Producers";

		int producerNameStartRowIndex=13;
		int producerNameStartColIndex=4;
		
		
		
		String firstProducerName="";
		int siteColNo=fnpFindColumnIndex("ProdcutsAndCoverageTab_FirstInnerProductTable_HeaderRow", "Site");
		int productCoverageInnerRow = fnpCountRowsInTable("ProdcutsAndCoverageTab_FirstInnerProductTable_data");
		String siteName ;
		String temp=null;
		boolean found=false;
		
		
		String[] namesPresent=null;
/*		if (classNameText.equalsIgnoreCase(GRASP_scheme_TestCase_Name)) {
			//String[] namesPresent=new String 
			String[] namesPresent1  = {"P1 auto", "P7 auto", "P8 auto"};
			namesPresent=namesPresent1;
		}else{
			if (classNameText.equalsIgnoreCase(Create_Membership_With_Option2_TestCase_Name)) {
				String[] namesPresent1  = {"P1 auto", "P2 auto"};
				namesPresent=namesPresent1;
			}
		}
		*/
		
		switch (classNameText) {

		

		case "Create_Membership_With_Option2":
			String[] namesPresent2  = {"P1 auto", "P2 auto"};
			namesPresent=namesPresent2;
			break;

		case GRASP_scheme_TestCase_Name:
		//	String[] namesPresent1  = {"P1 auto", "P7 auto", "P8 auto"};// Sudhir change in excel
			String[] namesPresent1  = {"P1 auto", "P2 auto", "P3 auto"};
			namesPresent=namesPresent1;
			break;
			
		case AH2_scheme_TestCase_Name:
			String[] namesPresent3  = {"P1 auto", "P2 auto"};
			namesPresent=namesPresent3;
			break;
			
		case Alerts_testing_App_Form_Option2_TestCase_Name:
			String[] namesPresent4  = {"P1 auto", "P2 auto"};
			namesPresent=namesPresent4;
			break;

		}
		
		
		
		
		
		for(int i=0; i <productCoverageInnerRow ;i++ ){
			found=false;
			siteName = fnpFetchFromTable("ProdcutsAndCoverageTab_FirstInnerProductTable_data", (i+1), siteColNo);
			 fnpMymsg("Site at row no. '"+(i+1)+"' is--'"+siteName+"'.");
				for(int j=(producerNameStartRowIndex+1); j <= option2Excel.getRowCount(currentWorkingSheet) ;j++ ){							
					 temp = option2Excel.getCellData_withoutCheckingAnyCellType(currentWorkingSheet, producerNameStartColIndex, j).trim();
					if(temp.equalsIgnoreCase(siteName)){
						found=true;
						fnpMymsg("This Site '"+siteName+"' is present in excel at row no. --'"+j+"'.");
						
/*						if (classNameText.equalsIgnoreCase(GRASP_scheme_TestCase_Name)) {
							if(siteName.contains(namesPresent[i])){
								msg=" As expected this row '"+(i+1)+"' contains name '"+namesPresent[i]+"'.";
								fnpMymsg(msg);
							}else{
								msg=" As expected this row '"+(i+1)+"' does not contain name '"+namesPresent[i]+"'. Actual is this --'"+siteName+"'.";
								fnpMymsg(msg);
								throw new Exception(msg);
							}
						}*/
						
						
						if(siteName.contains(namesPresent[i])){
							msg=" As expected this row '"+(i+1)+"' contains name '"+namesPresent[i]+"'.";
							fnpMymsg(msg);
						}else{
							msg=" As expected this row '"+(i+1)+"' does not contain name '"+namesPresent[i]+"'. Actual is this --'"+siteName+"'.";
							fnpMymsg(msg);
							throw new Exception(msg);
						}
						

						
						
						break;
					}
				}
				
				if (!(found)) {
					msg="This Site '"+siteName+"' is NOT present in any row in excel sheet '"+currentWorkingSheet+"'.";
					fnpMymsg(msg);
					throw new Exception(msg);
					
				} 

		}
		
		
		
		
		//Verify total no. of employes should be 60 in GRASP Scheme		
		if (classNameText.equalsIgnoreCase(GRASP_scheme_TestCase_Name)) {
			int expectedTotalNoOfEmployeeValue=60;
			int totalNoOfEmployeesColNo=fnpFindColumnIndex("ProdcutsAndCoverageTab_Table_HeaderRow", "Total Number of Employees");
			String actualTotalNoOfEmployeeValue_string=fnpFetchFromTable("ProdcutsAndCoverageTab_Table_data", 1, totalNoOfEmployeesColNo).trim();
			if (actualTotalNoOfEmployeeValue_string.equalsIgnoreCase("")) {
				msg="Total no. of employees count are missing in the table.";
				fnpMymsg(msg);
				throw new Exception(msg);
				
			} else {
				int actualTotalNoOfEmployeeValue=Integer.parseInt(actualTotalNoOfEmployeeValue_string);
				if (actualTotalNoOfEmployeeValue!=expectedTotalNoOfEmployeeValue) {
					msg="Total no. of employees count are not equal in the table as actual is --'"+actualTotalNoOfEmployeeValue+"' but expected is --'"+expectedTotalNoOfEmployeeValue+"'.";
					fnpMymsg(msg);
					throw new Exception(msg);
				} else {
					msg="Total no. of employees count are equal  to "+expectedTotalNoOfEmployeeValue+" in the table.";
					fnpMymsg(msg);

				}
			}
			
		}
		
		
		
		
		
		
		
		
		
		
		fnpClick_OR("FirstViewSymbolforFirstProduct");
		if (fnpCheckElementDisplayed2("ViewProductLabelofDialog", 15)) {
			fnpMymsg("View product dialog box is opened successfully.");
		} else {
			fnpMymsg("Either View product dialog box is NOT opened successfully  or its label id is changed.");
		}
		
		
		
		
		
		String editProductLabel=fnpGetText_OR("ViewProductLabelofDialog");
		if (editProductLabel.equalsIgnoreCase("View Product")) {
			fnpMymsg("View product dialog box label's value is 'View Product'.");
		} else {
			fnpMymsg("View product dialog box label's value is NOT 'View Product'.");
		}
		
		/************************************************************************************************/
		
		
		
		
		fnpClick_OR("CloseSignOfViewProductLabelofDialog");
	}
	
	
	
	public void fnpVerify_Services_tab() throws Throwable{
		fnpClick_OR("iAg_ServicesTabLink");
		
		int expectedRows;
		
		int servicesRow = fnpCountRowsInTable("iAg_ServicesTab_Table_data");
		
		switch (classNameText) {


		case GRASP_scheme_TestCase_Name:
			expectedRows=1; //earlier for this scheme, 0 was there
			break;
			
		 default:
			 expectedRows=1;
			break;
			


		}
		
		
		
		
		fnpMymsg("Total Services tab rows in iPulse for current work order are ---"+servicesRow);
		if (servicesRow!=expectedRows){
			throw new Exception("Total count of rows in Services tab are '"+servicesRow+"'  but expected is '"+expectedRows+".");
		}else{
			fnpMymsg("Total count of rows in Services tab are '"+servicesRow+"' as expected.");
		}
		
	}
	
	
	
	public void fnpMatchScheme(String schemeName) throws Throwable{
		
	    String scheme_wo_link_xpath=OR.getProperty("WoLinkAtImportResultsScreen_part1")+schemeName+OR.getProperty("WoLinkAtImportResultsScreen_part2");
	    
		String woNo = fnpGetText_NOR(scheme_wo_link_xpath);
		fnpMymsg("Wo no. is ---"+woNo);
		fnpClick_NOR(scheme_wo_link_xpath);
		fnpWaitForVisible("iAg_SnapshotTabLink");

				
		
		String path=System.getProperty("user.dir") + "\\docs\\iAg_option2\\ModifiedThoroughScript\\"+iAgOption2FileName;
		Xls_Reader option2Excel=new Xls_Reader(path);
		
		
		
		/**********Matching VAT NO************************************/
		option2Excel=fnpCommonCode_for_matching_VAT_No( option2Excel) ;
		/*********************************************/
		
		
		fnpVerify_Producer_sheet_some_data( option2Excel) ;				
		
		fnpVerify_Sites_sheet_some_data( option2Excel) ;
		
		fnpVerify_Contacts_tab_some_data( option2Excel);
		
		fnpVerify_Products_and_Coverage_tab_data( option2Excel) ;
		
		fnpVerify_Services_tab();
	}

	
	public static void fnpVerificationInAlert_GlobalGapPendingImportRegistration(Hashtable table, String alertName, int rowNo, String columnName,String searchValue) throws Throwable {

		TestSuiteBase_HealthScience.fnpCommonAlertGeneratedVerification( table,  alertName,  columnName, searchValue);
		 
		 String updatedTableDataXpath=getTableData_fromAlertName( alertName);	
		 String updatedHHeaderXpath=getTableHeader_fromAlertName( alertName);	
		 int colIndex =fnpFindColumnIndex_NOR(updatedHHeaderXpath, columnName);
		 
		 
		 /***************Some verificaition****************/
		 int scheme_colIndex =fnpFindColumnIndex_NOR(updatedHHeaderXpath, "Scheme");
		 String schemeValue = fnpFetchFromTable_NOR(updatedTableDataXpath, rowNo, scheme_colIndex).trim();
		 String expectedScheme="GlobalGap";
		 Assert.assertEquals(schemeValue,expectedScheme , "Scheme is not matched. Expected is this'"+expectedScheme+"' but actual is this '"+schemeValue+"'.");
		 
		 
		 
		 
		 
		 int createDate_colIndex =fnpFindColumnIndex_NOR(updatedHHeaderXpath, "Create Date");
		 String createDate = fnpFetchFromTable_NOR(updatedTableDataXpath, rowNo, createDate_colIndex).trim();
		 if (createDate.length()<5) {
			 msg="Create Date is not present (missing) in this alert '"+alertName+"'.";
			 fnpMymsg(msg);
			 throw new Exception(msg);
			
		}
		// Assert.assertEquals(createDate,import_USTime, "Create Date is not matched. Expected is this'"+import_USTime+"' but actual is this '"+createDate+"'.");
		 
		String timeFormat = "dd-MMM-yyyy hh:mm a";	
		SimpleDateFormat sdfmt1 = new SimpleDateFormat(timeFormat);
		 
		java.util.Date actualDateInDateFormat = sdfmt1.parse(createDate);
		java.util.Date expectedDateInDateFormat = sdfmt1.parse(import_USTime);
		 
		String textMessage="";
/*		
		if (actualDateInDateFormat.compareTo(expectedDateInDateFormat) > 0) {
			textMessage = "Alert '"+alertName+"' 's Create Date '"+createDate+"' is after expected date '"+expectedDateInDateFormat+"', hence failed. ";				
			fnpMymsg(textMessage);
			fnpDisplayingMessageInFrame(textMessage, 5);
			throw new Exception(textMessage);
		} else if (actualDateInDateFormat.compareTo(expectedDateInDateFormat) < 0) {
			textMessage = "Alert '"+alertName+"' 's Create Date '"+createDate+"' is after expected date '"+expectedDateInDateFormat+"', hence failed. ";	
			fnpMymsg(textMessage);
			fnpDisplayingMessageInFrame(textMessage, 5);
			throw new Exception(textMessage);
		} else {
			textMessage = "Alert '"+alertName+"' 's Create Date '"+createDate+"' is equal to expected date  '"+expectedDateInDateFormat+"', hence passed. ";	
			fnpMymsg(textMessage);
			fnpDisplayingMessageInFrame(textMessage, 5);
		}

		*/ 
			
			
			
			Date d1 = null;
			Date d2 = null;
			
			d1 = sdfmt1.parse(createDate);
			d2 = sdfmt1.parse(import_USTime);
			
			//in milliseconds
			long diff = d1.getTime() - d2.getTime();
			long diffMinutes = diff / (60 * 1000) % 60;
			fnpMymsg("Difference in create time in alert is --"+diffMinutes);
			if (diffMinutes<2) {
				
				textMessage = "Alert '"+alertName+"' 's Create Date '"+createDate+"' is equal to expected date  '"+expectedDateInDateFormat+"', hence passed. ";					
				fnpMymsg(textMessage);
				fnpDisplayingMessageInFrame(textMessage, 5);
			} else {
				textMessage = "Alert '"+alertName+"' 's Create Date '"+createDate+"' is not matched with  expected date '"+expectedDateInDateFormat+"', hence failed. ";	
				fnpMymsg(textMessage);
				fnpDisplayingMessageInFrame(textMessage, 5);
				throw new Exception (textMessage);
			}
			
			
		 
		 
		 int createUser_colIndex =fnpFindColumnIndex_NOR(updatedHHeaderXpath, "Create User");
		 String createUser = fnpFetchFromTable_NOR(updatedTableDataXpath, rowNo, createUser_colIndex).trim();
		// Assert.assertEquals(createUser,hashXlData.get("iAg_Login_user") , "Create User is not matched. Expected is this'"+hashXlData.get("iAg_Login_user")+"' but actual is this '"+createUser+"'.");
		 Assert.assertTrue(createUser.equalsIgnoreCase(hashXlData.get("iAg_Login_user").trim()), "Create User is not matched. Expected is this'"+hashXlData.get("iAg_Login_user")+"' but actual is this '"+createUser+"'.");
		 
		 /****************************************************/
		 
		 
		 
		String linkValueInSpecifiedColumn = fnpFetchFromTable_NOR(updatedTableDataXpath, rowNo, colIndex);
		fnpMymsg("Going to click this link '" + linkValueInSpecifiedColumn + "' in row '"+rowNo+"' in this Alert '" + alertName + "'.");
			
		
		//driver.findElement(By.linkText(linkValueInSpecifiedColumn)).click();
		fnpClickInTable_NOR(updatedTableDataXpath, rowNo, colIndex);
		
		fnpLoading_wait();
			
			
			

	}
	
	
	public static void  fnpWorkAroundSubmittingSelectedRows() throws Throwable{
		//String scheme_wo_link_xpath=OR.getProperty("WoLinkAtImportResultsScreen_part1")+IPULSE_CONSTANTS.AH2_scheme_name+OR.getProperty("WoLinkAtImportResultsScreen_part2");
		
		fnpGetORObjectX("YesConfirmationBtnInSubmissionConfirmationPopup").click();
		fnpLoading_wait_specialCase(20);
		fnpWaitTillThisElementIsNoLongerDisplayedTillMaxWait_OR("LoadingImg", "Loading is not getting over", 300);
		if (fnpCheckElementDisplayedImmediately("TopMessage3")) {
			String SuccessfulMsg = fnpGetText_OR("TopMessage3");
			fnpMymsg("Top Message after clicking Yes Confirmation Btn In Submission Confirmation Popup ----" + SuccessfulMsg);

			Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"),
					"Top message does not contain 'success' word, so might be submission is not successfull.");
		} else {
			fnpMymsg("Success message is not visible for fist time after clicking YesConfirmationBtnInSubmissionConfirmationPopup, so going to refresh it again");
			driver.navigate().refresh();
			Thread.sleep(10000);
			fnpWaitForVisible("ImportResultPage_Producers_Table");
			int totalProducerRemaining=fnpCountRowsInTable("ImportResultPage_Producers_Table");
			String checkbox_xpath=".//*[@id='mainForm:prodTabView:importResOp2PrdCusDT_data']//div[contains(@id,'mainForm:prodTabView:importResOp2PrdCusDT:')][contains(@class,'ui-selectbooleancheckbox ui-chkbox')]//span[contains(@class,'ui-chkbox-icon ui-icon ui-icon-blank ui-c')]";
			List<WebElement> totalCheckboxes;

			int iwhileCounter=0;
			int maxWhileTry=5;
			while(totalProducerRemaining>0){
				iwhileCounter++;
				fnpMymsg("Producers table is not blank as rows count is '"+totalProducerRemaining+"' and it means all producers are not submitted sucessfully, so trying it again --"+iwhileCounter+" time.");
				//totalCheckboxes=driver.findElements(By.xpath(checkbox_xpath));
				totalCheckboxes=fnpGetORObject_list_NOR(checkbox_xpath, 1);
				for (int i = 0; i < totalCheckboxes.size(); i++) {
					totalCheckboxes.get(i).click();
					fnpLoading_wait();
					
/*					Thread.sleep(2000);
					fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
					*/
				}
				
				fnpClick_OR("SubmitSelectedRowsBtn");
				fnpGetORObjectX("YesConfirmationBtnInSubmissionConfirmationPopup").click();
				fnpLoading_wait_specialCase(20);
				fnpCheckError("Error is thrown by applicataion after clicking on Yes button in confirmation dialog for submitting the selected rows.");
				if (fnpCheckElementDisplayedImmediately("TopMessage3")) {
					String SuccessfulMsg = fnpGetText_OR("TopMessage3");
					fnpMymsg("Top Message after clicking Yes Confirmation Btn In Submission Confirmation Popup ----" + SuccessfulMsg);

					Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"),
							"Top message does not contain 'success' word, so might be submission is not successfull.");
				} else {
					fnpMymsg("Success message is not visible for fist time after clicking YesConfirmationBtnInSubmissionConfirmationPopup, so going to refresh it again--"+(iwhileCounter+1)+" time.");;
					driver.navigate().refresh();
					Thread.sleep(10000);
					totalProducerRemaining=fnpCountRowsInTable("ImportResultPage_Producers_Table");				
		
				}
				
				
				if (iwhileCounter>maxWhileTry) {
					msg="Submitting the producers again and again, max ="+maxWhileTry+" , still producers are left, they are not getting submitting.";
					fnpMymsg(msg);
					throw new Exception(msg);
					
				}
				
				

			}
			
			if (fnpCheckElementDisplayedImmediately("TopMessage3")) {
				String SuccessfulMsg = fnpGetText_OR("TopMessage3");
				fnpMymsg("Top Message after clicking Yes Confirmation Btn In Submission Confirmation Popup ----" + SuccessfulMsg);

				Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"),
						"Top message does not contain 'success' word, so might be submission is not successfull.");
			} else{
				fnpMymsg("Success message not visible even after submitting all producers");
			}
			
			
		}
		

		


		
	}
	
	
	
	public static void  fnpWorkAroundSubmittingSelectedRows_ignoringUnknownErrorIfAny() throws Throwable{
		//String scheme_wo_link_xpath=OR.getProperty("WoLinkAtImportResultsScreen_part1")+IPULSE_CONSTANTS.AH2_scheme_name+OR.getProperty("WoLinkAtImportResultsScreen_part2");
		
		fnpGetORObjectX("YesConfirmationBtnInSubmissionConfirmationPopup").click();
		//fnpLoading_wait_specialCase(20);
	//	fnpLoading_wait_withoutErrorChecking();
		fnpLoading_wait_withoutErrorChecking(20);
		fnpWaitTillThisElementIsNoLongerDisplayedTillMaxWait_OR("LoadingImg", "Loading is not getting over", 300);
		if (fnpCheckElementDisplayedImmediately("TopMessage3")) {
			String SuccessfulMsg = fnpGetText_OR("TopMessage3");
			fnpMymsg("Top Message after clicking Yes Confirmation Btn In Submission Confirmation Popup ----" + SuccessfulMsg);

			Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"),
					"Top message does not contain 'success' word, so might be submission is not successfull.");
		} else {
			
/*			if (fnpCheckElementDisplayedImmediately("ImportResultPage_Producers_Table")
					||  fnpCheckElementDisplayedImmediately("ImportResultPage_Producers_Table2")   ) {
				
				*/
			int totalProducerRemaining=0;
			String checkbox_xpath="";
			if (fnpCheckElementDisplayedImmediately("ImportResultPage_Producers_Table")){
				 totalProducerRemaining=fnpCountRowsInTable("ImportResultPage_Producers_Table");
				 checkbox_xpath=".//*[@id='mainForm:prodTabView:importResOp2PrdCusDT_data']//div[contains(@id,'mainForm:prodTabView:importResOp2PrdCusDT:')][contains(@class,'ui-selectbooleancheckbox ui-chkbox')]//span[contains(@class,'ui-chkbox-icon ui-icon ui-icon-blank ui-c')]";
			}else{
				if (fnpCheckElementDisplayedImmediately("ImportResultPage_Producers_Table2")) {
					totalProducerRemaining=fnpCountRowsInTable("ImportResultPage_Producers_Table2");
					 checkbox_xpath=".//*[@id='mainForm:prodTabView:importResOp2PrdDT_data']//div[contains(@id,'mainForm:prodTabView:importResOp2PrdDT:')][contains(@class,'ui-selectbooleancheckbox ui-chkbox')]//span[contains(@class,'ui-chkbox-icon ui-icon ui-icon-blank ui-c')]";
				}
			}
			
			List<WebElement> totalCheckboxes = driver.findElements(By.xpath(checkbox_xpath));
			if (totalCheckboxes.size()>0)   {	
				
			//	fnpMymsg("Success message is not visible for fist time after clicking YesConfirmationBtnInSubmissionConfirmationPopup, so going to refresh it again");
				fnpMymsg("Producers/data from excel are not uploaded successfully, so trying again.");
				fnpDesireScreenshot("Producers_not_uploaded_successfully_so_trying_again");
				driver.navigate().refresh();
				Thread.sleep(10000);
			//	fnpWaitForVisible("ImportResultPage_Producers_Table");

				

				
				if (fnpCheckElementDisplayedImmediately("ImportResultPage_Producers_Table")){
					 totalProducerRemaining=fnpCountRowsInTable("ImportResultPage_Producers_Table");
					 checkbox_xpath=".//*[@id='mainForm:prodTabView:importResOp2PrdCusDT_data']//div[contains(@id,'mainForm:prodTabView:importResOp2PrdCusDT:')][contains(@class,'ui-selectbooleancheckbox ui-chkbox')]//span[contains(@class,'ui-chkbox-icon ui-icon ui-icon-blank ui-c')]";
				}else{
					if (fnpCheckElementDisplayedImmediately("ImportResultPage_Producers_Table2")) {
						totalProducerRemaining=fnpCountRowsInTable("ImportResultPage_Producers_Table2");
						 checkbox_xpath=".//*[@id='mainForm:prodTabView:importResOp2PrdDT_data']//div[contains(@id,'mainForm:prodTabView:importResOp2PrdDT:')][contains(@class,'ui-selectbooleancheckbox ui-chkbox')]//span[contains(@class,'ui-chkbox-icon ui-icon ui-icon-blank ui-c')]";
					}
				}
				
			//	List<WebElement> totalCheckboxes;
				//totalCheckboxes=driver.findElements(By.xpath(checkbox_xpath));
				totalCheckboxes=fnpGetORObject_list_NOR(checkbox_xpath, 1);
				
				

				int iwhileCounter=0;
				int maxWhileTry=5;
			//	while(totalProducerRemaining>0){
				
/*				while(totalProducerRemaining>0   ||  ( fnpCheckElementDisplayedImmediately("ImportResultPage_Producers_Table")
						||  fnpCheckElementDisplayedImmediately("ImportResultPage_Producers_Table2")   )  ){
					
					*/
					
				while(totalCheckboxes.size()>0   &&  ( fnpCheckElementDisplayedImmediately("ImportResultPage_Producers_Table")
						||  fnpCheckElementDisplayedImmediately("ImportResultPage_Producers_Table2")   )  ){	
					
					iwhileCounter++;
					
					if (fnpCheckElementDisplayedImmediately("ImportResultPage_Producers_Table")){
						 totalProducerRemaining=fnpCountRowsInTable("ImportResultPage_Producers_Table");
						 checkbox_xpath=".//*[@id='mainForm:prodTabView:importResOp2PrdCusDT_data']//div[contains(@id,'mainForm:prodTabView:importResOp2PrdCusDT:')][contains(@class,'ui-selectbooleancheckbox ui-chkbox')]//span[contains(@class,'ui-chkbox-icon ui-icon ui-icon-blank ui-c')]";
					}else{
						if (fnpCheckElementDisplayedImmediately("ImportResultPage_Producers_Table2")) {
							totalProducerRemaining=fnpCountRowsInTable("ImportResultPage_Producers_Table2");
							 checkbox_xpath=".//*[@id='mainForm:prodTabView:importResOp2PrdDT_data']//div[contains(@id,'mainForm:prodTabView:importResOp2PrdDT:')][contains(@class,'ui-selectbooleancheckbox ui-chkbox')]//span[contains(@class,'ui-chkbox-icon ui-icon ui-icon-blank ui-c')]";
						}
					}
					
					
					//totalCheckboxes=driver.findElements(By.xpath(checkbox_xpath));
					totalCheckboxes=fnpGetORObject_list_NOR(checkbox_xpath, 1);
					fnpMymsg("Producers table is not blank as checkboxes count is '"+totalCheckboxes.size()+"' and it means all producers are not submitted sucessfully, so trying it again --"+iwhileCounter+" time.");
					
					for (int i = 0; i < totalCheckboxes.size(); i++) {
						totalCheckboxes.get(i).click();
						fnpLoading_wait();
						
	/*					Thread.sleep(2000);
						fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
						*/
					}
					
					fnpClick_OR("SubmitSelectedRowsBtn");
					fnpGetORObjectX("YesConfirmationBtnInSubmissionConfirmationPopup").click();
					//fnpLoading_wait_specialCase(20);
					fnpLoading_wait_withoutErrorChecking(20);
					//fnpCheckError("Error is thrown by applicataion after clicking on Yes button in confirmation dialog for submitting the selected rows.");
					if (fnpCheckElementDisplayedImmediately("ImportResultPage_Producers_Table")){
						 totalProducerRemaining=fnpCountRowsInTable("ImportResultPage_Producers_Table");
						 checkbox_xpath=".//*[@id='mainForm:prodTabView:importResOp2PrdCusDT_data']//div[contains(@id,'mainForm:prodTabView:importResOp2PrdCusDT:')][contains(@class,'ui-selectbooleancheckbox ui-chkbox')]//span[contains(@class,'ui-chkbox-icon ui-icon ui-icon-blank ui-c')]";
					}else{
						if (fnpCheckElementDisplayedImmediately("ImportResultPage_Producers_Table2")) {
							totalProducerRemaining=fnpCountRowsInTable("ImportResultPage_Producers_Table2");
							 checkbox_xpath=".//*[@id='mainForm:prodTabView:importResOp2PrdDT_data']//div[contains(@id,'mainForm:prodTabView:importResOp2PrdDT:')][contains(@class,'ui-selectbooleancheckbox ui-chkbox')]//span[contains(@class,'ui-chkbox-icon ui-icon ui-icon-blank ui-c')]";
						}
					}
					//totalCheckboxes=driver.findElements(By.xpath(checkbox_xpath));
					totalCheckboxes=fnpGetORObject_list_NOR(checkbox_xpath, 1);
					if ( fnpCheckElementDisplayedImmediately("TopMessage3")    & (totalCheckboxes.size()==0)) {
						String SuccessfulMsg = fnpGetText_OR("TopMessage3");
						fnpMymsg("Top Message after clicking Yes Confirmation Btn In Submission Confirmation Popup ----" + SuccessfulMsg);

						Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"),
								"Top message does not contain 'success' word, so might be submission is not successfull.");
						
						break;
					} else {
						
						if (totalCheckboxes.size()>0) {
							//fnpMymsg("Success message is not visible for "+(iwhileCounter+1)+" time after clicking YesConfirmationBtnInSubmissionConfirmationPopup, so going to refresh it again--"+(iwhileCounter+1)+" time.");;
							fnpMymsg("Producers/data from excel are not uploaded successfully, so trying again--"+iwhileCounter);
							fnpDesireScreenshot("Producers_not_uploaded_successfully_so_trying_again_"+iwhileCounter);
							
							driver.navigate().refresh();
							Thread.sleep(10000);
						//	totalProducerRemaining=fnpCountRowsInTable("ImportResultPage_Producers_Table");		
							
							if (fnpCheckElementDisplayedImmediately("ImportResultPage_Producers_Table")){
								 totalProducerRemaining=fnpCountRowsInTable("ImportResultPage_Producers_Table");
								 checkbox_xpath=".//*[@id='mainForm:prodTabView:importResOp2PrdCusDT_data']//div[contains(@id,'mainForm:prodTabView:importResOp2PrdCusDT:')][contains(@class,'ui-selectbooleancheckbox ui-chkbox')]//span[contains(@class,'ui-chkbox-icon ui-icon ui-icon-blank ui-c')]";
							}else{
								if (fnpCheckElementDisplayedImmediately("ImportResultPage_Producers_Table2")) {
									totalProducerRemaining=fnpCountRowsInTable("ImportResultPage_Producers_Table2");
									 checkbox_xpath=".//*[@id='mainForm:prodTabView:importResOp2PrdDT_data']//div[contains(@id,'mainForm:prodTabView:importResOp2PrdDT:')][contains(@class,'ui-selectbooleancheckbox ui-chkbox')]//span[contains(@class,'ui-chkbox-icon ui-icon ui-icon-blank ui-c')]";
								}
							}
							
							//totalCheckboxes=driver.findElements(By.xpath(checkbox_xpath));
							totalCheckboxes=fnpGetORObject_list_NOR(checkbox_xpath, 1);
						} else {
							break;
						}
	
			
					}
					
					
					if (iwhileCounter>maxWhileTry) {
						msg="Submitting the producers again and again, max ="+maxWhileTry+" , still producers are left, they are not getting submitting.";
						fnpMymsg(msg);
						throw new Exception(msg);
						
					}
					
					

				}
				
				if (fnpCheckElementDisplayedImmediately("TopMessage3")) {
					String SuccessfulMsg = fnpGetText_OR("TopMessage3");
					fnpMymsg("Top Message after clicking Yes Confirmation Btn In Submission Confirmation Popup ----" + SuccessfulMsg);

					Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"),
							"Top message does not contain 'success' word, so might be submission is not successfull.");
				} else{
					msg="Success message not visible even after submitting all producers";
					fnpMymsg(msg);
					throw new Exception(msg);
					
				}
			}else{
				fnpCheckError("");
				if (fnpCheckElementDisplayedImmediately("TopMessage3")) {
					String SuccessfulMsg = fnpGetText_OR("TopMessage3");
					fnpMymsg("Top Message after clicking Yes Confirmation Btn In Submission Confirmation Popup ----" + SuccessfulMsg);

					Assert.assertTrue(SuccessfulMsg.toLowerCase().contains("success"),
							"Top message does not contain 'success' word, so might be submission is not successfull.");
				} else{
					msg="Success message not visible even after submitting all producers";
					fnpMymsg(msg);
					throw new Exception(msg);
					
				}
			}

			
			
		}
		

		
		fnpCheckError("");

		
	}
	
	
	
	
	
	public static void test() throws Throwable{
		
	try{	
		
		fnpLoading_wait();
		

		
		
		fnpWaitForVisible("iAg_ecapSPATaskHeader");
		
		String headerValue = fnpGetText_OR("iAg_ecapSPATaskHeader");
		
		if (!(headerValue.equalsIgnoreCase(iAg_Technical_Review_in_Progress_headerName))) {
			   fnpDesireScreenshot("iAg_Technical_Review_in_Progress_headerName");
		} else {
			fnpMymsg("Technical Review in Progress screen is opened successfully.");
		}
		
	}catch(Throwable t){
		Thread.sleep(100);
	}

	}
	
	
	
	public static void test1() throws Throwable{
		try{
			workOrderNo="W0582372";
			Thread.sleep(1);
			hashXlData.clear();
			fnpLoadHashData(hashXlData, currentSuiteXls, "Certification_Autotest", 2);

			hashXlData.put("currentwo", workOrderNo);
			
			
			
			Hashtable<String, String> ht = new Hashtable<String,String>();
			ht.putAll(hashXlData);
			//fnpClickTopHomeMenu();
			// -------Audit_awaiting_Technical_Review_Assigned_to_me -------------------
			TestSuiteBase_HealthScience.fnpCommonAlertGeneratedVerificationHS(ht, "Audit_awaiting_Technical_Review_Assigned_to_me", "Audit_awaiting_Technical_Review_Assigned_to_me_AlertTable_header",
					"WO #", "Audit_awaiting_Technical_Review_Assigned_to_me_AlertTable", "Audit_awaiting_Technical_Review_Assigned_to_me_Alert_WO_filterTxtBox",
					workOrderNo);
			// ---------------------------------------------
			
			
			int colIndex = fnpFindColumnIndex("Audit_awaiting_Technical_Review_Assigned_to_me_AlertTable_header", "Task #");
			String s = fnpFetchFromTable("Audit_awaiting_Technical_Review_Assigned_to_me_AlertTable", 1, colIndex);
			
			driver.findElement(By.linkText(s)).click();

		}catch(Throwable t){
			Thread.sleep(100);
		}
	}
	
	
}


