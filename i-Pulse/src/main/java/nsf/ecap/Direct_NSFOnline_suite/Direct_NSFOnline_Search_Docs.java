package nsf.ecap.Direct_NSFOnline_suite;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.TimeUnit;

import nsf.ecap.MonitorPlan_Suite.TestSuiteBase_MonitorPlan;
import nsf.ecap.util.ErrorUtil;
import nsf.ecap.util.TestDataProvider;
import nsf.ecap.util.TestUtil;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;

public class Direct_NSFOnline_Search_Docs extends
		TestSuiteBase_Direct_NSFOnline {

	/* public static boolean fail = false; public static boolean skip = true; */
	// public static boolean isTestPass = true;

	// public static boolean NSFOnlineVersionScreenshot = true;

	public static String Errormsg;
	public static String NSFOnlineoriginalHandle;
	// public static String MainWindowHandle;
	public static String CustomerName;

	public static String TextFetch = null;
	public static String CaseSerialNo = null;

	public static int count = -1;
	public static Integer tabsCount;
	public static Integer RowCount;

	public static String CustomerName_Verification = null;

	// public static String UserName = null;
	// public static String Insight_SearchUserName = null;
	// public static String InsightoriginalHandle = null;

	@Parameters({ "className" })
	@BeforeTest
	public void checkTestSkip(String className) throws Exception {

		// isTestPass=true;
		count = -1;

		try {
			if (className.isEmpty()) {
				className = this.getClass().getSimpleName();
			}

			classNameText = className;
			currentScriptCode = fnpMappingClassNameWithScenarioCode(classNameText).trim();
			NSFOnlineVersionScreenshot = true;

			if (!TestUtil.isTestCaseRunnable(Direct_NSFOnline_suitexls, className)) {
				fnpMymsg("Skipping Test Case " + className + " as runmode set to NO");
				throw new SkipException("Skipping Test Case " + className + " as runmode set to NO");
			}

			fail = false;
			isTestPass = true;

			runmodes = TestUtil.getDataSetRunmodes(Direct_NSFOnline_suitexls, className);

			fnpMymsg("=========================================================================================================================================");

		} catch (SkipException sk) {
			String stackTrace = Throwables.getStackTraceAsString(sk);
			String errorMsg = sk.getMessage();
			Exception c = new Exception(errorMsg);
			ErrorUtil.addVerificationFailure(c);
			throw new SkipException(errorMsg + " \n\nSo,Skipping Test Case" + className);
		} catch (Throwable t) {
			fnpDesireScreenshot(className);
			String stackTrace = Throwables.getStackTraceAsString(t);
			String errorMsg = t.getMessage();
			errorMsg = errorMsg + "\n\n\n\nSee screenshot '" + className + "'\n\n" + stackTrace;
			Exception c = new Exception(errorMsg);
			ErrorUtil.addVerificationFailure(c);
			throw new Exception(errorMsg);
		}
	}

	@Test(dataProviderClass = TestDataProvider.class, dataProvider = "Direct_NSFOnline_Search_Docs")
	public void Direct_NSFOnline_Search_Docs(Hashtable<String, String> table) throws Throwable {
		currentClassNameInSimpleText = this.getClass().getSimpleName();
		count++;
		String UserName = table.get("UserName").split("=")[1].trim();

		CustomerName = table.get("ClientName").toLowerCase().trim();

		CaseSerialNo = table.get("Serial_No").split("=")[1];

		if (!runmodes[count].equalsIgnoreCase("Y")) {
			skip = true;
			fnpMymsg("****************************************************************************************************************************************");
			fnpMymsg("################################## Runmode of Case::" + (count + 1) + " for Customer[" + UserName + "]  is set to NO, So Skipping this Case.");
			fnpMymsg("*****************");
			fnpMymsg("");
			throw new SkipException("Runmode of Case::" + (count + 1) + " for Customer[" + UserName + "]  is set to NO, So Skipping this Case.");
		} else {
			skip = false;
			fnpMymsg("****************************************************************************************************************************************");
			fnpMymsg("################################## Execution Logs of Case::" + (count + 1) + " for Customer_No[" + UserName + "].");

		}

		try {

			String loginPassword = table.get("Password").split("=")[1].trim();
			CustomerName_Verification = table.get("CustomerName_Verification").split("=")[1].trim();

			hashXlData = new HashMap(table);

			fnpLaunchBrowserAndLoginDirectNSFOnline(UserName, loginPassword);

			
			
/*			try{
				if( UserName.contains("C0196359") || UserName.contains("C0180508") ){	
					String updatedUserName=UserName;	
					if (UserName.contains("admin")) {
							updatedUserName=UserName.replace("admin", "").trim();														
						}			
					fnpNsfOnline_Open_Link_Clik_Through_JS(updatedUserName);					
				}
			}catch(Throwable t){
				fnpDesireScreenshot("OpenLinkClickFail");
				throw new Exception("FAILED == Unable to click on open link, plz refer screenshot [OpenLinkClickFail].  Getting Exception >> "+t.getMessage());	
			}
		
		*/	

			if (!(fnpCheckElementDisplayedImmediately("NSFOnline_CustomerTopMenu"))) {
				String updatedUserName=UserName;
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				int whileLoopCount = 0;
				while (true) {
					whileLoopCount++;
					try {
						if (UserName.contains("admin")) {
							updatedUserName=UserName.replace("admin", "").trim().toUpperCase();														
						}
						fnpMymsg("@Pradeep---in try block for clicking customer--" + UserName + " for chance --" + whileLoopCount);
						jse = (JavascriptExecutor) driver;
						WebElement openLink = driver.findElement(By.xpath(".//tr/td[text()='" + updatedUserName + "']/../td[1]/a"));
						jse.executeScript("arguments[0].click();", openLink);				
						fnpMymsg("@Pradeep---clicked successfully customer --" + UserName);
						break;
					} catch (Throwable t) {
						fnpMymsg("@Pradeep---in catch block for clicking customer--" + UserName + " for chance --" + whileLoopCount);
						// if (whileLoopCount
						// >Integer.parseInt(CONFIG.getProperty("genMax_waitTime")))
						// {
						//if (whileLoopCount > 5) {
						if (whileLoopCount > 3) {
							throw new Exception(t);
							// break;
						}
						Thread.sleep(1000);
					}

				}

				Thread.sleep(10000);
			}
			
			
			
			
			
			
			NSFOnlineoriginalHandle = driver.getWindowHandle();

			/* if (NSFOnlineVersionScreenshot) {
			 * fnpGetDirectNSFOnineVersion(currentClassNameInSimpleText + "_" +
			 * UserName); } */
			if (NSFOnlineVersionScreenshot) {
				int iwhileCounter = 0;
				while (true) {
					iwhileCounter++;
					try {
						Thread.sleep(5000);
						fnpGetDirectNSFOnineVersion(currentClassNameInSimpleText + "_" + UserName);
						break;
					} catch (Throwable te) {
					//	if (iwhileCounter < 5) {
						if (iwhileCounter < 3) {
							fnpLaunchBrowserAndLoginDirectNSFOnline(UserName, loginPassword);
						} else {
							//throw new Exception(te.getStackTrace());
							throw new Exception(te.getMessage());
						}

					}
				}
			}

			String NoOfSets[] = table.get("Search_DocumentsNos").split(":");
			fnpMymsg("Sets data are [" + table.get("Search_DocumentsNos") + "]. and No. of DocumentSets are == " + NoOfSets.length);

			for (int j = 0; j < NoOfSets.length; j++) {

				String DocumentSet = NoOfSets[j];
				fnpMymsg("***************** Current searching DocumentSet  is [" + DocumentSet + "]  ******************");

				String CurrentSet[] = DocumentSet.split(",");

				String DocumentNo = fnsRemoveFormatting(CurrentSet[0]);
				String healthCondition = fnsRemoveFormatting(CurrentSet[1]).toLowerCase().trim();

				int whileRetries = 0;
				while (true) {
					try {

						fnpMouseHover("NSFOnline_CustomerTopMenu");

						fnpAjax_Link_Click_By_Passing_TwoAjaxXPathx("NSFOnline_CustomerTopMenu", "NSFOnline_DocumentsSubMenu");
						fnpLoading_wait_InDirectNSFOnline();

						driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
						// fnpClick_OR_DirectNSFOnline("NSFOnline_ExpandSearchCriteriaLink");
						driver.findElement(By.xpath(OR.getProperty("NSFOnline_ExpandSearchCriteriaLink"))).click();
						fnpLoading_wait_InDirectNSFOnline();

						// Thread.sleep(500);
						// fnpType("OR","NSFOnline_DocumentNoTxtBox",DocumentNo);
						fnpGetORObjectX("NSFOnline_DocumentNoTxtBox").sendKeys(DocumentNo);
						// driver.findElement(By.xpath(OR.getProperty("NSFOnline_DocumentNoTxtBox"))).sendKeys(DocumentNo);

						// fnpClick_OR_DirectNSFOnline("SearchBtn");
						driver.findElement(By.xpath(OR.getProperty("SearchBtn"))).click();
						Thread.sleep(3000);
						fnpLoading_wait_InDirectNSFOnline(1);

						// fnpGetORObjectX("SearchBtn").sendKeys(Keys.ARROW_DOWN);
						driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
						driver.findElement(By.xpath(OR.getProperty("SearchBtn"))).sendKeys(Keys.ARROW_DOWN);

						fnpMove_To_Element_and_DoubleClick_NOR(OR.getProperty("NSFOnline_ViewDocuments_DocumentsSearchResult_Table"));
						fnpMouseHover("NSFOnline_ViewDocuments_DocumentsSearchResult_Table");
						TextFetch = fnpGetText_OR("NSFOnline_ViewDocuments_DocumentsSearchResult_Table").trim().toLowerCase();
						driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);
						break;

					} catch (org.openqa.selenium.StaleElementReferenceException e) {
						if (whileRetries < 5) {
							// if (retries <
							// Integer.parseInt(CONFIG.getProperty("genMax_waitTime")))
							// {
							Thread.sleep(1000);
							whileRetries++;
							fnpMymsg("In staleElementException catch block of main while loop of search Docs --" + whileRetries);
							// continue;
						} else {
							throw e;
						}
					} catch (org.openqa.selenium.InvalidSelectorException is) {
						if (whileRetries < 5) {
							// if (retries <
							// Integer.parseInt(CONFIG.getProperty("genMax_waitTime")))
							// {
							Thread.sleep(1000);
							whileRetries++;
							fnpMymsg("In InvalidSelectorException catch block of main while loop of search Docs --" + whileRetries + " --and error thrown is --" + is.getMessage());

							fnpMymsg("@Pradeep---due to invalidSelector Exception , now going to login again for this UserName --" + UserName);
							fnpLaunchBrowserAndLoginDirectNSFOnline(UserName, loginPassword);

							// continue;
						} else {
							throw is;
						}
					}

					catch (Throwable t) {
						//if (whileRetries < 5) {
						if (whileRetries < 3) {
							// if (retries <
							// Integer.parseInt(CONFIG.getProperty("genMax_waitTime")))
							// {
							Thread.sleep(1000);
							whileRetries++;
							fnpMymsg("In Throwable t catch block of main while loop of search Docs --" + whileRetries);
							// continue;
						} else {
							throw t;
						}

					}
				} // /end of while loop

				if (healthCondition.equalsIgnoreCase("good")) {
					try {
						assert TextFetch.contains(DocumentNo.toLowerCase().trim()) : "FAILED == Documents No <" + DocumentNo + "> for Data   : UserName#" + CustomerName + "]  is not coming 'GOOD'<i.e. Not appear into the records Table>, Please refer Screen shot >>" + DocumentNo + "_GoodFail" + fnsScreenShot_Date_format();
						fnpMymsg("PASSED ==  Successfully Verify Documents No <" + DocumentNo + "> for Data  : UserName#" + CustomerName + "]  is coming 'GOOD'.");
						List<WebElement> totalRowsElement = driver.findElements(By.xpath(OR.getProperty("RecordsInSearchDocsTable_xpath")));
						int totalRows = totalRowsElement.size() - 1;

						if (totalRows == 1) {
							fnpMymsg("Total searched records are equal to 1, hence search for good doc works successfully.");
						} else {
							fnpMymsg("Total searched records are NOT equal to 1 as searched records" + "are --" + totalRows + " , hence search for good doc  NOT works successfully.");
							throw new Exception("Total searched records are NOT equal to 1 as searched records" + "are --" + totalRows + " , hence search for good doc  NOT works successfully.");
						}

					} catch (Throwable t) {
						fnpDesireScreenshot(DocumentNo + "_GoodFail");
						throw new Exception(t.getMessage());
					}
					// Thread.sleep(2000);
				} else {
					try {
						assert TextFetch.contains("no records to display") : "FAILED == Documents No <" + DocumentNo + "> for Data  [CustomerNo#" + UserName + " : UserName#" + CustomerName + "]  is not coming 'BAD'<i.e. Appear into the records Table>, Please refer Screen shot >>" + DocumentNo + "_BadFail" + fnsScreenShot_Date_format();
						fnpMymsg("PASSED ==  Successfully Verify Documents No <" + DocumentNo + "> for Data  [CustomerNo#" + UserName + " : UserName#" + CustomerName + "]  is coming 'BAD'.");
					} catch (Throwable t) {
						fnpDesireScreenshot(DocumentNo + "_BadFail");
						throw new Exception(t.getMessage());
					}
					// Thread.sleep(2000);
				}

				// fnpClick_OR_DirectNSFOnline("ResestBtn");// No longer
				// required now because each time we are coming from top menu to
				// sub menu clicking.
				// fnpGetORObjectX("ResestBtn").click();
				// driver.findElement(By.xpath(OR.getProperty("ResestBtn"))).click();
				// fnpLoading_wait_InDirectNSFOnline();

			}

			if (CustomerName_Verification.equalsIgnoreCase("Yes")) {
				fnpClick_OR_DirectNSFOnline("ResestBtn");
				Integer SearchTableTotalRowCount = fnsReturn_TotalRowCount("NSFOnline_Documents_SearchResult_TableHeader");
				Integer ColumnNameNo = fnsReturn_ColumnNo_By_MatchingColumnNameText("NSFOnline_Documents_SearchResult_TableHeader_1stRow", "Client Name", "th");

				while (true) {
					fnsReturn_TotalRowCount("NSFOnline_Documents_SearchResult_TableHeader");
					int ColumnVerificationLoopStart = 1;
					// fnsGet_Element_Enabled("NSFOnline_Documents_SearchResult_TableHeader");
					WebElement stdtable = fnsGet_OR_NsfOnline_ObjectX("NSFOnline_Documents_SearchResult_TableHeader");
					List<WebElement> AllRowsObj = stdtable.findElements(By.tagName("tr"));

					for (WebElement countrows : AllRowsObj) {

						if (ColumnVerificationLoopStart > 1) {

							String ColumnTextXpath = ".//*[@class='x7a']/tbody/tr[" + ColumnVerificationLoopStart + "]/td[" + ColumnNameNo + "]";
						//	String ColumnText = TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(ColumnTextXpath).toLowerCase().trim();

							try {
							//	TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element_and_DoubleClick(ColumnTextXpath);
						////		assert (ColumnText.contains(CustomerName)) : "FAILED == Insight Expected CustomerName(" + CustomerName + ") is not Matched with NsfOnline ActualClientName(" + ColumnText + "), Please refer screen shot [" + CustomerName + fnsScreenShot_Date_format() + "]";
							//	fnpMymsg("PASSED == Direct NSF Online CustomerName(" + CustomerName + ") is Matched with NsfOnline ActualClientName(" + ColumnText + ")");
							} catch (Throwable t) {
								fnpDesireScreenshot(CustomerName);
								fnpMymsg(t.getMessage());
								throw new Exception(t.getMessage());
							}
						}

						ColumnVerificationLoopStart++;

					}

					if (ColumnVerificationLoopStart == 27) {
						if (driver.findElements(By.xpath(OR_NsfOnline.getProperty("NSFOnline_Documents_SearchResult_Pagination_Next"))).size() > 0) {
							// fnsGet_Element_Enabled("NSFOnline_Documents_SearchResult_Pagination_Next");
							fnsWait_and_Click("NSFOnline_Documents_SearchResult_Pagination_Next");
							// Thread.sleep(7000);
							fnpLoading_wait_InDirectNSFOnline();
							Thread.sleep(500);
						} else {
							// Thread.sleep(1500);
							break;
						}

					} else {
						// Thread.sleep(1500);
						break;
					}

				} // while loop end
			} //

			driver.quit();

		} catch (Throwable t) {
			fnpCommonFinalCatchBlock_DirectNSFOnline(t, classNameText + "_flow  method is failed for UserName --" + UserName, "Case" + CaseSerialNo.trim() + "__For_UserName_" + UserName);

		}

	}

	@AfterMethod
	public void reportDataSetResult() {

		if (skip)
			TestUtil.reportDataSetResult(currentSuiteXls, this.getClass().getSimpleName(), count + 2, "SKIP");
		else if (fail) {
			isTestPass = false;
			TestUtil.reportDataSetResult(currentSuiteXls, this.getClass().getSimpleName(), count + 2, "FAIL");
		} else
			TestUtil.reportDataSetResult(currentSuiteXls, this.getClass().getSimpleName(), count + 2, "PASS");

		skip = false;
		fail = false;
	}

	@AfterTest
	public void reportTestResult() {

		int rowNum = currentSuiteXls.getCellRowNum("Test Cases", "TCID", this.getClass().getSimpleName());
		currentSuiteXls.setCellData("Test Cases", "Last_Execution", rowNum, fnReturnDateWithTimeForExcel());
		if (isTestPass) {
			TestUtil.reportDataSetResult(currentSuiteXls, "Test Cases", TestUtil.getRowNum(currentSuiteXls, this.getClass().getSimpleName()), "PASS");
			currentSuiteXls.setCellData("Test Cases", "Last_Successful_Execution", rowNum, fnReturnDateWithTimeForExcel());
		} else
			TestUtil.reportDataSetResult(currentSuiteXls, "Test Cases", TestUtil.getRowNum(currentSuiteXls, this.getClass().getSimpleName()), "FAIL");

		fnpMymsg("=========================================================================================================================================");

		hashXlData.clear();
	}

}
