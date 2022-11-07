package nsf.ecap.SCFS_suite;

import static nsf.ecap.config.IPULSE_CONSTANTS.WOISOTaskTab_Certification_MainTaskAIName;
import static nsf.ecap.config.IPULSE_CONSTANTS.WOISOTaskTab_TaskAISummaryTableColName_TaskAIName;
import static nsf.ecap.config.IPULSE_CONSTANTS.WOISOTaskTab_TechnicalReviewAIName;
import static nsf.ecap.config.IPULSE_CONSTANTS.WOISOTaskType_CertificationAudit;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import nsf.ecap.Work_Order_suite.NewNew_Draft_InReview_No_Fac;
import nsf.ecap.Work_Order_suite.TestSuiteBase;
import nsf.ecap.config.IPULSE_CONSTANTS;
import static nsf.ecap.config.IPULSE_CONSTANTS.*;
import nsf.ecap.util.ErrorUtil;
import nsf.ecap.util.TestDataProvider;
import nsf.ecap.util.TestUtil;
import nsf.ecap.util.Xls_Reader;

import nsf.ecap.ISR_suite.*;




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

public class Cert_Standards extends TestSuiteBase_SCFS_suite {

	public Xls_Reader currentSuiteXlsReference;
	public String className ;

	String deskAuditTaskTab_expectedBillCode = "";
	double deskAuditTaskTab_expectedQuantity = 0;
	double deskAuditTaskTab_expectedAuditDays = 0;

	String certAuditTaskTab_expectedBillCode = "";
	double certAuditTaskTab_expectedQuantity = 0;
	double certAuditTaskTab_expectedAuditDays = 0;

	@BeforeTest
	public void checkTestSkip() throws Throwable {
		currentSuiteXlsReference = currentSuiteXls;
		
		ISR_BS01 = new ISO9001_Single();
		ISR_BS01.checkTestSkip(this.getClass().getSimpleName());
		
		className = this.getClass().getSimpleName();
		runmodes = TestUtil.getDataSetRunmodes(currentSuiteXlsReference, className);
		fail = false;
		isTestPass = true;

		start = new Date();

	}

	@Test(dataProviderClass = TestDataProvider.class, dataProvider = "Cert_Standards_DataProvider")
	//@Test(priority = 1)
	public void Cert_Standards_flow(Hashtable<String, String> table) throws Throwable {
		
		skip=false;
		
		fnpMymsg("****************************************************************");
		count++;

		if (!runmodes[count].equalsIgnoreCase("Y")) {
			skip = true;
			fnpMymsg("Runmode for test set data set to no " + (count + 1));
			fnpMymsg("*****************");
			fnpMymsg("");
			throw new SkipException("Runmode for test set data set to no " + (count + 1));
		}
		
		
		
		try {
			

			IsBrowserPresentAlready = false;  // to close on each data set (whether pass or fail)
			//hashXlData=table;
			hashXlData=new HashMap(table); 
			hashXlData.put("Audit_usingGoldenProcedureOrOasis", "golden");
			
			
			
			ISR_BS01.CreateWOFlow();
			
			ISR_BS01.Standard_Facility_Tab();
			ISR_BS01.Financial_Tab_And_InReviewToInProcess();
			String expectedTaskNames = ((String) hashXlData.get("Task_created_just_after_InProcess"));
			if (expectedTaskNames.contains(WOISOTaskName_DeskAudit)) {
				ISR_BS01.DeskAuditTask();
			}
			
			
			
/*			
			String expectedTaskNames = ((String) hashXlData.get("Task_created_just_after_InProcess"));
			fnpLaunchBrowserAndLogin();
			fnpSearchWorkOrderOnly("W0670896");
			*/
			
			
			if (expectedTaskNames.contains(WOISOTaskName_CertificationAudit)) {
				ISR_BS01.CertAuditTask();
			}
			
			

			
			
			
			String fromAuditString = (String) hashXlData.get("copy_From_Audit_ForCertAuditTask").trim();
			fnReCertAuditTask(WOISOTaskType_ReCertificationAudit, WOISOTaskTab_ReCertification_MainTaskAIName, fromAuditString);
			
			
			
			
			
		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "  Cert_Standards_flow   is fail . ", "Cert_Standards_flowFail_Client--"+(String) hashXlData.get("Corp/Facility#")+"_Standard--"+ (String) hashXlData.get("Standard"));
			//IsBrowserPresentAlready = false; // This is for special reason if one case is fail due to application error or any other reason then it close the
			// browser and login again.
			//driver.quit();

		}

	}


	


	
	@AfterMethod
	public void reportDataSetResult() throws Throwable {
		
		fnpCommonCloseBrowsersAndKillProcess();
		
		
		if (skip)
				fnpMymsg("");
			//comment below line to not write skip in result column  OR not overwrite previous result if current test case for current data set is skipped
			//TestUtil.reportDataSetResult(currentSuiteXlsReference, this.getClass().getSimpleName(), count + 2, "SKIP");
		else if (fail) {
			isTestPass = false;
			TestUtil.reportDataSetResult(currentSuiteXlsReference, this.getClass().getSimpleName(), count + 2, "FAIL");
			fnpElapsedTime(currentSuiteName, this.getClass().getSimpleName(), currentScriptCode +" ====>     Client --> "+(String) hashXlData.get("Corp/Facility#")+"   And Standard --> "+ (String) hashXlData.get("Standard"), "FAIL");
		} else
			{
			TestUtil.reportDataSetResult(currentSuiteXlsReference, this.getClass().getSimpleName(), count + 2, "PASS");			
			//currentSuiteXlsReference.setCellData("Test Cases", "Last_Successful_Execution", rowNum, fnReturnDateWithTimeForExcel());
			currentSuiteXlsReference.setCellData(this.getClass().getSimpleName(), "Last_Successful_Execution", count + 2, fnReturnDateWithTimeForExcel());
			
			fnpElapsedTime(currentSuiteName, this.getClass().getSimpleName(), currentScriptCode +" ====>     Client --> "+(String) hashXlData.get("Corp/Facility#")+"   And Standard --> "+ (String) hashXlData.get("Standard"), "PASS");
			
			
			}
		skip = false;
		fail = false;

	}
	
	
	
	
	

	@AfterTest
	public void reportTestResult() throws Throwable {
		int rowNum = currentSuiteXls.getCellRowNum("Test Cases", "TCID", this.getClass().getSimpleName());
		currentSuiteXls.setCellData("Test Cases", "Last_Execution", rowNum, fnReturnDateWithTimeForExcel());

		// Add Results column to test case sheet
		if (isTestPass) {
			TestUtil.reportDataSetResult(currentSuiteXls, "Test Cases", TestUtil.getRowNum(currentSuiteXls, this.getClass().getSimpleName()), "PASS");
			currentSuiteXls.setCellData("Test Cases", "Last_Successful_Execution", rowNum, fnReturnDateWithTimeForExcel());
			//fnpElapsedTime(currentSuiteName, this.getClass().getSimpleName(), currentScriptCode, "PASS");
		} else {
			TestUtil.reportDataSetResult(currentSuiteXls, "Test Cases", TestUtil.getRowNum(currentSuiteXls, this.getClass().getSimpleName()), "FAIL");
			//fnpElapsedTime(currentSuiteName, this.getClass().getSimpleName(), currentScriptCode, "FAIL");
		}
		fnpMymsg("=========================================================================================================================================");

		hashXlData.clear();

		fnpCommonCloseBrowsersAndKillProcess();

	}
	
	
	
	

	@AfterTest
	public void closebrowser() throws InterruptedException {

		// for time being,later uncomment it
		//driver.quit();
		//IsBrowserPresentAlready = false;
		setIsBrowserPresentAlready_false();
		killprocess();

	}

	

}