package nsf.ecap.Client_Suite;

import java.util.Date;

import nsf.ecap.util.*;
import nsf.ecap.MonitorPlan_Suite.TestSuiteBase_MonitorPlan;
import nsf.ecap.NSFOnline_Suite.TestSuiteBase_NSFOnline;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;

public class Check_iPulse_Login_Time extends TestSuiteBase_CLNT{
	
	public boolean Run_Trunk = true;
	public boolean Run_Test = true;
	public boolean Run_Staging = true;
	
	
	
	
	
	public static Integer count = -1;
	public static boolean skip = true;
	
	public static String siteUrl_TRUNK = "https://testapps.nsf.org/trunkecap";
	public static String siteUrl_TEST = "https://testapps.nsf.org/ecap";
	public static String siteUrl_STAGING = "https://stgapps.nsf.org/ecap";
	
	public static String UserName = "testscriptuser";
	public static String Password = "welcome1010";
	
	
	
	
	@Parameters({ "className" })
	@BeforeTest
		public void checkTestSkip(String className) throws Exception {
		
		isTestCasePass=true;
		
		try {
			if (className.isEmpty()) {
				className = this.getClass().getSimpleName();}
		
			classNameText=className;
			currentScriptCode=fnpMappingClassNameWithScenarioCode(classNameText).trim();
	
			

			if (!TestUtil.isTestCaseRunnable(Client_Suitexls, className)) {
				fnsApps_Report_Logs("Skipping Test Case " + className + " as runmode set to NO");
				
				throw new SkipException("Skipping Test Case " + className + " as runmode set to NO");}

			runmodes = TestUtil.getDataSetRunmodes(Client_Suitexls, className);	
		
			fnsApps_Report_Logs("=========================================================================================================================================");
		
		}catch(SkipException sk){
			String errorMsg = sk.getMessage();
			Exception c = new Exception(errorMsg);
			ErrorUtil.addVerificationFailure(c);
			throw new SkipException(errorMsg + " \n\nSo,Skipping Test Case" + className);
		}catch (Throwable t) {								
				fnsTake_Screen_Shot(className);				
				String stackTrace = Throwables.getStackTraceAsString(t);
				String errorMsg = t.getMessage();
				errorMsg = errorMsg + "\n\n\n\nSee screenshot '" + className + "'\n\n" + stackTrace;
				Exception c = new Exception(errorMsg);
				ErrorUtil.addVerificationFailure(c);
				throw new Exception(errorMsg );
		}
}
	
	
	
	
@Test(priority = 1)
	public void TRUNK_Browser_Launch_And_Enter_User_Password() throws Throwable{
	
	startExecutionTime = fnpTimestamp();
	ScreenShotFlagWithOR_CLNT = true;
	if(Run_Trunk){
		try {
	
			if (BrowserName.equalsIgnoreCase("IE")) {
				System.setProperty("webdriver.ie.driver", CONFIG.getProperty("IEDriverPath"));
	
				DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
				caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				caps.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
				caps.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
				
			    			
			    driver=new InternetExplorerDriver(caps);
				fnsApps_Report_Logs("Browser type is IE");
			}
			
			
			if (BrowserName.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", CONFIG.getProperty("ChromeDriverPath"));
				DesiredCapabilities caps = DesiredCapabilities.chrome();
				
				ChromeOptions options = new ChromeOptions();
				options.addArguments("test-type");
				caps.setCapability(ChromeOptions.CAPABILITY, options);																																												
																																																				
				driver=new ChromeDriver(caps);				
			    
				fnsApps_Report_Logs("Browser type is CHROME");
			}
			
	
			if (BrowserName.equalsIgnoreCase("firefox")) {
				driver = new FirefoxDriver();
				fnsApps_Report_Logs("Browser type is Firefox");
			}
	
	
	
			
			try{
				start = new Date();
							
				fnsApps_Report_Logs("Application URL is == [ "+siteUrl_TRUNK+" ].");
							
				driver.get(siteUrl_TRUNK);
				  
				
				TestSuiteBase_MonitorPlan.fnsComman_BrowserLogin_ElementWait(OR_MtrPlan.getProperty("UserName"), CONFIG.getProperty("Element_MAX_WaitTime"));
				
				
				Thread.sleep(1500);
				
				
				fnsApps_Report_Logs("Application User Name is == [ "+UserName+" ].");
				fnsApps_Report_Logs("Application Password is == [ "+Password+" ].");
				TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(OR_MtrPlan.getProperty("UserName")).clear();
				TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(OR_MtrPlan.getProperty("UserName")).sendKeys("");
				TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(OR_MtrPlan.getProperty("UserName")).sendKeys(UserName);
				TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(OR_MtrPlan.getProperty("password")).sendKeys("");
				TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(OR_MtrPlan.getProperty("password")).sendKeys(Password);
				
			}catch (Throwable t) {
				throw new Exception(Throwables.getStackTraceAsString(t));
			}
	
			
			
		}catch(NoSuchWindowException W){
			isTestCasePass = false;
			throw new Exception(W.getMessage());			} 
		catch (Throwable t) {
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}
	
}




@Test(priority = 2, dependsOnMethods={"TRUNK_Browser_Launch_And_Enter_User_Password"})
public void TRUNK_Click_on_Login_Button_and_Wait_Till_Home_Screen_Load() throws Throwable{
	if(Run_Trunk){
		try{
			TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(OR_MtrPlan.getProperty("Login"));
			
			TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(OR_MtrPlan.getProperty("logOutBtn"));
			
			fnsGet_Element_Enabled("Footer");
				
			fnsApps_Report_Logs("PASSED == i-Pulse TRUNK Home screen load successfully.");
			driver.quit();
			fnsApps_Report_Logs("********************************************************************************************************************");	
		}catch (Throwable t) {
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}
	
}	

	
@Test(priority = 3, dependsOnMethods={"TRUNK_Click_on_Login_Button_and_Wait_Till_Home_Screen_Load"})
	public void TEST_Browser_Launch_And_Enter_User_Password() throws Throwable{
	
	if(Run_Test){
		try {

			if (BrowserName.equalsIgnoreCase("IE")) {
				System.setProperty("webdriver.ie.driver", CONFIG.getProperty("IEDriverPath"));
	
				DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
				caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				caps.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
				caps.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
				
			    			
			    driver=new InternetExplorerDriver(caps);
				fnsApps_Report_Logs("Browser type is IE");
			}
			
			
			if (BrowserName.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", CONFIG.getProperty("ChromeDriverPath"));
				DesiredCapabilities caps = DesiredCapabilities.chrome();
				
				ChromeOptions options = new ChromeOptions();
				options.addArguments("test-type");
				caps.setCapability(ChromeOptions.CAPABILITY, options);																																												
																																																				
				driver=new ChromeDriver(caps);				
			    
				fnsApps_Report_Logs("Browser type is CHROME");
			}
			
	
			if (BrowserName.equalsIgnoreCase("firefox")) {
				driver = new FirefoxDriver();
				fnsApps_Report_Logs("Browser type is Firefox");
			}
	
	
	
			
			try{
				start = new Date();
							
				fnsApps_Report_Logs("Application URL is == [ "+siteUrl_TEST+" ].");
							
				driver.get(siteUrl_TEST);
				  
				
				TestSuiteBase_MonitorPlan.fnsComman_BrowserLogin_ElementWait(OR_MtrPlan.getProperty("UserName"), CONFIG.getProperty("Element_MAX_WaitTime"));
				
				
				Thread.sleep(1500);
				
				
				fnsApps_Report_Logs("Application User Name is == [ "+UserName+" ].");
				fnsApps_Report_Logs("Application Password is == [ "+Password+" ].");
				TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(OR_MtrPlan.getProperty("UserName")).clear();
				TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(OR_MtrPlan.getProperty("UserName")).sendKeys("");
				TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(OR_MtrPlan.getProperty("UserName")).sendKeys(UserName);
				TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(OR_MtrPlan.getProperty("password")).sendKeys("");
				TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(OR_MtrPlan.getProperty("password")).sendKeys(Password);
				
			}catch (Throwable t) {
				throw new Exception(Throwables.getStackTraceAsString(t));
			}
	
			
			
		}catch(NoSuchWindowException W){
			isTestCasePass = false;
			throw new Exception(W.getMessage());			} 
		catch (Throwable t) {
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}
}




@Test(priority = 4, dependsOnMethods={"TEST_Browser_Launch_And_Enter_User_Password"})
public void TEST_Click_on_Login_Button_and_Wait_Till_Home_Screen_Load() throws Throwable{
	if(Run_Test){
		try{
			TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(OR_MtrPlan.getProperty("Login"));
			
			TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(OR_MtrPlan.getProperty("logOutBtn"));
				
			fnsGet_Element_Enabled("Footer");
				
			fnsApps_Report_Logs("PASSED ==  i-Pulse TEST Home screen load successfully.");
			driver.quit();
			fnsApps_Report_Logs("********************************************************************************************************************");	
		}catch (Throwable t) {
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}
}




@Test(priority = 5, dependsOnMethods={"TEST_Click_on_Login_Button_and_Wait_Till_Home_Screen_Load"})
public void STAGING_Browser_Launch_And_Enter_User_Password() throws Throwable{
	
	if(Run_Staging){
		try {
		
			if (BrowserName.equalsIgnoreCase("IE")) {
				System.setProperty("webdriver.ie.driver", CONFIG.getProperty("IEDriverPath"));
		
				DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
				caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				caps.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
				caps.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
				
			    			
			    driver=new InternetExplorerDriver(caps);
				fnsApps_Report_Logs("Browser type is IE");
			}
			
			
			if (BrowserName.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", CONFIG.getProperty("ChromeDriverPath"));
				DesiredCapabilities caps = DesiredCapabilities.chrome();
				
				ChromeOptions options = new ChromeOptions();
				options.addArguments("test-type");
				caps.setCapability(ChromeOptions.CAPABILITY, options);																																												
																																																				
				driver=new ChromeDriver(caps);				
			    
				fnsApps_Report_Logs("Browser type is CHROME");
			}
			
		
			if (BrowserName.equalsIgnoreCase("firefox")) {
				driver = new FirefoxDriver();
				fnsApps_Report_Logs("Browser type is Firefox");
			}
		
		
		
			
			try{
				start = new Date();
							
				fnsApps_Report_Logs("Application URL is == [ "+siteUrl_STAGING+" ].");
							
				driver.get(siteUrl_STAGING);
				  
				
				TestSuiteBase_MonitorPlan.fnsComman_BrowserLogin_ElementWait(OR_MtrPlan.getProperty("UserName"), CONFIG.getProperty("Element_MAX_WaitTime"));
				
				
				Thread.sleep(1500);
				
				
				fnsApps_Report_Logs("Application User Name is == [ "+UserName+" ].");
				fnsApps_Report_Logs("Application Password is == [ "+Password+" ].");
				TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(OR_MtrPlan.getProperty("UserName")).clear();
				TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(OR_MtrPlan.getProperty("UserName")).sendKeys("");
				TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(OR_MtrPlan.getProperty("UserName")).sendKeys(UserName);
				TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(OR_MtrPlan.getProperty("password")).sendKeys("");
				TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(OR_MtrPlan.getProperty("password")).sendKeys(Password);
				
			}catch (Throwable t) {
				throw new Exception(Throwables.getStackTraceAsString(t));
			}
		
		}catch(NoSuchWindowException W){
			isTestCasePass = false;
			throw new Exception(W.getMessage());			} 
		catch (Throwable t) {
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}
}




@Test(priority = 6, dependsOnMethods={"STAGING_Browser_Launch_And_Enter_User_Password"})
public void STAGING_Click_on_Login_Button_and_Wait_Till_Home_Screen_Load() throws Throwable{
	
	if(Run_Staging){
		try{
			TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(OR_MtrPlan.getProperty("Login"));
			
			TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(OR_MtrPlan.getProperty("logOutBtn"));
				
			fnsGet_Element_Enabled("Footer");
				driver.quit();
			fnsApps_Report_Logs("PASSED ==  i-Pulse STAGING Home screen load successfully.");
				fnsApps_Report_Logs("********************************************************************************************************************");	
		}catch (Throwable t) {
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}
}


	

	

	
		
		@AfterMethod
		public void reportDataSetResult() {
			if(count>-1){
				if (skip)
					TestUtil.reportDataSetResult(Client_Suitexls, this.getClass().getSimpleName(), count + 2 , "SKIP");
				else if (fail) {
					isTestCasePass = false;
					TestUtil.reportDataSetResult(Client_Suitexls, this.getClass().getSimpleName(), count + 2, "FAIL");
				} else
					TestUtil.reportDataSetResult(Client_Suitexls, this.getClass().getSimpleName(), count + 2, "PASS");
		
				skip = false;
				fail = false;
			}
	}

	
	@AfterTest
	public void reportTestResult() throws Throwable {
		
		TestSuiteBase_MonitorPlan.fns_ReportTestResult_atClassLevel(Client_Suitexls,  (this.getClass().getSimpleName()) );
	}	
	

	
}	
	
	
	
	
	
	
	
