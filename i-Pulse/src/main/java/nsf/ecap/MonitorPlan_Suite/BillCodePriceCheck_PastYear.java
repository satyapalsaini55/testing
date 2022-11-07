package nsf.ecap.MonitorPlan_Suite;

//
import nsf.ecap.util.TestUtil;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class BillCodePriceCheck_PastYear extends TestSuiteBase_MonitorPlan{
	
	public boolean IsBrowserPresentAlready = false;
	public boolean fail = false;
	public boolean skip = true;
	public boolean WO_Not_Click = true;

	public Integer iterationstart=0;
	public int count = -1;
	public String WorkOrderIDText;
	public String WO_UnitSchedAmtValue;
	public String WO_BillCodeValue;
	public String WO_UnitSchedAmtXpath;
	public String WO_BillCodeXpath;
	public Object DB_BillRateValue;
	
	
	public String WorkOrder_Sequence = null;
	public String Search_WOStatus = null;
	public String WO_BillCode_Year = null;
	public String Bill_Code__RateNotMatch = "";
	public String Bill_Rate__RateNotMatch = "";
	public String WO_Task_Description_Xpath1 = null;
	public String WO_Task_Description_Xpath2 = null;
	public String WO_Task_Description_Text = null;
	public String MP_Task_Description_Row_Xpath = null;
	public String MP_Task_Description = null;
	public String MP_NotMatch_BillCode_Price_Fetch = null;
	public String CreateForYearTextValue = null;
	public String RevenueProgramTextValue = null;
	public String SnapshotTabTaskTableText = null;
	public Integer TotalTaskCount = null;
	
	public boolean BillRate_NotMatch_MoveTo_MP;
	public boolean View_AMP_Link_Exists;
	public boolean First_Not_Match_Bill_Rate_Flag;
	public boolean WO_BillRate_Verify_From_MP;
	
	
	
	
	
	

	@Parameters({ "className" })
	@BeforeTest
	public void checkTestSkip(String className) throws Exception {
		
		if (className.isEmpty()) {
			className = this.getClass().getSimpleName();		}
		IsBrowserPresentAlready =false;
		fnsCheckClassLevelTestSkip(className);
	}
	
	
	

	
	
	
	

@Test( priority = 0)
public void Launch_Browser_and_Successfully_Login_into_the_Application() throws Throwable{
	fnsApps_Report_Logs("################################## [BS - 03] Bill Price Check W.R.T. DataBase Value");
	if (!IsBrowserPresentAlready) {		
		IsBrowserPresentAlready = fnsLaunchBrowserAndLogin();
		fnsApps_Report_Logs("Browser is launched and Successfully login into Application");	
	}
	fnsApps_Report_Logs("=========================================================================================================================================");
}	
	
	
	
	
	
	
	@Test(dataProvider = "getTestData", dependsOnMethods={"Launch_Browser_and_Successfully_Login_into_the_Application"})
	public void Verify_WOBillRateDisplay_Vs_DataBaseBillRateValue_for_WorkOrderSeqNo(String WorkOrderSequence, String SearchWOStatus, String BillCodeYear) throws Exception{
		TC_Step=1;
		count++;
		iterationstart++;
		WorkOrder_Sequence = WorkOrderSequence.trim();
		Search_WOStatus = SearchWOStatus.trim();
		WO_BillCode_Year = BillCodeYear.trim();
		
		View_AMP_Link_Exists = false;
		First_Not_Match_Bill_Rate_Flag = true;
		WO_BillRate_Verify_From_MP = true;
		
		WorkOrderIDText = "";
		WO_UnitSchedAmtValue = "";
		WO_BillCodeValue = "";
		WO_UnitSchedAmtXpath = "";
		WO_BillCodeXpath = "";
		
		Bill_Code__RateNotMatch = "";
		Bill_Rate__RateNotMatch = "";
		WO_Task_Description_Xpath1 = "";
		WO_Task_Description_Xpath2 = "";
		WO_Task_Description_Text = "";
		MP_Task_Description_Row_Xpath = "";
		MP_Task_Description = "";
		MP_NotMatch_BillCode_Price_Fetch = "";
		
		
	
		
		try {
			if (!(runmodes[(iterationstart-1)].equalsIgnoreCase("Y"))) {
				fnsApps_Report_Logs("****************************************************************************************************************************************");
				fnsApps_Report_Logs("#########   Runmode for Work Order Sequence No=" + (iterationstart)+" is set to NO, So Skipping this Case.");
				skip=true;
				throw new SkipException("#########   Runmode for Work Order Sequence No=" + (iterationstart)+" is set to NO, So Skipping this Case.");
			}else{
				skip=false;
				fnsApps_Report_Logs(":::::::   CASE <"+WorkOrder_Sequence+"> script start   ::::::: -----");
				fnsSearchWorkOrder_Ajax_Link_Click();
				
				fnsLoading_Progressingwait(10);	
				fnsGet_Element_Enabled("SearchWorkOrder_CorpFacility_TextField");

				fncSearchWO_By_SearchCriteria(WorkOrder_Sequence, Search_WOStatus, WO_BillCode_Year);
				
				fncVerify_WO_BillRate_Without_Opening_MP();
				
				if(BillRate_NotMatch_MoveTo_MP){
					
					//Clicking on WO Facilities Tab
					for(int clicktry=0; clicktry<26; clicktry++){
						fnsGet_Element_Enabled("EditWorkOrder_FacilitiesTAB");
						fnsWait_and_Click("EditWorkOrder_FacilitiesTAB");
						fnsLoading_Progressingwait(2);	
						if(driver.findElements(By.xpath(OR_MtrPlan.getProperty("EditWorkOrder_FacilitiesTAB_IncludeExistingFacilityStandard_Bttn"))).size()>0){	break;		}
						if(clicktry==25){
							isTestCasePass = false;
							fnsTake_Screen_Shot("TabOpenFail");
							throw new Exception("FAILED == <Info> TAB is not opened, Please refer screen shot >> TabOpenFail"+fnsScreenShot_Date_format());
						}
					}	
					
					fnsGet_Element_Enabled("EditWorkOrder_FacilitiesTAB_IncludeExistingFacilityStandard_Bttn");
					Thread.sleep(1500);
					
					if(driver.findElements(By.xpath(OR_MtrPlan.getProperty("EditWorkOrder_FacilitiesTAB_ViewAMP_Link"))).size()>0){
						View_AMP_Link_Exists = true;
						fnsGet_Element_Enabled("EditWorkOrder_FacilitiesTAB_ViewAMP_Link");
						fnsApps_Report_Logs("PASSED == There is no need to add 'View AMP' link as it is already exists.");							
					}else{
						View_AMP_Link_Exists = false;
						fnsWait_and_Click("EditWorkOrder_FacilitiesTAB_IncludeExistingFacilityStandard_Bttn");
						fnsLoading_Progressingwait(3);
						fnsGet_Element_Enabled("EditWorkOrder_FacilitiesTAB_SelectFacilityStandard_LK_Text");					
						fnsSelect_lookup_FirstCheckBox_and_Return();
						fnsLoading_Progressingwait(2);
						
						String Error_Text = fnsGet_Field_TEXT("Success_Message_Div").trim();
						if( (Error_Text.toLowerCase()).equals("standard(s) cannot be added to the annual work order when they are in apply status.")){
							fnsApps_Report_Logs("*********************************************************************************************************************************************");
							fnsApps_Report_Logs("###################### ::: Case <"+WorkOrder_Sequence+"> END :::  PASSED == As Expected : WO Facilities TAB : -- Unable to Verify Bill Rate as getting error <"+Error_Text+"> in facilities TAB, So rest of the Test Steps skipped for Work Order("+WorkOrderIDText+").   ###########");
							fnsApps_Report_Logs("*********************************************************************************************************************************************");
							
						}else{
							
							//Clicking on WO Info Tab
							for(int clicktry=0; clicktry<26; clicktry++){
								fnsGet_Element_Enabled("ViewWorkOrder_Info_Tab");
								fnsWait_and_Click("ViewWorkOrder_Info_Tab");
								fnsLoading_Progressingwait(2);	
								if(driver.findElements(By.xpath(OR_MtrPlan.getProperty("ViewWorkOrder_InfoTab_CreateforYear_Text"))).size()>0){	break;		}
								if(clicktry==25){
									isTestCasePass = false;
									fnsTake_Screen_Shot("TabOpenFail");
									throw new Exception("FAILED == <Info> TAB is not opened, Please refer screen shot >> TabOpenFail"+fnsScreenShot_Date_format());
								}
							}
							
							
							//Clicking on WO Facilities Tab
							for(int clicktry=0; clicktry<26; clicktry++){
								fnsGet_Element_Enabled("EditWorkOrder_FacilitiesTAB");
								fnsWait_and_Click("EditWorkOrder_FacilitiesTAB");
								fnsLoading_Progressingwait(2);	
								if(driver.findElements(By.xpath(OR_MtrPlan.getProperty("EditWorkOrder_FacilitiesTAB_IncludeExistingFacilityStandard_Bttn"))).size()>0){	break;		}
								if(clicktry==25){
									isTestCasePass = false;
									fnsTake_Screen_Shot("TabOpenFail");
									throw new Exception("FAILED == <Info> TAB is not opened, Please refer screen shot >> TabOpenFail"+fnsScreenShot_Date_format());
								}
							}	
							
							Thread.sleep(1000);							
							if(driver.findElements(By.xpath(OR_MtrPlan.getProperty("EditWorkOrder_FacilitiesTAB_ViewAMP_Link"))).size()>0){
								View_AMP_Link_Exists = true;
								fnsApps_Report_Logs("PASSED == Successfully added standards into the facilities TAB.");
							}
						}
					}
					
					if(View_AMP_Link_Exists){
						fnsWait_and_Click("EditWorkOrder_FacilitiesTAB_ViewAMP_Link");
						Thread.sleep(2000);
						fnsLoading_Progressingwait(2);
						fnsGet_Element_Enabled("EditAnnualMonitorPlan_Tasks_TAB");
						
						fnsWait_and_Click("EditAnnualMonitorPlan_Tasks_TAB");
						fnsLoading_Progressingwait(2);
						
						fnsGet_Element_Enabled("TaskTab_ShowAll_Link");
						fnsWait_and_Click("TaskTab_ShowAll_Link");
						Thread.sleep(3000);
						fnsLoading_Progressingwait(2);
						
						//Verify WO bill Rate with MP Bill Rate
						for(int TaskTAB_billcodematch=1; TaskTAB_billcodematch<=30; TaskTAB_billcodematch++){
							MP_Task_Description_Row_Xpath = "//tbody[@id='mainForm:tabView:mptasksdt_data']/tr["+(2*(TaskTAB_billcodematch-1)+1)+"]";
							if(driver.findElements(By.xpath(MP_Task_Description_Row_Xpath)).size()>0){
								MP_Task_Description = WithOut_OR_fnsGet_Field_TEXT(MP_Task_Description_Row_Xpath).toUpperCase().trim();
								if(MP_Task_Description.contains(WO_Task_Description_Text)){
									MP_NotMatch_BillCode_Price_Fetch = fnVerify_Fetch_PriceValues_of_BillingCode(TaskTAB_billcodematch, Bill_Code__RateNotMatch);
									MP_NotMatch_BillCode_Price_Fetch = MP_NotMatch_BillCode_Price_Fetch.split("\\.")[0];							
									try{
										assert MP_NotMatch_BillCode_Price_Fetch.equals(Bill_Rate__RateNotMatch):"#######  FAILED == [WO>>"+WorkOrderIDText+"] and it's Bill rate ("+Bill_Rate__RateNotMatch+") for Bill Code("+Bill_Code__RateNotMatch+") is not matched with MP Bill Rate("+MP_NotMatch_BillCode_Price_Fetch+").";
										fnsApps_Report_Logs("=============================================================================================================================================");
										fnsApps_Report_Logs("#######  PASSED ==  [WO>>"+WorkOrderIDText+"] and it's Bill rate ("+Bill_Rate__RateNotMatch+") for Bill Code("+Bill_Code__RateNotMatch+") is matched with MP Bill Rate("+MP_NotMatch_BillCode_Price_Fetch+").");
										fnsApps_Report_Logs("=============================================================================================================================================");
									}catch(Throwable t){
										isTestCasePass = false;
										fnsTake_Screen_Shot("BillRateVerificationFail");
										fnsApps_Report_Logs("=============================================================================================================================================");
										fnsApps_Report_Logs(t.getMessage()+", plz see screenshot [BillRateVerificationFail" + fnsScreenShot_Date_format() + "].");
										fnsApps_Report_Logs("=============================================================================================================================================");
										throw new Exception(t.getMessage()+", plz see screenshot [BillRateVerificationFail" + fnsScreenShot_Date_format() + "].");		
									}
									
									WO_BillRate_Verify_From_MP = true;
									break;
								}
							}else{
								if(WO_BillRate_Verify_From_MP){
									break;
								}else{
									
									fnsGet_Element_Enabled("EditAnnualMonitorPlan_TestingTasks_TAB");
									fnsWait_and_Click("EditAnnualMonitorPlan_TestingTasks_TAB");
									fnsLoading_Progressingwait(2);
									
									fnsGet_Element_Enabled("TestingTaskTab_ShowAll_Link");
									fnsWait_and_Click("TestingTaskTab_ShowAll_Link");
									Thread.sleep(3000);
									fnsLoading_Progressingwait(2);
									
									for(int TestingTAB_billcodematch=1; TestingTAB_billcodematch<=30; TestingTAB_billcodematch++){
										MP_Task_Description_Row_Xpath = "//tbody[@id='mainForm:tabView:mpttV_data']/tr["+(2*(TaskTAB_billcodematch-1)+1)+"]";
										if(driver.findElements(By.xpath(MP_Task_Description_Row_Xpath)).size()>0){
											MP_Task_Description = WithOut_OR_fnsGet_Field_TEXT(MP_Task_Description_Row_Xpath).toUpperCase().trim();
											if(MP_Task_Description.contains(WO_Task_Description_Text)){
												MP_NotMatch_BillCode_Price_Fetch = fnVerify_Fetch_PriceValues_of_BillingCode(TestingTAB_billcodematch, Bill_Code__RateNotMatch);
												MP_NotMatch_BillCode_Price_Fetch = MP_NotMatch_BillCode_Price_Fetch.split("\\.")[0];
												try{
													assert MP_NotMatch_BillCode_Price_Fetch.equals(Bill_Rate__RateNotMatch):"#######  FAILED == [WO>>"+WorkOrderIDText+"] and it's Bill rate ("+Bill_Rate__RateNotMatch+") for Bill Code("+Bill_Code__RateNotMatch+") is not matched with MP Bill Rate("+MP_NotMatch_BillCode_Price_Fetch+").";
													fnsApps_Report_Logs("=============================================================================================================================================");
													fnsApps_Report_Logs("#######  PASSED ==  [WO>>"+WorkOrderIDText+"] and it's Bill rate ("+Bill_Rate__RateNotMatch+") for Bill Code("+Bill_Code__RateNotMatch+") is matched with MP Bill Rate("+MP_NotMatch_BillCode_Price_Fetch+").");
													fnsApps_Report_Logs("=============================================================================================================================================");
												}catch(Throwable t){
													isTestCasePass = false;
													fnsTake_Screen_Shot("BillRateVerificationFail");
													fnsApps_Report_Logs("=============================================================================================================================================");
													fnsApps_Report_Logs(t.getMessage()+", plz see screenshot [BillRateVerificationFail" + fnsScreenShot_Date_format() + "].");
													fnsApps_Report_Logs("=============================================================================================================================================");
													throw new Exception(t.getMessage()+", plz see screenshot [BillRateVerificationFail" + fnsScreenShot_Date_format() + "].");		
												}
												
												WO_BillRate_Verify_From_MP = true;
												break;
											}
										}else{
											if(WO_BillRate_Verify_From_MP){
												break;
											}else{
												fnsTake_Screen_Shot("WO_BillCode_Not_Exists_into_MP");
												throw new Exception ("FAILED == [WO>>"+WorkOrderIDText+"] and it's Bill Code("+Bill_Code__RateNotMatch +") is not exists into monitor plan, please refer screen shot >> WO_BillCode_Not_Exists_into_MP"+fnsScreenShot_Date_format());
											}
										}
									}
								}
							}
						}
						
						
						//Clicking on BACK button from MP
						fnsGet_Element_Enabled("Back_Bttn");
						fnsWait_and_Click("Back_Bttn");
						fnsLoading_Progressingwait(3);	
						Thread.sleep(3000);
						fnsLoading_Progressingwait(3);	
						
						//Verify All Bill Code's Rate with DB Bill Rate value     except  only those TASK whose bill is verified in MP. 
						fnc_WO_TaskTAB_Verify_BillRate_with_DB_BillRate_Value();
										
						First_Not_Match_Bill_Rate_Flag = false;
						
						fnsApps_Report_Logs("*********************************************************************************************************************************************");
						fnsApps_Report_Logs("###################### ::: Case <"+WorkOrder_Sequence+"> END :::  PASSED == Successfully verified all Bills Rate for Work Order("+WorkOrderIDText+").   ###########");
						fnsApps_Report_Logs("*********************************************************************************************************************************************");
						
					}		
				}
		}
			
	}catch(SkipException sk){
		throw new SkipException(sk.getMessage());
	
			
	}catch(Throwable t){
		fail = true;
		isTestCasePass = false;
		fnsApps_Report_Logs(t.getMessage());
		throw new Exception(t.getMessage());}						
		
}




//######################################################################################################################################################################################
//------------------------------------------------------------  Classs's Method -------------------------------------------------------------------------------------------------
//#####################################################################################################################################################################################	
	

	
//Search Work Order by specific criteria : WO Status (In-progress), Work Order Type(Annual) and WO Summary Contains (2016)
public void fncSearchWO_By_SearchCriteria(String WO_Sequence , String WO_Status, String WO_BillCodeYear ) throws Throwable{
	
	WO_Not_Click = true;
		
	String SearchWorkOrder_WOStatus_DDValue_Select_Xpath = "//li[contains(text(), '"+WO_Status+"')]";
	WithOut_OR_fnGet_Element_Enabled(SearchWorkOrder_WOStatus_DDValue_Select_Xpath);
	WithOut_OR_fnClick(SearchWorkOrder_WOStatus_DDValue_Select_Xpath);
	
	fnsGet_Element_Enabled("SearchWorkOrder_WorkOrderType_DD");
	WebElement DdElement=fnsGet_OR_MtrPlan_ObjectX("SearchWorkOrder_WorkOrderType_DD");
	Actions action=new Actions(driver);	
	Thread.sleep(500);
	action.moveToElement(DdElement, 0, 0).build().perform();
	Thread.sleep(500);
	//action.moveToElement(DdElement, 145, 50).click().click().click().click().click().build().perform(); //Working on IE9.0
//	action.moveToElement(DdElement, 145, 50).click().click().click().click().click().click().click().click().click().click().click().click().click().click().click().click().click().click().click().click().build().perform();
	action.moveToElement(DdElement, 185, 50).click().click().click().click().click().click().click().click().click().click().click().click().click().click().click().build().perform();
	
	
	fnsMove_To_Element_and_Click("SearchWorkOrder_WorkOrderType_Annual");
	fnsWait_and_Click("SearchWorkOrder_WorkOrderType_Annual");


	fnsGet_Element_Enabled("SearchWorkOrder_WOSummaryContains_TextField");
	fnsWait_and_Type("SearchWorkOrder_WOSummaryContains_TextField", WO_BillCodeYear);

	fnsGet_Element_Enabled("SearchWorkOrder_Search_Bttn");
	fnsWait_and_Click("SearchWorkOrder_Search_Bttn");

	fnsLoading_Progressingwait(10);	
	fnsGet_Element_Enabled("SearchWorkOrder_SearchResult_TableHeader");
	fnsRetrun_TotalRowCount_OfTable("SearchWorkOrder_SearchResult_TableHeader");
	
	for(int i=0; i<30; i++){
		String SearchResultTableFirstRowXpath = "//*[@id='mainForm:stdSearchC_data']/tr["+WO_Sequence+"]";
		String SearchResultTableFirstRowText = WithOut_OR_fnsGet_Field_TEXT(SearchResultTableFirstRowXpath).trim();
		if((SearchResultTableFirstRowText.toLowerCase()).contains(WO_Status.toLowerCase()) && (SearchResultTableFirstRowText.toLowerCase()).contains("annual")){
			String WOIdTabelCellXpath="//*[@id='mainForm:stdSearchC_data']/tr["+WO_Sequence+"]/td[1]";
			WorkOrderIDText=WithOut_OR_fnsGet_Field_TEXT(WOIdTabelCellXpath).trim();
			WO_Not_Click = false;
			break;
		}else{
			Thread.sleep(1000);
		}
	}
	
	if(WO_Not_Click){
		fnsSearchWorkOrder_Ajax_Link_Click();
		
		fnsLoading_Progressingwait(10);	
		fnsGet_Element_Enabled("SearchWorkOrder_CorpFacility_TextField");
		
		fnsGet_Element_Enabled("SearchWorkOrder_WOStatus_DDClick");
		fnsDD_value_Select_By_MatchingText("SearchWorkOrder_WOStatus_DDClick", "SearchWorkOrder_WOStatus_DDValue", "li",  WO_Status);
		
		fnsGet_Element_Enabled("SearchWorkOrder_WorkOrderType_DD");
		/*WebElement DdElement1=fnsGet_OR_MtrPlan_ObjectX("SearchWorkOrder_WorkOrderType_DD");
		Actions act=new Actions(driver);*/
		action.moveToElement(DdElement, 0, 0).build().perform();
		action.moveToElement(DdElement, 145, 50).click().click().click().click().click().build().perform();
		fnsMove_To_Element_and_Click("SearchWorkOrder_WorkOrderType_Annual");
		fnsWait_and_Click("SearchWorkOrder_WorkOrderType_Annual");

		


		fnsGet_Element_Enabled("SearchWorkOrder_WOSummaryContains_TextField");
		fnsWait_and_Type("SearchWorkOrder_WOSummaryContains_TextField", WO_BillCodeYear);

		fnsGet_Element_Enabled("SearchWorkOrder_Search_Bttn");
		fnsWait_and_Click("SearchWorkOrder_Search_Bttn");

		fnsLoading_Progressingwait(10);	
		fnsGet_Element_Enabled("SearchWorkOrder_SearchResult_TableHeader");
		fnsRetrun_TotalRowCount_OfTable("SearchWorkOrder_SearchResult_TableHeader");
		
		String SearchResultTableFirstRowXpath = "//*[@id='mainForm:stdSearchC_data']/tr["+WO_Sequence+"]";
		String SearchResultTableFirstRowText = WithOut_OR_fnsGet_Field_TEXT(SearchResultTableFirstRowXpath).trim();
		if((SearchResultTableFirstRowText.toLowerCase()).contains(WO_Status.toLowerCase()) && (SearchResultTableFirstRowText.toLowerCase()).contains("annual")){
			String WOIdTabelCellXpath="//*[@id='mainForm:stdSearchC_data']/tr["+WO_Sequence+"]/td[1]";
			WorkOrderIDText=WithOut_OR_fnsGet_Field_TEXT(WOIdTabelCellXpath).trim();
			WO_Not_Click = false;
		}
	}
	if(WO_Not_Click){
		isTestCasePass = false;
		fnsTake_Screen_Shot("NoWOs");
		fnsApps_Report_Logs("=============================================================================================================================================");
		fnsApps_Report_Logs("FAILED == No WorkOrders are found in <"+WO_Status+"> Status on searchWOScreen, Please refer screen shot >> NoWOs"+fnsScreenShot_Date_format());
		fnsApps_Report_Logs("=============================================================================================================================================");
		throw new Exception("FAILED == No WorkOrders are found in <"+WO_Status+"> Status on searchWOScreen, Please refer screen shot >> NoWOs"+fnsScreenShot_Date_format());
	}
}	
	


	
//Open WO from Search WO screen. Fetch CreateForYear and RevenueProgram from Info and Financial TAB then Open TASK TAB and verify Bill Rate with DB Rill Rate.
public void fncVerify_WO_BillRate_Without_Opening_MP() throws Throwable{
	
	fnsTable_ClickOn_LINK_ByMatchingText(WorkOrderIDText);
	fnsLoading_Progressingwait(3);	
	fnsGet_Element_Enabled("Footer");
	fnsGet_Element_Enabled("ViewWorkOrder_SnapshotTab_TaskSummary_TableHeader");

	SnapshotTabTaskTableText=fnsGet_Field_TEXT("ViewWorkOrder_SnapshotTab_TaskSummary_TableHeader").toLowerCase().trim();
	
//	BillRate_NotMatch_MoveTo_MP = false;
	for(int TaskExistsCheckStart=0; TaskExistsCheckStart<iterationstart; TaskExistsCheckStart++){
						
		if(SnapshotTabTaskTableText.contains("no tasks associated with this work order yet.")){
			fnsTake_Screen_Shot("NoTaskAssociated");
			fnsApps_Report_Logs("=============================================================================================================================================");
			fnsApps_Report_Logs("PASSED == BillRate Verification Stoped as there is no Task Exists for this WorkOrder("+WorkOrderIDText+", Please refer screen shot >> NoTaskAssociated" +fnsScreenShot_Date_format() );
			fnsApps_Report_Logs("=============================================================================================================================================");
			break;
		}else{
				
			TotalTaskCount=fnsRetrun_TotalRowCount_OfTable("ViewWorkOrder_SnapshotTab_TaskSummary_TableHeader");	
			System.out.println("TotalTaskCount ===  "+fnsRetrun_TotalRowCount_OfTable("ViewWorkOrder_SnapshotTab_TaskSummary_TableHeader"));
			
			//Clicking on WO Info Tab
			for(int clicktry=0; clicktry<26; clicktry++){
				fnsGet_Element_Enabled("ViewWorkOrder_Info_Tab");
				fnsWait_and_Click("ViewWorkOrder_Info_Tab");
				fnsLoading_Progressingwait(2);	
				if(driver.findElements(By.xpath(OR_MtrPlan.getProperty("ViewWorkOrder_InfoTab_CreateforYear_Text"))).size()>0){	break;		}
				if(clicktry==25){
					isTestCasePass = false;
					fnsTake_Screen_Shot("TabOpenFail");
					throw new Exception("FAILED == <Info> TAB is not opened, Please refer screen shot >> TabOpenFail"+fnsScreenShot_Date_format());
				}
			}
			
			fnsGet_Element_Enabled("ViewWorkOrder_InfoTab_CreateforYear_Text");
			fnsMove_To_Element_and_DoubleClick("ViewWorkOrder_InfoTab_CreateforYear_Text");
			CreateForYearTextValue=fnsGet_Field_TEXT("ViewWorkOrder_InfoTab_CreateforYear_Text").trim();
			fnsText_Fetch_and_Assert("ViewWorkOrder_InfoTab_CreateforYear_Text", WO_BillCode_Year);
			
			
			
			//Clicking on WO Financial Tab
			for(int clicktry=0; clicktry<6; clicktry++){
				fnsGet_Element_Enabled("EditAnnualMonitorPlan_Financials_TAB");
				fnsWait_and_Click("EditAnnualMonitorPlan_Financials_TAB");
				fnsLoading_Progressingwait(2);	
				if(driver.findElements(By.xpath(OR_MtrPlan.getProperty("FinancialsTab_RevenueProgram_Xpath"))).size()>0){	break;		}
				if(clicktry==5){
					isTestCasePass = false;
					fnsTake_Screen_Shot("TabOpenFail");
					throw new Exception("FAILED == <Financials> TAB is not opened, Please refer screen shot >> TabOpenFail"+fnsScreenShot_Date_format());
				}
			}
						
			fnsGet_Element_Enabled("FinancialsTab_RevenueProgram_Xpath");
			fnsMove_To_Element_and_DoubleClick("FinancialsTab_RevenueProgram_Xpath");
			RevenueProgramTextValue=(fnsGet_Field_TEXT("FinancialsTab_RevenueProgram_Xpath").split("\\-")[0]).trim();
			
			fnc_WO_TaskTAB_Verify_BillRate_with_DB_BillRate_Value();
						
			}
		break;
	}
	if( !(BillRate_NotMatch_MoveTo_MP) ){
		fnsApps_Report_Logs("*********************************************************************************************************************************************");
		fnsApps_Report_Logs("###################### ::: Case <"+WorkOrder_Sequence+"> END :::  PASSED == Successfully verified all Bills Rate for Work Order("+WorkOrderIDText+").   ###########");
		fnsApps_Report_Logs("*********************************************************************************************************************************************");
	}

}



//Open TASK TAB and verify Bill Rate with DB Rill Rate except  only those TASK whose bill is verified in MP. 
public void fnc_WO_TaskTAB_Verify_BillRate_with_DB_BillRate_Value() throws Throwable{
	
	BillRate_NotMatch_MoveTo_MP = false;
	//Clicking on WO Task Tab
	for(int clicktry=0; clicktry<26; clicktry++){
		fnsGet_Element_Enabled("ViewWorkOrder_Tasks_Tab");
		fnsWait_and_Click("ViewWorkOrder_Tasks_Tab");
		fnsLoading_Progressingwait(2);	
		if(driver.findElements(By.xpath(OR_MtrPlan.getProperty("ViewWorkOrder_TasksTab_TaskList_MainTableHeader"))).size()>0){   break;   }
		if(clicktry==25){
			isTestCasePass = false;
			fnsTake_Screen_Shot("TabOpenFail");
			throw new Exception("FAILED == <Tasks> TAB is not opened, Please refer screen shot >> TabOpenFail"+fnsScreenShot_Date_format());
		}
	}
	
	
	fnsGet_Element_Enabled("ViewWorkOrder_ShowAll_Link");
	fnsWait_and_Click("ViewWorkOrder_ShowAll_Link");
	fnsLoading_Progressingwait(8);	
		
	
	for(int BillCodeCountNo=0; BillCodeCountNo<TotalTaskCount; BillCodeCountNo++){  
		
		String AddSingleBttn_Xpath = ".//*[@id='mainForm:tabView:dataTable:"+BillCodeCountNo+":appTaskAddSingleBtn']";
		String BillCodeTableHeaderXpaths="//*[@id='mainForm:tabView:dataTable:"+BillCodeCountNo+":jobExpence']/div/table";
		WO_Task_Description_Xpath1 = "//*[@id='mainForm:tabView:dataTable:"+BillCodeCountNo+":taskdesccolmn']";
		WO_Task_Description_Xpath2 = "//*[@id='mainForm:tabView:dataTable:"+BillCodeCountNo+":taskDescrColumn']";
		
		
		if(driver.findElements(By.xpath(BillCodeTableHeaderXpaths)).size()>0){
			
			WithOut_OR_fnGet_Element_Enabled(BillCodeTableHeaderXpaths);
			Thread.sleep(1500);
			if(driver.findElements(By.xpath(WO_Task_Description_Xpath1)).size()>0){
				WO_Task_Description_Text = WithOut_OR_fnsGet_Field_TEXT(WO_Task_Description_Xpath1).toUpperCase().trim();
			}else if(driver.findElements(By.xpath(WO_Task_Description_Xpath2)).size()>0){
				WO_Task_Description_Text = WithOut_OR_fnsGet_Field_TEXT(WO_Task_Description_Xpath2).toUpperCase().trim();
			}else {
				fnsTake_Screen_Shot("TaskDescrColumn_Not_Present");
				throw new Exception ("FAILED == 'Task Description' Column is not present.");
			}
			
			String CheckBillAdded_Text=WithOut_OR_fnsGet_Field_TEXT(BillCodeTableHeaderXpaths).toLowerCase().trim();
			System.out.println("CheckBillAdded_Text="+CheckBillAdded_Text);
			
			if(CheckBillAdded_Text.contains("no billing codes set up for the task.")){
				fnsApps_Report_Logs("PASSED == BillRate Verification is not done as there no billing codes set up for the task having xpath >>"+BillCodeTableHeaderXpaths);
			}else{	
				Integer BillCodeCountPerTask=WithOut_OR_Retrun_TotalRowCount_OfTable(BillCodeTableHeaderXpaths);
				System.out.println(" BillCodeCountPerTask == "+BillCodeCountPerTask);
				
				
				if(driver.findElements(By.xpath(AddSingleBttn_Xpath)).size()==0){									
					for(int BillCodeCountStart=1; BillCodeCountStart<=BillCodeCountPerTask; BillCodeCountStart++){
						
						WO_UnitSchedAmtXpath="//*[@id='mainForm:tabView:dataTable:"+BillCodeCountNo+":jobExpence_data']/tr["+BillCodeCountStart+"]/td[5]";
						System.out.println("UnitSchedAmtXpath=="+WO_UnitSchedAmtXpath);
						WO_BillCodeXpath="//*[@id='mainForm:tabView:dataTable:"+BillCodeCountNo+":jobExpence_data']/tr["+BillCodeCountStart+"]/td[1]";
						System.out.println("BillCodeXpath=="+WO_BillCodeXpath);
						
						WO_BillCodeValue=WithOut_OR_fnsGet_Field_TEXT(WO_BillCodeXpath);
						
						if( !(MP_Task_Description.contains(WO_Task_Description_Text)) ){
							DB_BillRateValue=fnFetchBillCodeRate_from_DB(WO_BillCodeValue, CreateForYearTextValue,RevenueProgramTextValue).get(0);
							
							WithOut_OR_fnMove_To_Element_and_DoubleClick(WO_UnitSchedAmtXpath);
							WO_UnitSchedAmtValue=(WithOut_OR_fnsGet_Field_TEXT(WO_UnitSchedAmtXpath).split("\\.")[0]).trim();
							WO_UnitSchedAmtValue=WO_UnitSchedAmtValue.replace(",", "").trim();
							
							if(DB_BillRateValue.equals(WO_UnitSchedAmtValue)){
								try{
									assert DB_BillRateValue.equals(WO_UnitSchedAmtValue):"#######  FAILED == [WO>>"+WorkOrderIDText+"], Bill rate 'UnitSchedAmtValue("+WO_UnitSchedAmtValue+")' for Bill Code("+WO_BillCodeValue+") and OrgUnit("+RevenueProgramTextValue+") is not matched with DB Bill Rate("+DB_BillRateValue+").";
									fnsApps_Report_Logs("=============================================================================================================================================");
									fnsApps_Report_Logs("#######  PASSED == [WO>>"+WorkOrderIDText+"], Bill rate 'UnitSchedAmtValue("+WO_UnitSchedAmtValue+")' for Bill Code("+WO_BillCodeValue+") and OrgUnit("+RevenueProgramTextValue+") is matched with DB Bill Rate("+DB_BillRateValue+").");
									fnsApps_Report_Logs("=============================================================================================================================================");
								}catch(Throwable t){
									isTestCasePass = false;
									fnsTake_Screen_Shot("BillRateVerificationFail");
									fnsApps_Report_Logs("=============================================================================================================================================");
									fnsApps_Report_Logs(t.getMessage()+", plz see screenshot [BillRateVerificationFail" + fnsScreenShot_Date_format() + "].");
									fnsApps_Report_Logs("=============================================================================================================================================");
									throw new Exception(t.getMessage()+", plz see screenshot [BillRateVerificationFail" + fnsScreenShot_Date_format() + "].");		
								}
							}else if (First_Not_Match_Bill_Rate_Flag){
								fnsApps_Report_Logs("PASSED == Bill Rate DB value and WO value are not matched, so navigating to MP.");
								Bill_Code__RateNotMatch = WO_BillCodeValue;
								Bill_Rate__RateNotMatch = WO_UnitSchedAmtValue;
								BillRate_NotMatch_MoveTo_MP = true;
								break;
							}
						}
					} 
					
				}else {
					for(int BillCodeCountStart=2; BillCodeCountStart<=BillCodeCountPerTask; BillCodeCountStart++){
						
						WO_UnitSchedAmtXpath="//*[@id='mainForm:tabView:dataTable:"+BillCodeCountNo+":jobExpence_data']/tr["+BillCodeCountStart+"]/td[6]";
						System.out.println("UnitSchedAmtXpath=="+WO_UnitSchedAmtXpath);
						WO_BillCodeXpath="//*[@id='mainForm:tabView:dataTable:"+BillCodeCountNo+":jobExpence_data']/tr["+BillCodeCountStart+"]/td[2]";
						System.out.println("BillCodeXpath=="+WO_BillCodeXpath);
						
						WO_BillCodeValue=WithOut_OR_fnsGet_Field_TEXT(WO_BillCodeXpath);
						
						if( !( MP_Task_Description.contains(WO_Task_Description_Text) ) ){	
							DB_BillRateValue=fnFetchBillCodeRate_from_DB(WO_BillCodeValue, CreateForYearTextValue,RevenueProgramTextValue).get(0);
							
							WithOut_OR_fnMove_To_Element_and_DoubleClick(WO_UnitSchedAmtXpath);
							WO_UnitSchedAmtValue=(WithOut_OR_fnsGet_Field_TEXT(WO_UnitSchedAmtXpath).split("\\.")[0]).trim();
							WO_UnitSchedAmtValue=WO_UnitSchedAmtValue.replace(",", "").trim();
							
							if(DB_BillRateValue.equals(WO_UnitSchedAmtValue)){
								try{
									assert DB_BillRateValue.equals(WO_UnitSchedAmtValue):"#######  FAILED == [WO>>"+WorkOrderIDText+"], Bill rate 'UnitSchedAmtValue("+WO_UnitSchedAmtValue+")' for Bill Code("+WO_BillCodeValue+") and OrgUnit("+RevenueProgramTextValue+") is not matched with DB Bill Rate("+DB_BillRateValue+").";
									fnsApps_Report_Logs("=============================================================================================================================================");
									fnsApps_Report_Logs("#######  PASSED == [WO>>"+WorkOrderIDText+"], Bill rate 'UnitSchedAmtValue("+WO_UnitSchedAmtValue+")' for Bill Code("+WO_BillCodeValue+") and OrgUnit("+RevenueProgramTextValue+") is matched with DB Bill Rate("+DB_BillRateValue+").");
									fnsApps_Report_Logs("=============================================================================================================================================");
								}catch(Throwable t){
									isTestCasePass = false;
									fnsTake_Screen_Shot("BillRateVerificationFail");
									fnsApps_Report_Logs("=============================================================================================================================================");
									fnsApps_Report_Logs(t.getMessage()+", plz see screenshot [BillRateVerificationFail" + fnsScreenShot_Date_format() + "].");
									fnsApps_Report_Logs("=============================================================================================================================================");
									throw new Exception(t.getMessage()+", plz see screenshot [BillRateVerificationFail" + fnsScreenShot_Date_format() + "].");		
								}
							}else  if (First_Not_Match_Bill_Rate_Flag){
								fnsApps_Report_Logs("PASSED == Bill Rate DB value and WO value are not matched, so navigating to MP.");
								Bill_Code__RateNotMatch = WO_BillCodeValue;
								Bill_Rate__RateNotMatch = WO_UnitSchedAmtValue;
								BillRate_NotMatch_MoveTo_MP = true;
								break;
							}
						}
					} 
			
				}
			
				
			} 
		} 
		
		if(BillRate_NotMatch_MoveTo_MP){
			break;
		}
		
	}
}




//######################################################################################################################################################################################
//------------------------------------------------------------  Configurations Method -------------------------------------------------------------------------------------------------
//#####################################################################################################################################################################################

@AfterMethod
public void reportDataSetResult() {
	if(count>-1){
		if (skip)
			TestUtil.reportDataSetResult(MonitorPlan_suitexls, this.getClass().getSimpleName(), iterationstart+1 , "SKIP");
		else if (fail) {
			isTestCasePass = false;
			TestUtil.reportDataSetResult(MonitorPlan_suitexls, this.getClass().getSimpleName(), iterationstart+1, "FAIL");
		} else
			TestUtil.reportDataSetResult(MonitorPlan_suitexls, this.getClass().getSimpleName(), iterationstart+1, "PASS");

		skip = false;
		fail = false;
	}
}



	
	
@AfterTest
public void reportTestResult() throws Throwable {
	
	fns_ReportTestResult_atClassLevel(MonitorPlan_suitexls,  (this.getClass().getSimpleName()), isTestCasePass );
}

@AfterTest
public void QuitBrowser(){
	driver.quit();
}



@DataProvider
public Object[][] getTestData() {
	return TestUtil.getData(MonitorPlan_suitexls, this.getClass().getSimpleName());
}
	
	
	
}