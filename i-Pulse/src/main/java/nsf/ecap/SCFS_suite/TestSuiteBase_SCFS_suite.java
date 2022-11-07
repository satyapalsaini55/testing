package nsf.ecap.SCFS_suite;

import static nsf.ecap.config.IPULSE_CONSTANTS.WOISOTaskTab_Certification_ClientRecordChangeAIName;
import static nsf.ecap.config.IPULSE_CONSTANTS.WOISOTaskTab_Certification_MainTaskAIName;
import static nsf.ecap.config.IPULSE_CONSTANTS.WOISOTaskTab_TaskAISummaryTableColName_TaskAIName;
import static nsf.ecap.config.IPULSE_CONSTANTS.WOISOTaskTab_TaskAISummaryTableColName_TaskAINo;
import static nsf.ecap.config.IPULSE_CONSTANTS.WOISOTaskTab_TechnicalReviewAIName;
import static nsf.ecap.config.IPULSE_CONSTANTS.WOISOTaskType_CertificationAudit;

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
import java.util.concurrent.TimeUnit;

import nsf.ecap.ISR_suite.ISO9001_Single;
import nsf.ecap.ISR_suite.TestSuiteBase_ISR_suite;
import nsf.ecap.Proposals_suite.TestSuiteBase_Proposal;
import nsf.ecap.Work_Order_suite.NewNew_Draft_InReview_No_Fac;
import nsf.ecap.Work_Order_suite.TestSuiteBase;
import nsf.ecap.config.IPULSE_CONSTANTS;
import nsf.ecap.util.ErrorUtil;
import nsf.ecap.util.TestUtil;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
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

import com.google.common.base.Throwables;
import com.google.common.base.Verify;

public class TestSuiteBase_SCFS_suite extends TestSuiteBase_ISR_suite {

	public static String runmodes[] = null;
	public static int count = -1;
	
	//public static String usingGoldenProcedureOrOasis="Oasis";//"golden";//"Oasis" or "golden" value

	public static ISO9001_Single ISR_BS01;

	// check if the suite has to be skipped or not
	@BeforeSuite
	public void checkSuiteSkip() throws Throwable {

		fnInitialize();

		//currentSuiteName = "SCFS_suite";
		setCurrentSuiteName("SCFS_suite");
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
			currentSuiteCode = "SCFS";

			int rowNum = suiteXls.getCellRowNum("Test Suite", "TSID", currentSuiteName);
			suiteXls.setCellData("Test Suite", "Last_Execution", rowNum, fnReturnDateWithTimeForExcel());

			//currentSuiteXls = SCFS_suitexls;
			setCurrentSuiteExcel(SCFS_suitexls);
		}

	}

	// To remove IEDriver.exe from Windows Task Manager
	 @AfterSuite(alwaysRun=true)
	//@AfterSuite
	public void cleanUp() {
		 
		 
/*		loginAs="";
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
	
	
	
	
	
	
	
	
}

