package nsf.ecap.Direct_NSFOnline_suite;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.TimeUnit;

import nsf.ecap.MonitorPlan_Suite.TestSuiteBase_MonitorPlan;
import nsf.ecap.util.TestDataProvider;
import nsf.ecap.util.TestUtil;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Direct_NSFO_Search_Suppliers extends
		TestSuiteBase_Direct_NSFOnline {
	public static String TextFetch = null;

	// public static boolean NSFOnlineVersionScreenshot = true;

	// Runmode of test case in a suite
	@BeforeTest
	public void checkTestSkip() throws Exception {
		count = -1;
		BS_NSFO_01 = new Direct_NSFOnline_Search_Docs();
		BS_NSFO_01.checkTestSkip(this.getClass().getSimpleName());

	}

	@Test(dataProviderClass = TestDataProvider.class, dataProvider = "Direct_NSFO_Search_Suppliers")
	public void Direct_NSFO_Search_Suppliers(Hashtable<String, String> table) throws Throwable {
		currentClassNameInSimpleText = this.getClass().getSimpleName();
		count++;

		String UserName = table.get("UserName").split("=")[1].trim();

		String CaseSerialNo = table.get("Serial_No").split("=")[1];

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

			hashXlData = new HashMap(table);

			fnpLaunchBrowserAndLoginDirectNSFOnline(UserName, loginPassword);
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

			String Document_Verification = "";
			String NoOfSets[] = table.get("Search_IDs").split(":");
			fnpMymsg("Sets data are [" + table.get("Search_IDs") + "]. and No. of " + " Ids are == " + NoOfSets.length);

			for (int j = 0; j < NoOfSets.length; j++) {

				if (!(table.get("Document_Verification").trim().equalsIgnoreCase(""))) {
					Document_Verification = table.get("Document_Verification").split(":")[j].trim();
				}

				String Set = NoOfSets[j];
				fnpMymsg("***************** Current searching " + " Id is [" + Set + "]  ******************");

				String CurrentSet[] = Set.split(",");

				String SupplierID = fnsRemoveFormatting(CurrentSet[0]);
				String healthCondition = fnsRemoveFormatting(CurrentSet[1]).toLowerCase().trim();

				int whileRetries = 0;
				while (true) {
					try {
						fnpMouseHover("NSFOnline_CustomerTopMenu");
						fnpAjax_Link_Click_By_Passing_TwoAjaxXPathx("NSFOnline_CustomerTopMenu", "NSFOnline_Customer_Suppliers");
						fnpLoading_wait_InDirectNSFOnline();

						driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

						// fnpClick_OR_DirectNSFOnline("NSFOnline_ExpandSearchCriteriaLink");
						driver.findElement(By.xpath(OR.getProperty("NSFOnline_ExpandSearchCriteriaLink"))).click();
						// Thread.sleep(3000);
						fnpLoading_wait_InDirectNSFOnline();

						driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
						// fnpGetORObjectX("NSFOnline_MySuppliers_CustomerSearchCriteria_CustomerText").sendKeys(SupplierID);
						driver.findElement(By.xpath(OR.getProperty("NSFOnline_MySuppliers_CustomerSearchCriteria_CustomerText"))).sendKeys(SupplierID);

						// fnpGetORObjectX("SearchBtn").click();
						driver.findElement(By.xpath(OR.getProperty("SearchBtn"))).click();
						Thread.sleep(3000);
						fnpLoading_wait_InDirectNSFOnline(1);
						// Thread.sleep(3000);
						// fnpGetORObjectX("SearchBtn").sendKeys(Keys.ARROW_DOWN);
						driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
						driver.findElement(By.xpath(OR.getProperty("SearchBtn"))).sendKeys(Keys.ARROW_DOWN);
						Thread.sleep(1000);
						// fnpWaitForVisible("NSFOnline_SupplierSearchResults_Table");
						// fnpMouseHover("NSFOnline_SupplierSearchResults_Table");
						fnpMove_To_Element_and_DoubleClick_NOR(OR.getProperty("NSFOnline_SupplierSearchResults_Table"));
						TextFetch = fnpGetText_OR("NSFOnline_SupplierSearchResults_Table").trim().toLowerCase();
						driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);

						if (healthCondition.equalsIgnoreCase("good")) {
							try {
								assert TextFetch.contains(SupplierID.toLowerCase().trim()) : "FAILED == Documents No <" + SupplierID + "> for Data   : UserName#" + UserName + "]  is not coming 'GOOD'<i.e. Not appear into the records Table>, Please refer Screen shot >>" + SupplierID + "_GoodFail" + fnsScreenShot_Date_format();
								fnpMymsg("PASSED ==  Successfully Verify SupplierID No <" + SupplierID + "> for Data  : UserName#" + UserName + "]  is coming 'GOOD'.");

								List<WebElement> totalRowsElement = driver.findElements(By.xpath(OR.getProperty("RecordsInSupplierSearchResults_Table_xpath")));
								int totalRows = totalRowsElement.size() - 1;

								if (totalRows == 1) {
									fnpMymsg("Total searched records are equal to 1, hence search for good Supplier ID works successfully.");
								} else {
									fnpMymsg("Total searched records are NOT equal to 1 as searched records" + "are --" + totalRows + " , hence search for good Supplier ID  NOT works successfully.");
									throw new Exception("Total searched records are NOT equal to 1 as searched records" + "are --" + totalRows + " , hence search for good Supplier ID  NOT works successfully.");
								}

							} catch (Throwable t) {
								fnpDesireScreenshot(SupplierID + "_GoodFail");
								throw new Exception(t.getMessage());
							}
							// Thread.sleep(2000);
						} else {
							try {
								assert TextFetch.contains("no items were found") : "FAILED == Supplier ID <" + SupplierID + "> for Data   : UserName#" + UserName + "]  is not coming 'BAD'<i.e. Appear into the records Table>, Please refer Screen shot >>" + SupplierID + "_BadFail" + fnsScreenShot_Date_format();
								fnpMymsg("PASSED ==  Successfully Verify Supplier ID <" + SupplierID + "> for Data   : UserName#" + UserName + "]  is coming 'BAD'.");
							} catch (Throwable t) {
								fnpDesireScreenshot(SupplierID + "_BadFail");
								throw new Exception(t.getMessage());
							}
							// Thread.sleep(2000);
						}

						Thread.sleep(1000);

						// if (Document_Verification.equalsIgnoreCase("NA") |
						// (!(Document_Verification.trim().equalsIgnoreCase("")))){
						if (!(Document_Verification.trim().equalsIgnoreCase(""))) {

							String SupplierId_Link_Xpath = "//a[contains(text(), '" + SupplierID + "')]";
							// driver.findElement(By.xpath(SupplierId_Link_Xpath)).click();
							try {
								//sometime clicked but page not opened ,so going to click again once more if it happend
								Thread.sleep(1000);
								fnpMouseHover_NotInOR(SupplierId_Link_Xpath);
								fnpGetORObjectX___NOR(SupplierId_Link_Xpath).click();
							} catch (Throwable te) {
								Thread.sleep(2000);
								fnpMouseHover_NotInOR(SupplierId_Link_Xpath);
								fnpGetORObjectX___NOR(SupplierId_Link_Xpath).click();
							}
							// TestSuiteBase_Aspirago.fnsLoading_Progressingwait_AspiragoOnline(4);
							fnpLoading_wait_InDirectNSFOnline();
							// fnsGet_Element_Enabled("Footer");

							// fnsGet_Element_Enabled("NSFOnline_ViewSuppliers_Documents");
							// fnsWait_and_Click("NSFOnline_ViewSuppliers_Documents");
							fnpClick_OR_DirectNSFOnline("NSFOnline_ViewSuppliers_Documents");
							// TestSuiteBase_Aspirago.fnsLoading_Progressingwait_AspiragoOnline(2);

							fnpWaitForVisible("NSFOnline_ViewSuppliers_Documents_Table");
							String DocumentTable_Text = fnpGetText_OR("NSFOnline_ViewSuppliers_Documents_Table").toLowerCase().trim();
					//		TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element_BY_OFFSET(OR_NsfOnline.getProperty("NSFOnline_ViewSuppliers_Documents_Table"), 10, 20);

							if (Document_Verification.equalsIgnoreCase("NA")) {
								try {
									// Thread.sleep(2000);
									assert (DocumentTable_Text.contains("no records to display")) : "FAILED == 'No Record To Display' is not coming into the Document TAB, Please refer Screenshot>>" + "NoRecordsToDisplay_FAIL_" + fnsScreenShot_Date_format();
									fnpMymsg("PASSED == Successfully Verified that 'No Record To Display' is coming in Document TAB.");
								} catch (Throwable t) {
									fnpDesireScreenshot("NoRecordsToDisplay_FAIL_");
									throw new Exception(t.getMessage());
								}
							} else {
								try {
									// Thread.sleep(2000);
									assert (DocumentTable_Text.contains(Document_Verification.toLowerCase())) : "FAILED == Document<" + Document_Verification + ">is not displayed into the Document TAB, Please refer Screenshot>>" + Document_Verification + "_DisplayFAIL_" + fnsScreenShot_Date_format();
									fnpMymsg("PASSED == Successfully Verified that Document<" + Document_Verification + "> is displayed into the Document TAB.");
								} catch (Throwable t) {
									fnpDesireScreenshot(Document_Verification + "_DisplayFAIL_");
									throw new Exception(t.getMessage());
								}
							}

							fnpClick_OR_DirectNSFOnline("NSFOBackBtn");

						} else {
							fnpClick_OR_DirectNSFOnline("ResestBtn");
						}

						break;

					} catch (org.openqa.selenium.StaleElementReferenceException e) {
						if (whileRetries < 5) {
							// if (retries <
							// Integer.parseInt(CONFIG.getProperty("genMax_waitTime")))
							// {
							Thread.sleep(1000);
							whileRetries++;
							fnpMymsg("In staleElementException catch block of main while loop of search suppliers --" + whileRetries);
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
							fnpMymsg("In InvalidSelectorException catch block of main while loop of search suppliers --" + whileRetries + " --and error thrown is --" + is.getMessage());

							fnpMymsg("@Pradeep---due to invalidSelector Exception , now going to login again for this UserName --" + UserName);
							fnpLaunchBrowserAndLoginDirectNSFOnline(UserName, loginPassword);
							// continue;
						} else {
							throw is;
						}
					}

					
					catch (org.openqa.selenium.NoSuchElementException NSEE) {
						if (whileRetries < 5) {
							// if (retries <
							// Integer.parseInt(CONFIG.getProperty("genMax_waitTime")))
							// {
							Thread.sleep(1000);
							whileRetries++;
							fnpMymsg("In InvalidSelectorException catch block of main while loop of search suppliers --" + whileRetries + " --and error thrown is --" + NSEE.getMessage());

							fnpMymsg("@Pradeep---due to invalidSelector Exception , now going to login again for this UserName --" + UserName);
							fnpLaunchBrowserAndLoginDirectNSFOnline(UserName, loginPassword);
							// continue;
						} else {
							throw NSEE;
						}
					}
					
					
					
					
					
					
					
					catch (Throwable t) {
/*
						if (whileRetries < 5) {
							Thread.sleep(1000);
							whileRetries++;
							fnpMymsg("In Throwable t catch block of main while loop of search suppliers --" + whileRetries);
							//fnpMymsg("So login again.");
							//fnpLaunchBrowserAndLoginDirectNSFOnline(UserName, loginPassword);
							// continue;
						} else {
							throw t;
						}
						
						*/
						throw t;

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

}
