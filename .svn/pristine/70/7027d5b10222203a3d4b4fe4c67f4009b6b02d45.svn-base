package nsf.ecap.Quartz_Jobs_suite;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import nsf.ecap.Work_Order_suite.TestSuiteBase;
import nsf.ecap.util.TestUtil;

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

public class TestSuiteBase_Quartz_Jobs extends TestSuiteBase {

	public static Quartz_Jobs BS_P01;
	public static String runmodes[] = null;
	public static int count = -1;








	// check if the suite has to be skipped or not
	@BeforeSuite
	public void checkSuiteSkip() throws Throwable {

		fnInitialize();

		//currentSuiteName = "Quartz_Jobs_suite";
		setCurrentSuiteName("Quartz_Jobs_suite");
		
	//	currentSuiteXls=Quartz_Jobs_suitexls;
		setCurrentSuiteExcel(Quartz_Jobs_suitexls);
		
		fnpMymsg("             ");
		fnpMymsg("             ");
		fnpMymsg("=========================================================================================================================================");
		fnpMymsg("####################"+currentSuiteName+" Start ############################################################");
		fnpMymsg("Checking Runmode of "+currentSuiteName);
		if (!TestUtil.isSuiteRunnable(suiteXls, currentSuiteName)) {
			fnpMymsg("Skipped "+currentSuiteName+" as the runmode was set to NO");
			//fnpMymsg("####################"+currentSuiteName+" End ############################################################");
			//fnpMymsg("=========================================================================================================================================");
			throw new SkipException("Runmode of "+currentSuiteName+" set to no. So Skipping all tests in "+currentSuiteName+".");
		}

		if (TestUtil.isSuiteRunnable(suiteXls, currentSuiteName)) {
			browserName = TestUtil.getBrowserName(suiteXls, currentSuiteName);
			excelSiteURL= TestUtil.getExcelSiteURL(suiteXls, currentSuiteName);
			fnpMymsg("Browser Name is :" + browserName);
			fnpMymsg("======================");
			currentSuiteCode = "Quartz";

			int rowNum = suiteXls.getCellRowNum("Test Suite", "TSID", currentSuiteName);
			suiteXls.setCellData("Test Suite", "Last_Execution", rowNum, fnReturnDateWithTimeForExcel());

		}

	}







	// To remove IEDriver.exe from Windows Task Manager
	 @AfterSuite(alwaysRun=true)
	//@AfterSuite
	public void cleanUp() {
		try {
			referenceSuite=currentSuiteName;
			fnpMymsg("#################### "+currentSuiteName+" Suite End ############################################################");
			if (driver!=null) {
				driver.quit();
			}
			IsBrowserPresentAlready = false;
			killprocess();
		}
		catch (Throwable t) {
			// Nothing to do
		}

	}


	




}
