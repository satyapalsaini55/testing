/*package nsf.ecap.EPSF_suite;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import nsf.ecap.Work_Order_suite.TestSuiteBase;
import nsf.ecap.util.ErrorUtil;
import nsf.ecap.util.TestUtil;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.os.WindowsUtils;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.google.common.base.Throwables;

public class TestSuiteBase_EPSF  extends TestSuiteBase {
	public static EPSF_Draft_Req_Recvd EPSF_BS_01;
	public static String runmodes[] = null;
	public static int count = -1;
//	public static boolean fail = false;
//	public static boolean skip = false;
//	public static boolean isTestPass = true;







	// check if the suite has to be skipped or not
	@BeforeSuite
	public void checkSuiteSkip() throws Throwable {

		fnInitialize();

		//currentSuiteName = "EPSF_suite";
		setCurrentSuiteName("EPSF_suite");
		fnpMymsg("             ");
		fnpMymsg("             ");
		fnpMymsg("=========================================================================================================================================");
		fnpMymsg("####################"+currentSuiteName +" Start ############################################################");
		fnpMymsg("Checking Runmode of "+currentSuiteName  );
		if (!TestUtil.isSuiteRunnable(suiteXls, currentSuiteName)) {
			fnpMymsg("Skipped "+currentSuiteName +"  as the runmode was set to NO");
			fnpMymsg("####################  "+currentSuiteName +"  End ############################################################");
			fnpMymsg("=========================================================================================================================================");
			throw new SkipException("Runmode of EPSF_suite set to no. So Skipping all tests in "+currentSuiteName );
		}

		if (TestUtil.isSuiteRunnable(suiteXls, currentSuiteName)) {
			browserName = TestUtil.getBrowserName(suiteXls, currentSuiteName);
			excelSiteURL= TestUtil.getExcelSiteURL(suiteXls, currentSuiteName);
			fnpMymsg("Browser Name is :" + browserName);
			fnpMymsg("======================");
			currentSuiteCode = "EPSF";

			int rowNum = suiteXls.getCellRowNum("Test Suite", "TSID", currentSuiteName);
			suiteXls.setCellData("Test Suite", "Last_Execution", rowNum, fnReturnDateWithTimeForExcel());

			currentSuiteXls=EPSF_suitexls;
		}

	}




	*//***********Function to Launch the browser and login with user credential details*************//*
	public static boolean fnpLaunchBrowserAndLogin() throws Exception {
		boolean present;
		try {

			killprocess();
			
			fnpLaunchBrowser();

			String userName = CONFIG.getProperty("adminUsername");
			String password = CONFIG.getProperty("adminPassword");

			// String siteUrl = CONFIG.getProperty("testSiteName");

			String siteUrl = null;

			if (excelSiteURL!=null) {
				if (excelSiteURL.equalsIgnoreCase("")) {
					siteUrl = CONFIG.getProperty("testSiteName");
				} else {
				//	siteUrl=excelSiteURL;
					String epsfUrl = excelSiteURL.split("LoginAsTestSiteName:")[0];									
					siteUrl=epsfUrl;

				}
			} else {
				siteUrl = CONFIG.getProperty("testSiteName");
			}
			

			

			
			
			
			
			
			
			driver.get(siteUrl);
			fnpMymsg("Navigating to url --->" + siteUrl);
			driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(Long.parseLong(CONFIG.getProperty("pageLoadTime")), TimeUnit.SECONDS);

			WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(CONFIG.getProperty("genMax_waitTime")));
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("UserName"))));

			fnpWaitTillVisiblityOfElementNClickable_OR("UserName");
		//	fnpGetORObjectX("UserName").clear();

						
			fnpGetORObjectX("UserName").sendKeys("");
			fnpGetORObjectX("UserName").sendKeys(userName);

			//Thread.sleep(1000);

			fnpGetORObjectX("password").sendKeys("");
			fnpGetORObjectX("password").sendKeys(password);
			
			//Thread.sleep(500);
			fnpMymsg("Just before login clicked");
			//fnpGetORObjectX("Login").click();
			fnpClick_OR("Login");
			fnpMymsg("Just login clicked");
		//	fnpLoading_wait();
			fnpMymsg("Just after loading wait after login clicked");
			//errorMessage
			if (fnpCheckElementDisplayedImmediately("errorMessage")) {
				throw new Exception("Login is not successfull.");
			}
			

			
			 * WebDriverWait wait3 = new WebDriverWait(driver, Integer.parseInt(CONFIG.getProperty("genMax_waitTime"))); WebElement element3 =
			 * wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("logOutBtn")))); WebElement logOutBtn =
			 * fnpGetORObjectX("logOutBtn"); Assert.assertEquals(true, logOutBtn.isDisplayed());
			 

			fnpWaitForVisible("logOutBtn");
			fnpWaitTillVisiblityOfElementNClickable_OR("logOutBtn");
			fnpWaitTillVisiblityOfElementNClickable_OR("ShowAlertsBtn");
			fnpWaitTillVisiblityOfElementNClickable_OR("FooterElement");
			
			

			present = true;


			if (!veryFirstTimeAfterLogin) {
				fnpFetchApplicationVersion();
				veryFirstTimeAfterLogin = true;
			}
			
			

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
			errorMsg = errorMsg + "\n\n\n\n Login failed.See screenshot 'LoginFailed' \n\n" + stackTrace;
			Exception c = new Exception(errorMsg);
			ErrorUtil.addVerificationFailure(c);
			
			
			killprocess();
			IsBrowserPresentAlready = false;
			
			throw new Exception(errorMsg);// reports
					
			

		}

		return present;
	}







	// To remove IEDriver.exe from Windows Task Manager
	// @AfterSuite(alwaysRun=true)
	@AfterSuite
	public void cleanUp() {
		try {
			referenceSuite=currentSuiteName;
			fnpMymsg("#################### Suite End ############################################################");
			driver.quit();
			IsBrowserPresentAlready = false;
			killprocess();
		}
		catch (Throwable t) {
			// Nothing to do
		}

	}


	
	

	public static String fnpremoveFormatting(
												String s) {

		s = s.replace("[", " ");
		s = s.replace("]", " ");
	//	s = s.replace("(", " ");
	//	s = s.replace(")", " ");
		s = s.trim();

		return s;

	}

	
	
	
	
	
	
	public static void fnpCommonCodeEPSFCreation(Hashtable <String,String> table) throws Throwable{
		try{
		//fnpCommonClickTopMainMenu("Menu","FirstRefrenceLinkJustBelowToMenu","CreateEPSF");
		fnpCommonClickTopMainMenu("Menu","FirstRefrenceLinkJustBelowToMenu","CreateEPSF","EPSFReferenceNoTxtBx");
		
		
		fnpType("OR", "EPSFReferenceNoTxtBx", (String) table.get("ReferenceNo"));
		
		
		String finProgram=((String) table.get("Financial_Programs")).trim();
		fnpPFList("EPSF_FinancialProgramList", "EPSF_FinancialProgramListOptions", finProgram);	
		fnpLoading_wait();
		
		fnpMymsg(" Facility No is to be select from client lookup is ---"+table.get("FacilityNo"));
		fnpClick_OR("EPSFFacilityNoLkp");
		fnpSearchNSelectFirstRadioBtn(1, (String) table.get("FacilityNo"), 1);
		String enteredFacilityNo = fnpWaitTillTextBoxDontHaveValue("EPSFFacilityNoTxtBx", "value");
		Assert.assertTrue(enteredFacilityNo.contains((String) table.get("FacilityNo")), "Facility No is not selected properly from lookup");
		fnpMymsg(" Facility No is properly selected from client lookup---"+enteredFacilityNo);
		
		
		
		String expCollType=((String) table.get("Collection_Type")).trim();
		fnpPFList("EPSF_CollectionTypeList", "EPSF_CollectionTypeListOptions", expCollType);
		
		
		if (expCollType.toLowerCase().contains("retest")) {			
			Thread.sleep(3000);				
		}
		
		Thread.sleep(3000);
		fnpPFList("EPSF_TestCategoryList", "EPSF_TestCategoryListOptions", (String) table.get("Test_Category"));
		
		fnpPFList("EPSF_StandardList", "EPSF_StandardListOptions", (String) table.get("Standard"));
		
		
		fnpPFList("EPSF_TestLocationList", "EPSF_TestLocationListOptions", (String) table.get("Test_Location"));
		
		fnpPFList("EPSF_ShipToLocationList", "EPSF_ShipToLocationListOptions", (String) table.get("Ship_to_Location"));
		
		
		
		
		
		if (expCollType.toLowerCase().contains("retest")) {			
			fnpGetORObjectX("EPSF_RetestOfEPSF").sendKeys((String) table.get("RetestAfterEPSF"));			
		}
		
		
		
		
		fnpClick_OR("EPSF_CreateNextBtn");
		
		
		String epsfMandatoryFieldsWithValues=(String) table.get("EPSF_MandatoryFields_With_Values");
		
		
		String mandFieldsSet[] = epsfMandatoryFieldsWithValues.split("::");
		fnpMymsg("No. of mandatory fields are ---" + mandFieldsSet.length);
		fnpMymsg("");
		fnpMymsg("");

	       *//************Assumption all these dyanamic fields are text boxes , not drop down or any other type ***************//*
			for (int i = 0; i < mandFieldsSet.length; i++) {
				fnpMymsg("");
				fnpMymsg("");
				fnpMymsg("Each Field and its value set is--" + mandFieldsSet[i]);
				String fieldValue[] = mandFieldsSet[i].split(",");
				//String fieldName = TestSuiteBase_Proposal.fnpremoveFormatting(fieldValue[0]);
				String fieldName = fnpremoveFormatting(fieldValue[0]);
				fnpMymsg("fieldName is--" + fieldName);

				//String valueToEnteredInField = TestSuiteBase_Proposal.fnpremoveFormatting(fieldValue[1]);
				String valueToEnteredInField =fnpremoveFormatting(fieldValue[1]);
				fnpMymsg("Value to be insert in this field is--" + valueToEnteredInField);

				String completeXpathForTxtBox=OR.getProperty("EPSF_DynamicTxtBox_Part1")+fieldName+OR.getProperty("EPSF_DynamicTxtBox_Part2");
				
				fnpMymsg("completeXpathForTxtBox is--" + completeXpathForTxtBox);
				
				String defaultValue=fnpGetORObjectX___NOR(completeXpathForTxtBox).getAttribute("value");
				if (defaultValue.trim().isEmpty()| (!defaultValue.trim().equalsIgnoreCase(valueToEnteredInField))) {
					fnpGetORObjectX___NOR(completeXpathForTxtBox).clear();
					fnpGetORObjectX___NOR(completeXpathForTxtBox).sendKeys(valueToEnteredInField);
				} else {
					//leave it as it is
				}
				
				

				Thread.sleep(1000);

			}
			 
		fnpClick_OR("EPSF_CreateNextBtn");//Click on Next for EPSF fields tab.
		fnpClick_OR("EPSF_CreateNextBtn");//Click on Next for EPSF Info tab.
		
		if (classNameText.equalsIgnoreCase("EPSF_Complete_Flow")) {
			
			fnpWaitForVisible("EPSFAddSampleBtn");
			fnpMymsg("Add Sample button is visible.");
			//fnpWaitTillVisiblityOfElementNClickable_OR("CreateEPSFFirstBtn");//this button is not present on this screen now
			
			
			*//******Currently it is not working , so will uncomment later *******************//*
			String varDisabledForCreateEPSFBtn = fnpGetAttribute_OR("CreateEPSFFirstBtn", "disabled");
			fnpMymsg("Value of attribute disabled for this 'Create EPSF' button is ---"+varDisabledForCreateEPSFBtn);
			Assert.assertEquals(varDisabledForCreateEPSFBtn, "true", " 'Create EPSF' button should be disable at this point of time but it is enabled now.");
			fnpMymsg("'Create EPSF' button is disabled now"); 
			*//******Currently it is not working , so will uncomment later *******************//*
			
			
			
			
			fnpClick_OR("EPSFAddSampleBtn");
			
			fnpGetORObjectX("EPSFSampleNameTxtBx").sendKeys((String) table.get("Sample_Name"));
			fnpGetORObjectX("EPSFSampleDescriptionTxtBx").sendKeys((String) table.get("Sample_Description"));			
			fnpClick_OR("EPSFAddSampleBtnInDialog");
			
			fnpWaitForVisible("EPSFAddTaskByTemplateBtn");
			fnpMymsg("Add Task By Template button is visible.");
			fnpWaitForVisible("EPSFAddTaskByTestCodeBtn");
			fnpMymsg("Add Task By Test Code button is visible.");
			
			//  String varClass=fnpGetAttribute_OR("EPSFAddTaskByTemplateBtn", "class");
			//  fnpMymsg("Pradeep---before click Template---class ----"+varClass);
			//  String varDisabled=fnpGetAttribute_OR("EPSFAddTaskByTemplateBtn", "disabled");
			//  fnpMymsg("Pradeep---before click Template---diabled ----"+varDisabled);
			  
			
			String varDisabledForTemplateBtn=fnpGetAttribute_OR("EPSFAddTaskByTemplateBtn", "disabled");
			Assert.assertEquals(varDisabledForTemplateBtn, null, " Add Task By Template button should be enable at this point of time but it is disabled now.");
			String varDisabledForTestCodeBtn=fnpGetAttribute_OR("EPSFAddTaskByTestCodeBtn", "disabled");
			Assert.assertEquals(varDisabledForTestCodeBtn, null, " Add Task By Test Code button should be enable at this point of time but it is disabled now.");
			  
			fnpMymsg("Now going to add a task by using 'Add Task By Template' button.");
			fnpClick_OR("EPSFAddTaskByTemplateBtn");
			fnpWaitTillClickable("FirstRadioBtnInSampleAdded"); 
			fnpClick_OR_WithoutWait("FirstRadioBtnInSampleAdded");
			fnpClick_OR("EPSFSearchAndReturnBtn");
			fnpWaitForVisible("EPSFDeleteTaskBtn");
			fnpMymsg("Task has been added  by using 'Add Task By Template' button.");
			
			
			varDisabledForTemplateBtn=fnpGetAttribute_OR("EPSFAddTaskByTemplateBtn", "disabled");
			fnpMymsg("Value of attribute disabled for this 'Add Task By Template' button is ---"+varDisabledForTemplateBtn);
			Assert.assertEquals(varDisabledForTemplateBtn, "true", " 'Add Task By Template' button should be disable at this point of time but it is enabled now.");
			fnpMymsg("'Add Task By Template' button is disabled now"); 
			
			varDisabledForTestCodeBtn=fnpGetAttribute_OR("EPSFAddTaskByTestCodeBtn", "disabled");
			fnpMymsg("Value of attribute disabled for this 'Add Task By Test Code' button is ---"+varDisabledForTestCodeBtn);
			Assert.assertEquals(varDisabledForTestCodeBtn, "true", " 'Add Task By Test Code' button should be disable at this point of time but it is enabled now.");
			fnpMymsg("'Add Task By Test Code' button is disabled now"); 
			
			
			fnpMymsg("Now going to delete above added task by using 'EPSFDeleteTaskBtn' button.");
			fnpClick_OR_WithoutWait("EPSFDeleteTaskBtn");
			fnpClick_OR("EPSFConfirmDelBtn");
			
			varDisabledForTemplateBtn=fnpGetAttribute_OR("EPSFAddTaskByTemplateBtn", "disabled");
			Assert.assertEquals(varDisabledForTemplateBtn, null, " Add Task By Template button should be enable at this point of time but it is disabled now.");
			fnpMymsg("'Add Task By Test Code' button is again enabled  now"); 
			varDisabledForTestCodeBtn=fnpGetAttribute_OR("EPSFAddTaskByTestCodeBtn", "disabled");
			Assert.assertEquals(varDisabledForTestCodeBtn, null, " Add Task By Test Code button should be enable at this point of time but it is disabled now.");
			fnpMymsg("'Add Task By Test Code' button is again enabled  now"); 
			
			
			
			
			fnpMymsg("Now going to add a task by using 'Add Task By Test Code' button.");
			fnpClick_OR("EPSFAddTaskByTestCodeBtn");
			fnpWaitTillClickable("FirstChkBxInSampleAdded"); 
			fnpClick_OR_WithoutWait("FirstChkBxInSampleAdded");
			fnpClick_OR("EPSFSearchAndReturnBtn2InSearchByTestCode");
			fnpWaitForVisible("EPSFDeleteTaskBtn");
			fnpMymsg("Task has been added  by using 'Add Task By Test Code' button.");
			
			varDisabledForTemplateBtn=fnpGetAttribute_OR("EPSFAddTaskByTemplateBtn", "disabled");
			fnpMymsg("Value of attribute disabled for this 'Add Task By Template' button is ---"+varDisabledForTemplateBtn);
			Assert.assertEquals(varDisabledForTemplateBtn, "true", " 'Add Task By Template' button should be disable at this point of time but it is enabled now.");
			fnpMymsg("'Add Task By Template' button is disabled now"); 
			varDisabledForTestCodeBtn=fnpGetAttribute_OR("EPSFAddTaskByTestCodeBtn", "disabled");
			Assert.assertEquals(varDisabledForTestCodeBtn, null, " Add Task By Test Code button should be enable at this point of time but it is disabled now.");
			fnpMymsg("Value of attribute disabled for this 'Add Task By Test Code' button is ---"+varDisabledForTestCodeBtn);
			fnpMymsg("'Add Task By Test Code' button is enabled now"); 
			fnpMymsg("'Add Task By Test Code' button is again enabled  now means we can add another task using 'Add Task By Test Code' button."); 
			fnpMymsg("It means multiple code can be added .");
			
		//	fnpMymsg("The End Pradeep");
			  
			  
			
			
		} else {

		}
		
		
		fnpClick_OR("EPSF_CreateNextBtn");//Click on Next for EPSF Tasks tab.
		
		fnpClick_OR("CreateEPSFFirstBtn");
		
	//	fnpClick_OR("EPSF_SaveBtn");
		
		String actual_epsfStatus=fnpGetText_OR("EPSF_Status");
		
		Assert.assertEquals(actual_epsfStatus,"DRAFT", "EPSF is not created in DRAFT Status");
		String EPSF_No=fnpGetText_OR("EPSF_No");
		fnpMymsg("EPSF is created in DRAFT status and its no. is---"+EPSF_No);
		
		
		if (expCollType.toLowerCase().contains("retest")) {			
			fnpGetORObjectX("RetestAfterEPSFTxt").sendKeys((String) table.get("RetestAfterEPSF"));
			fnpClick_OR("EPSF_SaveBtn");	
			fnpWaitForVisible("TopMessage3");
			
		}
		
		
		
		}catch(Throwable t){
			fnpDesireScreenshot("epsfCreation_failed");
			String stackTrace = Throwables.getStackTraceAsString(t);
			String errorMsg = t.getMessage();
			errorMsg = errorMsg + "\n\nfailed in fnpCommonCodeEPSFCreation ,plz see screenshot 'epsfCreation_failed'.\n\n" + stackTrace;
			throw new Exception(errorMsg);
		}
		
	}
	
	
	
	
	public static void fnpCommonClickEPSFIssuesTab() throws Throwable {
		
		fnpClick_OR("EPSF_IssuesTab");
		//fnpClick_OR_WithoutWait("EPSF_IssuesTab");

		//Thread.sleep(8000);
	//	fnpWaitForVisible("SampleReviewRequiredPFList");
		fnpWaitForVisible("SampleReviewRequiredLabel");
		
	}
	
	public static void fnpCommonClickEPSFInfoTab() throws Throwable {

		
		fnpClick_OR("EPSF_InfoTab");
		//fnpClick_OR_WithoutWait("EPSF_InfoTab");

		fnpWaitForVisible("EPSFStatusPFList");
		//Thread.sleep(8000);

	}

	
	public static void fnpCommonEPSFStatusChangedPFList(Hashtable table,String statusValue) throws Throwable{
		
		try {
			*//***** b/c if we want to select Reviewed then if Pre Reviewed is present before in list then it selects it  ****//*
			//fnpPFList("EPSFStatusPFList", "EPSFStatusPFListOptions", statusValue);  
			fnpPFListExactly("EPSFStatusPFList", "EPSFStatusPFListOptions", statusValue);
			
			fnpClick_OR("EPSF_SaveBtn");			
			String current_epsfStatus=fnpGetText_OR("EPSF_Status");
			String current_epsfStatusListValue=fnpGetText_OR("EPSFStatusPFList");
			Assert.assertTrue(current_epsfStatus.equalsIgnoreCase(statusValue), "EPSF status not changed to '"+
					statusValue+"'.");
			Assert.assertTrue(current_epsfStatus.equalsIgnoreCase(current_epsfStatusListValue), " EPSF status are not same " +
					"at both places i.e. at top and in status list");
			fnpMymsg("EPSF status  changed to '"+statusValue+"'.");
		
		
		} catch (Throwable t) {
			fnpDesireScreenshot("fnpCommonEPSFStatusChangedPFList_failed");
			String stackTrace = Throwables.getStackTraceAsString(t);
			String errorMsg = t.getMessage();
			errorMsg = errorMsg + "\n\nfailed in fnpCommonEPSFStatusChangedPFList ,plz see screenshot 'fnpCommonEPSFStatusChangedPFList_failed'.\n\n" + stackTrace;
			throw new Exception(errorMsg);
		}	
		
		
		
	}
	
	
	
	
	*//***********To select the value from prime faces list that contains matching value  *************//*
	public static void fnpPFListDoubleClick(
									String XpathKey,
									String XpathOptionsKey,
									String value) throws Throwable {
		try {
			fnpWaitForVisible(XpathKey);
			// fnpWaitTillVisiblityOfElementNClickable_OR(XpathKey);
		//	fnpGetORObjectX(XpathKey).click();
			fnpClick_OR_WithoutWait(XpathKey);
			Thread.sleep(500);
			String listValue = OR.getProperty(XpathOptionsKey) + "/div/ul/li[contains(@data-label,'" + value + "')]";
			
			
			
			try{
			fnpWaitTillVisiblityOfElementNClickable(listValue);
		//	driver.findElement(By.xpath(listValue)).click();
			fnpClick_NOR_withoutWait(listValue);
			}catch(Exception ee){
				// giving 2nd try
				fnpClick_OR_WithoutWait(XpathKey);
				Thread.sleep(500);
				fnpWaitTillVisiblityOfElementNClickable(listValue);
				//	driver.findElement(By.xpath(listValue)).click();
				fnpClick_NOR_withoutWait(listValue);
			}
			
			
			fnpWaitTillVisiblityOfElementNClickable(listValue);
		//	driver.findElement(By.xpath(listValue)).click();
			fnpClick_NOR_withoutWait(listValue);			
			Thread.sleep(500);

			if (fnpCheckElementDisplayed_NOR(listValue)) {
				fnpMymsg("@@@@@Pradeep---Clicking list value again inside fnpCheckElementDisplayed_NOR");
				fnpClick_NOR_withoutWait(listValue);			
				Thread.sleep(500);
			}
			
			if (fnpCheckElementPresenceImmediately_NotInOR(listValue)) {
				fnpMymsg("@@@@@Pradeep---Clicking list value again 2  inside fnpCheckElementPresenceImmediately_NotInOR");
				fnpClick_NOR_withoutWait(listValue);			
				Thread.sleep(500);
			}
			
			
			
		} catch (Exception e) {
			fnpDesireScreenshot("ValueMissingInList" + value);
			String errorMsg = e.getMessage();
			throw new Exception(errorMsg + "\n\n\n   And/OR  might be Value [" + value + "] is not present in List ,plz see screenshot [ValueMissingInList" + value + SShots + "]");
		}
	}

	
	
	
	

	// Function to fetch bill rate from DB
	public static ArrayList fnpFetchEPSFFromDB(
												String epsf_id
											) throws Throwable {
		
		ArrayList<String> epsfNoList = new ArrayList<String>();

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
			
			String conString="jdbc:oracle:thin:@dbserv13vm:1521:certtest"; 
			String username="testscriptuser";
			String password="test4nsf";
			connection = DriverManager.getConnection(conString,username,password);
			Statement stmt = connection.createStatement();

			//@SuppressWarnings("SQL_PREPARED_STATEMENT_GENERATED_FROM_NONCONSTANT_STRING")
			//String query1 = "SELECT * " + " FROM oai_epsf_dtls" + " WHERE epsf_no = '" + epsf_id + "' and record_status_flg<>'D'";
			
			String query1 = String.format("SELECT * FROM oai_epsf_dtls WHERE epsf_no = '%s' and record_status_flg<>'D'", epsf_id);
			
			 String query1 =new StringBuilder("SELECT *").
			                    append(" FROM oai_epsf_dtls").
			                    append(" WHERE epsf_no = '").
			                    append(epsf_id).
			                    append("' and record_status_flg<>'D'").toString();
			
			
						
		//	String query1 = "SELECT * " + " FROM oai_epsf_dtls" + " WHERE  record_status_flg<>'D'";

			ResultSet rst = stmt.executeQuery(query1);

			while (rst.next()) {
				//epsfNo = (rst.getString("epsf_no"));
				epsfNoList.add(rst.getString("epsf_no"));
				
			}
			rst.close();

			connection.close();

		} catch (SQLException e) {
			fnpMymsg("*********************************************** Connection Failed! with Database,   Getting Error >>  " + e.getMessage().trim());
			fnpMymsg("=========================================================================================================================================");
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		fnpMymsg("******************");

		return epsfNoList;
	}

	
	
	
	public void fnpClickAutoLoginToBeakerBtn() throws Throwable{
	//	Thread.sleep(15000);
		Thread.sleep(5000);
		fnpClick_OR("EPSF_AutoLoginToBeakerBtn");
		
		if (fnpCheckElementPresenceImmediately("TopMessage3")) {
			//nothing to do			
		} else {
			//else click it once again
			fnpClick_OR("EPSF_AutoLoginToBeakerBtn");
		}
		
	}
	
	
	
	
	
	
	

	// Function to Launch the browser and login with user credential details
	public static boolean fnpLaunchBrowserAndLogin(String loginAs,String siteUrl,String userName,String password) throws Exception {
		boolean present;
		try {

			fnpLaunchBrowser();

			
		
			
			if (excelSiteURL!=null) {
				if (excelSiteURL.equalsIgnoreCase("")) {
					siteUrl = CONFIG.getProperty("HSSiteName");
				} else {
					siteUrl=excelSiteURL;

				}
			} else {
				siteUrl = CONFIG.getProperty("HSSiteName");
			}
			
			
			
			System.out.println("Hi Pradeep Site name is----"+siteUrl);
			
			
			
			
			driver.get(siteUrl);
			fnpMymsg("Navigating to url --->"+siteUrl);
			driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(Long.parseLong(CONFIG.getProperty("pageLoadTime")), TimeUnit.SECONDS);

			WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(CONFIG.getProperty("genMax_waitTime")));
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("UserName"))));

			fnpGetORObjectX("UserName").clear();
			fnpGetORObjectX("loginAs").clear();

			
			
		//	fnpGetORObjectX("loginAs").sendKeys(loginAs);
			fnpType("OR","loginAs",loginAs);
		//	fnpGetORObjectX("UserName").sendKeys(userName);
			fnpType("OR","UserName",userName);
			Thread.sleep(500);
		//	fnpGetORObjectX("password").sendKeys(password);
			fnpType("OR","password",password);
			Thread.sleep(500);
			fnpGetORObjectX("Login").click();

		//	fnpLoading_wait(); //not worked
			//fnpLoading_waitInsideDialogBox(); //not worked

		//	Assert.assertEquals(false, driver.findElement(By.xpath(OR.getProperty("errorMessage"))).isDisplayed());
			
			
			if (fnpCheckElementPresenceImmediately("errorMessage")) {
				fnpMouseHover("errorMessage");
				Thread.sleep(1000);
				String errmsg=fnpGetORObjectX("errorMessage").getText();
				fnpMymsg("Error while loging " +" i.e --"+errmsg);
				throw new Exception("Error while loging " +" i.e --"+errmsg);
			}
			
			
			if (fnpCheckElementDisplayedImmediately("errorMessage")) {
				throw new Exception("Login is not successfull.");
			}
			
		//	fnpLoading_wait();
		//	fnpLoading_wait();
			
			WebDriverWait wait3 = new WebDriverWait(driver, Integer.parseInt(CONFIG.getProperty("genMax_waitTime")));
			WebElement element3 = wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("logOutBtn"))));
			WebElement logOutBtn = fnpGetORObjectX("logOutBtn");
			Assert.assertEquals(true, logOutBtn.isDisplayed());
		
			
			fnpWaitForVisible("logOutBtn");
			
			present = true;
		//	fnpLoading_wait();

			
			if (!veryFirstTimeAfterLogin) {
				fnpFetchApplicationVersion();
				veryFirstTimeAfterLogin = true;
			}
			
			
			//fnpFetchApplicationVersion("HS_Application_Version");
			
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
			errorMsg = errorMsg + "\n\n\n\n Login failed.See screenshot 'LoginFailed' \n\n" + stackTrace;
			Exception c = new Exception(errorMsg);
			ErrorUtil.addVerificationFailure(c);
			throw new Exception(errorMsg);// reports

		}

		return present;
	}


	
	
	

*//****Search workd order and click Edit button ****//*
	public static void fnpSearchEPSFOnly(
												String EPSF_No) throws Throwable {


		
		fnpClickAtTopWorkAroundForClickingMenu();
		
		
	//	fnpCommonClickTopMainMenu("Menu","FirstRefrenceLinkJustBelowToMenu","SearchEPSF");
	//	fnpCommonClickTopMainMenu("Menu","SearchEPSF","SearchEPSF");
		fnpCommonClickTopMainMenu("Menu","SearchEPSF","SearchEPSF","EPSFSearchEPSFTxtBx");
		
		
		fnpWaitForVisible("FooterElement");
		fnpWaitTillVisiblityOfElementNClickable_OR("FooterElement");
		fnpMouseHover("FooterElement");
		
	//	fnpLoading_wait();
		Thread.sleep(2000);
		fnpMymsg("Just before waiting for clickable EPSFSearchEPSFTxtBx");
		fnpWaitTillVisiblityOfElementNClickable_OR("EPSFSearchEPSFTxtBx");
		fnpWaitTillVisiblityOfElementNClickable_OR("MainSearchButton");
		fnpWaitForVisible("MainSearchButton");
		
		
		
		
		
		fnpType("OR", "EPSFSearchEPSFTxtBx", EPSF_No);
		fnpClick_OR("MainSearchButton");
		//fnpMymsg("just before picking value from table");
		String s = fnpFetchFromStSearchTable(1, 1);
		int j = 0;
	//	fnpLoading_wait();
		while (s.contains("No records found") && j < 15) {
			j++;
			Thread.sleep(1000);
			s = fnpFetchFromStSearchTable(1, 1);
		}
		//fnpMymsg("just before clicking first record");
		driver.findElement(By.linkText(s)).click();
		//fnpMymsg("just after clicking first record");
		fnpLoading_wait();
		fnpClick_OR("EPSFEditBtn");

	//	fnpLoading_wait();
		fnpMymsg("Edit button has been clicked");

	}


	
	
	
	
	
	
	
	
	
	
	
	}

*/