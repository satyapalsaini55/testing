package nsf.ecap.Audit_Functional_suite;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import nsf.ecap.IssueMgt_Suite.TestSuiteBase_IM;
import nsf.ecap.MonitorPlan_Suite.TestSuiteBase_MonitorPlan;
import nsf.ecap.Work_Order_suite.NewNew_Draft_InReview_No_Fac;
import nsf.ecap.Work_Order_suite.TestSuiteBase;
import nsf.ecap.util.ErrorUtil;
import nsf.ecap.util.TestUtil;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.google.common.base.Throwables;

public class TestSuiteBase_Audit_Functional_suite extends TestSuiteBase {

	public static Search_Audit BS_NSFO_01;

	public static String currentClassNameInSimpleText = "";



	public static String runmodes[] = null;
	public static Integer ColumnNo;
	public static Integer RowCount;

	public static Search_Audit SA_BS01;

	// ######################################################### Common
	// Functions
	// #######################################################################

	// check if the suite has to be skip
	@BeforeSuite
	public void checkSuiteSkip() throws Throwable {
		currentSuiteName = "Audit_Functional_suite";
		fnInitialize();
		//BrowserCheck();
		if (TestUtil.isSuiteRunnable(suiteXls, currentSuiteName)) {
			FileUtils.deleteDirectory(new File((System.getProperty("user.dir") + CONFIG.getProperty("screenshots_path") + "//currentSuiteName//")));
			browserName = TestUtil.getBrowserName(suiteXls, currentSuiteName);
			excelSiteURL = TestUtil.getExcelSiteURL(suiteXls, currentSuiteName);

			int rowNum = suiteXls.getCellRowNum("Test Suite", "TSID", currentSuiteName);
			suiteXls.setCellData("Test Suite", "Last_Execution", rowNum, fnReturnDateWithTimeForExcel());

			currentSuiteCode = "Audit_Functional";
			currentSuiteXls = Audit_Functional_suitexls;

		}
		if (!TestUtil.isSuiteRunnable(suiteXls, currentSuiteName)) {
			fnpMymsg("Skipped "+ currentSuiteName+" as the runmode was set to NO");
			throw new SkipException("Runmode of "+ currentSuiteName+"set to no. So Skipping all tests in "+ currentSuiteName);
		}


	}

	
	

}