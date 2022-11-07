package nsf.ecap.Client_Suite;

import java.util.Date;

import nsf.ecap.MonitorPlan_Suite.TestSuiteBase_MonitorPlan;
import nsf.ecap.TraQtion_Suite.TestSuiteBase_TraQtion;
import nsf.ecap.util.ErrorUtil;
import nsf.ecap.util.TestUtil;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;

public class Run_Algorithm extends TestSuiteBase_CLNT{
	
	public boolean IsBrowserPresentAlready = false;
	public String Errormsg;
	public Integer RowCountAfterUpdate;
	public Integer RowCountBeforeUpdate;
	
	
	public Integer count = -1;
	public Integer RowCount;
	public boolean skip = true;
	public String ScenarioName = null;
	public String CaseNo = null;
	public String CustomerName = null;
	public String RunDate = null;
	public String Client_22490 = null;
	public String ISR_Client_ID = null;
	
	
	public boolean LoadingImageFlag = true;
	public Integer TimeOut;
	public String PageSourceText = null;
	public String ErrorMsgText = null;
	public boolean BrowserDriver = false;
	
	public String Run_Algorithm_Xpath = "//*[contains(text(), 'Run Algorithm')]";
	public String Run_Algorithm_RUN_Date_Xpath = "//input[@id='mainForm:racalrudt_input']";
	public String Run_Algorithm_RUN_Bttn_Xpath = "//*[text()='RUN']";
	public String Run_Algorithm_RUN_Date_Data= null;
	

	
	@Parameters({ "className" })
	@BeforeTest
		public void checkTestSkip(String className) throws Exception {
		
		isTestCasePass=true;
		
		try {
			if (className.isEmpty()) {
				className = this.getClass().getSimpleName();
			}
		
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
	
	
	
	
		
@Test(dataProvider = "getTestData", priority = 1   )
public void Run_Algorithm_For_(String Case_No, String Run_Date, String Customer_Name) throws Throwable{	
	
	fnsApps_Report_Logs("=========================================================================================================================================");
			
	count++;
			
	CaseNo = (Case_No.split("\\=")[1]).trim();
	Run_Algorithm_RUN_Date_Data = (Run_Date.split("\\=")[1]).trim();
	CustomerName = (Customer_Name.split("\\=")[1]).trim();
		
	try {
		
		if (!(runmodes[count].equalsIgnoreCase("Y"))) {
			fnsApps_Report_Logs("****************************************************************************************************************************************");
			fnsApps_Report_Logs("################################## Runmode of Case: "+CaseNo.trim() +" for Data ["+Run_Date+" ::: "+CustomerName+"]  is set to NO, So Skipping this Case.");
			skip=true;
			throw new SkipException("Runmode of Case: "+CaseNo.trim() +" for Data ["+Run_Date+" ::: "+CustomerName+"]  is set to NO, So Skipping this Case.");
		}else{

			skip=false;
			fnsApps_Report_Logs("****************************************************************************************************************************************");
			fnsApps_Report_Logs("################################## Execution Logs of  Case: "+CaseNo.trim() +" for Data ["+Run_Date+" ::: "+CustomerName+"].");
			
			fncLaunchBrowser_And_SecureLogin();
			
			fncRunAlgorithm_Ajax_Link_Click();
			fncLoading_Progressingwait(3);
			
			TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(Run_Algorithm_RUN_Bttn_Xpath);
			fncLoading_Progressingwait(2);
			
			TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(Run_Algorithm_RUN_Date_Xpath);
			TestSuiteBase_MonitorPlan.WithOut_OR_fnType(Run_Algorithm_RUN_Date_Xpath, Run_Algorithm_RUN_Date_Data);
			
			TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(Run_Algorithm_RUN_Bttn_Xpath);
			TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(Run_Algorithm_RUN_Bttn_Xpath);
			
			fncLoading_Progressingwait(3);
			fnsApps_Report_Logs("PASSED == Successfully Run Algorithm for Date = "+Run_Algorithm_RUN_Date_Data);
				
			
		}
	}catch(SkipException sk){
			throw new SkipException(sk.getMessage());
			
		
	}catch(Throwable t){
		fail = true;
		isTestCasePass = false;
		fnsApps_Report_Logs(" "+t.getMessage());
		throw new Exception(" "+t.getMessage());
	}						
	
}

















//##################################################################################################################################################################################################
//###########################################################       Class methods     ##############################################################################################################	
	
	//Clicking on Search Client Ajax link.
		public void fncRunAlgorithm_Ajax_Link_Click() throws Exception {
			try{
				TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(2);
				fnsGet_Element_Enabled("Menu_Ajax_Link");
				WebElement Menu_Element = fnsGet_OR_CLNT_ObjectX("Menu_Ajax_Link");
				
				
				//New line added to run script in chrome.
				WebElement VersionLogoImage = fnsGet_OR_CLNT_ObjectX("VersionLogoImage");
				VersionLogoImage.click();
				Thread.sleep(500);
				
				Actions action1 = new Actions(driver);
				action1.moveToElement(Menu_Element).build().perform();
						
				Thread.sleep(500);		
			
				Actions action2 = new Actions(driver);
				TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_Element_Enabled(Run_Algorithm_Xpath);
				WebElement RunAlgo = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(Run_Algorithm_Xpath);
				action2.moveToElement(RunAlgo).click().build().perform();
				fnsApps_Report_Logs("PASSED == Successfully Click on 'Run Algorithm' Ajax Link.");							
			}catch(NoSuchWindowException W){
				isTestCasePass = false;
				throw new Exception(W.getMessage());			}
			catch(Throwable t) {
				isTestCasePass = false;
				fnsTake_Screen_Shot("Search_Client_Ajax");
				fnsApps_Report_Logs("FAILED == Clicking on Run Algorithm Ajex Link Failed, plz see screenshot [ " + "Search_Client_Ajax" + fnsScreenShot_Date_format() + "]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(t));
				throw new Exception("FAILED == Clicking on Run Algorithm Link Failed , plz see screenshot [ " + "Search_Client_Ajax" + fnsScreenShot_Date_format() + "]"+". Getting Exception  >> "+Throwables.getStackTraceAsString(t));}
}
		
		
		
		
		
		
		
		
		public void fncLoading_Progressingwait(int waitcount) throws Throwable{
			try{
				TestSuiteBase_TraQtion TestSuiteBase_TraQtion = new TestSuiteBase_TraQtion();
				LoadingImageFlag = true;
				TimeOut = 0;
				for(int wait=2; wait<=((Long.parseLong(CONFIG.getProperty("Element_MAX_WaitTime")))*60); wait++){
					if(LoadingImageFlag){
						Thread.sleep(1000);
					//	System.out.println("====================Enter Thread"+wait);
					}
					if(driver.findElements(By.xpath(OR_TraQtion.getProperty("IPulse_Loading"))).size()>0){
						Thread.sleep(1000);
						LoadingImageFlag = false;
					//	System.out.println("====================Enter Loading"+wait);
					}
					if(((driver.findElements(By.xpath(OR_TraQtion.getProperty("IPulse_Loading"))).size())==0) && wait > waitcount){
					//	System.out.println("====================Enter wait"+wait);
						break;
					}
								
					TimeOut = wait;	
					if(TimeOut==( (Long.parseLong(CONFIG.getProperty("Element_MAX_WaitTime")))*60)){
						throw new Exception("Loading Issue : After <"+(TimeOut)+">Seconds wait page is not getting Load, please refer screenshot >>PageLoadFail1" + fnsScreenShot_Date_format());
					}		
				}
				
				PageSourceText = driver.getPageSource().toLowerCase();
				if(PageSourceText.contains("we are sorry")){
					try{
						TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element(OR_TraQtion.getProperty("IPulse_MainErrorMsg"));
					}catch(Throwable t){
						ErrorMsgText = "We are sorry...an unexpected error has occurred. Details have been forwarded to the Application Support Team for investigation. If the problem persists, please submit an issue on the Apps Portal.";
						throw new IllegalArgumentException();
					}
				
				}

				if(PageSourceText.contains("ui-messages-error-summary")){		
					if (TestSuiteBase_TraQtion.fnsGet_OR_TraQtion_ObjectX("IPulse_MainErrorMsg").isDisplayed()) {
						TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element(OR_TraQtion.getProperty("IPulse_MainErrorMsg"));
						ErrorMsgText = TestSuiteBase_TraQtion.fnsGet_OR_TraQtion_ObjectX("IPulse_MainErrorMsg").getText();
						throw new IllegalArgumentException();
					}
				}
			}catch(IllegalArgumentException i){
				isTestCasePass = false;
				TestSuiteBase_MonitorPlan.fnsTake_Screen_Shot_Without_OR("UnExpectedError");
				fnsApps_Report_Logs("Getting Un-Expected Application Error<"+ErrorMsgText+">, please refer screenshot >>UnExpectedError" + fnsScreenShot_Date_format()+"  Exception ("+i.getMessage()+")");
				throw new Exception("Getting Un-Expected Application Error<"+ErrorMsgText+">, please refer screenshot >> UnExpectedError" + fnsScreenShot_Date_format()+"  Exception ("+i.getMessage()+")");	
				
			}catch(Throwable t){
				try{	
					if(TimeOut==( (Long.parseLong(CONFIG.getProperty("Element_MAX_WaitTime")))*5)){
						throw new Exception("Loading Issue : After <"+(TimeOut)+">Seconds wait page is not getting Load, please refer screenshot >>PageLoadFail2" + fnsScreenShot_Date_format()+"  Exception ("+t.getMessage()+")");
					}else{
						TimeOut = 0;
					}
					Thread.sleep(2000);
					if(driver.findElements(By.xpath(OR_TraQtion.getProperty("IPulse_Loading"))).size()>0){
						for(int wait=1; wait<11; wait++){
							if(driver.findElements(By.xpath(OR_TraQtion.getProperty("IPulse_Loading"))).size()>0){
								Thread.sleep(1000);			}
							if(((driver.findElements(By.xpath(OR_TraQtion.getProperty("IPulse_Loading"))).size())==0)){
								break;					}
							TimeOut = wait;		
						}
						if(TimeOut==10){
							throw new Exception("Loading Issue : After <"+(((Long.parseLong(CONFIG.getProperty("Element_MAX_WaitTime")))*60)+TimeOut)+">Seconds wait page is not getting Load, please refer screenshot >>PageLoadFail3" + fnsScreenShot_Date_format()+"  Exception ("+t.getMessage()+")");
						}
					}
				
				}catch(NullPointerException N){
					fnsApps_Report_Logs("Getting loading NullPointer Exception.");
					Thread.sleep(waitcount*1000);
			
				}catch(Throwable tt){
					isTestCasePass = false;
					TestSuiteBase_MonitorPlan.fnsTake_Screen_Shot_Without_OR("PageLoadFail");
					fnsApps_Report_Logs(tt.getMessage());
					throw new Exception(tt.getMessage());		}
			}
		}

		

	

		
		
		
		
		
		// Function to Launch browser and login
		public boolean fncLaunchBrowser_And_SecureLogin() throws Throwable {
			boolean present;
			startExecutionTime = fnpTimestamp();
					
			try {
			
				if (BrowserName.equalsIgnoreCase("IE")) {
					System.setProperty("webdriver.ie.driver", CONFIG.getProperty("IEDriverPath"));

					DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
					caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
					caps.setCapability("IGNORE_ZOOM_SETTING", true);
				    caps.setCapability("IE_ENSURE_CLEAN_SESSION", true);
				   caps.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
				    			
				    driver=new InternetExplorerDriver(caps);
	  			fnsApps_Report_Logs("Browser type is IE");}
				
				if (BrowserName.equalsIgnoreCase("chrome")) {
					System.setProperty("webdriver.chrome.driver", CONFIG.getProperty("ChromeDriverPath"));
					DesiredCapabilities caps = DesiredCapabilities.chrome();
					
					ChromeOptions options = new ChromeOptions();
					options.addArguments("test-type");
					caps.setCapability(ChromeOptions.CAPABILITY, options);																																												
																																																					
					driver=new ChromeDriver(caps);				
					BrowserDriver=true;
					fnsApps_Report_Logs("Browser type is CHROME");
				}
				

				if (BrowserName.equalsIgnoreCase("firefox")) {
					driver = new FirefoxDriver();
					fnsApps_Report_Logs("Browser type is Firefox");}

				
				fncIpulse_Login_Split_Excel_Urls("secureipulse", CONFIG.getProperty("LoginAs_knagalingam"), CONFIG.getProperty("adminUsername"), CONFIG.getProperty("adminPassword"));
				
				present = true;
						
				if (!veryFirstTimeAfterLogin) {
				//	TestSuiteBase_NSFOnline.fnsIPulse_Application_Version("Grip");
					veryFirstTimeAfterLogin=true;
				}
				
				if(ApplicationVersion_Flag){
				/*	TestSuiteBase_NSFOnline.fnsIPulse_Application_Version("Grip");*/
					ApplicationVersion_Flag = false;
				}
			
			} catch(NoSuchWindowException W){
				isTestCasePass = false;
				throw new Exception(W.getMessage());			}
			catch (Throwable t) {
				isTestCasePass = false;
				fnsTake_Screen_Shot("LaunchBrowserAndLoginFail");
				present = false;
				fnsApps_Report_Logs("");
				fnsApps_Report_Logs("'Launch Browser And Secure Login' is getting fail, Please refer screen shot [LaunchBrowserAndLoginFail"+fnsScreenShot_Date_format()+"]."+" Getting Exception >> "+Throwables.getStackTraceAsString(t));
				throw new Exception("'Launch Browser And Secure Login' is getting fail, Please refer screen shot [LaunchBrowserAndLoginFail"+fnsScreenShot_Date_format()+"]."+" Getting Exception >> "+Throwables.getStackTraceAsString(t));		}
		
			return present;
	}
		
	

	
		public void fncIpulse_Login_Split_Excel_Urls(String PortalNamne, String LoginAs, String userName, String password) throws Throwable{
			
			try{
				start = new Date();
				
				
				String siteUrl = CONFIG.getProperty("SecuretestSiteName");	
				
				
				driver.get(siteUrl);
				  
				
				TestSuiteBase_MonitorPlan.fnsComman_BrowserLogin_ElementWait(OR_MtrPlan.getProperty("UserName"), CONFIG.getProperty("Element_MAX_WaitTime"));
				
				if ( !(LoginAs==null) ){
					if( (PortalNamne.toLowerCase().trim()).equalsIgnoreCase("secureipulse")){
						TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(OR_MtrPlan.getProperty("LoginAs")).clear();
						TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(OR_MtrPlan.getProperty("LoginAs")).sendKeys("");
						TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(OR_MtrPlan.getProperty("LoginAs")).sendKeys(LoginAs);
					}	
				}
				
				TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(OR_MtrPlan.getProperty("UserName")).clear();
				TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(OR_MtrPlan.getProperty("UserName")).sendKeys("");
				TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(OR_MtrPlan.getProperty("UserName")).sendKeys(userName);
				TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(OR_MtrPlan.getProperty("password")).sendKeys("");
				TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(OR_MtrPlan.getProperty("password")).sendKeys(password);
				TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(OR_MtrPlan.getProperty("Login")).click();
				
				TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(10);
				Thread.sleep(1000);
				
				for(int i=0; i<=Long.parseLong(CONFIG.getProperty("Element_MAX_WaitTime")); i++){
				
					if(i==Long.parseLong(CONFIG.getProperty("Element_MAX_WaitTime"))){
						throw new Exception("After Login, Home Page is not getting load after <"+Long.parseLong(CONFIG.getProperty("Element_MAX_WaitTime"))+">seconds wait.");
					}else{
						Thread.sleep(1000);
					}
					
					if(driver.findElements(By.xpath(OR_MtrPlan.getProperty("Login_errorMessage"))).size()>0){
						String ErrorMsg = TestSuiteBase_MonitorPlan.WithOut_OR_fnGet_ObjectX(OR_MtrPlan.getProperty("Login_errorMessage")).getText();
						throw new Exception("I-pulse Login Failed, Application Error <"+ErrorMsg+">");
					}else
					
					/*//if(driver.findElements(By.xpath(OR_MtrPlan.getProperty("logOutBtn"))).size()>0)*/{
						TestSuiteBase_MonitorPlan.fnsComman_BrowserLogin_ElementWait(OR_MtrPlan.getProperty("logOutBtn"), CONFIG.getProperty("Element_MAX_WaitTime"));
						break;
					}
					
				}
			}catch (Throwable t) {
				throw new Exception(Throwables.getStackTraceAsString(t));
			}
	}
	
	
	
	
	
	
	
//##################################################################################################################################################################################################
//###########################################################       Configuration methods     #####################################################################################################	
	
	
	
	
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



@AfterMethod
public void QuitBrowser(){
	//TestSuiteBase_NSFOnline.MoveMouseCursorToTaskBar();
	if(skip){
		//Nothing to do.
	}else{
		driver.quit();
	}
}

	

@DataProvider
public Object[][] getTestData() {
	return getExcelData_for_DataProvider(Client_Suitexls, this.getClass().getSimpleName());
}
	
	
}