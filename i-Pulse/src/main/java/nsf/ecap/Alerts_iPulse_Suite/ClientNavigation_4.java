package nsf.ecap.Alerts_iPulse_Suite;

import static org.testng.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.google.common.base.Throwables;
import nsf.ecap.util.TestUtil;

public class ClientNavigation_4 extends TestSuiteBase_Alerts{
	public static int count = -1;
	public static boolean skip = true;
	public static String ScenarioName = null;
	public static String CaseNo = null;
	public static String ScenaioName = null;
	public static String ORG_Unit = null;
	public static String Client_22490 = null;
	public static String ISR_Client_ID = null;
	public static String FoodEquipment_Client_ID = null;
	public static String searchClientId = null;
	String oldTab="";
	ArrayList<String> samTab=null;
	
	@Parameters({ "className" })
	@BeforeTest
	public void checkTestSkip(String className) throws Exception {
		if (className.isEmpty()) {
			className = this.getClass().getSimpleName();
		}
		fnsCheckClassLevelTestSkip(className);
	}
	
	
	@Test(dataProvider = "getTestData", priority = 0)
	public void searchClientNavigationTest(String Case_No, String Scenario_Name,String Client) throws Throwable{
		fnsApps_Report_Logs(" **************************************************************");
		boolean isCorporateParentLinkHeaderAvailble=false;
		boolean flag=false;
		count++;
		ScenaioName = Scenario_Name.trim();
		ORG_Unit = (Scenario_Name.split("\\_")[0]).trim();
		searchClientId=Client;
		/*if(ScenaioName.equalsIgnoreCase("Client_ISR")){
			ISR_Client_ID = fnsReturn_ExcelCellValue_ByMatchingColumnName1(this.getClass().getSimpleName(), "ISR_Client").split("\\,")[0].trim();
			searchClientId=ISR_Client_ID;
			fnsApps_Report_Logs("Running Script for Client : "+ISR_Client_ID+" and test case is : "+ScenaioName);
		}else if(ScenaioName.equalsIgnoreCase("Client_FoodEquipment")){
			FoodEquipment_Client_ID = fnsReturn_ExcelCellValue_ByMatchingColumnName1(this.getClass().getSimpleName(), "FoodEquipment_Client").split("\\,")[0].trim();
			searchClientId=FoodEquipment_Client_ID;
			fnsApps_Report_Logs("Running Script for Client : "+FoodEquipment_Client_ID+" and test case is : "+ScenaioName);
		}*/
		try {
			if (!(runmodes[count].equalsIgnoreCase("Y"))) {
				fnsApps_Report_Logs("****************************************************************************************************************************************");
				fnsApps_Report_Logs("################################## Runmode of " + (Case_No)+" for Scenario ["+Scenario_Name+"]  is set to NO, So Skipping this Case.");
				skip=true;
				throw new SkipException("Runmode of " + (Case_No)+" for Scenario ["+Scenario_Name+"]  is set to NO, So Skipping this Case.");
			}else{
				skip=false;
				fnsApps_Report_Logs("****************************************************************************************************************************************");
				fnsApps_Report_Logs("################################## Execution Logs of  " + (Case_No)+" for Scenario ["+Scenario_Name+"].");
				if (!IsBrowserPresentAlready) {
				//***************************** Login Operation & Search Client **************************
				Login_and_searchClientLookup_Operation(searchClientId);
				//***************************** Client_ISR(0Z762) Script *********************************
				if(ScenaioName.equalsIgnoreCase("Client_ISR")){
					flag=fngIsSelectedTab(OR_Navi.getProperty("ViewClient_ClientInfoTab_Selected"),"Client Info");
					Assert.assertTrue(flag,"Client Info Tab is not selected of View Client Page. ");
					
					fngClickByXpath(OR_Navi.getProperty("ViewClient_ClientInHeader"));
					fnsApps_Report_Logs("Clicked on client in Header of View Client Page.");
					fngCheckErrMsgTest("FAILED-AfterClientInHeaderOfViewClientPage");
					isCorporateParentLinkHeaderAvailble=fngIsElementVisible(OR_Navi.getProperty("ViewClient_CorporateParentLinkInHeader"));
					if(isCorporateParentLinkHeaderAvailble){
						fngClickByXpath(OR_Navi.getProperty("ViewClient_CorporateParentLinkInHeader"));
						fnsApps_Report_Logs("Clicked Corporate Parent Link in header of View Client Page.");
						fngCheckErrMsgTest("FAILED-AfterClickCorporateParentLinkInHeader");
						boolean isClientOpened=fngIsElementVisible(OR_Navi.getProperty("ViewClient_ClientNo_InClientInfoTab"));
						Assert.assertTrue(isClientOpened,"Client No in Client Info tab is not available.");
						fnsApps_Report_Logs("The Corporate Parent Client get Opened");
					}else{
						fnsApps_Report_Logs("Corporate Parent Link in header is not Availble");
					}
					fngClickByXpath(OR_Navi.getProperty("ViewClient_BackButton"));
					fnsApps_Report_Logs("Clicked Back Button of View client Page.");
					fngCheckErrMsgTest("FAILED-ClickBackButtonOfViewClientPage");
					boolean isCorporateParentLinkDetailScreenAvailble=fngIsElementVisible(OR_Navi.getProperty("ViewClient__ClientInfo_CorporateParentLink"));
					if(isCorporateParentLinkDetailScreenAvailble){
						fngClickByXpath(OR_Navi.getProperty("ViewClient__ClientInfo_CorporateParentLink"));
						fnsApps_Report_Logs("Clicked Corporate Parent Link of Client info Tab in View Client Page.");
						fngCheckErrMsgTest("FAILED-AfterClickCorporateParentLinkClientInfoTabOfViewClientPage");
						fngLoading_wait();
						boolean isClientOpened=fngIsElementVisible(OR_Navi.getProperty("ViewClient_ClientNo_InClientInfoTab"));
						Assert.assertTrue(isClientOpened,"Client is not Opened!!!");
						fnsApps_Report_Logs("The Corporate Client of Client Info tab is Opened");
					}else{
						fnsApps_Report_Logs("Corporate Parent Link in Detail Screen is not Availble");
					}
					//***************************** Corporate Parent Link Operation Finish ************************
					/*fngClickByXpath(OR_Navi.getProperty("ViewClient_BackButton"));
					fnsApps_Report_Logs("Clicked Back Button View client Page.");
					fngCheckErrMsgTest("FAILED-ClickBackButtonOfViewClientPage");*/
					//***************************** Snapshot Tab Operation Start *********************************
					fngLoading_wait();
					SnapshotTab_Operation_ISR();
					CorrespondenceTab_Operation();
					InvoicesTab_Operation();
				}
				//***************************** Client_FoodEquipment(22490) Script *********************************
				if(ScenaioName.equalsIgnoreCase("Client_FoodEquipment")){
						//checking Snapshot tab is selected or not.
						flag=fngIsSelectedTab(OR_Navi.getProperty("ViewClient_SnapshotTab_Selected"),"Snapshot");
						Assert.assertTrue(flag,"Snapshot Tab Must be selected of View Client Page. ");
						
						fngClickByXpath(OR_Navi.getProperty("ViewClient_ClientInHeader"));
						fnsApps_Report_Logs("Clicked on the Header of client of View Client page");
						fngCheckErrMsgTest("FAILED-AfterClientInHeaderOfViewClientPage");
						
						isCorporateParentLinkHeaderAvailble=fngIsElementVisible(OR_Navi.getProperty("ViewClient_CorporateParentLinkInHeader"));
						if(isCorporateParentLinkHeaderAvailble){
							fngClickByXpath(OR_Navi.getProperty("ViewClient_CorporateParentLinkInHeader"));
							fnsApps_Report_Logs("Clicked Corporate Parent Link in header of View Client Page.");
							fngCheckErrMsgTest("FAILED-AfterClickCorporateParentLinkInHeader");
							String openedCorporateClient=fngGetTextValueByXpath(OR_Navi.getProperty("ViewClient_Client_InHeader"));       
							Assert.assertTrue(openedCorporateClient.equals(searchClientId));
							fnsApps_Report_Logs("The Corporate Parent Client get Opened");
						}else{
							fnsApps_Report_Logs("Corporate Parent Link in header is not Availble");
						}
						fngClickByXpath(OR_Navi.getProperty("ViewClient_BackButton"));
						fnsApps_Report_Logs("Clicked Back Button View client Page.");
						fngCheckErrMsgTest("FAILED-ClickBackButtonOfViewClientPage");
						//***************************** Snapshot Tab Operation Start *********************************
						SnapshotTab_Operation_FoodEquipment();
						FacilitiesTab_Operation();
						LinkTab_Operation();
						ContractsTab_Operation();
						NSFOnlineAccountsTab_Operation(searchClientId);
					}
				}
			}
		}catch(SkipException sk){
			throw new SkipException(sk.getMessage());
		}catch(Throwable t){
			fail = true;
			isTestCasePass = false;
			fnsTake_Screen_Shot("Fail_"+ScenaioName);
			fnsApps_Report_Logs(Throwables.getStackTraceAsString(t));
			throw new Exception(Throwables.getStackTraceAsString(t));
		}
	}
	//################################################################## Config Method ############################################################################

		@AfterMethod
		public void reportDataSetResult() {
			if(count>-1){
				if (skip)
					TestUtil.reportDataSetResult(Alerts_iPulse_suitexls, this.getClass().getSimpleName(), count + 2 , "SKIP");
				else if (fail) {
					isTestCasePass = false;
					TestUtil.reportDataSetResult(Alerts_iPulse_suitexls, this.getClass().getSimpleName(), count + 2, "FAIL");
				} else
					TestUtil.reportDataSetResult(Alerts_iPulse_suitexls, this.getClass().getSimpleName(), count + 2, "PASS");
				skip = false;
				fail = false;
			}
		}
		@AfterTest
		public void reportTestResult() throws Throwable {
			fns_ReportTestResult_atClassLevel(Alerts_iPulse_suitexls,  (this.getClass().getSimpleName()) );
		}
		@AfterMethod
		public void QuitBrowser() throws Throwable{
			try{
				driver.quit();
			}catch(Throwable t){
				//nothing to do			
			}		
		}
		@DataProvider
		public Object[][] getTestData() {
			return getExcelData_for_DataProviderTest(Alerts_iPulse_suitexls,this.getClass().getSimpleName());
		}
		//################################################################## Tabs Operation Method ############################################################################
		public void Login_and_searchClientLookup_Operation(String Searchclient) throws Throwable{
		BrowserOpen = fnsBrowserLaunchAndLogin("testscriptuser");
		//***************************** Lookup Operation start *********************************
		fngClickTopMainMenuAndFacilityLookup(OR_Navi.getProperty("MainMenu"),OR_Navi.getProperty("SearchClientAjax"),OR_Navi.getProperty("SearchClient_Facility_LookupBtton"));
		fngEnterValueInTextField(OR_Navi.getProperty("ClientLookup_ClientTextBox"),Searchclient);
		fnsApps_Report_Logs("Entered Client Value into Client TextBox : "+Searchclient);
		fngClickByXpath(OR_Navi.getProperty("ClientLookup_SearchButton"));
		fnsApps_Report_Logs("Clicked clientLookup searchButton");
		fngCheckErrMsgTest("Error-ClickClientLookupSearchBtton");
		fngLoading_wait();
		fngClickByXpath(OR_Navi.getProperty("ClientLookup_SearchedClient_RadioButton"));
		fnsApps_Report_Logs("Clicked Radio button for searched Client# into the Client Lookup.");
		fngCheckErrMsgTest("Error-ClickRadioBttnForSearchedClient");
		fngLoading_wait();
		fngClickByXpath(OR_Navi.getProperty("SearchClient_SearchButton"));
		fnsApps_Report_Logs("Clicked on the Search Button of Search Client Page.");
		fngCheckErrMsgTest("Error-ClickSearchButtonOfSearchClient");
		fngLoading_wait();
		fngClickByXpath(OR_Navi.getProperty("SearchClient_SearchedFacilityLink"));
		fnsApps_Report_Logs("Clicked on Searched Facility Link of Search Client Page");
		fngCheckErrMsgTest("Error-ClickSearchedFacilityLink");
		fngLoading_wait();
		//***************************** Lookup Operation End *********************************
	}
	public void CorrespondenceTab_Operation() throws Throwable{
		//***************************** Corporate Parent Link Operation Start *********************************
		boolean isCorrespondenceTabAvailable=fngIsElementVisible(OR_Navi.getProperty("ViewClient_CorrespondenceTab"));
		if(isCorrespondenceTabAvailable){
			fngClickByXpath(OR_Navi.getProperty("ViewClient_CorrespondenceTab"));
			fnsApps_Report_Logs("Clicked on Correspondence Tab of View Client Page");
			fngCheckErrMsgTest("Error-ClickOnCorrespondenceTabOfViewClientPage");
			List<WebElement> listOfMailLinks=fngReturnWebElementAfterExtractLocatorandValue(OR_Navi.getProperty("VeiwClient_CorrespondenceTab_NumberOfMailLink"));
			int size=listOfMailLinks.size();
			fnsApps_Report_Logs("Availability number of Mail links under the Correspondence Tab : "+size);
			if(size!=0){
				/*String linkText=getTextValueByXpath(OR_Navi.getProperty("MailLink_In_ClientCorrespondence"));
				if(linkText.contains("No records found")){
					fnsApps_Report_Logs("No Record Found, is coming, No Mail Link available in Correspondece Tab.");
				}else{*/
					fngClickByXpath(OR_Navi.getProperty("ViewClient_MailLink_InCorrespondenceTab"));
					fnsApps_Report_Logs("Click on Mail Link under the Correspondence Tab of View Client Page");
					fngCheckErrMsgTest("FAILED-ClickMailLinkInCorrespondenceTab");
					String title=fngGetTextValueByXpath(OR_Navi.getProperty("EmailHistoryDetail_Popup_Tittle"));
					Assert.assertTrue(title.equalsIgnoreCase("Email History Details"));
					fnsApps_Report_Logs("Email Template gets opened");
					fngClickByXpath(OR_Navi.getProperty("EmailHistoryDetail_Popup_CloseButton"));fnsApps_Report_Logs("Email History Detail Popup is close");
					fngCheckErrMsgTest("Error-ClickOnCloseBttnEmailHistoryDetailPopup");
					
					boolean flag=isHitorycalPopupClose(OR_Navi.getProperty("EmailHistoryDetail_Popup_Tittle"));
					assertTrue(flag,"Email History Detail Popup is not Closed!!");
				//}
			}else{
				fnsApps_Report_Logs("No Mail links are available under the Correspondence Tab");
			}
		}else{
			fnsApps_Report_Logs("Correspondence Tab is not available ");
		}
		//***************************** Corporate Parent Link Operation End *********************************
	}
	public void InvoicesTab_Operation() throws Throwable{ 
		boolean isInvoicesTabAvailable=fngIsElementVisible(OR_Navi.getProperty("ViewClient_InvoicesTab"));
		if(isInvoicesTabAvailable){
			fngClickByXpath(OR_Navi.getProperty("ViewClient_InvoicesTab"));
			fnsApps_Report_Logs("Clicked on Invoices Tab of Veiw Client Page.");
			fngCheckErrMsgTest("Error-ClickInvoiceTabOfViewClientPage");
			fngLoading_wait();
			boolean isInvoicesTabInInvoicesTabAvailable=fngIsElementVisible(OR_Navi.getProperty("VIewClient_InvoicesTab_In_InvoicesTab"));
			if(isInvoicesTabInInvoicesTabAvailable){
				String invoicesTableData=fngGetTextValueByXpath(OR_Navi.getProperty("ViewClient_InvoiceTabTable"));
				if(invoicesTableData.contains("No records found")){
					fnsApps_Report_Logs("No Record Found, is coming, No Invoice Link available in Invoices Tab.");
				}else{
					fngClickByXpath(OR_Navi.getProperty("ViewClient_InvoiceTab_InvoiceLink"));
					fnsApps_Report_Logs("Clicked on Invoice link under the Invoice Tab of View Client Page.");
					fngCheckErrMsgForInvoice("After click on the Invoice link of Invoice tab in view Client Page.");
					fngLoading_wait();
				}
			}else{
				fnsApps_Report_Logs("Invoices panel in Invoices Tab of View Client page is not available.");
			}
		}
	}
	
	public void SnapshotTab_Operation_ISR() throws Throwable{
		//***************************** Snapshot Tab Operation Start *********************************
		fngClickByXpath(OR_Navi.getProperty("ViewClient_SnapshotTab"));
		fnsApps_Report_Logs("Clicked SnapshotTab of View Client Page.");
		fngCheckErrMsgTest("FAILED-ClickSnapshotTabOfViewClientPage");
		fngLoading_wait();
		boolean isOpenWOTabAvailable=fngIsElementVisible(OR_Navi.getProperty("ViewClient_SnapshotTab_OpenWOTab"));
		if(isOpenWOTabAvailable){
			String dataInWOWithOpenQuotesTab=fngGetTextValueByXpath(OR_Navi.getProperty("ViewClient_Snapshot_OpenWorkOrders_RowData"));
			if(dataInWOWithOpenQuotesTab.contains("No records found")){
				fnsApps_Report_Logs("No Record found, No WO links are available in WO with Open Quotes panel");
			}else{
				String woFromOpenWoTab=fngCheckAndClickLink(OR_Navi.getProperty("ViewClient_Snapshot_OpenWOsTab_WoLink"),OR_Navi.getProperty("ViewClient_Snapshot_OpenWOs_Table"));
				String openedWO=fngGetTextValueByXpath(OR_Navi.getProperty("WO_InHeader"));
				Assert.assertTrue(woFromOpenWoTab.equalsIgnoreCase(openedWO));
				fngClickByXpath(OR_Navi.getProperty("EditWorkOrder_BackToViewButton"));
				fnsApps_Report_Logs("Clicked Back To View Button of Edit Work Order Page");
				fngCheckErrMsgTest("FAILED-ClickBackToViewButtonOfEditWOPage");
				fngLoading_wait();
				fngClickByXpath(OR_Navi.getProperty("BackButtonXpath"));
				fnsApps_Report_Logs("Clicked Back Button of View Work Order Page.");
				fngCheckErrMsgTest("FAILED-ClickBackButtonViewWOPage");
				fngClickByXpath(OR_Navi.getProperty("ViewClient_SnapshotTab"));
				fnsApps_Report_Logs("Clicked SnapshotTab of View Client Page.");
				fngCheckErrMsgTest("Error-ClickOnSnapshotTabOfViewClientPage");
			}
		}
		//***************************** Audit Link Operation under Audit tab Start *********************************
		boolean isAuditTabAvailable=fngIsElementVisible(OR_Navi.getProperty("ViewClient_Snapshot_AuditTab"));
		if(isAuditTabAvailable){
			String auditTabRows=fngGetTextValueByXpath(OR_Navi.getProperty("ViewClient_Snapshot_Audits_Rows"));
			if(auditTabRows.contains("No records found")){
				fnsApps_Report_Logs("No Record Found, is coming, No Audit & Visit Link available in Audit Tab.");
			}else{
				String clickedAuditNo=fngCheckAndClickLink(OR_Navi.getProperty("ViewClient_Snapshot_Audits_AuditLinks"),OR_Navi.getProperty("ViewClient_Snapshot_Audits_Table"));
				fngLoading_wait();
				//String AuditPageHeader=getTextValueByXpath(OR_Navi.getProperty("ViewAuditPage_Header"));//AuditHeaderXpath
				String openedAuditNo=fngGetTextValueByXpath(OR_Navi.getProperty("ViewAudit_Header_AuditNo"));
				Assert.assertTrue(openedAuditNo.equalsIgnoreCase(clickedAuditNo),"Clicked & Open Audit No is Mismatched!!");
				fnsApps_Report_Logs("Corresponding audit is opened !!!");
			
				fngClickByXpath(OR_Navi.getProperty("ViewAudit_BackButton"));
				fnsApps_Report_Logs("Clicked Back Button of View Audit Page.");
				fngCheckErrMsgTest("FAILED-ClickBackButtonOfViewAuditpage");
				fngClickByXpath(OR_Navi.getProperty("ViewClient_SnapshotTab"));
				fnsApps_Report_Logs("Clicked SnapshotTab of View Client page.");
				fngCheckErrMsgTest("FAILED-ClickSnapshotTabOfViewClientPage");
				//***************************** Visit Link Operation under Audit tab Start *********************************
				String clickedVisitNo=fngCheckAndClickLink(OR_Navi.getProperty("ViewClient_Snapshot_Audits_VisitLink"),OR_Navi.getProperty("ViewClient_Snapshot_Audits_Table"));
				fngLoading_wait();
				if(!clickedVisitNo.isEmpty()){
					//String visitPageHeader=getTextValueByXpath(OR_Navi.getProperty("ViewVisitPage_Header"));//AuditHeaderXpath
					String openedVisitNo=fngGetTextValueByXpath(OR_Navi.getProperty("ViewVisitPage_Header"));
					Assert.assertTrue(openedVisitNo.equalsIgnoreCase(clickedVisitNo),"Clicked & Open Visit No is Mismatched!!");
					fnsApps_Report_Logs("Corresponding Visit is opened!!!");
					fngClickByXpath(OR_Navi.getProperty("ViewVisit_BackButton"));
					fnsApps_Report_Logs("Clicked Back Button on View Visit Page");
					fngCheckErrMsgTest("FAILED-ClickBackButtonOfViewVisit");
				}else{
					fnsApps_Report_Logs("Visit No is not present");
				}
				fngClickByXpath(OR_Navi.getProperty("ViewClient_SnapshotTab"));
				fnsApps_Report_Logs("Clicked SnapshotTab of View Client Page.");
				fngCheckErrMsgTest("FAILED-ClickSnapshotTabOfViewClientPage");
			}
		}
		boolean isHistoricalWOTabAvailable=fngIsElementVisible(OR_Navi.getProperty("ViewClient_Snapshot_ViewHistoricalWOs_In_OpenWOsTab"));
		if(isHistoricalWOTabAvailable){
			fngClickByXpath(OR_Navi.getProperty("ViewClient_Snapshot_ViewHistoricalWOs_In_OpenWOsTab"));
			fnsApps_Report_Logs("Clicked View Historical WOs In Open Work Orders Panel");
			fngCheckErrMsgTest("FAILED-ClickViewHistoricalWoInOpenWo");
			String woLinkHistoricalWOTab=fngGetTextValueByXpath(OR_Navi.getProperty("HistoricalWOs_TableData"));
			if(woLinkHistoricalWOTab.contains("No records found")){
				fnsApps_Report_Logs("No Record Found, is coming, No WO Link available in Historical Wos Popup.");
				fngLoading_wait();
				fngClickByXpath(OR_Navi.getProperty("HistoricalWOsPopup_CloseButton"));  
				fnsApps_Report_Logs("Clicked Close Button of Historical WOs Popup");
				fngCheckErrMsgTest("FAILED-ClickCloseButtonHistoricalPopup");
			}else{
				fngClickByXpath(OR_Navi.getProperty("HistoricalWOsPopup_WoLink"));
				fnsApps_Report_Logs("Clicked WO Link available in Historical Wos Popup ");
				fngCheckErrMsgTest("FAILED-ClickWOLinkInHistoricalWo");
				String woLinkHistoricalOpenedWO=fngGetTextValueByXpath(OR_Navi.getProperty("ViewWorkOrderPage_WorkOrder_InHeader"));
				Assert.assertTrue(woLinkHistoricalWOTab.equalsIgnoreCase(woLinkHistoricalOpenedWO));
				fngClickByXpath(OR_Navi.getProperty("ViewWorkOrderPage_BackButton"));
				fnsApps_Report_Logs("Clicked Back Button of View Work Order Page.");
				fngCheckErrMsgTest("FAILED-ClickBackButtonOfViewWorkOrderPage");
				fngClickByXpath(OR_Navi.getProperty("ViewClient_SnapshotTab"));
				fnsApps_Report_Logs("Clicked SnapshotTab of View Client Page.");
				fngCheckErrMsgTest("FAILED-ClickSnapshotTabOfViewClientPage");
			}
		}
		boolean isOpenInvoiceTabAvailable=fngIsElementVisible(OR_Navi.getProperty("ViewClient_OpenInvoiceTab"));
		if(isOpenInvoiceTabAvailable){
			String msg=fngGetTextValueByXpath(OR_Navi.getProperty("ViewClient_OpenInvoiceTab_Table"));
			if(msg.contains("No records found")){
				fnsApps_Report_Logs("No records found under the Open Invoice tab.");
			}else{
				//do code when data is available under the open Invoices panel
			}
		}
	}
	public void SnapshotTab_Operation_FoodEquipment() throws Throwable{
		//WO With Open Quotes tab Operation 
		boolean flag=fngIsSelectedTab(OR_Navi.getProperty("ViewClient_SnapshotTab_Selected"), "Snapshot Tab");
		Assert.assertTrue(flag,"Snapshot Tab Must be selected ");
		
		boolean isWoWithOpenQuotesTabAvailable=fngIsElementVisible(OR_Navi.getProperty("ViewClient_Snapshot_WOwithOpenQuotesTab"));
		if(isWoWithOpenQuotesTabAvailable){
			String dataInWOWithOpenQuotesTab=fngGetTextValueByXpath(OR_Navi.getProperty("RowData_WOWithOpenQuotesTab"));
			if(dataInWOWithOpenQuotesTab.contains("No records found")){
				fnsApps_Report_Logs("No Record found, No WO links are available in WO with Open Quotes panel");
			}else{
				//need to do code when data is available under the WO with open Quotes panel
			}
		}
		//Open Work Orders Tab operation 
		boolean isWOOpenTabAvailable=fngIsElementVisible(OR_Navi.getProperty("ViewClient_SnapshotTab_OpenWOTab"));
		if(isWOOpenTabAvailable){
			String woFromOpenWoTab=fngCheckAndClickLink(OR_Navi.getProperty("ViewClient_Snapshot_OpenWOsTab_WoLink"),OR_Navi.getProperty("ViewClient_Snapshot_OpenWOs_Table"));
			fnsApps_Report_Logs("Clicked on WO link in Open WOs tab & WO Link is : "+woFromOpenWoTab);
			fngLoading_wait();
			String openedWO=fngGetTextValueByXpath(OR_Navi.getProperty("WO_InHeader"));
			fnsApps_Report_Logs("Got Work Order in Edit Work Order Page is :"+openedWO);
			Assert.assertTrue(openedWO.contains(woFromOpenWoTab),"Opened WO is mismatched!!!");
			fnsApps_Report_Logs("Corresponding Work order is opened!!");
		}
		fngClickByXpath(OR_Navi.getProperty("EditWorkOrder_BackButton"));
		fnsApps_Report_Logs("Clicked Back Button of Edit Work Order Page");
		fngCheckErrMsgTest("FAILED-afterClickBackButton");
		fngLoading_wait();
		boolean isProposalWithOpenQuotesTabAvailable=fngIsElementVisible(OR_Navi.getProperty("ViewClient_Snapshot_ProposalWithOpenQuotesTab"));
		if(isProposalWithOpenQuotesTabAvailable){
			String dataFromProposalOpenQuotesTab=fngCheckAndClickLink(OR_Navi.getProperty("ViewClient_Snapshot_ProposalWithOpenQuotesTab_ProposalLink"),OR_Navi.getProperty("ViewClient_Snapshot_ProposalWithOpenQuotesTab_ProposalTable"));
			fngLoading_wait();
			if(dataFromProposalOpenQuotesTab.contains("No records found") && !dataFromProposalOpenQuotesTab.isEmpty()){
				fnsApps_Report_Logs("No Record found, No WO links are available in Proposal with Open Quotes panel");
			}else{
				String openedProposal=fngGetTextValueByXpath(OR_Navi.getProperty("OpenedProposal_With_OpenQuotesTab"));
				Assert.assertTrue(dataFromProposalOpenQuotesTab.contains(openedProposal),"Opened Proposal is mismatched!!!");
				fnsApps_Report_Logs("Appropriate Proposal is opened!!");
				flag=fngIsSelectedTab(OR_Navi.getProperty("ClientInfoTab_Selected"),"Client Info");
				Assert.assertTrue(flag,"Client Info Tab Must be selected ");
				fngClickByXpath(OR_Navi.getProperty("ViewProposalPage_BackButton"));
				fnsApps_Report_Logs("Clicked Back Button of View Proposa page");
				fngCheckErrMsgTest("AfterClickBackButton");
				flag=fngIsSelectedTab(OR_Navi.getProperty("ViewClient_SnapshotTab_Selected"), "Snapshot Tab");
				Assert.assertTrue(flag,"Snapshot Tab Must be selected ");
			}
			boolean isAuditTab=fngIsElementVisible(OR_Navi.getProperty("ViewClient_Snapshot_AuditTab"));
			if(isAuditTab){
				//***************************** Visit Link Operation under Audit tab *********************************
				String auditTabRows=fngGetTextValueByXpath(OR_Navi.getProperty("ViewClient_Snapshot_Audits_Rows"));
				if(auditTabRows.contains("No records found")){
					fnsApps_Report_Logs("No Record Found, is coming, No Audit & Visit Link available in Audit Tab.");
				}else{
					String clickedAuditNo=fngCheckAndClickLink(OR_Navi.getProperty("ViewClient_Snapshot_Audits_AuditLinks"),OR_Navi.getProperty("ViewClient_Snapshot_Audits_Table"));
					//String AuditPageHeader=getTextValueByXpath(OR_Navi.getProperty("ViewAuditPage_Header"));
					fnsApps_Report_Logs("Clicked on the audit link in Audit# column under the AuditTab is : "+clickedAuditNo);
					fngLoading_wait();
					String openedAuditNo=fngGetTextValueByXpath(OR_Navi.getProperty("ViewAudit_Header_AuditNo"));
					fnsApps_Report_Logs("Open Audit no in the Header of View Audit Page is : "+clickedAuditNo);
					Assert.assertTrue(clickedAuditNo.equalsIgnoreCase(openedAuditNo),"Clicked & Open Audit No is Mismatched!!");
					fnsApps_Report_Logs("Corresponding audit is opened !!!");
					
					fngClickByXpath(OR_Navi.getProperty("ViewAudit_BackButton"));
					fnsApps_Report_Logs("Clicked Back Button of View Audit Page.");
					fngCheckErrMsgTest("AfterClickBackButtonViewAudit"); fngLoading_wait();
					fngClickByXpath(OR_Navi.getProperty("ViewClient_SnapshotTab"));
					fnsApps_Report_Logs("Clicked SnapshotTab of View Client Page.");
					fngCheckErrMsgTest("Error-AfterSnapshotTab");
					fngLoading_wait();
					//***************************** Visit Link Operation under Audit tab *********************************
					String clickedVisitNo=fngCheckAndClickLink(OR_Navi.getProperty("ViewClient_Snapshot_Audits_VisitLink"),OR_Navi.getProperty("ViewClient_Snapshot_AuditsTable_VisitNo"));//ViewClient_Snapshot_Audits_Table
					//String visitPageHeader=getTextValueByXpath(OR_Navi.getProperty("ViewVisitPage_Header"));
					fnsApps_Report_Logs("Clicked on the Visit link in Visit# column under the AuditTab is : "+clickedVisitNo);
					fngLoading_wait();
					String openedVisitNo=fngGetTextValueByXpath(OR_Navi.getProperty("ViewVisit_Header_AuditNo"));
					fnsApps_Report_Logs("Open Visit No in the Header of View Visit Page is : "+openedVisitNo);
					Assert.assertTrue(clickedVisitNo.equalsIgnoreCase(openedVisitNo),"Clicked & Open Visit No is Mismatched!!");
					fnsApps_Report_Logs("Corresponding Visit is opened!!!");
					fngClickByXpath(OR_Navi.getProperty("ViewVisit_BackButton"));
					fnsApps_Report_Logs("Clicked Back Button on View Visit Page");
					fngCheckErrMsgTest("Error-AfterBackButtonViewVisit");
					fngLoading_wait();
					//***************************** WO Link Operation under Audit tab ********************************* 
					//Temporary Comment bcs wo is not available in WO column,
					/*String woLink=fngCheckAndClickLink(OR_Navi.getProperty("ViewClient_Snapshot_Audits_WOLink"),OR_Navi.getProperty("ViewClient_Snapshot_AuditsTable_WONo"));//ViewClient_Snapshot_Audits_Table
					fnsApps_Report_Logs("Clicked on the Work Order in WO column under the AuditTab is : "+woLink);
					fngLoading_wait();
					String openedWO=fngGetTextValueByXpath(OR_Navi.getProperty("WO_InHeader"));
					fnsApps_Report_Logs("Open Work Order in the Header of Edit Work Order Page is : "+openedWO);;
					Assert.assertTrue(openedWO.contains(woLink),"Clicked & Open Work Order is Mismatched!!");
					fnsApps_Report_Logs("Corresponding WO is opened");
					fngClickByXpath(OR_Navi.getProperty("EditWorkOrder_BackButton"));
					fnsApps_Report_Logs("Clicked Back Button of Edit Work Order Page.");
					fngCheckErrMsgTest("Error-AfterBackButtonEditWOPage");*/
					fngLoading_wait();
				}
			}else{
				fnsApps_Report_Logs("Audit Section is not available into Snapshot tab of View Client Page.");
			}
			boolean isOpenInvoiceTabAvailable=fngIsElementVisible(OR_Navi.getProperty("ViewClient_OpenInvoiceTab"));
			if(isOpenInvoiceTabAvailable){
				String rowsInInvoiceTable=fngGetTextValueByXpath(OR_Navi.getProperty("ViewClient_Snapshot_OpenInvoices_Rows"));
				if(rowsInInvoiceTable.contains("No records found")){
					fnsApps_Report_Logs("No Record Found, is coming, No invoice Link available in Open Invoice Tab.");
				}else{
					fngCheckAndClickInvoiceLink(OR_Navi.getProperty("ViewClient_Snapshot_OpenInvoices_InvoiceLink"),OR_Navi.getProperty("ViewClient_Snapshot_OpenInvoices_InvoiceTable"));
					//fnsApps_Report_Logs("Clicked Invoice Link in Open Invoices Tab under the Snapshot Tab of View Client Page. ");
					fngLoading_wait();
					String woLinkOpenInvoiceTab=fngCheckAndClickLink(OR_Navi.getProperty("ViewClient_Snapshot_OpenInvoicesTab_WOLink"),OR_Navi.getProperty("ViewClient_Snapshot_OpenInvoices_InvoiceTable"));
					fnsApps_Report_Logs("Clicked Work Order Link in Open Invoices Tab under the Snapshot Tab of View Client Page.");
					fngLoading_wait();
					String openedWO=fngGetTextValueByXpath(OR_Navi.getProperty("WO_InHeader"));
					fnsApps_Report_Logs("Got Opened WO : "+openedWO);
					Assert.assertTrue(openedWO.contains(woLinkOpenInvoiceTab),"Clicked & Open Work Order is Mismatched!!");
					fnsApps_Report_Logs("Corresponding WO is opened");
					fngClickByXpath(OR_Navi.getProperty("EditWorkOrder_BackButton"));
					fnsApps_Report_Logs("Clicked Back Button of Edit Work Order Page");
					fngCheckErrMsgTest("Error-AfterBackButtonEditWOPage");
					fngLoading_wait();
				}
			}
		}
	}
	public void FacilitiesTab_Operation() throws Throwable{
		boolean isFacilitiesTabAvailable=fngIsElementVisible(OR_Navi.getProperty("ViewClient_FacilitiesTab"));
		if(isFacilitiesTabAvailable){
			fngClickByXpath(OR_Navi.getProperty("ViewClient_FacilitiesTab"));
			fnsApps_Report_Logs("Clicked Facilities Tab of View Client Page. ");
			fngCheckErrMsgTest("Error-AfterFacilitiesTabOfViewClient");
			fngLoading_wait(); 
			String invoicesTableData=fngGetTextValueByXpath(OR_Navi.getProperty("ViewClient_Facilities_TableData"));
			if(invoicesTableData.contains("No records found")){
				fnsApps_Report_Logs("No Record Found, is coming in Facilities Tab of View Client Page.");
			}else{
				String facilityCodeLink=fngCheckAndClickLink(OR_Navi.getProperty("ViewClient_FacilitiesTab_CodeLink"),OR_Navi.getProperty("ViewClient_FacilitiesTab_CodeValue"));//ViewClient_Facilities_TableData
				fnsApps_Report_Logs("Clicked Facility Code Link: "+facilityCodeLink);
				fngLoading_wait();
				String openedFacilityCode=fngGetTextValueByXpath(OR_Navi.getProperty("ViewClient_OpenClientInHeader"));
				fnsApps_Report_Logs("Facility Code Link: "+facilityCodeLink+" & Opened Facility Code: "+openedFacilityCode);
				Assert.assertTrue(openedFacilityCode.contains(facilityCodeLink),"Opened Facility Code is mismatched!!");
				fnsApps_Report_Logs("Corresponding client gets opened");
				fngClickByXpath(OR_Navi.getProperty("ViewClient_BackButton"));
				fnsApps_Report_Logs("Clicked Back Button of View Client Page");
				fngLoading_wait();
				fngClickByXpath(OR_Navi.getProperty("ViewClient_FacilitiesTab"));
				fnsApps_Report_Logs("Clicked Facilities Tab of View Client Page."); fngCheckErrMsgTest("Error-AfterFacilitiesTabOfViewClient"); fngLoading_wait();
				boolean VeiwAMPLinkInFacilitiesTab=fngIsElementVisible(OR_Navi.getProperty("ViewClient_FacilitiesTab_VeiwAMPLink"));
				if(VeiwAMPLinkInFacilitiesTab){
					fngClickByXpath(OR_Navi.getProperty("ViewClient_FacilitiesTab_VeiwAMPLink"));
					fnsApps_Report_Logs("Clicked Veiw AMP Link under the Facilities Tab of View Client Page.");
					fngCheckErrMsgTest("Error-AfterClickViewAmpLink");
					fngLoading_wait();
					int present=driver.findElements(By.xpath(OR_Navi.getProperty("AMP_Detail_Popup_Header"))).size();
					//boolean AmpDetailPopupAvialable=fngIsElementVisible(OR_Navi.getProperty("AMP_Detail_Popup_Header"));
					//if(AmpDetailPopupAvialable){
					if(present>0){
						String dataInFirstRow=fngGetTextValueByXpath(OR_Navi.getProperty("AMP_Detail_Popup_AMPCodeLink"));
						if(dataInFirstRow.contains("No records found")){
							fnsApps_Report_Logs("No records found, No Link available into AMP Detail Popup");
						}else{
							String AMPCodeLink=fngCheckAndClickLink(OR_Navi.getProperty("AMP_Detail_Popup_AMPCodeLink"),OR_Navi.getProperty("AMP_Detail_Popup_TableBody_Xpath"));
							fngLoading_wait();
							fnsApps_Report_Logs("Clicked Code link is : "+AMPCodeLink);
							fngLoading_wait();
						}
					}
					String veiwAMPPageTitle=fngGetTextValueByXpath(OR_Navi.getProperty("ViewAnnualMonitorPlan_Header"));
					fnsApps_Report_Logs("Header of the View Annual Monitor Plan Page is : "+veiwAMPPageTitle);
					Assert.assertTrue(veiwAMPPageTitle.contains("View Annual Monitor Plan"), "Annual Monitor Plan page Title is missmatched.");
					fngClickByXpath(OR_Navi.getProperty("ViewAnnualMonitorPlan_BackButton"));
					fnsApps_Report_Logs("Clicked on the Back Button of View Annual Monitor Plan Page");
					fngCheckErrMsgTest("Error-AfterClickBackButton");
					fngLoading_wait();
					/*boolean documentsTabSelected=fngIsSelectedTab(OR_Navi.getProperty("DocumentsTab_Selected"));
					assertTrue(documentsTabSelected,"After click on BackButton of View Annual Monitor Plan Page,Documnets Tab should be Open");
					fngClickByXpath(OR_Navi.getProperty("BackButtonXpath"));
					fnsApps_Report_Logs("Clicked on the Back Button of View client Page");*/
					boolean facilitiesTabSelected=fngIsSelectedTab(OR_Navi.getProperty("ViewClient_FacilitiesTabIsSelected"),"Facilities");
					assertTrue(facilitiesTabSelected,"After click on BackButton of View Annual Monitor Plan Page ,Facilities Tab should be Selected on View Client Page.");
				}
			}
		}else{
				fnsApps_Report_Logs("Facilities Tab is not available of View Client Page.");
		}
	}

	public void LinkTab_Operation() throws Throwable{
		boolean isLinkTabAvailable=fngIsElementVisible(OR_Navi.getProperty("ViewClient_LinksTab"));
		if(isLinkTabAvailable){
			fngClickByXpath(OR_Navi.getProperty("ViewClient_LinksTab"));
			fnsApps_Report_Logs("Clicked LinksTab of View Client Page  ");
			fngCheckErrMsgTest("Error-AfterLinksTabOfViewClient");
			fngLoading_wait();
			String dataInFirstRow=fngGetTextValueByXpath(OR_Navi.getProperty("ViewClient_LinksTab_LinksTable"));
			if(dataInFirstRow.contains("No records found")){
				fnsApps_Report_Logs("No records found, No Link available into Client Relationship of Link Tab of View Client Page");
			}else{
				String relationshipCodeLink=fngCheckAndClickLink(OR_Navi.getProperty("ViewClient_LinksTab_CodeLink"),OR_Navi.getProperty("ViewClient_LinksTab_LinksTableRow"));
				fngLoading_wait();
				fnsApps_Report_Logs("Clicked Code link is : "+relationshipCodeLink);
				String openedRelationshipCode=fngGetTextValueByXpath(OR_Navi.getProperty("OpenedCodeLink_RelationshipTable"));
				fnsApps_Report_Logs("Opened Code link is : "+relationshipCodeLink);
				Assert.assertTrue(openedRelationshipCode.contains(relationshipCodeLink),"Respective client in not get opened");
				fnsApps_Report_Logs("Respective client gets opened");
				fngClickByXpath(OR_Navi.getProperty("BackButtonXpath"));
				fnsApps_Report_Logs("Clicked Back Button");
				fngCheckErrMsgTest("Error-AfterClickBackButton");
				fngLoading_wait();
			}
		}
	}
	
	public void ContractsTab_Operation() throws Throwable{
		boolean isContractTabAvailable=fngIsElementVisible(OR_Navi.getProperty("ViewClient_ContractsTab"));
		if(isContractTabAvailable){
			fngClickByXpath(OR_Navi.getProperty("ViewClient_ContractsTab"));
			fnsApps_Report_Logs("Clicked Contracts Tab of View Client Page  ");
			fngCheckErrMsgTest("Error-AfterContractsTabOfViewClient");
			//String viewContractLink=checkAndClickLink(OR_Navi.getProperty("ViewContractsLink_TableUnderContractsTab"),OR_Navi.getProperty("ContractsTable_UnderContractsTab"));
			String viewContractLink=fngGetTextValueByXpath(OR_Navi.getProperty("ViewClient_ContractsTab_ContractsData"));//ViewClient_ContractsTab_ContractsLink
			fnsApps_Report_Logs("Got View Contract Link under the Contracts Tab : "+viewContractLink);
			if(!viewContractLink.contains("No records found") && !viewContractLink.isEmpty()){
				fngClickByXpath(OR_Navi.getProperty("ViewClient_ContractsTab_ContractsLink"));//ViewContractsLink_TableUnderContractsTab
				fnsApps_Report_Logs("Clicked View Contract Link under the Contract Column");
				fngCheckErrMsgTest("Error-ClickViewContractLink");
			}else{
				fnsApps_Report_Logs("No records found Or no View Contract Link is coming under the Contract Column");
			}
		}
	}
	
	public void NSFOnlineAccountsTab_Operation(String clientID) throws Throwable{
		boolean isContractTabAvailable=fngIsElementVisible(OR_Navi.getProperty("ViewClient_NSFOnlineAccountsTab"));
		if(isContractTabAvailable){
			fngClickByXpath(OR_Navi.getProperty("ViewClient_NSFOnlineAccountsTab"));
			fnsApps_Report_Logs("Clicked NSF Online Accounts Tab of View Client Page");
			fngCheckErrMsgTest("Error-ClickNSFOnlineAccountsTab");
			//String userNameLink=checkAndClickLink(OR_Navi.getProperty("UserNameLink_UnderNSFOnlineAccontsTab"),OR_Navi.getProperty("UserNameTable_UnderNSFOnlineAccontsTab"));'
			String clientDetailInHeader=fngGetTextValueByXpath(OR_Navi.getProperty("ViewClient_InHeader_clientDetail"));
			
			String userNameLink=fngGetTextValueByXpath(OR_Navi.getProperty("ViewClient_NSFOnlineAccontsTab_SearchResultPanel_Links_UserName"));
			if(!userNameLink.contains("No records found")){
				fngClickByXpath(OR_Navi.getProperty("ViewClient_NSFOnlineAccontsTab_SearchResultPanel_Links_UserName"));
				fnsApps_Report_Logs("Clicked Link under the User Name of Search Result Panel in NSFOnlineAccontsTab of View client Page.");
				fngCheckErrMsgTest("Error-ClickViewContractLink");
				fngLoading_wait();
				openNSFOLPortal();
				String OpeneduserNameLink=fngGetTextValueByXpath(OR_Navi.getProperty("NSFOnlineAcconts_Client_Number"));
				Assert.assertTrue(clientDetailInHeader.contains(OpeneduserNameLink),"Client Value is mismatched.");
			}else{
				fnsApps_Report_Logs("No Records Found in UserName column in NSF Online Accountsracts Tab.");
			}
		}
	}
	public void openNSFOLPortal() throws Throwable{
		fnsApps_Report_Logs("Going to open SAM Tab and enter the Auditor name.");
		oldTab=driver.getWindowHandle();
		samTab = new ArrayList<String>(driver.getWindowHandles());
		samTab.remove(oldTab);
		driver.switchTo().window(samTab.get(0));
		fngLoading_wait();
	}
	
	public boolean isHitorycalPopupClose(String xpath) throws Exception{
		boolean flag=false;
		try{
			int elementNo=driver.findElements(By.xpath(xpath)).size();
			if(elementNo>0){
				WebElement element=driver.findElement(By.xpath(xpath));
				Thread.sleep(300);
				if(!element.isDisplayed()){
					flag=true;
					Thread.sleep(300);
					fnsApps_Report_Logs("Historycal Popup is Close!!!");
				}
			}
		}catch(NoSuchElementException e){
			fnsApps_Report_Logs("FAILED == Element is not found >> "+xpath );
			throw new Exception("FAILED == Element is not found >> "+xpath );
		}catch(TimeoutException e){
			fnsApps_Report_Logs("FAILED == Element is not Present, getting TimeOut >> "+xpath);
			throw new Exception("FAILED == Element is not Present, getting TimeOut >> "+xpath);
		}catch(StaleElementReferenceException e){
			fnsApps_Report_Logs("FAILED == Element is not found >> "+xpath +" "+Throwables.getStackTraceAsString(e));
			throw new Exception("FAILED == Element is not found >> "+xpath);
		}catch(Throwable e){
			fnsApps_Report_Logs("FAILED == Unable To Select the Element having Xpath >> "+xpath);
			throw new Exception("FAILED == Unable To Select the Element having Xpath >> "+xpath);
		}return flag;
	}
	

}
