package nsf.ecap.Direct_NSFOnline_suite;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.TimeUnit;

import nsf.ecap.MonitorPlan_Suite.TestSuiteBase_MonitorPlan;
import nsf.ecap.util.TestDataProvider;
import nsf.ecap.util.TestUtil;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Direct_NSFO_Verify_CARs_Audits extends
		TestSuiteBase_Direct_NSFOnline {

	// public static boolean NSFOnlineVersionScreenshot = true;

	// Runmode of test case in a suite
	@BeforeTest
	public void checkTestSkip() throws Exception {
		count = -1;
		BS_NSFO_01 = new Direct_NSFOnline_Search_Docs();
		BS_NSFO_01.checkTestSkip(this.getClass().getSimpleName());

		fnpMymsg("################################## [BS-04] Direct_NSFO_Verify_CARs_Audits");
	}

	@Test(dataProviderClass = TestDataProvider.class, dataProvider = "Direct_NSFO_Verify_CARs_Audits")
	public void Direct_NSFO_Verify_CARs_Audits(Hashtable<String, String> table) throws Throwable {
		currentClassNameInSimpleText = this.getClass().getSimpleName();
		count++;

		String UserName = table.get("UserName").split("=")[1].trim();

		// int RecordYear = Integer.parseInt(table.get("Record_Year").trim());
		String RecordYear = table.get("Record_Year").trim();

		boolean customer_6M330 = false;
		boolean customer_C0002618 = false;
		boolean ExcellentFlag = false;
		boolean PassFlag = false;
		boolean FailFlag = false;
		boolean GoodFlag = false;
		boolean Approved = true;
		boolean Overdue = true;

		String PaginationText = "";
		String CARSummary_ApprovedTextString = "";

		Integer AccountSummary_TotalCARs = 0;
		Integer CarGraphTotal = 0;
		Integer Good = 0;
		Integer Excellent = 0;
		Integer Pass = 0;
		Integer Fail = 0;

		Integer CAR_x = 0;
		Integer CAR_y = 0;
		Integer CARSummary_Approved = 0;
		Integer CARSummary_Overdue = 0;

		String CaseSerialNo = table.get("Serial_No").split("=")[1];

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

		try {

			String loginPassword = table.get("Password").split("=")[1].trim();

			hashXlData = new HashMap(table);

			fnpLaunchBrowserAndLoginDirectNSFOnline(UserName, loginPassword);

			/* if(table.get("Customer_No").contains("6M330")){ customer_6M330 =
			 * true; }
			 * 
			 * if(table.get("Customer_No").contains("C0002618")){
			 * customer_C0002618 = true; } */

			driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(Long.parseLong(CONFIG.getProperty("pageLoadTime")), TimeUnit.SECONDS);

			String customerNumber = table.get("Customer_No").trim();
			Thread.sleep(7000);

			if (!(fnpCheckElementDisplayedImmediately("NsfOnline_Go_Bttn"))) {
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				int whileLoopCount = 0;
				while (true) {
					whileLoopCount++;
					try {

						fnpMymsg("@Pradeep---in try block for clicking customer--" + customerNumber + " for chance --" + whileLoopCount);
						jse = (JavascriptExecutor) driver;
						WebElement openLink = driver.findElement(By.xpath(".//tr/td[text()='" + customerNumber + "']/../td[1]/a"));
						jse.executeScript("arguments[0].click();", openLink);
						fnpMymsg("@Pradeep---clicked successfully customer --" + customerNumber);
						break;
					} catch (Throwable t) {
						fnpMymsg("@Pradeep---in catch block for clicking customer--" + customerNumber + " for chance --" + whileLoopCount);
						// if (whileLoopCount
						// >Integer.parseInt(CONFIG.getProperty("genMax_waitTime")))
						// {
						if (whileLoopCount > 5) {
							throw new Exception(t);
							// break;
						}
						Thread.sleep(1000);
					}

				}

				Thread.sleep(10000);
			}

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
						if (iwhileCounter < 5) {
							fnpLaunchBrowserAndLoginDirectNSFOnline(UserName, loginPassword);
						} else {
							//throw new Exception(te.getStackTrace());
							throw new Exception(te.getMessage());
						}

					}
				}
			}

			fnpSimpleSelectList_DirectNSFOnline("NsfOnline_Home_AllRound_DD", RecordYear);

			fnpClick_OR_DirectNSFOnline("NsfOnline_Go_Bttn");

			// CAR summary > click on the Approved CAR actions link.
			WebElement CarSummary = fnsGet_OR_NsfOnline_ObjectX("NsfOnline_CARSummary_FirstGraph_DivXpath");
			List<WebElement> CarSummaryElements = CarSummary.findElements(By.tagName("area"));

			int i = 0;
			for (WebElement Elements : CarSummaryElements) {

				String CARSummary_MouseOverAttributeTextArray = (CarSummaryElements.get(i).getAttribute("onmouseover"));
				// System.out.println("(CARSummary_MouseOverAttributeTextArray.toString())=========="+(CARSummary_MouseOverAttributeTextArray.toString()));
				if (CARSummary_MouseOverAttributeTextArray.contains("Approved")) {
					if (Approved) {
						String[] CARSummary_ApprovedTextArray = (CarSummaryElements.get(i).getAttribute("onmouseover")).split("\\,");
						CARSummary_ApprovedTextString = CARSummary_ApprovedTextArray[2].toString();
						CARSummary_ApprovedTextString = CARSummary_ApprovedTextString.split("\\'")[1];
						CARSummary_ApprovedTextString = CARSummary_ApprovedTextString.split("Approved")[0].trim();
						CARSummary_Approved = Integer.parseInt(CARSummary_ApprovedTextString);
						fnpMymsg("CARSummary_ApprovedTextString=" + CARSummary_ApprovedTextString);

						Point CAR_xy = Elements.getLocation();
						// System.out.println("Point="+CAR_xy);
						// System.out.println("");
						CAR_x = CAR_xy.getX();
						CAR_y = CAR_xy.getY();
						// System.out.println("x="+CAR_x+" y="+CAR_y);

						Approved = false;

						/* break; */
					}
				}

				if (CARSummary_MouseOverAttributeTextArray.contains("Overdue")) {
					if (Overdue) {
						String[] CARSummary_OverdueTextArray = (CarSummaryElements.get(i).getAttribute("onmouseover")).split("\\,");
						String CARSummary_OverdueTextString = CARSummary_OverdueTextArray[2].toString();
						CARSummary_OverdueTextString = CARSummary_OverdueTextString.split("\\'")[1];
						CARSummary_OverdueTextString = CARSummary_OverdueTextString.split("Overdue")[0].trim();
						CARSummary_Overdue = Integer.parseInt(CARSummary_OverdueTextString);
						fnpMymsg("CARSummary_OverdueTextString=" + CARSummary_OverdueTextString);

						Overdue = false;

						/* break; */

					}
				}

				i++;
			}

			CarGraphTotal = CARSummary_Approved + CARSummary_Overdue;
			fnpMymsg("CarGraphTotal============" + CarGraphTotal);

			if (!(CarGraphTotal > 0)) {
				throw new Exception("Graph is not loaded properly for UserName --" + UserName + ". See screenshot.");

			}

			// Clicking on Approved Graph
			Actions act5 = new Actions(driver);
			act5.moveToElement(CarSummary, CAR_x, CAR_y).click().build().perform();
			fnpMymsg("PASSED == Successfully click on 'Approved Corrective Action' of the Graph.");

			// fnpMymsg("@Pradeep--just before checking loading wait");
			fnpLoading_wait_InDirectNSFOnline();
			// fnpMymsg("@Pradeep--just after checking loading wait");

			fnpWaitForVisible("NsfOnline_CorrectiveActionSearchResults_PaginationDD");
			// fnpMymsg("@Pradeep--just after checking fnpWaitForVisible for NsfOnline_CorrectiveActionSearchResults_PaginationDD");
			WebElement PaginationDD = fnpGetORObjectX("NsfOnline_CorrectiveActionSearchResults_PaginationDD");
			// fnpMymsg("@Pradeep---before list");
			List<WebElement> PaginationDD_ElementList = PaginationDD.findElements(By.tagName("option"));
			// fnpMymsg("@Pradeep---after list");
			for (WebElement PaginationDD_EleList : PaginationDD_ElementList) {
				if ((PaginationDD_EleList.getText()).contains("Show")) {
					PaginationText = (PaginationDD_EleList.getText().split("All")[1]).trim();

					break;
				}
			}

			// fnsMove_To_MouseOver("NsfOnline_CorrectiveActionSearchResults_PaginationDD");
			fnpMouseHover("NsfOnline_CorrectiveActionSearchResults_PaginationDD");
			Thread.sleep(1000);
			assert CARSummary_ApprovedTextString.equalsIgnoreCase(PaginationText) : "FAILED == CAR Summary Graph Count(" + CARSummary_ApprovedTextString + ") is not Matched with Pagination DropDown Count(" + PaginationText + "), Please refer screen shot >> PaginationCountMatchFail" + fnsScreenShot_Date_format();
			fnpMymsg("PASSED == CAR Summary Graph Count(" + CARSummary_ApprovedTextString + ") is Matched with Pagination DropDown Count(" + PaginationText + ").");

			fnpClick_OR_DirectNSFOnline("NSFOnline_HomeMenu");

			String AccountSummary_TotalCARsString = fnpReturn_IntegerValue_From_AduitHomeAccountSummary_WebTableByMatchingRowText("NsfOnline_Home_AccountSummary_TableHeader", "Total CARs").trim();
			AccountSummary_TotalCARs = Integer.parseInt(AccountSummary_TotalCARsString);

			assert CarGraphTotal.equals(AccountSummary_TotalCARs) : "FAILED == Account Summary Total CARs(" + AccountSummary_TotalCARs + ") is not matched with Graph CAR Summary Total(" + CarGraphTotal + ") on the graph, Please refer screen shot >> TotalCarsCountMatchFail" + fnsScreenShot_Date_format();
			fnpMymsg("PASSED == Account Summary Total CARs(" + AccountSummary_TotalCARs + ") is matched with Graph CAR Summary Total(" + CarGraphTotal + ") on the graph.");

			fnpClick_OR_DirectNSFOnline("NsfOnline_Top10NonCompliance_Tab");

			// Pradeep--NSFOnline_Top10NonCompliance_Result_TableHeader_1stRow
			Integer Top10NonCompliance_Total_ColumnNo = fnsReturn_ColumnNo_By_MatchingColumnNameText("NSFOnline_Top10NonCompliance_Result_TableHeader_1stRow", "Total", "th");
			Integer Top10NonCompliance_NCCount_ColumnNo = fnsReturn_ColumnNo_By_MatchingColumnNameText("NSFOnline_Top10NonCompliance_Result_TableHeader_1stRow", "NC Count", "th");
			Integer Top10NonCompliance_PercentageNC_ColumnNo = fnsReturn_ColumnNo_By_MatchingColumnNameText("NSFOnline_Top10NonCompliance_Result_TableHeader_1stRow", "% NC", "th");

			for (int RowNo = 2; RowNo < 5; RowNo++) {
				String TotalColumnTextXpath = OR_NsfOnline.getProperty("NSFOnline_Top10NonCompliance_Result_TableHeader") + "/tbody/tr[" + RowNo + "]/td[" + Top10NonCompliance_Total_ColumnNo + "]";
				String NCCountColumnTextXpath = OR_NsfOnline.getProperty("NSFOnline_Top10NonCompliance_Result_TableHeader") + "/tbody/tr[" + RowNo + "]/td[" + Top10NonCompliance_NCCount_ColumnNo + "]";
				String PercentageNCColumnTextXpath = OR_NsfOnline.getProperty("NSFOnline_Top10NonCompliance_Result_TableHeader") + "/tbody/tr[" + RowNo + "]/td[" + Top10NonCompliance_PercentageNC_ColumnNo + "]";

				// String TotalColumnText=
				// TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(TotalColumnTextXpath).trim();
				String TotalColumnText = fnpGetText_NOR(TotalColumnTextXpath).trim();
				Integer Total = Integer.parseInt(TotalColumnText);
				// String NCCountColumnText=
				// TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(NCCountColumnTextXpath).trim();
				String NCCountColumnText = fnpGetText_NOR(NCCountColumnTextXpath).trim();
				Integer NCCount = Integer.parseInt(NCCountColumnText);
				// String PercentageNC=
				// TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(PercentageNCColumnTextXpath).trim();
				String PercentageNC = fnpGetText_NOR(PercentageNCColumnTextXpath).trim();
				// TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element_and_DoubleClick(PercentageNCColumnTextXpath);
				fnpMove_To_Element_and_DoubleClick_NOR(PercentageNCColumnTextXpath);

				// Formula
				DecimalFormat dfWithoutDec = new DecimalFormat("#");
				double PercentageNcCountInt = (double) (NCCount * 100) / Total;
				String PercentageNcCount = dfWithoutDec.format(PercentageNcCountInt);

				assert PercentageNC.equalsIgnoreCase(PercentageNcCount) : "FAILED == % NC count(" + PercentageNC + ") is not displayed correctlly, Please refer screen shot >> PercentageNcCountMatchFail" + fnsScreenShot_Date_format();
				fnpMymsg("PASSED == % NC count(" + PercentageNC + ") is displayed correctlly");

			}

			fnpMouseHover("Footer");

			fnpWaitForVisible("NsfOnline_CARSummary_SecondGraph_DivXpath");
			WebElement AuditInfo_Grades = fnpGetORObjectX("NsfOnline_CARSummary_SecondGraph_DivXpath");
			List<WebElement> AuditInfo_GradesElements = AuditInfo_Grades.findElements(By.tagName("area"));

			int K = 0;
			for (WebElement Elements : AuditInfo_GradesElements) {

				String AuditInfo_Grades_MouseOverAttributeTextArray = (AuditInfo_GradesElements.get(K).getAttribute("onmouseover"));

				// Fetching Good Count from Graph
				if ((AuditInfo_Grades_MouseOverAttributeTextArray.toString()).contains("Good")) {
					String[] AuditInfo_Grades_GoodTextArray = (AuditInfo_GradesElements.get(K).getAttribute("onmouseover")).split("\\,");
					String AuditInfo_Grades_Good = AuditInfo_Grades_GoodTextArray[2].toString();
					AuditInfo_Grades_Good = AuditInfo_Grades_Good.split("\\'")[1];
					AuditInfo_Grades_Good = (AuditInfo_Grades_Good.split("Good")[0]).trim();
					Good = Integer.parseInt(AuditInfo_Grades_Good);
					GoodFlag = true;
					System.out.println("AuditInfo_Grades_Good=" + AuditInfo_Grades_Good);
				}

				// Fetching Excellent Count from Graph
				if ((AuditInfo_Grades_MouseOverAttributeTextArray.toString()).contains("Excellent")) {
					String[] AuditInfo_Grades_ExcellentTextArray = (AuditInfo_GradesElements.get(K).getAttribute("onmouseover")).split("\\,");
					String AuditInfo_Grades_Excellent = AuditInfo_Grades_ExcellentTextArray[2].toString();
					AuditInfo_Grades_Excellent = AuditInfo_Grades_Excellent.split("\\'")[1];
					AuditInfo_Grades_Excellent = (AuditInfo_Grades_Excellent.split("Excellent")[0]).trim();
					Excellent = Integer.parseInt(AuditInfo_Grades_Excellent);
					ExcellentFlag = true;
					System.out.println("AuditInfo_Grades_Excellent=" + AuditInfo_Grades_Excellent);
				}

				// Fetching Pass Count from Graph
				if ((AuditInfo_Grades_MouseOverAttributeTextArray.toString()).contains("Pass")) {
					String[] AuditInfo_Grades_PassTextArray = (AuditInfo_GradesElements.get(K).getAttribute("onmouseover")).split("\\,");
					String AuditInfo_Grades_Pass = AuditInfo_Grades_PassTextArray[2].toString();
					AuditInfo_Grades_Pass = AuditInfo_Grades_Pass.split("\\'")[1];
					AuditInfo_Grades_Pass = (AuditInfo_Grades_Pass.split("Pass")[0]).trim();
					Pass = Integer.parseInt(AuditInfo_Grades_Pass);
					PassFlag = true;
					System.out.println("AuditInfo_Grades_Pass=" + AuditInfo_Grades_Pass);
				}

				// Fetching Fail Count from Graph
				if ((AuditInfo_Grades_MouseOverAttributeTextArray.toString()).contains("Fails")) {

					String[] AuditInfo_Grades_FailTextArray = (AuditInfo_GradesElements.get(K).getAttribute("onmouseover")).split("\\,");
					String AuditInfo_Grades_Fail = AuditInfo_Grades_FailTextArray[2].toString();
					AuditInfo_Grades_Fail = AuditInfo_Grades_Fail.split("\\'")[1];
					AuditInfo_Grades_Fail = (AuditInfo_Grades_Fail.split("Fail")[0]).trim();
					Fail = Integer.parseInt(AuditInfo_Grades_Fail);
					FailFlag = true;
					System.out.println("AuditInfo_Grades_Fail=" + AuditInfo_Grades_Fail);
				}

				K++;

			}

			// Percentage Count in javaCode
			DecimalFormat df = new DecimalFormat("#.00");

			Integer GradesGraphTotal = (Good + Excellent + Fail + Pass);

			if (ExcellentFlag) {
				// % of Excellent --> value should be same as in account
				// summary.
				double Excellent_PercentageDouble = (double) (Excellent * 100) / (GradesGraphTotal);
				String Excellent_Percentage = df.format(Excellent_PercentageDouble);

				String AccountSummary_Excellent = fnpReturn_IntegerValue_From_AduitHomeAccountSummary_WebTableByMatchingRowText("NsfOnline_Home_AccountSummary_TableHeader", "Excellent");
				String AccountSummary_ExcellentSplit = AccountSummary_Excellent.split("\\.")[1];
				String AccountSummary_ExcellentText = "";
				if ((AccountSummary_ExcellentSplit.length()) < 2) {
					AccountSummary_ExcellentText = AccountSummary_Excellent + "0";
				} else {
					AccountSummary_ExcellentText = AccountSummary_Excellent;
				}

				try {
					assert Excellent_Percentage.equalsIgnoreCase(AccountSummary_ExcellentText) : "FAILED == Graph Excellent_Percentage Count(" + Excellent_Percentage + ") is not matched with AccountSummary_Excellent Count(" + AccountSummary_Excellent + "), Please refer screen shot >> ExcellentCountMatchFail" + fnsScreenShot_Date_format();
					fnpMymsg("PASSED == Graph Excellent_Percentage Count(" + Excellent_Percentage + ") is Successfully matched with AccountSummary_Excellent Count(" + AccountSummary_Excellent + ").");
				} catch (Throwable I) {
					fnpDesireScreenshot("ExcellentCountMatchFail");
					// fnpMymsg(I.getMessage());
					throw new Exception(I.getMessage());
				}
			}

			if (GoodFlag) {
				// % of Good --> value should be same as in account summary.
				double Good_PercentageDouble = (double) (Good * 100) / (GradesGraphTotal);
				String Good_Percentage = df.format(Good_PercentageDouble);

				String AccountSummary_Good = fnpReturn_IntegerValue_From_AduitHomeAccountSummary_WebTableByMatchingRowText("NsfOnline_Home_AccountSummary_TableHeader", "Good");
				String AccountSummary_GoodSplit = AccountSummary_Good.split("\\.")[1];
				String AccountSummary_GoodText = "";
				if ((AccountSummary_GoodSplit.length()) < 2) {
					AccountSummary_GoodText = AccountSummary_Good + "0";
				} else {
					AccountSummary_GoodText = AccountSummary_Good;
				}

				try {
					assert Good_Percentage.equalsIgnoreCase(AccountSummary_GoodText) : "FAILED == Graph Good_Percentage Count(" + Good_Percentage + ") is not matched with AccountSummary_Good Count(" + AccountSummary_Good + "), Please refer screen shot >> GoodCountMatchFail" + fnsScreenShot_Date_format();
					fnpMymsg("PASSED == Graph Good_Percentage Count(" + Good_Percentage + ") is Successfully matched with AccountSummary_Good Count(" + AccountSummary_Good + ").");
				} catch (Throwable I) {
					fnpDesireScreenshot("GoodCountMatchFail");
					// fnpMymsg(I.getMessage());
					throw new Exception(I.getMessage());
				}
			}

			if (PassFlag) {
				// % of Pass --> value should be same as in account summary.
				double Pass_PercentageDouble = (double) (Pass * 100) / (GradesGraphTotal);
				String Pass_Percentage = df.format(Pass_PercentageDouble);

				String AccountSummary_Pass = fnpReturn_IntegerValue_From_AduitHomeAccountSummary_WebTableByMatchingRowText("NsfOnline_Home_AccountSummary_TableHeader", "Pass");
				String AccountSummary_PassSplit = AccountSummary_Pass.split("\\.")[1];
				String AccountSummary_PassText = "";
				if ((AccountSummary_PassSplit.length()) < 2) {
					AccountSummary_PassText = AccountSummary_Pass + "0";
				} else {
					AccountSummary_PassText = AccountSummary_Pass;
				}
				try {
					assert Pass_Percentage.equalsIgnoreCase(AccountSummary_PassText) : "FAILED == Graph Pass_Percentage Count(" + Pass_Percentage + ") is not matched with AccountSummary_Pass Count(" + AccountSummary_Pass + "), Please refer screen shot >> PassCountMatchFail" + fnsScreenShot_Date_format();
					fnpMymsg("PASSED == Graph Pass_Percentage Count(" + Pass_Percentage + ") is Successfully matched with AccountSummary_Pass Count(" + AccountSummary_Pass + ").");
				} catch (Throwable I) {
					fnpDesireScreenshot("PassCountMatchFail");
					// fnpMymsg(I.getMessage());
					throw new Exception(I.getMessage());
				}
			}

			if (FailFlag) {
				// % of Fail --> value should be same as in account summary.
				double Fail_PercentageDouble = (double) (Fail * 100) / (GradesGraphTotal);
				String Fail_Percentage = df.format(Fail_PercentageDouble);

				String AccountSummary_Fail = fnpReturn_IntegerValue_From_AduitHomeAccountSummary_WebTableByMatchingRowText("NsfOnline_Home_AccountSummary_TableHeader", "Fail");
				String AccountSummary_FailSplit = AccountSummary_Fail.split("\\.")[1];
				String AccountSummary_FailText = "";
				if ((AccountSummary_FailSplit.length()) < 2) {
					AccountSummary_FailText = AccountSummary_Fail + "0";
				} else {
					AccountSummary_FailText = AccountSummary_Fail;
				}
				try {
					assert Fail_Percentage.equalsIgnoreCase(AccountSummary_FailText) : "FAILED == Graph Fail_Percentage Count(" + Fail_Percentage + ") is not matched with AccountSummary_Fail Count(" + AccountSummary_Fail + "), Please refer screen shot >> FailCountMatchFail" + fnsScreenShot_Date_format();
					fnpMymsg("FailED == Graph Fail_Percentage Count(" + Fail_Percentage + ") is Successfully matched with AccountSummary_Fail Count(" + AccountSummary_Fail + ").");
				} catch (Throwable I) {
					fnpDesireScreenshot("FailCountMatchFail");
					// fnpMymsg(I.getMessage());
					throw new Exception(I.getMessage());
				}
			}

			// Verify that total of all graphs count under audit information is
			// equal to the "Audits Performed"
			String AuditsPerformedText = fnpReturn_IntegerValue_From_AduitHomeAccountSummary_WebTableByMatchingRowText("NsfOnline_Home_AccountSummary_TableHeader", "Audits Performed");
			Integer AuditsPerformed = Integer.parseInt(AuditsPerformedText);
			try {
				assert AuditsPerformed.equals(GradesGraphTotal) : "FAILED == Graph Total Count(" + GradesGraphTotal + ") is not matched with AuditPerformed Count(" + AuditsPerformed + "), Please refer screen shot >> GraphTotalCountMatchFail" + fnsScreenShot_Date_format();
				fnpMymsg("PASSED == Graph Total Count(" + GradesGraphTotal + ") is Successfully matched with AuditPerformed Count(" + AuditsPerformed + ").");
				Thread.sleep(2000);
			} catch (Throwable I) {
				fnpDesireScreenshot("GraphTotalCountMatchFail");
				// fnpMymsg(I.getMessage());
				throw new Exception(I.getMessage());
			}

			try {
				// Go to the Top 10>Verify that the First value of Current Round
				// should be equal to the Top Score of the Account summary.
				
				
				fnpClick_OR_DirectNSFOnline("NSFOnline_AuditInfo_Top10_Tab");
				if (customer_6M330) {
					// fnsGet_Element_Enabled("NSFOnline_AuditInfo_Top10_Go_Bttn");
					fnpWaitForVisible("NSFOnline_AuditInfo_Top10_Go_Bttn");
				}

				// Thread.sleep(2000);

				// NSFOnline_AuditInfo_Top10_Result
				fnpWaitForVisible("NSFOnline_AuditInfo_Top10_Result_1stRow_3rdCell");
				// String
				// CurrentRound=fnsGet_Field_TEXT("NSFOnline_AuditInfo_Top10_Result_1stRow_3rdCell").trim();
				
				String CurrentRound = fnpGetText_OR("NSFOnline_AuditInfo_Top10_Result_1stRow_3rdCell").trim();
				CurrentRound = CurrentRound.split("\\%")[0];
				
				//fnpMove_To_Element_and_DoubleClick_NOR(OR.getProperty("NSFOnline_AuditInfo_Top10_Result_1stRow_3rdCell"));
				fnpMouseHover("NSFOnline_AuditInfo_Top10_Result_1stRow_3rdCell");
				String TopScore = fnpReturn_IntegerValue_From_AduitHomeAccountSummary_WebTableByMatchingRowText("NsfOnline_Home_AccountSummary_TableHeader", "Top Score");
				TopScore = TopScore.split("\\.")[0];
				try {
					assert CurrentRound.equalsIgnoreCase(TopScore) : "FAILED == CurrentRound(" + CurrentRound + "%) is not matched with AccountSummary TopScore(" + TopScore + "%), Please refer screen shot >> TopScoreMatchFail" + fnsScreenShot_Date_format();
					fnpMymsg("PASSED == Successfully Matched Current Round(" + CurrentRound + "%) with AccountSummary TopScore(" + TopScore + "%).");
					Thread.sleep(1500);
				} catch (Throwable I) {
					fnpDesireScreenshot("TopScoreMatchFail");
					// fnpMymsg(I.getMessage());
					throw new Exception(I.getMessage());
				}

			} catch (Throwable I) {
				driver.close();
				fnpMymsg("PASSED == Successfully Close NSFOnline Window.");
				driver.switchTo().window(MainWindowHandle);
				fnpMymsg("PASSED == Successfully Switch to Insight Window.");
				// fnpMymsg("AuditInfo Top10 : "+I.getMessage());
				throw new Exception("AuditInfo Top10 : " + I.getMessage());
			}

			fnpClick_OR_DirectNSFOnline("NSFOnline_AuditInfo_MostImproved_Tab");

			if (customer_6M330) {
				// fnsGet_Element_Enabled("NSFOnline_AuditInfo_MostImproved_Go_Bttn");
				fnpWaitForVisible("NSFOnline_AuditInfo_MostImproved_Go_Bttn");
			}

			// NSFOnline_AuditInfo_MostImproved_TableHeader
			fnpWaitForVisible("NSFOnline_AuditInfo_MostImproved_TableHeader");

			for (int row = 2; row < 25; row++) {
				String CurrentRoundColumnxpath = (OR_NsfOnline.getProperty("NSFOnline_AuditInfo_MostImproved_TableHeader") + "/tbody/tr[" + row + "]/td[3]");
				String PreviousRoundColumnxpath = (OR_NsfOnline.getProperty("NSFOnline_AuditInfo_MostImproved_TableHeader") + "/tbody/tr[" + row + "]/td[4]");
				// Thread.sleep(400);
				// if(driver.findElements(By.xpath(CurrentRoundColumnxpath)).size()>0){
				if (fnpFindNoOfElementsWithThisXpath_NOR(CurrentRoundColumnxpath) > 0) {

					// Table Current Round Data Fetch
				//	String CurrentRoundColumnText = TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(CurrentRoundColumnxpath).trim();
				//	CurrentRoundColumnText = CurrentRoundColumnText.split("\\%")[0];
				//	Integer CurrentRoundColumn = Integer.parseInt(CurrentRoundColumnText);

					// Table Previous Round Data Fetch
				//	String PreviousRoundColumnText = TestSuiteBase_MonitorPlan.WithOut_OR_fnsGet_Field_TEXT(PreviousRoundColumnxpath).trim();
			//		PreviousRoundColumnText = PreviousRoundColumnText.split("\\%")[0];
				//	Integer PreviousRoundColumn = Integer.parseInt(PreviousRoundColumnText);
//
				//	TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element_and_DoubleClick(CurrentRoundColumnxpath);
			//		TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element_and_DoubleClick(PreviousRoundColumnxpath);
					try {
				//		assert CurrentRoundColumn > PreviousRoundColumn : "FAILED == Current Round(" + CurrentRoundColumn + ") is not Greater than from Previous Round(" + PreviousRoundColumn + ") for Table Row(" + row + "), Please refer screen shot >> GreaterThanMatchFail" + fnsScreenShot_Date_format();
				//		fnpMymsg("PASSED == Successfully Verify Current Round(" + CurrentRoundColumn + ") is Greater than from Previous Round(" + PreviousRoundColumn + ") for Table Row(" + row + ").");
					} catch (Throwable I) {
						fnpDesireScreenshot("GreaterThanMatchFail");
						// fnpMymsg(I.getMessage());
						throw new Exception(I.getMessage());
					}
				} else {
					break;
				}
			}

			// Go to the Bottom 10>Verify that the First value of Current Round
			// should be equal to the Bottom Score of the Account summary.
			fnpClick_OR_DirectNSFOnline("NSFOnline_AuditInfo_Bottom10_Tab");

			if (customer_6M330) {
				// fnsGet_Element_Enabled("NSFOnline_AuditInfo_Bottom10_Go_Bttn");
				fnpWaitForVisible("NSFOnline_AuditInfo_Bottom10_Go_Bttn");
			}

			// Thread.sleep(2000);

			// Pradeep NSFOnline_AuditInfo_Bottom10_Result_1stRow_3rdCell
			fnpWaitForVisible("NSFOnline_AuditInfo_Bottom10_Result_1stRow_3rdCell");
			String BottomCurrentRound = fnsGet_Field_TEXT("NSFOnline_AuditInfo_Bottom10_Result_1stRow_3rdCell").trim();
			BottomCurrentRound = BottomCurrentRound.split("\\%")[0];
		//	TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element_and_DoubleClick(OR_NsfOnline.getProperty("NSFOnline_AuditInfo_Bottom10_Result_1stRow_3rdCell"));
			// fnsMove_To_Element_and_DoubleClick("NSFOnline_AuditInfo_Bottom10_Result_1stRow_3rdCell");
			String BottomScore = fnpReturn_IntegerValue_From_AduitHomeAccountSummary_WebTableByMatchingRowText("NsfOnline_Home_AccountSummary_TableHeader", "Bottom Score");
			BottomScore = BottomScore.split("\\.")[0];
			try {
				assert BottomCurrentRound.equalsIgnoreCase(BottomScore) : "FAILED == BottomCurrentRound(" + BottomCurrentRound + "%) is not matched with AccountSummary BottomScore(" + BottomScore + "%), Please refer screen shot >> BottomScoreMatchFail" + fnsScreenShot_Date_format();
				fnpMymsg("PASSED == Successfully Matched Bottom Current Round(" + BottomCurrentRound + "%) with AccountSummary BottomScore(" + BottomScore + "%).");
				Thread.sleep(1500);
			} catch (Throwable I) {
				fnpDesireScreenshot("BottomScoreMatchFail");
				// fnpMymsg(I.getMessage());
				throw new Exception(I.getMessage());
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
