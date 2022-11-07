package nsf.ecap.Direct_NSFOnline_suite;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.TimeUnit;

import nsf.ecap.Grip_Suite.TestSuiteBase_Grip;
import nsf.ecap.IssueMgt_Suite.TestSuiteBase_IM;
import nsf.ecap.MonitorPlan_Suite.TestSuiteBase_MonitorPlan;
import nsf.ecap.util.TestDataProvider;
import nsf.ecap.util.TestUtil;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Direct_NSFO_SCA_IM_Validations extends
		TestSuiteBase_Direct_NSFOnline {

	public static String CaseSerialNo = null;
	public static String ScenaioName = null;

	public static boolean ViewIssueFlag = false;

	public static String TitleShortDescriptionText = "[BS-05] NSFOnline SCA IM Validations-Case8, D/T=";
	public static String IssueType_DD_Value = null;
	public static String IssueSubType_DD_Value = null;
	public static String Issue_ID = "I0059310";
	public static String Confirmyoursite_LK_SiteNo = null;

	public static String SearchResult_GetText = null;

	// Runmode of test case in a suite
	@BeforeTest
	public void checkTestSkip() throws Exception {
		count = -1;
		BS_NSFO_01 = new Direct_NSFOnline_Search_Docs();
		BS_NSFO_01.checkTestSkip(this.getClass().getSimpleName());

		fnpMymsg("################################## [BS-05] Direct_NSFO_SCA_IM_Validations");
	}

	@Test(dataProviderClass = TestDataProvider.class, dataProvider = "Direct_NSFO_SCA_IM_Validations")
	public void Direct_NSFO_SCA_IM_Validations(Hashtable<String, String> table) throws Throwable {
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

		try {

			// AuditType=table.get("Types_Of_Audit");

			String loginPassword = table.get("Password").split("=")[1].trim();
			ScenaioName = table.get("Scenario_Name").trim();

			hashXlData = new HashMap(table);

			fnpLaunchBrowserAndLoginDirectNSFOnline(UserName, loginPassword);

			driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(Long.parseLong(CONFIG.getProperty("pageLoadTime")), TimeUnit.SECONDS);

			if (ScenaioName.contains("IssueManagement_Create_Issue")) {

				Thread.sleep(1000);
				Actions act = new Actions(driver);
				// act.moveByOffset(0,
				// 0).click().sendKeys(Keys.TAB).sendKeys(Keys.ENTER).build().perform();
				Thread.sleep(10000);

				fnpMymsg("PASSED == Successfully Click on the 'Open' link");
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

			if (ScenaioName.contains("IssueManagement_Create_Issue")) {

				Confirmyoursite_LK_SiteNo = table.get("Search_IDs").split(":")[0].trim();
				Confirmyoursite_LK_SiteNo = Confirmyoursite_LK_SiteNo.split("=")[1].trim();
				Confirmyoursite_LK_SiteNo = Confirmyoursite_LK_SiteNo.split("\\)")[0].trim();

				IssueType_DD_Value = table.get("Search_IDs").split(":")[1].trim();
				IssueType_DD_Value = IssueType_DD_Value.split("=")[1].trim();
				IssueType_DD_Value = IssueType_DD_Value.split("\\)")[0].trim();

				IssueSubType_DD_Value = table.get("Search_IDs").split(":")[2].trim();
				IssueSubType_DD_Value = IssueSubType_DD_Value.split("=")[1].trim();
				IssueSubType_DD_Value = IssueSubType_DD_Value.split("\\)")[0].trim();

				fnpAjax_Link_Click_By_Passing_TwoAjaxXPathx("NSFOnline_Issues_TopMenu_Ajax_Link", "NSFOnline_Issues_Ajax_Link");
				fnpLoading_wait_InDirectNSFOnline();

				fnpWaitForVisible("NSFOnline_ExpandSearchCriteriaLink");

				fnpClick_OR_DirectNSFOnline("NSFOnline_CreatIssue_Bttn");

				fnpClick_OR_DirectNSFOnline("NSFOnline_CreatIssue_SiteInfo_Confirmyoursite_LK");

				String NSFOnlineoriginalHandle = driver.getWindowHandle();
				driver.switchTo().frame(0);
				fnpMymsg("PASSED == Successfully switch to Site LookUp frame.");

				/* fnsGet_Element_Enabled(
				 * "NSFOnline_CreatIssue_SiteInfo_Confirmyoursite_LK_SiteNo_Text"
				 * ); fnsWait_and_Type(
				 * "NSFOnline_CreatIssue_SiteInfo_Confirmyoursite_LK_SiteNo_Text"
				 * , Confirmyoursite_LK_SiteNo); */
				fnpType("OR", "NSFOnline_CreatIssue_SiteInfo_Confirmyoursite_LK_SiteNo_Text", Confirmyoursite_LK_SiteNo);

				/* fnsGet_Element_Enabled(
				 * "NSFOnline_CreatIssue_SiteInfo_Confirmyoursite_LK_Search_Bttn"
				 * ); fnsWait_and_Click(
				 * "NSFOnline_CreatIssue_SiteInfo_Confirmyoursite_LK_Search_Bttn"
				 * ); */
				fnpClick_OR_DirectNSFOnline("NSFOnline_CreatIssue_SiteInfo_Confirmyoursite_LK_Search_Bttn");

				/* fnsGet_Element_Enabled(
				 * "NSFOnline_CreatIssue_SiteInfo_Confirmyoursite_LK_SelectRadio_Bttn"
				 * ); fnsWait_and_Click(
				 * "NSFOnline_CreatIssue_SiteInfo_Confirmyoursite_LK_SelectRadio_Bttn"
				 * ); */
				fnpClick_OR_DirectNSFOnline("NSFOnline_CreatIssue_SiteInfo_Confirmyoursite_LK_SelectRadio_Bttn");

				/* fnsGet_Element_Enabled(
				 * "NSFOnline_CreatIssue_SiteInfo_Confirmyoursite_LK_SelectReturn_Bttn"
				 * ); fnsWait_and_Click(
				 * "NSFOnline_CreatIssue_SiteInfo_Confirmyoursite_LK_SelectReturn_Bttn"
				 * );
				 * TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline
				 * (3); */
				fnpClick_OR_DirectNSFOnline("NSFOnline_CreatIssue_SiteInfo_Confirmyoursite_LK_SelectReturn_Bttn");

				driver.switchTo().defaultContent();

				/* TestSuiteBase_MonitorPlan.
				 * WithOut_OR_fnDD_SelectValue_By_SelectClass(OR_IM.getProperty(
				 * "NSFOnline_CreatIssue_SiteInfo_IssueType_DD_Click"),
				 * IssueType_DD_Value);
				 * TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline
				 * (2); */
		//		TestSuiteBase_MonitorPlan.WithOut_OR_fnDD_SelectValue_By_SelectClass(OR_IM.getProperty("NSFOnline_CreatIssue_SiteInfo_IssueSubType_DD_Click"), IssueSubType_DD_Value);

				/* fnsGet_Element_Enabled(
				 * "NSFOnline_CreatIssue_SiteInfo_CreateIssue_Bttn");
				 * fnsWait_and_Click
				 * ("NSFOnline_CreatIssue_SiteInfo_CreateIssue_Bttn");
				 * Thread.sleep(3000); */
				fnpClick_OR_DirectNSFOnline("NSFOnline_CreatIssue_SiteInfo_CreateIssue_Bttn");

				fnpWaitForVisible("NSFOnline_Validation_Message");
				Issue_ID = fnsGet_Field_TEXT("NSFOnline_Validation_Message");
				try {
					assert ((Issue_ID.toLowerCase()).contains("success")) : "FAILED == Issue ID is not created, Getting Error<" + Issue_ID + ">, Please refer screenshot >> IssueCreateFail" + fnsScreenShot_Date_format();
				} catch (Throwable t) {
					fnpDesireScreenshot("IssueCreateFail");
					throw new Exception(t.getMessage());
				}
				Issue_ID = Issue_ID.split("Issue")[1].trim();
				Issue_ID = Issue_ID.split("is")[0].trim();
				fnpMymsg("PASSED == **************** Successfully created Issue and Issue ID is " + Issue_ID);

				/* fnsGet_Element_Enabled("NSFOnline_CreatIssue_SiteInfo_Next_Bttn"
				 * );
				 * fnsWait_and_Click("NSFOnline_CreatIssue_SiteInfo_Next_Bttn");
				 * Thread.sleep(2000);
				 * TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline
				 * (1); */
				fnpClick_OR_DirectNSFOnline("NSFOnline_CreatIssue_SiteInfo_Next_Bttn");

				fnpWaitForVisible("NSFOnline_CreatIssue_SummaryInfo_SaveNext_Bttn");

				fnpWaitForVisible("NSFOnline_CreatIssue_SummaryInfo_IssueTitleReasonforIssue_Text");

				Thread.sleep(1000);
				fnpClick_OR_DirectNSFOnline("NSFOnline_CreatIssue_SummaryInfo_IssueTitleReasonforIssue_Text");
				// fnsWait_and_Type("NSFOnline_CreatIssue_SummaryInfo_IssueTitleReasonforIssue_Text",
				// TitleShortDescriptionText+TestSuiteBase_IM.fnIssueCreationText_Date_format());
				fnpType("OR", "NSFOnline_CreatIssue_SummaryInfo_IssueTitleReasonforIssue_Text", TitleShortDescriptionText);

				/* fnsGet_Element_Enabled(
				 * "NSFOnline_CreatIssue_SummaryInfo_DateIncidentOccurred_calander_Bttn"
				 * ); fnsWait_and_Click(
				 * "NSFOnline_CreatIssue_SummaryInfo_DateIncidentOccurred_calander_Bttn"
				 * ); Thread.sleep(3000); */
				fnpClick_OR_DirectNSFOnline("NSFOnline_CreatIssue_SummaryInfo_DateIncidentOccurred_calander_Bttn");

				driver.switchTo().frame(0);
	//			TestSuiteBase_IM.fncCalendarDatePicker_BySelectValues_From_DropDown_NSFOnline(TestSuiteBase_Grip.fnsReturn_Requried_Day(-2), TestSuiteBase_Grip.fnsReturn_Requried_Month("MMMM", -1), null);
				driver.switchTo().defaultContent();

				/* fnsGet_Element_Enabled(
				 * "NSFOnline_CreatIssue_SummaryInfo_TimeIncidentOccurred_Text"
				 * ); fnsWait_and_Type(
				 * "NSFOnline_CreatIssue_SummaryInfo_TimeIncidentOccurred_Text",
				 * TestSuiteBase_IM.fnReturn_Requried_Time_HHmm(0)); */
		//		fnpType("OR", "NSFOnline_CreatIssue_SummaryInfo_TimeIncidentOccurred_Text", TestSuiteBase_IM.fnReturn_Requried_Time_HHmm(0));

				/* fnsGet_Element_Enabled(
				 * "NSFOnline_CreatIssue_SummaryInfo_YourName_Text");
				 * fnsWait_and_Type
				 * ("NSFOnline_CreatIssue_SummaryInfo_YourName_Text",
				 * "Testscriptuser"); */

				fnpType("OR", "NSFOnline_CreatIssue_SummaryInfo_YourName_Text", "Testscriptuser");

				/* fnsGet_Element_Enabled(
				 * "NSFOnline_CreatIssue_SummaryInfo_SaveNext_Bttn");
				 * fnsWait_and_Click
				 * ("NSFOnline_CreatIssue_SummaryInfo_SaveNext_Bttn");
				 * TestSuiteBase_TraQtion
				 * .fnsLoading_Progressingwait_TraQtionOnline(5); */

				fnpClick_OR_DirectNSFOnline("NSFOnline_CreatIssue_SummaryInfo_SaveNext_Bttn");

				/* fnsGet_Element_Enabled(
				 * "NSFOnline_CreatIssue_DetailsInfo_SaveNext_Bttn");
				 * fnsWait_and_Click
				 * ("NSFOnline_CreatIssue_DetailsInfo_SaveNext_Bttn");
				 * TestSuiteBase_TraQtion
				 * .fnsLoading_Progressingwait_TraQtionOnline(3); */
				fnpClick_OR_DirectNSFOnline("NSFOnline_CreatIssue_DetailsInfo_SaveNext_Bttn");

				/* fnsGet_Element_Enabled(
				 * "NSFOnline_CreatIssue_ProductInfo_YES_Radio_Bttn");
				 * fnsWait_and_Click
				 * ("NSFOnline_CreatIssue_ProductInfo_YES_Radio_Bttn");
				 * TestSuiteBase_TraQtion
				 * .fnsLoading_Progressingwait_TraQtionOnline(3); */

				fnpClick_OR_DirectNSFOnline("NSFOnline_CreatIssue_ProductInfo_YES_Radio_Bttn");

				/* fnsGet_Element_Enabled(
				 * "NSFOnline_CreatIssue_ProductInfo_FreeTextBox");
				 * fnsWait_and_Type
				 * ("NSFOnline_CreatIssue_ProductInfo_FreeTextBox",
				 * TitleShortDescriptionText
				 * +TestSuiteBase_IM.fnIssueCreationText_Date_format()); */
			//	fnpType("OR", "NSFOnline_CreatIssue_ProductInfo_FreeTextBox", TitleShortDescriptionText + TestSuiteBase_IM.fnIssueCreationText_Date_format());

				/* fnsGet_Element_Enabled(
				 * "NSFOnline_CreatIssue_ProductInfo_SaveNext_Bttn");
				 * fnsWait_and_Click
				 * ("NSFOnline_CreatIssue_ProductInfo_SaveNext_Bttn");
				 * TestSuiteBase_TraQtion
				 * .fnsLoading_Progressingwait_TraQtionOnline(3); */

				fnpClick_OR_DirectNSFOnline("NSFOnline_CreatIssue_ProductInfo_SaveNext_Bttn");

				/* fnsGet_Element_Enabled(
				 * "NSFOnline_CreatIssue_DocumentInfo_ConfirmIssue_Bttn");
				 * fnsWait_and_Click
				 * ("NSFOnline_CreatIssue_DocumentInfo_ConfirmIssue_Bttn");
				 * Thread.sleep(5000); */

				fnpClick_OR_DirectNSFOnline("NSFOnline_CreatIssue_DocumentInfo_ConfirmIssue_Bttn");

				fnpWaitForVisible("NSFOnline_Validation_Message");
				String Issue_ID_Success_Text = fnsGet_Field_TEXT("NSFOnline_Validation_Message").trim();

				try {
					assert ((Issue_ID_Success_Text.toLowerCase()).contains(Issue_ID.toLowerCase()) && (Issue_ID_Success_Text.toLowerCase()).contains("success")) : "FAILED == Issue Id is not Genrated, getting error < " + Issue_ID_Success_Text + " >";
					fnpMymsg("PASSED == **************** Successfully verified that Issue Id has been genrated. ( " + Issue_ID + " )");
				} catch (Throwable t) {
					fnpDesireScreenshot("IssueID_Not_Created");
					throw new Exception(t.getMessage());
				}

				/* fnsGet_Element_Enabled("NSFOnline_ExpandSearchCriteriaLink");
				 * fnsWait_and_Click("NSFOnline_ExpandSearchCriteriaLink"); */
				fnpClick_OR_DirectNSFOnline("NSFOnline_ExpandSearchCriteriaLink");

				/* fnsGet_Element_Enabled("NSFOnline_IssueEscalation_SearchID_Input"
				 * );
				 * fnsWait_and_Type("NSFOnline_IssueEscalation_SearchID_Input",
				 * Issue_ID); */

				fnpType("OR", "NSFOnline_IssueEscalation_SearchID_Input", Issue_ID);

				/* fnsGet_Element_Enabled("NSFOnline_IssueEscalation_Search_Bttn"
				 * );
				 * fnsWait_and_Click("NSFOnline_IssueEscalation_Search_Bttn");
				 * TestSuiteBase_TraQtion
				 * .fnsLoading_Progressingwait_TraQtionOnline(3); */

				fnpClick_OR_DirectNSFOnline("NSFOnline_IssueEscalation_Search_Bttn");
				Thread.sleep(1000);
				fnpGetORObjectX("NSFOnline_IssueEscalation_Search_Bttn").sendKeys(Keys.ARROW_DOWN);
				// driver.findElement(By.xpath(OR.getProperty("NSFOnline_IssueEscalation_Search_Bttn"))).sendKeys(Keys.ARROW_DOWN);

			/*//	fnpWaitForVisible("NSFOnline_IssueEscalation_SearchResultTable");
				TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element_BY_OFFSET(OR_NsfOnline.getProperty("NSFOnline_IssueEscalation_SearchResultTable"), 30, 10);

				String IssueId_Link_Xpath = "//a[contains(text(), '" + Issue_ID + "')]";
				TestSuiteBase_MonitorPlan.WithOut_OR_fnClick(IssueId_Link_Xpath);
				TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline(3);*/

				fncClicking_On_NsfOnline_Issue_TABs("NSFOnline_Issue_Summary_TAB", "NSFOnline_EditIssue_SummaryTAB_SummaryInfo_Header", "Summary");

				/* fnsGet_Element_Enabled("NSFOnline_Issue_Edit_Button");
				 * fnsWait_and_Click("NSFOnline_Issue_Edit_Button");
				 * TestSuiteBase_TraQtion
				 * .fnsLoading_Progressingwait_TraQtionOnline(3); */
				fnpClick_OR_DirectNSFOnline("NSFOnline_Issue_Edit_Button");

				/* fnsGet_Element_Enabled(
				 * "NSFOnline_CreatIssue_SummaryInfo_DateIncidentOccurred_calander_Bttn"
				 * ); fnsWait_and_Click(
				 * "NSFOnline_CreatIssue_SummaryInfo_DateIncidentOccurred_calander_Bttn"
				 * ); Thread.sleep(3000); */
				fnpClick_OR_DirectNSFOnline("NSFOnline_CreatIssue_SummaryInfo_DateIncidentOccurred_calander_Bttn");

				driver.switchTo().frame(0);
		//		TestSuiteBase_IM.fncCalendarDatePicker_BySelectValues_From_DropDown_NSFOnline(TestSuiteBase_Grip.fnsReturn_Requried_Day(-3), TestSuiteBase_Grip.fnsReturn_Requried_Month("MMMM", -2), null);
				driver.switchTo().defaultContent();

				fnpWaitForVisible("NSFOnline_CreatIssue_SummaryInfo_TimeIncidentOccurred_Text");
	//			TestSuiteBase_MonitorPlan.WithOut_OR_fnClear(OR_IM.getProperty("NSFOnline_CreatIssue_SummaryInfo_TimeIncidentOccurred_Text"));
			//	fnsWait_and_Type("NSFOnline_CreatIssue_SummaryInfo_TimeIncidentOccurred_Text", TestSuiteBase_IM.fnReturn_Requried_Time_HHmm(-20));

			//	String SummaryInfo_EditedTime = TestSuiteBase_IM.fnReturn_Requried_Time_HHmm(-20);
				String SummaryInfo_EditedDate = fnsGet_OR_NsfOnline_ObjectX("NSFOnline_EditIssue_SummaryTAB_DateIncidentOccurred_Text").getAttribute("value").trim();
				System.out.println("SummaryInfo_EditedDate   ===== " + SummaryInfo_EditedDate);

				/* fnpWaitForVisible(
				 * "NSFOnline_EditIssue_SummaryTAB_SaveReturnToView");
				 * fnsWait_and_Click
				 * ("NSFOnline_EditIssue_SummaryTAB_SaveReturnToView");
				 * TestSuiteBase_TraQtion
				 * .fnsLoading_Progressingwait_TraQtionOnline(3); */
				fnpClick_OR_DirectNSFOnline("NSFOnline_EditIssue_SummaryTAB_SaveReturnToView");

				fncClicking_On_NsfOnline_Issue_TABs("NSFOnline_Issue_Summary_TAB", "NSFOnline_EditIssue_SummaryTAB_SummaryInfo_Header", "Summary");

				String Summary_Tab_Data = fnsGet_Field_TEXT("NSFOnline_Issue_TAB_DataTable").trim();

				try {
					assert (Summary_Tab_Data.contains(SummaryInfo_EditedDate)) : "FAILED == 'Date' is not updated in 'Summary' TAB. Script Edited date<" + SummaryInfo_EditedDate + ">, Please refer Screenshot[SummaryTabUpdateFail" + fnsScreenShot_Date_format();
					String SummaryInfo_EditedDate_Xpath = "//*[text()='" + SummaryInfo_EditedDate + "']";
					// TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element_and_DoubleClick(SummaryInfo_EditedDate_Xpath);
					fnpMove_To_Element_and_DoubleClick_NOR(SummaryInfo_EditedDate_Xpath);
					fnpMymsg("PASSED == **************** Successfully updated 'Time' in summary TAB.");
					Thread.sleep(1000);
				} catch (Throwable t) {
					fnpDesireScreenshot("SummaryTabUpdateFail");
					throw new Exception(t.getMessage());
				}

				try {
			//		assert (Summary_Tab_Data.contains(SummaryInfo_EditedTime)) : "FAILED == 'Time' is not updated in 'Summary' TAB. Script Edited Time<" + SummaryInfo_EditedTime + ">, Please refer Screenshot[SummaryTabUpdateFail" + fnsScreenShot_Date_format();
			//		String SummaryInfo_EditedTime_Xpath = "//*[text()='" + SummaryInfo_EditedTime + "']";
					// TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element_and_DoubleClick(SummaryInfo_EditedTime_Xpath);
			//		fnpMove_To_Element_and_DoubleClick_NOR(SummaryInfo_EditedTime_Xpath);
					fnpMymsg("PASSED == **************** Successfully updated 'Date' in summary TAB.");
					Thread.sleep(1000);
				} catch (Throwable t) {
					fnpDesireScreenshot("SummaryTabUpdateFail");
					throw new Exception(t.getMessage());
				}

				fncClicking_On_NsfOnline_Issue_TABs("NSFOnline_Issue_Detail_TAB", "NSFOnline_EditIssue_DetailTAB_DetailInfo_Header", "Detail");

				fncClicking_On_NsfOnline_Issue_TABs("NSFOnline_Issue_Product_TAB", "NSFOnline_EditIssue_ProductTAB_ProductInfo_Header", "Product");

				fncClicking_On_NsfOnline_Issue_TABs("NSFOnline_Issue_Document_TAB", "NSFOnline_EditIssue_DocumentTAB_DocumentInfo_Header", "Document");

				fncClicking_On_NsfOnline_Issue_TABs("NSFOnline_Issue_Response_TAB", "NSFOnline_EditIssue_ResponseTAB_ResponseInfo_Header", "Response");

			} else {

				// Verify for Good Bad

				String NoOfSets[] = table.get("Search_IDs").split(":");
				fnpMymsg("Sets data are [" + table.get("Search_IDs") + "]. and No. of " + " Ids are == " + NoOfSets.length);

				for (int j = 0; j < NoOfSets.length; j++) {
					ViewIssueFlag = false;

					String Search_IDsSet = NoOfSets[j];
					fnpMymsg("****************************************************************************************************");
					fnpMymsg("************************ Current searching " + " Id is [" + Search_IDsSet + "]  *************************");

					String CurrentSet[] = Search_IDsSet.split(",");
					String Search_IDNo = fnsRemoveFormatting(CurrentSet[0]);
					String GoodBadCondition = fnsRemoveFormatting(CurrentSet[1]).toLowerCase().trim();

					int whileRetries = 0;
					while (true) {
						try {

							if (ScenaioName.contains(("IssueManagement_Client_IssueValidation").trim()) || ScenaioName.contains(("IssueManagement_Supplier_IssueValidation").trim())) {
								fnpAjax_Link_Click_By_Passing_TwoAjaxXPathx("NSFOnline_Issues_TopMenu_Ajax_Link", "NSFOnline_Issues_Ajax_Link");
							}

							// fnpClick_OR_DirectNSFOnline("NSFOnline_ExpandSearchCriteriaLink");
							driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
							driver.findElement(By.xpath(OR.getProperty("NSFOnline_ExpandSearchCriteriaLink"))).click();
							fnpLoading_wait_InDirectNSFOnline();

							fnpType("OR", "NSFOnline_IssueEscalation_SearchID_Input", Search_IDNo);
							// fnpClick_OR_DirectNSFOnline("NSFOnline_IssueEscalation_Search_Bttn");
							driver.findElement(By.xpath(OR.getProperty("NSFOnline_IssueEscalation_Search_Bttn"))).click();
							Thread.sleep(3000);
							fnpLoading_wait_InDirectNSFOnline(1);
							// Thread.sleep(1000);
							// fnpGetORObjectX("NSFOnline_IssueEscalation_Search_Bttn").sendKeys(Keys.ARROW_DOWN);
							driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
							driver.findElement(By.xpath(OR.getProperty("NSFOnline_IssueEscalation_Search_Bttn"))).sendKeys(Keys.ARROW_DOWN);

					//		TestSuiteBase_MonitorPlan.WithOut_OR_fnMove_To_Element_BY_OFFSET(OR_NsfOnline.getProperty("NSFOnline_IssueEscalation_SearchResultTable"), 30, 10);
					//		// fnpMove_To_Element_and_DoubleClick_NOR(OR.getProperty("NSFOnline_IssueEscalation_SearchResultTable2"));
							fnpMouseHover_NotInOR(OR.getProperty("NSFOnline_IssueEscalation_SearchResultTable2"));
							// String
							// SearchResult_GetText=fnsGet_Field_TEXT("NSFOnline_IssueEscalation_SearchResultTable").toLowerCase().trim();
							SearchResult_GetText = fnpGetText_OR("NSFOnline_IssueEscalation_SearchResultTable").toLowerCase().trim();
							driver.manage().timeouts().implicitlyWait(Long.parseLong(CONFIG.getProperty("implicitWaitTime")), TimeUnit.SECONDS);
							break;

						} catch (org.openqa.selenium.StaleElementReferenceException e) {
							if (whileRetries < 5) {
								// if (retries <
								// Integer.parseInt(CONFIG.getProperty("genMax_waitTime")))
								// {
								Thread.sleep(1000);
								whileRetries++;
								fnpMymsg("In staleElementException catch block of main while loop of SCA_IM --" + whileRetries);
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
								fnpMymsg("In InvalidSelectorException catch block of main while loop of SCA_IM --" + whileRetries + " --and error thrown is --" + is.getMessage());

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
								fnpMymsg("In Throwable t catch block of main while loop of SCA_IM --" + whileRetries);
								// continue;
							} else {
								throw t;
							}

						}
					} // /end of while loop

					try {
						if (GoodBadCondition.equalsIgnoreCase("good")) {
							// Thread.sleep(500);
							assert (SearchResult_GetText.contains(Search_IDNo.toLowerCase())) : "FAILED == Issue id <" + Search_IDNo + "> for Data  [ UserName# " + UserName + "]  is not coming 'GOOD'<i.e. SearchId is not appeared into the records Table>, ,Please refer Screenshot[" + GoodBadCondition + "_Fail" + fnsScreenShot_Date_format();
							fnpMymsg("PASSED == Issue id <" + Search_IDNo + "> for Data  [ UserName# " + UserName + "]  is coming 'GOOD'<i.e. SearchID is appeared into the records Table>.");
							ViewIssueFlag = true;

							List<WebElement> totalRowsElement = driver.findElements(By.xpath(OR.getProperty("NSFOnline_IssueEscalation_SearchResultTable_RowsXpath")));
							int totalRows = totalRowsElement.size() - 1;

							if (totalRows == 1) {
								fnpMymsg("Total searched records are equal to 1, hence search for good  works successfully.");
							} else {
								fnpMymsg("Total searched records are NOT equal to 1 as searched records" + "are --" + totalRows + " , hence search for good   NOT works successfully.");
								throw new Exception("Total searched records are NOT equal to 1 as searched records" + "are --" + totalRows + " , hence search for good   NOT works successfully.");
							}

						} else {
							// Thread.sleep(2000);
							// assert
							// (SearchResult_GetText.contains("no items were found")):"FAILED == Issue id <"+Search_IDNo+"> for Data  [ UserName# "+UserName+"]  is not coming 'BAD'<i.e. SearchID is appeared into the records Table>,,Please refer Screenshot["+GoodBadCondition+"_Fail"+fnsScreenShot_Date_format();
							Assert.assertFalse(fnpCheckRecordsPresentInTableInNSFOnline(OR.getProperty("NSFOnline_IssueEscalation_SearchResultTable_bodyXpath")), "FAILED == Issue id <" + Search_IDNo + "> for Data  [ UserName# " + UserName + "]  is not coming 'BAD'<i.e. SearchID is appeared into the records Table>,,Please refer Screenshot[" + GoodBadCondition + "_Fail" + fnsScreenShot_Date_format());
							fnpMymsg("PASSED == Issue id <" + Search_IDNo + "> for Data   [ UserName# " + UserName + "]  is coming 'BAD'<i.e. SearchID is not appeared into the records Table>.");
						}
					} catch (Throwable t) {
						fnpDesireScreenshot(GoodBadCondition + "_Fail");
						throw new Exception(t.getMessage());
					}

					if (ViewIssueFlag) {
						String IssueId_Link_Xpath = "//a[contains(text(), '" + Search_IDNo + "')]";
						fnpGetORObjectX___NOR(IssueId_Link_Xpath).click();
						fnpLoading_wait_InDirectNSFOnline();

						// fnpWaitForVisible("Footer");

						String Header_Info_Text = fnsGet_Field_TEXT("NsfOnline_ViewIssue_HeaderInfo").toLowerCase().trim();
						String Header_Info_IssueId_Xpath = OR_NsfOnline.getProperty("NsfOnline_ViewIssue_HeaderInfo") + "/tbody/tr[1]/td[1]/table/tbody/tr[1]/td[2]/span";
						fnpMove_To_Element_and_DoubleClick_NOR(Header_Info_IssueId_Xpath);
						try {
							// Thread.sleep(2000);
							assert (Header_Info_Text.contains(Search_IDNo.toLowerCase())) : "FAILED == 'View Issue' screen is not getting opened, Please refer Screenshot[ViewIssesScreenOpenFail" + fnsScreenShot_Date_format();
							fnpMymsg("PASSED == Successfully opened 'View Issue' screen and Issue id <" + Search_IDNo + "> is displayed on the screen.");
						} catch (Throwable t) {
							fnpDesireScreenshot("ViewIssesScreenOpenFail");
							throw new Exception(t.getMessage());
						}
					}

				}

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

	// ######################################################## Class Function
	// ########################################################
	// Clicking on Issue Tabs
	public static void fncClicking_On_NsfOnline_Issue_TABs(String TabXpath, String TabElementXpath, String TabName) throws Throwable {
		try {

			fnpWaitForVisible(TabXpath);
			for (int clicktry = 0; clicktry < 4; clicktry++) {

				/* fnsWait_and_Click(TabXpath);
				 * TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait_NsfOnline
				 * (3); */
				fnpClick_OR_DirectNSFOnline(TabXpath);

				if (driver.findElements(By.xpath(OR.getProperty(TabElementXpath))).size() > 0) {
					break;
				}
				if (clicktry == 3) {
					throw new Exception("After 4 time Click on TAB, Tab is not getting opened.");
				}
			}
			fnpMymsg("PASSED == **************** Successfully opened '" + TabName + "' TAB with NO errors.");
		} catch (Throwable t) {
			fnpDesireScreenshot("TabOpeningFail");
			throw new Exception("FAILED == < " + TabName + " > TAB is not getting opened, Please refer screen shot >> TabOpenFail" + fnsScreenShot_Date_format() + " Getting Exception >> " + t.getMessage());
		}

	}

}
