package nsf.ecap.Direct_NSFOnline_suite;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import nsf.ecap.MonitorPlan_Suite.TestSuiteBase_MonitorPlan;
import nsf.ecap.util.TestDataProvider;
import nsf.ecap.util.TestUtil;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Direct_NSFO_Search_Audits_Test extends
		TestSuiteBase_Direct_NSFOnline {

	// public static boolean NSFOnlineVersionScreenshot = true;

	public static String AuditType;
	public static String CaseSerialNo = null;

	public static String Errormsg;
	// public static String Customer_No = null;
	public static String Insight_SearchUserName = null;
	public static String Split_Text = null;
	public static String No_of_MatchingText_Set[] = null;
	public static String Matching_Text1 = null;
	public static String Matching_Text2 = null;
	public static String Matching_Text3 = null;
	public static String Matching_Text4 = null;
	public static String Matching_Text5 = null;
	public static String Matching_Text6 = null;
	public static String Matching_Text7 = null;
	public static String Matching_Text8 = null;
	public static String Matching_Text9 = null;
	public static String Matching_Text10 = null;

	public static Integer ColumnNumber = null;
	public static Integer GetTextLength;
	public static Integer tabsCount;
	public static Integer count = -1;

	// Runmode of test case in a suite
	@BeforeTest
	public void checkTestSkip() throws Exception {
		count = -1;
		BS_NSFO_01 = new Direct_NSFOnline_Search_Docs();
		BS_NSFO_01.checkTestSkip(this.getClass().getSimpleName());

		fnpMymsg("################################## [BS-03] Direct_NSFO_Search_Audits_Test");
	}

	@Test(dataProviderClass = TestDataProvider.class, dataProvider = "Direct_NSFO_Search_Audits_Test")
	public void Direct_NSFO_Search_Audits_Test(Hashtable<String, String> table) throws Throwable {
		currentClassNameInSimpleText = this.getClass().getSimpleName();
		count++;

		String UserName = table.get("UserName").split("=")[1].trim();

		CaseSerialNo = table.get("Serial_No").split("=")[1];

		if (!runmodes[count].equalsIgnoreCase("Y")) {
			skip = true;
			fnpMymsg("****************************************************************************************************************************************");
			fnpMymsg("################################## Runmode of Case::" + (count + 1) + " for User[" + UserName + "]  is set to NO, So Skipping this Case.");
			fnpMymsg("*****************");
			fnpMymsg("");
			throw new SkipException("Runmode of Case::" + (count + 1) + " for User[" + UserName + "]  is set to NO, So Skipping this Case.");
		} else {
			skip = false;
			fnpMymsg("****************************************************************************************************************************************");
			fnpMymsg("################################## Execution Logs of Case::" + (count + 1) + " for User[" + UserName + "].");

		}

		hashXlData = new HashMap(table);

		try {

			AuditType = table.get("Types_Of_Audit");

			String loginPassword = table.get("Password").split("=")[1].trim();

			fnpLaunchBrowserAndLoginDirectNSFOnline(UserName, loginPassword);

			driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(Long.parseLong(CONFIG.getProperty("pageLoadTime")), TimeUnit.SECONDS);

			/* if (NSFOnlineVersionScreenshot) {
			 * fnpGetDirectNSFOnineVersion(currentClassNameInSimpleText + "_" +
			 * UserName); } */
			
			
			
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
			
			
		
			
			
			
			
			
			if (NSFOnlineVersionScreenshot) {
				int iwhileCounter = 0;
				while (true) {
					iwhileCounter++;
					try {
						Thread.sleep(5000);
						fnpGetDirectNSFOnineVersion(currentClassNameInSimpleText + "_" + UserName);
						break;
					} catch (Throwable te) {
						if (iwhileCounter < 5) {
							fnpLaunchBrowserAndLoginDirectNSFOnline(UserName, loginPassword);
						} else {
							//throw new Exception(te.getStackTrace());
							throw new Exception(te.getMessage());
						}

					}
				}
			}

			// Verification of Completed_Audits and
			// Scheduled_and_Upcoming_Audits
			if ((AuditType.contains("Completed_Audits")) || (AuditType.contains("Scheduled_and_Upcoming_Audits"))) {
				Thread.sleep(1000);
				if (fnpCheckElementDisplayedImmediately("NSFOnline_ExpandSearchCriteriaLink")) {
					fnpClick_OR_DirectNSFOnline("NSFOnline_ExpandSearchCriteriaLink");

				}

				String NoOfSets[] = table.get("Search_AuditsNos_and_Matching_or_NotMatchingText").split(":");
				fnpMymsg("Sets data are [" + table.get("Search_AuditsNos_and_Matching_or_NotMatchingText") + "] for (" + AuditType + ") Type. And No. of Sets are == " + NoOfSets.length);

				for (int j = 0; j < NoOfSets.length; j++) {

					String AuditSet = NoOfSets[j];
					fnpMymsg("***************** Current searching (" + AuditType + ") Set  is [" + AuditSet + "]  ******************");

					String CurrentSet[] = AuditSet.split(",");

					String AuditNo = fnsRemoveFormatting(CurrentSet[0]);
					String healthCondition = fnsRemoveFormatting(CurrentSet[1]).toLowerCase().trim();

					int whileRetries = 0;
					while (true) {
						try {

							if ((AuditType.contains("Completed_Audits")) || (AuditType.contains("SCA_Client_Audit_Validation")) || (AuditType.contains("SCA_Supplier_Audit_Validation"))) {
								fnpAjax_Link_Click_By_Passing_TwoAjaxXPathx("NSFOnline_AuditsTopMenu", "NSFOnline_CompletedAuditsSubMenu");
								fnpLoading_wait_InDirectNSFOnline();
							}
							if (AuditType.contains("Scheduled_and_Upcoming_Audits")) {
								fnpAjax_Link_Click_By_Passing_TwoAjaxXPathx("NSFOnline_AuditsTopMenu", "NSFOnline_ScheduledUpcomingSubMenu");
								fnpLoading_wait_InDirectNSFOnline();
							}
							if (AuditType.contains("SCA_Client_Standards")) {
								fnpAjax_Link_Click_By_Passing_TwoAjaxXPathx("NSFOnline_Customer_Ajax_Link", "NSFOnline_Standards_Ajax_Link");
								Thread.sleep(3000);
								fnpLoading_wait_InDirectNSFOnline();
							}

							// fnpClick_OR_DirectNSFOnline("NSFOnline_ExpandSearchCriteriaLink");
							driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
							driver.findElement(By.xpath(OR.getProperty("NSFOnline_ExpandSearchCriteriaLink"))).click();

							Thread.sleep(1000);
							
							fnpGetORObjectX___NOR("//button[contains(text(), 'Search')]");
							
							fnpGetORObjectX("NSFOnline_AuditNoTxtBox").sendKeys(AuditNo);
							// driver.findElement(By.xpath(OR.getProperty("NSFOnline_AuditNoTxtBox"))).sendKeys(AuditNo);
							
							
							//fnpClick_OR_DirectNSFOnline("NSFOnline_CompletedAuditSearch_Bttn");
							
/*							fnpWaitTillVisiblityOfElementNClickable_OR_DirectNSFOnline("NSFOnline_AuditSearchBttn_DivXpath");
							//String SearchBttnText=fnpGetText_OR("NSFOnline_AuditSearchBttn_DivXpath").toLowerCase().trim();
							String SearchBttnText=fnpGetText_OR("NSFOnline_AuditSearchBttn_DivXpath");
							
							if (SearchBttnText.contains("Search")){
								//fnpClick_OR_DirectNSFOnline("NSFOnline_CompletedAuditSearch_Bttn");
								driver.findElement(By.xpath("//button[contains(text(), 'Search')]")).click();
								fnpLoading_wait_InDirectNSFOnline();
							}
							if ((SearchBttnText.contains("SEARCH"))){
								//fnpClick_OR_DirectNSFOnline("NSFOnline_CompletedAuditSearch_Bttn");
								//driver.findElement(By.xpath("//button[contains(text(), 'SEARCH')]")).click();
								driver.findElement(By.xpath("//button[contains(text(), 'Search')]")).click();
								fnpLoading_wait_InDirectNSFOnline();
							}
							*/	
							
							
							
							
							driver.findElement(By.xpath("//button[contains(text(), 'Search')]")).click();
							fnpLoading_wait_InDirectNSFOnline();
							
							
							
							Thread.sleep(1000);
							// fnpGetORObjectX("NSFOnline_CompletedAuditSearch_Bttn").sendKeys(Keys.ARROW_DOWN);
							driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
						//	driver.findElement(By.xpath(OR.getProperty("NSFOnline_CompletedAuditSearch_Bttn"))).sendKeys(Keys.ARROW_DOWN);
							
							fnpGetORObjectX___NOR("//button[contains(text(), 'Search')]");
							driver.findElement(By.xpath("//button[contains(text(), 'Search')]")).sendKeys(Keys.ARROW_DOWN);
							fnpGetORObjectX___NOR("//button[contains(text(), 'Search')]").sendKeys(Keys.ARROW_DOWN);
							fnpGetORObjectX___NOR("//button[contains(text(), 'Search')]").sendKeys(Keys.ARROW_DOWN);
							
							driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);

							break;

						} catch (org.openqa.selenium.StaleElementReferenceException e) {
							if (whileRetries < 5) {
								// if (retries <
								// Integer.parseInt(CONFIG.getProperty("genMax_waitTime")))
								// {
								Thread.sleep(1000);
								whileRetries++;
								fnpMymsg("In staleElementException catch block of main while loop of search Audits Test --" + whileRetries);
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
								fnpMymsg("In InvalidSelectorException catch block of main while loop of search Audits Test --" + whileRetries + " --and error thrown is --" + is.getMessage());

								fnpMymsg("@Pradeep---due to invalidSelector Exception , now going to login again for this UserName --" + UserName);
								fnpLaunchBrowserAndLoginDirectNSFOnline(UserName, loginPassword);
								// continue;
							} else {
								throw is;
							}
						}

						catch (Throwable t) {

							if (whileRetries < 5) {
								// if (retries <
								// Integer.parseInt(CONFIG.getProperty("genMax_waitTime")))
								// {
								Thread.sleep(1000);
								whileRetries++;
								fnpMymsg("In Throwable t catch block of main while loop of search Audits Test --" + whileRetries);
								// continue;
							} else {
								throw t;
							}

						}
					} // /end of while loop

					if (healthCondition.equalsIgnoreCase("good")) {
						// assert
						// (GetTextLength>50):"Audit No <"+AuditNo+"> for Data  [CustomerNo#"+Customer_No+" : UserName#"+Insight_SearchUserName+"]  is not coming 'GOOD'<i.e. Not appear into the records Table>.";
						Assert.assertTrue(fnpCheckRecordsPresentInTableInNSFOnline(OR.getProperty("NSFOnline_CompletedAuditSearchResult_Table_bodyXpath")), "Audit No <" + AuditNo + "> for Data  [UserName#" + UserName + "]  is not coming 'GOOD'<i.e. Not Appearing into the records Table>.");
						fnpMymsg("PASSED == Audit No <" + AuditNo + "> for Data  [UserName#" + UserName + "]  is  coming 'GOOD'<i.e. Appear into the records Table>.");

					} else {
						// assert
						// (GetTextLength<50):"Audit No <"+AuditNo+"> for Data  [CustomerNo#"+Customer_No+" : UserName#"+Insight_SearchUserName+"]  is not coming 'BAD'<i.e. Appear into the records Table>.";
						Assert.assertFalse(fnpCheckRecordsPresentInTableInNSFOnline(OR.getProperty("NSFOnline_CompletedAuditSearchResult_Table_bodyXpath")), "Audit No <" + AuditNo + "> for Data  [UserName#" + UserName + "]  is not coming 'BAD' <i.e. Appearing into the records Table>.");
						fnpMymsg("PASSED == Audit No <" + AuditNo + "> Data  [UserName#" + UserName + "]   is coming 'BAD'<i.e. Not appear into the records Table>.");

					}

				}

			}

			String Search_AuditsNos_and_Matching_or_NotMatchingText = table.get("Search_AuditsNos_and_Matching_or_NotMatchingText").trim();

			// Verification of SCA_Client_Audit_Validation
			if ((AuditType.contains("SCA_Client_Audit_Validation"))) {
				int whileRetries2 = 0;
				while (true) {
					try {
						if ((AuditType.contains("Completed_Audits")) || (AuditType.contains("SCA_Client_Audit_Validation")) || (AuditType.contains("SCA_Supplier_Audit_Validation"))) {
							fnpAjax_Link_Click_By_Passing_TwoAjaxXPathx("NSFOnline_AuditsTopMenu", "NSFOnline_CompletedAuditsSubMenu");
							fnpLoading_wait_InDirectNSFOnline();
						}
						if (AuditType.contains("Scheduled_and_Upcoming_Audits")) {
							fnpAjax_Link_Click_By_Passing_TwoAjaxXPathx("NSFOnline_AuditsTopMenu", "NSFOnline_ScheduledUpcomingSubMenu");
							fnpLoading_wait_InDirectNSFOnline();
						}
						if (AuditType.contains("SCA_Client_Standards")) {
							fnpAjax_Link_Click_By_Passing_TwoAjaxXPathx("NSFOnline_Customer_Ajax_Link", "NSFOnline_Standards_Ajax_Link");
							Thread.sleep(3000);
							fnpLoading_wait_InDirectNSFOnline();
						}

						FncNSfOnline_Verify_SearchResult_ColumnValues("Audit Type", Search_AuditsNos_and_Matching_or_NotMatchingText);

						driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);

						break;

					} catch (org.openqa.selenium.StaleElementReferenceException e) {
						if (whileRetries2 < 5) {
							// if (retries <
							// Integer.parseInt(CONFIG.getProperty("genMax_waitTime")))
							// {
							Thread.sleep(1000);
							whileRetries2++;
							fnpMymsg("In staleElementException catch block of main while loop of search Audits Test --" + whileRetries2);
							// continue;
						} else {
							throw e;
						}
					} catch (org.openqa.selenium.InvalidSelectorException is) {
						if (whileRetries2 < 5) {
							// if (retries <
							// Integer.parseInt(CONFIG.getProperty("genMax_waitTime")))
							// {
							Thread.sleep(1000);
							whileRetries2++;
							fnpMymsg("In InvalidSelectorException catch block of main while loop of search Audits Test --" + whileRetries2 + " --and error thrown is --" + is.getMessage());

							fnpMymsg("@Pradeep---due to invalidSelector Exception , now going to login again for this UserName --" + UserName);
							fnpLaunchBrowserAndLoginDirectNSFOnline(UserName, loginPassword);
							// continue;
						} else {
							throw is;
						}
					}

					catch (Throwable t) {

						if (whileRetries2 < 5) {
							// if (retries <
							// Integer.parseInt(CONFIG.getProperty("genMax_waitTime")))
							// {
							Thread.sleep(1000);
							whileRetries2++;
							fnpMymsg("In Throwable t catch block of main while loop of search Audits Test --" + whileRetries2);
							// continue;
						} else {
							throw t;
						}

					}
				} // /end of while loop

			}

			// Verification of SCA_Supplier_Audit_Validation
			if ((AuditType.contains("SCA_Supplier_Audit_Validation"))) {
				int whileRetries3 = 0;
				while (true) {
					try {
						if ((AuditType.contains("Completed_Audits")) || (AuditType.contains("SCA_Client_Audit_Validation")) || (AuditType.contains("SCA_Supplier_Audit_Validation"))) {
							fnpAjax_Link_Click_By_Passing_TwoAjaxXPathx("NSFOnline_AuditsTopMenu", "NSFOnline_CompletedAuditsSubMenu");
							fnpLoading_wait_InDirectNSFOnline();
						}
						if (AuditType.contains("Scheduled_and_Upcoming_Audits")) {
							fnpAjax_Link_Click_By_Passing_TwoAjaxXPathx("NSFOnline_AuditsTopMenu", "NSFOnline_ScheduledUpcomingSubMenu");
							fnpLoading_wait_InDirectNSFOnline();
						}
						if (AuditType.contains("SCA_Client_Standards")) {
							fnpAjax_Link_Click_By_Passing_TwoAjaxXPathx("NSFOnline_Customer_Ajax_Link", "NSFOnline_Standards_Ajax_Link");
							Thread.sleep(3000);
							fnpLoading_wait_InDirectNSFOnline();
						}

						if (fnpCheckElementDisplayedImmediately("NSFOnline_ExpandSearchCriteriaLink")) {
							fnpClick_OR_DirectNSFOnline("NSFOnline_ExpandSearchCriteriaLink");

						}

						// if( (Customer_No.toLowerCase()).equalsIgnoreCase("c0177059")
						// ){
						if ((UserName.toLowerCase()).equalsIgnoreCase("c0177067admin")) {
							FncNSfOnline_Verify_SearchResult_ColumnValues("Cus#", Search_AuditsNos_and_Matching_or_NotMatchingText);
						} else {
							FncNSfOnline_Verify_SearchResult_ColumnValues("Type/Std", Search_AuditsNos_and_Matching_or_NotMatchingText);
						}

						driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);

						break;

					} catch (org.openqa.selenium.StaleElementReferenceException e) {
						if (whileRetries3 < 5) {
							// if (retries <
							// Integer.parseInt(CONFIG.getProperty("genMax_waitTime")))
							// {
							Thread.sleep(1000);
							whileRetries3++;
							fnpMymsg("In staleElementException catch block of main while loop of search Audits Test --" + whileRetries3);
							// continue;
						} else {
							throw e;
						}
					} catch (org.openqa.selenium.InvalidSelectorException is) {
						if (whileRetries3 < 5) {
							// if (retries <
							// Integer.parseInt(CONFIG.getProperty("genMax_waitTime")))
							// {
							Thread.sleep(1000);
							whileRetries3++;
							fnpMymsg("In InvalidSelectorException catch block of main while loop of search Audits Test --" + whileRetries3 + " --and error thrown is --" + is.getMessage());

							fnpMymsg("@Pradeep---due to invalidSelector Exception , now going to login again for this UserName --" + UserName);
							fnpLaunchBrowserAndLoginDirectNSFOnline(UserName, loginPassword);
							// continue;
						} else {
							throw is;
						}
					}

					catch (Throwable t) {

						if (whileRetries3 < 5) {
							// if (retries <
							// Integer.parseInt(CONFIG.getProperty("genMax_waitTime")))
							// {
							Thread.sleep(1000);
							whileRetries3++;
							fnpMymsg("In Throwable t catch block of main while loop of search Audits Test --" + whileRetries3);
							// continue;
						} else {
							throw t;
						}

					}
				} // /end of while loop

			}

			// Verification of SCA_Client_Standards
			if ((AuditType.contains("SCA_Client_Standards"))) {
				int whileRetries4 = 0;
				while (true) {
					try {
						if ((AuditType.contains("Completed_Audits")) || (AuditType.contains("SCA_Client_Audit_Validation")) || (AuditType.contains("SCA_Supplier_Audit_Validation"))) {
							fnpAjax_Link_Click_By_Passing_TwoAjaxXPathx("NSFOnline_AuditsTopMenu", "NSFOnline_CompletedAuditsSubMenu");
							fnpLoading_wait_InDirectNSFOnline();
						}
						if (AuditType.contains("Scheduled_and_Upcoming_Audits")) {
							fnpAjax_Link_Click_By_Passing_TwoAjaxXPathx("NSFOnline_AuditsTopMenu", "NSFOnline_ScheduledUpcomingSubMenu");
							fnpLoading_wait_InDirectNSFOnline();
						}
						if (AuditType.contains("SCA_Client_Standards")) {
							fnpAjax_Link_Click_By_Passing_TwoAjaxXPathx("NSFOnline_Customer_Ajax_Link", "NSFOnline_Standards_Ajax_Link");
							Thread.sleep(3000);
							fnpLoading_wait_InDirectNSFOnline();
						}

						if (fnpCheckElementDisplayedImmediately("NSFOnline_ExpandSearchCriteriaLink")) {
							fnpClick_OR_DirectNSFOnline("NSFOnline_ExpandSearchCriteriaLink");

						}

						FncNSfOnline_Verify_SearchResult_ColumnValues("Standard Code", Search_AuditsNos_and_Matching_or_NotMatchingText);

						driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);

						break;

					} catch (org.openqa.selenium.StaleElementReferenceException e) {
						if (whileRetries4 < 5) {
							// if (retries <
							// Integer.parseInt(CONFIG.getProperty("genMax_waitTime")))
							// {
							Thread.sleep(1000);
							whileRetries4++;
							fnpMymsg("In staleElementException catch block of main while loop of search Audits Test --" + whileRetries4);
							// continue;
						} else {
							throw e;
						}
					} catch (org.openqa.selenium.InvalidSelectorException is) {
						if (whileRetries4 < 5) {
							// if (retries <
							// Integer.parseInt(CONFIG.getProperty("genMax_waitTime")))
							// {
							Thread.sleep(1000);
							whileRetries4++;
							fnpMymsg("In InvalidSelectorException catch block of main while loop of search Audits Test --" + whileRetries4 + " --and error thrown is --" + is.getMessage());

							fnpMymsg("@Pradeep---due to invalidSelector Exception , now going to login again for this UserName --" + UserName);
							fnpLaunchBrowserAndLoginDirectNSFOnline(UserName, loginPassword);
							// continue;
						} else {
							throw is;
						}
					}

					catch (Throwable t) {

						if (whileRetries4 < 5) {
							// if (retries <
							// Integer.parseInt(CONFIG.getProperty("genMax_waitTime")))
							// {
							Thread.sleep(1000);
							whileRetries4++;
							fnpMymsg("In Throwable t catch block of main while loop of search Audits Test --" + whileRetries4);
							// continue;
						} else {
							throw t;
						}

					}
				} // /end of while loop

			}

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

	// }

	// ######################################################### Class Function
	// ######################################################################################

	public static void FncNSfOnline_Verify_SearchResult_ColumnValues(String ColumnName_TextNeedToVerify, String DP_Excel_Column_MatchingText) throws Throwable {
		try {
			// fnpWaitForVisible("NSFOnline_Audit_SearchResult_TableHeader");
			Thread.sleep(1000);

			String Table_ColumnHeader_Row_Xpath = OR.getProperty("NSFOnline_Audit_SearchResult_TableHeader") + "/tbody/tr[1]";
			// Integer ColumnNumber =
			// TestSuiteBase_MonitorPlan.WithOut_OR_Retrun_TableColumnNo_of_MatchingColumnName(Table_ColumnHeader_Row_Xpath,
			// ColumnName_TextNeedToVerify);
			Integer ColumnNumber = fnpFindColumnIndex_InNSFOnline_NOR(Table_ColumnHeader_Row_Xpath, ColumnName_TextNeedToVerify);

			int TotalRows = driver.findElements(By.xpath(OR.getProperty("NSFOnline_Audit_SearchResult_TableHeader") + "/tbody/tr")).size();
			// for(int RowNo_Start = 2; RowNo_Start<TotalRows +1;
			// RowNo_Start++){
			int RowNo_Start = 2;
			while (RowNo_Start < TotalRows + 1) {

				fnpMymsg("@Pradeep --RowNo_Start value  now in for loop is --" + RowNo_Start);
				String AuditType_ColumnRow_Xpath = OR.getProperty("NSFOnline_Audit_SearchResult_TableHeader") + "/tbody/tr[" + RowNo_Start + "]/td[" + ColumnNumber + "]";

				if (driver.findElements(By.xpath(AuditType_ColumnRow_Xpath)).size() > 0) {
					// String AuditTypeColumn_Text =
					// TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(AuditType_ColumnRow_Xpath).toLowerCase().trim();
					String AuditTypeColumn_Text = fnpGetText_NOR(AuditType_ColumnRow_Xpath).toLowerCase().trim();
					// String First4Letter_of_AuditTypeColumn =
					// AuditTypeColumn_Text.substring(0, 4).toLowerCase();
			//		TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element_and_DoubleClick(AuditType_ColumnRow_Xpath);
					// fnpMove_To_Element_and_DoubleClick_NOR(AuditType_ColumnRow_Xpath);

					String Excel_Column_MatchingText_Split = DP_Excel_Column_MatchingText.split("=")[0].toLowerCase().trim();
					String Split_Text;
					String[] No_of_MatchingText_Set;
					String Matching_Text1;
					if (Excel_Column_MatchingText_Split.contains("start")) {

						String letters_of_AuditTypeColumnToBeMatched1 = "";
						String letters_of_AuditTypeColumnToBeMatched2 = "";
						int lenghtOfMatchingText1 = 0;
						int lenghtOfMatchingText2 = 0;
						Split_Text = DP_Excel_Column_MatchingText.split("=")[1].trim();
						No_of_MatchingText_Set = Split_Text.split("/");
						if (No_of_MatchingText_Set.length > 2) {
							throw new Exception("Code for Start with option is written only for 2 start with not for --" + No_of_MatchingText_Set.length);

						}
						if (No_of_MatchingText_Set.length == 1) {
							Matching_Text1 = No_of_MatchingText_Set[0];
							lenghtOfMatchingText1 = Matching_Text1.length();
							letters_of_AuditTypeColumnToBeMatched1 = AuditTypeColumn_Text.substring(0, lenghtOfMatchingText1).toLowerCase();
							try {
								assert (letters_of_AuditTypeColumnToBeMatched1.equalsIgnoreCase(Matching_Text1.toLowerCase().trim())) : "In RowNo<" + RowNo_Start + ">, the '" + ColumnName_TextNeedToVerify + "' Column contains the Text<" + AuditTypeColumn_Text.toUpperCase() + ">, which is not start with '" + Matching_Text1 + "'. Please refer screenshot >> Case" + CaseSerialNo.trim() + "_TextStartWith_Fail" + fnsScreenShot_Date_format();
							} catch (Throwable t) {
								fnpDesireScreenshot("Case" + CaseSerialNo.trim() + "_TextStartWith_Fail");
								throw new Exception(t.getMessage());
							}

						}
						if (No_of_MatchingText_Set.length == 2) {
							Matching_Text1 = No_of_MatchingText_Set[0];
							Matching_Text2 = No_of_MatchingText_Set[1];

							lenghtOfMatchingText1 = Matching_Text1.length();
							lenghtOfMatchingText2 = Matching_Text2.length();

							letters_of_AuditTypeColumnToBeMatched1 = AuditTypeColumn_Text.substring(0, lenghtOfMatchingText1).toLowerCase();
							letters_of_AuditTypeColumnToBeMatched2 = AuditTypeColumn_Text.substring(0, lenghtOfMatchingText2).toLowerCase();

							try {
								assert (letters_of_AuditTypeColumnToBeMatched1.equalsIgnoreCase(Matching_Text1.toLowerCase().trim()) || letters_of_AuditTypeColumnToBeMatched2.equalsIgnoreCase(Matching_Text2.toLowerCase().trim())) : "In RowNo<" + RowNo_Start + ">, the '" + ColumnName_TextNeedToVerify + "' Column contains the Text<" + AuditTypeColumn_Text.toUpperCase() + ">, which is not start with '" + Matching_Text1 + "/" + Matching_Text2 + "'. Please refer screenshot >> Case" + CaseSerialNo.trim() + "_TextStartWith_Fail" + fnsScreenShot_Date_format();
							} catch (Throwable t) {
								fnpDesireScreenshot("Case" + CaseSerialNo.trim() + "_TextStartWith_Fail");
								throw new Exception(t.getMessage());
							}
						}

					} else {

						Split_Text = DP_Excel_Column_MatchingText.split("=")[1].trim();
						/* No_of_MatchingText_Set = Split_Text.split("/"); */

						try {
							assert ((Split_Text.toLowerCase()).contains(AuditTypeColumn_Text.toLowerCase().trim())) : "In RowNo<" + RowNo_Start + ">, the '" + ColumnName_TextNeedToVerify + "' Column contains the Text<" + AuditTypeColumn_Text.toUpperCase() + ">, which is not matched with '" + Split_Text + "'. Please refer screenshot >> " + "Case" + CaseSerialNo.trim() + "_ColumnValueMatchFail" + fnsScreenShot_Date_format();
						} catch (Throwable t) {
							fnpDesireScreenshot("Case" + CaseSerialNo.trim() + "_ColumnValueMatchFail");
							throw new Exception(t.getMessage());
						}

					}

				} else {
					break;
				}
				if (RowNo_Start == 26) {
					fnpMymsg("@Pradeep --RowNo_Start value  now in checking condition 'RowNo_Start==26' is --" + RowNo_Start);
					AuditType_ColumnRow_Xpath = OR.getProperty("NSFOnline_Audit_SearchResult_TableHeader") + "/tbody/tr[" + (RowNo_Start + 1) + "]/td[" + ColumnNumber + "]";
					// if(driver.findElements(By.xpath(AuditType_ColumnRow_Xpath)).size()>0){
					if (fnpCheckElementPresenceImmediately_NotInOR(AuditType_ColumnRow_Xpath)) {
						throw new Exception("Pagination Failed == More than 25 records are displayed in search results on a single page");
					} else
					// if(driver.findElements(By.xpath(OR.getProperty("NSFOnline_Standards_SearchResult_NEXT_Link"))).size()>0){
					if (fnpCheckElementPresenceImmediately_NotInOR(OR.getProperty("NSFOnline_Standards_SearchResult_NEXT_Link_NotDisable"))) {
						fnpClick_OR_DirectNSFOnline("NSFOnline_Standards_SearchResult_NEXT_Link_NotDisable");
						fnpMymsg("@Pradeep Clicked 'Next' link.");
						TotalRows = driver.findElements(By.xpath(OR.getProperty("NSFOnline_Audit_SearchResult_TableHeader") + "/tbody/tr")).size();
						RowNo_Start = 1;
					}else{
						break;
					}
				}
				RowNo_Start++;
			}
			// Thread.sleep(2000);
			fnpMymsg("PASSED == Case:" + CaseSerialNo.trim() + " >> Successfully verified all text values in column '" + ColumnName_TextNeedToVerify + "'.");

		} catch (Throwable t) {
			// driver.close();
			fnpMymsg("PASSED == Successfully Close NSFOnline Window.");
			// fnpMymsg("PASSED == Successfully Switch to Insight Window.");
			throw new Exception("FAILED == Case:" + CaseSerialNo.trim() + " >>> " + t.getMessage());
		}
	}

}
