package nsf.ecap.IssueMgt_Suite;

import java.util.HashMap;
import java.util.Properties;



//
import nsf.ecap.util.*;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.util.HSSFColor.GREEN;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.os.WindowsUtils;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;




//import com.gargoylesoftware.htmlunit.javascript.host.Document; 
import com.google.common.base.Throwables;

public class CreateEscalation_ChargesVerify extends TestSuiteBase_IM{
	
	public static String issue_id_green="I0060317";
	public static String issue_id_amber;//="I0037958";
	public static String issue_id_red;//="I0036525";
	public static String Escalation_Id;//="E0003191";
	public static String validation_error_msg_text;
	public static String PerIssueChargeValue;
	public static String EscalationChargeValue;//="30.00";
	public static String ChargeAmountGreenValue;
	public static String ChargeAmountAmberValue;
	public static String ChargeAmountRedValue;
	public static String TitleShortDescriptionText="CreateEsc_Charges[BS-03], D/T=";
	
	public static boolean IsBrowserPresentAlready = false;
		
	

	
	@Parameters({ "className" })
	@BeforeTest
		public void checkTestSkip(String className) throws Exception {
		
		isTestCasePass=true;
		
		
		try {
			// this.getClass().getSimpleName()
			if (className.isEmpty()) {
				className = this.getClass().getSimpleName();

			}
			
			classNameText=className;
			currentScriptCode=fnpMappingClassNameWithScenarioCode(classNameText).trim();

			if (!TestUtil.isTestCaseRunnable(IssueMgt_Suitexls, className)) {
				fnApps_Report_Logs("Skipping Test Case " + className + " as runmode set to NO");// logs
				throw new SkipException("Skipping Test Case " + className + " as runmode set to NO");// reports
			}

			
			fnApps_Report_Logs("=========================================================================================================================================");
		
		}
		catch(SkipException sk){
			String stackTrace = Throwables.getStackTraceAsString(sk);
			String errorMsg = sk.getMessage();
			Exception c = new Exception(errorMsg);
			ErrorUtil.addVerificationFailure(c);
			throw new SkipException(errorMsg + " \n\nSo,Skipping Test Case" + className);// reports
		}
		catch (Throwable t) {
								
				//fnDesireScreenshot(className);				
				String stackTrace = Throwables.getStackTraceAsString(t);
				String errorMsg = Throwables.getStackTraceAsString(t);
				errorMsg = errorMsg + "\n\n\n\nSee screenshot '" + className + "'\n\n" + stackTrace;
				Exception c = new Exception(errorMsg);
				ErrorUtil.addVerificationFailure(c);
				throw new Exception(errorMsg );// reports
		}
}
	
	
	
	
	
	
	
	@BeforeClass (description="OracleQueryLaunch_ToClosed_OpenIssues_and_InActiveAlerts_Whose --- productCode(501214), Issue_Type(PRQ) and Client(NSF-CMiRetailTestingClient")
	public void OracleQueryLaunch_ToClosed_OpenIssues_and_InActiveAlerts() throws Throwable {

		//Function Called to close all issues in database
		fnUpdateDB_to_Close_issues();
		
	}
	
	
	
	
	

@Test( priority = 0)
public void Launch_Browser_and_Successfully_Login_into_the_Application() throws Throwable{
	fnApps_Report_Logs("################################## [BS-03]  Create Escalation Charges Verify");
	if (!IsBrowserPresentAlready) {
		IsBrowserPresentAlready = fnLaunchBrowserAndLogin();
		fnApps_Report_Logs("Browser is launched and ******** Successfully login into 'I-Pulse' Application.");
	}
}	
	
	
	
	@Test(dependsOnMethods={"Launch_Browser_and_Successfully_Login_into_the_Application"}, priority = 1, description="  [Create Issue ID with Green Color]")
	public void Escalation_PRQ_FirstIssue_Green() throws Throwable{
		
			
		fnApps_Report_Logs("################### Test Case ::::::1 Escalation_PRQ_FirstIssue_Green ");
	//	IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//src//nsf//ecap//xls//IssueMgt_Suite.xlsx");
		WebDriverWait waitvar = new WebDriverWait(driver, Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
		

		  
		
			
		//Clicking on CreateIssue Ajax Link
		fnCreateIssue_Ajax_Link_Click();
		
		//Site Details page data entered and clicking on next button
		fnVerify_SiteDetailsPage_Data_Entered_and_NavigateTo_SummaryDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_PRQ", 2, 2), IssueMgt_Suitexls.getCellData("Escalation_PRQ", 2, 3));
	
		//Summary Details page data entered and clicking on next button
		fnVerify_SummaryDetailsPage_Data_Entered_and_NavigateTo_CustomerDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_PRQ", 2, 6), IssueMgt_Suitexls.getCellData("Escalation_PRQ", 2, 7), IssueMgt_Suitexls.getCellData("Escalation_PRQ", 2, 8), fnGet_2Days_Past_date(), TitleShortDescriptionText);
	


		//Clicking on Customer  NEXT Button
		fnClick_UnTill_Navigate_to_NextPage("CreateIssue_Customer_next_bttn","CreateIssue_Customer_Name_TextField","CreateIssue_product_code_bttn");	
		
		//Product Details page data entered and clicking on next button
		fnVerify_ProductDetailsPage_Data_Entered_and_NavigateTo_DocumentsDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_PRQ", 2, 9));
		
	
		// Clicking on Document next button
		fnClick_UnTill_Navigate_to_NextPage("CreateIssue_document_next_bttn","CreateIssue_document_Add_Attachement","CreateIssue_confirmation_submit_bttn");		
		
		// Clicking on Confirmation Submit button
		fnWait_and_Click("CreateIssue_confirmation_submit_bttn");
		
	
		
		//Verify issue ID Generated and Color is as per Expected and Stored into String
		issue_id_green=fnVerified_IssueID_Generated_and_Color_Verification(1, "GREEN");
		
}
	

	

	@Test(dependsOnMethods={"Escalation_PRQ_FirstIssue_Green"}, priority = 2, description="  [Verify charges are for basic level by clicking on the charges tab. Verify the Charge Level and the amount.]")
	public void Escalation_PRQ_FirstIssue_Verify_Charges_on_ViewIssue_ChargesScreen() throws Throwable{
		
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::2 Escalation_PRQ_FirstIssue_Verify_charges_on_ViewIssue_ChargesScreen ");
		
//		IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//src//nsf//ecap//xls//IssueMgt_Suite.xlsx");
		
		
		
		
		try{
			
		
		//Clicking on ClientChargesConfiguration_Ajax_Link
		fnClientChargesConfiguration_Ajax_Link_Click();
		
		//Wait till page load and ClientLookUp_Button enabled
		fnGet_Element_Enabled("SearchCharge_ClientLookUp_Button");
		fnWait_and_Click("SearchCharge_ClientLookUp_Button");
		fnsLoading_Progressingwait(3);
				
		
		// Enter client name into search filed on client lookup
		fnGet_Element_Enabled("CreateIssue_SiteDetails_client_lookup_value_text_field");
		fnWait_and_Type("CreateIssue_SiteDetails_client_lookup_value_text_field", IssueMgt_Suitexls.getCellData("Escalation_PRQ", 2, 2));
								
		// Selecting first radio button from search result table
		//fnLoading_wait();
		fnsLookup_RadioBttn_Select();
		
		//Clicking on Search button on Search Charge screen
		fnGet_Element_Enabled("SearchCharge_Search_Button");
		fnWait_and_Click("SearchCharge_Search_Button");
		TestSuiteBase_MonitorPlan.fnsLoading_Progressingwait(10);
		Thread.sleep(1000);
		//Wait and type in Client search filter
		//waitvar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR_IM.getProperty("SearchCharge_ClientLookUp_Button")))).isEnabled();
		//fnWait_and_Type("SearchCharge_Client_SearchResultTableFilter", IssueMgt_Suitexls.getCellData("Escalation_PRQ", 2, 2) );
		
		//Wait and type in Issue Type search filter
		fnGet_Element_Enabled("SearchCharge_Issue_Type_SearchResultTableFilter");
		fnWait_and_Type("SearchCharge_Issue_Type_SearchResultTableFilter", IssueMgt_Suitexls.getCellData("Escalation_PRQ", 2, 7) );
		Thread.sleep(5000);
		
			
		String SearchResult_ClientName_link = IssueMgt_Suitexls.getCellData("Escalation_PRQ", 2, 2).trim();
		String SearchResult_ClientName_link_Xpath = ".//a[contains(text(),'" + SearchResult_ClientName_link + "')]";
		
		WithOut_OR_fnGet_Element_Enabled(SearchResult_ClientName_link_Xpath);
		WithOut_OR_fnClick(SearchResult_ClientName_link_Xpath);
		fnsLoading_Progressingwait(2);
				
		fnApps_Report_Logs("PASSED == Successfully click on Client Name Link   >> "+IssueMgt_Suitexls.getCellData("Escalation_PRQ", 2, 2)+ " << on Search Charge Screen");
		}catch(Throwable t){
			isTestCasePass = false;
			fnTake_Screen_Shot(IssueMgt_Suitexls.getCellData("Escalation_PRQ", 2, 2));
			fnApps_Report_Logs("FAILED == Unable to click on Client Name Link >> "+IssueMgt_Suitexls.getCellData("Escalation_PRQ", 2, 2)+"  ,Please refer ScreenShot [ "+IssueMgt_Suitexls.getCellData("Escalation_PRQ", 2, 2)+fnScreenShot_Date_format() +" ], Getting Eception >> "+Throwables.getStackTraceAsString(t));
			throw new Exception("FAILED == Unable to click on Client Name Link >> "+IssueMgt_Suitexls.getCellData("Escalation_PRQ", 2, 2)+"  ,Please refer ScreenShot [ "+IssueMgt_Suitexls.getCellData("Escalation_PRQ", 2, 2)+fnScreenShot_Date_format() +" ], Getting Eception >> "+Throwables.getStackTraceAsString(t));
			}
		
		
		
		
		
		
		
		try{
		
			//int row=fnGet_RowIndex_by_CellTextValue("ClientChargeConfiguration_PerIssueCharge_TableHeader","ClientChargeConfiguration_PerIssueCharge_Table_CELLHeader","BC","Y");;
			
			//Waiting for Footer till page load
			fnGet_Element_Enabled("footer");
			Thread.sleep(2000);
			
			// Storing per issue charge value in String
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			String issuechargexpath="mainForm:issChCoPIsdt:1:issChCoPIsChrgamt_input"; //BC Charge and justified Y Value
			String PerIssueCharge=(String)jse.executeScript(" return document.getElementById('"+issuechargexpath+"').value;");
			PerIssueChargeValue=PerIssueCharge.trim();
			
			Thread.sleep(500);
			// Storing per Escalation charge value in String
			String escalationxpath="mainForm:issChCoPIsdt:5:issChCoPIsChrgamt_input";  //ES Charge Value
			String EscalationCharge=(String)jse.executeScript(" return document.getElementById('"+escalationxpath+"').value;");
			EscalationChargeValue=EscalationCharge.trim();
		
		
		fnApps_Report_Logs("PASSED == Charge Amount is displayed on Client Charge Configuration Page and successfully get the charge value >> Issue Charge [ "+PerIssueChargeValue+" ]."+" >> Escalation Charge [ "+EscalationChargeValue+" ].");
		}catch(Throwable t){
			isTestCasePass = false;
			fnTake_Screen_Shot(PerIssueChargeValue+EscalationChargeValue);
			fnApps_Report_Logs("FAILED == Unable to Fetch Charge Amount from Client Charge Configuration Page,Please refer ScreenShot [ "+PerIssueChargeValue+EscalationChargeValue+fnScreenShot_Date_format() +" ], Getting Eception >> "+Throwables.getStackTraceAsString(t));
			throw new Exception("FAILED == Unable to Fetch Charge Amount from Client Charge Configuration Page,Please refer ScreenShot [ "+PerIssueChargeValue+EscalationChargeValue+fnScreenShot_Date_format() +" ], Getting Eception >> "+Throwables.getStackTraceAsString(t));
			}	
		
		
		
		
		
		
		
		
		try{
			
			//Search IssueID and also navigate to ViewIssue Summary Details page 
			fncIssueID_SearchIssueID_and_Navigate_to_ViewIssueSummaryDetailsPageComes(issue_id_green, true);
			
			//Clicking on Charge TAB on View issue page
			fnGet_Element_Enabled("ViewIssueCharges_Charges_Tab");
			fnWait_and_Click("ViewIssueCharges_Charges_Tab");
			
			
			//Waiting for Footer till page load
			fnGet_Element_Enabled("footer");
			
			
			Thread.sleep(2000);
			//Getting charge amount and stored in a String
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			String ChargeAmountxpath="mainForm:issedittabvw:isschrgamt";
			String ChargeAmount=(String)jse.executeScript(" return document.getElementById('"+ChargeAmountxpath+"').value;");
			String ChargeAmountValue=ChargeAmount.trim();
			
			
			System.out.println("ChargeAmountValue   "+ChargeAmountValue);
			
			assert ChargeAmountValue.contains(PerIssueChargeValue):"FAILED == (View Issue)Charge amount is not matched with (Client Charge Configuration)Amount";
					
		
		fnApps_Report_Logs("PASSED ==  (View Issue)Charge amount is matched with (Client Charge Configuration)Charge Amount [ "+ChargeAmountValue+" ].");
		}catch(Throwable t){
			isTestCasePass = false;
			fnTake_Screen_Shot("ViewIssue"+PerIssueChargeValue);
			fnApps_Report_Logs("FAILED == (View Issue)Charge amount is not matched with (Client Charge Configuration)Amount,Please refer ScreenShot [ "+"ViewIssue"+PerIssueChargeValue+fnScreenShot_Date_format() +" ] , Getting Eception >> "+Throwables.getStackTraceAsString(t));
			throw new Exception("FAILED == (View Issue)Charge amount is not matched with (Client Charge Configuration)Amount,Please refer ScreenShot [ "+"ViewIssue"+PerIssueChargeValue+fnScreenShot_Date_format() +" ] , Getting Eception >> "+Throwables.getStackTraceAsString(t));
			}	
		
		
		
		
		
			
	}
	
	

	
	
	@Test(dependsOnMethods={"Escalation_PRQ_FirstIssue_Verify_Charges_on_ViewIssue_ChargesScreen"},priority = 3, description="  [Create Issue ID with Amber color]")
	public void Escalation_PRQ_SecondIssue_Amber() throws Throwable{
		
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::3 Escalation_PRQ_SecondIssue_Amber ");
	//	IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//src//nsf//ecap//xls//IssueMgt_Suite.xlsx");
	
			
		//Clicking on CreateIssue Ajax Link
		fnCreateIssue_Ajax_Link_Click();
		
		//Site Details page data entered and clicking on next button
		fnVerify_SiteDetailsPage_Data_Entered_and_NavigateTo_SummaryDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_PRQ", 2, 2), IssueMgt_Suitexls.getCellData("Escalation_PRQ", 2,5));
	
		//Summary Details page data entered and clicking on next button
		fnVerify_SummaryDetailsPage_Data_Entered_and_NavigateTo_CustomerDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_PRQ", 2, 6), IssueMgt_Suitexls.getCellData("Escalation_PRQ", 2, 7), IssueMgt_Suitexls.getCellData("Escalation_PRQ", 2, 8), fnGet_2Days_Past_date(), TitleShortDescriptionText);
	


		//Clicking on Customer  NEXT Button
		fnClick_UnTill_Navigate_to_NextPage("CreateIssue_Customer_next_bttn","CreateIssue_Customer_Name_TextField","CreateIssue_product_code_bttn");	
		
		//Product Details page data entered and clicking on next button
		fnVerify_ProductDetailsPage_Data_Entered_and_NavigateTo_DocumentsDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_PRQ", 2, 9));
		
	
		// Clicking on Document next button
		fnClick_UnTill_Navigate_to_NextPage("CreateIssue_document_next_bttn","CreateIssue_document_Add_Attachement","CreateIssue_confirmation_submit_bttn");		
		
		
		// Clicking on Confirmation Submit button
		fnWait_and_Click("CreateIssue_confirmation_submit_bttn");
		
	
		
		//Verify issue ID Generated and Color is as per Expected and Stored into String
		issue_id_amber=fnVerified_IssueID_Generated_and_Color_Verification(2, "AMBER");
		
}
	
	

	@Test(dependsOnMethods={"Escalation_PRQ_SecondIssue_Amber"},priority = 4, description="  [Create Issue ID with RED color]")
	public void Escalation_PRQ_ThirdIssue_Red() throws Throwable{
		
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::4 Escalation_PRQ_ThirdIssue_Red ");
		
	//	IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//src//nsf//ecap//xls//IssueMgt_Suite.xlsx");
		
			
		//Clicking on CreateIssue Ajax Link
		fnCreateIssue_Ajax_Link_Click();
		
		//Site Details page data entered and clicking on next button
		fnVerify_SiteDetailsPage_Data_Entered_and_NavigateTo_SummaryDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_PRQ", 2, 2), IssueMgt_Suitexls.getCellData("Escalation_PRQ", 2, 4));
	
		//Summary Details page data entered and clicking on next button
		fnVerify_SummaryDetailsPage_Data_Entered_and_NavigateTo_CustomerDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_PRQ", 2, 6), IssueMgt_Suitexls.getCellData("Escalation_PRQ", 2, 7), IssueMgt_Suitexls.getCellData("Escalation_PRQ", 2, 8), fnGet_2Days_Past_date(), TitleShortDescriptionText);



		//Clicking on Customer  NEXT Button
		fnClick_UnTill_Navigate_to_NextPage("CreateIssue_Customer_next_bttn","CreateIssue_Customer_Name_TextField","CreateIssue_product_code_bttn");	
		
		//Product Details page data entered and clicking on next button
		fnVerify_ProductDetailsPage_Data_Entered_and_NavigateTo_DocumentsDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_PRQ", 2, 9));
		
	
		// Clicking on Document next button
		fnClick_UnTill_Navigate_to_NextPage("CreateIssue_document_next_bttn","CreateIssue_document_Add_Attachement","CreateIssue_confirmation_submit_bttn");		
		
		
		// Clicking on Confirmation Submit button
		fnWait_and_Click("CreateIssue_confirmation_submit_bttn");
		
	
	
		
		//Verify issue ID Generated and Color is as per Expected and Stored into String
		issue_id_red=fnVerified_IssueID_Generated_and_Color_Verification(3, "RED");
		
}
	
	

	
	@Test(dependsOnMethods={"Escalation_PRQ_ThirdIssue_Red"},priority = 5, description="  [Verify charges on Third issue are at escalation level.Click on the Charges tab and Verify that Charge Level = 'escalation charge' ]")
	public void Escalation_PRQ_ThirdIssue_Verify_Charges_on_ViewIssue_ChargesScreen_should_Display_at_EscalationLevel() throws Throwable{
		
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::5 Escalation_PRQ_ThirdIssue_Verify_Charges_on_ViewIssue_ChargesScreen_should_Display_at_EscalationLevel ");
		
	//	IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//src//nsf//ecap//xls//IssueMgt_Suite.xlsx");
		
		try{
						
			//Clicking on Charge TAB on View issue page
			fnGet_Element_Enabled("ViewIssueCharges_Charges_Tab");
			fnWait_and_Click("ViewIssueCharges_Charges_Tab");
			
			
			//Waiting for Footer till page load
			fnGet_Element_Enabled("footer");
			Thread.sleep(2000);
			
			//Getting charge amount and stored in a String
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			String ChargeAmountxpath="mainForm:issedittabvw:isschrgamt";
			String ChargeAmount=(String)jse.executeScript(" return document.getElementById('"+ChargeAmountxpath+"').value;");
			String ChargeAmountValue=ChargeAmount.trim();
			
			assert ChargeAmountValue.contains(EscalationChargeValue):"FAILED ==  ThirdIssue --- (View Issue)Charge amount is not matched with (Client Charge Configuration)Escalation Charge Amount";
					
		
		fnApps_Report_Logs("PASSED == ThirdIssue --- (View Issue)Charge amount is matched with (Client Charge Configuration)Escalation Charge Amount [ "+ChargeAmountValue+" ].");
		}catch(Throwable t){
			isTestCasePass = false;
			fnTake_Screen_Shot("ViewIssueEscalationCharge"+EscalationChargeValue);
			fnApps_Report_Logs("FAILED ==  ThirdIssue --- (View Issue)Charge amount is not matched with (Client Charge Configuration)Escalation Charge Amount,Please refer ScreenShot [ "+"ViewIssueEscalationCharge"+EscalationChargeValue+fnScreenShot_Date_format() +" ], Getting Eception >> "+Throwables.getStackTraceAsString(t));
			throw new Exception("FAILED == ThirdIssue ---  (View Issue)Charge amount is not matched with (Client Charge Configuration)Escalation Charge Amount,Please refer ScreenShot [ "+"ViewIssueEscalationCharge"+EscalationChargeValue+fnScreenShot_Date_format() +" ], Getting Eception >> "+Throwables.getStackTraceAsString(t));
			}	
	
			
	}
	
	

	@Test(dependsOnMethods={"Escalation_PRQ_ThirdIssue_Verify_Charges_on_ViewIssue_ChargesScreen_should_Display_at_EscalationLevel"},priority = 6, description="  [Verify charges on First issue are at escalation level.Click on the Charges tab and Verify that Charge Level = 'escalation charge' ]")
	public void Escalation_PRQ_FirstIssue_Verify_Charges_on_ViewIssue_ChargesScreen_should_Display_at_EscalationLevel() throws Throwable{
		
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::6 Escalation_PRQ_FirstIssue_Verify_Charges_on_ViewIssue_ChargesScreen_should_Display_at_EscalationLevel ");
		
//		IssueMgt_Suitexls = new Xls_Reader(System.getProperty("user.dir") + "//src//nsf//ecap//xls//IssueMgt_Suite.xlsx");
		
		
				
		
		
		try{
			
			//Search IssueID and also navigate to ViewIssue Summary Details page 
			fncIssueID_SearchIssueID_and_Navigate_to_ViewIssueSummaryDetailsPageComes(issue_id_green, true);
			
			//Clicking on Charge TAB on View issue page
			fnGet_Element_Enabled("ViewIssueCharges_Charges_Tab");
			fnWait_and_Click("ViewIssueCharges_Charges_Tab");
			
			
			//Waiting for Footer till page load
			fnGet_Element_Enabled("footer");
			
			
			Thread.sleep(2000);
			//Getting charge amount and stored in a String
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			String ChargeAmountxpath="mainForm:issedittabvw:isschrgamt";
			String ChargeAmount=(String)jse.executeScript(" return document.getElementById('"+ChargeAmountxpath+"').value;");
			String ChargeAmountValue=ChargeAmount.trim();
			
			assert ChargeAmountValue.contains(EscalationChargeValue):"FAILED ==  FirstIssue --- (View Issue)Charge amount is not matched with (Client Charge Configuration)Escalation Charge Amount";
					
		
		fnApps_Report_Logs("PASSED == FirstIssue --- (View Issue)Charge amount is matched with (Client Charge Configuration)Escalation Charge Amount [ "+ChargeAmountValue+" ].");
		}catch(Throwable t){
			isTestCasePass = false;
			fnTake_Screen_Shot("ViewIssueEscalationCharge"+EscalationChargeValue);
			fnApps_Report_Logs("FAILED ==  FirstIssue --- (View Issue)Charge amount is not matched with (Client Charge Configuration)Escalation Charge Amount,Please refer ScreenShot [ "+"ViewIssueEscalationCharge"+EscalationChargeValue+fnScreenShot_Date_format() +" ], Getting Eception >> "+Throwables.getStackTraceAsString(t));
			throw new Exception("FAILED == FirstIssue ---  (View Issue)Charge amount is not matched with (Client Charge Configuration)Escalation Charge Amount,Please refer ScreenShot [ "+"ViewIssueEscalationCharge"+EscalationChargeValue+fnScreenShot_Date_format() +" ], Getting Eception >> "+Throwables.getStackTraceAsString(t));
			}	
	
			
	}
	
	

			
	@Test(dependsOnMethods={"Escalation_PRQ_FirstIssue_Verify_Charges_on_ViewIssue_ChargesScreen_should_Display_at_EscalationLevel"},priority = 7, description="  [Verify Escalations ID Generated with three issues.]")
	public void Escalation_PRQ_Generated_With_3_Issues() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("################### Test Case ::::::7 Escalation_PRQ_Generated_With_3_Issues ");
		
	
		try{
			fnGet_Element_Enabled("Escalation_id_link");
			Thread.sleep(500);
			Escalation_Id=fnGet_Field_TEXT("Escalation_id_link").trim();
					try{
						assert (Escalation_Id.contains("E")):"Escalations ID is not Generated >> "+Escalation_Id+"Please refer ScreenShot [ "+Escalation_Id+fnScreenShot_Date_format() +" ]";	
					  
						fnApps_Report_Logs("Escalations ID has been generated === "+Escalation_Id);
					}catch(Throwable t){
						fnTake_Screen_Shot(Escalation_Id);
						throw new Exception(Throwables.getStackTraceAsString(t));
					}
				
					
			//Clicking on Escalation ID link
			fnWait_and_Click("Escalation_id_link");
			
			
	
			fnGet_Element_Enabled("footer");
			
			for(int i=0; i<5; i++){
				Thread.sleep(1500);
				if(driver.findElements(By.xpath(OR_IM.getProperty("ViewEscalation_RelatedIssues_table"))).size()>0){
					break;
				}
				else{
				driver.navigate().refresh();
				Thread.sleep(5000);
				}
			}
			
					
	
			try{
				
				//Verify 8 Newly generated issues exists into the table on Escalation details screen.
				String related_issue_table=fnGet_Field_TEXT("ViewEscalation_RelatedIssues_table").trim();
				assert ((related_issue_table.contains(issue_id_green)) && related_issue_table.contains(issue_id_amber) && related_issue_table.contains(issue_id_red) ):
					"FAILED == Issues IDs are not Exists into the Related Issues Table."+"Please refer ScreenShot [ IssueIds_Not_Exists"+fnScreenShot_Date_format()+" ]";	
				fnApps_Report_Logs("PASSED == All issues ids exists into table");
			}catch(Throwable t){
				fnTake_Screen_Shot("IssueIds_Not_Exists");
				throw new Exception(Throwables.getStackTraceAsString(t));}
			
		}catch(Throwable t){
			isTestCasePass = false;
			fnApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
		
		//Verify Escalation Alert and entered Escalation Id into alert search filter
		fnVerify_ADMIN_Alert_redirect_from_summarydetailsPage("EscalationsUnassigned_text_link","EscalationsUnassigned_issue_id_filter", Escalation_Id);
		
		//verify Escalation ID exists in Alert Table
		fnVerify_ID_Exist_inAlert_Table("EscalationsUnassigned_table_1st_cell","EscalationsUnassigned_text_link", Escalation_Id);	
	}
	
	

	
	@Test(dependsOnMethods={"Escalation_PRQ_Generated_With_3_Issues"},priority = 8, description="  [Change the status from >Initial Record >SATISFACTORY CLOSURE. Mention new status response>Click save. Check -Click on Assign to me Button.]")
	public void Escalation_EditEscalation_Change_status_InitialRecord_to_SatisfactoryClosure() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("####### Test Case ::::8 Escalation_EditEscalation_Change_status_InitialRecord_to_SatisfactoryClosure");
		WebDriverWait waitvar = new WebDriverWait(driver, Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
		

		//Search Escalation and also navigate to ViewEscalation EscalationDetails page
		fncEscalation_SearchEscalation_and_Navigate_till_ViewEscalationDetailsPageComes(Escalation_Id);
		
		
		fnGet_Element_Enabled("Escalation_Summary_assign_to_me_button");
		fnWait_and_Click("Escalation_Summary_assign_to_me_button");
		fnsLoading_Progressingwait(3);
			
		fncEscalation_VerifyChange_Status_on_SummaryDetailsPage(IssueMgt_Suitexls.getCellData("Escalation_PRQ", 2, 26));
		
		fnGet_Element_Enabled("validation_error_msg");
		fncVerify_DataUpdated_Successfully("validation_error_msg", "Summary TAB");	
		
	}
	

	
	@Test(dependsOnMethods={"Escalation_EditEscalation_Change_status_InitialRecord_to_SatisfactoryClosure"},priority = 9, description="  [Issue Management >Click Create Invoices>Enter the customer and find the issues created for the Escalation. Verify the amount,charge level and justified for the issues]")
	public void Escalation_PRQ_Verify_Charges_on_InvocingScreen_for_AllIssues() throws Throwable{
		fnApps_Report_Logs("=========================================================================================================================================");
		fnApps_Report_Logs("####### Test Case ::::9 Escalation_PRQ_Verify_Charges_on_InvocingScreen_for_AllIssues");
		WebDriverWait waitvar = new WebDriverWait(driver, Long.parseLong(CONFIG.getProperty("ElementWaitTime")));
		

		//Click on CreateInvoice Ajax Link
		fnCreateInvoice_Ajax_Link_Click();
		
		//Wait till page load and ClientLookUp_Button enabled
		fnGet_Element_Enabled("Invoicing_ClientLookUp_Button");
		fnWait_and_Click("Invoicing_ClientLookUp_Button");
				
				
		// Enter client name into search filed on client lookup
		fnGet_Element_Enabled("CreateIssue_SiteDetails_client_lookup_value_text_field");
		fnWait_and_Type("CreateIssue_SiteDetails_client_lookup_value_text_field", IssueMgt_Suitexls.getCellData("Escalation_PRQ", 2, 2));
										
		// Selecting first radio button from search result table
		//fnLoading_wait();
		fnsLookup_RadioBttn_Select();
				
		//Waiting for Footer till page load
		fnGet_Element_Enabled("footer");
		
		
		
		//Fetching charge value from web table
		try{
		int ChargeIndexGreen=fnGet_RowIndex_by_CellTextValue("Invoicing_IssuesToInvoice_TableHeader","Invoicing_IssuesToInvoice_Table_CELLHeade",issue_id_green,issue_id_green);
			
		System.out.println("ChargeIndexGreen    "+ChargeIndexGreen);
		
		Thread.sleep(1000);
		//Getting charge amount and stored in a String
		JavascriptExecutor jse = (JavascriptExecutor)driver;
							  //=mainForm:issInvListCnfrm:87:issInvEnfChrgAmt_input
		String ChargeXpathGreen="mainForm:issInvListCnfrm:"+ChargeIndexGreen+":issInvEnfChrgAmt_input";
		String ChargeAmountGreen=(String)jse.executeScript(" return document.getElementById('"+ChargeXpathGreen+"').value;");
		ChargeAmountGreenValue=ChargeAmountGreen.trim();
		
		System.out.println("ChargeXpathGreen      "+ChargeXpathGreen);
		

		fnApps_Report_Logs("PASSED == Charge Amount is fetched from Invoicing(Issues to Invoice) >> Issue Charge [ "+ChargeAmountGreenValue+" ]."+" >> Escalation Charge [ "+EscalationChargeValue+" ].");
		}catch(Throwable t){
			isTestCasePass = false;
			fnTake_Screen_Shot(ChargeAmountGreenValue+EscalationChargeValue);
			fnApps_Report_Logs("FAILED == FirstIssue Unable to Fetch Charge Amount from Invoicing(Issues to Invoice) Page,Please refer ScreenShot [ "+ChargeAmountGreenValue+EscalationChargeValue+fnScreenShot_Date_format() +" ], Getting Eception >> "+Throwables.getStackTraceAsString(t));
			throw new Exception("FAILED == FirstIssue Unable to Fetch Charge Amount from Invoicing(Issues to Invoice) Page,Please refer ScreenShot [ "+ChargeAmountGreenValue+EscalationChargeValue+fnScreenShot_Date_format() +" ], Getting Eception >> "+Throwables.getStackTraceAsString(t));
			}	
		
		
		
		
		
		//Verify charge amount for First issue ID
		try{
		
		assert ChargeAmountGreenValue.contains(EscalationChargeValue):"FAILED ==  FirstIssue --- (View Issue)Charge amount is not matched with (Client Charge Configuration)Escalation Charge Amount";
		fnApps_Report_Logs("PASSED == FirstIssue --- (View Issue)Charge amount is matched with (Client Charge Configuration)Escalation Charge Amount [ "+ChargeAmountGreenValue+" ].");
		}catch(Throwable t){
			isTestCasePass = false;
			fnTake_Screen_Shot("ChargeAmountGreenValue"+issue_id_green);
			fnApps_Report_Logs("FAILED ==  FirstIssue --- (View Issue)Charge amount is not matched with (Client Charge Configuration)Escalation Charge Amount,Please refer ScreenShot [ "+"ChargeAmountGreenValue"+issue_id_green+fnScreenShot_Date_format() +" ].");
			throw new Exception("FAILED == FirstIssue ---  (View Issue)Charge amount is not matched with (Client Charge Configuration)Escalation Charge Amount,Please refer ScreenShot [ "+"ChargeAmountGreenValue"+issue_id_green+fnScreenShot_Date_format() +" ].");
			}	
	

		
		//############################################Verify charge amount for Second issue ID
		/*try{
			
			fnGet_Element_Enabled("Pagination_PageChangeTEXT");
			fnWait_and_Click("Pagination_MoveToFirstPage");
			for(int m=0; !driver.findElement(By.xpath(OR_IM.getProperty("Pagination_PageChangeTEXT"))).getText().contains("1"); m++){
				Thread.sleep(500);
				
				if(m>Long.parseLong(CONFIG.getProperty("ElementWaitTime"))){
					break;}
				}
			}catch(Throwable t){
				isTestCasePass = false;
				fnTake_Screen_Shot("Footerpagelinkfailed2");
				fnApps_Report_Logs("FAILED ==  SecondIssue --- Clicking on Pagination page link is getting failed,Please refer ScreenShot [ "+"Footerpagelinkfailed2"+fnScreenShot_Date_format() +" ], Getting Eception >> "+Throwables.getStackTraceAsString(t));
				throw new Exception("FAILED == SecondIssue ---  (View Issue)Charge amount is not matched with (Client Charge Configuration)Escalation Charge Amount,Please refer ScreenShot [ "+"Footerpagelinkfailed2"+fnScreenShot_Date_format() +" ], Getting Eception >> "+Throwables.getStackTraceAsString(t));
				}	
			
			*/
		
			
		try{
			
		fnsWait_and_Clear("Invoicing_IssuesToInvoice_IssueRefFilter");
		fnWait_and_Type("Invoicing_IssuesToInvoice_IssueRefFilter",issue_id_amber);
		fnsLoading_Progressingwait(2);
		
		
		int ChargeIndex=fnGet_RowIndex_by_CellTextValue("Invoicing_IssuesToInvoice_TableHeader","Invoicing_IssuesToInvoice_Table_CELLHeade",issue_id_amber,issue_id_amber);
			
		Thread.sleep(1000);
		//Fetching charge amount and stored in a String
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		String ChargeXpathAmber="mainForm:issInvListCnfrm:"+ChargeIndex+":issInvEnfChrgAmt_input";
		String ChargeAmountAmber=(String)jse.executeScript(" return document.getElementById('"+ChargeXpathAmber+"').value;");
		ChargeAmountAmberValue=ChargeAmountAmber.trim();
		System.out.println("ChargeXpathAmber      "+ChargeXpathAmber);
		fnApps_Report_Logs("PASSED == Charge Amount is fetched from Invoicing(Issues to Invoice) >> Issue Charge [ "+ChargeAmountAmberValue+" ]."+" >> Escalation Charge [ "+EscalationChargeValue+" ].");
		}catch(Throwable t){
			isTestCasePass = false;
			fnTake_Screen_Shot(ChargeAmountGreenValue+EscalationChargeValue);
			fnApps_Report_Logs("FAILED == SecondIssue Unable to Fetch Charge Amount from Invoicing(Issues to Invoice) Page,Please refer ScreenShot [ "+ChargeAmountGreenValue+EscalationChargeValue+fnScreenShot_Date_format() +" ], Getting Eception >> "+Throwables.getStackTraceAsString(t));
			throw new Exception("FAILED == SecondIssue Unable to Fetch Charge Amount from Invoicing(Issues to Invoice) Page,Please refer ScreenShot [ "+ChargeAmountGreenValue+EscalationChargeValue+fnScreenShot_Date_format() +" ], Getting Eception >> "+Throwables.getStackTraceAsString(t));
			}	
		
		
		//Verify second issue  charge value with escalation value.
		try{
		assert ChargeAmountAmberValue.contains(EscalationChargeValue):"FAILED ==  SecondIssue --- (View Issue)Charge amount is not matched with (Client Charge Configuration)Escalation Charge Amount";
		fnApps_Report_Logs("PASSED == SecondIssue --- (View Issue)Charge amount is matched with (Client Charge Configuration)Escalation Charge Amount [ "+ChargeAmountAmberValue+" ].");
		}catch(Throwable t){
			isTestCasePass = false;
			fnTake_Screen_Shot("ChargeAmountAmberValue"+issue_id_amber);
			fnApps_Report_Logs("FAILED ==  SecondIssue --- (View Issue)Charge amount is not matched with (Client Charge Configuration)Escalation Charge Amount,Please refer ScreenShot [ "+"ChargeAmountAmberValue"+issue_id_amber+fnScreenShot_Date_format() +" ].");
			throw new Exception("FAILED == SecondIssue ---  (View Issue)Charge amount is not matched with (Client Charge Configuration)Escalation Charge Amount,Please refer ScreenShot [ "+"ChargeAmountAmberValue"+issue_id_amber+fnScreenShot_Date_format() +" ].");
			}	
			

		//#################################################Verify charge amount for Third issue ID
		/*try{
			fnGet_Element_Enabled("Pagination_PageChangeTEXT");
			fnWait_and_Click("Pagination_MoveToFirstPage");	
			for(int m=0; !driver.findElement(By.xpath(OR_IM.getProperty("Pagination_PageChangeTEXT"))).getText().contains("1"); m++){
				Thread.sleep(500);
				
				if(m>Long.parseLong(CONFIG.getProperty("ElementWaitTime"))){
					break;}
				}
		}catch(Throwable t){
			isTestCasePass = false;
			fnTake_Screen_Shot("Footerpagelinkfailed3");
			fnApps_Report_Logs("FAILED ==  ThirdIssue --- Clicking on Pagination page link is getting failed,Please refer ScreenShot [ "+"Footerpagelinkfailed3"+fnScreenShot_Date_format() +" ].");
			throw new Exception("FAILED == ThirdIssue ---  (View Issue)Charge amount is not matched with (Client Charge Configuration)Escalation Charge Amount,Please refer ScreenShot [ "+"Footerpagelinkfailed3"+fnScreenShot_Date_format() +" ].");
			}	
		*/
			
			
		try{
			
		fnsWait_and_Clear("Invoicing_IssuesToInvoice_IssueRefFilter");
		fnWait_and_Type("Invoicing_IssuesToInvoice_IssueRefFilter",issue_id_red);
		fnsLoading_Progressingwait(2);
			
		int ChargeIndex=fnGet_RowIndex_by_CellTextValue("Invoicing_IssuesToInvoice_TableHeader","Invoicing_IssuesToInvoice_Table_CELLHeade",issue_id_red,issue_id_red);
		Thread.sleep(1000);		
		//Getting charge amount and stored in a String
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		String ChargeXpathRed="mainForm:issInvListCnfrm:"+(ChargeIndex)+":issInvEnfChrgAmt_input";
		String ChargeAmountRed=(String)jse.executeScript(" return document.getElementById('"+ChargeXpathRed+"').value;");
		ChargeAmountRedValue=ChargeAmountRed.trim();
		System.out.println("ChargeXpathRed      "+ChargeXpathRed);
		System.out.println("Invoic  issue amount value  "+ChargeAmountRedValue);
		fnApps_Report_Logs("PASSED == Charge Amount is fetched from Invoicing(Issues to Invoice) >> Issue Charge [ "+ChargeAmountRedValue+" ]."+" >> Escalation Charge [ "+EscalationChargeValue+" ].");
		}catch(Throwable t){
			isTestCasePass = false;
			fnTake_Screen_Shot(ChargeAmountGreenValue+EscalationChargeValue);
			fnApps_Report_Logs("FAILED == ThirdIssue Unable to Fetch Charge Amount from Invoicing(Issues to Invoice) Page,Please refer ScreenShot [ "+ChargeAmountGreenValue+EscalationChargeValue+fnScreenShot_Date_format() +" ], Getting Eception >> "+Throwables.getStackTraceAsString(t));
			throw new Exception("FAILED == ThirdIssue Unable to Fetch Charge Amount from Invoicing(Issues to Invoice) Page,Please refer ScreenShot [ "+ChargeAmountGreenValue+EscalationChargeValue+fnScreenShot_Date_format() +" ], Getting Eception >> "+Throwables.getStackTraceAsString(t));
			}	
		
		
		//Verify second issue  charge value with escalation value.
		try{
		
		assert ChargeAmountRedValue.contains(EscalationChargeValue):"FAILED ==  ThirdIssue --- (View Issue)Charge amount is not matched with (Client Charge Configuration)Escalation Charge Amount";
		fnApps_Report_Logs("PASSED == ThirdIssue --- (View Issue)Charge amount is matched with (Client Charge Configuration)Escalation Charge Amount [ "+ChargeAmountRedValue+" ].");
		}catch(Throwable t){
			isTestCasePass = false;
			fnTake_Screen_Shot("ChargeAmountRedValue"+issue_id_red);
			fnApps_Report_Logs("FAILED ==  ThirdIssue --- (View Issue)Charge amount is not matched with (Client Charge Configuration)Escalation Charge Amount,Please refer ScreenShot [ "+"ChargeAmountRedValue"+issue_id_red+fnScreenShot_Date_format() +" ].");
			throw new Exception("FAILED == ThirdIssue ---  (View Issue)Charge amount is not matched with (Client Charge Configuration)Escalation Charge Amount,Please refer ScreenShot [ "+"ChargeAmountRedValue"+issue_id_red+fnScreenShot_Date_format() +" ].");
			}	
			
		
		fnApps_Report_Logs("###################################### [BS - 03]  CreateEscalation_ChargesVerify Script END ########################################### ");
		fnApps_Report_Logs("=========================================================================================================================================");
		
		
	
		}
	

	@AfterTest
	public void reportTestResult() throws Throwable {
		fns_ReportTestResult_atClassLevel(IssueMgt_Suitexls,  (this.getClass().getSimpleName()) );
	}
	
	
	

	@AfterTest
	public void Test_success() throws Throwable{
		driver.quit();
		//WindowsUtils.tryToKillByName("IEDriverServer.exe");
		
		Thread.sleep(5000);
	}
	
	
}	
	
	
	
	
	
	
	
