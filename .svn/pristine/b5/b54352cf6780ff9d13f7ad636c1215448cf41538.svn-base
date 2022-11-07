package nsf.ecap.Agriculture_suite;


//https://ecapGG:NSFukiag1@test.globalgap.org/globalgapaxis/services/Globalgap?wsdl

import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import nsf.ecap.Health_Science_suite.TestSuiteBase_HealthScience;
import nsf.ecap.ISR_suite.TestSuiteBase_ISR_suite;
import nsf.ecap.util.ErrorUtil;
import nsf.ecap.util.TestUtil;
import nsf.ecap.util.Xls_Reader;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;

//BS-01
public class Create_Membership extends TestSuiteBase_Agriculture_suite {

	// Runmode of test case in a suite
	@BeforeTest
	@Parameters({ "className" })
	public  void checkTestSkip(String className) throws Throwable {

		try {

			if (className.isEmpty()) {
				className = this.getClass().getSimpleName();

			}
			//classNameText = className;
			setClassNameText( className);
			currentScriptCode = fnpMappingClassNameWithScenarioCode(classNameText).trim();

			fnpMymsg("             ");
			fnpMymsg("             ");
			fnpMymsg("             ");
			fnpMymsg("             ");
			fnpMymsg("             ");
			fnpMymsg("=========================================================================================================================================");
			fnpMymsg("Checking Runmode of testcase '" + className + "' .");

			if (!TestUtil.isTestCaseRunnable(Agriculture_suitexls, className)) {

				fnpMymsg("Skipping Test Case" + className + " as runmode set to NO");// logs
				fnpMymsg("=========================================================================================================================================");
				throw new SkipException("Skipping Test Case" + className + " as runmode set to NO");// reports
			}

			fnpMymsg("Going to execute the script for  '" + className + "'  as runmode set to Yes");// logs

			
			fnpLoadHashData(hashXlData, currentSuiteXls, className, 2);
			
			fail = false;
			isTestPass = true;

		}

		catch (SkipException sk) {
			skip = true;
			String stackTrace = Throwables.getStackTraceAsString(sk);
			String errorMsg = sk.getMessage();
			Exception c = new Exception(stackTrace);
			ErrorUtil.addVerificationFailure(c);
			throw new SkipException(errorMsg + " \n\nSo,Skipping Test Case" + className);// reports
		} catch (Throwable t) {
			fnpCommonFinalCatchBlock(t, "  checkTestSkip method  is fail . ", "checkTestSkipFail");

		}

	}

	@Test(priority = 1)
	public void Create() throws Throwable {

		fnpMymsg(" **************************************************************");
		fnpMymsg(" Executing Create");

		try {
			
			start = new Date();
			 
			//New Request from Sudhir in mail having subject "Today's  iAg Status -16 April-2019 --FAILED"
			if (!iAgJobPausedAlreadyFlag) {
				
				/*
				 * TestSuiteBase_ISR_suite.fnpProcessJob("CompleteAutoAIJob","Pause","Paused");
				 * iAgJobPausedAlreadyFlag=true;
				 */
				
				env=CONFIG.getProperty("Environment");
				String urlToOpenForJob;
				if (env.equalsIgnoreCase("Test")) {
					urlToOpenForJob=CONFIG.getProperty("TEST_Jobs_site_url");
				}else{
					urlToOpenForJob=CONFIG.getProperty("STAGE_Jobs_site_url");
				}
				
				String userName = CONFIG.getProperty("adminUsername");
				String password = CONFIG.getProperty("adminPassword");
				
			TestSuiteBase_ISR_suite.fnpProcessJob(urlToOpenForJob,"",userName,password,"CompleteAutoAIJob","Pause","Paused");
				iAgJobPausedAlreadyFlag=true;
				
				
				
			}

			
			
			
			//**** To Cancel the membership (so that the test can be repeated)  through front end****/
			//callCompleteTheAlreadyCreatedMembership();
			
			
			//**** To Cancel the membership (so that the test can be repeated) through backend ***
		//	fnpCleanMembershipUsingQueries(hashXlData.get("Client").trim(),hashXlData.get("Standard_Code").trim(),hashXlData.get("Member_Type").trim());
		
			hashXlData.clear();
			
			String className = "Create_Membership";
			
			fnpLoadHashData(hashXlData, currentSuiteXls, className, 2);
			Hashtable table = new Hashtable(hashXlData);

			
			fnpLaunchBrowserAndLogin();
			

			String ClientNo_text;
	
			 ClientNo_text =fnpCreateClientOnly( table);
			 
			 fnpLoadHashData(hashXlData, currentSuiteXls, className, 2);
			 hashXlData.put("Client", ClientNo_text);
			

						
			// *******Click Create Memebrship from top menu  ****/
			// Step =>Hover over the Menu option from the Home Page and select Create Membership
			fnpCommonClickTopMainMenu("Menu", "FirstRefrenceLinkJustBelowToMenu_foriAG", "CreateMembershipLink", "ClientLKPBtn");
			

			// ******* Verify Create Membership/WO screen is displayed *******/
			//Step =>The Create Membership/WO screen is displayed
			fnpWaitForVisible("CreateMembershipWOLabel_heading");
			String headingName=fnpGetText_OR("CreateMembershipWOLabel_heading");
			Assert.assertEquals(headingName, "Create Membership/WO", "Header is not matched exectly.");
			
			
			/// *******Selecting the client from lookup *******/
			//Step =>From the field headed Client # Name click on the (magnifying glass) search icon
			fnpClick_OR("ClientLKPBtn_id");
			fnpSearchNSelectFirstRadioBtn(1, (String) hashXlData.get("Client"), 1);
			String EnteredClient = fnpWaitTillTextBoxDontHaveValue("Ag_ClientTxtBox", "value");
			Assert.assertTrue(EnteredClient.contains((String) hashXlData.get("Client")), "Client Value is not selected properly from lookup");
			fnpMymsg(" Client value is properly selected from client lookup");
			
			
			
			//Step =>****From the dropdown box headed Select Scheme Provider select ***********/
			fnpPFList("Select_Scheme_Provider_PFList", "Select_Scheme_Provider_PFListOptions", (String) hashXlData.get("Select_Scheme_Provider"));
			//fnpLoading_wait();
			
			
			//Step =>****From the Select Scheme dropdown box select : ***********/
			fnpPFList("Select_Scheme_PFList", "Select_Scheme_PFListOptions", (String) hashXlData.get("Select_Scheme"));
		//	fnpLoading_wait();
			
			
			fnpLoading_wait();
			if (fnpCheckElementDisplayedImmediately("CreateMembership_BillingOffice_PFList")) {				
				fnpPFList("CreateMembership_BillingOffice_PFList", "CreateMembership_BillingOffice_PFListOptions", (String) hashXlData.get("Billing_Office"));
				fnpLoading_wait();
			}
			
			if (fnpCheckElementDisplayedImmediately("CreateMembership_InvoiceCurrency_PFList")) {				
				fnpPFList("CreateMembership_InvoiceCurrency_PFList", "CreateMembership_InvoiceCurrency_PFListOptions", (String) hashXlData.get("Invoice_Currency"));
				fnpLoading_wait();
			}
			
			
			
			//Step =>****From the Member Type dropdown box select ***********/
			fnpPFList("Member_Type_PFList", "Member_Type_PFListOptions", (String) hashXlData.get("Member_Type"));
			fnpLoading_wait();
			
			
			fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
			
			//Step =>****In the Transferring Membership dropdown box select : ***********/
			fnpPFList("Transferring_Membership_PFList", "Transferring_Membership_PFListOptions", (String) hashXlData.get("Transferring_Membership"));
			fnpLoading_wait_CheckLoadingIconOnlyIniPulse();
			
			//Step =>****Click on the Create button***********/
			fnpClick_OR("Create_Membership_btn");
			


			
			

			isTestPass = true;
			fnpMymsg(" **************************************************************");

		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "  Create   is fail . ", "CreateFail");

		}

	}

	@Test(priority = 2, dependsOnMethods = { "Create" })
	public void Sites_Tab() throws Throwable {
		fnpMymsg(" **************************************************************");
		try {

			
			
			//Step =>############Click on the Edit button############/
			fnpClick_OR("EditWOBtn");
			

/*			fnpMymsg("In site tab============================>");
			fnpPrintAllValuesInHashMap(hashXlData);
*/
			
			fnpAddingdataInSitesTab((String) hashXlData.get("Name_PPU"),(String) hashXlData.get("Unit_Type_PPU"),
					(String) hashXlData.get("Address_Line_1_PPU"),(String) hashXlData.get("Town_City_PPU"),
					(String) hashXlData.get("Country_PPU"),(String) hashXlData.get("State_PPU"),
					(String) hashXlData.get("Postal_Code_PPU"),(String) hashXlData.get("Longitude_PPU"),
					(String) hashXlData.get("Latitude_PPU"),(String) hashXlData.get("Salutation_PPU"),
					(String) hashXlData.get("First_Name_PPU"),(String) hashXlData.get("Last_Name_PPU"),
					(String) hashXlData.get("Job_Title_PPU"),(String) hashXlData.get("Phone_PPU"),
					(String) hashXlData.get("Email_PPU"),(String) hashXlData.get("Fax_PPU"),
					(String) hashXlData.get("Mobile_PPU"),(String) hashXlData.get("Cont_Pref_PPU"));
			



/*			fnpMymsg("In site tab 2============================>");
			fnpPrintAllValuesInHashMap(hashXlData);
*/
			
			fnpAddingdataInSitesTab((String) hashXlData.get("Name_PHU"),(String) hashXlData.get("Unit_Type_PHU"),
					(String) hashXlData.get("Address_Line_1_PHU"),(String) hashXlData.get("Town_City_PHU"),
					(String) hashXlData.get("Country_PHU"),(String) hashXlData.get("State_PHU"),
					(String) hashXlData.get("Postal_Code_PHU"),(String) hashXlData.get("Longitude_PHU"),
					(String) hashXlData.get("Latitude_PHU"),(String) hashXlData.get("Salutation_PHU"),
					(String) hashXlData.get("First_Name_PHU"),(String) hashXlData.get("Last_Name_PHU"),
					(String) hashXlData.get("Job_Title_PHU"),(String) hashXlData.get("Phone_PHU"),
					(String) hashXlData.get("Email_PHU"),(String) hashXlData.get("Fax_PHU"),
					(String) hashXlData.get("Mobile_PHU"),(String) hashXlData.get("Cont_Pref_PHU"));
			
			


			
			isTestPass = true;
			fnpMymsg(" **************************************************************");

		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "  Sites_Tab is fail . ", "Sites_Tab");

		}

	}


	@Test(priority = 3, dependsOnMethods = { "Sites_Tab" })
	public void Contacts_Tab() throws Throwable {
		fnpMymsg(" **************************************************************");
		try {
			
			
			//Step =>**** Click on the Contacts tab***********/
			fnpClick_OR("iAg_ContactsTab");
			
			
			//Step =>**** Click on the Add button***********/
			fnpClick_OR("iAg_ContactsTab_ADD_btn");
			
			
			
			int totalRow_beforeAdding=fnpCountRowsInTable("ProducerContactsTable_EditWO");
			
			
			//Step =>**** Click on the Add New button**********/
			fnpClick_OR("iAg_ContactsTab_Add_New_btn");
			
			
			
			
			//Step =>****  From the Salutation dropdown box select : ***********/
			fnpPFList("PC_Salutation_PFList", "PC_Salutation_PFListOptions", (String) hashXlData.get("PC_Salutation"));
			
			
			//Step =>****      In the First Name field enter      :***********/
			fnpType("OR", "PC_FirstName", (String) hashXlData.get("PC_FirstName"));
			
			
			//Step =>****      In the Last Name field enter      :***********/
			fnpType("OR", "PC_LastName", (String) hashXlData.get("PC_LastName"));
			
			
			
			//Step =>****   From the Bill to Office select  : ***********/
			fnpPFList("PC_BillToOffice_PFList", "PC_BillToOffice_PFListOptions", (String) hashXlData.get("PC_Bill_To_Office"));
			
			
			
			//Step =>****      In the Last Name field enter      :***********/
			fnpType("OR", "PC_JobTitle", (String) hashXlData.get("Job_Title"));
			
			
			
			//Step =>**** Click on the check box next to Same Address**********/
			fnpClick_OR("SameAddresscheckBox");
			//fnpGetORObjectX("SameAddresscheckBox").click();
			
			
			//Step =>**** Click on the Save & Close button**********/
			fnpClick_OR("PC_Save_CloseBtn");
			
			
			
/*			
			*//**********Here Assumption is that latest added will be comes to first row  (means added at top i.e. in row 1)
			//****Logic to here to check that first row contains **********//*
			 totalRow=fnpCountRowsInTable("ProducerContactsTable_EditWO");
			 nameColIndex = fnpFindColumnIndex("OperationalUnitsTable_HeaderRow", "Name");
			
			if (totalRow<2) {
				throw new Exception("A new row for a PHU is NOT displayed.");
				
			} else {
					if (totalRow>2) {
						throw new Exception("Not A new row but MULTIPLE rows have been generated for a PHU is displayed. Rows are --"+totalRow);
					} else {

						String nameValueInFirstRow = fnpFetchFromTable("OperationalUnitsTable_EditWO", 1, nameColIndex);
						Assert.assertEquals(nameValueInFirstRow, (String) hashXlData.get("Name_PHU"), "PHU is not added same as 'Name' is not matching. " +
								"Actual is--'"+nameValueInFirstRow+"'  and expected is ---"+(String) hashXlData.get("Name_PHU"));
						
					}
			}
			
			*/
			
			
			
			
			fnpWaitTillThisElementIsNoLongerDisplayedTillMaxWait_OR("PC_Save_CloseBtn",
					"After clicking 'Save' button in pop up of adding producer contacts at contacts tab, " +
							"nothing is happend even after approx. ",Integer.parseInt(CONFIG.getProperty("genMax_waitTime")));

			
			
			
			int totalRow_afterAdding=fnpCountRowsInTable("ProducerContactsTable_EditWO");
			
			if (totalRow_afterAdding !=(totalRow_beforeAdding+1) ) {
				
				throw new Exception("Producer Contacts row has not been added successfully as rows count " +
						"before addding were ---"+totalRow_beforeAdding+ "  and after adding are --"+totalRow_afterAdding);
			} else {
				fnpMymsg("Row has been added successfully in Producer Contacts table.");
			}
			
			
			
			
			
			
			isTestPass = true;
			fnpMymsg(" **************************************************************");

		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "  Contacts_Tab is fail . ", "Contacts_Tab");

		}

	}

	
	
	
	
	@Test(priority = 4, dependsOnMethods = { "Contacts_Tab" })
	public void Products_Coverage_Tab() throws Throwable {
		fnpMymsg(" **************************************************************");
		try {

			
		
			
			//Step =>**** Click on the Products & Coverage tab**********/
			fnpClick_OR("iAg_ProductsCoverageTab");
			
			
			//Step =>**** Click on the Add button**********/
			fnpClick_OR("iAg_RegisteredProducts_AddBtn");
			
			
			
			
			//Step =>****   Click on the Site dropdown box and select  : ***********/
			fnpPFList("AddProduct_Site_PFList", "AddProduct_Site_PFListOptions", (String) hashXlData.get("Site"));
			
			fnpLoading_wait();
			
			//Step =>****    In the Product dropdown box select  : ***********/
			fnpPFList("AddProduct_Product_PFList", "AddProduct_Product_PFListOptions", (String) hashXlData.get("Product"));
			
			fnpLoading_wait();

			//Step =>**** Ensure the check box titled Covered is ticked (by clicking on it if necessary)**********/
			
			String classOfChkBx_covered=fnpGetORObjectX("Category_covered_chkbox").getAttribute("class");
			//ui-chkbox-icon ui-icon ui-icon-blank ui-c
			//ui-chkbox-icon ui-icon ui-icon-check ui-c
			if (classOfChkBx_covered.contains("ui-icon-check")) {
				fnpMymsg("It (convered checkbox) is already checked.");
				
			} else {
				fnpMymsg("It (convered checkbox) is NOT already checked, so going to checked it");
				
/*				fnpGetORObjectX("Category_covered_chkbox").click();
				Thread.sleep(1000);
				*/
				fnpClick_OR("Category_covered_chkbox");				
				fnpLoading_wait();
				
				classOfChkBx_covered=fnpGetORObjectX("Category_covered_chkbox").getAttribute("class");
				if (classOfChkBx_covered.contains("ui-icon-check")) {
					fnpMymsg("It is checked successfully.");
				}else{
					fnpMymsg("It is NOT checked successfully as class does not contains 'ui-icon-check' text after checking it." +
							" Here class is --"+classOfChkBx_covered+", hence failed.");
					throw new Exception("It is NOT checked successfully as class does not contains 'ui-icon-check' text after checking it." +
							" Here class is --"+classOfChkBx_covered+", hence failed.");
				}
			}
			
			
			
			
			

			//Step =>****  Ensure the check box titled uncovered is unticked (by clicking on it if necessary)**********/
			
			String classOfChkBx_uncovered=fnpGetORObjectX("Category_Uncovered_chkbox").getAttribute("class");

			if (!(classOfChkBx_uncovered.contains("ui-icon-check"))) {
				fnpMymsg("It (Unconvered checkbox) is already NOT checked.");
				
			} else {
				fnpMymsg("It (convered checkbox) is  already checked, so going to Unchecked it");
				
/*				fnpGetORObjectX("Category_Uncovered_chkbox").click();
				Thread.sleep(1000);
				*/
				fnpClick_OR("Category_Uncovered_chkbox");				
				fnpLoading_wait();
				
				classOfChkBx_uncovered=fnpGetORObjectX("Category_Uncovered_chkbox").getAttribute("class");
				if (!(classOfChkBx_uncovered.contains("ui-icon-check"))) {
					fnpMymsg("It is Unchecked successfully.");
				}else{
					fnpMymsg("It is NOT Unchecked successfully as class still contains 'ui-icon-check' text after Unchecking it." +
							" Here class is --"+classOfChkBx_covered+", hence failed.");
					throw new Exception("It is NOT Unchecked successfully as class still contains 'ui-icon-check' text after Unchecking it." +
							" Here class is --"+classOfChkBx_covered+", hence failed.");
				}
			}
			
			
			
			
			
			
			
			
			
			//Step =>****      In the field titled First Harvest enter      :***********/
			fnpType("OR", "Covered_FirstHarvest", (String) hashXlData.get("Covered_First_Harvest"));
			
			
			
			//Step =>****      In the field titled further Harvest enter      :***********/
			fnpType("OR", "Covered_FurtherHarvest", (String) hashXlData.get("Covered_Further_Harvest"));
			
			
			
			
			//Step =>****      In the field headed Total : enter      :***********/
			fnpType("OR", "Covered_Total", (String) hashXlData.get("Total"));
			fnpLoading_wait();
			
			//Step =>****      Click on the Yes radio button for Harvest Excluded      :***********/          
			fnpGetORObjectX("HarvestExcluded_chkbox_label_Yes").click();
			fnpLoading_wait_specialCase(10);

			//Thread.sleep(1000*30);
			Actions action0 = new Actions(driver);
			action0.moveToElement(fnpGetORObjectX("ProductHandling_label")).doubleClick().build().perform();
			fnpLoading_wait_specialCase(20);
			
			
			//Step =>****   Select Product Handling from excel  : ***********/
			fnpPFList("ProductHandling_PFList", "ProductHandling_PFListOptions", (String) hashXlData.get("Product_Handling"));
			fnpLoading_wait_specialCase(10);
			//Actions action0 = new Actions(driver);
			action0.moveToElement(fnpGetORObjectX("ProductHandling_label")).doubleClick().build().perform();
			fnpLoading_wait_specialCase(20);
			
			//Step =>**** Click on the Destination Countries Add button **********/
			fnpClick_OR("DestinationCountries_AddBtn");
			
			
			//Step =>**** Click on the Check box for Austrailia **********/
			fnpSearchNSelectFirstCheckBox(1, (String) hashXlData.get("Destination_Country"), 1);
			//Thread.sleep(2000);

			//Step =>****  Click on Select and Return **********/
			fnpClickInDialog_OR("SelectAndReturnBtn");
			
			
			
			
			//logic for row added for Destination Country
			int totalRow = fnpCountRowsInTable("Destination_Countries_Table_data");
			int countryColIndex = fnpFindColumnIndex("Destination_Countries_Table_HeaderRow", "Country");
			
			if (totalRow<1) {
				throw new Exception("A new row for a Destination Country is NOT displayed/added.");
				
			} else {
					if (totalRow>1) {
						throw new Exception("Not A new row but MULTIPLE rows have been generated for a Destination Countries are displayed. Rows are --"+totalRow);
					} else {

						String countryInFirstRow = fnpFetchFromTable("Destination_Countries_Table_data", 1, countryColIndex);
						Assert.assertEquals(countryInFirstRow, (String) hashXlData.get("Destination_Country"), "Destination Country is not added same as 'Country' is not matching. " +
								"Actual is--'"+countryInFirstRow+"'  and expected is ---"+(String) hashXlData.get("Destination_Country"));
						
					}
			}
			
			
			

			
			//Step =>****  Click on the Add button in the Packhouses section **********/
			fnpClick_OR("PackHouses_AddBtn");
			
			
			
			//Step =>**** Click on the check box for the PACKHOUSE 1**********/
			fnpSearchNSelectFirstCheckBox(1, (String) hashXlData.get("Pack_House"), 1);
			//fnpWithoutSearchSelectFirstRadioBtn();
			
			
			//Step =>****  Click on Select and Return **********/
			fnpClickInDialog_OR("SelectAndReturnBtn");
			
			fnpWaitTillThisElementIsNoLongerDisplayedTillMaxWait_OR("SelectAndReturnBtn",
					"After clicking 'Select and Return' button in pop up of adding pack house details at product coverage tab, " +
							"nothing is happend even after approx. ",Integer.parseInt(CONFIG.getProperty("genMax_waitTime")));

			
			
			
			//logic for row added for Pack House
			 totalRow = fnpCountRowsInTable("PackHouses_Table_data");
			 int packHouseColIndex = fnpFindColumnIndex("PackHouses_Table_HeaderRow", "Packhouse");
			
			if (totalRow<1) {
				throw new Exception("A new row for a PackHouses is NOT displayed/added.");
				
			} else {
					if (totalRow>1) {
						throw new Exception("Not A new row but MULTIPLE rows have been generated for a PackHouses Countries are displayed. Rows are --"+totalRow);
					} else {

						String packHouseInFirstRow = fnpFetchFromTable("PackHouses_Table_data", 1, countryColIndex);
						Assert.assertEquals(packHouseInFirstRow, (String) hashXlData.get("Pack_House"), "Pack House is not added same as 'Packhouse' is not matching. " +
								"Actual is--'"+packHouseInFirstRow+"'  and expected is ---"+(String) hashXlData.get("Pack_House"));
						
					}
			}
			
			
			
			
			
			
			//Step =>****  Check the No radio button for Parrallel Production **********/
			fnpClick_OR("ParallelProduction_radioBtn_label_NO");
			
			
			
			//Step =>****  Check the No Radio button for Parallel Ownership **********/
			fnpClick_OR("ParallelOwnership_radioBtn_label_NO");
			
			

			
			
			//Step =>****   Click on the Save button in the Add Product popup box **********/
			fnpClick_OR("AddProduct_SaveBtn");

	
			
			fnpWaitTillThisElementIsNoLongerDisplayedTillMaxWait_OR("AddProduct_SaveBtn",
					"After clicking 'Save' button in pop up of adding Product details at product coverage tab, " +
							"nothing is happend even after approx. ",Integer.parseInt(CONFIG.getProperty("genMax_waitTime")));

			
			
			
			//logic for row added for Registered Products
			 totalRow = fnpCountRowsInTable("RegisteredProducts_Table_data");
			 int productsColIndex = fnpFindColumnIndex("RegisteredProducts_Table_HeaderRow", "Product");
			
			if (totalRow<1) {
				throw new Exception("A new row for a Registered Products is NOT displayed/added.");
				
			} else {
					if (totalRow>1) {
						throw new Exception("Not A new row but MULTIPLE rows have been generated for a Registered Products are displayed. Rows are --"+totalRow);
					} else {

						String productCodeWithProductNameInFirstRow = fnpFetchFromTable("RegisteredProducts_Table_data", 1, productsColIndex);
						
						String[] productCodeWithProductName = productCodeWithProductNameInFirstRow.split("-");
						String productInFirstRow;
						if (productCodeWithProductName.length==2) {
							 productInFirstRow=productCodeWithProductNameInFirstRow.split("-")[1].trim();
						} else {
							throw new Exception ("Product Code with Product Name is not displayed " +
									"properly as script is looking for product name after hypen (-). Actual complete value in column" +
									"is --'"+productCodeWithProductNameInFirstRow+"'.");
						}
						
						
						
						Assert.assertEquals(productInFirstRow, (String) hashXlData.get("Product"), "Registered Products is not added same as 'Product' is not matching. " +
								"Actual is--'"+productInFirstRow+"'  and expected is ---"+(String) hashXlData.get("Product"));
						
					}
			}
			
			
			
			
			
			
			
			
			
			
			
			isTestPass = true;
			fnpMymsg(" **************************************************************");

		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "  Products_Coverage_Tab is fail . ", "Products_Coverage_Tab");

		}

	}

	
	
	
	@Test(priority = 5, dependsOnMethods = { "Products_Coverage_Tab" })
	public void Services_Tab() throws Throwable {
		fnpMymsg(" **************************************************************");
		try {

			
			
			
			//Step =>****    Click on the Services Tab **********/
			fnpClick_OR("iAg_ServicesTab");
			
			
			
			//Step =>****    Click on the Add button**********/
			fnpClick_OR("ServicesTab_AddBtn");
			
			
			
			
			//Step =>****    In the Service dropdown box select   : ***********/
			fnpPFList("Service_PFList", "Service_PFListOptions", (String) hashXlData.get("Service"));
	

			
			//Step =>****    Auditor or Inspector   : ***********/	

			fnpPFListUsingFieldName("Auditor or Inspector",(String) hashXlData.get("Auditor_or_Inspector")) ;
			
			
			
			
			//Step =>****    In the Employee or Subcontractor dropdown box select   : ***********/
			fnpPFListUsingFieldName("Employee or Subcontractor",(String) hashXlData.get("Employee_or_Subcontractor")) ;
			
			
			
			//Step =>****      In the Name field enter      :***********/
			fnpType("OR", "Service_Name", (String) hashXlData.get("Service_Name"));
			
			
			//Step =>****     Click on the Save button**********/
			fnpClick_OR("Service_SaveBtn");
			
			fnpWaitTillThisElementIsNoLongerDisplayedTillMaxWait_OR("Service_SaveBtn",
					"After clicking 'Save' button in pop up of adding  details in popup at  services tab, " +
							"nothing is happend even after approx. ",Integer.parseInt(CONFIG.getProperty("genMax_waitTime")));

			
			
			
			//logic for row added
			int totalRow = fnpCountRowsInTable("ServicesTable_EditWO");
			 int serviceColIndex = fnpFindColumnIndex("ServicesTable_HeaderRow", "Service");
			
			if (totalRow<1) {
				throw new Exception("A new row for a Services is NOT displayed/added.");
				
			} else {
					if (totalRow>1) {
						throw new Exception("Not A new row but MULTIPLE rows have been generated for a Services is displayed. Rows are --"+totalRow);
					} else {

						String serviceValueInFirstRow = fnpFetchFromTable("ServicesTable_EditWO", 1, serviceColIndex);
						Assert.assertEquals(serviceValueInFirstRow, (String) hashXlData.get("Service"), "Service is not added same as 'Service' is not matching. " +
								"Actual is--'"+serviceValueInFirstRow+"'  and expected is ---"+(String) hashXlData.get("Service"));
						
					}
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			isTestPass = true;
			fnpMymsg(" **************************************************************");

		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "  Services_Tab is fail . ", "Services_Tab");

		}

	}

	
	
	
	
	@Test(priority = 6, dependsOnMethods = { "Services_Tab" })
	public void Equipment_Tab() throws Throwable {
		fnpMymsg(" **************************************************************");
		try {

			
		
			
			
			//Step =>****     Click on the Equipment tab**********/
			fnpClick_OR("iAg_EquipmentTab");
			
			//Step =>****  Click on the Add button**********/
			fnpClick_OR("EquipmentTab_AddBtn");
			
			
			
			
			//Step =>****    In the Service dropdown box select   : ***********/
			String equipmentValue=(String) hashXlData.get("Equipment");
			boolean doingFurtherStepsINEquipmentTab=true;
			try{				
			fnpPFList("Equipment_PFList", "Equipment_PFListOptions", equipmentValue);
			doingFurtherStepsINEquipmentTab=true;
			}catch(Throwable t){
				if(t.getMessage().contains("might be Value ["+equipmentValue+"] is not present in List")){
					fnpMymsg("As there is no value present in equipment tab, so instead of throwing error, we are skipping further steps in equipment tab.");
					doingFurtherStepsINEquipmentTab=false;
				}else{
					throw t;
				}
			}
			
			
			if (doingFurtherStepsINEquipmentTab) {
				
				//Step =>****       In the name field enter      :***********/
				fnpType("OR", "Equipment_PersonName", (String) hashXlData.get("Equip_Person_Name"));
				
				
				//Step =>****  In the Address field enter       :***********/
				fnpType("OR", "Equipment_Address", (String) hashXlData.get("Equip_Address"));
				
				
				//Step =>****  In the Distance from farm (km) field enter       :***********/
				fnpType("OR", "Equipment_DistanceFromFarm", (String) hashXlData.get("Equip_Distance_From_Farm"));
				
				
				
				
				//Step =>****     Click on the Save button**********/
				fnpClick_OR("Equipment_SaveBtn");
				
				
				
				fnpWaitTillThisElementIsNoLongerDisplayedTillMaxWait_OR("Equipment_SaveBtn",
						"After clicking 'Save' button in pop up of adding equipments details in popup at  equipment tab, " +
								"nothing is happend even after approx. ",Integer.parseInt(CONFIG.getProperty("genMax_waitTime")));

				
				
				
				//logic for row added
				int totalRow = fnpCountRowsInTable("EquipmentTable_EditWO");
				int nameColIndex = fnpFindColumnIndex("EquipmentTable_HeaderRow", "Name");
				
				if (totalRow<1) {
					throw new Exception("A new row for a Equipment is NOT displayed/added.");
					
				} else {
						if (totalRow>1) {
							throw new Exception("Not A new row but MULTIPLE rows have been generated for a Equipment is displayed. Rows are --"+totalRow);
						} else {

							String nameInFirstRow = fnpFetchFromTable("EquipmentTable_EditWO", 1, nameColIndex);
							Assert.assertEquals(nameInFirstRow, (String) hashXlData.get("Equip_Person_Name"), "Equipment is not added same as 'Person' is not matching. " +
									"Actual is--'"+nameInFirstRow+"'  and expected is ---"+(String) hashXlData.get("Equip_Person_Name"));
							
						}
				}
				
				
			} else {

				fnpClick_OR("EquipmentTab_cancelLinkInPopup");
				
			}

			
			
			
			
			
			
			
			
			isTestPass = true;
			fnpMymsg(" **************************************************************");

		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "  Equipment_Tab is fail . ", "Equipment_Tab");

		}

	}

	
	
	
	
	@Test(priority = 7, dependsOnMethods = { "Equipment_Tab" })
	public void Additional_Info_Tab() throws Throwable {
		fnpMymsg(" **************************************************************");
		try {

			
			//Step =>****  Click on the Additional Info tab**********/
			fnpClick_OR("iAg_AdditionalInfoTab");
			
			
			
			//Step =>****  Click on the No radio buttons for Parallel Production (CC) **********/
			fnpClick_OR("ParallelProduction_CC_radioBtn_label_NO");
			
			
			//Step =>**** Click on the No radio buttons for Parallel Ownership (CC) **********/
			fnpClick_OR("ParallelOwnership_CC_radioBtn_label_NO");
			
		

			
			
			
			
			isTestPass = true;
			fnpMymsg(" **************************************************************");

		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "  Additional_Info_Tab is fail . ", "Additional_Info_Tab");

		}

	}

	
	
	
	
	@Test(priority = 8, dependsOnMethods = { "Additional_Info_Tab" })
	public void Membership_Submission() throws Throwable {
		fnpMymsg(" **************************************************************");
		try {

			
			
			//Step =>**** Click 'Submit' to confirm and submit manually created membership  **********/
			fnpClick_OR("Membership_SubmitBtn");
			
			
			
			
			
			String workOrderNo = fnpGetText_OR("Membership_WO_No");
			fnpMymsg("Membership has been submitted successfully as work order has been generated corresponding to it..");
			fnpMymsg("Newly created WO no. is:" + workOrderNo);
			
			//Thread.sleep(2000);
			//
			
			
			alreadyCreatedMembershipThroughFirstScript=workOrderNo;
			
			
			isTestPass = true;
			fnpMymsg(" **************************************************************");

		} catch (Throwable t) {

			fnpCommonFinalCatchBlock(t, "  Membership_Submission is fail . ", "Membership_Submission");

		}

	}

	
	

	

	@AfterTest
	public void reportTestResult() throws Throwable {
		
/*		if (driver!=null) {
			
		}
		*/
		
		//Added on 19-02-2018 so that no one can use our created work order, so drop/delete everything
		//**** To Cancel the membership (so that the test can be repeated) through backend ****/
	//	fnpCleanMembershipUsingQueries(hashXlData.get("Client").trim(),hashXlData.get("Standard_Code").trim(),hashXlData.get("Member_Type").trim());
	

		
		
		
		int rowNum = currentSuiteXls.getCellRowNum("Test Cases", "TCID", this.getClass().getSimpleName());
		currentSuiteXls.setCellData("Test Cases", "Last_Execution", rowNum, fnReturnDateWithTimeForExcel());

		// Add Results column to test case sheet
		if (isTestPass) {
			TestUtil.reportDataSetResult(currentSuiteXls, "Test Cases", TestUtil.getRowNum(currentSuiteXls, this.getClass().getSimpleName()), "PASS");
			currentSuiteXls.setCellData("Test Cases", "Last_Successful_Execution", rowNum, fnReturnDateWithTimeForExcel());
			fnpElapsedTime(currentSuiteName, this.getClass().getSimpleName(), currentScriptCode, "PASS");
		} else {
			TestUtil.reportDataSetResult(currentSuiteXls, "Test Cases", TestUtil.getRowNum(currentSuiteXls, this.getClass().getSimpleName()), "FAIL");
			fnpElapsedTime(currentSuiteName, this.getClass().getSimpleName(), currentScriptCode, "FAIL");
		}
		fnpMymsg("=========================================================================================================================================");

		hashXlData.clear();

	//	fnpCommonCloseBrowsersAndKillProcess();
		
		

		

	}

}