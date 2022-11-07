package nsf.ecap.Client_Suite;

import nsf.ecap.Work_Order_suite.TestSuiteBase;
import nsf.ecap.util.*;
import org.openqa.selenium.By;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.google.common.base.Throwables;

public class CreateClient extends TestSuiteBase_CLNT {

	public static boolean IsBrowserPresentAlready = false;
	public static String ClientId_CO = null;
	public static String ClientId_PL = null;
	public static Integer count = -1;
	public static Integer RowCount;
	public static boolean skip = true;
	public static String ScenarioName = null;
	public static String CaseNo = null;
	public static String ClientType = null;
	public static String FetchClientIdText = null;
	public static String ClientCreatedMessage = null;
	public static String ScenaioName = null;
	public static String OrgUnit_DD_Value = null;

	@Parameters({ "className" })
	@BeforeTest
	public void checkTestSkip(String className) throws Exception {

		isTestCasePass = true;
		// count = 0;

		try {
			if (className.isEmpty()) {
				className = this.getClass().getSimpleName();
			}

			classNameText = className;
			currentScriptCode = fnpMappingClassNameWithScenarioCode(classNameText).trim();

			if (!TestUtil.isTestCaseRunnable(Client_Suitexls, className)) {
				fnsApps_Report_Logs("Skipping Test Case " + className + " as runmode set to NO");

				throw new SkipException("Skipping Test Case " + className + " as runmode set to NO");
			}

			runmodes = TestUtil.getDataSetRunmodes(Client_Suitexls, className);

			fnsApps_Report_Logs(
					"=========================================================================================================================================");

		} catch (SkipException sk) {
			String errorMsg = sk.getMessage();
			Exception c = new Exception(errorMsg);
			ErrorUtil.addVerificationFailure(c);
			throw new SkipException(errorMsg + " \n\nSo,Skipping Test Case" + className);
		} catch (Throwable t) {
			fnsTake_Screen_Shot(className);
			String stackTrace = Throwables.getStackTraceAsString(t);
			String errorMsg = t.getMessage();
			errorMsg = errorMsg + "\n\n\n\nSee screenshot '" + className + "'\n\n" + stackTrace;
			Exception c = new Exception(errorMsg);
			ErrorUtil.addVerificationFailure(c);
			throw new Exception(errorMsg);
		}
	}

	@Test(priority = 0)
	public void Launch_Browser_and_Successfully_Login_into_the_Application() throws Throwable {
		fnsApps_Report_Logs("################################## [BS - 01]  CreateClient");
		if (!IsBrowserPresentAlready) {
			// IsBrowserPresentAlready = TestSuiteBase.fnpLaunchBrowserAndLogin();
			IsBrowserPresentAlready = fnsLaunchBrowserAndLogin();
			fnsApps_Report_Logs("Browser is launched and Successfully login into Application");
		}
	}

	@Test(dataProvider = "getTestData", priority = 1, dependsOnMethods = {
			"Launch_Browser_and_Successfully_Login_into_the_Application" })
	public void Create_Client_(String Case_No, String Scenario_Name) throws Throwable {

		fnsApps_Report_Logs(
				"=========================================================================================================================================");

		count++;

		ScenaioName = Scenario_Name.trim();
		ClientType = (Scenario_Name.split("\\_")[2]).trim();

		if (ScenaioName.contains("ORG")) {
			OrgUnit_DD_Value = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(),
					"CO_OrgUnit_DD").split("\\,")[0].trim();
		}
		if (ScenaioName.contains("ISR")) {
			OrgUnit_DD_Value = fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(),
					"PL_OrgUnit_DD").split("\\,")[1].trim();
		}

		try {

			if (!(runmodes[count].equalsIgnoreCase("Y"))) {
				fnsApps_Report_Logs(
						"****************************************************************************************************************************************");
				fnsApps_Report_Logs("################################## Runmode of " + (Case_No) + " for Scenario ["
						+ Scenario_Name + "]  is set to NO, So Skipping this Case.");
				skip = true;
				throw new SkipException("Runmode of " + (Case_No) + " for Scenario [" + Scenario_Name
						+ "]  is set to NO, So Skipping this Case.");
			} else {

				skip = false;
				fnsApps_Report_Logs(
						"****************************************************************************************************************************************");
				fnsApps_Report_Logs("################################## Execution Logs of  " + (Case_No)
						+ " for Scenario [" + Scenario_Name + "].");

				if (ClientType.equals("CO")) {

					fnsApps_Report_Logs("###################################################### Test " + (Case_No)
							+ " for Scenario [" + Scenario_Name + "]");

					fnCreateClient_Ajax_Link_Click();

					fnsLoading_Progressingwait(1);

					// Added on 11.7.2016 -- as after selecting value from DD, all other fields
					// values are getting swiped.
					WithOut_OR_fnDD_SelectValue_Through_Filter(
							OR_CLNT.getProperty("CreateClient_ClientInfo_OrgUnit_DD_Click"),
							OR_CLNT.getProperty("CreateClient_ClientInfo_OrgUnit_DD_Filter"), OrgUnit_DD_Value);
					Thread.sleep(1500);
					fnsLoading_Progressingwait(1);

					fnsGet_Element_Enabled("CreateClient_ClientInfo_Name");
					fnsWait_and_Type("CreateClient_ClientInfo_Name", fnsReturn_ExcelCellValue_ByMatchingColumnName(
							this.getClass().getSimpleName(), "CO_ClientInfo_Name"));

					// Commented on 11.7.2016
					// fnsDD_value_Select_By_MatchingText("CreateClient_ClientInfo_OrgUnit_DD_Click","CreateClient_ClientInfo_OrgUnit_DD_Value",
					// "li",Client_Suitexls.getCellData("Create_CLNT", 2, 3));
					// TestSuiteBase_MonitorPlan.WithOut_OR_fnDD_SelectValue_Through_Filter(OR_CLNT.getProperty("CreateClient_ClientInfo_OrgUnit_DD_Click"),
					// OR_CLNT.getProperty("CreateClient_ClientInfo_OrgUnit_DD_Filter"),
					// Client_Suitexls.getCellData("Create_CLNT", 2, 3));
					// Thread.sleep(1500);
					// TestSuiteBase_Aspirago.fnsLoading_Progressingwait(2);

					fnsDD_value_Select_By_MatchingText("CreateClient_ClientInfo_Type_DD_Click",
							"CreateClient_ClientInfo_Type_DD_Value", "li", ClientType);

					fnsWait_and_Type("CreateClient_ClientAddress_AddressLine1",
							fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(),
									"CO_AddressLine1"));
					fnsWait_and_Type("CreateClient_ClientAddress_AddressLine2",
							fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(),
									"CO_AddressLine2") + fnCreateClient_Date_format());
					fnsWait_and_Type("CreateClient_ClientAddress_City",
							fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "CO_City"));
					fnsDD_value_Select_By_MatchingText("CreateClient_ClientAddress_State_DD_Click",
							"CreateClient_ClientAddress_State_DD_Value", "li",
							fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(),
									"CO_State_DD"));
					fnsLoading_Progressingwait(1);

					fnsGet_Element_Enabled("CreateClient_ClientAddress_Postal");
					fnsWait_and_Type("CreateClient_ClientAddress_Postal", fnsReturn_Requried_cal("ddMMyyyy"));

					fnsWait_and_Type("CreateClient_ClientAddress_Primary_Phone",
							fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(),
									"CO_Primary_Phone"));
					fnsWait_and_Type("CreateClient_ClientAddress_ContactEmail",
							fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(),
									"CO_ContactEmail"));

					fnsWait_and_Type("CreateClient_PrimaryContactDetails_FirstName",
							fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(),
									"CO_FirstName"));
					fnsWait_and_Type("CreateClient_PrimaryContactDetails_LastName",
							fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(),
									"CO_LastName"));
					fnsWait_and_Type("CreateClient_PrimaryContactDetails_Email",
							fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "CO_Email"));
					System.out.println(driver
							.findElements(By.xpath(OR_CLNT.getProperty("CreateClient_PrimaryContactDetails_Phone")))
							.size());
					fnsWait_and_Type("CreateClient_PrimaryContactDetails_Phone",
							fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "CO_Phone"));
					fnsDD_value_Select_By_MatchingText("CreateClient_PrimaryContactDetails_BillToOffice_Dd_Click",
							"CreateClient_PrimaryContactDetails_BillToOffice_Dd_Value", "li",
							fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(),
									"CO_BillToOffice_DD"));
					Thread.sleep(1500);
					fnsLoading_Progressingwait(1);

					fnsWait_and_Click("CreateClient_AccountManager_LookUp_Bttn");
					fnsLoading_Progressingwait(1);
					fnsWait_and_Type("CreateClient_AccountManager_LookUp_Bttn_Text",
							fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(),
									"CO_Account Manager_LK"));
					fnsLookup_RadioBttn_Select();

					Thread.sleep(1500);
					fnsWait_and_Click("CreateClient_Create_Bttn");
					fnsLoading_Progressingwait(1);
					// Thread.sleep(3000);
					fnsLoading_Progressingwait(1);
					for (int BttnClickWait = 0; BttnClickWait < 3; BttnClickWait++) {
						if (driver.findElements(By.xpath(OR_CLNT.getProperty("Updated_Message1"))).size() > 0) {
							break;
						} else if (driver.findElements(By.xpath(OR_CLNT.getProperty("CreateClient_Create_Bttn")))
								.size() > 0) {
							fnsLoading_Progressingwait(1);
							fnsWait_and_Click("CreateClient_Create_Bttn");
							Thread.sleep(2000);
							fnsLoading_Progressingwait(1);
						} else {
							Thread.sleep(3000);
							fnsLoading_Progressingwait(1);
						}
					}

					fnsGet_Element_Enabled("Updated_Message1");
					ClientCreatedMessage = fnsGet_Field_TEXT("Updated_Message1");
					try {
						assert (ClientCreatedMessage.toLowerCase().trim()).contains("success")
								: "FAILED == Create Client 'CO' is not Generated, Getting Error<" + ClientCreatedMessage
										+ ">, Please refer Screenshot >> CreateClientFail"
										+ fnsScreenShot_Date_format();
					} catch (Throwable t) {
						isTestCasePass = false;
						fnsTake_Screen_Shot("CreateClientFail");
						fnsApps_Report_Logs(t.getMessage());
						throw new Exception(t.getMessage());
					}

					// Thread.sleep(4000);
					String FetchClientIdText = fnsGet_Field_TEXT("Client_ID");
					ClientId_CO = FetchClientIdText.trim();
					fnsApps_Report_Logs("PASSED == Client Id Generated Successfully  ==  " + ClientId_CO);

				}

				if (ClientType.equals("PL")) {

					fnsApps_Report_Logs("###################################################### Test " + (Case_No)
							+ " for Scenario [" + Scenario_Name + "]");

					if (ClientId_CO == null) {
						throw new Exception(
								"FAILED == 'CO' Client id is not found, please run CO and PL scenarios together.");
					}

					fnCreateClient_Ajax_Link_Click();

					fnsLoading_Progressingwait(1);

					// Added on 11.7.2016 -- as after selecting value from DD, all other fields
					// values are getting swiped.
					WithOut_OR_fnDD_SelectValue_Through_Filter(
							OR_CLNT.getProperty("CreateClient_ClientInfo_OrgUnit_DD_Click"),
							OR_CLNT.getProperty("CreateClient_ClientInfo_OrgUnit_DD_Filter"), OrgUnit_DD_Value);
					Thread.sleep(1500);
					fnsLoading_Progressingwait(1);

					fnsGet_Element_Enabled("CreateClient_ClientInfo_Name");
					fnsWait_and_Type("CreateClient_ClientInfo_Name", fnsReturn_ExcelCellValue_ByMatchingColumnName(
							this.getClass().getSimpleName(), "PL_ClientInfo_Name"));

					// Commented on 11.7.2016
					// fnsDD_value_Select_By_MatchingText("CreateClient_ClientInfo_OrgUnit_DD_Click","CreateClient_ClientInfo_OrgUnit_DD_Value",
					// "li",Client_Suitexls.getCellData("Create_CLNT", 2, 20));
					// TestSuiteBase_MonitorPlan.WithOut_OR_fnDD_SelectValue_Through_Filter(OR_CLNT.getProperty("CreateClient_ClientInfo_OrgUnit_DD_Click"),
					// OR_CLNT.getProperty("CreateClient_ClientInfo_OrgUnit_DD_Filter"),
					// Client_Suitexls.getCellData("Create_CLNT", 2, 20));
					// Thread.sleep(1500);
					// TestSuiteBase_Aspirago.fnsLoading_Progressingwait(3);
					fnsDD_value_Select_By_MatchingText("CreateClient_ClientInfo_Type_DD_Click",
							"CreateClient_ClientInfo_Type_DD_Value", "li", ClientType);

					fnsWait_and_Type("CreateClient_ClientAddress_AddressLine1",
							fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(),
									"PL_AddressLine1"));
					fnsWait_and_Type("CreateClient_ClientAddress_AddressLine2",
							fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(),
									"PL_AddressLine2") + fnCreateClient_Date_format());
					fnsWait_and_Type("CreateClient_ClientAddress_City",
							fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "PL_City"));
					fnsDD_value_Select_By_MatchingText("CreateClient_ClientAddress_State_DD_Click",
							"CreateClient_ClientAddress_State_DD_Value", "li",
							fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(),
									"PL_State_DD"));
					fnsLoading_Progressingwait(1);

					fnsGet_Element_Enabled("CreateClient_ClientAddress_Postal");
					fnsWait_and_Type("CreateClient_ClientAddress_Postal", fnsReturn_Requried_cal("ddMMyyyy"));

					fnsWait_and_Type("CreateClient_ClientAddress_Primary_Phone",
							fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(),
									"PL_Primary_Phone"));
					fnsWait_and_Type("CreateClient_ClientAddress_ContactEmail",
							fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(),
									"PL_ContactEmail"));

					fnsGet_Element_Enabled("CreateClient_PrimaryContactDetails_FirstName");
					Thread.sleep(1000);
					fnsWait_and_Type("CreateClient_PrimaryContactDetails_FirstName",
							fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(),
									"PL_FirstName"));
					fnsWait_and_Type("CreateClient_PrimaryContactDetails_LastName",
							fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(),
									"PL_LastName"));
					fnsWait_and_Type("CreateClient_PrimaryContactDetails_Email",
							fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "PL_Email"));
					fnsWait_and_Type("CreateClient_PrimaryContactDetails_Phone",
							fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(), "PL_Phone"));
					fnsDD_value_Select_By_MatchingText("CreateClient_PrimaryContactDetails_BillToOffice_Dd_Click",
							"CreateClient_PrimaryContactDetails_BillToOffice_Dd_Value", "li",
							fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(),
									"PL_BillToOffice_DD"));
					Thread.sleep(1500);
					fnsLoading_Progressingwait(1);

					fnsWait_and_Click("CreateClient_AccountManager_LookUp_Bttn");
					fnsLoading_Progressingwait(1);
					fnsWait_and_Type("CreateClient_AccountManager_LookUp_Bttn_Text",
							fnsReturn_ExcelCellValue_ByMatchingColumnName(this.getClass().getSimpleName(),
									"PL_Account Manager_LK"));
					fnsLookup_RadioBttn_Select();
					Thread.sleep(1500);

					fnsWait_and_Click("CreateClient_ClientInfo_CorpFacilityParent_LookUp_Bttn");
					fnsLoading_Progressingwait(1);
					fnsWait_and_Type("CreateClient_ClientInfo_CorpFacilityParent_LookUp_SearchField_Client",
							ClientId_CO);
					fnsLookup_RadioBttn_Select();

					fnsGet_Element_Enabled("CreateClient_Create_Bttn");

					for (int wait = 0; wait < 5; wait++) {

						if (driver.findElements(By.xpath(OR_CLNT.getProperty("CreateClient_Create_Bttn"))).size() > 0) {
							Thread.sleep(1000);
							fnsWait_and_Click("CreateClient_Create_Bttn");
							fnsLoading_Progressingwait(1);
							break;
						} else {
							Thread.sleep(1000);
							fnsWait_and_Click("CreateClient_Create_Bttn");
							fnsLoading_Progressingwait(1);
						}

					}

					// fnsWait_and_Click("CreateClient_Create_Bttn");
					// fnsLoading_Progressingwait(1);

					// fnsLoading_Progressingwait(1);
					// for(int BttnClickWait=0; BttnClickWait<3; BttnClickWait++){
					// if(driver.findElements(By.xpath(OR_CLNT.getProperty("Updated_Message1"))).size()>0){
					// break;
					// }else
					// if(driver.findElements(By.xpath(OR_CLNT.getProperty("CreateClient_Create_Bttn"))).size()>0){
					// fnsLoading_Progressingwait(1);
					// fnsWait_and_Click("CreateClient_Create_Bttn");
					// Thread.sleep(2000);
					// fnsLoading_Progressingwait(1);
					// }else{
					// Thread.sleep(3000);
					// fnsLoading_Progressingwait(1);
					// }
					// }
					//
					fnsGet_Element_Enabled("Updated_Message1");
					ClientCreatedMessage = fnsGet_Field_TEXT("Updated_Message1");
					try {
						assert (ClientCreatedMessage.toLowerCase().trim()).contains("success")
								: "FAILED == Create Client 'PL' is not Generated, Getting Error<" + ClientCreatedMessage
										+ ">, Please refer Screenshot >> CreateClientFail"
										+ fnsScreenShot_Date_format();
					} catch (Throwable t) {
						isTestCasePass = false;
						fnsTake_Screen_Shot("CreateClientFail");
						fnsApps_Report_Logs(t.getMessage());
						throw new Exception(t.getMessage());
					}

					// Thread.sleep(4000);
					FetchClientIdText = fnsGet_Field_TEXT("Client_ID");
					ClientId_PL = FetchClientIdText.trim();
					fnsApps_Report_Logs("PASSED == Client Id Generated Successfully  ==  " + ClientId_PL);
					ClientId_CO = null;
				}

			}
		} catch (SkipException sk) {
			throw new SkipException(sk.getMessage());

		} catch (Throwable t) {
			fail = true;
			isTestCasePass = false;
			fnsApps_Report_Logs(" " + Throwables.getStackTraceAsString(t));
			throw new Exception(" " + Throwables.getStackTraceAsString(t));
		}

	}

	@AfterMethod
	public void reportDataSetResult() {
		if (count > -1) {
			if (skip)
				TestUtil.reportDataSetResult(Client_Suitexls, this.getClass().getSimpleName(), count + 2, "SKIP");
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

		fns_ReportTestResult_atClassLevel(Client_Suitexls, (this.getClass().getSimpleName()));
	}

	@AfterTest
	public void QuitBrowser() {
		// MoveMouseCursorToTaskBar();
		driver.quit();
	}

	@DataProvider
	public Object[][] getTestData() {
		return getExcelData_for_DataProvider(Client_Suitexls, this.getClass().getSimpleName());
		// return TestUtil.getData(Client_Suitexls, this.getClass().getSimpleName());
	}

}
